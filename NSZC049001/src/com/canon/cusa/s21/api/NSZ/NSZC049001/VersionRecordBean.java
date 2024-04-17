package com.canon.cusa.s21.api.NSZ.NSZC049001;

/**
 *<pre>
 * Register to UGW API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/01   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class VersionRecordBean {

    /** csvDblQoutFlg **/
    private String csvDblQoutFlg;

    /** recordName **/
    private String recordName;

    /** version **/
    private String version;

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
     * @return version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version String version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * createLineData
     * @return String
     */
    public String createLineData() {
        return NSZC049001.createRecordData(this.csvDblQoutFlg, this.recordName, this.version);
    }

}
