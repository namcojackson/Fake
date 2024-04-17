/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL1120.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFBL1120.NFBL1120CMsg;
import business.blap.NFBL1120.NFBL1120Query;
import business.blap.NFBL1120.NFBL1120SMsg;
import business.blap.NFBL1120.constant.NFBL1120Constant;
import business.blap.NFBL1120.common.NFBL1120CommonLogic;
import business.db.SAVE_SRCH_OPTTMsg;
import business.db.SAVE_SRCH_OPTTMsgArray;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Invoice Maintenance Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/09   Hitachi         K.Kojima        Update          QC#12725
 * 2017/03/16   Hitachi         E.Kameishi      Update          QC#14205
 * </pre>
 */
public class NFBL1120CommonLogic implements NFBL1120Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    /**
     * Method name: checkLocNm
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static boolean checkLocNm(NFBL1120CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFBL1120Query.getInstance().checkLocNm(bizMsg);

        if (ssmResult.isCodeNormal()) {
            return true;
        }
        return false;
    }

    /**
     * Method name: checkPrntVnd
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static boolean checkPrntVnd(NFBL1120CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFBL1120Query.getInstance().checkPrntVnd(bizMsg);

        if (ssmResult.isCodeNormal()) {
            return true;
        }
        return false;
    }
    
    
    
    /**
     * Method name: createInvoiceStatusPulldownList
     * <dd>The method explanation: Create pulldown list.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @SuppressWarnings("unchecked")
    public static void createInvoiceStatusPulldownList(EZDCMsg cMsg) {

        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;

        S21SsmEZDResult ssmResult = NFBL1120Query.getInstance().getInvoiceStatusPulldownValue();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                
                ZYPEZDItemValueSetter.setValue(bizMsg.apMaintInvStsCd_C.no(i), (String) map.get(AP_MAINT_INV_STS_CD));
                ZYPEZDItemValueSetter.setValue(bizMsg.apMaintInvStsDescTxt_D.no(i), (String) map.get(AP_MAINT_INV_STS_DESC_TXT));
            }
        }

    }

    // START 2016/09/13 K.Kojima [QC#12725,ADD]
    /**
     * createCurrentApproverPulldownList
     * @param cMsg EZDCMsg
     */
    public static void createCurrentApproverPulldownList(EZDCMsg cMsg) {
        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;

        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_NFBL1120_LIST_CD, GLBL_CMPY_CD);
        if (varCharConstVal != null) {
            String[] codeList = varCharConstVal.split(",");
            int no = 0;
            for (int i = 0; i < codeList.length; i++) {
                varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(codeList[i], GLBL_CMPY_CD);
                if (varCharConstVal != null) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstNm_C.no(no), codeList[i]);
                    ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_D.no(no), varCharConstVal);
                    no = no + 1;
                }
            }
        }
    }

    // END 2016/09/13 K.Kojima [QC#12725,ADD]

    /**
     * Method name: setPrntVndNm
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static void setPrntVndNm(NFBL1120CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFBL1120Query.getInstance().getPrntVndNm(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            Map map = (Map) resultList.get(0);
            ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, (String) map.get(PRNT_VND_NM));
        } else {
            bizMsg.prntVndCd.setErrorInfo(1, NFBM0069E);
        }
    }

    // START 2016/09/13 K.Kojima [QC#12725,DEL]
    // /**
    // * Method name: setApvrUsrNm
    // * <dd>The method explanation:
    // * <dd>Remarks:
    // * @param cMsg Business Component Interface Message
    // */
    // @SuppressWarnings("unchecked")
    // public static void setApvrUsrNm(NFBL1120CMsg bizMsg) {
    //
    // S21SsmEZDResult ssmResult =
    // NFBL1120Query.getInstance().getApvrUsrNm(bizMsg);
    //
    // if (ssmResult.isCodeNormal()) {
    // List resultList = (List) ssmResult.getResultObject();
    // Map map = (Map) resultList.get(0);
    // ZYPEZDItemValueSetter.setValue(bizMsg.apvrUsrNm, (String)
    // map.get(APVR_USR_NM));
    // } else {
    // bizMsg.prntVndCd.setErrorInfo(1, NFBM0069E);
    // }
    // }
    // END 2016/09/13 K.Kojima [QC#12725,DEL]

    /**
     * Method name: searchRecord
     * <dd>The method explanation: Get record for LINES tab.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @SuppressWarnings("unchecked")
    public static boolean searchRecord(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "searchRecord================================START", null);

        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;
        NFBL1120SMsg globalMsg = (NFBL1120SMsg) sMsg;

        globalMsg.A.setValidCount(0);
        globalMsg.A.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.A.clear();

        S21SsmEZDResult ssmResult = NFBL1120Query.getInstance().searchRecord(bizMsg, globalMsg);

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();

            if (resultList.size() > globalMsg.A.length()) {
                bizMsg.A.setValidCount(bizMsg.A.length());
                globalMsg.A.setValidCount(globalMsg.A.length());
                bizMsg.xxPageShowFromNum_A.setValue(1);
                bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.length());
                bizMsg.xxPageShowOfNum_A.setValue(globalMsg.A.getValidCount());

                bizMsg.setMessageInfo(NFBM0001W, new String[] {Long.toString(globalMsg.A.length()), Long.toString(resultList.size()) });

            } else {
                globalMsg.A.setValidCount(resultList.size());
                bizMsg.xxPageShowFromNum_A.setValue(1);
                bizMsg.xxPageShowOfNum_A.setValue(resultList.size());

                if (bizMsg.A.length() < globalMsg.A.getValidCount()) {
                    bizMsg.A.setValidCount(bizMsg.A.length());
                    bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.length());
                } else {
                    bizMsg.A.setValidCount(resultList.size());
                    bizMsg.xxPageShowToNum_A.setValue(resultList.size());
                }
                // The search ended normally
                bizMsg.setMessageInfo(ZZM8002I);

            }

            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                Map map = (Map) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apBatNum_A1, (String) map.get(AP_BAT_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apBatDt_A1, (String) map.get(AP_BAT_DT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).prntVndCd_A1, (String) map.get(PRNT_VND_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).prntVndNm_A1, (String) map.get(PRNT_VND_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apVndCd_A1, (String) map.get(AP_VND_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).vndSiteNm_A1, (String) map.get(VND_SITE_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMaintInvStsCd_A1, (String) map.get(AP_MAINT_INV_STS_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMaintInvStsDescTxt_A1, (String) map.get(AP_MAINT_INV_STS_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apvrUsrId_A1, (String) map.get(APVR_USR_ID));
                // START 2016/09/09 K.Kojima [QC#12725,MOD]
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apvrUsrNm_A1,
                // (String) map.get(APVR_USR_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).varCharConstVal_A1, (String) map.get(APVR_USR_NM));
                // END 2016/09/09 K.Kojima [QC#12725,MOD]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apInvNum_A1, (String) map.get(AP_INV_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invDt_A1, (String) map.get(INV_DT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apInvAmt_A1, (BigDecimal) map.get(AP_INV_AMT));
                //START 2017/03/16 E.Kameishi [QC#14205, ADD]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxRecHistCratTs_A1, (String) map.get(XX_REC_HIST_CRAT_TS));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_CRAT_BY_NM)));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxRecHistUpdTs_A1, (String) map.get(XX_REC_HIST_UPD_TS));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_UPD_BY_NM)));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxRecHistTblNm_A1, (String) map.get(XX_REC_HIST_TBL_NM));
                //END 2017/03/16 E.Kameishi [QC#14205, ADD]
            }
            // Copy SMsg info to CMsg.
            copySMsgToCMsgS(bizMsg, globalMsg);
        } else {
            // Not found
            bizMsg.A.setValidCount(0);
            bizMsg.xxPageShowFromNum_A.setValue(0);
            bizMsg.xxPageShowToNum_A.setValue(0);
            bizMsg.xxPageShowOfNum_A.setValue(0);
            bizMsg.setMessageInfo(NFBM0069E);
            return false;
        }

        EZDDebugOutput.println(5, "searchRecord================================END", null);
        return true;
    }

    /**
     * Method name: clearHeader
     * <dd>The method explanation: Clear values on HEADER tab.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void clearHeader(EZDCMsg cMsg) {

        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;

        bizMsg.apBatNum.clear();
        bizMsg.prntVndCd.clear();
        bizMsg.prntVndNm.clear();
        bizMsg.apBatDt.clear();
        bizMsg.locNm.clear();
        bizMsg.apMaintInvStsCd_S.clear();
        // START 2016/09/13 K.Kojima [QC#12725,MOD]
        // bizMsg.apvrUsrId.clear();
        // bizMsg.apvrUsrNm.clear();
        bizMsg.varCharConstNm_S.clear();
        // END 2016/09/13 K.Kojima [QC#12725,MOD]
        bizMsg.apInvNum.clear();
        bizMsg.invDt.clear();
        // START 2016/10/05 J.Kim [QC#5521,ADD]
        bizMsg.srchOptPk_S.clear();
        bizMsg.srchOptNm_S.clear();
        // END 2016/10/05 J.Kim [QC#5521,ADD]
    }
    
    /**
     * Method name: clearLines
     * <dd>The method explanation: Clear values on LINES tab.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void clearLines(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;
        NFBL1120SMsg globalMsg = (NFBL1120SMsg) sMsg;

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        globalMsg.A.clear();
        globalMsg.A.setValidCount(0);
    }
    
    /**
     * Method name: copySMsgToCMsgS
     * <dd>The method explanation: Copy SMsg info to CMsg.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void copySMsgToCMsgS(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "copySMsgToCMsgS================================START", null);
        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;
        NFBL1120SMsg globalMsg = (NFBL1120SMsg) sMsg;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
        }
        EZDDebugOutput.println(5, "copySMsgToCMsgS================================END", null);
    }

    // START 2016/10/05 J.Kim [QC#5521,ADD]
    /**
     * Call NSZC0330 for Search Option
     * @param bizMsg NFBL1120CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    public static boolean callSrchOptApi(NFBL1120CMsg bizMsg, NSZC033001PMsg pMsg) {

        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int i = 0; i < pMsg.xxMsgIdList.length(); i++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)) {
                    String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Pagination <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagination(NFBL1120CMsg cMsg, NFBL1120SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum_A.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_A.setValue(pageFrom + cMsg.A.getValidCount());

    }

    /**
     * Create Save Option PullDown list
     * @param bizMsg NFBL2060CMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void createPulldownListSaveOpt(NFBL1120CMsg bizMsg, String userId, String glblCmpyCd) {

        bizMsg.srchOptPk_H.clear();
        bizMsg.srchOptNm_H.clear();

        SAVE_SRCH_OPTTMsg saveSrchOptTMsg = new SAVE_SRCH_OPTTMsg();
        saveSrchOptTMsg.setSQLID("001");
        saveSrchOptTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        saveSrchOptTMsg.setConditionValue("srchOptAplId01", BIZ_ID);
        saveSrchOptTMsg.setConditionValue("srchOptUsrId01", userId);

        SAVE_SRCH_OPTTMsgArray saveSrchOptTMsgArray = (SAVE_SRCH_OPTTMsgArray) EZDTBLAccessor.findByCondition(saveSrchOptTMsg);
        for (int i = 0; i < saveSrchOptTMsgArray.length(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_H.no(i), saveSrchOptTMsgArray.no(i).srchOptPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_H.no(i), saveSrchOptTMsgArray.no(i).srchOptNm);
        }
    }


    /**
     * isExistSaveSearchName
     * @param bizMsg NFBL1120CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NFBL1120CMsg bizMsg) {

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_H.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }


    /**
     * setSelectSaveSearchName
     * @param bizMsg NFBL1120CMsg
     * @param pMsg NSZC033001PMsg
     */
    public static void setSelectSaveSearchName(NFBL1120CMsg bizMsg, NSZC033001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_H.no(i));
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_H.no(i));
            }
        }
        return;
    }

    /**
     * isSameSaveSearchName
     * @param bizMsg NFBL1120CMsg
     * @return boolean
     */
    public static boolean isSameSaveSearchName(NFBL1120CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return false;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_H.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    // START 2016/10/05 J.Kim [QC#5521,ADD]

}
