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

import static com.metaring.framework.util.CryptoUtil.CHARSET_UTF_8;
import static com.metaring.framework.util.CryptoUtil.fromBase64;
import static com.metaring.framework.util.CryptoUtil.toBase64String;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class Crypto {

    private final Cipher encrypter;
    private final Cipher decrypter;

    public Crypto(String algorythm, String key, String keyType, int maxKeyLength) {
        try {
            encrypter = Cipher.getInstance(algorythm);

            Key k = new SecretKeySpec(prepareKey(key, maxKeyLength).getBytes(CHARSET_UTF_8),keyType);

            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[encrypter.getBlockSize()]);

            encrypter.init(Cipher.ENCRYPT_MODE, k, ivParameterSpec);
            decrypter = Cipher.getInstance(algorythm);
            decrypter.init(Cipher.DECRYPT_MODE, k, ivParameterSpec);

        } catch (Exception e) {
            throw new IllegalArgumentException("An error occurred while creating Crypto Cipher" + e);
        }
    }

    private static final String prepareKey(String key, int maxKeyLength) {
        try {
            if (key.length() >= maxKeyLength) {
                return key.substring(0, maxKeyLength);
            }
            while (key.length() < maxKeyLength) {
                key += "0";
            }
        }
        catch (Exception e) {
        }
        return key;
    }

    public final String encrypt(String input) {
        try {
            return toBase64String(encrypter.doFinal(input.getBytes(CHARSET_UTF_8)));
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public final String decrypt(String input) {
        try {
            return new String (decrypter.doFinal(fromBase64(input)), CHARSET_UTF_8);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}