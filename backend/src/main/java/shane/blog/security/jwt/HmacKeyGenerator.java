package shane.blog.security.jwt;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class HmacKeyGenerator {
    public static SecretKey generateKey(String secret) {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        return new SecretKeySpec(decodedKey, "HmacSHA256");
    }
}