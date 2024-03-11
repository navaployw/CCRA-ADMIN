package com.arg.ccra.adminonline.utils;

import java.math.BigDecimal;
import java.util.Arrays;

public class TextUtil {
   private static final String[] NULL_STRING_ARRAY = new String[0];
   public static final String NULL = "";
   private static final char THOUSAND_SEPARATOR = ',';

   private TextUtil() {
   }

   public static String leftPad(String src, char pad, int length) {
      if (0 > length) {
         throw new GenericRuntimeException("CMN-R00008", ErrorMessages.getString("CMN-R00008", "" + length));
      } else {
         char[] buffer = new char[length];

         String var4;
         try {
            Arrays.fill(buffer, pad);
            if (null != src && src.length() != 0) {
               if (src.length() == length) {
                  var4 = src;
                  return var4;
               }

               if (src.length() > length) {
                  src.getChars(src.length() - length, src.length(), buffer, 0);
               } else {
                  src.getChars(0, src.length(), buffer, length - src.length());
               }

               var4 = new String(buffer);
               return var4;
            }

            var4 = new String(buffer);
         } finally {
            Object var8 = null;
         }

         return var4;
      }
   }

   public static String rightPad(String src, char pad, int length) {
      if (0 > length) {
         throw new GenericRuntimeException("CMN-R00008", ErrorMessages.getString("CMN-R00008", "" + length));
      } else {
         char[] buffer = new char[length];

         try {
            Arrays.fill(buffer, pad);
            String var4;
            if (null != src && src.length() != 0) {
               if (src.length() != length) {
                  if (src.length() > length) {
                     src.getChars(0, length, buffer, 0);
                  } else {
                     src.getChars(0, src.length(), buffer, 0);
                  }

                  var4 = new String(buffer);
                  return var4;
               } else {
                  var4 = src;
                  return var4;
               }
            } else {
               var4 = new String(buffer);
               return var4;
            }
         } finally {
            Object var8 = null;
         }
      }
   }

   public static String variableMessage(String msg, String[] args) {
      if (null != msg && null != args) {
         if (args.length == 0) {
            return msg;
         } else {
            msg = msg.replaceAll("\\{cr\\}", "\r").replaceAll("\\{lf\\}", "\n");

            for(int index = 0; index < args.length; ++index) {
               if (null != args[index]) {
                  msg = msg.replaceAll("\\{" + index + "\\}", args[index].replaceAll("\\\\", "\\\\\\\\").replaceAll("\\$", "\\\\\\$"));
               }
            }

            return msg;
         }
      } else {
         throw new GenericRuntimeException("CMN-R00009", ErrorMessages.getString("CMN-R00009", new String[]{TextUtil.class.getName(), "variableMessage"}));
      }
   }

   public static String replaceChars(String src, String chars, String[] replaceWith) {
      StringBuffer result = new StringBuffer();
      if (null != src && null != chars && null != replaceWith && src.length() != 0 && chars.length() != 0) {
         char[] s = src.toCharArray();
         char[] c = chars.toCharArray();
         char[] buffer = new char[255];
         int i = 0;

         try {
            for(int index = 0; index < s.length; ++index) {
               if (buffer.length <= i) {
                  result.append(buffer);
                  i = 0;
               }

               char ch = s[index];

               int a;
               for(a = 0; a < c.length; ++a) {
                  if (ch == c[a]) {
                     if (replaceWith.length > a) {
                        if (0 < i) {
                           result.append(buffer, 0, i);
                           i = 0;
                        }

                        result.append(replaceWith[a]);
                     }
                     break;
                  }
               }

               if (c.length <= a) {
                  buffer[i++] = ch;
               }
            }

            if (i > 0) {
               result.append(buffer, 0, i);
            }

            String var17 = result.toString();
            return var17;
         } finally {
            s = null;
            c = null;
            Object var16 = null;
         }
      } else {
         return result.toString();
      }
   }

   public static String removeChars(String src, String chars) {
      return replaceChars(src, chars, NULL_STRING_ARRAY);
   }

   public static String formatNumber(String number) throws NumberFormatException {
      return formatNumber(new BigDecimal(number));
   }

   public static String formatNumber(String number, int scale) throws NumberFormatException {
      return formatNumber(new BigDecimal(number), scale);
   }

   public static String formatNumber(BigDecimal number) {
      return formatDecimal(number, 0, 1);
   }

   public static String formatNumber(BigDecimal number, int scale) {
      return formatDecimal(number, scale, 4);
   }

   public static String formatDecimal(BigDecimal number, int scale, int roundingMode) {
      return null == number ? "" : formatDecimal(number.setScale(scale, roundingMode));
   }

   public static String formatDecimal(BigDecimal number) {
      if (null == number) {
         return "";
      } else {
         int LEN = 1;
         String[] parts = number.abs().toString().split("\\.");
         char[] int_part = parts[0].toCharArray();
         StringBuffer buf = new StringBuffer();
         int length = int_part.length;
         if (0 > number.signum()) {
            buf.append('-');
         }

         for(int index = 0; index < length; ++index) {
            buf.append(int_part[index]);
            if ((length - index - 1) % 3 == 0 && index < length - 1) {
               buf.append(',');
            }
         }

         if (2 == parts.length) {
            buf.append(".").append(parts[1]);
         }

         int_part = null;
         return buf.toString();
      }
   }
}
