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
public class DelDevRecordBean {

    /** csvDblQoutFlg **/
    private String csvDblQoutFlg;

    /** ugwRecNm **/
    private String ugwRecNm;

    /** ugwDvcId **/
    private String ugwDvcId;

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
     * @return ugwRecNm
     */
    public String getUgwRecNm() {
        return ugwRecNm;
    }
    /**
     * @param ugwRecNm String ugwRecNm
     */
    public void setUgwRecNm(String ugwRecNm) {
        this.ugwRecNm = ugwRecNm;
    }

    /**
     * @return ugwDvcId
     */
    public String getUgwDvcId() {
        return ugwDvcId;
    }

    /**
     * @param ugwDvcId String ugwDvcId
     */
    public void setUgwDvcId(String ugwDvcId) {
        this.ugwDvcId = ugwDvcId;
    }

    /**
     * createLineData
     * @return String
     */
    public String createLineData() {
        return NSZC050001.createRecordData(this.csvDblQoutFlg, this.ugwRecNm, this.ugwDvcId);
    }

}
