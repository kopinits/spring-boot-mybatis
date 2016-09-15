package com.markus.app.rest.support;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Crypt {

	private static final Charset PLAIN_TEXT_ENCODING = Charset.forName("UTF-8");
	private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final String KEY_ALGORITHM = "AES";
	// As we do not want to install the Unlimited Strength policies in JVM, we can only use a 128bit key with AES, even if we use BouncyCastle
	// In case of wanting to provide 256bit strength (with unlimited strength activated in JVM, change MD5 for SHA-256
	private static final String PASSWORD_HASH_ALGORITHM = "SHA-256";

	public static String encrypt(final String input, final String password) throws UnsupportedEncodingException,
			NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, InvalidKeySpecException, IllegalBlockSizeException,
			BadPaddingException, ShortBufferException, InvalidParameterSpecException {
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

		Key key = buildKey(password);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] ivBytes = cipher.getParameters().getParameterSpec(IvParameterSpec.class).getIV();

		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivBytes));
		byte[] encrypted = cipher.doFinal(input.getBytes(PLAIN_TEXT_ENCODING));

		// Store the IV along with the encrypted data, in the first 16 positions
		byte[] concatenated = new byte[ivBytes.length + encrypted.length];
		System.arraycopy(ivBytes, 0, concatenated, 0, ivBytes.length);
		System.arraycopy(encrypted, 0, concatenated, ivBytes.length, encrypted.length);

		return Base64.encodeBase64String(concatenated);
	}

	public static String decrypt(final String encrypted, final String password) throws NoSuchAlgorithmException,
			IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchProviderException {
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

		Key key = buildKey(password);

		byte[] dec = Base64.decodeBase64(encrypted);

		// Recover the IV from the binary data
		byte[] iv = new byte[16];
		System.arraycopy(dec, 0, iv, 0, 16);
		byte[] cText = new byte[dec.length - 16];
		System.arraycopy(dec, 16, cText, 0, cText.length);
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

		byte[] decrypted = cipher.doFinal(cText);
		return new String(decrypted, PLAIN_TEXT_ENCODING);
	}

	private static Key buildKey(final String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		char[] cPassword = password.toCharArray();
		MessageDigest digester = MessageDigest.getInstance(PASSWORD_HASH_ALGORITHM);
		digester.update(String.valueOf(cPassword).getBytes(PLAIN_TEXT_ENCODING));
		byte[] key = digester.digest();
		SecretKeySpec spec = new SecretKeySpec(key, KEY_ALGORITHM);
		return spec;
	}
}
