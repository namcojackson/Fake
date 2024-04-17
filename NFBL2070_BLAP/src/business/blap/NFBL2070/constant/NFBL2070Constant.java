/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2070.constant;

/**
 * <pre>
 * Compensation Credit I/F Error Correction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   CSAI            Miyauchi        Create          N/A
 * 2016/10/11   Hitachi         K.Kojima        Update          QC#12824
 * 2016/11/02   Fujitsu         S.Fujita        Update          QC#15734
 * 2016/11/22   Hitachi         T.Tsuchida      Update          QC#16041
 * 2016/11/28   Fujitsu         T.Murai         Update          QC#16158
 * 2016/12/05   Fujitsu         H.Ikeda         Update          QC#15823
 * </pre>
 */
public interface NFBL2070Constant {

    /**
     * Massage ID MSG_ID
     * @author
     *
     */
    public static enum SCREEN_ID {
        NFBL2070_INIT,
        NFBL2070Scrn00_CMN_Clear,
        NFBL2070Scrn00_Search,
        NFBL2070Scrn00_OnClick_Fix,
        NFBL2070Scrn00_CMN_Submit,
        NFBL2070Scrn00_PagePrev,
        NFBL2070Scrn00_PageNext,
        NFBL2070Scrn00_PageJump,
        NFBL2070Scrn00_CMN_Reset
    }

    // START 2016/11/28 [QC#16158, DEL]
//    /**
//     * MAX Select Row Count
//     */
//    public static int MAX_ROW_CNT = 200;
    // END 2016/11/28 [QC#16158, DEL]

    /**
     * Massage ID MSG_ID
     * @author
     *
     */
    public static enum MSG_ID {
        // START 2016/10/11 K.Kojima [QC#12824,MOD]
        // NFBM0002E,
        ZZZM9001E,
        // END 2016/10/11 K.Kojima [QC#12824,MOD]
        // START 2016/11/02 S.Fujita [QC#15734,MOD]
//        NFBM0085W,
        NZZM0001W,
        // END   2016/11/02 S.Fujita [QC#15734,MOD]
        NFBM0225E,
        NFBM1242E,
        NFBM1173E,
        NFBM1281W,
        NFBM1297E,
        NFBM1285E,
        NFBM1287E,
        NFBM1303W,
        NFBM1304E,
        ZZZM9013E,
        NFBM0005I,
        NFBM0023E
        // START 2016/11/28 [QC#16158, ADD]
        ,NZZM0011E // Please check at least 1 @ checkbox.
        ,NZZM0002I // The process has been successfully completed.
        ,NFBM0073E // Table : @  Insert Error. Return Code = @
        // END 2016/11/28 [QC#16158, ADD]
        // START 2016/12/06 [QC#15823, ADD]
        ,NFZM0027E // This data has been updated by another user.
        // END   2016/12/06 [QC#15823, ADD]
        
    }

    public static enum DTL_COL_LIST {
        XX_CHECK_BOX
       ,RTL_INV_PK
       ,RTL_INV_LINE_PK
       ,RTL_INV_STS_CD
       ,BILL_TO_CUST_CD
       ,SELL_TO_CUST_CD
       ,SER_NUM
       ,MDL_NM
       ,RTL_INV_APVL_DT
       ,BLLG_PER_FROM_DT
       ,BLLG_PER_THRU_DT
       ,RTL_INV_LINE_NUM
       ,RTL_INV_CHRG_TP_DESC_TXT
       ,SHIP_QTY
       ,DEAL_GRS_UNIT_PRC_AMT
       ,SLS_TAX_RATE
       ,BLLG_BIZ_TP_CD
       ,RTL_DIV_CD
       ,RTL_INV_NUM
       // START 2016/11/22 T.Tsuchida [QC#16041,MOD]
       //,RTL_SMRY_INV_NUM
       ,ITRL_RTL_SMRY_INV_NUM
       // END 2016/11/22 T.Tsuchida [QC#16041,MOD]
       ,MDSE_CD
       ,SVC_DLR_CD
       ,INSTL_POST_CD
       ,INSTL_CD
       ,ISTL_SUB_LOC_CD
       ,INV_LINE_CRAT_DT
       ,INV_LINE_MOD_DT
       ,AP_INV_ROSS_STS_CD
    }

//    /** FLAG */
//    public enum FLG {
//        /** Yes */
//        Y,
//        /** No */
//        N
//    }
}