package com.example.demo.services.exceptions;

public class ForbiddenException extends RuntimeException {
	public ForbiddenException(String msg) {
		super(msg);
	}
}
