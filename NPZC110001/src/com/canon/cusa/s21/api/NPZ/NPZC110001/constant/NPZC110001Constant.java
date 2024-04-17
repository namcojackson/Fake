/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC110001.constant;

/**
 * <pre>
 * Get PO Line From EDI API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2013   Hitachi         K.Kasai         Create          N/A
 * 08/19/2013   Hitachi         K.Kasai         Update          QC1752
 * 09/27/2013   CSAI            K.Lee           Update          MEX-LC001
 * </pre>
 */
public class NPZC110001Constant {

    /** NPZM0001E:Global Company Code is required. */
    public static final String NPZM0001E = "NPZM0001E";

    /** NPZM0018E:PO Number is required. */
    public static final String NPZM0018E = "NPZM0018E";

    /** NPZM0020E:Merchandise Code is required. */
    public static final String NPZM0020E = "NPZM0020E";

    /** NPZM0033E:Sales Date is required. */
    public static final String NPZM0033E = "NPZM0033E";

    /** NPZM0131E: An input parameter, "Detail List" has not been set.*/
    public static final String NPZM0131E = "NPZM0131E";

    /** NPZM0141E: Error occurred in Supersede API.*/
    public static final String NPZM0141E = "NPZM0141E";

    /** NPZM0142E: There are multiple PO Line # associated with the Merchandise Code. PO Line # cannot be uniquely specified.*/
    public static final String NPZM0142E = "NPZM0142E";

    /** NPZM0155E: No PO data found.*/
    public static final String NPZM0155E = "NPZM0155E";

    /** NPZM0156E: No PO Detail data found.*/
    public static final String NPZM0156E = "NPZM0156E";

    /** NPZM0157E: EDI PO Detail Cross Reference data is not consistent.*/
    public static final String NPZM0157E = "NPZM0157E";

    /** NPZM0158E: PO Detail cannot be uniquely identified.*/
    public static final String NPZM0158E = "NPZM0158E";

    /** NPZM0159E: Input Parameter "PO Order Number" or "EDI PO Order Number" must be set.*/
    public static final String NPZM0159E = "NPZM0159E";

    /** NPZM0160E: Merchandise code is not consistent.*/
    public static final String NPZM0160E = "NPZM0160E";

    /** DB item Name : MDSE_CD. */
    public static final String DB_ORD_TAKE_MDSE_CD = "ordTakeMdseCd";

    /** DB : PO_ORD_DTL_LINE_NUM */
    public static final String DB_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** param:glblCmpyCd01 */
    public static final String PARAM_GLBL_CMPY_CD_01 = "glblCmpyCd01";

    /** param:poOrdNum01 */
    public static final String PARAM_PO_ORD_NUM_01 = "poOrdNum01";

    /** param:mdseCd01 */
    public static final String PARAM_MDSE_CD_01 = "mdseCd01";

    /** param:PERCENTAGE */
    public static final String PARAM_PERCENTAGE = "%";

    /** DB : PO_ORD_NUM */
    public static final String DB_PO_ORD_NUM = "PO_ORD_NUM";

}
