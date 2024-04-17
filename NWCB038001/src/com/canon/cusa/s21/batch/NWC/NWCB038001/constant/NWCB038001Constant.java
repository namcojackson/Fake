/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB038001.constant;

/**
 * <pre>
 * NWCB038001Constant.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/18   Fujitsu         M.Yamada        Create          QC#18131
 * </pre>
 */
public class NWCB038001Constant {

    /** Message id */
    public static enum MSG_ID {
        /** <pre>Data insert failure. [ TableName = @ , key = @, value = @ ]</pre> */
        ZZMM0001E
        /** <pre>Data update failure.  [ TableName = @ , key = @, value = @ ]</pre> */
        , ZZMM0015E
        /** [@] is mandatory. */
        , ZZZM9025E
    }

    /** num const key. */
    public static enum NUM_CONST_KEY {
        /** NWCB0380_INIT_SETUP_AOT_DAYS */
        NWCB0380_INIT_SETUP_AOT_DAYS
        /** NWCB0380_KEY_STRING_FROM */
        , NWCB0380_KEY_STRING_FROM
        /** NWCB0380_KEY_STRING_TO */
        , NWCB0380_KEY_STRING_TO
        /** NWCB0380_AOT_MINS */
        , NWCB0380_AOT_MINS
    }
}
