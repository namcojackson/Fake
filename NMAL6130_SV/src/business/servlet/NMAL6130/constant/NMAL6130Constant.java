/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/23/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.NMAL6130.constant;

public interface NMAL6130Constant {

    /**
     * My Screen ID
     */
    String MY_SCRN_ID = "NMAL6130Scrn00";

    /**
     * Screen Display Mode : Read-Only
     */
    String DISPLAY_MODE_READ_ONLY = "Read-Only";

    /**
     * Screen Display Mode : Upload-Only
     */
    String DISPLAY_MODE_UPLOAD_ONLY = "Upload-Only";

    /**
     * Screen Display Mode : Modification
     */
    String DISPLAY_MODE_MODIFICATION = "Modification";

    /**
     * Button - Attributes : Upload<br> { Name, new Name, Value,
     * Confirm Message ID }
     */
    String[] BTN_UPLOAD = {"Upload", "Upload", "Upload", "" };

    /**
     * Button - Attributes : Select All<br> { Name, new Name, Value,
     * Confirm Message ID }
     */
    String[] BTN_SELECT_ALL = {"SelectAll", "SelectAll", "SelectAll", "" };

    /**
     * Button - Attributes : Un Select All<br> { Name, new Name,
     * Value, Confirm Message ID }
     */
    String[] BTN_UN_SELECT_ALL = {"UnSelectAll", "UnSelectAll", "Un Select All", "" };

    /**
     * Button - Attributes : Delete<br> { Name, new Name, Value,
     * Confirm Message ID }
     */
    String[] BTN_DELETE = {"Delete", "Delete", "Delete", "ZZM8102I" };

    /**
     * Common Button - Attributes : Delete<br> { Name, new Name,
     * Value, Confirm Message ID }
     */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear", "" };

    /**
     * Common Button - Attributes : Close<br> { Name, new Name,
     * Value, Confirm Message ID }
     */
    String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close", "" };

    /**
     * key to get "display mode" from ezd parameters.
     */
    int PARAM_DISPLAY_MODE = 0;

    /**
     * key to get "business application id" from ezd parameters.
     */
    int PARAM_EZBUSINESS_ID = 1;

    /**
     * key to get "attachments grouping text" from ezd parameters.
     */
    int PARAM_ATT_DATA_GRP_TXT = 2;

    /**
     * key to get "attachments limit (This is key of [NUM_CONST]
     * table.)" from ezd parameters.
     */
    int PARAM_ATT_LIMIT_NUM = 3;

    /**
     * key to get "authorize file extentions (This is key of
     * [VAR_CHAR_CONST ] table.)" from ezd parameters.
     */
    int PARAM_AUTHORIZE_FILE_EXT = 4;

    /**
     * key to get "authorize file volume(size) (This is key of
     * [NUM_CONST] table.)" from ezd parameters.
     */
    int PARAM_ATT_DATA_VOL = 5;

    /**
     * key to get "company pk" from ezd parameters.
     */
    int PARAM_CMPY_PK = 6;

    /**
     * key to get "party location pk" from ezd parameters.
     * [NUM_CONST] table.)" from ezd parameters.
     */
    int PARAM_PTY_LOC_PK = 7;    
}
