/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC070001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * FSR Debrief API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/13/2015   Hitachi         A.Kohinata      Create          N/A
 * 12/16/2015   Hitachi         T.Tomita        Update          CSA QC#978
 * 01/02/2016   Fujitsu         S.Nakai         Update          QC#2750
 * 02/25/2016   Hitachi         T.Iwamoto       Update          CSA QC#3991
 * 06/03/2016   Hitachi         Y.Takeno        Update          CSA QC#3727
 * 08/14/2019   Hitachi         K.Fujimoto      Update          CSA QC#52546
 * 2023/11/30   CITS            R.Kurahashi     Update          QC#62436
 * </pre>
 */
public final class NSZC070001Constant {

    /** Mode: Debrief */
    public static final String MODE_DEBRIEF = "D";

    /** Mode: Charges */
    public static final String MODE_CHARGES = "C";

    /** Mode: Install Check */
    public static final String MODE_INSTALL_CHECK = "I";

    // START 2015/12/16 T.Tomita [QC#978, ADD]
    /** Mode Code : Debrief */
    public static final String MODE_CD_DEBRIEF = "01";

    /** Mode Code : Charges */
    public static final String MODE_CD_CHARGES = "02";
    // END 2015/12/16 T.Tomita [QC#978, ADD]
    /** Mode Code : Install Check Mode */
    public static final String MODE_CD_INSTALL_CHECK = "03";

    /** Format Time stamp **/
    public static final String FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** 60 minute */
    public static final BigDecimal TM_VAL = new BigDecimal(60);

    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NSZM0001E = "NSZM0001E";

    /** Input parameter "Sales Date" is a mandatory field. */
    public static final String NSZM0002E = "NSZM0002E";

    /** Input parameter "Process Mode" is a mandatory field. */
    public static final String NSZM0003E = "NSZM0003E";
    /** Input parameter "Merchandise Code" is a mandatory field. */
    public static final String NSZM0013E = "NSZM0013E";
    /** Input parameter "FSR Number" is a mandatory field. */
    public static final String NSZM0291E = "NSZM0291E";
    /** Input parameter "Service Config Master Pk" is a mandatory field. */
    public static final String NSZM0570E = "NSZM0570E";
    /** Input parameter "Mdse Code" is a mandatory field. */
    public static final String NSZM0842E = "NSZM0842E";
    /** Input parameter "Install Check Verify Flag" is a mandatory field. */
    public static final String NSZM0843E = "NSZM0843E";

    /**
     * Input parameter "Service Labor Full Deal Amount" is a mandatory
     * field.
     */
    public static final String NSZM0061E = "NSZM0061E";

    /**
     * Input parameter "Service Labor Discount Rate" is a mandatory
     * field.
     */
    public static final String NSZM0066E = "NSZM0066E";

    /**
     * Input parameter "Service Labor Deal Discount Amount" is a
     * mandatory field.
     */
    public static final String NSZM0067E = "NSZM0067E";

    /**
     * Input parameter "Service Machine Master PK" is a mandatory
     * field.
     */
    public static final String NSZM0074E = "NSZM0074E";

    /** Input parameter "Service Task Number" is a mandatory field. */
    public static final String NSZM0082E = "NSZM0082E";

    /** Input parameter "FSR#" is a mandatory field. */
    public static final String NSZM0180E = "NSZM0180E";

    /**
     * Input parameter "Service Problem Type Code" is a mandatory
     * field.
     */
    public static final String NSZM0243E = "NSZM0243E";

    /**
     * Input parameter "Service Problem Location Type Code" is a
     * mandatory field.
     */
    public static final String NSZM0244E = "NSZM0244E";

    /**
     * Input parameter "Service Problem Reason Type Code" is a
     * mandatory field.
     */
    public static final String NSZM0245E = "NSZM0245E";

    /**
     * Input parameter "Service Problem Correct Type Code" is a
     * mandatory field.
     */
    public static final String NSZM0246E = "NSZM0246E";

    /** Input parameter "Merchandise Name" is a mandatory field. */
    public static final String NSZM0249E = "NSZM0249E";

    /** Input parameter "Service Parts Quantity" is a mandatory field. */
    public static final String NSZM0250E = "NSZM0250E";

    /**
     * Input parameter "Service Time Event From Date" is a mandatory
     * field.
     */
    public static final String NSZM0273E = "NSZM0273E";

    /**
     * Input parameter "Service Time Event From Time" is a mandatory
     * field.
     */
    public static final String NSZM0274E = "NSZM0274E";

    /**
     * Input parameter "Service Time Event To Date" is a mandatory
     * field.
     */
    public static final String NSZM0275E = "NSZM0275E";

    /**
     * Input parameter "Service Time Event To Time" is a mandatory
     * field.
     */
    public static final String NSZM0276E = "NSZM0276E";

    /** Input parameter "Machine Down Flag" is a mandatory field. */
    public static final String NSZM0278E = "NSZM0278E";

    /** Input parameter "User ID" is a mandatory field. */
    public static final String NSZM0293E = "NSZM0293E";

    /** Input parameter "Service Execute Date" is a mandatory field. */
    public static final String NSZM0739E = "NSZM0739E";

    /**
     * Input parameter "Service Time Event Duration" is a mandatory
     * field.
     */
    public static final String NSZM0761E = "NSZM0761E";

    /** Input parameter "IWR Status Code" is a mandatory field. */
    public static final String NSZM0792E = "NSZM0792E";

    /**
     * Input parameter "FSR Visit Time Event Table Primary Key" is a
     * mandatory field.
     */
    public static final String NSZM0793E = "NSZM0793E";

    /** Input parameter "Labor Item Code" is a mandatory field. */
    public static final String NSZM0794E = "NSZM0794E";

    /**
     * Input parameter "Merchandise Item Billing Type Code" is a
     * mandatory field.
     */
    public static final String NSZM0795E = "NSZM0795E";

    /** Input parameter "Labor Charge Flag" is a mandatory field. */
    public static final String NSZM0796E = "NSZM0796E";

    /**
     * Input parameter "FSR Usage Table Primary Key" is a mandatory
     * field.
     */
    public static final String NSZM0797E = "NSZM0797E";

    /** Input parameter "UOM Code" is a mandatory field. */
    public static final String NSZM0798E = "NSZM0798E";

    /** Input parameter "Parts Charge Flag" is a mandatory field. */
    public static final String NSZM0799E = "NSZM0799E";

    /**
     * Input parameter "FSR Expense Table Primary Key" is a mandatory
     * field.
     */
    public static final String NSZM0800E = "NSZM0800E";

    /** Input parameter "Expense Quantity" is a mandatory field. */
    public static final String NSZM0801E = "NSZM0801E";

    /** Input parameter "Expense Charge Flag" is a mandatory field. */
    public static final String NSZM0802E = "NSZM0802E";

    /** Process Mode is not valid. */
    public static final String NSZM0039E = "NSZM0039E";

    /** Failed to search a Service Task record. */
    public static final String NSZM0079E = "NSZM0079E";

    /** Failed to search a FSR record. */
    public static final String NSZM0181E = "NSZM0181E";

    /** Failed to search a FSR Visit record. */
    public static final String NSZM0182E = "NSZM0182E";

    /** Failed to search a FSR_USG record. */
    public static final String NSZM0345E = "NSZM0345E";

    /** Failed to search a FSR Visit Task record. */
    public static final String NSZM0423E = "NSZM0423E";

    /** Failed to insert the SVC_MEMO. */
    public static final String NSZM0475E = "NSZM0475E";

    /** Failed to search a FSR_EXP record. */
    public static final String NSZM0762E = "NSZM0762E";

    /** Failed to search a Technician Location record. */
    public static final String NSZM0772E = "NSZM0772E";

    /** Failed to search a FSR_VISIT_TM_EVENT record. */
    public static final String NSZM0803E = "NSZM0803E";

    // START 2015/12/16 T.Tomita [QC#978, ADD]
    /** Input parameter "FSR Visit#" is a mandatory field. */
    public static final String NSZM0125E = "NSZM0125E";

    /** Input parameter "UOM Code" is a mandatory field. */
    public static final String NSZM0644E = "NSZM0644E";

    /** Input parameter "Service Invoice Charge Type Code" is a mandatory field. */
    public static final String NSZM0127E = "NSZM0127E";

    /** Input parameter "Service Charge Transaction Type Code" is a mandatory field. */
    public static final String NSZM0807E = "NSZM0807E";

    /** Input parameter "Service Charge Quantity" is a mandatory field. */
    public static final String NSZM0808E = "NSZM0808E";

    /** Input parameter "Service Charge Unit Amount" is a mandatory field. */
    public static final String NSZM0809E = "NSZM0809E";

    /** Input parameter "Service Charge Deal Amount" is a mandatory field. */
    public static final String NSZM0810E = "NSZM0810E";

    /** Input parameter "Service Charge Flag" is a mandatory field. */
    public static final String NSZM0811E = "NSZM0811E";

    /** Input parameter "Price Category Code" is a mandatory field. */
    public static final String NSZM0812E = "NSZM0812E";

    /** Input parameter "Service Charge Unit Amount Of Time" is a mandatory field. */
    public static final String NSZM0813E = "NSZM0813E";
    // END 2015/12/16 T.Tomita [QC#978, ADD]

    /** Input parameter "Service Charge Discount Rate" is a mandatory field. */
    public static final String NSZM0973E = "NSZM0973E";

    // add start 2016/02/25 CSA Defect#3991
    /** Failed to insert a Service Task record. */
    public static final String NSZM0080E = "NSZM0080E";

    /** It failed to insert the FSR. */
    public static final String NSZM0171E = "NSZM0171E";
    // add end 2016/02/25 CSA Defect#3991

    // add start 2016/06/03 CSA Defect#3727
    /** Date Start Position */
    public static final int DATE_START_POS = 0;

    /** Date End Position */
    public static final int DATE_END_POS = 8;

    /** Time Start Position */
    public static final int TIME_START_POS = 8;

    /** Time End Position */
    public static final int TIME_END_POS = 6;
    // add end 2016/06/03 CSA Defect#3727

    // START  02/01/2017 [QC#17291, ADD]
    public static final BigDecimal TM_NUM_MAX_VALUE = BigDecimal.valueOf(9999);
    // END    02/01/2017 [QC#17291, ADD]

    // ADD START 2019/08/14 K.Fujimoto QC#52546
    /** Sub warehouse:Good */
    public static final String PART_USG_INVTY_LOC_SFX = "G";
    // ADD END   2019/08/14 K.Fujimoto QC#52546

    // START 2023/11/30 R.Kurahashi [QC#62436, ADD]
    /** Format Date Time **/
    public static final String FORMAT_Dt = "yyyyMMddHHmmss";
    // END 2023/11/30 R.Kurahashi [QC#62436, ADD]
}
