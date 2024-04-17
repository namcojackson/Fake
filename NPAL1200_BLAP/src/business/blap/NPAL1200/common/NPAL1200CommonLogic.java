/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1200.common;

import static business.blap.NPAL1200.constant.NPAL1200Constant.BUSINESS_APPLICATION_ID;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_EFF_FROM_DT;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_EFF_THRU_DT;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_FROM_RTL_WH_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_FROM_SRC_ZN_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_INSRC_ENBL_FLG;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_MDSE_ITEM_CLS_TP_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_TO_RTL_WH_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_TO_SRC_ZN_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.EZUPTIME;
import static business.blap.NPAL1200.constant.NPAL1200Constant.EZUPTIMEZONE;
import static business.blap.NPAL1200.constant.NPAL1200Constant.FROM_RTL_WH_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.FROM_SRC_ZN_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.INSRC_ENBL_FLG;
import static business.blap.NPAL1200.constant.NPAL1200Constant.INSRC_ITEM_PRC_THRHD_AMT;
import static business.blap.NPAL1200.constant.NPAL1200Constant.INSRC_RNK_SORT_NUM;
import static business.blap.NPAL1200.constant.NPAL1200Constant.INSRC_ZN_PLN_PK;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MDSE_ITEM_CLS_TP_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MODE_ADD_CANCEL;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MODE_DELETE;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MODE_NORMAL;
import static business.blap.NPAL1200.constant.NPAL1200Constant.NPAM0001W;
import static business.blap.NPAL1200.constant.NPAL1200Constant.ZZPM0037W;
import static business.blap.NPAL1200.constant.NPAL1200Constant.RTL_WH_CATG_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.RTL_WH_NM;
import static business.blap.NPAL1200.constant.NPAL1200Constant.RTL_WH_NM_FROM;
import static business.blap.NPAL1200.constant.NPAL1200Constant.RTL_WH_NM_TO;
import static business.blap.NPAL1200.constant.NPAL1200Constant.SRC_ZN_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.TO_RTL_WH_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.TO_SRC_ZN_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.ZZZM9003I;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_PRCH_REQ_TP_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_S21_WH_FLG;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_MNX_WH_FLG;
import static business.blap.NPAL1200.constant.NPAL1200Constant.PLAN_TYPE_CD_S21_WH;
import static business.blap.NPAL1200.constant.NPAL1200Constant.PLAN_TYPE_CD_MNX_WH;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_WH_MNX_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.PRCH_REQ_TP_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.WH_OWNR_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.WH_OWNR_CD_FROM;
import static business.blap.NPAL1200.constant.NPAL1200Constant.WH_OWNR_CD_TO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1200.NPAL1200CMsg;
import business.blap.NPAL1200.NPAL1200Query;
import business.blap.NPAL1200.NPAL1200SMsg;
import business.blap.NPAL1200.NPAL1200_ASMsgArray;
import business.db.RTL_WHTMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NPAL1200 Insourcing Planning Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS       Yasushi Nomura        Create          N/A
 * 02/22/2016   CITS       K.Ogino               Update          QC#4329
 * 08/22/2016   CITS       S.Endo                Update          QC#12612
 * 08/30/2016   CITS       S.Endo                Update          QC#13726
 * 01/24/2017   CITS            T.Kikuhara      Update          QC#10621
 * 03/01/2023   CITS       R.Kurahashi           Update          QC#61128
 *</pre>
 */
public class NPAL1200CommonLogic {

    /**
     * Create Pulldown Search Option
     * @param cMsg NPAL1200CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownSearchOption(NPAL1200CMsg cMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.srchOptPk_CD.clear();
        cMsg.srchOptNm_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put("srchOptAplId", BUSINESS_APPLICATION_ID);

        // Execute
        S21SsmEZDResult result = NPAL1200Query.getInstance().getSearchOptionPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map recode = resultList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_CD.no(i), (BigDecimal) recode.get("SRCH_OPT_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_DI.no(i), (String) recode.get("SRCH_OPT_NM"));

                if (i >= cMsg.srchOptPk_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Execute API NSZC033001
     * @param bizMsg NPAL1200CMsg
     * @param pMsg NSZC033001PMsg
     * @return
     */
    private static boolean executeNszc0330(NPAL1200CMsg cMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        cMsg.srchOptPk_SE.setErrorInfo(1, msgId);
                        cMsg.srchOptNm_TX.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Call API NSZC033001 (Delete SearchOption)
     * @param cMsg NPAL1200CMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearchOption(NPAL1200CMsg cMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_APPLICATION_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            createPullDownSearchOption(cMsg, glblCmpyCd);
            cMsg.srchOptNm_TX.clear();
            cMsg.srchOptPk_SE.clear();
        }
    }

    /**
     * Call API NSZC033001 (Search SearchOption)
     * @param cMsg NPAL1200CMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchSearchOption(NPAL1200CMsg cMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_APPLICATION_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (!executeNszc0330(cMsg, pMsg)) {
            return; // Error
        }

        // Set Result
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_TX, pMsg.srchOptNm);

        ZYPEZDItemValueSetter.setValue(cMsg.srcZnCd_SF, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(cMsg.srcZnCd_ST, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseItemClsTpCd_SH, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_HF, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_HT, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H1, pMsg.srchOptTxt_06);
        // QC#61128 Add Start
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_SF, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTpCd_SF, pMsg.srchOptTxt_08);
        // QC#61128 Add End
    }

    /**
     * Call API NSZC033001 (Save SearchOption)
     * @param cMsg NPAL1200CMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearchOption(NPAL1200CMsg cMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX) || isSameSaveSearchName(cMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SE);
        }
        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_TX);
        } else {
            setSelectSaveSearchName(pMsg, cMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_APPLICATION_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.srcZnCd_SF);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.srcZnCd_ST);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.mdseItemClsTpCd_SH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.rtlWhCd_HF);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.rtlWhCd_HT);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.xxChkBox_H1);
        // QC#61128 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.prchReqTpCd_SF);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.xxTpCd_SF);
        // QC#61128 Add End

        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            createPullDownSearchOption(cMsg, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_SE, pMsg.srchOptPk);
            // QC#10621 add start
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Save Search" });
            // QC#10621 add end
        }
    }

    /**
     * check duplicate search name
     * @param cMsg NPAL1200CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NPAL1200CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_DI.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_DI.no(i))) {
                return false;
            }
            if (cMsg.srchOptNm_TX.getValue().equals(cMsg.srchOptNm_DI.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SE) //
                        && cMsg.srchOptPk_SE.getValue().compareTo(cMsg.srchOptPk_CD.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * isSameSaveSearchName
     * @param cMsg NPAL1200CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NPAL1200CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SE)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_DI.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_DI.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_SE.getValue().compareTo(cMsg.srchOptPk_CD.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_TX.getValue().equals(cMsg.srchOptNm_DI.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param cMsg NPAL1200CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NPAL1200CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SE)) {
            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_DI.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_DI.no(i))) {
                return;
            }
            if (cMsg.srchOptPk_SE.getValue().compareTo(cMsg.srchOptPk_CD.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_DI.no(i));
            }
        }
        return;
    }

    /**
     * <pre>
     * Search Detail.
     * </pre>
     * @param cMsg NPAL1200CMsg
     * @param sMsg NPAL1200SMsg
     * @param glblCmpyCd String
     * @param copyFlag boolean
     * @param submitFlg boolean
     */
    public static void searchDetail(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg, String glblCmpyCd, boolean copyFlag, boolean submitFlg) {
        // Create Param
        ZYPTableUtil.clear(sMsg.A);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(cMsg.srcZnCd_SF)) {
            ssmParam.put(DB_PARAM_FROM_SRC_ZN_CD, cMsg.srcZnCd_SF.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.srcZnCd_ST)) {
            ssmParam.put(DB_PARAM_TO_SRC_ZN_CD, cMsg.srcZnCd_ST.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_HF)) {
            ssmParam.put(DB_PARAM_FROM_RTL_WH_CD, cMsg.rtlWhCd_HF.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_HT)) {
            ssmParam.put(DB_PARAM_TO_RTL_WH_CD, cMsg.rtlWhCd_HT.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.mdseItemClsTpCd_SH)) {
            ssmParam.put(DB_PARAM_MDSE_ITEM_CLS_TP_CD, cMsg.mdseItemClsTpCd_SH.getValue());
        }
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
            ssmParam.put(DB_PARAM_INSRC_ENBL_FLG, cMsg.xxChkBox_H1.getValue());
        }
        // QC#61128 Add Start
        if (ZYPCommonFunc.hasValue(cMsg.prchReqTpCd_SF.getValue())) {
            ssmParam.put(DB_PARAM_PRCH_REQ_TP_CD, cMsg.prchReqTpCd_SF.getValue());
        }
        
        if (ZYPCommonFunc.hasValue(cMsg.xxTpCd_SF.getValue())) {
            if (PLAN_TYPE_CD_S21_WH.equals(cMsg.xxTpCd_SF.getValue())) {
                ssmParam.put(DB_PARAM_S21_WH_FLG, ZYPConstant.FLG_ON_Y);
            } else if (PLAN_TYPE_CD_MNX_WH.equals(cMsg.xxTpCd_SF.getValue())) {
                ssmParam.put(DB_PARAM_MNX_WH_FLG, ZYPConstant.FLG_ON_Y);
                ssmParam.put(DB_PARAM_WH_MNX_CD, WH_OWNR.MNX);
            }
        }
        // QC#61128 Add End

        // Execute
        S21SsmEZDResult result = NPAL1200Query.getInstance().search(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            if (resultMap.size() >= sMsg.A.length()) {
                // 200 over
                if (!submitFlg) {
                    cMsg.setMessageInfo(NPAM0001W);
                }
            }
            int i = 0;
            for (; i < resultMap.size(); i++) {
                if ((sMsg.A.length()) <= i) {
                    break;
                }
                Map<String, Object> recode = (Map<String, Object>) resultMap.get(i);
                sMsg.A.no(i).xxNum_ID.setValue(i);
                sMsg.A.no(i).xxNum_M1.setValue(MODE_NORMAL);
                sMsg.A.no(i).xxNum_M2.setValue(MODE_NORMAL);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).fromSrcZnCd_D1, (String) recode.get(FROM_SRC_ZN_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).fromRtlWhCd_D1, (String) recode.get(FROM_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_D1, (String) recode.get(RTL_WH_NM_FROM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).toSrcZnCd_D1, (String) recode.get(TO_SRC_ZN_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).toRtlWhCd_D1, (String) recode.get(TO_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_D2, (String) recode.get(RTL_WH_NM_TO));
                // pulldown list
                for (int j = 0; j < sMsg.C.getValidCount(); j++) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseItemClsTpCd_CE.no(j), sMsg.C.no(j).mdseItemClsTpCd_XX);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseItemClsTpDescTxt_DE.no(j), sMsg.C.no(j).mdseItemClsTpDescTxt_XX);
                }
                // QC#61128 Add Start
                for (int k = 0; k < sMsg.D.getValidCount(); k++) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqTpCd_CE.no(k), sMsg.D.no(k).prchReqTpCd_XX);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqTpDescTxt_DE.no(k), sMsg.D.no(k).prchReqTpDescTxt_XX);
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).whOwnrCd_D1, (String) recode.get(WH_OWNR_CD_FROM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).whOwnrCd_D2, (String) recode.get(WH_OWNR_CD_TO));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqTpCd_SE, (String) recode.get(PRCH_REQ_TP_CD));
                // QC#61128 Add End
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseItemClsTpCd_SE, (String) recode.get(MDSE_ITEM_CLS_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).insrcItemPrcThrhdAmt_D1, (BigDecimal) recode.get(INSRC_ITEM_PRC_THRHD_AMT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).insrcRnkSortNum_D1, (BigDecimal) recode.get(INSRC_RNK_SORT_NUM));
                sMsg.A.no(i).insrcEnblFlg_CD.no(0).setValue(ZYPConstant.FLG_ON_Y);
                sMsg.A.no(i).insrcEnblFlg_DD.no(0).setValue(ZYPConstant.FLG_ON_Y);
                sMsg.A.no(i).insrcEnblFlg_CD.no(1).setValue(ZYPConstant.FLG_OFF_N);
                sMsg.A.no(i).insrcEnblFlg_DD.no(1).setValue(ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).insrcEnblFlg_SD, (String) recode.get(INSRC_ENBL_FLG));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRqstTs_D1, (String) recode.get(EZUPTIME));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRqstTz_D1, (String) recode.get(EZUPTIMEZONE));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).insrcZnPlnPk_D1, (BigDecimal) recode.get(INSRC_ZN_PLN_PK));
            }
            sMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum.setValue(BigDecimal.ONE);
            copyFromSMsgOntoCmsg(cMsg, sMsg);

            // Normal End
            if (!submitFlg) {
                cMsg.setMessageInfo(ZZZM9003I, new String[] {"Search" });
            }
        } else {
            // not has search result
            if (!submitFlg) {
                cMsg.setMessageInfo(ZZPM0037W);
                cMsg.xxPageShowFromNum.clear();
                cMsg.xxPageShowToNum.clear();
                cMsg.xxPageShowOfNum.clear();
            }
        }
    }

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * </pre>
     * @param cMsg NPAL1200CMsg
     * @param sMsg NPAL1200SMsg
     */
    public static void copyFromSMsgOntoCmsg(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {
        // copy data from SMsg onto CMsg
        ZYPTableUtil.clear(cMsg.A);
        setPagePos(cMsg, sMsg);

        if (0 < sMsg.A.getValidCount()) {
            int cMsgCount = 0;
            if (cMsg.xxPageShowFromNum.getValueInt() > 0) {
                    for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < sMsg.A.getValidCount(); i++) {
                        if ((cMsgCount + cMsg.xxPageShowFromNum.getValueInt() - 1) == cMsg.xxPageShowToNum.getValueInt()) {
                            break;
                        }
                        if ((sMsg.A.no(i).xxNum_M1.getValueInt() != MODE_DELETE) && (sMsg.A.no(i).xxNum_M1.getValueInt() != MODE_ADD_CANCEL)) {
                            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                            cMsgCount++;
                        }
                    }
                }
            cMsg.A.setValidCount(cMsgCount);
        }
    }

    /**
     * <pre>
     * copyFromCmsgOntoSmsg
     * Copy data From CMsg Onto SMsg
     * </pre>
     * @param sMsg NPAL1200SMsg
     * @param cMsg NPAL1200CMsg
     */
    public static void copyFromCmsgOntoSmsg(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {
        if (cMsg.A.getValidCount() == 0) {
            return;
        }

        int sIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++, sIndex++) {
            if ((sMsg.A.no(sIndex).xxNum_M1.getValueInt() != MODE_DELETE) //
                    && (sMsg.A.no(sIndex).xxNum_M1.getValueInt() != MODE_ADD_CANCEL)) {
                EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(sIndex), null);
            } else {
                i--;
            }
        }
    }

    /**
     * <pre>
         * Set page pos.
         * </pre>
     * @param cMsg NPAL1200CMsg
     * @param sMsg NPAL1200SMsg
     */
    public static void setPagePos(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {
        if ((cMsg.A.length() == 0) || (sMsg.A.length() == 0) || (sMsg.A.getValidCount() <= 0)) {
            cMsg.xxPageShowFromNum.setValue(0);
            cMsg.xxPageShowToNum.setValue(0);
            cMsg.xxPageShowOfNum.setValue(0);
            return;
        }
        int startRowCount = 0;
        if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
            startRowCount = cMsg.xxPageShowFromNum.getValueInt();
        }
        int allRowCount = countRow(sMsg.A);
        if (startRowCount == 0) {
            cMsg.xxPageShowFromNum.setValue(1);
        } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {
            // last page
            cMsg.xxPageShowFromNum.setValue(getLastPageStartCount(allRowCount, cMsg.A.length()));
        } else {
            if ((startRowCount % cMsg.A.length()) != 1) {
                startRowCount = startRowCount - (startRowCount % cMsg.A.length()) + 1;
            }
            cMsg.xxPageShowFromNum.setValue(startRowCount);
        }
        if ((cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1) < allRowCount) {
            cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1);
        } else {
            cMsg.xxPageShowToNum.setValue(allRowCount);
        }
        cMsg.xxPageShowOfNum.setValue(allRowCount);
    }

    private static int countRow(NPAL1200_ASMsgArray sMsg) {
        int ret = 0;
        for (int i = 0; i < sMsg.getValidCount(); i++) {
            if ((sMsg.no(i).xxNum_M1.getValueInt() != MODE_DELETE) && (sMsg.no(i).xxNum_M1.getValueInt() != MODE_ADD_CANCEL)) {
                ret++;
            }
        }
        return ret;
    }

    private static int getLastPageStartCount(int allRowCount, int pageRowCount) {
        if ((allRowCount <= 0) || (pageRowCount <= 0)) {
            return 0;
        }
        if (allRowCount <= pageRowCount) {
            return 1;
        }
        if (allRowCount % pageRowCount == 0) {
            return allRowCount - pageRowCount + 1;
        }
        return ((int) (allRowCount / pageRowCount)) * pageRowCount + 1;
    }

    /**
     * <pre>
         * get RtlWhName by RtlWhCd
         * </pre>
     * @param rtlWhCd String
     * @param glblCmpyCd String
     * @return RtlWhName
     */
    public static String getRtlWhName(String rtlWhCd, String glblCmpyCd) {
        RTL_WHTMsg msg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(msg.rtlWhCd, rtlWhCd);
        msg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(msg);

        if (msg != null) {
            return msg.rtlWhNm.getValue();
        }

        return null;
    }

    /**
     * get Rtl WH Info
     * @param rtlWhCd String
     * @param salesDate String
     * @param glblCmpyCd String
     * @return Rtl WH Info
     */
    public static RTL_WHTMsg getRtlWh(String rtlWhCd, String salesDate, String glblCmpyCd) {
        RTL_WHTMsg ret = null;
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, rtlWhCd);
        ssmParam.put(DB_PARAM_EFF_FROM_DT, salesDate);
        ssmParam.put(DB_PARAM_EFF_THRU_DT, salesDate);

        // Execute
        S21SsmEZDResult result = NPAL1200Query.getInstance().getRtlWh(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            if (0 < resultList.size()) {
                ret = new RTL_WHTMsg();
                Map recode = resultList.get(0);

                ZYPEZDItemValueSetter.setValue(ret.rtlWhNm, (String) recode.get(RTL_WH_NM));
                ZYPEZDItemValueSetter.setValue(ret.srcZnCd, (String) recode.get(SRC_ZN_CD));
                ZYPEZDItemValueSetter.setValue(ret.rtlWhCatgCd, (String) recode.get(RTL_WH_CATG_CD));
                // WC#61128 Add Start
                ZYPEZDItemValueSetter.setValue(ret.whOwnrCd, (String) recode.get(WH_OWNR_CD));
                // WC#61128 Add End
            }
        }
        return ret;
    }

    /**
     * get Rtl WH Info
     * @param rtlWhCdFrom String
     * @param rtlWhCdTo String
     * @param mdseItemClsTpCd String
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkInsrcZone(String rtlWhCdFrom, String rtlWhCdTo, String mdseItemClsTpCd, String glblCmpyCd) {
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_FROM_RTL_WH_CD, rtlWhCdFrom);
        ssmParam.put(DB_PARAM_TO_RTL_WH_CD, rtlWhCdTo);
        if ((mdseItemClsTpCd == null) || (mdseItemClsTpCd.length() == 0)) {
            ssmParam.put(DB_PARAM_MDSE_ITEM_CLS_TP_CD, null);
        } else {
            ssmParam.put(DB_PARAM_MDSE_ITEM_CLS_TP_CD, mdseItemClsTpCd);
        }

        // Execute
        S21SsmEZDResult result = NPAL1200Query.getInstance().checkZone(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            if (0 < resultList.size()) {
                return true;
            }
        }
        return false;
    }
    
    // QC#61128 Add Start
    /**
     * checkInsrcZone
     * @param rtlWhCdFrom String
     * @param rtlWhCdTo String
     * @param mdseItemClsTpCd String
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkInsrcZoneForPrchReqTp(String rtlWhCdFrom, String rtlWhCdTo, String mdseItemClsTpCd, String prchReqTpCd, String glblCmpyCd) {
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_FROM_RTL_WH_CD, rtlWhCdFrom);
        ssmParam.put(DB_PARAM_TO_RTL_WH_CD, rtlWhCdTo);
        ssmParam.put(DB_PARAM_PRCH_REQ_TP_CD, prchReqTpCd);
        if ((mdseItemClsTpCd == null) || (mdseItemClsTpCd.length() == 0)) {
            ssmParam.put(DB_PARAM_MDSE_ITEM_CLS_TP_CD, null);
        } else {
            ssmParam.put(DB_PARAM_MDSE_ITEM_CLS_TP_CD, mdseItemClsTpCd);
        }

        // Execute
        S21SsmEZDResult result = NPAL1200Query.getInstance().checkZoneForPrchReqTp(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            if (0 < resultList.size()) {
                return true;
            }
        }
        return false;
    }
    // QC#61128 Add End
}
