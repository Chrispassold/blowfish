package src.assimetrica;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

public class Decrypt extends BaseCryptDecrypt {

    private Decrypt(Chave chave) throws NoSuchPaddingException, NoSuchAlgorithmException {
        super(chave);
    }

    static BaseCryptDecrypt from(Chave chave) throws NoSuchAlgorithmException, NoSuchPaddingException {
        return new Decrypt(chave);
    }

    public void process(File filein, File fileout) throws Exception {
        final byte[] bytes = Files.readAllBytes(filein.toPath());
        byte[] signed = process(bytes);
        toFile(signed, fileout);
    }

    private byte[] process(byte[] bytes) throws Exception {
        RSAPublicKeySpec spec = chave.getPublicKeySpec();
        final PublicKey key = kf.generatePublic(spec);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(bytes);
    }
}
