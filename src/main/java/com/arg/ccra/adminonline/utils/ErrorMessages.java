package com.arg.ccra.adminonline.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ErrorMessages {
    private static final String BUNDLE_NAME = "common-util-errors";
    private static final ResourceBundle BUNDLE;
 
    private ErrorMessages() {
    }
 
    public static String getString(String key, String[] vars) {
       try {
          return TextUtil.variableMessage(BUNDLE.getString(key), vars);
       } catch (MissingResourceException var3) {
          return "**" + key + "**";
       }
    }
 
    public static String getString(String key, String var) {
       return getString(key, new String[]{var});
    }
 
    public static String getString(String key) {
       try {
          return BUNDLE.getString(key);
       } catch (MissingResourceException var2) {
          return "**" + key + "**";
       }
    }
 
    static {
       ResourceBundle rb = null;
 
       try {
          rb = ResourceBundle.getBundle("common-util-errors");
       } catch (MissingResourceException var9) {
          try {
             rb = ResourceBundle.getBundle("common-util-errors", Locale.ENGLISH);
          } catch (MissingResourceException var8) {
             Package pkg = ErrorMessages.class.getPackage();
             String package_name;
             if (null == pkg) {
                int index;
                if (-1 == (index = (package_name = ErrorMessages.class.getName()).lastIndexOf("."))) {
                   throw var8;
                }
 
                package_name = package_name.substring(0, index);
             } else {
                package_name = ErrorMessages.class.getPackage().getName();
             }
 
             String bundle_name = package_name + "." + "common-util-errors";
 
             try {
                rb = ResourceBundle.getBundle(bundle_name);
             } catch (MissingResourceException var7) {
                rb = ResourceBundle.getBundle(bundle_name, Locale.ENGLISH);
             }
          }
       }
 
       BUNDLE = rb;
    }
 }
 