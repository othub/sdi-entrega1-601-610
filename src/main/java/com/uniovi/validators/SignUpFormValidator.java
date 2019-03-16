package com.uniovi.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

/**
 * 
 * 
 */
@Component
public class SignUpFormValidator implements Validator {
    @Autowired
    private UsersService usersService;

    // these are used for detecting a correct email.
    private String regex = "^(.+)@(.+)$";
    private Pattern pattern = Pattern.compile(regex);

    /**
     * {@inheritDoc}
     * 
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> aClass) {
	return User.class.equals(aClass);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     *      org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
	User user = (User) target;
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.empty");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Error.empty");
	Matcher matcher = pattern.matcher(user.getEmail());

	if (!matcher.matches()) {
	    errors.rejectValue("email", "Error.signup.email.length");
	}

	if (user.getEmail().equals("admin@email.com")) {
	    errors.rejectValue("email", "Error.signup.email.admin");
	}

	if (usersService.getUserByEmail(user.getEmail()) != null) {
	    errors.rejectValue("email", "Error.signup.email.duplicate");
	}

	if (user.getName().length() < 2 || user.getName().length() > 24) {
	    errors.rejectValue("name", "Error.signup.name.length");
	}

	if (user.getLastName().length() < 2 || user.getLastName().length() > 24) {
	    errors.rejectValue("lastName", "Error.signup.lastName.length");
	}

	if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
	    errors.rejectValue("password", "Error.signup.password.length");
	}

	if (!user.getPasswordConfirm().equals(user.getPassword())) {
	    errors.rejectValue("passwordConfirm", "Error.signup.passwordConfirm.coincidence");
	}

    }
}
