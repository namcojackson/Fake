/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2080.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFBL2080.NFBL2080CMsg;
import business.blap.NFBL2080.NFBL2080Query;
import business.blap.NFBL2080.NFBL2080SMsg;
import business.blap.NFBL2080.constant.NFBL2080Constant;
import business.db.VND_INV_BOL_WRKTMsg;
import business.db.VND_INV_BOL_WRKTMsgArray;
import business.db.VND_INV_LINE_WRKTMsg;
import business.db.VND_INV_WRKTMsg;
import business.parts.NPZC110001PMsg;
import business.parts.NPZC110001_poDetailOutListPMsg;

import com.canon.cusa.s21.api.NPZ.NPZC110001.NPZC110001;
//START 2019/05/20 H.Umeda[QC#50205,ADD]
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
//END   2019/05/20 H.Umeda[QC#50205,ADD]
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
//START 2019/05/20 H.Umeda[QC#50205,ADD]
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
//END   2019/05/20 H.Umeda[QC#50205,ADD]

/** 
 *<pre>
 * AP Invoice I/F Error Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   CSAI            Y.Miyauchi      Create          N/A
 * 2016/07/28   Fujitsu         S.Yoshida       Update          QC#12555,12575
 * 2016/08/04   Fujitsu         C.Tanaka        Update          QC#12760
 * 2016/09/23   Hitachi         K.Kasai         Update          QC#14751
 * 2016/11/17   Hitachi         T.Tsuchida      Update          QC#16106
 * 2016/12/06   Fujitsu         H.Ikeda         Update          QC#15823
 * 2018/05/10   Hitachi         Y.Takeno        Update          QC#25601
 * 2018/10/10   Hitachi         J.Kim           Update          QC#28723
 * 2018/10/13   Hitachi         J.Kim           Update          QC#28723
 * 2018/11/20   Hitachi         J.Kim           Update          QC#29221
 * 2018/11/30   Hitachi         J.Kim           Update          QC#29477
 * 2019/01/09   Fujitsu         Y.Matsui        Update          QC#29884
 * 2019/03/25   Hitachi         Y.Takeno        Update          QC#30850
 * 2019/04/17   Fujitsu         H.Ikeda         Update          QC#31166
 * 2019/05/20   Hitachi         H.Umeda         Update          QC#50204,50205
 * 2019/08/16   Hitachi         Y.Takeno        Update          QC#52280
 * 2019/09/03   Hitachi         Y.Takeno        Update          QC#53078
 * 2019/10/10   Hitachi         Y.Takeno        Update          QC#54067
 * 2019/11/28   Fujitsu         H.Nagashima     Update          QC#54850
 * 2020/01/27   CITS            M.Furugoori     Update          QC#55009
 * 2020/05/26   Fujitsu         H.Mizukami      Update          QC#56007
 * 2023/10/04   CITS            M.Kobayashi     Update          QC#61880
 *</pre>
 */
public class NFBL2080CommonLogic implements NFBL2080Constant {

    /**
     * Create INV_AR_PROC_STS pulldown list.
     * @param cMsg NLAL3000CMsg
     * @param sMsg NLAL3000SMsg
     */
	@SuppressWarnings("unchecked")
    public static void createInvArProcStsPulldownList(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {

        NFBL2080Query queryObj = NFBL2080Query.getInstance();
        S21SsmEZDResult ssmResult = queryObj.findInvArProcStsList(cMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> invArProcStsList =  (List<Map<String, Object>>) ssmResult.getResultObject();
            int intListSize = invArProcStsList.size();
            if (intListSize > cMsg.invArProcStsCd.length()) {
                intListSize = cMsg.invArProcStsCd.length();
            }
            //Add Start QC#12760
            int cnt = 0;
            ZYPEZDItemValueSetter.setValue(cMsg.invArProcStsCd.no(cnt), INV_AR_PROC_STS_CD_BLANK);
            ZYPEZDItemValueSetter.setValue(cMsg.invArProcStsDescTxt.no(cnt), INV_AR_PROC_STS_DESC_TXT_BLANK);
            ZYPEZDItemValueSetter.setValue(sMsg.invArProcStsCd.no(cnt), INV_AR_PROC_STS_CD_BLANK);
            ZYPEZDItemValueSetter.setValue(sMsg.invArProcStsDescTxt.no(cnt), INV_AR_PROC_STS_DESC_TXT_BLANK);
            cnt++;
            //Add End QC#12760
            for (int i = 0; i < intListSize; i++) {
                Map<String, Object> map = (Map<String, Object>)invArProcStsList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.invArProcStsCd.no(cnt), (String) map.get(INV_AR_PROC_STS_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.invArProcStsDescTxt.no(cnt), (String) map.get(INV_AR_PROC_STS_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.invArProcStsCd.no(cnt), (String) map.get(INV_AR_PROC_STS_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.invArProcStsDescTxt.no(cnt), (String) map.get(INV_AR_PROC_STS_DESC_TXT));
                cnt++;
            }
        }
    }

	/**
     * find vendor invoice data for scrn00
     * @param cMsg NLAL3000CMsg
     * @param sMsg NLAL3000SMsg
     * @param errorIfNotFound true: display not found error message<br>
     * false: no display not error message
     */
    public static void findAndSetVndInvDataList(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg, boolean errorIfNotFound) {

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        NFBL2080Query queryObj = NFBL2080Query.getInstance();
        S21SsmEZDResult ssmResult = queryObj.findVndInvDataList(cMsg, sMsg, false);

        if (ssmResult.isCodeNormal()) {
             List<Map<String, Object>> vndInvList =  (List<Map<String, Object>>) ssmResult.getResultObject();

             // Limit
             int queryResCnt = ssmResult.getQueryResultCount();
             if(queryResCnt > sMsg.A.length()) {
                 cMsg.setMessageInfo(MSG_ID.NZZM0001W.name());
                 queryResCnt = sMsg.A.length();
             } else if (queryResCnt == 0) {
                 // START 2019/01/09 Y.Matsui [QC#29884,MOD]
                 if (errorIfNotFound) {
                     cMsg.setMessageInfo(MSG_ID.NZZM0000E.name());
                 }
                 // END   2019/01/09 Y.Matsui [QC#29884,MOD]
             } else {
                 // The search ended normally
                 cMsg.setMessageInfo(MSG_ID.ZZM8002I.name());
             }

             if (vndInvList.size() > cMsg.A.length()) {
                 cMsg.A.setValidCount(cMsg.A.length());
             } else {
                 cMsg.A.setValidCount(vndInvList.size());
             }
             sMsg.A.setValidCount(vndInvList.size());

             //Vendor Invoice Data List setting
             setVndInvoiceDataList(cMsg, sMsg, vndInvList);

             ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_P1, BigDecimal.ONE);
             ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_P1, BigDecimal.valueOf(cMsg.A.getValidCount()));
             ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_P1, BigDecimal.valueOf(queryResCnt));
             ZYPEZDItemValueSetter.setValue(sMsg.xxPageShowFromNum_P1, BigDecimal.ONE);
             ZYPEZDItemValueSetter.setValue(sMsg.xxPageShowToNum_P1, BigDecimal.valueOf(cMsg.A.getValidCount()));
             ZYPEZDItemValueSetter.setValue(sMsg.xxPageShowOfNum_P1, BigDecimal.valueOf(queryResCnt));
        } else {
            // START 2019/01/09 Y.Matsui [QC#29884,MOD]
            if (errorIfNotFound) {
                cMsg.setMessageInfo(MSG_ID.NZZM0000E.name());
            }
            // END   2019/01/09 Y.Matsui [QC#29884,MOD]
            cMsg.xxPageShowFromNum_P1.clear();
            cMsg.xxPageShowToNum_P1.clear();
            cMsg.xxPageShowOfNum_P1.clear();
            sMsg.xxPageShowFromNum_P1.clear();
            sMsg.xxPageShowToNum_P1.clear();
            sMsg.xxPageShowOfNum_P1.clear();

        }
    }
    
    /**
     * set vendor invoice data list
     * @param cMsg
     * @param vndInvList
     */
    public static void setVndInvoiceDataList(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg, List<Map<String, Object>> vndInvList) {
        int iCnt = 0;
        for (Map<String, Object> map : vndInvList) {
             //Set Grid Data
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).locNm_A1, (String) map.get(LOC_NM));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).vndInvNum_A1, (String) map.get(VND_INV_NUM));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).vndInvProcStsCd_A1, (String) map.get(VND_INV_PROC_STS_CD));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).invArProcStsCd_A1, (String) map.get(INV_AR_PROC_STS_CD));
//Mod Start QC#12575
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).poOrdNum_A1, (String) map.get(PO_ORD_NUM));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).ediPoOrdNum_A1, (String) map.get(EDI_PO_ORD_NUM));
//Mod End   QC#12575
             // START 2018/10/10 J.Kim [QC#28723,MOD]
             // ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).xxCratDt_A1, (String) map.get(INV_DT));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).xxCratDt_A1, (String) map.get(CRAT_DT));
             // END 2018/10/10 J.Kim [QC#28723,MOD]
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).invTotDealNetAmt_A1, (BigDecimal) map.get(INV_TOT_DEAL_NET_AMT));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).vndInvBolLineNum_A1, (String) map.get(VND_INV_BOL_LINE_NUM));
             sMsg.A.no(iCnt).batErrMsgTxt_AP.clear();
             sMsg.A.no(iCnt).batErrMsgTxt_AC.clear();
             sMsg.A.no(iCnt).batErrMsgTxt_AD.clear();

             // START 2018/11/30 J.Kim [QC#29477,DEL]
             // if ( iCnt < cMsg.A.getValidCount() ) {
             // END 2018/11/30 J.Kim [QC#29477,DEL]
                //Get Message Information
                 NFBL2080Query queryObj = NFBL2080Query.getInstance();
                 S21SsmEZDResult ssmResult = queryObj.findVndInvErrInfo(sMsg.A.no(iCnt).vndInvNum_A1.getValue(), sMsg.A.no(iCnt).vndInvBolLineNum_A1.getValue()
                         , null, null, null);

                 if (ssmResult.isCodeNormal()) {
                      List<Map<String, Object>> vndInvErrList =  (List<Map<String, Object>>) ssmResult.getResultObject();

                    // START 2018/11/17 J.Kim [QC#29221,DEL]
                    // if (vndInvErrList.size() >= 99) {
                    // continue;
                    // }
                    // int iCnt2 = 0;
                    // for (Map vndInvErr : vndInvErrList) {
                    // //Set Error Message Information
                    // sMsg.A.no(iCnt).batErrMsgTxt_AC.no(iCnt2).setValue(vndInvErr.get(BAT_ERR_MSG_TXT).toString());
                    // sMsg.A.no(iCnt).batErrMsgTxt_AD.no(iCnt2).setValue(vndInvErr.get(BAT_ERR_MSG_TXT).toString());
                    // iCnt2 ++;
                    // }
                    // END 2018/11/17 J.Kim [QC#29221,DEL]
                    // START 2018/11/17 J.Kim [QC#29221,ADD]
                    int iCnt2 = 0;
                    List<String> vndInvErrStrList = new ArrayList<String>();
                    for (Map<String, Object> vndInvErr : vndInvErrList) {
                        String vndInvErrMsgId = (String) vndInvErr.get(VND_INV_ERR_MSG_ID);
                        String errMsg = editErrMsg(vndInvErr);
                        if (!vndInvErrStrList.contains(vndInvErrMsgId)) {
                            sMsg.A.no(iCnt).batErrMsgTxt_AC.no(iCnt2).setValue(errMsg);
                            sMsg.A.no(iCnt).batErrMsgTxt_AD.no(iCnt2).setValue(errMsg);
                            vndInvErrStrList.add(vndInvErrMsgId);
                            iCnt2++;
                        }
                        if (iCnt2 >= 99) {
                            break;
                        }
                    }
                    // END 2018/11/17 J.Kim [QC#29221,ADD]
                 }

             // START 2018/11/30 J.Kim [QC#29477,ADD]
             if ( iCnt < cMsg.A.getValidCount() ) {
             // END 2018/11/30 J.Kim [QC#29477,ADD]
                 EZDMsg.copy(sMsg.A.no(iCnt), null, cMsg.A.no(iCnt), null);
             }

             iCnt ++;

             // START 2016/09/23 [QC#14751, MOD]
             if (iCnt >= sMsg.A.length()) {
             // END 2016/09/23 [QC#14751, MOD]
                 cMsg.setMessageInfo(MSG_ID.NZZM0001W.name());
                 return;
             }
        }
    }

    /**
     * getVndHdrInfo
     * @param cMsg
     * @param sMsg
     */
    public static void getVndHdrInfo(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg){

        int iSelectionCnt = getHdrSelectionRow(cMsg);

        NFBL2080Query queryObj = NFBL2080Query.getInstance();
        S21SsmEZDResult ssmResult = queryObj.findVndInvHeadInfo(cMsg, sMsg, iSelectionCnt);

        if (ssmResult.isCodeNormal()) {
             List<Map<String, Object>> vndInvList =  (List<Map<String, Object>>) ssmResult.getResultObject();

             ZYPEZDItemValueSetter.setValue(sMsg.locNm_H1, (String) vndInvList.get(0).get(LOC_NM));
             ZYPEZDItemValueSetter.setValue(sMsg.vndInvNum_H1, (String) vndInvList.get(0).get(VND_INV_NUM));
             ZYPEZDItemValueSetter.setValue(sMsg.soNum_H1, (String) vndInvList.get(0).get(ASN_SO_NUM));
             ZYPEZDItemValueSetter.setValue(sMsg.vndInvProcStsCd_H1, (String) vndInvList.get(0).get(VND_INV_PROC_STS_CD));
//Mod Start QC#12575
             ZYPEZDItemValueSetter.setValue(sMsg.poOrdNum_H1, (String) vndInvList.get(0).get(PO_ORD_NUM));
             ZYPEZDItemValueSetter.setValue(sMsg.ediPoOrdNum_H1, (String) vndInvList.get(0).get(EDI_PO_ORD_NUM));
//Mod End   QC#12575
             ZYPEZDItemValueSetter.setValue(sMsg.vndInvBolLineNum_H1, (String) vndInvList.get(0).get(VND_INV_BOL_LINE_NUM));
             ZYPEZDItemValueSetter.setValue(sMsg.custIssPoNum_H1, (String) vndInvList.get(0).get(CUST_ISS_PO_NUM));
           //Add Start QC#12555
             ZYPEZDItemValueSetter.setValue(sMsg.xxCratDt_H1, (String) vndInvList.get(0).get(CRAT_DT));
//Add End   QC#12555
//START 2019/05/20 H.Umeda[QC#50204,50205,ADD]
             ZYPEZDItemValueSetter.setValue(sMsg.invTpCd_H1, (String) vndInvList.get(0).get(INV_TP_CD));
             ZYPEZDItemValueSetter.setValue(sMsg.itrlIntfcId_H1, (String) vndInvList.get(0).get(ITRL_INTFC_ID));
             ZYPEZDItemValueSetter.setValue(sMsg.crDrRsnCd_H1, (String) vndInvList.get(0).get(CR_DR_RSN_CD));
             sMsg.xxInvCratFlg_H1.clear();
             if(TP_CD_CR_MEMO.equals(sMsg.invTpCd_H1.getValue())){
                 S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
                 String glblCmpyCd = profile.getGlobalCompanyCode();
                 List<String> vndCrDrRsnCdRtrnList = splitStringTxt(ZYPCodeDataUtil.getVarCharConstValue("NFBB131001_VND_CR_DR_RSN_RTRN", glblCmpyCd));
                 String CrDrRsnCd = (String)vndInvList.get(0).get(CR_DR_RSN_CD);
                 if(ZYPCommonFunc.hasValue(CrDrRsnCd)){
                     if (vndCrDrRsnCdRtrnList.contains(CrDrRsnCd)) {
                         if(INTF_ID_CUSA_WS.equals(sMsg.itrlIntfcId_H1.getValue())){
                             ZYPEZDItemValueSetter.setValue(sMsg.xxInvCratFlg_H1,INV_FLG_ON);
                         }
                     }
                 }
                 // START 2019/09/03 [QC#53078, DEL]
                 // else{
                 //     if(INTF_ID_CUSA_PARTS.equals(sMsg.itrlIntfcId_H1.getValue())){
                 //         ZYPEZDItemValueSetter.setValue(sMsg.xxInvCratFlg_H1,INV_FLG_ON);
                 //     }
                 // }
                 // END   2019/09/03 [QC#53078, DEL]
             }
//END   2019/05/20 H.Umeda[QC#50204,50205,ADD]
             NFBL2080Query queryObjMsg = NFBL2080Query.getInstance();
             // START 2018/11/30 J.Kim [QC#29477,MOD]
             //S21SsmEZDResult ssmResultMsg = queryObjMsg.findVndInvErrInfo(sMsg.A.no(iSelectionCnt).vndInvNum_A1.getValue(), sMsg.A.no(iSelectionCnt).vndInvBolLineNum_A1.getValue()
             //        , null, null, null);
             S21SsmEZDResult ssmResultMsg = queryObjMsg.findVndInvErrInfo(cMsg.A.no(iSelectionCnt).vndInvNum_A1.getValue(), cMsg.A.no(iSelectionCnt).vndInvBolLineNum_A1.getValue()
                     , null, null, null);
             // END 2018/11/30 J.Kim [QC#29477,MOD]

             if (ssmResult.isCodeNormal()) {
                  List<Map<String, Object>> vndInvErrList =  (List<Map<String, Object>>) ssmResultMsg.getResultObject();

                  // START 2018/11/17 J.Kim [QC#29221,DEL]
                  // if (vndInvErrList.size() >= 99) {
                  //     return;
                  // }
                  // END 2018/11/17 J.Kim [QC#29221,DEL]
                  int iCnt = 0;
                  // START 2016/11/17 T.Tsuchida [QC#16106,ADD]
                  sMsg.batErrMsgTxt_HP.clear();
                  sMsg.batErrMsgTxt_HC.clear();
                  sMsg.batErrMsgTxt_HD.clear();
                  // END 2016/11/17 T.Tsuchida [QC#16106,ADD]
                  cMsg.batErrMsgTxt_HP.clear();
                  cMsg.batErrMsgTxt_HC.clear();
                  cMsg.batErrMsgTxt_HD.clear();
                  // START 2018/11/17 J.Kim [QC#29221,DEL]
                  //for (Map vndInvErr : vndInvErrList) {
                  //    //Set Error Message Information
                  //    sMsg.batErrMsgTxt_HC.no(iCnt).setValue(vndInvErr.get(BAT_ERR_MSG_TXT).toString());
                  //    sMsg.batErrMsgTxt_HD.no(iCnt).setValue(vndInvErr.get(BAT_ERR_MSG_TXT).toString()); 
                  //    iCnt ++;
                  //}
                  // END 2018/11/17 J.Kim [QC#29221,DEL]
                // START 2018/11/17 J.Kim [QC#29221,ADD]
                List<String> vndInvErrStrList = new ArrayList<String>();
                for (Map<String, Object> vndInvErr : vndInvErrList) {
                    String vndInvErrMsgId = (String) vndInvErr.get(VND_INV_ERR_MSG_ID);
                    String errMsg = editErrMsg(vndInvErr);
                    if (!vndInvErrStrList.contains(vndInvErrMsgId)) {
                        sMsg.batErrMsgTxt_HC.no(iCnt).setValue(errMsg);
                        sMsg.batErrMsgTxt_HD.no(iCnt).setValue(errMsg);
                        // START 2018/11/30 J.Kim [QC#29477,MOD]
                        cMsg.batErrMsgTxt_HC.no(iCnt).setValue(errMsg);
                        cMsg.batErrMsgTxt_HD.no(iCnt).setValue(errMsg);
                        // END 2018/11/30 J.Kim [QC#29477,MOD]
                        vndInvErrStrList.add(vndInvErrMsgId);
                        iCnt++;
                    }
                    if (iCnt >= 99) {
                        break;
                    }
                }
                // END 2018/11/17 J.Kim [QC#29221,ADD]
             }

        } else {
        	cMsg.setMessageInfo(MSG_ID.NZZM0000E.name());
        }
    }

    /**
     * getVndDtlInfo
     * @param cMsg
     * @param sMsg
     */
    public static void getVndDtlInfo(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {

        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        sMsg.B.clear();
        sMsg.B.setValidCount(0);

        int iSelectionCnt = getHdrSelectionRow(cMsg);

        NFBL2080Query queryObj = NFBL2080Query.getInstance();
        S21SsmEZDResult ssmResult = queryObj.findVndInvDtlInfo(cMsg, sMsg, iSelectionCnt);

        if (ssmResult.isCodeNormal()) {
             List<Map<String, Object>> vndInvList =  (List<Map<String, Object>>) ssmResult.getResultObject();

             sMsg.B.setValidCount(vndInvList.size());
             cMsg.B.setValidCount(vndInvList.size());

             //Vendor Invoice Data List setting
             setVndInvoiceDetailList(cMsg, sMsg, vndInvList);

        } else {
            cMsg.setMessageInfo(MSG_ID.NZZM0000E.name());
        }
    }

    /**
     * setVndInvoiceDetailList
     * @param cMsg
     * @param sMsg
     * @param vndInvList
     */
    public static void setVndInvoiceDetailList(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg, List<Map<String, Object>> vndInvList) {
        int iCnt = 0;
        for (Map<String, Object> map : vndInvList) {

             // START 2020/01/27 [QC#55009, ADD]
             if (iCnt == cMsg.B.length()) {
                 cMsg.setMessageInfo(MSG_ID.NZZM0001W.name());
                 return;
             }
             // END   2020/01/27 [QC#55009, ADD]

             //Set Grid Data
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).ediPoOrdDtlLineNum_B1, (String) map.get(EDI_PO_ORD_DTL_LINE_NUM));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).soNum_B1, (String) map.get(SO_NUM));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).mdseCd_B1, (String) map.get(MDSE_CD));
             // START 2019/03/25 [QC#30850, ADD]
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).mdseCd_BK, (String) map.get(MDSE_CD));
             // END   2019/03/25 [QC#30850, ADD]
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).mdseNm_B1, (String) map.get(MDSE_NM));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).shipQty_B1, (BigDecimal) map.get(SHIP_QTY));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).dealGrsTotPrcAmt_B1, (BigDecimal) map.get(VND_INV_LINE_DEAL_NET_AMT));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).vndInvBolLineNum_B1, (String) map.get(VND_INV_BOL_LINE_NUM));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).vndInvLineNum_B1, (String) map.get(VND_INV_LINE_NUM));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).vndInvLineSubNum_B1, (String) map.get(VND_INV_LINE_SUB_NUM));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).vndInvLineSubTrxNum_B1, (String) map.get(VND_INV_LINE_SUB_TRX_NUM));
//Mod Start QC#12575
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).poOrdNum_B1, (String) map.get(PO_ORD_NUM));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).ediPoOrdNum_B1, (String) map.get(EDI_PO_ORD_NUM));
//Mod End   QC#12575

             //Get Message Information
             NFBL2080Query queryObj = NFBL2080Query.getInstance();
             // START 2019/03/27 [QC#30850, MOD]
             // START 2023/10/04 M.Kobayashi [QC#61880,MOD]
             if (!FREIGHT.equals(map.get(MDSE_CD)) && !TAX.equals(map.get(MDSE_CD)) && !MISC.equals(map.get(MDSE_CD))) {
                 S21SsmEZDResult ssmResult = queryObj.findVndInvErrInfo( map.get(VND_INV_NUM).toString(), map.get(VND_INV_BOL_LINE_NUM).toString()
                          , map.get(VND_INV_LINE_NUM).toString(), map.get(VND_INV_LINE_SUB_NUM).toString(), map.get(VND_INV_LINE_SUB_TRX_NUM).toString());
    
                 if (ssmResult.isCodeNormal()) {
                      List<Map<String, Object>> vndInvErrList =  (List<Map<String, Object>>) ssmResult.getResultObject();
    
                    // START 2018/11/17 J.Kim [QC#29221,MOD]
                    // int iCnt2 = 0;
                    // for (Map vndInvErr : vndInvErrList) {
                    //    ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt2).batErrMsgTxt_B1, (String) vndInvErr.get(BAT_ERR_MSG_TXT).toString());
                    //
                    //    iCnt2++;
                    //}
                    int digitErrMsgTxt = sMsg.B.no(iCnt).getAttr("batErrMsgTxt_B1").getDigit();
                    StringBuffer sb = new StringBuffer();
                    List<String> vndInvErrStrList = new ArrayList<String>();
                    for (Map<String, Object> vndInvErr : vndInvErrList) {
                        String vndInvErrMsgId = (String) vndInvErr.get(VND_INV_ERR_MSG_ID);
                        if (!vndInvErrStrList.contains(vndInvErrMsgId)) {
                            vndInvErrStrList.add(vndInvErrMsgId);
                            String errMsgTxt = (String) vndInvErr.get(BAT_ERR_MSG_TXT);
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(errMsgTxt);
                        }
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).batErrMsgTxt_B1, S21StringUtil.subStringByLength(sb.toString(), 0, digitErrMsgTxt));
                    // END 2018/11/19 J.Kim [QC#29221,MOD]
                 }
             }
             // END 2023/10/04 M.Kobayashi [QC#61880,MOD]
             // END   2019/03/27 [QC#30850, MOD]

             EZDMsg.copy(sMsg.B.no(iCnt), null, cMsg.B.no(iCnt), null);
             iCnt ++;

             // START 2020/01/27 [QC#55009, DEL]
             // if (iCnt > sMsg.B.length()) {
             //     cMsg.setMessageInfo(MSG_ID.NZZM0001W.name());
             //     return;
             // }
             // END   2020/01/27 [QC#55009, DEL]
        }
    }
    /**
     * getHdrSelectionRow
     * @param cMsg
     * @return
     */
    public static int getHdrSelectionRow(NFBL2080CMsg cMsg) {
        //
        for ( int iCnt=0; iCnt<cMsg.A.getValidCount(); iCnt++ ) {
            if (cMsg.A.no(iCnt).xxChkBox_A2.getValue().equals(FLG.Y.name())) {
                return iCnt;
            }
        }

        return RTRN_ERR_CD;
    }

    /**
     * updatePoHeaderInfo
     * @param cMsg
     * @param glblCmpyCd
     */
    public static void updatePoInfo(NFBL2080CMsg cMsg, String glblCmpyCd) {

        //Invoice Work Update
        VND_INV_WRKTMsg inVIWMsg = new VND_INV_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(inVIWMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inVIWMsg.vndInvNum, (String) cMsg.vndInvNum_H1.getValue());
        VND_INV_WRKTMsg resultVIMsg = (VND_INV_WRKTMsg)EZDTBLAccessor.findByKeyForUpdateNoWait(inVIWMsg);
        if (resultVIMsg == null) {
            cMsg.setMessageInfo("NZZM0003E");
            // roll back
            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
        } else {
//            resultVIMsg.invDt.setValue(cMsg.xxCratDt_H1.getValue());
            resultVIMsg.custIssPoNum.setValue(cMsg.custIssPoNum_H1.getValue());
            resultVIMsg.vndInvProcStsCd.setValue(VND_INV_PROC_STS_CD_LIST.S.name());
            //Add Start QC#12760
            resultVIMsg.invArProcStsCd.clear();
            //Add End QC#12760

            //Update
            EZDTBLAccessor.update(resultVIMsg);
        }

         //Detail Update
        // START 2016/12/06 H.Ikeda [QC#15823,ADD]
        VND_INV_LINE_WRKTMsg resultVILWMsgs[] = new VND_INV_LINE_WRKTMsg[cMsg.B.getValidCount()];
        int msgCnt = 0;
        for (int iCnt=0; iCnt<cMsg.B.getValidCount(); iCnt++) {
            // START 2016/11/17 T.Tsuchida [QC#16106,ADD]
            // START 2023/10/04 M.Kobayashi [QC#61880,MOD]
            if (FREIGHT.equals(cMsg.B.no(iCnt).mdseCd_B1.getValue())
                    || TAX.equals(cMsg.B.no(iCnt).mdseCd_B1.getValue())
                    || MISC.equals(cMsg.B.no(iCnt).mdseCd_B1.getValue())) {
                continue;
            }
            // END 2023/10/04 M.Kobayashi [QC#61880,MOD]
            // END 2016/11/17 T.Tsuchida [QC#16106,ADD]
            VND_INV_LINE_WRKTMsg inVILWMsg = new VND_INV_LINE_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(inVILWMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inVILWMsg.vndInvNum, (String) cMsg.vndInvNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(inVILWMsg.vndInvLineNum, (String) cMsg.B.no(iCnt).vndInvLineNum_B1.getValue());
            ZYPEZDItemValueSetter.setValue(inVILWMsg.vndInvBolLineNum, (String) cMsg.B.no(iCnt).vndInvBolLineNum_B1.getValue());
            ZYPEZDItemValueSetter.setValue(inVILWMsg.vndInvLineSubNum, (String) cMsg.B.no(iCnt).vndInvLineSubNum_B1.getValue());
            ZYPEZDItemValueSetter.setValue(inVILWMsg.vndInvLineSubTrxNum, (String) cMsg.B.no(iCnt).vndInvLineSubTrxNum_B1.getValue());
            VND_INV_LINE_WRKTMsg resultVILWMsg = (VND_INV_LINE_WRKTMsg)EZDTBLAccessor.findByKeyForUpdateNoWait(inVILWMsg);
            if (resultVILWMsg == null) {
                cMsg.setMessageInfo("NZZM0003E");
                // roll back
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return;
            } else {

                resultVILWMsg.shipQty.setValue(cMsg.B.no(iCnt).shipQty_B1.getValue());
                resultVILWMsg.mdseCd.setValue(cMsg.B.no(iCnt).mdseCd_B1.getValue());
                resultVILWMsg.mdseNm.setValue(cMsg.B.no(iCnt).mdseNm_B1.getValue());
                //  START 2020/05/27 [QC#56007,MOD]
                String poOrdDtlLineNum = null;
                if (ZYPCommonFunc.hasValue(cMsg.poOrdNum_H1)) {
                    poOrdDtlLineNum = getPoOrdDtlLineNum(glblCmpyCd, cMsg.poOrdNum_H1.getValue(), cMsg.B.no(iCnt).ediPoOrdDtlLineNum_B1.getValue());
                }
                if (poOrdDtlLineNum == null) {
                    resultVILWMsg.poOrdDtlLineNum.clear();
                } else {
                    String ediPoOrdDtlLineNum = resultVILWMsg.ediPoOrdDtlLineNum.getValue();
                    if (ediPoOrdDtlLineNum.length() > 3) {
                        if (!ediPoOrdDtlLineNum.substring(ediPoOrdDtlLineNum.length() - 3, ediPoOrdDtlLineNum.length()).equals(poOrdDtlLineNum)) {
                            resultVILWMsg.poOrdDtlLineNum.clear();
                        }
                    } else {
                        if (!ediPoOrdDtlLineNum.equals(poOrdDtlLineNum)) {
                            resultVILWMsg.poOrdDtlLineNum.clear();
                        }
                    }
                }
                //  END  2020/05/27 [QC#56007,MOD]
                // START 2019/11/28 H.Nagashima [QC#54850, ADD]
                //if (ZYPCommonFunc.hasValue(resultVILWMsg.poOrdDtlLineNum.getValue())) {
                //  if (!resultVILWMsg.ediPoOrdDtlLineNum.getValue().equals(cMsg.B.no(iCnt).ediPoOrdDtlLineNum_B1.getValue())) {
                //      resultVILWMsg.poOrdDtlLineNum.clear();
                //  }
                //}
                // END   2019/11/28 H.Nagashima [QC#54850, ADD]
                // START 2019/04/17 H.Ikeda [QC#31166, ADD]
                //  START 2020/05/26 [QC#56007,MOD]
                //resultVILWMsg.ediPoOrdDtlLineNum.setValue(cMsg.B.no(iCnt).ediPoOrdDtlLineNum_B1.getValue());
                if (poOrdDtlLineNum != null) {
                    resultVILWMsg.ediPoOrdDtlLineNum.setValue(poOrdDtlLineNum);
                } else {
                    resultVILWMsg.ediPoOrdDtlLineNum.setValue("");
                }
                //  END   2020/05/26 [QC#56007,MOD]
                // END   2019/04/17 H.Ikeda [QC#31166, ADD]
                
                // START 2019/03/25 [QC#30850, ADD]
                if (resultVIMsg.invTpCd == null
                        // START 2019/08/16 [QC#52280, ADD]
                        // || !((resultVIMsg.invTpCd.getValue().equals(INV_TP.INVOICE) || (resultVIMsg.invTpCd.getValue().equals(INV_TP.CREDIT_MEMO))))) {
                        || !((resultVIMsg.invTpCd.getValue().equals(INV_TP.INVOICE) || (resultVIMsg.invTpCd.getValue().equals(INV_TP.CREDIT_MEMO))
                        || (resultVIMsg.invTpCd.getValue().equals(INV_TP.DEBIT_MEMO))))) {
                        // END   2019/08/16 [QC#52280, ADD]
                    resultVILWMsgs[msgCnt] = resultVILWMsg;
                    msgCnt++;
                    continue;
                }

                if(VND_INV_LINE_SUB_NUM_000.equals(cMsg.B.no(iCnt).vndInvLineSubNum_B1.getValue())){
                    resultVILWMsgs[msgCnt] = resultVILWMsg;
                    msgCnt++;
                    continue;
                }

                if ((ZYPCommonFunc.hasValue(cMsg.poOrdNum_H1) ||  ZYPCommonFunc.hasValue(cMsg.ediPoOrdNum_H1)) 
                        && ZYPCommonFunc.hasValue(cMsg.B.no(iCnt).mdseCd_B1) && !cMsg.B.no(iCnt).mdseCd_B1.getValue().equals(cMsg.B.no(iCnt).mdseCd_BK.getValue())) {
                    NPZC110001PMsg pMsg = new NPZC110001PMsg();
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
                    ZYPEZDItemValueSetter.setValue(pMsg.ediPoOrdNum, cMsg.ediPoOrdNum_H1);
                    ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum_I, cMsg.poOrdNum_H1);
                    //  START 2020/05/26 [QC#56007,MOD]
                    //ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(0).ediPoOrdDtlLineNum, cMsg.B.no(iCnt).ediPoOrdDtlLineNum_B1);
                    poOrdDtlLineNum = getPoOrdDtlLineNum(glblCmpyCd, cMsg.poOrdNum_H1.getValue(), cMsg.B.no(iCnt).ediPoOrdDtlLineNum_B1.getValue());
                    if (poOrdDtlLineNum == null) {
                        ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(0).ediPoOrdDtlLineNum, "");
                    } else {
                        ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(0).ediPoOrdDtlLineNum, poOrdDtlLineNum);
                    }
                    //  END   2020/05/26 [QC#56007,MOD]
                    ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(0).mdseCd, cMsg.B.no(iCnt).mdseCd_B1);
                    pMsg.poDetailInList.setValidCount(1);
                    NPZC110001 apiGetPoLine = new NPZC110001();
                    apiGetPoLine.execute(pMsg, ONBATCH_TYPE.ONLINE);

                    if (ZYPCommonFunc.hasValue(pMsg.poOrdNum_O) && pMsg.poDetailOutList.getValidCount() != 0) {
                        if (ZYPCommonFunc.hasValue(pMsg.poDetailOutList.no(0).poOrdDtlLineNum) && ZYPCommonFunc.hasValue(pMsg.poDetailOutList.no(0).mdseCd)) {
                            resultVILWMsg.vndMdseCd.clear();
                        } else {
                            String errMsgId = null;
                            if (S21ApiUtil.isXxMsgId(pMsg)) {
                                List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
                                errMsgId = xxMsgIdList.get(0);
                            } else {
                                for (int i = 0; i < pMsg.poDetailOutList.getValidCount(); i++) {
                                    NPZC110001_poDetailOutListPMsg poDtlPmg = (NPZC110001_poDetailOutListPMsg) pMsg.poDetailOutList.get(i);
                                    if (ZYPCommonFunc.hasValue(poDtlPmg.xxMsgId)) {
                                        errMsgId = poDtlPmg.xxMsgId.getValue();
                                    }
                                }
                            }
                            cMsg.B.no(iCnt).mdseCd_B1.setErrorInfo(1, errMsgId);
                            cMsg.setMessageInfo("ZZM9037E");
                            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                            return;
                        }
                    } 
                }
                // END   2019/03/25 [QC#30850, ADD]

                resultVILWMsgs[msgCnt] = resultVILWMsg;
                msgCnt++;
                //Update
                //EZDTBLAccessor.update(resultVILWMsg);
            }
        }
        if (msgCnt > 0) {
            int updateCnt = S21FastTBLAccessor.update(resultVILWMsgs);
            if (updateCnt != msgCnt) {
                cMsg.setMessageInfo("NZZM0003E");
                // roll back
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return;
            }
        }

         //BOL Update
         VND_INV_BOL_WRKTMsg inBolMsg = new VND_INV_BOL_WRKTMsg();
         // START 2019/04/17 H.Ikeda [QC#31166, MOD]
         /*
         ZYPEZDItemValueSetter.setValue(inBolMsg.glblCmpyCd, glblCmpyCd);
         ZYPEZDItemValueSetter.setValue(inBolMsg.vndInvNum, (String) cMsg.vndInvNum_H1.getValue());
         ZYPEZDItemValueSetter.setValue(inBolMsg.vndInvBolLineNum, (String) cMsg.vndInvBolLineNum_H1.getValue());
         VND_INV_BOL_WRKTMsg resultBOMsg = (VND_INV_BOL_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inBolMsg);

         if (resultBOMsg == null) {
             cMsg.setMessageInfo("NZZM0003E");
             // roll back
             cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
             return;
         } else {
             resultBOMsg.ediPoOrdNum.setValue(cMsg.ediPoOrdNum_H1.getValue());
             resultBOMsg.asnSoNum.setValue(cMsg.soNum_H1.getValue());
             // START 2018/05/10 [QC#25601, ADD]
             resultBOMsg.poOrdNum.setValue(cMsg.poOrdNum_H1.getValue());
             // END   2018/05/10 [QC#25601, ADD]

                 EZDTBLAccessor.update(resultBOMsg);
         }
         */
         inBolMsg.setSQLID("001");
         inBolMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
         inBolMsg.setConditionValue("vndInvNum01", (String) cMsg.vndInvNum_H1.getValue());
         VND_INV_BOL_WRKTMsgArray resultBOMsgArr = (VND_INV_BOL_WRKTMsgArray) EZDTBLAccessor.findByCondition(inBolMsg);
         if (resultBOMsgArr != null && resultBOMsgArr.length() > 0) {
             for (int i = 0; i < resultBOMsgArr.length(); i++) {
                 VND_INV_BOL_WRKTMsg resultBOMsg = resultBOMsgArr.no(i);
                 resultBOMsg.ediPoOrdNum.setValue(cMsg.ediPoOrdNum_H1.getValue());
                 resultBOMsg.poOrdNum.setValue(cMsg.poOrdNum_H1.getValue());
                 EZDTBLAccessor.update(resultBOMsg);
             }
         } else {
             cMsg.setMessageInfo("NZZM0003E");
             // roll back
             cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
             return;
         }
         // END    2019/04/17 H.Ikeda [QC#31166, MOD]
    }

    public static void getSetTabIndex(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(sMsg.xxDplyTab, cMsg.xxDplyTab);
    }

    public static void keepSearchCondition(NFBL2080CMsg cMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.vndCd_HD, cMsg.vndCd.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.vndInvProcStsCd_HD, cMsg.vndInvProcStsCd_P1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.invArProcStsCd_HD, cMsg.invArProcStsCd_P1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.vndInvNum_HD, cMsg.vndInvNum.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.soNum_HD, cMsg.soNum.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxCratDt_HD, cMsg.xxCratDt_S1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxCratDt_HE, cMsg.xxCratDt_S2.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.ediPoOrdNum_HD, cMsg.ediPoOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.batErrMsgTxt_HE, cMsg.batErrMsgTxt.getValue());

    }

    public static void retrieveSearchCondition(NFBL2080CMsg cMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.vndCd, cMsg.vndCd_HD.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.vndInvProcStsCd_P1, cMsg.vndInvProcStsCd_HD.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.invArProcStsCd_P1, cMsg.invArProcStsCd_HD.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.vndInvNum, cMsg.vndInvNum_HD.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.soNum, cMsg.soNum_HD.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxCratDt_S1, cMsg.xxCratDt_HD.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxCratDt_S2, cMsg.xxCratDt_HE.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.ediPoOrdNum, cMsg.ediPoOrdNum_HD.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.batErrMsgTxt, cMsg.batErrMsgTxt_HE.getValue());

    }

    public static void clearHeaderTab(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {

        cMsg.locNm_H1.clear();
        cMsg.vndInvProcStsCd_H1.clear();
        cMsg.vndInvNum_H1.clear();
        cMsg.vndInvBolLineNum_H1.clear();
//Mod Start QC#12575
        cMsg.poOrdNum_H1.clear();
        cMsg.ediPoOrdNum_H1.clear();
//Mod End   QC#12575
        cMsg.soNum_H1.clear();
        cMsg.xxCratDt_H1.clear();
        cMsg.custIssPoNum_H1.clear();
        cMsg.batErrMsgTxt_HP.clear();
        // START 2019/01/09 Y.Matsui [QC#29884,ADD]
        cMsg.batErrMsgTxt_HC.clear();
        cMsg.batErrMsgTxt_HD.clear();
        // END   2019/01/09 Y.Matsui [QC#29884,ADD]

        sMsg.locNm_H1.clear();
        sMsg.vndInvProcStsCd_H1.clear();
        sMsg.vndInvNum_H1.clear();
        sMsg.vndInvBolLineNum_H1.clear();
//Mod Start QC#12575
        sMsg.poOrdNum_H1.clear();
        sMsg.ediPoOrdNum_H1.clear();
//Mod End   QC#12575
        sMsg.soNum_H1.clear();
        sMsg.custIssPoNum_H1.clear();
        sMsg.xxCratDt_H1.clear();
        sMsg.batErrMsgTxt_HP.clear();

        // START 2019/01/09 Y.Matsui [QC#29884,ADD]
        sMsg.batErrMsgTxt_HC.clear();
        sMsg.batErrMsgTxt_HD.clear();
        // END   2019/01/09 Y.Matsui [QC#29884,ADD]
    }
    
    public static void clearDetailTab(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {

        cMsg.B.clear();
        cMsg.B.setValidCount(0);

        sMsg.B.clear();
        sMsg.B.setValidCount(0);

    }
 
    // START 2018/10/12 J.Kim [QC#28723,ADD]
    /**
     * setParamFindVndInvData
     * @param cMsg NFBL2080CMsg
     * @param glblCmpyCd String 
     * @return queryPrm
     */
    public static Map<String, Object> setParamFindVndInvData(NFBL2080CMsg cMsg, String glblCmpyCd) {

        Map<String, Object> queryPrm = new HashMap<String, Object>();
        queryPrm.put("glblCmpyCd", glblCmpyCd);
        queryPrm.put("cMsg", cMsg);
        if (ZYPCommonFunc.hasValue(cMsg.xxCratDt_S1)) {
            queryPrm.put("fromDt", cMsg.xxCratDt_S1.getValue().concat("000000000"));
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxCratDt_S1)) {
            queryPrm.put("toDt", cMsg.xxCratDt_S2.getValue().concat("235959999"));
        }
        if (INV_AR_PROC_STS_CD_BLANK.equals(cMsg.invArProcStsCd_P1.getValue())) {
            queryPrm.put("blankFlg", ZYPConstant.FLG_ON_Y);
        }
        // START 2019/10/10 [QC#54067, ADD]
        queryPrm.put("invTpCd", INV_TP.CREDIT_MEMO);
        queryPrm.put("itrlIntfcId", INTF_ID_CUSA_WS);
        // END   2019/10/10 [QC#54067, ADD]
        return queryPrm;
    }

    /**
     * vndInvErrList
     * @param ssmResult S21SsmEZDResult
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> vndInvErrList(S21SsmEZDResult ssmResult) {
        return (List<Map<String, Object>>) ssmResult.getResultObject();

    }
    // END 2018/10/12 J.Kim [QC#28723,ADD]

    /**
     * Edit Error Message
     */
    public static String editErrMsg(Map<String, Object> vndInvErr) {

        // Set Error Message Information
        String errMsgTxt = (String) vndInvErr.get(BAT_ERR_MSG_TXT);
        String errMsg = errMsgTxt;
        if (errMsg.indexOf(".") >= 0) {
            errMsg = errMsgTxt.substring(0, errMsg.indexOf(".") + 1);
        }
        return errMsg;
    }

//START 2019/05/20 H.Umeda[QC#50205,ADD]
    /**
     * get array from search text if it has "," in text field.
     * @param val String
     * @return ArrayList<String>
     */
    private static ArrayList<String> splitStringTxt(String val) {

        ArrayList<String> splitValList = new ArrayList<String>();

        if (ZYPCommonFunc.hasValue(val)) {

            if (val.indexOf(",") != -1) {

                String[] splitValArray = val.split(",");

                for (int i = 0; i < splitValArray.length; i++) {

                    if (!splitValArray[i].trim().equals("") && splitValArray[i].length() > 0) {

                        splitValList.add(splitValArray[i].trim());
                    }
                }
            }
        }

        return splitValList;
    }
//END   2019/05/20 H.Umeda[QC#50205,ADD]
//  START 2020/05/26 [QC#56007,ADD]
    /**
     * get PO_DTL 
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param dispPoDtlLineNum String
     * @return String
     */
    private static String getPoOrdDtlLineNum(String glblCmpyCd, String poOrdNum, String dispPoDtlLineNum) {

        S21SsmEZDResult ssmResult = NFBL2080Query.getInstance().getPoOrdDtlLineNum(glblCmpyCd, poOrdNum, dispPoDtlLineNum);

        if (ssmResult.isCodeNormal()) {
             List<Map<String, Object>> poOrdDtlLineNumInvList =  (List<Map<String, Object>>) ssmResult.getResultObject();

             return (String) poOrdDtlLineNumInvList.get(0).get(PO_ORD_DTL_LINE_NUM);
        } else {
            return null;
        }
    }
  //  END   2020/05/26 [QC#56007,ADD]
}