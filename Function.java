package com.yugabyte.function;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 引入我们刚刚添加的 Gson 库
import com.google.gson.Gson;

// --- 数据模型类 ---
// 用于映射数据库中的 employees 表的每一行数据
class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    // Getters for Gson serialization
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}


public class Function {
    
    @FunctionName("YugaDbTest")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        context.getLogger().info("Java HTTP trigger to fetch employees.");

        final String dbUrl = System.getenv("DB_URL");
        final String dbUser = System.getenv("DB_USER");
        final String dbPassword = System.getenv("DB_PASSWORD");
        
        // 1. 创建一个列表，用于存放所有从数据库查询到的员工对象
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            
            Statement statement = connection.createStatement();
            // 2. 将 SQL 查询语句替换为查询 employees 表
            String sqlQuery = "SELECT id, name, department, salary FROM employees;";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            
            // 3. 使用 while 循环遍历所有结果行
            while (resultSet.next()) {
                // 4. 从结果集中按列名提取数据
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                double salary = resultSet.getDouble("salary");
                
                // 5. 创建一个 Employee 对象并将其添加到列表中
                employees.add(new Employee(id, name, department, salary));
            }
            
        } catch (Exception e) {
            context.getLogger().severe("Database query failed. Error: " + e.getMessage());
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                          .body("Database query failed: " + e.getMessage())
                          .build();
        }

        // 6. 使用 Gson 将员工列表转换为 JSON 格式的字符串
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(employees);

        // 7. 构建带有 JSON 内容和正确 Content-Type 的 HTTP 响应
        return request.createResponseBuilder(HttpStatus.OK)
                      .header("Content-Type", "application/json") // 告诉浏览器返回的是 JSON
                      .body(jsonResponse)
                      .build();
    }
}