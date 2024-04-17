///**
// * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
// */
//package com.canon.cusa.s21.batch.NPA.NPAB112001;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
//
///**
// *<pre>
// * NPAB1120:MRP Run Batch
// *
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 08/08/2012   Fujitsu         S.Yoshida       Create          N/A
// *</pre>
// */
//public class NPAB112001ErrBean {
//
//    /** Batch ID */
//    private String batchId = null;
//
//    /** Merchandise Code */
//    private String mdseCd = null;
//
//    /** Inventory Location Code */
//    private String invtyLocCd = null;
//
//    /** Error Date */
//    private String errDate = null;
//
//    /** Error Code */
//    private String errCode = null;
//
//    /**
//     * Constructor
//     * @param batchId Batch ID
//     * @param errDate Error Date
//     */
//    public NPAB112001ErrBean(String batchId, String errDate) {
//        this.batchId = batchId;
//        this.errDate = errDate;
//    }
//
//    /**
//     * Get Batch ID
//     * @return Batch ID
//     */
//    public String getBatchId() {
//        return batchId;
//    }
//
//    /**
//     * Set Batch ID
//     * @param batchId Batch ID
//     */
//    public void setBatchId(String batchId) {
//        setStringValue(this.batchId, batchId);
//    }
//
//    /**
//     * Get Merchandise Code
//     * @return Merchandise Code
//     */
//    public String getMdseCd() {
//        return mdseCd;
//    }
//
//    /**
//     * Set Merchandise Code
//     * @param mdseCd Merchandise Code
//     */
//    public void setMdseCd(String mdseCd) {
//        setStringValue(this.mdseCd, mdseCd);
//    }
//
//    /**
//     * Get Inventory Location Code
//     * @return Inventory Location Code
//     */
//    public String getInvtyLocCd() {
//        return invtyLocCd;
//    }
//
//    /**
//     * Set Inventory Location Code
//     * @param invtyLocCd Inventory Location Code
//     */
//    public void setInvtyLocCd(String invtyLocCd) {
//        setStringValue(this.invtyLocCd, invtyLocCd);
//    }
//
//    /**
//     * Get Error Date
//     * @return Error Date
//     */
//    public String getErrDate() {
//        return errDate;
//    }
//
//    /**
//     * Set Error Date
//     * @param errDate Error Date
//     */
//    public void setErrDate(String errDate) {
//        setStringValue(this.errDate, errDate);
//    }
//
//    /**
//     * Get Error Code
//     * @return Error Code
//     */
//    public String getErrCode() {
//        return errCode;
//    }
//
//    /**
//     * Set Error Code
//     * @param errCode Error Code
//     */
//    public void setErrCode(String errCode) {
//        setStringValue(this.errCode, errCode);
//    }
//
//    /**
//     * Set Error.
//     * @param pErrCode Error Code
//     * @param pMdseCd Merchandise Code
//     * @param pInvtyLocCd Inventory Location Code
//     */
//    public void setErr(String pErrCode, String pMdseCd, String pInvtyLocCd) {
//        this.errCode = pErrCode;
//        this.mdseCd = pMdseCd;
//        this.invtyLocCd = pInvtyLocCd;
//    }
//
//    /**
//     * Is Error
//     * @return true : Error
//     */
//    public boolean isErr() {
//        if (ZYPCommonFunc.hasValue(errCode) && errCode.endsWith("E")) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * Set String Value
//     * @param to Set to
//     * @param from Set from
//     */
//    private void setStringValue(String to, String from) {
//        if (!ZYPCommonFunc.hasValue(from)) {
//            to = new String();
//        } else {
//            to = from;
//        }
//    }
//}
