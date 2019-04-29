package src;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Blowfish {

    private final Cipher c;

    public Blowfish() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        c = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secretKey);
    }

    String enconde(String value) throws BadPaddingException, IllegalBlockSizeException {
        return new String(c.doFinal(value.getBytes()));
    }
}
