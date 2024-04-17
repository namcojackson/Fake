package oracle.apps.e580.itt.workbench;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import parts.common.EZDPBigDecimalItem;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;
import business.parts.NPZC104001PMsg;
import canon.apps.common.CanonConstants;
import canon.apps.common.CanonS21SessionValidate;

import com.canon.common.CanonCommonUtil;
import com.canon.cusa.s21.api.NPZ.NPZC104001.NPZC104001;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;


public class CanonE580CreatePOS21Api {

	
	private   String clsName="CanonE580CreatePOS21Api";
    String  MODE_CODE_SR_CREATE="01";
    String  MODE_CODE_SR_UPDATE="02";
    CanonCommonUtil util;         


		public CanonE580CreatePOS21Api(){
		util= new CanonCommonUtil();
		}
	
	public String checkNull(HttpServletRequest req, String str) {
		String s = "";

		String tmp = req.getParameter(str);
		if (tmp != null) {
			logMsg(str + ": " + tmp);
			if (tmp.trim().length() > 0) {
				s = tmp.trim();
			}

		}
		return s;

	}

	public BigDecimal checkNull(HttpServletRequest req, BigDecimal str) {
		BigDecimal s = null;

		if (req.getParameter(str.toString()) != null) {
			BigDecimal tmp = new BigDecimal(req.getParameter(str.toString()));
			s = tmp;
		} else
			s = BigDecimal.ZERO;
		return s;

	}

	public NPZC104001PMsg  getMsg(HttpServletRequest request) {
		NPZC104001PMsg  pmsg = new NPZC104001PMsg();			
		return pmsg;

	}
	
	public String[] createServicerequest(HttpServletRequest request) {
		
		SimpleDateFormat format = new SimpleDateFormat("z");		
		SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		String timezone = format.format(new Date());
		String invokeTimestamp = lsDateFmt.format(new Date()); 
		String loginUser= CanonS21SessionValidate.getUserName();		

		
		//Initialize S21DB-Carrier (It should be done. It leads NullPointer exception when didn't initialize.)
		//These contents are used as a default data in S21-Standard table. (Update user, time, time-zone, program id, company code)
		EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
		EZDDBCICarrier.setProgID("S21EXTN_E580");

		String[] resArr = new String[4];

		NPZC104001 s21Api=new NPZC104001();
		NPZC104001PMsg pmsg = null;
		
		
		
		String p_itt_number = request.getParameter("itt_number");	
		String called_from = request.getParameter("create_po_called_from");
		String cusa_po_num = request.getParameter("cusa_po_num");
		String dealer_po_num = request.getParameter("dealer_po_num");
		
		boolean isCreatePO="C".equals(called_from);
		 
		Object[] createPoPrcResult=CanonE580ITTWorkbenchDAO.createPoPrc(p_itt_number, loginUser, called_from, cusa_po_num, dealer_po_num);
		List<NPZC104001PMsg> pmsgListFromDao=(List<NPZC104001PMsg>)createPoPrcResult[0];
		System.out.println("pmsgListFromDao size:"+pmsgListFromDao.size());
		boolean [] poCreatedFlag=null;
		String [] createdPoNumber=null;
		boolean partialFailure=false;
		
		
		String dealer_code = request.getParameter("dealer_code_id");
		
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
				pmsg=getMsg(request);
			pmsg=pmsgFromDao;
			printInputParm(pmsg);
			System.out.println("createPoPrc returns poLineInfo count"+pmsg.poLineInfo.getValidCount());
	        System.out.println("createPoPrc returns "+pmsg.xxModeCd.getValue());
			// Normal Case. (No error msg) - S21API set some error message id
			// when got any error while S21API's function.
	        System.out.println("####################### Create PO API called #############");
			s21Api.execute(pmsg,ONBATCH_TYPE.ONLINE); // execute API
			if (!S21ApiUtil.isXxMsgId(pmsg)) {
				// There is no message id, so can do commit the transaction.
				logMsg(" No error, before commit");
				EZDConnectionMgr.getInstance().commit(); 
				// commit	
				logMsg("After commit"); 
				resArr[0] = "";	
				CanonE580ITTWorkbenchDAO.updatePoItt(p_itt_number,pmsg.poOrdNum.getValue(),"",loginUser);
				resArr[2]=String.format("%s PO success! PO Number(s) ", isCreatePO ? "Create" : "Update");
				poCreatedFlag[count]=true;
				createdPoNumber[count]=pmsg.poOrdNum.getValue();				
				//resArr[2]=resArr[2]+pmsg.poOrdNum.getValue()+",";
				logMsg("PO number is:"+pmsg.poOrdNum.getValue());
				
			}
			else {
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
				System.out.println("dealer Code:"+dealer_code);
				resArr[0] = "ERROR : ";
				resArr[1] = sb.toString();
				resArr[2]=String.format("Error returned from S21 API while %s the Purchase Order" , isCreatePO ? "creating" : "updating");
				
				// If S21API got error, roll-back the transaction.
				EZDConnectionMgr.getInstance().rollback();
				// roll-back
				// throw new
				// S21AbendException("S21AbendException is thrown...");
				String p_err_msg="";
				
				if(!pmsg.vndCd.getValue().equalsIgnoreCase(dealer_code))
					p_err_msg=String.format("CUSA PO failed to %s. Error Message:" , isCreatePO ? "create" : "update")+s21ApiError;
				else
					p_err_msg=String.format("DEALER PO failed to %s. Error Message:", isCreatePO ? "create" : "update")+s21ApiError;
				
				CanonE580ITTWorkbenchDAO.updatePoItt(p_itt_number,"",p_err_msg,loginUser);				
				break;
			}
			}
			
			
			if(!poCreatedFlag[0])
			{
				resArr[0] = "";
				resArr[1] = "";
				resArr[2]=String.format("%s PO failed! Please retry this action after the API errors are fixed", isCreatePO ? "Create" : "Update");
			}
			else
			{
				for(int i=1;i<poCreatedFlag.length;i++)
				{
					if(!poCreatedFlag[i])
					{
						partialFailure=true;
						resArr[2]=String.format("%s PO success partially! PO Number(s) ", isCreatePO ? "Create" : "Update");
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
						resArr[2]=resArr[2]+String.format(" %s.", isCreatePO ? "created" : "updated");
					if(i+1==poCreatedFlag.length && partialFailure)
						resArr[2]=resArr[2]+" For failed PO\'s errors, please refer to ITT error table.";
				}
				
			}
			
			}
			else
			{
				resArr[0] = String.format("%s PO failed with following error message:", isCreatePO ? "Create" : "Update");
				resArr[1] = "";
				resArr[2] = (String)createPoPrcResult[2];
				logMsg("There is no eligible item to process:");	
			}
			
			
		} catch (Exception e) {
			logMsg("ERROR MESSAGE : " + e.getMessage());
			resArr[0] = "ERROR : ";
			resArr[1] = "";
			resArr[2] = String.format("%s PO failed! Please retry this action after API errors are fixed", isCreatePO ? "Create" : "Update");
			CanonE580ITTWorkbenchDAO.updatePoItt(p_itt_number,"",e.getMessage(),loginUser);	
			e.printStackTrace();
		} finally { // Release DB resource (Close DB Connection)
			EZDConnectionMgr.getInstance().releaseResource();
		}
		
		
		
		return resArr;
	}

	private void printInputParm(NPZC104001PMsg pmsgValuesFromTables) {
		
		logMsg("S21 APi name:"+"NPZC104001");
		logMsg("############# Input parameters are as follows #############");
		checkNull("xxModeCd",	        	pmsgValuesFromTables.xxModeCd.getValue());	        	            	
         checkNull("eventId",	           pmsgValuesFromTables.eventId.getValue());	             	           
         checkNull("procDt",pmsgValuesFromTables.procDt.getValue());
         checkNull("xxRqstTs",pmsgValuesFromTables.xxRqstTs.getValue());
         checkNull("poOrdNum",	           pmsgValuesFromTables.poOrdNum.getValue());	             	            
         checkNull("dsPoTpCd",	           pmsgValuesFromTables.dsPoTpCd.getValue());	             	            
         checkNull("dsPoTpNm",	           pmsgValuesFromTables.dsPoTpNm.getValue());	             	            
         checkNull("poQlfyCd",pmsgValuesFromTables.poQlfyCd.getValue());
         checkNull("poSubmtPsnCd",pmsgValuesFromTables.poSubmtPsnCd.getValue());
         checkNull("poSubmtTs",pmsgValuesFromTables.poSubmtTs.getValue());
         checkNull("poApvlStsCd",pmsgValuesFromTables.poApvlStsCd.getValue());
         checkNull("poApvlPsnCd",pmsgValuesFromTables.poApvlPsnCd.getValue());
         checkNull("poApvlDt",pmsgValuesFromTables.poApvlDt.getValue());
         checkNull("poApvlTs",pmsgValuesFromTables.poApvlTs.getValue());
         checkNull("destRtlWhCd",pmsgValuesFromTables.destRtlWhCd.getValue());
         checkNull("srcRtlWhCd",pmsgValuesFromTables.srcRtlWhCd.getValue());
         checkNull("poOrdSrcCd",pmsgValuesFromTables.poOrdSrcCd.getValue());
         checkNull("prntVndCd",pmsgValuesFromTables.prntVndCd.getValue());
         checkNull("prntVndNm",pmsgValuesFromTables.prntVndNm.getValue());
         checkNull("vndCd",pmsgValuesFromTables.vndCd.getValue());
         checkNull("vndNm",pmsgValuesFromTables.vndNm.getValue());
         checkNull("dealCcyCd",pmsgValuesFromTables.dealCcyCd.getValue());
         checkNull("vndDropShipFlg",pmsgValuesFromTables.vndDropShipFlg.getValue());
         checkNull("prchGrpCd",pmsgValuesFromTables.prchGrpCd.getValue());
         checkNull("vndPmtTermCd",pmsgValuesFromTables.vndPmtTermCd.getValue());
         checkNull("vndPmtTermDescTxt",pmsgValuesFromTables.vndPmtTermDescTxt.getValue());
         checkNull("rqstTechTocCd",pmsgValuesFromTables.rqstTechTocCd.getValue());
         checkNull("rqstRcvDt",pmsgValuesFromTables.rqstRcvDt.getValue());
         checkNull("rqstRcvTm",pmsgValuesFromTables.rqstRcvTm.getValue());
         checkNull("shipToCustCd",pmsgValuesFromTables.shipToCustCd.getValue());
         checkNull("shipToLocNm",pmsgValuesFromTables.shipToLocNm.getValue());
         checkNull("shipToAcctNm",pmsgValuesFromTables.shipToAcctNm.getValue());
         checkNull("shipToAddlLocNm",pmsgValuesFromTables.shipToAddlLocNm.getValue());
         checkNull("shipToFirstLineAddr",pmsgValuesFromTables.shipToFirstLineAddr.getValue());
         checkNull("shipToScdLineAddr",pmsgValuesFromTables.shipToScdLineAddr.getValue());
         checkNull("shipToThirdLineAddr",pmsgValuesFromTables.shipToThirdLineAddr.getValue());
         checkNull("shipToFrthLineAddr",pmsgValuesFromTables.shipToFrthLineAddr.getValue());
         checkNull("shipToFirstRefCmntTxt",pmsgValuesFromTables.shipToFirstRefCmntTxt.getValue());
         checkNull("shipToScdRefCmntTxt",pmsgValuesFromTables.shipToScdRefCmntTxt.getValue());
         checkNull("shipToCtyAddr",pmsgValuesFromTables.shipToCtyAddr.getValue());
         checkNull("shipToStCd",pmsgValuesFromTables.shipToStCd.getValue());
         checkNull("shipToProvNm",pmsgValuesFromTables.shipToProvNm.getValue());
         checkNull("shipToCntyNm",pmsgValuesFromTables.shipToCntyNm.getValue());
         checkNull("shipToPostCd",pmsgValuesFromTables.shipToPostCd.getValue());
         checkNull("shipToCtryCd",pmsgValuesFromTables.shipToCtryCd.getValue());
         checkNull("ctacPsnNm",pmsgValuesFromTables.ctacPsnNm.getValue());
         checkNull("rtrnShipToRtlWhCd",pmsgValuesFromTables.rtrnShipToRtlWhCd.getValue());
         checkNull("shipFromSoNumListTxt",pmsgValuesFromTables.shipFromSoNumListTxt.getValue());
         checkNull("carrCd",pmsgValuesFromTables.carrCd.getValue());
         checkNull("carrAcctNum",pmsgValuesFromTables.carrAcctNum.getValue());
         checkNull("shpgSvcLvlCd",pmsgValuesFromTables.shpgSvcLvlCd.getValue());
         checkNull("frtChrgToCd",pmsgValuesFromTables.frtChrgToCd.getValue());
         checkNull("frtChrgMethCd",pmsgValuesFromTables.frtChrgMethCd.getValue());
         checkNull("lineBizTpCd",pmsgValuesFromTables.lineBizTpCd.getValue());
         checkNull("poOrdCmntTxt",pmsgValuesFromTables.poOrdCmntTxt.getValue());
         checkNull("trsmtMethTpCd",pmsgValuesFromTables.trsmtMethTpCd.getValue());
         checkNull("sendPoFaxNum",pmsgValuesFromTables.sendPoFaxNum.getValue());
         checkNull("sendPoEmlAddr",pmsgValuesFromTables.sendPoEmlAddr.getValue());
         checkNull("poSendFlg",pmsgValuesFromTables.poSendFlg.getValue());
         checkNull("poSendTs",pmsgValuesFromTables.poSendTs.getValue());
         checkNull("poPrintFlg",pmsgValuesFromTables.poPrintFlg.getValue());
         checkNull("dsctnInd",pmsgValuesFromTables.dsctnInd.getValue());
         checkNull("wfFlg",pmsgValuesFromTables.wfFlg.getValue());
         checkNull("vndIssOrdNum",pmsgValuesFromTables.vndIssOrdNum.getValue());
         logMsg("poInfo: Header Message Information #############");
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
         logMsg("poLineInfo:  Line Information #############");
         for(int canonE580PohMsgTlbCount=0;canonE580PohMsgTlbCount<pmsgValuesFromTables.poLineInfo.getValidCount();canonE580PohMsgTlbCount++)
	           {
        	 checkNull("poOrdDtlLineNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).poOrdDtlLineNum.getValue());
 			checkNull("dispPoDtlLineNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).dispPoDtlLineNum.getValue());
 			checkNull("poLineTpCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).poLineTpCd.getValue());
 			checkNull("poMdseCmpsnTpCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).poMdseCmpsnTpCd.getValue());
 			checkNull("setPoOrdDtlLineNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).setPoOrdDtlLineNum.getValue());
 			checkNull("mdseCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).mdseCd.getValue());
 			checkNull("mdseNm",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).mdseNm.getValue()); 			
 			checkNull("mdseDescShortTxt",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).mdseDescShortTxt.getValue());
 			checkNullBigDecimal("poQty",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).poQty);
 			checkNullBigDecimal("poDispQty",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).poDispQty);
 			checkNullBigDecimal("poInvQty",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).poInvQty);
 			checkNull("poDispUomCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).poDispUomCd.getValue());
 			checkNullBigDecimal("thisMthFobCostAmt",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).thisMthFobCostAmt);
 			checkNullBigDecimal("entDealNetUnitPrcAmt",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).entDealNetUnitPrcAmt);
 			checkNullBigDecimal("entPoDtlDealNetAmt",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).entPoDtlDealNetAmt);
 			checkNullBigDecimal("entFuncNetUnitPrcAmt",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).entFuncNetUnitPrcAmt);
 			checkNullBigDecimal("entPoDtlFuncNetAmt",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).entPoDtlFuncNetAmt);
 			checkNullBigDecimal("exchRate",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).exchRate);
 			checkNull("custUomCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).custUomCd.getValue());
 			checkNull("destRtlSwhCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).destRtlSwhCd.getValue());
 			checkNull("srcRtlSwhCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).srcRtlSwhCd.getValue());
 			checkNull("invtyLocCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).invtyLocCd.getValue());
 			checkNull(" rqstRcvDt",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount). rqstRcvDt.getValue());
 			checkNull("rqstRcvTm",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).rqstRcvTm.getValue());
 			checkNull("frtCondCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).frtCondCd.getValue());
 			checkNull("origMdseCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).origMdseCd.getValue());
 			checkNull("fromStkStsCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).fromStkStsCd.getValue());
 			checkNull("toStkStsCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).toStkStsCd.getValue());
 			checkNull("adminPsnCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).adminPsnCd.getValue());
 			checkNull("poMatchTpCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).poMatchTpCd.getValue());
 			checkNullBigDecimal(" ordQty",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).ordQty);
 			checkNull("cpoOrdNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).cpoOrdNum.getValue());
 			checkNull("cpoDtlLineNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).cpoDtlLineNum.getValue());
 			checkNull("cpoDtlLineSubNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).cpoDtlLineSubNum.getValue());
 			checkNull("custIssPoNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).custIssPoNum.getValue());
 			checkNull("custIssPoDt",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).custIssPoDt.getValue());
 			checkNull("cpoOrdTpCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).cpoOrdTpCd.getValue());
 			checkNull("billToCustCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).billToCustCd.getValue());
 			checkNull("sellToCustCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).sellToCustCd.getValue());
 			checkNull("shpgPlnNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).shpgPlnNum.getValue());
 			checkNull("prchReqNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).prchReqNum.getValue());
 			checkNull("prchReqLineNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).prchReqLineNum.getValue());
 			checkNullBigDecimal(" prchReqLineSubNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount). prchReqLineSubNum);
 			checkNull("trxRefNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).trxRefNum.getValue());
 			checkNull("trxRefLineNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).trxRefLineNum.getValue());
 			checkNull("trxRefLineSubNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).trxRefLineSubNum.getValue());
 			checkNullBigDecimal("aslDtlPk",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).aslDtlPk);
 			checkNull("aslMdseCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).aslMdseCd.getValue());
 			checkNullBigDecimal("aslUnitPrcAmt",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).aslUnitPrcAmt); 			
 			checkNull("shipFromSoNumListTxt",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).shipFromSoNumListTxt.getValue());
 			checkNull("vndInvtyLocCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).vndInvtyLocCd.getValue());
 			checkNull("vndIssPoOrdNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).vndIssPoOrdNum.getValue());
 			checkNull("proNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).proNum.getValue());
 			checkNull("vndPoAckStsCd",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).vndPoAckStsCd.getValue());
 			checkNull("origPoOrdNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).origPoOrdNum.getValue());
 			checkNull("origPoOrdDtlLineNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).origPoOrdDtlLineNum.getValue());
 			checkNull("origDispPoDtlLineNum",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).origDispPoDtlLineNum.getValue());
 			checkNullBigDecimal("svcConfigMstrPk",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount). svcConfigMstrPk);
 			checkNull("poSendTs",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).poSendTs.getValue());
 			checkNull("poOrdDtlCmntTxt",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).poOrdDtlCmntTxt.getValue());
 			checkNull("xxMsgId",pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTlbCount).xxMsgId.getValue());
	           }
         logMsg("Account Information: ############ ");
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
         logMsg("Serial Information: ###############");
         for(int canonE580PosTlbCount=0;canonE580PosTlbCount<pmsgValuesFromTables.serNumList.getValidCount();canonE580PosTlbCount++)
	           {
        	 checkNull("poOrdDtlLineNum",pmsgValuesFromTables.serNumList.no(canonE580PosTlbCount).poOrdDtlLineNum.getValue());
        	 checkNullBigDecimal("poSerNumPk",pmsgValuesFromTables.serNumList.no(canonE580PosTlbCount).poSerNumPk);
        	 checkNull("serNum",pmsgValuesFromTables.serNumList.no(canonE580PosTlbCount).serNum.getValue());		
	           }
         
         
         
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

	public void logMsg(String str) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		System.out.println(str);
	}
	
//	public static void main(String [] args) {
//		boolean isCreatePO=true;
//		System.out.println(String.format("%s PO success! PO Number(s) ", isCreatePO ? "Create" : "Update"));		
//	}
}
