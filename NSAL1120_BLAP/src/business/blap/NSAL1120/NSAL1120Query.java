/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1120;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Meter and Pricing Details
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         O.Okuma         Create          N/A
 * 2016/03/17   Hitachi         O.Okuma         Update          QC5422
 * 2016/04/18   Hitachi         K.Yamada        Update          CSA QC#7056
 * 2017/10/02   Hitachi         U.Kim           Update          QC#18749
 * 2018/07/19   Hitachi         K.Kojima        Update          QC#26791
 * 2022/04/04   CITS            E.Sanchez       Update          QC#59914
 *</pre>
 */
public final class NSAL1120Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1120Query INSTANCE = new NSAL1120Query();

    /**
     * Constructor.
     */
    private NSAL1120Query() {
        super();
    }

    /**
     * getBaseDataForRebil
     * @param cMsg NSAL1120CMsg
     * @param sMsg NSAL1120SMsg
     * @return Search Data
     */
    public S21SsmEZDResult getBaseDataForRebil(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcCrRebilDtlPk", cMsg.svcCrRebilDtlPk.getValue());
        // add start 2016/04/15 CSA Defect#7056
        params.put("dsContrCatg_reg", DS_CONTR_CATG.REGULAR);
        params.put("dsContrDtlTp_flt", DS_CONTR_DTL_TP.FLEET);
        params.put("dsContrCatg_agg", DS_CONTR_CATG.AGGREGATE);
        params.put("dsContrDtlTp_agg", DS_CONTR_DTL_TP.AGGREGATE);
        // add end 2016/04/15 CSA Defect#7056
        // START 2022/04/04 E.Sanchez [QC#59914,ADD]
        params.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // END 2022/04/04 E.Sanchez [QC#59914,ADD]
        params.put("rowNum", sMsg.A.length() + 1);

        return getSsmEZDClient().queryObjectList("getBaseDataForRebil", params);
    }

    /**
     * getBaseDataForInv
     * @param cMsg NSAL1120CMsg
     * @param sMsg NSAL1120SMsg
     * @return Search Data
     */
    public S21SsmEZDResult getBaseDataForInv(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcInvLinePk", cMsg.svcInvLinePk.getValue());
        // START 2017/10/02 U.Kim [QC#18749, ADD]
        params.put("svcCrRebilPk", cMsg.svcCrRebilPk.getValue());
        // END 2017/10/02 U.Kim [QC#18749, ADD]
        // add start 2016/04/15 CSA Defect#7056
        params.put("dsContrCatg_reg", DS_CONTR_CATG.REGULAR);
        params.put("dsContrDtlTp_flt", DS_CONTR_DTL_TP.FLEET);
        params.put("dsContrCatg_agg", DS_CONTR_CATG.AGGREGATE);
        params.put("dsContrDtlTp_agg", DS_CONTR_DTL_TP.AGGREGATE);
        // add end 2016/04/15 CSA Defect#7056
        // START 2022/04/04 E.Sanchez [QC#59914,ADD]
        params.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // END 2022/04/04 E.Sanchez [QC#59914,ADD]
        params.put("rowNum", sMsg.A.length() + 1);

        return getSsmEZDClient().queryObjectList("getBaseDataForInv", params);
    }

    /**
     * getMeterDataForRebil
     * @param cMsg NSAL1120CMsg
     * @param sMsg NSAL1120SMsg
     * @return Search Data
     */
    public S21SsmEZDResult getMeterDataForRebil(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcCrRebilDtlPk", cMsg.svcCrRebilDtlPk.getValue());
        // START 2022/04/04 E.Sanchez [QC#59914,ADD]
        params.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // END 2022/04/04 E.Sanchez [QC#59914,ADD]
        params.put("rowNum", sMsg.B.length() + 1);

        return getSsmEZDClient().queryObjectList("getMeterDataForRebil", params);
    }

    /**
     * getMeterDataForInv
     * @param cMsg NSAL1120CMsg
     * @param sMsg NSAL1120SMsg
     * @return Search Data
     */
    public S21SsmEZDResult getMeterDataForInv(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcInvLinePk", cMsg.svcInvLinePk.getValue());
        // START 2017/10/02 U.Kim [QC#18749, ADD]
        params.put("svcCrRebilPk", cMsg.svcCrRebilPk.getValue());
        // END 2017/10/02 U.Kim [QC#18749, ADD]
        // START 2022/04/04 E.Sanchez [QC#59914,ADD]
        params.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // END 2022/04/04 E.Sanchez [QC#59914,ADD]
        params.put("rowNum", sMsg.B.length() + 1);

        return getSsmEZDClient().queryObjectList("getMeterDataForInv", params);
    }

    /**
     * getPricingDataForRebilNonFleet
     * @param cMsg NSAL1120CMsg
     * @param sMsg NSAL1120SMsg
     * @param list List<Integer>
     * @return Search Data
     */
    public S21SsmEZDResult getPricingDataForRebilNonFleet(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg, List<Integer> list) {

        Map<String, Object> params = new HashMap<String, Object>();

        // mod start 2016/04/19 CSA Defect#7235
        // START 2018/07/19 K.Kojima [QC#26791,MOD]
        // List<BigDecimal> svcCrRebilMtrBllgPkList = new ArrayList<BigDecimal>();
        List<BigDecimal> svcMachMstrPkList = new ArrayList<BigDecimal>();
        // END 2018/07/19 K.Kojima [QC#26791,MOD]

        for (Integer selectedRowNum : list) {
            // START 2018/07/19 K.Kojima [QC#26791,MOD]
            // if (hasValue(sMsg.B.no(selectedRowNum).svcCrRebilMtrBllgPk_B)) {
            //     svcCrRebilMtrBllgPkList.add(sMsg.B.no(selectedRowNum).svcCrRebilMtrBllgPk_B.getValue());
            // }
            if (hasValue(sMsg.B.no(selectedRowNum).svcMachMstrPk_B)) {
                svcMachMstrPkList.add(sMsg.B.no(selectedRowNum).svcMachMstrPk_B.getValue());
            }
            // END 2018/07/19 K.Kojima [QC#26791,MOD]
        }
        // mod end 2016/04/19 CSA Defect#7235

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        // START 2018/07/19 K.Kojima [QC#26791,MOD]
        // params.put("svcCrRebilMtrBllgPkList", svcCrRebilMtrBllgPkList.toArray());
        params.put("svcMachMstrPkList", svcMachMstrPkList.toArray());
        // END 2018/07/19 K.Kojima [QC#26791,MOD]
        // add start 2016/04/18 CSA Defect#7056
        params.put("svcCrRebilDtlPk", cMsg.svcCrRebilDtlPk.getValue());
        params.put("dsContrDtlTpAgg", DS_CONTR_DTL_TP.AGGREGATE);
        // add end 2016/04/18 CSA Defect#7056
        params.put("rowNum", sMsg.C.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getPricingDataForRebilNonFleet", params, sMsg.C);
    }

    /**
     * getPricingDataForRebilFleet
     * @param cMsg NSAL1120CMsg
     * @param sMsg NSAL1120SMsg
     * @param list List<Integer>
     * @return Search Data
     */
    public S21SsmEZDResult getPricingDataForRebilFleet(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg, List<Integer> list) {

        Map<String, Object> params = new HashMap<String, Object>();
        String[] bllgMtrLbCdList = new String[list.size()];

        // mod start 2016/04/14 CSA Defect#5260
        //for (int i = 0; i < list.size(); i++) {
        //    bllgMtrLbCdList[i] = sMsg.B.no(list.get(i)).bllgMtrLbCd_B.getValue();
        //}

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcCrRebilDtlPk", cMsg.svcCrRebilDtlPk.getValue());
        //params.put("bllgMtrLbCdList", bllgMtrLbCdList);
        params.put("dsContrDtlTpCd_Fleet", DS_CONTR_DTL_TP.FLEET);
        params.put("rowNum", sMsg.C.length() + 1);
        // mod end 2016/04/14 CSA Defect#5260

        return getSsmEZDClient().queryEZDMsgArray("getPricingDataForRebilFleet", params, sMsg.C);
    }

    /**
     * getPricingDataForInvNonFleet
     * @param cMsg NSAL1120CMsg
     * @param sMsg NSAL1120SMsg
     * @param list List<Integer>
     * @return Search Data
     */
    public S21SsmEZDResult getPricingDataForInvNonFleet(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg, List<Integer> list) {

        Map<String, Object> params = new HashMap<String, Object>();

        BigDecimal[] svcInvLineMtrPkList = new BigDecimal[list.size()];

        for (int i = 0; i < list.size(); i++) {
            svcInvLineMtrPkList[i] = sMsg.B.no(list.get(i)).svcInvLineMtrPk_B.getValue();
        }

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcInvLineMtrPkList", svcInvLineMtrPkList);
        params.put("rowNum", sMsg.C.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getPricingDataForInvNonFleet", params, sMsg.C);
    }

    /**
     * getPricingDataForInvFleet
     * @param cMsg NSAL1120CMsg
     * @param sMsg NSAL1120SMsg
     * @param list List<Integer>
     * @return Search Data
     */
    public S21SsmEZDResult getPricingDataForInvFleet(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg, List<Integer> list) {

        Map<String, Object> params = new HashMap<String, Object>();

        // mod start 2016/04/14 CSA Defect#5260
        //String[] svcInvNumList = new String[list.size()];
        //String[] mtrLbCdList = new String[list.size()];

        //for (int i = 0; i < list.size(); i++) {
        //    svcInvNumList[i] = sMsg.B.no(list.get(i)).origSvcInvNum_B.getValue();
        //    mtrLbCdList[i] = sMsg.B.no(list.get(i)).mtrLbCd_B.getValue();
        //}

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        //params.put("svcInvNumList", svcInvNumList);
        //params.put("mtrLbCdList", mtrLbCdList);
        params.put("svcInvLinePk", cMsg.svcInvLinePk.getValue());
        params.put("dsContrDtlTpCd_Fleet", DS_CONTR_DTL_TP.FLEET);
        params.put("rowNum", sMsg.C.length() + 1);
        // mod end 2016/04/14 CSA Defect#5260

        return getSsmEZDClient().queryEZDMsgArray("getPricingDataForInvFleet", params, sMsg.C);
    }

    /**
     * Singleton instance getter.
     * @return NSAL1120Query
     */
    public static NSAL1120Query getInstance() {
        return INSTANCE;
    }

}
