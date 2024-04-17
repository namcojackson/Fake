/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC503001;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;

/**
 *<pre>
 * Lease Buyout Approve API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFZC503001TokenObject extends S21NwfUtilTokenObj {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** AR_WRT_OFF_RQST.AR_WRT_OFF_RQST_PK */
    private List<NFZC503001TokenObjectLine> lines = new ArrayList<NFZC503001TokenObjectLine>();

    /**
     * add Line
     * @param line NFZC503001TokenObjectLine
     */
    public void addLine(NFZC503001TokenObjectLine line) {
        lines.add(line);
    }

    /**
     * get Lines
     * @return List<NFZC503001TokenObjectLine>
     */
    public List<NFZC503001TokenObjectLine> getLines() {
        return lines;
    }
}
