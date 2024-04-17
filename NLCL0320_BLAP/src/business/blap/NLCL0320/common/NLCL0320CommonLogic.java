/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.blap.NLCL0320.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0320.NLCL0320CMsg;
import business.blap.NLCL0320.NLCL0320Query;
import business.blap.NLCL0320.NLCL0320SMsg;
import business.blap.NLCL0320.constant.NLCL0320Constant;
import business.db.RTL_WHTMsg;
import business.parts.NFZC102001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CSAI            K.Lee           Create          N/A
 * 2016/11/18   CITS            K.Kameoka       Update          QC#15705
 * 07/18/2017   CITS            K.Ogino         Update          QC#19433
 *</pre>
 */
public class NLCL0320CommonLogic implements NLCL0320Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    public static NLCL0320Query getQuery() {
        return NLCL0320Query.getInstance();
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NLCL0320CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        ZYPTableUtil.clear(bizMsgAry);

        int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry.length() && startIndex + dispRowNum < shareMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(shareMsgAry.get(startIndex + dispRowNum), null, bizMsgAry.get(dispRowNum), null);
        }
        bizMsgAry.setValidCount(dispRowNum);
        bizMsg.xxPageShowToNum_A.setValue(startIndex + dispRowNum);
        bizMsg.xxPageShowOfNum_A.setValue(shareMsgAry.getValidCount());
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void copyPage(NLCL0320CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry.length() && dispRowNum < bizMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(bizMsgAry.get(dispRowNum), null, shareMsgAry.get(startIndex + dispRowNum), null);
        }
    }

    /**
     * Previous Page
     * @param bizMsg NLCL0320CMsg
     * @param globalMsg NLCL0320SMsg
     */
    public static void prevPage(NLCL0320CMsg bizMsg, NLCL0320SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;

        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() - bizMsgAry.length());
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Next Page
     * @param bizMsg NLCL0320CMsg
     * @param globalMsg NLCL0320SMsg
     */
    public static void nextPage(NLCL0320CMsg bizMsg, NLCL0320SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() + bizMsgAry.length());
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Last Page
     * @param bizMsg NLCL0320CMsg
     * @param globalMsg NLCL0320SMsg
     */
    public static void firstErrorPage(NLCL0320CMsg bizMsg, NLCL0320SMsg globalMsg, int errNum) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        BigDecimal errPageFromNum = getErrorPageFromNum(bizMsg, globalMsg, errNum);
        bizMsg.xxPageShowFromNum_A.setValue(errPageFromNum);
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Last Page
     * @param bizMsg NLCL0320CMsg
     * @param globalMsg NLCL0320SMsg
     */
    public static void lastPage(NLCL0320CMsg bizMsg, NLCL0320SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        BigDecimal lastPageFromNum = getLastPageFromNum(bizMsg, globalMsg);
        bizMsg.xxPageShowFromNum_A.setValue(lastPageFromNum);
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Get Last Page From Number
     * @param bizMsg NLCL0320CMsg
     * @param globalMsg NLCL0320SMsg
     * @return BigDecimal
     */
    public static BigDecimal getLastPageFromNum(NLCL0320CMsg bizMsg, NLCL0320SMsg globalMsg) {
        int pageCnt = globalMsg.A.getValidCount() / bizMsg.A.length();
        int lastPageFromNum = bizMsg.A.length() * pageCnt + 1;
        if (globalMsg.A.getValidCount() % bizMsg.A.length() == 0) {
            lastPageFromNum = lastPageFromNum - bizMsg.A.length();
        }
        return new BigDecimal(lastPageFromNum);
    }

    /**
     * Get Last Page From Number
     * @param bizMsg NLCL0320CMsg
     * @param globalMsg NLCL0320SMsg
     * @return BigDecimal
     */
    public static BigDecimal getErrorPageFromNum(NLCL0320CMsg bizMsg, NLCL0320SMsg globalMsg, int errNum) {
        errNum++;
        int pageCnt = errNum / bizMsg.A.length();
        int errPageFromNum = bizMsg.A.length() * pageCnt + 1;
        if (errNum % bizMsg.A.length() == 0) {
            errPageFromNum = errPageFromNum - bizMsg.A.length();
        }
        return new BigDecimal(errPageFromNum);
    }

    public static String getRtlWhNm(String glblCmpyCd, String rtlWhCd) {
        if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
            return null;
        }
        RTL_WHTMsg inMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, rtlWhCd);
        RTL_WHTMsg outMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg != null) {
            return outMsg.rtlWhNm.getValue();
        }
        return null;
    }

    public static boolean isUpdatedLine(NLCL0320SMsg globalMsg, int index) {
        if (!globalMsg.A.no(index).adjAcctAliasDescTxt_A.getValue().equals(globalMsg.B.no(index).adjAcctAliasDescTxt_B.getValue())) {
            return true;
        }
        if (!globalMsg.A.no(index).xxScrItem130Txt_A.getValue().equals(globalMsg.B.no(index).xxScrItem130Txt_B.getValue())) {
            return true;
        }
        if (!globalMsg.A.no(index).effThruDt_A.getValue().equals(globalMsg.B.no(index).effThruDt_B.getValue())) {
            return true;
        }
        if (!globalMsg.A.no(index).effThruDt_A.getValue().equals(globalMsg.B.no(index).effThruDt_B.getValue())) {
            return true;
        }
        return false;
    }

    public static boolean isDuplicate(String glblCmpyCd, String rtlWhCd, String adjAcctAliasNm, String effFromDt, String effThruDt, String isUpdateFlg) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("rtlWhCd", rtlWhCd);
        prmMap.put("adjAcctAliasNm", adjAcctAliasNm);
        prmMap.put("effFromDt", effFromDt);
        prmMap.put("effThruDt", effThruDt);
        prmMap.put("isUpdateFlg", isUpdateFlg);
        S21SsmEZDResult ssmResult = getQuery().countDuplicate(prmMap);
        if (ssmResult.isCodeNormal()) {
            BigDecimal count = (BigDecimal) ssmResult.getResultObject();
            if (count.compareTo(BigDecimal.ZERO) > 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasLineError(String glblCmpyCd, NLCL0320SMsg globalMsg, int i) {
        boolean errFlg = false;

        if (globalMsg.A.no(i).effFromDt_A.getValue().compareTo(globalMsg.A.no(i).effThruDt_A.getValue()) > 0) {
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).ezUpTime_A)) {
                globalMsg.A.no(i).effFromDt_A.setErrorInfo(1, MESSAGE_ID.NWDM0099E.toString(), new String[] {});
            }
            globalMsg.A.no(i).effThruDt_A.setErrorInfo(1, MESSAGE_ID.NWDM0099E.toString(), new String[] {});
            errFlg = true;
        }

        if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).ezUpTime_A)) {
            if (globalMsg.A.no(i).effFromDt_A.getValue().compareTo(ZYPDateUtil.getSalesDate()) < 0) {
                globalMsg.A.no(i).effFromDt_A.setErrorInfo(1, MESSAGE_ID.NWDM0134E.toString(), new String[] {});
                errFlg = true;
            }

            if (globalMsg.A.no(i).effThruDt_A.getValue().compareTo(ZYPDateUtil.getSalesDate()) < 0) {
                globalMsg.A.no(i).effThruDt_A.setErrorInfo(1, MESSAGE_ID.NWDM0223E.toString(), new String[] {});
                errFlg = true;
            }
        }

        String rtlWhNm = NLCL0320CommonLogic.getRtlWhNm(glblCmpyCd, globalMsg.A.no(i).rtlWhCd_A.getValue());
        if (!ZYPCommonFunc.hasValue(rtlWhNm)) {
            globalMsg.A.no(i).rtlWhCd_A.setErrorInfo(1, MESSAGE_ID.NMAM0009E.toString(), new String[] {"Retail Warehouse Code" });
            errFlg = true;
        }
        return errFlg;
    }

    /**
     * checkManualSegmentElement
     * @param cMsg NLCL0320CMsg
     * @param sMsg NAL1500SMsg
     * @param glblCmpyCd String
     * @param accountType AccountType
     * @return boolean
     */
    public static void checkManualSegmentElement(NLCL0320CMsg cMsg, NLCL0320SMsg sMsg, String glblCmpyCd) {

        int idx = cMsg.xxNum.getValueInt();

        // Length Check
        List<String> tokenList = null;
        tokenList = tokenListSplit(cMsg.A.no(idx).xxScrItem130Txt_A.getValue(), glblCmpyCd);

        if (tokenList.size() == 0) {
            // no input.
            return;
        } else if (tokenList.size() < SEGMENT_TOKEN_LIST_SIZE) {
            cMsg.A.no(idx).xxScrItem130Txt_A.setErrorInfo(1, NPAM1193E, new String[] {MSG_PARAM_SEGMENT });
            return;
        }

        // window PopUp
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH));
        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR));

        // DB COLUMN SET
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaCmpyCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaAfflCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaBrCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaCcCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaAcctCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaProdCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaChCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaProjCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).coaExtnCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));

        return;
    }

    /**
     * checkManualSegmentElementForSMsg
     * @param sMsg NLCL0320SMsg
     * @param glblCmpyCd String
     * @param rowIndex int
     * @return boolean
     */
    public static boolean checkManualSegmentElementForSMsg(NLCL0320SMsg sMsg, String glblCmpyCd, int rowIndex) {

        List<EZDSStringItem> checkTarget = Arrays.asList(sMsg.A.no(rowIndex).xxScrItem130Txt_A);
        for (EZDSStringItem target : checkTarget) {

            // Length Check
            List<String> tokenList = tokenListSplit(target.getValue(), glblCmpyCd);
            if (tokenList == null || tokenList.size() != SEGMENT_TOKEN_LIST_SIZE) {
                target.setErrorInfo(1, NPAM1193E, new String[] {MSG_PARAM_SEGMENT });
                return false;
            }

            // coaCmpyCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CMPY });
                return false;
            }

            // coaExtnCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_EXTN });
                return false;
            }

            // coaCcCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CC });
                return false;
            }

            // coaAcctCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_ACCT });
                return false;
            }

            // coaProjCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_PROJ });
                return false;
            }

            // coaProdCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_PROD });
                return false;
            }

            // coaAfflCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_AFFL });
                return false;
            }

            // coaChCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CH });
                return false;
            }

            // coaBrCd check
            if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR)) {
                target.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_BR });
                return false;
            }

            // GL Code Combination Check API NFZC102001
            NFZC102001 afzc102001 = new NFZC102001();
            NFZC102001PMsg paramMsg = new NFZC102001PMsg();

            paramMsg.glblCmpyCd.setValue(glblCmpyCd);
            paramMsg.xxMstChkFlg.setValue(ZYPConstant.FLG_ON_Y);
            paramMsg.xxGlCmbnChkFlg.setValue(ZYPConstant.FLG_ON_Y);
            paramMsg.xxArcsApiChkFlg.setValue("");
            paramMsg.coaCmpyCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
            paramMsg.coaAfflCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
            paramMsg.coaBrCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
            paramMsg.coaCcCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
            paramMsg.coaAcctCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
            paramMsg.coaProdCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
            paramMsg.coaChCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
            paramMsg.coaProjCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
            paramMsg.coaExtnCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));

            // QC#19433 Start
            ZYPEZDItemValueSetter.setValue(paramMsg.resrcObjNm, "NLCL0320");
            // QC#19433 End

            afzc102001.execute(paramMsg, ONBATCH_TYPE.ONLINE);

            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();

                // QC#15705 Start
                if (msgPrms != null && msgPrms.length > 0) {
                    // QC#15705 End

                    if (msgPrms[0].equals(DB_COLUMN_COA_CMPY_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CMPY });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_BR_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_BR });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_CC_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CC });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_ACCT_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_ACCT });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_PROD_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROD });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_CH_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CH });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_AFFL_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_AFFL });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_PROJ_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROJ });
                    }
                    if (msgPrms[0].equals(DB_COLUMN_COA_EXTN_CD)) {
                        target.setErrorInfo(1, msgId, new String[] {MSG_PARAM_EXTN });
                    } else {
                        target.setErrorInfo(1, msgId, new String[] {msgPrms[0] });
                    }
                    // QC#15705 Start
                } else {
                    target.setErrorInfo(1, msgId, null);
                }
                // QC#15705 End
                return false;
            }

            // DB COLUMN SET
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCmpyCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAfflCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaBrCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaCcCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaAcctCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProdCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaChCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaProjCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowIndex).coaExtnCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
        }
        return true;
    }

    private static String splitSegmentElement(String element, int len) {
        if (!hasValue(element)) {
            return null;
        }
        if (element.length() > len) {
            element = element.substring(0, len);
        }
        return element;
    }

    /**
     * validateSegmentElement
     * @param element String
     * @param len int
     * @return boolean
     */
    private static boolean validateSegmentElement(String element, int len) {
        if (!hasValue(element)) {
            return true;
        }
        if (element.length() > len) {
            return false;
        }
        return true;
    }

    /**
     * tokenListSplit
     * @param xxScrItem50Txt_A1 String
     * @param glblCmpyCd String
     * @return tokenList List<String>
     */
    private static List<String> tokenListSplit(String xxScrItem50Txt_A1, String glblCmpyCd) {
        String delimiter = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DELIMITER, glblCmpyCd);

        if (!hasValue(xxScrItem50Txt_A1)) {
            return new ArrayList<String>();
        }
        String[] list = xxScrItem50Txt_A1.split(Pattern.quote(delimiter), -1);
        List<String> tokenList = new ArrayList<String>();
        for (String val : list) {
            tokenList.add(val);
        }

        return tokenList;
    }
}
