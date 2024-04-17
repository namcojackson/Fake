/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC501001;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;

/**
 *<pre>
 * Workflow Status update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NFZC501001TokenObject extends S21NwfUtilTokenObj {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** AR_WRT_OFF_RQST.AR_WRT_OFF_RQST_PK */
    private List<NFZC501001TokenObjectLine> lines = new ArrayList<NFZC501001TokenObjectLine>();

    /**
     * add Line
     * @param line NFZC501001TokenObjectLine
     */
    public void addLine(NFZC501001TokenObjectLine line) {
        lines.add(line);
    }

    /**
     * get Lines
     * @return List<NFZC501001TokenObjectLine>
     */
    public List<NFZC501001TokenObjectLine> getLines() {
        return lines;
    }
}
