import java.util.Random;
import java.util.Scanner;

public class Main {

    /**
     * makeItStrong is an algorithm that modifies the password with a minimum number of changes in order for the password
     * to be a strong one
     * @param s - string that represents the initial password
     * @return newPassword - the string s after being modified in a strong password
     *         noOfChanges - the minimum number of changes made to the initial password
     **/

    public static String makeItStrong(String s){
        //noOfChanges = the minimum number of changes
        int noOfChanges = 0;
        Random r = new Random();
        // a new character used for replacement
        char newChar = 0;

        Password password = new Password(s);
        String newPassword = password.getPassword();
        Password strongerPassword = new Password(newPassword);

        //if the inserted password is already strong returns 0
        if(strongerPassword.checkIfStrong())
            return "0";

        //if the password doesn't have any digit one will be added
        if(!strongerPassword.hasDigit()) {
            newPassword = newPassword.concat(String.valueOf(r.nextInt(10)));
            noOfChanges ++;

        }

        //if the password doesn't have any lowercase letters one will be added
        if(!strongerPassword.hasLowercase()){
            newChar = (char) ('a' + r.nextInt(26));
            newPassword = newPassword.concat(String.valueOf(newChar).toLowerCase());
            noOfChanges ++;
        }

        //if the password doesn't have any uppercase, one will be added
        if(!strongerPassword.hasUppercase()){
            newChar = (char) ('A' + r.nextInt(26));
            newPassword = newPassword.concat(String.valueOf(newChar).toUpperCase());
            noOfChanges ++;
        }

        //if the length of the possible-modified password is less than 6 will be inserted the minimum number of characters
        //in order for the length to be 6
        if (newPassword.length() < 6) {
            for (int i = 0; i < 6 - newPassword.length(); i++) {
                newPassword = newPassword.concat(String.valueOf(newChar));
                noOfChanges++;
            }
        }

        //if the length of the possible-modified password is bigger than 20, will be deleted the minimum number of characters
        //in order for the length to be 20
        if(password.getPassword().length() > 20) {
            for (int i = 0; i <= password.getPassword().length() - 20; i++) {
                strongerPassword = new Password(strongerPassword.deletion());
                newPassword = strongerPassword.getPassword();
                noOfChanges++;
            }
            if(!strongerPassword.hasDigit()){
                newPassword = newPassword.concat(String.valueOf(r.nextInt(10)));
            }
        }

        //if there are are more than 2 identical characters, one of the character will be either removed or replaced
        if(strongerPassword.charactersNearEachOther()){

            char[] passwordToChar = newPassword.toCharArray(); //creating an array of characters with the passwords length
            StringBuffer stringBuffer = new StringBuffer(newPassword);

            for( int i = 0; i < newPassword.length()-1; i++) {
                if (passwordToChar[i] == passwordToChar[i + 1]){

                    //checking if the password would have the right length if a character will be deleted,
                    //then going further withe the deletion of one of the duplicate characters
                    if (new Password(strongerPassword.deletion()).checkLength()) {
                        newPassword = String.valueOf(stringBuffer.deleteCharAt(i));
                        noOfChanges++;
                        i++;
                    }
                    //if the length would be too short, one of the duplicate characters will be replaced with a random character
                    //if  newChar(the random character) is the same as the character to be deleted, we'll get another random value
                    else if (!new Password(strongerPassword.deletion()).checkLength()) {

                        if (newChar != passwordToChar[i]){
                            newPassword = strongerPassword.replacement(passwordToChar[i], newChar);
                            noOfChanges ++;
                        }
                        else {
                            newChar = (char) ('A' + r.nextInt(26));
                            newPassword = strongerPassword.replacement(passwordToChar[i], newChar);
                        }
                    }
                }
            }
        }

     return  "An alternative for your password to be stronger: " + newPassword +
             "\nThe minimum number of changes: " + noOfChanges;
    }



    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);
        System.out.print("Enter a password: ");
        String passwordString= sc.nextLine();
        Password password = new Password(passwordString);
        System.out.println(makeItStrong(password.getPassword()));


    }
}
