package org.example.blabla.util;

import lombok.experimental.UtilityClass;
import org.example.blabla.exception.AppException;

import java.util.regex.Pattern;

@UtilityClass
public class PhoneUtil {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^(\\+7|8|7)?(\\d{10})$");

    public boolean isPhoneNumberValid(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

    public String cutPhoneNumber(String phone) {
        if (!isPhoneNumberValid(phone)) {
            throw new AppException("Phone validation failed");
        }
        var phoneWithoutBrackets = phone.replaceAll("\\)", "").replaceAll("\\(", "");
        return phoneWithoutBrackets.substring(phoneWithoutBrackets.length() - 10);
    }

}
