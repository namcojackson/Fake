/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2290;

import java.util.HashMap;
import java.util.Map;

import business.blap.NWAL2290.constant.NWAL2290Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL2290BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/05   Fujitsu         A.Kosai         Create          N/A
 * 2919/03/27   Fujitsu         K.Ishizuka      Update          S21_NA#30765
 *</pre>
 */
public final class NWAL2290Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL2290Query MY_INSTANCE = new NWAL2290Query();

    /**
     * Private constructor
     */
    private NWAL2290Query() {
        super();
    }

    /**
     * Get the NWAL2290Query instance.
     * @return NWAL2290Query instance.
     */
    public static NWAL2290Query getInstance() {
        return MY_INSTANCE;
    }

    public S21SsmEZDResult getEopsCpoList(String glblCmpyCd, NWAL2290CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordSrcRefNum", bizMsg.ordSrcRefNum.getValue());
        params.put("configCatgCd_O", CONFIG_CATG.OUTBOUND);
        params.put("imptSts_Err", IMPT_STS.ERROR);
        params.put("rankNum", NWAL2290Constant.RANK_NUM);

        return getSsmEZDClient().queryObjectList("getEopsCpoList", params);
    }
    
    // 2019/03/27 S21_NA#30765 Add Start
    public S21SsmEZDResult getDealConfigCpoList(String glblCmpyCd, NWAL2290CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordSrcRefNum", bizMsg.ordSrcRefNum.getValue());

        return getSsmEZDClient().queryObjectList("getDealConfigCpoList", params);
    }
    // 2019/03/27 S21_NA#30765 Add End
}
