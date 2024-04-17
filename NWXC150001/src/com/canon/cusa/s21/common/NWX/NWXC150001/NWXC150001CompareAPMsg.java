/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001;

import java.io.Serializable;
import java.util.Comparator;

import business.parts.NWZC150001_APMsg;

/**
 * <pre>
 * Compare NWZC150001_APMsg with cpoDtlLineNum_A1 + cpoDtlLineSubNum_A1
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/02   Fujitsu         S.Takami        Create          S21_NA#12637
 * </pre>
 */
public class NWXC150001CompareAPMsg implements Comparator<NWZC150001_APMsg>, Serializable {

    /** Default serial Version */
    private static final long serialVersionUID = 1L;

    /**
     * Compare NWZC150001_APMsg with cpoDtlLineNum_A1 + cpoDtlLineSubNum_A1
     * @param o1 NWZC150001_APMsg
     * @param o2 NWZC150001_APMsg
     * @return compare result
     */
    @Override
    public int compare(NWZC150001_APMsg o1, NWZC150001_APMsg o2) {

        String cpoDtlLineNum1 = o1.cpoDtlLineNum_A1.getValue();
        cpoDtlLineNum1 = cpoDtlLineNum1 + "." + o1.cpoDtlLineSubNum_A1.getValue();

        String cpoDtlLineNum2 = o2.cpoDtlLineNum_A1.getValue();
        cpoDtlLineNum2 = cpoDtlLineNum2 + "." + o2.cpoDtlLineSubNum_A1.getValue();

        return cpoDtlLineNum1.compareTo(cpoDtlLineNum2);
    }
}
