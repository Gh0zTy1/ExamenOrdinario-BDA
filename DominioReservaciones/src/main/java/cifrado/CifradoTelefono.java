/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cifrado;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase de utilidad para cifrado y descifrado AES-256.
 * Proporciona métodos seguros para cifrar y descifrar texto.
/**
 *
 * @author caarl
 */
public class CifradoTelefono {
    
    /**
     * Clave de cifrado utilizada para encriptación AES-256.
     * IMPORTANTE: En un entorno de producción, utilice un sistema de gestión de claves seguro.
     */
    private static final String SECRET_KEY = "R35T4UR4NT351234_CLAVE_SEGURA_EXTENSION";
    
    /**
     * Cifra el texto de entrada utilizando encriptación AES-256.
     * 
     * @param texto Texto plano a ser encriptado
     * @return Texto encriptado codificado en Base64
     * @throws Exception Si ocurre un error durante el cifrado
     */
    public static String encriptar(String texto) throws Exception {
        // Generar una clave segura de 256 bits a partir de la clave secreta
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = sha.digest(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        key = Arrays.copyOf(key, 32); // Truncar a 256 bits
        
        // Crear SecretKeySpec para AES-256
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        
        // Inicializar Cipher para encriptación
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        
        // Encriptar el texto
        byte[] encryptedData = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));
        
        // Devolver texto encriptado codificado en Base64
        return Base64.getEncoder().encodeToString(encryptedData);
    }
    
    /**
     * Descifra el texto encriptado utilizando descifrado AES-256.
     * 
     * @param textoEncriptado Texto encriptado codificado en Base64
     * @return Texto plano original descifrado
     * @throws Exception Si ocurre un error durante el descifrado
     */
    public static String desencriptar(String textoEncriptado) throws Exception {
        // Generar una clave segura de 256 bits a partir de la clave secreta
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = sha.digest(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        key = Arrays.copyOf(key, 32); // Truncar a 256 bits
        
        // Crear SecretKeySpec para AES-256
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        
        // Inicializar Cipher para descifrado
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        
        // Decodificar el texto encriptado de Base64
        byte[] decodedData = Base64.getDecoder().decode(textoEncriptado);
        
        // Descifrar los datos
        byte[] decryptedData = cipher.doFinal(decodedData);
        
        // Devolver el texto descifrado
        return new String(decryptedData, StandardCharsets.UTF_8);
    }
}