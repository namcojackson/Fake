/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2010   Fujitsu         I.Kondo         Create          N/A
 * 11/21/2022   Hitachi			R.Takau			Update			QC#57252	 
 * </pre>
 */
package business.blap.NFCL5140.constant;

/**
 * NFCL5140Constant interface.
 */
public interface NFCL5140Constant {
    /**
     * "glblCmpyCd01"
     */
    String DB_FNAME_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * "arCashAppManEntryTpCd01"
     */
    String DB_FNAME_AR_CASH_APP_MAN_ENTRY_TP_CD1 = "arCashAppManEntryTpCd01";

    /**
     * "arCashAppManEntryTpCd02"
     */
    String DB_FNAME_AR_CASH_APP_MAN_ENTRY_TP_CD2 = "arCashAppManEntryTpCd02";

    /**
     * "arCashAppManEntryTpCd03"
     */
    String DB_FNAME_AR_CASH_APP_MAN_ENTRY_TP_CD3 = "arCashAppManEntryTpCd03";

    /**
     */
    String CONNECT_CHAR = ":";


    /** AR_ADJ_CATG_CD = 'ADJ' */
    String AR_ADJ_CATG_CD_ADJ = "ADJ";
    
    // START 2022/11/11 R.Takau [QC#57252,ADD]
    /**
     */
    String SLC_OTHER = "998";
    
    /** @ is invalid. */
    static final String NFBM0041E = "NFBM0041E";
    /** Invalid @ format. */
    static final String NFCM0833E = "NFCM0833E";
    // END 2022/11/21 R.Takau [QC#57252,ADD]
    
    /**
     * TRX_TP_CD enum.
     */
    enum AR_TRX_TP_CD {
        /** ACC */
        ACC("1"),
        /** DED */
        DED("2"),
        /** ADJ */
        ADJ("3");

        /** value */
        private String value;

        /**
         * @param value String
         */
        private AR_TRX_TP_CD(String value) {
            this.value = value;
        }

        /**
         * @return String
         */
        public String getValue() {
            return value;
        }
    }

}
