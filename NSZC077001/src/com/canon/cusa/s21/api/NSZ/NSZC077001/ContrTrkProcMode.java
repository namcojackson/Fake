/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC077001;


/**
 * <pre>
 * Contract Tracking API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/30/2015   Hitachi         K.Kishimoto     Create          
 * 02/23/2016   Hitachi         K.Kishimoto     Update          QC#3687
 * 05/31/2016   Hitachi         T.Tomita        Update          QC#1523, 4624
 * </pre>
 */
public enum ContrTrkProcMode {

    /**
     * User Operation
     */
    USER_OPERATION("01"),

    /**
     * Auto Approval
     */
    AUTO_APPROVAL("02"),

    /**
     * Contract mode change
     */
    CONTRACT_MODE_CHANGE("03"),

    /**
     * Meter Read Validation
     */
    METER_READ_VALIDATION("04"),

    /**
     * Meter Validation Status Revert
     */
    METER_VALIDATION_STATUS_REVERT("05"),

    // Add Start 02/23/2016 <QC#3687>
    /**
     * Auto Renewal
     */
    AUTO_RENEWAL("06"),

    /**
     * Annual Escalation
     */
    ANNUAL_ESCALATION("07"),

    /**
     * Billing Hold
     */
    BILLING_HOLD("08"),
    // Add End 02/23/2016 <QC#3687>
    // add start 2016/05/31 CSA Defect#1523, 4624
    /**
     * Preview Billing
     */
    PREVIEW_BILLING("09"),

    /**
     * Preview Billing Action
     */
    PREVIEW_BILLING_ACTION("10");
    // add end 2016/05/31 CSA Defect#1523, 4624

    /**
     * String representation of the process mode
     */
    public final String code;

    private ContrTrkProcMode(String code) {
        this.code = code;
    }

    /**
     * Returns all the ContrTrkProcMode codes
     * @return ContrTrkProcMode codes
     */
    public static String[] codes() {

        ContrTrkProcMode[] values = values();
        int length = values.length;

        String[] codes = new String[length];

        for (int i = 0; i < length; i++) {
            codes[i] = values[i].code;
        }

        return codes;
    }

    /**
     * Returns the ContrTrkProcMode of the specified ContrTrkProcMode code
     * @param code ContrTrkProcMode code
     * @return ContrTrkProcMode
     */
    public static ContrTrkProcMode codeOf(String code) {

        for (ContrTrkProcMode mode : values()) {
            if (mode.code.equals(code)) {
                return mode;
            }
        }

        return null;
    }

}
