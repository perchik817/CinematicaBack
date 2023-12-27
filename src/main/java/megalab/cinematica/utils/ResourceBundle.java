package megalab.cinematica.utils;

import megalab.cinematica.models.enums.Language;

import java.util.Locale;

public class ResourceBundle {
    private ResourceBundle(){}

    private static final java.util.ResourceBundle messEn =
            java.util.ResourceBundle.getBundle("mess_en",
                    Locale.forLanguageTag("en"));

    private static final java.util.ResourceBundle messRu =
            java.util.ResourceBundle.getBundle("mess_ru",
                    Locale.forLanguageTag("ru"));

    public static String periodMess(String key, Language lang) {

        switch (lang){
            case EN: return messEn.getString(key);
            case RU:
            default:
                return messRu.getString(key);
        }

    }
}
