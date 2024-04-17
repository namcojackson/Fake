/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0390;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.SVC_MODTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Mods Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/15   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public final class NSBL0390Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0390Query INSTANCE = new NSBL0390Query();

    /**
     * Constructor.
     */
    private NSBL0390Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0390Query
     */
    public static NSBL0390Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSvcMdSqNm
     * @param cMsg NSBL0390CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMdSqNm(NSBL0390CMsg cMsg) {

        Map<String, Object> ssmPrm = new HashMap<String, Object>();

        ssmPrm.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmPrm.put("svcModYr", cMsg.svcModYr.getValue());
        ssmPrm.put("svcModMth", cMsg.svcModMth.getValue());
        ssmPrm.put("svcModDay", cMsg.svcModDay.getValue());
        ssmPrm.put("svcMnfCd", cMsg.svcMnfCd.getValue());

        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getSvcModByModSqNmNextVal", ssmPrm);

        return rslt;
    }


    /**
     * get Mod By Mod Primary Key
     * @param cMsg NSBL0390CMsg
     * @return SVC_MODTMsg
     */
    public SVC_MODTMsg getMod(NSBL0390CMsg cMsg) {

        SVC_MODTMsg tmsg = new SVC_MODTMsg();
        setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tmsg.svcModPk, cMsg.svcModPk);
        return (SVC_MODTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tmsg);
    }
}
