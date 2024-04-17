 /*===========================================================================+
 |             ORACLE NAIO (INDIA)              BANGALORE , INDIA            |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  FILENAME                                                                 |
 |     Canon_E193_AttachmentUploadDAO.java                                   |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |     Data Access Object for Attachements.                                  | 
 |                                                                           | 
 |  MODIFICATION HISTORY                                                     |
 |                                                                           |
 |       18-SEPTEMBER-05 Vijayakumar.B    1.0   Initial Create               |
 |                                                                           |
 |                                                                           |
 +==========================================================================*/
package com.canon.oracle.custapp.custinq.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import oracle.apps.jtf.aom.transaction.TransactionScope;
// For Cursor Implementation
import oracle.jdbc.OracleTypes;
import oracle.sql.BLOB;
import oracle.sql.STRUCT;
import canon.apps.common.CanonConstants;

import com.canon.common.CanonCommonUtil;
import com.canon.oracle.custapp.custinq.beans.CanonE193AttFileRec;
// Custom Imports
import com.canon.oracle.custapp.custinq.beans.Canon_E193_AttachmentObj;
import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;
import com.canon.oracle.custapp.util.CanonUploadUtil;



/**
   * Canon_E193_AttachmentUploadDAO class will get the db connection, upload files, download files and delete files.
   */
public class Canon_E193_AttachmentUploadDAO implements Serializable
{
  //variable declaration
  CanonCustAppDBUtil connUtil	= null;
  OutputStream blobOutputStream = null;
  Connection conn               = null;
  Statement objStatement        = null;
  ResultSet objResultSet        = null;
  PreparedStatement objPrepStat = null;
  InputStream sampleFileStream  = null;
  BLOB bUpload                  = null;
  String strFilePath            = "";
  String strResult              = "";
  CanonCommonUtil util;
  private   String clsName="Canon_E193_AttachmentUploadDAO";
  String SCH_NAME="";	
  public Canon_E193_AttachmentUploadDAO()
  {
	  util = new CanonCommonUtil();
	  SCH_NAME = CanonConstants.SCHEMA_NAME;
  }
  
  /**
   * This uploadFile() is used to upload the files into DB.
   */
  public boolean uploadFile (byte[] fileContent, String strFileName, 
	                         String strFileContentType, String strFileFormat, 
							 int fileSize,String strValue, String strTicketNumber) throws CanonCustAppExceptionUtil
  {
	boolean bResult			= false;
	int iValue				= 0;
	CallableStatement proc	= null;
	boolean bolValue		= false;
	Blob blob				= null;
	System.out.println("in uploadFile strFileName: " + strFileName + " strTicketNumber : " + strTicketNumber);
	// First insert into fnd_lobs_document which is a temp table. Then from here it will uploaded into fnd_lobs.
	try
	{
		connUtil					= new CanonCustAppDBUtil();
		conn						= (Connection)(connUtil.getConnection());
		objStatement				= conn.createStatement();	
		CallableStatement callStmt	= null;
 		String strLoadFile			= "{ call canon_e193_file_load.load_file_details(?,?,?,?,?) }";
		callStmt					= conn.prepareCall(strLoadFile);
		//System.out.println("hello load file details call is prepared");
		callStmt.setString(1,strFileName);
		callStmt.setString(2,strFileContentType);
		callStmt.setInt(3,fileSize);
		callStmt.setString(4,strFileFormat);
		//System.out.println("hello load file details call ");
		callStmt.registerOutParameter(5, OracleTypes.CURSOR);
		callStmt.execute();
		//System.out.println("hello load file details call is executed" + callStmt.getObject(5));
		objResultSet = (ResultSet) callStmt.getObject(5);
		//System.out.println("hello load file details after getting resulytset" + objResultSet);
	  if (objResultSet.next())
      {	
		Blob bBlob			= objResultSet.getBlob("BLOB_CONTENT");
		//System.out.println("hello load file details after getting bBlob" + bBlob);
		blobOutputStream = ((oracle.sql.BLOB)bBlob).getBinaryOutputStream();	
		//System.out.println("hello load file details after getting blobOutputStream" + blobOutputStream);
		// Select the blob cloumn and insertion the file as Outputstream into the Blob column
		blobOutputStream.write(fileContent);
        blobOutputStream.flush();
        blobOutputStream.close();
		conn.commit();
		bResult = true;
	  }
      conn.commit();
      //System.out.println("hello load file details call is committed");
	  /* 

		This procedure call get the files from the FND_LOBS_DOCUMENT and insert into the fnd_lobs, 
		fnd_documents and fnd_documents_tl tables.

	  */
    
	  String x_errbuf	= "";			
	  int x_retcode		= 0;
	  long x_access_id  = 0;
	  int x_file_id		= 0;

      proc = conn.prepareCall("{ call CANON_E193_FILE_LOAD.upload_file(?, ?, ?, ?, ? ) }");
      //System.out.println("call prepared to upload file");
      proc.registerOutParameter(1, Types.VARCHAR);
      proc.registerOutParameter(2, Types.INTEGER);
	  proc.setString(3,strFileName);
      proc.registerOutParameter(4, Types.INTEGER);
	  proc.registerOutParameter(5, Types.INTEGER);

      proc.execute();
      //System.out.println("query executed to upload file");
	  conn.commit();

 	  x_errbuf    = proc.getString(1);
	  x_retcode   = proc.getInt(2);
	  x_access_id = proc.getLong(4);	
  	  x_file_id   = proc.getInt(5);
	  
	  // After inserting into the fnd_lobs table from fnd_lobs_document
	   String	x_errbuf_1					= "";            
	   int		x_retcode_1					= 0;         
	   String 	x_rowid 					= "";         
	   int		x_attached_document_id		= 0;      
	   int		x_document_id				= 0;      
	   int		x_media_id					= 0;      
	   String	v_entity_name				= "";          
	   String 	v_category_name				= "";          
	   String 	v_file_name					= "";          
	   String 	v_pk1_value					= strTicketNumber;          
	   String	v_pk2_value					= "";          
	   String	v_pk3_value					= "";          
	   String	v_pk4_value					= "";          
	   String	v_pk5_value					= "";          
	   String	v_usage_type				= "O";          
	   String   v_language					= "US";          
	   String	v_publish_flag				= "Y";          
	   int	 	v_security_type				= 1;          
	   String  	v_automatically_added_flag	= "Y";   	


      if(x_retcode == 0 )
	  {
		proc = 
			 conn.prepareCall("{ call CANON_E193_FILE_LOAD.attach_file(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) }");
//System.out.println("prepare call to attach file");
		proc.registerOutParameter(1,  Types.VARCHAR);
	    proc.registerOutParameter(2,  Types.INTEGER);
		proc.registerOutParameter(3,  Types.VARCHAR);
		proc.registerOutParameter(4,  Types.INTEGER);
		proc.registerOutParameter(5,  Types.INTEGER);	
		proc.registerOutParameter(6,  Types.INTEGER);
		proc.setString(7, v_entity_name);
		proc.setString(8, v_category_name);
		proc.setString(9, strFileName);
		proc.setInt(10, x_file_id);
 		proc.setString(11, v_pk1_value);
	    proc.setString(12, v_pk2_value);
		proc.setString(13, v_pk3_value);
		proc.setString(14, v_pk4_value);
		proc.setString(15, v_pk5_value);
 		proc.setString(16, v_usage_type);
	    proc.setString(17, v_language);
		proc.setString(18, v_publish_flag);
		proc.setInt(19, v_security_type);
		proc.setString(20, v_automatically_added_flag);
   	    proc.execute();
   	 //System.out.println("executed call to attach file");
        conn.commit();

        //System.out.println("query commited to attach file");
     	x_errbuf_1				= proc.getString(1);
		x_retcode_1				= proc.getInt(2);
		x_rowid					= proc.getString(3);	
		x_attached_document_id	= proc.getInt(4);
		x_document_id			= proc.getInt(5);
		x_media_id				= proc.getInt(6);	

	  } // end of if 

	} // end of try
    catch (CanonCustAppExceptionUtil csExp) 
	{
		throw (csExp);
	}
	catch (SQLException eSQLExp) 
	{
		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	}
	catch (Exception eExp) 
	{
		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	}// End of try catch block
	finally 
	{
		try 
		{
			if(objResultSet != null) objResultSet.close();
	        if(conn != null) conn.close();
	        if(connUtil != null) connUtil.releaseConnection();
	    }
	    catch (SQLException eSQLExp) 
		{
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	    }
	    catch (Exception eExp) 
		{
	     	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	    }
	}// End of finally block
    return bResult;
  }
  
	/**
	 * This deleteCSAFile() is used to delete the selected file from DB and
	 * Ticket Number.
	 */  
	public boolean deleteCSAFile(String ticketNumber, String strDocumentId,
			int id) throws CanonCustAppExceptionUtil {
		System.out.println("Inside deleteCSAFile Method StrDocumentId "
				+ strDocumentId + " ticketNumber " + ticketNumber + " id " + id);
		boolean bDelete = false;
		CallableStatement proc = null;
		strDocumentId = strDocumentId == null ? "0" : strDocumentId;
		int iDocumentId = Integer.parseInt(strDocumentId);
		//System.out.println(" Inside Delete Method~ ");
		try {
			connUtil = new CanonCustAppDBUtil();
			conn = (Connection) (connUtil.getConnection());
			objStatement = conn.createStatement();

			proc = conn.prepareCall("{ call CANON_E193_CS_EVOLUTION_PKG.DEL_DM_ATTACHMENT(?, ?, ?, ?) }");
			proc.setString(1, ticketNumber);
			proc.setInt(2, iDocumentId);
			proc.setInt(3, id);
			proc.registerOutParameter(4, OracleTypes.VARCHAR);
			//System.out.println(" Inside Delete Method Before Execute Call ~ ");
			proc.execute();

			String status = proc.getString(4);

			if (status != null && status.equalsIgnoreCase("S")) {
				bDelete = true;
			}

			//System.out.println(" Inside Delete Method Before Commit Call ~ ");
			//conn.commit();
			System.out
					.println(" Delete Query executed so Is Deleted the File~ "
							+ bDelete);
		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_AttachmentUploadDAO, Method:deleteCSAFile()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_AttachmentUploadDAO, Method:deleteCSAFile()"));
		} finally {
			try {
				if (objResultSet != null)
					objResultSet.close();
				if (conn != null)
					conn.close();
				if (connUtil != null)
					connUtil.releaseConnection();
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_AttachmentUploadDAO, Method:deleteCSAFile()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_AttachmentUploadDAO, Method:deleteCSAFile()"));
			}
		}
		return bDelete;
	}
  
  
  public boolean deleteFile(String strDocumentId) throws CanonCustAppExceptionUtil
  {
		System.out.println(" Inside Delete Method~ StrDocumentId "
				+ strDocumentId);
		int iDocumentId = 0;
		boolean bDelete = false;
		CallableStatement proc = null;
		strDocumentId = strDocumentId == null ? "0" : strDocumentId;
		iDocumentId = Integer.parseInt(strDocumentId);
		//System.out.println(" Inside Delete Method~ ");
		try {
			connUtil = new CanonCustAppDBUtil();
			conn = (Connection) (connUtil.getConnection());
			objStatement = conn.createStatement();

			String v_delete_ref_Flag = "Y";
			int v_datatype_id = 6;

			proc = conn
					.prepareCall("{ call FND_DOCUMENTS_PKG.Delete_Row(?, ?, ?) }");
			proc.setInt(1, iDocumentId);
			proc.setInt(2, v_datatype_id);
			proc.setString(3, v_delete_ref_Flag);
			//System.out.println(" Inside Delete Method Before Execute Call ~ ");
			proc.execute(); 
			bDelete = true;
			//System.out.println(" Inside Delete Method Before Commit Call ~ ");
			conn.commit();
			System.out.println(" Delete Query executed so Is Deleted the File~ "
							+ bDelete);
		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_AttachmentUploadDAO, Method:deleteFile()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_AttachmentUploadDAO, Method:deleteFile()"));
		} finally {
			try {
				if (objResultSet != null)
					objResultSet.close();
				if (conn != null)
					conn.close();
				if (connUtil != null)
					connUtil.releaseConnection();
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_AttachmentUploadDAO, Method:deleteFile()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_AttachmentUploadDAO, Method:deleteFile()"));
			}
		}
		return bDelete;
  }

  
  public boolean deleteAttachedFileFromTempFolder(String strFileName) throws CanonCustAppExceptionUtil {
	 
	  boolean bDelete			= false;
	 // System.out.println(" Deleted File Name ~ " + strFileName);
	 /* String[] deleteDownloadFiles = {};*/
	 
	 // for(int i=0;i<deleteDownloadFiles.length;i++){
	  try { 
		 
		 // execute(strFileName);
		// strFileName ="/WebSphere/apps/s21/csawds/updownfiles/download/"+deleteDownloadFiles[i];
		System.out.println("FileName is : "+strFileName);
			  execute(strFileName);
	         File file = new File(strFileName);
	        // System.out.println(" File is deleted!");
	         if(file != null && file.exists()){
		         if(file.delete()) { 
		        	 bDelete = true; 
		            System.out.println(file.getName() + " is deleted!");
		         } else {
		            System.out.println("Delete operation is failed.");
		    	 }
	         }else {
	        	 System.out.println(file.getName() + " is not exist. Deletion not required.");
	         }
		
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	 // }
	  return bDelete;
  }
  public static void execute(String folderToDelete) throws IOException {
      String os = System.getProperty("os.name").toLowerCase();
      /* try{
    	  if (isWindows(os)) {
    		  System.out.println("I reached here!");
    		  Runtime.getRuntime().exec(new String[]{"chmod", "777", folderToDelete});
    	  } else if (isUnix(os) || isMac(os) || isSolaris(os)) {
    		  Runtime.getRuntime().exec("rm -r -f  " + folderToDelete);
		}
      }catch(IOException e){
    	  throw new RuntimeException(e);
      }*/
      
      if (isWindows(os)) {
    	 try {
    		Runtime.getRuntime().exec(new String[]{"chmod", "777", folderToDelete});
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
      }
  }
  public static boolean isWindows(String os) {
      return (os.contains("win"));
  }
  public static boolean isUnix(String os) {
      return (os.contains("nix") || os.contains("aix") || os.contains("nux"));
  }

  public static boolean isSolaris(String os) {
      return (os.contains("sunos"));
  }

  public static boolean isMac(String os) {
      return (os.contains("mac"));
  }
 /**
   * This getDocuments() is used to get the selected file from DB.
   */	
  public ArrayList getDocuments(int iFileId) throws CanonCustAppExceptionUtil
  {
    Canon_E193_AttachmentObj objCanonValueObject = null;
	CanonCustAppDBUtil connUtil					 = null;
	Connection conn								 = null;
    CallableStatement callStmt					 = null;
	ArrayList alDocList							 = new ArrayList();
    try 
    {
		connUtil		= new CanonCustAppDBUtil();
		conn = (Connection)(connUtil.getConnection());
		String strGetFile = "{ call canon_e193_file_load.fetch_documents(?,?) }";
		callStmt = conn.prepareCall(strGetFile);
		callStmt.setInt(1,iFileId);
		callStmt.registerOutParameter(2, OracleTypes.CURSOR);
		callStmt.execute();
		objResultSet = (ResultSet) callStmt.getObject(2);

		while(objResultSet.next()) {
		objCanonValueObject = new Canon_E193_AttachmentObj();
		objCanonValueObject.setFileContentType(objResultSet.getString("FILE_CONTENT_TYPE"));
		objCanonValueObject.setDownloadData(objResultSet.getBlob("FILE_DATA"));
		objCanonValueObject.setFileName(objResultSet.getString("FILE_NAME"));
		alDocList.add(objCanonValueObject);
		}
    }catch (CanonCustAppExceptionUtil csExp) 
	{
		throw (csExp);
	}
	catch (SQLException eSQLExp) 
	{
		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getDocuments()"));
	}
	catch (Exception eExp) 
	{
		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getDocuments()"));
	}
	finally 
	{
		try 
		{
			if(objResultSet != null) objResultSet.close();
			// Remove this comment - If we try to realease the connection here this function is not working 
			// throwing page cannot be found Error.
			//if(conn != null) conn.close();
	        //if(connUtil != null) connUtil.releaseConnection();
	    }
	    catch (SQLException eSQLExp) 
		{
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getDocuments()"));
	    }
	    catch (Exception eExp) 
		{
	     	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getDocuments()"));
	    }
	}

    return alDocList;
  }
  
  /**
   * This getFilesFromDB() is used to get the files from DB and Ticket Number for display in attachments jsp.
   */	
  public ArrayList getFilesFromDB(int iTicketNumber) throws CanonCustAppExceptionUtil
  {
    ArrayList alList							 = new ArrayList();
    Canon_E193_AttachmentObj objCanonValueObject = null;
	StringBuffer sbFiles						 = new StringBuffer();
	CanonCustAppDBUtil connUtil					 = null;
    CallableStatement callStmt					 = null;
    System.out.println("query got executed getFilesFromDB method" + iTicketNumber);
    try
    {
		connUtil			  = new CanonCustAppDBUtil();
		conn				  = (Connection)(connUtil.getConnection());
		//String strFielsFromDB = "{ call canon_e193_file_load.fetch_files(?,?) }";
		String strFielsFromDB = "{ call CANON_E193_CS_EVOLUTION_PKG.GET_DM_ATTACH_FILE_INFO(?,?) }";
		callStmt			  = conn.prepareCall(strFielsFromDB);
		callStmt.setInt(1,iTicketNumber);
		callStmt.registerOutParameter(2, OracleTypes.CURSOR);
		callStmt.execute();
		//System.out.println("query got executed getFilesFromDB method");
		objResultSet = (ResultSet) callStmt.getObject(2);

		while(objResultSet.next()) 
		{
			objCanonValueObject = new Canon_E193_AttachmentObj();
	        objCanonValueObject.setFileId(objResultSet.getInt("FILE_ID"));
		    objCanonValueObject.setFileName(objResultSet.getString("FILE_NAME"));
			objCanonValueObject.setDocumentId(objResultSet.getInt("DOCUMENT_ID"));
			objCanonValueObject.setFileType(objResultSet.getString("FILE_TYPE"));
			objCanonValueObject.setDownloadData(objResultSet.getBlob("ATTACHMENT"));
	        alList.add(objCanonValueObject);
		}	
    }
	catch (CanonCustAppExceptionUtil csExp) 
	{	System.err.println(csExp);
		throw (csExp);
	}
	catch (SQLException eSQLExp) 
	{
		System.err.println(eSQLExp);
		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	}
	catch (Exception eExp) 
	{
		System.err.println(eExp);
		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	}
	finally 
	{
		try 
		{
			if(objResultSet != null) objResultSet.close();
	        if(conn != null) conn.close();
	        if(connUtil != null) connUtil.releaseConnection();
	    }
	    catch (SQLException eSQLExp) 
		{
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	    }
	    catch (Exception eExp) 
		{
	     	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	    }
	}
    return alList;
  }
  
  public ArrayList<CanonE193AttFileRec> getFilesFromS21DB(int iTicketNumber) throws CanonCustAppExceptionUtil
  {
	  ArrayList<CanonE193AttFileRec> fileRecList = new ArrayList<CanonE193AttFileRec>();
    CanonE193AttFileRec objCanonValueObject = null;
	StringBuffer sbFiles						 = new StringBuffer();
	CanonCustAppDBUtil connUtil					 = null;
    CallableStatement callStmt					 = null;
    System.out.println(" query got executed getFilesFromDB method iTicketNumber~ " + iTicketNumber);
    try
    {
		connUtil			  = new CanonCustAppDBUtil();
		conn				  = (Connection)(connUtil.getConnection());
		//String strFielsFromDB = "{ call canon_e193_file_load.fetch_files(?,?) }";
		String strFielsFromDB = "{ call CANON_E193_CS_EVOLUTION_PKG.GET_ATTACH_FILE_INFO(?,?,?,?,?) }";
		callStmt			  = conn.prepareCall(strFielsFromDB);
		callStmt.setString(1,String.valueOf(iTicketNumber));
		callStmt.setString(2,null);
		callStmt.setString(3,null);
		callStmt.setString(4,"N"); // Modified value "Y" TO "N" Need all the uploaded file name on server
		callStmt.registerOutParameter(5, OracleTypes.ARRAY, SCH_NAME+".CANON_E193_ATT_FILE_TBL");
		callStmt.execute();
		Array canone193AttrFileTblTypArray = callStmt.getArray(5);
		//System.out.println(" Array : "+ canone193AttrFileTblTypArray.getArray());
		Object listObj[] = null;
		listObj = (Object[]) ((Array) callStmt.getObject(5)).getArray();	
		//System.out.println("query got executed getFilesFromDB method ");		
		Object[] objcanone193AttrFileTblTypArray = (Object[]) canone193AttrFileTblTypArray.getArray();
		//System.out.println(" Object Length!!! "+listObj.length);
		/*if(listObj!=null && listObj.length>0)
			  for (int k = 0; k < listObj.length; k++) {
				  if(listObj[k]!=null){
				   System.out.println(" Values!!! "+listObj[k]);
				 //  metertList.add((Canon_E193_CRMeterObj)listObj[k]);	
				  }
		         }*/
		
		int attrCountcanone193CraetePoObj=objcanone193AttrFileTblTypArray.length;
		//System.out.println(" Length AttrFileTblTypArray~ " +attrCountcanone193CraetePoObj);
		for(int i=0; i<attrCountcanone193CraetePoObj;i++){
			STRUCT structCanone193CraetePoTblTypArray = (STRUCT)objcanone193AttrFileTblTypArray[i];
			Object[] obj = structCanone193CraetePoTblTypArray.getAttributes();
			int getAttributesTillFirstTableType=0;
			             	      	            	    	           
				String strBusinessId=(String) obj[getAttributesTillFirstTableType++];     	            	
            	String strAttGrpTxt=(String) obj[getAttributesTillFirstTableType++]; 
            	BigDecimal fileId=(BigDecimal) obj[getAttributesTillFirstTableType++]; 
            	String strfileName=(String) obj[getAttributesTillFirstTableType];
            	//System.out.println(" Attached fileName~ " +strfileName);
            	CanonE193AttFileRec canonE193AttFileRec=new CanonE193AttFileRec(strBusinessId,strAttGrpTxt,fileId.intValue(),strfileName);
            	fileRecList.add(canonE193AttFileRec);
            	            
		}	
		
		
		
    }
	catch (CanonCustAppExceptionUtil csExp) 
	{	System.err.println(csExp);
		throw (csExp);
	}
	catch (SQLException eSQLExp) 
	{
		System.err.println(eSQLExp);
		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	}
	catch (Exception eExp) 
	{
		System.err.println(eExp);
		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	}
	finally 
	{
		try 
		{
			if(objResultSet != null) objResultSet.close();
	        if(conn != null) conn.close();
	        if(connUtil != null) connUtil.releaseConnection();
	    }
	    catch (SQLException eSQLExp) 
		{
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	    }
	    catch (Exception eExp) 
		{
	     	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	    }
	}
    System.out.println(" FileRecList Size~ " + fileRecList.size());
    return fileRecList;
  }
  
  
  public ArrayList<CanonE193AttFileRec> getCSAFilesFromS21DB(int iTicketNumber) throws CanonCustAppExceptionUtil
  {
	  ArrayList<CanonE193AttFileRec> fileRecList = new ArrayList<CanonE193AttFileRec>();
	  CanonE193AttFileRec canonE193AttFileRec = null;
	  CanonCustAppDBUtil connUtil = null;
	  CallableStatement callStmt = null;
	  ResultSet resultSet = null;
	  System.out.println(" In getCSAFilesFromS21DB method iTicketNumber~ " + iTicketNumber);
	  try
	  {
		  connUtil = new CanonCustAppDBUtil();
		  conn = (Connection)(connUtil.getConnection());
		  String strFielsFromDB = "{ call CANON_E193_CS_EVOLUTION_PKG.GET_DM_ATTACHMENT(?,?) }";
		  callStmt = conn.prepareCall(strFielsFromDB);
		  callStmt.setString(1,String.valueOf(iTicketNumber));
		  callStmt.registerOutParameter(2, OracleTypes.CURSOR);
		  callStmt.execute();
		  resultSet = (ResultSet) callStmt.getObject(2);
		  String strfileName = "", fileNameWithPath = "";
		  Integer fileId = null;
		  Integer id = 0;
		  while (resultSet.next())
		  {	
			  fileId = resultSet.getInt("DOCUMENT_ID");
			  fileNameWithPath = resultSet.getString("FILE_PATH");
			  id = resultSet.getInt("ID");
			  strfileName = CanonUploadUtil.getFileName(fileNameWithPath);
			  canonE193AttFileRec=new CanonE193AttFileRec(String.valueOf(iTicketNumber), "" ,
					  fileId.intValue(), strfileName, fileNameWithPath, id);
			  fileRecList.add(canonE193AttFileRec);
		  }
		
    	/*canonE193AttFileRec = new CanonE193AttFileRec("2002333","777",1, 
    			"E503_20170911152645525_@Q08693_20170911152645548_@2002333.xlsx", 
    			"/WebSphere/apps//filebox/attachment/2002333/20170911/E503_20170911152645525_@Q08693_20170911152645548_@2002333.xlsx");
    	fileRecList.add(canonE193AttFileRec);*/
    }
    catch (CanonCustAppExceptionUtil csExp) 
	{	System.err.println(csExp);
		throw (csExp);
	}
	catch (SQLException eSQLExp) 
	{
		System.err.println(eSQLExp);
		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	}
	catch (Exception eExp) 
	{
		System.err.println(eExp);
		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	}
	finally 
	{
		try 
		{
			if(resultSet != null) resultSet.close();
			if(callStmt != null) callStmt.close();
	        if(conn != null) conn.close();
	        if(connUtil != null) connUtil.releaseConnection();
	    }
	    catch (SQLException eSQLExp) 
		{
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	    }
	    catch (Exception eExp) 
		{
	     	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AttachmentUploadDAO, Method:getFilesFromDB()"));
	    }
	}
    System.out.println(" FileRecList Size~ " + fileRecList.size());
    return fileRecList;
  }
  
  
  
  public String getUploadFilePath(){
		
		String res="";
		ResultSet rs = null;
		CallableStatement cstmt = null;
		
		 CanonCustAppDBUtil connAttachUtil = null;
		 Connection connection = null;
			
		try {
			//connection = TransactionScope.getConnection();
			connection = (Connection)(connAttachUtil.getConnection());
			//cstmt = (CallableStatement)connection.prepareCall("{CALL CANON_E193_CS_EVOLUTION_PKG.GET_FILE_UPLOAD_PATH(?)}");
			cstmt = connection.prepareCall("{CALL CANON_E193_CS_EVOLUTION_PKG.GET_FILE_UPLOAD_PATH(?)}");
			
			
			cstmt.registerOutParameter(1, OracleTypes.VARCHAR);
		    cstmt.execute();
		    res= cstmt.getString(1);
		} catch (Exception ex1) {
			util.logMsg(true,clsName+".getUploadFilePath ", ex1.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
				if (connection != null)
					TransactionScope.releaseConnection(connection);
			} catch (Exception ex2) {
				util.logMsg(true,clsName+".getUploadFilePath", ex2.getMessage());
			}
		}
		return res;
	}
  
}