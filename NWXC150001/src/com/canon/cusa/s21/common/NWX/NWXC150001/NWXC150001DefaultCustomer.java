/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

import business.parts.NMZC610001PMsg;

/**
 * <pre>
 * Get Default Customer Data 04 Mode
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/28   Fujitsu         S.Takami        Create          S21_NA#8659
 * 2019/01/31   Fujitsu         Hd.Sugawara     Update          S21_NA#30097
 * 2019/03/12   Fujitsu         Hd.Sugawara     Update          S21_NA#30730
 * </pre>
 */
public class NWXC150001DefaultCustomer {

    /** Deliver From  */
    public static final String MODE_SHIP_ACCT_TO_BILL_TO = "01";
    public static final String MODE_SHIP_TO_ACCT = "02";
    public static final String MODE_BILL_TO_ACCT = "03";

    /** Default serial Version */
    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * Get Default Customer Info
     * </pre>
     * @param paramBean Parameter Bean.
     * @return NMZC610001PMsg Customer Info PMessage
     */
    public static NMZC610001PMsg getDefaultCustomerData(NWXC150001DefaultCustomerBean paramBean) {

        NMZC610001PMsg custApiPMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(custApiPMsg.glblCmpyCd, paramBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(custApiPMsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
        ZYPEZDItemValueSetter.setValue(custApiPMsg.dsTrxRuleTpCd, paramBean.getDsTrxRuleTpCd());
        ZYPEZDItemValueSetter.setValue(custApiPMsg.dsAcctNum_I1, paramBean.getDsAcctNum());
        // Add Start 2019/01/31 QC#30097
        if (ZYPConstant.FLG_ON_Y.equals(paramBean.getXxSelFlg())) {
            ZYPEZDItemValueSetter.setValue(custApiPMsg.xxSelFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(custApiPMsg.xxSelFlg, ZYPConstant.FLG_OFF_N);
        }
        // Add End 2019/01/31 QC#30097

        String mode = paramBean.getXxMode();
        if (!ZYPCommonFunc.hasValue(mode)) {
            paramBean.setXxMode(MODE_SHIP_ACCT_TO_BILL_TO);
        }

        boolean isUseShipTo = false;
        boolean isUseBillTo = false;

        if (ZYPCommonFunc.hasValue(paramBean.getShipToCustCd())) {
            ZYPEZDItemValueSetter.setValue(custApiPMsg.shipToCustCd, paramBean.getShipToCustCd());
            isUseShipTo = true;
        } else if (ZYPCommonFunc.hasValue(paramBean.getBillToCustCd())) {
            ZYPEZDItemValueSetter.setValue(custApiPMsg.billToCustCd, paramBean.getBillToCustCd());
            isUseBillTo = true;
        }

        // call NMZC6100 Customer Information Get API
        NMZC610001 api = new NMZC610001();
        ONBATCH_TYPE onBatchType = paramBean.getOnBatchType();
        if (onBatchType == null) {
            onBatchType = ONBATCH_TYPE.ONLINE;
        }
        api.execute(custApiPMsg, onBatchType);

        if (S21ApiUtil.isXxMsgId(custApiPMsg)) {
            return custApiPMsg;
        }

        if (isUseShipTo || isUseBillTo) {
            return custApiPMsg;
        }

        // Del Start 2019/01/31 QC#30097
//        if (custApiPMsg.DefaultBillShipList.getValidCount() > 0) {
//            boolean isRecall = false;
//            String shipToCustCd = null;
//            String billToCustCd = null;
//
//            if (S21StringUtil.isEquals(MODE_SHIP_ACCT_TO_BILL_TO, mode) //
//                    || S21StringUtil.isEquals(MODE_SHIP_TO_ACCT, mode)) {
//                shipToCustCd = custApiPMsg.DefaultBillShipList.no(0).shipToCustCd.getValue();
//                if (ZYPCommonFunc.hasValue(shipToCustCd)) {
//                    custApiPMsg.shipToCustCd.setValue(shipToCustCd);
//                    isRecall = true;
//                }
//            } else if (S21StringUtil.isEquals(MODE_BILL_TO_ACCT, mode)) {
//                billToCustCd = custApiPMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
//                if (ZYPCommonFunc.hasValue(billToCustCd)) {
//                    custApiPMsg.billToCustCd.setValue(billToCustCd);
//                    isRecall = true;
//                }
//            }
//            
//            if (isRecall) {
//                custApiPMsg.DefaultBillShipList.clear();
//                api.execute(custApiPMsg, onBatchType);
//            }
//        }
        // Del End 2019/01/31 QC#30097
        return custApiPMsg;
    }

    // Add Start 2019/03/12 QC#30730
    /**
     * getRelatedBillTo
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return String
     */
    public static String getRelatedBillTo(String glblCmpyCd, String shipToCustCd){
        String result = NWXC150001Query.getInstance().getRelatedBillTo(glblCmpyCd, shipToCustCd);

        return result;
    }
    // Add End 2019/03/12 QC#30730
}
