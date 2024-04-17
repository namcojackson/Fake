/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2080;

import static business.blap.NFBL2080.constant.NFBL2080Constant.*;

import java.util.HashMap;
import java.util.Map;

import business.blap.NFBL2080.common.NFBL2080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * AP Invoice I/F Error Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   CSAI            Miyauchi        Create          N/A
 * 2016/07/28   Fujitsu         S.Yoshida       Update          QC#12555,12575
 * 2016/08/04   Fujitsu         C.Tanaka        Update          QC#12760
 * 2016/11/17   Hitachi         T.Tsuchida      Update          QC#16106
 * 2018/10/13   Hitachi         J.Kim           Update          QC#28723
 * 2019/10/10   Hitachi         Y.Takeno        Update          QC#54067
 * 2020/05/26   Fujitsu         H.Mizukami      Update          QC#56007
 * 2023/10/04   CITS            M.Kobayashi     Update          QC#61880
 * </pre>
 */
public class NFBL2080Query extends S21SsmEZDQuerySupport {
	 /**
     * Singleton instance.
     */
    private static final NFBL2080Query myInstance = new NFBL2080Query();

    /**
     * Constructor
     */
    public NFBL2080Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFBL2080Query
     */
    public static NFBL2080Query getInstance() {
        return myInstance;
    }

	/**
    * <pre>
    * statementId = "findVndInvList"
    * </pre>
    *
    * find Vender Invoice Data
    * @param cMsg Business Message
    * @return S21SsmEZDResult
    */
   public S21SsmEZDResult findInvArProcStsList(NFBL2080CMsg cMsg) {
       Map<String, Object> queryPrm = new HashMap<String, Object>();

       queryPrm.put("glblCmpyCd",this.getGlobalCompanyCode());

       return getSsmEZDClient().queryObjectList("findInvArProcStsList", queryPrm);
   }

	/**
    * <pre>
    * statementId = "findVndInvList"
    * </pre>
    *
    * find Vender Invoice Data
    * @param cMsg Business Message
    * @param sMsg Global Message
    * @param csvFlg true: for csv download false: for screen
    * @return S21SsmEZDResult
    */
   public S21SsmEZDResult findVndInvDataList(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg, boolean csvFlg) {

        // START 2018/10/12 J.Kim [QC#28723,MOD]
        // Map<String, Object> queryPrm = new HashMap<String,
        // Object>();
        //
        // queryPrm.put("glblCmpyCd",this.getGlobalCompanyCode());
        // queryPrm.put("cMsg",cMsg);
        //       
        // //Add Start QC#12555
        // if (ZYPCommonFunc.hasValue(cMsg.xxCratDt_S1)) {
        // queryPrm.put("fromDt",cMsg.xxCratDt_S1.getValue().concat("000000000"));
        // }
        // if (ZYPCommonFunc.hasValue(cMsg.xxCratDt_S1)) {
        // queryPrm.put("toDt",cMsg.xxCratDt_S2.getValue().concat("235959999"));
        // }
        // // Add End QC#12555
        // // Add Start QC#12760
        // if
        // (INV_AR_PROC_STS_CD_BLANK.equals(cMsg.invArProcStsCd_P1.getValue()))
        // {
        // queryPrm.put("blankFlg", ZYPConstant.FLG_ON_Y);
        // }
        // // Add End QC#12760
        Map<String, Object> queryPrm = NFBL2080CommonLogic.setParamFindVndInvData(cMsg, this.getGlobalCompanyCode());
        // END 2018/10/12 J.Kim [QC#28723,MOD]

       return getSsmEZDClient().queryObjectList("findVndInvList", queryPrm);
   }

   /**
    * <pre>
    * statementId = "findVndInvHeadInfo"
    * </pre>
    * @param
    * @return
    */
   public S21SsmEZDResult findVndInvHeadInfo(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg, int selectRowCnt) {

       Map<String, Object> queryPrm = new HashMap<String, Object>();

       queryPrm.put("glblCmpyCd",this.getGlobalCompanyCode());
       queryPrm.put("vndInvNum", cMsg.A.no(selectRowCnt).vndInvNum_A1);
       queryPrm.put("vndInvBolLineNum",  cMsg.A.no(selectRowCnt).vndInvBolLineNum_A1);
//Mod Start QC#12575
       queryPrm.put("poOrdNum", cMsg.A.no(selectRowCnt).poOrdNum_A1);
       queryPrm.put("ediPoOrdNum", cMsg.A.no(selectRowCnt).ediPoOrdNum_A1);
//Mod End   QC#12575

       return getSsmEZDClient().queryObjectList("findVndInvHeadInfo", queryPrm);
   }

   /**
    * <pre>
    * statementId = "findVndInvDtlInfo"
    * </pre>
    * @param
    * @return
    */
   public S21SsmEZDResult findVndInvDtlInfo(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg, int selectRowCnt) {

       Map<String, Object> queryPrm = new HashMap<String, Object>();

       queryPrm.put("glblCmpyCd",this.getGlobalCompanyCode());
       queryPrm.put("vndInvNum", cMsg.A.no(selectRowCnt).vndInvNum_A1);
//Mod Start QC#12575
       queryPrm.put("poOrdNum", cMsg.A.no(selectRowCnt).poOrdNum_A1);
       queryPrm.put("ediPoOrdNum", cMsg.A.no(selectRowCnt).ediPoOrdNum_A1);
//Mod End   QC#12575
       queryPrm.put("vndInvBolLineNum", cMsg.A.no(selectRowCnt).vndInvBolLineNum_A1);
       // START 2016/11/17 T.Tsuchida [QC#16106,ADD]
       queryPrm.put("freight", FREIGHT);
       queryPrm.put("tax", TAX);
       // END 2016/11/17 T.Tsuchida [QC#16106,ADD]
       // START 2023/10/04 M.Kobayashi [QC#61880,ADD]
       queryPrm.put("misc", MISC);
       // END 2023/10/04 M.Kobayashi [QC#61880,ADD]
       // START 2019/10/10 [QC#54067, ADD]
       queryPrm.put("invTpCd", INV_TP.CREDIT_MEMO);
       queryPrm.put("itrlIntfcId", INTF_ID_CUSA_WS);
       // END   2019/10/10 [QC#54067, ADD]

       //  START 2020/05/26 [QC#56007,ADD]
       if (ZYPCommonFunc.hasValue(cMsg.A.no(selectRowCnt).poOrdNum_A1)
           && ZYPCommonFunc.hasValue(cMsg.A.no(selectRowCnt).ediPoOrdNum_A1)) {
           return getSsmEZDClient().queryObjectList("findVndInvDtlInfoAndPoDtl", queryPrm);
       }
       //  END   2020/05/26 [QC#56007,ADD]
       return getSsmEZDClient().queryObjectList("findVndInvDtlInfo", queryPrm);
   }

   /**
    * <pre>
    * statementId = "findVndInvErrInfo"
    * </pre>
    * @param glblCmpyCd
    * @param vndInvNum
    * @param vndInvBolLineNum
    * @return
    */
   public S21SsmEZDResult findVndInvErrInfo(String vndInvNum, String vndInvBolLineNum, String vndInvLineNum, String vndInvLineSubNum, String vndInvLineSubTrxNum) {
       Map<String, Object> queryPrm = new HashMap<String, Object>();

       queryPrm.put("glblCmpyCd", this.getGlobalCompanyCode());
       queryPrm.put("vndInvNum", vndInvNum);
       queryPrm.put("vndInvBolLineNum", vndInvBolLineNum);
       if (vndInvLineNum != null) {
           queryPrm.put("vndInvLineNum", vndInvLineNum);
       }
       if (vndInvLineSubNum != null) {
           queryPrm.put("vndInvLineSubNum", vndInvLineSubNum);
       }
       if (vndInvLineSubTrxNum != null) {
           queryPrm.put("vndInvLineSubTrxNum", vndInvLineSubTrxNum);
       }

       return getSsmEZDClient().queryObjectList("findVndInvErrInfo", queryPrm);
   }

// START 2020/05/26 [QC#56007,ADD]
   /**
    * <pre>
    * statementId = "getPoOrdDtlLineNum"
    * </pre>
    * @param
    * @return
    */
   public S21SsmEZDResult getPoOrdDtlLineNum(String glblCmpyCd, String poOrdNum, String dispPoDtlLineNum) {

       Map<String, Object> queryPrm = new HashMap<String, Object>();

       queryPrm.put("glblCmpyCd",glblCmpyCd);
       queryPrm.put("poOrdNum", poOrdNum);
       queryPrm.put("dispPoDtlLineNum",  dispPoDtlLineNum);

       return getSsmEZDClient().queryObjectList("getPoOrdDtlLineNum", queryPrm);
   }
// START 2020/05/26 [QC#56007,ADD]
}