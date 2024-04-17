/**
 * <Pre>
 * 
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.NFDL0070.constant;

/**
 * <pre>
 * NFDL0070Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/16   Fujitsu         M.Yamada        Create          N/A
 * 2018/07/06   Hitachi         Y.Takeno        Update          QC#26989
 *</pre>
 */
public class NFDL0070Constant {

    /** Biz Application ID : NFDL0070 */
    public static final String BIZ_APP_ID = "NFDL0070";

    /** Number of params. 1 */
    public static final int NUM_OF_PARAMS_I = 1;

    /** Number of params to NFDL0080. 2 */
    // START 2018/07/06 [QC#26989, MOD]
    // public static final int NUM_OF_PARAMS_NFDL0080 = 2;
    public static final int NUM_OF_PARAMS_NFDL0080 = 3;
    // END   2018/07/06 [QC#26989, MOD]

    /**
     * Parameter numbers enum.
     */
    public static enum PARAMS {
        /** Parameter 0 */
        NUM_0(0)
        /** Parameter 1 */
        , NUM_1(1)
        //
        , /* */;

        /** value */
        private int value;

        PARAMS(int value) {
            this.value = value;
        }

        /**
         * getValue
         * @return int.
         */
        public int getValue() {
            return value;
        }
    }

    /** MSG_ID */
    public enum MSG_ID {

        /** An input parameter "@" has not been set. */
        NFDM0001E
        /** Please Select @. */
        , NFDM0002E
        //
        , /* */;

    }

    // START 2016/11/11 J.Kim [QC#15756,ADD]
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";
    // END 2016/11/11 J.Kim [QC#15756,ADD]
}
