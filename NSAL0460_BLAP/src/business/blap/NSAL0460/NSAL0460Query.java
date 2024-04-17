/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0460;

import static business.blap.NSAL0460.constant.NSAL0460Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Start Read Capture
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Hitachi         T.Iwamoto       Create          N/A
 * 2015/12/14   Hitachi         T.Tsuchida      Update          QC#1577
 * 2016/02/08   Hitachi         A.Kohinata      Update          QC#3056
 * 2016/02/12   Hitachi         A.Kohinata      Update          QC#2886
 * 2016/02/18   Hitachi         A.Kohinata      Update          QC#2882
 * 2016/02/19   Hitachi         T.Aoyagi        Update          QC3694
 * 2018/05/11   Hitachi         K.Kojima        Update          QC#24817
 * 2022/06/22   CITS            E.Sanchez       Update          QC#59804
 * 2022/08/05   CITS            E.Sanchez       Update          QC#60357
 * 2022/11/04   CITS            E.Sanchez       Update          QC#60775
 *</pre>
 */
public final class NSAL0460Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0460Query INSTANCE = new NSAL0460Query();

    /**
     * Private constructor
     */
    private NSAL0460Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0460Query singleton instance
     */
    public static NSAL0460Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0420CMsg
     * @param sMsg NSAL0420SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMainData(NSAL0460CMsg cMsg, NSAL0460SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>();
        // START 2016/02/19 T.Aoyagi [QC3694, MOD]
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrPkList.add((BigDecimal) cMsg.P.no(i).dsContrPk_P1.getValue());
            if (ZYPCommonFunc.hasValue(cMsg.P.no(i).dsContrDtlPk_P1.getValue())) {
                dsContrDtlPkList.add(cMsg.P.no(i).dsContrDtlPk_P1.getValue());
            }
        }
        params.put("dsContrPkList", dsContrPkList);
        params.put("dsContrDtlPkList", dsContrDtlPkList);
        // END 2016/02/19 T.Aoyagi [QC3694, MOD]
        // [QC#2882,MOD]START
        //params.put("stsTerm", DS_CONTR_CTRL_STS.TERMINATED);
        //params.put("stsExp", DS_CONTR_CTRL_STS.EXPIRED);
        //params.put("stsCan", DS_CONTR_CTRL_STS.CANCELLED);
        //params.put("stsTermHold", DS_CONTR_CTRL_STS.TERMINATED_HOLD);
        //params.put("stsExpHold", DS_CONTR_CTRL_STS.EXPIRED_HOLD);
        params.put("stsDraft", DS_CONTR_CTRL_STS.DRAFT);
        params.put("stsEnterd", DS_CONTR_CTRL_STS.ENTERED);
        params.put("stsQaHold", DS_CONTR_CTRL_STS.QA_HOLD);
        // [QC#2882,MOD]END
        params.put("dsContrCatgCd", DS_CONTR_CATG.FLEET);
        // [QC#3056,MOD]START
        params.put("limitNum", sMsg.A.length() + 1);
        // [QC#3056,MOD]END
        // [QC#2886,ADD]START
        params.put("svcMemoTpCd", SVC_MEMO_TP.UPDATE_START_METER_READ);
        params.put("svcMemoTrxNm", SVC_MEMO_TRX_NM_SVC_PHYS_MTR_PK);
        // [QC#2886,ADD]END
        // START 2018/05/11 K.Kojima [QC#24817,ADD]
        params.put("noBllgCntrMsg", NO_BLLG_CNTR_MSG);
        // END 2018/05/11 K.Kojima [QC#24817,ADD]
        return getSsmEZDClient().queryEZDMsgArray("getMainData", params, sMsg.A);

    }

    // START 2022/06/22 E.Sanchez [QC#59804, ADD]
    /**
     * @param glblCmpyCd String
     * @param svcPhysMtrReadGrpSq BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrSchdBySvcPhysMrReadGrpSq(String glblCmpyCd, BigDecimal svcPhysMtrReadGrpSq) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcPhysMtrReadGrpSq", svcPhysMtrReadGrpSq);
        return getSsmEZDClient().queryObjectList("getBllgMtrSchdBySvcPhysMrReadGrpSq", ssmPrm);
    }
    // END 2022/06/22 E.Sanchez [QC#59804, ADD]

    // START 2022/06/22 E.Sanchez [QC#60357, ADD]
    /**
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @param mtrReadDt String
     * @param contrEffFromDt String
     * @return List<Map<String, Object>>
     */
    // START 2022/11/04 E.Sanchez [QC#60775, MOD]
    // public List<Map<String, Object>> getUnbilledSchedules(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal dsContrDtlPk, String mtrReadDt) {
    public List<Map<String, Object>> getUnbilledSchedules(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal dsContrDtlPk,
                                                          String mtrReadDt, String contrEffFromDt) {
    // END 2022/11/04 E.Sanchez [QC#60775, MOD]
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("stsCan", DS_CONTR_CTRL_STS.CANCELLED);
        ssmPrm.put("dsBllgSchdStsCdClose", DS_BLLG_SCHD_STS.CLOSE);
        ssmPrm.put("mtrReadDt", mtrReadDt);
        ssmPrm.put("usgChrgFlg", ZYPConstant.FLG_ON_Y);
        ssmPrm.put("skip", SKIP_RECOV_TP.SKIP);
        ssmPrm.put("bllblFlg", ZYPConstant.FLG_ON_Y);
        ssmPrm.put("actvFlg", ZYPConstant.FLG_ON_Y);
        // START 2022/11/04 E.Sanchez [QC#60775, ADD]
        ssmPrm.put("contrEffFromDt", contrEffFromDt);
        // END 2022/11/04 E.Sanchez [QC#60775, ADD]

        S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("getUnbilledSchedules", ssmPrm);
        if (rslt != null && rslt.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            return rsltList;
        }
        return null;
    }
    // END 2022/06/22 E.Sanchez [QC#60357, ADD]
}
