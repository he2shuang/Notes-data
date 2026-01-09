


[访问 API 时出现 Azure 错误 405？以下是快速修复的方法 --- Azure Error 405 When Accessing API? Here’s How to Fix It Fast](https://www.hostingseekers.com/blog/azure-error-405-fix/)

[新部署的 Azure 函数返回 404 未找到错误 - Stack Overflow --- New deployed azure function returns 404 Not Found error - Stack Overflow](https://stackoverflow.com/questions/64942139/new-deployed-azure-function-returns-404-not-found-error)

[如何排查 Azure Function HTTP Trigger 404 错误 --- How to troubleshoot Azure Function HTTP Trigger 404 Error](https://techcommunity.microsoft.com/blog/appsonazureblog/how-to-troubleshoot-azure-functions-http-trigger-404-error/3766817)

[我的 Azure 函数应用显示 404 错误，尽管日志显示计时器和队列功能正常——Microsoft 问答 --- My Azure functions app is returning 404 errors although the logs show timers and queue functions working - Microsoft Q&A](https://learn.microsoft.com/en-us/answers/questions/2145998/my-azure-functions-app-is-returning-404-errors-alt)

[Azure API 管理限制解决方法：当 HTTP 方法不匹配时返回 404 而非 405 |Microsoft 社区中心 --- Azure API Management limitation workaround: Return 404 instead of 405 when the HTTP method not match | Microsoft Community Hub](https://techcommunity.microsoft.com/blog/azurepaasblog/azure-api-management-limitation-workaround-return-404-instead-of-405-when-the-ht/1588413)

[Azure Functions HTTP 触发器 | Microsoft Learn](https://learn.microsoft.com/zh-cn/azure/azure-functions/functions-bindings-http-webhook-trigger?tabs=python-v2%2Cisolated-process%2Cnodejs-v4%2Cfunctionsv2&pivots=programming-language-java)

[HttpTrigger 接口 |Microsoft Learn --- HttpTrigger Interface | Microsoft Learn](https://learn.microsoft.com/zh-cn/java/api/com.microsoft.azure.functions.annotation.httptrigger?view=azure-java-stable#com-microsoft-azure-functions-annotation-httptrigger-methods\(\))

[在本地开发并运行 Azure Functions | Microsoft Learn](https://learn.microsoft.com/zh-cn/azure/azure-functions/functions-develop-local?pivots=programming-language-java)


[函数应用应返回已知路由但不支持 HTTP 方法的请求 405。第#2452 期 ·Azure/azure-functions-dotnet-worker --- Function app should return 405 for request on known route but unsupported HTTP method · Issue #2452 · Azure/azure-functions-dotnet-worker](https://github.com/Azure/azure-functions-dotnet-worker/issues/2452)



源码探索：
[aspnetcore/src/Http/Routing/src/EndpointMiddleware.cs at main · dotnet/aspnetcore](https://github.com/dotnet/aspnetcore/blob/main/src/Http/Routing/src/EndpointMiddleware.cs)
[aspnetcore/src/Http/Routing/src/Constraints/HttpMethodRouteConstraint.cs at main · dotnet/aspnetcore](https://github.com/dotnet/aspnetcore/blob/main/src/Http/Routing/src/Constraints/HttpMethodRouteConstraint.cs)
[azure-functions-host/src/WebJobs.Script.WebHost/WebScriptHostHttpRoutesManager.cs at 9f7c22ed9bce3b1707c30345d2fc102bc2898569 · Azure/azure-functions-host](https://github.com/Azure/azure-functions-host/blob/9f7c22ed9bce3b1707c30345d2fc102bc2898569/src/WebJobs.Script.WebHost/WebScriptHostHttpRoutesManager.cs)




[azure-functions-host/src/WebJobs.Script.WebHost/WebScriptHostHttpRoutesManager.cs at 9f7c22ed9bce3b1707c30345d2fc102bc2898569 · Azure/azure-functions-host](https://github.com/Azure/azure-functions-host/blob/9f7c22ed9bce3b1707c30345d2fc102bc2898569/src/WebJobs.Script.WebHost/WebScriptHostHttpRoutesManager.cs#L37)
 WebJobsRouteBuilder routesBuilder = _router.CreateBuilder

[azure-functions-host/src/WebJobs.Script/Host/ScriptHost.cs at dev · Azure/azure-functions-host](https://github.com/Azure/azure-functions-host/blob/dev/src/WebJobs.Script/Host/ScriptHost.cs)

[azure-maven-plugins/azure-functions-maven-plugin/src/main/java/com/microsoft/azure/maven/function/PackageMojo.java at 3ffa5c961ae9e7c571a369223c1706e15e8c5f4a · microsoft/azure-maven-plugins](https://github.com/microsoft/azure-maven-plugins/blob/3ffa5c961ae9e7c571a369223c1706e15e8c5f4a/azure-functions-maven-plugin/src/main/java/com/microsoft/azure/maven/function/PackageMojo.java#L297)

[azure-maven-plugins/azure-functions-maven-plugin/src/main/java/com/microsoft/azure/maven/function/AbstractFunctionMojo.java at develop · microsoft/azure-maven-plugins](https://github.com/microsoft/azure-maven-plugins/blob/develop/azure-functions-maven-plugin/src/main/java/com/microsoft/azure/maven/function/AbstractFunctionMojo.java)