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
        System.out.println();

        System.out.println("Caso 3");
        byte[] content_3 = bf.encodeECB("SABONETE");
        System.out.println(content_3.length);
        System.out.println(byteToHex(content_3));
        System.out.println();

        System.out.println("Caso 4");
        byte[] content_4 = bf.encodeECB("SABONETESABONETESABONETE");
        System.out.println(content_4.length);
        System.out.println(byteToHex(content_4));
        System.out.println();

        System.out.println("Caso 5");
        byte[] content_5 = bf.encodeCBC("FURB");
        System.out.println(content_5.length);
        System.out.println(byteToHex(content_5));
        //System.out.println(bf.decodeCBC(content_5));
        System.out.println();

        System.out.println("Caso 6");
        byte[] content_6 = bf.encodeCBC("FURB", new byte[]{1,1,2,2,3,3,4,4});
        System.out.println(content_6.length);
        System.out.println(byteToHex(content_6));
        //System.out.println(bf.decodeCBC(content_5));
        System.out.println();

        System.out.println("Caso 7");
        byte[] content_7 = bf.encodeCBC("SABONETESABONETESABONETE", new byte[]{ 1, 1, 2, 2, 3, 3, 4, 4 });
        System.out.println(content_7.length);
        System.out.println(byteToHex(content_7));
        //System.out.println(bf.decodeCBC(content_5));
        System.out.println();

        System.out.println("Caso 8");
        byte[] content_8 = bf.encodeCBC("SABONETESABONETESABONETE", new byte[]{10,20,30,40,50,60,70,80});
        System.out.println(content_8.length);
        System.out.println(byteToHex(content_8));
        System.out.println(bf.decodeCBC(content_8, new byte[]{ 1, 1, 2, 2, 3, 3, 4, 4 }));
        System.out.println("");

        System.out.println("Caso 9");
        byte[] content_9 = bf.encodeECB("FURB");
        System.out.println(content_9.length);
        System.out.println(byteToHex(content_9));
//        System.out.println(bf.decodeECB(content_9, "11111"));
        System.out.println();
    }

    private static String byteToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }

}
