/*
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC002005;

import java.util.ArrayList;
import java.util.List;

import parts.dbcommon.EZDTBLAccessor;

public class NWXC002005AllocAvailSegDataArray {

    /** NORMAL_RTN */
    public static final String NORMAL_RTN = EZDTBLAccessor.RTNCD_NORMAL;

    /** NOT_FOUND_SEGMENT */
    public static final String NOT_FOUND = "9999";

    /** PARAMETER_ERROR */
    public static final String PARAM_ERROR = "9998";

    private List<NWXC002005AllocAvailSegData> allocAvalSegDataRcdLst;

    private String resultCode;

    public NWXC002005AllocAvailSegDataArray() {
        allocAvalSegDataRcdLst = new ArrayList<NWXC002005AllocAvailSegData>();
        setResultCode(NORMAL_RTN);
    }

    public boolean add(NWXC002005AllocAvailSegData data) {
        return allocAvalSegDataRcdLst.add(data);
    }

    public NWXC002005AllocAvailSegData getData(int index) {
        return allocAvalSegDataRcdLst.get(index);
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public int size() {
        return allocAvalSegDataRcdLst.size();
    }

}
