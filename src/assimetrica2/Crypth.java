package src.assimetrica2;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Crypth {
    private final static File PUBLIC = new File("./chave.pub");
    private final static File PRIVATE = new File("./chave.key");
    private final SecretKey secretKey;

    public Crypth() throws NoSuchAlgorithmException, IOException {
        secretKey = generateAESKey();
        KeyPair keyPair = generateRSAKey();

        CrypthMain.writeBytes(keyPair.getPublic().getEncoded(), PUBLIC);
        CrypthMain.writeBytes(keyPair.getPrivate().getEncoded(), PRIVATE);
    }

    public SecretKey getSecretKeyAES(){
        return secretKey;
    }

    public static RSAPrivateKey getPRIVATE() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) kf.generatePrivate(new PKCS8EncodedKeySpec(CrypthMain.readBytes(PRIVATE)));

    }

    public static RSAPublicKey getPUBLIC() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(CrypthMain.readBytes(PUBLIC)));
    }

    private SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128); // The AES key size in number of bits
        return generator.generateKey();
    }

    private KeyPair generateRSAKey() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        return kpg.generateKeyPair();
    }

    public byte[] encryptAES(String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return aesCipher.doFinal(plainText.getBytes());
    }

    public byte[] encryptAESKeyWithRSAKey() throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, IOException, InvalidKeySpecException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.PUBLIC_KEY, getPUBLIC());
        return cipher.doFinal(secretKey.getEncoded());
    }

    public byte[] decryptAESKeyWithRSAKey(byte[] encryptedKey) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, IOException, InvalidKeySpecException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.PRIVATE_KEY, getPRIVATE());
        return cipher.doFinal(encryptedKey);
    }

    public String decryptMessage(byte[] byteCipherText, byte[] decryptedKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey originalKey = new SecretKeySpec(decryptedKey, 0, decryptedKey.length, "AES");
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, originalKey);
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        return new String(bytePlainText);
    }
}
