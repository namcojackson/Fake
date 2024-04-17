/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0620.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 * 2016/02/18   Hitachi         T.Aoyagi        Update          QC3434
 * 2016/04/25   Hitachi         M.Gotou         Update          QC4326
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2017/02/07   Hitachi         K.Kojima        Update          QC#17303
 * 2017/06/16   Hitachi         K.Kojima        Update          QC#19256
 * 2017/08/02   Hitachi         K.Kojima        Update          QC#19906
 * 2017/08/21   Hitachi         E.Kameishi      Update          QC#8661
 * 2017/09/18   Hitachi         K.Kojima        Update          QC#21228
 * 2017/09/19   Hitachi         K.Kojima        Update          QC#21104
 * 2017/09/20   Hitachi         A.Kohinata      Update          QC#18534
 * 2017/10/02   Hitachi         K.Kojima        Update          QC#18417
 * 2017/10/10   Hitachi         K.Kojima        Update          QC#21104-1
 * 2017/11/14   Hitachi         K.Kojima        Update          QC#21886
 * 2017/12/18   Hitachi         K.Kojima        Update          QC#22722
 * 2018/01/16   Hitachi         K.Kojima        Update          QC#21811
 * 2018/02/06   Hitachi         M.Kidokoro      Update          QC#23375
 * 2018/04/11   Hitachi         K.Kojima        Update          QC#22722-4
 * 2018/06/05   Fujitsu         T.Ogura         Update          QC#21159
 * 2018/06/26   Fujitsu         T.Ogura         Update          QC#26336
 * 2018/07/05   Hitachi         A.Kohinata      Update          QC#21545
 * 2018/07/18   CITS            T.Wada          Update          QC#26424
 * 2018/11/05   Hitachi         K.Fujimoto      Update          QC#28627
 * 2019/02/21   Hitachi         K.Kim           Update          QC#30242
 * 2019/05/31   Hitachi         K.Fujimoto      Update          QC#50350
 * 2019/08/30   Hitachi         T.Aoyagi        Update          QC#53005
 * 2020/02/03   CITS            Y.Penequito     Update          QC#58312
 * 2021/03/17   CITS            L.Mandanas      Update          QC#58314-1
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/10/13   Hitachi         M.Komatsu       Update          QC#60069,60078,60537
 * 2023/09/12   Hitachi         S.Nakatani      Update          QC#60074
 *</pre>
 */
public class NSAL0620Constant {

    /** SCRN_ID : NSAL0620Scrn00 */
    public static final String SCRN_ID = "NSAL0620Scrn00";

    /** APL_ID : NSAL0620 */
    public static final String APL_ID = "NSAL0620";

    //START 2017/08/21 E.Kameishi [QC#8661,ADD]
    /** RPT_ID : NSAF0020 */
    public static final String RPT_ID = "NSAF0020";

    /** US_DOLLAR : $ */
    public static final String US_DOLLAR = "$";

    /** RPT_NM_SUFIX : Canon_Maintenance_S_ */
    public static final String RPT_NM_SUFIX = "Canon Maintenance S ";

    /** STR_SEMI_COLON : ; */
    public static final String STR_SEMI_COLON = ";";

    /** STR_DS_CONTR_NUM : ; */
    public static final String STR_DS_CONTR_NUM = "DC.DS_CONTR_NUM";

    /** STR_LIKE : ; */
    public static final String STR_LIKE = "LIKE";

    /** STR_QUOTE : ; */
    public static final String STR_QUOTE = "'";

    /** OTPT_OP_CD : ; */
    public static final String OTPT_OP_CD = "Online";

    /** STR_SPACE :   */
    public static final String STR_SPACE = " ";
    
    /** LINE_LVL_ONE : 1  */
    public static final String LINE_LVL_ONE = "1";
    //END 2017/08/21 E.Kameishi [QC#8661,ADD]

    //START 2021/02/03 Y.Penequito [QC#58312, ADD]
    /** LINE_LVL_ONE : 4  */
    public static final String LINE_LVL_FOUR = "4";

    /** PRINT_BASE : 1  */
    public static final String PRINT_BASE = "1";
    //END 2021/02/03 Y.Penequito [QC#58312, ADD]

    /** DATE_FORMAT_YYYYMMDD : yyyyMMdd */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /** STR_COMMA : , */
    public static final String STR_COMMA = ",";

    /** LIST_MAX_CNT : 99 */
    public static final int LIST_MAX_CNT = 99;

    // START 2017/08/02 K.Kojima [QC#20212,ADD]
    /** LIST_MAX_CNT_BLLG_MTR : 200 */
    public static final int LIST_MAX_CNT_BLLG_MTR = 200;
    // END 2017/08/02 K.Kojima [QC#20212,ADD]

    // START 2018/01/16 K.Kojima [QC#21811,ADD]
    /** LIST_MAX_CNT_BR_LIST : 300 */
    public static final int LIST_MAX_CNT_BR_LIST = 300;
    // END 2018/01/16 K.Kojima [QC#21811,ADD]

    /** THRU_DT_LIMIT */
    public static final String THRU_DT_LIMIT = "29991231";

    /** TBL_NM */
    public static enum TBL_NM {
        /** DS_CONTR_CATG */
        DS_CONTR_CATG
        /** DS_CONTR_CLS */
        , DS_CONTR_CLS
        /** SVC_RG */
        , SVC_RG
        /** SVC_CONTR_BR */
        , SVC_CONTR_BR
        /** SVC_MACH_MSTR_STS */
        , SVC_MACH_MSTR_STS
        /** BLLG_CYCLE */
        , BLLG_CYCLE;
    }

    /** COL_NM */
    public static enum COL_NM {

        /** DS_CONTR_NUM */
        DS_CONTR_NUM
        // START 2018/06/05 T.Ogura [QC#21159,MOD]
//        /** DS_CONTR_CATG_NM */
//        , DS_CONTR_CATG_NM
        /** DS_CONTR_CATG_DESC_TXT */
        , DS_CONTR_CATG_DESC_TXT
        // END   2018/06/05 T.Ogura [QC#21159,MOD]
        /** DS_ACCT_NM */
        , DS_ACCT_NM
        // START 2018/07/17 T.Wada [QC#26424,ADD]
        /** DS_ACCT_NUM */
        , DS_ACCT_NUM
        // END 2018/07/17 T.Wada [QC#26424,ADD]
        // START 2018/11/05 K.Fujimoto [QC#28627,ADD]
        /** CONTR_LINK_NUM **/
        , CONTR_LINK_NUM
        // END 2018/11/05 K.Fujimoto [QC#28627,ADD]
        /** CONTR_VRSN_EFF_FROM_DT */
        , CONTR_VRSN_EFF_FROM_DT
        /** CONTR_VRSN_EFF_THRU_DT */
        , CONTR_VRSN_EFF_THRU_DT
        // START 2018/07/17 T.Wada [QC#26424,ADD]
        /** BILL_TO_CUST_CD */
        , BILL_TO_CUST_CD
        /** BILL_TO_LOC_NM */
        , BILL_TO_LOC_NM
        // END 2018/07/17 T.Wada [QC#26424,ADD]
        /** DS_CONTR_CTRL_STS_NM */
        , DS_CONTR_CTRL_STS_NM
        /** SVC_RG_NM */
        , SVC_RG_NM
        /** SVC_CONTR_BR_CD */
        , SVC_CONTR_BR_CD
        // START 2018/07/17 T.Wada [QC#26424,ADD]
        /** DS_CONTR_STS_DESC_TXT */
        ,DS_CONTR_STS_DESC_TXT
        /** DS_CONTR_CLS_DESC_TXT */
        ,DS_CONTR_CLS_DESC_TXT
        /** BR_PSN_NM */
        ,BR_PSN_NM
        /** TOC_NM */
        ,TOC_NM
        /** SVC_CONTR_REF_CMNT_TXT */
        ,SVC_CONTR_REF_CMNT_TXT
        /** DS_CONTR_RPT_GRP_DESC_TXT */
        ,DS_CONTR_RPT_GRP_DESC_TXT
        /** DS_CONTR_NM */
        ,DS_CONTR_NM
        /** CONTR_INV_CMNT_TXT */
        ,CONTR_INV_CMNT_TXT
        /** DS_BILL_TGTR_FLG */
        ,DS_BILL_TGTR_FLG
        /** PRC_ALLOC_BY_MACH_QTY_FLG */
        ,PRC_ALLOC_BY_MACH_QTY_FLG
        /** MTR_EST_TP_DESC_TXT */
        ,MTR_EST_TP_DESC_TXT
        /** DS_ACCT_NM_LS */
        ,DS_ACCT_NM_LS
        /** BASE_CHRG_TO_LEASE_CMPY_FLG */
        ,BASE_CHRG_TO_LEASE_CMPY_FLG
        /** USG_CHRG_TO_LEASE_CMPY_FLG */
        ,USG_CHRG_TO_LEASE_CMPY_FLG
        /** CFS_LEASE_NUM_CMNT_TXT */
        ,CFS_LEASE_NUM_CMNT_TXT
        /** CUST_PO_NUM */
        ,CUST_PO_NUM
        /** PO_DT */
        ,PO_DT
        /** CR_CARD_CUST_REF_NUM */
        ,CR_CARD_CUST_REF_NUM
        /** CR_CARD_EXPR_YR_MTH */
        ,CR_CARD_EXPR_YR_MTH
        /** PMT_TERM_CASH_DISC_DESC_TXT */
        ,PMT_TERM_CASH_DISC_DESC_TXT
        /** CONTR_AUTO_RNW_TP_DESC_TXT */
        ,CONTR_AUTO_RNW_TP_DESC_TXT
        /** RNW_PRC_METH_DESC_TXT */
        ,RNW_PRC_METH_DESC_TXT
        /** BASE_PRC_UP_RATIO */
        ,BASE_PRC_UP_RATIO
        /** MTR_PRC_UP_RATIO */
        ,MTR_PRC_UP_RATIO
        /** BEF_END_RNW_DAYS_AOT */
        ,BEF_END_RNW_DAYS_AOT
        /** CONTR_UPLFT_TP_DESC_TXT */
        ,CONTR_UPLFT_TP_DESC_TXT
        /** UPLFT_PRC_METH_DESC_TXT */
        ,UPLFT_PRC_METH_DESC_TXT
        /** UPLFT_BASE_PRC_UP_RATIO */
        ,UPLFT_BASE_PRC_UP_RATIO
        /** UPLFT_MTR_PRC_UP_RATIO */
        ,UPLFT_MTR_PRC_UP_RATIO
        /** UPLFT_BEF_END_RNW_DAYS_AOT */
        ,UPLFT_BEF_END_RNW_DAYS_AOT
        // END 2018/07/17 T.Wada [QC#26424,ADD]
        /** SER_NUM */
        , SER_NUM
        /** T_MDL_NM */
        , T_MDL_NM
        /** CONTR_EFF_FROM_DT */
        , CONTR_EFF_FROM_DT
        /** CONTR_EFF_THRU_DT */
        , CONTR_EFF_THRU_DT
        /** SVC_MACH_MSTR_STS_NM */
        , SVC_MACH_MSTR_STS_NM
        /** NEXT_BLLG_DT */
        , NEXT_BLLG_DT
        /** SRCH_OPT_PK */
        , SRCH_OPT_PK
        /** SRCH_OPT_NM */
        , SRCH_OPT_NM
        /** SVC_MACH_MSTR_STS_NM */
//      ,SVC_MACH_MSTR_STS_NM
        /** SVC_MACH_TP_DESC_TXT */
        ,SVC_MACH_TP_DESC_TXT
        /** SVC_MACH_MSTR_PK */
        ,SVC_MACH_MSTR_PK
        /** SVC_CONFIG_MSTR_PK */
        ,SVC_CONFIG_MSTR_PK
        /** MDSE_CD */
        ,MDSE_CD
        /** MDSE_DESC_SHORT_TXT */
        ,MDSE_DESC_SHORT_TXT
        /** SHIP_TO_CUST_CD */
        ,SHIP_TO_CUST_CD
        /** SHIP_TO_LOC_NM */
        ,SHIP_TO_LOC_NM
        /** SHIP_TO_CUST_LOC_ADDR */
        ,SHIP_TO_CUST_LOC_ADDR
        /** SVC_BR_CD_DESC_TXT */
        ,SVC_BR_CD_DESC_TXT
          /** MDSE_DESC_SHORT_TXT */
          ,MDSE_DESC_SHORT_TXT_SA
          /** MTR_READ_METH_DESC_TXT */
          ,MTR_READ_METH_DESC_TXT
          /** CONTR_CLO_DT */
          ,CONTR_CLO_DT
          /** SVC_MEMO_RSN_DESC_TXT */
          ,SVC_MEMO_RSN_DESC_TXT
          /** RNW_EFF_FROM_DT */
          ,RNW_EFF_FROM_DT
          /** CONTR_RNW_TOT_CNT */
          ,CONTR_RNW_TOT_CNT
          /** BLLG_MTR_BILL_TO_CUST_CD */
          ,BLLG_MTR_BILL_TO_CUST_CD
          /** BILL_TO_LOC_NM */
          ,BILL_TO_LOC_NM_BS
          /** DS_CONTR_CTRL_STS_NM_H*/
          ,DS_CONTR_CTRL_STS_NM_H
          /** BILL_TO_CUST_LOC_ADDR */
          ,BILL_TO_CUST_LOC_ADDR
          /** XX_PSN_NM */
          ,BS_PSN_NM
          /** BLLG_CYCLE_DESC_TXT */
          ,BLLG_CYCLE_DESC_TXT
          /** BASE_PRC_DEAL_AMT */
          ,BASE_PRC_DEAL_AMT
          /** BASE_PRC_TERM_DEAL_AMT_RATE */
          ,BASE_PRC_TERM_DEAL_AMT_RATE
          /** BLLG_TMG_TP_DESC_TXT */
          ,BLLG_TMG_TP_DESC_TXT
          /** BASE_DPLY_PER_END_DAY */
          ,CONTR_CLO_DAY
          /** CONTR_BLLG_DAY */
          ,CONTR_BLLG_DAY
          /** DS_INV_TGTR_TP_DESC_TXT */
          ,DS_INV_TGTR_TP_DESC_TXT
          ;
    }

    /** QUERY_PRM_ID */
    public static enum QUERY_PRM_ID {
        /** glblCmpyCd */
        GLBL_CMPY_CD("glblCmpyCd")
        //START 2017/08/21 E.Kameishi [QC#8661,ADD]
        /** dsContrPk */
        , DS_CONTR_PK("dsContrPkList")
        /** dsContrDtlPk */
        , DS_CONTR_DTL_PK("dsContrDtlPkList")
        /** SVC_MACH_TP_CD : MACHINE */
        , SVC_MACH_TP_CD_MACH("svcMachTpCdMach")
        /** SVC_MACH_TP_CD : Accessory */
        , SVC_MACH_TP_CD_ACC("svcMachTpCdAcc")
        /** dsContrDtlTpCd : FLEET */
        , DS_CONTR_DTL_TP_CD_FLT("dsContrDtlTpCdFlt")
        /** dsContrDtlTpCd : AGGREGATE */
        , DS_CONTR_DTL_TP_CD_AGG("dsContrDtlTpCdAgg")
        /** maxThruDt */
        , MAX_THRU_DT("maxThruDt")
        /** bizAppId */
        , BIZ_APP_ID("bizAppId")
        //END 2017/08/21 E.Kameishi [QC#8661,ADD]
        // START 2018/04/11 K.Kojima [QC#22722-4,MOD]
        // /** dsContrNum */
        // , DS_CONTR_NUM("dsContrNum")
        /** dsContrNumEqual */
        , DS_CONTR_NUM_EQUAL("dsContrNumEqual")
        /** dsContrNumLike */
        , DS_CONTR_NUM_LIKE("dsContrNumLike")
        /** dsContrNumLikeOr */
        , DS_CONTR_NUM_LIKE_OR("dsContrNumLikeOr")
        // END 2018/04/11 K.Kojima [QC#22722-4,MOD]
        // START 2018/11/05 K.Fujimoto [QC#28627,ADD]
        /** contrLinkNumEqual */
        , CONTR_LINK_NUM_EQUAL("contrLinkNumEqual")
        /** contrLinkNumLike */
        , CONTR_LINK_NUM_LIKE("contrLinkNumLike")
        /** contrLinkNumLikeOr */
        , CONTR_LINK_NUM_LIKE_OR("contrLinkNumLikeOr")
        // END 2018/11/05 K.Fujimoto [QC#28627,ADD]
        /** dsContrCratDtFrom */
        , DS_CONTR_CRAT_DT_FROM("dsContrCratDtFrom")
        /** dsContrCratDtTo */
        , DS_CONTR_CRAT_DT_TO("dsContrCratDtTo")
        /** dsContrCatgCd */
        , DS_CONTR_CATG_CD("dsContrCatgCd")
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        /** dsContrCatgCdList */
        , DS_CONTR_CATG_CD_LIST("dsContrCatgCdList")
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        // START 2019/02/21 [QC#30242,MOD]
        /** dsContrDtlStsCdSbmt */
        , DS_CONTR_DTL_STS_CD_SBMT("dsContrDtlStsCdSbmt")
        // END 2019/02/21 [QC#30242,MOD]
        // START 2019/02/21 [QC#50350,ADD]
        /** dsContrDtlStsCdActv */
        , DS_CONTR_DTL_STS_CD_ACTV("dsContrDtlStsCdActv")
        // END 2019/02/21 [QC#50350,ADD]
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        /** dsContrDtlStsCdPdil */
        , DS_CONTR_DTL_STS_CD_PDIL("dsContrDtlStsCdPdil")
        /** dsContrDtlStsCdAppo */
        , DS_CONTR_DTL_STS_CD_APPO("dsContrDtlStsCdAppo")
        /** dsContrDtlStsCdArhd */
        , DS_CONTR_DTL_STS_CD_ARHD("dsContrDtlStsCdArhd")
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        /** dsContrCtrlStsCd */
        , DS_CONTR_CTRL_STS_CD("dsContrCtrlStsCd")
        // START 2017/11/14 K.Kojima [QC#21886,ADD]
        /** dsContrCtrlStsCdList */
        , DS_CONTR_CTRL_STS_CD_LIST("dsContrCtrlStsCdList")
        // END 2017/11/14 K.Kojima [QC#21886,ADD]
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        /** dsContrCtrlStsNmList */
        , DS_CONTR_CTRL_STS_NM_LIST("dsContrCtrlStsNmList")
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        /** invSeptBaseUsgFlg */
        , INV_SEPT_BASE_USG_FLG("invSeptBaseUsgFlg")
        // mod start 2016/04/25 CSA Defect#4326
        /** dsContrRptGrpNum */
        , DS_CONTR_RPT_GRP_NUM("dsContrRptGrpNum")
        // mod end 2016/04/25 CSA Defect#4326
        /** svcContrRefCmntTxt */
        , SVC_CONTR_REF_CMNT_TXT("svcContrRefCmntTxt")
        /** dsContrClsCd */
        , DS_CONTR_CLS_CD("dsContrClsCd")
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        /** dsContrClsCdList */
        , DS_CONTR_CLS_CD_LIST("dsContrClsCdList")
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        /** svcContrBrCd */
        , SVC_CONTR_BR_CD("svcContrBrCdList")
        /** billToCustCd */
        , BILL_TO_CUST_CD("billToCustCd")
        // add start 2016/07/01 CSA Defect#11261
//        /** svcPgmMdseCd */
//        , SVC_PGM_MDSE_CD("svcPgmMdseCd")
        /** mdseDescShortTxt */
        , MDSE_DESC_SHORT_TXT("mdseDescShortTxt")
        // add end 2016/07/01 CSA Defect#11261
        /** contrAutoRnwTpCdIsDoNotRnw */
        , CONTR_AUTO_RNW_TP_CD("contrAutoRnwTpCdIsDoNotRnw")
        /** dateType */
        , DATE_TYPE("dateType")
        /** selectType */
        , SELECT_TYPE("selectType")
        /** xxFromDt */
        , CONTR_EFF_FROM_DT("xxFromDt")
        /** xxThruDt */
        , CONTR_EFF_THRU_DT("xxThruDt")
        /** baseBllgCycleCd */
        , BASE_BLLG_CYCLE_CD("baseBllgCycleCd")
        /** mtrBllgCycleCd */
        , MTR_BLLG_CYCLE_CD("mtrBllgCycleCd")
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        /** baseBllgCycleCdList */
        , BASE_BLLG_CYCLE_CD_LIST("baseBllgCycleCdList")
        /** mtrBllgCycleCdList */
        , MTR_BLLG_CYCLE_CD_LIST("mtrBllgCycleCdList")
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        // START 2016/02/18 T.Aoyagi [QC3434, ADD]
        /** dsContrDtlTpCd */
        , DS_CONTR_DTL_TP_CD("dsContrDtlTpCdList")
        // END 2016/02/18 T.Aoyagi [QC3434, ADD]
        /** subContrFlg */
        , SUB_CONTR_FLG("subContrFlg")
        /** subContrFlg */
        , DS_CONTR_DTL_STS_CD("dsContrDtlStsCdList")
        /** svcRgNm */
        , SVC_RG_NM("svcRgNmList")
        /** dsAcctNum */
        , DS_ACCT_NUM("dsAcctNum")
        /** dsAcctNm */
        , DS_ACCT_NM("dsAcctNm")
        /** billToLocNmInclComma */
        , BILL_TO_LOC_NM_INCL_COMMA("billToLocNmInclComma")
        /** billToLocNm */
        , BILL_TO_LOC_NM("billToLocNm")
        /** serNum */
        , SER_NUM("serNum")
        /** svcMachMstrStsCd */
        , SVC_MACH_MSTR_STS_CD("svcMachMstrStsCd")
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        /** svcMachMstrStsCdList */
        , SVC_MACH_MSTR_STS_CD_LIST("svcMachMstrStsCdList")
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        /** svcMachMstrStsCdIsRMV */
        , SVC_MACH_MSTR_STS_CD_IS_RMV("svcMachMstrStsCdIsRMV")
        /** curLocNum */
        , CUR_LOC_NUM("curLocNum")
        // START 2022/10/13 M.Komatsu [QC#60537,ADD]
        /** mdseCd */
        , MDSE_CD("mdseCd")
        // END 2022/10/13 M.Komatsu [QC#60537,ADD]
        /** t_MdlNm */
        , T_MDL_NM("tMdlNm")
        // START 2019/08/30 [QC#53005,ADD]
        /** svcConfigMstrPk */
        , SVC_CONFIG_MSTR_PK("svcConfigMstrPk")
        // END 2019/08/30 [QC#53005,ADD]
        /** locNum */
        , LOC_NUM("locNum")
        /** locNmInclComma */
        , LOC_NM_INCL_COMMA("locNmInclComma")
        /** locNm */
        , LOC_NM("locNm")
        /** slsDt */
        , SLS_DT("slsDt")
        /** mtrReadMethCdList */
        , MTR_READ_METH_CD("mtrReadMethCdList")
        /** mtrLbFlg */
        , MTR_LB_FLG("mtrLbFlg")
        /** mtrLbCdList */
        , MTR_LB_CD("mtrLbCdList")
        /** baseChrgToLeaseCmpyFlg_H */
        , BASE_CHRG_FLG("baseChrgToLeaseCmpyFlg_H")
        /** usgChrgToLeaseCmpyFlg_H */
        , USG_CHRG_FLG("usgChrgToLeaseCmpyFlg_H")
        /** schdFlg */
        , SCHD_FLG("schdFlg")
        // START 2017/12/18 K.Kojima [QC#22722,ADD]
        /** schdFlg2 */
        , SCHD_FLG_NEXT_BLLG_DT("schdFlgNextBllgDt")
        // END 2017/12/18 K.Kojima [QC#22722,ADD]
        /** limitRowNum */
        , LIMIT_ROWNUM("limitRownum")
        /** thruDtLimit */
        , THRU_DT_LIMIT("thruDtLimit")
        /** onlyMyTaskFlg */
        , ONLY_MYTASK_FLG("onlyMyTaskFlg")
        // START 2018/02/06 M.Kidokoro [QC#23375,ADD]
        /** myTeamFlg */
        , MY_TEAM_FLG("myTeamFlg")
        // END 2018/02/06 M.Kidokoro [QC#23375,ADD]
        /** userId */
        , USER_ID("userId")
        /** srchOptUsrId */
        , SRCH_OPT_USR_ID("srchOptUsrId")
        /** srchOptAplId */
        , SRCH_OPT_APL_ID("srchOptAplId")
        // START 2017/06/16 K.Kojima [QC#19256,ADD]
        /** dsContrDtlStsCdOrdr */
        , DS_CONTR_DTL_STS_CD_ORDR("dsContrDtlStsCdOrdr")
        // END 2017/06/16 K.Kojima [QC#19256,ADD]
        // START 2017/09/18 K.Kojima [QC#21228,ADD]
        /** dsContrStsCdOrdr */
        , DS_CONTR_STS_CD_ORDR("dsContrStsCdOrdr")
        // END 2017/09/18 K.Kojima [QC#21228,ADD]
        // START 2017/09/19 K.Kojima [QC#21104,ADD]
        /** invTpCd */
        , INV_TP_CD("invTpCd")
        // END 2017/09/19 K.Kojima [QC#21104,ADD]
        // START 2017/10/10 K.Kojima [QC#21104-1,ADD]
        /** dsBllgSchdStsOpen */
        , DS_BLLG_SCHD_STS_OPEN("dsBllgSchdStsOpen")
        // END 2017/10/10 K.Kojima [QC#21104-1,ADD]
        // START 2017/10/02 K.Kojima [QC#18417,ADD]
        /** activePendingPOSearchFlg */
        , ACTIVE_PENDING_PO_SEARCH_FLG("activePendingPOSearchFlg")
        /** activeRenewalHoldSearchFlg */
        , ACtIVE_RENEWAL_HOLD_SEARCH_FLG("activeRenewalHoldSearchFlg")
        /** activePendingPO */
        , ACTIVE_PENDING_PO("activePendingPO")
        /** activeRenewalHold */
        , ACTIVE_RENEWAL_HOLD("activeRenewalHold")
        /** dsContrDtlStsCdActive */
        , DS_CONTR_CTRL_STS_CD_ACTV("dsContrCtrlStsCdActive")
        /** dsContrCtrlStsCdRenewalHoldForPO */
        , DS_CONTR_CTRL_STS_CD_RNPO("dsContrCtrlStsCdRenewalHoldForPO")
        /** dsContrCtrlStsCdRenewalHold */
        , DS_CONTR_CTRL_STS_CD_RNWH("dsContrCtrlStsCdRenewalHold")
        /** dsContrCatgCdFleet */
        , DS_CONTR_CATG_CD_FLEET("dsContrCatgCdFleet")
        /** dsContrDtlTpCdFleet */
        , DS_CONTR_DTL_TP_CD_FLEET("dsContrDtlTpCdFleet")
        // END 2017/10/02 K.Kojima [QC#18417,ADD]
        // add start 2018/07/05 CSA Defect#21545
        /** contrDtlStsCdListForRnwHld */
        , CONTR_DTL_STS_CD_LIST_FOR_RNW_HLD("contrDtlStsCdListForRnwHld")
        // add end 2018/07/05 CSA Defect#21545
        // START 2018/07/11 T.Wada [QC#26424,ADD]
        , SVC_MEMO_TP_CD_TERM("terminationNote")
        , DS_CONTR_BASE_USG_NM_B("dsContrBaseUsgNmB")
        , DS_CONTR_BASE_USG_NM_U("dsContrBaseUsgNmU")
        , DS_CONTR_MACH_LVL_NUM_1("dsContrMachLvlNum1")
        , DS_CONTR_MACH_LVL_NUM_2("dsContrMachLvlNum2")
        , DS_CONTR_MACH_LVL_NUM_3("dsContrMachLvlNum3")
        , LAST_DAY("lastDay")
        , DS_CONTR_CATG_CD_REG("dsContrCatgCdReg")
        , DS_CONTR_CATG_CD_AGG("dsContrCatgCdAgg")
        // END 2018/07/11 T.Wada [QC#26424,ADD]
        /** flgN */
        , FLG_OFF_N("flgOffN")
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        /** dsContrDtlStsCond */
        , DS_CONTR_DTL_STS_COND("dsContrDtlStsCond")
        /** dsContrDtlStsEtcCond */
        , DS_CONTR_DTL_STS_ETC_COND("dsContrDtlStsEtcCond")
        /** dsContrDtlStsOr1 */
        , DS_CONTR_DTL_STS_OR_1("dsContrDtlStsOr1")
        /** dsContrDtlStsOr2 */
        , DS_CONTR_DTL_STS_OR_2("dsContrDtlStsOr2")
        /** dsContrDtlStsOr3 */
        , DS_CONTR_DTL_STS_OR_3("dsContrDtlStsOr3")
        /** dsContrDtlStsOr4 */
        , DS_CONTR_DTL_STS_OR_4("dsContrDtlStsOr4")
        /** dsContrDtlStsOr5 */
        , DS_CONTR_DTL_STS_OR_5("dsContrDtlStsOr5")
        /** dsContrDtlStsOr6 */
        , DS_CONTR_DTL_STS_OR_6("dsContrDtlStsOr6");
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]

        /** queryPrm */
        private String queryPrm;

        /**
         * Get Query Parameter
         * @return getQueryPrm
         */
        QUERY_PRM_ID(String queryPrm) {
            this.queryPrm = queryPrm;
        }

        /**
         * Set Query Parameter
         * @return getQueryPrm
         */
        public String getQueryPrm() {
            return queryPrm;
        }

    }

    // START 2018/07/11 T.Wada [QC#26424,MOD]
    /** Limit Download RowNumber */
//    public static final int LIMIT_DL_ROWNUM = 2000;
    public static final int LIMIT_DL_ROWNUM = 65000;
    // END 2018/07/11 T.Wada [QC#26424,MOD]

    /** CSV file name */
    public static final String CSV_FILE_NAME = APL_ID;

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** MASS_UPDATE_LIST */
    public static enum MASS_UPDATE_LIST {
        /** Read Method */
        NSAL0710("NSAL0710", "Read Method")
        /** Start Read Capture Popup */
        , NSAL0460("NSAL0460", "Start Read Capture Popup")
        /** Terminate contract/machine */
        , NSAL0400("NSAL0400", "Terminate contract/machine")
        /** Renew contract / machine */
        , NSAL0690("NSAL0690", "Renew contract / machine")
        /** Cancel contract / machine */
        , NSAL0670("NSAL0670", "Cancel contract / machine")
        /** Put Contracts on Hold (at contract level) */
        , NSAL0630("NSAL0630", "Put Contracts on Hold (at contract level)")
        /** Put contract/machine on Billing Hold */
        , NSAL0700("NSAL0700", "Put contract/machine on Billing Hold")
        /** Update Contract Branch */
        , NSAL0640("NSAL0640", "Update Contract Branch")
        /** Update PO # contract / machine level */
        , NSAL0730("NSAL0730", "Update PO # contract / machine level")
        /** Update Credit Card Info contract / machine level */
        , NSAL0390("NSAL0390", "Update Credit Card Info contract / machine level")
        /** Setup Auto-Estimation on contract */
        , NSAL0650("NSAL0650", "Setup Auto-Estimation on contract")
        /** Add Notes on contract */
        , NSAL0660("NSAL0660", "Add Notes on contract")
        /** Invoicing Rules on machine base */
        , NSAL0750("NSAL0750", "Invoicing Rules on machine base")
        /** Update Bill To on contract/machine level */
        , NSAL0720("NSAL0720", "Update Bill To on contract/machine level")
        /** Escalation Rules contract/machine level */
        , NSAL0740("NSAL0740", "Escalation Rules contract/machine level")
        /** Renewal Rules contract/machine level */
        , NSAL0380("NSAL0380", "Renewal Rules contract/machine level")
        // START 2017/02/07 K.Kojima [QC#17303,ADD]
        /** Meter Upload */
        , ZYPL0210("ZYPL0210", "Meter Upload");
        // END 2017/02/07 K.Kojima [QC#17303,ADD]

        /** scrnId */
        private String scrnId;
        /** scrnNm */
        private String scrnNm;

        MASS_UPDATE_LIST(String scrnId, String scrnNm) {
            this.scrnId = scrnId;
            this.scrnNm = scrnNm;
        }

        /**
         * @return scrnId
         */
        public String getScrnId() {
            return scrnId;
        }

        /**
         * @return scrnNm
         */
        public String getScrnNm() {
            return scrnNm;
        }

    }

    /** MSG_ID */
    public enum MSG_ID {

        /** [@] field is mandatory. */
        ZZM9000E
        /** The process [@] has been successfully completed. */
        , ZZZM9003I
        /** Please select at least 1 check box. */
        , NSAM0015E
        /** Specified "@" already exists. */
        , NSAM0059E
        /** Please select only one checkbox. **/
        , NSAM0132E
        /** [@] is not selected. */
        , NSAM0199E
        /** The process has been successfully completed. */
        , NSAM0200I
        /**  Please select Contract Action. */
        , NSAM0366E
        /** [@] cannot be selected. */
        , NSAM0441E
        /** No search results found. */
        , NZZM0000E
        /** There are too many search results, there is data that cannot be displayed. */
        , NZZM0001W
        //START 2017/08/21 E.Kameishi [QC#8661,ADD]
        /** Failed to insert "@". */
        , NSAM0032E
        /** [@] */
        , ZZXM0001E
        /** There is no target for printing the Letter. */
        , NSAM0701E
        //END 2017/08/21 E.Kameishi [QC#8661,ADD]
        // START 2021/02/03 Y.Penequito [QC#58312, MOD]
        /** Failed to update "@". */
        , NSAM0031E
        // END 2021/02/03 Y.Penequito [QC#58312, MOD]
        // START 2021/03/17 L.Mandanas [QC#58314-1, ADD]
        /** Active CumCopy and Allowance coexist.*/
        , NSZM1378E;
        // END 2021/03/17 L.Mandanas [QC#58314-1, ADD]
    }

    /** MSG_KIND */
    public enum MSG_KIND {
        /** error */
        ERROR("E")
        /** warning */
        , WARNING("W")
        /** information */
        , INFO("I")
        //
        , /* */;

        /** */
        private String msgKind;

        MSG_KIND(String msgKind) {
            this.msgKind = msgKind;
        }

        /**
         * @return getMsgKind
         */
        public String getMsgKind() {
            return msgKind;
        }
    }

    /** DATE_TYPE_RADIO_BTN */
    public enum DATE_TYPE {
        /** SCHEDULE_DATE */
        SCHEDULE_DATE(new BigDecimal(1))
        /** INTERFACE_DATE */
        , INTERFACE_DATE(new BigDecimal(2))
        /** MACHINE_EFFECTIVITY */
        , MACHINE_EFFECTIVITY(new BigDecimal(3))
        /** RMA_DATE */
        , RMA_DATE(new BigDecimal(4))
        //
        , /* */;

        /** */
        private BigDecimal dateType;

        DATE_TYPE(BigDecimal dateType) {
            this.dateType = dateType;
        }

        /**
         * @return getMsgKind
         */
        public BigDecimal getDateType() {
            return dateType;
        }
    }

    /** XX_CHK_BOX_A */
    public static final String XX_CHK_BOX_A = "xxChkBox_A";

    // add start 2017/09/20 CSA Defect#18534
    /** ISTL_CALL_TP_CD */
    public static final String ISTL_CALL_TP_CD = "ISTL_CALL_TP_CD";

    /** VAR_CHAR_CONST: PND_ISTL_CONTR_STS_CD */
    public static final String PND_ISTL_CONTR_STS_CD = "PND_ISTL_CONTR_STS_CD";

    /** VAR_CHAR_CONST: PND_ISTL_CONTR_STS_NM */
    public static final String PND_ISTL_CONTR_STS_NM = "PND_ISTL_CONTR_STS_NM";
    // add end 2017/09/20 CSA Defect#18534

    // START 2017/10/02 K.Kojima [QC#18417,ADD]
    /** VAR_CHAR_CONST: ACTV_PENDING_PO_CONTR_STS_CD */
    public static final String ACTV_PENDING_PO_CONTR_STS_CD = "ACTV_PENDING_PO_CONTR_STS_CD";

    /** VAR_CHAR_CONST: ACTV_PENDING_PO_CONTR_STS_NM */
    public static final String ACTV_PENDING_PO_CONTR_STS_NM = "ACTV_PENDING_PO_CONTR_STS_NM";

    /** VAR_CHAR_CONST: ACTV_RENEWAL_HOLD_CONTR_STS_CD */
    public static final String ACTV_RENEWAL_HOLD_CONTR_STS_CD = "ACTV_RENEWAL_HOLD_CONTR_STS_CD";

    /** VAR_CHAR_CONST: ACTV_RENEWAL_HOLD_CONTR_STS_NM */
    public static final String ACTV_RENEWAL_HOLD_CONTR_STS_NM = "ACTV_RENEWAL_HOLD_CONTR_STS_NM";
    // END 2017/10/02 K.Kojima [QC#18417,ADD]

    // START 2022/02/04 K.Kitachi [QC#59684, ADD]
    /** VAR_CHAR_CONST: ALL_PER_TRMN_CONTR_STS_NM */
    public static final String ALL_PER_TRMN_CONTR_STS_NM = "ALL_PER_TRMN_CONTR_STS_NM";
    // END 2022/02/04 K.Kitachi [QC#59684, ADD]

    /** CONTR_LINK_NUM Search Type : Equal */
    public static final String CONTR_LINK_NUM_SRCH_TYPE_EQUAL = "CONTR_LINK_NUM_SRCH_TYPE_EQUAL";

    /** CONTR_LINK_NUM Search Type : Like */
    public static final String CONTR_LINK_NUM_SRCH_TYPE_LIKE = "CONTR_LINK_NUM_SRCH_TYPE_LIKE";

    /** CONTR_LINK_NUM Search Type : Like Or */
    public static final String CONTR_LINK_NUM_SRCH_TYPE_LIKE_OR = "CONTR_LINK_NUM_SRCH_TYPE_LIKE_OR";
    
    // END   2018/11/05 K.Fujimoto [QC#28627,ADD]
    
    // START 2018/04/11 K.Kojima [QC#22722-4,ADD]
    /** DS_CONTR_NUM Search Type : Equal */
    public static final String DS_CONTR_NUM_SRCH_TYPE_EQUAL = "DS_CONTR_NUM_SRCH_TYPE_EQUAL";

    /** DS_CONTR_NUM Search Type : Like */
    public static final String DS_CONTR_NUM_SRCH_TYPE_LIKE = "DS_CONTR_NUM_SRCH_TYPE_LIKE";

    /** DS_CONTR_NUM Search Type : Like Or */
    public static final String DS_CONTR_NUM_SRCH_TYPE_LIKE_OR = "DS_CONTR_NUM_SRCH_TYPE_LIKE_OR";

    /** Percent */
    public static final String PERCENT = "%";
    // END 2018/04/11 K.Kojima [QC#22722-4,ADD]

    // START 2018/06/26 T.Ogura [QC#26336,ADD]
    /** FETCH_SIZE */
    public static final int FETCH_SIZE = 1000;
    // END   2018/06/26 T.Ogura [QC#26336,ADD]

    // add start 2018/07/05 CSA Defect#21545
    /** VAR_CHAR_CONST: CONTR_DTL_STS_CD_FOR_RNW_HLD */
    public static final String CONTR_DTL_STS_CD_FOR_RNW_HLD = "CONTR_DTL_STS_CD_FOR_RNW_HLD";
    // add end 2018/07/05 CSA Defect#21545

    // START 2018/07/11 T.Wada [QC#26424,ADD]
    /** DS_CONTR_BASE_USG_NM:BASE */
    public static final String DS_CONTR_BASE_USG_NM_B = "BASE";

    /** DS_CONTR_BASE_USG_NM:USAGE */
    public static final String DS_CONTR_BASE_USG_NM_U = "OVERAGE";

    /** DS_CONTR_MACH_LVL_NUM:1 */
    public static final String DS_CONTR_MACH_LVL_NUM_1 = "1";

    /** DS_CONTR_MACH_LVL_NUM:2 */
    public static final String DS_CONTR_MACH_LVL_NUM_2 = "2";

    /** DS_CONTR_MACH_LVL_NUM:3 */
    public static final String DS_CONTR_MACH_LVL_NUM_3 = "3";
    
    public static final String LAST_DAY_OF_A_MONTH = "99";
    public static final String LAST_DAY = "Last Day";

    // END 2018/07/11 T.Wada [QC#26424,ADD]

    // START 2021/03/17 L.Mandanas [QC#58314-1, ADD]
    /**
     * SEMIANNUAL : SemiAnnual.
     */
    public static final String SEMIANNUAL = "SemiAnnual";

    /**
     * SEMIANNUALLY : Semiannually.
     */
    public static final String SEMIANNUALLY = "Semiannually";

    // START 2021/04/19 L.Mandanas [QC#58314-1, ADD]
    /**
     * LINE_LVL_TWO : 2.
     */
    public static final String LINE_LVL_TWO = "2";

    /**
     * LINE_LVL_FIVE : 5.
     */
    public static final String LINE_LVL_FIVE = "5";
    // END 2021/04/19 L.Mandanas [QC#58314-1, ADD]
    // END 2021/03/17 L.Mandanas [QC#58314-1, ADD]

    // START 2022/10/13 M.Komatsu [QC#60078,ADD]
    /**
     * NONEXISTENT CODE : XXXX
     */
    public static final String NONEXISTENT_CODE = "XXXX";
    // END 2022/10/13 M.Komatsu [QC#60078,ADD]

    // START 2023/09/12 S.Nakatani [QC#60074,ADD]
    /**
     * DECIMAL_FORMAT_PATTERN : #,##0.00
     */
    public static final String DECIMAL_FORMAT_PATTERN = "#,##0.00";
    // END 2023/09/12 S.Nakatani [QC#60074,ADD]
}
