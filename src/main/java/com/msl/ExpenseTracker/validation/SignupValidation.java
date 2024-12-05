package com.msl.ExpenseTracker.validation;

import com.msl.ExpenseTracker.model.Users;
import com.msl.ExpenseTracker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.ExpenseTracker.enums.ServiceCode;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.msl.ExpenseTracker.utility.ConfigReader.getNameRegexp;
import static com.msl.ExpenseTracker.utility.ConfigReader.getEmailRegexp;
import static com.msl.ExpenseTracker.utility.ConfigReader.getMobileRegexp;
import static com.msl.ExpenseTracker.utility.ConfigReader.getPasswordRegexp;

import lombok.Data;

@Component
@Data
public class SignupValidation {
	@Autowired
	UserRepo repo;

	public ServiceCode validateUserDetails(Users user) throws IOException {
		ServiceCode svc = ServiceCode.SIGNUP01;
		
//		Check for name
		Pattern patternName = Pattern.compile(getNameRegexp());
		Matcher searchName = patternName.matcher(user.getName());
		boolean matchName = searchName.find();

//		check for email
		Pattern patternEmail = Pattern.compile(getEmailRegexp());
		Matcher searchEmail = patternEmail.matcher(user.getEmail());
		boolean matchEmail = searchEmail.find();

//		check for password
		Pattern patternPassword = Pattern.compile(getPasswordRegexp());
		Matcher searchPassword = patternPassword.matcher(user.getPassword());
		boolean matchPassword = searchPassword.find();

//		check for mobile no;
		Pattern patternMobile = Pattern.compile(getMobileRegexp());
		Matcher searchMobile = patternMobile.matcher(user.getMobile());
		boolean matchMobileNo = searchMobile.find();

		System.out.println(user);
		 if ( null==user.getName()) {
			svc = ServiceCode.SIGNUP04;
		} else if (!matchName) {
			svc = ServiceCode.SIGNUP08;
		} else if (!matchEmail) {
			svc = ServiceCode.SIGNUP09;
		} else if (!user.getPassword().equals(user.getConfirmPassword())) {
			svc = ServiceCode.SIGNUP06;
		} else if (!matchPassword) {
			svc = ServiceCode.SIGNUP10;
		} else if (!matchMobileNo) {
			svc = ServiceCode.SIGNUP11;
		} else if (repo.findByEmail(user.getEmail())!=null) {
			svc = ServiceCode.SIGNUP03;
		} else if (repo.findByMobile(user.getMobile())!=null) {
			svc = ServiceCode.SIGNUP05;
		} 
		return svc;
	}

}
