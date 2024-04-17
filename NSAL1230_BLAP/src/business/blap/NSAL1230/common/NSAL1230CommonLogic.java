/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1230.common;

import static business.blap.NSAL1230.constant.NSAL1230Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import business.blap.NSAL1230.NSAL1230CMsg;
import business.blap.NSAL1230.NSAL1230Query;
import business.blap.NSAL1230.NSAL1230SMsg;
import business.blap.NSAL1230.NSAL1230_ACMsg;
import business.blap.NSAL1230.NSAL1230_ACMsgArray;
import business.db.DS_CONTR_BR_ALLOCTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/23   Hitachi         Y.Takeno        Update          QC#2728
 * 2016/07/29   Hitachi         T.Tomita        Update          QC#11572
 * 2016/08/04   Hitachi         T.Mizuki        Update          QC#12767
 * 2016/12/02   Hitachi         K.Kojima        Update          QC#14204
 * 2018/04/10   CITS            T.Wada          Update          QC#23378(Sol#320)
 *</pre>
 */
public class NSAL1230CommonLogic {

    /**
     * Validate checkbox.
     * @param cMsg NSAL1230CMsg cMsg
     * @return true/false
     */
    public static boolean validateCheckBox(NSAL1230CMsg cMsg) {
        boolean rtnVal = true;

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(cMsg.A, XX_CHK_BOX_A1, ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            rtnVal = false;
        }
        return rtnVal;
    }

    /**
     * Validate Segment string.
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL1230CMsg
     * @return true/false
     */
    public static boolean validateSegmentString(String glblCmpyCd, NSAL1230CMsg cMsg) {

        boolean result = true;
        String delimiter = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DELIMITER, glblCmpyCd);

        for (int index = 0; index < cMsg.A.getValidCount(); index++) {
            NSAL1230_ACMsg aCMsg = cMsg.A.no(index);
            String segmentString = aCMsg.coaAfflAcctNm_A1.getValue();
            // START 2016/07/29 T.Tomita [QC#11572, MOD]
//            StringTokenizer tokenizer = new StringTokenizer(segmentString, delimiter);
            String[] list = segmentString.split(Pattern.quote(delimiter), -1);
            List<String> tokenList = new ArrayList<String>();

//            while (tokenizer.hasMoreTokens()) {
//                String token = tokenizer.nextToken();
//                tokenList.add(token);
//            }
            for (String val : list) {
                tokenList.add(val);
            }
            // END 2016/07/29 T.Tomita [QC#11572, MOD]

            // START 2016/03/23 Y.Takeno [QC#2728, MOD]
            validateSegmentStringTokenList(glblCmpyCd, cMsg.A.no(index), tokenList);
            // END   2016/03/23 Y.Takeno [QC#2728, MOD]
        }

        return result;
    }

// START 2016/03/23 Y.Takeno [QC#2728, ADD]
    /**
     * Validate Segment string.
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL1230CMsg
     * @param index index
     * @return true/false
     */
    public static boolean validateSegmentString(String glblCmpyCd, NSAL1230CMsg cMsg, int index) {

        String delimiter = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DELIMITER, glblCmpyCd);

        if (index > cMsg.A.getValidCount()) {
            return false;
        }

        NSAL1230_ACMsg aCMsg = cMsg.A.no(index);

        if (!hasValue(aCMsg.coaAfflAcctNm_A1)) {
            return true;
        }
        String segmentString = aCMsg.coaAfflAcctNm_A1.getValue();
        // START 2016/07/29 T.Tomita [QC#11572, MOD]
//        StringTokenizer tokenizer = new StringTokenizer(segmentString, delimiter);
        String[] list = segmentString.split(Pattern.quote(delimiter), -1);
        List<String> tokenList = new ArrayList<String>();

//        while (tokenizer.hasMoreTokens()) {
//            String token = tokenizer.nextToken();
//            tokenList.add(token);
//        }
        for (String val : list) {
            tokenList.add(val);
        }
        // END 2016/07/29 T.Tomita [QC#11572, MOD]

        return validateSegmentStringTokenList(glblCmpyCd, cMsg.A.no(index), tokenList);
    }

    /**
     * Validate Segment string token list.
     * 
     * @param glblCmpyCd glblCmpyCd
     * @param aCMsg NSAL1230CMsg
     * @param tokenList tokenList
     * @return true/false
     */
    public static boolean validateSegmentStringTokenList(String glblCmpyCd, NSAL1230_ACMsg aCMsg, List<String> tokenList) {

        if (tokenList.size() != SEGMENT_TOKEN_LIST_SIZE) {
            aCMsg.coaAfflAcctNm_A1.setErrorInfo(1, NSAM0206E, new String[] {MSG_PARAM_SEGMENT });
            return false;
        }

        // START 2016/07/29 T.Tomita [QC#11572, MOD]
//        if (!validateSegmentElementCoaCmpyCd(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD))) {
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY)) {
            aCMsg.coaAfflAcctNm_A1.setErrorInfo(1, NSAM0209E, new String[] {MSG_PARAM_CMPY });
            return false;
        }

//        if (!validateSegmentElementCoaExtnCd(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD))) {
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN)) {
            aCMsg.coaAfflAcctNm_A1.setErrorInfo(1, NSAM0209E, new String[] {MSG_PARAM_EXTN });
            return false;
        }

//        if (!validateSegmentElementCoaCcCd(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD))) {
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC)) {
            aCMsg.coaAfflAcctNm_A1.setErrorInfo(1, NSAM0209E, new String[] {MSG_PARAM_CC });
            return false;
        }

//        if (!validateSegmentElementCoaAcctCd(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD))) {
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT)) {
            aCMsg.coaAfflAcctNm_A1.setErrorInfo(1, NSAM0209E, new String[] {MSG_PARAM_ACCT });
            return false;
        }

//        if (!validateSegmentElementCoaProjCd(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD))) {
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ)) {
            aCMsg.coaAfflAcctNm_A1.setErrorInfo(1, NSAM0209E, new String[] {MSG_PARAM_PROJ });
            return false;
        }

//        if (!validateSegmentElementCoaProdCd(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD))) {
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD)) {
            aCMsg.coaAfflAcctNm_A1.setErrorInfo(1, NSAM0209E, new String[] {MSG_PARAM_PROD });
            return false;
        }

//        if (!validateSegmentElementCoaAfflCd(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD))) {
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL)) {
            aCMsg.coaAfflAcctNm_A1.setErrorInfo(1, NSAM0209E, new String[] {MSG_PARAM_AFFL });
            return false;
        }

//        if (!validateSegmentElementCoaChCd(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD))) {
        if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH)) {
            aCMsg.coaAfflAcctNm_A1.setErrorInfo(1, NSAM0209E, new String[] {MSG_PARAM_CH });
            return false;
        }
        // END 2016/07/29 T.Tomita [QC#11572, MOD]

        return true;
    }
// END   2016/03/23 Y.Takeno [QC#2728, ADD]

    // START 2016/07/29 T.Tomita [QC#11572, DEL]
//    private static boolean validateSegmentElementCoaCmpyCd(String element) {
//        return (element != null && element.length() == SEGMENT_ELEMENT_LENGTH_CMPY);
//    }
//
//    private static boolean validateSegmentElementCoaExtnCd(String element) {
//        return (element != null && element.length() == SEGMENT_ELEMENT_LENGTH_EXTN);
//    }
//
//    private static boolean validateSegmentElementCoaCcCd(String element) {
//        return (element != null && element.length() == SEGMENT_ELEMENT_LENGTH_CC);
//    }
//
//    private static boolean validateSegmentElementCoaAcctCd(String element) {
//        return (element != null && element.length() == SEGMENT_ELEMENT_LENGTH_ACCT);
//    }
//
//    private static boolean validateSegmentElementCoaProjCd(String element) {
//        return (element != null && element.length() == SEGMENT_ELEMENT_LENGTH_PROJ);
//    }
//
//    private static boolean validateSegmentElementCoaProdCd(String element) {
//        return (element != null && element.length() == SEGMENT_ELEMENT_LENGTH_PROD);
//    }
//
//    private static boolean validateSegmentElementCoaAfflCd(String element) {
//        return (element != null && element.length() == SEGMENT_ELEMENT_LENGTH_AFFL);
//    }
//
//    private static boolean validateSegmentElementCoaChCd(String element) {
//        return (element != null && element.length() == SEGMENT_ELEMENT_LENGTH_CH);
//    }
    // END 2016/07/29 T.Tomita [QC#11572, DEL]

    // START 2016/07/29 T.Tomita [QC#11572, ADD]
    private static boolean validateSegmentElement(String element, int len) {
        if (!hasValue(element)) {
            return true;
        }
        if (element.length() > len) {
            return false;
        }
        return true;
    }
    // END 2016/07/29 T.Tomita [QC#11572, ADD]

    /**
     * Initialize CMsg.
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL1230CMsg
     * @param sMsg NSAL1230SMsg
     * @param resultList DS_CONTR_SEG_ALLOCTMsgArray
     */
    public static final void initMsgs(String glblCmpyCd, NSAL1230CMsg cMsg, NSAL1230SMsg sMsg, List<Map<String, Object>> resultList, DS_CONTR_DTLTMsg dsContrDtlInfo) {

        NSAL1230_ACMsgArray aCMsgArrray = cMsg.A;
        BigDecimal prcAllocRateTotal = BigDecimal.ZERO;
        BigDecimal prcAllocAmtTotal = BigDecimal.ZERO;    // QC#23378(Sol#320) Add

        if (resultList.size() > 0) {
// START 2016/03/23 Y.Takeno [QC#2728, MOD]
//            aCMsgArrray.setValidCount(resultTMsgArray.length());
            int listSize = resultList.size();
            if (resultList.size() > cMsg.A.length()) {
                listSize = cMsg.A.length();
                cMsg.setMessageInfo(NZZM0001W);
            }
            aCMsgArrray.setValidCount(listSize);
//            for (int index = 0; index < resultTMsgArray.length(); index++) {
// END   2016/03/23 Y.Takeno [QC#2728, MOD]
            for (int index = 0; index < aCMsgArrray.getValidCount(); index++) {
                Map<String, Object> result = (Map<String, Object>) resultList.get(index);
                NSAL1230_ACMsg aCMsg = cMsg.A.no(index);

                ZYPEZDItemValueSetter.setValue(aCMsg.glblCmpyCd_A1, (String)result.get("GLBL_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(aCMsg.dsContrPk_A1, (BigDecimal)result.get("DS_CONTR_PK"));
                ZYPEZDItemValueSetter.setValue(aCMsg.dsContrDtlPk_A1, (BigDecimal)result.get("DS_CONTR_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(aCMsg.svcInvChrgTpCd_A1, (String)result.get("SVC_INV_CHRG_TP_CD"));

                ZYPEZDItemValueSetter.setValue(aCMsg.coaCmpyCd_A1, (String)result.get("COA_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(aCMsg.coaExtnCd_A1, (String)result.get("COA_EXTN_CD"));
                ZYPEZDItemValueSetter.setValue(aCMsg.coaBrCd_A1, (String)result.get("COA_BR_CD"));
                ZYPEZDItemValueSetter.setValue(aCMsg.coaCcCd_A1, (String)result.get("COA_CC_CD"));
                ZYPEZDItemValueSetter.setValue(aCMsg.coaAcctCd_A1, (String)result.get("COA_ACCT_CD"));
                ZYPEZDItemValueSetter.setValue(aCMsg.coaProjCd_A1, (String)result.get("COA_PROJ_CD"));
                ZYPEZDItemValueSetter.setValue(aCMsg.coaProdCd_A1, (String)result.get("COA_PROD_CD"));
                ZYPEZDItemValueSetter.setValue(aCMsg.coaAfflCd_A1, (String)result.get("COA_AFFL_CD"));
                ZYPEZDItemValueSetter.setValue(aCMsg.coaChCd_A1, (String)result.get("COA_CH_CD"));
                ZYPEZDItemValueSetter.setValue(aCMsg.prcAllocRate_A1, (BigDecimal)result.get("PRC_ALLOC_RATE"));
                // QC#23378(Sol#320) Add Start
                ZYPEZDItemValueSetter.setValue(aCMsg.prcAllocAmt_A1, (BigDecimal)result.get("PRC_ALLOC_AMT"));
                ZYPEZDItemValueSetter.setValue(aCMsg.coaAcctDescTxt_A1, getSvcAcctDistCatgNm((String)result.get("SVC_ACCT_DIST_CATG_NM")));
                // QC#23378(Sol#320) Add End

                ZYPEZDItemValueSetter.setValue(aCMsg.xxRowNum_A1, BigDecimal.valueOf(index));
                ZYPEZDItemValueSetter.setValue(aCMsg.coaAfflAcctNm_A1, buildSegmentString(aCMsg));

                if (ZYPCommonFunc.hasValue(aCMsg.prcAllocRate_A1)) {
                    prcAllocRateTotal = prcAllocRateTotal.add(aCMsg.prcAllocRate_A1.getValue());
                }
                // QC#23378(Sol#320) Add Start
                if (ZYPCommonFunc.hasValue(aCMsg.prcAllocAmt_A1)) {
                    prcAllocAmtTotal = prcAllocAmtTotal.add(aCMsg.prcAllocAmt_A1.getValue());
                }
                // QC#23378(Sol#320) Add End

                // START 2016/12/02 K.Kojima [QC#14204,ADD]
                ZYPEZDItemValueSetter.setValue(aCMsg.xxRecHistCratTs_A, (String)result.get("EZINTIME"));
                ZYPEZDItemValueSetter.setValue(aCMsg.xxRecHistCratByNm_A, (String)result.get("EZINUSERID"));
                ZYPEZDItemValueSetter.setValue(aCMsg.xxRecHistUpdTs_A, (String)result.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(aCMsg.xxRecHistUpdByNm_A, (String)result.get("EZUPUSERID"));
                ZYPEZDItemValueSetter.setValue(aCMsg.xxRecHistTblNm_A, (String)result.get("EZTABLEID"));
                // END 2016/12/02 K.Kojima [QC#14204,ADD]

            }

            ZYPEZDItemValueSetter.setValue(cMsg.prcAllocRate, prcAllocRateTotal);

        }
        // QC#23378(Sol#320) Add Start
        if (ZYPCommonFunc.hasValue(cMsg.svcInvChrgTpCd) && SVC_INV_CHRG_TP.BASE_CHARGE.equals(cMsg.svcInvChrgTpCd.getValue())) {
            if (ZYPCommonFunc.hasValue(dsContrDtlInfo.basePrcDealAmt)) {
                ZYPEZDItemValueSetter.setValue(cMsg.basePrcDealAmt, dsContrDtlInfo.basePrcDealAmt.getValue());
            }
        }
        ZYPEZDItemValueSetter.setValue(cMsg.prcAllocAmt, prcAllocAmtTotal);
        // QC#23378(Sol#320) Add End

        // del start 2016/10/05 CSA Defect#13815
        //else {
        //    aCMsgArrray.setValidCount(1);
        //    setInitialRecord(glblCmpyCd, cMsg, 0);
        //}
        // del end 2016/10/05 CSA Defect#13815
    }

    // add start 2016/10/05 CSA Defect#13815
    /**
     * initMsgsByContrBrAlloc
     * @param glblCmpyCd
     * @param cMsg
     * @param sMsg
     * @param brAllocTMsgArray
     */
    public static final void initMsgsByContrBrAlloc(String glblCmpyCd, NSAL1230CMsg cMsg, NSAL1230SMsg sMsg, DS_CONTR_BR_ALLOCTMsgArray brAllocTMsgArray) {

        NSAL1230_ACMsgArray aCMsgArrray = cMsg.A;
        BigDecimal prcAllocRateTotal = BigDecimal.ZERO;

        if (brAllocTMsgArray.length() > 0) {
            int listSize = brAllocTMsgArray.length();
            if (brAllocTMsgArray.length() > cMsg.A.length()) {
                listSize = cMsg.A.length();
                cMsg.setMessageInfo(NZZM0001W);
            }
            aCMsgArrray.setValidCount(listSize);
            for (int index = 0; index < aCMsgArrray.getValidCount(); index++) {
                DS_CONTR_BR_ALLOCTMsg tMsg = (DS_CONTR_BR_ALLOCTMsg) brAllocTMsgArray.get(index);
                NSAL1230_ACMsg aCMsg = cMsg.A.no(index);

                ZYPEZDItemValueSetter.setValue(aCMsg.glblCmpyCd_A1, tMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(aCMsg.dsContrPk_A1, tMsg.dsContrPk);
                ZYPEZDItemValueSetter.setValue(aCMsg.dsContrDtlPk_A1, tMsg.dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(aCMsg.svcInvChrgTpCd_A1, tMsg.svcInvChrgTpCd);

                ZYPEZDItemValueSetter.setValue(aCMsg.coaBrCd_A1, tMsg.coaBrCd);
                ZYPEZDItemValueSetter.setValue(aCMsg.prcAllocRate_A1, tMsg.prcAllocRate);

                ZYPEZDItemValueSetter.setValue(aCMsg.xxRowNum_A1, BigDecimal.valueOf(index));
                ZYPEZDItemValueSetter.setValue(aCMsg.coaAfflAcctNm_A1, buildSegmentString(aCMsg));

                if (ZYPCommonFunc.hasValue(aCMsg.prcAllocRate_A1)) {
                    prcAllocRateTotal = prcAllocRateTotal.add(aCMsg.prcAllocRate_A1.getValue());
                }
            }

            ZYPEZDItemValueSetter.setValue(cMsg.prcAllocRate, prcAllocRateTotal);

        }
    }
    // add end 2016/10/05 CSA Defect#13815

    /**
     * Set empty record.
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL1230CMsg
     * @param index index
     */
    public static final void setInitialRecord(String glblCmpyCd, NSAL1230CMsg cMsg, int index) {

        NSAL1230_ACMsg aCMsg = cMsg.A.no(index);

        ZYPEZDItemValueSetter.setValue(aCMsg.glblCmpyCd_A1, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(aCMsg.dsContrPk_A1, cMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(aCMsg.dsContrDtlPk_A1, cMsg.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(aCMsg.svcInvChrgTpCd_A1, cMsg.svcInvChrgTpCd);

        ZYPEZDItemValueSetter.setValue(aCMsg.coaCmpyCd_A1, cMsg.coaCmpyCd);
        ZYPEZDItemValueSetter.setValue(aCMsg.coaExtnCd_A1, cMsg.coaExtnCd);
        ZYPEZDItemValueSetter.setValue(aCMsg.coaBrCd_A1, cMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(aCMsg.coaCcCd_A1, cMsg.coaCcCd);
        ZYPEZDItemValueSetter.setValue(aCMsg.coaAcctCd_A1, cMsg.coaAcctCd);
        ZYPEZDItemValueSetter.setValue(aCMsg.coaProjCd_A1, cMsg.coaProjCd);
        ZYPEZDItemValueSetter.setValue(aCMsg.coaProdCd_A1, cMsg.coaProdCd);
        ZYPEZDItemValueSetter.setValue(aCMsg.coaAfflCd_A1, cMsg.coaAfflCd);
        ZYPEZDItemValueSetter.setValue(aCMsg.coaChCd_A1, cMsg.coaChCd);
        ZYPEZDItemValueSetter.setValue(aCMsg.prcAllocRate_A1, DEFAULT_PERCENTAGE);

        ZYPEZDItemValueSetter.setValue(aCMsg.xxRowNum_A1, BigDecimal.valueOf(index));
        ZYPEZDItemValueSetter.setValue(aCMsg.coaAfflAcctNm_A1, buildSegmentString(aCMsg));

        ZYPEZDItemValueSetter.setValue(cMsg.prcAllocRate, DEFAULT_PERCENTAGE);
    }

    /**
     * Set empty record.
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL1230CMsg
     * @param index index
     */
    public static final void setEmptyRecord(String glblCmpyCd, NSAL1230CMsg cMsg, int index) {
        NSAL1230_ACMsg aCMsg = cMsg.A.no(index);

        ZYPEZDItemValueSetter.setValue(aCMsg.glblCmpyCd_A1, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(aCMsg.dsContrPk_A1, cMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(aCMsg.dsContrDtlPk_A1, cMsg.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(aCMsg.svcInvChrgTpCd_A1, cMsg.svcInvChrgTpCd);

        ZYPEZDItemValueSetter.setValue(aCMsg.xxRowNum_A1, BigDecimal.valueOf(index));
    }

    /**
     * reflect segment string to 9Segment elements.
     * @param aCMsg NSAL1230CMsg
     */
    public static final void reflectSegmentStringTo9Segments(NSAL1230_ACMsg aCMsg) {
        List<String> segmentList = parseSegmentString(aCMsg);
        if (segmentList.size() == SEGMENT_TOKEN_LIST_SIZE) {
            ZYPEZDItemValueSetter.setValue(aCMsg.coaCmpyCd_A1, segmentList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(aCMsg.coaExtnCd_A1, segmentList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(aCMsg.coaBrCd_A1, segmentList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(aCMsg.coaCcCd_A1, segmentList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(aCMsg.coaAcctCd_A1, segmentList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(aCMsg.coaProjCd_A1, segmentList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(aCMsg.coaProdCd_A1, segmentList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(aCMsg.coaAfflCd_A1, segmentList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(aCMsg.coaChCd_A1, segmentList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
        }
    }

 // START 2016/03/23 Y.Takeno [QC#2728, MOD]
    /**
     * clear 9Segments.
     * 
     * @param aCMsg NSAL1230_ACMsg
     */
    public static final void clear9Segments(NSAL1230_ACMsg aCMsg) {
        aCMsg.coaCmpyCd_A1.clear();
        aCMsg.coaExtnCd_A1.clear();
        aCMsg.coaBrCd_A1.clear();
        aCMsg.coaCcCd_A1.clear();
        aCMsg.coaAcctCd_A1.clear();
        aCMsg.coaProjCd_A1.clear();
        aCMsg.coaProdCd_A1.clear();
        aCMsg.coaAfflCd_A1.clear();
        aCMsg.coaChCd_A1.clear();
    }
// END   2016/03/23 Y.Takeno [QC#2728, MOD]

    /**
     * update segment string.
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL1230CMsg
     * @param index index
     */
    public static final void updateSegmentString(String glblCmpyCd, NSAL1230CMsg cMsg, int index) {
        String segmentString = buildSegmentString(cMsg.A.no(index));
        if (segmentString.length() > SEGMENT_STRING_MIN_LENGTH) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).coaAfflAcctNm_A1, segmentString);
        }
    }

    private static final String buildSegmentString(NSAL1230_ACMsg aCMsg) {

        String delimiter = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DELIMITER, aCMsg.glblCmpyCd_A1.getValue());
        // mod start 2016/08/04 CSA QC#12767
        StringBuffer sb = new StringBuffer();
        sb.append(aCMsg.coaCmpyCd_A1.getValue());
        sb.append(delimiter);
        
        // START 2016/10/05 J.Sumi [QC#13815, MOD]
        sb.append(aCMsg.coaBrCd_A1.getValue());
        // END 2016/10/05 J.Sumi [QC#13815, MOD]
        sb.append(delimiter);
        sb.append(aCMsg.coaCcCd_A1.getValue());
        sb.append(delimiter);
        sb.append(aCMsg.coaAcctCd_A1.getValue());
        sb.append(delimiter);
        sb.append(aCMsg.coaProdCd_A1.getValue());
        sb.append(delimiter);
        sb.append(aCMsg.coaChCd_A1.getValue());
        sb.append(delimiter);
        sb.append(aCMsg.coaAfflCd_A1.getValue());
        sb.append(delimiter);
        sb.append(aCMsg.coaProjCd_A1.getValue());
        sb.append(delimiter);
        sb.append(aCMsg.coaExtnCd_A1.getValue());
        // mod end 2016/08/04 CSA QC#12767

        return sb.toString();
    }

    private static final List<String> parseSegmentString(NSAL1230_ACMsg aCMsg) {

        List<String> tokenList = new ArrayList<String>();
        String segmentString = aCMsg.coaAfflAcctNm_A1.getValue();
        String delimiter = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DELIMITER, aCMsg.glblCmpyCd_A1.getValue());

        // START 2016/07/29 T.Tomita [QC#11572, MOD]
//        StringTokenizer tokenizer = new StringTokenizer(segmentString, delimiter);
        String[] list = segmentString.split(Pattern.quote(delimiter), -1);
//        while (tokenizer.hasMoreTokens()) {
//            String token = tokenizer.nextToken();
//            tokenList.add(token);
//        }
        for (String val : list) {
            tokenList.add(val);
        }

        return tokenList;
    }

    // QC#23378(Sol#320) Add Start
    /**
     * getSvcAcctDistCatgNm
     * @param svcAcctDistCatgNm
     * @return
     */
    public static final String getSvcAcctDistCatgNm(String svcAcctDistCatgNm) {
        if(!ZYPCommonFunc.hasValue(svcAcctDistCatgNm)) {
            return SVC_ACCT_DIST_CATG_NM_OTHER;
        }
        return svcAcctDistCatgNm;
    }
    // QC#23378(Sol#320) Add End
}
