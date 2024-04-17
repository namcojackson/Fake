/**
 * <pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/20/2011   Fujitsu         I.Kondo         Create          N/A
 * </pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC901001;

/**
 * NFCPurgeConst class.
 */
public final class NFCPurgeConst {

    private NFCPurgeConst() {
    }

    /**
     * AR_SEL_FLG enum.
     */
    public enum AR_SEL_FLG {
        /** ENTRY("N") */
        ENTRY("N")
        /** SEARCHED("Y") */
        , SEARCHED("Y");

        /** sFlg */
        private String sFlg;

        private AR_SEL_FLG(String sFlg) {
            this.sFlg = sFlg;
        }

        /**
         * @return String
         */
        public String getValue() {
            return this.sFlg;
        }
    }

    /**
     * SRC_RETEN_NUM_TP_CD enum
     */
    public enum SRC_RETEN_NUM_TP_CD {
        /** YEAR("Y") */
        YEAR("Y")
        /** MONTH("M") */
        , MONTH("M")
        /** DAY("D") */
        , DAY("D");

        /** tpCd */
        private String tpCd;

        private SRC_RETEN_NUM_TP_CD(String tpCd) {
            this.tpCd = tpCd;
        }

        /**
         * @return String
         */
        public String getValue() {
            return this.tpCd;
        }
    }
}
