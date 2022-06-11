package password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPass = "antonin";
        String encodedPass = encoder.encode(rawPass);

        System.out.println(encodedPass);
    }
}
