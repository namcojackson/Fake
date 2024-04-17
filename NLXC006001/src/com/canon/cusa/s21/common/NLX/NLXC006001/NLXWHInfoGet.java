/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC006001;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * 
 * Date       Company  Name       Create/Update Defect No
 * ----------------------------------------------------------------------
 * 06/15/2009 Fujitsu  M.Irisawa  Create        N/A
 * 11/25/2009 Fujitsu  A.Akabane  Update        RQ448
 * 01/04/2010 Fujitsu  S.Tsuboi   Update        2879
 *</pre>
 */
public class NLXWHInfoGet {

    /**
     */
    public static final String NORMAL_CD = "0";

    /**
     */
    public static final String ERR_CD = "1";

    /**
     */
    private static final int DIGIT_GLBL_CMPY_CD = 4;

    /**
     */
    private static final int DIGIT_INV_CNSGN_CD = 5;

    /**
     */
    private static final int DIGIT_FIRST_PROD_CTRL_CD = 8;

    /**
     */
    private static final int DIGIT_OPS_DT = 8;

    /**
     */
    private static final String NLAM1006E = "NLAM1006E";

    /**
     */
    private static final String NLAM1008E = "NLAM1008E";

    /**
     */
    private static final String NLAM1001E = "NLAM1001E";

    /**
     * statementId:getDefaultLocation
     */
    private static final String GET_DEFAULT_LOCATION = "getDefaultLocation";
    
    /**
     * statementId:getLocationName
     */
    private static final String GET_LOCATION_NAME_FOR_WH = "getLocationNameForWH";
    
    /**
     * statementId:getLocationName
     */
    private static final String GET_LOCATION_NAME_FOR_VND = "getLocationNameForVND";

    /**
     * statementId:getLocationCode
     */
    private static final String GET_LOCATION_CODE = "getLocationCode";

    /**
     */
    private static final String IMPT_INV_CNSGN = "IMPT_INV_CNSGN";

    /**
     */
    private static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /**
     */
    private static final String LOC_TP_CD = "LOC_TP_CD";

    /**
     */
    private static final String LOC_NM = "LOC_NM";    

    /**
     */
    private static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";    

    /**
     */
    private static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";    

    /**
     */
    private static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";    

    /**
     */
    private static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";    

    /**
     */
    private static final String CTY_ADDR = "CTY_ADDR";    

    /**
     */
    private static final String ST_CD = "ST_CD";    

    /**
     */
    private static final String POST_CD = "POST_CD";    

    /**
     */
    private static final String CTRY_CD = "CTRY_CD";    

    /**
     */
    private static final String IMPT_INV_CNSGN_CD = "IMPT_INV_CNSGN_CD";

    /**
     */
    private static final String OPS_DT = "OPS_DT";
    
    /**
     */
    private static final String IMPT_INV_CNSGN_NM = "IMPT_INV_CNSGN_NM";
    
    /**
     */
    private static final String DROP_SHIP_FLG = "DROP_SHIP_FLG";
    
    /**
     * SQL_PARA glblCmpyCd
     */
    private static final String SQL_PARA_GLBL_CMPY_CD = "glblCmpyCd";
    
    /**
     * SQL_PARA whCd
     */
    private static final String SQL_PARA_WH_CD = "whCd";

    /**
     * SQL_PARA vndCd
     */
    private static final String SQL_PARA_VND_CD = "vndCd";

    /**
     * SQL_PARA batProcDate
     */
    private static final String SQL_PARA_BAT_PROC_DATE = "batProcDate";

    /**
     * SQL_PARA invCnsgnCd
     */
    private static final String SQL_PARA_INV_CNSGN_CD = "invCnsgnCd";

    /**
     * SQL_PARA firstProdCtrlCd
     */
    private static final String SQL_PARA_FIRST_PROD_CTRL_CD = "firstProdCtrlCd";
    
    /**
     * SQL_PARA rgtnStsCd
     */
    private static final String SQL_PARA_RGTN_STS_CD = "rgtnStsCd";

    /**
     * KEY_NAME glblCmpyCd
     */
    public static final String KEY_NAME_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * KEY_NAME invCnsgnCd
     */
    public static final String KEY_NAME_INV_CNSGN_CD = "invCnsgnCd";

    /**
     * KEY_NAME grpSlsCd
     */
    public static final String KEY_NAME_GRP_SLS_CD = "grpSlsCd";

    /**
     * KEY_NAME firstProdCtrlCd
     */
    public static final String KEY_NAME_FIRST_PROD_CTRL_CD = "firstProdCtrlCd";

    /**
     * KEY_NAME opsDt
     */
    public static final String KEY_NAME_OPS_DT = "opsDt";

    /**
     * KEY_NAME defInvtyLocCd
     */
    public static final String KEY_NAME_DEF_INVTY_LOC_CD = "defInvtyLocCd";

    /**
     * KEY_NAME defInvtyLocNm
     */
    public static final String KEY_NAME_DEF_INVTY_LOC_NM = "defInvtyLocNm";

    /**
     * KEY_NAME locTpCd
     */
    public static final String KEY_NAME_LOC_TP_CD = "locTpCd";

    /**
     * KEY_NAME_FIRST_LINE_ADDR
     */
    public static final String KEY_NAME_FIRST_LINE_ADDR = "firstLineAddr";

    /**
     * KEY_NAME_SCD_LINE_ADDR
     */
    public static final String KEY_NAME_SCD_LINE_ADDR = "scdLineAddr";

    /**
     * KEY_NAME_THIRD_LINE_ADDR
     */
    public static final String KEY_NAME_THIRD_LINE_ADDR = "thirdLineAddr";

    /**
     * KEY_NAME_FRTH_LINE_ADDR
     */
    public static final String KEY_NAME_FRTH_LINE_ADDR = "frthLineAddr";

    /**
     * KEY_NAME_CTY_ADDR
     */
    public static final String KEY_NAME_CTY_ADDR = "ctyAddr";

    /**
     * KEY_NAME_ST_CD
     */
    public static final String KEY_NAME_ST_CD = "stCd";

    /**
     * KEY_NAME_POST_CD
     */
    public static final String KEY_NAME_POST_CD = "postCd";

    /**
     * KEY_NAME_CTRY_CD
     */
    public static final String KEY_NAME_CTRY_CD = "ctryCd";

    /**
     */
    public static final String KEY_MESSAGE_ID = "KEY_MESSAGE_ID";

    /**
     */
    public static final String KEY_MESSAGE = "KEY_MESSAGE";

    /**
     */
    private S21SsmLowLevelCodingClient ssmLowLev = null;

    /**
     */
    private String glblCmpyCode = null;

    /**
     */
    public NLXWHInfoGet() {
        this.ssmLowLev = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    /**
     * @return String
     * @throws SQLException SQLException
     */  
    public String mainRoutin(Map<String, String> param, List<Map<String, String>> errorList) throws SQLException {
        String returnCode = ERR_CD;
        String msg = null;
        Map<String, String> msgMap = new HashMap<String, String>();

        this.glblCmpyCode = param.get(KEY_NAME_GLBL_CMPY_CD);

        if (!ZYPCommonFunc.hasValue(param.get(KEY_NAME_GLBL_CMPY_CD))) {
            msg = S21MessageFunc.clspGetMessage(NLAM1006E, new String[] {KEY_NAME_GLBL_CMPY_CD });
            msgMap.put(KEY_MESSAGE_ID, NLAM1006E);
            msgMap.put(KEY_MESSAGE, msg);
            errorList.add(msgMap);
            returnCode = ERR_CD;
            return returnCode;
        }

        if (!ZYPCommonFunc.hasValue(param.get(KEY_NAME_INV_CNSGN_CD))) {
            msg = S21MessageFunc.clspGetMessage(NLAM1006E, new String[] {KEY_NAME_INV_CNSGN_CD });
            msgMap.put(KEY_MESSAGE_ID, NLAM1006E);
            msgMap.put(KEY_MESSAGE, msg);
            errorList.add(msgMap);
            returnCode = ERR_CD;
            return returnCode;
        }

        if (!ZYPCommonFunc.hasValue(param.get(KEY_NAME_OPS_DT))) {
            msg = S21MessageFunc.clspGetMessage(NLAM1006E, new String[] {KEY_NAME_OPS_DT });
            msgMap.put(KEY_MESSAGE_ID, NLAM1006E);
            msgMap.put(KEY_MESSAGE, msg);
            errorList.add(msgMap);
            returnCode = ERR_CD;
            return returnCode;
        }

        if ((param.get(KEY_NAME_GLBL_CMPY_CD).length() > DIGIT_GLBL_CMPY_CD)) {
            msg = S21MessageFunc.clspGetMessage(NLAM1008E, new String[] {KEY_NAME_GLBL_CMPY_CD, param.get(KEY_NAME_GLBL_CMPY_CD) });
            msgMap.put(KEY_MESSAGE_ID, NLAM1008E);
            msgMap.put(KEY_MESSAGE, msg);
            errorList.add(msgMap);
            returnCode = ERR_CD;
            return returnCode;
        }

        if ((param.get(KEY_NAME_INV_CNSGN_CD).length() > DIGIT_INV_CNSGN_CD)) {
            msg = S21MessageFunc.clspGetMessage(NLAM1008E, new String[] {KEY_NAME_INV_CNSGN_CD, param.get(KEY_NAME_INV_CNSGN_CD) });
            msgMap.put(KEY_MESSAGE_ID, NLAM1008E);
            msgMap.put(KEY_MESSAGE, msg);
            errorList.add(msgMap);
            returnCode = ERR_CD;
            return returnCode;
        }

        if (ZYPCommonFunc.hasValue(param.get(KEY_NAME_FIRST_PROD_CTRL_CD))) {
            if ((param.get(KEY_NAME_FIRST_PROD_CTRL_CD).length() > DIGIT_FIRST_PROD_CTRL_CD)) {
                msg = S21MessageFunc.clspGetMessage(NLAM1008E, new String[] {KEY_NAME_FIRST_PROD_CTRL_CD, param.get(KEY_NAME_FIRST_PROD_CTRL_CD) });
                msgMap.put(KEY_MESSAGE_ID, NLAM1008E);
                msgMap.put(KEY_MESSAGE, msg);
                errorList.add(msgMap);
                returnCode = ERR_CD;
                return returnCode;
            }
        }

        if ((param.get(KEY_NAME_OPS_DT).length() > DIGIT_OPS_DT)) {
            msg = S21MessageFunc.clspGetMessage(NLAM1008E, new String[] {KEY_NAME_OPS_DT, param.get(KEY_NAME_OPS_DT) });
            msgMap.put(KEY_MESSAGE_ID, NLAM1008E);
            msgMap.put(KEY_MESSAGE, msg);
            errorList.add(msgMap);
            returnCode = ERR_CD;
            return returnCode;
        }

        String glblCmpyCd = param.get(KEY_NAME_GLBL_CMPY_CD);
        String invCnsgnCd = param.get(KEY_NAME_INV_CNSGN_CD);
        String firstProdCtrlCd = param.get(KEY_NAME_FIRST_PROD_CTRL_CD);
        String batProcDate = param.get(KEY_NAME_OPS_DT);

        Map<String, String> queryParamDL = new HashMap<String, String>();
        queryParamDL.put(SQL_PARA_GLBL_CMPY_CD, glblCmpyCd);
        queryParamDL.put(SQL_PARA_INV_CNSGN_CD, invCnsgnCd);
        queryParamDL.put(SQL_PARA_BAT_PROC_DATE, batProcDate);
        PreparedStatement pstmtDL = null;
        ResultSet ssmResultDL = null;
        try {
            pstmtDL = ssmLowLev.createPreparedStatement(GET_DEFAULT_LOCATION, queryParamDL);
            ssmResultDL = pstmtDL.executeQuery();

            if (ssmResultDL.next()) {
            	String nullFlg = ZYPConstant.FLG_OFF_N;
            	
            	if (ssmResultDL.getString(LOC_TP_CD) == null) {
            		nullFlg = ZYPConstant.FLG_ON_Y;
            	}
            	
                if (ZYPConstant.FLG_OFF_N.equals(nullFlg) && LOC_TP.VENDOR.equals(ssmResultDL.getString(LOC_TP_CD))) {
                	
                    param.put(KEY_NAME_LOC_TP_CD, ssmResultDL.getString(LOC_TP_CD));
                	param.put(KEY_NAME_DEF_INVTY_LOC_CD, ssmResultDL.getString(INVTY_LOC_CD));
                    getLocationNameForVND(NLXC014001.nullToEmpty(ssmResultDL.getString(INVTY_LOC_CD)), param);
                    
                } else if (ZYPConstant.FLG_OFF_N.equals(nullFlg) && LOC_TP.INBOUND_CONSIGNEE.equals(ssmResultDL.getString(LOC_TP_CD))) {
                	
                	param.put(KEY_NAME_DEF_INVTY_LOC_CD, ssmResultDL.getString(INVTY_LOC_CD));
                    param.put(KEY_NAME_LOC_TP_CD, ssmResultDL.getString(LOC_TP_CD));
                    param.put(KEY_NAME_DEF_INVTY_LOC_NM, ssmResultDL.getString(IMPT_INV_CNSGN_NM));
                	
                } else if (ZYPConstant.FLG_OFF_N.equals(nullFlg) && ZYPConstant.FLG_ON_Y.equals(ssmResultDL.getString(DROP_SHIP_FLG))) {
                    param.put(KEY_NAME_LOC_TP_CD, ssmResultDL.getString(LOC_TP_CD));
                	param.put(KEY_NAME_DEF_INVTY_LOC_CD, ssmResultDL.getString(INVTY_LOC_CD));
                	getLocationNameForWH(NLXC014001.nullToEmpty(ssmResultDL.getString(INVTY_LOC_CD)), param);
                    
                } else {
                	
                    if (ZYPCommonFunc.hasValue(firstProdCtrlCd)) {
                        PreparedStatement pstmtILC = null;
                        ResultSet ssmResultILC = null;
                        try {
                            Map<String, String> queryParamILC = new HashMap<String, String>();
                            queryParamILC.put(SQL_PARA_GLBL_CMPY_CD, glblCmpyCd);
                            queryParamILC.put(SQL_PARA_INV_CNSGN_CD, invCnsgnCd);
                            queryParamILC.put(SQL_PARA_FIRST_PROD_CTRL_CD, firstProdCtrlCd);
                            queryParamILC.put(SQL_PARA_BAT_PROC_DATE, batProcDate);
                            pstmtILC = ssmLowLev.createPreparedStatement(GET_LOCATION_CODE, queryParamILC);
                            ssmResultILC = pstmtILC.executeQuery();

                            String invLocCd = null;
                            if (ssmResultILC.next() && ZYPCommonFunc.hasValue(ssmResultILC.getString(INVTY_LOC_CD))) {
                                invLocCd = ssmResultILC.getString(INVTY_LOC_CD);
                            } else {
                                invLocCd = ssmResultDL.getString(INVTY_LOC_CD);
                            }
                            param.put(KEY_NAME_LOC_TP_CD, ssmResultDL.getString(LOC_TP_CD));
                        	param.put(KEY_NAME_DEF_INVTY_LOC_CD, invLocCd);
                            getLocationNameForWH(NLXC014001.nullToEmpty(invLocCd), param);
                        } finally {
                            S21SsmLowLevelCodingClient.closeResource(pstmtILC, ssmResultILC);
                        }
                    } else {
                        param.put(KEY_NAME_LOC_TP_CD, ssmResultDL.getString(LOC_TP_CD));
                    	param.put(KEY_NAME_DEF_INVTY_LOC_CD, ssmResultDL.getString(INVTY_LOC_CD));
                        getLocationNameForWH(NLXC014001.nullToEmpty(ssmResultDL.getString(INVTY_LOC_CD)), param);
                    }                    
                	
                }
            } else {
                msg = S21MessageFunc.clspGetMessage(NLAM1001E, new String[] {IMPT_INV_CNSGN, IMPT_INV_CNSGN_CD + "," + OPS_DT, invCnsgnCd + "," + batProcDate });
                msgMap.put(KEY_MESSAGE_ID, NLAM1001E);
                msgMap.put(KEY_MESSAGE, msg);
                errorList.add(msgMap);
            }
            if (ZYPCommonFunc.hasValue(param.get(KEY_NAME_DEF_INVTY_LOC_CD))) {
                returnCode = NORMAL_CD;
            } else {
                returnCode = ERR_CD;
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(pstmtDL, ssmResultDL);
        }
        return returnCode;
    }

    /**
     * @throws SQLException SQLException
     */
    private void getLocationNameForWH(String locCd, Map<String, String> param) throws SQLException {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(SQL_PARA_GLBL_CMPY_CD, this.glblCmpyCode);
        queryParam.put(SQL_PARA_WH_CD, locCd);
        queryParam.put(SQL_PARA_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        PreparedStatement pstmtForWh = null;
        ResultSet ssmResultForWh = null;
        try {
            pstmtForWh = ssmLowLev.createPreparedStatement(GET_LOCATION_NAME_FOR_WH, queryParam);
            ssmResultForWh = pstmtForWh.executeQuery();

            if (ssmResultForWh.next()) {
                param.put(KEY_NAME_DEF_INVTY_LOC_NM, ssmResultForWh.getString(LOC_NM));
                param.put(KEY_NAME_FIRST_LINE_ADDR, ssmResultForWh.getString(FIRST_LINE_ADDR));
                param.put(KEY_NAME_SCD_LINE_ADDR, ssmResultForWh.getString(SCD_LINE_ADDR));
                param.put(KEY_NAME_THIRD_LINE_ADDR, ssmResultForWh.getString(THIRD_LINE_ADDR));
                param.put(KEY_NAME_FRTH_LINE_ADDR, ssmResultForWh.getString(FRTH_LINE_ADDR));
                param.put(KEY_NAME_CTY_ADDR, ssmResultForWh.getString(CTY_ADDR));
                param.put(KEY_NAME_ST_CD, ssmResultForWh.getString(ST_CD));
                param.put(KEY_NAME_POST_CD, ssmResultForWh.getString(POST_CD));
                param.put(KEY_NAME_CTRY_CD, ssmResultForWh.getString(CTRY_CD));
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(pstmtForWh, ssmResultForWh);
        }
    }
    
    /**
     * @throws SQLException SQLException
     */
    private void getLocationNameForVND(String locCd, Map<String, String> param) throws SQLException {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(SQL_PARA_GLBL_CMPY_CD, this.glblCmpyCode);
        queryParam.put(SQL_PARA_VND_CD, locCd);
        PreparedStatement pstmtForVnd = null;
        ResultSet ssmResultForVnd = null;
        try {
            pstmtForVnd = ssmLowLev.createPreparedStatement(GET_LOCATION_NAME_FOR_VND, queryParam);
            ssmResultForVnd = pstmtForVnd.executeQuery();

            if (ssmResultForVnd.next()) {
                param.put(KEY_NAME_DEF_INVTY_LOC_NM, ssmResultForVnd.getString(LOC_NM));
                param.put(KEY_NAME_FIRST_LINE_ADDR, ssmResultForVnd.getString(FIRST_LINE_ADDR));
                param.put(KEY_NAME_SCD_LINE_ADDR, ssmResultForVnd.getString(SCD_LINE_ADDR));
                param.put(KEY_NAME_THIRD_LINE_ADDR, ssmResultForVnd.getString(THIRD_LINE_ADDR));
                param.put(KEY_NAME_FRTH_LINE_ADDR, ssmResultForVnd.getString(FRTH_LINE_ADDR));
                param.put(KEY_NAME_CTY_ADDR, ssmResultForVnd.getString(CTY_ADDR));
                param.put(KEY_NAME_ST_CD, ssmResultForVnd.getString(ST_CD));
                param.put(KEY_NAME_POST_CD, ssmResultForVnd.getString(POST_CD));
                param.put(KEY_NAME_CTRY_CD, ssmResultForVnd.getString(CTRY_CD));
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(pstmtForVnd, ssmResultForVnd);
        }
    }

}
