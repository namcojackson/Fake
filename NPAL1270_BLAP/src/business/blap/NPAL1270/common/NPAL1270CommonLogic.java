/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1270.common;

import static business.blap.NPAL1270.constant.NPAL1270Constant.BIZ_APP_ID;
import static business.blap.NPAL1270.constant.NPAL1270Constant.CSV_EXCLUSION_ITEM;
import static business.blap.NPAL1270.constant.NPAL1270Constant.CSV_HEADER;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_COLUMN_PRCH_GRP_CD;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_COLUMN_PRCH_GRP_DESC_TXT;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_COLUMN_PRCH_REQ_SRC_TP_CD;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_COLUMN_PRCH_REQ_SRC_TP_DESC_TXT;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_COLUMN_PRCH_REQ_TP_CD;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_COLUMN_PRCH_REQ_TP_DESC_TXT;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_COLUMN_SRCH_OPT_NM;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_COLUMN_SRCH_OPT_PK;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_PARAM_PO_REQUISITION;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_PARAM_PRCH_REQ_LINE_TP_CD_EXPENSE;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_PARAM_PRCH_REQ_LINE_TP_CD_EXPENSE_W_RECEIPT;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_PARAM_PRCH_REQ_REC_TP_CD;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_PARAM_ROW_NUM;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_PARAM_SRCH_OPT_APL_ID;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_PARAM_SRCH_OPT_USR_ID;
import static business.blap.NPAL1270.constant.NPAL1270Constant.MAX_DOWNLOAD_CNT;
import static business.blap.NPAL1270.constant.NPAL1270Constant.NMAM0038I;
import static business.blap.NPAL1270.constant.NPAL1270Constant.NMAM8181W;
import static business.blap.NPAL1270.constant.NPAL1270Constant.NZZM0007E;
import static business.blap.NPAL1270.constant.NPAL1270Constant.SCRN_ID;
import static business.blap.NPAL1270.constant.NPAL1270Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCDateItem;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NPAL1270.NPAL1270CMsg;
import business.blap.NPAL1270.NPAL1270Query;
import business.blap.NPAL1270.NPAL1270SMsg;
import business.db.COA_PRODTMsg;
import business.db.COA_PROJTMsg;
import business.file.NPAL1270F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NPAL1270 PO Requisition List
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS            R Shimamoto     Create          N/A
 * 03/01/2016   CITS            K.Ogino         Update          QC#4736
 * 03/16/2016   CITS            K.Ogino         Update          QC#5634
 * 05/26/2016   CITS            K.Ogino         Update          QC#8931
 * 08/10/2017   CITS            H.Naoi          Update          Sol#097(QC#18398)
 * 01/29/2018   CITS            T.Gotoda        Update          QC#22521
 * 11/13/2018   CITS            T.Hakodate      Update          QC#28939
 * 09/23/2019   CITS            R.Shimamoto     Update          QC#53271
 * 02/01/2023   Hitachi         S.Dong          Update          QC#60966
 *</pre>
 */
public class NPAL1270CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * Method Name: complementFromToDate <dd>When one of from or to is
     * empty, fill empty value with the other. <dd>When from is future
     * of to, swap from and to.
     * @param from EZDCDateItem
     * @param to EZDCDateItem
     */
    public static void complementFromToDate(EZDCDateItem from, EZDCDateItem to) {
        if (ZYPCommonFunc.hasValue(from) && !ZYPCommonFunc.hasValue(to)) {
            to.setValue(from.getValue());
            return;
        }

        if (ZYPCommonFunc.hasValue(to) && !ZYPCommonFunc.hasValue(from)) {
            from.setValue(to.getValue());
            return;
        }

        if (ZYPCommonFunc.hasValue(from) && ZYPCommonFunc.hasValue(to)) {
            // date can be compared as string
            String fromValue = from.getValue();

            if (fromValue.compareTo(to.getValue()) > 0) {
                from.setValue(to.getValue());
                to.setValue(fromValue);
            }
        }
    }

    /**
     * Create Pulldown Search Option
     * @param cMsg NPAL1270CMsg
     * @param glblCmpyCd String
     * @param userId String
     */
    public static void createPullDownSearchOption(NPAL1270CMsg cMsg, String glblCmpyCd, String userId) {
        EZDDebugOutput.println(1, "NPAL1270 ASL Search Create Pulldown list SearchOption.", null);

        // Clear Pulldown Data
        cMsg.srchOptPk_PD.clear();
        cMsg.srchOptNm_PD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SRCH_OPT_APL_ID, BIZ_APP_ID);
        ssmParam.put(DB_PARAM_SRCH_OPT_USR_ID, cMsg.srchOptUsrId_U1.getValue());
        ssmParam.put(DB_PARAM_SRCH_OPT_USR_ID, userId);

        // Execute
        S21SsmEZDResult result = NPAL1270Query.getInstance().getSearchOptionPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

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
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownRequisitionType(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "NPAL1270 PO Requisition List Create Pulldown list Requisition Type.", null);

        // Clear Pulldown Data
        cMsg.prchReqTpCd_PD.clear();
        cMsg.prchReqTpDescTxt_PD.clear();

        // Set Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        // PO_REQUISITION
        ssmParam.put(DB_PARAM_PO_REQUISITION, PRCH_REQ_REC_TP.PO_REQUISITION);

        // Execute
        S21SsmEZDResult result = NPAL1270Query.getInstance().getRequisitionTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_PD.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpDescTxt_PD.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_TP_DESC_TXT));

                if (i >= cMsg.prchReqTpCd_PD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Header Status
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownHeaderStatus(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "NPAL1270 PO Requisition List Create Pulldown list Header Status.", null);

        // Clear Pulldown Data
        cMsg.prchReqStsCd_PD.clear();
        cMsg.prchReqStsDescTxt_PD.clear();

        ZYPCodeDataUtil.createPulldownList(PRCH_REQ_STS.class, cMsg.prchReqStsCd_PD, cMsg.prchReqStsDescTxt_PD);

    }

    /**
     * Create Pulldown Approval Status
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownApprovalStatus(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "NPAL1270 PO Requisition List Create Pulldown list Approval Status.", null);

        // Clear Pulldown Data
        cMsg.prchReqApvlStsCd_PD.clear();
        cMsg.prchReqApvlStsDescTxt_PD.clear();

        ZYPCodeDataUtil.createPulldownList(PRCH_REQ_APVL_STS.class, cMsg.prchReqApvlStsCd_PD, cMsg.prchReqApvlStsDescTxt_PD);

    }

    /**
     * Create Pulldown Line Status
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownLinelStatus(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "NPAL1270 PO Requisition List Create Pulldown list Line Status.", null);

        // Clear Pulldown Data
        cMsg.prchReqLineStsCd_PD.clear();
        cMsg.prchReqLineStsDescTxt_PD.clear();

        ZYPCodeDataUtil.createPulldownList(PRCH_REQ_LINE_STS.class, cMsg.prchReqLineStsCd_PD, cMsg.prchReqLineStsDescTxt_PD);

    }

    /**
     * Create Pulldown Document Source Type
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownDocumentSourceType(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "NPAL1270 PO Requisition List Create Pulldown list Document Source Type.", null);

        // Clear Pulldown Data
        cMsg.prchReqSrcTpCd_PD.clear();
        cMsg.prchReqSrcTpDescTxt_PD.clear();

        // Set Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.PO_REQUISITION);

        // Execute
        S21SsmEZDResult result = NPAL1270Query.getInstance().getDocumentSourceTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd_PD.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_SRC_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpDescTxt_PD.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_SRC_TP_DESC_TXT));

                if (i >= cMsg.prchReqSrcTpCd_PD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Business Unit
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownBusinessUnit(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "NPAL1270 PO Requisition List Create Pulldown list Business Unit.", null);

        // Clear Pulldown Data
        cMsg.prchGrpCd_PD.clear();
        cMsg.prchGrpDescTxt_PD.clear();

        // Set Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        // Execute
        S21SsmEZDResult result = NPAL1270Query.getInstance().getBusinessUnitPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchGrpCd_PD.no(i), (String) recode.get(DB_COLUMN_PRCH_GRP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchGrpDescTxt_PD.no(i), (String) recode.get(DB_COLUMN_PRCH_GRP_DESC_TXT));

                if (i >= cMsg.prchGrpCd_PD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Req Service Level
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownRequestedShipMethod(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "NPAL1270 PO Requisition List Create Pulldown list Req Service Level.", null);

        // Clear Pulldown Data
        cMsg.shpgSvcLvlCd_PD.clear();
        cMsg.shpgSvcLvlDescTxt_PD.clear();

        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, cMsg.shpgSvcLvlCd_PD, cMsg.shpgSvcLvlDescTxt_PD);

    }

    /**
     * Pre Search For Set Name
     * @param cMsg NPAL1270CMsg
     * @param glblCmpyCd String
     */
    public static void preSearchForSetName(NPAL1270CMsg cMsg, String glblCmpyCd) {
        // MERCHANDISE TYPE
        if (ZYPCommonFunc.hasValue(cMsg.coaMdseTpCd)) {
            COA_PROJTMsg coaProjMsg = new COA_PROJTMsg();
            ZYPEZDItemValueSetter.setValue(coaProjMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(coaProjMsg.coaProjCd, cMsg.coaMdseTpCd);
            coaProjMsg = (COA_PROJTMsg) EZDTBLAccessor.findByKey(coaProjMsg);
            if (coaProjMsg != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.coaProjDescTxt, coaProjMsg.coaProjDescTxt);
            } else {
                cMsg.coaProjDescTxt.clear();
            }
        } else {
            cMsg.coaProjDescTxt.clear();
        }
        // PRODUCT NAME
        if (ZYPCommonFunc.hasValue(cMsg.coaProdCd)) {
            COA_PRODTMsg coaProdMsg = new COA_PRODTMsg();
            ZYPEZDItemValueSetter.setValue(coaProdMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(coaProdMsg.coaProdCd, cMsg.coaProdCd);
            coaProdMsg = (COA_PRODTMsg) EZDTBLAccessor.findByKey(coaProdMsg);
            if (coaProdMsg != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.coaProdNm, coaProdMsg.coaProdNm);
            } else {
                cMsg.coaProdNm.clear();
            }
        } else {
            cMsg.coaProdNm.clear();
        }
    }

    /**
     * Search
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param glblCmpyCd String
     */
    public static void search(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_ROW_NUM, sMsg.A.length() + 1);
        // QC#28939 add start
        ssmParam.put("mdseItemTpCd", MDSE_ITEM_TP.TEXT_ITEM);
        // QC#28939 add end

        // PO_REQUISITION
        ssmParam.put(DB_PARAM_PO_REQUISITION, PRCH_REQ_REC_TP.PO_REQUISITION);
        // 2019/09/23 QC#53271 Add Start
        ssmParam.put(DB_PARAM_PRCH_REQ_LINE_TP_CD_EXPENSE, PRCH_REQ_LINE_TP.EXPENSE);
        ssmParam.put(DB_PARAM_PRCH_REQ_LINE_TP_CD_EXPENSE_W_RECEIPT, PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT);
        // 2019/09/23 QC#53271 Add End

        S21SsmEZDResult result = NPAL1270Query.getInstance().search(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NMAM8181W, new String[]{String.valueOf(sMsg.A.length()), String.valueOf(sMsg.A.length())});
                queryResCnt = sMsg.A.length();
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

            // Setting Next Page
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * Call API NSZC033001 (Save SearchOption)
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearchOption(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX) || isSameSaveSearchName(cMsg)) {
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

        pMsg.srchOptTxt_05.setValue(cMsg.prchReqCratDt_FR.getValue());
        pMsg.srchOptTxt_06.setValue(cMsg.prchReqCratDt_TO.getValue());
        pMsg.srchOptTxt_07.setValue(cMsg.rqstRcvDt_FR.getValue());
        pMsg.srchOptTxt_08.setValue(cMsg.rqstRcvDt_TO.getValue());

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.prchReqLineStsCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.prchReqSrcTpCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.trxRefNum);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.mrpPlnNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.fullPsnNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.prchGrpCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.shpgSvcLvlCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.carrNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, cMsg.prntVndNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, cMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, cMsg.vndNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, cMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, cMsg.rtlWhNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, cMsg.destRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, cMsg.rtlSwhNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, cMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, cMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_28, cMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_29, cMsg.xxScrDply);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_30, cMsg.coaMdseTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_31, cMsg.coaProdCd);
        // START 2023/02/01 S.Dong [QC#60966, ADD]
        pMsg.srchOptTxt_32.setValue(cMsg.rqstShipDt_FR.getValue());
        pMsg.srchOptTxt_33.setValue(cMsg.rqstShipDt_TO.getValue());
        // END 2023/02/01 S.Dong [QC#60966, ADD]
        
        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            createPullDownSearchOption(cMsg, glblCmpyCd, usrId);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_SL, pMsg.srchOptPk);
            // Message ; The process [@] has been successfully
            // completed.
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Save Search" });
        }
    }

    /**
     * Call API NSZC033001 (Delete SearchOption)
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearchOption(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            createPullDownSearchOption(cMsg, glblCmpyCd, usrId);
            cMsg.srchOptNm_TX.clear();
            cMsg.srchOptPk_SL.clear();
            cMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Delete Search") });
        }
    }

    /**
     * Call API NSZC033001 (Search SearchOption)
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchSearchOption(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg, String usrId, String glblCmpyCd) {
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

        cMsg.prchReqCratDt_FR.setValue(pMsg.srchOptTxt_05.getValue());
        cMsg.prchReqCratDt_TO.setValue(pMsg.srchOptTxt_06.getValue());
        cMsg.rqstRcvDt_FR.setValue(pMsg.srchOptTxt_07.getValue());
        cMsg.rqstRcvDt_TO.setValue(pMsg.srchOptTxt_08.getValue());

        ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineStsCd_SL, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd_SL, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(cMsg.trxRefNum, pMsg.srchOptTxt_11);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(cMsg.prchGrpCd_SL, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(cMsg.carrNm, pMsg.srchOptTxt_16);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, pMsg.srchOptTxt_17);
        ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, pMsg.srchOptTxt_19);
        ZYPEZDItemValueSetter.setValue(cMsg.vndCd, pMsg.srchOptTxt_20);
        ZYPEZDItemValueSetter.setValue(cMsg.vndNm, pMsg.srchOptTxt_21);
        ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd, pMsg.srchOptTxt_22);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, pMsg.srchOptTxt_23);
        ZYPEZDItemValueSetter.setValue(cMsg.destRtlSwhCd, pMsg.srchOptTxt_24);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, pMsg.srchOptTxt_25);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, pMsg.srchOptTxt_26);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm, pMsg.srchOptTxt_27);
        ZYPEZDItemValueSetter.setValue(cMsg.poOrdNum, pMsg.srchOptTxt_28);
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrDply, pMsg.srchOptTxt_29);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        ZYPEZDItemValueSetter.setValue(cMsg.coaMdseTpCd, pMsg.srchOptTxt_30);
        ZYPEZDItemValueSetter.setValue(cMsg.coaProdCd, pMsg.srchOptTxt_31);
        // START 2023/02/01 S.Dong [QC#60966, ADD]
        cMsg.rqstShipDt_FR.setValue(pMsg.srchOptTxt_32.getValue());
        cMsg.rqstShipDt_TO.setValue(pMsg.srchOptTxt_33.getValue());
        // END 2023/02/01 S.Dong [QC#60966, ADD]
    }

    /**
     * check duplicate search name
     * @param cMsg NPAL1270CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NPAL1270CMsg cMsg) {
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
     * isSameSaveSearchName
     * @param cMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NPAL1270CMsg cMsg) {
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
     * @param cMsg NPAL1270CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NPAL1270CMsg cMsg) {
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
     * @param bizMsg NPAL1270CMsg
     * @param pMsg NSZC033001PMsg
     * @return
     */
    private static boolean executeNszc0330(NPAL1270CMsg cMsg, NSZC033001PMsg pMsg) {
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
     * <pre>
     * Write csv file Contract Info
     * @param bizMsg NPAL1270CMsg
     * @param rs     ResultSet
     * @param glblCmpyCd     String
     * @throws SQLException
     * </pre>
     */
    public static void writeCsvFileInfo(NPAL1270CMsg bizMsg, ResultSet rs, String glblCmpyCd) throws SQLException {

        NPAL1270F00FMsg fMsg = new NPAL1270F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        csvOutFile.writeHeader(CSV_HEADER, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));
        // DisplayLevel
        if (!("Detail".equals(bizMsg.xxScrDply.getValue()))) {
            // searchDetail
            for (String colName : CSV_EXCLUSION_ITEM)
                fMsg.addExclusionItem(colName);
        }

        do {
            if (rs.getRow() > MAX_DOWNLOAD_CNT) {
                bizMsg.setMessageInfo(NZZM0007E, null);
                break;
            }

            ZYPEZDItemValueSetter.setValue(fMsg.prchReqNum_A, rs.getString("PRCH_REQ_NUM_H"));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqTpDescTxt_A, rs.getString("PRCH_REQ_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqStsDescTxt_A, rs.getString("PRCH_REQ_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqApvlStsDescTxt_A, rs.getString("PRCH_REQ_APVL_STS_DESC_TXT"));

            ZYPEZDItemValueSetter.setValue(fMsg.prchReqCratDt_A, rs.getString("PRCH_REQ_CRAT_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rqstRcvDt_A, rs.getString("RQST_RCV_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rqstRcvTm_A, rs.getString("RQST_RCV_TM"));
            // START 2023/02/01 S.Dong [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.rqstShipDt_A, rs.getString("RQST_SHIP_DT"));
            // END 2023/02/01 S.Dong [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqSrcTpDescTxt_A, rs.getString("PRCH_REQ_SRC_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.trxRefNum_A, rs.getString("TRX_REF_NUM"));
            //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            ZYPEZDItemValueSetter.setValue(fMsg.mrpPlnNm_A, rs.getString("MRP_PLN_NM"));
            //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqCratByPsnCd_A, rs.getString("PRCH_REQ_CRAT_BY_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.fullPsnNm_A, rs.getString("FULL_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.prchGrpCd_A, rs.getString("PRCH_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.shpgSvcLvlDescTxt_A, rs.getString("SHPG_SVC_LVL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.carrCd_A, rs.getString("CARR_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.carrNm_A, rs.getString("VND_NM1"));
            ZYPEZDItemValueSetter.setValue(fMsg.prntVndCd_A, rs.getString("PRNT_VND_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.prntVndNm_A, rs.getString("PRNT_VND_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.vndCd_A, rs.getString("VND_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.locNm_A, rs.getString("VND_NM2"));
            ZYPEZDItemValueSetter.setValue(fMsg.destRtlWhCd_A, rs.getString("DEST_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_A, rs.getString("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.destRtlSwhCd_A, rs.getString("DEST_RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhNm_A, rs.getString("RTL_SWH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToCustCd_A, rs.getString("SHIP_TO_CUST_CD_H"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.poOrdNum_A, rs.getString("PO_ORD_NUM"));
            if (bizMsg.xxScrDply.getValue().equals("Detail")) {
                ZYPEZDItemValueSetter.setValue(fMsg.prchReqLineNum_A, rs.getString("PRCH_REQ_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prchReqLineStsNm_A, rs.getString("PRCH_REQ_LINE_STS_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_A, rs.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_A, rs.getString("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.coaMdseTpCd_A, rs.getString("COA_MDSE_TP_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.coaProdCd_A, rs.getString("COA_PROD_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.prchReqQty_A, rs.getBigDecimal("PRCH_REQ_QTY"));
                ZYPEZDItemValueSetter.setValue(fMsg.poSchdRelDt_A, rs.getString("PO_SCHD_REL_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prchReqRelDtTmTs_A, rs.getString("PRCH_REQ_REL_DT_TM_TS"));
            }

            csvOutFile.write();

        } while (rs.next());
        csvOutFile.close();
    }

}
