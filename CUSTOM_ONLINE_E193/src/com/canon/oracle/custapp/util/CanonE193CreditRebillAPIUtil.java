package com.canon.oracle.custapp.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import canon.apps.common.CanonConstants;
import canon.apps.common.CanonS21SessionValidate;
import javax.servlet.http.HttpServletRequest;
import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDConnectionMgr;
import business.parts.NSZC053001PMsg;
import com.canon.common.CanonCommonUtil;
//import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
//import com.canon.cusa.s21.api.NSZ.NSZC053001.CIEntryProcess;
import com.canon.cusa.s21.api.NSZ.NSZC053001.NSZC053001;
//import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SvcChrg;
//import com.canon.cusa.s21.common.NSX.NSXC002001.SvcPgmBean;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_CRBaseObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_CRMeterObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_CRPriceObj;

public class CanonE193CreditRebillAPIUtil { 

	private   String clsName="CanonE193CreditRebillAPIUtil";
	         String  MODE_CODE_CR_CREATE="1";
	          String  MODE_CODE_SR_UPDATE="2";
	          String  MODE_CODE_SR_CANCEL="4";
	          CanonCommonUtil util;         
	
	  
	public CanonE193CreditRebillAPIUtil(){
		util= new CanonCommonUtil();
	}
	public   NSZC053001PMsg  getMsg(HttpServletRequest req) throws ParseException{
		
		NSZC053001PMsg pmsg = new NSZC053001PMsg();
		
		   
		util.logMsg(false, clsName+".createCreditRebill", "CREATE CREDIT REBILL PARAMETERS"); 
		// Mandatory Params
		pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
		pmsg.xxModeCd.setValue(MODE_CODE_CR_CREATE);
		SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMdd");
		String slsDt = lsDateFmt.format(new Date()); 
		//Start Changes
		String custIncdtId  =(String) (req.getAttribute("custIncdtId")==null?"":req.getAttribute("custIncdtId"));// "2000393";
		String custIncdtLineId  =(String) (req.getAttribute("custIncdtLineId")==null?"":req.getAttribute("custIncdtLineId"));//"1";
		String svcCrRebilDescTxt=(String) (req.getAttribute("svcCrRebilDescTxt")==null?"":req.getAttribute("svcCrRebilDescTxt"));
		String svcCrRebilSrcNm = (String) (req.getAttribute("svcCrRebilSrcNm")==null?"":req.getAttribute("svcCrRebilSrcNm"));//"Customer Care";
		String custCareRgtnPsnCd =(String) (req.getAttribute("custCareRgtnPsnCd")==null?"":req.getAttribute("custCareRgtnPsnCd")); //"TESTPSN";
		String origSvcInvNum =(String) (req.getAttribute("origSvcInvNum")==null?"":req.getAttribute("origSvcInvNum"));//"20000862";
		System.out.println(" origSvcInvNum in creditBill api completed:"+origSvcInvNum);
		/*int svcCrRebilPk=0;
		if(!(req.getAttribute("svcCrRebilPk")==null))
		{
			String svcPk=(String) req.getAttribute("svcCrRebilPk");
			svcCrRebilPk=Integer.parseInt(svcPk);
		}*/ 		
		String svcContrBrCd=(String) (req.getAttribute("svcContrBrCd")==null?"":req.getAttribute("svcContrBrCd"));
		// NewLy Added to get value of custIncdtAsgPsnCd from request For Defect#17205.
		String custIncdtAsgPsnCd=(String) (req.getAttribute("custIncdtAsgPsnCd")==null?"":req.getAttribute("custIncdtAsgPsnCd")); 
		 
	
		ArrayList<Canon_E193_CRMeterObj> meterList = new ArrayList<Canon_E193_CRMeterObj>();
		int listCount=0;
		int commListCount = 0; // This variable for, when Base & Usage Contact will trigger together 
		 
		System.out.println("req.getAttribute(meterList): "+req.getAttribute("meterList"));
		System.out.println("req.getAttribute(baseList): "+req.getAttribute("baseList"));
		System.out.println("req.getAttribute(priceList): "+req.getAttribute("priceList"));
		if(req.getAttribute("meterList")!=null)			
		{
			 meterList=(ArrayList<Canon_E193_CRMeterObj>)req.getAttribute("meterList");
			 System.out.println(" meterList Size= " + meterList.size());
			 if(meterList.size()!=0)
			 {
				 listCount=listCount+meterList.size();
				 commListCount = commListCount+listCount;
			 }
		}
		ArrayList<Canon_E193_CRPriceObj> priceList=new ArrayList<Canon_E193_CRPriceObj>();
		if(req.getAttribute("priceList")!=null)			
		{
			 priceList=(ArrayList<Canon_E193_CRPriceObj>)req.getAttribute("priceList");	
			 System.out.println(" priceList Size= " + priceList.size());
			 if(priceList.size()!=0){
				 listCount=listCount+priceList.size();
				 commListCount = listCount;
			 }
		}
		ArrayList<Canon_E193_CRBaseObj> baseList=new ArrayList<Canon_E193_CRBaseObj>();
		if(req.getAttribute("baseList")!=null)			
		{
			 baseList=(ArrayList<Canon_E193_CRBaseObj>)req.getAttribute("baseList");
			 System.out.println(" baseList Size= " + baseList.size());
			 if(baseList.size()!=0){
				 listCount=listCount+baseList.size();
			 }
			 //System.out.println(" BASE LIST COUNT= " + listCount + " baseList.SIZE()= " + baseList.size() );
		}
		
		
		pmsg.custIncdtId.setValue(custIncdtId);
		System.out.println("!!!! Credit Rebill Params custIncdtId " + custIncdtId);
		 
		pmsg.custIncdtLineId.setValue(custIncdtLineId);
		System.out.println("!!!! Credit Rebill Params custIncdtLineId " +custIncdtLineId);
		 
		pmsg.svcCrRebilDescTxt.setValue(svcCrRebilDescTxt);
		System.out.println("!!!! Credit Rebill Params svcCrRebilDescTxt " +svcCrRebilDescTxt);
		 
		pmsg.svcCrRebilSrcNm.setValue(svcCrRebilSrcNm);
		System.out.println("!!!! Credit Rebill Params svcCrRebilSrcNm " + svcCrRebilSrcNm);
		 
		pmsg.custCareRgtnPsnCd.setValue(custCareRgtnPsnCd);
		System.out.println("!!!! Credit Rebill Params custCareRgtnPsnCd " + custCareRgtnPsnCd);
		 
		pmsg.slsDt.setValue(slsDt);
		System.out.println("!!!! Credit Rebill Params slsDt" + slsDt);
		 
		pmsg.svcContrBrCd.setValue(svcContrBrCd);
		System.out.println("!!!! Credit Rebill Params svcContrBrCd" + svcContrBrCd);
		 
		// Need to setValue of custIncdtPsnCd at NSZC053001PMsg Defect#17205
		pmsg.custIncdtAsgPsnCd.setValue(custIncdtAsgPsnCd);
		System.out.println("!!!! Credit Rebill Params custIncdtAsgPsnCd" + custIncdtAsgPsnCd);
		 
		String serialNumber = "";
		int contractLineId = 0;
		String mtrBllgFromDt = "";
		String mtrBllgThruDt = "";
		String physMtrLbCd = "L0";
		String bllgMtrLbCd = "";
		BigDecimal startTotalHardRead = null;
		BigDecimal endTotalHardRead = null;
		BigDecimal oldTestCopies = null;
		BigDecimal newTestCopies = null;
		//String invNumber = "";
		SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat targetFormat = new SimpleDateFormat("yyyyMMdd");
		Date dateToFormat = null;
		String formattedDate = "";
		int lengthOfMeterList = meterList.size();
		System.out.println("MeterList Length " + lengthOfMeterList);

		int apiListCount = 0;
		 
		for (int tempCount = 0; tempCount < lengthOfMeterList; tempCount++) 
		{
			if (meterList.get(tempCount) != null) 
			{
				serialNumber = meterList.get(tempCount).getSerialNum();
				mtrBllgFromDt = meterList.get(tempCount).getStartMtrRd();
				mtrBllgThruDt = meterList.get(tempCount).getEndMtrRd();
				startTotalHardRead = meterList.get(tempCount).getStTotHardRd();
				endTotalHardRead =   meterList.get(tempCount).getEndTotHardRd();
				oldTestCopies =      meterList.get(tempCount).getOldTestCp();
				newTestCopies =      meterList.get(tempCount).getNewTestCp();
				bllgMtrLbCd =        meterList.get(tempCount).getBillIngMeterLbCd();
				physMtrLbCd =        meterList.get(tempCount).getPhysMtrLbCd();
				//invNumber =          meterList.get(tempCount).getInvNum();
				
				pmsg.xxCrRebilDtlList.no(apiListCount).origSvcInvNum.setValue(origSvcInvNum); 
				System.out.println("!!!! Credit Rebill Params Meter origSvcInvNum " + origSvcInvNum);
				
				if(serialNumber != null && serialNumber.trim().length() > 0 && !"Fleet".equalsIgnoreCase(serialNumber))
				{
					pmsg.xxCrRebilDtlList.no(apiListCount).serNum.setValue(serialNumber);
					System.out.println("!!!! Credit Rebill Params Meter serialNumber "+ serialNumber);
				}
				
				pmsg.xxCrRebilDtlList.no(apiListCount).startMtrReadDt.setValue(mtrBllgFromDt);
				System.out.println("!!!! Credit Rebill Params Meter startMtrReadDt " + mtrBllgFromDt );
				
				pmsg.xxCrRebilDtlList.no(apiListCount).endMtrReadDt.setValue(mtrBllgThruDt);
				System.out.println("!!!! Credit Rebill Params Meter endMtrReadDt " + mtrBllgThruDt);
				
				pmsg.xxCrRebilDtlList.no(apiListCount).newStartReadMtrCnt.setValue(startTotalHardRead);
				System.out.println("!!!! Credit Rebill Params Meter newStartReadMtrCnt " + startTotalHardRead);
				
				pmsg.xxCrRebilDtlList.no(apiListCount).newEndReadMtrCnt.setValue(endTotalHardRead);
				System.out.println("!!!! Credit Rebill Params Meter newEndReadMtrCnt "+ endTotalHardRead);
				
				pmsg.xxCrRebilDtlList.no(apiListCount).newStartTestMtrCnt.setValue(oldTestCopies);
				System.out.println("!!!! Credit Rebill Params Meter newStartTestMtrCnt "+ oldTestCopies); 	
				
				pmsg.xxCrRebilDtlList.no(apiListCount).newEndTestMtrCnt.setValue(newTestCopies);
				System.out.println("!!!! Credit Rebill Params Meter newEndTestMtrCnt "+ newTestCopies);
				
				pmsg.xxCrRebilDtlList.no(apiListCount).physMtrLbCd.setValue(physMtrLbCd);
				System.out.println("!!!! Credit Rebill Params Meter physMtrLbCd "+ physMtrLbCd);
				
				pmsg.xxCrRebilDtlList.no(apiListCount).bllgMtrLbCd.setValue(bllgMtrLbCd);
				System.out.println("!!!! Credit Rebill Params Meter bllgMtrLbCd "+ bllgMtrLbCd);
				
				apiListCount++;
			 }
		}
		 
		 //Price
		 System.out.println("Starting price list:::::");
		 System.out.println("priceList Length " + priceList.size() + "apiListCount " + apiListCount);
		 
		 String tempDateBilledFrom = null;
		 String tempDateBilledTo = null;
		 
		 for(int tempCount=0;tempCount<priceList.size() ;tempCount++)
		 {
			 tempDateBilledFrom = priceList.get(tempCount).getDateBilledFrom();
			 tempDateBilledTo = priceList.get(tempCount).getDateBilledTo();
			 
			 if(tempDateBilledFrom != null && !tempDateBilledFrom.trim().isEmpty() && tempDateBilledTo != null  && !tempDateBilledTo.trim().isEmpty())
			 {
				 break;
			 }
		 }
		 
		 for(int tempCount=0;tempCount<priceList.size() ;tempCount++)
		 {
			 BigDecimal oldXsCopyQty=null;
			 BigDecimal oldXsMtrAmtRate=null;
			 BigDecimal newXsCopyQty=null;
			 BigDecimal newXsMtrAmtRate=null;
			
			 String dateBilledFrom=priceList.get(tempCount).getDateBilledFrom();
			 String dateBilledTo=priceList.get(tempCount).getDateBilledTo();
			 
			 if(dateBilledFrom == null)
			 {
				 dateBilledFrom = tempDateBilledFrom;
			 }
			 if(dateBilledTo == null)
			 {
				 dateBilledTo = tempDateBilledTo;
			 }
			 
			 serialNumber = priceList.get(tempCount).getSerialNum();
			 bllgMtrLbCd = priceList.get(tempCount).getBillIngMeterLbCd(); 
			 
			 oldXsCopyQty=priceList.get(tempCount).getOldTier1CopyVolume();
			 oldXsMtrAmtRate=priceList.get(tempCount).getOldTier1Rate();
			 newXsCopyQty=priceList.get(tempCount).getNewTier1CopyVolume();
			 newXsMtrAmtRate=priceList.get(tempCount).getNewTier1Rate();			 

			 if(oldXsCopyQty != null || oldXsMtrAmtRate != null || newXsCopyQty != null || newXsMtrAmtRate != null)
			 {
				 priceListApiParamSetup(serialNumber, origSvcInvNum, 
							oldXsCopyQty, oldXsMtrAmtRate, newXsCopyQty,
							newXsMtrAmtRate, dateBilledFrom, dateBilledTo, 
							bllgMtrLbCd, pmsg, apiListCount);
				 apiListCount++;
			 }
			 
			 oldXsCopyQty=priceList.get(tempCount).getOldTier2CopyVolume();
			 oldXsMtrAmtRate=priceList.get(tempCount).getOldTier2Rate();
			 newXsCopyQty=priceList.get(tempCount).getNewTier2CopyVolume();
			 newXsMtrAmtRate=priceList.get(tempCount).getNewTier2Rate();

			 if(oldXsCopyQty != null || oldXsMtrAmtRate != null || newXsCopyQty != null || newXsMtrAmtRate != null)
			 {
				 priceListApiParamSetup(serialNumber, origSvcInvNum, 
							oldXsCopyQty, oldXsMtrAmtRate, newXsCopyQty,
							newXsMtrAmtRate, dateBilledFrom, dateBilledTo, 
							bllgMtrLbCd, pmsg, apiListCount);
				 apiListCount++;
			 }
			 
			 oldXsCopyQty=priceList.get(tempCount).getOldTier3CopyVolume();
			 oldXsMtrAmtRate=priceList.get(tempCount).getOldTier3Rate();
			 newXsCopyQty=priceList.get(tempCount).getNewTier3CopyVolume();
			 newXsMtrAmtRate=priceList.get(tempCount).getNewTier3Rate();			 

			 if(oldXsCopyQty != null || oldXsMtrAmtRate != null || newXsCopyQty != null || newXsMtrAmtRate != null)
			 {
				 priceListApiParamSetup(serialNumber, origSvcInvNum, 
							oldXsCopyQty, oldXsMtrAmtRate, newXsCopyQty,
							newXsMtrAmtRate, dateBilledFrom, dateBilledTo, 
							bllgMtrLbCd, pmsg, apiListCount);
				 apiListCount++;
			 }
			 
			 oldXsCopyQty=priceList.get(tempCount).getOldTier4CopyVolume();
			 oldXsMtrAmtRate=priceList.get(tempCount).getOldTier4Rate();
			 newXsCopyQty=priceList.get(tempCount).getNewTier4CopyVolume();
			 newXsMtrAmtRate=priceList.get(tempCount).getNewTier4Rate();

			 if(oldXsCopyQty != null || oldXsMtrAmtRate != null || newXsCopyQty != null || newXsMtrAmtRate != null)
			 {
				 priceListApiParamSetup(serialNumber, origSvcInvNum, 
							oldXsCopyQty, oldXsMtrAmtRate, newXsCopyQty,
							newXsMtrAmtRate, dateBilledFrom, dateBilledTo, 
							bllgMtrLbCd, pmsg, apiListCount);
				 apiListCount++;
			 }
		 }
		 
		 // For Base Contact.
		 int basePriceLength = baseList.size();
		 System.out.println("~BasePriceLength~ "+baseList.size() + " apiListCount= " + apiListCount);
		 for(int tempCount=0;tempCount <basePriceLength;tempCount++)
		 {
			 if(baseList.get(tempCount)!=null) 
			 {
				 serialNumber = baseList.get(tempCount).getSerialNum(); 
				 
				 contractLineId = baseList.get(tempCount).getContractLineId();
				 //System.out.println("CanonE193CreditRebillAPIUtil  contractLineId : " + contractLineId);
				 
				 String baseBllgFromDt=baseList.get(tempCount).getStartMtrRdDt();
				 String baseBllgThruDt=baseList.get(tempCount).getEndMtrRdDt();
				 BigDecimal newBaseDealAmt=baseList.get(tempCount).getNewBasePrc();
				 
				 if(serialNumber != null && serialNumber.trim().length() > 0 && !"Fleet".equalsIgnoreCase(serialNumber))
				 {
					 pmsg.xxCrRebilDtlList.no(apiListCount).serNum.setValue(serialNumber);
					 System.out.println("!!!! Credit Rebill Params Base serialNumber "+ serialNumber);
				 }
				
				 pmsg.xxCrRebilDtlList.no(apiListCount).dsContrDtlPk.setValue(contractLineId);
				 
				 pmsg.xxCrRebilDtlList.no(apiListCount).origSvcInvNum.setValue(origSvcInvNum);
				 System.out.println("!!!! Credit Rebill Params Base origSvcInvNum "+ origSvcInvNum);
				 
				 dateToFormat=originalFormat.parse(baseBllgFromDt);
				 formattedDate= targetFormat.format(dateToFormat);
				 pmsg.xxCrRebilDtlList.no(apiListCount).baseBllgFromDt.setValue(formattedDate);
				 System.out.println("!!!! Credit Rebill Params Base baseBllgFromDt"+ formattedDate);
				 
				 dateToFormat=originalFormat.parse(baseBllgThruDt);
				 formattedDate= targetFormat.format(dateToFormat);
				 pmsg.xxCrRebilDtlList.no(apiListCount).baseBllgThruDt.setValue(formattedDate);
				 System.out.println("!!!! Credit Rebill Params Base baseBllgThruDt "+ baseBllgThruDt);
				 
				 pmsg.xxCrRebilDtlList.no(apiListCount).newBaseDealAmt.setValue(newBaseDealAmt);
				 System.out.println("!!!! Credit Rebill Params Base newBaseDealAmt "+  newBaseDealAmt);
				 apiListCount++;
			 } 
		 }
		 
		 pmsg.xxCrRebilDtlList.setValidCount(apiListCount);
		 System.out.println("!!!! Credit Rebill Params xxCrRebilDtlList" + apiListCount);		 
		 //End Changes
		 
		 return pmsg;
	}
	
	private void priceListApiParamSetup(String serialNumber, String origSvcInvNum, 
			BigDecimal oldXsCopyQty, BigDecimal oldXsMtrAmtRate, BigDecimal newXsCopyQty,
			BigDecimal newXsMtrAmtRate, String dateBilledFrom, String dateBilledTo, 
			String bllgMtrLbCd, NSZC053001PMsg pmsg, int apiListCount)
	{
		if(serialNumber != null && serialNumber.trim().length() > 0 && !"Fleet".equalsIgnoreCase(serialNumber))
		 {
			 pmsg.xxCrRebilDtlList.no(apiListCount).serNum.setValue(serialNumber);
			 System.out.println("!!!! Credit Rebill Params Price serialNumber "+serialNumber);
		 }
		 
		 pmsg.xxCrRebilDtlList.no(apiListCount).origSvcInvNum.setValue(origSvcInvNum);
		 System.out.println("!!!! Credit Rebill Params Price  origSvcInvNum "+ origSvcInvNum);
		 
		 
		 pmsg.xxCrRebilDtlList.no(apiListCount).oldXsCopyQty.setValue(oldXsCopyQty);			   
		 System.out.println("!!!! Credit Rebill Params Price oldXsCopyQty "+ oldXsCopyQty);
		   
		 
		 pmsg.xxCrRebilDtlList.no(apiListCount).oldXsMtrAmtRate.setValue(oldXsMtrAmtRate);
		 System.out.println("!!!! Credit Rebill Params Price oldXsMtrAmtRate "+ oldXsMtrAmtRate);
		 
		 
		 pmsg.xxCrRebilDtlList.no(apiListCount).newXsCopyQty.setValue(newXsCopyQty);			 
		 System.out.println("!!!! Credit Rebill Params Price newXsCopyQty "+ newXsCopyQty);
		 
		
		 pmsg.xxCrRebilDtlList.no(apiListCount).newXsMtrAmtRate.setValue(newXsMtrAmtRate);
		 System.out.println("!!!! Credit Rebill Params Price newXsMtrAmtRate " + newXsMtrAmtRate);
		
		 pmsg.xxCrRebilDtlList.no(apiListCount).mtrBllgFromDt.setValue(dateBilledFrom);
		 System.out.println("!!!! Credit Rebill Params Price mtrBllgFromDt "+dateBilledFrom);
		
		 pmsg.xxCrRebilDtlList.no(apiListCount).mtrBllgThruDt.setValue(dateBilledTo);			 
		 System.out.println("!!!! Credit Rebill Params Price mtrBllgThruDt "+ dateBilledTo);
		
		 pmsg.xxCrRebilDtlList.no(apiListCount).bllgMtrLbCd.setValue(String.valueOf(bllgMtrLbCd)); 
		 System.out.println("!!!! Credit Rebill Params Price bllgMtrLbCd" + bllgMtrLbCd);
	}
	
	
	// For testing purpose
/*public   NSZC053001PMsg  getMsg(HttpServletRequest req){
		
		NSZC053001PMsg pmsg = new NSZC053001PMsg();
		
		 //SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		// SimpleDateFormat sdfTm = new SimpleDateFormat("HHmmss");
		// String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
		 //String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
		   
		 util.logMsg(false, clsName+".createCreditRebill", "CREATE CREDIT REBILL PARAMETERS"); 
		 
		 // Mandatory Params
		 pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
		 pmsg.xxModeCd.setValue(MODE_CODE_SR_CANCEL);
		// pmsg.xxModeCd.setValue(MODE_CODE_CR_CREATE); 
		 
		 //Start Changes
		 
		 String custIncdtId  ="2000818";//"2000363";
		 String slsDt = "20170217";
		 String custIncdtLineId  ="1";
		 String svcCrRebilDescTxt = "BILLING -- INCORRECT PRICING/METER READS";
		 String svcCrRebilSrcNm = "Customer Care";
		 String custCareRgtnPsnCd ="Q08693";
		 String origSvcInvNum ="6000061";
		 String slsDt = "20160613";
		 String  mtrBllgFromDt ="20160201";
		 String  mtrBllgThruDt ="20160229";
		 String physMtrLbCd="2T";
		 
		 //update
		// String 
		 pmsg.svcCrRebilPk.setValue(2000981);
		 pmsg.xxCrRebilDtlList.no(0).svcCrRebilDtlPk.setValue(2000981);
		 //end  update
		 
		 pmsg.custIncdtId.setValue(custIncdtId);
		 pmsg.custIncdtLineId.setValue(custIncdtLineId);
		 pmsg.svcCrRebilDescTxt.setValue(svcCrRebilDescTxt);
		 pmsg.svcCrRebilSrcNm.setValue(svcCrRebilSrcNm);
		 pmsg.custCareRgtnPsnCd.setValue(custCareRgtnPsnCd);
		 pmsg.xxCrRebilDtlList.no(0).origSvcInvNum.setValue(origSvcInvNum);
		 pmsg.custIncdtAsgPsnCd.setValue("K01987");
		 pmsg.svcContrBrCd.setValue("050");
		// pmsg.svcCrRebilDescTxt.setValue("Test");
		 pmsg.slsDt.setValue(slsDt);
		 pmsg.xxCrRebilDtlList.no(0).serNum.setValue("CRS002-2");
		 
		 //Meter  Read
		 pmsg.xxCrRebilDtlList.no(0).startMtrReadDt.setValue(mtrBllgFromDt);
		 pmsg.xxCrRebilDtlList.no(0).endMtrReadDt.setValue(mtrBllgThruDt);
		 pmsg.xxCrRebilDtlList.no(0).physMtrLbCd.setValue(physMtrLbCd);
		 pmsg.xxCrRebilDtlList.no(0).bllgMtrLbCd.setValue("1W");
		 pmsg.xxCrRebilDtlList.no(0).serNum.setValue("CRS002-2");
		 pmsg.xxCrRebilDtlList.no(0).newStartReadMtrCnt.setValue(100);
		 pmsg.xxCrRebilDtlList.no(0).newEndReadMtrCnt.setValue(500);
		 pmsg.xxCrRebilDtlList.no(0).mtrBllgFromDt.setValue(mtrBllgFromDt);
		 pmsg.xxCrRebilDtlList.no(0).mtrBllgThruDt.setValue(mtrBllgThruDt);
		// 
		 
		 //Price
		pmsg.xxCrRebilDtlList.no(0).mtrBllgFromDt.setValue(mtrBllgFromDt);
		 pmsg.xxCrRebilDtlList.no(0).mtrBllgThruDt.setValue(mtrBllgThruDt);
		 pmsg.xxCrRebilDtlList.no(0).oldXsCopyQty.setValue(0);
		 pmsg.xxCrRebilDtlList.no(0).oldXsMtrAmtRate.setValue((int) 2.5);
		 pmsg.xxCrRebilDtlList.no(0).newXsCopyQty.setValue(500);
		 pmsg.xxCrRebilDtlList.no(0).newXsMtrAmtRate.setValue(5);
		 pmsg.xxCrRebilDtlList.no(0).bllgMtrLbCd.setValue("1W");
		 
		 // Base
		 pmsg.xxCrRebilDtlList.no(0).baseBllgFromDt.setValue(mtrBllgFromDt);
		 pmsg.xxCrRebilDtlList.no(0).baseBllgThruDt.setValue(mtrBllgThruDt);
		 pmsg.xxCrRebilDtlList.no(0).newBaseDealAmt.setValue(100);
		 
		 pmsg.xxCrRebilDtlList.setValidCount(1);
		 
			
		 //End Changes
		 pmsg.custIncdtId.setValue(custIncdtId);
		 pmsg.slsDt.setValue(slsDt);
		 pmsg.svcCrRebilPk.setValue(23);
		 pmsg.xxCrRebilDtlList.no(0).svcCrRebilDtlPk.setValue(23);
		 
		
		return pmsg;
		
	}*/
	public String[] createCreditRebill(HttpServletRequest req){
		
		SimpleDateFormat format = new SimpleDateFormat("z");		
		SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		String timezone = format.format(new Date());
		String invokeTimestamp = lsDateFmt.format(new Date()); 
		String loginUser= CanonS21SessionValidate.getUserName();
		System.out.println("in createCreditRebill LoginUser:!!! "+ loginUser);
		//Initialize S21DB-Carrier (It should be done. It leads NullPointer exception when didn't initialize.)
				//These contents are used as a default data in S21-Standard table. (Update user, time, time-zone, program id, company code)
	    EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
		/*EZDDBCICarrier.setProgID("S21EXTN_E580");*/
	    EZDDBCICarrier.setProgID("S21EXTN_E193"); 
		//Create instance of API 
		NSZC053001 s21Api = new NSZC053001();
		String res[] = new String[2];
		//Create instance of message
	//	NSZC043001PMsg pmsg = new NSZC043001PMsg();
		NSZC053001PMsg  pmsg = new NSZC053001PMsg();
		//s21Api.execute(pmsg, ONBATCH_TYPE.BATCH);
		try {
			// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
			//execute API
			//pmsg.custIncdtAsgPsnCd.setValue(loginUser);
			pmsg = getMsg(req);
			util.logMsg(false,clsName+".createCreditRebill", pmsg.toString());
			util.logMsg(false,clsName+".createServicerequest", "Calling Credit Rebill API");
			util.logMsg(false,clsName+".createCreditRebill", "Before Execution");
			s21Api.execute(pmsg, ONBATCH_TYPE.ONLINE);
			util.logMsg(false,clsName+".createCreditRebill", "After Execution");
				if (!S21ApiUtil.isXxMsgId(pmsg)) {
					// There is no message id, so can do commit the transaction.
					EZDConnectionMgr.getInstance().commit();
					System.out.println("in createCreditRebill Commit executed successfully");
					res[0]="S";
					//res[1]=pmsg.fsrNum.getValue();
					res[1]="";
					// commit	
					util.logMsg(false,clsName+".createCreditRebill", "Return Code :"+ pmsg.getReturnCode());
                 } else {
					StringBuffer sb=new StringBuffer("");
						// Error Case - S21API set some error message id when got any error while S21API's function.		
						List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
						for (String msg : err) {
							util.logMsg(true,clsName+".createCreditRebill", "Credit Rebill Creation Error :"+msg);
							sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
						}		
						res[0]="F";
						res[1]=sb.toString();
						// If S21API got error, roll-back the transaction.
							EZDConnectionMgr.getInstance().rollback(); 
						// roll-back		
						// throw new S21AbendException("S21AbendException is thrown...");
				}
			} 
			catch(ParseException pe)
			{
				System.out.println("Date parse Exception Credit Rebill API");
				res[0]="E";
				res[1]=pe.getMessage();
				util.logMsg(true,clsName+".createServicerequest", "Error in date parse : "+ pe.getMessage());
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Inside Exception Credit Rebill API");
				res[0]="E";
				res[1]=e.getMessage();
				util.logMsg(true,clsName+".createServicerequest", "Error in FSR Creation : "+ e.getMessage());
			} finally {	// Release DB resource (Close DB Connection)	
				EZDConnectionMgr.getInstance().releaseResource();
			}
		return res;
	}
	
	/**\
	 * Added By Basudev A
	 * @param req- which contains TicketId,svcCrRebilPk & svcCrRebilDtPk
	 * @return result String array
	 */
	
	public String[] cancelCreditRebill(HttpServletRequest req){
		SimpleDateFormat format = new SimpleDateFormat("z");		
		SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		String timezone = format.format(new Date());
		String invokeTimestamp = lsDateFmt.format(new Date()); 
		String loginUser= CanonS21SessionValidate.getUserName();
		System.out.println("in cancelCreditRebill LoginUser:!!! "+ loginUser);
		//Initialize S21DB-Carrier (It should be done. It leads NullPointer exception when didn't initialize.)
				//These contents are used as a default data in S21-Standard table. (Update user, time, time-zone, program id, company code)
	    EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
		EZDDBCICarrier.setProgID("S21EXTN_E193");
		//Create instance of API 
		NSZC053001 s21Api = new NSZC053001();
		String res[] = new String[2];
		//Create instance of message
		NSZC053001PMsg  pmsg = new NSZC053001PMsg();
		
		try {
			
			pmsg = getMsgForCancel(req);
			util.logMsg(false,clsName+".CancelCreeditRebill", pmsg.toString());
			util.logMsg(false,clsName+".CancelServicerequest", "Calling Credit Rebill API");
			util.logMsg(false,clsName+".CancelCreeditRebill", "Before Execution");
			s21Api.execute(pmsg, ONBATCH_TYPE.ONLINE);	
				if (!S21ApiUtil.isXxMsgId(pmsg)) {
					// There is no message id, so can do commit the transaction.
					EZDConnectionMgr.getInstance().commit();
					System.out.println("in cancelCreditRebill Commit executed successfully");
					res[0]="Y";
					//res[1]=pmsg.fsrNum.getValue();
					res[1]=pmsg.getReturnCode();
					// commit	
					util.logMsg(false,clsName+".CancelCreeditRebill", "Return Code :"+ pmsg.getReturnCode());
                 } else {
					StringBuffer sb=new StringBuffer("");
						// Error Case - S21API set some error message id when got any error while S21API's function.		
						List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
						for (String msg : err) {
							util.logMsg(true,clsName+".CancelCreeditRebill", "Credit Rebill Creation Error :"+msg);
							sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
						}		
						res[0]="E";
						res[1]=sb.toString();
						// If S21API got error, roll-back the transaction.
							EZDConnectionMgr.getInstance().rollback(); 
						// roll-back		
						// throw new S21AbendException("S21AbendException is thrown...");
				}
			} 
			catch(ParseException pe)
			{
				System.err.println("Date parse Exception Credit Rebill API");
				res[0]="E";
				res[1]=pe.getMessage();
				util.logMsg(true,clsName+".createServicerequest", "Error in date parse : "+ pe.getMessage());
			}
			catch (Exception e) {
				e.printStackTrace();
				System.err.println("Inside Exception Credit Rebill API");
				res[0]="E";
				res[1]=e.getMessage();
				util.logMsg(true,clsName+".createServicerequest", "Error in FSR Creation : "+ e.getMessage());
			} finally {	// Release DB resource (Close DB Connection)	
				EZDConnectionMgr.getInstance().releaseResource();
			}
		return res;
	}
	/**\
	 * Added By Basudev A
	 * @param req - CANCEL CREDIT REBILL PARAMETERS
	 * @return
	 * @throws ParseException
	 */
	public   NSZC053001PMsg  getMsgForCancel(HttpServletRequest req) throws ParseException{
		
		NSZC053001PMsg pmsg = new NSZC053001PMsg();
	  util.logMsg(false, clsName+".cancelCreditRebill", "CANCEL CREDIT REBILL PARAMETERS"); 
		// Mandatory Parameters for cancelCreditRebill
		 pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
		 pmsg.xxModeCd.setValue(MODE_CODE_SR_CANCEL); // Cancel Mode.
		 SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMdd");
		 String slsDt = lsDateFmt.format(new Date()); 
		  //Start Changes
		  String custIncdtId  =(String) (req.getAttribute("custIncdtId")==null?"":req.getAttribute("custIncdtId"));// "2000393";
		 int svcCrRebilPk=0;
		
		 /*int svcCrRebilDtPk=0;	
	
		 if(!(req.getAttribute("svcCrRebilDtPk")==null))
		 {
			 String crRebilDtPk=(String) req.getAttribute("svcCrRebilDtPk");
			 svcCrRebilDtPk=Integer.parseInt(crRebilDtPk);
			 
		 }*/ 	
		 if(!(req.getAttribute("svcCrRebilPk")==null))
		 {
			 String svcPk=(String) req.getAttribute("svcCrRebilPk");
			 svcCrRebilPk=Integer.parseInt(svcPk);
			 
		 } 		
		 pmsg.custIncdtId.setValue(custIncdtId);
		 pmsg.slsDt.setValue(slsDt);
		 pmsg.svcCrRebilPk.setValue(svcCrRebilPk);
		 pmsg.xxCrRebilDtlList.no(0).svcCrRebilDtlPk.setValue(svcCrRebilPk);
		 
		 return pmsg;
	}
   public  String checkNull(HttpServletRequest req, String str ){
	     String s="";
		String tmp =  req.getParameter(str);
		if (tmp != null) {
			if(tmp.trim().length()>0){
			  s=tmp.trim();
			  util.logMsg(false, clsName+".createServicerequest", str +" = "+s);
			}
		}
		return s;  
	 }
}

