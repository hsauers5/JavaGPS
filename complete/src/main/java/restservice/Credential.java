package restservice;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Credential {

    private static String filePath = "src/main/java/restservice/";

    private String username;
    private String password;

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @param credentialFileName - Filename containing credentials as username,password
     */
    public Credential(String credentialFileName) {

        try {
            String creds = new String(Files.readAllBytes(Paths.get(filePath + credentialFileName)));
            String username = creds.substring(0, creds.indexOf(","));
            String password = creds.substring(creds.indexOf(",")+1);
            this.username = username;
            this.password = password;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
/*
    public static void main(String[] args) {
        Credential thisCredential = new Credential("APICreds.txt");
        System.out.println(thisCredential);
        System.out.println(thisCredential.getBase64Enc());

        Credential secondCredential = new Credential("username1", "password");
        System.out.println(secondCredential);
        System.out.println(secondCredential.getBase64Enc());

        System.out.println(Authorization.isAPIAuthenticated(secondCredential.getBase64Enc()));
    }
*/

    public String toString() {
        return username + ":" + password;
    }

    public String getBase64Enc() {
        byte[] authEncBytes = Base64.encodeBase64(this.toString().getBytes());
        return new String(authEncBytes);
    }

    public HashMap getAuthHeader() {
        HashMap authHeader = new HashMap<>();
        authHeader.put("Authorization", this.getBase64Enc());
        return authHeader;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
