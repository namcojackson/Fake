/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 * 
 * <pre>
 * Invoice API
 * This API creates data of Invoice, Credit Memo and Debit Memo from Whole Sales orders.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/06/2009   Fujitsu         N.Mitsuishi     Create          N/A
 * 09/11/2009   Fujitsu         N.Mitsuishi     Update          83
 * 09/15/2009   Fujitsu         N.Mitsuishi     Update          123
 * 09/17/2009   Fujitsu         N.Mitsuishi     Update          115
 * 09/25/2009   Fujitsu         N.Mitsuishi     Update          256
 * 10/07/2009   Fujitsu         N.Mitsuishi     Update          453
 * 10/15/2009   Fujitsu         N.Mitsuishi     Update          775
 * 10/29/2009   Fujitsu         T.Nagase        Update          1159
 * 11/04/2009   Fujitsu         N.Mitsuishi     Update          1068
 * 11/09/2009   Fujitsu         N.Mitsuishi     Update          1563
 * 11/10/2009   Fujitsu         N.Mitsuishi     Update          RQ0349
 * 11/25/2009   Fujitsu         N.Mitsuishi     Update          2091
 * 12/14/2009   Fujitsu         N.Mitsuishi     Update          2525
 * 12/28/2009   Fujitsu         N.Mitsuishi     Update          N/A
 * 01/05/2010   Fujitsu         A.Suda          Update          N/A (refactoring : cache and high-performance Tuning)
 * 01/19/2010   Fujitsu         A.Suda          Update          RQ0826
 * 01/26/2010   Fujitsu         N.Mitsuishi     Update          RQ0826,3171
 * 03/02/2010   Fujitsu         N.Mitsuishi     Update          N/A (Invoice Comment can set even CPO_DTL without only in CPO.)
 * 03/03/2010   Fujitsu         N.Mitsuishi     Update          N/A (Freight Amount can set even Order Entry Screen.)
 * 03/09/2010   Fujitsu         N.Mitsuishi     Update          3471
 * 03/11/2010   Fujitsu         N.Mitsuishi     Update          4116(RQ1096)
 * 04/15/2010   Fujitsu         A.Suda          Update          5384
 * 05/12/2010   Fujitsu         H.Nagashima     Update          5359
 * 05/18/2010   Fujitsu         K.Tajima        Update          6483
 * 05/26/2010   Fujitsu         A.Suda          Update          6756
 * 06/01/2010   Fujitsu         A.Suda          Update          6759
 * 06/04/2010   Fujitsu         A.Suda          Update          6955 
 * 06/08/2010   Fujitsu         H.Nagashima     Update          6664
 * 06/09/2010   Fujitsu         H.Nagashima     Update          4743
 * 06/14/2010   Fujitsu         H.Nagashima     Update          6797,7154
 * 06/29/2010   Fujitsu         H.Nagashima     Update          7278
 * 07/08/2010   Fujitsu         H.Nagashima     Update          7659
 * 07/09/2010   Fujitsu         A.Suda          Update          7694 
 * 07/29/2010   Fujitsu         H.Nagashima     Update          137
 * 08/03/2010   Fujitsu         H.Nagashima     Update          140
 * 08/17/2010   Fujitsu         H.Nagashima     Update          241
 * 08/25/2010   Fujitsu         H.Nagashima     Update          247
 * 09/02/2010   Fujitsu         K.Tajima        Update          349
 * 09/09/2010   Fujitsu         R.Watanabe      Update          438(All2)
 * 09/16/2010   Fujitsu         R.Watanabe      Update          277(All2)
 * 09/17/2010   Fujitsu         R.Watanabe      Update          449(All2)
 * 09/28/2010   Fujitsu         R.Watanabe      Update          N/A(Adjust a fraction.)
 * 09/28/2010   Fujitsu         R.Watanabe      Update          502(All2)
 * 10/15/2010   Fujitsu         R.Watanabe      Update          505(All2)
 * 11/08/2010   Fujitsu         R.Watanabe      Update          516(All2)
 * 11/15/2010   Fujitsu         R.Watanabe      Update          640(All2)
 * 01/20/2011   Fujitsu         N.Sakamoto      Update          1112(All2)
 * 02/11/2011   Fujitsu         K.Tajima        Update          1510(Prod)
 * 02/14/2011   CSAI            M.Takahashi     Update          1551
 * 03/01/2011   Fujitsu         R.Watanabe      Update          1744(Prod)
 * 04/01/2011   Fujitsu         R.Watanabe      Update          1956(Prod)
 * 04/15/2011   Fujitsu         R.Watanabe      Update          1956(Prod)
 * 11/28/2011   CSAI            A.Katayama      Update          ITG#371538
 * 01/10/2012   CSAI            A.Katayama      Update          ITG#377542
 * 09/10/2012   Fujitsu         S.Tsunaki       Update          WDS
 * 12/18/2012   Fujitsu         N.Sakamoto      Update          Def#33(IT)
 * 01/07/2013   Fujitsu         S.Tsunaki       Update          Def#146(IT)
 * 01/10/2013   Fujitsu         S.Tsunaki       Update          Def#155(IT)
 * 01/28/2013   Fujitsu         S.Tsunaki       Update          Def#107(IT)
 * 03/06/2013   Fujitsu         T.Tozuka        Update          Def#734(IT)
 * 08/02/2013   Fujitsu         N.Nakazawa      Update          WDS R-OM010
 * 08/02/2013   Fujitsu         N.Nakazawa      Update          WDS R-OM021
 * 08/02/2013   Fujitsu         N.Nakazawa      Update          WDS R-OM028
 * 08/02/2013   Fujitsu         N.Nakazawa      Update          WDS R-OM041
 * 09/12/2013   Fujitsu         S.Yamamoto      Update          WDS R-OM058
 * 10/15/2013   Fujitsu         S.Yamamoto      Update          WDS QC#2646
 * 12/11/2013   Fujitsu         T.Nakamura      Update          WDS Defect#2957
 * 09/29/2015   Fujitsu         H.Nagashima     Update          CSA
 * 03/24/2015   Fujitsu         H.Nagashima     Update          QC#5514
 * 04/12/2015   Fujitsu         H.Nagashima     Update          QC#6832
 * 06/21/2015   SRAA            K.Aratani       Update          QC#9918
 * 06/24/2015   SRAA            K.Aratani       Update          QC#10791
 * 07/06/2016   Fujitsu         H.Ikeda         Update          QC#10909
 * 08/01/2016   Hitachi         Y.Takeno        Update          QC#9192
 * 08/02/2016   Fujitsu         H.Nagashima     Update          QC#12771
 * 08/30/2016   Fujitsu         H.Nagashima     Update          QC#12892
 * 09/01/2016   Fujitsu         H.Nagashima     Update          QC#10885
 * 10/17/2016   SRAA            K.Aratani       Update          QC#15294
 * 10/21/2016   SRAA            K.Aratani       Update          QC#15503
 * 10/31/2016   Fujitsu         H.Nagashima     Update          QC#14607,15018
 * 11/01/2016   SRAA            K.Aratani       Update          QC#15696
 * 11/11/2016   Fujitsu         H.Nagashima     Update          QC#15701,14292
 * 12/09/2016   Fujitsu         H.Nagashima     Update          QC#16519
 * 12/13/2016   Fujitsu         H.Nagashima     Update          QC#16237
 * 12/20/2016   Fujitsu         H.Nagashima     Update          QC#16716
 * 01/26/2017   Fujitsu         H.Nagashima     Update          QC#15624
 * 02/02/2017   Fujitsu         H.Nagashima     Update          QC#17270
 * 02/03/2017   Fujitsu         H.Nagashima     Update          QC#16830
 * 03/01/2017   Fujitsu         H.Nagashima     Update          QC#16531
 * 2017/04/24   Fujitsu         M.Yamada        Update          QC#18113
 * 04/26/2017   Hitachi         T.Tomita        Update          RS#8381
 * 05/17/2017   Fujitsu         S.Ohki          Update          RS#5749,RS#5751,RS#5753,RS#5754,RS#5755,RS#5756,RS#5757,RS#5758,RS#5759,RS#5761RS#5763,RS#5764,RS#5765,RS#7795,RS#7827,RS#7835,RS#7845,RS#7868,RS#7877,RS#7879,RS#7907,RS#7924,RS#7994,RS#8034,RS#8353,RS#8354,RS#8355
 * 06/21/2017   Fujitsu         H.Nagashima     Update          QC#19398
 * 2017/07/18   Fujitsu         H.Ikeda         Update          QC#19781
 * 2017/07/18   Fujitsu         H.Ikeda         Update          QC#19895
 * 2017/07/25   Fujitsu         H.Ikeda         Update          QC#20083
 * 2017/07/26   Fujitsu         H.Ikeda         Update          QC#19783
 * 2017/08/28   Fujitsu         H.Ikeda         Update          QC#20748
 * 2017/09/27   Fujitsu         H.Nagashima     Update          QC#20605
 * 2017/10/12   Fujitsu         T.Aoi           Update          QC#21645,21777
 * 2017/10/18   Fujitsu         T.Aoi           Update          QC#21730
 * 2017/10/20   Fujitsu         H.Sugawara      Update          QC#21773
 * 2017/10/23   Fujitsu         T.Aoi           Update          QC#20719
 * 2017/10/24   Fujitsu         A.Kosai         Update          QC#21780
 * 2017/10/25   Fujitsu         T.Aoi           Update          QC#21809
 * 2017/10/31   Fujitsu         Y.Kanefusa      Update          S21_NA#22031
 * 2017/11/15   Fujitsu         Y.Kanefusa      Update          S21_NA#22495
 * 2017/12/27   Fujitsu         M.Ohno          Update          S21_NA#22407
 * 2018/01/17   Fujitsu         A.Kosai         Update          S21_NA#23268
 * 01/23/2018   Fujitsu         Mz.Takahashi    Update          QC#23086
 * 03/14/2018   Hitachi         E.Kameishi      Update          QC#23689
 * 03/16/2018   Fujitsu         H.Nagashima     Update          QC#24875
 * 03/29/2018   Fujitsu         H.Nagashima     Update          QC#23559
 * 05/21/2018   Fujitsu         Y.Kanefusa      Update          S21_NA#21841
 * 06/14/2018   Fujitsu         H.Nagashima     Update          QC#26121
 * 08/03/2018   Fujitsu         R.Nakamura      Update          QC#27430
 * 08/07/2018   Fujitsu         R.Nakamura      Update          QC#27438
 * 08/03/2018   Fujitsu         Y.Kanefusa      Update          S21_NA#27479
 * 08/21/2018   Fujitsu         Mz.Takahashi    Update          QC#27172
 * 09/03/2018   Fujitsu         Y.Kanefusa      Create          S21_NA#9700
 * 09/11/2018   Fujitsu         S.Takami        Update          QC#28113
 * 11/13/2018   Fujitsu         K.Kato          Update          QC#27954
 * 01/16/2018   Fujitsu         Hd.Sugawara     Update          QC#29935
 * 06/03/2019   Fujitsu         K.Kato          Update          QC#50654
 * 07/11/2022   CITS            A.Cullano       Update          QC#60272
 * 01/17/2023   Hitachi         D.Yoshida       Update          QC#61468
 * </pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC035001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.leftPad;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Arrays.asList;
import static parts.dbcommon.EZDTBLAccessor.RTNCD_DUPLICATE;
import static parts.dbcommon.EZDTBLAccessor.RTNCD_NOT_FOUND;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BIZ_AREA_ORGTMsg;
import business.db.BLLG_CYCLETMsg;
import business.db.CCYTMsg;
import business.db.CNTYTMsg;
import business.db.COA_PRODTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CR_DR_SUB_RSNTMsg;
import business.db.CTAC_PSNTMsg;
import business.db.DS_INVTY_LOC_VTMsg;
import business.db.DS_INV_LINE_SER_NUMTMsg;
import business.db.DS_INV_LINE_TAX_DTLTMsg;
import business.db.DS_INV_TPTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.DS_TAX_GRP_EXEMTMsg;
import business.db.FRT_CHRG_METHTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.INVTMsg;
import business.db.INV_BOLTMsg;
import business.db.INV_BOLTMsgArray;
import business.db.INV_CASH_DISC_TERMTMsg;
import business.db.INV_CASH_DISC_TERMTMsgArray;
import business.db.INV_LINETMsg;
import business.db.INV_LINETMsgArray;
import business.db.INV_PRMO_INFOTMsg;
import business.db.INV_PRMO_INFOTMsgArray;
import business.db.MDL_NMTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MDSE_STORE_PKGTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.S21_ORGTMsg;
import business.db.S21_PSNTMsg;
import business.db.SVC_ALLOC_TPTMsg;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.parts.NFZC309001PMsg;
import business.parts.NMZC600001PMsg;
import business.parts.NSZC111001PMsg;
import business.parts.NSZC111001_xxNonWtyContrListPMsg;
import business.parts.NSZC115001PMsg;
import business.parts.NSZC115001_svcConfigRefListPMsg;
import business.parts.NWZC035001PMsg;
import business.parts.NWZC035002PMsg;
import business.parts.NWZC036101PMsg;
import business.parts.NWZC036101_taxCalculateInputLinePMsg;
import business.parts.NWZC036101_taxCalculateOutputLinePMsg;
import business.parts.NWZC055001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC309001.NFZC309001;
import com.canon.cusa.s21.api.NMZ.NMZC600001.NMZC600001;
import com.canon.cusa.s21.api.NSZ.NSZC111001.NSZC111001;
import com.canon.cusa.s21.api.NSZ.NSZC111001.constant.NSZC111001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC115001.NSZC115001;
import com.canon.cusa.s21.api.NSZ.NSZC115001.constant.NSZC115001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC036101.NWZC036101;
import com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant;
import com.canon.cusa.s21.api.NWZ.NWZC055001.NWZC055001;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001OrderTakeMdseTBLAccessor;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001RateData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_CHRG_CPLT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL_RSN_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_INV_CLM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PSN_DEPT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SLS_TAX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.EASY_PACK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRINT_STYLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SFTY_INV_TXT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TXT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

public class NWZC035001 extends S21ApiCommonBase {

    public static final String NWZM0163E = "NWZM0163E";
    public static final String NWZM0097E = "NWZM0097E";
    public static final String NWZM0002E = "NWZM0002E";
    public static final String NWZM0003E = "NWZM0003E";
    public static final String NWZM0004E = "NWZM0004E";
    public static final String NWZM0251E = "NWZM0251E";
    public static final String NWZM0252E = "NWZM0252E";
    public static final String NWZM0608E = "NWZM0608E";
    public static final String NWZM0253E = "NWZM0253E";
    public static final String NWZM0254E = "NWZM0254E";
    public static final String NWZM0444E = "NWZM0444E";
    public static final String NWZM0256E = "NWZM0256E";
    public static final String NWZM0257E = "NWZM0257E";
    public static final String NWZM0258E = "NWZM0258E";
    public static final String NWZM0259E = "NWZM0259E";
    public static final String NWZM0260E = "NWZM0260E";
    public static final String NWZM0261E = "NWZM0261E";
    public static final String NWZM0262E = "NWZM0262E";
    public static final String NWZM0255E = "NWZM0255E";
    public static final String NWZM0263E = "NWZM0263E";
    public static final String NWZM0355E = "NWZM0355E";
    public static final String NWZM0264E = "NWZM0264E";
    public static final String NWZM0276E = "NWZM0276E";
    public static final String NWZM0277E = "NWZM0277E";
    public static final String NWZM0278E = "NWZM0278E";
    public static final String NWZM0074E = "NWZM0074E";
    public static final String NWZM0368E = "NWZM0368E";
    public static final String NWZM0439E = "NWZM0439E";
    public static final String NWZM0440E = "NWZM0440E";
    public static final String NWZM0265E = "NWZM0265E";
    public static final String NWZM0341E = "NWZM0341E";
    public static final String NWZM0357E = "NWZM0357E";
    public static final String NWZM0269E = "NWZM0269E";
    public static final String NWZM0271E = "NWZM0271E";
    public static final String NWZM0272E = "NWZM0272E";
    public static final String NWZM0273E = "NWZM0273E";
    public static final String NWZM0275E = "NWZM0275E";
    public static final String NWZM0924E = "NWZM0924E";
    public static final String NWZM0971E = "NWZM0971E";
    public static final String NWZM1095E = "NWZM1095E";
    public static final String NWZM1096E = "NWZM1096E";
    public static final String NWZM1097E = "NWZM1097E";
    public static final String NWZM1098E = "NWZM1098E";
    public static final String NWZM1274E = "NWZM1274E";
    public static final String NWZM0925E = "NWZM0925E";
    public static final String NWZM1127E = "NWZM1127E";
    public static final String NWZM1128E = "NWZM1128E";
    public static final String NWZM1129E = "NWZM1129E";
    public static final String NWZM1508E = "NWZM1508E";
    public static final String NWZM1509E = "NWZM1509E";
    public static final String NWZM1510E = "NWZM1510E";
    public static final String NWZM1511E = "NWZM1511E";
    public static final String NWZM1512E = "NWZM1512E";
    public static final String NWZM1513E = "NWZM1513E";
    public static final String NWZM1514E = "NWZM1514E";

    private static final int DIGIT_INV_BOL_LINE_NUM           = new INV_BOLTMsg().getAttr("invBolLineNum").getDigit();
    private static final int DIGIT_INV_LINE_NUM               = new INV_LINETMsg().getAttr("invLineNum").getDigit();
    private static final int DIGIT_INV_LINE_SUB_NUM           = new INV_LINETMsg().getAttr("invLineSubNum").getDigit();
    private static final int DIGIT_INV_CASH_DISC_TERM_DTL_NUM = new INV_CASH_DISC_TERMTMsg().getAttr("invCashDiscTermDtlNum").getDigit();

    private static final String FIXED_INV_LINE_SUB_TRX_NUM          = "001";
    private static final String FIXED_INV_PRMO_INFO_SEQ_NUM         = "001";
    private static final String FIXED_SET_ITEM_CPO_DTL_LINE_SUB_NUM = "000";

    private static final String STS_CD_ZERO = "0";
    private static final String STS_CD_ONE  = "1";
    private static final String STS_CD_PROCESSED = "2";

    private static final int CONST_SPLY_PGM_UNIT_AMT_RATE_SCALE = 6;

    /** dummy mdse cd varchar const key */
    private static final String CONST_KEY_DUMMY_CR_MDSE_CD = "DUMMY_CR_MDSE_CD";
    /** Invoice Line COA Account Code varchar const key */
    private static final String CONST_KEY_INV_LINE_COA_ACCT_CD = "INV_LINE_COA_ACCT_CD";
    /** Off Invoice COA Account Code varchar const key */
    private static final String CONST_KEY_OFF_INV_COA_ACCT_CD = "OFF_INV_COA_ACCT_CD";

    /** Credit Card Payment Term Cach Discount Code */
    private static final String CONST_KEY_CC_CASH_DISC_CD = "CC_CASH_DISC_CD";

    private static final String CONST_KEY_PMT_TERM_CR_CARD = "PMT_TERM_CR_CARD";

    /** dummy frt tax mdse cd varchar const key */
    private static final String CONST_KEY_FRT_TAX_DUMMY_MDSE_CD = "FRT_TAX_DUMMY_MDSE_CD";
    /** default transaction type for tax varchar const key */
    private static final String CONST_KEY_DEFAULT_TAX_TRX_TP = "DEFAULT_TAX_TRX_TP";
    /** frt tax detail line number */
    private static final String CONST_FRT_TAX_LINE_NUM = "*";
    /** search contact by bill */
    private static final String CONST_SEARCH_TARGET_BILL_TO = "BILL_TO";
    /** search contact by ship */
    private static final String CONST_SEARCH_TARGET_SHIP_TO = "SHIP_TO";
    /** optional invoice grouping key (DS_INV_GRP_ATTR) */
    private static final String CONST_INV_GRP_ATTRB_TXT_SHIP_TO_CUST_ACCT   = "SHIP_TO_CUST_ACCT_CD";
    private static final String CONST_INV_GRP_ATTRB_TXT_SHIP_TO_CUST_LOC    = "SHIP_TO_CUST_LOC_CD";
    private static final String CONST_INV_GRP_ATTRB_TXT_SHIP_TO_CTAC_PSN_PK = "SHIP_TO_CUST_CTAC_PSN_PK";

    private static final String NOT_EQUALS = "!";

    private static final String Y = ZYPConstant.FLG_ON_Y;
    private static final String N = ZYPConstant.FLG_OFF_N;

    private static final String ANSI_TRX_NUM_810 = "810";

    private static final String CONST_ORD_CATG_BIZ_CTX_EASY_PACK1 = "EASY_PACK1";

    private static final String DROP_SHIP_WH = "DS";

    // 2018/11/13 S21_NA#27954 Add Start
    private static final String ORD_CATG_CTX_TP_SUPPLIES_ORDER = "SUPPLIES_ORDER";
    private static final String ORD_CATG_CTX_TP_EQUIPMENT_ORDER = "EQUIPMENT_ORDER";
    // 2018/11/13 S21_NA#27954 Add End

    private final S21SsmEZDClient   ssmEzdClient;
    private final S21SsmBatchClient ssmBatchClient;

    /** funcCcyCd */
    private String funcCcyCd;
    /** dummy mdse cd */
    private String dummyCrMdseCd;
    /** global company code */
    private String glblCmpyCd;

    /** funcCcyScale */
    private int funcCcyScale;

    private S21LRUMap<String, Object> s21lruMap= new S21LRUMap<String, Object>();

    private String billOnlyLineCategCd;

    // 2018/09/11 QC#28113 Add Start
    private S21LRUMap<String, EZDTMsgArray> mdseStorePkgMap = new S21LRUMap<String, EZDTMsgArray>();
    // 2018/09/11 QC#28113 Add End

    public NWZC035001() {
        super();
        this.ssmEzdClient   = S21SsmEZDClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    public void execute(final List<NWZC035001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {

        List<S21ApiMessageMap> msgMapList = new ArrayList<S21ApiMessageMap>();

        for (NWZC035001PMsg pMsg : pMsgList) {
            S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
            msgMapList.add(msgMap);
        }

        doProcess(msgMapList, onBatchType);

        for (S21ApiMessageMap msgMap : msgMapList) {
            msgMap.flush();
        }
    }

    protected void doProcess(List<S21ApiMessageMap> msgMapList, final ONBATCH_TYPE onBatchType) {

        boolean isSuccess = true;

        isSuccess = inputCheck(msgMapList);
        if (!isSuccess) {
            return;
        }
        dummyCrMdseCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_DUMMY_CR_MDSE_CD, glblCmpyCd);
        if (dummyCrMdseCd == null) {
            throw new S21AbendException("[Error]Not Found 'VAR_CHAR_CONST' : varCharConstNm=" + CONST_KEY_DUMMY_CR_MDSE_CD);
        }

        isSuccess = getFuncCcyCd(msgMapList.get(0));
        if (!isSuccess) {
            return;
        }

        isSuccess = getFuncCcyScale(msgMapList.get(0));
        if (!isSuccess) {
            return;
        }

        List<OrderDataBean> orderDataBeanList = new ArrayList<OrderDataBean>();
        List<INV_PRMO_INFOBean> invPrmoInfoBeanList = new ArrayList<INV_PRMO_INFOBean>();

        isSuccess = getOrderData(msgMapList, orderDataBeanList, invPrmoInfoBeanList);
        if (!isSuccess) {
            return;
        }

        isSuccess = getComparisonObject(orderDataBeanList);
        if (!isSuccess) {
            return;
        }

        isSuccess = getCtacPsn(orderDataBeanList);
        if (!isSuccess) {
            return;
        }
        isSuccess = setOptGrpKey(orderDataBeanList);
        if (!isSuccess) {
            return;
        }

        final List<INVBean> invBeanList = new ArrayList<INVBean>();
        if (!getINVData(orderDataBeanList, invBeanList)) {
            return;
        }

        final List<INV_BOLBean> allInvBolBeanList = new ArrayList<INV_BOLBean>();
        getINV_BOLData(orderDataBeanList, allInvBolBeanList);
        if (!getFromMasterForINV_BOLData(allInvBolBeanList)) {
            isSuccess = false;
        }

        final List<INV_LINEBean> allInvLineBeanList = new ArrayList<INV_LINEBean>();
        // 2019/06/25 QC#50780 Mod Start
//        if (!getINV_LINEData(orderDataBeanList, allInvLineBeanList)) {
        if (!getINV_LINEData(orderDataBeanList, allInvLineBeanList, invBeanList)) {
        // 2019/06/25 QC#50780 Mod End
            isSuccess = false;
        }
        // QC#21841 2018/05/21 Add Start
        if(!getCalcBaseData(allInvLineBeanList)){
            isSuccess = false;
        }
        // QC#21841 2018/05/21 Add End

        if (!isSuccess) {
            return;
        }

        makingInvoiceStructure(invBeanList, allInvBolBeanList, allInvLineBeanList, invPrmoInfoBeanList);

        isSuccess = addInvLineForBillWithEquipmentData(invBeanList, allInvBolBeanList, allInvLineBeanList);
        if (!isSuccess) {
            return;
        }

        isSuccess = moneyItemCalculationBeforeGetTax(invBeanList);
        if (!isSuccess) {
            return;
        }

        isSuccess = makeDummySetMdseData(allInvBolBeanList, allInvLineBeanList);
        if (!isSuccess) {
            return;
        }

        isSuccess = getFromMasterForINV_LINEData(invBeanList, allInvLineBeanList);

        if (!isSuccess) {
            return;
        }

        getBackOrderQty(allInvLineBeanList);

        setNumbers(invBeanList, onBatchType);

        String cpoSrcTpCd = invBeanList.get(0).getCpoSrcTpCd();

        // Mod Start 2017/10/20 QC#21773
        //if (CPO_SRC_TP.CREDIT_AND_REBILL_ENTRY.equals(cpoSrcTpCd) && CPO_ORD_TP.CREDIT.equals(invBeanList.get(0).getInvTMsg().cpoOrdTpCd.getValue())) {
        if ((CPO_SRC_TP.CREDIT.equals(cpoSrcTpCd) || CPO_SRC_TP.REBILL.equals(cpoSrcTpCd)) //
                && CPO_ORD_TP.CREDIT.equals(invBeanList.get(0).getInvTMsg().cpoOrdTpCd.getValue())) {
            // Mod End 2017/10/20 QC#21773
            getTaxForCreditOrderOfCreditAndRebill(invBeanList);
        } else {
            isSuccess = getTaxFromTaxCalculationAPI(invBeanList, onBatchType);
        }
        if (!isSuccess) {
            return;
        }

        isSuccess = moneyItemCalculationAfterGetTax(invBeanList);
        if (!isSuccess) {
            return;
        }

        // QC#27479 2018/08/03 Del Start
        //String invTpCd = invBeanList.get(0).getInvTMsg().invTpCd.getValue();
        //if (INV_TP.INVOICE.equals(invTpCd) || INV_TP.DEBIT_MEMO.equals(invTpCd)) {
        //    isSuccess = callCreditLimitUpdateAPI(invBeanList, onBatchType);
        //    if (!isSuccess) {
        //        return;
        //    }
        //}
        // QC#27479 2018/08/03 Del End

        isSuccess = masterValidation(invBeanList);
        if (!isSuccess) {
            return;
        }

        isSuccess = insertUpdateINVandINV_CASH_DISC_TERM(invBeanList);
        if (!isSuccess) {
            return;
        }

//        isSuccess = insertINV_BOL(allInvBolBeanList);
        isSuccess = insertINV_BOL(allInvBolBeanList, invBeanList);// QC#26121 mod
        if (!isSuccess) {
            return;
        }

//        isSuccess = insertINV_LINEandINV_PRMO_INFO(allInvLineBeanList);
        isSuccess = insertINV_LINEandINV_PRMO_INFO(allInvLineBeanList, invBeanList);    // QC#26121 mod
        if (!isSuccess) {
            return;
        }

        isSuccess = insertDS_INV_LINE_SER_NUM(invBeanList);
        if (!isSuccess) {
            return;
        }
        isSuccess = insertDS_INV_LINE_TAX_DTL(invBeanList);
        if (!isSuccess) {
            return;
        }
        isSuccess = createInvoiceSalesCredit(invBeanList);
        if (!isSuccess) {
            return;
        }
        // START 2018/03/13 E.Kameishi [QC#23689,DEL]
//        isSuccess = callInvoiceDistributionAPI(invBeanList);
//        if (!isSuccess) {
//            return;
//        }
        // END 2018/03/13 E.Kameishi [QC#23689,DEL]
        // QC#27479 2018/08/03 Add Start
        String invTpCd = invBeanList.get(0).getInvTMsg().invTpCd.getValue();
        if (INV_TP.INVOICE.equals(invTpCd) || INV_TP.DEBIT_MEMO.equals(invTpCd)) {
            isSuccess = callCreditLimitUpdateAPI(invBeanList, onBatchType);
            if (!isSuccess) {
                return;
            }
        }
        // QC#27479 2018/08/03 Add End
    }

    private boolean callCreditLimitUpdateAPI(List<INVBean> invBeanList, ONBATCH_TYPE onBatchType) {

        boolean isSuccess = true;

        List<NMZC600001PMsg> pMsgList = new ArrayList<NMZC600001PMsg>();

        for (INVBean invBean : invBeanList) {

            String ccPmtTermCashDisc = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_CC_CASH_DISC_CD, glblCmpyCd);
            if (ZYPCommonFunc.hasValue(ccPmtTermCashDisc)) {
                List<String> ccPmtTermCashDiscList = asList(ccPmtTermCashDisc.split(","));
                if (ccPmtTermCashDiscList.contains(invBean.getInvTMsg().pmtTermCashDiscCd.getValue())) {
                    continue;
                }
            }

            S21ApiMessageMap msgMap = invBean.getMsgMap();
            INVTMsg invTMsg = invBean.getInvTMsg();

            NMZC600001PMsg pMsg = new NMZC600001PMsg();
            pMsg.glblCmpyCd.setValue(invTMsg.glblCmpyCd.getValue());
            pMsg.xxModeCd.setValue(NMZC600001.MODE_ALL);
            pMsg.dsAcctNum.setValue(invTMsg.billToCustAcctCd.getValue());
            pMsg.billToCustCd.setValue(invTMsg.billToCustCd.getValue());
            pMsg.invAmt.setValue(invBean.getThisTimeInvTotFuncNetAmt());
            pMsg.invDt.setValue(((NWZC035001PMsg) msgMap.getPmsg()).slsDt.getValue());
            pMsg.updKeyNum.setValue(invTMsg.invNum.getValue());
            pMsg.slsDt.setValue(((NWZC035001PMsg) msgMap.getPmsg()).slsDt.getValue());

            pMsgList.add(pMsg);
        }

        NMZC600001 creditLimitUpdateAPI = new NMZC600001();
        creditLimitUpdateAPI.execute(pMsgList, onBatchType);

        Iterator<INVBean> invBeanListItr = invBeanList.iterator();
        for (NMZC600001PMsg pMsg : pMsgList) {
            S21ApiMessageMap msgMap = invBeanListItr.next().getMsgMap();
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                    msgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    private boolean exchFuncAmtToDealAmt(INVBean invBean, List<NWXC001001ExchangePriceData> invBolExchDataList, List<NWXC001001ExchangePriceData> invLineExchDataList, S21LRUMap<String, String> cacheMap) {

        boolean isSuccess = true;

        S21ApiMessageMap invMsgMap = invBean.getMsgMap();
        NWZC035001PMsg paramPMsg = (NWZC035001PMsg) invMsgMap.getPmsg();
        INVTMsg invTMsg = invBean.getInvTMsg();

        String acctArthTpCd = cacheMap.get(invTMsg.dealCcyCd.getValue());
        if (acctArthTpCd == null) {
            NWXC001001RateData rateData = NWXC001001Exchange.getRate(invTMsg.glblCmpyCd.getValue(), invTMsg.dealCcyCd.getValue(), paramPMsg.slsDt.getValue());
            if (rateData == null) {
                invMsgMap.addXxMsgId(NWZM0368E);
                isSuccess = false;
            } else if (!rateData.getXxMsgIdList().isEmpty()) {
                for (String msgId : rateData.getXxMsgIdList()) {
                    invMsgMap.addXxMsgId(msgId);
                }
                isSuccess = false;
            } else {
                invBean.setAcctArthTpCd(rateData.getAcctArthTpCd());
                cacheMap.put(rateData.getAcctArthTpCd(), invTMsg.dealCcyCd.getValue());
            }
        } else {
            invBean.setAcctArthTpCd(acctArthTpCd);
        }

        NWXC001001ExchangeData exchData = new NWXC001001ExchangeData();
        exchData.setGlblCmpyCd(invTMsg.glblCmpyCd.getValue());
        exchData.setCcyCd(invTMsg.dealCcyCd.getValue());
        exchData.setSlsDt(paramPMsg.slsDt.getValue());

        exchData.setPriceData(invBolExchDataList);

        NWXC001001RateData rateData = new NWXC001001RateData();
        rateData.setAcctArthTpCd(invBean.getAcctArthTpCd());
        rateData.setActlExchRate(invTMsg.actlApplyExchRate.getValue());

        NWXC001001Exchange.exchDealUnitPrice(exchData, rateData);
        if (!exchData.getXxMsgIdList().isEmpty()) {
            for (String msgId : exchData.getXxMsgIdList()) {
                invMsgMap.addXxMsgId(msgId);
            }
            isSuccess = false;
        }

        invBolExchDataList = exchData.getPriceData();

        exchData.setPriceData(invLineExchDataList);
        NWXC001001Exchange.exchDealUnitPrice(exchData, rateData);
        if (!exchData.getXxMsgIdList().isEmpty()) {
            for (String msgId : exchData.getXxMsgIdList()) {
                invMsgMap.addXxMsgId(msgId);
            }
            isSuccess = false;
        }

        invLineExchDataList = exchData.getPriceData();

        List<INV_BOLBean> invBolBeanList = invBean.getInvBolBeanList();

        Iterator<NWXC001001ExchangePriceData> invBolExchDataItr = invBolExchDataList.iterator();
        Iterator<NWXC001001ExchangePriceData> invLineExchDataItr = invLineExchDataList.iterator();

        for (INV_BOLBean invBolBean : invBolBeanList) {
            InvBolExchangeAmountData invBolExchAmtData = (InvBolExchangeAmountData) invBolExchDataItr.next();
            INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();
            invBolTMsg.frtDealTaxAmt.setValue(invBolExchAmtData.getFrtDealTaxAmt());

            List<INV_LINEBean> invLineBeanList = invBolBean.getInvLineBeanList();

            for (INV_LINEBean invLineBean : invLineBeanList) {
                INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();
                InvLineExchangeAmountData invLineExchAmtData = (InvLineExchangeAmountData) invLineExchDataItr.next();
                invLineTMsg.invLineDealTaxAmt.setValue(invLineExchAmtData.getInvLineDealTaxAmt());
            }
        }
        return isSuccess;
    }

    @SuppressWarnings("unchecked")
    private void getBackOrderQty(List<INV_LINEBean> invLineBeanList) {

        for (INV_LINEBean invLineBean : invLineBeanList) {

            NWZC035001PMsg paramPMsg = (NWZC035001PMsg) invLineBean.getMsgMap().getPmsg();
            INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

            if (FIXED_SET_ITEM_CPO_DTL_LINE_SUB_NUM.equals(invLineTMsg.cpoDtlLineSubNum.getValue()) && N.equals(invLineTMsg.setRatioKeepFlg.getValue())) {
                continue;
            }

            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd", paramPMsg.glblCmpyCd.getValue());
            mapParam.put("cpoOrdNum", paramPMsg.cpoOrdNum.getValue());
            mapParam.put("cpoDtlLineNum", paramPMsg.cpoDtlLineNum.getValue());
            mapParam.put("cpoDtlLineSubNum", invLineTMsg.cpoDtlLineSubNum.getValue());
            mapParam.put("shpgStsCd", SHPG_STS.VALIDATED);
            mapParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
            mapParam.put("mdseTpCd", MDSE_TP.SALES_BOM);

            List<BigDecimal> ssmResList = (List<BigDecimal>) ssmBatchClient.queryObjectList("querySHPG_PLN", mapParam);
            BigDecimal boQty = ZERO;
            for (BigDecimal ordQty : ssmResList) {
                boQty = boQty.add(ordQty);
            }
            invLineTMsg.boQty.setValue(boQty);
        }
    }

    private boolean getComparisonObject(List<OrderDataBean> orderDataBeanList) {

        boolean isSuccess = true;

        Map<String, Object> cacheMap = new HashMap<String, Object>();

        boolean creditOrderFlg = false;
        // 2017/10/12 QC#21645 Mod Start
        BigDecimal cpoDtlDealNetAmt = BigDecimal.ZERO;
        for (OrderDataBean orderDataBean : orderDataBeanList) {
            NWZC035002PMsg orderDataPMsg = orderDataBean.getNWZC035002PMsg();
            //if (CR_REBIL.CREDIT.equals(orderDataPMsg.crRebilCd.getValue())) {
            //    creditOrderFlg = true;
            //    break;
            //}
            cpoDtlDealNetAmt = cpoDtlDealNetAmt.add(orderDataPMsg.cpoDtlDealNetAmt.getValue());
        }
        if (cpoDtlDealNetAmt.compareTo(BigDecimal.ZERO) < 0) {
            creditOrderFlg = true;
        }
        // 2017/10/12 QC#21645 Mod End

        for (OrderDataBean orderDataBean : orderDataBeanList) {

            S21ApiMessageMap msgMap = orderDataBean.getMsgMap();
            NWZC035001PMsg paramPMsg = (NWZC035001PMsg) msgMap.getPmsg();
            NWZC035002PMsg orderDataPMsg = orderDataBean.getNWZC035002PMsg();

            if (!paramPMsg.cpoOrdNum.getValue().equals(cacheMap.get("cpoOrdNum"))) {
                cacheMap.put("cpoOrdNum", paramPMsg.cpoOrdNum.getValue());

                S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryBILL_TO_FOR_FLR_PLN_FLG", orderDataPMsg, orderDataPMsg);
                if (ssmRes.getQueryResultCount() == 0) {
                    msgMap.addXxMsgId(NWZM0608E);
                    isSuccess = false;
                }
                cacheMap.put("flPlnFlg", orderDataPMsg.flPlnFlg.getValue());

                if (Y.equals(orderDataPMsg.flPlnFlg.getValue())) {
                    Map<String, String> mapParam = new HashMap<String, String>();
                    mapParam.put("glblCmpyCd", paramPMsg.glblCmpyCd.getValue());
                    mapParam.put("cpoOrdNum", paramPMsg.cpoOrdNum.getValue());
                    mapParam.put("hldRsnCd", HLD_RSN.FLOOR_PLAN);

                    ssmRes = ssmEzdClient.queryEZDMsg("queryHLD", mapParam, orderDataPMsg);

                    cacheMap.put("apvlNum", orderDataPMsg.apvlNum.getValue());
                }

                // QC#26121 2018/06/14 del Start
//                if (creditOrderFlg) {
//                    orderDataPMsg.invTpCd.setValue(INV_TP.CREDIT_MEMO);
//                } else {
//                    orderDataPMsg.invTpCd.setValue(INV_TP.INVOICE);
//                }
//
//                cacheMap.put("invTpCd", orderDataPMsg.invTpCd.getValue());
                // QC#26121 2018/06/14 del End

                DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTmsg = new DS_ORD_TP_PROC_DFNTMsg();
                dsOrdTpProcDfnTmsg.glblCmpyCd.setValue(paramPMsg.glblCmpyCd.getValue());
                dsOrdTpProcDfnTmsg.dsOrdTpCd.setValue(orderDataPMsg.dsOrdTpCd.getValue());

                dsOrdTpProcDfnTmsg = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(dsOrdTpProcDfnTmsg);

                if (dsOrdTpProcDfnTmsg != null) {
                    setValue(orderDataPMsg.dsInvTpCd,   dsOrdTpProcDfnTmsg.dsInvTpCd.getValue());
                } else {
                    msgMap.addXxMsgId(NWZM1514E);
                    isSuccess = false;
                }
                cacheMap.put("dsInvTpCd", orderDataPMsg.dsInvTpCd.getValue());

            } else {
                orderDataPMsg.flPlnFlg.setValue((String) cacheMap.get("flPlnFlg"));
                setValue(orderDataPMsg.apvlNum, (String) cacheMap.get("apvlNum"));
//                orderDataPMsg.invTpCd.setValue((String) cacheMap.get("invTpCd"));     // QC#26121 2018/06/14 del
                setValue(orderDataPMsg.crInvTpCd, (String) cacheMap.get("crInvTpCd"));
                setValue(orderDataPMsg.dsInvTpCd, (String) cacheMap.get("dsInvTpCd"));
            }

        }
        return isSuccess;
    }

    private boolean getFreightFromCPO(INVBean invBean, INV_BOLBean invBolBean, boolean isFirstInvBolOfProcess) {

        boolean isSuccess = true;

        INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();
        BigDecimal shipDealFrtAmt = ZERO;

        if (invBean.isNeedUpdate() || !isFirstInvBolOfProcess) {
            return isSuccess;
        }

        INVTMsg invTMsg = invBean.getInvTMsg();
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", invTMsg.glblCmpyCd.getValue());
        mapParam.put("wholeSales", TRX_SRC_TP.WHOLE_SALES);
        mapParam.put("cpoOrdNum", invTMsg.cpoOrdNum.getValue());

        Integer resultCnt = (Integer) ssmBatchClient.queryObject("queryCPOforCount", mapParam);

        if (resultCnt > 0) {
            return isSuccess;
        }

        BigDecimal shipFuncFrtAmt = invBolTMsg.shipFuncFrtAmt.getValue();
        invBolTMsg.shipFuncFrtAmt.setValue(shipFuncFrtAmt.add(invBolBean.getShpgChrgAmt()));

        if (this.funcCcyCd.equals(invTMsg.dealCcyCd.getValue())) {
            shipDealFrtAmt = invBolTMsg.shipDealFrtAmt.getValue().add(invBolBean.getShpgChrgAmt());
            invBolTMsg.shipDealFrtAmt.setValue(shipDealFrtAmt);

        } else {

            S21ApiMessageMap msgMap = invBolBean.getMsgMap();

            NWXC001001RateData rateData = NWXC001001Exchange.getRate(invTMsg.glblCmpyCd.getValue(), invTMsg.dealCcyCd.getValue(), invTMsg.invDt.getValue());
            if (rateData == null) {
                msgMap.addXxMsgId(NWZM0368E);
                isSuccess = false;
            } else if (!rateData.getXxMsgIdList().isEmpty()) {
                for (String msgId : rateData.getXxMsgIdList()) {
                    msgMap.addXxMsgId(msgId);
                }
                isSuccess = false;
            } else {
                invBean.setAcctArthTpCd(rateData.getAcctArthTpCd());
            }

            List<NWXC001001ExchangePriceData> invBolExchDataList = new ArrayList<NWXC001001ExchangePriceData>();
            InvBolExchangeAmountData invBolExchAmtData = new InvBolExchangeAmountData();
            invBolExchAmtData.setShpgChrgFuncAmt(invBolBean.getShpgChrgAmt());
            invBolExchDataList.add(invBolExchAmtData);

            NWXC001001ExchangeData exchData = new NWXC001001ExchangeData();
            exchData.setGlblCmpyCd(invTMsg.glblCmpyCd.getValue());
            exchData.setCcyCd(invTMsg.dealCcyCd.getValue());
            exchData.setSlsDt(invTMsg.invDt.getValue());
            exchData.setPriceData(invBolExchDataList);

            rateData = new NWXC001001RateData();
            rateData.setAcctArthTpCd(invBean.getAcctArthTpCd());
            rateData.setActlExchRate(invTMsg.actlApplyExchRate.getValue());

            NWXC001001Exchange.exchDealUnitPrice(exchData, rateData);
            if (!exchData.getXxMsgIdList().isEmpty()) {

                for (String msgId : exchData.getXxMsgIdList()) {
                    msgMap.addXxMsgId(msgId);
                }
                isSuccess = false;
            }

            shipDealFrtAmt = invBolTMsg.shipDealFrtAmt.getValue().add(invBolBean.getShpgChrgAmt());
            invBolTMsg.shipDealFrtAmt.setValue(shipDealFrtAmt);
        }

        return isSuccess;
    }

    private boolean getFromMasterForINV_BOLData(List<INV_BOLBean> allInvBolBeanList) {

        boolean isSuccess = true;

        S21LRUMap<String, Object> s21LRUMap = new S21LRUMap<String, Object>();

        for (INV_BOLBean invBolBean : allInvBolBeanList) {
            S21ApiMessageMap msgMap = invBolBean.getMsgMap();
            INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();

            if (hasValue(invBolTMsg.shpgSvcLvlCd)) {
                String cacheKey = createCacheKey("querySHPG_SVC_LVL", invBolTMsg.shpgSvcLvlCd.getValue());
                String shpgSvcLvlNm = (String) s21LRUMap.get(cacheKey);
                if (shpgSvcLvlNm == null) {
                    S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("querySHPG_SVC_LVL", invBolTMsg, invBolTMsg);
                    if (ssmRes.getQueryResultCount() == 0) {
                        msgMap.addXxMsgId(NWZM0276E);
                        isSuccess = false;
                    }
                    s21LRUMap.put(cacheKey, invBolTMsg.shpgSvcLvlNm.getValue());
                } else {
                    invBolTMsg.shpgSvcLvlNm.setValue(shpgSvcLvlNm);
                }
            }

            if (hasValue(invBolTMsg.frtChrgMethCd)) {

                FRT_CHRG_METHTMsg fcmTMsg = new FRT_CHRG_METHTMsg();
                fcmTMsg.glblCmpyCd.setValue(glblCmpyCd);
                fcmTMsg.frtChrgMethCd.setValue(invBolTMsg.frtChrgMethCd.getValue());
                fcmTMsg = (FRT_CHRG_METHTMsg) S21CacheTBLAccessor.findByKey(fcmTMsg);

                if (fcmTMsg == null) {
                    msgMap.addXxMsgId(NWZM0277E);
                    isSuccess = false;
                } else {
                    setValue(invBolTMsg.frtChrgMethNm, fcmTMsg.frtChrgMethNm);
                }
            }

            if (hasValue(invBolTMsg.frtChrgToCd)) {
                String cacheKey = createCacheKey("queryFRT_CHRG_TO", invBolTMsg.frtChrgToCd.getValue());
                String frtChrgToNm = (String) s21LRUMap.get(cacheKey);
                if (frtChrgToNm == null) {
                    S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryFRT_CHRG_TO", invBolTMsg, invBolTMsg);
                    if (ssmRes.getQueryResultCount() == 0) {
                        msgMap.addXxMsgId(NWZM0278E);
                        isSuccess = false;
                    }
                    s21LRUMap.put(cacheKey, invBolTMsg.frtChrgToNm.getValue());
                } else {
                    invBolTMsg.frtChrgToNm.setValue(frtChrgToNm);
                }
            }

            String shipToCustAcctCd = invBolTMsg.shipToCustAcctCd.getValue();
            String cacheKey = createCacheKey("getCustAcctNm", shipToCustAcctCd);
            String custName = (String) s21LRUMap.get(cacheKey);
            if (custName == null) {
                Map<String, String> mapParam = new HashMap<String, String>();
                mapParam.put("glblCmpyCd", glblCmpyCd);
                mapParam.put("dsAcctNum", shipToCustAcctCd);

                custName = (String) ssmBatchClient.queryObject("getCustAcctNm", mapParam);
                if (custName == null) {
                    msgMap.addXxMsgId(NWZM1509E);
                    isSuccess = false;
                } else {
                    s21LRUMap.put(cacheKey, custName);
                }
            }
            setValue(invBolTMsg.shipToCustAcctNm, custName);
        }

        return isSuccess;
    }

    private CTAC_PSNTMsg getCtacPsnNm(String searchTargetStr, String dsCpoConfigPk, String cpoOrdNum, String custLocCd, String custAcctCd, String ctacTpCd, String invDt, S21LRUMap<String, Object> s21LRUMap) {

        //get Contact from cache
        String cacheKey1 = createCacheKey("getCtacPsnFromCpoConfig",        searchTargetStr, dsCpoConfigPk);
        String cacheKey2 = createCacheKey("getCtacPsnFromCpoOrder",         searchTargetStr, cpoOrdNum);
        String cacheKey3 = createCacheKey("getCtacPsnFromCustomerLocation", searchTargetStr, custLocCd);
        String cacheKey4 = createCacheKey("getCtacPsnFromCustomerAccount",  searchTargetStr, custAcctCd);

        CTAC_PSNTMsg ctacPsnTmsg = (CTAC_PSNTMsg) s21LRUMap.get(cacheKey1);
        if (ctacPsnTmsg != null) {
            return ctacPsnTmsg;
        }
        ctacPsnTmsg = (CTAC_PSNTMsg) s21LRUMap.get(cacheKey2);
        if (ctacPsnTmsg != null) {
            return ctacPsnTmsg;
        }
        ctacPsnTmsg = (CTAC_PSNTMsg) s21LRUMap.get(cacheKey3);
        if (ctacPsnTmsg != null) {
            return ctacPsnTmsg;
        }
        ctacPsnTmsg = (CTAC_PSNTMsg) s21LRUMap.get(cacheKey4);
        if (ctacPsnTmsg != null) {
            return ctacPsnTmsg;
        }

        //QC#23559 add Start
        boolean isBillToSearch = CONST_SEARCH_TARGET_BILL_TO.equals(searchTargetStr);
        //QC#23559 add End
        //searche table
        // 1.by config
        ctacPsnTmsg = new CTAC_PSNTMsg();
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd",    glblCmpyCd);
        mapParam.put("ctacTpCd",      ctacTpCd);
        mapParam.put("dsCpoConfigPk", dsCpoConfigPk);
//QC#23559 add Start
        mapParam.put("cpoOrdNum",     cpoOrdNum);
        if (isBillToSearch) {
            mapParam.put("scdCtacTpCd", CTAC_TP.BILL_TO_CONTACT);
        } else {
            mapParam.put("scdCtacTpCd", CTAC_TP.ORDER_CONTACT);
        }
//QC#23559 add End
        S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("getCtacPsnFromCpo", mapParam, ctacPsnTmsg);

        int resultCount = ssmRes.getQueryResultCount();
        if (resultCount != 0) {
            s21LRUMap.put(cacheKey1, ctacPsnTmsg);
            return ctacPsnTmsg;
        }

        // 2.by Order Num
        ctacPsnTmsg = new CTAC_PSNTMsg();
        mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd",    glblCmpyCd);
        mapParam.put("ctacTpCd",      ctacTpCd);
        mapParam.put("cpoOrdNum",     cpoOrdNum);
//QC#23559 add Start
        if (isBillToSearch) {
            mapParam.put("scdCtacTpCd", CTAC_TP.BILL_TO_CONTACT);
        } else {
            mapParam.put("scdCtacTpCd", CTAC_TP.ORDER_CONTACT);
        }
//QC#23559 add End
        ssmRes = ssmEzdClient.queryEZDMsg("getCtacPsnFromCpo", mapParam, ctacPsnTmsg);

        resultCount = ssmRes.getQueryResultCount();
        if (resultCount != 0) {
            s21LRUMap.put(cacheKey2, ctacPsnTmsg);
            return ctacPsnTmsg;
        }

        // 3. by location
        ctacPsnTmsg = new CTAC_PSNTMsg();
        mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd",   glblCmpyCd);
        mapParam.put("ctacTpCd",     ctacTpCd);
        mapParam.put("custLocCd",    custLocCd);
        mapParam.put("activeFlg",    ZYPConstant.FLG_ON_Y);
        mapParam.put("invDt",        invDt);
//        if (CONST_SEARCH_TARGET_BILL_TO.equals(searchTargetStr)) {
        if (isBillToSearch) {   //QC#23559 mod
            mapParam.put("dsCtacPsnDeptCd", DS_CTAC_PSN_DEPT.ACCOUNTS_PAYABLE);
            mapParam.put("billToLocFlg",    ZYPConstant.FLG_ON_Y);
            mapParam.put("scdCtacTpCd", CTAC_TP.BILL_TO_CONTACT); //QC#23559 add
        } else {
            mapParam.put("scdCtacTpCd", CTAC_TP.ORDER_CONTACT);
        }

        ssmRes = ssmEzdClient.queryEZDMsg("getCtacPsnFromCustomer", mapParam, ctacPsnTmsg);

        resultCount = ssmRes.getQueryResultCount();
        if (resultCount != 0) {
            s21LRUMap.put(cacheKey3, ctacPsnTmsg);
            return ctacPsnTmsg;
        }

        // 4. by account
        ctacPsnTmsg = new CTAC_PSNTMsg();
        mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd",   glblCmpyCd);
        mapParam.put("ctacTpCd",     ctacTpCd);
        mapParam.put("custAcctCd",   custAcctCd);
        mapParam.put("activeFlg",    ZYPConstant.FLG_ON_Y);
        mapParam.put("invDt",        invDt);
//        if (CONST_SEARCH_TARGET_BILL_TO.equals(searchTargetStr)) {
        if (isBillToSearch) {   //QC#23559 mod
            mapParam.put("dsCtacPsnDeptCd", DS_CTAC_PSN_DEPT.ACCOUNTS_PAYABLE);
            mapParam.put("scdCtacTpCd", CTAC_TP.BILL_TO_CONTACT); //QC#23559 add
        } else {
            mapParam.put("scdCtacTpCd", CTAC_TP.ORDER_CONTACT);
        }

        ssmRes = ssmEzdClient.queryEZDMsg("getCtacPsnFromCustomer", mapParam, ctacPsnTmsg);

        resultCount = ssmRes.getQueryResultCount();
        if (resultCount != 0) {
            s21LRUMap.put(cacheKey4, ctacPsnTmsg);
            return ctacPsnTmsg;
        }

        return ctacPsnTmsg;
    }

    //QC#27172 add Start
    private CTAC_PSNTMsg getBillToCtacPsnNm(String dsCpoConfigPk, String cpoOrdNum, String custLocCd, String custAcctCd, String ctacTpCd, String invDt, S21LRUMap<String, Object> s21LRUMap) {

        //get Contact from cache
        String cacheKey1 = createCacheKey("getCtacPsnFromCpoConfig",        CONST_SEARCH_TARGET_BILL_TO, dsCpoConfigPk);
        String cacheKey2 = createCacheKey("getCtacPsnFromCpoOrder",         CONST_SEARCH_TARGET_BILL_TO, cpoOrdNum);
        String cacheKey3 = createCacheKey("getCtacPsnFromCustomerLocation", CONST_SEARCH_TARGET_BILL_TO, custLocCd);
        String cacheKey4 = createCacheKey("getCtacPsnFromCustomerAccount",  CONST_SEARCH_TARGET_BILL_TO, custAcctCd);

        CTAC_PSNTMsg ctacPsnTmsg = (CTAC_PSNTMsg) s21LRUMap.get(cacheKey1);
        if (ctacPsnTmsg != null) {
            return ctacPsnTmsg;
        }
        ctacPsnTmsg = (CTAC_PSNTMsg) s21LRUMap.get(cacheKey2);
        if (ctacPsnTmsg != null) {
            return ctacPsnTmsg;
        }
        ctacPsnTmsg = (CTAC_PSNTMsg) s21LRUMap.get(cacheKey3);
        if (ctacPsnTmsg != null) {
            return ctacPsnTmsg;
        }
        ctacPsnTmsg = (CTAC_PSNTMsg) s21LRUMap.get(cacheKey4);
        if (ctacPsnTmsg != null) {
            return ctacPsnTmsg;
        }

        // 1.by config
        ctacPsnTmsg = new CTAC_PSNTMsg();
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd",    glblCmpyCd);
        mapParam.put("ctacTpCd",      ctacTpCd);
        mapParam.put("dsCpoConfigPk", dsCpoConfigPk);
        mapParam.put("cpoOrdNum",     cpoOrdNum);
        mapParam.put("scdCtacTpCd", CTAC_TP.BILL_TO_CONTACT);
        S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("getCtacPsnFromCpo", mapParam, ctacPsnTmsg);

        int resultCount = ssmRes.getQueryResultCount();
        if (resultCount != 0) {
            s21LRUMap.put(cacheKey1, ctacPsnTmsg);
            return ctacPsnTmsg;
        }

        // 2.by Order Num
        ctacPsnTmsg = new CTAC_PSNTMsg();
        mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd",    glblCmpyCd);
        mapParam.put("ctacTpCd",      ctacTpCd);
        mapParam.put("cpoOrdNum",     cpoOrdNum);
        mapParam.put("scdCtacTpCd", CTAC_TP.BILL_TO_CONTACT);
        ssmRes = ssmEzdClient.queryEZDMsg("getCtacPsnFromCpo", mapParam, ctacPsnTmsg);

        resultCount = ssmRes.getQueryResultCount();
        if (resultCount != 0) {
            s21LRUMap.put(cacheKey2, ctacPsnTmsg);
            return ctacPsnTmsg;
        }

        // 3. by location
        ctacPsnTmsg = new CTAC_PSNTMsg();
        mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd",   glblCmpyCd);
        mapParam.put("ctacTpCd",     ctacTpCd);
        mapParam.put("custLocCd",    custLocCd);
        mapParam.put("activeFlg",    ZYPConstant.FLG_ON_Y);
        mapParam.put("invDt",        invDt);
        mapParam.put("dsCtacPsnDeptCd", DS_CTAC_PSN_DEPT.ACCOUNTS_PAYABLE);
        mapParam.put("billToLocFlg",    ZYPConstant.FLG_ON_Y);
        mapParam.put("scdCtacTpCd", CTAC_TP.BILL_TO_CONTACT);

        List<Map> ssmResList = (List<Map>) ssmBatchClient.queryObjectList("getBillToCtacPsnFromCustomer", mapParam);

        if ((ssmResList != null) &&  !ssmResList.isEmpty()) {
            if (ssmResList.size() == 1) {
                ctacPsnTmsg = toCtacPsn(ssmResList.get(0));
                s21LRUMap.put(cacheKey3, ctacPsnTmsg);
                return(ctacPsnTmsg);
            }
        }

        // 4. by account
        ctacPsnTmsg = new CTAC_PSNTMsg();
        mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd",   glblCmpyCd);
        mapParam.put("ctacTpCd",     ctacTpCd);
        mapParam.put("custAcctCd",   custAcctCd);
        mapParam.put("activeFlg",    ZYPConstant.FLG_ON_Y);
        mapParam.put("invDt",        invDt);
        mapParam.put("dsCtacPsnDeptCd", DS_CTAC_PSN_DEPT.ACCOUNTS_PAYABLE);
        mapParam.put("scdCtacTpCd", CTAC_TP.BILL_TO_CONTACT);

        ssmResList = (List<Map>) ssmBatchClient.queryObjectList("getBillToCtacPsnFromCustomer", mapParam);

        if ((ssmResList != null) &&  !ssmResList.isEmpty()) {
            if (ssmResList.size() == 1) {
                ctacPsnTmsg = toCtacPsn(ssmResList.get(0));
                s21LRUMap.put(cacheKey4, ctacPsnTmsg);
                return(ctacPsnTmsg);
            }
        }

        return ctacPsnTmsg;
    }
    //QC#27172 add End

    //QC#27172 add Start
    private CTAC_PSNTMsg toCtacPsn(Map rs){
        CTAC_PSNTMsg result = new CTAC_PSNTMsg();

        setValue(result.ctacPsnPk, (BigDecimal)rs.get("CTAC_PSN_PK"));
        setValue(result.ctacPsnFirstNm, (String)rs.get("CTAC_PSN_FIRST_NM"));
        setValue(result.ctacPsnMidNm, (String)rs.get("CTAC_PSN_MID_NM"));
        setValue(result.ctacPsnLastNm, (String)rs.get("CTAC_PSN_LAST_NM"));

        return result;
    }
    //QC#27172 add End

    @SuppressWarnings("unchecked")
    private void getFromMasterForINV_CASH_DISC_TERM(NWZC035002PMsg orderDataPMsg, INVBean invBean) {

        S21ApiMessageMap msgMap = invBean.getMsgMap();
        NWZC035001PMsg paramPMsg = (NWZC035001PMsg) msgMap.getPmsg();

        String glblCmpyCd = orderDataPMsg.glblCmpyCd.getValue();
        String beforeAdjustNETDueDt = invBean.getBeforeAdjustNETDueDt();
        if (beforeAdjustNETDueDt == null) {
            return;
        }
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("cashDiscTermCd", orderDataPMsg.cashDiscTermCd.getValue());
        mapParam.put("slsDt", paramPMsg.slsDt.getValue());

        BigDecimal cashDiscAOT = new BigDecimal(ZYPDateUtil.getDiffDays(beforeAdjustNETDueDt, paramPMsg.slsDt.getValue()));
        mapParam.put("cashDiscAOT", cashDiscAOT);

        List<Map> ssmResList = (List<Map>) ssmBatchClient.queryObjectList("queryCASH_DISC_TERM", mapParam);

        if (ssmResList.isEmpty()) {
            // Even if the search result doesn't exist, it doesn't make it to the error.
            return;
        }

        int invCashDiscTermDtlNum = 1;
        for (Map mapRes : ssmResList) {

            INV_CASH_DISC_TERMTMsg invCashDiscTermTMsg = new INV_CASH_DISC_TERMTMsg();

            invCashDiscTermTMsg.glblCmpyCd.setValue(glblCmpyCd);

            BigDecimal cashDiscPct = (BigDecimal) mapRes.get("CASH_DISC_PCT");
            invCashDiscTermTMsg.invCashDiscRatioPct.setValue(cashDiscPct);

            int minusCashDiscFromAOT = ((BigDecimal) mapRes.get("CASH_DISC_FROM_AOT")).negate().intValue();

            String cashDiscDueDt = ZYPDateUtil.addDays(beforeAdjustNETDueDt, minusCashDiscFromAOT);

            invCashDiscTermTMsg.invCashDiscDueDt.setValue(cashDiscDueDt);

            invCashDiscTermTMsg.invCashDiscTermDtlNum.setValue(leftPad(Integer.toString(invCashDiscTermDtlNum++), DIGIT_INV_CASH_DISC_TERM_DTL_NUM, "0"));

            invBean.addInvCashDiscTerm(invCashDiscTermTMsg);
        }
    }

    @SuppressWarnings("unchecked")
    private boolean getFromMasterForINV_LINEData(List<INVBean> invBeanList, List<INV_LINEBean> allInvLineList) {

        boolean isSuccess = true;

        try {

            final S21LRUMap<String, Object> cache = new S21LRUMap<String, Object>();

            for (INVBean invBean : invBeanList) {
                INVTMsg invTMsg = invBean.getInvTMsg();
                String sellToCustCd = invTMsg.sellToCustCd.getValue();

                List<INV_BOLBean> invBolBeanList = invBean.getInvBolBeanList();
                for (INV_BOLBean invBolBean : invBolBeanList) {

                    List<INV_LINEBean> invLineBeanList = invBolBean.getInvLineBeanList();

                    for (INV_LINEBean invLineBean : invLineBeanList) {

                        S21ApiMessageMap msgMap = invLineBean.getMsgMap();
                        INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

                        //CSA MOD Start
                        String cacheKey;
                        Map resultMap;

                        S21_ORGTMsg s21OrgTmsg = new S21_ORGTMsg();
                        setValue(s21OrgTmsg.glblCmpyCd, glblCmpyCd);
                        setValue(s21OrgTmsg.tocCd, invLineTMsg.slsRepTocCd.getValue());
                        s21OrgTmsg = (S21_ORGTMsg) S21CacheTBLAccessor.findByKey(s21OrgTmsg);
                        if (s21OrgTmsg == null) {
                            msgMap.addXxMsgId(NWZM0263E);
                            isSuccess = false;
                        } else {
                            setValue(invLineTMsg.firstOrgCd, s21OrgTmsg.firstOrgCd.getValue());
                            setValue(invLineTMsg.firstOrgNm, s21OrgTmsg.firstOrgNm.getValue());
                            setValue(invLineTMsg.scdOrgCd, s21OrgTmsg.scdOrgCd.getValue());
                            setValue(invLineTMsg.scdOrgNm, s21OrgTmsg.scdOrgNm.getValue());
                            setValue(invLineTMsg.thirdOrgCd, s21OrgTmsg.thirdOrgCd.getValue());
                            setValue(invLineTMsg.thirdOrgNm, s21OrgTmsg.thirdOrgNm.getValue());
                            setValue(invLineTMsg.frthOrgCd, s21OrgTmsg.frthOrgCd.getValue());
                            setValue(invLineTMsg.frthOrgNm, s21OrgTmsg.frthOrgNm.getValue());
                            setValue(invLineTMsg.fifthOrgCd, s21OrgTmsg.fifthOrgCd.getValue());
                            setValue(invLineTMsg.fifthOrgNm, s21OrgTmsg.fifthOrgNm.getValue());
                            setValue(invLineTMsg.sixthOrgCd, s21OrgTmsg.sixthOrgCd.getValue());
                            setValue(invLineTMsg.sixthOrgNm, s21OrgTmsg.sixthOrgNm.getValue());
                            setValue(invLineTMsg.svnthOrgCd, s21OrgTmsg.svnthOrgCd.getValue());
                            setValue(invLineTMsg.svnthOrgNm, s21OrgTmsg.svnthOrgNm.getValue());
                            setValue(invLineTMsg.eighthOrgCd, s21OrgTmsg.eighthOrgCd.getValue());
                            setValue(invLineTMsg.eighthOrgNm, s21OrgTmsg.eighthOrgNm.getValue());
                            setValue(invLineTMsg.ninthOrgCd, s21OrgTmsg.ninthOrgCd.getValue());
                            setValue(invLineTMsg.ninthOrgNm, s21OrgTmsg.ninthOrgNm.getValue());
                            setValue(invLineTMsg.tenthOrgCd, s21OrgTmsg.tenthOrgCd.getValue());
                            setValue(invLineTMsg.tenthOrgNm, s21OrgTmsg.tenthOrgNm.getValue());
                            setValue(invLineTMsg.elvthOrgCd, s21OrgTmsg.elvthOrgCd.getValue());
                            setValue(invLineTMsg.elvthOrgNm, s21OrgTmsg.elvthOrgNm.getValue());
                            setValue(invLineTMsg.slsRepTocNm, s21OrgTmsg.tocNm.getValue());
                        }

                        String mdseCd = NWXC001001OrderTakeMdseTBLAccessor.findMdseCd(invLineTMsg.glblCmpyCd.getValue(), invLineTMsg.mdseCd.getValue());

                        if (dummyCrMdseCd.equals(mdseCd)) {
                            cacheKey = createCacheKey("queryMDSE", mdseCd, invLineTMsg.crDrRsnSubCd.getValue());
                        } else {
                            cacheKey = createCacheKey("queryMDSE", mdseCd);
                        }
                        resultMap = (Map) cache.get(cacheKey);
                        if (resultMap == null) {
                            Map<String, String> mapParam = new HashMap<String, String>();
                            mapParam.put("glblCmpyCd", invLineTMsg.glblCmpyCd.getValue());
                            mapParam.put("mdseCd", mdseCd);
                            Map<String, Object> mapRes = (Map<String, Object>) ssmBatchClient.queryObject("queryMDSE", mapParam);
                            if (mapRes == null) {
                                msgMap.addXxMsgId(NWZM0265E);
                                isSuccess = false;
                                continue;
                            } else {
                                if (Y.equals((String) mapRes.get("EXP_ITEM_FLG"))) {
                                    int mdseNmDigit = invLineTMsg.getAttr("mdseNm").getDigit();
                                    String crDrSubRsnNm = (String) ssmBatchClient.queryObject("queryCR_DR_SUB_RSN_FOR_MDSE_NM", invLineTMsg);
                                    if (mdseNmDigit < crDrSubRsnNm.length()) {
                                        crDrSubRsnNm = crDrSubRsnNm.substring(0, mdseNmDigit);
                                    }
                                    setValue(invLineTMsg.mdseNm, crDrSubRsnNm);
                                } else {
                                    setValue(invLineTMsg.mdseNm, (String) mapRes.get("MDSE_NM"));
                                }
                                setValue(invLineTMsg.zerothProdCtrlCd, (String) mapRes.get("ZEROTH_PROD_CTRL_CD"));
                                setValue(invLineTMsg.zerothProdCtrlNm, (String) mapRes.get("ZEROTH_PROD_CTRL_NM"));
                                setValue(invLineTMsg.firstProdCtrlCd, (String) mapRes.get("FIRST_PROD_CTRL_CD"));
                                setValue(invLineTMsg.firstProdCtrlNm, (String) mapRes.get("FIRST_PROD_CTRL_NM"));
                                setValue(invLineTMsg.scdProdCtrlCd, (String) mapRes.get("SCD_PROD_CTRL_CD"));
                                setValue(invLineTMsg.scdProdCtrlNm, (String) mapRes.get("SCD_PROD_CTRL_NM"));
                                setValue(invLineTMsg.thirdProdCtrlCd, (String) mapRes.get("THIRD_PROD_CTRL_CD"));
                                setValue(invLineTMsg.thirdProdCtrlNm, (String) mapRes.get("THIRD_PROD_CTRL_NM"));
                                setValue(invLineTMsg.frthProdCtrlCd, (String) mapRes.get("FRTH_PROD_CTRL_CD"));
                                setValue(invLineTMsg.frthProdCtrlNm, (String) mapRes.get("FRTH_PROD_CTRL_NM"));
                                setValue(invLineTMsg.fifthProdCtrlCd, (String) mapRes.get("FIFTH_PROD_CTRL_CD"));
                                setValue(invLineTMsg.fifthProdCtrlNm, (String) mapRes.get("FIFTH_PROD_CTRL_NM"));
                                setValue(invLineTMsg.sixthProdCtrlCd, (String) mapRes.get("SIXTH_PROD_CTRL_CD"));
                                setValue(invLineTMsg.sixthProdCtrlNm, (String) mapRes.get("SIXTH_PROD_CTRL_NM"));
                                setValue(invLineTMsg.svnthProdCtrlCd, (String) mapRes.get("SVNTH_PROD_CTRL_CD"));
                                setValue(invLineTMsg.svnthProdCtrlNm, (String) mapRes.get("SVNTH_PROD_CTRL_NM"));
                                invLineBean.setInvtyCtrlFlg((String) mapRes.get("INVTY_CTRL_FLG"));
                                invLineBean.setInvtyValFlg((String) mapRes.get("INVTY_VAL_FLG"));
                                invLineBean.setMdseTpCd((String) mapRes.get("MDSE_TP_CD"));
                            }
                            Map<String, Object> tempMap = new HashMap<String, Object>();
                            tempMap.put("mdseNm", invLineTMsg.mdseNm.getValue());
                            tempMap.put("zerothProdCtrlCd", invLineTMsg.zerothProdCtrlCd.getValue());
                            tempMap.put("zerothProdCtrlNm", invLineTMsg.zerothProdCtrlNm.getValue());
                            tempMap.put("firstProdCtrlCd", invLineTMsg.firstProdCtrlCd.getValue());
                            tempMap.put("firstProdCtrlNm", invLineTMsg.firstProdCtrlNm.getValue());
                            tempMap.put("scdProdCtrlCd", invLineTMsg.scdProdCtrlCd.getValue());
                            tempMap.put("scdProdCtrlNm", invLineTMsg.scdProdCtrlNm.getValue());
                            tempMap.put("thirdProdCtrlCd", invLineTMsg.thirdProdCtrlCd.getValue());
                            tempMap.put("thirdProdCtrlNm", invLineTMsg.thirdProdCtrlNm.getValue());
                            tempMap.put("frthProdCtrlCd", invLineTMsg.frthProdCtrlCd.getValue());
                            tempMap.put("frthProdCtrlNm", invLineTMsg.frthProdCtrlNm.getValue());
                            tempMap.put("fifthProdCtrlCd", invLineTMsg.fifthProdCtrlCd.getValue());
                            tempMap.put("fifthProdCtrlNm", invLineTMsg.fifthProdCtrlNm.getValue());
                            tempMap.put("sixthProdCtrlCd", invLineTMsg.sixthProdCtrlCd.getValue());
                            tempMap.put("sixthProdCtrlNm", invLineTMsg.sixthProdCtrlNm.getValue());
                            tempMap.put("svnthProdCtrlCd", invLineTMsg.svnthProdCtrlCd.getValue());
                            tempMap.put("svnthProdCtrlNm", invLineTMsg.svnthProdCtrlNm.getValue());
                            tempMap.put("invtyCtrlFlg", invLineBean.getInvtyCtrlFlg());
                            tempMap.put("invtyValFlg", invLineBean.getInvtyValFlg());
                            tempMap.put("mdseTpCd", invLineBean.getMdseTpCd());
                            cache.put(cacheKey, tempMap);
                        } else {
                            invLineTMsg.mdseNm.setValue((String) resultMap.get("mdseNm"));
                            invLineTMsg.zerothProdCtrlCd.setValue((String) resultMap.get("zerothProdCtrlCd"));
                            invLineTMsg.zerothProdCtrlNm.setValue((String) resultMap.get("zerothProdCtrlNm"));
                            invLineTMsg.firstProdCtrlCd.setValue((String) resultMap.get("firstProdCtrlCd"));
                            invLineTMsg.firstProdCtrlNm.setValue((String) resultMap.get("firstProdCtrlNm"));
                            invLineTMsg.scdProdCtrlCd.setValue((String) resultMap.get("scdProdCtrlCd"));
                            invLineTMsg.scdProdCtrlNm.setValue((String) resultMap.get("scdProdCtrlNm"));
                            invLineTMsg.thirdProdCtrlCd.setValue((String) resultMap.get("thirdProdCtrlCd"));
                            invLineTMsg.thirdProdCtrlNm.setValue((String) resultMap.get("thirdProdCtrlNm"));
                            invLineTMsg.frthProdCtrlCd.setValue((String) resultMap.get("frthProdCtrlCd"));
                            invLineTMsg.frthProdCtrlNm.setValue((String) resultMap.get("frthProdCtrlNm"));
                            invLineTMsg.fifthProdCtrlCd.setValue((String) resultMap.get("fifthProdCtrlCd"));
                            invLineTMsg.fifthProdCtrlNm.setValue((String) resultMap.get("fifthProdCtrlNm"));
                            invLineTMsg.sixthProdCtrlCd.setValue((String) resultMap.get("sixthProdCtrlCd"));
                            invLineTMsg.sixthProdCtrlNm.setValue((String) resultMap.get("sixthProdCtrlNm"));
                            invLineTMsg.svnthProdCtrlCd.setValue((String) resultMap.get("svnthProdCtrlCd"));
                            invLineTMsg.svnthProdCtrlNm.setValue((String) resultMap.get("svnthProdCtrlNm"));
                            invLineBean.setInvtyCtrlFlg((String) resultMap.get("invtyCtrlFlg"));
                            invLineBean.setInvtyValFlg((String) resultMap.get("invtyValFlg"));
                            invLineBean.setMdseTpCd((String) resultMap.get("mdseTpCd"));
                        }

                        if (hasValue(invLineTMsg.coaProdCd)) {

                            boolean isSalesAdministration = TRX.SALES.equals(invLineTMsg.trxCd.getValue()) && COA_PROD.ADMINISTRATION.equals(invLineTMsg.coaProdCd.getValue());

                            if (isSalesAdministration) {
                                cacheKey = createCacheKey("queryCOA_PROD", invLineTMsg.coaProdCd.getValue(), invLineTMsg.slsRepTocCd.getValue());
                            } else {
                                cacheKey = createCacheKey("queryCOA_PROD", invLineTMsg.coaProdCd.getValue());
                            }

                            COA_PRODTMsg coaProdMsg = (COA_PRODTMsg) cache.get(cacheKey);
                            if (coaProdMsg == null) {
                                S21SsmEZDResult ssmRes = null;
                                if (isSalesAdministration) {
                                    ssmRes = ssmEzdClient.queryEZDMsg("queryCOA_PROD_BySalesRep", invLineTMsg, invLineTMsg);
                                } else {
                                    ssmRes = ssmEzdClient.queryEZDMsg("queryCOA_PROD", invLineTMsg, invLineTMsg);
                                }

                                if (ssmRes.getQueryResultCount() == 0) {
                                    msgMap.addXxMsgId(NWZM0265E);
                                    isSuccess = false;
                                    continue;
                                }

                                coaProdMsg = new COA_PRODTMsg();
                                setValue(coaProdMsg.coaProdCd, invLineTMsg.coaProdCd);
                                setValue(coaProdMsg.coaProdNm, invLineTMsg.coaProdNm);
                                cache.put(cacheKey, coaProdMsg);
                            } else {
                                invLineTMsg.coaProdCd.setValue(coaProdMsg.coaProdCd.getValue());
                                invLineTMsg.coaProdNm.setValue(coaProdMsg.coaProdNm.getValue());
                            }
                        }

                        cacheKey = createCacheKey("queryMDSE_SFTY_INFO", mdseCd);
                        resultMap = (Map) cache.get(cacheKey);
                        if (resultMap == null) {
                            Map<String, String> mapParam = new HashMap<String, String>();
                            mapParam.put("glblCmpyCd", invLineTMsg.glblCmpyCd.getValue());
                            mapParam.put("mdseCd", mdseCd);
                            S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryMDSE_SFTY_INFO", mapParam, invLineTMsg);
                            if (ssmRes.getQueryResultCount() == 0) {
                                // Even if the search result doesn't exist, it doesn't make it to the error.
                                continue;
                            }
                            Map<String, Object> tempMap = new HashMap<String, Object>();

                            tempMap.put("mercCntnFlg", invLineTMsg.mercCntnFlg.getValue());
                            cache.put(cacheKey, tempMap);
                        } else {

                            invLineTMsg.mercCntnFlg.setValue((String) resultMap.get("mercCntnFlg"));
                        }

                        if (Y.equals(invLineTMsg.mercCntnFlg.getValue())) {
                            cacheKey = createCacheKey("querySFTY_INV_TXT", SFTY_INV_TXT.TEXT_TO_BE_PRINTED_ON_INVOICE_FOR_MDSE_WITH_HG);
                            String mercStmtOnInvTxt = (String) cache.get(cacheKey);
                            if (mercStmtOnInvTxt == null) {
                                Map<String, String> mapParam = new HashMap<String, String>();
                                mapParam.put("glblCmpyCd", invLineTMsg.glblCmpyCd.getValue());
                                mapParam.put("sftyInvTxtCd", SFTY_INV_TXT.TEXT_TO_BE_PRINTED_ON_INVOICE_FOR_MDSE_WITH_HG);
                                Map mapRes = (Map) ssmBatchClient.queryObject("querySFTY_INV_TXT", mapParam);
                                // Even if the search result doesn't exist, it doesn't make it to the error.
                                if (mapRes != null) {
                                    String firstSftyInvTxt = nvl((String) mapRes.get("FIRST_SFTY_INV_TXT"));
                                    String scdSftyInvTxt = nvl((String) mapRes.get("SCD_SFTY_INV_TXT"));
                                    String thirdSftyInvTxt = nvl((String) mapRes.get("THIRD_SFTY_INV_TXT"));
                                    invLineTMsg.mercStmtOnInvTxt.setValue((firstSftyInvTxt + " " + scdSftyInvTxt + " " + thirdSftyInvTxt).trim());
                                    cache.put(cacheKey, invLineTMsg.mercStmtOnInvTxt.getValue());
                                }
                            } else {
                                invLineTMsg.mercStmtOnInvTxt.setValue(mercStmtOnInvTxt);
                            }
                        }

                        String slsRepTocCd = invLineTMsg.slsRepTocCd.getValue();
                        cacheKey = createCacheKey("queryTRTY_STRU", slsRepTocCd);
                        resultMap = (Map<String, String>) cache.get(cacheKey);
                        if (resultMap == null) {
                            Map<String, String> mapParam = new HashMap<String, String>();
                            mapParam.put("glblCmpyCd", invLineTMsg.glblCmpyCd.getValue());
                            mapParam.put("tocCd", slsRepTocCd);
                            resultMap = (Map<String, String>) ssmBatchClient.queryObject("queryTRTY_STRU", mapParam);
                        }
                        if (resultMap != null) {
                            setValue(invLineTMsg.dsFirstOrgCd,  (String) resultMap.get("FIRST_ORG_CD"));
                            setValue(invLineTMsg.dsFirstOrgNm,  (String) resultMap.get("FIRST_ORG_NM"));
                            setValue(invLineTMsg.dsScdOrgCd,    (String) resultMap.get("SCD_ORG_CD"));
                            setValue(invLineTMsg.dsScdOrgNm,    (String) resultMap.get("SCD_ORG_NM"));
                            setValue(invLineTMsg.dsThirdOrgCd,  (String) resultMap.get("THIRD_ORG_CD"));
                            setValue(invLineTMsg.dsThirdOrgNm,  (String) resultMap.get("THIRD_ORG_NM"));
                            setValue(invLineTMsg.dsFrthOrgCd,   (String) resultMap.get("FRTH_ORG_CD"));
                            setValue(invLineTMsg.dsFrthOrgNm,   (String) resultMap.get("FRTH_ORG_NM"));
                            setValue(invLineTMsg.dsFifthOrgCd,  (String) resultMap.get("FIFTH_ORG_CD"));
                            setValue(invLineTMsg.dsFifthOrgNm,  (String) resultMap.get("FIFTH_ORG_NM"));
                            setValue(invLineTMsg.dsSixthOrgCd,  (String) resultMap.get("SIXTH_ORG_CD"));
                            setValue(invLineTMsg.dsSixthOrgNm,  (String) resultMap.get("SIXTH_ORG_NM"));
                            setValue(invLineTMsg.dsSvnthOrgCd,  (String) resultMap.get("SVNTH_ORG_CD"));
                            setValue(invLineTMsg.dsSvnthOrgNm,  (String) resultMap.get("SVNTH_ORG_NM"));
                            setValue(invLineTMsg.dsEighthOrgCd, (String) resultMap.get("EIGHTH_ORG_CD"));
                            setValue(invLineTMsg.dsEighthOrgNm, (String) resultMap.get("EIGHTH_ORG_NM"));
                            setValue(invLineTMsg.dsNinthOrgCd,  (String) resultMap.get("NINTH_ORG_CD"));
                            setValue(invLineTMsg.dsNinthOrgNm,  (String) resultMap.get("NINTH_ORG_NM"));
                            setValue(invLineTMsg.dsTenthOrgCd,  (String) resultMap.get("TENTH_ORG_CD"));
                            setValue(invLineTMsg.dsTenthOrgNm,  (String) resultMap.get("TENTH_ORG_NM"));
                            setValue(invLineTMsg.bizAreaOrgCd,  (String) resultMap.get("FIRST_BIZ_AREA_ORG_CD"));
                            BIZ_AREA_ORGTMsg bizAreaOrgTmsg = new BIZ_AREA_ORGTMsg();
                            setValue(bizAreaOrgTmsg.glblCmpyCd, glblCmpyCd);
                            setValue(bizAreaOrgTmsg.bizAreaOrgCd, invLineTMsg.bizAreaOrgCd.getValue());
                            bizAreaOrgTmsg = (BIZ_AREA_ORGTMsg) S21CacheTBLAccessor.findByKey(bizAreaOrgTmsg);
                            if (bizAreaOrgTmsg != null) {
                                setValue(invLineTMsg.bizAreaOrgNm, bizAreaOrgTmsg.bizAreaOrgNm.getValue());
                            }
                        }

                        //MDL_NM
                        MDL_NMTMsg mdlNmTmsg = new MDL_NMTMsg();
                        setValue(mdlNmTmsg.t_GlblCmpyCd, glblCmpyCd);
                        setValue(mdlNmTmsg.t_MdlId, invLineTMsg.mdlId.getValue());
                        mdlNmTmsg = (MDL_NMTMsg) S21CacheTBLAccessor.findByKey(mdlNmTmsg);
                        if (mdlNmTmsg != null) {
                            setValue(invLineTMsg.mdlNm, mdlNmTmsg.t_MdlNm.getValue());
                        }

                    }
                }
            }

            return isSuccess;

        } finally {

            if (isSuccess) {
                String invLineCoaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_INV_LINE_COA_ACCT_CD, glblCmpyCd);
                String offInvCoaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_OFF_INV_COA_ACCT_CD, glblCmpyCd);

                List<String> invLineCoaAcctLst = new ArrayList<String>();
                List<String> offInvCoaAcctLst = new ArrayList<String>();

                if (hasValue(invLineCoaAcctCd)) {
                    invLineCoaAcctLst = Arrays.asList(invLineCoaAcctCd.split(","));
                }
                if (hasValue(offInvCoaAcctCd)) {
                    offInvCoaAcctLst = Arrays.asList(offInvCoaAcctCd.split(","));
                }

                final S21LRUMap<String, String> cache = new S21LRUMap<String, String>();

                // --------------------------------------------------
                // Cost to Zero.
                // + 010-02 : Sales Without Cost
                // + 010-03 : Credti or Debit
                // --------------------------------------------------
                for (INV_LINEBean invLineBean : allInvLineList) {

                    final INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();
                    String trxCd = invLineTMsg.trxCd.getValue();
                    String trxRsnCd = invLineTMsg.trxRsnCd.getValue();
                    String crDrSubRsnCd = invLineTMsg.crDrRsnSubCd.getValue();
                    String cacheKey = createCacheKey("drSubRsnCd", crDrSubRsnCd);

                    if (!offInvCoaAcctLst.isEmpty()) {
                        List<INV_PRMO_INFOTMsg> invPrmoInfoTMsgLst = invLineBean.getInvPrmoInfoList();
                        for (INV_PRMO_INFOTMsg invPrmoInfoTMsg : invPrmoInfoTMsgLst) {

                            if (hasValue(invPrmoInfoTMsg.coaAcctCd) && offInvCoaAcctLst.contains(invPrmoInfoTMsg.coaAcctCd.getValue())) {

                                for (String invLineCoaAcct : invLineCoaAcctLst) {

                                    String[] value = invLineCoaAcct.split("-");

                                    if (value.length == 4) {

                                        // -------------------------------------------------------------------------------------------------
                                        // Variable Character Const Table
                                        //  VAR_CHAR_CONST_VAL setting
                                        //  value + "," + value + ",".....
                                        //   value = TRX_CD + "-" + TRX_RSN_CD + "-" + CR_DR_SUB_RSN.PRC_PROT_CATG_CD + "-" + COA_ACCT_CD
                                        // -------------------------------------------------------------------------------------------------

                                        String prcProtCatgCd = "";

                                        String constTrxCd = value[0];
                                        String constTrxRsnCd = value[1];
                                        String constPrcProtCatgCd = value[2];
                                        String constCoaAcctCd = value[3];

                                        if (hasValue(constPrcProtCatgCd) && hasValue(crDrSubRsnCd)) {
                                            prcProtCatgCd = cache.get(cacheKey);
                                            if (!hasValue(prcProtCatgCd)) {
                                                CR_DR_SUB_RSNTMsg crDrSubRsnMsg = findCrDrSubRsnByKey(glblCmpyCd, crDrSubRsnCd);
                                                prcProtCatgCd = crDrSubRsnMsg.prcProtCatgCd.getValue();
                                                cache.put(cacheKey, prcProtCatgCd);
                                            }
                                        }

                                        if (checkValue(trxCd, constTrxCd) && checkValue(trxRsnCd, constTrxRsnCd) && checkValue(prcProtCatgCd, constPrcProtCatgCd)) {
                                            setValue(invPrmoInfoTMsg.coaAcctCd, constCoaAcctCd);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private boolean getFromMasterForINVData(NWZC035002PMsg orderDataPMsg, INVBean invBean, S21LRUMap<String, Object> s21LRUMap) {

        boolean isSuccess = true;

        S21ApiMessageMap msgMap = invBean.getMsgMap();
        INVTMsg invTMsg = invBean.getInvTMsg();
        String cacheKey;
        Map resultMap;

        cacheKey = createCacheKey("queryBILL_TO_and_REM_TO", orderDataPMsg.payerCustCd.getValue());
        resultMap = (Map) s21LRUMap.get(cacheKey);
        if (resultMap == null) {
            S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryBILL_TO_and_REM_TO", orderDataPMsg, invTMsg);
            if (ssmRes.getQueryResultCount() == 0 && N.equals(orderDataPMsg.exptFlg.getValue())) {
                msgMap.addXxMsgId(NWZM0259E);
                isSuccess = false;
            }
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("remitToLocNm", invTMsg.remitToLocNm.getValue());
            tempMap.put("remitToAddlLocNm", invTMsg.remitToAddlLocNm.getValue());
            tempMap.put("remId", invTMsg.remId.getValue());
            s21LRUMap.put(cacheKey, tempMap);

        } else {
            invTMsg.remitToLocNm.setValue((String) resultMap.get("remitToLocNm"));
            invTMsg.remitToAddlLocNm.setValue((String) resultMap.get("remitToAddlLocNm"));
            invTMsg.remId.setValue((String) resultMap.get("remId"));
        }

        cacheKey = createCacheKey("queryINV_RCPNT_FOR_DUNS_NUM", orderDataPMsg.invRcpntCustCd.getValue());

        String dunsNum = (String) s21LRUMap.get(cacheKey);
        if (dunsNum == null) {
            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd", orderDataPMsg.glblCmpyCd.getValue());
            mapParam.put("invRcpntCustCd", orderDataPMsg.invRcpntCustCd.getValue());
            mapParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            // Even if the search result doesn't exist, it doesn't make it to the error.
            S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryINV_RCPNT_FOR_DUNS_NUM", mapParam, invTMsg);

            if (!ssmRes.isCodeNormal()) {
                mapParam.put("rgtnStsCd", RGTN_STS.TERMINATED);
                ssmEzdClient.queryEZDMsg("queryINV_RCPNT_FOR_DUNS_NUM", mapParam, invTMsg);
            }

            s21LRUMap.put(cacheKey, invTMsg.dunsNum.getValue());
        } else {
            invTMsg.dunsNum.setValue(dunsNum);
        }

        cacheKey = createCacheKey("querySELL_TO_and_CNTY", orderDataPMsg.invRcpntCustCd.getValue());

        resultMap = (Map) s21LRUMap.get(cacheKey);
        if (resultMap == null) {
            S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("querySELL_TO_and_CNTY", orderDataPMsg, invTMsg);
            if (ssmRes.getQueryResultCount() == 0) {
                msgMap.addXxMsgId(NWZM0260E);
                isSuccess = false;
            }
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("sellToLocNm", invTMsg.sellToLocNm.getValue());
            tempMap.put("sellToAddlLocNm", invTMsg.sellToAddlLocNm.getValue());
            tempMap.put("sellToFirstLineAddr", invTMsg.sellToFirstLineAddr.getValue());
            tempMap.put("sellToScdLineAddr", invTMsg.sellToScdLineAddr.getValue());
            tempMap.put("sellToThirdLineAddr", invTMsg.sellToThirdLineAddr.getValue());
            tempMap.put("sellToFrthLineAddr", invTMsg.sellToFrthLineAddr.getValue());
            tempMap.put("sellToCtyAddr", invTMsg.sellToCtyAddr.getValue());
            tempMap.put("sellToProvNm", invTMsg.sellToProvNm.getValue());
            tempMap.put("sellToStCd", invTMsg.sellToStCd.getValue());
            tempMap.put("sellToPostCd", invTMsg.sellToPostCd.getValue());
            tempMap.put("sellToCtryCd", invTMsg.sellToCtryCd.getValue());
            tempMap.put("sellToFirstRefCmntTxt", invTMsg.sellToFirstRefCmntTxt.getValue());
            tempMap.put("sellToScdRefCmntTxt", invTMsg.sellToScdRefCmntTxt.getValue());
            tempMap.put("sellToCntyNm", invTMsg.sellToCntyNm.getValue());
            s21LRUMap.put(cacheKey, tempMap);

        } else {
            invTMsg.sellToLocNm.setValue((String) resultMap.get("sellToLocNm"));
            invTMsg.sellToAddlLocNm.setValue((String) resultMap.get("sellToAddlLocNm"));
            invTMsg.sellToFirstLineAddr.setValue((String) resultMap.get("sellToFirstLineAddr"));
            invTMsg.sellToScdLineAddr.setValue((String) resultMap.get("sellToScdLineAddr"));
            invTMsg.sellToThirdLineAddr.setValue((String) resultMap.get("sellToThirdLineAddr"));
            invTMsg.sellToFrthLineAddr.setValue((String) resultMap.get("sellToFrthLineAddr"));
            invTMsg.sellToCtyAddr.setValue((String) resultMap.get("sellToCtyAddr"));
            invTMsg.sellToProvNm.setValue((String) resultMap.get("sellToProvNm"));
            invTMsg.sellToStCd.setValue((String) resultMap.get("sellToStCd"));
            invTMsg.sellToPostCd.setValue((String) resultMap.get("sellToPostCd"));
            invTMsg.sellToCtryCd.setValue((String) resultMap.get("sellToCtryCd"));
            invTMsg.sellToFirstRefCmntTxt.setValue((String) resultMap.get("sellToFirstRefCmntTxt"));
            invTMsg.sellToScdRefCmntTxt.setValue((String) resultMap.get("sellToScdRefCmntTxt"));
            invTMsg.sellToCntyNm.setValue((String) resultMap.get("sellToCntyNm"));
        }

        cacheKey = createCacheKey("queryINV_RCPNT_and_CNTY", orderDataPMsg.invRcpntCustCd.getValue());

        resultMap = (Map) s21LRUMap.get(cacheKey);
        if (resultMap == null) {
            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd", orderDataPMsg.glblCmpyCd.getValue());
            mapParam.put("invRcpntCustCd", orderDataPMsg.invRcpntCustCd.getValue());
            mapParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryINV_RCPNT_and_CNTY", mapParam, invTMsg);
            if (ssmRes.getQueryResultCount() == 0) {

                mapParam.put("rgtnStsCd", RGTN_STS.TERMINATED);
                ssmRes = ssmEzdClient.queryEZDMsg("queryINV_RCPNT_and_CNTY", mapParam, invTMsg);
            }
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("rcpntLocNm", invTMsg.rcpntLocNm.getValue());
            tempMap.put("rcpntAddlLocNm", invTMsg.rcpntAddlLocNm.getValue());
            tempMap.put("rcpntFirstLineAddr", invTMsg.rcpntFirstLineAddr.getValue());
            tempMap.put("rcpntScdLineAddr", invTMsg.rcpntScdLineAddr.getValue());
            tempMap.put("rcpntThirdLineAddr", invTMsg.rcpntThirdLineAddr.getValue());
            tempMap.put("rcpntFrthLineAddr", invTMsg.rcpntFrthLineAddr.getValue());
            tempMap.put("rcpntCtyAddr", invTMsg.rcpntCtyAddr.getValue());
            tempMap.put("rcpntProvNm", invTMsg.rcpntProvNm.getValue());
            tempMap.put("rcpntStCd", invTMsg.rcpntStCd.getValue());
            tempMap.put("rcpntPostCd", invTMsg.rcpntPostCd.getValue());
            tempMap.put("rcpntCtryCd", invTMsg.rcpntCtryCd.getValue());
            tempMap.put("rcpntCntyNm", invTMsg.rcpntCntyNm.getValue());
            s21LRUMap.put(cacheKey, tempMap);

        } else {
            invTMsg.rcpntLocNm.setValue((String) resultMap.get("rcpntLocNm"));
            invTMsg.rcpntAddlLocNm.setValue((String) resultMap.get("rcpntAddlLocNm"));
            invTMsg.rcpntFirstLineAddr.setValue((String) resultMap.get("rcpntFirstLineAddr"));
            invTMsg.rcpntScdLineAddr.setValue((String) resultMap.get("rcpntScdLineAddr"));
            invTMsg.rcpntThirdLineAddr.setValue((String) resultMap.get("rcpntThirdLineAddr"));
            invTMsg.rcpntFrthLineAddr.setValue((String) resultMap.get("rcpntFrthLineAddr"));
            invTMsg.rcpntCtyAddr.setValue((String) resultMap.get("rcpntCtyAddr"));
            invTMsg.rcpntProvNm.setValue((String) resultMap.get("rcpntProvNm"));
            invTMsg.rcpntStCd.setValue((String) resultMap.get("rcpntStCd"));
            invTMsg.rcpntPostCd.setValue((String) resultMap.get("rcpntPostCd"));
            invTMsg.rcpntCtryCd.setValue((String) resultMap.get("rcpntCtryCd"));
            invTMsg.rcpntCntyNm.setValue((String) resultMap.get("rcpntCntyNm"));
        }

        // Because logic is complex, the result is not cached.
        Map mapRes = (Map) ssmBatchClient.queryObject("queryPMT_TERM", orderDataPMsg);
        if (mapRes == null) {
            msgMap.addXxMsgId(NWZM0262E);
            isSuccess = false;
        } else {
            setValue(invTMsg.pmtTermNm, (String) mapRes.get("PMT_TERM_NM"));

            if (!getNETDueDt(invBean, s21LRUMap)) {
                isSuccess = false;
            }
        
//QC#26121 del Start
//            if (!INV_TP.CREDIT_MEMO.equals(orderDataPMsg.invTpCd.getValue())) {
//                if (hasValue(orderDataPMsg.cashDiscTermCd)) {
//                    getFromMasterForINV_CASH_DISC_TERM(orderDataPMsg, invBean);
//                }
//            }
//QC#26121 del End

        }

        if (hasValue(orderDataPMsg.pmtTermCashDiscCd.getValue())) {
            PMT_TERM_CASH_DISCTMsg pmtTermCashTMsg = new PMT_TERM_CASH_DISCTMsg();
            pmtTermCashTMsg.glblCmpyCd.setValue(orderDataPMsg.glblCmpyCd.getValue());
            pmtTermCashTMsg.pmtTermCashDiscCd.setValue(orderDataPMsg.pmtTermCashDiscCd.getValue());

            pmtTermCashTMsg = (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(pmtTermCashTMsg);

            if (pmtTermCashTMsg == null) {
                msgMap.addXxMsgId(NWZM0924E);
                isSuccess = false;
            } else {
                invTMsg.pmtTermCashDiscDescTxt.setValue(pmtTermCashTMsg.pmtTermCashDiscDescTxt.getValue());
            }
        }

        if (hasValue(invTMsg.crAnlstPsnCd)) {
            cacheKey = createCacheKey("queryS21_PSN", invTMsg.crAnlstPsnCd.getValue());
            String crAnlstPsnNm = (String) s21LRUMap.get(cacheKey);
            if (crAnlstPsnNm == null) {
                mapRes = (Map) ssmBatchClient.queryObject("queryS21_PSN", invTMsg);
                if (mapRes == null) {
                    msgMap.addXxMsgId(NWZM0355E);
                    isSuccess = false;
                } else {
                    String firstNm = nvl((String) mapRes.get("PSN_FIRST_NM"));
                    String midNm = (String) mapRes.get("PSN_MID_NM");
                    String lastNm = nvl((String) mapRes.get("PSN_LAST_NM"));

                    if (hasValue(midNm)) {
                        invTMsg.crAnlstPsnNm.setValue(firstNm + " " + midNm.charAt(0) + "." + " " + lastNm);
                    } else {
                        invTMsg.crAnlstPsnNm.setValue(firstNm + " " + lastNm);
                    }
                }
                s21LRUMap.put(cacheKey, invTMsg.crAnlstPsnNm.getValue());
            } else {
                invTMsg.crAnlstPsnNm.setValue(crAnlstPsnNm);
            }
        }

        // get s21org by salesrep
        if (hasValue(invTMsg.slsRepTocCd)) {
            S21_ORGTMsg s21OrgTmsg = new S21_ORGTMsg();
            setValue(s21OrgTmsg.glblCmpyCd, glblCmpyCd);
            setValue(s21OrgTmsg.tocCd,      invTMsg.slsRepTocCd.getValue());
            s21OrgTmsg = (S21_ORGTMsg) S21CacheTBLAccessor.findByKey(s21OrgTmsg);

            if (s21OrgTmsg == null) {
                msgMap.addXxMsgId(NWZM0263E);
                isSuccess = false;
            } else {
                setValue(invTMsg.slsRepFirstOrgCd,  s21OrgTmsg.firstOrgCd.getValue());
                setValue(invTMsg.slsRepFirstOrgNm,  s21OrgTmsg.firstOrgNm.getValue());
                setValue(invTMsg.slsRepScdOrgCd,    s21OrgTmsg.scdOrgCd.getValue());
                setValue(invTMsg.slsRepScdOrgNm,    s21OrgTmsg.scdOrgNm.getValue());
                setValue(invTMsg.slsRepThirdOrgCd,  s21OrgTmsg.thirdOrgCd.getValue());
                setValue(invTMsg.slsRepThirdOrgNm,  s21OrgTmsg.thirdOrgNm.getValue());
                setValue(invTMsg.slsRepFrthOrgCd,   s21OrgTmsg.frthOrgCd.getValue());
                setValue(invTMsg.slsRepFrthOrgNm,   s21OrgTmsg.frthOrgNm.getValue());
                setValue(invTMsg.slsRepFifthOrgCd,  s21OrgTmsg.fifthOrgCd.getValue());
                setValue(invTMsg.slsRepFifthOrgNm,  s21OrgTmsg.fifthOrgNm.getValue());
                setValue(invTMsg.slsRepSixthOrgCd,  s21OrgTmsg.sixthOrgCd.getValue());
                setValue(invTMsg.slsRepSixthOrgNm,  s21OrgTmsg.sixthOrgNm.getValue());
                setValue(invTMsg.slsRepSvnthOrgCd,  s21OrgTmsg.svnthOrgCd.getValue());
                setValue(invTMsg.slsRepSvnthOrgNm,  s21OrgTmsg.svnthOrgNm.getValue());
                setValue(invTMsg.slsRepEighthOrgCd, s21OrgTmsg.eighthOrgCd.getValue());
                setValue(invTMsg.slsRepEighthOrgNm, s21OrgTmsg.eighthOrgNm.getValue());
                setValue(invTMsg.slsRepNinthOrgCd,  s21OrgTmsg.ninthOrgCd.getValue());
                setValue(invTMsg.slsRepNinthOrgNm,  s21OrgTmsg.ninthOrgNm.getValue());
                setValue(invTMsg.slsRepTenthOrgCd,  s21OrgTmsg.tenthOrgCd.getValue());
                setValue(invTMsg.slsRepTenthOrgNm,  s21OrgTmsg.tenthOrgNm.getValue());
                setValue(invTMsg.slsRepElvthOrgCd,  s21OrgTmsg.elvthOrgCd.getValue());
                setValue(invTMsg.slsRepElvthOrgNm,  s21OrgTmsg.elvthOrgNm.getValue());
                setValue(invTMsg.slsRepTocNm,       s21OrgTmsg.tocNm.getValue());
            }

        }

        // get name
        // SOLD_TO_CUST_LOC_NM
        Map<String, String> mapParam;

        String billToCustCd = invTMsg.soldToCustLocCd.getValue();
        cacheKey = createCacheKey("getSoldToCustLocNm", billToCustCd);
        String custName = (String) s21LRUMap.get(cacheKey);
        if (custName == null) {
            mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd",   orderDataPMsg.glblCmpyCd.getValue());
            mapParam.put("billToCustCd", billToCustCd);
            custName = (String) ssmBatchClient.queryObject("getSoldToCustLocNm", mapParam);
            if (custName == null) {
//                msgMap.addXxMsgId(NWZM0608E);
//                isSuccess = false;
            } else {
                s21LRUMap.put(cacheKey, custName);
            }
        }
        setValue(invTMsg.soldToCustLocNm, custName);

        //BILL_TO_CUST_ACCT_NM
        String billToCustAcctCd = invTMsg.billToCustAcctCd.getValue();
        cacheKey = createCacheKey("getCustAcctNm", billToCustAcctCd);
        custName = (String) s21LRUMap.get(cacheKey);
        if (custName == null) {
            mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd", orderDataPMsg.glblCmpyCd.getValue());
            mapParam.put("dsAcctNum", billToCustAcctCd);

            custName = (String) ssmBatchClient.queryObject("getCustAcctNm", mapParam);
            if (custName == null) {
                msgMap.addXxMsgId(NWZM1509E);
                isSuccess = false;
            } else {
                s21LRUMap.put(cacheKey, custName);
            }
        }
        setValue(invTMsg.billToCustAcctNm, custName);

        return isSuccess;
    }

    private boolean setInvSendStatus(INVBean invBean, String invPrintStsCd) {

        INVTMsg invTMsg = invBean.getInvTMsg();
        setValue(invTMsg.invPrintStsCd, invPrintStsCd);
        setValue(invTMsg.invMlSendStsCd,  STS_CD_PROCESSED);
        setValue(invTMsg.invEdiSendStsCd, STS_CD_PROCESSED);
        setValue(invTMsg.invFaxSendStsCd, STS_CD_PROCESSED);
        setValue(invTMsg.invEmlSendStsCd, STS_CD_PROCESSED);

        return true;
    }

    private void setInitInvSendStatus(INVTMsg invTMsg) {
        invTMsg.invPrintStsCd.setValue(STS_CD_ZERO);
        invTMsg.invMlSendStsCd.setValue(STS_CD_ZERO);
        invTMsg.invEdiSendStsCd.setValue(STS_CD_ZERO);
        invTMsg.invFaxSendStsCd.setValue(STS_CD_ZERO);
        invTMsg.invEmlSendStsCd.setValue(STS_CD_ZERO);
    }

    private boolean getFuncCcyCd(S21ApiMessageMap msgMap) {
        NWZC035001PMsg pMsg = (NWZC035001PMsg) msgMap.getPmsg();

        // if cache doesn't have GLBL_CMPY infomation, find
        // [GLBL_CMPY] table it.
        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        glblTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());

        glblTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblTMsg);
        if (glblTMsg == null) {
            msgMap.addXxMsgId(NWZM0257E);
            return false;
        }

        this.funcCcyCd = glblTMsg.stdCcyCd.getValue();
        return true;
    }

    private void getINV_BOLData(List<OrderDataBean> orderDataBeanList, List<INV_BOLBean> allInvBolBeanList) {

        Collections.sort(orderDataBeanList, new INV_BOLDataComparator());

        Map<String, String> invBolKeyMap = new HashMap<String, String>();

        List<INV_BOLBean> invBolBeanList = new ArrayList<INV_BOLBean>();

        INV_BOLBean invBolBean = null;
        for (OrderDataBean orderDataBean : orderDataBeanList) {

            NWZC035002PMsg orderDataPMsg = orderDataBean.getNWZC035002PMsg();

            String pmtTermCd = orderDataPMsg.pmtTermCd.getValue();
            String cashDiscTermCd = orderDataPMsg.cashDiscTermCd.getValue();
            String dealCcyCd = orderDataPMsg.dealCcyCd.getValue();
            String soNum = orderDataPMsg.soNum.getValue();
            String bolNum = orderDataPMsg.bolNum.getValue();
            String proNum = orderDataPMsg.proNum.getValue();

            String billToCustAcctCd     = orderDataPMsg.billToCustAcctCd.getValue();
            String billToCtacPsnFirstNm = orderDataPMsg.billToCtacPsnFirstNm.getValue();
            String billToCtacPsnMidNm   = orderDataPMsg.billToCtacPsnMidNm.getValue();
            String billToCtacPsnLastNm  = orderDataPMsg.billToCtacPsnLastNm.getValue();
            String origInvNum           = orderDataPMsg.origInvNum.getValue();
            //opt key
            String shipToCustAcctCd     = orderDataPMsg.shipToCustAcctCd_G.getValue();
            String shipToCustLocCd      = orderDataPMsg.shipToCustLocCd_G.getValue();
            BigDecimal shipToCtacPsnPk  = orderDataPMsg.ctacPsnPk_G.getValue();
            //bol key
            String invtyLocCd           = orderDataPMsg.shipFromInvtyLocCd.getValue();
            String invRcpntCustCd = orderDataPMsg.invRcpntCustCd.getValue();

            // 2019/06/03 QC#50654 Add Start
            String origInvtyLocCd       = orderDataPMsg.origInvtyLocCd.getValue();
            // 2019/06/03 QC#50654 Add End

            if (!(pmtTermCd.equals(invBolKeyMap.get("pmtTermCd"))
                    && cashDiscTermCd.equals(invBolKeyMap.get("cashDiscTermCd"))
                    && dealCcyCd.equals(invBolKeyMap.get("dealCcyCd"))
                    && soNum.equals(invBolKeyMap.get("soNum"))
                    && bolNum.equals(invBolKeyMap.get("bolNum"))
                    && proNum.equals(invBolKeyMap.get("proNum"))
                    && invRcpntCustCd.equals(invBolKeyMap.get("invRcpntCustCd"))
                    && billToCustAcctCd.equals(invBolKeyMap.get("billToCustAcctCd"))
                    && billToCtacPsnFirstNm.equals(invBolKeyMap.get("billToCtacPsnFirstNm"))
                    && billToCtacPsnMidNm.equals(invBolKeyMap.get("billToCtacPsnMidNm"))
                    && billToCtacPsnLastNm.equals(invBolKeyMap.get("billToCtacPsnLastNm"))
                    && origInvNum.equals(invBolKeyMap.get("origInvNum"))
                    && (shipToCustAcctCd == null
                     || shipToCustAcctCd != null && shipToCustAcctCd.equals(invBolKeyMap.get("shipToCustAcctCd")))
                    && (shipToCustLocCd == null
                     || shipToCustLocCd != null && shipToCustLocCd.equals(invBolKeyMap.get("shipToCustLocCd")))
                    && (shipToCtacPsnPk == null
                     || shipToCtacPsnPk != null && shipToCtacPsnPk.toString().equals(invBolKeyMap.get("shipToCtacPsnPk")))
                    && (!hasValue(invtyLocCd)
                     || hasValue(invtyLocCd) && invtyLocCd.equals(invBolKeyMap.get("invtyLocCd")))
            )) {
                invBolBean = new INV_BOLBean(orderDataBean.getMsgMap(), orderDataPMsg);

                invBolBeanList.add(invBolBean);
            // 2019/06/03 QC#50654 Add Start
            } else if ((hasValue(invtyLocCd) && "CR".equals(invtyLocCd)
                     && (hasValue(origInvtyLocCd) && hasValue(invBolKeyMap.get("origInvtyLocCd")) && !origInvtyLocCd.equals(invBolKeyMap.get("origInvtyLocCd"))))
                    ) {
                invBolBean = new INV_BOLBean(orderDataBean.getMsgMap(), orderDataPMsg);

                invBolBeanList.add(invBolBean);
            // 2019/06/03 QC#50654 Add End
            // START 2022/07/11 A.Cullano [QC#60272, ADD]
            } else if (hasValue(invtyLocCd) && "CR".equals(invtyLocCd) && !hasValue(origInvtyLocCd)) {
                invBolBean = new INV_BOLBean(orderDataBean.getMsgMap(), orderDataPMsg);
            }
            // END 2022/07/11 A.Cullano [QC#60272, ADD]

            INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();
            if (hasValue(orderDataPMsg.carrCd)) {
                invBolTMsg.carrCd.setValue(orderDataPMsg.carrCd.getValue());
            }
            if (hasValue(orderDataPMsg.carrNm)) {
                invBolTMsg.carrNm.setValue(orderDataPMsg.carrNm.getValue());
            }
            if (hasValue(orderDataPMsg.shipFromInvtyLocCd)) {
                // 2017/10/18 QC#21730 Mod Start
                String shipFromInvtyLocCd = orderDataPMsg.shipFromInvtyLocCd.getValue();
                invBolTMsg.shipFromInvtyLocCd.setValue(shipFromInvtyLocCd);
                if (!"CR".equals(shipFromInvtyLocCd)) {
                    invBolTMsg.origInvtyLocCd.setValue(shipFromInvtyLocCd);
                } else {
                    invBolTMsg.origInvtyLocCd.setValue(orderDataPMsg.origInvtyLocCd.getValue());
                }
                // 2017/10/18 QC#21730 Mod End
            }
            if (hasValue(orderDataPMsg.shipDt)) {
                invBolTMsg.shipDt.setValue(orderDataPMsg.shipDt.getValue());
            }
            if (hasValue(orderDataPMsg.arvDt)) {
                invBolTMsg.arvDt.setValue(orderDataPMsg.arvDt.getValue());
            }
            if (hasValue(orderDataPMsg.shpgSvcLvlCd)) {
                invBolTMsg.shpgSvcLvlCd.setValue(orderDataPMsg.shpgSvcLvlCd.getValue());
            }
            if (hasValue(orderDataPMsg.frtChrgMethCd)) {
                invBolTMsg.frtChrgMethCd.setValue(orderDataPMsg.frtChrgMethCd.getValue());
            }
            if (hasValue(orderDataPMsg.frtChrgToCd)) {
                invBolTMsg.frtChrgToCd.setValue(orderDataPMsg.frtChrgToCd.getValue());
            }

            invBolKeyMap.put("pmtTermCd", pmtTermCd);
            invBolKeyMap.put("cashDiscTermCd", cashDiscTermCd);
            invBolKeyMap.put("dealCcyCd", dealCcyCd);
            invBolKeyMap.put("soNum", soNum);
            invBolKeyMap.put("bolNum", bolNum);
            invBolKeyMap.put("proNum", proNum);
            invBolKeyMap.put("invRcpntCustCd", invRcpntCustCd);
            invBolKeyMap.put("billToCustAcctCd", billToCustAcctCd);
            invBolKeyMap.put("billToCtacPsnFirstNm", billToCtacPsnFirstNm);
            invBolKeyMap.put("billToCtacPsnMidNm", billToCtacPsnMidNm);
            invBolKeyMap.put("billToCtacPsnLastNm", billToCtacPsnLastNm);
            invBolKeyMap.put("origInvNum", origInvNum);
            if (shipToCustAcctCd != null) {
                invBolKeyMap.put("shipToCustAcctCd", shipToCustAcctCd);
            }
            if (shipToCustLocCd != null) {
                invBolKeyMap.put("shipToCustLocCd", shipToCustLocCd);
            }
            if (shipToCtacPsnPk != null) {
                invBolKeyMap.put("shipToCtacPsnPk", shipToCtacPsnPk.toString());
            }
            invBolKeyMap.put("invtyLocCd", invtyLocCd);
            // 2019/06/03 QC#50654 Add Start
            invBolKeyMap.put("origInvtyLocCd", invBolTMsg.origInvtyLocCd.getValue());
            // 2019/06/03 QC#50654 Add End
        }

        //skip  intangble INV_BOL
        if (invBolBeanList.size() > 1) {
            String bolKey;
            for (INV_BOLBean bean : invBolBeanList) {
                INV_BOLTMsg invBolTMsg = bean.getInvBolTMsg();

                bolKey = bean.getInvBolKey();

                boolean skipFlg = false;
                if (!hasValue(invBolTMsg.shipFromInvtyLocCd)) {
                    for (INV_BOLBean bean2 : invBolBeanList) {
                        INV_BOLTMsg tmsg = bean2.getInvBolTMsg();

                        String key = bean2.getInvBolKey();

                        if (bolKey.equals(key)) {
                            if (hasValue(tmsg.shipFromInvtyLocCd)) {
                                skipFlg = true;
                                break;
                            }
                        }
                    }
                }
                if (!skipFlg) {
                    allInvBolBeanList.add(bean);
                }
            }
        } else {
            allInvBolBeanList.addAll(invBolBeanList);
        }
    }

    private void getINV_CASH_DISC_TERMData(INVTMsg invTMsg, INVBean invBean, String invNum) {
        INV_CASH_DISC_TERMTMsg conditionCDTMsg = new INV_CASH_DISC_TERMTMsg();
        conditionCDTMsg.setSQLID("001");
        conditionCDTMsg.setConditionValue("glblCmpyCd01", invTMsg.glblCmpyCd.getValue());
        conditionCDTMsg.setConditionValue("invNum01", invNum);
        INV_CASH_DISC_TERMTMsgArray resultArray = (INV_CASH_DISC_TERMTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(conditionCDTMsg);
        // Even if the search result doesn't exist, it doesn't make it to the error.
        for (int i = 0; i < resultArray.length(); i++) {
            INV_CASH_DISC_TERMTMsg invCashDiscTermTMsg = resultArray.no(i);
            invBean.addInvCashDiscTerm(invCashDiscTermTMsg);
        }
    }

// 2019/06/25 QC#50780 Mod Start
//    private boolean getINV_LINEData(List<OrderDataBean> orderDataBeanList, List<INV_LINEBean> allInvLineBeanList) {
    private boolean getINV_LINEData(List<OrderDataBean> orderDataBeanList, List<INV_LINEBean> allInvLineBeanList, List<INVBean> invBeanList) {
// 2019/06/25 QC#50780 Mod End

        boolean isSuccess = true;

        for (OrderDataBean orderDataBean : orderDataBeanList) {

            NWZC035002PMsg orderDataPMsg = orderDataBean.getNWZC035002PMsg();

            INV_LINEBean invLineBean = new INV_LINEBean(orderDataBean.getMsgMap(), orderDataPMsg);
            // WDS Add Start
            invLineBean.setInvRcpntCustCd(orderDataPMsg.invRcpntCustCd.getValue());
            // WDS Add End
            // 2019/06/03 QC#50654 Add Start
            invLineBean.setOrigInvtyLocCd(orderDataPMsg.origInvtyLocCd.getValue());
            // 2019/06/03 QC#50654 Add End

            // 2019/06/25 QC#50780 Add Start
            if (CPO_SRC_TP.REBILL.equals(invBeanList.get(0).getCpoSrcTpCd()) && ZYPCommonFunc.hasValue(invLineBean.getDsContrNum())) {
                setValue(invLineBean.getInvLineTMsg().dsContrDtlPk, getDsContrDtlPK(orderDataPMsg, invLineBean));
            }
            // 2019/06/25 QC#50780 Add End
            allInvLineBeanList.add(invLineBean);
        }

        Collections.sort(allInvLineBeanList, new Comparator<INV_LINEBean>() {
            public int compare(INV_LINEBean arg0, INV_LINEBean arg1) {
                INV_LINETMsg invLineTMsg0 = arg0.getInvLineTMsg();
                INV_LINETMsg invLineTMsg1 = arg1.getInvLineTMsg();
                StringBuilder str0 = new StringBuilder().append(invLineTMsg0.cpoDtlLineNum.getValue()).append(invLineTMsg0.cpoDtlLineSubNum.getValue());
                StringBuilder str1 = new StringBuilder().append(invLineTMsg1.cpoDtlLineNum.getValue()).append(invLineTMsg1.cpoDtlLineSubNum.getValue());
                return str0.toString().compareTo(str1.toString());
            }
        });

        // If Set Merchandise, judging whether I keep the constitution ratio, and it is shipped.
        for (INV_LINEBean invLineBean : allInvLineBeanList) {

            INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

            invLineTMsg.setRatioKeepFlg.setValue(Y);
        }

        return isSuccess;
    }

    // QC#21841 2018/05/21 Add Start
    private boolean getCalcBaseData(List<INV_LINEBean> allInvLineBeanList){
        List<INV_LINEBean> resultList = new ArrayList<INV_LINEBean>();

        boolean isSuccess = true;
        for(INV_LINEBean lineBean : allInvLineBeanList){
            resultList.add(lineBean);
            List<Map<String, Object>> ssmRsltList = getCalcBaseForCharges(lineBean);
            for(Map<String, Object> ssmRslt : ssmRsltList){
                INV_LINEBean addLineBean = new INV_LINEBean();
                addLineBean.copyOrignInvLine(lineBean);
                INV_LINETMsg invLineTMsg = addLineBean.getInvLineTMsg();

                BigDecimal lineAmt = (BigDecimal) ssmRslt.get("CALC_PRC_AMT_RATE");
                BigDecimal qty = invLineTMsg.shipQty.getValue();
                BigDecimal unitPrcAmt = (BigDecimal) ssmRslt.get("UNIT_PRC_AMT");
                String prcDtlGrpCd = (String) ssmRslt.get("PRC_DTL_GRP_CD");

                //create invoice Line
                setValue(invLineTMsg.mdseCd, (String)ssmRslt.get("CHRG_MDSE_CD"));
                if(PRC_DTL_GRP.FREIGHT.equals(prcDtlGrpCd)){
                    addLineBean.setInvLineCtgCd(INV_LINE_CATG.FREIGHT);
                    setValue(invLineTMsg.invLineCatgCd, INV_LINE_CATG.FREIGHT);
                } else{
                    addLineBean.setInvLineCtgCd(INV_LINE_CATG.CHARGE);
                    setValue(invLineTMsg.invLineCatgCd, INV_LINE_CATG.CHARGE);
                }
                setValue(invLineTMsg.baseCmptFlg, ZYPConstant.FLG_OFF_N);
                setValue(invLineTMsg.shipQty, qty);
                setValue(invLineTMsg.ordQty, qty);

                setValue(invLineTMsg.origOrCustMdseCd, invLineTMsg.mdseCd);
                invLineTMsg.shpgPlnNum.clear();

                //get trx,trxRsn
                getSvcTrxCd(addLineBean);


                INV_PRMO_INFOTMsg invPrmoInfoTMsg = new INV_PRMO_INFOTMsg();

                setValue(invPrmoInfoTMsg.glblCmpyCd, glblCmpyCd);

                setValue(invPrmoInfoTMsg.dealUnitPrcAmt, unitPrcAmt);
                setValue(invPrmoInfoTMsg.dealLastNetUnitPrcAmt, unitPrcAmt);
                setValue(invPrmoInfoTMsg.dealNetAmt, lineAmt);
                setValue(invPrmoInfoTMsg.dealDiscAmt, ZERO);
                setValue(invPrmoInfoTMsg.dealPrmoNetUnitPrcAmt, unitPrcAmt);
                setValue(invPrmoInfoTMsg.dealPerUnitFixAmt, ZERO);
                setValue(invPrmoInfoTMsg.dealSlsPctNum, ZERO);
                setValue(invPrmoInfoTMsg.funcPerUnitFixAmt, ZERO);
                setValue(invPrmoInfoTMsg.funcUnitPrcAmt, unitPrcAmt);
                setValue(invPrmoInfoTMsg.funcLastNetUnitPrcAmt, unitPrcAmt);
                setValue(invPrmoInfoTMsg.funcNetAmt, lineAmt);
                setValue(invPrmoInfoTMsg.funcDiscAmt, ZERO);
                setValue(invPrmoInfoTMsg.funcPrmoNetUnitPrcAmt, unitPrcAmt);
                invPrmoInfoTMsg.invPrmoInfoSqNum.setValue(FIXED_INV_PRMO_INFO_SEQ_NUM);

                setValue(invPrmoInfoTMsg.prmoQty, qty);
                // 2018/09/11 QC#28113 Add Start
                setCorrectUom(invLineTMsg);
                // 2018/09/11 QC#28113 Add End
                addLineBean.getInvPrmoInfoList().add(invPrmoInfoTMsg);

                resultList.add(addLineBean);
            }
        }

        allInvLineBeanList.clear();
        allInvLineBeanList.addAll(resultList);

        return isSuccess;
    }
    // QC#21841 2018/05/21 Add End

    @SuppressWarnings("unchecked")
    private boolean getINVData(List<OrderDataBean> orderDataBeanList, List<INVBean> invBeanList) {

        boolean isSuccess = true;

        Collections.sort(orderDataBeanList, new INVDataComparator());

        Map<String, String> invKeyMap = new HashMap<String, String>();

        S21LRUMap<String, Object> s21LRUMap = new S21LRUMap<String, Object>();

        String invPrintStsCd = STS_CD_ONE;

        for (OrderDataBean orderDataBean : orderDataBeanList) {

            S21ApiMessageMap msgMap = orderDataBean.getMsgMap();
            NWZC035001PMsg paramPMsg = (NWZC035001PMsg) msgMap.getPmsg();
            NWZC035002PMsg orderDataPMsg = orderDataBean.getNWZC035002PMsg();

            String pmtTermCd = orderDataPMsg.pmtTermCd.getValue();
            String cashDiscTermCd = orderDataPMsg.cashDiscTermCd.getValue();
            String dealCcyCd = orderDataPMsg.dealCcyCd.getValue();
            String billToCustAcctCd     = orderDataPMsg.billToCustAcctCd.getValue();
            String billToCtacPsnFirstNm = orderDataPMsg.billToCtacPsnFirstNm.getValue();
            String billToCtacPsnMidNm   = orderDataPMsg.billToCtacPsnMidNm.getValue();
            String billToCtacPsnLastNm  = orderDataPMsg.billToCtacPsnLastNm.getValue();
            String origInvNum           = orderDataPMsg.origInvNum.getValue();
            //opt key
            String shipToCustAcctCd     = orderDataPMsg.shipToCustAcctCd_G.getValue();
            String shipToCustLocCd      = orderDataPMsg.shipToCustLocCd_G.getValue();
            BigDecimal shipToCtacPsnPk  = orderDataPMsg.ctacPsnPk_G.getValue();

            String invRcpntCustCd = orderDataPMsg.invRcpntCustCd.getValue();

            if (pmtTermCd.equals(invKeyMap.get("pmtTermCd")) && cashDiscTermCd.equals(invKeyMap.get("cashDiscTermCd")) && dealCcyCd.equals(invKeyMap.get("dealCcyCd"))) {

                if (invRcpntCustCd.equals(invKeyMap.get("invRcpntCustCd"))) {

                    if (billToCustAcctCd.equals(invKeyMap.get("billToCustAcctCd"))
                        && billToCtacPsnFirstNm.equals(invKeyMap.get("billToCtacPsnFirstNm"))
                        && billToCtacPsnMidNm.equals(invKeyMap.get("billToCtacPsnMidNm"))
                        && billToCtacPsnLastNm.equals(invKeyMap.get("billToCtacPsnLastNm"))
                        && origInvNum.equals(invKeyMap.get("origInvNum"))
                        && (shipToCustAcctCd == null
                         || shipToCustAcctCd != null && shipToCustAcctCd.equals(invKeyMap.get("shipToCustAcctCd")))
                        && (shipToCustLocCd == null
                         || shipToCustLocCd != null && shipToCustLocCd.equals(invKeyMap.get("shipToCustLocCd")))
                        && (shipToCtacPsnPk == null
                         || shipToCtacPsnPk != null && shipToCtacPsnPk.toString().equals(invKeyMap.get("shipToCtacPsnPk")))
                        ) {

                        continue;
                    }
                }
            }

            invKeyMap.put("pmtTermCd", orderDataPMsg.pmtTermCd.getValue());
            invKeyMap.put("cashDiscTermCd", orderDataPMsg.cashDiscTermCd.getValue());
            invKeyMap.put("dealCcyCd", orderDataPMsg.dealCcyCd.getValue());
            invKeyMap.put("invRcpntCustCd", orderDataPMsg.invRcpntCustCd.getValue());
            invKeyMap.put("billToCustAcctCd",     billToCustAcctCd);
            invKeyMap.put("billToCtacPsnFirstNm", billToCtacPsnFirstNm);
            invKeyMap.put("billToCtacPsnMidNm",   billToCtacPsnMidNm);
            invKeyMap.put("billToCtacPsnLastNm",  billToCtacPsnLastNm);
            invKeyMap.put("origInvNum",           origInvNum);
            if (shipToCustAcctCd != null) {
                invKeyMap.put("shipToCustAcctCd", shipToCustAcctCd);
            }
            if (shipToCustLocCd != null) {
                invKeyMap.put("shipToCustLocCd",  shipToCustLocCd);
            }
            if (shipToCtacPsnPk != null) {
                invKeyMap.put("shipToCtacPsnPk",  shipToCtacPsnPk.toString());
            }

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", orderDataPMsg.glblCmpyCd.getValue());
            ssmParam.put("cpoOrdNum", paramPMsg.cpoOrdNum.getValue());
            ssmParam.put("pmtTermCd", orderDataPMsg.pmtTermCd.getValue());
            if (hasValue(orderDataPMsg.cashDiscTermCd)) {
                ssmParam.put("cashDiscTermCd", orderDataPMsg.cashDiscTermCd.getValue());
            }
            ssmParam.put("dealCcyCd", orderDataPMsg.dealCcyCd.getValue());

            // Mod Start 2017/07/12 CSA Defect#19895
            if (CR_REBIL.CREDIT.equals(orderDataPMsg.crRebilCd.getValue())
             || (CR_REBIL.REBILL.equals(orderDataPMsg.crRebilCd.getValue()) && CR_REBIL_RSN_CATG.INTERNAL.equals(orderDataPMsg.crRebilRsnCatgCd.getValue()))) {
            //if (CR_REBIL.CREDIT.equals(orderDataPMsg.crRebilCd.getValue())) {
            // Mod End   2017/07/12 CSA Defect#19895
                INVTMsg invTMsg = new INVTMsg();
                setValue(invTMsg.glblCmpyCd, glblCmpyCd);
                setValue(invTMsg.invNum, orderDataPMsg.origInvNum);
                invTMsg = (INVTMsg) S21CacheTBLAccessor.findByKey(invTMsg);
                if (invTMsg == null) {
                    msgMap.addXxMsgId(NWZM1513E);
                    isSuccess = false;
                    continue;
                }
                ssmParam.put("invDt", invTMsg.invDt.getValue());
            } else {
                ssmParam.put("invDt", paramPMsg.slsDt.getValue());
            }

            ssmParam.put("invRcpntCd", invRcpntCustCd);
            ssmParam.put("origInvNum", orderDataPMsg.origInvNum.getValue());
            ssmParam.put("billToCtacPsnFirstNm", orderDataPMsg.billToCtacPsnFirstNm.getValue());
            ssmParam.put("billToCtacPsnMidNm", orderDataPMsg.billToCtacPsnMidNm.getValue());
            ssmParam.put("billToCtacPsnLastNm", orderDataPMsg.billToCtacPsnLastNm.getValue());
            ssmParam.put("ctacPsnPk", "");
            ssmParam.put("shipToCustAcctCd", "");
            ssmParam.put("shipToCustLocCd", "");
            if (hasValue(orderDataPMsg.ctacPsnPk_G)) {
                ssmParam.put("ctacPsnPk", orderDataPMsg.ctacPsnPk_G.getValue());
                ssmParam.put("optGrpKeyFlg", Y);
            }
            if (hasValue(orderDataPMsg.shipToCustAcctCd_G)) {
                ssmParam.put("shipToCustAcctCd", orderDataPMsg.shipToCustAcctCd_G.getValue());
                ssmParam.put("optGrpKeyFlg", Y);
            }
            if (hasValue(orderDataPMsg.shipToCustLocCd_G)) {
                ssmParam.put("shipToCustLocCd", orderDataPMsg.shipToCustLocCd_G.getValue());
                ssmParam.put("optGrpKeyFlg", Y);
            }

            List<INVTMsg> invTMsgList = (List<INVTMsg>) ssmBatchClient.queryObjectList("queryMargeINV_DS", ssmParam);

            if (invTMsgList != null) {
                if (invTMsgList.size() == 1) {

                    INVTMsg invTMsg = invTMsgList.get(0);
                    setValue(invTMsg.glblCmpyCd, glblCmpyCd);
//                    invTMsg = (INVTMsg) S21FastTBLAccessor.findByKey(invTMsg);
                    invTMsg = (INVTMsg) EZDTBLAccessor.findByKey(invTMsg);
                    String lastInvTpCd = invTMsg.invTpCd.getValue();    // QC#26121 add
                    INVBean invBean = new INVBean(msgMap, orderDataPMsg, invTMsg, true);
                    invBean.setLastInvTpCd(lastInvTpCd);    // QC#26121 add

                    if (hasValue(orderDataPMsg.cashDiscTermCd)) {
                        getINV_CASH_DISC_TERMData(invTMsg, invBean, invTMsg.invNum.getValue());
                    }

                    // 2018/01/17 S21_NA#23268 Add Start
                    BigDecimal cpoDtlDealNetAmt = invBean.getInvTMsg().invTotDealNetAmt.getValue();
                    // QC#24875 add Start
//                    if (INV_TP.CREDIT_MEMO.equals(invTMsg.invTpCd.getValue())) {
                    if (INV_TP.CREDIT_MEMO.equals(invBean.getLastInvTpCd())) {
                        cpoDtlDealNetAmt = cpoDtlDealNetAmt.negate();
                        // QC#24875 2018/06/08 add Start
                        negateInvAmt(invTMsg);
                        // QC#24875 2018/06/08 add End
                    }
                    // QC#24875 add End
                    for (OrderDataBean beanForCalcAmt : orderDataBeanList) {

                        if (pmtTermCd.equals(invKeyMap.get("pmtTermCd"))
                                && cashDiscTermCd.equals(invKeyMap.get("cashDiscTermCd"))
                                && dealCcyCd.equals(invKeyMap.get("dealCcyCd"))
                                && invRcpntCustCd.equals(invKeyMap.get("invRcpntCustCd"))
                                && billToCustAcctCd.equals(invKeyMap.get("billToCustAcctCd"))
                                && billToCtacPsnFirstNm.equals(invKeyMap.get("billToCtacPsnFirstNm"))
                                && billToCtacPsnMidNm.equals(invKeyMap.get("billToCtacPsnMidNm"))
                                && billToCtacPsnLastNm.equals(invKeyMap.get("billToCtacPsnLastNm"))
                                && origInvNum.equals(invKeyMap.get("origInvNum"))
                                && (shipToCustAcctCd == null 
                                        || (shipToCustAcctCd != null && shipToCustAcctCd.equals(invKeyMap.get("shipToCustAcctCd"))))
                                && (shipToCustLocCd == null
                                        || (shipToCustLocCd != null && shipToCustLocCd.equals(invKeyMap.get("shipToCustLocCd"))))
                                && (shipToCtacPsnPk == null
                                        || (shipToCtacPsnPk != null && shipToCtacPsnPk.toString().equals(invKeyMap.get("shipToCtacPsnPk"))))
                        ) {

                            cpoDtlDealNetAmt = cpoDtlDealNetAmt.add(beanForCalcAmt.getNWZC035002PMsg().cpoDtlDealNetAmt.getValue());
                        }
                    }
                    // QC#26121 2018/06/14 del Start
//                    if (cpoDtlDealNetAmt.compareTo(BigDecimal.ZERO) < 0) {
//                        setValue(invBean.getInvTMsg().invTpCd, INV_TP.CREDIT_MEMO);
//                    } else {
//                        setValue(invBean.getInvTMsg().invTpCd, INV_TP.INVOICE);
//                    }
                    // QC#26121 2018/06/14 del End
                    // 2018/01/17 S21_NA#23268 Add End
                    // START 2023/11/10 [QC#61468, ADD]
                    convDsInvTpFcOnlyContrSupply(invBean);
                    // END   2023/11/10 [QC#61468, ADD]
                    invBeanList.add(invBean);
                    continue;

                } else if (invTMsgList.size() >= 2) {
                    msgMap.addXxMsgId(NWZM0256E);
                    isSuccess = false;
                    continue;
                }
            }

            INVTMsg invTMsg = new INVTMsg();
            invTMsg.invDt.setValue(paramPMsg.slsDt.getValue());
            invTMsg.acctDt.setValue(paramPMsg.slsDt.getValue());
            invTMsg.pmtTermStartDt.setValue(paramPMsg.slsDt.getValue());
            invTMsg.trxSrcTpCd.setValue(TRX_SRC_TP.WHOLE_SALES);
            invTMsg.fnlzInvFlg.setValue(N);
            invTMsg.invTotDealNetAmt.setValue(ZERO);
            invTMsg.invTotFuncNetAmt.setValue(ZERO);
            invTMsg.invTotDealSlsAmt.setValue(ZERO);
            invTMsg.invTotFuncSlsAmt.setValue(ZERO);
            invTMsg.invTotDealDiscAmt.setValue(ZERO);
            invTMsg.invTotFuncDiscAmt.setValue(ZERO);
            invTMsg.invTotDealFrtAmt.setValue(ZERO);
            invTMsg.invTotFuncFrtAmt.setValue(ZERO);
            invTMsg.invTotDealTaxAmt.setValue(ZERO);
            invTMsg.invTotFuncTaxAmt.setValue(ZERO);
            invTMsg.histCratCpltFlg.setValue(N);

            INVBean invBean = new INVBean(msgMap, orderDataPMsg, invTMsg, false);

            INVTMsg    origInvTMsg   = invBean.getOrigInvTMsg();
            String crRebilCd = orderDataPMsg.crRebilCd.getValue();

//            if (origInvTMsg != null && (CR_REBIL.CREDIT.equals(crRebilCd) || CR_REBIL_RSN_CATG.INTERNAL.equals(orderDataPMsg.crRebilRsnCatgCd.getValue()))) {
                if (origInvTMsg != null && (CR_REBIL.CREDIT.equals(crRebilCd)
                                        || (CR_REBIL.REBILL.equals(crRebilCd) && CR_REBIL_RSN_CATG.INTERNAL.equals(orderDataPMsg.crRebilRsnCatgCd.getValue())))) {
                setValue(invTMsg.invDt, origInvTMsg.invDt.getValue());
            }

            setValue(invTMsg.csmpInvProcStsCd, CSMP_INV_PROC_STS.NONE);
            setValue(invTMsg.csmpInvClmStsCd, CSMP_INV_CLM_STS.NONE);
            
            //set send status code
            invPrintStsCd = STS_CD_ONE;

            setInvSendStatus(invBean, invPrintStsCd);

            if (hasValue(orderDataPMsg.dsOrdTpCd)) {
                DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTmsg = new DS_ORD_TP_PROC_DFNTMsg();
                dsOrdTpProcDfnTmsg.glblCmpyCd.setValue(paramPMsg.glblCmpyCd.getValue());
                dsOrdTpProcDfnTmsg.dsOrdTpCd.setValue(orderDataPMsg.dsOrdTpCd.getValue());

                dsOrdTpProcDfnTmsg = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(dsOrdTpProcDfnTmsg);

                if (dsOrdTpProcDfnTmsg != null) {
                    setValue(invTMsg.lineBizTpCd, dsOrdTpProcDfnTmsg.lineBizTpCd.getValue());

                    //QC#24836
                    //if (INV_PRINT_STYLE.SUMMARY.equals(dsOrdTpProcDfnTmsg.invPrintStyleCd.getValue())) {
                    if (INV_PRINT_STYLE.SUMMARY.equals(dsOrdTpProcDfnTmsg.invPrintStyleCd.getValue()) 
                            || INV_PRINT_STYLE.SUMMARY_BY_CONFIG.equals(dsOrdTpProcDfnTmsg.invPrintStyleCd.getValue())) {
                        invTMsg.dplyMdseDtlFlg.setValue(ZYPConstant.FLG_OFF_N);
                    } else {
                        invTMsg.dplyMdseDtlFlg.setValue(ZYPConstant.FLG_ON_Y);
                    }
                }
            }

            //get biz area cd
            Map<String, Object> mapParam = new HashMap<String, Object>();
            mapParam.put("glblCmpyCd",  glblCmpyCd);
            List<String> ordCatgCtxTpCdList = new ArrayList<String>();
            ordCatgCtxTpCdList.add(ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
            ordCatgCtxTpCdList.add(ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
            mapParam.put("ordCatgCtxTpCdList", ordCatgCtxTpCdList.toArray(new String[0]));
            mapParam.put("dsOrdCatgCd", invTMsg.dsOrdCatgCd.getValue());
            mapParam.put("dsOrdTpCd",   invTMsg.dsOrdTpCd.getValue());
            mapParam.put("dsOrdRsnCd",  invTMsg.dsOrdRsnCd.getValue());
            String bizAreaCd = (String) ssmBatchClient.queryObject("getBizAreaCd", mapParam);
            if (bizAreaCd != null) {
                setValue(invTMsg.dsBizAreaCd, bizAreaCd);
            }

            if (!getFromMasterForINVData(orderDataPMsg, invBean, s21LRUMap)) {
                isSuccess = false;
            }

            // START 2023/11/10 [QC#61468, ADD]
            convDsInvTpFcOnlyContrSupply(invBean);
            // END   2023/11/10 [QC#61468, ADD]
            invBeanList.add(invBean);

        }

        //QC#22278 move to NWCB010001
        //boolean prtFlg= true;
        //for (OrderDataBean orderDataBean : orderDataBeanList) {
        //    NWZC035002PMsg orderDataPMsg = orderDataBean.getNWZC035002PMsg();
        //    if (CR_REBIL_RSN_CATG.INTERNAL.equals(orderDataPMsg.crRebilRsnCatgCd.getValue())
        //       && CR_REBIL.REBILL.equals(orderDataPMsg.crRebilCd.getValue())
        //    ) {
        //        prtFlg = false;
        //        break;
        //    }
        //}
        //if (!prtFlg) {
        //    for (INVBean invBean : invBeanList) {
        //        INVTMsg invTMsg = invBean.getInvTMsg();
        //        setValue(invTMsg.invPrintStsCd, STS_CD_ZERO);
        //    }
        //}

        return isSuccess;
    }

    @SuppressWarnings("unchecked")
    private void getInvoiceCommentText(NWZC035001PMsg paramPMsg, NWZC035002PMsg orderDataPMsg, S21LRUMap<String, Map<String, String>> cacheMap) {

        String cpoOrdNum = paramPMsg.cpoOrdNum.getValue();
        String cacheKey = createCacheKey("queryMSG_TXT_DTL", cpoOrdNum);
        Map<String, String> cmntTxtMap = cacheMap.get(cacheKey);

        if (cmntTxtMap == null) {

            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd", paramPMsg.glblCmpyCd.getValue());
            mapParam.put("cpoOrdNum", cpoOrdNum);
            mapParam.put("txtTpCd", TXT_TP.INVOICE_COMMENT);
            List<Map<String, String>> cmntTxtList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("queryMSG_TXT_DTL", mapParam);
            if (cmntTxtList.isEmpty()) {
                cacheMap.put(cacheKey, new HashMap<String, String>());
                return;
            }

            String breakKey = null;
            StringBuilder stbInvMsgtxt = new StringBuilder();
            String cmntTxt[] = new String[4];
            for (Map<String, String> resultMap : cmntTxtList) {

                String cpoDtlLineNum = nvl(resultMap.get("CPO_DTL_LINE_NUM"));
                String cpoDtlLineSubNum = nvl(resultMap.get("CPO_DTL_LINE_SUB_NUM"));
                String msgTxtInfoTxt = resultMap.get("MSG_TXT_INFO_TXT");

                int sqNum = Integer.parseInt(resultMap.get("TXT_SQ_NUM")) - 1;

                String dtlNum = cpoDtlLineNum + cpoDtlLineSubNum;
                if (breakKey != null && !breakKey.equals(dtlNum)) {
                    if (stbInvMsgtxt.length() != 0) {
                        break;
                    }
                }
                cmntTxt[sqNum] = msgTxtInfoTxt;

                stbInvMsgtxt = stbInvMsgtxt.append(msgTxtInfoTxt);
                breakKey = dtlNum;
            }
            String firstCmntTxt = cmntTxt[0];
            String scdCmntTxt   = cmntTxt[1];
            String thirdCmntTxt = cmntTxt[2];
            String frthCmntTxt  = cmntTxt[3];

            setValue(orderDataPMsg.invFirstCmntTxt, firstCmntTxt);
            setValue(orderDataPMsg.invScdCmntTxt, scdCmntTxt);
            setValue(orderDataPMsg.invThirdCmntTxt, thirdCmntTxt);
            setValue(orderDataPMsg.invFrthCmntTxt, frthCmntTxt);

            cmntTxtMap = new HashMap<String, String>();
            cmntTxtMap.put("invFirstCmntTxt", firstCmntTxt);
            cmntTxtMap.put("invScdCmntTxt", scdCmntTxt);
            cmntTxtMap.put("invThirdCmntTxt", thirdCmntTxt);
            cmntTxtMap.put("invFrthCmntTxt", frthCmntTxt);
            cacheMap.put(cacheKey, cmntTxtMap);

        } else {
            setValue(orderDataPMsg.invFirstCmntTxt, cmntTxtMap.get("invFirstCmntTxt"));
            setValue(orderDataPMsg.invScdCmntTxt, cmntTxtMap.get("invScdCmntTxt"));
            setValue(orderDataPMsg.invThirdCmntTxt, cmntTxtMap.get("invThirdCmntTxt"));
            setValue(orderDataPMsg.invFrthCmntTxt, cmntTxtMap.get("invFrthCmntTxt"));
        }
    }

    private boolean getNETDueDt(INVBean invBean, S21LRUMap<String, Object> cacheMap) {

        boolean isSuccess = true;
        S21ApiMessageMap msgMap = invBean.getMsgMap();
        NWZC035001PMsg paramPMsg = (NWZC035001PMsg) msgMap.getPmsg();
        INVTMsg invTMsg = invBean.getInvTMsg();

        boolean ezPack1Flg = false;
        String dsOrdCatgCd = invTMsg.dsOrdCatgCd.getValue();
        String dsOrdTpCd   = invTMsg.dsOrdTpCd.getValue();
        String dsOrdRsnCd  = invTMsg.dsOrdRsnCd.getValue();
        String billToCustAcctCd = invTMsg.billToCustAcctCd.getValue();
        String billToCustLocCd = invTMsg.billToCustCd.getValue();
        String invDt       = invTMsg.invDt.getValue();

        String cacheKey = createCacheKey("queryORD_CATG_BIZ_CTX"
                        , CONST_ORD_CATG_BIZ_CTX_EASY_PACK1
                        , dsOrdCatgCd
                        , dsOrdTpCd
                        , dsOrdRsnCd
                        , billToCustAcctCd
                        , billToCustLocCd
                        , invDt);
        Integer resultCnt = (Integer) cacheMap.get(cacheKey);

        if (resultCnt == null) {
            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd",       paramPMsg.glblCmpyCd.getValue());
            mapParam.put("ordCatgCtxTpCd",   CONST_ORD_CATG_BIZ_CTX_EASY_PACK1);
            mapParam.put("dsOrdCatgCd",      dsOrdCatgCd);
            mapParam.put("dsOrdTpCd",        dsOrdTpCd);
            mapParam.put("dsOrdRsnCd",       dsOrdRsnCd);
            mapParam.put("billToCustAcctCd", billToCustAcctCd);
            mapParam.put("billToCustLocCd",  billToCustLocCd);
            mapParam.put("invDt",            invDt);
            resultCnt = (Integer) ssmBatchClient.queryObject("queryORD_CATG_BIZ_CTX", mapParam);
            cacheMap.put(cacheKey, resultCnt);
        }
        if (resultCnt > 0) {
            ezPack1Flg = true;
        }

        // due date calculation api
        NFZC309001PMsg pmsg = new NFZC309001PMsg();
        setValue(pmsg.glblCmpyCd, glblCmpyCd);
        setValue(pmsg.pmtTermCashDiscCd, invTMsg.pmtTermCashDiscCd.getValue());
        setValue(pmsg.trxDt, invTMsg.invDt.getValue());
        if (ezPack1Flg) {
            setValue(pmsg.startDt, getEndOfMonth(invDt, 0));
        }
        NFZC309001 dueDateCalculationAPI = new NFZC309001();
        dueDateCalculationAPI.execute(pmsg, ONBATCH_TYPE.BATCH);
        if (pmsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pmsg.xxMsgIdList.getValidCount(); i++) {
                S21InfoLogOutput.println(pmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                isSuccess = true;
            }
            return false;

        } else {
            setValue(invTMsg.netDueDt, pmsg.dueDt.getValue());
        }

        return isSuccess;
    }

    private String getEndOfMonth(String date, int m) {

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(ZYPDateUtil.DateFormatter(date, "yyyyMMdd", "yyyy")),
                Integer.valueOf(ZYPDateUtil.DateFormatter(date, "yyyyMMdd", "MM")) - 1,
                Integer.valueOf(ZYPDateUtil.DateFormatter(date, "yyyyMMdd", "dd")));

        cal.add(Calendar.MONTH, m);

        String yyyy = String.valueOf(cal.get(Calendar.YEAR));
        String mm = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (mm != null && mm.length() == 1) {
            mm = "0" + mm;
        }
        String dd = String.valueOf(cal.getActualMaximum(Calendar.DATE));

        return yyyy + mm + dd;
    }

    @SuppressWarnings("unchecked")
    private boolean getOrderData(List<S21ApiMessageMap> msgMapList, List<OrderDataBean> orderDataBeanList, List<INV_PRMO_INFOBean> invPrmoInfoBeanList) {

        boolean isSuccess = true;

        S21LRUMap<String, Map<String, String>> cacheMap = new S21LRUMap<String, Map<String, String>>();

        for (S21ApiMessageMap msgMap : msgMapList) {

            NWZC035001PMsg paramPMsg = (NWZC035001PMsg) msgMap.getPmsg();

            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd", paramPMsg.glblCmpyCd.getValue());
            mapParam.put("cpoOrdNum", paramPMsg.cpoOrdNum.getValue());
            mapParam.put("cpoDtlLineNum", paramPMsg.cpoDtlLineNum.getValue());
            mapParam.put("cpoDtlLineSubNum", paramPMsg.cpoDtlLineSubNum.getValue());
            mapParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            NWZC035002PMsg orderDataPMsg;

            mapParam.put("shpgPlnNum", paramPMsg.shpgPlnNum.getValue());

            orderDataPMsg = (NWZC035002PMsg) ssmBatchClient.queryObject("queryContainSHPG_PLN", mapParam);

            if (orderDataPMsg == null) {
                msgMap.addXxMsgId(NWZM0251E);
                isSuccess = false;
                continue;
            }

            if (!hasValue(paramPMsg.shpgPlnNum)) {
                orderDataPMsg.shipDt.setValue(paramPMsg.slsDt.getValue());
                orderDataPMsg.shipDealFrtAmt.setValue(ZERO);
                orderDataPMsg.shipFuncFrtAmt.setValue(ZERO);
            }

            else {
                if (!hasValue(orderDataPMsg.shipDt)) {
                    orderDataPMsg.shipDt.setValue(paramPMsg.slsDt.getValue());
                }
            }

            getInvoiceCommentText(paramPMsg, orderDataPMsg, cacheMap);

            orderDataBeanList.add(new OrderDataBean(msgMap, orderDataPMsg));

            List<INV_PRMO_INFOTMsg> invPrmoInfoTMsgList = (List<INV_PRMO_INFOTMsg>) ssmBatchClient.queryObjectList("queryPRC_DTL", paramPMsg);
            if (invPrmoInfoTMsgList.isEmpty()) {
                msgMap.addXxMsgId(NWZM0252E);
                isSuccess = false;
                continue;
            }

            for (INV_PRMO_INFOTMsg invPrmoInfoTMsg : invPrmoInfoTMsgList) {
                invPrmoInfoTMsg.glblCmpyCd.setValue(paramPMsg.glblCmpyCd.getValue());
            }

            invPrmoInfoBeanList.add(new INV_PRMO_INFOBean(msgMap, invPrmoInfoTMsgList));

        }
        return isSuccess;
    }

    private boolean getTaxCalculationAPIResult(INVBean invBean, Iterator<NWZC036101PMsg> taxcalcPMsgItr, List<NWXC001001ExchangePriceData> invBolExchDataList, List<NWXC001001ExchangePriceData> invLineExchDataList) {

        boolean isSuccess = true;

        List<INV_BOLBean> invBolBeanList = invBean.getInvBolBeanList();
        DS_INV_LINE_TAX_DTLTMsg dsInvLineTaxDtlTMsg;
        NWZC036101_taxCalculateOutputLinePMsg taxcalcOutputLinePMsg;
        BigDecimal taxAmt;
        BigDecimal taxPct;
        List<BigDecimal> taxAmtList;
        List<BigDecimal> taxPctList;
        List<String> taxResultList;
        List<String> frtTaxTpList = new ArrayList<String>();
        frtTaxTpList.add(DS_SLS_TAX_TP.FREIGHT_STATE_TAX);
        frtTaxTpList.add(DS_SLS_TAX_TP.FREIGHT_COUNTY_TAX);
        frtTaxTpList.add(DS_SLS_TAX_TP.FREIGHT_CITY_TAX);
        List<String> slsTaxTpList = new ArrayList<String>();
        slsTaxTpList.add(DS_SLS_TAX_TP.STATE_TAX);
        slsTaxTpList.add(DS_SLS_TAX_TP.COUNTY_TAX);
        slsTaxTpList.add(DS_SLS_TAX_TP.CITY_TAX);

        for (INV_BOLBean invBolBean : invBolBeanList) {
            INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();
            String invNum = invBolTMsg.invNum.getValue();
            String invBolLineNum = invBolTMsg.invBolLineNum.getValue();
            NWZC036101PMsg taxcalcPMsg = taxcalcPMsgItr.next();
            S21ApiMessageMap invBolMsgMap = invBolBean.getMsgMap();
            if (taxcalcPMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < taxcalcPMsg.xxMsgIdList.getValidCount(); i++) {
                    invBolMsgMap.addXxMsgId(taxcalcPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                isSuccess = false;
            }

            boolean firstLineFlg = true;
            int taxCalcLineNum = 0;
            List<INV_LINEBean> invLineBeanList = invBolBean.getInvLineBeanList();
            for (INV_LINEBean invLineBean : invLineBeanList) {
                INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

                if (ZYPConstant.FLG_ON_Y.equals(invLineBean.getSetItemDummyFlg())) {
                    continue;
                }

                List<DS_INV_LINE_TAX_DTLTMsg> dsInvLineTaxDtlTMsgTList = invLineBean.getDsInvLineTaxDtlList();
                if (dsInvLineTaxDtlTMsgTList == null) {
                    continue;
                }

                // QC#21841 2018/05/21 Del Start
                // tax for invBol
                //if (firstLineFlg) {
                //    firstLineFlg = false;
                //
                //    taxcalcOutputLinePMsg = (NWZC036101_taxCalculateOutputLinePMsg) taxcalcPMsg.taxCalculateOutputLine.no(taxCalcLineNum++);
                //    taxAmt  = taxcalcOutputLinePMsg.invLineFuncTaxAmt.getValue();
                //    if (taxAmt == null) {
                //        taxAmt = BigDecimal.ZERO;
                //    }
                //    taxPct  = taxcalcOutputLinePMsg.xxTaxCalcLineTaxPct.getValue();
                //    if (taxPct == null) {
                //        taxPct = BigDecimal.ZERO;
                //    }
                //
                //    setValue(invBolTMsg.frtTaxPct, taxPct);
                //    setValue(invBolTMsg.frtFuncTaxAmt, taxAmt);
                //    setValue(invBolTMsg.frtDealTaxAmt, taxAmt);
                //
                //    // tax detail
                //    taxAmtList = new ArrayList<BigDecimal>();
                //    taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_01.getValue());
                //    taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_02.getValue());
                //    taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_03.getValue());
                //    taxPctList = new ArrayList<BigDecimal>();
                //    taxPctList.add(taxcalcOutputLinePMsg.taxPct_01.getValue());
                //    taxPctList.add(taxcalcOutputLinePMsg.taxPct_02.getValue());
                //    taxPctList.add(taxcalcOutputLinePMsg.taxPct_03.getValue());
                //    taxResultList = new ArrayList<String>();
                //    taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_01.getValue());
                //    taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_02.getValue());
                //    taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_03.getValue());
                //
                //    for (int i = 0; i < taxResultList.size(); i++) {
                //        taxAmt = taxAmtList.get(i);
                //        taxPct = taxPctList.get(i);
                //        if (taxAmt != null) {
                //            dsInvLineTaxDtlTMsg = createTaxDtlTMsg(invNum, invBolLineNum, null, null, null);
                //            setValue(dsInvLineTaxDtlTMsg.dsSlsTaxTpCd, frtTaxTpList.get(i));
                //            setValue(dsInvLineTaxDtlTMsg.funcSlsTaxAmt, taxAmt);
                //            setValue(dsInvLineTaxDtlTMsg.dealSlsTaxAmt, taxAmt);
                //            setValue(dsInvLineTaxDtlTMsg.slsTaxPct, taxPct);
                //            setValue(dsInvLineTaxDtlTMsg.taxAreaId, taxcalcOutputLinePMsg.taxAreaId);
                //            setValue(dsInvLineTaxDtlTMsg.taxRsltDescTxt, taxResultList.get(i));
                //            dsInvLineTaxDtlTMsgTList.add(dsInvLineTaxDtlTMsg);
                //        }
                //    }
                //}
                // QC#21841 2018/05/21 Del End

                // tax for invLine
                String invLineNum = invLineTMsg.invLineNum.getValue();
                String invLineSubNum = invLineTMsg.invLineSubNum.getValue();
                String invLineSubTrxNum = invLineTMsg.invLineSubTrxNum.getValue();

                taxcalcOutputLinePMsg = (NWZC036101_taxCalculateOutputLinePMsg) taxcalcPMsg.taxCalculateOutputLine.no(taxCalcLineNum++);
                taxAmt  = taxcalcOutputLinePMsg.invLineFuncTaxAmt.getValue();
                if (taxAmt == null) {
                    taxAmt = BigDecimal.ZERO;
                }
                taxPct  = taxcalcOutputLinePMsg.xxTaxCalcLineTaxPct.getValue();
                if (taxPct == null) {
                    taxPct = BigDecimal.ZERO;
                }
S21InfoLogOutput.println("=== NWZC035001 : getTaxCalculationAPIResult ===");
S21InfoLogOutput.println(taxcalcOutputLinePMsg.toString());
S21InfoLogOutput.println(" invLineFuncTaxAmt[" + taxAmt.toString()+"]");
S21InfoLogOutput.println(" xxTaxCalcLineTaxPct[" + taxPct.toString()+"]");
                setValue(invLineTMsg.taxPct, taxPct);
                setValue(invLineTMsg.invLineFuncTaxAmt, taxAmt);
                setValue(invLineTMsg.invLineDealTaxAmt, taxAmt);
                setValue(invLineTMsg.taxCalcGeoCd, taxcalcOutputLinePMsg.taxAreaId.getValue());
                
                // tax detail
                taxAmtList = new ArrayList<BigDecimal>();
                taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_01.getValue());
                taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_02.getValue());
                taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_03.getValue());
                taxPctList = new ArrayList<BigDecimal>();
                taxPctList.add(taxcalcOutputLinePMsg.taxPct_01.getValue());
                taxPctList.add(taxcalcOutputLinePMsg.taxPct_02.getValue());
                taxPctList.add(taxcalcOutputLinePMsg.taxPct_03.getValue());
                taxResultList = new ArrayList<String>();
                taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_01.getValue());
                taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_02.getValue());
                taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_03.getValue());

                for (int i = 0; i < taxResultList.size(); i++) {
                    taxAmt = taxAmtList.get(i);
                    taxPct = taxPctList.get(i);
                    if (taxAmt != null) {
S21InfoLogOutput.println("line=" + i);
S21InfoLogOutput.println(i+">invLineFuncTaxAmt[" + taxAmt.toString()+"]");
S21InfoLogOutput.println(i+">xxTaxCalcLineTaxPct[" + taxPct.toString()+"]");
                        dsInvLineTaxDtlTMsg = createTaxDtlTMsg(invNum, invBolLineNum, invLineNum, invLineSubNum, invLineSubTrxNum);
                        // QC#21841 2018/05/21 Add Start
                        //setValue(dsInvLineTaxDtlTMsg.dsSlsTaxTpCd, slsTaxTpList.get(i));
                        if (INV_LINE_CATG.FREIGHT.equals(invLineTMsg.invLineCatgCd.getValue())) {
                            setValue(dsInvLineTaxDtlTMsg.dsSlsTaxTpCd, frtTaxTpList.get(i));
                        } else {
                        setValue(dsInvLineTaxDtlTMsg.dsSlsTaxTpCd, slsTaxTpList.get(i));
                        }
                        // QC#21841 2018/05/21 Add End
                        setValue(dsInvLineTaxDtlTMsg.funcSlsTaxAmt, taxAmt);
                        setValue(dsInvLineTaxDtlTMsg.dealSlsTaxAmt, taxAmt);
                        setValue(dsInvLineTaxDtlTMsg.slsTaxPct, taxPct);
                        setValue(dsInvLineTaxDtlTMsg.taxAreaId, taxcalcOutputLinePMsg.taxAreaId);
                        setValue(dsInvLineTaxDtlTMsg.taxRsltDescTxt, taxResultList.get(i));
                        dsInvLineTaxDtlTMsgTList.add(dsInvLineTaxDtlTMsg);
                    }
                }
            }

        }
        return isSuccess;
    }
    private DS_INV_LINE_TAX_DTLTMsg createTaxDtlTMsg(String invNum, String invBolLineNum, String invLineNum, String invLineSubNum, String invLineSubTrxNum) {

        DS_INV_LINE_TAX_DTLTMsg dsInvLineTaxDtlTMsg = new DS_INV_LINE_TAX_DTLTMsg();
        setValue(dsInvLineTaxDtlTMsg.glblCmpyCd,       glblCmpyCd);
        setValue(dsInvLineTaxDtlTMsg.invNum,           invNum);
        setValue(dsInvLineTaxDtlTMsg.invBolLineNum,    invBolLineNum);
        if (invLineNum == null) {
            setValue(dsInvLineTaxDtlTMsg.invLineNum,       CONST_FRT_TAX_LINE_NUM);
        } else {
            setValue(dsInvLineTaxDtlTMsg.invLineNum,       invLineNum);
        }
        if (invLineSubNum == null) {
            setValue(dsInvLineTaxDtlTMsg.invLineSubNum,    CONST_FRT_TAX_LINE_NUM);
        } else {
            setValue(dsInvLineTaxDtlTMsg.invLineSubNum,    invLineSubNum);
        }
        if (invLineSubTrxNum == null) {
            setValue(dsInvLineTaxDtlTMsg.invTrxLineSubNum, CONST_FRT_TAX_LINE_NUM);
        } else {
            setValue(dsInvLineTaxDtlTMsg.invTrxLineSubNum, invLineSubTrxNum);
        }

        return dsInvLineTaxDtlTMsg;
    }

    private void getTaxForCreditOrderOfCreditAndRebill(List<INVBean> invBeanList) {

        for (INVBean invBean : invBeanList) {

            List<INV_BOLBean> invBolBeanList = invBean.getInvBolBeanList();

            for (INV_BOLBean invBolBean : invBolBeanList) {
                INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();
                invBolTMsg.frtTaxPct.setValue(ZERO);
                invBolTMsg.frtDealTaxAmt.setValue(ZERO);
                invBolTMsg.frtFuncTaxAmt.setValue(ZERO);

                List<INV_LINEBean> invLineBeanList = invBolBean.getInvLineBeanList();

                for (INV_LINEBean invLineBean : invLineBeanList) {
                    INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();
                    invLineTMsg.invLineDealTaxAmt.setValue(invLineBean.getOrigInvLineDealTaxAmt());
                    invLineTMsg.invLineFuncTaxAmt.setValue(invLineBean.getOrigInvLineFuncTaxAmt());
                    invLineTMsg.taxPct.setValue(invLineBean.getOrigInvLineTaxPct());
                }
            }
        }
    }

    private boolean getTaxFromTaxCalculationAPI(List<INVBean> invBeanList, ONBATCH_TYPE onBatchType) {

        boolean isSuccess = true;

        List<NWZC036101PMsg> taxcalcPMsgList = makeTaxCalculationAPIParam(invBeanList);

        NWZC036101 taxCalculationAPI = new NWZC036101();
        for (NWZC036101PMsg taxcalcPMsg : taxcalcPMsgList) {
            taxCalculationAPI.execute(taxcalcPMsg, onBatchType);
        }

        S21LRUMap<String, String> cacheMap = new S21LRUMap<String, String>();

        Iterator<NWZC036101PMsg> taxcalcPMsgItr = taxcalcPMsgList.iterator();
        for (INVBean invBean : invBeanList) {

            List<NWXC001001ExchangePriceData> invBolExchDataList = new ArrayList<NWXC001001ExchangePriceData>();
            List<NWXC001001ExchangePriceData> invLineExchDataList = new ArrayList<NWXC001001ExchangePriceData>();

            if (!getTaxCalculationAPIResult(invBean, taxcalcPMsgItr, invBolExchDataList, invLineExchDataList)) {
                isSuccess = false;
                continue;
            }

            // Only Tax of the dealing currency can be acquired from Tax Calculation API.
            // Therefore, when the functional currency and the dealing currency are different,
            // the amount of money of Tax of the dealing currency is requested from the amount of money of Tax of the functional currency.
            if (this.funcCcyCd.equals(invBean.getInvTMsg().dealCcyCd.getValue())) {
                continue;
            }

            if (!exchFuncAmtToDealAmt(invBean, invBolExchDataList, invLineExchDataList, cacheMap)) {
                isSuccess = false;
            }

        }
        return isSuccess;
    }

    private Boolean inputCheck(List<S21ApiMessageMap> msgMapList) {

        boolean isSuccess = true;

        for (S21ApiMessageMap msgMap : msgMapList) {

            NWZC035001PMsg pMsg = (NWZC035001PMsg) msgMap.getPmsg();

            if (!hasValue(pMsg.glblCmpyCd)) {
                msgMap.addXxMsgId(NWZM0163E);
                isSuccess = false;
            } else {
                glblCmpyCd = pMsg.glblCmpyCd.getValue();
            }

            if (!hasValue(pMsg.cpoOrdNum)) {
                msgMap.addXxMsgId(NWZM0002E);
                isSuccess = false;
            }

            if (!hasValue(pMsg.cpoDtlLineNum)) {
                msgMap.addXxMsgId(NWZM0003E);
                isSuccess = false;
            }

            if (!hasValue(pMsg.cpoDtlLineSubNum)) {
                msgMap.addXxMsgId(NWZM0004E);
                isSuccess = false;
            }
        }

        return isSuccess;
    }

//    private boolean insertINV_BOL(List<INV_BOLBean> allInvBolBeanList) {
    private boolean insertINV_BOL(List<INV_BOLBean> allInvBolBeanList, List<INVBean> invBeanList) {

        // QC#26121 2018/06/14 add Start
        for (INVBean invBean : invBeanList) {
            if (!invBean.getLastInvTpCd().equals(invBean.getInvTMsg().invTpCd.getValue())) {

                // update sign for other INV_LINE
                INV_BOLTMsg inTMsg = new INV_BOLTMsg();
                inTMsg.setSQLID("001");
                inTMsg.setConditionValue("glblCmpyCd01",invBean.getInvTMsg().glblCmpyCd.getValue());
                inTMsg.setConditionValue("invNum01",invBean.getInvTMsg().invNum.getValue());

                INV_BOLTMsgArray tmsgArray = (INV_BOLTMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);

                if (tmsgArray.length() > 0) {
                    for (int i = 0; i < tmsgArray.length(); i++) {
                        INV_BOLTMsg tmsg = (INV_BOLTMsg) tmsgArray.get(i);
                        negateInvAmt(tmsg);

                        S21ApiTBLAccessor.update(tmsg);
                        if (RTNCD_NOT_FOUND.equals(tmsg.getReturnCode())) {
                            invBean.getMsgMap().addXxMsgId(NWZM0272E);
                            return false;
                        }
                    }
                }
            }
        }
        // QC#26121 2018/06/14 add End
        for (INV_BOLBean invBolBean : allInvBolBeanList) {
            S21ApiMessageMap msgMap = invBolBean.getMsgMap();
            INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();
            //QC#26121 2018/06/14 add Start
            if (INV_TP.CREDIT_MEMO.equals(invBolBean.getInvTpCd())) {
                negateInvAmt(invBolTMsg);
            }
            //QC#26121 2018/06/14 add End
            INV_BOLTMsg updTmsg = (INV_BOLTMsg) S21ApiTBLAccessor.findByKey(invBolTMsg);
            if (updTmsg != null) {

                updTmsg.shipDealSlsAmt.setValue(updTmsg.shipDealSlsAmt.getValue().add(invBolTMsg.shipDealSlsAmt.getValue()));
                updTmsg.shipFuncSlsAmt.setValue(updTmsg.shipFuncSlsAmt.getValue().add(invBolTMsg.shipFuncSlsAmt.getValue()));
                updTmsg.shipDealNetAmt.setValue(updTmsg.shipDealNetAmt.getValue().add(invBolTMsg.shipDealNetAmt.getValue()));
                updTmsg.shipFuncNetAmt.setValue(updTmsg.shipFuncNetAmt.getValue().add(invBolTMsg.shipFuncNetAmt.getValue()));
                updTmsg.shipDealFrtAmt.setValue(updTmsg.shipDealFrtAmt.getValue().add(invBolTMsg.shipDealFrtAmt.getValue()));
                updTmsg.shipFuncFrtAmt.setValue(updTmsg.shipFuncFrtAmt.getValue().add(invBolTMsg.shipFuncFrtAmt.getValue()));
                updTmsg.shipDealDiscAmt.setValue(updTmsg.shipDealDiscAmt.getValue().add(invBolTMsg.shipDealDiscAmt.getValue()));
                updTmsg.shipFuncDiscAmt.setValue(updTmsg.shipFuncDiscAmt.getValue().add(invBolTMsg.shipFuncDiscAmt.getValue()));
                updTmsg.shipDealHdlgChrgAmt.setValue(updTmsg.shipDealHdlgChrgAmt.getValue().add(invBolTMsg.shipDealHdlgChrgAmt.getValue()));
                updTmsg.shipFuncHdlgChrgAmt.setValue(updTmsg.shipFuncHdlgChrgAmt.getValue().add(invBolTMsg.shipFuncHdlgChrgAmt.getValue()));
                updTmsg.totBolDealTaxAmt.setValue(updTmsg.totBolDealTaxAmt.getValue().add(invBolTMsg.totBolDealTaxAmt.getValue()));
                updTmsg.totBolFuncTaxAmt.setValue(updTmsg.totBolFuncTaxAmt.getValue().add(invBolTMsg.totBolFuncTaxAmt.getValue()));
                updTmsg.frtDealTaxAmt.setValue(updTmsg.frtDealTaxAmt.getValue().add(invBolTMsg.frtDealTaxAmt.getValue()));
                updTmsg.frtFuncTaxAmt.setValue(updTmsg.frtFuncTaxAmt.getValue().add(invBolTMsg.frtFuncTaxAmt.getValue()));

                S21ApiTBLAccessor.update(updTmsg);
                if (RTNCD_NOT_FOUND.equals(updTmsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0272E);
                    return false;
                }
            } else {
                S21ApiTBLAccessor.insert(invBolTMsg);
                if (RTNCD_DUPLICATE.equals(invBolTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0272E);
                    return false;
                }
            }
        }
        return true;
    }

//    private boolean insertINV_LINEandINV_PRMO_INFO(List<INV_LINEBean> allInvLineBeanList) {
    private boolean insertINV_LINEandINV_PRMO_INFO(List<INV_LINEBean> allInvLineBeanList, List<INVBean> invBeanList) {

        // QC#26121 2018/06/14 add Start
        for (INVBean invBean : invBeanList) {
            if (!invBean.getLastInvTpCd().equals(invBean.getInvTMsg().invTpCd.getValue())) {

                // update sign for other INV_LINE
                INV_LINETMsg lineTMsg = new INV_LINETMsg();
                lineTMsg.setSQLID("002");
                lineTMsg.setConditionValue("glblCmpyCd01",invBean.getInvTMsg().glblCmpyCd.getValue());
                lineTMsg.setConditionValue("invNum01",invBean.getInvTMsg().invNum.getValue());

                INV_LINETMsgArray lineTMsgArray = (INV_LINETMsgArray) S21ApiTBLAccessor.findByCondition(lineTMsg);

                if (lineTMsgArray.length() > 0) {
                    for (int i = 0; i < lineTMsgArray.length(); i++) {
                        INV_LINETMsg tmsg = (INV_LINETMsg) lineTMsgArray.get(i);
                        negateInvAmt(tmsg);

                        S21ApiTBLAccessor.update(tmsg);
                        if (RTNCD_NOT_FOUND.equals(tmsg.getReturnCode())) {
                            invBean.getMsgMap().addXxMsgId(NWZM0273E);
                            return false;
                        }
                    }
                }
                // update sign for other INV_PRMO_INFO
                INV_PRMO_INFOTMsg prmoTMsg = new INV_PRMO_INFOTMsg();
                prmoTMsg.setSQLID("001");
                prmoTMsg.setConditionValue("glblCmpyCd01",invBean.getInvTMsg().glblCmpyCd.getValue());
                prmoTMsg.setConditionValue("invNum01",invBean.getInvTMsg().invNum.getValue());

                INV_PRMO_INFOTMsgArray prmoTMsgArray = (INV_PRMO_INFOTMsgArray) S21ApiTBLAccessor.findByCondition(prmoTMsg);

                if (prmoTMsgArray.length() > 0) {
                    for (int i = 0; i < prmoTMsgArray.length(); i++) {
                        INV_PRMO_INFOTMsg tmsg = (INV_PRMO_INFOTMsg) prmoTMsgArray.get(i);
                        negateInvAmt(tmsg);

                        S21ApiTBLAccessor.update(tmsg);
                        if (RTNCD_NOT_FOUND.equals(tmsg.getReturnCode())) {
                            invBean.getMsgMap().addXxMsgId(NWZM0275E);
                            return false;
                        }
                    }
                }
            }
        }
        // QC#26121 2018/06/14 add End

        for (INV_LINEBean invLineBean : allInvLineBeanList) {

            S21ApiMessageMap msgMap = invLineBean.getMsgMap();
            INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();
            //QC#26121 2018/06/14 add Start
            if (INV_TP.CREDIT_MEMO.equals(invLineBean.getInvTpCd())) {
                negateInvAmt(invLineTMsg);
            }
            //QC#26121 2018/06/14 add End

            INV_LINETMsg updTmsg = (INV_LINETMsg) S21ApiTBLAccessor.findByKey(invLineTMsg);

            if (updTmsg != null) {

                invLineTMsg.shpgPlnNum.clear();
                invLineTMsg.invLineDealNetAmt.setValue(invLineTMsg.invLineDealNetAmt.getValue().add(updTmsg.invLineDealNetAmt.getValue()));
                invLineTMsg.invLineDealTaxAmt.setValue(invLineTMsg.invLineDealTaxAmt.getValue().add(updTmsg.invLineDealTaxAmt.getValue()));
                invLineTMsg.invLineFuncNetAmt.setValue(invLineTMsg.invLineFuncNetAmt.getValue().add(updTmsg.invLineFuncNetAmt.getValue()));
                invLineTMsg.invLineFuncTaxAmt.setValue(invLineTMsg.invLineFuncTaxAmt.getValue().add(updTmsg.invLineFuncTaxAmt.getValue()));
                invLineTMsg.dealDiscUnitPrcAmt.setValue(invLineTMsg.dealDiscUnitPrcAmt.getValue().add(updTmsg.dealDiscUnitPrcAmt.getValue()));
                invLineTMsg.funcDiscUnitPrcAmt.setValue(invLineTMsg.funcDiscUnitPrcAmt.getValue().add(updTmsg.funcDiscUnitPrcAmt.getValue()));
                invLineTMsg.shipQty.setValue(invLineTMsg.shipQty.getValue().add(updTmsg.shipQty.getValue()));
                // 2017/10/23 QC#20719 Del Start
                //// Add Start 2017/08/28 CSA Defect#20748
                //if (!hasValue(invLineTMsg.shipCmplCostAmt)) {
                //    setValue(invLineTMsg.shipCmplCostAmt, BigDecimal.ZERO);
                //}
                //if (!hasValue(updTmsg.shipCmplCostAmt)) {
                //    setValue(updTmsg.shipCmplCostAmt, BigDecimal.ZERO);
                //}
                //// Add End   2017/08/28 CSA Defect#20748
                //invLineTMsg.shipCmplCostAmt.setValue(invLineTMsg.shipCmplCostAmt.getValue().add(updTmsg.shipCmplCostAmt.getValue()));
                // 2017/10/23 QC#20719 Del End
                invLineTMsg.dealGrsTotPrcAmt.setValue(invLineTMsg.dealGrsTotPrcAmt.getValue().add(updTmsg.dealGrsTotPrcAmt.getValue()));
                invLineTMsg.funcGrsTotPrcAmt.setValue(invLineTMsg.funcGrsTotPrcAmt.getValue().add(updTmsg.funcGrsTotPrcAmt.getValue()));

                S21ApiTBLAccessor.update(invLineTMsg);
                if (RTNCD_NOT_FOUND.equals(invLineTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0273E);
                    return false;
                }
            } else {

                if (Y.equals(invLineBean.getBillWithEquipFlg())) {
                    invLineTMsg.cpoDtlLineNum.clear();
                    invLineTMsg.cpoDtlLineSubNum.clear();
                    invLineTMsg.shpgPlnNum.clear();
                    invLineTMsg.origCpoOrdNum.clear();
                    invLineTMsg.origInvNum.clear();
                    invLineTMsg.csmpContrNum.clear();
                    setValue(invLineTMsg.baseCmptFlg, ZYPConstant.FLG_OFF_N);
                }

                S21ApiTBLAccessor.insert(invLineTMsg);
                if (RTNCD_DUPLICATE.equals(invLineTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0273E);
                    return false;
                }
            }

            List<INV_PRMO_INFOTMsg> invPrmoInfoList = invLineBean.getInvPrmoInfoList();
            for (INV_PRMO_INFOTMsg invPrmoInfoTMsg : invPrmoInfoList) {
                //QC#26121 2018/06/14 add Start
                if (INV_TP.CREDIT_MEMO.equals(invLineBean.getInvTpCd())) {
                    negateInvAmt(invPrmoInfoTMsg);
                }
                //QC#26121 2018/06/14 add End

                S21ApiTBLAccessor.insert(invPrmoInfoTMsg);
                if (RTNCD_DUPLICATE.equals(invPrmoInfoTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0275E);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean insertUpdateINVandINV_CASH_DISC_TERM(List<INVBean> invBeanList) {

        for (INVBean invBean : invBeanList) {

            final S21ApiMessageMap msgMap = invBean.getMsgMap();

            final INVTMsg invTMsg = invBean.getInvTMsg();

            // QC#26121 2018/06/14 Add Start
            if (BigDecimal.ZERO.compareTo(invTMsg.invTotDealNetAmt.getValue()) > 0) {
                invBean.setInvTpCd(INV_TP.CREDIT_MEMO);
                DS_INV_TPTMsg  dsInvTpTmsg = invBean.getDsInvTpTMsg();
                if (ZYPCommonFunc.hasValue(dsInvTpTmsg.crDsInvTpCd)) {
                    ZYPEZDItemValueSetter.setValue(invTMsg.dsInvTpCd, dsInvTpTmsg.crDsInvTpCd);
                }
                negateInvAmt(invTMsg);
            } else {
                invBean.setInvTpCd(INV_TP.INVOICE);
            }
            if (invBean.getLastInvTpCd() == null) {
               invBean.setLastInvTpCd(invTMsg.invTpCd.getValue());
            }
            // QC#26121 2018/06/14 Add End

            // --------------------------------------------------
            // update
            // --------------------------------------------------
            if (invBean.isNeedUpdate()) {

                List<String> invUpList = new ArrayList<String>();
                invUpList.add("invTotDealNetAmt");
                invUpList.add("invTotDealSlsAmt");
                invUpList.add("invTotDealFrtAmt");
                invUpList.add("invTotDealTaxAmt");
                invUpList.add("invTotDealDiscAmt");
                invUpList.add("invTotFuncNetAmt");
                invUpList.add("invTotFuncSlsAmt");
                invUpList.add("invTotFuncFrtAmt");
                invUpList.add("invTotFuncTaxAmt");
                invUpList.add("invTotFuncDiscAmt");
                // QC#26121 2018/06/14 Add Start
                invUpList.add("invTpCd");
                invUpList.add("dsInvTpCd");
                // QC#26121 2018/06/14 Add End

                if (ZERO.compareTo(invTMsg.invTotDealNetAmt.getValue()) != 0) {
                    invUpList.add("invPrintStsCd");
                    invUpList.add("invMlSendStsCd");
                    invUpList.add("invEdiSendStsCd");
                    invUpList.add("invFaxSendStsCd");
                    invUpList.add("invEmlSendStsCd");
                }

                // INV
                S21ApiTBLAccessor.updateSelectionField(invTMsg, invUpList.toArray(new String[0]));

                // INV_CASH_DISC_TERM
                for (INV_CASH_DISC_TERMTMsg invCashDiscTermTMsg : invBean.getInvCashDiscTermList()) {
                    S21ApiTBLAccessor.updateSelectionField(
                            invCashDiscTermTMsg,
                            new String[] {
                                "invCashDiscAmt"
                            }
                    );
                }

            // --------------------------------------------------
            // insert
            // --------------------------------------------------
            } else {

                boolean creditCardsFlg = false;
                String creditCardsConstVal = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_PMT_TERM_CR_CARD, invBean.getInvTMsg().glblCmpyCd.getValue());
                if (ZYPCommonFunc.hasValue(creditCardsConstVal)) {
                    String[] creditCardsArry = creditCardsConstVal.split(",");
                    for (int i = 0; i < creditCardsArry.length; i++) {
                        if (creditCardsArry[i].equals(invBean.getInvTMsg().pmtTermCashDiscCd.getValue())) {
                            creditCardsFlg = true;
                            break;
                        }
                    }
                }
                if (creditCardsFlg) {
                    setValue(invTMsg.crCardChrgCpltCd, CR_CARD_CHRG_CPLT.WAITING_FOR_CREDIT_CARD_CHARGE);
                } else {
                    setValue(invTMsg.crCardChrgCpltCd, CR_CARD_CHRG_CPLT.NO_NEED_CREDIT_CARD_CHARGE);
                }
                setValue(invTMsg.arAutoPurgeOfsFlg, N);

                // QC#26121 2018/06/14 del Start
//                // QC#23086 2018/01/23 Add Start
//                if (ZYPCommonFunc.hasValue(invTMsg.invTpCd) && 
//                        INV_TP.CREDIT_MEMO.equals(invTMsg.invTpCd.getValue())) {
//                    DS_INV_TPTMsg  dsInvTpTmsg = invBean.getDsInvTpTMsg();
//
//                    if ((dsInvTpTmsg != null) && 
//                            (ZYPCommonFunc.hasValue(dsInvTpTmsg.crDsInvTpCd))){
//                        ZYPEZDItemValueSetter.setValue(invTMsg.dsInvTpCd, dsInvTpTmsg.crDsInvTpCd);
//                    } 
//                }
//                // QC#23086 2018/01/23 Add End
                // QC#26121 2018/06/14 del End

                // INV
                S21ApiTBLAccessor.insert(invTMsg);
                if (RTNCD_DUPLICATE.equals(invTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM0269E);
                    return false;
                }

                // INV_CASH_DISC_TERM
                if (!isEmpty(invBean.getInvCashDiscTermList())) {

                    // create a 'net due date record'. (Percentage = 0%)
                    final INV_CASH_DISC_TERMTMsg netDueDateRecord = new INV_CASH_DISC_TERMTMsg();
                    setValue(netDueDateRecord.glblCmpyCd,          invTMsg.glblCmpyCd);
                    setValue(netDueDateRecord.invNum,              invTMsg.invNum);
                    setValue(netDueDateRecord.invCashDiscDueDt,    invTMsg.netDueDt);
                    setValue(netDueDateRecord.invCashDiscRatioPct, ZERO);
                    setValue(netDueDateRecord.invCashDiscAmt,      invTMsg.invTotDealNetAmt);

                    final List<INV_CASH_DISC_TERMTMsg> invCashDiscTermList = new ArrayList<INV_CASH_DISC_TERMTMsg>(invBean.getInvCashDiscTermList());
                    invCashDiscTermList.add(netDueDateRecord);

                    // order by 'INV_CASH_DISC_DUE_DT' ASC.
                    Collections.sort(invCashDiscTermList,
                            new Comparator<INV_CASH_DISC_TERMTMsg>(){
                                public int compare(INV_CASH_DISC_TERMTMsg tMsg1, INV_CASH_DISC_TERMTMsg tMsg2) {
                                    return tMsg1.invCashDiscDueDt.getValue().compareTo(tMsg2.invCashDiscDueDt.getValue());
                                }
                            }
                    );

                    int invCashDiscTermDtlNum = 1;
                    for (INV_CASH_DISC_TERMTMsg invCashDiscTermTMsg : invCashDiscTermList) {
                        // INV_CASH_DISC_TERM_DTL_NUM
                        setValue(invCashDiscTermTMsg.invCashDiscTermDtlNum, Integer.toString(invCashDiscTermDtlNum++));
                        S21ApiTBLAccessor.insert(invCashDiscTermTMsg);
                        if (RTNCD_DUPLICATE.equals(invCashDiscTermTMsg.getReturnCode())) {
                            msgMap.addXxMsgId(NWZM0271E);
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    private boolean makeDummySetMdseData(List<INV_BOLBean> allInvBolBeanList, List<INV_LINEBean> allInvLineBeanList) {

        boolean isSuccess = true;

        for (INV_BOLBean invBolBean : allInvBolBeanList) {
            List<INV_LINEBean> invLineBeanList = invBolBean.getInvLineBeanList();

            List<INV_LINEBean> dummySetMdseBeanList = new ArrayList<INV_LINEBean>();

            Set<String> keySet = new HashSet<String>();
            for (INV_LINEBean invLineBean : invLineBeanList) {

                INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

                if (!(N.equals(invLineTMsg.setRatioKeepFlg.getValue()) && (FIXED_SET_ITEM_CPO_DTL_LINE_SUB_NUM.equals(invLineTMsg.cpoDtlLineSubNum.getValue()) || hasValue(invLineTMsg.setMdseCd)))) {
                    continue;
                }

                if (FIXED_SET_ITEM_CPO_DTL_LINE_SUB_NUM.equals(invLineTMsg.cpoDtlLineSubNum.getValue())) {
                    invLineTMsg.shipQty.clear();
                    List<INV_PRMO_INFOTMsg> invPrmoInfoList = invLineBean.getInvPrmoInfoList();
                    for (INV_PRMO_INFOTMsg invPrmoInfoTMSg : invPrmoInfoList) {
                        invPrmoInfoTMSg.prmoQty.clear();
                    }
                }

                // When the merchandise of eight columns of orders was divided to 10 columns of plural merchandises,
                // I prevent it from making plural same dummy Set Merchandise.
                String key = createCacheKey(invLineTMsg.cpoDtlLineNum.getValue(), invLineTMsg.cpoDtlLineSubNum.getValue());
                if (keySet.contains(key)) {
                    continue;
                }
                keySet.add(key);

                int beforeDummySetMdseDataListSize = dummySetMdseBeanList.size();
                for (Iterator<INV_LINEBean> itr = dummySetMdseBeanList.iterator(); itr.hasNext();) {
                    INV_LINETMsg dummySetMdseDataTMsg = itr.next().getInvLineTMsg();
                    if (invLineTMsg.cpoDtlLineNum.getValue().equals(dummySetMdseDataTMsg.cpoDtlLineNum.getValue()) && invLineTMsg.cpoDtlLineSubNum.getValue().equals(dummySetMdseDataTMsg.cpoDtlLineSubNum.getValue())) {
                        itr.remove();
                    }
                }

                if (beforeDummySetMdseDataListSize != dummySetMdseBeanList.size()) {
                    continue;
                }

                S21ApiMessageMap msgMap = invLineBean.getMsgMap();
                NWZC035001PMsg paramPMsg = (NWZC035001PMsg) msgMap.getPmsg();

                List<Map> ssmResList = (List<Map>) ssmBatchClient.queryObjectList("queryDS_CPO_DTL_FOR_MAKE_DUMMY_SET_MDSE", paramPMsg);
                if (ssmResList.isEmpty()) {
                    msgMap.addXxMsgId(NWZM0074E);
                    isSuccess = false;
                    continue;
                }

                for (Map mapRes : ssmResList) {
                    INV_LINEBean dummySetMdseBean = new INV_LINEBean(msgMap, invLineTMsg);
                    INV_LINETMsg dummySetMdseTMsg = dummySetMdseBean.getInvLineTMsg();
                    dummySetMdseTMsg.cpoDtlLineSubNum.setValue((String) mapRes.get("CPO_DTL_LINE_SUB_NUM"));
                    setValue(dummySetMdseTMsg.mdseCd, (String) mapRes.get("MDSE_CD"));
                    setValue(dummySetMdseTMsg.ordQty, (BigDecimal) mapRes.get("ORD_QTY"));
                    setValue(dummySetMdseTMsg.slsRepTocCd, (String) mapRes.get("SLS_REP_OR_SLS_TEAM_TOC_CD"));
                    setValue(dummySetMdseTMsg.baseCmptFlg, (String) mapRes.get("BASE_CMPT_FLG"));

                    dummySetMdseTMsg.shpgPlnNum.clear();
                    INV_PRMO_INFOTMsg invPrmoInfoTMsg = new INV_PRMO_INFOTMsg();
                    invPrmoInfoTMsg.glblCmpyCd.setValue(invLineTMsg.glblCmpyCd.getValue());
                    if (FIXED_SET_ITEM_CPO_DTL_LINE_SUB_NUM.equals(dummySetMdseTMsg.cpoDtlLineSubNum.getValue())) {
                        dummySetMdseTMsg.shipQty.clear();
                        dummySetMdseTMsg.dealNetUnitPrcAmt.clear();
                        dummySetMdseTMsg.invLineDealTaxAmt.clear();
                        dummySetMdseTMsg.invLineDealNetAmt.clear();
                        dummySetMdseTMsg.dealDiscUnitPrcAmt.clear();
                        dummySetMdseTMsg.funcNetUnitPrcAmt.clear();
                        dummySetMdseTMsg.invLineFuncTaxAmt.clear();
                        dummySetMdseTMsg.invLineFuncNetAmt.clear();
                        dummySetMdseTMsg.funcDiscUnitPrcAmt.clear();
                        dummySetMdseTMsg.dealGrsUnitPrcAmt.clear();
                        dummySetMdseTMsg.dealGrsTotPrcAmt.clear();
                        dummySetMdseTMsg.funcGrsUnitPrcAmt.clear();
                        dummySetMdseTMsg.funcGrsTotPrcAmt.clear();
                        dummySetMdseTMsg.taxPct.clear();
                        dummySetMdseTMsg.setMdseCd.clear();
                    } else {
                        dummySetMdseTMsg.shipQty.setValue(ZERO);
                        dummySetMdseTMsg.dealNetUnitPrcAmt.setValue(ZERO);
                        dummySetMdseTMsg.invLineDealTaxAmt.setValue(ZERO);
                        dummySetMdseTMsg.invLineDealNetAmt.setValue(ZERO);
                        dummySetMdseTMsg.dealDiscUnitPrcAmt.setValue(ZERO);
                        dummySetMdseTMsg.funcNetUnitPrcAmt.setValue(ZERO);
                        dummySetMdseTMsg.invLineFuncTaxAmt.setValue(ZERO);
                        dummySetMdseTMsg.invLineFuncNetAmt.setValue(ZERO);
                        dummySetMdseTMsg.funcDiscUnitPrcAmt.setValue(ZERO);
                        dummySetMdseTMsg.dealGrsUnitPrcAmt.setValue(ZERO);
                        dummySetMdseTMsg.dealGrsTotPrcAmt.setValue(ZERO);
                        dummySetMdseTMsg.funcGrsUnitPrcAmt.setValue(ZERO);
                        dummySetMdseTMsg.funcGrsTotPrcAmt.setValue(ZERO);
                        dummySetMdseTMsg.taxPct.setValue(ZERO);
                        if (FIXED_SET_ITEM_CPO_DTL_LINE_SUB_NUM.equals(invLineTMsg.cpoDtlLineSubNum.getValue())) {
                            dummySetMdseTMsg.setMdseCd.setValue(invLineTMsg.mdseCd.getValue());
                        }
                        invPrmoInfoTMsg.prmoQty.setValue(ZERO);
                        invPrmoInfoTMsg.dealUnitPrcAmt.setValue(ZERO);
                        invPrmoInfoTMsg.dealLastNetUnitPrcAmt.setValue(ZERO);
                        invPrmoInfoTMsg.dealNetAmt.setValue(ZERO);
                        invPrmoInfoTMsg.dealDiscAmt.setValue(ZERO);
                        invPrmoInfoTMsg.dealPrmoNetUnitPrcAmt.setValue(ZERO);
                        invPrmoInfoTMsg.dealPerUnitFixAmt.setValue(ZERO);
                        invPrmoInfoTMsg.dealSlsPctNum.setValue(ZERO);
                        invPrmoInfoTMsg.funcPerUnitFixAmt.setValue(ZERO);
                        invPrmoInfoTMsg.funcUnitPrcAmt.setValue(ZERO);
                        invPrmoInfoTMsg.funcLastNetUnitPrcAmt.setValue(ZERO);
                        invPrmoInfoTMsg.funcNetAmt.setValue(ZERO);
                        invPrmoInfoTMsg.funcDiscAmt.setValue(ZERO);
                        invPrmoInfoTMsg.funcPrmoNetUnitPrcAmt.setValue(ZERO);
                        invPrmoInfoTMsg.invPrmoInfoSqNum.setValue(FIXED_INV_PRMO_INFO_SEQ_NUM);
                    }
                    dummySetMdseBean.addInvPrmoInfo(invPrmoInfoTMsg);
                    dummySetMdseBeanList.add(dummySetMdseBean);
                }
            }
            invLineBeanList.addAll(dummySetMdseBeanList);
            allInvLineBeanList.addAll(dummySetMdseBeanList);
        }

        return isSuccess;
    }

    @SuppressWarnings("unchecked")
    private List<NWZC036101PMsg> makeTaxCalculationAPIParam(List<INVBean> invBeanList) {

        List<NWZC036101PMsg> taxcalcPMsgList = new ArrayList<NWZC036101PMsg>();
        String frtTaxDummyMdseCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_FRT_TAX_DUMMY_MDSE_CD, glblCmpyCd);
        if (frtTaxDummyMdseCd == null) {
            throw new S21AbendException("[Error]Not Found 'VAR_CHAR_CONST' : varCharConstNm=" + CONST_KEY_FRT_TAX_DUMMY_MDSE_CD);
        }
        NWZC036101_taxCalculateInputLinePMsg taxCalcInputLinePMsg;

        for (INVBean invBean : invBeanList) {
            NWZC035001PMsg paramPMsg = (NWZC035001PMsg) invBean.getMsgMap().getPmsg();
            INVTMsg invTMsg = invBean.getInvTMsg();

            String sellToCustCd = invTMsg.sellToCustCd.getValue();
            String billToCustAcctCd = invTMsg.billToCustAcctCd.getValue();
            String billToCustLocCd = invTMsg.billToCustCd.getValue();
            // get billToTaxExemGrpCd
            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd",   glblCmpyCd);
            mapParam.put("billToCustCd", billToCustLocCd);

            String billToTaxExemGrpCd = (String) ssmBatchClient.queryObject("getTaxGrpExemCdFromBillToCust", mapParam);
            if (billToTaxExemGrpCd == null) {
                mapParam.put("acctNum", sellToCustCd);
                billToTaxExemGrpCd = (String) ssmBatchClient.queryObject("getTaxGrpExemCdFromDsAcctCust", mapParam);
            }
            String billToTaxGrpExemReslFlg = null;
            if (billToTaxExemGrpCd != null) {
                DS_TAX_GRP_EXEMTMsg dsTaxGrpExemTmsg = new DS_TAX_GRP_EXEMTMsg();
                setValue(dsTaxGrpExemTmsg.glblCmpyCd,     glblCmpyCd);
                setValue(dsTaxGrpExemTmsg.dsTaxGrpExemCd, billToTaxExemGrpCd);
                dsTaxGrpExemTmsg = (DS_TAX_GRP_EXEMTMsg) S21CacheTBLAccessor.findByKey(dsTaxGrpExemTmsg);
                if (dsTaxGrpExemTmsg != null) {
                    billToTaxGrpExemReslFlg = dsTaxGrpExemTmsg.dsTaxGrpExemReslFlg.getValue();
                }
            }

            DS_INV_TPTMsg dsInvTpTMsg = invBean.getDsInvTpTMsg();
// Del Start 2017/07/18 CSA Defect#19781
//            // get salesrep address from S21_PSN
//            S21_PSNTMsg s21PsnTMsg = new S21_PSNTMsg();
//            mapParam = new HashMap<String, String>();
//            mapParam.put("glblCmpyCd", glblCmpyCd);
//            mapParam.put("tocCd",      invTMsg.slsRepTocCd.getValue());
//            mapParam.put("invDt",      invTMsg.invDt.getValue());
//            S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("getSalesRepAdress", mapParam, s21PsnTMsg);
//
//            int resultCount = ssmRes.getQueryResultCount();
//            // get county name
//            String slsRepCntyNm = null;
//            if (hasValue(s21PsnTMsg.cntyPk)) {
//                slsRepCntyNm = getCountyName(s21PsnTMsg.cntyPk.getValue());
//            }
// Del End 2017/07/18 CSA Defect#19781
            List<INV_BOLBean> invBolBeanList = invBean.getInvBolBeanList();

            // The parameter of Tax Calculation API is made by the unit of INV_BOL.
            for (INV_BOLBean invBolBean : invBolBeanList) {
                INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();

                String invtyLocCd = invBolTMsg.shipFromInvtyLocCd.getValue();
                boolean isCR = "CR".equals(invtyLocCd);

                if (isCR) {
                    // 2017/10/18 QC#21730 Mod Start
                    //INV_LINETMsg invLineTMsg = invBolBean.getInvLineBeanList().get(0).getInvLineTMsg();

                    //Map<String, String> mapRes = getOriginalInvtyLoc(invTMsg.cpoOrdNum.getValue(), invLineTMsg.cpoDtlLineNum.getValue(), invLineTMsg.cpoDtlLineSubNum.getValue());

                    //if (mapRes != null) {
                    //    invtyLocCd = (String) mapRes.get("SHIP_FROM_INVTY_LOC_CD");
                    //}
                    if (ZYPCommonFunc.hasValue(invBolTMsg.origInvtyLocCd)) {
                        invtyLocCd = invBolTMsg.origInvtyLocCd.getValue();
                    }
                    // 2017/10/18 QC#21730 Mod End
                }

                NWZC036101PMsg taxcalcPMsg = new NWZC036101PMsg();
                int taxCalcLineNum = 0;
                setValue(taxcalcPMsg.glblCmpyCd, paramPMsg.glblCmpyCd.getValue());
                setValue(taxcalcPMsg.slsDt, paramPMsg.slsDt.getValue());
                setValue(taxcalcPMsg.xxModeCd,  NWZC036101Constant.PROC_MODE_INVOICE);
                // START 2023/11/10 [QC#61468, ADD]
                if (S21StringUtil.isEquals(DS_INV_TP.INVOICE_SUPPLIES_CONTRACT, invTMsg.dsInvTpCd.getValue())
                        || S21StringUtil.isEquals(DS_INV_TP.CREDIT_SUPPLIES_CONTRACT, invTMsg.dsInvTpCd.getValue())) {
                    setValue(taxcalcPMsg.xxModeCd, NWZC036101Constant.PROC_MODE_QUOTATION);
                }
                // END   2023/11/10 [QC#61468, ADD]
                // QC#26121 del Start
//                // 2017/12/27 QC#22407 add start
//                setValue(taxcalcPMsg.invTpCd, invTMsg.invTpCd.getValue());
//                // 2017/12/27 QC#22407 add end
                // QC#26121 del End
                //Sell To Account Number
                setValue(taxcalcPMsg.dsAcctNum_SE, sellToCustCd);
                //Bill To Account Number
                setValue(taxcalcPMsg.billToAcctNum, billToCustAcctCd);
                //Bill To Location Number
                setValue(taxcalcPMsg.billToLocNum, billToCustLocCd);
                //Bill to  Vertex Group Exemption
                setValue(taxcalcPMsg.dsTaxGrpExemCd_B, billToTaxExemGrpCd);
                //Bill to  Vertex Group Exemption Resale Flg
                setValue(taxcalcPMsg.dsTaxGrpExemReslFlg_B, billToTaxGrpExemReslFlg);
                //Ship To Account Number
                setValue(taxcalcPMsg.dsAcctNum_ST, invBolTMsg.shipToCustAcctCd.getValue());
                //Ship To Location Number
                setValue(taxcalcPMsg.shipToLocNum, invBolTMsg.shipToCustCd.getValue());
                //Ship to Vertex Group Exemption
                mapParam = new HashMap<String, String>();
                mapParam.put("glblCmpyCd",   glblCmpyCd);
                mapParam.put("shipToCustCd", invBolTMsg.shipToCustCd.getValue());

                String shipToTaxExemGrpCd = (String) ssmBatchClient.queryObject("getTaxGrpExemCdFromShipToCust", mapParam);
                if (shipToTaxExemGrpCd == null) {
                    mapParam.put("acctNum", sellToCustCd);
                    shipToTaxExemGrpCd = (String) ssmBatchClient.queryObject("getTaxGrpExemCdFromDsAcctCust", mapParam);
                }
                setValue(taxcalcPMsg.dsTaxGrpExemCd_ST, shipToTaxExemGrpCd);
                //Tax Calculation Flag
                setValue(taxcalcPMsg.taxCalcFlg, dsInvTpTMsg.taxCalcFlg.getValue());
                //Tax Exemption
                setValue(taxcalcPMsg.taxExemFlg, dsInvTpTMsg.taxExemFlg.getValue());
                //Tax Exemption Reason Code
                setValue(taxcalcPMsg.taxExemRsnCd, dsInvTpTMsg.taxExemRsnCd.getValue());
                //Lease Option Code
                setValue(taxcalcPMsg.leasePrchOptCd, invTMsg.leasePrchOptCd.getValue());
                //Transaction Date
                setValue(taxcalcPMsg.trxDt, invTMsg.invDt.getValue());
                //Tax Calculate Header Num
                setValue(taxcalcPMsg.xxTaxCalcHdrNum, invTMsg.invNum.getValue());

                //get SHIP_TO_CUST & DS_SHIP_TO_CUST
                String geoCd = null;
                String dsInsdCtyLimitFlg = null;
                BigDecimal shipToCntyPk = null;
                mapParam = new HashMap<String, String>();
                mapParam.put("glblCmpyCd",   glblCmpyCd);
                mapParam.put("shipToCustCd", invBolTMsg.shipToCustCd.getValue());

                Map<String, Object> mapRes = (Map<String, Object>) ssmBatchClient.queryObject("querySHIP_TO_CUST", mapParam);
                if (mapRes != null) {
                    shipToCntyPk = (BigDecimal) mapRes.get("CNTY_PK");
                    geoCd = (String) mapRes.get("GEO_CD");
                    dsInsdCtyLimitFlg = (String) mapRes.get("DS_INSD_CTY_LIMIT_FLG");
                }
                //Ship To Tax Area ID
                setValue(taxcalcPMsg.geoCd_ST, geoCd);
                //Ship To Inside City Limit Flag
                setValue(taxcalcPMsg.dsInsdCtyLimitFlg_ST, dsInsdCtyLimitFlg);

                setValue(taxcalcPMsg.taxAreaId_ST, getTaxAreaId(glblCmpyCd, invBolTMsg.shipToCtyAddr.getValue(), shipToCntyPk, invBolTMsg.shipToStCd.getValue()));

                //Ship to First Line Address
                setValue(taxcalcPMsg.firstLineAddr_ST, invBolTMsg.shipToFirstLineAddr.getValue());
                //Ship to Second Line Address
                setValue(taxcalcPMsg.scdLineAddr_ST, invBolTMsg.shipToScdLineAddr.getValue());
                //Ship to City Address
                setValue(taxcalcPMsg.ctyAddr_ST, invBolTMsg.shipToCtyAddr.getValue());
                //Ship to State Code
                setValue(taxcalcPMsg.stCd_ST, invBolTMsg.shipToStCd.getValue());
                //Ship To County Name
                setValue(taxcalcPMsg.cntyNm_ST, invBolTMsg.shipToCntyNm.getValue());
                //Ship To Post Code
                setValue(taxcalcPMsg.postCd_ST, invBolTMsg.shipToPostCd.getValue());
                //Ship To Country Code
                setValue(taxcalcPMsg.ctryCd_ST, invBolTMsg.shipToCtryCd.getValue());
                // Add Start 2017/07/25 CSA Defect#20083
                // get salesrep address from S21_PSN
                S21_PSNTMsg s21PsnTMsg = new S21_PSNTMsg();
                mapParam = new HashMap<String, String>();
                mapParam.put("glblCmpyCd", glblCmpyCd);
                mapParam.put("tocCd",      invBolBean.getInvLineBeanList().get(0).getInvLineTMsg().slsRepTocCd.getValue());
                mapParam.put("invDt",      invTMsg.invDt.getValue());
                ssmEzdClient.queryEZDMsg("getSalesRepAdress", mapParam, s21PsnTMsg);
                // get county name
                String slsRepCntyNm = null;
                if (hasValue(s21PsnTMsg.cntyPk)) {
                    slsRepCntyNm = getCountyName(s21PsnTMsg.cntyPk.getValue());
                }
                // Add End  2017/07/25 CSA Defect#20083
                //Sales Rep Tax Area ID
                setValue(taxcalcPMsg.geoCd_SR, s21PsnTMsg.geoCd.getValue());
                //Sales Rep Inside City Limit Flag
                setValue(taxcalcPMsg.dsInsdCtyLimitFlg_SR, s21PsnTMsg.dsInsdCtyLimitFlg.getValue());
                //Sales Rep First Line Address
                setValue(taxcalcPMsg.firstLineAddr_SR, s21PsnTMsg.firstLineAddr.getValue());
                //Sales Rep Second Line Address
                setValue(taxcalcPMsg.scdLineAddr_SR, s21PsnTMsg.scdLineAddr.getValue());
                //Sales Rep City Address
                setValue(taxcalcPMsg.ctyAddr_SR, s21PsnTMsg.ctyAddr.getValue());
                //Sales Rep State Code
                setValue(taxcalcPMsg.stCd_SR, s21PsnTMsg.stCd.getValue());
                //Sales Rep County Name
                setValue(taxcalcPMsg.cntyNm_SR, slsRepCntyNm);
                //Sales Rep Post Code
                setValue(taxcalcPMsg.postCd_SR, s21PsnTMsg.postCd.getValue());
                //Sales Rep Country Code
                setValue(taxcalcPMsg.ctryCd_SR, s21PsnTMsg.ctryCd.getValue());

                // get ship from
                DS_INVTY_LOC_VTMsg dsInvtyLocVTMsg = new DS_INVTY_LOC_VTMsg();
                // Mod Start 2017/07/18 CSA Defect#19781
                //resultCount = 0;
                int resultCount = 0;
                // Mod End   2017/07/18 CSA Defect#19781
                if (hasValue(invtyLocCd)) {
                    // QC#22031 2017/10/17 Mod Start
                    //mapParam = new HashMap<String, String>();
                    //mapParam.put("glblCmpyCd",   glblCmpyCd);
                    //mapParam.put("invtyLocCd", invtyLocCd);
                    // Mod Start 2017/07/18 CSA Defect#19781
                    //ssmRes = ssmEzdClient.queryEZDMsg("queryDS_INVTY_LOC_V", mapParam, dsInvtyLocVTMsg);
                    //S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryDS_INVTY_LOC_V", mapParam, dsInvtyLocVTMsg);
                    // Mod End   2017/07/18 CSA Defect#19781
                    if(isVendorCode(glblCmpyCd, invtyLocCd)){
                        invtyLocCd = DROP_SHIP_WH;
                    }
                    mapParam = new HashMap<String, String>();
                    mapParam.put("glblCmpyCd",   glblCmpyCd);
                    mapParam.put("invtyLocCd", invtyLocCd);
                    S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryDS_INVTY_LOC_V", mapParam, dsInvtyLocVTMsg);
                    resultCount = ssmRes.getQueryResultCount();
                    // QC#22031 2017/10/17 Mod End
                }

                List<INV_LINEBean> invLineBeanList = invBolBean.getInvLineBeanList();

                // QC#21841 2018/05/21 Del Start
                //// ** set freight info **
                //// first line is freight
                //taxCalcInputLinePMsg = (NWZC036101_taxCalculateInputLinePMsg) taxcalcPMsg.taxCalculateInputLine.no(taxCalcLineNum++);
                ////Tax Calculate Line Number
                //setValue(taxCalcInputLinePMsg.xxTaxCalcLineNum_A, Integer.toString(taxCalcLineNum));
                ////Merchandise Code
                //setValue(taxCalcInputLinePMsg.mdseCd_A, frtTaxDummyMdseCd);
                ////Shipped Quantity
                //setValue(taxCalcInputLinePMsg.shipQty_A, BigDecimal.ONE);
                ////Function Net Unit Price Amount
                //setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, invBolTMsg.shipFuncFrtAmt.getValue());
                ////Sales Amount
                //setValue(taxCalcInputLinePMsg.slsAmt_A, invBolTMsg.shipFuncFrtAmt.getValue());
                ////get mdse info
                //getMdseInfoForTaxParam(frtTaxDummyMdseCd, taxCalcInputLinePMsg);
                //
                //if (resultCount != 0) {
                //    //Ship from Tax Area ID
                //    setValue(taxCalcInputLinePMsg.geoCd_AF, dsInvtyLocVTMsg.geoCd.getValue());
                //    //Ship from First Line Address
                //    setValue(taxCalcInputLinePMsg.firstLineAddr_AF, dsInvtyLocVTMsg.firstLineAddr.getValue());
                //    //Ship from Second Line Address
                //    setValue(taxCalcInputLinePMsg.scdLineAddr_AF, dsInvtyLocVTMsg.scdLineAddr.getValue());
                //    //Ship from City Address
                //    setValue(taxCalcInputLinePMsg.ctyAddr_AF, dsInvtyLocVTMsg.ctyAddr.getValue());
                //    //Ship from State Code
                //    setValue(taxCalcInputLinePMsg.stCd_AF, dsInvtyLocVTMsg.stCd.getValue());
                //    //Ship from County Name
                //    setValue(taxCalcInputLinePMsg.cntyNm_AF, getCountyName(dsInvtyLocVTMsg.cntyPk.getValue()));
                //    //Ship from Post Code
                //    setValue(taxCalcInputLinePMsg.postCd_AF, dsInvtyLocVTMsg.postCd.getValue());
                //    //Ship from Country Code
                //    setValue(taxCalcInputLinePMsg.ctryCd_AF, dsInvtyLocVTMsg.ctryCd.getValue());
                //}
                //
                //// Add Start 2017/07/18 CSA Defect#19781
                ////Sales Rep Tax Area ID
                //setValue(taxCalcInputLinePMsg.geoCd_AR, s21PsnTMsg.geoCd.getValue());
                ////Sales Rep Inside City Limit Flag
                //setValue(taxCalcInputLinePMsg.dsInsdCtyLimitFlg_AR, s21PsnTMsg.dsInsdCtyLimitFlg.getValue());
                ////Sales Rep First Line Address
                //setValue(taxCalcInputLinePMsg.firstLineAddr_AR, s21PsnTMsg.firstLineAddr.getValue());
                ////Sales Rep Second Line Address
                //setValue(taxCalcInputLinePMsg.scdLineAddr_AR, s21PsnTMsg.scdLineAddr.getValue());
                ////Sales Rep City Address
                //setValue(taxCalcInputLinePMsg.ctyAddr_AR, s21PsnTMsg.ctyAddr.getValue());
                ////Sales Rep State Code
                //setValue(taxCalcInputLinePMsg.stCd_AR, s21PsnTMsg.stCd.getValue());
                ////Sales Rep County Name
                //setValue(taxCalcInputLinePMsg.cntyNm_AR, slsRepCntyNm);
                ////Sales Rep Post Code
                //setValue(taxCalcInputLinePMsg.postCd_AR, s21PsnTMsg.postCd.getValue());
                ////Sales Rep Country Code
                //setValue(taxCalcInputLinePMsg.ctryCd_AR, s21PsnTMsg.ctryCd.getValue());
                //// Add End  2017/07/18 CSA Defect#19781
                //// ** set freight info **
                // QC#21841 2018/05/21 Del Start
                //
                //taxcalcPMsg.taxCalculateInputLine.setValidCount(taxCalcLineNum);
                // QC#21841 2018/05/21 Del End

                // Tax Calculate Input Line
                for (INV_LINEBean invLineBean : invLineBeanList) {

                    INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

                    if (ZYPConstant.FLG_ON_Y.equals(invLineBean.getSetItemDummyFlg())) {
                        continue;
                    }

                    taxCalcInputLinePMsg = (NWZC036101_taxCalculateInputLinePMsg) taxcalcPMsg.taxCalculateInputLine.no(taxCalcLineNum++);
                    // Add Start  2017/07/18 CSA Defect#19781
                    //Sales Rep Tax Area ID
                    setValue(taxCalcInputLinePMsg.geoCd_AR, s21PsnTMsg.geoCd.getValue());
                    //Sales Rep Inside City Limit Flag
                    setValue(taxCalcInputLinePMsg.dsInsdCtyLimitFlg_AR, s21PsnTMsg.dsInsdCtyLimitFlg.getValue());
                    //Sales Rep First Line Address
                    setValue(taxCalcInputLinePMsg.firstLineAddr_AR, s21PsnTMsg.firstLineAddr.getValue());
                    //Sales Rep Second Line Address
                    setValue(taxCalcInputLinePMsg.scdLineAddr_AR, s21PsnTMsg.scdLineAddr.getValue());
                    //Sales Rep City Address
                    setValue(taxCalcInputLinePMsg.ctyAddr_AR, s21PsnTMsg.ctyAddr.getValue());
                    //Sales Rep State Code
                    setValue(taxCalcInputLinePMsg.stCd_AR, s21PsnTMsg.stCd.getValue());
                    //Sales Rep County Name
                    setValue(taxCalcInputLinePMsg.cntyNm_AR, slsRepCntyNm);
                    //Sales Rep Post Code
                    setValue(taxCalcInputLinePMsg.postCd_AR, s21PsnTMsg.postCd.getValue());
                    //Sales Rep Country Code
                    setValue(taxCalcInputLinePMsg.ctryCd_AR, s21PsnTMsg.ctryCd.getValue());
                    // Add End  2017/07/18 CSA Defect#19781

                    //Tax Calculate Line Number
                    setValue(taxCalcInputLinePMsg.xxTaxCalcLineNum_A, Integer.toString(taxCalcLineNum));
                    //Merchandise Code
                    setValue(taxCalcInputLinePMsg.mdseCd_A, invLineTMsg.mdseCd.getValue());

                    //get mdse info
                    getMdseInfoForTaxParam(invLineTMsg.mdseCd.getValue(), taxCalcInputLinePMsg);

                    //Shipped Quantity
                    setValue(taxCalcInputLinePMsg.shipQty_A, invLineTMsg.shipQty.getValue());
                    //Function Net Unit Price Amount
                    setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, invLineTMsg.funcNetUnitPrcAmt.getValue());
                    //Sales Amount
                    setValue(taxCalcInputLinePMsg.slsAmt_A, invLineTMsg.invLineFuncNetAmt.getValue());

                    //QC#19398
//                    if (Y.equals(invLineBean.getInvtyCtrlFlg())) {
                    if (Y.equals(invLineBean.getInvtyCtrlFlg()) || FIXED_SET_ITEM_CPO_DTL_LINE_SUB_NUM.equals(invLineTMsg.invLineSubNum.getValue())) {
                        if (resultCount != 0) {
                            //Ship from Tax Area ID
                            setValue(taxCalcInputLinePMsg.geoCd_AF, dsInvtyLocVTMsg.geoCd.getValue());
                            //Ship from First Line Address
                            setValue(taxCalcInputLinePMsg.firstLineAddr_AF, dsInvtyLocVTMsg.firstLineAddr.getValue());
                            //Ship from Second Line Address
                            setValue(taxCalcInputLinePMsg.scdLineAddr_AF, dsInvtyLocVTMsg.scdLineAddr.getValue());
                            //Ship from City Address
                            setValue(taxCalcInputLinePMsg.ctyAddr_AF, dsInvtyLocVTMsg.ctyAddr.getValue());
                            //Ship from State Code
                            setValue(taxCalcInputLinePMsg.stCd_AF, dsInvtyLocVTMsg.stCd.getValue());
                            //Ship from County Name
                            setValue(taxCalcInputLinePMsg.cntyNm_AF, getCountyName(dsInvtyLocVTMsg.cntyPk.getValue()));
                            //Ship from Post Code
                            setValue(taxCalcInputLinePMsg.postCd_AF, dsInvtyLocVTMsg.postCd.getValue());
                            //Ship from Country Code
                            setValue(taxCalcInputLinePMsg.ctryCd_AF, dsInvtyLocVTMsg.ctryCd.getValue());
                            // QC#22031 2017/10/31 Add Start
                            setValue(taxCalcInputLinePMsg.whCd_AF, dsInvtyLocVTMsg.rtlWhCd.getValue());
                            // QC#22031 2017/10/31 Add End
                        }
                        // QC#22031 2017/10/31 Del Start
                        //Ship from WH Code
                        //if (hasValue(invtyLocCd)) {
                        //    mapParam.put("glblCmpyCd", glblCmpyCd);
                        //    mapParam.put("invtyLocCd", invtyLocCd);
                        //    String rtlWhCd = (String) ssmBatchClient.queryObject("getRtlWh", mapParam);
                        //    setValue(taxCalcInputLinePMsg.whCd_AF, rtlWhCd);
                        //}
                        // QC#22031 2017/10/31 Del End
                    }
                    taxcalcPMsg.taxCalculateInputLine.setValidCount(taxCalcLineNum);
                }
                taxcalcPMsgList.add(taxcalcPMsg);
            }
        }
        return taxcalcPMsgList;
    }

    @SuppressWarnings("unchecked")
    private String getTaxAreaId(String glblCmpyCd, String shipToCtyAddr, BigDecimal shipToCntyPk, String shipToStCd) {
        String taxAreaId = null;

        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", glblCmpyCd);
        inParam.put("ctyAddr", shipToCtyAddr);
        inParam.put("cntyPk", shipToCntyPk);
        inParam.put("stCd", shipToStCd);

        Map<String, Object> resultMap = (Map<String, Object>)ssmBatchClient.queryObject("getTaxAreaId", inParam);
        if (resultMap == null) {
            inParam.put("ctyAddr", null);
            resultMap = (Map<String, Object>)ssmBatchClient.queryObject("getTaxAreaId", inParam);
        }
        if (resultMap == null) {
            inParam.put("cntyPk", null);
            resultMap = (Map<String, Object>)ssmBatchClient.queryObject("getTaxAreaId", inParam);
        }
        if (resultMap != null) {
            taxAreaId = (String)resultMap.get("TAX_AREA_ID");
        }

        return taxAreaId;
    }

    // 2017/10/18 QC#21730 Del Start
    //@SuppressWarnings("unchecked")
    //private Map<String, String> getOriginalInvtyLoc(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

    //    Map<String, String> mapParam = new HashMap<String, String>();
    //    mapParam.put("glblCmpyCd",          glblCmpyCd);
    //    mapParam.put("cpoOrdNum",           cpoOrdNum);
    //    mapParam.put("cpoDtlLineNum",       cpoDtlLineNum);
    //    mapParam.put("cpoDtlLineSubNum",    cpoDtlLineSubNum);
    //    Map<String, String> mapRes = (Map<String, String>) ssmBatchClient.queryObject("getOriginalInvtyLocCd", mapParam);
    //
    //    if (mapRes != null) {
    //        String origInvtyLocCd       = (String) mapRes.get("SHIP_FROM_INVTY_LOC_CD");
    //        String origCpoOrdNum        = (String) mapRes.get("CPO_ORD_NUM");
    //        String origCpoDtlLineNum    = (String) mapRes.get("CPO_DTL_LINE_NUM");
    //        String origCpoDtlLineSubNum = (String) mapRes.get("CPO_DTL_LINE_SUB_NUM");
    //        if ("CR".equals(origInvtyLocCd)) {
    //            Map<String, String> mapRes2 = getOriginalInvtyLoc(origCpoOrdNum, origCpoDtlLineNum, origCpoDtlLineSubNum);
    //            if (mapRes2 == null) {
    //                // last record is not exist
    //                return mapRes;
    //            } else {
    //                // laset record is found
    //                return mapRes2;
    //            }
    //        } else {
    //            //original
    //            return mapRes;
    //        }
    //    }
    //
    //    return mapRes;
    //}
    // 2017/10/18 QC#21730 Del End

    private void getMdseInfoForTaxParam(String mdseCd, NWZC036101_taxCalculateInputLinePMsg taxCalcInputLinePMsg) {

        MDSETMsg mdseTMsg = getDsMdseInfo(mdseCd);

        String svcAllocTpCd = null;
        String svcAllocTrxTpNm = null;
        String taxExemTpCd  = null;
        if (mdseTMsg != null) {
            svcAllocTpCd = mdseTMsg.svcAllocTpCd.getValue();
            taxExemTpCd  = mdseTMsg.taxExemTpCd.getValue();
        }
        if (svcAllocTpCd != null) {
            SVC_ALLOC_TPTMsg svcAllocTpTMsg = new SVC_ALLOC_TPTMsg();
            setValue(svcAllocTpTMsg.glblCmpyCd,   glblCmpyCd);
            setValue(svcAllocTpTMsg.svcAllocTpCd, svcAllocTpCd);
            svcAllocTpTMsg = (SVC_ALLOC_TPTMsg) S21CacheTBLAccessor.findByKey(svcAllocTpTMsg);
            if (svcAllocTpTMsg != null) {
                svcAllocTrxTpNm = svcAllocTpTMsg.svcAllocTrxTpNm.getValue();
            }
        }
        if (!hasValue(svcAllocTrxTpNm)) {
            // default set if value is null
            svcAllocTrxTpNm = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_DEFAULT_TAX_TRX_TP, glblCmpyCd);
        }

        //Service Allocation Type
        setValue(taxCalcInputLinePMsg.svcAllocTpCd_A, svcAllocTpCd);
        //Trx Type
        setValue(taxCalcInputLinePMsg.svcAllocTrxTpNm_A, svcAllocTrxTpNm);
        //Product Tax Code
        setValue(taxCalcInputLinePMsg.taxExemTpCd_A, taxExemTpCd);

    }
    private MDSETMsg getDsMdseInfo(String mdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        setValue(mdseTMsg.mdseCd,     mdseCd);
        mdseTMsg =(MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            // get mdsecd
            ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
            setValue(ordTakeMdseTMsg.glblCmpyCd, glblCmpyCd);
            setValue(ordTakeMdseTMsg.ordTakeMdseCd, mdseCd);
            ordTakeMdseTMsg =(ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseTMsg);

            mdseTMsg = new MDSETMsg();
            setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
            setValue(mdseTMsg.mdseCd,     ordTakeMdseTMsg.mdseCd.getValue());
            mdseTMsg =(MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        }

        return mdseTMsg;
    }
    private String getCountyName(BigDecimal cntyPk) {

        String cntyNm = null;
        CNTYTMsg cntyTMsg = new CNTYTMsg();
        setValue(cntyTMsg.glblCmpyCd, glblCmpyCd);
        setValue(cntyTMsg.cntyPk, cntyPk);
        cntyTMsg = (CNTYTMsg) S21CacheTBLAccessor.findByKey(cntyTMsg);
        if (cntyTMsg != null) {
            cntyNm = cntyTMsg.cntyNm.getValue();
        }
        return cntyNm;
    }

    private void makingInvoiceStructure(List<INVBean> invBeanList, List<INV_BOLBean> allInvBolBeanList, List<INV_LINEBean> allInvLineBeanList, List<INV_PRMO_INFOBean> invPrmoInfoBeanList) {

        for (INVBean invBean : invBeanList) {
            INVTMsg invTMsg = invBean.getInvTMsg();

            String origInvNum = invTMsg.origInvNum.getValue();
            if (origInvNum == null) {
                origInvNum = "";
            }
            String shipToCustAcctCd = invBean.getShipToCustAcctCd();
            String shipToCustLocCd = invBean.getShipToCustLocCd();
            String shipToCustCtacPsnPk = invBean.getShipToCtacPsnPk();

            Set<INV_LINEBean> processedLineBean = new HashSet<INV_LINEBean>();

            for (INV_BOLBean invBolBean : allInvBolBeanList) {

                if (invTMsg.pmtTermCd.getValue().equals(invBolBean.getPmtTermCd())
                        && invTMsg.cashDiscTermCd.getValue().equals(invBolBean.getCashDiscTermCd())
                        && invTMsg.dealCcyCd.getValue().equals(invBolBean.getDealCcyCd())
                        && invBean.getInvRcpntCustCd().equals(invBolBean.getInvRcpntCustCd())
                        && invTMsg.billToCustAcctCd.getValue().equals(invBolBean.getBillToCustAcctCd())
                        && invTMsg.billToCtacPsnFirstNm.getValue().equals(invBolBean.getBillToCtacPsnFirstNm())
                        && invTMsg.billToCtacPsnMidNm.getValue().equals(invBolBean.getBillToCtacPsnMidNm())
                        && invTMsg.billToCtacPsnLastNm.getValue().equals(invBolBean.getBillToCtacPsnLastNm())
                        && origInvNum.equals(invBolBean.getOrigInvNum())
                        && (shipToCustAcctCd == null
                            || shipToCustAcctCd != null && shipToCustAcctCd.equals(invBolBean.getShipToCustAcctCd()))
                        && (shipToCustLocCd == null
                            || shipToCustLocCd != null && shipToCustLocCd.equals(invBolBean.getShipToCustLocCd()))
                        && (shipToCustCtacPsnPk == null
                            || shipToCustCtacPsnPk != null && shipToCustCtacPsnPk.equals(invBolBean.getShipToCtacPsnPk()))
                ) {

                    invBean.addInvBol(invBolBean);
                    INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();

                    for (INV_LINEBean invLineBean : allInvLineBeanList) {

                        if (processedLineBean.contains(invLineBean)) {
                            continue;
                        }

                        String shipFromInvtyLocCd = invLineBean.getShipFromInvtyLocCd();

                        // 2019/06/03 QC#50654 Add Start
                        String origInvtyLocCd       = invLineBean.getOrigInvtyLocCd();
                        // 2019/06/03 QC#50654 Add End

                        if (invBolBean.getPmtTermCd().equals(invLineBean.getPmtTermCd())
                                && invBolBean.getCashDiscTermCd().equals(invLineBean.getCashDiscTermCd())
                                && invBolBean.getDealCcyCd().equals(invLineBean.getDealCcyCd())
                                && invBolTMsg.soNum.getValue().equals(invLineBean.getSoNum())
                                && invBolTMsg.bolNum.getValue().equals(invLineBean.getBolNum())
                                && invBolTMsg.proNum.getValue().equals(invLineBean.getProNum())
                                && invBolBean.getInvRcpntCustCd().equals(invLineBean.getInvRcpntCustCd())
                                && invBolBean.getBillToCustAcctCd().equals(invLineBean.getBillToCustAcctCd())
                                && invBolBean.getBillToCtacPsnFirstNm().equals(invLineBean.getBillToCtacPsnFirstNm())
                                && invBolBean.getBillToCtacPsnMidNm().equals(invLineBean.getBillToCtacPsnMidNm())
                                && invBolBean.getBillToCtacPsnLastNm().equals(invLineBean.getBillToCtacPsnLastNm())
                                && origInvNum.equals(invLineBean.getOrigInvNum())
                                && (shipToCustAcctCd == null
                                    || shipToCustAcctCd != null && shipToCustAcctCd.equals(invLineBean.getShipToCustAcctCd()))
                                && (shipToCustLocCd == null
                                    || shipToCustLocCd != null && shipToCustLocCd.equals(invLineBean.getShipToCustLocCd()))
                                && (shipToCustCtacPsnPk == null
                                    || shipToCustCtacPsnPk != null && shipToCustCtacPsnPk.equals(invLineBean.getShipToCtacPsnPk()))
                                && (!hasValue(shipFromInvtyLocCd)
                                    || hasValue(shipFromInvtyLocCd) && invBolTMsg.shipFromInvtyLocCd.getValue().equals(shipFromInvtyLocCd))
                                // 2019/06/03 QC#50654 Add Start
                                && (!hasValue(origInvtyLocCd)
                                    || hasValue(origInvtyLocCd) && hasValue(invBolTMsg.origInvtyLocCd.getValue()) && origInvtyLocCd.equals(invBolTMsg.origInvtyLocCd.getValue()))
                                // 2019/06/03 QC#50654 Add End
                        ) {

                            if (invLineBean.getInvBolBean() == null) {
                                invBolBean.addInvLine(invLineBean);
                                invLineBean.setInvBolBean(invBolBean);
                            }

                            // QC#21841 2018/05/24 Add Start
                            if (!INV_LINE_CATG.ITEM.equals(invLineBean.getInvLineTMsg().invLineCatgCd.getValue())){
                                continue;
                            }
                            // QC#21841 2018/05/24 Add End

                            NWZC035001PMsg paramPMsg0 = (NWZC035001PMsg) invLineBean.getMsgMap().getPmsg();

                            for (INV_PRMO_INFOBean invPrmoInfoBean : invPrmoInfoBeanList) {
                                NWZC035001PMsg paramPMsg1 = (NWZC035001PMsg) invPrmoInfoBean.getMsgMap().getPmsg();

                                if (paramPMsg0.cpoDtlLineNum.getValue().equals(paramPMsg1.cpoDtlLineNum.getValue()) && paramPMsg0.cpoDtlLineSubNum.getValue().equals(paramPMsg1.cpoDtlLineSubNum.getValue())
                                        && paramPMsg0.shpgPlnNum.getValue().equals(paramPMsg1.shpgPlnNum.getValue())) {
                                    invLineBean.addAllInvPrmoInfo(invPrmoInfoBean.getInvPrmoInfoTMsgList());
                                    processedLineBean.add(invLineBean);
                                }
                            }
                        }
                    }
                }
            }
        }

        for (INVBean invBean : invBeanList) {

            List<INV_BOLBean> invBolBeanList = invBean.getInvBolBeanList();

            // 2019/06/03 QC#50654 Add Start
            List<INV_BOLBean> delInvBolBeanList = new ArrayList<INV_BOLBean>();
            // 2019/06/03 QC#50654 Add End

            for (INV_BOLBean invBolBean : invBolBeanList) {

                List<INV_LINEBean> invLineBeanList = invBolBean.getInvLineBeanList();
                for (INV_LINEBean invLineBean : invLineBeanList) {

                    INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

                    // START 2018/02/23 J.Kim [QC#24380,MOD]
                    // if (!CR_REBIL.REBILL.equals(invLineBean.crRebilCd())) {
                    if (!CR_REBIL.REBILL.equals(invLineBean.crRebilCd()) && !CR_REBIL.CREDIT.equals(invLineBean.crRebilCd())) {
                        // END 2018/02/23 J.Kim [QC#24380,MOD]
                        if (hasValue(invLineTMsg.csmpContrNum) || hasValue(invLineTMsg.dlrRefNum)) {

                            setValue(invLineTMsg.csmpInvProcStsCd, CSMP_INV_PROC_STS.UNPROCESSED);
                        } else {

                            setValue(invLineTMsg.csmpInvProcStsCd, CSMP_INV_PROC_STS.NONE);
                        }
                    } else {

                        setValue(invLineTMsg.csmpInvProcStsCd, CSMP_INV_PROC_STS.NONE);
                    }
                }
                // 2019/06/03 QC#50654 Add Start
                if ("CR".equals(invBolBean.getInvBolTMsg().shipFromInvtyLocCd.getValue())) {
                    if (invLineBeanList.isEmpty()) {
                    	delInvBolBeanList.add(invBolBean);
                    }
                }
                // 2019/06/03 QC#50654 Add End
            }

            // 2019/06/03 QC#50654 Add Start
            for (INV_BOLBean invBolBean : delInvBolBeanList) {
                invBolBeanList.remove(invBolBean);
                allInvBolBeanList.remove(invBolBean);
            // 2019/06/03 QC#50654 Add End
            }
        }
    }

    private boolean moneyItemCalculationAfterGetTax(List<INVBean> invBeanList) {

        boolean isSuccess = true;

        for (INVBean invBean : invBeanList) {
            BigDecimal invTotDealTaxAmt = ZERO;
            BigDecimal invTotFunctaxAmt = ZERO;
            BigDecimal frtDealTaxAmt = ZERO;
            BigDecimal frtFuncTaxAmt = ZERO;

            INVTMsg invTMsg = invBean.getInvTMsg();
            //boolean crFlg = (INV_TP.CREDIT_MEMO.equals(invTMsg.invTpCd.getValue())); // 2017/10/12 QC#21777 Del

            List<INV_BOLBean> invBolBeanList = invBean.getInvBolBeanList();

            INV_BOLTMsg targetInvBolTMsg = invBolBeanList.get(0).getInvBolTMsg();
            targetInvBolTMsg.frtDealTaxAmt.setValue(ZERO);
            targetInvBolTMsg.frtFuncTaxAmt.setValue(ZERO);

            for (INV_BOLBean invBolBean : invBolBeanList) {
                BigDecimal bolDealTaxAmt = ZERO;
                BigDecimal bolFuncTaxAmt = ZERO;

                List<INV_LINEBean> invLineBeanList = invBolBean.getInvLineBeanList();

                for (INV_LINEBean invLineBean : invLineBeanList) {
                    INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

                    // 2017/10/12 QC#21777 Del Start
                    //if (crFlg) {
                    //    setValue(invLineTMsg.dealNetUnitPrcAmt, invLineTMsg.dealNetUnitPrcAmt.getValue().abs());
                    //    setValue(invLineTMsg.funcNetUnitPrcAmt, invLineTMsg.funcNetUnitPrcAmt.getValue().abs());
                    //    setValue(invLineTMsg.invLineDealTaxAmt, invLineTMsg.invLineDealTaxAmt.getValue().abs());
                    //    setValue(invLineTMsg.invLineDealNetAmt, invLineTMsg.invLineDealNetAmt.getValue().abs());
                    //    setValue(invLineTMsg.invLineFuncTaxAmt, invLineTMsg.invLineDealTaxAmt.getValue().abs());
                    //    setValue(invLineTMsg.invLineFuncNetAmt, invLineTMsg.invLineFuncNetAmt.getValue().abs());
                    //    List<DS_INV_LINE_TAX_DTLTMsg> dsInvLineTaxDtlTMsgList = invLineBean.getDsInvLineTaxDtlList();
                    //    for (DS_INV_LINE_TAX_DTLTMsg dsInvLineTaxDtlTMsg : dsInvLineTaxDtlTMsgList) {
                    //        setValue(dsInvLineTaxDtlTMsg.dealSlsTaxAmt, dsInvLineTaxDtlTMsg.dealSlsTaxAmt.getValue().abs());
                    //        setValue(dsInvLineTaxDtlTMsg.funcSlsTaxAmt, dsInvLineTaxDtlTMsg.funcSlsTaxAmt.getValue().abs());
                    //    }
                    //}
                    // 2017/10/12 QC#21777 Del End

                    invTotDealTaxAmt = invTotDealTaxAmt.add(invLineTMsg.invLineDealTaxAmt.getValue());
                    invTotFunctaxAmt = invTotFunctaxAmt.add(invLineTMsg.invLineFuncTaxAmt.getValue());
                    // QC#21841 2018/05/21 Mod Start
                    //bolDealTaxAmt = bolDealTaxAmt.add(invLineTMsg.invLineDealTaxAmt.getValue());
                    //bolFuncTaxAmt = bolFuncTaxAmt.add(invLineTMsg.invLineFuncTaxAmt.getValue());
                    if (INV_LINE_CATG.FREIGHT.equals(invLineTMsg.invLineCatgCd.getValue())) {
                        frtDealTaxAmt = frtDealTaxAmt.add(invLineTMsg.invLineDealTaxAmt.getValue());
                        frtFuncTaxAmt = frtFuncTaxAmt.add(invLineTMsg.invLineFuncTaxAmt.getValue());
                    } else {
                        bolDealTaxAmt = bolDealTaxAmt.add(invLineTMsg.invLineDealTaxAmt.getValue());
                        bolFuncTaxAmt = bolFuncTaxAmt.add(invLineTMsg.invLineFuncTaxAmt.getValue());
                    }
                    // QC#21841 2018/05/21 Mod End
                }

                INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();
                // QC#21841 2018/05/21 Mod Start
                // invBolTMsg.totBolDealTaxAmt.setValue(bolDealTaxAmt.add(invBolTMsg.frtDealTaxAmt.getValue()));
                // invBolTMsg.totBolFuncTaxAmt.setValue(bolFuncTaxAmt.add(invBolTMsg.frtFuncTaxAmt.getValue()));
                // invTotDealTaxAmt = invTotDealTaxAmt.add(invBolTMsg.frtDealTaxAmt.getValue());
                // invTotFunctaxAmt = invTotFunctaxAmt.add(invBolTMsg.frtFuncTaxAmt.getValue());
                // for aggregate Freight
                if (BigDecimal.ZERO.compareTo(invBolTMsg.shipDealFrtAmt.getValue()) == 0) {
                    targetInvBolTMsg.frtDealTaxAmt.setValue(targetInvBolTMsg.frtDealTaxAmt.getValue().add(frtDealTaxAmt));
                    targetInvBolTMsg.frtFuncTaxAmt.setValue(targetInvBolTMsg.frtFuncTaxAmt.getValue().add(frtFuncTaxAmt));
                    invBolTMsg.frtDealTaxAmt.setValue(ZERO);
                    invBolTMsg.frtFuncTaxAmt.setValue(ZERO);
                    invBolTMsg.totBolDealTaxAmt.setValue(bolDealTaxAmt);
                    invBolTMsg.totBolFuncTaxAmt.setValue(bolFuncTaxAmt);
                } else {
                    invBolTMsg.frtDealTaxAmt.setValue(frtDealTaxAmt);
                    invBolTMsg.frtFuncTaxAmt.setValue(frtFuncTaxAmt);
                    invBolTMsg.totBolDealTaxAmt.setValue(bolDealTaxAmt.add(frtDealTaxAmt));
                    invBolTMsg.totBolFuncTaxAmt.setValue(bolFuncTaxAmt.add(frtFuncTaxAmt));
                    targetInvBolTMsg = invBolTMsg;
                }
                // QC#21841 2018/05/21 Mod End

            }

            S21ApiMessageMap msgMap = invBean.getMsgMap();
            Integer scale = (Integer) ssmBatchClient.queryObject("queryCCY", invTMsg);
            if (scale == null) {
                msgMap.addXxMsgId(NWZM0368E);
                isSuccess = false;
            }

            invTMsg.invTotDealTaxAmt.setValue(invTMsg.invTotDealTaxAmt.getValue().add(invTotDealTaxAmt));
            invTMsg.invTotFuncTaxAmt.setValue(invTMsg.invTotFuncTaxAmt.getValue().add(invTotFunctaxAmt));
            invTMsg.invTotDealNetAmt.setValue(invTMsg.invTotDealNetAmt.getValue().add(invTotDealTaxAmt));
            invTMsg.invTotFuncNetAmt.setValue(invTMsg.invTotFuncNetAmt.getValue().add(invTotFunctaxAmt));
            invBean.setThisTimeInvTotFuncNetAmt(invBean.getThisTimeInvTotFuncNetAmt().add(invTotFunctaxAmt));

            List<INV_CASH_DISC_TERMTMsg> invCashDiscTermTMsgList = invBean.getInvCashDiscTermList();

            BigDecimal invTotDealNetAmt = invTMsg.invTotDealNetAmt.getValue();
            BigDecimal invTotDealFrtAmt = invTMsg.invTotDealFrtAmt.getValue();

            for (INV_CASH_DISC_TERMTMsg invCashDiscTermTMsg : invCashDiscTermTMsgList) {
                BigDecimal invCashDiscRatioPct = invCashDiscTermTMsg.invCashDiscRatioPct.getValue();
                BigDecimal invCashDiscTermAmt = invTotDealNetAmt.subtract(invTotDealNetAmt.subtract(invTotDealFrtAmt).subtract(invTMsg.invTotDealTaxAmt.getValue()).multiply(invCashDiscRatioPct.movePointLeft(2)));
                invCashDiscTermTMsg.invCashDiscAmt.setValue(invCashDiscTermAmt.setScale(scale, HALF_UP));
            }
        }

        return isSuccess;
    }

    private boolean moneyItemCalculationBeforeGetTax(List<INVBean> invBeanList) {

        boolean isSuccess = true;

        boolean isFirstInvBolOfProcess = true;

        for (INVBean invBean : invBeanList) {
            BigDecimal invDealNetAmt = ZERO;
            BigDecimal invTotDealSlsAmt = ZERO;
            BigDecimal invFuncNetAmt = ZERO;
            BigDecimal invTotFuncSlsAmt = ZERO;
            BigDecimal invTotDealDiscAmt = ZERO;
            BigDecimal invTotFuncDiscAmt = ZERO;
            BigDecimal invTotDealFrtAmt = ZERO;
            BigDecimal invTotFuncFrtAmt = ZERO;

            INVTMsg invTMsg = invBean.getInvTMsg();
            S21ApiMessageMap msgMap = invBean.getMsgMap();
            Integer scale = (Integer) ssmBatchClient.queryObject("queryCCY", invTMsg);
            if (scale == null) {
                msgMap.addXxMsgId(NWZM0368E);
                isSuccess = false;
                break;
            }

            Map<String, INV_LINEBean> setParentMap = new HashMap<String, INV_LINEBean>();

            List<INV_BOLBean> invBolBeanList = invBean.getInvBolBeanList();

            for (INV_BOLBean invBolBean : invBolBeanList) {

                BigDecimal shipDealNetAmt = ZERO;
                BigDecimal shipFuncNetAmt = ZERO;
                BigDecimal shipDealSlsAmt = ZERO;
                BigDecimal shipFuncSlsAmt = ZERO;
                BigDecimal shipDealDiscAmt = ZERO;
                BigDecimal shipFuncDiscAmt = ZERO;
                BigDecimal shipDealFrtAmt = ZERO;
                BigDecimal shipFuncFrtAmt = ZERO;

                List<INV_LINEBean> invLineBeanList = invBolBean.getInvLineBeanList();

                for (int i = 0; i < invLineBeanList.size(); i++) {
                    INV_LINEBean invLineBean = invLineBeanList.get(i);
                    INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

                    BigDecimal netRndAmt = BigDecimal.ZERO;
                    List<Map<String, Object>> roundingAmountMapList = getRoundingAdjustAmount(invTMsg, invLineBean);
                    if (roundingAmountMapList != null && !roundingAmountMapList.isEmpty()) {
                        for (Map<String, Object> roundingAmountMap : roundingAmountMapList) {

                            BigDecimal rndAmt = (BigDecimal) roundingAmountMap.get("CALC_PRC_AMT_RATE");
                            netRndAmt = netRndAmt.add(rndAmt.negate());
                        }
                        // QC#26121 2018/06/14 del Start
//                        if (INV_TP.CREDIT_MEMO.equals(invTMsg.invTpCd.getValue())) {
//                            netRndAmt = netRndAmt.negate();
//                        }
                        // QC#26121 2018/06/14 del End
                    }

                    List<INV_PRMO_INFOTMsg> invPrmoInfoList = invLineBean.getInvPrmoInfoList();

                    // QC#26121 2018/06/14 del Start
//                    if (INV_TP.CREDIT_MEMO.equals(invTMsg.invTpCd.getValue())) {
//                        for (INV_PRMO_INFOTMsg invPrmoInfoMsg : invPrmoInfoList) {
//                            setValue(invPrmoInfoMsg.dealUnitPrcAmt, invPrmoInfoMsg.dealUnitPrcAmt.getValue().negate());
//                            setValue(invPrmoInfoMsg.dealLastNetUnitPrcAmt, invPrmoInfoMsg.dealLastNetUnitPrcAmt.getValue().negate());
//                            setValue(invPrmoInfoMsg.dealNetAmt, invPrmoInfoMsg.dealNetAmt.getValue().negate());
//                            setValue(invPrmoInfoMsg.dealDiscAmt, invPrmoInfoMsg.dealDiscAmt.getValue().negate());
//                            setValue(invPrmoInfoMsg.dealPrmoNetUnitPrcAmt, invPrmoInfoMsg.dealPrmoNetUnitPrcAmt.getValue().negate());
//                            setValue(invPrmoInfoMsg.funcUnitPrcAmt, invPrmoInfoMsg.funcUnitPrcAmt.getValue().negate());
//                            setValue(invPrmoInfoMsg.funcLastNetUnitPrcAmt, invPrmoInfoMsg.funcLastNetUnitPrcAmt.getValue().negate());
//                            setValue(invPrmoInfoMsg.funcNetAmt, invPrmoInfoMsg.funcNetAmt.getValue().negate());
//                            setValue(invPrmoInfoMsg.funcDiscAmt, invPrmoInfoMsg.funcDiscAmt.getValue().negate());
//                            setValue(invPrmoInfoMsg.funcPrmoNetUnitPrcAmt, invPrmoInfoMsg.funcPrmoNetUnitPrcAmt.getValue().negate());
//                        }
//                    }
                    // QC#26121 2018/06/14 del Start

                    // QC#21841 2018/05/21 Mod Start
                    INV_PRMO_INFOTMsg invPrmoInfoTMsg = invPrmoInfoList.get(0);

                    if (INV_LINE_CATG.ITEM.equals(invLineTMsg.invLineCatgCd.getValue())) {
                        invLineBean.setDealGrsTotPrcAmt(invPrmoInfoTMsg.dealUnitPrcAmt.getValue().multiply(invPrmoInfoTMsg.prmoQty.getValue()));
                        invLineBean.setFuncGrsTotPrcAmt(invPrmoInfoTMsg.funcUnitPrcAmt.getValue().multiply(invPrmoInfoTMsg.prmoQty.getValue()));                        
                    } else {
                        invLineBean.setDealGrsTotPrcAmt(invPrmoInfoTMsg.dealNetAmt.getValue());
                        invLineBean.setFuncGrsTotPrcAmt(invPrmoInfoTMsg.funcNetAmt.getValue());
                    }
                    // QC#21841 2018/05/21 Mod End

                    if (FIXED_SET_ITEM_CPO_DTL_LINE_SUB_NUM.equals(invLineTMsg.cpoDtlLineSubNum.getValue())) {
                        setParentMap.put(getParentKey(invLineBean, true), invLineBean);
                        if (N.equals(invLineTMsg.setRatioKeepFlg.getValue())) {
                            continue;
                        }
                    }

                    BigDecimal dealDiscUnitPrcAmt = ZERO;
                    BigDecimal funcDiscUnitPrcAmt = ZERO;

                    for (INV_PRMO_INFOTMsg invPrmoInfoMsg : invPrmoInfoList) {
                        dealDiscUnitPrcAmt = dealDiscUnitPrcAmt.add(invPrmoInfoMsg.dealDiscAmt.getValue());
                        funcDiscUnitPrcAmt = funcDiscUnitPrcAmt.add(invPrmoInfoMsg.funcDiscAmt.getValue());
                    }

                    invLineTMsg.dealDiscUnitPrcAmt.setValue(dealDiscUnitPrcAmt);
                    invLineTMsg.funcDiscUnitPrcAmt.setValue(funcDiscUnitPrcAmt);
                    invLineTMsg.dealNetUnitPrcAmt.setValue(invPrmoInfoTMsg.dealLastNetUnitPrcAmt.getValue());
                    invLineTMsg.funcNetUnitPrcAmt.setValue(invPrmoInfoTMsg.funcLastNetUnitPrcAmt.getValue());
                    invLineTMsg.invLineDealNetAmt.setValue(invPrmoInfoTMsg.dealNetAmt.getValue());
                    invLineTMsg.invLineFuncNetAmt.setValue(invPrmoInfoTMsg.funcNetAmt.getValue());
                    invLineTMsg.dealGrsUnitPrcAmt.setValue(invPrmoInfoTMsg.dealUnitPrcAmt.getValue());
                    invLineTMsg.funcGrsUnitPrcAmt.setValue(invPrmoInfoTMsg.funcUnitPrcAmt.getValue());

                    if (Y.equals(invLineBean.getBillWithEquipFlg())) {
                        invLineTMsg.dealGrsTotPrcAmt.setValue(invPrmoInfoTMsg.dealNetAmt.getValue());
                        invLineTMsg.funcGrsTotPrcAmt.setValue(invPrmoInfoTMsg.funcNetAmt.getValue());
                    } else {
                        invLineTMsg.dealGrsTotPrcAmt.setValue(invLineBean.getDealGrsTotPrcAmt());
                        invLineTMsg.funcGrsTotPrcAmt.setValue(invLineBean.getFuncGrsTotPrcAmt());
                    }

                    if (netRndAmt.compareTo(BigDecimal.ZERO) != 0) {
                        invLineTMsg.invLineDealNetAmt.setValue(invLineTMsg.invLineDealNetAmt.getValue().add(netRndAmt));
                        invLineTMsg.invLineFuncNetAmt.setValue(invLineTMsg.invLineFuncNetAmt.getValue().add(netRndAmt));
                        invLineTMsg.dealGrsTotPrcAmt.setValue(invLineTMsg.dealGrsTotPrcAmt.getValue().add(netRndAmt));
                        invLineTMsg.funcGrsTotPrcAmt.setValue(invLineTMsg.funcGrsTotPrcAmt.getValue().add(netRndAmt));
                    }

                    // QC#21841 2018/05/21 Add Start
                    //shipDealNetAmt = shipDealNetAmt.add(invLineTMsg.invLineDealNetAmt.getValue());
                    //shipFuncNetAmt = shipFuncNetAmt.add(invLineTMsg.invLineFuncNetAmt.getValue());
                    //shipDealSlsAmt = shipDealSlsAmt.add(invLineTMsg.dealGrsTotPrcAmt.getValue());
                    //shipFuncSlsAmt = shipFuncSlsAmt.add(invLineTMsg.funcGrsTotPrcAmt.getValue());
                    //shipDealDiscAmt = shipDealDiscAmt.add(invLineTMsg.dealDiscUnitPrcAmt.getValue());
                    //shipFuncDiscAmt = shipFuncDiscAmt.add(invLineTMsg.funcDiscUnitPrcAmt.getValue());
                    //// 2017/10/24 QC#21780 Mod Start
                    ////shipDealFrtAmt = shipDealFrtAmt.add(invLineBean.getShipDealFrtAmt());
                    ////shipFuncFrtAmt = shipFuncFrtAmt.add(invLineBean.getShipFuncFrtAmt());
                    //if (INV_TP.CREDIT_MEMO.equals(invTMsg.invTpCd.getValue())) {
                    //    shipDealFrtAmt = shipDealFrtAmt.add(invLineBean.getShipDealFrtAmt().negate());
                    //    shipFuncFrtAmt = shipFuncFrtAmt.add(invLineBean.getShipFuncFrtAmt().negate());
                    //} else {
                    //    shipDealFrtAmt = shipDealFrtAmt.add(invLineBean.getShipDealFrtAmt());
                    //    shipFuncFrtAmt = shipFuncFrtAmt.add(invLineBean.getShipFuncFrtAmt());
                    // }
                    // 2017/10/24 QC#21780 Mod End
                    if (INV_LINE_CATG.FREIGHT.equals(invLineBean.getInvLineCtgCd())) {
                        shipDealFrtAmt = shipDealFrtAmt.add(invLineTMsg.invLineDealNetAmt.getValue());
                        shipFuncFrtAmt = shipFuncFrtAmt.add(invLineTMsg.invLineFuncNetAmt.getValue());
                    } else {
                        shipDealNetAmt = shipDealNetAmt.add(invLineTMsg.invLineDealNetAmt.getValue());
                        shipFuncNetAmt = shipFuncNetAmt.add(invLineTMsg.invLineFuncNetAmt.getValue());
                        shipDealSlsAmt = shipDealSlsAmt.add(invLineTMsg.dealGrsTotPrcAmt.getValue());
                        shipFuncSlsAmt = shipFuncSlsAmt.add(invLineTMsg.funcGrsTotPrcAmt.getValue());
                        shipDealDiscAmt = shipDealDiscAmt.add(invLineTMsg.dealDiscUnitPrcAmt.getValue());
                        shipFuncDiscAmt = shipFuncDiscAmt.add(invLineTMsg.funcDiscUnitPrcAmt.getValue());
                    }
                    // QC#21841 2018/05/21 Add End

                    BigDecimal splyPgmUnitAmtRate = ZERO;
                    BigDecimal splyPgmShipQty     = ZERO;
                    MDSETMsg mdseTMsg = getDsMdseInfo(invLineTMsg.mdseCd.getValue());
                    if (!Y.equals(invLineBean.getBillWithEquipFlg())) {
                        if (hasValue(mdseTMsg.areaOfPaperNum)) {
                            splyPgmShipQty = invLineTMsg.shipQty.getValue().multiply(mdseTMsg.areaOfPaperNum.getValue());
                            if (!ZERO.equals(splyPgmShipQty)) {
                                // Mod Start 2018/08/03 QC#27430
//                                splyPgmUnitAmtRate = invLineTMsg.dealNetUnitPrcAmt.getValue().divide(splyPgmShipQty, CONST_SPLY_PGM_UNIT_AMT_RATE_SCALE, HALF_UP);
                                splyPgmUnitAmtRate = invLineTMsg.invLineDealNetAmt.getValue().divide(splyPgmShipQty, CONST_SPLY_PGM_UNIT_AMT_RATE_SCALE, HALF_UP);
                                // Mod Start 2018/08/03 QC#27430
                            }
                        }
                        
                        // Add Start 2018/08/07 QC#27438
                        if(ZYPCommonFunc.hasValue(mdseTMsg.easyPackTpCd) && EASY_PACK_TP.EASYPAC_TONER.equals(mdseTMsg.easyPackTpCd.getValue())) {
                            splyPgmUnitAmtRate = invLineTMsg.dealNetUnitPrcAmt.getValue();
                        }
                        // Add End 2018/08/07 QC#27438
                    }

                    else {
                        setValue(invLineTMsg.mdseCd, mdseTMsg.mdseCd);
                    }

                    setValue(invLineTMsg.splyPgmShipQty, splyPgmShipQty);
                    setValue(invLineTMsg.splyPgmUnitAmtRate, splyPgmUnitAmtRate);
                }

                INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();

                // 0 is always set to this item.
                invBolTMsg.shipDealHdlgChrgAmt.setValue(ZERO);
                invBolTMsg.shipFuncHdlgChrgAmt.setValue(ZERO);

                invBolTMsg.shipDealNetAmt.setValue(shipDealNetAmt);
                invBolTMsg.shipFuncNetAmt.setValue(shipFuncNetAmt);
                invBolTMsg.shipDealSlsAmt.setValue(shipDealSlsAmt);
                invBolTMsg.shipFuncSlsAmt.setValue(shipFuncSlsAmt);
                invBolTMsg.shipDealDiscAmt.setValue(shipDealDiscAmt);
                invBolTMsg.shipFuncDiscAmt.setValue(shipFuncDiscAmt);

                String frtChrgMethCd = invBolTMsg.frtChrgMethCd.getValue();
                if (FRT_CHRG_METH.INVOICE_IN_1_5_PCT_CHRG.equals(frtChrgMethCd)) {
                    FRT_CHRG_METHTMsg fcmTMsg = new FRT_CHRG_METHTMsg();
                    fcmTMsg.glblCmpyCd.setValue(glblCmpyCd);
                    fcmTMsg.frtChrgMethCd.setValue(invBolTMsg.frtChrgMethCd.getValue());
                    fcmTMsg = (FRT_CHRG_METHTMsg) S21CacheTBLAccessor.findByKey(fcmTMsg);
                    BigDecimal frtChrgPct = fcmTMsg.frtChrgPct.getValue();

                    invBolTMsg.shipDealFrtAmt.setValue(shipDealNetAmt.multiply(frtChrgPct).divide(new BigDecimal(100)).setScale(scale, HALF_UP));
                    invBolTMsg.shipFuncFrtAmt.setValue(shipFuncNetAmt.multiply(frtChrgPct).divide(new BigDecimal(100)).setScale(scale, HALF_UP));
                } else {
                    invBolTMsg.shipDealFrtAmt.setValue(shipDealFrtAmt);
                    invBolTMsg.shipFuncFrtAmt.setValue(shipFuncFrtAmt);
                }
                if (hasValue(invBolBean.getShpgChrgAmt()) && ZERO.compareTo(invBolBean.getShpgChrgAmt()) != 0) {
                    isSuccess = getFreightFromCPO(invBean, invBolBean, isFirstInvBolOfProcess);
                }

                invDealNetAmt = invDealNetAmt.add(invBolTMsg.shipDealNetAmt.getValue());
                invFuncNetAmt = invFuncNetAmt.add(invBolTMsg.shipFuncNetAmt.getValue());
                invTotDealSlsAmt = invTotDealSlsAmt.add(invBolTMsg.shipDealSlsAmt.getValue());
                invTotFuncSlsAmt = invTotFuncSlsAmt.add(invBolTMsg.shipFuncSlsAmt.getValue());
                invTotDealDiscAmt = invTotDealDiscAmt.add(invBolTMsg.shipDealDiscAmt.getValue());
                invTotFuncDiscAmt = invTotFuncDiscAmt.add(invBolTMsg.shipFuncDiscAmt.getValue());
                invTotDealFrtAmt = invTotDealFrtAmt.add(invBolTMsg.shipDealFrtAmt.getValue());
                invTotFuncFrtAmt = invTotFuncFrtAmt.add(invBolTMsg.shipFuncFrtAmt.getValue());

                isFirstInvBolOfProcess = false;
            }

            invBean.setThisTimeInvTotFuncNetAmt(invFuncNetAmt.add(invTotFuncFrtAmt));
            invTMsg.invTotDealNetAmt.setValue(invTMsg.invTotDealNetAmt.getValue().add(invDealNetAmt).add(invTotDealFrtAmt));
            invTMsg.invTotFuncNetAmt.setValue(invTMsg.invTotFuncNetAmt.getValue().add(invFuncNetAmt).add(invTotFuncFrtAmt));
            invTMsg.invTotDealSlsAmt.setValue(invTMsg.invTotDealSlsAmt.getValue().add(invTotDealSlsAmt));
            invTMsg.invTotFuncSlsAmt.setValue(invTMsg.invTotFuncSlsAmt.getValue().add(invTotFuncSlsAmt));
            invTMsg.invTotDealDiscAmt.setValue(invTMsg.invTotDealDiscAmt.getValue().add(invTotDealDiscAmt));
            invTMsg.invTotFuncDiscAmt.setValue(invTMsg.invTotFuncDiscAmt.getValue().add(invTotFuncDiscAmt));
            invTMsg.invTotDealFrtAmt.setValue(invTMsg.invTotDealFrtAmt.getValue().add(invTotDealFrtAmt));
            invTMsg.invTotFuncFrtAmt.setValue(invTMsg.invTotFuncFrtAmt.getValue().add(invTotFuncFrtAmt));

            if (ZERO.compareTo(invTMsg.invTotDealNetAmt.getValue()) == 0) {
                setInitInvSendStatus(invTMsg);
                // Add Start 2019/01/16 QC#29935
            } else {
                setInvSendStatus(invBean, STS_CD_ONE);
                // Add End 2019/01/16 QC#29935
            }

            aggrigateFreightAmount(invBean);
        }
        return isSuccess;
    }

    private void aggrigateFreightAmount(INVBean invBean) {

        // aggregate freight amount
        BigDecimal dealFrtTotalAmt = BigDecimal.ZERO;
        BigDecimal funcFrtTotalAmt = BigDecimal.ZERO;
        List<INV_BOLBean> invBolBeanList = invBean.getInvBolBeanList();

        String prevSoNum = null;
        String prevBolNum = null;
        String prevProNum = null;
        String prevShipToCustAcctCd    = null;
        String prevShipToCustLocCd     = null;
        BigDecimal prevShipToCustCtacPsnPk = null;
        INV_BOLTMsg targetInvBolTMsg = null;

        for (INV_BOLBean invBolBean : invBolBeanList) {

            INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();

            String shipToCustAcctCd = invBolTMsg.shipToCustAcctCd.getValue();
            String shipToCustCd = invBolTMsg.shipToCustCd.getValue();
            BigDecimal ctacPsnPk = invBolTMsg.ctacPsnPk.getValue();
            if (invBolTMsg.soNum.getValue().equals(prevSoNum)
                && invBolTMsg.bolNum.getValue().equals(prevBolNum)
                && invBolTMsg.proNum.getValue().equals(prevProNum)
                && (shipToCustAcctCd == null
                 || shipToCustAcctCd != null && shipToCustAcctCd.equals(prevShipToCustAcctCd))
                && (shipToCustCd == null
                 || shipToCustCd != null && shipToCustCd.equals(prevShipToCustLocCd))
                && (ctacPsnPk == null
                 || ctacPsnPk != null && ctacPsnPk.equals(prevShipToCustCtacPsnPk))
                ) {
                dealFrtTotalAmt = invBolTMsg.shipDealFrtAmt.getValue();
                funcFrtTotalAmt = invBolTMsg.shipFuncFrtAmt.getValue();
                setValue(invBolTMsg.shipDealFrtAmt, ZERO);
                setValue(invBolTMsg.shipFuncFrtAmt, ZERO);
                setValue(targetInvBolTMsg.shipDealFrtAmt, targetInvBolTMsg.shipDealFrtAmt.getValue().add(dealFrtTotalAmt));
                setValue(targetInvBolTMsg.shipFuncFrtAmt, targetInvBolTMsg.shipFuncFrtAmt.getValue().add(funcFrtTotalAmt));
            } else {
                targetInvBolTMsg = invBolTMsg;
            }
            prevSoNum = invBolTMsg.soNum.getValue();
            prevBolNum = invBolTMsg.bolNum.getValue();
            prevProNum = invBolTMsg.proNum.getValue();
            prevShipToCustAcctCd    = invBolTMsg.shipToCustAcctCd.getValue();
            prevShipToCustLocCd     = invBolTMsg.shipToCustCd.getValue();
            prevShipToCustCtacPsnPk = invBolTMsg.ctacPsnPk.getValue();
        }
        

    }

    private String getParentKey(INV_LINEBean invLineBean, boolean setItemFlg) {
        INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

        StringBuilder sb = new StringBuilder();
        sb.append(invLineTMsg.cpoOrdNum.getValue());
        sb.append("-");
        sb.append(invLineTMsg.cpoDtlLineNum.getValue());
        sb.append("-");
        sb.append(FIXED_SET_ITEM_CPO_DTL_LINE_SUB_NUM);
        sb.append("-");
        if (setItemFlg) {
            sb.append(invLineTMsg.shpgPlnNum.getValue());
        } else {
            sb.append(invLineBean.getSetShpgPlnNum());
        }
        return sb.toString();
    }

    private void setNumbers(List<INVBean> invBeanList, ONBATCH_TYPE onBatchType) {

        for (INVBean invBean : invBeanList) {
            INVTMsg invTMsg = invBean.getInvTMsg();
            String glblCmpyCd = invTMsg.glblCmpyCd.getValue();

            int invBolLineNum = 1;
            if (invBean.isNeedUpdate()) {
                String maxInvBolLineNum = (String) ssmBatchClient.queryObject("queryMAX_INV_BOL_LINE_NUM", invTMsg);
                if (maxInvBolLineNum != null) {
                    invBolLineNum = Integer.parseInt(maxInvBolLineNum) + 1;
                }
            } else {

                DS_INV_TPTMsg dsInvTpTMsg = invBean.getDsInvTpTMsg();
                // START 2017/11/24 E.Kameishi [QC#19735, MOD]
                //invTMsg.invNum.setValue(ZYPExtnNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue()));
                invTMsg.invNum.setValue(ZYPMaxTenDigitsNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue()));
                // END 2017/11/24 E.Kameishi [QC#19735, MOD]

                List<INV_CASH_DISC_TERMTMsg> invCashDiscTermTMsgList = invBean.getInvCashDiscTermList();
                for (INV_CASH_DISC_TERMTMsg invCashDiscTermTMsg : invCashDiscTermTMsgList) {
                    invCashDiscTermTMsg.invNum.setValue(invTMsg.invNum.getValue());
                }
            }

            List<INV_BOLBean> invBolBeanList = invBean.getInvBolBeanList();
            for (INV_BOLBean invBolBean : invBolBeanList) {

                INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();
                invBolTMsg.invNum.setValue(invTMsg.invNum.getValue());
                String updInvBolLineNum = (String) ssmBatchClient.queryObject("queryINV_BOL_LINE_NUM", invBolTMsg);
                if (hasValue(updInvBolLineNum)) {
                    invBolTMsg.invBolLineNum.setValue(updInvBolLineNum);
                } else {
                    invBolTMsg.invBolLineNum.setValue(leftPad(Integer.toString(invBolLineNum++), DIGIT_INV_BOL_LINE_NUM, "0"));
                }

                List<INV_LINEBean> invLineBeanList = invBolBean.getInvLineBeanList();
                Collections.sort(invLineBeanList, new Comparator<INV_LINEBean>() {
                    public int compare(INV_LINEBean arg0, INV_LINEBean arg1) {
                        INV_LINETMsg invLineTMsg0 = arg0.getInvLineTMsg();
                        INV_LINETMsg invLineTMsg1 = arg1.getInvLineTMsg();
                        // QC#21841 2018/05/21 Mod Start
                        //    StringBuilder str0 = new StringBuilder().append(invLineTMsg0.cpoDtlLineNum.getValue()).append(invLineTMsg0.cpoDtlLineSubNum.getValue());
                        //    StringBuilder str1 = new StringBuilder().append(invLineTMsg1.cpoDtlLineNum.getValue()).append(invLineTMsg1.cpoDtlLineSubNum.getValue());
                        StringBuilder str0 = new StringBuilder().append(invLineTMsg0.cpoDtlLineNum.getValue()).append(invLineTMsg0.invLineCatgCd.getValue()).append(invLineTMsg0.cpoDtlLineSubNum.getValue());
                        StringBuilder str1 = new StringBuilder().append(invLineTMsg1.cpoDtlLineNum.getValue()).append(invLineTMsg1.invLineCatgCd.getValue()).append(invLineTMsg1.cpoDtlLineSubNum.getValue());
                        return str0.toString().compareTo(str1.toString());
                        // QC#21841 2018/05/21 Mod End
                    }
                });

                int invLineNum = 0;
                int invLineSubNum = 0;
                String beforeCpoDtlLineNum = "";
                String beforeCpoDtlLineSubNum = "";
                String beforeMdseCd = "";
                String maxInvLineNum = (String) ssmBatchClient.queryObject("queryMAX_INV_LINE_NUM", invBolTMsg);
                if (hasValue(maxInvLineNum)) {
                    invLineNum = Integer.parseInt(maxInvLineNum);
                }
                for (INV_LINEBean invLineBean : invLineBeanList) {
                    INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();
                    invLineTMsg.invNum.setValue(invBolTMsg.invNum.getValue());
                    invLineTMsg.invBolLineNum.setValue(invBolTMsg.invBolLineNum.getValue());
                    NWZC035001PMsg paramPMsg = (NWZC035001PMsg) invLineBean.getMsgMap().getPmsg();
                    paramPMsg.invNum.setValue(invLineTMsg.invNum.getValue());
                    String mdseCd = invLineTMsg.mdseCd.getValue();

                    if (Y.equals(invLineBean.getBillWithEquipFlg())) {
                        invLineNum++;
                        invLineSubNum = 1;
                    // QC#21841 2018/05/21 Add Start
                    } else if (!INV_LINE_CATG.ITEM.equals(invLineBean.getInvLineTMsg().invLineCatgCd.getValue())) {
                        invLineNum++;
                        invLineSubNum = 1;
                    // QC#21841 2018/05/21 Add End
                    } else {

                        if (!invLineTMsg.cpoDtlLineNum.getValue().equals(beforeCpoDtlLineNum)) {
                            invLineNum++;
                        }
                        if (!invLineTMsg.cpoDtlLineSubNum.getValue().equals(beforeCpoDtlLineSubNum)) {
                            if (FIXED_SET_ITEM_CPO_DTL_LINE_SUB_NUM.equals(invLineTMsg.cpoDtlLineSubNum.getValue())) {
                                invLineSubNum = 0;
                            } else if (hasValue(invLineTMsg.setMdseCd)) {
                                if (!mdseCd.equals(beforeMdseCd)) {
                                    invLineSubNum++;
                                }
                            } else {
                                invLineSubNum = 1;
                            }
                        }
                        if (invLineTMsg.cpoDtlLineNum.getValue().equals(beforeCpoDtlLineNum) && invLineTMsg.cpoDtlLineSubNum.getValue().equals(beforeCpoDtlLineSubNum)) {
                            if (hasValue(invLineTMsg.setMdseCd)) {
                                if (!mdseCd.equals(beforeMdseCd)) {
                                    invLineSubNum++;
                                }
                            } else {
                                invLineNum++;
                            }
                        }
                    }
                    INV_LINETMsg tmsg = (INV_LINETMsg) S21ApiTBLAccessor.findByKey(invLineTMsg);
                    if (tmsg != null) {
                        invLineTMsg.invLineNum.setValue(tmsg.invLineNum.getValue());
                        invLineNum--;
                    } else {
                        invLineTMsg.invLineNum.setValue(leftPad(Integer.toString(invLineNum), DIGIT_INV_LINE_NUM, "0"));
                    }
                    invLineTMsg.invLineSubNum.setValue(leftPad(Integer.toString(invLineSubNum), DIGIT_INV_LINE_SUB_NUM, "0"));
                    invLineTMsg.invLineSubTrxNum.setValue(FIXED_INV_LINE_SUB_TRX_NUM);

                    beforeCpoDtlLineNum = invLineTMsg.cpoDtlLineNum.getValue();
                    beforeCpoDtlLineSubNum = invLineTMsg.cpoDtlLineSubNum.getValue();
                    beforeMdseCd = invLineTMsg.mdseCd.getValue();

                    List<INV_PRMO_INFOTMsg> invPrmoInfoList = invLineBean.getInvPrmoInfoList();
                    for (INV_PRMO_INFOTMsg invPrmoInfoTMsg : invPrmoInfoList) {
                        invPrmoInfoTMsg.invNum.setValue(invLineTMsg.invNum.getValue());
                        invPrmoInfoTMsg.invBolLineNum.setValue(invLineTMsg.invBolLineNum.getValue());
                        invPrmoInfoTMsg.invLineNum.setValue(invLineTMsg.invLineNum.getValue());
                        invPrmoInfoTMsg.invLineSubNum.setValue(invLineTMsg.invLineSubNum.getValue());
                        invPrmoInfoTMsg.invLineSubTrxNum.setValue(invLineTMsg.invLineSubTrxNum.getValue());
                        BigDecimal invPrmoInfopk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.INV_PRMO_INFO_SQ);
                        invPrmoInfoTMsg.invPrmoInfoPk.setValue(invPrmoInfopk);
                    }
                }
            }
        }
    }

    private static String createCacheKey(String... args) {

        StringBuilder str = new StringBuilder();
        for (String s : args) {
            str.append(s);
        }
        return str.toString();
    }

    @SuppressWarnings("unused")
    private static String flgToStsCd(String flg) {

        String stsCd = "";

        if (Y.equals(flg)) {
            stsCd = STS_CD_ONE;
        }

        if (N.equals(flg)) {
            stsCd = STS_CD_ZERO;
        }

        return stsCd;
    }

    @SuppressWarnings("unchecked")
    private static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    private static String nvl(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }

    private boolean checkValue(String value, String constValue) {

        if (constValue.startsWith(NOT_EQUALS)) {
            if (!constValue.substring(NOT_EQUALS.length()).equals(value)) {
                return true;
            }
        } else {
            if (constValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private CR_DR_SUB_RSNTMsg findCrDrSubRsnByKey(String glblCmpyCd, String crDrSubRsnCd) {

        CR_DR_SUB_RSNTMsg inMsg = new CR_DR_SUB_RSNTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.crDrSubRsnCd.setValue(crDrSubRsnCd);

        return (CR_DR_SUB_RSNTMsg)S21FastTBLAccessor.findByKey(inMsg);
    }

    private void createSsmParam (StringBuilder sb, NWZC035002PMsg orderDataPMsg) {

        String isNull = " IS NULL";
        if (hasValue(orderDataPMsg.shipToLocNm)) {
            append(sb, "IB.SHIP_TO_LOC_NM = '", getValue(orderDataPMsg.shipToLocNm.getValue()), "'");
        } else {
            append(sb, "IB.SHIP_TO_LOC_NM", isNull);
        }
        if (hasValue(orderDataPMsg.shipToAddlLocNm)) {
            append(sb, " AND IB.SHIP_TO_ADDL_LOC_NM = '", getValue(orderDataPMsg.shipToAddlLocNm.getValue()), "'");
        } else {
            append(sb, " AND IB.SHIP_TO_ADDL_LOC_NM", isNull);
        }
        if (hasValue(orderDataPMsg.shipToFirstLineAddr)) {
            append(sb, " AND IB.SHIP_TO_FIRST_LINE_ADDR = '", getValue(orderDataPMsg.shipToFirstLineAddr.getValue()), "'");
        } else {
            append(sb, " AND IB.SHIP_TO_FIRST_LINE_ADDR", isNull);
        }
        if (hasValue(orderDataPMsg.shipToScdLineAddr)) {
            append(sb, " AND IB.SHIP_TO_SCD_LINE_ADDR = '", getValue(orderDataPMsg.shipToScdLineAddr.getValue()), "'");
        } else {
            append(sb, " AND IB.SHIP_TO_SCD_LINE_ADDR", isNull);
        }
        if (hasValue(orderDataPMsg.shipToThirdLineAddr)) {
            append(sb, " AND IB.SHIP_TO_THIRD_LINE_ADDR = '", getValue(orderDataPMsg.shipToThirdLineAddr.getValue()), "'");
        } else {
            append(sb, " AND IB.SHIP_TO_THIRD_LINE_ADDR", isNull);
        }
        if (hasValue(orderDataPMsg.shipToFrthLineAddr)) {
            append(sb, " AND IB.SHIP_TO_FRTH_LINE_ADDR = '", getValue(orderDataPMsg.shipToFrthLineAddr.getValue()), "'");
        } else {
            append(sb, " AND IB.SHIP_TO_FRTH_LINE_ADDR", isNull);
        }
        if (hasValue(orderDataPMsg.shipToFirstRefCmntTxt)) {
            append(sb, " AND IB.SHIP_TO_FIRST_REF_CMNT_TXT = '", getValue(orderDataPMsg.shipToFirstRefCmntTxt.getValue()), "'");
        } else {
            append(sb, " AND IB.SHIP_TO_FIRST_REF_CMNT_TXT", isNull);
        }
        if (hasValue(orderDataPMsg.shipToScdRefCmntTxt)) {
            append(sb, " AND IB.SHIP_TO_SCD_REF_CMNT_TXT = '", getValue(orderDataPMsg.shipToScdRefCmntTxt.getValue()), "'");
        } else {
            append(sb, " AND IB.SHIP_TO_SCD_REF_CMNT_TXT", isNull);
        }
        if (hasValue(orderDataPMsg.shipToCtyAddr)) {
            append(sb, " AND IB.SHIP_TO_CTY_ADDR = '", getValue(orderDataPMsg.shipToCtyAddr.getValue()), "'");
        } else {
            append(sb, " AND IB.SHIP_TO_CTY_ADDR", isNull);
        }
        if (hasValue(orderDataPMsg.shipToStCd)) {
            append(sb, " AND IB.SHIP_TO_ST_CD = '", getValue(orderDataPMsg.shipToStCd.getValue()), "'");
        } else {
            append(sb, " AND IB.SHIP_TO_ST_CD", isNull);
        }
        if (hasValue(orderDataPMsg.shipToPostCd)) {
            append(sb, " AND IB.SHIP_TO_POST_CD = '", getValue(orderDataPMsg.shipToPostCd.getValue()), "'");
        } else {
            append(sb, " AND IB.SHIP_TO_POST_CD", isNull);
        }
        if (hasValue(orderDataPMsg.shipToCtryCd)) {
            append(sb, " AND IB.SHIP_TO_CTRY_CD = '", getValue(orderDataPMsg.shipToCtryCd.getValue()), "'");
        } else {
            append(sb, " AND IB.SHIP_TO_CTRY_CD", isNull);
        }
    }

    private void append(StringBuilder sb, String... strs) {
        for (String str : strs) {
            sb.append(str);
        }
    }

    private String getValue(String item) {

        return item.replaceAll("'", "''");
    }

    @SuppressWarnings("unchecked")
    private boolean insertDS_INV_LINE_TAX_DTL(List<INVBean> invBeanList) {

        Map<String, String> mapParam;
        for (INVBean invBean : invBeanList) {
            //QC#24705 add Start
            if (!invBean.getLastInvTpCd().equals(invBean.getInvTMsg().invTpCd.getValue())) {
                mapParam = new HashMap<String, String>();
                mapParam.put("glblCmpyCd",       glblCmpyCd);
                mapParam.put("invNum",           invBean.getInvTMsg().invNum.getValue());
                List<Map<String, BigDecimal>> mapResList = (ArrayList<Map<String, BigDecimal>>) ssmBatchClient.queryObjectList("queryDS_INV_LINE_TAX_DTL_forUpdate", mapParam);

                for (Map<String, BigDecimal> mapRes : mapResList) {
                    DS_INV_LINE_TAX_DTLTMsg tmsg = new DS_INV_LINE_TAX_DTLTMsg();
                    setValue(tmsg.glblCmpyCd, glblCmpyCd);
                    setValue(tmsg.dsInvLineTaxDtlPk, mapRes.get("DS_INV_LINE_TAX_DTL_PK"));
                    setValue(tmsg.dealSlsTaxAmt, mapRes.get("DEAL_SLS_TAX_AMT"));
                    setValue(tmsg.funcSlsTaxAmt, mapRes.get("FUNC_SLS_TAX_AMT"));
                    negateInvAmt(tmsg);

                    S21ApiTBLAccessor.updateSelectionField(tmsg, new String[]{"dealSlsTaxAmt", "funcSlsTaxAmt"});
                    if (RTNCD_NOT_FOUND.equals(tmsg.getReturnCode())) {
                        invBean.getMsgMap().addXxMsgId(NWZM1512E);
                        return false;
                    }
                }
            }
            //QC#24705 add End

            List<INV_BOLBean> invBolBeanList = invBean.getInvBolBeanList();
            for (INV_BOLBean invBolBean : invBolBeanList) {

                List<INV_LINEBean> invLineBeanList = invBolBean.getInvLineBeanList();
                for (INV_LINEBean invLineBean : invLineBeanList) {
                    S21ApiMessageMap msgMap = invLineBean.getMsgMap();

                    if (ZYPConstant.FLG_ON_Y.equals(invLineBean.getSetItemDummyFlg())) {
                        continue;
                    }
                    List<DS_INV_LINE_TAX_DTLTMsg> dsInvLineTaxDtlTMsgList = invLineBean.getDsInvLineTaxDtlList();
                    if (dsInvLineTaxDtlTMsgList == null) {
                        continue;
                    }
                    for (DS_INV_LINE_TAX_DTLTMsg dsInvLineTaxDtlTMsg : dsInvLineTaxDtlTMsgList) {

                        mapParam = new HashMap<String, String>();
                        mapParam.put("glblCmpyCd",       glblCmpyCd);
                        mapParam.put("invNum",           dsInvLineTaxDtlTMsg.invNum.getValue());
                        mapParam.put("invBolLineNum",    dsInvLineTaxDtlTMsg.invBolLineNum.getValue());
                        mapParam.put("invLineNum",       dsInvLineTaxDtlTMsg.invLineNum.getValue());
                        mapParam.put("invLineSubNum",    dsInvLineTaxDtlTMsg.invLineSubNum.getValue());
                        mapParam.put("invTrxLineSubNum", dsInvLineTaxDtlTMsg.invTrxLineSubNum.getValue());
                        mapParam.put("dsSlsTaxTpCd",     dsInvLineTaxDtlTMsg.dsSlsTaxTpCd.getValue());
                        BigDecimal pk = (BigDecimal) ssmBatchClient.queryObject("queryDS_INV_LINE_TAX_DTL", mapParam);
                        //QC#26121 2018/06/14 add Start
                        if (INV_TP.CREDIT_MEMO.equals(invLineBean.getInvTpCd())) {
                            negateInvAmt(dsInvLineTaxDtlTMsg);
                        }
                        //QC#26121 2018/06/14 add End

                        if (pk != null) {
                            //update
                            setValue(dsInvLineTaxDtlTMsg.dsInvLineTaxDtlPk, pk);
                            S21ApiTBLAccessor.update(dsInvLineTaxDtlTMsg);
                            if (RTNCD_NOT_FOUND.equals(dsInvLineTaxDtlTMsg.getReturnCode())) {
                                msgMap.addXxMsgId(NWZM1512E);
                                return false;
                            }
                        } else {
                            //insert
                            setValue(dsInvLineTaxDtlTMsg.dsInvLineTaxDtlPk,
                                    ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_INV_LINE_TAX_DTL_SQ));
                            S21ApiTBLAccessor.insert(dsInvLineTaxDtlTMsg);
                            if (RTNCD_DUPLICATE.equals(dsInvLineTaxDtlTMsg.getReturnCode())) {
                                msgMap.addXxMsgId(NWZM1512E);
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    private boolean insertDS_INV_LINE_SER_NUM(List<INVBean> invBeanList) {

        Map<String, String> mapParam;

        for (INVBean invBean : invBeanList) {

            List<INV_BOLBean> invBolBeanList = invBean.getInvBolBeanList();
            for (INV_BOLBean invBolBean : invBolBeanList) {
                INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();

                List<INV_LINEBean> invLineBeanList = invBolBean.getInvLineBeanList();
                for (INV_LINEBean invLineBean : invLineBeanList) {
                    if (Y.equals(invLineBean.getBillWithEquipFlg())) {
                        continue;
                    }
                    if (ZYPConstant.FLG_ON_Y.equals(invLineBean.getSetItemDummyFlg())) {
                        continue;
                    }
                    S21ApiMessageMap msgMap = invLineBean.getMsgMap();
                    INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

                    List<String> serNumList = null;
                    if (hasValue(invLineBean.getSoSlpNum())) {
                        // get serial number from SHIP_SER_NUM
                        mapParam = new HashMap<String, String>();
                        mapParam.put("glblCmpyCd", glblCmpyCd);
                        mapParam.put("mdseCd",     invLineTMsg.mdseCd.getValue());
                        mapParam.put("soNum",      invBolTMsg.soNum.getValue());
                        mapParam.put("soSlpNum",   invLineBean.getSoSlpNum());
                        serNumList = (ArrayList<String>) ssmBatchClient.queryObjectList("querySHIP_SER_NUM", mapParam);
                    }
                    if (serNumList == null || serNumList.isEmpty()) {
                        // get serial number from PO_SER_NUM
                        mapParam = new HashMap<String, String>();
                        mapParam.put("glblCmpyCd",  glblCmpyCd);
                        mapParam.put("keyInfoCd01", invLineTMsg.cpoOrdNum.getValue());
                        mapParam.put("keyInfoCd02", invLineTMsg.cpoDtlLineNum.getValue());
                        mapParam.put("keyInfoCd03", invLineTMsg.cpoDtlLineSubNum.getValue());
                        serNumList = (ArrayList<String>) ssmBatchClient.queryObjectList("queryPO_SER_NUM", mapParam);
                    }

                    if (serNumList == null || serNumList.isEmpty()) {
                        // get serial number from DS_CPO_DTL
                        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                        setValue(cpoDtlTMsg.glblCmpyCd, glblCmpyCd);
                        setValue(cpoDtlTMsg.cpoOrdNum, invLineTMsg.cpoOrdNum);
                        setValue(cpoDtlTMsg.cpoDtlLineNum, invLineTMsg.cpoDtlLineNum);
                        setValue(cpoDtlTMsg.cpoDtlLineSubNum, invLineTMsg.cpoDtlLineSubNum);
                        cpoDtlTMsg = (CPO_DTLTMsg)S21FastTBLAccessor.findByKey(cpoDtlTMsg);
                        if (cpoDtlTMsg != null) {
                            String serNum = cpoDtlTMsg.serNum.getValue();
                            if (hasValue(serNum)) {
                                serNumList.add(serNum);
                            }
                        }
                    }

                    if (serNumList.isEmpty()) {
                        continue;
                    }
                    DS_INV_LINE_SER_NUMTMsg dsInvLineSerNumTMsg = new DS_INV_LINE_SER_NUMTMsg();
                    setValue(dsInvLineSerNumTMsg.glblCmpyCd,       glblCmpyCd);
                    setValue(dsInvLineSerNumTMsg.invNum,           invLineTMsg.invNum.getValue());
                    setValue(dsInvLineSerNumTMsg.invBolLineNum,    invBolTMsg.invBolLineNum.getValue());
                    setValue(dsInvLineSerNumTMsg.invLineNum,       invLineTMsg.invLineNum.getValue());
                    setValue(dsInvLineSerNumTMsg.invLineSubNum,    invLineTMsg.invLineSubNum.getValue());
                    setValue(dsInvLineSerNumTMsg.invTrxLineSubNum, invLineTMsg.invLineSubTrxNum.getValue());
                    setValue(dsInvLineSerNumTMsg.mdseCd,           invLineTMsg.mdseCd.getValue());

                    for (String serNum : serNumList) {
                        if (serNum != null) {
                            setValue(dsInvLineSerNumTMsg.serNum,   serNum);
                            // exist check
                            mapParam = new HashMap<String, String>();
                            mapParam.put("glblCmpyCd",       glblCmpyCd);
                            mapParam.put("invNum",           dsInvLineSerNumTMsg.invNum.getValue());
                            mapParam.put("invBolLineNum",    dsInvLineSerNumTMsg.invBolLineNum.getValue());
                            mapParam.put("invLineNum",       dsInvLineSerNumTMsg.invLineNum.getValue());
                            mapParam.put("invLineSubNum",    dsInvLineSerNumTMsg.invLineSubNum.getValue());
                            mapParam.put("invTrxLineSubNum", dsInvLineSerNumTMsg.invTrxLineSubNum.getValue());
                            mapParam.put("mdseCd",           dsInvLineSerNumTMsg.mdseCd.getValue());
                            mapParam.put("serNum",           dsInvLineSerNumTMsg.serNum.getValue());
                            BigDecimal pk = (BigDecimal) ssmBatchClient.queryObject("queryDS_INV_LINE_SER_NUM", mapParam);

                            if (pk != null) {
                                //update
                                setValue(dsInvLineSerNumTMsg.dsInvLineSerNumPk, pk);
                                S21ApiTBLAccessor.update(dsInvLineSerNumTMsg);
                                if (RTNCD_NOT_FOUND.equals(dsInvLineSerNumTMsg.getReturnCode())) {
                                    msgMap.addXxMsgId(NWZM1511E);
                                    return false;
                                }
                            } else {
                                //insert
                                setValue(dsInvLineSerNumTMsg.dsInvLineSerNumPk,
                                        ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_INV_LINE_SER_NUM_SQ));
                                S21ApiTBLAccessor.insert(dsInvLineSerNumTMsg);
                                if (RTNCD_DUPLICATE.equals(dsInvLineSerNumTMsg.getReturnCode())) {
                                    msgMap.addXxMsgId(NWZM1511E);
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean getFuncCcyScale(S21ApiMessageMap msgMap) {
        CCYTMsg ccyTMsg = new CCYTMsg();
        ccyTMsg.glblCmpyCd.setValue(glblCmpyCd);
        ccyTMsg.ccyCd.setValue(funcCcyCd);

        ccyTMsg = (CCYTMsg) S21CodeTableAccessor.findByKey(ccyTMsg);
        if (ccyTMsg == null || ccyTMsg.aftDeclPntDigitNum.getValue() == null) {
            msgMap.addXxMsgId(NWZM0368E);
            return false;
        }

        this.funcCcyScale = ccyTMsg.aftDeclPntDigitNum.getValue().intValue();
        return true;
    }

    @SuppressWarnings("unused")
    private BigDecimal add(BigDecimal val1, BigDecimal val2) {
        if (val1 == null && val2 == null) {
            return BigDecimal.ZERO;
        } else if (val1 == null && val2 != null) {
            return val2;
        } else if (val1 != null && val2 == null) {
            return val1;
        } else {
            return val1.add(val2);
        }

    }

    private boolean addInvLineForBillWithEquipmentData(List<INVBean> invBeanList, List<INV_BOLBean> allInvBeanBolList, List<INV_LINEBean> allInvLineBeanList) {

        boolean isSuccess = true;

        for (INVBean invBean : invBeanList) {
            INVTMsg invTMsg = invBean.getInvTMsg();

            List<INV_BOLBean> invBolBeanList = invBean.getInvBolBeanList();
            for (INV_BOLBean invBolBean : invBolBeanList) {

                List<INV_LINEBean> addInvLineBeanList = new ArrayList<INV_LINEBean>();
                List<INV_LINEBean> invLineBeanList = invBolBean.getInvLineBeanList();
                List<BigDecimal> fleetLineList = new ArrayList<BigDecimal>();
                for (INV_LINEBean invLineBean : invLineBeanList) {

                    INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();
                    Map<String, String> mapParam = new HashMap<String, String>();
                    mapParam.put("glblCmpyCd",       glblCmpyCd);
                    mapParam.put("cpoOrdNum",        invTMsg.cpoOrdNum.getValue());
                    mapParam.put("cpoDtlLineNum",    invLineTMsg.cpoDtlLineNum.getValue());
                    mapParam.put("cpoDtlLineSubNum", invLineTMsg.cpoDtlLineSubNum.getValue());
                    // Mod Start  2017/07/25 CSA Defect#19783
                    //BigDecimal dsContrDtlPk = (BigDecimal) ssmBatchClient.queryObject("getBillWithEquipmentTargetCpoSvcDtlPk", mapParam);
                    List<Map<String, Object>> ssmResList = (List<Map<String, Object>>)  ssmBatchClient.queryObjectList("getBillWithEquipmentTargetCpoSvcDtlPk", mapParam);
                    //if (dsContrDtlPk == null) {
                    if (ssmResList == null || ssmResList.isEmpty()) {
                        continue;
                    }
                    for (Map<String, Object> mapRes : ssmResList) {
                        BigDecimal dsContrDtlPk = BigDecimal.ZERO;
                        BigDecimal fleetContrDtlPk = null;          // QC#20605 add
                        boolean fleetFlg = false;
                        String dsContrCatgCd = (String) mapRes.get("DS_CONTR_CATG_CD");
                        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                            BigDecimal dsContrPk = (BigDecimal) mapRes.get("DS_CONTR_PK");
                            Map<String, Object> mapParam2 = new HashMap<String, Object>();
                            mapParam2.put("glblCmpyCd",    glblCmpyCd);
                            mapParam2.put("cpoOrdNum",     invTMsg.cpoOrdNum.getValue());
                            mapParam2.put("dsContrPk",     dsContrPk);
                            dsContrDtlPk = (BigDecimal) ssmBatchClient.queryObject("getBillWithEquipmentTargetCpoSvcDtlPkFleet", mapParam2);
                            if (dsContrDtlPk != null) {
                                fleetFlg = true;
                                continue;
                            }
                            // QC#20605 add Start
                            mapParam2.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);
                            fleetContrDtlPk = (BigDecimal) ssmBatchClient.queryObject("getBillWithEquipmentTargetCpoSvcDtlPkFleet", mapParam2);
                            // QC#20605 add End

                            mapParam2.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
                            dsContrDtlPk = (BigDecimal) ssmBatchClient.queryObject("getBillWithEquipmentTargetCpoSvcDtlPkFleet", mapParam2);
                            if (fleetLineList.isEmpty()) {
                                fleetLineList.add(dsContrPk);
                            } else {
                                for (BigDecimal fleetPk: fleetLineList) {
                                    if (fleetPk.compareTo(dsContrPk) == 0) {
                                        fleetFlg = true;
                                        break;
                                    }
                                }
                                if (!fleetFlg) {
                                    fleetLineList.add(dsContrPk);
                                }
                            }
                        } else {
                            dsContrDtlPk = (BigDecimal) mapRes.get("DS_CONTR_DTL_PK");
                        }

                        if (fleetFlg) {
                            continue;
                        }

                        if (billOnlyLineCategCd == null) {
                            mapParam.put("glblCmpyCd",              glblCmpyCd);
                            mapParam.put("ordLineCtxTpCdBillOnly",  "BILL_ONLY");
                            billOnlyLineCategCd = (String) ssmBatchClient.queryObject("getBillOnlyLineCatgCd", mapParam);
                        }

                        if (!callContractInquiryApi(invBean, invLineBean)) {
                            continue;
                        }

                        // ***** service price *****
//                        addInvLineForCpoSvcPrc(dsContrDtlPk, invLineBean, invTMsg, addInvLineBeanList, allInvLineBeanList);
                        addInvLineForCpoSvcPrc(dsContrDtlPk, fleetContrDtlPk, invLineBean, invTMsg, addInvLineBeanList, allInvLineBeanList);

                        // QC#20605 add Start
                        if (hasValue(fleetContrDtlPk)) {
                            continue;
                        }
                        // QC#20605 add End

                        // ***** additional base charge *****
                        addInvLineForCpoSvcAddlBasePrc(dsContrDtlPk, invLineBean, invTMsg, addInvLineBeanList, allInvLineBeanList);
                        
                        //***** additional service charge *****
                        addInvLineForCpoSvcAddlSvcChrgPrc(dsContrDtlPk, invLineBean, invTMsg, addInvLineBeanList, allInvLineBeanList);
                    }
                    // Update billWithEquipInvdFlg
                    if (!updateBillWithEquipInvdFlg(invLineBean)) {
                        continue;
                    }
                    // Mod End  2017/07/25 CSA Defect#19783
                }
                if (!addInvLineBeanList.isEmpty()) {
                    invBolBean.getInvLineBeanList().addAll(addInvLineBeanList);
                }
            }
        }

        return isSuccess;
    }

    private boolean updateBillWithEquipInvdFlg(INV_LINEBean invLineBean) {

        NSZC115001PMsg contrImptApiMsg = new NSZC115001PMsg();
        INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

        NSZC115001_svcConfigRefListPMsg configRefMsg = contrImptApiMsg.svcConfigRefList.no(0);
        setValue(configRefMsg.cpoOrdNum,        invLineTMsg.cpoOrdNum.getValue());
        setValue(configRefMsg.cpoDtlLineNum,    invLineTMsg.cpoDtlLineNum.getValue());
        setValue(configRefMsg.cpoDtlLineSubNum, invLineTMsg.cpoDtlLineSubNum.getValue());
        contrImptApiMsg.svcConfigRefList.setValidCount(1);
        
        boolean isSuccess = true;
        S21ApiMessageMap msgMap = invLineBean.getMsgMap();
        setValue(contrImptApiMsg.glblCmpyCd,    glblCmpyCd);
        setValue(contrImptApiMsg.xxProcMd,      NSZC115001Constant.PROC_MODE_BW_EQUIP);

        NSZC115001 contrImptApi = new NSZC115001();
        contrImptApi.execute(contrImptApiMsg, ONBATCH_TYPE.BATCH);

        if (contrImptApiMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < contrImptApiMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(contrImptApiMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            isSuccess = false;
        }
        return isSuccess;
    }

    private boolean callContractInquiryApi(INVBean invBean, INV_LINEBean invLineBean) {

        boolean isSuccess = true;
        S21ApiMessageMap msgMap         = invLineBean.getMsgMap();
        INVTMsg invTMsg                 = invBean.getInvTMsg();
        INV_LINETMsg invLineTMsg        = invLineBean.getInvLineTMsg();

        //Call Contract Inquiry API
        NSZC111001PMsg pMsg = new NSZC111001PMsg();
        setValue(pMsg.glblCmpyCd,       glblCmpyCd);
        setValue(pMsg.xxModeCd,         NSZC111001Constant.BILL_WITH_EQUIP_MODE);
        setValue(pMsg.slsDt,            invTMsg.invDt);
        setValue(pMsg.cpoOrdNum,        invLineTMsg.cpoOrdNum.getValue());
        setValue(pMsg.cpoDtlLineNum,    invLineTMsg.cpoDtlLineNum.getValue());
        setValue(pMsg.cpoDtlLineSubNum, invLineTMsg.cpoDtlLineSubNum.getValue());
        setValue(pMsg.billWithEquipFlg, ZYPConstant.FLG_ON_Y);
        setValue(pMsg.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
        new NSZC111001().execute(pMsg, ONBATCH_TYPE.BATCH);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            isSuccess = false;
        } else {
            NSZC111001_xxNonWtyContrListPMsg outMsg = (NSZC111001_xxNonWtyContrListPMsg) pMsg.xxNonWtyContrList.get(0);
            setValue(invLineTMsg.svcMachMstrPk,   outMsg.svcMachMstrPk);
            setValue(invLineTMsg.dsContrNum,      outMsg.dsContrNum);
            setValue(invLineTMsg.dsContrSqNum,    outMsg.dsContrSqNum);
            invLineBean.setDsContrNum(invLineTMsg.dsContrNum.getValue());
            invLineBean.setDsContrSqNum(invLineTMsg.dsContrSqNum.getValue());
        }
        return isSuccess;
    }

    @SuppressWarnings("unchecked")
//    private void addInvLineForCpoSvcPrc (BigDecimal dsContrDtlPk, INV_LINEBean invLineBean, INVTMsg invTMsg, List<INV_LINEBean> addInvLineBeanList, List<INV_LINEBean> allInvLineBeanList) {
    private void addInvLineForCpoSvcPrc (BigDecimal dsContrDtlPk, BigDecimal fleetContrDtlPk,INV_LINEBean invLineBean, INVTMsg invTMsg, List<INV_LINEBean> addInvLineBeanList, List<INV_LINEBean> allInvLineBeanList) { // QC#20605 mod

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd",       glblCmpyCd);
        mapParam.put("dsContrDtlPk",      dsContrDtlPk);

        List<String> dsContrDtlTpCdList = new ArrayList<String>();
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.BASE_ONLY);
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.BASE_AND_USAGE);
        // Add Start  2017/07/25 CSA Defect#19783
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        // Add End    2017/07/25 CSA Defect#19783
        mapParam.put("dsContrDtlTpCdList", dsContrDtlTpCdList.toArray(new String[0]));

        List<Map<String, Object>> ssmResList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("queryCPO_SVC_PRC", mapParam);

        for (Map<String, Object> mapRes : ssmResList) {

            BigDecimal basePrcDealAmt = (BigDecimal) mapRes.get("BASE_PRC_DEAL_AMT");
            String svcPrcCatgCd       = (String) mapRes.get("SVC_PRC_CATG_CD");
            String baseBllgCycleCd    = (String) mapRes.get("BASE_BLLG_CYCLE_CD");
            String cpoSvcMdseCd       = (String) mapRes.get("SVC_PGM_MDSE_CD");
            BigDecimal termMthNum     = (BigDecimal) mapRes.get("CONTR_DURN_AOT");

            //create invoice line
            INV_LINEBean addInvLineBean   = new INV_LINEBean(invLineBean);
            INV_LINETMsg addInvLineTMsg   = addInvLineBean.getInvLineTMsg();

            // QC#22495 2017/11/15 Mod Start
            // BigDecimal lineAmt = getLineAmtForBillWithEquipment(termMthNum, baseBllgCycleCd, basePrcDealAmt); // QC#18113
            // BigDecimal unitPrcAmt = lineAmt.divide(termMthNum, funcCcyScale, HALF_UP);
            //
            // setValue(addInvLineTMsg.ordQty,  termMthNum);
            // setValue(addInvLineTMsg.shipQty, termMthNum);

            BigDecimal qty = getLineQtyForBillWithEquipment(termMthNum, baseBllgCycleCd);
            BigDecimal lineAmt =BigDecimal.ZERO;
            BigDecimal unitPrcAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(basePrcDealAmt)) {
                lineAmt = basePrcDealAmt.multiply(qty).setScale(funcCcyScale, HALF_UP);
                unitPrcAmt = basePrcDealAmt;
            }

            setValue(addInvLineTMsg.ordQty,  qty);
            setValue(addInvLineTMsg.shipQty, qty);
            // QC#22495 2017/11/15 Mod End
            setValue(addInvLineTMsg.boQty,   BigDecimal.ZERO);
            setValue(addInvLineTMsg.dealNetUnitPrcAmt, unitPrcAmt);

            setValue(addInvLineTMsg.invLineDealNetAmt, lineAmt);

            //setValue(addInvLineTMsg.shipCmplCostAmt, BigDecimal.ZERO); // 2017/10/23 QC#20719 Del
            setValue(addInvLineTMsg.dealGrsUnitPrcAmt, unitPrcAmt);

            setValue(addInvLineTMsg.dealGrsUnitPrcAmt, lineAmt);

            setValue(addInvLineTMsg.mdseCd, cpoSvcMdseCd);

            // QC#20605 mod Start
//            setValue(addInvLineTMsg.dsContrDtlPk, dsContrDtlPk);
            if (hasValue(fleetContrDtlPk)) {
                setValue(addInvLineTMsg.dsContrDtlPk, fleetContrDtlPk);
            } else {
                setValue(addInvLineTMsg.dsContrDtlPk, dsContrDtlPk);
            }
            // QC#20605 mod End
            setValue(addInvLineTMsg.svcPrcCatgCd, svcPrcCatgCd);

            setValue(addInvLineTMsg.dsOrdPosnNum, invLineBean.getDsOrdPosnNum());
            addInvLineBean.setDsOrdPosnNum(invLineBean.getDsOrdPosnNum());
            //get trx,trxRsn
            getSvcTrxCd(addInvLineBean);

            setValue(addInvLineTMsg.dsOrdLineCatgCd, billOnlyLineCategCd);
            setValue(addInvLineTMsg.origOrCustMdseCd, cpoSvcMdseCd);
            // 2019/07/01 QC#50780 Add Start
            setValue(addInvLineTMsg.dsContrNum, (String) mapRes.get("DS_CONTR_NUM"));
            // 2019/07/01 QC#50780 Add End

            addInvLineBeanList.add(addInvLineBean);

            // QC#22495 2017/11/15 Mod Start
            // addInvLineBean.getInvPrmoInfoList().add(createInvPrmoInfoForBillWithEquipment(unitPrcAmt, termMthNum, lineAmt));
            addInvLineBean.getInvPrmoInfoList().add(createInvPrmoInfoForBillWithEquipment(unitPrcAmt, qty, lineAmt));
            // QC#22495 2017/11/15 Mod End

            allInvLineBeanList.add(addInvLineBean);
        }
    }

    @SuppressWarnings("unchecked")
    private void addInvLineForCpoSvcAddlSvcChrgPrc (BigDecimal dsContrDtlPk, INV_LINEBean invLineBean, INVTMsg invTMsg, List<INV_LINEBean> addInvLineBeanList, List<INV_LINEBean> allInvLineBeanList) {

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd",     glblCmpyCd);
        mapParam.put("dsContrDtlPk",   dsContrDtlPk);
        mapParam.put("addlChrgCatgCd", ADDL_CHRG_CATG.NORMAL);
        List<Map<String, Object>> ssmResList3 = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("queryCPO_SVC_ADDL_CHRG_PRC", mapParam);

        for (Map<String, Object> mapRes3 : ssmResList3) {

            BigDecimal addlChrgPrcDealAmt  = (BigDecimal) mapRes3.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT");
            String addlChrgMdseCd          = (String) mapRes3.get("ADDL_CHRG_TP_CD");
            String svcPrcCatgCd            = (String) mapRes3.get("CHRG_PRC_CATG_CD");
            BigDecimal termMthNum          = (BigDecimal) mapRes3.get("CONTR_DURN_AOT");
            String baseBllgCycleCd         = (String) mapRes3.get("BASE_BLLG_CYCLE_CD");

            //create invoice line
            INV_LINEBean addInvLineBean   = new INV_LINEBean(invLineBean);
            INV_LINETMsg addInvLineTMsg   = addInvLineBean.getInvLineTMsg();
            setValue(addInvLineTMsg.mdseCd, addlChrgMdseCd);
            setMdseInfoForSvc(addInvLineBean);

            // QC#22495 2017/11/15 Mod Start
            //BigDecimal lineAmt = getLineAmtForBillWithEquipment(termMthNum, baseBllgCycleCd, addlChrgPrcDealAmt); // QC#18113
            //BigDecimal unitPrcAmt = lineAmt.divide(termMthNum, funcCcyScale, HALF_UP);
            //
            //setValue(addInvLineTMsg.dealNetUnitPrcAmt, unitPrcAmt);
            //
            //setValue(addInvLineTMsg.invLineDealNetAmt, lineAmt);
            //
            //setValue(addInvLineTMsg.dealGrsUnitPrcAmt, unitPrcAmt);
            //
            //setValue(addInvLineTMsg.dealGrsUnitPrcAmt, lineAmt);
            //
            //setValue(addInvLineTMsg.shipQty, termMthNum);
            //setValue(addInvLineTMsg.ordQty,  termMthNum);

            BigDecimal qty = getLineQtyForBillWithEquipment(termMthNum, baseBllgCycleCd);
            BigDecimal lineAmt = BigDecimal.ZERO;
            BigDecimal unitPrcAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(addlChrgPrcDealAmt)) {
                lineAmt = addlChrgPrcDealAmt.multiply(qty).setScale(funcCcyScale, HALF_UP);
                unitPrcAmt = addlChrgPrcDealAmt;
            }

            setValue(addInvLineTMsg.dealNetUnitPrcAmt, unitPrcAmt);

            setValue(addInvLineTMsg.invLineDealNetAmt, lineAmt);

            setValue(addInvLineTMsg.dealGrsUnitPrcAmt, unitPrcAmt);

            setValue(addInvLineTMsg.dealGrsUnitPrcAmt, lineAmt);

            setValue(addInvLineTMsg.shipQty, qty);
            setValue(addInvLineTMsg.ordQty, qty);
            // QC#22495 2017/11/15 Mod End
            setValue(addInvLineTMsg.dsContrDtlPk,  dsContrDtlPk);
            setValue(addInvLineTMsg.svcPrcCatgCd, svcPrcCatgCd);

            setValue(addInvLineTMsg.dsOrdPosnNum, invLineBean.getDsOrdPosnNum());
            setValue(addInvLineTMsg.dsOrdLineCatgCd, billOnlyLineCategCd);
            setValue(addInvLineTMsg.origOrCustMdseCd, addlChrgMdseCd);

            addInvLineBean.setDsOrdPosnNum(invLineBean.getDsOrdPosnNum());
            //get trx,trxRsn
            getSvcTrxCd(addInvLineBean);

            addInvLineBeanList.add(addInvLineBean);

            // QC#22495 2017/11/15 Mod Start
            // addInvLineBean.getInvPrmoInfoList().add(createInvPrmoInfoForBillWithEquipment(unitPrcAmt, termMthNum, lineAmt));
            addInvLineBean.getInvPrmoInfoList().add(createInvPrmoInfoForBillWithEquipment(unitPrcAmt, qty, lineAmt));
            // QC#22495 2017/11/15 Mod End
            allInvLineBeanList.add(addInvLineBean);
        }
    }

    private BigDecimal getLineAmtForBillWithEquipment(BigDecimal term, String bllgCycleCd, BigDecimal prcAmt) {

        BigDecimal lineAmt = ZERO;

        if (!ZYPCommonFunc.hasValue(term) || !ZYPCommonFunc.hasValue(prcAmt)) {
            return lineAmt;
        }

        BLLG_CYCLETMsg bllgCycleTMsg = new BLLG_CYCLETMsg();
        setValue(bllgCycleTMsg.glblCmpyCd, glblCmpyCd);
        setValue(bllgCycleTMsg.bllgCycleCd, bllgCycleCd);

        bllgCycleTMsg = (BLLG_CYCLETMsg) S21CacheTBLAccessor.findByKey(bllgCycleTMsg);

        if (bllgCycleTMsg != null) {
            // QC#18113
            lineAmt = prcAmt.multiply(term).divide(bllgCycleTMsg.bllgCycleMthAot.getValue()).setScale(funcCcyScale, HALF_UP);
        }

        return lineAmt;
    }
    // QC#22495 2017/11/15 Add Start
    private BigDecimal getLineQtyForBillWithEquipment(BigDecimal term, String bllgCycleCd) {

        BigDecimal lineQty = BigDecimal.ZERO;
        if (!ZYPCommonFunc.hasValue(term)) {
            return lineQty;
        }

        BLLG_CYCLETMsg bllgCycleTMsg = new BLLG_CYCLETMsg();
        setValue(bllgCycleTMsg.glblCmpyCd, glblCmpyCd);
        setValue(bllgCycleTMsg.bllgCycleCd, bllgCycleCd);

        bllgCycleTMsg = (BLLG_CYCLETMsg) S21CacheTBLAccessor.findByKey(bllgCycleTMsg);

        if (bllgCycleTMsg != null) {
            lineQty = term.divide(bllgCycleTMsg.bllgCycleMthAot.getValue(), 0, HALF_UP);
        }

        return lineQty;
    }
    // QC#22495 2017/11/15 Add End

    @SuppressWarnings("unchecked")
    private boolean getSvcTrxCd(INV_LINEBean invLineBean) {

        boolean ret = true;
        INV_LINETMsg    invLineTMsg   = invLineBean.getInvLineTMsg();

        String cacheKey = createCacheKey("getTrxCdForBillWithEquip", invLineTMsg.mdseCd.getValue());
        Map<String, Object> mapRes1 = (HashMap<String, Object>) s21lruMap.get(cacheKey);

        if (mapRes1 == null) {
            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd",  glblCmpyCd);
            mapParam.put("mdseCd",      invLineTMsg.mdseCd.getValue());
            mapRes1 = (HashMap<String, Object>) ssmBatchClient.queryObject("getTrxCdForBillWithEquip", mapParam);

            if (mapRes1 != null) {
                s21lruMap.put(cacheKey, mapRes1);
            }
        }
        if (mapRes1 != null) {
            setValue(invLineTMsg.trxCd,    (String) mapRes1.get("SVC_TRX_CD"));
            setValue(invLineTMsg.trxRsnCd, (String) mapRes1.get("SVC_TRX_RSN_CD"));
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    private void addInvLineForCpoSvcAddlBasePrc (BigDecimal dsContrDtlPk, INV_LINEBean invLineBean, INVTMsg invTMsg, List<INV_LINEBean> addInvLineBeanList, List<INV_LINEBean> allInvLineBeanList) {

        // ***** additional base charge *****
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd",      glblCmpyCd);
        mapParam.put("dsContrDtlPk",    dsContrDtlPk);
        mapParam.put("addlChrgCatgCd",  ADDL_CHRG_CATG.RENTAL);
        mapParam.put("dsContrDtlTpCd",  DS_CONTR_DTL_TP.ACCESSORIES);

        List<Map<String, Object>> ssmResList2 = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("queryCPO_SVC_ADDL_BASE_PRC", mapParam);
    
        for (Map<String, Object> mapRes2 : ssmResList2) {
            BigDecimal addlBasePrcDealAmt  = (BigDecimal) mapRes2.get("BASE_PRC_DEAL_AMT"); // 2017/10/25 QC#21809 Mod

            String svcPrcCatgCd            = (String) mapRes2.get("SVC_PRC_CATG_CD");
            String baseBllgCycleCd         = (String) mapRes2.get("BASE_BLLG_CYCLE_CD");
            BigDecimal termMthNum          = (BigDecimal) mapRes2.get("CONTR_DURN_AOT");

            String cpoSvcMdseCd            = (String) mapRes2.get("SVC_PGM_MDSE_CD");

            // QC#22495 2017/11/15 Mod Start
            //BigDecimal lineAmt             = getLineAmtForBillWithEquipment(termMthNum, baseBllgCycleCd, addlBasePrcDealAmt); // QC#18113
            //BigDecimal unitPrcAmt          = lineAmt.divide(termMthNum, funcCcyScale, HALF_UP);

            BigDecimal qty = getLineQtyForBillWithEquipment(termMthNum, baseBllgCycleCd);
            BigDecimal lineAmt = BigDecimal.ZERO;
            BigDecimal unitPrcAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(addlBasePrcDealAmt)) {
                lineAmt = addlBasePrcDealAmt.multiply(qty).setScale(funcCcyScale, HALF_UP);
                unitPrcAmt = addlBasePrcDealAmt;
            }
            // QC#22495 2017/11/15 Mod End

            //create invoice Line
            INV_LINEBean addlBaseInvLineBean   = new INV_LINEBean(invLineBean);
            INV_LINETMsg addlBaseInvLineTMsg   = addlBaseInvLineBean.getInvLineTMsg();

            setValue(addlBaseInvLineTMsg.mdseCd, cpoSvcMdseCd);

            setMdseInfoForSvc(addlBaseInvLineBean);

            setValue(addlBaseInvLineTMsg.dealNetUnitPrcAmt, unitPrcAmt);

            setValue(addlBaseInvLineTMsg.invLineDealNetAmt, lineAmt);

            setValue(addlBaseInvLineTMsg.dealGrsUnitPrcAmt, unitPrcAmt);

            setValue(addlBaseInvLineTMsg.dealGrsUnitPrcAmt, lineAmt);

            // QC#22495 2017/11/15 Mod Start
            // setValue(addlBaseInvLineTMsg.shipQty, termMthNum);
            // setValue(addlBaseInvLineTMsg.ordQty, termMthNum);
            setValue(addlBaseInvLineTMsg.shipQty, qty);
            setValue(addlBaseInvLineTMsg.ordQty, qty);
            // QC#22495 2017/11/15 Mod End

            setValue(addlBaseInvLineTMsg.svcPrcCatgCd, svcPrcCatgCd);
            setValue(addlBaseInvLineTMsg.dsContrDtlPk, dsContrDtlPk);

            setValue(addlBaseInvLineTMsg.dsOrdPosnNum, invLineBean.getDsOrdPosnNum());
            setValue(addlBaseInvLineTMsg.dsOrdLineCatgCd, billOnlyLineCategCd);
            setValue(addlBaseInvLineTMsg.origOrCustMdseCd, cpoSvcMdseCd);

            addlBaseInvLineBean.setDsOrdPosnNum(invLineBean.getDsOrdPosnNum());
            //get trx,trxRsn
            getSvcTrxCd(addlBaseInvLineBean);

            addInvLineBeanList.add(addlBaseInvLineBean);

            // QC#22495 2017/11/15 Mod Start
            // addlBaseInvLineBean.getInvPrmoInfoList().add(createInvPrmoInfoForBillWithEquipment(unitPrcAmt, termMthNum, lineAmt));
            addlBaseInvLineBean.getInvPrmoInfoList().add(createInvPrmoInfoForBillWithEquipment(unitPrcAmt, qty, lineAmt));
            // QC#22495 2017/11/15 Mod End

            allInvLineBeanList.add(addlBaseInvLineBean);
        }
    }

    private INV_PRMO_INFOTMsg createInvPrmoInfoForBillWithEquipment(BigDecimal unitPrcAmt, BigDecimal termMthNum, BigDecimal amt) {

        INV_PRMO_INFOTMsg invPrmoInfoTMsg = new INV_PRMO_INFOTMsg();

        setValue(invPrmoInfoTMsg.glblCmpyCd, glblCmpyCd);

        setValue(invPrmoInfoTMsg.dealUnitPrcAmt, unitPrcAmt);
        setValue(invPrmoInfoTMsg.dealLastNetUnitPrcAmt, unitPrcAmt);
        setValue(invPrmoInfoTMsg.dealNetAmt, amt);
        setValue(invPrmoInfoTMsg.dealDiscAmt, ZERO);
        setValue(invPrmoInfoTMsg.dealPrmoNetUnitPrcAmt, unitPrcAmt);
        setValue(invPrmoInfoTMsg.dealPerUnitFixAmt, ZERO);
        setValue(invPrmoInfoTMsg.dealSlsPctNum, ZERO);

        setValue(invPrmoInfoTMsg.funcPerUnitFixAmt, ZERO);
        setValue(invPrmoInfoTMsg.funcUnitPrcAmt, unitPrcAmt);
        setValue(invPrmoInfoTMsg.funcLastNetUnitPrcAmt, unitPrcAmt);
        setValue(invPrmoInfoTMsg.funcNetAmt, amt);
        setValue(invPrmoInfoTMsg.funcDiscAmt, ZERO);
        setValue(invPrmoInfoTMsg.funcPrmoNetUnitPrcAmt, unitPrcAmt);

        setValue(invPrmoInfoTMsg.prmoQty, termMthNum);

        return invPrmoInfoTMsg;
    }

    private void setMdseInfoForSvc(INV_LINEBean invLineBean) {

        INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

        String mdseCd = invLineTMsg.mdseCd.getValue();

        MDSETMsg mdseTMsg = new MDSETMsg();
        setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        setValue(mdseTMsg.mdseCd, mdseCd);

        mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        if (mdseTMsg != null) {
            setValue(invLineTMsg.mdseNm, mdseTMsg.mdseNm.getValue());
            setValue(invLineTMsg.zerothProdCtrlCd, mdseTMsg.zerothProdCtrlCd.getValue());
            setValue(invLineTMsg.zerothProdCtrlNm, mdseTMsg.zerothProdCtrlNm.getValue());
            setValue(invLineTMsg.firstProdCtrlCd, mdseTMsg.firstProdCtrlCd.getValue());
            setValue(invLineTMsg.firstProdCtrlNm, mdseTMsg.firstProdCtrlNm.getValue());
            setValue(invLineTMsg.scdProdCtrlCd, mdseTMsg.scdProdCtrlCd.getValue());
            setValue(invLineTMsg.scdProdCtrlNm, mdseTMsg.scdProdCtrlNm.getValue());
            setValue(invLineTMsg.thirdProdCtrlCd, mdseTMsg.thirdProdCtrlCd.getValue());
            setValue(invLineTMsg.thirdProdCtrlNm, mdseTMsg.thirdProdCtrlNm.getValue());
            setValue(invLineTMsg.frthProdCtrlCd, mdseTMsg.frthProdCtrlCd.getValue());
            setValue(invLineTMsg.frthProdCtrlNm, mdseTMsg.frthProdCtrlNm.getValue());
            setValue(invLineTMsg.fifthProdCtrlCd, mdseTMsg.fifthProdCtrlCd.getValue());
            setValue(invLineTMsg.fifthProdCtrlNm, mdseTMsg.fifthProdCtrlNm.getValue());
            setValue(invLineTMsg.sixthProdCtrlCd, mdseTMsg.sixthProdCtrlCd.getValue());
            setValue(invLineTMsg.sixthProdCtrlNm, mdseTMsg.sixthProdCtrlNm.getValue());
            setValue(invLineTMsg.svnthProdCtrlCd, mdseTMsg.svnthProdCtrlCd.getValue());
            setValue(invLineTMsg.svnthProdCtrlNm, mdseTMsg.svnthProdCtrlNm.getValue());
        }

    }

    private boolean getCtacPsn(List<OrderDataBean> orderDataBeanList) {

        boolean isSuccess = true;

        S21LRUMap<String, Object> cache = new S21LRUMap<String, Object>();
        CTAC_PSNTMsg ctacPsnTmsg = null;
        for (OrderDataBean orderDataBean : orderDataBeanList) {
            S21ApiMessageMap msgMap = orderDataBean.getMsgMap();
            NWZC035002PMsg orderDataPMsg = orderDataBean.getNWZC035002PMsg();
            NWZC035001PMsg param = (NWZC035001PMsg) msgMap.getPmsg();

            String dsCpoConfigPk = orderDataPMsg.dsCpoConfigPk.getValue().toString();
            String cpoOrdNum     = orderDataPMsg.cpoOrdNum.getValue();
            String custLocCd     = orderDataPMsg.billToCustLocCd.getValue();
            String custAcctCd    = orderDataPMsg.billToCustAcctCd.getValue();
            String ctacTpCd;
            //get bill to Contact person
            // QC#27954 2018/11/13 Mod Start
//            if (DS_INV_TP.INVOICE_SUPPLIES.equals(orderDataPMsg.dsInvTpCd.getValue())) {
//                ctacTpCd = CTAC_TP.BILL_TO_SUPPLIES;
//            } else {
////                ctacTpCd = CTAC_TP.BILL_TO_CONTACT;
//                ctacTpCd = CTAC_TP.BILL_TO_CONTACT_EQUIPMENT;    //QC#23559 mod
//            }
                ctacTpCd = setOrdCatgBizCtx(orderDataPMsg);
            // QC#27954 2018/11/13 Mod End

            //QC#27172 mod Start
            ctacPsnTmsg = getBillToCtacPsnNm(dsCpoConfigPk, cpoOrdNum, custLocCd, custAcctCd, ctacTpCd, param.slsDt.getValue(), cache);
            //ctacPsnTmsg = getCtacPsnNm(CONST_SEARCH_TARGET_BILL_TO, dsCpoConfigPk, cpoOrdNum, custLocCd, custAcctCd, ctacTpCd, param.slsDt.getValue(), cache);
            //QC#27172 mod End

            if (ctacPsnTmsg != null) {
                setValue(orderDataPMsg.ctacPsnPk_B,          ctacPsnTmsg.ctacPsnPk.getValue());
                setValue(orderDataPMsg.billToCtacPsnFirstNm, ctacPsnTmsg.ctacPsnFirstNm.getValue());
                setValue(orderDataPMsg.billToCtacPsnMidNm,   ctacPsnTmsg.ctacPsnMidNm.getValue());
                setValue(orderDataPMsg.billToCtacPsnLastNm,  ctacPsnTmsg.ctacPsnLastNm.getValue());
            } else {
//                msgMap.addXxMsgId(NWZM1508E);
//                isSuccess = false;
            }
            //get ship to contact person
            custLocCd = orderDataPMsg.shipToCustCd.getValue();
            custAcctCd = orderDataPMsg.shipToCustAcctCd.getValue();
//            ctacPsnTmsg = getCtacPsnNm(CONST_SEARCH_TARGET_SHIP_TO, dsCpoConfigPk, cpoOrdNum, custLocCd, custAcctCd, CTAC_TP.SHIP_TO_CONTACT, param.slsDt.getValue(), cache);
            ctacPsnTmsg = getCtacPsnNm(CONST_SEARCH_TARGET_SHIP_TO, dsCpoConfigPk, cpoOrdNum, custLocCd, custAcctCd, CTAC_TP.DELIVERY_INSTALL, param.slsDt.getValue(), cache);

            if (ctacPsnTmsg != null) {
                setValue(orderDataPMsg.ctacPsnPk_S,          ctacPsnTmsg.ctacPsnPk.getValue());
                setValue(orderDataPMsg.shipToCtacPsnFirstNm, ctacPsnTmsg.ctacPsnFirstNm.getValue());
                setValue(orderDataPMsg.shipToCtacPsnMidNm,   ctacPsnTmsg.ctacPsnMidNm.getValue());
                setValue(orderDataPMsg.shipToCtacPsnLastNm,  ctacPsnTmsg.ctacPsnLastNm.getValue());
            } else {
//                msgMap.addXxMsgId(NWZM1508E);
//                isSuccess = false;
            }

        }
        return isSuccess;
    }

    @SuppressWarnings("unchecked")
    private boolean setOptGrpKey(List<OrderDataBean> orderDataBeanList) {

        boolean isSuccess = true;

        S21LRUMap<String, Object> cache = new S21LRUMap<String, Object>();

        for (OrderDataBean orderDataBean : orderDataBeanList) {

            NWZC035002PMsg orderDataPMsg = orderDataBean.getNWZC035002PMsg();
            String dsInvTpCd = orderDataPMsg.dsInvTpCd.getValue();

            String cacheKey = createCacheKey("getInvGrpAttrbTxt", dsInvTpCd);
            List<String> invGrpAttrbTxtList = (ArrayList<String>) cache.get(cacheKey);
            if (invGrpAttrbTxtList == null) {
                //optional grouping key
                Map<String, String> mapParam = new HashMap<String, String>();
                mapParam.put("glblCmpyCd", glblCmpyCd);
                mapParam.put("dsInvTpCd",  orderDataPMsg.dsInvTpCd.getValue());
                invGrpAttrbTxtList = (ArrayList<String>) ssmBatchClient.queryObjectList("getInvGrpAttrbTxt", mapParam);
                cache.put(cacheKey, invGrpAttrbTxtList);
            }

            for (String grpAttrbTxt : invGrpAttrbTxtList) {
                if (CONST_INV_GRP_ATTRB_TXT_SHIP_TO_CUST_ACCT.equals(grpAttrbTxt)) {
                    // ship to cust account
                    setValue(orderDataPMsg.shipToCustAcctCd_G, orderDataPMsg.shipToCustAcctCd.getValue());
                }
                if (CONST_INV_GRP_ATTRB_TXT_SHIP_TO_CUST_LOC.equals(grpAttrbTxt)) {
                    // ship to cust location
                    setValue(orderDataPMsg.shipToCustLocCd_G, orderDataPMsg.shipToCustCd.getValue());
                }
                if (CONST_INV_GRP_ATTRB_TXT_SHIP_TO_CTAC_PSN_PK.equals(grpAttrbTxt)) {
                    // ship to ctac psn pk
                    setValue(orderDataPMsg.ctacPsnPk_G,       orderDataPMsg.ctacPsnPk_S.getValue());
                }

            }

        }
        return isSuccess;
    }

    private boolean masterValidation(List<INVBean> invBeanList) {

        boolean isSuccess = true;
        for (INVBean invBean : invBeanList) {

            // dummy check
            isSuccess = dummyCheck(invBean);

            // set result
            if (!isSuccess) {
                setValue(invBean.getInvTMsg().itrlInvErrFlg, Y);
            }

        }

        return isSuccess;
    }

    private boolean dummyCheck(INVBean invBean)  {
        return true;
    }

    //*** call invoice sales credit creation api
    private boolean createInvoiceSalesCredit(List<INVBean> invBeanList) {

        boolean isSuccess = true;
        NWZC055001 invSlsCrCratAPI = new NWZC055001();
        NWZC055001PMsg pMsg;
        INVTMsg invTMsg;

        for (INVBean invBean : invBeanList) {
            invTMsg = invBean.getInvTMsg();
            pMsg = new NWZC055001PMsg();
            setValue(pMsg.glblCmpyCd, glblCmpyCd);
            setValue(pMsg.invNum, invTMsg.invNum.getValue());

            setValue(pMsg.dsOrdLineDrctnCd, DS_ORD_LINE_DRCTN.OUTBOUND);
            invSlsCrCratAPI.execute(pMsg, ONBATCH_TYPE.BATCH);

            S21ApiMessageMap msgMap = invBean.getMsgMap();
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                    msgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                isSuccess = false;
            }
        }

        return isSuccess;
    }

    // START 2018/03/13 E.Kameishi [QC#23689,DEL]
//    private boolean callInvoiceDistributionAPI(List<INVBean> invBeanList) {
//
//        boolean isSuccess = true;
//        NFZC103001 invDistAPI = new NFZC103001();
//        List<NFZC103001PMsg> pMsgList = new ArrayList<NFZC103001PMsg>();
//
//        for (INVBean invBean : invBeanList) {
//            INVTMsg invTMsg = invBean.getInvTMsg();
//            NFZC103001PMsg pMsg = new NFZC103001PMsg();
//            setValue(pMsg.glblCmpyCd, glblCmpyCd);
//            setValue(pMsg.invNum, invTMsg.invNum.getValue());
//            setValue(pMsg.procDt, invTMsg.invDt.getValue());
//            pMsgList.add(pMsg);
//        }
//
//        invDistAPI.excute(pMsgList, ONBATCH_TYPE.BATCH);
//
//        for (NFZC103001PMsg pMsg : pMsgList) {
//            if (pMsg.xxMsgIdList.getValidCount() > 0) {
//                // update ITRL_INV_ERR_FLG:Y
//                INVTMsg invTMsg = new INVTMsg();
//                setValue(invTMsg.glblCmpyCd, glblCmpyCd);
//                setValue(invTMsg.invNum, pMsg.invNum.getValue());
//                setValue(invTMsg.itrlInvErrFlg, Y);
//                S21ApiTBLAccessor.updateSelectionField(invTMsg, new String[]{"itrlInvErrFlg"});
//
//                break;
//            }
//        }
//
//        return isSuccess;
//    }
    // END 2018/03/13 E.Kameishi [QC#23689,DEL]

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getRoundingAdjustAmount(INVTMsg invTMsg, INV_LINEBean invLineBean) {

        if (ZYPConstant.FLG_ON_Y.equals(invLineBean.getBillWithEquipFlg())) {
            return null;
        }

        INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd",          glblCmpyCd);
        mapParam.put("shpgPlnNum",          invLineTMsg.shpgPlnNum.getValue());
        mapParam.put("cpoOrdNum",           invTMsg.cpoOrdNum.getValue());
        mapParam.put("cpoDtlLineNum",       invLineTMsg.cpoDtlLineNum.getValue());
        mapParam.put("cpoDtlLineSubNum",    invLineTMsg.cpoDtlLineSubNum.getValue());
        List<Map<String, Object>> resMapList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getRoundingAdjustAmount", mapParam);

        return resMapList;
    }
    // QC#22031 2017/10/17 Add Start
    private boolean isVendorCode(String glblCmpyCd, String vndCd) {
        VNDTMsg inMsg = new VNDTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("vndCd01", vndCd);
        VNDTMsgArray tmsgArray = (VNDTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (tmsgArray.length() == 0) {
            return false;
        }
        return true;
    }
    // QC#22031 2017/10/17 Add End

    // QC#21841 2018/05/21 Add Start
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getCalcBaseForCharges(INV_LINEBean invLineBean) {

        INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd",          glblCmpyCd);
        mapParam.put("shpgPlnNum",          invLineTMsg.shpgPlnNum.getValue());
        List<String> list = new ArrayList<String>();
        list.add(PRC_DTL_GRP.FREIGHT);
        list.add(PRC_DTL_GRP.HANDLING_FEE);
        list.add(PRC_DTL_GRP.FUEL_SURCHARGE);
        list.add(PRC_DTL_GRP.SHIPPING_FEE);
        list.add(PRC_DTL_GRP.RESTOCKING_FEE);  // QC#27479 2018/08/03 Add
        mapParam.put("prcDtlGrpCdList",     list);
        List<String> listRnd = new ArrayList<String>();
        listRnd.add(PRC_DTL_GRP.ROUNDING_FACTOR_1);
        listRnd.add(PRC_DTL_GRP.ROUNDING_FACTOR_2);
        mapParam.put("prcDtlGrpCdRnd",      listRnd);
        mapParam.put("delFlg",              ZYPConstant.FLG_OFF_N);
        mapParam.put("applyFlg",            ZYPConstant.FLG_ON_Y); // QC#9700  2018/09/03 Add
        List<Map<String, Object>> resMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getCalcBaseForCharges", mapParam);

        return resMapList;
    }
    // QC#21841 2018/05/21 Add End
    private void log(List<INVBean> invBeanList, String s) {
        System.out.println("--------------------------------------" + s );
        for (INVBean invBean : invBeanList) {
            List<INV_BOLBean> invBolBeanList = invBean.getInvBolBeanList();
            for (INV_BOLBean invBolBean : invBolBeanList) {
                List<INV_LINEBean> invLineBeanList = invBolBean.getInvLineBeanList();
                for (INV_LINEBean invLineBean : invLineBeanList) {
                    INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();
                    StringBuffer sb = new StringBuffer();
                    sb.append(" cpoOrdNum : ").append(invLineTMsg.cpoOrdNum.getValue());
                    sb.append(" cpoDtlLineNum : ").append(invLineTMsg.cpoDtlLineNum.getValue());
                    sb.append(" cpoDtlLineSubNum : ").append(invLineTMsg.cpoDtlLineSubNum.getValue());
                    sb.append(" invLineNum : ").append(invLineTMsg.invLineNum.getValue());
                    sb.append(" invLineSubNum : ").append(invLineTMsg.invLineSubNum.getValue());
                    sb.append(" mdseCd : ").append(invLineTMsg.mdseCd.getValue());
                    sb.append(" invLineDealNetAmt : ").append(invLineTMsg.invLineDealNetAmt.getValue());
                    sb.append(" invLineCatgCd : ").append(invLineTMsg.invLineCatgCd.getValue());
                    System.out.println(sb.toString());
                }
            }
        }
    }
    private void log(List<INV_LINEBean> allInvLineBeanList,String s, String s2) {
        System.out.println("--------------------------------------" + s + s2);
        for (INV_LINEBean invLineBean : allInvLineBeanList) {
            StringBuffer sb = new StringBuffer();
            INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();
            sb.append(" cpoOrdNum : ").append(invLineTMsg.cpoOrdNum.getValue());
            sb.append(" cpoDtlLineNum : ").append(invLineTMsg.cpoDtlLineNum.getValue());
            sb.append(" cpoDtlLineSubNum : ").append(invLineTMsg.cpoDtlLineSubNum.getValue());
            sb.append(" invLineNum : ").append(invLineTMsg.invLineNum.getValue());
            sb.append(" invLineSubNum : ").append(invLineTMsg.invLineSubNum.getValue());
            sb.append(" mdseCd : ").append(invLineTMsg.mdseCd.getValue());
            sb.append(" invLineDealNetAmt : ").append(invLineTMsg.invLineDealNetAmt.getValue());
            sb.append(" invLineCatgCd : ").append(invLineTMsg.invLineCatgCd.getValue());
            System.out.println(sb.toString());
        }
    }

//QC#26121 add Start
    private void negateInvAmt(EZDTMsg msg) {

        if (msg instanceof INVTMsg) {
            INVTMsg invTMsg = (INVTMsg) msg;
            negateAmt(invTMsg.invTotDealNetAmt);
            negateAmt(invTMsg.invTotDealSlsAmt);
            negateAmt(invTMsg.invTotDealFrtAmt);
            negateAmt(invTMsg.invTotDealTaxAmt);
            negateAmt(invTMsg.invTotDealDiscAmt);
            negateAmt(invTMsg.invTotFuncNetAmt);
            negateAmt(invTMsg.invTotFuncSlsAmt);
            negateAmt(invTMsg.invTotFuncFrtAmt);
            negateAmt(invTMsg.invTotFuncTaxAmt);
            negateAmt(invTMsg.invTotFuncDiscAmt);

        } else if (msg instanceof INV_BOLTMsg) {
            INV_BOLTMsg invBolTMsg = (INV_BOLTMsg) msg;
            negateAmt(invBolTMsg.shipDealSlsAmt);
            negateAmt(invBolTMsg.shipFuncSlsAmt);
            negateAmt(invBolTMsg.shipDealNetAmt);
            negateAmt(invBolTMsg.shipDealFrtAmt);
            negateAmt(invBolTMsg.shipFuncFrtAmt);
            negateAmt(invBolTMsg.shipDealDiscAmt);
            negateAmt(invBolTMsg.shipFuncDiscAmt);
            negateAmt(invBolTMsg.shipDealHdlgChrgAmt);
            negateAmt(invBolTMsg.shipFuncHdlgChrgAmt);
            negateAmt(invBolTMsg.totBolDealTaxAmt);
            negateAmt(invBolTMsg.totBolFuncTaxAmt);
            negateAmt(invBolTMsg.frtDealTaxAmt);
            negateAmt(invBolTMsg.frtFuncTaxAmt);

        } else if (msg instanceof INV_LINETMsg) {
            INV_LINETMsg invLineTMsg = (INV_LINETMsg) msg;
            negateAmt(invLineTMsg.dealNetUnitPrcAmt);
            negateAmt(invLineTMsg.funcNetUnitPrcAmt);
            negateAmt(invLineTMsg.invLineDealNetAmt);
            negateAmt(invLineTMsg.invLineDealTaxAmt);
            negateAmt(invLineTMsg.invLineFuncNetAmt);
            negateAmt(invLineTMsg.invLineFuncTaxAmt);
            negateAmt(invLineTMsg.dealDiscUnitPrcAmt);
            negateAmt(invLineTMsg.funcDiscUnitPrcAmt);
            negateAmt(invLineTMsg.dealGrsUnitPrcAmt);
            negateAmt(invLineTMsg.funcGrsUnitPrcAmt);
            negateAmt(invLineTMsg.dealGrsTotPrcAmt);
            negateAmt(invLineTMsg.funcGrsTotPrcAmt);

        } else if (msg instanceof INV_PRMO_INFOTMsg) {
            INV_PRMO_INFOTMsg invPrmoInfoTMsg = (INV_PRMO_INFOTMsg) msg;
            negateAmt(invPrmoInfoTMsg.dealUnitPrcAmt);
            negateAmt(invPrmoInfoTMsg.dealLastNetUnitPrcAmt);
            negateAmt(invPrmoInfoTMsg.dealNetAmt);
            negateAmt(invPrmoInfoTMsg.dealDiscAmt);
            negateAmt(invPrmoInfoTMsg.dealPrmoNetUnitPrcAmt);
            negateAmt(invPrmoInfoTMsg.funcUnitPrcAmt);
            negateAmt(invPrmoInfoTMsg.funcLastNetUnitPrcAmt);
            negateAmt(invPrmoInfoTMsg.funcNetAmt);
            negateAmt(invPrmoInfoTMsg.funcDiscAmt);
            negateAmt(invPrmoInfoTMsg.funcPrmoNetUnitPrcAmt);

        } else if (msg instanceof DS_INV_LINE_TAX_DTLTMsg) {
            DS_INV_LINE_TAX_DTLTMsg dsInvLineTaxDtlTMsg = (DS_INV_LINE_TAX_DTLTMsg) msg;
            negateAmt(dsInvLineTaxDtlTMsg.dealSlsTaxAmt);
            negateAmt(dsInvLineTaxDtlTMsg.funcSlsTaxAmt);
        }
    }

    private void negateAmt(EZDTBigDecimalItem obj) {
        if (obj != null) {
            obj.setValue(obj.getValue().negate());
        }
    }
//QC#26121 add End
    // 2018/09/11 QC#28113 Add Start
    private void setCorrectUom(INV_LINETMsg invLineTMsg) {

        MDSE_STORE_PKGTMsg mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.glblCmpyCd, invLineTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.pkgUomCd, invLineTMsg.uomCd);
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.mdseCd, invLineTMsg.mdseCd);

        mdseStorePkgTMsg = (MDSE_STORE_PKGTMsg) S21CacheTBLAccessor.findByKey(mdseStorePkgTMsg);
        if (mdseStorePkgTMsg != null) {
            return;
        }

        String key = invLineTMsg.glblCmpyCd.getValue() + "-" + invLineTMsg.mdseCd.getValue();
        MDSE_STORE_PKGTMsgArray mdseStorePkgTMsgArry = (MDSE_STORE_PKGTMsgArray) this.mdseStorePkgMap.get(key);
        if (mdseStorePkgTMsgArry == null) {
            mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
            mdseStorePkgTMsg.setSQLID("002");
            mdseStorePkgTMsg.setConditionValue("glblCmpyCd01", invLineTMsg.glblCmpyCd.getValue());
            mdseStorePkgTMsg.setConditionValue("mdseCd01", invLineTMsg.mdseCd.getValue());

            mdseStorePkgTMsgArry = (MDSE_STORE_PKGTMsgArray) EZDTBLAccessor.findByCondition(mdseStorePkgTMsg);
            this.mdseStorePkgMap.put(key, mdseStorePkgTMsgArry);
        }
        if (mdseStorePkgTMsgArry == null || mdseStorePkgTMsgArry.getValidCount() == 0) {
            invLineTMsg.uomCd.setValue(PKG_UOM.EACH);
            ZYPEZDItemValueSetter.setValue(invLineTMsg.ordCustUomQty, invLineTMsg.ordQty);
            return;
        }
        MDSE_STORE_PKGTMsg firstTMsg = mdseStorePkgTMsgArry.no(0);

        for (int i = 1; i < mdseStorePkgTMsgArry.getValidCount(); i++) {
            MDSE_STORE_PKGTMsg curTMsg = mdseStorePkgTMsgArry.no(i);
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseStorePkgTMsg.primPkgUomFlg.getValue())) {
                firstTMsg = curTMsg;
                break;
            }
        }
        if (firstTMsg != null) {
            BigDecimal inEachQty = firstTMsg.inEachQty.getValue();
            invLineTMsg.uomCd.setValue(firstTMsg.pkgUomCd.getValue());
            BigDecimal ordCustUomQty = invLineTMsg.shipQty.getValue().divide(inEachQty).setScale(0, BigDecimal.ROUND_UP);
            invLineTMsg.ordCustUomQty.setValue(ordCustUomQty);
        }
        return;
    }
    // 2018/09/11 QC#28113 Add End

    // 2018/11/13 S21_NA#27954 Add Start
    private String setOrdCatgBizCtx(NWZC035002PMsg orderDataPMsg) {

        List<Map<String, Object>> ordCatgBizCtxList = new ArrayList<Map<String, Object>>();
        Map<String, Object> ordCatgBizCtxMap = new HashMap<String, Object>();

        String ctacTpCd = null;

        ordCatgBizCtxList = getOrdCatgBizCtxList(glblCmpyCd, ORD_CATG_CTX_TP_SUPPLIES_ORDER, orderDataPMsg);

        if (!ordCatgBizCtxList.isEmpty()) {
            ordCatgBizCtxMap = getOrdCatgBizCtx(ordCatgBizCtxList, orderDataPMsg);

            if (!ordCatgBizCtxMap.isEmpty()) {
                if (ZYPCommonFunc.hasValue((String) ordCatgBizCtxMap.get("FIRST_BIZ_CTX_ATTRB_TXT"))) {
                    String ordCatgBizCtx = (String) ordCatgBizCtxMap.get("FIRST_BIZ_CTX_ATTRB_TXT");
                    if (DS_TRX_RULE_TP.PARTS.equals(ordCatgBizCtx)) {
                        ctacTpCd = CTAC_TP.BILL_TO_CONTACT_PARTS;
                    } else if (DS_TRX_RULE_TP.SUPPLIES.equals(ordCatgBizCtx)) {
                        ctacTpCd = CTAC_TP.BILL_TO_SUPPLIES;
                    }
                }
            }
        }
  
        if (!ZYPCommonFunc.hasValue(ctacTpCd)) {
            ordCatgBizCtxList = getOrdCatgBizCtxList(glblCmpyCd, ORD_CATG_CTX_TP_EQUIPMENT_ORDER, orderDataPMsg);

            if (!ordCatgBizCtxList.isEmpty()) {
                ordCatgBizCtxMap = getOrdCatgBizCtx(ordCatgBizCtxList, orderDataPMsg);
                
                if (!ordCatgBizCtxMap.isEmpty()) {
                    ctacTpCd = CTAC_TP.BILL_TO_CONTACT_EQUIPMENT;
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(ctacTpCd)) {
            ctacTpCd = CTAC_TP.BILL_TO_CONTACT_EQUIPMENT;
        }

        return ctacTpCd;
    }

    private Map<String, Object> getOrdCatgBizCtx(List<Map<String, Object>> ordCatgBizCtxList, NWZC035002PMsg orderDataPMsg) {

        Map<String, Object> resMap = new HashMap<String, Object>();

        String dsOrdTpCd = orderDataPMsg.dsOrdTpCd.getValue();
        String dsOrdRsnCd = orderDataPMsg.dsOrdRsnCd.getValue();

        resMap = getOrdCatgBizCtxMap(ordCatgBizCtxList, dsOrdTpCd, dsOrdRsnCd);

        if (resMap.isEmpty()) {
            resMap = getOrdCatgBizCtxMap(ordCatgBizCtxList, dsOrdTpCd, null);
        }

        if (resMap.isEmpty()) {
            resMap = getOrdCatgBizCtxMap(ordCatgBizCtxList, null, null);
        }

        return resMap;
    }

    private Map<String, Object> getOrdCatgBizCtxMap(List<Map<String, Object>> ordCatgBizCtxList, String dsOrdTpCd, String dsOrdRsnCd) {

        Map<String, Object> resMap = new HashMap<String, Object>();

        if (ZYPCommonFunc.hasValue(dsOrdTpCd) && ZYPCommonFunc.hasValue(dsOrdRsnCd)) {
            for (Map<String, Object> map : ordCatgBizCtxList) {
                if (dsOrdTpCd.equals((String) map.get("DS_ORD_TP_CD"))
                 && dsOrdRsnCd.equals((String) map.get("DS_ORD_RSN_CD"))) {
                    resMap = map;
                    break;
                }
            }

        } else if (ZYPCommonFunc.hasValue(dsOrdTpCd) && !ZYPCommonFunc.hasValue(dsOrdRsnCd)) {
            for (Map<String, Object> map : ordCatgBizCtxList) {
                if (dsOrdTpCd.equals((String) map.get("DS_ORD_TP_CD"))
                 && !ZYPCommonFunc.hasValue((String) map.get("DS_ORD_RSN_CD"))) {
                    resMap = map;
                    break;
                }
            }

        } else if (!ZYPCommonFunc.hasValue(dsOrdTpCd) && !ZYPCommonFunc.hasValue(dsOrdRsnCd)) {
            for (Map<String, Object> map : ordCatgBizCtxList) {
                if (!ZYPCommonFunc.hasValue((String) map.get("DS_ORD_TP_CD"))
                 && !ZYPCommonFunc.hasValue((String) map.get("DS_ORD_RSN_CD"))) {
                    resMap = map;
                    break;
                }
            }

        }

        return resMap;
    }

    private List<Map<String, Object>> getOrdCatgBizCtxList(String glblCmpyCd, String OrdCatgCtxTpCd, NWZC035002PMsg orderDataPMsg) {

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("OrdCatgCtxTpCd", OrdCatgCtxTpCd);
        mapParam.put("dsOrdCatgCd", orderDataPMsg.dsOrdCatgCd.getValue());
        List<Map<String, Object>> resMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getOrdCatgBizCtx", mapParam);

        return resMapList;
    }
    // 2018/11/13 S21_NA#27954 Add End
    // 2019/06/28 QC#50780 Add Start
    private BigDecimal getDsContrDtlPK(NWZC035002PMsg orderDataPMsg, INV_LINEBean invLineBean) {

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", orderDataPMsg.glblCmpyCd.getValue());
        mapParam.put("cpoOrdNum", orderDataPMsg.cpoOrdNum);
        mapParam.put("cpoDtlLineNum", invLineBean.getInvLineTMsg().cpoDtlLineNum.getValue());
        mapParam.put("cpoDtlLineSubNum", invLineBean.getInvLineTMsg().cpoDtlLineSubNum.getValue());
        mapParam.put("billWithEquipFlg", ZYPConstant.FLG_ON_Y);
        List<BigDecimal> resMapList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getDsContrDtlPK", mapParam);

        if (resMapList != null && resMapList.size() > 0) {
            return resMapList.get(0);
        } else {
            return null;
        }
    }
    // 2019/06/28 QC#50780 Add End

    // START 2023/11/10 [QC#61468, ADD]
    private void convDsInvTpFcOnlyContrSupply(INVBean invBean) {
        INVTMsg invTMsg = invBean.getInvTMsg();
        if (!S21StringUtil.isEquals(DS_INV_TP.INVOICE_SUPPLIES, invTMsg.dsInvTpCd.getValue())) {
            return;
        }
        if ((!hasValue(invTMsg.invTotDealNetAmt)) || (!hasValue(invTMsg.invTotDealFrtAmt))) {
            return;
        }
        if (invTMsg.invTotDealNetAmt.getValue().compareTo(invTMsg.invTotDealFrtAmt.getValue()) != 0) {
            return;
        }
        if (BigDecimal.ZERO.compareTo(invTMsg.invTotDealFrtAmt.getValue()) == 0) {
            return;
        }
        if (S21StringUtil.isEquals(INV_TP.INVOICE, invTMsg.invTpCd.getValue())) {
            setValue(invTMsg.dsInvTpCd, DS_INV_TP.INVOICE_SUPPLIES_CONTRACT);
        } else if (S21StringUtil.isEquals(INV_TP.CREDIT_MEMO, invTMsg.invTpCd.getValue())) {
            setValue(invTMsg.dsInvTpCd, DS_INV_TP.CREDIT_SUPPLIES_CONTRACT);
        }
    }
    // END   2023/11/10 [QC#61468, ADD]

}
