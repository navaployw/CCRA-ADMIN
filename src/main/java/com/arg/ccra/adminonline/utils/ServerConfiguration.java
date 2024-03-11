package com.arg.ccra.adminonline.utils;

import java.time.ZoneId;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerConfiguration {
    private static final String BUNDLE_NAME = "server-configuration";
    private static final ResourceBundle BUNDLE;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ServerConfiguration.class);
    static
    {
        ResourceBundle rb = null;

        try
        {

            rb = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        catch (MissingResourceException e)
        {
            try
            {

                rb = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ENGLISH);
            }
            catch (MissingResourceException e1)
            {
                String package_name;
                Package pkg = ServerConfiguration.class
                        .getPackage();

                if (null == pkg)
                {
                    int index;

                    if (-1 == (index = (package_name = ServerConfiguration.class
                                    .getName()).lastIndexOf(".")))
                    {
                        throw e1;
                    }

                    package_name = package_name.substring(0, index);
                }
                else
                {
                    package_name = ServerConfiguration.class.getPackage()
                                                                .getName();
                }

                String bundle_name = package_name + "." + BUNDLE_NAME;
                
                try
                {
                    String infoLog = String.format("bundle_name: %s", bundle_name);
                    logger.info(infoLog);
                    rb = ResourceBundle.getBundle(bundle_name);
                }
                catch (MissingResourceException x)
                {
                    String infoLog = String.format("MissingResourceException: %s", x);
                    logger.info(infoLog);
                    rb = ResourceBundle.getBundle(bundle_name, Locale.ENGLISH);
                }
            }
        }

        BUNDLE = rb;
    }

    
    private ServerConfiguration()
    {
    }
    
    
    public static Locale getLocale(){
        return new Locale(getString("locale.language"), getString("locale.country"));
    }
    
    public static ZoneId getZoneId(){
        return ZoneId.of(getString("zoneid"));
    }

    
    public static String getString(final String key, final String[] vars)
    {
        try
        {
            return TextUtil.variableMessage(BUNDLE.getString(key), vars);
        }
        catch (MissingResourceException e)
        {
            return "**" + key + "**";
        }
    }
    public static String getString(final String key, final String var)
    {
        return getString(key, new String[] { var });
    }
    public static String getString(final String key)
    {
        try
        {
            return BUNDLE.getString(key);
        }
        catch (MissingResourceException e)
        {
            return "**" + key + "**";
        }
    }
}
