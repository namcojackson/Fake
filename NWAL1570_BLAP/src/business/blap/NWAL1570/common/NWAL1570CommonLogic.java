/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1570.common;

import static business.blap.NWAL1570.constant.NWAL1570Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;

import business.blap.NWAL1570.NWAL1570CMsg;
import business.blap.NWAL1570.NWAL1570Query;
import business.blap.NWAL1570.NWAL1570SMsg;
import business.parts.NSZC033001PMsg;

/**
 *<pre>
 * NWAL1570CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 * 2016/11/22   Fujitsu         K.Sato          Update          QC#15760
 * 2018/08/01   Fujitsu         T.Aoi           Update          QC#26304
 * 2024/02/29   CITS            T.Miki          Update          QC#63608
 *</pre>
 */
public class NWAL1570CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * Set Authority
     * @param bizMsg NWAL1570CMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setAuthority(NWAL1570CMsg bizMsg, S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);

        if (functionIds == null || functionIds.isEmpty()) {
            // ZZSM4300E=0,User @ has no permissions to operate this application.
            throw new S21AbendException("ZZSM4300E", new String[]{userProfileService.getContextUserInfo().getUserId()});
        }

        int funcIdCnt = 0;
        for (String functionId : functionIds) {
            bizMsg.F.no(funcIdCnt++).xxFuncId.setValue(functionId);
        }
        bizMsg.F.setValidCount(funcIdCnt);
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NWAL1570CMsg bizMsg = (NWAL1570CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
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
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * callNszc0330forSearchOption
     * @param bizMsg NWAL1570CMsg
     * @param glblMsg NWAL1570SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxCpoOrdNumSrchTxt_H1, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCpoOrdNumSrchTxt_H2, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNumSrchTxt, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLeasePoNumSrchTxt, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSoldToAcctNmSrchTxt, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSoldToAcctCdSrchTxt, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSoldToLocCdSrchTxt, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxShipToAcctNmSrchTxt, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxShipToAcctCdSrchTxt, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxShipToLocCdSrchTxt, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxBillToAcctNmSrchTxt, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxBillToAcctCdSrchTxt, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxBillToLocCdSrchTxt, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCoaExtnSrchTxt, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCoaBrSrchTxt, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSlsRepTocSrchTxt, pMsg.srchOptTxt_16);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCpoSrcTpSrchTxt, pMsg.srchOptTxt_17);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsBizLineSrchTxt, pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsOrdCatgSrchTxt, pMsg.srchOptTxt_19);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsOrdTpSrchTxt, pMsg.srchOptTxt_20);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsOrdRsnSrchTxt, pMsg.srchOptTxt_21);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCsmpContrNumSrchTxt, pMsg.srchOptTxt_22);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPrcContrNumSrchTxt, pMsg.srchOptTxt_23);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdSrcRefNumSrchTxt, pMsg.srchOptTxt_24);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxMdseSrchTxt, pMsg.srchOptTxt_25);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseForSlsSmrySrchTxt, pMsg.srchOptTxt_26);
        ZYPEZDItemValueSetter.setValue(bizMsg.zerothProdCtrlSrchTxt, pMsg.srchOptTxt_27);
        ZYPEZDItemValueSetter.setValue(bizMsg.firstProdCtrlSrchTxt, pMsg.srchOptTxt_28);
        ZYPEZDItemValueSetter.setValue(bizMsg.scdProdCtrlSrchTxt, pMsg.srchOptTxt_29);
        ZYPEZDItemValueSetter.setValue(bizMsg.thirdProdCtrlSrchTxt, pMsg.srchOptTxt_30);
        ZYPEZDItemValueSetter.setValue(bizMsg.frthProdCtrlSrchTxt, pMsg.srchOptTxt_31);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCoaProdSrchTxt, pMsg.srchOptTxt_32);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCoaMdseTpSrchTxt, pMsg.srchOptTxt_33);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxMdlSrchTxt, pMsg.srchOptTxt_34);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtrnRsnCd, pMsg.srchOptTxt_35);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLineCatgSrchTxt, pMsg.srchOptTxt_36);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdLineSrcSrchTxt, pMsg.srchOptTxt_37);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRtlWhSrchTxt, pMsg.srchOptTxt_38);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRtlSwhSrchTxt, pMsg.srchOptTxt_39);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxVndSrchTxt, pMsg.srchOptTxt_40);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCpoOrdNumSrchTxt, pMsg.srchOptTxt_41);
        ZYPEZDItemValueSetter.setValue(bizMsg.soNumSrchTxt, pMsg.srchOptTxt_42);
        ZYPEZDItemValueSetter.setValue(bizMsg.invNumSrchTxt, pMsg.srchOptTxt_43);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsContrNumSrchTxt, pMsg.srchOptTxt_44);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSvcConfigMstrSrchTxt, pMsg.srchOptTxt_45);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdTeamSrchTxt, pMsg.srchOptTxt_46);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdZnSrchTxt, pMsg.srchOptTxt_47);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCratByUsrSrchTxt, pMsg.srchOptTxt_48);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltModeCd, pMsg.srchOptTxt_49);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxInclImptOrdFlg, pMsg.srchOptTxt_50);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxOnlySlsOrdFlg, pMsg.srchOptTxt_51);
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_52.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ordFromDt, pMsg.srchOptTxt_52.getValue());
        } else {
            bizMsg.ordFromDt.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_53.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ordToDt, pMsg.srchOptTxt_53.getValue());
        } else {
            bizMsg.ordToDt.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_54.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxBookFromDt, pMsg.srchOptTxt_54.getValue());
        } else {
            bizMsg.xxBookFromDt.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_55.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxBookToDt, pMsg.srchOptTxt_55.getValue());
        } else {
            bizMsg.xxBookToDt.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_56.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxActlShipFromDt, pMsg.srchOptTxt_56.getValue());
        } else {
            bizMsg.xxActlShipFromDt.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_57.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxActlShipToDt, pMsg.srchOptTxt_57.getValue());
        } else {
            bizMsg.xxActlShipToDt.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_58.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invFromDt, pMsg.srchOptTxt_58.getValue());
        } else {
            bizMsg.invFromDt.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_59.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invToDt, pMsg.srchOptTxt_59.getValue());
        } else {
            bizMsg.invToDt.clear();
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.dplyBy01ItemNm, pMsg.srchOptTxt_60);
        ZYPEZDItemValueSetter.setValue(bizMsg.dplyBy02ItemNm, pMsg.srchOptTxt_61);
        ZYPEZDItemValueSetter.setValue(bizMsg.dplyBy03ItemNm, pMsg.srchOptTxt_62);
        ZYPEZDItemValueSetter.setValue(bizMsg.grpByDnldCd, pMsg.srchOptTxt_63);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg, pMsg.srchOptTxt_64);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxInclPendImptOrdFlg, pMsg.srchOptTxt_65);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllOpenOrdFlg, pMsg.srchOptTxt_66);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdHdrStsAllSelFlg, pMsg.srchOptTxt_67);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLineStsAllSelFlg, pMsg.srchOptTxt_68);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRtrnStsAllSelFlg, pMsg.srchOptTxt_69);
        String[] stsList = pMsg.srchOptTxt_70.getValue().split(",");
        for (int i = 0; i < stsList.length; i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).xxOrdHdrStsSelFlg_HS, stsList[i]);
        }
        stsList = pMsg.srchOptTxt_71.getValue().split(",");
        for (int i = 0; i < stsList.length; i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).xxLineStsSelFlg_LS, stsList[i]);
        }
        stsList = pMsg.srchOptTxt_72.getValue().split(",");
        for (int i = 0; i < stsList.length; i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.R.no(i).xxRtrnLineStsSelFlg_RS, stsList[i]);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSerNumSrchTxt, pMsg.srchOptTxt_73);

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_74.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdSrcImptFromDt, pMsg.srchOptTxt_74.getValue());
        } else {
            bizMsg.xxOrdSrcImptFromDt.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_75.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdSrcImptToDt, pMsg.srchOptTxt_75.getValue());
        } else {
            bizMsg.xxOrdSrcImptToDt.clear();
        }
        // QC#15760 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSvcMachMstrSrchTxt, pMsg.srchOptTxt_76);
        // QC#15760 Add End
        // 2018/08/01 QC#26304 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAquNumSrchTxt, pMsg.srchOptTxt_77);
        // 2018/08/01 QC#26304 Add End
        // 2024/02/29 CSA QC#63608 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_A, pMsg.srchOptTxt_78);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_B, pMsg.srchOptTxt_79);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_C, pMsg.srchOptTxt_80);
        // 2024/02/29 CSA QC#63608 Add End
    }

    private static boolean callNszc0330(NWAL1570CMsg bizMsg, NSZC033001PMsg pMsg) {
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
                        bizMsg.srchOptPk_H1.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_H1.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NWAL1570CMsg
     * @param glblMsg NWAL1570SMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearch(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg, String userId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, userId);
            bizMsg.srchOptNm_H1.clear();
            bizMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Delete Search") });
        }
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NWAL1570CMsg
     * @param srchOptUsrId user id
     */
    public static void createSavedSearchOptionsPullDown(NWAL1570CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NWAL1570Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.srchOptPk_L1.clear();
            bizMsg.srchOptNm_L1.clear();
            return;
        }

        bizMsg.srchOptPk_L1.clear();
        bizMsg.srchOptNm_L1.clear();

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L1.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L1.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L1.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }

    }

    /**
     * getOrdTeamZoneByUser
     * @param bizMsg NWAL1570CMsg
     * @param srchOptUsrId user id
     */
    public static void getOrdTeamZoneByUser(NWAL1570CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NWAL1570Query.getInstance().getOrdTeamZoneByUser(bizMsg, srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L1.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.xxOrdTeamSrchTxt.setValue((String) resultMap.get("ORD_TEAM_MSTR_NM"));
            bizMsg.xxOrdZnSrchTxt.setValue((String) resultMap.get("ORD_ZN_DESC_TXT"));
        }

    }

    /**
     * getDsContrPk
     * @param bizMsg NWAL1570CMsg
     */
    public static void getDsContrPk(NWAL1570CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NWAL1570Query.getInstance().getDdsontrPk(bizMsg);
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.dsContrPk.setValue((BigDecimal) resultMap.get("DS_CONTR_PK"));
        }

    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NWAL1570CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NWAL1570CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H1)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L1.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L1.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_H1.getValue().equals(bizMsg.srchOptNm_L1.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_H1) //
                        && bizMsg.srchOptPk_H1.getValue().compareTo(bizMsg.srchOptPk_L1.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * callNszc0330forSaveSearch.
     * @param bizMsg NWAL1570CMsg
     * @param glblMsg NWAL1570SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearch(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H1) //
                || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_H1);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_H1);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        // Save item
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.xxCpoOrdNumSrchTxt_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.xxCpoOrdNumSrchTxt_H2);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.custIssPoNumSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.xxLeasePoNumSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.xxSoldToAcctNmSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.xxSoldToAcctCdSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.xxSoldToLocCdSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.xxShipToAcctNmSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.xxShipToAcctCdSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.xxShipToLocCdSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.xxBillToAcctNmSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.xxBillToAcctCdSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.xxBillToLocCdSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.xxCoaExtnSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.xxCoaBrSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.xxSlsRepTocSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.xxCpoSrcTpSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, bizMsg.xxDsBizLineSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, bizMsg.xxDsOrdCatgSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, bizMsg.xxDsOrdTpSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, bizMsg.xxDsOrdRsnSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, bizMsg.xxCsmpContrNumSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, bizMsg.xxPrcContrNumSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, bizMsg.xxOrdSrcRefNumSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, bizMsg.xxMdseSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, bizMsg.mdseForSlsSmrySrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, bizMsg.zerothProdCtrlSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_28, bizMsg.firstProdCtrlSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_29, bizMsg.scdProdCtrlSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_30, bizMsg.thirdProdCtrlSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_31, bizMsg.frthProdCtrlSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_32, bizMsg.xxCoaProdSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_33, bizMsg.xxCoaMdseTpSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_34, bizMsg.xxMdlSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_35, bizMsg.rtrnRsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_36, bizMsg.xxLineCatgSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_37, bizMsg.xxOrdLineSrcSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_38, bizMsg.xxRtlWhSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_39, bizMsg.xxRtlSwhSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_40, bizMsg.xxVndSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_41, bizMsg.xxCpoOrdNumSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_42, bizMsg.soNumSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_43, bizMsg.invNumSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_44, bizMsg.xxDsContrNumSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_45, bizMsg.xxSvcConfigMstrSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_46, bizMsg.xxOrdTeamSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_47, bizMsg.xxOrdZnSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_48, bizMsg.xxCratByUsrSrchTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_49, bizMsg.xxRsltModeCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_50, bizMsg.xxInclImptOrdFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_51, bizMsg.xxOnlySlsOrdFlg);
        if (ZYPCommonFunc.hasValue(bizMsg.ordFromDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_52, bizMsg.ordFromDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.ordToDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_53, bizMsg.ordToDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxBookFromDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_54, bizMsg.xxBookFromDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxBookToDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_55, bizMsg.xxBookToDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxActlShipFromDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_56, bizMsg.xxActlShipFromDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxActlShipToDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_57, bizMsg.xxActlShipToDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.invFromDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_58, bizMsg.invFromDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.invToDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_59, bizMsg.invToDt.getValue());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_60, bizMsg.dplyBy01ItemNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_61, bizMsg.dplyBy02ItemNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_62, bizMsg.dplyBy03ItemNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_63, bizMsg.grpByDnldCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_64, bizMsg.xxPgFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_65, bizMsg.xxInclPendImptOrdFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_66, bizMsg.xxAllOpenOrdFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_67, bizMsg.xxOrdHdrStsAllSelFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_68, bizMsg.xxLineStsAllSelFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_69, bizMsg.xxRtrnStsAllSelFlg);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <  bizMsg.H.length(); i++) {
            if (bizMsg.H.no(i).xxOrdHdrStsSelFlg_HS.getValue().equals(Y)) {
                if (hasValue(sb.toString())) {
                    append(sb, COMMA);
                }
                append(sb, bizMsg.H.no(i).xxOrdHdrStsSelFlg_HS.getValue());
            } else {
                if (hasValue(sb.toString())) {
                    append(sb, COMMA);
                }
                append(sb, N);
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_70, sb.toString());

        sb = new StringBuilder();
        for (int i = 0; i <  bizMsg.L.length(); i++) {
            if (bizMsg.L.no(i).xxLineStsSelFlg_LS.getValue().equals(Y)) {
                if (hasValue(sb.toString())) {
                    append(sb, COMMA);
                }
                append(sb, bizMsg.L.no(i).xxLineStsSelFlg_LS.getValue());
            } else {
                if (hasValue(sb.toString())) {
                    append(sb, COMMA);
                }
                append(sb, N);
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_71, sb.toString());

        sb = new StringBuilder();
        for (int i = 0; i <  bizMsg.R.length(); i++) {
            if (bizMsg.R.no(i).xxRtrnLineStsSelFlg_RS.getValue().equals(Y)) {
                if (hasValue(sb.toString())) {
                    append(sb, COMMA);
                }
                append(sb, bizMsg.R.no(i).xxRtrnLineStsSelFlg_RS.getValue());
            } else {
                if (hasValue(sb.toString())) {
                    append(sb, COMMA);
                }
                append(sb, N);
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_72, sb.toString());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_73, bizMsg.xxSerNumSrchTxt);

        if (ZYPCommonFunc.hasValue(bizMsg.xxOrdSrcImptFromDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_74, bizMsg.xxOrdSrcImptFromDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxOrdSrcImptToDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_75, bizMsg.xxOrdSrcImptToDt.getValue());
        }

        // QC#15760 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_76, bizMsg.xxSvcMachMstrSrchTxt);
        // QC#15760 Add End

        // 2018/08/01 QC#26304 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_77, bizMsg.xxAquNumSrchTxt);
        // 2018/08/01 QC#26304 Add End
        
        // 2024/02/29 CSA QC#63608 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_78, bizMsg.xxChkBox_A);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_79, bizMsg.xxChkBox_B);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_80, bizMsg.xxChkBox_C);
        // 2024/02/29 CSA QC#63608 Add End
        
        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, usrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_H1, pMsg.srchOptPk);
            bizMsg.srchOptNm_H1.clear();
            bizMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Save Search") });
        }
    }

    private static boolean isSameSaveSearchName(NWAL1570CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_H1)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_H1)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L1.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L1.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_H1.getValue().compareTo(cMsg.srchOptPk_L1.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_H1.getValue().equals(cMsg.srchOptNm_L1.no(i).getValue())) {
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
     * @param bizMsg NWAL1570CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NWAL1570CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_H1)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L1.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L1.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_H1.getValue().compareTo(bizMsg.srchOptPk_L1.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L1.no(i));
            }
        }
        return;
    }

    private static void append(StringBuilder sb, String... strs) {
        for (String str : strs) {
            sb.append(str);
        }
    }

}
