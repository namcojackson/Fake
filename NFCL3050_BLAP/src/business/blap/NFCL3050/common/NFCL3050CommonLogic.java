/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3050.common;

import static business.blap.NFCL3050.constant.NFCL3050Constant.BIZ_ID;
import static business.blap.NFCL3050.constant.NFCL3050Constant.MONTH_INDEX;
import static business.blap.NFCL3050.constant.NFCL3050Constant.NFAM0035E;
import static business.blap.NFCL3050.constant.NFCL3050Constant.NFCM0048I;
import static business.blap.NFCL3050.constant.NFCL3050Constant.NFCM0594E;
import static business.blap.NFCL3050.constant.NFCL3050Constant.NFCM0615E;
import static business.blap.NFCL3050.constant.NFCL3050Constant.NZZM0001W;
import static business.blap.NFCL3050.constant.NFCL3050Constant.PERCENT;
import static business.blap.NFCL3050.constant.NFCL3050Constant.SCRN_ID_00;
import static business.blap.NFCL3050.constant.NFCL3050Constant.YEAR_INDEX;
import static business.blap.NFCL3050.constant.NFCL3050Constant.ZZZM9003I;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static parts.common.EZDCommonFunc.isNumberCheck;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NFCL3050.NFCL3050CMsg;
import business.blap.NFCL3050.NFCL3050Query;
import business.blap.NFCL3050.NFCL3050SMsg;
import business.blap.NFCL3050.NFCL3050_ACMsg;
import business.blap.NFCL3050.NFCL3050_ACMsgArray;
import business.blap.NFCL3050.NFCL3050_ASMsg;
import business.blap.NFCL3050.NFCL3050_ASMsgArray;
import business.blap.NFCL3050.constant.NFCL3050Constant;
import business.db.AJE_INV_ACCT_DISTTMsg;
import business.db.AJE_INV_ACCT_DISTTMsgArray;
import business.db.AR_ACCT_DTTMsg;
import business.db.DS_INV_TPTMsg;
import business.db.INVTMsg;
import business.db.INV_TPTMsg;
import business.db.SYS_SRCTMsg;
import business.parts.NFZC103001PMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC103001.NFZC103001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DSPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * NFCL3050CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/17   Fujitsu         S.Fujita        Create          N/A
 * 2016/03/22   CSAI            K.Uramori       Update          QC#5770
 * 2016/04/12   CSAI            K.Uramori       Update          QC#6925
 * 2016/04/25   Fujitsu         S.Fujita        Update          QC#5047
 * 2016/05/19   Fujitsu         S.Fujita        Update          QC#7780
 * 2016/06/24   Hitachi         K.Kojima        Update          QC#8026
 * 2016/07/12   Hitachi         K.Kojima        Update          QC#11049
 * 2016/07/14   Hitachi         K.Kojima        Update          QC#11507
 * 2016/08/23   Fujitsu         S.Fujita        Update          QC#13478
 * 2016/09/05   Hitachi         K.Kojima        Update          QC#11049
 * 2016/09/07   Fujitsu         S.Fujita        Update          QC#14287
 * 2017/03/17   Fujitsu         T.Murai         Update          QC#14205
 * 2017/11/20   Fujitsu         W.Honda         Update          QC#22498
 * 2018/03/27   Fujitsu         H.Ikeda         Update          QC#22736
 * 2018/07/30   CITS            K.Ogino         Update          QC#26680
 * 2018/10/24   Hitachi         J.Kim           Update          QC#28869
 * 2022/11/29   Hitachi         M.Nakajima      Update          QC#60742
 * 2024/03/05   Hitachi         TZ.Win          Update          QC#63665
 *</pre>
 */
public class NFCL3050CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NFCL3050CMsg
     * @param srchOptUsrId String
     */
    public static void createSavedSearchOptionsPullDown(NFCL3050CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NFCL3050Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.srchOptPk_H.clear();
            bizMsg.srchOptNm_H.clear();
            return;
        }

        bizMsg.srchOptPk_H.clear();
        bizMsg.srchOptNm_H.clear();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_H.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_H.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_H.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }
    }

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NFCL3050CMsg
     * @param glblMsg NFCL3050SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forDeleteSearch(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I"
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Delete Search") });
        }
    }
    /**
     * callNszc0330forSaveSearch
     * @param bizMsg NFCL3050CMsg
     * @param glblMsg NFCL3050SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forSaveSearch(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)
                || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.billToCustAcctNm_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.billToCustAcctCd_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.shipToLocNm_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.shipToCustCd_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.invNum_FR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.invNum_TO.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.xxFromDt_FR)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.xxFromDt_FR.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxToDt_TO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.xxToDt_TO.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dueDt_FR)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.dueDt_FR.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dueDt_TO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.dueDt_TO.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dealRmngBalGrsAmt_LO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.dealRmngBalGrsAmt_LO.getValue().toString());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dealRmngBalGrsAmt_HI)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.dealRmngBalGrsAmt_HI.getValue().toString());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.invTotDealNetAmt_LO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.invTotDealNetAmt_LO.getValue().toString());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.invTotDealNetAmt_HI)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.invTotDealNetAmt_HI.getValue().toString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.dsInvTpCd_L.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.invTpCd_L.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.mdlNm_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, bizMsg.serNum_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, bizMsg.xxChkBox_CL.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, bizMsg.xxChkBox_CN.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, bizMsg.xxChkBox_ER.getValue());
        // START 2016/06/24 K.Kojima [QC#8026,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, bizMsg.sysSrcCd_L.getValue());
        // END 2016/06/24 K.Kojima [QC#8026,ADD]
        // START 2016/07/12 K.Kojima [QC#11049,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, bizMsg.glDt_FR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, bizMsg.glDt_TO.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, bizMsg.srcSysDocNum.getValue());
        // START 2016/08/23 S.Fujita [QC#13478,QC#14287,MOD]
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, bizMsg.xxChkBox_IC.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_H)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, bizMsg.xxRadioBtn_H.getValue().toString());
        }
        // END   2016/08/23 S.Fujita [QC#13478,QC#14287,MOD]
        // END 2016/07/12 K.Kojima [QC#11049,ADD]

        // START 2024/03/05 TZ.Win [QC#63665, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.custIncdtId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, bizMsg.custIncdtId.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxCratDt_FR)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_28, bizMsg.xxCratDt_FR.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxCratDt_TO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_29, bizMsg.xxCratDt_TO.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.ezInUserID)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_30, bizMsg.ezInUserID.getValue());
        }
        // END 2024/03/05 TZ.Win [QC#63665, ADD]

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I" //
                    , new String[] {converter.convLabel2i18nLabel(NFCL3050Constant.SCRN_ID_00, "Save Search") });
        }

    }

    private static boolean isSameSaveSearchName(NFCL3050CMsg bizMsg) {
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

    /**
     * isExistSaveSearchName
     * @param bizMsg NFCL3050CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NFCL3050CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_H.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)
                        && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NFCL3050CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NFCL3050CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_H.no(i));
            }
        }
        return;
    }

    private static boolean callNszc0330(NFCL3050CMsg bizMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
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
     * callNszc0330forSearchOption
     * @param bizMsg NFCL3050CMsg
     * @param glblMsg NFCL3050SMsg
     * @param usrId String
     */
    public static void callNszc0330forSearchOption(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg, String usrId) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NFCL3050Constant.BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm_H, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd_H, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm_H, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_H, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.invNum_FR, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.invNum_TO, pMsg.srchOptTxt_06);
        if (pMsg.srchOptTxt_07.getValue() != null) {
          if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_07.getValue(), "yyyyMMdd")) {
              ZYPEZDItemValueSetter.setValue(bizMsg.xxFromDt_FR, pMsg.srchOptTxt_07.getValue());
          } else {
            bizMsg.xxFromDt_FR.clear();
          }
        }
        if (pMsg.srchOptTxt_08.getValue() != null) {
          if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_08.getValue(), "yyyyMMdd")) {
              ZYPEZDItemValueSetter.setValue(bizMsg.xxToDt_TO, pMsg.srchOptTxt_08.getValue());
          } else {
              bizMsg.xxToDt_TO.clear();
          }
        }
        if (pMsg.srchOptTxt_09.getValue() != null) {
          if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_09.getValue(), "yyyyMMdd")) {
              ZYPEZDItemValueSetter.setValue(bizMsg.dueDt_FR, pMsg.srchOptTxt_09.getValue());
          } else {
              bizMsg.dueDt_FR.clear();
          }
        }
        if (pMsg.srchOptTxt_10.getValue() != null) {
          if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_10.getValue(), "yyyyMMdd")) {
              ZYPEZDItemValueSetter.setValue(bizMsg.dueDt_TO, pMsg.srchOptTxt_10.getValue());
          } else {
              bizMsg.dueDt_TO.clear();
          }
        }
        if (isNumberCheck(pMsg.srchOptTxt_11.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealRmngBalGrsAmt_LO, new BigDecimal(pMsg.srchOptTxt_11.getValue()));
        } else {
            bizMsg.dealRmngBalGrsAmt_LO.clear();
        }
        if (isNumberCheck(pMsg.srchOptTxt_12.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealRmngBalGrsAmt_HI, new BigDecimal(pMsg.srchOptTxt_12.getValue()));
        } else {
            bizMsg.dealRmngBalGrsAmt_HI.clear();
        }
        if (isNumberCheck(pMsg.srchOptTxt_13.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invTotDealNetAmt_LO, new BigDecimal(pMsg.srchOptTxt_13.getValue()));
        } else {
            bizMsg.invTotDealNetAmt_LO.clear();
        }
        if (isNumberCheck(pMsg.srchOptTxt_14.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invTotDealNetAmt_HI, new BigDecimal(pMsg.srchOptTxt_14.getValue()));
        } else {
            bizMsg.invTotDealNetAmt_HI.clear();
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.dsInvTpCd_L, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(bizMsg.invTpCd_L, pMsg.srchOptTxt_16);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdlNm_H, pMsg.srchOptTxt_17);
        ZYPEZDItemValueSetter.setValue(bizMsg.serNum_H, pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CL, pMsg.srchOptTxt_19);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CN, pMsg.srchOptTxt_20);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_ER, pMsg.srchOptTxt_21);
        // START 2016/06/24 K.Kojima [QC#8026,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.sysSrcCd_L, pMsg.srchOptTxt_22);
        // END 2016/06/24 K.Kojima [QC#8026,ADD]
        // START 2016/07/12 K.Kojima [QC#11049,ADD]
        if (pMsg.srchOptTxt_23.getValue() != null) {
            if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_23.getValue(), "yyyyMMdd")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.glDt_FR, pMsg.srchOptTxt_23.getValue());
            } else {
                bizMsg.glDt_FR.clear();
            }
        }
        if (pMsg.srchOptTxt_24.getValue() != null) {
            if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_24.getValue(), "yyyyMMdd")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.glDt_TO, pMsg.srchOptTxt_24.getValue());
            } else {
                bizMsg.glDt_TO.clear();
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.srcSysDocNum, pMsg.srchOptTxt_25);
        // START 2016/08/23 S.Fujita [QC#13478,QC#14287,MOD]
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_IC, pMsg.srchOptTxt_26);
        if (isNumberCheck(pMsg.srchOptTxt_26.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_H, new BigDecimal(pMsg.srchOptTxt_26.getValue()));
        }
        // END   2016/08/23 S.Fujita [QC#13478,QC#14287,MOD]
        // END 2016/07/12 K.Kojima [QC#11049,ADD]

        // START 2024/03/05 TZ.Win [QC#63665, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.custIncdtId, pMsg.srchOptTxt_27.getValue());

        if (pMsg.srchOptTxt_28.getValue() != null) {
            if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_28.getValue(), "yyyyMMdd")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxCratDt_FR, pMsg.srchOptTxt_28.getValue());
            } else {
                bizMsg.xxCratDt_FR.clear();
            }
        } 
        if (pMsg.srchOptTxt_29.getValue() != null) {
          if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_29.getValue(), "yyyyMMdd")) {
              ZYPEZDItemValueSetter.setValue(bizMsg.xxCratDt_TO, pMsg.srchOptTxt_29.getValue());
          } else {
              bizMsg.xxCratDt_TO.clear();
          }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.ezInUserID, pMsg.srchOptTxt_30);
        // END 2024/03/05 TZ.Win [QC#63665, ADD]
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    // START 2017/03/17 [QC#14205,MOD]
    //public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {
    public static void loadOnePageToCMsg(NFCL3050CMsg cMsg, NFCL3050_ACMsgArray cMsgArray, NFCL3050_ASMsgArray sMsgArray) {
    // END 2017/03/17 [QC#14205,MOD]

        NFCL3050CMsg bizMsg = (NFCL3050CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {
                // START 2017/03/17 [QC#14205,MOD]
//                EZDMsg sLineMsg = sMsgArray.get(i);
//                int indexOfCMsg = i - startIndex;
//
//                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);
                int indexOfCMsg = i - startIndex;

                NFCL3050_ASMsg sLineMsg = (NFCL3050_ASMsg) sMsgArray.get(i);
                NFCL3050_ACMsg cLineMsg = (NFCL3050_ACMsg) cMsgArray.get(indexOfCMsg);

                EZDMsg.copy(sLineMsg, null, cLineMsg, null);
                cLineMsg.xxRecHistCratByNm_A.setValue(ZYPRecHistUtil.getFullNameForRecHist(sLineMsg.xxRecHistCratByNm_A.getValue()));
                cLineMsg.xxRecHistUpdByNm_A.setValue(ZYPRecHistUtil.getFullNameForRecHist(sLineMsg.xxRecHistUpdByNm_A.getValue()));

                // END 2017/03/17 [QC#14205,MOD]

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * It copy 'NFCL3050CMsg.A' to 'NFCL3050SMsg.A' .
     * @param bizMsg NFCL3050CMsg
     * @param globalMsg NFCL3050SMsg
     */
    public static void saveCurrentPageToSMsg(NFCL3050CMsg bizMsg, NFCL3050SMsg globalMsg) {
        bizMsg.clearErrorInfo();
        bizMsg.A.clearErrorInfo();
        globalMsg.clearErrorInfo();
        globalMsg.A.clearErrorInfo();

        int fromIdx = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, globalMsg.A.no(fromIdx + i), null);
        }
    }
    
    //---- start add 2016/03/22 QC#5770
    public static void callAcctDistAPI(NFCL3050CMsg bizMsg) {

        NFZC103001 invDistAPI = new NFZC103001();
        List<NFZC103001PMsg> pMsgList = new ArrayList<NFZC103001PMsg>();

        
        for (int i=0; i < bizMsg.A.getValidCount(); i++) {
            
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A.getValue())) {
                NFZC103001PMsg pMsg = new NFZC103001PMsg();
                setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                setValue(pMsg.invNum, bizMsg.A.no(i).invNum_A.getValue());
                // START 2016/09/05 K.Kojima [QC#11049,MOD]
                // setValue(pMsg.procDt,
                // bizMsg.A.no(i).invDt_A.getValue());
                setValue(pMsg.procDt, ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
                // END 2016/09/05 K.Kojima [QC#11049,MOD]
                
                pMsgList.add(pMsg);
                
                // call one by one to update INV.
                invDistAPI.excute(pMsgList, ONBATCH_TYPE.ONLINE);
                
                for(NFZC103001PMsg pmsg : pMsgList) {
                    if (pmsg.xxMsgIdList.getValidCount() == 0) {
                        
                        // no error
                        // update FNLZ_INV_FLG of INV table
                        INVTMsg invTmsg = new INVTMsg();
                        
                        setValue(invTmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                        setValue(invTmsg.invNum, bizMsg.A.no(i).invNum_A.getValue());
                        try {
                            invTmsg = (INVTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(invTmsg);
                        } catch (EZDDBRecordLockedException ex) {
                            bizMsg.setMessageInfo(NFCM0615E, new String[] {"Invoice"});
                            return;
                        }
                        
                        if (invTmsg == null) {
                            bizMsg.setMessageInfo(NFAM0035E, new String[] {"Invoice Finalization", "Invoice cannot be retrieved."});
                            return;
                        }
                        
                        if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(i).ezUpTime_A.getValue(), bizMsg.A.no(i).ezUpTimeZone_A.getValue(), invTmsg.ezUpTime.getValue(), invTmsg.ezUpTimeZone.getValue())) {
                            bizMsg.setMessageInfo(NFCM0594E, new String[] {"INV#".concat(bizMsg.A.no(i).invNum_A.getValue())});
                            return;
                        }
                        
                        // START 2017/11/20 W.Honda [QC#22498,ADD]
                        AJE_INV_ACCT_DISTTMsg inMsg = new AJE_INV_ACCT_DISTTMsg();

                        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
                        inMsg.setConditionValue("invNum01", invTmsg.invNum.getValue());
                        inMsg.setSQLID("001");

                        AJE_INV_ACCT_DISTTMsgArray outMsgArray = (AJE_INV_ACCT_DISTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

                        if (outMsgArray == null || outMsgArray.length() <= 0) {
                            // Basically it can not be an error.
                            bizMsg.setMessageInfo(NFAM0035E, new String[] {"Invoice Accounting Date Update", "account distribution cannot be retrieved."});
                            return;
                        }

                        setValue(invTmsg.acctDt, outMsgArray.no(0).glDt);
                        // END   2017/11/20 W.Honda [QC#22498,ADD]
                        // START 2018/03/27 H.Ikeda [QC#22736,MOD]
                        //setValue(invTmsg.fnlzInvFlg, ZYPConstant.FLG_ON_Y);
                        setValue(invTmsg.itrlInvErrFlg, ZYPConstant.FLG_OFF_N);
                        // END   2018/03/27 H.Ikeda [QC#22736,MOD]
                        EZDTBLAccessor.update(invTmsg);
                    }

                }
                
                pMsgList.clear();
            }
        }
        
        // success message
        bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Account Distribution Regeneration"});
       
    }
    
    // moved from BL02
    /**
     * search Mod QC#26680 Performance
     * @param glblCmpyCd String
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    public static void search(String glblCmpyCd, NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(1000);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NFCL3050Query.getInstance().getClass());

            ps = ssmLLClient.createPreparedStatement("getInvList", createSearchParams(glblCmpyCd, bizMsg, glblMsg), execParam);
            rs = ps.executeQuery();

            int cnt = 0;
            if (!rs.next()) {
                bizMsg.setMessageInfo(NFCM0048I);

                bizMsg.A.setValidCount(0);
                bizMsg.xxPageShowFromNum.clear();
                bizMsg.xxPageShowToNum.clear();
                bizMsg.xxPageShowOfNum.clear();

            } else {

                do {
                    // set global
                    if (cnt > glblMsg.A.length() - 1) {
                        bizMsg.setMessageInfo(NZZM0001W);
                        cnt = glblMsg.A.length();
                        break;
                    }

                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).invNum_A, rs.getString("INV_NUM"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).invDt_A, rs.getString("INV_DT"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).acctDt_A, rs.getString("ACCT_DT"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).invDueDt_A, rs.getString("INV_DUE_DT"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).invTotDealNetAmt_A, rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).invTpNm_A, rs.getString("INV_TP_NM"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).billToCustAcctNm_A, rs.getString("BILL_TO_CUST_ACCT_NM"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).billToCustAcctCd_A, rs.getString("BILL_TO_CUST_ACCT_CD"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).xxScrItem61Txt_B, rs.getString("SLS_REP_TOC_NM"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).dsInvTpNm_A, rs.getString("DS_INV_TP_NM"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).dsContrNum_A, rs.getString("DS_CONTR_NUM"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).invSrcTxt_A, rs.getString("INV_SRC_TXT"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).dealRmngBalGrsAmt_A, rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).arCashApplyStsNm_A, rs.getString("AR_CASH_APPLY_STS_NM"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).dealCltDsptAmt_A, rs.getBigDecimal("DEAL_CLT_DSPT_AMT"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).cltDsptDt_A, rs.getString("CLT_DSPT_DT"));
                    if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_ER) && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_ER.getValue())) {
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).invErrMsgTxt_A, rs.getString("INV_ERR_MSG_TXT"));
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).invldValTxt_A, rs.getString("INVLD_VAL_TXT"));
                    }
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).ezUpTime_A, rs.getString("EZUPTIME"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).ezUpTimeZone_A, rs.getString("EZUPTIMEZONE"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).sysSrcNm_A, rs.getString("SYS_SRC_NM"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).glDt_A, rs.getString("GL_DT"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).fnlzInvFlg_A, rs.getString("FNLZ_INV_FLG"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).custIssPoNum_A, rs.getString("CUST_ISS_PO_NUM"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).xxRecHistCratTs_A, rs.getString("XX_REC_HIST_CRAT_TS"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).xxRecHistCratByNm_A, rs.getString("XX_REC_HIST_CRAT_BY_NM"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).xxRecHistUpdTs_A, rs.getString("XX_REC_HIST_UPD_TS"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).xxRecHistUpdByNm_A, rs.getString("XX_REC_HIST_UPD_BY_NM"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).xxRecHistTblNm_A, rs.getString("XX_REC_HIST_TBL_NM"));
                    // START 2022/11/29 M.Nakajima [QC#60742,ADD]
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).cltPsnNm_A, rs.getString("CLT_PSN_NM"));
                    // END 2022/11/29 M.Nakajima [QC#60742,ADD]
                    cnt++;

                } while (rs.next());

                glblMsg.A.setValidCount(cnt);

                bizMsg.xxPageShowFromNum.setValue(1);
                NFCL3050CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            }

        } catch (SQLException e) {
            throw new S21AbendException(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    // START 2016/04/25 S.Fujita [QC#5047,MOD]
    /**
     * setSearchFlg
     * @param character String
     * @return searchFlg String
     */
    public static String setSearchFlg(String character) {

        String searchFlg;
        if (character.contains(PERCENT)) {
            searchFlg = ZYPConstant.FLG_ON_Y;
        } else {
            searchFlg = ZYPConstant.FLG_OFF_N;
        }

        return searchFlg;
    }
    // END 2016/04/25 S.Fujita [QC#5047,MOD]

    // START 2016/09/05 K.Kojima [QC#11049,DEL]
    // // START 2016/05/19 S.Fujita [QC#7780,ADD]
    // /**
    // * checkAcctDist
    // * @param bizMsg
    // * @return
    // */
    // public static boolean checkAcctDist(NFCL3050CMsg bizMsg) {
    // boolean isNotError = true;
    //
    // getArAcctDt(bizMsg);
    //
    // String slsProcDt =
    // ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue());
    // String slsProcYM = getYearMonth(slsProcDt);
    // String preSlsProcYM = getYearMonth(getBeforeMonth(slsProcDt,
    // "yyyyMMdd"));
    // String acctDtYM = getYearMonth(bizMsg.acctDt.getValue());
    //
    // for (int i=0; i < bizMsg.A.getValidCount(); i++) {
    //
    // if
    // (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A.getValue()))
    // {
    // String invAcctDtYM =
    // getYearMonth(bizMsg.A.no(i).acctDt_A.getValue());
    //
    // if (acctDtYM.equals(slsProcYM)) {
    // if (!slsProcYM.equals(invAcctDtYM)) {
    // isNotError = false;
    // bizMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NFCM0844E);
    // } else {
    // // do nothing.
    // }
    // } else {
    // if (!slsProcYM.equals(invAcctDtYM) &&
    // !preSlsProcYM.equals(invAcctDtYM)) {
    // isNotError = false;
    // bizMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NFCM0844E);
    // } else {
    // // do nothing.
    // }
    // }
    // }
    // }
    // return isNotError;
    // }
    // END 2016/09/05 K.Kojima [QC#11049,DEL]

    /**
     * getArAcctDt
     * @param bizMsg
     * @return
     */
    public static boolean getArAcctDt(NFCL3050CMsg bizMsg) {
        
        String arSubSysId = ZYPCodeDataUtil.getVarCharConstValue("AR_SUB_SYS_ID", bizMsg.glblCmpyCd.getValue());

        AR_ACCT_DTTMsg inMsg = new AR_ACCT_DTTMsg();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.subSysCd.setValue(arSubSysId);
        inMsg.onlBatTpCd.setValue("1");
        
        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);
        if(outMsg == null) {
            return false;
        }

        bizMsg.acctDt.setValue(outMsg.acctDt.getValue());

        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param param String
     * @return String
     */
    public static String getYearMonth(String param) {

        StringBuilder yearManth = new StringBuilder();
        String yymmdd = ZYPDateUtil.convertFormat(param, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_YYMMDD, null);
        String[] ymd = ZYPDateUtil.getSplitDay(yymmdd);
        yearManth.append(ymd[YEAR_INDEX]);
        yearManth.append(ymd[MONTH_INDEX]);
        return yearManth.toString();
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param param String
     * @param format String
     * @return String
     */
    public static String getBeforeMonth(String param, String format) {

        String retVal = "";

        SimpleDateFormat ft = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        try {

            cal.setTime(ft.parse(param));
            cal.add(Calendar.MONTH, -1);
            retVal = ft.format(cal.getTime());

        } catch (ParseException pe) {
            EZDDebugOutput.println(1, "getBeforeMonth() param:" + param + ", format:" + format, new NFCL3050CommonLogic());
        }
        return retVal;
    }
    // END 2016/05/19 S.Fujita [QC#7780,ADD]

    // START 2016/07/14 K.Kojima [QC#11507,ADD]
    /**
     * @param bizMsg NFCL3050CMsg
     */
    public static void createPulldownListInvoiceType(NFCL3050CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NFCL3050Query.getInstance().getInvoiceTpList();
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        List<DS_INV_TPTMsg> outMsg = (List<DS_INV_TPTMsg>) ssmResult.getResultObject();
        for (int i = 0; i < outMsg.size(); i++) {
            if (i >= bizMsg.dsInvTpCd_H.length()) {
                break;
            }
            DS_INV_TPTMsg dsInvTpTMsg = (DS_INV_TPTMsg) outMsg.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsInvTpCd_H.no(i), dsInvTpTMsg.dsInvTpCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsInvTpNm_H.no(i), dsInvTpTMsg.dsInvTpNm);
        }
    }

    /**
     * @param bizMsg NFCL3050CMsg
     */
    public static void createPulldownListInvoiceClass(NFCL3050CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NFCL3050Query.getInstance().getInvoiceClassList();
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        List<INV_TPTMsg> outMsg = (List<INV_TPTMsg>) ssmResult.getResultObject();
        for (int i = 0; i < outMsg.size(); i++) {
            if (i >= bizMsg.invTpCd_H.length()) {
                break;
            }
            INV_TPTMsg invTpTMsg = (INV_TPTMsg) outMsg.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.invTpCd_H.no(i), invTpTMsg.invTpCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.invTpNm_H.no(i), invTpTMsg.invTpNm);
        }
    }

    /**
     * @param bizMsg NFCL3050CMsg
     */
    public static void createPulldownListSoruce(NFCL3050CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NFCL3050Query.getInstance().getSourceList();
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        List<SYS_SRCTMsg> outMsg = (List<SYS_SRCTMsg>) ssmResult.getResultObject();
        for (int i = 0; i < outMsg.size(); i++) {
            if (i >= bizMsg.dsInvTpCd_H.length()) {
                break;
            }
            SYS_SRCTMsg sysSrcTMsg = (SYS_SRCTMsg) outMsg.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.sysSrcCd_H.no(i), sysSrcTMsg.sysSrcCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.sysSrcNm_H.no(i), sysSrcTMsg.sysSrcNm);
        }
    }
    // END 2016/07/14 K.Kojima [QC#11507,ADD]

    /**
     * createSearchParams
     */
    private static Map<String, Object> createSearchParams(String glblCmpyCd, NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cMsg", bizMsg);
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("billToCustAcctNm_H", bizMsg.billToCustAcctNm_H.getValue());
        params.put("billToCustAcctCd_H", bizMsg.billToCustAcctCd_H.getValue());
        params.put("shipToLocNm_H", bizMsg.shipToLocNm_H.getValue());
        params.put("shipToCustCd_H", bizMsg.shipToCustCd_H.getValue());
        params.put("invNum_FR", bizMsg.invNum_FR.getValue());
        String searchFlg = NFCL3050CommonLogic.setSearchFlg( bizMsg.invNum_FR.getValue());
        params.put("searchFlg", searchFlg);
        params.put("invNum_TO", bizMsg.invNum_TO.getValue());
        params.put("xxFromDt_FR", bizMsg.xxFromDt_FR.getValue());
        params.put("xxToDt_TO", bizMsg.xxToDt_TO.getValue());
        params.put("dueDt_FR", bizMsg.dueDt_FR.getValue());
        params.put("dueDt_TO", bizMsg.dueDt_TO.getValue());
        params.put("dealRmngBalGrsAmt_LO", bizMsg.dealRmngBalGrsAmt_LO.getValue());
        params.put("dealRmngBalGrsAmt_HI", bizMsg.dealRmngBalGrsAmt_HI.getValue());
        params.put("invTotDealNetAmt_LO", bizMsg.invTotDealNetAmt_LO.getValue());
        params.put("invTotDealNetAmt_HI", bizMsg.invTotDealNetAmt_HI.getValue());
        params.put("dsInvTpCd_L", bizMsg.dsInvTpCd_L.getValue());
        params.put("invTpCd_L", bizMsg.invTpCd_L.getValue());
        params.put("sysSrcCd_L", bizMsg.sysSrcCd_L);
        params.put("mdlNm_H", bizMsg.mdlNm_H.getValue());
        params.put("serNum_H", bizMsg.serNum_H.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.mdlNm_H.getValue()) || ZYPCommonFunc.hasValue(bizMsg.serNum_H.getValue())) {
            params.put("svcInvLineFlg", ZYPConstant.FLG_ON_Y);
        } else {
            params.put("svcInvLineFlg", ZYPConstant.FLG_OFF_N);
        }

        params.put("grpInvNum_H", bizMsg.grpInvNum_H.getValue());

        if (!ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_CL.getValue())) {
            List<String> inCloInv = new ArrayList<String>();
            inCloInv.add(AR_CASH_APPLY_STS.APPLIED);
            inCloInv.add(AR_CASH_APPLY_STS.VOID);
            params.put("inCloInv", inCloInv);
        }

        params.put("invTpCRM", INV_TP.CREDIT_MEMO);
        params.put("Applied", AR_CASH_APPLY_STS.APPLIED);
        params.put("Unpplied", AR_CASH_APPLY_STS.UNAPPLIED);
        params.put("Partial", AR_CASH_APPLY_STS.PARTIAL);
        params.put("Void", AR_CASH_APPLY_STS.VOID);
        params.put("cltDsptStsCdApproved", CLT_DSPT_STS.APPROVED);
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("flgN", ZYPConstant.FLG_OFF_N);
        params.put("rowNum", glblMsg.A.length() + 1);

        // START 2024/03/05 TZ.Win [QC#63665, ADD]
        params.put("custIncdtId", bizMsg.custIncdtId.getValue());
        params.put("xxCratDt_FR", bizMsg.xxCratDt_FR.getValue());
        params.put("xxCratDt_TO", bizMsg.xxCratDt_TO.getValue());
        params.put("ezInUserID", bizMsg.ezInUserID.getValue());
        // END 2024/03/05 TZ.Win [QC#63665, ADD]

        return params;
    }

    // START 2018/10/24 J.Kim [QC#28869,ADD]
    /**
     * outputSearchLog
     * @param bizMsg NFCL3050CMsg
     */
    public static void outputSearchLog(NFCL3050CMsg cMsg) {

        StringBuffer sb = new StringBuffer();
        sb.append("NFCL3050 Search Condition :");
        if (ZYPCommonFunc.hasValue(cMsg.billToCustAcctNm_H)) {
            sb.append(" Bill To Customer Name(*)[").append(cMsg.billToCustAcctNm_H.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd_H)) {
            sb.append(" Bill To Customer Number(*)[").append(cMsg.billToCustAcctCd_H.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.shipToLocNm_H)) {
            sb.append(" Ship To Customer Name(*)[").append(cMsg.shipToLocNm_H.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_H)) {
            sb.append(" Ship To Customer Number(*)[").append(cMsg.shipToCustCd_H.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.invNum_FR)) {
            sb.append(" Invoice Number From[").append(cMsg.invNum_FR.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.invNum_TO)) {
            sb.append(" Invoice Number To[").append(cMsg.invNum_TO.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxFromDt_FR) || ZYPCommonFunc.hasValue(cMsg.xxToDt_TO)) {
            sb.append(" Invoice Date[").append(cMsg.xxFromDt_FR.getValue()).append("-").append(cMsg.xxToDt_TO.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.dueDt_FR) || ZYPCommonFunc.hasValue(cMsg.dueDt_TO)) {
            sb.append(" Due Date[").append(cMsg.dueDt_FR.getValue()).append("-").append(cMsg.dueDt_TO.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.dealRmngBalGrsAmt_LO)) {
            sb.append(" Invoice Balance Low[").append(cMsg.dealRmngBalGrsAmt_LO.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.dealRmngBalGrsAmt_HI)) {
            sb.append(" Invoice Balance High[").append(cMsg.dealRmngBalGrsAmt_HI.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.invTotDealNetAmt_LO)) {
            sb.append(" Invoice Amount Low[").append(cMsg.invTotDealNetAmt_LO.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.invTotDealNetAmt_HI)) {
            sb.append(" Invoice Amount High[").append(cMsg.invTotDealNetAmt_HI.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsInvTpCd_L)) {
            sb.append(" Invoice Type[").append(cMsg.dsInvTpCd_L.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.invTpCd_L)) {
            sb.append(" Invoice Class[").append(cMsg.invTpCd_L.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.sysSrcCd_L)) {
            sb.append(" Source[").append(cMsg.sysSrcCd_L.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.glDt_FR) || ZYPCommonFunc.hasValue(cMsg.glDt_TO)) {
            sb.append(" GL Date[").append(cMsg.glDt_FR.getValue()).append("-").append(cMsg.glDt_TO.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.srcSysDocNum)) {
            sb.append(" Source Number(*)[").append(cMsg.srcSysDocNum.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.grpInvNum_H)) {
            sb.append(" Bill Number(*)[").append(cMsg.grpInvNum_H.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.mdlNm_H)) {
            sb.append(" Model Number(*)[").append(cMsg.mdlNm_H.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_CL)) {
            sb.append(" Include Closed Invoices[").append(cMsg.xxChkBox_CL.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.serNum_H)) {
            sb.append(" Serial Number(*)[").append(cMsg.serNum_H.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_CN)) {
            sb.append(" Include Consolidated Invoices[").append(cMsg.xxChkBox_CN.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.custIssPoNum_H)) {
            sb.append(" Customer PO Number(*)[").append(cMsg.custIssPoNum_H.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxRadioBtn_H)) {
            sb.append(" Completed /Incompleted Invoices[").append(cMsg.xxRadioBtn_H.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_ER)) {
            sb.append(" Invoice Error[").append(cMsg.xxChkBox_ER.getValue()).append("]");
        }

        // START 2024/03/05 TZ.Win [QC#63665, ADD]
        if (ZYPCommonFunc.hasValue(cMsg.custIncdtId)) {
            sb.append(" CI Number[").append(cMsg.custIncdtId.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxCratDt_FR) || ZYPCommonFunc.hasValue(cMsg.xxCratDt_TO)) {
            sb.append(" Creation Date[").append(cMsg.xxCratDt_FR.getValue()).append("-").append(cMsg.xxCratDt_TO.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.ezInUserID)) {
            sb.append(" Created by[").append(cMsg.ezInUserID.getValue()).append("]");
        }
        // END 2024/03/05 TZ.Win [QC#63665, ADD]

        S21InfoLogOutput.println(sb.toString());
    }
    // END 2018/10/24 J.Kim [QC#28869,ADD]
}
