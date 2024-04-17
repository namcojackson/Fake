/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1090;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Credit Rebill Detail Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Hitachi         A.Kohinata      Create          N/A
 * 2016/04/15   Hitachi         K.Yamada        Update          CSA QC#7056
 * 2018/03/31   CITS            M.Naito         Update          QC#18672
 * 2021/01/12   CITS            R.Shimamoto     Update          QC#58177
 * 2023/07/06   CITS            T.Aizawa        Update          QC#59538
 *</pre>
 */
public final class NSAL1090Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1090Query INSTANCE = new NSAL1090Query();

    /**
     * Constructor.
     */
    private NSAL1090Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL1090Query
     */
    public static NSAL1090Query getInstance() {
        return INSTANCE;
    }

    /**
     * getHeaderInfo
     * @param cMsg NSAL1090CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderInfo(NSAL1090CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("custIncdtId", cMsg.custIncdtId_H.getValue());

        return getSsmEZDClient().queryEZDMsg("getHeaderInfo", params, cMsg);
    }

    /**
     * getDetailInfo
     * @param cMsg NSAL1090CMsg
     * @param sMsg NSAL1090SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailInfo(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("custIncdtId", cMsg.custIncdtId_H.getValue());
        // add start 2016/04/15 CSA Defect#7056
        params.put("dsContrCatg_reg", DS_CONTR_CATG.REGULAR);
        params.put("dsContrDtlTp_flt", DS_CONTR_DTL_TP.FLEET);
        params.put("dsContrCatg_agg", DS_CONTR_CATG.AGGREGATE);
        params.put("dsContrDtlTp_agg", DS_CONTR_DTL_TP.AGGREGATE);
        // add end 2016/04/15 CSA Defect#7056
        params.put("rowNum", cnt);
        // START 2018/03/31 M.Naito [QC#18672, ADD]
        params.put("addlChrgInvTpCd", ADDL_CHRG_INV_TP.BASE);
        // START 2018/03/31 M.Naito [QC#18672, ADD]

        return getSsmEZDClient().queryEZDMsgArray("getDetailInfo", params, sMsg.A);
    }

    // START 2021/01/12 R.Shimamoto [QC#58177,ADD]
    public S21SsmEZDResult getMaxMtrBllgThruDtFromUsage(NSAL1090CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("custIncdtId", cMsg.custIncdtId_H.getValue());

        return getSsmEZDClient().queryObjectList("getMaxMtrBllgThruDtFromUsage", params);
    }

    public S21SsmEZDResult getMaxMtrBllgThruDtFromBase(NSAL1090CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("custIncdtId", cMsg.custIncdtId_H.getValue());

        return getSsmEZDClient().queryObjectList("getMaxMtrBllgThruDtFromBase", params);
    }

    public BigDecimal checkFuturePrcEffFromUsage(NSAL1090CMsg cMsg, BigDecimal dsContrBllgMtrPk, String maxDt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        params.put("maxDt", maxDt);

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("checkFuturePrcEffFromUsage", params);
        BigDecimal cnt = (BigDecimal) rs.getResultObject();

        return cnt;
    }

    public BigDecimal checkFuturePrcEffFromBase(NSAL1090CMsg cMsg, BigDecimal dsContrDtlPk, String maxDt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("maxDt", maxDt);

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("checkFuturePrcEffFromBase", params);
        BigDecimal cnt = (BigDecimal) rs.getResultObject();

        return cnt;
    }
    // END 2021/01/12 R.Shimamoto [QC#58177,ADD]

    // START 2023/07/06 t.aizawa [QC#59538,ADD]
    /**
     * getInvoiceWithMoreThanOneBillingPeriod
     * @param cMsg NSAL1090CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvoiceWithMoreThanOneBillingPeriod(NSAL1090CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("custIncdtId", cMsg.custIncdtId_H.getValue());
        params.put("svcInvChrgTpBC", SVC_INV_CHRG_TP.BASE_CHARGE);
        params.put("svcInvChrgTpMC", SVC_INV_CHRG_TP.METER_CHARGE);
        params.put("svcInvChrgTpAC", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);

        return getSsmEZDClient().queryObjectList("getInvoiceWithMoreThanOneBillingPeriod", params);
    }
   // END   2023/07/06 t.aizawa [QC#59538,ADD]

}
