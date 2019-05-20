package src.assimetrica;

import java.io.File;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

class Chave {

    public static final File PUBLIC_FILENAME = new File(".", "chave.pub");
    public static final File PRIVATE_FILENAME = new File(".", "chave.key");

    private BigInteger privateModulus;
    private BigInteger privateExpoent;
    private BigInteger publicModulus;
    private BigInteger publicExpoent;

    private Chave(BigInteger privateModulus, BigInteger privateExpoent, BigInteger publicModulus, BigInteger publicExpoent) {
        this.privateModulus = privateModulus;
        this.privateExpoent = privateExpoent;
        this.publicModulus = publicModulus;
        this.publicExpoent = publicExpoent;
    }

    static Chave loadOrGenerate() throws NoSuchAlgorithmException {

        if (!shouldCreateFile()) {
            PUBLIC_FILENAME.delete();
            PRIVATE_FILENAME.delete();
        }

        KeyPair keyPair = montarChave();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        return new Chave(privateKey.getModulus(), privateKey.getPrivateExponent(), publicKey.getModulus(), publicKey.getPublicExponent());
    }

    private static boolean shouldCreateFile() {
        return !PUBLIC_FILENAME.exists() && !PRIVATE_FILENAME.exists();
    }

    private static KeyPair montarChave() throws NoSuchAlgorithmException {
        final int keySize = 1024;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    public RSAPrivateKeySpec getPrivateKeySpec() {
        return new RSAPrivateKeySpec(
                new BigInteger(privateModulus.toByteArray()),
                new BigInteger(privateExpoent.toByteArray())
        );
    }

    public RSAPublicKeySpec getPublicKeySpec() {
        return new RSAPublicKeySpec(
                new BigInteger(publicModulus.toByteArray()),
                new BigInteger(publicExpoent.toByteArray())
        );
    }

    @Override
    public String toString() {
        return "Chave{" +
                "privateModulus=" + privateModulus +
                ", privateExpoent=" + privateExpoent +
                ", publicModulus=" + publicModulus +
                ", publicExpoent=" + publicExpoent +
                '}';
    }
}