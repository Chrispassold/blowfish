package src;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Blowfish {

    private final SecretKey secretKey = new SecretKeySpec("ABCDE".getBytes(StandardCharsets.UTF_8), "Blowfish");

    byte[] encodeECB(String value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher c = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secretKey);

        return c.doFinal(value.getBytes(StandardCharsets.UTF_8));
    }

    String decodeECB(byte[] value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher c = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secretKey);

        return new String(c.doFinal(value));
    }

    /**
     * Funcionará, mas na hora de descriptar dará erro, pois é necessário um vetor de inicialização.
     */
    byte[] encodeCBC(String value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher c = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secretKey);

        return c.doFinal(value.getBytes(StandardCharsets.UTF_8));
    }

    byte[] encodeCBC(String value, byte[] initVector) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Cipher c = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(initVector));

        return c.doFinal(value.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Não funcionará pois é necessário ter um vetor de inicialização
     */
    String decodeCBC(byte[] value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher c = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secretKey);

        return new String(c.doFinal(value));
    }

    String decodeCBC(byte[] value, byte[] initVector) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Cipher c = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(initVector));

        return new String(c.doFinal(value));
    }

}
