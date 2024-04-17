/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC111001.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * <pre>
 * Contract Inquiry API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   Hitachi         K.Ochiai        Create          N/A
 * 04/25/2017   Hitachi         T.Tomita        Update          RS#8380
 * </pre>
 */
public class NSZC111001Constant {

    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NSZM0001E = "NSZM0001E";

    /** Input parameter "Sales Date" is a mandatory field. */
    public static final String NSZM0002E = "NSZM0002E";

    /** Input parameter "Service Machine Master PK" is a mandatory field. */
    public static final String NSZM0074E = "NSZM0074E";

    /** Input Parameter xxModeCd is not correct. */
    public static final String NSZM0175E = "NSZM0175E";

    /** Input parameter "Mode Code" is a mandatory field. */
    public static final String NSZM0122E = "NSZM0122E";

    /** Input parameter "CPO Order Number" is a mandatory field. */
    public static final String NSZM0402E = "NSZM0402E";

    /** xxModeCd 01 BILL_WITH_EQUIP. */
    public static final String BILL_WITH_EQUIP_MODE = "01";

    /** xxModeCd 02 ASCC. */
    public static final String ASCC_CODE_02 = "02";

    /** Default Bill with Equipment Flag Value */
    public static final String DEF_BILL_WITH_EQUIP_FLG = ZYPConstant.FLG_ON_Y;

    /** Default Bill with Equipment Invoiced Flag Value */
    public static final String DEF_BILL_WITH_EQUIP_INVD_FLG = ZYPConstant.FLG_OFF_N;
    
}
