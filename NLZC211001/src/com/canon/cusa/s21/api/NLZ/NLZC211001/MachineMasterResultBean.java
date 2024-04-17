/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC211001;


/**
 * <pre>
 * Update SO Confirmation
 * Common Logic
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/28/2015   CITS            T.Tokutomi      Create
 * 06/10/2016   CSAI            Y.Imazu         Update          QC#9772
 *</pre>
 */
public class MachineMasterResultBean {

    /** msgId */
    private String msgId;

    /** msgPrmTxt1 */
    private String msgPrmTxt1;

    /** msgPrmTxt2 */
    private String msgPrmTxt2;

    /** assetErrMsgId */
    private String assetErrMsgId;

    /**
     * @return msgId
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * @param msgId String
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /**
     * @return msgPrmTxt1
     */
    public String getMsgPrmTxt1() {
        return msgPrmTxt1;
    }

    /**
     * @param msgPrmTxt1 String
     */
    public void setMsgPrmTxt1(String msgPrmTxt1) {
        this.msgPrmTxt1 = msgPrmTxt1;
    }

    /**
     * @return msgPrmTxt2
     */
    public String getMsgPrmTxt2() {
        return msgPrmTxt2;
    }

    /**
     * @param msgPrmTxt2 String
     */
    public void setMsgPrmTxt2(String msgPrmTxt2) {
        this.msgPrmTxt2 = msgPrmTxt2;
    }

    /**
     * @return assetErrMsgId
     */
    public String getAssetErrMsgId() {
        return assetErrMsgId;
    }

    /**
     * @param assetErrMsgId String
     */
    public void setAssetErrMsgId(String assetErrMsgId) {
        this.assetErrMsgId = assetErrMsgId;
    }
}
