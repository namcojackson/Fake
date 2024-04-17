/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC036001.constant;

/**
 * <pre>
 * Solution Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSZC036001Constant {

    /**
     * Message ID
     */
    public static enum MESSAGE_ID {
        /** Input parameter "Global Company Code" is a mandatory field. */
        NSZM0001E,
        /** Input parameter "Search Option In Application ID" is a mandatory field. */
        NSZM0580E,
        /** Input parameter "Update Person Code" is a mandatory field. */
        NSZM0581E,
        /** Input parameter "Service Config Master Pk" is a mandatory field. */
        NSZM0570E,
        /** Input parameter "Delete Flag" is a mandatory field. */
        NSZM0571E,
        /** The Service Machine Master is not exist. Please set a number equal to or greater than 1. */
        NSZM0572E,
        /** Input Parameter "Solution Name" is mandatory field if mode is insert. */
        NSZM0573E,
        /** Input Parameter "Solution Name" is mandatory field if mode is delete. */
        NSZM0574E,
        /** The data cannot be processed since Service Machine Master Status Code["Terminated","Duplicate"] is not valid.[Configuration# : @] */
        NSZM0575E,
        /** The data connot be processed since Service Machine Usage Status Code["In Inventory"] is not valid.[Configuration# : @] */
        NSZM0576E,
        /** This Configuration is already set another Solution#.[Configuration# : @] */
        NSZM0577E,
        /** This Configuration is not exist. [Configuration# : @] */
        NSZM0578E,
        /** Failed to update a Service Config Master record.[Configuration# : @] */
        NSZM0579E
    }
}
