/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001.constant;

/**
 * <pre>
 * DS_CONTR_CTRL_CLS constant class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Tomita        Create          N/A
 * 11/17/2015   Hitachi         A.Kohinata      Update          N/A
 * 01/12/2016   Hitachi         K.Kasai         Update          N/A
 * 05/17/2016   Hitachi         K.Kasai         Update          QC#447
 * </pre>
 */
public interface SVC_COV_FEAT {
    /** Labor Inclusive */
    String LBOR_INCL = "00";

    /** Supplies Inclusive */
    String SPLY_INCL = "01";

    /** Parts Inclusive */
    String PARTS_INCL = "02";

    /** Drums Inclusive */
    String DRUM_INCL = "03";

    /** Expense Inclusive */
    String EXP_INCL = "04";

    /** Bill Code */
    String BILL_CD = "05";

    /** Invoice Display */
    String INV_DISP = "06";

    /** Consolidated Invoice Display */
    String CONSL_INV_DISP = "07";

    /** Coverage Type Description */
    String COV_TP_DESC = "08";

    // START 2016/01/12 K.Kasai [N/A, ADD]
    /** Laser Plus Contract */
    String LASER_PLUS_CONTR = "12";
    // END 2016/01/12 K.Kasai [N/A, ADD]

    // START 2015/11/17 A.Kohinata [N/A, DEL]
    /** MPS */
    String MPS = "09";

    /** OPTIMA Contract */
    String OPTIMA = "10";
    // END 2015/11/17 A.Kohinata [N/A, DEL]
    // START 2016/05/17 K.Kasai [QC#447, ADD]
    /** SLA Time */
    String SLA_TIME = "11";
    // END 2016/05/17 K.Kasai [QC#447, ADD]
}
