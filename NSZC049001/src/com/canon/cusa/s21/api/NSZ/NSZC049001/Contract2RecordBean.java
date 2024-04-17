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
public class Contract2RecordBean {

    /** csvDblQoutFlg **/
    private String csvDblQoutFlg;

    /** recordName **/
    private String recordName;

    /** contractNumber **/
    private String contractNumber;

    /** salesOrganizationId **/
    private String salesOrganizationId;

    /** customerId **/
    private String customerId;

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
     * @return contractNumber
     */
    public String getContractNumber() {
        return contractNumber;
    }

    /**
     * @param contractNumber String contractNumber
     */
    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    /**
     * @return salesOrganizationId
     */
    public String getSalesOrganizationId() {
        return salesOrganizationId;
    }

    /**
     * @param salesOrganizationId String salesOrganizationId
     */
    public void setSalesOrganizationId(String salesOrganizationId) {
        this.salesOrganizationId = salesOrganizationId;
    }

    /**
     * @return customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId String customerId
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * createLineData
     * @return String
     */
    public String createLineData() {
        return NSZC049001.createRecordData(this.csvDblQoutFlg, this.recordName, this.contractNumber, this.salesOrganizationId, this.customerId);
    }

}
