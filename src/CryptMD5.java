import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Korybut on 13.08.2017.
 */
public class CryptMD5 {

    private static MessageDigest md;

    public static String cryptMD5(String passwd){
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passwdByte = passwd.getBytes();
            md.reset();
            byte[] digested = md.digest(passwdByte);
            StringBuffer sb = new StringBuffer();
            for(byte aDigested : digested) {
                sb.append(Integer.toHexString(0xff & aDigested));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptMD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
