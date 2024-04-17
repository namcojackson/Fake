/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0020.common;

import static business.blap.NSAL0020.constant.NSAL0020Constant.APL_ID;
import static business.blap.NSAL0020.constant.NSAL0020Constant.SCRN_ID;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSAL0020.NSAL0020CMsg;
import business.blap.NSAL0020.NSAL0020Query;
import business.blap.NSAL0020.NSAL0020SMsg;
import business.blap.NSAL0020.constant.NSAL0020Constant.MSG_ID;
import business.blap.NSAL0020.constant.NSAL0020Constant.MSG_KIND;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   Hitachi         T.Yonekura      Create          N/A
 * 2013/08/27   Hitachi         Y.Igarashi      Update          QC1851
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Update          NA(No Mark up comment)
 * 2015/11/25   Hitachi         Y.Tsuchimoto    Update          QC#1027
 * 2016/05/13   Hitachi         T.Tomita        Update          QC#4578
 * 2016/09/15   Hitachi         N.Arai          Update          QC#11616
 * 2016/12/13   Hitachi         K.Ochiai        Update          QC#16563
 *</pre>
 */
public class NSAL0020CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * isExistSaveSearchName
     * @param cMsg NSAL0020CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NSAL0020CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) //
                        && cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * callNszc0330forSaveSearch
     * @param cMsg NSAL0020CMsg
     * @param sMsg NSAL0200SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forSaveSearch(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S) //
                || isSameSaveSearchName(cMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, cMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, APL_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.serNum.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.svcSlnSq)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.svcSlnSq.getValue().toString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.svcSlnNm.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.svcConfigMstrPk.getValue().toString());
        }
        if (ZYPCommonFunc.hasValue(cMsg.svcMachMstrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.svcMachMstrPk.getValue().toString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.xxChkBox.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.istlDt_FR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.istlDt_TO.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.svcMachMstrStsCd_PS.getValue());
        // START 2015/11/24 T.Tomita [QC#1027, MOD]
        if (ZYPCommonFunc.hasValue(cMsg.svcLicCnt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.svcLicCnt.getValue().toString());
        }
        // END 2015/11/24 T.Tomita [QC#1027, MOD]
        // START 2016/05/13 T.Tomita [QC#4578, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.xxComnScrColValTxt_O.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.ownrAcctNum.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.ownrLocNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.xxComnScrColValTxt_B.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.billToAcctNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.indBillToLocNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, cMsg.xxComnScrColValTxt_C.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, cMsg.curLocAcctNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, cMsg.indCurLocNum.getValue());
        // END 2016/05/13 T.Tomita [QC#4578, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, cMsg.t_MdlNm.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, cMsg.mdseCd.getValue());
        // START 2016/09/15 N.Arai [QC#11616, MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, cMsg.mdseNm.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, cMsg.mdseDescShortTxt.getValue());
        // END 2016/09/15 N.Arai [QC#11616, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, cMsg.coaMdseTpCd_PS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, cMsg.cpoOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, cMsg.custMachCtrlNum.getValue());
        // START 2016/12/13 K.Ochiai [QC#16563, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_28, cMsg.custIssPoNum.getValue());
        // END 2016/12/13 K.Ochiai [QC#16563, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_29, cMsg.dsContrNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_30, cMsg.ctacPsnLastNm.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_31, cMsg.svcCtacTpCd_PS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_32, cMsg.tocNm.getValue());

        if (callNszc0330(cMsg, pMsg)) {
            createSavedSearchOptionsPullDown(cMsg, srchOptUsrId);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_S, pMsg.srchOptPk);
            cMsg.srchOptNm_S.clear();
            cMsg.setMessageInfo(MSG_ID.ZZZM9003I.toString() //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Save Search") });
        }
    }

    private static boolean isSameSaveSearchName(NSAL0020CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {
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
     * @param cMsg NSAL0020CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NSAL0020CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                return;
            }
            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_L.no(i));
            }
        }
        return;
    }

    private static boolean callNszc0330(NSAL0020CMsg cMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);
                    if (msgId.endsWith(MSG_KIND.ERROR.getMsgKind())) {
                        cMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        cMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param cMsg NSAL0020CMsg
     * @param srchOptUsrId String
     */
    public static void createSavedSearchOptionsPullDown(NSAL0020CMsg cMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NSAL0020Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            cMsg.srchOptPk_L.clear();
            cMsg.srchOptNm_L.clear();
            return;
        }

        cMsg.srchOptPk_L.clear();
        cMsg.srchOptNm_L.clear();

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < cMsg.srchOptPk_L.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            cMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            cMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }
    }

    /**
     * callNszc0330forDeleteSearch
     * @param cMsg NSAL0020CMsg
     * @param sMsg NSAL0020SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forDeleteSearch(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, APL_ID);

        if (callNszc0330(cMsg, pMsg)) {
            createSavedSearchOptionsPullDown(cMsg, srchOptUsrId);
            cMsg.srchOptNm_S.clear();
            cMsg.setMessageInfo(MSG_ID.ZZZM9003I.toString() //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Delete Search") });
        }
    }


    /**
     * callNszc0330forSearchOption
     * @param cMsg NSAL0020CMsg
     * @param sMsg NSAL0020SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forSearchOption(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, APL_ID);

        if (!callNszc0330(cMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, pMsg.srchOptTxt_01.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, pMsg.srchOptTxt_02.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.serNum, pMsg.srchOptTxt_03.getValue());
        // START 2015/11/24 T.Tomita [QC#1027, MOD]
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_04)) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcSlnSq, new BigDecimal(pMsg.srchOptTxt_04.getValue()));
        } else {
            cMsg.svcSlnSq.clear();
        }
        ZYPEZDItemValueSetter.setValue(cMsg.svcSlnNm, pMsg.srchOptTxt_05.getValue());
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_06)) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcConfigMstrPk, new BigDecimal(pMsg.srchOptTxt_06.getValue()));
        } else {
            cMsg.svcConfigMstrPk.clear();
        }
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_07)) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrPk, new BigDecimal(pMsg.srchOptTxt_07.getValue()));
        } else {
            cMsg.svcMachMstrPk.clear();
        }
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox, pMsg.srchOptTxt_08.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.istlDt_FR, pMsg.srchOptTxt_09.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.istlDt_TO, pMsg.srchOptTxt_10.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrStsCd_PS, pMsg.srchOptTxt_11.getValue());
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_12)) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcLicCnt, new BigDecimal(pMsg.srchOptTxt_12.getValue()));
        } else {
            cMsg.svcLicCnt.clear();
        }
        // END 2015/11/24 T.Tomita [QC#1027, MOD]
        // START 2016/05/13 T.Tomita [QC#4578, MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxComnScrColValTxt_O, pMsg.srchOptTxt_13.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.ownrAcctNum, pMsg.srchOptTxt_14.getValue());
//        ZYPEZDItemValueSetter.setValue(cMsg.ownrLocNum, pMsg.srchOptTxt_15.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxComnScrColValTxt_B, pMsg.srchOptTxt_16.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.billToAcctNum, pMsg.srchOptTxt_17.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.indBillToLocNum, pMsg.srchOptTxt_18.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxComnScrColValTxt_C, pMsg.srchOptTxt_19.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.curLocAcctNum, pMsg.srchOptTxt_20.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.indCurLocNum, pMsg.srchOptTxt_21.getValue());
        // END 2016/05/13 T.Tomita [QC#4578, MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm, pMsg.srchOptTxt_22.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, pMsg.srchOptTxt_23.getValue());
        // START 2016/09/15 N.Arai [QC#11616, MOD]
        // ZYPEZDItemValueSetter.setValue(cMsg.mdseNm, pMsg.srchOptTxt_24.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, pMsg.srchOptTxt_24.getValue());
        // END 2016/09/15 N.Arai [QC#11616, MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.coaMdseTpCd_PS, pMsg.srchOptTxt_25.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.cpoOrdNum, pMsg.srchOptTxt_26.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.custMachCtrlNum, pMsg.srchOptTxt_27.getValue());
        // START 2016/12/13 K.Ochiai [QC#16563, MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.custIssPoNum, pMsg.srchOptTxt_28.getValue());
        // END 2016/12/13 K.Ochiai [QC#16563, MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrNum, pMsg.srchOptTxt_29.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnLastNm, pMsg.srchOptTxt_30.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.svcCtacTpCd_PS, pMsg.srchOptTxt_31.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.tocNm, pMsg.srchOptTxt_32.getValue());

    }

    /**
     * Pagenation
     * @param cMsg NSAL0020CMsg
     * @param sMsg NSAL0020SMsg
     * @param pageFrom int
     */
    public static void pagenation(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg, int pageFrom) {

        EZDCMsgArray cAry = null;
        EZDSMsgArray sAry = null;

        cAry = cMsg.A;
        sAry = sMsg.A;

        int i = pageFrom;
        int j = 0;
        for (; i < sAry.getValidCount() && j < cAry.length(); i++, j++) {
            EZDMsg.copy(sAry.get(i), null, cAry.get(j), null);
        }
        cAry.setValidCount(j);

        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cAry.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sAry.getValidCount());
    }

    // Old NSAB0020 Start----------------------------------------
//    /**
//     * Pagenation
//     * @param cMsg NSAL0020CMsg
//     * @param sMsg NSAL0020SMsg
//     * @param pageFrom int
//     */
//    public static void pagenation(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg, int pageFrom) {
//
//        EZDCMsgArray cAry = null;
//        EZDSMsgArray sAry = null;
//
//        cAry = cMsg.A;
//        sAry = sMsg.A;
//
//        int i = pageFrom;
//        int j = 0;
//        for (; i < sAry.getValidCount() && j < cAry.length(); i++, j++) {
//            EZDMsg.copy(sAry.get(i), null, cAry.get(j), null);
//        }
//        cAry.setValidCount(j);
//
//        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
//        cMsg.xxPageShowToNum.setValue(pageFrom + cAry.getValidCount());
//        cMsg.xxPageShowOfNum.setValue(sAry.getValidCount());
//    }

//    /**
//     * Contract Common Parts Stub A
//     * @param glblCmpyCd String
//     * @param sMsg NSAL0020SMsg
//     * @param contrNum String
//     * @param index int
//     */
//    public static void setContrInfo(String glblCmpyCd, NSAL0020SMsg sMsg, String contrNum, int index) {
//
//        NSXC001001Contr dsxc001001Contr = new NSXC001001Contr();
//        ContrInfoBean contrInfoBean = dsxc001001Contr.getContrInfo(glblCmpyCd, sMsg.A.no(index).svcConfigMstrPk_A0.getValue(), sMsg.A.no(index).svcMachMstrPk_A0.getValue(), null);
//        if (contrInfoBean != null) {
//            // QC1851 Add Start
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrPk_A0, contrInfoBean.getContrPk());
//            // QC1851 Add End
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrNum_A0, contrInfoBean.getContrNum());
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrSqNum_A0, contrInfoBean.getContrSeq());
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxViewNm_A0, contrInfoBean.getCv());
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).contrVrsnEffFromDt_A0, contrInfoBean.getContrEffDt());
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).contrVrsnEffThruDt_A0, contrInfoBean.getContrExprDt());
//        }
//    }

//    /**
//     * Contract Common Parts Stub B
//     * @param cMsg NSAL0020CMsg
//     * @return list    ArrayList<BigDecimal>
//     */
//    public static ArrayList<BigDecimal> searchContrInfo(NSAL0020CMsg cMsg) {
//
//        NSXC001001PMsg prmPmsg = new NSXC001001PMsg();
//        prmPmsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
//        prmPmsg.dsContrNum.setValue(cMsg.dsContrNum.getValue());
//
//        NSXC001001Contr.searchMachMstrList(prmPmsg);
//
//        ArrayList<BigDecimal> list = new ArrayList<BigDecimal>();
//
//        if (prmPmsg.xxContrList.getValidCount() != 0) {
//            for (int i = 0; i < prmPmsg.xxContrList.getValidCount(); i++) {
//                list.add(prmPmsg.xxContrList.no(i).svcMachMstrPk.getValue());
//            }
//        }
//
//        return list;
//    }
//    /**
//     * Contract Common Parts Stub B
//     * @param cMsg NSAL0020CMsg
//     * @return list    ArrayList<BigDecimal>
//     */
//    public static void setLayerNm(NSAL0020CMsg cMsg) {

//        if (ZYPCommonFunc.hasValue(cMsg.firstOrgCd)) {
//            ZYPEZDItemValueSetter.setValue(cMsg.firstOrgNm, NSAL0020Query.getInstance().getOrgNm(BigDecimal.ONE, cMsg.firstOrgCd.getValue()));
//        } else {
//            cMsg.firstOrgNm.clear();
//        }
//        if (ZYPCommonFunc.hasValue(cMsg.scdOrgCd)) {
//            ZYPEZDItemValueSetter.setValue(cMsg.scdOrgNm, NSAL0020Query.getInstance().getOrgNm(new BigDecimal("2"), cMsg.scdOrgCd.getValue()));
//        } else {
//            cMsg.scdOrgNm.clear();
//        }
//        if (ZYPCommonFunc.hasValue(cMsg.thirdOrgCd)) {
//            ZYPEZDItemValueSetter.setValue(cMsg.thirdOrgNm, NSAL0020Query.getInstance().getOrgNm(new BigDecimal("3"), cMsg.thirdOrgCd.getValue()));
//        } else {
//            cMsg.thirdOrgNm.clear();
//        }
//        if (ZYPCommonFunc.hasValue(cMsg.frthOrgCd)) {
//            ZYPEZDItemValueSetter.setValue(cMsg.frthOrgNm, NSAL0020Query.getInstance().getOrgNm(new BigDecimal("4"), cMsg.frthOrgCd.getValue()));
//        } else {
//            cMsg.frthOrgNm.clear();
//        }
//        if (ZYPCommonFunc.hasValue(cMsg.fifthOrgCd)) {
//            ZYPEZDItemValueSetter.setValue(cMsg.fifthOrgNm, NSAL0020Query.getInstance().getOrgNm(new BigDecimal("5"), cMsg.fifthOrgCd.getValue()));
//        } else {
//            cMsg.fifthOrgNm.clear();
//        }
//    }

}
