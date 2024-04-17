/*
 * Copyright (c) 2008 Canon USA Inc. All rights reserved.
 * Original Author: Kazuhiro Tajima
 * Company: Fujitsu Limited
 * Date: Jan 8, 2009
 */
package business.servlet.ZZZL9900.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import parts.servletcommon.EZDApplicationContext;

public class ZZZL9900CommonLogic {

    /**
     * プロパティから指定されたキーの値を返します。
     *
     * @param key プロパティキー名
     * @return プロパティキー値
     */
    public static String getProperty(String key) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("_TestBusinessAplID");
            return rb.getString(key);
        } catch (MissingResourceException e) {
        }

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("C:/S21_EZDeveloper/dvlpPC/00temp/stubFiles/_TestBusinessAplID.properties"));
            return prop.getProperty(key);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * StubDirを返します。
     * @param ctx 
     * @return
     */
    public static String getStubDir( EZDApplicationContext ctx ) {
        String dir = ctx.getSettingStr( "EZD_DENBUN_DIR" );
        if( !dir.endsWith( File.separator ) ) {
            dir = dir + File.separator;
        }
        return dir;
    }
}
