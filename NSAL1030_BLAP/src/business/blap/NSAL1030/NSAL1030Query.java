/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1030;

import static business.blap.NSAL1030.constant.NSAL1030Constant.*;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Contract Invoice Detail Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/29   Hitachi         A.Kohinata      Create          N/A
 * 05/11/2016   Hitachi         T.Aoyagi        Update          QC#7734
 *</pre>
 */
public final class NSAL1030Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1030Query INSTANCE = new NSAL1030Query();

    /**
     * Constructor.
     */
    private NSAL1030Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL1030Query
     */
    public static NSAL1030Query getInstance() {
        return INSTANCE;
    }

    /**
     * getHeaderInfo
     * @param cMsg NSAL1030CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderInfo(NSAL1030CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcCrRebilPk", cMsg.svcCrRebilPk_P.getValue());

        return getSsmEZDClient().queryEZDMsg("getHeaderInfo", params, cMsg);
    }

    /**
     * getDetailInfo
     * @param cMsg NSAL1030CMsg
     * @param sMsg NSAL1030SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailInfo(NSAL1030CMsg cMsg, NSAL1030SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cMsg", cMsg);
        params.put("svcInvChrgTpBaseCharge", SVC_INV_CHRG_TP.BASE_CHARGE);
        params.put("svcInvChrgTpMeterCharge", SVC_INV_CHRG_TP.METER_CHARGE);
        params.put("invTypeBase", DISP_INV_TYPE_BASE);
        params.put("invTypeUsage", DISP_INV_TYPE_USAGE);
        params.put("svcInvChrgTpCdList", new String[] {SVC_INV_CHRG_TP.BASE_CHARGE, SVC_INV_CHRG_TP.METER_CHARGE });
        // START 05/11/2016 T.Aoyagi [QC#7734, ADD]
        params.put("svcCrRebilStsCd", SVC_CR_REBIL_STS.CANCELLED);
        // END 05/11/2016 T.Aoyagi [QC#7734, ADD]
        // add start 2016/04/18 CSA Defect#7056
        params.put("dsContrCatg_reg", DS_CONTR_CATG.REGULAR);
        params.put("dsContrDtlTp_flt", DS_CONTR_DTL_TP.FLEET);
        params.put("dsContrCatg_agg", DS_CONTR_CATG.AGGREGATE);
        params.put("dsContrDtlTp_agg", DS_CONTR_DTL_TP.AGGREGATE);
        // add end 2016/04/18 CSA Defect#7056
        params.put("rowNum", cnt);

        return getSsmEZDClient().queryEZDMsgArray("getDetailInfo", params, sMsg.A);
    }
}
