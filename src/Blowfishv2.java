package src;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Blowfishv2 {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private final SecretKey secretKey;
    private final Mode mode;

    public Blowfishv2(Mode mode) {
        this.secretKey = new SecretKeySpec("ABCDE".getBytes(StandardCharsets.UTF_8), "Blowfish");
        this.mode = mode;
    }

    public Blowfishv2(Mode mode, String key) {
        this.mode = mode;
        secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "Blowfish");
    }

    private static String toHex(byte[] value) {
        char[] hexChars = new char[value.length * 2];
        for (int j = 0; j < value.length; j++) {
            int v = value[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public class Encode {

        private Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            Cipher c = Cipher.getInstance("Blowfish/" + mode.toString() + "/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, secretKey);

            return c;
        }

        public String make(String value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
            return toHex(getCipher().doFinal(value.getBytes(StandardCharsets.UTF_8)));
        }
    }

    public class Decode {
        private Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            Cipher c = Cipher.getInstance("Blowfish/" + mode.toString() + "/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, secretKey);

            return c;
        }

        public String make(String value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
            final byte[] bytes = DatatypeConverter.parseHexBinary(value);
            return new String(getCipher().doFinal(bytes));
        }
    }


    public enum Mode {
        ECB,
        CBC
    }

}
