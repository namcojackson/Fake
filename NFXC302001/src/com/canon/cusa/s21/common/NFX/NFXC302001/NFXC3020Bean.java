/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * NFXC3020Bean.
 * 
 * <pre>
 *
 * Date         Company    Name         Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/17/2009   Canon      M.Moriyama   Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC302001;

/**
 * NFXC3020Bean.
 */
public class NFXC3020Bean {

    /** procCnt. */
    private int procCnt = 0;

    /** rtrnNo. */
    private String rtrnNo = null;

    /**
     * setProcCnt.
     * @param pProcCnt int
     */
    public void setProcCnt(int pProcCnt) {
        procCnt = pProcCnt;
    }

    /**
     * getProcCnt.
     * @return procCnt
     */
    public int getProcCnt() {
        return procCnt;
    }

    /**
     * setRtrnNo.
     * @param pRtrnNo String
     */
    public void setRtrnNo(String pRtrnNo) {
        rtrnNo = pRtrnNo;
    }

    /**
     * getRtrnNo.
     * @return rtrnNo
     */
    public String getRtrnNo() {
        return rtrnNo;
    }
}
