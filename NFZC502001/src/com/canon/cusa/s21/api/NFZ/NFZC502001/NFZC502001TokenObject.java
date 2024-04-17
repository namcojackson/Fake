/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFZ.NFZC502001;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;

/**
 * <pre>
 * AP Invoice Workflow Status Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CUSA            Y.Aikawa        Create          N/A
 * </pre>
 */
public class NFZC502001TokenObject extends S21NwfUtilTokenObj {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    // Attribute 1
    //   Primary Key

    /** AR_WRT_OFF_RQST.AR_WRT_OFF_RQST_PK */
    private List<NFZC502001TokenObjectLine> lines = new ArrayList<NFZC502001TokenObjectLine>();

    /**
     * add Line
     * @param line NFZC502001TokenObjectLine
     */
    public void addLine(NFZC502001TokenObjectLine line) {
        lines.add(line);
    }

    /**
     * get Lines
     * @return List<NFZC502001TokenObjectLine>
     */
    public List<NFZC502001TokenObjectLine> getLines() {
        return lines;
    }
}
