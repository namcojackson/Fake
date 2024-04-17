/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0740;

import java.util.HashMap;
import java.util.Map;

import business.db.PRC_SVC_PLN_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/27   Hitachi         Y.Takeno        Create          N/A
 * 2017/09/05   CITS            M.Naito         Update          QC#18724
 * 2017/12/21   Hitachi         M.Kidokoro      Update          QC#22660
 *</pre>
 */
public final class NSAL0740Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0740Query INSTANCE = new NSAL0740Query();

    /**
     * Constructor.
     */
    private NSAL0740Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0740Query
     */
    public static NSAL0740Query getInstance() {
        return INSTANCE;
    }

    /**
     * Get Contract Info.
     * 
     * @param ssmParam Map<String, Object>
     * @param aCMsgArray NSAL0740_ACMsgArray
     * @param aSMsgArray NSAL0740_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrInfo(Map<String, Object> ssmParam, NSAL0740_ACMsgArray aCMsgArray, NSAL0740_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContrInfo", ssmParam, aSMsgArray);
    }

    // START 2017/09/05 M.Naito [QC#18724, ADD]
    /**
     * Get PRC_SVC_PLN_TP Info.
     * 
     * @param glblCmpyCd String
     * @param prcSvcPlnTpCd String
     * @return PRC_SVC_PLN_TPTMsg
     */
    public PRC_SVC_PLN_TPTMsg getPrcSvcPlnTpInfo(String glblCmpyCd, String prcSvcPlnTpCd) {
        PRC_SVC_PLN_TPTMsg prmTMsg = new PRC_SVC_PLN_TPTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.prcSvcPlnTpCd, prcSvcPlnTpCd);
        return (PRC_SVC_PLN_TPTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }
    // END 2017/09/05 M.Naito [QC#18724, ADD]

    // START 2017/12/21 M.Kidokoro [QC#22660, ADD]
    /**
     * check Exist Different Contr Eff Thru Date
     * @param asMsg NSAL0740_ASMsg
     * @return true: exist
     */
    public boolean isExistDiffThruDate(NSAL0740_ASMsg asMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", asMsg.glblCmpyCd_D1.getValue());
        params.put("dsContrPk", asMsg.dsContrPk_D1.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistDiffThruDate", params);
        if ((Integer) result.getResultObject() != null) {
            return true;
        } else {
            return false;
        }
    }
    // END 2017/12/21 M.Kidokoro [QC#22660, ADD]
}
