/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC019001;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.SHPG_ORD_MSGTMsg;
import business.db.SHPG_ORD_MSGTMsgArray;
import business.db.SHPG_ORD_RPT_SER_WRKTMsg;
import business.db.SHPG_ORD_RPT_WRKTMsg;
import business.db.SHPG_ORD_RPT_WRKTMsgArray;

import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.common.NLX.NLXC015001.NLXC015001;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_RGTN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PACK_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_ORD_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/24/2009   Fujitsu         M.Irisawa       Create          N/A
 * 10/16/2009   Fujitsu         A.Akabane       Update          760
 * 10/21/2009   Fujitsu         M.Irisawa       Update          867
 * 11/25/2009   Fujitsu         M.Irisawa       Update          RQ0649
 * 07/14/2010   Fujitsu         R.Mori          Update          7680
 * 01/18/2010   CSAI            M.Takahashi     Update  
 * 04/11/2012   Fujitsu         Y.Taoka         Update          Oce#22
 * 2014/05/21   Fujitsu         M.Yamada        Update          CSA#38
 * 12/07/2016   CITS            Y.Fujii         Update          R360
 * 03/07/2017   CITS            K.Fukumura      Update          QC#19077
 * 10/16/2017   CITS            T.Kikuhara      Update          QC#20246(Sol#454)
 * 05/31/2018   CITS            K.Fukumura      Update          QC#25818
 * 09/25/2020   CITS            H.Dimay         Update          QC#57619
 *</pre>
 */
public class NLXSOReport {

    /**
     */
    public static final String RTN_CD_NORMAL = "0";

    /**
     */
    public static final String RTN_CD_ERROR = "1";

    /**
     */
    private static final String NLXM1005E = "NLXM1005E";

    /**
     */
    private static final String NLXM1006E = "NLXM1006E";

    /**
     */
    private static final String NLXM1017E = "NLXM1017E";

    /**
     */
    private static final String NLXM1013E = "NLXM1013E";

    /**
     */
    private static final String NLXM1014E = "NLXM1014E";

    /**
     */
    private static final String NLXM1015E = "NLXM1015E";

    /**
     */
    private static final String NLXM1016E = "NLXM1016E";

    /**
     */
    private static final String NLXM1020E = "NLXM1020E";

    /** "NLBF0010_LINE_NUM_OF_1ST_PAGE" does not exist in "NUMBER_CONST". */
    private static final String NLXM1037E = "NLXM1037E";

    /** "NLBF0010_LINE_NUM_OF_2ND_PAGE" does not exist in "NUMBER_CONST". */
    private static final String NLXM1038E = "NLXM1038E";

    /** "NLBF0010_PURGE_DT" does not exist in "NUMBER_CONST". */
    private static final String NLXM1039E = "NLXM1039E";

    /** "NLBF0010_NUM_OF_DESC_LTR" does not exist in "NUMBER_CONST". */
    private static final String NLXM1040E = "NLXM1040E";

    /** Failed to get RTL_WH. */
    private static final String NLXM1041E = "NLXM1041E";

    /** Failed to get SO_RPT_MAP. */
    private static final String NLXM1042E = "NLXM1042E";

    /** Failed to get PRCH_REQ. */
    private static final String NLXM1043E = "NLXM1043E";

    /** "NLBF0010_P_LINE_NUM_1ST_PAGE" does not exist in "NUMBER_CONST". */
    private static final String NLXM1044E = "NLXM1044E";

    /** "NLBF0010_P_LINE_NUM_2ND_PAGE" does not exist in "NUMBER_CONST". */
    private static final String NLXM1045E = "NLXM1045E";

    /** It failed to delete the work data for Packing Slip List Report. */
    private static final String NLXM1046E = "NLXM1046E";

    
    /**
     */
    private static final String AZZM0000E = "NZZM0000E";

    /**
     * SO_MSG_DESC_TXT
     */
    private static final String SO_MSG_DESC_TXT = "SO_MSG_DESC_TXT";

    /**
     * SO_NUM
     */
    private static final String SO_NUM = "SO_NUM";

    /**
     * GLBL_CMPY_NM
     */
    private static final String GLBL_CMPY_NM = "GLBL_CMPY_NM";

    /**
     * FIRST_LINE_ADDR
     */
    private static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /**
     * SCD_LINE_ADDR
     */
    private static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /**
     * THIRD_LINE_ADDR
     */
    private static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /**
     * FRTH_LINE_ADDR
     */
    private static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /**
     * CTY_ADDR
     */
    private static final String CTY_ADDR = "CTY_ADDR";

    /**
     * ST_CD
     */
    private static final String ST_CD = "ST_CD";

    /**
     * POST_CD
     */
    private static final String POST_CD = "POST_CD";

    /**
     * RT_CUST_LINE_LOC_NM_01
     */
    private static final String RT_CUST_LINE_LOC_NM_01 = "RT_CUST_LINE_LOC_NM_01";

    /**
     * RT_CUST_LINE_LOC_NM_02
     */
    private static final String RT_CUST_LINE_LOC_NM_02 = "RT_CUST_LINE_LOC_NM_02";

    /**
     * RT_CUST_LINE_ADDR_01
     */
    private static final String RT_CUST_LINE_ADDR_01 = "RT_CUST_LINE_ADDR_01";

    /**
     * RT_CUST_LINE_ADDR_02
     */
    private static final String RT_CUST_LINE_ADDR_02 = "RT_CUST_LINE_ADDR_02";

    /**
     * RT_CUST_LINE_ADDR_03
     */
    private static final String RT_CUST_LINE_ADDR_03 = "RT_CUST_LINE_ADDR_03";

    /**
     * RT_CUST_LINE_ADDR_04
     */
    private static final String RT_CUST_LINE_ADDR_04 = "RT_CUST_LINE_ADDR_04";

    /**
     * RT_CTY_ADDR
     */
    private static final String RT_CTY_ADDR = "RT_CTY_ADDR";

    /**
     * RT_ST_CD
     */
    private static final String RT_ST_CD = "RT_ST_CD";

    /**
     * RT_POST_CD
     */
    private static final String RT_POST_CD = "RT_POST_CD";

    /**
     * TRX_HDR_NUM
     */
    private static final String TRX_HDR_NUM = "TRX_HDR_NUM";

    /**
     * SL_CUST_LINE_LOC_NM_01
     */
    private static final String SL_CUST_LINE_LOC_NM_01 = "SL_CUST_LINE_LOC_NM_01";

    /**
     * SL_CUST_LINE_LOC_NM_02
     */
    private static final String SL_CUST_LINE_LOC_NM_02 = "SL_CUST_LINE_LOC_NM_02";

    /**
     * SL_CUST_LINE_ADDR_01
     */
    private static final String SL_CUST_LINE_ADDR_01 = "SL_CUST_LINE_ADDR_01";

    /**
     * SL_CUST_LINE_ADDR_02
     */
    private static final String SL_CUST_LINE_ADDR_02 = "SL_CUST_LINE_ADDR_02";

    /**
     * SL_CUST_LINE_ADDR_03
     */
    private static final String SL_CUST_LINE_ADDR_03 = "SL_CUST_LINE_ADDR_03";

    /**
     * SL_CUST_LINE_ADDR_04
     */
    private static final String SL_CUST_LINE_ADDR_04 = "SL_CUST_LINE_ADDR_04";

    /**
     * SL_CTY_ADDR
     */
    private static final String SL_CTY_ADDR = "SL_CTY_ADDR";

    /**
     * SL_ST_CD
     */
    private static final String SL_ST_CD = "SL_ST_CD";

    /**
     * SL_POST_CD
     */
    private static final String SL_POST_CD = "SL_POST_CD";

    /**
     * SP_CUST_LINE_LOC_NM_01
     */
    private static final String SP_CUST_LINE_LOC_NM_01 = "SP_CUST_LINE_LOC_NM_01";

    /**
     * SP_CUST_LINE_LOC_NM_02
     */
    private static final String SP_CUST_LINE_LOC_NM_02 = "SP_CUST_LINE_LOC_NM_02";

    /**
     * SP_CTAC_PTNR_PSN_NM
     */
    private static final String SP_CTAC_PTNR_PSN_NM = "SP_CTAC_PTNR_PSN_NM";

    /**
     * SP_CUST_LINE_ADDR_01
     */
    private static final String SP_CUST_LINE_ADDR_01 = "SP_CUST_LINE_ADDR_01";

    /**
     * SP_CUST_LINE_ADDR_02
     */
    private static final String SP_CUST_LINE_ADDR_02 = "SP_CUST_LINE_ADDR_02";

    /**
     * SP_CUST_LINE_ADDR_03
     */
    private static final String SP_CUST_LINE_ADDR_03 = "SP_CUST_LINE_ADDR_03";

    /**
     * SP_CUST_LINE_ADDR_04
     */
    private static final String SP_CUST_LINE_ADDR_04 = "SP_CUST_LINE_ADDR_04";

    /**
     * SP_CTY_ADDR
     */
    private static final String SP_CTY_ADDR = "SP_CTY_ADDR";

    /**
     * SP_ST_CD
     */
    private static final String SP_ST_CD = "SP_ST_CD";

    /**
     * SP_POST_CD
     */
    private static final String SP_POST_CD = "SP_POST_CD";

    /**
     * WH_CD
     */
    private static final String WH_CD = "WH_CD";

    /**
     * FRT_CHRG_TO_CD
     */
    private static final String FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";

    /**
     * FRT_CHRG_TO_NM
     */
    private static final String FRT_CHRG_TO_NM = "FRT_CHRG_TO_NM";

    /**
     * FRT_CHRG_METH_CD
     */
    private static final String FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";

    /**
     * FRT_CHRG_METH_NM
     */
    private static final String FRT_CHRG_METH_NM = "FRT_CHRG_METH_NM";

    /**
     * CARR_ACCT_NUM
     */
    private static final String CARR_ACCT_NUM = "CARR_ACCT_NUM";

    /**
     * S80_SHPG_TERM_CD
     */
    private static final String S80_SHPG_TERM_CD = "S80_SHPG_TERM_CD";

    /**
     * S80_SHPG_TERM_NM
     */
    private static final String S80_SHPG_TERM_NM = "S80_SHPG_TERM_NM";

    /**
     * SHPG_SVC_LVL_CD
     */
    private static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /**
     * SHPG_SVC_LVL_NM
     */
    private static final String SHPG_SVC_LVL_NM = "SHPG_SVC_LVL_NM";

    /**
     * SHPG_MODE_CD
     */
    private static final String SHPG_MODE_CD = "SHPG_MODE_CD";

    /**
     * SHPG_MODE_NM
     */
    private static final String SHPG_MODE_NM = "SHPG_MODE_NM";

    /**
     * SHPG_METH_CD
     */
    private static final String SHPG_METH_CD = "SHPG_METH_CD";

    /**
     * SHPG_METH_NM
     */
    private static final String SHPG_METH_NM = "SHPG_METH_NM";

    /**
     * SCE_ORD_TP_CD
     */
    private static final String SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /**
     * SCE_ORD_TP_NM
     */
    private static final String SCE_ORD_TP_NM = "SCE_ORD_TP_NM";

    /**
     * RQST_ORD_QTY_TXT
     */
    private static final String RQST_ORD_QTY = "RQST_ORD_QTY";

    /**
     * SHPG_QTY
     */
    private static final String SHPG_QTY = "SHPG_QTY";

    /**
     * FROM_STK_STS_CD
     */
    private static final String FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /**
     * TO_STK_STS_CD
     */
    private static final String TO_STK_STS_CD = "TO_STK_STS_CD";

    /**
     * STK_STS_NM
     */
    private static final String FROM_STK_STS_NM = "FROM_STK_STS_NM";

    /**
     * TO_STK_STS_NM
     */
    private static final String TO_STK_STS_NM = "TO_STK_STS_NM";

    /**
     * MDSE_CD
     */
    private static final String MDSE_CD = "MDSE_CD";

    /**
     * CUST_MDSE_CD
     */
    private static final String CUST_MDSE_CD = "CUST_MDSE_CD";

    /**
     * MDSE_NM
     */
    private static final String MDSE_NM = "MDSE_NM";

    /**
     * UPC_CD
     */
    private static final String UPC_CD = "UPC_CD";

    /**
     * SER_NUM_TAKE_FLG
     */
    private static final String SER_NUM_TAKE_FLG = "SER_NUM_TAKE_FLG";

    /**
     * SET_MDSE_CD
     */
    private static final String SET_MDSE_CD = "SET_MDSE_CD";

    /**
     * SET_MDSE_NM
     */
    private static final String SET_MDSE_NM = "SET_MDSE_NM";

    /**
     * PSD_DT
     */
    private static final String PSD_DT = "PSD_DT";

    /**
     * PDD_DT
     */
    private static final String PDD_DT = "PDD_DT";

    /**
     * RSD_DT
     */
    private static final String RSD_DT = "RSD_DT";

    /**
     * RDD_DT
     */
    private static final String RDD_DT = "RDD_DT";

    /**
     * CUST_ISS_PO_NUM
     */
    private static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /**
     * RTE_GRP_NUM
     */
    private static final String RTE_GRP_NUM = "RTE_GRP_NUM";

    // 2014/05/23 Del CSA#38 Start
    //    /**
    //     * TOT_SHIP_AMT
    //     */
    //    private static final String TOT_SHIP_AMT = "TOT_SHIP_AMT";
    //
    //    /**
    //     * STD_CCY_CD
    //     */
    //    private static final String STD_CCY_CD = "STD_CCY_CD";
    // 2014/05/23 Del CSA#38 End

    /**
     * DNLD_TM_TS
     */
    private static final String DNLD_TM_TS = "DNLD_TM_TS";

    /**
     * BIN_NUM
     */
    private static final String BIN_NUM = "BIN_NUM";

    // 04/11/2012 #22 START
    /**
     * SO_CONFIG_FLG
     */
    private static final String SO_CONFIG_FLG = "SO_CONFIG_FLG";

    /**
     * CONFIG_ITEM_FLG
     */
    private static final String CONFIG_ITEM_FLG = "CONFIG_ITEM_FLG";

    // 04/11/2012 #22 END

    // 2014/05/21 Add CSA#38 START
    /** TOT_SHPG_WT_PRINT_TXT */
    private static final String TOT_SHPG_WT = "TOT_SHPG_WT";

    /** SO_PRINT_SHIP_WT_UNIT_TXT */
    private static final String SO_PRINT_SHIP_WT_UNIT_TXT = "SO_PRINT_SHIP_WT_UNIT_TXT";

    /** SO_SLP_NUM */
    private static final String SO_SLP_NUM = "SO_SLP_NUM";

    /** SER_NUM */
    private static final String SER_NUM = "SER_NUM";

    /** MDSE_RGTN_TP_CD */
    private static final String MDSE_RGTN_TP_CD = "MDSE_RGTN_TP_CD";

    //    /** DATE_FMT_SEPARATOR */
    //    private static final String DATE_FMT_SEPARATOR = "/";

    // 2014/05/21 Add CSA#38 END

    /**
     */
    private static final int SYSDT_DT_FROM = 0;

    /**
     */
    private static final int SYSDT_DT_TO = 8;

    //    /**
    //     */
    //    private static final int DT_YEAR_FROM = 0;
    //
    //    /**
    //     */
    //    private static final int DT_YEAR_TO = 4;
    //
    //    /**
    //     */
    //    private static final int DT_MONTH_TO = 6;
    //
    //    /**
    //     */
    //    private static final int DT_DAY_TO = 8;

    /**     */
    private static final int NUM0 = 0;

    /**     */
    private static final int NUM1 = 1;

    /**     */
    private static final int NUM2 = 2;

    /**     */
    private static final int NUM3 = 3;

    /**     */
    private static final int NUM4 = 4;

    /**     */
    private static final int NUM5 = 5;

    /**     */
    private static final int NUM6 = 6;

    /**     */
    private static final int NUM7 = 7;

    /**     */
    private static final int NUM8 = 8;

    /**     */
    private static final int NUM9 = 9;

    /**     */
    private static final int NUM10 = 10;

    /**     */
    private static final int NUM12 = 12;

    // 2014/05/23 Del CSA#38 Start
    //    /**
    //     */
    //    private static final int LN_SMALL_TACT = 2;
    // 2014/05/23 Del CSA#38 End

    // =================================================
    // Const
    // =================================================
    /** ONE_BLANK . */
    public static final String ONE_BLANK = " ";

    /** COMMA. */
    public static final String COMMA = ",";

    /** PHONE. */
    public static final String PHONE = "Phone:";

    /** LINE_NUM_SEPARATOR. */
    public static final String LINE_NUM_SEPARATOR = ".";

    /** DS_COND_CONST_GRP_ID. */
    public static final String DS_COND_CONST_GRP_ID = "NLZC2110_SHIP_CTRL";

    /** BIZ_APP_ID. */
    public static final String BIZ_APP_ID = "NLBL2020";

    /** SO_MSG_TP_CD. */
    public static final String SO_MSG_TP_CD = "S";

    /** DATE_BEGININDEXWO. */
    public static final int DATE_BEGININDEX = 0;

    /** DATE_ENDINDEX. */
    public static final int DATE_ENDINDEX = 8;

    /** SCALE_ZERO. */
    public static final int SCALE_ZERO = 0;

    /** SCALE_WEIGHT. */
    public static final int SCALE_WEIGHT = 4;

    /** SCALE_SEPARATOR. */
    public static final String SCALE_SEPARATOR = ".";

    /** Date Format */
    public static final String DATE_TIME_FORMAT_14 = "yyyyMMddHHmmss";

    // =================================================
    // Var Char / Number Const Name
    // =================================================
    /** Number Const Name : NLBF0010_PURGE_DT */
    public static final String CONST_NLBF0010_PURGE_DT = "NLBF0010_PURGE_DT";

    /** Number Const Name : NLBF0010_LINE_NUM_OF_1ST_PAGE */
    public static final String CONST_NLBF0010_LINE_NUM_OF_1ST_PAGE = "NLBF0010_LINE_NUM_OF_1ST_PAGE";

    /** Number Const Name : NLBF0010_LINE_NUM_OF_2ND_PAGE */
    public static final String CONST_NLBF0010_LINE_NUM_OF_2ND_PAGE = "NLBF0010_LINE_NUM_OF_2ND_PAGE";

    /** Number Const Name : NLBF0010_P_LINE_NUM_1ST_PAGE */
    public static final String CONST_NLBF0010_P_LINE_NUM_1ST_PAGE = "NLBF0010_P_LINE_NUM_1ST_PAGE";

    /** Number Const Name : NLBF0010_P_LINE_NUM_2ND_PAGE */
    public static final String CONST_NLBF0010_P_LINE_NUM_2ND_PAGE = "NLBF0010_P_LINE_NUM_2ND_PAGE";

    /** Number Const Name : NLBF0010_NUM_OF_DESC_LTR */
    public static final String CONST_NLBF0010_NUM_OF_DESC_LTR = "NLBF0010_NUM_OF_DESC_LTR";

    /** Var Char Const Name : NLBF0010_REPRINT */
    public static final String CONST_NLBF0010_REPRINT = "NLBF0010_REPRINT";


    // =================================================
    // DB_PARAM Const
    // =================================================
    /** DB_PARAM_GLBL_CMPY_CD : glblCmpyCd */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DB_PARAM_BILL_TO_CUST_CD : billToCustCd */
    public static final String DB_PARAM_BILL_TO_CUST_CD = "billToCustCd";

    /** DB_PARAM_SHIP_TO_CUST_CD : shipToCustCd */
    public static final String DB_PARAM_SHIP_TO_CUST_CD = "shipToCustCd";

    /** DB_PARAM_SO_NUM : soNum */
    public static final String DB_PARAM_SO_NUM = "soNum";

    /** DB_PARAM_SO_CUST_DATA_TP_CD : soCustDataTpCd */
    public static final String DB_PARAM_SO_CUST_DATA_TP_CD = "soCustDataTpCd";

    /** DB_PARAM_SO_SLP_NUM : soSlpNum */
    public static final String DB_PARAM_SO_SLP_NUM = "soSlpNum";

    /** DB_PARAM_MDSE_CD : mdseCd */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    /** DB_PARAM_WH_PICK_FLG : whPickFlg */
    public static final String DB_PARAM_WH_PICK_FLG = "whPickFlg";

    /** DB_PARAM_ORD_ASG_FLG : ordAsgFlg */
    public static final String DB_PARAM_ORD_ASG_FLG = "ordAsgFlg";

    /** DB_PARAM_PROC_DT : procDt */
    public static final String DB_PARAM_PROC_DT = "procDt";

    /** DB_PARAM_RTL_WH_CD : rtlWhCd */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /** DB_PARAM_TEL_NUM : telNum */
    public static final String DB_PARAM_TEL_NUM = "telNum";

    /** DB_PARAM_LINE_NUM_SEPARATOR : lineNumSeparator */
    public static final String DB_PARAM_LINE_NUM_SEPARATOR = "lineNumSeparator";

    /** DB_PARAM_INBD_OTBD_CD : inbdOtbdCd */
    public static final String DB_PARAM_INBD_OTBD_CD = "inbdOtbdCd";

    /** DB_PARAM_DS_COND_CONST_GRP_ID : dsCondConstGrpId */
    public static final String DB_PARAM_DS_COND_CONST_GRP_ID = "dsCondConstGrpId";

    /** DB_PARAM_BIZ_APP_ID : bizAppId */
    public static final String DB_PARAM_BIZ_APP_ID = "bizAppId";

    /** DB_PARAM_SO_MSG_TP_CD : soMsgTpCd */
    public static final String DB_PARAM_SO_MSG_TP_CD = "soMsgTpCd";

    /** DB_PARAM_DS_SO_LINE_STS_CD_01 : dsSoLineStsCd01 */
    public static final String DB_PARAM_DS_SO_LINE_STS_CD_01 = "dsSoLineStsCd01";

    /** DB_PARAM_DS_SO_LINE_STS_CD_02 : dsSoLineStsCd02 */
    public static final String DB_PARAM_DS_SO_LINE_STS_CD_02 = "dsSoLineStsCd02";

    /** DB_PARAM_SO_PROC_STS_CD : soProcStsCd */
    public static final String DB_PARAM_SO_PROC_STS_CD = "soProcStsCd";

    /** DB_PARAM_SO_PRINT_FLG : soPrintFlg */
    public static final String DB_PARAM_SO_PRINT_FLG = "soPrintFlg";

    /** DB_PARAM_SHIP_FLG : shipFlg */
    public static final String DB_PARAM_SHIP_FLG = "shipFlg";

    /** DB_PARAM_SO_LINE_OPEN_FLG : soLineOpenFlg */
    public static final String DB_PARAM_SO_LINE_OPEN_FLG = "soLineOpenFlg";

    /** DB_PARAM_FLG_Y : flgY */
    public static final String DB_PARAM_FLG_Y = "flgY";

    /** DB_PARAM_FLG_N : flgN */
    public static final String DB_PARAM_FLG_N = "flgN";

    /** DB_PARAM_SCE_ORD_TP_CD : sceOrdTpCd */
    public static final String DB_PARAM_SCE_ORD_TP_CD = "sceOrdTpCd";

    /** DB_PARAM_RTL_WH_CATG_CD : rtlWhCatgCd */
    public static final String DB_PARAM_RTL_WH_CATG_CD = "rtlWhCatgCd";

    /** DB_PARAM_PRCH_REQ_NUM : prchReqNum */
    public static final String DB_PARAM_PRCH_REQ_NUM = "prchReqNum";

    /** DB_PARAM_PRNT_PURGE_TS : purgeTs */
    public static final String DB_PARAM_PRNT_PURGE_TS = "purgeTs";


    // =================================================
    // DB_COLUMN Const
    // =================================================
    /** DB Column Name: SO_NUM. */
    public static final String DB_COLUMN_SO_NUM = "SO_NUM";

    /** DB Column Name: SO_CUST_DATA_TP_CD. */
    public static final String DB_COLUMN_SO_CUST_DATA_TP_CD = "SO_CUST_DATA_TP_CD";

    /** DB Column Name: DS_ACCT_NM. */
    public static final String DB_COLUMN_DS_ACCT_NM = "DS_ACCT_NM";

    /** DB Column Name: SO_CUST_LINE_LOC_NM_01. */
    public static final String DB_COLUMN_SO_CUST_LINE_LOC_NM_01 = "SO_CUST_LINE_LOC_NM_01";

    /** DB Column Name: SO_CUST_LINE_LOC_NM_02. */
    public static final String DB_COLUMN_SO_CUST_LINE_LOC_NM_02 = "SO_CUST_LINE_LOC_NM_02";

    /** DB Column Name: SO_CUST_LINE_ADDR_01. */
    public static final String DB_COLUMN_SO_CUST_LINE_ADDR_01 = "SO_CUST_LINE_ADDR_01";

    /** DB Column Name: SO_CUST_LINE_ADDR_02. */
    public static final String DB_COLUMN_SO_CUST_LINE_ADDR_02 = "SO_CUST_LINE_ADDR_02";

    /** DB Column Name: SO_CUST_LINE_ADDR_03. */
    public static final String DB_COLUMN_SO_CUST_LINE_ADDR_03 = "SO_CUST_LINE_ADDR_03";

    /** DB Column Name: SO_CUST_LINE_ADDR_04. */
    public static final String DB_COLUMN_SO_CUST_LINE_ADDR_04 = "SO_CUST_LINE_ADDR_04";

    //QC#20246 UPD START
    /** DB Column Name: CTAC_PTNR_PSN_NM. */
    public static final String DB_COLUMN_CTAC_PTNR_PSN_NM = "CTAC_PTNR_PSN_NM";
    //QC#20246 UPD END

    /** DB Column Name: CTY_ADDR. */
    public static final String DB_COLUMN_CTY_ADDR = "CTY_ADDR";

    /** DB Column Name: ST_CD. */
    public static final String DB_COLUMN_ST_CD = "ST_CD";

    /** DB Column Name: POST_CD. */
    public static final String DB_COLUMN_POST_CD = "POST_CD";

    /** DB Column Name: SER_NUM. */
    public static final String DB_COLUMN_SER_NUM = "SER_NUM";

    /** DB Column Name: FIRST_LINE_ADDR. */
    public static final String DB_COLUMN_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** DB Column Name: SCD_LINE_ADDR. */
    public static final String DB_COLUMN_SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /** DB Column Name: THIRD_LINE_ADDR. */
    public static final String DB_COLUMN_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /** DB Column Name: FRTH_LINE_ADDR. */
    public static final String DB_COLUMN_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /** DB Column Name: TEL_NUM. */
    public static final String DB_COLUMN_TEL_NUM = "TEL_NUM";

    /** DB Column Name: SHIP_TO_CUST_CD. */
    public static final String DB_COLUMN_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** DB Column Name: BILL_TO_CUST_CD. */
    public static final String DB_COLUMN_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB Column Name: WH_CD. */
    public static final String DB_COLUMN_WH_CD = "WH_CD";

    /** DB Column Name: SHIP_DT_TM_TS. */
    public static final String DB_COLUMN_SHIP_DT_TM_TS = "SHIP_DT_TM_TS";

    /** DB Column Name: CUST_ISS_PO_NUM. */
    public static final String DB_COLUMN_CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** DB Column Name: CPO_ORD_NUM. */
    public static final String DB_COLUMN_CPO_ORD_NUM = "CPO_ORD_NUM";

    /** DB Column Name: TOC_NM. */
    public static final String DB_COLUMN_TOC_NM = "TOC_NM";

    /** DB Column Name: SCE_ORD_TP_NM. */
    public static final String DB_COLUMN_SCE_ORD_TP_NM = "SCE_ORD_TP_NM";

    /** DB Column Name: TXT_SQ_NUM. */
    public static final String DB_COLUMN_TXT_SQ_NUM = "TXT_SQ_NUM";

    /** DB Column Name: SO_PRT_FLG. */
    public static final String DB_COLUMN_SO_PRT_FLG = "SO_PRT_FLG";

    /** DB Column Name: SO_MSG_DESC_TXT. */
    public static final String DB_COLUMN_SO_MSG_DESC_TXT = "SO_MSG_DESC_TXT";

    /** DB Column Name: MDSE_CD. */
    public static final String DB_COLUMN_MDSE_CD = "MDSE_CD";

    /** DB Column Name: MDSE_DESC_SHORT_TXT. */
    public static final String DB_COLUMN_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** DB Column Name: SHPG_QTY. */
    public static final String DB_COLUMN_SHPG_QTY = "SHPG_QTY";

    /** DB Column Name: SHIP_QTY. */
    public static final String DB_COLUMN_SHIP_QTY = "SHIP_QTY";

    /** DB Column Name: TOT_SHPG_WT. */
    public static final String DB_COLUMN_TOT_SHPG_WT = "TOT_SHPG_WT";

    /** DB Column Name: SO_SLP_NUM. */
    public static final String DB_COLUMN_SO_SLP_NUM = "SO_SLP_NUM";

    /** DB Column Name: PRCH_REQ_NUM. */
    public static final String DB_COLUMN_PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** DB Column Name: PRCH_REQ_TP_DESC_TXT. */
    public static final String DB_COLUMN_PRCH_REQ_TP_DESC_TXT = "PRCH_REQ_TP_DESC_TXT";

    /** DB Column Name: FULL_PSN_NM. */
    public static final String DB_COLUMN_FULL_PSN_NM = "FULL_PSN_NM";

    /** DB Column Name: PACK_LIST_TP_CD. */
    public static final String DB_COLUMN_PACK_LIST_TP_CD = "PACK_LIST_TP_CD";

    /** DB Column Name: SCE_ORD_TP_CD. */
    public static final String DB_COLUMN_SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** DB Column Name: RTL_WH_CATG_CD. */
    public static final String DB_COLUMN_RTL_WH_CATG_CD = "RTL_WH_CATG_CD";

    /** DB Column Name: SER_NUM_TAKE_FLG. */
    public static final String DB_COLUMN_SER_NUM_TAKE_FLG = "SER_NUM_TAKE_FLG";

    /** DB Column Name: DS_SO_LINE_STS_CD. */
    public static final String DB_COLUMN_DS_SO_LINE_STS_CD = "DS_SO_LINE_STS_CD";

    /** DB Column Name: SHPG_ORD_RPT_WRK_PK. */
    public static final String DB_COLUMN_SHPG_ORD_RPT_WRK_PK = "SHPG_ORD_RPT_WRK_PK";


    /**
     * GLBL_CMPY_CD
     */
    private String glblCmpyCdM;

    /**
     * USR_ID
     */
    private String usrIdM;

    /**
     * PROC_START_TS
     */
    private String procStartTsM;

    /** soNum. */
    private String soNumM;

    /**
     */
    private S21SsmLowLevelCodingClient ssmLowLev = null;

    /** SSM Client. */
    private S21SsmBatchClient ssmBatchClient = null;

    /** returnMsgIdList. */
    private List<String> returnMsgIdList;

    /** procErrorFlg. */
    private boolean procErrorFlg;

    /** report work table purge date. */
    private BigDecimal purgeDt;

    /** Line Number of 1st Page. */
    private BigDecimal lineNumOf1stPage;

    /** Line Number of 2nd Page. */
    private BigDecimal lineNumOf2ndPage;

    /** Parts Line Number of 1st Page. */
    private BigDecimal partsLineNumOf1stPage;

    /** Parts Line Number of 2nd Page. */
    private BigDecimal partsLineNumOf2ndPage;

    /** Description Maximum number of characters. */
    private BigDecimal numOfDescLtr;

    /** page number. */
    private BigDecimal pageNum = BigDecimal.ONE;

    /** line number. */
    private BigDecimal pageLineNum = BigDecimal.ZERO;

    /** reprint char string. */
    private String reprintCharString;

    /** total order qty. */
    private BigDecimal totOrdQty;

    /** total ship qty. */
    private BigDecimal totShipQty;

    /** total back order qty. */
    private BigDecimal totBoQty;

    /** total ship Wt. */
    private BigDecimal totShpgWt;

    /**
     * @param glblCmpyCd String
     * @param usrId String
     * @param procStartTs String
     * @param soNumList List<String>
     * @param xxMsgIdList List<String>
     * @return RTN_CD String
     * @throws SQLException SQLException
     */
    public String setSoRptWrk(String glblCmpyCd, String usrId, String procStartTs, List<String> soNumList, List<String> xxMsgIdList) throws SQLException {
        ssmLowLev = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        checkParam(glblCmpyCd, usrId, procStartTs, soNumList, xxMsgIdList);

        if (NLXC015001.hasValue(xxMsgIdList)) {
            return RTN_CD_ERROR;
        }

        this.glblCmpyCdM = glblCmpyCd;
        this.usrIdM = usrId;
        this.procStartTsM = procStartTs;

        SHPG_ORD_RPT_WRKTMsgArray resultEditData = selectSo(soNumList, xxMsgIdList);

        if (resultEditData != null) {
            registSoData(resultEditData, xxMsgIdList);
        }

        if (NLXC015001.hasValue(xxMsgIdList)) {
            for (String xxMsgId : xxMsgIdList) {
                if (xxMsgId.endsWith("E")) {
                    return RTN_CD_ERROR;
                }
            }
        }
        return RTN_CD_NORMAL;
    }

    /**
     */
    private void checkParam(String glblCmpyCd, String usrId, String procStartTs, List<String> soNumList, List<String> xxMsgIdList) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1005E), this);
            xxMsgIdList.add(NLXM1005E);
            return;
        }

        if (!ZYPCommonFunc.hasValue(usrId)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1005E), this);
            xxMsgIdList.add(NLXM1005E);
            return;
        }

        if (!ZYPCommonFunc.hasValue(procStartTs)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1005E), this);
            xxMsgIdList.add(NLXM1005E);
            return;
        }

        if (!NLXC015001.hasValue(soNumList)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1006E), this);
            xxMsgIdList.add(NLXM1006E);
            return;
        }
    }

    /**
     * @throws SQLException SQLException
     */
    private SHPG_ORD_RPT_WRKTMsgArray selectSo(List<String> soNumList, List<String> xxMsgIdList) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCdM);
        paramMap.put("soNumList", soNumList);
        paramMap.put("retTpCd", SO_CUST_DATA_TP.RETURN_TO);
        paramMap.put("selTpCd", SO_CUST_DATA_TP.SELL_TO);
        paramMap.put("shpTpCd", SO_CUST_DATA_TP.SHIP_TO);
        paramMap.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
        SHPG_ORD_RPT_WRKTMsgArray resultEditData;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = ssmLowLev.createPreparedStatement("selectSo", paramMap);
            resultSet = pstmt.executeQuery();

            if (!resultSet.next()) {
                EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(AZZM0000E), this);
                xxMsgIdList.add(AZZM0000E);
            }

            if (NLXC015001.hasValue(xxMsgIdList)) {
                return null;
            }

            Map<String, SHPG_ORD_MSGTMsgArray> resultMsgMap = selectSoMessageBulk(soNumList);

            resultEditData = editSoData(resultSet, resultMsgMap, xxMsgIdList);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(pstmt, resultSet);
        }
        return resultEditData;
    }

    /**
     * 2014/05/21 Add CSA#38 Select the SO_SER data
     * @param selectSoresultSet SHPG_ORD_RPT_WRKTMsg
     * @param itemLineNum
     * @param List<String> xxMsgIdList
     * @throws SQLException SQLException
     */
    private List<SHPG_ORD_RPT_SER_WRKTMsg> selectSoSer(ResultSet selectSoresultSet, String itemLineNum, List<String> xxMsgIdList) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCdM);
        paramMap.put("soNum", selectSoresultSet.getString(SO_NUM));
        paramMap.put("soSlpNum", selectSoresultSet.getString(SO_SLP_NUM));

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        List<SHPG_ORD_RPT_SER_WRKTMsg> resultEditData;
        try {
            pstmt = ssmLowLev.createPreparedStatement("selectSoSer", paramMap);
            resultSet = pstmt.executeQuery();
            resultEditData = editSoSerData(resultSet, selectSoresultSet, itemLineNum, xxMsgIdList);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(pstmt, resultSet);
        }
        return resultEditData;
    }

    /**
     * @throws SQLException
     */
    // private Map<String, SHPG_ORD_MSGTMsgArray>
    // selectSoMessage(List<String> soNumList) throws SQLException {
    // Map<String, SHPG_ORD_MSGTMsgArray> resultMsgMap = new
    // HashMap<String, SHPG_ORD_MSGTMsgArray>();
    // for (String soNum : soNumList) {
    // Map<String, Object> paramMap = new HashMap<String, Object>();
    // paramMap.put("glblCmpyCd", this.glblCmpyCdM);
    // paramMap.put("soNum", soNum);
    // paramMap.put("cpoInfo", SHPG_ORD_MSG_TP.CPO_INFORMATION);
    // paramMap.put("routingMsg", SHPG_ORD_MSG_TP.ROUTING_MESSAGE);
    // PreparedStatement pstmt = null;
    // ResultSet resultSet = null;
    // try {
    // pstmt = ssmLowLev.createPreparedStatement("selectSoMessage",
    // paramMap);
    // resultSet = pstmt.executeQuery();
    // if (resultSet.next()) {
    // resultSet.last();
    // EZDTMsg[] resultArrayWrk = new EZDTMsg[resultSet.getRow()];
    // resultSet.beforeFirst();
    // while (resultSet.next()) {
    // SHPG_ORD_MSGTMsg wrkTMsg = new SHPG_ORD_MSGTMsg();
    // ZYPEZDItemValueSetter.setValue(wrkTMsg.soMsgDescTxt,
    // resultSet.getString(SO_MSG_DESC_TXT));
    // resultArrayWrk[resultSet.getRow() - 1] = wrkTMsg;
    // }
    // SHPG_ORD_MSGTMsgArray resultMsgData = new
    // SHPG_ORD_MSGTMsgArray();
    // resultMsgData.setMsgList(resultArrayWrk);
    // resultMsgMap.put(soNum, resultMsgData);
    // } else {
    // resultMsgMap.put(soNum, new SHPG_ORD_MSGTMsgArray());
    // }
    // } finally {
    // S21SsmLowLevelCodingClient.closeResource(pstmt, resultSet);
    // }
    // }
    // return resultMsgMap;
    // }
    /**
     * @throws SQLException
     */
    private Map<String, SHPG_ORD_MSGTMsgArray> selectSoMessageBulk(List<String> soNumList) throws SQLException {

        Map<String, SHPG_ORD_MSGTMsgArray> resultMsgMap = new HashMap<String, SHPG_ORD_MSGTMsgArray>();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCdM);
        paramMap.put("soNumList", soNumList);
        paramMap.put("cpoInfo", SHPG_ORD_MSG_TP.CPO_INFORMATION);
        paramMap.put("routingMsg", SHPG_ORD_MSG_TP.ROUTING_MESSAGE);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = ssmLowLev.createPreparedStatement("selectSoMessageBulk", paramMap);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                resultSet.last();
                EZDTMsg[] resultArrayWrk = new EZDTMsg[resultSet.getRow()];
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    SHPG_ORD_MSGTMsg wrkTMsg = new SHPG_ORD_MSGTMsg();
                    ZYPEZDItemValueSetter.setValue(wrkTMsg.soMsgDescTxt, resultSet.getString(SO_MSG_DESC_TXT));
                    ZYPEZDItemValueSetter.setValue(wrkTMsg.soNum, resultSet.getString(SO_NUM));
                    resultArrayWrk[resultSet.getRow() - 1] = wrkTMsg;
                }

                for (String soNum : soNumList) {
                    boolean msgFlg = false;
                    int msgCnt = 0;

                    EZDTMsg[] resultArrayWrkTmp = new EZDTMsg[1];
                    SHPG_ORD_MSGTMsgArray resultMsgData = new SHPG_ORD_MSGTMsgArray();

                    for (EZDTMsg wrkTMsg : resultArrayWrk) {
                        SHPG_ORD_MSGTMsg wrkTMsg1 = (SHPG_ORD_MSGTMsg) wrkTMsg;
                        if (wrkTMsg1.soNum.getValue().equals(soNum)) {

                            resultArrayWrkTmp = addResultArrayWrk(msgCnt, wrkTMsg1, resultArrayWrkTmp);
                            msgCnt = msgCnt + 1;
                            msgFlg = true;
                        }
                    }
                    if (msgFlg) {
                        resultMsgData.setMsgList(resultArrayWrkTmp);
                        resultMsgMap.put(soNum, resultMsgData);
                    } else {
                        resultMsgMap.put(soNum, resultMsgData);
                    }
                }
            } else {
                for (String soNum : soNumList) {
                    resultMsgMap.put(soNum, new SHPG_ORD_MSGTMsgArray());
                }
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(pstmt, resultSet);
        }

        return resultMsgMap;
    }

    private EZDTMsg[] addResultArrayWrk(int arryaCnt, SHPG_ORD_MSGTMsg wrkTMsg1, EZDTMsg[] resultArrayWrkTmp) {

        EZDTMsg[] resultArrayWrk = new EZDTMsg[arryaCnt + 1];

        for (int i = 0; i < resultArrayWrkTmp.length; i++) {
            resultArrayWrk[i] = resultArrayWrkTmp[i];
        }

        resultArrayWrk[arryaCnt] = wrkTMsg1;
        return resultArrayWrk;
    }

    /**
     * @throws SQLException
     */
    private SHPG_ORD_RPT_WRKTMsgArray editSoData(ResultSet resultSet, Map<String, SHPG_ORD_MSGTMsgArray> resultMsgMap, List<String> xxMsgIdList) throws SQLException {
        resultSet.last();
        EZDTMsg[] resultArrayWrk = new EZDTMsg[resultSet.getRow()];

        int lineNum = 1;
        String soNumOld = "";

        resultSet.beforeFirst();
        while (resultSet.next()) {
            SHPG_ORD_RPT_WRKTMsg wrkTMsg = new SHPG_ORD_RPT_WRKTMsg();
            String soNum = resultSet.getString(SO_NUM);

            if (!soNumOld.equals(soNum)) {
                lineNum = 1;
                soNumOld = soNum;
            }
            // GLBL_CMPY_CD
            ZYPEZDItemValueSetter.setValue(wrkTMsg.glblCmpyCd, this.glblCmpyCdM);
            // USER_ID
            ZYPEZDItemValueSetter.setValue(wrkTMsg.usrId, this.usrIdM);
            // PROC_START_TS
            ZYPEZDItemValueSetter.setValue(wrkTMsg.procStartTs, this.procStartTsM);
            // SO_NUM
            ZYPEZDItemValueSetter.setValue(wrkTMsg.soNum, soNum);
            // ITEM_LINE_NUM
            ZYPEZDItemValueSetter.setValue(wrkTMsg.itemLineNum, String.valueOf(lineNum));
            // GLBL_CMPY_NM
            ZYPEZDItemValueSetter.setValue(wrkTMsg.glblCmpyNm, resultSet.getString(GLBL_CMPY_NM));
            // WH_ADDR  2014/05/21 Mod CSA#38 for Check Style
            editWhAddr(resultSet, wrkTMsg);
            // RTRN_TO_XX  2014/05/21 Mod CSA#38 for Check Style
            editReturnTo(resultSet, wrkTMsg);
            // CPO_ORD_NUM
            ZYPEZDItemValueSetter.setValue(wrkTMsg.cpoOrdNum, resultSet.getString(TRX_HDR_NUM));
            // SELL_TO_XX  2014/05/21 Mod CSA#38 for Check Style
            editSellTo(resultSet, wrkTMsg);
            // SHIP_TO_XX  2014/05/21 Mod CSA#38 for Check Style
            editShipTo(resultSet, wrkTMsg);
            // WH_CD
            ZYPEZDItemValueSetter.setValue(wrkTMsg.whCd, resultSet.getString(WH_CD));
            // FRT_CHRG_TO_TXT
            if (resultSet.getString(FRT_CHRG_TO_CD) != null || resultSet.getString(FRT_CHRG_TO_NM) != null) {
                StringBuilder sbFrtChrgTo = new StringBuilder();
                sbFrtChrgTo.append(NLXC014001.nullToEmpty(resultSet.getString(FRT_CHRG_TO_CD)));
                sbFrtChrgTo.append(" - ");
                sbFrtChrgTo.append(NLXC014001.nullToEmpty(resultSet.getString(FRT_CHRG_TO_NM)));
                ZYPEZDItemValueSetter.setValue(wrkTMsg.frtChrgToTxt, S21StringUtil.trimString(sbFrtChrgTo.toString()));
            }
            // FRT_CHRG_METH_TXT
            if (resultSet.getString(FRT_CHRG_METH_CD) != null || resultSet.getString(FRT_CHRG_METH_NM) != null) {
                StringBuilder sbFrtChrgMeth = new StringBuilder();
                sbFrtChrgMeth.append(NLXC014001.nullToEmpty(resultSet.getString(FRT_CHRG_METH_CD)));
                sbFrtChrgMeth.append(" - ");
                sbFrtChrgMeth.append(NLXC014001.nullToEmpty(resultSet.getString(FRT_CHRG_METH_NM)));
                ZYPEZDItemValueSetter.setValue(wrkTMsg.frtChrgMethTxt, S21StringUtil.trimString(sbFrtChrgMeth.toString()));
            }
            // CARR_ACCT_NUM
            ZYPEZDItemValueSetter.setValue(wrkTMsg.carrAcctNum, NLXC014001.nullToEmpty(resultSet.getString(CARR_ACCT_NUM)));
            // S80_SHPG_TERM_TXT
            if (resultSet.getString(S80_SHPG_TERM_CD) != null || resultSet.getString(S80_SHPG_TERM_NM) != null) {
                StringBuilder sbS80ShpgTerm = new StringBuilder();
                sbS80ShpgTerm.append(NLXC014001.nullToEmpty(resultSet.getString(S80_SHPG_TERM_CD)));
                sbS80ShpgTerm.append(" - ");
                sbS80ShpgTerm.append(NLXC014001.nullToEmpty(resultSet.getString(S80_SHPG_TERM_NM)));
                ZYPEZDItemValueSetter.setValue(wrkTMsg.s80ShpgTermTxt, S21StringUtil.trimString(sbS80ShpgTerm.toString()));
            }
            // SHPG_SVC_LVL_TXT
            if (resultSet.getString(SHPG_SVC_LVL_CD) != null || resultSet.getString(SHPG_SVC_LVL_NM) != null) {
                StringBuilder sbShpgSvcLvl = new StringBuilder();
                sbShpgSvcLvl.append(NLXC014001.nullToEmpty(resultSet.getString(SHPG_SVC_LVL_CD)));
                sbShpgSvcLvl.append(" - ");
                sbShpgSvcLvl.append(NLXC014001.nullToEmpty(resultSet.getString(SHPG_SVC_LVL_NM)));
                ZYPEZDItemValueSetter.setValue(wrkTMsg.shpgSvcLvlTxt, S21StringUtil.trimString(sbShpgSvcLvl.toString()));
            }
            // SO_TP_TXT
            String sceOrdTpCd = resultSet.getString(SCE_ORD_TP_CD);
            if (sceOrdTpCd != null || resultSet.getString(SCE_ORD_TP_NM) != null) {
                StringBuilder sbSceOrdTp = new StringBuilder();
                sbSceOrdTp.append(NLXC014001.nullToEmpty(sceOrdTpCd));
                sbSceOrdTp.append(" - ");
                sbSceOrdTp.append(NLXC014001.nullToEmpty(resultSet.getString(SCE_ORD_TP_NM)));
                ZYPEZDItemValueSetter.setValue(wrkTMsg.soTpTxt, S21StringUtil.trimString(sbSceOrdTp.toString()));
            }
            // SHPG_MODE_TXT
            // EXPT_SHPG_METH_TXT
            if (!NLXSceConst.SCE_ORD_TYPE_EXPORT.equals(sceOrdTpCd) && !NLXSceConst.SCE_ORD_TYPE_RI.equals(sceOrdTpCd)) {
                if (resultSet.getString(SHPG_MODE_CD) != null || resultSet.getString(SHPG_MODE_NM) != null) {
                    StringBuilder sbShpgMode = new StringBuilder();
                    sbShpgMode.append(NLXC014001.nullToEmpty(resultSet.getString(SHPG_MODE_CD)));
                    sbShpgMode.append(" - ");
                    sbShpgMode.append(NLXC014001.nullToEmpty(resultSet.getString(SHPG_MODE_NM)));
                    ZYPEZDItemValueSetter.setValue(wrkTMsg.shpgModeTxt, S21StringUtil.trimString(sbShpgMode.toString()));
                }
            } else {
                if (resultSet.getString(SHPG_METH_CD) != null || resultSet.getString(SHPG_METH_NM) != null) {
                    StringBuilder sbShpgMeth = new StringBuilder();
                    sbShpgMeth.append(NLXC014001.nullToEmpty(resultSet.getString(SHPG_METH_CD)));
                    sbShpgMeth.append(" - ");
                    sbShpgMeth.append(NLXC014001.nullToEmpty(resultSet.getString(SHPG_METH_NM)));
                    ZYPEZDItemValueSetter.setValue(wrkTMsg.exptShpgMethTxt, S21StringUtil.trimString(sbShpgMeth.toString()));
                }
            }
            // SO_MSG_DESC_TXT_01 - 10  2014/05/21 Mod CSA#38 for Check Style
            editSoMsgDescTxt(resultMsgMap, wrkTMsg, soNum);
            // RQST_ORD_QTY_TXT
            if (ZYPCommonFunc.hasValue(resultSet.getBigDecimal(RQST_ORD_QTY))) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.rqstOrdQtyTxt, String.valueOf(resultSet.getBigDecimal(RQST_ORD_QTY)));
            }
            // SHPG_QTY_TXT
            if (ZYPCommonFunc.hasValue(resultSet.getBigDecimal(SHPG_QTY))) {
                ZYPEZDItemValueSetter.setValue(wrkTMsg.shpgQtyTxt, String.valueOf(resultSet.getBigDecimal(SHPG_QTY)));
            }
            // STK_STS, TO_STK_STS_TXT  2014/05/21 Mod CSA#38 for Check Style
            editStkSts(resultSet, wrkTMsg, sceOrdTpCd);
            // MDSE_CD
            ZYPEZDItemValueSetter.setValue(wrkTMsg.mdseCd, getMdseCd(resultSet)); // 2014/05/21 Update CSA#38
            // CUST_MDSE_CD
            ZYPEZDItemValueSetter.setValue(wrkTMsg.custMdseCd, resultSet.getString(CUST_MDSE_CD));
            // MDSE_NM
            ZYPEZDItemValueSetter.setValue(wrkTMsg.mdseNm, resultSet.getString(MDSE_NM));
            // UPC_CD
            ZYPEZDItemValueSetter.setValue(wrkTMsg.upcCd, resultSet.getString(UPC_CD));
            // SER_NUM_TAKE_FLG
            ZYPEZDItemValueSetter.setValue(wrkTMsg.serNumTakeFlg, resultSet.getString(SER_NUM_TAKE_FLG));
            // SET_MDSE_CD
            ZYPEZDItemValueSetter.setValue(wrkTMsg.setMdseCd, resultSet.getString(SET_MDSE_CD));
            // SET_MDSE_NM
            ZYPEZDItemValueSetter.setValue(wrkTMsg.setMdseNm, resultSet.getString(SET_MDSE_NM));
            // SYS_DT_TXT
            String strDate = resultSet.getString(DNLD_TM_TS);
            String sysDtTxt;
            if (ZYPCommonFunc.hasValue(strDate)) {
                sysDtTxt = strDate.substring(SYSDT_DT_FROM, SYSDT_DT_TO);
                if (ZYPDateUtil.checkDate(sysDtTxt)) {
                    ZYPEZDItemValueSetter.setValue(wrkTMsg.sysDtTxt //
                            , ZYPDateUtil.formatEzd8ToSysDisp(sysDtTxt)); // 2014/06/13 Mod CSA#38
                } else {
                    EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1020E), this);
                    xxMsgIdList.add(NLXM1020E);
                    return null;
                }
            }
            // 2014/05/23 Del CSA#38 Start
            // TOT_AMT_CCY_TXT
            //            StringBuilder totAmtCcyTxt = new StringBuilder();
            //            BigDecimal totShipAmt = resultSet.getBigDecimal(TOT_SHIP_AMT);
            //            if (ZYPCommonFunc.hasValue(totShipAmt)) {
            //                totShipAmt = totShipAmt.setScale(LN_SMALL_TACT, BigDecimal.ROUND_DOWN);
            //                totAmtCcyTxt.append(totShipAmt.toString());
            //                BigDecimal tempShipAmt = totShipAmt.abs();
            //                String amtString = tempShipAmt.toPlainString();
            //                int insertPos = 6;
            //                int amtLength = amtString.length() - 3;
            //                for (int i = 0; i < amtLength / 3; i++) {
            //                    if ((amtLength % 3) == 0 && i == (amtLength / 3) - 1) {
            //                        break;
            //                    } else {
            //                        totAmtCcyTxt.insert(totAmtCcyTxt.length() - insertPos, ",");
            //                        insertPos = insertPos + 4;
            //                    }
            //                }
            //                totAmtCcyTxt.append("(");
            //                totAmtCcyTxt.append(NLXC014001.nullToEmpty(resultSet.getString(STD_CCY_CD)));
            //                totAmtCcyTxt.append(")");
            //                ZYPEZDItemValueSetter.setValue(wrkTMsg.totAmtCcyTxt, totAmtCcyTxt.toString());
            //            }
            // 2014/05/23 Del CSA#38 End

            // PSD_DT
            String psdDt = resultSet.getString(PSD_DT);
            if (ZYPCommonFunc.hasValue(psdDt)) {
                if (ZYPDateUtil.checkDate(psdDt)) {
                    ZYPEZDItemValueSetter.setValue(wrkTMsg.psdDtTxt //
                            , ZYPDateUtil.formatEzd8ToSysDisp(psdDt)); // 2014/06/13 Mod CSA#38
                } else {
                    EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1013E), this);
                    xxMsgIdList.add(NLXM1013E);
                    return null;
                }
            }
            // PDD_DT
            String pddDt = resultSet.getString(PDD_DT);
            if (ZYPCommonFunc.hasValue(pddDt)) {
                if (ZYPDateUtil.checkDate(pddDt)) {
                    ZYPEZDItemValueSetter.setValue(wrkTMsg.pddDtTxt //
                            , ZYPDateUtil.formatEzd8ToSysDisp(pddDt)); // 2014/06/13 Mod CSA#38
                } else {
                    EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1014E), this);
                    xxMsgIdList.add(NLXM1014E);
                    return null;
                }
            }
            // RSD_DT
            String rsdDt = resultSet.getString(RSD_DT);
            if (ZYPCommonFunc.hasValue(rsdDt)) {
                if (ZYPDateUtil.checkDate(rsdDt)) {
                    ZYPEZDItemValueSetter.setValue(wrkTMsg.rsdDtTxt //
                            , ZYPDateUtil.formatEzd8ToSysDisp(rsdDt)); // 2014/06/13 Mod CSA#38
                } else {
                    EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1015E), this);
                    xxMsgIdList.add(NLXM1015E);
                    return null;
                }
            }
            // RDD_DT
            String rddDt = resultSet.getString(RDD_DT);
            if (ZYPCommonFunc.hasValue(rddDt)) {
                if (ZYPDateUtil.checkDate(rddDt)) {
                    ZYPEZDItemValueSetter.setValue(wrkTMsg.rddDtTxt //
                            , ZYPDateUtil.formatEzd8ToSysDisp(rddDt)); // 2014/06/13 Mod CSA#38
                } else {
                    EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1016E), this);
                    xxMsgIdList.add(NLXM1016E);
                    return null;
                }
            }
            // CUST_ISS_PO_NUM
            ZYPEZDItemValueSetter.setValue(wrkTMsg.custIssPoNum, resultSet.getString(CUST_ISS_PO_NUM));
            // RTE_GRP_NUM
            ZYPEZDItemValueSetter.setValue(wrkTMsg.rteGrpNum, resultSet.getString(RTE_GRP_NUM));

            // Defect#7680
            ZYPEZDItemValueSetter.setValue(wrkTMsg.binNum, resultSet.getString(BIN_NUM));

            // 04/11/2012 #22 START
            ZYPEZDItemValueSetter.setValue(wrkTMsg.soConfigFlg, resultSet.getString(SO_CONFIG_FLG));
            ZYPEZDItemValueSetter.setValue(wrkTMsg.configItemFlg, resultSet.getString(CONFIG_ITEM_FLG));
            // 04/11/2012 #22 END

            // 2014/05/20 Add CSA#38 Start
            ZYPEZDItemValueSetter.setValue(wrkTMsg.totShipWtPrintTxt //
                    , ZYPFormatUtil.formatNumToSysDisp(resultSet.getBigDecimal(TOT_SHPG_WT)));
            ZYPEZDItemValueSetter.setValue(wrkTMsg.shipWtUnitTxt //
                    , ZYPCodeDataUtil.getVarCharConstValue(SO_PRINT_SHIP_WT_UNIT_TXT, this.glblCmpyCdM));

            List<SHPG_ORD_RPT_SER_WRKTMsg> tMsgList = selectSoSer(resultSet, wrkTMsg.itemLineNum.getValue(), xxMsgIdList);
            if (tMsgList.size() > 0) {
                SHPG_ORD_RPT_SER_WRKTMsg[] tMsgArray = new SHPG_ORD_RPT_SER_WRKTMsg[tMsgList.size()];
                S21FastTBLAccessor.insert(tMsgList.toArray(tMsgArray));
            }
            // 2014/05/20 Add CSA#38 End

            resultArrayWrk[resultSet.getRow() - 1] = wrkTMsg;
            lineNum++;
        }
        SHPG_ORD_RPT_WRKTMsgArray resultArray = new SHPG_ORD_RPT_WRKTMsgArray();
        resultArray.setMsgList(resultArrayWrk);
        return resultArray;
    }

    /**
     * 2014/05/21 Add CSA#38 for Check Style edit STK_STS_TXT, TO_STK_STS_TXT
     * @param resultSet
     * @param wrkTMsg
     * @param sceOrdTpCd
     * @throws SQLException
     */
    private void editStkSts(ResultSet resultSet, SHPG_ORD_RPT_WRKTMsg wrkTMsg, String sceOrdTpCd) throws SQLException {
        // STK_STS_TXT
        if (resultSet.getString(FROM_STK_STS_CD) != null || resultSet.getString(FROM_STK_STS_NM) != null) {
            StringBuilder sbStkSts = new StringBuilder();
            sbStkSts.append(NLXC014001.nullToEmpty(resultSet.getString(FROM_STK_STS_CD)));
            sbStkSts.append(" - ");
            sbStkSts.append(NLXC014001.nullToEmpty(resultSet.getString(FROM_STK_STS_NM)));
            ZYPEZDItemValueSetter.setValue(wrkTMsg.stkStsTxt, S21StringUtil.trimString(sbStkSts.toString()));
        }
        // TO_STK_STS_TXT
        if (NLXSceConst.SCE_ORD_TYPE_SC.equals(sceOrdTpCd) || NLXSceConst.SCE_ORD_TYPE_DC.equals(sceOrdTpCd)) {
            if (resultSet.getString(TO_STK_STS_CD) != null || resultSet.getString(TO_STK_STS_NM) != null) {
                StringBuilder sbStkSts = new StringBuilder();
                sbStkSts.append(NLXC014001.nullToEmpty(resultSet.getString(TO_STK_STS_CD)));
                sbStkSts.append(" - ");
                sbStkSts.append(NLXC014001.nullToEmpty(resultSet.getString(TO_STK_STS_NM)));
                ZYPEZDItemValueSetter.setValue(wrkTMsg.toStkStsTxt, S21StringUtil.trimString(sbStkSts.toString()));
            }
        }
    }

    /**
     * 2014/05/21 Add CSA#38 for Check Style edit WH_ADDR_01, WH_ADDR_02
     * @param resultSet
     * @param wrkTMsg
     * @throws SQLException
     */
    private void editWhAddr(ResultSet resultSet, SHPG_ORD_RPT_WRKTMsg wrkTMsg) throws SQLException {
        // WH_ADDR_01
        StringBuilder sbWhAddr01 = new StringBuilder();
        sbWhAddr01.append(NLXC014001.nullToEmpty(resultSet.getString(FIRST_LINE_ADDR)));
        sbWhAddr01.append(" ");
        sbWhAddr01.append(NLXC014001.nullToEmpty(resultSet.getString(SCD_LINE_ADDR)));
        sbWhAddr01.append(" ");
        sbWhAddr01.append(NLXC014001.nullToEmpty(resultSet.getString(THIRD_LINE_ADDR)));
        sbWhAddr01.append(" ");
        sbWhAddr01.append(NLXC014001.nullToEmpty(resultSet.getString(FRTH_LINE_ADDR)));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.whAddr_01, S21StringUtil.trimString(sbWhAddr01.toString()));
        // WH_ADDR_02
        StringBuilder sbWhAddr02 = new StringBuilder();
        sbWhAddr02.append(NLXC014001.nullToEmpty(resultSet.getString(CTY_ADDR)));
        sbWhAddr02.append(", ");
        sbWhAddr02.append(NLXC014001.nullToEmpty(resultSet.getString(ST_CD)));
        sbWhAddr02.append(" ");
        sbWhAddr02.append(NLXC014001.nullToEmpty(resultSet.getString(POST_CD)));
        String whAddr02 = S21StringUtil.trimString(sbWhAddr02.toString());
        if (whAddr02.length() > 1) {
            ZYPEZDItemValueSetter.setValue(wrkTMsg.whAddr_02, whAddr02);
        }
    }

    /**
     * 2014/05/21 Add CSA#38 for Check Style edit RTRN_TO_NM, RTRN_TO_ADDR_01
     * @param resultSet
     * @param wrkTMsg
     * @throws SQLException
     */
    private void editReturnTo(ResultSet resultSet, SHPG_ORD_RPT_WRKTMsg wrkTMsg) throws SQLException {
        // RTRN_TO_NM
        StringBuilder sbRtrnToNm = new StringBuilder();
        sbRtrnToNm.append(NLXC014001.nullToEmpty(resultSet.getString(RT_CUST_LINE_LOC_NM_01)));
        sbRtrnToNm.append(" ");
        sbRtrnToNm.append(NLXC014001.nullToEmpty(resultSet.getString(RT_CUST_LINE_LOC_NM_02)));

        //            ZYPEZDItemValueSetter.setValue(wrkTMsg.rtrnToNm, S21StringUtil.trimString(sbRtrnToNm.toString()));    // 2014/05/21 Del CSA#38
        // RTRN_TO_ADDR_01
        List<String> rtAddrList = new ArrayList<String>();
        rtAddrList.add(resultSet.getString(RT_CUST_LINE_ADDR_01));
        rtAddrList.add(resultSet.getString(RT_CUST_LINE_ADDR_02));
        rtAddrList.add(resultSet.getString(RT_CUST_LINE_ADDR_03));
        rtAddrList.add(resultSet.getString(RT_CUST_LINE_ADDR_04));
        List<String> rtCtyAddrList = new ArrayList<String>();
        rtCtyAddrList.add(resultSet.getString(RT_CTY_ADDR));
        rtCtyAddrList.add(resultSet.getString(RT_ST_CD));
        rtCtyAddrList.add(resultSet.getString(RT_POST_CD));
        rtAddrList.add(createCtyAddr(rtCtyAddrList));
        List<EZDTStringItem> rtItemList = new ArrayList<EZDTStringItem>();
        rtItemList.add(wrkTMsg.rtrnToAddr_01);
        rtItemList.add(wrkTMsg.rtrnToAddr_02);
        rtItemList.add(wrkTMsg.rtrnToAddr_03);
        rtItemList.add(wrkTMsg.rtrnToAddr_04);
        rtItemList.add(wrkTMsg.rtrnToAddr_05);
        //            setAddr(rtItemList, rtAddrList);  // 2014/05/21 Del CSA#38

        // 2014/05/21 Add CSA#38 Start
        if (hasReturnToValue(sbRtrnToNm, rtItemList, rtAddrList)) {
            ZYPEZDItemValueSetter.setValue(//
                    wrkTMsg.rtrnToNm //
                    , S21StringUtil.trimString(sbRtrnToNm.toString()));
            setAddr(rtItemList, rtAddrList);

        } else {
            ZYPEZDItemValueSetter.setValue(//
                    wrkTMsg.rtrnToNm, "N/A");
        }
        // 2014/05/21 Add CSA#38 End
    }

    /**
     * 2014/05/21 Add CSA#38 for Check Style edit SELL_TO_NM, SELL_TO_ADDR_01
     * @param resultSet
     * @param wrkTMsg
     * @throws SQLException
     */
    private void editSellTo(ResultSet resultSet, SHPG_ORD_RPT_WRKTMsg wrkTMsg) throws SQLException {
        // SELL_TO_NM
        StringBuilder sbSellToNm = new StringBuilder();
        sbSellToNm.append(NLXC014001.nullToEmpty(resultSet.getString(SL_CUST_LINE_LOC_NM_01)));
        sbSellToNm.append(" ");
        sbSellToNm.append(NLXC014001.nullToEmpty(resultSet.getString(SL_CUST_LINE_LOC_NM_02)));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.sellToNm, S21StringUtil.trimString(sbSellToNm.toString()));
        // SELL_TO_ADDR_01
        List<String> slAddrList = new ArrayList<String>();
        slAddrList.add(resultSet.getString(SL_CUST_LINE_ADDR_01));
        slAddrList.add(resultSet.getString(SL_CUST_LINE_ADDR_02));
        slAddrList.add(resultSet.getString(SL_CUST_LINE_ADDR_03));
        slAddrList.add(resultSet.getString(SL_CUST_LINE_ADDR_04));
        List<String> slCtyAddrList = new ArrayList<String>();
        slCtyAddrList.add(resultSet.getString(SL_CTY_ADDR));
        slCtyAddrList.add(resultSet.getString(SL_ST_CD));
        slCtyAddrList.add(resultSet.getString(SL_POST_CD));
        slAddrList.add(createCtyAddr(slCtyAddrList));
        List<EZDTStringItem> slItemList = new ArrayList<EZDTStringItem>();
        slItemList.add(wrkTMsg.sellToAddr_01);
        slItemList.add(wrkTMsg.sellToAddr_02);
        slItemList.add(wrkTMsg.sellToAddr_03);
        slItemList.add(wrkTMsg.sellToAddr_04);
        slItemList.add(wrkTMsg.sellToAddr_05);
        setAddr(slItemList, slAddrList);
    }

    /**
     * 2014/05/21 Add CSA#38 for Check Style edit SHIP_TO_NM, SHIP_TO_ADDR_01 and SHIP_TO_ADDR_02
     * @param resultSet
     * @param wrkTMsg
     * @throws SQLException
     */
    private void editShipTo(ResultSet resultSet, SHPG_ORD_RPT_WRKTMsg wrkTMsg) throws SQLException {
        // SHIP_TO_NM
        StringBuilder sbShipToNm = new StringBuilder();
        sbShipToNm.append(NLXC014001.nullToEmpty(resultSet.getString(SP_CUST_LINE_LOC_NM_01)));
        sbShipToNm.append(" ");
        sbShipToNm.append(NLXC014001.nullToEmpty(resultSet.getString(SP_CUST_LINE_LOC_NM_02)));
        ZYPEZDItemValueSetter.setValue(wrkTMsg.shipToNm, S21StringUtil.trimString(sbShipToNm.toString()));
        if (ZYPCommonFunc.hasValue(resultSet.getString(SP_CTAC_PTNR_PSN_NM))) {
            // SHIP_TO_ADDR_01
            StringBuilder sbShipToAddr01 = new StringBuilder();
            sbShipToAddr01.append("ATTN: ");
            sbShipToAddr01.append(NLXC014001.nullToEmpty(resultSet.getString(SP_CTAC_PTNR_PSN_NM)));
            ZYPEZDItemValueSetter.setValue(wrkTMsg.shipToAddr_01, S21StringUtil.trimString(sbShipToAddr01.toString()));
        }
        // SHIP_TO_ADDR_02
        List<String> spAddrList = new ArrayList<String>();
        spAddrList.add(resultSet.getString(SP_CUST_LINE_ADDR_01));
        spAddrList.add(resultSet.getString(SP_CUST_LINE_ADDR_02));
        spAddrList.add(resultSet.getString(SP_CUST_LINE_ADDR_03));
        spAddrList.add(resultSet.getString(SP_CUST_LINE_ADDR_04));
        List<String> spCtyAddrList = new ArrayList<String>();
        spCtyAddrList.add(resultSet.getString(SP_CTY_ADDR));
        spCtyAddrList.add(resultSet.getString(SP_ST_CD));
        spCtyAddrList.add(resultSet.getString(SP_POST_CD));
        spAddrList.add(createCtyAddr(spCtyAddrList));
        List<EZDTStringItem> spItemList = new ArrayList<EZDTStringItem>();
        if (!ZYPCommonFunc.hasValue(resultSet.getString(SP_CTAC_PTNR_PSN_NM))) {
            spItemList.add(wrkTMsg.shipToAddr_01);
        }
        spItemList.add(wrkTMsg.shipToAddr_02);
        spItemList.add(wrkTMsg.shipToAddr_03);
        spItemList.add(wrkTMsg.shipToAddr_04);
        spItemList.add(wrkTMsg.shipToAddr_05);
        spItemList.add(wrkTMsg.shipToAddr_06);
        setAddr(spItemList, spAddrList);
    }

    /**
     * 2014/05/21 Add CSA#38 for Check Style edit SO_MSG_DESC_TXT_01 - 10
     * @param resultMsgMap
     * @param wrkTMsg
     * @param soNum
     */
    private void editSoMsgDescTxt(Map<String, SHPG_ORD_MSGTMsgArray> resultMsgMap, SHPG_ORD_RPT_WRKTMsg wrkTMsg, String soNum) {
        // SO_MSG_DESC_TXT_01
        SHPG_ORD_MSGTMsgArray msgArray = resultMsgMap.get(soNum);
        if (msgArray.length() > NUM0) {
            ZYPEZDItemValueSetter.setValue(wrkTMsg.soMsgDescTxt_01, msgArray.no(NUM0).soMsgDescTxt.getValue());
        }
        // SO_MSG_DESC_TXT_02
        if (msgArray.length() > NUM1) {
            ZYPEZDItemValueSetter.setValue(wrkTMsg.soMsgDescTxt_02, msgArray.no(NUM1).soMsgDescTxt.getValue());
        }
        // SO_MSG_DESC_TXT_03
        if (msgArray.length() > NUM2) {
            ZYPEZDItemValueSetter.setValue(wrkTMsg.soMsgDescTxt_03, msgArray.no(NUM2).soMsgDescTxt.getValue());
        }
        // SO_MSG_DESC_TXT_04
        if (msgArray.length() > NUM3) {
            ZYPEZDItemValueSetter.setValue(wrkTMsg.soMsgDescTxt_04, msgArray.no(NUM3).soMsgDescTxt.getValue());
        }
        // SO_MSG_DESC_TXT_05
        if (msgArray.length() > NUM4) {
            ZYPEZDItemValueSetter.setValue(wrkTMsg.soMsgDescTxt_05, msgArray.no(NUM4).soMsgDescTxt.getValue());
        }
        // SO_MSG_DESC_TXT_06
        if (msgArray.length() > NUM5) {
            ZYPEZDItemValueSetter.setValue(wrkTMsg.soMsgDescTxt_06, msgArray.no(NUM5).soMsgDescTxt.getValue());
        }
        // SO_MSG_DESC_TXT_07
        if (msgArray.length() > NUM6) {
            ZYPEZDItemValueSetter.setValue(wrkTMsg.soMsgDescTxt_07, msgArray.no(NUM6).soMsgDescTxt.getValue());
        }
        // SO_MSG_DESC_TXT_08
        if (msgArray.length() > NUM7) {
            ZYPEZDItemValueSetter.setValue(wrkTMsg.soMsgDescTxt_08, msgArray.no(NUM7).soMsgDescTxt.getValue());
        }
        // SO_MSG_DESC_TXT_09
        if (msgArray.length() > NUM8) {
            ZYPEZDItemValueSetter.setValue(wrkTMsg.soMsgDescTxt_09, msgArray.no(NUM8).soMsgDescTxt.getValue());
        }
        // SO_MSG_DESC_TXT_10
        if (msgArray.length() > NUM9) {
            ZYPEZDItemValueSetter.setValue(wrkTMsg.soMsgDescTxt_10, msgArray.no(NUM9).soMsgDescTxt.getValue());
        }
    }

    /**
     * 2014/05/21 Add CSA#38 if Return To has a value then return true.
     * @param sbRtrnToNm
     * @param rtItemList
     * @param rtAddrList
     * @return
     */
    private boolean hasReturnToValue(//
            StringBuilder sbRtrnToNm //
            , List<EZDTStringItem> rtItemList //
            , List<String> rtAddrList) {
        if (ZYPCommonFunc.hasValue(S21StringUtil.trimString(sbRtrnToNm.toString()))) {
            return true;
        }
        for (EZDTStringItem si : rtItemList) {
            if (ZYPCommonFunc.hasValue(si)) {
                return true;
            }
        }
        for (String s : rtAddrList) {
            if (ZYPCommonFunc.hasValue(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 2014/05/21 Add CSA#38 edit mdseCd to XXXXXXXX(XX)
     * @param rs
     * @return
     * @throws SQLException
     */
    private String getMdseCd(ResultSet rs) throws SQLException {
        String mdseCd = rs.getString(MDSE_CD);
        if (!MDSE_RGTN_TP.MERCURY.equals(rs.getString(MDSE_RGTN_TP_CD))) {
            return mdseCd;
        }
        if (mdseCd.indexOf('-') > 0) {
            return mdseCd;
        }
        if (mdseCd.length() >= NUM10 && mdseCd.length() <= NUM12) {
            return S21StringUtil.concatStrings(//
                    S21StringUtil.subStringByLength(mdseCd, 0, NUM8) //
                    , "(" //
                    , S21StringUtil.subStringByLength(mdseCd, NUM8, NUM2) //
                    , ")");
        }
        return mdseCd;
    }

    /**
     * 2014/05/21 Add CSA#38 edit SO_SER Data
     * @param selectSoSerResultSet
     * @param selectSoResutlSet
     * @param xxMsgIdList
     * @return
     * @throws SQLException
     */
    private List<SHPG_ORD_RPT_SER_WRKTMsg> editSoSerData(//
            ResultSet selectSoSerResultSet //
            , ResultSet selectSoResutlSet //
            , String itemLineNum, List<String> xxMsgIdList) throws SQLException {

        List<SHPG_ORD_RPT_SER_WRKTMsg> tMsgList = new ArrayList<SHPG_ORD_RPT_SER_WRKTMsg>();
        while (selectSoSerResultSet.next()) {
            SHPG_ORD_RPT_SER_WRKTMsg tMsg = new SHPG_ORD_RPT_SER_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCdM);
            ZYPEZDItemValueSetter.setValue(tMsg.usrId, this.usrIdM);
            ZYPEZDItemValueSetter.setValue(tMsg.procStartTs, this.procStartTsM);

            ZYPEZDItemValueSetter.setValue(tMsg.soNum, selectSoResutlSet.getString(SO_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.itemLineNum, itemLineNum);
            ZYPEZDItemValueSetter.setValue(tMsg.serNum, selectSoSerResultSet.getString(SER_NUM));

            tMsgList.add(tMsg);
        }
        return tMsgList;

    }

    /**
     */
    private void registSoData(SHPG_ORD_RPT_WRKTMsgArray wrkTMsgArray, List<String> xxMsgIdList) {

        SHPG_ORD_RPT_WRKTMsg[] tmsgs = new SHPG_ORD_RPT_WRKTMsg[wrkTMsgArray.length()]; // 配列を初期化

        for (int i = 0; i < wrkTMsgArray.length(); i++) {
            SHPG_ORD_RPT_WRKTMsg st = new SHPG_ORD_RPT_WRKTMsg();
            st = (SHPG_ORD_RPT_WRKTMsg) wrkTMsgArray.get(i);
            tmsgs[i] = st;

        }

        int retCnt = S21FastTBLAccessor.insert(tmsgs);

        if (retCnt != wrkTMsgArray.length()) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1017E), this);
            xxMsgIdList.add(NLXM1017E);
            return;
        }

        // for (int i = 0; i < wrkTMsgArray.length(); i++) {
        // SHPG_ORD_RPT_WRKTMsg soRptWrkT = (SHPG_ORD_RPT_WRKTMsg)
        // wrkTMsgArray.get(i);
        // S21ApiTBLAccessor.insert(soRptWrkT);
        // if
        // (!EZDTBLAccessor.RTNCD_NORMAL.equals(soRptWrkT.getReturnCode()))
        // {
        // EZDDebugOutput.printLog(1,
        // S21MessageFunc.clspGetMessage(NLXM1017E), this);
        // xxMsgIdList.add(NLXM1017E);
        // return;
        // }
        // }

    }

    // 2014/06/13 Del CSA#38 Start
    //    /**
    //     * @param string
    //     * @return String
    //     */
    //    private String transDt(String string) {
    //        StringBuilder strBuilder = new StringBuilder();
    //        strBuilder.append(string.substring(DT_YEAR_TO, DT_MONTH_TO));
    //        strBuilder.append(DATE_FMT_SEPARATOR); // 2014/05/20 CSA#38 Replace ("-");
    //        strBuilder.append(string.substring(DT_MONTH_TO, DT_DAY_TO));
    //        strBuilder.append(DATE_FMT_SEPARATOR); // 2014/05/20 CSA#38 Replace ("-");
    //        strBuilder.append(string.substring(DT_YEAR_FROM, DT_YEAR_TO));
    //        return strBuilder.toString();
    //    }
    // 2014/06/13 Del CSA#38 Start

    private void setAddr(List<EZDTStringItem> itemList, List<String> addrList) {
        if (!NLXC015001.hasValue(addrList) || !NLXC015001.hasValue(itemList)) {
            return;
        }
        Iterator<EZDTStringItem> iteItem = itemList.iterator();
        for (String addr : addrList) {
            if (iteItem.hasNext() && ZYPCommonFunc.hasValue(addr)) {
                EZDTStringItem item = (EZDTStringItem) iteItem.next();
                ZYPEZDItemValueSetter.setValue(item, S21StringUtil.trimString(addr));
            }
        }
    }

    private String createCtyAddr(List<String> addrList) {
        if (!NLXC015001.hasValue(addrList)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addrList.size(); i++) {
            String addr = addrList.get(i);
            if (ZYPCommonFunc.hasValue(addr)) {
                sb.append(addr);
                if (i == 0) {
                    if (addrList.size() != 1) {
                        sb.append(", ");
                    }
                } else if (!(i == addrList.size() - 1)) {
                    sb.append(" ");
                }
            }
        }
        return S21StringUtil.trimString(sb.toString());
    }


    /**
     * <pre>setSoRptWrk</pre>
     * @param glblCmpyCd    String
     * @param usrId         String
     * @param procStartTs   String
     * @param soNum         String
     * @param xxMsgIdList   List<String>
     * @return reportPK     BigDecimal
     * @throws SQLException SQLException
     */
    public BigDecimal setSoRptWrk(String glblCmpyCd, String usrId, String procStartTs, String soNum, List<String> xxMsgIdList) throws SQLException {
        ssmLowLev = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        returnMsgIdList = xxMsgIdList;

        checkParam(glblCmpyCd, usrId, procStartTs, soNum);

        if (procErrorFlg) {
            return null;
        }

        this.glblCmpyCdM = glblCmpyCd;
        this.usrIdM = usrId;
        this.procStartTsM = procStartTs;
        this.soNumM = soNum;

        checkDBParams();

        if (procErrorFlg) {
            return null;
        }

        // remove work table 
        removeShpgOrdRptWrk();

        List<SHPG_ORD_RPT_WRKTMsg> soInfoList = getSoInfo(this.soNumM);
        if (procErrorFlg) {
            return null;
        }

        // output report work
        outputSoReportWork(soInfoList);

        if (procErrorFlg) {
            return null;
        }

        SHPG_ORD_RPT_WRKTMsg info = soInfoList.get(0);

        return info.shpgOrdRptPrintRqstSq.getValue();
    }

    /**
     * <pre>checkParam</pre>
     * @param glblCmpyCd    String
     * @param usrId         String
     * @param procStartTs   String
     * @param soNum         String
     */
    private void checkParam(String glblCmpyCd, String usrId, String procStartTs, String soNum) {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1005E), this);
            addErrorMsgList(NLXM1005E);
            return;
        }

        if (!ZYPCommonFunc.hasValue(usrId)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1005E), this);
            addErrorMsgList(NLXM1005E);
            return;
        }

        if (!ZYPCommonFunc.hasValue(procStartTs)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1005E), this);
            addErrorMsgList(NLXM1005E);
            return;
        }

        if (!ZYPCommonFunc.hasValue(soNum)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1006E), this);
            addErrorMsgList(NLXM1006E);
            return;
        }

    }

    /**
     * addErrorMsgList
     * @param   messageID   String
     */
    private void addErrorMsgList(String messageID) {
        returnMsgIdList.add(messageID);
        procErrorFlg = true;
    }

    /**
     * checkDBParams
     */
    private void checkDBParams() {

        /** report work table purge date. */
        purgeDt = ZYPCodeDataUtil.getNumConstValue(CONST_NLBF0010_PURGE_DT, glblCmpyCdM);
        if (!ZYPCommonFunc.hasValue(purgeDt)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1039E), this);
            addErrorMsgList(NLXM1039E);
        }

        lineNumOf1stPage = ZYPCodeDataUtil.getNumConstValue(CONST_NLBF0010_LINE_NUM_OF_1ST_PAGE, glblCmpyCdM);
        if (!ZYPCommonFunc.hasValue(lineNumOf1stPage)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1037E), this);
            addErrorMsgList(NLXM1037E);
        }

        lineNumOf2ndPage = ZYPCodeDataUtil.getNumConstValue(CONST_NLBF0010_LINE_NUM_OF_2ND_PAGE, glblCmpyCdM);
        if (!ZYPCommonFunc.hasValue(lineNumOf2ndPage)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1038E), this);
            addErrorMsgList(NLXM1038E);
        }

        numOfDescLtr = ZYPCodeDataUtil.getNumConstValue(CONST_NLBF0010_NUM_OF_DESC_LTR, glblCmpyCdM);
        if (!ZYPCommonFunc.hasValue(numOfDescLtr)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1040E), this);
            addErrorMsgList(NLXM1040E);
        }

        partsLineNumOf1stPage = ZYPCodeDataUtil.getNumConstValue(CONST_NLBF0010_P_LINE_NUM_1ST_PAGE, glblCmpyCdM);
        if (!ZYPCommonFunc.hasValue(partsLineNumOf1stPage)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1044E), this);
            addErrorMsgList(NLXM1044E);
        }

        partsLineNumOf2ndPage = ZYPCodeDataUtil.getNumConstValue(CONST_NLBF0010_P_LINE_NUM_2ND_PAGE, glblCmpyCdM);
        if (!ZYPCommonFunc.hasValue(partsLineNumOf2ndPage)) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1045E), this);
            addErrorMsgList(NLXM1045E);
        }

        reprintCharString = ZYPCodeDataUtil.getVarCharConstValue(CONST_NLBF0010_REPRINT, glblCmpyCdM);
    }
    /**
     * getSoInfo
     * @param   soNum           String
     * @return  soInfoList      List<SHPG_ORD_RPT_WRKTMsg>
     * @throws  SQLException    SQLException
     */
    private List<SHPG_ORD_RPT_WRKTMsg> getSoInfo(String soNum) throws SQLException {

        List<SHPG_ORD_RPT_WRKTMsg> detailList = new ArrayList<SHPG_ORD_RPT_WRKTMsg>();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCdM);
        paramMap.put(DB_PARAM_SO_NUM, soNum);
        paramMap.put(DB_PARAM_LINE_NUM_SEPARATOR, LINE_NUM_SEPARATOR);
        paramMap.put(DB_PARAM_SO_CUST_DATA_TP_CD, SO_CUST_DATA_TP.SHIP_TO);
        paramMap.put(DB_PARAM_INBD_OTBD_CD, INBD_OTBD.OUTBOUND);
        paramMap.put(DB_PARAM_DS_COND_CONST_GRP_ID, DS_COND_CONST_GRP_ID);
        paramMap.put(DB_PARAM_BIZ_APP_ID, BIZ_APP_ID);
        paramMap.put(DB_PARAM_SO_MSG_TP_CD, SO_MSG_TP_CD);
        paramMap.put(DB_PARAM_DS_SO_LINE_STS_CD_01, DS_SO_LINE_STS.PICK_CONFIRMED);
        paramMap.put(DB_PARAM_DS_SO_LINE_STS_CD_02, DS_SO_LINE_STS.AWAITING_SHIPPING);
        paramMap.put(DB_PARAM_SO_PROC_STS_CD, SO_PROC_STS.SHIP);
        paramMap.put(DB_PARAM_SO_PRINT_FLG, ZYPConstant.FLG_ON_Y);
        paramMap.put(DB_PARAM_SHIP_FLG, ZYPConstant.FLG_OFF_N);
        paramMap.put(DB_PARAM_SO_LINE_OPEN_FLG, ZYPConstant.FLG_ON_Y);
        paramMap.put(DB_PARAM_FLG_Y, ZYPConstant.FLG_ON_Y);
        paramMap.put(DB_PARAM_FLG_N, ZYPConstant.FLG_OFF_N);

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = ssmLowLev.createPreparedStatement("getSO", paramMap);
            resultSet = pstmt.executeQuery();

            SHPG_ORD_RPT_WRKTMsg headerInfo = null;
            while (resultSet.next()) {
                if (headerInfo == null) {
                    headerInfo = getHeaderInfo(resultSet);
                }
                // So / So Serial Set
                setDetailInfo(headerInfo, detailList, resultSet);
            }

            // Add BackOrderRecord
            setDetailInfoBackOrder(headerInfo, detailList);
            
            if (procErrorFlg) {
                return null;
            }

            if (detailList.size() == 0) {
                EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(AZZM0000E), this);
                addErrorMsgList(AZZM0000E);
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(pstmt, resultSet);
        }
        return detailList;
    }

    /**
     * getHeaderInfo.
     * @param   resultSet   ResultSet
     * @return  headerInfo  SHPG_ORD_RPT_WRKTMsg
     */
    private SHPG_ORD_RPT_WRKTMsg getHeaderInfo(ResultSet resultSet) throws SQLException {

        SHPG_ORD_RPT_WRKTMsg headerInfo = new SHPG_ORD_RPT_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(headerInfo.glblCmpyCd, this.glblCmpyCdM);
        ZYPEZDItemValueSetter.setValue(headerInfo.usrId, this.usrIdM);
        ZYPEZDItemValueSetter.setValue(headerInfo.procStartTs, this.procStartTsM);
        ZYPEZDItemValueSetter.setValue(headerInfo.soNum, this.soNumM);

        BigDecimal shpgOrdRptWrkSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SHPG_ORD_RPT_WRK_SQ);
        ZYPEZDItemValueSetter.setValue(headerInfo.shpgOrdRptWrkPk, shpgOrdRptWrkSq);
        ZYPEZDItemValueSetter.setValue(headerInfo.shpgOrdRptPrintRqstSq, shpgOrdRptWrkSq);

        // retail warehouse info
        String rtlWhCd = resultSet.getString(DB_COLUMN_WH_CD);
        setRetailWhAddress(headerInfo, rtlWhCd);

        // set bill to info
        ZYPEZDItemValueSetter.setValue(headerInfo.sellToDsAcctNum, resultSet.getString(DB_COLUMN_BILL_TO_CUST_CD));
        // bill to address
        setShipToBillToAddress(headerInfo, resultSet, SO_CUST_DATA_TP.BILL_TO);

        // set ship to info
        ZYPEZDItemValueSetter.setValue(headerInfo.shipToDsAcctNum, resultSet.getString(DB_COLUMN_SHIP_TO_CUST_CD));
        // ship to address
        setShipToBillToAddress(headerInfo, resultSet, SO_CUST_DATA_TP.SHIP_TO);
        // ship date
        String shipDtTs = resultSet.getString(DB_COLUMN_SHIP_DT_TM_TS);
        if (ZYPCommonFunc.hasValue(shipDtTs) && shipDtTs.length() >= DATE_ENDINDEX) {
            shipDtTs = shipDtTs.substring(DATE_BEGININDEX, DATE_ENDINDEX);
            ZYPEZDItemValueSetter.setValue(headerInfo.shipDtTxt, ZYPDateUtil.formatEzd8ToSysDisp(shipDtTs));
        }

        ZYPEZDItemValueSetter.setValue(headerInfo.cpoOrdNum, resultSet.getString(DB_COLUMN_CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(headerInfo.custIssPoNum, resultSet.getString(DB_COLUMN_CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(headerInfo.tocNm, resultSet.getString(DB_COLUMN_TOC_NM));
        ZYPEZDItemValueSetter.setValue(headerInfo.sceOrdTpNm, resultSet.getString(DB_COLUMN_SCE_ORD_TP_NM));

        // so message
        setSoMessage(headerInfo);

        // reprint
        String soPrtFlg = resultSet.getString(DB_COLUMN_SO_PRT_FLG);
        if (ZYPConstant.FLG_ON_Y.equals(soPrtFlg)) {
            ZYPEZDItemValueSetter.setValue(headerInfo.reprValTxt, reprintCharString);
        }

        String packListTpCd = getSoRptMap(resultSet);
        ZYPEZDItemValueSetter.setValue(headerInfo.packListTpCd, packListTpCd);

        if (PACK_LIST_TP.PARTS.equals(packListTpCd)) {
            setTechInfo(headerInfo, resultSet);
        }

        return headerInfo;
    }

    /**
     * setShipToBillToAddress
     * @param   headerInfo      SHPG_ORD_RPT_WRKTMsg
     * @param   resultSet       ResultSet
     * @param   soCustDataTpCd  String
     * @throws  SQLException
     */
    private void setShipToBillToAddress(SHPG_ORD_RPT_WRKTMsg headerInfo, ResultSet resultSet, String soCustDataTpCd) throws SQLException {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCdM);
        String soNum = resultSet.getString(DB_COLUMN_SO_NUM);
        ssmParam.put(DB_PARAM_SO_NUM, soNum);
        ssmParam.put(DB_PARAM_SO_CUST_DATA_TP_CD, soCustDataTpCd);

        List<Map<String, Object>> mapAddressList = this.ssmBatchClient.queryObjectList("getShipToBillToAddress", ssmParam);
        if (mapAddressList == null || mapAddressList.isEmpty()) {
            return;
        }

        Map<String, Object> mapAddress = mapAddressList.get(0);
        //QC#20246 UPD START
        List<String> addressList = getAddressList(mapAddress, soCustDataTpCd);
        //QC#20246 UPD END
        String soCustLineLocNm01 = (String) mapAddress.get(DB_COLUMN_SO_CUST_LINE_LOC_NM_01);

        if (SO_CUST_DATA_TP.BILL_TO.equals(soCustDataTpCd)) {
            setBillToAddress(headerInfo, soCustLineLocNm01, addressList);
        } else if (SO_CUST_DATA_TP.SHIP_TO.equals(soCustDataTpCd)) {
            setShipToAddress(headerInfo, soCustLineLocNm01, addressList);
        }
    }

    /**
     * getAddressList
     * @param   mapAddressInfo  Map<String, Object>
     */
    //QC#20246 UPD START
    private List<String> getAddressList(Map<String, Object> mapAddressInfo, String soCustDataTpCd) {
    //QC#20246 UPD END

        String ctacPtnrPsnNm = (String) mapAddressInfo.get(DB_COLUMN_CTAC_PTNR_PSN_NM);
        String soCustLineAddr01 = (String) mapAddressInfo.get(DB_COLUMN_SO_CUST_LINE_ADDR_01);
        String soCustLineAddr02 = (String) mapAddressInfo.get(DB_COLUMN_SO_CUST_LINE_ADDR_02);
        String soCustLineAddr03 = (String) mapAddressInfo.get(DB_COLUMN_SO_CUST_LINE_ADDR_03);
        String soCustLineAddr04 = (String) mapAddressInfo.get(DB_COLUMN_SO_CUST_LINE_ADDR_04);
        String ctyAddr = (String) mapAddressInfo.get(DB_COLUMN_CTY_ADDR);
        String stCd = (String) mapAddressInfo.get(DB_COLUMN_ST_CD);
        String postCd = (String) mapAddressInfo.get(DB_COLUMN_POST_CD);

        // Address List
        List<String> adderList = new ArrayList<String>();

        //QC#20246 ADD START
        // check ctacPtnrPsnNm
        if (SO_CUST_DATA_TP.SHIP_TO.equals(soCustDataTpCd) && ZYPCommonFunc.hasValue(ctacPtnrPsnNm)) {
            adderList.add("ATTN: " + ctacPtnrPsnNm);
        }
        //QC#20246 ADD END

        // check soCustLineAddr01
        if (ZYPCommonFunc.hasValue(soCustLineAddr01)) {
            adderList.add(soCustLineAddr01);
        }

        // check soCustLineAddr02
        if (ZYPCommonFunc.hasValue(soCustLineAddr02)) {
            adderList.add(soCustLineAddr02);
        }

        // check soCustLineAddr03
        if (ZYPCommonFunc.hasValue(soCustLineAddr03)) {
            adderList.add(soCustLineAddr03);
        }

        // check soCustLineAddr04
        if (ZYPCommonFunc.hasValue(soCustLineAddr04)) {
            adderList.add(soCustLineAddr04);
        }

        String outsoCustLineAddr05 = "";
        // check ctyAddr
        if (ZYPCommonFunc.hasValue(ctyAddr)) {
            outsoCustLineAddr05 = ctyAddr;
        }
        // check stCd
        if (ZYPCommonFunc.hasValue(stCd)) {
            if (ZYPCommonFunc.hasValue(outsoCustLineAddr05)) {
                outsoCustLineAddr05 = outsoCustLineAddr05 + COMMA;
            }
            outsoCustLineAddr05 = outsoCustLineAddr05 + stCd;
        }
        // check postCd
        if (ZYPCommonFunc.hasValue(postCd)) {
            if (ZYPCommonFunc.hasValue(outsoCustLineAddr05)) {
                outsoCustLineAddr05 = outsoCustLineAddr05 + ONE_BLANK;
            }
            outsoCustLineAddr05 = outsoCustLineAddr05 + postCd;
        }
        if (ZYPCommonFunc.hasValue(outsoCustLineAddr05)) {
            adderList.add(outsoCustLineAddr05);
        }

        return adderList;
    }

    /**
     * setShipToAddress
     * @param   headerInfo          SHPG_ORD_RPT_WRKTMsg
     * @param   soCustLineLocNm01   String
     * @param   addressList         List<String>
     */
    private void setShipToAddress(SHPG_ORD_RPT_WRKTMsg headerInfo, String soCustLineLocNm01, List<String> addressList) {

        // shipToNm
        ZYPEZDItemValueSetter.setValue(headerInfo.shipToNm, soCustLineLocNm01);

        int count = 0;
        // set address 01
        if (addressList.size() > count) {
            ZYPEZDItemValueSetter.setValue(headerInfo.shipToAddr_01, addressList.get(count));
            count++;
        }

        // set address 02
        if (addressList.size() > count) {
            ZYPEZDItemValueSetter.setValue(headerInfo.shipToAddr_02, addressList.get(count));
            count++;
        }

        // set address 03
        if (addressList.size() > count) {
            ZYPEZDItemValueSetter.setValue(headerInfo.shipToAddr_03, addressList.get(count));
            count++;
        }

        // set address 04
        if (addressList.size() > count) {
            ZYPEZDItemValueSetter.setValue(headerInfo.shipToAddr_04, addressList.get(count));
            count++;
        }

        // set address 05
        if (addressList.size() > count) {
            ZYPEZDItemValueSetter.setValue(headerInfo.shipToAddr_05, addressList.get(count));
            count++;
        }

        //QC#20246 ADD START
        // set address 06
        if (addressList.size() > count) {
            ZYPEZDItemValueSetter.setValue(headerInfo.shipToAddr_06, addressList.get(count));
            count++;
        }
        //QC#20246 ADD END
    }

    /**
     * setBillToAddress
     * @param   headerInfo          SHPG_ORD_RPT_WRKTMsg
     * @param   soCustLineLocNm01   String
     * @param   addressList         List<String>
     */
    private void setBillToAddress(SHPG_ORD_RPT_WRKTMsg headerInfo, String soCustLineLocNm01, List<String> addressList) {

        // sellToNm
        ZYPEZDItemValueSetter.setValue(headerInfo.sellToNm, soCustLineLocNm01);

        int count = 0;
        // set address 01
        if (addressList.size() > count) {
            ZYPEZDItemValueSetter.setValue(headerInfo.sellToAddr_01, addressList.get(count));
            count++;
        }

        // set address 02
        if (addressList.size() > count) {
            ZYPEZDItemValueSetter.setValue(headerInfo.sellToAddr_02, addressList.get(count));
            count++;
        }

        // set address 03
        if (addressList.size() > count) {
            ZYPEZDItemValueSetter.setValue(headerInfo.sellToAddr_03, addressList.get(count));
            count++;
        }

        // set address 04
        if (addressList.size() > count) {
            ZYPEZDItemValueSetter.setValue(headerInfo.sellToAddr_04, addressList.get(count));
            count++;
        }

        // set address 05
        if (addressList.size() > count) {
            ZYPEZDItemValueSetter.setValue(headerInfo.sellToAddr_05, addressList.get(count));
            count++;
        }
    }

    /**
     * setRetailWhAddress
     * @param   headerInfo  SHPG_ORD_RPT_WRKTMsg
     * @param   rtlWhCd     String
     */
    private void setRetailWhAddress(SHPG_ORD_RPT_WRKTMsg headerInfo, String rtlWhCd) {

        Map<String, Object> mapAddress = getRetailWhAddress(rtlWhCd);

        String firstLineAddr = (String) mapAddress.get(DB_COLUMN_FIRST_LINE_ADDR);
        String secondLineAddr = (String) mapAddress.get(DB_COLUMN_SCD_LINE_ADDR);
        String thirdLineAddr = (String) mapAddress.get(DB_COLUMN_THIRD_LINE_ADDR);
        String fourthLineAddr = (String) mapAddress.get(DB_COLUMN_FRTH_LINE_ADDR);
        String ctyAddr = (String) mapAddress.get(DB_COLUMN_CTY_ADDR);
        String stCd = (String) mapAddress.get(DB_COLUMN_ST_CD);
        String postCd = (String) mapAddress.get(DB_COLUMN_POST_CD);
        String tenNum = (String) mapAddress.get(DB_COLUMN_TEL_NUM);

        // Source Parts Center address list
        List<String> adderList = new ArrayList<String>();

        // check FirstLineAddr
        if (ZYPCommonFunc.hasValue(firstLineAddr)) {
            adderList.add(firstLineAddr);
        }

        // check outScdLineAddr
        String outScdLineAddr = "";
        // check ScdLineAddr
        if (ZYPCommonFunc.hasValue(secondLineAddr)) {
            outScdLineAddr = secondLineAddr;
        }
        // check ThirdLineAddr
        if (ZYPCommonFunc.hasValue(thirdLineAddr)) {
            if (ZYPCommonFunc.hasValue(outScdLineAddr)) {
                outScdLineAddr = outScdLineAddr + ONE_BLANK;
            }
            outScdLineAddr = outScdLineAddr + thirdLineAddr;
        }
        // check FourthLineAddr
        if (ZYPCommonFunc.hasValue(fourthLineAddr)) {
            if (ZYPCommonFunc.hasValue(outScdLineAddr)) {
                outScdLineAddr = outScdLineAddr + ONE_BLANK;
            }
            outScdLineAddr = outScdLineAddr + fourthLineAddr;
        }
        if (ZYPCommonFunc.hasValue(outScdLineAddr)) {
            adderList.add(outScdLineAddr);
        }

        // check outThirdLineAddr
        String outThirdLineAddr = "";
        // check ctyAddr
        if (ZYPCommonFunc.hasValue(ctyAddr)) {
            outThirdLineAddr = ctyAddr;
        }
        // check stCd
        if (ZYPCommonFunc.hasValue(stCd)) {
            if (ZYPCommonFunc.hasValue(outThirdLineAddr)) {
                outThirdLineAddr = outThirdLineAddr + COMMA;
            }
            outThirdLineAddr = outThirdLineAddr + stCd;
        }
        // check postCd
        if (ZYPCommonFunc.hasValue(postCd)) {
            if (ZYPCommonFunc.hasValue(outThirdLineAddr)) {
                outThirdLineAddr = outThirdLineAddr + ONE_BLANK;
            }
            outThirdLineAddr = outThirdLineAddr + postCd;
        }
        if (ZYPCommonFunc.hasValue(outThirdLineAddr)) {
            adderList.add(outThirdLineAddr);
        }

        // check outFourthLineAddr
        String outFourthLineAddr = "";
        if (ZYPCommonFunc.hasValue(tenNum)) {
            outFourthLineAddr = PHONE + tenNum;
        }
        if (ZYPCommonFunc.hasValue(outFourthLineAddr)) {
            adderList.add(outFourthLineAddr);
        }

        int addrLine = 0;
        // set First Line Addr
        if (adderList.size() > addrLine) {
            ZYPEZDItemValueSetter.setValue(headerInfo.rtlWhDplyAddr_01, adderList.get(addrLine));
            addrLine++;
        }
        // set SecondLineAddr
        if (adderList.size() > addrLine) {
            ZYPEZDItemValueSetter.setValue(headerInfo.rtlWhDplyAddr_02, adderList.get(addrLine));
            addrLine++;
        }
        // set ThirdLineAddr
        if (adderList.size() > addrLine) {
            ZYPEZDItemValueSetter.setValue(headerInfo.rtlWhDplyAddr_03, adderList.get(addrLine));
            addrLine++;
        }
        // set FourthLineAddr
        if (adderList.size() > addrLine) {
            ZYPEZDItemValueSetter.setValue(headerInfo.rtlWhDplyAddr_04, adderList.get(addrLine));
            addrLine++;
        }
    }

    /**
     * getRetailWhAddress.
     * @param   rtlWhCd         String
     * @return  sourceWhInfo    Map<String, Object>
     */
    private Map<String, Object> getRetailWhAddress(String rtlWhCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCdM);
        queryParam.put(DB_PARAM_PROC_DT, this.procStartTsM);
        queryParam.put(DB_PARAM_RTL_WH_CD, rtlWhCd);

        List<Map<String, Object>> result = this.ssmBatchClient.queryObjectList("getRetailWhAddress", queryParam);
        if (result == null || result.isEmpty()) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1041E), this);
            addErrorMsgList(NLXM1041E);
            return null;
        }
        return result.get(0);

    }

    /**
     * setSoMessage
     * @param detailInfo    SHPG_ORD_RPT_WRKTMsg
     */
    private void setSoMessage(SHPG_ORD_RPT_WRKTMsg headerInfo) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCdM);
        ssmParam.put(DB_PARAM_SO_NUM, this.soNumM);

        List<Map<String, Object>> mapSoMessageList = this.ssmBatchClient.queryObjectList("getSoMessage", ssmParam);
        if (mapSoMessageList == null || mapSoMessageList.isEmpty()) {
            return;
        }

        String soMsgCmntTxt = "";
        String soMsgDescTxt = "";
        for (Map<String, Object> mapSoMessage : mapSoMessageList) {
            soMsgDescTxt = (String) mapSoMessage.get(DB_COLUMN_SO_MSG_DESC_TXT);
            if (ZYPCommonFunc.hasValue(soMsgDescTxt)) {
                // START 2020/09/25 [QC#57619, ADD]
                soMsgDescTxt = soMsgDescTxt.replaceAll("\\r\\n", "\n");
                soMsgDescTxt = soMsgDescTxt.replaceAll("\\r", "\n");
                // END 2020/09/25 [QC#57619, ADD]
                soMsgCmntTxt = ZYPCommonFunc.concatString(soMsgCmntTxt, null, soMsgDescTxt);
            }
        }
        ZYPEZDItemValueSetter.setValue(headerInfo.soMsgCmntTxt, soMsgCmntTxt);
    }

    /**
     * setDetailInfo.
     * @param   headerInfo  SHPG_ORD_RPT_WRKTMsg
     * @param   detailList  List<SHPG_ORD_RPT_WRKTMsg>
     * @param   resultSet   ResultSet
     */
    private void setDetailInfo(SHPG_ORD_RPT_WRKTMsg headerInfo, List<SHPG_ORD_RPT_WRKTMsg> detailList, ResultSet resultSet) throws SQLException {

        SHPG_ORD_RPT_WRKTMsg detailInfo = getDetailInfo(headerInfo, resultSet);
        detailList.add(detailInfo);

        // serial check
        String serNumTakeFlg = resultSet.getString(DB_COLUMN_SER_NUM_TAKE_FLG);
        if (ZYPConstant.FLG_ON_Y.equals(serNumTakeFlg)) {
            String dsSoLineStsCd = resultSet.getString(DB_COLUMN_DS_SO_LINE_STS_CD);
            List<String> serialList = getSOSerialNumber(detailInfo, dsSoLineStsCd);

            setSoSerialInfo(headerInfo, detailList, serialList);
        }

    }

    /**
     * getDetailInfo.
     * @param   headerInfo  SHPG_ORD_RPT_WRKTMsg
     * @param   resultSet   ResultSet
     * @return  detailInfo  SHPG_ORD_RPT_WRKTMsg
     */
    private SHPG_ORD_RPT_WRKTMsg getDetailInfo(SHPG_ORD_RPT_WRKTMsg headerInfo, ResultSet resultSet) throws SQLException {

        SHPG_ORD_RPT_WRKTMsg detailInfo = new SHPG_ORD_RPT_WRKTMsg();
        EZDMsg.copy(headerInfo, null, detailInfo, null);

        ZYPEZDItemValueSetter.setValue(detailInfo.mdseCd, resultSet.getString(DB_COLUMN_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(detailInfo.soSlpNum, resultSet.getString(DB_COLUMN_SO_SLP_NUM));
        ZYPEZDItemValueSetter.setValue(detailInfo.mdseDescShortTxt, resultSet.getString(DB_COLUMN_MDSE_DESC_SHORT_TXT));

        BigDecimal rqstOrdQty = resultSet.getBigDecimal(DB_COLUMN_SHPG_QTY);
        ZYPEZDItemValueSetter.setValue(detailInfo.rqstOrdQtyTxt, convertText(rqstOrdQty));

        BigDecimal shpgQty = resultSet.getBigDecimal(DB_COLUMN_SHIP_QTY);
        ZYPEZDItemValueSetter.setValue(detailInfo.shpgQtyTxt, convertText(shpgQty));

        BigDecimal boQty = resultSet.getBigDecimal("BO_QTY");
        ZYPEZDItemValueSetter.setValue(detailInfo.boQtyDplyTxt, convertText(boQty));

        BigDecimal shpgWt = resultSet.getBigDecimal(DB_COLUMN_TOT_SHPG_WT);
        setWeightItem(shpgWt, detailInfo.shipWtDplyTxt_01, detailInfo.shipWtDplyTxt_02);

        // set page number
        setPdfPageNumber(detailInfo, ZYPConstant.FLG_OFF_N);

        // total
        totOrdQty = addQty(totOrdQty, rqstOrdQty);
        totShipQty = addQty(totShipQty, shpgQty);
        totBoQty = addQty(totBoQty, boQty);
        totShpgWt = addQty(totShpgWt, shpgWt);

        // not null column dummy data set
        ZYPEZDItemValueSetter.setValue(detailInfo.serNumTakeFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(detailInfo.soConfigFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(detailInfo.configItemFlg, ZYPConstant.FLG_OFF_N);

        return detailInfo;
    }

    /**
     * getSOSerialNumber
     * @param   headerInfo      SHPG_ORD_RPT_WRKTMsg
     * @param   dsSoLineStsCd   String
     * @return  serialList      List<String>
     */
    private List<String> getSOSerialNumber(SHPG_ORD_RPT_WRKTMsg detailInfo, String dsSoLineStsCd) {

        List<String> serialList = new ArrayList<String>();

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCdM);
        ssmParam.put(DB_PARAM_SO_NUM, detailInfo.soNum);
        ssmParam.put(DB_PARAM_SO_SLP_NUM, detailInfo.soSlpNum);
        ssmParam.put(DB_PARAM_MDSE_CD, detailInfo.mdseCd);

        String statementId;
        if (DS_SO_LINE_STS.SHIPPED.equals(dsSoLineStsCd)) {
            statementId = "getSOSerialOfShipped";
        } else {
            statementId = "getSOSerialOfOhter";
            ssmParam.put(DB_PARAM_WH_PICK_FLG, ZYPConstant.FLG_ON_Y);
            ssmParam.put(DB_PARAM_ORD_ASG_FLG, ZYPConstant.FLG_ON_Y);
        }

        List<Map<String, Object>> mapSerialList = this.ssmBatchClient.queryObjectList(statementId, ssmParam);
        if (mapSerialList == null || mapSerialList.isEmpty()) {
            return serialList;
        }

        // set serial number list
        for (Map<String, Object> mapSerial : mapSerialList) {
            String serNum = (String) mapSerial.get(DB_COLUMN_SER_NUM);
            if (ZYPCommonFunc.hasValue(serNum)) {
                serialList.add(serNum);
            }
        }

        return serialList;
    }

    /**
     * setSoSerialInfo.
     * @param   headerInfo  SHPG_ORD_RPT_WRKTMsg
     * @param   detailList  List<SHPG_ORD_RPT_WRKTMsg>
     * @param   serialList  List<String>
     */
    private void setSoSerialInfo(SHPG_ORD_RPT_WRKTMsg headerInfo, List<SHPG_ORD_RPT_WRKTMsg> detailList, List<String> serialList) {

        SHPG_ORD_RPT_WRKTMsg serialInfo = null;

        for (String serial : serialList) {
            serialInfo = new SHPG_ORD_RPT_WRKTMsg();
            EZDMsg.copy(headerInfo, null, serialInfo, null);

            ZYPEZDItemValueSetter.setValue(serialInfo.serNum, serial);

            // not null column dummy data set
            ZYPEZDItemValueSetter.setValue(serialInfo.serNumTakeFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(serialInfo.soConfigFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(serialInfo.configItemFlg, ZYPConstant.FLG_OFF_N);

            setPdfPageNumber(serialInfo, ZYPConstant.FLG_ON_Y);
            detailList.add(serialInfo);
        }
    }

    /**
     * setDetailInfoBackOrder.
     * @param   headerInfo  SHPG_ORD_RPT_WRKTMsg
     * @param   detailList  SHPG_ORD_RPT_WRKTMsg
     */
    private void setDetailInfoBackOrder(SHPG_ORD_RPT_WRKTMsg headerInfo, List<SHPG_ORD_RPT_WRKTMsg> detailList) throws SQLException {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd",this.glblCmpyCdM);
        params.put("soNum",headerInfo.soNum.getValue());

        
        List<Map<String, Object>> mapBoRecordList= (List<Map<String, Object>>)this.ssmBatchClient.queryObjectList("getBoRecord", params);
        // RWS Completion
        for (Map<String, Object> mapBoRecord : mapBoRecordList) {
            SHPG_ORD_RPT_WRKTMsg detailInfo = new SHPG_ORD_RPT_WRKTMsg();
            EZDMsg.copy(headerInfo, null, detailInfo, null);

            ZYPEZDItemValueSetter.setValue(detailInfo.mdseCd, (String) mapBoRecord.get(DB_COLUMN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(detailInfo.soSlpNum, (String) mapBoRecord.get(DB_COLUMN_SO_SLP_NUM));
            ZYPEZDItemValueSetter.setValue(detailInfo.mdseDescShortTxt, (String) mapBoRecord.get(DB_COLUMN_MDSE_DESC_SHORT_TXT));

            BigDecimal rqstOrdQty = BigDecimal.ZERO;
            ZYPEZDItemValueSetter.setValue(detailInfo.rqstOrdQtyTxt, convertText(rqstOrdQty));

            BigDecimal shpgQty = BigDecimal.ZERO;
            ZYPEZDItemValueSetter.setValue(detailInfo.shpgQtyTxt, convertText(shpgQty));

            BigDecimal boQty = (BigDecimal) mapBoRecord.get("BO_QTY");
            ZYPEZDItemValueSetter.setValue(detailInfo.boQtyDplyTxt, convertText(boQty));

            BigDecimal shpgWt = BigDecimal.ZERO;
            setWeightItem(shpgWt, detailInfo.shipWtDplyTxt_01, detailInfo.shipWtDplyTxt_02);

            // set page number
            setPdfPageNumber(detailInfo, ZYPConstant.FLG_OFF_N);

            // total
            totOrdQty = addQty(totOrdQty, rqstOrdQty);
            totShipQty = addQty(totShipQty, shpgQty);
            totBoQty = addQty(totBoQty, boQty);
            totShpgWt = addQty(totShpgWt, shpgWt);

            // not null column dummy data set
            ZYPEZDItemValueSetter.setValue(detailInfo.serNumTakeFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(detailInfo.soConfigFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(detailInfo.configItemFlg, ZYPConstant.FLG_OFF_N);
            
            detailList.add(detailInfo);
            
            // serial check
            String serNumTakeFlg = (String) mapBoRecord.get(DB_COLUMN_SER_NUM_TAKE_FLG);
            if (ZYPConstant.FLG_ON_Y.equals(serNumTakeFlg)) {
                String dsSoLineStsCd = (String) mapBoRecord.get(DB_COLUMN_DS_SO_LINE_STS_CD);
                List<String> serialList = getSOSerialNumber(detailInfo, dsSoLineStsCd);

                setSoSerialInfo(headerInfo, detailList, serialList);
            }
        }
    }
    
    /**
     * addQty
     * @param   one     BigDecimal
     * @param   two     BigDecimal
     * @return  result  BigDecimal
     */
    private BigDecimal addQty(BigDecimal one, BigDecimal two) {
        BigDecimal num1 = one;
        if (!ZYPCommonFunc.hasValue(num1)) {
            num1 = BigDecimal.ZERO;
        }
        BigDecimal num2 = two;
        if (!ZYPCommonFunc.hasValue(num2)) {
            num2 = BigDecimal.ZERO;
        }

        return num1.add(num2);
    }

    /**
     * convertText.
     * @param   value  BigDecimal
     * @return  text   String
     */
    private String convertText(BigDecimal value) {
        BigDecimal num = value;
        if (!ZYPCommonFunc.hasValue(value)) {
            num = BigDecimal.ZERO;
        }
        return ZYPFormatUtil.formatNumToSysDisp(num);
    }


    /**
     * setWeightItem.
     * @param   value               BigDecimal
     * @param   valintegerValItemue EZDTStringItem
     * @param   decimalValItem      EZDTStringItem
     */
    private void setWeightItem(BigDecimal value, EZDTStringItem integerValItem, EZDTStringItem decimalValItem) {

        BigDecimal num = value;
        if (!ZYPCommonFunc.hasValue(num) || BigDecimal.ZERO.equals(num)) {
            ZYPEZDItemValueSetter.setValue(integerValItem, BigDecimal.ZERO.toPlainString());
        } else {
            BigDecimal integerVal = num.setScale(SCALE_ZERO, RoundingMode.DOWN);
            ZYPEZDItemValueSetter.setValue(integerValItem, convertText(integerVal));

            num.setScale(SCALE_WEIGHT);
            String wtDplyTxt = num.toPlainString();
            int index = wtDplyTxt.indexOf(SCALE_SEPARATOR);
            if (index > 0) {
                ZYPEZDItemValueSetter.setValue(decimalValItem, wtDplyTxt.substring(index));
            }
        }
    }


    /**
     * setPdfPageNumber
     * @param   detailInfo  SHPG_ORD_RPT_WRKTMsg
     * @param   serialFlg   String
     */
    private void setPdfPageNumber(SHPG_ORD_RPT_WRKTMsg detailInfo, String SerialFlg) {

        BigDecimal maxLineNum;
        String packListTpCd = detailInfo.packListTpCd.getValue();
        if (BigDecimal.ONE.compareTo(pageNum) == 0) {
            if (PACK_LIST_TP.MDSE.equals(packListTpCd)) {
                maxLineNum = lineNumOf1stPage;
            } else {
                maxLineNum = partsLineNumOf1stPage;
            }
        } else {
            if (PACK_LIST_TP.MDSE.equals(packListTpCd)) {
                maxLineNum = lineNumOf2ndPage;
            } else {
                maxLineNum = partsLineNumOf2ndPage;
            }
        }

        // QC#25818 20180531 Start
        BigDecimal addLineNum = BigDecimal.ONE;
        // BigDecimal addLineNum = null;
        // if (ZYPConstant.FLG_ON_Y.equals(SerialFlg)) {
        //     addLineNum = BigDecimal.ONE;
        // } else {
        //     // calculation add line
        //     BigDecimal lineDescLength = new BigDecimal(detailInfo.mdseDescShortTxt.getValue().length());
        //     addLineNum = lineDescLength.divide(numOfDescLtr, 0, BigDecimal.ROUND_UP);
        // }
        // QC#25818 20180531 End

        // add line
        pageLineNum = pageLineNum.add(addLineNum);

        // Page break decision
        if (maxLineNum.compareTo(pageLineNum) < 0) {
            pageNum = pageNum.add(BigDecimal.ONE);
            pageLineNum = addLineNum;
        }

        ZYPEZDItemValueSetter.setValue(detailInfo.shpgOrdRptPgNum, pageNum);
        ZYPEZDItemValueSetter.setValue(detailInfo.shpgOrdRptLineNum, pageLineNum);
    }

    /**
     * outputSoReportWork.
     * @param   detailInfoList  List<SHPG_ORD_RPT_WRKTMsg>
     */
    private void outputSoReportWork(List<SHPG_ORD_RPT_WRKTMsg> detailInfoList) {

        int insCnt = 0;
        for (SHPG_ORD_RPT_WRKTMsg detailInfo : detailInfoList) {
            // total info set
            setTotalToDetailInfo(detailInfo);
            if (insCnt > 0) {
                // PK update
                BigDecimal shpgOrdRptWrkSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SHPG_ORD_RPT_WRK_SQ);
                ZYPEZDItemValueSetter.setValue(detailInfo.shpgOrdRptWrkPk, shpgOrdRptWrkSq);
            }

            EZDTBLAccessor.insert(detailInfo);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(detailInfo.getReturnCode())) {
                EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1017E), this);
                addErrorMsgList(NLXM1017E);
                break;
            }
            insCnt++;
        }
    }

    /**
     * setTotalToDetailInfo.
     * @param   headerInfo  SHPG_ORD_RPT_WRKTMsg
     */
    private void setTotalToDetailInfo(SHPG_ORD_RPT_WRKTMsg detailInfo) {
        ZYPEZDItemValueSetter.setValue(detailInfo.totOrdQtyDplyTxt, convertText(totOrdQty));
        ZYPEZDItemValueSetter.setValue(detailInfo.totShipQtyDplyTxt, convertText(totShipQty));
        ZYPEZDItemValueSetter.setValue(detailInfo.totBoQtyDplyTxt, convertText(totBoQty));
        setWeightItem(totShpgWt, detailInfo.totShipWtDplyTxt_01, detailInfo.totShipWtDplyTxt_02);
    }

    /**
     * setTechInfo.
     * @param   headerInfo  SHPG_ORD_RPT_WRKTMsg
     * @param   resutlSet   ResultSet
     */
    private void setTechInfo(SHPG_ORD_RPT_WRKTMsg headerInfo, ResultSet resutlSet) throws SQLException {

        String prchReqNum = resutlSet.getString(DB_COLUMN_PRCH_REQ_NUM);
        ZYPEZDItemValueSetter.setValue(headerInfo.prchReqNum, prchReqNum);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCdM);
        queryParam.put(DB_PARAM_PRCH_REQ_NUM, prchReqNum);

        List<Map<String, Object>> mapResultList = this.ssmBatchClient.queryObjectList("getTechInfo", queryParam);
        if (mapResultList == null || mapResultList.isEmpty()) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1043E), this);
            addErrorMsgList(NLXM1043E);
            return;
        }

        Map<String, Object> mapResult = mapResultList.get(0);
        ZYPEZDItemValueSetter.setValue(headerInfo.prchReqTpDescTxt, (String) mapResult.get(DB_COLUMN_PRCH_REQ_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(headerInfo.fullPsnNm, (String) mapResult.get(DB_COLUMN_FULL_PSN_NM));
    }

    /**
     * getSoRptMap.
     * @param   resutlSet       ResultSet
     * @return  packListTpCd    String
     */
    private String getSoRptMap(ResultSet resutlSet) throws SQLException {

        String packListTpCd = "";
        String sceOrdTpCd = resutlSet.getString(DB_COLUMN_SCE_ORD_TP_CD);
        String rtlWhCatgCd = resutlSet.getString(DB_COLUMN_RTL_WH_CATG_CD);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCdM);
        queryParam.put(DB_PARAM_SCE_ORD_TP_CD, sceOrdTpCd);
        queryParam.put(DB_PARAM_RTL_WH_CATG_CD, rtlWhCatgCd);

        List<Map<String, Object>> mapResultList = this.ssmBatchClient.queryObjectList("getSoRptMap", queryParam);
        if (mapResultList == null || mapResultList.isEmpty()) {
            EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1042E), this);
            addErrorMsgList(NLXM1042E);
            return null;
        }
        Map<String, Object> mapResult = mapResultList.get(0);
        packListTpCd = (String) mapResult.get(DB_COLUMN_PACK_LIST_TP_CD);

        return packListTpCd;
    }

    /**
     * Remove Purge Record
     */
    private void removeShpgOrdRptWrk() throws SQLException {

       //Check Target Date Record
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        String purgeDtTm = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_FORMAT_14);
        purgeDtTm = ZYPDateUtil.addDays(purgeDtTm, purgeDt.negate().intValue());
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCdM);
        paramMap.put(DB_PARAM_PRNT_PURGE_TS, purgeDtTm);

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setMaxRows(0);
            preparedStatement = ssmLowLev.createPreparedStatement("getRemoveShpgOrdRptWrkData", paramMap, execParam);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                SHPG_ORD_RPT_WRKTMsg tMsg = new SHPG_ORD_RPT_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCdM);
                ZYPEZDItemValueSetter.setValue(tMsg.shpgOrdRptWrkPk, rs.getBigDecimal(DB_COLUMN_SHPG_ORD_RPT_WRK_PK));

                // Delete Target Date Record
                EZDTBLAccessor.remove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    EZDDebugOutput.printLog(1, S21MessageFunc.clspGetMessage(NLXM1046E), this);
                    addErrorMsgList(NLXM1046E);
                }
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
        }
    }
}
