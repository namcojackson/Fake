/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/06/2010   Fujitsu         S.Yamamoto      Create          5224
 * </pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.db.BILL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsg;
//import business.db.US_TAX_EXEMTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWXC001001Certificate {

    public static final Class clazz = NWXC001001Certificate.class;

    public static final String clazzNm = clazz.getSimpleName();

    public static final S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(clazz);

    /**
     * Roll layer number 0 (prinCustPk)
     */
    private static final int ASG_LOC_ROLE_LAYER_NUM0 = 0;

    /**
     * Roll layer number 1
     */
    private static final int ASG_LOC_ROLE_LAYER_NUM1 = 1;

    /**
     * Roll layer number 2
     */
    private static final int ASG_LOC_ROLE_LAYER_NUM2 = 2;

    /**
     * chkCertificate
     * @param glblCmpyCd
     * @param billToCustCd
     * @param sellToCustCd
     * @param shipToStCd
     * @param slsDt
     * @return String
     */
    public static String chkCertificate(String glblCmpyCd, String billToCustCd, String sellToCustCd, String shipToStCd, String slsDt) {

//        // ===================================
//        // Parameter check
//        // ===================================
//        if (!inputParameterCheck(glblCmpyCd, billToCustCd, sellToCustCd, shipToStCd, slsDt)) {
//            return ZYPConstant.FLG_OFF_N;
//        }
//
//        // ===================================
//        // Get US_ST_TAX_EXAM
//        // ===================================
//        BILL_TO_CUSTTMsg billToCustTRes = getBillToCust(glblCmpyCd, billToCustCd);
//        if (billToCustTRes == null) {
//            return ZYPConstant.FLG_OFF_N;
//        }
//
//        SELL_TO_CUSTTMsg sellToCustTRes = getSellToCust(glblCmpyCd, sellToCustCd);
//        if (sellToCustTRes == null) {
//            return ZYPConstant.FLG_OFF_N;
//        }
//
//        BigDecimal prinCustPk = sellToCustTRes.prinCustPk.getValue();
//        BigDecimal billToCustPk = billToCustTRes.billToCustPk.getValue();
//        BigDecimal sellToCustPk = sellToCustTRes.sellToCustPk.getValue();
//        US_TAX_EXEMTMsg usTaxExemTRes = getUsTaxExem(glblCmpyCd, prinCustPk, billToCustPk, sellToCustPk);
//        if (usTaxExemTRes == null) {
//            return ZYPConstant.FLG_OFF_N;
//        }
//
//        BigDecimal custPk = usTaxExemTRes.custPk.getValue();
//        BigDecimal asgLocRoleLayerNum = usTaxExemTRes.asgLocRoleLayerNum.getValue();
//        boolean isUsStTaxExam = isUsStTaxExam(glblCmpyCd, custPk, asgLocRoleLayerNum, shipToStCd, slsDt);
//        if (!isUsStTaxExam) {
//            return ZYPConstant.FLG_OFF_N;
//        }

        return ZYPConstant.FLG_ON_Y;
    }

    private static boolean inputParameterCheck(String glblCmpyCd, String billToCustCd, String sellToCustCd, String shipToStCd, String slsDt) {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(sellToCustCd)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(shipToStCd)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(slsDt)) {
            return false;
        }
        return true;
    }

    private static BILL_TO_CUSTTMsg getBillToCust(String glblCmpyCd, String billToCustCd) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("billToCustCd", billToCustCd);

        BILL_TO_CUSTTMsg billToCustTmsg = (BILL_TO_CUSTTMsg) ssmBatchClient.queryObject("queryBillToCust", queryParam);

        return billToCustTmsg;
    }

    private static SELL_TO_CUSTTMsg getSellToCust(String glblCmpyCd, String sellToCustCd) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("sellToCustCd", sellToCustCd);

        SELL_TO_CUSTTMsg sellToCustTmsg = (SELL_TO_CUSTTMsg) ssmBatchClient.queryObject("querySellToCust", queryParam);

        return sellToCustTmsg;
    }
//
//    private static US_TAX_EXEMTMsg getUsTaxExem(String glblCmpyCd, BigDecimal prinCustPk, BigDecimal billToCustPk, BigDecimal sellToCustPk) {
//
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("prinCustPk", prinCustPk);
//        queryParam.put("billToCustPk", billToCustPk);
//        queryParam.put("sellToCustPk", sellToCustPk);
//        queryParam.put("asgLocRoleLayerNum0", ASG_LOC_ROLE_LAYER_NUM0);
//        queryParam.put("asgLocRoleLayerNum1", ASG_LOC_ROLE_LAYER_NUM1);
//        queryParam.put("asgLocRoleLayerNum2", ASG_LOC_ROLE_LAYER_NUM2);
//
//        US_TAX_EXEMTMsg usTaxExemTmsg = (US_TAX_EXEMTMsg) ssmBatchClient.queryObject("queryUsTaxExam", queryParam);
//
//        return usTaxExemTmsg;
//    }
//
//    private static boolean isUsStTaxExam(String glblCmpyCd, BigDecimal custPk, BigDecimal asgLocRoleLayerNum, String shipToStCd, String slsDt) {
//
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("custPk", custPk);
//        queryParam.put("asgLocRoleLayerNum", asgLocRoleLayerNum);
//        queryParam.put("stCd", shipToStCd);
//        queryParam.put("slsTaxExemFlg", ZYPConstant.FLG_ON_Y);
//        queryParam.put("slsDt", slsDt);
//
//        Integer ssmRes = (Integer) ssmBatchClient.queryObject("queryUsStTaxExam", queryParam);
//
//        if (ssmRes > 0) {
//            return true;
//        }
//        return false;
//    }
}
