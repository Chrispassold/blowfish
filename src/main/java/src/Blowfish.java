package src;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Blowfish {

    private final SecretKey secretKey;

    public Blowfish() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
        keyGenerator.init(128);
        secretKey = keyGenerator.generateKey();
    }

    String encondeECB(String value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher c = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secretKey);

        return new String(c.doFinal(value.getBytes()));
    }

    String encondeCBC(String value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher c = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secretKey);

        return new String(c.doFinal(value.getBytes()));
    }
}
