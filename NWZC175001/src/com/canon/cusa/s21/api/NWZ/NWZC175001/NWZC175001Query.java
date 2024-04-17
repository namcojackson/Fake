/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC175001;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.api.NWZ.NWZC175001.cache.DataCacheForSSM;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Buyout Approval Hold API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/21   Fujitsu         E.Yoshitake     Create          N/A
 * 2016/10/13   Fujitsu         T.Yoshida       Create          QC#14973 (For Performance)
 *</pre>
 */
public class NWZC175001Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWZC175001Query INSTANCE = new NWZC175001Query();

    /**
     * Constructor.
     */
    public NWZC175001Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWZC175001Query
     */
    public static NWZC175001Query getInstance() {
        return INSTANCE;
    }

    /**
     * Count Buyout Approval Hold
     * @param bean NWXC005001ValidationBean
     * @return hold count
     */
    public Integer cntBuyoutApprovalHold(NWXC005001ValidationBean bean) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("dsCpoDtlTMsg", bean.getDscdTMsg());
        ssmParam.put("glblCmpyCd", bean.getInputPMsg().glblCmpyCd);
        ssmParam.put("hldRsnCd", HLD_RSN.BUYOUT_APPROVAL);

        return (Integer) getSsmEZDClient().queryObject("cntBuyoutApprovalHold", ssmParam).getResultObject();
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

        return DataCacheForSSM.getInstance().cntCreditOrder(ssmParam, getSsmEZDClient());
    }

    /**
     * Get Request Process Flag
     * @param bean NWXC005001ValidationBean
     * @return request process flag
     */
    public String getRqstProcFlg(NWXC005001ValidationBean bean) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("pMsg", bean.getInputPMsg());
        ssmParam.put("dsCpoTMsg", bean.getDscTMsg());
        ssmParam.put("dsCpoDtlTMsg", bean.getDscdTMsg());

        return DataCacheForSSM.getInstance().getRqstProcFlg(ssmParam, getSsmEZDClient());
    }
}
