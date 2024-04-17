/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1400;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.ACCT_CONTR_ADMIN_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 * NSAL1400 Named Customer Resource setup
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/01   Hitachi         T.Tomita        Create          QC#18362
 *</pre>
 */
public final class NSAL1400Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL1400Query INSTANCE = new NSAL1400Query();

    /**
     * Private constructor
     */
    private NSAL1400Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL1400Query singleton instance
     */
    public static NSAL1400Query getInstance() {
        return INSTANCE;
    }

    /**
     * Search
     * @param cMsg NSAL1400CMsg
     * @param sMsg NSAL1400SMsg
     * @return Meter Group List
     */
    public List<Map<String, Object>> getSvcLineBizTp(String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);

        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSvcLineBizTp", param).getResultObject();
    }

    /**
     * Search
     * @param cMsg NSAL1400CMsg
     * @param sMsg NSAL1400SMsg
     * @return Meter Group List
     */
    public S21SsmEZDResult search(NSAL1400CMsg cMsg, NSAL1400SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("svcLineBizCd", cMsg.svcLineBizCd_H.getValue());
        ssmParam.put("dsAcctNm", cMsg.dsAcctNm_H.getValue());
        if (hasValue(cMsg.xxChkBox_H) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_H.getValue())) {
            ssmParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
        }
        ssmParam.put("slsDt", cMsg.slsDt.getValue());
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("count", sMsg.A.length());

        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }

    public ACCT_CONTR_ADMIN_RELNTMsg getAcctContrAdminRelnForPk(String glblCmpyCd, BigDecimal acctContrAdminRelnPk) {
        ACCT_CONTR_ADMIN_RELNTMsg inMsg = new ACCT_CONTR_ADMIN_RELNTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.acctContrAdminRelnPk, acctContrAdminRelnPk);
        return (ACCT_CONTR_ADMIN_RELNTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    public ACCT_CONTR_ADMIN_RELNTMsg getAcctContrAdminRelnForPkForUpdate(String glblCmpyCd, BigDecimal acctContrAdminRelnPk) {
        ACCT_CONTR_ADMIN_RELNTMsg inMsg = new ACCT_CONTR_ADMIN_RELNTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.acctContrAdminRelnPk, acctContrAdminRelnPk);
        return (ACCT_CONTR_ADMIN_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
    }

    public BigDecimal countCustomer(String glblCmpyCd, String slsDt, String sellToCustCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("sellToCustCd", sellToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (BigDecimal) getSsmEZDClient().queryObject("countCustomer", ssmParam).getResultObject();
    }

    public BigDecimal countResource(String glblCmpyCd, String slsDt, String svcLineBizCd, String contrAdminPsnCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("svcLineBizCd", svcLineBizCd);
        ssmParam.put("contrAdminPsnCd", contrAdminPsnCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (BigDecimal) getSsmEZDClient().queryObject("countResource", ssmParam).getResultObject();
    }

    public BigDecimal countDuplicatePeriod(String glblCmpyCd, String slsDt, NSAL1400_ASMsg lineSMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("dsAcctNum", lineSMsg.dsAcctNum_A.getValue());
        ssmParam.put("svcLineBizCd", lineSMsg.svcLineBizCd_A.getValue());
        ssmParam.put("effFromDt", lineSMsg.effFromDt_A.getValue());
        String effThruDt = lineSMsg.effThruDt_A.getValue();
        if (!hasValue(effThruDt)) {
            effThruDt = slsDt;
        }
        ssmParam.put("effThruDt", effThruDt);
        ssmParam.put("acctContrAdminRelnPk", lineSMsg.acctContrAdminRelnPk_A.getValue());
        return (BigDecimal) getSsmEZDClient().queryObject("countDuplicatePeriod", ssmParam).getResultObject();
    }

}
