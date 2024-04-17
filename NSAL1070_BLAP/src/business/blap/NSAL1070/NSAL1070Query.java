package business.blap.NSAL1070;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/26/2015   Fujitsu         M.Hara          Create          N/A
 * 2017/09/28   Hitachi         K.Kojima        Update          QC#18376
 *</pre>
 */
public final class NSAL1070Query extends S21SsmEZDQuerySupport {
    /** Singleton instance. */
    private static final NSAL1070Query MY_INSTANCE = new NSAL1070Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NSAL1070Query() {
        super();
    }

    /**
     * <pre>
     * Get the NSAL1070Query instance.
     * </pre>
     * @return NSAL1070Query instance
     */
    public static NSAL1070Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * searchXsMtrList
     * @param param Map<String, Object>
     * @param sMsg  NSAL1070SMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult searchXsMtrList(Map<String, Object> param, NSAL1070SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getXsMtrList", param, sMsg.A);
    }

    /**
     * searchXsMtrListForArrg
     * @param param Map<String, Object>
     * @param sMsg NSAL1070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchXsMtrListForArrg(Map<String, Object> param, NSAL1070SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getXsMtrListForArrg", param, sMsg.B);
    }

    // START 2017/09/28 K.Kojima [QC#18376,ADD]
    /**
     * getUplftMtrPrcUpRatio
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param bllgMtrLbCd String
     * @return BigDecimal
     */
    public BigDecimal getUplftMtrPrcUpRatio(String glblCmpyCd, BigDecimal dsContrDtlPk, String bllgMtrLbCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        param.put("bllgMtrLbCd", bllgMtrLbCd);
        BigDecimal uplftMtrPrcUpRatio = (BigDecimal) getSsmEZDClient().queryObject("getUplftMtrPrcUpRatio", param).getResultObject();
        if (uplftMtrPrcUpRatio == null) {
            uplftMtrPrcUpRatio = BigDecimal.ZERO;
        }
        return uplftMtrPrcUpRatio;
    }

    /**
     * getUplftMtrPrcUpRatioForLine
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param bllgMtrLbCd String
     * @return BigDecimal
     */
    public BigDecimal getUplftMtrPrcUpRatioForLine(String glblCmpyCd, BigDecimal dsContrDtlPk, String bllgMtrLbCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        param.put("bllgMtrLbCd", bllgMtrLbCd);
        BigDecimal uplftMtrPrcUpRatio = (BigDecimal) getSsmEZDClient().queryObject("getUplftMtrPrcUpRatioForLine", param).getResultObject();
        if (uplftMtrPrcUpRatio == null) {
            uplftMtrPrcUpRatio = BigDecimal.ZERO;
        }
        return uplftMtrPrcUpRatio;
    }
    // END 2017/09/28 K.Kojima [QC#18376,ADD]

}
