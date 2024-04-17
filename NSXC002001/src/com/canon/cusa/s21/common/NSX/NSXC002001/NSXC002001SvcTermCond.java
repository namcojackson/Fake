/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC002001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.common.NSX.NSXC002001.constant.NSXC002001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Term Condition
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/31   Hitachi         T.Kanasaka      Create          QC#15136
 * </pre>
 */
public class NSXC002001SvcTermCond {

    /**
     * S21SsmBatchClient object.
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC002001SvcTermCond.class);

    /**
     * Get Default Service Term and Condition (Contract Level)
     * @param glblCmpyCd Global Company Code
     * @param slsDt Sales Date
     * @param svcPgmMdseCd Service Program Mdse Code
     * @return Default Term Condition Information Bean List
     */
    public static List<DefSvcTermCondInfoBean> getTermCondInfoForContrLvl(String glblCmpyCd, String slsDt, String svcPgmMdseCd) {
        if (!checkParameter(glblCmpyCd, slsDt, svcPgmMdseCd)) {
            return null;
        }

        return getDefTermCond(glblCmpyCd, slsDt, svcPgmMdseCd, ZYPConstant.FLG_ON_Y);
    }

    /**
     * Get Default Service Term and Condition (Machine Level)
     * @param glblCmpyCd Global Company Code
     * @param slsDt Sales Date
     * @param svcPgmMdseCd Service Program Mdse Code
     * @return Default Term Condition Information Bean List
     */
    public static List<DefSvcTermCondInfoBean> getTermCondInfoForMachLvl(String glblCmpyCd, String slsDt, String svcPgmMdseCd) {
        if (!checkParameter(glblCmpyCd, slsDt, svcPgmMdseCd)) {
            return null;
        }

        return getDefTermCond(glblCmpyCd, slsDt, svcPgmMdseCd, ZYPConstant.FLG_OFF_N);
    }

    /**
     * Exists Term and Condition (Contract Level)
     * @param glblCmpyCd Global Company Code
     * @param dsContrPk DS Contract PK
     * @return true:exist
     */
    public static boolean isExistTermCondForContrLvl(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        BigDecimal svcTermCondPk = (BigDecimal) SSM_CLIENT.queryObject("isExistTermCondForContrLvl", ssmPrm);
        if (svcTermCondPk == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Exists Term and Condition (Machine Level)
     * @param glblCmpyCd Global Company Code
     * @param dsContrPk DS Contract PK
     * @param dsContrDtlPk DS Contract Detail PK
     * @return true:exist
     */
    public static boolean isExistTermCondForMachLvl(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        BigDecimal svcTermCondPk = (BigDecimal) SSM_CLIENT.queryObject("isExistTermCondForMachLvl", ssmPrm);
        if (svcTermCondPk == null) {
            return false;
        } else {
            return true;
        }
    }

    private static List<DefSvcTermCondInfoBean> getDefTermCond(String glblCmpyCd, String slsDt, String svcPgmMdseCd, String contrFlg) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("svcPgmMdseCd", svcPgmMdseCd);
        ssmPrm.put("defEffThruDt", NSXC002001Constant.DEF_EFF_THRU_DT);
        if (ZYPConstant.FLG_ON_Y.equals(contrFlg)) {
            ssmPrm.put("contrFlg", ZYPConstant.FLG_ON_Y);
            ssmPrm.put("machFlg", ZYPConstant.FLG_OFF_N);
        } else {
            ssmPrm.put("contrFlg", ZYPConstant.FLG_OFF_N);
            ssmPrm.put("machFlg", ZYPConstant.FLG_ON_Y);
        }

        return (List<DefSvcTermCondInfoBean>) SSM_CLIENT.queryObjectList("getDefTermCond", ssmPrm);
    }

    private static boolean checkParameter(String glblCmpyCd, String slsDt, String svcPgmMdseCd) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(slsDt)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(svcPgmMdseCd)) {
            return false;
        }
        return true;
    }
}
