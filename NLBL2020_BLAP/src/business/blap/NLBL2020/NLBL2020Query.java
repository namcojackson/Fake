/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL2020;

import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.blap.NLBL2020.constant.NLBL2020Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 06/30/2016   CSAI            K.Lee           Update          Configuration Change
 * 07/14/2016   CSAI            Y.Imazu         Update          QC#11900
 * 07/24/2017   CITS            Y.Iwasaki       Update          QC#20030
 * 02/16/2018   CITS            T.Tokutomi      Update          QC#18367
 * 01/07/2020   Fujitsu         R.Nakamura      Update          QC#55308
 * 01/31/2020   Fujitsu         T.Ogura         Update          QC#55570
 * 04/04/2022   CITS            R.Azucena       Update          QC#59802
 * 08/08/2022   CITS            R.Azucena       Update          QC#60416
 * 12/07/2022   Hitachi         T.Kuroda        Update          QC#60810
 * 07/31/2023   Hitachi         M.Kikushima     Update          QC#61677
 *</pre>
 */
public class NLBL2020Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLBL2020Query MY_INSTANCE = new NLBL2020Query();

    /**
     * Constructor.
     */
    public NLBL2020Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL2020Query
     */
    public static NLBL2020Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get S21SsmBatchClient for CSV Download
     * @return S21SsmBatchClient
     */
    private S21SsmBatchClient getSsmBatchClient() {
        return S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * getSceOrdTypeList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSceOrdTypeList(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSceOrdTypeList", ssmParam);
    }

    // Add Start 2020/01/07 QC#55308
    /**
     * Get Sub Warehouse Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHName(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObject("getWHName", ssmParam);
    }
    // Add End 2020/01/07 QC#55308

    /**
     * Get Sub Warehouse Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSWHName(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObject("getSWHName", ssmParam);
    }

    /**
     * Get Ship to Cust Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCustName(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObject("getShipToCustName", ssmParam);
    }

    /**
     * countWarehousePermission
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPermissionWhList(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPermissionWhList", ssmParam);
    }

    /**
     * getSoSerialList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSoSerialList(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSoSerialList", ssmParam);
    }

    /**
     * searchSoList
     * @param ssmParam Map<String, Object>
     * @param sMsg NLBL2020SMsg
     * @return S21SsmEZDResult
     */
    public void searchSoList(Map<String, Object> ssmParam, NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {
        getSsmBatchClient().queryObject("searchSoList", ssmParam, new SoResult(cMsg, sMsg));
    }

    /**
     * getSoCancelAvalFlg
     * @param ssmParam Map<String, String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSoCancelAvalFlg(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObject("getSoCancelAvalFlg", ssmParam);
    }

    /**
     * countSetItem
     * @param ssmParam Map<String, String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countSetItem(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObject("countSetItem", ssmParam);
    }

    /**
     * getSvcMachMstrPk
     * @param ssmParam Map<String, String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMachMstrPk(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSvcMachMstrPk", ssmParam);
    }

    /**
     * searchPickList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPickList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchPickList", ssmParam);
    }

    /**
     * getSoSum
     * @param ssmParam Map<String, String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSoSum(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObject("getSoSum", ssmParam);
    }

    /**
     * getCarrCdList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrCdList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCarrCdList", ssmParam);
    }

    /**
     * getAcctCarrCnt
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcctCarrCnt(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getAcctCarrCnt", ssmParam);
    }

    /**
     * getCarrSvcLvlCdList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrSvcLvlCdList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCarrSvcLvlCdList", ssmParam);
    }

    /**
     * getRtlWhList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWhList(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getRtlWhList", queryParams);
    }

    /**
     * getRtlSwhList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlSwhList(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getRtlSwhList", queryParams);
    }

    /**
     * getCarrList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrList(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getCarrList", queryParams);
    }

    /**
     * getMdseList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseList(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getMdseList", queryParams);
    }

    /**
     * getShipToList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToList(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getShipToList", queryParams);
    }

    /**
     * getInvtyLocCnt
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvtyLocCnt(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObject("getInvtyLocCnt", queryParams);
    }

    /**
     * getOrdTakeMdseList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdTakeMdseList(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getOrdTakeMdseList", queryParams);
    }

    /**
     * getSvcMachMstr
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMachMstr(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getSvcMachMstr", queryParams);
    }

    /**
     * getSvcMachMstr
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRwsNumListBySo(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getRwsNumListBySo", queryParams);
    }

    /**
     * getSvcMachMstr
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfigCmptList(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getConfigCmptList", queryParams);
    }

    /**
     * getShpgInstnCmntTxt
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShpgInstnCmntTxt(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getShpgInstnCmntTxt", queryParams);
    }

    /**
     * getBolRelnCnt
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBolRelnCnt(Map<String, String> queryParams) {
        return getSsmEZDClient().queryObject("getBolRelnCnt", queryParams);
    }

    private static class SoResult extends S21SsmVoidResultSetHandlerSupport {

        /** */
        private NLBL2020SMsg sMsg;

        private NLBL2020CMsg cMsg;

        public SoResult(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {
            this.sMsg = sMsg;
            this.cMsg = cMsg;
        }

        protected void doProcessQueryResult(ResultSet rs) throws SQLException {

            rs.last();
            int cnt = rs.getRow();

            if (cnt > sMsg.A.length()) {

                cMsg.setMessageInfo(NLBL2020Constant.NZZM0001W);
                cnt = sMsg.A.length();
            }

            cMsg.xxPageShowOfNum_A.setValue(cnt);

            if (cnt == 0) {
                cMsg.xxPageShowFromNum_A.clear();
                cMsg.xxPageShowToNum_A.clear();
                cMsg.xxPageShowOfNum_A.clear();
                cMsg.xxPageShowFromNum_B.clear();
                cMsg.xxPageShowToNum_B.clear();
                cMsg.xxPageShowOfNum_B.clear();
                cMsg.setMessageInfo(NLBL2020Constant.NZZM0000E);
                return;
            }

            int rowCnt = 0;
            rs.first();
            do {

                if (rs.getRow() > sMsg.A.length()) {
                    cMsg.setMessageInfo(NLBL2020Constant.NZZM0001W);
                    break;
                }

                NLBL2020_ASMsg aSMsg = sMsg.A.no(rowCnt);

                ZYPEZDItemValueSetter.setValue(aSMsg.trxHdrNum_A1, rs.getString("SRC_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.dplyLineNum_A1, rs.getString("DPLY_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.trxLineNum_A1, rs.getString("SRC_ORD_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.trxLineSubNum_A1, rs.getString("SRC_ORD_LINE_SUB_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.soNum_A1, rs.getString("SO_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.soSlpNum_A1, rs.getString("SO_SLP_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.sceOrdTpNm_A1, rs.getString("SCE_ORD_TP_NM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.rtlWhNm_A1, rs.getString("SHIP_FROM_RTL_WH_NM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shipFromRtlSwhCd_A1, rs.getString("SHIP_FROM_RTL_SWH_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.dsSoLineStsDescTxt_A1, rs.getString("DS_SO_LINE_STS_DESC_TXT"));
                // START 07/31/2023 M.Kikushima [QC#61677, ADD]
                ZYPEZDItemValueSetter.setValue(aSMsg.schdCoordStsDescTxt_A1, rs.getString("SCHD_COORD_STS_DESC_TXT"));
                // END 07/31/2023 M.Kikushima [QC#61677, ADD]
                ZYPEZDItemValueSetter.setValue(aSMsg.rmvConfigFlg_A1, rs.getString("RMV_CONFIG_FLG"));
                ZYPEZDItemValueSetter.setValue(aSMsg.svcConfigMstrPk_A1, rs.getBigDecimal("SHIP_SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(aSMsg.pickSvcConfigMstrPk_A1, rs.getBigDecimal("PICK_SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(aSMsg.mdseCd_A1, rs.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.mdseDescShortTxt_A1, rs.getString("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(aSMsg.backOrdImpctTpDescTxt_A1, rs.getString("BACK_ORD_IMPCT_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(aSMsg.fromStkStsCd_A1, rs.getString("FROM_STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shpgQty_A1, rs.getBigDecimal("SHPG_QTY"));
                ZYPEZDItemValueSetter.setValue(aSMsg.basePkgUomCd_A1, rs.getString("DPLY_PKG_UOM_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.inEachQty_A1, rs.getBigDecimal("UOM_EACH_QTY"));
                ZYPEZDItemValueSetter.setValue(aSMsg.pickConfQty_A1, rs.getBigDecimal("PICK_CONF_QTY"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shipQty_A1, rs.getBigDecimal("SHIP_QTY"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shpgBalQty_A1, rs.getBigDecimal("SHPG_BAL_QTY"));
                ZYPEZDItemValueSetter.setValue(aSMsg.proNum_A1, rs.getString("PRO_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.totFrtAmt_A1, rs.getBigDecimal("TOT_FRT_AMT"));
                ZYPEZDItemValueSetter.setValue(aSMsg.rddDt_A1, rs.getString("RDD_DT"));
                ZYPEZDItemValueSetter.setValue(aSMsg.rqstRcvDt_A1, rs.getString("RQST_RCV_DT"));
                ZYPEZDItemValueSetter.setValue(aSMsg.rqstRcvTm_A1, rs.getString("RQST_RCV_TM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.pktStsTs_A1, rs.getString("PKT_STS_TS"));
                ZYPEZDItemValueSetter.setValue(aSMsg.pktStsTs_A2, rs.getString("PKT_PACK_STS_TS"));
                ZYPEZDItemValueSetter.setValue(aSMsg.soCratTs_A1, rs.getString("SO_CRAT_TS"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shipDtTmTs_A1, rs.getString("SHIP_DT_TM_TS"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shpgSvcLvlDescTxt_A1, rs.getString("SHPG_SVC_LVL_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(aSMsg.carrCd_A1, rs.getString("CARR_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.carrNm_A1, rs.getString("CARR_NM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.carrCd_A2, rs.getString("RQST_CARR_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.carrNm_A2, rs.getString("RQST_CARR_NM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.carrAcctNum_A1, rs.getString("CARR_ACCT_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.tocNm_A1, rs.getString("TOC_NM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.toInvtyLocCd_A1, rs.getString("SHIP_TO_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shipToLocNm_A1, rs.getString("SHIP_TO_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shipToCtyAddr_A1, rs.getString("SHIP_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shipToStCd_A1, rs.getString("SHIP_TO_ST_CD"));
                // QC#18367 Add.
                ZYPEZDItemValueSetter.setValue(aSMsg.shipToCtryCd_A1, rs.getString("SHIP_TO_CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shipToCtacPsnNm_A1, rs.getString("SHIP_TO_CTAC_PSN_NM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxScrItem19Txt_A1, rs.getString("PACK_SLP_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxYesNoCd_A2, rs.getString("SHPG_ORD_DELY_INSTN_FLG"));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxDsMultMsgDplyTxt_A1, rs.getString("SHPG_INSTN_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(aSMsg.actlShpgSvcLvlCd_A1, rs.getString("ACTL_SVC_LVL_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.sceOrdTpCd_AH, rs.getString("SCE_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.trxSrcTpCd_AH, rs.getString("TRX_SRC_TP_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.soPrintFlg_AH, rs.getString("SO_PRINT_FLG"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shipFlg_AH, rs.getString("SHIP_FLG"));
                ZYPEZDItemValueSetter.setValue(aSMsg.serNumTakeFlg_AH, rs.getString("SER_NUM_TAKE_FLG"));
                ZYPEZDItemValueSetter.setValue(aSMsg.instlBaseCtrlFlg_AH, rs.getString("INSTL_BASE_CTRL_FLG"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shpgBalQty_AH, rs.getBigDecimal("SHPG_BAL_QTY"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shipToCustAcctCd_AH, rs.getString("SHIP_TO_CUST_ACCT_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shipFromRtlWhCd_AH, rs.getString("SHIP_FROM_RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.invtyLocCd_AH, rs.getString("SHIP_FROM_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.trxHdrNum_AH, rs.getString("TRX_HDR_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.trxLineNum_AH, rs.getString("TRX_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.trxLineSubNum_AH, rs.getString("TRX_LINE_SUB_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.trxHdrNum_MM, rs.getString("SRC_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.trxLineNum_MM, rs.getString("SRC_ORD_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.trxLineSubNum_MM, rs.getString("SRC_ORD_LINE_SUB_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.soLineOpenFlg_AH, rs.getString("SO_LINE_OPEN_FLG"));
                ZYPEZDItemValueSetter.setValue(aSMsg.toStkStsCd_AH, rs.getString("TO_STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.dsSoLineStsCd_AH, rs.getString("DS_SO_LINE_STS_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.setMdseCd_AH, rs.getString("SET_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxExstFlg_MS, rs.getString("MAN_SEND_RQST_FLG"));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxExstFlg_SR, rs.getString("SEND_RQST_FLG"));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxExstFlg_C1, rs.getString("DS_COND_CONST_VAL_TXT_01"));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxExstFlg_C4, rs.getString("DS_COND_CONST_VAL_TXT_04"));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxExstFlg_C5, rs.getString("DS_COND_CONST_VAL_TXT_05"));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxExstFlg_C6, rs.getString("DS_COND_CONST_VAL_TXT_06"));
                ZYPEZDItemValueSetter.setValue(aSMsg.invtyAcctCd_A1, rs.getString("INVTY_ACCT_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsg.mdlId_A1, rs.getBigDecimal("MDL_ID"));
                ZYPEZDItemValueSetter.setValue(aSMsg.ezUpTime_SO, rs.getString("SO_EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(aSMsg.ezUpTimeZone_SO, rs.getString("SO_EZUPTIMEZONE"));
                ZYPEZDItemValueSetter.setValue(aSMsg.ezUpTime_SD, rs.getString("SOD_EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(aSMsg.ezUpTimeZone_SD, rs.getString("SOD_EZUPTIMEZONE"));
                ZYPEZDItemValueSetter.setValue(aSMsg.trxHdrNum_HI, rs.getString("SRC_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.dplyLineNum_HI, rs.getString("DPLY_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.soNum_HI, rs.getString("SO_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.soSlpNum_HI, rs.getString("SO_SLP_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.shpgSvcLvlCd_A1, rs.getString("SHPG_SVC_LVL_CD"));
                // Who Column
                ZYPEZDItemValueSetter.setValue(aSMsg.xxRecHistCratTs_A1, rs.getString("XX_REC_HIST_CRAT_TS"));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxRecHistCratByNm_A1, rs.getString("XX_REC_HIST_CRAT_BY_NM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxRecHistUpdTs_A1, rs.getString("XX_REC_HIST_UPD_TS"));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxRecHistUpdByNm_A1, rs.getString("XX_REC_HIST_UPD_BY_NM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxRecHistTblNm_A1, rs.getString("XX_REC_HIST_TBL_NM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.wmsDropFlg_A1, rs.getString("WMS_DROP_FLG"));
                ZYPEZDItemValueSetter.setValue(aSMsg.whOwnrCd_A1, rs.getString("WH_OWNR_CD"));

                // Set Ship Qty to Open Line
                if (ZYPConstant.FLG_ON_Y.equals(aSMsg.soLineOpenFlg_AH.getValue())) {

                    ZYPEZDItemValueSetter.setValue(aSMsg.shipQty_A1, aSMsg.shpgBalQty_A1.getValue());
                }

                // Convert TimeStamp
                ZYPEZDItemValueSetter.setValue(aSMsg.xxTsDsp19Txt_A1, convDplyDtTm(aSMsg.soCratTs_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxTsDsp19Txt_A2, convDplyDtTm(aSMsg.pktStsTs_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxTsDsp19Txt_A5, convDplyDtTm(aSMsg.pktStsTs_A2.getValue()));
                ZYPEZDItemValueSetter.setValue(aSMsg.xxTsDsp19Txt_A3, convDplyDtTm(aSMsg.shipDtTmTs_A1.getValue()));

                if (ZYPCommonFunc.hasValue(aSMsg.rqstRcvDt_A1)) {

                    if (ZYPCommonFunc.hasValue(aSMsg.rqstRcvTm_A1)) {

                        String needByDtTs = S21StringUtil.concatStrings(aSMsg.rqstRcvDt_A1.getValue(), aSMsg.rqstRcvTm_A1.getValue(), "00");
                        ZYPEZDItemValueSetter.setValue(aSMsg.xxTsDsp19Txt_A4, convDplyDtTm(needByDtTs));

                    } else {

                        String needByDtTs = S21StringUtil.concatStrings(aSMsg.rqstRcvDt_A1.getValue(), "000000");
                        ZYPEZDItemValueSetter.setValue(aSMsg.xxTsDsp19Txt_A4, convDplyDtTm(needByDtTs));
                    }
                }

                // Serial
                Clob clob = rs.getClob("SER_NUM");

                if (clob != null) {

                    int maxLength = aSMsg.getAttr("serNum_A1").getDigit();
                    String serNum = clob.getSubString(1L, maxLength);

                    if (aSMsg.shipQty_A1.getValue().abs().intValue() == 1) {

                        ZYPEZDItemValueSetter.setValue(aSMsg.serNum_A1, serNum);

                    } else if (aSMsg.shipQty_A1.getValue().abs().intValue() > 1) {

                        StringBuilder sb = new StringBuilder();
                        boolean setSerVal = false;
                        int serCnt = 0;

                        serNum = clob.getSubString(1L, (int) clob.length());

                        String[] serArray = serNum.split(",");

                        for (String ser : serArray) {

                            ZYPEZDItemValueSetter.setValue(aSMsg.serNum_AH.no(serCnt), ser);

                            if (ZYPCommonFunc.hasValue(aSMsg.serNum_AH.no(serCnt))) {

                                if (sb.length() > 0) {
                                    sb.append(",");
                                }

                                sb.append(aSMsg.serNum_AH.no(serCnt).getValue());

                                if (sb.length() <= maxLength) {

                                    ZYPEZDItemValueSetter.setValue(aSMsg.serNum_A1, sb.toString());

                                } else {

                                    ZYPEZDItemValueSetter.setValue(aSMsg.serNum_A1, sb.toString().substring(0, maxLength));

                                }

                                setSerVal = true;

                            } else {

                                if (!setSerVal) {

                                    aSMsg.serNum_A1.clear();

                                }
                            }

                            serCnt++;
                        }
                    }
                }

                rowCnt++;
                sMsg.A.setValidCount(rowCnt);

            } while (rs.next());

            return;
        }
    }

    /**
     * convDplyDtTm
     * @param timeStamp String
     * @return String
     */
    public static String convDplyDtTm(String timeStamp) {

        if (ZYPCommonFunc.hasValue(timeStamp)) {

            return ZYPDateUtil.formatEzd14ToDisp(timeStamp);
        }

        return null;
    }
    
    /**
     * getExportPdfData
     * QC#18367 Add method.
     * @param ssmParam Map<String, String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getExportPdfData(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObjectList("getExportPdfData", ssmParam);
    }

    // START 01/31/2020 T.Ogura [QC#55570,ADD]
    /**
     * getSellToCustCd
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSellToCustCd(String glblCmpyCd, String shipToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("shipToCustCd", shipToCustCd);
        return  getSsmEZDClient().queryObject("getSellToCustCd", params);
    }
    // END   01/31/2020 T.Ogura [QC#55570,ADD]

    // START 01/31/2020 T.Ogura [QC#55570,ADD]
    /**
     * getBillToLocation
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToLocation(String glblCmpyCd, String billToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("billToCustCd", billToCustCd);
        return getSsmEZDClient().queryObjectList("getBillToLocation", params);
    }
    // END   01/31/2020 T.Ogura [QC#55570,ADD]

    // START 2022/04/04 R.Azucena [QC#59802, ADD]
    // START 2022/08/08 R.Azucena [QC#60416, DEL]
    /**
     * getAvailSingleQty
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    // public S21SsmEZDResult getAvailSingleQty(Map<String, Object> ssmParam) {
    //     return getSsmEZDClient().queryObject("getAvailSingleQty", ssmParam);
    // }
    // END 2022/08/08 R.Azucena [QC#60416, DEL]
    // END 2022/04/04 R.Azucena [QC#59802, ADD]

    // START 12/07/2022 T.Kuroda [QC#60810, ADD]
    /**
     * getCountWMSWarehouseList
     * @param ssmParam Map<String, String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCountWMSWarehouseList(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObject("getCountWMSWarehouseList", ssmParam);
    }
    // END   12/07/2022 T.Kuroda [QC#60810, ADD]
}
