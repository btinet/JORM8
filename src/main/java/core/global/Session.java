package core.global;

import entity.Benutzer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Session {

    protected static HashMap<String, String> post = new HashMap<>();

    protected static Benutzer benutzer;
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final String OUTPUT_FORMAT = "%-20s:%s";

    public static void set(String key, String value){
        post.put(key,value);
    }

    public static String get(String key){
        if(post.containsKey(key)){
            String string = post.get(key);
            post.remove(key);
            return string;
        }
        return "";
    }

    public static String copy(String key){
        if(post.containsKey(key)){
            return post.get(key);
        }
        return "";
    }

    public static void setBenutzer(Benutzer benutzer){
        Session.benutzer = benutzer;
    }

    public static Benutzer getBenutzer(){
        return Session.benutzer;
    }

    public static void destroyBenutzer(){
        Session.benutzer = null;
    }
    public static void destroySession(){
        Session.post = new HashMap<>();
        Session.benutzer = null;
    }

    private static byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        return md.digest(input);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String getHash(String string){
        byte[] md5InBytes = digest(string.getBytes(UTF_8));
        return bytesToHex(md5InBytes);
    }

}
