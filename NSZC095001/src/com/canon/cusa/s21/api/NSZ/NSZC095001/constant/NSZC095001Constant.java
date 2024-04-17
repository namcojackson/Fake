/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC095001.constant;

/**
 * <pre>
 * ONA Meter Reads MRWEBR API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/06   Hitachi         A.Kohinata      Create          N/A
 * 2020/03/03   Fujitsu         S.Kosaka        Update          QC#55962
 * 2020/03/03   Hitachi         A.Kohinata      Update          QC#56123
 * </pre>
 */
public final class NSZC095001Constant {

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "ADB";

    /** Program ID */
    public static final String PROGRAM_ID = "NSZC095001";

    /** Const Key NSZC0950_MTR_READ_SRC_TP */
    public static final String CONST_KEY_NSZC0950_MTR_READ_SRC_TP = "NSZC0950_MTR_READ_SRC_TP";

    /**
     * Input parameter "Service Machine Master PK" is a mandatory
     * field.
     */
    public static final String NSZM0074E = "NSZM0074E";

    /**
     * Input parameter "Reading Meter Count" is a mandatory field.
     */
    public static final String NSZM0266E = "NSZM0266E";

    /**
     * Input parameter "DS Meter Reading Type Code" is a mandatory
     * field.
     */
    public static final String NSZM0356E = "NSZM0356E";

    /**
     * Input parameter "Meter Information" is a mandatory field.
     */
    public static final String NSZM0357E = "NSZM0357E";

    /**
     * Input parameter "DS Meter Reading Type Code" was not found in
     * the DS_MTR_READ_TP.
     */
    public static final String NSZM0359E = "NSZM0359E";

    /**
     * Input parameter "Service Physical Meter Primary Key" is a
     * mandatory field.
     */
    public static final String NSZM0958E = "NSZM0958E";

    // add start 2020/03/03 QC#56123
    /**
     * Current meter reading can not be lower than the previous read.
     * Please select the meter reading type as Adjustment to enter
     * lower reads.
     */
    public static final String NSZM0541E = "NSZM0541E";

    /**
     * Current meter reading can not be higher than the next actual
     * read. Please enter lower reads than the next actual read.
     */
    public static final String NSZM1312E = "NSZM1312E";
    // add end 2020/03/03 QC#56123

    // 2020/03/03 QC#55962 Add Start
    /**
     * Message Type Error.
     */
    public static final String MESSAGE_TYPE_ERROR = "E";

    /**
     * Message Type Warning.
     */
    public static final String MESSAGE_TYPE_WARNING = "W";
    // 2020/03/03 QC#55962 Add End

}
