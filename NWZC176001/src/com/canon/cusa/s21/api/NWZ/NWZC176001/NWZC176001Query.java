/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC176001;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.api.NWZ.NWZC176001.cache.DataCacheForSSM;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Buyout Billing Hold API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         E.Yoshitake     Create          N/A
 * 2016/10/13   Fujitsu         T.Yoshida       Create          QC#14973 (For Performance)
 *</pre>
 */
public class NWZC176001Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWZC176001Query INSTANCE = new NWZC176001Query();

    /**
     * Constructor.
     */
    public NWZC176001Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWZC175001Query
     */
    public static NWZC176001Query getInstance() {
        return INSTANCE;
    }

    /**
     * Count Buyout Billing Hold
     * @param bean NWXC005001ValidationBean
     * @return hold count
     */
    public Integer cntBuyoutBillingHold(NWXC005001ValidationBean bean) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("dsCpoDtlTMsg", bean.getDscdTMsg());
        ssmParam.put("glblCmpyCd", bean.getInputPMsg().glblCmpyCd);
        ssmParam.put("hldRsnCd", HLD_RSN.BUYOUT_BILLING);

        return (Integer) getSsmEZDClient().queryObject("cntBuyoutBillingHold", ssmParam).getResultObject();
    }

    /**
     * Count Credit Order
     * @param bean NWXC005001ValidationBean
     * @return credit order count
     */
    public Integer cntCreditOrder(NWXC005001ValidationBean bean) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("pMsg", bean.getInputPMsg());
        ssmParam.put("ordLineStsCd", ORD_LINE_STS.CANCELLED);
        ssmParam.put("crRebilCd", CR_REBIL.CREDIT);

        // QC#14973 Mod Start
        // return (Integer) getSsmEZDClient().queryObject("cntCreditOrder", ssmParam).getResultObject();
        return DataCacheForSSM.getInstance().cntCreditOrder(ssmParam, getSsmEZDClient());
        // QC#14973 Mod End
    }

    /**
     * Get DS CPO DTL Info
     * @param bean NWXC005001ValidationBean
     * @return DS CPO DTL Info
     */
    public List<Map<String, String>> getDsCpoDtlInfo(NWXC005001ValidationBean bean) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("pMsg", bean.getInputPMsg());
        ssmParam.put("dsCpoDtlTMsg", bean.getDscdTMsg());
        ssmParam.put("ordLineStsCd", ORD_LINE_STS.CANCELLED);

         // QC#14973 Mod Start
        // return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getDsCpoDtlInfo", ssmParam).getResultObject();
        return DataCacheForSSM.getInstance().getDsCpoDtlInfo(ssmParam, getSsmEZDClient());
        // QC#14973 Mod End
    }
}
