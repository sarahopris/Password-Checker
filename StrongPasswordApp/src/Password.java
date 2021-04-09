import java.util.function.Function;
import java.util.Random;

public class Password {

    private String password;

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    /**
     * @return - true if the length is bigger than 6 and less than 20 characters
     * - false if else
     */
    public boolean checkLength() {
        return password.length() >= 6 && password.length() <= 20;
    }


    /**
     * @return - true if the password has at least one lowercase letter
     *           false if else
     */
    public Boolean hasLowercase(){
        Function<String, Boolean> hasLowerCase = s -> s.chars().filter(Character::isLowerCase).count() > 0;
        return hasLowerCase.apply(password);
    }

    /**
     * @return - true if the password has at least one uppercase letter
     *           false if else
     */
    public Boolean hasUppercase(){
        Function<String, Boolean> hasUpperCase = s -> s.chars().filter(Character::isUpperCase).count() > 0;
        return hasUpperCase.apply(password);
    }
    /**
     * @return - true if the password has at least one digit
     *           false if else
     */
    public Boolean hasDigit(){
        Function<String, Boolean> hasDigit = s -> s.chars().filter(Character::isDigit).count() > 0;
        return hasDigit.apply(password);
    }

    /**
     * @return true if there are 3 identical characters near each other
     *         false if there are no more than 2 identical characters near each other
     */

    public Boolean charactersNearEachOther() {
        boolean c = false;
        int length = password.length();
        for (int i = 0; i <= length - 3; i++) {
            c = (password.charAt(i) == password.charAt(i + 1) && password.charAt(i + 1) == password.charAt(i + 2) && password.charAt(i) ==password.charAt(i + 2));
            if(c)
                break;
        }
        return c;
    }

    /**
     * @return true if the password is strong
     *         false if else
     */
    public Boolean checkIfStrong() {
        return checkLength() && hasUppercase() && hasLowercase() && hasDigit() && !charactersNearEachOther();
    }


    /**
     * deletes the last character from the password
     * @return     -the new password as string after deleting the last character
     *             - null if else
     */
    public String deletion()
    {
        Password strongPassword;
        StringBuffer stringBuffer = new StringBuffer(password);

        strongPassword = new Password( String.valueOf(stringBuffer.delete(password.length()-1, password.length())));

        return strongPassword.getPassword();

    }

    /**
     * @param  oldChar - represents the problematic character that needs to pe replaced
     * @param  newChar - represents the replacement for the oldChar
     * @return the password after the replacement of one character
     */
    public  String replacement(Character oldChar,Character newChar){
        Password strongPassword;
        strongPassword = new Password(password.replace(oldChar,newChar));
        return strongPassword.getPassword();

    }

}


