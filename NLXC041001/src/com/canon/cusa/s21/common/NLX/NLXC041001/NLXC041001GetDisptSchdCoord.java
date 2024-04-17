/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC041001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Dispatch Schedule Coordinator
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   CSAI            Y.Imazu         Create          CSA
 * 09/07/2017   CITS            T.Tokutomi      Update          QC#19359
 * 2017/10/03   CITS            K.Ogino         Update          QC#21519
 * </pre>
 */
public class NLXC041001GetDisptSchdCoord {

    /** S21SsmBatchClient */
    private static S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NLXC041001GetDisptSchdCoord.class);

    /** Check Type SO */
    private static final String SO = "SO";

    /** Check Type RWS */
    private static final String RWS = "RWS";

    /** Check Type CPO */
    private static final String CPO = "CPO";

    /** An input parameter, [Global Company Code], has not been set. */
    private static final String NLXM1023E = "NLXM1023E";

    /** An input parameter, [CPO Order Number] has not been set. */
    private static final String NLXM1047E = "NLXM1047E";

    /** An input parameter, [Retail WH Code] has not been set. */
    private static final String NLXM1048E = "NLXM1048E";

    /** An input parameter, [State Code] has not been set. */
    private static final String NLXM1049E = "NLXM1049E";

    /** Sales Date is not set. */
    private static final String NLZM2079E = "NLZM2079E";

    /**
     * Either [Shipping Order Number] or [Retail WH Code and State
     * Code] needs to be set.
     */
    private static final String NLBM1339E = "NLBM1339E";

    /**
     * Either [Receiving Order Number] or [Retail WH Code and State
     * Code] needs to be set.
     */
    private static final String NLBM1362E = "NLBM1362E";

    /**
     * Schedule coordinator that can be assigned automatically cannot
     * be specified.
     */
    private static final String NLBM1340W = "NLBM1340W";

    /**
     * QC#19359 Update Get Dispatched Schedule Coordinator
     * @param glblCmpyCd String
     * @param slsDt String
     * @param soNum String
     * @param rtlWhCd String
     * @param stCd String
     * @return schdCoordInfo
     */
    public static NLXC041001SchdCoordInfo getDisptSchdCoord(String glblCmpyCd, String slsDt, String soNum, String rtlWhCd, String stCd) {

        NLXC041001SchdCoordInfo schdCoordInfo = new NLXC041001SchdCoordInfo();

        // QC#21519
        ArrayList<String> errList = new ArrayList<String>();

        // Parameter Check
        if (!checkReqParams(glblCmpyCd, slsDt, soNum, rtlWhCd, stCd, SO)) {

            schdCoordInfo.setErrList(errList);
            return schdCoordInfo;
        }
        // Get Schedule Coordinator Of Same Order
        if (ZYPCommonFunc.hasValue(soNum)) {
            List<Map<String, Object>> schdCoordInfoOfSameOrder = getSchdCoordInfoOfSameOrder(glblCmpyCd, slsDt, soNum, null, null, rtlWhCd, stCd);

            if (schdCoordInfoOfSameOrder != null && !schdCoordInfoOfSameOrder.isEmpty()) {
                setSchdCoordInfo(schdCoordInfo, schdCoordInfoOfSameOrder.get(0));
                return schdCoordInfo;
            }
        }

        // Get Schedule Coordinator
        List<Map<String, Object>> schdCoordInfoList = getSchdCoordInfoList(glblCmpyCd, slsDt, soNum, null, rtlWhCd, stCd);

        // No Data
        if (schdCoordInfoList == null || schdCoordInfoList.isEmpty()) {

            errList.add(NLBM1340W);
            schdCoordInfo.setErrList(errList);
            return schdCoordInfo;
        }

        // Result Set
        setSchdCoordInfo(schdCoordInfo, schdCoordInfoList.get(0));

        return schdCoordInfo;
    }

    /**
     * QC#19359 Update Check Required Parameters
     * @param glblCmpyCd String
     * @param slsDt String
     * @param srcOrdNum String
     * @param rtlWhCd String
     * @param stCd String
     * @param type String
     * @return boolean
     */
    private static boolean checkReqParams(String glblCmpyCd, String slsDt, String srcOrdNum, String rtlWhCd, String stCd, String type) {

        boolean isChkNorm = true;
        // QC#21519
        ArrayList<String> errList = new ArrayList<String>();

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {

            errList.add(NLXM1023E);
            isChkNorm = false;
        }

        if (!ZYPCommonFunc.hasValue(slsDt)) {

            errList.add(NLZM2079E);
            isChkNorm = false;
        }

        if (CPO.equals(type)) {
            if (!ZYPCommonFunc.hasValue(srcOrdNum)) {
                errList.add(NLXM1047E);
                isChkNorm = false;
            }
            if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
                errList.add(NLXM1048E);
                isChkNorm = false;
            }
            if (!ZYPCommonFunc.hasValue(stCd)) {
                errList.add(NLXM1049E);
                isChkNorm = false;
            }
        } else {

            if (!ZYPCommonFunc.hasValue(srcOrdNum) && (!ZYPCommonFunc.hasValue(rtlWhCd) || !ZYPCommonFunc.hasValue(stCd))) {

                if (SO.equals(type)) {
                    errList.add(NLBM1339E);
                } else {
                    // RWS
                    errList.add(NLBM1362E);
                }
                isChkNorm = false;
            }

        }
        return isChkNorm;
    }

    /**
     * QC#19359 Update Get Schedule Coordinator Info List
     * @param glblCmpyCd String
     * @param slsDt String
     * @param soNum String
     * @param rwsNum String
     * @param rtlWhCd String
     * @param stCd String
     * @return List<Map<String, Object>>
     */
    private static List<Map<String, Object>> getSchdCoordInfoList(String glblCmpyCd, String slsDt, String soNum, String rwsNum, String rtlWhCd, String stCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("slsDt", slsDt);
        queryParam.put("rtlWhCd", rtlWhCd);
        queryParam.put("stCd", stCd);
        queryParam.put("soNum", soNum);
        queryParam.put("rwsNum", rwsNum);
        queryParam.put("soCustDataTpShipTo", SO_CUST_DATA_TP.SHIP_TO);
        queryParam.put("soPrintFlgY", ZYPConstant.FLG_ON_Y);
        queryParam.put("shipFlgN", ZYPConstant.FLG_OFF_N);
        queryParam.put("rwsOpenFlgY", ZYPConstant.FLG_ON_Y);
        queryParam.put("rcvFlgN", ZYPConstant.FLG_OFF_N);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSchdCoordInfoList", queryParam);
    }

    /**
     * Set Schedule Coordinator Info
     * @param schdCoordInfo NLXC041001SchdCoordInfo
     * @param resultMap Map<String, Object>
     */
    private static void setSchdCoordInfo(NLXC041001SchdCoordInfo schdCoordInfo, Map<String, Object> resultMap) {

        schdCoordInfo.setSchdCoordPsnCd((String) resultMap.get("SCHD_COORD_PSN_CD"));
        schdCoordInfo.setRtlWhCd((String) resultMap.get("RTL_WH_CD"));
        schdCoordInfo.setStCd((String) resultMap.get("ST_CD"));
        schdCoordInfo.setCarrCd((String) resultMap.get("CARR_CD"));
        schdCoordInfo.setCarrCtacEmlAddr((String) resultMap.get("CARR_CTAC_EML_ADDR"));
        schdCoordInfo.setCarrCtacTelNum((String) resultMap.get("CARR_CTAC_TEL_NUM"));
        schdCoordInfo.setEffFromDt((String) resultMap.get("EFF_FROM_DT"));
        schdCoordInfo.setEffThruDt((String) resultMap.get("EFF_THRU_DT"));
    }

    /**
     * QC#19359 Create Get Dispatched Schedule Coordinator
     * @param glblCmpyCd String
     * @param slsDt String
     * @param soNum String
     * @param rtlWhCd String
     * @param stCd String
     * @return schdCoordInfo
     */
    public static NLXC041001SchdCoordInfo getDisptSchdCoordForRws(String glblCmpyCd, String slsDt, String rwsNum, String rtlWhCd, String stCd) {

        NLXC041001SchdCoordInfo schdCoordInfo = new NLXC041001SchdCoordInfo();
        // QC#21519
        ArrayList<String> errList = new ArrayList<String>();

        // Parameter Check
        if (!checkReqParams(glblCmpyCd, slsDt, rwsNum, rtlWhCd, stCd, RWS)) {

            schdCoordInfo.setErrList(errList);
            return schdCoordInfo;
        }

        // Get Schedule Coordinator Of Same Order
        if (ZYPCommonFunc.hasValue(rwsNum)) {
            List<Map<String, Object>> schdCoordInfoOfSameOrder = getSchdCoordInfoOfSameOrder(glblCmpyCd, slsDt, null, rwsNum, null, rtlWhCd, stCd);

            if (schdCoordInfoOfSameOrder != null && !schdCoordInfoOfSameOrder.isEmpty()) {
                setSchdCoordInfo(schdCoordInfo, schdCoordInfoOfSameOrder.get(0));
                return schdCoordInfo;
            }
        }

        // Get Schedule Coordinator
        List<Map<String, Object>> schdCoordInfoList = getSchdCoordInfoList(glblCmpyCd, slsDt, null, rwsNum, rtlWhCd, stCd);

        // No Data
        if (schdCoordInfoList == null || schdCoordInfoList.isEmpty()) {

            errList.add(NLBM1340W);
            schdCoordInfo.setErrList(errList);
            return schdCoordInfo;
        }

        // Result Set
        setSchdCoordInfo(schdCoordInfo, schdCoordInfoList.get(0));

        return schdCoordInfo;
    }

    /**
     * QC#19359 Create Get Schedule Coordinator Info Of Same Order
     * @param glblCmpyCd String
     * @param slsDt String
     * @param soNum String
     * @param rwsNum String
     * @param cpoOrdNum String
     * @param rtlWhCd String
     * @param stCd String
     * @return List<Map<String, Object>>
     */
    private static List<Map<String, Object>> getSchdCoordInfoOfSameOrder(String glblCmpyCd, String slsDt, String soNum, String rwsNum, String cpoOrdNum, String rtlWhCd, String stCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("slsDt", slsDt);
        queryParam.put("soNum", soNum);
        queryParam.put("rwsNum", rwsNum);
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("rtlWhCd", rtlWhCd);
        queryParam.put("stCd", stCd);
        queryParam.put("soCustDataTpShipTo", SO_CUST_DATA_TP.SHIP_TO);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSchdCoordInfoOfSameOrder", queryParam);
    }

    /**
     * QC#19359 Create Get Dispatched Schedule Coordinator
     * @param glblCmpyCd String
     * @param slsDt String
     * @param soNum String
     * @param rtlWhCd String
     * @param stCd String
     * @return schdCoordInfo
     */
    public static NLXC041001SchdCoordInfo getDisptSchdCoordForSalesOrder(String glblCmpyCd, String slsDt, String cpoOrdNum, String rtlWhCd, String stCd) {

        NLXC041001SchdCoordInfo schdCoordInfo = new NLXC041001SchdCoordInfo();
        // QC#21519
        ArrayList<String> errList = new ArrayList<String>();
        // Parameter Check
        if (!checkReqParams(glblCmpyCd, slsDt, cpoOrdNum, rtlWhCd, stCd, CPO)) {

            schdCoordInfo.setErrList(errList);
            return schdCoordInfo;
        }

        // Get Schedule Coordinator Of Same Order
        if (ZYPCommonFunc.hasValue(cpoOrdNum)) {
            List<Map<String, Object>> schdCoordInfoOfSameOrder = getSchdCoordInfoOfSameOrder(glblCmpyCd, slsDt, null, null, cpoOrdNum, rtlWhCd, stCd);

            if (schdCoordInfoOfSameOrder != null && !schdCoordInfoOfSameOrder.isEmpty()) {
                setSchdCoordInfo(schdCoordInfo, schdCoordInfoOfSameOrder.get(0));
                return schdCoordInfo;
            }
        }

        // Get Schedule Coordinator
        List<Map<String, Object>> schdCoordInfoList = getSchdCoordInfoList(glblCmpyCd, slsDt, null, null, rtlWhCd, stCd);

        // No Data
        if (schdCoordInfoList == null || schdCoordInfoList.isEmpty()) {

            errList.add(NLBM1340W);
            schdCoordInfo.setErrList(errList);
            return schdCoordInfo;
        }

        // Result Set
        setSchdCoordInfo(schdCoordInfo, schdCoordInfoList.get(0));

        return schdCoordInfo;
    }
}
