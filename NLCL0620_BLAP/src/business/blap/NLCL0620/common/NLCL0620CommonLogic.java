/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0620.common;

import static business.blap.NLCL0620.constant.NLCL0620Constant.BIZ_APP_ID;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_COLUMN_CNT;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_COLUMN_CNT_OPEN_ORDR;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_COLUMN_INVTY_LOC_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_COLUMN_LOC_TP_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_COLUMN_MAX_ADJ_SUBMT_TS;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_COLUMN_PHYS_INVTY_NUM;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_COLUMN_RTL_SWH_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_COLUMN_RTL_WH_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_COLUMN_RTL_WH_NM;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_COLUMN_TECH_NM;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_COLUMN_TECH_TOC_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_COLUMN_TOTAL_ADJ_GRS_AMT;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_COLUMN_TOTAL_ADJ_NET_AMT;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_CMSG;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_DEST_RTL_WH_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_INSOURCED_PO;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_LOC_TP_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_MAX_ROWNUM;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_OPEN_STS_FLG;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_PHYS_INVTY_CNT_STS_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_PHYS_INVTY_CTRL_NM;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_PHYS_INVTY_DT;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_PHYS_INVTY_NUM;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_PHYS_INVTY_STS_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_PRCH_REQ_LINE_SUB_NUM;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_PRCH_REQ_REC_TP_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_PROCR_TP_CD_1;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_PROCR_TP_CD_2;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_RQST_TECH_TOC_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_SALES_DATE;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_TECH_TOC_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_THRESHOLD_OPEN_ORDER_DAYS;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_USER_ID;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_VALUE_EXCLUSION_LINE_SUB_NUM;
import static business.blap.NLCL0620.constant.NLCL0620Constant.FUNC_ID_ALL_USERS;
import static business.blap.NLCL0620.constant.NLCL0620Constant.MSG_VALUE_PHYS_INVTY_CTRL;
import static business.blap.NLCL0620.constant.NLCL0620Constant.NLCM0143E;
import static business.blap.NLCL0620.constant.NLCL0620Constant.NLCM0144E;
import static business.blap.NLCL0620.constant.NLCL0620Constant.NLCM0162E;
import static business.blap.NLCL0620.constant.NLCL0620Constant.NLCM0229E;
import static business.blap.NLCL0620.constant.NLCL0620Constant.NLZM2278E;
import static business.blap.NLCL0620.constant.NLCL0620Constant.NMAM0038I;
import static business.blap.NLCL0620.constant.NLCL0620Constant.NMAM0176E;
import static business.blap.NLCL0620.constant.NLCL0620Constant.NMAM8181W;
import static business.blap.NLCL0620.constant.NLCL0620Constant.VAR_CHAR_CONST_VAL_DELIM;
import static business.blap.NLCL0620.constant.NLCL0620Constant.ZZM8100I;
import static business.blap.NLCL0620.constant.NLCL0620Constant.REPLACE_CHAR;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0620.NLCL0620CMsg;
import business.blap.NLCL0620.NLCL0620Query;
import business.blap.NLCL0620.NLCL0620SMsg;
import business.db.PHYS_INVTY_CTRLTMsg;
import business.db.PHYS_INVTY_STSTMsg;
import business.file.NLCL0620F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_QLFY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NLCL0620 Tech PI Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/02/2016   CITS         Makoto Okigami     Create          N/A
 * 12/06/2018   Fujitsu         S.Ohki          Update          QC#28128
 * 12/11/2018   Fujitsu         S.Ohki          Update          QC#28755
 * 12/17/2019   Fujitsu         T.Ogura         Update          QC#54986
 * 02/12/2020   CITS            K.Ogino         Update          QC#55863
 * 06/25/2020   CITS          M.Furugoori       Update          QC#56979
 * 09/29/2021   CITS            R.Azucena       Update          QC#59216
 *</pre>
 */
public class NLCL0620CommonLogic {

    /**
     * Search
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     * @param glblCmpyCd String
     */
    public static void search(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg, String glblCmpyCd) {

        // START 2018/12/11 S.Ohki [QC#28755,MOD]
//        if (!checkTechnician(cMsg, sMsg, glblCmpyCd)) {
//            return;
//        }

        if (ZYPCommonFunc.hasValue(cMsg.techTocCd)) {
        	if (!checkTechnician(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
        }
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
        	if (!checkLocation(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
        }
        // END 2018/12/11 S.Ohki [QC#28755,ADD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.TECH_REQUEST);
        ssmParam.put(DB_PARAM_PRCH_REQ_LINE_SUB_NUM, DB_PARAM_VALUE_EXCLUSION_LINE_SUB_NUM);
        ssmParam.put(DB_PARAM_OPEN_STS_FLG, ZYPConstant.FLG_ON_Y);
        ssmParam.put(DB_PARAM_PROCR_TP_CD_1, PROCR_TP.SUPPLIER);
        ssmParam.put(DB_PARAM_PROCR_TP_CD_2, PROCR_TP.WAREHOUSE);
        ssmParam.put(DB_PARAM_PHYS_INVTY_DT, cMsg.physInvtyDt.getValue());
        ssmParam.put(DB_PARAM_MAX_ROWNUM, sMsg.A.length() + 1);
        // START 2018/12/11 S.Ohki [QC#28755,ADD]
        ssmParam.put(DB_PARAM_RQST_TECH_TOC_CD, cMsg.techTocCd.getValue());
        ssmParam.put(DB_PARAM_DEST_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        // END 2018/12/11 S.Ohki [QC#28755,ADD]
        // START 01/07/2020 T.Ogura [QC#54986,ADD]
        ssmParam.put(DB_PARAM_INSOURCED_PO, PRCH_REQ_LINE_TP.INSOURCED_PO);
        // START 2021/09/29 R.Azucena[QC#59216, MOD]
        // ssmParam.put(DB_PARAM_CHOICE_SHIP_CONFIRMATION, PRCH_REQ_SRC_TP.CHOICE_SHIP_CONFIRMATION);
        ssmParam.put("shipConfirmationCdArray", new String[] {PRCH_REQ_SRC_TP.CHOICE_SHIP_CONFIRMATION, PRCH_REQ_SRC_TP.MNX_SHIP_CONFIRMATION });
        // END 2021/09/29 R.Azucena[QC#59216, MOD]

        // QC#54986 Start
        String[] inclPoOrdSrcCds = null;
        String varCharConst = ZYPCodeDataUtil.getVarCharConstValue("NLCL0620_INCL_PO_ORD_SRC_CD", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(varCharConst)) {
            inclPoOrdSrcCds = varCharConst.split(",");
        }
        String[] exclDsPoTpCds = null;
        varCharConst = ZYPCodeDataUtil.getVarCharConstValue("NLCL0620_EXCL_DS_PO_TP_CD", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(varCharConst)) {
            exclDsPoTpCds = varCharConst.split(",");
        }
        String[] exclPoStsCds = null;
        varCharConst = ZYPCodeDataUtil.getVarCharConstValue("NLCL0620_EXCL_PO_STS_CD", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(varCharConst)) {
            exclPoStsCds = varCharConst.split(",");
        }
        if (inclPoOrdSrcCds != null) {
            ssmParam.put("poOrdSrcCd", inclPoOrdSrcCds);
        }
        if (exclDsPoTpCds != null) {
            ssmParam.put("dsPoTpCd", exclDsPoTpCds);
        }
        if (exclPoStsCds != null) {
            ssmParam.put("poStsCd", exclPoStsCds);
        }
        ssmParam.put("poQlfyCdTech", PO_QLFY.TECH_REQUEST);
        ssmParam.put("soPrinted", "SO Printed");
        ssmParam.put("printed", RWS_STS.PRINTED);
        ssmParam.put("receiving", RWS_STS.RECEIVING);
        ssmParam.put("cancel", PRCH_REQ_LINE_STS.CANCELLED);
        ssmParam.put("shipped", ZYPCodeDataUtil.getName(SHPG_STS.class, glblCmpyCd, SHPG_STS.SHIPPED));
        // QC#55863
        ssmParam.put("entered", ACCT_INV_STS.ENTERED);
        // QC#54986 End
        // END   01/07/2020 T.Ogura [QC#54986,ADD]

        S21SsmEZDResult result = null;
        result = NLCL0620Query.getInstance().search(ssmParam, sMsg);

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
            cMsg.setMessageInfo(NMAM8181W, new String[] {String.format("%d", sMsg.A.length()), String.format("%d", sMsg.A.length())});
            queryResCnt = sMsg.A.length();
        }

        // Copy 1 page Data(SMsg -> CMsg)
        int i = 0;
        for (; i < sMsg.A.getValidCount(); i++) {
            if (i == cMsg.A.length()) {
                break;
            }
            // START 2020/06/25 [QC#56979,ADD]
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).proNum_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).carrTrkUrl_A1)) {
                editCarrTrkUrl(sMsg, i);
            }
            // END 2020/06/25 [QC#56979,ADD]
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);

        // Setting Next Page
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(queryResCnt);

    }

    // START 2020/06/25 [QC#56979,ADD]
    /**
     * <pre>
     * editCarrTrkUrl
     * ADD QC:56979
     * </pre>
     * @param sMsg NLCL0620SMsg
     */
    public static void editCarrTrkUrl(NLCL0620SMsg sMsg, int i) {
        String url = sMsg.A.no(i).carrTrkUrl_A1.getValue();
        Pattern pattern = Pattern.compile(REPLACE_CHAR);
        Matcher matcher = pattern.matcher(url);
        String str = matcher.replaceAll(sMsg.A.no(i).proNum_A1.getValue());
        sMsg.A.no(i).carrTrkUrl_A1.setValue(str);
    }
    // END 2020/06/25 [QC#56979,ADD]

    /**
     * Search Last Physical Inventory
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     * @param glblCmpyCd String
     */
    public static void searchLastPhysicalInventory(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg, String glblCmpyCd) {

        cMsg.shipDt.clear();
        cMsg.adjGrsAmt.clear();
        cMsg.adjNetAmt.clear();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_TECH_TOC_CD, cMsg.techTocCd.getValue());
        ssmParam.put(DB_PARAM_PHYS_INVTY_STS_CD, PHYS_INVTY_STS.CLOSE);
        ssmParam.put(DB_PARAM_PHYS_INVTY_CNT_STS_CD, PHYS_INVTY_CNT_STS.PI_CANCELED);
        ssmParam.put(DB_PARAM_MAX_ROWNUM, 1);
        // START 2018/12/11 S.Ohki [QC#28755,ADD]
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        // END 2018/12/11 S.Ohki [QC#28755,ADD]

        S21SsmEZDResult result = null;
        result = NLCL0620Query.getInstance().getMaxAdjustSubmitDate(ssmParam);

        if (!result.isCodeNormal()) {
            return;
        }

        List<Map> maxAdjustSubmitDateList = (List<Map>) result.getResultObject();
        if (maxAdjustSubmitDateList.size() == 0) {
            return;
        }

        Map record = maxAdjustSubmitDateList.get(0);
        Map<String, Object> ssmParamLastPhysicalInventory = new HashMap<String, Object>();
        ssmParamLastPhysicalInventory.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParamLastPhysicalInventory.put(DB_PARAM_TECH_TOC_CD, cMsg.techTocCd.getValue());
        ssmParamLastPhysicalInventory.put(DB_PARAM_PHYS_INVTY_NUM, (String) record.get(DB_COLUMN_PHYS_INVTY_NUM));
        // START 2018/12/11 S.Ohki [QC#28755,ADD]
        ssmParamLastPhysicalInventory.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        // END 2018/12/11 S.Ohki [QC#28755,ADD]

        S21SsmEZDResult resultLastPhysicalInventory = null;
        resultLastPhysicalInventory = NLCL0620Query.getInstance().getLastPhysicalInventory(ssmParamLastPhysicalInventory);

        if (!resultLastPhysicalInventory.isCodeNormal()) {
            return;
        }

        List<Map> lastPhysicalInventoryList = (List<Map>) resultLastPhysicalInventory.getResultObject();
        if (lastPhysicalInventoryList.size() == 0) {
            return;
        }

        Map recordLastPhysicalInventory = lastPhysicalInventoryList.get(0);
        String adjSubmtTs = (String) recordLastPhysicalInventory.get(DB_COLUMN_MAX_ADJ_SUBMT_TS);
        ZYPEZDItemValueSetter.setValue(cMsg.shipDt, adjSubmtTs.substring(0, 8));
        ZYPEZDItemValueSetter.setValue(cMsg.adjGrsAmt, (BigDecimal) recordLastPhysicalInventory.get(DB_COLUMN_TOTAL_ADJ_GRS_AMT));
        ZYPEZDItemValueSetter.setValue(cMsg.adjNetAmt, (BigDecimal) recordLastPhysicalInventory.get(DB_COLUMN_TOTAL_ADJ_NET_AMT));

    }

    /**
     * Check Technician
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     * @param glblCmpyCd String
     */
    private static boolean checkTechnician(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_TECH_TOC_CD, cMsg.techTocCd.getValue());
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));
        // START 2018/12/06 S.Ohki [QC#28128,MOD]
//        ssmParam.put(DB_PARAM_USER_ID, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
        if (funcList == null || !funcList.contains(FUNC_ID_ALL_USERS)) {
            ssmParam.put(DB_PARAM_USER_ID, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
        // END 2018/12/06 S.Ohki [QC#28128,MOD]

        S21SsmEZDResult result = null;
        result = NLCL0620Query.getInstance().getTechnicianName(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NLZM2278E, new String[] {cMsg.techTocCd.getValue()});
            cMsg.techTocCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.techTocCd.getValue()});
            return false;
        }

        List<Map> technicianList = (List<Map>) result.getResultObject();
        if (technicianList.size() == 0) {
            cMsg.setMessageInfo(NLZM2278E, new String[] {cMsg.techTocCd.getValue()});
            cMsg.techTocCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.techTocCd.getValue()});
            return false;
        }

        Map record = technicianList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.techNm, (String) record.get(DB_COLUMN_TECH_NM));

        return true;
    }

    /**
     * Submit
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     * @param glblCmpyCd String
     */
    public static void submit(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg, String glblCmpyCd) {

        if (!checkPhysicalInventoryControlName(cMsg, sMsg, glblCmpyCd)) {
            return;
        }

        // START 2018/12/11 S.Ohki [QC#28755,MOD]
//        if (!checkTechnician(cMsg, sMsg, glblCmpyCd)) {
//            return;
//        }

        if (ZYPCommonFunc.hasValue(cMsg.techTocCd)) {
        	if (!checkTechnician(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
        }
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
        	if (!checkLocation(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
        }
        // END 2018/12/11 S.Ohki [QC#28755,ADD]

        if (!checkOpenOrder(cMsg, sMsg, glblCmpyCd)) {
            return;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        //ssmParam.put(DB_PARAM_TECH_TOC_CD, cMsg.techTocCd.getValue());
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmParam.put(DB_PARAM_LOC_TP_CD, cMsg.varCharConstVal.getValue().split(VAR_CHAR_CONST_VAL_DELIM));
        // START 2018/12/11 S.Ohki [QC#28755,ADD]
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        // END 2018/12/11 S.Ohki [QC#28755,ADD]

        S21SsmEZDResult result = null;
        result = NLCL0620Query.getInstance().getTechnicianLocation(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NLCM0162E);
        }

        List<Map> technicianLocationList = (List<Map>) result.getResultObject();
        String piNumber = null;
        DecimalFormat df = new DecimalFormat("#");
        df.setMinimumIntegerDigits(15);
        df.setMaximumIntegerDigits(15);

        for (int i = 0; i < technicianLocationList.size(); i++) {

            Map record = technicianLocationList.get(i);

            BigDecimal physInvtyCtrlSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CTRL_SQ);

            if (i == 0) {
                piNumber = df.format(physInvtyCtrlSq);
            }

            PHYS_INVTY_CTRLTMsg inMsg = new PHYS_INVTY_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, physInvtyCtrlSq);
            ZYPEZDItemValueSetter.setValue(inMsg.whCd, (String) record.get(DB_COLUMN_INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, (String) record.get(DB_COLUMN_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.rtlSwhCd, (String) record.get(DB_COLUMN_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.locTpCd, (String) record.get(DB_COLUMN_LOC_TP_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlNm, cMsg.physInvtyCtrlNm);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyDt, cMsg.physInvtyDt);
            // START 2018/12/11 S.Ohki [QC#28755,MOD]
//            ZYPEZDItemValueSetter.setValue(inMsg.techTocCd, cMsg.techTocCd);
//            ZYPEZDItemValueSetter.setValue(inMsg.techNm, cMsg.techNm);

            String tecTocCd = (String) record.get(DB_COLUMN_TECH_TOC_CD);

            Map<String, Object> ssmParamTec = new HashMap<String, Object>();
            ssmParamTec.put(DB_PARAM_TECH_TOC_CD, tecTocCd);
            ssmParamTec.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParamTec.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));

            List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
            if (funcList == null || !funcList.contains(FUNC_ID_ALL_USERS)) {
                ssmParamTec.put(DB_PARAM_USER_ID, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
            }

            S21SsmEZDResult resultTec = null;
            resultTec = NLCL0620Query.getInstance().getTechnicianName(ssmParamTec);

            if (!resultTec.isCodeNormal()) {
            	cMsg.setMessageInfo(NLCM0229E);
                cMsg.rtlWhCd.setErrorInfo(1, NLCM0229E);
                return;
            }

            List<Map> technicianList = (List<Map>) resultTec.getResultObject();
            if (technicianList.size() == 0) {
            	cMsg.setMessageInfo(NLCM0229E);
                cMsg.rtlWhCd.setErrorInfo(1, NLCM0229E);
                return;
            }
            Map recordTec = technicianList.get(0);

            ZYPEZDItemValueSetter.setValue(inMsg.techTocCd, tecTocCd);
            ZYPEZDItemValueSetter.setValue(inMsg.techNm, (String) recordTec.get(DB_COLUMN_TECH_NM));
            // END 2018/12/11 S.Ohki [QC#28755,MOD]
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyStsCd, PHYS_INVTY_STS.SCHEDULED);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyStsNm, cMsg.physInvtyStsDescTxt_03);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntStsCd, PHYS_INVTY_CNT_STS.SCHEDULED);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyNum, piNumber);
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            S21FastTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_VALUE_PHYS_INVTY_CTRL});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return;
            }
        }

        cMsg.setMessageInfo(ZZM8100I);
    }

    /**
     * Check Physical Inventory Control Name
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     * @param glblCmpyCd String
     */
    private static boolean checkPhysicalInventoryControlName(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_CTRL_NM, cMsg.physInvtyCtrlNm.getValue());

        S21SsmEZDResult result = null;
        result = NLCL0620Query.getInstance().countPhysicalInventoryControlForName(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NLCM0143E, new String[] {cMsg.physInvtyCtrlNm.getValue()});
            cMsg.physInvtyCtrlNm.setErrorInfo(1, NLCM0143E, new String[] {cMsg.physInvtyCtrlNm.getValue()});
            return false;
        }

        List<Map> countList = (List<Map>) result.getResultObject();
        if (countList.size() == 0) {
            cMsg.setMessageInfo(NLCM0143E, new String[] {cMsg.physInvtyCtrlNm.getValue()});
            cMsg.physInvtyCtrlNm.setErrorInfo(1, NLCM0143E, new String[] {cMsg.physInvtyCtrlNm.getValue()});
            return false;
        }

        Map record = countList.get(0);
        BigDecimal count = (BigDecimal) record.get(DB_COLUMN_CNT);
        if (count.intValue() != 0) {
            cMsg.setMessageInfo(NLCM0143E, new String[] {cMsg.physInvtyCtrlNm.getValue()});
            cMsg.physInvtyCtrlNm.setErrorInfo(1, NLCM0143E, new String[] {cMsg.physInvtyCtrlNm.getValue()});
            return false;
        }

        return true;
    }

    /**
     * Check Open Order
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     * @param glblCmpyCd String
     */
    private static boolean checkOpenOrder(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_THRESHOLD_OPEN_ORDER_DAYS, cMsg.numConstVal.getValue());
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.TECH_REQUEST);
        ssmParam.put(DB_PARAM_PRCH_REQ_LINE_SUB_NUM, DB_PARAM_VALUE_EXCLUSION_LINE_SUB_NUM);
        ssmParam.put(DB_PARAM_OPEN_STS_FLG, ZYPConstant.FLG_ON_Y);
        ssmParam.put(DB_PARAM_PROCR_TP_CD_1, PROCR_TP.SUPPLIER);
        ssmParam.put(DB_PARAM_PROCR_TP_CD_2, PROCR_TP.WAREHOUSE);
        ssmParam.put(DB_PARAM_PHYS_INVTY_DT, cMsg.physInvtyDt.getValue());
        ssmParam.put(DB_PARAM_MAX_ROWNUM, sMsg.F.length());
        ssmParam.put(DB_PARAM_RQST_TECH_TOC_CD, cMsg.techTocCd.getValue());
        ssmParam.put(DB_PARAM_DEST_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        ssmParam.put(DB_PARAM_INSOURCED_PO, PRCH_REQ_LINE_TP.INSOURCED_PO);
        // START 2021/09/29 R.Azucena[QC#59216, MOD]
        // ssmParam.put(DB_PARAM_CHOICE_SHIP_CONFIRMATION, PRCH_REQ_SRC_TP.CHOICE_SHIP_CONFIRMATION);
        ssmParam.put("shipConfirmationCdArray", new String[] {PRCH_REQ_SRC_TP.CHOICE_SHIP_CONFIRMATION, PRCH_REQ_SRC_TP.MNX_SHIP_CONFIRMATION });
        // START 2021/09/29 R.Azucena[QC#59216, MOD]
        // QC#54986
        String[] inclPoOrdSrcCds = null;
        String varCharConst = ZYPCodeDataUtil.getVarCharConstValue("NLCL0620_INCL_PO_ORD_SRC_CD", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(varCharConst)) {
            inclPoOrdSrcCds = varCharConst.split(",");
        }
        String[] exclDsPoTpCds = null;
        varCharConst = ZYPCodeDataUtil.getVarCharConstValue("NLCL0620_EXCL_DS_PO_TP_CD", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(varCharConst)) {
            exclDsPoTpCds = varCharConst.split(",");
        }
        String[] exclPoStsCds = null;
        varCharConst = ZYPCodeDataUtil.getVarCharConstValue("NLCL0620_EXCL_PO_STS_CD", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(varCharConst)) {
            exclPoStsCds = varCharConst.split(",");
        }
        if (inclPoOrdSrcCds != null) {
            ssmParam.put("poOrdSrcCd", inclPoOrdSrcCds);
        }
        if (exclDsPoTpCds != null) {
            ssmParam.put("dsPoTpCd", exclDsPoTpCds);
        }
        if (exclPoStsCds != null) {
            ssmParam.put("poStsCd", exclPoStsCds);
        }
        ssmParam.put("poQlfyCdTech", PO_QLFY.TECH_REQUEST);
        ssmParam.put("printed", RWS_STS.PRINTED);
        ssmParam.put("receiving", RWS_STS.RECEIVING);
        ssmParam.put("cancel", PRCH_REQ_LINE_STS.CANCELLED);
        ssmParam.put("shipped", ZYPCodeDataUtil.getName(SHPG_STS.class, glblCmpyCd, SHPG_STS.SHIPPED));
        // QC#55863
        ssmParam.put("entered", ACCT_INV_STS.ENTERED);

        S21SsmEZDResult result = null;
        result = NLCL0620Query.getInstance().countOpenOrder(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NLCM0144E, new String[] {String.valueOf(cMsg.numConstVal.getValueInt())});
            return false;
        }

        List<Map> countList = (List<Map>) result.getResultObject();
        if (countList.size() == 0) {
            cMsg.setMessageInfo(NLCM0144E, new String[] {String.valueOf(cMsg.numConstVal.getValueInt())});
            return false;
        }

        Map record = countList.get(0);
        BigDecimal count = (BigDecimal) record.get(DB_COLUMN_CNT_OPEN_ORDR);
        if (count.intValue() > 0) {
            cMsg.setMessageInfo(NLCM0144E, new String[] {String.valueOf(cMsg.numConstVal.getValueInt())});
            return false;
        }

        return true;
    }

    /**
     * Get Physical Inventory Status Name
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     * @param glblCmpyCd String
     */
    public static void getPhysicalInventoryStatusName(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg, String glblCmpyCd) {

        PHYS_INVTY_STSTMsg tMsg = (PHYS_INVTY_STSTMsg) ZYPCodeDataUtil.findByCode(PHYS_INVTY_STS.class, glblCmpyCd, PHYS_INVTY_STS.SCHEDULED);
        ZYPEZDItemValueSetter.setValue(cMsg.physInvtyStsDescTxt_03, tMsg.physInvtyStsDescTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.physInvtyStsDescTxt_03, tMsg.physInvtyStsDescTxt);

    }

    /**
     * Search Technician
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     * @param glblCmpyCd String
     */
    public static void searchTechnician(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_TECH_TOC_CD, cMsg.techTocCd.getValue());
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));
        // START 2018/12/06 S.Ohki [QC#28128,MOD]
//        ssmParam.put(DB_PARAM_USER_ID, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
        if (funcList == null || !funcList.contains(FUNC_ID_ALL_USERS)) {
            ssmParam.put(DB_PARAM_USER_ID, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
        // END 2018/12/06 S.Ohki [QC#28128,MOD]

        S21SsmEZDResult result = null;
        result = NLCL0620Query.getInstance().getTechnicianName(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.techTocCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.techTocCd.getValue()});
            return;
        }

        List<Map> technicianList = (List<Map>) result.getResultObject();
        if (technicianList.size() == 0) {
            cMsg.techTocCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.techTocCd.getValue()});
            return;
        }
        Map record = technicianList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.techNm, (String) record.get(DB_COLUMN_TECH_NM));

    }

    // START 2018/12/11 S.Ohki [QC#28755,ADD]
    /**
     * Check Location
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     * @param glblCmpyCd String
     */
    private static boolean checkLocation(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
        if (funcList == null || !funcList.contains(FUNC_ID_ALL_USERS)) {
            ssmParam.put(DB_PARAM_USER_ID, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        S21SsmEZDResult result = null;
        result = NLCL0620Query.getInstance().getLocationName(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NLZM2278E, new String[] {cMsg.rtlWhCd.getValue()});
            cMsg.rtlWhCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.rtlWhCd.getValue()});
            return false;
        }

        List<Map> locationList = (List<Map>) result.getResultObject();
        if (locationList.size() == 0) {
            cMsg.setMessageInfo(NLZM2278E, new String[] {cMsg.rtlWhCd.getValue()});
            cMsg.rtlWhCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.rtlWhCd.getValue()});
            return false;
        }

        Map record = locationList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, (String) record.get(DB_COLUMN_RTL_WH_NM));

        return true;
    }

    /**
     * Search Location
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     * @param glblCmpyCd String
     */
    public static void searchLocation(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
        if (funcList == null || !funcList.contains(FUNC_ID_ALL_USERS)) {
            ssmParam.put(DB_PARAM_USER_ID, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        S21SsmEZDResult result = null;
        result = NLCL0620Query.getInstance().getLocationName(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.rtlWhCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.rtlWhCd.getValue()});
            return;
        }

        List<Map> locationList = (List<Map>) result.getResultObject();
        if (locationList.size() == 0) {
            cMsg.rtlWhCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.rtlWhCd.getValue()});
            return;
        }
        Map record = locationList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, (String) record.get(DB_COLUMN_RTL_WH_NM));
    }
    // END 2018/12/11 S.Ohki [QC#28755,ADD]

    // START 2019/12/17 T.Ogura [QC#54986,ADD]
    /**
     * Search
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     * @param glblCmpyCd String
     */
    public static void download(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg, String glblCmpyCd) {

        if (ZYPCommonFunc.hasValue(cMsg.techTocCd)) {
            if (!checkTechnician(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
        }
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
            if (!checkLocation(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
        }
    }
    // END   2019/12/17 T.Ogura [QC#54986,ADD]

    // START 2019/12/17 T.Ogura [QC#54986,ADD]
    /**
     * writeCsvFile
     * @param cMsg NLCL0620CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NLCL0620CMsg cMsg, ResultSet rs) throws SQLException {

        NLCL0620F00FMsg fMsg = new NLCL0620F00FMsg();
        ZYPCSVOutFile fileWriter = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        /** CSV Header */
        String[] csvHeader = {"Technician Code", "Technician Name", "Location Code", "Location Name", "Scheduled Date",
                "Part Request#", "Part Request Type", "Item#", "Item Description", "Shipment#", "Shipment Date",
                "RWS#", "Warehouse/Supplier", "Tracking Number", "Shipment Status", "Age(days)"};
        fileWriter.writeHeader(csvHeader);

        int cnt = 0;
        while (rs.next()) {
            writeCsvLine(cMsg, fMsg, rs);
            fileWriter.write();

            cnt++;
            fMsg.clear();
        }
        fileWriter.close();
    }
    // END   2019/12/17 T.Ogura [QC#54986,ADD]

    // START 2019/12/17 T.Ogura [QC#54986,ADD]
    /**
     * writeCsvLine
     * @param cMsg NLCL0620CMsg
     * @param fMsg NLCL0620F00FMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    private static void writeCsvLine(NLCL0620CMsg cMsg, NLCL0620F00FMsg fMsg, ResultSet rs) throws SQLException {
        ZYPEZDItemValueSetter.setValue(fMsg.techTocCd, rs.getString("RQST_TECH_TOC_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.techNm, rs.getString("RQST_TECH_TOC_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCd, rs.getString("DEST_RTL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm, rs.getString("DEST_RTL_WH_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.physInvtyDt, rs.getString("PHYS_INVTY_DT"));
        ZYPEZDItemValueSetter.setValue(fMsg.prchReqNum, rs.getString("PRCH_REQ_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.prchReqTpDescTxt, rs.getString("PRCH_REQ_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.rwsRefNum, rs.getString("SO_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.shipDt, rs.getString("SHIP_DATE"));
        ZYPEZDItemValueSetter.setValue(fMsg.rwsNum, rs.getString("RWS_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt, rs.getString("WAREHOUSE_OR_SUPPLIER"));
        ZYPEZDItemValueSetter.setValue(fMsg.proNum, rs.getString("PRO_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.shpgStsDescTxt, rs.getString("SHPG_STS_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt, rs.getString("AGE"));
    }
    // END   2019/12/17 T.Ogura [QC#54986,ADD]

}
