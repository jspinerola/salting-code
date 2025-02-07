import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Password: ");
        String userPassword = input.next();

        System.out.println("User's Password: " + userPassword);

        StringBuilder salt = new StringBuilder();
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz"
                + "!@#$%^&*()_+{}[]|:;";

        for (int i = 0; i < userPassword.length(); i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            salt.append(AlphaNumericString.charAt(index));
        }

        System.out.println("Salt: " + salt);

        String saltedPassword = userPassword + salt;

        System.out.println("Salted Password: " + saltedPassword);

        System.out.println("Hashed User Password:   " + hashString(userPassword));
        System.out.println("Hashed Salted Password: " + hashString(saltedPassword));



    }
    public static String hashString(String input) {
        StringBuilder hashedInput = new StringBuilder();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA");
            byte[] byteArray = digest.digest(input.getBytes());
            for (byte elem :
                    byteArray) {
                String hexCharacters = Integer.toHexString(elem);
                while (hexCharacters.length() == 1) {
                    hexCharacters = "0" + hexCharacters;
                }
                hashedInput.append(hexCharacters);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Couln't find algorithm.", e);
        }
        return hashedInput.toString();
    }
}
