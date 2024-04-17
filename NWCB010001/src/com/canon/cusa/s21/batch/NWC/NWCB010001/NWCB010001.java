/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB010001;

import static com.canon.cusa.s21.batch.NWC.NWCB010001.constant.NWCB010001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import parts.common.EZDMsg;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_TRX_BALTMsg;
import business.db.CONSL_BILLTMsg;
import business.db.INVTMsg;
import business.db.INV_PRT_CTRLTMsg;
import business.db.INV_PRT_FLEET_CHRG_DTLTMsg;
import business.db.INV_PRT_FLEET_CHRG_DTLTMsgArray;
import business.db.INV_PRT_FLEET_LOCTMsg;
import business.db.INV_PRT_FLEET_LOCTMsgArray;
import business.db.INV_PRT_FLEET_MACHTMsg;
import business.db.INV_PRT_FLEET_MACHTMsgArray;
import business.db.INV_PRT_FLEET_MTRTMsg;
import business.db.INV_PRT_FLEET_MTRTMsgArray;
import business.db.INV_PRT_FLEET_SMRY_MTRTMsg;
import business.db.INV_PRT_FLEET_SMRY_MTRTMsgArray;
import business.db.INV_PRT_FLEET_SMRY_XSTMsg;
import business.db.INV_PRT_FLEET_SMRY_XSTMsgArray;
import business.db.INV_PRT_FLEET_SUB_TOTTMsg;
import business.db.INV_PRT_FLEET_SUB_TOTTMsgArray;
import business.db.INV_PRT_HDRTMsg;
import business.db.INV_PRT_HDRTMsgArray;
import business.db.INV_PRT_MAINT_CHRG_DTLTMsg;
import business.db.INV_PRT_MAINT_CHRG_DTLTMsgArray;
import business.db.INV_PRT_MAINT_MACHTMsg;
import business.db.INV_PRT_MAINT_MACHTMsgArray;
import business.db.INV_PRT_MAINT_MTRTMsg;
import business.db.INV_PRT_MAINT_MTRTMsgArray;
import business.db.INV_PRT_MAINT_SUB_TOTTMsg;
import business.db.INV_PRT_MAINT_SUB_TOTTMsgArray;
import business.db.INV_PRT_MAINT_XS_MTRTMsg;
import business.db.INV_PRT_MAINT_XS_MTRTMsgArray;
import business.db.INV_PRT_SLS_PART_DTLTMsg;
import business.db.INV_PRT_SLS_PART_DTLTMsgArray;
import business.db.INV_PRT_SLS_PART_SUB_TOTTMsg;
import business.db.INV_PRT_SLS_PART_SUB_TOTTMsgArray;
import business.db.INV_PRT_SMRYTMsg;
import business.db.INV_PRT_SMRYTMsgArray;
import business.db.LINE_BIZ_TPTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_InstructionListPMsg;
import business.parts.NMZC610001_InstructionListPMsgArray;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.batch.NWC.NWCB010001.constant.NWCB010001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_PRINT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_CHRG_CPLT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL_RSN_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_VCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_INV_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SLS_TAX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TAX_PRNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.EASY_PACK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRINT_STYLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PROC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_BAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_BR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_CTRL_REC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_DTL_FMT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_SMRY_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_MERGE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.USG_INV_DESC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRINT_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.internal.S21BatchTransactionQuery;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * Invoice Print Data Creation Batch
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 10/28/2015   Fujitsu        T.Murai          Create          N/A
 * 01/19/2016   Fujitsu        H.Nagashima      Update          QC#3223
 * 01/20/2016   Fujitsu        H.Nagashima      Update          QC#3385
 * 02/22/2016   SRAA           K.Aratani        Update          QC#3138
 * 03/15/2016   SRAA           K.Aratani        Update          QC#5527
 * 03/25/2016   Fujitsu        H.Nagashima      Update          QC#6059
 * 03/30/2016   SRAA           K.Aratani        Update          QC#6294
 * 04/22/2016   SRAA           K.Aratani        Update          QC#7561
 * 06/01/2016   SRAA           K.Aratani        Update          QC#9356
 * 06/23/2016   SRAA           K.Aratani        Update          QC#10850
 * 07/26/2016   SRAA           K.Aratani        Update          QC#6934
 * 08/12/2016   SRAA           K.Aratani        Update          QC#9356
 * 08/20/2016   SRAA           K.Aratani        Update          QC#13710
 * 08/26/2016   SRAA           K.Aratani        Update          QC#13929
 * 10/25/2016   Fujitsu        H.Nagashima      Update          QC#13358
 * 11/21/2016   SRAA           K.Aratani        Update          QC#16034
 * 01/09/2017   SRAA           K.Aratani        Update          QC#16851
 * 02/07/2017   SRAA           K.Aratani        Update          QC#17491
 * 02/14/2017   SRAA           K.Aratani        Update          QC#17577
 * 02/27/2017   Fujitsu        N.Aoyama         Update          QC#16575
 * 03/14/2017   Fujitsu        H.Nagashima      Update          QC#17962
 * 05/08/2017   Fujitsu        H.Nagashima      Update          QC#18223
 * 05/17/2017   Fujitsu        S.Ohki           Update          RS#8350
 * 05/19/2017   SRAA           K.Aratani        Update          QC#18677
 * 05/19/2017   SRAA           K.Aratani        Update          QC#18660
 * 06/16/2017   SRAA           K.Aratani        Update          QC#19274
 * 09/11/2017   Fujitsu        H.Ikeda          Update          QC#20971
 * 09/12/2017   Fujitsu        S.Iidaka         Update          QC#20888
 * 09/26/2017   Fujitsu        H.Nagashima      Update          QC#20788
 * 10/20/2017   SRAA           K.Aratani        Update          QC#21990
 * 2017/10/26   Fujitsu        H.Ikeda          Update          QC#20141
 * 11/22/2017   SRAA           K.Aratani        Update          QC#22278
 * 12/26/2017   SRAA           K.Aratani        Update          QC#18371
 * 01/09/2017   Hitachi        E.Kameishi       Update          QC#20312
 * 01/28/2017   Fujitsu        Mz.Takahashi     Update          QC#20141
 * 04/12/2017   SRAA           K.Aratani        Update          QC#25491
 * 04/12/2018   Fujitsu        Mz.Takahashi     Update          QC#25431
 * 05/25/2018   Fujitsu        T.Aoi            Update          QC#21841
 * 06/06/2018   Fujitsu        T.Aoi            Update          QC#21841-1
 * 06/21/2018   SRAA           K.Aratani        Update          QC#26826, 26846, 26881, 26919
 * 07/17/2018   Hitachi        E.Kameishi       Update          QC#27007
 * 07/25/2018   Hitachi        E.Kameishi       Update          QC#27068
 * 08/16/2018   Fujitsu        T.Aoi            Update          QC#27438
 * 08/20/2018   CITS           M.Naito          Update          QC#13309
 * 08/22/2018   Fujitsu        T.Aoi            Update          QC#27443
 * 09/04/2018   Fujitsu        T.Aoi            Update          QC#28051
 * 09/10/2018   Fujitsu        T.Ogura          Update          QC#27999
 * 10/11/2018   Fujitsu        T.Aoi            Update          QC#28590
 * 10/23/2018   Fujitsu        Mz.Takahashi     Update          QC#27069
 * 10/24/2018   Fujitsu        Y.Matsui         Update          QC#28894
 * 11/14/2018   Hitachi        K.Fujimoto       Update          QC#28627
 * 11/17/2018   Fujitsu         H.Kumagai       Update          QC#27954
 * 01/10/2019   Fujitsu        Mz.Takahashi     Update          QC#29371 SRNO15
 * 01/15/2019   Fujitsu        M.Ohno           Update          QC#29371 SRNO13
 * 01/22/2019   Fujitsu        Y.Kanefusa       Update          QC#29371 SRNO6
 * 01/26/2018   Fujitsu        Mz.Takahashi     Update          QC#29672
 * 01/26/2019   Fujitsu        Hd.Sugawara      Update          QC#30071
 * 01/28/2019   Fujitsu        M.Ishii          Update          QC#30077
 * 2019/01/28   Fujitsu        M.Yamada         Update          QC#29371 SRNO7
 * 02/01/2019   Fujitsu        Y.Kanefusa       Update          QC#29371 SRNO11
 * 02/05/2019   Fujitsu        M.Ohno           Update          QC#29371 SRNO7
 * 02/06/2019   Fujitsu        R.Nakamura       Update          QC#29371
 * 02/06/2019   Fujitsu        S.Takami         Update          QC#29371 SRNO8
 * 02/06/2019   Fujitsu        S.Yamamoto       Update          QC#29371
 * 02/07/2019   Hitachi        K.Fujimoto       Update          QC#29371
 * 02/08/2019   Fujitus        K.Ishizuka       Update          QC#30144
 * 02/18/2019   Fujitsu        Y.Kanefusa       Update          QC#30346
 * 2019/02/20   Fujitsu        M.Yamada         Update          QC#30345
 * 2019/02/22   SRAA           K.Aratani        Update          QC#30491, QC#30494
 * 2019/03/07   Fujitsu        H.Ikeda          Update          QC#30687
 * 2019/03/27   Fujitsu        M.Yamada         Update          QC#30852
 * 2019/03/28   Fujitsu        Y.Kanefusa       Update          QC#30907
 * 2019/04/04   Fujitsu        K.Kato           Update          QC#30756
 * 2019/04/16   Fujitsu        Y.Kanefusa       Update          QC#31177
 * 2019/04/24   Fujitsu        Y.Kanefusa       Update          QC#31273
 * 2019/05/07   Fujitsu        T.Murai          Update          QC#50078
 * 2019/05/10   Fujitsu        T.Murai          Update          QC#50148
 * 2019/05/13   Fujitsu        Y.Kanefusa       Update          QC#50104
 * 2019/05/13   Fujitsu        Mz.Takahashi     Update          QC#50149
 * 2019/05/30   Fujitsu        Mz.Takahashi     Update          QC#50580
 * 2019/06/04   Fujitsu        Mz.Takahashi     Update          QC#50658
 * 2019/06/04   Fujitsu        T.Murai          Update          QC#50681,50689
 * 2019/06/12   Fujitsu        Mz.Takahashi     Update          QC#50817
 * 2019/06/14   Fujitsu        Mz.Takahashi     Update          QC#50844
 * 2019/06/25   Fujitsu        Mz.Takahashi     Update          QC#50883
 * 2019/07/06   Fujitsu        Mz.Takahashi     Update          QC#50885
 * 2019/07/20   Fujitsu        S.Kosaka         Update          QC#51811
 * 2019/07/16   Fujitsu        Y.Kanefusa       Update          QC#51634
 * 2019/07/20   Fujitsu        Y.Kanefusa       Update          QC#51829
 * 2019/07/31   Fujitsu        Y.Kanefusa       Update          QC#52209
 * 2019/08/20   Fujitsu        Y.Kanefusa       Update          QC#52868
 * 2019/08/22   Fujitsu        Mz.Takahashi     Update          QC#52928
 * 2019/08/29   Fujitsu        Y.Kanefusa       Update          QC#52618
 * 2019/09/04   Fujitsu         S.Takami        Update          QC#53254
 * 2019/09/07   Fujitsu        Mz.Takahashi     Update          QC#53026
 * 2019/09/13   Fujitsu        Y.Kanefusa       Update          QC#53015
 * 2019/09/17   Fujitsu        Y.Kanefusa       Update          QC#53014
 * 2019/10/10   Fujitsu        Y.Kanefusa       Update          QC#53014
 * 2019/10/11   Fujitsu        M.Ohno & Taka    Update          QC#53888
 * 2019/11/07   Fujitsu        Y.Kanefusa       Update          QC#54343
 * 2019/11/02   Fujitsu        M.Ohno           Update          QC#53873
 * 2019/11/11   Fujitsu        Y.Kanefusa       Update          QC#54520
 * 2019/12/02   Fujitsu         Y.Kanefusa      Update          QC#53598
 * 2020/06/23   CITS           K.Ogino          Update          QC#56943
 * 2021/02/23   CITS           K.Ogino          Update          QC#57344,QC#58231
 * 2021/04/23   CITS           L.Mandanas       Update          QC#58350
 * 2021/09/23   CITS           L.Mandanas       Update          QC#58350
 * 2021/12/13   CITS           K.Ogino          Update          QC#59356
 * 2022/03/29   CITS           L.Mandanas       Update          QC#58350
 * 2022/06/20   Hitachi        K.Kishimoto      Update          QC#60205
 * 2022/07/20   Hitachi        R.Onozuka        Update          QC#59669
 * 2022/08/03   Hitachi        D.Yoshida        Update          QC#60129
 * 2022/09/01   CITS           L.Mandanas       Update          QC#58350
 * 2022/09/26   Hitachi        R.Onozuka        Update          QC#60624
 * 2022/11/11   CITS           L.Mandanas       Update          QC#60802
 * 2023/07/18   Hitachi        T.Doi            Update          QC#61421
 * 2023/12/15   Hitachi        R.Takau          Update          QC#61584
 * 2024/01/11   Hitachi        H.Watanabe       Update          QC#61468
 *</pre>
 */
public class NWCB010001 extends S21BatchMain {

    /** Normal Counter */
    private int normCnt = 0;

    /** Error Counter */
    private int errCnt = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Timsstamp */
    private String currentSystemTs = null;
    
    /** Process Mode */
    private String procMode = null;

    /** Term Code */
    private TERM_CD termCd = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Company 1st Address */
    private String cmpyFrstAddr = null;

    /** Company 2nd Address */
    private String cmpyScdAddr = null;

    /** Company City */
    private String cmpyCtyAddr = null;

    /** Company State */
    private String cmpyState = null;

    /** Company Post */
    private String cmpyPost = null;

    /** Company Tel */
    private String cmpyTel = null;

    /** Company Url */
    private String cmpyUrl = null;

    /** Company Url */
    private BigDecimal invPrintLimit = null;

    /** Default Bill To Attention */
    private String defBillToAttn = null;
    
    /** Easy Pack1 Invoice Message1 */
    private String easyPac1InvMsg1 = null;
    // QC#20141 del Start
    ///** Easy Pack1 Invoice Message2 */
    //private String easyPac1InvMsg2 = null;
    // QC#20141 del End
    /** Easy Pack1 Invoice Message3 */
    private String easyPac1InvMsg3 = null;
    // QC#20141 del Start
    ///** Easy Pack1 Invoice Message4 */
    //private String easyPac1InvMsg4 = null;
    // QC#20141 del End
    /** Invoice Print Short Fall Text */
    private String invPrintShortFallTxt = null;
    
    /** Invoice Print Short Fall Item */
    private String invPrintShortFallItem = null;
    
    /** OM Invoice BO sort (0:INV->BO, 1:BO->INV) */
    private String invPrintOmInvBoSort = null;
    
    /** Invoice is part of a Consolidated Bill */
    private String invPrtOfConsolBillMsg = null;
    
    /** Credit Card Message 01 */
    private String invCcMsg01 = null;
    
    /** Credit Card Message 02 */
    private String invCcMsg02 = null;
    
    /** Credit Card Message 03 */
    private String invCcMsg03 = null;

    // 2019/09/04 QC#53254 Add Start
    /** Credit Card Message 04 */
    private String invCcMsg04 = null;
    // 2019/09/04 QC#53254 Add End

    // START 2022/08/03 [QC#60129, ADD]
    /** Credit Card Message 05 */
    private String invCcMsg05 = null;
    // END   2022/08/03 [QC#60129, ADD]

    /** Consolidated Credit Card Footer Message */
    private String conslCrCardFooterMsg = null;
    
    private S21LRUMap<String, Object> cache = null;

    private boolean dataReCreationFlg = false;
    
    /** Error List */
    private List<Map<String, String>> mailErrorList = new ArrayList<Map<String, String>>();

    /** Run Book DS_INV_TP_CD */
    private String[] runBookDsInvTpCdArray = null;

    /** Run Book SYS_SRC_CD */
    private String[] runBookSysSrcCdArray = null;

    // QC#50078 2019/05/07 Add Start
    /** FSR INV_TP_CD */
    private String[] fsrInvTpCdArray = null;
    // QC#50078 2019/05/07 Add End
    
    // 2019/10/10 QC#53888 Add Start
    /** DataBase Scheme Name */
    private String dbSchema = null;
    // 2019/10/10 QC#53888 Add End

    // QC#58231
    /** Limit Amout Map */
    private Map<String, BigDecimal> limitAmtMap = null;
    // QC#58231
    /** Cor Proj Cds */
    private String[] coaProjCds = null;

    // START 2023/11/9 R.Takau [QC#61584, ADD]
    /** BILL_TO_CUST_ACCT_CD_CFS */
    private String[] billToCustAcctCdCfsArray = null;

    /** SHIP_TO_CUST_ST_CD */
    private String[] shipToCustStCdArray = null;

    /** BILL_TO_CUST_ACCT_CD_CFS */
    private String[] slsRepStCdArray = null;

    /** TAX_PRINT_DS_INV_TP */
    private String[] taxPrintDsInvTpArray = null;

    /** SLS_TAX_MDSE_CD */
    private String[] slsTaxMdseCdArray = null;

    // END 2023/11/9 R.Takau [QC#61584, ADD]

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWCB010001().executeBatch(NWCB010001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        this.procMode = getUserVariable1();
        if (this.procMode == null || "".equals(this.procMode)) {
            throw new S21AbendException(NWCM0059E, new String[] {"Process Mode" });
        }

    }

    @Override
    protected void mainRoutine() {

        try {
            this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BUSINESS_ID);

            this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
            
            this.cmpyFrstAddr = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_CMPY_FIRST_ADDR, this.glblCmpyCd);
            this.cmpyScdAddr = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_CMPY_SCD_ADDR, this.glblCmpyCd);
            this.cmpyCtyAddr = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_CMPY_CTY_ADDR, this.glblCmpyCd);
            this.cmpyState = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_CMPY_STATE, this.glblCmpyCd);
            this.cmpyPost = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_CMPY_POST, this.glblCmpyCd);
            this.cmpyTel = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_CMPY_TEL, this.glblCmpyCd);
            this.cmpyUrl = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_CMPY_URL, this.glblCmpyCd);
            this.defBillToAttn = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_DEF_BILL_TO_ATTN, this.glblCmpyCd);
            this.easyPac1InvMsg1 = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_EASY_PAC_1_INV_MEG_01, this.glblCmpyCd);
            // QC#20141 del Start
            //this.easyPac1InvMsg2 = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_EASY_PAC_1_INV_MEG_02, this.glblCmpyCd);
            // QC#20141 del End
            this.easyPac1InvMsg3 = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_EASY_PAC_1_INV_MEG_03, this.glblCmpyCd);
            // QC#20141 del Start
            //this.easyPac1InvMsg4 = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_EASY_PAC_1_INV_MEG_04, this.glblCmpyCd);
            // QC#20141 del End
            this.invPrintShortFallTxt = ZYPCodeDataUtil.getVarCharConstValue(INV_PRINT_SHORT_FALL_TXT, this.glblCmpyCd);
            this.invPrintShortFallItem = ZYPCodeDataUtil.getVarCharConstValue(INV_PRINT_SHORT_FALL_ITEM, this.glblCmpyCd);
            this.invPrintOmInvBoSort = ZYPCodeDataUtil.getVarCharConstValue(INV_PRINT_OM_INV_BO_SORT, this.glblCmpyCd);
            this.invPrtOfConsolBillMsg = ZYPCodeDataUtil.getVarCharConstValue(NWCB0100_PRT_OF_CONSL_MSG, this.glblCmpyCd);
            this.invCcMsg01 = ZYPCodeDataUtil.getVarCharConstValue(NWCB0100_CR_CARD_MSG_01, this.glblCmpyCd);
            this.invCcMsg02 = ZYPCodeDataUtil.getVarCharConstValue(NWCB0100_CR_CARD_MSG_02, this.glblCmpyCd);
            this.invCcMsg03 = ZYPCodeDataUtil.getVarCharConstValue(NWCB0100_CR_CARD_MSG_03, this.glblCmpyCd);

            // 2019/09/04 QC#53254 Add Start
            this.invCcMsg04 = ZYPCodeDataUtil.getVarCharConstValue(NWCB0100_CR_CARD_MSG_04, this.glblCmpyCd);
            // 2019/09/04 QC#53254 Add End

            // START 2022/08/03 [QC#60129, ADD]
            this.invCcMsg05 = ZYPCodeDataUtil.getVarCharConstValue(NWCB0100_CR_CARD_MSG_05, this.glblCmpyCd);
            // END   2022/08/03 [QC#60129, ADD]

            this.conslCrCardFooterMsg = ZYPCodeDataUtil.getVarCharConstValue(NWCB0100_PRT_CONSL_CC_FTR_MSG, this.glblCmpyCd);
            this.invPrintLimit = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_NM_INV_PRT_TOT_LMT, this.glblCmpyCd);
            String runBookDsInvTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_RUN_BOOK_DS_INV_TP, this.glblCmpyCd);
            if (hasValue(runBookDsInvTpCd)) {
                runBookDsInvTpCdArray = runBookDsInvTpCd.split(",");
            }
            String runBookSysSrcCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_RUN_BOOK_SYS_SRC, this.glblCmpyCd);
            if (hasValue(runBookSysSrcCd)) {
                runBookSysSrcCdArray = runBookSysSrcCd.split(",");
            }
            // QC#50078 2019/05/07 Add Start
            String fsrInvTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_FSR_INV_TP, this.glblCmpyCd);
            if (hasValue(fsrInvTpCd)) {
                fsrInvTpCdArray = fsrInvTpCd.split(",");
            }
            // QC#50078 2019/05/07 Add Start
            
            // START 2023/11/9 R.Takau [QC#61584, ADD]
            this.slsTaxMdseCdArray = getVarcharConst (VAR_CHAR_CONST_SLS_TAX_MDSE_CD, NWCB0100_SLS_TAX_MDSE_CD_CONST_VAL);
            this.billToCustAcctCdCfsArray = getVarcharConst (VAR_CHAR_CONST_BILL_TO_CUST_ACCT_CD_CFS, BILL_TO_CUST_ACCT_CD_CFS_CONST_VAL);
            this.slsRepStCdArray = getVarcharConst (VAR_CHAR_CONST_NWCB0100_SLS_REP_ST_CD,NWCB0100_SLS_REP_ST_CD_CONST_VAL);
            this.shipToCustStCdArray = getVarcharConst (VAR_CHAR_CONST_NWCB0100_SHIP_TO_CUST_ST_CD, NWCB0100_SHIP_TO_CUST_ST_CD_CONST_VAL);
            this.taxPrintDsInvTpArray = getVarcharConst (VAR_CHAR_CONST_NWCB0010_TAX_PRINT_DS_INV_TP, NWCB0010_TAX_PRINT_DS_INV_TP_CONST_VAL);
            // END 2023/11/9 R.Takau [QC#61584, ADD]

            // 2019/10/10 QC#53888 Add Start
            this.dbSchema = ZYPCodeDataUtil.getVarCharConstValue(CONST_DB_SCHEMA, glblCmpyCd);
            try {
                truncateInsertWrkTbl();
            } catch (SQLException e) {
                S21InfoLogOutput.println(String.format("truncateInsertWrkTbl Error [%s]",e.getMessage()));
                termCd = TERM_CD.ABNORMAL_END;
                return;
            }
            // 2019/10/10 QC#53888 Add Start
            // QC#58231
            this.limitAmtMap = getNumConstMap();
            String varConst = ZYPCodeDataUtil.getVarCharConstValue("NWCB010001_LIMIT_CHK_PROJ_CD", this.glblCmpyCd);
            if (hasValue(varConst)) {
                this.coaProjCds = varConst.split(",");
            }

            this.cache = new S21LRUMap<String, Object>();
    
            // Register Print Table
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("slsDt", this.slsDt);
            ssmParam.put("procMode", this.procMode);
            ssmParam.put("formatKey1", "1");
            ssmParam.put("formatKey2", "2");
            // START 2018/07/17 E.Kameishi [QC#27007,DEL]
            //ssmParam.put("invPrintStsCd0", "0");
            // START 2018/07/17 E.Kameishi [QC#27007,DEL]
            ssmParam.put("invPrintStsCd1", "1");
            ssmParam.put("invPrintStsCd3", "3");  //Exclude Consolidated Credit Memo
            ssmParam.put("dsTaxPrntTp10", DS_TAX_PRNT_TP.TOTAL_TAX_ONLY);
            ssmParam.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
            ssmParam.put("dsContrCatgAgg", DS_CONTR_CATG.AGGREGATE); // QC#29371
            ssmParam.put("svcInvSrcTpC", SVC_INV_SRC_TP.CONTRACT);
            ssmParam.put("formatTpFL", INV_PRT_DTL_FMT_TP.FLEET_CONTRACT);
            ssmParam.put("formatTpAG", INV_PRT_DTL_FMT_TP.AGGREGATE_CONTRACT); // QC#29371
            ssmParam.put("formatTpMA", INV_PRT_DTL_FMT_TP.MAINTENANCE);
            ssmParam.put("formatTpPL", INV_PRT_DTL_FMT_TP.PARTS_AND_LABOR);
            ssmParam.put("formatTpSS", INV_PRT_DTL_FMT_TP.SALE_AND_SUPPLY);
            ssmParam.put("conslPrintStsN", CONSL_PRINT_STS.NOT_PRINTED);
            ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
            ssmParam.put("crCardTrxTpCpo", CR_CARD_TRX_TP.CPO);
            ssmParam.put("amtFormat", FM_AMT);
            ssmParam.put("svcInvChrgTpCdMC", SVC_INV_CHRG_TP.METER_CHARGE);

            //QC#27069 Add Start
            ssmParam.put("svcInvChrgTpCdAC", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
            ssmParam.put("svcInvChrgTpCdBC", SVC_INV_CHRG_TP.BASE_CHARGE);
            //QC#27069 Add End

            // QC#50078 2019/05/07 Add Start
            ssmParam.put("fsrInvTpCdArray", fsrInvTpCdArray);
            // QC#50078 2019/05/07 Add End

            // START 2023/11/30 R.Takau [QC#61584,ADD] 
            ssmParam.put("billToCustAcctCdCfsArray", this.billToCustAcctCdCfsArray);
            ssmParam.put("shipToCustStCdArray", this.shipToCustStCdArray);
            ssmParam.put("slsRepStCdArray", this.slsRepStCdArray);
            ssmParam.put("taxPrintDsInvTpArray", this.taxPrintDsInvTpArray);
            ssmParam.put("slsTaxMdseCdArray", this.slsTaxMdseCdArray);
            // END 2023/11/30 R.Takau [QC#61584,ADD] 

            this.ssmBatchClient.queryObjectList("getInvoiceData", ssmParam, new ExecPrintDBProc());

            //Print
            if (PRINT_MODE.equals(this.procMode)) {
                // Invoice from waiting for Consolidated Term to Daily
                // Invoice from waiting for Consolidated AR Invoice to Daily
                // Credit Memo excluded by Consolidated Batch
                ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", this.glblCmpyCd);
                ssmParam.put("conslPrintStsN", CONSL_PRINT_STS.NOT_PRINTED);
                ssmParam.put("dsTaxPrntTp10", DS_TAX_PRNT_TP.TOTAL_TAX_ONLY);
 
                this.ssmBatchClient.queryObjectList("getConsTermWaitData", ssmParam, new ExecConsTermsWaitDBProc());
            }

            // Arrival Consolidated Term
            ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("conslPrintStsN", CONSL_PRINT_STS.NOT_PRINTED);
            ssmParam.put("conslStsCd10", CONSL_STS.ACCEPTED);
            ssmParam.put("dsTaxPrntTp10", DS_TAX_PRNT_TP.TOTAL_TAX_ONLY);
            ssmParam.put("procMode", this.procMode);
    
            this.ssmBatchClient.queryObjectList("getConsTermArrvData", ssmParam, new ExecConsTermsArrvDBProc());
            
        } finally {
            if (mailErrorList.size() > 0) {
                postErrMail();
                termCd = TERM_CD.WARNING_END;
            }
            commit();
        }
    }

    /**
     * execute DB process class
     */
    private class ExecPrintDBProc extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            List<String> invoiceNumList = new ArrayList<String>();
            List<String> ordCatgCtxTpListForSplyOrds = new ArrayList<String>();
            ordCatgCtxTpListForSplyOrds.add(ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
            ordCatgCtxTpListForSplyOrds.add(ORD_CATG_CTX_TP.EASY_PAC1); // 2018/08/22 QC#27443
            // QC#31177 2019/04/16 Add Start
            List<String> ordCatgCtxTpListForNoConfOrds = new ArrayList<String>();
            ordCatgCtxTpListForNoConfOrds.add(ORD_CATG_CTX_TP.INV_PRT_NOCONF_ORDER);
            // QC#31177 2019/04/16 Add End
            //QC#26846
            List<String> ordCatgCtxTpListForBillablePartsOrds = new ArrayList<String>();
            ordCatgCtxTpListForBillablePartsOrds.add("INV_PRT_PARTS_ORDER");
            dataReCreationFlg = false;

            while (rs.next()) {

                try {
                String invoiceNum = rs.getString(INV_NUM);
                String dsBizAreaCd = rs.getString(DS_BIZ_AREA_CD);
                String billToCustCd = rs.getString(BILL_TO_CUST_CD);
                BigDecimal mcCnt = rs.getBigDecimal(MC_CNT);

                int lineCount = 0;

                dataReCreationFlg = false;
                // If Process Mode is Re-Print ,Logical Delete
                Map<String, Map<String, Object>> preConslBillInfoMap = new HashMap<String, Map<String, Object>>();
                if (REPRINT_MODE.equals(procMode)) {
                    preConslBillInfoMap = logicalDelete(invoiceNum, invoiceNumList);
                }

                //IsSupplyFlg
                boolean isSupplyOrderFlg = getOrdCatgBizCtxExistsFlg(rs.getString(DS_ORD_CATG_CD), 
                    rs.getString(DS_ORD_TP_CD), rs.getString(DS_ORD_RSN_CD), ordCatgCtxTpListForSplyOrds);
                // QC#31177 2019/04/16 Add Start
                //IsNoConfigFlg
                boolean isNoConfigOrderFlg = getOrdCatgBizCtxExistsFlg(rs.getString(DS_ORD_CATG_CD), 
                        rs.getString(DS_ORD_TP_CD), rs.getString(DS_ORD_RSN_CD), ordCatgCtxTpListForNoConfOrds);
                // QC#31177 2019/04/16 Add End
                //QC#26846
                //isBillablePartsOrderFlg
                boolean isBillablePartsOrderFlg = getOrdCatgBizCtxExistsFlg(rs.getString(DS_ORD_CATG_CD), 
                    rs.getString(DS_ORD_TP_CD), rs.getString(DS_ORD_RSN_CD), ordCatgCtxTpListForBillablePartsOrds);
                // GET Customer Invoice Information
                NMZC610001PMsg nmzc6100PMsgForRule = getNMZC610001PMsgForRule(dsBizAreaCd, billToCustCd);
                INV_PRT_CTRLTMsg invPrtCtrlTmsg = setInitInvPrtCtrl(nmzc6100PMsgForRule);
                String custBllgTpCd = null;
                String billVehicleCd = null; //QC#26846
                if (nmzc6100PMsgForRule.InvoiceRuleList.getValidCount() == 0) {
                    custBllgTpCd = CUST_BLLG_TP.DAILY;
                    billVehicleCd = CUST_BLLG_VCLE.PRINT_ONLY;  //QC#26846
                } else {
                    custBllgTpCd = nmzc6100PMsgForRule.InvoiceRuleList.no(0).custBllgTpCd.getValue();
                    billVehicleCd = nmzc6100PMsgForRule.InvoiceRuleList.no(0).custBllgVcleCd.getValue();  //QC#26846
                }

                Map<String, String> billToCustInfo = new HashMap<String, String>();
                // Judges Easy Pac1
                boolean isEasyPac1Flg = isEasyPac(rs, billToCustInfo);

                // 2019/01/28 QC#30077 Mod Start
//                // GET Customer Instructions Information
//                String hederComment = getCustInstInfo(rs, isEasyPac1Flg, billToCustInfo, invPrtCtrlTmsg.conslBillFlg.getValue());
//                lineCount = getLineCount(hederComment);
//
//                Map<String, Object> footerMsgMap = new HashMap<String, Object>();
//                List<Map<String, Object>> footerMsgList = getFooterComment();
//                if (footerMsgList != null && footerMsgList.size() > 0 && mcCnt.intValue() != 0) {
//                    for (Map<String, Object> footerMessageMap : footerMsgList) {
//                        footerMsgMap.put((String) footerMessageMap.get(USG_INV_DESC_CD), footerMessageMap.get(USG_INV_DESC_TXT));
//                    }
//                }
//
//                INV_PRT_HDRTMsg invPrtHdrTmsg = new INV_PRT_HDRTMsg();
//                //setInvPrtCtrl(invPrtCtrlTmsg, invPrtHdrTmsg, custBllgTpCd, rs, isEasyPac1Flg, (Map<String, Object>)preConslBillInfoMap.get(INV_PRT_CTRL)); //QC#26846
//                setInvPrtCtrl(invPrtCtrlTmsg, invPrtHdrTmsg, custBllgTpCd, rs, isEasyPac1Flg, (Map<String, Object>)preConslBillInfoMap.get(INV_PRT_CTRL), billVehicleCd);

                INV_PRT_HDRTMsg invPrtHdrTmsg = new INV_PRT_HDRTMsg();
                //setInvPrtCtrl(invPrtCtrlTmsg, invPrtHdrTmsg, custBllgTpCd, rs, isEasyPac1Flg, (Map<String, Object>)preConslBillInfoMap.get(INV_PRT_CTRL)); //QC#26846
                setInvPrtCtrl(invPrtCtrlTmsg, invPrtHdrTmsg, custBllgTpCd, rs, isEasyPac1Flg, (Map<String, Object>) preConslBillInfoMap.get(INV_PRT_CTRL), billVehicleCd);

                // conslBillFlg=Y when Easy PAC I contains
                    if (isEasyPac1Flg) {
                        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.conslBillFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.conslBillFlg, ZYPConstant.FLG_ON_Y);
                    }

                // GET Customer Instructions Information
                String hederComment = getCustInstInfo(rs, isEasyPac1Flg, billToCustInfo, invPrtCtrlTmsg.conslBillFlg.getValue());
                lineCount = getLineCount(hederComment);

                Map<String, Object> footerMsgMap = new HashMap<String, Object>();
                List<Map<String, Object>> footerMsgList = getFooterComment();
                if (footerMsgList != null && footerMsgList.size() > 0 && mcCnt.intValue() != 0) {
                    for (Map<String, Object> footerMessageMap : footerMsgList) {
                        footerMsgMap.put((String) footerMessageMap.get(USG_INV_DESC_CD), footerMessageMap.get(USG_INV_DESC_TXT));
                    }
                }
                // 2019/01/28 QC#30077 Mod End
                // START 2022/07/20 R.Onozuka [QC#59669, MOD]
                //setInvPrtHdr(invPrtHdrTmsg, rs, hederComment, footerMsgMap);
                setInvPrtHdr(invPrtHdrTmsg, rs, hederComment, footerMsgMap, (Map<String, Object>) preConslBillInfoMap.get(INV_PRT_CTRL));
                // START 2022/07/20 R.Onozuka [QC#59669, MOD]

                INV_PRT_HDRTMsg[] invPrtHdrTmsgArray = null;
                INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray = null;

                String istlPmtTermFlg = rs.getString(ISTL_FLG);
                boolean result = true;

                // Consolidated Term "Daily" & Not EasyPac1Flg & Installment Flag=Y
                if (CUST_BLLG_TP.DAILY.equals(custBllgTpCd) && !isEasyPac1Flg && ZYPConstant.FLG_ON_Y.equals(istlPmtTermFlg)) {

                    List<Map<String, Object>> pmtTermInfoList = getPmtTermInfoList(rs.getString(PMT_TERM_CD));
                    if (!pmtTermInfoList.isEmpty()) {
                        invPrtCtrlTmsgArray = new INV_PRT_CTRLTMsg[pmtTermInfoList.size()];
                        invPrtHdrTmsgArray  = new INV_PRT_HDRTMsg[pmtTermInfoList.size()];
                        pmtSplitProcDaily(invPrtCtrlTmsgArray, invPrtHdrTmsgArray, invPrtCtrlTmsg, invPrtHdrTmsg, rs, pmtTermInfoList);
                    } else {
                        invPrtCtrlTmsgArray = new INV_PRT_CTRLTMsg[1];
                        invPrtHdrTmsgArray = new INV_PRT_HDRTMsg[1];
                        invPrtCtrlTmsgArray[0] = invPrtCtrlTmsg;
                        invPrtHdrTmsgArray[0] = invPrtHdrTmsg;
                    }
                } else {
                    invPrtCtrlTmsgArray = new INV_PRT_CTRLTMsg[1];
                    invPrtHdrTmsgArray = new INV_PRT_HDRTMsg[1];
                    invPrtCtrlTmsgArray[0] = invPrtCtrlTmsg;
                    invPrtHdrTmsgArray[0] = invPrtHdrTmsg;
                }

                //QC#25431 Del Start
                // Insert INV_PRT_CTRL
                //result = insertCtrl(invPrtCtrlTmsgArray, invoiceNum);
                //if (!result) {
                //    rollback();
                //    termCd = TERM_CD.WARNING_END;
                //    continue;
                //}
                //QC#25431 Del End

                //QC#25431 Add Start
                for (int i = 0; i < invPrtCtrlTmsgArray.length; i++) {
                    INV_PRT_CTRLTMsg ipcTmsg = invPrtCtrlTmsgArray[i];
                    ipcTmsg.invPrtCtrlPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_CTRL_SQ));
                }
                //QC#25431 Add End

                // Insert INV_PRT_HDR
                result = insertHdr(invPrtHdrTmsgArray, invPrtCtrlTmsgArray, invoiceNum);
                if (!result) {
                    termCd = TERM_CD.WARNING_END;
                    continue;
                }

                lineCount += FIXED_COUNT;
                // Insert INV_PRT_SMRY
                //result = createPrintSummaryTable(rs, lineCount, isEasyPac1Flg, isSupplyOrderFlg);  //QC#26846
                // QC#31177 2019/04/16 Mod Start
                result = createPrintSummaryTable(rs, lineCount, isEasyPac1Flg, isSupplyOrderFlg, isNoConfigOrderFlg, isBillablePartsOrderFlg);
                // QC#31177 2019/04/16 Mod End
                if (!result) {
                    termCd = TERM_CD.WARNING_END;
                    continue;
                }
                
                String formatTp = rs.getString(FORMAT_TYPE);
                if (INV_PRT_DTL_FMT_TP.FLEET_CONTRACT.equals(formatTp)) {
                    result = setFleetInfo(rs, (Map<String, Object>)preConslBillInfoMap.get(INV_PRT_FLEET_SUB_TOT));
                } else if (INV_PRT_DTL_FMT_TP.AGGREGATE_CONTRACT.equals(formatTp)) {
                    result = setAggInfo(rs, (Map<String, Object>)preConslBillInfoMap.get(INV_PRT_FLEET_SUB_TOT));
                } else if (INV_PRT_DTL_FMT_TP.MAINTENANCE.equals(formatTp)) {
                    result = setMaintInfo(rs, (Map<String, Object>)preConslBillInfoMap.get(INV_PRT_MAINT_SUB_TOT));
                } else if (INV_PRT_DTL_FMT_TP.SALE_AND_SUPPLY.equals(formatTp)) {
                    //result = setSaleSplyInfo(rs, isEasyPac1Flg, (Map<String, Object>)preConslBillInfoMap.get(INV_PRT_SLS_PART_SUB_TOT), billToCustInfo, isSupplyOrderFlg, invPrtCtrlTmsgArray);  //QC#26846
                    // QC#31177 2019/04/16 Mod Start
                    //result = setSaleSplyInfo(rs, isEasyPac1Flg, (Map<String, Object>)preConslBillInfoMap.get(INV_PRT_SLS_PART_SUB_TOT), billToCustInfo, isSupplyOrderFlg, invPrtCtrlTmsgArray, isBillablePartsOrderFlg);
                    result = setSaleSplyInfo(rs, isEasyPac1Flg, (Map<String, Object>)preConslBillInfoMap.get(INV_PRT_SLS_PART_SUB_TOT), billToCustInfo, isSupplyOrderFlg, isNoConfigOrderFlg, invPrtCtrlTmsgArray, isBillablePartsOrderFlg);
                    // QC#31177 2019/04/16 Mod End
                } else if (INV_PRT_DTL_FMT_TP.PARTS_AND_LABOR.equals(formatTp)) {
                    result = setPartsLaborInfo(rs, (Map<String, Object>)preConslBillInfoMap.get(INV_PRT_SLS_PART_SUB_TOT));
                }
                if (!result) {
                    rollback();
                    termCd = TERM_CD.WARNING_END;
                    ++errCnt;
                    continue;
                }

                // QC#58231
                // Limit Check
                for (INV_PRT_CTRLTMsg tMsg : invPrtCtrlTmsgArray) {

                    if (isCreditRebill(tMsg)) {
                        continue;
                    }

                    if (INV_PRT_BR.WEB_BILLING_PRINT_ONLY.equals(invPrtCtrlTmsg.invPrtBrCd.getValue()) //
                            || INV_PRT_BR.WEB_BILLING_PRINT_AND_EMAIL.equals(invPrtCtrlTmsg.invPrtBrCd.getValue()) //
                            || INV_PRT_BR.STRATEGIC_ACCOUNTS_PRINT_ONLY.equals(invPrtCtrlTmsg.invPrtBrCd.getValue()) //
                            || INV_PRT_BR.STRATEGIC_ACCOUNTS_PRINT_AND_EMAIL.equals(invPrtCtrlTmsg.invPrtBrCd.getValue())) {
                        continue;
                    }

                    if (INV_PRT_CTRL_REC.CONTRACT_INVOICES.equals(tMsg.invPrtCtrlRecCd.getValue())) {
                        // Total
                        chkReviewTotAmt(tMsg, "CONTRACT_TOT_LIMIT_AMT", rs.getBigDecimal(INV_AMT));
                        if (ZYPCommonFunc.hasValue(tMsg.conslBillTotAmt)) {
                            chkReviewTotAmt(tMsg, "CONTRACT_TOT_LIMIT_AMT", tMsg.conslBillTotAmt.getValue());
                        }
                        // Line
                        if (ZYPCommonFunc.hasValue(tMsg.invNum)) {
                            chkReviewLineAmt(tMsg, "CONTRACT_LINE_LIMIT_AMT");
                        }
                    } else {
                        // Total
                        chkReviewTotAmt(tMsg, "EQUIP_TOT_LIMIT_AMT", rs.getBigDecimal(INV_AMT));
                        if (ZYPCommonFunc.hasValue(tMsg.conslBillTotAmt)) {
                            chkReviewTotAmt(tMsg, "EQUIP_TOT_LIMIT_AMT", tMsg.conslBillTotAmt.getValue());
                        }
                        // Line
                        if (ZYPCommonFunc.hasValue(tMsg.invNum)) {
                            chkReviewLineAmt(tMsg, "SW_ITEM_LINE_LIMIT_AMT");
                        }
                    }
                }
                

                //QC#25431 Add Start
                result = insertCtrl(invPrtCtrlTmsgArray, invoiceNum);
                if (!result) {
                    rollback();
                    termCd = TERM_CD.WARNING_END;
                    continue;
                }
                //QC#25431 Add End

                //If not excluded consolidated credit memo
                if (!"3".equals(rs.getString("INV_PRINT_STS_CD"))) {
                    result = updateInv(invoiceNum);
                    if (!result) {
                        rollback();
                        termCd = TERM_CD.WARNING_END;
                        ++errCnt;
                        continue;
                    }
                }
                
                if (result) {
                    ++normCnt;
                    commit();
                }
            } catch(NWCB010001BusinessException e) {
                setMailMessage(e.getNumber(), e.getMsgId(), e.getMsgParam());
                rollback();
                termCd = TERM_CD.WARNING_END;
                ++errCnt;
                continue;
            }
            } //End Loop
            return true;
        }

        /**
         * @param invNum String
         * @param invNumList List<String>
         */
        private Map<String, Map<String, Object>> logicalDelete(String invNum, List<String> invNumList) {

            Map<String, Map<String, Object>> preConslBillInfoMap = new HashMap<String, Map<String, Object>>(); //QC#7561
            // Logical Delete by invoice number
            if (invNum != null && !"".equals(invNum) && !invNumList.contains(invNum)) {
                //store Consolidation Info
                logicalDeletePrtHdr(invNum);
                logicalDeletePrtSmry(invNum);

                Map<String, Object> keyBillCtrlMap = logicalDeletePrtCtrl(invNum);
                preConslBillInfoMap.put(INV_PRT_CTRL, keyBillCtrlMap);

                Map<String, Object> keyBillFleetSubTotMap = logicalDeleteFleetSubTot(invNum);
                preConslBillInfoMap.put(INV_PRT_FLEET_SUB_TOT, keyBillFleetSubTotMap);
                // logicalDeleteFleetInvLine(invNum); // QC#30346 2019/02/18 Add // QC#52209 2019/07/31 Del
                logicalDeleteFleetChrgDtl(invNum);
                logicalDeleteFleetLoc(invNum);
                logicalDeleteFleetMach(invNum);
                logicalDeleteFleetMtr(invNum);
                logicalDeleteFleetSmryMtr(invNum);
                logicalDeleteFleetSmryXs(invNum);

                Map<String, Object> keyBillMaintSubTotMap = logicalDeleteMaintSubTot(invNum);
                preConslBillInfoMap.put(INV_PRT_MAINT_SUB_TOT, keyBillMaintSubTotMap);
                logicalDeleteMaintMach(invNum);
                logicalDeleteMaintChrg(invNum);
                logicalDeleteMaintMtr(invNum);
                logicalDeleteMaintXs(invNum);

                Map<String, Object> keyBillSlsPartsSubTotMap = logicalDeleteSlsPartSubTot(invNum);
                preConslBillInfoMap.put(INV_PRT_SLS_PART_SUB_TOT, keyBillSlsPartsSubTotMap);
                logicalDeleteSlsPartDtl(invNum);
                invNumList.add(invNum);
            }
            return preConslBillInfoMap;
        }
    }
    
    /**
     * @param consBillNum String
     * @param consBillNumList List<String>
     */
    private void logicalDeleteForConsl(String consBillNum, List<String> conslBillDeleteList) {

        // Logical Delete by Cons Bill number
        if (consBillNum != null && !"".equals(consBillNum) && !conslBillDeleteList.contains(consBillNum)) {
            conslBillDeleteList.add(consBillNum);
            logicalDeletePrtHdr(consBillNum);
            logicalDeletePrtSmry(consBillNum);
            logicalDeletePrtCtrlForCons(consBillNum);
        }
    }

    private NMZC610001PMsg getNMZC610001PMsgForRule(String dsBizAreaCd, String billToCustCd)
            throws SQLException, NWCB010001BusinessException {

        String key = createCacheKey(NMZC610001PMSG_FOR_RULE, dsBizAreaCd + billToCustCd);
        NMZC610001PMsg nmzc6100PMsgForRule = (NMZC610001PMsg) this.cache.get(key);

        // If Map has the same key
        if (nmzc6100PMsgForRule != null) {
            return nmzc6100PMsgForRule;
        }

        String custInvSrcCd = getCustInvSrcCd(dsBizAreaCd);

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        pMsg.glblCmpyCd.setValue(glblCmpyCd);
        pMsg.xxModeCd.setValue(NMZC610001Constant.PROCESS_MODE_INVOICE);
        pMsg.custInvSrcCd.setValue(custInvSrcCd);
        pMsg.billToCustCd.setValue(billToCustCd);

        new NMZC610001().execute(pMsg, ONBATCH_TYPE.BATCH);
//Debug Start
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        }
//Debug End
        this.cache.put(key, pMsg);

        return pMsg;
    }

    private void getCustInvInfoEx(Map<String, Map<String, NMZC610001PMsg>> custInvMap, ResultSet rs)
        throws SQLException, NWCB010001BusinessException {

        String dsBizAreaCd = rs.getString(DS_BIZ_AREA_CD);
        String billToCustCd = rs.getString(BILL_TO_CUST_CD);

        // If Map has the same key
        if (custInvMap.containsKey(dsBizAreaCd) && custInvMap.get(dsBizAreaCd).containsKey(billToCustCd)) {
            return;
        }
        
        String custInvSrcCd = getCustInvSrcCd(dsBizAreaCd);
        
        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        pMsg.glblCmpyCd.setValue(glblCmpyCd);
        pMsg.xxModeCd.setValue(NMZC610001Constant.PROCESS_MODE_INVOICE);
        pMsg.custInvSrcCd.setValue(custInvSrcCd);
        pMsg.billToCustCd.setValue(billToCustCd);
        
        new NMZC610001().execute(pMsg, ONBATCH_TYPE.BATCH);
//Debug Start
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        }
//Debug End
        Map<String, NMZC610001PMsg> custInvVlaueMap = new HashMap<String, NMZC610001PMsg>();
        custInvVlaueMap.put(billToCustCd, pMsg);
        custInvMap.put(dsBizAreaCd, custInvVlaueMap);

        return;
    }

    
    private boolean isEasyPac(ResultSet rs, Map<String, String> billToCustInfo) throws SQLException, NWCB010001BusinessException {

        boolean judge1 = orderReason(rs);

        boolean judge2 = billToCust(rs, billToCustInfo);
        if (judge1 && judge2) {
            return true;
        }
        return false;

    }

    private String getCustInstInfo(ResultSet rs, boolean isEasyPac1Flg, Map<String, String> custInfo, String conslBillFlg) throws SQLException, NWCB010001BusinessException {

        String lineBizTpCd = rs.getString(LINE_BIZ_TP_CD);
        String dsBizAreaCd = rs.getString(DS_BIZ_AREA_CD);
        String billToCustCd = rs.getString(BILL_TO_CUST_CD);
        BigDecimal dueAmt = rs.getBigDecimal(BALANCE_DUE);
        BigDecimal totalAmt = rs.getBigDecimal(INV_AMT);

        String custCareTktNum = rs.getString(CUST_CARE_TKT_NUM);
        String origInvNum = rs.getString(ORIG_INV_NUM);
        String invTpCd = rs.getString(INV_TP_CD);

        String comment1 = nullConvEmp(rs.getString(COMMENT1));
        String comment2 = nullConvEmp(rs.getString(COMMENT2));
        String comment3 = nullConvEmp(rs.getString(COMMENT3));
        String comment4 = nullConvEmp(rs.getString(COMMENT4));
        String pmtCreditFlg = rs.getString(PMT_CC_FLG);
        //Credit Card
        String creditCard4Digit = getCreditCardLastDigit(rs);
        
        //Customer Message
        String messageBody = nullConvEmp(getCustInstInfoApi(lineBizTpCd, billToCustCd, dsBizAreaCd));

        String easyPac1Msg = "";
        if (isEasyPac1Flg) {
            easyPac1Msg = getEasyPac1Msg(custInfo);
        }

        String fixedWord = nullConvEmp(replaceSystemLineSeparator(rs.getString(INV_PRINT_MSG_TXT)));
        StringBuilder stackStr = new StringBuilder();
        List<String> strList = new ArrayList<String>();
        String returnStr = "";
        String invPrtOfConslBillMsg = "";
        if (ZYPConstant.FLG_ON_Y.equals(conslBillFlg)) {
            invPrtOfConslBillMsg = this.invPrtOfConsolBillMsg;
        }

        String creditAndRebillMsg = "";
        if (hasValue(origInvNum)) {
            if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
                if (hasValue(custCareTktNum)) {
                    creditAndRebillMsg = "Credit for INV# " + origInvNum + " , CI# " + custCareTktNum;
                } else {
                    creditAndRebillMsg = "Credit for INV# " + origInvNum;
                }
            } else {
                if (hasValue(custCareTktNum)) {
                    creditAndRebillMsg = "Rebill for INV# " + origInvNum + " , CI# " + custCareTktNum;
                } else {
                    creditAndRebillMsg = "Rebill for INV# " + origInvNum;
                }
            }
        }

        // 06/12/2019 QC#50817 Del Start
        //// TotalAmout - DueAmount = 0 (Unpaid)
        //// 05/30/2019 QC#50580 Mod Start
        //if (BigDecimal.ZERO.compareTo(totalAmt.subtract(dueAmt)) == 0) {
        ////if (BigDecimal.ZERO.equals(totalAmt.subtract(dueAmt))) {
        //// 05/30/2019 QC#50580 Mod End
        //
        //  if (ZYPCommonFunc.hasValue(easyPac1Msg)) {
        //      strList.add(easyPac1Msg);
        //  } else {
        //      strList.add(" ");
        //  }
        //  strList.add(creditAndRebillMsg);
        //  strList.add(fixedWord);
        //  strList.add(invPrtOfConslBillMsg);
        //  stackStr.append(comment1).append(comment2).append(comment3).append(comment4);
        //  if (hasValue(stackStr.toString())) strList.add(" ");
        //  strList.add(stackStr.toString());
        //  if (hasValue(messageBody)) strList.add(" ");
        //  strList.add(messageBody);
        //
        //  returnStr = setComment(strList);
        //
        //} else {
        // 06/12/2019 QC#50817 Del End
            // TotalAmout - DueAmount <> 0 (Partial paid or Paid)
            if (!ZYPConstant.FLG_ON_Y.equals(pmtCreditFlg)) {
                if (ZYPCommonFunc.hasValue(easyPac1Msg)) {
                    strList.add(easyPac1Msg);
                } else {
                    strList.add(" ");
                }
                strList.add(creditAndRebillMsg);
                strList.add(fixedWord);
                strList.add(invPrtOfConslBillMsg);
                stackStr.append(comment1).append(comment2).append(comment3).append(comment4);
                if (hasValue(stackStr.toString())) strList.add(" ");
                strList.add(stackStr.toString());
                if (hasValue(messageBody)) strList.add(" ");
                strList.add(messageBody);

                returnStr = setComment(strList);
            } else {
                stackStr.append(invCcMsg02).append(creditCard4Digit);
                if (ZYPCommonFunc.hasValue(stackStr.toString())) {
                    stackStr.append(" ");
                }
                strList.add(stackStr.toString());
                // 2019/09/04 QC#53254 Mod Start
//                strList.add(invCcMsg03);
                if (chkCrCardChrgCpltCd(rs.getString("CR_CARD_CHRG_CPLT_CD"))) {
                    strList.add(invCcMsg03);
                } else {
                    strList.add(invCcMsg04);
                }
                // 2019/09/04 QC#53254 Mod End
                if (!easyPac1Msg.isEmpty()) {
                    strList.add(easyPac1Msg);
                } else {
                    strList.add(" ");
                }
                strList.add(creditAndRebillMsg);
                strList.add(fixedWord);
                strList.add(invPrtOfConslBillMsg);
                stackStr = new StringBuilder();
                stackStr.append(comment1).append(comment2).append(comment3).append(comment4);
                if (hasValue(stackStr.toString())) strList.add(" ");
                strList.add(stackStr.toString());
                if (hasValue(messageBody)) strList.add(" ");
                strList.add(messageBody);

                returnStr = setComment(strList);
            }
        // 06/12/2019 QC#50817 Del Start
        //}
        // 06/12/2019 QC#50817 Del End
        // add the consolidated component message
        return cutOffString(returnStr, INV_CMT_CUT_OFF_LEN);  
    }

    //Max Line length should be 130.
    private String setComment(List<String> strList) {
        StringBuilder stackStr = new StringBuilder();
        boolean firstCommetnFlg = true;
        //Loop List
        for (int i = 0; i < strList.size(); i++) {
            //Line Data
            String lineMsg = strList.get(i);
            if (lineMsg != null && !"".equals(lineMsg)) {
                //Add Return Code without First Line.
                if (!firstCommetnFlg) {
                    stackStr.append(ENTER);
                }
                //Split Line Data by Return Code
                String[] splitMsgLine = lineMsg.split(ENTER);
                boolean firstCommetnFlg2 = true;
                //Loop Line Data
                for( String lineData : splitMsgLine ) {
                    //Add Return Code without First Line.
                    if (!firstCommetnFlg2) {
                        stackStr.append(ENTER);
                    }
                    //If length is over INV_LINE_CMT_CUT_OFF_LEN, Split by INV_LINE_CMT_CUT_OFF_LEN.
                    if (hasValue(lineData) && lineData.length() > INV_LINE_CMT_CUT_OFF_LEN) {
                        int loopCnt = 0;
                        if (lineData.length() % INV_LINE_CMT_CUT_OFF_LEN == 0) {
                            loopCnt = lineData.length() / INV_LINE_CMT_CUT_OFF_LEN;
                        } else {
                            loopCnt = lineData.length() / INV_LINE_CMT_CUT_OFF_LEN + 1;
                        }
                        for (int c = 0; c < loopCnt; c++) {
                            int start = c * INV_LINE_CMT_CUT_OFF_LEN;
                            int end = 0;
                            if ((c+1)*INV_LINE_CMT_CUT_OFF_LEN >= lineData.length()) {
                                end = lineData.length();
                                stackStr.append(lineData.substring(start, end));
                            } else {
                                end = (c+1)*INV_LINE_CMT_CUT_OFF_LEN;
                                stackStr.append(lineData.substring(start, end));
                                stackStr.append(ENTER);
                            }
                        }
                    } else {
                        stackStr.append(lineData);
                    }
                    firstCommetnFlg2 = false;
                }
                
                firstCommetnFlg = false;
            }
        }
        return stackStr.toString();
    }

    private String getCustInvSrcCd(String dsBizAreaCd) {

        if (DS_BIZ_AREA.EQUIPMENT.equals(dsBizAreaCd)) {
            return CUST_INV_SRC.EQUIPMENT;
        } else if (DS_BIZ_AREA.SUPPLIES.equals(dsBizAreaCd)) {
            return CUST_INV_SRC.SUPPLIES;
        } else if (DS_BIZ_AREA.PARTS.equals(dsBizAreaCd)) { //QC#26826
            // 2018/11/17 Mod Start QC#27954
//            return CUST_INV_SRC.SUPPLIES;
            return CUST_INV_SRC.PARTS;
            // 2018/11/17 Mod End QC#27954
        } else if (DS_BIZ_AREA.CONTRACTS.equals(dsBizAreaCd)) {
            return CUST_INV_SRC.SERVICE_CONTRACT;
        } else if (DS_BIZ_AREA.FIELD_SERVICE.equals(dsBizAreaCd)) {
            return CUST_INV_SRC.SERVICE_CALL;
        //QC#17962 add Start
        } else if (DS_BIZ_AREA.INVOICING.equals(dsBizAreaCd)) {
            return CUST_INV_SRC.INVOICING;
        //QC#17962 add End
        }
        return "";
    }

    private NMZC610001PMsg getNMZC610001PMsgForMessage(String lineBizTpCd, String billToCustCd) {
        
        //Key = lineBizTpCd + dsBizAreaCd + billToCustCd + dsCustMsgTpCd
        String key = createCacheKey(NMZC610001PMSG_FOR_MSG, lineBizTpCd + billToCustCd);
        NMZC610001PMsg nmzc6100PMsgForMessage = (NMZC610001PMsg) this.cache.get(key);
        if (nmzc6100PMsgForMessage == null) {
            nmzc6100PMsgForMessage = new NMZC610001PMsg();
            ZYPEZDItemValueSetter.setValue(nmzc6100PMsgForMessage.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(nmzc6100PMsgForMessage.xxModeCd, NMZC610001Constant.PROCESS_MODE_INSTRUCTION_FOR_INV_PRINT);
            ZYPEZDItemValueSetter.setValue(nmzc6100PMsgForMessage.lineBizTpCd, lineBizTpCd);
            //QC#16034
            // Mod Start 2019/01/26 QC#30071
            //nmzc6100PMsgForMessage.inDsBizAreaCdList.setValidCount(7);
            nmzc6100PMsgForMessage.inDsBizAreaCdList.setValidCount(8);
            // Mod End 2019/01/26 QC#30071
            nmzc6100PMsgForMessage.inDsBizAreaCdList.no(0).dsBizAreaCd.setValue(DS_BIZ_AREA.EQUIPMENT);
            nmzc6100PMsgForMessage.inDsBizAreaCdList.no(1).dsBizAreaCd.setValue(DS_BIZ_AREA.SUPPLIES);
            nmzc6100PMsgForMessage.inDsBizAreaCdList.no(2).dsBizAreaCd.setValue(DS_BIZ_AREA.CONTRACTS);
            nmzc6100PMsgForMessage.inDsBizAreaCdList.no(3).dsBizAreaCd.setValue(DS_BIZ_AREA.FIELD_SERVICE);
            nmzc6100PMsgForMessage.inDsBizAreaCdList.no(4).dsBizAreaCd.setValue(DS_BIZ_AREA.CUSTOMER);
            nmzc6100PMsgForMessage.inDsBizAreaCdList.no(5).dsBizAreaCd.setValue(DS_BIZ_AREA.INVOICING);
            nmzc6100PMsgForMessage.inDsBizAreaCdList.no(6).dsBizAreaCd.setValue(DS_BIZ_AREA.COLLECTIONS);
            // Add Start 2019/01/26 QC#30071
            nmzc6100PMsgForMessage.inDsBizAreaCdList.no(7).dsBizAreaCd.setValue(DS_BIZ_AREA.PARTS);
            // Add End 2019/01/26 QC#30071
            ZYPEZDItemValueSetter.setValue(nmzc6100PMsgForMessage.billToCustCd, billToCustCd);
            ZYPEZDItemValueSetter.setValue(nmzc6100PMsgForMessage.slsDt, ZYPDateUtil.getSalesDate());
            //QC#16034
            nmzc6100PMsgForMessage.inDsCustMsgTpCdList.setValidCount(1);
            nmzc6100PMsgForMessage.inDsCustMsgTpCdList.no(0).dsCustMsgTpCd.setValue(DS_CUST_MSG_TP.MSG);
            //nmzc6100PMsgForMessage.inDsCustMsgTpCdList.no(1).dsCustMsgTpCd.setValue(DS_CUST_MSG_TP.NOTE);
            //nmzc6100PMsgForMessage.inDsCustMsgTpCdList.no(2).dsCustMsgTpCd.setValue(DS_CUST_MSG_TP.SPCL_HANDLING);
            new NMZC610001().execute(nmzc6100PMsgForMessage, ONBATCH_TYPE.BATCH);
            this.cache.put(key, nmzc6100PMsgForMessage);
        }
        return nmzc6100PMsgForMessage;
    }
    
    private String getCustInstInfoApi(String lineBizTpCd, String billToCustCd, String bizAreaCd) {
        
        NMZC610001PMsg pMsg = getNMZC610001PMsgForMessage(lineBizTpCd, billToCustCd);
        Map<String, String> map = getMessageBody(pMsg);
        
        List<String> messageList = new ArrayList<String>();
        String messageBody = null;
        // add start 2023/07/18 QC#61421
        messageList.add(map.get(DS_BIZ_AREA.ALL + DS_CUST_MSG_TP.MSG));
        // add end 2023/07/18 QC#61421
        messageList.add(map.get(DS_BIZ_AREA.CUSTOMER + DS_CUST_MSG_TP.MSG));
        messageList.add(map.get(DS_BIZ_AREA.CUSTOMER + DS_CUST_MSG_TP.NOTE));
        messageList.add(map.get(DS_BIZ_AREA.CUSTOMER + DS_CUST_MSG_TP.SPCL_HANDLING));
        messageBody = setComment(messageList);
        if (!ZYPCommonFunc.hasValue(messageBody)) {
            messageList = new ArrayList<String>();
            messageList.add(map.get(DS_BIZ_AREA.INVOICING + DS_CUST_MSG_TP.MSG));
            messageList.add(map.get(DS_BIZ_AREA.INVOICING + DS_CUST_MSG_TP.NOTE));
            messageList.add(map.get(DS_BIZ_AREA.INVOICING + DS_CUST_MSG_TP.SPCL_HANDLING));
            messageBody = setComment(messageList);
            
            if (!ZYPCommonFunc.hasValue(messageBody)) {
                messageList = new ArrayList<String>();
                messageList.add(map.get(DS_BIZ_AREA.COLLECTIONS + DS_CUST_MSG_TP.MSG));
                messageList.add(map.get(DS_BIZ_AREA.COLLECTIONS + DS_CUST_MSG_TP.NOTE));
                messageList.add(map.get(DS_BIZ_AREA.COLLECTIONS + DS_CUST_MSG_TP.SPCL_HANDLING));
                messageBody = setComment(messageList);
            }
        }
        if (DS_BIZ_AREA.EQUIPMENT.equals(bizAreaCd)) {
            messageList.add(map.get(DS_BIZ_AREA.EQUIPMENT + DS_CUST_MSG_TP.MSG));
            messageList.add(map.get(DS_BIZ_AREA.EQUIPMENT + DS_CUST_MSG_TP.NOTE));
            messageList.add(map.get(DS_BIZ_AREA.EQUIPMENT + DS_CUST_MSG_TP.SPCL_HANDLING));
            // Mod Start 2019/01/26 QC#30071
        //} else if (DS_BIZ_AREA.SUPPLIES.equals(bizAreaCd) || DS_BIZ_AREA.PARTS.equals(bizAreaCd)) {  //QC#26826
        } else if (DS_BIZ_AREA.SUPPLIES.equals(bizAreaCd)) {
            // Mod End 2019/01/26 QC#30071
            messageList.add(map.get(DS_BIZ_AREA.SUPPLIES + DS_CUST_MSG_TP.MSG));
            messageList.add(map.get(DS_BIZ_AREA.SUPPLIES + DS_CUST_MSG_TP.NOTE));
            messageList.add(map.get(DS_BIZ_AREA.SUPPLIES + DS_CUST_MSG_TP.SPCL_HANDLING));
        } else if (DS_BIZ_AREA.CONTRACTS.equals(bizAreaCd)) {
            messageList.add(map.get(DS_BIZ_AREA.CONTRACTS + DS_CUST_MSG_TP.MSG));
            messageList.add(map.get(DS_BIZ_AREA.CONTRACTS + DS_CUST_MSG_TP.NOTE));
            messageList.add(map.get(DS_BIZ_AREA.CONTRACTS + DS_CUST_MSG_TP.SPCL_HANDLING));
        } else if (DS_BIZ_AREA.FIELD_SERVICE.equals(bizAreaCd)) {
            messageList.add(map.get(DS_BIZ_AREA.FIELD_SERVICE + DS_CUST_MSG_TP.MSG));
            messageList.add(map.get(DS_BIZ_AREA.FIELD_SERVICE + DS_CUST_MSG_TP.NOTE));
            messageList.add(map.get(DS_BIZ_AREA.FIELD_SERVICE + DS_CUST_MSG_TP.SPCL_HANDLING));
            // Add Start 2019/01/26 QC#30071
        } else if (DS_BIZ_AREA.PARTS.equals(bizAreaCd)) {
            messageList.add(map.get(DS_BIZ_AREA.PARTS + DS_CUST_MSG_TP.MSG));
            messageList.add(map.get(DS_BIZ_AREA.PARTS + DS_CUST_MSG_TP.NOTE));
            messageList.add(map.get(DS_BIZ_AREA.PARTS + DS_CUST_MSG_TP.SPCL_HANDLING));
            // Add End 2019/01/26 QC#30071
        }
        messageBody = setComment(messageList);

        return messageBody;
    }

    private Map<String, String> getMessageBody(NMZC610001PMsg pMsg) {

        NMZC610001_InstructionListPMsgArray instListArray = pMsg.InstructionList;
        Map<String, String> map = new HashMap<String, String>();
        //QC#16034
        //CUSTOMER
        StringBuffer msg00_10 = new StringBuffer();
        //StringBuffer msg00_20 = new StringBuffer();
        //StringBuffer msg00_30 = new StringBuffer();
        //EQUIPMENT
        StringBuffer msg10_10 = new StringBuffer();
        //StringBuffer msg10_20 = new StringBuffer();
        //StringBuffer msg10_30 = new StringBuffer();
        //SUPPLIES
        StringBuffer msg20_10 = new StringBuffer();
        //StringBuffer msg20_20 = new StringBuffer();
        //StringBuffer msg20_30 = new StringBuffer();
        //CONTRACTS
        StringBuffer msg30_10 = new StringBuffer();
        //StringBuffer msg30_20 = new StringBuffer();
        //StringBuffer msg30_30 = new StringBuffer();
        //FIELD SERVICE
        StringBuffer msg40_10 = new StringBuffer();
        //StringBuffer msg40_20 = new StringBuffer();
        //StringBuffer msg40_30 = new StringBuffer();
        //INVOICING
        StringBuffer msg50_10 = new StringBuffer();
        //StringBuffer msg50_20 = new StringBuffer();
        //StringBuffer msg50_30 = new StringBuffer();
        //COLLECTIONS
        StringBuffer msg60_10 = new StringBuffer();
        //StringBuffer msg60_20 = new StringBuffer();
        //StringBuffer msg60_30 = new StringBuffer();

        // Add Start 2019/01/26 QC#30071
        StringBuffer msg70_10 = new StringBuffer();
        // Add End 2019/01/26 QC#30071

        // add start 2023/07/18 QC#61421
        StringBuffer msg80_10 = new StringBuffer();
        // add end 2023/07/18 QC#61421

        for (int i = 0; i < instListArray.getValidCount(); i++) {
            NMZC610001_InstructionListPMsg instMsg = instListArray.no(i);
            if (!ZYPConstant.FLG_ON_Y.equals(instMsg.dsPrintOnInvFlg.getValue())) {
                continue;
            }
            if (DS_BIZ_AREA.CUSTOMER.equals(instMsg.dsBizAreaCd.getValue())) {
                if (DS_CUST_MSG_TP.MSG.equals(instMsg.dsCustMsgTpCd.getValue())) {
                    msg00_10.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                //QC#16034
                //} else if (DS_CUST_MSG_TP.NOTE.equals(instMsg.dsCustMsgTpCd.getValue())) {
                //    msg00_20.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                //} else {
                //    msg00_30.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                }
            } else if (DS_BIZ_AREA.EQUIPMENT.equals(instMsg.dsBizAreaCd.getValue())) {
                if (DS_CUST_MSG_TP.MSG.equals(instMsg.dsCustMsgTpCd.getValue())) {
                    msg10_10.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                //QC#16034
                //} else if (DS_CUST_MSG_TP.NOTE.equals(instMsg.dsCustMsgTpCd.getValue())) {
                //    msg10_20.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                //} else {
                //    msg10_30.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                }
                // Mod Start 2019/01/26 QC#30071
            //} else if (DS_BIZ_AREA.SUPPLIES.equals(instMsg.dsBizAreaCd.getValue()) || DS_BIZ_AREA.PARTS.equals(instMsg.dsBizAreaCd.getValue())) {  //QC#26826
            } else if (DS_BIZ_AREA.SUPPLIES.equals(instMsg.dsBizAreaCd.getValue())) {
                // Mod End 2019/01/26 QC#30071
                if (DS_CUST_MSG_TP.MSG.equals(instMsg.dsCustMsgTpCd.getValue())) {
                    msg20_10.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                //QC#16034
                //} else if (DS_CUST_MSG_TP.NOTE.equals(instMsg.dsCustMsgTpCd.getValue())) {
                //    msg20_20.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                //} else {
                //    msg20_30.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                }
            } else if (DS_BIZ_AREA.CONTRACTS.equals(instMsg.dsBizAreaCd.getValue())) {
                if (DS_CUST_MSG_TP.MSG.equals(instMsg.dsCustMsgTpCd.getValue())) {
                    msg30_10.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                //QC#16034
                //} else if (DS_CUST_MSG_TP.NOTE.equals(instMsg.dsCustMsgTpCd.getValue())) {
                //    msg30_20.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                //} else {
                //    msg30_30.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                }
            } else if (DS_BIZ_AREA.FIELD_SERVICE.equals(instMsg.dsBizAreaCd.getValue())) {
                if (DS_CUST_MSG_TP.MSG.equals(instMsg.dsCustMsgTpCd.getValue())) {
                    msg40_10.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                //QC#16034
                //} else if (DS_CUST_MSG_TP.NOTE.equals(instMsg.dsCustMsgTpCd.getValue())) {
                //    msg40_20.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                //} else {
                //    msg40_30.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                }
            } else if (DS_BIZ_AREA.INVOICING.equals(instMsg.dsBizAreaCd.getValue())) {
                if (DS_CUST_MSG_TP.MSG.equals(instMsg.dsCustMsgTpCd.getValue())) {
                    msg50_10.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                //QC#16034
                //} else if (DS_CUST_MSG_TP.NOTE.equals(instMsg.dsCustMsgTpCd.getValue())) {
                //    msg50_20.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                //} else {
                //    msg50_30.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                }
            } else if (DS_BIZ_AREA.COLLECTIONS.equals(instMsg.dsBizAreaCd.getValue())) {
                if (DS_CUST_MSG_TP.MSG.equals(instMsg.dsCustMsgTpCd.getValue())) {
                    msg60_10.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                //QC#16034
                //} else if (DS_CUST_MSG_TP.NOTE.equals(instMsg.dsCustMsgTpCd.getValue())) {
                //    msg60_20.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                //} else {
                //    msg60_30.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                }
                // Add Start 2019/01/26 QC#30071
            } else if (DS_BIZ_AREA.PARTS.equals(instMsg.dsBizAreaCd.getValue())) {
                if (DS_CUST_MSG_TP.MSG.equals(instMsg.dsCustMsgTpCd.getValue())) {
                    msg70_10.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                }
                // Add End 2019/01/26 QC#30071
            // add start 2023/07/18 QC#61421
            } else if (DS_BIZ_AREA.ALL.equals(instMsg.dsBizAreaCd.getValue())) {
                if (DS_CUST_MSG_TP.MSG.equals(instMsg.dsCustMsgTpCd.getValue())) {
                    msg80_10.append(replaceSystemLineSeparator(instMsg.dsCustMsgTxt.getValue()));
                }
            // add end 2023/07/18 QC#61421
            }
        }
        
        map.put(DS_BIZ_AREA.CUSTOMER + DS_CUST_MSG_TP.MSG, msg00_10.toString());
        //map.put(DS_BIZ_AREA.CUSTOMER + DS_CUST_MSG_TP.NOTE , msg00_20.toString());
        //map.put(DS_BIZ_AREA.CUSTOMER + DS_CUST_MSG_TP.SPCL_HANDLING, msg00_30.toString());
        
        map.put(DS_BIZ_AREA.EQUIPMENT + DS_CUST_MSG_TP.MSG, msg10_10.toString());
        //map.put(DS_BIZ_AREA.EQUIPMENT + DS_CUST_MSG_TP.NOTE , msg10_20.toString());
        //map.put(DS_BIZ_AREA.EQUIPMENT + DS_CUST_MSG_TP.SPCL_HANDLING, msg10_30.toString());

        map.put(DS_BIZ_AREA.SUPPLIES + DS_CUST_MSG_TP.MSG, msg20_10.toString());
        //map.put(DS_BIZ_AREA.SUPPLIES + DS_CUST_MSG_TP.NOTE , msg20_20.toString());
        //map.put(DS_BIZ_AREA.SUPPLIES + DS_CUST_MSG_TP.SPCL_HANDLING, msg20_30.toString());

        map.put(DS_BIZ_AREA.CONTRACTS + DS_CUST_MSG_TP.MSG, msg30_10.toString());
        //map.put(DS_BIZ_AREA.CONTRACTS + DS_CUST_MSG_TP.NOTE , msg30_20.toString());
        //map.put(DS_BIZ_AREA.CONTRACTS + DS_CUST_MSG_TP.SPCL_HANDLING, msg30_30.toString());

        map.put(DS_BIZ_AREA.FIELD_SERVICE + DS_CUST_MSG_TP.MSG, msg40_10.toString());
        //map.put(DS_BIZ_AREA.FIELD_SERVICE + DS_CUST_MSG_TP.NOTE , msg40_20.toString());
        //map.put(DS_BIZ_AREA.FIELD_SERVICE + DS_CUST_MSG_TP.SPCL_HANDLING, msg40_30.toString());

        map.put(DS_BIZ_AREA.INVOICING + DS_CUST_MSG_TP.MSG, msg50_10.toString());
        //map.put(DS_BIZ_AREA.INVOICING + DS_CUST_MSG_TP.NOTE , msg50_20.toString());
        //map.put(DS_BIZ_AREA.INVOICING + DS_CUST_MSG_TP.SPCL_HANDLING, msg50_30.toString());

        map.put(DS_BIZ_AREA.COLLECTIONS + DS_CUST_MSG_TP.MSG, msg60_10.toString());
        //map.put(DS_BIZ_AREA.COLLECTIONS + DS_CUST_MSG_TP.NOTE , msg60_20.toString());
        //map.put(DS_BIZ_AREA.COLLECTIONS + DS_CUST_MSG_TP.SPCL_HANDLING, msg60_30.toString());

        // Add Start 2019/01/26 QC#30071
        map.put(DS_BIZ_AREA.PARTS + DS_CUST_MSG_TP.MSG, msg70_10.toString());
        // Add End 2019/01/26 QC#30071

        // add start 2023/07/18 QC#61421
        map.put(DS_BIZ_AREA.ALL + DS_CUST_MSG_TP.MSG, msg80_10.toString());
        // add end 2023/07/18 QC#61421
        return map;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getFooterComment() {
        List<Map<String, Object>> footerMsgList = null;
        String key = createCacheKey(FOOTER_COMMENT);
        footerMsgList = (List<Map<String, Object>>) this.cache.get(key);
        if (footerMsgList == null) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            footerMsgList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFooterComment", ssmParam);
            this.cache.put(key, footerMsgList);
        }
        return footerMsgList;
    }

    private void setInvPrtCtrl(INV_PRT_CTRLTMsg invPrtCtrlTmsg, INV_PRT_HDRTMsg invPrtHdrTmsg,
            String custBllgTpCd, ResultSet rs, boolean isEasyPac1Flg, 
            Map<String, Object> preConslBillInfoForCtrlMap, 
            String billVehicleCd) //QC#26826
            throws SQLException, NWCB010001BusinessException {


        String invTp = rs.getString(INV_TP_CD);
        BigDecimal totalAmt = rs.getBigDecimal(INV_AMT);
        String formatKey = rs.getString(FORMAT_KEY);
        String printFlg = rs.getString(INV_PRINT_FLG);
        String printStsCd = rs.getString(INV_PRINT_STS_CD);
        String invSrcTpCd = rs.getString(INV_SRC_TP);
        String crRebillRsnCatgCd = rs.getString(CR_REBIL_RSN_CATG_CD); //QC#22278

        // Set Common Items
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invNum, rs.getString(INV_NUM));
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invImageTpCd, "1");
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invCratTs, this.currentSystemTs);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.billToDsAcctNum, rs.getString(BILL_TO_CD));
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.billToDsAcctNm, rs.getString(BILL_TO_NM));
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.billToCustCd, rs.getString(BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.billToLocNum, rs.getString(BILL_TO_LOC_NUM));
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.reprInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.splyPgmInvRvwFlg, ZYPConstant.FLG_OFF_N);
        if (preConslBillInfoForCtrlMap != null && preConslBillInfoForCtrlMap.get(CONSL_BILL_NUM) != null) {
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.conslBillNum, (String) preConslBillInfoForCtrlMap.get(CONSL_BILL_NUM));
        }
        if (preConslBillInfoForCtrlMap != null && preConslBillInfoForCtrlMap.get(CONSL_BILL_INV_DT) != null) {
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.conslBillInvDt, (String) preConslBillInfoForCtrlMap.get(CONSL_BILL_INV_DT));
        }
        if (preConslBillInfoForCtrlMap != null && preConslBillInfoForCtrlMap.get("CONSL_BILL_TOT_AMT") != null) {
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.conslBillTotAmt, (BigDecimal) preConslBillInfoForCtrlMap.get(CONSL_BILL_TOT_AMT));
        }
        if (preConslBillInfoForCtrlMap != null && preConslBillInfoForCtrlMap.get(CONSL_BILL_INV_CRAT_PSN_CD) != null) {
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.conslBillInvCratPsnCd, (String) preConslBillInfoForCtrlMap.get(CONSL_BILL_INV_CRAT_PSN_CD));
        }
        if (preConslBillInfoForCtrlMap != null && preConslBillInfoForCtrlMap.get(ORIG_CONSL_BILL_NUM) != null) {
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.origConslBillNum, (String) preConslBillInfoForCtrlMap.get(ORIG_CONSL_BILL_NUM));
        }
        
        //QC#26846
        if (hasValue(billVehicleCd) && CUST_BLLG_VCLE.WEB_BILLING_PRINT_ONLY.equals(billVehicleCd)) {
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invPrtCtrlRecCd, INV_PRT_CTRL_REC.OM_INVOICES);
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invPrtBrCd, INV_PRT_BR.WEB_BILLING_PRINT_ONLY);
            invPrtCtrlTmsg.invPrtBatTpCd.clear();
        } else if (hasValue(billVehicleCd) && CUST_BLLG_VCLE.WEB_BILLING_PRINT_AND_EMAIL.equals(billVehicleCd)) {
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invPrtCtrlRecCd, INV_PRT_CTRL_REC.OM_INVOICES);
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invPrtBrCd, INV_PRT_BR.WEB_BILLING_PRINT_AND_EMAIL);
            invPrtCtrlTmsg.invPrtBatTpCd.clear();
        // 2019/01/15 QC#29371 SRNO#13 Add Start
        } else if (hasValue(billVehicleCd) && CUST_BLLG_VCLE.STRATEGIC_ACCOUNTS_PRINT_ONLY.equals(billVehicleCd)) {
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invPrtCtrlRecCd, INV_PRT_CTRL_REC.OM_INVOICES);
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invPrtBrCd, INV_PRT_BR.STRATEGIC_ACCOUNTS_PRINT_ONLY);
            invPrtCtrlTmsg.invPrtBatTpCd.clear();
        } else if (hasValue(billVehicleCd) && CUST_BLLG_VCLE.STRATEGIC_ACCOUNTS_PRINT_AND_EMAIL.equals(billVehicleCd)) {
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invPrtCtrlRecCd, INV_PRT_CTRL_REC.OM_INVOICES);
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invPrtBrCd, INV_PRT_BR.STRATEGIC_ACCOUNTS_PRINT_AND_EMAIL);
            invPrtCtrlTmsg.invPrtBatTpCd.clear();
        // 2019/01/15 QC#29371 SRNO#13 Add End
        } else {
            if (FORMAT_TP_SVC.equals(formatKey) && SVC_INV_SRC_TP.CONTRACT.equals(invSrcTpCd)) {
                ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invPrtCtrlRecCd, INV_PRT_CTRL_REC.CONTRACT_INVOICES);
                invPrtCtrlTmsg.invPrtBrCd.clear();
                //SP:SINGLE PAGE, MP:MULTIPLE PAGE is correct? Should this batch update this?
                ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invPrtBatTpCd, rs.getString(BAT_TP));
            } else {
                ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invPrtCtrlRecCd, INV_PRT_CTRL_REC.OM_INVOICES);
                ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invPrtBrCd, 
                        getInvPrtBrCd(rs.getString(DS_ORD_CATG_CD),rs.getString(DS_ORD_TP_CD),rs.getString(DS_ORD_RSN_CD),
                                rs.getString(SLS_REP_TOC_CD), rs.getString(SYS_SRC_CD), rs.getString(DS_INV_TP)));
                invPrtCtrlTmsg.invPrtBatTpCd.clear();
            }
        }

        // Consolidated Term "Daily" & Not Easy Pac1
        if (CUST_BLLG_TP.DAILY.equals(custBllgTpCd) && !isEasyPac1Flg) {
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtHdrTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);

            if (INV_TP.CREDIT_MEMO.equals(invTp)) {
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (BigDecimal.ZERO.compareTo(totalAmt) == 0) {
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            //QC#18677
            //if (FORMAT_TP_SVC.equals(formatKey) && ZYPConstant.FLG_OFF_N.equals(printFlg) && invPrintLimit.compareTo(totalAmt) > 0) {
            if (FORMAT_TP_SVC.equals(formatKey) && invPrintLimit.compareTo(totalAmt) > 0) {
                //if (totalAmt.compareTo(new BigDecimal(5)) < 0) {
                    invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                //}
            }

            if (ZYPConstant.FLG_OFF_N.equals(printFlg)) {
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                invPrtCtrlTmsg.invFtpProcStsCd.setValue(PROC_STS.IN_COMPLETED);
            }
            if (INV_PRT_STS_0.equals(printStsCd)) {
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            
            //QC#22278
            // Case Daily and Internal Rebill
            //   Credit Rebill Reason Category is Internal 
            //   Invoice Type = Invoice
            if (CR_REBIL_RSN_CATG.INTERNAL.equals(crRebillRsnCatgCd)
                    && INV_TP.INVOICE.equals(invTp)) {
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
//QC#25491
//                invPrtCtrlTmsg.invFtpProcStsCd.setValue(PROC_STS.IN_COMPLETED);
            }
//            if (ZYPConstant.FLG_ON_Y.equals(pmtCreditFlg)) {
//                // invPrtHdrTmsg.
//                invPrtHdrTmsg.invBalAmtTxt.setValue("0");
//            }

            // Consolidated Term "Consolidated"
        // 2019/11/01 QC#53873 Mod Start
//        } else if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd) || isEasyPac1Flg) {
        } else if ((CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd) || isEasyPac1Flg) && ZYPConstant.FLG_ON_Y.equals(rs.getString(CONS_BILL_STATUS))) {
       // 2019/11/01 QC#53873 Mod End
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtHdrTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);

            if (ZYPConstant.FLG_OFF_N.equals(printFlg)) {
                invPrtCtrlTmsg.invFtpProcStsCd.setValue(PROC_STS.IN_COMPLETED);
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (INV_PRT_STS_0.equals(printStsCd)) {
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (BigDecimal.ZERO.compareTo(totalAmt) == 0) {
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
        //QC#20141 add Start
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.espacFlg, createEasyPac1Flg(isEasyPac1Flg));
        //QC#20141 add End

        // 2019/11/01 QC#53873 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(rs.getString(CONS_BILL_STATUS))) {
        // 2019/11/01 QC#53873 Add End
            // START 2018/08/20 M.Naito [QC#13309, ADD]
            if (checkTempEttl(invPrtCtrlTmsg.invNum.getValue())) {
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
                invPrtHdrTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
            // END 2018/08/20 M.Naito [QC#13309, ADD]
            // START 2018/11/14 K.Fujimoto [QC#28627, ADD]
            if (checkContrLinkNum(invPrtCtrlTmsg.invNum.getValue())) {
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
                invPrtHdrTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
            // END   2018/11/14 K.Fujimoto [QC#28627, ADD]
            // 2019/11/01 QC#53873 Add Start
            if (isCreditRebill(invPrtCtrlTmsg) && isOrigCons(rs.getString(ORIG_INV_NUM))){
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
                invPrtHdrTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
            
        } else {
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtHdrTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        // 2019/11/01 QC#53873 Add End

    }

    //QC#20141 add Start
    private String createEasyPac1Flg(boolean isEasyPac1Flg){
        if (isEasyPac1Flg) {
            return ZYPConstant.FLG_ON_Y;
        } else{
            return ZYPConstant.FLG_OFF_N;
        }
    }
    //QC#20141 add Start

    // START 2022/07/20 R.Onozuka [QC#59669, MOD]
    //private void setInvPrtHdr(INV_PRT_HDRTMsg invPrtHdrTmsg, ResultSet rs, String hederComment, Map<String, Object> footerMsgMap)
    private void setInvPrtHdr(INV_PRT_HDRTMsg invPrtHdrTmsg, ResultSet rs, String hederComment, Map<String, Object> footerMsgMap, Map<String, Object> preConslBillInfoForCtrlMap)
    // END 2022/07/20 R.Onozuka [QC#59669, MOD]
            throws SQLException, NWCB010001BusinessException {
        String pmtCreditFlg = rs.getString(PMT_CC_FLG);
        String ccySgnTxt = rs.getString(CCY_SGN_TXT) == null ? "" : rs.getString(CCY_SGN_TXT);

        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invBillNum, rs.getString(INV_NUM));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invDt, rs.getString(INV_DT));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invDtTxt, getDtTxt(rs.getString(INV_DT)));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.netDueDtTxt, getDtTxt(rs.getString(PAST_DUE_AFT)));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invTotAmtTxt, setNegate(rs.getString(INV_AMT_TXT), rs.getBigDecimal(INV_AMT), rs.getString(INV_TP_CD)));
//        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invBalAmtTxt, setNegate(rs.getString(BALANCE_DUE_TXT), rs.getBigDecimal(BALANCE_DUE), rs.getString(INV_TP_CD)));
        //QC#30687 mod Start
        //if (ZYPConstant.FLG_ON_Y.equals(pmtCreditFlg)) {
        if (ZYPConstant.FLG_ON_Y.equals(pmtCreditFlg) && chkCrCardChrgCpltCd(rs.getString("CR_CARD_CHRG_CPLT_CD"))) {
        //QC#30687 mod end
            invPrtHdrTmsg.invBalAmtTxt.setValue(ccySgnTxt + "0.00");
        } else {
            ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invBalAmtTxt, setNegate(rs.getString(BALANCE_DUE_TXT), rs.getBigDecimal(BALANCE_DUE), rs.getString(INV_TP_CD)));
        }
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invTotChrgAmtTxt, setNegate(rs.getString(INV_CHARGE_AMT_TXT), rs.getBigDecimal(INV_CHARGE_AMT), rs.getString(INV_TP_CD)));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invFrtAmtTxt, setNegate(rs.getString(FRT_AMT_TXT), rs.getBigDecimal(FRT_AMT), rs.getString(INV_TP_CD)));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invFrtTaxAmtTxt, setNegate(rs.getString(TAX_AMT_TXT), rs.getBigDecimal(TAX_AMT), rs.getString(INV_TP_CD)));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invPrePmtAmtTxt, setNegate(rs.getString(PRE_PMT_AMT_TXT), rs.getBigDecimal(PRE_PMT_AMT), rs.getString(INV_TP_CD)));

        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToCustCd, rs.getString(BILL_TO_CD));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToLocNm, cutOffString(rs.getString(BILL_TO_NM), BILL_TO_ACCT_NM_CUT_OFF_LEN));
        if (hasValue(rs.getString(BILL_TO_ATTN))) {
            ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToAttnTxt, rs.getString(BILL_TO_ATTN));
        } else {
            ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToAttnTxt, defBillToAttn);
        }
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToFirstLineAddr, rs.getString(BILL_TO_FIRST_ADDR));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToScdLinePrntAddr, rs.getString(BILL_TO_SCD_ADDR));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToCtyAddr, rs.getString(BILL_TO_CTY));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToStCd, rs.getString(BILL_TO_ST));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToPostCd, rs.getString(BILL_TO_ZIP_CD));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.remToEinCd, rs.getString(REM_TO_EIN_CD));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.remToLocNm, rs.getString(REM_TO_NM));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.remToFirstLineAddr, rs.getString(REM_TO_FIRST_ADDR));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.remToScdLinePrntAddr, rs.getString(REM_TO_SCD_ADDR));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.remToCtyAddr, rs.getString(REM_TO_CTY));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.remToStCd, rs.getString(REM_TO_ST));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.remToPostCd, rs.getString(REM_TO_POST));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.pmtTermCd, rs.getString(PMT_TERM_CD));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.pmtTermNm, rs.getString(TERMS));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invPrtHdrCmntTxt, hederComment);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.dplyTaxDtlFlg, rs.getString(TAX_DETAIL_FLG));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.dplyMdseDtlFlg, rs.getString(DTL_DPLY_FLG));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invCmpyFirstLineAddr, cmpyFrstAddr);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invCmpyScdLinePrntAddr, cmpyScdAddr);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invCmpyCtyAddr, cmpyCtyAddr);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invCmpyStCd, cmpyState);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invCmpyPostCd, cmpyPost);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invCmpyTelNum, cmpyTel);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invCmpyUrl, cmpyUrl);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.pmtTermCashDiscCd, rs.getString(PMT_TERM_CASH_DISC_CD));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.pmtTermCashDiscDescTxt, rs.getString(PMT_TERM_CASH_DISC_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.lineBizTpCd, rs.getString(LINE_BIZ_TP_CD)); // QC#54520 2019/11/11 Add

        int i = 1;
        String footerComment1 = null;
        String footerComment2 = null;
        String footerComment3 = null;
        // String footKey = (String) footerMsgMap.get(USG_INV_DESC_CD);
        if (footerMsgMap.containsKey(USG_INV_DESC.ALLOWANCE)) {
            footerComment1 = (String) footerMsgMap.get(USG_INV_DESC.ALLOWANCE);
            footerComment1 = "(" + String.valueOf(i) + ") - " + footerComment1;
            i++;
        }
        if (footerMsgMap.containsKey(USG_INV_DESC.BILLABLE_COPIES)) {
            footerComment2 = (String) footerMsgMap.get(USG_INV_DESC.BILLABLE_COPIES);
            footerComment2 = "(" + String.valueOf(i) + ") - " + footerComment2;
            i++;
        }
        if (footerMsgMap.containsKey(USG_INV_DESC_OTHER)) {
            footerComment3 = (String) footerMsgMap.get(USG_INV_DESC_OTHER);
            footerComment3 = "(" + String.valueOf(i) + ") - " + footerComment3;
            i++;
        }
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.firstUsgInvDescTxt, footerComment1);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.scdUsgInvDescTxt, footerComment2);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.thirdUsgInvDescTxt, footerComment3);

        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invTotAmt, rs.getBigDecimal(INV_AMT));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invBalAmt, rs.getBigDecimal(BALANCE_DUE));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invTotChrgAmt, rs.getBigDecimal(INV_CHARGE_AMT));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invFrtAmt, rs.getBigDecimal(FRT_AMT));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invFrtTaxAmt, rs.getBigDecimal(TAX_AMT));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invPrePmtAmt, rs.getBigDecimal(PRE_PMT_AMT));
        // START 2022/07/20 R.Onozuka [QC#59669, ADD]
        String conslBillNum = null;
        if (preConslBillInfoForCtrlMap != null && preConslBillInfoForCtrlMap.get(CONSL_BILL_NUM) != null) {
            conslBillNum = (String) preConslBillInfoForCtrlMap.get(CONSL_BILL_NUM);
        }
        // START 2022/09/26 R.Onozuka [QC#60624, MOD]
//        String ocrScanNum = genOcrScanNum(rs.getString(LINE_BIZ_TP_CD), 
//                                          rs.getString(BILL_TO_CD),
//                                          conslBillNum,
//                                          rs.getString(INV_NUM),
//                                          rs.getBigDecimal(INV_AMT));
        String ocrScanNum = genOcrScanNum(rs.getString(LINE_BIZ_TP_CD), 
                                          rs.getString(BILL_TO_CD),
                                          conslBillNum,
                                          rs.getString(INV_NUM),
                                          rs.getBigDecimal(INV_AMT),
                                          rs.getString(INV_TP_CD));
        // END 2022/09/26 R.Onozuka [QC#60624, MOD]
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.ocrScanNum, ocrScanNum);
        // END 2022/07/20 R.Onozuka [QC#59669, ADD]
        // START 2023/11/09 R.Takau [QC#61584, ADD]
        if (ZYPConstant.FLG_ON_Y.equals(rs.getString("TAX_PRINT_FLG"))) {
            invPrtHdrTmsg.invPrintCatgCd.setValue(INV_PRINT_CATG.TAX_PRINT);
        }
        // END 2023/11/09 R.Takau [QC#61584, ADD]

    }
    private boolean pmtSplitProcDaily(INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray, INV_PRT_HDRTMsg[] invPrtHdrTmsgArray,
            INV_PRT_CTRLTMsg invPrtCtrlTmsg, INV_PRT_HDRTMsg invPrtHdrTmsg, ResultSet rs, 
            List<Map<String, Object>> pmtTermInfoList) throws SQLException, NWCB010001BusinessException {

        String invDateStr = rs.getString(INV_DT);
        String invTpCd = rs.getString(INV_TP_CD);

        BigDecimal balanceDueSplitAmt = new BigDecimal(0);
        BigDecimal balanceDueAmt = rs.getBigDecimal(BALANCE_DUE);
        String ccySgnTxt = rs.getString(CCY_SGN_TXT);

        invPrtCtrlTmsgArray[0] = invPrtCtrlTmsg;
        invPrtHdrTmsgArray[0] = invPrtHdrTmsg;

        for (int i = 0; i < pmtTermInfoList.size(); i++) {

            Map<String, Object> pmtTermMap = pmtTermInfoList.get(i);
            BigDecimal istlPmtTermPct = (BigDecimal) pmtTermMap.get(ISTL_PMT_TERM_PCT);

            INV_PRT_CTRLTMsg copyCtrlTMsg = new INV_PRT_CTRLTMsg();
            INV_PRT_HDRTMsg copyHdrTMsg = new INV_PRT_HDRTMsg();
            EZDMsg.copy(invPrtCtrlTmsg, null, copyCtrlTMsg, null);
            EZDMsg.copy(invPrtHdrTmsg, null, copyHdrTMsg, null);

            // get Balance Due of copied HdrTmsg
            BigDecimal balanceDueSplit = (balanceDueAmt.multiply(istlPmtTermPct)).divide(BIGDECIMAL_100, 2,
                    BigDecimal.ROUND_HALF_UP);

            balanceDueSplitAmt = balanceDueSplitAmt.add(balanceDueSplit);

            BigDecimal installmentNetDt = (BigDecimal) pmtTermMap.get(ISTL_PMT_TERM_AOT);
            if (installmentNetDt == null) {
                installmentNetDt = BigDecimal.ZERO;
            }
            String netDueDt = ZYPDateUtil.addDays(invDateStr, installmentNetDt.intValue());
            // set Copy INV_PRT_HDR
            ZYPEZDItemValueSetter.setValue(copyHdrTMsg.invBalAmt, balanceDueSplit);
            ZYPEZDItemValueSetter.setValue(copyHdrTMsg.invBalAmtTxt, fmtAmtFromNumToStrInvTp(balanceDueSplit, invTpCd, ccySgnTxt));
            
            ZYPEZDItemValueSetter.setValue(copyHdrTMsg.netDueDtTxt, ZYPDateUtil.formatEzd8ToDisp(netDueDt));

            // set Copy INV_PRT_CTRL
            ZYPEZDItemValueSetter.setValue(copyCtrlTMsg.istlPmtTermDtlNum, (String) pmtTermMap.get(ISTL_PMT_TERM_DTL_NUM));

            invPrtCtrlTmsgArray[i] = copyCtrlTMsg;
            invPrtHdrTmsgArray[i]  = copyHdrTMsg;
        }

        if (balanceDueSplitAmt.compareTo(balanceDueAmt) != 0) {
            BigDecimal blaDueFraction = balanceDueAmt.subtract(balanceDueSplitAmt);
            BigDecimal amount = invPrtHdrTmsgArray[0].invBalAmt.getValue().add(blaDueFraction);
            ZYPEZDItemValueSetter.setValue(invPrtHdrTmsgArray[0].invBalAmt, amount);
            ZYPEZDItemValueSetter.setValue(invPrtHdrTmsgArray[0].invBalAmtTxt, fmtAmtFromNumToStrInvTp(amount, invTpCd, ccySgnTxt));
        }
        return true;
    }

    private boolean insertCtrl(INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray, String invoiceNum) {

        //QC#25431 Del Start
        //for (int i = 0; i < invPrtCtrlTmsgArray.length; i++) {
        //    INV_PRT_CTRLTMsg invPrtCtrlTmsg = invPrtCtrlTmsgArray[i];
        //    invPrtCtrlTmsg.invPrtCtrlPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_CTRL_SQ));
        //}
        //QC#25431 Del End

        int result = EZDFastTBLAccessor.insert(invPrtCtrlTmsgArray);
        if (result != invPrtCtrlTmsgArray.length) {
            //NWCM0109E=0,It failed to register @ Table.[@]
            S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_CTRLTMsg().getTableName(), invoiceNum));
            return false;
        }
//        normCnt += result;
        return true;
    }

    private boolean updateCtrlHdr(INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray, INV_PRT_HDRTMsg[] invPrtHdrTmsgArray) {

        int i = 0;
        for (i = 0; i < invPrtCtrlTmsgArray.length; i++) {
            INV_PRT_CTRLTMsg invPrtCtrlTmsg = invPrtCtrlTmsgArray[i];
            INV_PRT_HDRTMsg invPrtHdrTmsg = invPrtHdrTmsgArray[i];
            if (hasValue(invPrtCtrlTmsg.invPrtCtrlPk)) {
                S21FastTBLAccessor.update(invPrtCtrlTmsg);
                if (!RETURN_CD_NORMAL.equals(invPrtCtrlTmsg.getReturnCode())) {
                    S21InfoLogOutput.println(NWCM0110E, toArray(invPrtCtrlTmsg.getTableName(), String.valueOf(invPrtCtrlTmsg.invPrtCtrlPk.getValue())));
                    return false;
                }
                S21FastTBLAccessor.update(invPrtHdrTmsg);
                if (!RETURN_CD_NORMAL.equals(invPrtHdrTmsg.getReturnCode())) {
                    S21InfoLogOutput.println(NWCM0110E, toArray(invPrtHdrTmsg.getTableName(), String.valueOf(invPrtHdrTmsg.invPrtCtrlPk.getValue())));
                    return false;
                }
            } else {
                BigDecimal seq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_CTRL_SQ);
                invPrtCtrlTmsg.invPrtCtrlPk.setValue(seq);
                S21FastTBLAccessor.insert(invPrtCtrlTmsg);
                if (!RETURN_CD_NORMAL.equals(invPrtCtrlTmsg.getReturnCode())) {
                    S21InfoLogOutput.println(NWCM0110E, toArray(invPrtCtrlTmsg.getTableName(), String.valueOf(invPrtCtrlTmsg.invPrtCtrlPk.getValue())));
                    return false;
                }
                invPrtHdrTmsg.invPrtCtrlPk.setValue(seq);
                S21FastTBLAccessor.insert(invPrtHdrTmsg);
                if (!RETURN_CD_NORMAL.equals(invPrtHdrTmsg.getReturnCode())) {
                    S21InfoLogOutput.println(NWCM0110E, toArray(invPrtHdrTmsg.getTableName(), String.valueOf(invPrtHdrTmsg.invPrtCtrlPk.getValue())));
                    return false;
                }
            }
        }

//        normCnt += i;
        return true;
    }

    private boolean insertHdr(INV_PRT_HDRTMsg[] invPrtHdrTmsgArray, INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray, String invoiceNum) {

        for (int i = 0; i < invPrtHdrTmsgArray.length; i++) {
            INV_PRT_HDRTMsg invPrtHdrTmsg = invPrtHdrTmsgArray[i];
            INV_PRT_CTRLTMsg invPrtCtrlTmsg = invPrtCtrlTmsgArray[i];

            invPrtHdrTmsg.invPrtCtrlPk.setValue(invPrtCtrlTmsg.invPrtCtrlPk.getValue());
        }

        int result = EZDFastTBLAccessor.insert(invPrtHdrTmsgArray);
        if (result != invPrtCtrlTmsgArray.length) {
            //NWCM0109E=0,It failed to register @ Table.[@]
            S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_HDRTMsg().getTableName(), invoiceNum));
            return false;
        }
//        normCnt += result;
        return true;
    }

    //private boolean createPrintSummaryTable(ResultSet rs, int headerLineCount, boolean isEasyPac1Flg, boolean isSupplyOrderFlg)  //QC#26846 
    private boolean createPrintSummaryTable(ResultSet rs, 
                                              int headerLineCount, 
                                              boolean isEasyPac1Flg, 
                                              boolean isSupplyOrderFlg, 
                                              boolean isNoConfigOrderFlg,  // QC#31177 2019/04/16 Add
                                              boolean isBillablePartsOrderFlg) 
    throws SQLException, NWCB010001BusinessException {

        String invoiceNum = rs.getString(INV_NUM);
        String taxDtlFlg = rs.getString(TAX_DETAIL_FLG);
        String dtlDplyFlg = rs.getString(DTL_DPLY_FLG);
        int lineCount = headerLineCount;
        List<INV_PRT_SMRYTMsg> invPrtSmryList = new ArrayList<INV_PRT_SMRYTMsg>();

        //QC#27069 Add Start
        String manInvFlg = rs.getString(MAN_INV_FLG);
        //QC#27069 Add End

        if (INV_PRT_DTL_FMT_TP.FLEET_CONTRACT.equals(rs.getString(FORMAT_TYPE))) {
            // GET FLEET Summary
            List<Map<String, Object>> ssmFleetResult = null;
            ssmFleetResult = getFleetSummary(invoiceNum, taxDtlFlg, ZYPConstant.FLG_ON_Y); // QC#29371

            if (ssmFleetResult.size() > 0) {
                lineCount++;
                // 2019/07/16 QC#50885 Mod Start
                lineCount = setSmry(invPrtSmryList, ssmFleetResult, rs, lineCount, isEasyPac1Flg, true);
                //lineCount = setSmry(invPrtSmryList, ssmFleetResult, rs, lineCount, isEasyPac1Flg);
                // 2019/07/16 QC#50885 Mod End
            }
        // QC#29371
        } else if (INV_PRT_DTL_FMT_TP.AGGREGATE_CONTRACT.equals(rs.getString(FORMAT_TYPE))) {
            // GET FLEET Summary
            List<Map<String, Object>> ssmFleetResult = null;
            ssmFleetResult = getFleetSummary(invoiceNum, taxDtlFlg, ZYPConstant.FLG_OFF_N);

            if (ssmFleetResult.size() > 0) {
                lineCount++;
                // 2019/07/16 QC#50885 Mod Start
                lineCount = setSmry(invPrtSmryList, ssmFleetResult, rs, lineCount, isEasyPac1Flg, true);
                //lineCount = setSmry(invPrtSmryList, ssmFleetResult, rs, lineCount, isEasyPac1Flg);
                // 2019/07/16 QC#50885 Mod End
            }
        } else if (INV_PRT_DTL_FMT_TP.MAINTENANCE.equals(rs.getString(FORMAT_TYPE))) {
            // GET MAINTENCE Summary
            List<Map<String, Object>> ssmMaintenceResult = null;

            //QC#27069 Mod Start
            ssmMaintenceResult = getMaintenceSummary(invoiceNum, taxDtlFlg, manInvFlg);
            //ssmMaintenceResult = getMaintenceSummary(invoiceNum, taxDtlFlg);
            //QC#27069 Mod End

            if (ssmMaintenceResult.size() > 0) {
                lineCount++;
                // 2019/07/16 QC#50885 Mod Start
                lineCount = setSmry(invPrtSmryList, ssmMaintenceResult, rs, lineCount, isEasyPac1Flg, true);
                //lineCount = setSmry(invPrtSmryList, ssmMaintenceResult, rs, lineCount, isEasyPac1Flg);
                // 2019/07/16 QC#50885 Mod End
            }
        } else if (INV_PRT_DTL_FMT_TP.PARTS_AND_LABOR.equals(rs.getString(FORMAT_TYPE))) {
            // GET PARTS/LABOR Summary
            List<Map<String, Object>> ssmPartsResult = null;
            //QC#50078 2019/05/07 Mod Start
            ssmPartsResult = getPartsSummary(invoiceNum, taxDtlFlg, manInvFlg);
            // ssmPartsResult = getPartsSummary(invoiceNum, taxDtlFlg);
            //QC#50078 2019/05/07 Mod End

            if (ssmPartsResult.size() > 0) {
                lineCount++;
                // 2019/07/16 QC#50885 Mod Start
                lineCount = setSmry(invPrtSmryList, ssmPartsResult, rs, lineCount, isEasyPac1Flg, false);
                //lineCount = setSmry(invPrtSmryList, ssmPartsResult, rs, lineCount, isEasyPac1Flg);
                // 2019/07/16 QC#50885 Mod End
            }
        } else if (INV_PRT_DTL_FMT_TP.SALE_AND_SUPPLY.equals(rs.getString(FORMAT_TYPE))) {
            // GET SALE Summary
            List<Map<String, Object>> ssmSaleResult = null;
            //ssmSaleResult = getSaleSummary(invoiceNum, taxDtlFlg, dtlDplyFlg, isEasyPac1Flg, isSupplyOrderFlg); //QC#26846
            // ssmSaleResult = getSaleSummary(invoiceNum, taxDtlFlg, dtlDplyFlg, isEasyPac1Flg, isSupplyOrderFlg, isBillablePartsOrderFlg);
            // QC#31177 2019/04/16 Mod Start
            ssmSaleResult = getSaleSummary(invoiceNum, taxDtlFlg, dtlDplyFlg, isEasyPac1Flg, isSupplyOrderFlg, isNoConfigOrderFlg, isBillablePartsOrderFlg);
            // QC#31177 2019/04/16 Mod End

            if (ssmSaleResult.size() > 0) {
                lineCount++;
                //lineCount = setSmry(invPrtSmryList, ssmSaleResult, rs, lineCount,isEasyPac1Flg);
                lineCount = setSmryforSales(invPrtSmryList, ssmSaleResult, rs, lineCount,isEasyPac1Flg, taxDtlFlg);
            }
        }

        if (invPrtSmryList.size() > 0) {
            int result = EZDFastTBLAccessor.insert(invPrtSmryList.toArray(new INV_PRT_SMRYTMsg[invPrtSmryList.size()]));
            if (result != invPrtSmryList.size()) {
                //NWCM0110E=0,It failed to update @ Table.[@]
                S21InfoLogOutput.println(NWCM0110E, toArray(new INV_PRT_SMRYTMsg().getTableName(), invoiceNum));
                return false;
            }
//            normCnt += result;
        }
        return true;
    }

    private void logicalDeletePrtHdr(String invNum) {

        INV_PRT_HDRTMsg deleteHdrTMsg = new INV_PRT_HDRTMsg();
        INV_PRT_HDRTMsgArray deleteHdrTMsgArray;

        // set sql condition
        deleteHdrTMsg.setSQLID("001");
        deleteHdrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        deleteHdrTMsg.setConditionValue("invBillNum01", invNum);

        deleteHdrTMsgArray = (INV_PRT_HDRTMsgArray) EZDTBLAccessor.findByConditionForUpdate(deleteHdrTMsg);
        // logical remove INV_PRT_SMRY
        if (deleteHdrTMsgArray != null && deleteHdrTMsgArray.length() > 0) {

            for (int i = 0; i < deleteHdrTMsgArray.length(); i++) {
                dataReCreationFlg = true;
                deleteHdrTMsg = (INV_PRT_HDRTMsg) deleteHdrTMsgArray.get(i);

                EZDTBLAccessor.logicalRemove(deleteHdrTMsg);
            }
        }
    }

    private void logicalDeletePrtSmry(String invNum) {

        INV_PRT_SMRYTMsg deleteSmryTMsg = new INV_PRT_SMRYTMsg();
        INV_PRT_SMRYTMsgArray deleteSmryTMsgArray;

        // set sql condition
        deleteSmryTMsg.setSQLID("001");
        deleteSmryTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        deleteSmryTMsg.setConditionValue("invBillNum01", invNum);

        deleteSmryTMsgArray = (INV_PRT_SMRYTMsgArray) EZDTBLAccessor.findByConditionForUpdate(deleteSmryTMsg);
        // logical remove INV_PRT_SMRY
        if (deleteSmryTMsgArray != null && deleteSmryTMsgArray.length() > 0) {

            for (int i = 0; i < deleteSmryTMsgArray.length(); i++) {
                deleteSmryTMsg = (INV_PRT_SMRYTMsg) deleteSmryTMsgArray.get(i);

                EZDTBLAccessor.logicalRemove(deleteSmryTMsg);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> logicalDeletePrtCtrl(String invNum) {

        Map<String, Object> returnMap = new HashMap<String, Object>();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getInvPrtCtrlPk", ssmParam);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) list.get(i);
                INV_PRT_CTRLTMsg deleteCtrlTMsg = new INV_PRT_CTRLTMsg();
                deleteCtrlTMsg.glblCmpyCd.setValue(glblCmpyCd);
                deleteCtrlTMsg.invPrtCtrlPk.setValue((BigDecimal)map.get(INV_PRT_CTRL_PK));
                
                //Store Previous Consolidated Bill
                returnMap.put("CONSL_BILL_NUM", (String)map.get(CONSL_BILL_NUM));
                returnMap.put("CONSL_BILL_INV_DT", (String)map.get(CONSL_BILL_INV_DT));
                returnMap.put("CONSL_BILL_TOT_AMT", (BigDecimal)map.get(CONSL_BILL_TOT_AMT));
                returnMap.put("CONSL_BILL_INV_CRAT_PSN_CD", (String)map.get(CONSL_BILL_INV_CRAT_PSN_CD));
                returnMap.put("ORIG_CONSL_BILL_NUM", (String)map.get(ORIG_CONSL_BILL_NUM));
                
                deleteCtrlTMsg = (INV_PRT_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdate(deleteCtrlTMsg);
                if (deleteCtrlTMsg != null) {
                    EZDTBLAccessor.logicalRemove(deleteCtrlTMsg);
                }
            }
        }
        return returnMap;
    }
    
    @SuppressWarnings("unchecked")
    private void logicalDeletePrtCtrlForCons(String conslBillNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("conslBillNum", conslBillNum);
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getInvPrtCtrlPkForCons", ssmParam);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) list.get(i);
                INV_PRT_CTRLTMsg deleteCtrlTMsg = new INV_PRT_CTRLTMsg();
                deleteCtrlTMsg.glblCmpyCd.setValue(glblCmpyCd);
                deleteCtrlTMsg.invPrtCtrlPk.setValue((BigDecimal)map.get(INV_PRT_CTRL_PK));
                deleteCtrlTMsg = (INV_PRT_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdate(deleteCtrlTMsg);
                if (deleteCtrlTMsg != null) {
                    EZDTBLAccessor.logicalRemove(deleteCtrlTMsg);
                }
            }
        }
    }
    
    private Map<String, Object> logicalDeleteFleetSubTot(String invNum) {

        Map<String, Object> returnMap = new HashMap<String, Object>();  //QC#7561
        
        INV_PRT_FLEET_SUB_TOTTMsg fleetSubTMsg = new INV_PRT_FLEET_SUB_TOTTMsg();
        INV_PRT_FLEET_SUB_TOTTMsgArray fleetSubTMsgArray;

        // set sql condition
        fleetSubTMsg.setSQLID("001");
        fleetSubTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        fleetSubTMsg.setConditionValue("invNum01", invNum);

        fleetSubTMsgArray = (INV_PRT_FLEET_SUB_TOTTMsgArray) EZDTBLAccessor.findByConditionForUpdate(fleetSubTMsg);
        // logical remove INV_PRT_FLEET_SUB_TOT
        if (fleetSubTMsgArray != null && fleetSubTMsgArray.length() > 0) {

            for (int i = 0; i < fleetSubTMsgArray.length(); i++) {
                fleetSubTMsg = (INV_PRT_FLEET_SUB_TOTTMsg) fleetSubTMsgArray.get(i);

                //Store Previous Consolidated Bill
                StringBuffer key = new StringBuffer(INV_PRT_FLEET_SUB_TOT);
                if (hasValue(fleetSubTMsg.dsContrPk)) {
                    key.append(fleetSubTMsg.dsContrPk.getValue().toString());
                }
                if (hasValue(fleetSubTMsg.svcPgmNm)) {
                    key.append(fleetSubTMsg.svcPgmNm.getValue());
                }
                if (hasValue(fleetSubTMsg.custIssPoNum)) {
                    key.append(fleetSubTMsg.custIssPoNum.getValue());
                }
                returnMap.put(CONSL_BILL_NUM, fleetSubTMsg.conslBillNum.getValue());
                returnMap.put(key.toString(), fleetSubTMsg.billFeetSubTotSq.getValue());
                
                EZDTBLAccessor.logicalRemove(fleetSubTMsg);
            }
        }
        return returnMap;
    }

    // QC#52209 2019/07/31 Del Start
    // QC#30346 2019/02/18 Add Start
    //private void logicalDeleteFleetInvLine(String invNum) {
    //
    //    INV_PRT_FLEET_LINETMsg fleetInvLineTMsg = new INV_PRT_FLEET_LINETMsg();
    //    INV_PRT_FLEET_LINETMsgArray fleetInvLineTMsgArray;
    //
    //    // set sql condition
    //    fleetInvLineTMsg.setSQLID("001");
    //    fleetInvLineTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
    //    fleetInvLineTMsg.setConditionValue("invNum01", invNum);
    //
    //    fleetInvLineTMsgArray = (INV_PRT_FLEET_LINETMsgArray) EZDTBLAccessor.findByConditionForUpdate(fleetInvLineTMsg);
    //    // logical remove INV_PRT_FLEET_LINE
    //    if (fleetInvLineTMsgArray != null && fleetInvLineTMsgArray.length() > 0) {
    //
    //        for (int i = 0; i < fleetInvLineTMsgArray.length(); i++) {
    //            fleetInvLineTMsg = (INV_PRT_FLEET_LINETMsg) fleetInvLineTMsgArray.get(i);
    //
    //            EZDTBLAccessor.logicalRemove(fleetInvLineTMsg);
    //        }
    //    }
    //}
    // QC#30346 2019/02/18 Add End
    // QC#52209 2019/07/31 Del End

    private void logicalDeleteFleetChrgDtl(String invNum) {

        INV_PRT_FLEET_CHRG_DTLTMsg fleetDtlTMsg = new INV_PRT_FLEET_CHRG_DTLTMsg();
        INV_PRT_FLEET_CHRG_DTLTMsgArray fleetDtlTMsgArray;

        // set sql condition
        fleetDtlTMsg.setSQLID("001");
        fleetDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        fleetDtlTMsg.setConditionValue("invNum01", invNum);

        fleetDtlTMsgArray = (INV_PRT_FLEET_CHRG_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdate(fleetDtlTMsg);
        // logical remove INV_PRT_FLEET_CHRG_DTL
        if (fleetDtlTMsgArray != null && fleetDtlTMsgArray.length() > 0) {

            for (int i = 0; i < fleetDtlTMsgArray.length(); i++) {
                fleetDtlTMsg = (INV_PRT_FLEET_CHRG_DTLTMsg) fleetDtlTMsgArray.get(i);

                EZDTBLAccessor.logicalRemove(fleetDtlTMsg);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private void logicalDeleteFleetLoc(String invNum) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getInvPrtFleetLocList", ssmParam);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) list.get(i);
                INV_PRT_FLEET_LOCTMsg deleteCtrlTMsg = new INV_PRT_FLEET_LOCTMsg();
                deleteCtrlTMsg.glblCmpyCd.setValue(glblCmpyCd);
                deleteCtrlTMsg.invPrtFleetLocPk.setValue((BigDecimal)map.get(INV_PRT_FLEET_LOC_PK));
                deleteCtrlTMsg = (INV_PRT_FLEET_LOCTMsg) EZDTBLAccessor.findByKeyForUpdate(deleteCtrlTMsg);
                if (deleteCtrlTMsg != null) {
                    EZDTBLAccessor.logicalRemove(deleteCtrlTMsg);
                }
            }
        }
    }

    private void logicalDeleteFleetMach(String invNum) {

        INV_PRT_FLEET_MACHTMsg fleetMachTMsg = new INV_PRT_FLEET_MACHTMsg();
        INV_PRT_FLEET_MACHTMsgArray fleetMachTMsgArray;

        // set sql condition
        fleetMachTMsg.setSQLID("001");
        fleetMachTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        fleetMachTMsg.setConditionValue("invNum01", invNum);

        fleetMachTMsgArray = (INV_PRT_FLEET_MACHTMsgArray) EZDTBLAccessor.findByConditionForUpdate(fleetMachTMsg);
        // logical remove INV_PRT_FLEET_MACH
        if (fleetMachTMsgArray != null && fleetMachTMsgArray.length() > 0) {

            for (int i = 0; i < fleetMachTMsgArray.length(); i++) {
                fleetMachTMsg = (INV_PRT_FLEET_MACHTMsg) fleetMachTMsgArray.get(i);

                EZDTBLAccessor.logicalRemove(fleetMachTMsg);
            }
        }
    }

    private void logicalDeleteFleetMtr(String invNum) {

        INV_PRT_FLEET_MTRTMsg fleetMtrTMsg = new INV_PRT_FLEET_MTRTMsg();
        INV_PRT_FLEET_MTRTMsgArray fleetMtrTMsgArray;

        // set sql condition
        fleetMtrTMsg.setSQLID("001");
        fleetMtrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        fleetMtrTMsg.setConditionValue("invNum01", invNum);

        fleetMtrTMsgArray = (INV_PRT_FLEET_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdate(fleetMtrTMsg);
        // logical remove INV_PRT_FLEET_MTR
        if (fleetMtrTMsgArray != null && fleetMtrTMsgArray.length() > 0) {

            for (int i = 0; i < fleetMtrTMsgArray.length(); i++) {
                fleetMtrTMsg = (INV_PRT_FLEET_MTRTMsg) fleetMtrTMsgArray.get(i);

                EZDTBLAccessor.logicalRemove(fleetMtrTMsg);
            }
        }
    }

    private void logicalDeleteFleetSmryMtr(String invNum) {

        INV_PRT_FLEET_SMRY_MTRTMsg fleetSmryMtrTMsg = new INV_PRT_FLEET_SMRY_MTRTMsg();
        INV_PRT_FLEET_SMRY_MTRTMsgArray fleetSmryMtrTMsgArray;

        // set sql condition
        fleetSmryMtrTMsg.setSQLID("001");
        fleetSmryMtrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        fleetSmryMtrTMsg.setConditionValue("invNum01", invNum);

        fleetSmryMtrTMsgArray = (INV_PRT_FLEET_SMRY_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdate(fleetSmryMtrTMsg);
        // logical remove INV_PRT_FLEET_SMRY_MTR
        if (fleetSmryMtrTMsgArray != null && fleetSmryMtrTMsgArray.length() > 0) {

            for (int i = 0; i < fleetSmryMtrTMsgArray.length(); i++) {
                fleetSmryMtrTMsg = (INV_PRT_FLEET_SMRY_MTRTMsg) fleetSmryMtrTMsgArray.get(i);

                EZDTBLAccessor.logicalRemove(fleetSmryMtrTMsg);
            }
        }
    }

    private void logicalDeleteFleetSmryXs(String invNum) {

        INV_PRT_FLEET_SMRY_XSTMsg fleetSmryXsTMsg = new INV_PRT_FLEET_SMRY_XSTMsg();
        INV_PRT_FLEET_SMRY_XSTMsgArray fleetSmryXsTMsgArray;

        // set sql condition
        fleetSmryXsTMsg.setSQLID("001");
        fleetSmryXsTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        fleetSmryXsTMsg.setConditionValue("invNum01", invNum);

        fleetSmryXsTMsgArray = (INV_PRT_FLEET_SMRY_XSTMsgArray) EZDTBLAccessor.findByConditionForUpdate(fleetSmryXsTMsg);
        // logical remove INV_PRT_FLEET_SMRY_XS
        if (fleetSmryXsTMsgArray != null && fleetSmryXsTMsgArray.length() > 0) {

            for (int i = 0; i < fleetSmryXsTMsgArray.length(); i++) {
                fleetSmryXsTMsg = (INV_PRT_FLEET_SMRY_XSTMsg) fleetSmryXsTMsgArray.get(i);

                EZDTBLAccessor.logicalRemove(fleetSmryXsTMsg);
            }
        }
    }

    private Map<String, Object> logicalDeleteMaintSubTot(String invNum) {

        Map<String, Object> returnMap = new HashMap<String, Object>();  //QC#7561
        
        INV_PRT_MAINT_SUB_TOTTMsg maintSubTMsg = new INV_PRT_MAINT_SUB_TOTTMsg();
        INV_PRT_MAINT_SUB_TOTTMsgArray maintSubTMsgArray;

        // set sql condition
        maintSubTMsg.setSQLID("001");
        maintSubTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        maintSubTMsg.setConditionValue("invNum01", invNum);

        maintSubTMsgArray = (INV_PRT_MAINT_SUB_TOTTMsgArray) EZDTBLAccessor.findByConditionForUpdate(maintSubTMsg);
        // logical remove INV_PRT_MAINT_SUB_TOT
        if (maintSubTMsgArray != null && maintSubTMsgArray.length() > 0) {

            for (int i = 0; i < maintSubTMsgArray.length(); i++) {
                maintSubTMsg = (INV_PRT_MAINT_SUB_TOTTMsg) maintSubTMsgArray.get(i);

                //Store Previous Consolidated Bill
                StringBuffer key = new StringBuffer(INV_PRT_MAINT_SUB_TOT);
                if (hasValue(maintSubTMsg.shipToCustCd)) {
                    key.append(maintSubTMsg.shipToCustCd.getValue());
                }
                if (hasValue(maintSubTMsg.custIssPoNum)) {
                    key.append(maintSubTMsg.custIssPoNum.getValue());
                }
                if (hasValue(maintSubTMsg.firstBllgAttrbValTxt)) {
                    key.append(maintSubTMsg.firstBllgAttrbValTxt.getValue());
                }
                if (hasValue(maintSubTMsg.scdBllgAttrbValTxt)) {
                    key.append(maintSubTMsg.scdBllgAttrbValTxt.getValue());
                }
                if (hasValue(maintSubTMsg.thirdBllgAttrbValTxt)) {
                    key.append(maintSubTMsg.thirdBllgAttrbValTxt.getValue());
                }
                if (hasValue(maintSubTMsg.frthBllgAttrbValTxt)) {
                    key.append(maintSubTMsg.frthBllgAttrbValTxt.getValue());
                }
                if (hasValue(maintSubTMsg.fifthBllgAttrbValTxt)) {
                    key.append(maintSubTMsg.fifthBllgAttrbValTxt.getValue());
                }
                if (hasValue(maintSubTMsg.sixthBllgAttrbValTxt)) {
                    key.append(maintSubTMsg.sixthBllgAttrbValTxt.getValue());
                }
                returnMap.put(CONSL_BILL_NUM, maintSubTMsg.conslBillNum.getValue());
                returnMap.put(key.toString(), maintSubTMsg.billMaintSubTotSq.getValue());
                
                EZDTBLAccessor.logicalRemove(maintSubTMsg);
            }
        }
        return returnMap;
    }

    private void logicalDeleteMaintMach(String invNum) {

        INV_PRT_MAINT_MACHTMsg maintMachTMsg = new INV_PRT_MAINT_MACHTMsg();
        INV_PRT_MAINT_MACHTMsgArray maintMachTMsgArray;

        // set sql condition
        maintMachTMsg.setSQLID("001");
        maintMachTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        maintMachTMsg.setConditionValue("invNum01", invNum);

        maintMachTMsgArray = (INV_PRT_MAINT_MACHTMsgArray) EZDTBLAccessor.findByConditionForUpdate(maintMachTMsg);
        // logical remove INV_PRT_MAINT_MACH
        if (maintMachTMsgArray != null && maintMachTMsgArray.length() > 0) {

            for (int i = 0; i < maintMachTMsgArray.length(); i++) {
                maintMachTMsg = (INV_PRT_MAINT_MACHTMsg) maintMachTMsgArray.get(i);

                EZDTBLAccessor.logicalRemove(maintMachTMsg);
            }
        }
    }

    private void logicalDeleteMaintChrg(String invNum) {

        INV_PRT_MAINT_CHRG_DTLTMsg maintDtlTMsg = new INV_PRT_MAINT_CHRG_DTLTMsg();
        INV_PRT_MAINT_CHRG_DTLTMsgArray maintDtlTMsgArray;

        // set sql condition
        maintDtlTMsg.setSQLID("001");
        maintDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        maintDtlTMsg.setConditionValue("invNum01", invNum);

        maintDtlTMsgArray = (INV_PRT_MAINT_CHRG_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdate(maintDtlTMsg);
        // logical remove INV_PRT_SMRY
        if (maintDtlTMsgArray != null && maintDtlTMsgArray.length() > 0) {

            for (int i = 0; i < maintDtlTMsgArray.length(); i++) {
                maintDtlTMsg = (INV_PRT_MAINT_CHRG_DTLTMsg) maintDtlTMsgArray.get(i);

                EZDTBLAccessor.logicalRemove(maintDtlTMsg);
            }
        }
    }

    private void logicalDeleteMaintMtr(String invNum) {

        INV_PRT_MAINT_MTRTMsg maintMtrTMsg = new INV_PRT_MAINT_MTRTMsg();
        INV_PRT_MAINT_MTRTMsgArray maintMtrTMsgArray;

        // set sql condition
        maintMtrTMsg.setSQLID("001");
        maintMtrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        maintMtrTMsg.setConditionValue("invNum01", invNum);

        maintMtrTMsgArray = (INV_PRT_MAINT_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdate(maintMtrTMsg);
        // logical remove INV_PRT_MAINT_MTR
        if (maintMtrTMsgArray != null && maintMtrTMsgArray.length() > 0) {

            for (int i = 0; i < maintMtrTMsgArray.length(); i++) {
                maintMtrTMsg = (INV_PRT_MAINT_MTRTMsg) maintMtrTMsgArray.get(i);

                EZDTBLAccessor.logicalRemove(maintMtrTMsg);
            }
        }
    }

    private void logicalDeleteMaintXs(String invNum) {

        INV_PRT_MAINT_XS_MTRTMsg maintXsTMsg = new INV_PRT_MAINT_XS_MTRTMsg();
        INV_PRT_MAINT_XS_MTRTMsgArray maintXsTMsgArray;

        // set sql condition
        maintXsTMsg.setSQLID("001");
        maintXsTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        maintXsTMsg.setConditionValue("invNum01", invNum);

        maintXsTMsgArray = (INV_PRT_MAINT_XS_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdate(maintXsTMsg);
        // logical remove INV_PRT_MAINT_XS_MTR
        if (maintXsTMsgArray != null && maintXsTMsgArray.length() > 0) {

            for (int i = 0; i < maintXsTMsgArray.length(); i++) {
                maintXsTMsg = (INV_PRT_MAINT_XS_MTRTMsg) maintXsTMsgArray.get(i);

                EZDTBLAccessor.logicalRemove(maintXsTMsg);
            }
        }
    }

    private Map<String, Object> logicalDeleteSlsPartSubTot(String invNum) {

        Map<String, Object> returnMap = new HashMap<String, Object>();  //QC#7561
        
        INV_PRT_SLS_PART_SUB_TOTTMsg slsPartSubTMsg = new INV_PRT_SLS_PART_SUB_TOTTMsg();
        INV_PRT_SLS_PART_SUB_TOTTMsgArray slsPartSubTMsgArray;

        // set sql condition
        slsPartSubTMsg.setSQLID("001");
        slsPartSubTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        slsPartSubTMsg.setConditionValue("invNum01", invNum);

        slsPartSubTMsgArray = (INV_PRT_SLS_PART_SUB_TOTTMsgArray) EZDTBLAccessor.findByConditionForUpdate(slsPartSubTMsg);
        // logical remove INV_PRT_SLS_PART_SUB_TOT
        if (slsPartSubTMsgArray != null && slsPartSubTMsgArray.length() > 0) {

            for (int i = 0; i < slsPartSubTMsgArray.length(); i++) {
                slsPartSubTMsg = (INV_PRT_SLS_PART_SUB_TOTTMsg) slsPartSubTMsgArray.get(i);

                //Store Previous Consolidated Bill
                returnMap.put(CONSL_BILL_NUM, slsPartSubTMsg.conslBillNum.getValue());
                
                EZDTBLAccessor.logicalRemove(slsPartSubTMsg);
            }
        }
        return returnMap;
    }

    private void logicalDeleteSlsPartDtl(String invNum) {

        INV_PRT_SLS_PART_DTLTMsg slsPartDtlTMsg = new INV_PRT_SLS_PART_DTLTMsg();
        INV_PRT_SLS_PART_DTLTMsgArray slsPartDtlTMsgArray;

        // set sql condition
        slsPartDtlTMsg.setSQLID("001");
        slsPartDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        slsPartDtlTMsg.setConditionValue("invNum01", invNum);

        slsPartDtlTMsgArray = (INV_PRT_SLS_PART_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdate(slsPartDtlTMsg);
        // logical remove INV_PRT_SLS_PART_DTL
        if (slsPartDtlTMsgArray != null && slsPartDtlTMsgArray.length() > 0) {

            for (int i = 0; i < slsPartDtlTMsgArray.length(); i++) {
                slsPartDtlTMsg = (INV_PRT_SLS_PART_DTLTMsg) slsPartDtlTMsgArray.get(i);

                EZDTBLAccessor.logicalRemove(slsPartDtlTMsg);
            }
        }
    }

    private INV_PRT_CTRLTMsg setInitInvPrtCtrl(NMZC610001PMsg custInvInfo) {
        // Set init INV_PRT_CTRL
        INV_PRT_CTRLTMsg invPrtCtrlTmsg = new INV_PRT_CTRLTMsg();

        String custBllgTpCd = null;
        String vehicle = null;

        if (custInvInfo.InvoiceRuleList.getValidCount() == 0) {
            // In the case of No data , Vehicle Print Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        custBllgTpCd = custInvInfo.InvoiceRuleList.no(0).custBllgTpCd.getValue();
        vehicle = custInvInfo.InvoiceRuleList.no(0).custBllgVcleCd.getValue();

        // Vehicle EDI
        if (CUST_BLLG_VCLE.EDI.equals(vehicle)) {
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.EDI_INVOICE);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else if (CUST_BLLG_VCLE.EMAIL_ONLY.equals(vehicle)) {
            // Vehicle Email Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else if (CUST_BLLG_VCLE.MANUAL_SPECIAL_BILL.equals(vehicle)) {
            // Vehicle SB Manual
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            //QC#19274
            //invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.MANUAL_BILLING);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else if (CUST_BLLG_VCLE.SPECIAL_BILL_NO_REVIEW.equals(vehicle)) {
            // Vehicle SB No Review
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.SPECIAL_BILLING);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else if (CUST_BLLG_VCLE.SPECIAL_BILL_REVIEW_REQD.equals(vehicle)) {
            // Vehicle SB Required
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.SPECIAL_BILLING);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else if (CUST_BLLG_VCLE.PRINT_ONLY.equals(vehicle)) {
            // Vehicle Print Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else if (CUST_BLLG_VCLE.PRINT_AND_EMAIL.equals(vehicle)) {
            // Vehicle Print & Email
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else if (CUST_BLLG_VCLE.DO_NOT_PRINT.equals(vehicle)) {
            // Vehicle Do Not Print
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else if (CUST_BLLG_VCLE.NEEDS_EMAIL_REVIEW.equals(vehicle)) {
            // Vehicle Need Email Review
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        //QC6934
        } else if (CUST_BLLG_VCLE.DAILY_SUMMARY_EMAIL_AND_PRINT.equals(vehicle)) {
            // Vehicle Print & Email
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        //QC#26846
        } else if (CUST_BLLG_VCLE.WEB_BILLING_PRINT_ONLY.equals(vehicle)) {
            // Vehicle Print Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else if (CUST_BLLG_VCLE.WEB_BILLING_PRINT_AND_EMAIL.equals(vehicle)) {
            // Vehicle Print & Email
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        // 2018/01/15 QC#29371 SRNO#13 Add Start
        } else if (CUST_BLLG_VCLE.STRATEGIC_ACCOUNTS_PRINT_ONLY.equals(vehicle)) {
            // Vehicle Strategic Accounts Print Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else if (CUST_BLLG_VCLE.STRATEGIC_ACCOUNTS_PRINT_AND_EMAIL.equals(vehicle)) {
            // Vehicle Strategic Accounts Print & Email
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
            invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
                invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
            // 2018/01/15 QC#29371 SRNO#13 Add End
        }
        return invPrtCtrlTmsg;
    }

    private boolean orderReason(ResultSet rs) throws SQLException, NWCB010001BusinessException {
        ORD_CATG_BIZ_CTXTMsg ordCatgBizTMsg = new ORD_CATG_BIZ_CTXTMsg();
        ORD_CATG_BIZ_CTXTMsgArray ordCatgBizTMsgArray;

        // set sql condition
        ordCatgBizTMsg.setSQLID("005");
        ordCatgBizTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        ordCatgBizTMsg.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EASY_PAC1);
        ordCatgBizTMsg.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.EASY_PAC1_RETURN);
        ordCatgBizTMsg.setConditionValue("dsOrdCatgCd01", rs.getString(DS_ORD_CATG_CD));
        ordCatgBizTMsg.setConditionValue("dsOrdTpCd01", rs.getString(DS_ORD_TP_CD));

        ordCatgBizTMsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(ordCatgBizTMsg);

        if (ordCatgBizTMsgArray != null && ordCatgBizTMsgArray.length() > 0) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private boolean billToCust(ResultSet rs, Map<String, String> billToCustInfo) throws SQLException, NWCB010001BusinessException {

        String key = createCacheKey(SPLY_CONTR_BY_BILL_TO_ACCT_CUST, rs.getString(BILL_TO_CD) + rs.getString(ORD_DT));
        List<Map<String, Object>> billToCustList = (List<Map<String, Object>>) this.cache.get(key);
        if (billToCustList == null) {
            Map<String, String> param = new HashMap<String, String>();
            param.put("glblCmpyCd", glblCmpyCd);
            param.put("billToCd", rs.getString(BILL_TO_CD));
            param.put("orderDt", rs.getString(ORD_DT));
            param.put("fmRate", FM_RATE3);
            param.put("fmQty", FM_QTY3);

            billToCustList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getByBillToCustAcct", param);
        }
        if (billToCustList == null || billToCustList.size() == 0) {
            return false;
        }

        String amtRate = (String) billToCustList.get(0).get(AMT_RATE);
        String quotQty = (String) billToCustList.get(0).get(QUOT_QTY);

        billToCustInfo.put(AMT_RATE, amtRate);
        billToCustInfo.put(QUOT_QTY, quotQty);
        return true;
    }

    private String getEasyPac1Msg(Map<String, String> custInfo) {

        StringBuilder easyPac1InvMsgSb = new StringBuilder();
        easyPac1InvMsgSb.append(this.easyPac1InvMsg1);
        easyPac1InvMsgSb.append(ENTER);
        // QC#20141 del Start
        //easyPac1InvMsgSb.append(this.easyPac1InvMsg2).append(" ").append(custInfo.get(AMT_RATE)).append(" ").append(EASY_PAC_MSG1);
        //easyPac1InvMsgSb.append(ENTER);
        // QC#20141 del End
        easyPac1InvMsgSb.append(this.easyPac1InvMsg3).append(" ").append(custInfo.get(QUOT_QTY)).append(" ").append(EASY_PAC_MSG2);
        // QC#20141 del Start
        //easyPac1InvMsgSb.append(ENTER);
        //easyPac1InvMsgSb.append(this.easyPac1InvMsg4);
        // QC#20141 del End
        return easyPac1InvMsgSb.toString();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getFleetSummary(String invoiceNum, String taxDtlFlg, String fleetFlg) { // QC#29371

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invoiceNum);
        ssmParam.put("taxDtlFlg", taxDtlFlg);
        ssmParam.put("dsSlsTaxTpCd01", DS_SLS_TAX_TP.STATE_TAX);
        ssmParam.put("dsSlsTaxTpCd02", DS_SLS_TAX_TP.COUNTY_TAX);
        ssmParam.put("dsSlsTaxTpCd03", DS_SLS_TAX_TP.CITY_TAX);
        ssmParam.put("siSrcTpCd", SVC_INV_SRC_TP.CONTRACT);
        // 2019/02/05 QC#29371 SRNO#7 Mod Start
        if (ZYPConstant.FLG_ON_Y.equals(fleetFlg)) {
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.FLEET);
        } else {
            ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.AGGREGATE);
        }
        // 2019/02/05 QC#29371 SRNO#7 Mod End
        ssmParam.put("siChrgTpCdBC", SVC_INV_CHRG_TP.BASE_CHARGE);
        ssmParam.put("siChrgTpCdAC", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        ssmParam.put("siChrgTpCdMC", SVC_INV_CHRG_TP.METER_CHARGE);
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("amtFormat", FM_AMT);
        ssmParam.put("fleetFlg", fleetFlg); // QC#29371

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFleetSummary", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getMaintenceSummary(String invoiceNum, String taxDtlFlg, String manInvFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invoiceNum);
        ssmParam.put("taxDtlFlg", taxDtlFlg);
        ssmParam.put("dsSlsTaxTpCd01", DS_SLS_TAX_TP.STATE_TAX);
        ssmParam.put("dsSlsTaxTpCd02", DS_SLS_TAX_TP.COUNTY_TAX);
        ssmParam.put("dsSlsTaxTpCd03", DS_SLS_TAX_TP.CITY_TAX);
        ssmParam.put("siSrcTpCd", SVC_INV_SRC_TP.CONTRACT);
        ssmParam.put("dsContrCatgCdREG", DS_CONTR_CATG.REGULAR);
        ssmParam.put("dsContrCatgCdAGG", DS_CONTR_CATG.AGGREGATE);
        ssmParam.put("siChrgTpCdBC", SVC_INV_CHRG_TP.BASE_CHARGE);
        ssmParam.put("siChrgTpCdAC", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        ssmParam.put("siChrgTpCdMC", SVC_INV_CHRG_TP.METER_CHARGE);
        ssmParam.put("siLineTpCdMACH", SVC_INV_LINE_TP.MACHINE);
        ssmParam.put("siLineTpCdACSY", SVC_INV_LINE_TP.ACCESSORY);
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("qtyFormat", FM_QTY);
        ssmParam.put("amtFormat", FM_AMT);
        //QC#27069 Add Start
        ssmParam.put("dsContrDtlTpFleet", DS_CONTR_DTL_TP.FLEET);
        //QC#27069 Add End
        //QC#50681 Add Start 2019/06/04
        ssmParam.put("dsContrDtlTpAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        //QC#50681 Add End 2019/06/04

        //QC#27069 Add Start
        String statementId;

        if (ZYPConstant.FLG_ON_1.equals(manInvFlg)) {
            statementId = "getMaintenceSummary_manInv";
        } else {
            statementId = "getMaintenceSummary";
        }
        //QC#27069 Add End

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList(statementId, ssmParam);
    }

    @SuppressWarnings("unchecked")
    // QC#50078 2019/05/07 Mod Start 
    private List<Map<String, Object>> getPartsSummary(String invoiceNum, String taxDtlFlg, String manInvFlg) {
    // private List<Map<String, Object>> getPartsSummary(String invoiceNum, String taxDtlFlg) {
    // QC#50078 2019/05/07 Mod End 

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invoiceNum);
        ssmParam.put("taxDtlFlg", taxDtlFlg);
        ssmParam.put("dsSlsTaxTpCd01", DS_SLS_TAX_TP.STATE_TAX);
        ssmParam.put("dsSlsTaxTpCd02", DS_SLS_TAX_TP.COUNTY_TAX);
        ssmParam.put("dsSlsTaxTpCd03", DS_SLS_TAX_TP.CITY_TAX);
        ssmParam.put("siSrcTpCd", SVC_INV_SRC_TP.DISPATCH);
        ssmParam.put("invSmryLineTpNmNC", NON_COPIER);
        ssmParam.put("invSmryLineTpNmC", COPIER);
        ssmParam.put("ordClsPL", PARTS_OR_LABOR);
        ssmParam.put("prodCd", "K%");
        //QC#25026
        //ssmParam.put("coaMdseTpCd", COA_PROJ.SOFTWARE);
        ssmParam.put("coaMdseTpCd", COA_PROJ_CD_ZZZ);
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        //QC#17491
        //ssmParam.put("qtyFormat", FM_QTY);
        ssmParam.put("qtyFormat", FM_QTY4);
        ssmParam.put("amtFormat", FM_AMT);

        // QC#50078 2019/05/07 Add Start
        String statementId;

        if (ZYPConstant.FLG_ON_1.equals(manInvFlg)) {
            statementId = "getPartsSummary_manInv";
        } else {
            statementId = "getPartsSummary";
        }
        // QC#50078 2019/05/07 Add End
        // QC#50078 2019/05/07 Mod Start
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList(statementId, ssmParam);
        // return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPartsSummary", ssmParam);
        // QC#50078 2019/05/07 Mod End
    }

    @SuppressWarnings("unchecked")
    //private List<Map<String, Object>> getSaleSummary(String invoiceNum, String taxDtlFlg, String dtlDplyFlg, boolean isEasyPac1Flg, boolean isSupplyOrderFlg) {  //QC#26846
    private List<Map<String, Object>> getSaleSummary(String invoiceNum, 
                                                      String taxDtlFlg, 
                                                      String dtlDplyFlg, 
                                                      boolean isEasyPac1Flg, 
                                                      boolean isSupplyOrderFlg, 
                                                      boolean isNoConfigOrderFlg, // QC#31177 2019/04/16 Add
                                                      boolean isBillablePartsOrderFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invoiceNum);
        ssmParam.put("taxDtlFlg", taxDtlFlg);
        ssmParam.put("dtlDplyFlg", dtlDplyFlg);
        ssmParam.put("dsSlsTaxTpCd01", DS_SLS_TAX_TP.STATE_TAX);
        ssmParam.put("dsSlsTaxTpCd02", DS_SLS_TAX_TP.COUNTY_TAX);
        ssmParam.put("dsSlsTaxTpCd03", DS_SLS_TAX_TP.CITY_TAX);
        ssmParam.put("invSmryLineTpNmNC", NON_COPIER);
        ssmParam.put("invSmryLineTpNmC", COPIER);
        ssmParam.put("prodCd", "K%");
        //QC#25026
        //ssmParam.put("coaMdseTpCd", COA_PROJ.SOFTWARE);
        ssmParam.put("coaMdseTpCd", COA_PROJ_CD_ZZZ);
        ssmParam.put("ordClsSUPL", SUPPLY);
        ssmParam.put("ordClsPARTS", PARTS);  //QC#26846
        ssmParam.put("ordClsSL", SALE);
        ssmParam.put("creditAndOdRebill", CREDIT_ANDOR_REBILL);
        ssmParam.put("shortFall", "SHORTFALL");
        ssmParam.put("varCharConst1", nullConvEmp(this.invPrintShortFallTxt));
        ssmParam.put("varCharConst2", nullConvEmp(this.invPrintShortFallItem));
        ssmParam.put("isEasyPac1Flg", isEasyPac1Flg);
        ssmParam.put("isSupplyOrderFlg", isSupplyOrderFlg);
        ssmParam.put("isNoConfigOrderFlg", isNoConfigOrderFlg); // QC#31177 2019/04/16 Add
        ssmParam.put("isBillablePartsOrderFlg", isBillablePartsOrderFlg);  //QC#26846
        ssmParam.put("qtyFormat", FM_QTY);
        ssmParam.put("amtFormat", FM_AMT);
//QC#13096 add Start
        ssmParam.put("zdInvPrtExcl", "ZD_INV_PRT_EXCL");
//QC#13096 add End
        // 2018/05/25 QC#21841 Add Start
        ssmParam.put("invLineCatgItem", INV_LINE_CATG.ITEM);
        ssmParam.put("invLineCatgFrt", INV_LINE_CATG.FREIGHT);
        ssmParam.put("invLineCatgChg", INV_LINE_CATG.CHARGE);
        // 2018/05/25 QC#21841 Add End
        ssmParam.put("invPrtPrmoItems", INV_PRT_PRMO_ITEMS); // QC#29371  SRNO.11 2019/02/01 Add

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSaleSummary", ssmParam);
    }

    private int setSmry(List<INV_PRT_SMRYTMsg> invPrtSmryList, List<Map<String, Object>> resultMapList, ResultSet rs, int lineCount, boolean isEasyPacFlg, boolean isMainte)
        throws SQLException, NWCB010001BusinessException {
        
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put(INV_TP_CD, rs.getString(INV_TP_CD));
        rsMap.put(INV_NUM, rs.getString(INV_NUM));
        rsMap.put(INV_CHARGE_AMT_TXT, rs.getString(INV_CHARGE_AMT_TXT));
        rsMap.put(FRT_AMT_TXT, rs.getString(FRT_AMT_TXT));
        rsMap.put(TAX_AMT_TXT, rs.getString(TAX_AMT_TXT));
        rsMap.put(INV_AMT_TXT, rs.getString(INV_AMT_TXT));
        rsMap.put(PRE_PMT_AMT_TXT, rs.getString(PRE_PMT_AMT_TXT));
        rsMap.put(BALANCE_DUE_TXT, rs.getString(BALANCE_DUE_TXT));
        rsMap.put(INV_TP_CD, rs.getString(INV_TP_CD));
        rsMap.put("CCY_SGN_TXT", rs.getString("CCY_SGN_TXT"));
        
        rsMap.put("INV_TOT_DEAL_FRT_AMT", rs.getBigDecimal("INV_TOT_DEAL_FRT_AMT"));
        rsMap.put("INV_TOT_DEAL_TAX_AMT", rs.getBigDecimal("INV_TOT_DEAL_TAX_AMT"));
        rsMap.put("INV_TOT_DEAL_SLS_AMT", rs.getBigDecimal("INV_TOT_DEAL_SLS_AMT"));
        rsMap.put("INV_TOT_DEAL_NET_AMT", rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
        
        INV_PRT_SMRYTMsg invPrtSmryTMsg;

        String invTpCd = (String) rsMap.get(INV_TP_CD);
        String invNum  = (String) rsMap.get(INV_NUM);
        String ccySgnTxt  = (String) rsMap.get("CCY_SGN_TXT");

        String ordClsNm = "";
        String prevOrdClsNm = null;
        String invSmryLineTpNm = "";

        BigDecimal invSmryStTaxAmt   = new BigDecimal(0);
        BigDecimal invSmryCntyTaxAmt = new BigDecimal(0);
        BigDecimal invSmryCityTaxAmt = new BigDecimal(0);
        BigDecimal invSmryTotTaxAmt  = new BigDecimal(0);
        BigDecimal invSmrySubTotAmt  = new BigDecimal(0);

        for (Map<String, Object> resultMap : resultMapList) {
            ordClsNm        = resultMap.get(SOURCE) == null ? "" : (String) resultMap.get(SOURCE);
            invSmryLineTpNm = resultMap.get(PRODUCT_TYPE) == null ? "" : (String) resultMap.get(PRODUCT_TYPE);
            
            if (prevOrdClsNm == null) {
                prevOrdClsNm = ordClsNm;
            }
            
            // QC#20141 mod Start
            if (isEasyPacFlg) {
                if (COPIER.equals(invSmryLineTpNm)) {
                    invSmryLineTpNm = COPIER_EASY_PAC1;
                } else if (SHORTFALL.equals(invSmryLineTpNm)) {
                    invSmryLineTpNm = SHORTFALL_EASY_PAC1;
                }
            }
            // QC#20141 mod End
            // QC#51634 2019/07/16 Add Start
            if (!ordClsNm.equals(prevOrdClsNm)) {

                // tax line
                lineCount++;
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, prevOrdClsNm, null, lineCount, isEasyPacFlg, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryStTaxAmtTxt,   fmtAmtFromNumToStrInvTp(invSmryStTaxAmt, invTpCd, ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCntyTaxAmtTxt, fmtAmtFromNumToStrInvTp(invSmryCntyTaxAmt, invTpCd, ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCityTaxAmtTxt, fmtAmtFromNumToStrInvTp(invSmryCityTaxAmt, invTpCd, ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotTaxAmtTxt,  fmtAmtFromNumToStrInvTp(invSmryTotTaxAmt, invTpCd, ccySgnTxt));
                invPrtSmryList.add(invPrtSmryTMsg);
                // sub total
                lineCount++;
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, prevOrdClsNm, null, lineCount, isEasyPacFlg, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmrySubTotAmtTxt,  fmtAmtFromNumToStrInvTp(invSmrySubTotAmt, invTpCd, ccySgnTxt));
                invPrtSmryList.add(invPrtSmryTMsg);

                // init amount
                invSmryStTaxAmt   = new BigDecimal(0);
                invSmryCntyTaxAmt = new BigDecimal(0);
                invSmryCityTaxAmt = new BigDecimal(0);
                invSmryTotTaxAmt  = new BigDecimal(0);
                invSmrySubTotAmt  = new BigDecimal(0);

                prevOrdClsNm = ordClsNm;
            }
            // QC#51634 2019/07/16 Add End

            //QC#9356
            if (!hasValue(ordClsNm)) {
                S21InfoLogOutput.println("SVC_INV_LINE.SVC_PGM_MDSE_CD is not setup on INV_PRINT_SVC_PGM.Invoice#[" + invNum + "]. Please check it.");
                NWCB010001BusinessException e = new NWCB010001BusinessException();
                //NWCM0146E=0,{@} is not setup.
                e.setNumber(invNum);
                e.setMsgId(NWCM0146E);
                e.setMsgParam(new String[]{"INV_PRINT_SVC_PGM.INV_PRINT_SVC_PGM_CD"});
                throw e;
            }
            
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, ordClsNm, invSmryLineTpNm, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryLineTpNm,       invSmryLineTpNm);
            // 2019/07/06 QC#50885 Mod Start
            String qty;

            if (isMainte){
                qty = "";
            } else {
                qty = setNegateQty((String) resultMap.get(QTY), invTpCd);
            }

            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryLineQtyTxt,     qty);
            //ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryLineQtyTxt,     setNegateQty((String) resultMap.get(QTY), invTpCd));
            // 2019/07/06 QC#50885 Mod End

            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryBaseChrgAmtTxt, setNegate((String) resultMap.get(BASE_CHARGE), invTpCd));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryAttChrgAmtTxt,  setNegate((String) resultMap.get(ATTACHMENT_CHARGE), invTpCd));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryUsgChrgAmtTxt,  setNegate((String) resultMap.get(USAGE_CHARGE), invTpCd));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryChrgAmtTxt,     setNegate((String) resultMap.get(AMOUNT), invTpCd));
            invPrtSmryList.add(invPrtSmryTMsg);

            // QC#51634 2019/07/16 Del Start
            //if (!ordClsNm.equals(prevOrdClsNm)) {
            //
            //    // tax line
            //    lineCount++;
            //    //invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, ordClsNm, null, lineCount, isEasyPacFlg);
            //    invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, prevOrdClsNm, null, lineCount, isEasyPacFlg);
            //    ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryStTaxAmtTxt,   fmtAmtFromNumToStrInvTp(invSmryStTaxAmt, invTpCd, ccySgnTxt));
            //    ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCntyTaxAmtTxt, fmtAmtFromNumToStrInvTp(invSmryCntyTaxAmt, invTpCd, ccySgnTxt));
            //    ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCityTaxAmtTxt, fmtAmtFromNumToStrInvTp(invSmryCityTaxAmt, invTpCd, ccySgnTxt));
            //    ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotTaxAmtTxt,  fmtAmtFromNumToStrInvTp(invSmryTotTaxAmt, invTpCd, ccySgnTxt));
            //    invPrtSmryList.add(invPrtSmryTMsg);
            //    // sub total
            //    lineCount++;
            //    //invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, ordClsNm, null, lineCount, isEasyPacFlg);
            //    //invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, prevOrdClsNm, null, lineCount, isEasyPacFlg);
            //    ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmrySubTotAmtTxt,  fmtAmtFromNumToStrInvTp(invSmrySubTotAmt, invTpCd, ccySgnTxt));
            //    invPrtSmryList.add(invPrtSmryTMsg);
            //
            //    // init amount
            //    invSmryStTaxAmt   = new BigDecimal(0);
            //    invSmryCntyTaxAmt = new BigDecimal(0);
            //    invSmryCityTaxAmt = new BigDecimal(0);
            //    invSmryTotTaxAmt  = new BigDecimal(0);
            //    invSmrySubTotAmt  = new BigDecimal(0);
            //
            //    prevOrdClsNm = ordClsNm;
            //}
            // QC#51634 2019/07/16 Del End

            //summary
            BigDecimal stateTaxAmt  = (BigDecimal) resultMap.get(STATE_TAX);
            if (stateTaxAmt != null) {
                invSmryStTaxAmt = invSmryStTaxAmt.add(stateTaxAmt);
            }
            BigDecimal countyTaxAmt = (BigDecimal) resultMap.get(COUNTY_TAX);
            if (countyTaxAmt != null) {
                invSmryCntyTaxAmt = invSmryCntyTaxAmt.add(countyTaxAmt);
            }
            BigDecimal cityTaxAmt   = (BigDecimal) resultMap.get(CITY_TAX);
            if (cityTaxAmt != null) {
                invSmryCityTaxAmt = invSmryCityTaxAmt.add(cityTaxAmt);
            }
            BigDecimal totalTaxAmt  = (BigDecimal) resultMap.get(TOTAL_TAX);
            if (totalTaxAmt != null) {
                invSmryTotTaxAmt = invSmryTotTaxAmt.add(totalTaxAmt);
            }
            BigDecimal subTotalAmt  = (BigDecimal) resultMap.get(SUBTOTALS);
            if (subTotalAmt != null) {
                invSmrySubTotAmt = invSmrySubTotAmt.add(subTotalAmt);
            }
        }
        //Total Tax
        lineCount++;
        // QC#57344
        invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, ordClsNm, null, lineCount, isEasyPacFlg, invPrtSmryList);
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryStTaxAmtTxt,   fmtAmtFromNumToStrInvTp(invSmryStTaxAmt, invTpCd, ccySgnTxt));
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCntyTaxAmtTxt, fmtAmtFromNumToStrInvTp(invSmryCntyTaxAmt, invTpCd, ccySgnTxt));
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCityTaxAmtTxt, fmtAmtFromNumToStrInvTp(invSmryCityTaxAmt, invTpCd, ccySgnTxt));
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotTaxAmtTxt,  fmtAmtFromNumToStrInvTp(invSmryTotTaxAmt, invTpCd, ccySgnTxt));
        invPrtSmryList.add(invPrtSmryTMsg);
        //Subtotals
        lineCount++;
        // QC#57344
        invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, ordClsNm, null, lineCount, isEasyPacFlg, invPrtSmryList);
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmrySubTotAmtTxt,  fmtAmtFromNumToStrInvTp(invSmrySubTotAmt, invTpCd, ccySgnTxt));
        invPrtSmryList.add(invPrtSmryTMsg);

        //Total Charges
        lineCount++;
        // QC#57344
        invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotChrgAmtTxt,  fmtAmtFromNumToStrInvTp((BigDecimal) rsMap.get(INV_TOT_DEAL_SLS_AMT), invTpCd, ccySgnTxt));
        invPrtSmryList.add(invPrtSmryTMsg);

        //Freight
        lineCount++;
        // QC#57344
        invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryFrtAmtTxt,  fmtAmtFromNumToStrInvTp((BigDecimal) rsMap.get(INV_TOT_DEAL_FRT_AMT), invTpCd, ccySgnTxt));
        invPrtSmryList.add(invPrtSmryTMsg);

        //Total Tax
        lineCount++;
        // QC#57344
        invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryFrtTaxAmtTxt,  fmtAmtFromNumToStrInvTp((BigDecimal) rsMap.get(INV_TOT_DEAL_TAX_AMT), invTpCd, ccySgnTxt));
        invPrtSmryList.add(invPrtSmryTMsg);

        //Total Amount
        lineCount++;
        // QC#57344
        invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotAmtTxt,  fmtAmtFromNumToStrInvTp((BigDecimal) rsMap.get(INV_TOT_DEAL_NET_AMT), invTpCd, ccySgnTxt));
        invPrtSmryList.add(invPrtSmryTMsg);

        // QC#20971 Mod Start
        // QC#30687 Mod Start
        //if (PMT_TERM_CASH_DISC.CREDIT_CARD.equals(rs.getString("PMT_TERM_CASH_DISC_CD")) ){
        if (PMT_TERM_CASH_DISC.CREDIT_CARD.equals(rs.getString("PMT_TERM_CASH_DISC_CD")) && chkCrCardChrgCpltCd(rs.getString("CR_CARD_CHRG_CPLT_CD"))){
        // QC#30687 Mod End
            //Prepay Amount
            lineCount++;
            String invSmryBalAmtTxt = setNegate((String) rsMap.get(BALANCE_DUE_TXT), invTpCd);
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryPrePmtAmtTxt, invSmryBalAmtTxt);
            invPrtSmryList.add(invPrtSmryTMsg);

            //Net Amount Due
            lineCount++;
            String invSmryPrePmtAmtTxt = setNegate(createPrePmtAmtTxt((String) rsMap.get(PRE_PMT_AMT_TXT), ccySgnTxt), invTpCd);
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryBalAmtTxt, invSmryPrePmtAmtTxt);
            invPrtSmryList.add(invPrtSmryTMsg);
        } else {
            //Prepay Amount
            lineCount++;
            String invSmryPrePmtAmtTxt = setNegate(createPrePmtAmtTxt((String) rsMap.get(PRE_PMT_AMT_TXT), ccySgnTxt), invTpCd);
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryPrePmtAmtTxt, invSmryPrePmtAmtTxt);
            invPrtSmryList.add(invPrtSmryTMsg);

            //Net Amount Due
            lineCount++;
            String invSmryBalAmtTxt = setNegate((String) rsMap.get(BALANCE_DUE_TXT), invTpCd);
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryBalAmtTxt, invSmryBalAmtTxt);
            invPrtSmryList.add(invPrtSmryTMsg);
        }
        // QC#20971 Mod End

        return lineCount;
    }

    // QC#30687 Add Start
    /**
     * chkCrCardChrgCpltCd
     * 
     * @param crCardChrgCpltCd String
     * @return boolean
     */
    private boolean chkCrCardChrgCpltCd(String crCardChrgCpltCd) {

        if (hasValue(crCardChrgCpltCd)) {
            if (S21StringUtil.isEquals(CR_CARD_CHRG_CPLT.COMPLETED_CREDIT_CARD_CHARGE, crCardChrgCpltCd)) {
                return true;
            }
        }
        return false;
    }
    // QC#30687 Mod End

    @SuppressWarnings("unchecked")
    private int setSmryforSales(List<INV_PRT_SMRYTMsg> invPrtSmryList, List<Map<String, Object>> resultMapList, ResultSet rs, int lineCount, boolean isEasyPacFlg, String taxDtlFlg)
    throws SQLException, NWCB010001BusinessException {
    
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put(INV_TP_CD, rs.getString(INV_TP_CD));
        rsMap.put(INV_NUM, rs.getString(INV_NUM));
        rsMap.put(INV_CHARGE_AMT_TXT, rs.getString(INV_CHARGE_AMT_TXT));
        rsMap.put(FRT_AMT_TXT, rs.getString(FRT_AMT_TXT));
        rsMap.put(TAX_AMT_TXT, rs.getString(TAX_AMT_TXT));
        rsMap.put(INV_AMT_TXT, rs.getString(INV_AMT_TXT));
        rsMap.put(PRE_PMT_AMT_TXT, rs.getString(PRE_PMT_AMT_TXT));
        rsMap.put(BALANCE_DUE_TXT, rs.getString(BALANCE_DUE_TXT));
        rsMap.put(INV_TP_CD, rs.getString(INV_TP_CD));
        rsMap.put("CCY_SGN_TXT", rs.getString("CCY_SGN_TXT"));
        
        rsMap.put("INV_TOT_DEAL_FRT_AMT", rs.getBigDecimal("INV_TOT_DEAL_FRT_AMT"));
        rsMap.put("INV_TOT_DEAL_TAX_AMT", rs.getBigDecimal("INV_TOT_DEAL_TAX_AMT"));
        rsMap.put("INV_TOT_DEAL_SLS_AMT", rs.getBigDecimal("INV_TOT_DEAL_SLS_AMT"));
        rsMap.put("INV_TOT_DEAL_NET_AMT", rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
        
        INV_PRT_SMRYTMsg invPrtSmryTMsg;
    
        String invTpCd = (String) rsMap.get(INV_TP_CD);
        String invNum  = (String) rsMap.get(INV_NUM);
        String ccySgnTxt  = (String) rsMap.get("CCY_SGN_TXT");
    
        String ordClsNm = "";
        String prevOrdClsNm = null;
        String invSmryLineTpNm = "";
    
        BigDecimal invSmryStTaxAmt   = new BigDecimal(0);
        BigDecimal invSmryCntyTaxAmt = new BigDecimal(0);
        BigDecimal invSmryCityTaxAmt = new BigDecimal(0);
        BigDecimal invSmryTotTaxAmt  = new BigDecimal(0);
        BigDecimal invSmrySubTotAmt  = new BigDecimal(0);

        // START 2018/09/10 T.Ogura [QC#27999,ADD]
        boolean freightInvoiceOnlyFlg = isFreightInvoiceOnly(invNum);
        // END   2018/09/10 T.Ogura [QC#27999,ADD]

        for (Map<String, Object> resultMap : resultMapList) {
            ordClsNm        = resultMap.get(SOURCE) == null ? "" : (String) resultMap.get(SOURCE);
            invSmryLineTpNm = resultMap.get(PRODUCT_TYPE) == null ? "" : (String) resultMap.get(PRODUCT_TYPE);
            
            if (prevOrdClsNm == null) {
                prevOrdClsNm = ordClsNm;
            }
            
            // QC#20141 mod Start
            if (isEasyPacFlg) {
                if (COPIER.equals(invSmryLineTpNm)) {
                    invSmryLineTpNm = COPIER_EASY_PAC1;
                } else if (SHORTFALL.equals(invSmryLineTpNm)) {
                    invSmryLineTpNm = SHORTFALL_EASY_PAC1;
                }
            }
            // QC#20141 mod End
    
            //QC#9356
            //if (!hasValue(ordClsNm)) {
            //    S21InfoLogOutput.println("DebugMsg:INV_PRINT_SVC_PGM is not setup.Invoice#[" + invNum + "]");
            //    NWCB010001BusinessException e = new NWCB010001BusinessException();
            //    //NWCM0146E=0,{@} is not setup.
            //    e.setNumber(invNum);
            //    e.setMsgId(NWCM0146E);
            //    e.setMsgParam(new String[]{"INV_PRINT_SVC_PGM.INV_PRINT_SVC_PGM_CD"});
            //    throw e;
            //}
            
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, ordClsNm, invSmryLineTpNm, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryLineTpNm,       invSmryLineTpNm);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryLineQtyTxt,     setNegateQty((String) resultMap.get(QTY), invTpCd));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryBaseChrgAmtTxt, setNegate((String) resultMap.get(BASE_CHARGE), invTpCd));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryAttChrgAmtTxt,  setNegate((String) resultMap.get(ATTACHMENT_CHARGE), invTpCd));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryUsgChrgAmtTxt,  setNegate((String) resultMap.get(USAGE_CHARGE), invTpCd));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryChrgAmtTxt,     setNegate((String) resultMap.get(AMOUNT), invTpCd));
            // START 2018/09/10 T.Ogura [QC#27999,ADD]
            if (freightInvoiceOnlyFlg) {
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.ordClsNm,          "");
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryLineTpNm,   "");
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryLineQtyTxt, "");
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryChrgAmtTxt, "");
            }
            // END   2018/09/10 T.Ogura [QC#27999,ADD]
            // QC#29371  SRNO.11 2019/02/01 Mod Start
            // invPrtSmryList.add(invPrtSmryTMsg);
            if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, (String) resultMap.get(NWCB010001Constant.PRMO_ITEM_PRINT_FLG))
                    && ZYPCommonFunc.hasValue((String) resultMap.get(NWCB010001Constant.COA_MDSE_TP_CD))) {
            } else {
                invPrtSmryList.add(invPrtSmryTMsg);
            }
            // QC#29371  SRNO.11 2019/02/01 Mod End

            if (!ordClsNm.equals(prevOrdClsNm)) {
                // tax line
                lineCount++;
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, ordClsNm, null, lineCount, isEasyPacFlg, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryStTaxAmtTxt,   fmtAmtFromNumToStrInvTp(invSmryStTaxAmt, invTpCd, ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCntyTaxAmtTxt, fmtAmtFromNumToStrInvTp(invSmryCntyTaxAmt, invTpCd, ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCityTaxAmtTxt, fmtAmtFromNumToStrInvTp(invSmryCityTaxAmt, invTpCd, ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotTaxAmtTxt,  fmtAmtFromNumToStrInvTp(invSmryTotTaxAmt, invTpCd, ccySgnTxt));
                invPrtSmryList.add(invPrtSmryTMsg);
                // sub total
                lineCount++;
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, ordClsNm, null, lineCount, isEasyPacFlg, invPrtSmryList);

                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmrySubTotAmtTxt,  fmtAmtFromNumToStrInvTp(invSmrySubTotAmt, invTpCd, ccySgnTxt));
                invPrtSmryList.add(invPrtSmryTMsg);

                // init amount
                invSmryStTaxAmt = new BigDecimal(0);
                invSmryCntyTaxAmt = new BigDecimal(0);
                invSmryCityTaxAmt = new BigDecimal(0);
                invSmryTotTaxAmt = new BigDecimal(0);
                invSmrySubTotAmt = new BigDecimal(0);

                prevOrdClsNm = ordClsNm;
            }
    
            //summary
            BigDecimal stateTaxAmt  = (BigDecimal) resultMap.get(STATE_TAX);
            if (stateTaxAmt != null) {
                invSmryStTaxAmt = invSmryStTaxAmt.add(stateTaxAmt);
            }
            BigDecimal countyTaxAmt = (BigDecimal) resultMap.get(COUNTY_TAX);
            if (countyTaxAmt != null) {
                invSmryCntyTaxAmt = invSmryCntyTaxAmt.add(countyTaxAmt);
            }
            BigDecimal cityTaxAmt   = (BigDecimal) resultMap.get(CITY_TAX);
            if (cityTaxAmt != null) {
                invSmryCityTaxAmt = invSmryCityTaxAmt.add(cityTaxAmt);
            }
            BigDecimal totalTaxAmt  = (BigDecimal) resultMap.get(TOTAL_TAX);
            if (totalTaxAmt != null) {
                invSmryTotTaxAmt = invSmryTotTaxAmt.add(totalTaxAmt);
            }
            BigDecimal subTotalAmt  = (BigDecimal) resultMap.get(SUBTOTALS);
            if (subTotalAmt != null) {
                invSmrySubTotAmt = invSmrySubTotAmt.add(subTotalAmt);
            }
            
        }
        
        // if supplies
        BigDecimal suppliesCount = rs.getBigDecimal("SUPLY_CNT");
        if (suppliesCount != null && suppliesCount.compareTo(BigDecimal.ONE) >= 0) {
            
            //get Freight Info per invoice# 
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("invNum", rs.getString(INV_NUM));
            ssmParam.put("taxDtlFlg", taxDtlFlg);
            ssmParam.put("dsSlsTaxTpCd11", DS_SLS_TAX_TP.FREIGHT_STATE_TAX);
            ssmParam.put("dsSlsTaxTpCd12", DS_SLS_TAX_TP.FREIGHT_COUNTY_TAX);
            ssmParam.put("dsSlsTaxTpCd13", DS_SLS_TAX_TP.FREIGHT_CITY_TAX);
            ssmParam.put("qtyFormat", FM_QTY);
            ssmParam.put("amtFormat", FM_AMT);
            // 2018/05/25 QC#21841 Add Start
            ssmParam.put("invLineCatgFrt", INV_LINE_CATG.FREIGHT);
            // 2018/05/25 QC#21841 Add End
            Map<String, Object> freightInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getSaleSummaryFreightInfo", ssmParam);
            BigDecimal frtTaxAmt = BigDecimal.ZERO;  // FRT_TAX
            BigDecimal frtStTaxAmt = BigDecimal.ZERO;  // FRT_STATE_TAX
            BigDecimal frtCntyTaxAmt = BigDecimal.ZERO;  // FRT_COUNTY_TAX
            BigDecimal frtCtyTaxAmt = BigDecimal.ZERO;  // FRT_CITY_TAX
            BigDecimal frtChrgAmt = BigDecimal.ZERO;  // FRT_CHRG_AMT
            if (freightInfoMap != null) {
                frtTaxAmt = (BigDecimal) freightInfoMap.get("FRT_TAX") == null ? BigDecimal.ZERO : (BigDecimal) freightInfoMap.get("FRT_TAX");
                frtStTaxAmt = (BigDecimal) freightInfoMap.get("FRT_STATE_TAX") == null ? BigDecimal.ZERO : (BigDecimal) freightInfoMap.get("FRT_STATE_TAX");
                frtCntyTaxAmt = (BigDecimal) freightInfoMap.get("FRT_COUNTY_TAX") == null ? BigDecimal.ZERO : (BigDecimal) freightInfoMap.get("FRT_COUNTY_TAX");
                frtCtyTaxAmt = (BigDecimal) freightInfoMap.get("FRT_CITY_TAX") == null ? BigDecimal.ZERO : (BigDecimal) freightInfoMap.get("FRT_CITY_TAX");
                frtChrgAmt = (BigDecimal) freightInfoMap.get("FRT_CHRG_AMT") == null ? BigDecimal.ZERO : (BigDecimal) freightInfoMap.get("FRT_CHRG_AMT");
            }
            
            //################################################################
            //Supplies INV_SMRY_AMT_SPLY_TXT
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, ordClsNm, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryAmtSplyTxt,  fmtAmtFromNumToStrInvTp((BigDecimal) rsMap.get(INV_TOT_DEAL_SLS_AMT), invTpCd, ccySgnTxt));
            invPrtSmryList.add(invPrtSmryTMsg);
            
            //Total tax
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, ordClsNm, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryStTaxAmtTxt,   fmtAmtFromNumToStrInvTp(invSmryStTaxAmt, invTpCd, ccySgnTxt));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCntyTaxAmtTxt, fmtAmtFromNumToStrInvTp(invSmryCntyTaxAmt, invTpCd, ccySgnTxt));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCityTaxAmtTxt, fmtAmtFromNumToStrInvTp(invSmryCityTaxAmt, invTpCd, ccySgnTxt));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotTaxAmtTxt,  fmtAmtFromNumToStrInvTp(invSmryTotTaxAmt, invTpCd, ccySgnTxt));
            invPrtSmryList.add(invPrtSmryTMsg);
            
            //Subtotals
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, ordClsNm, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmrySubTotAmtTxt,  fmtAmtFromNumToStrInvTp(invSmrySubTotAmt, invTpCd, ccySgnTxt));
            invPrtSmryList.add(invPrtSmryTMsg);
            
            //################################################################
            //Freight
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryFrtAmtTxt,  fmtAmtFromNumToStrInvTp((BigDecimal) rsMap.get(INV_TOT_DEAL_FRT_AMT), invTpCd, ccySgnTxt));
            invPrtSmryList.add(invPrtSmryTMsg);
            
            //Tax (Freight Tax)
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryAmtFrtTaxTxt,     fmtAmtFromNumToStrInvTp(frtTaxAmt, invTpCd, ccySgnTxt));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryStAmtTaxTxt,   fmtAmtFromNumToStrInvTp(frtStTaxAmt, invTpCd, ccySgnTxt));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCntyAmtTaxTxt, fmtAmtFromNumToStrInvTp(frtCntyTaxAmt, invTpCd, ccySgnTxt));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCtyAmtTaxTxt, fmtAmtFromNumToStrInvTp(frtCtyTaxAmt, invTpCd, ccySgnTxt));
            invPrtSmryList.add(invPrtSmryTMsg);
            
            //SubTotals
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryChrgAmtFrtTxt,  fmtAmtFromNumToStrInvTp(frtChrgAmt, invTpCd, ccySgnTxt));
            invPrtSmryList.add(invPrtSmryTMsg);
    
            //################################################################
            //Total Items with Freight 
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotAmtTxt,  
                    fmtAmtFromNumToStrInvTp(((BigDecimal) rsMap.get(INV_TOT_DEAL_FRT_AMT)).add((BigDecimal) rsMap.get(INV_TOT_DEAL_SLS_AMT)), invTpCd, ccySgnTxt));
            invPrtSmryList.add(invPrtSmryTMsg);
            
            //Total Tax
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryAmtFrtTaxTxt,  fmtAmtFromNumToStrInvTp((BigDecimal) rsMap.get(INV_TOT_DEAL_TAX_AMT), invTpCd, ccySgnTxt));
            //ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryFrtTaxAmtTxt,  fmtAmtFromNumToStrInvTp((BigDecimal) rsMap.get(INV_TOT_DEAL_TAX_AMT), invTpCd, ccySgnTxt));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryStAmtTaxTxt,   fmtAmtFromNumToStrInvTp(frtStTaxAmt.add(invSmryStTaxAmt), invTpCd, ccySgnTxt));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCntyAmtTaxTxt, fmtAmtFromNumToStrInvTp(frtCntyTaxAmt.add(invSmryCntyTaxAmt), invTpCd, ccySgnTxt));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCtyAmtTaxTxt, fmtAmtFromNumToStrInvTp(frtCtyTaxAmt.add(invSmryCityTaxAmt), invTpCd, ccySgnTxt));
            invPrtSmryList.add(invPrtSmryTMsg);
            
        } else {
    
            //################################################################
            //Total tax
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, ordClsNm, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryStTaxAmtTxt,   fmtAmtFromNumToStrInvTp(invSmryStTaxAmt, invTpCd, ccySgnTxt));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCntyTaxAmtTxt, fmtAmtFromNumToStrInvTp(invSmryCntyTaxAmt, invTpCd, ccySgnTxt));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCityTaxAmtTxt, fmtAmtFromNumToStrInvTp(invSmryCityTaxAmt, invTpCd, ccySgnTxt));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotTaxAmtTxt,  fmtAmtFromNumToStrInvTp(invSmryTotTaxAmt, invTpCd, ccySgnTxt));
            // START 2018/09/10 T.Ogura [QC#27999,ADD]
            if (freightInvoiceOnlyFlg) {
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.ordClsNm,            "");
            }
            // END   2018/09/10 T.Ogura [QC#27999,ADD]
            invPrtSmryList.add(invPrtSmryTMsg);
            
            // Subtotals
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, ordClsNm, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmrySubTotAmtTxt,  fmtAmtFromNumToStrInvTp(invSmrySubTotAmt, invTpCd, ccySgnTxt));
            // START 2018/09/10 T.Ogura [QC#27999,ADD]
            if (freightInvoiceOnlyFlg) {
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.ordClsNm,            "");
            }
            // END   2018/09/10 T.Ogura [QC#27999,ADD]
            invPrtSmryList.add(invPrtSmryTMsg);
            
            //################################################################
            //Total Charges
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotChrgAmtTxt,  fmtAmtFromNumToStrInvTp((BigDecimal) rsMap.get(INV_TOT_DEAL_SLS_AMT), invTpCd, ccySgnTxt));
            invPrtSmryList.add(invPrtSmryTMsg);
    
            //Freight
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryFrtAmtTxt,  fmtAmtFromNumToStrInvTp((BigDecimal) rsMap.get(INV_TOT_DEAL_FRT_AMT), invTpCd, ccySgnTxt));
            invPrtSmryList.add(invPrtSmryTMsg);
    
            //Total Tax
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryFrtTaxAmtTxt,  fmtAmtFromNumToStrInvTp((BigDecimal) rsMap.get(INV_TOT_DEAL_TAX_AMT), invTpCd, ccySgnTxt));
            invPrtSmryList.add(invPrtSmryTMsg);
    
            //################################################################
            //Total Amount
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotAmtTxt,  fmtAmtFromNumToStrInvTp((BigDecimal) rsMap.get(INV_TOT_DEAL_NET_AMT), invTpCd, ccySgnTxt));
            invPrtSmryList.add(invPrtSmryTMsg);
        }
        // QC#20971 Mod Start
        // QC#30687 Mod Start
        //if (PMT_TERM_CASH_DISC.CREDIT_CARD.equals(rs.getString("PMT_TERM_CASH_DISC_CD")) ){
        if (PMT_TERM_CASH_DISC.CREDIT_CARD.equals(rs.getString("PMT_TERM_CASH_DISC_CD")) && chkCrCardChrgCpltCd(rs.getString("CR_CARD_CHRG_CPLT_CD"))){
        // QC#30687 Mod End
            //Prepay Amount
            lineCount++;
            String invSmryBalAmtTxt = setNegate((String) rsMap.get(BALANCE_DUE_TXT), invTpCd);
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryPrePmtAmtTxt, invSmryBalAmtTxt);
            invPrtSmryList.add(invPrtSmryTMsg);
    
            //Net Amount Due
            lineCount++;
            String invSmryPrePmtAmtTxt = setNegate(createPrePmtAmtTxt((String) rsMap.get(PRE_PMT_AMT_TXT), ccySgnTxt), invTpCd);
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryBalAmtTxt, invSmryPrePmtAmtTxt);
            invPrtSmryList.add(invPrtSmryTMsg);
        } else {
            //Prepay Amount
            lineCount++;
            String invSmryPrePmtAmtTxt = setNegate(createPrePmtAmtTxt((String) rsMap.get(PRE_PMT_AMT_TXT), ccySgnTxt), invTpCd);
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryPrePmtAmtTxt, invSmryPrePmtAmtTxt);
            invPrtSmryList.add(invPrtSmryTMsg);
    
            //Net Amount Due
            lineCount++;
            String invSmryBalAmtTxt = setNegate((String) rsMap.get(BALANCE_DUE_TXT), invTpCd);
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(invNum, null, null, lineCount, isEasyPacFlg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryBalAmtTxt, invSmryBalAmtTxt);
            invPrtSmryList.add(invPrtSmryTMsg);
        }
        // QC#20971 Mod End

        return lineCount;
    }

    // QC#20971 Add Start
    /**
     * @param prePmtAmtTxt
     * @param ccySgnTxt
     * @return
     */
    private String createPrePmtAmtTxt(String prePmtAmtTxt, String ccySgnTxt){
        if (prePmtAmtTxt == null) {
            prePmtAmtTxt = ccySgnTxt + "0.00";
        }
        return prePmtAmtTxt;
    }
    // QC#20971 Add Start

    // QC#57344
    private INV_PRT_SMRYTMsg getInvPrtSmryPMsg(String invBillNum, String ordClsNm, String invSmryLineTpNm, int lineCount, boolean isEasyPacFlg, List<INV_PRT_SMRYTMsg> invPrtSmryList) {

        INV_PRT_SMRYTMsg invPrtSmryTMsg = new INV_PRT_SMRYTMsg();
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invPrtSmryPk,ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_SMRY_SQ));
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invBillNum, invBillNum);

        // QC#20141 mod Start
        if (isEasyPacFlg) {
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.ordClsNm, "");
        } else {
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.ordClsNm, ordClsNm);
        } 
        //QC#20141 mod End
        
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryLineTpNm, invSmryLineTpNm);
        BigDecimal pageNum = BigDecimal.ONE;
        if (lineCount > ONE_PAGE_LINE_COUNT) {
            // QC#57344
            if (invPrtSmryList != null && invPrtSmryList.size() == 0) {
                pageNum = new BigDecimal(1);
            } else {
                pageNum = new BigDecimal(2);
            }
        }
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryPageNum, pageNum);

        return invPrtSmryTMsg;
    }

    // START 2018/09/10 T.Ogura [QC#27999,ADD]
    private boolean isFreightInvoiceOnly(String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        ssmParam.put("invLineCatgFrt", INV_LINE_CATG.FREIGHT);
        Integer resCnt = (Integer) ssmBatchClient.queryObject("getFreightInvoiceOnly", ssmParam);
        if (resCnt.intValue() > 0) {
            return true;
        }
        return false;
    }
    // END   2018/09/10 T.Ogura [QC#27999,ADD]

    private boolean setFleetInfo(ResultSet rs, Map<String, Object> preConslBillInfoForFleetMap) throws SQLException, NWCB010001BusinessException {

        String invNum = rs.getString(INV_NUM);
        String taxDtlFlg = rs.getString(TAX_DETAIL_FLG);
        // QC#52209 2019/07/31 Del Start
        // QC#30346 2019/02/18 Add Start
        //List<Map<String, Object>> ssmResult = getFleetInvLine(invNum, ZYPConstant.FLG_ON_Y); // QC#29371
        //boolean resultFlg = insertFleetInvLine(invNum, ssmResult, ZYPConstant.FLG_ON_Y); // QC#29371
        //if (!resultFlg) {
        //    return false;
        //}
        // QC#30346 2019/02/18 Add End
        // QC#52209 2019/07/31 Del End

        // GET Fleet SubTotal
        List<Map<String, Object>> ssmFleetResult = getFleetSubTotal(invNum, taxDtlFlg, ZYPConstant.FLG_ON_Y); // QC#29371
        if (ssmFleetResult.size() == 0) {
            //NWCM0112E=It failed to get [@].(@)
            S21InfoLogOutput.println(NWCM0112E, toArray("FleetSubTotal", invNum));
            return false;
        }
        // INSERT FLEET
        boolean result = setFleet(ssmFleetResult, rs, preConslBillInfoForFleetMap);
        if (!result) {
            return false;
        }
        return true;
    }
    // QC#52209 2019/07/31 Mod Start
    // QC#30346 2019/02/18 Add Start
    //@SuppressWarnings("unchecked")
    //private List<Map<String, Object>> getFleetInvLine(String invNum, String fleetFlg) {
    //    Map<String, Object> ssmParam = new HashMap<String, Object>();
    //    ssmParam.put("glblCmpyCd", glblCmpyCd);
    //    ssmParam.put("invNum", invNum);
    //    return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFleetInvLine", ssmParam);
    //}
    //private boolean insertFleetInvLine(String invNum, List<Map<String, Object>> resultMapList, String fleetFlg) {
    //
    //    List<NWCB010001InvPrtFleetLineBean> invPrtFleetLineBeanList = new ArrayList<NWCB010001InvPrtFleetLineBean>();
    //    List<NWCB010001InvPrtFleetLineBean> rentalSplitList = new ArrayList<NWCB010001InvPrtFleetLineBean>();
    //    List<INV_PRT_FLEET_LINETMsg> invPrtFleetLineTMsgList = new ArrayList<INV_PRT_FLEET_LINETMsg>();
    //    for (Map<String, Object> map : resultMapList) {
    //        NWCB010001InvPrtFleetLineBean bean = new NWCB010001InvPrtFleetLineBean();
    //        bean.setInvNum((String) map.get("INV_NUM"));
    //        bean.setInvBolLineNum((String) map.get("INV_BOL_LINE_NUM"));
    //        bean.setInvLineNum((String) map.get("INV_LINE_NUM"));
    //        bean.setInvLineSubNum((String) map.get("INV_LINE_SUB_NUM"));
    //        bean.setInvLineSubTrxNum((String) map.get("INV_LINE_SUB_TRX_NUM"));
    //        bean.setSvcInvLinePk((BigDecimal) map.get("SVC_INV_LINE_PK"));
    //        bean.setSvcInvNum((String) map.get("SVC_INV_NUM"));
    //        bean.setSvcInvLineNum((String) map.get("SVC_INV_LINE_NUM"));
    //        bean.setInvLineDealTaxAmt((BigDecimal) map.get("INV_LINE_DEAL_TAX_AMT"));
    //        bean.setInvLineDealNetAmt((BigDecimal) map.get("INV_LINE_DEAL_NET_AMT"));
    //        bean.setSvcInvChrgTpCd((String) map.get("SVC_INV_CHRG_TP_CD"));
    //        bean.setShipToCustCd((String) map.get("SHIP_TO_CUST_CD"));
    //        bean.setMdlNm((String) map.get("MDL_NM"));
    //        bean.setBllgPerFromDt((String) map.get("BLLG_PER_FROM_DT"));
    //        bean.setBllgPerThruDt((String) map.get("BLLG_PER_THRU_DT"));
    //        bean.setDsContrPK((BigDecimal) map.get("DS_CONTR_PK"));
    //        bean.setDsContrDtlPk((BigDecimal) map.get("DS_CONTR_DTL_PK"));
    //        bean.setSvcPgmMdseCd((String) map.get("SVC_PGM_MDSE_CD"));
    //        bean.setSvcMachMstrPk((BigDecimal) map.get("SVC_MACH_MSTR_PK"));
    //        bean.setSerNum((String) map.get("SER_NUM"));
    //        bean.setCustIssPoNum((String) map.get("CUST_ISS_PO_NUM"));
    //        bean.setPrntSvcInvLinePK((BigDecimal) map.get("PRNT_SVC_INV_LINE_PK"));
    //        bean.setFirstBllgAttrbValTxt((String) map.get("FIRST_BLLG_ATTRB_VAL_TXT"));
    //        bean.setScdBllgAttrbValTxt((String) map.get("SCD_BLLG_ATTRB_VAL_TXT"));
    //        bean.setThirdBllgAttrbValTxt((String) map.get("THIRD_BLLG_ATTRB_VAL_TXT"));
    //        bean.setFrthBllgAttrbValTxt((String) map.get("FRTH_BLLG_ATTRB_VAL_TXT"));
    //        bean.setSvcInvLineTpCd((String) map.get("SVC_INV_LINE_TP_CD"));
    //        bean.setCopyInclQty((BigDecimal) map.get("COPY_INCL_QTY"));
    //        bean.setDsContrBllgSchdPk((BigDecimal) map.get("DS_CONTR_BLLG_SCHD_PK"));
    //        bean.setInvPrintCovTxt((String) map.get("INV_PRINT_COV_TXT"));
    //        bean.setSvcPgmTpCd((String) map.get("SVC_PGM_TP_CD"));
    //        bean.setDsContrCratTpCd((String) map.get("DS_CONTR_CRAT_TP_CD"));
    //
    //        if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(bean.getSvcInvChrgTpCd())) {
    //            if (SVC_PGM_TP.RENTALSPLIT.equals(bean.getSvcPgmTpCd())) {
    //                rentalSplitList.add(bean);
    //            } else {
    //                invPrtFleetLineBeanList.add(bean);
    //            }
    //        } else {
    //            invPrtFleetLineBeanList.add(bean);
    //        }
    //    }
    //    //merge RentalSplit
    //    for (NWCB010001InvPrtFleetLineBean bean : rentalSplitList) {
    //        NWCB010001InvPrtFleetLineBean data = getRentalOriginal(bean, invPrtFleetLineBeanList);
    //            if (data != null) {
    //                data.setInvLineDealTaxAmt(data.getInvLineDealTaxAmt().add(bean.getInvLineDealTaxAmt()));
    //                data.setInvLineDealNetAmt(data.getInvLineDealNetAmt().add(bean.getInvLineDealNetAmt()));
    //            } else {
    //                invPrtFleetLineBeanList.add(bean);
    //            }
    //    }
    //    for (NWCB010001InvPrtFleetLineBean bean : invPrtFleetLineBeanList) {
    //        INV_PRT_FLEET_LINETMsg invPrtFleetLineTMsg = new INV_PRT_FLEET_LINETMsg();
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.glblCmpyCd, glblCmpyCd);
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.invPrtFleetLinePk, 
    //                ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_FLEET_LINE_SQ));
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.invNum, bean.getInvNum());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.invBolLineNum, bean.getInvBolLineNum());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.invLineNum, bean.getInvLineNum());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.invLineSubNum, bean.getInvLineSubNum());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.invLineSubTrxNum, bean.getInvLineSubTrxNum());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.svcInvLinePk, bean.getSvcInvLinePk());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.svcInvNum, bean.getSvcInvNum());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.svcInvLineNum, bean.getSvcInvLineNum());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.invLineDealTaxAmt, bean.getInvLineDealTaxAmt());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.invLineDealNetAmt, bean.getInvLineDealNetAmt());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.svcInvChrgTpCd, bean.getSvcInvChrgTpCd());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.shipToCustCd, bean.getShipToCustCd());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.mdlNm, bean.getMdlNm());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.bllgPerFromDt, bean.getBllgPerFromDt());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.bllgPerThruDt,bean.getBllgPerThruDt());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.dsContrPk, bean.getDsContrPK());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.dsContrDtlPk, bean.getDsContrDtlPk());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.svcPgmMdseCd,bean.getSvcPgmMdseCd());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.svcMachMstrPk, bean.getSvcMachMstrPk());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.serNum, bean.getSerNum());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.custIssPoNum, bean.getCustIssPoNum());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.prntSvcInvLinePk, bean.getPrntSvcInvLinePK());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.firstBllgAttrbValTxt, bean.getFirstBllgAttrbValTxt());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.scdBllgAttrbValTxt, bean.getScdBllgAttrbValTxt());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.thirdBllgAttrbValTxt, bean.getThirdBllgAttrbValTxt());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.frthBllgAttrbValTxt, bean.getFrthBllgAttrbValTxt());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.fifthBllgAttrbValTxt, bean.getFifthBllgAttrbValTxt());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.sixthBllgAttrbValTxt, bean.getSixthBllgAttrbValTxt());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.svcInvLineTpCd, (String) bean.getSvcInvLineTpCd());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.copyInclQty, bean.getCopyInclQty());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.dsContrBllgSchdPk, bean.getDsContrBllgSchdPk());
    //        ZYPEZDItemValueSetter.setValue(invPrtFleetLineTMsg.invPrintCovTxt, bean.getInvPrintCovTxt());
    //        invPrtFleetLineTMsgList.add(invPrtFleetLineTMsg);
    //    }
    //
    //    if (invPrtFleetLineTMsgList.size() > 0) {
    //        // INSERT
    //        int result = EZDFastTBLAccessor.insert(invPrtFleetLineTMsgList.toArray(new INV_PRT_FLEET_LINETMsg[invPrtFleetLineTMsgList.size()]));
    //        if (result != invPrtFleetLineTMsgList.size()) {
    //            S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_FLEET_LINETMsg().getTableName(), invNum));
    //            rollback();
    //            termCd = TERM_CD.ABNORMAL_END;
    //            return false;
    //        }
    //    }
    //    return true;
    //}
    // QC#52209 2019/07/31 Mod End
    // QC#52209 2019/07/31 Del Start
    //private NWCB010001InvPrtFleetLineBean getRentalOriginal(NWCB010001InvPrtFleetLineBean bean, List<NWCB010001InvPrtFleetLineBean> list) {
    //    NWCB010001InvPrtFleetLineBean tmp = null;
    //    for (NWCB010001InvPrtFleetLineBean data : list) {
    //        if (data.getSvcMachMstrPk().compareTo(bean.getSvcMachMstrPk()) == 0) {
    //            if (S21StringUtil.isEquals(DS_CONTR_CRAT_TP.SHELL, data.getDsContrCratTpCd())) {
    //                return data;
    //            }
    //            if (tmp == null) {
    //                tmp = data;
    //            } else {
    //                if (tmp.getDsContrDtlPk().compareTo(data.getDsContrDtlPk()) < 0) {
    //                    tmp = data;
    //                }
    //            }
    //        }
    //    }
    //    return tmp;
    //}
    // QC#30346 2019/02/18 Add End
    // QC#52209 2019/07/31 Del End

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getFleetSubTotal(String invNum, String taxDtlFlg, String fleetFlg) { // QC#29371
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        ssmParam.put("taxDtlFlg", taxDtlFlg);
        ssmParam.put("fleetFlg", fleetFlg); // QC#29371
        ssmParam.put("dsSlsTaxTpCd01", DS_SLS_TAX_TP.STATE_TAX);
        ssmParam.put("dsSlsTaxTpCd02", DS_SLS_TAX_TP.COUNTY_TAX);
        ssmParam.put("dsSlsTaxTpCd03", DS_SLS_TAX_TP.CITY_TAX);
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("qtyFormat", FM_QTY2);
        ssmParam.put("amtFormat", FM_AMT);
        ssmParam.put("siChrgTpCdBC", SVC_INV_CHRG_TP.BASE_CHARGE);
        ssmParam.put("svcInvLineTpAddl", SVC_INV_LINE_TP.ADDITONAL_CHARGE); // QC#29371
        // 02/06/2019 QC#29371 SRNO8 Add Start
        ssmParam.put("svcInvMergeTpIntoBaseCharge", SVC_INV_MERGE_TP.MERGE_INTO_BASE_CHARGE);
        // 02/06/2019 QC#29371 SRNO8 Add End

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFleetSubTotal", ssmParam);
    }

    private boolean setFleet(List<Map<String, Object>> resultMapList, ResultSet rs, Map<String, Object> preConslBillInfoForFleetMap) throws SQLException, NWCB010001BusinessException {

        boolean resultFlg = true;
        String invNum = rs.getString(INV_NUM);
        String invTpCd = rs.getString(INV_TP_CD);

        INV_PRT_FLEET_SUB_TOTTMsg invPrtFleetSubTotTmsg;
        List<INV_PRT_FLEET_SUB_TOTTMsg> invPrtFleetSubTotList = new ArrayList<INV_PRT_FLEET_SUB_TOTTMsg>();

        for (Map<String, Object> resultMap : resultMapList) {

            invPrtFleetSubTotTmsg = new INV_PRT_FLEET_SUB_TOTTMsg();
            String svcPgm = null;

            StringBuilder sbSvcPgm = new StringBuilder();
            sbSvcPgm.append(nullConvEmp((String) resultMap.get(SVC_PGM_NM))).append(" - ").append(DS_CONTR_CATG.FLEET).append("_").append(
                    resultMap.get(CONTR_NO));
            BigDecimal baseCount = (BigDecimal) resultMap.get(BASE_CHARGE_COUNT);
            if (ZYPCommonFunc.hasValue(baseCount) && BigDecimal.ZERO.compareTo(baseCount) != 0 
                && resultMap.get(BASE_COPY_QTY) != null && !"0".equals((String) resultMap.get(BASE_COPY_QTY))) {
                sbSvcPgm.append(ENTER).append(SVC_MESSAGE1).append((String) resultMap.get(BASE_COPY_QTY)).append(SVC_MESSAGE3);
            }
            svcPgm = sbSvcPgm.toString();

            BigDecimal stateTax = setNegate((BigDecimal) resultMap.get(STATE_TAX), invTpCd);
            BigDecimal countyTax = setNegate((BigDecimal) resultMap.get(COUNTY_TAX), invTpCd);
            BigDecimal cityTax = setNegate((BigDecimal) resultMap.get(CITY_TAX), invTpCd);
            BigDecimal totalTax = setNegate((BigDecimal) resultMap.get(TOTAL_TAX), invTpCd);
            BigDecimal totalAmt = setNegate((BigDecimal) resultMap.get(TOTAL_AMT), invTpCd);
            String stateTaxTxt = setNegate((String) resultMap.get(STATE_TAX_TXT), invTpCd);
            String countyTaxTxt = setNegate((String) resultMap.get(COUNTY_TAX_TXT), invTpCd);
            String cityTaxTxt = setNegate((String) resultMap.get(CITY_TAX_TXT), invTpCd);
            String totalTaxTxt = setNegate((String) resultMap.get(TOTAL_TAX_TXT), invTpCd);
            String totalAmtTxt = setNegate((String) resultMap.get(TOTAL_AMT_TXT), invTpCd);

            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invPrtFleetSubTotPk, ZYPOracleSeqAccessor
                    .getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_FLEET_SUB_TOT_SQ));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invNum, invNum);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invPrtDtlFmtTpCd, INV_PRT_DTL_FMT_TP.FLEET_CONTRACT);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.dsContrPk, (BigDecimal) resultMap.get(CONTR_PK));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.dsContrNum, (String) resultMap.get(CONTR_NO));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.custIssPoNum, (String) resultMap.get(PO_NO));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.svcPgmNm, (String) resultMap.get(SVC_PGM_CD));//Temp Search Key

            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invStTaxAmtTxt, stateTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invCntyTaxAmtTxt, countyTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invCityTaxAmtTxt, cityTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invTotTaxAmtTxt, totalTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invSubTotAmtTxt, totalAmtTxt);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invStTaxAmt, stateTax);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invCntyTaxAmt, countyTax);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invCityTaxAmt, cityTax);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invTotTaxAmt, totalTax);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invSubTotAmt, totalAmt);

            if (preConslBillInfoForFleetMap != null && !preConslBillInfoForFleetMap.isEmpty()) {
                //Store Previous Consolidated Bill
                StringBuffer key = new StringBuffer(INV_PRT_FLEET_SUB_TOT);
                if ((BigDecimal) resultMap.get(CONTR_PK) != null) {
                    key.append(((BigDecimal) resultMap.get(CONTR_PK)).toString());
                }
                if (hasValue(svcPgm)) {
                    key.append(svcPgm);
                }
                if (hasValue((String) resultMap.get(PO_NO))) {
                    key.append((String) resultMap.get(PO_NO));
                }
                if (preConslBillInfoForFleetMap.get(CONSL_BILL_NUM) != null) {
                    ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.conslBillNum, (String) preConslBillInfoForFleetMap.get(CONSL_BILL_NUM));
                }
                if (preConslBillInfoForFleetMap.get(key.toString()) != null) {
                    ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.billFeetSubTotSq, (BigDecimal) preConslBillInfoForFleetMap.get(key.toString()));
                }
            }
            
            // set FLEET CHARGE DETAIL INFO
            resultFlg = setFleetChrgDtl(invPrtFleetSubTotTmsg, invTpCd, ZYPConstant.FLG_ON_Y); // QC#29371
            if (!resultFlg) {
                return false;
            }
            String meterFlag = rs.getString(AR_METER_FLG);

            // set FLEET METER CHARGE INFO
            resultFlg = setFleetMtrChrg(invPrtFleetSubTotTmsg, meterFlag, ZYPConstant.FLG_ON_Y); // QC#29371
            if (!resultFlg) {
                return false;
            }
            // set FLEET BASE CHARGE
            resultFlg = setFleetBaseChrg(invPrtFleetSubTotTmsg, ZYPConstant.FLG_ON_Y); // QC#29371
            if (!resultFlg) {
                return false;
            }
            // set FLEET Summary Meter
            resultFlg = setFleetSmry(invPrtFleetSubTotTmsg, invTpCd, meterFlag, ZYPConstant.FLG_ON_Y); // QC#29371
            if (!resultFlg) {
                return false;
            }

            // QC#13358 mod Start
//            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.svcPgmNm, svcPgm);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.svcPgmNm, (String) resultMap.get("INV_PRINT_COV_TXT"));
            // QC#13358 mod End
            invPrtFleetSubTotList.add(invPrtFleetSubTotTmsg);

        }
        int result = EZDFastTBLAccessor.insert(invPrtFleetSubTotList.toArray(new INV_PRT_FLEET_SUB_TOTTMsg[invPrtFleetSubTotList
                .size()]));
        if (result != invPrtFleetSubTotList.size()) {
            S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_FLEET_SUB_TOTTMsg().getTableName(), invNum));
            return false;
        }
//        normCnt += result;
        return true;
    }

    private boolean setFleetChrgDtl(INV_PRT_FLEET_SUB_TOTTMsg invPrtFleetSubTotTmsg, String invTpCd, String fleetFlg) {

        // GET Fleet ChrgDtl
        List<Map<String, Object>> ssmFleetResult = getFleetChrgDtl(invPrtFleetSubTotTmsg, fleetFlg); // QC#29371
        if (ssmFleetResult.size() == 0) {
            return true;
        }
        // INSERT INV_PRT_FLEET_CHG_DTL
        boolean result = insertFleetChrgDtl(ssmFleetResult, invTpCd, invPrtFleetSubTotTmsg, fleetFlg); // QC#29371
        if (!result) {
            return false;
        }
        return true;
    }

    // QC#29371
    private boolean setFleetChrgDtlForAgg(INV_PRT_FLEET_SUB_TOTTMsg invPrtFleetSubTotTmsg, String invTpCd, String fleetFlg) {

        // GET Agg ChrgDtl
        List<Map<String, Object>> ssmFleetResult = getFleetChrgDtlForAgg(invPrtFleetSubTotTmsg, fleetFlg);
        if (ssmFleetResult.size() == 0) {
            return true;
        }
        // INSERT INV_PRT_FLEET_CHG_DTL
        boolean result = insertFleetChrgDtl(ssmFleetResult, invTpCd, invPrtFleetSubTotTmsg, fleetFlg);
        if (!result) {
            return false;
        }
        return true;
    }

    // QC#29371
    private List<Map<String, Object>> getFleetChrgDtlForAgg(INV_PRT_FLEET_SUB_TOTTMsg paramTmsg, String fleetFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", paramTmsg.invNum.getValue());
        ssmParam.put("invNum", paramTmsg.invNum.getValue());
        ssmParam.put("fleetFlg", fleetFlg);
        ssmParam.put("contrPk", paramTmsg.dsContrPk.getValue());
        ssmParam.put("svcPgm", paramTmsg.svcPgmNm.getValue());
        ssmParam.put("poNo", paramTmsg.custIssPoNum.getValue());
        ssmParam.put("printDtlFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("siChrgTpCdBC", SVC_INV_CHRG_TP.BASE_CHARGE);
        ssmParam.put("siChrgTpCdAC", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        ssmParam.put("siChrgTpCdMC", SVC_INV_CHRG_TP.METER_CHARGE);
        ssmParam.put("siChrgTpNmBase", BASE);
        ssmParam.put("siChrgTpNmUsage", USAGE);
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("amtFormat", FM_AMT);
        // 02/06/2019 QC#29371 SRNO8 Add Start
        ssmParam.put("svcInvMergeTpIntoBaseCharge", SVC_INV_MERGE_TP.MERGE_INTO_BASE_CHARGE);
        // 02/06/2019 QC#29371 SRNO8 Add End

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFleetChrgDtlForAgg", ssmParam);
    }

    private boolean setFleetMtrChrg(INV_PRT_FLEET_SUB_TOTTMsg invPrtFleetSubTotTmsg, String meterFlg, String fleetFlg) { // QC#29371

        // GET Fleet MtrDtl
        List<Map<String, Object>> ssmFleetResult = getFleetMtrChrg(invPrtFleetSubTotTmsg, meterFlg, fleetFlg); // QC#29371
        if (ssmFleetResult.size() == 0) {
            return true;
        }
        // INSERT INV_PRT_FLEET_LOC, _MACH, _MTR
        boolean result = insertFleetMtrChrg(ssmFleetResult, invPrtFleetSubTotTmsg);
        if (!result) {
            return false;
        }

        return true;
    }

    private boolean setFleetBaseChrg(INV_PRT_FLEET_SUB_TOTTMsg invPrtFleetSubTotTmsg, String fleetFlg) { // QC#29371

        // GET Fleet BaseDtl
        List<Map<String, Object>> ssmFleetResult = getFleetBaseChrg(invPrtFleetSubTotTmsg, fleetFlg); // QC#29371
        if (ssmFleetResult.size() == 0) {
            return true;
        }
        // INSERT INV_PRT_FLEET_LOC, _MACH
        boolean result = insertFleetBaseChrg(ssmFleetResult, invPrtFleetSubTotTmsg);
        if (!result) {
            return false;
        }

        return true;
    }

    private boolean setFleetSmry(INV_PRT_FLEET_SUB_TOTTMsg invPrtFleetSubTotTmsg, String invTpCd, String meterFlg, String fleetFlg) { // QC#29371

        // GET Fleet BaseDtl
        List<Map<String, Object>> ssmFleetResult = getFleetSmryMtr(invPrtFleetSubTotTmsg, meterFlg, fleetFlg); // QC#29371
        if (ssmFleetResult.size() == 0) {
            return true;
        }
        // INSERT INV_PRT_FLEET_LOC, _MACH
        boolean result = insertFleetSmryMtr(ssmFleetResult, invTpCd, invPrtFleetSubTotTmsg, meterFlg, fleetFlg); // QC#30907 2019/03/28 Mod
        if (!result) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getFleetChrgDtl(INV_PRT_FLEET_SUB_TOTTMsg paramTmsg, String fleetFlg) { // QC#29371
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", paramTmsg.invNum.getValue());
        ssmParam.put("invNum", paramTmsg.invNum.getValue());
        ssmParam.put("fleetFlg", fleetFlg); // QC#29371
        ssmParam.put("contrPk", paramTmsg.dsContrPk.getValue());
        ssmParam.put("svcPgm", paramTmsg.svcPgmNm.getValue());
        ssmParam.put("poNo", paramTmsg.custIssPoNum.getValue());
        ssmParam.put("printDtlFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("siChrgTpCdBC", SVC_INV_CHRG_TP.BASE_CHARGE);
        ssmParam.put("siChrgTpCdAC", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        ssmParam.put("siChrgTpCdMC", SVC_INV_CHRG_TP.METER_CHARGE);
        ssmParam.put("siChrgTpNmBase", BASE);
        ssmParam.put("siChrgTpNmUsage", USAGE);
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("amtFormat", FM_AMT);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFleetChrgDtl", ssmParam);
    }

        
    @SuppressWarnings("unchecked")
    private Map<String, Object> getFleetChrgDtlForAdditionalChargeNonDisp(//
            INV_PRT_FLEET_SUB_TOTTMsg paramTmsg, String periodFrom, String periodThru, String addlChrgInvTpCd, String fleetFlg, BigDecimal svcMachMstrPk) { // QC#29371
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", paramTmsg.invNum.getValue());
        ssmParam.put("invNum", paramTmsg.invNum.getValue());
        ssmParam.put("fleetFlg", fleetFlg); // QC#29371
        ssmParam.put("contrPk", paramTmsg.dsContrPk.getValue());
        ssmParam.put("svcPgm", paramTmsg.svcPgmNm.getValue());
        ssmParam.put("poNo", paramTmsg.custIssPoNum.getValue());
        ssmParam.put("printDtlFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("siChrgTpCdBC", SVC_INV_CHRG_TP.BASE_CHARGE);
        ssmParam.put("siChrgTpCdAC", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        ssmParam.put("siChrgTpCdMC", SVC_INV_CHRG_TP.METER_CHARGE);
        ssmParam.put("siChrgTpNmBase", BASE);
        ssmParam.put("siChrgTpNmUsage", USAGE);
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("amtFormat", FM_AMT);
        ssmParam.put("addlChrgInvTpCd", addlChrgInvTpCd);
        ssmParam.put("periodFrom", periodFrom);
        ssmParam.put("periodThru", periodThru);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk); // QC#30345
        return (Map<String, Object>) ssmBatchClient.queryObject("getFleetChrgDtlForAdditionalChargeNonDisp", ssmParam);
    }
    /**
     * @param ssmFleetResult S21SsmEZDResult
     * @param invTpCd String
     * @param fleetFlg String
     * @param subTotPk BigDecimal
     * @return boolean
     */
    private boolean insertFleetChrgDtl(List<Map<String, Object>> resultMapList, String invTpCd, INV_PRT_FLEET_SUB_TOTTMsg invPrtFleetSubTotTmsg, String fleetFlg) { // QC#29371

        List<INV_PRT_FLEET_CHRG_DTLTMsg> invPrtFleetChgDtlList = new ArrayList<INV_PRT_FLEET_CHRG_DTLTMsg>();
        for (Map<String, Object> resultMap : resultMapList) {

            INV_PRT_FLEET_CHRG_DTLTMsg invPrtFleetChgDtlTmsg = new INV_PRT_FLEET_CHRG_DTLTMsg();
            String fromDt = getDtTxt((String) resultMap.get(PERIOD_FROM));
            String thruDt = getDtTxt((String) resultMap.get(PERIOD_THRU));
            String totalAmtTxt = setNegate((String) resultMap.get(TOTAL_AMT_TXT), invTpCd);

            //QC#25910
            if (SVC_INV_CHRG_TP.BASE_CHARGE.equals((String) resultMap.get("SVC_INV_CHRG_TP_CD"))) {
                Map<String, Object> addChrgNonDispMap //
                = getFleetChrgDtlForAdditionalChargeNonDisp(invPrtFleetSubTotTmsg //
                        , (String) resultMap.get(PERIOD_FROM), (String) resultMap.get(PERIOD_THRU) //
                        , ADDL_CHRG_INV_TP.BASE, fleetFlg //
                        , (BigDecimal) resultMap.get(SVC_MACH_MSTR_PK)); // QC#30345
                BigDecimal baseTotAmt = BigDecimal.ZERO;
                if (resultMap.get("AMOUNT") != null) {
                    baseTotAmt = (BigDecimal) resultMap.get("AMOUNT");
                }
                BigDecimal addChrgNonDispAmt = BigDecimal.ZERO;
                if (addChrgNonDispMap != null && addChrgNonDispMap.get("AMOUNT") != null) {
                    addChrgNonDispAmt = (BigDecimal) addChrgNonDispMap.get("AMOUNT");
                    baseTotAmt = baseTotAmt.add(addChrgNonDispAmt);
                    totalAmtTxt = fmtAmtFromNumToStrInvTp(baseTotAmt, invTpCd, (String) resultMap.get(CCY_SGN_TXT));
                }
            }
            
            ZYPEZDItemValueSetter.setValue(invPrtFleetChgDtlTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invPrtFleetChgDtlTmsg.invPrtFleetChrgDtlPk, ZYPOracleSeqAccessor
                    .getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_FLEET_CHRG_DTL_SQ));
            ZYPEZDItemValueSetter.setValue(invPrtFleetChgDtlTmsg.invPrtFleetSubTotPk, invPrtFleetSubTotTmsg.invPrtFleetSubTotPk);
            ZYPEZDItemValueSetter.setValue(invPrtFleetChgDtlTmsg.invNum, (String) resultMap.get(INV_NUM));
            ZYPEZDItemValueSetter.setValue(invPrtFleetChgDtlTmsg.invChrgTpNm, (String) resultMap.get(CHARGE_TYPE));
            ZYPEZDItemValueSetter.setValue(invPrtFleetChgDtlTmsg.bllgPerFromDtTxt, fromDt);
            ZYPEZDItemValueSetter.setValue(invPrtFleetChgDtlTmsg.bllgPerThruDtTxt, thruDt);
            ZYPEZDItemValueSetter.setValue(invPrtFleetChgDtlTmsg.lineTotAmtTxt, totalAmtTxt);
            ZYPEZDItemValueSetter.setValue(invPrtFleetChgDtlTmsg.dplySpclChrgFlg, (String) resultMap.get(SPECIAL_CHARGE_FLG));

            if (hasValue(invPrtFleetSubTotTmsg.billFeetSubTotSq)) {
                ZYPEZDItemValueSetter.setValue(invPrtFleetChgDtlTmsg.billFeetSubTotSq, invPrtFleetSubTotTmsg.billFeetSubTotSq);
            }
            // QC#29371
            if(ZYPConstant.FLG_OFF_N.equals(fleetFlg)){
                ZYPEZDItemValueSetter.setValue(invPrtFleetChgDtlTmsg.billFeetSubTotSq, (BigDecimal) resultMap.get(SVC_MACH_MSTR_PK));
            }

            invPrtFleetChgDtlList.add(invPrtFleetChgDtlTmsg);
        }

        if (invPrtFleetChgDtlList.size() > 0) {
            // INSERT
            int result = EZDFastTBLAccessor.insert(invPrtFleetChgDtlList
                    .toArray(new INV_PRT_FLEET_CHRG_DTLTMsg[invPrtFleetChgDtlList.size()]));
            if (result != invPrtFleetChgDtlList.size()) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_FLEET_CHRG_DTLTMsg().getTableName(), String.valueOf(invPrtFleetSubTotTmsg.invPrtFleetSubTotPk.getValue())));
                rollback();
                termCd = TERM_CD.ABNORMAL_END;
                return false;
            }
//            normCnt += result;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getFleetMtrChrg(INV_PRT_FLEET_SUB_TOTTMsg paramTmsg, String meterFlg, String fleetFlg) { // QC#29371
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", paramTmsg.invNum.getValue());
        ssmParam.put("fleetFlg", fleetFlg); // QC#29371
        ssmParam.put("contrPk", paramTmsg.dsContrPk.getValue());
        ssmParam.put("svcPgm", paramTmsg.svcPgmNm.getValue());
        ssmParam.put("poNo", paramTmsg.custIssPoNum.getValue());
        ssmParam.put("siChrgTpCdMC", SVC_INV_CHRG_TP.METER_CHARGE);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("meterFlg", meterFlg);
        ssmParam.put("qtyFormat", FM_QTY);

        // 06/04/2019 QC#50658 Add Start
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        // 06/04/2019 QC#50658 Add End

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFleetMtrChrg", ssmParam);
    }

    /**
     * @param ssmFleetResult S21SsmEZDResult
     * @param subTotPk BigDecimal
     * @return boolean
     */
    private boolean insertFleetMtrChrg(List<Map<String, Object>> resultMapList, INV_PRT_FLEET_SUB_TOTTMsg invPrtFleetSubTotTmsg) {

        INV_PRT_FLEET_LOCTMsg invPrtFleetLocTmsg;
        INV_PRT_FLEET_MACHTMsg invPrtFleetMachTmsg;
        INV_PRT_FLEET_MTRTMsg invPrtFleetMtrTmsg;
        List<INV_PRT_FLEET_LOCTMsg> invPrtFleetLocList = new ArrayList<INV_PRT_FLEET_LOCTMsg>();
        List<INV_PRT_FLEET_MACHTMsg> invPrtFleetMachList = new ArrayList<INV_PRT_FLEET_MACHTMsg>();
        List<INV_PRT_FLEET_MTRTMsg> invPrtFleetMtrList = new ArrayList<INV_PRT_FLEET_MTRTMsg>();

        //SHIP_TO_CODE, SHIP_TO_NAME, SHIP_TO_FST_ADDR, SHIP_TO_SCD_ADDR, SHIP_TO_CITY, SHIP_TO_ST, SHIP_TO_POST
        String locationKey = "";
        String preLocationKey = "";
        BigDecimal locPk = null;
        //MACHINE_PK
        String machineKey = "";
        String preMachineKey = "";
        BigDecimal machPk = null;
        for (Map<String, Object> resultMap : resultMapList) {

            locationKey = nullConvEmp((String) resultMap.get(SHIP_TO_CODE)) 
                        + nullConvEmp((String) resultMap.get(SHIP_TO_NAME))
                        + nullConvEmp((String) resultMap.get(SHIP_TO_FST_ADDR))
                        + nullConvEmp((String) resultMap.get(SHIP_TO_SCD_ADDR))
                        + nullConvEmp((String) resultMap.get(SHIP_TO_ST))
                        + nullConvEmp((String) resultMap.get(SHIP_TO_POST))
                        ;
            machineKey = repString((BigDecimal) resultMap.get(MACHINE_PK));
                
            if (!preLocationKey.equals(locationKey)) {
                // SET INV_PRT_FLEET_LOC
                invPrtFleetLocTmsg = new INV_PRT_FLEET_LOCTMsg();
                locPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_FLEET_LOC_SQ);
    
                ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.invPrtFleetLocPk, locPk);
                ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.invPrtFleetSubTotPk, invPrtFleetSubTotTmsg.invPrtFleetSubTotPk);
                // QC#20788 add Start
                ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToCustAcctCd, (String) resultMap.get(SHIP_TO_CUST_ACCT_CD));
                ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToCustAcctNm, (String) resultMap.get(SHIP_TO_CUST_ACCT_NM));
                // QC#20788 add End
                ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToCustCd, (String) resultMap.get(SHIP_TO_CODE));
                ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToLocNm, (String) resultMap.get(SHIP_TO_NAME));
                ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToFirstLineAddr, (String) resultMap.get(SHIP_TO_FST_ADDR));
                ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToScdLinePrntAddr, (String) resultMap.get(SHIP_TO_SCD_ADDR));
                ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToCtyAddr, (String) resultMap.get(SHIP_TO_CITY));
                ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToStCd, (String) resultMap.get(SHIP_TO_ST));
                ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToPostCd, (String) resultMap.get(SHIP_TO_POST));
                ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.invNum, (String) resultMap.get(INV_NUM));
                if (hasValue(invPrtFleetSubTotTmsg.billFeetSubTotSq)) {
                    ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.billFeetSubTotSq, invPrtFleetSubTotTmsg.billFeetSubTotSq);
                }

                invPrtFleetLocList.add(invPrtFleetLocTmsg);
                
                // SET INV_PRT_FLEET_MACH
                invPrtFleetMachTmsg = new INV_PRT_FLEET_MACHTMsg();
                machPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_FLEET_MACH_SQ);

                ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.invPrtFleetMachPk, machPk);
                ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.invPrtFleetLocPk, locPk);
                ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.svcMachMstrPk, (BigDecimal) resultMap.get(MACHINE_PK));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.invPrtSerNumTxt, (String) resultMap.get(SERIAL_NO));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.mdlNm, (String) resultMap.get(MODEL));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.firstBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_1));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.scdBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_2));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.thirdBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_3));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.frthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_4));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.fifthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_5));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.sixthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_6));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.invNum, (String) resultMap.get(INV_NUM));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.custIssPoNum, (String) resultMap.get(CUST_ISS_PO_NUM)); // QC#29371

                invPrtFleetMachList.add(invPrtFleetMachTmsg);
                
                // SET INV_PRT_FLEET_MTR
                invPrtFleetMtrTmsg = new INV_PRT_FLEET_MTRTMsg();

                ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.invPrtFleetMtrPk, ZYPOracleSeqAccessor
                        .getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_FLEET_MTR_SQ));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.invPrtFleetMachPk, machPk);
                ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.invNum, (String) resultMap.get(INV_NUM));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.mtrLbNm, cutOffString((String) resultMap.get(METER_TYPE), MTR_LB_CUT_OFF_LEN));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.prevTotCopyQtyTxt, (String) resultMap.get(START_READ));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.totCopyQtyTxt, (String) resultMap.get(END_READ));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.testCopyQtyTxt, (String) resultMap.get(TEST_COPIES));
                ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.bllgCopyQtyTxt, (String) resultMap.get(COPIES_MADE));

                invPrtFleetMtrList.add(invPrtFleetMtrTmsg);
                
            } else {
                if (!preMachineKey.equals(machineKey)) {
                    // SET INV_PRT_FLEET_MACH
                    invPrtFleetMachTmsg = new INV_PRT_FLEET_MACHTMsg();
                    machPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_FLEET_MACH_SQ);

                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.invPrtFleetMachPk, machPk);
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.invPrtFleetLocPk, locPk);
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.svcMachMstrPk, (BigDecimal) resultMap.get(MACHINE_PK));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.invPrtSerNumTxt, (String) resultMap.get(SERIAL_NO));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.mdlNm, (String) resultMap.get(MODEL));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.firstBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_1));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.scdBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_2));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.thirdBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_3));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.frthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_4));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.fifthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_5));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.sixthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_6));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.invNum, (String) resultMap.get(INV_NUM));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.custIssPoNum, (String) resultMap.get(CUST_ISS_PO_NUM)); // QC#51829 2019/07/20 Add Start

                    invPrtFleetMachList.add(invPrtFleetMachTmsg);
                    
                    
                    // SET INV_PRT_FLEET_MTR
                    invPrtFleetMtrTmsg = new INV_PRT_FLEET_MTRTMsg();

                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.invPrtFleetMtrPk, ZYPOracleSeqAccessor
                            .getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_FLEET_MTR_SQ)); // QC#29371
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.invPrtFleetMachPk, machPk);
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.invNum, (String) resultMap.get(INV_NUM));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.mtrLbNm, cutOffString((String) resultMap.get(METER_TYPE), MTR_LB_CUT_OFF_LEN));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.prevTotCopyQtyTxt, (String) resultMap.get(START_READ));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.totCopyQtyTxt, (String) resultMap.get(END_READ));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.testCopyQtyTxt, (String) resultMap.get(TEST_COPIES));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.bllgCopyQtyTxt, (String) resultMap.get(COPIES_MADE));

                    invPrtFleetMtrList.add(invPrtFleetMtrTmsg);
                    
                } else {
                    // SET INV_PRT_FLEET_MTR
                    invPrtFleetMtrTmsg = new INV_PRT_FLEET_MTRTMsg();

                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.invPrtFleetMtrPk, ZYPOracleSeqAccessor
                            .getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_FLEET_MTR_SQ)); // QC#29371
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.invPrtFleetMachPk, machPk);
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.invNum, (String) resultMap.get(INV_NUM));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.mtrLbNm, cutOffString((String) resultMap.get(METER_TYPE), MTR_LB_CUT_OFF_LEN));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.prevTotCopyQtyTxt, (String) resultMap.get(START_READ));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.totCopyQtyTxt, (String) resultMap.get(END_READ));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.testCopyQtyTxt, (String) resultMap.get(TEST_COPIES));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMtrTmsg.bllgCopyQtyTxt, (String) resultMap.get(COPIES_MADE));

                    invPrtFleetMtrList.add(invPrtFleetMtrTmsg);
                    
                }
            }

            preLocationKey = locationKey;
            preMachineKey = machineKey;
        }

        if (invPrtFleetLocList.size() > 0 && invPrtFleetMachList.size() > 0 && invPrtFleetMtrList.size() > 0) {
            // INSERT
            int result = EZDFastTBLAccessor.insert(invPrtFleetLocList
                    .toArray(new INV_PRT_FLEET_LOCTMsg[invPrtFleetLocList.size()]));
            if (result != invPrtFleetLocList.size()) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_FLEET_LOCTMsg().getTableName(), String.valueOf(invPrtFleetSubTotTmsg.invPrtFleetSubTotPk.getValue())));
                return false;
            }
//            normCnt += result;
            result = EZDFastTBLAccessor.insert(invPrtFleetMachList
                    .toArray(new INV_PRT_FLEET_MACHTMsg[invPrtFleetMachList.size()]));
            if (result != invPrtFleetMachList.size()) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_FLEET_MACHTMsg().getTableName(), String.valueOf(invPrtFleetSubTotTmsg.invPrtFleetSubTotPk.getValue())));
                return false;
            }
//            normCnt += result;
            result = EZDFastTBLAccessor.insert(invPrtFleetMtrList.toArray(new INV_PRT_FLEET_MTRTMsg[invPrtFleetMtrList.size()]));
            if (result != invPrtFleetMtrList.size()) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_FLEET_MTRTMsg().getTableName(), String.valueOf(invPrtFleetSubTotTmsg.invPrtFleetSubTotPk.getValue())));
                return false;
            }
//            normCnt += result;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getFleetBaseChrg(INV_PRT_FLEET_SUB_TOTTMsg paramTmsg, String fleetFlg) { // QC#29371
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", paramTmsg.invNum.getValue());
        ssmParam.put("fleetFlg", fleetFlg); // QC#29371
        ssmParam.put("contrPk", paramTmsg.dsContrPk.getValue());
        ssmParam.put("svcPgm", paramTmsg.svcPgmNm.getValue());
        ssmParam.put("poNo", paramTmsg.custIssPoNum.getValue());
        ssmParam.put("siChrgTpCdBC", SVC_INV_CHRG_TP.BASE_CHARGE);
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("invPrtFreetSubTotPk", paramTmsg.invPrtFleetSubTotPk.getValue());

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFleetBaseChrg", ssmParam);
    }

    private boolean insertFleetBaseChrg(List<Map<String, Object>> resultMapList, INV_PRT_FLEET_SUB_TOTTMsg invPrtFleetSubTotTmsg) {

        INV_PRT_FLEET_LOCTMsg invPrtFleetLocTmsg;
        INV_PRT_FLEET_MACHTMsg invPrtFleetMachTmsg;
        List<INV_PRT_FLEET_LOCTMsg> invPrtFleetLocList = new ArrayList<INV_PRT_FLEET_LOCTMsg>();
        List<INV_PRT_FLEET_MACHTMsg> invPrtFleetMachList = new ArrayList<INV_PRT_FLEET_MACHTMsg>();

        BigDecimal locPk = null;
        String locationKey = "";
        String preLocationKey = "";
        String machineKey = "";
        String preMachineKey = "";
        for (Map<String, Object> resultMap : resultMapList) {

            locationKey = nullConvEmp((String) resultMap.get(SHIP_TO_CODE)) 
            + nullConvEmp((String) resultMap.get(SHIP_TO_NAME))
            + nullConvEmp((String) resultMap.get(SHIP_TO_FST_ADDR))
            + nullConvEmp((String) resultMap.get(SHIP_TO_SCD_ADDR))
            + nullConvEmp((String) resultMap.get(SHIP_TO_ST))
            + nullConvEmp((String) resultMap.get(SHIP_TO_POST))
            ;
            machineKey = repString((BigDecimal) resultMap.get(MACHINE_PK));
            
            BigDecimal locCnt = (BigDecimal) resultMap.get(LOC_CNT);
            if (locCnt == null) locCnt = BigDecimal.ZERO;
            BigDecimal machCnt = (BigDecimal) resultMap.get(MACH_CNT);
            if (machCnt == null) machCnt = BigDecimal.ZERO;

            // 2019/02/07 QC#29371 K.Fujimoto Mod Start
            // if (locCnt.intValue() == 0 && machCnt.intValue() == 0) {
            // if (locCnt.intValue() == 0 ) {
            //    if (!preLocationKey.equals(locationKey)) {
            if (locCnt.intValue() == 0 && !preLocationKey.equals(locationKey)) {
                    // SET INV_PRT_FLEET_LOC
                    invPrtFleetLocTmsg = new INV_PRT_FLEET_LOCTMsg();
                    locPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_FLEET_LOC_SQ);
    
                    ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.invPrtFleetLocPk, locPk);
                    ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.invPrtFleetSubTotPk, invPrtFleetSubTotTmsg.invPrtFleetSubTotPk);
                    // QC#20788 add Start
                    ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToCustAcctCd, (String) resultMap.get(SHIP_TO_CUST_ACCT_CD));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToCustAcctNm, (String) resultMap.get(SHIP_TO_CUST_ACCT_NM));
                    // QC#20788 add End
                    ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToCustCd, (String) resultMap.get(SHIP_TO_CODE));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToLocNm, (String) resultMap.get(SHIP_TO_NAME));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToFirstLineAddr, (String) resultMap.get(SHIP_TO_FST_ADDR));
                    ZYPEZDItemValueSetter
                            .setValue(invPrtFleetLocTmsg.shipToScdLinePrntAddr, (String) resultMap.get(SHIP_TO_SCD_ADDR));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToCtyAddr, (String) resultMap.get(SHIP_TO_CITY));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToStCd, (String) resultMap.get(SHIP_TO_ST));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.shipToPostCd, (String) resultMap.get(SHIP_TO_POST));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.invNum, (String) resultMap.get(INV_NUM));
                    if (hasValue(invPrtFleetSubTotTmsg.billFeetSubTotSq)) {
                        ZYPEZDItemValueSetter.setValue(invPrtFleetLocTmsg.billFeetSubTotSq, invPrtFleetSubTotTmsg.billFeetSubTotSq);
                    }
                    invPrtFleetLocList.add(invPrtFleetLocTmsg);
            //    }
            }
            
            // if (machCnt.intValue() == 0) {
            //    if (!preMachineKey.equals(machineKey)) {
            if (machCnt.intValue() == 0 && !preMachineKey.equals(machineKey) && existsInvPrtFleetChrgDtl((String) resultMap.get(INV_NUM), (BigDecimal) resultMap.get(MACHINE_PK) ) ) {
                    BigDecimal existLocPk = getInvPrtFleetLocPk((String) resultMap.get(INV_NUM), (String) resultMap.get(SHIP_TO_CODE), invPrtFleetSubTotTmsg.invPrtFleetSubTotPk.getValue());
                    if (hasValue(existLocPk)) {
                        locPk = existLocPk;
                    }
                    // SET INV_PRT_FLEET_MACH
                    invPrtFleetMachTmsg = new INV_PRT_FLEET_MACHTMsg();
                    BigDecimal machPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_FLEET_MACH_SQ);
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.invPrtFleetMachPk, machPk);
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.invPrtFleetLocPk, locPk);
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.svcMachMstrPk, (BigDecimal) resultMap.get(MACHINE_PK));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.invPrtSerNumTxt, (String) resultMap.get(SERIAL_NO));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.mdlNm, (String) resultMap.get(MODEL));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.firstBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_1));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.scdBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_2));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.thirdBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_3));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.frthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_4));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.fifthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_5));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.sixthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_6));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.invNum, (String) resultMap.get(INV_NUM));
                    ZYPEZDItemValueSetter.setValue(invPrtFleetMachTmsg.custIssPoNum, (String) resultMap.get(CUST_ISS_PO_NUM)); // QC#51829 2019/07/20 Add Start

                    invPrtFleetMachList.add(invPrtFleetMachTmsg);
            //    }
            }
            preLocationKey = locationKey;
            preMachineKey = machineKey;
            //locPk = null;
        }

        if (invPrtFleetLocList.size() > 0) {
            // INSERT
            int result = EZDFastTBLAccessor.insert(invPrtFleetLocList
                    .toArray(new INV_PRT_FLEET_LOCTMsg[invPrtFleetLocList.size()]));
            if (result != invPrtFleetLocList.size()) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_FLEET_LOCTMsg().getTableName(), String.valueOf(invPrtFleetSubTotTmsg.invPrtFleetSubTotPk.getValue())));
                return false;
            }
        }
        if (invPrtFleetMachList.size() > 0) {
//            normCnt += result;
            int result = EZDFastTBLAccessor.insert(invPrtFleetMachList
                    .toArray(new INV_PRT_FLEET_MACHTMsg[invPrtFleetMachList.size()]));
            if (result != invPrtFleetMachList.size()) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_FLEET_MACHTMsg().getTableName(), String.valueOf(invPrtFleetSubTotTmsg.invPrtFleetSubTotPk.getValue())));
                return false;
            }
//            normCnt += result;
        }
        // 2019/02/07 QC#29371 K.Fujimoto Mod Start
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getFleetSmryMtr(INV_PRT_FLEET_SUB_TOTTMsg paramTmsg, String meterFlg, String fleetFlg) { // QC#29371
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", paramTmsg.invNum.getValue());
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);
        ssmParam.put("fleetFlg", fleetFlg); // QC#29371
        ssmParam.put("contrPk", paramTmsg.dsContrPk.getValue());
        ssmParam.put("svcPgm", paramTmsg.svcPgmNm.getValue());
        ssmParam.put("poNo", paramTmsg.custIssPoNum.getValue());
        ssmParam.put("siChrgTpCdMC", SVC_INV_CHRG_TP.METER_CHARGE);
        ssmParam.put("siChrgTpCdAC", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        ssmParam.put("type1", "1");
        ssmParam.put("type2", "2");
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("meterFlg", meterFlg);
        ssmParam.put("qtyFormat", FM_QTY);
        ssmParam.put("rateFormat", FM_RATE);
        ssmParam.put("amtFormat", FM_AMT);

        //2019/06/11 QC#50705 Add Start
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        //2019/06/11 QC#50705 Add End

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFleetSmryMtr", ssmParam);
    }

    /**
     * @param ssmFleetResult S21SsmEZDResult
     * @param invTpCd String
     * @param subTotPk BigDecimal
     * @return boolean
     */
    private boolean insertFleetSmryMtr(List<Map<String, Object>> resultMapList, String invTpCd, INV_PRT_FLEET_SUB_TOTTMsg invPrtFleetSubTotTmsg, String meterFlg,  String fleetFlg) { // QC#30907 2019/03/28 Mod

        boolean resultFlg = true;
        INV_PRT_FLEET_SMRY_MTRTMsg invPrtFleetSmryMtrTmsg;
        List<INV_PRT_FLEET_SMRY_MTRTMsg> invPrtFleetSmryMtrList = new ArrayList<INV_PRT_FLEET_SMRY_MTRTMsg>();

        String invNum = invPrtFleetSubTotTmsg.invNum.getValue();

        // START 2021/04/23 L.Mandanas [QC#58350, ADD]
        BigDecimal rollOvrCntRem;
        BigDecimal rollOvrCntAllw;
        BigDecimal contrBllgSchdPk;
        BigDecimal rollOvrUsed;
        BigDecimal billableUsage;
        List < Map < String, Object > > aggRollovrCurrList;
        BigDecimal actualUsage;
        BigDecimal allowance;
        // START 2022/03/29 L.Mandanas [QC#58350, ADD]
        BigDecimal multipliedUsage;
        // END 2022/03/29 L.Mandanas [QC#58350, ADD]
        List < Map < String, Object > > aggRollovrRemList;
        // END 2021/04/23 L.Mandanas [QC#58350, ADD]

        for (Map<String, Object> resultMap : resultMapList) {

            // SET INV_PRT_FLEET_SMRY_MTR
            invPrtFleetSmryMtrTmsg = new INV_PRT_FLEET_SMRY_MTRTMsg();
            BigDecimal smryMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_FLEET_SMRY_MTR_SQ);

            String strChrgAmt = setNegate((String) resultMap.get(AMOUNT_TXT), invTpCd);
            BigDecimal chrgAmt = setNegate((BigDecimal) resultMap.get(AMOUNT), invTpCd);

            // START 2021/04/23 L.Mandanas [QC#58350, ADD]
            rollOvrCntRem = BigDecimal.ZERO;
            rollOvrCntAllw = BigDecimal.ZERO;
            contrBllgSchdPk = BigDecimal.ZERO;
            rollOvrUsed = BigDecimal.ZERO;
            billableUsage = BigDecimal.ZERO;
            aggRollovrCurrList = null;
            // START 2021/09/23 L.Mandanas [QC#58350, ADD]
            boolean hasFreeCopy = false;
            // END 2021/09/23 L.Mandanas [QC#58350, ADD]
            actualUsage = (BigDecimal) resultMap.get(COPIES_MADE);
            allowance = (BigDecimal) resultMap.get(ALLOWANCE);
            aggRollovrRemList = getAggregateRolloverRemaining(invNum,
                        (String) resultMap.get(MTR_LB_CD));
            // START 2022/03/29 L.Mandanas [QC#58350, ADD]
            multipliedUsage = (BigDecimal) resultMap.get("MULTIPLIED_USAGE");
            // END 2022/03/29 L.Mandanas [QC#58350, ADD]

            // START 2021/09/23 L.Mandanas [QC#58350, MOD]
            if (!aggRollovrRemList.isEmpty()) {
//            if (!aggRollovrRemList.isEmpty()
//                    && aggRollovrRemList.get(0).get(ROLL_OVER_CNT) != null) {
            // END 2021/09/23 L.Mandanas [QC#58350, MOD]
                // START 2021/09/23 L.Mandanas [QC#58350, ADD]
                if ((aggRollovrRemList.get(0).get(USG_FREE_COPY_CNT) != null
                      && !BigDecimal.ZERO.equals(
                          aggRollovrRemList.get(0).get(USG_FREE_COPY_CNT)))
                      || (aggRollovrRemList.get(0).get(FREE_COPY_CNT) != null
                      && !BigDecimal.ZERO.equals(
                          aggRollovrRemList.get(0).get(FREE_COPY_CNT)))) {
                    hasFreeCopy = true;
                }

                if (!hasFreeCopy) {
                    if (aggRollovrRemList.get(0).get(ROLL_OVER_CNT) != null) {
                        rollOvrCntRem = (BigDecimal) aggRollovrRemList.get(0)
                                       .get(ROLL_OVER_CNT);
                        contrBllgSchdPk = (BigDecimal) aggRollovrRemList.get(0)
                                       .get(DS_CONTR_BLLG_SCHD_PK);
                        aggRollovrCurrList =
                            getCurrentRolloverCount(contrBllgSchdPk);
                    }
                    if (!aggRollovrCurrList.isEmpty()
                            && aggRollovrCurrList.get(0)
                                .get(ROLL_OVER_CNT) != null) {
                        rollOvrCntAllw = (BigDecimal) aggRollovrCurrList.get(0)
                                           .get(ROLL_OVER_CNT);
                    }
                    // START 2022/03/29 L.Mandanas [QC#58350, MOD]
                      //rollOvrUsed = computeRolloverUsed(rollOvrCntAllw,
                      //        actualUsage, allowance);
                    rollOvrUsed = computeRolloverUsed(rollOvrCntAllw,
                            multipliedUsage, allowance);
                    // END 2022/03/29 L.Mandanas [QC#58350, MOD]
                }
                else {
                    if (aggRollovrRemList.get(0).get(FREE_COPY_CNT) != null) {
                        rollOvrCntRem = (BigDecimal) aggRollovrRemList.get(0)
                                         .get(FREE_COPY_CNT);
                    }
                    if (aggRollovrRemList.get(0).get(USG_FREE_COPY_CNT) != null) {
                        rollOvrUsed = (BigDecimal) aggRollovrRemList.get(0)
                                         .get(USG_FREE_COPY_CNT);
                    }
                    rollOvrCntAllw = rollOvrUsed.add(rollOvrCntRem);
                }
                // END 2021/09/23 L.Mandanas [QC#58350, ADD]
            }
            
            // START 2021/09/23 L.Mandanas [QC#58350, DEL]
//            if (!hasFreeCopy && aggRollovrCurrList != null
//                    && !aggRollovrCurrList.isEmpty()
//                    && aggRollovrCurrList.get(0).get(ROLL_OVER_CNT) != null) {
//                rollOvrCntAllw = (BigDecimal) aggRollovrCurrList.get(0)
//                                   .get(ROLL_OVER_CNT);
//            }
//            rollOvrUsed = computeRolloverUsed(rollOvrCntAllw,
//                    actualUsage, allowance);
            // END 2021/09/23 L.Mandanas [QC#58350, DEL]
            // START 2022/03/29 L.Mandanas [QC#58350, MOD]
              //billableUsage = computeBillableUsage(rollOvrCntAllw,
              //        actualUsage, allowance);
            billableUsage = (BigDecimal) resultMap.get(BILLABLE_COPIES);
            // END 2022/03/29 L.Mandanas [QC#58350, MOD]
            // END 2021/4/23 L.Mandanas [QC#58350, ADD]

            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryMtrTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryMtrTmsg.invPrtFleetSmryMtrPk, smryMtrPk);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryMtrTmsg.invPrtFleetSubTotPk, invPrtFleetSubTotTmsg.invPrtFleetSubTotPk);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryMtrTmsg.invChrgTpNm, (String) resultMap.get(METER_TYPE));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryMtrTmsg.totBllgCopyQtyTxt, (String) resultMap.get(COPIES_MADE_TXT));
            ZYPEZDItemValueSetter
                    .setValue(invPrtFleetSmryMtrTmsg.contrMtrMultRateTxt, (String) resultMap.get(MULTIPLIER_TXT));
            ZYPEZDItemValueSetter
                    .setValue(invPrtFleetSmryMtrTmsg.totMlyCopyInclQtyTxt, (String) resultMap.get(ALLOWANCE_TXT));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryMtrTmsg.contrMtrTotAmtTxt, strChrgAmt);
            ZYPEZDItemValueSetter
                    .setValue(invPrtFleetSmryMtrTmsg.dplySpclChrgFlg, (String) resultMap.get(SPECIAL_CHARGE_FLG));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryMtrTmsg.invNum, invNum);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryMtrTmsg.totBllgCopyQty, (BigDecimal) resultMap.get(COPIES_MADE));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryMtrTmsg.contrMtrMultRate, (BigDecimal) resultMap.get(MULTIPLIER));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryMtrTmsg.totMlyCopyInclQty, (BigDecimal) resultMap.get(ALLOWANCE));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryMtrTmsg.contrMtrTotAmt, chrgAmt);
            if (hasValue(invPrtFleetSubTotTmsg.billFeetSubTotSq)) {
                ZYPEZDItemValueSetter.setValue(invPrtFleetSmryMtrTmsg.billFeetSubTotSq, invPrtFleetSubTotTmsg.billFeetSubTotSq);
            }

            // START 2021/04/23 L.Mandanas [QC#58350, ADD]
            ZYPEZDItemValueSetter.setValue(
                    invPrtFleetSmryMtrTmsg.nextRollOverQtyDplyTxt,
                    NumberFormat.getNumberInstance(Locale.US)
                    .format(rollOvrCntRem));
            ZYPEZDItemValueSetter.setValue(
                    invPrtFleetSmryMtrTmsg.rollOverQtyDplyTxt,
                    NumberFormat.getNumberInstance(Locale.US)
                    .format(rollOvrCntAllw));
            ZYPEZDItemValueSetter.setValue(
                    invPrtFleetSmryMtrTmsg.rollOverUsedQtyDplyTxt,
                    NumberFormat.getNumberInstance(Locale.US)
                    .format(rollOvrUsed));
            ZYPEZDItemValueSetter.setValue(
                    invPrtFleetSmryMtrTmsg.mtrCopyQtyDplyTxt,
                    NumberFormat.getNumberInstance(Locale.US)
                    .format(billableUsage));
            // END 2021/04/23 L.Mandanas [QC#58350, ADD]

            // START 2018/07/25 E.Kameishi [QC#27068,MOD]
            // set SMRY_XS
            resultFlg = setFleetSmryXs(invPrtFleetSmryMtrTmsg, (BigDecimal) resultMap.get(MAX_DS_CONTRACT_BLLG_MTR_PK), (BigDecimal) resultMap.get(SVC_CONTR_MTR_BLLG_PK), meterFlg, (String) resultMap.get(MTR_LB_CD), fleetFlg); // QC#30907 2019/03/28 Mod
            // END 2018/07/25 E.Kameishi [QC#27068,MOD]
            if (!resultFlg) {
                return false;
            }
            invPrtFleetSmryMtrList.add(invPrtFleetSmryMtrTmsg);
        }

        if (invPrtFleetSmryMtrList.size() > 0) {
            // INSERT
            int result = EZDFastTBLAccessor.insert(invPrtFleetSmryMtrList
                    .toArray(new INV_PRT_FLEET_SMRY_MTRTMsg[invPrtFleetSmryMtrList.size()]));
            if (result != invPrtFleetSmryMtrList.size()) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_FLEET_SMRY_MTRTMsg().getTableName(), String.valueOf(invPrtFleetSubTotTmsg.invPrtFleetSubTotPk.getValue())));
                return false;
            }
//            normCnt += result;
        }
        return true;
    }

    private boolean setFleetSmryXs(INV_PRT_FLEET_SMRY_MTRTMsg invPrtFleetSmryMtrTmsg, BigDecimal contrBllgPk, BigDecimal svcContrMtrBllgPk, String meterFlg, String mtrLbCd, String fleetFlg) { // QC#30907 2019/03/28 Mod

        boolean result = true;
        // START 2018/07/25 E.Kameishi [QC#27068,MOD]
        // GET Fleet SmryXs
        List<Map<String, Object>> ssmFleetResult = getFleetSmryXs(invPrtFleetSmryMtrTmsg.invNum.getValue(), contrBllgPk, svcContrMtrBllgPk, meterFlg, mtrLbCd, fleetFlg);  // QC#30907 2019/03/28 Mod
        // END 2018/07/25 E.Kameishi [QC#27068,MOD]

        if (ssmFleetResult.size() == 1) {
            // UPDATE INV_PRT_FLEET_SMRY_XS
            result = updateFleetSmryMtr(ssmFleetResult, invPrtFleetSmryMtrTmsg);
        } else if (ssmFleetResult.size() >= 2) {
            // INSERT INV_PRT_FLEET_SMRY_XS
            result = insertFleetSmryXs(ssmFleetResult, invPrtFleetSmryMtrTmsg);
        }
        if (!result) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getFleetSmryXs(String svcInvNum, BigDecimal contrBllgPk, BigDecimal svcContrMtrBllgPk, String meterFlg, String mtrLbCd, String fleetFlg) {  // QC#30907 2019/03/28 Mod
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgMtrPk", contrBllgPk);
        ssmParam.put("svcContrMtrBllgPk", svcContrMtrBllgPk);
        // QC#30907 2019/03/28 Add Start
        ssmParam.put("mtrLbCd", mtrLbCd);
        // QC#30907 2019/03/28 Add End
        ssmParam.put("svcInvNum", svcInvNum);
        ssmParam.put("qtyFormat", FM_QTY);

        // 2019/06/25 QC#50883 Mod Start
        ssmParam.put("rateFormat", FM_RATE2);
        //ssmParam.put("rateFormat", FM_RATE6);
        // 2019/06/25 QC#50883 Mod End

        // Add Start 2019/02/06 QC#29371
        ssmParam.put("rateFormat2", FM_RATE2);
        // Add End 2019/02/06 QC#29371
        // START 2018/07/25 E.Kameishi [QC#27068,ADD]
        ssmParam.put("meterFlg", meterFlg);
        // END 2018/07/25 E.Kameishi [QC#27068,ADD]
        // START 2019/06/04 [QC#50689 ,ADD]
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        // END 2019/06/04 [QC#50689 ,ADD]

        // QC#30907 2019/03/28 Mod Start
        // return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSmryXs", ssmParam);
        if(S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, fleetFlg)){
            return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSmryXs", ssmParam);
        } else {
            return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSmryXsForAgg", ssmParam);
        }
        // QC#30907 2019/03/28 Mod End
    }

    /**
     * @param ssmFleetResult S21SsmEZDResult
     * @param invPrtFleetSmryMtrTmsg INV_PRT_FLEET_SMRY_MTRTMsg
     * @return boolean
     */
    private boolean updateFleetSmryMtr(List<Map<String, Object>> resultMapList, INV_PRT_FLEET_SMRY_MTRTMsg invPrtFleetSmryMtrTmsg) {

        Map<String, Object> resultMap = resultMapList.get(0);
        if (resultMap.get(RATE) == null) {
            return true;
        }
        ZYPEZDItemValueSetter.setValue(invPrtFleetSmryMtrTmsg.xsMtrAmtRateTxt, (String) resultMap.get(RATE_TXT));
        ZYPEZDItemValueSetter.setValue(invPrtFleetSmryMtrTmsg.xsMtrAmtRate, (BigDecimal) resultMap.get(RATE));

        return true;
    }

    /**
     * @param ssmFleetResult S21SsmEZDResult
     * @param invPrtFleetSmryMtrTmsg INV_PRT_FLEET_SMRY_MTRTMsg
     * @return boolean
     */
    private boolean insertFleetSmryXs(List<Map<String, Object>> resultMapList, INV_PRT_FLEET_SMRY_MTRTMsg invPrtFleetSmryMtrTmsg) {

        INV_PRT_FLEET_SMRY_XSTMsg invPrtFleetSmryXsTmsg;
        List<INV_PRT_FLEET_SMRY_XSTMsg> invPrtFleetSmryXsList = new ArrayList<INV_PRT_FLEET_SMRY_XSTMsg>();

        BigDecimal smryMtrPk = invPrtFleetSmryMtrTmsg.invPrtFleetSmryMtrPk.getValue();
        String invNum = invPrtFleetSmryMtrTmsg.invNum.getValue();
        String invChrgTpNm = invPrtFleetSmryMtrTmsg.invChrgTpNm.getValue();

        for (Map<String, Object> resultMap : resultMapList) {

            // SET INV_PRT_FLEET_SMRY_MTR
            invPrtFleetSmryXsTmsg = new INV_PRT_FLEET_SMRY_XSTMsg();
            BigDecimal smryXsPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_FLEET_SMRY_XS_SQ);

            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryXsTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryXsTmsg.invPrtFleetSmryXsPk, smryXsPk);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryXsTmsg.invPrtFleetSmryMtrPk, smryMtrPk);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryXsTmsg.invChrgTpNm, invChrgTpNm);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryXsTmsg.xsMtrCopyQtyTxt, (String) resultMap.get(QTY));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryXsTmsg.xsMtrAmtRateTxt, (String) resultMap.get(RATE_TXT));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSmryXsTmsg.invNum, invNum);

            invPrtFleetSmryXsList.add(invPrtFleetSmryXsTmsg);

        }

        if (invPrtFleetSmryXsList.size() > 0) {
            // INSERT
            int result = EZDFastTBLAccessor.insert(invPrtFleetSmryXsList
                    .toArray(new INV_PRT_FLEET_SMRY_XSTMsg[invPrtFleetSmryXsList.size()]));
            if (result != invPrtFleetSmryXsList.size()) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_FLEET_SMRY_XSTMsg().getTableName(), String.valueOf(smryMtrPk)));
                return false;
            }
//            normCnt += result;
        }
        return true;
    }

    /**
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException
     */
    private boolean setMaintInfo(ResultSet rs, Map<String, Object> preConslBillInfoForMaintMap) throws SQLException, NWCB010001BusinessException {

        String invNum = rs.getString(INV_NUM);
        String taxDtlFlg = rs.getString(TAX_DETAIL_FLG);

        //QC#27069 Add Start
        String manInvFlg = rs.getString(MAN_INV_FLG);
        //QC#27069 Add End

        // GET Fleet SubTotal
        //QC#27069 Mod Start
        List<Map<String, Object>> ssmMaintResult = getMaintSubTotal(invNum, taxDtlFlg, manInvFlg);
        //List<Map<String, Object>> ssmMaintResult = getMaintSubTotal(invNum, taxDtlFlg);
        //QC#27069 Mod End

        if (ssmMaintResult.size() == 0) {
            //NWCM0112E=It failed to get [@].(@)
            S21InfoLogOutput.println(NWCM0112E, toArray("MaintSubTotal", invNum));
            return false;
        }
        // INSERT FLEET
        boolean result = setMaint(ssmMaintResult, rs, preConslBillInfoForMaintMap);
        if (!result) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getMaintSubTotal(String invNum, String taxDtlFlg, String manInvFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        ssmParam.put("taxDtlFlg", taxDtlFlg);
        ssmParam.put("dsSlsTaxTpCd01", DS_SLS_TAX_TP.STATE_TAX);
        ssmParam.put("dsSlsTaxTpCd02", DS_SLS_TAX_TP.COUNTY_TAX);
        ssmParam.put("dsSlsTaxTpCd03", DS_SLS_TAX_TP.CITY_TAX);
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("amtFormat", FM_AMT);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        //QC#27069 Add Start
        String statementId;

        if (ZYPConstant.FLG_ON_1.equals(manInvFlg)) {
            statementId = "getMaintSubTotal_manInv";
        } else {
            statementId = "getMaintSubTotal";
        }
        //QC#27069 Add End
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList(statementId, ssmParam);
    }

    private boolean setMaint(List<Map<String, Object>> resultMapList, ResultSet rs, Map<String, Object> preConslBillInfoForMaintMap) throws SQLException, NWCB010001BusinessException {

        String invNum = rs.getString(INV_NUM);
        String invTpCd = rs.getString(INV_TP_CD);
        String meterFlg = rs.getString(AR_METER_FLG);

        boolean resultFlg = true;
        INV_PRT_MAINT_SUB_TOTTMsg invPrtMaintSubTotTmsg;
        List<INV_PRT_MAINT_SUB_TOTTMsg> invPrtMaintSubTotList = new ArrayList<INV_PRT_MAINT_SUB_TOTTMsg>();

        for (Map<String, Object> resultMap : resultMapList) {

            invPrtMaintSubTotTmsg = new INV_PRT_MAINT_SUB_TOTTMsg();

            BigDecimal stateTax = setNegate((BigDecimal) resultMap.get(STATE_TAX), invTpCd);
            BigDecimal countyTax = setNegate((BigDecimal) resultMap.get(COUNTY_TAX), invTpCd);
            BigDecimal cityTax = setNegate((BigDecimal) resultMap.get(CITY_TAX), invTpCd);
            BigDecimal totalTax = setNegate((BigDecimal) resultMap.get(TOTAL_TAX), invTpCd);
            BigDecimal totalAmt = setNegate((BigDecimal) resultMap.get(TOTAL_AMOUNT), invTpCd);
            String stateTaxTxt = setNegate((String) resultMap.get(STATE_TAX_TXT), invTpCd);
            String countyTaxTxt = setNegate((String) resultMap.get(COUNTY_TAX_TXT), invTpCd);
            String cityTaxTxt = setNegate((String) resultMap.get(CITY_TAX_TXT), invTpCd);
            String totalTaxTxt = setNegate((String) resultMap.get(TOTAL_TAX_TXT), invTpCd);
            String totalAmtTxt = setNegate((String) resultMap.get(TOTAL_AMOUNT_TXT), invTpCd);

            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.invPrtMaintSubTotPk, ZYPOracleSeqAccessor
                    .getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_MAINT_SUB_TOT_SQ));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.invNum, invNum);
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.invPrtDtlFmtTpCd, INV_PRT_DTL_FMT_TP.MAINTENANCE);
            // QC#20788 add Start
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.shipToCustAcctCd, (String) resultMap.get(SHIP_TO_CUST_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.shipToCustAcctNm, (String) resultMap.get(SHIP_TO_CUST_ACCT_NM));
            // QC#20788 add End
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.shipToCustCd, (String) resultMap.get(SHIP_TO_CODE));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.shipToLocNm, (String) resultMap.get(SHIP_TO_NAME));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.shipToFirstLineAddr, (String) resultMap.get(SHIP_TO_FST_ADDR));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.shipToScdLinePrntAddr, (String) resultMap.get(SHIP_TO_SCD_ADDR));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.shipToCtyAddr, (String) resultMap.get(SHIP_TO_CITY));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.shipToStCd, (String) resultMap.get(SHIP_TO_ST));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.shipToPostCd, (String) resultMap.get(SHIP_TO_POST));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.custIssPoNum, (String) resultMap.get(PO_NO));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.firstBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_1));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.scdBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_2));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.thirdBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_3));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.frthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_4));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.fifthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_5));
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.sixthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_6));

            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.invStTaxAmtTxt, stateTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.invCntyTaxAmtTxt, countyTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.invCityTaxAmtTxt, cityTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.invTotTaxAmtTxt, totalTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.invSubTotAmtTxt, totalAmtTxt);
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.invStTaxAmt, stateTax);
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.invCntyTaxAmt, countyTax);
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.invCityTaxAmt, cityTax);
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.invTotTaxAmt, totalTax);
            ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.invSubTotAmt, totalAmt);

            if (preConslBillInfoForMaintMap != null && !preConslBillInfoForMaintMap.isEmpty()) {
                //Store Previous Consolidated Bill
                StringBuffer key = new StringBuffer(INV_PRT_MAINT_SUB_TOT);
                if (hasValue((String) resultMap.get(SHIP_TO_CODE))) {
                    key.append((String) resultMap.get(SHIP_TO_CODE));
                }
                if (hasValue((String) resultMap.get(PO_NO))) {
                    key.append((String) resultMap.get(PO_NO));
                }
                if (hasValue((String) resultMap.get(CUST_CODES_1))) {
                    key.append((String) resultMap.get(CUST_CODES_1));
                }
                if (hasValue((String) resultMap.get(CUST_CODES_2))) {
                    key.append((String) resultMap.get(CUST_CODES_2));
                }
                if (hasValue((String) resultMap.get(CUST_CODES_3))) {
                    key.append((String) resultMap.get(CUST_CODES_3));
                }
                if (hasValue((String) resultMap.get(CUST_CODES_4))) {
                    key.append((String) resultMap.get(CUST_CODES_4));
                }
                if (hasValue((String) resultMap.get(CUST_CODES_5))) {
                    key.append((String) resultMap.get(CUST_CODES_5));
                }
                if (hasValue((String) resultMap.get(CUST_CODES_6))) {
                    key.append((String) resultMap.get(CUST_CODES_6));
                }
                if (preConslBillInfoForMaintMap.get(CONSL_BILL_NUM) != null) {
                    ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.conslBillNum, (String) preConslBillInfoForMaintMap.get(CONSL_BILL_NUM));
                }
                if (preConslBillInfoForMaintMap.get(key.toString()) != null) {
                    ZYPEZDItemValueSetter.setValue(invPrtMaintSubTotTmsg.billMaintSubTotSq, (BigDecimal) preConslBillInfoForMaintMap.get(key.toString()));
                }
            }
            
            invPrtMaintSubTotList.add(invPrtMaintSubTotTmsg);

            //QC#27069 Add Start
            String manInvFlg = rs.getString(MAN_INV_FLG);
            //QC#27069 Add End

            // set MAINTENANCE MACH INFO
            //QC#27069 Mod Start
            resultFlg = setMaintMach(invPrtMaintSubTotTmsg, invTpCd, meterFlg, manInvFlg);
            //resultFlg = setMaintMach(invPrtMaintSubTotTmsg, invTpCd, meterFlg);
            //QC#27069 Mod End

            if (!resultFlg) {
                return false;
            }
        }
        int result = EZDFastTBLAccessor.insert(invPrtMaintSubTotList.toArray(new INV_PRT_MAINT_SUB_TOTTMsg[invPrtMaintSubTotList
                .size()]));
        if (result != invPrtMaintSubTotList.size()) {
            S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_MAINT_SUB_TOTTMsg().getTableName(), invNum));
            return false;
        }
//        normCnt += result;
        return true;
    }

    private boolean setMaintMach(INV_PRT_MAINT_SUB_TOTTMsg invPrtMaintSubTotTmsg, String invTpCd, String meterFlg, String manInvFlg) {

        // GET Maint Mach
        //QC#27069 Mod Start
        List<Map<String, Object>> ssmMaintResult = getMaintMach(invPrtMaintSubTotTmsg, manInvFlg);
        //List<Map<String, Object>> ssmMaintResult = getMaintMach(invPrtMaintSubTotTmsg);
        //QC#27069 Mod End
        if (ssmMaintResult.size() == 0) {
            return true;
        }
        // INSERT INV_PRT_MAINT_MACH,
        //QC#27069 Mod Start
        boolean result = insertMaintMach(ssmMaintResult, invPrtMaintSubTotTmsg, invTpCd, meterFlg, manInvFlg);
        //boolean result = insertMaintMach(ssmMaintResult, invPrtMaintSubTotTmsg, invTpCd, meterFlg);
        //QC#27069 Mod End
        if (!result) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getMaintMach(INV_PRT_MAINT_SUB_TOTTMsg paramTmsg, String manInvFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", paramTmsg.invNum.getValue());
        ssmParam.put("shipToCust", paramTmsg.shipToCustCd.getValue());
        ssmParam.put("poNo", paramTmsg.custIssPoNum.getValue());
        ssmParam.put("custCodes1", paramTmsg.firstBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes2", paramTmsg.scdBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes3", paramTmsg.thirdBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes4", paramTmsg.frthBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes5", paramTmsg.fifthBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes6", paramTmsg.sixthBllgAttrbValTxt.getValue());
        ssmParam.put("siChrgTpCdBC", SVC_INV_CHRG_TP.BASE_CHARGE);
        ssmParam.put("siChrgTpCdMC", SVC_INV_CHRG_TP.METER_CHARGE);
        ssmParam.put("siChrgTpCdAC", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE); // QC#20888 add
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("qtyFormat", FM_QTY2);
        //ssmParam.put("svcPgmTpCd14", SVC_PGM_TP.RENTALSPLIT); // QC#52209 2019/07/31 Del

        // QC#29672 2019/01/29 Add Start
        ssmParam.put("svcInvMergeTpPrintWithBase", SVC_INV_MERGE_TP.MERGE_INTO_BASE_CHARGE );
        // QC#29672 2019/01/29 Add End

        //QC#27069 Add Start
        String statementId;

        if (ZYPConstant.FLG_ON_1.equals(manInvFlg)) {
            statementId = "getMaintMach_manInv";
        } else {
            statementId = "getMaintMach";
        }
        //QC#27069 Add End

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList(statementId, ssmParam);
    }

    private boolean insertMaintMach(List<Map<String, Object>> resultMapList, INV_PRT_MAINT_SUB_TOTTMsg paramTmsg, String invTpCd, String meterFlg, String manInvFlg) {

        //boolean resultFlg = true;
        INV_PRT_MAINT_MACHTMsg invPrtMaintMachTmsg;
        List<INV_PRT_MAINT_MACHTMsg> invPrtMaintMachList = new ArrayList<INV_PRT_MAINT_MACHTMsg>();

        String invNum = paramTmsg.invNum.getValue();

        //QC#50149 2019/05/13 Add Start
        List<Map<String, Object>> sortedMapList = updateAndOrderBySerNum(resultMapList);
        //QC#50149 2019/05/13 Add End

        //QC#29672 2019/01/29 Add Start
        sortedMapList = orderbySvcInvChrgTp(sortedMapList);
        //QC#29672 2019/01/29 Add End

        for (Map<String, Object> resultMap : sortedMapList) {

            String svcPgm = (String) resultMap.get(SVC_PGM_NM);
            String serialNo = (String) resultMap.get(SERIAL_NO);
            //QC#50149 2019/05/13 Del Start
            //String invLineTp = (String) resultMap.get(INV_LINE_TP_CD);
            //String baseSerial = (String) resultMap.get(BASE_SERIAL_NO);
            //QC#50149 2019/05/13 Del End
            BigDecimal baseChrgCount = (BigDecimal) resultMap.get(BASE_CHRG_COUNT);
            BigDecimal meterChrgCount = (BigDecimal) resultMap.get(METER_CHRG_COUNT);
            //QC#29672 2019/01/29 Add Start
            String svcInvChrgTp = (String) resultMap.get(SVC_INV_CHRG_TP_CD);
            String svcInvMergeTpCd = (String) resultMap.get(SVC_INV_MERGE_TP_CD);
            String hasParent = (String) resultMap.get("HAS_PARENT");
            //QC#29672 2019/01/29 Add End

            //QC#50149 2019/05/13 Del Start
            //StringBuilder sb = new StringBuilder();
            //if (SVC_INV_LINE_TP.ACCESSORY.equals(invLineTp)) {
            //    if (baseSerial == null) {
            //        //QC#18660
            //        if (serialNo != null) {
            //            sb.append(serialNo);
            //        }
            //    } else {
            //        //QC#18660
            //        if (serialNo != null) {
            //            sb.append(serialNo).append(SERIAL_MESSAGE).append(baseSerial).append(")"); //QC#20888
            //        } else {
            //            sb.append(SERIAL_MESSAGE).append(baseSerial).append(")"); //QC#20888
            //        }
            //    }
            //    serialNo = sb.toString();
            //}
            ////QC#30494 Start
            ////Serial Special Serial Logic
            //if (!hasValue(serialNo)) {
            //    sb = new StringBuilder();
            //    //Parameter : MACHINE_PK --> SVC_MACH_MSTR.SVC_MACH_TP_CD = 2: Accessory --> SVC_CONFIG_MSTR.SVC_MACH_MSTR_PK --> SVC_MACH_MSTR.SER_NUM
            //    Map<String, Object> mapMaintSerNum = getMaintSerNum((BigDecimal) resultMap.get(MACHINE_PK));
            //    if (mapMaintSerNum != null && SVC_MACH_TP.ACCESSORY.equals((String)mapMaintSerNum.get("ACC_SVC_MACH_TP_CD"))) {
            //        if (hasValue((String)mapMaintSerNum.get("ACC_SER_NUM"))) {
            //            if (hasValue((String)mapMaintSerNum.get("BASE_SER_NUM"))) {
            //                sb.append(serialNo).append(SERIAL_MESSAGE).append((String)mapMaintSerNum.get("BASE_SER_NUM")).append(")");
            //            } else {
            //                sb.append(serialNo);
            //            }
            //        } else {
            //            if (hasValue((String)mapMaintSerNum.get("BASE_SER_NUM"))) {
            //                sb.append(SERIAL_MESSAGE).append((String)mapMaintSerNum.get("BASE_SER_NUM")).append(")");
            //            }
            //        }
            //    }
            //    serialNo = sb.toString();
            //}
            ////QC#30494 End
            //QC#50149 2019/05/13 Del End

            StringBuilder sbSvcPgm = new StringBuilder();
            sbSvcPgm.append(nullConvEmp(svcPgm));
            if (BigDecimal.ZERO.compareTo(meterChrgCount) == 0 && BigDecimal.ZERO.compareTo(baseChrgCount) != 0 
                && resultMap.get(BASE_COPY_QTY) != null && !"0".equals((String) resultMap.get(BASE_COPY_QTY))) {
                sbSvcPgm.append(SVC_MESSAGE1).append((String) resultMap.get(BASE_COPY_QTY)).append(SVC_MESSAGE3);
            }
            svcPgm = sbSvcPgm.toString();

            // SET INV_PRT_MAINT_MACH
            invPrtMaintMachTmsg = new INV_PRT_MAINT_MACHTMsg();
            BigDecimal machPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_MAINT_MACH_SQ);

            ZYPEZDItemValueSetter.setValue(invPrtMaintMachTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invPrtMaintMachTmsg.invPrtMaintMachPk, machPk);
            ZYPEZDItemValueSetter.setValue(invPrtMaintMachTmsg.invPrtMaintSubTotPk, paramTmsg.invPrtMaintSubTotPk);
            ZYPEZDItemValueSetter.setValue(invPrtMaintMachTmsg.svcMachMstrPk, (BigDecimal) resultMap.get(MACHINE_PK));
            ZYPEZDItemValueSetter.setValue(invPrtMaintMachTmsg.invPrtSerNumTxt, serialNo);
            ZYPEZDItemValueSetter.setValue(invPrtMaintMachTmsg.mdlNm, (String) resultMap.get(MODEL));
            ZYPEZDItemValueSetter.setValue(invPrtMaintMachTmsg.dsContrPk, (BigDecimal) resultMap.get(CONTR_PK));
            ZYPEZDItemValueSetter.setValue(invPrtMaintMachTmsg.dsContrNum, (String) resultMap.get(CONTR_NO));
            //ZYPEZDItemValueSetter.setValue(invPrtMaintMachTmsg.svcPgmNm, (String) resultMap.get(SVC_PGM_NM));
            ZYPEZDItemValueSetter.setValue(invPrtMaintMachTmsg.svcPgmNm, (String) resultMap.get("INV_PRINT_COV_TXT"));
            ZYPEZDItemValueSetter.setValue(invPrtMaintMachTmsg.invNum, invNum);
            if (hasValue(paramTmsg.billMaintSubTotSq)) {
                ZYPEZDItemValueSetter.setValue(invPrtMaintMachTmsg.billMaintSubTotSq, paramTmsg.billMaintSubTotSq);
            }

            //QC#18223 mod Start
            // set Maintenance CHARGE INFO
//            resultFlg = setMaintChrgDtl(paramTmsg, invPrtMaintMachTmsg, (BigDecimal) resultMap.get(MODEL_ID), invTpCd);
            //QC#27069 Mod Start
            //QC#29672 2019/01/30 Mod Start
            // START 06/20/2022 [QC#60205, MOD]
//            Integer maintChrgDtlCnt = setMaintChrgDtl(paramTmsg, invPrtMaintMachTmsg, (BigDecimal) resultMap.get(MODEL_ID), invTpCd, manInvFlg, svcInvChrgTp, svcInvMergeTpCd, hasParent);
            String invLineTpCd = (String) resultMap.get(INV_LINE_TP_CD);
            Integer maintChrgDtlCnt = setMaintChrgDtl(paramTmsg, invPrtMaintMachTmsg, (BigDecimal) resultMap.get(MODEL_ID), invTpCd, manInvFlg, svcInvChrgTp, svcInvMergeTpCd, hasParent, invLineTpCd);
            // END   06/20/2022 [QC#60205, MOD]
            //Integer maintChrgDtlCnt = setMaintChrgDtl(paramTmsg, invPrtMaintMachTmsg, (BigDecimal) resultMap.get(MODEL_ID), invTpCd, manInvFlg);
            //QC#29672 2019/01/30 Mod End
            //Integer maintChrgDtlCnt = setMaintChrgDtl(paramTmsg, invPrtMaintMachTmsg, (BigDecimal) resultMap.get(MODEL_ID), invTpCd, manInvFlg);
            //QC#27069 Mod End
//            if (!resultFlg) {
            if (maintChrgDtlCnt == null) {
                return false;
            }

            Integer maintMtrCnt = 0;

            if (SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvChrgTp)) {
            // set Maintenance METER
//            resultFlg = setMaintMtr(paramTmsg, invPrtMaintMachTmsg, (BigDecimal) resultMap.get(MODEL_ID), invTpCd, meterFlg);
            //QC#27069 Mod Start
                maintMtrCnt = setMaintMtr(paramTmsg, invPrtMaintMachTmsg, (BigDecimal) resultMap.get(MODEL_ID), invTpCd, meterFlg, manInvFlg);
            //Integer maintMtrCnt = setMaintMtr(paramTmsg, invPrtMaintMachTmsg, (BigDecimal) resultMap.get(MODEL_ID), invTpCd, meterFlg);
            //QC#27069 Mod End
//            if (!resultFlg) {
                if (maintMtrCnt == null) {
                    return false;
                }
            }
            if (maintChrgDtlCnt == 0 && maintMtrCnt == 0) {
                continue;
            }
            //QC#18223 mod End

            //QC#13358 mod Start
//            ZYPEZDItemValueSetter.setValue(invPrtMaintMachTmsg.svcPgmNm, svcPgm);
            ZYPEZDItemValueSetter.setValue(invPrtMaintMachTmsg.svcPgmNm, (String) resultMap.get("INV_PRINT_COV_TXT"));
            //QC#13358 mod End
            invPrtMaintMachList.add(invPrtMaintMachTmsg);
            
        }

        if (invPrtMaintMachList.size() > 0) {
            // INSERT
            int result = EZDFastTBLAccessor.insert(invPrtMaintMachList.toArray(new INV_PRT_MAINT_MACHTMsg[invPrtMaintMachList
                    .size()]));
            if (result != invPrtMaintMachList.size()) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_MAINT_MACHTMsg().getTableName(), String.valueOf(paramTmsg.invPrtMaintSubTotPk.getValue())));
                return false;
            }
//            normCnt += result;
        }
        return true;
    }

    //QC#50149 2019/05/13 Add Start
    private List<Map<String, Object>> updateAndOrderBySerNum(List<Map<String, Object>> resultMapList){
        List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
        TreeMap<String, List<Map<String, Object>>> sortMap = new TreeMap<String, List<Map<String, Object>>>();
        Map<String, Object> preMap = null;

        for (Map<String, Object> resultMap : resultMapList) {

            String serialNo = (String) resultMap.get(SERIAL_NO);
            String sortSerialNo = (String) resultMap.get(SERIAL_NO);
            String invLineTp = (String) resultMap.get(INV_LINE_TP_CD);
            String baseSerial = (String) resultMap.get(BASE_SERIAL_NO);

            if (!hasValue(sortSerialNo)){
                sortSerialNo = "";
            }

            StringBuilder sb = new StringBuilder();
            StringBuilder sbSort = new StringBuilder();
            if (SVC_INV_LINE_TP.ACCESSORY.equals(invLineTp)) {
                if (baseSerial == null) {
                    if (serialNo != null) {
                        sb.append(serialNo);
                        sbSort.append(serialNo);
                    }
                } else {
                    if (serialNo != null) {
                        sb.append(serialNo).append(SERIAL_MESSAGE).append(baseSerial).append(")");
                        sbSort.append(baseSerial);
                    } else {
                        sb.append(SERIAL_MESSAGE).append(baseSerial).append(")");
                        sbSort.append(baseSerial);
                    }
                }
                serialNo = sb.toString();
                sortSerialNo = sbSort.toString();
            }

            //Serial Special Serial Logic
            if (!hasValue(serialNo)) {
                sb = new StringBuilder();
                //Parameter : MACHINE_PK --> SVC_MACH_MSTR.SVC_MACH_TP_CD = 2: Accessory --> SVC_CONFIG_MSTR.SVC_MACH_MSTR_PK --> SVC_MACH_MSTR.SER_NUM
                Map<String, Object> mapMaintSerNum = getMaintSerNum((BigDecimal) resultMap.get(MACHINE_PK));
                if (mapMaintSerNum != null && SVC_MACH_TP.ACCESSORY.equals((String)mapMaintSerNum.get("ACC_SVC_MACH_TP_CD"))) {
                    if (hasValue((String)mapMaintSerNum.get("ACC_SER_NUM"))) {
                        if (hasValue((String)mapMaintSerNum.get("BASE_SER_NUM"))) {
                            sb.append(serialNo).append(SERIAL_MESSAGE).append((String)mapMaintSerNum.get("BASE_SER_NUM")).append(")");
                            sbSort.append((String)mapMaintSerNum.get("BASE_SER_NUM"));
                        } else {
                            sb.append(serialNo);
                            sbSort.append(serialNo);
                        }
                    } else {
                        if (hasValue((String)mapMaintSerNum.get("BASE_SER_NUM"))) {
                            sb.append(SERIAL_MESSAGE).append((String)mapMaintSerNum.get("BASE_SER_NUM")).append(")");
                            sbSort.append((String)mapMaintSerNum.get("BASE_SER_NUM"));
                        }
                    }
                }
                serialNo = sb.toString();
                sortSerialNo = sbSort.toString();
            }

            resultMap.put(SERIAL_NO, serialNo);
            resultMap.put(SERIAL_NO_SORT, sortSerialNo);

            if (preMap == null){
                List<Map<String, Object>> wk = new ArrayList<Map<String, Object>>();
                wk.add(resultMap);
                sortMap.put(sortSerialNo, wk);
                preMap = resultMap;
                continue;
            }

            if (!convString((BigDecimal) resultMap.get(CONTR_PK)).equals((convString((BigDecimal) preMap.get(CONTR_PK))))){
                rtnList.addAll(treeToArray(sortMap));
                sortMap.clear();
            }

            if (sortMap.containsKey(sortSerialNo)){
                sortMap.get(sortSerialNo).add(resultMap);
            } else {
                List<Map<String, Object>> wk = new ArrayList<Map<String, Object>>();
                wk.add(resultMap);
                sortMap.put(sortSerialNo, wk);
            }
        }

        if (sortMap.size() > 0){
            rtnList.addAll(treeToArray(sortMap));
        }

        return rtnList;
    }
    //QC#50149 2019/05/13 Add End

    //QC#50149 2019/05/13 Add Start
    private List<Map<String, Object>> treeToArray(TreeMap<String, List<Map<String, Object>>> sortMap){
        List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
        List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>(sortMap.values());

        for (List<Map<String, Object>> tmpList : list){
            for (Map<String, Object> m : tmpList){
                rtnList.add(m);
            }
        }

        return rtnList;
    }
    //QC#50149 2019/05/13 Add End

    //QC#29672 2019/01/29 Add Start
    private List<Map<String, Object>> orderbySvcInvChrgTp(List<Map<String, Object>> resultMapList){
        List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> wrkList = new ArrayList<Map<String, Object>>();
        Map<String, Object> preMap = null;

        for (Map<String, Object> resultMap : resultMapList) {

            if (preMap == null){
                wrkList.add(resultMap);
                preMap = resultMap;
                continue;
            }

            if (!convString((BigDecimal) resultMap.get(CONTR_PK)).equals((convString((BigDecimal) preMap.get(CONTR_PK)))) ||
                //QC#50149 2019/05/13 Mod Start
                !convString((String) resultMap.get(SERIAL_NO_SORT)).equals((convString((String) preMap.get(SERIAL_NO_SORT)))) ||
                //!convString((String) resultMap.get(SERIAL_NO)).equals((convString((String) preMap.get(SERIAL_NO)))) ||
                //QC#50149 2019/05/13 Mod End
                !convString((BigDecimal) resultMap.get(MACHINE_PK)).equals((convString((BigDecimal) preMap.get(MACHINE_PK)))) ||
                !convString((BigDecimal) resultMap.get(MODEL_ID)).equals((convString((BigDecimal) preMap.get(MODEL_ID)))) ||
                !convString((String) resultMap.get(SVC_PGM_CD)).equals((convString((String) preMap.get(SVC_PGM_CD)))) ||
                !convString((String) resultMap.get(INV_LINE_TP_CD)).equals((convString((String) preMap.get(INV_LINE_TP_CD)))) ||
                !convString((String) resultMap.get("INV_PRINT_COV_TXT")).equals((convString((String) preMap.get("INV_PRINT_COV_TXT"))))){
                rtnList.addAll(oderbySvcInvChrgTpBcMcAc(wrkList));
                wrkList.clear();
                wrkList.add(resultMap);
                preMap = resultMap;
                continue;
            }

            wrkList.add(resultMap);
        }

        if (!wrkList.isEmpty()){
            rtnList.addAll(oderbySvcInvChrgTpBcMcAc(wrkList));
        }

        return rtnList;
    }
    //QC#29672 2019/01/29 Add End

    //QC#29672 2019/01/29 Add Start
    private List<Map<String, Object>> oderbySvcInvChrgTpBcMcAc(List<Map<String, Object>> wrkList){
        List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> bcList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> acList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> mcList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> etcList = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> resultMap : wrkList) {
            String svcInvChrgTp = (String)resultMap.get(SVC_INV_CHRG_TP_CD);

            if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(svcInvChrgTp)) {
                bcList.add(resultMap);
            } else if (SVC_INV_CHRG_TP.ADDITIONAL_CHARGE.equals(svcInvChrgTp)) {
                acList.add(resultMap);
            } else if (SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvChrgTp)) {
                mcList.add(resultMap);
            } else {
                etcList.add(resultMap);
            }
        }

        rtnList.addAll(bcList);
        rtnList.addAll(mcList);
        rtnList.addAll(acList);
        rtnList.addAll(etcList);

        return rtnList;
    }
    //QC#29672 2019/01/29 Add End

    //QC#30494 Start
    private Map<String, Object> getMaintSerNum(BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        return (Map<String, Object>) ssmBatchClient.queryObject("getMaintSerNum", ssmParam);
    }
    //QC#30494 End
    
  //QC#18223 mod Start
//  private boolean setMaintChrgDtl(INV_PRT_MAINT_SUB_TOTTMsg invPrtMaintSubTotTmsg, INV_PRT_MAINT_MACHTMsg invPrtMaintMachTmsg,
    private Integer setMaintChrgDtl(INV_PRT_MAINT_SUB_TOTTMsg invPrtMaintSubTotTmsg, INV_PRT_MAINT_MACHTMsg invPrtMaintMachTmsg,
            // START 06/20/2022 [QC#60205, MOD]
//            BigDecimal modelId, String invTpCd, String manInvFlg, String svcInvChrgTp, String svcInvMergeTpCd, String hasParent) {
            BigDecimal modelId, String invTpCd, String manInvFlg, String svcInvChrgTp, String svcInvMergeTpCd, String hasParent, String invLineTpCd) {
            // END   06/20/2022 [QC#60205, MOD]

      // GET Maint Mach
      //QC#27069 Mod Start
      //QC#29672 2019/01/29 Mod Start
        // START 06/20/2022 [QC#60205, MOD]
//        List<Map<String, Object>> ssmMaintResult = getMaintChrgDtl(invPrtMaintSubTotTmsg, invPrtMaintMachTmsg, modelId, manInvFlg, svcInvChrgTp, svcInvMergeTpCd, hasParent);
        List<Map<String, Object>> ssmMaintResult = getMaintChrgDtl(invPrtMaintSubTotTmsg, invPrtMaintMachTmsg, modelId, manInvFlg, svcInvChrgTp, svcInvMergeTpCd, hasParent, invLineTpCd);
        // END   06/20/2022 [QC#60205, MOD]
        //List<Map<String, Object>> ssmMaintResult = getMaintChrgDtl(invPrtMaintSubTotTmsg, invPrtMaintMachTmsg, modelId, manInvFlg);
      //QC#29672 2019/01/29 Mod End
      //List<Map<String, Object>> ssmMaintResult = getMaintChrgDtl(invPrtMaintSubTotTmsg, invPrtMaintMachTmsg, modelId);
      //QC#27069 Mod End

      // INSERT INV_PRT_MAINT_MACH,
//      boolean result = insertMaintChrgDtl(ssmMaintResult, invPrtMaintMachTmsg, invTpCd);
      //QC#27069 Mod Start
        // START 06/20/2022 [QC#60205, MOD]
        //Integer maintChrgDtlCnt = insertMaintChrgDtl(ssmMaintResult, invPrtMaintMachTmsg, invTpCd, invPrtMaintSubTotTmsg, modelId, manInvFlg, svcInvMergeTpCd, hasParent);
        Integer maintChrgDtlCnt = insertMaintChrgDtl(ssmMaintResult, invPrtMaintMachTmsg, invTpCd, invPrtMaintSubTotTmsg, modelId, manInvFlg, svcInvMergeTpCd, hasParent, invLineTpCd);
        // END   06/20/2022 [QC#60205, MOD]
      //Integer maintChrgDtlCnt = insertMaintChrgDtl(ssmMaintResult, invPrtMaintMachTmsg, invTpCd, invPrtMaintSubTotTmsg, modelId);
      //QC#27069 Mod End
//      if (!result) {
//          return false;
//      }
//      return true;
        return maintChrgDtlCnt;
    }
//QC#18223 mod End

    @SuppressWarnings("unchecked")
    // START 06/20/2022 [QC#60205, MOD]
//    private List<Map<String, Object>> getMaintChrgDtl(INV_PRT_MAINT_SUB_TOTTMsg subTotTmsg, INV_PRT_MAINT_MACHTMsg machTmsg, BigDecimal modelId, String manInvFlg, String svcInvChrgTp, String svcInvMergeTpCd, String hasParent) {
    private List<Map<String, Object>> getMaintChrgDtl(INV_PRT_MAINT_SUB_TOTTMsg subTotTmsg, INV_PRT_MAINT_MACHTMsg machTmsg, BigDecimal modelId, String manInvFlg, String svcInvChrgTp, String svcInvMergeTpCd, String hasParent, String invLineTpCd) {
    // END   06/20/2022 [QC#60205, MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", subTotTmsg.invNum.getValue());
        ssmParam.put("shipToCust", subTotTmsg.shipToCustCd.getValue());
        ssmParam.put("poNo", subTotTmsg.custIssPoNum.getValue());
        ssmParam.put("custCodes1", subTotTmsg.firstBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes2", subTotTmsg.scdBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes3", subTotTmsg.thirdBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes4", subTotTmsg.frthBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes5", subTotTmsg.fifthBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes6", subTotTmsg.sixthBllgAttrbValTxt.getValue());
        ssmParam.put("contrPk", machTmsg.dsContrPk.getValue());
        ssmParam.put("machPk", machTmsg.svcMachMstrPk.getValue());
        ssmParam.put("modelId", modelId);
        ssmParam.put("siChrgTpCdBC", SVC_INV_CHRG_TP.BASE_CHARGE);
        ssmParam.put("siChrgTpCdAC", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        ssmParam.put("siChrgTpCdMC", SVC_INV_CHRG_TP.METER_CHARGE);

        //QC#29672 2019/01/30 Add Start
        if (SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvChrgTp)){
            ssmParam.put("isChrgTpCdMC", ZYPConstant.FLG_ON_Y);
        }

        if (ZYPConstant.FLG_ON_Y.equals(hasParent) && SVC_INV_MERGE_TP.SPLIT_BASE_CHARGE.equals(svcInvMergeTpCd)){
            ssmParam.put("isMergeTpPrintAccs", SVC_INV_MERGE_TP.SPLIT_BASE_CHARGE);
        }
        // START 06/20/2022 [QC#60205, ADD]
        if (SVC_INV_LINE_TP.MACHINE.equals(invLineTpCd)) {
            ssmParam.put("lineTpMach", SVC_INV_LINE_TP.MACHINE);
        }
        ssmParam.put("dtlTpAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        ssmParam.put("mergeIntoBase", SVC_INV_MERGE_TP.MERGE_INTO_BASE_CHARGE);
        // END   06/20/2022 [QC#60205, ADD]

        //QC#29672 2019/01/30 Add End

        ssmParam.put("svcPgm", machTmsg.svcPgmNm.getValue());
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("amtFormat", FM_AMT);
        ssmParam.put("siChrgTpNmBase", BASE);
        ssmParam.put("siChrgTpNmUsage", USAGE);
        ssmParam.put("siChrgTpNmAttachment", ATT);  //QC#20888 add
        ssmParam.put("tpFax", INV_SMRY_LINE_TP.FAX);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);     //QC#18223 add
        ssmParam.put("siInvTpCdMACH", SVC_INV_LINE_TP.MACHINE);  //QC#20888 add

        ssmParam.put("svcInvChrgTpCd", svcInvChrgTp);  // QC#30852
        //QC#27069 Add Start
        String statementId;

        if (ZYPConstant.FLG_ON_1.equals(manInvFlg)) {
            statementId = "getMaintMtrChrg_manInv";
        } else {
            statementId = "getMaintMtrChrg";
        }
        //QC#27069 Add End

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList(statementId, ssmParam);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getMaintChrgDtlForAdditionalChargeNonDisp(INV_PRT_MAINT_SUB_TOTTMsg subTotTmsg, 
            INV_PRT_MAINT_MACHTMsg machTmsg, BigDecimal modelId, String periodFrom, String periodThru, BigDecimal svcContrBaseBllgPk,
            // START 06/20/2022 [QC#60205, MOD]
            // String addlChrgInvTpCd, String manInvFlg) {
            String addlChrgInvTpCd, String manInvFlg, String invLineTpCd, BigDecimal dsContrDtlPk) {
            // END   06/20/2022 [QC#60205, MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", subTotTmsg.invNum.getValue());
        ssmParam.put("shipToCust", subTotTmsg.shipToCustCd.getValue());
        ssmParam.put("poNo", subTotTmsg.custIssPoNum.getValue());
        ssmParam.put("custCodes1", subTotTmsg.firstBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes2", subTotTmsg.scdBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes3", subTotTmsg.thirdBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes4", subTotTmsg.frthBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes5", subTotTmsg.fifthBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes6", subTotTmsg.sixthBllgAttrbValTxt.getValue());
        ssmParam.put("contrPk", machTmsg.dsContrPk.getValue());
        ssmParam.put("machPk", machTmsg.svcMachMstrPk.getValue());
        ssmParam.put("modelId", modelId);
        ssmParam.put("siChrgTpCdBC", SVC_INV_CHRG_TP.BASE_CHARGE);
        ssmParam.put("siChrgTpCdAC", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        ssmParam.put("siChrgTpCdMC", SVC_INV_CHRG_TP.METER_CHARGE);
        ssmParam.put("svcPgm", machTmsg.svcPgmNm.getValue());
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("amtFormat", FM_AMT);
        ssmParam.put("siChrgTpNmBase", BASE);
        ssmParam.put("siChrgTpNmUsage", USAGE);
        ssmParam.put("siChrgTpNmAttachment", ATT);  //QC#20888 add
        ssmParam.put("tpFax", INV_SMRY_LINE_TP.FAX);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);     //QC#18223 add
        ssmParam.put("siInvTpCdMACH", SVC_INV_LINE_TP.MACHINE);  //QC#20888 add
        ssmParam.put("periodFrom", periodFrom);
        ssmParam.put("periodThru", periodThru);
        ssmParam.put("svcContrBaseBllgPk", svcContrBaseBllgPk);
        ssmParam.put("addlChrgInvTpCd", addlChrgInvTpCd);
        // START 06/20/2022 [QC#60205, ADD]
        ssmParam.put("invLineTpCd", invLineTpCd);
        ssmParam.put("contrDtlPk", dsContrDtlPk);
        // END   06/20/2022 [QC#60205, ADD]

        //QC#27069 Add Start
        String statementId;

        if (ZYPConstant.FLG_ON_1.equals(manInvFlg)) {
            statementId = "getMaintChrgDtlForAdditionalChargeNonDisp";
        } else {
            statementId = "getMaintChrgDtlForAdditionalChargeNonDisp";
        }
        //QC#27069 Add End

        return (Map<String, Object>) ssmBatchClient.queryObject(statementId, ssmParam);
    }
    
//    private boolean insertMaintChrgDtl(List<Map<String, Object>> resultMapList, INV_PRT_MAINT_MACHTMsg paramTmsg, String invTpCd) {
    private Integer insertMaintChrgDtl(List<Map<String, Object>> resultMapList, INV_PRT_MAINT_MACHTMsg paramTmsg, String invTpCd,
            // START 06/20/2022 [QC#60205, MOD]
            //INV_PRT_MAINT_SUB_TOTTMsg invPrtMaintSubTotTmsg, BigDecimal modelId, String manInvFlg, String svcInvMergeTpCd, String hasParent) {//QC#18223
            INV_PRT_MAINT_SUB_TOTTMsg invPrtMaintSubTotTmsg, BigDecimal modelId, String manInvFlg, String svcInvMergeTpCd, String hasParent, String invLineTpCd) {
            // END   06/20/2022 [QC#60205, MOD]

        INV_PRT_MAINT_CHRG_DTLTMsg invPrtMaintChrgDtlTmsg;
        List<INV_PRT_MAINT_CHRG_DTLTMsg> invPrtMaintChrgDtlList = new ArrayList<INV_PRT_MAINT_CHRG_DTLTMsg>();
        List<NWCB010001InvPrtMaintChagDtlBean> beanList = new ArrayList<NWCB010001InvPrtMaintChagDtlBean>(); // QC#29371 SRNO#6 2019/01/22 Add
        List<NWCB010001InvPrtMaintChagDtlBean> sumList = new ArrayList<NWCB010001InvPrtMaintChagDtlBean>();  // QC#29371 SRNO#6 2019/01/22 Add

        BigDecimal machTotPk = paramTmsg.invPrtMaintMachPk.getValue();
        String invNum = paramTmsg.invNum.getValue();

        for (Map<String, Object> resultMap : resultMapList) {

            String chrgTp = (String) resultMap.get(CHARGE_TYPE); //QC#18223 add
            if (!hasValue(chrgTp)) {
                continue;
            }

            //QC#29672 2019/01/30 Add Start
            // START 06/20/2022 [QC#60205, MOD]
            //if (ZYPConstant.FLG_OFF_N.equals(hasParent)){
            if (ZYPConstant.FLG_OFF_N.equals(hasParent) && !S21StringUtil.isEquals(SVC_INV_CHRG_TP.ADDITIONAL_CHARGE, (String) resultMap.get(SVC_INV_CHRG_TP_CD))){
            // END   06/20/2022 [QC#60205, MOD]
                if (ZYPConstant.FLG_ON_Y.equals((String)resultMap.get("HAS_PARENT")) && SVC_INV_MERGE_TP.SPLIT_BASE_CHARGE.equals((String)resultMap.get(SVC_INV_MERGE_TP_CD))){
                    continue;
                }
            }
            //QC#29672 2019/01/30 Add End

            String pridFr = getDtTxt((String) resultMap.get(PERIOD_FROM));
            String pridTh = getDtTxt((String) resultMap.get(PERIOD_THRU));
//            String amountTxt = setNegate((String) resultMap.get(AMOUNT_TXT), invTpCd); // unused
            BigDecimal amount = (BigDecimal) resultMap.get(AMOUNT); // QC#29371 SRNO#6 2019/01/22 Add

            //QC#25910
            if (SVC_INV_CHRG_TP.BASE_CHARGE.equals((String) resultMap.get("SVC_INV_CHRG_TP_CD"))) {
                String svcContrBaseBllgPkComma = (String) resultMap.get("SVC_CONTR_BASE_BLLG_PK_COMMA");  //Base line
                if (hasValue(svcContrBaseBllgPkComma)) {
                    String[] svcContrBaseBllgPkArray = svcContrBaseBllgPkComma.split(",");
                    if (svcContrBaseBllgPkArray != null) {
                        for (String svcContrBaseBllgPkString : svcContrBaseBllgPkArray) {
                            if (hasValue(svcContrBaseBllgPkString)) {
                                BigDecimal svcContrBaseBllgPk = new BigDecimal(svcContrBaseBllgPkString);
                                //QC#27069 Mod Start
                                Map<String, Object> addChrgNonDispMap = getMaintChrgDtlForAdditionalChargeNonDisp(invPrtMaintSubTotTmsg, 
                                        paramTmsg, modelId, (String) resultMap.get(PERIOD_FROM), (String) resultMap.get(PERIOD_THRU), 
                                        // START 06/20/2022 [QC#60205, MOD]
                                        //svcContrBaseBllgPk, ADDL_CHRG_INV_TP.BASE, manInvFlg);
                                        svcContrBaseBllgPk, ADDL_CHRG_INV_TP.BASE, manInvFlg, invLineTpCd, (BigDecimal) resultMap.get("DS_CONTR_DTL_PK"));
                                        // END   06/20/2022 [QC#60205, MOD]
                                //Map<String, Object> addChrgNonDispMap = getMaintChrgDtlForAdditionalChargeNonDisp(invPrtMaintSubTotTmsg, 
                                //        paramTmsg, modelId, (String) resultMap.get(PERIOD_FROM), (String) resultMap.get(PERIOD_THRU), 
                                //        svcContrBaseBllgPk, ADDL_CHRG_INV_TP.BASE);
                                //QC#27069 Mod End
                                BigDecimal baseTotAmt = BigDecimal.ZERO;
                                if (resultMap.get("AMOUNT") != null) {
                                    baseTotAmt = (BigDecimal) resultMap.get("AMOUNT");
                                }
                                BigDecimal addChrgNonDispAmt = BigDecimal.ZERO;
                                if (addChrgNonDispMap != null && addChrgNonDispMap.get("AMOUNT") != null) {
                                    addChrgNonDispAmt = (BigDecimal) addChrgNonDispMap.get("AMOUNT");
                                    baseTotAmt = baseTotAmt.add(addChrgNonDispAmt);
//                                    amountTxt = fmtAmtFromNumToStrInvTp(baseTotAmt, invTpCd, (String) resultMap.get(CCY_SGN_TXT)); // unused
                                    amount = baseTotAmt; // QC#29371 SRNO#6 2019/01/22 Add
                                }
                            }
                        }
                    }
                }
            }

            // QC#29371 2019/01/22 SRNO#6  Add Start
            NWCB010001InvPrtMaintChagDtlBean bean = new NWCB010001InvPrtMaintChagDtlBean();
            bean.setGlblCmpyCd(glblCmpyCd);
            bean.setInvPrtMaintMachPk(machTotPk);
            bean.setInvNum(invNum);
            bean.setChrgTypeCd((String) resultMap.get(SVC_INV_CHRG_TP_CD));
            bean.setInvChrgTpNm((String) resultMap.get(CHARGE_TYPE));
            bean.setBllgPerFromDtTxt(pridFr);
            bean.setBllgPerThruDtTxt(pridTh);
            bean.setLineTotAmt(amount);
            bean.setDplySpclChrgFlg((String) resultMap.get(SPECIAL_CHARGE_FLG));
            bean.setCcySgnTxt((String) resultMap.get(CCY_SGN_TXT));
            bean.setType((String) resultMap.get(TYPE));
            bean.setSvcMachMstrPK((BigDecimal) resultMap.get(SVC_MACH_MSTR_PK));
            bean.setDsContrCratTpCd((String) resultMap.get(DS_CONTR_CRAT_TP_CD));
            bean.setSvcInvMergeTpCd((String) resultMap.get(SVC_INV_MERGE_TP_CD));
            bean.setSvcPgmTpCd((String) resultMap.get(SVC_PGM_TP_CD));
            // Add QC#56943
            bean.setHasParent((String) resultMap.get("HAS_PARENT"));
            beanList.add(bean);
            // QC#29371 2019/01/22 SRNO#6 Add End

            // QC#29371 2019/01/22 SRNO#6 Del Start
            // SET INV_PRT_FLEET_MACH
            // invPrtMaintChrgDtlTmsg = new INV_PRT_MAINT_CHRG_DTLTMsg();
            // BigDecimal chrgDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_MAINT_CHRG_DTL_SQ);

            // ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.glblCmpyCd, glblCmpyCd);
            // ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.invPrtMaintChrgDtlPk, chrgDtlPk);
            // ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.invPrtMaintMachPk, machTotPk);
            // ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.invNum, invNum);
            // ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.invChrgTpNm, (String) resultMap.get(CHARGE_TYPE));
            // ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.bllgPerFromDtTxt, pridFr);
            // ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.bllgPerThruDtTxt, pridTh);
            // ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.lineTotAmtTxt, amountTxt);
            // ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.dplySpclChrgFlg, (String) resultMap.get(SPECIAL_CHARGE_FLG));

            //invPrtMaintChrgDtlList.add(invPrtMaintChrgDtlTmsg);
            // QC#29371 2019/01/22 SRNO#6 Del End
        }
        // QC#52209 2019/07/31 Del Start
        // QC#29371 2019/01/22 SRNO#6 Add Start
        // Summary for Rental
        //for (NWCB010001InvPrtMaintChagDtlBean bean : beanList) {
        //    if (S21StringUtil.isEquals(SVC_PGM_TP.RENTALSPLIT, bean.getSvcPgmTpCd())) {
        //        NWCB010001InvPrtMaintChagDtlBean data = getRentalOriginal(bean, sumList);
        //        if (data != null) {
        //            data.setLineTotAmt(data.getLineTotAmt().add(bean.getLineTotAmt()));
        //        } else {
        //            sumList.add(bean);
        //        }
        //    } else {
        //        sumList.add(bean);
        //    }
        //}
        //beanList.clear();
        //beanList.addAll(sumList);
        //sumList.clear();
        // START 06/20/2022 [QC#60205, ADD]
        // Summary for Additional Charge
        List<NWCB010001InvPrtMaintChagDtlBean> sumAddlList = new ArrayList<NWCB010001InvPrtMaintChagDtlBean>();  // QC#29371 SRNO#6 2019/01/22 Add
        for (NWCB010001InvPrtMaintChagDtlBean bean : beanList) {
            if (!S21StringUtil.isEquals("3", bean.getType())) {
                continue;
            }
            if (S21StringUtil.isEquals(SVC_INV_MERGE_TP.SPLIT_BASE_CHARGE, bean.getSvcInvMergeTpCd())) {
                sumAddlList.add(bean);
                continue;
            }
            NWCB010001InvPrtMaintChagDtlBean data = getConfigAddl(bean, sumAddlList);
            if (data != null) {
                data.setLineTotAmt(data.getLineTotAmt().add(bean.getLineTotAmt()));
            } else {
                sumAddlList.add(bean);
            }
        }
        // END   06/20/2022 [QC#60205, ADD]
        // Summary for Accessory Charge
        for (NWCB010001InvPrtMaintChagDtlBean bean : beanList) {
            // START 06/20/2022 [QC#60205, ADD]
            if (S21StringUtil.isEquals("3", bean.getType())) {
                continue;
            }
            // END   06/20/2022 [QC#60205, ADD]
            if (S21StringUtil.isEquals(SVC_INV_CHRG_TP.BASE_CHARGE, bean.getChrgTypeCd()) && S21StringUtil.isEquals(SVC_INV_MERGE_TP.MERGE_INTO_BASE_CHARGE, bean.getSvcInvMergeTpCd())) {
                NWCB010001InvPrtMaintChagDtlBean data = getMainMachine(bean, sumList);
                if (data != null) {
                    data.setLineTotAmt(data.getLineTotAmt().add(bean.getLineTotAmt()));
                } else {
                    // Add QC#56943
                    if (ZYPConstant.FLG_OFF_N.equals(bean.getHasParent())){
                        sumList.add(bean);
                    }
                }
            } else {
                sumList.add(bean);
            }
        }
        beanList.clear();
        // START 06/20/2022 [QC#60205, ADD]
        if (sumAddlList.size() > 0) {
            beanList.addAll(sumAddlList);
        }
        // END   06/20/2022 [QC#60205, ADD]
        beanList.addAll(sumList);

        // QC#29672 2019/01/26 Mod Start
        //sumList.clear();
        ////Summary for Print Level(TYPE ,INV_NUM ,CCY_SGN_TXT ,CHARGE_TYPE ,PERIOD_FROM ,PERIOD_THRU ,SVC_INV_CHRG_TP_CD)
        //Map<List<Object>, NWCB010001InvPrtMaintChagDtlBean> map = new HashMap<List<Object>, NWCB010001InvPrtMaintChagDtlBean>();
        //NWCB010001InvPrtMaintChagDtlBean data;
        //for (NWCB010001InvPrtMaintChagDtlBean bean : beanList) {
        //    List<Object> key = new ArrayList<Object>();
        //    key.add(bean.getType());
        //    key.add(bean.getCcySgnTxt());
        //    key.add(bean.getDplySpclChrgFlg());
        //    key.add(bean.getInvChrgTpNm());
        //    key.add(bean.getBllgPerFromDtTxt());
        //    key.add(bean.getBllgPerThruDtTxt());
        //    key.add(bean.getChrgTypeCd());
        //    data = map.get(key);
        //    if (data == null) {
        //        map.put(key, bean);
        //    } else {
        //        data.setLineTotAmt(data.getLineTotAmt().add(bean.getLineTotAmt()));
        //    }
        //}

        //for (Map.Entry<List<Object>, NWCB010001InvPrtMaintChagDtlBean> entry : map.entrySet()) {
        //    NWCB010001InvPrtMaintChagDtlBean bean = entry.getValue();
        for (NWCB010001InvPrtMaintChagDtlBean bean : beanList) {
        // QC#29672 2019/01/26 Mod End
            invPrtMaintChrgDtlTmsg = new INV_PRT_MAINT_CHRG_DTLTMsg();
            BigDecimal chrgDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_MAINT_CHRG_DTL_SQ);

            ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.glblCmpyCd, bean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.invPrtMaintChrgDtlPk, chrgDtlPk);
            ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.invPrtMaintMachPk, bean.getInvPrtMaintMachPk());
            ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.invNum, invNum);
            ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.invChrgTpNm, bean.getInvChrgTpNm());
            ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.bllgPerFromDtTxt, bean.getBllgPerFromDtTxt());
            ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.bllgPerThruDtTxt, bean.getBllgPerThruDtTxt());

            // QC#50148 2019/05/09 Mod Start
            if (S21StringUtil.isEquals(SVC_INV_CHRG_TP.METER_CHARGE, bean.getChrgTypeCd()) && !ZYPConstant.FLG_ON_1.equals(manInvFlg)) { // QC#29371 upd
            // if (S21StringUtil.isEquals(SVC_INV_CHRG_TP.METER_CHARGE, bean.getChrgTypeCd())) { // QC#29371 upd
            // QC#50148 2019/05/09 Mod End
                invPrtMaintChrgDtlTmsg.lineTotAmtTxt.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.lineTotAmtTxt, fmtAmtFromNumToStrInvTp(bean.getLineTotAmt(), invTpCd, bean.getCcySgnTxt()));
            }
            ZYPEZDItemValueSetter.setValue(invPrtMaintChrgDtlTmsg.dplySpclChrgFlg, bean.getDplySpclChrgFlg());
            invPrtMaintChrgDtlList.add(invPrtMaintChrgDtlTmsg);
        }
        // QC#29371 2019/01/22 SRNO#6 Add End

        if (invPrtMaintChrgDtlList.size() > 0) {
            // INSERT
            int result = EZDFastTBLAccessor.insert(invPrtMaintChrgDtlList
                    .toArray(new INV_PRT_MAINT_CHRG_DTLTMsg[invPrtMaintChrgDtlList.size()]));
            if (result != invPrtMaintChrgDtlList.size()) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_MAINT_CHRG_DTLTMsg().getTableName(), String.valueOf(machTotPk)));
//                return false;
                return null;    //QC#18223
            }
//            normCnt += result;
        }
//        return true;
        return invPrtMaintChrgDtlList.size();    //QC#18223
    }
    // QC#52209 2019/07/31 Del Start
    // QC#29371 2019/01/22 SRNO#6 Add Start
    //private NWCB010001InvPrtMaintChagDtlBean getRentalOriginal(NWCB010001InvPrtMaintChagDtlBean bean, List<NWCB010001InvPrtMaintChagDtlBean> list) {
    //    NWCB010001InvPrtMaintChagDtlBean tmp = null;
    //    for (NWCB010001InvPrtMaintChagDtlBean data : list) {
    //        if (data.getSvcMachMstrPK().compareTo(bean.getSvcMachMstrPK()) == 0) {
    //            if (S21StringUtil.isEquals(DS_CONTR_CRAT_TP.SHELL, data.getDsContrCratTpCd())) {
    //                return data;
    //            }
    //            if (tmp == null) {
    //                tmp = data;
    //            } else {
    //                if (tmp.getInvPrtMaintMachPk().compareTo(data.getInvPrtMaintMachPk()) < 0) {
    //                    tmp = data;
    //                }
    //            }
    //        }
    //    }
    //    return tmp;
    //}
    // QC#52209 2019/07/31 Del End
    private NWCB010001InvPrtMaintChagDtlBean getMainMachine(NWCB010001InvPrtMaintChagDtlBean bean, List<NWCB010001InvPrtMaintChagDtlBean> list) {
        for (NWCB010001InvPrtMaintChagDtlBean data : list) {
            if (data.getInvPrtMaintMachPk().compareTo(bean.getInvPrtMaintMachPk()) == 0) {
                if (S21StringUtil.isEquals("1", data.getType())) {
                    return data;
                }
            }
        }
        return null;
    }
    // QC#29371 2019/01/22 SRNO#6 Add End
    // START 06/20/2022 [QC#60205, ADD]
    private NWCB010001InvPrtMaintChagDtlBean getConfigAddl(NWCB010001InvPrtMaintChagDtlBean bean, List<NWCB010001InvPrtMaintChagDtlBean> list) {
        for (NWCB010001InvPrtMaintChagDtlBean data : list) {
            if (data.getInvPrtMaintMachPk().compareTo(bean.getInvPrtMaintMachPk()) == 0) {
                if (S21StringUtil.isEquals("3", data.getType())) {
                    return data;
                }
            }
        }
        return null;
    }
    // END   06/20/2022 [QC#60205, ADD]

  //QC#18223 mod Start
//  private boolean setMaintMtr(INV_PRT_MAINT_SUB_TOTTMsg invPrtMaintSubTotTmsg, INV_PRT_MAINT_MACHTMsg invPrtMaintMachTmsg,
  private Integer setMaintMtr(INV_PRT_MAINT_SUB_TOTTMsg invPrtMaintSubTotTmsg, INV_PRT_MAINT_MACHTMsg invPrtMaintMachTmsg,
          BigDecimal modelId, String invTpCd, String meterFlg, String manInvFlg) {

      // GET Maint Mtr
      //QC#27069 Mod Start
      List<Map<String, Object>> ssmMaintResult = getMaintMtr(invPrtMaintSubTotTmsg, invPrtMaintMachTmsg, modelId, meterFlg, manInvFlg);
      //List<Map<String, Object>> ssmMaintResult = getMaintMtr(invPrtMaintSubTotTmsg, invPrtMaintMachTmsg, modelId, meterFlg);
      //QC#27069 Mod End
      if (ssmMaintResult.size() == 0) {
          return 0;
      }
      // INSERT INV_PRT_MAINT_MTR,_XS_MTR
//      boolean result = insertMaintMtr(ssmMaintResult, invPrtMaintMachTmsg, invTpCd);
      // START 2022/11/11 [QC#60802, MOD]
      //Integer maintMtrCnt = insertMaintMtr(ssmMaintResult, invPrtMaintMachTmsg, invTpCd, meterFlg);
      Integer maintMtrCnt = insertMaintMtr(ssmMaintResult, invPrtMaintMachTmsg, invTpCd, meterFlg, manInvFlg);
      // END 2022/11/11 [QC#60802, MOD]
//      if (!result) {
//          return false;
//      }
//      return true;
      return maintMtrCnt;
  }
//QC#18223 mod End

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getMaintMtr(INV_PRT_MAINT_SUB_TOTTMsg subTotTmsg, INV_PRT_MAINT_MACHTMsg machTmsg, BigDecimal modelId,
            String meterFlg, String manInvFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", subTotTmsg.invNum.getValue());
        ssmParam.put("shipToCust", subTotTmsg.shipToCustCd.getValue());
        ssmParam.put("poNo", subTotTmsg.custIssPoNum.getValue());
        ssmParam.put("custCodes1", subTotTmsg.firstBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes2", subTotTmsg.scdBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes3", subTotTmsg.thirdBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes4", subTotTmsg.frthBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes5", subTotTmsg.fifthBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes6", subTotTmsg.sixthBllgAttrbValTxt.getValue());
        ssmParam.put("contrPk", machTmsg.dsContrPk.getValue());
        ssmParam.put("machPk", machTmsg.svcMachMstrPk.getValue());
        ssmParam.put("modelId", modelId);
        ssmParam.put("svcPgm", machTmsg.svcPgmNm.getValue());
        ssmParam.put("invChrgTpCd", SVC_INV_CHRG_TP.METER_CHARGE);
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("meterFlg", meterFlg);
        ssmParam.put("rateFormat", FM_RATE);
        ssmParam.put("qtyFormat", FM_QTY);
        ssmParam.put("amtFormat", FM_AMT);
        // QC#28894 Add Start
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        // QC#28894 Add End

        //QC#27069 Add Start
        String statementId;

        if (ZYPConstant.FLG_ON_1.equals(manInvFlg)) {
            statementId = "getMaintMtr";
        } else {
            statementId = "getMaintMtr";
        }
        //QC#27069 Add End

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList(statementId, ssmParam);
    }

  //QC#18223 mod Start
//  private boolean insertMaintMtr(List<Map<String, Object>> resultMapList, INV_PRT_MAINT_MACHTMsg paramTmsg, String invTpCd) {
  // START 2022/11/11 [QC#60802, MOD]
  //private Integer insertMaintMtr(List<Map<String, Object>> resultMapList, INV_PRT_MAINT_MACHTMsg paramTmsg, String invTpCd, String meterFlg) {
  private Integer insertMaintMtr(List<Map<String, Object>> resultMapList, INV_PRT_MAINT_MACHTMsg paramTmsg, String invTpCd, String meterFlg, String manInvFlg) {
  // END 2022/11/11 [QC#60802, MOD]

        boolean resultFlg = true;
        INV_PRT_MAINT_MTRTMsg invPrtMaintMtrTmsg;
        List<INV_PRT_MAINT_MTRTMsg> invPrtMaintMtrList = new ArrayList<INV_PRT_MAINT_MTRTMsg>();

        BigDecimal machTotPk = paramTmsg.invPrtMaintMachPk.getValue();
        String invNum = paramTmsg.invNum.getValue();
        // START 2021/04/23 L.Mandanas [QC#58350, ADD]
        BigDecimal dsContrPk = paramTmsg.dsContrPk.getValue();
        BigDecimal rollOvrCntNxt;
        BigDecimal rollOvrCntCrrt ;
        BigDecimal rollOvrUsed;
        BigDecimal contrBllgSchdPk;
        BigDecimal actualUsage;
        BigDecimal allowance;
        // START 2022/03/29 L.Mandanas [QC#58350, ADD]
        BigDecimal multipliedUsage;
        // END 2022/03/29 L.Mandanas [QC#58350, ADD]
        List < Map < String, Object > > nxtRollovrResultList;
        List < Map < String, Object > > currRollovrResult;
        // END 2021/04/23 L.Mandanas [QC#58350, ADD]

        Map<BigDecimal, BigDecimal> dsContePkrMap = new HashMap<BigDecimal, BigDecimal>();
        Map<BigDecimal, BigDecimal> svcConteMtrBllgPkMap = new HashMap<BigDecimal, BigDecimal>();

        for (Map<String, Object> resultMap : resultMapList) {

            String mtrAmtTxt = setNegate((String) resultMap.get(METER_AMOUNT_TXT), invTpCd);

            // SET INV_PRT_FLEET_MACH
            invPrtMaintMtrTmsg = new INV_PRT_MAINT_MTRTMsg();
            BigDecimal mtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_MAINT_MTR_SQ);
            // START 2021/04/23 L.Mandanas [QC#58350, ADD]
            rollOvrCntNxt = BigDecimal.ZERO;
            rollOvrCntCrrt = BigDecimal.ZERO;
            rollOvrUsed = BigDecimal.ZERO;
            contrBllgSchdPk = null;
            nxtRollovrResultList = null;
            currRollovrResult = null;
            // START 2021/09/23 L.Mandanas [QC#58350, ADD]
            boolean hasFreeCopy = false;
            // END 2021/09/23 L.Mandanas [QC#58350, ADD]
            actualUsage = new BigDecimal(((String) resultMap.get(
                            COPIES_MADE_TXT)).replaceAll(",", ""));
            allowance = new BigDecimal(((String) resultMap.get(
                            ALLOWANCE_TXT)).replaceAll(",", ""));
            nxtRollovrResultList = getNonFleetNextRolloverCount(
                        (BigDecimal) resultMap.get(DS_CONTRACT_BLLG_MTR_PK),
                        (BigDecimal) resultMap.get(SVC_CONTR_MTR_BLLG_PK),
                        invNum, dsContrPk);
            // START 2022/03/29 L.Mandanas [QC#58350, ADD]
            multipliedUsage = (BigDecimal) resultMap.get("MULTIPLIED_USAGE");
            // END 2022/03/29 L.Mandanas [QC#58350, ADD]

            // START 2022/06/20 [QC#58350, MOD]
            if (nxtRollovrResultList != null && nxtRollovrResultList.size() > 0) {
            //    if (nxtRollovrResultList.size() > 0) {
            // END   2022/06/20 [QC#58350, MOD]
                // START 2021/09/23 L.Mandanas [QC#58350, ADD]
                if ((nxtRollovrResultList.get(0).get(USG_FREE_COPY_CNT) != null
                      && !BigDecimal.ZERO.equals(
                          nxtRollovrResultList.get(0).get(USG_FREE_COPY_CNT)))
                      || (nxtRollovrResultList.get(0).get(FREE_COPY_CNT) != null
                      && !BigDecimal.ZERO.equals(
                          nxtRollovrResultList.get(0).get(FREE_COPY_CNT)))) {
                    hasFreeCopy = true;
                }
                // END 2021/09/23 L.Mandanas [QC#58350, ADD]
                contrBllgSchdPk = (BigDecimal) nxtRollovrResultList.get(0)
                            .get(DS_CONTR_BLLG_SCHD_PK);
                // START 2021/09/23 L.Mandanas [QC#58350, MOD]
                  //if (!nxtRollovrResultList.isEmpty()
                if (!hasFreeCopy) {
                // END 2021/09/23 L.Mandanas [QC#58350, MOD]
                    if (nxtRollovrResultList.get(0)
                          .get(ROLL_OVER_CNT) != null) {
                      rollOvrCntNxt = (BigDecimal) nxtRollovrResultList.get(0)
                                  .get(ROLL_OVER_CNT);
                    }
                // START 2021/09/23 L.Mandanas [QC#58350, ADD]
                    if (hasValue(contrBllgSchdPk)) {
                        currRollovrResult =
                            getCurrentRolloverCount(contrBllgSchdPk);
                        if (!currRollovrResult.isEmpty()
                              && currRollovrResult.get(0)
                                  .get(ROLL_OVER_CNT) != null) {
                              rollOvrCntCrrt = (BigDecimal) currRollovrResult
                                  .get(0).get(ROLL_OVER_CNT);
                        }
                    }
                    // START 2022/03/29 L.Mandanas [QC#58350, MOD]
                       //rollOvrUsed = computeRolloverUsed(rollOvrCntCrrt,
                       //        actualUsage, allowance);
                    rollOvrUsed = computeRolloverUsed(rollOvrCntCrrt,
                            multipliedUsage, allowance);
                    // END 2022/03/29 L.Mandanas [QC#58350, MOD]
                }
                else {
                    if (nxtRollovrResultList.get(0)
                            .get(FREE_COPY_CNT) != null) {
                        rollOvrCntNxt = (BigDecimal) nxtRollovrResultList
                                .get(0).get(FREE_COPY_CNT);
                    }
                    if (nxtRollovrResultList.get(0)
                        .get(USG_FREE_COPY_CNT) != null) {
                        rollOvrUsed = (BigDecimal) nxtRollovrResultList.get(0)
                                   .get(USG_FREE_COPY_CNT);
                    }
                   rollOvrCntCrrt = rollOvrUsed.add(rollOvrCntNxt);
                }
                // END 2021/09/23 L.Mandanas [QC#58350, ADD]
            }
            // START 2021/09/23 L.Mandanas [QC#58350, DEL]
//            if (hasValue(contrBllgSchdPk)) {
//                currRollovrResult = getCurrentRolloverCount(contrBllgSchdPk);
//                if (!currRollovrResult.isEmpty()
//                      && currRollovrResult.get(0).get(ROLL_OVER_CNT) != null) {
//                      rollOvrCntCrrt = (BigDecimal) currRollovrResult.get(0)
//                                    .get(ROLL_OVER_CNT);
//                    }
//            }
//            rollOvrUsed = computeRolloverUsed(rollOvrCntCrrt,
//                    actualUsage, allowance);

            // END 2021/09/23 L.Mandanas [QC#58350, DEL]
            // END 2021/04/23 L.Mandanas [QC#58350, ADD]

            ZYPEZDItemValueSetter.setValue(invPrtMaintMtrTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invPrtMaintMtrTmsg.invPrtMaintMtrPk, mtrPk);
            ZYPEZDItemValueSetter.setValue(invPrtMaintMtrTmsg.invPrtMaintMachPk, machTotPk);
            ZYPEZDItemValueSetter.setValue(invPrtMaintMtrTmsg.invNum, invNum);
            ZYPEZDItemValueSetter.setValue(invPrtMaintMtrTmsg.mtrLbNm, cutOffString((String) resultMap.get(METER_TYPE), MTR_LB_CUT_OFF_LEN));
            ZYPEZDItemValueSetter.setValue(invPrtMaintMtrTmsg.prevTotCopyQtyTxt, (String) resultMap.get(START_READ));
            ZYPEZDItemValueSetter.setValue(invPrtMaintMtrTmsg.totCopyQtyTxt, (String) resultMap.get(END_READ));
            ZYPEZDItemValueSetter.setValue(invPrtMaintMtrTmsg.testCopyQtyTxt, (String) resultMap.get(TEST_COPIES));
            ZYPEZDItemValueSetter.setValue(invPrtMaintMtrTmsg.bllgCopyQtyTxt, (String) resultMap.get(COPIES_MADE_TXT));
            ZYPEZDItemValueSetter.setValue(invPrtMaintMtrTmsg.contrMtrMultRateTxt, (String) resultMap.get(MULTIPLIER_TXT));
            ZYPEZDItemValueSetter.setValue(invPrtMaintMtrTmsg.mlyCopyInclPrcQtyTxt, (String) resultMap.get(ALLOWANCE_TXT));
            ZYPEZDItemValueSetter.setValue(invPrtMaintMtrTmsg.mtrCopyQtyTxt, (String) resultMap.get(BILLABLE_COPIES));
            ZYPEZDItemValueSetter.setValue(invPrtMaintMtrTmsg.lineTotAmtTxt, mtrAmtTxt);

            // START 2021/04/23 L.Mandanas [QC#58350, ADD]
            ZYPEZDItemValueSetter.setValue(
                    invPrtMaintMtrTmsg.nextRollOverQtyDplyTxt,
                    NumberFormat.getNumberInstance(Locale.US)
                                .format(rollOvrCntNxt));
            ZYPEZDItemValueSetter.setValue(
                    invPrtMaintMtrTmsg.rollOverQtyDplyTxt,
                    NumberFormat.getNumberInstance(Locale.US)
                                .format(rollOvrCntCrrt));
            ZYPEZDItemValueSetter.setValue(
                    invPrtMaintMtrTmsg.rollOverUsedQtyDplyTxt,
                    NumberFormat.getNumberInstance(Locale.US)
                                .format(rollOvrUsed));
            // END 2021/04/23 L.Mandanas [QC#58350, ADD]

            dsContePkrMap.put(mtrPk, (BigDecimal) resultMap.get(DS_CONTRACT_BLLG_MTR_PK));
            svcConteMtrBllgPkMap.put(mtrPk, (BigDecimal) resultMap.get(SVC_CONTR_MTR_BLLG_PK));
            
            invPrtMaintMtrList.add(invPrtMaintMtrTmsg);
        }

        if (invPrtMaintMtrList.size() > 0) {
            // INSERT
            int result = EZDFastTBLAccessor.insert(invPrtMaintMtrList
                    .toArray(new INV_PRT_MAINT_MTRTMsg[invPrtMaintMtrList.size()]));
            if (result != invPrtMaintMtrList.size()) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_MAINT_MTRTMsg().getTableName(), String.valueOf(machTotPk)));
//                return false;
                return null;
            }
//            normCnt += result;
        }
        // START 2018/07/25 E.Kameishi [QC#27068,ADD]
        // set XS_MTR
        // START 2022/11/11 [QC#60802, MOD]
        //resultFlg = setMaintXsMtr(invPrtMaintMtrList, dsContePkrMap, svcConteMtrBllgPkMap, meterFlg);
        resultFlg = setMaintXsMtr(invPrtMaintMtrList, dsContePkrMap, svcConteMtrBllgPkMap, meterFlg, manInvFlg);
        // END 2022/11/11 [QC#60802, MOD]
        // END 2018/07/25 E.Kameishi [QC#27068,ADD]
        if (!resultFlg) {
//            return false;
            return null;
        }

//        return true;
        return invPrtMaintMtrList.size();
    }
//QC#18223 mod End
    //START 2022/11/11 [QC#60802, MOD]
    //private boolean setMaintXsMtr(List<INV_PRT_MAINT_MTRTMsg> invPrtMaintMtrTmsgList, Map<BigDecimal, BigDecimal> dsContePkrMap, Map<BigDecimal, BigDecimal> svcConteMtrBllgPkMap, String meterFlg) {
    private boolean setMaintXsMtr(List<INV_PRT_MAINT_MTRTMsg> invPrtMaintMtrTmsgList, Map<BigDecimal, BigDecimal> dsContePkrMap, Map<BigDecimal, BigDecimal> svcConteMtrBllgPkMap, String meterFlg, String manInvFlg) {
    // END 2022/11/11 [QC#60802, MOD]

        boolean result = true;
        for (INV_PRT_MAINT_MTRTMsg maintmtrTmsg : invPrtMaintMtrTmsgList) {
            BigDecimal mtrPk = maintmtrTmsg.invPrtMaintMtrPk.getValue();
            BigDecimal contrBllgPk = dsContePkrMap.get(mtrPk);
            BigDecimal svcContrMtrBllgPk = svcConteMtrBllgPkMap.get(mtrPk);
            
            // START 2022/06/20 [QC#58350, ADD]
            // START 2022/11/11 [QC#60802, MOD]
            //if (!ZYPCommonFunc.hasValue(contrBllgPk) || !ZYPCommonFunc.hasValue(svcContrMtrBllgPk) || !ZYPCommonFunc.hasValue(meterFlg)) {
            if (!ZYPConstant.FLG_OFF_0.equals(manInvFlg) && (!ZYPCommonFunc.hasValue(contrBllgPk) || !ZYPCommonFunc.hasValue(svcContrMtrBllgPk) || !ZYPCommonFunc.hasValue(meterFlg))) {
            // END 2022/11/11 [QC#60802, MOD]
                return true;
            }
            // END   2022/06/20 [QC#58350, ADD]
            
            // START 2018/07/25 E.Kameishi [QC#27068,ADD]
            // GET Fleet SmryXs
            List<Map<String, Object>> ssmMaintResult = getMaintXsMtrEx(maintmtrTmsg.invNum.getValue(), contrBllgPk, svcContrMtrBllgPk, meterFlg);
            // END 2018/07/25 E.Kameishi [QC#27068,ADD]

            if (ssmMaintResult.size() == 1) {
                // UPDATE INV_PRT_FLEET_SMRY_XS
                result = updateMaintMtr(ssmMaintResult, mtrPk);
            } else if (ssmMaintResult.size() >= 2) {
                // INSERT INV_PRT_FLEET_SMRY_XS
                result = insertMaintXsMtr(ssmMaintResult, maintmtrTmsg);
            }
            if (!result) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getMaintXsMtrEx(String svcInvNum, BigDecimal contrBllgPk, BigDecimal svcContrMtrBllgPk, String meterFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgMtrPk", contrBllgPk);
        ssmParam.put("svcContrMtrBllgPk", svcContrMtrBllgPk);
        ssmParam.put("svcInvNum", svcInvNum);
        ssmParam.put("qtyFormat", FM_QTY);
        ssmParam.put("rateFormat", FM_RATE2);
        // START 2018/07/25 E.Kameishi [QC#27068,ADD]
        ssmParam.put("meterFlg", meterFlg);
        // END 2018/07/25 E.Kameishi [QC#27068,ADD]
        // START 2019/06/04  [QC#50689,ADD]
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        // END 2019/06/04 [QC#50689,ADD]

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSmryXsEx", ssmParam);
    }

    private boolean updateMaintMtr(List<Map<String, Object>> resultMapList, BigDecimal mtrPk) {

        Map<String, Object> resultMap = resultMapList.get(0);
        if (resultMap.get(RATE) == null) {
            return true;
        }
        
        INV_PRT_MAINT_MTRTMsg invPrtMaintMtrTmsg = new INV_PRT_MAINT_MTRTMsg();
        invPrtMaintMtrTmsg.glblCmpyCd.setValue(glblCmpyCd);
        invPrtMaintMtrTmsg.invPrtMaintMtrPk.setValue(mtrPk);

        INV_PRT_MAINT_MTRTMsg resultMsg = (INV_PRT_MAINT_MTRTMsg) EZDTBLAccessor.findByKeyForUpdate(invPrtMaintMtrTmsg);

        if (resultMsg == null) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(resultMsg.xsMtrAmtRateTxt, nullConvEmp((String) resultMap.get(RATE)));
        EZDTBLAccessor.update(resultMsg);

        return true;
    }

    private boolean insertMaintXsMtr(List<Map<String, Object>> resultMapList, INV_PRT_MAINT_MTRTMsg invPrtMaintMtrTmsg) {

        INV_PRT_MAINT_XS_MTRTMsg invPrtMaintXsMtrTmsg;
        List<INV_PRT_MAINT_XS_MTRTMsg> invPrtMaintXsMtrList = new ArrayList<INV_PRT_MAINT_XS_MTRTMsg>();

        BigDecimal mtrPk = invPrtMaintMtrTmsg.invPrtMaintMtrPk.getValue();
        String invNum = invPrtMaintMtrTmsg.invNum.getValue();

        for (Map<String, Object> resultMap : resultMapList) {

            // SET INV_PRT_FLEET_SMRY_MTR
            invPrtMaintXsMtrTmsg = new INV_PRT_MAINT_XS_MTRTMsg();
            BigDecimal xsMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_MAINT_XS_MTR_SQ);

            ZYPEZDItemValueSetter.setValue(invPrtMaintXsMtrTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invPrtMaintXsMtrTmsg.invPrtMaintXsMtrPk, xsMtrPk);
            ZYPEZDItemValueSetter.setValue(invPrtMaintXsMtrTmsg.invPrtMaintMtrPk, mtrPk);
            ZYPEZDItemValueSetter.setValue(invPrtMaintXsMtrTmsg.xsMtrCopyQtyTxt, (String) resultMap.get(QTY));
            ZYPEZDItemValueSetter.setValue(invPrtMaintXsMtrTmsg.xsMtrAmtRateTxt, (String) resultMap.get(RATE));
            ZYPEZDItemValueSetter.setValue(invPrtMaintXsMtrTmsg.invNum, invNum);

            invPrtMaintXsMtrList.add(invPrtMaintXsMtrTmsg);

        }
        if (invPrtMaintXsMtrList.size() > 0) {
            // INSERT
            int result = EZDFastTBLAccessor.insert(invPrtMaintXsMtrList.toArray(new INV_PRT_MAINT_XS_MTRTMsg[invPrtMaintXsMtrList
                    .size()]));
            if (result != invPrtMaintXsMtrList.size()) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_MAINT_XS_MTRTMsg().getTableName(), String.valueOf(mtrPk)));
                return false;
            }
//            normCnt += result;
        }
        return true;
    }

    //private boolean setSaleSplyInfo(ResultSet rs, boolean isEasyPac1Flg, Map<String, Object> preConslBillInfoForSalesMap, Map<String, String> billToCustInfo, boolean isSupplyOrderFlg, INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray)   //QC#26846
    private boolean setSaleSplyInfo(ResultSet rs, 
                                      boolean isEasyPac1Flg, 
                                      Map<String, Object> preConslBillInfoForSalesMap, 
                                      Map<String, String> billToCustInfo, 
                                      boolean isSupplyOrderFlg, 
                                      boolean isNoConfigOrderFlg, // QC#31177 2019/04/16 Add
                                      INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray, 
                                      boolean isBillablePartsOrderFlg) 
    throws SQLException, NWCB010001BusinessException {

        String invNum = rs.getString(INV_NUM);
        String taxDtlFlg = rs.getString(TAX_DETAIL_FLG);
        String dtlDplyFlg = rs.getString(DTL_DPLY_FLG);
        String cpoOrdNum = rs.getString(CPO_ORD_NUM);

        // GET SALE& supply Info
        //List<Map<String, Object>> ssmSaleSplyResult = getSaleSplySubTotal(invNum, taxDtlFlg, dtlDplyFlg, cpoOrdNum, isSupplyOrderFlg);  //QC#26846
        List<Map<String, Object>> ssmSaleSplyResult = getSaleSplySubTotal(invNum, taxDtlFlg, dtlDplyFlg, cpoOrdNum, isSupplyOrderFlg, isBillablePartsOrderFlg);
        if (ssmSaleSplyResult.size() == 0) {
            //NWCM0112E=It failed to get [@].(@)
            S21InfoLogOutput.println(NWCM0112E, toArray("SaleSplySubTotal", invNum));
            return false;
        }
        // INSERT SALE& supply Info
        //boolean result = setSaleSply(ssmSaleSplyResult, rs, isEasyPac1Flg, dtlDplyFlg, preConslBillInfoForSalesMap, billToCustInfo, isSupplyOrderFlg, invPrtCtrlTmsgArray);  //QC#26846
        // boolean result = setSaleSply(ssmSaleSplyResult, rs, isEasyPac1Flg, dtlDplyFlg, preConslBillInfoForSalesMap, billToCustInfo, isSupplyOrderFlg, invPrtCtrlTmsgArray, isBillablePartsOrderFlg);
        boolean result = setSaleSply(ssmSaleSplyResult, rs, isEasyPac1Flg, dtlDplyFlg, preConslBillInfoForSalesMap, billToCustInfo, isSupplyOrderFlg, isNoConfigOrderFlg, invPrtCtrlTmsgArray, isBillablePartsOrderFlg);
        if (!result) {
            S21InfoLogOutput.println(NWCM0112E, toArray("SaleSplyDetail", invNum));
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    //private List<Map<String, Object>> getSaleSplySubTotal(String invNum, String taxDtlFlg, String dtlDplyFlg, String cpoOrdNum, boolean isSupplyOrderFlg) {  //QC#26846
    private List<Map<String, Object>> getSaleSplySubTotal(String invNum, String taxDtlFlg, String dtlDplyFlg, String cpoOrdNum, boolean isSupplyOrderFlg, boolean isBillablePartsOrderFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        if (hasValue(cpoOrdNum)) {
            ssmParam.put("cpoOrdNum", cpoOrdNum);
        }
        ssmParam.put("taxDtlFlg", taxDtlFlg);
        ssmParam.put("dtlDplyFlg", dtlDplyFlg);
        ssmParam.put("dsSlsTaxTpCd01", DS_SLS_TAX_TP.STATE_TAX);
        ssmParam.put("dsSlsTaxTpCd02", DS_SLS_TAX_TP.COUNTY_TAX);
        ssmParam.put("dsSlsTaxTpCd03", DS_SLS_TAX_TP.CITY_TAX);
        ssmParam.put("dsSlsTaxTpCd11", DS_SLS_TAX_TP.FREIGHT_STATE_TAX);
        ssmParam.put("dsSlsTaxTpCd12", DS_SLS_TAX_TP.FREIGHT_COUNTY_TAX);
        ssmParam.put("dsSlsTaxTpCd13", DS_SLS_TAX_TP.FREIGHT_CITY_TAX);
        //ssmParam.put("kome", "*"); // 2018/06/06 QC#21841-1 Del
        ssmParam.put("prodCd", "K%");
        //QC#25026
        //ssmParam.put("coaMdseTpCd", COA_PROJ.SOFTWARE);
        ssmParam.put("coaMdseTpCd", COA_PROJ_CD_ZZZ);
        ssmParam.put("ordCatgNmSale", SALE);
        ssmParam.put("ordCatgNmSply", SUPPLY);
        ssmParam.put("ordCatgNmParts", PARTS);  //QC#26846
        ssmParam.put("copier", "-" + COPIER);
        ssmParam.put("nonCopier", "-" + NON_COPIER);
        ssmParam.put("qtyFormat", FM_QTY);
        ssmParam.put("amtFormat", FM_AMT);
        ssmParam.put("shpgStsCd", SHPG_STS.VALIDATED);
        ssmParam.put("isSupplyOrderFlg", isSupplyOrderFlg);
        ssmParam.put("isBillablePartsOrderFlg", isBillablePartsOrderFlg);  //QC#26846
        // START 2018/09/10 T.Ogura [QC#27999,DEL]
//        ssmParam.put("invLineCatgCd", INV_LINE_CATG.FREIGHT); // QC#21841 add
        // END   2018/09/10 T.Ogura [QC#27999,DEL]

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSalsSplySubTotal", ssmParam);
    }

    private boolean setSaleSply(List<Map<String, Object>> resultMapList, 
            ResultSet rs, 
            boolean isEasyPac1Flg, 
            String dtlDplyFlg, 
            Map<String, Object> preConslBillInfoForSalesMap, 
            Map<String, String> billToCustInfo,
            boolean isSupplyOrderFlg,
            boolean isNoConfigOrderFlg, // QC#31177 2019/04/16 Add
            INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray,
            boolean isBillablePartsOrderFlg) //QC#26846
            throws SQLException, NWCB010001BusinessException {

        boolean resultFlg = true;

        String invNum = rs.getString(INV_NUM);
        String invTpCd = rs.getString(INV_TP_CD);
        String ccySgnTxt = rs.getString(CCY_SGN_TXT);

        INV_PRT_SLS_PART_SUB_TOTTMsg invPrtSlsPartSubTotTmsg;
        List<INV_PRT_SLS_PART_SUB_TOTTMsg> invPrtSlsPartSubTotList = new ArrayList<INV_PRT_SLS_PART_SUB_TOTTMsg>();

        String invBolNum = "";
        String invBolNumPre = "";

        Map<String, Object> nextMap = new HashMap<String, Object>();
        Map<String, Object> preMap = new HashMap<String, Object>();

        BigDecimal freight = BigDecimal.ZERO;
        // START 2018/09/10 T.Ogura [QC#27999,DEL]
//        BigDecimal freStateTax = BigDecimal.ZERO;
//        BigDecimal freCountyTac = BigDecimal.ZERO;
//        BigDecimal freCityTax = BigDecimal.ZERO;
//        BigDecimal freTotalTax = BigDecimal.ZERO;
        // END   2018/09/10 T.Ogura [QC#27999,DEL]

        BigDecimal charge = BigDecimal.ZERO;
        BigDecimal stateTax = BigDecimal.ZERO;
        BigDecimal countyTax = BigDecimal.ZERO;
        BigDecimal cityTax = BigDecimal.ZERO;
        BigDecimal totalTax = BigDecimal.ZERO;
        BigDecimal totalAmt = BigDecimal.ZERO;
        //2019/01/18 QC#29371 SRNO15 Add Start
        Map<String, String> dsOrdPosnNumMap = new HashMap<String, String>();
        //List<String> dsOrdPosnNumList = new ArrayList<String>();
        //2019/01/18 QC#29371 SRNO15 Add End

        for (int i = 0; i < resultMapList.size(); i++) {
            Map<String, Object> resultMap = resultMapList.get(i);
            if (resultMapList.size() > i+1) {
                nextMap = resultMapList.get( i+1 );
            } else {
                nextMap = new HashMap<String, Object>();
            }
            charge = addBig(charge, resultMap, CHARGE);
            stateTax = addBig(stateTax, resultMap, STATE_TAX);
            countyTax = addBig(countyTax, resultMap, COUNTY_TAX);
            cityTax = addBig(cityTax, resultMap, CITY_TAX);
            totalTax = addBig(totalTax, resultMap, TOTAL_TAX);
            totalAmt = addBig(totalAmt, resultMap, TOTAL_AMOUNT);

            invBolNum = (String) resultMap.get(INVOICE_BOL_NUM);
            if (i == 0 || isSameParent(resultMap, preMap)) {
                if (ZYPCommonFunc.hasValue(invBolNum) && !invBolNum.equals(invBolNumPre)) {
                    freight = addBig(freight, resultMap, FREIGHT);
                    // START 2018/09/10 T.Ogura [QC#27999,DEL]
//                    freStateTax = addBig(freStateTax, resultMap, FREIGHT_STATE_TAX);
//                    freCountyTac = addBig(freCountyTac, resultMap, FREIGHT_COUNTY_TAX);
//                    freCityTax = addBig(freCityTax, resultMap, FREIGHT_CITY_TAX);
//                    freTotalTax = addBig(freTotalTax, resultMap, FREIGHT_TOTAL_TAX);
                    // END   2018/09/10 T.Ogura [QC#27999,DEL]
                }
            }
            invBolNumPre = invBolNum;
            preMap.putAll(resultMap);

            //2019/01/18 QC#29371 SRNO15 Add Start
            String dsOrdPosnNum = (String) resultMap.get(DS_ORD_POSN_NUM);

            if (S21StringUtil.isNotEmpty(dsOrdPosnNum)) {
                if (!dsOrdPosnNumMap.containsKey(dsOrdPosnNum)){
                    dsOrdPosnNumMap.put(dsOrdPosnNum, dsOrdPosnNum);
                }
            }
            //2019/01/18 QC#29371 SRNO15 Add End

            if (isSameParent(resultMap, nextMap)) {
                continue;
            }

            invPrtSlsPartSubTotTmsg = new INV_PRT_SLS_PART_SUB_TOTTMsg();
            BigDecimal slsPartSubPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_SLS_PART_SUB_TOT_SQ);

            // START 2018/09/10 T.Ogura [QC#27999,DEL]
//            stateTax = stateTax.add(freStateTax);
//            countyTax = countyTax.add(freCountyTac);
//            cityTax = cityTax.add(freCityTax);
//            totalTax = totalTax.add(freTotalTax);
//            totalAmt = totalAmt.add(freight).add(freTotalTax);
            // END   2018/09/10 T.Ogura [QC#27999,DEL]

            String chargeTxt = fmtAmtFromNumToStrInvTp(charge, invTpCd, ccySgnTxt);
            String freightTxt = null;
            if (BigDecimal.ZERO.compareTo(freight) != 0) {
                freightTxt = fmtAmtFromNumToStrInvTp(freight, invTpCd, ccySgnTxt);
            }
            String stateTaxTxt = fmtAmtFromNumToStrInvTp(stateTax, invTpCd, ccySgnTxt);
            String countyTaxTxt = fmtAmtFromNumToStrInvTp(countyTax, invTpCd, ccySgnTxt);
            String cityTaxTxt = fmtAmtFromNumToStrInvTp(cityTax, invTpCd, ccySgnTxt);
            String totalTaxTxt = fmtAmtFromNumToStrInvTp(totalTax, invTpCd, ccySgnTxt);
            String totalAmtTxt = fmtAmtFromNumToStrInvTp(totalAmt, invTpCd, ccySgnTxt);

            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invPrtSlsPartSubTotPk, slsPartSubPk);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invNum, invNum);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invPrtDtlFmtTpCd, INV_PRT_DTL_FMT_TP.SALE_AND_SUPPLY);
            //QC#20788 add Start
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToCustAcctCd, (String) resultMap.get(SHIP_TO_CUST_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToCustAcctNm, (String) resultMap.get(SHIP_TO_CUST_ACCT_NM));
            //QC#20788 add End
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToCustCd, (String) resultMap.get(SHIP_TO_CODE));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToLocNm, (String) resultMap.get(SHIP_TO_NAME));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToFirstLineAddr, (String) resultMap.get(SHIP_TO_FST_ADDR));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToScdLinePrntAddr, (String) resultMap.get(SHIP_TO_SCD_ADDR));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToCtyAddr, (String) resultMap.get(SHIP_TO_CITY));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToStCd, (String) resultMap.get(SHIP_TO_ST));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToPostCd, (String) resultMap.get(SHIP_TO_POST));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.custIssPoNum, (String) resultMap.get(PO_NO));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.firstBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_1));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.scdBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_2));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.thirdBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_3));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.frthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_4));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.fifthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_5));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.sixthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_6));

            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.cpoOrdNum, (String) resultMap.get(ORDER_NO));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invTrkNum, (String) resultMap.get(TRACKING_NO));
            //QC#20141 add Start
            String invChrgTpNm = "";

            if (isEasyPac1Flg) {
                invChrgTpNm = (String) resultMap.get(CHARGE_TYPE);

                if (CR_DR_RSN_SUB_SHORT_FALL_CD.equals((String) resultMap.get(CR_DR_RSN_SUB_CD))){
                    ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invChrgTpNm, invChrgTpNm);
                } else {
                    ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invChrgTpNm, createEasyPac1ChargeType(invChrgTpNm));
                }
            } else {
                ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invChrgTpNm, (String) resultMap.get(CHARGE_TYPE));
            }
            //QC#20141 add End
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.slsRepTocNm, (String) resultMap.get(SALES_REP));

            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invTotChrgAmtTxt, chargeTxt);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invFrtAmtTxt, freightTxt);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invStTaxAmtTxt, stateTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invCntyTaxAmtTxt, countyTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invCityTaxAmtTxt, cityTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invTotTaxAmtTxt, totalTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invSubTotAmtTxt, totalAmtTxt);
            if (preConslBillInfoForSalesMap != null && !preConslBillInfoForSalesMap.isEmpty()) {
                if (preConslBillInfoForSalesMap.get(CONSL_BILL_NUM) != null) {
                    ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.conslBillNum, (String) preConslBillInfoForSalesMap.get(CONSL_BILL_NUM));
                }
            }

            invPrtSlsPartSubTotList.add(invPrtSlsPartSubTotTmsg);

            // set Sale/Supply PART DETAIL
            // QC#31177 2019/04/16 Mod Start
            //resultFlg = setSlsPartDtl(invPrtSlsPartSubTotTmsg, (String) resultMap.get(SALES_REP_CODE), rs, isEasyPac1Flg, invChrgTpNm, billToCustInfo, ccySgnTxt, isSupplyOrderFlg, invPrtCtrlTmsgArray, (String) resultMap.get(DS_ORD_POSN_NUM));  //QC#26846
            //2019/01/10 QC#29371 SRNO15 mod Start
            //resultFlg = setSlsPartDtl(invPrtSlsPartSubTotTmsg, (String) resultMap.get(SALES_REP_CODE), rs, isEasyPac1Flg, invChrgTpNm, billToCustInfo, ccySgnTxt, isSupplyOrderFlg, invPrtCtrlTmsgArray, (String) resultMap.get(DS_ORD_POSN_NUM), isBillablePartsOrderFlg);
            // resultFlg = setSlsPartDtl(invPrtSlsPartSubTotTmsg, (String) resultMap.get(SALES_REP_CODE), rs, isEasyPac1Flg, invChrgTpNm, billToCustInfo, ccySgnTxt, isSupplyOrderFlg, invPrtCtrlTmsgArray, isBillablePartsOrderFlg, new ArrayList<String>(dsOrdPosnNumMap.values()));
            resultFlg = setSlsPartDtl(invPrtSlsPartSubTotTmsg, (String) resultMap.get(SALES_REP_CODE), rs, isEasyPac1Flg, invChrgTpNm, billToCustInfo, ccySgnTxt, isSupplyOrderFlg, isNoConfigOrderFlg, invPrtCtrlTmsgArray, isBillablePartsOrderFlg, new ArrayList<String>(dsOrdPosnNumMap.values()));
            // QC#31177 2019/04/16 Mod End
            dsOrdPosnNumMap.clear();
            //2019/01/10 QC#29371 SRNO15 mod End
            if (!resultFlg) {
                return false;
            }

            freight = BigDecimal.ZERO;
            // START 2018/09/10 T.Ogura [QC#27999,DEL]
//            freStateTax = BigDecimal.ZERO;
//            freCountyTac = BigDecimal.ZERO;
//            freCityTax = BigDecimal.ZERO;
//            freTotalTax = BigDecimal.ZERO;
            // END   2018/09/10 T.Ogura [QC#27999,DEL]

            charge = BigDecimal.ZERO;
            stateTax = BigDecimal.ZERO;
            countyTax = BigDecimal.ZERO;
            cityTax = BigDecimal.ZERO;
            totalTax = BigDecimal.ZERO;
            totalAmt = BigDecimal.ZERO;

        }
        int result = EZDFastTBLAccessor.insert(invPrtSlsPartSubTotList
                .toArray(new INV_PRT_SLS_PART_SUB_TOTTMsg[invPrtSlsPartSubTotList.size()]));
        if (result != invPrtSlsPartSubTotList.size()) {
            S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_SLS_PART_SUB_TOTTMsg().getTableName(), invNum));
            return false;
        }
//        normCnt += result;
        return true;
    }

    //QC#20141 add Start
    private String createEasyPac1ChargeType(String item) {
        if (!hasValue(item)) {
            return item;
        }
        String[] items = item.split("-", 0);
        return items[0];
    }
    //QC#20141 add End

    private boolean setSlsPartDtl(INV_PRT_SLS_PART_SUB_TOTTMsg invPrtMaintSubTotTmsg, 
            String slsRepCd, ResultSet rs,
            boolean isEasyPac1Flg, String invChrgTpNm, 
            Map<String, String> billToCustInfo,
            String ccySgnTxt, 
            boolean isSupplyOrderFlg,
            boolean isNoConfigOrderFlg, // QC#31177 2019/04/16 Add
            INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray, 
            boolean isBillablePartsOrderFlg,
            List<String> dsOrdPosnNumList) //QC#26846
            throws SQLException, NWCB010001BusinessException{

        //String dtlDplyFlg = rs.getString(DTL_DPLY_FLG);
        String invTpCd = rs.getString(INV_TP_CD);
        String cpoOrdNum = rs.getString(CPO_ORD_NUM);
        String invPrintStyleCd = rs.getString(INV_PRINT_STYLE_CD);

        // GET SALE/SUPPLY
        List<Map<String, Object>> ssmSalsSplyResult = null;
        //ssmSalsSplyResult = getSalsSplyPartDtl(invPrtMaintSubTotTmsg, slsRepCd, dtlDplyFlg, cpoOrdNum, isEasyPac1Flg, invChrgTpNm, isSupplyOrderFlg, dsOrdPosnNum);
        //2019/01/10 QC#29371 SRNO15 mod Start
        ssmSalsSplyResult = getSalsSplyPartDtl(invPrtMaintSubTotTmsg, slsRepCd, cpoOrdNum, isEasyPac1Flg, invChrgTpNm, isSupplyOrderFlg, invPrintStyleCd, dsOrdPosnNumList);
        //2019/01/10 QC#29371 SRNO15 mod End
        if (ssmSalsSplyResult.size() == 0) {
            return false;
        }

        // START 2018/09/10 T.Ogura [QC#27999,ADD]
        if (!isFreightInvoiceOnly(invPrtMaintSubTotTmsg.invNum.getValue())) {
            Iterator<Map<String, Object>> it = ssmSalsSplyResult.iterator();
            while (it.hasNext()) {
                Map<String, Object> map = it.next();
                String invLineCatgCd = (String)map.get(INV_LINE_CATG_CD);
                if (hasValue(invLineCatgCd) && INV_LINE_CATG.FREIGHT.equals(invLineCatgCd)) {
                    it.remove();
                }
            }
        }
        // END   2018/09/10 T.Ogura [QC#27999,ADD]

        //QC#25431 Add Start
        boolean flg = isExistsEffectiveLine(ssmSalsSplyResult);

        if (!flg){
            for (INV_PRT_CTRLTMsg tmsg : invPrtCtrlTmsgArray){
                ZYPEZDItemValueSetter.setValue(tmsg.invFtpProcStsCd, INV_PRINT_EXCLUSIVE);
            }
        }
        //QC#25431 Add End

        // INSERT INV_PRT_SLS_PART_DTL
//        boolean result = insertSalsSplyPartDtl(ssmSalsSplyResult, invPrtMaintSubTotTmsg, invTpCd, isEasyPac1Flg, billToCustInfo, ccySgnTxt, isSupplyOrderFlg, dtlDplyFlg);
        // QC#31177 2019/04/16 Mod Start
        // boolean result = insertSalsSplyPartDtl(ssmSalsSplyResult, invPrtMaintSubTotTmsg, invTpCd, isEasyPac1Flg, billToCustInfo, ccySgnTxt, isSupplyOrderFlg, invPrintStyleCd);
        boolean result = insertSalsSplyPartDtl(ssmSalsSplyResult, invPrtMaintSubTotTmsg, invTpCd, isEasyPac1Flg, billToCustInfo, ccySgnTxt, isSupplyOrderFlg, isNoConfigOrderFlg, invPrintStyleCd);
        // QC#31177 2019/04/16 Mod End
        if (!result) {
            return false;
        }
        return true;
    }

    //QC#25431 Add Start
    private boolean isExistsEffectiveLine(List<Map<String, Object>> list){
        boolean result = false;

        for(Map<String, Object> rec : list){
            String clsdOrdFlg = (String)rec.get("EFF_LINE_FLG");

            if (ZYPConstant.FLG_ON_1.equals(clsdOrdFlg)){
                result = true;
                break;
            }
        }
        return result;
    }
    //QC#25431 Add End

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSalsSplyPartDtl(INV_PRT_SLS_PART_SUB_TOTTMsg subTotTmsg, String slsRepCd, //String dtlDplyFlg,
            String cpoOrdNum, boolean isEasyPac1Flg, String invChrgTpNm, boolean isSupplyOrderFlg,  String invPrintStyleCd, List<String> dsOrdPosnNumList) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", subTotTmsg.invNum.getValue());
        if (hasValue(cpoOrdNum)) {
            ssmParam.put("cpoOrdNum", cpoOrdNum);
        }
        //ssmParam.put("dtlDplyFlg", dtlDplyFlg);
        ssmParam.put("shipToCust", subTotTmsg.shipToCustCd.getValue());
        ssmParam.put("poNo", subTotTmsg.custIssPoNum.getValue());
        ssmParam.put("custCodes1", subTotTmsg.firstBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes2", subTotTmsg.scdBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes3", subTotTmsg.thirdBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes4", subTotTmsg.frthBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes5", subTotTmsg.fifthBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes6", subTotTmsg.sixthBllgAttrbValTxt.getValue());
        ssmParam.put("OrdNo", subTotTmsg.cpoOrdNum.getValue());
        //QC#20141 add Start
        if (S21StringUtil.isEmpty(invChrgTpNm)){
        ssmParam.put("invChrgTpNm", subTotTmsg.invChrgTpNm.getValue());
        } else {
            ssmParam.put("invChrgTpNm", invChrgTpNm);
        }
        //QC#20141 add End
        ssmParam.put("slsRepTocCd", slsRepCd);
        ssmParam.put("catgCtxTp", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        ssmParam.put("prodCd", "K%");
        //QC#25026
        //ssmParam.put("coaMdseTpCd", COA_PROJ.SOFTWARE);
        ssmParam.put("coaMdseTpCd", COA_PROJ_CD_ZZZ);
        ssmParam.put("ordCatgNmSale", SALE);
        ssmParam.put("ordCatgNmSply", SUPPLY);
        ssmParam.put("copier", "-" + COPIER);
        ssmParam.put("nonCopier", "-" + NON_COPIER);
        ssmParam.put("isEasyPac1Flg", isEasyPac1Flg);
        ssmParam.put("amtFormat", FM_AMT);
        ssmParam.put("qtyFormat", FM_QTY);
        ssmParam.put("rateFormat", FM_RATE6);
        ssmParam.put("easyPac1Return", ORD_CATG_CTX_TP.EASY_PAC1_RETURN);
        ssmParam.put("easyPac1", ORD_CATG_CTX_TP.EASY_PAC1);
        ssmParam.put("varCharConst2", nullConvEmp(this.invPrintShortFallItem));
        List<String> shpgStsCdList = new ArrayList<String>();
        shpgStsCdList.add(SHPG_STS.INVOICED);
        shpgStsCdList.add(SHPG_STS.CANCELLED);
        ssmParam.put("shpgStsCdList", shpgStsCdList);
        ssmParam.put("isSupplyOrderFlg", isSupplyOrderFlg);
//QC#13096 add Start
        ssmParam.put("zdInvPrtExcl", "ZD_INV_PRT_EXCL");
//QC#13096 add End
        //2019/01/18 QC#29371 SRNO15 Mod Start
        //ssmParam.put("posnNum", dsOrdPosnNum);
        if (!dsOrdPosnNumList.isEmpty()){
            ssmParam.put("dsOrdPosnNumList", dsOrdPosnNumList);
        }
        //2019/01/18 QC#29371 SRNO15 Mod End
        ssmParam.put("invPrintStyleCd", invPrintStyleCd);
        
        //(0:INV->BO, 1:BO->INV, 2:Mix)
        if (!hasValue(invPrintOmInvBoSort) || "0".equals(invPrintOmInvBoSort)) {
            ssmParam.put("sortFlgInv", INV_PRINT_OM_INV_BO_SORT_FIRST);
            ssmParam.put("sortFlgBo", INV_PRINT_OM_INV_BO_SORT_SCD);
        } else if ("1".equals(invPrintOmInvBoSort)) {
            ssmParam.put("sortFlgInv", INV_PRINT_OM_INV_BO_SORT_SCD);
            ssmParam.put("sortFlgBo", INV_PRINT_OM_INV_BO_SORT_FIRST);
        } else {
            ssmParam.put("sortFlgInv", INV_PRINT_OM_INV_BO_SORT_FIRST);
            ssmParam.put("sortFlgBo", INV_PRINT_OM_INV_BO_SORT_FIRST);
        }
        // 2018/05/25 QC#21841 Add Start
        ssmParam.put("invLineCatgChg", INV_LINE_CATG.CHARGE);
        // START 2018/09/10 T.Ogura [QC#27999,DEL]
//        ssmParam.put("invLineCatgFrt", INV_LINE_CATG.FREIGHT);
        // END   2018/09/10 T.Ogura [QC#27999,DEL]
        // 2018/05/25 QC#21841 Add End
        ssmParam.put("invPrtPrmoItems", INV_PRT_PRMO_ITEMS); // QC#29371  SRNO.11 2019/02/01 Add

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSalsSplyPartDtl", ssmParam);
    }

    private boolean insertSalsSplyPartDtl(List<Map<String, Object>> resultMapList, 
            INV_PRT_SLS_PART_SUB_TOTTMsg paramTmsg, 
            String invTpCd, 
            boolean isEasyPac1Flg, 
            Map<String, String> billToCustInfo,
            String ccySgnTxt, 
            boolean isSupplyOrderFlg,
            boolean isNoConfigOrderFlg, // QC#31177 2019/04/16 Add
//            String dtlDplyFlg,
            String invPrintStyleCd) {

        INV_PRT_SLS_PART_DTLTMsg invPrtSlsPartDtlTmsg;
        List<INV_PRT_SLS_PART_DTLTMsg> invPrtSlsPartDtlList = new ArrayList<INV_PRT_SLS_PART_DTLTMsg>();

        BigDecimal subTotPk = paramTmsg.invPrtSlsPartSubTotPk.getValue();
        String invNum = paramTmsg.invNum.getValue();

        List<Map<String, Object>> serialNoList = getSerialNoList(invNum);

        // START 2018/09/10 T.Ogura [QC#27999,ADD]
        boolean freightInvoiceOnlyFlg = isFreightInvoiceOnly(invNum);
        // END   2018/09/10 T.Ogura [QC#27999,ADD]

        for (Map<String, Object> resultMap : resultMapList) {

            //QC#18371
            //QC#20141 add Start
            //if (isEasyPac1Flg) {
                String dispLineNo = (String) resultMap.get(DISPLAY_LINE_NO);
                if (hasValue(dispLineNo)) {
                    String[] dispLineNos = dispLineNo.split("\\.");
                    if (dispLineNos.length > 2) {
                        continue;
                    }
                }
            //}
            //QC#20141 add End

            String ordQty = setNegateQty((String) resultMap.get(ORDER_QTY), invTpCd);
            String shipQty = setNegateQty((String) resultMap.get(SHIP_QTY), invTpCd);
            String totalSqFeet = setNegateQty((String) resultMap.get(TOTAL_SQ_FEET), invTpCd);

            //QC#20141 add Start
            String crDrRsnSubCd = (String)resultMap.get(CR_DR_RSN_SUB_CD);
            String ezPacTpCd = (String)resultMap.get(EASY_PACK_TP_CD);
            //QC#20141 add End

            String colorFlg = getColorFlg(resultMap, isSupplyOrderFlg);
            //QC#7427

            // START 2023/12/15 R.Takau [QC#61584, ADD]
            String invPrtCatgCd = (String) resultMap.get(INV_PRINT_CATG_CD);
            String shipToStCd = (String) resultMap.get(SHIP_TO_ST_CD);
            // END 2023/12/15 R.Takau [QC#61584, ADD]
            String priceAmount = null;
            if (isEasyPac1Flg) {
                //QC#20141 add Start
                if (INV_PRINT_STYLE.SUMMARY.equals(invPrintStyleCd)) {
//                if (ZYPConstant.FLG_OFF_N.equals(dtlDplyFlg)) {
                    priceAmount = "";
                } else {
                    if (CR_DR_RSN_SUB_SHORT_FALL_CD.equals(crDrRsnSubCd)){
                        //ShortFall
                        // 2018/08/16 QC#27438 Mod Start
                        //priceAmount = fmtAmtFromNumToStrForEzPac((BigDecimal) resultMap.get(PRICE_AMOUNT), ccySgnTxt);
                        priceAmount = fmtAmtFromNumToStrForEzPac((BigDecimal) resultMap.get(PRICE_AMOUNT), ccySgnTxt, ezPacTpCd);
                        // 2018/08/16 QC#27438 Mod End
                    } else {
                        // 2018/08/16 QC#27438 Mod Start
                        //priceAmount = fmtAmtFromNumToStrForEzPac((BigDecimal) resultMap.get(PRICE_AMOUNT), ccySgnTxt);
                        priceAmount = fmtAmtFromNumToStrForEzPac((BigDecimal) resultMap.get(PRICE_AMOUNT), ccySgnTxt, ezPacTpCd);
                        // 2018/08/16 QC#27438 Mod End
                        //priceAmount = billToCustInfo.get(AMT_RATE);
                    }
                }
                //QC#20141 add End
            } else {
                if (INV_PRINT_STYLE.SUMMARY.equals(invPrintStyleCd)) {
                    priceAmount = "";
                } else if (INV_PRINT_STYLE.DETAIL.equals(invPrintStyleCd)) {
                    priceAmount = fmtAmtFromNumToStr((BigDecimal) resultMap.get(PRICE_AMOUNT), ccySgnTxt);
                // START 2023/12/15 R.Takau [QC#61584, ADD]
                } else if (hasValue(invPrtCatgCd)) {
                    priceAmount = fmtAmtFromNumToStr((BigDecimal) resultMap.get(PRICE_AMOUNT), ccySgnTxt);
                // END 2023/12/15 R.Takau [QC#61584, ADD]
                } else if ("Y".equals((String) resultMap.get(BASE_CMPT_FLG))){
                    priceAmount = fmtAmtFromNumToStr((BigDecimal) resultMap.get(CONFIG_SUM_PRICE_AMOUNT), ccySgnTxt);
                } else {
                    //2019/08/22 QC#52928 Mod Start
                    if (ZYPConstant.FLG_ON_Y.equals((String)resultMap.get(IS_EXISTS_BASE_CMPT))){
                        priceAmount = "";
                    } else {
                        priceAmount = fmtAmtFromNumToStr((BigDecimal) resultMap.get(PRICE_AMOUNT), ccySgnTxt);
                    }
                    //priceAmount = fmtAmtFromNumToStr((BigDecimal) resultMap.get(PRICE_AMOUNT), ccySgnTxt);
                    //2019/08/22 QC#52928 Mod End
                }
                //priceAmount = getAmtTxt((BigDecimal) resultMap.get("PRICE_AMOUNT"));
            }
            String lineTotAmtTxts = null;
            if (isEasyPac1Flg) {
                lineTotAmtTxts = fmtAmtFromNumToStr(setNegate((BigDecimal) resultMap.get(LINE_TOTAL_AMOUNT), invTpCd), ccySgnTxt);
            } else {
                if (INV_PRINT_STYLE.SUMMARY.equals(invPrintStyleCd)) {
                    lineTotAmtTxts = "";
                } else if (INV_PRINT_STYLE.DETAIL.equals(invPrintStyleCd)) {
                    lineTotAmtTxts = fmtAmtFromNumToStr(setNegate((BigDecimal) resultMap.get(LINE_TOTAL_AMOUNT), invTpCd), ccySgnTxt);
                // START 2023/12/15 R.Takau [QC#61584, ADD]
                } else if (hasValue(invPrtCatgCd)) {
                    lineTotAmtTxts = fmtAmtFromNumToStr(setNegate((BigDecimal) resultMap.get(LINE_TOTAL_AMOUNT), invTpCd), ccySgnTxt);
                // END 2023/12/15 R.Takau [QC#61584, ADD]
                } else if ("Y".equals((String) resultMap.get(BASE_CMPT_FLG))){
                    lineTotAmtTxts = fmtAmtFromNumToStr(setNegate((BigDecimal) resultMap.get(CONFIG_SUM_LINE_TOTAL_AMOUNT), invTpCd), ccySgnTxt);
                } else {
                    //2019/08/22 QC#52928 Mod Start
                    if (ZYPConstant.FLG_ON_Y.equals((String)resultMap.get(IS_EXISTS_BASE_CMPT))){
                        lineTotAmtTxts = "(Included)";
                    } else {
                        lineTotAmtTxts = fmtAmtFromNumToStr(setNegate((BigDecimal) resultMap.get(LINE_TOTAL_AMOUNT), invTpCd), ccySgnTxt);
                    }
                    //lineTotAmtTxts = "(Included)";
                  //2019/08/22 QC#52928 Mod End
                }
            }
            
            // START 2023/12/15 R.Takau [QC#61584, ADD]
            String invLineAmtTaxTxt = null;
            if (INV_PRINT_STYLE.SUMMARY.equals(invPrintStyleCd)) {
                invLineAmtTaxTxt = "";
            } else if (hasValue(invPrtCatgCd)) {
                invLineAmtTaxTxt = fmtAmtFromNumToStr((BigDecimal) resultMap.get(INV_LINE_DEAL_TAX_AMT), ccySgnTxt);
            } else {
                invLineAmtTaxTxt = "";
            }
            // END 2023/12/15 R.Takau [QC#61584, ADD]
            //String lineTotAmtTxts = getAmtTxt(setNegate((BigDecimal) resultMap.get("LINE_TOTAL_AMOUNT"), invTpCd));
            //String lineTotAmtTxts = fmtAmtFromNumToStr(setNegate((BigDecimal) resultMap.get(LINE_TOTAL_AMOUNT), invTpCd), ccySgnTxt);

            // SET INV_PRT_SLS_PART_DTL
            // QC#31177 2019/04/16 Mod Start
            // String description = getDescInfo(resultMap, isSupplyOrderFlg, serialNoList);
            String description = getDescInfo(resultMap, isNoConfigOrderFlg, serialNoList);
            // QC#31177 2019/04/16 Mod End
//            String colorFlg = getColorFlg(resultMap, isSupplyOrderFlg);

            invPrtSlsPartDtlTmsg = new INV_PRT_SLS_PART_DTLTMsg();
            BigDecimal chrgDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_SLS_PART_DTL_SQ);

            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invPrtSlsPartDtlPk, chrgDtlPk);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invPrtSlsPartSubTotPk, subTotPk);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invNum, invNum);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.dplyLineNum, (String) resultMap.get(DISPLAY_LINE_NO));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.mdseCd, (String) resultMap.get(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invMdseDescTxt, description);
            // START 2018/09/10 T.Ogura [QC#27999,ADD]
            if (freightInvoiceOnlyFlg) {
                ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.dplyLineNum, "");
                ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.mdseCd, "");
                ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invMdseDescTxt, "");
            }
            // END   2018/09/10 T.Ogura [QC#27999,ADD]

            //QC#20141 mod Start
            if (isEasyPac1Flg && !CR_DR_RSN_SUB_SHORT_FALL_CD.equals(crDrRsnSubCd)){
                ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invPrtOrdQtyTxt, shipQty);
            } else if (isEasyPac1Flg && CR_DR_RSN_SUB_SHORT_FALL_CD.equals(crDrRsnSubCd)){
                ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invPrtOrdQtyTxt, totalSqFeet);
            } else {
                ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invPrtOrdQtyTxt, ordQty);
            }

            if (isEasyPac1Flg){
                ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invPrtShipQtyTxt, chkShortFallItem(crDrRsnSubCd, ezPacTpCd, isEasyPac1Flg, totalSqFeet));
            } else {
                ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invPrtShipQtyTxt, shipQty);
                // START 2018/09/10 T.Ogura [QC#27999,ADD]
                if (freightInvoiceOnlyFlg) {
                    ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invPrtShipQtyTxt, "");
                }
                // END   2018/09/10 T.Ogura [QC#27999,ADD]
            }

            //QC#20141 mod End
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.dealUnitPrcAmtTxt, priceAmount);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.dealLineTotPrcAmtTxt, lineTotAmtTxts);
            // START 2018/09/10 T.Ogura [QC#27999,ADD]
            if (freightInvoiceOnlyFlg) {
                ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.dealUnitPrcAmtTxt, "");
                ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.dealLineTotPrcAmtTxt, "");
            }
            // END   2018/09/10 T.Ogura [QC#27999,ADD]
            // QC#31273 2019/04/24 Add Start
            if (INV_LINE_CATG.CHARGE.equals((String) resultMap.get(INV_LINE_CATG_CD))) {
                ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.dealUnitPrcAmtTxt, "");
            }
            // QC#31273 2019/04/24 Add End
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invMdseDescColorFlg, colorFlg);

            //QC#17577
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invBolLineNum, (String) resultMap.get(INV_BOL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invLineNum, (String) resultMap.get(INV_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invLineSubNum, (String) resultMap.get(INV_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invLineSubTrxNum, (String) resultMap.get(INV_LINE_SUB_TRX_NUM));

            // START 2023/11/30 R.Takau [QC#61584, ADD]
            if (hasValue(shipToStCd) && Arrays.asList(this.shipToCustStCdArray).contains(resultMap.get(SHIP_TO_ST_CD))) {
                ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invLineAmtTaxTxt, invLineAmtTaxTxt);
            }
            // END 2023/11/30 R.Takau [QC#61584, ADD]

            invPrtSlsPartDtlList.add(invPrtSlsPartDtlTmsg);
        }

        if (invPrtSlsPartDtlList.size() > 0) {
            // INSERT
            int result = EZDFastTBLAccessor.insert(invPrtSlsPartDtlList.toArray(new INV_PRT_SLS_PART_DTLTMsg[invPrtSlsPartDtlList.size()]));
            if (result != invPrtSlsPartDtlList.size()) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_SLS_PART_DTLTMsg().getTableName(), String.valueOf(subTotPk)));
                return false;
            }
//            normCnt += result;
        }
        return true;
    }

    //QC#20141 add Start
    private String chkShortFallItem(String crDrRsnSubCd, String ezPacTpCd, boolean isEasyPac1Flg, String shipQty){
        if (!(hasValue(crDrRsnSubCd) || hasValue(ezPacTpCd))) {
            return shipQty;
        }
        if (isEasyPac1Flg) {
            if (CR_DR_RSN_SUB_SHORT_FALL_CD.equals(crDrRsnSubCd) || 
                    EASY_PACK_TP.EASYPAC_TONER.equals(ezPacTpCd)) {
                shipQty = "";
            }
        }
        return shipQty;
    }
    //QC#20141 add End

    // QC#31177 2019/04/16 Mod
    //private String getDescInfo(Map<String, Object> resultMap, boolean isSupplyOrderFlg, List<Map<String, Object>> serialNoList) {
    private String getDescInfo(Map<String, Object> resultMap, boolean isNoConfigOrderFlg, List<Map<String, Object>> serialNoList) {

        StringBuilder sbDesc = new StringBuilder();
        sbDesc.append(resultMap.get(DESCRIPTION));

        String custMdseCd = (String) resultMap.get(CUSTOMER_MDSE_CODE);
        String csmpNo = (String) resultMap.get(CSMP_NO);
        String trackingNo = (String) resultMap.get(TRACKING_NO);
        String baseCmptFlg = (String) resultMap.get(BASE_CMPT_FLG);
        //String serialNo = getSerialNo(resultMap);
        //QC#18371
        List<Map<String, Object>> dispSerialNolist = getDispSerialNoList(resultMap, serialNoList);
        String mdseCd = (String) resultMap.get(ORDER_MDSE);
        String ordTakeMdseCd = nullConvEmp((String) resultMap.get(ORDER_TAKE_MDSE));

        if (ZYPCommonFunc.hasValue(custMdseCd)
                && !(mdseCd.equals(custMdseCd)
                        || 
                    ordTakeMdseCd.equals(custMdseCd))) {
            sbDesc.append(ENTER).append("(").append(custMdseCd).append(")");
        }
        if (ZYPCommonFunc.hasValue(csmpNo)) {
            sbDesc.append(ENTER).append(" CSMP# ").append(csmpNo);
        }
        if (dispSerialNolist != null && dispSerialNolist.size() > 0) {
            int count = 0;
            //QC#18371
            String preMdseDesc = "";
            for (Map<String, Object> serialNoMap : dispSerialNolist) {
                if (ZYPCommonFunc.hasValue((String) serialNoMap.get("SER_NUM"))) {
                    String mdseDesc = resultMap.get(DESCRIPTION) == null ? "" : (String) resultMap.get(DESCRIPTION);
                    if (count == 0 && !preMdseDesc.equals((String) serialNoMap.get("MDSE_DESC_SHORT_TXT"))) {
                        if (!mdseDesc.equals((String) serialNoMap.get("MDSE_DESC_SHORT_TXT"))) {
                            sbDesc.append(ENTER).append((String) serialNoMap.get("MDSE_DESC_SHORT_TXT")).append(" Serial# ").append((String) serialNoMap.get("SER_NUM"));
                        } else {
                            sbDesc.append(ENTER).append("Serial# ").append((String) serialNoMap.get("SER_NUM"));
                        }
                    } else {
                        if (!preMdseDesc.equals((String) serialNoMap.get("MDSE_DESC_SHORT_TXT"))) {
                            if (!mdseDesc.equals((String) serialNoMap.get("MDSE_DESC_SHORT_TXT"))) {
                                sbDesc.append(" ").append((String) serialNoMap.get("MDSE_DESC_SHORT_TXT")).append(" Serial# ").append((String) serialNoMap.get("SER_NUM"));
                            } else {
                                sbDesc.append(", ").append((String) serialNoMap.get("SER_NUM"));
                            }
                        } else {
                            sbDesc.append(", ").append((String) serialNoMap.get("SER_NUM"));
                        }
                    }
                    count++ ;
                }
                //QC#18371
                preMdseDesc = (String) serialNoMap.get("MDSE_DESC_SHORT_TXT");
            }
        }
        //QC#14292 add Start
        //bill with equipment data
        if (hasValue((BigDecimal) resultMap.get("DS_CONTR_DTL_PK"))) {
            return sbDesc.toString();
        }
        //QC#14292 add End
        if (ZYPCommonFunc.hasValue(trackingNo)) {
            sbDesc.append(ENTER).append(" Tracking# ").append(trackingNo);
        }
        //Not Supply Order
        // QC#31177 2019/04/16 Mod Start
        // if (!isSupplyOrderFlg) {
        if (!isNoConfigOrderFlg) {
        // QC#31177 2019/04/16 Mod End
            if (ZYPConstant.FLG_ON_Y.equals(baseCmptFlg)) {
                sbDesc.append(" (BASE)");
            } else {
                sbDesc.append(" (Attachment)");
            }
        }

        return sbDesc.toString();
    }

    private String getColorFlg(Map<String, Object> resultMap, boolean isSupplyOrderFlg) {

        String colorFlg = ZYPConstant.FLG_OFF_N;
        // Supply Order
        if (isSupplyOrderFlg) {
            colorFlg = ZYPConstant.FLG_OFF_N;
        } else {
            String baseCmptFlg = (String) resultMap.get(BASE_CMPT_FLG);
            String baseCmptFlgB = (String) resultMap.get(B_BASE_CMPT_FLG);
            BigDecimal lineCountPstnNo = (BigDecimal) resultMap.get(LINE_COUNT_PER_POSITION_NO);

            if (baseCmptFlgB == null) {
                if (ZYPConstant.FLG_OFF_N.equals(baseCmptFlg)) {
                    colorFlg = ZYPConstant.FLG_ON_Y;
                }
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(baseCmptFlg)) {

                    if (BigDecimal.ONE.compareTo(lineCountPstnNo) < 0) {
                        colorFlg = ZYPConstant.FLG_ON_Y;
                    } else if (BigDecimal.ONE.compareTo(lineCountPstnNo) == 0) {
                        colorFlg = ZYPConstant.FLG_OFF_N;
                    }
                } else if (ZYPConstant.FLG_OFF_N.equals(baseCmptFlg)) {
                    colorFlg = ZYPConstant.FLG_OFF_N;
                }
            }
        }
        return colorFlg;
    }

    /**
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException
     */
    private boolean setPartsLaborInfo(ResultSet rs, Map<String, Object> preConslBillInfoForPartsMap) throws SQLException, NWCB010001BusinessException {

        String invNum = rs.getString(INV_NUM);
        String taxDtlFlg = rs.getString(TAX_DETAIL_FLG);
        String dtlDplyFlg = rs.getString(DTL_DPLY_FLG);
        // String invTpCd = rs.getString(INV_TP_CD); // Del QC#50078 2019/05/07
        // String ccySgnTxt = rs.getString(CCY_SGN_TXT); // Del QC#50078 2019/05/07

        // QC#50078 2019/05/07 Add Start
        String manInvFlg = rs.getString(MAN_INV_FLG);
        // QC#50078 2019/05/07 Add End

        // GET PARTS & LAbor Info
        // QC#50078 2019/05/07 Mod Start
        List<Map<String, Object>> ssmPartsLaborResult = getPartsLaborSubTotal(invNum, taxDtlFlg, dtlDplyFlg, manInvFlg);
        // List<Map<String, Object>> ssmPartsLaborResult = getPartsLaborSubTotal(invNum, taxDtlFlg, dtlDplyFlg);
        // QC#50078 2019/05/07 Mod End

        if (ssmPartsLaborResult.size() == 0) {
            //NWCM0112E=It failed to get [@].(@)
            S21InfoLogOutput.println(NWCM0112E, toArray("PartsLaborSubTotal", invNum));
            return false;
        }
        // INSERT PARTS & LAbor Info
        // QC#50078 2019/05/07 Mod Start
        boolean result = setPartsLabor(ssmPartsLaborResult, preConslBillInfoForPartsMap, rs);
        // boolean result = setPartsLabor(ssmPartsLaborResult, invNum, invTpCd, dtlDplyFlg, preConslBillInfoForPartsMap, ccySgnTxt);
        // QC#50078 2019/05/07 Mod End

        if (!result) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // QC#50078 2019/05/07 Mod Start
    private List<Map<String, Object>> getPartsLaborSubTotal(String invNum, String taxDtlFlg, String dtlDplyFlg, String manInvFlg) {
    // private List<Map<String, Object>> getPartsLaborSubTotal(String invNum, String taxDtlFlg, String dtlDplyFlg) {
    // QC#50078 2019/05/07 Mod End

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        ssmParam.put("taxDtlFlg", taxDtlFlg);
        ssmParam.put("dtlDplyFlg", dtlDplyFlg);
        ssmParam.put("dsSlsTaxTpCd01", DS_SLS_TAX_TP.STATE_TAX);
        ssmParam.put("dsSlsTaxTpCd02", DS_SLS_TAX_TP.COUNTY_TAX);
        ssmParam.put("dsSlsTaxTpCd03", DS_SLS_TAX_TP.CITY_TAX);
        ssmParam.put("prodCd", "K%");
        //QC#25026
        //ssmParam.put("coaMdseTpCd", COA_PROJ.SOFTWARE);
        ssmParam.put("coaMdseTpCd", COA_PROJ_CD_ZZZ);
        ssmParam.put("svcInvSrcTpCdD", SVC_INV_SRC_TP.DISPATCH);
        ssmParam.put("invChrgTpPN", PARTS_OR_LABOR + "-" + NON_COPIER);
        ssmParam.put("invChrgTpPC", PARTS_OR_LABOR + "-" + COPIER);
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("qtyFormat", FM_QTY);
        ssmParam.put("amtFormat", FM_AMT);

        // QC#50078 2019/05/07 Add Start
        String statementId;

        if (ZYPConstant.FLG_ON_1.equals(manInvFlg)) {
            statementId = "getPartsLaborSubTotal_manInv";
        } else {
            statementId = "getPartsLaborSubTotal";
        }
        // QC#50078 2019/05/07 Add End

        // QC#50078 2019/05/07 Mod Start
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList(statementId, ssmParam);
        // return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPartsLaborSubTotal", ssmParam);
        // QC#50078 2019/05/07 Mod End
    }

    // QC#50078 2019/05/07 Mod Start
    private boolean setPartsLabor(List<Map<String, Object>> resultMapList,
            Map<String, Object> preConslBillInfoForPartsMap, ResultSet rs) throws SQLException {
    // private boolean setPartsLabor(List<Map<String, Object>> resultMapList, String invNum, String invTpCd, String dtlDplyFlg, 
    //      Map<String, Object> preConslBillInfoForPartsMap, String ccySgnTxt) {
        // QC#50078 2019/05/07 Mod End

        // QC#50078 2019/05/07 Add Start
        String invNum = rs.getString(INV_NUM);
        String dtlDplyFlg = rs.getString(DTL_DPLY_FLG);
        String invTpCd = rs.getString(INV_TP_CD);
        String ccySgnTxt = rs.getString(CCY_SGN_TXT);
        String manInvFlg = rs.getString(MAN_INV_FLG);
        // QC#50078 2019/05/07 Mod End

        boolean resultFlg = true;
        INV_PRT_SLS_PART_SUB_TOTTMsg invPrtSlsPartSubTotTmsg;
        List<INV_PRT_SLS_PART_SUB_TOTTMsg> invPrtSlsPartSubTotList = new ArrayList<INV_PRT_SLS_PART_SUB_TOTTMsg>();

        for (Map<String, Object> resultMap : resultMapList) {

            invPrtSlsPartSubTotTmsg = new INV_PRT_SLS_PART_SUB_TOTTMsg();
            BigDecimal slsPartSubPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_SLS_PART_SUB_TOT_SQ);

            String chargeTxt = setNegate((String) resultMap.get(CHARGE_TXT), invTpCd);
            String stateTaxTxt = setNegate((String) resultMap.get(STATE_TAX_TXT), invTpCd);
            String countyTaxTxt = setNegate((String) resultMap.get(COUNTY_TAX_TXT), invTpCd);
            String cityTaxTxt = setNegate((String) resultMap.get(CITY_TAX_TXT), invTpCd);
            String totalTaxTxt = setNegate((String) resultMap.get(TOTAL_TAX_TXT), invTpCd);
            String totalAmtTxt = setNegate((String) resultMap.get(TOTAL_AMOUNT_TXT), invTpCd);

            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invPrtSlsPartSubTotPk, slsPartSubPk);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invNum, invNum);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invPrtDtlFmtTpCd, INV_PRT_DTL_FMT_TP.PARTS_AND_LABOR);
            // QC#20788 add Start
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToCustAcctCd, (String) resultMap.get(SHIP_TO_CUST_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToCustAcctNm, (String) resultMap.get(SHIP_TO_CUST_ACCT_NM));
            // QC#20788 add End
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToCustCd, (String) resultMap.get(SHIP_TO_CODE));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToLocNm, (String) resultMap.get(SHIP_TO_NAME));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToFirstLineAddr, (String) resultMap.get(SHIP_TO_FST_ADDR));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToScdLinePrntAddr, (String) resultMap.get(SHIP_TO_SCD_ADDR));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToCtyAddr, (String) resultMap.get(SHIP_TO_CITY));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToStCd, (String) resultMap.get(SHIP_TO_ST));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.shipToPostCd, (String) resultMap.get(SHIP_TO_POST));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.custIssPoNum, (String) resultMap.get(PO_NO));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.firstBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_1));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.scdBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_2));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.thirdBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_3));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.frthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_4));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.fifthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_5));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.sixthBllgAttrbValTxt, (String) resultMap.get(CUST_CODES_6));

            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.cpoOrdNum, (String) resultMap.get(ORDER_NO));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invChrgTpNm, (String) resultMap.get(CHARGE_TYPE));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.fsrNum, (String) resultMap.get(FSR_NO));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.techCd, (String) resultMap.get(TECH_CD));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.techNm, (String) resultMap.get(TECH_NM));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.serNum, (String) resultMap.get(SERIAL_NO));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.mdlNm, (String) resultMap.get(MODEL));

            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invTotChrgAmtTxt, chargeTxt);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invStTaxAmtTxt, stateTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invCntyTaxAmtTxt, countyTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invCityTaxAmtTxt, cityTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invTotTaxAmtTxt, totalTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.invSubTotAmtTxt, totalAmtTxt);
            if (preConslBillInfoForPartsMap != null && !preConslBillInfoForPartsMap.isEmpty()) {
                if (preConslBillInfoForPartsMap.get(CONSL_BILL_NUM) != null) {
                    ZYPEZDItemValueSetter.setValue(invPrtSlsPartSubTotTmsg.conslBillNum, (String) preConslBillInfoForPartsMap.get(CONSL_BILL_NUM));
                }
            }
            
            invPrtSlsPartSubTotList.add(invPrtSlsPartSubTotTmsg);

            // set Sale/Supply PART DETAIL
            // 2017/02/27 QC#16575 UPD START
            // resultFlg = setPartsLaborDtl(invPrtSlsPartSubTotTmsg, invTpCd, dtlDplyFlg, ccySgnTxt);
            // QC#50078 2019/05/07 Mod Start 
            // resultFlg = setPartsLaborDtl(invPrtSlsPartSubTotTmsg, invTpCd, dtlDplyFlg, ccySgnTxt, (String) resultMap.get("MDSE_CD"));
            resultFlg = setPartsLaborDtl(invPrtSlsPartSubTotTmsg, invTpCd, dtlDplyFlg, ccySgnTxt, (String) resultMap.get("MDSE_CD"), manInvFlg);
            // QC#50078 2019/05/07 Mod End 
            // 2017/02/27 QC#16575 UPD E N D
            if (!resultFlg) {
                return false;
            }
        }
        int result = EZDFastTBLAccessor.insert(invPrtSlsPartSubTotList
                .toArray(new INV_PRT_SLS_PART_SUB_TOTTMsg[invPrtSlsPartSubTotList.size()]));
        if (result != invPrtSlsPartSubTotList.size()) {
            S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_SLS_PART_SUB_TOTTMsg().getTableName(), invNum));
            return false;
        }
//        normCnt += result;
        return true;
    }

        // 2017/02/27 QC#16575 UPD START
    // private boolean setPartsLaborDtl(INV_PRT_SLS_PART_SUB_TOTTMsg invPrtMaintSubTotTmsg, String invTpCd, String dtlDplyFlg, String ccySgnTxt) {
    // QC#50078 2019/05/07 Mod Start 
    // private boolean setPartsLaborDtl(INV_PRT_SLS_PART_SUB_TOTTMsg invPrtMaintSubTotTmsg, String invTpCd, String dtlDplyFlg, String ccySgnTxt, String mdse_cd) {
    private boolean setPartsLaborDtl(INV_PRT_SLS_PART_SUB_TOTTMsg invPrtMaintSubTotTmsg, String invTpCd, String dtlDplyFlg, String ccySgnTxt, String mdse_cd, String manInvFlg) {
    // QC#50078 2019/05/07 Mod Start 
        // 2017/02/27 QC#16575 UPD E N D


        // GET PARTS/LABOR
        // 2017/02/27 QC#16575 UPD START
        // List<Map<String, Object>> ssmSalsSplyResult = getPartsLaborPartDtl(invPrtMaintSubTotTmsg, dtlDplyFlg);
        // QC#50078 2019/05/07 Mod Start 
        // List<Map<String, Object>> ssmSalsSplyResult = getPartsLaborPartDtl(invPrtMaintSubTotTmsg, dtlDplyFlg, mdse_cd);
        List<Map<String, Object>> ssmSalsSplyResult = getPartsLaborPartDtl(invPrtMaintSubTotTmsg, dtlDplyFlg, mdse_cd, manInvFlg);
        // QC#50078 2019/05/07 Mod End 
        // 2017/02/27 QC#16575 UPD E N D

        if (ssmSalsSplyResult.size() == 0) {
            return false;
        }
        // INSERT INV_PRT_SLS_PART_DTL
        boolean result = insertPartsLaborPartDtl(ssmSalsSplyResult, invPrtMaintSubTotTmsg, invTpCd, ccySgnTxt);
        if (!result) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
        // 2017/02/27 QC#16575 UPD START
    //private List<Map<String, Object>> getPartsLaborPartDtl(INV_PRT_SLS_PART_SUB_TOTTMsg subTotTmsg, String dtlDplyFlg) {
    // QC#50078 2019/05/07 Mod Start 
    private List<Map<String, Object>> getPartsLaborPartDtl(INV_PRT_SLS_PART_SUB_TOTTMsg subTotTmsg, String dtlDplyFlg, String mdse_cd, String manInvFlg) {
    // private List<Map<String, Object>> getPartsLaborPartDtl(INV_PRT_SLS_PART_SUB_TOTTMsg subTotTmsg, String dtlDplyFlg, String mdse_cd) {
    // QC#50078 2019/05/07 Mod End 
        // 2017/02/27 QC#16575 UPD E N D

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", subTotTmsg.invNum.getValue());
        ssmParam.put("shipToCust", subTotTmsg.shipToCustCd.getValue());
        ssmParam.put("poNo", subTotTmsg.custIssPoNum.getValue());
        ssmParam.put("custCodes1", subTotTmsg.firstBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes2", subTotTmsg.scdBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes3", subTotTmsg.thirdBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes4", subTotTmsg.frthBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes5", subTotTmsg.fifthBllgAttrbValTxt.getValue());
        ssmParam.put("custCodes6", subTotTmsg.sixthBllgAttrbValTxt.getValue());
        ssmParam.put("fsrNo", subTotTmsg.fsrNum.getValue());
        ssmParam.put("serNo", subTotTmsg.serNum.getValue());
        // 2017/02/27 QC#16575 ADD START
        ssmParam.put("mdseCd", mdse_cd);
        // 2017/02/27 QC#16575 ADD E N D
        ssmParam.put("model", subTotTmsg.mdlNm.getValue());
        ssmParam.put("orderNo", subTotTmsg.cpoOrdNum.getValue());
        ssmParam.put("invChrgTpNm", subTotTmsg.invChrgTpNm.getValue());
        ssmParam.put("techNm", subTotTmsg.techNm.getValue());
        ssmParam.put("prodCd", "K%");
        //QC#25026
        //ssmParam.put("coaMdseTpCd", COA_PROJ.SOFTWARE);
        ssmParam.put("coaMdseTpCd", COA_PROJ_CD_ZZZ);
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("svcInvSrcTpCdD", SVC_INV_SRC_TP.DISPATCH);
        ssmParam.put("invChrgTpPN", PARTS_OR_LABOR + "-" + NON_COPIER);
        ssmParam.put("invChrgTpPC", PARTS_OR_LABOR + "-" + COPIER);
        //QC#17491
        //ssmParam.put("qtyFormat", FM_QTY);
        ssmParam.put("qtyFormat", FM_QTY4);
        ssmParam.put("amtFormat", FM_AMT);
        ssmParam.put("dtlDplyFlg", dtlDplyFlg);

        //QC#50078 2019/05/07 Add Start
        String statementId;

        if (ZYPConstant.FLG_ON_1.equals(manInvFlg)) {
            statementId = "getPartsLaborPartDtl_manInv";
        } else {
            statementId = "getPartsLaborPartDtl";
        }
        //QC#50078 2019/05/07 Add End

        //QC#50078 2019/05/07 Mod Start
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList(statementId, ssmParam);
        // return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPartsLaborPartDtl", ssmParam);
        //QC#50078 2019/05/07 Mod End
    }

    private boolean insertPartsLaborPartDtl(List<Map<String, Object>> resultMapList, INV_PRT_SLS_PART_SUB_TOTTMsg paramTmsg, String invTpCd, String ccySgnTxt) {

        INV_PRT_SLS_PART_DTLTMsg invPrtSlsPartDtlTmsg;
        List<INV_PRT_SLS_PART_DTLTMsg> invPrtSlsPartDtlList = new ArrayList<INV_PRT_SLS_PART_DTLTMsg>();

        BigDecimal subTotPk = paramTmsg.invPrtSlsPartSubTotPk.getValue();
        String invNum = paramTmsg.invNum.getValue();

        for (Map<String, Object> resultMap : resultMapList) {

            String ordQty = setNegateQty((String) resultMap.get(ORDER_QTY), invTpCd);
            String shipQty = setNegateQty((String) resultMap.get(SHIP_QTY), invTpCd);
            //String priceAmount = getAmtTxt((BigDecimal) resultMap.get("PRICE_AMOUNT"));
            String priceAmount = fmtAmtFromNumToStr((BigDecimal) resultMap.get(PRICE_AMOUNT), ccySgnTxt);
            //String lineTotAmtTxt = getAmtTxt(setNegate((BigDecimal) resultMap.get("LINE_TOTAL_AMOUNT"), invTpCd));
            String lineTotAmtTxt = fmtAmtFromNumToStr(setNegate((BigDecimal) resultMap.get(LINE_TOTAL_AMOUNT), invTpCd), ccySgnTxt);

            // SET INV_PRT_SLS_PART_DTL
            invPrtSlsPartDtlTmsg = new INV_PRT_SLS_PART_DTLTMsg();
            BigDecimal chrgDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_SLS_PART_DTL_SQ);

            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invPrtSlsPartDtlPk, chrgDtlPk);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invPrtSlsPartSubTotPk, subTotPk);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invNum, invNum);
            //QC#17516
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.dplyLineNum, (String) resultMap.get(DPLY_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.mdseCd, (String) resultMap.get(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invMdseDescTxt, (String) resultMap.get(DESCRIPTION));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invPrtOrdQtyTxt, ordQty);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invPrtShipQtyTxt, shipQty);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.dealUnitPrcAmtTxt, priceAmount);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.dealLineTotPrcAmtTxt, lineTotAmtTxt);
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invMdseDescColorFlg, ZYPConstant.FLG_OFF_N);

            //QC#17577
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invBolLineNum, (String) resultMap.get(INV_BOL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invLineNum, (String) resultMap.get(INV_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invLineSubNum, (String) resultMap.get(INV_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(invPrtSlsPartDtlTmsg.invLineSubTrxNum, (String) resultMap.get(INV_LINE_SUB_TRX_NUM));
            
            invPrtSlsPartDtlList.add(invPrtSlsPartDtlTmsg);
        }

        if (invPrtSlsPartDtlList.size() > 0) {
            // INSERT
            int result = EZDFastTBLAccessor.insert(invPrtSlsPartDtlList.toArray(new INV_PRT_SLS_PART_DTLTMsg[invPrtSlsPartDtlList
                    .size()]));
            if (result != invPrtSlsPartDtlList.size()) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_SLS_PART_DTLTMsg().getTableName(), String.valueOf(subTotPk)));
                return false;
            }
//            normCnt += result;
        }
        return true;
    }

    /**
     * @param invoiceNum
     * @return
     */
    private boolean updateInv(String invoiceNum) {

        INVTMsg invTmsg = new INVTMsg();
        invTmsg.glblCmpyCd.setValue(glblCmpyCd);
        invTmsg.invNum.setValue(invoiceNum);

        INVTMsg resultMsg = (INVTMsg) EZDTBLAccessor.findByKeyForUpdate(invTmsg);

        if (resultMsg == null) {
            S21InfoLogOutput.println(NWCM0109E, toArray(invTmsg.getTableName(), invTmsg.invNum.getValue()));
            return false;
        }
        resultMsg.invPrintStsCd.setValue(INV_PRT_STS_2);
        // START 2018/01/09 E.Kameishi [QC#20312,ADD]
        if (!ZYPCommonFunc.hasValue(resultMsg.invPrintCratStsCd)) {
            ZYPEZDItemValueSetter.setValue(resultMsg.invPrintCratStsCd, INV_PRT_STS_1);
        } else if (INV_PRT_STS_1.equals(resultMsg.invPrintCratStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(resultMsg.invPrintCratStsCd, INV_PRT_STS_2);
        }
        // END2018/01/09 E.Kameishi [QC#20312,ADD]
        EZDTBLAccessor.update(resultMsg);
        return true;
    }

    /**
     * execute DB process class
     */
    private class ExecConsTermsWaitDBProc extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            Map<String, Map<String, NMZC610001PMsg>> custInvMap = new HashMap<String, Map<String, NMZC610001PMsg>>();
            boolean isEasyPac1Flg = false;
            Map<String, String> billToCustInfo = new HashMap<String, String>();
            dataReCreationFlg = false;
            while (rs.next()) {
                
                try {
                // GET Customer Invoice Information
                String dsBizAreaCd = rs.getString(DS_BIZ_AREA_CD);
                String billToCustCd = rs.getString(BILL_TO_CUST_CD);
                String invTp = rs.getString(INV_TP_CD); //QC#22278
                String crRebillRsnCatgCd = rs.getString(CR_REBIL_RSN_CATG_CD); //QC#22278
                
                getCustInvInfoEx(custInvMap, rs);
                NMZC610001PMsg nmzc6100PMsgForRule = getNMZC610001PMsgForRule(dsBizAreaCd, billToCustCd);
                String custBllgTpCd = CUST_BLLG_TP.DAILY;
                if (nmzc6100PMsgForRule.InvoiceRuleList.getValidCount() > 0) {
                    custBllgTpCd = nmzc6100PMsgForRule.InvoiceRuleList.no(0).custBllgTpCd.getValue();
                }
                billToCustInfo = new HashMap<String, String>();
                isEasyPac1Flg = isEasyPac2(rs, billToCustInfo);
                //((Daily or AR Invoice) and Not EasyPAC) or excluded consolidated CM
                if (((CUST_BLLG_TP.DAILY.equals(custBllgTpCd) 
//                        || SYS_SRC.S21_ACCOUNTING_AR.equals(rs.getString(SYS_SRC_CD)) //QC#17962 del
                        ) 
                        && !isEasyPac1Flg)
                        || INV_PRT_STS_3.equals(rs.getString(INV_PRINT_STS_CD))) //Exclude Consolidated Credit Memo
                    {
                    INV_PRT_CTRLTMsg invPrtCtrlTMsg = getInvCtrl(rs.getBigDecimal(INV_PRT_CTRL_PK));
                    INV_PRT_HDRTMsg invPrtHdrTMsg = getInvHdr(rs.getBigDecimal(INV_PRT_CTRL_PK));
                    setInitInvPrtCtrlForConsTermsWait(nmzc6100PMsgForRule, invPrtCtrlTMsg);

                    invPrtCtrlTMsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
                    invPrtHdrTMsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);

                    if (INV_TP.CREDIT_MEMO.equals(rs.getString(INV_TP_CD))) {
                        invPrtCtrlTMsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                    }
                    if (BigDecimal.ZERO.compareTo(rs.getBigDecimal(INV_AMT)) == 0) {
                        invPrtCtrlTMsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                    }
                    if (FORMAT_TP_SVC.equals(rs.getString(FORMAT_KEY)) 
                            && ZYPConstant.FLG_OFF_N.equals(rs.getString(INV_PRINT_FLG)) 
                            && invPrintLimit.compareTo(rs.getBigDecimal(INV_AMT)) > 0) {
                        if (rs.getBigDecimal(INV_AMT).compareTo(new BigDecimal(5)) < 0) {
                            invPrtCtrlTMsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                        }
                    }

                    if (ZYPConstant.FLG_OFF_N.equals(rs.getString(INV_PRINT_FLG))) {
                        invPrtCtrlTMsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                        invPrtCtrlTMsg.invFtpProcStsCd.setValue(PROC_STS.IN_COMPLETED);
                    }
                    if (INV_PRT_STS_0.equals(rs.getString(INV_PRINT_STS_CD))) {
                        invPrtCtrlTMsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
                    }
                    // START 2019/03/12 H.Ikeda [QC#30687, MOD]
                    //if (ZYPConstant.FLG_ON_Y.equals(rs.getString(PMT_CC_FLG))) {
                    if (ZYPConstant.FLG_ON_Y.equals(rs.getString(PMT_CC_FLG)) && chkCrCardChrgCpltCd(rs.getString("CR_CARD_CHRG_CPLT_CD"))) {
                    // END   2019/03/12 H.Ikeda [QC#30687, MOD]
                        // invPrtHdrTmsg.
                        //invPrtHdrTMsg.invBalAmtTxt.setValue("0");
                        String ccySgnTxt = rs.getString(CCY_SGN_TXT);
                        invPrtHdrTMsg.invBalAmtTxt.setValue(ccySgnTxt + "0.00");
                    }
                    //QC#22278
                    // Case Daily and Internal Rebill
                    //   Credit Rebill Reason Category is Internal 
                    //   Invoice Type = Invoice
                    if (CR_REBIL_RSN_CATG.INTERNAL.equals(crRebillRsnCatgCd)
                            && INV_TP.INVOICE.equals(invTp)) {
                        invPrtCtrlTMsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
//QC#25491
//                        invPrtCtrlTMsg.invFtpProcStsCd.setValue(PROC_STS.IN_COMPLETED);
                    }
                    INV_PRT_HDRTMsg[] invPrtHdrTmsgArray = null;
                    INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray = null;

                    List<Map<String, Object>> pmtTermInfoList = getPmtTermInfoList(rs.getString(PMT_TERM_CD));
                    if (!pmtTermInfoList.isEmpty()) {
                        invPrtCtrlTmsgArray = new INV_PRT_CTRLTMsg[pmtTermInfoList.size()];
                        invPrtHdrTmsgArray  = new INV_PRT_HDRTMsg[pmtTermInfoList.size()];
                        pmtSplitProcDaily(invPrtCtrlTmsgArray, invPrtHdrTmsgArray, invPrtCtrlTMsg, invPrtHdrTMsg, rs, pmtTermInfoList);
                    } else {
                        invPrtCtrlTmsgArray = new INV_PRT_CTRLTMsg[1];
                        invPrtHdrTmsgArray = new INV_PRT_HDRTMsg[1];
                        invPrtCtrlTmsgArray[0] = invPrtCtrlTMsg;
                        invPrtHdrTmsgArray[0] = invPrtHdrTMsg;
                    }
                    boolean result = true;
                    result = updateCtrlHdr(invPrtCtrlTmsgArray, invPrtHdrTmsgArray);
                    if (!result) {
                        rollback();
                        termCd = TERM_CD.WARNING_END;
                        ++errCnt;
                        continue;
                    }
                    if (INV_PRT_STS_3.equals(rs.getString(INV_PRINT_STS_CD))) {
                        result = updateInv(rs.getString(INV_NUM));
                        if (!result) {
                            rollback();
                            termCd = TERM_CD.WARNING_END;
                            ++errCnt;
                            continue;
                        }
                    }
                    if (result) {
                        commit();
                        ++normCnt;
                    }
                }
                } catch(NWCB010001BusinessException e) {
                    setMailMessage(e.getNumber(), e.getMsgId(), e.getMsgParam());
                    rollback();
                    termCd = TERM_CD.WARNING_END;
                    continue;
                }
            }
            return true;
        }
    }

    /**
     * execute DB process class
     */
    private class ExecConsTermsArrvDBProc extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
            String preInvNum = "";
            String preConsNum = "";

            String invNum = "";
            String consNum = "";
            Map<String, Map<String, BigDecimal>> invAmountByConsNum = new HashMap<String, Map<String, BigDecimal>>();
            List<String> invList = new ArrayList<String>();

            BigDecimal consInvAmt = BigDecimal.ZERO;
            BigDecimal consBalAmt = BigDecimal.ZERO;
            BigDecimal consChrgAmt = BigDecimal.ZERO;
            BigDecimal consFreAmt = BigDecimal.ZERO;
            BigDecimal consTaxAmt = BigDecimal.ZERO;
            BigDecimal consPrePayAmt = BigDecimal.ZERO;

            boolean sameLineBizTpByCons = true;
            boolean sameDsBizAreaByCons = true;
            boolean isEasyPac1Flg = false;
            boolean hasFooterMsg = false;
            boolean batchReviewFlg = false;
            boolean hasCrPaymentFlg = false;  //QC#10850
//            String lineBiz = "";
//            String bizArea = "";

            Map<String, Object> rsMap = new HashMap<String, Object>();
            Map<String, Object> prevRsMap = new HashMap<String, Object>();
            Map<String, Map<String, NMZC610001PMsg>> custInvMap = new HashMap<String, Map<String, NMZC610001PMsg>>();
            Map<String, String> billToCustInfo = new HashMap<String, String>();
            List<String> conslBillDeleteList = new ArrayList<String>();
            Map<String, Boolean> booleanList = new HashMap<String, Boolean>();
            boolean result = true;
            dataReCreationFlg = false;
            Map<String, List<String>> bizAreaBizTpSetMap = new HashMap<String, List<String>>(); // 2019/02/20 S21_NA#30144 Add
            while (rs.next()) {

                rsMap = resultSetMap(rs);
                invNum = rs.getString(INV_NUM);
                consNum = rs.getString(CONS_BILL_NUM);
                String dsBizAreaCd = rs.getString(DS_BIZ_AREA_CD);
                String lineBizTpCd = rs.getString(LINE_BIZ_TP_CD);
                String consBillToCustCd = rs.getString(CONS_BILL_TO_CD);
                String billToCustCd = rs.getString(BILL_TO_CUST_CD);
                BigDecimal invPrtCtrlPk = rs.getBigDecimal(INV_PRT_CTRL_PK);
                String invTp = rs.getString(INV_TP_CD);
                //boolean isConsolidated = true;  //20160401 add naga
                String pmtCreditFlg = rs.getString(PMT_CC_FLG);     //20160728 add
                boolean isCrRebillCollectedInSameBillFlg = false;
                boolean skipFlg = false; // QC#53014 2019/09/17 Add

                //QC#25026
                // QC#53014 2019/09/17 Del Start
                // BigDecimal origConslBillPk = rs.getBigDecimal(ORIG_CONSL_BILL_PK);
                // if (hasValue(origConslBillPk)) {
                //     dataReCreationFlg = true;
                // }
                // QC#53014 2019/09/17 Del End
                

                // If Process Mode is Re-Print ,Logical Delete
                //if (REPRINT_MODE.equals(procMode)) {
                //   logicalDeleteForConsl(consNum, conslBillDeleteList);
                //}
                
                // loop for each invoice
                boolean sameInvFlg = false;
                if (invNum == null) {
                    if (preInvNum != null) {
                        sameInvFlg = false;
                    } else {
                        sameInvFlg = true;
                    }
                } else {
                    sameInvFlg = invNum.equals(preInvNum);
                }
                if (sameInvFlg) {
                    continue;
                }

                
                //Insert Print Data for Consolidation Bill
                if (hasValue(preConsNum) && !preConsNum.equals(consNum)) {
                    Map<String, BigDecimal> amtMap = new HashMap<String, BigDecimal>();
                    amtMap.put("consInvAmt", consInvAmt);
                    amtMap.put("consBalAmt", consBalAmt);
                    amtMap.put("consChrgAmt", consChrgAmt);
                    amtMap.put("consFreAmt", consFreAmt);
                    amtMap.put("consTaxAmt", consTaxAmt);
                    amtMap.put("consPrePayAmt", consPrePayAmt);
                    //QC#6294
                    invAmountByConsNum.put(preConsNum, amtMap);
                    
                    BigDecimal wkConslBillPk = (BigDecimal) prevRsMap.get(CONS_BILL_NUM);
                    String wkStrConsBillNum = wkConslBillPk == null ? null : String.valueOf(wkConslBillPk);
                    logicalDeleteForConsl(wkStrConsBillNum, conslBillDeleteList);
                    // Do each ConsBillNumber
                    // boolean chkResult = doAfterAllInvEachCons(invAmountByConsNum, prevRsMap, custInvMap, billToCustInfo, booleanList, invList);  // 2019/02/20 S21_NA#30144 Mod
                    boolean chkResult = doAfterAllInvEachCons(invAmountByConsNum, prevRsMap, custInvMap, billToCustInfo, booleanList, invList, bizAreaBizTpSetMap);
                    if (!chkResult) result = false;
                    if (!result) {
                        rollback();
                        termCd = TERM_CD.WARNING_END;
                        ++errCnt;
                        prevRsMap = new HashMap<String, Object>();
//                        continue;
                    } else {
                        ++normCnt;
                        commit();
                    }
                    
                    result = true;
                    consInvAmt = BigDecimal.ZERO;
                    consBalAmt = BigDecimal.ZERO;
                    consChrgAmt = BigDecimal.ZERO;
                    consFreAmt = BigDecimal.ZERO;
                    consTaxAmt = BigDecimal.ZERO;
                    consPrePayAmt = BigDecimal.ZERO;
                    dataReCreationFlg = false;
                    invList = new ArrayList<String>();
                    
                    sameDsBizAreaByCons = true;
                    sameLineBizTpByCons = true;
//                    bizArea = "";
//                    lineBiz = "";
                    hasFooterMsg = false;
                    hasCrPaymentFlg = false;  //QC#10850
                    bizAreaBizTpSetMap = new HashMap<String, List<String>>(); // 2019/02/20 S21_NA#30144 Add
                    // QC#53015 2019/09/05 Add Start
                    batchReviewFlg = false;
                    // QC#53015 2019/09/05 Add End
                }
                
                //Amount
                BigDecimal invAmt = rs.getBigDecimal(INV_AMT);
                BigDecimal chrgAmt = rs.getBigDecimal(INV_CHARGE_AMT);
                BigDecimal freAmt = rs.getBigDecimal(FRT_AMT);
                BigDecimal taxAmt = rs.getBigDecimal(TAX_AMT);
                BigDecimal balanceAmt = rs.getBigDecimal(BALANCE_AMT);
                BigDecimal prePayAmt = rs.getBigDecimal(PRE_PMT_AMT);
                if (INV_TP.CREDIT_MEMO.equals(invTp)) {
                    //isConsolidated = isInConsolidated(invNum);  //20160401 add naga
                    invAmt = invAmt.negate();
                    //QC#21990
                    //balanceAmt = balanceAmt.negate();   //20160401 add naga
                    chrgAmt = chrgAmt.negate();
                    freAmt = freAmt.negate();
                    taxAmt = taxAmt.negate();
                    prePayAmt = prePayAmt.negate();
                }
                //QC#22278
                //Credit & Rebill relation
                //My AR balance is ZERO.
                //Cash Applied invoice/Credit Memo AR balance is also ZERO.
                //Cash Applied invoice/Credit Memo is in same bill#.
                // QC#53014 2019/09/17 Mod Start
                //isCrRebillCollectedInSameBillFlg = isCrRebillCollectedInSameBill(consNum, invNum);
                //if (isConsolidated) {   //20160401 add naga
                //if (!isCrRebillCollectedInSameBillFlg) {
                //    consInvAmt = consInvAmt.add(invAmt);
                //    // START 2019/03/12 H.Ikeda [QC#30687, MOD]
                //    //if (ZYPConstant.FLG_OFF_N.equals(pmtCreditFlg)) {
                //    if (ZYPConstant.FLG_OFF_N.equals(pmtCreditFlg) || (ZYPConstant.FLG_ON_Y.equals(pmtCreditFlg) && !chkCrCardChrgCpltCd(rs.getString("CR_CARD_CHRG_CPLT_CD")))) {
                //    // END 2019/03/12 H.Ikeda [QC#30687, MOD]
                //        consBalAmt = consBalAmt.add(balanceAmt);
                //    }
                //    consChrgAmt = consChrgAmt.add(chrgAmt);
                //    consFreAmt = consFreAmt.add(freAmt);
                //    consTaxAmt = consTaxAmt.add(taxAmt);
                //    consPrePayAmt = consPrePayAmt.add(prePayAmt);
                //}
                skipFlg = isOrigOrCreditInvoice(consNum, invNum);
                //if (isConsolidated) {   //20160401 add naga
                if (!skipFlg) {
                    consInvAmt = consInvAmt.add(invAmt);
                    // START 2019/03/12 H.Ikeda [QC#30687, MOD]
                    //if (ZYPConstant.FLG_OFF_N.equals(pmtCreditFlg)) {
                    if (ZYPConstant.FLG_OFF_N.equals(pmtCreditFlg) || (ZYPConstant.FLG_ON_Y.equals(pmtCreditFlg) && !chkCrCardChrgCpltCd(rs.getString("CR_CARD_CHRG_CPLT_CD")))) {
                    // END 2019/03/12 H.Ikeda [QC#30687, MOD]
                        consBalAmt = consBalAmt.add(balanceAmt);
                    }
                    consChrgAmt = consChrgAmt.add(chrgAmt);
                    consFreAmt = consFreAmt.add(freAmt);
                    consTaxAmt = consTaxAmt.add(taxAmt);
                    consPrePayAmt = consPrePayAmt.add(prePayAmt);
                }
                // QC#53014 2019/09/17 Mod End

                //Batch Type
                String batchTp = rs.getString(BATCH_TP);
                if (INV_PRT_BAT_TP.NO_REVIEW.equals(batchTp)) {
                    invList.add(invNum);
                }
                
                INV_PRT_CTRLTMsg invPrtCtrlTmsg = getInvCtrl(invPrtCtrlPk);
                // GET Customer Invoice Information
                if (!custInvMap.containsKey(dsBizAreaCd)
                        || !custInvMap.get(dsBizAreaCd).containsKey(notValue(consBillToCustCd, billToCustCd))) {
                    NMZC610001PMsg pMsg = getNMZC610001PMsgForRule(dsBizAreaCd, notValue(consBillToCustCd, billToCustCd));
                    Map<String, NMZC610001PMsg> custInvValueMap = new HashMap<String, NMZC610001PMsg>();
                    custInvValueMap.put(notValue(consBillToCustCd, billToCustCd), pMsg);
                    custInvMap.put(dsBizAreaCd, custInvValueMap);
                }

                billToCustInfo = new HashMap<String, String>();
                // Judges Easy Pac1
                isEasyPac1Flg = isEasyPac2(rs, billToCustInfo);

                INV_PRT_HDRTMsg invPrtHdrTmsg = getInvHdr(invPrtCtrlPk);
                if (ZYPCommonFunc.hasValue(invPrtHdrTmsg.firstUsgInvDescTxt)
                        || ZYPCommonFunc.hasValue(invPrtHdrTmsg.scdUsgInvDescTxt)
                        || ZYPCommonFunc.hasValue(invPrtHdrTmsg.thirdUsgInvDescTxt)) {
                    hasFooterMsg = true;
                }
                //QC#10850
                //Footer(Meter Charge) and Credit Card
                if (PMT_TERM_CASH_DISC.CREDIT_CARD.equals(invPrtHdrTmsg.pmtTermCashDiscCd.getValue())) {
                    hasCrPaymentFlg = true;
                }
                
                ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.conslBillNum, rs.getString(CONS_BILL_NUM));
                ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.conslBillInvDt, rs.getString(CONS_BILL_DT));
                ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.conslBillTotAmt, rs.getBigDecimal(CONSL_TOT_DEAL_NET_AMT));
                ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.conslBillInvCratPsnCd, rs.getString(CONS_CREATE_USER));
                //QC#22278
                // QC#53014 2019/09/17 Mod Start
                // if (isCrRebillCollectedInSameBillFlg) {
                //     ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.exclBillTxt, "YES");
                // } else {
                //     ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.exclBillTxt, "NO");
                // }
                if (skipFlg) {
                    ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.exclBillTxt, "YES");
                } else {
                    ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.exclBillTxt, "NO");
                }
                // QC#53014 2019/09/17 Mod End
//                if (INV_TP.CREDIT_MEMO.equals(invTp)) {
//                    if (isConsolidated) {
//                        invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
//                        invPrtHdrTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);
//                    } else {
//                        invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
//                        invPrtHdrTmsg.conslBillFlg.setValue(ZYPConstant.FLG_OFF_N);
//                    }
//                }

                INV_PRT_HDRTMsg[] invPrtHdrTmsgArray = null;
                INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray = null;

                invPrtCtrlTmsgArray = new INV_PRT_CTRLTMsg[1];
                invPrtHdrTmsgArray = new INV_PRT_HDRTMsg[1];
                invPrtCtrlTmsgArray[0] = invPrtCtrlTmsg;
                invPrtHdrTmsgArray[0] = invPrtHdrTmsg;

                
                // Insert INV_PRT_CTRL
                boolean chkCtrlResult = updateCtrl(invPrtCtrlTmsgArray, invNum);
                if (!chkCtrlResult) result = false;
                // Insert INV_PRT_HDR
                boolean chkHdrResult = updateHdr(invPrtHdrTmsgArray, invPrtCtrlTmsgArray, invNum);
                if (!chkHdrResult) result = false;
                if (ZYPConstant.FLG_OFF_N.equals(invPrtCtrlTmsg.conslBillFlg.getValue())) {
//                    preConsNum = consNum;
//                    preInvNum = invNum;
//                    prevRsMap = rsMap;
                    continue;
                }

                boolean chkFleetResult = updateFleet(invNum, consNum);
                if (!chkFleetResult) result = false;
                boolean chkMaintResult = updateMaint(invNum, consNum);
                if (!chkMaintResult) result = false;
                boolean chkSlsResult = updateSlsPart(invNum, consNum);
                if (!chkSlsResult) result = false;

                // QC#53015 2019/09/05 Del Start
                // batchReviewFlg = false;
                // QC#53015 2019/09/05 Del End
                // equal all BizArea & LineBiz each ConsBillNum
                // 2019/02/20 S21_NA#30144 Mod Start
                // if (ZYPCommonFunc.hasValue(bizArea) && ZYPCommonFunc.hasValue(lineBiz) && sameDsBizAreaByCons
                //         && sameLineBizTpByCons) {
                //     sameDsBizAreaByCons = bizArea.equals(dsBizAreaCd);
                //     sameLineBizTpByCons = lineBiz.equals(lineBizTpCd);
                    // 2019/02/08 S21_NA#30144 Add Mod
                    // bizArea = dsBizAreaCd;
                    // lineBiz = lineBizTpCd;
                // } 
                // bizArea = dsBizAreaCd;
                // lineBiz = lineBizTpCd;
                // 2019/02/08 S21_NA#30144 Add End
                if (ZYPCommonFunc.hasValue(dsBizAreaCd) && ZYPCommonFunc.hasValue(lineBizTpCd)) {
                    if (bizAreaBizTpSetMap.containsKey(lineBizTpCd)) {
                        if (!bizAreaBizTpSetMap.get(lineBizTpCd).contains(dsBizAreaCd)) {
                            bizAreaBizTpSetMap.get(lineBizTpCd).add(dsBizAreaCd);
                        }
                    } else {
                        bizAreaBizTpSetMap.put(lineBizTpCd, new ArrayList<String>(Arrays.asList(dsBizAreaCd)));
                    }
                }
                // 2019/02/20 S21_NA#30144 Mod End
                
                String invPrtBatNm = rs.getString(BATCH_TP_NM);
                if (ZYPCommonFunc.hasValue(invPrtBatNm) && invPrtBatNm.matches(".*" + "REVIEW" + ".*")) {
                    batchReviewFlg = true;
                }
                booleanList.put("isEasyPac1Flg", isEasyPac1Flg);
                booleanList.put("sameParamByCons", sameDsBizAreaByCons && sameLineBizTpByCons);
                booleanList.put("hasFooterMsg", hasFooterMsg);
                booleanList.put("batchReviewFlg", batchReviewFlg);
                booleanList.put("hasCrPaymentFlg", hasCrPaymentFlg);  //QC#10850
                
                // for nextloop
                preConsNum = consNum;
                preInvNum = invNum;
                prevRsMap = rsMap;
            }
            if (!prevRsMap.isEmpty()) {
                Map<String, BigDecimal> amtMap = new HashMap<String, BigDecimal>();
                amtMap.put("consInvAmt", consInvAmt);
                amtMap.put("consBalAmt", consBalAmt);
                amtMap.put("consChrgAmt", consChrgAmt);
                amtMap.put("consFreAmt", consFreAmt);
                amtMap.put("consTaxAmt", consTaxAmt);
                amtMap.put("consPrePayAmt", consPrePayAmt);
                invAmountByConsNum.put(consNum, amtMap);

                BigDecimal wkConslBillPk = (BigDecimal) prevRsMap.get(CONS_BILL_NUM);
                String wkStrConsBillNum = wkConslBillPk == null ? null : String.valueOf(wkConslBillPk);
                logicalDeleteForConsl(wkStrConsBillNum, conslBillDeleteList);
                // Do each ConsBillNumber
                // boolean chkResult = doAfterAllInvEachCons(invAmountByConsNum, prevRsMap, custInvMap, billToCustInfo, booleanList, invList); // 2019/02/20 S21_NA#30144 Mod
                boolean chkResult = doAfterAllInvEachCons(invAmountByConsNum, prevRsMap, custInvMap, billToCustInfo, booleanList, invList, bizAreaBizTpSetMap);
                if (!chkResult) result = false;
                if (!result) {
                    rollback();
                    termCd = TERM_CD.WARNING_END;
                    ++errCnt;
                } else {
                    commit();
                    ++normCnt;
                }
            }

            } catch (NWCB010001BusinessException e) {
                setMailMessage(e.getNumber(), e.getMsgId(), e.getMsgParam());
                rollback();
                termCd = TERM_CD.WARNING_END;
                ++errCnt;
            }
            return true;
        }
    }

    private INV_PRT_CTRLTMsg getInvCtrl(BigDecimal invPrtCtrlPk) {

        INV_PRT_CTRLTMsg invPrtCtrlTMsg = new INV_PRT_CTRLTMsg();
        invPrtCtrlTMsg.glblCmpyCd.setValue(glblCmpyCd);
        invPrtCtrlTMsg.invPrtCtrlPk.setValue(invPrtCtrlPk);

        return (INV_PRT_CTRLTMsg) S21FastTBLAccessor.findByKeyForUpdate(invPrtCtrlTMsg);
    }

    private INV_PRT_HDRTMsg getInvHdr(BigDecimal invPrtCtrlPk) {

        INV_PRT_HDRTMsg invPrtHdrTMsg = new INV_PRT_HDRTMsg();
        invPrtHdrTMsg.glblCmpyCd.setValue(glblCmpyCd);
        invPrtHdrTMsg.invPrtCtrlPk.setValue(invPrtCtrlPk);

        return (INV_PRT_HDRTMsg) S21FastTBLAccessor.findByKeyForUpdate(invPrtHdrTMsg);
    }

    private boolean isEasyPac2(ResultSet rs, Map<String, String> billToCustInfo) throws SQLException, NWCB010001BusinessException {

        boolean judge1 = orderReason(rs);

        boolean judge2 = billToCust2(rs, billToCustInfo);
        if (judge1 && judge2) {
            return true;
        }
        return false;

    }

    @SuppressWarnings("unchecked")
    private boolean billToCust2(ResultSet rs, Map<String, String> billToCustInfo) throws SQLException, NWCB010001BusinessException {
        
        String key = createCacheKey(SPLY_CONTR_BY_BILL_TO_ACCT_CUST, rs.getString(CONS_BILL_TO_ACCT) + rs.getString(ORD_DT));
        List<Map<String, Object>> billToCustList = (List<Map<String, Object>>) this.cache.get(key);
        if (billToCustList == null) {
            Map<String, String> param = new HashMap<String, String>();
            param = new HashMap<String, String>();
            param.put("glblCmpyCd", glblCmpyCd);
            param.put("billToCd", rs.getString(CONS_BILL_TO_ACCT));
            param.put("orderDt", rs.getString(ORD_DT));
            param.put("fmRate", FM_RATE3);
            param.put("fmQty", FM_QTY3);

            billToCustList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getByBillToCustAcct", param);
        }
        
        if (billToCustList == null || billToCustList.size() == 0) {
            return false;
        }

        String amtRate = (String) billToCustList.get(0).get(AMT_RATE);
        String quotQty = (String) billToCustList.get(0).get(QUOT_QTY);

        billToCustInfo.put(AMT_RATE, amtRate);
        billToCustInfo.put(QUOT_QTY, quotQty);
        return true;
    }

//    /**
//     * @param invNum String
//     * @return boolean
//     */
//    private boolean isInConsolidated(String invNum) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("invNum", invNum);
//
//        Integer resCnt = (Integer) ssmBatchClient.queryObject("isInConsolidated", ssmParam);
//        if (resCnt.intValue() > 0) {
//            return true;
//        }
//        return false;
//    }
    /**
     * @param invNum String
     * @return boolean
     */
    private boolean isCrRebillCollectedInSameBill(String consNum, String arTrxNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("conslBillNum", consNum);
        ssmParam.put("arTrxNum", arTrxNum);

        Integer resCnt = (Integer) ssmBatchClient.queryObject("isCrRebillCollectedInSameBill", ssmParam);
        if (resCnt.intValue() > 0) {
            return true;
        }
        return false;
    }

    
    /**
     * @param invNum String
     * @return boolean
     */
    private boolean isOrigOrCreditInvoice(String consNum, String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("conslBillNum", consNum);
        ssmParam.put("invNum", invNum);
        ssmParam.put("invTpCd", INV_TP.CREDIT_MEMO);

        Integer resCnt = (Integer) ssmBatchClient.queryObject("isOrigOrCreditInvoice", ssmParam);
        if (resCnt.intValue() > 0) {
            return true;
        }
        return false;
    }
    
    /**
     * @param invPrtCtrlTmsgArray INV_PRT_CTRLTMsg[]
     * @param rs ResultSet
     * @return boolean
     */
    private boolean updateCtrl(INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray, String invoiceNum) {

        INV_PRT_CTRLTMsg[] insInvPrtCtrlTmsgArray = new INV_PRT_CTRLTMsg[invPrtCtrlTmsgArray.length - 1];

        for (int i = 1; i < invPrtCtrlTmsgArray.length; i++) {
            INV_PRT_CTRLTMsg invPrtCtrlTmsg = invPrtCtrlTmsgArray[i];
            invPrtCtrlTmsg.invPrtCtrlPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_CTRL_SQ));
            insInvPrtCtrlTmsgArray[i - 1] = invPrtCtrlTmsg;
        }

        EZDTBLAccessor.update(invPrtCtrlTmsgArray[0]);

        if (insInvPrtCtrlTmsgArray.length > 0) {
            int result = EZDFastTBLAccessor.insert(insInvPrtCtrlTmsgArray);
            if (result != insInvPrtCtrlTmsgArray.length) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_CTRLTMsg().getTableName(), invoiceNum));
                return false;
            }
//            normCnt += result;
        }
        return true;
    }

    /**
     * @param invPrtHdrTmsgArray INV_PRT_HDRTMsg[]
     * @param invPrtCtrlTmsgArray INV_PRT_CTRLTMsg[]
     * @return
     */
    private boolean updateHdr(INV_PRT_HDRTMsg[] invPrtHdrTmsgArray, INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray, String invoiceNum) {

        INV_PRT_HDRTMsg[] insInvPrtHdrTmsgArray = new INV_PRT_HDRTMsg[invPrtHdrTmsgArray.length - 1];

        for (int i = 1; i < invPrtHdrTmsgArray.length; i++) {
            INV_PRT_HDRTMsg invPrtHdrTmsg = invPrtHdrTmsgArray[i];
            INV_PRT_CTRLTMsg invPrtCtrlTmsg = invPrtCtrlTmsgArray[i];

            invPrtHdrTmsg.invPrtCtrlPk.setValue(invPrtCtrlTmsg.invPrtCtrlPk.getValue());
            insInvPrtHdrTmsgArray[i - 1] = invPrtHdrTmsg;
        }
        EZDTBLAccessor.update(invPrtHdrTmsgArray[0]);

        if (insInvPrtHdrTmsgArray.length > 0) {

            int result = EZDFastTBLAccessor.insert(insInvPrtHdrTmsgArray);
            if (result != insInvPrtHdrTmsgArray.length) {
                S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_HDRTMsg().getTableName(), invoiceNum));
                return false;
            }
//            normCnt += result;
        }
        return true;
    }

    /**
     * @param invNum String
     * @param consNum String
     * @return boolean
     */
    private boolean updateFleet(String invNum, String consNum) {
        INV_PRT_FLEET_SUB_TOTTMsg invFleetSubTot = new INV_PRT_FLEET_SUB_TOTTMsg();
        invFleetSubTot.setSQLID("001");
        invFleetSubTot.setConditionValue("glblCmpyCd01", glblCmpyCd);
        invFleetSubTot.setConditionValue("invNum01", invNum);

        INV_PRT_FLEET_SUB_TOTTMsgArray invFleetSubTotArray = (INV_PRT_FLEET_SUB_TOTTMsgArray) EZDTBLAccessor
                .findByConditionForUpdate(invFleetSubTot);
        INV_PRT_FLEET_SUB_TOTTMsg[] invFleetSubTotList = new INV_PRT_FLEET_SUB_TOTTMsg[invFleetSubTotArray.length()];
        for (int i = 0; i < invFleetSubTotArray.length(); i++) {
            INV_PRT_FLEET_SUB_TOTTMsg tMsg = (INV_PRT_FLEET_SUB_TOTTMsg) invFleetSubTotArray.get(i);
            ZYPEZDItemValueSetter.setValue(tMsg.conslBillNum, consNum);
            invFleetSubTotList[i] = tMsg;
        }
        if (invFleetSubTotList != null && invFleetSubTotList.length > 0) {
            int resultNum = EZDFastTBLAccessor.update(invFleetSubTotList);
            if (resultNum != invFleetSubTotList.length) {
                //NWCM0110E=0,It failed to update @ Table.[@]
                S21InfoLogOutput.println(NWCM0110E, toArray(new INV_PRT_FLEET_SUB_TOTTMsg().getTableName(), invNum + ":" + consNum));
                return false;
            }
        }
        return true;
    }

    /**
     * @param invNum String
     * @param consNum String
     * @return boolean
     */
    private boolean updateMaint(String invNum, String consNum) {
        INV_PRT_MAINT_SUB_TOTTMsg invMaintSubTot = new INV_PRT_MAINT_SUB_TOTTMsg();
        invMaintSubTot.setSQLID("001");
        invMaintSubTot.setConditionValue("glblCmpyCd01", glblCmpyCd);
        invMaintSubTot.setConditionValue("invNum01", invNum);

        INV_PRT_MAINT_SUB_TOTTMsgArray invMaintSubTotArray = (INV_PRT_MAINT_SUB_TOTTMsgArray) EZDTBLAccessor
                .findByConditionForUpdate(invMaintSubTot);
        INV_PRT_MAINT_SUB_TOTTMsg[] invMaintSubTotList = new INV_PRT_MAINT_SUB_TOTTMsg[invMaintSubTotArray.length()];
        for (int i = 0; i < invMaintSubTotArray.length(); i++) {
            INV_PRT_MAINT_SUB_TOTTMsg tMsg = (INV_PRT_MAINT_SUB_TOTTMsg) invMaintSubTotArray.get(i);
            ZYPEZDItemValueSetter.setValue(tMsg.conslBillNum, consNum);
            invMaintSubTotList[i] = tMsg;
        }
        if (invMaintSubTotList != null && invMaintSubTotList.length > 0) {
            int resultNum = EZDFastTBLAccessor.update(invMaintSubTotList);
            if (resultNum != invMaintSubTotList.length) {
                //NWCM0110E=0,It failed to update @ Table.[@]
                S21InfoLogOutput.println(NWCM0110E, toArray(new INV_PRT_MAINT_SUB_TOTTMsg().getTableName(), invNum + ":" + consNum));
                return false;
            }
        }
        return true;
    }

    /**
     * @param invNum String
     * @param consNum String
     * @return boolean
     */
    private boolean updateSlsPart(String invNum, String consNum) {
        INV_PRT_SLS_PART_SUB_TOTTMsg invSlsPartSubTot = new INV_PRT_SLS_PART_SUB_TOTTMsg();
        invSlsPartSubTot.setSQLID("001");
        invSlsPartSubTot.setConditionValue("glblCmpyCd01", glblCmpyCd);
        invSlsPartSubTot.setConditionValue("invNum01", invNum);

        INV_PRT_SLS_PART_SUB_TOTTMsgArray invSlsPartSubTotArray = (INV_PRT_SLS_PART_SUB_TOTTMsgArray) EZDTBLAccessor
                .findByConditionForUpdate(invSlsPartSubTot);
        INV_PRT_SLS_PART_SUB_TOTTMsg[] invSlsPartSubTotList = new INV_PRT_SLS_PART_SUB_TOTTMsg[invSlsPartSubTotArray.length()];
        for (int i = 0; i < invSlsPartSubTotArray.length(); i++) {
            INV_PRT_SLS_PART_SUB_TOTTMsg tMsg = (INV_PRT_SLS_PART_SUB_TOTTMsg) invSlsPartSubTotArray.get(i);
            ZYPEZDItemValueSetter.setValue(tMsg.conslBillNum, consNum);
            invSlsPartSubTotList[i] = tMsg;
        }
        if (invSlsPartSubTotList != null && invSlsPartSubTotList.length > 0) {
            int resultNum = EZDFastTBLAccessor.update(invSlsPartSubTotList);
            if (resultNum != invSlsPartSubTotList.length) {
                //NWCM0110E=0,It failed to update @ Table.[@]
                S21InfoLogOutput.println(NWCM0110E, toArray(invSlsPartSubTot.getTableName(), invNum + ":" + consNum));
                return false;
            }
        }
        return true;
    }

    /**
     * @param invAmountByConsNum Map<String, Map<String, BigDecimal>>
     * @param rsMap ResultSet
     * @param custInvMap Map<String, Map<String, NMZC610001PMsg>>
     * @param custInfo Map<String, String>
     * @param booleanList Map<String, Boolean>
     * @return boolaen
     * @throws SQLException
     */
    private boolean doAfterAllInvEachCons(
            Map<String, Map<String, BigDecimal>> invAmountByConsNum, 
            Map<String, Object> rsMap,
            Map<String, Map<String, NMZC610001PMsg>> custInvMap, 
            Map<String, String> custInfo, 
            Map<String, Boolean> booleanList,
            List<String> invList,
            Map<String, List<String>> bizAreaBizTpSetMap) throws SQLException, NWCB010001BusinessException { // 2019/02/20 S21_NA#30144 Add Param

        String dsBizAreaCd = (String) rsMap.get(DS_BIZ_AREA_CD);
        String consBillToCustCd = (String) rsMap.get(CONS_BILL_TO_CD);
        String billToCustCd = (String) rsMap.get(BILL_TO_CUST_CD);
        BigDecimal conslBillPk = (BigDecimal) rsMap.get(CONS_BILL_NUM);
        String strConsBillNum = conslBillPk == null ? null : String.valueOf(conslBillPk);
        Map<String, BigDecimal> amtMap = invAmountByConsNum.get(strConsBillNum);

        boolean isEasyPac1Flg = booleanList.get("isEasyPac1Flg");
        boolean sameParamByCons = booleanList.get("sameParamByCons");
        boolean hasFooterMsg = booleanList.get("hasFooterMsg");
        boolean batchReviewFlg = booleanList.get("batchReviewFlg");
        boolean hasCrPaymentFlg = booleanList.get("hasCrPaymentFlg");  //QC#10850

        NMZC610001PMsg custInvInfo = custInvMap.get(dsBizAreaCd).get(notValue(consBillToCustCd, billToCustCd));
        INV_PRT_CTRLTMsg invPrtCtrlTMsg = setInitInvPrtCtrlForConslBill(custInvInfo);

        // String headerMsg = getCustInstInfoForConslBill(invAmountByConsNum, rsMap, custInfo, isEasyPac1Flg, sameParamByCons); // 2019/02/20 S21_NA#30144 Mod
        String headerMsg = getCustInstInfoForConslBill(invAmountByConsNum, rsMap, custInfo, isEasyPac1Flg, sameParamByCons, bizAreaBizTpSetMap);
        int lineCount = getLineCount(headerMsg);

        Map<String, Object> footerMsg = new HashMap<String, Object>();
        // 2019/06/14 QC#50844 Mod Start
        if (hasFooterMsg || hasCrPaymentFlg) {
            footerMsg = getFooterCommentForConsl(hasFooterMsg, hasCrPaymentFlg);
        }
        //if (hasFooterMsg) {
        //    footerMsg = getFooterCommentForConsl(hasCrPaymentFlg);  //QC#10850
        //}
        // 2019/06/14 QC#50844 Mod End

        //QC#26846
        String custBllgVcleCd = custInvInfo.InvoiceRuleList.no(0).custBllgVcleCd.getValue();
        if (hasValue(custBllgVcleCd) && CUST_BLLG_VCLE.WEB_BILLING_PRINT_ONLY.equals(custBllgVcleCd)) {
            invPrtCtrlTMsg.invPrtBrCd.setValue(INV_PRT_BR.WEB_BILLING_PRINT_ONLY);
            invPrtCtrlTMsg.invPrtBatTpCd.clear();
        } else if (hasValue(custBllgVcleCd) && CUST_BLLG_VCLE.WEB_BILLING_PRINT_AND_EMAIL.equals(custBllgVcleCd)) {
            invPrtCtrlTMsg.invPrtBrCd.setValue(INV_PRT_BR.WEB_BILLING_PRINT_AND_EMAIL);
            invPrtCtrlTMsg.invPrtBatTpCd.clear();
        // 2018/01/15 QC#29371 SRNO#13 Add Start
        } else if (hasValue(custBllgVcleCd) && CUST_BLLG_VCLE.STRATEGIC_ACCOUNTS_PRINT_ONLY.equals(custBllgVcleCd)) {
            invPrtCtrlTMsg.invPrtBrCd.setValue(INV_PRT_BR.STRATEGIC_ACCOUNTS_PRINT_ONLY);
            invPrtCtrlTMsg.invPrtBatTpCd.clear();
        } else if (hasValue(custBllgVcleCd) && CUST_BLLG_VCLE.STRATEGIC_ACCOUNTS_PRINT_AND_EMAIL.equals(custBllgVcleCd)) {
            invPrtCtrlTMsg.invPrtBrCd.setValue(INV_PRT_BR.STRATEGIC_ACCOUNTS_PRINT_AND_EMAIL);
            invPrtCtrlTMsg.invPrtBatTpCd.clear();
        // 2018/01/15 QC#29371 SRNO#13 Add End
        } else {
            // set BatchTypeName
            String batchTpCd = setBatchTypeName(batchReviewFlg, custBllgVcleCd, amtMap, invList, rsMap);
            invPrtCtrlTMsg.invPrtBatTpCd.setValue(batchTpCd);
        }

        INV_PRT_HDRTMsg[] invPrtHdrTmsgArray = null;
        INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray = null;

        INV_PRT_HDRTMsg invPrtHdrTmsg = new INV_PRT_HDRTMsg();
        
        setInvPrtCtrlCons(invPrtCtrlTMsg, rsMap, invAmountByConsNum);

        //QC#20141 mod Start
        //setInvPrtHdrCons(invPrtHdrTmsg, rsMap, headerMsg, footerMsg, amtMap);
        setInvPrtHdrCons(invPrtHdrTmsg, rsMap, headerMsg, footerMsg, amtMap, isEasyPac1Flg);
        //QC#20141 mod Start
        boolean result = false;

        invPrtCtrlTmsgArray = new INV_PRT_CTRLTMsg[1];
        invPrtHdrTmsgArray = new INV_PRT_HDRTMsg[1];
        invPrtCtrlTmsgArray[0] = invPrtCtrlTMsg;
        invPrtHdrTmsgArray[0] = invPrtHdrTmsg;

        result = insertCtrlForConsl(invPrtCtrlTmsgArray, strConsBillNum);
        if (!result) {
            return false;
        }
        result = insertHdrForConsl(invPrtHdrTmsgArray, invPrtCtrlTmsgArray, strConsBillNum);
        if (!result) {
            return false;
        }
        lineCount += FIXED_COUNT;

        // 2018/09/06 QC#28051 Add Start
        // Update the status of the data capture INV
        result = updateInv(conslBillPk);
        if (!result) {
            return false;
        }
        // 2018/09/06 QC#28051 Add End

        result = createPrintSummaryTable(rsMap, lineCount, isEasyPac1Flg);
        if (!result) {
            return false;
        }
        // Set Print Status 02:PRINTED
        result = setConslSts02(strConsBillNum);
        if (!result) {
            return false;
        }

        // 2018/09/06 QC#28051 Del Start
        // Update the status of the data capture INV
        //result = updateInv(conslBillPk);
        //if (!result) {
        //    return false;
        //}
        // 2018/09/06 QC#28051 Del End

        // Update the status of the data capture AR_TRX_BAL
        result = updateArTrxBal(conslBillPk);
        if (!result) {
            return false;
        }

        // UPDATE GroupKey of PrintData at Subtotal
        result = updateGroupKey(strConsBillNum);
        if (!result) {
            return false;
        }
        return true;
    }

    private INV_PRT_CTRLTMsg setInitInvPrtCtrlForConslBill(NMZC610001PMsg custInvInfo) {
        // Set init INV_PRT_CTRL
        INV_PRT_CTRLTMsg invPrtCtrlTmsg = new INV_PRT_CTRLTMsg();

        String vehicle = null;
        invPrtCtrlTmsg.conslBillFlg.setValue(ZYPConstant.FLG_ON_Y);

        if (custInvInfo.InvoiceRuleList.getValidCount() == 0) {
            // In the case of No data , Vehicle Print Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        }

        vehicle = custInvInfo.InvoiceRuleList.no(0).custBllgVcleCd.getValue();

        // Vehicle EDI
        if (CUST_BLLG_VCLE.EDI.equals(vehicle)) {
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.EDI_INVOICE);
        } else if (CUST_BLLG_VCLE.EMAIL_ONLY.equals(vehicle)) {
            // Vehicle Email Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        } else if (CUST_BLLG_VCLE.MANUAL_SPECIAL_BILL.equals(vehicle)) {
            // Vehicle SB Manual
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            //QC#19274
            //invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.MANUAL_BILLING);
        } else if (CUST_BLLG_VCLE.SPECIAL_BILL_NO_REVIEW.equals(vehicle)) {
            // Vehicle SB No Review
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.SPECIAL_BILLING);
        } else if (CUST_BLLG_VCLE.SPECIAL_BILL_REVIEW_REQD.equals(vehicle)) {
            // Vehicle SB Required
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.SPECIAL_BILLING);
        } else if (CUST_BLLG_VCLE.PRINT_ONLY.equals(vehicle)) {
            // Vehicle Print Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        } else if (CUST_BLLG_VCLE.PRINT_AND_EMAIL.equals(vehicle)) {
            // Vehicle Print & Email
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        } else if (CUST_BLLG_VCLE.DO_NOT_PRINT.equals(vehicle)) {
            // Vehicle Do Not Print
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        } else if (CUST_BLLG_VCLE.NEEDS_EMAIL_REVIEW.equals(vehicle)) {
            // Vehicle Need Email Review
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        //QC#6934
        } else if (CUST_BLLG_VCLE.DAILY_SUMMARY_EMAIL_AND_PRINT.equals(vehicle)) {
            // Vehicle Print & Email
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        //QC#26846
        } else if (CUST_BLLG_VCLE.WEB_BILLING_PRINT_ONLY.equals(vehicle)) {
            // Vehicle Print Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        } else if (CUST_BLLG_VCLE.WEB_BILLING_PRINT_AND_EMAIL.equals(vehicle)) {
            // Vehicle Print & Email
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        // 2018/01/15 QC#29371 SRNO#13 Add Start
        } else if (CUST_BLLG_VCLE.STRATEGIC_ACCOUNTS_PRINT_ONLY.equals(vehicle)) {
            // Vehicle Strategic Accounts Print Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        } else if (CUST_BLLG_VCLE.STRATEGIC_ACCOUNTS_PRINT_AND_EMAIL.equals(vehicle)) {
            // Vehicle Strategic Accounts Print & Email
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invFtpProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            if (dataReCreationFlg) {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
            } else {
                invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        // 2018/01/15 QC#29371 SRNO#13 Add End
        }
        return invPrtCtrlTmsg;
    }

    // 2019/02/20 S21_NA#30144 Mod Start
    // private String getCustInstInfoForConslBill(Map<String, Map<String, BigDecimal>> invAmountByConsNum, Map<String, Object> rsMap,
    //         Map<String, String> custInfo, boolean isEasyPac1Flg, boolean sameParamFlg) throws SQLException, NWCB010001BusinessException {
    private String getCustInstInfoForConslBill(Map<String, Map<String, BigDecimal>> invAmountByConsNum, Map<String, Object> rsMap,
            Map<String, String> custInfo, boolean isEasyPac1Flg, boolean sameParamFlg,
            Map<String, List<String>> bizAreaBizTpSetMap) throws SQLException, NWCB010001BusinessException {
        // 2019/02/20 S21_NA#30144 Mod End

        // 2019/02/20 S21_NA#30144 Del Start
        // String lineBizTpCd = LINE_BIZ_TP.ALL;
        // String dsBizAreaCd = DS_BIZ_AREA.CUSTOMER;
        // if (sameParamFlg) {
        //     lineBizTpCd = (String) rsMap.get(LINE_BIZ_TP_CD);
        //     dsBizAreaCd = (String) rsMap.get(DS_BIZ_AREA_CD);
        // }
        // 2019/02/20 S21_NA#30144 Del End
        String billToCustCd = (String) rsMap.get(BILL_TO_CUST_CD);
        BigDecimal consBillNum = (BigDecimal) rsMap.get(CONS_BILL_NUM);
        String strConsBillNum = consBillNum == null ? null : String.valueOf(consBillNum);
        //BigDecimal dueAmt = invAmountByConsNum.get(strConsBillNum).get("consBalAmt");
        //BigDecimal totalAmt = invAmountByConsNum.get(strConsBillNum).get("consInvAmt");

        String comment1 = nullConvEmp((String) rsMap.get(COMMENT1));
        String comment2 = nullConvEmp((String) rsMap.get(COMMENT2));
        String comment3 = nullConvEmp((String) rsMap.get(COMMENT3));
        String comment4 = nullConvEmp((String) rsMap.get(COMMENT4));
        // 2019/02/20 S21_NA#30144 Mod Start
        String lineBizTpCd = "";
        String messageBody = "";
        List<String> instMsgList = new ArrayList<String>();
        for (Map.Entry<String, List<String>> map : bizAreaBizTpSetMap.entrySet()) {
            lineBizTpCd = map.getKey();
            for (String dsBizAreaCd : map.getValue()) {
                messageBody = nullConvEmp(getCustInstInfoApi(lineBizTpCd, billToCustCd, dsBizAreaCd));
                if (hasValue(messageBody) && !instMsgList.contains(messageBody)) {
                    instMsgList.add(messageBody);
                }
            }
        }
        // String messageBody = nullConvEmp(getCustInstInfoApi(lineBizTpCd, billToCustCd, dsBizAreaCd));
        // 2019/02/20 S21_NA#30144 Mod End

        String easyPac1Msg = "";
        if (isEasyPac1Flg) {
            easyPac1Msg = getEasyPac1Msg(custInfo);
        }

        String fixedWord = nullConvEmp(replaceSystemLineSeparator((String) rsMap.get(INV_PRINT_MSG_TXT)));
        StringBuilder stackStr = new StringBuilder();
        List<String> strList = new ArrayList<String>();
        String returnStr = "";

        // 2019/06/13 QC#50817 Mod Start
        // START 2022/08/03 [QC#60129, MOD]
//        if (isExistsPmtCC(consBillNum)){
//            strList.add(invCcMsg01);
        List<Map<String, String>> pmtCCInfoList = getPmtCCInfo(consBillNum);
        if (pmtCCInfoList != null && 0 < pmtCCInfoList.size()) {
            List<String> errCCList = new ArrayList<String>();
            for (Map<String, String> pmtCCInfo : pmtCCInfoList) {
                if (!chkCrCardChrgCpltCd(pmtCCInfo.get("CR_CARD_CHRG_CPLT_CD"))) {
                    errCCList.add(pmtCCInfo.get("CR_CARD_LAST_DIGIT_NUM"));
                }
            }
            if (0 == errCCList.size()) {
                strList.add(invCcMsg01);
            } else {
                strList.add(invCcMsg05);
                for (String errCC: errCCList) {
                    stackStr.append(invCcMsg02).append(errCC);
                    strList.add(stackStr.toString());
                    stackStr = new StringBuilder();
                }
            }
        // END   2022/08/03 [QC#60129, MOD]
        }

        //// TotalAmout - DueAmount = 0 (Unpaid)
        //if (BigDecimal.ZERO.compareTo(totalAmt.subtract(dueAmt)) != 0) {
        //    strList.add(invCcMsg01);
        //}
        // 2019/06/13 QC#50817 Mod End

        if (ZYPCommonFunc.hasValue(easyPac1Msg)) {
            strList.add(easyPac1Msg);
        } else {
            strList.add(" ");
        }
        // QC#55210 2020/01/09 Add Start
        String custCareTktNum = "";
        String origInvNum = "";
        String creditAndRebillMsg = "";
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("consBillNum", consBillNum);
        List<Map<String, Object>> rsList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getOriginalInvForComment", ssmParam);
        for (Map<String, Object> map : rsList) {
            creditAndRebillMsg = "";
            custCareTktNum = (String) map.get(CUST_CARE_TKT_NUM);
            origInvNum = (String) map.get(ORIG_INV_NUM);
            if (hasValue(origInvNum)) {
                if (hasValue(custCareTktNum)) {
                    creditAndRebillMsg = "Rebill for INV# " + origInvNum + " , CI# " + custCareTktNum;
                } else {
                    creditAndRebillMsg = "Rebill for INV# " + origInvNum;
                }
            }
            strList.add(creditAndRebillMsg);
        }
        
        // QC#55210 2020/01/09 Add End
        strList.add(fixedWord);
        stackStr.append(comment1).append(comment2).append(comment3).append(comment4);
        if (hasValue(stackStr.toString())) strList.add(" ");
        strList.add(stackStr.toString());
        // 2019/02/20 S21_NA#30144 Mod Start
        // if (hasValue(messageBody)) strList.add(" ");
        // strList.add(messageBody);
        if (instMsgList.size() > 0) {
            strList.add(" ");
            strList.addAll(instMsgList);
        }
        // 2019/02/20 S21_NA#30144 Mod End

        returnStr = setComment(strList);
        
        return cutOffString(returnStr, INV_CMT_CUT_OFF_LEN);
    }

    /**
     * get FooterMessage
     * @return Map<String, Object>
     */
    // 2019/06/14 QC#50844 Mod Start
    private Map<String, Object> getFooterCommentForConsl(boolean ftFlg, boolean crFlg) {

        Map<String, Object> footerMsgMap = new HashMap<String, Object>();

        if (crFlg) {
            // (*) - Invoices marked are paid by Credit Card/Advances/Credit Memo
            footerMsgMap.put(USG_INV_DESC.ALLOWANCE, this.conslCrCardFooterMsg);
        }

        if (ftFlg) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);

            List<Map<String, Object>> footerMsgMapList = getFooterComment();
            if (footerMsgMapList.size() == 0) {
                return null;
            }

            for (Map<String, Object> footerMsg : footerMsgMapList) {
                String commentCd = (String) footerMsg.get(USG_INV_DESC_CD);
                if (!crFlg) {
                    if (USG_INV_DESC.ALLOWANCE.equals(commentCd)) {
                        // (1) - Allowance may be prorated for Partial Periods
                        String fMsg = "(" + USG_INV_DESC.ALLOWANCE + ") - " + (String) footerMsg.get(USG_INV_DESC_TXT);
                        footerMsgMap.put(USG_INV_DESC.ALLOWANCE, fMsg);
                    }
                    if (USG_INV_DESC.BILLABLE_COPIES.equals(commentCd)) {
                        // (2) - Billable Copies includes Allocated Copies and Billing Multiplier if applicable.
                        String fMsg = "(" + USG_INV_DESC.BILLABLE_COPIES + ") - " + (String) footerMsg.get(USG_INV_DESC_TXT);
                        footerMsgMap.put(USG_INV_DESC.BILLABLE_COPIES, fMsg);
                    }
                    if (USG_INV_DESC_OTHER.equals(commentCd)) {
                        // Filler
                        String fMsg = "(3) - " + (String) footerMsg.get(USG_INV_DESC_TXT);
                        footerMsgMap.put(USG_INV_DESC_OTHER, fMsg);
                    }
                } else {
                    if (USG_INV_DESC.ALLOWANCE.equals(commentCd)) {
                        // (1) - Allowance may be prorated for Partial Periods
                        String fMsg = "(" + USG_INV_DESC.ALLOWANCE + ") - " + (String) footerMsg.get(USG_INV_DESC_TXT);
                        footerMsgMap.put(USG_INV_DESC.BILLABLE_COPIES, fMsg);
                    }
                    if (USG_INV_DESC.BILLABLE_COPIES.equals(commentCd)) {
                        // (2) - Billable Copies includes Allocated Copies and Billing Multiplier if applicable.
                        String fMsg = "(" + USG_INV_DESC.BILLABLE_COPIES + ") - " + (String) footerMsg.get(USG_INV_DESC_TXT);
                        footerMsgMap.put(USG_INV_DESC_OTHER, fMsg);
                    }
                }
            }
        }
        return footerMsgMap;
    }

    //private Map<String, Object> getFooterCommentForConsl(boolean crFlg) {

    //    Map<String, Object> ssmParam = new HashMap<String, Object>();
    //    ssmParam.put("glblCmpyCd", glblCmpyCd);

    //    List<Map<String, Object>> footerMsgMapList = getFooterComment();
    //    if (footerMsgMapList.size() == 0) {
    //        return null;
    //    }
    //    Map<String, Object> footerMsgMap = new HashMap<String, Object>();
    //    //QC#10850
    //    if (crFlg) {
    //        //(*) - Invoices marked are paid by Credit Card/Advances/Credit Memo
    //        footerMsgMap.put(USG_INV_DESC.ALLOWANCE, this.conslCrCardFooterMsg);
    //    }
    //    for (Map<String, Object> footerMsg : footerMsgMapList) {
    //        String commentCd = (String) footerMsg.get(USG_INV_DESC_CD);
    //        if (!crFlg) {
    //            if (USG_INV_DESC.ALLOWANCE.equals(commentCd)) {
    //                //(1) - Allowance may be prorated for Partial Periods
    //                String fMsg = "(" + USG_INV_DESC.ALLOWANCE + ") - " + (String) footerMsg.get(USG_INV_DESC_TXT);
    //                footerMsgMap.put(USG_INV_DESC.ALLOWANCE, fMsg);
    //            }
    //            if (USG_INV_DESC.BILLABLE_COPIES.equals(commentCd)) {
    //                //(2) - Billable Copies includes Allocated Copies and Billing Multiplier if applicable.
    //                String fMsg = "(" + USG_INV_DESC.BILLABLE_COPIES + ") - " + (String) footerMsg.get(USG_INV_DESC_TXT);
    //                footerMsgMap.put(USG_INV_DESC.BILLABLE_COPIES, fMsg);
    //            }
    //            if (USG_INV_DESC_OTHER.equals(commentCd)) {
    //                //Filler
    //                String fMsg = "(3) - " + (String) footerMsg.get(USG_INV_DESC_TXT);
    //                footerMsgMap.put(USG_INV_DESC_OTHER, fMsg);
    //            }
    //        } else {
    //            if (USG_INV_DESC.ALLOWANCE.equals(commentCd)) {
    //                //(1) - Allowance may be prorated for Partial Periods
    //                String fMsg = "(" + USG_INV_DESC.ALLOWANCE + ") - " + (String) footerMsg.get(USG_INV_DESC_TXT);
    //                footerMsgMap.put(USG_INV_DESC.BILLABLE_COPIES, fMsg);
    //            }
    //            if (USG_INV_DESC.BILLABLE_COPIES.equals(commentCd)) {
    //                //(2) - Billable Copies includes Allocated Copies and Billing Multiplier if applicable.
    //                String fMsg = "(" + USG_INV_DESC.BILLABLE_COPIES + ") - " + (String) footerMsg.get(USG_INV_DESC_TXT);
    //                footerMsgMap.put(USG_INV_DESC_OTHER, fMsg);
    //            }
    //        }
    //    }
    //    return footerMsgMap;
    //}
    // 2019/06/14 QC#50844 Mod End

    /**
     * @param batchReviewFlg boolean
     * @returnString
     */
    private String setBatchTypeName(boolean batchReviewFlg, String vehicle, Map<String, BigDecimal> amtMap, List<String> invList, Map<String, Object> rsMap) {

        String batchTpCd = INV_PRT_BAT_TP.NO_REVIEW;

        // QC#53015 2019/09/05 Add Start
        if (CUST_BLLG_VCLE.NEEDS_EMAIL_REVIEW.equals(vehicle)) {
            batchTpCd = INV_PRT_BAT_TP.NEEDS_EMAIL_REVIEW;
            return batchTpCd;
        }
        // QC#53015 2019/09/05 Add End

        if (batchReviewFlg) {
            batchTpCd = INV_PRT_BAT_TP.REVIEW_REQUIRED;
            return batchTpCd;
        }

        String specilaBillFlg = getSpecialBillFlg(vehicle);
        if (ZYPConstant.FLG_ON_Y.equals(specilaBillFlg)) {
            batchTpCd = INV_PRT_BAT_TP.SPECIAL_BILL;
            return batchTpCd;
        }

        // QC#53015 2019/09/05 Del Start
        //if (CUST_BLLG_VCLE.NEEDS_EMAIL_REVIEW.equals(vehicle)) {
        //    batchTpCd = INV_PRT_BAT_TP.NEEDS_EMAIL_REVIEW;
        //    return batchTpCd;
        //}
        // QC#53015 2019/09/05 Del End
        BigDecimal totalBillAmt = amtMap.get("consInvAmt") == null ? BigDecimal.ZERO : amtMap.get("consInvAmt");
        BigDecimal conslTotAmt = rsMap.get(CONSL_TOT_DEAL_NET_AMT) == null ? BigDecimal.ZERO : (BigDecimal)rsMap.get(CONSL_TOT_DEAL_NET_AMT);
        if (totalBillAmt.compareTo(conslTotAmt) != 0) {
            batchTpCd = INV_PRT_BAT_TP.DOLLAR_MISMATCH;
            return batchTpCd;
        }
        
        if (BigDecimal.ZERO.compareTo(totalBillAmt) >= 0) {
            batchTpCd = INV_PRT_BAT_TP.ZERO_DOLLAR;
            return batchTpCd;
        }

        return batchTpCd;
    }

    private String getSpecialBillFlg(String vehicle) {
        if (vehicle == null) return ZYPConstant.FLG_OFF_N;
        String key = createCacheKey(SPECIAL_BILLING_FLG, vehicle);
        String specialBillingFlg = (String) this.cache.get(key);
        if (specialBillingFlg == null) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("vehicle", vehicle);
            specialBillingFlg = (String) ssmBatchClient.queryObject("getSpecialBillFlg", ssmParam);
            if (specialBillingFlg == null) specialBillingFlg = ZYPConstant.FLG_OFF_N;
            this.cache.put(key, specialBillingFlg);
        }
        return specialBillingFlg;
    }

    private void setInvPrtCtrlCons(INV_PRT_CTRLTMsg invPrtCtrlTmsg, Map<String, Object> rs,
            Map<String, Map<String, BigDecimal>> invAmountByConsNum) throws SQLException, NWCB010001BusinessException {

        BigDecimal consBillNum = (BigDecimal) rs.get(CONS_BILL_NUM);
        String strConsBillNum = consBillNum == null ? null : String.valueOf(consBillNum);
        // QC#53015 2019/09/13 Add Start
        BigDecimal origConslBillNum = (BigDecimal) rs.get(ORIG_CONSL_BILL_PK);
        String strorigConslBillNum = origConslBillNum == null ? null : String.valueOf(origConslBillNum);
        // QC#53015 2019/09/13 Add End
        BigDecimal consInvNum = invAmountByConsNum.get(strConsBillNum).get("consInvAmt");

        // Set Common Items
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invImageTpCd, "1");
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invCratTs, this.currentSystemTs);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.conslBillNum, strConsBillNum);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.conslBillInvDt, (String) rs.get(CONS_BILL_DT));
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.conslBillTotAmt, consInvNum);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.billToDsAcctNum, (String) rs.get(CONS_BILL_TO_ACCT));
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.billToDsAcctNm, (String) rs.get(CONS_BILL_TO_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.billToCustCd, (String) rs.get(CONS_BILL_TO_CD));
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.billToLocNum, (String) rs.get(CONS_BILL_TO_LOC_NUM));
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.reprInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.splyPgmInvRvwFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invPrtCtrlRecCd, INV_PRT_CTRL_REC.CONSOLIDATED_BILL);
        // QC#53015 2019/09/13 Mod Start
        //ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.origConslBillNum, strConsBillNum);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.origConslBillNum, strorigConslBillNum);
        // QC#53015 2019/09/13 Mod End

        if (ZYPConstant.FLG_OFF_N.equals(invPrtCtrlTmsg.invOtptReqFlg.getValue())) {
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invFtpProcStsCd.getValue())) {
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        if (BigDecimal.ZERO.compareTo(consInvNum) >= 0) {
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * @param invPrtHdrTmsg INV_PRT_HDRTMsg
     * @param rs ResultSet
     * @param hederComment String
     * @param footerMsgMap Map<String, Object>
     * @param amtMap Map<String, BigDecimal>
     * @param isEasyPac1Flg boolean
     * @throws SQLException
     */
    private void setInvPrtHdrCons(INV_PRT_HDRTMsg invPrtHdrTmsg, Map<String, Object> rs, String hederComment,
            Map<String, Object> footerMsgMap, Map<String, BigDecimal> amtMap, boolean isEasyPac1Flg) throws SQLException, NWCB010001BusinessException {

        BigDecimal consInvAmt = amtMap.get("consInvAmt");
        BigDecimal consBalAmt = amtMap.get("consBalAmt");
        BigDecimal consChrgAmt = amtMap.get("consChrgAmt");
        BigDecimal consFreAmt = amtMap.get("consFreAmt");
        BigDecimal consTaxAmt = amtMap.get("consTaxAmt");
        BigDecimal consPrePayAmt = amtMap.get("consPrePayAmt");
        String ccySgnTxt = (String) rs.get(CCY_SGN_TXT);

        BigDecimal consBillNum = (BigDecimal) rs.get(CONS_BILL_NUM);
        String strConslBillNum = consBillNum == null ? null : String.valueOf(consBillNum);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invBillNum, strConslBillNum);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invDt, (String) rs.get(CONS_BILL_DT));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invDtTxt, getDtTxt((String) rs.get(CONS_BILL_DT)));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.netDueDtTxt, getDtTxt((String) rs.get(CONS_BILL_DUE_DT)));
        //ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invTotAmtTxt, getAmtTxt(consInvAmt));
        //ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invBalAmtTxt, getAmtTxt(consBalAmt));
        //ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invTotChrgAmtTxt, getAmtTxt(consChrgAmt));
        //ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invFrtAmtTxt, getAmtTxt(consFreAmt));
        //ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invFrtTaxAmtTxt, getAmtTxt(consTaxAmt));
        //ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invPrePmtAmtTxt, getAmtTxt(consPrePayAmt));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invTotAmtTxt, fmtAmtFromNumToStr(consInvAmt, ccySgnTxt));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invBalAmtTxt, fmtAmtFromNumToStr(consBalAmt, ccySgnTxt));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invTotChrgAmtTxt, fmtAmtFromNumToStr(consChrgAmt, ccySgnTxt));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invFrtAmtTxt, fmtAmtFromNumToStr(consFreAmt, ccySgnTxt));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invFrtTaxAmtTxt, fmtAmtFromNumToStr(consTaxAmt, ccySgnTxt));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invPrePmtAmtTxt, fmtAmtFromNumToStr(consPrePayAmt, ccySgnTxt));

        //ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToCustCd, (String) rs.get(CONS_BILL_TO_CD));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToCustCd, (String) rs.get(CONS_BILL_TO_ACCT));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToLocNm, (String) rs.get(CONS_BILL_TO_NM));
        
        if (hasValue((String) rs.get(CONS_BILL_TO_ATTENTION))) {
            ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToAttnTxt, (String) rs.get(CONS_BILL_TO_ATTENTION));
        } else {
            ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToAttnTxt, defBillToAttn);
        }
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToFirstLineAddr, (String) rs.get(CONS_BILL_TO_FST_ADDR));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToScdLinePrntAddr, (String) rs.get(CONS_BILL_TO_SCD_ADDR));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToCtyAddr, (String) rs.get(CONS_BILL_TO_CTY));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToStCd, (String) rs.get(CONS_BILL_TO_ST));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.billToPostCd, (String) rs.get(CONS_BILL_TO_ZIP_CD));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.remToEinCd, (String) rs.get(CONS_REMIT_TO_EIN_CD));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.remToLocNm, (String) rs.get(CONS_REMIT_TO_NM));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.remToFirstLineAddr, (String) rs.get(CONS_REMIT_TO_FST_ADDR));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.remToScdLinePrntAddr, (String) rs.get(CONS_REMIT_TO_SCD_ADDR));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.remToCtyAddr, (String) rs.get(CONS_REMIT_TO_CTY));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.remToStCd, (String) rs.get(CONS_REMIT_TO_ST));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.remToPostCd, (String) rs.get(CONS_REMIT_TO_POST));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.pmtTermCd, (String) rs.get(CONS_TERM_CD));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.pmtTermNm, (String) rs.get(CONS_TERMS));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invPrtHdrCmntTxt, hederComment);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.conslBillFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.dplyTaxDtlFlg, (String) rs.get(TAX_DETAIL_FLG));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.dplyMdseDtlFlg, (String) rs.get(DETAIL_DISPLAY_FLG));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invCmpyFirstLineAddr, cmpyFrstAddr);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invCmpyScdLinePrntAddr, cmpyScdAddr);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invCmpyCtyAddr, cmpyCtyAddr);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invCmpyStCd, cmpyState);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invCmpyPostCd, cmpyPost);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invCmpyTelNum, cmpyTel);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invCmpyUrl, cmpyUrl);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.pmtTermCashDiscCd, (String) rs.get(PMT_TERM_CASH_DISC_CD));
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.pmtTermCashDiscDescTxt, (String) rs.get(PMT_TERM_CASH_DISC_DESC_TXT));

        String footerComment1 = null;
        String footerComment2 = null;
        String footerComment3 = null;
        if (footerMsgMap.containsKey(USG_INV_DESC.ALLOWANCE)) {
            footerComment1 = (String) footerMsgMap.get(USG_INV_DESC.ALLOWANCE);
        }
        if (footerMsgMap.containsKey(USG_INV_DESC.BILLABLE_COPIES)) {
            footerComment2 = (String) footerMsgMap.get(USG_INV_DESC.BILLABLE_COPIES);
        }
        if (footerMsgMap.containsKey(USG_INV_DESC_OTHER)) {
            footerComment3 = (String) footerMsgMap.get(USG_INV_DESC_OTHER);
        }
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.firstUsgInvDescTxt, footerComment1);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.scdUsgInvDescTxt, footerComment2);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.thirdUsgInvDescTxt, footerComment3);

        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invTotAmt, consInvAmt);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invBalAmt, consBalAmt);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invTotChrgAmt, consChrgAmt);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invFrtAmt, consFreAmt);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invFrtTaxAmt, consTaxAmt);
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.invPrePmtAmt, consPrePayAmt);

        //QC#20141 add Start
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.espacFlg, createEasyPac1Flg(isEasyPac1Flg));
        //QC#20141 add End
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.lineBizTpCd, (String) rs.get(LINE_BIZ_TP_CD)); // QC#54520 2019/11/11 Add
        // START 2022/07/20 R.Onozuka [QC#59669, ADD]
        String conslBillNum = strConslBillNum;
        // START 2022/09/26 R.Onozuka [QC#60624, MOD]
//        String ocrScanNum = genOcrScanNum((String) rs.get(LINE_BIZ_TP_CD), 
//                                          (String) rs.get(CONS_BILL_TO_ACCT),
//                                          conslBillNum,
//                                          null,
//                                          consInvAmt);
        String ocrScanNum = genOcrScanNum((String) rs.get(LINE_BIZ_TP_CD), 
                                          (String) rs.get(CONS_BILL_TO_ACCT),
                                          conslBillNum,
                                          null,
                                          consInvAmt,
                                          null);
        // END 2022/09/26 R.Onozuka [QC#60624, MOD]
        ZYPEZDItemValueSetter.setValue(invPrtHdrTmsg.ocrScanNum, ocrScanNum);
        // END 2022/07/20 R.Onozuka [QC#59669, ADD]
    }

    @SuppressWarnings("unchecked")
    private boolean createPrintSummaryTable(Map<String, Object> rsMap, int headerLineCount, boolean isEasyPac1Flg)
            throws SQLException, NWCB010001BusinessException {

        String invTpCd = (String) rsMap.get(INV_TP_CD);
        BigDecimal bConslBillNum = (BigDecimal) rsMap.get(CONS_BILL_NUM);
        String conslBillNum = bConslBillNum == null ? null : String.valueOf(bConslBillNum);
        String taxDtlFlg = (String) rsMap.get(TAX_DETAIL_FLG);
        String dtlDplyFlg = (String) rsMap.get(DTL_DPLY_FLG);
        int lineCount = headerLineCount;
        List<INV_PRT_SMRYTMsg> invPrtSmryList = new ArrayList<INV_PRT_SMRYTMsg>();

        //GET Summary for Consolidation
        List<Map<String, Object>> ssmSummaryResult = null;
        ssmSummaryResult = getSummaryForConsol(bConslBillNum, taxDtlFlg, dtlDplyFlg, isEasyPac1Flg);
        
        BigDecimal frtTaxAmt = BigDecimal.ZERO;  // FRT_TAX
        BigDecimal frtStTaxAmt = BigDecimal.ZERO;  // FRT_STATE_TAX
        BigDecimal frtCntyTaxAmt = BigDecimal.ZERO;  // FRT_COUNTY_TAX
        BigDecimal frtCtyTaxAmt = BigDecimal.ZERO;  // FRT_CITY_TAX
        BigDecimal frtChrgAmt = BigDecimal.ZERO;  // FRT_CHRG_AMT
        BigDecimal frtAmt = BigDecimal.ZERO;  // FRT_AMT
        BigDecimal itemStTaxAmt = BigDecimal.ZERO;  // STATE_TAX
        BigDecimal itemCntyTaxAmt = BigDecimal.ZERO;  // COUNTY_TAX
        BigDecimal itemCtyTaxAmt = BigDecimal.ZERO;  // CITY_TAX
        BigDecimal itemAmt = BigDecimal.ZERO;  // ITEM_AMT
        BigDecimal itemTaxAmt = BigDecimal.ZERO;  // ITEM_TAX_AMT
        if (isEasyPac1Flg) {

            //get Freight Info per invoice# 
            Map<String, Object> paramEasy = new HashMap<String, Object>();
            paramEasy.put("glblCmpyCd", glblCmpyCd);
            paramEasy.put("conslBillNum", conslBillNum);
            paramEasy.put("taxDtlFlg", taxDtlFlg);
            paramEasy.put("dsSlsTaxTpCd11", DS_SLS_TAX_TP.FREIGHT_STATE_TAX);
            paramEasy.put("dsSlsTaxTpCd12", DS_SLS_TAX_TP.FREIGHT_COUNTY_TAX);
            paramEasy.put("dsSlsTaxTpCd13", DS_SLS_TAX_TP.FREIGHT_CITY_TAX);
            paramEasy.put("qtyFormat", FM_QTY);
            paramEasy.put("amtFormat", FM_AMT);
            // 2018/05/25 QC#21841 Add Start
            paramEasy.put("invLineCatgFrt", INV_LINE_CATG.FREIGHT);
            // 2018/05/25 QC#21841 Add End
            Map<String, Object> freightInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getSaleSummaryFreightInfoConsl", paramEasy);
            if (freightInfoMap != null) {
                frtTaxAmt = (BigDecimal) freightInfoMap.get("FRT_TAX") == null ? BigDecimal.ZERO : (BigDecimal) freightInfoMap.get("FRT_TAX");
                frtStTaxAmt = (BigDecimal) freightInfoMap.get("FRT_STATE_TAX") == null ? BigDecimal.ZERO : (BigDecimal) freightInfoMap.get("FRT_STATE_TAX");
                frtCntyTaxAmt = (BigDecimal) freightInfoMap.get("FRT_COUNTY_TAX") == null ? BigDecimal.ZERO : (BigDecimal) freightInfoMap.get("FRT_COUNTY_TAX");
                frtCtyTaxAmt = (BigDecimal) freightInfoMap.get("FRT_CITY_TAX") == null ? BigDecimal.ZERO : (BigDecimal) freightInfoMap.get("FRT_CITY_TAX");
                frtChrgAmt = (BigDecimal) freightInfoMap.get("FRT_CHRG_AMT") == null ? BigDecimal.ZERO : (BigDecimal) freightInfoMap.get("FRT_CHRG_AMT");
                frtAmt = (BigDecimal) freightInfoMap.get("FRT_AMT") == null ? BigDecimal.ZERO : (BigDecimal) freightInfoMap.get("FRT_AMT");
            }

            Map<String, Object> paramEasyTax = new HashMap<String, Object>();
            paramEasyTax.put("glblCmpyCd", glblCmpyCd);
            paramEasyTax.put("conslBillNum", conslBillNum);
            paramEasyTax.put("taxDtlFlg", taxDtlFlg);
            paramEasyTax.put("dsSlsTaxTpCd11", DS_SLS_TAX_TP.STATE_TAX);
            paramEasyTax.put("dsSlsTaxTpCd12", DS_SLS_TAX_TP.COUNTY_TAX);
            paramEasyTax.put("dsSlsTaxTpCd13", DS_SLS_TAX_TP.CITY_TAX);
            paramEasyTax.put("qtyFormat", FM_QTY);
            paramEasyTax.put("amtFormat", FM_AMT);
            // QC#59356
            paramEasyTax.put("invLineCatgCd", INV_LINE_CATG.FREIGHT);
            Map<String, Object> itemTaxInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getSaleSummaryTaxConsl", paramEasyTax);
            // 2018/09/04 QC#28051 Mod Start
            //if (freightInfoMap != null) {
            if (itemTaxInfoMap != null) {
            // 2018/09/04 QC#28051 Mod End
                itemStTaxAmt = (BigDecimal) itemTaxInfoMap.get("STATE_TAX") == null ? BigDecimal.ZERO : (BigDecimal) itemTaxInfoMap.get("STATE_TAX");
                itemCntyTaxAmt = (BigDecimal) itemTaxInfoMap.get("COUNTY_TAX") == null ? BigDecimal.ZERO : (BigDecimal) itemTaxInfoMap.get("COUNTY_TAX");
                itemCtyTaxAmt = (BigDecimal) itemTaxInfoMap.get("CITY_TAX") == null ? BigDecimal.ZERO : (BigDecimal) itemTaxInfoMap.get("CITY_TAX");
                itemAmt = (BigDecimal) itemTaxInfoMap.get("ITEM_AMT") == null ? BigDecimal.ZERO : (BigDecimal) itemTaxInfoMap.get("ITEM_AMT");
                itemTaxAmt = (BigDecimal) itemTaxInfoMap.get("ITEM_TAX_AMT") == null ? BigDecimal.ZERO : (BigDecimal) itemTaxInfoMap.get("ITEM_TAX_AMT");
            }
        }
        if (ssmSummaryResult.size() > 0) {
            //lineCount++;
            // QC#20141 mod Start
            //lineCount = setSmryForConsl(invPrtSmryList, ssmSummaryResult, rsMap, lineCount);
            lineCount = setSmryForConsl(invPrtSmryList, ssmSummaryResult, rsMap, lineCount, isEasyPac1Flg, itemAmt);
            // QC#20141 mod End
        }
        
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invBillNum", conslBillNum);
        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getConslInvPrtHdr", ssmParam);
        if (map != null) {

            if (isEasyPac1Flg) {

                String ccySgnTxt = (String) rsMap.get(CCY_SGN_TXT);
                //fmtAmtFromNumToStr
                //################################################################
                //Freight
                lineCount++;
                // QC#57344
                INV_PRT_SMRYTMsg invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, null, null, lineCount, isEasyPac1Flg, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryFrtAmtTxt,  fmtAmtFromNumToStr(frtAmt, ccySgnTxt));
                invPrtSmryList.add(invPrtSmryTMsg);
                
                //Tax (Freight Tax)
                lineCount++;
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, null, null, lineCount, isEasyPac1Flg, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryAmtFrtTaxTxt,     fmtAmtFromNumToStr(frtTaxAmt, ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryStAmtTaxTxt,   fmtAmtFromNumToStr(frtStTaxAmt, ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCntyAmtTaxTxt, fmtAmtFromNumToStr(frtCntyTaxAmt, ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCtyAmtTaxTxt, fmtAmtFromNumToStr(frtCtyTaxAmt, ccySgnTxt));
                invPrtSmryList.add(invPrtSmryTMsg);
                
                //SubTotals
                lineCount++;
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, null, null, lineCount, isEasyPac1Flg, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryChrgAmtFrtTxt,  fmtAmtFromNumToStr(frtChrgAmt, ccySgnTxt));
                invPrtSmryList.add(invPrtSmryTMsg);
        
                //################################################################
                //Total Items with Freight 
                lineCount++;
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, null, null, lineCount, isEasyPac1Flg, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotAmtTxt,  fmtAmtFromNumToStr(frtAmt.add(itemAmt), ccySgnTxt));
                invPrtSmryList.add(invPrtSmryTMsg);
                
                //Total Tax
                lineCount++;
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, null, null, lineCount, isEasyPac1Flg, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryAmtFrtTaxTxt,  fmtAmtFromNumToStr(frtTaxAmt.add(itemTaxAmt), ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryStAmtTaxTxt,   fmtAmtFromNumToStr(frtStTaxAmt.add(itemStTaxAmt), ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCntyAmtTaxTxt, fmtAmtFromNumToStr(frtCntyTaxAmt.add(itemCntyTaxAmt), ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCtyAmtTaxTxt, fmtAmtFromNumToStr(frtCtyTaxAmt.add(itemCtyTaxAmt), ccySgnTxt));
                invPrtSmryList.add(invPrtSmryTMsg);
                
                //INV_SMRY_BAL_AMT_TXT
                lineCount++;
                // 2019/08/20 QC#51811 Mod Start
                //String invSmryBalAmtTxt = setNegate((String) map.get(INV_BAL_AMT_TXT), invTpCd);
                String invSmryBalAmtTxt = (String) map.get(INV_BAL_AMT_TXT);
                // 2019/08/20 QC#51811 Mod End
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, null, null, lineCount, false, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryBalAmtTxt, invSmryBalAmtTxt);
                invPrtSmryList.add(invPrtSmryTMsg);

            } else {
                //INV_SMRY_TOT_CHRG_AMT_TXT
                lineCount++;
                // 2019/08/20 QC#51811 Mod Start
                //String invSmryTotChrgAmtTxt = setNegate((String) map.get(INV_TOT_CHRG_AMT_TXT), invTpCd);
                String invSmryTotChrgAmtTxt = (String) map.get(INV_TOT_CHRG_AMT_TXT);
                // 2019/08/20 QC#51811 Mod End
                // QC#57344
                INV_PRT_SMRYTMsg invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, null, null, lineCount, false, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotChrgAmtTxt, invSmryTotChrgAmtTxt);
                invPrtSmryList.add(invPrtSmryTMsg);
            
                //INV_SMRY_FRT_AMT_TXT
                lineCount++;
                // 2019/08/20 QC#51811 Mod Start
                //String invSmryFrtAmtTxt = setNegate((String) map.get(INV_FRT_AMT_TXT), invTpCd);
                String invSmryFrtAmtTxt = (String) map.get(INV_FRT_AMT_TXT);
                // 2019/08/20 QC#51811 Mod End
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, null, null, lineCount, false, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryFrtAmtTxt, invSmryFrtAmtTxt);
                invPrtSmryList.add(invPrtSmryTMsg);
            
                //INV_SMRY_FRT_TAX_AMT_TXT(total tax)
                lineCount++;
                // 2019/08/20 QC#51811 Mod Start
                //String invSmryFrtTaxAmtTxt = setNegate((String) map.get(INV_FRT_TAX_AMT_TXT), invTpCd);
                String invSmryFrtTaxAmtTxt = (String) map.get(INV_FRT_TAX_AMT_TXT);
                // 2019/08/20 QC#51811 Mod End
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, null, null, lineCount, false, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryFrtTaxAmtTxt, invSmryFrtTaxAmtTxt);
                invPrtSmryList.add(invPrtSmryTMsg);
            
                //INV_SMRY_TOT_AMT_TXT
                lineCount++;
                // 2019/08/20 QC#51811 Mod Start
                //String invSmryTotAmtTxt = setNegate((String) map.get(INV_TOT_AMT_TXT), invTpCd);
                String invSmryTotAmtTxt = (String) map.get(INV_TOT_AMT_TXT);
                // 2019/08/20 QC#51811 Mod End
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, null, null, lineCount, false, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotAmtTxt, invSmryTotAmtTxt);
                invPrtSmryList.add(invPrtSmryTMsg);
            
                //INV_SMRY_PRE_PMT_AMT_TXT
                //lineCount++;
                //String invSmryPrePmtAmtTxt = setNegate((String) map.get("INV_PRE_PMT_AMT_TXT"), invTpCd);
                //invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, null, null, lineCount);
                //ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryPrePmtAmtTxt, invSmryPrePmtAmtTxt);
                //invPrtSmryList.add(invPrtSmryTMsg);
            
                //INV_SMRY_BAL_AMT_TXT
                lineCount++;
                // 2019/08/20 QC#51811 Mod Start
                //String invSmryBalAmtTxt = setNegate((String) map.get(INV_BAL_AMT_TXT), invTpCd);
                String invSmryBalAmtTxt = (String) map.get(INV_BAL_AMT_TXT);
                // 2019/08/20 QC#51811 Mod End
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, null, null, lineCount, false, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryBalAmtTxt, invSmryBalAmtTxt);
                invPrtSmryList.add(invPrtSmryTMsg);
            }

        }
        
        int result = EZDFastTBLAccessor.insert(invPrtSmryList.toArray(new INV_PRT_SMRYTMsg[invPrtSmryList.size()]));
        if (result != invPrtSmryList.size()) {
            //NWCM0109E=0,It failed to register @ Table.[@]
            S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_SMRYTMsg().getTableName(), conslBillNum));
            return false;
        }
//        normCnt += result;
        return true;
    }

    /**
     * @param consBillNum String
     * @return boolean
     */
    private boolean setConslSts02(String consBillNum) {
        if (ZYPCommonFunc.hasValue(consBillNum)) {
            CONSL_BILLTMsg conslBillTmsg = new CONSL_BILLTMsg();
            conslBillTmsg.glblCmpyCd.setValue(glblCmpyCd);
            conslBillTmsg.conslBillPk.setValue(new BigDecimal(consBillNum));

            conslBillTmsg = (CONSL_BILLTMsg) EZDFastTBLAccessor.findByKeyForUpdate(conslBillTmsg);

            conslBillTmsg.conslPrintStsCd.setValue(CONSL_PRINT_STS.PRINTED);

            EZDTBLAccessor.update(conslBillTmsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(conslBillTmsg.getReturnCode())) {
                //NWCM0110E=0,It failed to update @ Table.[@]
                S21InfoLogOutput.println(NWCM0110E, toArray(conslBillTmsg.getTableName(), consBillNum));
                return false;
            }
        }
        return true;
    }

    /**
     * @param invNum String
     * @param consBillNum String
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean updateInv(BigDecimal conslBillPk) {

        String key = createCacheKey(CONSL_LINE, conslBillPk);
        List<String> invList = (List<String>) cache.get(key);
        if (invList == null) {
            HashMap<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("conslBillPk", conslBillPk);
            invList = (List<String>) ssmBatchClient.queryObjectList("getConslLine", ssmParam);
            cache.put(key, invList);
        }

        if (invList != null && !invList.isEmpty()) {
            for (String invNum : invList) {

                INVTMsg invTmsg = new INVTMsg();
                invTmsg.glblCmpyCd.setValue(glblCmpyCd);
                invTmsg.invNum.setValue(invNum);

                invTmsg = (INVTMsg) EZDFastTBLAccessor.findByKeyForUpdate(invTmsg);
                if (invTmsg == null) {
                    return false;
                }
                invTmsg.grpInvNum.setValue(String.valueOf(conslBillPk));
                EZDTBLAccessor.update(invTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invTmsg.getReturnCode())) {
                    //NWCM0110E=0,It failed to update @ Table.[@]
                    S21InfoLogOutput.println(NWCM0110E, toArray(invTmsg.getTableName(), invTmsg.invNum.getValue()));
                    return false;
                }
            }
        }
        
        return true;
    }

    /**
     * @param invNum String
     * @param consBillNum String
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean updateArTrxBal(BigDecimal conslBillPk) {

        String key = createCacheKey(CONSL_LINE, conslBillPk);
        List<String> invList = (List<String>) cache.get(key);

        if (invList != null && !invList.isEmpty()) {
            for (String invNum : invList) {

                HashMap<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("invNum", invNum);
                BigDecimal arTrxBalPk = (BigDecimal) ssmBatchClient.queryObject("getArTrxBalPk", ssmParam);
                if (arTrxBalPk == null) {
                    continue;
                }

                AR_TRX_BALTMsg arTrxBalTmsg = new AR_TRX_BALTMsg();
                arTrxBalTmsg.glblCmpyCd.setValue(glblCmpyCd);
                arTrxBalTmsg.arTrxBalPk.setValue(arTrxBalPk);
                arTrxBalTmsg = (AR_TRX_BALTMsg) EZDFastTBLAccessor.findByKeyForUpdate(arTrxBalTmsg);

              if (arTrxBalTmsg != null) {
                    arTrxBalTmsg.grpInvNum.setValue(String.valueOf(conslBillPk));
                    EZDTBLAccessor.update(arTrxBalTmsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(arTrxBalTmsg.getReturnCode())) {
                        //NWCM0110E=0,It failed to update @ Table.[@]
                        S21InfoLogOutput.println(NWCM0110E, toArray(arTrxBalTmsg.getTableName(), arTrxBalTmsg.arTrxNum.getValue()));
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @param consBillNum String
     * @return boolean
     */
    private boolean updateGroupKey(String consBillNum) {

        // get Fleet Data
        List<Map<String, Object>> resultList = getFleetPkForUpdate(consBillNum);

        boolean result = false;
        BigDecimal billFleetSubTotSq = BigDecimal.ZERO;

        // if same group -> same sequence
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = resultList.get(i);

            BigDecimal fleetSubTotPk = (BigDecimal) resultMap.get(INV_PRT_FLEET_SUB_TOT_PK);

            if (i != 0) {
                Map<String, Object> preMap = resultList.get(i - 1);
                if (!isEqual(resultMap, preMap, INV_TP_CD) || !isEqual(resultMap, preMap, DS_CONTR_PK)
                        || !isEqual(resultMap, preMap, SVC_PGM_NM) || !isEqual(resultMap, preMap, CUST_ISS_PO_NUM)) {
                    billFleetSubTotSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.BILL_FEET_SUB_TOT_SQ);
                }
            } else {
                billFleetSubTotSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.BILL_FEET_SUB_TOT_SQ);
            }

            // update INV_PRT_FLEET_SUB_TOT
            result = updateFleetSubTot(fleetSubTotPk, billFleetSubTotSq);
            if (!result) {
                return false;
            }
            // 02/06/2019 QC#29371 Del Start
//            // update INV_PRT_FLEET_CHRG_DTL 
//            result = updateFleetChrgDtl(fleetSubTotPk, billFleetSubTotSq);
//            if (!result) {
//                return false;
//            }
            // 02/06/2019 QC#29371 Del End
            // update INV_PRT_FLEET_LOC
            result = updateFleetLoc(fleetSubTotPk, billFleetSubTotSq);
            if (!result) {
                return false;
            }
            // update INV_PRT_FLEET_LOC
            result = updateFleetSmryMtr(fleetSubTotPk, billFleetSubTotSq);
            if (!result) {
                return false;
            }
        }

        // get Maint Data
        resultList = getMaintPkForUpdate(consBillNum);

        BigDecimal billMaintSubTotSq = BigDecimal.ZERO;

        // if same group -> same sequence
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = resultList.get(i);

            BigDecimal maintSubTotPk = (BigDecimal) resultMap.get(INV_PRT_MAINT_SUB_TOT_PK);

            if (i != 0) {
                Map<String, Object> preMap = resultList.get(i - 1);
                if (!isEqual(resultMap, preMap, INV_TP_CD) || !isEqual(resultMap, preMap, SHIP_TO_CUST_CD)
                        || !isEqual(resultMap, preMap, CUST_ISS_PO_NUM)
                        || !isEqual(resultMap, preMap, FIRST_BLLG_ATTRB_VAL_TXT)
                        || !isEqual(resultMap, preMap, SCD_BLLG_ATTRB_VAL_TXT)
                        || !isEqual(resultMap, preMap, THIRD_BLLG_ATTRB_VAL_TXT)
                        || !isEqual(resultMap, preMap, FRTH_BLLG_ATTRB_VAL_TXT)
                        || !isEqual(resultMap, preMap, FIFTH_BLLG_ATTRB_VAL_TXT)
                        || !isEqual(resultMap, preMap, SIXTH_BLLG_ATTRB_VAL_TXT)) {

                    billMaintSubTotSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.BILL_MAINT_SUB_TOT_SQ);
                }
            } else {
                billMaintSubTotSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.BILL_MAINT_SUB_TOT_SQ);
            }

            // update INV_PRT_MAINT_SUB_TOT
            result = updateMaintSubTot(maintSubTotPk, billMaintSubTotSq);
            if (!result) {
                return false;
            }

            // update INV_PRT_MAINT_MACH
            result = updateMaintMach(maintSubTotPk, billMaintSubTotSq);
            if (!result) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getFleetPkForUpdate(String consBillNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("consBillNum", consBillNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFleetPkForUpdate", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getMaintPkForUpdate(String consBillNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("consBillNum", consBillNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMaintPkForUpdate", ssmParam);
    }

    /**
     * @param fleetSubTotPk BigDecimal
     * @param sqNum BigDecimal
     * @return boolean
     */
    private boolean updateFleetSubTot(BigDecimal fleetSubTotPk, BigDecimal sqNum) {

        INV_PRT_FLEET_SUB_TOTTMsg fleetSubTotTmsg = new INV_PRT_FLEET_SUB_TOTTMsg();
        fleetSubTotTmsg.glblCmpyCd.setValue(glblCmpyCd);
        fleetSubTotTmsg.invPrtFleetSubTotPk.setValue(fleetSubTotPk);

        fleetSubTotTmsg = (INV_PRT_FLEET_SUB_TOTTMsg) EZDFastTBLAccessor.findByKeyForUpdate(fleetSubTotTmsg);
        if (fleetSubTotTmsg == null) {
            return true;
        }
        fleetSubTotTmsg.billFeetSubTotSq.setValue(sqNum);
        EZDTBLAccessor.update(fleetSubTotTmsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(fleetSubTotTmsg.getReturnCode())) {
            //NWCM0110E=0,It failed to update @ Table.[@]
            S21InfoLogOutput.println(NWCM0110E, toArray(fleetSubTotTmsg.getTableName(), String.valueOf(fleetSubTotPk)));
            return false;
        }
        return true;

    }

    /**
     * @param fleetSubTotPk BigDecimal
     * @param sqNum BigDecimal
     * @return boolean
     */
    private boolean updateFleetChrgDtl(BigDecimal fleetSubTotPk, BigDecimal sqNum) {

        INV_PRT_FLEET_CHRG_DTLTMsg fleetChrgDtlTmsg = new INV_PRT_FLEET_CHRG_DTLTMsg();
        fleetChrgDtlTmsg.setSQLID("002");
        fleetChrgDtlTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        fleetChrgDtlTmsg.setConditionValue("invPrtFleetSubTotPk01", fleetSubTotPk);

        INV_PRT_FLEET_CHRG_DTLTMsgArray fleetChrgDtlTmsgArray = (INV_PRT_FLEET_CHRG_DTLTMsgArray) EZDTBLAccessor
                .findByConditionForUpdate(fleetChrgDtlTmsg);

        //QC#6296 add Start
        if (fleetChrgDtlTmsgArray.length() == 0) {
            return true;
        }
        //QC#6296 add End

        INV_PRT_FLEET_CHRG_DTLTMsg[] fleetChrgDtlTmsgList = new INV_PRT_FLEET_CHRG_DTLTMsg[fleetChrgDtlTmsgArray.length()];

        for (int i = 0; i < fleetChrgDtlTmsgArray.length(); i++) {
            INV_PRT_FLEET_CHRG_DTLTMsg tmsg = fleetChrgDtlTmsgArray.no(i);
            tmsg.billFeetSubTotSq.setValue(sqNum);
            fleetChrgDtlTmsgList[i] = tmsg;
        }

        int result = EZDFastTBLAccessor.update(fleetChrgDtlTmsgList);

        if (result != fleetChrgDtlTmsgList.length) {
            //NWCM0110E=0,It failed to update @ Table.[@]
            S21InfoLogOutput.println(NWCM0110E, toArray(fleetChrgDtlTmsg.getTableName(), String.valueOf(fleetSubTotPk)));
            return false;
        }
        return true;
    }

    /**
     * @param fleetSubTotPk BigDecimal
     * @param sqNum BigDecimal
     * @return boolean
     */
    private boolean updateFleetLoc(BigDecimal fleetSubTotPk, BigDecimal sqNum) {

        INV_PRT_FLEET_LOCTMsg fleetLocTmsg = new INV_PRT_FLEET_LOCTMsg();
        fleetLocTmsg.setSQLID("001");
        fleetLocTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        fleetLocTmsg.setConditionValue("invPrtFleetSubTotPk01", fleetSubTotPk);

        INV_PRT_FLEET_LOCTMsgArray fleetLocTmsgArray = (INV_PRT_FLEET_LOCTMsgArray) EZDTBLAccessor
                .findByConditionForUpdate(fleetLocTmsg);

        if (fleetLocTmsgArray.length() == 0) {
            return true;
        }

        INV_PRT_FLEET_LOCTMsg[] fleetLocTmsgList = new INV_PRT_FLEET_LOCTMsg[fleetLocTmsgArray.length()];

        for (int i = 0; i < fleetLocTmsgArray.length(); i++) {
            INV_PRT_FLEET_LOCTMsg tmsg = fleetLocTmsgArray.no(i);
            tmsg.billFeetSubTotSq.setValue(sqNum);
            fleetLocTmsgList[i] = tmsg;
        }

        int result = EZDFastTBLAccessor.update(fleetLocTmsgList);

        if (result != fleetLocTmsgList.length) {
            //NWCM0110E=0,It failed to update @ Table.[@]
            S21InfoLogOutput.println(NWCM0110E, toArray(fleetLocTmsg.getTableName(), String.valueOf(fleetSubTotPk)));
            return false;
        }
        return true;
    }

    /**
     * @param fleetSubTotPk BigDecimal
     * @param sqNum BigDecimal
     * @return boolean
     */
    private boolean updateFleetSmryMtr(BigDecimal fleetSubTotPk, BigDecimal sqNum) {

        INV_PRT_FLEET_SMRY_MTRTMsg fleetSmryMtrTmsg = new INV_PRT_FLEET_SMRY_MTRTMsg();
        fleetSmryMtrTmsg.setSQLID("002");
        fleetSmryMtrTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        fleetSmryMtrTmsg.setConditionValue("invPrtFleetSubTotPk01", fleetSubTotPk);

        INV_PRT_FLEET_SMRY_MTRTMsgArray fleetSmryMtrTmsgArray = (INV_PRT_FLEET_SMRY_MTRTMsgArray) EZDTBLAccessor
                .findByConditionForUpdate(fleetSmryMtrTmsg);

        if (fleetSmryMtrTmsgArray.length() == 0) {
            return true;
        }

        INV_PRT_FLEET_SMRY_MTRTMsg[] fleetSmryMtrTmsgList = new INV_PRT_FLEET_SMRY_MTRTMsg[fleetSmryMtrTmsgArray.length()];

        for (int i = 0; i < fleetSmryMtrTmsgArray.length(); i++) {
            INV_PRT_FLEET_SMRY_MTRTMsg tmsg = fleetSmryMtrTmsgArray.no(i);
            tmsg.billFeetSubTotSq.setValue(sqNum);
            fleetSmryMtrTmsgList[i] = tmsg;
        }

        int result = EZDFastTBLAccessor.update(fleetSmryMtrTmsgList);

        if (result != fleetSmryMtrTmsgList.length) {
            //NWCM0110E=0,It failed to update @ Table.[@]
            S21InfoLogOutput.println(NWCM0110E, toArray(fleetSmryMtrTmsg.getTableName(), String.valueOf(fleetSubTotPk)));
            return false;
        }
        return true;
    }

    // QC#29371
    private boolean setAgg(List<Map<String, Object>> resultMapList, ResultSet rs, Map<String, Object> preConslBillInfoForAggMap) throws SQLException, NWCB010001BusinessException {

        boolean resultFlg = true;
        String invNum = rs.getString(INV_NUM);
        String invTpCd = rs.getString(INV_TP_CD);

        INV_PRT_FLEET_SUB_TOTTMsg invPrtFleetSubTotTmsg;
        List<INV_PRT_FLEET_SUB_TOTTMsg> invPrtFleetSubTotList = new ArrayList<INV_PRT_FLEET_SUB_TOTTMsg>();

        for (Map<String, Object> resultMap : resultMapList) {

            invPrtFleetSubTotTmsg = new INV_PRT_FLEET_SUB_TOTTMsg();
            String svcPgm = null;

            StringBuilder sbSvcPgm = new StringBuilder();
            sbSvcPgm.append(nullConvEmp((String) resultMap.get(SVC_PGM_NM))).append(" - ").append(DS_CONTR_CATG.AGGREGATE).append("_").append(
                    resultMap.get(CONTR_NO));
            BigDecimal baseCount = (BigDecimal) resultMap.get(BASE_CHARGE_COUNT);
            if (ZYPCommonFunc.hasValue(baseCount) && BigDecimal.ZERO.compareTo(baseCount) != 0 
                && resultMap.get(BASE_COPY_QTY) != null && !"0".equals((String) resultMap.get(BASE_COPY_QTY))) {
                sbSvcPgm.append(ENTER).append(SVC_MESSAGE1).append((String) resultMap.get(BASE_COPY_QTY)).append(SVC_MESSAGE3);
            }
            svcPgm = sbSvcPgm.toString();

            BigDecimal stateTax = setNegate((BigDecimal) resultMap.get(STATE_TAX), invTpCd);
            BigDecimal countyTax = setNegate((BigDecimal) resultMap.get(COUNTY_TAX), invTpCd);
            BigDecimal cityTax = setNegate((BigDecimal) resultMap.get(CITY_TAX), invTpCd);
            BigDecimal totalTax = setNegate((BigDecimal) resultMap.get(TOTAL_TAX), invTpCd);
            BigDecimal totalAmt = setNegate((BigDecimal) resultMap.get(TOTAL_AMT), invTpCd);
            String stateTaxTxt = setNegate((String) resultMap.get(STATE_TAX_TXT), invTpCd);
            String countyTaxTxt = setNegate((String) resultMap.get(COUNTY_TAX_TXT), invTpCd);
            String cityTaxTxt = setNegate((String) resultMap.get(CITY_TAX_TXT), invTpCd);
            String totalTaxTxt = setNegate((String) resultMap.get(TOTAL_TAX_TXT), invTpCd);
            String totalAmtTxt = setNegate((String) resultMap.get(TOTAL_AMT_TXT), invTpCd);

            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invPrtFleetSubTotPk, ZYPOracleSeqAccessor
                    .getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_FLEET_SUB_TOT_SQ));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invNum, invNum);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invPrtDtlFmtTpCd, INV_PRT_DTL_FMT_TP.AGGREGATE_CONTRACT);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.dsContrPk, (BigDecimal) resultMap.get(CONTR_PK));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.dsContrNum, (String) resultMap.get(CONTR_NO));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.custIssPoNum, (String) resultMap.get(PO_NO));
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.svcPgmNm, (String) resultMap.get(SVC_PGM_CD));//Temp Search Key

            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invStTaxAmtTxt, stateTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invCntyTaxAmtTxt, countyTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invCityTaxAmtTxt, cityTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invTotTaxAmtTxt, totalTaxTxt);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invSubTotAmtTxt, totalAmtTxt);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invStTaxAmt, stateTax);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invCntyTaxAmt, countyTax);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invCityTaxAmt, cityTax);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invTotTaxAmt, totalTax);
            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.invSubTotAmt, totalAmt);

            if (preConslBillInfoForAggMap != null && !preConslBillInfoForAggMap.isEmpty()) {
                //Store Previous Consolidated Bill
                StringBuffer key = new StringBuffer(INV_PRT_FLEET_SUB_TOT);
                if ((BigDecimal) resultMap.get(CONTR_PK) != null) {
                    key.append(((BigDecimal) resultMap.get(CONTR_PK)).toString());
                }
                if (hasValue(svcPgm)) {
                    key.append(svcPgm);
                }
                if (hasValue((String) resultMap.get(PO_NO))) {
                    key.append((String) resultMap.get(PO_NO));
                }
                if (preConslBillInfoForAggMap.get(CONSL_BILL_NUM) != null) {
                    ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.conslBillNum, (String) preConslBillInfoForAggMap.get(CONSL_BILL_NUM));
                }
                if (preConslBillInfoForAggMap.get(key.toString()) != null) {
                    ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.billFeetSubTotSq, (BigDecimal) preConslBillInfoForAggMap.get(key.toString()));
                }
            }
            
            // set FLEET CHARGE DETAIL INFO
            // QC#50104 2019/05/13 Del
            //resultFlg = setFleetChrgDtl(invPrtFleetSubTotTmsg, invTpCd, ZYPConstant.FLG_OFF_N); // QC#29371
            //if (!resultFlg) {
            //    return false;
            //}
            resultFlg = setFleetChrgDtlForAgg(invPrtFleetSubTotTmsg, invTpCd, ZYPConstant.FLG_OFF_N); // QC#29371
            if (!resultFlg) {
                return false;
            }
            String meterFlag = rs.getString(AR_METER_FLG);

            // set FLEET METER CHARGE INFO
            resultFlg = setFleetMtrChrg(invPrtFleetSubTotTmsg, meterFlag, ZYPConstant.FLG_OFF_N); // QC#29371
            if (!resultFlg) {
                return false;
            }
            // set FLEET BASE CHARGE
            resultFlg = setFleetBaseChrg(invPrtFleetSubTotTmsg, ZYPConstant.FLG_OFF_N); // QC#29371
            if (!resultFlg) {
                return false;
            }
            // set FLEET Summary Meter
            resultFlg = setFleetSmry(invPrtFleetSubTotTmsg, invTpCd, meterFlag, ZYPConstant.FLG_OFF_N); // QC#29371);
            if (!resultFlg) {
                return false;
            }

            ZYPEZDItemValueSetter.setValue(invPrtFleetSubTotTmsg.svcPgmNm, (String) resultMap.get("INV_PRINT_COV_TXT"));
            invPrtFleetSubTotList.add(invPrtFleetSubTotTmsg);

        }
        int result = EZDFastTBLAccessor.insert(invPrtFleetSubTotList.toArray(new INV_PRT_FLEET_SUB_TOTTMsg[invPrtFleetSubTotList
                .size()]));
        if (result != invPrtFleetSubTotList.size()) {
            S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_FLEET_SUB_TOTTMsg().getTableName(), invNum));
            return false;
        }
        return true;
    }

    // QC#29371
    private boolean setAggInfo(ResultSet rs, Map<String, Object> preConslBillInfoForAggMap) throws SQLException, NWCB010001BusinessException {
        String invNum = rs.getString(INV_NUM);
        String taxDtlFlg = rs.getString(TAX_DETAIL_FLG);

        // QC#52209 2019/07/31 Del Start
        // QC#30346 2019/02/18 Add Start
        //List<Map<String, Object>> ssmResult = getFleetInvLine(invNum, ZYPConstant.FLG_ON_Y); // QC#29371
        //boolean resultFlg = insertFleetInvLine(invNum, ssmResult, ZYPConstant.FLG_ON_Y); // QC#29371
        //if (!resultFlg) {
        //    return false;
        //}
        // QC#30346 2019/02/18 Add End
        // QC#52209 2019/07/31 Del End

        // GET Aggregate SubTotal
        List<Map<String, Object>> ssmAggResult = getFleetSubTotal(invNum, taxDtlFlg, ZYPConstant.FLG_OFF_N); // QC#29371
        if (ssmAggResult.size() == 0) {
            //NWCM0112E=It failed to get [@].(@)
            S21InfoLogOutput.println(NWCM0112E, toArray("AggregateSubTotal", invNum));
            return false;
        }
        // INSERT AGGREGATE
        boolean result = setAgg(ssmAggResult, rs, preConslBillInfoForAggMap);
        if (!result) {
            return false;
        }
        return true;
    }

    /**
     * @param maintSubTotPk BigDecimal
     * @param sqNum BigDecimal
     * @return boolean
     */
    private boolean updateMaintSubTot(BigDecimal maintSubTotPk, BigDecimal sqNum) {

        INV_PRT_MAINT_SUB_TOTTMsg maintSubTotTmsg = new INV_PRT_MAINT_SUB_TOTTMsg();
        maintSubTotTmsg.glblCmpyCd.setValue(glblCmpyCd);
        maintSubTotTmsg.invPrtMaintSubTotPk.setValue(maintSubTotPk);

        maintSubTotTmsg = (INV_PRT_MAINT_SUB_TOTTMsg) EZDFastTBLAccessor.findByKeyForUpdate(maintSubTotTmsg);
        if (maintSubTotTmsg == null) {
            return true;
        }
        maintSubTotTmsg.billMaintSubTotSq.setValue(sqNum);
        EZDTBLAccessor.update(maintSubTotTmsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(maintSubTotTmsg.getReturnCode())) {
            //NWCM0110E=0,It failed to update @ Table.[@]
            S21InfoLogOutput.println(NWCM0110E, toArray(maintSubTotTmsg.getTableName(), String.valueOf(maintSubTotPk)));
            return false;
        }
        return true;
    }

    /**
     * @param maintSubTotPk BigDecimal
     * @param sqNum BigDecimal
     * @return boolean
     */
    private boolean updateMaintMach(BigDecimal maintSubTotPk, BigDecimal sqNum) {

        INV_PRT_MAINT_MACHTMsg maintMachTmsg = new INV_PRT_MAINT_MACHTMsg();
        maintMachTmsg.setSQLID("002");
        maintMachTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        maintMachTmsg.setConditionValue("invPrtMaintSubTotPk01", maintSubTotPk);

        INV_PRT_MAINT_MACHTMsgArray maintMachTmsgArray = (INV_PRT_MAINT_MACHTMsgArray) EZDTBLAccessor
                .findByConditionForUpdate(maintMachTmsg);

        if (maintMachTmsgArray.length() == 0) {
            return true;
        }

        INV_PRT_MAINT_MACHTMsg[] maintMachTmsgList = new INV_PRT_MAINT_MACHTMsg[maintMachTmsgArray.length()];

        for (int i = 0; i < maintMachTmsgArray.length(); i++) {
            INV_PRT_MAINT_MACHTMsg tmsg = maintMachTmsgArray.no(i);
            tmsg.billMaintSubTotSq.setValue(sqNum);
            maintMachTmsgList[i] = tmsg;
        }

        int result = EZDFastTBLAccessor.update(maintMachTmsgList);

        if (result != maintMachTmsgList.length) {
            //NWCM0110E=0,It failed to update @ Table.[@]
            S21InfoLogOutput.println(NWCM0110E, toArray(maintMachTmsg.getTableName(), String.valueOf(maintSubTotPk)));
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSerialNoList(String invNum) {
        if (!hasValue(invNum)) {
            return null;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSerialNoList", ssmParam);
    }

    //QC#18371
    private List<Map<String, Object>> getDispSerialNoList(Map<String, Object> resultMap, List<Map<String, Object>> serialNoList) {
        List<Map<String, Object>> dispSerialNoList = new ArrayList<Map<String, Object>>();
        if (serialNoList == null || serialNoList.size() == 0) {
            return dispSerialNoList;
        }
        //Back Order is no serial
        if (!hasValue((String) resultMap.get(INV_NUM))) {
            return dispSerialNoList;
        }
        StringBuffer sb = new StringBuffer();
        sb.append((String) resultMap.get(INV_NUM));
        sb.append((String) resultMap.get(INV_BOL_LINE_NUM));
        sb.append((String) resultMap.get(INV_LINE_NUM));
        
        for (Map<String, Object> map : serialNoList) {
            String invLineKey = (String) map.get("INV_LINE_KEY");
            if (invLineKey.equals(sb.toString())) {
                dispSerialNoList.add(map);
            }
        }
        return dispSerialNoList;
    }

    /**
     * @param str1 String
     * @param str2 String
     * @return String
     */
    private String notValue(String str1, String str2) {
        if (str1 == null) {
            return str2;
        }
        return str1;
    }

    private String setNegativeCurrencyFormat(String strAmt) {
        String ret = null;
        if (!hasValue(strAmt)) {
            return ret;
        }
        ret = "(" + strAmt + ")";
        return ret;
    }
    
    private String fmtAmtFromNumToStr(BigDecimal amt, String ccySgnTxt) {
        if (!hasValue(amt)) return null;
        if (ccySgnTxt == null) ccySgnTxt = "";
        amt = amt.setScale(2, BigDecimal.ROUND_HALF_UP);
        DecimalFormat df = new DecimalFormat(ccySgnTxt + "###,###,###,###,##0.00");
        //NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
        String strAmt = "";
        if (BigDecimal.ZERO.compareTo(amt) > 0) {
            strAmt = df.format(amt.abs());
            strAmt = setNegativeCurrencyFormat(strAmt);
        } else {
            strAmt = df.format(amt);
        }
        return strAmt;
    }
    private String fmtAmtFromNumToStrInvTp(BigDecimal amt, String invTpCd, String ccySgnTxt) {
        if (!hasValue(amt)) return null;
        if (ccySgnTxt == null) ccySgnTxt = "";
        amt = amt.setScale(2, BigDecimal.ROUND_HALF_UP);
        if (INV_TP.CREDIT_MEMO.equals(invTpCd)) amt = amt.negate();
        DecimalFormat df = new DecimalFormat(ccySgnTxt + "###,###,###,###,##0.00");
        //NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
        String strAmt = "";
        if (BigDecimal.ZERO.compareTo(amt) > 0) {
            strAmt = df.format(amt.abs());
            strAmt = setNegativeCurrencyFormat(strAmt);
        } else {
            strAmt = df.format(amt);
        }
        return strAmt;
    }

    /**
     * @param amt BigDecimal
     * @param ccySgnTxt String
     * @param ezPacTpCd String
     * @return
     */
    // 2018/08/16 QC#27438 Mod Start
    //private String fmtAmtFromNumToStrForEzPac(BigDecimal amt, String ccySgnTxt) {
    private String fmtAmtFromNumToStrForEzPac(BigDecimal amt, String ccySgnTxt, String ezPacTpCd) {
    // 2018/08/16 QC#27438 Mod End
        if (!hasValue(amt)) return null;
        if (ccySgnTxt == null) ccySgnTxt = "";
        // 2018/08/16 QC#27438 Mod Start
        //amt = amt.setScale(6, BigDecimal.ROUND_HALF_UP);
        //DecimalFormat df = new DecimalFormat(ccySgnTxt + "###,###,###,##0.000000");
        DecimalFormat df;
        if (EASY_PACK_TP.EASYPAC_TONER.equals(ezPacTpCd)) {
            amt = amt.setScale(2, BigDecimal.ROUND_HALF_UP);
            df = new DecimalFormat(ccySgnTxt + "###,###,###,##0.00");
        } else {
            amt = amt.setScale(6, BigDecimal.ROUND_HALF_UP);
            df = new DecimalFormat(ccySgnTxt + "###,###,###,##0.000000");
        }
        // 2018/08/16 QC#27438 Mod End
        //NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
        String strAmt = "";
        if (BigDecimal.ZERO.compareTo(amt) > 0) {
            strAmt = df.format(amt.abs());
            strAmt = setNegativeCurrencyFormat(strAmt);
        } else {
            strAmt = df.format(amt);
        }
        return strAmt;
    }
    
    private String setNegate(String str, String invTpCd) {

        if (str == null || "".equals(str) || "0".equals(str) || "$0.00".equals(str)) {
            return str;
        }

        if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
            if (str.startsWith("(")) {
                str = str.substring(1, str.length() - 1);
            } else if (str.startsWith("-")) {
                str = str.substring(1);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("(").append(str).append(")");
                str = sb.toString();
            }
        } else {
            if (str.startsWith("-")) {
                StringBuilder sb = new StringBuilder();
                sb.append("(").append(str.substring(1)).append(")");
                str = sb.toString();
            }
        }
        return str;
    }

    private String setNegateFromMinusToParentheses(String str) {
        if (str == null || !hasValue(str)) {
            return str;
        }
        if (str.startsWith("-")) {
            str = str.substring(1);
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(str).append(")");
            str = sb.toString();
        }
        
        return str;
        
    }
    private String setNegate(String str, BigDecimal amt, String invTpCd) {

        if (amt == null || BigDecimal.ZERO.compareTo(amt) == 0) {
            return str;
        }

        if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
            if (BigDecimal.ZERO.compareTo(amt) > 0) {
                StringBuilder sb = new StringBuilder();
                if (str.startsWith("-")) {
                    sb.append(str.substring(1));
                } else {
                    sb.append(str);
                }
                str = sb.toString();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("(").append(str).append(")");
                str = sb.toString();
            }
        } else {
            //negative amount
            if (BigDecimal.ZERO.compareTo(amt) > 0) {
                StringBuilder sb = new StringBuilder();
                if (str.startsWith("-")) {
                    str = str.substring(1);
                }
                sb.append("(").append(str).append(")");
                str = sb.toString();
            //positive
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                str = sb.toString();
            }
            
        }
        return str;
    }
    
    private String setNegateQty(String str, String invTpCd) {

        if (str == null || "".equals(str) || "0".equals(str)) {
            return str;
        }

        if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {

            if (str.startsWith("-")) {
                str = str.substring(1);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("-").append(str);
                str = sb.toString();
            }
        }
        return str;
    }

    private BigDecimal setNegate(BigDecimal num, String invTpCd) {

        if (num == null) {
            return null;
        }
        if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
            return num.negate();
        }
        return num;
    }

    private String getDtTxt(String date) {
        if (!ZYPCommonFunc.hasValue(date)) {
            return "";
        }
        String dateY = date.substring(0, 4);
        String dateM = date.substring(4, 6);
        String dateD = date.substring(6, 8);

        StringBuilder sb = new StringBuilder();
        sb.append(dateM).append("/").append(dateD).append("/").append(dateY);

        return sb.toString();
    }

    private int getLineCount(String message) {
        String[] messageLine = message.split(ENTER);

        return messageLine.length;
    }

    //2019/01/10 QC#29371 SRNO15 mod Start
    /**
     * @param nowParent Map<String, Object>
     * @param preParent Map<String, Object>
     * @return boolean
     */
    private boolean isSameParent(Map<String, Object> nowParent, Map<String, Object> preParent) {

        if (!isEqual(nowParent, preParent, "SHIP_TO_CODE")) {
            return false;
        }
        if (!isEqual(nowParent, preParent, "PO_NO")) {
            return false;
        }
        if (!isEqual(nowParent, preParent, "CUST_CODES1")) {
            return false;
        }
        if (!isEqual(nowParent, preParent, "CUST_CODES2")) {
            return false;
        }
        if (!isEqual(nowParent, preParent, "CUST_CODES3")) {
            return false;
        }
        if (!isEqual(nowParent, preParent, "CUST_CODES4")) {
            return false;
        }
        if (!isEqual(nowParent, preParent, "CUST_CODES5")) {
            return false;
        }
        if (!isEqual(nowParent, preParent, "CUST_CODES6")) {
            return false;
        }
        if (!isEqual(nowParent, preParent, "ORDER_NO")) {
            return false;
        }
        if (!isEqual(nowParent, preParent, "CHARGE_TYPE")) {
            return false;
        }
        if (!isEqual(nowParent, preParent, "SALES_REP_CODE")) {
            return false;
        }
        return true;

    }

//  private boolean isSameParent(Map<String, Object> nowParent, Map<String, Object> preParent) {
    //
////          if (!isEqual(nowParent, preParent, "SHIP_TO_CODE")) {
////              return false;
////          }
//          if (!isEqual(nowParent, preParent, "PO_NO")) {
//              return false;
//          }
////          if (!isEqual(nowParent, preParent, "CUST_CODES1")) {
////              return false;
////          }
////          if (!isEqual(nowParent, preParent, "CUST_CODES2")) {
////              return false;
////          }
////          if (!isEqual(nowParent, preParent, "CUST_CODES3")) {
////              return false;
////          }
////          if (!isEqual(nowParent, preParent, "CUST_CODES4")) {
////              return false;
////          }
////          if (!isEqual(nowParent, preParent, "CUST_CODES5")) {
////              return false;
////          }
////          if (!isEqual(nowParent, preParent, "CUST_CODES6")) {
////              return false;
////          }
//          if (!isEqual(nowParent, preParent, "ORDER_NO")) {
//              return false;
//          }
////          if (!isEqual(nowParent, preParent, "CHARGE_TYPE")) {
////              return false;
////          }
////          if (!isEqual(nowParent, preParent, "SALES_REP_CODE")) {
////              return false;
////          }
//          if (!isEqual(nowParent, preParent, "DS_ORD_POSN_NUM")) {
//              return false;
//          }
//          return true;
    //
//        }
    //2019/01/10 QC#29371 SRNO15 mod End
    
    
    /**
     * @param nowParent Map<String, Object>
     * @param preParent Map<String, Object>
     * @param paramNm String
     * @return boolean
     */
    private boolean isEqual(Map<String, Object> nowParent, Map<String, Object> preParent, String paramNm) {
        if (!nowParent.containsKey(paramNm)) {
            return false;
        }
        String str1 = (String) nowParent.get(paramNm);
        if (!preParent.containsKey(paramNm)) {
            return false;
        }
        String str2 = (String) preParent.get(paramNm);

        // 2019/04/04 QC#30756 Mod Start
        //return isEqual(str1, str2);
        if (CUST_ISS_PO_NUM.equals(paramNm)) {
            return isEqualsIgnoreCase(str1, str2);
        } else {
            return isEqual(str1, str2);
        }
        // 2019/04/04 QC#30756 Mod End
    }

    /**
     * @param nowStr Map<String, Object>
     * @param preStr Map<String, Object>
     * @param paramNm String
     * @return boolean
     */
    private boolean isEqual(String nowStr, String preStr) {

        if (nowStr == null) {
            if (preStr == null) {
                return true;
            }
            return false;
        }
        if (preStr == null) {
            return false;
        }
        return nowStr.equals(preStr);
    }

    // 2019/04/04 QC#30756 Add Start
    /**
     * @param nowStr Map<String, Object>
     * @param preStr Map<String, Object>
     * @param paramNm String
     * @return boolean
     */
    private boolean isEqualsIgnoreCase(String nowStr, String preStr) {

        if (nowStr == null) {
            if (preStr == null) {
                return true;
            }
            return false;
        }
        if (preStr == null) {
            return false;
        }
        return nowStr.equalsIgnoreCase(preStr);
    }
    // 2019/04/04 QC#30756 Add End

    private BigDecimal addBig(BigDecimal amt1, Map<String, Object> map, String paramNm) {

        Object obj = map.get(paramNm);
        if (obj == null) {
            return amt1;
        }

        amt1 = amt1.add((BigDecimal) obj);
        return amt1;
    }

    /**
     * null conversion empty for append
     * @param str String
     * @return String
     */
    private String nullConvEmp(String str) {

        if (!ZYPCommonFunc.hasValue(str)) {
            str = "";
        }
        return str;

    }

    private Map<String, Object> resultSetMap(ResultSet rs) throws SQLException, NWCB010001BusinessException {
        Map<String, Object> rsMap = new HashMap<String, Object>();
        rsMap.put("CONS_BILL_NUM", rs.getObject("CONS_BILL_NUM"));
        rsMap.put("CONS_BILL_DT", rs.getObject("CONS_BILL_DT"));
        rsMap.put("CONS_BILL_DUE_DT", rs.getObject("CONS_BILL_DUE_DT"));
        rsMap.put("CONS_BILL_TO_CD", rs.getObject("CONS_BILL_TO_CD"));
        rsMap.put("CONS_BILL_TO_NM", rs.getObject("CONS_BILL_TO_NM"));
        rsMap.put("CONS_BILL_TO_ATTENTION", rs.getObject("CONS_BILL_TO_ATTENTION"));
        rsMap.put("CONS_BILL_TO_FST_ADDR", rs.getObject("CONS_BILL_TO_FST_ADDR"));
        rsMap.put("CONS_BILL_TO_SCD_ADDR", rs.getObject("CONS_BILL_TO_SCD_ADDR"));
        rsMap.put("CONS_BILL_TO_CTY", rs.getObject("CONS_BILL_TO_CTY"));
        rsMap.put("CONS_BILL_TO_ST", rs.getObject("CONS_BILL_TO_ST"));
        rsMap.put("CONS_BILL_TO_ZIP_CD", rs.getObject("CONS_BILL_TO_ZIP_CD"));
        rsMap.put("CONS_BILL_TO_ACCT", rs.getObject("CONS_BILL_TO_ACCT"));
        rsMap.put("CONS_BILL_TO_ACCT_NM", rs.getObject("CONS_BILL_TO_ACCT_NM"));
        rsMap.put("CONS_BILL_TO_LOC_NUM", rs.getObject("CONS_BILL_TO_LOC_NUM"));
        rsMap.put("CONS_TERM_CD", rs.getObject("CONS_TERM_CD"));
        rsMap.put("CONS_TERMS", rs.getObject("CONS_TERMS"));
        rsMap.put("CONS_REMIT_TO_EIN_CD", rs.getObject("CONS_REMIT_TO_EIN_CD"));
        rsMap.put("CONS_REMIT_TO_NM", rs.getObject("CONS_REMIT_TO_NM"));
        rsMap.put("CONS_REMIT_TO_FST_ADDR", rs.getObject("CONS_REMIT_TO_FST_ADDR"));
        rsMap.put("CONS_REMIT_TO_SCD_ADDR", rs.getObject("CONS_REMIT_TO_SCD_ADDR"));
        rsMap.put("CONS_REMIT_TO_CTY", rs.getObject("CONS_REMIT_TO_CTY"));
        rsMap.put("CONS_REMIT_TO_ST", rs.getObject("CONS_REMIT_TO_ST"));
        rsMap.put("CONS_REMIT_TO_POST", rs.getObject("CONS_REMIT_TO_POST"));
        rsMap.put("CONS_CREATE_USER", rs.getObject("CONS_CREATE_USER"));
        rsMap.put("CONS_CREATE_DT", rs.getObject("CONS_CREATE_DT"));
        rsMap.put("DS_BIZ_AREA_CD", rs.getObject("DS_BIZ_AREA_CD"));
        rsMap.put("LINE_BIZ_TP_CD", rs.getObject("LINE_BIZ_TP_CD"));
        rsMap.put("FOOTER_MSG", rs.getObject("FOOTER_MSG"));
        rsMap.put("INV_NUM", rs.getObject("INV_NUM"));
        rsMap.put("INV_TP_CD", rs.getObject("INV_TP_CD"));
        rsMap.put("INV_AMT", rs.getObject("INV_AMT"));
        rsMap.put("BALANCE_AMT", rs.getObject("BALANCE_AMT"));
        rsMap.put("INV_CHARGE_AMT", rs.getObject("INV_CHARGE_AMT"));
        rsMap.put("FRT_AMT", rs.getObject("FRT_AMT"));
        rsMap.put("TAX_AMT", rs.getObject("TAX_AMT"));
        rsMap.put("PRE_PMT_AMT", rs.getObject("PRE_PMT_AMT"));
        rsMap.put("ISTL_PMT_TERM_FLG", rs.getObject("ISTL_PMT_TERM_FLG"));
        rsMap.put("PMT_CC_FLG", rs.getObject("PMT_CC_FLG"));
        rsMap.put("TAX_DETAIL_FLG", rs.getObject("TAX_DETAIL_FLG"));
        rsMap.put("DETAIL_DISPLAY_FLG", rs.getObject("DETAIL_DISPLAY_FLG"));
        rsMap.put("BATCH_TP", rs.getObject("BATCH_TP"));
        rsMap.put("BATCH_TP_NM", rs.getObject("BATCH_TP_NM"));
        rsMap.put("BILL_TO_CUST_CD", rs.getObject("BILL_TO_CUST_CD"));
        rsMap.put("CONTROL_REC_CD", rs.getObject("CONTROL_REC_CD"));
        rsMap.put("DS_ORD_CATG_CD", rs.getObject("DS_ORD_CATG_CD"));
        rsMap.put("DS_ORD_TP_CD", rs.getObject("DS_ORD_TP_CD"));
        rsMap.put("ORD_DT", rs.getObject("ORD_DT"));
        rsMap.put("INV_PRT_CTRL_PK", rs.getObject("INV_PRT_CTRL_PK"));
        rsMap.put("COMMENT1", rs.getObject("COMMENT1"));
        rsMap.put("COMMENT2", rs.getObject("COMMENT2"));
        rsMap.put("COMMENT3", rs.getObject("COMMENT3"));
        rsMap.put("COMMENT4", rs.getObject("COMMENT4"));
        rsMap.put("INV_PRINT_FLG", rs.getObject("INV_PRINT_FLG"));
        rsMap.put("INV_PRINT_MSG_TXT", rs.getObject("INV_PRINT_MSG_TXT"));
        rsMap.put("CCY_SGN_TXT", rs.getObject("CCY_SGN_TXT"));
        rsMap.put("PMT_TERM_CASH_DISC_CD", rs.getObject("PMT_TERM_CASH_DISC_CD"));
        rsMap.put("PMT_TERM_CASH_DISC_DESC_TXT", rs.getObject("PMT_TERM_CASH_DISC_DESC_TXT"));
        rsMap.put(CONSL_TOT_DEAL_NET_AMT, rs.getObject(CONSL_TOT_DEAL_NET_AMT));
        rsMap.put("ORIG_CONSL_BILL_PK", rs.getObject("ORIG_CONSL_BILL_PK")); // QC#53015 2019/09/05 Add
        
        return rsMap;

    }

    private void setInitInvPrtCtrlForConsTermsWait(NMZC610001PMsg custInvInfo, INV_PRT_CTRLTMsg invPrtCtrlTmsg) {

        String vehicle = null;

        if (custInvInfo.InvoiceRuleList.getValidCount() == 0) {
            // In the case of No data , Vehicle Print Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (hasValue(invPrtCtrlTmsg.invPrtProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invPrtProcStsCd.getValue())) {
                if (dataReCreationFlg) {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
                } else {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
                }
            }
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        }

        vehicle = custInvInfo.InvoiceRuleList.no(0).custBllgVcleCd.getValue();
        // Vehicle EDI
        if (CUST_BLLG_VCLE.EDI.equals(vehicle)) {
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            if (hasValue(invPrtCtrlTmsg.invEdiProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invEdiProcStsCd.getValue())) {
                invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.EDI_INVOICE);
        } else if (CUST_BLLG_VCLE.EMAIL_ONLY.equals(vehicle)) {
            // Vehicle Email Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            if (hasValue(invPrtCtrlTmsg.invEmlProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invEmlProcStsCd.getValue())) {
                if (dataReCreationFlg) {
                    invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
                } else {
                    invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
                }
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        } else if (CUST_BLLG_VCLE.MANUAL_SPECIAL_BILL.equals(vehicle)) {
            // Vehicle SB Manual
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            //QC#19274
            //invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            if (hasValue(invPrtCtrlTmsg.invSpclBillProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invSpclBillProcStsCd.getValue())) {
                invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.MANUAL_BILLING);
        } else if (CUST_BLLG_VCLE.SPECIAL_BILL_NO_REVIEW.equals(vehicle)) {
            // Vehicle SB No Review
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            if (hasValue(invPrtCtrlTmsg.invSpclBillProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invSpclBillProcStsCd.getValue())) {
                invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.SPECIAL_BILLING);
        } else if (CUST_BLLG_VCLE.SPECIAL_BILL_REVIEW_REQD.equals(vehicle)) {
            // Vehicle SB Required
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            if (hasValue(invPrtCtrlTmsg.invSpclBillProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invSpclBillProcStsCd.getValue())) {
                invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_ON_1);
            }
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.SPECIAL_BILLING);
        } else if (CUST_BLLG_VCLE.PRINT_ONLY.equals(vehicle)) {
            // Vehicle Print Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (hasValue(invPrtCtrlTmsg.invPrtProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invPrtProcStsCd.getValue())) {
                if (dataReCreationFlg) {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
                } else {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
                }
            }
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        } else if (CUST_BLLG_VCLE.PRINT_AND_EMAIL.equals(vehicle)) {
            // Vehicle Print & Email
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (hasValue(invPrtCtrlTmsg.invPrtProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invPrtProcStsCd.getValue())) {
                if (dataReCreationFlg) {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
                } else {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
                }
            }
            if (hasValue(invPrtCtrlTmsg.invEmlProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invEmlProcStsCd.getValue())) {
                if (dataReCreationFlg) {
                    invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
                } else {
                    invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
                }
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        } else if (CUST_BLLG_VCLE.DO_NOT_PRINT.equals(vehicle)) {
            // Vehicle Do Not Print
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        } else if (CUST_BLLG_VCLE.NEEDS_EMAIL_REVIEW.equals(vehicle)) {
            // Vehicle Need Email Review
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            if (hasValue(invPrtCtrlTmsg.invEmlProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invEmlProcStsCd.getValue())) {
                if (dataReCreationFlg) {
                    invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
                } else {
                    invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
                }
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        //QC#6934
        } else if (CUST_BLLG_VCLE.DAILY_SUMMARY_EMAIL_AND_PRINT.equals(vehicle)) {
            // Vehicle Print & Email
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (hasValue(invPrtCtrlTmsg.invPrtProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invPrtProcStsCd.getValue())) {
                if (dataReCreationFlg) {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
                } else {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
                }
            }
            if (hasValue(invPrtCtrlTmsg.invEmlProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invEmlProcStsCd.getValue())) {
                if (dataReCreationFlg) {
                    invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
                } else {
                    invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
                }
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        //QC#26846
        } else if (CUST_BLLG_VCLE.WEB_BILLING_PRINT_ONLY.equals(vehicle)) {
            // Vehicle Print Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (hasValue(invPrtCtrlTmsg.invPrtProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invPrtProcStsCd.getValue())) {
                if (dataReCreationFlg) {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
                } else {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
                }
            }
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        } else if (CUST_BLLG_VCLE.WEB_BILLING_PRINT_AND_EMAIL.equals(vehicle)) {
            // Vehicle Print & Email
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (hasValue(invPrtCtrlTmsg.invPrtProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invPrtProcStsCd.getValue())) {
                if (dataReCreationFlg) {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
                } else {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
                }
            }
            if (hasValue(invPrtCtrlTmsg.invEmlProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invEmlProcStsCd.getValue())) {
                if (dataReCreationFlg) {
                    invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
                } else {
                    invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
                }
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        // 2018/01/16 QC#29371 SRNO#13 Add Start
        } else if (CUST_BLLG_VCLE.STRATEGIC_ACCOUNTS_PRINT_ONLY.equals(vehicle)) {
            // Vehicle Strategic Accounts Print Only
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (hasValue(invPrtCtrlTmsg.invPrtProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invPrtProcStsCd.getValue())) {
                if (dataReCreationFlg) {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
                } else {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
                }
            }
            invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        } else if (CUST_BLLG_VCLE.STRATEGIC_ACCOUNTS_PRINT_AND_EMAIL.equals(vehicle)) {
            // Vehicle Strategic Accounts Print & Email
            invPrtCtrlTmsg.invOtptReqFlg.setValue(ZYPConstant.FLG_ON_Y);
            if (hasValue(invPrtCtrlTmsg.invPrtProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invPrtProcStsCd.getValue())) {
                if (dataReCreationFlg) {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(INV_PRT_STS_2);
                } else {
                    invPrtCtrlTmsg.invPrtProcStsCd.setValue(ZYPConstant.FLG_ON_1);
                }
            }
            if (hasValue(invPrtCtrlTmsg.invEmlProcStsCd) && ZYPConstant.FLG_OFF_0.equals(invPrtCtrlTmsg.invEmlProcStsCd.getValue())) {
                if (dataReCreationFlg) {
                    invPrtCtrlTmsg.invEmlProcStsCd.setValue(INV_PRT_STS_2);
                } else {
                    invPrtCtrlTmsg.invEmlProcStsCd.setValue(ZYPConstant.FLG_ON_1);
                }
            }
            invPrtCtrlTmsg.invEdiProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invSpclBillProcStsCd.setValue(ZYPConstant.FLG_OFF_0);
            invPrtCtrlTmsg.invRvwReqFlg.setValue(ZYPConstant.FLG_OFF_N);
            invPrtCtrlTmsg.invProcTpCd.setValue(INV_PROC_TP.NEW_CUSTOM_BILLING);
        // 2018/01/16 QC#29371 SRNO#13 Add End
        }
    }
    
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSummaryForConsol(BigDecimal conslBillPk, String taxDtlFlg, String dtlDplyFlg, boolean isEasyPac1Flg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("conslBillPk", conslBillPk);
        ssmParam.put("taxDtlFlg", taxDtlFlg);
        ssmParam.put("dsSlsTaxTpCd01", DS_SLS_TAX_TP.STATE_TAX);
        ssmParam.put("dsSlsTaxTpCd02", DS_SLS_TAX_TP.COUNTY_TAX);
        ssmParam.put("dsSlsTaxTpCd03", DS_SLS_TAX_TP.CITY_TAX);
        ssmParam.put("siSrcTpCdCONTR", SVC_INV_SRC_TP.CONTRACT);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.FLEET);
        ssmParam.put("siChrgTpCdBC", SVC_INV_CHRG_TP.BASE_CHARGE);
        ssmParam.put("siChrgTpCdAC", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        ssmParam.put("siChrgTpCdMC", SVC_INV_CHRG_TP.METER_CHARGE);
        ssmParam.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("amtFormat", FM_AMT);
        
        
        ssmParam.put("dsContrCatgCdREG", DS_CONTR_CATG.REGULAR);
        ssmParam.put("dsContrCatgCdAGG", DS_CONTR_CATG.AGGREGATE);
        ssmParam.put("siLineTpCdMACH", SVC_INV_LINE_TP.MACHINE);
        ssmParam.put("siLineTpCdACSY", SVC_INV_LINE_TP.ACCESSORY);
        ssmParam.put("qtyFormat", FM_QTY);
        //QC#17491
        ssmParam.put("qtyFormat4", FM_QTY4);

        
        ssmParam.put("siSrcTpCdDSPT", SVC_INV_SRC_TP.DISPATCH);
        ssmParam.put("invSmryLineTpNmNC", NON_COPIER);
        ssmParam.put("invSmryLineTpNmC", COPIER);
        ssmParam.put("ordClsPL", PARTS_OR_LABOR);
        ssmParam.put("prodCd", "K%");
        //QC#25026
        //ssmParam.put("coaMdseTpCd", COA_PROJ.SOFTWARE);
        ssmParam.put("coaMdseTpCd", COA_PROJ_CD_ZZZ);
        
        ssmParam.put("dtlDplyFlg", dtlDplyFlg);
        ssmParam.put("ordClsSUPL", SUPPLY);
        ssmParam.put("ordClsPARTS", PARTS);  //QC#26846
        ssmParam.put("ordClsSL", SALE);
        ssmParam.put("ordCatgCtxTp", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        // QC#31177 2019/04/16 Add Start
        ssmParam.put("ordCatgCtxTpNoConfig", ORD_CATG_CTX_TP.INV_PRT_NOCONF_ORDER);
        // QC#31177 2019/04/16 Add End
        ssmParam.put("ordCatgCtxTpParts", "INV_PRT_PARTS_ORDER");  //QC#26846
        ssmParam.put("creditAndOdRebill", CREDIT_ANDOR_REBILL);
        ssmParam.put("easyPac1Return", ORD_CATG_CTX_TP.EASY_PAC1_RETURN);
        ssmParam.put("easyPac1", ORD_CATG_CTX_TP.EASY_PAC1);
        // QC#20141 mod Start
        //ssmParam.put("shortFall", "SHORTFALL");
        ssmParam.put("shortFall", "Contract Shortfall Sq Footage");
        // QC#20141 mod End
        ssmParam.put("varCharConst1", nullConvEmp(this.invPrintShortFallTxt));
        ssmParam.put("varCharConst2", nullConvEmp(this.invPrintShortFallItem));
        ssmParam.put("isEasyPac1Flg", isEasyPac1Flg);
        ssmParam.put("zdInvPrtExcl", "ZD_INV_PRT_EXCL");
        ssmParam.put("invLineCatgFreight", INV_LINE_CATG.FREIGHT); // QC#29371

        // 2019/09/07  QC#53026 Add Start
        ssmParam.put("invLineCatgItem", INV_LINE_CATG.ITEM);
        ssmParam.put("invLineCatgChg", INV_LINE_CATG.CHARGE);
        // 2019/09/07  QC#53026 Add End
        // QC#53598 2019/12/02 Add Start
        ssmParam.put("invPrtPrmoItems", INV_PRT_PRMO_ITEMS);
        ssmParam.put("prmoItemPrintFlg", ZYPConstant.FLG_OFF_N);
        // QC#53598 2019/12/02 Add End
        

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSummaryForConsol", ssmParam);
    }
    // QC#20141 mod Start
    //private int setSmryForConsl(List<INV_PRT_SMRYTMsg> invPrtSmryList, List<Map<String, Object>> resultMapList, Map<String, Object> rsMap, int lineCount)
    private int setSmryForConsl(List<INV_PRT_SMRYTMsg> invPrtSmryList, List<Map<String, Object>> resultMapList, Map<String, Object> rsMap, int lineCount, boolean isEasyPac1Flg, BigDecimal itemAmt)
    // QC#20141 mod End
    throws SQLException, NWCB010001BusinessException {

        INV_PRT_SMRYTMsg invPrtSmryTMsg;
        BigDecimal bConslBillNum = (BigDecimal) rsMap.get(CONS_BILL_NUM);
        String ccySgnTxt = (String) rsMap.get(CCY_SGN_TXT);
        String conslBillNum = String.valueOf(bConslBillNum);
    
        String ordClsNm = "";
        String prevOrdClsNm = null;
        String invSmryLineTpNm = "";
    
        BigDecimal invSmryStTaxAmt   = new BigDecimal(0);
        BigDecimal invSmryCntyTaxAmt = new BigDecimal(0);
        BigDecimal invSmryCityTaxAmt = new BigDecimal(0);
        BigDecimal invSmryTotTaxAmt  = new BigDecimal(0);
        BigDecimal invSmrySubTotAmt  = new BigDecimal(0);
        
        boolean qtyNotZeroFlg = false;
        for (Map<String, Object> resultMap : resultMapList) {
            if (resultMap.get(QTY) != null && !"0".equals((String) resultMap.get(QTY))) {
                qtyNotZeroFlg = true;
            }
        }
        int recCount = 0;
        for (Map<String, Object> resultMap : resultMapList) {
            //Qty ZERO Suppress
            if (qtyNotZeroFlg && resultMap.get(QTY) != null && "0".equals((String) resultMap.get(QTY))) {
                // 2018/10/11 QC#28590 Add Start
                if (isEasyPac1Flg) {
                    // summary
                    BigDecimal stateTaxAmt = (BigDecimal) resultMap.get(STATE_TAX);
                    if (stateTaxAmt != null) {
                        invSmryStTaxAmt = invSmryStTaxAmt.add(stateTaxAmt);
                    }
                    BigDecimal countyTaxAmt = (BigDecimal) resultMap.get(COUNTY_TAX);
                    if (countyTaxAmt != null) {
                        invSmryCntyTaxAmt = invSmryCntyTaxAmt.add(countyTaxAmt);
                    }
                    BigDecimal cityTaxAmt = (BigDecimal) resultMap.get(CITY_TAX);
                    if (cityTaxAmt != null) {
                        invSmryCityTaxAmt = invSmryCityTaxAmt.add(cityTaxAmt);
                    }
                    BigDecimal totalTaxAmt = (BigDecimal) resultMap.get(TOTAL_TAX);
                    if (totalTaxAmt != null) {
                        invSmryTotTaxAmt = invSmryTotTaxAmt.add(totalTaxAmt);
                    }
                    BigDecimal subTotalAmt = (BigDecimal) resultMap.get(SUBTOTALS);
                    if (subTotalAmt != null) {
                        invSmrySubTotAmt = invSmrySubTotAmt.add(subTotalAmt);
                    }
                    // QC#52868 2019/08/20 Add Start
                    continue;
                    // QC#52868 2019/08/20 Add End
                }
                // 2018/10/11 QC#28590 Add End
                // QC#52868 2019/08/20 Del Start
                //continue;
                // QC#52868 2019/08/20 Del End
            }

            ordClsNm        = resultMap.get(SOURCE) == null ? "" : (String) resultMap.get(SOURCE);
            invSmryLineTpNm = resultMap.get(PRODUCT_TYPE) == null ? "" : (String) resultMap.get(PRODUCT_TYPE);
    
            if (prevOrdClsNm == null) {
                prevOrdClsNm = ordClsNm;
            }
            if (recCount == 0) {
                lineCount++;
            }
            lineCount++;
            // QC#20141 mod Start
            if (isEasyPac1Flg) {
                ordClsNm = "";
                prevOrdClsNm = "";
                if (COPIER.equals(invSmryLineTpNm)) {
                    invSmryLineTpNm = COPIER_EASY_PAC1;
                } else if (SHORTFALL.equals(invSmryLineTpNm)) {
                    invSmryLineTpNm = SHORTFALL_EASY_PAC1;
                }
            }
            // QC#54343 2019/11/07 Add Start
            if (!ordClsNm.equals(prevOrdClsNm)) {

                // tax line
                lineCount++;
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, prevOrdClsNm, null, lineCount, isEasyPac1Flg, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryStTaxAmtTxt,   fmtAmtFromNumToStr(invSmryStTaxAmt,  ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCntyTaxAmtTxt, fmtAmtFromNumToStr(invSmryCntyTaxAmt,  ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCityTaxAmtTxt, fmtAmtFromNumToStr(invSmryCityTaxAmt,  ccySgnTxt));
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotTaxAmtTxt,  fmtAmtFromNumToStr(invSmryTotTaxAmt,  ccySgnTxt));
                invPrtSmryList.add(invPrtSmryTMsg);
                // sub total
                lineCount++;
                // QC#57344
                invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, prevOrdClsNm, null, lineCount, isEasyPac1Flg, invPrtSmryList);
                ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmrySubTotAmtTxt,  fmtAmtFromNumToStr(invSmrySubTotAmt,  ccySgnTxt));
                invPrtSmryList.add(invPrtSmryTMsg);

                // init amount
                invSmryStTaxAmt   = new BigDecimal(0);
                invSmryCntyTaxAmt = new BigDecimal(0);
                invSmryCityTaxAmt = new BigDecimal(0);
                invSmryTotTaxAmt  = new BigDecimal(0);
                invSmrySubTotAmt  = new BigDecimal(0);

                prevOrdClsNm = ordClsNm;
            }
            // QC#54343 2019/11/07 Add End
            // QC#20141 mod End
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, ordClsNm, invSmryLineTpNm, lineCount, isEasyPac1Flg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryLineTpNm,       invSmryLineTpNm);

            // 2019/07/16 QC#50885 Mod Start
            String qty;

            if (ZYPConstant.CHKBOX_ON_1.equals((String)resultMap.get(IS_MANTE))){
                qty = "";
            } else {
                qty = (String) resultMap.get(QTY);
            }
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryLineQtyTxt,     qty);
            //ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryLineQtyTxt,     (String) resultMap.get(QTY));
            // 2019/07/16 QC#50885 Mod End

            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryBaseChrgAmtTxt, setNegateFromMinusToParentheses((String) resultMap.get(BASE_CHARGE)));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryAttChrgAmtTxt,  setNegateFromMinusToParentheses((String) resultMap.get(ATTACHMENT_CHARGE)));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryUsgChrgAmtTxt,  setNegateFromMinusToParentheses((String) resultMap.get(USAGE_CHARGE)));
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryChrgAmtTxt,     setNegateFromMinusToParentheses((String) resultMap.get(AMOUNT)));
            invPrtSmryList.add(invPrtSmryTMsg);
    
            //summary
            BigDecimal stateTaxAmt  = (BigDecimal) resultMap.get(STATE_TAX);
            if (stateTaxAmt != null) {
                invSmryStTaxAmt = invSmryStTaxAmt.add(stateTaxAmt);
            }
            BigDecimal countyTaxAmt = (BigDecimal) resultMap.get(COUNTY_TAX);
            if (countyTaxAmt != null) {
                invSmryCntyTaxAmt = invSmryCntyTaxAmt.add(countyTaxAmt);
            }
            BigDecimal cityTaxAmt   = (BigDecimal) resultMap.get(CITY_TAX);
            if (cityTaxAmt != null) {
                invSmryCityTaxAmt = invSmryCityTaxAmt.add(cityTaxAmt);
            }
            BigDecimal totalTaxAmt  = (BigDecimal) resultMap.get(TOTAL_TAX);
            if (totalTaxAmt != null) {
                invSmryTotTaxAmt = invSmryTotTaxAmt.add(totalTaxAmt);
            }
            BigDecimal subTotalAmt  = (BigDecimal) resultMap.get(SUBTOTALS);
            if (subTotalAmt != null) {
                invSmrySubTotAmt = invSmrySubTotAmt.add(subTotalAmt);
            }
            recCount++;
        }
        
        if (isEasyPac1Flg) {
            //Supplies INV_SMRY_AMT_SPLY_TXT
            lineCount++;
            // QC#57344
            invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, ordClsNm, null, lineCount, isEasyPac1Flg, invPrtSmryList);
            ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryAmtSplyTxt,  fmtAmtFromNumToStr(itemAmt, ccySgnTxt));
            invPrtSmryList.add(invPrtSmryTMsg);
        }
        
        // tax line
        lineCount++;
        // QC#57344
        invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, ordClsNm, null, lineCount, isEasyPac1Flg, invPrtSmryList);
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryStTaxAmtTxt,   fmtAmtFromNumToStr(invSmryStTaxAmt, ccySgnTxt));
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCntyTaxAmtTxt, fmtAmtFromNumToStr(invSmryCntyTaxAmt, ccySgnTxt));
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryCityTaxAmtTxt, fmtAmtFromNumToStr(invSmryCityTaxAmt, ccySgnTxt));
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmryTotTaxAmtTxt,  fmtAmtFromNumToStr(invSmryTotTaxAmt, ccySgnTxt));
        invPrtSmryList.add(invPrtSmryTMsg);
        // sub total
        lineCount++;
        // QC#57344
        invPrtSmryTMsg = getInvPrtSmryPMsg(conslBillNum, ordClsNm, null, lineCount, isEasyPac1Flg, invPrtSmryList);
        ZYPEZDItemValueSetter.setValue(invPrtSmryTMsg.invSmrySubTotAmtTxt,  fmtAmtFromNumToStr(invSmrySubTotAmt, ccySgnTxt));
        invPrtSmryList.add(invPrtSmryTMsg);
    
        return lineCount;
    }
    
    @Override
    protected void termRoutine() {
        // Set termination code and total commit count.
        setTermState(this.termCd, normCnt, errCnt, normCnt + errCnt);
    }
    
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getPmtTermInfoList(String pmtTermCd) {
        String key = createCacheKey(PMT_TERM_INFO_LIST, pmtTermCd);
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.cache.get(key);
        if (list == null) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("pmtTermCd", pmtTermCd);
            list = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPmtTermInfo", ssmParam);
            this.cache.put(key, list);
        }
        return list;
    }
    
    private String repString(BigDecimal val) {
        if (hasValue(val)) {
            return val.toString();
        } else {
            return "";
        }
    }

    private String getCreditCardLastDigit(ResultSet rs) throws SQLException, NWCB010001BusinessException {
        String result = null;
        String sysSrcCd = rs.getString(SYS_SRC_CD);
        if (SYS_SRC.S21_ACCOUNTING_AR.equals(sysSrcCd)) {
            result = getCreditCardLastDigitForINV(rs.getString(INV_NUM));
        } else {
            String formatTp = rs.getString(FORMAT_TYPE);
            if (INV_PRT_DTL_FMT_TP.FLEET_CONTRACT.equals(formatTp)) {
                result = rs.getString(CR_CARD_LAST_DIGIT);
            } else if (INV_PRT_DTL_FMT_TP.MAINTENANCE.equals(formatTp)) {
                result = rs.getString(CR_CARD_LAST_DIGIT);
            } else if (INV_PRT_DTL_FMT_TP.SALE_AND_SUPPLY.equals(formatTp)) {
                result = getCreditCardLastDigitForOM(rs.getString(CPO_ORD_NUM));
            } else if (INV_PRT_DTL_FMT_TP.PARTS_AND_LABOR.equals(formatTp)) {
                result = getCreditCardLastDigitForSC(rs.getString(FSR_NUM), rs.getString(CR_CARD_CUST_REF_NUM));
            }
        }
        return result;
    }
    @SuppressWarnings("unchecked")
    private String getCreditCardLastDigitForINV(String invNum) {
        if (!hasValue(invNum)) return null;
        String crCardLastDigit = null;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getCreditCardLastDigitForINV", ssmParam);
        if (map != null && map.get(CR_CARD_LAST_DIGIT) != null) {
            crCardLastDigit = (String) map.get(CR_CARD_LAST_DIGIT);
        }
        return crCardLastDigit;
    }
    @SuppressWarnings("unchecked")
    private String getCreditCardLastDigitForOM(String cpoOrdNum) {
        if (!hasValue(cpoOrdNum)) return null;
        String crCardLastDigit = null;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getCreditCardLastDigitForOM", ssmParam);
        if (map != null && map.get(CR_CARD_LAST_DIGIT) != null) {
            crCardLastDigit = (String) map.get(CR_CARD_LAST_DIGIT);
        }
        return crCardLastDigit;
    }
    @SuppressWarnings("unchecked")
    private String getCreditCardLastDigitForSC(String fsrNum, String crCardCustRefNum) {
        if (!hasValue(fsrNum) || !hasValue(crCardCustRefNum)) return null;
        String crCardLastDigit = null;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("fsrNum", fsrNum);
        ssmParam.put("crCardCustRefNum", crCardCustRefNum);
        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getCreditCardLastDigitForSC", ssmParam);
        if (map != null && map.get(CR_CARD_LAST_DIGIT) != null) {
            crCardLastDigit = (String) map.get(CR_CARD_LAST_DIGIT);
        }
        return crCardLastDigit;
    }
    
    private String cutOffString(String val, int len) {
        if (ZYPCommonFunc.hasValue(val) && val.length() > len) {
            return val.substring(0, len);
        } else {
            return val;
        }
    }

    private String[] toArray(String ...appendMsgList) {
        String[] msgArray = new String[appendMsgList.length];
        for (int i = 0; i < msgArray.length; i++) {
            msgArray[i] = appendMsgList[i];
        }
        return msgArray;
    }

    private boolean insertCtrlForConsl(INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray, String conslBillNum) {

        for (int i = 0; i < invPrtCtrlTmsgArray.length; i++) {
            INV_PRT_CTRLTMsg invPrtCtrlTmsg = invPrtCtrlTmsgArray[i];
            invPrtCtrlTmsg.invPrtCtrlPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRT_CTRL_SQ));
        }

        int result = EZDFastTBLAccessor.insert(invPrtCtrlTmsgArray);
        if (result != invPrtCtrlTmsgArray.length) {
            //NWCM0109E=0,It failed to register @ Table.[@]
            S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_CTRLTMsg().getTableName(), conslBillNum));
            return false;
        }
//        normCnt += result;
        return true;
    }
    private boolean insertHdrForConsl(INV_PRT_HDRTMsg[] invPrtHdrTmsgArray, INV_PRT_CTRLTMsg[] invPrtCtrlTmsgArray, String conslBillNum) {

        for (int i = 0; i < invPrtHdrTmsgArray.length; i++) {
            INV_PRT_HDRTMsg invPrtHdrTmsg = invPrtHdrTmsgArray[i];
            INV_PRT_CTRLTMsg invPrtCtrlTmsg = invPrtCtrlTmsgArray[i];

            invPrtHdrTmsg.invPrtCtrlPk.setValue(invPrtCtrlTmsg.invPrtCtrlPk.getValue());
        }

        int result = EZDFastTBLAccessor.insert(invPrtHdrTmsgArray);
        if (result != invPrtCtrlTmsgArray.length) {
            //NWCM0109E=0,It failed to register @ Table.[@]
            S21InfoLogOutput.println(NWCM0109E, toArray(new INV_PRT_HDRTMsg().getTableName(), conslBillNum));
            return false;
        }
//        normCnt += result;
        return true;
    }

    private void postErrMail() {

        // get mail information : address
        S21Mail mail = new S21Mail(glblCmpyCd);
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, GROUP_FROM);

        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        if (fromAddrList.isEmpty()) {
            throw new S21AbendException(NWCM0060E);
        }
        mail.setFromAddress(fromAddrList.get(0));

        S21MailGroup group = new S21MailGroup(glblCmpyCd, ML_GRP_ID);
        List<S21MailAddress> toAddrList = group.getMailAddress();

        if (toAddrList.isEmpty()) {
            throw new S21AbendException(NWCM0060E);
        }
        mail.setToAddressList(toAddrList);

        // get mail template
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        template.setTemplateParameter("batchId", ML_GRP_ID);
        template.setTemplateParameter("batchNm", BATCH_NM);
        template.setTemplateParameter("batchProcLogId", super.getBatchProcessLogID());

        // set Message
        StringBuilder msg = new StringBuilder();
        for (Map<String, String> map : mailErrorList) {
            msg.append(LINE_FEED_CODE);
            msg.append(map.get(NUMBER));
            msg.append(SEPARATOR);
            msg.append(map.get(MESSAGE));
        }

        template.setTemplateParameter("ErrorInfo", msg.toString());
        mail.setMailTemplate(template);

        // post mail
        mail.postMail();
    }
    /**
     * setMailMessage
     * @param key long
     * @param number String
     * @param msgId String
     * @param msgParam String[]
     */
    private void setMailMessage(String number, String msgId, String[] msgParam) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(NUMBER, number);
        map.put(MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        this.mailErrorList.add(map);
    }
    
    @SuppressWarnings("unchecked")
    private boolean getOrdCatgBizCtxExistsFlg(String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd, List<String> ordCatgCtxTpList) {
        if (!hasValue(dsOrdCatgCd) || ordCatgCtxTpList == null || ordCatgCtxTpList.size() == 0) return false;
        for (String ordCatgCtxTp : ordCatgCtxTpList) {
            String key = createCacheKey(ORD_CATG_BIZ_CTX_EXISTS_FLG, dsOrdCatgCd + nullConvEmp(dsOrdTpCd) + nullConvEmp(dsOrdRsnCd) + ordCatgCtxTp);
            String existsFlg = (String) this.cache.get(key);
            if (existsFlg == null) {
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("dsOrdCatgCd", dsOrdCatgCd);
                ssmParam.put("dsOrdTpCd", dsOrdTpCd);
                ssmParam.put("dsOrdRsnCd", dsOrdRsnCd);
                ssmParam.put("ordCatgCtxTpList", ordCatgCtxTpList);
                Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getOrdCatgBizCtxExistsFlg", ssmParam);
                existsFlg = ZYPConstant.FLG_OFF_N;
                if (map != null && map.get(EXISTS_FLG) != null) {
                    existsFlg = ZYPConstant.FLG_ON_Y;
                }
                this.cache.put(key, existsFlg);
            }
            if (ZYPConstant.FLG_ON_Y.equals(existsFlg)) {
                return true;
            }
        }
        return false;
    }
    private String replaceSystemLineSeparator(String val) {
        if (!hasValue(val)) return val;
        
        String ret = val.replaceAll("\r\n", "\r");
        if (ret.indexOf("\n") >= 0) {
            ret = ret.replaceAll("\n", "\r");
        }
        ret = ret.replaceAll("\r", ENTER);
        
        return ret;
    }

    @SuppressWarnings("unchecked")
    private String getInvPrtBrCd(String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd, String slsRepTocCd, String sysSrcCd, String dsInvTpCd) {
        String invPrtBrCd = "";
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        //(1).Get from Run Book
        //Run Book from DS Invoice Type Code
        if (runBookDsInvTpCdArray != null && runBookDsInvTpCdArray.length > 0) {
            for (String runBookDsInvTpCd : runBookDsInvTpCdArray) {
                if (hasValue(dsInvTpCd) && hasValue(runBookDsInvTpCd) && dsInvTpCd.equals(runBookDsInvTpCd)) {
                    invPrtBrCd = INV_PRT_BR.RUN_BOOK;
                    break;
                }
            }
        }
        if (hasValue(invPrtBrCd)) {
            return invPrtBrCd;
        }
        
        //Run Book from System Source Code
        if (runBookSysSrcCdArray != null && runBookSysSrcCdArray.length > 0) {
            for (String runBookSysSrcCd : runBookSysSrcCdArray) {
                if (hasValue(sysSrcCd) && hasValue(runBookSysSrcCd) && sysSrcCd.equals(runBookSysSrcCd)) {
                    invPrtBrCd = INV_PRT_BR.RUN_BOOK;
                    break;
                }
            }
        }
        if (hasValue(invPrtBrCd)) {
            return invPrtBrCd;
        }
        
        //(2).Get from ORD_CATG_CTX_TP_CD = EMSD_PRINT_BR
        if (hasValue(dsOrdCatgCd) && hasValue(dsOrdTpCd)) {
            ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("ordCatgCtxTp", "EMSD_PRINT_BR");
            ssmParam.put("dsOrdCatgCd", dsOrdCatgCd);
            ssmParam.put("dsOrdTpCd", dsOrdTpCd);
            ssmParam.put("dsOrdRsnCd", dsOrdRsnCd);
            Map<String, Object> ordCatgCtxMap = (Map<String, Object>) ssmBatchClient.queryObject("getOrdCatgCtxForInvPrtBr", ssmParam);
            if (ordCatgCtxMap != null) {
                invPrtBrCd = (String) ordCatgCtxMap.get("FIRST_BIZ_CTX_ATTRB_TXT");
            }
            if (hasValue(invPrtBrCd)) {
                return invPrtBrCd;
            }
        }
        
        //(3).Get from COA_BR table
        ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsRepTocCd", slsRepTocCd);
        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getCoaBrPrintCd", ssmParam);
        if (map != null) {
            invPrtBrCd = map.get("INV_PRT_BR_CD") == null ? "" : (String) map.get("INV_PRT_BR_CD");
        }
        
        return invPrtBrCd;
    }
    // START 2018/08/20 M.Naito [QC#13309, ADD]
    private boolean checkTempEttl(String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        Integer resCnt = (Integer) ssmBatchClient.queryObject("isTempEttl", ssmParam);
        if (resCnt.intValue() > 0) {
            return true;
        }
        return false;
    }
    // END 2018/08/20 M.Naito [QC#13309, ADD]

    // START 2018/11/14 K.Fujimoto [QC#28627, ADD]
    private boolean checkContrLinkNum(String invNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        Integer resCnt = (Integer) ssmBatchClient.queryObject("isContrLinkNum", ssmParam);
        if (resCnt.intValue() > 0) {
            return true;
        }
        return false;
    }
    // END 2018/11/14 K.Fujimoto [QC#28627, ADD]

    //QC#29672 2019/01/29 Add Start
    private String convString(String val){
        if (S21StringUtil.isEmpty(val)){
            return "";
        } else {
            return val;
        }
    }
    //QC#29672 2019/01/29 Add End

    //QC#29672 2019/01/29 Add Start
    private String convString(BigDecimal val){
        
        if (val == null){
            return "";
        } else {
            return val.toPlainString();
        }
    }
    //QC#29672 2019/01/29 Add End
    // 2019/02/07 QC#29371 K.Fujimoto Mod Start
    private BigDecimal getInvPrtFleetLocPk(String invNum, String shipToCustCd, BigDecimal invPrtFleetSubTotPk){
        if (invNum == null || shipToCustCd == null) {
            return null;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        ssmParam.put("shipToCustCd", shipToCustCd);
        ssmParam.put("invPrtFleetSubTotPk", invPrtFleetSubTotPk);
        BigDecimal invPrtFleetLocPk = (BigDecimal) ssmBatchClient.queryObject("getInvPrtFleetLocPk", ssmParam);
        if (invPrtFleetLocPk != null) {
            return invPrtFleetLocPk;
        }
        return null;
    }
    
    private boolean existsInvPrtFleetChrgDtl(String invNum, BigDecimal svcMachMstrPk){
        if (invNum == null || svcMachMstrPk == null) {
            return false;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        BigDecimal isInvPrtFleetChrgDtlCnt = (BigDecimal) ssmBatchClient.queryObject("isInvPrtFleetChrgDtl", ssmParam);
        if (isInvPrtFleetChrgDtlCnt.intValue() > 0) {
            return true;
        }
        return false;
    }
    // 2019/02/07 QC#29371 K.Fujimoto Mod End

    // 2019/06/13 QC#50817 Add Start
    // START 2022/08/03 [QC#60129, MOD]
//    private boolean isExistsPmtCC(BigDecimal conslBillPk){
    private List<Map<String, String>> getPmtCCInfo(BigDecimal conslBillPk){
//        boolean ret = false;
    // END   2022/08/03 [QC#60129, MOD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("conslBillPk", conslBillPk);
        // START 2022/08/03 [QC#60129, MOD]
//        BigDecimal cnt = (BigDecimal) ssmBatchClient.queryObject("getPmtCCCount", ssmParam);
//
//        if (cnt != null) {
//            if (BigDecimal.ONE.compareTo(cnt) <= 0) {
//                ret = true;
//            }
//        }
//        return ret;
        return (List<Map<String, String>>) ssmBatchClient.queryObjectList("getPmtCCInfo", ssmParam);
        // END   2022/08/03 [QC#60129, MOD]
    }
    // 2019/06/13 QC#50817 Add End

    // 2019/10/10 QC#53888 Add Start
    private void truncateInsertWrkTbl() throws SQLException {
        S21StopWatch sw = new S21StopWatch();

        S21SsmBatchClientCustom ssmBatchClientCustom = new S21SsmBatchClientCustom(this.getClass());

        // truncate
        Map<String, String> truncateParams= new HashMap<String, String>();
        sw.start();
        ssmBatchClientCustom.delete("truncateWrkTbl", truncateParams);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:truncateWrkTbl]End [%s]", sw.getElapsedMilliSec()));

        // Insert
        sw.start();
        try {
            insertWrkTbl();
        } catch (SQLException e) {
            rollback();
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:insertInvPrtSvcInvLineWrk]Error [%s]", sw.getElapsedMilliSec()));
            throw e;
        }
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:insertInvPrtSvcInvLineWrk]End [%s]", sw.getElapsedMilliSec()));

        commit();

        // Update Transaction
        S21BatchTransactionQuery queryComponet = new S21BatchTransactionQuery();
        String sql = String.format("DBMS_STATS.GATHER_TABLE_STATS ('%s','%s', CASCADE => TRUE); ", this.dbSchema, WRK_TBL_NM);

        StringBuilder cmd = new StringBuilder();
        cmd.append("DECLARE ");
        cmd.append("BEGIN ");
        cmd.append(sql);
        cmd.append("END;");

        int num = queryComponet.executeUpdate(cmd.toString());

        if (num < 0) {
            S21InfoLogOutput.println(String.format("Error happened \"%s\"", sql));
        } else {
            S21InfoLogOutput.println(String.format("Statistics update finished \"%s\"", sql));
        }
    }
    // 2019/10/10 QC#53888 Add End

    // 2019/10/10 QC#53888 Add Start
    private void insertWrkTbl() throws SQLException {
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        Map<String, Serializable> param = new HashMap<String, Serializable>();

        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("slsDt", this.slsDt);
        param.put("procMode", this.procMode);
        param.put("formatKey1", "1");
        param.put("formatKey2", "2");
        param.put("invPrintStsCd1", "1");
        param.put("invPrintStsCd3", "3");  //Exclude Consolidated Credit Memo
        param.put("dsTaxPrntTp10", DS_TAX_PRNT_TP.TOTAL_TAX_ONLY);
        param.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
        param.put("dsContrCatgAgg", DS_CONTR_CATG.AGGREGATE); // QC#29371
        param.put("svcInvSrcTpC", SVC_INV_SRC_TP.CONTRACT);
        param.put("formatTpFL", INV_PRT_DTL_FMT_TP.FLEET_CONTRACT);
        param.put("formatTpAG", INV_PRT_DTL_FMT_TP.AGGREGATE_CONTRACT); // QC#29371
        param.put("formatTpMA", INV_PRT_DTL_FMT_TP.MAINTENANCE);
        param.put("formatTpPL", INV_PRT_DTL_FMT_TP.PARTS_AND_LABOR);
        param.put("formatTpSS", INV_PRT_DTL_FMT_TP.SALE_AND_SUPPLY);
        param.put("conslPrintStsN", CONSL_PRINT_STS.NOT_PRINTED);
        param.put("sysSrcCdNFC", SYS_SRC.S21_ACCOUNTING_AR);
        param.put("crCardTrxTpCpo", CR_CARD_TRX_TP.CPO);
        param.put("amtFormat", FM_AMT);
        param.put("svcInvChrgTpCdMC", SVC_INV_CHRG_TP.METER_CHARGE);
        param.put("svcInvChrgTpCdAC", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        param.put("svcInvChrgTpCdBC", SVC_INV_CHRG_TP.BASE_CHARGE);
        // 2024/01/11 QC#61468 Add Start
        param.put("siChrgTpCdFC", SVC_INV_CHRG_TP.FREIGHT_CHARGE);
        // 2024/01/11 QC#61468 Add End
        param.put("fsrInvTpCdArray", fsrInvTpCdArray);
        param.put("conslStsCd10", CONSL_STS.ACCEPTED);
        param.put("dsTaxPrntTp10", DS_TAX_PRNT_TP.TOTAL_TAX_ONLY);
        param.put("procMode", this.procMode);

        S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();

        ssmParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        PreparedStatement stmt = null;

        try {
            stmt = ssmLLClient.createPreparedStatement("insertInvPrtSvcInvLineWrk", param, ssmParam);
            stmt.execute();
        } finally {
            if (stmt != null){
                stmt.close();
            }
        }
    }
    // 2019/10/10 QC#53888 Add End
    // 2019/11/01 QC#53873 Add Start
    private boolean isCreditRebill(INV_PRT_CTRLTMsg invPrtCtrlTmsg) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("invNum", invPrtCtrlTmsg.invNum.getValue());
        param.put("excludeSysSrcCd", SYS_SRC.S21_ACCOUNTING_AR);
        BigDecimal cnt = (BigDecimal) ssmBatchClient.queryObject("isCreditRebill", param);
        if (cnt.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }

    private boolean isOrigCons(String origInvNum) {
        INV_PRT_HDRTMsg tMsg = new INV_PRT_HDRTMsg();
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("invBillNum01", origInvNum);
        tMsg.setSQLID("001");
        INV_PRT_HDRTMsgArray tMsgArray = (INV_PRT_HDRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.getValidCount() > 0 && ZYPConstant.FLG_ON_Y.equals(tMsgArray.no(0).conslBillFlg.getValue())) {
            return true;
        }
        return false;
    }
    // 2019/11/01 QC#53873 Add End

    // QC#58231
    private Map<String, BigDecimal> getNumConstMap() {

        Map<String, BigDecimal> constMap = new HashMap<String, BigDecimal>();
        BigDecimal multiCalcCnt = ZYPCodeDataUtil.getNumConstValue("EQUIP_TOT_LIMIT_AMT", glblCmpyCd);
        BigDecimal invPrtTotLmt = ZYPCodeDataUtil.getNumConstValue("CONTRACT_TOT_LIMIT_AMT", glblCmpyCd);
        BigDecimal bllgPrvwAvalMaxPrc = ZYPCodeDataUtil.getNumConstValue("SW_ITEM_LINE_LIMIT_AMT", glblCmpyCd);
        BigDecimal defSlsRepCrPct = ZYPCodeDataUtil.getNumConstValue("CONTRACT_LINE_LIMIT_AMT", glblCmpyCd);

        constMap.put("EQUIP_TOT_LIMIT_AMT", multiCalcCnt);
        constMap.put("CONTRACT_TOT_LIMIT_AMT", invPrtTotLmt);
        constMap.put("SW_ITEM_LINE_LIMIT_AMT", bllgPrvwAvalMaxPrc);
        constMap.put("CONTRACT_LINE_LIMIT_AMT", defSlsRepCrPct);

        return constMap;
    }
    // QC#58231
    private void chkReviewTotAmt(INV_PRT_CTRLTMsg tMsg, String chkNm, BigDecimal totAmt) throws SQLException {

        BigDecimal chkTotAmt = this.limitAmtMap.get(chkNm);
        if (!ZYPCommonFunc.hasValue(chkTotAmt)) {
            return;
        }
        if (ZYPCommonFunc.hasValue(totAmt) &&  chkTotAmt.compareTo(totAmt) <= 0) {
            ZYPEZDItemValueSetter.setValue(tMsg.invPrtBatTpCd, INV_PRT_BAT_TP.NEEDS_REVIEW);
        }
    }
    // QC#58231
    private void chkReviewLineAmt(INV_PRT_CTRLTMsg invPrtCtrlTmsg, String chkNm) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("invNum", invPrtCtrlTmsg.invNum.getValue());
        param.put("coaProjCds", this.coaProjCds);
        List<BigDecimal> amtLists = (List<BigDecimal>) ssmBatchClient.queryObjectList("getLineAmt", param);

        if (amtLists == null || amtLists.isEmpty()) {
            return;
        }

        BigDecimal chkTotAmt = this.limitAmtMap.get(chkNm);
        if (!ZYPCommonFunc.hasValue(chkTotAmt)) {
            return;
        }

        for (BigDecimal amt : amtLists) {
            if (ZYPCommonFunc.hasValue(amt) && chkTotAmt.compareTo(amt) <= 0) {
                ZYPEZDItemValueSetter.setValue(invPrtCtrlTmsg.invPrtBatTpCd, INV_PRT_BAT_TP.NEEDS_REVIEW);
                break;
            }
        }
    }

    // START 2021/04/23 L.Mandanas [QC#58350, ADD]
    /**
     * Computes the Rollover Used.
     * @param rollOvrCnt BigDecimal
     * @param actualUsage BigDecimal
     * @param allowance BigDecimal
     * @return int BigDecimal
     */
    private BigDecimal computeRolloverUsed(final BigDecimal rollOvrCnt,
                                        final BigDecimal actualUsage,
                                        final BigDecimal allowance) {
        BigDecimal rollOvrUsed = BigDecimal.ZERO;
        if (actualUsage.compareTo(allowance.add(rollOvrCnt)) <= 0) {
            if (actualUsage.compareTo(allowance) <= 0) {
                rollOvrUsed = BigDecimal.ZERO;
            } else {
                rollOvrUsed = actualUsage.subtract(allowance);
            }
        } else {
            rollOvrUsed = rollOvrCnt;
        }
        return rollOvrUsed;
    }

    /**
     * Computes the Billable Usage.
     * @param rollAllwnc BigDecimal
     * @param actualUsage BigDecimal
     * @param allowance BigDecimal
     * @return BigDecimal rollOvrUsed
     */
    private BigDecimal computeBillableUsage(final BigDecimal rollAllwnc,
                                         final BigDecimal actualUsage,
                                         final BigDecimal allowance) {
        BigDecimal billableUsage = (actualUsage.subtract(allowance))
                                        .subtract(rollAllwnc);
        if (billableUsage.compareTo(BigDecimal.ZERO) < 0) {
            billableUsage = BigDecimal.ZERO;
        }
        return billableUsage;
    }

    /**
     * get current Rollover Count.
     * @param contrBllgSchdPk BigDecimal
     * @return List<Map<String, Object>> queryObjectList
     */
    @SuppressWarnings("unchecked")
    private List < Map < String, Object > > getCurrentRolloverCount(
                                            final BigDecimal contrBllgSchdPk) {
        Map < String, Object > ssmParam = new HashMap < String, Object >();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("contrBllgSchdPk", contrBllgSchdPk);
        // START 2021/06/04 L.Mandanas [QC#58350, ADD]
        ssmParam.put("dsBllgSchdTp", DS_BLLG_SCHD_TP.REGULAR);
        // END 2021/06/04 L.Mandanas [QC#58350, ADD]
        // START 2022/09/01 L.Mandanas [QC#58350, ADD]
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        // END 2022/09/01 L.Mandanas [QC#58350, ADD]
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getCurrentRolloverCount", ssmParam);
    }

    /**
     * get next Rollover Count for non-fleet.
     * @param contrBllgPk BigDecimal
     * @param svcContrMtrBllgPk BigDecimal
     * @param invNum String
     * @param dsContrPk BigDecimal
     * @return List<Map<String, Object>> queryObjectList
     */
    @SuppressWarnings("unchecked")
    private List < Map < String, Object > > getNonFleetNextRolloverCount(
                                            final BigDecimal contrBllgPk,
                                            final BigDecimal svcContrMtrBllgPk,
                                            final String invNum,
                                            final BigDecimal dsContrPk) {
        // START 2022/06/20 [QC#58350, MOD]
        if (!ZYPCommonFunc.hasValue(contrBllgPk)) {
            return null;
        }
        // END   2022/06/20 [QC#58350, MOD]
        Map < String, Object > ssmParam = new HashMap < String, Object >();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("contrBllgPk", contrBllgPk);
        ssmParam.put("svcContrMtrBllgPk", svcContrMtrBllgPk);
        ssmParam.put("invNum", invNum);
        ssmParam.put("dsContrPk", dsContrPk);
        // START 2021/09/23 L.Mandanas [QC#58350, ADD]
        ssmParam.put("invTpCd", INV_TP.CREDIT_MEMO);
        // END 2021/09/23 L.Mandanas [QC#58350, ADD]
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getNonFleetNextRolloverCount", ssmParam);
    }

    /**
     * get next Rollover Remaining for aggregate.
     * @param invNum String
     * @param bllgMtrLbCd String
     * @return List<Map<String, Object>> queryObjectList
     */
    @SuppressWarnings("unchecked")
    private List < Map < String, Object > > getAggregateRolloverRemaining(
                                              final String invNum,
                                              final String bllgMtrLbCd) {
        Map < String, Object > ssmParam = new HashMap < String, Object >();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("invNum", invNum);
        ssmParam.put("bllgMtrLbCd", bllgMtrLbCd);
        // START 2021/09/23 L.Mandanas [QC#58350, ADD]
        ssmParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        ssmParam.put("invTpCd", INV_TP.CREDIT_MEMO);
        // END 2021/09/23 L.Mandanas [QC#58350, ADD]
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getAggRolloverRemaining", ssmParam);
    }
    // END 2021/04/23 L.Mandanas [QC#58350, ADD]
    // START 2022/07/20 R.Onozuka [QC#59669, ADD]
    // START 2022/09/23 R.Onozuka [QC#60624, MOD]
//    private String genOcrScanNum(String lineBizTp, String billToCust, String conslBillNum, String invNum, BigDecimal invAmt){
    private String genOcrScanNum(String lineBizTp, String billToCust, String conslBillNum, String invNum, BigDecimal invAmt, String invTpCd){
    // END 2022/09/23 R.Onozuka [QC#60624, MOD]

        String ocrScanNum = null;

        // set 1st Field of OCR Scan Line (LOCK BOX Account Number)
        String firstF = null;

        LINE_BIZ_TPTMsg inLineBizLineTMsg = new LINE_BIZ_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inLineBizLineTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inLineBizLineTMsg.lineBizTpCd, lineBizTp);
        LINE_BIZ_TPTMsg outLineBizLineTMsg = (LINE_BIZ_TPTMsg) S21CacheTBLAccessor.findByKey(inLineBizLineTMsg);
        if(!(outLineBizLineTMsg == null)){
            firstF = outLineBizLineTMsg.lockBoxAcctNum.getValue();
        }

        // set 2nd Field of OCR Scan Line (Bill To Cust)
        String secondF = null;
        if(hasValue(billToCust)){
            if (billToCust.length() <= 10) {
                secondF = String.format("%10s", billToCust).replace(" ", "0");
            }
        }

        // set 3rd Field of OCR Scan Line (Invoice Number or Consl Bill Number)
        String thirdF = null;
        if (hasValue(conslBillNum)) {
            if (conslBillNum.length() <= 12) {
                thirdF = String.format("%12s", conslBillNum).replace(" ", "0");
            }
        } else {
            if (hasValue(invNum) && !invNum.matches("^[a-zA-Z]*$") && invNum.length() <= 12) {
                thirdF = String.format("%12s", invNum).replace(" ", "0");
            }
        }

        // set 4th Field of OCR Scan Line (Invoice Amount)
        String fourthF = null;
        // START 2022/09/26 R.Onozuka [QC#60624, ADD]
        if(INV_TP.CREDIT_MEMO.equals(invTpCd)){
            invAmt = BigDecimal.ZERO;
        }
        // END 2022/09/26 R.Onozuka [QC#60624, ADD]
        if(hasValue(invAmt)){
            String tempInvAmt = (invAmt.abs().toPlainString().replace(".", ""));
            for(int i = 0; i < (2 - invAmt.scale()) ; i++){
                tempInvAmt = tempInvAmt + "0";
            }
            if (tempInvAmt.length() <= 14) {
                fourthF = String.format("%14s", tempInvAmt).replace(" ", "0");
            } else {
                return ocrScanNum;
            }
        }

        // set 5th Field of OCR Scan Line (Check Digit)
        // set temporary OCR Scan Line for calculation of Check Digit
        String tempOcrScanNumS = null;
        String fifthF = null;
        if (hasValue(firstF) && hasValue(secondF) && hasValue(thirdF) && hasValue(fourthF)) {
            tempOcrScanNumS = firstF + secondF + thirdF + fourthF;
            fifthF = calcCheckDigitOfOcrScanNum(tempOcrScanNumS);
        }

        // generate OCR Scan Line
        if (hasValue(firstF) && hasValue(secondF) && hasValue(thirdF) && hasValue(fourthF) && hasValue(fifthF)) {
            ocrScanNum = firstF + secondF + thirdF + fourthF + fifthF;
        }

        return ocrScanNum;
    }

    private String calcCheckDigitOfOcrScanNum(String tempOcrScanNumS) {
        String checkDigit = null;

        // convert string to list
        List<Integer> ocrScanNumEleList = new ArrayList<Integer>();
        if (hasValue(tempOcrScanNumS)) {
            for (int i = 0; i < tempOcrScanNumS.length(); i++) {
                ocrScanNumEleList.add(Integer.parseInt(String.valueOf(tempOcrScanNumS.charAt(i))));
            }
        }

        // calcurate Check Digit
        int tempResult = 0;
        int calcTotal = 0;
        int size = ocrScanNumEleList.size();
        for (int i = 0; i < size; i++) {
            tempResult = ocrScanNumEleList.get(i);
            if (i % 2 == 0) {
                tempResult = tempResult * 2;
            }
            if (String.valueOf(tempResult).length() > 1) {
                calcTotal = calcTotal + ((tempResult - (tempResult % 10)) / 10) + (tempResult % 10);
            } else {
                calcTotal = calcTotal + tempResult;
            }
        }
        if (calcTotal % 10 == 0) {
            tempResult = 0;
        } else {
            tempResult = 10 - (calcTotal % 10);
        }

        checkDigit = Integer.valueOf(tempResult).toString();

        return checkDigit;
    }
    // END 2022/07/20 R.Onozuka [QC#59669, ADD]
    
    // START 2023/12/30 R.Takau [QC#61584,ADD]
    private String[] getVarcharConst (String strVar, String strDefault) {
        String strConst = ZYPCodeDataUtil.getVarCharConstValue(strVar, this.glblCmpyCd);
        String[] strArray = strDefault.split(",");
        if (hasValue(strConst)) {
            strArray = strConst.split(",");
        }
        return strArray;
    }
    // END 2023/12/30 R.Takau [QC#61584,ADD]
}