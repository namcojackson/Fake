package com.canon.cusa.s21.batch.NSA.NSAB004001;

/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/01/11   Hitachi         S.Moriai        Create          QC#62117
 * </pre>
 */
public class MissingStartMeterMailDetailData {

    /** DS_CONTR_NUM */
    private String contract;

    /** SER_NUM */
    private String serial;

    /** CONTR_VRSN_EFF_FROM_DT */
    private String startDate;

    /**
     * @return dsContrNum
     */
    public String getContract() {
        return contract;
    }

    /**
     * @param dsContrNum String
     */
    public void setContract(String contract) {
        this.contract = contract;
    }

    /**
     * @return serial
     */
    public String getSerial() {
        return serial;
    }

    /**
     * @param serial String
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * @return startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate String
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
