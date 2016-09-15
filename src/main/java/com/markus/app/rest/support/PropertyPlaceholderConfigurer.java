package com.markus.app.rest.support;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.lang3.StringUtils;

/**
 * Application specific implementation of Spring ProperyPlaceholderConfigurer to decrypt encrypted properties.
 *
 *
 *
 
 <bean id="propertyConfigurer" 
			class="com.db.distribute.rest.support.PropertyPlaceholderConfigurer">
			<property name="ignoreResourceNotFound" value="true"></property>
	      <property name="locations">
	        <list>
 			  <value>classpath:embedded.application.properties</value>
	          <value>classpath:distribute.properties</value>
	          <value>classpath:distribute.properties.override</value>
	        </list>
	      </property>
    </bean>
 in applicationCOntext.xml
 
 *
 */
public class PropertyPlaceholderConfigurer extends
		org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {
	private static final String DECRYPT_KEY_STORAGE = "com.db.masterPassword";

	@Override
	protected String convertPropertyValue(final String originalValue) {
		String value = originalValue;

		if (StringUtils.isNotEmpty(originalValue)) {
			Pattern pattern = Pattern.compile("^ENC\\[(.*)\\]");
			Matcher matcher = pattern.matcher(originalValue);

			if (matcher.find()) {
				try {
					String key = System.getProperty(DECRYPT_KEY_STORAGE);
					String toDecrypt = matcher.group().substring(4, matcher.group().length() - 1);
					if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(toDecrypt)) {
						value = Crypt.decrypt(toDecrypt, key);
					}
				} catch (NoSuchAlgorithmException ex) {
					Logger.getLogger(PropertyPlaceholderConfigurer.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IllegalBlockSizeException ex) {
					Logger.getLogger(PropertyPlaceholderConfigurer.class.getName()).log(Level.SEVERE, null, ex);
				} catch (NoSuchPaddingException ex) {
					Logger.getLogger(PropertyPlaceholderConfigurer.class.getName()).log(Level.SEVERE, null, ex);
				} catch (BadPaddingException ex) {
					Logger.getLogger(PropertyPlaceholderConfigurer.class.getName()).log(Level.SEVERE, null, ex);
				} catch (InvalidKeyException ex) {
					Logger.getLogger(PropertyPlaceholderConfigurer.class.getName()).log(Level.SEVERE, null, ex);
				} catch (InvalidAlgorithmParameterException ex) {
					Logger.getLogger(PropertyPlaceholderConfigurer.class.getName()).log(Level.SEVERE, null, ex);
				} catch (UnsupportedEncodingException ex) {
					Logger.getLogger(PropertyPlaceholderConfigurer.class.getName()).log(Level.SEVERE, null, ex);
				} catch (NoSuchProviderException ex) {
					Logger.getLogger(PropertyPlaceholderConfigurer.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return super.convertPropertyValue(value);
	}
}
