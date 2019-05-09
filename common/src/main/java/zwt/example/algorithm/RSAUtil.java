package zwt.example.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zwt.example.util.ConfigUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;

/**
 * RSA 加签验签
 */
public class RSAUtil {
    private static final Logger LOG = LoggerFactory.getLogger(RSAUtil.class);

    private static RSAPrivateKey privateKey;

    private static RSAPublicKey publicKey;
    private RSAUtil(){}
    static {
        String privateKeyStr = ConfigUtil.get("privateKey");
        loadPrivateKey(privateKeyStr);
        String publicKeyStr = ConfigUtil.get("publicKey");
        loadPublicKey(publicKeyStr);
    }

    /**
     * 根据字符串实例化公钥
     * @param publicKeyStr
     */
    private static void loadPublicKey(String publicKeyStr) {
        try {
            Decoder decoder = Base64.getDecoder();
            byte[] buffer = decoder.decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            LOG.info(String.format("IOException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        } catch (InvalidKeySpecException e) {
            LOG.info(String.format("InvalidKeySpecException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        } catch (NullPointerException e) {
            LOG.info(String.format("NullPointerException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        }
    }

    /**
     * 根据文件流实例化公钥
     * @param in
     */
    private static void loadPublicPem(InputStream in) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(stream2ByteArray(in));
            publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            LOG.info(String.format("NoSuchAlgorithmException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        } catch (InvalidKeySpecException e) {
            LOG.info(String.format("InvalidKeySpecException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        } catch (NullPointerException e) {
            LOG.info(String.format("NullPointerException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        }
    }

    /**
     * 根据字符串实例化私钥
     * @param privateKeyStr
     * @return
     */
    private static void loadPrivateKey(String privateKeyStr) {
        try {
            LOG.info("privateKeyStr:" + privateKeyStr);
            Decoder decoder = Base64.getDecoder();
            byte[] buffer = decoder.decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            LOG.info(String.format("NoSuchAlgorithmException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        } catch (InvalidKeySpecException e) {
            LOG.info(String.format("InvalidKeySpecException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        } catch (NullPointerException e) {
            LOG.info(String.format("NullPointerException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        }
    }

    /**
     * 根据文件流实例化私钥
     * @param in
     */
    private static void loadPrivateKeyPem(InputStream in) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(stream2ByteArray(in));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            LOG.info(String.format("NoSuchAlgorithmException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        } catch (InvalidKeySpecException e) {
            LOG.info(String.format("InvalidKeySpecException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        } catch (NullPointerException e) {
            LOG.info(String.format("NullPointerException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        }
    }

    /**
     * MD5加签
     *
     * @param signSrc
     * @return
     */
    public static String getMd5WithRsaSignatureBase64(String signSrc) {
        String sign = null;
        try {
            sign = getMd5WithRsaSignatureBase64(signSrc.getBytes("utf-8"));
        } catch (Exception e) {
            LOG.info(String.format("Exception: [errCode: empty], [errMsg: %s]", e.getMessage()));
        }
        return sign;
    }

    /**
     * MD5加签
     *
     * @param data
     * @return
     */
    private static String getMd5WithRsaSignatureBase64(byte[] data) {
        String sign = null;
        try {
            Signature sig = Signature.getInstance("MD5withRSA");
            sig.initSign(privateKey);
            sig.update(data);
            sign = Base64.getEncoder().encodeToString(sig.sign());
        } catch (NoSuchAlgorithmException e) {
            LOG.info(String.format("NoSuchAlgorithmException: [errMsg: %s]", e.getMessage()));
        } catch (InvalidKeyException e) {
            LOG.info(String.format("InvalidKeyException: [errMsg: %s]", e.getMessage()));
        } catch (SignatureException e) {
            LOG.info(String.format("SignatureException: [errMsg: %s]", e.getMessage()));
        } catch (Exception e) {
            LOG.info(String.format("Exception: [errMsg: %s]", e.getMessage()));
        }
        return sign;
    }

    /**
     * SHA1加签
     *
     * @param data
     * @return
     */
    public static String getSHA1SignatureBase64(String data) {
        String sign = null;
        try {
            Signature sig = Signature.getInstance("SHA1withRSA");
            sig.initSign(privateKey);
            byte[] bytes = data.getBytes("utf-8");
            sig.update(bytes);
            sign = Base64.getEncoder().encodeToString(sig.sign());
        } catch (NoSuchAlgorithmException e) {
            LOG.info(String.format("NoSuchAlgorithmException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        } catch (InvalidKeyException e) {
            LOG.info(String.format("InvalidKeyException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        } catch (SignatureException e) {
            LOG.info(String.format("SignatureException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        } catch (Exception e) {
            LOG.info(String.format("Exception: [errCode: empty], [errMsg: %s]", e.getMessage()));
        }
        return sign;
    }

    /**
     * SHA256加签
     *
     * @param signSrc
     * @return
     */
    public static String getSHA256SignatureBase64(String signSrc) {
        String sign = null;
        try {
            return getSHA256SignatureBase64(signSrc.getBytes("utf-8"));
        } catch (Exception e) {
            LOG.info(String.format("Exception: [errMsg: %s]", e.getMessage()));
        }
        return sign;
    }

    /**
     * SHA256加签
     *
     * @param data
     * @return
     */
    private static String getSHA256SignatureBase64(byte[] data) {
        String sign = null;
        try {
            Signature sig = Signature.getInstance("SHA256WithRSA");
            sig.initSign(privateKey);
            sig.update(data);
            sign = Base64.getEncoder().encodeToString(sig.sign());
        } catch (NoSuchAlgorithmException e) {
            LOG.info(String.format("NoSuchAlgorithmException: [errMsg: %s]", e.getMessage()));
        } catch (InvalidKeyException e) {
            LOG.info(String.format("InvalidKeyException: [errMsg: %s]", e.getMessage()));
        } catch (SignatureException e) {
            LOG.info(String.format("SignatureException: [errMsg: %s]", e.getMessage()));
        } catch (Exception e) {
            LOG.info(String.format("Exception: [errMsg: %s]", e.getMessage()));
        }
        return sign;
    }

    /**
     * SHA1验签
     *
     * @param signSrc
     * @param sign
     * @param modulus
     * @return
     */
    public static boolean verifySHA1SignatureBase64(byte[] signSrc, String sign, String modulus) {
        boolean result = false;
        try {
            result = verifySHA1SignatureBase64(signSrc, Base64.getDecoder().decode(sign), modulus);
        } catch (Exception e) {
            LOG.info(String.format("Exception: [errCode: empty], [errMsg: %s]", e.getMessage()));
        }
        return result;
    }

    /**
     * SHA1验签
     *
     * @param signSrc
     * @param signature
     * @param modulus
     * @return
     */
    private static boolean verifySHA1SignatureBase64(byte[] signSrc, byte[] signature, String modulus) {
        boolean result = false;
//        PublicKey pubKey = RSACryptography.getPubKey(modulus);
        try {
            Signature sig = Signature.getInstance("SHA1withRSA");
            sig.initVerify(publicKey);
            sig.update(signSrc);
            result = sig.verify(signature);
        } catch (NoSuchAlgorithmException e) {
            LOG.info(String.format("NoSuchAlgorithmException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        } catch (InvalidKeyException e) {
            LOG.info(String.format("InvalidKeyException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        } catch (SignatureException e) {
            LOG.info(String.format("SignatureException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        } catch (Exception e) {
            LOG.info(String.format("Exception: [errCode: empty], [errMsg: %s]", e.getMessage()));
        }
        return result;
    }

    private static byte[] stream2ByteArray(InputStream input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n;
        try {
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }
        } catch (IOException e) {
            LOG.info(String.format("IOException: [errCode: empty], [errMsg: %s]", e.getMessage()));
        }
        return output.toByteArray();
    }
}
