/**
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * <pre>
 * Validation Process Manager API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2009   Fujitsu         K.Kato          Create          N/A
 * 09/08/2009   Fujitsu         K.Kato          Update          67->71
 * 09/17/2009   Fujitsu         K.Kato          Update          146
 * 09/23/2009   Fujitsu         K.Kato          Update          209,221
 * 09/28/2009   Fujitsu         K.Kato          Update          266
 * 09/30/2009   Fujitsu         K.Kato          Update          299
 * 10/01/2009   Fujitsu         K.Kato          Update          321
 * 10/02/2009   Fujitsu         K.Kato          Update          154,328
 * 10/02/2009   Fujitsu         K.Kato          Update          395
 * 10/06/2009   Fujitsu         K.Kato          Update          459
 * 10/08/2009   Fujitsu         K.Kato          Update          524,527
 * 10/08/2009   Fujitsu         K.Kato          Update          564
 * 10/12/2009   Fujitsu         K.Kato          Update          652
 * 10/26/2009   Fujitsu         S.Sugino        Update
 * 11/18/2009   Fujitsu         K.Kato          Update          1868
 * 11/23/2009   Fujitsu         K.Kato          Update          1878 1964 1977
 * 12/18/2009   Fujitsu         K.Tajima        Update          N/A (refactoring to get high-performance)
 * 01/07/2010   Fujitsu         K.Tajima        Update          N/A (debug and refactoring.)
 * 01/25/2010   Fujitsu         K.Kato          Update          3153
 * 02/03/2010   Fujitsu         K.Kato          Update          3504
 * 02/08/2010   Fujitsu         T.Nagase        Update          N/A (Dealer profile)
 * 03/22/2010   Fujitsu         M.Nakamura      Update          3399,3450
 * 03/24/2010   Fujitsu         S.Sugino        Update          3933
 * 04/15/2010   Fujitsu         A.Suda          Update          5570
 * 04/26/2010   Fujitsu         A.Suda          Update          N/A (Export Hold Add)
 * 04/30/2010   Fujitsu         A.Suda          Update          5359
 * 05/17/2010   Fujitsu         R.Watanabe      Update          6217
 * 05/18/2010   Fujitsu         A.Suda          Update          5359 
 * 05/18/2010   Fujitsu         A.Suda          Update          6498 
 * 05/25/2010   Fujitsu         A.Suda          Update          6538
 * 06/18/2010   Fujitsu         A.Suda          Update          7100
 * 09/07/2010   Fujitsu         A.Suda          Update          412(All2)
 * 09/22/2010   Fujitsu         S.Yamamoto      Update          29(All2)
 * 10/03/2010   Fujitsu         K.Tajima        Update          Performance tuning : execOrderValidation
 * 10/06/2010   Fujitsu         K.Tajima        Update          Performance tuning : execRecalculation
 * 11/10/2010   Fujitsu         K.Tajima        Update          Def# 645 [Performance] NWAL0010 Order Entry (case of Auto Allocation)
 * 11/18/2010   CSAI            T.Gotoda        Update          694(All2)
 * 11/19/2010   Fujitsu         S.Yamamoto      Update          540(All2)
 * 11/29/2010   Fujitsu         K.Tajima        Update          Def# 645 [Performance] NWAL0010 Order Entry (case of Auto Allocation)
 * 12/13/2010   CSAI            T.Gotoda        Update          782(All2)
 * 12/29/2010   Fujitsu         S.Takami        Update          1047(All2) (Top Stop Hold CPO_DTL)
 * 01/13/2011   Fujitsu         S.Yamamoto      Update          1110(All2)
 * 01/18/2011   Fujitsu         K.Tajima        Update          1123 [Performance] NWAL0050 : Hold Release (Submit)
 * 02/06/2011   Fujitsu         K.Tajima        Update          Prod (1339)
 * 02/13/2011   Fujitsu         S.Yamamoto      Update          Prod (1423)
 * 02/16/2011   Fujitsu         K.Tajima        Update          1594(Prod)
 * 03/17/2011   Fujitsu         S.Yamamoto      Update          1910(Prod)
 * 04/06/2011   Fujitsu         S.Yamamoto      Update          1481(Prod)
 * 04/19/2011   Fujitsu         S.Yamamoto      Update          2095(Prod)
 * 04/20/2011   Fujitsu         S.Yamamoto      Update          2025(Prod)
 * 08/11/2011   CSAI            A.Katayama      Update          ITG#357339
 * 09/19/2011   Fujitsu         S.Yamamoto      Update          ITG#351571
 * 09/26/2011   Fujitsu         S.Yamamoto      Update          ITG#351571 update
 * 01/03/2012   Fujitsu         S.Yamamoto      Update          ITG#372937 Create new mode [Credit Validation]
 * 07/20/2012   Fujitsu         S.Iidaka        Update          WDS#110 Delivery Block
 * 09/20/2012   Fujitsu         Y.Mori          Update          WDS#113 Credit Card Authorization (Paymentech)
 * 10/25/2012   Fujitsu         S.Iidaka        Update          WDS#110 Delivery Block
 * 10/25/2012   Fujitsu         S.Iidaka        Update          WDS#110 Wating-OSI Install(Billing Block)
 * 11/14/2012   Fujitsu         A.Suda          Update          WDS#104 105 Pricing
 * 12/25/2012   Fujitsu         A.Suda          Update          WDS Defect#78
 * 01/17/2013   Fujitsu         S.Iidaka        Update          WDS Defect#201
 * 01/25/2012   Fujitsu         A.Suda          Update          WDS Defect#430#404
 * 02/05/2013   Fujitsu         S.Iidaka        Update          WDS Defect#405
 * 02/18/2013   Fujitsu         S.Iidaka        Update          WDS Defect#704
 * 02/20/2013   Fujitsu         S.Iidaka        Update          WDS Defect#713
 * 05/21/2013   Fujitsu         S.Yamamoto      Update          #R-OM004
 * 05/29/2013   Fujitsu         S.Yamamoto      Update          #R-MS001
 * 07/01/2013   Fujitsu         S.Yoshida       Update          DS-Solution#R-OM021
 * 09/05/2013   Fujitsu         A.Suda          Update          WDS Defect#1242#1654
 * 09/30/2013   Fujitsu         A.Suda          Update          WDS Defect#2548#2550
 * 11/12/2013   Fujitsu         M.Fuji          Update          WDS Defect#3025
 * 11/14/2013   Fujitsu         M.Fuji          Update          WDS Defect#3025
 * 01/06/2014   Fujitsu         M.Fuji          Update          WDS Defect#3223
 * 2015/09/24   Fujitsu         M.Yamada        Update          CSA
 * 2015/12/03   Fujitsu         S.Takami        Update          S21_NA#1407
 * 2016/01/07   Fujitsu         S.Takami        Update          S21_NA#2588
 * 2016/02/22   Fujitsu         T.Ishii         Update          S21_NA#2279
 * 2016/02/24   Fujitsu         T.Ishii         Update          S21_NA#2709
 * 2016/07/14   Fujitsu         S.Iidaka        Update          S21_NA#11546
 * 2016/07/27   Fujitsu         T.Ishii         Update          S21_NA#5887
 * 2016/08/04   Fujitsu         T.Yoshida       Update          S21_NA#12895
 * 2016/08/10   SRAA            K.Aratani       Update          S21_NA#13323
 * 2016/09/12   Fujitsu         T.Yoshida       Update          S21_NA#12234
 * 2016/09/20   Fujitsu         Y.Kanefusa      Update          S21_NA#11306
 * 2016/09/26   Fujitsu         H.Nagashima     Update          S21_NA#7180
 * 2016/10/06   Fujitsu         H.Nagashima     Update          S21_NA#14970
 * 2016/10/06   Fujitsu         T.Yoshida       Update          S21_NA#14973
 * 2016/11/15   Fujitsu         H.Nagashima     Update          S21_NA#15745
 * 2016/11/15   Fujitsu         Y.Kanefusa      Update          S21_NA#15960
 * 2017/01/04   Fujitsu         T.Yoshida       Update          S21_NA#14512
 * 2017/03/29   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.1
 * 2017/04/28   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2
 * 2017/05/08   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 RS#7255(Display Status)
 * 2017/05/16   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 RS#7319
 * 2017/06/09   Fujitsu         S.Takami        Update          S21_NA#14685
 * 2017/09/26   Fujitsu         S.Takami        Update          S21_NA#21279
 * 2017/12/28   Fujitsu         M.Ohno          Update          S21_NA#22737
 * 2018/01/18   Fujitsu         M.Ohno          Update          S21_NA#22737-2
 * 2018/01/18   Fujitsu         M.Ohno          Update          S21_NA#23662
 * 2018/07/02   Fujitsu         K.Ishizuka      Update          S21_NA#24189
 * 2018/07/06   Fujitsu         Y.Matsui        Update          S21_NA#27079
 * 2018/08/27   Fujitsu         Y.Matsui        Update          S21_NA#27692
 * 2018/11/05   Fujitsu         K.Kato          Update          S21_NA#29015
 * 2018/12/13   Fujitsu         T.Murai         Update          S21_NA#29488
 * 2019/01/09   Fujitsu         T.Murai         Update          S21_NA#29879
 * 2019/04/10   Fujitsu         Mz.Takahashi    Update          S21_NA#31053
 * 2019/05/07   Fujitsu         S.Kosaka        Update          S21_NA#50073
 * 2019/08/30   Fujitsu         R.Nakamura      Update          S21_NA#53016
 * 2023/03/23   CITS            A.Cullano       Update          QC#61328
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 * 2023/06/26   CITS            F.Komaki        Update          QC#61556
 * </pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC004001;

//import static com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ContractInfo.chkContractInfo;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.count;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByCondition;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByConditionForUpdate;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.insert;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.logicalRemove;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.update;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.updateByPartialValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.updateSelectionField;
import static com.canon.cusa.s21.framework.common.S21StringUtil.isEquals;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKey;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKeyForUpdateAPI;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.logicalRemoveByPartialValue;
import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static parts.dbcommon.EZDTBLAccessor.RTNCD_NORMAL;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.CUST_CR_PRFLTMsgArray;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_CONFIGTMsgArray;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsgArray;
import business.db.HLDTMsg;
import business.db.HLDTMsgArray;
import business.db.HLD_RSNTMsg;
import business.db.MDSETMsg;
import business.db.S21_ORGTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NMZC600001PMsg;
import business.parts.NMZC600001_xxOutPrmListPMsg;
import business.parts.NWXC005001PMsg;
import business.parts.NWXC005001_shpgPlnListPMsg;
import business.parts.NWZC004001PMsg;
import business.parts.NWZC004001_APMsg;
import business.parts.NWZC004001_APMsgArray;
import business.parts.NWZC033001PMsg;
import business.parts.NWZC044001PMsg;
import business.parts.NWZC188001PMsg;
import business.parts.NWZC218001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC600001.NMZC600001;
import com.canon.cusa.s21.api.NSZ.NSZC063001.NSZC063001;
import com.canon.cusa.s21.api.NSZ.NSZC064001.NSZC064001;
import com.canon.cusa.s21.api.NSZ.NSZC065001.NSZC065001;
import com.canon.cusa.s21.api.NSZ.NSZC066001.NSZC066001;
import com.canon.cusa.s21.api.NWZ.NWZC004001.cache.DataCacheForValidation;
import com.canon.cusa.s21.api.NWZ.NWZC011001.NWZC011001;
import com.canon.cusa.s21.api.NWZ.NWZC019001.NWZC019001;
import com.canon.cusa.s21.api.NWZ.NWZC033001.NWZC033001;
import com.canon.cusa.s21.api.NWZ.NWZC044001.NWZC044001;
import com.canon.cusa.s21.api.NWZ.NWZC158001.NWZC158001;
import com.canon.cusa.s21.api.NWZ.NWZC159001.NWZC159001;
import com.canon.cusa.s21.api.NWZ.NWZC173001.NWZC173001;
import com.canon.cusa.s21.api.NWZ.NWZC174001.NWZC174001;
import com.canon.cusa.s21.api.NWZ.NWZC175001.NWZC175001;
import com.canon.cusa.s21.api.NWZ.NWZC176001.NWZC176001;
import com.canon.cusa.s21.api.NWZ.NWZC178001.NWZC178001;
import com.canon.cusa.s21.api.NWZ.NWZC179001.NWZC179001;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.api.NWZ.NWZC193001.NWZC193001;
import com.canon.cusa.s21.api.NWZ.NWZC195001.NWZC195001;
import com.canon.cusa.s21.api.NWZ.NWZC218001.NWZC218001;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXCustCrPrflTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXHldRsnTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXHldRsnTMsgThreadLocalCache.HldRsnTMsgCacheKey;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_REL_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmListResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;

public class NWZC004001 extends S21ApiCommonBase {

    // START ADD M.FUJI [Defect#3223]
    /**
     * Local Hash
     */
    private final HashMap<String, BigDecimal> slsHldCntMap = new HashMap<String, BigDecimal>();
    // END ADD M.FUJI [Defect#3223]

    /** Validation Type : Order Validation */
    public static final String VAL_TP_OV = "1";

    /** Validation Type : Soft Allocation */
    public static final String VAL_TP_SA = "2";

    /** Validation Type : Shipping Validation */
    public static final String VAL_TP_SV = "3";

    /** Validation Type : Recalculation */
    public static final String VAL_TP_RC = "6";

    /** Validation Type : Credit Validation */
    //  ********** [# 372937] - START
    //    private static final String VAL_TP_CV = "7";
    public static final String VAL_TP_CV = "7";
    //  ********** [# 372937] - END

    /** Error Message */
    public static final String NWZM0001E = "NWZM0001E";

    /** Error Message */
    public static final String NWZM0445E = "NWZM0445E";

    /** Error Message */
    public static final String NWZM0245E = "NWZM0245E";

    /** Error Message */
    public static final String NWZM0002E = "NWZM0002E";

    /** Error Message */
    public static final String NWZM0003E = "NWZM0003E";

    /** Error Message */
    public static final String NWZM0004E = "NWZM0004E";

    /** Error Message */
    public static final String NWZM0250E = "NWZM0250E";

    /** Error Message */
    public static final String NWZM0073E = "NWZM0073E";

    /** Error Message */
    public static final String NWZM0074E = "NWZM0074E";

    /** Error Message */
    public static final String NWZM0075E = "NWZM0075E";

    /** Error Message */
    public static final String NWZM0077E = "NWZM0077E";

    /** Error Message */
    public static final String NWZM0081E = "NWZM0081E";

    /** Error Message */
    public static final String NWZM0078E = "NWZM0078E";

    /** Error Message */
    public static final String NWZM0080E = "NWZM0080E";

    /** Error Message */
    public static final String NWZM0010E = "NWZM0010E";

    /** Error Message */
    public static final String NWZM0187E = "NWZM0187E";

    /** Error Message */
    public static final String NWZM0148E = "NWZM0148E";

    /** Error Message */
    public static final String NZZM0003E = "NZZM0003E";

    //START 2013/07/01 S.Yoshida [ADD for R-OM021]
    /** "PMT_TERM_CR_CARD" doesn't exist in the VAR_CHAR_CONST. */
    public static final String NWZM1277E = "NWZM1277E";
    //END 2013/07/01 S.Yoshida [ADD for R-OM021]

    /** Check Point : Order Validation */
    private static final String CHK_PNT_OV = "1";

    /** Check Point : Soft Allocation */
    private static final String CHK_PNT_SA = "2";

    /** Check Point : Shipping Validation */
    private static final String CHK_PNT_SV = "3";

    /** Check Point : Credit Validation */
    private static final String CHK_PNT_CV = "5";

    /** Release Point : Credit Check */
    private static final String REL_PNT_CREDIT_CHECK = "3";

    /** Release Point : S/O Creation */
    private static final String REL_PNT_SO_CREATION = "4";

    /** SET Item Header */
    private static final String SET_ITEM_PARENT_NUM = "000";

    /** Max Amount */
    private static final BigDecimal MAX_AMT = new BigDecimal("999999999999999.9999");

    //    private static final BigDecimal MAX_QTY = new BigDecimal("9999999999");

    //    private static final String SET_SO_AVAL_QTY = "SET_SO_AVAL_QTY";
    //    private static final String SET_PO_AVAL_QTY = "SET_PO_AVAL_QTY";

    /** Date Format */
    String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS"; //20150924 add

    /** Flag 'Y' */
    private static final String Y = ZYPConstant.FLG_ON_Y;

    /** Flag 'N' */
    private static final String N = ZYPConstant.FLG_OFF_N;

    //    /** CASE_UOM_CD - Var Char CONST_VAL */
    //    private static final String CASE_UOM_CD = "CASE_UOM_CD";

    //START 2013/07/01 S.Yoshida [ADD for R-OM021]
    /** VAR_CHAR_CONST key. */
    private static final String PMT_TERM_CR_CARD = "PMT_TERM_CR_CARD";

    /** PMT_TERM_CASH_DISC for Credit Card. */
    private String[] pmtTermCrCardAry = null;
    //END 2013/07/01 S.Yoshida [ADD for R-OM021]

    /** SSM Client */
    private final S21SsmBatchClient ssmClient;

    /** Message ID Set */
    private Set<String> xxMsgIdSet;

    /** Batch Type */
    private ONBATCH_TYPE onBatchType;

    /** Previous CPO Order Number */
    private String preCpoOrdNum;

    /** Component Composition List */
    private Map<String, Object> compCmpsnList;

    /** Order Validator */
    private OrderValidator orderValidator;

    // S21_NA#13323 Add Start
    /** Is All Line Credit Flag */
    private boolean isAllLineCreditFlg = false;
    // S21_NA#13323 Add End

    // S21_NA#14973 Add Start
    /** Exemption Flag */
    private boolean hasExemptionFlg = false;
    // S21_NA#14973 Add End

    // 2017/12/27 QC#22737 add start
    /** CR_CHK_LVL_ACCT */
    private String CR_CHK_LVL_ACCT = "CR_CHK_LVL_ACCT";
    // 2017/12/27 QC#22737 add end

    // 2018/01/18 QC#22737-2 add start
    /** Credit Check Mode Equipment */
    private String EQUIP = "EQUIP";
    
    /** Credit Check Mode Contract */
    private String CONTRACT = "CONTRACT";
    
    /** Credit Check Mode Contract */
    private String BILLABLE = "BILLABLE";

    // Add Start 2019/09/06 QC#53016
    /** Const Key Credit/Rebill And Bill Only Dummy WH Code  */
    private String CONST_KEY_CR_AND_BILL_ONLY_DUMMY_WH_CD = "CR_AND_BILL_ONLY_DUMMY_WH_CD";
    // Add End 2019/09/06 QC#53016

    /** Credit Profile type */
    private String CHK_REQ = "03";
    // 2018/01/18 QC#22737-2 add end

    //    /** Credit Card Payment Code */
    //    private final String DS_PMT_METH_CREDIT_CARD = "CC";

    // 2018/11/05 S21_NA#29015 Add Start
    public static final String SUB_SYS_ID = "NWZ";

    public static final String PROCESS_ID = "OM";

    public static final String DOCUMENT_TYPE = "OM";

    public static final String EVENT_ID = "Booked";
    // 2018/11/05 S21_NA#29015 Add End


    /** cache "Validation APIs" */
    private final Map<Class<? extends S21ApiCommonBase>, S21ApiCommonBase> apiCache = new HashMap<Class<? extends S21ApiCommonBase>, S21ApiCommonBase>();

    public NWZC004001() {
        super();
        ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Validation Process Manager.
     * @param param NWZC004001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC004001PMsg param, final ONBATCH_TYPE onBatchType) {
        final String methodNm = "execute";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            /*********************************************************
             * 1. initialize global variables.
             ********************************************************/
            this.onBatchType = onBatchType;
            this.xxMsgIdSet = new LinkedHashSet<String>();
            this.preCpoOrdNum = null;
            this.compCmpsnList = null;

            /*********************************************************
             * 2. check API parameter.
             ********************************************************/
            if (!checkReqParams(param)) {
                return;
            }

            /*********************************************************
             * 3. Validation
             ********************************************************/
            final String xxRqstTpCd = param.xxRqstTpCd.getValue();

            this.hasExemptionFlg = hasExemption(param); // S21_NA#14973 Add
            this.isAllLineCreditFlg = isAllLineCredit(param); // S21_NA#13323 Add

            if (VAL_TP_OV.equals(xxRqstTpCd)) {
                execOrderValidation(param);
            } else if (VAL_TP_SA.equals(xxRqstTpCd)) {
                execSoftAllocValidation(param);
            } else if (VAL_TP_SV.equals(xxRqstTpCd)) {
                execShippingValidation(param);
                // ********** [# 372937] - START
            } else if (VAL_TP_CV.equals(xxRqstTpCd)) {
                execCreditValidationForAutoHoldRls(param);
                // ********** [# 372937] - END
            } else if (VAL_TP_RC.equals(xxRqstTpCd)) {
                execRecalculation(param);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            // set param.xxMsgIdList.
            if (hasXxMsgId()) {
                int cnt = 0;
                for (String xxMsgId : xxMsgIdSet) {
                    if (cnt >= param.xxMsgIdList.length()) {
                        break;
                    }
                    setValue(param.xxMsgIdList.no(cnt).xxMsgId, xxMsgId);
                    cnt++;
                }
                param.xxMsgIdList.setValidCount(cnt);
            }

            writePerformanceProfilingLog(getClass(), "#xxHldFlg=[" + param.xxHldFlg.getValue() + "]");
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    /**
     * Validation Process Manager.
     * @param params List<NWZC004001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWZC004001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NWZC004001PMsg param : params) {
            this.execute(param, onBatchType);
        }
    }

    /**
     * Credit Validation.
     * @param param
     * @throws SQLException
     */
    private void execCreditValidation(NWZC004001PMsg param) throws SQLException {
        final String methodNm = "execCreditValidation";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            // --------------------------------------------------
            // get Validation Order List.
            // --------------------------------------------------
            final List<Map<String, Object>> orderList = getCrChkOrderList(param);
            if (orderList == null) {
                addXxMsgId(NWZM0075E);
                return;
            } else if (orderList.isEmpty()) {
                return;
            }

            // --------------------------------------------------
            // execute Validation.
            // --------------------------------------------------
            final boolean hasHlds = getOrderValidator().exec(param, orderList, CHK_PNT_CV);
            if (hasXxMsgId()) {
                return;
            }

            // Def#1481 Delete
            // --------------------------------------------------
            // update Credit Check Qty.
            // --------------------------------------------------
            //updateCrChkQty(orderList);
            //if (hasXxMsgId()) {
            //    return;
            //}

            if (hasHlds) {

                final NWZC004001PMsg pMsg;

                /****************************************************************************************************
                 * case : Order Entry Submit (New Entry) -> CPO Update
                 * API -> Order Validation -> Allocaion API -> Hard
                 * Allocation API -> Shipping Validaion -> Credit
                 * Validation(*this case)
                 ****************************************************************************************************/
                // S21_NA#14973 Mod Start
//                // request by [NWAL0010] : Order Entry Screen (New Entry)
//                if (isRequestByOrderEntryScrnNewEntry()) {
//                    pMsg = param;
//
//                } else {
//                    pMsg = (NWZC004001PMsg) param.clone();
//                    setValue(pMsg.cpoOrdNum_I, (String) orderList.get(0).get("CPO_ORD_NUM"));
//                    setValue(pMsg.cpoDtlLineNum_I, "");
//                    setValue(pMsg.cpoDtlLineSubNum_I, "");
//                    setValue(pMsg.shpgPlnNum_I, "");
//                }
                pMsg = (NWZC004001PMsg) param.clone();
                setValue(pMsg.cpoOrdNum_I, (String) orderList.get(0).get("CPO_ORD_NUM"));
                setValue(pMsg.cpoDtlLineNum_I, "");
                setValue(pMsg.cpoDtlLineSubNum_I, "");
                setValue(pMsg.shpgPlnNum_I, "");
                // S21_NA#14973 Mod End

                final List<Map<String, Object>> recalcOrderList = getRecalcOrderList(pMsg);

                // --------------------------------------------------
                // update 'SLS/CR' Hld Qty.
                // --------------------------------------------------
                updateHldQty(recalcOrderList);
                if (hasXxMsgId()) {
                    return;
                }
            }

            // Def#1481 Add
            // --------------------------------------------------
            // update Credit Check Qty.
            // --------------------------------------------------
            updateCrChkQty(param, orderList);
            if (hasXxMsgId()) {
                return;
            }

            // --------------------------------------------------
            // clear Available 'SO/PO' Qty.
            // --------------------------------------------------
//            clearAvalQty(orderList.get(0));

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    /**
     * Credit Validation. (for [NWAL0050] : Hold Release Screen)
     * @param crValidPMsgList
     * @return NWZC004001PMsg
     * @throws SQLException
     */
    private NWZC004001PMsg execCreditValidationForHoldReleaseScrn(List<NWZC004001PMsg> crValidPMsgList) throws SQLException {
        final String methodNm = "execCreditValidationForHoldReleaseScrn";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            final NWZC004001PMsg param = new NWZC004001PMsg();

            if (!isEmpty(crValidPMsgList)) {

                // set only 'CPO_ORD_NUM'.
                setValue(param.glblCmpyCd, crValidPMsgList.get(0).glblCmpyCd);
                setValue(param.slsDt, crValidPMsgList.get(0).slsDt);
                setValue(param.cpoOrdNum_I, crValidPMsgList.get(0).cpoOrdNum_I);

                // --------------------------------------------------
                // get Validation Order List.
                // --------------------------------------------------
                final List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();

                for (NWZC004001PMsg crValidPMsg : crValidPMsgList) {
                    final List<Map<String, Object>> crChkOrderList = getCrChkOrderList(crValidPMsg);
                    if (crChkOrderList != null) {
                        orderList.addAll(crChkOrderList);
                    }
                }

                if (isEmpty(orderList)) {
                    return param;
                }

                // --------------------------------------------------
                // execute Validation.
                // --------------------------------------------------
                final boolean hasHlds = getOrderValidator().exec(param, orderList, CHK_PNT_CV);
                if (hasXxMsgId()) {
                    return param;
                }

                // Def#1481 Delete
                // --------------------------------------------------
                // update Credit Check Qty.
                // --------------------------------------------------
                //updateCrChkQty(orderList);
                //if (hasXxMsgId()) {
                //    return param;
                //}

                if (hasHlds) {

                    final List<Map<String, Object>> recalcOrderList = new ArrayList<Map<String, Object>>();

                    final Set<String> doneHldCpoOrdSet = new HashSet<String>();

                    for (int i = 0; i < param.A.getValidCount(); i++) {

                        final NWZC004001_APMsg hldPMsg = param.A.no(i);
                        String cpoOrdNum = hldPMsg.cpoOrdNum_O.getValue();
                        String cpoDtlLineNum = hldPMsg.cpoDtlLineNum_O.getValue();
                        String cpoDtlLineSubNum = hldPMsg.cpoDtlLineSubNum_O.getValue();
                        String shpgPlnNum = hldPMsg.shpgPlnNum_O.getValue();

                        final String key = new StringBuilder().append(cpoOrdNum).append(".").append(cpoDtlLineNum).append(".").append(cpoDtlLineSubNum).append(".").append(shpgPlnNum).toString();
                        if (doneHldCpoOrdSet.contains(key)) {
                            continue;
                        } else {
                            doneHldCpoOrdSet.add(key);
                        }

                        final NWZC004001PMsg pMsg = new NWZC004001PMsg();
                        setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
                        setValue(pMsg.cpoOrdNum_I, cpoOrdNum);
                        setValue(pMsg.cpoDtlLineNum_I, cpoDtlLineNum);
                        setValue(pMsg.cpoDtlLineSubNum_I, cpoDtlLineSubNum);
                        setValue(pMsg.shpgPlnNum_I, shpgPlnNum);

                        recalcOrderList.addAll(getRecalcOrderList(pMsg));
                    }

                    // --------------------------------------------------
                    // update 'SLS/CR' Hld Qty.
                    // --------------------------------------------------
                    updateHldQty(recalcOrderList);
                    if (hasXxMsgId()) {
                        return param;
                    }
                }

                // --------------------------------------------------
                // update Credit Check Qty.
                // --------------------------------------------------
                updateCrChkQty(param, orderList);
                if (hasXxMsgId()) {
                    return param;
                }

                // --------------------------------------------------
                // clear Available 'SO/PO' Qty.
                // --------------------------------------------------
                clearAvalQty(orderList.get(0));
            }

            return param;

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    /**
     * Order Validation.
     * @param param
     * @throws SQLException
     */
    protected void execOrderValidation(NWZC004001PMsg param) throws SQLException {
        final String methodNm = "execOrderValidation";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            // --------------------------------------------------
            // get Validation Order List.
            // --------------------------------------------------
            List<Map<String, Object>> orderList = getOrderList(param);
            if (orderList.isEmpty()) {
                addXxMsgId(NWZM0075E);
                return;
            }

            // --------------------------------------------------
            // execute Validation.
            // --------------------------------------------------
            final boolean hasHlds = getOrderValidator().exec(param, orderList, CHK_PNT_OV);
            if (hasXxMsgId()) {
                return;
            }

            // START 09/05/2013 A.Suda [WDS Defect#1242#1654 ADD]
            // --------------------------------------------------
            // update Hld Flg.
            // --------------------------------------------------
            String cpoOrdNum = (String) orderList.get(0).get("CPO_ORD_NUM");
            updateHldFlg(param, cpoOrdNum);
            if (hasXxMsgId()) {
                return;
            }

            // --------------------------------------------------
            // update 'SLS/CR' Hld Qty.
            // --------------------------------------------------
            updateHldQty(orderList);
            if (hasXxMsgId()) {
                return;
            }
            // END 09/05/2013 A.Suda [WDS Defect#1242#1654 ADD]

            // ********** add by K.Tajima [Def#1594(Prod)] - START
            boolean execRecalculation = false;
            final String threadKey = ThreadKey.EXEC_RE_CALCULATION.toString();
            if (getFromThread(threadKey) != null) {
                execRecalculation = ((Boolean) getFromThread(threadKey)).booleanValue();
                putInThread(threadKey, Boolean.FALSE);
            }
            // ********** add by K.Tajima [Def#1594( Prod)] - E N D

            if (hasHlds || execRecalculation) {

                // --------------------------------------------------
                // get Recalculation Order List.
                // --------------------------------------------------
                orderList = getRecalcOrderList(param);

                // --------------------------------------------------
                // update 'SLS/CR' Hld Qty.
                // --------------------------------------------------
                updateHldQty(orderList);
                if (hasXxMsgId()) {
                    return;
                }

                // --------------------------------------------------
                // clear Available Qty.
                // --------------------------------------------------
                clearAvalQty(orderList.get(0));
            }

            // --------------------------------------------------
            // Credit Validation
            // --------------------------------------------------
            for (Map<String, Object> order : orderList) {

                final String cpoDtlLineSubNum = (String) order.get("CPO_DTL_LINE_SUB_NUM");
                //                final String whType           = (String) order.get("whType");
                //                final String shpgStsCd        = (String) order.get("SHPG_STS_CD");

                //WDS#110 START
                //                final BigDecimal ordQty   = (BigDecimal) order.get("S_ORD_QTY");
                //WDS#110 E N D
                final BigDecimal crChkQty = (BigDecimal) order.get("CR_CHK_QTY");

                final String glblCmpyCd = (String) order.get("GLBL_CMPY_CD");
                final String shpgPlnNum = (String) order.get("SHPG_PLN_NUM");

                // lock [SHPG_PLN]
                SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
                setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
                shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);

                if (SHPG_STS.CANCELLED.equals(shpgPlnTMsg.shpgStsCd.getValue())) {
                    continue;
                }

                final BigDecimal crHldQty = shpgPlnTMsg.crHldQty.getValue();
                //WDS#110 START
                //                final BigDecimal crHldQtyOld = (BigDecimal) order.get("CR_HLD_QTY");
                //WDS#110 E N D

                // do Credit Validation?
                boolean doCrValidation = false;
                if (!SET_ITEM_PARENT_NUM.equals(cpoDtlLineSubNum)) {
                    // Def#1481 Modify
                    // if (ordQty.compareTo(crChkQty) > 0) {
                    //WDS#110 START
                    //                    if (ordQty.compareTo(crChkQty) > 0 && (ZERO.compareTo(crHldQty) == 0 && ZERO.compareTo(crHldQtyOld) == 0)) {
                    if (ZERO.compareTo(crChkQty) == 0 && ZERO.compareTo(crHldQty) == 0 && !existsSlsHld(shpgPlnTMsg)) {
                        //WDS#110 E N D
                        doCrValidation = true;
                    }
                }

                if (doCrValidation) {

                    final NWZC004001PMsg crValidPMsg = new NWZC004001PMsg();
                    setValue(crValidPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
                    setValue(crValidPMsg.slsDt, param.slsDt.getValue());
                    // 2018/07/06 QC#27079 add start
                    setValue(crValidPMsg.cpoOrdNum_I, (String) order.get("CPO_ORD_NUM"));
                    setValue(crValidPMsg.cpoDtlLineNum_I, (String) order.get("CPO_DTL_LINE_NUM"));
                    setValue(crValidPMsg.cpoDtlLineSubNum_I, (String) order.get("CPO_DTL_LINE_SUB_NUM"));
                    // 2018/07/06 QC#27079 add end
                    setValue(crValidPMsg.shpgPlnNum_I, (String) order.get("SHPG_PLN_NUM"));

                    // Credit Validation
                    execCreditValidation(crValidPMsg);
                    if (hasXxMsgId()) {
                        return;
                    }

                    // has Credit Validation Holds.
                    if (crValidPMsg.A.getValidCount() > 0) {
                        addCreditValidHldOrders(crValidPMsg.A, param);
                    }

                    break; // S21_NA#14973 Add
                }
            }
            clearAvalQty(orderList.get(0));

            // START 09/05/2013 A.Suda [WDS Defect#1242#1654 ADD]
            // --------------------------------------------------
            // update Available Qty.
            // --------------------------------------------------
            // S21_NA#12234 Mod Start
            // boolean hasNotError = updAvalQty(param, cpoOrdNum);
            // if (!hasNotError) {
            //     return;
            // }
            String isLastLoop = param.xxRsltFlg.getValue();
            if (!ZYPCommonFunc.hasValue(isLastLoop) || !ZYPConstant.FLG_OFF_N.equals(isLastLoop)) {
                boolean hasNotError = updAvalQty(param, cpoOrdNum);
                if (!hasNotError) {
                    return;
                }
            }
            // S21_NA#12234 Mod End
            // END 09/05/2013 A.Suda [WDS Defect#1242#1654 ADD]

            // --------------------------------------------------
            // set Hld Flg.
            // --------------------------------------------------
            setHldFlg(param);

            // 2017/05/08 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add Start
            setOrdHdrDplySts(param);
            // 2017/05/08 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add End

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    /**
     * Recalculation.
     * @param param
     * @throws SQLException
     */
    protected void execRecalculation(NWZC004001PMsg param) throws SQLException {
        final String methodNm = "execRecalculation";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            // 2017/05/08 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add Start
            List<String> recalcShpgPlnNumList = new ArrayList<String>(0);
            // 2017/05/08 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add End
            // --------------------------------------------------
            // get Recalculation Order List.
            // --------------------------------------------------
            final List<Map<String, Object>> orderList = getRecalcOrderList(param);
            if (orderList.isEmpty()) {
                addXxMsgId(NWZM0073E);
                return;
            }

            final String cpoOrdNum = (String) orderList.get(0).get("CPO_ORD_NUM");
            if (preCpoOrdNum == null) {
                preCpoOrdNum = cpoOrdNum;
            } else if (preCpoOrdNum.equals(cpoOrdNum)) {
                return;
            } else {
                preCpoOrdNum = cpoOrdNum;
            }

            // 2017/05/08 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add Start
            for (Map<String, Object> orderData : orderList) {
                String shpgPlnNum = (String) orderData.get("SHPG_PLN_NUM");
                if (!S21StringUtil.isEmpty(shpgPlnNum)) {
                    recalcShpgPlnNumList.add(shpgPlnNum);
                }
            }
            // 2017/05/08 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add End
            // --------------------------------------------------
            // update Hld Flg.
            // --------------------------------------------------
            updateHldFlg(param, cpoOrdNum);
            if (hasXxMsgId()) {
                return;
            }

            // --------------------------------------------------
            // update 'SLS/CR' Hld Qty.
            // --------------------------------------------------
            updateHldQty(orderList);
            if (hasXxMsgId()) {
                return;
            }
            
            // 2018/07/02 S21_NA#24189 Add Start
            // --------------------------------------------------
            // reset invoiced 'SLS/CR' Hld Qty.
            // --------------------------------------------------
            resetInvdSlsHldQty(param, cpoOrdNum);
            // 2018/07/02 S21_NA#24189 Add End

            // --------------------------------------------------
            // Credit Validation
            // --------------------------------------------------
            // request by [NWAL0050] : Hold Release Screen
            if (isRequestByHoldReleaseScrn()) {

                /****************************************************************************************************
                 * case : Hold Release Screen Submit -> Hold Release
                 * API -> Recalculation -> Credit Validation ->
                 * Recalculation(*this case)
                 ****************************************************************************************************/
                final List<NWZC004001PMsg> crValidPMsgList = new ArrayList<NWZC004001PMsg>();

                // Def#1910 Add start -->
                // START 2013/02/18 S.Iidaka [WDS#Def 704 DEL]
                //                final Set<String> doneShipCpltSet = new HashSet<String>();
                //                final Set<String> doneSetShpgPlnSet = new HashSet<String>();
                // E N D 2013/02/18 S.Iidaka [WDS#Def 704 DEL]
                // Def#1910 Add end <--

                for (Map<String, Object> order : orderList) {

                    final String cpoDtlLineSubNum = (String) order.get("CPO_DTL_LINE_SUB_NUM");

                    if (!SET_ITEM_PARENT_NUM.equals(cpoDtlLineSubNum)) {

                        final String glblCmpyCd = (String) order.get("GLBL_CMPY_CD");
                        final String shpgPlnNum = (String) order.get("SHPG_PLN_NUM");

                        // lock [SHPG_PLN]
                        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                        setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
                        setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
                        shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);

                        if (SHPG_STS.CANCELLED.equals(shpgPlnTMsg.shpgStsCd.getValue())) {
                            continue;
                        }

                        final BigDecimal crHldQty = shpgPlnTMsg.crHldQty.getValue();
                        //WDS#110 START
                        //                        final BigDecimal crHldQtyOld = (BigDecimal) order.get("CR_HLD_QTY");
                        //                        final BigDecimal ordQty   = (BigDecimal) order.get("S_ORD_QTY");
                        //WDS#110 E N D
                        final BigDecimal crChkQty = (BigDecimal) order.get("CR_CHK_QTY");

                        // Def#1481 Modufy
                        //if (ordQty.compareTo(crChkQty) > 0) {
                        //WDS#110 START
                        //                          if (ordQty.compareTo(crChkQty) > 0 && (ZERO.compareTo(crHldQty) == 0 && ZERO.compareTo(crHldQtyOld) == 0)) {
                        if (ZERO.compareTo(crChkQty) == 0 && ZERO.compareTo(crHldQty) == 0 && !existsSlsHld(shpgPlnTMsg)) {
                            //WDS#110 E N D
                            // START 2013/1/28 A.Suda [WDS#Def 430]
                            // Def#1910 Add start -->
                            //final String shipCpltCd = (String) order.get("SHIP_CPLT_CD");
                            //if (hasValue(shipCpltCd)) {
                            //    final String key = new StringBuilder().append(shipCpltCd).toString();
                            //    if (doneShipCpltSet.contains(key)) {
                            //        continue;
                            //    } else {
                            //        doneShipCpltSet.add(key);
                            //    }
                            //}

                            //final String setShpgPlnNum = (String) order.get("SET_SHPG_PLN_NUM");
                            //if (hasValue(setShpgPlnNum)) {
                            //    final String key = new StringBuilder().append(setShpgPlnNum).toString();
                            //    if (doneSetShpgPlnSet.contains(key)) {
                            //        continue;
                            //    } else {
                            //        doneSetShpgPlnSet.add(key);
                            //    }
                            //}
                            // Def#1910 Add end <--
                            // END 2013/1/28 A.Suda [WDS#Def 430 DEL]

                            final NWZC004001PMsg crValidPMsg = new NWZC004001PMsg();
                            setValue(crValidPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
                            setValue(crValidPMsg.slsDt, param.slsDt.getValue());
                            setValue(crValidPMsg.cpoOrdNum_I, (String) order.get("CPO_ORD_NUM"));
                            setValue(crValidPMsg.cpoDtlLineNum_I, (String) order.get("CPO_DTL_LINE_NUM"));
                            setValue(crValidPMsg.cpoDtlLineSubNum_I, (String) order.get("CPO_DTL_LINE_SUB_NUM"));
                            setValue(crValidPMsg.shpgPlnNum_I, (String) order.get("SHPG_PLN_NUM"));
                            crValidPMsgList.add(crValidPMsg);
                        }
                    }
                }

                if (!isEmpty(crValidPMsgList)) {

                    // Credit Validation
                    final NWZC004001PMsg crValidPMsg = execCreditValidationForHoldReleaseScrn(crValidPMsgList);
                    if (hasXxMsgId()) {
                        return;
                    }

                    // has Credit Validation Holds.
                    if (crValidPMsg.A.getValidCount() > 0) {
                        addCreditValidHldOrders(crValidPMsg.A, param);
                    }
                }

            } else {

                for (Map<String, Object> order : orderList) {

                    final String cpoDtlLineSubNum = (String) order.get("CPO_DTL_LINE_SUB_NUM");

                    if (!SET_ITEM_PARENT_NUM.equals(cpoDtlLineSubNum)) {

                        final String glblCmpyCd = (String) order.get("GLBL_CMPY_CD");
                        final String shpgPlnNum = (String) order.get("SHPG_PLN_NUM");

                        // lock [SHPG_PLN]
                        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                        setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
                        setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
                        shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);

                        if (SHPG_STS.CANCELLED.equals(shpgPlnTMsg.shpgStsCd.getValue())) {
                            continue;
                        }

                        final BigDecimal crHldQty = shpgPlnTMsg.crHldQty.getValue();
                        //WDS#110 START
                        //                        final BigDecimal crHldQtyOld = (BigDecimal) order.get("CR_HLD_QTY");
                        //                        final BigDecimal ordQty   = (BigDecimal) order.get("S_ORD_QTY");
                        //WDS#110 E N D
                        final BigDecimal crChkQty = (BigDecimal) order.get("CR_CHK_QTY");


                        // START 2018/08/27 [QC#27692, ADD]
                        boolean skipCrediValidation  = ZYPConstant.FLG_ON_Y.equals(param.xxPgFlg.getValue());
                        // END   2018/08/27 [QC#27692, ADD]

                        // Def#1481 Modify
                        //if (ordQty.compareTo(crChkQty) > 0) {
                        //WDS#110 START
                        //                          if (ordQty.compareTo(crChkQty) > 0 && (ZERO.compareTo(crHldQty) == 0 && ZERO.compareTo(crHldQtyOld) == 0)) {
                        // START 2013/1/28 A.Suda [WDS#Def 404]
                        if (ZERO.compareTo(crChkQty) == 0 && ZERO.compareTo(crHldQty) == 0 && !existsSlsHld(shpgPlnTMsg) && !skipCrediValidation) { // 2018/08/27 [QC#27692, MOD]
                            // END 2013/1/28 A.Suda [WDS#Def 404 MOD]
                            //WDS#110 E N D

                            final NWZC004001PMsg crValidPMsg = new NWZC004001PMsg();
                            setValue(crValidPMsg.glblCmpyCd, param.glblCmpyCd.getValue());
                            setValue(crValidPMsg.slsDt, param.slsDt.getValue());
                            setValue(crValidPMsg.cpoOrdNum_I, (String) order.get("CPO_ORD_NUM"));
                            setValue(crValidPMsg.cpoDtlLineNum_I, (String) order.get("CPO_DTL_LINE_NUM"));
                            setValue(crValidPMsg.cpoDtlLineSubNum_I, (String) order.get("CPO_DTL_LINE_SUB_NUM"));
                            if (hasValue(shpgPlnNum)) {
                                setValue(crValidPMsg.shpgPlnNum_I, shpgPlnNum);
                            } else {
                                setValue(crValidPMsg.shpgPlnNum_I, (String) order.get("SHPG_PLN_NUM"));
                            }

                            // Credit Validation
                            execCreditValidation(crValidPMsg);
                            if (hasXxMsgId()) {
                                return;
                            }

                            // has Credit Validation Holds.
                            if (crValidPMsg.A.getValidCount() > 0) {
                                addCreditValidHldOrders(crValidPMsg.A, param);
                            }

                            break; // S21_NA#14973 Add
                        }
                    }
                }
                clearAvalQty(orderList.get(0));
            }

            // --------------------------------------------------
            // update Credit Check Qty.
            // --------------------------------------------------
            updateCrChkQty(param, orderList);
            if (hasXxMsgId()) {
                return;
            }

            // START 09/05/2013 A.Suda [WDS Defect#1242#1654 ADD]
            // --------------------------------------------------
            // update Available Qty.
            // --------------------------------------------------
            boolean hasNotError = updAvalQty(param, cpoOrdNum);
            if (!hasNotError) {
                return;
            }
            // END 09/05/2013 A.Suda [WDS Defect#1242#1654 ADD]

            // --------------------------------------------------
            // set Hld Flg.
            // --------------------------------------------------
            setHldFlg(param);

            // 2017/05/08 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add Start
            callOrderDisplayStatusUpdAPI(param, cpoOrdNum, recalcShpgPlnNumList, false);
            // 2017/05/08 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add End

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    /**
     * Shipping Validation.
     * @param param
     * @throws SQLException
     */
    protected void execShippingValidation(NWZC004001PMsg param) throws SQLException {
        final String methodNm = "execShippingValidation";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            // --------------------------------------------------
            // get Validation Order List.
            // --------------------------------------------------
            final List<Map<String, Object>> orderList = getOrderList(param);
            if (orderList.isEmpty()) {
                addXxMsgId(NWZM0075E);
                return;
            }

            // --------------------------------------------------
            // Validation (Shipping)
            // --------------------------------------------------
            final boolean hasHlds = getOrderValidator().exec(param, orderList, CHK_PNT_SV);
            if (hasXxMsgId()) {
                return;
            }

            // START 11/12/2013 M.FUJI [WDS Defect#3025 DELETE]
            //if (hasHlds) {
            // END 11/12/2013 M.FUJI [WDS Defect#3025 DELETE]

            // --------------------------------------------------
            // get Recalculation Order List.
            // --------------------------------------------------
            final List<Map<String, Object>> recalcOrderList = getRecalcOrderList(param);

            // START 11/14/2013 M.FUJI [WDS Defect#2745 ADD]
            // --------------------------------------------------
            // update Hld Flg.
            // --------------------------------------------------
            updateHldFlg(param, (String) orderList.get(0).get("CPO_ORD_NUM"));
            if (hasXxMsgId()) {
                return;
            }
            // END 11/14/2013 M.FUJI [WDS Defect#2745 ADD]

            // --------------------------------------------------
            // update 'SLS/CR' Hld Qty.
            // --------------------------------------------------
            updateHldQty(recalcOrderList);
            if (hasXxMsgId()) {
                return;
            }

            // START 11/12/2013 M.FUJI [WDS Defect#3025 DELETE]
            // --------------------------------------------------
            // clear Available 'SO/PO' Qty.
            // --------------------------------------------------
            //clearAvalQty(recalcOrderList.get(0));
            //}
            // END 11/12/2013 M.FUJI [WDS Defect#3025 DELETE]

            // START 11/12/2013 M.FUJI [WDS Defect#3025 MOVE]
            // START 09/05/2013 A.Suda [WDS Defect#1242#1654 ADD]
            // --------------------------------------------------
            // update Available Qty.
            // --------------------------------------------------
            String cpoOrdNum = (String) orderList.get(0).get("CPO_ORD_NUM");
            boolean hasNotError = updAvalQty(param, cpoOrdNum);
            if (!hasNotError) {
                return;
            }
            // END 09/05/2013 A.Suda [WDS Defect#1242#1654 ADD]
            // END 11/12/2013 M.FUJI [WDS Defect#3025 MOVE]

            // --------------------------------------------------
            // set Hld Flg.
            // --------------------------------------------------
            setHldFlg(param);

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    /**
     * Soft Allocation.
     * @param param
     * @throws SQLException
     */
    protected void execSoftAllocValidation(NWZC004001PMsg param) throws SQLException {
        final String methodNm = "execSoftAllocValidation";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            // --------------------------------------------------
            // get Validation Order List.
            // --------------------------------------------------
            final List<Map<String, Object>> orderList = getOrderList(param);
            if (orderList.isEmpty()) {
                addXxMsgId(NWZM0075E);
                return;
            }

            // --------------------------------------------------
            // Validation (Soft Allocation)
            // --------------------------------------------------
            final boolean hasHlds = getOrderValidator().exec(param, orderList, CHK_PNT_SA);
            if (hasXxMsgId()) {
                return;
            }

            if (hasHlds) {

                // --------------------------------------------------
                // get Recalculation Order List.
                // --------------------------------------------------
                final List<Map<String, Object>> recalcOrderList = getRecalcOrderList(param);

                // --------------------------------------------------
                // update 'SLS/CR' Hld Qty.
                // --------------------------------------------------
                updateHldQty(recalcOrderList);
                if (hasXxMsgId()) {
                    return;
                }

                // --------------------------------------------------
                // clear Available 'SO/PO' Qty.
                // --------------------------------------------------
                clearAvalQty(recalcOrderList.get(0));
            }

            // --------------------------------------------------
            // set Hld Flg.
            // --------------------------------------------------
            setHldFlg(param);

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private boolean checkReqParams(final NWZC004001PMsg param) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        if (!hasValue(glblCmpyCd)) {
            addXxMsgId(NWZM0001E);
            return false;
        }

        String slsDt = param.slsDt.getValue();
        if (!hasValue(slsDt)) {
            addXxMsgId(NWZM0445E);
            return false;
        }

        String xxRqstTpCd = param.xxRqstTpCd.getValue();
        String cpoOrdNum = param.cpoOrdNum_I.getValue();
        String cpoDtlLineNum = param.cpoDtlLineNum_I.getValue();
        String cpoDtlLineSubNum = param.cpoDtlLineSubNum_I.getValue();
        String shpgPlnNum = param.shpgPlnNum_I.getValue();

        if (VAL_TP_OV.equals(xxRqstTpCd)) {

            if (!hasValue(cpoOrdNum)) {
                addXxMsgId(NWZM0002E);
                return false;
            }
            if (hasValue(cpoOrdNum) && hasValue(cpoDtlLineSubNum) && !hasValue(cpoDtlLineNum)) {
                addXxMsgId(NWZM0003E);
                return false;
            }
            if (hasValue(cpoOrdNum) && hasValue(cpoDtlLineNum) && !hasValue(cpoDtlLineSubNum)) {
                addXxMsgId(NWZM0004E);
                return false;
            }

        } else if (asList(VAL_TP_SA, VAL_TP_SV).contains(xxRqstTpCd)) {

            if (!hasValue(shpgPlnNum)) {
                addXxMsgId(NWZM0250E);
                return false;
            }

        } else if (VAL_TP_RC.equals(xxRqstTpCd)) {

            if (!hasValue(shpgPlnNum)) {

                if (!hasValue(cpoOrdNum)) {
                    addXxMsgId(NWZM0002E);
                    return false;
                }
                if (hasValue(cpoDtlLineSubNum) && !hasValue(cpoDtlLineNum)) {
                    addXxMsgId(NWZM0003E);
                    return false;
                }
                if (hasValue(cpoDtlLineNum) && !hasValue(cpoDtlLineSubNum)) {
                    addXxMsgId(NWZM0004E);
                    return false;
                }
            }

        } else if (VAL_TP_CV.equals(xxRqstTpCd)) {

            if (!hasValue(cpoOrdNum) && !hasValue(shpgPlnNum)) {
                addXxMsgId(NWZM0002E);
                return false;
            }

        } else {
            addXxMsgId(NWZM0245E);
            return false;
        }

        //START 2013/07/01 S.Yoshida [ADD for R-OM021]
        // PMT_TERM_CASH_DISC for Credit Card.
        // String pmtTermCrCard = ZYPCodeDataUtil.getVarCharConstValue(PMT_TERM_CR_CARD, glblCmpyCd); // S21_NA#14512 Del
        String pmtTermCrCard = DataCacheForValidation.getInstance().getVarCharConstValue(glblCmpyCd, PMT_TERM_CR_CARD); // S21_NA#14512 Add
        if (!hasValue(pmtTermCrCard)) {
            addXxMsgId(NWZM1277E);
            return false;
        }
        this.pmtTermCrCardAry = pmtTermCrCard.split(",");
        //END 2013/07/01 S.Yoshida [ADD for R-OM021]

        return true;
    }

    private void clearAvalQty(Map<String, Object> order) {
        final String methodNm = "clearAvalQty";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            final String glblCmpyCd = (String) order.get("GLBL_CMPY_CD");
            final String cpoOrdNum = (String) order.get("CPO_ORD_NUM");

            final Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("hldLvl01", HLD_LVL.CPO_LEVEL);
            ssmParam.put("cpoOrdNum", cpoOrdNum);
            ssmParam.put("relPoint", REL_PNT_SO_CREATION);
            ssmParam.put("relFlg", N);

            final BigDecimal hldCnt = (BigDecimal) ssmClient.queryObject("getHldCnt", ssmParam);

            if (hldCnt.intValue() > 0) {

                final SHPG_PLNTMsg condTMsg = new SHPG_PLNTMsg();
                setValue(condTMsg.glblCmpyCd, glblCmpyCd);
                setValue(condTMsg.trxHdrNum, cpoOrdNum);

                final SHPG_PLNTMsg updTMsg = new SHPG_PLNTMsg();
                setValue(updTMsg.avalSoQty, ZERO);
                setValue(updTMsg.avalPoQty, ZERO);

                // ***** updateByPartialValue
                writePerformanceProfilingLog(getClass(), " #clear AVAL_SO_QTY and AVAL_PO_QTY. cpoOrdNum=[" + cpoOrdNum + "]");
                updateByPartialValue(//
                        condTMsg, new String[] {"glblCmpyCd", "trxHdrNum" } //
                        , updTMsg, new String[] {"avalSoQty", "avalPoQty" });
            }

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private boolean existsHld(SHPG_PLNTMsg shpgPlnMsg, String relPoint) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
        ssmParam.put("relPoint", relPoint);
        ssmParam.put("relFlg", N);
        ssmParam.put("cpoOrdNum", shpgPlnMsg.trxHdrNum.getValue());
        ssmParam.put("cpoLineNum", shpgPlnMsg.trxLineNum.getValue());
        ssmParam.put("shpgPlnNum", shpgPlnMsg.shpgPlnNum.getValue());
        ssmParam.put("hldLvl01", HLD_LVL.CPO_LEVEL);
        ssmParam.put("hldLvl02", HLD_LVL.CPO_DETAIL_LEVEL);
        ssmParam.put("hldLvl03", HLD_LVL.SHIPPING_PLAN_LEVEL);

        return !ZERO.equals((BigDecimal) ssmClient.queryObject("getHldCnt", ssmParam));
    }

    //WDS#110 START
    /**
     * Check exits Sales Hold(exclude AB-HOLD, B6-HOLD).
     * @param shpgPlnMsg SHPG_PLNTMsg
     * @return boolean
     */
    private boolean existsSlsHld(SHPG_PLNTMsg shpgPlnMsg) {

        // START MODIFY M.FUJI [Defect#3223]
        String key = shpgPlnMsg.glblCmpyCd.getValue().concat(shpgPlnMsg.trxHdrNum.getValue());
        BigDecimal slsHldCnt;

        if (slsHldCntMap.containsKey(key)) {
            slsHldCnt = (BigDecimal) slsHldCntMap.get(key);
        } else {
            final Map<String, String> ssmParam = new HashMap<String, String>();
            //final String whTpCd = getWhType(shpgPlnMsg.glblCmpyCd.getValue(), shpgPlnMsg.mdseCd.getValue(), shpgPlnMsg.poReqFlg.getValue());

            ssmParam.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
            ssmParam.put("cpoOrdNum", shpgPlnMsg.trxHdrNum.getValue());
            ssmParam.put("relFlg", N);
            //WDS Defect#2548#2550 DELETE
            // 2017/09/26 S21_NA#21279 Mod Start (Relive)
            ssmParam.put("hldLvl01",       HLD_LVL.CPO_LEVEL);
            ssmParam.put("whTpCdCommon",   WH_TP.COMMON);
            // 2017/09/26 S21_NA#21279 Mod End
            ssmParam.put("relPntCrChkFlg", Y);
            //WDS Defect#2548#2550 DELETE
            //ssmParam.put("whTpCd",         whTpCd);

            slsHldCnt = (BigDecimal) ssmClient.queryObject("getSlsHldCnt", ssmParam);
            slsHldCntMap.put(key, slsHldCnt);
        }

        // 2017/09/26 S21_NA#21279 Add Start
        if (ZERO.compareTo(slsHldCnt) == 0) {
            final Map<String, String> ssmParam = new HashMap<String, String>();
            final String whTpCd = getWhType(shpgPlnMsg.glblCmpyCd.getValue(), shpgPlnMsg.mdseCd.getValue(), shpgPlnMsg.poReqFlg.getValue());

            ssmParam.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
            ssmParam.put("cpoOrdNum", shpgPlnMsg.trxHdrNum.getValue());
            ssmParam.put("cpoDtlLineNum",  shpgPlnMsg.trxLineNum.getValue());
            ssmParam.put("cpoDtlLineSubNum",shpgPlnMsg.trxLineSubNum.getValue());
            ssmParam.put("setItemParentNum", SET_ITEM_PARENT_NUM);
            ssmParam.put("shpgPlnNum",shpgPlnMsg.shpgPlnNum.getValue());
            ssmParam.put("relFlg", N);
            ssmParam.put("hldLvl02",       HLD_LVL.CPO_DETAIL_LEVEL);
            ssmParam.put("hldLvl03",       HLD_LVL.SHIPPING_PLAN_LEVEL);
            ssmParam.put("whTpCdCommon",   WH_TP.COMMON);
            ssmParam.put("relPntCrChkFlg", Y);
            ssmParam.put("whTpCd",         whTpCd);

            List<BigDecimal> slsDtlHldCntList = (List<BigDecimal>) ssmClient.queryObjectList("getDtlSlsHldCnt", ssmParam);
            if (slsDtlHldCntList != null) {
                for (BigDecimal slsDtlHldCnt : slsDtlHldCntList) {
                    slsHldCnt = slsHldCnt.add(slsDtlHldCnt);
                }
            }
        }
        // 2017/09/26 S21_NA#21279 Add End

        return !ZERO.equals(slsHldCnt);
        //        return !ZERO.equals((BigDecimal) ssmClient.queryObject("getSlsHldCnt", ssmParam));
        //END MODIFY M.FUJI [Defect#3223]
    }

    //WDS#110 E N D

    /** check Exist Hold (Set Item) */
    private boolean existsHldSet(SHPG_PLNTMsg shpgPlnMsg, String relPoint) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
        ssmParam.put("relPoint", relPoint);
        ssmParam.put("relFlg", N);
        ssmParam.put("cpoOrdNum", shpgPlnMsg.trxHdrNum.getValue());
        ssmParam.put("cpoLineNum", shpgPlnMsg.trxLineNum.getValue());
        if (hasValue(shpgPlnMsg.setShpgPlnNum)) {
            ssmParam.put("shpgPlnNum", shpgPlnMsg.setShpgPlnNum.getValue());
        } else {
            ssmParam.put("shpgPlnNum", shpgPlnMsg.shpgPlnNum.getValue());

        }
        ssmParam.put("hldLvl01", HLD_LVL.CPO_LEVEL);
        ssmParam.put("hldLvl02", HLD_LVL.CPO_DETAIL_LEVEL);
        ssmParam.put("hldLvl03", HLD_LVL.SHIPPING_PLAN_LEVEL);
        ssmParam.put("trxSrcTp", TRX_SRC_TP.WHOLE_SALES_RETURN); // QC#11306 2016/09/20 Add

        return !ZERO.equals((BigDecimal) ssmClient.queryObject("getHldSetCompCnt", ssmParam));
    }

    private void getCompCmpsnList(final String glblCmpyCd, final String trxHdrNum, final String trxLineNum) {

        if (this.compCmpsnList == null) {
            this.compCmpsnList = new HashMap<String, Object>();
        }

        if (this.compCmpsnList.isEmpty()) {

            // 2017/06/09 S21_NA#14685 Add Start
            CPO_DTLTMsg prntSetCpoDtlTMsg = new CPO_DTLTMsg();
            prntSetCpoDtlTMsg.glblCmpyCd.setValue(glblCmpyCd);
            prntSetCpoDtlTMsg.cpoOrdNum.setValue(trxHdrNum);
            prntSetCpoDtlTMsg.cpoDtlLineNum.setValue(trxLineNum);
            prntSetCpoDtlTMsg.cpoDtlLineSubNum.setValue(SET_ITEM_PARENT_NUM);

            prntSetCpoDtlTMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(prntSetCpoDtlTMsg);
            BigDecimal prntSetOrdQty = BigDecimal.ONE;
            if (prntSetCpoDtlTMsg != null) {
                prntSetOrdQty = prntSetCpoDtlTMsg.ordQty.getValue();
            }
            // 2017/06/09 S21_NA#14685 Add End
            CPO_DTLTMsgArray cpoDtlMsgArray = getCompCpoDtlMsgArray(glblCmpyCd, trxHdrNum, trxLineNum);
            for (int i = 0, end1 = cpoDtlMsgArray.getValidCount(); i < end1; i++) {

                CPO_DTLTMsg cpoDtlMsg = (CPO_DTLTMsg) cpoDtlMsgArray.get(i);
                // 2023/06/26 QC#61556 START
                if (ZYPConstant.FLG_ON_Y.equals(cpoDtlMsg.cpoDtlCancFlg.getValue())) {
                    this.compCmpsnList.put(cpoDtlMsg.cpoDtlLineSubNum.getValue(), BigDecimal.ZERO);
                    continue;
                }
                // 2023/06/26 QC#61556 END
                // 2017/06/09 S21_NA#14685 Mod Start
//                BigDecimal compQty = getCompQty(glblCmpyCd, cpoDtlMsg.setMdseCd.getValue(), cpoDtlMsg.mdseCd.getValue(), cpoDtlMsg.cpoOrdTs.getValue());
                BigDecimal dtlOrdQty = cpoDtlMsg.ordQty.getValue();
                BigDecimal compQty = dtlOrdQty.divide(prntSetOrdQty, RoundingMode.DOWN); // QC#26691
                // 2017/06/09 S21_NA#14685 Mod End
                this.compCmpsnList.put(cpoDtlMsg.cpoDtlLineSubNum.getValue(), compQty);
            }

        }
    }

    private CPO_DTLTMsgArray getCompCpoDtlMsgArray(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum) {

        final CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        cpoDtlTMsg.setSQLID("005");
        cpoDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        cpoDtlTMsg.setConditionValue("cpoDtlLineNum01", cpoDtlLineNum);
        cpoDtlTMsg.setConditionValue("cpoDtlLineSubNum01", SET_ITEM_PARENT_NUM);

        return (CPO_DTLTMsgArray) findByCondition(cpoDtlTMsg);

    }

    // 2017/06/09 S21_NA#14685 Mod Start
//    /** Get Component Qty */
//    private BigDecimal getCompQty(String glblCmpyCd, String prntMdseCd, String childmdseCode, String ordTs) {
//
//        CMPSNTMsg cmpsnMsg = NWXCmpsnTMsgThreadLocalCache.getInstance().get(glblCmpyCd, prntMdseCd, childmdseCode, ordTs, ordTs);
//
//        BigDecimal compQty = ZERO;
//        if (cmpsnMsg != null) {
//            compQty = cmpsnMsg.childMdseQty.getValue();
//        }
//
//        return compQty;
//    }
    /** Get Component Qty */
    private BigDecimal getCompQty(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        if (this.compCmpsnList == null || this.compCmpsnList.isEmpty()) {
            getCompCmpsnList(glblCmpyCd, cpoOrdNum, cpoDtlLineNum);
        }
        return (BigDecimal) this.compCmpsnList.get(cpoDtlLineSubNum);
    }
    // 2017/06/09 S21_NA#14685 Mod End

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getCrChkOrderList(NWZC004001PMsg param) {
        final String methodNm = "getCrChkOrderList";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        writePerformanceProfilingLog(getClass(), " - CPO_ORD_NUM=[" + param.cpoOrdNum_I.getValue() + "], " + "CPO_DTL_LINE_NUM=[" + param.cpoDtlLineNum_I.getValue() + "], " + "CPO_DTL_LINE_SUB_NUM=[" + param.cpoDtlLineSubNum_I.getValue()
                + "], " + "SHPG_PLN_NUM=[" + param.shpgPlnNum_I.getValue() + "]");

        List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();

        try {

            // SHPG_PLN
            SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
            setValue(shpgPlnTMsg.glblCmpyCd, param.glblCmpyCd.getValue());
            setValue(shpgPlnTMsg.shpgPlnNum, param.shpgPlnNum_I.getValue());

            shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);

            // WDS#110 START
            //            if (hasValue(shpgPlnTMsg.shipCpltCd.getValue())) {
            //                orderList = getCrChkOrderListByShipCplt(shpgPlnTMsg);
            //                if (orderList == null) {
            //                    return orderList;
            //                }
            //
            //            } else if (hasValue(shpgPlnTMsg.setMdseCd.getValue())) {
            //                orderList = getCrChkOrderListBySet(shpgPlnTMsg);
            //                if (orderList == null) {
            //                    return orderList;
            //                }

            //            } else if (Y.equals(shpgPlnTMsg.uomCpltFlg.getValue())) {
            //                orderList = getCrChkOrderListByUOM(shpgPlnTMsg);
            //                if (orderList == null) {
            //                    return orderList;
            //                }
            //    
            //            } else {
            // WDS#110 E N D

            if (existsHld(shpgPlnTMsg, REL_PNT_CREDIT_CHECK)) {
                return orderList;
            }

            final Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", shpgPlnTMsg.glblCmpyCd.getValue());
            ssmParam.put("shpgPlnNum", shpgPlnTMsg.shpgPlnNum.getValue());
            ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);

            // Def#2025 Delete
            //                if (ONBATCH_TYPE.BATCH.equals(this.onBatchType)) {
            //                    ssmParam.put("wait", "1");
            //                } else {
            //                    ssmParam.put("wait", "0");
            //                }

            //                final List<Map<String, Object>> crChkOrdList = ssmClient.queryObjectList("getCrChkOrderList", ssmParam);
            final List<Map<String, Object>> crChkOrdList = ssmClient.queryObjectList("getCrChkOrderList", ssmParam, new S21SsmListResultSetHandlerSupport() {
                @Override
                protected List doProcessQueryResult(ResultSet rs) throws SQLException {
                    return toMapList(rs);
                }
            });

            if (crChkOrdList.isEmpty()) {
                return null;
            }

            final Map<String, Object> crChkOrd = crChkOrdList.get(0);
            String shpgStsCd = (String) crChkOrd.get("SHPG_STS_CD");
            String whTyp = getWhType(shpgPlnTMsg.glblCmpyCd.getValue(), (String) crChkOrd.get("S_MDSE_CD"), (String) crChkOrd.get("PO_REQ_FLG"));
            BigDecimal ordQty = (BigDecimal) crChkOrd.get("S_ORD_QTY");
            BigDecimal crChkQty = (BigDecimal) crChkOrd.get("CR_CHK_QTY");
            BigDecimal crHldQty = (BigDecimal) crChkOrd.get("CR_HLD_QTY");

            if (!SHPG_STS.CANCELLED.equals(shpgStsCd)) {
                if (SHPG_STS.SAVED.equals(shpgStsCd)) {
                    orderList.clear();
                    // WDS#110 START
                    //                    } else if (WH_TP.COMMON.equals(whTyp) && SHPG_STS.VALIDATED.equals(shpgStsCd)) {
                    //                        orderList.clear();
                    //                    } else if (SET_ITEM_PARENT_NUM.equals((String) crChkOrd.get("CPO_DTL_LINE_SUB_NUM"))) {
                    //                        orderList.clear();
                    // WDS#110 E N D
                    // Def#1481 Modify
                    //} else if (ordQty.subtract(crChkQty).compareTo(ZERO) <= 0) {
                } else if (ordQty.subtract(crChkQty).compareTo(ZERO) <= 0 || ordQty.subtract(crHldQty).compareTo(ZERO) <= 0) {
                    orderList.clear();
                } else {
                    crChkOrd.put("crChkQty", ordQty.subtract(crChkQty));
                    crChkOrd.put("whType", whTyp);
                    orderList.add(crChkOrd);
                }
            }
            // WDS#110 START
            //            }
            // WDS#110 E N D

            for (Map<String, Object> order : orderList) {
                order.put("whType", getWhType((String) order.get("GLBL_CMPY_CD"), (String) order.get("S_MDSE_CD"), (String) order.get("PO_REQ_FLG")));
            }

            // Def#2025 Add
            executeForUpdate(orderList);

            return orderList;

        } finally {

            if (orderList == null) {
                writePerformanceProfilingLog(getClass(), " - orderList=[null]");
            } else {
                writePerformanceProfilingLog(getClass(), " - orderList.size=[" + orderList.size() + "]");
            }

            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    // WDS#110 START
    //    /** Check Credit Check Order(Set Item) */
    //    @SuppressWarnings("unchecked")
    //    private List<Map<String, Object>> getCrChkOrderListBySet(SHPG_PLNTMsg shpgPlnMsg) {
    //
    //        final List<Map<String, Object>> ordList = new ArrayList<Map<String, Object>>();
    //        final List<List<Map<String, Object>>> wkList = new ArrayList<List<Map<String, Object>>>();
    //
    //        this.compCmpsnList = new HashMap<String, Object>();
    //
    //        // Component check
    //        Map<String, Object> setShpgPlnNumList = new HashMap<String, Object>();
    //        boolean shpgPlnNumSetFlg = true;
    //        boolean whTpFlg          = false;
    //
    //        if (existsHldSet(shpgPlnMsg, REL_PNT_CREDIT_CHECK)) {
    //            return ordList;
    //        }
    //
    //        String glblCmpyCd = shpgPlnMsg.glblCmpyCd.getValue();
    //        String trxHdrNum  = shpgPlnMsg.trxHdrNum.getValue();
    //        String trxLineNum = shpgPlnMsg.trxLineNum.getValue();
    //
    //        CPO_DTLTMsgArray cpoDtlMsgArray = getCompCpoDtlMsgArray(glblCmpyCd, trxHdrNum, trxLineNum);
    //
    //        BigDecimal crChkQtySet = getCreateSetQty(cpoDtlMsgArray, false, shpgPlnMsg);
    //        if (ZERO.compareTo(crChkQtySet) >= 0) {
    //            return ordList;
    //        }
    //
    //        for (int i = 0, end1 = cpoDtlMsgArray.getValidCount(); i < end1; i++) {
    //
    //            if (!setShpgPlnNumList.isEmpty()) {
    //                shpgPlnNumSetFlg = false;
    //            }
    //
    //            CPO_DTLTMsg cpoDtlMsg = (CPO_DTLTMsg) cpoDtlMsgArray.get(i);
    //
    //            final Map<String, String> ssmParam = new HashMap<String, String>();
    //            ssmParam.put("glblCmpyCd",    cpoDtlMsg.glblCmpyCd.getValue());
    //            ssmParam.put("trxHdrNum",     cpoDtlMsg.cpoOrdNum.getValue());
    //            ssmParam.put("trxLineNum",    cpoDtlMsg.cpoDtlLineNum.getValue());
    //            ssmParam.put("trxLineSubNum", cpoDtlMsg.cpoDtlLineSubNum.getValue());
    //            ssmParam.put("trxSrcTpCd",    TRX_SRC_TP.WHOLE_SALES);
    //            ssmParam.put("relPoint",      REL_PNT_CREDIT_CHECK);
    //            ssmParam.put("relFlg",        N);
    //            ssmParam.put("hldLvl03",      HLD_LVL.SHIPPING_PLAN_LEVEL);
    //
    //            // Def#2025 Delete
    ////            if (ONBATCH_TYPE.BATCH.equals(this.onBatchType)) {
    ////                ssmParam.put("wait", "1");
    ////            } else {
    ////                ssmParam.put("wait", "0");
    ////            }
    //
    ////            final List<Map<String, Object>> crChkListTmp = ssmClient.queryObjectList("getSetCrOrderRs", ssmParam);
    //            final List<Map<String, Object>> crChkListTmp = ssmClient.queryObjectList("getSetCrOrderRs", ssmParam, new S21SsmListResultSetHandlerSupport() {
    //                @Override
    //                protected List doProcessQueryResult(ResultSet rs) throws SQLException {
    //                    return toMapList(rs);
    //                }
    //            });
    //
    //            if (crChkListTmp.isEmpty()) {
    //                return null;
    //            }
    //
    //            // Def#2025 Add
    //            executeForUpdate(crChkListTmp);
    //
    //            BigDecimal compQty      = getCompQty(glblCmpyCd, cpoDtlMsg.setMdseCd.getValue(), cpoDtlMsg.mdseCd.getValue(), cpoDtlMsg.cpoOrdTs.getValue());
    //            BigDecimal crChkedQty   = (BigDecimal) ssmClient.queryObject("getCrChkQty", ssmParam);
    //            BigDecimal crChkNeedQty = crChkQtySet.multiply(compQty).subtract(crChkedQty);
    //
    //            this.compCmpsnList.put(cpoDtlMsg.cpoDtlLineSubNum.getValue(), compQty);
    //
    //            if (ZERO.compareTo(crChkNeedQty) < 0) {
    //
    //                for (Map<String, Object> compMap : crChkListTmp) {
    //
    //                    String mdseCd        = (String) compMap.get("S_MDSE_CD");
    //                    String poReqFlg      = (String) compMap.get("PO_REQ_FLG");
    //                    String shpgStsCd     = (String) compMap.get("SHPG_STS_CD");
    //                    String whTyp         = getWhType(glblCmpyCd, mdseCd, poReqFlg);
    //                    String setShpgPlnNum = (String) compMap.get("SET_SHPG_PLN_NUM");
    //
    //                    // Def#1481 Add
    //                    String setShpgPlnNumProc = shpgPlnMsg.setShpgPlnNum.getValue();
    //                    if (!setShpgPlnNumProc.equals(setShpgPlnNum)) {
    //                        continue;
    //                    }
    //
    //                    BigDecimal ordQty    = (BigDecimal) compMap.get("S_ORD_QTY");
    //                    BigDecimal crChkQty  = (BigDecimal) compMap.get("CR_CHK_QTY");
    //
    //                    if (WH_TP.COMMON.equals(whTyp)) {
    //                        whTpFlg = true;
    //                    }
    //
    //                    if (!SHPG_STS.CANCELLED.equals(shpgStsCd) || !SHPG_STS.SAVED.equals(shpgStsCd)) {
    //                        if ((WH_TP.COMMON.equals(whTyp) && !SHPG_STS.VALIDATED.equals(shpgStsCd))) {
    //
    //                            if (!hasValue(setShpgPlnNum)) {
    //                                continue;
    //                            }
    //
    //                            // 2010/05/18 Defect 5359
    //                            SHPG_PLNTMsg setShpgPlnMsg = new SHPG_PLNTMsg();
    //                            setValue(setShpgPlnMsg.glblCmpyCd, cpoDtlMsg.glblCmpyCd.getValue());
    //                            setValue(setShpgPlnMsg.shpgPlnNum, setShpgPlnNum);
    //                            SHPG_PLNTMsg setShpgPlnRcd = (SHPG_PLNTMsg) findByKey(setShpgPlnMsg);
    //
    //                            if (setShpgPlnRcd == null) {
    //                                continue;
    //                            } else if (SHPG_STS.VALIDATED.equals(setShpgPlnRcd.shpgStsCd.getValue())) {
    //                                continue;
    //                            }
    //
    //                            if (shpgPlnNumSetFlg || setShpgPlnNumList.containsKey(setShpgPlnNum)) {
    //                                if (ordQty.compareTo(crChkQty) > 0) {
    //                                    BigDecimal qty = ordQty.subtract(crChkQty);
    //                                    if (crChkNeedQty.compareTo(qty) <= 0) {
    //                                        compMap.put("crChkQty", crChkNeedQty);
    //                                    } else {
    //                                        compMap.put("crChkQty", qty);
    //                                    }
    //                                    crChkNeedQty = crChkNeedQty.subtract(qty);
    //                                    ordList.add(compMap);
    //                                    if (shpgPlnNumSetFlg) {
    //                                        setShpgPlnNumList.put(setShpgPlnNum, setShpgPlnNum);
    //                                    }
    //                                }
    //                            }
    //
    //                        } else if (!WH_TP.COMMON.equals(whTyp)) {
    //
    //                            wkList.add(crChkListTmp);
    //                            break;
    //                        }
    //                    }
    //
    //                    if (ZERO.compareTo(crChkNeedQty) >= 0) {
    //                        break;
    //                    }
    //                }
    //            }
    //        }
    //
    //        // VendorDrop Intangible
    //        if (!wkList.isEmpty()) {
    //
    //            String brforeTrxHdrNum     = "";
    //            String brforeTrxLineNum    = "";
    //            String brforeTrxLineSubNum = "";
    //
    //            for (List<Map<String, Object>> compList : wkList) {
    //
    //                BigDecimal crChkNeedQty = ZERO;
    //
    //                for (Map<String, Object> compMap : compList) {
    //
    //                    if (!brforeTrxHdrNum.equals((String) compMap.get("CPO_ORD_NUM")) || !brforeTrxLineNum.equals((String) compMap.get("CPO_DTL_LINE_NUM")) || !brforeTrxLineSubNum.equals((String) compMap.get("CPO_DTL_LINE_SUB_NUM"))) {
    //
    //                        final Map<String, String> ssmParam = new HashMap<String, String>();
    //                        ssmParam.put("glblCmpyCd",    glblCmpyCd);
    //                        ssmParam.put("trxSrcTpCd",    TRX_SRC_TP.WHOLE_SALES);
    //                        ssmParam.put("trxHdrNum",     (String) compMap.get("CPO_ORD_NUM"));
    //                        ssmParam.put("trxLineNum",    (String) compMap.get("CPO_DTL_LINE_NUM"));
    //                        ssmParam.put("trxLineSubNum", (String) compMap.get("CPO_DTL_LINE_SUB_NUM"));
    //
    //                        BigDecimal crChkedQty = (BigDecimal) ssmClient.queryObject("getCrChkQty", ssmParam);
    //
    //                        BigDecimal compQty = ZERO;
    //
    //                        compQty = (BigDecimal) this.compCmpsnList.get((String) compMap.get("CPO_DTL_LINE_SUB_NUM"));
    //
    //                        if (!hasValue(compQty)) {
    //                            compQty = getCompQty(glblCmpyCd, (String) compMap.get("CPO_ORD_NUM"), (String) compMap.get("CPO_DTL_LINE_NUM"), (String) compMap.get("CPO_DTL_LINE_SUB_NUM"));
    //                        }
    //
    //                        crChkNeedQty = crChkQtySet.multiply(compQty).subtract(crChkedQty);
    //
    //                        if (ZERO.compareTo(crChkNeedQty) > 0) {
    //                            break;
    //                        } else if (ZERO.compareTo(crChkNeedQty) == 0) {
    //                            break;
    //                        }
    //
    //                        brforeTrxHdrNum     = (String) compMap.get("CPO_ORD_NUM");
    //                        brforeTrxLineNum    = (String) compMap.get("CPO_DTL_LINE_NUM");
    //                        brforeTrxLineSubNum = (String) compMap.get("CPO_DTL_LINE_SUB_NUM");
    //
    //                    }
    //
    //                    BigDecimal ordQty    = (BigDecimal) compMap.get("S_ORD_QTY");
    //                    BigDecimal crChkQty  = (BigDecimal) compMap.get("CR_CHK_QTY");
    //                    String setShpgPlnNum = (String) compMap.get("SET_SHPG_PLN_NUM");
    //
    //                    if (!hasValue(setShpgPlnNum)) {
    //                        continue;
    //                    }
    //
    //                    if (setShpgPlnNumList.containsKey(setShpgPlnNum) || !whTpFlg) {
    //                        // !whTpFlg
    //                        // 1 When the setItem is composed only of the VenderDrop
    //                        // 2 When the setItem is composed only of the Intangible
    //
    //                        if (ordQty.compareTo(crChkQty) > 0) {
    //                            BigDecimal qty = ordQty.subtract(crChkQty);
    //                            if (crChkNeedQty.compareTo(qty) <= 0) {
    //                                compMap.put("crChkQty", crChkNeedQty);
    //                            } else {
    //                                compMap.put("crChkQty", qty);
    //                            }
    //                            crChkNeedQty = crChkNeedQty.subtract(qty);
    //                            ordList.add(compMap);
    //                        }
    //                    }
    //
    //                    if (ZERO.compareTo(crChkNeedQty) >= 0) {
    //                        break;
    //                    }
    //                }
    //            }
    //        }
    //
    //        if (!ordList.isEmpty()) {
    //
    //            final Map<String, String> ssmParam = new HashMap<String, String>();
    //            ssmParam.put("glblCmpyCd",    shpgPlnMsg.glblCmpyCd.getValue());
    //            ssmParam.put("trxHdrNum",     shpgPlnMsg.trxHdrNum.getValue());
    //            ssmParam.put("trxLineNum",    shpgPlnMsg.trxLineNum.getValue());
    //            ssmParam.put("trxLineSubNum", SET_ITEM_PARENT_NUM);
    //            ssmParam.put("trxSrcTpCd",    TRX_SRC_TP.WHOLE_SALES);
    //
    //            // Def#2025 Delete
    ////            if (ONBATCH_TYPE.BATCH.equals(this.onBatchType)) {
    ////                ssmParam.put("wait", "1");
    ////            } else {
    ////                ssmParam.put("wait", "0");
    ////            }
    //
    ////            final List<Map<String, Object>> crChkOrdList = ssmClient.queryObjectList("getCrChkOrderList", ssmParam);
    //            final List<Map<String, Object>> crChkOrdList = ssmClient.queryObjectList("getCrChkOrderList", ssmParam, new S21SsmListResultSetHandlerSupport() {
    //                @Override
    //                protected List doProcessQueryResult(ResultSet rs) throws SQLException {
    //                    return toMapList(rs);
    //                }
    //            });
    //
    //            if (!crChkOrdList.isEmpty()) {
    //                Map<String, Object> setMap = (Map<String, Object>) crChkOrdList.get(0);
    //                setMap.put("crChkQty", ZERO);
    //                ordList.add(setMap);
    //            }
    //
    //            // Def#2025 Add
    //            executeForUpdate(crChkOrdList);
    //        }
    //
    //        return ordList;
    //    }
    //
    //    @SuppressWarnings("unchecked")
    //    private List<Map<String, Object>> getCrChkOrderListByShipCplt(SHPG_PLNTMsg shpgPlnMsg) {
    //
    //        final List<Map<String, Object>> ordList = new ArrayList<Map<String, Object>>();
    //
    //        String glblCmpyCd = shpgPlnMsg.glblCmpyCd.getValue();
    //        String trxHdrNum  = shpgPlnMsg.trxHdrNum.getValue();
    //        String shipCpltCd = shpgPlnMsg.shipCpltCd.getValue();
    //
    //        final Map<String, String> ssmParam = new HashMap<String, String>();
    //        ssmParam.put("glblCmpyCd", glblCmpyCd);
    //        ssmParam.put("trxHdrNum",  trxHdrNum);
    //        ssmParam.put("shipCpltCd", shipCpltCd);
    //        ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
    //        ssmParam.put("RelPnt",     REL_PNT_CREDIT_CHECK);
    //        ssmParam.put("RelPntFlg",  Y);
    //        ssmParam.put("hldLvl01",   HLD_LVL.CPO_LEVEL);
    //        ssmParam.put("hldLvl02",   HLD_LVL.CPO_DETAIL_LEVEL);
    //        ssmParam.put("hldLvl03",   HLD_LVL.SHIPPING_PLAN_LEVEL);
    //        ssmParam.put("relFlg",     N);
    //
    //        final BigDecimal hldCntByShipCplt = (BigDecimal) ssmClient.queryObject("getHldCntByShipCplt", ssmParam);
    //        if (ZERO.compareTo(hldCntByShipCplt) < 0) {
    //            return ordList;
    //        }
    //
    //        ssmParam.clear();
    //        ssmParam.put("glblCmpyCd", glblCmpyCd);
    //        ssmParam.put("trxHdrNum",  trxHdrNum);
    //        ssmParam.put("shipCpltCd", shipCpltCd);
    //        ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
    //
    //        // Def#2025 Delete
    ////        if (ONBATCH_TYPE.BATCH.equals(this.onBatchType)) {
    ////            ssmParam.put("wait", "1");
    ////        } else {
    ////            ssmParam.put("wait", "0");
    ////        }
    //
    ////        final List<Map<String, Object>> crChkOrdList = ssmClient.queryObjectList("getCrChkOrderList", ssmParam);
    //        final List<Map<String, Object>> crChkOrdList = ssmClient.queryObjectList("getCrChkOrderList", ssmParam, new S21SsmListResultSetHandlerSupport() {
    //            @Override
    //            protected List doProcessQueryResult(ResultSet rs) throws SQLException {
    //                return toMapList(rs);
    //            }
    //        });
    //
    //        if (crChkOrdList.isEmpty()) {
    //            return null;
    //        }
    //
    //        // Def#2025 Add
    //        executeForUpdate(ordList);
    //
    //        boolean crChkAval = true;
    //        for (Map<String, Object> crChkOrd : crChkOrdList) {
    //
    //            String mdseCd       = (String) crChkOrd.get("S_MDSE_CD");
    //            String poReqFlg     = (String) crChkOrd.get("PO_REQ_FLG");
    //            String shpgStsCd    = (String) crChkOrd.get("SHPG_STS_CD");
    //            String whTyp        = getWhType(glblCmpyCd, mdseCd, poReqFlg);
    //            BigDecimal ordQty   = (BigDecimal) crChkOrd.get("S_ORD_QTY");
    //            BigDecimal crChkQty = (BigDecimal) crChkOrd.get("CR_CHK_QTY");
    //            BigDecimal crHldQty = (BigDecimal) crChkOrd.get("CR_HLD_QTY");
    //
    //            if (!SHPG_STS.CANCELLED.equals(shpgStsCd)) {
    //                if (SHPG_STS.SAVED.equals(shpgStsCd)) {
    //                    crChkAval = false;
    //                    break;
    //                } else if (WH_TP.COMMON.equals(whTyp) && SHPG_STS.VALIDATED.equals(shpgStsCd)) {
    //                    crChkAval = false;
    //                    break;
    //                } else if (SET_ITEM_PARENT_NUM.equals((String) crChkOrd.get("CPO_DTL_LINE_SUB_NUM"))) {
    //                    crChkOrd.put("crChkQty", ZERO);
    //                    ordList.add(crChkOrd);
    //                // Def#1481 Add
    //                } else if (ZERO.compareTo(crChkQty) != 0 || ZERO.compareTo(crHldQty) != 0) {
    //                    continue;
    //                } else {
    //                    crChkOrd.put("crChkQty", ordQty.subtract(crChkQty));
    //                    ordList.add(crChkOrd);
    //                }
    //            }
    //        }
    //
    //        if (!crChkAval) {
    //            ordList.clear();
    //        }
    //
    //        return ordList;
    //    }
    // WDS#110 E N D

    //    @SuppressWarnings("unchecked")
    //    private List<Map<String, Object>> getCrChkOrderListByUOM(SHPG_PLNTMsg shpgPlnTMsg) {
    //
    //        final List<Map<String, Object>> ordList = new ArrayList<Map<String, Object>>();
    //
    //        if (existsHld(shpgPlnTMsg, REL_PNT_CREDIT_CHECK)) {
    //            return ordList;
    //        }
    //
    //        final Map<String, String> ssmParam = new HashMap<String, String>();
    //        ssmParam.put("glblCmpyCd", shpgPlnTMsg.glblCmpyCd.getValue());
    //        ssmParam.put("shpgPlnNum", shpgPlnTMsg.shpgPlnNum.getValue());
    //        ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
    //        
    //        if (ONBATCH_TYPE.BATCH.equals(this.onBatchType)) {
    //            ssmParam.put("wait", "1");
    //        } else {
    //            ssmParam.put("wait", "0");
    //        }
    //
    //        final List<Map<String, Object>> crChkOrdList = (List<Map<String, Object>>) ssmClient.queryObjectList("getCrChkOrderList", ssmParam);
    //        if (crChkOrdList.isEmpty()) {
    //            return null;
    //        }
    //
    //        final Map<String, Object> crChkOrd = crChkOrdList.get(0);
    //        String shpgStsCd     = (String) crChkOrd.get("SHPG_STS_CD");
    //        String whTyp        = getWhType(shpgPlnTMsg.glblCmpyCd.getValue(), (String) crChkOrd.get("S_MDSE_CD"), (String) crChkOrd.get("PO_REQ_FLG"));
    //        BigDecimal ordQty   = (BigDecimal) crChkOrd.get("S_ORD_QTY");
    //        BigDecimal crChkQty = (BigDecimal) crChkOrd.get("CR_CHK_QTY");
    //        BigDecimal eachQty  = ONE;
    //        
    //        if (!SHPG_STS.CANCELLED.equals(shpgStsCd)) {
    //            
    //            if (SHPG_STS.SAVED.equals(shpgStsCd)) {
    //                ordList.clear();
    //            
    //            } else if (WH_TP.COMMON.equals(whTyp) && SHPG_STS.VALIDATED.equals(shpgStsCd)) {
    //                ordList.clear();
    //            
    //            } else if (SET_ITEM_PARENT_NUM.equals((String) crChkOrd.get("CPO_DTL_LINE_SUB_NUM"))) {
    //                ordList.clear();
    //
    //            } else {
    //                
    //                String uomCd = getUomCd(shpgPlnTMsg.glblCmpyCd.getValue(), CASE_UOM_CD);
    //
    //                if (hasValue(uomCd)) {
    //
    //                    final MDSE_STORE_PKGTMsg strPkgMsg = getMdseStorePkgMsg(shpgPlnTMsg.glblCmpyCd.getValue(), shpgPlnTMsg.mdseCd.getValue(), uomCd);
    //                    if (strPkgMsg != null) {
    //                        if (hasValue(strPkgMsg.inEachQty)) {
    //                            eachQty = strPkgMsg.inEachQty.getValue();
    //                        }
    //                    }
    //                }
    //                
    //                if (ZERO.compareTo(ordQty.subtract(crChkQty).divide(eachQty, 0, BigDecimal.ROUND_DOWN)) >= 0) {
    //                    ordList.clear();
    //                
    //                } else {
    //                    final BigDecimal qty = ordQty.subtract(crChkQty).divide(eachQty, 0, BigDecimal.ROUND_DOWN);
    //                    crChkOrd.put("crChkQty", qty.multiply(eachQty));
    //                    crChkOrd.put("whType",   whTyp);
    //                    ordList.add(crChkOrd);
    //                }
    //            }
    //        }
    //
    //        return ordList;
    //    }

    // WDS#110 START
    //    /** Get Create Set Available QTY 
    //     * @param shpgPlnMsg */
    //    @SuppressWarnings("unchecked")
    //    private BigDecimal getCreateSetQty(CPO_DTLTMsgArray cpoDtlMsgArray, boolean remHldQty, SHPG_PLNTMsg shpgPlnMsg) {
    //
    //        BigDecimal setQty = MAX_QTY;
    //
    //        for (int i = 0, end1 = cpoDtlMsgArray.getValidCount(); i < end1; i++) {
    //
    //            CPO_DTLTMsg cpoDtlMsg = (CPO_DTLTMsg) cpoDtlMsgArray.get(i);
    //
    //            String glblCmpyCd = cpoDtlMsg.glblCmpyCd.getValue();
    //
    //            BigDecimal compQty = getCompQty(glblCmpyCd, cpoDtlMsg.setMdseCd.getValue(), cpoDtlMsg.mdseCd.getValue(), cpoDtlMsg.cpoOrdTs.getValue());
    //
    //            Map<String, String> queryParam = new HashMap<String, String>();
    //            queryParam.put("glblCmpyCd", glblCmpyCd);
    //            queryParam.put("trxHdrNum", cpoDtlMsg.cpoOrdNum.getValue());
    //            queryParam.put("trxLineNum", cpoDtlMsg.cpoDtlLineNum.getValue());
    //            queryParam.put("trxLineSubNum", cpoDtlMsg.cpoDtlLineSubNum.getValue());
    //            queryParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
    //            queryParam.put("relPoint", REL_PNT_CREDIT_CHECK);
    //            queryParam.put("relFlg", N);
    //            queryParam.put("hldLvl03", HLD_LVL.SHIPPING_PLAN_LEVEL);
    //            // Def#2025 Delete
    ////            if (ONBATCH_TYPE.BATCH.equals(this.onBatchType)) {
    ////                queryParam.put("wait", "1");
    ////            } else {
    ////                queryParam.put("wait", "0");
    ////            }
    //
    ////            final List<Map<String, Object>> crChkListTmp = (List<Map<String, Object>>) ssmClient.queryObjectList("getSetCrOrderRs", queryParam);
    //            final List<Map<String, Object>> crChkListTmp = ssmClient.queryObjectList("getSetCrOrderRs", queryParam, new S21SsmListResultSetHandlerSupport() {
    //                @Override
    //                protected List doProcessQueryResult(ResultSet rs) throws SQLException {
    //                    return toMapList(rs);
    //                }
    //            });
    //
    //            // Def#2025 Add
    //            executeForUpdate(crChkListTmp);
    //
    //            if (crChkListTmp.isEmpty()) {
    //                setQty = ZERO;
    //                return setQty;
    //            }
    //
    //            BigDecimal compSoAvalQty = ZERO;
    //            BigDecimal compSoAvalQtySet = ZERO;
    //            String reqShpgPlnNum = shpgPlnMsg.shpgPlnNum.getValue();
    //
    //            for (Map<String, Object> compMap : crChkListTmp) {
    //
    //                String setShpgStsCd = (String) compMap.get("SHPG_STS_CD");
    //                String setMdseCode = (String) compMap.get("S_MDSE_CD");
    //                String setWhTyp = getWhType(glblCmpyCd, setMdseCode, (String) compMap.get("PO_REQ_FLG"));
    //                String shpgPlnNum = (String) compMap.get("SHPG_PLN_NUM");
    //
    //                if (!SHPG_STS.CANCELLED.equals(setShpgStsCd) && !SHPG_STS.SAVED.equals(setShpgStsCd)) {
    //                    if (WH_TP.COMMON.equals(setWhTyp) && !SHPG_STS.VALIDATED.equals(setShpgStsCd) || !WH_TP.COMMON.equals(setWhTyp)) {
    //                        if (remHldQty) {
    //                            compSoAvalQty = compSoAvalQty.add((BigDecimal) compMap.get("CR_CHK_QTY"));
    //                            BigDecimal crHldQty = (BigDecimal) compMap.get("CR_HLD_QTY");
    //                            BigDecimal slsHldQty = (BigDecimal) compMap.get("SLS_HLD_QTY");
    //                            // Def#1481 Modify
    //                            // if (slsHldQty.compareTo(crHldQty) < 0) {
    //                            if (slsHldQty.compareTo(crHldQty) < 0 && reqShpgPlnNum.equals(shpgPlnNum)) {
    //                                compSoAvalQty = compSoAvalQty.subtract(crHldQty);
    //                            } else {
    //                                compSoAvalQty = compSoAvalQty.subtract(slsHldQty);
    //                            }
    //                        } else {
    //                            compSoAvalQty = compSoAvalQty.add((BigDecimal) compMap.get("S_ORD_QTY"));
    //                        }
    //                    }
    //                }
    //            }
    //
    //            if (compSoAvalQty.compareTo(ZERO) <= 0) {
    //                setQty = ZERO;
    //                return setQty;
    //            }
    //
    //            if (compQty.compareTo(ZERO) == 0) {
    //                setQty = ZERO;
    //                return setQty;
    //            }
    //
    //            compSoAvalQtySet = compSoAvalQty.divide(compQty, 0, BigDecimal.ROUND_DOWN);
    //            if (compSoAvalQtySet.compareTo(ZERO) <= 0) {
    //                setQty = ZERO;
    //                return setQty;
    //            }
    //
    //            if (compSoAvalQtySet.compareTo(setQty) < 0) {
    //                setQty = compSoAvalQtySet;
    //            }
    //        }
    //
    //        return setQty;
    //    }
    // WDS#110 E N D

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getEachCompCrChkQty(final String glblCmpyCd, final String trxHdrNum, final String trxLineNum, final String trxSrcTpCd, final String shpgPlnNum) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxHdrNum", trxHdrNum);
        ssmParam.put("trxLineNum", trxLineNum);
        ssmParam.put("trxLineNumSet", SET_ITEM_PARENT_NUM);
        ssmParam.put("setShpgPlnNum", shpgPlnNum);
        ssmParam.put("trxSrcTpCd", trxSrcTpCd);

        return ssmClient.queryObjectList("queryEachCompCrChkQty", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getEachCompCrHldQty(final String glblCmpyCd, final String trxHdrNum, final String trxLineNum, final String trxSrcTpCd, final String setShpgPlnNum) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxHdrNum", trxHdrNum);
        ssmParam.put("trxLineNum", trxLineNum);
        ssmParam.put("trxSrcTpCd", trxSrcTpCd);
        ssmParam.put("setShpgPlnNum", setShpgPlnNum);
        ssmParam.put("trxLineNumSet", SET_ITEM_PARENT_NUM);

        return ssmClient.queryObjectList("queryEachCompCrHldQty", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<String> getHldOrderList(String glblCmpyCd, String cpoOrdNum) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);

        final Map<String, String> hldLvl = new HashMap<String, String>();
        ssmParam.put("hldLvl", hldLvl);
        hldLvl.put("cpo", HLD_LVL.CPO_LEVEL);
        hldLvl.put("cpoDtl", HLD_LVL.CPO_DETAIL_LEVEL);
        hldLvl.put("shpgPln", HLD_LVL.SHIPPING_PLAN_LEVEL);
        ssmParam.put("trxSrcTp", TRX_SRC_TP.WHOLE_SALES_RETURN); // QC#11306 2016/09/20 Add

        return ssmClient.queryObjectList("getHldOrderList", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getOrderList(NWZC004001PMsg param) {
        final String methodNm = "getOrderList";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        writePerformanceProfilingLog(getClass(), //
                " - CPO_ORD_NUM=[" + param.cpoOrdNum_I.getValue() + "], " //
                        + "CPO_DTL_LINE_NUM=[" + param.cpoDtlLineNum_I.getValue() + "], " //
                        + "CPO_DTL_LINE_SUB_NUM=[" + param.cpoDtlLineSubNum_I.getValue() + "], " //
                        + "SHPG_PLN_NUM=[" + param.shpgPlnNum_I.getValue() + "]" //
        );

        try {

            final Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
            ssmParam.put("cpoOrdNum_I", param.cpoOrdNum_I.getValue());
            ssmParam.put("cpoDtlLineNum_I", param.cpoDtlLineNum_I.getValue());
            ssmParam.put("cpoDtlLineSubNum_I", param.cpoDtlLineSubNum_I.getValue());
            ssmParam.put("shpgPlnNum_I", param.shpgPlnNum_I.getValue());

            // 2023/06/26 QC#61556 START
            // START 2023/03/23 A.Cullano [QC#61328, ADD]
            //ssmParam.put("ordLineCancelled", ORD_LINE_STS.CANCELLED);
            // END 2023/03/23 A.Cullano [QC#61328, ADD]
            // 2023/06/26 QC#61556 END

            // Def#2025 Delete
            //            if (ONBATCH_TYPE.BATCH.equals(this.onBatchType)) {
            //                ssmParam.put("wait", "1");
            //            } else {
            //                ssmParam.put("wait", "0");
            //            }

            //            final List<Map<String, Object>> orderList = ssmClient.queryObjectList("getOrderList", ssmParam);
            final List<Map<String, Object>> orderList = ssmClient.queryObjectList("getOrderList", ssmParam, new S21SsmListResultSetHandlerSupport() {
                @Override
                protected List doProcessQueryResult(ResultSet rs) throws SQLException {
                    return toMapList(rs);
                }
            });

            // WH
            for (Map<String, Object> order : orderList) {
                order.put("whType", getWhType((String) order.get("GLBL_CMPY_CD"), (String) order.get("S_MDSE_CD"), (String) order.get("PO_REQ_FLG")));
            }

            // Def#2025 Add
            executeForUpdate(orderList);

            writePerformanceProfilingLog(getClass(), " - orderList.size=[" + orderList.size() + "]");
            return orderList;

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private OrderValidator getOrderValidator() {

        if (orderValidator == null) {
            orderValidator = new OrderValidator();
        }
        return orderValidator;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getRecalcOrderList(NWZC004001PMsg param) {
        final String methodNm = "getRecalcOrderList";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        writePerformanceProfilingLog(getClass(), //
                " - CPO_ORD_NUM=[" + param.cpoOrdNum_I.getValue() + "], " //
                        + "CPO_DTL_LINE_NUM=[" + param.cpoDtlLineNum_I.getValue() + "], " //
                        + "CPO_DTL_LINE_SUB_NUM=[" + param.cpoDtlLineSubNum_I.getValue() + "], " //
                        + "SHPG_PLN_NUM=[" + param.shpgPlnNum_I.getValue() + "]" //
        );

        try {

            final Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
            // 2023/06/26 QC#61556 START
            // START 2023/03/23 A.Cullano [QC#61328, ADD]
            //ssmParam.put("ordLineCancelled", ORD_LINE_STS.CANCELLED);
            // END 2023/03/23 A.Cullano [QC#61328, ADD]
            // 2023/06/26 QC#61556 END

            // --------------------------------------------------
            // SHPG_PLN
            // --------------------------------------------------
            if (hasValue(param.shpgPlnNum_I)) {

                SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                setValue(shpgPlnTMsg.glblCmpyCd, param.glblCmpyCd);
                setValue(shpgPlnTMsg.shpgPlnNum, param.shpgPlnNum_I);

                shpgPlnTMsg = (SHPG_PLNTMsg) findByKey(shpgPlnTMsg);

                if (hasValue(shpgPlnTMsg.shipCpltCd)) {
                    ssmParam.put("cpoOrdNum_I", shpgPlnTMsg.trxHdrNum.getValue());
                    ssmParam.put("shipCpltCd", shpgPlnTMsg.shipCpltCd.getValue());
                } else if (hasValue(shpgPlnTMsg.setMdseCd) || SET_ITEM_PARENT_NUM.equals(shpgPlnTMsg.trxLineSubNum.getValue())) {
                    ssmParam.put("cpoOrdNum_I", shpgPlnTMsg.trxHdrNum.getValue());
                    ssmParam.put("cpoDtlLineNum_I", shpgPlnTMsg.trxLineNum.getValue());
                } else {
                    ssmParam.put("shpgPlnNum_I", shpgPlnTMsg.shpgPlnNum.getValue());
                }

                // --------------------------------------------------
                // CPO_DTL
                // --------------------------------------------------
            } else if (hasValue(param.cpoDtlLineSubNum_I)) {

                CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                setValue(cpoDtlTMsg.glblCmpyCd, param.glblCmpyCd);
                setValue(cpoDtlTMsg.cpoOrdNum, param.cpoOrdNum_I);
                setValue(cpoDtlTMsg.cpoDtlLineNum, param.cpoDtlLineNum_I);
                setValue(cpoDtlTMsg.cpoDtlLineSubNum, param.cpoDtlLineSubNum_I);

                cpoDtlTMsg = (CPO_DTLTMsg) findByKey(cpoDtlTMsg);

                if (hasValue(cpoDtlTMsg.shipCpltCd)) {
                    ssmParam.put("cpoOrdNum_I", cpoDtlTMsg.cpoOrdNum.getValue());
                    ssmParam.put("shipCpltCd", cpoDtlTMsg.shipCpltCd.getValue());
                } else if (hasValue(cpoDtlTMsg.setMdseCd) || SET_ITEM_PARENT_NUM.equals(cpoDtlTMsg.cpoDtlLineSubNum.getValue())) {
                    ssmParam.put("cpoOrdNum_I", cpoDtlTMsg.cpoOrdNum.getValue());
                    ssmParam.put("cpoDtlLineNum_I", cpoDtlTMsg.cpoDtlLineNum.getValue());
                } else {
                    ssmParam.put("cpoOrdNum_I", cpoDtlTMsg.cpoOrdNum.getValue());
                    ssmParam.put("cpoDtlLineNum_I", cpoDtlTMsg.cpoDtlLineNum.getValue());
                    ssmParam.put("cpoDtlLineSubNum_I", cpoDtlTMsg.cpoDtlLineSubNum.getValue());
                }

                // --------------------------------------------------
                // CPO
                // --------------------------------------------------
            } else {
                ssmParam.put("cpoOrdNum_I", param.cpoOrdNum_I.getValue());
            }

            // Def#2025 Delete
            //            if (ONBATCH_TYPE.BATCH.equals(this.onBatchType)) {
            //                ssmParam.put("wait", "1");
            //            } else {
            //                ssmParam.put("wait", "0");
            //            }

            //            final List<Map<String, Object>> orderList = ssmClient.queryObjectList("getOrderList", ssmParam);
            final List<Map<String, Object>> orderList = ssmClient.queryObjectList("getOrderList", ssmParam, new S21SsmListResultSetHandlerSupport() {
                @Override
                protected List doProcessQueryResult(ResultSet rs) throws SQLException {
                    return toMapList(rs);
                }
            });

            String cpoDtlLineSubNum = "";
            boolean isCompCmpsnListFlg = false;

            if (this.compCmpsnList == null) {
                this.compCmpsnList = new HashMap<String, Object>();
                isCompCmpsnListFlg = true;
            }

            for (Map<String, Object> order : orderList) {

                // WH
                order.put("whType", getWhType((String) order.get("GLBL_CMPY_CD"), (String) order.get("S_MDSE_CD"), (String) order.get("PO_REQ_FLG")));

                if (isCompCmpsnListFlg) {
                    if (!cpoDtlLineSubNum.equals((String) order.get("CPO_DTL_LINE_SUB_NUM"))) {
                        final String setMdseCd = (String) order.get("SET_MDSE_CD");
                        if (hasValue(setMdseCd)) {
                            // 2017/06/09 S21_NA#14685 Add Start
//                            final BigDecimal compQty = getCompQty(param.glblCmpyCd.getValue(), setMdseCd, (String) order.get("CD_MDSE_CD"), (String) order.get("CPO_ORD_TS"));
                            final BigDecimal compQty = getCompQty(param.glblCmpyCd.getValue(), (String) order.get("CPO_ORD_NUM"), (String) order.get("CPO_DTL_LINE_NUM"), (String) order.get("CPO_DTL_LINE_SUB_NUM"));
                            // 2017/06/09 S21_NA#14685 Add End
                            this.compCmpsnList.put((String) order.get("CPO_DTL_LINE_SUB_NUM"), compQty);
                            cpoDtlLineSubNum = (String) order.get("CPO_DTL_LINE_SUB_NUM");
                        }
                    }
                }
            }

            // Def#2025 Add
            executeForUpdate(orderList);

            writePerformanceProfilingLog(getClass(), " - orderList.size=[" + orderList.size() + "]");
            return orderList;

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    //WDS#110 START
    /** Get SO Available QTY (Ship Complete) */
    /*
     * @SuppressWarnings("unchecked") private BigDecimal
     * getScSoAvalQty(SHPG_PLNTMsg shpgPlnMsg) { final Map<String,
     * String> ssmParam = new HashMap<String, String>();
     * ssmParam.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
     * ssmParam.put("trxHdrNum", shpgPlnMsg.trxHdrNum.getValue());
     * ssmParam.put("shipCpltCd", shpgPlnMsg.shipCpltCd.getValue());
     * ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
     * ssmParam.put("RelPnt", REL_PNT_SO_CREATION);
     * ssmParam.put("RelPntFlg", Y); ssmParam.put("hldLvl01",
     * HLD_LVL.CPO_LEVEL); ssmParam.put("hldLvl02",
     * HLD_LVL.CPO_DETAIL_LEVEL); ssmParam.put("hldLvl03",
     * HLD_LVL.SHIPPING_PLAN_LEVEL); ssmParam.put("relFlg", N); //
     * BigDecimal scHldCnt = (BigDecimal)
     * ssmClient.queryObject("getscHldCntRs", ssmParam); BigDecimal
     * scHldCnt = (BigDecimal)
     * ssmClient.queryObject("getHldCntByShipCplt", ssmParam);
     * BigDecimal soAvalQty = shpgPlnMsg.crChkQty.getValue(); if
     * (ZERO.compareTo(scHldCnt) < 0) { soAvalQty = ZERO; return
     * soAvalQty; } ssmParam.clear(); ssmParam.put("glblCmpyCd",
     * shpgPlnMsg.glblCmpyCd.getValue()); ssmParam.put("trxHdrNum",
     * shpgPlnMsg.trxHdrNum.getValue()); ssmParam.put("shipCpltCd",
     * shpgPlnMsg.shipCpltCd.getValue()); ssmParam.put("trxSrcTpCd",
     * TRX_SRC_TP.WHOLE_SALES); // Def#2025 Delete // if
     * (ONBATCH_TYPE.BATCH.equals(this.onBatchType)) { //
     * ssmParam.put("wait", "1"); // } else { // ssmParam.put("wait",
     * "0"); // } // final List<Map<String, Object>> scList =
     * (List<Map<String, Object>>)
     * ssmClient.queryObjectList("getCrChkOrderList", ssmParam); final
     * List<Map<String, Object>> scList =
     * ssmClient.queryObjectList("getCrChkOrderList", ssmParam, new
     * S21SsmListResultSetHandlerSupport() {
     * @Override protected List doProcessQueryResult(ResultSet rs)
     * throws SQLException { return toMapList(rs); } }); // Def#2025
     * Add executeForUpdate(scList); for (Map<String, Object> scMap :
     * scList) { final String cpoDtlLineSubNum = (String)
     * scMap.get("CPO_DTL_LINE_SUB_NUM"); final String shpgStsCd =
     * (String) scMap.get("SHPG_STS_CD"); if
     * (!SET_ITEM_PARENT_NUM.equals(cpoDtlLineSubNum)) { if
     * (!SHPG_STS.CANCELLED.equals(shpgStsCd) &&
     * !SHPG_STS.SAVED.equals(shpgStsCd)) { final BigDecimal ordQty =
     * (BigDecimal) scMap.get("S_ORD_QTY"); final BigDecimal
     * crChkedQty = (BigDecimal) scMap.get("CR_CHK_QTY"); if
     * (ordQty.compareTo(crChkedQty) > 0) { soAvalQty = ZERO; break; }
     * } } } return soAvalQty; }
     */
    //WDS#110 E N D
    //WDS#110 START
    /** Get SO Available QTY (Set) */
    /*
     * @SuppressWarnings("unchecked") private BigDecimal
     * getSetSoAvalQty(SHPG_PLNTMsg shpgPlnMsg, String setAvalQty) {
     * BigDecimal soAvalQty = ZERO; if (existsHldSet(shpgPlnMsg,
     * REL_PNT_SO_CREATION)) { return soAvalQty; } // Def#1481 Add
     * soAvalQty = shpgPlnMsg.avalSoQty.getValue(); if
     * (ZERO.compareTo(soAvalQty) < 0) { return soAvalQty; } String
     * glblCmpyCd = shpgPlnMsg.glblCmpyCd.getValue(); String trxHdrNum
     * = shpgPlnMsg.trxHdrNum.getValue(); String trxLineNum =
     * shpgPlnMsg.trxLineNum.getValue(); CPO_DTLTMsgArray
     * cpoDtlMsgArray = getCompCpoDtlMsgArray(glblCmpyCd, trxHdrNum,
     * trxLineNum); BigDecimal soAvalQtySet =
     * getCreateSetQty(cpoDtlMsgArray, true, shpgPlnMsg); for (int i =
     * 0, end1 = cpoDtlMsgArray.getValidCount(); i < end1; i++) {
     * CPO_DTLTMsg cpoDtlMsg = (CPO_DTLTMsg) cpoDtlMsgArray.get(i); if
     * (cpoDtlMsg.cpoDtlLineSubNum.getValue().equals(shpgPlnMsg.
     * trxLineSubNum.getValue())) { Map<String, String> queryParam =
     * new HashMap<String, String>(); queryParam.put("glblCmpyCd",
     * cpoDtlMsg.glblCmpyCd.getValue()); queryParam.put("trxHdrNum",
     * cpoDtlMsg.cpoOrdNum.getValue()); queryParam.put("trxLineNum",
     * cpoDtlMsg.cpoDtlLineNum.getValue());
     * queryParam.put("trxLineSubNum",
     * cpoDtlMsg.cpoDtlLineSubNum.getValue());
     * queryParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES); //
     * Def#2025 Delete // if
     * (ONBATCH_TYPE.BATCH.equals(this.onBatchType)) { //
     * queryParam.put("wait", "1"); // } else { //
     * queryParam.put("wait", "0"); // } // final List<Map<String,
     * Object>> crChkListTmp = (List<Map<String, Object>>)
     * ssmClient.queryObjectList("getCrChkOrderList", queryParam);
     * final List<Map<String, Object>> crChkListTmp =
     * ssmClient.queryObjectList("getCrChkOrderList", queryParam, new
     * S21SsmListResultSetHandlerSupport() {
     * @Override protected List doProcessQueryResult(ResultSet rs)
     * throws SQLException { return toMapList(rs); } }); if
     * (crChkListTmp.isEmpty()) { return soAvalQty; } // Def#2025 Add
     * executeForUpdate(crChkListTmp); BigDecimal compQty =
     * getCompQty(glblCmpyCd, cpoDtlMsg.setMdseCd.getValue(),
     * cpoDtlMsg.mdseCd.getValue(), cpoDtlMsg.cpoOrdTs.getValue());
     * BigDecimal soCreatedQty = ZERO; BigDecimal soNeedQty =
     * soAvalQtySet.multiply(compQty); for (Map<String, Object> scMsg
     * : crChkListTmp) { String shpgStsCd = (String)
     * scMsg.get("SHPG_STS_CD"); if
     * (!shpgPlnMsg.shpgPlnNum.getValue().equals((String)
     * scMsg.get("SHPG_PLN_NUM"))) { if
     * (SET_SO_AVAL_QTY.equals(setAvalQty)) { if
     * (!SHPG_STS.CANCELLED.equals(shpgStsCd) &&
     * !SHPG_STS.SAVED.equals(shpgStsCd) &&
     * !SHPG_STS.VALIDATED.equals(shpgStsCd)) { if
     * (SET_SO_AVAL_QTY.equals(setAvalQty)) { soCreatedQty =
     * soCreatedQty.add((BigDecimal) scMsg.get("AVAL_SO_QTY")); } else
     * { soCreatedQty = soCreatedQty.add((BigDecimal)
     * scMsg.get("S_ORD_QTY")); } } } else if
     * (SET_SO_AVAL_QTY.equals(setAvalQty)) { if
     * (!SHPG_STS.CANCELLED.equals(shpgStsCd) &&
     * !SHPG_STS.SAVED.equals(shpgStsCd)) { if
     * (SHPG_STS.VALIDATED.equals(shpgStsCd)) { soCreatedQty =
     * soCreatedQty.add((BigDecimal) scMsg.get("AVAL_PO_QTY")); } else
     * { soCreatedQty = soCreatedQty.add((BigDecimal)
     * scMsg.get("S_ORD_QTY")); } } } } } soNeedQty =
     * soNeedQty.subtract(soCreatedQty); if (ZERO.compareTo(soNeedQty)
     * < 0) { BigDecimal crHldQty = shpgPlnMsg.crHldQty.getValue();
     * BigDecimal slsHldQty = shpgPlnMsg.slsHldQty.getValue();
     * BigDecimal hldQty = ZERO; if (slsHldQty.compareTo(crHldQty) <
     * 0) { hldQty = crHldQty; } else { hldQty = slsHldQty; } if
     * (soNeedQty
     * .compareTo(shpgPlnMsg.crChkQty.getValue().subtract(hldQty)) >
     * 0) { soAvalQty =
     * shpgPlnMsg.crChkQty.getValue().subtract(hldQty); } else {
     * soAvalQty = soNeedQty; } } break; } } return soAvalQty; }
     */
    //WDS#110 E N D
    private boolean isExistShipCpltHold(List<HLDTMsg> shipCpltHldList, String pCpoOrdNum, String pCpoDtlLineNum, String pCpoDtlLineSubNum) {

        for (HLDTMsg hldMsg : shipCpltHldList) {

            String cpoOrdNum = hldMsg.cpoOrdNum.getValue();
            String cpoDtlLineNum = hldMsg.cpoDtlLineNum.getValue();
            String cpoDtlLineSubNum = hldMsg.cpoDtlLineSubNum.getValue();

            if (cpoOrdNum.equals(pCpoOrdNum) && cpoDtlLineNum.equals(pCpoDtlLineNum) && cpoDtlLineSubNum.equals(pCpoDtlLineSubNum)) {
                return true;
            }
        }

        return false;
    }

    //WDS#110 START
    /**
     * get SetShpgPlnNum
     */
    /*
     * private SHPG_PLNTMsgArray
     * searchShpgPlnNumForUpdate(SHPG_PLNTMsg edtShpgPlnRec) { final
     * SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
     * shpgPlnTMsg.setSQLID("034");
     * shpgPlnTMsg.setConditionValue("glblCmpyCd01",
     * edtShpgPlnRec.glblCmpyCd.getValue());
     * shpgPlnTMsg.setConditionValue("trxHdrNum01",
     * edtShpgPlnRec.trxHdrNum.getValue());
     * shpgPlnTMsg.setConditionValue("trxLineNum01",
     * edtShpgPlnRec.trxLineNum.getValue());
     * shpgPlnTMsg.setConditionValue("trxLineSubNum01",
     * SET_ITEM_PARENT_NUM);
     * shpgPlnTMsg.setConditionValue("trxSrcTpCd01",
     * edtShpgPlnRec.trxSrcTpCd.getValue());
     * shpgPlnTMsg.setConditionValue("setShpgPlnNum01",
     * edtShpgPlnRec.setShpgPlnNum.getValue()); return
     * (SHPG_PLNTMsgArray) findByConditionForUpdate(shpgPlnTMsg); }
     */
    //WDS#110 E N D
    //WDS#110 START
    /** Update AvailableQty */
    /*
     * private void updateAvailableQty(final List<SHPG_PLNTMsg>
     * compList) { for (SHPG_PLNTMsg shpgPlnMap : compList) {
     * SHPG_PLNTMsgArray shpgPlnMsgArray =
     * searchShpgPlnNumForUpdate(shpgPlnMap); for (int i = 0, end =
     * shpgPlnMsgArray.getValidCount(); i < end; i++) { final
     * SHPG_PLNTMsg shpgPlnMsg = shpgPlnMsgArray.no(i); if
     * (SHPG_STS.HARD_ALLOCATED
     * .equals(shpgPlnMsg.shpgStsCd.getValue())) { if
     * (shpgPlnMsg.avalSoQty
     * .getValue().compareTo(shpgPlnMsg.crChkQty.getValue()) == 0) {
     * continue; } else {
     * shpgPlnMsg.avalSoQty.setValue(shpgPlnMsg.crChkQty.getValue());
     * } } else if (Y.equals(shpgPlnMsg.poReqFlg.getValue()) &&
     * SHPG_STS.VALIDATED.equals(shpgPlnMsg.shpgStsCd.getValue())) {
     * if
     * (shpgPlnMsg.avalPoQty.getValue().compareTo(shpgPlnMsg.crChkQty
     * .getValue()) == 0) { continue; } else {
     * shpgPlnMsg.avalPoQty.setValue(shpgPlnMsg.crChkQty.getValue());
     * } } // update update(shpgPlnMsg); } } }
     */
    //WDS#110 E N D
    // Def#1481 Add
    //    @SuppressWarnings("unchecked")
    private void addInProcAmt(NWZC004001PMsg param, String payerCustCd, String billToCustAcctCd, SHPG_PLNTMsg shpgPlnTMsg) { //20150924 mod

        BigDecimal inProcAmt = ZERO;
        if (!SET_ITEM_PARENT_NUM.equals(shpgPlnTMsg.trxLineSubNum.getValue()) && (ZERO.compareTo(shpgPlnTMsg.spFuncNetUnitPrcAmt.getValue()) < 0)) {
            final BigDecimal crChkQty = shpgPlnTMsg.ordQty.getValue();
            final BigDecimal unitPrice = shpgPlnTMsg.spFuncNetUnitPrcAmt.getValue();
            inProcAmt = inProcAmt.add(crChkQty.multiply(unitPrice));
        }

        if (MAX_AMT.compareTo(inProcAmt) < 0) {
            addXxMsgId(NWZM0148E);
            return;
        } else if (ZERO.compareTo(inProcAmt) >= 0) {
            return;
        }

        // -------------------------------------------------------
        // NMZC600001 : Credit Limit Validation API
        // -------------------------------------------------------
        NMZC600001PMsg crBalPMsg = new NMZC600001PMsg();
        setValue(crBalPMsg.xxModeCd, NMZC600001.MODE_ALL); // 20150924 add
        setValue(crBalPMsg.dsAcctNum, billToCustAcctCd); // 20150924 add

        setValue(crBalPMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(crBalPMsg.billToCustCd, payerCustCd);
        //        setValue(crBalPMsg.inProcAmt, inProcAmt); // 20150924 del
        setValue(crBalPMsg.updKeyNum, shpgPlnTMsg.trxHdrNum);
        setValue(crBalPMsg.slsDt, param.slsDt);

        ((NMZC600001) getAPI(NMZC600001.class)).execute(crBalPMsg, onBatchType);
        // 20150924 mod start
        //        writePerformanceProfilingLog(getClass(), "    #call NMZC600001 : Credit Limit Validation API. inProcAmt=[" + crBalPMsg.inProcAmt.getValue() + "], crBalAmt=[" + crBalPMsg.crBalAmt.getValue() + "]");
        writePerformanceProfilingLog(getClass() //
                , "    #call NMZC600001 : Credit Limit Validation API. Account Number=[" //
                        + crBalPMsg.dsAcctNum.getValue() //
                        + "], Bill To Cust Code=[" //
                        + crBalPMsg.billToCustCd.getValue() + "]");
        // 20150924 mod end

        // has API errors?
        final int apiErrMsgIdList = crBalPMsg.xxMsgIdList.getValidCount();
        if (apiErrMsgIdList > 0) {
            for (int i = 0; i < apiErrMsgIdList; i++) {
                addXxMsgId(crBalPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return;
        }
    }

    //WDS#110 START
    /*
     * private void updateAvalQty(List<Map<String, Object>> orderList)
     * { final String methodNm = "updateAvalQty";
     * writePerformanceProfilingLogStart(getClass(), methodNm); try {
     * if (orderList.isEmpty()) { return; } final Map<String, Object>
     * compShpgPlnMap = new HashMap<String, Object>(); final
     * List<SHPG_PLNTMsg> compList = new ArrayList<SHPG_PLNTMsg>();
     * for (Map<String, Object> order : orderList) { final String
     * glblCmpyCd = (String) order.get("GLBL_CMPY_CD"); final String
     * shpgPlnNum = (String) order.get("SHPG_PLN_NUM"); // lock
     * [SHPG_PLN] SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
     * setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
     * setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum); shpgPlnTMsg =
     * (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg); if
     * (SHPG_STS.CANCELLED.equals(shpgPlnTMsg.shpgStsCd.getValue())) {
     * continue; } final BigDecimal oldAvalSoQty =
     * shpgPlnTMsg.avalSoQty.getValue(); final BigDecimal oldAvalPoQty
     * = shpgPlnTMsg.avalPoQty.getValue(); final String shpgStsCdCd =
     * shpgPlnTMsg.shpgStsCd.getValue(); final String trxLineSubNum =
     * shpgPlnTMsg.trxLineSubNum.getValue(); final String setMdseCd =
     * shpgPlnTMsg.setMdseCd.getValue(); final String shipCpltCd =
     * shpgPlnTMsg.shipCpltCd.getValue(); final String whType =
     * (String) order.get("whType"); final String setShpgPlnNum =
     * shpgPlnTMsg.setShpgPlnNum.getValue(); final BigDecimal crHldQty
     * = shpgPlnTMsg.crHldQty.getValue(); final BigDecimal slsHldQty =
     * shpgPlnTMsg.slsHldQty.getValue(); final BigDecimal ordQty =
     * shpgPlnTMsg.ordQty.getValue(); final BigDecimal hldQty; if
     * (crHldQty.compareTo(slsHldQty) < 0) { hldQty = slsHldQty; }
     * else { hldQty = crHldQty; } if (WH_TP.COMMON.equals(whType) &&
     * SHPG_STS.HARD_ALLOCATED.equals(shpgStsCdCd)) { // Def#1481
     * Delete //if (ZERO.compareTo(crCheckQty) < 0) { if
     * (hasValue(shipCpltCd)) { setValue(shpgPlnTMsg.avalSoQty,
     * getScSoAvalQty(shpgPlnTMsg)); } else if (hasValue(setMdseCd)) {
     * if (!hasValue(setShpgPlnNum)) { continue; } // 2010/05/18
     * Defect 5359 SHPG_PLNTMsg setShpgPlnTMsg = new SHPG_PLNTMsg();
     * setValue(setShpgPlnTMsg.glblCmpyCd,
     * shpgPlnTMsg.glblCmpyCd.getValue());
     * setValue(setShpgPlnTMsg.shpgPlnNum, setShpgPlnNum);
     * setShpgPlnTMsg = (SHPG_PLNTMsg) findByKey(setShpgPlnTMsg); if
     * (setShpgPlnTMsg == null) { continue; } else if
     * (SHPG_STS.VALIDATED
     * .equals(setShpgPlnTMsg.shpgStsCd.getValue())) { continue; }
     * setValue(shpgPlnTMsg.avalSoQty, getSetSoAvalQty(shpgPlnTMsg,
     * SET_SO_AVAL_QTY)); // Def#1481 Modify //} else if
     * (crCheckQty.compareTo(hldQty) <= 0) { } else if
     * (ordQty.compareTo(hldQty) <= 0) {
     * setValue(shpgPlnTMsg.avalSoQty, ZERO); } else { // Def#1481
     * Modify //setValue(shpgPlnTMsg.avalSoQty,
     * crCheckQty.subtract(hldQty)); setValue(shpgPlnTMsg.avalSoQty,
     * ordQty); } // updateSelectionField final boolean
     * isAvalSoQtyChanged =
     * !nullToZero(oldAvalSoQty).equals(nullToZero
     * (shpgPlnTMsg.avalSoQty.getValue())); // AVAL_SO_QTY if
     * (isAvalSoQtyChanged) { writePerformanceProfilingLog(getClass(),
     * " #update AVAL_SO_QTY. shpgPlnNum=[" + shpgPlnNum +
     * "], avalSoQty=[" + oldAvalSoQty + "]->[" +
     * shpgPlnTMsg.avalSoQty.getValue() + "]");
     * updateSelectionField(shpgPlnTMsg, new String[] {"avalSoQty" });
     * } // Def#1481 Delete // } } else if
     * (WH_TP.VENDOR.equals(whType) &&
     * SHPG_STS.VALIDATED.equals(shpgStsCdCd)) { if
     * (!SET_ITEM_PARENT_NUM.equals(trxLineSubNum)) { // Def#1481
     * Delete //if (ZERO.compareTo(crCheckQty) < 0) { if
     * (hasValue(shipCpltCd)) { setValue(shpgPlnTMsg.avalPoQty,
     * getScSoAvalQty(shpgPlnTMsg)); } else if (hasValue(setMdseCd)) {
     * setValue(shpgPlnTMsg.avalPoQty, getSetSoAvalQty(shpgPlnTMsg,
     * SET_PO_AVAL_QTY)); // Def#1481 Modify //} else if
     * (crCheckQty.compareTo(hldQty) <= 0) { } else if
     * (ordQty.compareTo(hldQty) <= 0) {
     * setValue(shpgPlnTMsg.avalPoQty, ZERO); } else { // Def#1481
     * Modify //setValue(shpgPlnTMsg.avalPoQty,
     * crCheckQty.subtract(hldQty)); setValue(shpgPlnTMsg.avalPoQty,
     * ordQty); } // updateSelectionField final boolean
     * isAvalPoQtyChanged =
     * !nullToZero(oldAvalPoQty).equals(nullToZero
     * (shpgPlnTMsg.avalPoQty.getValue())); // AVAL_PO_QTY if
     * (isAvalPoQtyChanged) { writePerformanceProfilingLog(getClass(),
     * " #update AVAL_PO_QTY. shpgPlnNum=[" + shpgPlnNum +
     * "], avalPoQty=[" + oldAvalPoQty + "]->[" +
     * shpgPlnTMsg.avalPoQty.getValue() + "]");
     * updateSelectionField(shpgPlnTMsg, new String[] {"avalPoQty" });
     * } // Def#1481 Delete // } } } // 2010/04/30 Defect 5359 if
     * (hasValue(setMdseCd) && hasValue(setShpgPlnNum)) { if
     * (ZERO.compareTo(shpgPlnTMsg.avalSoQty.getValue()) < 0 ||
     * ZERO.compareTo(shpgPlnTMsg.avalPoQty.getValue()) < 0) { if
     * (compShpgPlnMap.containsKey(setShpgPlnNum)) { continue; } else
     * { compShpgPlnMap.put(setShpgPlnNum, setShpgPlnNum);
     * compList.add(shpgPlnTMsg); } } } } if (!compList.isEmpty()) {
     * updateAvailableQty(compList); } } finally {
     * writePerformanceProfilingLogEnd(getClass(), methodNm); } }
     */
    //WDS#110 E N D
    private void updateCrChkQty(NWZC004001PMsg param, List<Map<String, Object>> orderList) {
        final String methodNm = "updateCrChkQty";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            final List<Map<String, Object>> compList = new ArrayList<Map<String, Object>>();
            final Map<String, Object> compShpgPlnMap = new HashMap<String, Object>();

            String payerCustCd = null;
            String trxHdrNum = null; //20150924 add

            for (Map<String, Object> order : orderList) {

                final String glblCmpyCd = (String) order.get("GLBL_CMPY_CD");
                final String shpgPlnNum = (String) order.get("SHPG_PLN_NUM");

                // lock [SHPG_PLN]
                SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
                setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
                shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);

                //WDS#110
                //If payment method is CreditCard then this record shoud be skipped.

                // START 2013/02/20 S.Iidaka [WDS#Def 713 DEL]
                //                final String dsPmtMethCd = (String) order.get("DS_PMT_METH_CD");
                //                if (DS_PMT_METH_CREDIT_CARD.equals(dsPmtMethCd)) {
                //                    continue;
                //                }
                // E N D 2013/02/20 S.Iidaka [WDS#Def 713 DEL]

                if (SHPG_STS.CANCELLED.equals(shpgPlnTMsg.shpgStsCd.getValue())) {
                    continue;
                }

                trxHdrNum = shpgPlnTMsg.trxHdrNum.getValue(); //20150924 add
                if (!SET_ITEM_PARENT_NUM.equals((String) order.get("CPO_DTL_LINE_SUB_NUM"))) {

                    // Def#1481 Modify
                    final BigDecimal crHldQty = shpgPlnTMsg.crHldQty.getValue();
                    final BigDecimal ordQty = shpgPlnTMsg.ordQty.getValue();
                    final BigDecimal oldCrChkQty = shpgPlnTMsg.crChkQty.getValue();
                    //WDS#110 START
                    //                    final String whType         = (String) order.get("whType");
                    //                    final String shpgStsCdCd    = shpgPlnTMsg.shpgStsCd.getValue();
                    //                    final String invtyLocCd     = shpgPlnTMsg.invtyLocCd.getValue();
                    //WDS#110 E N D
                    payerCustCd = (String) order.get("PAYER_CUST_CD");
                    //WDS#110 START
                    //                    final String shipCpltCd     = shpgPlnTMsg.shipCpltCd.getValue();
                    //WDS#110 E N D
                    final String billToCustAcctCd = (String) order.get("BILL_TO_CUST_ACCT_CD"); // 20150924 add
                    final String setMdseCd = shpgPlnTMsg.setMdseCd.getValue();
                    final String setShpgPlnNum = shpgPlnTMsg.setShpgPlnNum.getValue();
                    final BigDecimal oldCrHldQty = (BigDecimal) order.get("CR_HLD_QTY");

                    /*
                     * if (ZERO.compareTo(oldCrChkQty) < 0) {
                     * continue; } if
                     * (ZERO.compareTo(shpgPlnTMsg.slsHldQty
                     * .getValue()) < 0 &&
                     * ZERO.compareTo(shpgPlnTMsg.crHldQty.getValue())
                     * >= 0 && ZERO.compareTo(oldCrHldQty) >= 0) {
                     * continue; }
                     */
                    if (ZERO.compareTo(oldCrHldQty) < 0) {
                        if (ZERO.compareTo(shpgPlnTMsg.crHldQty.getValue()) < 0) {
                            continue;
                        }
                    } else {
                        if (ZERO.compareTo(shpgPlnTMsg.slsHldQty.getValue()) < 0) {
                            if (existsHldSet(shpgPlnTMsg, REL_PNT_CREDIT_CHECK)) {
                                continue;
                            }
                        }
                    }

                    //WDS#110 START
                    //                    if ((WH_TP.COMMON.equals(whType) && SHPG_STS.HARD_ALLOCATED.equals(shpgStsCdCd))
                    //                        || (WH_TP.VENDOR.equals(whType) && SHPG_STS.VALIDATED.equals(shpgStsCdCd))
                    //                        || (!hasValue(invtyLocCd) && SHPG_STS.VALIDATED.equals(shpgStsCdCd))) {
                    //WDS#110 E N D

                    if (ordQty.compareTo(crHldQty) <= 0) {
                        setValue(shpgPlnTMsg.crChkQty, ZERO);

                        //WDS#110 START
                        //                    } else if (hasValue(shipCpltCd)) {
                        //                        setValue(shpgPlnTMsg.crChkQty, getCrChkQty(shpgPlnTMsg));
                        //WDS#110 E N D

                    } else if (hasValue(setMdseCd)) {

                        if (!hasValue(setShpgPlnNum)) {
                            continue;
                        }

                        if (existsHldSet(shpgPlnTMsg, REL_PNT_CREDIT_CHECK)) {
                            continue;
                        }
                        // ********** [# 351571] - START
                        //                            if (WH_TP.COMMON.equals(whType) && SHPG_STS.HARD_ALLOCATED.equals(shpgStsCdCd)) {
                        // ********** [# 351571] - END
                        SHPG_PLNTMsg setShpgPlnTMsg = new SHPG_PLNTMsg();
                        setValue(setShpgPlnTMsg.glblCmpyCd, shpgPlnTMsg.glblCmpyCd.getValue());
                        setValue(setShpgPlnTMsg.shpgPlnNum, setShpgPlnNum);

                        setShpgPlnTMsg = (SHPG_PLNTMsg) findByKey(setShpgPlnTMsg);

                        if (setShpgPlnTMsg == null) {
                            continue;
                            // START 01/17/2013 S.Iidaka [WDSDefect#201 DEL]
                            //                        } else if (SHPG_STS.VALIDATED.equals(setShpgPlnTMsg.shpgStsCd.getValue())) {
                            // E N D 01/17/2013 S.Iidaka [WDSDefect#201 DEL]
                            // ********** [# 351571] - START
                            //                                    if (ZERO.compareTo(oldCrHldQty) == 0) {
                            //                                        continue;
                            //                                    }
                            // START 01/17/2013 S.Iidaka [WDSDefect#201 DEL]
                            //
                            //                            if (!isIncludeValidatedCompCommonWH(setShpgPlnNum, orderList)) {
                            //                                continue;
                            //                            }
                            // E N D 01/17/2013 S.Iidaka [WDSDefect#201 DEL]

                            // ********** [# 351571] - END
                        }
                        // ********** [# 351571] - START
                        //                            }
                        // ********** [# 351571] - END
                        // START 09/05/2013 A.Suda [WDSDefect#1242 MOD]
                        if (ZERO.compareTo(oldCrChkQty) == 0 && ZERO.compareTo(crHldQty) == 0 && !existsSlsHld(shpgPlnTMsg)) {
                            // END 09/05/2013 A.Suda [WDSDefect#1242 MOD]
                            setValue(shpgPlnTMsg.crChkQty, ordQty);
                        }

                    } else {
                        // START 09/05/2013 A.Suda [WDSDefect#1242 MOD]
                        if (ZERO.compareTo(oldCrChkQty) == 0 && ZERO.compareTo(crHldQty) == 0 && !existsSlsHld(shpgPlnTMsg)) {
                            // END 09/05/2013 A.Suda [WDSDefect#1242 MOD]
                            setValue(shpgPlnTMsg.crChkQty, ordQty);
                        }
                    }
                    //WDS#110 START
                    //                    }
                    //WDS#110 E N D

                    //final BigDecimal crChkQtyOld = (BigDecimal) order.get("CR_CHK_QTY");
                    //final BigDecimal crChkQty    = (BigDecimal) order.get("crChkQty");

                    //shpgPlnTMsg = new SHPG_PLNTMsg();
                    //setValue(shpgPlnTMsg.glblCmpyCd, (String) order.get("GLBL_CMPY_CD"));
                    //setValue(shpgPlnTMsg.shpgPlnNum, (String) order.get("SHPG_PLN_NUM"));
                    //setValue(shpgPlnTMsg.crChkQty,   crChkQtyOld.add(crChkQty));

                    final boolean isCrChkQtyChanged = !nullToZero(oldCrChkQty).equals(nullToZero(shpgPlnTMsg.crChkQty.getValue())); // CR_CHK_QTY
                    // ********** [# 351571] - START
                    final boolean isPlusCrChkQty = nullToZero(oldCrChkQty).compareTo(nullToZero(shpgPlnTMsg.crChkQty.getValue())) == -1;
                    if (isCrChkQtyChanged && isPlusCrChkQty) {
                        //                    if (isCrChkQtyChanged) {
                        // ********** [# 351571] - END
                        // ***** updateSelectionField
                        writePerformanceProfilingLog(getClass(), " #update CR_CHK_QTY. shpgPlnNum=[" + shpgPlnTMsg.shpgPlnNum.getValue() + "], crChkQty=[" + oldCrChkQty + "]->[" + shpgPlnTMsg.crChkQty.getValue() + "]");
                        updateSelectionField(shpgPlnTMsg, new String[] {"crChkQty" });
                        // Def#1481 Add
                        // ***** add inproc amt
                        //QC#14970 del Start
//                        if (isUpdateInProcAmt(order, shpgPlnTMsg)) {
//                            addInProcAmt(param, payerCustCd, billToCustAcctCd, shpgPlnTMsg); // 20150924 mod
//                        }
                        //QC#14970 del End
                    }
                }

                // 2010/04/30 Defect 5359
                final String setMdseCd = (String) order.get("SET_MDSE_CD");
                final String setShpgPlnNum = (String) order.get("SET_SHPG_PLN_NUM");

                if (hasValue(setMdseCd) && hasValue(setShpgPlnNum)) {
                    // Def#1481 Add
                    if (ZERO.compareTo(shpgPlnTMsg.crChkQty.getValue()) < 0) {
                        if (compShpgPlnMap.containsKey(setShpgPlnNum)) {
                            continue;
                        } else {
                            compShpgPlnMap.put(setShpgPlnNum, setShpgPlnNum);
                            compList.add(order);
                        }
                    }
                }
            }

            if (!compList.isEmpty()) {
                updateCrChkQtySet(compList);
            }
            updateOrderBook(param, trxHdrNum); //20150924 add

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    //20150924 add
    private void updateOrderBook(NWZC004001PMsg param, String trxHdrNum) {
        if (!ZYPCommonFunc.hasValue(trxHdrNum)) {
            return;
        }

        if (!isBookHoldOnly(param, trxHdrNum)) {
            return;
        }
        releaseBookHold(param, trxHdrNum);
    }

    // 20150924 add
    private void releaseBookHold(NWZC004001PMsg param, String trxHdrNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.clear();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", trxHdrNum);
        ssmParam.put("hldRsnCd", HLD_RSN.BOOK);
        @SuppressWarnings("unchecked")
        final List<HLDTMsg> bookHldList = (List<HLDTMsg>) ssmClient.queryObjectList("getHold", ssmParam);
        if (bookHldList == null || bookHldList.size() == 0) {
            return;
        }
        boolean hasError = false;
        for (HLDTMsg tMsg : bookHldList) {

            tMsg = (HLDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
            if (tMsg == null) {
                continue;
            }
            // S21_NA#2279 start
            // ZYPEZDItemValueSetter.setValue(tMsg.relPsnCd,
            // getPsnCd());
            // ZYPEZDItemValueSetter.setValue(tMsg.relFlg,
            // ZYPConstant.FLG_ON_Y);
            // ZYPEZDItemValueSetter.setValue(tMsg.relDt,
            // param.slsDt);
            // S21ApiTBLAccessor.updateSelectionField(tMsg, new
            // String[] {"relPsnCd", "relFlg", "relDt" });

            final NWZC033001PMsg pMsg = new NWZC033001PMsg();
            setValue(pMsg.glblCmpyCd, param.glblCmpyCd.getValue());
            setValue(pMsg.slsDt, param.slsDt.getValue());
            setValue(pMsg.hldPk, tMsg.hldPk);
            setValue(pMsg.cpoOrdNum, tMsg.cpoOrdNum);
            setValue(pMsg.cpoDtlLineNum, tMsg.cpoDtlLineNum);
            setValue(pMsg.cpoDtlLineSubNum, tMsg.cpoDtlLineSubNum);
            setValue(pMsg.shpgPlnNum, tMsg.shpgPlnNum);
            setValue(pMsg.hldRelRsnCd, HLD_REL_RSN.AUTO);
            NWZC033001 relHoldApi = new NWZC033001();
            relHoldApi.execute(pMsg, this.onBatchType);

            final int apiMessageCount = pMsg.xxMsgIdList.getValidCount();
            if (apiMessageCount > 0) {
                for (int i = 0; i < apiMessageCount; i++) {
                    addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    if (pMsg.xxMsgIdList.no(i).xxMsgId.getValue().endsWith("E")) {
                        hasError = true;
                    }
                }
            }
            // S21_NA#2279 end
        }
        if (!hasError) { // S21_NA#2279 start

            // updateUnholdCpo(param, trxHdrNum); // S21_NA#2279
            updateBookCpo(param, trxHdrNum);
            updateRtrnLineSts(param, trxHdrNum);
            // 2018/11/05 S21_NA#29015 Add Start
            insBizProcLogBooked(trxHdrNum);
            // 2018/11/05 S21_NA#29015 Add End

            // 2018/12/12 S21_NA#29488 Add Start
            // MDSE(INVTY_CTRL_FLG = 'N' & INSTL_BASE_CTRL_FLG = 'Y') Only -> [Arrived Waiting For Installation] Hold ON
            createAwaitingInstlHld(param, trxHdrNum);
            // 2018/12/12 S21_NA#29488 Add End

            // Add Start 2019/08/30 QC#53016
            addHldByValSetTgt(param);
            // Add End 2019/08/30 QC#53016
        }
    }

    // 2017/05/08 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add Start
    private void callOrderDisplayStatusUpdAPI(NWZC004001PMsg param, String trxHdrNum, List<String> shpgPlnNumList, boolean rtrnLineFlg) {

        DS_CPO_RTRN_DTLTMsgArray dsCpoRtrnDtlTMsgArray = null;
        if (rtrnLineFlg) {
            DS_CPO_RTRN_DTLTMsg condDsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
            condDsCpoRtrnDtlTMsg.setSQLID("001");
            condDsCpoRtrnDtlTMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            condDsCpoRtrnDtlTMsg.setConditionValue("cpoOrdNum01", trxHdrNum);

            dsCpoRtrnDtlTMsgArray = (DS_CPO_RTRN_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(condDsCpoRtrnDtlTMsg);
        }

        NWZC188001PMsg stsUpdApiParam = new NWZC188001PMsg();
        stsUpdApiParam.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        stsUpdApiParam.cpoOrdNum.setValue(trxHdrNum);
        if (shpgPlnNumList != null && !shpgPlnNumList.isEmpty()) {
            int i = 0;
            for (String shpgPlnNum : shpgPlnNumList) {
                stsUpdApiParam.shpgPlnNumList.no(i).shpgPlnNum.setValue(shpgPlnNum);
                i++;
            }
            stsUpdApiParam.shpgPlnNumList.setValidCount(i);
        }
        if (dsCpoRtrnDtlTMsgArray != null && dsCpoRtrnDtlTMsgArray.getValidCount() > 0) {
            int i = 0;
            while (i < dsCpoRtrnDtlTMsgArray.getValidCount()) {
                stsUpdApiParam.rmaLineList.no(i).dsCpoRtrnLineNum.setValue(dsCpoRtrnDtlTMsgArray.no(i).dsCpoRtrnLineNum.getValue());
                stsUpdApiParam.rmaLineList.no(i).dsCpoRtrnLineSubNum.setValue(dsCpoRtrnDtlTMsgArray.no(i).dsCpoRtrnLineSubNum.getValue());
                i++;
            }
            stsUpdApiParam.rmaLineList.setValidCount(i);
        }

        if (stsUpdApiParam.shpgPlnNumList.getValidCount() > 0 || stsUpdApiParam.rmaLineList.getValidCount() > 0) {
            List<NWZC188001PMsg> stsUpdApiParamList = new ArrayList<NWZC188001PMsg>();
            stsUpdApiParamList.add(stsUpdApiParam);
            
            new NWZC188001().execute(stsUpdApiParamList, onBatchType);
        }
    }
    // 2017/05/08 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add End
    //20150924 add
    private void updateRtrnLineSts(NWZC004001PMsg param, String trxHdrNum) {
        //  2016/01/07 S21_NA#2588 Mod Start
//        DS_CPO_RTRN_DTLTMsg tMsg = new DS_CPO_RTRN_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, trxHdrNum);
//        tMsg = (DS_CPO_RTRN_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
//        if (tMsg == null) {
//            return;
//        }
//        if (!isActive(tMsg.rtrnLineStsCd.getValue())) {
//            return;
//        }
//        ZYPEZDItemValueSetter.setValue(tMsg.rtrnLineStsCd, RTRN_LINE_STS.BOOKED);
//        S21ApiTBLAccessor.updateSelectionField(tMsg, new String[] {"rtrnLineStsCd" });
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", trxHdrNum);
        List<String> omitRtrnLineStsCdList = new ArrayList<String>();
        omitRtrnLineStsCdList.add(RTRN_LINE_STS.CLOSED);
        omitRtrnLineStsCdList.add(RTRN_LINE_STS.CANCELLED);
        //S21_NA#11546 START
        omitRtrnLineStsCdList.add(RTRN_LINE_STS.RWS_CANCELLED);
        omitRtrnLineStsCdList.add(RTRN_LINE_STS.RWS_CREATED);
        omitRtrnLineStsCdList.add(RTRN_LINE_STS.PARTIALLY_RECEIVED);
        omitRtrnLineStsCdList.add(RTRN_LINE_STS.RECEIVED);
        //S21_NA#11546 END
        ssmParam.put("omitRtrnLineStsCdList", omitRtrnLineStsCdList.toArray(new String[0]));

        List<DS_CPO_RTRN_DTLTMsg> dsCpoRtrnDtlTMsgList = (List<DS_CPO_RTRN_DTLTMsg>) ssmClient.queryObjectList("getEnteredDsCpoRtrnDtlList", ssmParam);

        for (DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg : dsCpoRtrnDtlTMsgList) {
            dsCpoRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsCpoRtrnDtlTMsg);

            if (null == dsCpoRtrnDtlTMsg) {
                continue;
            }

            dsCpoRtrnDtlTMsg.rtrnLineStsCd.setValue(RTRN_LINE_STS.BOOKED);
            // 2017/04/28 S21_NA#Review structure Lv.2 Mod Start
//            S21ApiTBLAccessor.updateSelectionField(dsCpoRtrnDtlTMsg, new String[] {"rtrnLineStsCd" });
            // 2019/04/10 S21_NA#31053 Mod Start
            dsCpoRtrnDtlTMsg.rtrnLineDplyStsCd.setValue(RTRN_LINE_DPLY_STS.PENDING_RETURN);
            //dsCpoRtrnDtlTMsg.rtrnLineDplyStsCd.setValue(RTRN_LINE_DPLY_STS.BOOKED);
            // 2019/04/10 S21_NA#31053 Mod End
            S21ApiTBLAccessor.updateSelectionField(dsCpoRtrnDtlTMsg, new String[] {"rtrnLineStsCd", "rtrnLineDplyStsCd"});
            // 2017/04/28 S21_NA#Review structure Lv.2 Mod End
        }
        return;
        // 2016/01/07 S21_NA#2588 Mod End
    }

//    //20150924 add
//    private boolean isActive(String rtrnLineStsCd) {
//        if (RTRN_LINE_STS.CLOSED.equals(rtrnLineStsCd) //
//                || RTRN_LINE_STS.CANCELLED.equals(rtrnLineStsCd)) {
//            return false;
//        }
//        return true;
//    }

//    //20150924 add
//    private String getPsnCd() {
//        S21UserProfileService prof = S21UserProfileServiceFactory.getInstance().getService();
//        S21UserInfo userInfo = prof.getContextUserInfo();
//        return userInfo.getUserId();
//    }

    //20150924 add
    private void updateBookCpo(NWZC004001PMsg param, String trxHdrNum) {
        CPOTMsg tMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, trxHdrNum);
        tMsg = (CPOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
        if (tMsg == null) {
            return;
        }
        //ZYPEZDItemValueSetter.setValue(tMsg.ordBookTs, DATE_TIME_PATTERN);
        ZYPEZDItemValueSetter.setValue(tMsg.ordBookTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
        ZYPEZDItemValueSetter.setValue(tMsg.ordBookFlg, ZYPConstant.FLG_ON_Y);
        // 2017/05/08 S21_NA#Review structure Lv.2 RS#7255(Display Status) Modd Start
//        S21ApiTBLAccessor.updateSelectionField(tMsg, new String[] {"ordBookTs", "ordBookFlg" });
        tMsg.ordHdrDplyStsCd.setValue(ORD_HDR_DPLY_STS.BOOKED);
        S21ApiTBLAccessor.updateSelectionField(tMsg, new String[] {"ordBookTs", "ordBookFlg", "ordHdrDplyStsCd"});
        // 2017/05/08 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add Start
    }

//    //20150924 add
//    private void updateUnholdCpo(NWZC004001PMsg param, String trxHdrNum) {
//        CPOTMsg tMsg = new CPOTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, trxHdrNum);
//        tMsg = (CPOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
//        if (tMsg == null) {
//            return;
//        }
//        ZYPEZDItemValueSetter.setValue(tMsg.cpoHldFlg, ZYPConstant.FLG_OFF_N);
//        S21ApiTBLAccessor.updateSelectionField(tMsg, new String[] {"cpoHldFlg" });
//    }

    //20150924 add
    private boolean isBookHoldOnly(NWZC004001PMsg param, String trxHdrNum) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("trxHdrNum", trxHdrNum);
        ssmParam.put("slsDt", param.slsDt.getValue());
        ssmParam.put("bookHold", HLD_RSN.BOOK);

        BigDecimal cnt = (BigDecimal) ssmClient.queryObject("cntExceptBookHold", ssmParam);
        return (BigDecimal.ZERO.compareTo(cnt) == 0);
    }

    // START 01/17/2013 S.Iidaka [WDSDefect#201 DEL]
    // ********** [# 351571] - START
    //    private boolean isIncludeValidatedCompCommonWH(String setShpgPlnNum, List<Map<String, Object>> orderList) {
    //
    //        for (Map<String, Object> order : orderList) {
    //
    //            final String glblCmpyCd = (String) order.get("GLBL_CMPY_CD");
    //            final String shpgPlnNum = (String) order.get("SHPG_PLN_NUM");
    //
    //            if (shpgPlnNum.equals(setShpgPlnNum)) {
    //                continue;
    //            }
    //
    //            // [SHPG_PLN]
    //            SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
    //            setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
    //            setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
    //            shpgPlnTMsg = (SHPG_PLNTMsg) findByKey(shpgPlnTMsg);
    //
    //            if (shpgPlnTMsg == null) {
    //                continue;
    //            }
    //
    //            final String cmpSetShpgPlnNum  = shpgPlnTMsg.setShpgPlnNum.getValue();
    //
    //            if (!setShpgPlnNum.equals(cmpSetShpgPlnNum)) {
    //                continue;
    //            }
    //
    //            if (SHPG_STS.CANCELLED.equals(shpgPlnTMsg.shpgStsCd.getValue())) {
    //                continue;
    //            }
    //
    //            final String whType         = (String) order.get("whType");
    //            final String shpgStsCdCd    = shpgPlnTMsg.shpgStsCd.getValue();
    //
    //            if (WH_TP.COMMON.equals(whType) && SHPG_STS.VALIDATED.equals(shpgStsCdCd)) {
    //                return false;
    //            }
    //        }
    //        return true;
    //    }
    // ********** [# 351571] - END
    // E N D 01/17/2013 S.Iidaka [WDSDefect#201 DEL]

    @SuppressWarnings("unchecked")
    private boolean isUpdateInProcAmt(Map<String, Object> order, SHPG_PLNTMsg shpgPlnTMsg) {

        //WDS#104 105 START
        // START 02/20/2013 S.Iidaka [WDSDefect#713 MOD]
        // START MODIFY S.Yamamoto [OM004]
        //        if (isCreditCardPayment(shpgPlnTMsg) || checkDefferd(shpgPlnTMsg) || isPriceZero(shpgPlnTMsg)) {
        if (isCreditCardPayment(shpgPlnTMsg) || isPriceZero(shpgPlnTMsg)) {
            // END   MODIFY S.Yamamoto [OM004]
            // E N D 02/20/2013 S.Iidaka [WDSDefect#201 MOD]
            return false;
        }
        //WDS#104 105 END

        final String cpoOrdTpCd = (String) order.get("CD_CPO_ORD_TP_CD");
        final String cpoSrcTpCd = (String) order.get("CPO_SRC_TP_CD");
        final String whTpCd = (String) order.get("whType");
        // START ADD S.Yamamoto [OM004]
        final String applyDsOrdTpCd = (String) order.get("DS_ORD_TP_CD");
        // END   ADD S.Yamamoto [OM004]

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", (String) order.get("GLBL_CMPY_CD"));
        ssmParam.put("hldRsnCd_CrLimit", HLD_RSN.CREDIT_LIMIT);
        ssmParam.put("ordTpCd", cpoOrdTpCd);
        ssmParam.put("srcTpCd", cpoSrcTpCd);
        // START ADD S.Yamamoto [OM004]
        ssmParam.put("applyDsOrdTpCd", applyDsOrdTpCd);
        // END   ADD S.Yamamoto [OM004]

        List<String> applyOrdTpList = ssmClient.queryObjectList("getCreditLimitOrdTpList", ssmParam);

        if (!isValidOrdTp(applyOrdTpList, HLD_RSN.CREDIT_LIMIT, cpoSrcTpCd, cpoOrdTpCd, whTpCd)) {
            return false;
        }

        ssmParam.clear();
        ssmParam.put("glblCmpyCd", (String) order.get("GLBL_CMPY_CD"));
        ssmParam.put("hldRsnCd_CrLimit", HLD_RSN.CREDIT_LIMIT);

        List<Map<String, String>> hldCtrlOrgList = ssmClient.queryObjectList("getCreditLimitHldCtrlOrgList", ssmParam);

        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        setValue(cpoDtlTMsg.slsRepOrSlsTeamTocCd, shpgPlnTMsg.slsRepTocCd);

        if (!isHldCtrlOrg(cpoDtlTMsg, hldCtrlOrgList, HLD_RSN.CREDIT_LIMIT)) {
            return false;
        }

        return true;
    }

    /** Update CreditCheckQty (Set) */
    private void updateCrChkQtySet(final List<Map<String, Object>> compList) {

        for (Map<String, Object> shpgPlnMap : compList) {

            SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
            shpgPlnMsg.glblCmpyCd.setValue((String) shpgPlnMap.get("GLBL_CMPY_CD"));
            shpgPlnMsg.shpgPlnNum.setValue((String) shpgPlnMap.get("SET_SHPG_PLN_NUM"));
            SHPG_PLNTMsg setShpgPlnMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnMsg);

            if (setShpgPlnMsg == null) {
                return;
            }

            List<Map<String, Object>> compCrChkQtyList = getEachCompCrChkQty(setShpgPlnMsg.glblCmpyCd.getValue(), setShpgPlnMsg.trxHdrNum.getValue(), setShpgPlnMsg.trxLineNum.getValue(), setShpgPlnMsg.trxSrcTpCd.getValue(),
                    (String) shpgPlnMap.get("SET_SHPG_PLN_NUM"));

            if (compCrChkQtyList == null || compCrChkQtyList.isEmpty()) {
                return;
            }

            // get composition quantity of each component.
            getCompCmpsnList(setShpgPlnMsg.glblCmpyCd.getValue(), setShpgPlnMsg.trxHdrNum.getValue(), setShpgPlnMsg.trxLineNum.getValue());

            BigDecimal setCrChkQty = null;

            for (Map<String, Object> compCrChkQty : compCrChkQtyList) {

                BigDecimal totalOrdQty = (BigDecimal) compCrChkQty.get("SUM_CR_CHK_QTY");
                String lineSubNum = (String) compCrChkQty.get("TRX_LINE_SUB_NUM");

                // none --> SetPossibleQty 0
                if (!hasValue(totalOrdQty)) {
                    return;
                } else if (ZERO.compareTo(totalOrdQty) > 0) {
                    return;
                }

                BigDecimal compCmpsnQty = (BigDecimal) this.compCmpsnList.get(lineSubNum);

                if (compCmpsnQty == null || ZERO.compareTo(compCmpsnQty) == 0) {
                    setCrChkQty = ZERO;
                    break;
                }

                BigDecimal calcSetPossibleQty = totalOrdQty.divide(compCmpsnQty, BigDecimal.ROUND_DOWN);

                if (ZERO.compareTo(calcSetPossibleQty) > 0) {
                    // calcSetPossibleQty < 0
                    return;
                }

                if (setCrChkQty == null) {
                    setCrChkQty = calcSetPossibleQty;

                } else if (setCrChkQty.compareTo(calcSetPossibleQty) > 0) {
                    // setCrChkQty > calcSetPossibleQty
                    setCrChkQty = calcSetPossibleQty;
                }
            }

            if (setCrChkQty == null || ZERO.compareTo(setCrChkQty) > 0) {
                return;
            }

            setShpgPlnMsg.crChkQty.setValue(setCrChkQty);

            // ******** update
            update(setShpgPlnMsg);
        }
    }

    private void updateHldFlg(NWZC004001PMsg param, String cpoOrdNum) {
        final String methodNm = "updateHldFlg";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            final String glblCmpyCd = param.glblCmpyCd.getValue();

            final List<String> hldOrdList = getHldOrderList(glblCmpyCd, cpoOrdNum);

            //            // CPO_HLD_FLG
            //            updateCpoHldFlg(hldOrdList, glblCmpyCd, cpoOrdNum);
            //            // CPO_DTL_HLD_FLG
            //            updateCpoDtlHldFlg(hldOrdList, glblCmpyCd, cpoOrdNum);
            //            // SHIP_PLN_HLD_FLG
            //            updateShpgPlnHldFlg(hldOrdList, glblCmpyCd, cpoOrdNum);

            final Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("cpoOrdNum", cpoOrdNum);
            // SHPG_STS
            final Map<String, String> shpgSts = new HashMap<String, String>();
            shpgSts.put("saved", SHPG_STS.SAVED);
            ssmParam.put("shpgSts", shpgSts);

            ssmClient.queryObjectList("getOrderListToUpdateHldFlg", ssmParam, new S21SsmVoidResultSetHandlerSupport() {

                @Override
                protected void doProcessQueryResult(ResultSet rs) throws SQLException {

                    final String[] CPO_HLD_FLG = {"cpoHldFlg" };
                    final String[] CPO_DTL_HLD_FLG = {"cpoDtlHldFlg" };
                    final String[] SHIP_PLN_HLD_FLG = {"shipPlnHldFlg" };

                    while (rs.next()) {

                        final String tblNm = rs.getString("TBL_NM");
                        final String hldFlg = rs.getString("HLD_FLG");
                        final String cpoOrdNum = rs.getString("CPO_ORD_NUM");
                        final String cpoDtlLineNum = rs.getString("CPO_DTL_LINE_NUM");
                        final String cpoDtlLineSubNum = rs.getString("CPO_DTL_LINE_SUB_NUM");
                        final String shpgPlnNum = rs.getString("SHPG_PLN_NUM");

                        // ------------------------------
                        // CPO
                        // ------------------------------
                        if ("CPO".equals(tblNm)) {

                            final StringBuilder key = new StringBuilder();
                            key.append(HLD_LVL.CPO_LEVEL);
                            key.append(".").append(cpoOrdNum);

                            // CPO_HLD_FLG
                            final String cpoHldFlg;
                            if (hldOrdList.contains(key.toString())) {
                                cpoHldFlg = Y;
                            } else {
                                cpoHldFlg = N;
                            }

                            if (!cpoHldFlg.equals(hldFlg)) {
                                writePerformanceProfilingLog(NWZC004001.class, " #update CPO_HLD_FLG. cpoOrdNum=[" + cpoOrdNum + "], cpoHldFlg=[" + hldFlg + "]->[" + cpoHldFlg + "]");

                                final CPOTMsg cpoTMsg = new CPOTMsg();
                                setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
                                setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);

                                // ***** updateSelectionField
                                setValue(cpoTMsg.cpoHldFlg, cpoHldFlg);
                                updateSelectionField(cpoTMsg, CPO_HLD_FLG);
                            }

                            // ------------------------------
                            // CPO_DTL
                            // ------------------------------
                        } else if ("CPO_DTL".equals(tblNm)) {

                            final StringBuilder key = new StringBuilder();
                            key.append(HLD_LVL.CPO_DETAIL_LEVEL);
                            key.append(".").append(cpoOrdNum);
                            key.append(".").append(cpoDtlLineNum);
                            key.append(".").append(cpoDtlLineSubNum);

                            // CPO_DTL_HLD_FLG
                            final String cpoDtlHldFlg;
                            if (hldOrdList.contains(key.toString())) {
                                cpoDtlHldFlg = Y;
                            } else {
                                cpoDtlHldFlg = N;
                            }

                            if (!cpoDtlHldFlg.equals(hldFlg)) {
                                writePerformanceProfilingLog(NWZC004001.class, " #update CPO_DTL_HLD_FLG. cpoOrdNum=[" + cpoOrdNum + "], cpoDtlLineNum=[" + cpoDtlLineNum + "], cpoDtlLineSubNum=[" + cpoDtlLineSubNum + "], cpoDtlHldFlg=["
                                        + hldFlg + "]->[" + cpoDtlHldFlg + "]");

                                final CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                                setValue(cpoDtlTMsg.glblCmpyCd, glblCmpyCd);
                                setValue(cpoDtlTMsg.cpoOrdNum, cpoOrdNum);
                                setValue(cpoDtlTMsg.cpoDtlLineNum, cpoDtlLineNum);
                                setValue(cpoDtlTMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);

                                // ***** updateSelectionField
                                setValue(cpoDtlTMsg.cpoDtlHldFlg, cpoDtlHldFlg);
                                updateSelectionField(cpoDtlTMsg, CPO_DTL_HLD_FLG);
                            }

                            // ------------------------------
                            // SHPG_PLN
                            // ------------------------------
                        } else if ("SHPG_PLN".equals(tblNm)) {

                            final StringBuilder key = new StringBuilder();
                            key.append(HLD_LVL.SHIPPING_PLAN_LEVEL);
                            key.append(".").append(cpoOrdNum);
                            key.append(".").append(cpoDtlLineNum);
                            key.append(".").append(cpoDtlLineSubNum);
                            key.append(".").append(shpgPlnNum);

                            // SHIP_PLN_HLD_FLG
                            final String shipPlnHldFlg;
                            if (hldOrdList.contains(key.toString())) {
                                shipPlnHldFlg = Y;
                            } else {
                                shipPlnHldFlg = N;
                            }

                            if (!shipPlnHldFlg.equals(hldFlg)) {
                                writePerformanceProfilingLog(NWZC004001.class, " #update SHIP_PLN_HLD_FLG. shpgPlnNum=[" + shpgPlnNum + "], shipPlnHldFlg=[" + hldFlg + "]->[" + shipPlnHldFlg + "]");

                                final SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                                setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
                                setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);

                                // ***** updateSelectionField
                                setValue(shpgPlnTMsg.shipPlnHldFlg, shipPlnHldFlg);
                                updateSelectionField(shpgPlnTMsg, SHIP_PLN_HLD_FLG);
                            }
                        }
                    }
                }
            });

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    @SuppressWarnings("unchecked")
    private void updateHldQty(List<Map<String, Object>> orderList) {
        final String methodNm = "updateHldQty";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            if (orderList.isEmpty()) {
                return;
            }

            final Map<String, Object> trxHdr = orderList.get(0);
            final String glblCmpyCd = (String) trxHdr.get("GLBL_CMPY_CD");
            final String cpoOrdNum = (String) trxHdr.get("CPO_ORD_NUM");

            final List<Map<String, Object>> compList = new ArrayList<Map<String, Object>>();
            final Map<String, Object> compShpgPlnMap = new HashMap<String, Object>();

            // START 2012/12/27 A.Suda [WDSDefect#78 DEL]
            //            // Target 'SHPG_PLN_STS_CD' List.
            //            final List<String> targetShpgPlnStsList =
            //                asList(SHPG_STS.CANCELLED, SHPG_STS.VALIDATED, SHPG_STS.HARD_ALLOCATED, SHPG_STS.S_OR_O_CANCELLED, SHPG_STS.P_OR_O_CANCELLED);
            // END 2012/12/27 A.Suda [WDSDefect#78 DEL]

            // START 2013/02/05 S.Iidaka [WDSDefect#405 ADD]
            // Ignore 'SHPG_PLN_STS_CD' List.
            final List<String> ignoreShpgPlnStsList = asList(SHPG_STS.INVOICED);
            // E N D 2013/02/05 S.Iidaka [WDSDefect#405 ADD]

            final String[] updateSelectionField = new String[] {"slsHldQty", "crHldQty" };

            for (Map<String, Object> order : orderList) {

                // START 2013/02/05 S.Iidaka [WDSDefect#405 ADD]
                if (ignoreShpgPlnStsList.contains((String) order.get("SHPG_STS_CD"))) {
                    continue;
                }
                // E N D 2013/02/05 S.Iidaka [WDSDefect#405 ADD]

                // START 2012/12/27 A.Suda [WDSDefect#78 DEL]
                //                if (!targetShpgPlnStsList.contains((String) order.get("SHPG_STS_CD"))) {
                //                    continue;
                //                }
                // END 2012/12/27 A.Suda [WDSDefect#78 DEL]
                final String cpoDtlLineNum = (String) order.get("CPO_DTL_LINE_NUM");
                final String cpoDtlLineSubNum = (String) order.get("CPO_DTL_LINE_SUB_NUM");
                final String shpgPlnNum = (String) order.get("SHPG_PLN_NUM");

                /**********************************************************************
                 * get 'SLS_HLD_QTY' and 'CR_HLD_QTY'.
                 **********************************************************************/
                BigDecimal slsHldQty = ZERO;
                BigDecimal crHldQty = ZERO;

                final Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("cpoOrdNum", cpoOrdNum);
                ssmParam.put("cpoDtlLineNum", cpoDtlLineNum);
                ssmParam.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
                ssmParam.put("shpgPlnNum", shpgPlnNum);
                ssmParam.put("CPO_LEVEL", HLD_LVL.CPO_LEVEL);
                ssmParam.put("CPO_DETAIL_LEVEL", HLD_LVL.CPO_DETAIL_LEVEL);
                ssmParam.put("SHIPPING_PLAN_LEVEL", HLD_LVL.SHIPPING_PLAN_LEVEL);
                ssmParam.put("trxSrcTp", TRX_SRC_TP.WHOLE_SALES_RETURN);    // QC#11306 2016/09/20 Add

                //                final List<Map<String, Object>> hldQtyInfoList = ssmClient.queryObjectList("getHldQtyInfoList", ssmParam);
                final List<Map<String, Object>> hldQtyInfoList = ssmClient.queryObjectList("getHldQtyInfoList", ssmParam, new S21SsmListResultSetHandlerSupport() {
                    @Override
                    protected List doProcessQueryResult(ResultSet rs) throws SQLException {
                        return toMapList(rs);
                    }
                });

                for (Map<String, Object> hldQtyInfo : hldQtyInfoList) {

                    final String hldLvlCd = (String) hldQtyInfo.get("HLD_LVL_CD");
                    final String hldTpCd = (String) hldQtyInfo.get("HLD_TP_CD");

                    final BigDecimal hldQty;
                    if (asList(HLD_LVL.CPO_LEVEL, HLD_LVL.CPO_DETAIL_LEVEL).contains(hldLvlCd)) {
                        hldQty = (BigDecimal) order.get("S_ORD_QTY");
                    } else {
                        hldQty = (BigDecimal) hldQtyInfo.get("HLD_QTY");
                    }

                    if (HLD_TP.SALES_HOLD.equals(hldTpCd)) {
                        slsHldQty = hldQty;
                    } else if (HLD_TP.CREDIT_HOLD.equals(hldTpCd)) {
                        crHldQty = hldQty;
                    }
                }

                // ********** 10/13/2010 : add by K.Tajima - [START] : to get High-performance
                final SHPG_PLNTMsg reqTMsg = new SHPG_PLNTMsg();
                setValue(reqTMsg.glblCmpyCd, glblCmpyCd);
                setValue(reqTMsg.shpgPlnNum, shpgPlnNum);
                final SHPG_PLNTMsg dbShpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(reqTMsg);

                final boolean isHldQtyChanged //
                = !nullToZero(dbShpgPlnTMsg.slsHldQty.getValue()).equals(nullToZero(slsHldQty)) // SLS_HLD_QTY
                        || !nullToZero(dbShpgPlnTMsg.crHldQty.getValue()).equals(nullToZero(crHldQty)); // CR_HLD_QTY

                if (isHldQtyChanged) {
                    writePerformanceProfilingLog(getClass(), " #update HLD_QTY. shpgPlnNum=[" + shpgPlnNum //
                            + "], slsHldQty=[" + dbShpgPlnTMsg.slsHldQty.getValue() + "]->[" + slsHldQty //
                            + "], crHldQty=[" + dbShpgPlnTMsg.crHldQty.getValue() + "]->[" + crHldQty + "]");

                    /**********************************************************************
                     * update 'SLS_HLD_QTY' and 'CR_HLD_QTY'.
                     **********************************************************************/
                    setValue(dbShpgPlnTMsg.slsHldQty, slsHldQty);
                    setValue(dbShpgPlnTMsg.crHldQty, crHldQty);

                    // ***** updateSelectionField
                    updateSelectionField(dbShpgPlnTMsg, updateSelectionField);
                }
                // ********** 10/13/2010 : add by K.Tajima - [E N D] : to get High-performance

                // 2010/04/30 Defect 5359
                String setMdseCd = (String) order.get("SET_MDSE_CD");
                String setShpgPlnNum = (String) order.get("SET_SHPG_PLN_NUM");

                if (hasValue(setMdseCd) && hasValue(setShpgPlnNum)) {
                    if (compShpgPlnMap.containsKey(setShpgPlnNum)) {
                        continue;
                    } else {
                        compShpgPlnMap.put(setShpgPlnNum, setShpgPlnNum);
                        compList.add(order);
                    }
                }
            }

            // 2010/04/30 Defect 5359
            if (!compList.isEmpty()) {
                updateSetHldQty(compList);
            }

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    /** Update Hold Qty (Set) */
    private void updateSetHldQty(final List<Map<String, Object>> compList) {

        for (Map<String, Object> comp : compList) {

            final String glblCmpyCd = (String) comp.get("GLBL_CMPY_CD");
            final String trxHdrNum = (String) comp.get("CPO_ORD_NUM");
            final String trxLineNum = (String) comp.get("CPO_DTL_LINE_NUM");
            final String setShpgPlnNum = (String) comp.get("SET_SHPG_PLN_NUM");
            final String trxSrcTpCd = (String) comp.get("TRX_SRC_TP_CD");

            final List<Map<String, Object>> compCrHldQtyList = getEachCompCrHldQty(glblCmpyCd, trxHdrNum, trxLineNum, trxSrcTpCd, setShpgPlnNum);

            if (isEmpty(compCrHldQtyList)) {
                return;
            }

            BigDecimal setCrHoldQty = null;

            for (Map<String, Object> compCrHldQty : compCrHldQtyList) {

                BigDecimal totalCrHldQty = (BigDecimal) compCrHldQty.get("SUM_CR_HLD_QTY");
                String lineSubNum = (String) compCrHldQty.get("TRX_LINE_SUB_NUM");

                if (!hasValue(totalCrHldQty)) {
                    continue;
                } else if (ZERO.compareTo(totalCrHldQty) > 0) {
                    continue;
                } else if (ZERO.compareTo(totalCrHldQty) == 0) {
                    continue;
                }

                // get composition quantity of each component.
                getCompCmpsnList(glblCmpyCd, trxHdrNum, trxLineNum);

                BigDecimal compCmpsnQty = (BigDecimal) this.compCmpsnList.get(lineSubNum);

                if (compCmpsnQty == null || ZERO.compareTo(compCmpsnQty) == 0) {
                    setCrHoldQty = null;
                    break;
                }

                BigDecimal calcSetPossibleQty = totalCrHldQty.divide(compCmpsnQty, BigDecimal.ROUND_UP);

                if (ZERO.compareTo(calcSetPossibleQty) > 0) {
                    // calcSetPossibleQty <=0
                    continue;
                }

                if (setCrHoldQty == null) {
                    setCrHoldQty = calcSetPossibleQty;

                } else if (setCrHoldQty.compareTo(calcSetPossibleQty) > 0) {
                    // setCrChkQty > calcSetPossibleQty
                    setCrHoldQty = calcSetPossibleQty;
                }
            }

            if (setCrHoldQty == null) {
                setCrHoldQty = ZERO;
            }

            // Set Item SHPG_PLN
            SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
            setValue(shpgPlnMsg.glblCmpyCd, glblCmpyCd);
            setValue(shpgPlnMsg.shpgPlnNum, setShpgPlnNum);

            SHPG_PLNTMsg setShpgPlnMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnMsg);

            if (setShpgPlnMsg == null) {
                return;
            }

            if (setShpgPlnMsg.ordQty.getValue().compareTo(setCrHoldQty) < 0) {
                setCrHoldQty = setShpgPlnMsg.ordQty.getValue();
            }

            setShpgPlnMsg.crHldQty.setValue(setCrHoldQty);

            // ******** update
            update(setShpgPlnMsg);
        }

    }

    private static void addCreditValidHldOrders(NWZC004001_APMsgArray crValidHldMsgArray, NWZC004001PMsg param) {

        // has Credit Validation Holds.
        if (crValidHldMsgArray.getValidCount() > 0) {

            final NWZC004001_APMsgArray retHldMsgArray = param.A;

            for (int i = 0; i < crValidHldMsgArray.getValidCount(); i++) {

                final int hldCnt = retHldMsgArray.getValidCount();
                if (hldCnt >= retHldMsgArray.length()) {
                    break;
                } else {
                    retHldMsgArray.setValidCount(hldCnt + 1);
                }

                // set 'Credit Validation Hold Orders' to param.
                final NWZC004001_APMsg hldMsg = retHldMsgArray.no(retHldMsgArray.getValidCount() - 1);
                setValue(hldMsg.cpoOrdNum_O, crValidHldMsgArray.no(i).cpoOrdNum_O);
                setValue(hldMsg.cpoDtlLineNum_O, crValidHldMsgArray.no(i).cpoDtlLineNum_O);
                setValue(hldMsg.cpoDtlLineSubNum_O, crValidHldMsgArray.no(i).cpoDtlLineSubNum_O);
                setValue(hldMsg.shpgPlnNum_O, crValidHldMsgArray.no(i).shpgPlnNum_O);
            }
        }
    }

    private static void deleteShipCpltHld(NWXC005001PMsg validPMsg) {

        final HLDTMsg condHldTMsg = new HLDTMsg();
        setValue(condHldTMsg.glblCmpyCd, validPMsg.glblCmpyCd);
        setValue(condHldTMsg.hldRsnCd, HLD_RSN.SHIP_COMPLETE_HOLD);
        setValue(condHldTMsg.cpoOrdNum, validPMsg.cpoOrdNum_I);
        setValue(condHldTMsg.relFlg, N);

        // ***** logicalRemoveByPartialValue
        logicalRemoveByPartialValue(condHldTMsg, new String[] {"glblCmpyCd", "hldRsnCd", "cpoOrdNum", "relFlg" });

        final CPO_DTLTMsg condCpoDtlTMsg = new CPO_DTLTMsg();
        setValue(condCpoDtlTMsg.glblCmpyCd, validPMsg.glblCmpyCd);
        setValue(condCpoDtlTMsg.cpoOrdNum, validPMsg.cpoOrdNum_I);

        final CPO_DTLTMsg updCpoDtlTMsg = new CPO_DTLTMsg();
        setValue(updCpoDtlTMsg.cpoDtlHldFlg, N);

        // ***** updateByPartialValue
        updateByPartialValue(condCpoDtlTMsg, new String[] {"glblCmpyCd", "cpoOrdNum" }, updCpoDtlTMsg, new String[] {"cpoDtlHldFlg" });
    }

    private static EZDTMsg findByKeyWithCache(EZDTMsg reqTMsg) {

        return S21CacheTBLAccessor.findByKey(reqTMsg);
    }

    //    private static MDSE_STORE_PKGTMsg getMdseStorePkgMsg(String glblCmpyCd, String mdseCd, String custUomCd) {
    //
    //        MDSETMsg mdseMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
    //
    //        final MDSE_STORE_PKGTMsg inStrPkgMsg = new MDSE_STORE_PKGTMsg();
    //        setValue(inStrPkgMsg.glblCmpyCd, glblCmpyCd);
    //        setValue(inStrPkgMsg.pkgUomCd,   custUomCd);
    //        setValue(inStrPkgMsg.mdseCd,     mdseMsg.mdseCd.getValue());
    //
    //        return (MDSE_STORE_PKGTMsg) findByKeyWithCache(inStrPkgMsg);
    //    }

    private static String getWhType(String glblCmpyCd, String mdseCd, String poReqFlg) {

        String whType = null;

        if (Y.equals(poReqFlg)) {
            whType = WH_TP.VENDOR;

        } else {

            final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
            if (mdseTMsg != null) {
                final String invtyCtrlFlg = mdseTMsg.invtyCtrlFlg.getValue();
                if (Y.equals(invtyCtrlFlg)) {
                    whType = WH_TP.COMMON;
                } else {
                    whType = WH_TP.NON_W_OR_H;
                }
            }
        }

        return whType;
    }

    @SuppressWarnings("unchecked")
    private static boolean isEmpty(Collection col) {
        return col == null || col.isEmpty();
    }

    private static BigDecimal nullToZero(BigDecimal bc) {

        if (bc == null) {
            return ZERO;
        }
        return bc;
    }

    private void updateCpoDtlHldFlg(List<String> hldOrdList, String glblCmpyCd, String cpoOrdNum) {

        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        cpoDtlTMsg.setSQLID("001");
        cpoDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);

        final CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) findByConditionForUpdate(cpoDtlTMsg);

        if (cpoDtlTMsgArray.getValidCount() == 0) {
            return;
        }

        final String[] updList = {"cpoDtlHldFlg" };

        for (int i = 0; i < cpoDtlTMsgArray.getValidCount(); i++) {

            cpoDtlTMsg = cpoDtlTMsgArray.no(i);

            final StringBuilder key = new StringBuilder();
            key.append(HLD_LVL.CPO_DETAIL_LEVEL);
            key.append(".").append(cpoDtlTMsg.cpoOrdNum.getValue());
            key.append(".").append(cpoDtlTMsg.cpoDtlLineNum.getValue());
            key.append(".").append(cpoDtlTMsg.cpoDtlLineSubNum.getValue());

            // CPO_DTL_HLD_FLG
            final String cpoDtlHldFlg;
            if (hldOrdList.contains(key.toString())) {
                cpoDtlHldFlg = Y;
            } else {
                cpoDtlHldFlg = N;
            }

            if (!cpoDtlHldFlg.equals(cpoDtlTMsg.cpoDtlHldFlg.getValue())) {
                writePerformanceProfilingLog(getClass(), " #update CPO_DTL_HLD_FLG. cpoOrdNum=[" + cpoOrdNum + "], cpoDtlLineNum=[" + cpoDtlTMsg.cpoDtlLineNum.getValue() + "], cpoDtlLineSubNum=[" + cpoDtlTMsg.cpoDtlLineSubNum.getValue()
                        + "], cpoDtlHldFlg=[" + cpoDtlTMsg.cpoDtlHldFlg.getValue() + "]->[" + cpoDtlHldFlg + "]");

                // ***** updateSelectionField
                setValue(cpoDtlTMsg.cpoDtlHldFlg, cpoDtlHldFlg);
                updateSelectionField(cpoDtlTMsg, updList);
            }
        }
    }

    //    private void updateCpoHldFlg(List<String> hldOrdList, String glblCmpyCd, String cpoOrdNum) {
    //
    //        final StringBuilder key = new StringBuilder();
    //        key.append(HLD_LVL.CPO_LEVEL);
    //        key.append(".").append(cpoOrdNum);
    //        
    //        // CPO_HLD_FLG
    //        final String cpoHldFlg;
    //        if(hldOrdList.contains(key.toString())) {
    //            cpoHldFlg = Y;
    //        } else {
    //            cpoHldFlg = N;
    //        }
    //
    //        // CPO
    //        CPOTMsg cpoTMsg = new CPOTMsg();
    //        setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
    //        setValue(cpoTMsg.cpoOrdNum,  cpoOrdNum);
    //        cpoTMsg = (CPOTMsg) findByKeyForUpdateAPI(cpoTMsg);
    //
    //        if (cpoTMsg == null) {
    //            return;
    //        }
    //            
    //        if (!cpoHldFlg.equals(cpoTMsg.cpoHldFlg.getValue())) {
    //            writePerformanceProfilingLog(getClass(), " #update CPO_HLD_FLG. cpoOrdNum=[" + cpoOrdNum + "], cpoHldFlg=[" + cpoTMsg.cpoHldFlg.getValue() + "]->[" + cpoHldFlg + "]");
    //
    //            // ***** updateSelectionField
    //            setValue(cpoTMsg.cpoHldFlg, cpoHldFlg);
    //            updateSelectionField(cpoTMsg, new String[] {"cpoHldFlg" });
    //        }
    //    }

    //    private void updateShpgPlnHldFlg(List<String> hldOrdList, String glblCmpyCd, String cpoOrdNum) {
    //
    //        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
    //        shpgPlnTMsg.setSQLID("023");
    //        shpgPlnTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
    //        shpgPlnTMsg.setConditionValue("trxHdrNum01",  cpoOrdNum);
    //        shpgPlnTMsg.setConditionValue("shpgStsCd01",  SHPG_STS.SAVED);
    //
    //        final SHPG_PLNTMsgArray shpgPlnTMsgArray = (SHPG_PLNTMsgArray) findByConditionForUpdate(shpgPlnTMsg);
    //        
    //        if (shpgPlnTMsgArray.getValidCount() == 0) {
    //            return;
    //        }
    //
    //        final String[] updList = {"shipPlnHldFlg" };
    //        
    //        for (int i = 0; i < shpgPlnTMsgArray.getValidCount(); i++) {
    //
    //            shpgPlnTMsg = shpgPlnTMsgArray.no(i);
    //            
    //            final StringBuilder key = new StringBuilder();
    //            key.append(HLD_LVL.SHIPPING_PLAN_LEVEL);
    //            key.append(".").append(shpgPlnTMsg.trxHdrNum.getValue());
    //            key.append(".").append(shpgPlnTMsg.trxLineNum.getValue());
    //            key.append(".").append(shpgPlnTMsg.trxLineSubNum.getValue());
    //            key.append(".").append(shpgPlnTMsg.shpgPlnNum.getValue());
    //
    //            // SHIP_PLN_HLD_FLG
    //            final String shipPlnHldFlg;
    //            if (hldOrdList.contains(key.toString())) {
    //                shipPlnHldFlg = Y;
    //            } else {
    //                shipPlnHldFlg = N;
    //            }
    //            
    //            if (!shipPlnHldFlg.equals(shpgPlnTMsg.shipPlnHldFlg.getValue())) {
    //                writePerformanceProfilingLog(getClass(), " #update SHIP_PLN_HLD_FLG. shpgPlnNum=[" + shpgPlnTMsg.shpgPlnNum.getValue() + "], shipPlnHldFlg=[" + shpgPlnTMsg.shipPlnHldFlg.getValue() + "]->[" + shipPlnHldFlg + "]");
    //
    //                // ***** updateSelectionField
    //                setValue(shpgPlnTMsg.shipPlnHldFlg, shipPlnHldFlg);
    //                updateSelectionField(shpgPlnTMsg, updList);
    //            }
    //        }
    //    }

    @SuppressWarnings("unchecked")
    private static void writePerformanceProfilingLog(Class clazz, String str) {
        // S21_NA#12895 Del Start
        // final StringBuilder sb = new StringBuilder();
        // sb.append("#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(str);
        // System.out.println(sb.toString());
        // S21_NA#12895 Del End
    }

    @SuppressWarnings("unchecked")
    private static void writePerformanceProfilingLogEnd(Class clazz, String methodNm) {
        // S21_NA#12895 Del Start
        // final StringBuilder sb = new StringBuilder();
        // sb.append("#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(methodNm).append(" - [E N D] ").append(System.currentTimeMillis());
        // System.out.println(sb.toString());
        // S21_NA#12895 Del End
    }

    @SuppressWarnings("unchecked")
    private static void writePerformanceProfilingLogStart(Class clazz, String methodNm) {
        // S21_NA#12895 Del Start
        // final StringBuilder sb = new StringBuilder();
        // sb.append("#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(methodNm).append(" - [START] ").append(System.currentTimeMillis());
        // System.out.println(sb.toString());
        // S21_NA#12895 Del End
    }

    private static final class HldProcDfn {

        final String hldRsnCd;

        final String hldRsnNm;

        final String hldLvlCd;

        final String exReqFlg;

        private HldProcDfn(ResultSet rs) throws SQLException {
            this.hldRsnCd = rs.getString("HLD_RSN_CD");
            this.hldRsnNm = rs.getString("HLD_RSN_NM");
            this.hldLvlCd = rs.getString("HLD_LVL_CD");
            this.exReqFlg = rs.getString("EX_REQ_FLG");
        }
    }

    /**
     * Order Validator
     * @author K.Tajima
     */
    private final class OrderValidator extends S21SsmVoidResultSetHandlerSupport {

        private NWZC004001PMsg param;

        private String glblCmpyCd;

        private String hldDfnChkPnt;

        // START ADD S.Yamamoto [OM004]
        private String applyDsOrdTpCd;

        // END   ADD S.Yamamoto [OM004]
        private List<Map<String, Object>> orderList;

        private CPOTMsg cpoTMsg;

        private CPO_DTLTMsgArray cpoDtlTMsgArray;

        //private SHPG_PLNTMsgArray shpgPlnTMsgArray;

        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        private DS_CPOTMsg dcpoTMsg;
//
//        private DS_CPO_DTLTMsgArray dcpoDtlTMsgArray;
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        private DS_CPO_CONFIGTMsgArray dcpoConfigTMsgArray;

        private final S21LRUMap<String, List<HldProcDfn>> hldProcDfnListCache = new S21LRUMap<String, List<HldProcDfn>>();

        private final S21LRUMap<String, List<Map<String, String>>> hldCtrlOrgListCache = new S21LRUMap<String, List<Map<String, String>>>();

        private final S21LRUMap<String, List<String>> applyOrdTpListCache = new S21LRUMap<String, List<String>>();

        private List<Map<String, String>> hldCtrlOrgList;

        private List<String> applyOrdTpList;

        // Def#1481 Delete
        ///** cache "Validation APIs" */
        //private final Map<Class<? extends S21ApiCommonBase>, S21ApiCommonBase> apiCache = new HashMap<Class<? extends S21ApiCommonBase>, S21ApiCommonBase>();
        /** cache "HLD_CTRL_TAX_EXEM" */
        private final S21LRUMap<String, List<Map<String, Object>>> hldCtrlTaxExemCache = new S21LRUMap<String, List<Map<String, Object>>>();

        /** cache "BILL_TO_CUST.FL_PLN_CMPY_FLG" */
        final S21LRUMap<String, String> flPlnCmpyFlgCache = new S21LRUMap<String, String>();
        
        // 2018/01/18 QC#22737-2 add start
        private List<NWXC005001PMsg> hldParamList = new ArrayList<NWXC005001PMsg>();

        /** Credit Check Mode */
        private String crChkMode = null;

        private boolean chkReqFlg = false;
        
        private boolean crProfileFlg = false;

        private boolean contrHldFlg = false;

        private boolean crLimitFlg = false;

        private boolean overdueFlg = false;
        // 2018/01/18 QC#22737-2 add end

        private OrderValidator() {
        }

        /**
         * <pre>
         * execute Validation. (Order, Shipping, Credit or Soft Allocation Validation)
         * 
         * @param param NWZC004001PMsg
         * @param orderList SHPG_PLN list
         * @param hldDfnChkPnt HLD_DFN_CHK_PNT
         * 
         * @return true/has holds, false/nothing holds
         * </pre>
         */
        //        @SuppressWarnings("unchecked")
        public boolean exec(NWZC004001PMsg param, List<Map<String, Object>> orderList, String hldDfnChkPnt) {
            final String methodNm = "exec";
            writePerformanceProfilingLogStart(getClass(), methodNm);

            boolean hasHlds = false;

            try {

                if (isEmpty(orderList)) {
                    return hasHlds;
                }

                this.param = param;
                this.glblCmpyCd = param.glblCmpyCd.getValue();
                this.hldDfnChkPnt = hldDfnChkPnt;
                this.orderList = orderList;
                // START ADD S.Yamamoto [OM004]
                this.applyDsOrdTpCd = (String) orderList.get(0).get("DS_ORD_TP_CD");
                // END   ADD S.Yamamoto [OM004]

                /**************************************************************
                 * get 'HldCtrlOrgList' and 'ApplyOrdTpList'
                 *************************************************************/
                this.hldCtrlOrgList = getHldCtrlOrgList();
                this.applyOrdTpList = getApplyOrdTpList();

                /**************************************************************
                 * get [CPO], [CPO_DTL], [DS_CPO], [DS_CPO_DTL] and
                 * [SHPG_PLN].
                 *************************************************************/
                final String cpoOrdNum;

                if (hasValue(param.shpgPlnNum_I)) {

                    // [SHPG_PLN]
                    SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                    setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
                    setValue(shpgPlnTMsg.shpgPlnNum, param.shpgPlnNum_I);
                    //                    shpgPlnTMsg = (SHPG_PLNTMsg) findByKey(shpgPlnTMsg);
                    shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyWithCache(shpgPlnTMsg);

                    cpoOrdNum = shpgPlnTMsg.trxHdrNum.getValue();

                    //shpgPlnTMsg.setSQLID("023");
                    //shpgPlnTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                    //shpgPlnTMsg.setConditionValue("trxHdrNum01", cpoOrdNum);
                    //shpgPlnTMsg.setConditionValue("shpgStsCd01", SHPG_STS.SAVED);
                    //this.shpgPlnTMsgArray = (SHPG_PLNTMsgArray) findByCondition(shpgPlnTMsg);

                    boolean isSameCPO = false;
                    if (this.cpoTMsg != null) {
                        if (this.cpoTMsg.cpoOrdNum.getValue().equals(cpoOrdNum)) {
                            isSameCPO = true;
                        }
                    }

                    if (!isSameCPO) {
                        // [CPO_DTL]
                        final CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();
                        reqCpoDtlTMsg.setSQLID("001");
                        reqCpoDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                        reqCpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
                        this.cpoDtlTMsgArray = (CPO_DTLTMsgArray) findByCondition(reqCpoDtlTMsg);
                        // [CPO]
                        final CPOTMsg reqCpoTMsg = new CPOTMsg();
                        setValue(reqCpoTMsg.glblCmpyCd, glblCmpyCd);
                        setValue(reqCpoTMsg.cpoOrdNum, cpoOrdNum);
                        this.cpoTMsg = (CPOTMsg) findByKeyForUpdateAPI(reqCpoTMsg);
                        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                        // [DS_CPO_DTL]
//                        final DS_CPO_DTLTMsg reqDcpoDtlTMsg = new DS_CPO_DTLTMsg();
//                        reqDcpoDtlTMsg.setSQLID("001");
//                        reqDcpoDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//                        reqDcpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
//                        this.dcpoDtlTMsgArray = (DS_CPO_DTLTMsgArray) findByCondition(reqDcpoDtlTMsg);
//                        // [DS_CPO]
//                        final DS_CPOTMsg reqDcpoTMsg = new DS_CPOTMsg();
//                        setValue(reqDcpoTMsg.glblCmpyCd, glblCmpyCd);
//                        setValue(reqDcpoTMsg.cpoOrdNum, cpoOrdNum);
//                        this.dcpoTMsg = (DS_CPOTMsg) findByKeyForUpdateAPI(reqDcpoTMsg);
                        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
                        // [DS_CPO_CONFIG] 20150924 add
                        final DS_CPO_CONFIGTMsg reqDcpoConfigTMsg = new DS_CPO_CONFIGTMsg();
                        reqDcpoConfigTMsg.setSQLID("001");
                        reqDcpoConfigTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                        reqDcpoConfigTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
                        this.dcpoConfigTMsgArray = (DS_CPO_CONFIGTMsgArray) findByCondition(reqDcpoConfigTMsg);
                    }

                } else {

                    cpoOrdNum = param.cpoOrdNum_I.getValue();

                    // [CPO]
                    final CPOTMsg reqCpoTMsg = new CPOTMsg();
                    setValue(reqCpoTMsg.glblCmpyCd, glblCmpyCd);
                    setValue(reqCpoTMsg.cpoOrdNum, cpoOrdNum);
                    this.cpoTMsg = (CPOTMsg) findByKeyForUpdateAPI(reqCpoTMsg);
                    // [CPO_DTL]
                    final CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();
                    reqCpoDtlTMsg.setSQLID("001");
                    reqCpoDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                    reqCpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
                    this.cpoDtlTMsgArray = (CPO_DTLTMsgArray) findByCondition(reqCpoDtlTMsg);
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                    // [DS_CPO]
//                    final DS_CPOTMsg reqDcpoTMsg = new DS_CPOTMsg();
//                    setValue(reqDcpoTMsg.glblCmpyCd, glblCmpyCd);
//                    setValue(reqDcpoTMsg.cpoOrdNum, cpoOrdNum);
//                    this.dcpoTMsg = (DS_CPOTMsg) findByKeyForUpdateAPI(reqDcpoTMsg);
//                    // [DS_CPO_DTL]
//                    final DS_CPO_DTLTMsg reqDcpoDtlTMsg = new DS_CPO_DTLTMsg();
//                    reqDcpoDtlTMsg.setSQLID("001");
//                    reqDcpoDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//                    reqDcpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
//                    this.dcpoDtlTMsgArray = (DS_CPO_DTLTMsgArray) findByCondition(reqDcpoDtlTMsg);
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del End
                    // [SHPG_PLN]
                    //final SHPG_PLNTMsg reqShpgPlnTMsg = new SHPG_PLNTMsg();
                    //reqShpgPlnTMsg.setSQLID("023");
                    //reqShpgPlnTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                    //reqShpgPlnTMsg.setConditionValue("trxHdrNum01", cpoOrdNum);
                    //reqShpgPlnTMsg.setConditionValue("shpgStsCd01", SHPG_STS.SAVED);
                    //this.shpgPlnTMsgArray = (SHPG_PLNTMsgArray) findByCondition(reqShpgPlnTMsg);
                    // [DS_CPO_CONFIG] 20150924 add
                    final DS_CPO_CONFIGTMsg reqDcpoConfigTMsg = new DS_CPO_CONFIGTMsg();
                    reqDcpoConfigTMsg.setSQLID("001");
                    reqDcpoConfigTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                    reqDcpoConfigTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
                    this.dcpoConfigTMsgArray = (DS_CPO_CONFIGTMsgArray) findByCondition(reqDcpoConfigTMsg);
                }

                /**************************************************************
                 * get 'HLD_PROC_DFN' and exec Validation.
                 *************************************************************/
                // Credit Check Mode
                if (existsContract(param)) {
                    crChkMode = CONTRACT;
                } else if (isSupplyOrdCatg(this.cpoTMsg, param.glblCmpyCd.getValue())) {
                    crChkMode = BILLABLE;
                } else {
                    crChkMode = EQUIP;
                }

                final String hldProcDfnCacheKey = new StringBuilder().append(glblCmpyCd).append(",").append(hldDfnChkPnt).toString();

                if (hldProcDfnListCache.get(hldProcDfnCacheKey) != null) {
                    validation(hldProcDfnListCache.get(hldProcDfnCacheKey));
                } else {
                    final Map<String, String> ssmParam = new HashMap<String, String>();
                    ssmParam.put("glblCmpyCd", glblCmpyCd);
                    ssmParam.put("hldDfnChkPnt", hldDfnChkPnt);
                    ssmClient.queryObjectList("getHldProcDfnList", ssmParam, this);
                }

                // 2018/01/18 QC#22737-2 add start
                // execHold
                for (int i = 0; i < this.hldParamList.size(); i++) {
                    NWXC005001PMsg apiPMsg = this.hldParamList.get(i);
                    String hldRsnCd = apiPMsg.hldRsnCd.getValue();

                    if(crChkMode.equals(CONTRACT)) {
                        if (HLD_RSN.CREDIT_PROFILE.equals(hldRsnCd) && !(crProfileFlg || (chkReqFlg && contrHldFlg))) {
                            continue;
                        }
                    } else if (crChkMode.equals(BILLABLE)) {
                        if (HLD_RSN.CREDIT_PROFILE.equals(hldRsnCd) && !(crProfileFlg || (chkReqFlg && (crLimitFlg || overdueFlg)))) {
                            continue;
                        }
                    }
                    
                    final Map<String, Object> hldOrd = new HashMap<String, Object>();
                    hldOrd.put("HLD_RSN_CD", apiPMsg.hldRsnCd.getValue());
                    hldOrd.put("CPO_ORD_NUM", apiPMsg.cpoOrdNum_O.getValue());
                    hldOrd.put("CPO_DTL_LINE_NUM", apiPMsg.cpoDtlLineNum_O.getValue());
                    hldOrd.put("CPO_DTL_LINE_SUB_NUM", apiPMsg.cpoDtlLineSubNum_O.getValue());
                    hldOrd.put("SHPG_PLN_NUM", apiPMsg.shpgPlnNum_O.getValue());
                    hldOrd.put("ORD_QTY", apiPMsg.ordQty_O.getValue());

                    execHld(hldOrd);
                }
                
                // 2018/02/21 QC#23662 add start
                hldParamList = new ArrayList<NWXC005001PMsg>();
                // 2018/02/21 QC#23662 add start
                // 2018/01/18 QC#22737-2 add end

                // has Hlds?
                hasHlds = param.A.getValidCount() > 0;
                return hasHlds;

            } finally {
                writePerformanceProfilingLog(getClass(), "#hasHlds?=[" + hasHlds + "]");
                writePerformanceProfilingLogEnd(getClass(), methodNm);
            }
        }

        @Override
        protected void doProcessQueryResult(ResultSet rs) throws SQLException {
            final String methodNm = "doProcessQueryResult";
            writePerformanceProfilingLogStart(getClass(), methodNm);

            try {

                if (!rs.next()) {
                    return;
                }

                // cache.
                final String hldProcDfnCacheKey = new StringBuilder().append(glblCmpyCd).append(",").append(hldDfnChkPnt).toString();
                hldProcDfnListCache.put(hldProcDfnCacheKey, new ArrayList<HldProcDfn>());

                writePerformanceProfilingLogStart(getClass(), "validation");
                try {
                    do {
                        final HldProcDfn hldProcDfn = new HldProcDfn(rs);
                        hldProcDfnListCache.get(hldProcDfnCacheKey).add(hldProcDfn);
                        // ***** validation
                        validation(hldProcDfn);
                    } while (rs.next());
                } finally {
                    writePerformanceProfilingLogEnd(getClass(), "validation");
                }

            } finally {
                writePerformanceProfilingLogEnd(getClass(), methodNm);
            }
        }

        protected void validation(List<HldProcDfn> hldProcDfnList) {
            final String methodNm = "validation";
            writePerformanceProfilingLogStart(getClass(), methodNm);

            try {

                for (HldProcDfn hldProcDfn : hldProcDfnList) {
                    validation(hldProcDfn);
                }

            } finally {
                writePerformanceProfilingLogEnd(getClass(), methodNm);
            }
        }

        void validation(HldProcDfn hldProcDfn) {

            // Hold Process Definition Information
            final String hldRsnCd = hldProcDfn.hldRsnCd;
            final String hldRsnNm = hldProcDfn.hldRsnNm;
            final String hldLvlCd = hldProcDfn.hldLvlCd;
            final String exReqFlg = hldProcDfn.exReqFlg;

            // cache 'HLD_RSN.EX_REQ_FLG'.
            cacheExReqFlg(glblCmpyCd, hldRsnCd, exReqFlg);

            /*****************************************************
             * HLD_LVL : CPO
             ****************************************************/
            if (HLD_LVL.CPO_LEVEL.equals(hldLvlCd)) {

                String preCpoOrdNum = "";

                for (Map<String, Object> order : orderList) {

                    final String cpoSrcTpCd = (String) order.get("CPO_SRC_TP_CD");
                    final String cpoOrdTpCd = (String) order.get("C_CPO_ORD_TP_CD");
                    final String whTpCd = WH_TP.COMMON;

                    final String cpoOrdNum = (String) order.get("CPO_ORD_NUM");

                    if (!preCpoOrdNum.equals(cpoOrdNum)) {

                        preCpoOrdNum = cpoOrdNum;

                        if (!isValidOrdTp(applyOrdTpList, hldRsnCd, cpoSrcTpCd, cpoOrdTpCd, whTpCd)) {
                            continue;
                        }

                        final CPO_DTLTMsg cpoDtlTMsg = cpoDtlTMsgArray.no(0);
                        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                        final DS_CPO_DTLTMsg dcpoDtlTMsg = dcpoDtlTMsgArray.no(0);
                        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
                        final DS_CPO_CONFIGTMsg dcpoConfigTMsg = dcpoConfigTMsgArray.no(0);

                        boolean isHldCtrlOrg = false;
                        for (int i = 0; i < cpoDtlTMsgArray.getValidCount(); i++) {
                            isHldCtrlOrg = isHldCtrlOrg(cpoDtlTMsgArray.no(i), hldCtrlOrgList, hldRsnCd);
                            if (isHldCtrlOrg) {
                                break;
                            }
                        }

                        if (!isHldCtrlOrg) {
                            continue;
                        }

                        if (HLD_RSN.MISSING_TAX_EXEMPT_CERTIFICATE.equals(hldRsnCd)) {
                            if (!checkHldCtrlTaxExem(cpoTMsg.glblCmpyCd.getValue(), cpoTMsg.addShipToCtryCd.getValue(), cpoTMsg.addShipToStCd.getValue())) {
                                continue;
                            }
                        }

                        // --------------------------------------------------
                        // call Validation API.
                        // --------------------------------------------------
                        final NWXC005001PMsg validPMsg = new NWXC005001PMsg();
                        setValue(validPMsg.glblCmpyCd, glblCmpyCd);
                        setValue(validPMsg.slsDt, param.slsDt);
                        setValue(validPMsg.cpoOrdNum_I, cpoOrdNum);

                        writePerformanceProfilingLog(getClass(), " - HLD_LVL=[CPO], HLD_RSN_CD=[" + hldRsnCd + "], HLD_RSN_NM=[" + hldRsnNm + "] (" + cpoOrdNum + ")");
                        // 2017/03/29 S21_NA#Review structure Lv.1 Mod Start
//                        callValidationAPI(hldRsnCd, order, validPMsg, cpoTMsg, null, null, hldLvlCd, cpoDtlTMsg.exptFlg.getValue(), dcpoTMsg, dcpoDtlTMsg, dcpoConfigTMsg);
                        callValidationAPI(hldRsnCd, order, validPMsg, cpoTMsg, null, null, hldLvlCd, cpoDtlTMsg.exptFlg.getValue(), dcpoConfigTMsg);
                        // 2017/03/29 S21_NA#Review structure Lv.1 Mod End

                        if (HLD_RSN.SHIP_COMPLETE_HOLD.equals(hldRsnCd)) {
                            break;
                        }
                    }
                }

                /*************************************************
                 * CPO_DETAIL
                 ************************************************/
            } else if (HLD_LVL.CPO_DETAIL_LEVEL.equals(hldLvlCd)) {

                String preCpoOrdNum = "";
                String preCpoDtlLineNum = "";
                String preCpoDtlLineSubNum = "";

                for (Map<String, Object> order : orderList) {

                    final String cpoSrcTpCd = (String) order.get("CPO_SRC_TP_CD");
                    final String cpoOrdTpCd = (String) order.get("CD_CPO_ORD_TP_CD");
                    final String whTpCd = (String) order.get("whType");

                    final String cpoOrdNum = (String) order.get("CPO_ORD_NUM");
                    final String cpoDtlLineNum = (String) order.get("CPO_DTL_LINE_NUM");
                    final String cpoDtlLineSubNum = (String) order.get("CPO_DTL_LINE_SUB_NUM");
                    // final BigDecimal svcConfigMstrPk = (BigDecimal) order.get("SVC_CONFIG_MSTR_PK"); // 20150924 add -> 2015/12/03 S21_NA#1407Del
                    final BigDecimal dsCpoConfigPk = (BigDecimal) order.get("DS_CPO_CONFIG_PK"); // 2015/12/03 S21_NA#1407 add

                    if (!preCpoOrdNum.equals(cpoOrdNum) || !preCpoDtlLineNum.equals(cpoDtlLineNum) || !preCpoDtlLineSubNum.equals(cpoDtlLineSubNum)) {

                        preCpoOrdNum = cpoOrdNum;
                        preCpoDtlLineNum = cpoDtlLineNum;
                        preCpoDtlLineSubNum = cpoDtlLineSubNum;

                        if (!isValidOrdTp(applyOrdTpList, hldRsnCd, cpoSrcTpCd, cpoOrdTpCd, whTpCd)) {
                            continue;
                        }

                        final CPO_DTLTMsg cpoDtlTMsg = getCpoDtlTMsg(cpoDtlTMsgArray, cpoDtlLineNum, cpoDtlLineSubNum);
                        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                        final DS_CPO_DTLTMsg dcpoDtlTMsg = getDcpoDtlTMsg(dcpoDtlTMsgArray, cpoDtlLineNum, cpoDtlLineSubNum);
                        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
                        // final DS_CPO_CONFIGTMsg dcpoConfigTMsg = getDcpoConfigTMsg(dcpoConfigTMsgArray, svcConfigMstrPk); // 20150924 add -> 2015/12/03 S21_NA#1407Del
                        final DS_CPO_CONFIGTMsg dcpoConfigTMsg = getDcpoConfigTMsg(dcpoConfigTMsgArray, dsCpoConfigPk); // 2015/12/03 S21_NA#1407

                        if (!isHldCtrlOrg(cpoDtlTMsg, hldCtrlOrgList, hldRsnCd)) {
                            continue;
                        }

                        if (HLD_RSN.MISSING_TAX_EXEMPT_CERTIFICATE.equals(hldRsnCd)) {
                            if (!checkHldCtrlTaxExem(cpoDtlTMsg.glblCmpyCd.getValue(), cpoDtlTMsg.shipToCtryCd.getValue(), cpoDtlTMsg.shipToStCd.getValue())) {
                                continue;
                            }
                        }

                        // --------------------------------------------------
                        // call Validation API.
                        // --------------------------------------------------
                        final NWXC005001PMsg validPMsg = new NWXC005001PMsg();
                        setValue(validPMsg.glblCmpyCd, glblCmpyCd);
                        setValue(validPMsg.slsDt, param.slsDt);
                        setValue(validPMsg.cpoOrdNum_I, cpoOrdNum);
                        setValue(validPMsg.cpoDtlLineNum_I, cpoDtlLineNum);
                        setValue(validPMsg.cpoDtlLineSubNum_I, cpoDtlLineSubNum);

                        writePerformanceProfilingLog(getClass(), " - HLD_LVL=[CPO_DTL], HLD_RSN_CD=[" + hldRsnCd + "], HLD_RSN_NM=[" + hldRsnNm + "] (" + cpoOrdNum + "." + cpoDtlLineNum + "." + cpoDtlLineSubNum + ")");
                        // 2017/03/29 S21_NA#Review structure Lv.1 Mod Start
//                        callValidationAPI(hldRsnCd, order, validPMsg, cpoTMsg, cpoDtlTMsg, null, hldLvlCd, cpoDtlTMsg.exptFlg.getValue(), dcpoTMsg, dcpoDtlTMsg, dcpoConfigTMsg);
                        callValidationAPI(hldRsnCd, order, validPMsg, cpoTMsg, cpoDtlTMsg, null, hldLvlCd, cpoDtlTMsg.exptFlg.getValue(), dcpoConfigTMsg);
                        // 2017/03/29 S21_NA#Review structure Lv.1 Mod End

                        if (HLD_RSN.SHIP_COMPLETE_HOLD.equals(hldRsnCd)) {
                            break;
                        }
                    }
                }

                /*************************************************
                 * SHIPPING_PLAN
                 ************************************************/
            } else if (HLD_LVL.SHIPPING_PLAN_LEVEL.equals(hldLvlCd)) {

                for (Map<String, Object> order : orderList) {

                    final String cpoSrcTpCd = (String) order.get("CPO_SRC_TP_CD");
                    final String cpoOrdTpCd = (String) order.get("CD_CPO_ORD_TP_CD");
                    final String whTpCd = (String) order.get("whType");

                    final String cpoOrdNum = (String) order.get("CPO_ORD_NUM");
                    final String cpoDtlLineNum = (String) order.get("CPO_DTL_LINE_NUM");
                    final String cpoDtlLineSubNum = (String) order.get("CPO_DTL_LINE_SUB_NUM");
                    final String shpgPlnNum = (String) order.get("SHPG_PLN_NUM");
                    // final BigDecimal svcConfigMstrPk = (BigDecimal) order.get("SVC_CONFIG_MSTR_PK"); // 2015/12/03 S21_NA#1407Del
                    final BigDecimal dsCpoConfigPk = (BigDecimal) order.get("DS_CPO_CONFIG_PK"); // 2015/12/03 S21_NA#1407 add

                    if (!isValidOrdTp(applyOrdTpList, hldRsnCd, cpoSrcTpCd, cpoOrdTpCd, whTpCd)) {
                        continue;
                    }

                    final CPO_DTLTMsg cpoDtlTMsg = getCpoDtlTMsg(cpoDtlTMsgArray, cpoDtlLineNum, cpoDtlLineSubNum);
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                    final DS_CPO_DTLTMsg dcpoDtlTMsg = getDcpoDtlTMsg(dcpoDtlTMsgArray, cpoDtlLineNum, cpoDtlLineSubNum);
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del End
                    //final SHPG_PLNTMsg shpgPlnTMsg = getShpgPlnTMsg(shpgPlnTMsgArray, shpgPlnNum);
                    final SHPG_PLNTMsg inShpgPlnTMsg = new SHPG_PLNTMsg();
                    inShpgPlnTMsg.glblCmpyCd.setValue(glblCmpyCd);
                    inShpgPlnTMsg.shpgPlnNum.setValue(shpgPlnNum);
                    SHPG_PLNTMsg shpgPlnTMsg = (SHPG_PLNTMsg) findByKey(inShpgPlnTMsg);
                    
                    // final DS_CPO_CONFIGTMsg dcpoConfigTMsg = getDcpoConfigTMsg(dcpoConfigTMsgArray, svcConfigMstrPk); // 20150924 add -> 2015/12/03 S21_NA#1407Del
                    final DS_CPO_CONFIGTMsg dcpoConfigTMsg = getDcpoConfigTMsg(dcpoConfigTMsgArray, dsCpoConfigPk); // 2015/12/03 S21_NA#1407

                    if (!isHldCtrlOrg(cpoDtlTMsg, hldCtrlOrgList, hldRsnCd)) {
                        continue;
                    }

                    if (HLD_RSN.MISSING_TAX_EXEMPT_CERTIFICATE.equals(hldRsnCd)) {
                        if (!checkHldCtrlTaxExem(shpgPlnTMsg.glblCmpyCd.getValue(), shpgPlnTMsg.shipToCtryCd.getValue(), shpgPlnTMsg.shipToStCd.getValue())) {
                            continue;
                        }
                    }

                    // --------------------------------------------------
                    // call Validation API.
                    // --------------------------------------------------
                    final NWXC005001PMsg validPMsg = new NWXC005001PMsg();
                    setValue(validPMsg.glblCmpyCd, glblCmpyCd);
                    setValue(validPMsg.slsDt, param.slsDt);
                    setValue(validPMsg.cpoOrdNum_I, cpoOrdNum);
                    setValue(validPMsg.cpoDtlLineNum_I, cpoDtlLineNum);
                    setValue(validPMsg.cpoDtlLineSubNum_I, cpoDtlLineSubNum);
                    setValue(validPMsg.shpgPlnNum_I, shpgPlnNum);

                    writePerformanceProfilingLog(getClass(), " - HLD_LVL=[SHPG_PLN], HLD_RSN_CD=[" + hldRsnCd + "], HLD_RSN_NM=[" + hldRsnNm + "] (" + cpoOrdNum + "." + cpoDtlLineNum + "." + cpoDtlLineSubNum + "." + shpgPlnNum + ")");
                    // 2017/03/29 S21_NA#Review structure Lv.1 Mod Start
//                    callValidationAPI(hldRsnCd, order, validPMsg, cpoTMsg, cpoDtlTMsg, shpgPlnTMsg, hldLvlCd, cpoDtlTMsg.exptFlg.getValue(), dcpoTMsg, dcpoDtlTMsg, dcpoConfigTMsg);
                    callValidationAPI(hldRsnCd, order, validPMsg, cpoTMsg, cpoDtlTMsg, shpgPlnTMsg, hldLvlCd, cpoDtlTMsg.exptFlg.getValue(), dcpoConfigTMsg);
                    // 2017/03/29 S21_NA#Review structure Lv.1 Mod End

                    if (HLD_RSN.SHIP_COMPLETE_HOLD.equals(hldRsnCd)) {
                        break;
                    }
                }
            }
        }

        // 2015/12/03 S21_NA#1407 Update Parameter name svcConfigMstrPk -> dsCpoConfigPk
        // 20150924 add
//        private DS_CPO_CONFIGTMsg getDcpoConfigTMsg(DS_CPO_CONFIGTMsgArray dcpoConfigTMsgArray, BigDecimal svcConfigMstrPk) {
        private DS_CPO_CONFIGTMsg getDcpoConfigTMsg(DS_CPO_CONFIGTMsgArray dcpoConfigTMsgArray, BigDecimal dsCpoConfigPk) {
            for (int i = 0; i < dcpoConfigTMsgArray.getValidCount(); i++) {
                final DS_CPO_CONFIGTMsg dcpoConfigTMsg = dcpoConfigTMsgArray.no(i);
                // 2015/12/03 S21_NA#1407 Mod start
//                if (!ZYPCommonFunc.hasValue(dcpoConfigTMsg.svcConfigMstrPk)) {
//                    continue;
//                }
//                if (dcpoConfigTMsg.svcConfigMstrPk.getValue().compareTo(svcConfigMstrPk) == 0) {
                // 2015/12/03 S21_NA#1407 Delete End
                if (null == dsCpoConfigPk) {
                    return null;
                }
                if (dcpoConfigTMsg.dsCpoConfigPk.getValue().compareTo(dsCpoConfigPk) == 0) {
                    return dcpoConfigTMsg;
                }
                // 2015/12/03 S21_NA#1407 Mod End
            }
            return null;
        }

        private void addHld(Map<String, Object> hldOrd, BigDecimal hldQty) {

            String hldRsnCd = (String) hldOrd.get("HLD_RSN_CD");
            String cpoOrdNum = (String) hldOrd.get("CPO_ORD_NUM");
            String cpoDtlLineNum = (String) hldOrd.get("CPO_DTL_LINE_NUM");
            String cpoDtlLineSubNum = (String) hldOrd.get("CPO_DTL_LINE_SUB_NUM");
            String shpgPlnNum = (String) hldOrd.get("SHPG_PLN_NUM");

            int level = 0;
            if (hasValue(shpgPlnNum)) {
                level = 3;
            } else if (hasValue(cpoDtlLineNum) && hasValue(cpoDtlLineSubNum)) {
                level = 2;
            } else {
                level = 1;
            }

            HLDTMsg hldTMsg = new HLDTMsg();
            hldTMsg.setSQLID("010");
            hldTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            hldTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
            hldTMsg.setConditionValue("cpoDtlLineNum01", cpoDtlLineNum);
            hldTMsg.setConditionValue("cpoDtlLineSubNum01", cpoDtlLineSubNum);
            hldTMsg.setConditionValue("shpgPlnNum01", shpgPlnNum);
            hldTMsg.setConditionValue("hldRsnCd01", hldRsnCd);
            hldTMsg.setConditionValue("relFlg01", N);

            HLDTMsgArray hldTMsgArray = (HLDTMsgArray) findByConditionForUpdate(hldTMsg);

            // --------------------------------------------------
            // Update
            // --------------------------------------------------
            if (hldTMsgArray.getValidCount() > 0) {

                if (level == 3) {

                    hldTMsg = (HLDTMsg) hldTMsgArray.get(0);
                    if (HLD_RSN.CREDIT_LIMIT.equals(hldRsnCd)) {
                        setValue(hldTMsg.hldQty, hldTMsg.hldQty.getValue().add(hldQty));
                    } else {
                        setValue(hldTMsg.hldQty, hldQty);
                    }

                    // ***** update
                    update(hldTMsg);

                    if (!RTNCD_NORMAL.equals(hldTMsg.getReturnCode())) {
                        addXxMsgId(NWZM0080E);
                    }
                }

                // --------------------------------------------------
                // Insert
                // --------------------------------------------------
            } else {

                setValue(hldTMsg.glblCmpyCd, glblCmpyCd);
                setValue(hldTMsg.hldPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.HLD_SQ));
                setValue(hldTMsg.hldRsnCd, hldRsnCd);
                setValue(hldTMsg.hldDt, param.slsDt);
                setValue(hldTMsg.relFlg, N);
                setValue(hldTMsg.rvwFlg, N);

                if (level == 1) {
                    setValue(hldTMsg.cpoOrdNum, cpoOrdNum);
                } else if (level == 2) {
                    setValue(hldTMsg.cpoOrdNum, cpoOrdNum);
                    setValue(hldTMsg.cpoDtlLineNum, cpoDtlLineNum);
                    setValue(hldTMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
                    setValue(hldTMsg.hldQty, hldQty);
                } else {
                    setValue(hldTMsg.cpoOrdNum, cpoOrdNum);
                    setValue(hldTMsg.cpoDtlLineNum, cpoDtlLineNum);
                    setValue(hldTMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
                    setValue(hldTMsg.shpgPlnNum, shpgPlnNum);
                    setValue(hldTMsg.hldQty, hldQty);
                }

                // ***** insert
                insert(hldTMsg);

                if (!RTNCD_NORMAL.equals(hldTMsg.getReturnCode())) {
                    addXxMsgId(NWZM0080E);
                }
            }
        }

        // Def#1481 Delete
        //        private S21ApiCommonBase getAPI(Class<? extends S21ApiCommonBase> clazz) {
        //            
        //            if (!apiCache.containsKey(clazz)) {
        //                try {
        //                    apiCache.put(clazz, (S21ApiCommonBase) Class.forName(clazz.getName()).newInstance());
        //                } catch (Exception e) {
        //                    throw new S21AbendException(e);
        //                }
        //            }
        //            
        //            return apiCache.get(clazz);
        //        }

        private void cacheExReqFlg(String glblCmpyCd, String hldRsnCd, String exReqFlg) {

            final HldRsnTMsgCacheKey cacheKey = NWXHldRsnTMsgThreadLocalCache.getInstance().createCacheKey(glblCmpyCd, hldRsnCd);

            final HLD_RSNTMsg hldRsnTMsg = new HLD_RSNTMsg();
            hldRsnTMsg.exReqFlg.setValue(exReqFlg);

            NWXHldRsnTMsgThreadLocalCache.getInstance().cache(cacheKey, hldRsnTMsg);
        }

        //WDS#110 START
        //        private void callValidationAPI(String hldRsnCd, Map<String, Object> order, NWXC005001PMsg valParam, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDtlTMsg, SHPG_PLNTMsg shpgPlnTMsg, String hldLvlCd, String exptFlg) {
        //            callValidationAPI(hldRsnCd, order, valParam, cpoTMsg, cpoDtlTMsg, shpgPlnTMsg, hldLvlCd, exptFlg, null, null);
        //        }
        //WDS#110 E N D

        // ********** [WDS#110] - START
        private void callValidationAPI(//
                String hldRsnCd //
                , Map<String, Object> order //
                , NWXC005001PMsg valParam //
                , CPOTMsg cpoTMsg //
                , CPO_DTLTMsg cpoDtlTMsg //
                , SHPG_PLNTMsg shpgPlnTMsg //
                , String hldLvlCd //
                , String exptFlg //
                //, DS_CPOTMsg dcpoTMsg // 2017/03/29 S21_NA#Review structure Lv.1 Del
                //, DS_CPO_DTLTMsg dcpoDtlTMsg // 2017/03/29 S21_NA#Review structure Lv.1 Del
                , DS_CPO_CONFIGTMsg dcpoConfigTMsg) {
            // ********** [WDS#110] - E N D

            // QC#7180 add Start
            if (ZYPConstant.FLG_ON_Y.equals(cpoTMsg.ordBookFlg.getValue())) {
                HLD_RSNTMsg hldRsnTMsg = new HLD_RSNTMsg();
                setValue(hldRsnTMsg.glblCmpyCd, glblCmpyCd);
                setValue(hldRsnTMsg.hldRsnCd, hldRsnCd);
                hldRsnTMsg = (HLD_RSNTMsg) S21FastTBLAccessor.findByKey(hldRsnTMsg);
                // mod start 2023/04/25 QC#61337
                //if (hldRsnTMsg != null
                //    && ZYPConstant.FLG_ON_Y.equals(hldRsnTMsg.blockBookHldRelFlg.getValue())) {
                //    return;
                //}
                if (hldRsnTMsg != null //
                        && skipHldValidation(hldRsnCd, hldRsnTMsg.blockBookHldRelFlg.getValue(), cpoTMsg.amtChngInd.getValue())) {
                    return;
                }
                // mod end 2023/04/25 QC#61337
            }
            // QC#7180 add End

            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            /*********************************************************
//             * TOP_STOP
//             ********************************************************/
//            if (HLD_RSN.TOP_STOP.equals(hldRsnCd)) {

//                final NWZC106001PMsg apiPMsg = new NWZC106001PMsg();
//                setValue(apiPMsg.glblCmpyCd, valParam.glblCmpyCd);
//                setValue(apiPMsg.slsDt, valParam.slsDt);
//                setValue(apiPMsg.shpgPlnNum, valParam.shpgPlnNum_I);
//                setValue(apiPMsg.cpoOrdNum, valParam.cpoOrdNum_I);
//                setValue(apiPMsg.cpoDtlLineNum, valParam.cpoDtlLineNum_I);
//                setValue(apiPMsg.cpoDtlLineSubNum, valParam.cpoDtlLineSubNum_I);
//
//                // call API.
//                ((NWZC106001) getAPI(NWZC106001.class)).execute(apiPMsg, onBatchType);
//
//                // has API errors?
//                final int apiErrMsgIdList = apiPMsg.xxMsgIdList.getValidCount();
//                if (apiErrMsgIdList > 0) {
//                    for (int i = 0; i < apiErrMsgIdList; i++) {
//                        addXxMsgId(apiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
//                    }
//                    return;
//                }
//
//                // --- HLD
//                final String resHldRsnCd = apiPMsg.hldRsnCd_O.getValue();
//                if (hasValue(resHldRsnCd)) {
//
//                    final Map<String, Object> hldOrd = new HashMap<String, Object>();
//                    hldOrd.put("CPO_ORD_NUM", apiPMsg.cpoOrdNum_O.getValue());
//                    hldOrd.put("CPO_DTL_LINE_NUM", apiPMsg.cpoDtlLineNum_O.getValue());
//                    hldOrd.put("CPO_DTL_LINE_SUB_NUM", apiPMsg.cpoDtlLineSubNum_O.getValue());
//                    hldOrd.put("SHPG_PLN_NUM", apiPMsg.shpgPlnNum_O.getValue());
//                    hldOrd.put("HLD_RSN_CD", resHldRsnCd);
//                    //                    hldOrd.put("ORD_QTY",              (BigDecimal) order.get("S_ORD_QTY")); 12/30/2010 1047(All2)
//                    hldOrd.put("ORD_QTY", apiPMsg.ordQty_O.getValue());
//
//                    execHld(hldOrd);
//                }
//
//                /*****************************************************
//                 * UNREGISTERED_EXPORT_SALES_CONDITION
//                 ****************************************************/
//            } else if (HLD_RSN.UNREGISTERED_EXPORT_SALES_CONDITION.equals(hldRsnCd) && Y.equals(exptFlg)) {
//
//                final NTZC008001PMsg apiPMsg = new NTZC008001PMsg();
//                setValue(apiPMsg.glblCmpyCd, valParam.glblCmpyCd);
//                setValue(apiPMsg.slsDt, valParam.slsDt);
//                setValue(apiPMsg.cpoOrdNum, valParam.cpoOrdNum_I);
//                setValue(apiPMsg.cpoDtlLineNum, valParam.cpoDtlLineNum_I);
//                setValue(apiPMsg.cpoDtlLineSubNum, valParam.cpoDtlLineSubNum_I);
//
//                // call API.
//                ((NTZC008001) getAPI(NTZC008001.class)).execute(apiPMsg, onBatchType);

//                // --- HLD
//                if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
//
//                    final Map<String, Object> hldOrd = new HashMap<String, Object>();
//                    hldOrd.put("CPO_ORD_NUM", apiPMsg.cpoOrdNum.getValue());
//                    if (!hasValue(apiPMsg.cpoDtlLineNum)) {
//                        hldOrd.put("CPO_DTL_LINE_NUM", null);
//                    } else {
//                        hldOrd.put("CPO_DTL_LINE_NUM", apiPMsg.cpoDtlLineNum.getValue());
//                    }
//                    if (!hasValue(apiPMsg.cpoDtlLineSubNum)) {
//                        hldOrd.put("CPO_DTL_LINE_SUB_NUM", null);
//                    } else {
//                        hldOrd.put("CPO_DTL_LINE_SUB_NUM", apiPMsg.cpoDtlLineSubNum.getValue());
//                    }
//
//                    hldOrd.put("SHPG_PLN_NUM", null);
//                    hldOrd.put("HLD_RSN_CD", hldRsnCd);
//                    hldOrd.put("ORD_QTY", (BigDecimal) order.get("CD_ORD_QTY"));
//
//                    execHld(hldOrd);
//                }

//                /*****************************************************
//                 * UNREGISTERED_MDSE_EXPORT_MASTER
//                 ****************************************************/
//            } else if (HLD_RSN.UNREGISTERED_MDSE_EXPORT_MASTER.equals(hldRsnCd) && Y.equals(exptFlg)) {
//
//                final NTZC009001PMsg apiPMsg = new NTZC009001PMsg();
//                setValue(apiPMsg.glblCmpyCd, valParam.glblCmpyCd);
//                setValue(apiPMsg.slsDt, valParam.slsDt);
//                setValue(apiPMsg.cpoOrdNum, valParam.cpoOrdNum_I);
//                setValue(apiPMsg.cpoDtlLineNum, valParam.cpoDtlLineNum_I);
//                setValue(apiPMsg.cpoDtlLineSubNum, valParam.cpoDtlLineSubNum_I);
//
//                // call API.
//                ((NTZC009001) getAPI(NTZC009001.class)).execute(apiPMsg, onBatchType);

                // --- HLD
//                if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
//
//                    final Map<String, Object> hldOrd = new HashMap<String, Object>();
//                    hldOrd.put("CPO_ORD_NUM", apiPMsg.cpoOrdNum.getValue());
//                    if (!hasValue(apiPMsg.cpoDtlLineNum)) {
//                        hldOrd.put("CPO_DTL_LINE_NUM", null);
//                    } else {
//                        hldOrd.put("CPO_DTL_LINE_NUM", apiPMsg.cpoDtlLineNum.getValue());
//                    }
//                    if (!hasValue(apiPMsg.cpoDtlLineSubNum)) {
//                        hldOrd.put("CPO_DTL_LINE_SUB_NUM", null);
//                    } else {
//                        hldOrd.put("CPO_DTL_LINE_SUB_NUM", apiPMsg.cpoDtlLineSubNum.getValue());
//                    }
//                    hldOrd.put("SHPG_PLN_NUM", null);
//                    hldOrd.put("HLD_RSN_CD", hldRsnCd);
//                    hldOrd.put("ORD_QTY", (BigDecimal) order.get("CD_ORD_QTY"));
//
//                    execHld(hldOrd);
//                }
//
//            /*****************************************************
//             * EXPORT_LICENSE
//             ****************************************************/
//            } else if (HLD_RSN.EXPORT_LICENSE.equals(hldRsnCd) && Y.equals(exptFlg)) {
//
//                final NWZC404001PMsg apiPMsg = new NWZC404001PMsg();
//                setValue(apiPMsg.glblCmpyCd, valParam.glblCmpyCd);
//                setValue(apiPMsg.slsDt, valParam.slsDt);
//                setValue(apiPMsg.cpoOrdNum, valParam.cpoOrdNum_I);
//                setValue(apiPMsg.cpoDtlLineNum, valParam.cpoDtlLineNum_I);
//                setValue(apiPMsg.cpoDtlLineSubNum, valParam.cpoDtlLineSubNum_I);
//
//                // call API.
//                ((NWZC404001) getAPI(NWZC404001.class)).execute(apiPMsg, onBatchType);
//
//                // has API errors?
//                final int apiErrMsgIdList = apiPMsg.xxMsgIdList.getValidCount();
//                if (apiErrMsgIdList > 0) {
//                    for (int i = 0; i < apiErrMsgIdList; i++) {
//                        addXxMsgId(apiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
//                    }
//                    return;
//                }
//
//                // --- HLD
//                if (apiPMsg.xxHoldDataList.getValidCount() > 0) {
//
//                    final List<Map<String, Object>> hldOrdList = new ArrayList<Map<String, Object>>();
//
//                    for (int i = 0; i < apiPMsg.xxHoldDataList.getValidCount(); i++) {
//
//                        final NWZC404001_xxHoldDataListPMsg xxHldPMsg = apiPMsg.xxHoldDataList.no(i);
//
//                        final Map<String, Object> hldOrd = new HashMap<String, Object>();
//                        hldOrdList.add(hldOrd);
//                        hldOrd.put("CPO_ORD_NUM", xxHldPMsg.cpoOrdNum_A1.getValue());
//                        hldOrd.put("CPO_DTL_LINE_NUM", xxHldPMsg.cpoDtlLineNum_A1.getValue());
//                        hldOrd.put("CPO_DTL_LINE_SUB_NUM", xxHldPMsg.cpoDtlLineSubNum_A1.getValue());
//                        hldOrd.put("SHPG_PLN_NUM", null);
//                        hldOrd.put("HLD_RSN_CD", hldRsnCd);
//                        hldOrd.put("ORD_QTY", (BigDecimal) order.get("CD_ORD_QTY"));
//                    }
//
//                    execHld(hldOrdList);
//                }

//            } else 2017/03/29 S21_NA#Review structure Lv.1 Del Start End
            /*****************************************************
             * SHIP_COMPLETE_HOLD
             ****************************************************/
            if (HLD_RSN.SHIP_COMPLETE_HOLD.equals(hldRsnCd)) {

                final NWXC005001PMsg apiPMsg = new NWXC005001PMsg();
                setValue(apiPMsg.glblCmpyCd, valParam.glblCmpyCd);
                setValue(apiPMsg.slsDt, valParam.slsDt);
                setValue(apiPMsg.cpoOrdNum_I, valParam.cpoOrdNum_I);

                /****************************************************************************************************
                 * case : Order Entry Submit (New Entry) -> CPO Update
                 * API -> Order Validation -> Allocaion API -> Hard
                 * Allocation API -> Shipping Validaion (*this case)
                 ****************************************************************************************************/
                if (isRequestByOrderEntryScrnNewEntry()) {
                    final String cpoOrdNum = apiPMsg.cpoOrdNum_I.getValue();
                    final String doneCpoOrdNum = (String) getFromThread(ThreadKey.DONE_SHIP_CPLT_VALIDATION_CPO_ORD_NUM.toString());
                    if (doneCpoOrdNum != null) {
                        if (doneCpoOrdNum.equals(cpoOrdNum)) {
                            // skip. because ship complete validaion is already done.
                            return;
                        }
                    }
                    putInThread(ThreadKey.DONE_SHIP_CPLT_VALIDATION_CPO_ORD_NUM.toString(), cpoOrdNum);
                }

                final List<Map<String, Object>> hldOrdList = execShipCpltValidation(new NWXC005001ValidationBean(apiPMsg, cpoTMsg, null, null));

                // has API errors?
                final int apiErrMsgIdList = apiPMsg.xxMsgIdList.getValidCount();
                if (apiErrMsgIdList > 0) {
                    for (int i = 0; i < apiErrMsgIdList; i++) {
                        addXxMsgId(apiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    }
                    return;
                }

                // --- HLD
                if (!hldOrdList.isEmpty()) {
                    execHld(hldOrdList);
                }

                /***************************************************************
                 * <pre>Other (Use Common Interface Bean : ValidationManagerBean)</pre>
                 ***************************************************************/
            } else {

                // --------------------------------------------------
                // ValidationManagerBean
                // --------------------------------------------------
                // ********** [WDS#110] - START
                final ValidationManagerBean validBean // 2017/03/29 S21_NA#Review structure Lv.1 Mod Start
//                = new ValidationManagerBean(valParam, cpoTMsg, cpoDtlTMsg, shpgPlnTMsg, dcpoTMsg, dcpoDtlTMsg, dcpoConfigTMsg);
                = new ValidationManagerBean(valParam, cpoTMsg, cpoDtlTMsg, shpgPlnTMsg, dcpoConfigTMsg);
                // 2017/03/29 S21_NA#Review structure Lv.1 Mod End
                // ********** [WDS#110] - E N D

                // ORD_QTY
                final BigDecimal ordQty;
                if (HLD_LVL.CPO_LEVEL.equals(hldLvlCd)) {
                    ordQty = null;
                } else if (HLD_LVL.CPO_DETAIL_LEVEL.equals(hldLvlCd)) {
                    ordQty = (BigDecimal) order.get("CD_ORD_QTY");
                } else if (HLD_RSN.CREDIT_LIMIT.equals(hldRsnCd)) {
                    ordQty = (BigDecimal) order.get("crChkQty");
                } else {
                    ordQty = (BigDecimal) order.get("S_ORD_QTY");
                }
                validBean.setOrdQty(ordQty);

                /*************************************************
                 * CREDIT_LIMIT
                 ************************************************/
                if (HLD_RSN.CREDIT_LIMIT.equals(hldRsnCd)) {
                    // S21_NA#14973 Add Start
                    if (!hasExemptionFlg && !isAllLineCreditFlg) {
                        // 2018/01/18 QC#22737-2 add start
                        if (EQUIP.equals(crChkMode) || (BILLABLE.equals(crChkMode) && chkReqFlg)) {
                            execCreditLimitValidation(hldRsnCd, validBean);

                            if (hasValue(validBean.getInputPMsg().hldRsnCd)) {
                                crLimitFlg = true;
                            }
                        }
                        // 2018/01/18 QC#22737-2 add end
                    }
                    // S21_NA#14973 Add End

                    // S21_NA#14973 Del Start
//                    //20150924 add start
//                    //if (isCrChkExem(param, dcpoTMsg.billToCustAcctCd.getValue())) { // S21_NA#14973 Mod
//                    if (hasExemptionFlg) { // S21_NA#14973 Mod
//                        return;
//                    }
//                    //20150924 add end
//
//                    //WDS#110 START
//                    // START 2013/02/18 S.Iidaka [WDS#Def 704 MOD]
//                    if (isCreditCardPayment(validBean.getSpTMsg()) || isPriceZero(validBean.getSpTMsg())) {
//                        // E N D 2013/02/18 S.Iidaka [WDS#Def 704 MOD]
//                        return;
//                    }
//                    //WDS#110 END
//
//                    execCreditLimitValidation(hldRsnCd, validBean);
                    // S21_NA#14973 Del End

                    /*********************************************
                     * CREDIT_PROFILE
                     ********************************************/
                } else if (HLD_RSN.CREDIT_PROFILE.equals(hldRsnCd)) {

                    // S21_NA#13323 and 14973
                    if (!hasExemptionFlg && !isAllLineCreditFlg) {
                        ((NWZC011001) getAPI(NWZC011001.class)).execute(validBean, onBatchType);

                        // 2018/01/18 QC#22737-2 add start
                        if (hasValue(validBean.getInputPMsg().hldRsnCd)) {
                            for (int i=0; i < validBean.getInputPMsg().modeCd.getValidCount(); i++) {
                                if (CHK_REQ.equals(validBean.getInputPMsg().modeCd.no(i).xxModeCd.getValue())) {
                                    chkReqFlg = true;
                                } else {
                                    crProfileFlg = true;
                                }
                            }
                        }
                        // 2018/01/18 QC#22737-2 add end
                    }

                    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                    /*********************************************
//                     * CREDIT_CHECK_DATE_PAST
//                     ********************************************/
//                } else if (HLD_RSN.CREDIT_CHECK_DATE_PAST.equals(hldRsnCd)) {
//
//                    ((NWZC016001) getAPI(NWZC016001.class)).execute(validBean, onBatchType);

//                    /*********************************************
//                     * LAST_INVOICE_DATE
//                     ********************************************/
//                } else if (HLD_RSN.LAST_INVOICE_DATE.equals(hldRsnCd)) {
//
//                    ((NWZC012001) getAPI(NWZC012001.class)).execute(validBean, onBatchType);
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del End

                    /*********************************************
                     * MISSING_TAX_EXEMPT_CERTIFICATE
                     ********************************************/
                } else if (HLD_RSN.MISSING_TAX_EXEMPT_CERTIFICATE.equals(hldRsnCd)) {
                    //                    ((NWZC013001) getAPI(NWZC013001.class)).execute(validBean, onBatchType);

                    /*********************************************
                     * FLOOR_PLAN
                     ********************************************/
                } else if (HLD_RSN.FLOOR_PLAN.equals(hldRsnCd)) {

                    execFloorPlanValidation(hldRsnCd, validBean);

                    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                    /*********************************************
//                     * MANUAL_TOC
//                     ********************************************/
//                } else if (HLD_RSN.MANUAL_TOC.equals(hldRsnCd)) {
//                    ((NWZC023001) getAPI(NWZC023001.class)).execute(validBean, onBatchType);

//                    /*********************************************
//                     * MANUAL_SHIP_TO_ADDRESS
//                     ********************************************/
//                } else if (HLD_RSN.MANUAL_SHIP_TO_ADDRESS.equals(hldRsnCd)) {
//
//                    ((NWZC009001) getAPI(NWZC009001.class)).execute(validBean, onBatchType);
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del End

                    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                    /*********************************************
//                     * MANUAL_TERMS_OF_PAYMENT
//                     ********************************************/
//                } else if (HLD_RSN.MANUAL_TERMS_OF_PAYMENT.equals(hldRsnCd)) {
//                    ((NWZC014001) getAPI(NWZC014001.class)).execute(validBean, onBatchType);
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del End

                    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                    /*********************************************
//                     * MANUAL_PRICE
//                     ********************************************/
//                } else if (HLD_RSN.MANUAL_PRICE.equals(hldRsnCd)) {
//                    ((NWZC015001) getAPI(NWZC015001.class)).execute(validBean, onBatchType);

//                    /*********************************************
//                     * SHIPPING_CONTRACT_RESTRICTION
//                     ********************************************/
//                } else if (HLD_RSN.SHIPPING_CONTRACT_RESTRICTION.equals(hldRsnCd)) {
//
//                    ((NWZC010001) getAPI(NWZC010001.class)).execute(validBean, onBatchType);

//                    /*********************************************
//                     * SHIPPING_HOLD
//                     ********************************************/
//                } else if (HLD_RSN.SHIPPING_HOLD.equals(hldRsnCd)) {
//
//                    ((NWZC018001) getAPI(NWZC018001.class)).execute(validBean, onBatchType);

//                    /*********************************************
//                     * THIRD_PARTY_SHIPPING_LAVEL
//                     ********************************************/
//                } else if (HLD_RSN.THIRD_PARTY_SHIPPING_LAVEL.equals(hldRsnCd)) {
//
//                    ((NWZC005001) getAPI(NWZC005001.class)).execute(validBean, onBatchType);
//
//                    // START DELETE S.Yamamoto [MS001]
//                    //                /*********************************************
//                    //                 * TRAINING_ORDER_HOLD
//                    //                 ********************************************/
//                    //                } else if (HLD_RSN.TRAINING_ORDER_HOLD.equals(hldRsnCd)) {
//                    //
//                    //                    ((NWZC024001) getAPI(NWZC024001.class)).execute(validBean, onBatchType);
//                    // END   DELETE S.Yamamoto [MS001]

//                    /*********************************************
//                     * CUSTOMER_HOLD
//                     ********************************************/
//                } else if (HLD_RSN.CUSTOMER_HOLD.equals(hldRsnCd)) {
//
//                    ((NWZC048001) getAPI(NWZC048001.class)).execute(validBean, onBatchType);

//                    /*********************************************
//                     * CUSTOMER_HOLD_WITH_DAYS
//                     ********************************************/
//                } else if (HLD_RSN.CUSTOMER_HOLD_WITH_DAYS.equals(hldRsnCd)) {
//
//                    ((NWZC049001) getAPI(NWZC049001.class)).execute(validBean, onBatchType);

//                    /*********************************************
//                     * ITEM_HOLD
//                     ********************************************/
//                } else if (HLD_RSN.ITEM_HOLD.equals(hldRsnCd)) {
//
//                    ((NWZC050001) getAPI(NWZC050001.class)).execute(validBean, onBatchType);

//                    /*********************************************
//                     * ITEM_HOLD_WITH_DAYS
//                     ********************************************/
//                } else if (HLD_RSN.ITEM_HOLD_WITH_DAYS.equals(hldRsnCd)) {
//
//                    ((NWZC051001) getAPI(NWZC051001.class)).execute(validBean, onBatchType);

//                    /*********************************************
//                     * COMPONENT_NOT_SOLD_SEPARATE
//                     ********************************************/
//                } else if (HLD_RSN.COMPONENT_NOT_SOLD_SEPARATE.equals(hldRsnCd)) {
//
//                    ((NWZC052001) getAPI(NWZC052001.class)).execute(validBean, onBatchType);

//                    /*********************************************
//                     * ORDER_QUANTITY_ERROR_HLD
//                     ********************************************/
//                } else if (HLD_RSN.ORDER_QUANTITY_ERROR_HLD.equals(hldRsnCd)) {
//
//                    ((NWZC110001) getAPI(NWZC110001.class)).execute(validBean, onBatchType);
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del End

                    /*********************************************
                     * OVER_DUE
                     ********************************************/
                } else if (HLD_RSN.OVER_DUE.equals(hldRsnCd)) {

                    // S21_NA#13323 and 14973
                    if (!hasExemptionFlg && !isAllLineCreditFlg) {
                        // 2018/01/18 QC#22737-2 add start
                        if (EQUIP.equals(crChkMode) || (BILLABLE.equals(crChkMode) && chkReqFlg)) {
                            ((NWZC019001) getAPI(NWZC019001.class)).execute(validBean, onBatchType);

                            if (hasValue(validBean.getInputPMsg().hldRsnCd)) {
                                overdueFlg = true;
                            }
                        }
                        // 2018/01/18 QC#22737-2 add end
                        // 2018/07/06 QC#27079 add start
                        if (CONTRACT.equals(crChkMode) && chkReqFlg) {
                            ((NWZC193001) getAPI(NWZC193001.class)).execute(validBean, onBatchType);

                            if (hasValue(validBean.getInputPMsg().hldRsnCd)) {
                                overdueFlg = true;
                            }
                        }
                        // 2018/07/06 QC#27079 add end
                    }

                    // START 01/07/2013 K.Yamada [Def#3292,DEL]
                    // ********** [WDS#110] - START
                    /*********************************************
                     * WATING_OSI_INSTALL
                     ********************************************/
                    //} else if (HLD_RSN.WAITING_OSI_INSTALL.equals(hldRsnCd)) {
                    //    ((NWZC200001) getAPI(NWZC200001.class)).execute(validBean, onBatchType);
                    // ********** [WDS#110] - E N D
                    // END 01/07/2013 K.Yamada [Def#3292,DEL]
                    // ********** [WDS#113] - START
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                    /*********************************************
//                     * PRE_AUTHORIZATION_HOLD
//                     ********************************************/
//                } else if (HLD_RSN.PRE_AUTHORIZATION_HOLD.equals(hldRsnCd)) {
//
//                    ((NWZC204001) getAPI(NWZC204001.class)).execute(validBean, onBatchType);
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del End
                    // ********** [WDS#113] - E N D
                    // 20150924 add start
                    // Sales Hold
                } else if (HLD_RSN.VALIDATION_HOLD.equals(hldRsnCd)) {
                    ((NWZC158001) getAPI(NWZC158001.class)).execute(validBean, onBatchType);

                } else if (HLD_RSN.PROFITABILITY_HOLD.equals(hldRsnCd)) {
                    ((NWZC159001) getAPI(NWZC159001.class)).execute(validBean, onBatchType);

                } else if (HLD_RSN.SUPPLY_YIELD_VALIDATION_HOLD.equals(hldRsnCd)) {
                    if (!isAllLineCreditFlg) { // QC#15960 2016/11/15 Add
                        ((NSZC063001) getAPI(NSZC063001.class)).execute(validBean, onBatchType);
                    }

                } else if (HLD_RSN.PENDING_ORDER_HOLD.equals(hldRsnCd)) {
                    if (!isAllLineCreditFlg) { // QC#15960 2016/11/15 Add
                        ((NSZC064001) getAPI(NSZC064001.class)).execute(validBean, onBatchType);
                    }

                } else if (HLD_RSN.CONTRACT_STATUS_HOLD.equals(hldRsnCd)) {
                    if (!isAllLineCreditFlg) { // QC#15960 2016/11/15 Add
                        ((NSZC065001) getAPI(NSZC065001.class)).execute(validBean, onBatchType);
                    }

                } else if (HLD_RSN.SUPPLY_ENFORCEMENT_HOLD.equals(hldRsnCd)) {
                    if (!isAllLineCreditFlg) { // QC#15960 2016/11/15 Add
                        ((NSZC066001) getAPI(NSZC066001.class)).execute(validBean, onBatchType);
                    }

                } else if (HLD_RSN.BUYOUT_APPROVAL.equals(hldRsnCd)) {
                    ((NWZC175001) getAPI(NWZC175001.class)).execute(validBean, onBatchType);

                } else if (HLD_RSN.BUYOUT_BILLING.equals(hldRsnCd)) {
                    ((NWZC176001) getAPI(NWZC176001.class)).execute(validBean, onBatchType);

                } else if (HLD_RSN.ITT_OUTBOUND_HOLD.equals(hldRsnCd)) {
                    ((NWZC179001) getAPI(NWZC179001.class)).execute(validBean, onBatchType);

//QC#15745 Add Start
                } else if (HLD_RSN.CONTRACT_CREDIT_REVIEW.equals(hldRsnCd)) {
                    if (!hasExemptionFlg && !isAllLineCreditFlg) {
                        // 2018/01/18 QC#22737-2 add start
                        if (EQUIP.equals(crChkMode) || (CONTRACT.equals(crChkMode) && chkReqFlg)) {
                            ((NWZC178001) getAPI(NWZC178001.class)).execute(validBean, onBatchType);

                            if (hasValue(validBean.getInputPMsg().hldRsnCd)) {
                                contrHldFlg = true;
                            }
                        }
                        // 2018/01/18 QC#22737-2 add end
                    }
//QC#15745 Add End
                    // Credit Hold
                } else if (HLD_RSN.PO_REVIEW.equals(hldRsnCd)) {
                    // S21_NA#13323 and 14973
                    if (!hasExemptionFlg && !isAllLineCreditFlg) {
                        ((NWZC173001) getAPI(NWZC173001.class)).execute(validBean, onBatchType);
                    }

                } else if (HLD_RSN.FINAL_REVIEW.equals(hldRsnCd)) {
                    // S21_NA#13323 and 14973
                    if (!hasExemptionFlg && !isAllLineCreditFlg) {
                        ((NWZC174001) getAPI(NWZC174001.class)).execute(validBean, onBatchType);
                    }
                    // 20150924 add end

                // add start 2023/04/25 QC#61337
                } else if (HLD_RSN.MANUAL_PRICE.equals(hldRsnCd)) {
                    ((NWZC195001) getAPI(NWZC195001.class)).execute(validBean, onBatchType);
                // add end 2023/05/25 QC#61337

                } else {
                    return;
                }

                final NWXC005001PMsg apiPMsg = validBean.getInputPMsg();

                // has API errors?
                final int apiErrMsgIdList = apiPMsg.xxMsgIdList.getValidCount();
                if (apiErrMsgIdList > 0) {
                    for (int i = 0; i < apiErrMsgIdList; i++) {
                        addXxMsgId(apiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    }
                    return;
                }

                // S21_NA#14973 Add Start
                if (apiPMsg.shpgPlnList.getValidCount() > 0) {
                    execHldForBulk(apiPMsg);
                    return;
                }
                // S21_NA#14973 Add End

                // ----- HLD
                if (hasValue(apiPMsg.hldRsnCd)) {

                    // 2018/01/18 QC#22737-2 add start
                    ZYPEZDItemValueSetter.setValue(apiPMsg.ordQty_O, ((ValidationManagerBean) validBean).getOrdQty());
                    hldParamList.add(apiPMsg);
                    // 2018/01/18 QC#22737-2 add end
                    // 2018/01/18 QC#22737-2 del start
//                    final Map<String, Object> hldOrd = new HashMap<String, Object>();
//                    hldOrd.put("HLD_RSN_CD", apiPMsg.hldRsnCd.getValue());
//                    hldOrd.put("CPO_ORD_NUM", apiPMsg.cpoOrdNum_O.getValue());
//                    hldOrd.put("CPO_DTL_LINE_NUM", apiPMsg.cpoDtlLineNum_O.getValue());
//                    hldOrd.put("CPO_DTL_LINE_SUB_NUM", apiPMsg.cpoDtlLineSubNum_O.getValue());
//                    hldOrd.put("SHPG_PLN_NUM", apiPMsg.shpgPlnNum_O.getValue());
//                    hldOrd.put("ORD_QTY", ((ValidationManagerBean) validBean).getOrdQty());
//
//                    execHld(hldOrd);
                    // 2018/01/18 QC#22737-2 del end
                }
            }
        }

        // S21_NA#14973 Del Start
        // 20150924 add
//        private boolean isCrChkExem(NWZC004001PMsg param, String dsAcctNum) {
//            final Map<String, String> ssmParam = new HashMap<String, String>();
//            ssmParam.put("glblCmpyCd", glblCmpyCd);
//            ssmParam.put("dsAcctNum", dsAcctNum);
//            ssmParam.put("slsDt", param.slsDt.getValue());
//
//            BigDecimal cnt = (BigDecimal) ssmClient.queryObject("cntCrChkExem", ssmParam);
//
//            return (BigDecimal.ZERO.compareTo(cnt) < 0);
//        }
        // S21_NA#14973 Del End

        @SuppressWarnings("unchecked")
        private boolean checkHldCtrlTaxExem(final String glblCmpyCd, final String paramCtryCd, final String paramStCd) {

            // ---------------------------------------------------------------------
            // If country and state doesn't have any sales tax, tax Hold is not create.
            // ---------------------------------------------------------------------

            // find & cache
            String key = glblCmpyCd;
            List<Map<String, Object>> hldCtrlTaxExemCacheList = hldCtrlTaxExemCache.get(key);

            // -------------
            // find
            // -------------
            if (isEmpty(hldCtrlTaxExemCacheList)) {

                final Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);

                final List<Map<String, Object>> taxExemList = ssmClient.queryObjectList("queryHoldCtrlTaxExem", ssmParam);

                if (isEmpty(taxExemList)) {

                    // -------------
                    // create tax hold
                    // -------------
                    final Map<String, Object> map = new HashMap<String, Object>();
                    map.put("none", "none");

                    final List<Map<String, Object>> listEmptyMap = new ArrayList<Map<String, Object>>();
                    listEmptyMap.add(map);

                    hldCtrlTaxExemCache.put(key, listEmptyMap);

                    return true;

                } else {

                    boolean isTaxExemFlg = true;

                    for (Map<String, Object> taxExem : taxExemList) {

                        String ctryCd = (String) taxExem.get("CTRY_CD");
                        String stCd = (String) taxExem.get("ST_CD");

                        if (hasValue(ctryCd) && hasValue(stCd)) {

                            if (isEquals(ctryCd, paramCtryCd) && isEquals(stCd, paramStCd)) {
                                isTaxExemFlg = false;
                                break;
                            }

                        } else if (hasValue(ctryCd) && !hasValue(stCd)) {

                            if (isEquals(ctryCd, paramCtryCd) && !hasValue(paramStCd)) {
                                isTaxExemFlg = false;
                                break;
                            }

                        } else if (!hasValue(ctryCd) && hasValue(stCd)) {

                            if (!hasValue(paramCtryCd) && isEquals(stCd, paramStCd)) {
                                isTaxExemFlg = false;
                                break;
                            }
                        }
                    }

                    hldCtrlTaxExemCache.put(key, taxExemList);
                    return isTaxExemFlg;
                }
            }

            // -------------
            // cache
            // -------------
            for (Map<String, Object> taxExemCache : hldCtrlTaxExemCacheList) {

                String isTaxExem = (String) taxExemCache.get("none");

                if (isTaxExem == null) {

                    String ctryCd = (String) taxExemCache.get("CTRY_CD");
                    String stCd = (String) taxExemCache.get("ST_CD");

                    if (hasValue(ctryCd) && hasValue(stCd)) {

                        if (isEquals(ctryCd, paramCtryCd) && isEquals(stCd, paramStCd)) {
                            return false;
                        }

                    } else if (hasValue(ctryCd) && !hasValue(stCd)) {

                        if (isEquals(ctryCd, paramCtryCd) && !hasValue(paramStCd)) {
                            return false;
                        }

                    } else if (!hasValue(ctryCd) && hasValue(stCd)) {

                        if (!hasValue(paramCtryCd) && isEquals(stCd, paramStCd)) {
                            return false;
                        }
                    }

                } else {
                    return true;
                }
            }

            return true;
        }

        @SuppressWarnings("unchecked")
        private void execCreditLimitValidation(String hldRsnCd, NWXC005001ValidationBean validBean) {

            final NWXC005001PMsg pMsg = validBean.getInputPMsg();
            final CPOTMsg cpoTMsg = validBean.getCTMsg();
            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            final DS_CPOTMsg dsCpoTMsg = validBean.getDscTMsg(); // 20150924 add
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
            // SHPG_PLNTMsg shpgPlnTMsg = validBean.getSpTMsg(); // S21_NA#14973 Del

            // S21_NA#2709
            // if (SET_ITEM_PARENT_NUM.equals(shpgPlnTMsg.trxLineSubNum.getValue())) {
            //    return;
            // }

            // S21_NA#14973 Del Start
//            // ********** [# 372937] - START
//            if (getShpgPlnHold(pMsg, shpgPlnTMsg, hldRsnCd)) {
//                return;
//            }
//            // ********** [# 372937] - END
            // S21_NA#14973 Del End

            // S21_NA#14973 Add Start
            if (existCreditLimitHold(pMsg, hldRsnCd)) {
                return;
            }
            // S21_NA#14973 Add End

            //Def# Prod (1339)
            NMZC600001PMsg crBalPMsg = null;

            final String threadKey = ThreadKey.DONE_CR_BALANCE_PMSG.toString();
            String crBalPMsgCacheKey = null;

            // S21_NA#14973 Del Start
//            final String shipCpltCd = shpgPlnTMsg.shipCpltCd.getValue();
//            final String setItemShipCpltFlg = shpgPlnTMsg.setItemShipCpltFlg.getValue();
//            //WDS#110 START
//            final String trxHdrNum = shpgPlnTMsg.trxHdrNum.getValue();
//            final String setShpgPlnNum = shpgPlnTMsg.setShpgPlnNum.getValue();
//            //WDS#110 E N D
//
//            BigDecimal sumInProcAmt = null;
            // S21_NA#14973 Del End

            // S21_NA#14973 Del Start
//            //WDS#110 START
//            /**************************************************
//             * SHIP_CPLT_CD
//             **************************************************/
//            if (hasValue(shipCpltCd)) {
//                writePerformanceProfilingLog(getClass(), "    #shipCpltCd=[" + shipCpltCd + "]");
//
//                S21LRUMap<String, NMZC600001PMsg> doneCrBalInfo = (S21LRUMap<String, NMZC600001PMsg>) getFromThread(threadKey);
//                if (doneCrBalInfo == null) {
//                    doneCrBalInfo = new S21LRUMap<String, NMZC600001PMsg>();
//                    putInThread(threadKey, doneCrBalInfo);
//                }
//
//                final Map<String, String> ssmParam = new HashMap<String, String>();
//                ssmParam.put("glblCmpyCd", glblCmpyCd);
//                ssmParam.put("trxHdrNum", trxHdrNum);
//                ssmParam.put("shipCpltCd", shipCpltCd);
//                ssmParam.put("trxLineNumSet", SET_ITEM_PARENT_NUM);
//                ssmParam.put("credit", CR_REBIL.CREDIT); //20150924 add
//                //START ADD M.FUJI [Defect#2745]
//                ssmParam.put("crChkQty_Zero", BigDecimal.ZERO.toString());
//                //END ADD M.FUJI [Defect#2745]
//
//                sumInProcAmt = (BigDecimal) ssmClient.queryObject("sumInProcAmtForSC", ssmParam);
//
//                // cache Key.
//                crBalPMsgCacheKey = new StringBuilder() //
//                        .append("shipCpltCd=").append(shipCpltCd).append(",") // SHIP_CPLT_CD
//                        .append(shpgPlnTMsg.trxHdrNum.getValue()).append(",") // TRX_HDR_NUM
//                        .append(shpgPlnTMsg.trxLineNum.getValue()).append(",") // TRX_LINE_NUM
//                        .append(shpgPlnTMsg.trxLineSubNum.getValue()).toString(); // TRX_LINE_SUB_NUM
//
//                final NMZC600001PMsg donePMsg = doneCrBalInfo.get(crBalPMsgCacheKey);
//                if (donePMsg != null) {
//                    crBalPMsg = donePMsg;
//                    writePerformanceProfilingLog(getClass(), "    #has cache. cacheKey=[" + crBalPMsgCacheKey + "]");
//                }
//
//                /**************************************************
//                 * SET_ITEM_SHIP_CPLT_FLG
//                 **************************************************/
//            } else if (Y.equals(setItemShipCpltFlg)) {
//                writePerformanceProfilingLog(getClass(), "    #setItemShipCpltFlg=[" + setItemShipCpltFlg + "]");
//
//                S21LRUMap<String, NMZC600001PMsg> doneCrBalInfo = (S21LRUMap<String, NMZC600001PMsg>) getFromThread(threadKey);
//                if (doneCrBalInfo == null) {
//                    doneCrBalInfo = new S21LRUMap<String, NMZC600001PMsg>();
//                    putInThread(threadKey, doneCrBalInfo);
//                }
//
//                final Map<String, String> ssmParam = new HashMap<String, String>();
//                ssmParam.put("glblCmpyCd", glblCmpyCd);
//                ssmParam.put("trxHdrNum", trxHdrNum);
//                ssmParam.put("setShpgPlnNum", setShpgPlnNum);
//                ssmParam.put("trxLineNumSet", SET_ITEM_PARENT_NUM);
//                ssmParam.put("credit", CR_REBIL.CREDIT); //20150924 add
//                //START ADD M.FUJI [Defect#2745]
//                ssmParam.put("crChkQty_Zero", BigDecimal.ZERO.toString());
//                //END ADD M.FUJI [Defect#2745]
//
//                sumInProcAmt = (BigDecimal) ssmClient.queryObject("sumInProcAmtForSet", ssmParam);
//
//                // cache Key.
//                crBalPMsgCacheKey = new StringBuilder() //
//                        .append("setItemShipCpltFlg=").append(setItemShipCpltFlg).append(",") // SET_ITEM_SHIP_CPLT_FLG
//                        .append(shpgPlnTMsg.trxHdrNum.getValue()).append(",") // TRX_HDR_NUM
//                        .append(shpgPlnTMsg.trxLineNum.getValue()).append(",") // TRX_LINE_NUM
//                        .append(shpgPlnTMsg.trxLineSubNum.getValue()).append(",") // TRX_LINE_SUB_NUM
//                        .append(shpgPlnTMsg.setShpgPlnNum.getValue()).toString(); // SET_SHPG_PLN_NUM
//
//                final NMZC600001PMsg donePMsg = doneCrBalInfo.get(crBalPMsgCacheKey);
//                if (donePMsg != null) {
//                    crBalPMsg = donePMsg;
//                    writePerformanceProfilingLog(getClass(), "    #has cache. cacheKey=[" + crBalPMsgCacheKey + "]");
//                }
//            }
//            //WDS#110 E N D
            // S21_NA#14973 Del End

//            if (crBalPMsg == null) {
                // S21_NA#14973 Del Start
//                BigDecimal inProcAmt = ZERO;
//
//                if (hasValue(shipCpltCd) || Y.equals(setItemShipCpltFlg)) {
//
//                    if (ZERO.compareTo(sumInProcAmt) < 0) {
//                        inProcAmt = sumInProcAmt;
//                    }
//
//                } else {
//
//                    if (ZERO.compareTo(shpgPlnTMsg.spFuncNetUnitPrcAmt.getValue()) < 0) {
//                        final BigDecimal crChkQty = validBean.getOrdQty();
//                        final BigDecimal unitPrice = shpgPlnTMsg.spFuncNetUnitPrcAmt.getValue();
//                        inProcAmt = inProcAmt.add(crChkQty.multiply(unitPrice));
//                    }
//                }
                // S21_NA#14973 Del End

                //                if (!SET_ITEM_PARENT_NUM.equals(shpgPlnTMsg.trxLineSubNum.getValue()) && (ZERO.compareTo(shpgPlnTMsg.spFuncNetUnitPrcAmt.getValue()) < 0)) {
                //                    final BigDecimal crChkQty  = validBean.getOrdQty();
                //                    final BigDecimal unitPrice = shpgPlnTMsg.spFuncNetUnitPrcAmt.getValue();
                //                    inProcAmt = inProcAmt.add(crChkQty.multiply(unitPrice));
                //                }

                // S21_NA#14973 Del Start
//                if (MAX_AMT.compareTo(inProcAmt) < 0) {
//                    addXxMsgId(NWZM0148E);
//                    return;
//                } else if (ZERO.compareTo(inProcAmt) >= 0) {
//                    return;
//                }
                // S21_NA#14973 Del End

                // -------------------------------------------------------
                // NMZC600001 : Credit Limit Validation API
                // -------------------------------------------------------
            // 2017/12/27 QC#22737 add start
            String acctLvlFlg =  ZYPCodeDataUtil.getVarCharConstValue(CR_CHK_LVL_ACCT, glblCmpyCd);
            // 2017/12/27 QC#22737 add end
            crBalPMsg = new NMZC600001PMsg();
            setValue(crBalPMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(crBalPMsg.billToCustCd, cpoTMsg.payerCustCd);
            // 2017/12/28 QC#22737 add start
            if (ZYPConstant.FLG_ON_Y.equals(acctLvlFlg)) {
                setValue(crBalPMsg.xxModeCd, NMZC600001.MODE_ACCT); 
            } else {
                setValue(crBalPMsg.xxModeCd, NMZC600001.MODE_BILL); 
            }
            // 2017/12/28 QC#22737 add end
//                setValue(crBalPMsg.xxModeCd, NMZC600001.MODE_ALL); // 20150924 add
            setValue(crBalPMsg.dsAcctNum, cpoTMsg.billToCustAcctCd); // 20150924 add
            //                setValue(crBalPMsg.inProcAmt, inProcAmt); //20150924 del
            setValue(crBalPMsg.updKeyNum, cpoTMsg.cpoOrdNum);
            setValue(crBalPMsg.slsDt, pMsg.slsDt);
            // Def#1481 Add
            setValue(crBalPMsg.xxReadOnlyFlg, Y);

            ((NMZC600001) getAPI(NMZC600001.class)).execute(crBalPMsg, onBatchType);

            //                writePerformanceProfilingLog(getClass(), "    #call NMZC600001 : Credit Limit Validation API. inProcAmt=[" + crBalPMsg.inProcAmt.getValue() + "], crBalAmt=[" + crBalPMsg.crBalAmt.getValue() + "]");

            // cache.
            if (crBalPMsgCacheKey != null) {
                ((S21LRUMap<String, NMZC600001PMsg>) getFromThread(threadKey)).put(crBalPMsgCacheKey, crBalPMsg);
            }
//            }

            // has API errors?
            final int apiErrMsgIdList = crBalPMsg.xxMsgIdList.getValidCount();
            if (apiErrMsgIdList > 0) {
                for (int i = 0; i < apiErrMsgIdList; i++) {
                    addXxMsgId(crBalPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                return;
            }

            //20150924 mod start
            //            final BigDecimal crBalAmt = crBalPMsg.crBalAmt.getValue();
            //            final String crChkReqTpCd = crBalPMsg.crChkReqTpCd.getValue();
            //
            //            if (crBalAmt.compareTo(ZERO) < 0 && NMZC600001.CR_CHK_REQ_TP_CREDIT_LIMIT.equals(crChkReqTpCd)) {
            //                shpgPlnTMsg = validBean.getSpTMsg();
            //                if (!SET_ITEM_PARENT_NUM.equals(shpgPlnTMsg.trxLineSubNum.getValue())) {
            //                    // HLD
            //                    final NWXC005001PMsg updpMsg = validBean.getInputPMsg();
            //                    setValue(updpMsg.cpoOrdNum_O, shpgPlnTMsg.trxHdrNum);
            //                    setValue(updpMsg.cpoDtlLineNum_O, shpgPlnTMsg.trxLineNum);
            //                    setValue(updpMsg.cpoDtlLineSubNum_O, shpgPlnTMsg.trxLineSubNum);
            //                    setValue(updpMsg.shpgPlnNum_O, shpgPlnTMsg.shpgPlnNum);
            //                    setValue(updpMsg.hldRsnCd, hldRsnCd);
            //                }
            //            }

            boolean isHold = false;
            for (int i = 0; i < crBalPMsg.xxOutPrmList.getValidCount(); i++) {
                NMZC600001_xxOutPrmListPMsg oPMsg = crBalPMsg.xxOutPrmList.no(i);

                final BigDecimal crBalAmt = oPMsg.crBalAmt.getValue();
                // S21_NA#5887 modify start
                // final String crChkReqTpCd =
                // oPMsg.crChkReqTpCd.getValue();

                // if (crBalAmt.compareTo(ZERO) < 0 &&
                // NMZC600001.CR_CHK_REQ_TP_CREDIT_LIMIT.equals(crChkReqTpCd))
                // {
                if (crBalAmt.compareTo(ZERO) < 0) {
                    // 2017/12/28 QC#22737 add start
                    if (ZYPConstant.FLG_OFF_N.equals(acctLvlFlg) && !ZYPCommonFunc.hasValue(oPMsg.billToCustCd)) {
                        if (existsCusrCrPrfl(pMsg.glblCmpyCd.getValue(), cpoTMsg.payerCustCd.getValue())) {
                            continue;
                        }
                    }
                    // 2017/12/28 QC#22737 add end

                    isHold = true;
                }
                // S21_NA#5887 modify end
            }

            if (isHold) {
                 // S21_NA#14973 Del Start
//                shpgPlnTMsg = validBean.getSpTMsg();
//                if (!SET_ITEM_PARENT_NUM.equals(shpgPlnTMsg.trxLineSubNum.getValue())) {
//                    // HLD
//                    final NWXC005001PMsg updpMsg = validBean.getInputPMsg();
//                    setValue(updpMsg.cpoOrdNum_O, shpgPlnTMsg.trxHdrNum);
//                    setValue(updpMsg.cpoDtlLineNum_O, shpgPlnTMsg.trxLineNum);
//                    setValue(updpMsg.cpoDtlLineSubNum_O, shpgPlnTMsg.trxLineSubNum);
//                    setValue(updpMsg.shpgPlnNum_O, shpgPlnTMsg.shpgPlnNum);
//                    setValue(updpMsg.hldRsnCd, hldRsnCd);
//                }
                // S21_NA#14973 Del End

                // S21_NA#14973 Add Start
                setOutParam(pMsg, hldRsnCd);
                // S21_NA#14973 Add End
            }
            //20150924 mod end
        }

        private String getFlPlnCmpyFlg(CPOTMsg cpoTMsg) {

            final String glblCmpyCd = cpoTMsg.glblCmpyCd.getValue();
            final String payerCustCd = cpoTMsg.payerCustCd.getValue();

            // cache
            final StringBuilder cacheKeySb = new StringBuilder();
            cacheKeySb.append(glblCmpyCd).append(",");
            cacheKeySb.append(payerCustCd);

            final String cacheKey = cacheKeySb.toString();

            String flPlnCmpyFlg = flPlnCmpyFlgCache.get(cacheKey);

            if (flPlnCmpyFlg == null) {

                // [BILL_TO_CUST]
                final BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
                billToCustTMsg.setSQLID("003");
                billToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                billToCustTMsg.setConditionValue("billToCustCd01", payerCustCd);
                billToCustTMsg.setMaxCount(1);

                final BILL_TO_CUSTTMsgArray billToCustTMsgArray = (BILL_TO_CUSTTMsgArray) findByCondition(billToCustTMsg);
                if (billToCustTMsgArray.length() <= 0) {
                    return null;
                }

                flPlnCmpyFlg = billToCustTMsgArray.no(0).flPlnCmpyFlg.getValue();
                flPlnCmpyFlgCache.put(cacheKey, flPlnCmpyFlg);
            }

            return flPlnCmpyFlg;
        }

        private void execFloorPlanValidation(String hldRsnCd, NWXC005001ValidationBean validBean) {

            final NWXC005001PMsg pMsg = validBean.getInputPMsg();
            final CPOTMsg cpoTMsg = validBean.getCTMsg();
            final SHPG_PLNTMsg shpgPlnTMsg = validBean.getSpTMsg();

            // exclude "000" LINE_SUB_NUM.
            if (SET_ITEM_PARENT_NUM.equals(shpgPlnTMsg.trxLineSubNum.getValue())) {
                return;
            }

            // FL_PLN_CMPY_FLG
            final String flPlnCmpyFlg = getFlPlnCmpyFlg(cpoTMsg);
            if (flPlnCmpyFlg == null) {
                addXxMsgId(NWZM0187E);
                return;
            }

            // Floor Plan Company
            if (Y.equals(flPlnCmpyFlg)) {

                if (getShpgPlnHold(pMsg, shpgPlnTMsg, hldRsnCd)) {
                    return;
                }

                // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                final HLD_RSNTMsg resHldRsnTMsg = NWXHldRsnTMsgThreadLocalCache.getInstance().get(cpoTMsg.glblCmpyCd.getValue(), hldRsnCd);
//                if (Y.equals(resHldRsnTMsg.exReqFlg.getValue())) {
//                    if (findCustExToOrdRstQty(pMsg, cpoTMsg, hldRsnCd)) {
//                        return;
//                    }
//                }
                // 2017/03/29 S21_NA#Review structure Lv.1 Del End

                // HLD
                setValue(pMsg.hldRsnCd, hldRsnCd);
                setValue(pMsg.cpoOrdNum_O, pMsg.cpoOrdNum_I);
                // 2017/05/16 S21_NA#Review structure Lv.2 RS#7319 Del Start
//                setValue(pMsg.cpoDtlLineNum_O, pMsg.cpoDtlLineNum_I);
//                setValue(pMsg.cpoDtlLineSubNum_O, pMsg.cpoDtlLineSubNum_I);
//                setValue(pMsg.shpgPlnNum_O, pMsg.shpgPlnNum_I);
                // 2017/05/16 S21_NA#Review structure Lv.2 RS#7319 Del End
            }
        }

        private void execHld(List<Map<String, Object>> hldOrdList) {

            if (isEmpty(hldOrdList)) {
                return;
            }

            for (Map<String, Object> hldOrd : hldOrdList) {
                this.execHld(hldOrd);
            }
        }

        private void execHld(Map<String, Object> hldOrd) {
            writePerformanceProfilingLog(getClass(), "  execHld = " + hldOrd);

            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            String hldRsnCd = (String) hldOrd.get("HLD_RSN_CD");
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
            String cpoOrdNum = (String) hldOrd.get("CPO_ORD_NUM");
            String cpoDtlLineNum = (String) hldOrd.get("CPO_DTL_LINE_NUM");
            String cpoDtlLineSubNum = (String) hldOrd.get("CPO_DTL_LINE_SUB_NUM");
            String shpgPlnNum = (String) hldOrd.get("SHPG_PLN_NUM");
            BigDecimal ordQty = (BigDecimal) hldOrd.get("ORD_QTY");

            // add HLD.
            this.addHld(hldOrd, ordQty);

            // update HLD_FLG
            this.updateHldFlg(hldOrd);

            // write Biz Process Log.
//            this.writeBizProcLog(cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum, hldRsnCd);

            if (param.A.getValidCount() >= param.A.length()) {
                return;
            }
            param.A.setValidCount(param.A.getValidCount() + 1);
            final NWZC004001_APMsg lastLine = (NWZC004001_APMsg) param.A.get(param.A.getValidCount() - 1);
            setValue(lastLine.cpoOrdNum_O, cpoOrdNum);
            setValue(lastLine.cpoDtlLineNum_O, cpoDtlLineNum);
            setValue(lastLine.cpoDtlLineSubNum_O, cpoDtlLineSubNum);
            setValue(lastLine.shpgPlnNum_O, shpgPlnNum);
        }

        @SuppressWarnings("unchecked")
        private List<Map<String, Object>> execShipCpltValidation(NWXC005001ValidationBean validBean) {

            final List<Map<String, Object>> hldOrdList = new ArrayList<Map<String, Object>>();

            NWXC005001PMsg pMsg = validBean.getInputPMsg();

            String glblCmpyCd = pMsg.glblCmpyCd.getValue();
            String cpoOrdNum = pMsg.cpoOrdNum_I.getValue();

            final Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("cpoOrdNum", cpoOrdNum);
            ssmParam.put("cpoDtlLineSubNum", SET_ITEM_PARENT_NUM);
            ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
            ssmParam.put("shpgStsCds", new String[] {SHPG_STS.VALIDATED, SHPG_STS.HARD_ALLOCATED });

            final List<Map<String, String>> shipCpltMapList = (List<Map<String, String>>) ssmClient.queryObjectList("getShipCpltListRs", ssmParam);

            if (shipCpltMapList.isEmpty()) {

                if (existsShipCpltHld(glblCmpyCd, cpoOrdNum)) {
                    deleteShipCpltHld(pMsg);
                    updateCpoDtlHldFlg(getHldOrderList(glblCmpyCd, cpoOrdNum), glblCmpyCd, cpoOrdNum);
                    // ********** add by K.Tajima [Def#1594(Prod)] - START
                    putInThread(ThreadKey.EXEC_RE_CALCULATION.toString(), Boolean.TRUE);
                    // ********** add by K.Tajima [Def#1594(Prod)] - E N D
                }

                return hldOrdList;
            }

            ssmParam.clear();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("cpoOrdNum", cpoOrdNum);
            ssmParam.put("hldRsnCd", HLD_RSN.SHIP_COMPLETE_HOLD);
            final List<HLDTMsg> shipCpltHldList = (List<HLDTMsg>) ssmClient.queryObjectList("getHold", ssmParam); //20150924 mod

            // [CPO_DTL]
            CPO_DTLTMsg cpoDtlMsg = new CPO_DTLTMsg();
            cpoDtlMsg.setSQLID("001");
            cpoDtlMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            cpoDtlMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
            CPO_DTLTMsgArray cpoDtlMsgArray = (CPO_DTLTMsgArray) findByCondition(cpoDtlMsg);

            for (Map<String, String> shipCpltMap : shipCpltMapList) {

                for (int i = 0; i < cpoDtlMsgArray.getValidCount(); i++) {

                    cpoDtlMsg = cpoDtlMsgArray.no(i);

                    cpoOrdNum = cpoDtlMsg.cpoOrdNum.getValue();
                    String cpoDtlLineNum = cpoDtlMsg.cpoDtlLineNum.getValue();
                    String cpoDtlLineSubNum = cpoDtlMsg.cpoDtlLineSubNum.getValue();

                    if (cpoOrdNum.equals(shipCpltMap.get("TRX_HDR_NUM"))) {
                        if (cpoDtlLineNum.equals(shipCpltMap.get("TRX_LINE_NUM"))) {
                            if (cpoDtlLineSubNum.equals(shipCpltMap.get("TRX_LINE_SUB_NUM"))) {
                                if (!isExistShipCpltHold(shipCpltHldList, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum)) {

                                    final Map<String, Object> holdApiMap = new HashMap<String, Object>();
                                    holdApiMap.put("CPO_ORD_NUM", cpoOrdNum);
                                    holdApiMap.put("CPO_DTL_LINE_NUM", cpoDtlLineNum);
                                    holdApiMap.put("CPO_DTL_LINE_SUB_NUM", cpoDtlLineSubNum);
                                    holdApiMap.put("SHPG_PLN_NUM", null);
                                    holdApiMap.put("HLD_RSN_CD", HLD_RSN.SHIP_COMPLETE_HOLD);
                                    holdApiMap.put("ORD_QTY", cpoDtlMsg.ordQty.getValue());
                                    hldOrdList.add(holdApiMap);
                                }
                            }
                        }
                    }
                }
            }

            // Non Ship Complete record logical remove for HLD
            for (int i = 0; i < cpoDtlMsgArray.getValidCount(); i++) {

                cpoDtlMsg = cpoDtlMsgArray.no(i);

                cpoOrdNum = cpoDtlMsg.cpoOrdNum.getValue();
                String cpoDtlLineNum = cpoDtlMsg.cpoDtlLineNum.getValue();
                String cpoDtlLineSubNum = cpoDtlMsg.cpoDtlLineSubNum.getValue();

                boolean isShipCplt = false;

                for (Map<String, String> shipCpltMap : shipCpltMapList) {
                    if (cpoOrdNum.equals(shipCpltMap.get("TRX_HDR_NUM"))) {
                        if (cpoDtlLineNum.equals(shipCpltMap.get("TRX_LINE_NUM"))) {
                            if (cpoDtlLineSubNum.equals(shipCpltMap.get("TRX_LINE_SUB_NUM"))) {
                                isShipCplt = true;
                                break;
                            }
                        }
                    }
                }

                if (!isShipCplt) {
                    for (HLDTMsg hldMsg : shipCpltHldList) {

                        final String hCpoOrdNum = hldMsg.cpoOrdNum.getValue();
                        final String hCpoDtlLineNum = hldMsg.cpoDtlLineNum.getValue();
                        final String hCpoDtlLineSubNum = hldMsg.cpoDtlLineSubNum.getValue();

                        if (cpoOrdNum.equals(hCpoOrdNum)) {
                            if (cpoDtlLineNum.equals(hCpoDtlLineNum)) {
                                if (cpoDtlLineSubNum.equals(hCpoDtlLineSubNum)) {
                                    // ***** logicalRemove
                                    logicalRemove(hldMsg);
                                    // ********** add by K.Tajima [Def#1594(Prod)] - START
                                    putInThread(ThreadKey.EXEC_RE_CALCULATION.toString(), Boolean.TRUE);
                                    // ********** add by K.Tajima [Def#1594(Prod)] - E N D
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            updateCpoDtlHldFlg(getHldOrderList(glblCmpyCd, cpoOrdNum), glblCmpyCd, cpoOrdNum);

            return hldOrdList;
        }

        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        private boolean findCustExToOrdRstQty(NWXC005001PMsg param, CPOTMsg cpoMsg, String hldRsnCd) {
//
//            return chkContractInfo(param.glblCmpyCd.getValue(), null, null, null, null, hldRsnCd, cpoMsg.payerCustCd.getValue(), param.slsDt.getValue());
//        }
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        @SuppressWarnings("unchecked")
        private List<String> getApplyOrdTpList() {

            // cache.
            // START MODIFY S.Yamamoto [OM004]
            //            final String cacheKey = new StringBuilder().append(glblCmpyCd).append(",").append(hldDfnChkPnt).toString();
            final String cacheKey = new StringBuilder().append(glblCmpyCd).append(",").append(hldDfnChkPnt).append(",").append(applyDsOrdTpCd).toString();
            // START MODIFY S.Yamamoto [OM004]

            List<String> applyOrdTpList = this.applyOrdTpListCache.get(cacheKey);

            // query & cache.
            if (applyOrdTpList == null) {
                final Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("hldDfnChkPnt", hldDfnChkPnt);
                // START ADD S.Yamamoto [OM004]
                ssmParam.put("applyDsOrdTpCd", applyDsOrdTpCd);
                // END   ADD S.Yamamoto [OM004]
                applyOrdTpList = ssmClient.queryObjectList("getApplyOrdTpList", ssmParam);
                this.applyOrdTpListCache.put(cacheKey, applyOrdTpList);
            }

            if (applyOrdTpList != null) {
                return applyOrdTpList;
            } else {
                return emptyList();
            }
        }

        private CPO_DTLTMsg getCpoDtlTMsg(CPO_DTLTMsgArray cpoDtlTMsgArray, String cpoDtlLineNum, String cpoDtlLineSubNum) {

            for (int i = 0; i < cpoDtlTMsgArray.getValidCount(); i++) {
                final CPO_DTLTMsg cpoDtlTMsg = cpoDtlTMsgArray.no(i);
                if (cpoDtlTMsg.cpoDtlLineNum.getValue().equals(cpoDtlLineNum)) {
                    if (cpoDtlTMsg.cpoDtlLineSubNum.getValue().equals(cpoDtlLineSubNum)) {
                        return cpoDtlTMsg;
                    }
                }
            }
            return null;
        }

        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        private DS_CPO_DTLTMsg getDcpoDtlTMsg(DS_CPO_DTLTMsgArray dcpoDtlTMsgArray, String cpoDtlLineNum, String cpoDtlLineSubNum) {
//
//            for (int i = 0; i < dcpoDtlTMsgArray.getValidCount(); i++) {
//                final DS_CPO_DTLTMsg dcpoDtlTMsg = dcpoDtlTMsgArray.no(i);
//                if (dcpoDtlTMsg.cpoDtlLineNum.getValue().equals(cpoDtlLineNum)) {
//                    if (dcpoDtlTMsg.cpoDtlLineSubNum.getValue().equals(cpoDtlLineSubNum)) {
//                        return dcpoDtlTMsg;
//                    }
//                }
//            }
//            return null;
//        }
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        @SuppressWarnings("unchecked")
        private List<Map<String, String>> getHldCtrlOrgList() {

            // cache.
            final String cacheKey = new StringBuilder().append(glblCmpyCd).toString();

            List<Map<String, String>> hldCtrlOrgList = this.hldCtrlOrgListCache.get(cacheKey);

            // query & cache.
            if (hldCtrlOrgList == null) {
                final Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                hldCtrlOrgList = ssmClient.queryObjectList("getHldCtrlOrgList", ssmParam);
                this.hldCtrlOrgListCache.put(cacheKey, hldCtrlOrgList);
            }

            if (hldCtrlOrgList != null) {
                return hldCtrlOrgList;
            } else {
                return emptyList();
            }
        }

        private boolean getShpgPlnHold(NWXC005001PMsg param, SHPG_PLNTMsg spMsg, String hldRsnCd) {

            final HLDTMsg inMsg = new HLDTMsg();
            inMsg.setSQLID("020");
            inMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            inMsg.setConditionValue("shpgPlnNum01", spMsg.shpgPlnNum.getValue());
            inMsg.setConditionValue("hldRsnCd01", hldRsnCd);

            return count(inMsg) > 0;
        }

        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        private SHPG_PLNTMsg getShpgPlnTMsg(SHPG_PLNTMsgArray shpgPlnTMsgArray, String shpgPlnNum) {
//            for (int i = 0; i < shpgPlnTMsgArray.getValidCount(); i++) {
//                final SHPG_PLNTMsg shpgPlnTMsg = shpgPlnTMsgArray.no(i);
//                if (shpgPlnTMsg.shpgPlnNum.getValue().equals(shpgPlnNum)) {
//                    return shpgPlnTMsg;
//                }
//            }
//            return null;
//        }
        // 2017/03/29 S21_NA#Review structure Lv.1 Del

        private boolean existsShipCpltHld(String glblCmpyCd, String cpoOrdNum) {

            final Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("cpoOrdNum", cpoOrdNum);
            ssmParam.put("hldRsnCd", HLD_RSN.SHIP_COMPLETE_HOLD);

            final String hldRsnCd = (String) ssmClient.queryObject("isShipCpltHold", ssmParam);

            if (hasValue(hldRsnCd)) {
                return true;
            }

            return false;
        };

        private void updateHldFlg(Map<String, Object> hldOrd) {

            String cpoOrdNum = (String) hldOrd.get("CPO_ORD_NUM");
            String cpoDtlLineNUm = (String) hldOrd.get("CPO_DTL_LINE_NUM");
            String cpoDtlLineSubNum = (String) hldOrd.get("CPO_DTL_LINE_SUB_NUM");
            String shpgPlnNum = (String) hldOrd.get("SHPG_PLN_NUM");

            // SHPG_PLN
            if (hasValue(shpgPlnNum)) {

                final SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
                setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
                setValue(shpgPlnTMsg.shipPlnHldFlg, Y);

                // ***** updateSelectionField
                updateSelectionField(shpgPlnTMsg, new String[] {"shipPlnHldFlg" });

                // CPO_DTL
            } else if (hasValue(cpoDtlLineNUm) && hasValue(cpoDtlLineSubNum)) {

                final CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                setValue(cpoDtlTMsg.glblCmpyCd, glblCmpyCd);
                setValue(cpoDtlTMsg.cpoOrdNum, cpoOrdNum);
                setValue(cpoDtlTMsg.cpoDtlLineNum, cpoDtlLineNUm);
                setValue(cpoDtlTMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
                setValue(cpoDtlTMsg.cpoDtlHldFlg, Y);

                // ***** updateSelectionField
                updateSelectionField(cpoDtlTMsg, new String[] {"cpoDtlHldFlg" });

                // CPO
            } else if (hasValue(cpoOrdNum)) {

                final CPOTMsg cpoTMsg = new CPOTMsg();
                setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
                setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);
                setValue(cpoTMsg.cpoHldFlg, Y);

                // ***** updateSelectionField
                updateSelectionField(cpoTMsg, new String[] {"cpoHldFlg" });
            }
        }

        private void writeBizProcLog(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum, String hldRsnCd) {

            final S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();
            bizLogMsg.setSubSysId("NWZ");
            bizLogMsg.setProcId("OM");
            bizLogMsg.setEventId("Hold");
            bizLogMsg.setDocTpCd("OM");
            bizLogMsg.setPrntDocId(cpoOrdNum);
            bizLogMsg.setBizProcCmntTxt_01(null);
            bizLogMsg.setBizProcCmntTxt_02(hldRsnCd);
            bizLogMsg.setBizProcCmntTxt_03(null);

            if (!hasValue(cpoDtlLineNum)) {
                bizLogMsg.setDocId("");
            } else {
                bizLogMsg.setDocId(cpoDtlLineNum + "." + cpoDtlLineSubNum);
            }

            S21BusinessProcessLog.print(bizLogMsg);
        }

        // S21_NA#14973 Add Start
        private void execHldForBulk(NWXC005001PMsg apiPMsg) {

            List<HLDTMsg> insHoldTMsgList = new ArrayList<HLDTMsg>();
            List<HLDTMsg> updHoldTMsgList = new ArrayList<HLDTMsg>();
            List<SHPG_PLNTMsg> updShpgPlnTMsgList = new ArrayList<SHPG_PLNTMsg>();

            for (int i = 0; i < apiPMsg.shpgPlnList.getValidCount(); i++) {
                NWXC005001_shpgPlnListPMsg shpgPlnPMsg = apiPMsg.shpgPlnList.no(i);
                addHldForBulk(shpgPlnPMsg, insHoldTMsgList, updHoldTMsgList);
                updateHldFlgForBulk(shpgPlnPMsg, updShpgPlnTMsgList);
            }

            // Bulk Insert
            if (!insHoldTMsgList.isEmpty()) {
                int cnt = S21FastTBLAccessor.insert(insHoldTMsgList.toArray(new HLDTMsg[0]));
                if (cnt != insHoldTMsgList.size()) {
                    addXxMsgId(NWZM0080E);
                }
            }

            // Bulk Update
            if (!updHoldTMsgList.isEmpty()) {
                int cnt = S21FastTBLAccessor.update(updHoldTMsgList.toArray(new HLDTMsg[0]));
                if (cnt != updHoldTMsgList.size()) {
                    addXxMsgId(NWZM0080E);
                }
            }

            // Bulk Update
            if (!updShpgPlnTMsgList.isEmpty()) {
                int cnt = S21FastTBLAccessor.update(updShpgPlnTMsgList.toArray(new SHPG_PLNTMsg[0]));
                if (cnt != updShpgPlnTMsgList.size()) {
                    addXxMsgId(NWZM0078E);
                }
            }

            if (param.A.getValidCount() >= param.A.length()) {
                return;
            }
            param.A.setValidCount(param.A.getValidCount() + 1);
            final NWZC004001_APMsg lastLine = (NWZC004001_APMsg) param.A.get(param.A.getValidCount() - 1);
            NWXC005001_shpgPlnListPMsg lastLinePMsg = apiPMsg.shpgPlnList.no(apiPMsg.shpgPlnList.getValidCount() - 1);
            setValue(lastLine.cpoOrdNum_O, lastLinePMsg.cpoOrdNum);
            setValue(lastLine.cpoDtlLineNum_O, lastLinePMsg.cpoDtlLineNum);
            setValue(lastLine.cpoDtlLineSubNum_O, lastLinePMsg.cpoDtlLineSubNum);
            setValue(lastLine.shpgPlnNum_O, lastLinePMsg.shpgPlnNum);
        }

        private void addHldForBulk(NWXC005001_shpgPlnListPMsg shpgPlnPMsg, List<HLDTMsg> insHoldTMsgList, List<HLDTMsg> updHoldTMsgList) {

            String cpoOrdNum = shpgPlnPMsg.cpoOrdNum.getValue();
            String cpoDtlLineNum = shpgPlnPMsg.cpoDtlLineNum.getValue();
            String cpoDtlLineSubNum = shpgPlnPMsg.cpoDtlLineSubNum.getValue();
            String shpgPlnNum = shpgPlnPMsg.shpgPlnNum.getValue();
            BigDecimal ordQty = shpgPlnPMsg.ordQty.getValue();
            String hldRsnCd = shpgPlnPMsg.hldRsnCd.getValue();

            HLDTMsg hldTMsg = new HLDTMsg();
            hldTMsg.setSQLID("010");
            hldTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            hldTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
            hldTMsg.setConditionValue("cpoDtlLineNum01", cpoDtlLineNum);
            hldTMsg.setConditionValue("cpoDtlLineSubNum01", cpoDtlLineSubNum);
            hldTMsg.setConditionValue("shpgPlnNum01", shpgPlnNum);
            hldTMsg.setConditionValue("hldRsnCd01", hldRsnCd);
            hldTMsg.setConditionValue("relFlg01", N);
            HLDTMsgArray hldTMsgArray = (HLDTMsgArray) findByConditionForUpdate(hldTMsg);

            if (hldTMsgArray.getValidCount() > 0) {
                hldTMsg = (HLDTMsg) hldTMsgArray.get(0);
                if (HLD_RSN.CREDIT_LIMIT.equals(hldRsnCd)) {
                    setValue(hldTMsg.hldQty, hldTMsg.hldQty.getValue().add(ordQty));
                } else {
                    setValue(hldTMsg.hldQty, ordQty);
                }

                // Update
                updHoldTMsgList.add(hldTMsg);

            } else {
                setValue(hldTMsg.glblCmpyCd, glblCmpyCd);
                setValue(hldTMsg.hldPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.HLD_SQ));
                setValue(hldTMsg.hldRsnCd, hldRsnCd);
                setValue(hldTMsg.hldDt, param.slsDt);
                setValue(hldTMsg.relFlg, N);
                setValue(hldTMsg.rvwFlg, N);
                setValue(hldTMsg.cpoOrdNum, cpoOrdNum);
                setValue(hldTMsg.cpoDtlLineNum, cpoDtlLineNum);
                setValue(hldTMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
                setValue(hldTMsg.shpgPlnNum, shpgPlnNum);
                setValue(hldTMsg.hldQty, ordQty);

                // Insert
                insHoldTMsgList.add(hldTMsg);
            }
        }

        private void updateHldFlgForBulk(NWXC005001_shpgPlnListPMsg shpgPlnPMsg, List<SHPG_PLNTMsg> updShpgPlnTMsgList) {

            SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
            setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
            setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnPMsg.shpgPlnNum.getValue());
            shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);

            if (shpgPlnTMsg != null) {
                setValue(shpgPlnTMsg.shipPlnHldFlg, Y);
                updShpgPlnTMsgList.add(shpgPlnTMsg);
            }
        }
        // S21_NA#14973 Add End
    }

    //    private static final String getUomCd(String glblCmpyCd, String varCharConstNm) {
    //        
    //        VAR_CHAR_CONSTTMsg varCharConstTMsg = new VAR_CHAR_CONSTTMsg();
    //        setValue(varCharConstTMsg.glblCmpyCd,     glblCmpyCd);
    //        setValue(varCharConstTMsg.varCharConstNm, varCharConstNm);
    //        
    //        varCharConstTMsg = (VAR_CHAR_CONSTTMsg)findByKeyWithCache(varCharConstTMsg);
    //        
    //        if (varCharConstTMsg == null) {
    //            return null;
    //        } else {
    //            return varCharConstTMsg.varCharConstVal.getValue();
    //        }
    //    }

    private static void setHldFlg(NWZC004001PMsg pMsg) {

        if (pMsg.A.getValidCount() > 0) {
            setValue(pMsg.xxHldFlg, Y);
        } else {
            setValue(pMsg.xxHldFlg, N);
        }
    }

    /**
     * Process Requester.
     * @author K.Tajima
     */
    public static enum Requester {

        /**
         * [NWAL0010] : Order Entry Screen (New Entry)
         */
        NWAL0010_ORDER_ENTRY_SCREEN_NEW_ENTRY

        /**
         * [NWAL0050] : Hold Release Screen
         */
        , NWAL0050_HOLD_RELEASE_SCREEN;
    }

    /**
     * Thread Data Key.
     * @author K.Tajima
     */
    private static enum ThreadKey {

        /**
         * Done "Ship Complete Validation" CPO_ORD_NUM.
         */
        DONE_SHIP_CPLT_VALIDATION_CPO_ORD_NUM

        /**
         * Done "Credit Limit Validation" PMsg. (NMZC600001PMsg)
         */
        , DONE_CR_BALANCE_PMSG

        /**
         * execute "Recalculation"
         */
        , EXEC_RE_CALCULATION;

    }

    private static boolean isRequestByOrderEntryScrnNewEntry() {

        return Boolean.TRUE.equals(getFromThread(Requester.NWAL0010_ORDER_ENTRY_SCREEN_NEW_ENTRY.toString()));
    }

    private static boolean isRequestByHoldReleaseScrn() {

        return Boolean.TRUE.equals(getFromThread(Requester.NWAL0050_HOLD_RELEASE_SCREEN.toString()));
    }

    private static void putInThread(String key, Object obj) {
        S21ApplicationCacheHolder.put(key, obj);
    }

    private static Object getFromThread(String key) {
        return S21ApplicationCacheHolder.get(key);
    }

    private boolean hasXxMsgId() {
        return xxMsgIdSet != null && !xxMsgIdSet.isEmpty();
    }

    private void addXxMsgId(String xxMsgId) {
        if (xxMsgIdSet != null) {
            xxMsgIdSet.add(xxMsgId);
        }
    }

    private static List<Map<String, Object>> toMapList(ResultSet rs) throws SQLException {

        final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        if (rs.next()) {

            final Set<String> columnNameSet = new HashSet<String>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                columnNameSet.add(rs.getMetaData().getColumnName(i));
            }

            do {
                final Map<String, Object> data = new HashMap<String, Object>();
                list.add(data);
                for (String columnName : columnNameSet) {
                    data.put(columnName, rs.getObject(columnName));
                }
            } while (rs.next());
        }

        return list;
    }

    // Def#1481 Add
    private S21ApiCommonBase getAPI(Class<? extends S21ApiCommonBase> clazz) {

        if (!apiCache.containsKey(clazz)) {
            try {
                apiCache.put(clazz, (S21ApiCommonBase) Class.forName(clazz.getName()).newInstance());
            } catch (Exception e) {
                throw new S21AbendException(e);
            }
        }

        return apiCache.get(clazz);
    }

    // Def#2025 Add
    private void executeForUpdate(List<Map<String, Object>> orderList) {

        Set<String> ordNumSet = new HashSet<String>();
        Set<String> lineNumSet = new HashSet<String>();

        for (Map<String, Object> order : orderList) {

            String glblCmpyCd = (String) order.get("GLBL_CMPY_CD");
            String cpoOrdNum = (String) order.get("CPO_ORD_NUM");
            String cpoDtlLineNum = (String) order.get("CPO_DTL_LINE_NUM");
            String cpoDtlLineSubNum = (String) order.get("CPO_DTL_LINE_SUB_NUM");
            String shpgPlnNum = (String) order.get("SHPG_PLN_NUM");

            if (!ordNumSet.contains(cpoOrdNum)) {
                CPOTMsg cpoTMsg = new CPOTMsg();
                setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
                setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);
                cpoTMsg = (CPOTMsg) findByKeyForUpdateAPI(cpoTMsg);

                ordNumSet.add(cpoOrdNum);
            }

            StringBuilder lineNum = new StringBuilder();
            lineNum.append(cpoDtlLineNum).append(",").append(cpoDtlLineSubNum);

            if (!lineNumSet.contains(lineNum.toString())) {
                CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                setValue(cpoDtlTMsg.glblCmpyCd, glblCmpyCd);
                setValue(cpoDtlTMsg.cpoOrdNum, cpoOrdNum);
                setValue(cpoDtlTMsg.cpoDtlLineNum, cpoDtlLineNum);
                setValue(cpoDtlTMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
                cpoDtlTMsg = (CPO_DTLTMsg) findByKeyForUpdateAPI(cpoDtlTMsg);

                lineNumSet.add(lineNum.toString());
            }

            SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
            setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
            setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
            shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);
        }
    }

    //WDS#110 START
    //     // Def#1481 Add
    //     /** Get Credit Check QTY (Ship Complete) */
    //     @SuppressWarnings("unchecked")
    //     private BigDecimal getCrChkQty(SHPG_PLNTMsg shpgPlnMsg) {
    //
    //         final String glblCmpyCd = shpgPlnMsg.glblCmpyCd.getValue();
    //         final String trxHdrNum  = shpgPlnMsg.trxHdrNum.getValue();
    //         final String shipCpltCd = shpgPlnMsg.shipCpltCd.getValue();
    //
    //         Map<String, String> ssmParam = new HashMap<String, String>();
    //         ssmParam.put("glblCmpyCd", glblCmpyCd);
    //         ssmParam.put("trxHdrNum",  trxHdrNum);
    //         ssmParam.put("shipCpltCd", shipCpltCd);
    //         ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
    //         ssmParam.put("RelPnt",     REL_PNT_CREDIT_CHECK);
    //         ssmParam.put("RelPntFlg",  Y);
    //         ssmParam.put("hldLvl01",   HLD_LVL.CPO_LEVEL);
    //         ssmParam.put("hldLvl02",   HLD_LVL.CPO_DETAIL_LEVEL);
    //         ssmParam.put("hldLvl03",   HLD_LVL.SHIPPING_PLAN_LEVEL);
    //         ssmParam.put("relFlg",     N);
    //
    //         final BigDecimal hldCntByShipCplt = (BigDecimal) ssmClient.queryObject("getHldCntByShipCplt", ssmParam);
    //         if (ZERO.compareTo(hldCntByShipCplt) < 0) {
    //             return ZERO;
    //         }
    //
    //         BigDecimal crChkQty = shpgPlnMsg.ordQty.getValue();
    //
    //         ssmParam = new HashMap<String, String>();
    //         ssmParam.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
    //         ssmParam.put("trxHdrNum",  shpgPlnMsg.trxHdrNum.getValue());
    //         ssmParam.put("shipCpltCd", shpgPlnMsg.shipCpltCd.getValue());
    //         ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
    //
    //         final List<Map<String, Object>> scList = ssmClient.queryObjectList("getCrChkOrderList", ssmParam, new S21SsmListResultSetHandlerSupport() {
    //             @Override
    //             protected List doProcessQueryResult(ResultSet rs) throws SQLException {
    //                 return toMapList(rs);
    //             }
    //         });
    //
    //         executeForUpdate(scList);
    //
    //         for (Map<String, Object> scMap : scList) {
    //
    //             final String cpoDtlLineSubNum  = (String) scMap.get("CPO_DTL_LINE_SUB_NUM");
    //             final String shpgStsCd         = (String) scMap.get("SHPG_STS_CD");
    //
    //             if (!SET_ITEM_PARENT_NUM.equals(cpoDtlLineSubNum)) {
    //                 if (!SHPG_STS.CANCELLED.equals(shpgStsCd) && !SHPG_STS.SAVED.equals(shpgStsCd)) {
    //
    //                     final String mdseCd       = (String) scMap.get("S_MDSE_CD");
    //                     final String poReqFlg     = (String) scMap.get("PO_REQ_FLG");
    //                     final String whTyp        = getWhType(glblCmpyCd, mdseCd, poReqFlg);
    //
    //                     if (WH_TP.COMMON.equals(whTyp) && SHPG_STS.VALIDATED.equals(shpgStsCd)) {
    //                         crChkQty = ZERO;
    //                         break;
    //                     }
    //                 }
    //             }
    //         }
    //
    //         return crChkQty;
    //     }
    //WDS#110 E N D

    private boolean isHldCtrlOrg(CPO_DTLTMsg cpoDtlTMsg, List<Map<String, String>> hldCtrlOrgList, String hldRsnCd) {

        // 05/25/2010 Defect 6538 --- add ---
        // ---------------------------------------------------------------------
        // If data doesn't exist in the HLD_CTRL_ORG table, Hold is not created.
        // ---------------------------------------------------------------------
        if (isEmpty(hldCtrlOrgList)) {
            return false;
        }

        // HLD_CTRL_ORG
        for (Map<String, String> hldCtrlOrg : hldCtrlOrgList) {
            if (isEquals(hldRsnCd, hldCtrlOrg.get("HLD_RSN_CD"))) {
                if (isEquals("*", hldCtrlOrg.get("FIRST_ORG_CD"))) {
                    return true;
                }
                if (!hasValue(hldCtrlOrg.get("FIRST_ORG_CD"))) {
                    return false;
                }
            }
        }

        // TOC_CD
        final S21_ORGTMsg s21Org = getS21Org(cpoDtlTMsg.glblCmpyCd.getValue(), cpoDtlTMsg.slsRepOrSlsTeamTocCd.getValue());
        if (s21Org == null) {
            return false;
        }

        for (Map<String, String> hldCtrlOrg : hldCtrlOrgList) {

            final String defHldRsnCd = hldCtrlOrg.get("HLD_RSN_CD");
            final String tocCd = hldCtrlOrg.get("TOC_CD");
            final String eighthOrgCd = hldCtrlOrg.get("EIGHTH_ORG_CD");
            final String svnthOrgCd = hldCtrlOrg.get("SVNTH_ORG_CD");
            final String sixthOrgCd = hldCtrlOrg.get("SIXTH_ORG_CD");
            final String fifthOrgCd = hldCtrlOrg.get("FIFTH_ORG_CD");
            final String frthOrgCd = hldCtrlOrg.get("FRTH_ORG_CD");
            final String thirdOrgCd = hldCtrlOrg.get("THIRD_ORG_CD");
            final String scdOrgCd = hldCtrlOrg.get("SCD_ORG_CD");
            final String firstOrgCd = hldCtrlOrg.get("FIRST_ORG_CD");

            // HldRsnCd
            if (hasValue(defHldRsnCd)) {
                if (!isEquals(hldRsnCd, defHldRsnCd)) {
                    continue;
                }
            } else {
                continue;
            }

            // Toc
            if (hasValue(tocCd)) {
                if (isEquals(tocCd, s21Org.tocCd.getValue())) {
                    return true;
                }

                // 8th
            } else if (hasValue(eighthOrgCd)) {
                if (isEquals(eighthOrgCd, s21Org.eighthOrgCd.getValue()) && isEquals(svnthOrgCd, s21Org.svnthOrgCd.getValue()) && isEquals(sixthOrgCd, s21Org.sixthOrgCd.getValue()) && isEquals(fifthOrgCd, s21Org.fifthOrgCd.getValue())
                        && isEquals(frthOrgCd, s21Org.frthOrgCd.getValue()) && isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue()) && isEquals(scdOrgCd, s21Org.scdOrgCd.getValue()) && isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 7th
            } else if (hasValue(svnthOrgCd)) {
                if (isEquals(svnthOrgCd, s21Org.svnthOrgCd.getValue()) && isEquals(sixthOrgCd, s21Org.sixthOrgCd.getValue()) && isEquals(fifthOrgCd, s21Org.fifthOrgCd.getValue()) && isEquals(frthOrgCd, s21Org.frthOrgCd.getValue())
                        && isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue()) && isEquals(scdOrgCd, s21Org.scdOrgCd.getValue()) && isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 6th
            } else if (hasValue(sixthOrgCd)) {
                if (isEquals(sixthOrgCd, s21Org.sixthOrgCd.getValue()) && isEquals(fifthOrgCd, s21Org.fifthOrgCd.getValue()) && isEquals(frthOrgCd, s21Org.frthOrgCd.getValue()) && isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue())
                        && isEquals(scdOrgCd, s21Org.scdOrgCd.getValue()) && isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 5th
            } else if (hasValue(fifthOrgCd)) {
                if (isEquals(fifthOrgCd, s21Org.fifthOrgCd.getValue()) && isEquals(frthOrgCd, s21Org.frthOrgCd.getValue()) && isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue()) && isEquals(scdOrgCd, s21Org.scdOrgCd.getValue())
                        && isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 4th
            } else if (hasValue(frthOrgCd)) {
                if (isEquals(frthOrgCd, s21Org.frthOrgCd.getValue()) && isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue()) && isEquals(scdOrgCd, s21Org.scdOrgCd.getValue()) && isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 3rd
            } else if (hasValue(thirdOrgCd)) {
                if (isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue()) && isEquals(scdOrgCd, s21Org.scdOrgCd.getValue()) && isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 2nd
            } else if (hasValue(scdOrgCd)) {
                if (isEquals(scdOrgCd, s21Org.scdOrgCd.getValue()) && isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 1st
            } else if (hasValue(firstOrgCd)) {
                if (isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }
            }
        }

        return false;
    }

    private S21_ORGTMsg getS21Org(final String glblCmpyCd, final String tocCd) {

        final S21_ORGTMsg s21OrgTMsg = new S21_ORGTMsg();
        setValue(s21OrgTMsg.glblCmpyCd, glblCmpyCd);
        setValue(s21OrgTMsg.tocCd, tocCd);

        return (S21_ORGTMsg) findByKeyWithCache(s21OrgTMsg);
    }

    private boolean isValidOrdTp(List<String> applyOrdTpList, String hldRsnCd, String cpoSrcTpCd, String cpoOrdTpCd, String whTpCd) {

        // HLD_RSN_CD + "." + CPO_SRC_TP_CD + "." + CPO_ORD_TP_CD + "." + WH_TP_CD
        final StringBuilder sb = new StringBuilder();
        sb.append(hldRsnCd).append(".").append(cpoSrcTpCd).append(".").append(cpoOrdTpCd).append(".").append(whTpCd);

        boolean isValidOrdTp = applyOrdTpList.contains(sb.toString());

        if (!isValidOrdTp) {
            if (!WH_TP.COMMON.equals(whTpCd)) {
                isValidOrdTp = isValidOrdTp(applyOrdTpList, hldRsnCd, cpoSrcTpCd, cpoOrdTpCd, WH_TP.COMMON);
            }
        }

        return isValidOrdTp;
    }

    /**
     * <pre>
     * Credit Validation. (for [NWAB012001] : Automatic Hold Release Batch)
     * 
     * @param param
     * @return NWZC004001PMsg
     * 
     * @throws SQLException
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private NWZC004001PMsg execCreditValidationForAutoHoldRls(NWZC004001PMsg param) throws SQLException {
        final String methodNm = "execCreditValidationForHoldReleaseScrn";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();

            final Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            ssmParam.put("shpgPlnNum", param.shpgPlnNum_I.getValue());
            ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);

            final List<Map<String, Object>> crChkOrdList = ssmClient.queryObjectList("getCrChkOrderList", ssmParam, new S21SsmListResultSetHandlerSupport() {
                @Override
                protected List doProcessQueryResult(ResultSet rs) throws SQLException {
                    return toMapList(rs);
                }
            });

            if (crChkOrdList.isEmpty()) {
                return null;
            }

            final Map<String, Object> crChkOrd = crChkOrdList.get(0);
            crChkOrd.put("crChkQty", (BigDecimal) crChkOrd.get("S_ORD_QTY"));
            crChkOrd.put("whType", getWhType(param.glblCmpyCd.getValue(), (String) crChkOrd.get("S_MDSE_CD"), (String) crChkOrd.get("PO_REQ_FLG")));
            orderList.add(crChkOrd);

            // --------------------------------------------------
            // execute Validation.
            // --------------------------------------------------
            getOrderValidator().exec(param, orderList, CHK_PNT_CV);

            return param;

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    //WDS#104 105 START
    // START DELETE S.Yamamoto [OM004]
    //    private boolean checkDefferd(SHPG_PLNTMsg shpgPlnTMsg) {
    //
    //        TRX_RSNTMsg trxRsnTMsg = new TRX_RSNTMsg();
    //        ZYPEZDItemValueSetter.setValue(trxRsnTMsg.glblCmpyCd, shpgPlnTMsg.glblCmpyCd);
    //        ZYPEZDItemValueSetter.setValue(trxRsnTMsg.trxCd, shpgPlnTMsg.trxCd);
    //        ZYPEZDItemValueSetter.setValue(trxRsnTMsg.trxRsnCd, shpgPlnTMsg.trxRsnCd);
    //        trxRsnTMsg = (TRX_RSNTMsg) findByKeyWithCache(trxRsnTMsg);
    //
    //        boolean isDefferd = false;
    //        if (trxRsnTMsg != null && ZYPConstant.FLG_ON_Y.equals(trxRsnTMsg.dfrdTrxFlg.getValue())) {
    //            isDefferd = true;
    //        }
    //        return isDefferd;
    //    }
    // END   DELETE S.Yamamoto [OM004]
    //WDS#104 105 END
    // START 2013/02/18 S.Iidaka [WDS#Def 704 ADD]

    private boolean isPriceZero(SHPG_PLNTMsg shpgPlnTMsg) {
        if (ZERO.compareTo(shpgPlnTMsg.spTotDealSlsAmt.getValue()) == 0) {
            return true;
        }
        return false;
    }

    // E N D 2013/02/18 S.Iidaka [WDS#Def 704 ADD]

    // START 2013/02/18 S.Iidaka [WDS#Def 704 MOD]
    private boolean isCreditCardPayment(SHPG_PLNTMsg shpgPlnTMsg) {

        //START 2013/07/01 S.Yoshida [MOD for R-OM021]
        //        DS_CPOTMsg dsCpoTMsg = new DS_CPOTMsg();
        //        setValue(dsCpoTMsg.glblCmpyCd, shpgPlnTMsg.glblCmpyCd.getValue());
        //        setValue(dsCpoTMsg.cpoOrdNum,  shpgPlnTMsg.trxHdrNum.getValue());
        //        dsCpoTMsg = (DS_CPOTMsg) findByKey(dsCpoTMsg);
        //
        //        if (DS_PMT_METH.CREDIT_CARD.equals(dsCpoTMsg.dsPmtMethCd.getValue())) {
        //            return true;
        //        }

        for (String pmtTermCrCard : pmtTermCrCardAry) {
            if (pmtTermCrCard.equals(shpgPlnTMsg.pmtTermCashDiscCd.getValue())) {
                return true;
            }
        }
        //END 2013/07/01 S.Yoshida [MOD for R-OM021]

        return false;
    }

    // E N D 2013/02/18 S.Iidaka [WDS#Def 704 MOD]

    // START 09/05/2013 A.Suda [WDS Defect#1242#1654 ADD]
    private boolean updAvalQty(NWZC004001PMsg param, String cpoOrdNum) {

        final NWZC218001PMsg avalUpdatePMsg = new NWZC218001PMsg();
        setValue(avalUpdatePMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        setValue(avalUpdatePMsg.slsDt, param.slsDt.getValue());
        setValue(avalUpdatePMsg.cpoOrdNum, cpoOrdNum);

        NWZC218001 avalUpdateAPI = new NWZC218001();
        avalUpdateAPI.execute(avalUpdatePMsg, onBatchType);
        final int apiErrMsgIdList = avalUpdatePMsg.xxMsgIdList.getValidCount();
        if (apiErrMsgIdList > 0) {
            for (int i = 0; i < apiErrMsgIdList; i++) {
                addXxMsgId(avalUpdatePMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return false;
        }

        return true;
    }
    // END 09/05/2013 A.Suda [WDS Defect#1242#1654 ADD]

    // S21_NA#13323 Add Start
    private boolean isAllLineCredit(NWZC004001PMsg param) {

        if (!hasValue(param.cpoOrdNum_I.getValue())) {
            return false;
        }

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", param.cpoOrdNum_I.getValue());
        ssmParam.put("ordLineStsCancel", ORD_LINE_STS.CANCELLED);
        ssmParam.put("crRebilCd", CR_REBIL.CREDIT);

        // return (Integer) ssmClient.queryObject("cntNotAllLineCredit", ssmParam) == 0; // S21_NA#14512 Del
        return DataCacheForValidation.getInstance().isAllLineCredit(ssmParam, ssmClient); // S21_NA#14512 Add
    }
    // S21_NA#13323 Add End

    // S21_NA#14973 Add Start
    private boolean hasExemption(NWZC004001PMsg param) {

        if (!hasValue(param.cpoOrdNum_I.getValue())) {
            return false;
        }

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", param.cpoOrdNum_I.getValue());
        ssmParam.put("slsDt", param.slsDt.getValue());

        // return (Integer) ssmClient.queryObject("hasExemption", ssmParam) > 0; // S21_NA#14512 Del
        return DataCacheForValidation.getInstance().hasExemption(ssmParam, ssmClient); // S21_NA#14512 Add
    }

    private boolean existCreditLimitHold(NWXC005001PMsg param, String hldRsnCd) {

        final HLDTMsg condition = new HLDTMsg();
        condition.setSQLID("019");
        condition.setMaxCount(1);
        condition.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        condition.setConditionValue("cpoOrdNum01", param.cpoOrdNum_I.getValue());
        condition.setConditionValue("hldRsnCd01", hldRsnCd);
        return count(condition) > 0;
    }

    private void setOutParam(NWXC005001PMsg param, String hldRsnCd) {

        // 2017/05/16 S21_NA#Review structure Lv.2 RS#7319 Mod Start
//        List<Map<String, Object>> shpgPlnList = getShpgPlnList(param);
//        if (shpgPlnList == null || shpgPlnList.size() == 0) {
//            return;
//        }
//
//        int index = 0;
//        for (; index < shpgPlnList.size(); index++) {
//            NWXC005001_shpgPlnListPMsg shpgPlnListPMsg = param.shpgPlnList.no(index);
//            Map<String, Object> shpgPlnInfo = shpgPlnList.get(index);
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.cpoOrdNum, (String) shpgPlnInfo.get("TRX_HDR_NUM"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.cpoDtlLineNum, (String) shpgPlnInfo.get("TRX_LINE_NUM"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.cpoDtlLineSubNum, (String) shpgPlnInfo.get("TRX_LINE_SUB_NUM"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.shpgPlnNum, (String) shpgPlnInfo.get("SHPG_PLN_NUM"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.ordQty, (BigDecimal) shpgPlnInfo.get("ORD_QTY"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.hldRsnCd, hldRsnCd);
//        }
//        param.shpgPlnList.setValidCount(index);

        param.cpoOrdNum_O.setValue(param.cpoOrdNum_I.getValue());
        param.hldRsnCd.setValue(hldRsnCd);
        // 2017/05/16 S21_NA#Review structure Lv.2 RS#7319 Mod Start
    }

    // 2017/05/16 S21_NA#Review structure Lv.2 RS#7319 Del Start
//    @SuppressWarnings("unchecked")
//    private List<Map<String, Object>> getShpgPlnList(NWXC005001PMsg param) {
//
//        Map<String, String> ssmParam = new HashMap<String, String>();
//        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
//        ssmParam.put("cpoOrdNum", param.cpoOrdNum_I.getValue());
//        ssmParam.put("setParentLineSubNum", SET_ITEM_PARENT_NUM);
//        ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
//
//        return (List<Map<String, Object>>) ssmClient.queryObjectList("getShpgPlnList", ssmParam);
//    }
    // 2017/05/16 S21_NA#Review structure Lv.2 RS#7319 Del End
    // S21_NA#14973 Add End

    // 2017/05/08 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add Start
    private void setOrdHdrDplySts(NWZC004001PMsg param) {

        CPOTMsg condCpoTMsg = new CPOTMsg();
        condCpoTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        condCpoTMsg.cpoOrdNum.setValue(param.cpoOrdNum_I.getValue());
        CPOTMsg dbCpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(condCpoTMsg);
        if (dbCpoTMsg == null) {
            return;
        }
        boolean isOpenOrd = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, dbCpoTMsg.openFlg.getValue());
        boolean isSubmit = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, dbCpoTMsg.submtFlg.getValue());
        boolean isDiChkHld = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, dbCpoTMsg.diChkHldFlg.getValue());
        boolean isCpoHld = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, dbCpoTMsg.cpoHldFlg.getValue());
        boolean isWfRej = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, dbCpoTMsg.wfRejFlg.getValue());
        boolean isOrdBooked = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, dbCpoTMsg.ordBookFlg.getValue());

        if (!isOpenOrd) {
            return;
        }
        if (!isSubmit) {
            return;
        }
        if (isDiChkHld) {
            dbCpoTMsg.ordHdrDplyStsCd.setValue(ORD_HDR_DPLY_STS.DI_CHECK_HOLD);
            S21ApiTBLAccessor.updateSelectionField(dbCpoTMsg, new String[] {"ordHdrDplyStsCd"});
            return;
        }
        if (isOrdBooked) {
            dbCpoTMsg.ordHdrDplyStsCd.setValue(ORD_HDR_DPLY_STS.BOOKED);
            S21ApiTBLAccessor.updateSelectionField(dbCpoTMsg, new String[] {"ordHdrDplyStsCd"});
            return;
        }
        if (isCpoHld && isWfRej) {
            dbCpoTMsg.ordHdrDplyStsCd.setValue(ORD_HDR_DPLY_STS.PENDING_RE_SUBMISSION);
            S21ApiTBLAccessor.updateSelectionField(dbCpoTMsg, new String[] {"ordHdrDplyStsCd"});
            return;
        }
        List<Map<String, String>> hdrHldList = getHdrHldList(param);

        String ordHdrDplyStsCd = "";

        boolean isValidHld = false;
        boolean isSupAbuseHld = false;
        boolean isProfitHld = false;
        boolean isCrHld = false;
        for (Map<String, String> hdrHldMap : hdrHldList) {
            String hldRsnCd = hdrHldMap.get("HLD_RSN_CD");
            String hldTpCd = hdrHldMap.get("HLD_TP_CD");

            if (isCpoHld && !isWfRej) {
                if(S21StringUtil.isEquals(HLD_RSN.VALIDATION_HOLD, hldRsnCd)) {
                    isValidHld = true;
                } else if (S21StringUtil.isEquals(HLD_RSN.SUPPLY_YIELD_VALIDATION_HOLD, hldRsnCd) //
                        || S21StringUtil.isEquals(HLD_RSN.PENDING_ORDER_HOLD, hldRsnCd) //
                        || S21StringUtil.isEquals(HLD_RSN.CONTRACT_STATUS_HOLD, hldRsnCd) //
                        || S21StringUtil.isEquals(HLD_RSN.SUPPLY_ENFORCEMENT_HOLD, hldRsnCd)) {
                    isSupAbuseHld = true;
                } else if (S21StringUtil.isEquals(HLD_RSN.PROFITABILITY_HOLD, hldRsnCd)) {
                    isProfitHld = true;
                }
            }
            if (!isWfRej) {
                if (S21StringUtil.isEquals(HLD_TP.CREDIT_HOLD, hldTpCd)) {
                    isCrHld = true;
                }
            }
        }
        if (isCrHld) {
            BigDecimal crHldCnt = isHdrCrHld(param);
            if (crHldCnt == null || BigDecimal.ZERO.compareTo(crHldCnt) == 0) {
                isCrHld = false;
            }
        }

        if (isValidHld) {
            ordHdrDplyStsCd = ORD_HDR_DPLY_STS.VALIDATION_HOLD;
        } else if (isSupAbuseHld) {
            ordHdrDplyStsCd = ORD_HDR_DPLY_STS.SUPPLY_ABUSE_HOLD;
        } else if (isProfitHld) {
            ordHdrDplyStsCd = ORD_HDR_DPLY_STS.PROFITABILITY_HOLD;
        } else if (isCrHld) {
            ordHdrDplyStsCd = ORD_HDR_DPLY_STS.CREDIT_HOLD;
        } else {
            ordHdrDplyStsCd = ORD_HDR_DPLY_STS.BOOKED;
        }
        dbCpoTMsg.ordHdrDplyStsCd.setValue(ordHdrDplyStsCd);
        S21ApiTBLAccessor.updateSelectionField(dbCpoTMsg, new String[] {"ordHdrDplyStsCd"});

        return;
    }

    private List<Map<String, String>> getHdrHldList(NWZC004001PMsg param) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", param.cpoOrdNum_I.getValue());

        return (List<Map<String, String>>) ssmClient.queryObjectList("getHdrHldList", ssmParam);
    }

    private BigDecimal isHdrCrHld(NWZC004001PMsg param) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("trxHdrNum", param.cpoOrdNum_I.getValue());

        return (BigDecimal) ssmClient.queryObject("isHdrCrHld", ssmParam);
    }
    // 2017/05/08 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add End

    private boolean existsCusrCrPrfl(String glblCmpyCd, String billToCustCd) {

        final CUST_CR_PRFLTMsgArray custCrPrflTMsgArray = NWXCustCrPrflTMsgThreadLocalCache.getInstance().get(glblCmpyCd, billToCustCd);

        if (custCrPrflTMsgArray.getValidCount() > 0 && ZYPCommonFunc.hasValue(custCrPrflTMsgArray.no(0).crLimitAmt)) {
            return true;
        }

        return false;
    }

    private boolean existsContract(NWZC004001PMsg param) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", param.cpoOrdNum_I.getValue());
        // 2019/05/07 QC#50073 Add Start
        ssmParam.put("ordCatgContrSup", ORD_CATG_CTX_TP.CONTRACT_SUPPLY_ORDER_CATEGORY);
        // 2019/05/07 QC#50073 Add End

        BigDecimal count = (BigDecimal) ssmClient.queryObject("countContr", ssmParam);

        if (count == null || BigDecimal.ZERO.compareTo(count) >= 0) {
            return false;
        }

        return true;
    }

    private boolean isSupplyOrdCatg(CPOTMsg cpo, String glblCmpyCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordCatgCtxTp", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);

        List<String> supplyOrdCatgList = (List<String>) ssmClient.queryObjectList("getSpplyOrdCatg", ssmParam);

        if (supplyOrdCatgList != null && supplyOrdCatgList.contains(cpo.dsOrdCatgCd.getValue())) {
            return true;
        }

        return false;
    }
    
    // 2018/07/02 S21_NA#24189 Add Start
    @SuppressWarnings("unchecked")
    private void resetInvdSlsHldQty(NWZC004001PMsg param, String cpoOrdNum) {
        final String methodNm = "resetInvdSlsHldQty";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            final Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            ssmParam.put("trxHdrNum", cpoOrdNum);
            ssmParam.put("shpgStsCd", SHPG_STS.INVOICED);

            final List<String> invdShpgNumList = ssmClient.queryObjectList("getInvdShpgNumList", ssmParam);

            for (String shpgPlnNum : invdShpgNumList) {
                if (!ZYPCommonFunc.hasValue(shpgPlnNum)) {
                    continue;
                }

                SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                setValue(shpgPlnTMsg.glblCmpyCd, param.glblCmpyCd.getValue());
                setValue(shpgPlnTMsg.shpgPlnNum, shpgPlnNum);
                shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);
                
                setValue(shpgPlnTMsg.slsHldQty, BigDecimal.ZERO);

                // ***** updateSelectionField
                updateSelectionField(shpgPlnTMsg, new String[] {"slsHldQty"});

            }
        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }

    }
    // 2018/07/02 S21_NA#24189 Add End

    // 2018/11/05 S21_NA#29015 Add Start
    private void insBizProcLogBooked(String trxHdrNum) {

        final S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();
        bizLogMsg.setSubSysId(SUB_SYS_ID);
        bizLogMsg.setProcId(PROCESS_ID);
        bizLogMsg.setEventId(EVENT_ID);
        bizLogMsg.setDocTpCd(DOCUMENT_TYPE);
        bizLogMsg.setDocId("");
        bizLogMsg.setPrntDocId(trxHdrNum);
        bizLogMsg.setBizProcCmntTxt_01(null);
        bizLogMsg.setBizProcCmntTxt_02(null);
        bizLogMsg.setBizProcCmntTxt_03(null);

        S21BusinessProcessLog.print(bizLogMsg);
    }
    // 2018/11/05 S21_NA#29015 Add End

    // 2018/12/13 S21_NA#29488 Add Start
    private void createAwaitingInstlHld(NWZC004001PMsg param, String cpoOrdNum) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        // 2019/01/09 S21_NA#29879 Add Start
        ssmParam.put("configTpCdAdd", CONFIG_TP.ADD_TO_CONFIG);
        ssmParam.put("hldRsnCd", HLD_RSN.ARRIVED_WAITING_FOR_INSTALLATION);
        // 2019/01/09 S21_NA#29879 Add End
        ssmParam.put("ordLineStsCancel", ORD_LINE_STS.CANCELLED);
        
        List<Map<String, Object>> targetList = (List<Map<String, Object>>) ssmClient.queryObjectList("getHldTarget", ssmParam);

        List<NWZC044001PMsg> addHldInfoApiPMsgList = new ArrayList<NWZC044001PMsg>();
        for (Map<String, Object> target : targetList) {
            NWZC044001PMsg pmsg = new NWZC044001PMsg();
            ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, (String) target.get("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, (String) target.get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, (String) target.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(pmsg.shpgPlnNum, (String) target.get("SHPG_PLN_NUM"));
            ZYPEZDItemValueSetter.setValue(pmsg.hldRsnCd, HLD_RSN.ARRIVED_WAITING_FOR_INSTALLATION);

            ZYPEZDItemValueSetter.setValue(pmsg.slsDt, param.slsDt);
            addHldInfoApiPMsgList.add(pmsg);
        }

        if (!addHldInfoApiPMsgList.isEmpty()) {
            NWZC044001 ADD_HOLD_API = new NWZC044001();
            ADD_HOLD_API.execute(addHldInfoApiPMsgList, ONBATCH_TYPE.BATCH);
        }

        for (NWZC044001PMsg pMsg : addHldInfoApiPMsgList) {
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                    final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    addXxMsgId(xxMsgId + "CPO Order Number : " + cpoOrdNum);

                }
            }
        }
    }
    // 2018/12/13 S21_NA#29488 Add End

    // Add Start 2019/08/30 QC#53016
    private void addHldByValSetTgt(NWZC004001PMsg param){

        String crRebillDummyWhCdCsv = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_CR_AND_BILL_ONLY_DUMMY_WH_CD, param.glblCmpyCd.getValue());
        List<String> crRebillDummyWhCdList = new ArrayList<String>();
        if (crRebillDummyWhCdCsv != null) {
            String[] crRebillDummyWhCd = crRebillDummyWhCdCsv.split(",");
            crRebillDummyWhCdList = Arrays.asList(crRebillDummyWhCd);
        }
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", param.cpoOrdNum_I.getValue());
        ssmParam.put("crRebillDummyWhCdList", crRebillDummyWhCdList);

        Map<String, Object> getCpoData = null;
        List<Map<String, Object>> getCpoDataList = (List<Map<String, Object>>) ssmClient.queryObjectList("getCpoData", ssmParam);
        if (null == getCpoDataList || getCpoDataList.size() == 0) {
            return;
        }
        getCpoData = getCpoDataList.get(0);

        String hldRsnCd = getHldRsnFromValSet(getCpoData);
        if (ZYPCommonFunc.hasValue(hldRsnCd)) {
            addHld(param, getCpoData, hldRsnCd);
        }
    }

    private String getHldRsnFromValSet(Map<String, Object> getCpoData) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", (String) getCpoData.get("GLBL_CMPY_CD"));
        queryParam.put("dsOrdCatgCd", (String) getCpoData.get("DS_ORD_CATG_CD"));
        queryParam.put("dsOrdTpCd", (String) getCpoData.get("DS_ORD_TP_CD"));
        queryParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.AUTO_PENDING_BILLING_HOLD_TARGET);

        return (String) ssmClient.queryObject("getHldRsnFromValSet", queryParam);
    }

    private void addHld(NWZC004001PMsg param, Map<String, Object> getCpoData, String hldRsnCd){

        String glblCmpyCd = param.glblCmpyCd.getValue();
        HLDTMsg hldTMsg = new HLDTMsg();
        hldTMsg.setSQLID("008");
        hldTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        hldTMsg.setConditionValue("cpoOrdNum01", (String) getCpoData.get("CPO_ORD_NUM"));
        hldTMsg.setConditionValue("hldRsnCd01", hldRsnCd);

        EZDTMsgArray array =  S21ApiTBLAccessor.findByCondition(hldTMsg);
        if (array != null && array.length() != 0) {
            return;
        }

        final Map<String, Object> hldOrd = new HashMap<String, Object>();
        hldOrd.put("HLD_RSN_CD", hldRsnCd);
        hldOrd.put("CPO_ORD_NUM", (String) getCpoData.get("CPO_ORD_NUM"));

        NWZC044001PMsg pMsg = new NWZC044001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, (String) getCpoData.get("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, (String) getCpoData.get("CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.hldRsnCd, HLD_RSN.AUTO_PENDING_BILLING);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);

        new NWZC044001().execute(pMsg, ONBATCH_TYPE.BATCH);
        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            addXxMsgId(xxMsgId + "CPO Order Number : " + (String) getCpoData.get("CPO_ORD_NUM"));
        }
    }
    // Add End 2019/08/30 QC#53016

    // add start 2023/04/25 QC#61337
    private boolean skipHldValidation(String hldRsnCd, String blockBookHldRelFlg, String amtChngInd) {
        if (ZYPConstant.FLG_OFF_N.equals(blockBookHldRelFlg)) {
            return false;
        }
        if (HLD_RSN.MANUAL_PRICE.equals(hldRsnCd) && ZYPConstant.FLG_ON_1.equals(amtChngInd)) {
            return false;
        }
        return true;
    }
    // add end 2023/04/25 QC#61337
}
