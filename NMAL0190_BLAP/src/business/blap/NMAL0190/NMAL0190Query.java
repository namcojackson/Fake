/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0190;

import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_CONST_DISABLED;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_CONST_NEW;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_CONST_SUDP;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_PRM_DESC;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_PRM_EZINTIME;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_PRM_ITEMNUMBER;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_PRM_MAX_INVTY_QTY;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_PRM_MRP_ENBL_FLG;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_PRM_RELN_MDSE_CD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_PRM_RELN_TP_NM;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_PRM_ROP_QTY;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_PRM_RTL_SWH_NM;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_PRM_RTL_WH_NM;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_PRM_SUPD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_RPM_CART_DT;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_RPM_MDSE_CD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.NZZM0001W;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_CONST_COMPATIBLE;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_CSV_RELN;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_CSV_REV;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_CSV_SUPD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_MAX_COUNT;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_PRM_COMPATIBLE;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_PRM_FORWARD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_PRM_GLBLCMPYCD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_PRM_ITEM_NUMBER;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_PRM_MDSE_CD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_PRM_MDSE_ITEM_RELN_TP_CD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_PRM_MRP_ENBLE_FLG;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_PRM_RELN_MDSE_CD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_PRM_ROWNUM;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_PRM_SUPD_FROM_MDSE_CD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.SQL_PRM_SUPD_RELN_STAGE_PK;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL0190.common.NMAL0190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL0190Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         T.Arima         Create          N/A
 * 2016/02/23   CITS            S.Tanikawa       Update          QC#2669
 * 2016/03/24   CITS            S.Tanikawa       Update          QC#5660
 * 2020/12/17   CITS            J.Evangelista   Update          QC#57542
 *</pre>
 */
public final class NMAL0190Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL0190Query MY_INSTANCE = new NMAL0190Query();

    /**
     * Private constructor
     */
    private NMAL0190Query() {
        super();
    }

    /**
     * Get the NMAL0190Query instance.
     * @return NMAL0190Query instance
     */
    public static NMAL0190Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NMAL0190CMsg
     * @param glblMsg NMAL0190SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchKeyRec(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(SQL_PRM_GLBLCMPYCD, getGlobalCompanyCode());
        params.put(SQL_PRM_SUPD_RELN_STAGE_PK, bizMsg.supdRelnStagePk_P.getValue());
        params.put(SQL_PRM_ROWNUM, glblMsg.A.length());

        // UPDATE START 2016/03/04 QC#2669
        // return getSsmEZDClient().queryEZDMsgArray(SQL_ID_SEARCH, params, glblMsg.A);
//        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("seachKeyRec", params);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("seachKeyRec", params);
        return ssmResult;
        // UPDATE END 2016/03/04 QC#2669

    }

    // ADD START 2016/03/04 QC#2669
    /**
     * getSupdReln
     * @param sFromMdseCd String SUPD_FROM_MDSE_CD
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupdReln(String sFromMdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(SQL_PRM_GLBLCMPYCD, getGlobalCompanyCode());
        params.put(SQL_PRM_SUPD_FROM_MDSE_CD, sFromMdseCd);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getSupdRelnList", params);

        return ssmResult;
    }

    /**
     * getMdseItemFlipSet
     * @param mdseCd String
     * @param relnMdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseItemFlipSet(String mdseCd, String relnMdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(SQL_PRM_GLBLCMPYCD, getGlobalCompanyCode());
        params.put(SQL_PRM_MDSE_CD, mdseCd);
        params.put(SQL_PRM_RELN_MDSE_CD, relnMdseCd);
        params.put(SQL_PRM_MDSE_ITEM_RELN_TP_CD, SQL_CONST_COMPATIBLE);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getMdseItemFlipSet", params);
        return ssmResult;
    }

    /**
     * getItemStatusList for pull down
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemStatusList() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        return getSsmEZDClient().queryObjectList("getItemStatusList", ssmParam);
    }

    /**
     * getCountSupdFrom
     * @param lineMsg NMAL0190_ACMsg
     * @return Integer
     */
    public Integer getCountSupdFrom(NMAL0190_ACMsg lineMsg) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(SQL_PRM_GLBLCMPYCD, getGlobalCompanyCode());
        queryParams.put(SQL_PRM_SUPD_FROM_MDSE_CD, lineMsg.supdFromMdseCd);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getCountSupdFrom", queryParams);
        Integer resCnt = (Integer) ssmResult.getResultObject();

        return resCnt;
    }
    // ADD END   2016/03/04 QC#2669

    /**
     * Build Suppersession Message
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    public void buidlSupd(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(SQL_MAX_COUNT);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL0190Query.getInstance().getClass());
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(SQL_PRM_ROWNUM, SQL_MAX_COUNT);
            ssmParam.put(SQL_PRM_GLBLCMPYCD, getGlobalCompanyCode());
            ssmParam.put(SQL_PRM_ITEM_NUMBER, bizMsg.A.no(0).supdToMdseCd.getValue());

            ps = ssmLLClient.createPreparedStatement(SQL_CSV_SUPD, ssmParam, execParam);
            rs = ps.executeQuery();
            // START 2020/12/17 J.Evangelista [QC#57542,DEL]
//            if (rs.getRow() > SQL_MAX_COUNT) {
//                bizMsg.setMessageInfo(NZZM0001W);
//                return;
//            }
            // END   2020/12/17 J.Evangelista [QC#57542,DEL]
            // Build CSV Message From glblMsg.
            // START 2020/12/17 J.Evangelista [QC#57542,MOD]
//            convSupdToCsv(glblMsg, rs);
            convSupdToCsv(bizMsg, glblMsg, rs);
            // END   2020/12/17 J.Evangelista [QC#57542,MOD]

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * Build Forward Message
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    public void buildForward(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg) {
        buildReln(bizMsg, glblMsg, true);
    }

    /**
     * Build Backward Message
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    public void buildBackward(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg) {
        buildReln(bizMsg, glblMsg, false);
    }
    /**
     * Build New Revision Message
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    public void buildNewRevision(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg) {
        buildRev(bizMsg, glblMsg, true);
    }

    /**
     * Build Old Revision Message
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    public void buildOldRevision(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg) {
        buildRev(bizMsg, glblMsg, false);
    }
    /**
     * Build RelationShip (Forward /Backward) Message
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param isForward True:Forward / False:Backward
     */
    private void buildReln(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg, boolean isForward) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(SQL_MAX_COUNT);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL0190Query.getInstance().getClass());
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(SQL_PRM_ROWNUM, SQL_MAX_COUNT);
            ssmParam.put(SQL_PRM_GLBLCMPYCD, getGlobalCompanyCode());
            String mdseCd = bizMsg.A.no(0).supdToMdseCd.getValue();
            ssmParam.put(SQL_PRM_ITEM_NUMBER, mdseCd);
            if (isForward) {
                ssmParam.put(SQL_PRM_FORWARD, FLG_ON_Y);
            } else {
                ssmParam.put(SQL_PRM_FORWARD, FLG_OFF_N);
            }
            ssmParam.put(SQL_PRM_COMPATIBLE, SQL_CONST_COMPATIBLE);

            ps = ssmLLClient.createPreparedStatement(SQL_CSV_RELN, ssmParam, execParam);
            rs = ps.executeQuery();
            // START 2020/12/17 J.Evangelista [QC#57542,DEL]
//            if (rs.getRow() > SQL_MAX_COUNT) {
//                bizMsg.setMessageInfo(NZZM0001W);
//                return;
//            }
            // END   2020/12/17 J.Evangelista [QC#57542,DEL]
            // Build CSV Message From glblMsg.
            // START 2020/12/17 J.Evangelista [QC#57542,MOD]
//            convRelnToCsv(glblMsg, rs);
            convRelnToCsv(bizMsg, glblMsg, rs);
            // END   2020/12/17 J.Evangelista [QC#57542,MOD]

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }


    /**
     * Build Revision (New / Old) Message
     * @param bizMsg
     * @param glblMsg
     * @param isNew   (True:New / False: Old)
     */
    private void buildRev(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg, boolean isNew) {
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(SQL_MAX_COUNT);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL0190Query.getInstance().getClass());
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(SQL_PRM_ROWNUM, SQL_MAX_COUNT);
            ssmParam.put(SQL_PRM_GLBLCMPYCD, getGlobalCompanyCode());

            if (isNew) {
                String mdseCd = bizMsg.A.no(0).supdToMdseCd.getValue();
                ssmParam.put(SQL_PRM_MDSE_CD, mdseCd);
                ssmParam.put(SQL_PRM_MRP_ENBLE_FLG, FLG_ON_Y);
            } else {
                String mdseCd = bizMsg.A.no(0).supdFromMdseCd.getValue();
                ssmParam.put(SQL_PRM_MDSE_CD, mdseCd);
                ssmParam.put(SQL_PRM_MRP_ENBLE_FLG, FLG_OFF_N);
            }
            ssmParam.put(SQL_PRM_COMPATIBLE, SQL_CONST_COMPATIBLE);

            ps = ssmLLClient.createPreparedStatement(SQL_CSV_REV, ssmParam, execParam);
            rs = ps.executeQuery();
            // START 2020/12/17 J.Evangelista [QC#57542,DEL]
//            if (rs.getRow() > SQL_MAX_COUNT) {
//                bizMsg.setMessageInfo(NZZM0001W);
//                return;
//            }
            // END   2020/12/17 J.Evangelista [QC#57542,DEL]
            // Build CSV Message From glblMsg.
            // START 2020/12/17 J.Evangelista [QC#57542,MOD]
//            convRevToCsv(glblMsg, rs);
            convRevToCsv(bizMsg, glblMsg, rs);
            // END   2020/12/17 J.Evangelista [QC#57542,MOD]

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * Convert ResultSet(SUPD) To Csv
     * @param bizMsg Business Message
     * @param glblMsg glblMsg.O
     * @param rs ResultSet
     * @throws SQLException
     */
    // START 2020/12/17 J.Evangelista [QC#57542,MOD]
//    private void convSupdToCsv(NMAL0190SMsg glblMsg, ResultSet rs) throws SQLException {
    private void convSupdToCsv(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg, ResultSet rs) throws SQLException {
    // END   2020/12/17 J.Evangelista [QC#57542,MOD]
        int index = glblMsg.O.getValidCount();

        while (rs.next()) {
            // START 2020/12/17 J.Evangelista [QC#57542,ADD]
            if (rs.getRow() > SQL_MAX_COUNT) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }
            // END   2020/12/17 J.Evangelista [QC#57542,ADD]

            NMAL0190_OSMsg csvMsg = glblMsg.O.no(index);
            // resultSet -> OSMsg
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C1, rs.getString(CSV_PRM_ITEMNUMBER));
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C2, CSV_CONST_SUDP);
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C3, "");
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C4, rs.getString(CSV_PRM_SUPD));
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C5, rs.getString(CSV_PRM_DESC));
            String outCsvDt = rs.getString(CSV_RPM_CART_DT);
            outCsvDt = NMAL0190CommonLogic.formatCsvDate(outCsvDt);
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C6, outCsvDt);
            index++;
        }
        glblMsg.O.setValidCount(index);
    }

    // UPDATE START 2016/03/24 QC#5660
    /**
     * Convert ResultSet(Forward/Backward) To Csv
     * @param bizMsg Business Message
     * @param glblMsg glblMsg.O
     * @param rs ResultSet
     * @throws SQLException
     */
    // START 2020/12/17 J.Evangelista [QC#57542,MOD]
//    private void convRelnToCsv(NMAL0190SMsg glblMsg, ResultSet rs) throws SQLException {
    private void convRelnToCsv(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg, ResultSet rs) throws SQLException {
    // END   2020/12/17 J.Evangelista [QC#57542,MOD]
        int index = glblMsg.O.getValidCount();

        while (rs.next()) {
            // START 2020/12/17 J.Evangelista [QC#57542,ADD]
            if (rs.getRow() > SQL_MAX_COUNT) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }
            // END   2020/12/17 J.Evangelista [QC#57542,ADD]

            NMAL0190_OSMsg csvMsg = glblMsg.O.no(index);
            // resultSet -> OSMsg
            // ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C1, rs.getString(CSV_PRM_ITEMNUMBER));
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C1, rs.getString(CSV_RPM_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C2, rs.getString(CSV_PRM_RELN_TP_NM));
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C3, "");
            // ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C4, rs.getString(CSV_PRM_SUPD));
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C4, rs.getString(CSV_PRM_RELN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C5, rs.getString(CSV_PRM_DESC));

            // String outCsvDt = rs.getString(CSV_RPM_CART_DT);
            // outCsvDt = NMAL0190CommonLogic.formatCsvDate(outCsvDt);
            // ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C6, outCsvDt);

            if (rs.getString(CSV_PRM_EZINTIME) != null) {
                String outCsvDt = rs.getString(CSV_PRM_EZINTIME).substring(0, 8);

                outCsvDt = NMAL0190CommonLogic.formatCsvDate(outCsvDt);
                ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C6, outCsvDt);
            }

            index++;
        }
        glblMsg.O.setValidCount(index);
    }
    // UPDATE END 2016/03/24 QC#5660
    /**
     * Convert ResultSet(Old Revision/New Revision) To Csv
     *
     * @param bizMsg Business Message
     * @param glblMsg glblMsg.O
     * @param rs      ResultSet
     * @throws SQLException
     */
    // START 2020/12/17 J.Evangelista [QC#57542,MOD]
//    private void convRevToCsv(NMAL0190SMsg glblMsg, ResultSet rs) throws SQLException {
    private void convRevToCsv(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg, ResultSet rs) throws SQLException {
    // END   2020/12/17 J.Evangelista [QC#57542,MOD]
        int index = glblMsg.O.getValidCount();

        while (rs.next()) {
            // START 2020/12/17 J.Evangelista [QC#57542,ADD]
            if (rs.getRow() > SQL_MAX_COUNT) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }
            // END   2020/12/17 J.Evangelista [QC#57542,ADD]

            NMAL0190_OSMsg csvMsg = glblMsg.O.no(index);
            // resultSet -> OSMsg
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C1, rs.getString(CSV_RPM_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C2, rs.getString(CSV_PRM_DESC));
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C3, "");
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C4, rs.getString(CSV_PRM_RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C5, rs.getString(CSV_PRM_RTL_SWH_NM));

            String mrpEnblFlg = rs.getString(CSV_PRM_MRP_ENBL_FLG);
            if (FLG_ON_Y.equals(mrpEnblFlg)) {
                ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C6, CSV_CONST_NEW);

            } else {
                ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C6, CSV_CONST_DISABLED);

            }
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C7, rs.getString(CSV_PRM_ROP_QTY));
            ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C8, rs.getString(CSV_PRM_MAX_INVTY_QTY));
            index++;
        }
        glblMsg.O.setValidCount(index);
    }
}
