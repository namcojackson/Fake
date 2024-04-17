 /*===========================================================================+
 |             ORACLE NAIO (INDIA)              BANGALORE , INDIA            |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  FILENAME                                                                 |
 |     CanonUploadUtil.java		                                             |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |     Util class for Attachements which checks for Format Type and          |
 |	   Content Type.                                                         | 
 |                                                                           | 
 |  MODIFICATION HISTORY                                                     |
 |                                                                           |
 |       18-SEPTEMBER-05 Vijayakumar.B    1.0   Initial Create.               |
 |                                                                           |
 |                                                                           |
 +==========================================================================*/
package com.canon.oracle.custapp.util;

public class CanonUploadUtil
{
	public String getUploadContentType(String strFileType)
    {
		String strCntType = "";
		if (strFileType.trim().equalsIgnoreCase("txt"))
		{
		  strCntType =  "text/plain";
		}
		else if (strFileType.trim().equalsIgnoreCase("doc")||strFileType.trim().equalsIgnoreCase("docx") )
		{
		  strCntType =  "application/msword";
		}
		else if (strFileType.trim().equalsIgnoreCase("xls"))
		{
		  strCntType =  "application/vnd.ms-excel";
		}
		else if (strFileType.trim().equalsIgnoreCase("pdf"))
		{
		  strCntType =  "application/pdf";
		}
		else if (strFileType.trim().equalsIgnoreCase("ppt"))
		{
		  strCntType =  "application/ppt";
		}
		else
		{
		  strCntType =  "application/octet-stream";
		}
	return strCntType;
	}
  
	public String getUploadFileFormat(String strFileType)
	{
		String strFormatType = "";
	    if (strFileType.equals("txt"))
	    {
		  strFormatType = "text";
	    }
	    else
	    {
		  strFormatType = "binary";
	    }
	    return strFormatType;
	 }
	
	public static String getSubStringByDelimitter(String actualString, String delimitter)
	{
		String resultString = "";
		if(actualString != null && !"".equalsIgnoreCase(actualString.trim()))
		{
			resultString = actualString.substring(actualString.lastIndexOf(delimitter)+1,actualString.length());
		}
		return resultString;
	}
  
	public static String getFileExtension(String strFileName)
	{
		return getSubStringByDelimitter(strFileName, ".");
	}
	
	public static String getFileName(String fileNameWithPath)
	{
		return getSubStringByDelimitter(fileNameWithPath, "/");
	}
}


