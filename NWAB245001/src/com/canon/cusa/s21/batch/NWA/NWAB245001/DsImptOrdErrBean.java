/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB245001;

import java.util.ArrayList;
import java.util.List;

import business.db.DS_IMPT_ORD_ERRTMsg;

/**
 * <pre>
 * CUSA Retail Order Import Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/16/2016   CITS            S.Tanikawa      Create          N/A
 * </pre>
 */
public class DsImptOrdErrBean extends DS_IMPT_ORD_ERRTMsg {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * dsImptOrdErrList
     */
    private List<DS_IMPT_ORD_ERRTMsg> dsImptOrdErrList;

    /**
     * DsImptOrdErrBean
     */
    public DsImptOrdErrBean() {
        dsImptOrdErrList = new ArrayList<DS_IMPT_ORD_ERRTMsg>();
    }

    /**
     * getDsImptOrdErrList
     * @return dsImptOrdErrList
     */
    public List<DS_IMPT_ORD_ERRTMsg> getDsImptOrdErrList() {
        return dsImptOrdErrList;
    }
}
