package com.own.utils;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.springframework.stereotype.Component;
@Component
public class FileEncryption {
	public static final String PRIVATE_SEC_KEY="aef-ASE5";
	
	BlockCipher engine = new DESEngine();

    private byte[] Encrypt(String keys, byte[] plainText) {
        byte[] key = keys.getBytes();
        byte[] ptBytes = plainText;
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(engine));
        cipher.init(true, new KeyParameter(key));
        byte[] rv = new byte[cipher.getOutputSize(ptBytes.length)];
        int tam = cipher.processBytes(ptBytes, 0, ptBytes.length, rv, 0);
        try {
            cipher.doFinal(rv, tam);
        } catch (Exception ce) {
            ce.printStackTrace();
        }
        return rv;
    }

    private byte[] Decrypt(String key2, byte[] cipherText) {
        byte[] key = key2.getBytes();
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(engine));
        cipher.init(false, new KeyParameter(key));
        byte[] rv = new byte[cipher.getOutputSize(cipherText.length)];
        int tam = cipher.processBytes(cipherText, 0, cipherText.length, rv, 0);
        try {
            cipher.doFinal(rv, tam);
        } catch (Exception ce) {
            ce.printStackTrace();
        }
        return rv;
    }
    public   byte [] encrypt(String keys, byte[] plainBytes) {
    	return Encrypt(keys, plainBytes);
    }
    public   byte [] decrypt(String keys, byte[] encryptedBytes) {
    	return Decrypt(keys, encryptedBytes);
    }

}
