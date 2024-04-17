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
public class Customer2RecordBean {

    /** csvDblQoutFlg **/
    private String csvDblQoutFlg;

    /** recordName **/
    private String recordName;

    /** customerId **/
    private String customerId;

    /** salesOrganizationId **/
    private String salesOrganizationId;

    /** customerName **/
    private String customerName;

    /** customerAddress1 **/
    private String customerAddress1;

    /** customerAddress2 **/
    private String customerAddress2;

    /** customerAddress3 **/
    private String customerAddress3;

    /** customerAddress4 **/
    private String customerAddress4;

    /** productionSetting **/
    private String productionSetting;

    /** firmwareDistributionControlFlag **/
    private String firmwareDistributionControlFlag;

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
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName String customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return customerAddress1
     */
    public String getCustomerAddress1() {
        return customerAddress1;
    }

    /**
     * @param customerAddress1 String customerAddress1
     */
    public void setCustomerAddress1(String customerAddress1) {
        this.customerAddress1 = customerAddress1;
    }

    /**
     * @return customerAddress2
     */
    public String getCustomerAddress2() {
        return customerAddress2;
    }

    /**
     * @param customerAddress2 String customerAddress2
     */
    public void setCustomerAddress2(String customerAddress2) {
        this.customerAddress2 = customerAddress2;
    }

    /**
     * @return customerAddress3
     */
    public String getCustomerAddress3() {
        return customerAddress3;
    }

    /**
     * @param customerAddress3 String customerAddress3
     */
    public void setCustomerAddress3(String customerAddress3) {
        this.customerAddress3 = customerAddress3;
    }

    /**
     * @return customerAddress4
     */
    public String getCustomerAddress4() {
        return customerAddress4;
    }

    /**
     * @param customerAddress4 String customerAddress4
     */
    public void setCustomerAddress4(String customerAddress4) {
        this.customerAddress4 = customerAddress4;
    }

    /**
     * @return productionSetting
     */
    public String getProductionSetting() {
        return productionSetting;
    }

    /**
     * @param productionSetting String productionSetting
     */
    public void setProductionSetting(String productionSetting) {
        this.productionSetting = productionSetting;
    }

    /**
     * @return firmwareDistributionControlFlag
     */
    public String getFirmwareDistributionControlFlag() {
        return firmwareDistributionControlFlag;
    }

    /**
     * @param firmwareDistributionControlFlag String firmwareDistributionControlFlag
     */
    public void setFirmwareDistributionControlFlag(String firmwareDistributionControlFlag) {
        this.firmwareDistributionControlFlag = firmwareDistributionControlFlag;
    }

    /**
     * createLineData
     * @return String
     */
    public String createLineData() {
        return NSZC049001.createRecordData(this.csvDblQoutFlg, this.recordName, this.customerId, this.salesOrganizationId, this.customerName, this.customerAddress1, this.customerAddress2, this.customerAddress3,
                this.customerAddress4, this.productionSetting, this.firmwareDistributionControlFlag);
    }

}
