/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001;

import java.io.Serializable;
import java.util.Comparator;

import business.parts.NWZC150001_rtnDtlPMsg;

/**
 * <pre>
 * Compare NWZC150001_rtnDtlPMsg with cpoDtlLineNum_B1 + cpoDtlLineSubNum_B1
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/02   Fujitsu         S.Takami        Create          S21_NA#12637
 * </pre>
 */
public class NWXC150001CompareRtnDtlPMsg implements Comparator<NWZC150001_rtnDtlPMsg>, Serializable {

    /** Default serial Version */
    private static final long serialVersionUID = 1L;

    /**
     * Compare NWZC150001_rtnDtlPMsg with cpoDtlLineNum_B1 + cpoDtlLineSubNum_B1
     * @param o1 NWZC150001_rtnDtlPMsg
     * @param o2 NWZC150001_rtnDtlPMsg
     * @return compare result
     */
    @Override
    public int compare(NWZC150001_rtnDtlPMsg o1, NWZC150001_rtnDtlPMsg o2) {

        String cpoDtlLineNum1 = o1.cpoDtlLineNum_B1.getValue();
        cpoDtlLineNum1 = cpoDtlLineNum1 + "." + o1.cpoDtlLineSubNum_B1.getValue();

        String cpoDtlLineNum2 = o2.cpoDtlLineNum_B1.getValue();
        cpoDtlLineNum2 = cpoDtlLineNum2 + "." + o2.cpoDtlLineSubNum_B1.getValue();

        return cpoDtlLineNum1.compareTo(cpoDtlLineNum2);
    }
}
