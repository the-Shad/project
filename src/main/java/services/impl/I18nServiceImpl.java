package services.impl;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import services.I18nService;

public class I18nServiceImpl implements I18nService {

	@Override
	public String getMessage(String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("interface", locale);
		String val = key;
		try {
			val = bundle.getString(key);
		} catch (MissingResourceException e) {
			return val;
		}
		return val;
	}

}
