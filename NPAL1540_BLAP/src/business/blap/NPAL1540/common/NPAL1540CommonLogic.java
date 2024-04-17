/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1540.common;

import static business.blap.NPAL1540.constant.NPAL1540Constant.AFT_DECL_PNT_DIGIT_NUM_DEF;
import static business.blap.NPAL1540.constant.NPAL1540Constant.API_PARAM_TIMESTAMP_FORMAT_HMS;
import static business.blap.NPAL1540.constant.NPAL1540Constant.API_PARAM_TIMESTAMP_FORMAT_YMD_HMS;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_AFT_DECL_PNT_DIGIT_NUM;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_ASL_MDSE_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_ASN_QTY;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_DEST_RTL_SWH_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_DEST_RTL_WH_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_DISP_PO_DTL_LINE_NUM;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_DPLY_VND_NM;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_INVTY_LOC_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_IN_TRANSIT_QTY;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_MDSE_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_MDSE_DESC_SHORT_TXT;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_PO_ORD_DTL_LINE_NUM;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_PO_QTY;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_PO_RCV_QTY;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_PO_STS_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_RCV_ASN_VND;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_RCV_SER_TAKE_FLG;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_RTL_WH_NM;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_SHIP_TO_CUST_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_SHPG_SVC_LVL_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_COLUMN_VND_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_ASL_MDSE_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_ASN_EDI_PROC_STS_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_ASN_SO_NUM;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_CARR_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_DISP_PO_DTL_LINE_NUM;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_ENTERED;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_ITRL_INTFC_ID;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_MAX_ROWNUM;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_NO_ENTRY;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_PO_ORD_DTL_LINE_NUM;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_PO_ORD_NUM;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_PO_STS_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_ROWNUM;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_SALES_DATE;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DB_PARAM_VND_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_ASN_MDSE_CD;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_ASN_QTY;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DISPLAY_VAL_SER_INPUT_ENTERED;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DISPLAY_VAL_SER_INPUT_NO_ENTRY;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EDI_SUB_NUM_001;
import static business.blap.NPAL1540.constant.NPAL1540Constant.ITRL_INTFC_ID_MANUAL;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM0077E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1551W;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1556E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1557E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1558E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1559E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1560E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1561E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1562E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1567E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1568E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1569E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1572E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1618E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1629E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1630E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1631E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPAM1632E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NPZM0268E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NZZM0000E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.NZZM0011E;
import static business.blap.NPAL1540.constant.NPAL1540Constant.XX_CHK_BOX_A1;
import static business.blap.NPAL1540.constant.NPAL1540Constant.ZZM8100I;
import static business.blap.NPAL1540.constant.NPAL1540Constant.ZZM9000E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1540.NPAL1540CMsg;
import business.blap.NPAL1540.NPAL1540Query;
import business.blap.NPAL1540.NPAL1540SMsg;
import business.blap.NPAL1540.NPAL1540_ASMsg;
import business.db.EDI_ASN_DTL_WRKTMsg;
import business.db.EDI_ASN_HDR_WRKTMsg;
import business.db.EDI_ASN_SER_NUM_WRKTMsg;
import business.parts.NPZC110001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC110001.NPZC110001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASN_EDI_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ELAN_PRINT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NPAL1540 Manual ASN Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/19/2016   CITS         Makoto Okigami     Create          N/A
 * 11/11/2016   CITS            M.Naito         Update          CSA
 * 02/26/2018   CITS         T.Wada             Update          QC#20445
 * 03/19/2018   CITS         K.Ogino            Update          QC#24905
 * 03/23/2018   CITS         S.Katsuma          Update          QC#24353
 * 09/13/2018   CITS         K.Ogino            Update          QC#26964/QC#26965(TST Impreso / Dietzgen PO EDI)
 * 11/08/2018   CITS        T.Hakodate          Update          QC#29031
 *</pre>
 */
public class NPAL1540CommonLogic {

    /**
     * Search
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     * @param glblCmpyCd String
     */
    public static void search(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg, String glblCmpyCd) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.S);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_ITRL_INTFC_ID, ITRL_INTFC_ID_MANUAL);
        ssmParam.put(DB_PARAM_ASN_EDI_PROC_STS_CD, new String[] {ASN_EDI_PROC_STS.PROCESSED, ASN_EDI_PROC_STS.PROCESSED_WITH_WARNING});
        ssmParam.put(DB_PARAM_NO_ENTRY, DISPLAY_VAL_SER_INPUT_NO_ENTRY);
        ssmParam.put(DB_PARAM_ENTERED, DISPLAY_VAL_SER_INPUT_ENTERED);
        ssmParam.put(DB_PARAM_MAX_ROWNUM, sMsg.A.length() + 1);
        
        //QC#29031 add start
        ssmParam.put("locStsCd", LOC_STS.IN_TRANSIT);
        //QC#29031 add end

        S21SsmEZDResult result = null;
        result = NPAL1540Query.getInstance().search(ssmParam, sMsg);

        if (!result.isCodeNormal()) {
            // START 2018/03/23 S.Katsuma [QC#24353,ADD]
            cMsg.clear();
            ZYPEZDItemValueSetter.setValue(cMsg.asnSoNum, sMsg.asnSoNum);
            ZYPEZDItemValueSetter.setValue(cMsg.asnSoNum_HD, sMsg.asnSoNum_HD);
            ZYPEZDItemValueSetter.setValue(cMsg.shipDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(sMsg.shipDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(cMsg.asnPlnDelyDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(sMsg.asnPlnDelyDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
            // END 2018/03/23 S.Katsuma [QC#24353,ADD]
            cMsg.setMessageInfo(NZZM0000E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }

        // Max Recode Over
        int queryResCnt = result.getQueryResultCount();
        if (queryResCnt > sMsg.A.length()) {
            cMsg.setMessageInfo(NPAM1551W, new String[] {String.format("%d", sMsg.A.length()), String.format("%d", sMsg.A.length())});
            queryResCnt = sMsg.A.length();
        }

        // Set Header Item
        if (sMsg.A.getValidCount() != 0) {
            ZYPEZDItemValueSetter.setValue(sMsg.asnSoNum, sMsg.A.no(0).asnSoNum_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.asnSoNum_HD, sMsg.A.no(0).asnSoNum_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.xxScrItem54Txt, sMsg.A.no(0).xxScrItem54Txt_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.xxScrItem7Txt, sMsg.A.no(0).xxScrItem7Txt_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.poOrdNum, sMsg.A.no(0).poOrdNum_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.poOrdNum_HD, sMsg.A.no(0).poOrdNum_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.vndCd, sMsg.A.no(0).vndCd_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.dplyVndNm, sMsg.A.no(0).dplyVndNm_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.shipFromSoNum, sMsg.A.no(0).shipFromSoNum_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.carrCd, sMsg.A.no(0).carrCd_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.asnBolNum, sMsg.A.no(0).asnBolNum_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.asnProNum, sMsg.A.no(0).asnProNum_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.shipDt, sMsg.A.no(0).shipDt_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.asnPlnDelyDt, sMsg.A.no(0).asnPlnDelyDt_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.asnTotFrtAmt, sMsg.A.no(0).asnTotFrtAmt_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.asnTotShipWt, sMsg.A.no(0).asnTotShipWt_AH);
            ZYPEZDItemValueSetter.setValue(sMsg.vndInvtyLocCd, sMsg.A.no(0).vndInvtyLocCd_AH);
        }

        // Copy 1 page Data(SMsg -> CMsg)
        int i = 0;
        for (; i < sMsg.A.getValidCount(); i++) {
            if (i == cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);
        ZYPEZDItemValueSetter.setValue(cMsg.asnSoNum, sMsg.asnSoNum);
        ZYPEZDItemValueSetter.setValue(cMsg.asnSoNum_HD, sMsg.asnSoNum_HD);
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem54Txt, sMsg.xxScrItem54Txt);
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem7Txt, sMsg.xxScrItem7Txt);
        ZYPEZDItemValueSetter.setValue(cMsg.poOrdNum, sMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(cMsg.poOrdNum_HD, sMsg.poOrdNum_HD);
        ZYPEZDItemValueSetter.setValue(cMsg.vndCd, sMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(cMsg.dplyVndNm, sMsg.dplyVndNm);
        ZYPEZDItemValueSetter.setValue(cMsg.shipFromSoNum, sMsg.shipFromSoNum);
        ZYPEZDItemValueSetter.setValue(cMsg.carrCd, sMsg.carrCd);
        ZYPEZDItemValueSetter.setValue(cMsg.asnBolNum, sMsg.asnBolNum);
        ZYPEZDItemValueSetter.setValue(cMsg.asnProNum, sMsg.asnProNum);
        ZYPEZDItemValueSetter.setValue(cMsg.shipDt, sMsg.shipDt);
        ZYPEZDItemValueSetter.setValue(cMsg.asnPlnDelyDt, sMsg.asnPlnDelyDt);
        ZYPEZDItemValueSetter.setValue(cMsg.asnTotFrtAmt, sMsg.asnTotFrtAmt);
        ZYPEZDItemValueSetter.setValue(cMsg.asnTotShipWt, sMsg.asnTotShipWt);
        ZYPEZDItemValueSetter.setValue(cMsg.vndInvtyLocCd, sMsg.vndInvtyLocCd);

        // Setting Next Page
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(queryResCnt);

        // Search Serial
        Map<String, Object> ssmParamSerial = new HashMap<String, Object>();
        ssmParamSerial.put(DB_PARAM_CMSG, cMsg);
        ssmParamSerial.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParamSerial.put(DB_PARAM_MAX_ROWNUM, sMsg.S.length() + 1);

        result = NPAL1540Query.getInstance().searchSerial(ssmParamSerial, sMsg);
        queryResCnt = result.getQueryResultCount();
        if (queryResCnt > sMsg.S.length()) {
            cMsg.setMessageInfo(NPAM1551W, new String[] {String.format("%d", sMsg.S.length()), String.format("%d", sMsg.S.length())});
        }
    }

    /**
     * Submit
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     * @param glblCmpyCd String
     */
    public static void submit(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg, String glblCmpyCd) {

        int maxDisplayRows = cMsg.A.length();

        if (!checkDuplicate(sMsg, glblCmpyCd)) {
            cMsg.setMessageInfo(NPAM1559E);
            return;
        }

        if (ZYPCommonFunc.hasValue(sMsg.carrCd) && !checkAsnCarrier(cMsg, sMsg, glblCmpyCd)) {
            cMsg.carrCd.setErrorInfo(1, NPAM1556E);
            return;
        }

        if (ZYPDateUtil.compare(sMsg.shipDt.getValue(), ZYPDateUtil.getSalesDate(glblCmpyCd)) > 0) {
            cMsg.shipDt.setErrorInfo(1, NPAM1568E);
            return;
        }

        if (ZYPDateUtil.compare(sMsg.asnPlnDelyDt.getValue(), sMsg.shipDt.getValue()) < 0) {
            cMsg.asnPlnDelyDt.setErrorInfo(1, NPAM1569E);
            return;
        }

        String prePoOrdDtlLineNum = null;
        BigDecimal totalShipQty = BigDecimal.ZERO;
        BigDecimal totalWeight = BigDecimal.ZERO;
        BigDecimal totalFreightCharge = BigDecimal.ZERO;
        List<NPAL1540_ASMsg> asMsgList = new ArrayList<NPAL1540_ASMsg>();

        // QC#26964/QC#26965 Start
        int errIndex = -1;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxOrdId_AH) //
                    && "SO".equals(sMsg.A.no(i).xxOrdId_AH.getValue()) //
                    && !ZYPCommonFunc.hasValue(sMsg.carrCd) && !ZYPCommonFunc.hasValue(sMsg.asnProNum) //
                    && !ZYPCommonFunc.hasValue(sMsg.asnBolNum)
                    )
                    {
                cMsg.carrCd.setErrorInfo(1, NPAM1631E);
                cMsg.asnProNum.setErrorInfo(1, NPAM1631E);
                cMsg.asnBolNum.setErrorInfo(1, NPAM1631E);
                if (errIndex == -1) {
                    errIndex = i;
                }
            }

            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).asnMdseCd_A1)) {
                sMsg.A.no(i).asnMdseCd_A1.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_ASN_MDSE_CD});
                int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum.setValue(errScrnInex);
                if (errIndex == -1) {
                    errIndex = i;
                }
            }

            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).asnQty_A1)) {
                sMsg.A.no(i).asnQty_A1.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_ASN_QTY});
                int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum.setValue(errScrnInex);
                if (errIndex == -1) {
                    errIndex = i;
                }
            } else {
                // QC#24905
                if (BigDecimal.ZERO.compareTo(sMsg.A.no(i).asnQty_A1.getValue()) >= 0) {
                    sMsg.A.no(i).asnQty_A1.setErrorInfo(1, NPAM1618E, new String[] {"Ship Quantity", "0" });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    if (errIndex == -1) {
                        errIndex = i;
                    }
                }
            }

            if (prePoOrdDtlLineNum != null && !prePoOrdDtlLineNum.equals(sMsg.A.no(i).poOrdDtlLineNum_A1.getValue())) {
                if (!checkShipQty(asMsgList, totalShipQty, glblCmpyCd)) {
                    for (NPAL1540_ASMsg asMsg : asMsgList) {
                        if (ZYPCommonFunc.hasValue(asMsg.xxOrdId_AH) //
                                && "SO".equals(asMsg.xxOrdId_AH.getValue())) {
                            asMsg.asnQty_A1.setErrorInfo(1, NPAM1632E);
                        } else {
                            asMsg.asnQty_A1.setErrorInfo(1, NPAM1572E);
                        }
                    }
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    if (errIndex == -1) {
                        errIndex = i;
                    }
                }
                totalShipQty = getValue(sMsg.A.no(i).asnQty_A1);
                asMsgList.clear();
            } else {
                totalShipQty = totalShipQty.add(getValue(sMsg.A.no(i).asnQty_A1));
            }
            asMsgList.add(sMsg.A.no(i));

            totalWeight = totalWeight.add(getValue(sMsg.A.no(i).asnTotShipWt_A1));
            totalFreightCharge = totalFreightCharge.add(getValue(sMsg.A.no(i).asnTotFrtAmt_A1));

            if (!checkItem(sMsg, sMsg.A.no(i), glblCmpyCd)) {
                int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum.setValue(errScrnInex);
                if (errIndex == -1) {
                    errIndex = i;
                }
            }

            if (!checkShipQtyAndSerQty(sMsg, sMsg.A.no(i), glblCmpyCd)) {
                int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum.setValue(errScrnInex);
                if (errIndex == -1) {
                    errIndex = i;
                }
            }

            prePoOrdDtlLineNum = sMsg.A.no(i).poOrdDtlLineNum_A1.getValue();
        }

        if (prePoOrdDtlLineNum != null) {
            if (!checkShipQty(asMsgList, totalShipQty, glblCmpyCd)) {
                for (NPAL1540_ASMsg asMsg : asMsgList) {
                    if (ZYPCommonFunc.hasValue(asMsg.xxOrdId_AH) //
                            && "SO".equals(asMsg.xxOrdId_AH.getValue())) {
                        asMsg.asnQty_A1.setErrorInfo(1, NPAM1632E);
                    } else {
                        asMsg.asnQty_A1.setErrorInfo(1, NPAM1572E);
                    }
                }
            }
        }

        if (0 <= errIndex) {
            int nextRecIdx = getPageStartRowIndexForError(errIndex, cMsg, sMsg);
            int fromNum = nextRecIdx;
            cMsg.xxPageShowFromNum.setValue(fromNum);
            NPAL1540CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
            return;
        }
        // QC#26964/QC#26965 End

        // Check Weight
        if (ZYPCommonFunc.hasValue(sMsg.asnTotShipWt) && sMsg.asnTotShipWt.getValue().compareTo(totalWeight) < 0) {
            cMsg.asnTotShipWt.setErrorInfo(1, NPAM1558E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(sMsg.asnTotFrtAmt, totalFreightCharge);
        if (!ZYPCommonFunc.hasValue(sMsg.asnTotShipWt)) {
            ZYPEZDItemValueSetter.setValue(sMsg.asnTotShipWt, totalWeight);
        }

        String systemDateTime = ZYPDateUtil.getCurrentSystemTime(API_PARAM_TIMESTAMP_FORMAT_YMD_HMS);
        String systemTime = ZYPDateUtil.getCurrentSystemTime(API_PARAM_TIMESTAMP_FORMAT_HMS);

        EDI_ASN_HDR_WRKTMsg tMsgHdr = new EDI_ASN_HDR_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsgHdr.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.asnSoNum, sMsg.asnSoNum);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.vndCd, sMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.poOrdNum, sMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.vndInvtyLocCd, sMsg.vndInvtyLocCd);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.asnTotShipWt, sMsg.asnTotShipWt);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.asnTotFrtAmt, sMsg.asnTotFrtAmt);
        if (ZYPCommonFunc.hasValue(sMsg.shipFromSoNum)) {
            ZYPEZDItemValueSetter.setValue(tMsgHdr.shipFromSoNum, sMsg.shipFromSoNum);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsgHdr.shipFromSoNum, sMsg.asnSoNum);
        }
        ZYPEZDItemValueSetter.setValue(tMsgHdr.brFlg, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.procFlg, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.asnEdiProcStsCd, ASN_EDI_PROC_STS.SAVED);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.ediRcvTs, systemDateTime);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.itrlIntfcId, ITRL_INTFC_ID_MANUAL);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.ediPoOrdNum, sMsg.A.no(0).poOrdNum_A1);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.cmProcStsCd, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.vndInvNum, sMsg.asnSoNum);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.asnShipDtTmTs, ZYPCommonFunc.concatString(sMsg.shipDt.getValue(), "", systemTime));
        ZYPEZDItemValueSetter.setValue(tMsgHdr.shipToCustCd, sMsg.A.no(0).shipToCustCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsgHdr.elanPrintStsCd, ELAN_PRINT_STS.INITIAL);

        EZDTBLAccessor.insert(tMsgHdr);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgHdr.getReturnCode())) {
            cMsg.setMessageInfo(NPAM1557E, new String[] {tMsgHdr.getTableName()});
            return;
        }

        int serialCount = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            EDI_ASN_DTL_WRKTMsg tMsgDtl = new EDI_ASN_DTL_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(tMsgDtl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsgDtl.asnSoNum, sMsg.asnSoNum);
            ZYPEZDItemValueSetter.setValue(tMsgDtl.asnLineNum, String.format("%03d", i + 1));
            ZYPEZDItemValueSetter.setValue(tMsgDtl.asnSoSlpNum, String.format("%03d", i + 1));
            ZYPEZDItemValueSetter.setValue(tMsgDtl.vndInvtyLocCd, sMsg.vndInvtyLocCd);
            ZYPEZDItemValueSetter.setValue(tMsgDtl.asnProNum, sMsg.asnProNum);
            ZYPEZDItemValueSetter.setValue(tMsgDtl.asnCarrCd, sMsg.trdPtnrCarrCd);
            ZYPEZDItemValueSetter.setValue(tMsgDtl.asnBolNum, sMsg.asnBolNum);
            ZYPEZDItemValueSetter.setValue(tMsgDtl.ediPoOrdDtlLineNum, sMsg.A.no(i).poOrdDtlLineNum_A1);
            ZYPEZDItemValueSetter.setValue(tMsgDtl.ediSubNum, EDI_SUB_NUM_001);
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).asnTotShipWt_A1)) {
                ZYPEZDItemValueSetter.setValue(tMsgDtl.asnTtlWt, sMsg.A.no(i).asnTotShipWt_A1.getValue().toPlainString());
            } else {
                ZYPEZDItemValueSetter.setValue(tMsgDtl.asnTtlWt, BigDecimal.ZERO.toPlainString());
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).asnTotFrtAmt_A1)) {
                ZYPEZDItemValueSetter.setValue(tMsgDtl.asnTotFrtAmt, sMsg.A.no(i).asnTotFrtAmt_A1);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsgDtl.asnTotFrtAmt, BigDecimal.ZERO);
            }
            ZYPEZDItemValueSetter.setValue(tMsgDtl.asnPlnDelyDt, sMsg.asnPlnDelyDt);
            ZYPEZDItemValueSetter.setValue(tMsgDtl.mdseCd, sMsg.A.no(i).mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(tMsgDtl.asnMdseCd, sMsg.A.no(i).asnMdseCd_A1);
            ZYPEZDItemValueSetter.setValue(tMsgDtl.asnQty, sMsg.A.no(i).asnQty_A1);
            ZYPEZDItemValueSetter.setValue(tMsgDtl.asnStkStsCd, STK_STS.GOOD);
            if (sMsg.A.no(i).asnMdseCd_A1.getValue().equals(sMsg.A.no(i).asnMdseCd_AD.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsgDtl.mdseCdUpdFlg, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsgDtl.mdseCdUpdFlg, ZYPConstant.FLG_ON_Y);
            }
            ZYPEZDItemValueSetter.setValue(tMsgDtl.poOrdNum, sMsg.A.no(i).poOrdNum_A1);
            ZYPEZDItemValueSetter.setValue(tMsgDtl.poOrdDtlLineNum, sMsg.A.no(i).poOrdDtlLineNum_A1);

            EZDTBLAccessor.insert(tMsgDtl);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgDtl.getReturnCode())) {
                cMsg.setMessageInfo(NPAM1557E, new String[] {tMsgDtl.getTableName()});
                return;
            }

            for (int serialPos = 0; serialPos < sMsg.S.getValidCount(); serialPos++) {
                if (sMsg.A.no(i).asnSoSlpNum_A1.getValue().equals(sMsg.S.no(serialPos).asnSoSlpNum_S1.getValue())) {
                    EDI_ASN_SER_NUM_WRKTMsg tMsgSer = new EDI_ASN_SER_NUM_WRKTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsgSer.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsgSer.soSerId, String.format("%d", serialCount + 1));
                    ZYPEZDItemValueSetter.setValue(tMsgSer.asnMdseCd, sMsg.A.no(i).asnMdseCd_A1);
                    ZYPEZDItemValueSetter.setValue(tMsgSer.asnSoNum, sMsg.asnSoNum);
                    ZYPEZDItemValueSetter.setValue(tMsgSer.asnSoSlpNum, String.format("%03d", i + 1));
                    ZYPEZDItemValueSetter.setValue(tMsgSer.mdseCd, sMsg.A.no(i).mdseCd_A1);
                    ZYPEZDItemValueSetter.setValue(tMsgSer.vndInvtyLocCd, sMsg.vndInvtyLocCd);
                    ZYPEZDItemValueSetter.setValue(tMsgSer.serNum, sMsg.S.no(serialPos).serNum_S1);
                    ZYPEZDItemValueSetter.setValue(tMsgSer.serTakeDtTmTs, ZYPCommonFunc.concatString(ZYPDateUtil.getSalesDate(glblCmpyCd), "", systemTime));
                    ZYPEZDItemValueSetter.setValue(tMsgSer.poOrdNum, sMsg.poOrdNum);
                    ZYPEZDItemValueSetter.setValue(tMsgSer.poOrdDtlLineNum, sMsg.A.no(i).poOrdDtlLineNum_A1);

                    EZDTBLAccessor.insert(tMsgSer);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgSer.getReturnCode())) {
                        cMsg.setMessageInfo(NPAM1557E, new String[] {tMsgSer.getTableName()});
                        return;
                    }
                    serialCount++;
                }
            }
        }
        cMsg.setMessageInfo(ZZM8100I);
    }

    /**
     * Apply PO
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     * @param glblCmpyCd String
     */
    public static void applyPo(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PO_ORD_NUM, cMsg.poOrdNum);
        ssmParam.put(DB_PARAM_ROWNUM, BigDecimal.ONE);
        // QC#26964/QC#26965
        ssmParam.put("tagDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmParam.put("soPrinted", SHPG_STS.S_OR_O_PRINTED);

        S21SsmEZDResult result = null;
        result = NPAL1540Query.getInstance().getPoHdr(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.poOrdNum.setErrorInfo(1, NZZM0000E);
            return;
        }

        Map record = (Map) result.getResultObject();
        if (record == null) {
            cMsg.poOrdNum.setErrorInfo(1, NZZM0000E);
            return;
        }
        // QC#26964/QC#26965
        if ("SO".equals((String) record.get("ORD_FLG"))) {
            if (!SHPG_STS.S_OR_O_PRINTED.equals((String) record.get(DB_COLUMN_PO_STS_CD))) {
                cMsg.poOrdNum.setErrorInfo(1, NPAM1629E);
                return;
            }

            if (record.get(DB_COLUMN_RCV_ASN_VND) == null) {
                cMsg.poOrdNum.setErrorInfo(1, NPAM1630E);
                return;
            }

        } else {
            if (!PO_STS.VALIDATED.equals((String) record.get(DB_COLUMN_PO_STS_CD))
                    && !PO_STS.RECEIVING.equals((String) record.get(DB_COLUMN_PO_STS_CD))
                    && !PO_STS.PO_CONFIRMED.equals((String) record.get(DB_COLUMN_PO_STS_CD))
                    && !PO_STS.PO_ERROR.equals((String) record.get(DB_COLUMN_PO_STS_CD))) {
                cMsg.poOrdNum.setErrorInfo(1, NPAM1560E);
                return;
            }

            if (record.get(DB_COLUMN_RCV_ASN_VND) == null) {
                cMsg.poOrdNum.setErrorInfo(1, NPAM1561E);
                return;
            }
        }

        ZYPEZDItemValueSetter.setValue(cMsg.vndCd, (String) record.get(DB_COLUMN_VND_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.dplyVndNm, (String) record.get(DB_COLUMN_DPLY_VND_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.poOrdNum_HD, cMsg.poOrdNum);

        ZYPEZDItemValueSetter.setValue(sMsg.vndCd, cMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(sMsg.dplyVndNm, cMsg.dplyVndNm);
        ZYPEZDItemValueSetter.setValue(sMsg.poOrdNum_HD, cMsg.poOrdNum_HD);
    }

    /**
     * Add All Line
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     * @param glblCmpyCd String
     */
    public static void addAllLine(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PO_ORD_NUM, sMsg.poOrdNum);
        ssmParam.put(DB_PARAM_PO_STS_CD, new String[] {PO_STS.VALIDATED, PO_STS.RECEIVING, PO_STS.PO_CONFIRMED, PO_STS.PO_ERROR});
        ssmParam.put(DB_PARAM_ASN_EDI_PROC_STS_CD, new String[] {ASN_EDI_PROC_STS.PROCESSED, ASN_EDI_PROC_STS.PROCESSED_WITH_WARNING});
        ssmParam.put(DB_PARAM_MAX_ROWNUM, sMsg.A.length() + 1);
        ssmParam.put("poMdseCmpsnTpCd", PO_MDSE_CMPSN_TP.PARENT);
        // QC#26964/QC#26965
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        
        // QC#29031 add start
        ssmParam.put("locStsCd", LOC_STS.IN_TRANSIT);
        // QC#29031 add end

        S21SsmEZDResult result = null;
        result = NPAL1540Query.getInstance().getPoDtl(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.poOrdNum.setErrorInfo(1, NZZM0000E);
            return;
        }

        List<Map> poDtlList = (List<Map>) result.getResultObject();
        if (poDtlList.size() == 0) {
            cMsg.poOrdNum.setErrorInfo(1, NZZM0000E);
            return;
        }

        int queryResCnt = poDtlList.size();
        if (queryResCnt > sMsg.A.length()) {
            cMsg.setMessageInfo(NPAM1551W, new String[] {String.format("%d", sMsg.A.length()), String.format("%d", sMsg.A.length())});
            queryResCnt = sMsg.A.length();
        }

        for (int i = 0; i < queryResCnt; i++) {
            Map record = poDtlList.get(i);
            // QC#26964/QC#26965
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poOrdNum_A1, (String) record.get("ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dispPoDtlLineNum_A1, (String) record.get(DB_COLUMN_DISP_PO_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poOrdDtlLineNum_A1, (String) record.get(DB_COLUMN_PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).asnMdseCd_A1, (String) record.get(DB_COLUMN_ASL_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).asnMdseCd_AD, (String) record.get(DB_COLUMN_ASL_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseCd_A1, (String) record.get(DB_COLUMN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseDescShortTxt_A1, (String) record.get(DB_COLUMN_MDSE_DESC_SHORT_TXT));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poQty_A1, (BigDecimal) record.get(DB_COLUMN_PO_QTY));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxQty10Num_A1, (BigDecimal) record.get(DB_COLUMN_IN_TRANSIT_QTY));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poRcvQty_A1, (BigDecimal) record.get(DB_COLUMN_PO_RCV_QTY));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).asnQty_A1, (BigDecimal) record.get(DB_COLUMN_ASN_QTY));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shpgSvcLvlCd_A1, (String) record.get(DB_COLUMN_SHPG_SVC_LVL_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlWhCd_A1, (String) record.get(DB_COLUMN_DEST_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A1, (String) record.get(DB_COLUMN_RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).destRtlSwhCd_A1, (String) record.get(DB_COLUMN_DEST_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).invtyLocCd_A1, (String) record.get(DB_COLUMN_INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).asnSoSlpNum_A1, String.format("%03d", i + 1));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxScrItem8Txt_A1, DISPLAY_VAL_SER_INPUT_NO_ENTRY);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipToCustCd_A1, (String) record.get(DB_COLUMN_SHIP_TO_CUST_CD));
            // QC#20445
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rcvSerTakeFlg_A1, (String) record.get(DB_COLUMN_RCV_SER_TAKE_FLG));
            // QC#26964/QC#26965
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxOrdId_AH, (String) record.get("XX_ORD_ID"));
        }
        sMsg.A.setValidCount(queryResCnt);
    }

    /**
     * Add Line
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     * @param glblCmpyCd String
     */
    public static void addLine(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg, String glblCmpyCd) {

        if (sMsg.A.getValidCount() == sMsg.A.length()) {
            cMsg.setMessageInfo(NPAM0077E, new String[] {String.format("%d", sMsg.A.length())});
            return;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PO_ORD_NUM, sMsg.poOrdNum);
        ssmParam.put(DB_PARAM_PO_STS_CD, new String[] {PO_STS.VALIDATED, PO_STS.RECEIVING, PO_STS.PO_CONFIRMED, PO_STS.PO_ERROR});
        ssmParam.put(DB_PARAM_ASN_EDI_PROC_STS_CD, new String[] {ASN_EDI_PROC_STS.PROCESSED, ASN_EDI_PROC_STS.PROCESSED_WITH_WARNING});
        ssmParam.put(DB_PARAM_DISP_PO_DTL_LINE_NUM, sMsg.dispPoDtlLineNum);
        ssmParam.put(DB_PARAM_MAX_ROWNUM, BigDecimal.ONE);
        ssmParam.put("poMdseCmpsnTpCd", PO_MDSE_CMPSN_TP.PARENT);
        // QC#26964/QC#26965
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));

        // QC#29031 add start
        ssmParam.put("locStsCd", LOC_STS.IN_TRANSIT);
        // QC#29031 add end
        S21SsmEZDResult result = null;
        result = NPAL1540Query.getInstance().getPoDtl(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.dispPoDtlLineNum.setErrorInfo(1, NPAM1567E);
            return;
        }

        List<Map> poDtlList = (List<Map>) result.getResultObject();
        if (poDtlList.size() == 0) {
            cMsg.dispPoDtlLineNum.setErrorInfo(1, NPAM1567E);
            return;
        }

        int count = sMsg.A.getValidCount();
        int maxAsnSoSlpNum = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            int asnSoSlpNum = Integer.parseInt(sMsg.A.no(i).asnSoSlpNum_A1.getValue());
            if (asnSoSlpNum > maxAsnSoSlpNum) {
                maxAsnSoSlpNum = asnSoSlpNum;
            }
        }

        Map record = poDtlList.get(0);
        // QC#26964/QC#26965
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).poOrdNum_A1, (String) record.get("ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).dispPoDtlLineNum_A1, (String) record.get(DB_COLUMN_DISP_PO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).poOrdDtlLineNum_A1, (String) record.get(DB_COLUMN_PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).asnMdseCd_A1, (String) record.get(DB_COLUMN_ASL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).asnMdseCd_AD, (String) record.get(DB_COLUMN_ASL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).mdseCd_A1, (String) record.get(DB_COLUMN_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).mdseDescShortTxt_A1, (String) record.get(DB_COLUMN_MDSE_DESC_SHORT_TXT));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).poQty_A1, (BigDecimal) record.get(DB_COLUMN_PO_QTY));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).xxQty10Num_A1, (BigDecimal) record.get(DB_COLUMN_IN_TRANSIT_QTY));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).poRcvQty_A1, (BigDecimal) record.get(DB_COLUMN_PO_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).asnQty_A1, (BigDecimal) record.get(DB_COLUMN_ASN_QTY));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).shpgSvcLvlCd_A1, (String) record.get(DB_COLUMN_SHPG_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).destRtlWhCd_A1, (String) record.get(DB_COLUMN_DEST_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).rtlWhNm_A1, (String) record.get(DB_COLUMN_RTL_WH_NM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).destRtlSwhCd_A1, (String) record.get(DB_COLUMN_DEST_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).invtyLocCd_A1, (String) record.get(DB_COLUMN_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).asnSoSlpNum_A1, String.format("%03d", maxAsnSoSlpNum + 1));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).xxScrItem8Txt_A1, DISPLAY_VAL_SER_INPUT_NO_ENTRY);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).shipToCustCd_A1, (String) record.get(DB_COLUMN_SHIP_TO_CUST_CD));
        // QC#26964/QC#26965
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).rcvSerTakeFlg_A1, (String) record.get(DB_COLUMN_RCV_SER_TAKE_FLG));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).xxOrdId_AH, (String) record.get("XX_ORD_ID"));
        sMsg.A.setValidCount(count + 1);
    }

    /**
     * Delete Row
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     * @param glblCmpyCd String
     */
    public static void deleteRow(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg, String glblCmpyCd) {

        List<Integer> delIdxList = ZYPTableUtil.getSelectedRows(sMsg.A, XX_CHK_BOX_A1, ZYPConstant.CHKBOX_ON_Y);

        if (delIdxList.size() == 0) {
            if (cMsg.A.getValidCount() == 0) {
                cMsg.setMessageInfo(NZZM0011E);
                return;
            }
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NZZM0011E);
            }
        }

        List<Integer> delSerialIndxList = new ArrayList<Integer>();
        for (Integer delIdx : delIdxList) {
            for (int i = 0; i < sMsg.S.getValidCount(); i++) {
                if (sMsg.A.no(delIdx.intValue()).asnSoSlpNum_A1.getValue().equals(sMsg.S.no(i).asnSoSlpNum_S1.getValue())) {
                    delSerialIndxList.add(Integer.valueOf(i));
                }
            }
        }

        ZYPTableUtil.deleteRows(sMsg.A, delIdxList);
        ZYPTableUtil.deleteRows(sMsg.S, delSerialIndxList);
        if (cMsg.xxPageShowFromNum.getValueInt() > sMsg.A.getValidCount()) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(sMsg.A.getValidCount() - 1));
        }
    }

    /**
     * Get CCY
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     * @param glblCmpyCd String
     */
    public static void getCcy(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_ROWNUM, BigDecimal.ONE);

        S21SsmEZDResult result = null;
        result = NPAL1540Query.getInstance().getCcy(ssmParam);

        if (!result.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxNum_C1, AFT_DECL_PNT_DIGIT_NUM_DEF);
            ZYPEZDItemValueSetter.setValue(sMsg.xxNum_C1, AFT_DECL_PNT_DIGIT_NUM_DEF);
            return;
        }

        Map record = (Map) result.getResultObject();
        if (record == null) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxNum_C1, AFT_DECL_PNT_DIGIT_NUM_DEF);
            ZYPEZDItemValueSetter.setValue(sMsg.xxNum_C1, AFT_DECL_PNT_DIGIT_NUM_DEF);
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.xxNum_C1, (BigDecimal) record.get(DB_COLUMN_AFT_DECL_PNT_DIGIT_NUM));
        ZYPEZDItemValueSetter.setValue(sMsg.xxNum_C1, (BigDecimal) record.get(DB_COLUMN_AFT_DECL_PNT_DIGIT_NUM));

    }

    /**
     * Update the global Message.
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     */
    public static void updateSMsg(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(sMsg.asnSoNum, cMsg.asnSoNum);
        ZYPEZDItemValueSetter.setValue(sMsg.poOrdNum, cMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(sMsg.carrCd, cMsg.carrCd);
        ZYPEZDItemValueSetter.setValue(sMsg.asnBolNum, cMsg.asnBolNum);
        ZYPEZDItemValueSetter.setValue(sMsg.asnProNum, cMsg.asnProNum);
        ZYPEZDItemValueSetter.setValue(sMsg.shipDt, cMsg.shipDt);
        ZYPEZDItemValueSetter.setValue(sMsg.asnPlnDelyDt, cMsg.asnPlnDelyDt);
        ZYPEZDItemValueSetter.setValue(sMsg.asnTotShipWt, cMsg.asnTotShipWt);
        ZYPEZDItemValueSetter.setValue(sMsg.vndInvtyLocCd, cMsg.vndInvtyLocCd);
        ZYPEZDItemValueSetter.setValue(sMsg.shipFromSoNum, cMsg.shipFromSoNum);
        ZYPEZDItemValueSetter.setValue(sMsg.dispPoDtlLineNum, cMsg.dispPoDtlLineNum);

        int ixG = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(ixG + i), null);
        }
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg NPAL1540CMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsg NPAL1540SMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(NPAL1540CMsg cMsg, EZDCMsgArray cMsgArray, NPAL1540SMsg sMsg, EZDSMsgArray sMsgArray) {

        ZYPTableUtil.clear(cMsgArray);

        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();

        if (sMsgArray.getValidCount() == 0) {
            cMsgArray.setValidCount(0);
            cMsg.xxPageShowFromNum.clear();
            return;
        }

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (cMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        if (startIndex == sMsgArray.getValidCount()) {
            startIndex = startIndex - cMsgArray.length();
        }
        for (; i < startIndex + cMsgArray.length(); i++) {
            if (i < sMsgArray.getValidCount()) {
                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);
            } else {
                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        cMsg.xxPageShowFromNum.setValue(startIndex + 1);
        cMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * Check Duplicate
     * @param sMsg NPAL1540SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean checkDuplicate(NPAL1540SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_ASN_SO_NUM, sMsg.asnSoNum.getValue());

        S21SsmEZDResult result = null;
        result = NPAL1540Query.getInstance().countEdiAsnHdr(ssmParam);

        if (!result.isCodeNormal()) {
            return false;
        }

        BigDecimal count = (BigDecimal) result.getResultObject();
        return (BigDecimal.ZERO.compareTo(count) == 0);
    }

    /**
     * Check ASN Carrier
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean checkAsnCarrier(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        // START 2018/03/23 S.Katsuma [QC#24353,MOD]
//        ssmParam.put(DB_PARAM_VND_CD, sMsg.vndCd.getValue());
        ssmParam.put(DB_PARAM_CARR_CD, sMsg.carrCd.getValue());
//        ssmParam.put(DB_PARAM_SHPG_SVC_LCL_CD, sMsg.A.no(0).shpgSvcLvlCd_A1.getValue());
        ssmParam.put(DB_PARAM_ROWNUM, BigDecimal.ONE);
        // END 2018/03/23 S.Katsuma [QC#24353,MOD]

        S21SsmEZDResult result = null;
        result = NPAL1540Query.getInstance().getAsnCarrCd(ssmParam);

        if (!result.isCodeNormal()) {
            return false;
        }

        String trdPtnrCarrCd = (String) result.getResultObject();
        if (trdPtnrCarrCd == null) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(sMsg.trdPtnrCarrCd, trdPtnrCarrCd);

        return true;
    }

    /**
     * Check Ship Qty
     * @param asMsgList List<NPAL1540_ASMsg>
     * @param shipQty BigDecimal
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean checkShipQty(List<NPAL1540_ASMsg> asMsgList, BigDecimal shipQty, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PO_ORD_NUM, asMsgList.get(0).poOrdNum_A1.getValue());
        ssmParam.put(DB_PARAM_PO_ORD_DTL_LINE_NUM, asMsgList.get(0).poOrdDtlLineNum_A1.getValue());
        ssmParam.put(DB_PARAM_ASN_EDI_PROC_STS_CD, new String[] {ASN_EDI_PROC_STS.PROCESSED, ASN_EDI_PROC_STS.PROCESSED_WITH_WARNING});
        ssmParam.put(DB_PARAM_ROWNUM, BigDecimal.ONE);

        // QC#29031 add start
        ssmParam.put("locStsCd", LOC_STS.IN_TRANSIT);
        // QC#29031 add end
        S21SsmEZDResult result = null;
        result = NPAL1540Query.getInstance().getPoQty(ssmParam);

        if (!result.isCodeNormal()) {
            return false;
        }

        Map record = (Map) result.getResultObject();
        if (record == null) {
            return false;
        }

        BigDecimal poQty = getDecimalValue(record, DB_COLUMN_PO_QTY);
        BigDecimal inTransitQty = getDecimalValue(record, DB_COLUMN_IN_TRANSIT_QTY);
        BigDecimal poRcvQty = getDecimalValue(record, DB_COLUMN_PO_RCV_QTY);

        BigDecimal resultQty = poQty.subtract(inTransitQty).subtract(poRcvQty);
        if (shipQty.compareTo(resultQty) > 0) {
            return false;
        }

        return true;
    }

    /**
     * Check Item
     * @param sMsg NPAL1540SMsg
     * @param asMsg NPAL1540_ASMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean checkItem(NPAL1540SMsg sMsg, NPAL1540_ASMsg asMsg, String glblCmpyCd) {

        if (asMsg.asnMdseCd_A1.getValue().equals(asMsg.asnMdseCd_AD.getValue())) {
            return true;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_ASL_MDSE_CD, asMsg.asnMdseCd_A1.getValue());
        ssmParam.put(DB_PARAM_VND_CD, sMsg.vndCd.getValue());
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmParam.put(DB_PARAM_ROWNUM, BigDecimal.ONE);

        S21SsmEZDResult result = null;
        result = NPAL1540Query.getInstance().getSupplierItem(ssmParam);

        if (!result.isCodeNormal()) {
            asMsg.asnMdseCd_A1.setErrorInfo(1, NPZM0268E);
            return false;
        }

        String mdseCd = (String) result.getResultObject();
        if (mdseCd == null) {
            asMsg.asnMdseCd_A1.setErrorInfo(1, NPZM0268E);
            return false;
        }

        NPZC110001PMsg pMsg = new NPZC110001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.ediPoOrdNum, sMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum_I, sMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(0).ediPoOrdDtlLineNum, asMsg.poOrdDtlLineNum_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(0).poOrdDtlLineNum, asMsg.poOrdDtlLineNum_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(0).mdseCd, mdseCd);
        pMsg.poDetailInList.setValidCount(1);

        NPZC110001 npzc110001 = new NPZC110001();
        npzc110001.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.poDetailOutList.getValidCount() > 0 && ZYPCommonFunc.hasValue(pMsg.poDetailOutList.no(0).xxMsgId)) {
            asMsg.asnMdseCd_A1.setErrorInfo(1, pMsg.poDetailOutList.no(0).xxMsgId.getValue());
            return false;
        }
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            asMsg.asnMdseCd_A1.setErrorInfo(1, pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return false;
        }

        return true;
    }

    /**
     * Check Ship Qty And Serial Qty
     * @param sMsg NPAL1540SMsg
     * @param asMsg NPAL1540_ASMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private static boolean checkShipQtyAndSerQty(NPAL1540SMsg sMsg, NPAL1540_ASMsg asMsg, String glblCmpyCd) {

        int serialCount = 0;
        for (int i = 0; i < sMsg.S.getValidCount(); i++) {
            if (asMsg.asnSoSlpNum_A1.getValue().equals(sMsg.S.no(i).asnSoSlpNum_S1.getValue())) {
                serialCount++;
            }
        }
        if (serialCount > asMsg.asnQty_A1.getValueInt()) {
            asMsg.xxScrItem8Txt_A1.setErrorInfo(1, NPAM1562E);
            return false;
        }
        return true;
    }

    private static BigDecimal getValue(EZDSBigDecimalItem val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val.getValue();
        } else {
            return BigDecimal.ZERO;
        }
    }

    private static BigDecimal getDecimalValue(Map<String, Object> record, String columnName) {
        BigDecimal val = (BigDecimal) record.get(columnName);
        if (val == null) {
            return BigDecimal.ZERO;
        } else {
            return val;
        }
    }

    /**
     * Add QC#26964/QC#26965. 
     * getPageStartRowIndexForError
     * @param index  int
     * @param bizMsg NPAL1540CMsg
     * @param globalMsg NPAL1540SMsg
     * @return int
     */
    public static int getPageStartRowIndexForError(int index, NPAL1540CMsg bizMsg, NPAL1540SMsg globalMsg) {
        int startIndex = 0;
        startIndex = (index / bizMsg.A.length()) * bizMsg.A.length();
        return startIndex;
    }
    
}
