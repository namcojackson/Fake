/*
 * <pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3180;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NLBL3180.constant.NLBL3180Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Ship Detail Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2021/06/18   CITS            J.Evangelista   Create          QC#58876
 * 08/18/2021   CITS            K.Ogino         Update          QC#58876-1
 * </pre>
 */
public final class NLBL3180Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLBL3180Query MYINSTANCE = new NLBL3180Query();

    /**
     * Constructor
     */
    private NLBL3180Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL3180Query
     */
    public static NLBL3180Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * getSoSlpNum
     * @param glblCmpyCd String
     * @param soNum String
     * @param prchReqNum String
     * @param prchReqLineNum String
     * @param prchReqLineSubNum BigDecimal
     * @return String
     */
    public String getSoSlpNum(String glblCmpyCd, String soNum, String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(NLBL3180Constant.DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(NLBL3180Constant.DB_PARAM_SO_NUM, soNum);
        ssmParam.put(NLBL3180Constant.DB_PARAM_PRCH_REQ_NUM, prchReqNum);
        ssmParam.put(NLBL3180Constant.DB_PARAM_PRCH_REQ_LINE_NUM, prchReqLineNum);
        ssmParam.put(NLBL3180Constant.DB_PARAM_PRCH_REQ_LINE_SUB_NUM, prchReqLineSubNum);

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getSoSlpNum", ssmParam);
        if (rs.isCodeNormal()) {
            return (String) rs.getResultObject();
        }

        return null;
    }

    // QC#58876-1 Mod Start
    /**
     * getLatestTrxPod
     * @param glblCmpyCd String
     * @param otbdOrdNumList List<String>
     * @param otbdOrdLineNum BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLatestTrxPod(String glblCmpyCd, List<String> otbdOrdNumList, BigDecimal otbdOrdLineNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(NLBL3180Constant.DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(NLBL3180Constant.DB_PARAM_OTBD_ORD_NUM_LIST, otbdOrdNumList.toArray());
        ssmParam.put(NLBL3180Constant.DB_PARAM_OTBD_ORD_LINE_NUM, otbdOrdLineNum);
        ssmParam.put(NLBL3180Constant.DB_PARAM_POD, WMS_TASK.POD);

        return getSsmEZDClient().queryObject("getLatestTrxPod", ssmParam);
    }

    /**
     * getLatestTrxPod
     * @param glblCmpyCd String
     * @param otbdOrdNumList List<String>
     * @param otbdOrdLineNum BigDecimal
     * @param intfcTrxId String
     * @param wrkTrxId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLatestTrx(String glblCmpyCd, List<String> otbdOrdNumList, BigDecimal otbdOrdLineNum, String intfcTrxId, String wrkTrxId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(NLBL3180Constant.DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(NLBL3180Constant.DB_PARAM_OTBD_ORD_NUM_LIST, otbdOrdNumList.toArray());
        ssmParam.put(NLBL3180Constant.DB_PARAM_OTBD_ORD_LINE_NUM, otbdOrdLineNum);
        if (ZYPCommonFunc.hasValue(intfcTrxId)) {
            ssmParam.put(NLBL3180Constant.DB_PARAM_INTFC_TRX_ID, intfcTrxId);
        }
        if (ZYPCommonFunc.hasValue(wrkTrxId)) {
            ssmParam.put(NLBL3180Constant.DB_PARAM_WRK_TRX_ID, wrkTrxId);
        }

        return getSsmEZDClient().queryObject("getLatestTrx", ssmParam);
    }

    /**
     * getShipDetail
     * @param glblCmpyCd String
     * @param trxId String
     * @param otbdOrdNumList List<String>
     * @param otbdOrdLineNum BigDecimal
     * @param intfcTrxId String
     * @param wrkTrxId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipDetail(String glblCmpyCd, String trxId, List<String> otbdOrdNumList, BigDecimal otbdOrdLineNum, String intfcTrxId, String wrkTrxId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(NLBL3180Constant.DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(NLBL3180Constant.DB_PARAM_TRX_ID, trxId);
        ssmParam.put(NLBL3180Constant.DB_PARAM_OTBD_ORD_NUM_LIST, otbdOrdNumList.toArray());
        ssmParam.put(NLBL3180Constant.DB_PARAM_OTBD_ORD_LINE_NUM, otbdOrdLineNum);
        if (ZYPCommonFunc.hasValue(intfcTrxId)) {
            ssmParam.put(NLBL3180Constant.DB_PARAM_INTFC_TRX_ID, intfcTrxId);
        }
        if (ZYPCommonFunc.hasValue(wrkTrxId)) {
            ssmParam.put(NLBL3180Constant.DB_PARAM_WRK_TRX_ID, wrkTrxId);
        }

        return getSsmEZDClient().queryObjectList("getShipDetail", ssmParam);
    }

    /**
     * getFirstTrxPod
     * @param glblCmpyCd String
     * @param otbdOrdNumList List<String>
     * @param otbdOrdLineNum BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFirstTrxPod(String glblCmpyCd, List<String> otbdOrdNumList, BigDecimal otbdOrdLineNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(NLBL3180Constant.DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(NLBL3180Constant.DB_PARAM_OTBD_ORD_NUM_LIST, otbdOrdNumList.toArray());
        ssmParam.put(NLBL3180Constant.DB_PARAM_OTBD_ORD_LINE_NUM, otbdOrdLineNum);
        ssmParam.put(NLBL3180Constant.DB_PARAM_POD, WMS_TASK.POD);

        return getSsmEZDClient().queryObject("getFirstTrxPod", ssmParam);
    }
    // QC#58876-1 Mod End
}
