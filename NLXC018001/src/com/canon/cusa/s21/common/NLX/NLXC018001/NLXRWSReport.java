/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC018001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import parts.common.EZDTStringItem;
import business.db.RWS_MSGTMsg;
import business.db.RWS_MSGTMsgArray;
import business.db.RWS_RPT_WRKTMsg;
import business.db.RWS_RPT_WRKTMsgArray;

import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.common.NLX.NLXC015001.NLXC015001;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/16/2009   Fujitsu         M.Irisawa       Create          N/A
 * 10/16/2009   Fujitsu         A.Akabane       Update          760
 * 07/14/2010   Fujitsu         R.Mori          Update          7680
 * 01/18/2010   CSAI            M.Takahashi     Update          
 * 2014/06/12   Fujitsu         M.Yamada        Update          CSA#435
 *</pre>
 */
public class NLXRWSReport {
    /**
     */
    public static final String RTN_CD_NORMAL = "0";

    /**
     */
    public static final String RTN_CD_ERROR = "1";

    /**
     * SQLID01
     */
    //    private static final String SQLID01 = "001";
    /**
     */
    private static final String NLXM1005E = "NLXM1005E";

    /**
     */
    private static final String NLXM1006E = "NLXM1006E";

    /**
     */
    private static final String NLXM1007E = "NLXM1007E";

    /**
     */
    private static final String NLXM1008E = "NLXM1008E";

    /**
     */
    private static final String NZZM0000E = "NZZM0000E";

    /**     */
    private static final String DATE_PATTERN = "yyyyMMdd HH:mm"; // 2014/06/12 Mod CSA#435

    /**
     */
    private static final String IMPT_PACK_SLP_LINE_NUM_BASE = "001";

    /**
     * WH_CD
     */
    private static final String WH_CD = "WH_CD";

    /**
     * WH_LOC_NM
     */
    private static final String WH_LOC_NM = "WH_LOC_NM";

    /**
     * SCE_ORD_TP_CD
     */
    private static final String SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /**
     * SCE_ORD_TP_NM
     */
    private static final String SCE_ORD_TP_NM = "SCE_ORD_TP_NM";

    /**
     * RWS_REF_NUM
     */
    private static final String RWS_REF_NUM = "RWS_REF_NUM";

    /**
     * RWS_NUM
     */
    private static final String RWS_NUM = "RWS_NUM";

    /**
     * LOC_TP_NM
     */
    private static final String LOC_TP_NM = "LOC_TP_NM";

    /**
     * FROM_LOC_CD
     */
    private static final String FROM_LOC_CD = "FROM_LOC_CD";

    /**
     * FROM_LOC_NM_01
     */
    private static final String FROM_LOC_NM_01 = "FROM_LOC_NM_01";

    /**
     * FROM_LOC_ADDR_01
     */
    private static final String FROM_LOC_ADDR_01 = "FROM_LOC_ADDR_01";

    /**
     * FROM_LOC_ADDR_02
     */
    private static final String FROM_LOC_ADDR_02 = "FROM_LOC_ADDR_02";

    /**
     * FROM_LOC_ADDR_03
     */
    private static final String FROM_LOC_ADDR_03 = "FROM_LOC_ADDR_03";

    /**
     * FROM_LOC_ADDR_04
     */
    private static final String FROM_LOC_ADDR_04 = "FROM_LOC_ADDR_04";

    /**
     * FROM_LOC_CTY_ADDR
     */
    private static final String FROM_LOC_CTY_ADDR = "FROM_LOC_CTY_ADDR";

    /**
     * FROM_LOC_ST_CD
     */
    private static final String FROM_LOC_ST_CD = "FROM_LOC_ST_CD";

    /**
     * FROM_LOC_POST_CD
     */
    private static final String FROM_LOC_POST_CD = "FROM_LOC_POST_CD";

    /**
     * RWS_CARR_CD
     */
    private static final String RWS_CARR_CD = "RWS_CARR_CD";

    /**
     * CARR_LOC_NM
     */
    private static final String CARR_LOC_NM = "CARR_LOC_NM";

    /**
     * FIRST_LINE_ADDR
     */
    private static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /**
     * SCD_LINE_ADDR
     */
    private static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /**
     * THIRD_LINE_ADDR
     */
    private static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /**
     * FRTH_LINE_ADDR
     */
    private static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /**
     * CTY_ADDR
     */
    private static final String CTY_ADDR = "CTY_ADDR";

    /**
     * ST_CD
     */
    private static final String ST_CD = "ST_CD";

    /**
     * POST_CD
     */
    private static final String POST_CD = "POST_CD";

    /**
     * WH_IN_ETA_DT
     */
    private static final String WH_IN_ETA_DT = "WH_IN_ETA_DT";

    /**
     * INLND_SHPG_METH_CD
     */
    private static final String INLND_SHPG_METH_CD = "INLND_SHPG_METH_CD";

    /**
     * INLND_SHPG_METH_NM
     */
    private static final String INLND_SHPG_METH_NM = "INLND_SHPG_METH_NM";

    /**
     * IMPT_INV_SHPG_METH_CD
     */
    private static final String IMPT_INV_SHPG_METH_CD = "IMPT_INV_SHPG_METH_CD";

    /**
     * IMPT_INV_SHPG_METH_NM
     */
    private static final String IMPT_INV_SHPG_METH_NM = "IMPT_INV_SHPG_METH_NM";

    /**
     * CNTNR_SEAL_NUM
     */
    private static final String CNTNR_SEAL_NUM = "CNTNR_SEAL_NUM";

    /**
     * OUT_PACK_CSE_QTY
     */
    private static final String OUT_PACK_CSE_QTY = "OUT_PACK_CSE_QTY";

    /**
     * FIRST_PROD_CTRL_CD
     */
    private static final String FIRST_PROD_CTRL_CD = "FIRST_PROD_CTRL_CD";

    /**
     * FIRST_PROD_CTRL_NM
     */
    private static final String FIRST_PROD_CTRL_NM = "FIRST_PROD_CTRL_NM";

    /**
     * MDSE_CD
     */
    private static final String MDSE_CD = "MDSE_CD";

    /**
     * MDSE_NM
     */
    private static final String MDSE_NM = "MDSE_NM";

    /**
     * RWS_QTY
     */
    private static final String RWS_QTY = "RWS_QTY";

    /**
     * INVTY_STK_STS_CD
     */
    private static final String INVTY_STK_STS_CD = "INVTY_STK_STS_CD";

    /**
     * STK_STS_NM
     */
    private static final String STK_STS_NM = "STK_STS_NM";

    /**
     * IMPT_ITEM_PACK_TP_CD
     */
    private static final String IMPT_ITEM_PACK_TP_CD = "IMPT_ITEM_PACK_TP_CD";

    /**
     * IMPT_PACK_SLP_LINE_NUM
     */
    private static final String IMPT_PACK_SLP_LINE_NUM = "IMPT_PACK_SLP_LINE_NUM";

    /**
     * BIN_NUM
     */
    private static final String BIN_NUM = "BIN_NUM";

    /**
     */
    private static final int NUM0 = 0;

    /**
     */
    private static final int NUM1 = 1;

    /**
     */
    private static final int NUM2 = 2;

    /**
     */
    private static final int NUM3 = 3;

    /**     */
    private static final int SYSDT_DT_FROM = 0;

    /**     */
    private static final int SYSDT_DT_TO = 8; // 2014/06/12 Mod CSA#435

    /**     */
    private static final int SYSDT_HM_FROM = 9; // 2014/06/12 Mod CSA#435

    /**     */
    private static final int SYSDT_HM_TO = 14; // 2014/06/12 Mod CSA#435

    //    /**
    //     */
    //    private static final int WHINETADT_YEAR_FROM = 0;
    //
    //    /**
    //     */
    //    private static final int WHINETADT_YEAR_TO = 4;
    //
    //    /**
    //     */
    //    private static final int WHINETADT_MONTH_TO = 6;
    //
    //    /**
    //     */
    //    private static final int WHINETADT_DAY_TO = 8;

    /**
     * GLBL_CMPY_CD
     */
    private String glblCmpyCdM;

    /**
     * USR_ID
     */
    private String usrIdM;

    /**
     * PROC_START_TS
     */
    private String procStartTsM;

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param usrId usrId
     * @param procStartTs procStartTs
     * @param rwsNumList rwsNumList
     * @param xxMsgIdList xxMsgIdList
     * @return String
     * @throws SQLException SQLException
     */
    public String setRwsRptWrk(String glblCmpyCd, String usrId, String procStartTs, List<String> rwsNumList, List<String> xxMsgIdList) throws SQLException {

        checkParam(glblCmpyCd, usrId, procStartTs, rwsNumList, xxMsgIdList);

        if (NLXC015001.hasValue(xxMsgIdList)) {
            return RTN_CD_ERROR;
        }

        this.glblCmpyCdM = glblCmpyCd;
        this.usrIdM = usrId;
        this.procStartTsM = procStartTs;

        RWS_RPT_WRKTMsgArray resultEditData = selectRws(rwsNumList, xxMsgIdList);

        if (resultEditData != null) {
            registRwsData(resultEditData, xxMsgIdList);
        }

        if (NLXC015001.hasValue(xxMsgIdList)) {
            return RTN_CD_ERROR;
        }
        return RTN_CD_NORMAL;
    }

    /**
     */
    private void checkParam(String glblCmpyCd, String usrId, String procStartTs, List<String> rwsNumList, List<String> xxMsgIdList) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1005E), this);
            xxMsgIdList.add(NLXM1005E);
            return;
        }

        if (!ZYPCommonFunc.hasValue(usrId)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1005E), this);
            xxMsgIdList.add(NLXM1005E);
            return;
        }

        if (!ZYPCommonFunc.hasValue(procStartTs)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1005E), this);
            xxMsgIdList.add(NLXM1005E);
            return;
        }

        if (!NLXC015001.hasValue(rwsNumList)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1006E), this);
            xxMsgIdList.add(NLXM1006E);
            return;
        }
    }

    /**
     * @throws SQLException SQLException
     */
    private RWS_RPT_WRKTMsgArray selectRws(List<String> rwsNumList, List<String> xxMsgIdList) throws SQLException {
        S21SsmLowLevelCodingClient ssmLowLev = S21SsmLowLevelCodingClient.getClient(this.getClass());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCdM);
        paramMap.put("rwsNumList", rwsNumList);
        paramMap.put("unbdOtbdCd", INBD_OTBD.INBOUND);
        RWS_RPT_WRKTMsgArray resultEditData;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = ssmLowLev.createPreparedStatement("selectRws", paramMap);
            resultSet = pstmt.executeQuery();

            if (!resultSet.next()) {
                EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NZZM0000E), this);
                xxMsgIdList.add(NZZM0000E);
                return null;
            }

            String preRwsNum = "";
            Map<String, BigDecimal> mapOutPackCseQty = new HashMap<String, BigDecimal>();
            BigDecimal totalOutPackCseQty = BigDecimal.ZERO;

            boolean bQtyFlg = false;

            resultSet.first();
            for (;;) {

                String rwsNum = resultSet.getString(RWS_NUM);
                String sceOrdTpCd = resultSet.getString(SCE_ORD_TP_CD);
                BigDecimal outPackCseQty = NLXC014001.nullToZero(resultSet.getBigDecimal(OUT_PACK_CSE_QTY));

                if ("".equals(preRwsNum)) {
                    preRwsNum = rwsNum;
                }

                if (!rwsNum.equals(preRwsNum)) {
                    mapOutPackCseQty.put(preRwsNum, totalOutPackCseQty);
                    totalOutPackCseQty = BigDecimal.ZERO;
                    bQtyFlg = false;

                } else {

                    if (NLXSceConst.SCE_ORD_TP_CD_IV.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_IO.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_CT.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TP_CD_CO.equals(sceOrdTpCd)
                            || NLXSceConst.SCE_ORD_TP_CD_CA.equals(sceOrdTpCd)) {

                        if (!bQtyFlg) {
                            Map<String, Object> param = new HashMap<String, Object>();
                            param.put("glblCmpyCd", this.glblCmpyCdM);
                            param.put("rwsNum", rwsNum);
                            param.put("unbdOtbdCd", INBD_OTBD.INBOUND);

                            PreparedStatement pstmtoutPackCseQty = null;
                            ResultSet resultSetOutPackCseQty = null;

                            bQtyFlg = true;
                            try {
                                pstmtoutPackCseQty = ssmLowLev.createPreparedStatement("getOutPackCseQty", param);
                                resultSetOutPackCseQty = pstmtoutPackCseQty.executeQuery();

                                while (resultSetOutPackCseQty.next()) {
                                    String imptItemPackTpCd = NLXC014001.nullToEmpty(resultSetOutPackCseQty.getString(IMPT_ITEM_PACK_TP_CD));
                                    String imptPackSlpLineNum = NLXC014001.nullToEmpty(resultSetOutPackCseQty.getString(IMPT_PACK_SLP_LINE_NUM));

                                    if (ZYPConstant.FLG_OFF_N.equals(imptItemPackTpCd) || (ZYPConstant.FLG_ON_Y.equals(imptItemPackTpCd) && IMPT_PACK_SLP_LINE_NUM_BASE.equals(imptPackSlpLineNum))) {
                                        totalOutPackCseQty = totalOutPackCseQty.add(NLXC014001.nullToZero(resultSetOutPackCseQty.getBigDecimal(OUT_PACK_CSE_QTY)));
                                    }
                                }

                            } finally {
                                S21SsmLowLevelCodingClient.closeResource(pstmtoutPackCseQty, resultSetOutPackCseQty);
                            }
                        }

                    } else {
                        totalOutPackCseQty = totalOutPackCseQty.add(outPackCseQty);
                    }

                    if (resultSet.next() == false) {
                        mapOutPackCseQty.put(preRwsNum, totalOutPackCseQty);
                        break;
                    }

                }
                preRwsNum = rwsNum;
            }

            if (NLXC015001.hasValue(xxMsgIdList)) {
                return null;
            }

            Map<String, RWS_MSGTMsgArray> resultMsgMap = selectMessageBulk(rwsNumList);

            resultEditData = editRwsData(resultSet, resultMsgMap, xxMsgIdList, mapOutPackCseQty);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(pstmt, resultSet);
        }
        return resultEditData;
    }

    /**
     */
    //    private Map<String, RWS_MSGTMsgArray> selectMessage(List<String> rwsNumList) {
    //        Map<String, RWS_MSGTMsgArray> resultMsgMap = new HashMap<String, RWS_MSGTMsgArray>();
    //        for (String rwsNum : rwsNumList) {
    //            RWS_MSGTMsgArray rwsMsgArray = null;
    //            RWS_MSGTMsg rwsMsgTMsg = new RWS_MSGTMsg();
    //            rwsMsgTMsg.setSQLID(SQLID01);
    //            rwsMsgTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCdM);
    //            rwsMsgTMsg.setConditionValue("rwsNum01", rwsNum);
    //            rwsMsgTMsg.setConditionValue("ezCancelFlag", "0");
    //            rwsMsgArray = (RWS_MSGTMsgArray) S21ApiTBLAccessor.findByCondition(rwsMsgTMsg);
    //            if (rwsMsgArray != null && rwsMsgArray.length() > 0) {
    //                resultMsgMap.put(rwsNum, rwsMsgArray);
    //            } else {
    //                resultMsgMap.put(rwsNum, new RWS_MSGTMsgArray());
    //            }
    //        }
    //        return resultMsgMap;
    //    }
    /**
     */
    private Map<String, RWS_MSGTMsgArray> selectMessageBulk(List<String> rwsNumList) throws SQLException {

        S21SsmLowLevelCodingClient ssmLowLev = S21SsmLowLevelCodingClient.getClient(this.getClass());
        Map<String, RWS_MSGTMsgArray> resultMsgMap = new HashMap<String, RWS_MSGTMsgArray>();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        for (String rwsNum : rwsNumList) {
            resultMsgMap.put(rwsNum, new RWS_MSGTMsgArray());
        }

        try {

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCdM);
            paramMap.put("rwsNumList", rwsNumList);
            pstmt = ssmLowLev.createPreparedStatement("selectRwsMessageBulk", paramMap);
            resultSet = pstmt.executeQuery();

            EZDTMsg[] resultArrayWrk_tmp = new EZDTMsg[1];
            String wkRwsNum = "";
            int msgCnt = 0;

            while (resultSet.next()) {

                if (wkRwsNum.equals(resultSet.getString("RWS_NUM"))) {
                    msgCnt = msgCnt + 1;
                    resultArrayWrk_tmp = addResultArrayWrk(msgCnt, resultSet, resultArrayWrk_tmp);

                } else {
                    RWS_MSGTMsgArray resultArray_tmp = new RWS_MSGTMsgArray();
                    resultArray_tmp.setMsgList(resultArrayWrk_tmp);
                    resultMsgMap.remove(wkRwsNum);
                    resultMsgMap.put(wkRwsNum, resultArray_tmp);
                    msgCnt = 0;
                    resultArrayWrk_tmp = new EZDTMsg[1];
                    resultArrayWrk_tmp = addResultArrayWrk(msgCnt, resultSet, resultArrayWrk_tmp);
                    wkRwsNum = resultSet.getString("RWS_NUM");

                }
            }
            RWS_MSGTMsgArray resultArray_tmp = new RWS_MSGTMsgArray();
            resultArray_tmp.setMsgList(resultArrayWrk_tmp);
            resultMsgMap.remove(wkRwsNum);
            resultMsgMap.put(wkRwsNum, resultArray_tmp);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(pstmt, resultSet);
        }
        return resultMsgMap;
    }

    private EZDTMsg[] addResultArrayWrk(int arryaCnt, ResultSet resultSet, EZDTMsg[] resultArrayWrk_tmp) throws SQLException {

        EZDTMsg[] resultArrayWrk = new EZDTMsg[arryaCnt + 1];

        for (int i = 0; i < resultArrayWrk_tmp.length; i++) {
            resultArrayWrk[i] = resultArrayWrk_tmp[i];
        }
        RWS_MSGTMsg tempMsg = new RWS_MSGTMsg();
        ZYPEZDItemValueSetter.setValue( tempMsg.rwsMsgTxt, resultSet.getString("RWS_MSG_TXT"));
        ZYPEZDItemValueSetter.setValue( tempMsg.rwsNum, resultSet.getString("RWS_NUM"));
        ZYPEZDItemValueSetter.setValue( tempMsg.rwsMsgSqNum, resultSet.getString("RWS_MSG_SQ_NUM"));        
        resultArrayWrk[arryaCnt] = tempMsg;

        return resultArrayWrk;
    }

    /**
     * @throws SQLException
     */
    private RWS_RPT_WRKTMsgArray editRwsData(ResultSet resultSet, Map<String, RWS_MSGTMsgArray> resultMsgMap, List<String> xxMsgIdList, Map<String, BigDecimal> mapOutPackCseQty) throws SQLException {
        resultSet.last();
        EZDTMsg[] resultArrayWrk = new EZDTMsg[resultSet.getRow()];

        String strDate = ZYPDateUtil.getCurrentSystemTime(DATE_PATTERN);
        String sysDtTxt = strDate.substring(SYSDT_DT_FROM, SYSDT_DT_TO);
        String sysDtTxtTm = strDate.substring(SYSDT_HM_FROM, SYSDT_HM_TO);

        int lineNum = 1;
        String rwsNumOld = "";
        BigDecimal totalOutPackCseQty = BigDecimal.ZERO;

        resultSet.beforeFirst();
        while (resultSet.next()) {
            RWS_RPT_WRKTMsg wrkTMsg = new RWS_RPT_WRKTMsg();
            String rwsNum = resultSet.getString(RWS_NUM);

            if (!rwsNumOld.equals(rwsNum)) {
                lineNum = 1;
                rwsNumOld = rwsNum;
                totalOutPackCseQty = mapOutPackCseQty.get(rwsNum);
            }
            ZYPEZDItemValueSetter.setValue(wrkTMsg.glblCmpyCd, this.glblCmpyCdM);
            ZYPEZDItemValueSetter.setValue(wrkTMsg.usrId, this.usrIdM);
            ZYPEZDItemValueSetter.setValue(wrkTMsg.procStartTs, this.procStartTsM);
            ZYPEZDItemValueSetter.setValue(wrkTMsg.rwsNum, rwsNum);
            ZYPEZDItemValueSetter.setValue(wrkTMsg.itemLineNum, String.valueOf(lineNum));
            ZYPEZDItemValueSetter.setValue(wrkTMsg.rptDtTxt //
                    , ZYPDateUtil.formatEzd8ToSysDisp(sysDtTxt)); // 2014/06/12 Mod CSA#435
            ZYPEZDItemValueSetter.setValue(wrkTMsg.rptHourMnTxt, sysDtTxtTm);
            if (resultSet.getString(WH_CD) != null || resultSet.getString(WH_LOC_NM) != null) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.whCdTxt, NLXC014001.nullToEmpty(resultSet.getString(WH_CD)) + ":" + NLXC014001.nullToEmpty(resultSet.getString(WH_LOC_NM)));
            }
            if (resultSet.getString(SCE_ORD_TP_CD) != null || resultSet.getString(SCE_ORD_TP_NM) != null) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.rwsTpCdTxt, NLXC014001.nullToEmpty(resultSet.getString(SCE_ORD_TP_CD)) + ":" + NLXC014001.nullToEmpty(resultSet.getString(SCE_ORD_TP_NM)));
            }
            ZYPEZDItemValueSetter.setValue(wrkTMsg.prtRwsNum, resultSet.getString(RWS_REF_NUM));
            ZYPEZDItemValueSetter.setValue(wrkTMsg.locTpNm, resultSet.getString(LOC_TP_NM));
            if (resultSet.getString(FROM_LOC_CD) != null || resultSet.getString(FROM_LOC_NM_01) != null) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.poVndCdTxt, NLXC014001.nullToEmpty(resultSet.getString(FROM_LOC_CD)) + ":" + NLXC014001.nullToEmpty(resultSet.getString(FROM_LOC_NM_01)));
            }
            List<String> poAddrList = new ArrayList<String>();
            poAddrList.add(resultSet.getString(FROM_LOC_ADDR_01));
            poAddrList.add(resultSet.getString(FROM_LOC_ADDR_02));
            poAddrList.add(resultSet.getString(FROM_LOC_ADDR_03));
            poAddrList.add(resultSet.getString(FROM_LOC_ADDR_04));
            List<String> poCtyAddrList = new ArrayList<String>();
            poCtyAddrList.add(resultSet.getString(FROM_LOC_CTY_ADDR));
            poCtyAddrList.add(resultSet.getString(FROM_LOC_ST_CD));
            poCtyAddrList.add(resultSet.getString(FROM_LOC_POST_CD));
            poAddrList.add(createCtyAddr(poCtyAddrList));
            List<EZDTStringItem> poItemList = new ArrayList<EZDTStringItem>();
            poItemList.add(wrkTMsg.poAddr_01);
            poItemList.add(wrkTMsg.poAddr_02);
            poItemList.add(wrkTMsg.poAddr_03);
            poItemList.add(wrkTMsg.poAddr_04);
            poItemList.add(wrkTMsg.poAddr_05);
            setAddr(poItemList, poAddrList);
            if (resultSet.getString(RWS_CARR_CD) != null || resultSet.getString(CARR_LOC_NM) != null) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.carrCdTxt, NLXC014001.nullToEmpty(resultSet.getString(RWS_CARR_CD)) + ":" + NLXC014001.nullToEmpty(resultSet.getString(CARR_LOC_NM)));
            }

            List<String> vndAddrList = new ArrayList<String>();
            vndAddrList.add(resultSet.getString(FIRST_LINE_ADDR));
            vndAddrList.add(resultSet.getString(SCD_LINE_ADDR));
            vndAddrList.add(resultSet.getString(THIRD_LINE_ADDR));
            vndAddrList.add(resultSet.getString(FRTH_LINE_ADDR));
            List<String> vndCtyAddrList = new ArrayList<String>();
            vndCtyAddrList.add(resultSet.getString(CTY_ADDR));
            vndCtyAddrList.add(resultSet.getString(ST_CD));
            vndCtyAddrList.add(resultSet.getString(POST_CD));
            vndAddrList.add(createCtyAddr(vndCtyAddrList));
            List<EZDTStringItem> ctyItemList = new ArrayList<EZDTStringItem>();
            ctyItemList.add(wrkTMsg.carrAddr_01);
            ctyItemList.add(wrkTMsg.carrAddr_02);
            ctyItemList.add(wrkTMsg.carrAddr_03);
            ctyItemList.add(wrkTMsg.carrAddr_04);
            ctyItemList.add(wrkTMsg.carrAddr_05);
            setAddr(ctyItemList, vndAddrList);

            String whInEtaDt = resultSet.getString(WH_IN_ETA_DT);
            if (ZYPCommonFunc.hasValue(whInEtaDt)) {
                if (ZYPDateUtil.checkDate(whInEtaDt)) {
                    ZYPEZDItemValueSetter.setValue(wrkTMsg.whInEtaDtTxt //
                            , ZYPDateUtil.formatEzd8ToSysDisp(whInEtaDt)); // 2014/06/12 Mod CSA#435
                } else {
                    EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1008E), this);
                    xxMsgIdList.add(NLXM1008E);
                    return null;
                }
            }
            String inlndShpgMethCd = resultSet.getString(INLND_SHPG_METH_CD);
            if (ZYPCommonFunc.hasValue(inlndShpgMethCd)) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.shpgMethCdTxt, inlndShpgMethCd + ":" + NLXC014001.nullToEmpty(resultSet.getString(INLND_SHPG_METH_NM)));
            } else if (resultSet.getString(IMPT_INV_SHPG_METH_CD) != null || resultSet.getString(IMPT_INV_SHPG_METH_NM) != null) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.shpgMethCdTxt, NLXC014001.nullToEmpty(resultSet.getString(IMPT_INV_SHPG_METH_CD)) + ":" + NLXC014001.nullToEmpty(resultSet.getString(IMPT_INV_SHPG_METH_NM)));
            }
            wrkTMsg.cntnrSealNum.setValue(NLXC014001.nullToEmpty(resultSet.getString(CNTNR_SEAL_NUM)));

            ZYPEZDItemValueSetter.setValue(wrkTMsg.outPackCseQtyTxt //
                    , ZYPFormatUtil.formatNumToSysDisp(totalOutPackCseQty)); // 2014/06/12 Mod CSA#435 Start

            RWS_MSGTMsgArray msgArray = resultMsgMap.get(rwsNum);
            if (msgArray.length() > NUM0) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.rwsMsgTxt_01, msgArray.no(NUM0).rwsMsgTxt.getValue());
            }
            if (msgArray.length() > NUM1) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.rwsMsgTxt_02, msgArray.no(NUM1).rwsMsgTxt.getValue());
            }
            if (msgArray.length() > NUM2) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.rwsMsgTxt_03, msgArray.no(NUM2).rwsMsgTxt.getValue());
            }
            if (msgArray.length() > NUM3) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.rwsMsgTxt_04, msgArray.no(NUM3).rwsMsgTxt.getValue());
            }
            if (resultSet.getString(FIRST_PROD_CTRL_CD) != null || resultSet.getString(FIRST_PROD_CTRL_NM) != null) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.firstProdCtrlCdTxt, NLXC014001.nullToEmpty(resultSet.getString(FIRST_PROD_CTRL_CD)) + ":" + NLXC014001.nullToEmpty(resultSet.getString(FIRST_PROD_CTRL_NM)));
            }
            ZYPEZDItemValueSetter.setValue(wrkTMsg.mdseCd, resultSet.getString(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(wrkTMsg.mdseNm, resultSet.getString(MDSE_NM));
            if (ZYPCommonFunc.hasValue(resultSet.getBigDecimal(RWS_QTY))) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.rwsQtyTxt //
                        , ZYPFormatUtil.formatNumToSysDisp(resultSet.getBigDecimal(RWS_QTY))); // 2014/06/12 Mod CSA#435 Start
            }
            if (resultSet.getString(INVTY_STK_STS_CD) != null || resultSet.getString(STK_STS_NM) != null) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.rwsStkStsTxt, NLXC014001.nullToEmpty(resultSet.getString(INVTY_STK_STS_CD)) + ":" + NLXC014001.nullToEmpty(resultSet.getString(STK_STS_NM)));
            }

            // add Defect#7680
            ZYPEZDItemValueSetter.setValue(wrkTMsg.binNum, NLXC014001.nullToEmpty(resultSet.getString(BIN_NUM)));

            resultArrayWrk[resultSet.getRow() - 1] = wrkTMsg;
            lineNum++;
        }
        RWS_RPT_WRKTMsgArray resultArray = new RWS_RPT_WRKTMsgArray();
        resultArray.setMsgList(resultArrayWrk);
        return resultArray;
    }

    /**
     */
    private void registRwsData(RWS_RPT_WRKTMsgArray wrkTMsgArray, List<String> xxMsgIdList) {

        RWS_RPT_WRKTMsg[] tmsgs = new RWS_RPT_WRKTMsg[wrkTMsgArray.length()]; //配列を初期化

        for (int i = 0; i < wrkTMsgArray.length(); i++) {
            RWS_RPT_WRKTMsg st = new RWS_RPT_WRKTMsg();
            st = (RWS_RPT_WRKTMsg) wrkTMsgArray.get(i);
            tmsgs[i] = st;

        }

        int retCnt = S21FastTBLAccessor.insert(tmsgs);

        if (retCnt != wrkTMsgArray.length()) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1007E), this);
            xxMsgIdList.add(NLXM1007E);
            return;
        }

        //        for (int i = 0; i < wrkTMsgArray.length(); i++) {
        //            RWS_RPT_WRKTMsg rwsRptWrkT = (RWS_RPT_WRKTMsg) wrkTMsgArray.get(i);
        //            S21ApiTBLAccessor.insert(rwsRptWrkT);
        //            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsRptWrkT.getReturnCode())) {
        //                EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1007E), this);
        //                xxMsgIdList.add(NLXM1007E);
        //                return;
        //            }
        //        }
    }

    //    private String transDt(String string) {
    //        StringBuilder strBuilder = new StringBuilder();
    //        strBuilder.append(string.substring(WHINETADT_YEAR_TO, WHINETADT_MONTH_TO));
    //        strBuilder.append("-");
    //        strBuilder.append(string.substring(WHINETADT_MONTH_TO, WHINETADT_DAY_TO));
    //        strBuilder.append("-");
    //        strBuilder.append(string.substring(WHINETADT_YEAR_FROM, WHINETADT_YEAR_TO));
    //        return strBuilder.toString();
    //    }

    private void setAddr(List<EZDTStringItem> itemList, List<String> addrList) {
        if (!NLXC015001.hasValue(addrList) || !NLXC015001.hasValue(itemList)) {
            return;
        }
        Iterator iteItem = itemList.iterator();
        for (String addr : addrList) {
            if (iteItem.hasNext() && ZYPCommonFunc.hasValue(addr)) {
                EZDTStringItem item = (EZDTStringItem) iteItem.next();
                ZYPEZDItemValueSetter.setValue(item, S21StringUtil.trimString(addr));
            }
        }
    }

    private String createCtyAddr(List<String> addrList) {
        if (!NLXC015001.hasValue(addrList)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addrList.size(); i++) {
            String addr = addrList.get(i);
            if (ZYPCommonFunc.hasValue(addr)) {
                sb.append(addr);
                if (i == 0) {
                    if (addrList.size() != 1) {
                        sb.append(", ");
                    }
                } else if (!(i == addrList.size() - 1)) {
                    sb.append(" ");
                }
            }
        }
        return S21StringUtil.trimString(sb.toString());
    }
}
