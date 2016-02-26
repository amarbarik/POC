package za.co.random.poc;//Task#2.2
import java.math.BigInteger;
import java.io.*;
public class Decrypt {

	/**
	 * @param args
	 */
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
    // Use RSA to decrypt data.
    //
	public static byte[] RsaDecrypt(byte[] modulusBytes,byte[] privateExponentBytes,byte[] encryptedDataBytes)
    {
        //
        // Reverse the passed in byte arrays and then use these to 
        // create the BigIntegers for the RSA computation.
        //
        ReverseByteArray(modulusBytes);
        ReverseByteArray(privateExponentBytes);
        ReverseByteArray(encryptedDataBytes);

        BigInteger modulus = new BigInteger(1,modulusBytes);
        BigInteger privateExponent = new BigInteger(1,privateExponentBytes);
        BigInteger encryptedData = new BigInteger(1,encryptedDataBytes);
        //
        // Perform RSA encryption: 
        // plaintext = ciphertext^privateExponent % modulus.
        //
        BigInteger decryptedData = encryptedData.modPow(privateExponent,modulus);

        //
        // Reverse the generated plaintext.
        //
        byte[] decryptedDataBytes = decryptedData.toByteArray();
        ReverseByteArray(decryptedDataBytes);

        //
        // Undo the reversal of the passed in byte arrays.
        //
        ReverseByteArray(modulusBytes);
        ReverseByteArray(privateExponentBytes);
        ReverseByteArray(encryptedDataBytes);

        return decryptedDataBytes;
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
        // Private exponent of the private key generated by the 
        // server (in little endian format).
        //
        byte[] privateExponentBytes = 
        {
            (byte) 0xc1, (byte) 0x07, (byte) 0xe7, (byte) 0xd4, 
            (byte) 0xd3, (byte) 0x38, (byte) 0x8d, (byte) 0x36,
            (byte) 0xf5, (byte) 0x9e, (byte) 0x8b, (byte) 0x96, 
            (byte) 0x0d, (byte) 0x55, (byte) 0x65, (byte) 0x08,
            (byte) 0x28, (byte) 0x25, (byte) 0xa3, (byte) 0x2e, 
            (byte) 0xc7, (byte) 0x68, (byte) 0xd6, (byte) 0x44,
            (byte) 0x85, (byte) 0x2d, (byte) 0x32, (byte) 0xf6, 
            (byte) 0x72, (byte) 0xa8, (byte) 0x9b, (byte) 0xba,
            (byte) 0x5e, (byte) 0x82, (byte) 0x82, (byte) 0xf0, 
            (byte) 0x5c, (byte) 0x0c, (byte) 0xeb, (byte) 0x6b,
            (byte) 0x12, (byte) 0x6a, (byte) 0xa7, (byte) 0x45, 
            (byte) 0x15, (byte) 0xce, (byte) 0x41, (byte) 0xe0,
            (byte) 0x03, (byte) 0xe5, (byte) 0xe6, (byte) 0x6d, 
            (byte) 0xdf, (byte) 0xfd, (byte) 0x58, (byte) 0x61,
            (byte) 0x0b, (byte) 0x07, (byte) 0xa4, (byte) 0x7b, 
            (byte) 0xb3, (byte) 0xf3, (byte) 0x71, (byte) 0x94
        };
        //
        // Sample Client File for Encryption and decryption
        //
        try
        {
        	FileInputStream fis=new FileInputStream(filename);//original file
            filename=filename.replace("_.",".");
            System.out.println(filename);
            
        int r;
        String s="";
        while((r=fis.read())!=-1)
        {
        	s+=(char)r;
        }
        byte[] encryptedClientRandomBytes =s.getBytes(); 
        System.out.println("Client encrypted random:");
        System.out.println(encryptedClientRandomBytes.toString());
        System.out.print(s);
        System.out.print("\n");
        PrintBytes(encryptedClientRandomBytes);
        //
        // Perform decryption.
        //
        byte[] decryptedClientRandomBytes = RsaDecrypt(modulusBytes,privateExponentBytes,encryptedClientRandomBytes);
        FileOutputStream fos1=new FileOutputStream(filename);//decrypted file
        fos1.write(decryptedClientRandomBytes);
        System.out.println("Decrypted client random:");
        System.out.println(decryptedClientRandomBytes.toString());
        PrintBytes(decryptedClientRandomBytes);
        FileInputStream fis1=new FileInputStream(filename);
        while((r=fis1.read())!=-1)
        {
        	s+=(char)r;
        }
        System.out.println(filename+"\n"+s);
        }
        catch(IOException e)
        {
        	System.out.println(e.toString());
        }
	}
}