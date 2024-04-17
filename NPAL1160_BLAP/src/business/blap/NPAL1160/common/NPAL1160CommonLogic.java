/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1160.common;

import static business.blap.NPAL1160.constant.NPAL1160Constant.ASTERISK;
import static business.blap.NPAL1160.constant.NPAL1160Constant.CSV_HEADER_INFO_LIMIT;
import static business.blap.NPAL1160.constant.NPAL1160Constant.CSV_HEADER_INFO_LOC;
import static business.blap.NPAL1160.constant.NPAL1160Constant.CSV_HEADER_INFO_MBR;
import static business.blap.NPAL1160.constant.NPAL1160Constant.CSV_HEADER_INFO_TEAM;
import static business.blap.NPAL1160.constant.NPAL1160Constant.CSV_HEADER_INFO_TRX;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_APVL_HIST_SRC_TP_CD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_APVL_HRCH_TP_CD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_APVL_TEAM_MBR_PK;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_APVL_TEAM_NM;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_APVL_TEAM_PK;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_APVL_TEAM_POSN_TP;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_MDSE_ITEM_TP_CD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_PRCH_GRP_CD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_PRCH_REQ_REC_TP_CD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_PRCH_REQ_TP_CD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_PSN_CD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_RGTN_STS_CD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_RTL_SWH_PRTY_LOC_FLG;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_PO_REQUISITION;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_INVENTORY_REQUEST;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DB_PARAM_SCR_ENT_AVAL_FLG;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_APVLLIMIT;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_LOCATION;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_MEMBER;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TEAM;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TECHMIN;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TECHTHRHD;
import static business.blap.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TRANSACTION;
import static business.blap.NPAL1160.constant.NPAL1160Constant.MAX_DOWNLOAD_CNT;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NLBM1344E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NMAM0038I;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NMAM0074E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NMAM0074E_PARAM1;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NMAM0074E_PARAM2;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NMAM0265E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NPAM0005I;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NPAM0007E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NPAM0049E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NPAM0077E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NPAM0108E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NPAM1199E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NPAM1215E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NPAM1237W;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NPAM1361E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NPAM1607E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NZZM0001W;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NZZM0003E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.NZZM0007E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.SEQUENCE_NM_APVL_LIMIT_SQ;
import static business.blap.NPAL1160.constant.NPAL1160Constant.SEQUENCE_NM_APVL_TEAM_LOC_SQ;
import static business.blap.NPAL1160.constant.NPAL1160Constant.SEQUENCE_NM_APVL_TEAM_MBR_SQ;
import static business.blap.NPAL1160.constant.NPAL1160Constant.SEQUENCE_NM_APVL_TEAM_SQ;
import static business.blap.NPAL1160.constant.NPAL1160Constant.SEQUENCE_NM_APVL_TEAM_TRX_SQ;
import static business.blap.NPAL1160.constant.NPAL1160Constant.ZZM9000E;
import static business.blap.NPAL1160.constant.NPAL1160Constant.ZZZM9003I;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.CHKBOX_ON_Y;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NPAL1160.NPAL1160CMsg;
import business.blap.NPAL1160.NPAL1160Query;
import business.blap.NPAL1160.NPAL1160SMsg;
import business.blap.NPAL1160.NPAL1160_ACMsg;
import business.blap.NPAL1160.NPAL1160_ASMsg;
import business.blap.NPAL1160.NPAL1160_BCMsg;
import business.blap.NPAL1160.NPAL1160_BSMsg;
import business.blap.NPAL1160.NPAL1160_CCMsg;
import business.blap.NPAL1160.NPAL1160_CSMsg;
import business.blap.NPAL1160.NPAL1160_DSMsg;
import business.blap.NPAL1160.NPAL1160_ECMsg;
import business.blap.NPAL1160.NPAL1160_ESMsg;
import business.blap.NPAL1160.NPAL1160_FSMsgArray;
import business.blap.NPAL1160.NPAL1160_GSMsgArray;
import business.blap.NPAL1160.NPAL1160_HSMsgArray;
import business.blap.NPAL1160.NPAL1160_ISMsgArray;
import business.blap.NPAL1160.NPAL1160_JSMsgArray;
import business.db.APVL_LIMITTMsg;
import business.db.APVL_TEAMTMsg;
import business.db.APVL_TEAM_LOCTMsg;
import business.db.APVL_TEAM_MBRTMsg;
import business.db.APVL_TEAM_TRXTMsg;
import business.db.TECH_APVL_MINTMsg;
import business.db.TECH_APVL_THRHDTMsg;
import business.file.NPAL1160F00FMsg;
import business.file.NPAL1160F01FMsg;
import business.file.NPAL1160F02FMsg;
import business.file.NPAL1160F03FMsg;
import business.file.NPAL1160F04FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HRCH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_TEAM_POSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NPAL1160 PO/Inventory Approval Maintenance
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            R Shimamoto     Create          N/A
 * 03/23/2016   CITS            K.Ogino         Update          QC#5272
 * 06/03/2016   CSAI            D.Fukaya        Update          QC#8408
 * 06/03/2016   CSAI            D.Fukaya        Update          QC#8416
 * 06/03/2016   CSAI            D.Fukaya        Update          QC#8473
 * 06/03/2016   CSAI            D.Fukaya        Update          QC#8539
 * 11/29/2016   CITS            K.Kameoka       Update          QC#16149
 * 12/12/2016   CITS            K.Ogino         Update          QC#16495
 * 11/14/2017   CITS            T.Tokutomi      Create          QC#18689-Sol#303
 * 12/18/2017   CITS            T.Tokutomi      Create          QC#18689-1
 * 05/07/2018   CITS            T.Tokutomi      Update          QC#25934
 * 05/17/2023   Hitachi         T.Kuroda        Update          QC#61211
 * 08/29/2023   Hitachi         M.Kikushima     Update          QC#61590
 *</pre>
 */
public class NPAL1160CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * Create Pulldown Requisition Type
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownHierarchyType(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {
        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(APVL_HRCH_TP.class, cMsg.apvlHrchTpCd_TC, cMsg.apvlHrchTpDescTxt_TD);
    }

    /**
     * Create Pulldown Position
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownPosition(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {
        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(APVL_TEAM_POSN_TP.class, cMsg.apvlTeamPosnTpCd_MC, cMsg.apvlTeamPosnTpDescTxt_MD);
    }

    /**
     * Create Pulldown Planning Group
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownPlanningGroup(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.prchGrpCd_SC.clear();
        cMsg.prchGrpDescTxt_SD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        // Execute
        S21SsmEZDResult result = NPAL1160Query.getInstance().getPlanningGroupPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchGrpCd_SC.no(i), (String) recode.get("PRCH_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.prchGrpDescTxt_SD.no(i), (String) recode.get("PRCH_GRP_DESC_TXT"));

                if (i >= cMsg.prchGrpCd_SC.length()) {
                    break;
                }

            }
        }
    }

    /**
     * Create Pulldown Transaction
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownTransaction(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {
        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(APVL_HIST_SRC_TP.class, cMsg.apvlHistSrcTpCd_SC, cMsg.apvlHistSrcTpDescTxt_SD);
    }

    /**
     * Create Pulldown Requisition Type By Approve Limit
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownHierarchyTypeByAL(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {
        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(APVL_HRCH_TP.class, cMsg.apvlHrchTpCd_LC, cMsg.apvlHrchTpDescTxt_LD);
    }

    /**
     * Create Pulldown Position By Approval Limit
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownPositionByAL(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {
        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(APVL_TEAM_POSN_TP.class, cMsg.apvlTeamPosnTpCd_AC, cMsg.apvlTeamPosnTpDescTxt_AD);
    }

    /**
     * Create Pulldown Planning Group By Approval Limit
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownPlanningGroupByAL(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.prchGrpCd_AC.clear();
        cMsg.prchGrpDescTxt_AD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        // Execute
        S21SsmEZDResult result = NPAL1160Query.getInstance().getPlanningGroupPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchGrpCd_AC.no(i), (String) recode.get("PRCH_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.prchGrpDescTxt_AD.no(i), (String) recode.get("PRCH_GRP_DESC_TXT"));

                if (i >= cMsg.prchGrpCd_AC.length()) {
                    break;
                }
            }
        }
    }

    // START 2023/08/29 M.Kikushima [QC#61590, ADD]
    /**
     * Create Pulldown Planning Group By Approval Limit
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownRequestTypeByAL(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.prchReqTpCd_AC.clear();
        cMsg.prchReqTpNm_AD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PO_REQUISITION, PRCH_REQ_REC_TP.PO_REQUISITION);
        ssmParam.put(DB_PARAM_INVENTORY_REQUEST, PRCH_REQ_REC_TP.INVENTORY_REQUEST);
        ssmParam.put(DB_PARAM_SCR_ENT_AVAL_FLG, ZYPConstant.FLG_ON_Y);

        // Execute
        S21SsmEZDResult result = NPAL1160Query.getInstance().getRequestTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_AC.no(i), (String) recode.get("PRCH_REQ_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpNm_AD.no(i), (String) recode.get("PRCH_REQ_TP_NM"));

                if (i >= cMsg.prchReqTpCd_AC.length()) {
                    break;
                }
            }
        }

    }
    // END 2023/08/29 M.Kikushima [QC#61590, ADD]

    /**
     * Create Pulldown Transaction By Approval Limit
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownTransactionByAL(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {
        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(APVL_HIST_SRC_TP.class, cMsg.apvlHistSrcTpCd_AC, cMsg.apvlHistSrcTpDescTxt_AD);
    }

    /**
     * setHierarchyType
     * @param glblCmpyCd String
     * @param asMsg NPAL1160_ASMsg
     */
    public static void setHierarchyType(String glblCmpyCd, NPAL1160_ASMsg asMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().getHierarchyTypePulldownList(ssmParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(asMsg.apvlHrchTpCd_AC.no(i), (String) map.get("APVL_HRCH_TP_CD"));
                ZYPEZDItemValueSetter.setValue(asMsg.apvlHrchTpDescTxt_AD.no(i), (String) map.get("APVL_HRCH_TP_DESC_TXT"));

                if (i >= asMsg.apvlHrchTpCd_AC.length()) {
                    break;
                }
            }
        }
    }

    /**
     * @param glblCmpyCd String
     * @param acMsg NPAL1160_ACMsg
     */
    public static void setHierarchyType(String glblCmpyCd, NPAL1160_ACMsg acMsg) {
        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(APVL_HRCH_TP.class, acMsg.apvlHrchTpCd_AC, acMsg.apvlHrchTpDescTxt_AD);
    }

    /**
     * setPosition
     * @param glblCmpyCd String
     * @param bsMsg NPAL1160_BSMsg
     */
    public static void setPosition(String glblCmpyCd, NPAL1160_BSMsg bsMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().getPositionPulldownList(ssmParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(bsMsg.apvlTeamPosnTpCd_BC.no(i), (String) map.get("APVL_TEAM_POSN_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bsMsg.apvlTeamPosnTpDescTxt_BD.no(i), (String) map.get("APVL_TEAM_POSN_TP_DESC_TXT"));

                if (i >= bsMsg.apvlTeamPosnTpCd_BC.length()) {
                    break;
                }
            }
        }
    }

    /**
     * setPosition
     * @param glblCmpyCd String
     * @param bcMsg NPAL1160_BCMsg
     */
    public static void setPosition(String glblCmpyCd, NPAL1160_BCMsg bcMsg) {
        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(APVL_TEAM_POSN_TP.class, bcMsg.apvlTeamPosnTpCd_BC, bcMsg.apvlTeamPosnTpDescTxt_BD);
    }

    /**
     * sePlanningGroup
     * @param glblCmpyCd String
     * @param csMsg NPAL1160_CSMsg
     */
    public static void setPlanningGroup(String glblCmpyCd, NPAL1160_CSMsg csMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().getPlanningGroupPulldownList(ssmParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(csMsg.prchGrpCd_CC.no(i), (String) map.get("PRCH_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(csMsg.prchGrpDescTxt_CD.no(i), (String) map.get("PRCH_GRP_DESC_TXT"));

                if (i >= csMsg.prchGrpCd_CC.length()) {
                    break;
                }
            }
        }
    }

    /**
     * sePlanningGroup
     * @param glblCmpyCd String
     * @param ccMsg NPAL1160_CCMsg
     */
    public static void setPlanningGroup(String glblCmpyCd, NPAL1160_CCMsg ccMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().getPlanningGroupPulldownList(ssmParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(ccMsg.prchGrpCd_CC.no(i), (String) map.get("PRCH_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(ccMsg.prchGrpDescTxt_CD.no(i), (String) map.get("PRCH_GRP_DESC_TXT"));

                if (i >= ccMsg.prchGrpCd_CC.length()) {
                    break;
                }
            }
        }
    }

    /**
     * setTransaction
     * @param glblCmpyCd String
     * @param ccMsg NPAL1160_CCMsg
     */
    public static void setTransaction(String glblCmpyCd, NPAL1160_CSMsg csMsg) {
        // create Pull-Down
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().getTransactionPulldownList(ssmParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(csMsg.apvlHistSrcTpCd_CC.no(i), (String) map.get("APVL_HIST_SRC_TP_CD"));
                ZYPEZDItemValueSetter.setValue(csMsg.apvlHistSrcTpDescTxt_CD.no(i), (String) map.get("APVL_HIST_SRC_TP_DESC_TXT"));

                if (i >= csMsg.apvlHistSrcTpCd_CC.length()) {
                    break;
                }
            }
        }
    }

    /**
     * setHierarchyType By Approval Limit
     * @param glblCmpyCd String
     * @param asMsg NPAL1160_ASMsg
     */
    public static void setHierarchyTypeByAL(String glblCmpyCd, NPAL1160_ESMsg asMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().getHierarchyTypePulldownList(ssmParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(asMsg.apvlHrchTpCd_EC.no(i), (String) map.get("APVL_HRCH_TP_CD"));
                ZYPEZDItemValueSetter.setValue(asMsg.apvlHrchTpDescTxt_ED.no(i), (String) map.get("APVL_HRCH_TP_DESC_TXT"));

                if (i >= asMsg.apvlHrchTpCd_EC.length()) {
                    break;
                }
            }
        }
    }

    /**
     * setHierarchyType By Approval Limit
     * @param glblCmpyCd String
     * @param ecMsg NPAL1160_ECMsg
     */
    public static void setHierarchyTypeByAL(String glblCmpyCd, NPAL1160_ECMsg ecMsg) {
        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(APVL_HRCH_TP.class, ecMsg.apvlHrchTpCd_EC, ecMsg.apvlHrchTpDescTxt_ED);
    }

    /**
     * setPosition By Approval Limit
     * @param glblCmpyCd String
     * @param bsMsg NPAL1160_ESMsg
     */
    public static void setPositionByAL(String glblCmpyCd, NPAL1160_ESMsg bsMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().getPositionPulldownList(ssmParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(bsMsg.apvlTeamPosnTpCd_EC.no(i), (String) map.get("APVL_TEAM_POSN_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bsMsg.apvlTeamPosnTpDescTxt_ED.no(i), (String) map.get("APVL_TEAM_POSN_TP_DESC_TXT"));

                if (i >= bsMsg.apvlTeamPosnTpCd_EC.length()) {
                    break;
                }
            }
        }
    }

    /**
     * setPosition By Approval Limit
     * @param glblCmpyCd String
     * @param ecMsg NPAL1160_ECMsg
     */
    public static void setPositionByAL(String glblCmpyCd, NPAL1160_ECMsg ecMsg) {
        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(APVL_TEAM_POSN_TP.class, ecMsg.apvlTeamPosnTpCd_EC, ecMsg.apvlTeamPosnTpDescTxt_ED);
    }

    /**
     * sePlanningGroup By Approval Limit
     * @param glblCmpyCd String
     * @param csMsg NPAL1160_ESMsg
     */
    public static void setPlanningGroupByAL(String glblCmpyCd, NPAL1160_ESMsg csMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().getPlanningGroupPulldownList(ssmParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(csMsg.prchGrpCd_EC.no(i), (String) map.get("PRCH_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(csMsg.prchGrpDescTxt_ED.no(i), (String) map.get("PRCH_GRP_DESC_TXT"));

                if (i >= csMsg.prchGrpCd_EC.length()) {
                    break;
                }
            }
        }
    }

    /**
     * sePlanningGroup By Approval Limit
     * @param glblCmpyCd String
     * @param ecMsg NPAL1160_ECMsg
     */
    public static void setPlanningGroupByAL(String glblCmpyCd, NPAL1160_ECMsg ecMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().getPlanningGroupPulldownList(ssmParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(ecMsg.prchGrpCd_EC.no(i), (String) map.get("PRCH_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(ecMsg.prchGrpDescTxt_ED.no(i), (String) map.get("PRCH_GRP_DESC_TXT"));

                if (i >= ecMsg.prchGrpCd_EC.length()) {
                    break;
                }
            }
        }
    }

    /**
     * setTransaction By Approval Limit
     * @param glblCmpyCd String
     * @param csMsg NPAL1160_ESMsg
     */
    public static void setTransactionByAL(String glblCmpyCd, NPAL1160_ESMsg csMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().getTransactionPulldownList(ssmParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(csMsg.apvlHistSrcTpCd_EC.no(i), (String) map.get("APVL_HIST_SRC_TP_CD"));
                ZYPEZDItemValueSetter.setValue(csMsg.apvlHistSrcTpDescTxt_ED.no(i), (String) map.get("APVL_HIST_SRC_TP_DESC_TXT"));

                if (i >= csMsg.apvlHistSrcTpCd_EC.length()) {
                    break;
                }
            }
        }
    }

    /**
     * setTransaction By Approval Limit
     * @param glblCmpyCd String
     * @param ecMsg NPAL1160_ECMsg
     */
    public static void setTransactionByAL(String glblCmpyCd, NPAL1160_ECMsg ecMsg) {
        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(APVL_HIST_SRC_TP.class, ecMsg.apvlHistSrcTpCd_EC, ecMsg.apvlHistSrcTpDescTxt_ED);
    }

    /**
     * setParts/MERCH
     * @param glblCmpyCd String
     * @param csMsg NPAL1160_CCMsg
     */
    public static void setPartsMERCH(String glblCmpyCd, NPAL1160_CSMsg csMsg) {
        // create Pull-Down
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().getPartsMERCHPulldownList(ssmParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(csMsg.mdseItemTpCd_CC.no(i), (String) map.get("PO_MDSE_TP_CD"));
                ZYPEZDItemValueSetter.setValue(csMsg.mdseItemTpDescTxt_CD.no(i), (String) map.get("PO_MDSE_TP_DESC_TXT"));

                if (i >= csMsg.mdseItemTpCd_CC.length()) {
                    break;
                }
            }
        }

    }

    /**
     * Search Team
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void searchTeam(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        ZYPTableUtil.clear(sMsg.F);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        S21SsmEZDResult result = NPAL1160Query.getInstance().searchTeam(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }

            List<Map<String, Object>> list = (List) result.getResultObject();

            // Copy 1 page Data(SMsg -> CMsg)
            // QC#16149 Start
            // for (int i = 0; i < list.size(); i++) {
            int listCnt = list.size();
            if (list.size() > queryResCnt) {
                listCnt = queryResCnt;
            }
            for (int i = 0; i < listCnt; i++) {
                // QC#16149 End
                Map<String, Object> map = list.get(i);

                // Copy to CMsg
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).apvlTeamPk_A1, (BigDecimal) map.get("APVL_TEAM_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).apvlTeamNm_A1, (String) map.get("APVL_TEAM_NM"));
                setHierarchyType(glblCmpyCd, sMsg.A.no(i));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).apvlHrchTpCd_AS, (String) map.get("APVL_HRCH_TP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).apvlTeamDescTxt_A1, (String) map.get("APVL_TEAM_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).deleteFlagIf_A1, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTime_A1, (String) map.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTimeZone_A1, (String) map.get("EZUPTIMEZONE"));

            }
            // QC#16149 Start
            // sMsg.A.setValidCount(list.size());
            sMsg.A.setValidCount(listCnt);

            // QC#16149 End

            // Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            cMsg.A.setValidCount(i);
            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    /**
     * Search Member
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void searchMember(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        ZYPTableUtil.clear(sMsg.G);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        S21SsmEZDResult result = NPAL1160Query.getInstance().searchMember(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.B.length();
            }

            List<Map<String, Object>> list = (List) result.getResultObject();

            // Copy 1 page Data(SMsg -> CMsg)
            // QC#16149 Start
            int listCnt = list.size();
            if (list.size() > queryResCnt) {
                listCnt = queryResCnt;
            }
            for (int i = 0; i < listCnt; i++) {
                // for (int i = 0; i < list.size(); i++) {
                // QC#16149 End
                Map<String, Object> map = list.get(i);

                // Copy to CMsg
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamMbrPk_B1, (BigDecimal) map.get("APVL_TEAM_MBR_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamPk_B1, (BigDecimal) map.get("APVL_TEAM_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamNm_B1, (String) map.get("APVL_TEAM_NM"));
                setPosition(glblCmpyCd, sMsg.B.no(i));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamPosnTpCd_BS, (String) map.get("APVL_TEAM_POSN_TP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).psnCd_B1, (String) map.get("PSN_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).fullPsnNm_B1, (String) map.get("FULL_PSN_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).ezUpTime_B1, (String) map.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).ezUpTimeZone_B1, (String) map.get("EZUPTIMEZONE"));
            }
            // QC#16149 Start
            // sMsg.B.setValidCount(list.size());
            sMsg.B.setValidCount(listCnt);
            // QC#16149 End

            // Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }

            cMsg.B.setValidCount(i);
            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.B.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    /**
     * Search Member PK
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param apvlTeamPk BigDecimal
     */
    public static void searchMemberForPK(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, BigDecimal apvlTeamPk) {

        ZYPTableUtil.clear(sMsg.G);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_APVL_TEAM_PK, apvlTeamPk);

        S21SsmEZDResult result = NPAL1160Query.getInstance().searchMemberForPK(ssmParam);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.B.length();
            }

            List<Map<String, Object>> list = (List) result.getResultObject();

            // Copy 1 page Data(SMsg -> CMsg)
            // QC#16149 Start
            int listCnt = list.size();
            if (list.size() > queryResCnt) {
                listCnt = queryResCnt;
            }
            for (int i = 0; i < listCnt; i++) {
                // for (int i = 0; i < list.size(); i++) {
                // QC#16149 End
                Map<String, Object> map = list.get(i);

                // Copy to CMsg
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamMbrPk_B1, (BigDecimal) map.get("APVL_TEAM_MBR_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamPk_B1, (BigDecimal) map.get("APVL_TEAM_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamNm_B1, (String) map.get("APVL_TEAM_NM"));
                setPosition(glblCmpyCd, sMsg.B.no(i));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).apvlTeamPosnTpCd_BS, (String) map.get("APVL_TEAM_POSN_TP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).psnCd_B1, (String) map.get("PSN_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).fullPsnNm_B1, (String) map.get("FULL_PSN_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).ezUpTime_B1, (String) map.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).ezUpTimeZone_B1, (String) map.get("EZUPTIMEZONE"));
            }
            // QC#16149 Start
            // sMsg.B.setValidCount(list.size());
            sMsg.B.setValidCount(listCnt);
            // QC#16149 End

            // Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }

            cMsg.B.setValidCount(i);
            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.B.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    /**
     * Search Member Upload
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param apvlTeamMbrPk BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal searchMemberUpload(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, BigDecimal apvlTeamMbrPk) {

        BigDecimal getPk = BigDecimal.ZERO;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_APVL_TEAM_MBR_PK, apvlTeamMbrPk);

        S21SsmEZDResult result = NPAL1160Query.getInstance().searchMemberForUpload(ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List) result.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                getPk = (BigDecimal) map.get("APVL_TEAM_MBR_PK");
            }
        }
        return getPk;
    }

    /**
     * Search Transaction
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void searchTransaction(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        ZYPTableUtil.clear(sMsg.H);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        S21SsmEZDResult result = NPAL1160Query.getInstance().searchTransaction(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.C.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.C.length();
            }

            List<Map<String, Object>> list = (List) result.getResultObject();

            // Copy 1 page Data(SMsg -> CMsg)
            // QC#16149 Start
            int listCnt = list.size();
            if (list.size() > queryResCnt) {
                listCnt = queryResCnt;
            }
            for (int i = 0; i < listCnt; i++) {
                // for (int i = 0; i < list.size(); i++) {
                // QC#16149 End
                Map<String, Object> map = list.get(i);
                // Copy to CMsg
                setPartsMERCH(glblCmpyCd, sMsg.C.no(i));
                setTransaction(glblCmpyCd, sMsg.C.no(i));

                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).apvlTeamTrxPk_C1, (BigDecimal) map.get("APVL_TEAM_TRX_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).apvlTeamPk_C1, (BigDecimal) map.get("APVL_TEAM_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).apvlTeamNm_C1, (String) map.get("APVL_TEAM_NM"));
                setPlanningGroup(glblCmpyCd, sMsg.C.no(i));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).prchGrpCd_CS, (String) map.get("PRCH_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).mdseItemTpCd_CS, (String) map.get("PO_MDSE_TP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).apvlHistSrcTpCd_CS, (String) map.get("APVL_HIST_SRC_TP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).ezUpTime_C1, (String) map.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).ezUpTimeZone_C1, (String) map.get("EZUPTIMEZONE"));
            }
            // QC#16149 Start
            // sMsg.C.setValidCount(list.size());
            sMsg.C.setValidCount(listCnt);
            // QC#16149 End

            // Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == cMsg.C.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i), null);
            }

            cMsg.C.setValidCount(i);
            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.C.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    /**
     * Search Transaction PK
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param apvlTeamPk BigDecimal
     */
    public static void searchTransactionForPK(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, BigDecimal apvlTeamPk) {

        ZYPTableUtil.clear(sMsg.H);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_APVL_TEAM_PK, apvlTeamPk);

        S21SsmEZDResult result = NPAL1160Query.getInstance().searchTransactionForPK(ssmParam);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.C.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.C.length();
            }

            List<Map<String, Object>> list = (List) result.getResultObject();

            // Copy 1 page Data(SMsg -> CMsg)
            // QC#16149 Start
            int listCnt = list.size();
            if (list.size() > queryResCnt) {
                listCnt = queryResCnt;
            }
            for (int i = 0; i < listCnt; i++) {
                // for (int i = 0; i < list.size(); i++) {
                // QC#16149 End
                Map<String, Object> map = list.get(i);
                // Copy to CMsg
                setPartsMERCH(glblCmpyCd, sMsg.C.no(i));
                setTransaction(glblCmpyCd, sMsg.C.no(i));

                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).apvlTeamTrxPk_C1, (BigDecimal) map.get("APVL_TEAM_TRX_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).apvlTeamPk_C1, (BigDecimal) map.get("APVL_TEAM_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).apvlTeamNm_C1, (String) map.get("APVL_TEAM_NM"));
                setPlanningGroup(glblCmpyCd, sMsg.C.no(i));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).prchGrpCd_CS, (String) map.get("PRCH_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).mdseItemTpCd_CS, (String) map.get("PO_MDSE_TP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).apvlHistSrcTpCd_CS, (String) map.get("APVL_HIST_SRC_TP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).ezUpTime_C1, (String) map.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).ezUpTimeZone_C1, (String) map.get("EZUPTIMEZONE"));
            }
            // QC#16149 Start
            // sMsg.C.setValidCount(list.size());
            sMsg.C.setValidCount(listCnt);
            // QC#16149 End

            // Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == cMsg.C.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i), null);
            }

            cMsg.C.setValidCount(i);
            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.C.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    /**
     * Search Location
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void searchLocation(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        ZYPTableUtil.clear(sMsg.I);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);


        ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

        S21SsmEZDResult result = NPAL1160Query.getInstance().searchLocation(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.D.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.D.length();
            }

            List<Map<String, Object>> list = (List) result.getResultObject();

            // Copy 1 page Data(SMsg -> CMsg)
            // QC#16149 Start
            int listCnt = list.size();
            if (list.size() > queryResCnt) {
                listCnt = queryResCnt;
            }
            for (int i = 0; i < listCnt; i++) {
                // for (int i = 0; i < list.size(); i++) {
                // QC#16149 End
                Map<String, Object> map = list.get(i);
                // Copy to CMsg
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamLocPk_D1, (BigDecimal) map.get("APVL_TEAM_LOC_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamPk_D1, (BigDecimal) map.get("APVL_TEAM_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamNm_D1, (String) map.get("APVL_TEAM_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).rtlWhCd_D1, (String) map.get("RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).rtlWhNm_D1, (String) map.get("RTL_WH_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).ezUpTime_D1, (String) map.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).ezUpTimeZone_D1, (String) map.get("EZUPTIMEZONE"));
            }
            // QC#16149 Start
            // sMsg.D.setValidCount(list.size());
            sMsg.D.setValidCount(listCnt);
            // QC#16149 End

            // Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == cMsg.D.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.D.no(i), null, cMsg.D.no(i), null);
            }

            cMsg.D.setValidCount(i);
            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.D.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    /**
     * Search Location PK
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param apvlTeamPk BigDecimal
     */
    public static void searchLocationForPK(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, BigDecimal apvlTeamPk) {

        ZYPTableUtil.clear(sMsg.I);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_APVL_TEAM_PK, apvlTeamPk);
        ssmParam.put(DB_PARAM_RTL_SWH_PRTY_LOC_FLG, ZYPConstant.FLG_ON_Y);
        ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

        S21SsmEZDResult result = NPAL1160Query.getInstance().searchLocationForPK(ssmParam);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.D.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.D.length();
            }

            List<Map<String, Object>> list = (List) result.getResultObject();

            // Copy 1 page Data(SMsg -> CMsg)
            // QC#16149 Start
            int listCnt = list.size();
            if (list.size() > queryResCnt) {
                listCnt = queryResCnt;
            }
            for (int i = 0; i < listCnt; i++) {
                // for (int i = 0; i < list.size(); i++) {
                // QC#16149 End
                Map<String, Object> map = list.get(i);
                // Copy to CMsg
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamLocPk_D1, (BigDecimal) map.get("APVL_TEAM_LOC_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamPk_D1, (BigDecimal) map.get("APVL_TEAM_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).apvlTeamNm_D1, (String) map.get("APVL_TEAM_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).rtlWhCd_D1, (String) map.get("RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).rtlWhNm_D1, (String) map.get("RTL_WH_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).ezUpTime_D1, (String) map.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).ezUpTimeZone_D1, (String) map.get("EZUPTIMEZONE"));
            }
            // QC#16149 Start
            // sMsg.D.setValidCount(list.size());
            sMsg.D.setValidCount(listCnt);
            // QC#16149 End

            // Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == cMsg.D.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.D.no(i), null, cMsg.D.no(i), null);
            }

            cMsg.D.setValidCount(i);
            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.D.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    /**
     * Search Approval Limit
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void searchApprovalLimit(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        ZYPTableUtil.clear(sMsg.J);

        // START 2023/08/29 M.Kikushima [QC#61590, ADD]
        List<Map<String, String>> reqTypeListForIR = getRequestType(glblCmpyCd, APVL_HIST_SRC_TP.INVENTORY_REQUEST);
        List<Map<String, String>> reqTypeListForPR = getRequestType(glblCmpyCd, APVL_HIST_SRC_TP.PO_REQUISITION);
        // END 2023/08/29 M.Kikushima [QC#61590, ADD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        S21SsmEZDResult result = NPAL1160Query.getInstance().searchApprovalLimit(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.E.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.D.length();
            }

            List<Map<String, Object>> list = (List) result.getResultObject();

            // Copy 1 page Data(SMsg -> CMsg)
            // QC#16149 Start
            int listCnt = list.size();
            if (list.size() > queryResCnt) {
                listCnt = queryResCnt;
            }
            for (int i = 0; i < listCnt; i++) {
                // for (int i = 0; i < list.size(); i++) {
                // QC#16149 End
                Map<String, Object> map = list.get(i);
                // Copy to CMsg
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).apvlLimitPk_E1, (BigDecimal) map.get("APVL_LIMIT_PK"));
                setHierarchyTypeByAL(glblCmpyCd, sMsg.E.no(i));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).apvlHrchTpCd_ES, (String) map.get("APVL_HRCH_TP_CD"));
                setPositionByAL(glblCmpyCd, sMsg.E.no(i));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).apvlTeamPosnTpCd_ES, (String) map.get("APVL_TEAM_POSN_TP_CD"));
                setPlanningGroupByAL(glblCmpyCd, sMsg.E.no(i));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).prchGrpCd_ES, (String) map.get("PRCH_GRP_CD"));
                setTransactionByAL(glblCmpyCd, sMsg.E.no(i));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).apvlHistSrcTpCd_ES, (String) map.get("APVL_HIST_SRC_TP_CD"));

                // START 2023/08/29 M.Kikushima [QC#61590, ADD]
                if(APVL_HIST_SRC_TP.INVENTORY_REQUEST.equals(sMsg.E.no(i).apvlHistSrcTpCd_ES.getValue())
                   || APVL_HIST_SRC_TP.PO_REQUISITION.equals(sMsg.E.no(i).apvlHistSrcTpCd_ES.getValue())) {
                    if(APVL_HIST_SRC_TP.INVENTORY_REQUEST.equals(sMsg.E.no(i).apvlHistSrcTpCd_ES.getValue())) {
                        setRequestTypeByAL(glblCmpyCd, sMsg.E.no(i),reqTypeListForIR);
                    } else if(APVL_HIST_SRC_TP.PO_REQUISITION.equals(sMsg.E.no(i).apvlHistSrcTpCd_ES.getValue())){
                        setRequestTypeByAL(glblCmpyCd, sMsg.E.no(i),reqTypeListForPR);
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).prchReqTpCd_ES, (String) map.get("PRCH_REQ_TP_CD"));
                }
                // END 2023/08/29 M.Kikushima [QC#61590, ADD]

                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).psnCd_E1, (String) map.get("PSN_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).fullPsnNm_E1, (String) map.get("FULL_PSN_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).apvlLimitAmt_E1, (BigDecimal) map.get("APVL_LIMIT_AMT"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).ezUpTime_E1, (String) map.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).ezUpTimeZone_E1, (String) map.get("EZUPTIMEZONE"));
            }
            // QC#16149 Start
            // sMsg.E.setValidCount(list.size());
            sMsg.E.setValidCount(listCnt);
            // QC#16149 End

            // Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == cMsg.E.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.E.no(i), null, cMsg.E.no(i), null);
            }

            cMsg.E.setValidCount(i);
            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.E.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    /**
     * searchTechThreshold
     *  QC#18689 Add Method.
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void searchTechThreshold(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        ZYPTableUtil.clear(sMsg.K);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        S21SsmEZDResult result = NPAL1160Query.getInstance().searchTechThreshold(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.K.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.K.length();
            }

            // Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == cMsg.K.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.K.no(i), null, cMsg.K.no(i), null);
            }
            cMsg.K.setValidCount(i);

            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.K.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    // START 2023/05/17 T.Kuroda [QC#61211, ADD]
    /**
     * searchTechApvlMin
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void searchTechApvlMin(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        ZYPTableUtil.clear(sMsg.L);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        S21SsmEZDResult result = NPAL1160Query.getInstance().searchTechApprovalMinimum(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.L.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.L.length();
            }

            // Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == cMsg.L.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.L.no(i), null, cMsg.L.no(i), null);
            }
            cMsg.L.setValidCount(i);

            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.L.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
    }
    // END   2023/05/17 T.Kuroda [QC#61211, ADD]

    /**
     * <pre>
     * copyFromCmsgOntoSmsg
     * Copy data From CMsg Onto SMsg
     * </pre>
     * @param sMsg NPAL1160CMsg
     * @param cMsg NPAL1160SMsg
     */
    public static void copyFromCmsgOntoSmsg(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE A
            if (sMsg.A.getValidCount() == 0) {
                return;
            }
            for (int j = cMsg.xxPageShowFromNum.getValueInt() - 1, k = 0; j < cMsg.xxPageShowToNum.getValueInt(); j++, k++) {
                EZDMsg.copy(cMsg.A.no(k), null, sMsg.A.no(j), null);
            }

        } else if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE B
            if (sMsg.B.getValidCount() == 0) {
                return;
            }
            for (int j = cMsg.xxPageShowFromNum.getValueInt() - 1, k = 0; j < cMsg.xxPageShowToNum.getValueInt(); j++, k++) {
                EZDMsg.copy(cMsg.B.no(k), null, sMsg.B.no(j), null);
            }

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE C
            if (sMsg.C.getValidCount() == 0) {
                return;
            }
            for (int j = cMsg.xxPageShowFromNum.getValueInt() - 1, k = 0; j < cMsg.xxPageShowToNum.getValueInt(); j++, k++) {
                EZDMsg.copy(cMsg.C.no(k), null, sMsg.C.no(j), null);
            }

        } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE D
            if (sMsg.D.getValidCount() == 0) {
                return;
            }
            for (int j = cMsg.xxPageShowFromNum.getValueInt() - 1, k = 0; j < cMsg.xxPageShowToNum.getValueInt(); j++, k++) {
                EZDMsg.copy(cMsg.D.no(k), null, sMsg.D.no(j), null);
            }

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE E
            if (sMsg.E.getValidCount() == 0) {
                return;
            }
            for (int j = cMsg.xxPageShowFromNum.getValueInt() - 1, k = 0; j < cMsg.xxPageShowToNum.getValueInt(); j++, k++) {
                EZDMsg.copy(cMsg.E.no(k), null, sMsg.E.no(j), null);
            }

        } else if (DISPLAY_TAB_NM_TECHTHRHD.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE K
            if (sMsg.K.getValidCount() == 0) {
                return;
            }
            for (int j = cMsg.xxPageShowFromNum.getValueInt() - 1, k = 0; j < cMsg.xxPageShowToNum.getValueInt(); j++, k++) {
                EZDMsg.copy(cMsg.K.no(k), null, sMsg.K.no(j), null);
            }

        }

        // START 2023/05/17 T.Kuroda [QC#61211, ADD]
        else if (DISPLAY_TAB_NM_TECHMIN.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE L
            if (sMsg.L.getValidCount() == 0) {
                return;
            }
            for (int j = cMsg.xxPageShowFromNum.getValueInt() - 1, k = 0; j < cMsg.xxPageShowToNum.getValueInt(); j++, k++) {
                EZDMsg.copy(cMsg.L.no(k), null, sMsg.L.no(j), null);
            }
    
        }
        // END   2023/05/17 T.Kuroda [QC#61211, ADD]

    }

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * </pre>
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    public static void copyFromSMsgOntoCmsg(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE A
            ZYPTableUtil.clear(cMsg.A);
            setPagePos(cMsg, sMsg);

            if (0 < sMsg.A.getValidCount()) {
                int cMsgCount = 0;
                for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {
                    if (!ZYPConstant.FLG_ON_1.equals(sMsg.A.no(i).deleteFlagIf_A1.getValue())) {
                        EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                        cMsgCount++;
                    }
                }
                cMsg.A.setValidCount(cMsgCount);
            }

        } else if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE B
            ZYPTableUtil.clear(cMsg.B);
            setPagePos(cMsg, sMsg);

            if (0 < sMsg.B.getValidCount()) {
                int cMsgCount = 0;
                for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {
                    EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(cMsgCount), null);
                    cMsgCount++;
                }
                cMsg.B.setValidCount(cMsgCount);
            }

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE C
            ZYPTableUtil.clear(cMsg.C);
            setPagePos(cMsg, sMsg);

            if (0 < sMsg.C.getValidCount()) {
                int cMsgCount = 0;
                for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {
                    EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(cMsgCount), null);
                    cMsgCount++;
                }
                cMsg.C.setValidCount(cMsgCount);
            }

        } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE D
            ZYPTableUtil.clear(cMsg.D);
            setPagePos(cMsg, sMsg);

            if (0 < sMsg.D.getValidCount()) {
                int cMsgCount = 0;
                for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {
                    EZDMsg.copy(sMsg.D.no(i), null, cMsg.D.no(cMsgCount), null);
                    cMsgCount++;
                }
                cMsg.D.setValidCount(cMsgCount);
            }

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE E
            ZYPTableUtil.clear(cMsg.E);
            setPagePos(cMsg, sMsg);

            if (0 < sMsg.E.getValidCount()) {
                int cMsgCount = 0;
                for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {
                    EZDMsg.copy(sMsg.E.no(i), null, cMsg.E.no(cMsgCount), null);
                    cMsgCount++;
                }
                cMsg.E.setValidCount(cMsgCount);
            }

        } else if (DISPLAY_TAB_NM_TECHTHRHD.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE K
            ZYPTableUtil.clear(cMsg.K);
            setPagePos(cMsg, sMsg);

            if (0 < sMsg.K.getValidCount()) {
                int cMsgCount = 0;
                for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {
                    EZDMsg.copy(sMsg.K.no(i), null, cMsg.K.no(cMsgCount), null);
                    cMsgCount++;
                }
                cMsg.K.setValidCount(cMsgCount);
            }

        }

        // START 2023/05/17 T.Kuroda [QC#61211, ADD]
        else if (DISPLAY_TAB_NM_TECHMIN.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE L
            ZYPTableUtil.clear(cMsg.L);
            setPagePos(cMsg, sMsg);

            if (0 < sMsg.L.getValidCount()) {
                int cMsgCount = 0;
                for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {
                    EZDMsg.copy(sMsg.L.no(i), null, cMsg.L.no(cMsgCount), null);
                    cMsgCount++;
                }
                cMsg.L.setValidCount(cMsgCount);
            }

        }
        // END   2023/05/17 T.Kuroda [QC#61211, ADD]

    }

    /**
     * <pre>
     * Set page pos.
     * </pre>
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    public static void setPagePos(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE A
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
            int allRowCount = sMsg.A.getValidCount();
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

        } else if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE B
            if ((cMsg.B.length() == 0) || (sMsg.B.length() == 0) || (sMsg.B.getValidCount() <= 0)) {
                cMsg.xxPageShowFromNum.setValue(0);
                cMsg.xxPageShowToNum.setValue(0);
                cMsg.xxPageShowOfNum.setValue(0);
                return;
            }
            int startRowCount = 0;
            if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
                startRowCount = cMsg.xxPageShowFromNum.getValueInt();
            }
            int allRowCount = sMsg.B.getValidCount();
            if (startRowCount == 0) {
                cMsg.xxPageShowFromNum.setValue(1);
            } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {
                // last page
                cMsg.xxPageShowFromNum.setValue(getLastPageStartCount(allRowCount, cMsg.B.length()));
            } else {
                if ((startRowCount % cMsg.B.length()) != 1) {
                    startRowCount = startRowCount - (startRowCount % cMsg.B.length()) + 1;
                }
                cMsg.xxPageShowFromNum.setValue(startRowCount);
            }
            if ((cMsg.xxPageShowFromNum.getValueInt() + cMsg.B.length() - 1) < allRowCount) {
                cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.B.length() - 1);
            } else {
                cMsg.xxPageShowToNum.setValue(allRowCount);
            }
            cMsg.xxPageShowOfNum.setValue(allRowCount);

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE C
            if ((cMsg.C.length() == 0) || (sMsg.C.length() == 0) || (sMsg.C.getValidCount() <= 0)) {
                cMsg.xxPageShowFromNum.setValue(0);
                cMsg.xxPageShowToNum.setValue(0);
                cMsg.xxPageShowOfNum.setValue(0);
                return;
            }
            int startRowCount = 0;
            if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
                startRowCount = cMsg.xxPageShowFromNum.getValueInt();
            }
            int allRowCount = sMsg.C.getValidCount();
            if (startRowCount == 0) {
                cMsg.xxPageShowFromNum.setValue(1);
            } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {
                // last page
                cMsg.xxPageShowFromNum.setValue(getLastPageStartCount(allRowCount, cMsg.C.length()));
            } else {
                if ((startRowCount % cMsg.C.length()) != 1) {
                    startRowCount = startRowCount - (startRowCount % cMsg.C.length()) + 1;
                }
                cMsg.xxPageShowFromNum.setValue(startRowCount);
            }
            if ((cMsg.xxPageShowFromNum.getValueInt() + cMsg.C.length() - 1) < allRowCount) {
                cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.C.length() - 1);
            } else {
                cMsg.xxPageShowToNum.setValue(allRowCount);
            }
            cMsg.xxPageShowOfNum.setValue(allRowCount);

        } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE D
            if ((cMsg.D.length() == 0) || (sMsg.D.length() == 0) || (sMsg.D.getValidCount() <= 0)) {
                cMsg.xxPageShowFromNum.setValue(0);
                cMsg.xxPageShowToNum.setValue(0);
                cMsg.xxPageShowOfNum.setValue(0);
                return;
            }
            int startRowCount = 0;
            if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
                startRowCount = cMsg.xxPageShowFromNum.getValueInt();
            }
            int allRowCount = sMsg.D.getValidCount();
            if (startRowCount == 0) {
                cMsg.xxPageShowFromNum.setValue(1);
            } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {
                // last page
                cMsg.xxPageShowFromNum.setValue(getLastPageStartCount(allRowCount, cMsg.D.length()));
            } else {
                if ((startRowCount % cMsg.D.length()) != 1) {
                    startRowCount = startRowCount - (startRowCount % cMsg.D.length()) + 1;
                }
                cMsg.xxPageShowFromNum.setValue(startRowCount);
            }
            if ((cMsg.xxPageShowFromNum.getValueInt() + cMsg.D.length() - 1) < allRowCount) {
                cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.D.length() - 1);
            } else {
                cMsg.xxPageShowToNum.setValue(allRowCount);
            }
            cMsg.xxPageShowOfNum.setValue(allRowCount);

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE E
            if ((cMsg.E.length() == 0) || (sMsg.E.length() == 0) || (sMsg.E.getValidCount() <= 0)) {
                cMsg.xxPageShowFromNum.setValue(0);
                cMsg.xxPageShowToNum.setValue(0);
                cMsg.xxPageShowOfNum.setValue(0);
                return;
            }
            int startRowCount = 0;
            if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
                startRowCount = cMsg.xxPageShowFromNum.getValueInt();
            }
            int allRowCount = sMsg.E.getValidCount();
            if (startRowCount == 0) {
                cMsg.xxPageShowFromNum.setValue(1);
            } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {
                // last page
                cMsg.xxPageShowFromNum.setValue(getLastPageStartCount(allRowCount, cMsg.E.length()));
            } else {
                if ((startRowCount % cMsg.E.length()) != 1) {
                    startRowCount = startRowCount - (startRowCount % cMsg.E.length()) + 1;
                }
                cMsg.xxPageShowFromNum.setValue(startRowCount);
            }
            if ((cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1) < allRowCount) {
                cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.E.length() - 1);
            } else {
                cMsg.xxPageShowToNum.setValue(allRowCount);
            }
            cMsg.xxPageShowOfNum.setValue(allRowCount);

        }

        // START 2023/05/17 T.Kuroda [QC#61211, ADD]
        else if (DISPLAY_TAB_NM_TECHMIN.equals(cMsg.xxDplyTab.getValue())) {
            // TABLE L
            if ((cMsg.L.length() == 0) || (sMsg.L.length() == 0) || (sMsg.L.getValidCount() <= 0)) {
                cMsg.xxPageShowFromNum.setValue(0);
                cMsg.xxPageShowToNum.setValue(0);
                cMsg.xxPageShowOfNum.setValue(0);
                return;
            }
            int startRowCount = 0;
            if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
                startRowCount = cMsg.xxPageShowFromNum.getValueInt();
            }
            int allRowCount = sMsg.L.getValidCount();
            if (startRowCount == 0) {
                cMsg.xxPageShowFromNum.setValue(1);
            } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {
                // last page
                cMsg.xxPageShowFromNum.setValue(getLastPageStartCount(allRowCount, cMsg.L.length()));
            } else {
                if ((startRowCount % cMsg.L.length()) != 1) {
                    startRowCount = startRowCount - (startRowCount % cMsg.L.length()) + 1;
                }
                cMsg.xxPageShowFromNum.setValue(startRowCount);
            }
            if ((cMsg.xxPageShowFromNum.getValueInt() + cMsg.L.length() - 1) < allRowCount) {
                cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.L.length() - 1);
            } else {
                cMsg.xxPageShowToNum.setValue(allRowCount);
            }
            cMsg.xxPageShowOfNum.setValue(allRowCount);

        }
        // END   2023/05/17 T.Kuroda [QC#61211, ADD]

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
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param addIndex int
     */
    public static void addNewLineA(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int addIndex) {
        if (sMsg.A.length() < sMsg.xxPageShowOfNum.getValueInt() + 1) {
            cMsg.setMessageInfo(NPAM0077E);
            return;
        }

        // add new line
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).apvlTeamNm_A1, "");
        setHierarchyType(glblCmpyCd, sMsg.A.no(addIndex));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).apvlHrchTpCd_AS, "");
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addIndex).apvlTeamDescTxt_A1, "");

        cMsg.xxPageShowToNum.setValue(addIndex + 1);
        cMsg.xxPageShowOfNum.setValue(addIndex + 1);
        sMsg.A.setValidCount(addIndex + 1);

    }

    /**
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void copyLineA(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        cMsg.xxCellIdx.clear();

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A1", CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NPAM0049E);
            return;
        }

        // over display Size
        int nowRowSize = sMsg.A.getValidCount();
        int maxCnt = sMsg.A.length();
        int selectedRowSize = selectedRows.size();
        int newRowNum = nowRowSize + selectedRowSize;
        if (newRowNum > maxCnt) {
            cMsg.setMessageInfo(NPAM1199E);
            return;
        }

        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        // set newRowNum
        sMsg.A.setValidCount(newRowNum);

        int r = nowRowSize;
        for (int i = 0; i < selectedRowSize; i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(r).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(r).apvlTeamPk_A1, sMsg.A.no(selectedRows.get(i)).apvlTeamPk_A1.getValue());
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(r).apvlTeamNm_A1, sMsg.A.no(selectedRows.get(i)).apvlTeamNm_A1.getValue());
            setHierarchyType(glblCmpyCd, sMsg.A.no(r));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(r).apvlHrchTpCd_AS, "");
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(r).apvlTeamDescTxt_A1, "");
            r++;
        }

        ZYPTableUtil.clear(cMsg.A);

        for (int f = 0; f < sMsg.A.getValidCount(); f++) {
            sMsg.A.no(f).xxChkBox_A1.clear();
        }

        int copyFirstPage = (int) Math.ceil((double) (nowRowSize + 1) / (double) cMsg.A.length());
        int copyPageFrom = copyFirstPage * cMsg.A.length() - cMsg.A.length();

        cMsg.xxPageShowFromNum.setValue(copyPageFrom + 1);
        cMsg.xxPageShowToNum.clear();

        NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

        cMsg.xxCellIdx.setValue(selectedRowSize);
        NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

    }

    /**
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param addIndex int
     */
    public static void addNewLineB(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int addIndex) {
        if (sMsg.B.length() < sMsg.xxPageShowOfNum.getValueInt() + 1) {
            cMsg.setMessageInfo(NPAM0077E);
            return;
        }

        // add new line
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(addIndex).xxChkBox_B1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(addIndex).apvlTeamPk_B1, sMsg.apvlTeamPk_HD);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(addIndex).apvlTeamNm_B1, cMsg.apvlTeamNm_MT);
        setPosition(glblCmpyCd, sMsg.B.no(addIndex));
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(addIndex).apvlTeamPosnTpCd_BS, "");
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(addIndex).psnCd_B1, "");
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(addIndex).fullPsnNm_B1, "");

        cMsg.xxPageShowToNum.setValue(addIndex + 1);
        cMsg.xxPageShowOfNum.setValue(addIndex + 1);
        sMsg.B.setValidCount(addIndex + 1);
    }

    /**
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void copyLineB(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        sMsg.B.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        cMsg.xxCellIdx.clear();

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.B, "xxChkBox_B1", CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NPAM0049E);
            return;
        }

        // over display Size
        int nowRowSize = sMsg.B.getValidCount();
        int maxCnt = sMsg.B.length();
        int selectedRowSize = selectedRows.size();
        int newRowNum = nowRowSize + selectedRowSize;
        if (newRowNum > maxCnt) {
            cMsg.setMessageInfo(NPAM1199E);
            return;
        }

        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        // set newRowNum
        sMsg.B.setValidCount(newRowNum);

        int r = nowRowSize;
        for (int i = 0; i < selectedRowSize; i++) {

            ZYPEZDItemValueSetter.setValue(sMsg.B.no(r).xxChkBox_B1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(r).apvlTeamPk_B1, sMsg.B.no(selectedRows.get(i)).apvlTeamPk_B1.getValue());
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(r).apvlTeamNm_B1, sMsg.B.no(selectedRows.get(i)).apvlTeamNm_B1.getValue());
            setPosition(glblCmpyCd, sMsg.B.no(r));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(r).apvlTeamPosnTpCd_BS, "");
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(r).psnCd_B1, "");
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(r).fullPsnNm_B1, "");
            r++;

        }

        ZYPTableUtil.clear(cMsg.B);

        for (int f = 0; f < sMsg.B.getValidCount(); f++) {
            sMsg.B.no(f).xxChkBox_B1.clear();
        }

        int copyFirstPage = (int) Math.ceil((double) (nowRowSize + 1) / (double) cMsg.B.length());
        int copyPageFrom = copyFirstPage * cMsg.B.length() - cMsg.B.length();

        cMsg.xxPageShowFromNum.setValue(copyPageFrom + 1);
        cMsg.xxPageShowToNum.clear();

        NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        cMsg.xxPageShowOfNum.setValue(sMsg.B.getValidCount());

        cMsg.xxCellIdx.setValue(selectedRowSize);
        NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

    }

    /**
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param addIndex int
     */
    public static void addNewLineC(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int addIndex) {
        if (sMsg.C.length() < sMsg.xxPageShowOfNum.getValueInt() + 1) {
            cMsg.setMessageInfo(NPAM0077E);
            return;
        }

        // add new line
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(addIndex).xxChkBox_C1, ZYPConstant.FLG_OFF_N);
        setPartsMERCH(glblCmpyCd, sMsg.C.no(addIndex));
        setTransaction(glblCmpyCd, sMsg.C.no(addIndex));

        ZYPEZDItemValueSetter.setValue(sMsg.C.no(addIndex).apvlTeamPk_C1, sMsg.apvlTeamPk_HD);
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(addIndex).apvlTeamNm_C1, cMsg.apvlTeamNm_ST);
        setPlanningGroup(glblCmpyCd, sMsg.C.no(addIndex));
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(addIndex).prchGrpCd_CS, "");
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(addIndex).mdseItemTpCd_CS, "");
        ZYPEZDItemValueSetter.setValue(sMsg.C.no(addIndex).apvlHistSrcTpCd_CS, "");

        cMsg.xxPageShowToNum.setValue(addIndex + 1);
        cMsg.xxPageShowOfNum.setValue(addIndex + 1);
        sMsg.C.setValidCount(addIndex + 1);
    }

    /**
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void copyLineC(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        sMsg.C.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        cMsg.xxCellIdx.clear();

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.C, "xxChkBox_C1", CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NPAM0049E);
            return;
        }

        // over display Size
        int nowRowSize = sMsg.C.getValidCount();
        int maxCnt = sMsg.C.length();
        int selectedRowSize = selectedRows.size();
        int newRowNum = nowRowSize + selectedRowSize;
        if (newRowNum > maxCnt) {
            cMsg.setMessageInfo(NPAM1199E);
            return;
        }

        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        // set newRowNum
        sMsg.C.setValidCount(newRowNum);

        int r = nowRowSize;
        for (int i = 0; i < selectedRowSize; i++) {

            ZYPEZDItemValueSetter.setValue(sMsg.C.no(r).xxChkBox_C1, ZYPConstant.FLG_OFF_N);
            setPartsMERCH(glblCmpyCd, sMsg.C.no(r));
            setTransaction(glblCmpyCd, sMsg.C.no(r));

            ZYPEZDItemValueSetter.setValue(sMsg.C.no(r).apvlTeamPk_C1, sMsg.C.no(selectedRows.get(i)).apvlTeamPk_C1.getValue());
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(r).apvlTeamNm_C1, sMsg.C.no(selectedRows.get(i)).apvlTeamNm_C1.getValue());
            setPlanningGroup(glblCmpyCd, sMsg.C.no(r));
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(r).prchGrpCd_CS, "");
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(r).mdseItemTpCd_CS, "");
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(r).apvlHistSrcTpCd_CS, "");
            r++;

        }

        ZYPTableUtil.clear(cMsg.C);

        for (int f = 0; f < sMsg.C.getValidCount(); f++) {
            sMsg.C.no(f).xxChkBox_C1.clear();
        }

        int copyFirstPage = (int) Math.ceil((double) (nowRowSize + 1) / (double) cMsg.C.length());
        int copyPageFrom = copyFirstPage * cMsg.C.length() - cMsg.C.length();

        cMsg.xxPageShowFromNum.setValue(copyPageFrom + 1);
        cMsg.xxPageShowToNum.clear();

        NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        cMsg.xxPageShowOfNum.setValue(sMsg.C.getValidCount());

        cMsg.xxCellIdx.setValue(selectedRowSize);
        NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

    }

    /**
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param addIndex int
     */
    public static void addNewLineD(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int addIndex) {
        if (sMsg.D.length() < sMsg.xxPageShowOfNum.getValueInt() + 1) {
            cMsg.setMessageInfo(NPAM0077E);
            return;
        }

        // add new line
        ZYPEZDItemValueSetter.setValue(sMsg.D.no(addIndex).xxChkBox_D1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(sMsg.D.no(addIndex).apvlTeamPk_D1, sMsg.apvlTeamPk_HD);
        ZYPEZDItemValueSetter.setValue(sMsg.D.no(addIndex).apvlTeamNm_D1, cMsg.apvlTeamNm_LT);
        ZYPEZDItemValueSetter.setValue(sMsg.D.no(addIndex).rtlWhCd_D1, "");
        ZYPEZDItemValueSetter.setValue(sMsg.D.no(addIndex).rtlWhNm_D1, (String) "");

        cMsg.xxPageShowToNum.setValue(addIndex + 1);
        cMsg.xxPageShowOfNum.setValue(addIndex + 1);
        sMsg.D.setValidCount(addIndex + 1);
    }

    /**
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void copyLineD(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {
        sMsg.D.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        cMsg.xxCellIdx.clear();

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.D, "xxChkBox_D1", CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NPAM0049E);
            return;
        }

        // over display Size
        int nowRowSize = sMsg.D.getValidCount();
        int maxCnt = sMsg.D.length();
        int selectedRowSize = selectedRows.size();
        int newRowNum = nowRowSize + selectedRowSize;
        if (newRowNum > maxCnt) {
            cMsg.setMessageInfo(NPAM1199E);
            return;
        }

        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        // set newRowNum
        sMsg.D.setValidCount(newRowNum);

        int r = nowRowSize;
        for (int i = 0; i < selectedRowSize; i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.D.no(r).xxChkBox_D1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsg.D.no(r).apvlTeamPk_D1, sMsg.D.no(selectedRows.get(i)).apvlTeamPk_D1.getValue());
            ZYPEZDItemValueSetter.setValue(sMsg.D.no(r).apvlTeamNm_D1, sMsg.D.no(selectedRows.get(i)).apvlTeamNm_D1.getValue());
            ZYPEZDItemValueSetter.setValue(sMsg.D.no(r).rtlWhCd_D1, "");
            ZYPEZDItemValueSetter.setValue(sMsg.D.no(r).rtlWhNm_D1, (String) "");
            r++;
        }

        ZYPTableUtil.clear(cMsg.D);

        for (int f = 0; f < sMsg.D.getValidCount(); f++) {
            sMsg.D.no(f).xxChkBox_D1.clear();
        }

        int copyFirstPage = (int) Math.ceil((double) (nowRowSize + 1) / (double) cMsg.D.length());
        int copyPageFrom = copyFirstPage * cMsg.D.length() - cMsg.D.length();

        cMsg.xxPageShowFromNum.setValue(copyPageFrom + 1);
        cMsg.xxPageShowToNum.clear();

        NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        cMsg.xxPageShowOfNum.setValue(sMsg.D.getValidCount());

        cMsg.xxCellIdx.setValue(selectedRowSize);
        NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

    }

    /**
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param addIndex int
     */
    public static void addNewLineE(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int addIndex) {
        if (sMsg.E.length() < sMsg.xxPageShowOfNum.getValueInt() + 1) {
            cMsg.setMessageInfo(NPAM0077E);
            return;
        }

        // add new line
        ZYPEZDItemValueSetter.setValue(sMsg.E.no(addIndex).xxChkBox_E1, ZYPConstant.FLG_OFF_N);

        setHierarchyTypeByAL(glblCmpyCd, sMsg.E.no(addIndex));
        ZYPEZDItemValueSetter.setValue(sMsg.E.no(addIndex).apvlHrchTpCd_ES, "");

        setPositionByAL(glblCmpyCd, sMsg.E.no(addIndex));
        ZYPEZDItemValueSetter.setValue(sMsg.E.no(addIndex).apvlTeamPosnTpCd_ES, "");

        setPlanningGroupByAL(glblCmpyCd, sMsg.E.no(addIndex));
        ZYPEZDItemValueSetter.setValue(sMsg.E.no(addIndex).prchGrpCd_ES, "");

        setTransactionByAL(glblCmpyCd, sMsg.E.no(addIndex));
        ZYPEZDItemValueSetter.setValue(sMsg.E.no(addIndex).apvlHistSrcTpCd_ES, "");
        ZYPEZDItemValueSetter.setValue(sMsg.E.no(addIndex).psnCd_E1, "");
        ZYPEZDItemValueSetter.setValue(sMsg.E.no(addIndex).fullPsnNm_E1, "");

        cMsg.xxPageShowToNum.setValue(addIndex + 1);
        cMsg.xxPageShowOfNum.setValue(addIndex + 1);
        sMsg.E.setValidCount(addIndex + 1);
    }

    /**
     * copyLineE
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void copyLineE(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {
        sMsg.E.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        cMsg.xxCellIdx.clear();

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.E, "xxChkBox_E1", CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NPAM0049E);
            return;
        }

        // over display Size
        int nowRowSize = sMsg.E.getValidCount();
        int maxCnt = sMsg.E.length();
        int selectedRowSize = selectedRows.size();
        int newRowNum = nowRowSize + selectedRowSize;
        if (newRowNum > maxCnt) {
            cMsg.setMessageInfo(NPAM1199E);
            return;
        }

        NPAL1160CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        // START 2023/08/29 M.Kikushima [QC#61590, ADD]
        List<Map<String, String>> reqTypeListForIR = getRequestType(glblCmpyCd, APVL_HIST_SRC_TP.INVENTORY_REQUEST);
        List<Map<String, String>> reqTypeListForPR = getRequestType(glblCmpyCd, APVL_HIST_SRC_TP.PO_REQUISITION);
        // END 2023/08/29 M.Kikushima [QC#61590, ADD]

        // set newRowNum
        sMsg.E.setValidCount(newRowNum);

        int r = nowRowSize;
        for (int i = 0; i < selectedRowSize; i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.E.no(r).xxChkBox_E1, ZYPConstant.FLG_OFF_N);
            setHierarchyTypeByAL(glblCmpyCd, sMsg.E.no(r));
            ZYPEZDItemValueSetter.setValue(sMsg.E.no(r).apvlHrchTpCd_ES, sMsg.E.no(selectedRows.get(i)).apvlHrchTpCd_ES.getValue());
            setPositionByAL(glblCmpyCd, sMsg.E.no(r));
            ZYPEZDItemValueSetter.setValue(sMsg.E.no(r).apvlTeamPosnTpCd_ES, sMsg.E.no(selectedRows.get(i)).apvlTeamPosnTpCd_ES.getValue());
            setPlanningGroupByAL(glblCmpyCd, sMsg.E.no(r));
            ZYPEZDItemValueSetter.setValue(sMsg.E.no(r).prchGrpCd_ES, sMsg.E.no(selectedRows.get(i)).prchGrpCd_ES.getValue());
            setTransactionByAL(glblCmpyCd, sMsg.E.no(r));
            ZYPEZDItemValueSetter.setValue(sMsg.E.no(r).apvlHistSrcTpCd_ES, sMsg.E.no(selectedRows.get(i)).apvlHistSrcTpCd_ES.getValue());

            // START 2023/08/29 M.Kikushima [QC#61590, ADD]
            if(APVL_HIST_SRC_TP.INVENTORY_REQUEST.equals(sMsg.E.no(r).apvlHistSrcTpCd_ES.getValue())
                    || APVL_HIST_SRC_TP.PO_REQUISITION.equals(sMsg.E.no(r).apvlHistSrcTpCd_ES.getValue())) {
                if(APVL_HIST_SRC_TP.INVENTORY_REQUEST.equals(sMsg.E.no(r).apvlHistSrcTpCd_ES.getValue())) {
                    setRequestTypeByAL(glblCmpyCd, sMsg.E.no(r),reqTypeListForIR);
                } else if(APVL_HIST_SRC_TP.PO_REQUISITION.equals(sMsg.E.no(r).apvlHistSrcTpCd_ES.getValue())){
                    setRequestTypeByAL(glblCmpyCd, sMsg.E.no(r),reqTypeListForPR);
                }
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(r).prchReqTpCd_ES, sMsg.E.no(selectedRows.get(i)).prchReqTpCd_ES.getValue());          
            }
            // END 2023/08/29 M.Kikushima [QC#61590, ADD]

            ZYPEZDItemValueSetter.setValue(sMsg.E.no(r).apvlLimitAmt_E1, sMsg.E.no(selectedRows.get(i)).apvlLimitAmt_E1.getValue());
            r++;

        }

        ZYPTableUtil.clear(cMsg.E);

        for (int f = 0; f < sMsg.E.getValidCount(); f++) {
            sMsg.E.no(f).xxChkBox_E1.clear();
        }

        int copyFirstPage = (int) Math.ceil((double) (nowRowSize + 1) / (double) cMsg.E.length());
        int copyPageFrom = copyFirstPage * cMsg.E.length() - cMsg.E.length();

        cMsg.xxPageShowFromNum.setValue(copyPageFrom + 1);
        cMsg.xxPageShowToNum.clear();

        NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        cMsg.xxPageShowOfNum.setValue(sMsg.E.getValidCount());

        cMsg.xxCellIdx.setValue(selectedRowSize);
        NPAL1160CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

    }

    /**
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param rowNum int
     */
    public static void overlapCheckMember(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int rowNum) {

        int count = 0;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        S21SsmEZDResult result = NPAL1160Query.getInstance().searchTeamPKByName(ssmParam);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> list = (List) result.getResultObject();

            // Copy 1 page Data(SMsg -> CMsg)
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
            }
        }

        if (sMsg.E.length() < sMsg.xxPageShowOfNum.getValueInt() + 1) {
            cMsg.setMessageInfo(NPAM0077E);
            return;
        }

    }

    /**
     * Search Team PK By Name
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param selTeamNm String
     * @return BigDecimal
     */
    public static BigDecimal searchTeamPKByName(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, String selTeamNm) {

        BigDecimal temaPK = BigDecimal.ZERO;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_APVL_TEAM_NM, selTeamNm);

        S21SsmEZDResult result = NPAL1160Query.getInstance().searchTeamPKByName(ssmParam);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> list = (List) result.getResultObject();

            // Copy 1 page Data(SMsg -> CMsg)
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                temaPK = (BigDecimal) map.get("APVL_TEAM_PK");
            }
        }
        return temaPK;
    }

    /**
     * Delete Team Check
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param selTeamPk BigDecimal
     * @return boolean
     */
    public static boolean deleteTeamCheck(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, BigDecimal selTeamPk) {

        boolean useFlg = false;

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_APVL_TEAM_PK, selTeamPk);

        int count = 0;

        // Team Member Check
        BigDecimal resultMember = NPAL1160Query.getInstance().checkMemberByTeamPK(ssmParam);
        count = count + resultMember.intValue();

        // Team Transaction Check
        if (count == 0) {
            BigDecimal countTrx = NPAL1160Query.getInstance().checkTransactionByTeamPK(ssmParam);
            count = count + countTrx.intValue();
        } else {
            useFlg = true;
            return useFlg;
        }
        // Team Location Check
        if (count == 0) {
            BigDecimal countLoc = NPAL1160Query.getInstance().checkLocationByTeamPK(ssmParam);
            count = count + countLoc.intValue();
        } else {
            useFlg = true;
            return useFlg;
        }

        if (count > 0) {
            useFlg = true;
            return useFlg;
        }

        return useFlg;
    }

    /**
     * overlap check For Member
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param rowNum int
     * @return int
     */
    public static int overlapCheckForMember(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int rowNum) {

        int count = 0;
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_APVL_TEAM_PK, sMsg.B.no(rowNum).apvlTeamPk_B1.getValue());
        ssmParam.put(DB_PARAM_APVL_TEAM_POSN_TP, sMsg.B.no(rowNum).apvlTeamPosnTpCd_BS.getValue());
        ssmParam.put(DB_PARAM_PSN_CD, sMsg.B.no(rowNum).psnCd_B1.getValue());

        BigDecimal result = NPAL1160Query.getInstance().overlapCheckForMember(ssmParam);
        count = count + result.intValue();

        return count;
    }

    /**
     * overlap check For Transaction
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param rowNum int
     * @return int
     */
    public static int overlapCheckForTransaction(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int rowNum) {

        int count = 0;
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_APVL_TEAM_PK, sMsg.C.no(rowNum).apvlTeamPk_C1.getValue());
        if (ZYPCommonFunc.hasValue(sMsg.C.no(rowNum).prchGrpCd_CS)) {
            ssmParam.put(DB_PARAM_PRCH_GRP_CD, sMsg.C.no(rowNum).prchGrpCd_CS.getValue());
        }
        if (ZYPCommonFunc.hasValue(sMsg.C.no(rowNum).mdseItemTpCd_CS)) {
            ssmParam.put(DB_PARAM_MDSE_ITEM_TP_CD, sMsg.C.no(rowNum).mdseItemTpCd_CS.getValue());
        }
        ssmParam.put(DB_PARAM_APVL_HIST_SRC_TP_CD, sMsg.C.no(rowNum).apvlHistSrcTpCd_CS.getValue());

        BigDecimal result = NPAL1160Query.getInstance().overlapCheckForTransaction(ssmParam);
        count = count + result.intValue();

        return count;
    }

    /**
     * overlap check For Location
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param rowNum int
     * @return int
     */
    public static int overlapCheckForLocation(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int rowNum) {

        int count = 0;
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_APVL_TEAM_PK, sMsg.D.no(rowNum).apvlTeamPk_D1.getValue());
        ssmParam.put(DB_PARAM_RTL_WH_CD, sMsg.D.no(rowNum).rtlWhCd_D1.getValue());

        BigDecimal result = NPAL1160Query.getInstance().overlapCheckForLocation(ssmParam);
        count = count + result.intValue();

        return count;
    }

    /**
     * overlap check For ApvlLimit
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param rowNum int
     * @return int
     */
    public static int overlapCheckForApvlLimit(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int rowNum) {

        int count = 0;
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_APVL_HRCH_TP_CD, sMsg.E.no(rowNum).apvlHrchTpCd_ES.getValue());
        ssmParam.put(DB_PARAM_APVL_TEAM_POSN_TP, sMsg.E.no(rowNum).apvlTeamPosnTpCd_ES.getValue());
        ssmParam.put(DB_PARAM_PSN_CD, sMsg.E.no(rowNum).psnCd_E1.getValue());
        ssmParam.put(DB_PARAM_PRCH_GRP_CD, sMsg.E.no(rowNum).prchGrpCd_ES.getValue());
        ssmParam.put(DB_PARAM_APVL_HIST_SRC_TP_CD, sMsg.E.no(rowNum).apvlHistSrcTpCd_ES.getValue());
        // START 2023/08/29 M.Kikushima [QC#61590, ADD]
        ssmParam.put(DB_PARAM_PRCH_REQ_TP_CD, sMsg.E.no(rowNum).prchReqTpCd_ES.getValue());
        // END 2023/08/29 M.Kikushima [QC#61590, ADD]

        BigDecimal result = NPAL1160Query.getInstance().overlapCheckForApvlLimit(ssmParam);
        count = count + result.intValue();

        return count;
    }

    /**
     * Insert Team
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param rowNum int
     * @param newTeamPK Insert Team
     */
    public static void insertTeam(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int rowNum, BigDecimal newTeamPK) {

        APVL_TEAMTMsg inMsg = new APVL_TEAMTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.apvlTeamPk, newTeamPK);
        ZYPEZDItemValueSetter.setValue(inMsg.apvlTeamNm, cMsg.A.no(rowNum).apvlTeamNm_A1);
        ZYPEZDItemValueSetter.setValue(inMsg.apvlTeamDescTxt, cMsg.A.no(rowNum).apvlTeamDescTxt_A1);
        ZYPEZDItemValueSetter.setValue(inMsg.apvlHrchTpCd, cMsg.A.no(rowNum).apvlHrchTpCd_AS);

        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);

        S21FastTBLAccessor.insert(inMsg);

    }

    /**
     * Insert Member
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param rowNum int
     * @param newMemberPK BigDecimal
     */
    public static void insertMember(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int rowNum, BigDecimal newMemberPK) {

        APVL_TEAM_MBRTMsg inMsg = new APVL_TEAM_MBRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.apvlTeamMbrPk, newMemberPK);
        ZYPEZDItemValueSetter.setValue(inMsg.apvlTeamPk, cMsg.B.no(rowNum).apvlTeamPk_B1);
        ZYPEZDItemValueSetter.setValue(inMsg.apvlTeamPosnTpCd, cMsg.B.no(rowNum).apvlTeamPosnTpCd_BS);
        ZYPEZDItemValueSetter.setValue(inMsg.psnCd, cMsg.B.no(rowNum).psnCd_B1);

        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);

        S21FastTBLAccessor.insert(inMsg);

    }

    /**
     * Insert Transaction
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param rowNum int
     * @param newTrxPK BigDecimal
     */
    public static void insertTransaction(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int rowNum, BigDecimal newTrxPK) {

        APVL_TEAM_TRXTMsg inMsg = new APVL_TEAM_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.apvlTeamTrxPk, newTrxPK);
        ZYPEZDItemValueSetter.setValue(inMsg.apvlTeamPk, cMsg.C.no(rowNum).apvlTeamPk_C1);
        ZYPEZDItemValueSetter.setValue(inMsg.prchGrpCd, cMsg.C.no(rowNum).prchGrpCd_CS);
        ZYPEZDItemValueSetter.setValue(inMsg.poMdseTpCd, cMsg.C.no(rowNum).mdseItemTpCd_CS);
        ZYPEZDItemValueSetter.setValue(inMsg.apvlHistSrcTpCd, cMsg.C.no(rowNum).apvlHistSrcTpCd_CS);

        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);

        S21FastTBLAccessor.insert(inMsg);

    }

    /**
     * Insert Location
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param rowNum int
     * @param newLocPK BigDecimal
     */
    public static void insertLocation(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int rowNum, BigDecimal newLocPK) {

        APVL_TEAM_LOCTMsg inMsg = new APVL_TEAM_LOCTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.apvlTeamLocPk, newLocPK);
        ZYPEZDItemValueSetter.setValue(inMsg.apvlTeamPk, cMsg.D.no(rowNum).apvlTeamPk_D1);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, cMsg.D.no(rowNum).rtlWhCd_D1);

        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);

        S21FastTBLAccessor.insert(inMsg);

    }

    /**
     * Insert ApvlLimit
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @param rowNum int
     * @param newLimitPK BigDecimal
     */
    public static void insertApvlLimit(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd, int rowNum, BigDecimal newLimitPK) {

        APVL_LIMITTMsg inMsg = new APVL_LIMITTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.apvlLimitPk, newLimitPK);
        ZYPEZDItemValueSetter.setValue(inMsg.apvlHrchTpCd, cMsg.E.no(rowNum).apvlHrchTpCd_ES);
        ZYPEZDItemValueSetter.setValue(inMsg.apvlTeamPosnTpCd, cMsg.E.no(rowNum).apvlTeamPosnTpCd_ES);
        ZYPEZDItemValueSetter.setValue(inMsg.psnCd, cMsg.E.no(rowNum).psnCd_E1);
        ZYPEZDItemValueSetter.setValue(inMsg.prchGrpCd, cMsg.E.no(rowNum).prchGrpCd_ES);
        ZYPEZDItemValueSetter.setValue(inMsg.apvlHistSrcTpCd, cMsg.E.no(rowNum).apvlHistSrcTpCd_ES);
        ZYPEZDItemValueSetter.setValue(inMsg.apvlLimitAmt, cMsg.E.no(rowNum).apvlLimitAmt_E1);

        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);

        S21FastTBLAccessor.insert(inMsg);

    }

    /**
     * deleteLineA
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void deleteLineA(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(i).apvlTeamPk_A1)) {
                boolean useFlg = deleteTeamCheck(cMsg, sMsg, glblCmpyCd, sMsg.A.no(i).apvlTeamPk_A1.getValue());
                if (useFlg) {
                    cMsg.setMessageInfo(NMAM0074E, new String[] {NMAM0074E_PARAM1, NMAM0074E_PARAM2 });
                    return;
                }
            }
        }

        List<NPAL1160_ASMsg> remain = new ArrayList<NPAL1160_ASMsg>();

        List<NPAL1160_ASMsg> deleted = new ArrayList<NPAL1160_ASMsg>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // need to cut reference from sMsg.A
            NPAL1160_ASMsg clone = new NPAL1160_ASMsg();

            EZDMsg.copy(sMsg.A.no(i), null, clone, null);

            // checked only
            if (ZYPConstant.CHKBOX_ON_Y.equals(clone.xxChkBox_A1.getValue())) {
                // not new line
                if (!ZYPConstant.FLG_ON_1.equals(clone.deleteFlagIf_A1.getValue())) {
                    deleted.add(clone);
                }
            } else {
                remain.add(clone);
            }
        }

        // re-make sMsg to exclude checked lines
        for (int i = 0; i < remain.size(); i++) {

            EZDMsg.copy((EZDMsg) remain.get(i), null, sMsg.A.get(i), null);
        }
        sMsg.A.setValidCount(remain.size());

        int indx = sMsg.F.getValidCount();
        // keep checked message as F (Deleted)
        for (int i = 0; i < deleted.size(); i++) {

            EZDMsg.copy((EZDMsg) deleted.get(i), null, sMsg.F.get(indx + i), null);

            // set message to reminder lines are not deleted yet.
            cMsg.setMessageInfo(NPAM1237W);
            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        }
        sMsg.F.setValidCount(deleted.size() + indx);

        // copy from s to c
        cMsg.A.clear();
        for (int i = 0; i < cMsg.A.length(); i++) {
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(sMsg.A.getValidCount());
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(cMsg.xxPageShowOfNum.getValueInt() - deleted.size());

    }

    /**
     * deleteLineB
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    public static void deleteLineB(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        List<NPAL1160_BSMsg> remain = new ArrayList<NPAL1160_BSMsg>();

        List<NPAL1160_BSMsg> deleted = new ArrayList<NPAL1160_BSMsg>();
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            // need to cut reference from sMsg.B
            NPAL1160_BSMsg clone = new NPAL1160_BSMsg();

            EZDMsg.copy(sMsg.B.no(i), null, clone, null);

            // checked only
            if (ZYPConstant.CHKBOX_ON_Y.equals(clone.xxChkBox_B1.getValue())) {
                // not new line
                if (!ZYPConstant.FLG_ON_1.equals(clone.deleteFlagIf_B1.getValue())) {
                    deleted.add(clone);
                }
            } else {
                remain.add(clone);
            }
        }

        // re-make sMsg to exclude checked lines
        for (int i = 0; i < remain.size(); i++) {

            EZDMsg.copy((EZDMsg) remain.get(i), null, sMsg.B.get(i), null);
        }
        sMsg.B.setValidCount(remain.size());

        int indx = sMsg.G.getValidCount();
        // keep checked message as F (Deleted)
        for (int i = 0; i < deleted.size(); i++) {

            EZDMsg.copy((EZDMsg) deleted.get(i), null, sMsg.G.get(indx + i), null);

            // set message to reminder lines are not deleted yet.
            cMsg.setMessageInfo(NPAM1237W);
            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        }
        sMsg.G.setValidCount(deleted.size() + indx);

        // copy from s to c
        cMsg.B.clear();
        for (int i = 0; i < cMsg.B.length(); i++) {
            EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
        }
        cMsg.B.setValidCount(sMsg.B.getValidCount());
        cMsg.xxPageShowToNum.setValue(cMsg.B.getValidCount());
        cMsg.xxPageShowOfNum.setValue(cMsg.xxPageShowOfNum.getValueInt() - deleted.size());
    }

    /**
     * deleteLineC
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    public static void deleteLineC(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        List<NPAL1160_CSMsg> remain = new ArrayList<NPAL1160_CSMsg>();

        List<NPAL1160_CSMsg> deleted = new ArrayList<NPAL1160_CSMsg>();
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            // need to cut reference from sMsg.B
            NPAL1160_CSMsg clone = new NPAL1160_CSMsg();

            EZDMsg.copy(sMsg.C.no(i), null, clone, null);

            // checked only
            if (ZYPConstant.CHKBOX_ON_Y.equals(clone.xxChkBox_C1.getValue())) {
                // not new line
                if (!ZYPConstant.FLG_ON_1.equals(clone.deleteFlagIf_C1.getValue())) {
                    deleted.add(clone);
                }
            } else {
                remain.add(clone);
            }
        }

        // re-make sMsg to exclude checked lines
        for (int i = 0; i < remain.size(); i++) {

            EZDMsg.copy((EZDMsg) remain.get(i), null, sMsg.C.get(i), null);
        }
        sMsg.C.setValidCount(remain.size());

        int indx = sMsg.H.getValidCount();
        // keep checked message as F (Deleted)
        for (int i = 0; i < deleted.size(); i++) {

            EZDMsg.copy((EZDMsg) deleted.get(i), null, sMsg.H.get(indx + i), null);

            // set message to reminder lines are not deleted yet.
            cMsg.setMessageInfo(NPAM1237W);
            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        }
        sMsg.H.setValidCount(deleted.size() + indx);

        // copy from s to c
        cMsg.C.clear();
        for (int i = 0; i < cMsg.C.length(); i++) {
            EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i), null);
        }
        cMsg.C.setValidCount(sMsg.C.getValidCount());
        cMsg.xxPageShowToNum.setValue(cMsg.C.getValidCount());
        cMsg.xxPageShowOfNum.setValue(cMsg.xxPageShowOfNum.getValueInt() - deleted.size());
    }

    /**
     * deleteLineD
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    public static void deleteLineD(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        List<NPAL1160_DSMsg> remain = new ArrayList<NPAL1160_DSMsg>();

        List<NPAL1160_DSMsg> deleted = new ArrayList<NPAL1160_DSMsg>();
        for (int i = 0; i < sMsg.D.getValidCount(); i++) {
            // need to cut reference from sMsg.B
            NPAL1160_DSMsg clone = new NPAL1160_DSMsg();

            EZDMsg.copy(sMsg.D.no(i), null, clone, null);

            // checked only
            if (ZYPConstant.CHKBOX_ON_Y.equals(clone.xxChkBox_D1.getValue())) {
                // not new line
                if (!ZYPConstant.FLG_ON_1.equals(clone.deleteFlagIf_D1.getValue())) {
                    deleted.add(clone);
                }
            } else {
                remain.add(clone);
            }
        }

        // re-make sMsg to exclude checked lines
        for (int i = 0; i < remain.size(); i++) {

            EZDMsg.copy((EZDMsg) remain.get(i), null, sMsg.D.get(i), null);
        }
        sMsg.D.setValidCount(remain.size());

        int indx = sMsg.I.getValidCount();
        // keep checked message as F (Deleted)
        for (int i = 0; i < deleted.size(); i++) {

            EZDMsg.copy((EZDMsg) deleted.get(i), null, sMsg.I.get(indx + i), null);

            // set message to reminder lines are not deleted yet.
            cMsg.setMessageInfo(NPAM1237W);
            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        }
        sMsg.I.setValidCount(deleted.size() + indx);

        // copy from s to c
        cMsg.D.clear();
        for (int i = 0; i < cMsg.D.length(); i++) {
            EZDMsg.copy(sMsg.D.no(i), null, cMsg.D.no(i), null);
        }
        cMsg.D.setValidCount(sMsg.D.getValidCount());
        cMsg.xxPageShowToNum.setValue(cMsg.D.getValidCount());
        cMsg.xxPageShowOfNum.setValue(cMsg.xxPageShowOfNum.getValueInt() - deleted.size());
    }

    /**
     * deleteLineE
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     */
    public static void deleteLineE(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {

        List<NPAL1160_ESMsg> remain = new ArrayList<NPAL1160_ESMsg>();

        List<NPAL1160_ESMsg> deleted = new ArrayList<NPAL1160_ESMsg>();
        for (int i = 0; i < sMsg.E.getValidCount(); i++) {
            // need to cut reference from sMsg.E
            NPAL1160_ESMsg clone = new NPAL1160_ESMsg();

            EZDMsg.copy(sMsg.E.no(i), null, clone, null);

            // checked only
            if (ZYPConstant.CHKBOX_ON_Y.equals(clone.xxChkBox_E1.getValue())) {
                // not new line
                if (!ZYPConstant.FLG_ON_1.equals(clone.deleteFlagIf_E1.getValue())) {
                    deleted.add(clone);
                }
            } else {
                remain.add(clone);
            }
        }

        // re-make sMsg to exclude checked lines
        for (int i = 0; i < remain.size(); i++) {

            EZDMsg.copy((EZDMsg) remain.get(i), null, sMsg.E.get(i), null);
        }
        sMsg.E.setValidCount(remain.size());

        int indx = sMsg.J.getValidCount();
        // keep checked message as F (Deleted)
        for (int i = 0; i < deleted.size(); i++) {

            EZDMsg.copy((EZDMsg) deleted.get(i), null, sMsg.J.get(indx + i), null);

            // set message to reminder lines are not deleted yet.
            cMsg.setMessageInfo(NPAM1237W);
            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        }
        sMsg.J.setValidCount(deleted.size() + indx);

        // copy from s to c
        cMsg.E.clear();
        for (int i = 0; i < cMsg.E.length(); i++) {
            EZDMsg.copy(sMsg.E.no(i), null, cMsg.E.no(i), null);
        }
        cMsg.E.setValidCount(sMsg.E.getValidCount());
        cMsg.xxPageShowToNum.setValue(cMsg.E.getValidCount());
        cMsg.xxPageShowOfNum.setValue(cMsg.xxPageShowOfNum.getValueInt() - deleted.size());
    }

    /**
     * <pre>
     * Write csv file Contract Info
     * @param bizMsg NPAL1160CMsg
     * @param rs     ResultSet
     * @param glblCmpyCd     String
     * @throws SQLException
     * </pre>
     */
    public static void writeCsvFileInfo(NPAL1160CMsg bizMsg, ResultSet rs, String glblCmpyCd) throws SQLException {

        if (DISPLAY_TAB_NM_TEAM.equals(bizMsg.xxDplyTab.getValue())) {

            NPAL1160F02FMsg fMsg = new NPAL1160F02FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

            csvOutFile.writeHeader(CSV_HEADER_INFO_TEAM);

            do {
                if (rs.getRow() > MAX_DOWNLOAD_CNT) {
                    bizMsg.setMessageInfo(NZZM0007E, null);
                    break;
                }

                ZYPEZDItemValueSetter.setValue(fMsg.apvlTeamNm_A, rs.getString("APVL_TEAM_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.apvlTeamDescTxt_A, rs.getString("APVL_TEAM_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.apvlHrchTpCd_A, rs.getString("APVL_HRCH_TP_CD"));

                csvOutFile.write();

            } while (rs.next());
            csvOutFile.close();

        } else if (DISPLAY_TAB_NM_MEMBER.equals(bizMsg.xxDplyTab.getValue())) {

            NPAL1160F00FMsg fMsg = new NPAL1160F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

            csvOutFile.writeHeader(CSV_HEADER_INFO_MBR);

            do {
                if (rs.getRow() > MAX_DOWNLOAD_CNT) {
                    bizMsg.setMessageInfo(NZZM0007E, null);
                    break;
                }

                ZYPEZDItemValueSetter.setValue(fMsg.apvlTeamMbrPk_B, rs.getBigDecimal("APVL_TEAM_MBR_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.apvlTeamPk_B, rs.getBigDecimal("APVL_TEAM_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.apvlTeamPosnTpCd_B, rs.getString("APVL_TEAM_POSN_TP_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.psnCd_B, rs.getString("PSN_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.fullPsnNm_B, rs.getString("FULL_PSN_NM"));

                csvOutFile.write();

            } while (rs.next());
            csvOutFile.close();
        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(bizMsg.xxDplyTab.getValue())) {

            NPAL1160F03FMsg fMsg = new NPAL1160F03FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

            csvOutFile.writeHeader(CSV_HEADER_INFO_TRX);

            do {
                if (rs.getRow() > MAX_DOWNLOAD_CNT) {
                    bizMsg.setMessageInfo(NZZM0007E, null);
                    break;
                }

                ZYPEZDItemValueSetter.setValue(fMsg.apvlTeamNm_C, rs.getString("APVL_TEAM_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prchGrpCd_C, rs.getString("PRCH_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseItemTpCd_C, rs.getString("PO_MDSE_TP_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.apvlHistSrcTpCd_C, rs.getString("APVL_HIST_SRC_TP_CD"));

                csvOutFile.write();

            } while (rs.next());
            csvOutFile.close();

        } else if (DISPLAY_TAB_NM_LOCATION.equals(bizMsg.xxDplyTab.getValue())) {

            NPAL1160F01FMsg fMsg = new NPAL1160F01FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

            csvOutFile.writeHeader(CSV_HEADER_INFO_LOC);

            do {
                if (rs.getRow() > MAX_DOWNLOAD_CNT) {
                    bizMsg.setMessageInfo(NZZM0007E, null);
                    break;
                }

                ZYPEZDItemValueSetter.setValue(fMsg.apvlTeamLocPk_D, rs.getBigDecimal("APVL_TEAM_LOC_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.apvlTeamPk_D, rs.getBigDecimal("APVL_TEAM_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCd_D, rs.getString("RTL_WH_CD"));

                csvOutFile.write();

            } while (rs.next());
            csvOutFile.close();

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(bizMsg.xxDplyTab.getValue())) {

            NPAL1160F04FMsg fMsg = new NPAL1160F04FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

            csvOutFile.writeHeader(CSV_HEADER_INFO_LIMIT);

            do {
                if (rs.getRow() > MAX_DOWNLOAD_CNT) {
                    bizMsg.setMessageInfo(NZZM0007E, null);
                    break;
                }

                ZYPEZDItemValueSetter.setValue(fMsg.apvlHrchTpCd_E, rs.getString("APVL_HRCH_TP_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.apvlTeamPosnTpCd_E, rs.getString("APVL_TEAM_POSN_TP_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.psnCd_E, rs.getString("PSN_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.fullPsnNm_E, rs.getString("FULL_PSN_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prchGrpCd_E, rs.getString("PRCH_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.apvlHistSrcTpCd_E, rs.getString("APVL_HIST_SRC_TP_CD"));
                // START 2023/08/29 M.Kikushima [QC#61590, ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.prchReqTpCd_E, rs.getString("PRCH_REQ_TP_CD"));
                // END 2023/08/29 M.Kikushima [QC#61590, ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.apvlLimitAmt_E, rs.getBigDecimal("APVL_LIMIT_AMT"));

                csvOutFile.write();

            } while (rs.next());
            csvOutFile.close();

        }

    }

    /**
     * submitUpdate
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean submitDelete(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue())) {

            // check duplicated
            for (int i = 0; i < sMsg.F.getValidCount(); i++) {
                for (int j = 0; j < sMsg.F.getValidCount(); j++) {

                    if (sMsg.F.no(i).apvlTeamPk_A1.getValueInt() != 0) {
                        if (i != j && sMsg.F.no(i).apvlTeamPk_A1.getValue().equals(sMsg.F.no(j).apvlTeamPk_A1.getValue())) {
                            removeRowA(sMsg.F, i);
                            j--;
                        }
                    }
                }
            }

            // Delete Row
            List<APVL_TEAMTMsg> deleteMsgs = new ArrayList<APVL_TEAMTMsg>();

            NPAL1160_FSMsgArray orgMsg = sMsg.F;
            for (int i = 0; i < orgMsg.getValidCount(); i++) {
                APVL_TEAMTMsg tMsg = new APVL_TEAMTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.apvlTeamPk, orgMsg.no(i).apvlTeamPk_A1);

                APVL_TEAMTMsg prodCtrlTMsg = (APVL_TEAMTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (null != prodCtrlTMsg) {
                    String findEzUpTime = orgMsg.no(i).ezUpTime_A1.getValue();
                    String findEzUpTimeZone = orgMsg.no(i).ezUpTimeZone_A1.getValue();
                    String currentEzUpTime = prodCtrlTMsg.ezUpTime.getValue();
                    String currentEzUpTimeZone = prodCtrlTMsg.ezUpTimeZone.getValue();

                    if (ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                        deleteMsgs.add(prodCtrlTMsg);
                    } else {
                        cMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            }
            if (!deleteMsgs.isEmpty()) {
                int retDelete = S21FastTBLAccessor.removeLogical(deleteMsgs.toArray(new APVL_TEAMTMsg[0]));
                if (retDelete != deleteMsgs.size()) {
                    cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                    cMsg.setMessageInfo(NPAM0007E);
                    return false;
                }
            }

        } else if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {

            // check duplicated
            for (int i = 0; i < sMsg.G.getValidCount(); i++) {
                for (int j = 0; j < sMsg.G.getValidCount(); j++) {
                    if (sMsg.G.no(i).apvlTeamMbrPk_B1.getValueInt() != 0) {
                        if (i != j && sMsg.G.no(i).apvlTeamMbrPk_B1.getValue().equals(sMsg.G.no(j).apvlTeamMbrPk_B1.getValue())) {
                            removeRowB(sMsg.G, i);
                            j--;
                        }
                    }
                }
            }

            // Delete Row
            List<APVL_TEAM_MBRTMsg> deleteMsgs = new ArrayList<APVL_TEAM_MBRTMsg>();

            NPAL1160_GSMsgArray orgMsg = sMsg.G;
            for (int i = 0; i < orgMsg.getValidCount(); i++) {
                APVL_TEAM_MBRTMsg tMsg = new APVL_TEAM_MBRTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.apvlTeamMbrPk, orgMsg.no(i).apvlTeamMbrPk_B1);

                APVL_TEAM_MBRTMsg prodCtrlTMsg = (APVL_TEAM_MBRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (null != prodCtrlTMsg) {
                    String findEzUpTime = orgMsg.no(i).ezUpTime_B1.getValue();
                    String findEzUpTimeZone = orgMsg.no(i).ezUpTimeZone_B1.getValue();
                    String currentEzUpTime = prodCtrlTMsg.ezUpTime.getValue();
                    String currentEzUpTimeZone = prodCtrlTMsg.ezUpTimeZone.getValue();

                    if (ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                        deleteMsgs.add(prodCtrlTMsg);
                    } else {
                        cMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            }
            if (!deleteMsgs.isEmpty()) {
                int retDelete = S21FastTBLAccessor.removeLogical(deleteMsgs.toArray(new APVL_TEAM_MBRTMsg[0]));
                if (retDelete != deleteMsgs.size()) {
                    cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                    cMsg.setMessageInfo(NPAM0007E);
                    return false;
                }
            }

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < sMsg.H.getValidCount(); i++) {
                for (int j = 0; j < sMsg.H.getValidCount(); j++) {
                    if (sMsg.H.no(i).apvlTeamTrxPk_C1.getValueInt() != 0) {
                        if (i != j && sMsg.H.no(i).apvlTeamTrxPk_C1.getValue().equals(sMsg.H.no(j).apvlTeamTrxPk_C1.getValue())) {
                            removeRowC(sMsg.H, i);
                            j--;
                        }
                    }
                }
            }

            // Delete Row
            List<APVL_TEAM_TRXTMsg> deleteMsgs = new ArrayList<APVL_TEAM_TRXTMsg>();

            NPAL1160_HSMsgArray orgMsg = sMsg.H;
            for (int i = 0; i < orgMsg.getValidCount(); i++) {
                APVL_TEAM_TRXTMsg tMsg = new APVL_TEAM_TRXTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.apvlTeamTrxPk, orgMsg.no(i).apvlTeamTrxPk_C1);

                APVL_TEAM_TRXTMsg prodCtrlTMsg = (APVL_TEAM_TRXTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (null != prodCtrlTMsg) {
                    String findEzUpTime = orgMsg.no(i).ezUpTime_C1.getValue();
                    String findEzUpTimeZone = orgMsg.no(i).ezUpTimeZone_C1.getValue();
                    String currentEzUpTime = prodCtrlTMsg.ezUpTime.getValue();
                    String currentEzUpTimeZone = prodCtrlTMsg.ezUpTimeZone.getValue();

                    if (ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                        deleteMsgs.add(prodCtrlTMsg);
                    } else {
                        cMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            }
            if (!deleteMsgs.isEmpty()) {
                int retDelete = S21FastTBLAccessor.removeLogical(deleteMsgs.toArray(new APVL_TEAM_TRXTMsg[0]));
                if (retDelete != deleteMsgs.size()) {
                    cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                    cMsg.setMessageInfo(NPAM0007E);
                    return false;
                }
            }

        } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < sMsg.I.getValidCount(); i++) {
                for (int j = 0; j < sMsg.I.getValidCount(); j++) {
                    if (sMsg.I.no(i).apvlTeamLocPk_D1.getValueInt() != 0) {
                        if (i != j && sMsg.I.no(i).apvlTeamLocPk_D1.getValue().equals(sMsg.I.no(j).apvlTeamLocPk_D1.getValue())) {
                            removeRowD(sMsg.I, i);
                            j--;
                        }
                    }
                }
            }

            // Delete Row
            List<APVL_TEAM_LOCTMsg> deleteMsgs = new ArrayList<APVL_TEAM_LOCTMsg>();

            NPAL1160_ISMsgArray orgMsg = sMsg.I;
            for (int i = 0; i < orgMsg.getValidCount(); i++) {
                APVL_TEAM_LOCTMsg tMsg = new APVL_TEAM_LOCTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.apvlTeamLocPk, orgMsg.no(i).apvlTeamLocPk_D1);

                APVL_TEAM_LOCTMsg prodCtrlTMsg = (APVL_TEAM_LOCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (null != prodCtrlTMsg) {
                    String findEzUpTime = orgMsg.no(i).ezUpTime_D1.getValue();
                    String findEzUpTimeZone = orgMsg.no(i).ezUpTimeZone_D1.getValue();
                    String currentEzUpTime = prodCtrlTMsg.ezUpTime.getValue();
                    String currentEzUpTimeZone = prodCtrlTMsg.ezUpTimeZone.getValue();

                    if (ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                        deleteMsgs.add(prodCtrlTMsg);
                    } else {
                        cMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            }
            if (!deleteMsgs.isEmpty()) {
                int retDelete = S21FastTBLAccessor.removeLogical(deleteMsgs.toArray(new APVL_TEAM_LOCTMsg[0]));
                if (retDelete != deleteMsgs.size()) {
                    cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                    cMsg.setMessageInfo(NPAM0007E);
                    return false;
                }
            }

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < sMsg.J.getValidCount(); i++) {
                for (int j = 0; j < sMsg.J.getValidCount(); j++) {
                    if (sMsg.J.no(i).apvlLimitPk_E1.getValueInt() != 0) {
                        if (i != j && sMsg.J.no(i).apvlLimitPk_E1.getValue().equals(sMsg.J.no(j).apvlLimitPk_E1.getValue())) {
                            removeRowE(sMsg.J, i);
                            j--;
                        }
                    }
                }
            }

            // Delete Row
            List<APVL_LIMITTMsg> deleteMsgs = new ArrayList<APVL_LIMITTMsg>();

            NPAL1160_JSMsgArray orgMsg = sMsg.J;
            for (int i = 0; i < orgMsg.getValidCount(); i++) {
                APVL_LIMITTMsg tMsg = new APVL_LIMITTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.apvlLimitPk, orgMsg.no(i).apvlLimitPk_E1);

                APVL_LIMITTMsg prodCtrlTMsg = (APVL_LIMITTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (null != prodCtrlTMsg) {
                    String findEzUpTime = orgMsg.no(i).ezUpTime_E1.getValue();
                    String findEzUpTimeZone = orgMsg.no(i).ezUpTimeZone_E1.getValue();
                    String currentEzUpTime = prodCtrlTMsg.ezUpTime.getValue();
                    String currentEzUpTimeZone = prodCtrlTMsg.ezUpTimeZone.getValue();

                    if (ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                        deleteMsgs.add(prodCtrlTMsg);
                    } else {
                        cMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
            }
            if (!deleteMsgs.isEmpty()) {
                int retDelete = S21FastTBLAccessor.removeLogical(deleteMsgs.toArray(new APVL_LIMITTMsg[0]));
                if (retDelete != deleteMsgs.size()) {
                    cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                    cMsg.setMessageInfo(NPAM0007E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * removeRowA
     * @param table
     * @param index
     */
    private static void removeRowA(NPAL1160_FSMsgArray table, int index) {
        if (index < 0) {
            return;
        }
        for (int i = index; i < table.getValidCount() - 1; i++) {
            EZDMsg.copy(table.no(i + 1), null, table.no(i), null);
        }
        table.no(table.getValidCount() - 1).clear();
        table.setValidCount(table.getValidCount() - 1);
    }

    /**
     * removeRowB
     * @param table
     * @param index
     */
    private static void removeRowB(NPAL1160_GSMsgArray table, int index) {
        if (index < 0) {
            return;
        }
        for (int i = index; i < table.getValidCount() - 1; i++) {
            EZDMsg.copy(table.no(i + 1), null, table.no(i), null);
        }
        table.no(table.getValidCount() - 1).clear();
        table.setValidCount(table.getValidCount() - 1);
    }

    /**
     * removeRowC
     * @param table
     * @param index
     */
    private static void removeRowC(NPAL1160_HSMsgArray table, int index) {
        if (index < 0) {
            return;
        }
        for (int i = index; i < table.getValidCount() - 1; i++) {
            EZDMsg.copy(table.no(i + 1), null, table.no(i), null);
        }
        table.no(table.getValidCount() - 1).clear();
        table.setValidCount(table.getValidCount() - 1);
    }

    /**
     * removeRowD
     * @param table
     * @param index
     */
    private static void removeRowD(NPAL1160_ISMsgArray table, int index) {
        if (index < 0) {
            return;
        }
        for (int i = index; i < table.getValidCount() - 1; i++) {
            EZDMsg.copy(table.no(i + 1), null, table.no(i), null);
        }
        table.no(table.getValidCount() - 1).clear();
        table.setValidCount(table.getValidCount() - 1);
    }

    /**
     * removeRowE
     * @param table
     * @param index
     */
    private static void removeRowE(NPAL1160_JSMsgArray table, int index) {
        if (index < 0) {
            return;
        }
        for (int i = index; i < table.getValidCount() - 1; i++) {
            EZDMsg.copy(table.no(i + 1), null, table.no(i), null);
        }
        table.no(table.getValidCount() - 1).clear();
        table.setValidCount(table.getValidCount() - 1);
    }

    /**
     * submitInsertUpdate
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     */
    public static void submitInsertUpdate(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {
        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).apvlTeamPk_A1)) {
                    // Check duplication
                    BigDecimal check = NPAL1160CommonLogic.searchTeamPKByName(cMsg, sMsg, glblCmpyCd, sMsg.A.no(i).apvlTeamNm_A1.getValue());
                    if (check.compareTo(BigDecimal.ZERO) != 0) {
                        sMsg.A.no(i).apvlTeamNm_A1.setErrorInfo(1, NMAM0265E);
                        int page = i / cMsg.A.length();
                        int showFrom = page * cMsg.A.length() + 1;
                        cMsg.xxPageShowFromNum.setValue(showFrom);
                        copyFromSMsgOntoCmsg(cMsg, sMsg);
                        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
                        return;
                    }
                    // Insert data
                    APVL_TEAMTMsg insertTMsg = new APVL_TEAMTMsg();
                    BigDecimal newPk = ZYPOracleSeqAccessor.getNumberBigDecimal(SEQUENCE_NM_APVL_TEAM_SQ);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlTeamPk, newPk);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlTeamNm, sMsg.A.no(i).apvlTeamNm_A1);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlTeamDescTxt, sMsg.A.no(i).apvlTeamDescTxt_A1);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlHrchTpCd, sMsg.A.no(i).apvlHrchTpCd_AS);
                    EZDTBLAccessor.insert(insertTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NPAM0007E);
                        return;
                    }
                } else {
                    APVL_TEAMTMsg updateTMsg = new APVL_TEAMTMsg();
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlTeamPk, sMsg.A.no(i).apvlTeamPk_A1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, glblCmpyCd);
                    updateTMsg = (APVL_TEAMTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updateTMsg);
                    if (updateTMsg == null) {
                        cMsg.setMessageInfo(NZZM0003E);
                        return;
                    }
                    // Check exclusive
                    String findEzUpTime = sMsg.A.no(i).ezUpTime_A1.getValue();
                    String findEzUpTimeZone = sMsg.A.no(i).ezUpTimeZone_A1.getValue();
                    String currentEzUpTime = updateTMsg.ezUpTime.getValue();
                    String currentEzUpTimeZone = updateTMsg.ezUpTimeZone.getValue();
                    if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                        cMsg.setMessageInfo(NZZM0003E);
                        return;
                    }
                    // Update data
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlTeamNm, sMsg.A.no(i).apvlTeamNm_A1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlTeamDescTxt, sMsg.A.no(i).apvlTeamDescTxt_A1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlHrchTpCd, sMsg.A.no(i).apvlHrchTpCd_AS);
                    EZDTBLAccessor.update(updateTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NPAM0007E);
                        return;
                    }
                }
            }

        } else if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < sMsg.B.getValidCount(); i++) {

                if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).apvlTeamMbrPk_B1)) {
                    // Check duplication
                    int check = NPAL1160CommonLogic.overlapCheckForMember(cMsg, sMsg, glblCmpyCd, i);
                    if (check != 0) {
                        sMsg.B.no(i).apvlTeamNm_B1.setErrorInfo(1, NMAM0265E);
                        sMsg.B.no(i).apvlTeamPosnTpCd_BS.setErrorInfo(1, NMAM0265E);
                        sMsg.B.no(i).psnCd_B1.setErrorInfo(1, NMAM0265E);
                        int page = i / cMsg.B.length();
                        int showFrom = page * cMsg.B.length() + 1;
                        cMsg.xxPageShowFromNum.setValue(showFrom);
                        copyFromSMsgOntoCmsg(cMsg, sMsg);
                        cMsg.xxPageShowOfNum.setValue(sMsg.B.getValidCount());
                        return;
                    }
                    // Insert data
                    APVL_TEAM_MBRTMsg insertTMsg = new APVL_TEAM_MBRTMsg();
                    BigDecimal newPk = ZYPOracleSeqAccessor.getNumberBigDecimal(SEQUENCE_NM_APVL_TEAM_MBR_SQ);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlTeamMbrPk, newPk);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlTeamPk, sMsg.B.no(i).apvlTeamPk_B1);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlTeamPosnTpCd, sMsg.B.no(i).apvlTeamPosnTpCd_BS);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd, sMsg.B.no(i).psnCd_B1);
                    EZDTBLAccessor.insert(insertTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NPAM0007E);
                        return;
                    }
                } else {
                    APVL_TEAM_MBRTMsg updateTMsg = new APVL_TEAM_MBRTMsg();
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlTeamMbrPk, sMsg.B.no(i).apvlTeamMbrPk_B1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, glblCmpyCd);
                    updateTMsg = (APVL_TEAM_MBRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updateTMsg);
                    if (updateTMsg == null) {
                        cMsg.setMessageInfo(NZZM0003E);
                        return;
                    }
                    // Check exclusive
                    String findEzUpTime = sMsg.B.no(i).ezUpTime_B1.getValue();
                    String findEzUpTimeZone = sMsg.B.no(i).ezUpTimeZone_B1.getValue();
                    String currentEzUpTime = updateTMsg.ezUpTime.getValue();
                    String currentEzUpTimeZone = updateTMsg.ezUpTimeZone.getValue();
                    if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                        cMsg.setMessageInfo(NZZM0003E);
                        return;
                    }
                    // Update data
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlTeamPk, sMsg.B.no(i).apvlTeamPk_B1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlTeamPosnTpCd, sMsg.B.no(i).apvlTeamPosnTpCd_BS);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.psnCd, sMsg.B.no(i).psnCd_B1);
                    EZDTBLAccessor.update(updateTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NPAM0007E);
                        return;
                    }
                }
            }

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < sMsg.C.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).apvlTeamTrxPk_C1)) {
                    // Check duplication
                    int check = NPAL1160CommonLogic.overlapCheckForTransaction(cMsg, sMsg, glblCmpyCd, i);
                    if (check != 0) {
                        sMsg.C.no(i).apvlTeamNm_C1.setErrorInfo(1, NMAM0265E);
                        sMsg.C.no(i).prchGrpCd_CS.setErrorInfo(1, NMAM0265E);
                        sMsg.C.no(i).mdseItemTpCd_CS.setErrorInfo(1, NMAM0265E);
                        sMsg.C.no(i).apvlHistSrcTpCd_CS.setErrorInfo(1, NMAM0265E);
                        int page = i / cMsg.C.length();
                        int showFrom = page * cMsg.C.length() + 1;
                        cMsg.xxPageShowFromNum.setValue(showFrom);
                        copyFromSMsgOntoCmsg(cMsg, sMsg);
                        cMsg.xxPageShowOfNum.setValue(sMsg.C.getValidCount());
                        return;
                    }
                    // Insert data
                    APVL_TEAM_TRXTMsg insertTMsg = new APVL_TEAM_TRXTMsg();
                    BigDecimal newPk = ZYPOracleSeqAccessor.getNumberBigDecimal(SEQUENCE_NM_APVL_TEAM_TRX_SQ);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlTeamTrxPk, newPk);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlTeamPk, sMsg.C.no(i).apvlTeamPk_C1);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.prchGrpCd, sMsg.C.no(i).prchGrpCd_CS);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.poMdseTpCd, sMsg.C.no(i).mdseItemTpCd_CS);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlHistSrcTpCd, sMsg.C.no(i).apvlHistSrcTpCd_CS);
                    EZDTBLAccessor.insert(insertTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NPAM0007E);
                        return;
                    }
                } else {
                    APVL_TEAM_TRXTMsg updateTMsg = new APVL_TEAM_TRXTMsg();
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlTeamTrxPk, sMsg.C.no(i).apvlTeamTrxPk_C1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, glblCmpyCd);
                    updateTMsg = (APVL_TEAM_TRXTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updateTMsg);
                    if (updateTMsg == null) {
                        cMsg.setMessageInfo(NZZM0003E);
                        return;
                    }
                    // Check exclusive
                    String findEzUpTime = sMsg.C.no(i).ezUpTime_C1.getValue();
                    String findEzUpTimeZone = sMsg.C.no(i).ezUpTimeZone_C1.getValue();
                    String currentEzUpTime = updateTMsg.ezUpTime.getValue();
                    String currentEzUpTimeZone = updateTMsg.ezUpTimeZone.getValue();
                    if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                        cMsg.setMessageInfo(NZZM0003E);
                        return;
                    }
                    // Update data
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlTeamPk, sMsg.C.no(i).apvlTeamPk_C1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.prchGrpCd, sMsg.C.no(i).prchGrpCd_CS);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.poMdseTpCd, sMsg.C.no(i).mdseItemTpCd_CS);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlHistSrcTpCd, sMsg.C.no(i).apvlHistSrcTpCd_CS);
                    EZDTBLAccessor.update(updateTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NPAM0007E);
                        return;
                    }
                }
            }
        } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < sMsg.D.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(sMsg.D.no(i).apvlTeamLocPk_D1)) {
                    // Check duplication
                    int check = NPAL1160CommonLogic.overlapCheckForLocation(cMsg, sMsg, glblCmpyCd, i);
                    if (check != 0) {
                        sMsg.D.no(i).apvlTeamNm_D1.setErrorInfo(1, NMAM0265E);
                        sMsg.D.no(i).rtlWhCd_D1.setErrorInfo(1, NMAM0265E);
                        int page = i / cMsg.D.length();
                        int showFrom = page * cMsg.D.length() + 1;
                        cMsg.xxPageShowFromNum.setValue(showFrom);
                        copyFromSMsgOntoCmsg(cMsg, sMsg);
                        cMsg.xxPageShowOfNum.setValue(sMsg.D.getValidCount());
                        return;
                    }
                    // Insert data
                    APVL_TEAM_LOCTMsg insertTMsg = new APVL_TEAM_LOCTMsg();
                    BigDecimal newPk = ZYPOracleSeqAccessor.getNumberBigDecimal(SEQUENCE_NM_APVL_TEAM_LOC_SQ);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlTeamLocPk, newPk);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlTeamPk, sMsg.D.no(i).apvlTeamPk_D1);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.rtlWhCd, sMsg.D.no(i).rtlWhCd_D1);
                    EZDTBLAccessor.insert(insertTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NPAM0007E);
                        return;
                    }
                } else {
                    APVL_TEAM_LOCTMsg updateTMsg = new APVL_TEAM_LOCTMsg();
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlTeamLocPk, sMsg.D.no(i).apvlTeamLocPk_D1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, glblCmpyCd);
                    updateTMsg = (APVL_TEAM_LOCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updateTMsg);
                    if (updateTMsg == null) {
                        cMsg.setMessageInfo(NZZM0003E);
                        return;
                    }
                    // Check exclusive
                    String findEzUpTime = sMsg.D.no(i).ezUpTime_D1.getValue();
                    String findEzUpTimeZone = sMsg.D.no(i).ezUpTimeZone_D1.getValue();
                    String currentEzUpTime = updateTMsg.ezUpTime.getValue();
                    String currentEzUpTimeZone = updateTMsg.ezUpTimeZone.getValue();
                    if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                        cMsg.setMessageInfo(NZZM0003E);
                        return;
                    }
                    // Update data
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlTeamPk, sMsg.D.no(i).apvlTeamPk_D1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.rtlWhCd, sMsg.D.no(i).rtlWhCd_D1);
                    EZDTBLAccessor.update(updateTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NPAM0007E);
                        return;
                    }
                }
            }
        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < sMsg.E.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).apvlLimitPk_E1)) {
                    // Check duplication
                    int check = NPAL1160CommonLogic.overlapCheckForApvlLimit(cMsg, sMsg, glblCmpyCd, i);
                    if (check != 0) {
                        sMsg.E.no(i).apvlHrchTpCd_ES.setErrorInfo(1, NMAM0265E);
                        sMsg.E.no(i).apvlTeamPosnTpCd_ES.setErrorInfo(1, NMAM0265E);
                        sMsg.E.no(i).psnCd_E1.setErrorInfo(1, NMAM0265E);
                        sMsg.E.no(i).prchGrpCd_ES.setErrorInfo(1, NMAM0265E);
                        sMsg.E.no(i).apvlHistSrcTpCd_ES.setErrorInfo(1, NMAM0265E);
                        // START 2023/08/29 M.Kikushima [QC#61590, ADD]
                        sMsg.E.no(i).prchReqTpCd_ES.setErrorInfo(1, NMAM0265E);
                        // END 2023/08/29 M.Kikushima [QC#61590, ADD]
                        int page = i / cMsg.E.length();
                        int showFrom = page * cMsg.E.length() + 1;
                        cMsg.xxPageShowFromNum.setValue(showFrom);
                        copyFromSMsgOntoCmsg(cMsg, sMsg);
                        cMsg.xxPageShowOfNum.setValue(sMsg.E.getValidCount());
                        return;
                    }
                    // Insert data
                    APVL_LIMITTMsg insertTMsg = new APVL_LIMITTMsg();
                    BigDecimal newPk = ZYPOracleSeqAccessor.getNumberBigDecimal(SEQUENCE_NM_APVL_LIMIT_SQ);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlLimitPk, newPk);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlHrchTpCd, sMsg.E.no(i).apvlHrchTpCd_ES);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlTeamPosnTpCd, sMsg.E.no(i).apvlTeamPosnTpCd_ES);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd, sMsg.E.no(i).psnCd_E1);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.prchGrpCd, sMsg.E.no(i).prchGrpCd_ES);
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlHistSrcTpCd, sMsg.E.no(i).apvlHistSrcTpCd_ES);
                    // START 2023/08/29 M.Kikushima [QC#61590, ADD]
                    ZYPEZDItemValueSetter.setValue(insertTMsg.prchReqTpCd, sMsg.E.no(i).prchReqTpCd_ES);
                    // END 2023/08/29 M.Kikushima [QC#61590, ADD]
                    ZYPEZDItemValueSetter.setValue(insertTMsg.apvlLimitAmt, sMsg.E.no(i).apvlLimitAmt_E1);
                    EZDTBLAccessor.insert(insertTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NPAM0007E);
                        return;
                    }
                } else {
                    APVL_LIMITTMsg updateTMsg = new APVL_LIMITTMsg();
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlLimitPk, sMsg.E.no(i).apvlLimitPk_E1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, glblCmpyCd);
                    updateTMsg = (APVL_LIMITTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updateTMsg);
                    if (updateTMsg == null) {
                        cMsg.setMessageInfo(NZZM0003E);
                        return;
                    }
                    // Check exclusive
                    String findEzUpTime = sMsg.E.no(i).ezUpTime_E1.getValue();
                    String findEzUpTimeZone = sMsg.E.no(i).ezUpTimeZone_E1.getValue();
                    String currentEzUpTime = updateTMsg.ezUpTime.getValue();
                    String currentEzUpTimeZone = updateTMsg.ezUpTimeZone.getValue();
                    if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                        cMsg.setMessageInfo(NZZM0003E);
                        return;
                    }
                    // Update data
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlHrchTpCd, sMsg.E.no(i).apvlHrchTpCd_ES);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlTeamPosnTpCd, sMsg.E.no(i).apvlTeamPosnTpCd_ES);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.psnCd, sMsg.E.no(i).psnCd_E1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.prchGrpCd, sMsg.E.no(i).prchGrpCd_ES);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlHistSrcTpCd, sMsg.E.no(i).apvlHistSrcTpCd_ES);
                    // START 2023/08/29 M.Kikushima [QC#61590, ADD]
                    ZYPEZDItemValueSetter.setValue(updateTMsg.prchReqTpCd, sMsg.E.no(i).prchReqTpCd_ES);
                    // END 2023/08/29 M.Kikushima [QC#61590, ADD]
                    ZYPEZDItemValueSetter.setValue(updateTMsg.apvlLimitAmt, sMsg.E.no(i).apvlLimitAmt_E1);
                    EZDTBLAccessor.update(updateTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NPAM0007E);
                        return;
                    }
                }
            }
        } else if (DISPLAY_TAB_NM_TECHTHRHD.equals(cMsg.xxDplyTab.getValue())) {
            // QC#18689-Sol#303. New Tab Tech Threshold.
            for (int i = 0; i < sMsg.K.getValidCount(); i++) {

                TECH_APVL_THRHDTMsg updateTMsg = new TECH_APVL_THRHDTMsg();
                ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsg.lineBizTpCd, sMsg.K.no(i).lineBizTpCd_K1);

                updateTMsg = (TECH_APVL_THRHDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updateTMsg);

                if (updateTMsg == null) {
                    cMsg.setMessageInfo(NPAM0007E);
                    return;
                }

                // check Update Threshold Amount.
                BigDecimal currentAmt = updateTMsg.techApvlThrhdAmt.getValue();
                BigDecimal updateAmt = sMsg.K.no(i).techApvlThrhdAmt_K1.getValue();
                if (currentAmt.compareTo(updateAmt) == 0) {
                    // Same date.
                    continue;
                }

                // Check exclusive
                String findEzUpTime = sMsg.K.no(i).ezUpTime_K1.getValue();
                String findEzUpTimeZone = sMsg.K.no(i).ezUpTimeZone_K1.getValue();
                String currentEzUpTime = updateTMsg.ezUpTime.getValue();
                String currentEzUpTimeZone = updateTMsg.ezUpTimeZone.getValue();

                if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                // Update
                ZYPEZDItemValueSetter.setValue(updateTMsg.techApvlThrhdAmt, sMsg.K.no(i).techApvlThrhdAmt_K1);
                EZDTBLAccessor.update(updateTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NPAM0007E);
                    return;
                }
            }
        }
        // START 2023/05/17 T.Kuroda [QC#61211, ADD]
        else if (DISPLAY_TAB_NM_TECHMIN.equals(cMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < sMsg.L.getValidCount(); i++) {

                TECH_APVL_MINTMsg updateTMsg = new TECH_APVL_MINTMsg();
                ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsg.lineBizTpCd, sMsg.L.no(i).lineBizTpCd_L1);

                updateTMsg = (TECH_APVL_MINTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updateTMsg);

                if (updateTMsg == null) {
                    cMsg.setMessageInfo(NPAM0007E);
                    return;
                }

                // check Update Threshold Amount.
                BigDecimal currentAmt = updateTMsg.techApvlMinAmt.getValue();
                BigDecimal updateAmt = sMsg.L.no(i).techApvlMinAmt_L1.getValue();
                if (currentAmt.compareTo(updateAmt) == 0) {
                    // Same date.
                    continue;
                }

                // Check exclusive
                String findEzUpTime = sMsg.L.no(i).ezUpTime_L1.getValue();
                String findEzUpTimeZone = sMsg.L.no(i).ezUpTimeZone_L1.getValue();
                String currentEzUpTime = updateTMsg.ezUpTime.getValue();
                String currentEzUpTimeZone = updateTMsg.ezUpTimeZone.getValue();

                if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                // Update
                ZYPEZDItemValueSetter.setValue(updateTMsg.techApvlMinAmt, sMsg.L.no(i).techApvlMinAmt_L1);
                EZDTBLAccessor.update(updateTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NPAM0007E);
                    return;
                }
            }
        }
        // END   2023/05/17 T.Kuroda [QC#61211, ADD]

        // Normal End
        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
        cMsg.setMessageInfo(NPAM0005I);
    }

    public static void clearScreenItem(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(cMsg.E);
        ZYPTableUtil.clear(cMsg.P);

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(sMsg.D);
        ZYPTableUtil.clear(sMsg.E);
        ZYPTableUtil.clear(sMsg.F);
        ZYPTableUtil.clear(sMsg.G);
        ZYPTableUtil.clear(sMsg.H);
        ZYPTableUtil.clear(sMsg.I);
        ZYPTableUtil.clear(sMsg.J);
    }

    /**
     * Check input for Team Tab
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkInput(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        if (DISPLAY_TAB_NM_TEAM.equals(cMsg.xxDplyTab.getValue())) {
            if (!checkInputForTeamTab(cMsg, sMsg, glblCmpyCd)) {
                return false;
            }
            if (!checkDuplicationForTeamTab(cMsg, sMsg)) {
                return false;
            }
        } else if (DISPLAY_TAB_NM_MEMBER.equals(cMsg.xxDplyTab.getValue())) {
            if (!checkInputForMemberTab(cMsg, sMsg, glblCmpyCd)) {
                return false;
            }
            if (!checkDuplicationForMemberTab(cMsg, sMsg)) {
                return false;
            }
        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(cMsg.xxDplyTab.getValue())) {
            if (!checkInputForTrxTab(cMsg, sMsg, glblCmpyCd)) {
                return false;
            }
            if (!checkDuplicationForTrxTab(cMsg, sMsg)) {
                return false;
            }
        } else if (DISPLAY_TAB_NM_LOCATION.equals(cMsg.xxDplyTab.getValue())) {
            if (!checkInputForLocationTab(cMsg, sMsg, glblCmpyCd)) {
                return false;
            }
            if (!checkDuplicationForLocationTab(cMsg, sMsg)) {
                return false;
            }
        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(cMsg.xxDplyTab.getValue())) {
            if (!checkInputForApprovalLimitTab(cMsg, sMsg, glblCmpyCd)) {
                return false;
            }
            if (!checkDuplicationForApprovalLimitTab(cMsg, sMsg)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check input for Team Tab
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkInputForTeamTab(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        int firstErrorCount = -1;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // Team Name
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).apvlTeamNm_A1)) {
                sMsg.A.no(i).apvlTeamNm_A1.setErrorInfo(1, ZZM9000E, new String[] {"Team Name" });
                if (firstErrorCount == -1) {
                    firstErrorCount = i;
                }
            }
            // Hierarchy Type
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).apvlHrchTpCd_AS)) {
                sMsg.A.no(i).apvlHrchTpCd_AS.setErrorInfo(1, ZZM9000E, new String[] {"Hierarchy Type" });
                if (firstErrorCount == -1) {
                    firstErrorCount = i;
                }
            }
        }

        if (firstErrorCount != -1) {
            int page = firstErrorCount / cMsg.A.length();
            int showFrom = page * cMsg.A.length() + 1;
            cMsg.xxPageShowFromNum.setValue(showFrom);
            copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Check Duplication for Team Tab
     * </pre>
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @return boolean
     */
    public static boolean checkDuplicationForTeamTab(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {
        int firstErrorCount = -1;

        if (sMsg.A.getValidCount() == 0) {
            return true;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String checkApvlTeamNm = sMsg.A.no(i).apvlTeamNm_A1.getValue();

            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }

                String compareApvlTeamNm = sMsg.A.no(j).apvlTeamNm_A1.getValue();

                if (checkApvlTeamNm.equals(compareApvlTeamNm)) {
                    sMsg.A.no(i).apvlTeamNm_A1.setErrorInfo(1, NLBM1344E, new String[] {"Team Name" });

                    if (firstErrorCount == -1) {
                        firstErrorCount = i;
                    }
                    break;
                }
            }
        }
        if (firstErrorCount != -1) {
            int page = firstErrorCount / cMsg.A.length();
            int showFrom = page * cMsg.A.length() + 1;
            cMsg.xxPageShowFromNum.setValue(showFrom);
            copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
            return false;
        }
        return true;
    }

    /**
     * Check input for Member Tab
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkInputForMemberTab(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        int firstErrorCount = -1;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            // Team Name
            if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).apvlTeamNm_B1)) {
                sMsg.B.no(i).apvlTeamNm_B1.setErrorInfo(1, ZZM9000E, new String[] {"Team Name" });
                if (firstErrorCount == -1) {
                    firstErrorCount = i;
                }
            }
            // Position
            if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).apvlTeamPosnTpCd_BS)) {
                sMsg.B.no(i).apvlTeamPosnTpCd_BS.setErrorInfo(1, ZZM9000E, new String[] {"Position" });
                if (firstErrorCount == -1) {
                    firstErrorCount = i;
                }
            }
            // Member Code
            if (ZYPCommonFunc.hasValue(sMsg.B.no(i).psnCd_B1)) {
                S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().countAuthPsn(glblCmpyCd, sMsg.B.no(i).psnCd_B1.getValue());
                if ((Integer) ssmResult.getResultObject() == 0) {
                    sMsg.B.no(i).psnCd_B1.setErrorInfo(1, NPAM1361E, new String[] {"Member Id" });
                    if (firstErrorCount == -1) {
                        firstErrorCount = i;
                    }
                }
            } else {
                sMsg.B.no(i).psnCd_B1.setErrorInfo(1, ZZM9000E, new String[] {"Member Id" });
                if (firstErrorCount == -1) {
                    firstErrorCount = i;
                }
            }
        }

        if (firstErrorCount != -1) {
            int page = firstErrorCount / cMsg.B.length();
            int showFrom = page * cMsg.B.length() + 1;
            cMsg.xxPageShowFromNum.setValue(showFrom);
            copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.xxPageShowOfNum.setValue(sMsg.B.getValidCount());
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Check Duplication for Member Tab
     * </pre>
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @return boolean
     */
    public static boolean checkDuplicationForMemberTab(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {
        int firstErrorCount = -1;

        if (sMsg.B.getValidCount() == 0) {
            return true;
        }

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            String checkApvlTeamNm = sMsg.B.no(i).apvlTeamNm_B1.getValue();
            String checkApvlTeamPosnTpCd = sMsg.B.no(i).apvlTeamPosnTpCd_BS.getValue();
            String checkPsnCd = sMsg.B.no(i).psnCd_B1.getValue();

            for (int j = 0; j < sMsg.B.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }

                String compareApvlTeamNm = sMsg.B.no(j).apvlTeamNm_B1.getValue();
                String compareApvlTeamPosnTpCd = sMsg.B.no(j).apvlTeamPosnTpCd_BS.getValue();
                String comparePsnCd = sMsg.B.no(j).psnCd_B1.getValue();

                if (checkApvlTeamNm.equals(compareApvlTeamNm) && checkApvlTeamPosnTpCd.equals(compareApvlTeamPosnTpCd) && checkPsnCd.equals(comparePsnCd)) {
                    sMsg.B.no(i).apvlTeamNm_B1.setErrorInfo(1, NLBM1344E, new String[] {"Member Information" });
                    sMsg.B.no(i).apvlTeamPosnTpCd_BS.setErrorInfo(1, NLBM1344E, new String[] {"Member Information" });
                    sMsg.B.no(i).psnCd_B1.setErrorInfo(1, NLBM1344E, new String[] {"Member Information" });

                    if (firstErrorCount == -1) {
                        firstErrorCount = i;
                    }
                    break;
                }
            }
        }
        if (firstErrorCount != -1) {
            int page = firstErrorCount / cMsg.B.length();
            int showFrom = page * cMsg.B.length() + 1;
            cMsg.xxPageShowFromNum.setValue(showFrom);
            copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.xxPageShowOfNum.setValue(sMsg.B.getValidCount());
            return false;
        }
        return true;
    }

    /**
     * Check input for Transaction Tab
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkInputForTrxTab(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        int firstErrorCount = -1;
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {

            // Team Name
            if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).apvlTeamNm_C1)) {
                sMsg.C.no(i).apvlTeamNm_C1.setErrorInfo(1, ZZM9000E, new String[] {"Team Name" });
                if (firstErrorCount == -1) {
                    firstErrorCount = i;
                }
            }

            // // Planning Group
            // if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).prchGrpCd_CS))
            // {
            // sMsg.C.no(i).prchGrpCd_CS.setErrorInfo(1, ZZM9000E, new
            // String[] {"Planning Group"});
            // if (firstErrorCount == -1) {
            // firstErrorCount = i;
            // }
            // }
            //
            // // Parts/Merchandise
            // if
            // (!ZYPCommonFunc.hasValue(sMsg.C.no(i).mdseItemTpCd_CS))
            // {
            // sMsg.C.no(i).mdseItemTpCd_CS.setErrorInfo(1, ZZM9000E,
            // new String[] {"Parts/Merchandise"});
            // if (firstErrorCount == -1) {
            // firstErrorCount = i;
            // }
            // }

            // Transactions
            if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).apvlHistSrcTpCd_CS)) {
                sMsg.C.no(i).apvlHistSrcTpCd_CS.setErrorInfo(1, ZZM9000E, new String[] {"Transactions" });
                if (firstErrorCount == -1) {
                    firstErrorCount = i;
                }
            }
        }

        if (firstErrorCount != -1) {
            int page = firstErrorCount / cMsg.C.length();
            int showFrom = page * cMsg.C.length() + 1;
            cMsg.xxPageShowFromNum.setValue(showFrom);
            copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.xxPageShowOfNum.setValue(sMsg.C.getValidCount());
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Check Duplication for Transaction Tab
     * </pre>
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @return boolean
     */
    public static boolean checkDuplicationForTrxTab(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {
        int firstErrorCount = -1;

        if (sMsg.C.getValidCount() == 0) {
            return true;
        }

        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            String checkApvlTeamNm = sMsg.C.no(i).apvlTeamNm_C1.getValue();
            String checkPrchGrpCd = getStringForNull(sMsg.C.no(i).prchGrpCd_CS.getValue());
            String checkMdseItemTpCd = getStringForNull(sMsg.C.no(i).mdseItemTpCd_CS.getValue());
            String checkApvlHistSrcTpCd = sMsg.C.no(i).apvlHistSrcTpCd_CS.getValue();

            for (int j = 0; j < sMsg.C.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }

                String compareApvlTeamNm = sMsg.C.no(j).apvlTeamNm_C1.getValue();
                String comparePrchGrpCd = getStringForNull(sMsg.C.no(j).prchGrpCd_CS.getValue());
                String compareMdseItemTpCd = getStringForNull(sMsg.C.no(j).mdseItemTpCd_CS.getValue());
                String compareApvlHistSrcTpCd = sMsg.C.no(j).apvlHistSrcTpCd_CS.getValue();

                if (checkApvlTeamNm.equals(compareApvlTeamNm) && checkPrchGrpCd.equals(comparePrchGrpCd) && checkMdseItemTpCd.equals(compareMdseItemTpCd) && checkApvlHistSrcTpCd.equals(compareApvlHistSrcTpCd)) {
                    sMsg.C.no(i).apvlTeamNm_C1.setErrorInfo(1, NLBM1344E, new String[] {"Transaction Information" });
                    sMsg.C.no(i).prchGrpCd_CS.setErrorInfo(1, NLBM1344E, new String[] {"Transaction Information" });
                    sMsg.C.no(i).mdseItemTpCd_CS.setErrorInfo(1, NLBM1344E, new String[] {"Transaction Information" });
                    sMsg.C.no(i).apvlHistSrcTpCd_CS.setErrorInfo(1, NLBM1344E, new String[] {"Transaction Information" });

                    if (firstErrorCount == -1) {
                        firstErrorCount = i;
                    }
                    break;
                }
            }
        }
        if (firstErrorCount != -1) {
            int page = firstErrorCount / cMsg.C.length();
            int showFrom = page * cMsg.C.length() + 1;
            cMsg.xxPageShowFromNum.setValue(showFrom);
            copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.xxPageShowOfNum.setValue(sMsg.C.getValidCount());
            return false;
        }
        return true;
    }

    /**
     * Check input for Location Tab
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkInputForLocationTab(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        int firstErrorCount = -1;
        for (int i = 0; i < sMsg.D.getValidCount(); i++) {

            // Team Name
            if (!ZYPCommonFunc.hasValue(sMsg.D.no(i).apvlTeamNm_D1)) {
                sMsg.D.no(i).apvlTeamNm_D1.setErrorInfo(1, ZZM9000E, new String[] {"Team Name" });
                if (firstErrorCount == -1) {
                    firstErrorCount = i;
                }
            }

            // WH
            if (ZYPCommonFunc.hasValue(sMsg.D.no(i).rtlWhCd_D1)) {
                S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().countWh(glblCmpyCd, sMsg.D.no(i).rtlWhCd_D1.getValue());
                if ((Integer) ssmResult.getResultObject() == 0) {
                    sMsg.D.no(i).rtlWhCd_D1.setErrorInfo(1, NPAM1361E, new String[] {"Warehouse" });
                    if (firstErrorCount == -1) {
                        firstErrorCount = i;
                    }
                }
            } else {
                sMsg.D.no(i).rtlWhCd_D1.setErrorInfo(1, ZZM9000E, new String[] {"Warehouse" });
                if (firstErrorCount == -1) {
                    firstErrorCount = i;
                }
            }
        }

        if (firstErrorCount != -1) {
            int page = firstErrorCount / cMsg.D.length();
            int showFrom = page * cMsg.D.length() + 1;
            cMsg.xxPageShowFromNum.setValue(showFrom);
            copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.xxPageShowOfNum.setValue(sMsg.D.getValidCount());
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Check Duplication for Location Tab
     * </pre>
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @return boolean
     */
    public static boolean checkDuplicationForLocationTab(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {
        int firstErrorCount = -1;

        if (sMsg.D.getValidCount() == 0) {
            return true;
        }

        for (int i = 0; i < sMsg.D.getValidCount(); i++) {
            String checkApvlTeamNm = sMsg.D.no(i).apvlTeamNm_D1.getValue();
            String checkRtlWhCd = sMsg.D.no(i).rtlWhCd_D1.getValue();

            for (int j = 0; j < sMsg.D.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }

                String compareApvlTeamNm = sMsg.D.no(j).apvlTeamNm_D1.getValue();
                String compareRtlWhCd = sMsg.D.no(j).rtlWhCd_D1.getValue();

                if (checkApvlTeamNm.equals(compareApvlTeamNm) && checkRtlWhCd.equals(compareRtlWhCd)) {
                    sMsg.D.no(i).apvlTeamNm_D1.setErrorInfo(1, NLBM1344E, new String[] {"Location Information" });
                    sMsg.D.no(i).rtlWhCd_D1.setErrorInfo(1, NLBM1344E, new String[] {"Location Information" });

                    if (firstErrorCount == -1) {
                        firstErrorCount = i;
                    }
                    break;
                }
            }
        }
        if (firstErrorCount != -1) {
            int page = firstErrorCount / cMsg.D.length();
            int showFrom = page * cMsg.D.length() + 1;
            cMsg.xxPageShowFromNum.setValue(showFrom);
            copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.xxPageShowOfNum.setValue(sMsg.D.getValidCount());
            return false;
        }
        return true;
    }

    /**
     * Check input for Approval Limit Tab
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkInputForApprovalLimitTab(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg, String glblCmpyCd) {

        int firstErrorCount = -1;
        for (int i = 0; i < sMsg.E.getValidCount(); i++) {

            // Hierarchy Type
            if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).apvlHrchTpCd_ES)) {
                sMsg.E.no(i).apvlHrchTpCd_ES.setErrorInfo(1, ZZM9000E, new String[] {"Hierarchy Type" });
                if (firstErrorCount == -1) {
                    firstErrorCount = i;
                }
            }
            // Position
            if (APVL_HRCH_TP.POSITIONAL.equals(sMsg.E.no(i).apvlHrchTpCd_ES.getValue())) {
                if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).apvlTeamPosnTpCd_ES)) {
                    sMsg.E.no(i).apvlTeamPosnTpCd_ES.setErrorInfo(1, ZZM9000E, new String[] {"Position" });
                    if (firstErrorCount == -1) {
                        firstErrorCount = i;
                    }
                }
            }
            // Employee Id
            if (APVL_HRCH_TP.EMPLOYEE.equals(sMsg.E.no(i).apvlHrchTpCd_ES.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.E.no(i).psnCd_E1)) {
                    if (!ASTERISK.equals(sMsg.E.no(i).psnCd_E1.getValue())) {
                        S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().countAuthPsn(glblCmpyCd, sMsg.E.no(i).psnCd_E1.getValue());
                        if ((Integer) ssmResult.getResultObject() == 0) {
                            sMsg.E.no(i).psnCd_E1.setErrorInfo(1, NPAM1361E, new String[] {"Employee Id" });
                            if (firstErrorCount == -1) {
                                firstErrorCount = i;
                            }
                        }
                    }
                } else {
                    sMsg.E.no(i).psnCd_E1.setErrorInfo(1, ZZM9000E, new String[] {"Employee Id" });
                    if (firstErrorCount == -1) {
                        firstErrorCount = i;
                    }
                }
            } else {
                if (ZYPCommonFunc.hasValue(sMsg.E.no(i).psnCd_E1)) {
                    if (!ASTERISK.equals(sMsg.E.no(i).psnCd_E1.getValue())) {
                        S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().countAuthPsn(glblCmpyCd, sMsg.E.no(i).psnCd_E1.getValue());
                        if ((Integer) ssmResult.getResultObject() == 0) {
                            sMsg.E.no(i).psnCd_E1.setErrorInfo(1, NPAM1361E, new String[] {"Employee Id" });
                            if (firstErrorCount == -1) {
                                firstErrorCount = i;
                            }
                        }
                    }
                }
            }

            // QC#18689-Sol#303 Add check.
            // Planning Group
            if (APVL_HRCH_TP.EMPLOYEE.equals(sMsg.E.no(i).apvlHrchTpCd_ES.getValue()) //
                    && APVL_HIST_SRC_TP.TECH_REQUEST.equals(sMsg.E.no(i).apvlHistSrcTpCd_ES.getValue()) //
                    && ZYPCommonFunc.hasValue(sMsg.E.no(i).prchGrpCd_ES)) {
                sMsg.E.no(i).prchGrpCd_ES.setErrorInfo(1, NPAM0108E, new String[] {"Planning Group", "unnecessary" });
                if (firstErrorCount == -1) {
                    firstErrorCount = i;
                }
            }

            // Transactions
            if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).apvlHistSrcTpCd_ES)) {
                sMsg.E.no(i).apvlHistSrcTpCd_ES.setErrorInfo(1, ZZM9000E, new String[] {"Transactions" });
                if (firstErrorCount == -1) {
                    firstErrorCount = i;
                }
            }
            // $Limit
            if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).apvlLimitAmt_E1)) {
                sMsg.E.no(i).apvlLimitAmt_E1.setErrorInfo(1, ZZM9000E, new String[] {"$Limit" });
                if (firstErrorCount == -1) {
                    firstErrorCount = i;
                }
            }

            // QC#18689-Sol#303 Add check.
            // Check $Limit balance. false:NG
            if (!chkLimitAmtBalancing(glblCmpyCd, sMsg, i)) {
                if (firstErrorCount == -1) {
                    firstErrorCount = i;
                }
            }
        }

        if (firstErrorCount != -1) {
            int page = firstErrorCount / cMsg.E.length();
            int showFrom = page * cMsg.E.length() + 1;
            cMsg.xxPageShowFromNum.setValue(showFrom);
            copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.xxPageShowOfNum.setValue(sMsg.E.getValidCount());
            return false;
        }
        return true;
    }

    /**
     * chkLimitAmtBalancing QC#18689-Sol#303 Add method.
     * @param glblCmpyCd String
     * @param eSMsg NPAL1160_ESMsg
     * @return true:OK / false:NG
     */
    public static boolean chkLimitAmtBalancing(String glblCmpyCd, NPAL1160SMsg sMsg, int i) {

        NPAL1160_ESMsg eSMsg = sMsg.E.no(i);

        S21SsmEZDResult result = NPAL1160Query.getInstance().getBeforeAndAfterPositionLimitAmt( //
                glblCmpyCd //
                , eSMsg.apvlHrchTpCd_ES.getValue() //
                , eSMsg.prchGrpCd_ES.getValue() //
                , eSMsg.apvlHistSrcTpCd_ES.getValue() //
                , eSMsg.apvlTeamPosnTpCd_ES.getValue());

        if (result.isCodeNormal()) {
            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();

            String lowPosCd = "";
            BigDecimal lowAmt = BigDecimal.ZERO;
            String upPosCd = "";
            BigDecimal upAmt = BigDecimal.ZERO;
            boolean err = false;
            for (Map m : resultMap) {

                String comparePosCd = (String) m.get("APVL_TEAM_POSN_TP_CD");
                BigDecimal compareLimitAmt = (BigDecimal) m.get("APVL_LIMIT_AMT");

                if (comparePosCd.compareTo(eSMsg.apvlTeamPosnTpCd_ES.getValue()) < 0) {
                    // Upper position data
                    upAmt = compareLimitAmt;
                    upPosCd = comparePosCd;

                }
                if (comparePosCd.compareTo(eSMsg.apvlTeamPosnTpCd_ES.getValue()) > 0) {
                    // Lower position data
                    lowAmt = compareLimitAmt;
                    lowPosCd = comparePosCd;

                }
            }

            // check input data and change data.
            String currentPosCd = eSMsg.apvlTeamPosnTpCd_ES.getValue();
            for (int j = 0; j < sMsg.E.getValidCount(); j++) {
                String chkPosCd = sMsg.E.no(j).apvlTeamPosnTpCd_ES.getValue();
                if (upPosCd.equals(chkPosCd) //
                        || (upPosCd.compareTo(chkPosCd) < 0 && currentPosCd.compareTo(chkPosCd) > 0)) {
                    upAmt = sMsg.E.no(j).apvlLimitAmt_E1.getValue();
                }
                if (lowPosCd.equals(chkPosCd) //
                        || (lowPosCd.compareTo(chkPosCd) > 0 && currentPosCd.compareTo(chkPosCd) < 0)) {
                    lowAmt = sMsg.E.no(j).apvlLimitAmt_E1.getValue();
                }

            }

            if (BigDecimal.ZERO.compareTo(upAmt) == 0) {
                upAmt = new BigDecimal("999999999999999.99");
            }

            if (upAmt.compareTo(eSMsg.apvlLimitAmt_E1.getValue()) < 0) {
                // Input Amount Higher.
                err = true;
            }

            if (lowAmt.compareTo(eSMsg.apvlLimitAmt_E1.getValue()) > 0) {
                // Input Amount Lower.
                err = true;
            }

            if (err) {

                eSMsg.apvlLimitAmt_E1.setErrorInfo(1, NPAM1607E, new String[] {lowAmt.toPlainString(), upAmt.toPlainString() });
                return false;
            }

            // Success
            return true;
        } else {
            // not exist after and before position.
            return true;
        }
    }

    /**
     * <pre>
     * Check Duplication for Approval Limit Tab
     * </pre>
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @return boolean
     */
    public static boolean checkDuplicationForApprovalLimitTab(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {
        int firstErrorCount = -1;

        if (sMsg.E.getValidCount() == 0) {
            return true;
        }

        for (int i = 0; i < sMsg.E.getValidCount(); i++) {
            String checkApvlHrchTpCd = sMsg.E.no(i).apvlHrchTpCd_ES.getValue();
            String checkApvlTeamPosnTpCd = getStringForNull(sMsg.E.no(i).apvlTeamPosnTpCd_ES.getValue());
            String checkPsnCd = getStringForNull(sMsg.E.no(i).psnCd_E1.getValue());
            String checkPrchGrpCd = getStringForNull(sMsg.E.no(i).prchGrpCd_ES.getValue());
            String checkApvlHistSrcTpCd = sMsg.E.no(i).apvlHistSrcTpCd_ES.getValue();
            // START 2023/08/29 M.Kikushima [QC#61590, ADD]
            String checkPrchReqTpCd = sMsg.E.no(i).prchReqTpCd_ES.getValue();
            // END 2023/08/29 M.Kikushima [QC#61590, ADD]

            for (int j = 0; j < sMsg.E.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }

                String compareApvlHrchTpCd = sMsg.E.no(j).apvlHrchTpCd_ES.getValue();
                String compareApvlTeamPosnTpCd = getStringForNull(sMsg.E.no(j).apvlTeamPosnTpCd_ES.getValue());
                String comparePsnCd = getStringForNull(sMsg.E.no(j).psnCd_E1.getValue());
                String comparePrchGrpCd = getStringForNull(sMsg.E.no(j).prchGrpCd_ES.getValue());
                String compareApvlHistSrcTpCd = sMsg.E.no(j).apvlHistSrcTpCd_ES.getValue();
                // START 2023/08/29 M.Kikushima [QC#61590, ADD]
                String comparePrchReqTpCd = sMsg.E.no(j).prchReqTpCd_ES.getValue();
                // END 2023/08/29 M.Kikushima [QC#61590, ADD]

                if (checkApvlHrchTpCd.equals(compareApvlHrchTpCd) && checkApvlTeamPosnTpCd.equals(compareApvlTeamPosnTpCd) && checkPsnCd.equals(comparePsnCd) && checkPrchGrpCd.equals(comparePrchGrpCd)
                        && checkApvlHistSrcTpCd.equals(compareApvlHistSrcTpCd)
                        // START 2023/08/29 M.Kikushima [QC#61590, ADD]
                        && checkPrchReqTpCd.equals(comparePrchReqTpCd)) {
                        // END 2023/08/29 M.Kikushima [QC#61590, ADD]
                    sMsg.E.no(i).apvlHrchTpCd_ES.setErrorInfo(1, NLBM1344E, new String[] {"Approval Limit Informaton" });
                    sMsg.E.no(i).apvlTeamPosnTpCd_ES.setErrorInfo(1, NLBM1344E, new String[] {"Approval Limit Informaton" });
                    sMsg.E.no(i).psnCd_E1.setErrorInfo(1, NLBM1344E, new String[] {"Approval Limit Informaton" });
                    sMsg.E.no(i).prchGrpCd_ES.setErrorInfo(1, NLBM1344E, new String[] {"Approval Limit Informaton" });
                    sMsg.E.no(i).apvlHistSrcTpCd_ES.setErrorInfo(1, NLBM1344E, new String[] {"Approval Limit Informaton" });
                    // START 2023/08/29 M.Kikushima [QC#61590, ADD]
                    sMsg.E.no(i).prchReqTpCd_ES.setErrorInfo(1, NLBM1344E, new String[] {"Approval Limit Informaton" });
                    // END 2023/08/29 M.Kikushima [QC#61590, ADD]

                    if (firstErrorCount == -1) {
                        firstErrorCount = i;
                    }
                    break;
                }
            }
        }
        if (firstErrorCount != -1) {
            int page = firstErrorCount / cMsg.E.length();
            int showFrom = page * cMsg.E.length() + 1;
            cMsg.xxPageShowFromNum.setValue(showFrom);
            copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.xxPageShowOfNum.setValue(sMsg.E.getValidCount());
            return false;
        }
        return true;
    }

    public static String getStringForNull(String inputValue) {
        if (!ZYPCommonFunc.hasValue(inputValue)) {
            return "";
        }
        return inputValue;
    }

    /**
     * <pre>
     * Check if multiple detail are selected at Team Tab
     * </pre>
     * @param cMsg NPAL1160CMsg
     * @param sMsg NPAL1160SMsg
     * @return boolean
     */
    public static boolean checkMultipleSelectionAtTeamTab(NPAL1160CMsg cMsg, NPAL1160SMsg sMsg) {
        int firstErrorCount = -1;
        int selectedChkBoxCount = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                selectedChkBoxCount++;
            }
        }

        if (selectedChkBoxCount > 1) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                    sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM1215E);
                    if (firstErrorCount == -1) {
                        firstErrorCount = i;
                    }
                }
            }

            int page = firstErrorCount / cMsg.A.length();
            int showFrom = page * cMsg.A.length() + 1;
            cMsg.xxPageShowFromNum.setValue(showFrom);
            copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

            return false;
        }
        return true;
    }

    // START 2023/08/29 M.Kikushima [QC#61590, ADD]
    private static List<Map<String, String>> getRequestType(String  glblCmpyCd, String  apvlHistSrcTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        if(APVL_HIST_SRC_TP.INVENTORY_REQUEST.equals(apvlHistSrcTpCd)){
            ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.INVENTORY_REQUEST);
        }else if(APVL_HIST_SRC_TP.PO_REQUISITION.equals(apvlHistSrcTpCd)){
            ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.PO_REQUISITION);
        }
        ssmParam.put(DB_PARAM_SCR_ENT_AVAL_FLG, ZYPConstant.FLG_ON_Y);
        S21SsmEZDResult ssmResult = NPAL1160Query.getInstance().getRequestType(ssmParam);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        if (ssmResult.isCodeNormal()) {
           list = (List) ssmResult.getResultObject();
        }
        
        return list;
    }

    public static void setRequestTypeByAL(String glblCmpyCd, NPAL1160_ESMsg csMsg, List<Map<String, String>> list) {

        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map = list.get(i);
            ZYPEZDItemValueSetter.setValue(csMsg.prchReqTpCd_EC.no(i), (String) map.get("PRCH_REQ_TP_CD"));
            ZYPEZDItemValueSetter.setValue(csMsg.prchReqTpNm_ED.no(i), (String) map.get("PRCH_REQ_TP_NM"));
            if (i >= csMsg.prchReqTpCd_EC.length()) {
                break;
            }
        }
    }
    // END 2023/08/29 M.Kikushima [QC#61590, ADD]

}
