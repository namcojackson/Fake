/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0160;

import static business.blap.NFDL0160.constant.NFDL0160Constant.SELECT_ACT;
import static business.blap.NFDL0160.constant.NFDL0160Constant.SELECT_INACT;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Naito         Create          N/A
 * 2016/06/03   Fujitsu         S.Yoshida       Update          CSA QC#8887
 * 2016/08/09   Hitachi         K.Kojima        Update          QC#13129
 * 2018/08/03   Fujitsu         T.Ogura         Update          QC#25899
 *</pre>
 */
public final class NFDL0160Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NFDL0160Query MY_INSTANCE = new NFDL0160Query();

    /**
     * Private constructor
     */
    private NFDL0160Query() {
        super();
    }

    /**
     * Get the NFDL0160Query instance.
     * @return NFDL0160Query instance
     */
    public static NFDL0160Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NFDL0160CMsg
     * @param glblMsg NFDL0160SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NFDL0160CMsg bizMsg, NFDL0160SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        // START 2018/08/03 T.Ogura [QC#25899,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.cltPtfoCd)) {
            params.put("cltPtfoCd", bizMsg.cltPtfoCd.getValue());
        }
        // END   2018/08/03 T.Ogura [QC#25899,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.cltPtfoNm)) {
            params.put("cltPtfoNm", bizMsg.cltPtfoNm.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.cltPsnNm)) {
            params.put("cltPsnNm", bizMsg.cltPsnNm.getValue());
        }
        if (bizMsg.cltPtfoStsFlg.getValue().equals(SELECT_ACT)) {
            params.put("cltPtfoStsFlg", ZYPConstant.FLG_ON_Y);
        } else if (bizMsg.cltPtfoStsFlg.getValue().equals(SELECT_INACT)) {
            params.put("cltPtfoStsFlg", ZYPConstant.FLG_OFF_N);
        }
        params.put("rowNum", bizMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getCltPtfo", params, glblMsg.A);
    }

    /**
     * chkCltPtfoNm
     * @param scrnRelCltPtfoPk BigDecimal
     * @param scrnRelCltPtfoNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkCltPtfoNm(BigDecimal scrnRelCltPtfoPk, String scrnRelCltPtfoNm) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cltPtfoPk", scrnRelCltPtfoPk);
        params.put("cltPtfoNm", scrnRelCltPtfoNm);

        return getSsmEZDClient().queryObjectList("chkCltPtfoNm", params);
    }

    // START 2016/08/09 K.Kojima [QC#13129,ADD]
    /**
     * getCountSellToCust
     * @param glblCmpyCd String
     * @param cltPtfoPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getCountSellToCust(String glblCmpyCd, BigDecimal cltPtfoPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cltPtfoPk", cltPtfoPk);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getCountSellToCust", params);
        if (rs.isCodeNotFound()) {
            return BigDecimal.ZERO;
        }
        return (BigDecimal) rs.getResultObject();
    }

    /**
     * getCountDsAcctPros
     * @param glblCmpyCd String
     * @param cltPtfoPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getCountDsAcctPros(String glblCmpyCd, BigDecimal cltPtfoPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cltPtfoPk", cltPtfoPk);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getCountDsAcctPros", params);
        if (rs.isCodeNotFound()) {
            return BigDecimal.ZERO;
        }
        return (BigDecimal) rs.getResultObject();
    }

    /**
     * getCountBillToCust
     * @param glblCmpyCd String
     * @param cltPtfoPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getCountBillToCust(String glblCmpyCd, BigDecimal cltPtfoPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cltPtfoPk", cltPtfoPk);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getCountBillToCust", params);
        if (rs.isCodeNotFound()) {
            return BigDecimal.ZERO;
        }
        return (BigDecimal) rs.getResultObject();
    }

    /**
     * getCountCltPtfo
     * @param glblCmpyCd String
     * @param cltPtfoPk BigDecimal
     * @param pkList BigDecimal[]
     * @return BigDecimal
     */
    public BigDecimal getCountCltPtfo(String glblCmpyCd, BigDecimal cltPtfoPk, BigDecimal[] pkList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("relCltPtfoPk", cltPtfoPk);
        params.put("pkList", pkList);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getCountCltPtfo", params);
        if (rs.isCodeNotFound()) {
            return BigDecimal.ZERO;
        }
        return (BigDecimal) rs.getResultObject();
    }
    // END 2016/08/09 K.Kojima [QC#13129,ADD]

    // START 2018/08/03 T.Ogura [QC#25899,ADD]
    /**
     * getCltPtfoNmInfo
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCltPtfoNmInfo(String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList ("getCltPtfoNmInfo", ssmParam);
    }
    // END   2018/08/03 T.Ogura [QC#25899,ADD]

}
