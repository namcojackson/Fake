/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1250;

import static business.blap.NSAL1250.constant.NSAL1250Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.SVC_CONTR_BLLGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Preview Billing Workflow Details
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/07   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/04/14   Hitachi         K.Kishimoto     Update          QC#6657
 * 2017/09/06   Hitachi         K.Kishimoto     Update          QC#20963
 * 2018/06/15   Fujitsu         T.Ogura         Update          QC#24930
 * 2019/10/15   Hitachi         A.Kohinata      Update          QC#53574
 * 2019/11/28   Hitachi         K.Kishimoto     Update          QC#53567
 *</pre>
 */
public final class NSAL1250Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL1250Query INSTANCE = new NSAL1250Query();

    /**
     * Private constructor
     */
    private NSAL1250Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL1250Query singleton instance
     */
    public static NSAL1250Query getInstance() {
        return INSTANCE;
    }

    /**
     * getTransactionInfo
     * @param svcContrBllgPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTransactionInfo(BigDecimal svcContrBllgPk) {
        String glblCmpyCd = getGlobalCompanyCode();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgPk", svcContrBllgPk);
        //Add Start 04/14/2016 <QC#6657>
        ssmParam.put("dsContrCatgCd_Fleet", DS_CONTR_CATG.FLEET);
        //Add End 04/14/2016 <QC#6657>
        //Add Start 04/14/2016 <QC#20963>
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        //Add End 04/14/2016 <QC#20963>
        ssmParam.put("limitNum", 1);
        return getSsmEZDClient().queryObject("getTransactionInfo", ssmParam);
    }

    /**
     * getDetail1
     * @param svcContrBllgPk BigDecimal
     * @param limitNum int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetail1(SVC_CONTR_BLLGTMsg svcContrBllg, int limitNum) {
        String glblCmpyCd = getGlobalCompanyCode();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dateFormat", DATE_FORMAT);
        ssmParam.put("limitNum", limitNum);
        // mod start 2016/09/30 CSA Defect#10729
        ssmParam.put("billToCustCd", svcContrBllg.billToCustCd.getValue());
        ssmParam.put("svcContrBllgThruDt", svcContrBllg.svcContrBllgThruDt.getValue());
        if (!DS_CONTR_CATG.FLEET.equals(svcContrBllg.dsContrCatgCd.getValue())) {
            ssmParam.put("svcMachMstrPk", svcContrBllg.svcMachMstrPk.getValue());
        } else {
            ssmParam.put("dsContrPk", svcContrBllg.dsContrPk.getValue());
            // START 2018/06/15 T.Ogura [QC#24930,ADD]
            ssmParam.put("fleetFlg", ZYPConstant.FLG_ON_Y);
            // END   2018/06/15 T.Ogura [QC#24930,ADD]
        }
        // mod end 2016/09/30 CSA Defect#10729
        // add start 2019/10/11 QC#53574
        ssmParam.put("invTpInv", INV_TP.INVOICE);
        // add end 2019/10/11 QC#53574
        return getSsmEZDClient().queryObjectList("getDetail1", ssmParam);
    }

    /**
     * getSvcContrBllg
     * @param glblCmpyCd String
     * @param svcContrBllgPk BigDecimal
     * @return SVC_CONTR_BLLGTMsg
     */
    public SVC_CONTR_BLLGTMsg getSvcContrBllg(BigDecimal svcContrBllgPk) {
        String glblCmpyCd = getGlobalCompanyCode();
        SVC_CONTR_BLLGTMsg inMsg = new SVC_CONTR_BLLGTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcContrBllgPk, svcContrBllgPk);

        return (SVC_CONTR_BLLGTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    // START 2019/11/28 [QC#5356,ADD]
    /**
     * getDetail1
     * @param svcContrBllgPk BigDecimal
     * @return S21SsmEZDResult
     */
    public BigDecimal getSvcContrBllgPk(BigDecimal svcContrBllgPk) {
        BigDecimal retSvcContrBllgPk = null;
        String glblCmpyCd = getGlobalCompanyCode();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgPk", svcContrBllgPk);
        
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getSvcContrBllgPk", ssmParam);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > 0) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
                retSvcContrBllgPk = (BigDecimal)list.get(0).get("SVC_CONTR_BLLG_PK");
            }
        }
        return retSvcContrBllgPk;
    }
    // END   2019/11/28 [QC#5356,ADD]
}
