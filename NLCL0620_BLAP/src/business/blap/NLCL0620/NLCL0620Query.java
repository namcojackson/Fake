/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0620;

import static business.blap.NLCL0620.constant.NLCL0620Constant.BIZ_APP_ID;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_CMSG;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_DEST_RTL_WH_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_INSOURCED_PO;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_MAX_ROWNUM;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_OPEN_STS_FLG;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_PHYS_INVTY_DT;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_PRCH_REQ_LINE_SUB_NUM;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_PRCH_REQ_REC_TP_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_PROCR_TP_CD_1;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_PROCR_TP_CD_2;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_RQST_TECH_TOC_CD;
import static business.blap.NLCL0620.constant.NLCL0620Constant.DB_PARAM_VALUE_EXCLUSION_LINE_SUB_NUM;
import static business.blap.NLCL0620.constant.NLCL0620Constant.FETCH_SIZE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.blap.NLCL0620.common.NLCL0620CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_QLFY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NLCL0620 Tech PI Entry
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/02/2016   CITS         Makoto Okigami     Create          N/A
 * 12/17/2019   Fujitsu         T.Ogura         Update          QC#54986
 * 02/12/2020   CITS            K.Ogino         Update          QC#55863
 * 09/29/2021   CITS            R.Azucena       Update          QC#59216
 *</pre>
 */
public final class NLCL0620Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL0620Query MY_INSTANCE = new NLCL0620Query();

    /**
     * Constructor.
     */
    private NLCL0620Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLCL0620Query
     */
    public static NLCL0620Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @param sMsg NLCL0620SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NLCL0620SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }

    /**
     * Get Technician Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTechnicianName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getTechnicianName", ssmParam);
    }

    // START 2018/12/11 S.Ohki [QC#28755,ADD]
    /**
     * Get Location Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocationName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getLocationName", ssmParam);
    }
    // END 2018/12/11 S.Ohki [QC#28755,ADD]

    /**
     * Get Max Adjust Submit Date
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxAdjustSubmitDate(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getMaxAdjustSubmitDate", ssmParam);
    }

    /**
     * Get Last Physical Inventory
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLastPhysicalInventory(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getLastPhysicalInventory", ssmParam);
    }

    /**
     * Count Physical Inventory Control For Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countPhysicalInventoryControlForName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("countPhysicalInventoryControlForName", ssmParam);
    }

    /**
     * Count Open Order
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countOpenOrder(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("countOpenOrder", ssmParam);
    }

    /**
     * Get Technician Location
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTechnicianLocation(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getTechnicianLocation", ssmParam);
    }

    // START 2019/12/17 T.Ogura [QC#54986,ADD]
    /**
     * createCSV
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     * @param glblCmpyCd String
     */
    public void createCSV(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(DB_PARAM_CMSG, cMsg);
        params.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        params.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.TECH_REQUEST);
        params.put(DB_PARAM_PRCH_REQ_LINE_SUB_NUM, DB_PARAM_VALUE_EXCLUSION_LINE_SUB_NUM);
        params.put(DB_PARAM_OPEN_STS_FLG, ZYPConstant.FLG_ON_Y);
        params.put(DB_PARAM_PROCR_TP_CD_1, PROCR_TP.SUPPLIER);
        params.put(DB_PARAM_PROCR_TP_CD_2, PROCR_TP.WAREHOUSE);
        params.put(DB_PARAM_PHYS_INVTY_DT, cMsg.physInvtyDt.getValue());
        params.put(DB_PARAM_MAX_ROWNUM, sMsg.F.length());
        params.put(DB_PARAM_RQST_TECH_TOC_CD, cMsg.techTocCd.getValue());
        params.put(DB_PARAM_DEST_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        params.put(DB_PARAM_INSOURCED_PO, PRCH_REQ_LINE_TP.INSOURCED_PO);
        // START 2021/09/29 R.Azucena[QC#59216, MOD]
        // params.put(DB_PARAM_CHOICE_SHIP_CONFIRMATION, PRCH_REQ_SRC_TP.CHOICE_SHIP_CONFIRMATION);
        params.put("shipConfirmationCdArray", new String[] {PRCH_REQ_SRC_TP.CHOICE_SHIP_CONFIRMATION, PRCH_REQ_SRC_TP.MNX_SHIP_CONFIRMATION });
        // START 2021/09/29 R.Azucena[QC#59216, MOD]

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
            params.put("poOrdSrcCd", inclPoOrdSrcCds);
        }
        if (exclDsPoTpCds != null) {
            params.put("dsPoTpCd", exclDsPoTpCds);
        }
        if (exclPoStsCds != null) {
            params.put("poStsCd", exclPoStsCds);
        }
        params.put("poQlfyCdTech", PO_QLFY.TECH_REQUEST);
        params.put("printed", RWS_STS.PRINTED);
        params.put("receiving", RWS_STS.RECEIVING);
        params.put("cancel", PRCH_REQ_LINE_STS.CANCELLED);
        params.put("shipped", ZYPCodeDataUtil.getName(SHPG_STS.class, glblCmpyCd, SHPG_STS.SHIPPED));
        // QC#55863
        params.put("entered", ACCT_INV_STS.ENTERED);
        // QC#54986 End

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            String csvFileNm = ZYPCSVOutFile.createCSVOutFileNm(BIZ_APP_ID);
            cMsg.xxFileData.setTempFilePath(null, csvFileNm, ".csv");

            stmtSelect = ssmLLClient.createPreparedStatement("getDownloadList", params, execParam);
            rs = stmtSelect.executeQuery();

            NLCL0620CommonLogic.writeCsvFile(cMsg, rs);
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }
    // END   2019/12/17 T.Ogura [QC#54986,ADD]

}
