/*     */ package oracle.apps.fnd.common;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class VersionInfo
/*     */ {
/*     */   public static final String RCS_ID = "$Header: VersionInfo.java 115.11 2003/11/27 22:06:09 mskees ship $";
/*  82 */   public static final String AOLJ_VERSION = new String("Applications Object Library, Core Java Roll Up Patch J");
/*     */ 
/*  85 */   private static Vector mRCS_IDs = new Vector(500, 100);
/*     */ 
/*     */   public static Vector getRCS_IDs()
/*     */   {
/*  91 */     return mRCS_IDs;
/*     */   }
/*     */ 
/*     */   public static final boolean recordClassVersion(String paramString1, String paramString2)
/*     */   {
/* 105 */     if (paramString1 != null)
/*     */     {
/* 107 */       mRCS_IDs.addElement(paramString2 + "#" + paramString1);
/* 108 */       return true;
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   public static final String recordVersion(String paramString1, String paramString2)
/*     */   {
/* 124 */     if (paramString1 != null)
/*     */     {
/* 126 */       mRCS_IDs.addElement(paramString2 + "#" + paramString1);
/* 127 */       return paramString1;
/*     */     }
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */   public static final boolean recordClassVersion(Class paramClass)
/*     */   {
/*     */     try
/*     */     {
/* 143 */       return recordVersion((String)paramClass
/* 144 */         .getField("RCS_ID")
/* 145 */         .get(null), 
/* 146 */         paramClass) != null;
/*     */     }
/*     */     catch (Exception localException) {
/*     */     }
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */   public static final String recordVersion(String paramString, Class paramClass)
/*     */   {
/*     */     try
/*     */     {
/* 167 */       String str2 = paramClass.getName();
/*     */ 
/* 170 */       int i = str2.lastIndexOf(".");
/*     */       String str1;
/* 171 */       if (i < 0) str1 = ""; else {
/* 172 */         str1 = str2.substring(0, i);
/*     */       }
/*     */ 
/* 175 */       if (recordClassVersion(paramString, str1)) return paramString;
/* 176 */       return null;
/*     */     }
/*     */     catch (Exception localException) {
/*     */     }
/* 180 */     return null;
/*     */   }
/*     */ 
/*     */   public static void printVersionInfo()
/*     */   {
/* 190 */     printVersionInfo(System.out);
/*     */   }
/*     */ 
/*     */   public static void printVersionInfo(PrintStream paramPrintStream)
/*     */   {
/* 199 */     printVersionInfo(new PrintWriter(paramPrintStream));
/*     */   }
/*     */ 
/*     */   public static void printVersionInfo(PrintWriter paramPrintWriter)
/*     */   {
/* 208 */     for (Enumeration localEnumeration = getPrettyVersionInfo(); localEnumeration.hasMoreElements(); )
/*     */     {
/* 210 */       paramPrintWriter.println(localEnumeration.nextElement());
/*     */     }
/* 212 */     paramPrintWriter.flush();
/*     */   }
/*     */ 
/*     */   public static Enumeration getVersionInfo()
/*     */   {
/* 222 */     Enumeration localEnumeration1 = getRCS_IDs().elements();
/* 223 */     Vector localVector = new Vector(getRCS_IDs().size());
/*     */ 
/* 227 */     while (localEnumeration1.hasMoreElements())
/*     */     {
/* 229 */       String str = (String)localEnumeration1.nextElement();
/*     */ 
/* 231 */       int i = 0;
/*     */ 
/* 233 */       Enumeration localEnumeration2 = localVector.elements();
/*     */ 
/* 235 */       while ((localEnumeration2.hasMoreElements()) && 
/* 236 */         (str.compareTo((String)localEnumeration2.nextElement()) > 0)) {
/* 237 */         i++;
/*     */       }
/*     */ 
/* 240 */       localVector.insertElementAt(str, i);
/*     */     }
/*     */ 
/* 243 */     return localVector.elements();
/*     */   }
/*     */ 
/*     */   public static Enumeration getPrettyVersionInfo()
/*     */   {
/* 253 */     Enumeration localEnumeration = getVersionInfo();
/* 254 */     Vector localVector = new Vector(getRCS_IDs().size(), 10);
/*     */ 
/* 256 */     Object localObject = "";
/*     */ 
/* 258 */     while (localEnumeration.hasMoreElements())
/*     */     {
/* 260 */       String str1 = (String)localEnumeration.nextElement();
/*     */ 
/* 262 */       String str2 = str1.substring(0, str1.indexOf("#"));
/* 263 */       String str3 = str1.substring(str1.indexOf("#") + 1);
/*     */ 
/* 265 */       if (str2.compareTo((String)localObject) != 0) {
/* 266 */         localObject = str2;
/* 267 */         localVector.insertElementAt(str2, localVector.size());
/*     */       }
/* 269 */       localVector.insertElementAt(str3, localVector.size());
/*     */     }
/* 271 */     return localVector.elements();
/*     */   }
/*     */ 
/*     */   public static void displayClassVersion(String paramString)
/*     */   {
/* 281 */     displayClassVersion(paramString, System.out);
/*     */   }
/*     */ 
/*     */   public static void displayClassVersion(String paramString, PrintStream paramPrintStream)
/*     */   {
/* 290 */     displayClassVersion(paramString, new PrintWriter(paramPrintStream));
/*     */   }
/*     */ 
/*     */   public static void displayClassVersion(String paramString, PrintWriter paramPrintWriter)
/*     */   {
/*     */     try
/*     */     {
/* 305 */       paramPrintWriter.println(">>> Class: " + paramString);
/* 306 */       paramPrintWriter.println(Class.forName(paramString).getDeclaredField("RCS_ID").get(null));
/*     */     }
/*     */     catch (ClassNotFoundException localClassNotFoundException) {
/* 309 */       paramPrintWriter.println("Unable to find class " + paramString);
/*     */     } catch (NoSuchFieldException localNoSuchFieldException) {
/* 311 */       paramPrintWriter.println("Class " + paramString + " does not have an RCS_ID field");
/*     */     } catch (SecurityException localSecurityException) {
/* 313 */       paramPrintWriter.println("Security exception, unable to access field");
/*     */     } catch (IllegalAccessException localIllegalAccessException) {
/* 315 */       paramPrintWriter.println("IllegalAccessException, unable to access field");
/*     */     } catch (Exception localException) {
/* 317 */       localException.printStackTrace(paramPrintWriter);
/*     */     }
/*     */ 
/* 320 */     paramPrintWriter.flush();
/*     */   }
/*     */ 
/*     */   public static void AOLJ_RUP(String paramString)
/*     */   {
/* 330 */     System.out.println(AOLJ_VERSION);
/* 331 */     System.out.println("$Header: VersionInfo.java 115.11 2003/11/27 22:06:09 mskees ship $");
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 341 */     AOLJ_RUP(null);
/* 342 */     for (int i = 0; i < paramArrayOfString.length; i++)
/*     */     {
/* 344 */       displayClassVersion(paramArrayOfString[i]);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Temp\oracle\oracle.apps.fnd.zip
 * Qualified Name:     oracle.apps.fnd.common.VersionInfo
 * JD-Core Version:    0.6.2
 */