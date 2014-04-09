package security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DBSecurity {

    
    /**
     * Takes a password and a salt (both in String format), concatenates
     * them together and then hashes them using the SHA-256 algorithm.
     * 
     * @param password The password that has been entered.
     * @param salt The salt value from the database.
     * @return password unchanged if the hashing fails, or the hashed string 
     * if successful.
     */
    public static String hashedString(String password, String salt) 
    {
       
        try { 
            String strToHash = password + salt;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(strToHash.getBytes("UTF-8"));
            
            byte[] encoded = md.digest();
            
            String result = makeBase64String(encoded);
            
            return result;        
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return password; //should only happen upon failure
    }
    /**
     * Takes a byte array and transforms it into a base-64 string.
     *
     * @pre encodedString.length > 0
     * @post sb.toString().length > 0
     * @param encodedString
     * @return A string
     */
    private static String makeBase64String(byte[] encodedString)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encodedString.length; i++)
        {
            String hex = Integer.toHexString(0xff & encodedString[i]);
            if (hex.length() == 1)
            {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

}
