package utiles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mir00r on 23/3/23
 * @project IntelliJ IDEA
 */
public class Validators {
//    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String PHONE_PATTERN = "^\\(?([0-9]{3})\\)?[- ]?([0-9]{3})[- ]?([0-9]{4})$";
//    private static final String USA_PHONE_PATTERN = "^(\\([0-9]{3}\\)|[0-9]{3}-)[0-9]{3}-[0-9]{4}$\n";
    private static final String USA_PHONE_PATTERN = "^(1[ -]?)?\\(?(\\d{3})\\)?[ -]?(\\d{3})[ -]?(\\d{4})$";

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePhone(String phone) {
        Pattern pattern = Pattern.compile(USA_PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean isValidPhone(String phoneNumber) {
        return phoneNumber.matches(USA_PHONE_PATTERN);
    }

    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_PATTERN);
    }


    public static boolean validatePhoneAndEmail(String phone, String email) {
        return !validatePhone(phone) || !validateEmail(email);
    }
}
