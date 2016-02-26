package za.co.random.poc;//Task#2.1
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;


public class Encrypt {

	/**
	 * @param args
	 */
	//
    // Print out the contents of a byte array in hexadecimal.
    //
  private static void PrintBytes(byte[] bytes)
    {
        int cBytes = bytes.length;
        int iByte = 0;

        for (;;)
        {
            for (int i = 0; i < 8; i++)
            {
                String hex = Integer.toHexString(bytes[iByte++] & 0xff);
                if (hex.length() == 1)
                {
                    hex = "0" + hex;
                }

                System.out.print("0x" + hex + " ");
                if (iByte >= cBytes)
                {
                    System.out.println();
                    return;
                }
            }
            System.out.println();
        }
    }
    //
    // Reverse the order of the values in a byte array.
    //
    public static void ReverseByteArray(byte[] array)
    {
        int i, j;
        byte temp;

        for (i = 0, j = array.length - 1; i < j; i++, j--)
        {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }        
    }

    //
    // Use RSA to encrypt data.
    //
    public static byte[] RsaEncrypt(byte[] modulusBytes,byte[] exponentBytes,byte[] dataBytes)
    {
        //
        // Reverse the passed in byte arrays and then use these to 
        // create the BigIntegers for the RSA computation.
        //
        ReverseByteArray(modulusBytes);
        ReverseByteArray(exponentBytes);
        ReverseByteArray(dataBytes);

        BigInteger modulus = new BigInteger(1,modulusBytes);
        BigInteger exponent = new BigInteger(1,exponentBytes);
        BigInteger data = new BigInteger(1,dataBytes);
        //
        // Perform RSA encryption: 
        // ciphertext = plaintext^exponent % modulus.
        //
        BigInteger cipherText = data.modPow(exponent,modulus);
        //
        // Reverse the generated ciphertext.
        //
        byte[] cipherTextBytes = cipherText.toByteArray();
        ReverseByteArray(cipherTextBytes);
        //
        // Undo the reversal of the passed in byte arrays.
        //
        ReverseByteArray(modulusBytes);
        ReverseByteArray(exponentBytes);
        ReverseByteArray(dataBytes);

        return cipherTextBytes;
    }
	public static void main(String[] args) {
		String filename="";
		filename=args[0];
		filename.replace('/',File.separatorChar);
		System.out.println(filename);
		// TODO Auto-generated method stub
		//
        // Modulus bytes obtained straight from the wire in the 
        // proprietary certificate (in little endian format). 
        // This is for a 512-bit key set.
        //
        byte[] modulusBytes = 
        {        
            (byte) 0x37, (byte) 0xa8, (byte) 0x70, (byte) 0xfe, 
            (byte) 0x9a, (byte) 0xb9, (byte) 0xa8, (byte) 0x54,
            (byte) 0xcb, (byte) 0x98, (byte) 0x79, (byte) 0x44, 
            (byte) 0x7a, (byte) 0xb9, (byte) 0xeb, (byte) 0x38,
            (byte) 0x06, (byte) 0xea, (byte) 0x26, (byte) 0xa1, 
            (byte) 0x47, (byte) 0xea, (byte) 0x19, (byte) 0x70,
            (byte) 0x5d, (byte) 0xf3, (byte) 0x52, (byte) 0x88, 
            (byte) 0x70, (byte) 0x21, (byte) 0xb5, (byte) 0x9e,
            (byte) 0x50, (byte) 0xb4, (byte) 0xe1, (byte) 0xf5, 
            (byte) 0x1a, (byte) 0xd8, (byte) 0x2d, (byte) 0x51,
            (byte) 0x4d, (byte) 0x1a, (byte) 0xad, (byte) 0x79, 
            (byte) 0x7c, (byte) 0x89, (byte) 0x46, (byte) 0xb0,
            (byte) 0xcc, (byte) 0x66, (byte) 0x74, (byte) 0x02, 
            (byte) 0xd8, (byte) 0x28, (byte) 0x5d, (byte) 0x9d,
            (byte) 0xd7, (byte) 0xca, (byte) 0xfc, (byte) 0x60, 
            (byte) 0x0f, (byte) 0x38, (byte) 0xf9, (byte) 0xb3
        };

        //
        // Exponent bytes (in little endian order) obtained straight 
        // from the wire (in the proprietary certificate).
        //
        byte[] exponentBytes = 
        {        
            (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x00
        };
        //
        // Sample Client File for Encryption and decryption
        //
        try
        {
        FileInputStream fis=new FileInputStream(filename);//original file
        filename=filename.replace(".","_.");
        System.out.println(filename);
        FileOutputStream fos1=new FileOutputStream(filename);//encrypted file
        int r;
        String s="";
        while((r=fis.read())!=-1)
        {
        	s+=(char)r;
        }
        byte[] clientRandomBytes =s.getBytes(); 
        System.out.println("Client random:");
        System.out.println(clientRandomBytes.toString());
        System.out.print(s);
        System.out.print("\n");
        PrintBytes(clientRandomBytes);
        //
        // Perform encryption.
        //
        byte[] encryptedClientRandomBytes = RsaEncrypt(modulusBytes,exponentBytes,clientRandomBytes);      
        fos1.write(encryptedClientRandomBytes);//writes the encrypted file to amar1.txt
        System.out.println("Encrypted client random:");
        System.out.println(encryptedClientRandomBytes.toString());
        PrintBytes(encryptedClientRandomBytes);
        }
        catch(IOException e)
        {
        	System.out.println(e.toString());
        }
	}

}
