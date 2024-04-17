/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2830;

import static business.blap.NMAL2830.constant.NMAL2830Constant.NMAM0014E;
import static business.blap.NMAL2830.constant.NMAL2830Constant.NMAM0182E;
import static business.blap.NMAL2830.constant.NMAL2830Constant.NMAM8333I;
import static business.blap.NMAL2830.constant.NMAL2830Constant.SCRN_ID_00;
import static business.blap.NMAL2830.constant.NMAL2830Constant.ZZM9000E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL2830.common.NMAL2830CommonLogic;
import business.blap.NMAL2830.constant.NMAL2830Constant;
import business.parts.NMZC001001PMsg;
import business.parts.NMZC001002PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC001001.NMZC001001;
import com.canon.cusa.s21.api.NMZ.NMZC001001.constant.NMZC001001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL2830BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2830BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2830CMsg bizMsg = (NMAL2830CMsg) cMsg;
            NMAL2830SMsg glblMsg = (NMAL2830SMsg) sMsg;

            if ("NMAL2830Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2830Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL2830Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NMAL2830Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NMAL2830Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL2830Scrn00_SaveSearch(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2830Scrn00_CMN_Submit(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {

        NMAL2830CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        bizMsg.setCommitSMsg(true);

        NMAL2830_ASMsg asMsg;
        boolean errFlg = false;
        int errRow = -1;
        int multipleCheckCnt = 0;
        List<EZDSStringItem> errItemList = new ArrayList<EZDSStringItem>();

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            // ////////////
            // multiple check
            // ////////////
            if (NMAL2830Constant.ROW_TP_PROSPECT.equals(glblMsg.A.no(i).xxRowId_AT.getValue())) {

                errItemList.clear();
                multipleCheckCnt = 0;

                for (int j = i; j < glblMsg.A.getValidCount(); j++) {

                    asMsg = glblMsg.A.no(j);

                    if (i != j && NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {
                        break;
                    }

                    if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {

                        if (ZYPCommonFunc.hasValue(asMsg.locNum_M)) {
                            ++multipleCheckCnt;
                            errItemList.add(asMsg.locNum_M);
                        }
                    }

                    if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(asMsg.xxRowId_AT.getValue())) {

                        if (ZYPConstant.FLG_ON_Y.equals(asMsg.xxChkBox_M.getValue())) {
                            ++multipleCheckCnt;
                            errItemList.add(asMsg.xxChkBox_M);
                        }
                        if (ZYPConstant.FLG_ON_Y.equals(asMsg.xxChkBox_AM.getValue())) {
                            ++multipleCheckCnt;
                            errItemList.add(asMsg.xxChkBox_AM);
                        }
                    }

                    if (errRow == -1 && (multipleCheckCnt > 0)) {
                        errRow = j;
                    }
                }

                if (multipleCheckCnt > 1) {
                    errFlg = true;
                    for (EZDSStringItem errItem : errItemList) {
                        errItem.setErrorInfo(1, NMAL2830Constant.NMAM8461E, new String[] {NMAL2830Constant.MSG_ARGS_NMAM8461E });
                    }
                }
            }
        }

        if (errFlg) {
            bizMsg.xxPageShowFromNum.setValue(errRow);
            NMAL2830CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return;
        }

        List<String> mergeToTxtLocNumInActvList = new ArrayList<String>(glblMsg.A.getValidCount());
        List<String> mergeToLocNumInActvList = new ArrayList<String>(glblMsg.A.getValidCount());
        List<String> allMergeToLocNumInActvList = new ArrayList<String>(glblMsg.A.getValidCount());
        String apiCallTp = "";
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            asMsg = glblMsg.A.no(i);

            if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {
                apiCallTp = getApiCallTp(glblMsg, i);
            }

            if (!ZYPCommonFunc.hasValue(apiCallTp) || DS_ACCT_TP.CUSTOMER.equals(asMsg.dsAcctTpCd_A3.getValue())) {
                continue;
            }

            if (NMAL2830Constant.API_CALL_TP_MERGE_TO_TXT.equals(apiCallTp)) {

                // Get Merge To Txt Location#(In Active)
                if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {
                    mergeToTxtLocNumInActvList.add(asMsg.locNum_A1.getValue());
                }
            } else if (NMAL2830Constant.API_CALL_TP_MERGE_TO_CHECK.equals(apiCallTp)) {

                // Get Merge To Location#(In Active)
                if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {
                    mergeToLocNumInActvList.add(asMsg.locNum_A1.getValue());
                }
            } else if (NMAL2830Constant.API_CALL_TP_ALL_MERGE_TO_CHECK.equals(apiCallTp)) {

                // Get All Merge To Location#(In Active)
                if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {
                    allMergeToLocNumInActvList.add(asMsg.locNum_A1.getValue());
                }
                if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(asMsg.xxRowId_AT.getValue()) && !ZYPConstant.FLG_ON_Y.equals(asMsg.xxChkBox_AM.getValue())) {
                    allMergeToLocNumInActvList.add(asMsg.locNum_A3.getValue());
                }
            }
        }

        String[] msgArgs;
        BigDecimal resultCnt = BigDecimal.valueOf(0);
        errRow = -1;

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            asMsg = glblMsg.A.no(i);

            if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {
                apiCallTp = getApiCallTp(glblMsg, i);
            }

            if (!ZYPCommonFunc.hasValue(apiCallTp)) {
                continue;
            }

            // ////////////
            // master check
            // ////////////
            if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {

                // Merge To(Txt)
                if (ZYPCommonFunc.hasValue(asMsg.locNum_M)) {

                    resultCnt = NMAL2830Query.getInstance().existLocNumCnt(asMsg.locNum_M.getValue(), null);

                    if (BigDecimal.ZERO.compareTo(resultCnt) == 0) {
                        errFlg = true;
                        msgArgs = new String[] {NMAL2830Constant.PREFIX_MERGE_TO + asMsg.locNum_M.getValue() };
                        asMsg.locNum_M.setErrorInfo(1, NMAL2830Constant.NMAM8121E, msgArgs);
                        errRow = NMAL2830CommonLogic.getErrRow(errRow, i);
                    }
                }

                // Location #(Prospect)
                if (ZYPCommonFunc.hasValue(asMsg.locNum_A1)) {

                    resultCnt = NMAL2830Query.getInstance().existLocNumCnt(asMsg.locNum_A1.getValue(), asMsg.dsAcctNum_A1.getValue());

                    if (BigDecimal.ZERO.compareTo(resultCnt) == 0) {
                        errFlg = true;
                        msgArgs = new String[] {NMAL2830Constant.PREFIX_LOC_NUM + asMsg.locNum_A1.getValue() };
                        asMsg.locNum_M.setErrorInfo(1, NMAL2830Constant.NMAM8121E, msgArgs);
                        errRow = NMAL2830CommonLogic.getErrRow(errRow, i);
                    }
                }
            } else if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(asMsg.xxRowId_AT.getValue())) {

                // Location #(Duplicate)
                if (ZYPCommonFunc.hasValue(asMsg.locNum_A3)) {

                    resultCnt = NMAL2830Query.getInstance().existLocNumCnt(asMsg.locNum_A3.getValue(), asMsg.dsAcctNum_A3.getValue());

                    if (BigDecimal.ZERO.compareTo(resultCnt) == 0) {
                        errFlg = true;
                        msgArgs = new String[] {NMAL2830Constant.PREFIX_LOC_NUM + asMsg.locNum_A3.getValue() };
                        asMsg.xxChkBox_M.setErrorInfo(1, NMAL2830Constant.NMAM8121E, msgArgs);
                        asMsg.xxChkBox_AM.setErrorInfo(1, NMAL2830Constant.NMAM8121E, msgArgs);
                        errRow = NMAL2830CommonLogic.getErrRow(errRow, i);
                    }
                }
            }

            // ////////////
            // active check
            // ////////////
            if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {

                // Merge To(Txt)
                if (ZYPCommonFunc.hasValue(asMsg.locNum_M)) {

                    resultCnt = NMAL2830Query.getInstance().getCntActiveLocNum(asMsg.locNum_M.getValue(), null);

                    if (BigDecimal.ZERO.compareTo(resultCnt) == 0) {
                        errFlg = true;
                        msgArgs = new String[] {NMAL2830Constant.PREFIX_MERGE_TO + asMsg.locNum_M.getValue() };
                        asMsg.locNum_M.setErrorInfo(1, NMAL2830Constant.NMAM8497E, msgArgs);
                        errRow = NMAL2830CommonLogic.getErrRow(errRow, i);
                    }
                }

                // Location #(Prospect)
                if (ZYPCommonFunc.hasValue(asMsg.locNum_A1)) {

                    resultCnt = NMAL2830Query.getInstance().getCntActiveLocNum(asMsg.locNum_A1.getValue(), asMsg.dsAcctNum_A1.getValue());

                    if (BigDecimal.ZERO.compareTo(resultCnt) == 0) {
                        errFlg = true;
                        msgArgs = new String[] {NMAL2830Constant.PREFIX_LOC_NUM + asMsg.locNum_A1.getValue() };
                        asMsg.locNum_M.setErrorInfo(1, NMAL2830Constant.NMAM8497E, msgArgs);
                        errRow = NMAL2830CommonLogic.getErrRow(errRow, i);
                    }
                }
            } else if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(asMsg.xxRowId_AT.getValue())) {

                // Location #(Duplicate)
                if (ZYPCommonFunc.hasValue(asMsg.locNum_A3)) {

                    resultCnt = NMAL2830Query.getInstance().getCntActiveLocNum(asMsg.locNum_A3.getValue(), asMsg.dsAcctNum_A3.getValue());

                    if (BigDecimal.ZERO.compareTo(resultCnt) == 0) {
                        errFlg = true;
                        msgArgs = new String[] {NMAL2830Constant.PREFIX_LOC_NUM + asMsg.locNum_A3.getValue() };
                        asMsg.xxChkBox_M.setErrorInfo(1, NMAL2830Constant.NMAM8497E, msgArgs);
                        asMsg.xxChkBox_AM.setErrorInfo(1, NMAL2830Constant.NMAM8497E, msgArgs);
                        errRow = NMAL2830CommonLogic.getErrRow(errRow, i);
                    }
                }
            }

            // ////////////
            // merge check
            // ////////////
            boolean errMergeCheckFlg = false;
            if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {
                if (ZYPCommonFunc.hasValue(asMsg.locNum_M.getValue())) {
                    if (mergeToLocNumInActvList.contains(asMsg.locNum_M.getValue())) {
                        errMergeCheckFlg = true;
                    }
                    if (allMergeToLocNumInActvList.contains(asMsg.locNum_M.getValue())) {
                        errMergeCheckFlg = true;
                    }
                    if (mergeToTxtLocNumInActvList.contains(asMsg.locNum_M.getValue())) {
                        errMergeCheckFlg = true;
                    }
                }
            } else if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(asMsg.xxRowId_AT.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(asMsg.xxChkBox_M.getValue()) || ZYPConstant.FLG_ON_Y.equals(asMsg.xxChkBox_AM.getValue())) {
                    if (mergeToLocNumInActvList.contains(asMsg.locNum_A3.getValue())) {
                        errMergeCheckFlg = true;
                    }
                    if (allMergeToLocNumInActvList.contains(asMsg.locNum_A3.getValue())) {
                        errMergeCheckFlg = true;
                    }
                    if (mergeToTxtLocNumInActvList.contains(asMsg.locNum_A3.getValue())) {
                        errMergeCheckFlg = true;
                    }
                }
            }

            if (errMergeCheckFlg) {
                errFlg = true;
                if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {
                    asMsg.locNum_M.setErrorInfo(1, NMAL2830Constant.NMAM8592E, new String[] {NMAL2830Constant.MSG_ARGS_NMAM8592E });
                } else if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(asMsg.xxRowId_AT.getValue())) {
                    if (ZYPConstant.FLG_ON_Y.equals(asMsg.xxChkBox_M.getValue())) {
                        asMsg.xxChkBox_M.setErrorInfo(1, NMAL2830Constant.NMAM8592E, new String[] {NMAL2830Constant.MSG_ARGS_NMAM8592E });
                    } else if (ZYPConstant.FLG_ON_Y.equals(asMsg.xxChkBox_AM.getValue())) {
                        asMsg.xxChkBox_AM.setErrorInfo(1, NMAL2830Constant.NMAM8592E, new String[] {NMAL2830Constant.MSG_ARGS_NMAM8592E });
                    }
                }
                errRow = NMAL2830CommonLogic.getErrRow(errRow, i);
            }
        }

        if (errFlg) {
            bizMsg.xxPageShowFromNum.setValue(errRow);
            NMAL2830CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return;
        }

        // ////////////
        // Customer Update API
        // ////////////
        List<NMAL2830_ASMsg> asMsgList = new ArrayList<NMAL2830_ASMsg>();
        List<List<NMAL2830_ASMsg>> acctUnitList = new ArrayList<List<NMAL2830_ASMsg>>();
        List<Integer> setRowList = new ArrayList<Integer>(glblMsg.A.getValidCount());

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            if (!NMAL2830Constant.ROW_TP_PROSPECT.equals(glblMsg.A.no(i).xxRowId_AT.getValue())) {
                continue;
            }

            // Get API call type
            apiCallTp = getApiCallTp(glblMsg, i);

            if (!ZYPCommonFunc.hasValue(apiCallTp)) {
                continue;
            }

            asMsgList = new ArrayList<NMAL2830_ASMsg>();

            // Get Update Info List
            for (int j = i; j < glblMsg.A.getValidCount(); j++) {

                asMsg = glblMsg.A.no(j);

                if (!ZYPCommonFunc.hasValue(glblMsg.A.no(j).xxChkBox_AM)
                        && DS_ACCT_TP.CUSTOMER.equals(asMsg.dsAcctTpCd_A3.getValue())) {
                    continue;
                }

                if (NMAL2830Constant.API_CALL_TP_MERGE_TO_TXT.equals(apiCallTp)) {

                    if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {
                        asMsgList.add(asMsg);
                        setRowList.add(j);
                    }
                } else if (NMAL2830Constant.API_CALL_TP_MERGE_TO_CHECK.equals(apiCallTp)) {

                    if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {
                        asMsgList.add(asMsg);
                        setRowList.add(j);
                    }
                } else if (NMAL2830Constant.API_CALL_TP_ALL_MERGE_TO_CHECK.equals(apiCallTp)) {

                    if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {
                        asMsgList.add(asMsg);
                        setRowList.add(j);
                    }
                    if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(asMsg.xxRowId_AT.getValue()) && !ZYPConstant.FLG_ON_Y.equals(asMsg.xxChkBox_AM.getValue())) {
                        asMsgList.add(asMsg);
                        setRowList.add(j);
                    }
                }

                if (j == glblMsg.A.getValidCount() - 1 
                        || (i != (j + 1) 
                                && NMAL2830Constant.ROW_TP_PROSPECT.equals(glblMsg.A.no(j + 1).xxRowId_AT.getValue()))) {
                    acctUnitList.add(asMsgList);
                    break;
                }
            }
        }

        if (acctUnitList.size() == 0) {
            bizMsg.setMessageInfo(NMAM8333I);
            return;
        }

        // Get Merge To Location#
        List<String> mergeToLocNumList = new ArrayList<String>(glblMsg.A.getValidCount());
        for (int idx : ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_M", ZYPConstant.FLG_ON_Y)) {
            if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(glblMsg.A.no(idx).xxRowId_AT.getValue())) {
                mergeToLocNumList.add(glblMsg.A.no(idx).locNum_A3.getValue());
            }
        }

        // Get All Merge To Location#
        List<String> allMergeToLocNumList = new ArrayList<String>(glblMsg.A.getValidCount());
        for (int idx : ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_AM", ZYPConstant.FLG_ON_Y)) {
            if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(glblMsg.A.no(idx).xxRowId_AT.getValue())) {
                allMergeToLocNumList.add(glblMsg.A.no(idx).locNum_A3.getValue());
            }
        }

        // Get Merge To Txt Location#
        List<String> mergeToTxtLocNumList = new ArrayList<String>(glblMsg.A.getValidCount());
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (NMAL2830Constant.ROW_TP_PROSPECT.equals(glblMsg.A.no(i).xxRowId_AT.getValue())) {
                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).locNum_M)) {
                    mergeToTxtLocNumList.add(glblMsg.A.no(i).locNum_M.getValue());
                }
            }
        }

        S21SsmEZDResult getUpdateInfoResult;
        BigDecimal activeLocNumCnt = BigDecimal.valueOf(0);
        NMZC001001PMsg pMsg;
        NMZC001002PMsg pMsg2;
        NMZC001001 api = new NMZC001001();
        int cnt = 0;
        errRow = -1;
        String prosAcctInacRsnCd = ZYPCodeDataUtil.getVarCharConstValue(NMAL2830Constant.PROS_ACCT_INAC_RSN_CD, getGlobalCompanyCode());

        for (List<NMAL2830_ASMsg> list : acctUnitList) {

            for (int j = 0; j < list.size(); j++) {

                NMAL2830_ASMsg msg = list.get(j);

                getUpdateInfoResult = NMAL2830Query.getInstance().getUpdateInfo(msg);

                if (getUpdateInfoResult.isCodeNormal()) {

                    boolean acctInActvFlg = true;

                    if (NMAL2830Constant.ROW_TP_PROSPECT.equals(msg.xxRowId_AT.getValue())) {
                        if (mergeToTxtLocNumList.contains(msg.locNum_A1.getValue())) {
                            acctInActvFlg = false;
                        }
                    }
                    if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(msg.xxRowId_AT.getValue())) {
                        if (mergeToLocNumList.contains(msg.locNum_A3.getValue())) {
                            acctInActvFlg = false;
                        }
                        if (allMergeToLocNumList.contains(msg.locNum_A3.getValue())) {
                            acctInActvFlg = false;
                        }
                    }

                    activeLocNumCnt = NMAL2830Query.getInstance().getCntActiveLocNumThatExcCurLocNum(msg);
                    if (BigDecimal.ZERO.compareTo(activeLocNumCnt) < 0) {
                        acctInActvFlg = false;
                    }

                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) getUpdateInfoResult.getResultObject();

                    Map<String, Object> resultMap = resultList.get(0);
                    pMsg = new NMZC001001PMsg();

                    // Level 1
                    ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC001001Constant.PROCESS_MODE_PROS_UPD);
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
                    ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, (String) resultMap.get("DS_ACCT_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNm, (String) resultMap.get("DS_ACCT_NM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.dsAcctItrlFlg, (String) resultMap.get("DS_ACCT_ITRL_FLG"));
                    ZYPEZDItemValueSetter.setValue(pMsg.dsAcctClsCd, (String) resultMap.get("DS_ACCT_CLS_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.coaChCd, (String) resultMap.get("COA_CH_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.coaAfflCd, (String) resultMap.get("COA_AFFL_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.dsAcctDlrCd, (String) resultMap.get("DS_ACCT_DLR_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.dsAcctLegalNm, (String) resultMap.get("DS_ACCT_LEGAL_NM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.dbaNm, (String) resultMap.get("DBA_NM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.dsAcctUrl, (String) resultMap.get("DS_ACCT_URL"));
                    if (acctInActvFlg) {
                        ZYPEZDItemValueSetter.setValue(pMsg.xxAcctInacRsnFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctInacRsnCd, prosAcctInacRsnCd);
                    } else {
                        ZYPEZDItemValueSetter.setValue(pMsg.xxAcctInacRsnFlg, ZYPConstant.FLG_OFF_N);
                    }

                    pMsg.NMZC001002PMsg.setValidCount(1);

                    // Level 2
                    pMsg2 = pMsg.NMZC001002PMsg.no(0);
                    if (resultMap.get("PTY_LOC_PK_PRIMARY") != null) {
                        ZYPEZDItemValueSetter.setValue(pMsg2.xxPrinFlg, ZYPConstant.FLG_ON_Y);
                    }
                    if (NMAL2830Constant.ROW_TP_PROSPECT.equals(msg.xxRowId_AT.getValue())) {
                        ZYPEZDItemValueSetter.setValue(pMsg2.locNum, msg.locNum_A1.getValue());
                    } else if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(msg.xxRowId_AT.getValue())) {
                        ZYPEZDItemValueSetter.setValue(pMsg2.locNum, msg.locNum_A3.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(pMsg2.effFromDt, (String) resultMap.get("EFF_FROM_DT"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.effThruDt, (String) resultMap.get("EFF_THRU_DT"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.actvFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(pMsg2.firstLineAddr, (String) resultMap.get("FIRST_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.scdLineAddr, (String) resultMap.get("SCD_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.thirdLineAddr, (String) resultMap.get("THIRD_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.frthLineAddr, (String) resultMap.get("FRTH_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.ctyAddr, (String) resultMap.get("CTY_ADDR"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.ctryCd, (String) resultMap.get("CTRY_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.stCd, (String) resultMap.get("ST_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.postCd, (String) resultMap.get("POST_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.cntyPk, (BigDecimal) resultMap.get("CNTY_PK"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.locNm, (String) resultMap.get("LOC_NM"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.addlLocNm, (String) resultMap.get("ADDL_LOC_NM"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.glnNum, (BigDecimal) resultMap.get("GLN_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.telNum, (String) resultMap.get("TEL_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.faxNum, (String) resultMap.get("FAX_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.provNm, (String) resultMap.get("PROV_NM"));
                    ZYPEZDItemValueSetter.setValue(pMsg2.lineBizTpCd, (String) resultMap.get("LINE_BIZ_TP_CD"));

                    // call Customer Update API
                    api.execute(pMsg, ONBATCH_TYPE.ONLINE);

                    String msgId = null;
                    if (S21ApiUtil.isXxMsgId(pMsg)) {
                        S21ApiMessage apiMsg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                        msgId = apiMsg.getXxMsgid();
                        if (NMAL2830Constant.ROW_TP_PROSPECT.equals(list.get(j).xxRowId_AT.getValue())) {
                            glblMsg.A.no(setRowList.get(cnt)).locNum_M.setErrorInfo(1, msgId);
                            errRow = NMAL2830CommonLogic.getErrRow(errRow, setRowList.get(cnt));
                        } else if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(list.get(j).xxRowId_AT.getValue())) {
                            glblMsg.A.no(setRowList.get(cnt)).xxChkBox_AM.setErrorInfo(1, msgId);
                            errRow = NMAL2830CommonLogic.getErrRow(errRow, setRowList.get(cnt));
                        }
                        continue;
                    } else {
                        NMZC001002PMsg locPMsg = pMsg.NMZC001002PMsg.no(0);
                        if (ZYPCommonFunc.hasValue(locPMsg.xxMsgIdList.no(0).xxMsgId)) {
                            msgId = locPMsg.xxMsgIdList.no(0).xxMsgId.getValue();
                            if (NMAL2830Constant.ROW_TP_PROSPECT.equals(list.get(j).xxRowId_AT.getValue())) {
                                glblMsg.A.no(setRowList.get(cnt)).locNum_M.setErrorInfo(1, msgId);
                                errRow = NMAL2830CommonLogic.getErrRow(errRow, setRowList.get(cnt));
                            } else if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(list.get(j).xxRowId_AT.getValue())) {
                                glblMsg.A.no(setRowList.get(cnt)).xxChkBox_AM.setErrorInfo(1, msgId);
                                errRow = NMAL2830CommonLogic.getErrRow(errRow, setRowList.get(cnt));
                            }
                            continue;
                        }
                    }
                }
            }
            cnt++;
        }

        if(errRow != -1){
            bizMsg.xxPageShowFromNum.setValue(errRow);
        }
        NMAL2830CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * getApiCallTp
     * @param glblMsg NMAL2830SMsg
     * @param curIdx int
     * @return String
     */
    private String getApiCallTp(NMAL2830SMsg glblMsg, int curIdx) {

        String apiCallTp = "";

        for (int j = curIdx; j < glblMsg.A.getValidCount(); j++) {

            if (curIdx != j && NMAL2830Constant.ROW_TP_PROSPECT.equals(glblMsg.A.no(j).xxRowId_AT.getValue())) {
                break;
            }

            if (NMAL2830Constant.ROW_TP_PROSPECT.equals(glblMsg.A.no(j).xxRowId_AT.getValue())) {

                if (ZYPCommonFunc.hasValue(glblMsg.A.no(j).locNum_M)) {
                    apiCallTp = NMAL2830Constant.API_CALL_TP_MERGE_TO_TXT;
                    break;
                }
            } else if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(glblMsg.A.no(j).xxRowId_AT.getValue())) {

                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(j).xxChkBox_M.getValue())) {
                    apiCallTp = NMAL2830Constant.API_CALL_TP_MERGE_TO_CHECK;
                    break;
                }
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(j).xxChkBox_AM.getValue())) {
                    apiCallTp = NMAL2830Constant.API_CALL_TP_ALL_MERGE_TO_CHECK;
                    break;
                }
            }
        }
        return apiCallTp;
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2830Scrn00_DeleteSearch(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            bizMsg.srchOptPk_S.setErrorInfo(1, NMAM0014E, new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NMAL2830CommonLogic.callNszc0330forDeleteSearch(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2830Scrn00_SaveSearch(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }
        if (NMAL2830CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, NMAM0182E, new String[] {//
                    converter.convLabel2i18nLabel(SCRN_ID_00, "Save") //
                            , converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }

        NMAL2830CommonLogic.callNszc0330forSaveSearch(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }
}
