package com.resser.Response;

import lombok.Data;

@Data
public class ApiResponse {
    public ApiResponse(String string, boolean b) {
        //TODO Auto-generated constructor stub
    }
    private String message;
    private boolean status;
}
