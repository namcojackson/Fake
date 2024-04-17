 /*==========================================================================+
 |             ORACLE NAIO (INDIA)              BANGALORE , INDIA            |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  FILENAME                                                                 |
 |     Canon_E193_AttachmentObj.java		                                 |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |     Value Object Bean for Attachements.                                   | 
 |                                                                           | 
 |  MODIFICATION HISTORY                                                     |
 |                                                                           |
 |       18-SEPTEMBER-05 Vijayakumar.B    1.0   Initial Create               |
 |                                                                           |
 |                                                                           |
 +==========================================================================*/
package com.canon.oracle.custapp.custinq.beans;

import java.sql.Blob;

public class Canon_E193_AttachmentObj 
{
  private int iFileId               = 0;
  private String strFileName        = "";
  private String strFileType        = "";
  private String strFileContentType = "";
  private Blob blDownloadData       = null;
  private int iDocumentId			= 0;


  public void setDocumentId(int iDocumentId)
  {
	this.iDocumentId = iDocumentId;	
  }

  public int getDocumentId()
  {
	return iDocumentId;	
  }


  public void setFileId(int iFileId)
  {
    this.iFileId = iFileId;
  }


  public int getFileId()
  {
    return iFileId;
  }


  public void setFileName(String strFileName)
  {
    this.strFileName = strFileName;
  }


  public String getFileName()
  {
    return strFileName;
  }


  public void setFileType(String strFileType)
  {
    this.strFileType = strFileType;
  }


  public String getFileType()
  {
    return strFileType;
  }


  public void setFileContentType(String strFileContentType)
  {
    this.strFileContentType = strFileContentType;
  }


  public String getFileContentType()
  {
    return strFileContentType;
  }


  public void setDownloadData(Blob blDownloadData)
  {
    this.blDownloadData = blDownloadData;
  }


  public Blob getDownloadData()
  {
    return blDownloadData;
  }
  
}