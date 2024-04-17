/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC035001;

import java.util.Comparator;

import business.parts.NWZC035002PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 * <pre>
 * INV_BOLComparator
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/11/2009   Fujitsu         N.Mitsuishi     Create          N/A
 * 10/05/2015   Fujitsu         H.Nagashima     Update          CSA
 * </pre>
 */
public class INV_BOLDataComparator implements Comparator<OrderDataBean> {

    /**
     * This method is used to sort it before the repetition deletion
     * of INV_BOL data is done.
     * @param arg0 OrderDataBean
     * @param arg1 OrderDataBean
     * @return int
     */
    public int compare(OrderDataBean arg0, OrderDataBean arg1) {

        String str0 = makeComparatorString(arg0);
        String str1 = makeComparatorString(arg1);

        return str0.compareTo(str1);
    }

    private String makeComparatorString(OrderDataBean arg0) {

        NWZC035002PMsg orderDataPMsg0 = arg0.getNWZC035002PMsg();

        // Cash Discount Term Code might not have the value.
        // When the value doesn't exist to compare it accurately,
        // space is set.
        String cashDiscTermCd0;
        if (ZYPCommonFunc.hasValue(orderDataPMsg0.cashDiscTermCd)) {
            cashDiscTermCd0 = orderDataPMsg0.cashDiscTermCd.getValue();
        } else {
            cashDiscTermCd0 = " ";
        }

        // SO Number might not have the value.
        // When the value doesn't exist to compare it accurately,
        // space is set.
        String soNum0;
        if (ZYPCommonFunc.hasValue(orderDataPMsg0.soNum)) {
            soNum0 = orderDataPMsg0.soNum.getValue();
        } else {
            soNum0 = " ";
        }

        // BOL Number might not have the value.
        // When the value doesn't exist to compare it accurately,
        // space is set.
        String bolNum0;
        if (ZYPCommonFunc.hasValue(orderDataPMsg0.bolNum)) {
            bolNum0 = orderDataPMsg0.bolNum.getValue();
        } else {
            bolNum0 = " ";
        }

        // PRO Number might not have the value.
        // When the value doesn't exist to compare it accurately,
        // space is set.
        String proNum0;
        if (ZYPCommonFunc.hasValue(orderDataPMsg0.proNum)) {
            proNum0 = orderDataPMsg0.proNum.getValue();
        } else {
            proNum0 = " ";
        }

//CSA ADD Start
        // add grouping key
        // BILL_TO_CUST_ACCT_CD
        String billToCustAcctCd;
        if (ZYPCommonFunc.hasValue(orderDataPMsg0.billToCustAcctCd)) {
            billToCustAcctCd = orderDataPMsg0.billToCustAcctCd.getValue();
        } else {
            billToCustAcctCd = " ";
        }
        // BILL_TO_CTAC_PSN_FIRST_NM
        String billToCtacFirstNm;
        if (ZYPCommonFunc.hasValue(orderDataPMsg0.billToCtacPsnFirstNm)) {
            billToCtacFirstNm = orderDataPMsg0.billToCtacPsnFirstNm.getValue();
        } else {
            billToCtacFirstNm = " ";
        }
        // BILL_TO_CTAC_PSN_MID_NM
        String billToCtacMidNm;
        if (ZYPCommonFunc.hasValue(orderDataPMsg0.billToCtacPsnMidNm)) {
            billToCtacMidNm = orderDataPMsg0.billToCtacPsnMidNm.getValue();
        } else {
            billToCtacMidNm = " ";
        }
        // BILL_TO_CTAC_PSN_LAST_NM
        String billToCtacLastNm;
        if (ZYPCommonFunc.hasValue(orderDataPMsg0.billToCtacPsnLastNm)) {
            billToCtacLastNm = orderDataPMsg0.billToCtacPsnLastNm.getValue();
        } else {
            billToCtacLastNm = " ";
        }
        // ORIG_INV_NUM
        String origInvNum;
        if (ZYPCommonFunc.hasValue(orderDataPMsg0.origInvNum)) {
            origInvNum = orderDataPMsg0.origInvNum.getValue();
        } else {
            origInvNum = " ";
        }

        // SHIP_FROM_INVTY_LOC_CD
        String shipFromInvtyLocCd;
        if (ZYPCommonFunc.hasValue(orderDataPMsg0.shipFromInvtyLocCd)) {
            shipFromInvtyLocCd = orderDataPMsg0.shipFromInvtyLocCd.getValue();
        } else {
            shipFromInvtyLocCd = " ";
        }

        //optional grouping key
        // SHIP_TO_CUST_ACCT_CD
        String shipToCustAcctCd;
        if (ZYPCommonFunc.hasValue(orderDataPMsg0.shipToCustAcctCd_G)) {
            shipToCustAcctCd = orderDataPMsg0.shipToCustAcctCd_G.getValue();
        } else {
            shipToCustAcctCd = " ";
        }
        // SHIP_TO_CUST_LOC_CD
        String shipToCustLocCd;
        if (ZYPCommonFunc.hasValue(orderDataPMsg0.shipToCustLocCd_G)) {
            shipToCustLocCd = orderDataPMsg0.shipToCustLocCd_G.getValue();
        } else {
            shipToCustLocCd = " ";
        }
        // SHIP_TO_CTAC_PSN_PK
        String shipToCtacPsnPk;
        if (ZYPCommonFunc.hasValue(orderDataPMsg0.ctacPsnPk_G)) {
            shipToCtacPsnPk = orderDataPMsg0.ctacPsnPk_G.getValue().toString();
        } else {
            shipToCtacPsnPk = " ";
        }

//CSA ADD End
        // If it is the same order, Global Company Code, Order Number,
        // Invoice Type Code and Approval Number are not made an object of comparison
        // because it is the same.
//CSA MOD Start
//        StringBuilder strbld0 = new StringBuilder().append(orderDataPMsg0.pmtTermCd.getValue()).append(cashDiscTermCd0).append(orderDataPMsg0.dealCcyCd.getValue()).append(soNum0).append(bolNum0).append(proNum0);
        StringBuilder strbld0 = new StringBuilder().append(orderDataPMsg0.pmtTermCd.getValue())
                                                    .append(cashDiscTermCd0)
                                                    .append(orderDataPMsg0.dealCcyCd.getValue())
                                                    .append(billToCustAcctCd)   //add
                                                    .append(billToCtacFirstNm)  //add
                                                    .append(billToCtacMidNm)    //add
                                                    .append(billToCtacLastNm)   //add
                                                    .append(origInvNum)         //add
                                                    .append(shipToCustAcctCd)   //add opt
                                                    .append(shipToCustLocCd)    //add opt
                                                    .append(shipToCtacPsnPk)    //add opt
                                                    .append(soNum0)
                                                    .append(bolNum0)
                                                    .append(proNum0)
                                                    .append(shipFromInvtyLocCd); //add
//CSA MOD End

        return strbld0.toString();
    }
}
