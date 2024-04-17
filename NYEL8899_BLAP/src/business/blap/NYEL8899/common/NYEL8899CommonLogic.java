/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8899.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Properties;

import business.blap.NYEL8899.NYEL8899CMsg;

/**
 *<pre>
 * NYEL8899CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8899CommonLogic {

    public static void readSetting(NYEL8899CMsg bizMsg) {
        Properties configuration = new Properties();
        String path = bizMsg.xxFilePathTxt.getValue();
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(new File(path));
            configuration.load(inputStream);

        } catch (FileNotFoundException e) {
            bizMsg.setMessageInfo("NSZM0396E", new String[] {"Init Parameter", path });
        } catch (IOException e) {
            bizMsg.setMessageInfo("NSZM0396E", new String[] {"Init Parameter", path });
        }

        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!bizMsg.getMessageCode().isEmpty()) {
            return;
        }

        Field[] flds = bizMsg.getClass().getFields();

        try {
            for (Field fld : flds) {
                
                if (Modifier.isStatic(fld.getModifiers())) {
                    continue;
                }
                
                Object fldObj = fld.get(bizMsg);
                
                if (!configuration.containsKey(fld.getName())){
                    continue;
                }
                
                Object param = configuration.getProperty(fld.getName());

                Class<?> clazz = fldObj.getClass();
                Method setter = null;

                Method[] methods = clazz.getMethods();

                for (Method mtd : methods) {
                    if ("setValue".equals(mtd.getName())) {
                        Class<?>[] pms = mtd.getParameterTypes();

                        for (Class<?> pType : pms) {
                            if (pType.equals(String.class)) {
                                setter = mtd;
                                break;
                            } else if (pType.equals(BigDecimal.class)) {
                                setter = mtd;
                                param = new BigDecimal((String) param);
                                break;
                            } else if (pType.equals(int.class)) {
                                setter = mtd;
                                param = Integer.parseInt((String) param);
                                break;
                            }
                        }
                    }

                    if (setter != null) {
                        break;
                    }
                }

                /*
                 * try{ setter = clazz.getMethod("setValue",
                 * String.class); } catch (NoSuchMethodException e) {
                 * setter = clazz.getMethod("setValue",
                 * BigDecimal.class); param =
                 * Integer.parseInt((String)param); }
                 */

                setter.invoke(fldObj, param);
            }

        } catch (IllegalArgumentException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }
}
