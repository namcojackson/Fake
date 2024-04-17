/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1150;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import static business.blap.NPAL1150.constant.NPAL1150Constant.*;
import business.blap.NPAL1150.common.NPAL1150CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASN_EDI_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

/**
 *<pre>
 * NPAL1150 ASN Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/15   Hitachi         T.Kawazu        Create          N/A
 * 2013/05/30   Hitachi         T.Kawazu        Update          QC1233
 * 2013/06/13   Hitachi         T.Tomita        Update          QC1233
 * 2013/07/19   Hitachi         T.Kawazu        Update          QC1388
 * 2013/07/24   Hitachi         T.Kawazu        Update          QC1419
 * 2013/07/30   Hitachi         A.Kohinata      Update          QC1388
 * 2017/10/27   CITS            M.Naito         Update          QC20380
 * 09/13/2018   CITS            K.Ogino         Update          QC#26964/QC#26965(TST Impreso / Dietzgen PO EDI)
 *</pre>
 */


public class NPAL1150BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NPAL1150Scrn00_Search".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_Search((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else if ("NPAL1150Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_PageNext((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else if ("NPAL1150Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_PagePrev((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else if ("NPAL1150Scrn00_SoLink".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_SoLink((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else if ("NPAL1150Scrn00_Detail".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_Detail((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else if ("NPAL1150Scrn00_Header".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_Header((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else if ("NPAL1150Scrn00_Reprocess".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_Reprocess((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else if ("NPAL1150Scrn00_Cancel".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_Cancel((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else if ("NPAL1150_INIT".equals(screenAplID)) {
                doProcess_NPAL1150_INIT((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else if ("NPAL1150Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_CMN_Reset((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else if ("NPAL1150Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_CMN_Save((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            // 2013/06/13 QC1233 T.Tomita Add Start
            } else if ("NPAL1150Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_SelectAll((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else if ("NPAL1150Scrn00_UnSelectAll".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_UnSelectAll((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            } else if ("NPAL1150Scrn00_Refresh".equals(screenAplID)) {
                doProcess_NPAL1150Scrn00_Refresh((NPAL1150CMsg) cMsg, (NPAL1150SMsg) sMsg);
            // 2013/06/13 QC1233 T.Tomita Add End
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NPAL1150Scrn00_CMN_Reset(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        doProcess_NPAL1150_INIT(cMsg, sMsg);
    }

    /**
     * doProcess_NPAL1150Scrn00_CMN_Save
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_CMN_Save(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        if (!cMsg.xxErrFlg_A1.getValue().equals(ZYPConstant.CHKBOX_ON_1)) {
            NPAL1150CommonLogic.copyFromCMsgOntoSMsg(cMsg, sMsg);
            doProcess_NPAL1150Scrn00_SoLink(cMsg, sMsg);
        }
    }

    /**
     * 
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_Cancel(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        // 2013/06/13 QC1233 T.Tomita Update Start
        setValue(cMsg.vndCd_A1, cMsg.vndCd_A2);
        setValue(cMsg.ediPoOrdNum_A1, cMsg.ediPoOrdNum_A2);
        setValue(cMsg.asnEdiProcStsCd_SV, cMsg.asnEdiProcStsCd_A2);
        setValue(cMsg.xxFromDt_A1, cMsg.xxFromDt_A2);
        setValue(cMsg.xxToDt_A1, cMsg.xxToDt_A2);
        setValue(cMsg.batErrMsgTxt_A1, cMsg.batErrMsgTxt_A2);
        // 2013/06/13 QC1233 T.Tomita Update End
        doProcess_NPAL1150Scrn00_Search(cMsg, sMsg);
        cMsg.setCommitSMsg(true);
    }

    /**
     * 
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_Reprocess(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        // 2013/06/13 QC1233 T.Tomita Update Start
        setValue(cMsg.vndCd_A1, cMsg.vndCd_A2);
        setValue(cMsg.ediPoOrdNum_A1, cMsg.ediPoOrdNum_A2);
        setValue(cMsg.asnEdiProcStsCd_SV, cMsg.asnEdiProcStsCd_A2);
        setValue(cMsg.xxFromDt_A1, cMsg.xxFromDt_A2);
        setValue(cMsg.xxToDt_A1, cMsg.xxToDt_A2);
        setValue(cMsg.batErrMsgTxt_A1, cMsg.batErrMsgTxt_A2);
        // 2013/06/13 QC1233 T.Tomita Update End
        doProcess_NPAL1150Scrn00_Search(cMsg, sMsg);
        cMsg.setCommitSMsg(true);
    }

    /**
     * doProcess_NPAL1150Scrn00_TabAckHeader
     * @param cMsg  NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_Header(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        setValue(cMsg.xxErrFlg_A1, ZYPConstant.FLG_OFF_0);
    }

    /**
     * doProcess_NPAL1150Scrn00_TabAckDetail
     * @param cMsg  NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_Detail(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        if (cMsg.B.getValidCount() > 0 && cMsg.C.getValidCount() == 0) {
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }

    /**
     * doProcess_NPAL1150Scrn00_SOLink
     * @param cMsg  NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_SoLink(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        // cMsg -> sMsg
        NPAL1150CommonLogic.copyFromCMsgOntoSMsg(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        // 2013/06/13 QC1233 T.Tomita Add Start
        ZYPTableUtil.clear(sMsg.C);
        setValue(cMsg.xxChkBox_H1, ZYPConstant.FLG_OFF_N);
        // 2013/06/13 QC1233 T.Tomita Add End
        setValue(cMsg.xxErrFlg_A1, ZYPConstant.FLG_OFF_0);

        // UNDER HEADER TAB Data Get
        S21SsmEZDResult resultHeader = NPAL1150Query.getInstance().getSoLinkHeader(cMsg, sMsg);
        if (!resultHeader.isCodeNormal()) {
            cMsg.setMessageInfo(ZZZM9001E);
            cMsg.B.setValidCount(0);
            return;
        }
        // 2013/07/30 QC1388 A.Kohinata Update Start
        //editSoLinkHeader(cMsg, sMsg);
        editSoLinkHeader(cMsg, sMsg, resultHeader);
        // 2013/07/30 QC1388 A.Kohinata Update End
        // UNDER DETAIL TAB Data Get
        S21SsmEZDResult resultDetail = NPAL1150Query.getInstance().getSoLinkDetail(cMsg, sMsg);
        if (resultDetail == null) {
            return;
        }
        // 2013/06/13 QC1233 T.Tomita Update Start
//        editSoLinkDetail(cMsg, resultDetail);
        editSoLinkDetail(cMsg, sMsg, resultDetail);
        // 2013/06/13 QC1233 T.Tomita Update End
    }

    // 2013/06/13 QC1233 T.Tomita Update Start
    /**
     * @param cMsg          NPAL1150CMsg
     * @param sMsg          NPAL1150SMsg
     * @param resultDetail  S21SsmEZDResult
     */
    private void editSoLinkDetail(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg, S21SsmEZDResult resultDetail) {
        @SuppressWarnings("unchecked")
        List<Map <String, Object>> resultList = (List<Map <String, Object>>) resultDetail.getResultObject();

        // No Data
        if (resultList.size() == 0) {
            return;
        }

        // result  ->  cMsg
        String  wkAsnLineNum = "";
        String  wkPoOrdDtlLineNum = "";
        String  wkDispPoOrdDtlLineNum = "";
        int detailLineCnt = 0;
        int batErrMsgTxt = 0;
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> map = (Map<String, Object>) resultList.get(i);
            if (!wkAsnLineNum.equals((String) map.get("ASN_LINE_NUM"))) {
                wkAsnLineNum = (String) map.get("ASN_LINE_NUM");
                batErrMsgTxt = 0;

                NPAL1150_CSMsg dMsg = sMsg.C.no(detailLineCnt);
                // UPDATE QC1388 START
                if (ZYPCommonFunc.hasValue((String) map.get("PO_ORD_DTL_LINE_NUM"))) {
                    wkPoOrdDtlLineNum = (String) map.get("PO_ORD_DTL_LINE_NUM");
                } else {
                    wkPoOrdDtlLineNum = "";
                }
                if (wkPoOrdDtlLineNum.length() > LINE_NUM_LENGTH) {
                    wkDispPoOrdDtlLineNum = wkPoOrdDtlLineNum.substring(wkPoOrdDtlLineNum.length() - LINE_NUM_LENGTH , wkPoOrdDtlLineNum.length());
                } else {
                    wkDispPoOrdDtlLineNum = wkPoOrdDtlLineNum;
                }
                setValue(dMsg.dispPoDtlLineNum_D1, wkDispPoOrdDtlLineNum);
                setValue(dMsg.dispPoDtlLineNum_D2, wkDispPoOrdDtlLineNum);
                // UPDATE QC1388 END
                setValue(dMsg.dispPoDtlLineNum_HD, (String) map.get("HDN_PO_ORD_DTL_LINE_NUM"));
// Delete Start QC1233
//              setValue(dMsg.ediNum_D1, (String) map.get("EDI_NUM"));
// Delete End   QC1233
// Add Start QC1233
                setValue(dMsg.ediPoOrdDtlLineNum_D1, (String) map.get("EDI_PO_ORD_DTL_LINE_NUM"));
                setValue(dMsg.ediPoOrdDtlLineNum_D2, (String) map.get("EDI_PO_ORD_DTL_LINE_NUM"));
// Add End   QC1233
                setValue(dMsg.ediPoOrdDtlLineNum_HD, (String) map.get("HDN_EDI_PO_ORD_DTL_LINE_NUM"));
                setValue(dMsg.asnSoNum_D1, (String) map.get("ASN_SO_NUM"));
                setValue(dMsg.asnLineNum_D1, (String) map.get("ASN_LINE_NUM"));
                setValue(dMsg.asnSoSlpNum_D1, (String) map.get("ASN_SO_SLP_NUM"));
                setValue(dMsg.mdseCd_D1, (String) map.get("MDSE_CD"));
                setValue(dMsg.mdseCdUpdFlg_D1, (String) map.get("MDSE_CD_UPD_FLG"));
                setValue(dMsg.asnQty_D1, (BigDecimal) map.get("ASN_QTY"));
                // Delete QC1388 Start
                //setValue(dMsg.asnVndCd_D1, (String) map.get("ASN_VND_CD"));
                // Delete QC1388 End
                // Delete QC1388 Start
                setValue(dMsg.asnCarrCd_D1, (String) map.get("ASN_CARR_CD"));
                // Delete QC1388 Start
                setValue(dMsg.asnProNum_D1, (String) map.get("ASN_PRO_NUM"));
                setValue(dMsg.asnBolNum_D1, (String) map.get("ASN_BOL_NUM"));
                setValue(dMsg.asnPlnDelyDt_D1, (String) map.get("ASN_PLN_DELY_DT"));
                setValue(dMsg.ezUpTime_DD, (String) map.get("DEZUPTIME"));
                setValue(dMsg.ezUpTimeZone_DD, (String) map.get("DEZUPTIMEZONE"));
                if (ZYPCommonFunc.hasValue((String) map.get("BAT_ERR_MSG_TXT"))) {
                    setValue(dMsg.batErrMsgTxt_DD.no(batErrMsgTxt), (String) map.get("BAT_ERR_MSG_TXT"));
                    setValue(dMsg.batErrMsgTxt_DC.no(batErrMsgTxt), String.valueOf(batErrMsgTxt));
                    batErrMsgTxt++;
                }
                setValue(dMsg.xxNum_D1, new BigDecimal(batErrMsgTxt));

                detailLineCnt++;
// Delete Start QC1233
//              setValue(dMsg.custIssPoNum_D1, (String) map.get("CUST_ISS_PO_NUM"));
// Delete End   QC1233
            } else {
                if (ZYPCommonFunc.hasValue((String) map.get("BAT_ERR_MSG_TXT"))) {
                    NPAL1150_CSMsg dMsg = sMsg.C.no(detailLineCnt - 1);
                    setValue(dMsg.batErrMsgTxt_DD.no(batErrMsgTxt), (String) map.get("BAT_ERR_MSG_TXT"));
                    setValue(dMsg.batErrMsgTxt_DC.no(batErrMsgTxt), String.valueOf(batErrMsgTxt));
                    batErrMsgTxt++;
                    setValue(dMsg.xxNum_D1, new BigDecimal(batErrMsgTxt));
                }
            }
        }
        sMsg.C.setValidCount(detailLineCnt);

        // Copy from sMsg.C to cMsg.C
        NPAL1150CommonLogic.detailListDisp(cMsg, sMsg);
    }
    // 2013/06/13 QC1233 T.Tomita Update End

    /**
     * 
     * @param msg  NPAL1150CMsg
     * @param sMsg S21SsmEZDResult
     * @param resultHeader  S21SsmEZDResult
     */
    // 2013/07/30 QC1388 A.Kohinata Update Start
    //private void editSoLinkHeader(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
    private void editSoLinkHeader(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg, S21SsmEZDResult resultHeader) {
    // 2013/07/30 QC1388 A.Kohinata Update End

        if (sMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(ZZZM9001E);
            cMsg.B.setValidCount(0);
            return;
        }

        // 2013/07/30 QC1388 A.Kohinata Add Start
        @SuppressWarnings("unchecked")
        List<Map <String, Object>> resultList = (List<Map <String, Object>>) resultHeader.getResultObject();
        // No Data
        if (resultList.size() == 0) {
            cMsg.B.setValidCount(0);
            return;
        }

        Map<String, Object> map = (Map<String, Object>) resultList.get(0);
        // 2017/10/27 QC20380 M.Naito Mod Start
//        setValue(cMsg.B.no(0).vndNm_H1, (String) map.get("VND_NM"));
        setValue(cMsg.B.no(0).dplyVndNm_H1, (String) map.get("VND_NM"));
        // 2017/10/27 QC20380 M.Naito Mod End
        setValue(cMsg.B.no(0).asnEdiProcStsNm_H1, (String) map.get("ASN_EDI_PROC_STS_NM"));
        setValue(cMsg.B.no(0).ediRcvDateTs_H1, (String) map.get("EDI_RCV_TS"));
        setValue(cMsg.B.no(0).asnSoNum_H1, (String) map.get("ASN_SO_NUM"));
        setValue(cMsg.B.no(0).ediPoOrdNum_H1, (String) map.get("EDI_PO_ORD_NUM"));
        setValue(cMsg.B.no(0).vndInvtyLocCd_H1, (String) map.get("VND_INVTY_LOC_CD"));
        setValue(cMsg.B.no(0).asnShipDtTmTs_H1, (String) map.get("ASN_SHIP_DT_TM_TS"));
        setValue(cMsg.B.no(0).shipFromSoNum_H1, (String) map.get("SHIP_FROM_SO_NUM"));
        setValue(cMsg.B.no(0).poOrdNum_H1, (String) map.get("PO_ORD_NUM"));
        setValue(cMsg.B.no(0).vndCd_H1, (String) map.get("VND_CD"));
        setValue(cMsg.B.no(0).itrlIntfcId_H1, (String) map.get("ITRL_INTFC_ID"));
        int errMsgCnt = 0;
        for (int i = 0; i < resultList.size(); i++) {
            map = (Map<String, Object>) resultList.get(i);
            if (ZYPCommonFunc.hasValue((String) map.get("BAT_ERR_MSG_TXT"))) {
                setValue(cMsg.B.no(0).batErrMsgTxt_HD.no(errMsgCnt), (String) map.get("BAT_ERR_MSG_TXT"));
                setValue(cMsg.B.no(0).batErrMsgTxt_HC.no(errMsgCnt), String.valueOf(i));
                errMsgCnt++;
            }
        }
        cMsg.B.setValidCount(1);
        // 2013/07/30 QC1388 A.Kohinata Add End

        //  ediRcvDateTs   Edit mm/dd/yyyy hh:mm:ss
        if (ZYPCommonFunc.hasValue(cMsg.B.no(0).ediRcvDateTs_H1)) {
            setValue(cMsg.B.no(0).xxTsDsp19Txt_HR, NPAL1150CommonLogic.formatDateTs(cMsg.B.no(0).ediRcvDateTs_H1.getValue()));
        }
        //  asnShipDtTmTs  Edit mm/dd/yyyy hh:mm:ss
        if (ZYPCommonFunc.hasValue(cMsg.B.no(0).asnShipDtTmTs_H1)) {
            setValue(cMsg.B.no(0).xxTsDsp19Txt_HS, NPAL1150CommonLogic.formatDateTs(cMsg.B.no(0).asnShipDtTmTs_H1.getValue()));
        }
    }

    /**
     * doProcess_NPAL1150Scrn00_Search
     * @param cMsg  NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_Search(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        // Date Check
        if (ZYPCommonFunc.hasValue(cMsg.xxFromDt_A1) && ZYPCommonFunc.hasValue(cMsg.xxToDt_A1)) {
            if (cMsg.xxFromDt_A1.getValue().compareTo(cMsg.xxToDt_A1.getValue()) > 0) {
                cMsg.xxFromDt_A1.setErrorInfo(1, NPAM0225E, new String[] {DISP_ITEM_NAME_RCV_DATE_FROM });
                cMsg.xxToDt_A1.setErrorInfo(1, NPAM0225E, new String[] {DISP_ITEM_NAME_RCV_DATE_TO });
                return;
            }
        }

        // ACMsg clear, ASMsg clear,
        setValue(cMsg.xxErrFlg_A1, ZYPConstant.FLG_OFF_0);
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(sMsg.A);
        // 2013/06/13 QC1233 T.Tomita Add Start
        cMsg.xxNum.clear();
        // 2013/06/13 QC1233 T.Tomita Add End
        // 2013/06/13 QC1233 T.Tomita Update Start
        setValue(cMsg.vndCd_A2, cMsg.vndCd_A1);
        setValue(cMsg.ediPoOrdNum_A2, cMsg.ediPoOrdNum_A1);
        setValue(cMsg.asnEdiProcStsCd_A2, cMsg.asnEdiProcStsCd_SV);
        setValue(cMsg.xxFromDt_A2, cMsg.xxFromDt_A1);
        setValue(cMsg.xxToDt_A2, cMsg.xxToDt_A1);
        setValue(cMsg.batErrMsgTxt_A2, cMsg.batErrMsgTxt_A1);
        // 2013/06/13 QC1233 T.Tomita Update End
        S21SsmEZDResult ssmResult = NPAL1150Query.getInstance().getEdiAsnErrorList(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            if (sMsg.A.getValidCount() == 0) {
                cMsg.setMessageInfo(ZZZM9001E);
                cMsg.xxPageShowFromNum.setValue(0);
                cMsg.xxPageShowToNum.setValue(0);
                cMsg.xxPageShowOfNum.setValue(0);
                return;
            } else {
                // Clear Message Info
                cMsg.setMessageInfo(null);
            }

            // 1000  Over
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(ZZZM9002W);
            }

            // sMsg Data Edit
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                //  Edit mm/dd/yyyy hh:mm:ss
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).ediRcvDateTs_B1)) {
                    setValue(sMsg.A.no(i).xxTsDsp19Txt_BR, NPAL1150CommonLogic.formatDateTs(sMsg.A.no(i).ediRcvDateTs_B1.getValue()));
                }
                //  Edit mm/dd/yyyy hh:mm:ss
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).asnShipDtTmTs_B1)) {
                    setValue(sMsg.A.no(i).xxTsDsp19Txt_BS, NPAL1150CommonLogic.formatDateTs(sMsg.A.no(i).asnShipDtTmTs_B1.getValue()));
                }
            }

            // 1page copy（SMsg -> CMsg）
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum.setValue(BigDecimal.ONE);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

            cMsg.setMessageInfo(NPAM0005I);

        } else {
            cMsg.setMessageInfo(ZZZM9001E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * doProcess_NPAL1150_INIT
     * @param cMsg  NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150_INIT(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {

        // All items clear.
        // QC#26964/QC#26965
        String ediPoOrdNum = "";
        if (ZYPCommonFunc.hasValue(cMsg.ediPoOrdNum_A2)) {
            ediPoOrdNum = cMsg.ediPoOrdNum_A2.getValue();
        }

        cMsg.clear();
        cMsg.A.setValidCount(0);
        cMsg.B.setValidCount(0);
        cMsg.C.setValidCount(0);

        // make ASN_EDI_PROC_STS Source Pull down
        ZYPCodeDataUtil.createPulldownList(ASN_EDI_PROC_STS.class, cMsg.asnEdiProcStsCd_CD, cMsg.asnEdiProcStsNm_DI);
        cMsg.asnEdiProcStsCd_SV.setValue(ASN_EDI_PROC_STS.ERROR);

        // QC#26964/QC#26965
        if (ZYPCommonFunc.hasValue(ediPoOrdNum)) {
            ZYPEZDItemValueSetter.setValue(cMsg.ediPoOrdNum_A1, ediPoOrdNum);
            doProcess_NPAL1150Scrn00_Search(cMsg, sMsg);
        } else {
            // Page information Initialize
            cMsg.xxPageShowFromNum.setValue(0);
            cMsg.xxPageShowToNum.setValue(0);
            cMsg.xxPageShowOfNum.setValue(0);
        }
    }

    /**
     * <pre>
     * PagePrev Event
     * </pre>
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_PagePrev(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {

        // cMsg -> sMsg
        NPAL1150CommonLogic.copyFromCMsgOntoSMsg(cMsg, sMsg);
        // set values to items of pageNation for previous page
        // pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        cMsg.xxPageShowToNum.clear();
        NPAL1150CommonLogic.copyFromSMsgOntoCMsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * PageNext Event
     * </pre>
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_PageNext(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {

        // cMsg -> sMsg
        NPAL1150CommonLogic.copyFromCMsgOntoSMsg(cMsg, sMsg);

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt() + 1);
        cMsg.xxPageShowToNum.clear();
        NPAL1150CommonLogic.copyFromSMsgOntoCMsg(cMsg, sMsg);
    }

    // 2013/06/13 QC1233 T.Tomita Add Start
    /**
     * <pre>
     * SelectAll Event
     * </pre>
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_SelectAll(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).openAsnWrkFlg_B1.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_B1, ZYPConstant.FLG_ON_Y);
            }
        }

        ZYPTableUtil.clear(cMsg.A);
        NPAL1150CommonLogic.copyFromSMsgOntoCMsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * UnSelectAll Event
     * </pre>
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_UnSelectAll(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_B1, ZYPConstant.FLG_OFF_N);
        }

        ZYPTableUtil.clear(cMsg.A);
        NPAL1150CommonLogic.copyFromSMsgOntoCMsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * Refresh Event
     * </pre>
     * @param cMsg NPAL1150CMsg
     * @param sMsg NPAL1150SMsg
     */
    private void doProcess_NPAL1150Scrn00_Refresh(NPAL1150CMsg cMsg, NPAL1150SMsg sMsg) {
        // Copy from cMsg.C to sMsg.C
        NPAL1150CommonLogic.copyFromCCListOntoSCList(cMsg, sMsg);
        ZYPTableUtil.clear(cMsg.C);
        // Copy from sMsg.C to CMsg.C
        NPAL1150CommonLogic.detailListDisp(cMsg, sMsg);
    }
    // 2013/06/13 QC1233 T.Tomita Add End
}
