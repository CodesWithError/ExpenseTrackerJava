package com.msl.ExpenseTracker.enums;

public enum ServiceCode {
	SIGNUP01("SIGNUP01","User created successfully"),
	SIGNUP03("SIGNUP03","User already exist"),
	SIGNUP04("SIGNUP04","FullName can not be empty"),
	SIGNUP05("SIGNUP05","Mobile Number already exist"),
	SIGNUP06("SIGNUP06","Password does not match"),
	SIGNUP07("SIGNUP07","Unable to process your request, please try again later"),
	SIGNUP08("SIGNUP08","Name must be of 3 to 25 length with no special characters"),
	SIGNUP09("SIGNUP09","Enter valid Email"),
	SIGNUP10("SIGNUP10","password must contain at least 1 uppercase , 1 lowercase, 1 special character and 1 digit and length should be Greater than Equal to 8."),
	SIGNUP11("SIGNUP11","A valid mobile number generally have 10 digits (in India)."),
	LOGIN01("LOGIN01","Login Successfully"),
	LOGIN02("LOGIN02","Invalid username or password"),
	EXP01("EXP01","Expenses added"),
	EXP02("EXP02","Invalid Expenses data"),
	INC01("INC01","Income Added"),
	INC02("INC02","Invalid Income data"),
	VER01("VER01","Email verified successfully"),
	VER02("VER02","Please check your email !! to forget the password "),
	VER03("VER03","Please enter valid email"),
	ETA01("ETA01","Password reseted Successfully"),
	ETA02("ETA02","Invalid or expired token"),
	ETA03("ETA03","Password already used!! Please enter new password"),
	ETA04("ETA04","Id can not be zero! Please enter valid id");


	private final String code;
	private final String message;

	private ServiceCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
