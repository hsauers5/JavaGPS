package restservice;

import sun.misc.BASE64Decoder;

import java.io.IOException;

public class Authorization {

    public static boolean isAPIAuthenticated(String authString) {
        //System.out.println(authString);

        String decodedAuth = "";
        // Header is in the format "Basic 5tyc0uiDat4"
        // Extract data before decoding it back to original string
        String[] authParts = authString.split("\\s+");
        String authInfo = authParts[1];
        // Decode the data back to original string
        byte[] bytes = null;
        try {
            bytes = new BASE64Decoder().decodeBuffer(authInfo);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        decodedAuth = new String(bytes);

        if (decodedAuth.equals(new Credential("3rdEyeCreds.txt").toString())) {
            return true;
        } else {
            return false;
        }
    }

}
