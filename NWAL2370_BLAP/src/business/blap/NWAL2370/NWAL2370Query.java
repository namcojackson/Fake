/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2370;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/10   Fujitsu         N.Sugiura       Create          QC#10374
 * 2018/04/10   Fujitsu         W.Honda         Update          QC#10374
 *</pre>
 */

public class NWAL2370Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL2370Query MY_INSTANCE = new NWAL2370Query();

    /**
     * Private constructor
     */
    private NWAL2370Query() {
        super();
    }

    /**
     * Get the NWAL2370Query instance.
     * @return NWAL2370Query instance
     */
    public static NWAL2370Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getReturnData
     * @param bizMsg NWAL2370CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContr(NWAL2370CMsg bizMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", bizMsg.dsContrDtlPk.getValue());

        return getSsmEZDClient().queryObject("getDsContr", params);
    }

    /**
     * getReturnData
     * @param bizMsg NWAL2370CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHdrInfo(NWAL2370CMsg bizMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", bizMsg.dsContrDtlPk.getValue());

        return getSsmEZDClient().queryObject("getHdrInfo", params);
    }


    /**
     * getReturnData
     * @param bizMsg NWAL2370CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHdrInfoFromImport(NWAL2370CMsg bizMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsImptSvcConfigRefPk", bizMsg.dsImptSvcConfigRefPk.getValue());

        return getSsmEZDClient().queryObject("getHdrInfoFromImport", params);
    }

    /**
     * getReturnData
     * @param bizMsg NWAL2370CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRntlEqInfo(BigDecimal dsContrPk, BigDecimal dsCpoConfigPk, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        params.put("dsCpoConfigPk", dsCpoConfigPk);
        params.put("addlChrgCatgCdR", ADDL_CHRG_CATG.RENTAL);
        params.put("prcQlfyTpItemCode", PRC_QLFY_TP.ITEM_CODE);
        params.put("flgN", ZYPConstant.FLG_OFF_N);
        params.put("maxDate", "99991231");

        return getSsmEZDClient().queryObjectList("getRntlEqInfo", params);
    }

    /**
     * getReturnData
     * @param bizMsg NWAL2370CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRntlSvcInfoFromImpt(BigDecimal dsImptSvcDtlPk, String dsOrdPosnNum, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsImptSvcDtlPk", dsImptSvcDtlPk);
        params.put("dsOrdPosnNum", dsOrdPosnNum);
        params.put("addlChrgCatgCdR", ADDL_CHRG_CATG.RENTAL);
        params.put("prcQlfyTpItemCode", PRC_QLFY_TP.ITEM_CODE);
        params.put("flgN", ZYPConstant.FLG_OFF_N);
        params.put("maxDate", "99991231");

        return getSsmEZDClient().queryObjectList("getRntlSvcInfoFromImpt", params);
    }

    /**
     * getReturnData
     * @param bizMsg NWAL2370CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAccChrgInfo(BigDecimal dsContrPk, BigDecimal dsCpoConfigPk, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        params.put("dsCpoConfigPk", dsCpoConfigPk);
        params.put("dsContrDtlTpCdAcc", DS_CONTR_DTL_TP.ACCESSORIES);

        return getSsmEZDClient().queryObjectList("getAccChrgInfo", params);
    }

    /**
     * getReturnData
     * @param bizMsg NWAL2370CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddlChrgInfo(BigDecimal dsContrPk, BigDecimal dsCpoConfigPk, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        params.put("dsCpoConfigPk", dsCpoConfigPk);
        params.put("addlChrgCatgCdN", ADDL_CHRG_CATG.NORMAL);
        params.put("dsContrDtlTpMACB", DS_CONTR_DTL_TP.BASE_ONLY);
        params.put("dsContrDtlTpMACBU", DS_CONTR_DTL_TP.BASE_AND_USAGE);
        params.put("dsContrDtlTpACC", DS_CONTR_DTL_TP.ACCESSORIES);

        return getSsmEZDClient().queryObjectList("getAddlChrgInfo", params);
    }

    /**
     * getReturnData
     * @param bizMsg NWAL2370CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddlChrgInfoFromImpt(BigDecimal dsImptSvcDtlPk, String dsOrdPosnNum, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsImptSvcDtlPk", dsImptSvcDtlPk);
        params.put("dsOrdPosnNum", dsOrdPosnNum);

        return getSsmEZDClient().queryObjectList("getAddlChrgInfoFromImpt", params);
    }

}
