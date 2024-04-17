/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL4500.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009   SRA             T.Chijimatsu    Create          N/A
 * 04/05/2010   Fujitsu         T.Ishii         Update          5206
 * 08/05/2013   Fujitsu         N.Sugiura       Update          QC1469
 * 08/01/2016   CITS            S.Endo          Update          QC#10840
 *</pre>
 ***/
public interface NMAL4500Constant {
    
    String SCREEN_ID = "NMAL4500Scrn00";
    
    // one recode read no
    int ONE_ROW = 0;
    // DEL START 2013/08/05 QC1469
    // cuntry USA
    // String LIST_COUNTRY_USA = "US";
    // String LIST_COUNTRY_USA = CTRY.UNITED_STATES_OF_AMERICA;
    // DEL END 2013/08/05 QC1469
    // Sequence
//    String SEQ_VND_PK = "VND_SQ";
//    String SEQ_CMPY_PK = "CMPY_SQ";
//    String SEQ_PTY_LOC_WRK_PK = "PTY_LOC_WRK_SQ";
//    String SEQ_LOC_USG_PK = "LOC_USG_SQ";
//    String SEQ_PTY_PK = "PTY_SQ";
    String SEQ_VND_PK = ZYPOracleSeqAccessor.VND_SQ;
    String SEQ_CMPY_PK = ZYPOracleSeqAccessor.CMPY_SQ;
    String SEQ_PTY_LOC_WRK_PK = ZYPOracleSeqAccessor.PTY_LOC_WRK_SQ;
    String SEQ_LOC_USG_PK = ZYPOracleSeqAccessor.LOC_USG_SQ;
    String SEQ_SHIP_TO_CUST = ZYPOracleSeqAccessor.SHIP_TO_CUST_SQ;
    
    //apvlStsCd
//    String AP = "AP";
    String AP = APVL_STS.APPROVED;
    
    // EEF_TRUE_DATE(MAX)
    String EFF_THRU_DATE = "99991231";
    
    //RGTN_STS_CD
//    String P20 = "P20";
    String P20 = RGTN_STS.READY_FOR_ORDER_TAKING;
    
    
    //embgoFlg type
//    String embgoFlg_Y = "Y";
//    String embgoFlg_N = "N";
    String embgoFlg_Y = ZYPConstant.FLG_ON_Y;
    String embgoFlg_N = ZYPConstant.FLG_OFF_N;
    
    //locRoleTpCd type
//    String ROLE_VENDOR ="VND";
    String ROLE_VENDOR =LOC_ROLE_TP.VENDOR;
    
    //locGrpCd type
//    String GRP_VENDOR ="2";
    String GRP_VENDOR =LOC_GRP.VENDOR;
    
    // radio checkbox flag
    String CHECK_BOX_ON = "Y";
    String CHECK_BOX_OFF = "N";
    String RADIO_VALUE_Y = "Y";
    String RADIO_VALUE_N = "N";
    
    //Minimam row count
    // 10/21/2015 mod start
    // int MIN_LOC_ROW = 10;
    int MIN_LOC_ROW = 1;
    // 10/21/2015 mod end
    
    //VND ADDRESS check screen name
    String VND_CHECK = "VND_CHECK";
    
    //DB UPDATE TYPE (local constant)
    String NONE = "N";
    String UPDATE = "U";
    String CREATE = "C";
    String REMOVE = "R";
    String LOGICAL_REMOVE = "LR";
    
    // for SHIP_TO_CUST
    String N = "N";
    String VND = ROLE_VENDOR;
    String NUM_2 = GRP_VENDOR;
    
    // for Affiliation
    int AFFILIATION_NUM = 1;
    
    /** Environment DB Error "NZZM0003E" */
    String ENVIRONMENT_DB_ACCESS_ERROR = "NZZM0003E";
    
    int MAX_VND_CD_LEN_OF_OUTBOUND_CARRIER = 4;
 
    // <defect#5206 T.ishii 20100405>
    String SUFFIX_VND_CD_FOR_IN_HOUSE_KITTING_OR_IN_HOUSE_REFURBISH = "V";
    /** VarcharConst Key */
    public static final String NMAL4500_ADDR_CHK_FLG = "NMAL4500_ADDR_CHK_FLG";
    /** NO_ERROR */
    public static final String NMZC0030_NO_ERROR = "0";

    /** SUGGESTED */
    public static final String NMZC0030_SUGGESTED = "1";
    /** ERROR */
    public static final String NMZC0030_ERROR = "9";
    /** Error Message: [@] is not exist in Master. */
    public static final String NMAM8454E = "NMAM8454E";

    /** The corresponding [@] does not exist. */
    public static final String NMAM0039E = "NMAM0039E";

}
