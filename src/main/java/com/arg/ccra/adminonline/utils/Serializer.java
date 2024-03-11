package com.arg.ccra.adminonline.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;


public final class Serializer {
    /** @deprecated */
    // private static final Base64Encoder ENCODER = new Base64Encoder();
    /** @deprecated */
    // private static final Base64 DECODER = new Base64();
 
    private Serializer() {
    }
 
    
    public static String objectAsString(Object object) throws IOException {
       if (null == object) {
          throw new GenericRuntimeException("CMN-R00023", ErrorMessages.getString("CMN-R00023"));
       } else {
        //   Base64Encoder encoder = new Base64Encoder();
          ByteArrayOutputStream baos = null;
          ObjectOutputStream oos = null;
 
          String var4;
          try {
             oos = new ObjectOutputStream(baos = new ByteArrayOutputStream());
             oos.writeObject(object);
             var4 = Base64.getEncoder().encodeToString(baos.toByteArray());
          } finally {
             if (null != baos) {
                baos.close();
                baos = null;
             }
 
             if (null != oos) {
                oos.close();
                oos = null;
             }
 
            //  encoder = null;
          }
 
          return var4;
       }
    }
 
    public static Object deserialize(InputStream stream) throws IOException, ClassNotFoundException {
       if (null == stream) {
          throw new GenericRuntimeException("CMN-R00023", ErrorMessages.getString("CMN-R00023"));
       } else {
            // ENCODER decoder = new BASE64Decoder();
          ObjectInputStream ois = null;
          ByteArrayOutputStream baos = null;
          ByteArrayInputStream bais = null;
 
          Object var5;
          try {
            
            // Base64.getDecoder().decode(encoded);
            //  ENCODER.decodeBuffer(stream, baos = new ByteArrayOutputStream());
             byte[] bInput = Base64.getDecoder().decode(stream.readAllBytes());
             baos = new ByteArrayOutputStream();
             baos.write(bInput);
             bais = new ByteArrayInputStream(baos.toByteArray());
             ois = new ObjectInputStream(bais);
             var5 = ois.readObject();
          } finally {
             if (null != baos) {
                baos.close();
                baos = null;
             }
 
             if (null != ois) {
                ois.close();
                ois = null;
             }
 
             if (null != bais) {
                bais.close();
                bais = null;
             }
 
            //  decoder = null;
          }
 
          return var5;
       }
    }
 
    public static Object deserialize(String text) throws IOException, ClassNotFoundException {
       if (null != text && !text.trim().equals("")) {
        //   BASE64Decoder decoder = new BASE64Decoder();
          ObjectInputStream ois = null;
          ByteArrayInputStream bais = null;
 
          Object var5;
          try {
             //byte[] bytes = decoder.decodeBuffer(text);
             byte[] bytes = Base64.getDecoder().decode(text);
             ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
             var5 = ois.readObject();
          } finally {
             if (null != ois) {
                ois.close();
                ois = null;
             }
 
            //  decoder = null;
          }
 
          return var5;
       } else {
          throw new GenericRuntimeException("CMN-R00023", ErrorMessages.getString("CMN-R00023"));
       }
    }
}