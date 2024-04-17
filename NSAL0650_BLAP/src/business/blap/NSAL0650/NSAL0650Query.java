/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0650;

import java.math.BigDecimal;
import java.util.Map;

import business.db.DS_CONTRTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 * Update Auto Estimation Round
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Hitachi         T.Tsuchida      Create          N/A
 * 2016/12/02   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public final class NSAL0650Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0650Query INSTANCE = new NSAL0650Query();

    /**
     * Constructor.
     */
    private NSAL0650Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0650Query
     */
    public static NSAL0650Query getInstance() {
        return INSTANCE;
    }
    // mod start 2016/12/02 CSA QC#4210
    /**
     * get Contract Info
     * @param ssmParam Map<String, Object>
     * @param aSMsgArray NSAL0650_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrInfo(Map<String, Object> ssmParam, NSAL0650_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContrInfo", ssmParam, aSMsgArray);
    }
    // mod end 2016/12/02 CSA QC#4210
    /**
     * get Contract By Contract Primary Key
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return DS_CONTRTMsg
     */
    public DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg prmTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

}
