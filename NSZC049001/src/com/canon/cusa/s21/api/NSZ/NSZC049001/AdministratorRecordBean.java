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
public class AdministratorRecordBean {

    /** csvDblQoutFlg **/
    private String csvDblQoutFlg;

    /** recordName **/
    private String recordName;

    /** administratorName **/
    private String administratorName;

    /** salesOrganizationId **/
    private String salesOrganizationId;

    /** customerId **/
    private String customerId;

    /** administratorEmail **/
    private String administratorEmail;

    /** administratorPhone **/
    private String administratorPhone;

    /** administratorFax **/
    private String administratorFax;

    /** administratorCell **/
    private String administratorCell;

    /** administratorMemo **/
    private String administratorMemo;

    /**
     * @return csvDblQoutFlg
     */
    public String getCsvDblQoutFlg() {
        return csvDblQoutFlg;
    }

    /**
     * @param csvDblQoutFlg String
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
     * @param recordName String
     */
    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    /**
     * @return administratorName
     */
    public String getAdministratorName() {
        return administratorName;
    }

    /**
     * @param administratorName String
     */
    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }

    /**
     * @return salesOrganizationId
     */
    public String getSalesOrganizationId() {
        return salesOrganizationId;
    }

    /**
     * @param salesOrganizationId String
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
     * @param customerId String
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return administratorEmail
     */
    public String getAdministratorEmail() {
        return administratorEmail;
    }

    /**
     * @param administratorEmail String
     */
    public void setAdministratorEmail(String administratorEmail) {
        this.administratorEmail = administratorEmail;
    }

    /**
     * @return administratorPhone
     */
    public String getAdministratorPhone() {
        return administratorPhone;
    }

    /**
     * @param administratorPhone String
     */
    public void setAdministratorPhone(String administratorPhone) {
        this.administratorPhone = administratorPhone;
    }

    /**
     * @return administratorFax
     */
    public String getAdministratorFax() {
        return administratorFax;
    }

    /**
     * @param administratorFax String
     */
    public void setAdministratorFax(String administratorFax) {
        this.administratorFax = administratorFax;
    }

    /**
     * @return administratorCell
     */
    public String getAdministratorCell() {
        return administratorCell;
    }

    /**
     * @param administratorCell String
     */
    public void setAdministratorCell(String administratorCell) {
        this.administratorCell = administratorCell;
    }

    /**
     * @return administratorMemo
     */
    public String getAdministratorMemo() {
        return administratorMemo;
    }

    /**
     * @param administratorMemo String
     */
    public void setAdministratorMemo(String administratorMemo) {
        this.administratorMemo = administratorMemo;
    }

    /**
     * createLineData
     * @return String
     */
    public String createLineData() {
        return NSZC049001.createRecordData(this.csvDblQoutFlg, this.recordName, this.administratorName, this.salesOrganizationId, this.customerId, this.administratorEmail, this.administratorPhone, this.administratorFax,
                this.administratorCell, this.administratorMemo);
    }

}
