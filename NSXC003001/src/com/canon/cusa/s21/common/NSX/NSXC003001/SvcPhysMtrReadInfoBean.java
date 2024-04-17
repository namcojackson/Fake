/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC003001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            SRAA            Create          N/A
 * 2022/08/12   Hitachi         N.Takatsu       Update          QC#60186
 *</pre>
 */
public class SvcPhysMtrReadInfoBean {

    /**
     * Service Physical Meter Read Pk
     */
    private BigDecimal svcPhysMtrReadPk;

    /**
     * Service Physical Meter Pk
     */
    private BigDecimal svcPhysMtrPk;

    /**
     * Meter Read Date
     */
    private String mtrReadDt;

    /**
     * Read Meter Count
     */
    private BigDecimal readMtrCnt;
    
    // START 2022/08/12 N.Takatsu[QC#60186, ADD]
    /**
     * Meter Read Comment
     */
    private String mtrReadComment;
    // END 2022/08/12 N.Takatsu[QC#60186, ADD]

    /**
     * Get Service Physical Meter Read Pk
     * @return Service Physical Meter Read Pk
     */
    public BigDecimal getSvcPhysMtrReadPk() {
        return svcPhysMtrReadPk;
    }

    /**
     * Set Service Physical Meter Read Pk
     * @param svcPhysMtrReadPk Service Physical Meter Read Pk
     */
    public void setSvcPhysMtrReadPk(BigDecimal svcPhysMtrReadPk) {
        this.svcPhysMtrReadPk = svcPhysMtrReadPk;
    }

    /**
     * Get Service Physical Meter Pk
     * @return Service Physical Meter Pk
     */
    public BigDecimal getSvcPhysMtrPk() {
        return svcPhysMtrPk;
    }

    /**
     * Set Service Physical Meter Pk
     * @param svcPhysMtrPk Service Physical Meter Pk
     */
    public void setSvcPhysMtrPk(BigDecimal svcPhysMtrPk) {
        this.svcPhysMtrPk = svcPhysMtrPk;
    }

    /**
     * Get Meter Read Date
     * @return Meter Read Date
     */
    public String getMtrReadDt() {
        return mtrReadDt;
    }

    /**
     * Set Meter Read Date
     * @param mtrReadDt Meter Read Date
     */
    public void setMtrReadDt(String mtrReadDt) {
        this.mtrReadDt = mtrReadDt;
    }

    /**
     * Get Read Meter Count
     * @return Read Meter Count
     */
    public BigDecimal getReadMtrCnt() {
        return readMtrCnt;
    }

    /**
     * Set Read Meter Count
     * @param readMtrCnt Read Meter Count
     */
    public void setReadMtrCnt(BigDecimal readMtrCnt) {
        this.readMtrCnt = readMtrCnt;
    }
    
    // START 2022/08/12 N.Takatsu[QC#60186, ADD]
    /**
     * Get Meter Read Comment
     * @return Meter Read Comment
     */
    public String getMtrReadComment() {
        return mtrReadComment;
    }

    /**
     * Set Meter Read Comment
     * @param mtrReadComment Meter Read Comment
     */
    public void setMtrReadComment(String mtrReadComment) {
        this.mtrReadComment = mtrReadComment;
    }
    // END 2022/08/12 N.Takatsu[QC#60186, ADD]
    
    /**
     * String representation of this object.
     * @return String
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new StringBuilder().append("[svcPhysMtrReadPk=").append(svcPhysMtrReadPk).append(", svcPhysMtrPk=").append(svcPhysMtrPk).append(", mtrReadDt=").append(mtrReadDt).append(", readMtrCnt=").append(readMtrCnt).append("]")
                .toString();
    }

    /**
     * @return int
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + h(mtrReadDt);
        result = prime * result + h(readMtrCnt);
        result = prime * result + h(svcPhysMtrPk);
        result = prime * result + h(svcPhysMtrReadPk);
        return result;
    }

    private static int h(Object obj) {
        if (obj == null) {
            return 0;
        } else {
            return obj.hashCode();
        }
    }

    /**
     * @param obj Object
     * @return boolean
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SvcPhysMtrReadInfoBean other = (SvcPhysMtrReadInfoBean) obj;
        if (mtrReadDt == null) {
            if (other.mtrReadDt != null) {
                return false;
            }
        } else if (!mtrReadDt.equals(other.mtrReadDt)) {
            return false;
        }
        if (readMtrCnt == null) {
            if (other.readMtrCnt != null) {
                return false;
            }
        } else if (!readMtrCnt.equals(other.readMtrCnt)) {
            return false;
        }
        if (svcPhysMtrPk == null) {
            if (other.svcPhysMtrPk != null) {
                return false;
            }
        } else if (!svcPhysMtrPk.equals(other.svcPhysMtrPk)) {
            return false;
        }
        if (svcPhysMtrReadPk == null) {
            if (other.svcPhysMtrReadPk != null) {
                return false;
            }
        } else if (!svcPhysMtrReadPk.equals(other.svcPhysMtrReadPk)) {
            return false;
        }
        return true;
    }

}
