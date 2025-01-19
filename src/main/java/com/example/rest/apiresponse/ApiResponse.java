package com.example.rest.apiresponse;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

	private int statusCode;
	private String message;
	private LocalDateTime timeStamp;
	
}
