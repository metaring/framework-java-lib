/**
 *    Copyright 2019 MetaRing s.r.l.
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.metaring.framework.util;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.metaring.framework.Core;

public class CryptoUtil {

    public static final Charset CHARSET_UTF_8 = Charset.forName("UTF8");
    public static final String SYSTEM_BASE_PASS_KEY_PROPERTY_NAME = "system.base.pass.key";

    private static final String MESSAGE_DIGEST_ALGORYTHM = "MD5";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String KEY_TYPE = "AES";
    private static final String CIPHER_PROVIDER = "SunJCE";
    public static final Base64.Encoder BASE_64_ENCODER = Base64.getEncoder();
    public static final Base64.Decoder BASE_64_DECODER = Base64.getDecoder();
    private static final Random RANDOM = new Random((long) (Math.random() * 169.0));

    public static final String getSystemBasePassKey() {
        return Core.SYSKB.getText(SYSTEM_BASE_PASS_KEY_PROPERTY_NAME);
    }

    public static final byte[] getCommunicationPassKey(String sourceIp) {
        byte[] passKey = null;
        String basePassKey = getSystemBasePassKey();
        int basePassKeyMiddle = basePassKey.length() / 2;
        String ip = sourceIp;
        ip = ip.replace(".", "/");
        int ipMiddle = ip.length() / 2;
        String plainPassKey = ip.substring(0, ipMiddle) + "_" + basePassKey.substring(basePassKeyMiddle) + "_" + ip.substring(ipMiddle) + "_" + basePassKey.substring(0, basePassKeyMiddle);
        passKey = toMD5(plainPassKey.getBytes(CHARSET_UTF_8));
        passKey = fromBase64(passKey);
        return passKey;
    }

    public static final String getCommunicationPassKeyString(String sourceIp) {
        return new String(toBase64(getCommunicationPassKey(sourceIp)), CHARSET_UTF_8);
    }

    public static final SecretKeySpec getSecretKey(String sourceIp) {
        return new SecretKeySpec(getCommunicationPassKey(sourceIp), KEY_TYPE);
    }

    public static final byte[] toMD5(byte[] source) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MESSAGE_DIGEST_ALGORYTHM);
            messageDigest.reset();
            return toHex(messageDigest.digest(source));
        }
        catch (Exception messageDigest) {
            return null;
        }
    }

    public static final byte[] toMD5(String source) {
        return toMD5(source.getBytes(CHARSET_UTF_8));
    }

    public static final String toMD5String(String source) {
        return new String(toMD5(source), CHARSET_UTF_8);
    }

    public static final String toMD5String(Object source) {
        return toMD5String(source.toString());
    }

    public static final String toMD5String(byte[] source) {
        return new String(toMD5(source), CHARSET_UTF_8);
    }

    public static final byte[] toHex(byte[] source) {
        String hex = String.format("%040x", new BigInteger(1, source));
        return hex.substring(Math.abs(32 - hex.length())).getBytes(CHARSET_UTF_8);
    }

    public static final byte[] toHex(String source) {
        return toHex(source.getBytes(CHARSET_UTF_8));
    }

    public static final String toHexString(byte[] source) {
        return new String(toHex(source), CHARSET_UTF_8);
    }

    public static final String toHexString(String source) {
        return new String(toHex(source.getBytes(CHARSET_UTF_8)), CHARSET_UTF_8);
    }

    public static final byte[] decrypt(String sourceIp, byte[] encryptedPayload) {
        return convert(2, sourceIp, encryptedPayload);
    }

    public static final byte[] encrypt(String sourceIp, byte[] payload) {
        return convert(1, sourceIp, payload);
    }

    private static final byte[] convert(int cipherMode, String sourceIp, byte[] data) {
        byte[] convertedData = null;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION, CIPHER_PROVIDER);
            cipher.init(cipherMode, (Key) getSecretKey(sourceIp), new IvParameterSpec(new byte[cipher.getBlockSize()]));
            convertedData = cipher.doFinal(data);
        }
        catch (Exception e) {
        }
        return convertedData;
    }

    public static final byte[] toBase64(byte[] plainBytes) {
        return BASE_64_ENCODER.encode(plainBytes);
    }

    public static final byte[] toBase64(String source) {
        return toBase64(source.getBytes(CHARSET_UTF_8));
    }

    public static final String toBase64String(byte[] plainBytes) {
        return new String(toBase64(plainBytes), CHARSET_UTF_8);
    }

    public static final String toBase64String(String source) {
        return new String(toBase64(source.getBytes(CHARSET_UTF_8)), CHARSET_UTF_8);
    }

    public static final byte[] fromBase64(byte[] base64Bytes) {
        return BASE_64_DECODER.decode(base64Bytes);
    }

    public static final byte[] fromBase64(String source) {
        return BASE_64_DECODER.decode(source.getBytes(CHARSET_UTF_8));
    }

    public static final String fromBase64String(String source) {
        return new String(fromBase64(source), CHARSET_UTF_8);
    }

    public static final String fromBase64String(byte[] base64Bytes) {
        return new String(fromBase64(base64Bytes), CHARSET_UTF_8);
    }

    public static final String nextRandomCode() {
        return new String(toBase64(toMD5(("" + RANDOM.nextLong() + System.currentTimeMillis()).getBytes(CHARSET_UTF_8))), CHARSET_UTF_8).substring(0, 32);
    }

    public static final String nextUserToken(String sourceIp) {
        Object passKey = null;
        String basePassKey = nextRandomCode();
        int basePassKeyMiddle = basePassKey.length() / 2;
        String ip = sourceIp;
        ip = ip.replace((CharSequence) ".", (CharSequence) "/");
        int ipMiddle = ip.length() / 2;
        String plainPassKey = ip.substring(0, ipMiddle) + "_" + basePassKey.substring(basePassKeyMiddle) + "_" + ip.substring(ipMiddle) + "_" + basePassKey.substring(0, basePassKeyMiddle);
        passKey = toMD5(plainPassKey.getBytes(CHARSET_UTF_8));
        return new String((byte[]) passKey, CHARSET_UTF_8);
    }
}
