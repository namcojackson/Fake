package com.canon.cusa.s21.api.NSZ.NSZC050001;

/**
 *<pre>
 * De-Register from UGW API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/04   Hitachi         Y.Zhang         Create          N/A
 *</pre>
 */
public class DelRdsRecordBean {

    /** csvDblQoutFlg **/
    private String csvDblQoutFlg;

    /** recordName **/
    private String recordName;

    /** rdsId **/
    private String rdsId;

    /**
     * @return csvDblQoutFlg
     */
    public String getCsvDblQoutFlg() {
        return csvDblQoutFlg;
    }

    /**
     * @param csvDblQoutFlg String csvDblQoutFlg
     */
    public void setCsvDblQoutFlg(String csvDblQoutFlg) {
        this.csvDblQoutFlg = csvDblQoutFlg;
    }

    /**
     * @return recordName
     */
    public String getRecordName() {
        return recordName;
    }

    /**
     * @param recordName String recordName
     */
    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    /**
     * @return rdsId
     */
    public String getRdsId() {
        return rdsId;
    }

    /**
     * @param rdsId String rdsId
     */
    public void setRdsId(String rdsId) {
        this.rdsId = rdsId;
    }

    /**
     * createLineData
     * @return String
     */
    public String createLineData() {
        return NSZC050001.createRecordData(this.csvDblQoutFlg, this.recordName, this.rdsId);
    }

}
