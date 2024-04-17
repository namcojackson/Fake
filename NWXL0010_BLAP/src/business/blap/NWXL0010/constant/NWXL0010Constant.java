/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/14/2010   Fujitsu         K.Tajima        Create          N/A
 * 11/20/2013   Fujitsu         T.Yoshida       Update          DefID:2852
 *</pre>
 */
package business.blap.NWXL0010.constant;

public interface NWXL0010Constant {

    String MY_BIZ_ID = NWXL0010Constant.class.getSimpleName().substring(0, 8);

    String BIZ_LOG_EVENT_ID = "CSV file download";

    String REPORT_ID = "Report ID";

    String REPORT_NAME = "Report Name";

    String REPORT_DESCRIPTION = "Report Description";

    String NOW_TIME_PATTERN= "yyyyMMddhhmmss";
    
    /**
     * Function ID.
     * @author K.Tajima
     */
    enum FuncId {

        MODIFICATION("NWXL0010T010");

        private final String id;

        private FuncId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

    }

    /**
     * Message ID.
     * @author K.Tajima
     */
    enum MessageId {

        /**
         * No search results found.
         */
        NZZM0000E,

        /**
         * There are too many search results, there is data that
         * cannot be displayed.
         */
        NZZM0001W,

        /**
         * This data has been updated by another user.
         */
        NZZM0003E,

        /**
         * Data Duplication Error occurred.
         */
        NWZM0820E,

        /**
         * SQL Statement ignored. [@]
         */
        NWXM0001E,
    }

    /**
     * Report SQL Data Timing.
     * @author K.Tajima
     */
    enum RptSqlDataTmgTxt {

        REAL_TIME("Real Time")

        , DAILY("Daily")

        , WEEKLY("Weekly")

        , MONTHLY("Monthly");
        
        private final String txt;
        
        private RptSqlDataTmgTxt(String txt) {
            this.txt = txt;
        }

        public String getTxt() {
            return txt;
        }
    }

    /**
     * Screen ID.
     * @author K.Tajima
     */
    enum ScrnId {

        NWXL0010Scrn00

        , NWXL0010Scrn01
        
        , NWXL0010Scrn02
    }

}
