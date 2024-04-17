/**
 * <pre>
 * ATP Calculation Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/10/2009   Fujitsu         K.Kimura        Create          N/A
 * 10/20/2009   Fujitsu         K.Kimura        Update          854
 * 11/23/2009   Fujitsu         K.Kimura        Update          tuning
 * 01/05/2010   Fujitsu         K.Tajima        Update          N/A (refactoring : EZDTBLAccessor.findByKey -> S21FastTBLAccessor.findByKey)
 * 01/07/2010   Fujitsu         K.Tajima        Update          N/A (debug and refactoring.)
 * 03/24/2010   Fujitsu         R.Watanabe      Update          4371
 * 03/29/2010   Fujitsu         T.Kaneda        Update          N/A (performance improvement)
 * 04/27/2010   Fujitsu         K.Tajima        Update          2911, 4359 (performance Tuning)
 * 07/02/2010   Fujitsu         A.Suda          Update          7600
 * 04/19/2011   Fujitsu         S.Takami        Update          2068(PRD)

 * 2013/04/09   Fujitsu         S.Takami        Update          Phase 2.0 S21 WDS R-OM005-001
 * 2013/10/23   Fujitsu         D.Yanagisawa    Update          Phase 2.0 S21 Defect#2866
 * 2016/04/07   Fujitsu         M.Nakamura      Update          CSA: ReqID P628
 * 2017/07/28   Fujitsu         T.Murai         Update          S21_NA#5872(L3#001),#18386(L3#072)
 * 2017/09/22   CITS            T.Kikuhara      Update          QC#18675(SOL#219)
 * 2017/09/22   CITS            S.Endo          Update          QC#21077
 * 
 *</pre>
 */
package com.canon.cusa.s21.batch.NWD.NWDB008001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKey;
import static parts.dbcommon.EZDTBLAccessor.RTNCD_NORMAL;
import static parts.dbcommon.EZDTBLAccessor.RTNCD_NOT_FOUND;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ATP_BAT_WRKTMsg;
import business.db.ATP_HISTTMsg;
import business.db.ATP_INFOTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.SPLY_INFOTMsg;
import business.db.SPLY_INFOTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BACK_ORD_IMPCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_PLN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HARD_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.common.S21SsmEZDOracleJDBCUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

public class NWDB008001 extends S21BatchMain {

    private static final String GLBL_CMPY           = "Global Company Code";
    private static final String VAR_USER1           = "Supply Info Update Type";
    private static final String VAR_USER2           = "Multiple Total";

    private static final String RECREATE_FLG        = "0";
    private static final String REFRESH_FLG         = "1";
    private static final int MULT_TOTAL_ONE         = 1;

    private static final int BULK_SIZE              = 10000;

    /** Order Flag Value */
    private static final String ORD_FLG_ORD         = "1";
    private static final String ORD_FLG_DIST         = "2";

    /** Set Distribution Flag Value */
    private static final String SET_DIST_FLG_DIST  = "2";

    /** Trx Line Number of Set */
    private static final String TRX_LINE_SET_NUM    = "000";

    /** Setting Value */
    private static final String STOCK   = "STOCK";
    private static final String POYO    = "POYO";
    private static final String DOMES   = "DOMES";
    private static final String XFER    = "XFER";
    private static final String REFURB  = "REFURB";
    private static final String KIT     = "KIT";

    /** Attribute Name */
    private static final String MDSE_CD                 = "MDSE_CD";
    private static final String ORDER_FLG               = "ORDER_FLG";
    private static final String TRX_HDR_NUM             = "TRX_HDR_NUM";
    private static final String SHPG_PLN_NUM            = "SHPG_PLN_NUM";
//    private static final String SET_MDSE_CD             = "SET_MDSE_CD";
    private static final String SHIP_CPLT_CD            = "SHIP_CPLT_CD";
    private static final String SOFT_ALLOC_PK           = "SOFT_ALLOC_PK";
    private static final String DIST_PK                 = "DIST_PK";
    private static final String DIST_TM_FRAME_NUM       = "DIST_TM_FRAME_NUM";
//    private static final String ORD_TAKE_MDSE_CD        = "ORD_TAKE_MDSE_CD";
    private static final String BAT_GRP_CD              = "BAT_GRP_CD";
    private static final String PROC_GRP_NUM            = "PROC_GRP_NUM";
    private static final String PROC_SQ_NUM             = "PROC_SQ_NUM";
//    private static final String MDSE_CD_LST             = "MDSE_CD_LST";
//    private static final String ORD_TAKE_MDSE_CD_LST    = "ORD_TAKE_MDSE_CD_LST";
//    private static final String SET_MDSE_CD_LST         = "SET_MDSE_CD_LST";
    private static final String DS_CPO_CONFIG_PK        = "DS_CPO_CONFIG_PK";      // 2016/04/07 CSA: ReqID P628 Add
    private static final String DS_CPO_CONFIG_PK_LIST   = "DS_CPO_CONFIG_PK_LIST"; // 2016/04/07 CSA: ReqID P628 Add
    private static final String RTL_WH_CD               = "RTL_WH_CD";      // 2016/04/07 CSA: ReqID P628 Add
    private static final String RTL_WH_CD_LIST          = "RTL_WH_CD_LIST"; // 2016/04/07 CSA: ReqID P628 Add
    private static final String INVTY_LOC_CD            = "INVTY_LOC_CD";
    private static final String STK_STS_CD              = "STK_STS_CD";
    private static final String INVTY_AVAL_QTY          = "INVTY_AVAL_QTY";
    private static final String INBD_VIS_EVENT_CD       = "INBD_VIS_EVENT_CD";
    private static final String IMPT_INV_NUM            = "IMPT_INV_NUM";
    private static final String IMPT_INV_DO_NUM         = "IMPT_INV_DO_NUM";
    private static final String IMPT_CNTNR_NUM          = "IMPT_CNTNR_NUM";
    private static final String VIS_LOC_CD              = "VIS_LOC_CD";
    private static final String INVTY_STK_STS_CD        = "INVTY_STK_STS_CD";
    private static final String VIS_QTY                 = "VIS_QTY";
    private static final String ETA_ETD_DT              = "ETA_ETD_DT";
    private static final String SET_DIST_FLG            = "SET_DIST_FLG";
    private static final String INBD_VIS_DATA_TP_CD     = "INBD_VIS_DATA_TP_CD"; // 2013/04/09 Phase 2.0 S21 WDS R-OM005-001 ADD

    private static final String NWDM0173E = "NWDM0173E";
    private static final String NWDM0051E = "NWDM0051E";
    private static final String NLBM1019E = "NLBM1019E";
    private static final String NWDM0210E = "NWDM0210E";
    private static final String NWDM0003I = "NWDM0003I";

    private String glblCmpyCd;
    private String splyInfoUpdTp;
    private int multipleTotal;
    private String salesDt;

    private TERM_CD termCd = TERM_CD.NORMAL_END;
    private int totalRecCnt;
    private int normalRecCnt;
    private int errRecCnt;

    /** Direct Shipping Location Code 2013/04/09 Phase 2.0 S21 WDS R-OM005-001 */
    private static final String VIS_LOC_CD_DS = "DS";

    /** Business Application ID For AVAL_INVTY_APP_ID */
    private static final String BIZ_APP_ID = "NWDB0080";
    private S21SsmBatchClient ssmClient;

    public static void main(final String[] args) {
        new NWDB008001().executeBatch(NWDB008001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Get Global Company Code
        this.getGlblCmpyCd();

        // Get Constant Value
        this.getConstValue();

        // Get Sales Date
        salesDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (salesDt == null) {
            S21InfoLogOutput.println(NWDM0210E);
            throw new S21AbendException(NWDM0210E);
        }

        ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        // --------------------------------------------------
        // [SPLY_INFO]
        // --------------------------------------------------
        // Create Supply Information
        if (RECREATE_FLG.equals(splyInfoUpdTp)) {

            // Clear data of SPLY_INFO
            clearSplyInfo();

            // Create data of SPLY_INFO form INVTY
            createSplyInfo(SplyInfoCreateBy.INVTY);

            // Create data of SPLY_INFO form INBD_VIS
            createSplyInfo(SplyInfoCreateBy.INBD_VIS);

        } else {

            // Update Available Qty of SPLY_INFO
            updateSplyInfo();
        }

        // --------------------------------------------------
        // [ATP_BAT_WRK]
        // --------------------------------------------------
        // Clear ATP_BAT_WRK Table
        clearAtpBatWrk();

        // Move Shipped data of ATP_INFO
        moveAtpInfoToAtpHist();

        // get data to create [ATP_BAT_WRK].
        final List<Map<String, Object>> atpBatWrkInfoList = this.getAtpBatWrkInfo();

        if (!isEmpty(atpBatWrkInfoList)) {

            // create [ATP_BAT_WRK].
            boolean isNormalEnd = createAtpBatWrk(atpBatWrkInfoList);

            if (isNormalEnd) {

                // Clear distribution remainder
                clearAtpInfo();

               // normalRecCnt = atpBatWrkInfoList.size();
                totalRecCnt  = normalRecCnt;
                errRecCnt    = 0;

            } else {
                normalRecCnt = 0;
                totalRecCnt  = normalRecCnt;
                errRecCnt    = atpBatWrkInfoList.size();
            }
        }

        if (errRecCnt > 0) {
            termCd = TERM_CD.ABNORMAL_END;
            rollback();
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);
    }

    // 2016/04/07 CSA: ReqID P628 Mod Start.
//    private boolean addAttribute(Map<String, List<String>> batGrpDetailMap, Map<String, Object> atpBatWrkOthMap) {
//
//        String mdseCd           = (String) atpBatWrkOthMap.get(MDSE_CD);
//        String setMdseCd        = (String) atpBatWrkOthMap.get(SET_MDSE_CD);
//        String ordTakeMdseCd    = (String) atpBatWrkOthMap.get(ORD_TAKE_MDSE_CD);
//
//        List<String> mdseCdLst          = batGrpDetailMap.get(MDSE_CD_LST);
//        List<String> ordTakeMdseCdLst   = batGrpDetailMap.get(ORD_TAKE_MDSE_CD_LST);
//        List<String> setMdseCdLst       = batGrpDetailMap.get(SET_MDSE_CD_LST);
//
//        if (mdseCdLst == null) {
//            mdseCdLst = new ArrayList<String>();
//            batGrpDetailMap.put(MDSE_CD_LST, mdseCdLst);
//        }
//
//        if (ordTakeMdseCdLst == null) {
//            ordTakeMdseCdLst = new ArrayList<String>();
//            batGrpDetailMap.put(ORD_TAKE_MDSE_CD_LST, ordTakeMdseCdLst);
//        }
//
//        if (setMdseCdLst == null) {
//            setMdseCdLst = new ArrayList<String>();
//            batGrpDetailMap.put(SET_MDSE_CD_LST, setMdseCdLst);
//        }
//
//        boolean result = false;
//
//        if (hasValue(mdseCd)) {
//            if (!mdseCdLst.contains(mdseCd)) {
//                mdseCdLst.add(mdseCd);
//                result = true;
//            }
//        }
//
//        if (hasValue(ordTakeMdseCd)) {
//            if (!ordTakeMdseCdLst.contains(ordTakeMdseCd)) {
//                ordTakeMdseCdLst.add(ordTakeMdseCd);
//                result = true;
//            }
//        }
//
//        if (hasValue(setMdseCd)) {
//            if (!setMdseCdLst.contains(setMdseCd)) {
//                setMdseCdLst.add(setMdseCd);
//                result = true;
//            }
//        }

    private boolean addAttribute(Map<String, List<Object>> batGrpDetailMap, Map<String, Object> atpBatWrkOthMap) {
        BigDecimal dsCpoConfigPk  = (BigDecimal) atpBatWrkOthMap.get(DS_CPO_CONFIG_PK);
        String rtlWhCd            = (String) atpBatWrkOthMap.get(RTL_WH_CD);

        List<Object> dsCpoConfigPkList = batGrpDetailMap.get(DS_CPO_CONFIG_PK_LIST);
        List<Object> rtlWhCdList       = batGrpDetailMap.get(RTL_WH_CD_LIST);

        if (dsCpoConfigPkList == null) {
            dsCpoConfigPkList = new ArrayList<Object>();
            batGrpDetailMap.put(DS_CPO_CONFIG_PK_LIST, dsCpoConfigPkList);
        }

        if (rtlWhCdList == null) {
            rtlWhCdList = new ArrayList<Object>();
            batGrpDetailMap.put(RTL_WH_CD_LIST, rtlWhCdList);
        }

        boolean result = false;

        if (hasValue(dsCpoConfigPk)) {
            if (!dsCpoConfigPkList.contains(dsCpoConfigPk)) {
                dsCpoConfigPkList.add(dsCpoConfigPk);
                result = true;
            }
        }

        if (hasValue(rtlWhCd)) {
            if (!rtlWhCdList.contains(rtlWhCd)) {
                rtlWhCdList.add(rtlWhCd);
                result = true;
            }
        }
        // 2016/04/07 CSA: ReqID P628 Mod End.

        return result;
    }

    // 2016/04/07 CSA: ReqID P628 Mod Start.
//    private boolean addAttributeLst(Map<String, List<String>> batGrpDetailMap, List<Map<String, Object>> group) {
    private boolean addAttributeLst(Map<String, List<Object>> batGrpDetailMap, List<Map<String, Object>> group) {
    // 2016/04/07 CSA: ReqID P628 Mod End.

        boolean addAttributeFlg = false;

        for (Map<String, Object> atpBatWrkOthMap : group) {
            if (addAttribute(batGrpDetailMap, atpBatWrkOthMap)) {
                addAttributeFlg = true;
            }
        }

        return addAttributeFlg;
    }

    private void clearAtpBatWrk() {
        final String methodNm = "clearAtpBatWrk";
        methodStartLogOut(getClass(), methodNm);

        try {

            ATP_BAT_WRKTMsg reqTMsg = new ATP_BAT_WRKTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);

            // ***** [removeByPartialKey]
            EZDTBLAccessor.removeByPartialKey(reqTMsg);

            final String retCd = reqTMsg.getReturnCode();
            infoLogOut(getClass(), "EZDTBLAccessor.removeByPartialKey(" + reqTMsg.getTableName() + "). ReturnCode = ", retCd);

            if (!RTNCD_NORMAL.equals(retCd) && !RTNCD_NOT_FOUND.equals(retCd)) {
                String[] str = {reqTMsg.getTableName(), "removeByPartialKey", retCd };
                S21InfoLogOutput.println(NLBM1019E, str);
                throw new S21AbendException(NLBM1019E, str);
            }

        } finally {
            methodEndLogOut(getClass(), methodNm);
        }
    }

    @SuppressWarnings("unchecked")
    private void clearAtpInfo() {
        final String methodNm = "clearAtpInfo";
        methodStartLogOut(getClass(), methodNm);

        try {

            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);

            final List<ATP_INFOTMsg> atpInfoTMsgList = ssmClient.queryObjectList("getAtpInfo", ssmParam);
            infoLogOut(getClass(), "List<ATP_INFOTMsg>.size() = ", atpInfoTMsgList.size());

            if (!isEmpty(atpInfoTMsgList)) {
                // ***** [removePhysical]
                removePhysical(atpInfoTMsgList);
            }

        } finally {
            methodEndLogOut(getClass(), methodNm);
        }
    }

    private void clearSplyInfo() {
        final String methodNm = "clearSplyInfo";
        methodStartLogOut(getClass(), methodNm);

        try {

            SPLY_INFOTMsg reqTMsg = new SPLY_INFOTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);

            // ***** [removeByPartialKey]
            EZDTBLAccessor.removeByPartialKey(reqTMsg);

            final String retCd = reqTMsg.getReturnCode();
            infoLogOut(getClass(), "EZDTBLAccessor.removeByPartialKey(" + reqTMsg.getTableName() + "). ReturnCode = ", retCd);

            if (!RTNCD_NORMAL.equals(retCd) && !RTNCD_NOT_FOUND.equals(retCd)) {
                String[] str = {reqTMsg.getTableName(), "removeByPartialKey", retCd };
                S21InfoLogOutput.println(NLBM1019E, str);
                throw new S21AbendException(NLBM1019E, str);
            }

        } finally {
            methodEndLogOut(getClass(), methodNm);
        }
    }

    // 2013/10/23 Phase 2.0 S21 WDS Defect#2866 ADD Start
    private void clearDsSplyInfo() {
        final String methodNm = "clearDsSplyInfo";
        methodStartLogOut(getClass(), methodNm);

        try {

            SPLY_INFOTMsg reqTMsg = new SPLY_INFOTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);

            // ***** [removeByPartialKey]
            EZDTBLAccessor.removeByPartialKey(reqTMsg);

            final String retCd = reqTMsg.getReturnCode();
            infoLogOut(getClass(), "EZDTBLAccessor.removeByPartialKey(" + reqTMsg.getTableName() + "). ReturnCode = ", retCd);

            if (!RTNCD_NORMAL.equals(retCd) && !RTNCD_NOT_FOUND.equals(retCd)) {
                String[] str = {reqTMsg.getTableName(), "removeByPartialKey", retCd };
                S21InfoLogOutput.println(NLBM1019E, str);
                throw new S21AbendException(NLBM1019E, str);
            }

        } finally {
            methodEndLogOut(getClass(), methodNm);
        }
    }
    // 2013/10/23 Phase 2.0 S21 WDS Defect#2866 ADD E n d

    private boolean createAtpBatWrk(List<Map<String, Object>> atpBatWrkInfoList) {
        final String methodNm = "createAtpBatWrk";
        methodStartLogOut(getClass(), methodNm);

        try {

            // --------------------------------------------------
            // grouping.
            // --------------------------------------------------
            groupingGrpCd(atpBatWrkInfoList);

            // --------------------------------------------------
            // create [ATP_BAT_WRK].
            // --------------------------------------------------
            final List<ATP_BAT_WRKTMsg> atpBatWrkTMsgList = new ArrayList<ATP_BAT_WRKTMsg>();
            final Map<String, String> setDistMap = new HashMap<String, String>();
            int count = 0;
            String orderFlg         = null;
            String setDistFlg       = null;
            BigDecimal distPk       = null;
            String distTmFrameNum   = null;

            List<BigDecimal> hasCriticalMdseConfigPkList = new ArrayList<BigDecimal>(); // Add S21_NA#5872

            for (Map<String, Object> atpBatWrkInfo : atpBatWrkInfoList) {

                // Add Start S21_NA#5872
                BigDecimal dsConfigPk = (BigDecimal) atpBatWrkInfo.get("DS_CPO_CONFIG_PK");
                if (hasCriticalMdseConfigPkList.contains(dsConfigPk)) {
                    continue;
                }

                if (!checkCriticalMdse(atpBatWrkInfo)) {
                    hasCriticalMdseConfigPkList.add(dsConfigPk);
                    continue;
                }
                // Add End S21_NA#5872

                orderFlg         = null;
                setDistFlg       = null;
                distPk           = null;
                distTmFrameNum   = null;

                if (!(atpBatWrkInfo.get(ORDER_FLG) == null)) {
                    orderFlg = atpBatWrkInfo.get(ORDER_FLG).toString();
                }
                if (!(atpBatWrkInfo.get(SET_DIST_FLG) == null)) {
                    setDistFlg = atpBatWrkInfo.get(SET_DIST_FLG).toString();
                }
                if (!(atpBatWrkInfo.get(DIST_PK) == null)) {
                    distPk = (BigDecimal) atpBatWrkInfo.get(DIST_PK);
                }
                if (!(atpBatWrkInfo.get(DIST_TM_FRAME_NUM) == null)) {
                    distTmFrameNum = atpBatWrkInfo.get(DIST_TM_FRAME_NUM).toString();
                }

                if (ORD_FLG_DIST.equals(orderFlg) && SET_DIST_FLG_DIST.equals(setDistFlg)) {

                    if (!hasValue(distPk)) {
                        continue;

                    } else {

                        final StringBuilder key = new StringBuilder(100);
                        key.append(distPk.toString()).append(".").append(distTmFrameNum);
                        final String mapkey = key.toString();

                        if (setDistMap.containsKey(mapkey)) {
                            continue;
                        } else {
                            setDistMap.put(mapkey, mapkey);
                        }
                    }
                }

                final ATP_BAT_WRKTMsg atpBatWrkTMsg = toAtpBatWrkTMsg(atpBatWrkInfo);
                atpBatWrkTMsgList.add(atpBatWrkTMsg);
                count++;
                normalRecCnt = count;

                if (atpBatWrkTMsgList.size() >= BULK_SIZE) {
                    // ***** [insert]
                    if (insert(atpBatWrkTMsgList)) {
                        atpBatWrkTMsgList.clear();
                        continue;
                    } else {
                        return false;
                    }
                }
            }

            setDistMap.clear();

            // ***** [insert]
            return insert(atpBatWrkTMsgList);

        } finally {
            methodEndLogOut(getClass(), methodNm);
        }
    }

    // Add Start S21_NA#5872
    private boolean checkCriticalMdse(Map<String, Object> atpBatWrkInfo) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsCpoConfigPk", atpBatWrkInfo.get("DS_CPO_CONFIG_PK"));
        ssmParam.put("VALIDATED", SHPG_STS.VALIDATED);
        ssmParam.put("backOrderImpctC", BACK_ORD_IMPCT_TP.CRITICAL);

        return 0 == (Integer) ssmClient.queryObject("checkHasCriticalMdse", ssmParam);
    }
    // Add End S21_NA#5872

    private void createSplyInfo(SplyInfoCreateBy createBy) {
        final String methodNm = "createSplyInfo(" + createBy + ")";
        methodStartLogOut(getClass(), methodNm);

        try {

            String statementId;
            Map<String, Object> ssmParam = new HashMap<String, Object>();

            if (SplyInfoCreateBy.INVTY == createBy) {

                statementId = "getInvty";

                ssmParam.put("glblCmpyCd",          glblCmpyCd);
                // LOC_STS
                ssmParam.put("DC_STOCK",            LOC_STS.DC_STOCK);
                // STK_STS
                ssmParam.put("GOOD",                STK_STS.GOOD);
                ssmParam.put("RANK_B",              STK_STS.RANK_B);
                //QC#18675(SOL#219) DEL START
                //ssmParam.put("DISCOUNT_ITEM",       STK_STS.DISCOUNT_ITEM);
                //QC#18675(SOL#219) DEL START
                // LOC_TP
                ssmParam.put("WAREHOUSE",           LOC_TP.WAREHOUSE);
                ssmParam.put("OFFICE",              LOC_TP.OFFICE);
                // WH_SYS_TP
                ssmParam.put("WMS",                 WH_SYS_TP.WMS);
                ssmParam.put("S21_SYSTEM",          WH_SYS_TP.S21_SYSTEM);

                // 2013/04/09 Phase 2.0 S21 WDS R-OM005-001 ADD Start
                ssmParam.put("BIZ_APP_ID", BIZ_APP_ID);
                // 2013/04/09 Phase 2.0 S21 WDS R-OM005-001 ADD End
            } else {

                statementId = "getInbdVis";

                ssmParam.put("glblCmpyCd",          glblCmpyCd);
                // STK_STS
                ssmParam.put("GOOD",                STK_STS.GOOD);
                ssmParam.put("RANK_B",              STK_STS.RANK_B);
                //QC#18675(SOL#219) DEL START
                //ssmParam.put("DISCOUNT_ITEM",       STK_STS.DISCOUNT_ITEM);
                //QC#18675(SOL#219) DEL END
                // WH_SYS_TP
                ssmParam.put("WMS",                 WH_SYS_TP.WMS);
                ssmParam.put("S21_SYSTEM",          WH_SYS_TP.S21_SYSTEM);
                // INBD_VIS_EVENT
                ssmParam.put("DOMESTIC_PO_RECEIVE", INBD_VIS_EVENT.DOMESTIC_PO_RECEIVE);
                ssmParam.put("DC_TRANSFER_RECEIVE", INBD_VIS_EVENT.DC_TRANSFER_RECEIVE);
                ssmParam.put("REFURBISH_RECEIVE",   INBD_VIS_EVENT.REFURBISH_RECEIVE);
                ssmParam.put("KITTING_RECEIVE",     INBD_VIS_EVENT.KITTING_RECEIVE);
                ssmParam.put("WH_CHANCE_CR",        INBD_VIS_EVENT.WH_CHANGE_CR); // 04/19/2011 2068 added

                // 2013/04/09 Phase 2.0 S21 WDS R-OM005-001 ADD Start
                ssmParam.put("VIS_DATA_TP_STOCK_OUT", INBD_VIS_DATA_TP.STOCK_OUT);
                ssmParam.put("VIS_DATA_TP_STOC_IN", INBD_VIS_DATA_TP.STOCK_IN_DC);
                ssmParam.put("VIS_EVENT_CD_POYO", INBD_VIS_EVENT.POYO_RECEIVE);
                ssmParam.put("VIS_LOC_CD_DS", VIS_LOC_CD_DS);
                ssmParam.put("BIZ_APP_ID", BIZ_APP_ID);
                // 2013/04/09 Phase 2.0 S21 WDS R-OM005-001 ADD End
            }

            ssmClient.queryObject(statementId, ssmParam, new ShplyInfoCreator(createBy));

        } finally {
            methodEndLogOut(getClass(), methodNm);
        }
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getAtpBatWrkInfo() {
        final String methodNm = "getAtpBatWrkInfo";
        methodStartLogOut(getClass(), methodNm);

        try {
            // 2017/09/22 S.Endo QC#21077 MOD START
            //Map<String, String> ssmParam = new HashMap<String, String>();
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            // 2017/09/22 S.Endo QC#21077 MOD END
            
            ssmParam.put("glblCmpyCd",          glblCmpyCd);
            ssmParam.put("trxSrcTpWoleSale",    TRX_SRC_TP.WHOLE_SALES);
            ssmParam.put("distPlnSts",          DIST_PLN_STS.SUBMITTED);
            ssmParam.put("trxLineSetNum",       TRX_LINE_SET_NUM);
            ssmParam.put("salesDate",           salesDt);

            ssmParam.put("VALIDATED",           SHPG_STS.VALIDATED);
            ssmParam.put("HARD_ALLOCATED",      SHPG_STS.HARD_ALLOCATED);
            ssmParam.put("hardAlocTpAuto",      HARD_ALLOC_TP.AUTO);
            ssmParam.put("hardAlocTpManual",    HARD_ALLOC_TP.MANUAL);
            ssmParam.put("PO_PRINTED",          SHPG_STS.P_OR_O_PRINTED); // 2013/04/25 Phase 2.0 S21 WDS R-OM005-001 add
            ssmParam.put("ATP_CALC_FLG_Y",      ZYPConstant.FLG_ON_Y); // 2013/04/25 Phase 2.0 S21 WDS R-OM005-001 add
            ssmParam.put("OUTBOUND",            CONFIG_CATG.OUTBOUND); // 2016/04/07 CSA: ReqID P628 Add.
            ssmParam.put("RETAIL_EQUIPMENT",    ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS); // 2016/04/07 CSA: ReqID P628 Add.
            ssmParam.put("DAYS_PRIOR_ALLOC",    ORD_CATG_CTX_TP.DAYS_PRIOR_ALLOCATION_CONTROL_ORDERS); // 2016/04/07 CSA: ReqID P628 Add.

            // 2017/09/22 S.Endo QC#21077 ADD START
            ssmParam.put("defTmFenceDaysAot", ZYPCodeDataUtil.getNumConstValue("DEF_TM_FENCE_DAYS_AOT", glblCmpyCd));
            // 2017/09/22 S.Endo QC#21077 ADD END
            return ssmClient.queryObjectList("getAtpBatWrkInfo", ssmParam);

        } finally {
            methodEndLogOut(getClass(), methodNm);
        }
    }

    /**
     * Get Constant Value
     */
    private void getConstValue() {

        // Get Supply Info Update Type
        splyInfoUpdTp = getUserVariable1();

        if (!hasValue(splyInfoUpdTp)) {
            splyInfoUpdTp = RECREATE_FLG;
            S21InfoLogOutput.println(NWDM0003I, new String[] {VAR_USER1, RECREATE_FLG });
        } else if (!RECREATE_FLG.equals(splyInfoUpdTp) && !REFRESH_FLG.equals(splyInfoUpdTp)) {
            splyInfoUpdTp = REFRESH_FLG;
            S21InfoLogOutput.println(NWDM0003I, new String[] {VAR_USER1, REFRESH_FLG });
        }

        // Get Multiple Total
        try {
            multipleTotal = Integer.parseInt(getUserVariable2());
            if (multipleTotal < 0) {
                multipleTotal = MULT_TOTAL_ONE;
                S21InfoLogOutput.println(NWDM0003I, new String[] {VAR_USER2, Integer.toString(MULT_TOTAL_ONE) });
            }
        } catch (NumberFormatException e) {
            multipleTotal = MULT_TOTAL_ONE;
            S21InfoLogOutput.println(NWDM0003I, new String[] {VAR_USER2, Integer.toString(MULT_TOTAL_ONE) });
        }
    }

    private void getGlblCmpyCd() {

        this.glblCmpyCd = getUserProfileService().getGlobalCompanyCode();

        if (!hasValue(glblCmpyCd)) {
            S21InfoLogOutput.println(NWDM0173E);
            throw new S21AbendException(NWDM0173E);
        }

        GLBL_CMPYTMsg cmpyMsg = new GLBL_CMPYTMsg();
        cmpyMsg.glblCmpyCd.setValue(glblCmpyCd);

        GLBL_CMPYTMsg cmpyrMsg = (GLBL_CMPYTMsg) findByKey(cmpyMsg);

        if (cmpyrMsg == null) {
            String[] str = {GLBL_CMPY };
            S21InfoLogOutput.println(NWDM0051E, str);
            throw new S21AbendException(NWDM0051E, str);
        }
    }

    private String getShipCpltCdKey(Map atpBatWrkOthMap) {

        String trxHdrNum = (String) atpBatWrkOthMap.get(TRX_HDR_NUM);
        String shipCpltCd = (String) atpBatWrkOthMap.get(SHIP_CPLT_CD);

        return ZYPCommonFunc.concatString(trxHdrNum, "-", shipCpltCd);
    }

    private List<List<Map<String, Object>>> getShipCpltGroup(List<Map<String, Object>> atpBatWrkInfoList) {
//        println(1, "[ getShipCpltGroup ] start", this);

        List<List<Map<String, Object>>> shipCpltGroup = new ArrayList<List<Map<String, Object>>>();

        Map<String, Integer> shipCpltObjectIndex = new HashMap<String, Integer>();

        for (int i = 0; i < atpBatWrkInfoList.size(); i++) {

            Map<String, Object> atpBatWrkOthMap = atpBatWrkInfoList.get(i);

            String shipCpltCd = (String) atpBatWrkOthMap.get(SHIP_CPLT_CD);

            if (hasValue(shipCpltCd)) {

                String shipCpltKey = getShipCpltCdKey(atpBatWrkOthMap);

                if (shipCpltObjectIndex.get(shipCpltKey) == null) {

                    shipCpltObjectIndex.put(shipCpltKey, shipCpltGroup.size());

                    List<Map<String, Object>> group = new ArrayList<Map<String, Object>>();
                    group.add(atpBatWrkOthMap);
                    shipCpltGroup.add(group);

                } else {

                    List<Map<String, Object>> group = shipCpltGroup.get(shipCpltObjectIndex.get(shipCpltKey));
                    group.add(atpBatWrkOthMap);
                }
            }
        }

        for (int j = 0; j < atpBatWrkInfoList.size(); j++) {

            Map<String, Object> atpBatWrkOthMap = (Map<String, Object>) atpBatWrkInfoList.get(j);

            String shipCpltCd = (String) atpBatWrkOthMap.get(SHIP_CPLT_CD);

            if (!hasValue(shipCpltCd)) {

                List<Map<String, Object>> group = new ArrayList<Map<String, Object>>();
                group.add(atpBatWrkOthMap);
                shipCpltGroup.add(group);
            }
        }

//        println(1, "[ getShipCpltGroup ] end", this);

        return shipCpltGroup;
    }

    @SuppressWarnings("unchecked")
    private void groupingGrpCd(List<Map<String, Object>> atpBatWrkInfoList) {
        final String methodNm = "groupingGrpCd";
        methodStartLogOut(getClass(), methodNm);

        try {

            splitGroup(atpBatWrkInfoList);

            Map<String, Integer> procSqNumMap = new HashMap<String, Integer>();
            Map<Integer, Integer> eachBatchGroupTotalMap = new HashMap<Integer, Integer>();
            List<Integer> keyList = new ArrayList<Integer>();

            for (Map<String, Object> atpBatWrkOthMap : atpBatWrkInfoList) {

                int batGrpCd    = (Integer) atpBatWrkOthMap.get(BAT_GRP_CD);
                int procGrpNum  = (Integer) atpBatWrkOthMap.get(PROC_GRP_NUM);
                int procSqNum   = 0;

                String procSqNumKey = ZYPCommonFunc.concatString(Integer.toString(batGrpCd), "-", Integer.toString(procGrpNum));

                if (procSqNumMap.get(procSqNumKey) != null) {
                    procSqNum = procSqNumMap.get(procSqNumKey);
                }

                procSqNum++;

                atpBatWrkOthMap.put(PROC_SQ_NUM, procSqNum);
                procSqNumMap.put(procSqNumKey, procSqNum);

                // --------------------------------------------
                // The number of records of each batch groups is calculated. 
                // --------------------------------------------
                if (eachBatchGroupTotalMap.get(batGrpCd) != null) {
                    int count = eachBatchGroupTotalMap.get(batGrpCd);
                    eachBatchGroupTotalMap.put(batGrpCd, count);
                    count++;

                } else {
                    eachBatchGroupTotalMap.put(batGrpCd, 1);
                    keyList.add(batGrpCd);
                }
            }

// 2010/07/02 Defect 7600 add            
            reGroupingProcess(atpBatWrkInfoList, procSqNumMap, eachBatchGroupTotalMap, keyList);

            methodStartLogOut(getClass(), procSqNumMap.toString());


        } finally {
            methodEndLogOut(getClass(), methodNm);
        }
    }

    private void reGroupingProcess(List<Map<String, Object>> atpBatWrkInfoList, Map<String, Integer> eachProcGroupMap, Map<Integer, Integer> eachBatchGroupTotalMap, List<Integer> keyList) {

        // --------------------------------------------
        // The total of the record of the ATP_BAT_WRK table is acquired.
        // --------------------------------------------
        int totalAtpBatWrkListSize = atpBatWrkInfoList.size();

        if (totalAtpBatWrkListSize == 0) {
            return;
        }

        // --------------------------------------------
        // The number of average records to distribute it to each child batch is acquired. 
        // --------------------------------------------
        int averageAtpBatWrkListSize = totalAtpBatWrkListSize / multipleTotal;

        if (averageAtpBatWrkListSize == 0) {
            return;
        }

        Map<Integer, List<Entry<String, Integer>>> resProcGroupMap = new HashMap<Integer, List<Entry<String, Integer>>>();
        Map<Integer, Integer> eachBatchGroupAverageMap = new HashMap<Integer, Integer>();

        // --------------------------------------------
        // Each batch group sets the number of average records. 
        // --------------------------------------------
        for (int batchGroupNum : keyList) {
            eachBatchGroupAverageMap.put(batchGroupNum, averageAtpBatWrkListSize);
        }

        // --------------------------------------------
        // It sorts it in descending orders of the number of ProcessGroups. 
        // --------------------------------------------
        List<Entry<String, Integer>> eachProcGropSortList = sort(eachProcGroupMap);

        for (Map.Entry<String, Integer> procGrop : eachProcGropSortList) {

            Entry<Integer, Integer> maxBatchGroup = maxSizeMap(eachBatchGroupAverageMap);

            int batchGroupKey = maxBatchGroup.getKey();

            int batchGroupTotalCount =  maxBatchGroup.getValue();
            int procSqTotalCount =  procGrop.getValue();
            batchGroupTotalCount = batchGroupTotalCount - procSqTotalCount;
            eachBatchGroupAverageMap.put(batchGroupKey, batchGroupTotalCount);

            if (resProcGroupMap.containsKey(batchGroupKey)) {
                List<Entry<String, Integer>> procSqNumList = resProcGroupMap.get(batchGroupKey);
                procSqNumList.add(procGrop);
                resProcGroupMap.put(batchGroupKey, procSqNumList);
            } else {
                List<Entry<String, Integer>> procSqNumList = new ArrayList<Entry<String, Integer>>();
                procSqNumList.add(procGrop);
                resProcGroupMap.put(batchGroupKey, procSqNumList);
            }

        }
        // --------------------------------------------
        // The mapping of new batchGroupCd, ProcessGroupNumber and
        // old batchGroupCd, ProcessGroupNumber.
        // --------------------------------------------
        Map<String, String> newProcessNumMap = new HashMap<String, String>();

        for (int batchGroupNum : keyList) {

            List<Entry<String, Integer>> newBatchGroupList =  resProcGroupMap.get(batchGroupNum);
            int newProcGrpNum = 1;

            for (Entry<String, Integer> procGroup : newBatchGroupList) {

                String newProcSqNumKey = ZYPCommonFunc.concatString(Integer.toString(batchGroupNum), "-", Integer.toString(newProcGrpNum));
                newProcGrpNum++;
                newProcessNumMap.put(procGroup.getKey(), newProcSqNumKey);

            }
        }

        // --------------------------------------------
        // The transaction data is updated. 
        // --------------------------------------------
        int reserveProcGrop = 999999;

        for (Map<String, Object> atpBatWrkOthMap : atpBatWrkInfoList) {

            int batGrpCd    = (Integer) atpBatWrkOthMap.get(BAT_GRP_CD);
            int procGrpNum  = (Integer) atpBatWrkOthMap.get(PROC_GRP_NUM);

            String procSqNumKey = ZYPCommonFunc.concatString(Integer.toString(batGrpCd), "-", Integer.toString(procGrpNum));

            if (newProcessNumMap.containsKey(procSqNumKey)) {

                String newProcSqNumKey = newProcessNumMap.get(procSqNumKey);

                String newBatGrpCd = newProcSqNumKey.substring(0, newProcSqNumKey.indexOf("-", 0));
                String newProcGrpNum = newProcSqNumKey.substring(newProcSqNumKey.indexOf("-", 0) + 1 , newProcSqNumKey.length());

                if (hasValue(newBatGrpCd)) {
                    atpBatWrkOthMap.put(BAT_GRP_CD , Integer.parseInt(newBatGrpCd));

                } else {
                    atpBatWrkOthMap.put(BAT_GRP_CD , 1);
                }

                if (hasValue(newProcGrpNum)) {
                    atpBatWrkOthMap.put(PROC_GRP_NUM , Integer.parseInt(newProcGrpNum));

                } else {
                    atpBatWrkOthMap.put(PROC_GRP_NUM , reserveProcGrop);
                    reserveProcGrop--;
                }

            } else {

                atpBatWrkOthMap.put(BAT_GRP_CD , 1);
                atpBatWrkOthMap.put(PROC_GRP_NUM , reserveProcGrop);
                reserveProcGrop--;

            }
        }
    }

    private Entry<Integer, Integer> maxSizeMap(Map<Integer, Integer> sortMap) {

        final List<Map.Entry<Integer, Integer>> sortList = new ArrayList<Map.Entry<Integer, Integer>>(sortMap.entrySet());

        Collections.sort(sortList, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(final Entry<Integer, Integer> e1, final Entry<Integer, Integer> e2) {

                BigDecimal val1 = BigDecimal.valueOf(e1.getValue());
                BigDecimal val2 = BigDecimal.valueOf(e2.getValue());

                if (val1.compareTo(val2) == 0) {
                    BigDecimal key1 = BigDecimal.valueOf(e1.getKey());
                    BigDecimal key2 = BigDecimal.valueOf(e2.getKey());
                    return key1.compareTo(key2);
                } else {
                    return val2.compareTo(val1);
                }
            }
        });

        return sortList.get(0);
    }

    private List<Entry<String, Integer>> sort(Map<String, Integer> sortMap) {

        final List<Map.Entry<String, Integer>> sortList = new ArrayList<Map.Entry<String, Integer>>(sortMap.entrySet());

        Collections.sort(sortList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(final Entry<String, Integer> e1, final Entry<String, Integer> e2) {
                BigDecimal val1 = BigDecimal.valueOf(e1.getValue());
                BigDecimal val2 = BigDecimal.valueOf(e2.getValue());
                return val2.compareTo(val1);
            }
        });

        return sortList;
    }

    private boolean insert(final List... tMsgListArray) {

        for (List<EZDTMsg> tMsgList : tMsgListArray) {
            if (!isEmpty(tMsgList)) {
                EZDTMsg[] tMsgArray = tMsgList.toArray(new EZDTMsg[0]);
                // ***** [insert]
                int resRecordCnt = S21FastTBLAccessor.insert(tMsgArray);
                infoLogOut(getClass(), "S21FastTBLAccessor.insert(", tMsgArray[0].getTableName(), "). Result record count = [", resRecordCnt, "].");
                if (tMsgArray.length != resRecordCnt) {
                    infoLogOut(getClass(), "Error. S21FastTBLAccessor.insert(" + tMsgArray[0].getTableName() + "). EZDTMsg[].length = [" + tMsgArray.length + "], but result record count = [" + resRecordCnt + "].");
                    return false;
                }
            }
        }

        return true;
    }

    // 2016/04/07 CSA: ReqID P628 Mod Start.
//    private boolean isParameterGrpCheck(Map<String, List<String>> batGrpDetailMap, Map atpBatWrkOthMap) {

//        String mdseCdOth        = (String) atpBatWrkOthMap.get(MDSE_CD);
//        String setMdseCdOth     = (String) atpBatWrkOthMap.get(SET_MDSE_CD);
//        String ordTakeMdseCdOth = (String) atpBatWrkOthMap.get(ORD_TAKE_MDSE_CD);
//
//        List<String> mdseCdLst          = batGrpDetailMap.get(MDSE_CD_LST);
//        List<String> ordTakeMdseCdLst   = batGrpDetailMap.get(ORD_TAKE_MDSE_CD_LST);
//        List<String> setMdseCdLst       = batGrpDetailMap.get(SET_MDSE_CD_LST);
//
//        if (hasValue(mdseCdOth) && !isEmpty(mdseCdLst)) {
//            if (mdseCdLst.contains(mdseCdOth)) {
//                return true;
//            }
//        }
//
//        if (hasValue(ordTakeMdseCdOth) && !isEmpty(ordTakeMdseCdLst)) {
//            if (ordTakeMdseCdLst.contains(ordTakeMdseCdOth)) {
//                return true;
//            }
//        }
//
//        if (hasValue(setMdseCdOth) && !isEmpty(setMdseCdLst)) {
//            if (setMdseCdLst.contains(setMdseCdOth)) {
//                return true;
//            }
//        }
        // 2016/04/07 CSA: ReqID P628 Mod End.

    private boolean isParameterGrpCheck(Map<String, List<Object>> batGrpDetailMap, Map atpBatWrkOthMap) {

        BigDecimal dsCpoConfigPk = (BigDecimal) atpBatWrkOthMap.get(DS_CPO_CONFIG_PK);
        String rtlWhCd = (String) atpBatWrkOthMap.get(RTL_WH_CD);

        List<Object> dsCpoConfigPkList = batGrpDetailMap.get(DS_CPO_CONFIG_PK_LIST);
        List<Object> rtlWhCdList = batGrpDetailMap.get(RTL_WH_CD_LIST);

        if (hasValue(dsCpoConfigPk) && !isEmpty(dsCpoConfigPkList)) {
            if (dsCpoConfigPkList.contains(dsCpoConfigPk)) {
                return true;
            }
        }

        if (hasValue(rtlWhCd) && !isEmpty(rtlWhCdList)) {
            if (rtlWhCdList.contains(rtlWhCd)) {
                return true;
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    private void moveAtpInfoToAtpHist() {
        final String methodNm = "moveAtpInfoToAtpHist";
        methodStartLogOut(getClass(), methodNm);

        try {

            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            final List<ATP_INFOTMsg> atpInfoTMsgList = ssmClient.queryObjectList("getValidAtpInfo", ssmParam);

            infoLogOut(getClass(), "List<ATP_INFOTMsg>.size() = ", atpInfoTMsgList.size());

            // move [ATP_INFO] to [ATP_HIST].
            if (!isEmpty(atpInfoTMsgList)) {

                final List<ATP_HISTTMsg> atpHistTMsgList = new ArrayList<ATP_HISTTMsg>();

                for (ATP_INFOTMsg atpInfoTMsg : atpInfoTMsgList) {

                    final ATP_HISTTMsg atpHistTMsg = new ATP_HISTTMsg();
                    atpHistTMsgList.add(atpHistTMsg);

                    EZDMsg.copy(atpInfoTMsg, null, atpHistTMsg, null);
                    setValue(atpHistTMsg.atpHistPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.ATP_HIST_SQ));

                    if (atpHistTMsgList.size() >= BULK_SIZE) {
                        // ***** [insert]
                        insert(atpHistTMsgList);
                        atpHistTMsgList.clear();
                        continue;
                    }
                }

                // ***** [insert]
                insert(atpHistTMsgList);

                // ***** [removePhysical]
                removePhysical(atpInfoTMsgList);
            }

        } finally {
            methodEndLogOut(getClass(), methodNm);
        }
    }

    private boolean removePhysical(final List... tMsgListArray) {

        for (List<EZDTMsg> tMsgList : tMsgListArray) {
            if (!isEmpty(tMsgList)) {
                final EZDTMsg[] tMsgArray = (EZDTMsg[]) tMsgList.toArray(new EZDTMsg[0]);
                // ***** [removePhysical]
                final int resRecordCnt = S21FastTBLAccessor.removePhysical(tMsgArray);
                infoLogOut(getClass(), "S21FastTBLAccessor.removePhysical(", tMsgArray[0].getTableName(), "). Result record count = [", resRecordCnt, "].");
                if (tMsgArray.length != resRecordCnt) {
                    infoLogOut(getClass(), "Error. S21FastTBLAccessor.removePhysical(" + tMsgArray[0].getTableName() + "). EZDTMsg[].length = [" + tMsgArray.length + "], but result record count = [" + resRecordCnt + "].");
                    return false;
                }
            }
        }

        return true;
    }

    private void splitGroup(List<Map<String, Object>> atpBatWrkInfoList) {
//        println(1, "[ splitGroup ] start", this);

        List<List<Map<String, Object>>> shipCpltGroup = getShipCpltGroup(atpBatWrkInfoList);

        // 2016/04/07 CSA: ReqID P628 Mod Start.
//        List<Map<String, List<String>>> batGrpAttributeLst = new ArrayList<Map<String, List<String>>>();
        List<Map<String, List<Object>>> batGrpAttributeLst = new ArrayList<Map<String, List<Object>>>();
        // 2016/04/07 CSA: ReqID P628 Mod End.

        int attributeNum = 0;

        for (int i = 0; i < shipCpltGroup.size();) {

            List<Map<String, Object>> group = shipCpltGroup.get(i);

            int batGrpCdWrk = -1;

            for (int j = 0; j < batGrpAttributeLst.size(); j++) {

                // 2016/04/07 CSA: ReqID P628 Mod Start.
//                Map<String, List<String>> batGrpDetailMap = batGrpAttributeLst.get(j);
                Map<String, List<Object>> batGrpDetailMap = batGrpAttributeLst.get(j);
                // 2016/04/07 CSA: ReqID P628 Mod End.

                for (Map<String, Object> atpBatWrkOthMap : group) {

                    if (isParameterGrpCheck(batGrpDetailMap, atpBatWrkOthMap)) {

                        batGrpCdWrk = j;
                        break;
                    }
                }

                if (batGrpCdWrk >= 0) {
                    break;
                }
            }

            if (batGrpCdWrk < 0) {

                attributeNum = batGrpAttributeLst.size();

                // 2016/04/07 CSA: ReqID P628 Mod Start.
//                Map<String, List<String>> batGrpDetailMap = new HashMap<String, List<String>>();
                Map<String, List<Object>> batGrpDetailMap = new HashMap<String, List<Object>>();
                // 2016/04/07 CSA: ReqID P628 Mod End.

                batGrpAttributeLst.add(batGrpDetailMap);

            } else {
                attributeNum = batGrpCdWrk;
            }

            // 2016/04/07 CSA: ReqID P628 Mod Start.
//            Map<String, List<String>> batGrpDetailMap = batGrpAttributeLst.get(attributeNum);
            Map<String, List<Object>> batGrpDetailMap = batGrpAttributeLst.get(attributeNum);
            // 2016/04/07 CSA: ReqID P628 Mod End.

            addAttributeLst(batGrpDetailMap, group);

            for (Map<String, Object> atpBatWrkOthMap : group) {
                atpBatWrkOthMap.put(BAT_GRP_CD,     (attributeNum % multipleTotal) + 1);
                atpBatWrkOthMap.put(PROC_GRP_NUM,   (attributeNum - (attributeNum % multipleTotal)) / multipleTotal + 1);
            }

            shipCpltGroup.remove(i);

            splitGroupSub(attributeNum, shipCpltGroup, batGrpDetailMap, batGrpAttributeLst);
        }

//        println(1, "[ splitGroup ] end", this);
    }

    // 2016/04/07 CSA: ReqID P628 Mod Start.
//    private void splitGroupSub(int attributeNum, List<List<Map<String, Object>>> shipCpltGroup, Map<String, List<String>> batGrpDetailMap, List<Map<String, List<String>>> batGrpAttributeLst) {
    private void splitGroupSub(int attributeNum, List<List<Map<String, Object>>> shipCpltGroup, Map<String, List<Object>> batGrpDetailMap, List<Map<String, List<Object>>> batGrpAttributeLst) {
    // 2016/04/07 CSA: ReqID P628 Mod End.
//        println(1, "[ splitGroupSub ] start", this);

        for (int i = 0; i < shipCpltGroup.size();) {

            List<Map<String, Object>> group = shipCpltGroup.get(i);

            boolean isGropFlg = false;

            for (Map<String, Object> atpBatWrkOthMap : group) {

                if (isParameterGrpCheck(batGrpDetailMap, atpBatWrkOthMap)) {
                    isGropFlg = true;
                }
            }

            if (isGropFlg) {

                for (Map<String, Object> atpBatWrkOthMap : group) {
                    atpBatWrkOthMap.put(BAT_GRP_CD,     (attributeNum % multipleTotal) + 1);
                    atpBatWrkOthMap.put(PROC_GRP_NUM,   (attributeNum - (attributeNum % multipleTotal)) / multipleTotal + 1);
                }

                shipCpltGroup.remove(i);

                if (addAttributeLst(batGrpDetailMap, group)) {
                    i = 0;
                }
            } else {
                i++;
            }
        }

//        println(1, "[ splitGroupSub ] end", this);
    }

    private ATP_BAT_WRKTMsg toAtpBatWrkTMsg(Map atpBatWrkMap) {

        int batGrpCd    = (Integer) atpBatWrkMap.get(BAT_GRP_CD);
        int procGrpNum  = (Integer) atpBatWrkMap.get(PROC_GRP_NUM);
        int procSqNum   = (Integer) atpBatWrkMap.get(PROC_SQ_NUM);

        String orderFlg         = null;
        String shpgPlnNum       = null;
        BigDecimal softAllocPk  = null;
        BigDecimal distPk       = null;
        String distTmFrameNum   = null;

        if (!(atpBatWrkMap.get(ORDER_FLG) == null)) {
            orderFlg = atpBatWrkMap.get(ORDER_FLG).toString();
        }
        if (!(atpBatWrkMap.get(SHPG_PLN_NUM) == null)) {
            shpgPlnNum = atpBatWrkMap.get(SHPG_PLN_NUM).toString();
        }
        if (!(atpBatWrkMap.get(SOFT_ALLOC_PK) == null)) {
            softAllocPk = (BigDecimal) atpBatWrkMap.get(SOFT_ALLOC_PK);
        }
        if (!(atpBatWrkMap.get(DIST_PK) == null)) {
            distPk = (BigDecimal) atpBatWrkMap.get(DIST_PK);
        }
        if (!(atpBatWrkMap.get(DIST_TM_FRAME_NUM) == null)) {
            distTmFrameNum = atpBatWrkMap.get(DIST_TM_FRAME_NUM).toString();
        }

        ATP_BAT_WRKTMsg inMsg = new ATP_BAT_WRKTMsg();
        setValue(inMsg.glblCmpyCd,  glblCmpyCd);
        setValue(inMsg.batGrpCd,    Integer.toString(batGrpCd));
        setValue(inMsg.procGrpNum,  Integer.toString(procGrpNum));
        setValue(inMsg.procSqNum,   Integer.toString(procSqNum));

        // Processing is divided by the order and the distribution remainder.
        if (ORD_FLG_ORD.equals(orderFlg)) {
            setValue(inMsg.shpgPlnNum, shpgPlnNum);
            setValue(inMsg.softAllocPk, softAllocPk);
        } else {
            setValue(inMsg.distPk, distPk);
            setValue(inMsg.distTmFrameNum, distTmFrameNum);
        }

        return inMsg;
    }

    private boolean update(final List... tMsgListArray) {

        for (List<EZDTMsg> tMsgList : tMsgListArray) {
            if (!isEmpty(tMsgList)) {
                EZDTMsg[] tMsgArray = tMsgList.toArray(new EZDTMsg[0]);
                // ***** [update]
                int resRecordCnt = S21FastTBLAccessor.update(tMsgArray);
                infoLogOut(getClass(), "S21FastTBLAccessor.update(", tMsgArray[0].getTableName(), "). Result record count = [", resRecordCnt, "].");
                if (tMsgArray.length != resRecordCnt) {
                    infoLogOut(getClass(), "Error. S21FastTBLAccessor.update(" + tMsgArray[0].getTableName() + "). EZDTMsg[].length = [" + tMsgArray.length + "], but result record count = [" + resRecordCnt + "].");
                    return false;
                }
            }
        }

        return true;
    }

    private void updateSplyInfo() {
        final String methodNm = "updateSplyInfo";
        methodStartLogOut(getClass(), methodNm);

        try {

            SPLY_INFOTMsg reqSplyInfoTMsg = new SPLY_INFOTMsg();
            reqSplyInfoTMsg.setSQLID("001");
            reqSplyInfoTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

            SPLY_INFOTMsgArray resSplyInfoTMsgArray = (SPLY_INFOTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(reqSplyInfoTMsg);

            final List<SPLY_INFOTMsg> splyInfoTMsgList = new ArrayList<SPLY_INFOTMsg>();

            for (int i = 0; i < resSplyInfoTMsgArray.getValidCount(); i++) {

                SPLY_INFOTMsg splyInfoTMsg = resSplyInfoTMsgArray.no(i);
                splyInfoTMsgList.add(splyInfoTMsg);

                // RCV_PLN_QTY -> AVAL_QTY
                setValue(splyInfoTMsg.avalQty, splyInfoTMsg.rcvPlnQty);

                if (splyInfoTMsgList.size() >= BULK_SIZE) {
                    // ***** [update]
                    update(splyInfoTMsgList);
                    splyInfoTMsgList.clear();
                    continue;
                }
            }

            // ***** [update]
            update(splyInfoTMsgList);

        } finally {
            methodEndLogOut(getClass(), methodNm);
        }
    }

    private static void infoLogOut(Class caller, final Object... debugInfos) {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("<<").append(caller.getName()).append(">> ");
        for (final Object debugInfo : debugInfos) {
            sb.append(debugInfo);
        }
//        S21InfoLogOutput.println(sb.toString());
    }

    private static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    private static void methodEndLogOut(Class caller, final String methodNm) {
        infoLogOut(caller, new StringBuilder().append("[E N D]").append("******************** method : [").append(methodNm).append("]").toString());
    }

    private static void methodStartLogOut(Class caller, final String methodNm) {
        infoLogOut(caller, new StringBuilder().append("[START]").append("******************** method : [").append(methodNm).append("]").toString());
    }

    private final class ShplyInfoCreator extends S21SsmVoidResultSetHandlerSupport {

        private S21SsmEZDOracleJDBCUtil jdbcUtil = S21SsmEZDOracleJDBCUtil.getInstance();

        private SplyInfoCreateBy createBy;

        private ShplyInfoCreator(SplyInfoCreateBy createBy) {
            this.createBy = createBy;
        }

        @Override
        protected void doProcessQueryResult(final ResultSet rs) throws SQLException {
            final String methodNm = "doProcessQueryResult";
            methodStartLogOut(getClass(), methodNm);

            try {

                rs.last();
                final int records = rs.getRow();
                rs.first();
                infoLogOut(getClass(), "records = ", records);

                if (records > 0) {
                    // INVTY
                    if (SplyInfoCreateBy.INVTY == createBy) {
                        createByInvty(rs);
                    // INBD_VIS
                    } else {
                        createByInbdVis(rs);
                    }
                }

            } finally {
                methodEndLogOut(getClass(), methodNm);
            }
        }

        private void createByInbdVis(ResultSet rs) throws SQLException {
            final String methodNm = "createByInbdVis";
            methodStartLogOut(getClass(), methodNm);

            try {

                final List<SPLY_INFOTMsg> splyInfoTMsgList = new ArrayList<SPLY_INFOTMsg>();

                do {

                    String inbdVisEventCd   = jdbcUtil.getString(rs, INBD_VIS_EVENT_CD);
                    String imptInvNum       = jdbcUtil.getString(rs, IMPT_INV_NUM);
                    String imptInvDoNum     = jdbcUtil.getString(rs, IMPT_INV_DO_NUM);
                    String imptCntnrNum     = jdbcUtil.getString(rs, IMPT_CNTNR_NUM);

                    if (INBD_VIS_EVENT.DOMESTIC_PO_RECEIVE.equals(inbdVisEventCd)) {

                        imptInvDoNum = DOMES;
                        imptCntnrNum = DOMES;

                    } else if (INBD_VIS_EVENT.DC_TRANSFER_RECEIVE.equals(inbdVisEventCd)) {

                        imptInvDoNum = XFER;
                        imptCntnrNum = XFER;

                    } else if (INBD_VIS_EVENT.REFURBISH_RECEIVE.equals(inbdVisEventCd)) {

                        imptInvDoNum = REFURB;
                        imptCntnrNum = REFURB;

                    } else if (INBD_VIS_EVENT.KITTING_RECEIVE.equals(inbdVisEventCd)) {

                        imptInvDoNum = KIT;
                        imptCntnrNum = KIT;

                    } else if (INBD_VIS_EVENT.POYO_RECEIVE.equals(inbdVisEventCd)) {

//                        if (inbdVisEventCd == null) { // 2013/04/09 Mod
                        if (imptInvNum == null) {
                            imptInvNum = POYO;
                        }

                        if (imptInvDoNum == null) {
                            imptInvDoNum = POYO;
                        }

                        if (imptCntnrNum == null) {
                            imptCntnrNum = POYO;
                        }
                    }

                    final SPLY_INFOTMsg splyInfoTMsg = new SPLY_INFOTMsg();
                    splyInfoTMsgList.add(splyInfoTMsg);

                    setValue(splyInfoTMsg.glblCmpyCd,       glblCmpyCd);
                    setValue(splyInfoTMsg.splyInfoPk,       getNextSplyInfoPk());
                    setValue(splyInfoTMsg.inbdVisEventCd,   inbdVisEventCd);
                    setValue(splyInfoTMsg.imptInvNum,       imptInvNum);
                    setValue(splyInfoTMsg.imptCntnrNum,     imptCntnrNum);
                    setValue(splyInfoTMsg.vndInvDoNum,      imptInvDoNum);
                    setValue(splyInfoTMsg.invtyLocCd,       jdbcUtil.getString(rs, VIS_LOC_CD));
                    setValue(splyInfoTMsg.stkStsCd,         jdbcUtil.getString(rs, INVTY_STK_STS_CD));
                    setValue(splyInfoTMsg.rcvPlnQty,        jdbcUtil.getBigDecimal(rs, VIS_QTY));
                    setValue(splyInfoTMsg.mdseCd,           jdbcUtil.getString(rs, MDSE_CD));
                    setValue(splyInfoTMsg.avalQty,          jdbcUtil.getBigDecimal(rs, VIS_QTY));
                    setValue(splyInfoTMsg.etaOrEtdDt,       jdbcUtil.getString(rs, ETA_ETD_DT));
                    setValue(splyInfoTMsg.splyCalcDt,       salesDt);
                    setValue(splyInfoTMsg.inbdVisDataTpCd,  jdbcUtil.getString(rs, INBD_VIS_DATA_TP_CD));

                    if (splyInfoTMsgList.size() >= BULK_SIZE) {
                        // ***** [insert]
                        insert(splyInfoTMsgList);
                        splyInfoTMsgList.clear();

                        continue;
                    }

                } while (rs.next());

                // ***** [insert]
                insert(splyInfoTMsgList);

            } finally {
                methodEndLogOut(getClass(), methodNm);
            }
        }

        private void createByInvty(ResultSet rs) throws SQLException {
            final String methodNm = "createByInvty";
            methodStartLogOut(getClass(), methodNm);

            try {

                final List<SPLY_INFOTMsg> splyInfoTMsgList = new ArrayList<SPLY_INFOTMsg>();

                do {

                    final SPLY_INFOTMsg splyInfoTMsg = new SPLY_INFOTMsg();
                    splyInfoTMsgList.add(splyInfoTMsg);

                    setValue(splyInfoTMsg.glblCmpyCd,   glblCmpyCd);
                    setValue(splyInfoTMsg.splyInfoPk,   getNextSplyInfoPk());
                    setValue(splyInfoTMsg.imptInvNum,   STOCK);
                    setValue(splyInfoTMsg.imptCntnrNum, STOCK);
                    setValue(splyInfoTMsg.vndInvDoNum,  STOCK);
                    setValue(splyInfoTMsg.invtyLocCd,   jdbcUtil.getString(rs, INVTY_LOC_CD));
                    setValue(splyInfoTMsg.stkStsCd,     jdbcUtil.getString(rs, STK_STS_CD));
                    setValue(splyInfoTMsg.rcvPlnQty,    jdbcUtil.getBigDecimal(rs, INVTY_AVAL_QTY));
                    setValue(splyInfoTMsg.mdseCd,       jdbcUtil.getString(rs, MDSE_CD));
                    setValue(splyInfoTMsg.avalQty,      jdbcUtil.getBigDecimal(rs, INVTY_AVAL_QTY));
                    setValue(splyInfoTMsg.etaOrEtdDt,   salesDt);
                    setValue(splyInfoTMsg.splyCalcDt,   salesDt);
                    setValue(splyInfoTMsg.inbdVisDataTpCd, INBD_VIS_DATA_TP.STOCK_IN_DC);

                    if (splyInfoTMsgList.size() >= BULK_SIZE) {
                        // ***** [insert]
                        insert(splyInfoTMsgList);
                        splyInfoTMsgList.clear();

                        continue;
                    }

                } while (rs.next());

                // ***** [insert]
                insert(splyInfoTMsgList);

            } finally {
                methodEndLogOut(getClass(), methodNm);
            }
        }

        private BigDecimal getNextSplyInfoPk() {
            return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SPLY_INFO_SQ);
        }

    }

    /**
     * enum SplyInfoCreateBy
     */
    private static enum SplyInfoCreateBy {

        INVTY,

        INBD_VIS
    }
}
