/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Q01487
 * Company: Fujitsu Limited
 * Date: Jun 30, 2009
 */
package business.blap.ZZSL1110.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * The class S21AuthorizationServiceConfig provides to read
 * authorization service properties file in order to get logger type
 * information.
 * @author $Author: Piotr Cebula $
 * @version $Revision: 1.2 $ $Date: 2009/07/17 15:25:52 $
 */
public final class S21AuthorizationMaintenanceConfig {

    /**
     * S21AuthorizationServiceConfig instance.
     */
    private static S21AuthorizationMaintenanceConfig instance;

    /**
     * Authorization service properties.
     */
    private Properties properties = null;

    /**
     * Default authorization application property file name.
     */
    private static final String AUTH_PROPERTY_FILE = "s21-auth-app.properties";

    /**
     * The Constant USER_COUNTRY.
     */
    public static final String APP_COUNTRY = "s21.auth.app.country";

    /**
     * The Constant APP_DEFAULT_COUNTRY.
     */
    public static final String APP_DEFAULT_COUNTRY = "s21.auth.app.default.country";

    /**
     * The Constant USER_LANGUAGE.
     */
    public static final String APP_LANGUAGE = "s21.auth.app.lanuage";

    /**
     * The Constant APP_DEFAULT_LANGUAGE.
     */
    public static final String APP_DEFAULT_LANGUAGE = "s21.auth.app.default.langauge";

    /**
     * The Constant APP_DEFAULT_COMPANY_CODE.
     */
    public static final String APP_DEFAULT_COMPANY_CODE = "s21.auth.app.default.company_code";

    /**
     * The Constant APP_DEFAULT_COMPANY_NAME.
     */
    public static final String APP_DEFAULT_COMPANY_NAME = "s21.auth.app.default.company_name";

    /**
     * The Constant USE_DEFUALT_COMPANY_CODE.
     */
    public static final String USE_DEFUALT_COMPANY_CODE = "s21.auth.app.default.company_code.only";
    
    
    /**
     * Application mode setting
     * Possible values:
     * - Dev
     * - Normal
     */
    public static final String APP_MODE = "s21.auth.app.mode";
    
    /**
     * The Constant APP_DATA_ACCESS.
     */
    public static final String APP_DATA_ACCESS = "s21.auth.app.data.access";
    
    /**
     * The Constant APP_DATA_ACCESS.
     */
    public static final String IMPORT_DISABLE = "s21.auth.app.import.disable";
    
    /**
     * The Constant TRUE.
     */
    public static final String TRUE = "true";
    
    /**
     * The Constant FALSE.
     */
    public static final String FALSE = "false";

    /**
     * The Constant APP_CACHE_ENABLE.
     */
    public static final String APP_CACHE_ENABLE = "s21.auth.app.cache.enabled";
    
    /**
     * The Constant APP_AUDIT_ENABLE.
     */
    public static final String APP_AUDIT_ENABLE = "s21.auth.app.audit.trail.enabled";
    
    /**
     * The Constant APP_AUDIT_ENABLE.
     */
    public static final String APP_SECURITY_ENABLE = "s21.auth.app.security.enabled";
    
    /**
     * S21AuthorizationServiceConfig constructor.
     */
    private S21AuthorizationMaintenanceConfig() {
        super();
        init();
    }

    /**
     * Gets the instance.
     * @return S21AuthorizationServiceConfig - instance of
     * S21AuthorizationServiceConfig
     */
    public static synchronized S21AuthorizationMaintenanceConfig getInstance() {
        if (instance == null) {
            return new S21AuthorizationMaintenanceConfig();
        }
        return instance;
    }

    /**
     * Gives input stream for the given resource file name.
     * @param resourceFile the resource file name
     * @return InputStream the Input Stream
     * @throws FileNotFoundException - FileNotFoundException
     * @throws SecurityException - SecurityException
     */
    public InputStream getInputStream(String resourceFile) throws FileNotFoundException, SecurityException {
        InputStream istream = null;

        try {
            istream = new FileInputStream(resourceFile);
        } catch (FileNotFoundException exception) {
            try {
                istream = S21AuthorizationMaintenanceConfig.class.getClassLoader().getResourceAsStream(resourceFile);
            } catch (Exception e) {
                istream = ClassLoader.getSystemResourceAsStream(resourceFile);
            }

            if (istream == null) {
                System.out.println("Could not find resource file " + resourceFile);
            }
        }
        return (istream);
    }

    /**
     * It initializes input stream and load the stream using
     * properties object.
     */
    private synchronized void init() {
        InputStream is;
        try {
            is = getInputStream(AUTH_PROPERTY_FILE);
            properties = new Properties();
            if (is != null) {
                properties.load(is);
            }
        } catch (FileNotFoundException e) {
            String[] params = {AUTH_PROPERTY_FILE };
            throw new S21AbendException(e, "ZZSM0010E", params);
        } catch (SecurityException e) {
            String[] params = {AUTH_PROPERTY_FILE };
            throw new S21AbendException(e, "ZZSM0011E", params);
        } catch (IOException e) {
            String[] params = {AUTH_PROPERTY_FILE };
            throw new S21AbendException(e, "ZZSM0011E", params);
        }

    }

    /**
     * Gets the all countries.
     * @return the all countries
     */
    public HashMap<String, String> getAllCountries() {
        if (properties != null) {
            HashMap<String, String> countryMap = new HashMap<String, String>();
            try {
                Enumeration enumOne = properties.propertyNames();
                if (enumOne != null) {
                    while (enumOne.hasMoreElements()) {
                        String name = (String) enumOne.nextElement();
                        if (name.startsWith(APP_COUNTRY)) {
                            countryMap.put(name.substring(name.lastIndexOf('.') + 1), properties.getProperty(name));
                        }
                    }
                    return countryMap;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Gets the all languages.
     * @return the all languages
     */
    public HashMap<String, String> getAllLanguages() {
        if (properties != null) {
            HashMap<String, String> langMap = new HashMap<String, String>();
            try {
                Enumeration enumOne = properties.propertyNames();
                if (enumOne != null) {
                    while (enumOne.hasMoreElements()) {
                        String name = (String) enumOne.nextElement();
                        if (name.startsWith(APP_LANGUAGE)) {
                            langMap.put(name.substring(name.lastIndexOf('.') + 1), properties.getProperty(name));
                        }
                    }
                    return langMap;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Gets the default country.
     * @return the default country
     */
    public String getDefaultCountry() {
        if (properties != null) {
            String defCountry = "";
            if (properties.containsKey(APP_DEFAULT_COUNTRY)) {
                defCountry = properties.getProperty(APP_DEFAULT_COUNTRY);
                return defCountry;
            }
        }
        return null;
    }

    /**
     * Gets the default country.
     * @return the default country
     */
    public String getDefaultLanguage() {
        if (properties != null) {
            String defLanguage = "";
            if (properties.containsKey(APP_DEFAULT_LANGUAGE)) {
                defLanguage = properties.getProperty(APP_DEFAULT_LANGUAGE);
                return defLanguage;
            }
        }
        return null;
    }
    
    /**
     * Gets the default company code.
     * @return String
     */
    public String getDefaultCompanyCode() {
        return getDefaultValue(APP_DEFAULT_COMPANY_CODE);
    }
    
    /**
     * Gets the default company name.
     * @return String
     */
    public String getDefaultCompanyName() {
        return getDefaultValue(APP_DEFAULT_COMPANY_NAME);
    }
    
    /**
     * Gets a value for input key string.
     * @param keyString the key string
     * @return the value
     */
    private String getDefaultValue(String keyString) {
        if (properties != null) {
            if (properties.containsKey(keyString)) {
                return properties.getProperty(keyString);
            }
        }
        return null;
    }
    
   /**
     * Gets the application data access.
     * @return the application data access
     */
   public boolean getApplicationDataAccess() {
       if (properties != null) {
           if (properties.get(APP_DATA_ACCESS) != null) {
               String exists = String.valueOf(properties.get(APP_DATA_ACCESS));
               if (exists.equalsIgnoreCase(TRUE)) {
                   return true;
               }
           }
       }
       return false;
   }
   
   /**
     * Checks if is use default company code.
     * @return true, if is use default company code
     */
   public boolean isUseDefaultCompanyCode() {
       if (properties != null) {
           if (properties.get(USE_DEFUALT_COMPANY_CODE) != null) {
               String exists = String.valueOf(properties.get(USE_DEFUALT_COMPANY_CODE));
               if (exists.equalsIgnoreCase(TRUE)) {
                   return true;
               }
           }
       }
       return false;
   }
   
   /**
    * Gets the import disable flag.
    * @return boolean
    */
   public boolean isImportDisable() {
      if (properties != null) {
          if (properties.get(IMPORT_DISABLE) != null) {
              String exists = String.valueOf(properties.get(IMPORT_DISABLE));
              if (exists.equalsIgnoreCase(TRUE)) {
                  return true;
              }
          }
      }
      return false;
  }
   
   /**
     * Gets the application mode.
     * @return the application mode
     */
   public String getApplicationMode() {
       String value =  getDefaultValue(APP_MODE);
       if( value == null ) {
           return "Normal";
       } else {
           return value;
       }
   }
   
   
   /**
     * Checks if is app cache enabled.
     * @return true, if is app cache enabled
     */
  public boolean isAppCacheEnabled() {
      if (properties != null) {
          if (properties.get(APP_CACHE_ENABLE) != null) {
              String exists = String.valueOf(properties.get(APP_CACHE_ENABLE));
              if (exists.equalsIgnoreCase(TRUE)) {
                  return true;
              }
          }
      }
      return false;
  }

  /**
   * Gets the import disable flag.
   * @return boolean
   */
  public boolean isAuditTrailEnabled() {
     if (properties != null) {
         if (properties.get(APP_AUDIT_ENABLE) != null) {
             String exists = String.valueOf(properties.get(APP_AUDIT_ENABLE));
             if (exists.equalsIgnoreCase(TRUE)) {
                 return true;
             }
         }
     }
     return false;
 }

  /**
   * Returns true if application security (access control) is enabled 
   * @return boolean
   */
  public boolean isAccessControlEnabled() {
     if (properties != null) {
         if (properties.get(APP_SECURITY_ENABLE) != null) {
             String exists = String.valueOf(properties.get(APP_SECURITY_ENABLE));
             if (exists.equalsIgnoreCase(FALSE)) {
                 return false;
             }
         }
     }
     return true;
 }

}
