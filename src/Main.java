package src;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        final Blowfish bf = new Blowfish();

        System.out.println("Caso 1");
        byte[] content_1 = bf.encodeECB("FURB");
        System.out.println(content_1.length);
        System.out.println(byteToHex(content_1));
        System.out.println();
        System.out.println("Caso 2");
        byte[] content_2 = bf.encodeECB("COMPUTADOR");
        System.out.println(content_2.length);
        System.out.println(byteToHex(content_2));
        System.out.println("");
        System.out.println("Caso 3");
        byte[] content_3 = bf.encodeECB("SABONETE");
        System.out.println(content_3.length);
        System.out.println(byteToHex(content_3));
        System.out.println("");

        System.out.println("Caso 4");
        byte[] content_4 = bf.encodeECB("SABONETESABONETESABONETE");
        System.out.println(content_4.length);
        System.out.println(byteToHex(content_4));
        System.out.println("");

        System.out.println("Caso 5");
        byte[] content_5 = bf.encodeCBC("FURB");
        System.out.println(content_5.length);
        System.out.println(byteToHex(content_5));
        //System.out.println(bf.decodeCBC(content_5));
        System.out.println("");

        System.out.println("Caso 6");
        byte[] content_6 = bf.encodeCBC("FURB", new byte[]{1,1,2,2,3,3,4,4});
        System.out.println(content_6.length);
        System.out.println(byteToHex(content_6));
        //System.out.println(bf.decodeCBC(content_5));
        System.out.println("");

        System.out.println("Caso 7");
        byte[] content_7 = bf.encodeCBC("SABONETESABONETESABONETE", new byte[]{ 1, 1, 2, 2, 3, 3, 4, 4 });
        System.out.println(content_7.length);
        System.out.println(byteToHex(content_7));
        //System.out.println(bf.decodeCBC(content_5));
        System.out.println("");

        System.out.println("Caso 8");
        byte[] content_8 = bf.encodeCBC("SABONETESABONETESABONETE", new byte[]{10,20,30,40,50,60,70,80});
        System.out.println(content_8.length);
        System.out.println(byteToHex(content_8));
        System.out.println(bf.decodeCBC(content_8, new byte[]{ 1, 1, 2, 2, 3, 3, 4, 4 }));
        System.out.println("");
    }

    private static String byteToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }

}

/*
// Testando ECB
        byte[] ecbValue = blowfish.encodeECB("FURB");
        System.out.println(Arrays.toString(ecbValue));
        System.out.println(blowfish.decodeECB(ecbValue));
        ////////////////////////////////////////////////////
        // Testando CBC
        byte[] failureEncrypt = blowfish.encodeCBC("FURB");
        try {
            blowfish.decodeCBC(failureEncrypt);
        } catch (Exception e) {
            System.out.println("Não pode usar cbc sem um vetor de inicialização");
        }
        byte[] initVector = new byte[] { 1, 1, 2, 2, 3, 3, 4, 4 };
        byte[] cbcValue = blowfish.encodeCBC("FURB", initVector);
        System.out.println(Arrays.toString(cbcValue));
        System.out.println(blowfish.decodeCBC(cbcValue, initVector));
        ///////////////////////////////////////////////////
        // CASO 1
        blowfish.encodeECB("FURB");

        // TODO CASO 2
        blowfish.encodeECB("COMPUTADOR");

        // TODO CASO 3
        blowfish.encodeECB("SABONETE");

        // TODO CASO 4
        blowfish.encodeECB("SABONETESABONETESABONETE");

        // TODO CASO 5
        blowfish.encodeCBC("FURB");

        // TODO CASO 6
        blowfish.encodeCBC("FURB");

        // TODO CASO 7
        blowfish.encodeCBC("SABONETESABONETESABONETE");

        // TODO CASO 8
        blowfish.encodeCBC("SABONETESABONETESABONETE");

        // TODO CASO 9
        blowfish.encodeECB("FURB");
* */