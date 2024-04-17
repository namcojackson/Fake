/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPBL0010.common;

import static business.blap.NPBL0010.constant.NPBL0010Constant.BIZ_APP_ID;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_SRC_TP_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_SRC_TP_DESC_TXT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_TP_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_TP_DESC_TXT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_SRCH_OPT_NM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_SRCH_OPT_PK;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_CMSG;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_EXPENSE_ORDER_TP;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_PRCH_REQ_REC_TP_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_ROW_NUM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_SRCH_OPT_APL_ID;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_SRCH_OPT_USR_ID;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_VND_TP_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_WH_TRANSFER_TP;
import static business.blap.NPBL0010.constant.NPBL0010Constant.NMAM0038I;
import static business.blap.NPBL0010.constant.NPBL0010Constant.NMAM8181W;
import static business.blap.NPBL0010.constant.NPBL0010Constant.SCREEN_CONTROL_VALUE_SEARCH_OP_HEADER;
import static business.blap.NPBL0010.constant.NPBL0010Constant.SCRN_ID;
import static business.blap.NPBL0010.constant.NPBL0010Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NPBL0010.NPBL0010CMsg;
import business.blap.NPBL0010.NPBL0010Query;
import business.blap.NPBL0010.NPBL0010SMsg;
import business.blap.NPBL0010.NPBL0010_ACMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NPBL0010 Inventory Request List
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS      Makoto Okigami        Create          N/A
 * 04/05/2016   CITS      K.Ogino               Update          N/A
 * 03/07/2017   CITS      T.Kikuhara            Update          QC#15983
 * 08/10/2017   CITS      H.Naoi                Update          Sol#097(QC#18398)
 * 11/15/2017   CITS      K.Ogino               Update          QC#22345
 * 11/26/2018   CITS      K.Ogino               Update          QC#28409
 * 05/28/2019   CITS      K.Ogino               Update          QC#50203
 * 03/09/2020   Fujitsu   R.Nakamura            Update          QC#55940
 *</pre>
 */
public class NPBL0010CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * Create Pulldown Search Option
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownSearchOption(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.srchOptPk_PD.clear();
        cMsg.srchOptNm_PD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SRCH_OPT_APL_ID, BIZ_APP_ID);
        ssmParam.put(DB_PARAM_SRCH_OPT_USR_ID, cMsg.srchOptUsrId_U1.getValue());

        // Execute
        S21SsmEZDResult result = NPBL0010Query.getInstance().getSearchOptionPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, Object>> searchOptionList = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map<String, Object> recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_PD.no(i), (BigDecimal) recode.get(DB_COLUMN_SRCH_OPT_PK));
                ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_PD.no(i), (String) recode.get(DB_COLUMN_SRCH_OPT_NM));

                if (i >= cMsg.srchOptPk_PD.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Requisition Type
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownRequisitionType(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.prchReqTpCd_PD.clear();
        cMsg.prchReqTpDescTxt_PD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.INVENTORY_REQUEST);

        // Execute
        S21SsmEZDResult result = NPBL0010Query.getInstance().getRequisitionTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_PD.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpDescTxt_PD.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_TP_DESC_TXT));

                if (i >= cMsg.prchReqTpCd_PD.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Header Status
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownHeaderStatus(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.prchReqStsCd_PD.clear();
        cMsg.prchReqStsDescTxt_PD.clear();

        ZYPCodeDataUtil.createPulldownList(PRCH_REQ_STS.class, cMsg.prchReqStsCd_PD, cMsg.prchReqStsDescTxt_PD);

    }

    /**
     * Create Pulldown Approval Status
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownApprovalStatus(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.prchReqApvlStsCd_PD.clear();
        cMsg.prchReqApvlStsDescTxt_PD.clear();

        ZYPCodeDataUtil.createPulldownList(PRCH_REQ_APVL_STS.class, cMsg.prchReqApvlStsCd_PD, cMsg.prchReqApvlStsDescTxt_PD);

    }

    /**
     * Create Pulldown Document Source Type
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownDocumentSourceType(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.prchReqSrcTpCd_PD.clear();
        cMsg.prchReqSrcTpDescTxt_PD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.INVENTORY_REQUEST);

        // Execute
        S21SsmEZDResult result = NPBL0010Query.getInstance().getDocumentSourceTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd_PD.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_SRC_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpDescTxt_PD.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_SRC_TP_DESC_TXT));

                if (i >= cMsg.prchReqSrcTpCd_PD.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Requested Service Level
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownRequestedShipMethod(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.shpgSvcLvlCd_PD.clear();
        cMsg.shpgSvcLvlDescTxt_PD.clear();

        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, cMsg.shpgSvcLvlCd_PD, cMsg.shpgSvcLvlDescTxt_PD);

    }

    /**
     * Search
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     * @param glblCmpyCd String
     */
    public static void search(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.INVENTORY_REQUEST);
        ssmParam.put(DB_PARAM_ROW_NUM, sMsg.A.length() + 1);
        // QC#22345 Start
        ssmParam.put(DB_PARAM_EXPENSE_ORDER_TP, PRCH_REQ_TP.EXPENSE_ORDER);
        ssmParam.put(DB_PARAM_WH_TRANSFER_TP, PRCH_REQ_TP.WH_TRANSFER);
        // QC#22345 End
        // QC#28409
        ssmParam.put(DB_PARAM_VND_TP_CD, VND_TP.OUTBOUND_CARRIER);

        S21SsmEZDResult result = null;
        if (cMsg.xxRadioBtn.getValueInt() == SCREEN_CONTROL_VALUE_SEARCH_OP_HEADER) {
            result = NPBL0010Query.getInstance().searchHeader(ssmParam, sMsg);
        } else {
            result = NPBL0010Query.getInstance().searchDetail(ssmParam, sMsg);
        }

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }

        // Max Recode Over
        int queryResCnt = result.getQueryResultCount();
        if (queryResCnt > sMsg.A.length()) {
            // QC#15983 update start
            cMsg.setMessageInfo(NMAM8181W, new String[]{Integer.toString(sMsg.A.length()), Integer.toString(sMsg.A.length())});
            // QC#15983 update end
            queryResCnt = sMsg.A.length();
        }

        // Copy 1 page Data(SMsg -> CMsg)
        int i = 0;
        for (; i < sMsg.A.getValidCount(); i++) {
            if (i == cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            // QC#50203
            setCost(glblCmpyCd, cMsg.A.no(i));
        }
        cMsg.A.setValidCount(i);

        // Setting Next Page
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(queryResCnt);

    }

    /**
     * createSerialSelectQueryParts
     * @param sMsg NPBL0010SMsg
     */
    private static String createSerialQueryParts(NPBL0010SMsg sMsg) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!sMsg.A.no(i).prchReqLineNum_A1.isClear()) {
                if (sb.length() != 0) {
                    sb.append(" OR ");
                } else {
                    sb.append("(");
                }
                sb.append("(PRS.PRCH_REQ_NUM = '").append(sMsg.A.no(i).prchReqNum_A2.getValue());
                sb.append("' AND PRS.PRCH_REQ_LINE_NUM = '").append(sMsg.A.no(i).prchReqLineNum_A1.getValue());
                sb.append("' AND PRS.PRCH_REQ_LINE_SUB_NUM = ").append(sMsg.A.no(i).prchReqLineSubNum_A1.getValueInt());
                sb.append(")");
            }
        }
        if (sb.length() != 0) {
            sb.append(")");
        }
        return sb.toString();
    }

    /**
     * check duplicate search name
     * @param cMsg NPBL0010CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NPBL0010CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {
                return false;
            }
            if (cMsg.srchOptNm_TX.getValue().equals(cMsg.srchOptNm_PD.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL) //
                        && cMsg.srchOptPk_SL.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Call API NSZC033001 (Save SearchOption)
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearchOption(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX) //
                || isSameSaveSearchName(cMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SL);
        }
        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_TX);
        } else {
            setSelectSaveSearchName(pMsg, cMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.prchReqTpCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.prchReqStsCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.prchReqApvlStsCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.prchReqCratDt_RF.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.prchReqCratDt_RT.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.rqstRcvDt_NF.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.rqstRcvDt_NT.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.prchReqSrcTpCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.trxRefNum);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.mrpPlnNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.fullPsnNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.shpgSvcLvlCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.carrNm);
        if (ZYPCommonFunc.hasValue(cMsg.xxRadioBtn)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.xxRadioBtn.getValue().toPlainString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.rtlWhNm_SW);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.srcRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, cMsg.rtlSwhNm_SS);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, cMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, cMsg.rtlWhNm_DW);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, cMsg.destRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, cMsg.rtlSwhNm_DS);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, cMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, cMsg.dplyVndNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, cMsg.shipToCustCd_EO);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, cMsg.shipToLocNm_EO);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        // Add Start 2020/03/09 QC#55940
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_28, cMsg.mdseCd);
        // Add End 2020/03/09 QC#55940
        
        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            createPullDownSearchOption(cMsg, sMsg, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_SL, pMsg.srchOptPk);
            // Message ; The process [@] has been successfully
            // completed.
            cMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Save Search") });
        }
    }

    /**
     * Call API NSZC033001 (Delete SearchOption)
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearchOption(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            createPullDownSearchOption(cMsg, sMsg, glblCmpyCd);
            cMsg.srchOptNm_TX.clear();
            cMsg.srchOptPk_SL.clear();
            cMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Delete Search") });
        }
    }

    /**
     * Call API NSZC033001 (Search SearchOption)
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchSearchOption(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (!executeNszc0330(cMsg, pMsg)) {
            return; // Error
        }

        // Set Result
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_TX, pMsg.srchOptNm);

        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_SL, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsCd_SL, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsCd_SL, pMsg.srchOptTxt_04);
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_05)) {
            cMsg.prchReqCratDt_RF.setValue(pMsg.srchOptTxt_05.getValue());
        } else {
            cMsg.prchReqCratDt_RF.clear();
        }
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_06)) {
            cMsg.prchReqCratDt_RT.setValue(pMsg.srchOptTxt_06.getValue());
        } else {
            cMsg.prchReqCratDt_RT.clear();
        }
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_07)) {
            cMsg.rqstRcvDt_NF.setValue(pMsg.srchOptTxt_07.getValue());
        } else {
            cMsg.rqstRcvDt_NF.clear();
        }
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_08)) {
            cMsg.rqstRcvDt_NT.setValue(pMsg.srchOptTxt_08.getValue());
        } else {
            cMsg.rqstRcvDt_NT.clear();
        }
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd_SL, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(cMsg.trxRefNum, pMsg.srchOptTxt_10);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(cMsg.carrNm, pMsg.srchOptTxt_14);
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_15)) {
            cMsg.xxRadioBtn.setValue(new BigDecimal(pMsg.srchOptTxt_15.getValue()));
        } else {
            cMsg.xxRadioBtn.clear();
        }
        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlWhCd, pMsg.srchOptTxt_16);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_SW, pMsg.srchOptTxt_17);
        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlSwhCd, pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_SS, pMsg.srchOptTxt_19);
        ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd, pMsg.srchOptTxt_20);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_DW, pMsg.srchOptTxt_21);
        ZYPEZDItemValueSetter.setValue(cMsg.destRtlSwhCd, pMsg.srchOptTxt_22);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_DS, pMsg.srchOptTxt_23);
        ZYPEZDItemValueSetter.setValue(cMsg.vndCd, pMsg.srchOptTxt_24);
        ZYPEZDItemValueSetter.setValue(cMsg.dplyVndNm, pMsg.srchOptTxt_25);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_EO, pMsg.srchOptTxt_26);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm_EO, pMsg.srchOptTxt_27);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        // Add Start 2020/03/09 QC#55940
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, pMsg.srchOptTxt_28);
        // Add End 2020/03/09 QC#55940
    }

    /**
     * isSameSaveSearchName
     * @param cMsg NPBL0010CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NPBL0010CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_SL.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_TX.getValue().equals(cMsg.srchOptNm_PD.no(i).getValue())) {
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
     * @param cMsg NPBL0010CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NPBL0010CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {
                return;
            }
            if (cMsg.srchOptPk_SL.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_PD.no(i));
            }
        }
        return;
    }

    /**
     * Execute API NSZC033001
     * @param bizMsg NPBL0010CMsg
     * @param pMsg NSZC033001PMsg
     * @return
     */
    private static boolean executeNszc0330(NPBL0010CMsg cMsg, NSZC033001PMsg pMsg) {
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
                        cMsg.srchOptPk_SL.setErrorInfo(1, msgId);
                        cMsg.srchOptNm_TX.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Add QC#50203 getInvtyItemCos
     * @pamram glblCmpyCd String
     * @pamram String String
     * @pamram rtlSwhCd String
     * @pamram mdseCd String
     * @pamram qty BigDecimal
     */
    public static NLXC001001GetInventoryItemCostBean getInvtyItemCost(String glblCmpyCd, String rtlWhCd, String rtlSwhCd, String mdseCd, BigDecimal qty) {
        NLXC001001GetInventoryItemCostBean nlxc001001Bean = new NLXC001001GetInventoryItemCostBean();
        nlxc001001Bean.setGlblCmpyCd(glblCmpyCd);
        nlxc001001Bean.setInvtyLocCd(ZYPCommonFunc.concatString(rtlWhCd, "", rtlSwhCd));
        nlxc001001Bean.setMdseCd(mdseCd);
        if (ZYPCommonFunc.hasValue(qty)) {
            nlxc001001Bean.setQty(qty);
        } else {
            nlxc001001Bean.setQty(BigDecimal.ONE);
        }

        NLXC001001GetInventoryItemCost.getInventoryItemCost(nlxc001001Bean);
        return nlxc001001Bean;
    }

    /**
     * Add QC#50203 setCost
     * @param glblCmpyCd String
     * @param acMsg NPBL0010_ACMsg
     */
    public static void setCost(String glblCmpyCd, NPBL0010_ACMsg acMsg) {
        if (!PRCH_REQ_TP.VENDOR_RETURN.equals(acMsg.prchReqTpCd_A1.getValue())) {
            if (ZYPCommonFunc.hasValue(acMsg.mdseCd_A1)) {
                NLXC001001GetInventoryItemCostBean nlxc001001Bean = getInvtyItemCost(glblCmpyCd, acMsg.srcRtlWhCd_A1.getValue(), acMsg.srcRtlSwhCd_A1.getValue(), acMsg.mdseCd_A1.getValue(), acMsg.prchReqDispQty_A1.getValue());
                if (nlxc001001Bean.getErrList().isEmpty()) {
                    ZYPEZDItemValueSetter.setValue(acMsg.entDealNetUnitPrcAmt_A1, nlxc001001Bean.getUnitPrcAmt());
                    if (ZYPCommonFunc.hasValue(acMsg.prchReqDispQty_A1) && ZYPCommonFunc.hasValue(acMsg.entDealNetUnitPrcAmt_A1)) {
                        ZYPEZDItemValueSetter.setValue(acMsg.entPoDtlDealNetAmt_A1, acMsg.entDealNetUnitPrcAmt_A1.getValue().multiply(ZYPCommonFunc.getBigDecimal(acMsg.prchReqDispQty_A1, "0")));
                    }
                }
            }

        }
    }
}
