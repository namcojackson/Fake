package oracle.apps.e580.server;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.canon.common.CanonCommonUtil;
import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.api.NPZ.NPZC104001.NPZC104001;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.parts.NPZC004001PMsg;
import business.parts.NPZC104001PMsg;
import business.parts.NPZC104001_poLineInfoPMsg;
import canon.apps.common.CanonConstants;
import parts.common.EZDPBigDecimalItem;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;


public class CanonITTWorkBenchS21API {

	
    String  MODE_CODE_SR_CREATE="01";
    String  MODE_CODE_SR_UPDATE="02";
    CanonCommonUtil util;         
    private int logMsgindent =0; 

		public CanonITTWorkBenchS21API(){
		util= new CanonCommonUtil();
		}

	public NPZC104001PMsg  getMsg() {
		NPZC104001PMsg  pmsg = new NPZC104001PMsg();			
		return pmsg;

	}
	
	public boolean doPOStatusUpdate(NPZC104001PMsg poCreateMsg) {
		if (poCreateMsg.poLineInfo.getValidCount() > 0) {
			for (int i = 0; i < poCreateMsg.poLineInfo.getValidCount(); i++) {
				logMsgindent++;
				NPZC104001_poLineInfoPMsg line = poCreateMsg.poLineInfo.no(i);
				NPZC004001PMsg msg2 = new NPZC004001PMsg();

				msg2.glblCmpyCd.setValue(poCreateMsg.glblCmpyCd.getValue());
				msg2.poOrdNum.setValue(poCreateMsg.poOrdNum.getValue());
				msg2.poStsCd.setValue("06");
				msg2.mdseCd.setValue(line.mdseCd.getValue());
				msg2.poOrdDtlLineNum.setValue(line.poOrdDtlLineNum.getValue());
				// Here we need to reset MDSE_CD of original line to null;
				line.mdseCd.clear();
				boolean success = executePOStatusUpdate(msg2);
				logMsgindent--;
				if (!success) {
					return false;
				}
			}

		}
		return true;
	}

	public boolean executePOStatusUpdate(NPZC004001PMsg pmsg) {
		NPZC004001 s21Api = new NPZC004001();
		try {
			logMsg("NPZC004001 Before execute");
			s21Api.execute(pmsg, ONBATCH_TYPE.BATCH); // execute API
			logMsg("NPZC004001 After execute");
			// Normal Case. (No error msg) - S21API set some error message id
			// when got any error while S21API's function.
			if (!S21ApiUtil.isXxMsgId(pmsg)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			logMsg("ERROR MESSAGE : " + e.getMessage());
			e.printStackTrace();
			return false;
		}

	}
	
	public String[] cancelLines(String p_order_number) {
		
		SimpleDateFormat format = new SimpleDateFormat("z");		
		SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		String timezone = format.format(new Date());
		String invokeTimestamp = lsDateFmt.format(new Date()); 
		String loginUser= "Q05058"; //TODO		

		
		//Initialize S21DB-Carrier (It should be done. It leads NullPointer exception when didn't initialize.)
		//These contents are used as a default data in S21-Standard table. (Update user, time, time-zone, program id, company code)
		EZDDBCICarrier.initBatch(loginUser, invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
		
//		EZDDBCICarrier.setProgID("S21EXTN_E580");

		String[] resArr = new String[4];

		NPZC104001 s21Api=new NPZC104001();
		
		NPZC104001PMsg pmsg = null;
		
		
		
//		 String p_itt_number = request.getParameter("itt_number");	
		 Object[] cancelPoPrcResult=CanonITTWorkbenchDAO.cancelPoPrc(p_order_number);
		List<NPZC104001PMsg> pmsgListFromDao=(List<NPZC104001PMsg>)cancelPoPrcResult[0];
		logMsg("pmsgListFromDao size:"+pmsgListFromDao.size());
		boolean [] poCreatedFlag=null;
		String [] createdPoNumber=null;
		boolean partialFailure=false;
		
		
		//String dealer_code = request.getParameter("dealer_code_id");
		
		logMsg("Before execute  ");
		
		try {
			if(!pmsgListFromDao.isEmpty())
			{
				poCreatedFlag=new boolean[pmsgListFromDao.size()];
				createdPoNumber=new String[pmsgListFromDao.size()];
			int count=-1;
			for(NPZC104001PMsg pmsgFromDao:pmsgListFromDao)
			{	
				count++;
				pmsg=getMsg();
			pmsg=pmsgFromDao;
			printInputParm(pmsg);
			logMsg("cancelPoPrc returns poLineInfo count:"+pmsg.poLineInfo.getValidCount());
			logMsg("cancelPoPrc returns "+pmsg.xxModeCd.getValue());
	        // call NPZC0040 PO Status Update first
	        boolean success=doPOStatusUpdate(pmsg);
	        if(success) {
		        // then, call NPZC1040 PO Create API
	        	s21Api.execute(pmsg,ONBATCH_TYPE.BATCH); // execute PO Create API
	        	logMsg("Cancel PO API called");
	        	success=!S21ApiUtil.isXxMsgId(pmsg);
	        }
	        
			if (success) {
				// There is no message id, so can do commit the transaction.
				logMsg(" No error, before commit");
				EZDConnectionMgr.getInstance().commit(); 
				// commit	
				logMsg("After commit"); 
				resArr[0] = "";	
//				CanonITTWorkbenchServerDAO.updatePoItt(p_itt_number,pmsg.poOrdNum.getValue(),"",loginUser);
				resArr[2]="Cancel PO success! PO Number(s) ";
				poCreatedFlag[count]=true;
				createdPoNumber[count]=pmsg.poOrdNum.getValue();				
				//resArr[2]=resArr[2]+pmsg.poOrdNum.getValue()+",";
				logMsg("Created PO number is:"+pmsg.poOrdNum.getValue());
				
			}else {
				StringBuffer sb = new StringBuffer("");
				// Error Case - S21API set some error message id when got any
				// error while S21API's function.
				List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
				String s21ApiError="";
				for (String msg : err) {
					s21ApiError=s21ApiError+S21MessageFunc.clspGetMessage(msg);
					logMsg("ERROR MESSAGE : "
							+ S21MessageFunc.clspGetMessage(msg));					
					sb.append("ERROR : " + "\n");					
				}
				resArr[0] = "ERROR : ";
				resArr[1] = sb.toString();
				resArr[2] = "Error returned from S21 API while creating the Purchase Order";
				// If S21API got error, roll-back the transaction.
				EZDConnectionMgr.getInstance().rollback();
				// roll-back
				// throw new
				// S21AbendException("S21AbendException is thrown...");
				String p_err_msg="";
				
//				if(!pmsg.vndCd.getValue().equalsIgnoreCase(dealer_code))
//					p_err_msg="CUSA PO failed to create. Error Message:"+s21ApiError;
//				else
//					p_err_msg="DEALER PO failed to create. Error Message:"+s21ApiError;
				
//				CanonITTWorkbenchServerDAO.updatePoItt(p_itt_number,"",p_err_msg,loginUser);				
				break;
			}
			}
			
			
			if(!poCreatedFlag[0])
			{
				resArr[0] = "";
				resArr[1] = "";
				resArr[2]="Cancel PO failed! Please retry this action after the API errors are fixed";		
			}
			else
			{
				for(int i=1;i<poCreatedFlag.length;i++)
				{
					if(!poCreatedFlag[i])
					{
						partialFailure=true;
						resArr[2]="Cancel PO success partially! PO Number(s) ";
						break;
					}
				}
				
				for(int i=0;i<poCreatedFlag.length;i++)
				{
					if(poCreatedFlag[i])
						resArr[2]=resArr[2]+createdPoNumber[i];
					if(i+1!=poCreatedFlag.length)
						resArr[2]=resArr[2]+",";
					if(i+1==poCreatedFlag.length)
						resArr[2]=resArr[2]+" created.";
					if(i+1==poCreatedFlag.length && partialFailure)
						resArr[2]=resArr[2]+" For failed PO\'s errors, please refer to ITT error table.";
				}
				
			}
			
			}
			else
			{
				resArr[0] = "Cancel PO failed with following error message:";
				resArr[1] = "";
				resArr[2] = (String)cancelPoPrcResult[2];
				logMsg("There is no eligible item to process:");	
			}
			
			
		} catch (Exception e) {
			logMsg("ERROR MESSAGE : " + e.getMessage());
			resArr[0] = "ERROR : ";
			resArr[1] = "";
			resArr[2] = "Cancel PO failed! Please retry this action after API errors are fixed";
//			CanonITTWorkbenchServerDAO.updatePoItt(p_itt_number,"",e.getMessage(),loginUser);	
			e.printStackTrace();
		} finally { // Release DB resource (Close DB Connection)
			EZDConnectionMgr.getInstance().releaseResource();
		}
		
		
		
		return resArr;
	}

	private void printInputParm(NPZC104001PMsg pmsgValuesFromTables) {
		
		logMsg("S21 APi name:"+"NPZC104001");
		logMsg("Input parameters begin {");
		 checkNull("glblCmpyCd",	        	pmsgValuesFromTables.glblCmpyCd.getValue());	        	            	
		 checkNull("xxModeCd",	        	pmsgValuesFromTables.xxModeCd.getValue());	        	            	
         checkNull("eventId",	           pmsgValuesFromTables.eventId.getValue());	             	           
         checkNull("procDt",pmsgValuesFromTables.procDt.getValue());
         checkNull("xxRqstTs",pmsgValuesFromTables.xxRqstTs.getValue());
         checkNull("poOrdNum",	           pmsgValuesFromTables.poOrdNum.getValue());	             	            
         
         if(pmsgValuesFromTables.poInfo.getValidCount()>0) {
        	 logMsgindent++;
        	 logMsg("poInfo: Header Message Information begin {");
        	 for(int canonE580PohMsgTlbCount=0;canonE580PohMsgTlbCount<pmsgValuesFromTables.poInfo.getValidCount();canonE580PohMsgTlbCount++)
	           {
        	 	checkNullBigDecimal("poMsgPk",pmsgValuesFromTables.poInfo.no(canonE580PohMsgTlbCount).poMsgPk);
        		checkNull("poMsgTpCd",pmsgValuesFromTables.poInfo.no(canonE580PohMsgTlbCount).poMsgTpCd.getValue());
        		checkNull("poMsgSubmtPsnCd",pmsgValuesFromTables.poInfo.no(canonE580PohMsgTlbCount).poMsgSubmtPsnCd.getValue());
        		checkNull("xxDsMultMsgDplyTxt",pmsgValuesFromTables.poInfo.no(canonE580PohMsgTlbCount).xxDsMultMsgDplyTxt.getValue());
        		checkNull("prchReqNum",pmsgValuesFromTables.poInfo.no(canonE580PohMsgTlbCount).prchReqNum.getValue());
        		checkNull("prchReqLineNum",pmsgValuesFromTables.poInfo.no(canonE580PohMsgTlbCount).prchReqLineNum.getValue());
        		checkNullBigDecimal("prchReqLineSubNum",pmsgValuesFromTables.poInfo.no(canonE580PohMsgTlbCount).prchReqLineSubNum);	        		       	     	        	 
	           }
        	 logMsg("poInfo: Header Message Information end }");
        	 logMsgindent--;
         }
         if(pmsgValuesFromTables.poLineInfo.getValidCount()>0) {
        	 logMsgindent++;
        	 logMsg("poLineInfo:  Line Information begin {");
        	 for(int canonE580PohMsgTlbCount=0;canonE580PohMsgTlbCount<pmsgValuesFromTables.poLineInfo.getValidCount();canonE580PohMsgTlbCount++)
	           {
        	 checkNull("poOrdDtlLineNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).poOrdDtlLineNum.getValue());
	           }
        	 logMsg("poLineInfo:  Line Information end }");
        	 logMsgindent--;
         }
         if(pmsgValuesFromTables.poAcctInfo.getValidCount()>0) {
        	 logMsgindent++;
        	 logMsg("Account Information begin {");
        	 for(int canonE580PoaTlbCount=0;canonE580PoaTlbCount<pmsgValuesFromTables.poAcctInfo.getValidCount();canonE580PoaTlbCount++)
	           {	       	     	           
			checkNull("poOrdDtlLineNum",pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTlbCount).poOrdDtlLineNum.getValue());
			checkNull("poAcctTpCd",pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTlbCount).poAcctTpCd.getValue());
			checkNull("coaCmpyCd",pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTlbCount).coaCmpyCd.getValue());
			checkNull("coaAfflCd",pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTlbCount).coaAfflCd.getValue());
			checkNull("coaBrCd",pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTlbCount).coaBrCd.getValue());
			checkNull("coaChCd",pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTlbCount).coaChCd.getValue());
			checkNull("coaCcCd",pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTlbCount).coaCcCd.getValue());
			checkNull("coaAcctCd",pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTlbCount).coaAcctCd.getValue());
			checkNull("coaProdCd",pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTlbCount).coaProdCd.getValue());
			checkNull("coaProjCd",pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTlbCount).coaProjCd.getValue());
			checkNull("coaExtnCd",pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTlbCount).coaExtnCd.getValue());
	           }
        	 logMsg("Account Information end } ");
        	 logMsgindent--;
         }
         if(pmsgValuesFromTables.serNumList.getValidCount()>0) {
        	 logMsgindent++;
        	 logMsg("Serial Information begin { ");
        	 for(int canonE580PosTlbCount=0;canonE580PosTlbCount<pmsgValuesFromTables.serNumList.getValidCount();canonE580PosTlbCount++)
	           {
        	 checkNull("poOrdDtlLineNum",pmsgValuesFromTables.serNumList.no(canonE580PosTlbCount).poOrdDtlLineNum.getValue());
        	 checkNullBigDecimal("poSerNumPk",pmsgValuesFromTables.serNumList.no(canonE580PosTlbCount).poSerNumPk);
        	 checkNull("serNum",pmsgValuesFromTables.serNumList.no(canonE580PosTlbCount).serNum.getValue());		
	           }
        	 logMsg("Serial Information end } ");
        	 logMsgindent--;
         }
         
 		logMsg("Input parameters end }");
         
         
	}
	
	
	

	
	private void checkNull(String str, String value) {

		String tmp = value;
		if("".equalsIgnoreCase(tmp))
		tmp=null;
			logMsg(str + ": " + tmp);
		}
	
	private void checkNullBigDecimal(String str, EZDPBigDecimalItem value) {

		BigDecimal tmp =null;
		
		if(value==null)		
		tmp=null;
		else
			tmp=value.getValue();
			logMsg(str + ": " + tmp);
		}

	final String tabSpace="    ";
	public void logMsg(String str) {
		String space="";
		for(int i=0;i<logMsgindent;i++) {
			space+=tabSpace;
		}
		System.out.println(space+str);
	}
}
