/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2009   Fujitsu         FXS)Z.Wang      Create          N/A
 * 10/29/2009   Fujitsu         FAP)D.Kato      Update          DefID 0346
 * 07/13/2010   Fujitsu         I.Kondo         Update          DefID 7731 No.198
 * 08/06/2010   Fujitsu         I.Kondo         Update          Merge.
 * 08/10/2010   Fujitsu         I.Kondo         Update          For EXPT_FLG.
 *</pre>
 */
package business.servlet.NFCL5050.constant;

/**
 * NFCL5050Constant interface.
 */
public interface NFCL5050Constant {

    /**
     * "NUMBER_OF_LINES "
     */
    int NUMBER_OF_COLOR_LINES = 3;

    /**
     * "LINE NUMBER " :(1:4 Lines)
     */
    int COLOR_BEGIN_LINES = 5;

    /**
     * "LINE NUMBER " :(1:4 Lines)
     */
    int COLOR_INTERVAL_LINES = 6;

    /** Number of params. */
    int NUM_OF_PARAMS = 14;

    /**
     */
    String SUMMARY_STATUS_Y = "Y";

    /**
     */
    String SUMMARY_STATUS_N = "N";

    /**
     * Parameter numbers enum.
     */
    enum PARAMS {
        /** Parameter 0 */
        NUM_0(0)
        /** Parameter 1 */
        , NUM_1(1)
        /** Parameter 2 */
        , NUM_2(2)
        /** Parameter 3 */
        , NUM_3(3)
        /** Parameter 4 */
        , NUM_4(4)
        /** Parameter 5 */
        , NUM_5(5)
        /** Parameter 6 */
        , NUM_6(6)
        /** Parameter 7 */
        , NUM_7(7)
        /** Parameter 8 */
        , NUM_8(8)
        /** Parameter 9 */
        , NUM_9(9)
        /** Parameter 10 */
        , NUM_10(10)
        /** Parameter 11 */
        , NUM_11(11)
        /** Parameter 12 */
        , NUM_12(12)
        /** Parameter 13 */
        , NUM_13(13);

        /** value */
        private int value;

        PARAMS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
