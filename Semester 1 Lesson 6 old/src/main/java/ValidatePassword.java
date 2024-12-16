//package example.org;
public class ValidatePassword {

    /**
     Validates the password based on specified rules:
     - Length between 5 and 10 characters
     - Must contain at least one uppercase letter
     - Must contain at least one lowercase letter
     - Must contain at least one special character
     - Must contain at least one digit
     */
    public boolean validate(String password) {
        if (password == null || password.length() < 5 || password.length() > 10) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasSpecialChar = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUppercase = true;
            else if (Character.isLowerCase(c)) hasLowercase = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecialChar = true;
        }

        return hasUppercase && hasLowercase && hasSpecialChar && hasDigit;
    }
}