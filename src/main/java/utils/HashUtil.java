package utils;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    private static final Logger logger = Logger.getLogger(HashUtil.class);

    public static String getHash(String password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] passBytes = password.getBytes();
            byte[] passHash = sha256.digest(passBytes);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte hash : passHash) {
                stringBuilder.append(Integer.toString((hash & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("There is no such algorithm" + e);
        }
        return null;
    }
}
