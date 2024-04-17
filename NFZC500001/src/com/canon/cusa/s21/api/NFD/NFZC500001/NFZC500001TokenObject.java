/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFD.NFZC500001;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;

/**
 *<pre>
 * Write-Off WF Status Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Hitachi         T.Tsuchida      Create          N/A
 * 2020/02/20   Fujitsu         H.Mizukami      Update          QC#55773
 *</pre>
 */
public class NFZC500001TokenObject extends S21NwfUtilTokenObj {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    // Attribute 1
    //   Primary Key

    /** AR_WRT_OFF_RQST.AR_WRT_OFF_RQST_PK */
    private List<NFZC500001TokenObjectLine> lines = new ArrayList<NFZC500001TokenObjectLine>();

    /**
     * add Line
     * @param line NFZC500001TokenObjectLine
     */
    public void addLine(NFZC500001TokenObjectLine line) {
        lines.add(line);
    }

    /**
     * get Lines
     * @return List<NFZC500001TokenObjectLine>
     */
    public List<NFZC500001TokenObjectLine> getLines() {
        return lines;
    }

    /**
     * @param psnNum
     */
    public void setPsnNum(String psnNum) {
        this.psnNum = psnNum;
    }

    /**
     * @return psnNum
     */
    public String getPsnNum() {
        return psnNum;
    }

    /** PSN_NUM */
    private String psnNum;

    // START 2020/02/20 [QC#55773, ADD]
    /** Attribute 6 */
    private String attribute6;

    /** Attribute 6Lbl */
    private String attribute6Lbl;

    /**
     * @return line attribute6
     */
    public String getAttribute6() {
        return attribute6;
    }

    /**
     * @param attribute6
     */
    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    /**
     * @return line attribute6Lbl
     */
    public String getAttribute6Lbl() {
        return attribute6Lbl;
    }

    /**
     * @param attribute6Lbl
     */
    public void setAttribute6Lbl(String attribute6Lbl) {
        this.attribute6Lbl = attribute6Lbl;
    }
    // END  2020/02/20 [QC#55773, ADD]
}
