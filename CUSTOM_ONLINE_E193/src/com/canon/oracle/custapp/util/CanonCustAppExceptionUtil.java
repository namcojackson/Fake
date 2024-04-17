package com.canon.oracle.custapp.util;

/**
 * CanonCustAppExceptionUtil Utility class for all the Exception Handling in the CSR
 * Creation date: (8/23/2005 6:05:38 PM).
 * @author:
 */

public class CanonCustAppExceptionUtil extends Exception
{
  public int iErrorCode;
  public java.lang.String strErrorDesc;
  public java.lang.String strErrorLocation;

/**
 * CanonCustAppExceptionUtil constructor.
 */
public CanonCustAppExceptionUtil() {
  super();
}

/**
 * This Constructor takes three input parameter and
 * set the values for all the parameters passed.
 * Creation date: (8/24/05 12:00:00 PM)
 * @param iErrCode int
 * @param strErrDesc java.lang.String
 * @param strErrLoc java.lang.String
 */
public CanonCustAppExceptionUtil(int iErrCode, String strErrDesc, String strErrLoc)
{
  super();
  setIErrorCode(iErrCode);
  setStrErrorDesc(strErrDesc);
  setStrErrorLocation(strErrLoc);

}

/**
 * This method returs the error code as int.
 * Creation date: (8/24/05 12:00:00 PM)
 * @return iErrorCode int
 */
public int getIErrorCode()
{
  return iErrorCode;
}

/**
 * This method returs the error description as string.
 * Creation date: (8/24/05 12:00:00 PM)
 * @return strErrorDesc java.lang.String
 */
public java.lang.String getStrErrorDesc() {
  return strErrorDesc;
}

/**
 * This method returs the error location as string.
 * Creation date: (8/24/05 12:00:00 PM)
 * @return strErrorLocation java.lang.String
 */
public java.lang.String getStrErrorLocation() {
  return strErrorLocation;
}

/**
 * This method sets the error code for the passed parameter.
 * Creation date: (8/24/05 12:00:00 PM)
 * @param newIErrorCode int
 */
public void setIErrorCode(int newIErrorCode)
{
  iErrorCode = newIErrorCode;
}

/**
 * This method sets the error description for the passed parameter.
 * Creation date: (8/24/05 12:00:00 PM)
 * @param newStrErrorDesc java.lang.String
 */
public void setStrErrorDesc(java.lang.String newStrErrorDesc)
{
  strErrorDesc = newStrErrorDesc;
}

/**
 * This method sets the error location for the passed parameter.
 * Creation date: (8/24/05 12:00:00 PM)
 * @param newStrErrorLocation java.lang.String
 */
public void setStrErrorLocation(java.lang.String newStrErrorLocation)
{
  strErrorLocation = newStrErrorLocation;
}
}