package io.quarkus.security.jpa;

import java.security.MessageDigest;

class CustomHasher extends PasswordHasher {

    private static String SHA_256 = "SHA-256";

    @Override
    public String hash(String password) {
        return sha256(password);
    }

    private String sha256(String pwd) {
        return createPasswordWithHashAlgorithm(pwd, SHA_256);

    }

    private String createPasswordWithHashAlgorithm(String pwd,
            String algorithm) {
        MessageDigest md;
        try {
            // es MD5, SHA-256
            md = MessageDigest.getInstance(algorithm);
            md.update(pwd.getBytes());
            byte[] enc = md.digest();

            // Encode bytes to base64 to get a string
            // String encode = new BASE64Encoder().encode(enc);
            String encode = new String(java.util.Base64.getMimeEncoder().encode(enc));
            // logger.info(encode);
            return encode;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //logger.info(e.getMessage());
            return null;
        }
    }
}
