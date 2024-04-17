package com.canon.apps.servreq.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import parts.common.EZDBizPerformanceCounter;
import parts.common.EZDStringUtil;
import parts.dbcommon.EZDConnectionMgr;
import business.parts.NSZC070001PMsg;
import canon.apps.common.CanonConstants;

import com.canon.apps.servreq.dao.CanonE307ServiceReqCreateDao;
import com.canon.common.CanonCommonUtil;
import com.canon.cusa.s21.api.NSZ.NSZC070001.NSZC070001;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;


public class CanonE307ServiceReqChargesAPIUtil {
	
	StringBuffer sbParams;
	
	
	private   String clsName="CanonE307ServiceReqChargesAPIUtil";
    String  MODE_CODE_DEBRIEF="D";
    String  MODE_CODE_CHARGES="C";
    CanonCommonUtil util;   
	          
	public CanonE307ServiceReqChargesAPIUtil() {
		util= new CanonCommonUtil();
		sbParams = new StringBuffer("");
	}

	public  NSZC070001PMsg  getMsg(HttpServletRequest req){
		
		 NSZC070001PMsg pmsg = new NSZC070001PMsg();
		 CanonE307ServiceReqAPIUtil objApi = new CanonE307ServiceReqAPIUtil();
			try{
				 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
				 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmm");
				 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
				 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
				 String sSvcDt=sdfDt.format(Calendar.getInstance().getTime()); 
				   
				 // Mandatory Params
				 pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
				 String userId    = checkNull(req,"userName");
				// String modeCode    = checkNull(req,"modeCode");
				 
				 pmsg.xxModeCd.setValue(MODE_CODE_CHARGES);
				 pmsg.usrId.setValue(userId);
				 pmsg.slsDt.setValue(sSysDt);
				 //if(userId.length()>0) pmsg.usrId.setValue("Q03715");
				 
				 sbParams.append("\n"+"glblCmpyCd" + "= "+CanonConstants.CSA_COMPANY_CODE);
				 sbParams.append("\n"+"xxModeCd" + " = "+MODE_CODE_CHARGES);
				 sbParams.append("\n"+"userName" + " = "+userId);
				 sbParams.append("\n"+"Future Svc Dt" + " = "+sSvcDt);
				 sbParams.append("\n"+"Task Rcv Dt" + " = "+sSysDt);
				 sbParams.append("\n"+"Task Rcv Tm" + " = "+sSysTm);
				 
				 String strRecSize = checkNull(req,"recSize");
				 int recSz = 0;
				 if(!("".equals(strRecSize))&&!("null".equals(strRecSize))){
					 recSz=Integer.parseInt(strRecSize);
				 }				 
				 
				 util.logMsg(false,clsName+".saveChargesDetails", "Charges recSz : "+ recSz);
				 
				 String srvRqst           	= checkNull(req,"srvRqst");if(srvRqst.length()>0) pmsg.fsrNum.setValue(srvRqst);
				 String tskNum           	= checkNull(req,"tskNum");if(tskNum.length()>0) pmsg.svcTaskNum.setValue(tskNum);
				 String svcMachMstrPk   	= checkNull(req,"svcMachMstrPk");if(svcMachMstrPk.length()>0) pmsg.svcMachMstrPk.setValue(new BigDecimal(svcMachMstrPk.trim()));
				 String callTpCd			= checkNull(req,"callTpCd");
			     String profileId			= checkNull(req,"profileId");if(profileId.length()>0) pmsg.crCardCustRefNum.setValue(profileId);
			     String holderName			= checkNull(req,"holderName");if(holderName.length()>0) pmsg.crCardHldNm.setValue(holderName);
			     String authAmnt			= checkNull(req,"authAmnt");if(authAmnt.length()>0) pmsg.crCardAuthAmt.setValue(new BigDecimal(authAmnt));
			     
			     String cardType			= checkNull(req,"cardType");
			     if(cardType.length()>0){
			    	 String crTpDesc="";
			    	 if(cardType.length()<3){
			    	 CanonE307ServiceReqCreateDao crtObj = new CanonE307ServiceReqCreateDao();
					 crTpDesc = crtObj.getCRCardTp(cardType);
			    	 }else{
						 crTpDesc = cardType;
					 }
					// System.out.println("crTpDesc : "+ crTpDesc);
					 if(crTpDesc.length()>0) pmsg.crCardTpCd.setValue(crTpDesc);
				 }
			     String cardExpr			= checkNull(req,"cardExpr");if(cardExpr.length()>0) pmsg.crCardExprYrMth.setValue(cardExpr);
			     String txRefNum			= checkNull(req,"txRefNum");if(txRefNum.length()>0) pmsg.crCardAuthRefNum.setValue(txRefNum);
			     String srCcNum				= checkNull(req,"ccNum");if(srCcNum.length()>0) pmsg.crCardLastDigitNum.setValue(srCcNum);
				//Charges
				 for(int i=0;i<recSz;i++){
					String fsrChrgDtlPk   	= checkNull(req,"fsrChrgDtlPk"+i);if(fsrChrgDtlPk.length()>0) pmsg.xxChargeableList.no(i).fsrChrgDtlPk.setValue(new BigDecimal(fsrChrgDtlPk.trim()));
					System.out.println("fsrChrgDtlPk: "+fsrChrgDtlPk);
					if(srvRqst.length()>0) pmsg.xxChargeableList.no(i).fsrNum.setValue(srvRqst);
					String fsrVstNum   		= checkNull(req,"fsrVstNum"+i);if(fsrVstNum.length()>0) pmsg.xxChargeableList.no(i).fsrVisitNum.setValue(fsrVstNum);
					String chrgTskNum   		= checkNull(req,"chrgTskNum"+i);if(chrgTskNum.length()>0) pmsg.xxChargeableList.no(i).svcTaskNum.setValue(chrgTskNum);
					//if(tskNum.length()>0) pmsg.xxChargeableList.no(i).svcTaskNum.setValue(tskNum);
					String svcChrgTrxTpCd   = checkNull(req,"svcChrgTrxTpCd"+i);//if(svcChrgTrxTpCd.length()>0) pmsg.xxChargeableList.no(i).svcChrgTrxTpCd.setValue(svcChrgTrxTpCd);
					util.logMsg(false,clsName+".saveChargesDetails", "svcChrgTrxTpCd : "+ svcChrgTrxTpCd + "iVal : "+i);
			//		String mdseCd           	= checkNull(req,"mdseCd"+i);if(mdseCd.length()>0) pmsg.xxChargeableList.no(i).mdseCd.setValue(mdseCd);
					String mdseCd           	= checkNull(req,"itmNum"+i);if(mdseCd.length()>0) pmsg.xxChargeableList.no(i).mdseCd.setValue(mdseCd);
					String svcChrgQty = checkNull(req, "qty"+i);
					int chrgQty = 0;
					if(svcChrgQty.length()>0)
						 chrgQty = Integer.parseInt(svcChrgQty);
					pmsg.xxChargeableList.no(i).svcChrgQty.setValue(chrgQty);
					String uomCd           	= checkNull(req,"uomCd"+i);if(uomCd.length()>0) pmsg.xxChargeableList.no(i).uomCd.setValue(uomCd);
					String untLstPrice 		=checkNull(req, "untLstPrice"+i);if(untLstPrice.length()>0) pmsg.xxChargeableList.no(i).svcChrgUnitAmt.setValue(new BigDecimal(untLstPrice));
				//	String svcChrgDlAmt 	= checkNull(req,"extndAmt"+i);if(svcChrgDlAmt.length()>0) pmsg.xxChargeableList.no(i).svcChrgDealAmt.setValue(new BigDecimal(svcChrgDlAmt.trim()));
					String ovrRdPrc 		= checkNull(req,"ovrRdPrc"+i);
					String chrgFlg 			=checkNull(req, "chrgFlg"+i);
					String chngRsn 		= checkNull(req,"chngRsn"+i);
					
					//if(("0.0".equals(ovrRdPrc)) || ("0.00".equals(ovrRdPrc)) ||("0".equals(ovrRdPrc))){ 
					//	ovrRdPrc="";
					//}
					util.logMsg(false,clsName+".saveChargesDetails", "Before Bill code in Charges: " + svcMachMstrPk+" ovrRdPrc: "+ovrRdPrc+" chrgFlg: "+chrgFlg);
					String[] strObj = objApi.getBillCode(svcMachMstrPk,"","1","","",callTpCd, userId,"N");
					if(strObj!=null && strObj.length>0){
						System.out.println("discount rate: "+strObj[8]);
						//pmsg.xxChargeableList.no(i).svcChrgDiscRate.setValue(new BigDecimal(strObj[8]));
					}
					if(fsrChrgDtlPk.length()<=0){
						if("0".equals(svcChrgTrxTpCd)){
							pmsg.xxChargeableList.no(i).svcChrgTrxTpCd.setValue(svcChrgTrxTpCd);
							pmsg.xxChargeableList.no(i).svcChrgDiscRate.setValue(new BigDecimal("100"));
							pmsg.xxChargeableList.no(i).svcChrgFlg.setValue("N");
							if(chngRsn.length()>0) pmsg.xxChargeableList.no(i).ovrdChngRsnCd.setValue(chngRsn);
						}else if("1".equals(svcChrgTrxTpCd)){
							pmsg.xxChargeableList.no(i).svcChrgTrxTpCd.setValue(svcChrgTrxTpCd);
							pmsg.xxChargeableList.no(i).svcChrgDiscRate.setValue(new BigDecimal("0"));
							pmsg.xxChargeableList.no(i).svcChrgFlg.setValue("Y");
							if(ovrRdPrc.length()>0)pmsg.xxChargeableList.no(i).ovrdSvcChrgUnitAmt.setValue(new BigDecimal(ovrRdPrc));
							if(chngRsn.length()>0) pmsg.xxChargeableList.no(i).ovrdChngRsnCd.setValue(chngRsn);
						}else if("9".equals(svcChrgTrxTpCd)){
							//To DO
							//String[] strObj = objApi.getBillCode(svcMachMstrPk,"","1");
							pmsg.xxChargeableList.no(i).svcChrgTrxTpCd.setValue(svcChrgTrxTpCd);
							if(strObj!=null && strObj.length>0){
								System.out.println("discount rate: "+strObj[8]+ " svcChrgTrxTpCd: "+svcChrgTrxTpCd);
								pmsg.xxChargeableList.no(i).svcChrgDiscRate.setValue(new BigDecimal(strObj[8]));
							}
							pmsg.xxChargeableList.no(i).svcChrgFlg.setValue("Y");
							if(chngRsn.length()>0) pmsg.xxChargeableList.no(i).ovrdChngRsnCd.setValue(chngRsn);
						}
					}else{
						System.out.println("chngRsn: "+ chngRsn);
						if(ovrRdPrc.length()>0){
							if(svcChrgTrxTpCd.length()>0) pmsg.xxChargeableList.no(i).svcChrgTrxTpCd.setValue("1");
							pmsg.xxChargeableList.no(i).svcChrgDiscRate.setValue(new BigDecimal("0"));
							pmsg.xxChargeableList.no(i).svcChrgFlg.setValue("Y");
							if(ovrRdPrc.length()>0)pmsg.xxChargeableList.no(i).ovrdSvcChrgUnitAmt.setValue(new BigDecimal(ovrRdPrc));
							if(chngRsn.length()>0) pmsg.xxChargeableList.no(i).ovrdChngRsnCd.setValue(chngRsn);
						}else if(chrgFlg.length()>0){
							if(svcChrgTrxTpCd.length()>0) pmsg.xxChargeableList.no(i).svcChrgTrxTpCd.setValue("0");
							pmsg.xxChargeableList.no(i).svcChrgDiscRate.setValue(new BigDecimal("100"));
							pmsg.xxChargeableList.no(i).svcChrgFlg.setValue("N");
							if(chngRsn.length()>0) pmsg.xxChargeableList.no(i).ovrdChngRsnCd.setValue(chngRsn);
						}else if(ovrRdPrc.length()<=0){
							if(svcChrgTrxTpCd.length()>0) pmsg.xxChargeableList.no(i).svcChrgTrxTpCd.setValue(svcChrgTrxTpCd);
							String contDiscRt 	= checkNull(req,"contDiscRt"+i);if(contDiscRt.length()>0) pmsg.xxChargeableList.no(i).svcChrgDiscRate.setValue(new BigDecimal(contDiscRt.trim()));
							if(chrgFlg.length()>0) 
								pmsg.xxChargeableList.no(i).svcChrgFlg.setValue("N");
							else
								pmsg.xxChargeableList.no(i).svcChrgFlg.setValue("Y");
						}
							String prcCatgCd 		= checkNull(req,"prcCatgCd"+i);if(prcCatgCd.length()>0) pmsg.xxChargeableList.no(i).prcCatgCd.setValue(prcCatgCd);
					}

					
				//	String contPrc 	= checkNull(req,"contPrc"+i);if(contPrc.length()>0) pmsg.xxChargeableList.no(i).svcChrgDealDiscAmt.setValue(new BigDecimal(contPrc.trim()));
					
					
					util.logMsg(false,clsName+".saveChargesDetails", "chrgFlg : "+ chrgFlg);
					
					
					//String netPrice 		= checkNull(req,"netPrice"+i);if(netPrice.length()>0) pmsg.xxChargeableList.no(0).
					
				//	String userName 		= checkNull(req,"userName");if(userName.length()>0)  pmsg.xxChargeableList.no(i).ovrdChngUsrId.setValue(userName);
					
				
					String svcInvChrgTpCd 	= checkNull(req,"svcInvChrgTpCd"+i);if(svcInvChrgTpCd.length()>0) pmsg.xxChargeableList.no(i).svcInvChrgTpCd.setValue(svcInvChrgTpCd);
				 }
			 		pmsg.xxChargeableList.setValidCount(recSz);
				 
		}catch(Exception e){
			util.logMsg(false, clsName+".saveChargesDetails", "Exception in Getting Message :"+e.getMessage());
		}
			return pmsg;
	}
	public String[] saveChargesDetails(HttpServletRequest req){
		
		//Create instance of API 
		int tskCnt=1;
		String res[] = new String[2];
		NSZC070001 s21Api = new NSZC070001();
		//Create instance of message
		try {
				StringBuffer sb=new StringBuffer("");
				NSZC070001PMsg pmsg = new NSZC070001PMsg();
				pmsg = getMsg(req);		
				
				  String userName=pmsg.usrId.getValue();
				  String timeRegion="EST";
				  String appId="EXTNE307";
				  String pageId="CallAvoidSR";
				  String appPageId="EXTNE307_CallAvoidSR";
					
				 /* CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
				  EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userName,timeRegion,appId,pageId,appId);
				  bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/			
				
				  util.logMsg(false,clsName+".saveChargesDetails", "################## Calling Charges API #######################");
				  util.logMsg(false,clsName+".saveChargesDetails", " Before Charges API Execute.......");
				  util.logMsg(false,clsName+".saveChargesDetails", " Before Charges API Params....../n"+ pmsg.toString());
				  
				  //execute API
				  s21Api.execute(pmsg, ONBATCH_TYPE.ONLINE); 
			    
			/*	  bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
				  bizPerfCounter.requestQueue(); */
				
				  util.logMsg(false,clsName+".saveChargesDetails", " After Charges API Execute.......");
				  
				// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
				
					if (!S21ApiUtil.isXxMsgId(pmsg)) {
						
						// There is no message id, so can do commit the transaction.
						EZDConnectionMgr.getInstance().commit();
						res[0]="Y";
						res[1]=pmsg.fsrNum.getValue();
						// commit	
						util.logMsg(false,clsName+".saveChargesDetails", "Charges , FSR Number :"+ pmsg.fsrNum.getValue());
	
					} else {
							// Error Case - S21API set some error message id when got any error while S21API's function.		
							List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
							for (String msg : err) {
								util.logMsg(true,clsName+".saveChargesDetails", "Charges Error :"+msg);
								sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
							}		
							res[0]="E";
							res[1]=sb.toString();
							// If S21API got error, roll-back the transaction.
								EZDConnectionMgr.getInstance().rollback(); 
							// roll-back		
							// throw new S21AbendException("S21AbendException is thrown...");
					}
			} catch (Exception e) {
				util.logMsg(true,clsName+".saveChargesDetails", "Error in Charges : "+ e.getMessage());
			} finally {	// Release DB resource (Close DB Connection)	
				EZDConnectionMgr.getInstance().releaseResource();
			}
		return res;
	}
	public  String checkNull(HttpServletRequest req, String str ){
	    String s="";
		String tmp =  req.getParameter(str);
		if (tmp != null) {
			if(tmp.trim().length()>0){
			  s=tmp.trim();
			  sbParams.append("\n" +str +" = "+s );
			}
		}
		return s;  
	}
}
