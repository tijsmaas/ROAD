/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aidas.utils;

/**
 *
 * @author Geert
 */
public class Byte {
    
    /**
     * Convert the provided byte array to a {@link String}.
     * @param array the array to convert to a {@link String}.
     * @return the converted byte array.
     */
    public static String arrayToString(byte[] array) {
        String encodedString = "";

        for (byte b : array) {
            encodedString += Integer.toString((b&0xff) + 0x100, 16).substring(1);
        }

        return encodedString;
    }
}
