/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB109001.constant;

/**
 * <pre>
 * S-Cube Parts Master Info to CINC (WWABF301/311) (IF Data Creation)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/18/2013   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NMAB109001Constant {

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: InterfaceID */
    public static final String MSG_ITEM_INTERFACE_ID = "InterfaceID";

    /** message Item: ProcessCode */
    public static final String MSG_ITEM_PROCESS_CODE = "ProcessCode";

    /** message Item: CommitUnit */
    public static final String MSG_ITEM_COMMIT_UNIT = "Number of Commit Unit";

    /** Process Code ; Monthly(WWABF311) */
    public static final String MONTHLY = "WWABF311";

    /** message id : ZZM9000E Failed to get @ */
    public static final String MSG_ID_ZZM9000E = "ZZM9000E";

    /** message id : ZZM9004E Failed to get @ */
    public static final String MSG_ID_ZZM9004E = "ZZM9004E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** value:INTFC_UPD_IND:Dayly*/
    public static final String VALUE_INTFC_UPD_IND_DAYLY = "0";

    /** value:VALUE_NULL_COMPARE*/
    public static final String VALUE_NULL_COMPARE = "123456789012345678901234567890123456789012345678901";

    /** MAX_COMMIT_NUMBER */
    public static final int MAX_COMMIT_NUMBER = 1000;
}
