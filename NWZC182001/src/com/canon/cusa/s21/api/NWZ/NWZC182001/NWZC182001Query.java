/**
 *  <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC182001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_ORD_CATGTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.parts.NWZC182001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMA_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Auto Add RMA API Query
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         E.Yoshitake     Create          N/A
 * 2017/04/19   Fujitsu         S.Iidaka        Update          S21_NA#18286
 * 2019/05/23   Fujitsu         Y.Kanefusa      Update          S21_NA#50156
 *</pre>
 */
public class NWZC182001Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWZC182001Query INSTANCE = new NWZC182001Query();

    /** SSM Batch Cliant */
    private final S21SsmBatchClient ssmBatchClient;

    /**
     * Constructor.
     */
    public NWZC182001Query() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Singleton instance getter.
     * @return NWZC182001Query
     */
    public static NWZC182001Query getInstance() {
        return INSTANCE;
    }

    /**
     * Get DS Order Category
     * @param pMsg NWZC182001PMsg
     * @return DS_ORD_CATGTMsg
     */
    public DS_ORD_CATGTMsg getDsOrdCatg(NWZC182001PMsg pMsg) {
        DS_ORD_CATGTMsg tMsg = new DS_ORD_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdCatgCd, pMsg.dsOrdCatgCd);
        return (DS_ORD_CATGTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    /**
     * Get DS Order Type
     * @param pMsg NWZC182001PMsg
     * @return DS_ORD_TPTMsg
     */
    public DS_ORD_TPTMsg getDsOrdTp(NWZC182001PMsg pMsg) {
        DS_ORD_TPTMsg tMsg = new DS_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        return (DS_ORD_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    /**
     * Get Service Config Master by PK
     * @param pMsg NWZC182001PMsg
     * @return SVC_CONFIG_MSTRTMsg
     */
    public SVC_CONFIG_MSTRTMsg getSvcConfigMstr(NWZC182001PMsg pMsg) {
        SVC_CONFIG_MSTRTMsg tMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
        return (SVC_CONFIG_MSTRTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    /**
     * Get Service Machine Master
     * @param pMsg NWZC182001PMsg
     * @return SVC_CONFIG_MSTRTMsg
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getSvcMachMstrbyPk(NWZC182001PMsg pMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("pMsg", pMsg);
        ssmParam.put("rmaReqTpCd", RMA_REQ_TP.RMA_REQUIRED); //QC#18286
        // QC#50156 2019/05/23 Add Start
        List svcMathMstrSts = new ArrayList<String>();
        svcMathMstrSts.add(SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        ssmParam.put("svcMathMstrSts", svcMathMstrSts);
        // QC#50156 2019/05/23 Add End
        return ssmBatchClient.queryObjectList("getSvcMachMstrbyPk", ssmParam);
    }

    /**
     * Get Service Machine Master by Serial#
     * @param pMsg NWZC182001PMsg
     * @return SVC_MACH_MSTRTMsgArray
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getSvcMachMstrbySerNum(NWZC182001PMsg pMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("pMsg", pMsg);
        ssmParam.put("rmaReqTpCd", RMA_REQ_TP.RMA_REQUIRED); //QC#18286
        // QC#50156 2019/05/23 Add Start
        List svcMathMstrSts = new ArrayList<String>();
        svcMathMstrSts.add(SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        ssmParam.put("svcMathMstrSts", svcMathMstrSts);
        // QC#50156 2019/05/23 Add End
        return ssmBatchClient.queryObjectList("getSvcMachMstrInfo", ssmParam);
    }
}
