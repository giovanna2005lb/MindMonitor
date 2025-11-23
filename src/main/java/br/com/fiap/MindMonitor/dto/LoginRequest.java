package br.com.fiap.MindMonitor.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;

    private String password;
}