package com.canon.oracle.custapp.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;
import business.parts.NYZC001001PMsg;
import business.parts.NYZC002001PMsg;
import business.parts.NYZC003001PMsg;

import com.canon.common.CanonCommonUtil;
import com.canon.cusa.s21.api.NYZ.NYZC001001.NYZC001001;
import com.canon.cusa.s21.api.NYZ.NYZC002001.NYZC002001;
import com.canon.cusa.s21.api.NYZ.NYZC003001.NYZC003001;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.oracle.custapp.custinq.dao.Canon_E193_AttachmentUploadDAO;

// Referenced classes of package com.canon.oracle.custapp.custinq.dao:
//            Canon_E193_AttachmentUploadDAO

public class CanonE193FileUploadDownloadAPIUtil {

    CanonCommonUtil util; 
	StringBuffer sbParams;
	private   String clsName="CanonE193FileUploadDownloadAPIUtil";
	public CanonE193FileUploadDownloadAPIUtil() {
		util= new CanonCommonUtil();
	}
	
	public int uploadAttachment(String strTktNum, String strSerNum, String userName, String strFileName){
		String csaCompanyCode = "ADB";
		SimpleDateFormat format = new SimpleDateFormat("z");
        SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timezone = format.format(new Date());
        String invokeTimestamp = lsDateFmt.format(new Date());
		util.logMsg(false, clsName+".uploadAttachment", "Before EZDDBCICarrier.initOnline");
        EZDDBCICarrier.initOnline(userName, invokeTimestamp, timezone, csaCompanyCode);
        EZDDBCICarrier.setProgID("EXTN_E193");	
		util.logMsg(false, clsName+".uploadAttachment", "strTktNum : "+ strTktNum+" strSerNum : "+strSerNum+" userName : "+userName+" strFileName : "+strFileName);			
	        
			NYZC001001 nyzc001001 = new NYZC001001();			
			NYZC001001PMsg pmsg = new NYZC001001PMsg();
			int fileId = 0;
		  	pmsg.ezBusinessID.setValue(strTktNum); // Grouping key1 for this uploaded file
	    	pmsg.attDataGrpTxt.setValue(strSerNum); // Grouping key2 for this uploaded file
	        pmsg.attDataNm.setValue(strFileName);
	        util.logMsg(false, clsName+".uploadAttachment", "Before uploading file path");
	        // String path = "/e307/jsp/ADVC5000_0006JAM.pdf";
	        //String folderPath = "///WebSphere/apps/s21/csawds/updownfiles/upload/";
	        Canon_E193_AttachmentUploadDAO detObj = new Canon_E193_AttachmentUploadDAO();
		    String folderPath = "///WebSphere/apps/s21/csawds/updownfiles/upload/";//detObj.getUploadFilePath();
	        String filePath = folderPath+strFileName;
			
	        util.logMsg(false, clsName+".uploadAttachment", "File Path : " + filePath);
	        
	        pmsg.xxFilePathTxt.setValue(filePath);
			
	        // Invoke S21API(NYZC001001)
	        pmsg = nyzc001001.execute(pmsg, ONBATCH_TYPE.ONLINE);
	        
	        util.logMsg(false, clsName+".uploadAttachment", "After attachment API call");
	        
	    	String[] strRes = new String[2];


	        try {
	            // Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
	            if (!S21ApiUtil.isXxMsgId(pmsg)) {
	                // There is no message id, so can do commit the transaction.
	                
	                System.out.println("FileID : " + pmsg.attDataPk.getValueInt()); // fileID <- output from S21API(NYZC001001)
	                
	                fileId =pmsg.attDataPk.getValueInt();
	                util.logMsg(false, clsName+".uploadAttachment", "FileID : " + pmsg.attDataPk.getValueInt());
	                
	                EZDConnectionMgr.getInstance().commit(); // commit
	            } else {
	                // Error Case - S21API set some error message id when got any error while S21API's function.
	                List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
	                for (String msg: err) {
	                	 util.logMsg(false, clsName+".uploadAttachment", "ERROR MESSAGE : " + msg);
	                	 util.logMsg(false, clsName+".uploadAttachment",  S21MessageFunc.clspGetMessage(msg) );
	                }
	                // If S21API got error, roll-back the transaction.
	                EZDConnectionMgr.getInstance().rollback(); // roll-back
	                // throw new S21AbendException("S21AbendException is thrown...");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Release DB resource (Close DB Connection)
	            EZDConnectionMgr.getInstance().releaseResource();
	        }
	return fileId;
	}
	public String downLoadAttachment(String strTktNum, String strSerNum, String userName, int fileId){
		util.logMsg(false, clsName+".downLoadAttachment", "strTktNum : "+ strTktNum+" strSerNum : "+strSerNum+" userName : "+userName+" fileId : "+fileId);
        NYZC002001 nyzc002001 = new NYZC002001();
        String fileName ="";
        // Setting input param bean
        NYZC002001PMsg pmsg = new NYZC002001PMsg();
        pmsg.attDataPk.setValue(fileId); // FileID <- return value from NYZC001001(S21API)

        // Invoke S21API(NYZC002001)
        pmsg = nyzc002001.execute(pmsg, ONBATCH_TYPE.ONLINE);

        try {
            // Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
            if (!S21ApiUtil.isXxMsgId(pmsg)) {
                // There is no message id, so can do commit the transaction.
                System.out.println("File path : " + pmsg.xxFilePathTxt.getValue()); // file path in temporary folder on AP/Batch server <- output from S21API(NYZC002001)
                fileName=pmsg.xxFilePathTxt.getValue();

                util.logMsg(false, clsName+".downLoadAttachment", " File path : " + pmsg.xxFilePathTxt.getValue() );
                
                EZDConnectionMgr.getInstance().commit(); // commit
            } else {
                // Error Case - S21API set some error message id when got any error while S21API's function.
                List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
                for (String msg: err) {
                    System.out.println("error msg: " + msg);
                    System.out.println(S21MessageFunc.clspGetMessage(msg));
                }
                // If S21API got error, roll-back the transaction.
                EZDConnectionMgr.getInstance().rollback(); // roll-back
                // throw new S21AbendException("S21AbendException is thrown...");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Release DB resource (Close DB Connection)
            EZDConnectionMgr.getInstance().releaseResource();
        }
        return fileName;
	}
	
	
	public static boolean detachAttachment(String documentId)
	{
        // uploaded attachment pk (delete target attachment file id uploaded)
        //int attDataPk = 39960;
        boolean isDeleted = false;
		if(documentId != null && !documentId.trim().isEmpty() && !documentId.equals("0"))
		{
			String csaCompanyCode = "ADB";
			SimpleDateFormat format = new SimpleDateFormat("z");
	        SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	        String timezone = format.format(new Date());
	        String invokeTimestamp = lsDateFmt.format(new Date());
	        EZDDBCICarrier.initOnline("userName", invokeTimestamp, timezone, csaCompanyCode);
	        EZDDBCICarrier.setProgID("EXTN_E193");
	        
	        // Create S21API instance (NYZC003001)
			NYZC003001 nyzc003001 = new NYZC003001();
	
			// Setting input param bean
			NYZC003001PMsg pmsg = new NYZC003001PMsg();
			
			//pmsg.glblCmpyCd.setValue("ADB");		
			pmsg.attDataPk.setValue(new BigDecimal(documentId));
	
			nyzc003001.execute(pmsg, ONBATCH_TYPE.ONLINE);
			try {
				// Normal Case. (No error msg)
				if (!S21ApiUtil.isXxMsgId(pmsg)) {
					System.out.println("File Nm : " + pmsg.attDataNm.getValue() );
					EZDConnectionMgr.getInstance().commit(); // commit
					isDeleted = true;
				} else {
					// Error Case - S21API set some error message id when got any error while S21API's function.
					List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
					for (String msg : err) {
						System.out.println("error msg: " + msg);
						System.out.println(S21MessageFunc.clspGetMessage(msg));
					}
					// If S21API got error, roll-back the transaction.
					EZDConnectionMgr.getInstance().rollback(); // roll-back
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// Release DB resource (Close DB Connection)
				EZDConnectionMgr.getInstance().releaseResource();
			}
		}
		return isDeleted;
	}
	
	
}