/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFDL0130.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public final class NFDL0130Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NFDL0130";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * Message Id : NZZM0000E
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * Message Id : NZZM0001W
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * Message Id : This data has been updated by another user.
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * Message Id : @ has a duplicate of data.
     */
    public static final String NFDM0019E = "NFDM0019E";

    /**
     * Message Id : Cannot delete since related strategy information
     * exist.
     */
    public static final String NFDM0020E = "NFDM0020E";

    /**
     * Message Id : Failed to delete [@].
     */
    public static final String NFDM0021E = "NFDM0021E";

    /**
     * Message Id : Failed to update [@].
     */
    public static final String NFDM0004E = "NFDM0004E";

    /**
     * Message Id : Failed to insert [@].
     */
    public static final String NFDM0013E = "NFDM0013E";

    /**
     * Line Type : Insert
     */
    public static final String XX_TP_CD_INS = "1";

    /**
     * Line Type : Update
     */
    public static final String XX_TP_CD_UPD = "2";

    /**
     * Line Type : Delete
     */
    public static final String XX_TP_CD_DEL = "3";

}
