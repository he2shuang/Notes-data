package com.yugabyte.v3.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.yugabyte.v3.dto.ErrorResponse;
import com.yugabyte.v3.exception.BusinessException;
import com.yugabyte.v3.exception.ErrorCode;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * レスポンスユーティリティクラス
 * 標準化されたHTTPレスポンスを構築するために使用
 */
public class ResponseUtil {
    
    private static final Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        .create();
    
    /**
     * 成功レスポンスを構築
     */
    public static HttpResponseMessage success(HttpRequestMessage<?> request, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", data);
        response.put("timestamp", System.currentTimeMillis());
        
        return request.createResponseBuilder(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(gson.toJson(response))
                .build();
    }
    
    /**
     * エラーレスポンスを構築
     */
    public static HttpResponseMessage error(HttpRequestMessage<?> request, HttpStatus status, ErrorResponse error) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", error);
        response.put("timestamp", System.currentTimeMillis());
        
        return request.createResponseBuilder(status)
                .header("Content-Type", "application/json")
                .body(gson.toJson(response))
                .build();
    }
    
    /**
     * ビジネス例外レスポンスを構築
     */
    public static HttpResponseMessage businessError(HttpRequestMessage<?> request, BusinessException exception) {
        return error(request, HttpStatus.BAD_REQUEST, exception.toErrorResponse());
    }
    
    /**
     * 検証例外レスポンスを構築（ErrorCodeを使用）
     */
    public static HttpResponseMessage validationError(HttpRequestMessage<?> request, ErrorCode errorCode, String errorName, String errorDetail) {
        ErrorResponse error = new ErrorResponse(errorCode.getCode(), errorName != null ? errorName : "-", errorDetail);
        return error(request, HttpStatus.BAD_REQUEST, error);
    }
    
    /**
     * データベース例外レスポンスを構築（ErrorCodeを使用）
     */
    public static HttpResponseMessage databaseError(HttpRequestMessage<?> request, ErrorCode errorCode, String errorName, String errorDetail) {
        ErrorResponse error = new ErrorResponse(errorCode.getCode(), errorName != null ? errorName : "-", errorDetail);
        return error(request, HttpStatus.INTERNAL_SERVER_ERROR, error);
    }
    
    /**
     * 未キャッチ例外レスポンスを構築
     */
    public static HttpResponseMessage internalError(HttpRequestMessage<?> request, Exception exception) {
        ErrorResponse error = new ErrorResponse(
            ErrorCode.INTERNAL_ERROR.getCode(),
            "system",
            "システム内部エラー。管理者に連絡してください"
        );
        
        // ログに記録するがクライアントには公開しない
        return error(request, HttpStatus.INTERNAL_SERVER_ERROR, error);
    }
    
    /**
     * 設定エラーレスポンスを構築
     */
    public static HttpResponseMessage configError(HttpRequestMessage<?> request, ErrorCode errorCode, String errorName, String errorDetail) {
        ErrorResponse error = new ErrorResponse(errorCode.getCode(), errorName != null ? errorName : "-", errorDetail);
        return error(request, HttpStatus.INTERNAL_SERVER_ERROR, error);
    }
}
