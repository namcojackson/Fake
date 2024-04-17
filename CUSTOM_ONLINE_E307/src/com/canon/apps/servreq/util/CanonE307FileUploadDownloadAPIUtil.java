package com.canon.apps.servreq.util;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDBizPerformanceCounter;
import parts.common.EZDStringUtil;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;
import business.parts.NYZC001001PMsg;
import business.parts.NYZC002001PMsg;

import com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao;
import com.canon.common.CanonCommonUtil;
import com.canon.cusa.s21.api.NYZ.NYZC001001.NYZC001001;
import com.canon.cusa.s21.api.NYZ.NYZC002001.NYZC002001;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

public class CanonE307FileUploadDownloadAPIUtil {

    CanonCommonUtil util; 
	StringBuffer sbParams;
	private   String clsName="CanonE307FileUploadDownloadAPIUtil";
	public CanonE307FileUploadDownloadAPIUtil() {
		util= new CanonCommonUtil();
	}
	
	public String[] uploadAttachment(String strFsrNum, String strSerNum, String userName, String strFileName){
			util.logMsg(false, clsName+".uploadAttachment", "strFsrNum : "+ strFsrNum+" strSerNum : "+strSerNum+" userName : "+userName+" strFileName : "+strFileName);
			String csaCompanyCode = "ADB";
			SimpleDateFormat format = new SimpleDateFormat("z");
	        SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	        String timezone = format.format(new Date());
	        String invokeTimestamp = lsDateFmt.format(new Date());
	        EZDDBCICarrier.initOnline(userName, invokeTimestamp, timezone, csaCompanyCode);
	        EZDDBCICarrier.setProgID("EXTNE307");
			NYZC001001 nyzc001001 = new NYZC001001();
			strFileName = strFileName==null?"":strFileName.trim();
		//	strFileName = "ARC @ Brandt Engineering TDS750 775001596.pdf";
			NYZC001001PMsg pmsg = new NYZC001001PMsg();
			int fileId = 0;
			String res[] = new String[2];
		  	pmsg.ezBusinessID.setValue(strFsrNum); // Grouping key1 for this uploaded file
	    	pmsg.attDataGrpTxt.setValue(strSerNum); // Grouping key2 for this uploaded file
        	pmsg.attDataNm.setValue(strFileName);
	        util.logMsg(false, clsName+".uploadAttachment", "Before uploading file path");
	        //String folderPath = "///WebSphere/apps/s21/csawds/updownfiles/upload/";
	   	    CanonE307ServiceRequestDetailsDao detObj = new CanonE307ServiceRequestDetailsDao();
		    String folderPath = detObj.getUploadFilePath();
	        String filePath = "";
	        if("Credit Card Payment instead of PO.pdf".equals(strFileName)){
		        filePath = "/WebSphere/apps/filebox/dummyPO/Credit Card Payment instead of PO.pdf";
	        }else{
	        	filePath = folderPath+strFileName;
	        }
			
	        util.logMsg(false, clsName+".uploadAttachment", "File Path : " + filePath);
	        
	        pmsg.xxFilePathTxt.setValue(filePath);
	        
			String timeRegion="EST";
			String appId="EXTNE307";
			String pageId="UploadAttachment";
			String appPageId="EXTNE307_Upload";
			String fileExists = "N";
			
			File file = new File(filePath);
			
			if(file.exists()){
				fileExists="Y";
			} 
			
			if("Y".equals(fileExists)){
			
		/*	CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
			EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userName,timeRegion,appId,pageId,appId);
			bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/
			
	        // Invoke S21API(NYZC001001)
	        pmsg = nyzc001001.execute(pmsg, ONBATCH_TYPE.ONLINE);
	        
	        util.logMsg(false, clsName+".uploadAttachment", "After attachment API call");
		/*	bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
			bizPerfCounter.requestQueue(); */
	        
	    	String[] strRes = new String[2];

	        try {
	            // Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
	            if (!S21ApiUtil.isXxMsgId(pmsg)) {
	                // There is no message id, so can do commit the transaction.
	                
	                System.out.println("FileID : " + pmsg.attDataPk.getValueInt()); // fileID <- output from S21API(NYZC001001)
	                res[0] = "Y";
	                res[1] =String.valueOf(pmsg.attDataPk.getValueInt());
	                util.logMsg(false, clsName+".uploadAttachment", "FileID : " + pmsg.attDataPk.getValueInt());
	                
	                EZDConnectionMgr.getInstance().commit(); // commit
	               if("Credit Card Payment instead of PO.pdf".equals(strFileName)){
	                	// Do Nothing
	                }else{
		    			//File file = new File(filePath);
		    			System.out.println("filePath: "+ filePath);
		    			if(file.exists()){
		    				//System.out.println("Inside file exists");
		    				file.delete();
		    			}
	                }
	            } else {
	                // Error Case - S21API set some error message id when got any error while S21API's function.
	                List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
	                StringBuffer sb=new StringBuffer("");
	                for (String msg: err) {
	                	 util.logMsg(false, clsName+".uploadAttachment", "ERROR MESSAGE : " + msg);
	                	 util.logMsg(false, clsName+".uploadAttachment",  S21MessageFunc.clspGetMessage(msg) );
	                	 sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
	                }
	                res[0] = "E";
	                res[1]=sb.toString();
	                util.logMsg(false, clsName+".uploadAttachment", "errors: "+sb.toString());
	                // If S21API got error, roll-back the transaction.
	                EZDConnectionMgr.getInstance().rollback(); // roll-back
	                // throw new S21AbendException("S21AbendException is thrown...");
	                if("Credit Card Payment instead of PO.pdf".equals(strFileName)){
	                	// Do Nothing
	                }else{
		    			//File file = new File(filePath);
		    			System.out.println("filePath: "+ filePath);
		    			if(file.exists()){
		    				//System.out.println("Inside file exists");
		    				file.delete();
		    			}
	                }
	            }
	        } catch (Exception e) {
				res[0]="E";
				
				try{
					String str = e.getMessage();
					Pattern p = Pattern.compile("msg=.*");

					Matcher m = p.matcher(str);

					if (m.find()) {
						res[1] =  m.group(0).trim();
					}else{
						res[1] = e.getMessage();
					}
					util.logMsg(true,clsName+".createServicerequest", "Exception message while uploading .pdf: "+ res[1]);	
				}catch (Exception ex) {
					util.logMsg(true,clsName+".createServicerequest", "Error while parsing .pdf error message: "+ e.getMessage());
				}
				EZDConnectionMgr.getInstance().rollback();
				
	        } finally {
	            // Release DB resource (Close DB Connection)
	            EZDConnectionMgr.getInstance().releaseResource();
	        }
			}else{
				res[0]="E";
				res[1]="Not able to upload the "+ strFileName+" . Please try again.";
			}
	return res;
	}
	public String downLoadAttachment(String strFsrNum, String strSerNum, String userName, int fileId){
		util.logMsg(false, clsName+".downLoadAttachment", "strFsrNum : "+ strFsrNum+" strSerNum : "+strSerNum+" userName : "+userName+" fileId : "+fileId);
        NYZC002001 nyzc002001 = new NYZC002001();
        String fileName ="";
        // Setting input param bean
        NYZC002001PMsg pmsg = new NYZC002001PMsg();
        pmsg.attDataPk.setValue(fileId); // FileID <- return value from NYZC001001(S21API)

		String timeRegion="EST";
		String appId="EXTNE307";
		String pageId="DownloadAttachment";
		String appPageId="EXTNE307_Download";
		
	/*	CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
		EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userName,timeRegion,appId,pageId,appId);
		bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/
        
        // Invoke S21API(NYZC002001)
        pmsg = nyzc002001.execute(pmsg, ONBATCH_TYPE.ONLINE);
        
/*		bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
		bizPerfCounter.requestQueue();        */

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
}
