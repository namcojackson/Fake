/**
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC115001;

import static com.canon.cusa.s21.api.NSZ.NSZC115001.constant.NSZC115001Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import parts.common.EZDMsg;
import parts.common.EZDPMsg;
import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BIZ_PROC_LOGTMsg;
import business.db.BLLG_CYCLETMsg;
import business.db.CCYTMsg;
import business.db.CONTR_AUTO_RNW_TPTMsg;
import business.db.CONTR_INTFC_RNW_MAPTMsg;
import business.db.CONTR_INTFC_RNW_MAPTMsgArray;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsgArray;
import business.db.CONTR_UPLFT_TPTMsg;
import business.db.CONTR_UPLFT_TPTMsgArray;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_ACCT_CLSTMsg;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_ACCT_CR_PRFLTMsgArray;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsgArray;
import business.db.DS_CONTR_ADDL_CHRG_RECTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_DTL_RECTMsg;
import business.db.DS_CONTR_DTL_TPTMsg;
import business.db.DS_CONTR_INTFC_DEF_RULETMsg;
import business.db.DS_CONTR_INTFC_DEF_RULETMsgArray;
import business.db.DS_CONTR_RECTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsgArray;
import business.db.DS_CONTR_TAX_DTLTMsg;
import business.db.DS_CONTR_TAX_DTLTMsgArray;
import business.db.DS_CR_CARDTMsg;
import business.db.DS_INV_TGTR_TPTMsg;
import business.db.DS_MDLTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PRC_CATGTMsg;
import business.db.PRC_MTR_PKGTMsg;
import business.db.PRC_MTR_PKGTMsgArray;
import business.db.PRC_MTR_PKG_BLLG_MTRTMsg;
import business.db.PRC_SVC_PLN_TPTMsg;
import business.db.RNW_RATIO_BY_TERMTMsg;
import business.db.RNW_RATIO_BY_TERMTMsgArray;
import business.db.SCHD_CRAT_TMPLTMsg;
import business.db.SCHD_CRAT_TMPL_LINETMsg;
import business.db.SCHD_CRAT_TMPL_LINETMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHPG_INTVLTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMOTMsgArray;
import business.db.SVC_MEMO_RECTMsg;
import business.db.SVC_TERM_CONDTMsg;
import business.db.SVC_TERM_CONDTMsgArray;
import business.db.XS_CHRG_TPTMsg;
import business.db.XS_CHRG_TPTMsgArray;
import business.parts.NSZC115001PMsg;
import business.parts.NSZC115001_svcAddlBasePrcListPMsg;
import business.parts.NSZC115001_svcAddlBasePrcListPMsgArray;
import business.parts.NSZC115001_svcAddlChrgPrcListPMsg;
import business.parts.NSZC115001_svcConfigRefListPMsg;
import business.parts.NSZC115001_svcConfigRefListPMsgArray;
import business.parts.NSZC115001_svcDtlListPMsg;
import business.parts.NSZC115001_svcDtlListPMsgArray;
import business.parts.NSZC115001_svcPrcListPMsg;
import business.parts.NSZC115001_svcPrcListPMsgArray;
import business.parts.NSZC115001_svcUsgPrcListPMsg;
import business.parts.NSZC115001_svcUsgPrcListPMsgArray;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC172001PMsg;
import business.parts.NWZC172002PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC115001.constant.NSZC115001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC172001.NWZC172001;
import com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001DefaultSvcPgmGetter;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NSX.NSXC002001.DefSvcTermCondInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SvcTermCond;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001Exchange;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BILL_BY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_EDI;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TGTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_PLN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_TIER_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_DOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_BY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;

/**
 * <pre>
 * NSZC1150 : Contract Import API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/11   Hitachi         T.Kanasaka      Create          RS#7247
 * 2017/05/15   Hitachi         T.Tomita        Update          RS#8377
 * 2017/05/22   Hitachi         T.Tomita        Update          RS#8394
 * 2017/05/24   Hitachi         T.Tomita        Update          RS#8396
 * 2017/05/24   Hitachi         A.Kohinata      Update          RS#8379
 * 2017/05/25   Hitachi         T.Tomita        Update          RS#8398
 * 2017/06/06   Hitachi         T.Tomita        Update          QC#18846
 * 2017/06/12   Hitachi         A.Kohinata      Update          QC#18948
 * 2017/06/12   Hitachi         T.Kanasaka      Update          QC#18873
 * 2017/06/14   Hitachi         A.Kohinata      Update          QC#18821
 * 2017/06/14   Hitachi         Y.Takeno        Update          QC#18934
 * 2017/06/14   Hitachi         A.Kohinata      Update          QC#18841
 * 2017/06/15   Hitachi         Y.Takeno        Update          QC#18985
 * 2017/06/19   Hitachi         K.Ochiai        Update          QC#19324
 * 2017/06/19   Hitachi         K.Kitachi       Update          QC#18855
 * 2017/06/19   Hitachi         A.Kohinata      Update          QC#19036
 * 2017/06/20   Hitachi         Y.Takeno        Update          QC#18985-1
 * 2017/06/21   Hitachi         K.Kitachi       Update          QC#18068
 * 2017/06/22   Hitachi         K.Kitachi       Update          QC#19394,19441
 * 2017/06/22   Hitachi         T.Kanasaka      Update          QC#19350
 * 2017/06/22   Hitachi         A.Kohinata      Update          QC#19330
 * 2017/06/22   Hitachi         T.Kanasaka      Update          QC#19340
 * 2017/06/23   Hitachi         K.Kitachi       Update          QC#18068
 * 2017/06/27   Hitachi         T.Kanasaka      Update          QC#19560
 * 2017/06/29   Hitachi         K.Kitachi       Update          QC#18068
 * 2017/06/30   Hitachi         T.Kanasaka      Update          QC#19700
 * 2017/07/05   Hitachi         K.Kim           Update          QC#19672
 * 2017/07/06   Hitachi         Y.Takeno        Update          QC#19736
 * 2017/07/10   Hitachi         T.Tomita        Update          QC#19818
 * 2017/07/10   Hitachi         Y.Takeno        Update          QC#18985-2
 * 2017/07/21   Hitachi         T.Tomita        Update          QC#19962
 * 2017/07/26   Hitachi         K.Kojima        Update          QC#18203
 * 2017/07/28   Hitachi         Y.Takeno        Update          QC#20088
 * 2017/08/02   Hitachi         Y.Takeno        Update          QC#19792
 * 2017/08/03   Hitachi         Y.Takeno        Update          QC#20378
 * 2017/08/08   Hitachi         A.Kohinata      Update          QC#18799
 * 2017/08/09   Hitachi         K.Kim           Update          QC#20228
 * 2017/08/09   Hitachi         K.Kojima        Update          QC#20527
 * 2017/08/15   Hitachi         Y.Takeno        Update          QC#20378-2
 * 2017/08/18   Hitachi         Y.Takeno        Update          QC#20651
 * 2017/08/07   Hitachi         K.Kasai         Update          QC#18193,18195
 * 2017/08/23   Hitachi         U.Kim           Update          QC#18085
 * 2017/08/25   Hitachi         U.Kim           Update          QC#18345
 * 2017/08/28   Hitachi         Y.Takeno        Update          QC#20665
 * 2017/08/29   Hitachi         T.Kanasaka      Update          QC#20831
 * 2017/08/31   Hitachi         Y.Takeno        Update          QC#20665-1
 * 2017/08/31   Hitachi         U.Kim           Update          QC#18345-2
 * 2017/09/01   Hitachi         A.Kohinata      Update          QC#20882
 * 2017/09/04   Hitachi         Y.Takeno        Update          QC#20847
 * 2017/09/06   CITS            M.Naito         Update          QC#18724
 * 2017/09/12   Fujitsu         M.Yamada        Update          QC#20336(L3#397)
 * 2017/09/16   Hitachi         Y.Takeno        Update          QC#21007
 * 2017/10/06   Fujitsu         K.Ishizuka      Update          QC#21393
 * 2017/10/05   Hitachi         A.Kohinata      Update          QC#21542
 * 2017/10/17   Hitachi         Y.Takeno        Update          QC#21851
 * 2017/10/20   CITS            M.Naito         Update          QC#21484
 * 2017/11/20   Hitachi         K.Ochiai        Update          QC#21698
 * 2017/11/20   Hitachi         K.Ochiai        Update          QC#21700
 * 2017/12/05   Fujitsu         K.Ishizuka      Update          QC#22258
 * 2017/12/05   Hitachi         K.Kojima        Update          QC#21551
 * 2017/12/11   Hitachi         K.Kojima        Update          QC#19955
 * 2018/01/04   Hitachi         M.Kidokoro      Update          QC#23167
 * 2017/12/27   Hitachi         U.Kim           Update          QC#23104
 * 2018/01/09   Hitachi         M.Kidokoro      Update          QC#20635
 * 2018/01/30   CITS            M.Naito         Update          QC#21349
 * 2018/02/13   Hitachi         K.Kojima        Update          QC#23357
 * 2018/02/26   CITS            M.Naito         Update          QC#23934
 * 2018/02/20   Hitachi         K.Kojima        Update          QC#24234
 * 2018/03/06   Hitachi         U.Kim           Update          QC#24056
 * 2018/03/13   Hitachi         K.Kojima        Update          QC#24263
 * 2018/03/13   CITS            M.Naito         Update          QC#23378
 * 2018/03/23   Hitachi         K.Kojima        Update          QC#24458
 * 2018/04/09   Fujitsu         M.Ohno          Update          QC#20162
 * 2018/05/10   Fujitsu         M.Yamada        Update          QC#25030
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2018/05/15   Fujitsu         M.Yamada        Update          QC#25030
 * 2018/05/24   Hitachi         K.Kishimoto     Update          QC#26062
 * 2018/06/11   Fujitsu         T.Ogura         Update          QC#24852
 * 2018/07/06   Hitachi         K.Kitachi       Update          QC#26886
 * 2018/07/09   Hitachi         K.Kim           Update          QC#26611
 * 2018/07/09   Hitachi         K.Kojima        Update          QC#27135
 * 2018/07/09   Fujitsu         K.Ishizuka      Update          QC#26528
 * 2018/07/23   Hitachi         K.Kitachi       Update          QC#26589
 * 2018/07/27   Hitachi         K.Kitachi       Update          QC#26589
 * 2018/08/01   Fujitsu         M.Yamada        Update          QC#27005
 * 2018/08/22   Hitachi         K.Kojima        Update          QC#27867
 * 2018/08/22   Hitachi         A.Kohinata      Update          QC#23920
 * 2018/08/23   Hitachi         K.Kishimoto     Update          QC#22821
 * 2018/08/23   Fujitsu         K.Ishizuka      Update          QC#25105
 * 2018/09/10   Fujitsu         K.Ishizuka      Update          QC#28104
 * 2018/09/19   Hitachi         K.Kitachi       Update          QC#28164
 * 2018/11/15   Hitachi         K.Kim           Update          QC#28638
 * 2018/12/11   Fujitsu         M.Yamada        Update          QC#29248
 * 2018/12/19   Fujitsu         W.Honda         Update          QC#29636
 * 2019/01/08   Fujitsu         K.Ishizuka      Update          QC#29788
 * 2019/01/10   Hitachi         S.Kitamura      Update          QC#26928
 * 2019/01/15   Fujitsu         R.Nakamura      Update          QC#29860
 * 2019/01/18   Hitachi         T.Kanasaka      Update          QC#29081
 * 2019/01/18   Hitachi         K.Kim           Update          QC#29782
 * 2019/01/28   Hitachi         T.Tomita        Update          QC#26928
 * 2019/01/29   Fujitsu         Y.Kanefusa      Update          QC#29371
 * 2019/01/30   Hitachi         K.Kim           Update          QC#30137
 * 2019/02/05   Hitachi         K.Kim           Update          QC#30244
 * 2019/02/26   Hitachi         K.Fujimoto      Update          QC#30525
 * 2019/05/08   Fujitsu         K.Kato          Update          QC#50174
 * 2019/05/23   Fujitsu         W.Honda         Update          QC#50157
 * 2019/06/17   Fujitsu         W.Honda         Update          QC#50842
 * 2019/11/22   Fujitsu         S.Iidaka        Update          QC#51325
 * 2020/03/10   Fujitsu         C.Hara          Update          QC#56125
 * 2020/03/24   Hitachi         A.Kohinata      Update          QC#54318
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2023/07/05   CITS            T.Kojima        Update          QC#61472
 * 2023/11/08   CITS            R.Jin           Update          QC#62108
 *</pre>
 */
public class NSZC115001 extends S21ApiCommonBase {

    // -- Internal Variable ---------------
    /** SSM client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Cache of 'findByCondition' */
    private S21LRUMap<String, EZDTMsgArray> tMsgByCondMap = null;

    /** Cache of 'getMdlIdByCpoDtl' */
    private S21LRUMap<String, BigDecimal> mdlIdByCpoMap = null;

    /** Cache of 'getSvcConfigMstrPkByCpoDtl' */
    private S21LRUMap<String, BigDecimal> svcConfigMstrPkByCpoMap = null;

    /** Cache of 'getDsContrDtlGrpNumByMap' */
    private S21LRUMap<String, BigDecimal> dsContrDtlGrpNumMap = null;

    /** Cache of 'getFltAggDsContrDtlPkByMap' */
    private S21LRUMap<String, BigDecimal> fltAggDsContrDtlPkMap = null;

    /**
     * Old Supply Agreement Plan Map<dsContrDtlPk, splyAgmtPlnPk>
     */
    private Map<BigDecimal, BigDecimal> oldSplyAgmtPlnPkMap = null;

    /**
     * New Supply Agreement Plan Map<dsContrDtlPk,
     * List<Map<QueryResult>>)
     */
    private Map<BigDecimal, List<Map<String, Object>>> newSplyAgmtPlnPkMap = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** online/batch type */
    private ONBATCH_TYPE onBatchType = null;

    /** DS_CONTR_INTFC_DEF_RULETMsg */
    private DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = null;

    /** Currency Code */
    private String ccyCd = null;

    // START 2017/12/27 U.Kim [QC#23104,ADD]
    private BigDecimal dealCcyDigit = null;
    // END 2017/12/27 U.Kim [QC#23104,ADD]

    /** CPOTMsg */
    private CPOTMsg cpoTMsg = null;

    // START 2018/03/13 K.Kojima [QC#24263,DEL]
    // /** Contact Person PK */
    // private BigDecimal ctacPsnPk = null;
    // END 2018/03/13 K.Kojima [QC#24263,DEL]

    /** Toc Code */
    private String tocCd = null;

    /** Service Contract Branch Code */
    private String svcContrBrCd = null;

    /** Contract Admin Pserson Code */
    private String contrAdminPsnCd = null;

    /** Service Line of Business Code */
    private String svcLineBizCd = null;

    /** Base Charge To Lease Company Flag */
    private String baseChrgToLeaseCmpyFlg = null;

    /** Usage Charge To Lease Company Flag */
    private String usgChrgToLeaseCmpyFlg = null;

    // START 2018/01/04 M.Kidokoro [QC#23167,ADD]
    /** before BillBy Map */
    private Map<String, String> baseChrgToLeaseCmpyFlgMap = new HashMap<String, String>();

    /** before BillBy Map */
    private Map<String, String> usgChrgToLeaseCmpyFlgMap = new HashMap<String, String>();
    // END 2018/01/04 M.Kidokoro [QC#23167,ADD]

    /** DS Contract Detail PK List For Usage Removal */
    private List<BigDecimal> dsContrDtlPkListForUsgRemoval = null;

    /** DS Contract Detail PK List For Accessory or Additional Charge Removal */
    private List<BigDecimal> dsContrDtlPkListForAsryAddlRemoval = null;

    /** Delete Shell Line Num List For Add Contract */
    private List<BigDecimal> delShellLineNumListForAddContr = null;

    /** Exist DEAL_SVC_COST_TRNSF_AMT (Renew/Uplift) */
    private String isExistTrnsfAmtFlg = null;

    /** ADD_CHRG_TP_CD (Rental Accessory / Varchar Const) */
    private String rentalAddlChrgTpCdAcc = null;

    /** ADD_CHRG_TP_CD (Rental Machine / Varchar Const) */
    private String rentalAddlChrgTpCdMac = null;

    // START 2018/05/24 [QC#26062, ADD]
    /** Do Not Renewal/Uplift Service Program Type Code (Varchar Const) */
    private List<String> doNotRnwUpliftSvcPgmTp = null;

    // END   2018/05/24 [QC#26062, ADD]

    /** code convert map(PRC_TIER_SVC_OFFER_CD -> XS_CHRG_TP_CD) */
    private Map<String, Object> xsChrgTpConvMap = null;

    /** map<svcTermCondPk, dsContrDtlPk> for update cap qty QC#20336(L3#397) */
    private Map<BigDecimal, BigDecimal> updateCapQtyPkMap = new HashMap<BigDecimal,BigDecimal>();

    // START 2018/01/04 M.Kidokoro [QC#23167,ADD]
    /** before BillBy Map */
    private Map<BigDecimal, String> beforeBillByTPMap = new HashMap<BigDecimal, String>();
    // END 2018/01/04 M.Kidokoro [QC#23167,ADD]

    // START 2018/02/20 K.Kojima [QC#24234,ADD]
    /** update BillToCustCd */
    private Map<BigDecimal, String> updateBillToCustCd = new HashMap<BigDecimal, String>();

    // END 2018/02/20 K.Kojima [QC#24234,ADD]

    // START 2018/03/06 U.Kim [QC#24056,ADD]
    /** exists Error Message Flag **/
    private boolean existsErrMsgFlg = false;
    // END 2018/03/06 U.Kim [QC#24056,ADD]

    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NSZC115001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.tMsgByCondMap = new S21LRUMap<String, EZDTMsgArray>();
        this.mdlIdByCpoMap = new S21LRUMap<String, BigDecimal>();
        this.svcConfigMstrPkByCpoMap = new S21LRUMap<String, BigDecimal>();
        this.dsContrDtlGrpNumMap = new S21LRUMap<String, BigDecimal>();
        this.fltAggDsContrDtlPkMap = new S21LRUMap<String, BigDecimal>();
        this.oldSplyAgmtPlnPkMap = new HashMap<BigDecimal, BigDecimal>();
        this.newSplyAgmtPlnPkMap = new HashMap<BigDecimal, List<Map<String, Object>>>();
        this.dsContrDtlPkListForUsgRemoval = new ArrayList<BigDecimal>();
        this.dsContrDtlPkListForAsryAddlRemoval = new ArrayList<BigDecimal>();
        this.delShellLineNumListForAddContr = new ArrayList<BigDecimal>();
    }

    /**
     * <pre>
     * execute Shell API (List)
     * </pre>
     * @param inPrmMsgAry Input parameter list.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final List<NSZC115001PMsg> inPrmMsgAry, final ONBATCH_TYPE onBatchType) {
        for (int i = 0; i < inPrmMsgAry.size(); i++) {
            execute(inPrmMsgAry.get(i), onBatchType);
            clearFieldValue();
        }
    }

    private void clearFieldValue() {
        this.dsContrIntfcDefRuleTMsg = null;
        this.ccyCd = null;
        this.cpoTMsg = null;
        // START 2018/03/13 K.Kojima [QC#24263,DEL]
        // this.ctacPsnPk = null;
        // END 2018/03/13 K.Kojima [QC#24263,DEL]
        this.tocCd = null;
        this.svcContrBrCd = null;
        this.contrAdminPsnCd = null;
        this.svcLineBizCd = null;
        this.baseChrgToLeaseCmpyFlg = null;
        this.usgChrgToLeaseCmpyFlg = null;
        this.dsContrDtlPkListForUsgRemoval.clear();
        this.dsContrDtlPkListForAsryAddlRemoval.clear();
        this.delShellLineNumListForAddContr.clear();
        this.isExistTrnsfAmtFlg = null;
    }

    /**
     * <pre>
     * execute Shell API
     * </pre>
     * @param inPrmPMsg Input parameter
     * @param onBatchType Kind of Online or Batch
     */
    public void execute(final NSZC115001PMsg inPrmPMsg, final ONBATCH_TYPE onBatchType) {

        // Create Message Map
        final S21ApiMessageMap msgMap = new S21ApiMessageMap(inPrmPMsg);
        this.glblCmpyCd = ((NSZC115001PMsg) msgMap.getPmsg()).glblCmpyCd.getValue();
        this.onBatchType = onBatchType;
        createXsChrgTpConvMap();

        try {

            // Shingle Check
            checkSngl(msgMap);
            if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                return;
            }

            // Copy Mode
            if (PROC_MODE_COPY.equals(inPrmPMsg.xxProcMd.getValue())) {
                executeCopy(msgMap);
                return;
            }

            // Physical Delete Mode
            if (PROC_MODE_PHYS_DEL.equals(inPrmPMsg.xxProcMd.getValue())) {
                executePhysDel(msgMap);
                return;
            }

            // B/w Equipment Mode
            if (PROC_MODE_BW_EQUIP.equals(inPrmPMsg.xxProcMd.getValue())) {
                executeBwEquip(msgMap);
                return;
            }

            // DB Check
            checkDB(msgMap);
            if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                return;
            }

            // Combination Check
            checkComb(msgMap);
            if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                return;
            }

            // Decline Service Check
            checkDclnSvc(msgMap);
            if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                return;
            }

            // get Old SPLY_AGMT_PLN_PK
            setOldSplyAgmtPlnPk(msgMap);

            regist(msgMap);
            if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                return;
            }

            // 
            checkPrcMtrPkg(msgMap);
            if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                return;
            }

            // locked by another user
        } catch (EZDDBRecordLockedException e) {
            throw e;

        } finally {

            // Flush Message Map.
            msgMap.flush();
        }
    }

    private void createXsChrgTpConvMap() {
        this.xsChrgTpConvMap = new HashMap<String, Object>();

        XS_CHRG_TPTMsg param = new XS_CHRG_TPTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);

        XS_CHRG_TPTMsgArray tMsgArray = (XS_CHRG_TPTMsgArray) EZDTBLAccessor.findByCondition(param);
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            XS_CHRG_TPTMsg tMsg = tMsgArray.no(i);
            this.xsChrgTpConvMap.put(tMsg.prcTierSvcOfferCd.getValue(), tMsg);
        }
    }

    private void checkSngl(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(inPrmPMsg.glblCmpyCd)) {
            setErrStatus(msgMap, NSZM0001E); //mod u.kim 2018/03/06 QC#24056
        }

        if (!ZYPCommonFunc.hasValue(inPrmPMsg.xxProcMd)) {
            setErrStatus(msgMap, NSZM0003E); //mod u.kim 2018/03/06 QC#24056

        } else if (!PROC_MODE_NEW.equals(inPrmPMsg.xxProcMd.getValue()) && !PROC_MODE_MOD.equals(inPrmPMsg.xxProcMd.getValue()) && !PROC_MODE_COPY.equals(inPrmPMsg.xxProcMd.getValue())
                && !PROC_MODE_PHYS_DEL.equals(inPrmPMsg.xxProcMd.getValue()) && !PROC_MODE_BW_EQUIP.equals(inPrmPMsg.xxProcMd.getValue())) {
            setErrStatus(msgMap, NSZM0004E); //mod u.kim 2018/03/06 QC#24056
        }

        if (PROC_MODE_BW_EQUIP.equals(inPrmPMsg.xxProcMd.getValue()) || PROC_MODE_PHYS_DEL.equals(inPrmPMsg.xxProcMd.getValue())) {
            return;
        }

        if (!ZYPCommonFunc.hasValue(inPrmPMsg.slsDt)) {
            setErrStatus(msgMap, NSZM0002E); //mod u.kim 2018/03/06 QC#24056
        }

        if (!ZYPCommonFunc.hasValue(inPrmPMsg.refCpoOrdNum)) {
            setErrStatus(msgMap, NSZM1120E); //mod u.kim 2018/03/06 QC#24056
        }

        if (PROC_MODE_COPY.equals(inPrmPMsg.xxProcMd.getValue())) {
            return;
        }

        checkSnglSvcDtlList(msgMap);
        checkSnglSvcConfigRefList(msgMap);
        checkSnglSvcPrcList(msgMap);
        checkSnglSvcUsgPrcList(msgMap);
        checkSnglSvcAddlBasePrcList(msgMap);
        checkSnglSvcAddlChrgPrcList(msgMap);
    }

    private void checkSnglSvcDtlList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);

            // 2018/05/07 QC#22482 Add Start
            boolean isManOvrd = isManOvrd(svcDtlPMsg);
            // 2018/05/07 QC#22482 Add End

            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.xxRqstTpCd)) {
                addMsgForSvcDtlList(msgMap, NSZM1118E, svcDtlPMsg);
            } else if (!RQST_TP_NEW.equals(svcDtlPMsg.xxRqstTpCd.getValue()) && !RQST_TP_MOD.equals(svcDtlPMsg.xxRqstTpCd.getValue()) && !RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                addMsgForSvcDtlList(msgMap, NSZM1119E, svcDtlPMsg);
            }

            if (RQST_TP_MOD.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk)) {
                    addMsgForSvcDtlList(msgMap, NSZM0856E, svcDtlPMsg);
                }
            } else if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk)) {
                    addMsgForSvcDtlList(msgMap, NSZM0856E, svcDtlPMsg);
                }
                continue;
            }

            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.shellLineNum)) {
                addMsgForSvcDtlList(msgMap, NSZM1121E, svcDtlPMsg);
            }

            // 2018/05/07 QC#22482 Mod Start
//            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.svcPgmMdseCd)) {
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.svcPgmMdseCd) && !isManOvrd) {
            // 2018/05/07 QC#22482 Mod End
                addMsgForSvcDtlList(msgMap, NSZM1122E, svcDtlPMsg);
            }

            // 2018/05/07 QC#22482 Mod Start
//            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.prcSvcContrTpCd)) {
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.prcSvcContrTpCd) && !isManOvrd) {
            // 2018/05/07 QC#22482 Mod End
                addMsgForSvcDtlList(msgMap, NSZM1123E, svcDtlPMsg);
            }

            // 2018/05/07 QC#22482 Mod Start
//            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.prcSvcPlnTpCd)) {
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.prcSvcPlnTpCd) && !isManOvrd) {
            // 2018/05/07 QC#22482 Mod End
                addMsgForSvcDtlList(msgMap, NSZM1124E, svcDtlPMsg);
            }

            // 2018/05/07 QC#22482 Mod Start
//            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrCatgCd)) {
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrCatgCd) && !isManOvrd) {
            // 2018/05/07 QC#22482 Mod End
                addMsgForSvcDtlList(msgMap, NSZM1125E, svcDtlPMsg);
            }

            // 2018/05/07 QC#22482 Mod Start
//            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.baseBllgCycleCd)) {
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.baseBllgCycleCd) && !isManOvrd) {
            // 2018/05/07 QC#22482 Mod End
                addMsgForSvcDtlList(msgMap, NSZM1126E, svcDtlPMsg);
            }

            // 2018/05/07 QC#22482 Mod Start
//            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.fromPerMthNum)) {
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.fromPerMthNum) && !isManOvrd) {
            // 2018/05/07 QC#22482 Mod End
                addMsgForSvcDtlList(msgMap, NSZM1127E, svcDtlPMsg);
            }

            // 2018/05/07 QC#22482 Mod Start
//            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.toPerMthNum)) {
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.toPerMthNum) && !isManOvrd) {
            // 2018/05/07 QC#22482 Mod End
                addMsgForSvcDtlList(msgMap, NSZM1128E, svcDtlPMsg);
            }

            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.billWithEquipFlg)) {
                addMsgForSvcDtlList(msgMap, NSZM1129E, svcDtlPMsg);
            }

            // 2018/05/07 QC#22482 Mod Start
//            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.billByTpCd)) {
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.billByTpCd) && !isManOvrd) {
            // 2018/05/07 QC#22482 Mod End
                addMsgForSvcDtlList(msgMap, NSZM1130E, svcDtlPMsg);
            }

            // 2018/05/07 QC#22482 Mod Start
//            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.soldToCustLocCd)) {
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.soldToCustLocCd) && !isManOvrd) {
            // 2018/05/07 QC#22482 Mod End
                addMsgForSvcDtlList(msgMap, NSZM1131E, svcDtlPMsg);
            }

            // 2018/05/07 QC#22482 Mod Start
//            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.sellToCustCd)) {
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.sellToCustCd) && !isManOvrd) {
            // 2018/05/07 QC#22482 Mod End
                addMsgForSvcDtlList(msgMap, NSZM0016E, svcDtlPMsg);
            }
        }
    }

    private void checkSnglSvcConfigRefList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcConfigRefList.getValidCount(); i++) {
            svcConfigRefPMsg = inPrmPMsg.svcConfigRefList.no(i);

            if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.xxRqstTpCd)) {
                addMsgForSvcConfigRefList(msgMap, NSZM1118E, svcConfigRefPMsg);
            } else if (!RQST_TP_NEW.equals(svcConfigRefPMsg.xxRqstTpCd.getValue()) && !RQST_TP_MOD.equals(svcConfigRefPMsg.xxRqstTpCd.getValue()) && !RQST_TP_DEL.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                addMsgForSvcConfigRefList(msgMap, NSZM1119E, svcConfigRefPMsg);
            }

            if (RQST_TP_MOD.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.dsContrDtlPk)) {
                    addMsgForSvcConfigRefList(msgMap, NSZM0857E, svcConfigRefPMsg);
                }

            } else if (RQST_TP_DEL.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.dsContrDtlPk)) {
                    addMsgForSvcConfigRefList(msgMap, NSZM0857E, svcConfigRefPMsg);
                }
                continue;

            } else if (RQST_TP_NEW.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                svcConfigRefPMsg.dsContrDtlPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_DTL_SQ));

            }

            if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.shellLineNum)) {
                addMsgForSvcConfigRefList(msgMap, NSZM1121E, svcConfigRefPMsg);
            }

            if (!isFleet(inPrmPMsg, svcConfigRefPMsg.shellLineNum.getValue())) {
                if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.dsOrdPosnNum)) {
                    addMsgForSvcConfigRefList(msgMap, NSZM1132E, svcConfigRefPMsg);
                }
            }

            if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.cpoOrdNum)) {
                addMsgForSvcConfigRefList(msgMap, NSZM0402E, svcConfigRefPMsg);
            }

            if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.cpoDtlLineNum)) {
                addMsgForSvcConfigRefList(msgMap, NSZM0403E, svcConfigRefPMsg);
            }

            if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.cpoDtlLineSubNum)) {
                addMsgForSvcConfigRefList(msgMap, NSZM0404E, svcConfigRefPMsg);
            }

            // START 2017/08/18 [QC#20651, DEL]
            // if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.mtrReadMethCd)) {
            //     if (hasUsgBllgCycle(svcConfigRefPMsg.shellLineNum.getValue(), inPrmPMsg)) {
            //         addMsgForSvcConfigRefList(msgMap, NSZM1133E, svcConfigRefPMsg);
            //     }
            // }
            // END   2017/08/18 [QC#20651, DEL]
        }
    }

    private boolean hasUsgBllgCycle(BigDecimal shellLineNum, NSZC115001PMsg pMsg) {
        for (int i = 0; i < pMsg.svcDtlList.getValidCount(); i++) {
            NSZC115001_svcDtlListPMsg csdPMsg = pMsg.svcDtlList.no(i);
            if (!ZYPCommonFunc.hasValue(csdPMsg.shellLineNum)) {
                continue;
            }
            if (shellLineNum.compareTo(csdPMsg.shellLineNum.getValue()) == 0) {
                return ZYPCommonFunc.hasValue(csdPMsg.usgBllgCycleCd);
            }
        }
        return true;
    }

    private void checkSnglSvcPrcList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        NSZC115001_svcPrcListPMsg svcPrcPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcPrcList.getValidCount(); i++) {
            svcPrcPMsg = inPrmPMsg.svcPrcList.no(i);

            if (!ZYPCommonFunc.hasValue(svcPrcPMsg.xxRqstTpCd)) {
                addMsgForSvcPrcList(msgMap, NSZM1118E, svcPrcPMsg);
            } else if (!RQST_TP_NEW.equals(svcPrcPMsg.xxRqstTpCd.getValue())
                    && !RQST_TP_MOD.equals(svcPrcPMsg.xxRqstTpCd.getValue())
                    && !RQST_TP_DEL.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                addMsgForSvcPrcList(msgMap, NSZM1119E, svcPrcPMsg);
            }

            if (RQST_TP_MOD.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(svcPrcPMsg.dsContrDtlPk)) {
                    addMsgForSvcPrcList(msgMap, NSZM0857E, svcPrcPMsg);
                }

                if (!ZYPCommonFunc.hasValue(svcPrcPMsg.dsContrPk)) {
                    addMsgForSvcPrcList(msgMap, "NSZM9999E", svcPrcPMsg);
                }

            } else if (RQST_TP_DEL.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(svcPrcPMsg.dsContrDtlPk)) {
                    addMsgForSvcPrcList(msgMap, NSZM0857E, svcPrcPMsg);
                }
                continue;
            }

            if (!ZYPCommonFunc.hasValue(svcPrcPMsg.shellLineNum)) {
                addMsgForSvcPrcList(msgMap, NSZM1121E, svcPrcPMsg);
            }

            // add start 2017/06/14 CSA Defect#18841
            if (!isFleet(inPrmPMsg, svcPrcPMsg.shellLineNum.getValue()) && !ZYPCommonFunc.hasValue(svcPrcPMsg.dsOrdPosnNum)) {
                continue;
            }
            // add end 2017/06/14 CSA Defect#18841

            // 2018/05/07 QC#22482 Add Start
            if (isManOvrd(inPrmPMsg, svcPrcPMsg.shellLineNum.getValue())) {
                continue;
            }
            // 2018/05/07 QC#22482 Add End

            if (!isFleet(inPrmPMsg, svcPrcPMsg.shellLineNum.getValue())) {
//                if (!ZYPCommonFunc.hasValue(svcPrcPMsg.dsOrdPosnNum)) {
//                    addMsgForSvcPrcList(msgMap, NSZM1132E, svcPrcPMsg);
//                }

                if (!ZYPCommonFunc.hasValue(svcPrcPMsg.mdlId)) {
                    addMsgForSvcPrcList(msgMap, NSZM1134E, svcPrcPMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(svcPrcPMsg.mdlId)) {
                    addMsgForSvcPrcList(msgMap, NSZM1187E, svcPrcPMsg);
                }
            }

            if (!ZYPCommonFunc.hasValue(svcPrcPMsg.maintPrcCatgCd)) {
                addMsgForSvcPrcList(msgMap, NSZM1135E, svcPrcPMsg);
            }

            if (!isManOvrd(svcPrcPMsg, inPrmPMsg)
                    && !isUsgBllgCycleEmpty(svcPrcPMsg, inPrmPMsg)) {
                if (!ZYPCommonFunc.hasValue(svcPrcPMsg.prcMtrPkgPk)) {
                    addMsgForSvcPrcList(msgMap, NSZM1136E, svcPrcPMsg);
                }
            }

            if (!ZYPCommonFunc.hasValue(svcPrcPMsg.basePrcDealAmt)) {
                addMsgForSvcPrcList(msgMap, NSZM1137E, svcPrcPMsg);
            } else {
                if (svcPrcPMsg.basePrcDealAmt.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    addMsgForSvcPrcList(msgMap, NSZM1211E, svcPrcPMsg);
                }
                if (MAX_BASE_PRC.compareTo(svcPrcPMsg.basePrcDealAmt.getValue()) < 0) {
                    addMsgForSvcPrcList(msgMap, NSZM1190E, svcPrcPMsg);
                }
            }

            if (!ZYPCommonFunc.hasValue(svcPrcPMsg.dealPrcListPrcAmt)) {
                addMsgForSvcPrcList(msgMap, NSZM1138E, svcPrcPMsg);
            } else {
                if (svcPrcPMsg.dealPrcListPrcAmt.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    addMsgForSvcPrcList(msgMap, NSZM1211E, svcPrcPMsg);
                }
            }
        }
    }

    private boolean isManOvrd(NSZC115001_svcPrcListPMsg svcPrcPMsg, NSZC115001PMsg inPrmPMsg) {
        NSZC115001_svcDtlListPMsg svcDtlPMsg = getSvcDtlPMsg(inPrmPMsg, svcPrcPMsg);
        if (DS_CONTR_CATG.FLEET.equals(svcDtlPMsg.dsContrCatgCd.getValue())) {
            return ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.manContrOvrdFlg.getValue());
        }
        BigDecimal shellLineNum = svcPrcPMsg.shellLineNum.getValue();

        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            NSZC115001_svcDtlListPMsg csdPMsg = inPrmPMsg.svcDtlList.no(i);
            if (shellLineNum.compareTo(csdPMsg.shellLineNum.getValue()) == 0) {
                return ZYPConstant.FLG_ON_Y.equals(csdPMsg.manContrOvrdFlg.getValue());
            }
        }
        return false;
    }

    private NSZC115001_svcDtlListPMsg getSvcDtlPMsg(NSZC115001PMsg inPrmPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg) {
        BigDecimal shellLineNum = svcPrcPMsg.shellLineNum.getValue();
        if (!ZYPCommonFunc.hasValue(shellLineNum)) {
            return new NSZC115001_svcDtlListPMsg();
        }
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            NSZC115001_svcDtlListPMsg svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.shellLineNum)) {
                continue;
            }
            if (shellLineNum.compareTo(svcDtlPMsg.shellLineNum.getValue()) != 0) {
                continue;
            }
            return svcDtlPMsg;
        }
        return new NSZC115001_svcDtlListPMsg();
    }

    private boolean isUsgBllgCycleEmpty(NSZC115001_svcPrcListPMsg svcPrcPMsg, NSZC115001PMsg inPrmPMsg) {
        boolean isUsgBllgCycleEmpty = false;
        BigDecimal shellLineNum = svcPrcPMsg.shellLineNum.getValue();
        if (!ZYPCommonFunc.hasValue(shellLineNum)) {
            return isUsgBllgCycleEmpty;
        }
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            NSZC115001_svcDtlListPMsg svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.shellLineNum)) {
                continue;
            }
            if (shellLineNum.compareTo(svcDtlPMsg.shellLineNum.getValue()) == 0) {
                if (!ZYPCommonFunc.hasValue(svcDtlPMsg.usgBllgCycleCd)) {
                    isUsgBllgCycleEmpty = true;
                    break;
                }
            }
        }
        return isUsgBllgCycleEmpty;
    }

    private void checkSnglSvcUsgPrcList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcUsgPrcList.getValidCount(); i++) {
            svcUsgPrcPMsg = inPrmPMsg.svcUsgPrcList.no(i);

            if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.xxRqstTpCd)) {
                addMsgForSvcUsgPrcList(msgMap, NSZM1118E, svcUsgPrcPMsg);
            } else if (!RQST_TP_NEW.equals(svcUsgPrcPMsg.xxRqstTpCd.getValue()) && !RQST_TP_MOD.equals(svcUsgPrcPMsg.xxRqstTpCd.getValue()) && !RQST_TP_DEL.equals(svcUsgPrcPMsg.xxRqstTpCd.getValue())) {
                addMsgForSvcUsgPrcList(msgMap, NSZM1119E, svcUsgPrcPMsg);
            }

            if (RQST_TP_MOD.equals(svcUsgPrcPMsg.xxRqstTpCd.getValue())) {
//                if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.contrPhysBllgMtrRelnPk)) {
//                    addMsgForSvcUsgPrcList(msgMap, NSZM1139E, svcUsgPrcPMsg);
//                }

                if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.dsContrPk)) {
                    addMsgForSvcUsgPrcList(msgMap, NSZM0856E, svcUsgPrcPMsg);
                }

                if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.dsContrDtlPk)) {
                    addMsgForSvcUsgPrcList(msgMap, NSZM0857E, svcUsgPrcPMsg);
                }

            } else if (RQST_TP_DEL.equals(svcUsgPrcPMsg.xxRqstTpCd.getValue())) {
//                if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.contrPhysBllgMtrRelnPk)) {
//                    addMsgForSvcUsgPrcList(msgMap, NSZM1139E, svcUsgPrcPMsg);
//                }
                continue;
            }

            if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.shellLineNum)) {
                addMsgForSvcUsgPrcList(msgMap, NSZM1121E, svcUsgPrcPMsg);
            }

            // add start 2017/06/14 CSA Defect#18841
            if (!isFleet(inPrmPMsg, svcUsgPrcPMsg.shellLineNum.getValue()) && !ZYPCommonFunc.hasValue(svcUsgPrcPMsg.dsOrdPosnNum)) {
                continue;
            }
            // add end 2017/06/14 CSA Defect#18841

            if (!isFleet(inPrmPMsg, svcUsgPrcPMsg.shellLineNum.getValue())) {
                if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.mdlId)) {
                    addMsgForSvcUsgPrcList(msgMap, NSZM1134E, svcUsgPrcPMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.mdlId)) {
                    addMsgForSvcUsgPrcList(msgMap, NSZM1187E, svcUsgPrcPMsg);
                }
            }

            if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.bllgMtrLbCd)) {
                addMsgForSvcUsgPrcList(msgMap, NSZM0899E, svcUsgPrcPMsg);
            }

            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.xsMtrAmtRate)) {
                if (svcUsgPrcPMsg.xsMtrAmtRate.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    addMsgForSvcUsgPrcList(msgMap, NSZM1212E, svcUsgPrcPMsg);
                }
            }

            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.regMtrLbCd)) {
                if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.contrMtrMultRate)) {
                    addMsgForSvcUsgPrcList(msgMap, NSZM1193E, svcUsgPrcPMsg);
                }
            }
        }
    }

    private void checkSnglSvcAddlBasePrcList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcAddlBasePrcList.getValidCount(); i++) {
            svcAddlBasePrcPMsg = inPrmPMsg.svcAddlBasePrcList.no(i);

            if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.xxRqstTpCd)) {
                addMsgForAddlBasePrcList(msgMap, NSZM1118E, svcAddlBasePrcPMsg);
            } else if (!RQST_TP_NEW.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue()) && !RQST_TP_MOD.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue()) && !RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                addMsgForAddlBasePrcList(msgMap, NSZM1119E, svcAddlBasePrcPMsg);
            }

            if (RQST_TP_MOD.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                // START 2018/04/10 M.Naito [QC#23378,DEL]
//                if (ADDL_CHRG_CATG.RENTAL.equals(svcAddlBasePrcPMsg.addlChrgCatgCd.getValue())) {
//                    if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.dsContrAddlChrgPk)) {
//                        addMsgForAddlBasePrcList(msgMap, NSZM1140E, svcAddlBasePrcPMsg);
//                    }
//                }
                // END 2018/04/10 M.Naito [QC#23378,DEL]

                if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.dsContrPk)) {
                    addMsgForAddlBasePrcList(msgMap, NSZM0856E, svcAddlBasePrcPMsg);
                }

                if (ADDL_CHRG_CATG.ACCESSORY.equals(svcAddlBasePrcPMsg.addlChrgCatgCd.getValue())) {
                    if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.dsContrDtlPk)) {
                        addMsgForAddlBasePrcList(msgMap, NSZM0857E, svcAddlBasePrcPMsg);
                    }
                }

            } else if (RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                // START 2018/04/10 M.Naito [QC#23378,DEL]
//                if (ADDL_CHRG_CATG.RENTAL.equals(svcAddlBasePrcPMsg.addlChrgCatgCd.getValue())) {
//                    if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.dsContrAddlChrgPk)) {
//                        addMsgForAddlBasePrcList(msgMap, NSZM1140E, svcAddlBasePrcPMsg);
//                    }
//                }
                // END 2018/04/10 M.Naito [QC#23378,DEL]

                if (ADDL_CHRG_CATG.ACCESSORY.equals(svcAddlBasePrcPMsg.addlChrgCatgCd.getValue())) {
                    if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.dsContrDtlPk)) {
                        addMsgForAddlBasePrcList(msgMap, NSZM0857E, svcAddlBasePrcPMsg);
                    }
                }
                continue;
            }

            if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.addlChrgCatgCd)) {
                addMsgForAddlBasePrcList(msgMap, NSZM1254E, svcAddlBasePrcPMsg);
            }

//            if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.addlChrgTpCd)) {
//                addMsgForAddlBasePrcList(msgMap, NSZM0402E, svcAddlBasePrcPMsg);
//            }

            if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.shellLineNum)) {
                addMsgForAddlBasePrcList(msgMap, NSZM1121E, svcAddlBasePrcPMsg);
            }

            if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.cpoOrdNum)) {
                addMsgForAddlBasePrcList(msgMap, NSZM0402E, svcAddlBasePrcPMsg);
            }

            if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.cpoDtlLineNum)) {
                addMsgForAddlBasePrcList(msgMap, NSZM0403E, svcAddlBasePrcPMsg);
            }

            if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.cpoDtlLineSubNum)) {
                addMsgForAddlBasePrcList(msgMap, NSZM0404E, svcAddlBasePrcPMsg);
            }

            if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.addlBasePrcCatgCd)) {
                // START 2017/06/14 [QC#18934, MOD]
                // addMsgForAddlBasePrcList(msgMap, NSZM1141E, svcAddlBasePrcPMsg);
                if (!isFleet(inPrmPMsg, svcAddlBasePrcPMsg.shellLineNum.getValue())) {
                    addMsgForAddlBasePrcList(msgMap, NSZM1141E, svcAddlBasePrcPMsg);
                }
                // END 2017/06/14 [QC#18934, MOD]
            }

            if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.addlBasePrcDealAmt)) {
                // START 2017/06/14 [QC#18934, MOD]
                // addMsgForAddlBasePrcList(msgMap, NSZM1142E, svcAddlBasePrcPMsg);
                if (!isFleet(inPrmPMsg, svcAddlBasePrcPMsg.shellLineNum.getValue())) {
                    addMsgForAddlBasePrcList(msgMap, NSZM1142E, svcAddlBasePrcPMsg);
                }
                // END 2017/06/14 [QC#18934, MOD]
            } else {
                if (svcAddlBasePrcPMsg.addlBasePrcDealAmt.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    if (SVC_PRC_CATG.ACC_UNIT_BASE_CHARGE.equals(svcAddlBasePrcPMsg.svcPrcCatgCd.getValue())) {
                        addMsgForAddlBasePrcList(msgMap, NSZM1213E, svcAddlBasePrcPMsg);
                    } else {
                        addMsgForAddlBasePrcList(msgMap, NSZM1214E, svcAddlBasePrcPMsg);
                    }
                }
            }
        }
    }

    private void checkSnglSvcAddlChrgPrcList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        NSZC115001_svcAddlChrgPrcListPMsg svcAddlChrgPrcPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcAddlChrgPrcList.getValidCount(); i++) {
            svcAddlChrgPrcPMsg = inPrmPMsg.svcAddlChrgPrcList.no(i);

            if (!ZYPCommonFunc.hasValue(svcAddlChrgPrcPMsg.xxRqstTpCd)) {
                addMsgForSvcAddlChrgPrcList(msgMap, NSZM1118E, svcAddlChrgPrcPMsg);
            } else if (!RQST_TP_NEW.equals(svcAddlChrgPrcPMsg.xxRqstTpCd.getValue()) && !RQST_TP_MOD.equals(svcAddlChrgPrcPMsg.xxRqstTpCd.getValue()) && !RQST_TP_DEL.equals(svcAddlChrgPrcPMsg.xxRqstTpCd.getValue())) {
                addMsgForSvcAddlChrgPrcList(msgMap, NSZM1119E, svcAddlChrgPrcPMsg);
            }

            if (RQST_TP_MOD.equals(svcAddlChrgPrcPMsg.xxRqstTpCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(svcAddlChrgPrcPMsg.dsContrAddlChrgPk)) {
                    addMsgForSvcAddlChrgPrcList(msgMap, NSZM1140E, svcAddlChrgPrcPMsg);
                }

            } else if (RQST_TP_DEL.equals(svcAddlChrgPrcPMsg.xxRqstTpCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(svcAddlChrgPrcPMsg.dsContrAddlChrgPk)) {
                    addMsgForSvcAddlChrgPrcList(msgMap, NSZM1140E, svcAddlChrgPrcPMsg);
                }
                continue;
            }

            if (!ZYPCommonFunc.hasValue(svcAddlChrgPrcPMsg.shellLineNum)) {
                addMsgForSvcAddlChrgPrcList(msgMap, NSZM1121E, svcAddlChrgPrcPMsg);
            }

            if (!ZYPCommonFunc.hasValue(svcAddlChrgPrcPMsg.cpoOrdNum)) {
                addMsgForSvcAddlChrgPrcList(msgMap, NSZM0402E, svcAddlChrgPrcPMsg);
            }

            if (!ZYPCommonFunc.hasValue(svcAddlChrgPrcPMsg.cpoDtlLineNum)) {
                addMsgForSvcAddlChrgPrcList(msgMap, NSZM0403E, svcAddlChrgPrcPMsg);
            }

            if (!ZYPCommonFunc.hasValue(svcAddlChrgPrcPMsg.cpoDtlLineSubNum)) {
                addMsgForSvcAddlChrgPrcList(msgMap, NSZM0404E, svcAddlChrgPrcPMsg);
            }

            if (!ZYPCommonFunc.hasValue(svcAddlChrgPrcPMsg.addlChrgPrcCatgCd)) {
                addMsgForSvcAddlChrgPrcList(msgMap, NSZM1143E, svcAddlChrgPrcPMsg);
            }

            if (!ZYPCommonFunc.hasValue(svcAddlChrgPrcPMsg.addlChrgMdseCd)) {
                addMsgForSvcAddlChrgPrcList(msgMap, NSZM1144E, svcAddlChrgPrcPMsg);
            }

            if (!ZYPCommonFunc.hasValue(svcAddlChrgPrcPMsg.addlChrgPrcDealAmt)) {
                addMsgForSvcAddlChrgPrcList(msgMap, NSZM1145E, svcAddlChrgPrcPMsg);
            } else {
                if (svcAddlChrgPrcPMsg.addlChrgPrcDealAmt.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    addMsgForSvcAddlChrgPrcList(msgMap, NSZM1215E, svcAddlChrgPrcPMsg);
                }
            }
        }
    }

    private void checkDB(S21ApiMessageMap msgMap) {
        checkDBSvcDtlList(msgMap);
        checkDBSvcConfigRefList(msgMap);
        checkDBSvcPrcList(msgMap);
        checkDBSvcUsgPrcList(msgMap);
        checkDBSvcAddlBasePrcList(msgMap);
        checkDBSvcAddlChrgPrcList(msgMap);
    }

    private void checkDBSvcDtlList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        MDSETMsg mdseTMsg = new MDSETMsg();
        // Del Start 2019/01/15 QC#29860
//        ORD_TAKE_MDSETMsg otmTMsg = new ORD_TAKE_MDSETMsg();
        // Del End 2019/01/15 QC#29860
        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
        PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
        Map<String, String> prcCatgMap = new HashMap<String, String>();

        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);

            if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            // 2018/05/07 QC#22482 Add Start
            if (isManOvrd(svcDtlPMsg)) {
                continue;
            }
            // 2018/05/07 QC#22482 Add End

            if (ZYPCommonFunc.hasValue(svcDtlPMsg.svcPgmMdseCd)) {
                String val = svcDtlPMsg.svcPgmMdseCd.getValue();
                mdseTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                mdseTMsg.mdseCd.setValue(val);
                if (!isExistTblByPK(mdseTMsg)) {
                    // Mod Start 2019/01/15 QC#29860
//                    otmTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
//                    otmTMsg.ordTakeMdseCd.setValue(val);
//                    if (!isExistTblByPK(otmTMsg)) {
//                        addMsgForSvcDtlList(msgMap, NSZM1146E, svcDtlPMsg);
//                    }
                    addMsgForSvcDtlList(msgMap, NSZM1146E, svcDtlPMsg);
                    // Mod End 2019/01/15 QC#29860
                }
                mdseTMsg.clear();
                // Del Start 2019/01/15 QC#29860
//                otmTMsg.clear();
                // Del End 2019/01/15 QC#29860
            }

            if (ZYPCommonFunc.hasValue(svcDtlPMsg.prcSvcContrTpCd)) {
                String tblNm = "PRC_SVC_CONTR_TP";
                String cdVal = svcDtlPMsg.prcSvcContrTpCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcDtlList(msgMap, NSZM1147E, svcDtlPMsg);
                }
            }

            if (ZYPCommonFunc.hasValue(svcDtlPMsg.prcSvcPlnTpCd)) {
                String tblNm = "PRC_SVC_PLN_TP";
                String cdVal = svcDtlPMsg.prcSvcPlnTpCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcDtlList(msgMap, NSZM1148E, svcDtlPMsg);
                }
            }

            if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrCatgCd)) {
                String tblNm = "DS_CONTR_CATG";
                String cdVal = svcDtlPMsg.dsContrCatgCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcDtlList(msgMap, NSZM1149E, svcDtlPMsg);
                }
            }

            if (ZYPCommonFunc.hasValue(svcDtlPMsg.baseBllgCycleCd)) {
                String tblNm = "BLLG_CYCLE";
                String cdVal = svcDtlPMsg.baseBllgCycleCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcDtlList(msgMap, NSZM1150E, svcDtlPMsg);
                }
            }

            if (ZYPCommonFunc.hasValue(svcDtlPMsg.usgBllgCycleCd)) {
                String tblNm = "BLLG_CYCLE";
                String cdVal = svcDtlPMsg.usgBllgCycleCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcDtlList(msgMap, NSZM1151E, svcDtlPMsg);
                }
            }

            if (ZYPCommonFunc.hasValue(svcDtlPMsg.billByTpCd)) {
                String tblNm = "BILL_BY_TP";
                String cdVal = svcDtlPMsg.billByTpCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcDtlList(msgMap, NSZM1152E, svcDtlPMsg);
                }
            }

            if (ZYPCommonFunc.hasValue(svcDtlPMsg.soldToCustLocCd)) {
                String val = svcDtlPMsg.soldToCustLocCd.getValue();
                billToCustTMsg.setSQLID("990");
                billToCustTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                billToCustTMsg.setConditionValue("billToCustCd01", val);
                if (!isExistTblByCond(billToCustTMsg, val)) {
                    addMsgForSvcDtlList(msgMap, NSZM1153E, svcDtlPMsg);
                }
                billToCustTMsg.clear();
            }

            if (ZYPCommonFunc.hasValue(svcDtlPMsg.sellToCustCd)) {
                String val = svcDtlPMsg.sellToCustCd.getValue();
                sellToCustTMsg.setSQLID("990");
                sellToCustTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                sellToCustTMsg.setConditionValue("sellToCustCd01", val);
                if (!isExistTblByCond(sellToCustTMsg, val)) {
                    addMsgForSvcDtlList(msgMap, NSZM1154E, svcDtlPMsg);
                }
                sellToCustTMsg.clear();
            }

            if (ZYPCommonFunc.hasValue(svcDtlPMsg.manContrOvrdRsnCd)) {
                String tblNm = "SVC_MEMO_RSN";
                String cdVal = svcDtlPMsg.manContrOvrdRsnCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcDtlList(msgMap, NSZM1155E, svcDtlPMsg);
                }
            }

            if (ZYPCommonFunc.hasValue(svcDtlPMsg.crRebilCd)) {
                String tblNm = "CR_REBIL";
                String cdVal = svcDtlPMsg.crRebilCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcDtlList(msgMap, NSZM1156E, svcDtlPMsg);
                }
            }

            if (ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.addAsryFlg.getValue()) && ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk_AD)) {
                if (!isExistContr(inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg)) {
                    addMsgForSvcDtlList(msgMap, NSZM1220E, svcDtlPMsg);
                }
            }

            // START 2017/08/25 U.Kim [QC#18345, ADD]
            if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk_AD)) {
                DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, svcDtlPMsg.dsContrPk_AD);
                tMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(tMsg);
                if (tMsg != null && (DS_CONTR_CATG.FLEET.equals(tMsg.dsContrCatgCd.getValue()) || DS_CONTR_CATG.AGGREGATE.equals(tMsg.dsContrCatgCd.getValue()))) {
                    if (isInvoicedForAddContract(svcDtlPMsg)) {
                        String dsContrDtlTp = null;
                        if (DS_CONTR_CATG.FLEET.equals(tMsg.dsContrCatgCd.getValue())) {
                            dsContrDtlTp = DS_CONTR_DTL_TP.FLEET;
                        } else {
                            dsContrDtlTp = DS_CONTR_DTL_TP.AGGREGATE;
                        }
                        if (!checkBllgMtrForAddContract(svcDtlPMsg, inPrmPMsg, dsContrDtlTp)) {
                            // START 2017/08/31 U.Kim [QC#18345-2, MOD]
                            //addMsgForSvcAddContr(msgMap, NSZM1294E, svcDtlPMsg);
                            addMsgForSvcDtlList(msgMap, NSZM1294E, svcDtlPMsg);
                            // END 2017/08/31 U.Kim [QC#18345-2, MOD]
                        }
                    }
                    // add start 2018/08/22 QC#23920
                    if (DS_CONTR_CATG.AGGREGATE.equals(tMsg.dsContrCatgCd.getValue())) {
                        if (!isInvoicedForAddContract(svcDtlPMsg)) {
                            if (!checkBllgMtrForAddContract(svcDtlPMsg, inPrmPMsg, DS_CONTR_DTL_TP.AGGREGATE)) {
                                addMsgForSvcDtlList(msgMap, NSZM1345E, svcDtlPMsg);
                                return;
                            }
                        }
                        if (!checkTierCntForAgg(svcDtlPMsg, inPrmPMsg)) {
                            addMsgForSvcDtlList(msgMap, NSZM1174E, svcDtlPMsg);
                        }
                    }
                    // add end 2018/08/22 QC#23920
                }
            }
            // START 2017/08/25 U.Kim [QC#18345, ADD]
            
            // 2018/08/23 S21_NA#25105 Mod Start
            if (ZYPCommonFunc.hasValue(svcDtlPMsg.addlBasePrcCatgCd)) {
                String key = svcDtlPMsg.addlBasePrcCatgCd.getValue();
                if (!prcCatgMap.containsKey(key)) {
                    prcCatgTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                    prcCatgTMsg.prcCatgCd.setValue(key);
                    if (!isExistPrcCatg(prcCatgTMsg, inPrmPMsg.slsDt.getValue())) {
                        addMsgForSvcDtlList(msgMap, NSZM1203E, svcDtlPMsg);
                        prcCatgMap.put(key, NSZM1203E);
                    } else {
                        prcCatgMap.put(key, "");
                    }
                    prcCatgTMsg.clear();
                }
            }
            
            if (ZYPCommonFunc.hasValue(svcDtlPMsg.rntlPrcCatgCd)) {
                String key = svcDtlPMsg.rntlPrcCatgCd.getValue();
                if (!prcCatgMap.containsKey(key)) {
                    prcCatgTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                    prcCatgTMsg.prcCatgCd.setValue(key);
                    if (!isExistPrcCatg(prcCatgTMsg, inPrmPMsg.slsDt.getValue())) {
                        addMsgForSvcDtlList(msgMap, NSZM1204E, svcDtlPMsg);
                        prcCatgMap.put(key, NSZM1204E);
                    } else {
                        prcCatgMap.put(key, "");
                    }
                    prcCatgTMsg.clear();
                }
            }
            
            if (ZYPCommonFunc.hasValue(svcDtlPMsg.addlChrgPrcCatgCd)) {
                String key = svcDtlPMsg.addlChrgPrcCatgCd.getValue();
                if (!prcCatgMap.containsKey(key)) {
                    prcCatgTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                    prcCatgTMsg.prcCatgCd.setValue(key);
                    if (!isExistPrcCatg(prcCatgTMsg, inPrmPMsg.slsDt.getValue())) {
                        addMsgForSvcDtlList(msgMap, NSZM1205E, svcDtlPMsg);
                        prcCatgMap.put(key, NSZM1205E);
                    } else {
                        prcCatgMap.put(key, "");
                    }
                    prcCatgTMsg.clear();
                }
            }
            // 2018/08/23 S21_NA#25105 Mod End

        }
    }

    // START 2017/08/25 U.Kim [QC#18345, ADD]
    private boolean isInvoicedForAddContract(NSZC115001_svcDtlListPMsg svcDtlPMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", svcDtlPMsg.dsContrPk_AD);

        BigDecimal cnt = (BigDecimal) this.ssmBatchClient.queryObject("countInvoicedBllgSchd", param);
        if (cnt == null || cnt.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }

    private boolean checkBllgMtrForAddContract(NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001PMsg inPrmPMsg, String dsContrDtlTpCd) {
        List<String> bllgMtrLbFromDB = getBllgMtrLbForAddContractFromDatabase(svcDtlPMsg, dsContrDtlTpCd);
        List<String> bllgMtrLbFromPM = getBllgMtrLbForAddContractFromPMsg(svcDtlPMsg, inPrmPMsg);
        HashMap<String, String> bllgMtrLbFromDBMap = new HashMap<String, String>();
        for (String chkBllgMtrLbFromDB : bllgMtrLbFromDB) {
            bllgMtrLbFromDBMap.put(chkBllgMtrLbFromDB, chkBllgMtrLbFromDB);
        }
        for (String chkBllgMtrForAdd : bllgMtrLbFromPM) {
            if (!bllgMtrLbFromDBMap.containsKey(chkBllgMtrForAdd)) {
                return false;
            }
        }
        return true;
    }

    private List<String> getBllgMtrLbForAddContractFromDatabase(NSZC115001_svcDtlListPMsg svcDtlPMsg, String dsContrDtlTpCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", svcDtlPMsg.dsContrPk_AD);
        param.put("dsContrDtlTpCd", dsContrDtlTpCd);
        List<String> bllgMtrForFlt = this.ssmBatchClient.queryObjectList("getBllgMtrLbCdForAdd", param);
        return bllgMtrForFlt;
    }

    private List<String> getBllgMtrLbForAddContractFromPMsg(NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001PMsg inPrmPMsg) {
        NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg = null;
        List<String> bllgMtrForAdd = new ArrayList<String>();
        for (int i = 0; i < inPrmPMsg.svcUsgPrcList.getValidCount(); i++) {
            svcUsgPrcPMsg = inPrmPMsg.svcUsgPrcList.no(i);
            // 2019/01/08 S21_NA#29788 Mod Start 
            // if (svcDtlPMsg.dsContrPk.getValue().compareTo(svcUsgPrcPMsg.dsContrPk.getValue()) == 0 && ZYPCommonFunc.hasValue(svcUsgPrcPMsg.bllgMtrLbCd)) {
            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.dsContrPk) && svcDtlPMsg.dsContrPk.getValue().compareTo(svcUsgPrcPMsg.dsContrPk.getValue()) == 0 //
                    // 2019/01/08 S21_NA#29788 Mod End
                    && ZYPCommonFunc.hasValue(svcUsgPrcPMsg.bllgMtrLbCd)) {
                bllgMtrForAdd.add(svcUsgPrcPMsg.bllgMtrLbCd.getValue());
            }
        }
        return bllgMtrForAdd;
    }

    // END 2017/08/25 U.Kim [QC#18345, ADD]

    private boolean isExistContr(String cpoOrdNum, NSZC115001_svcDtlListPMsg svcDtlPMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("dsContrPk", svcDtlPMsg.dsContrPk_AD);

        Integer cnt = (Integer) this.ssmBatchClient.queryObject("getCountContr", param);
        if (cnt == null) {
            return false;
        }

        return cnt > 0;
    }

    private void checkDBSvcConfigRefList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcConfigRefList.getValidCount(); i++) {
            svcConfigRefPMsg = inPrmPMsg.svcConfigRefList.no(i);

            if (RQST_TP_DEL.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(svcConfigRefPMsg.mtrReadMethCd)) {
                String tblNm = "MTR_READ_METH";
                String cdVal = svcConfigRefPMsg.mtrReadMethCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcConfigRefList(msgMap, NSZM1157E, svcConfigRefPMsg);
                }
            }

            if (ZYPCommonFunc.hasValue(svcConfigRefPMsg.crRebilCd)) {
                String tblNm = "CR_REBIL";
                String cdVal = svcConfigRefPMsg.crRebilCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcConfigRefList(msgMap, NSZM1156E, svcConfigRefPMsg);
                }
            }
        }
    }

    private boolean isExistsMainMachine(NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg, NSZC115001PMsg inPrmPMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", inPrmPMsg.refCpoOrdNum);
        param.put("cpoDtlLineNum", svcConfigRefPMsg.cpoDtlLineNum);
        param.put("cpoDtlLineSubNum", svcConfigRefPMsg.cpoDtlLineSubNum);

        Integer cnt = (Integer) this.ssmBatchClient.queryObject("cntMainMachine", param);
        if (cnt == null) {
            return false;
        }

        return cnt > 0;
    }

    private void checkDBSvcPrcList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        String slsDt = inPrmPMsg.slsDt.getValue();

        DS_MDLTMsg mdlTMsg = new DS_MDLTMsg();
        PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
        PRC_MTR_PKGTMsg prcMtrPkgTMsg = new PRC_MTR_PKGTMsg();
        Map<String, String> prcCatgMap = new HashMap<String, String>();
        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();

        NSZC115001_svcPrcListPMsg svcPrcPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcPrcList.getValidCount(); i++) {
            svcPrcPMsg = inPrmPMsg.svcPrcList.no(i);

            if (RQST_TP_DEL.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            boolean isFleet = isFleet(inPrmPMsg, svcPrcPMsg.shellLineNum.getValue());

            if (!isFleet) {
                if (ZYPCommonFunc.hasValue(svcPrcPMsg.mdlId)) {
                    BigDecimal val = svcPrcPMsg.mdlId.getValue();
                    mdlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                    mdlTMsg.mdlId.setValue(val);
                    if (!isExistTblByPK(mdlTMsg)) {
                        addMsgForSvcPrcList(msgMap, NSZM1158E, svcPrcPMsg);
                    }
                    mdlTMsg.clear();
                }
            }

            if (ZYPCommonFunc.hasValue(svcPrcPMsg.maintPrcCatgCd)) {
                String key = svcPrcPMsg.maintPrcCatgCd.getValue();
                if (!prcCatgMap.containsKey(key)) {
                    prcCatgTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                    prcCatgTMsg.prcCatgCd.setValue(key);
                    if (!isExistPrcCatg(prcCatgTMsg, slsDt)) {
                        addMsgForSvcPrcList(msgMap, NSZM1202E, svcPrcPMsg);
                        prcCatgMap.put(key, NSZM1202E);
                    } else {
                        prcCatgMap.put(key, "");
                    }
                    prcCatgTMsg.clear();
                }
            }

            if (ZYPCommonFunc.hasValue(svcPrcPMsg.prcMtrPkgPk)) {
                BigDecimal val = svcPrcPMsg.prcMtrPkgPk.getValue();
                prcMtrPkgTMsg.setSQLID("001");
                prcMtrPkgTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                prcMtrPkgTMsg.setConditionValue("prcMtrPkgPk01", val);
                prcMtrPkgTMsg.setConditionValue("effFromDt01", slsDt);
                prcMtrPkgTMsg.setConditionValue("effThruDt01", slsDt);
                if (!isExistTblByCond(prcMtrPkgTMsg, val)) {
                    addMsgForSvcPrcList(msgMap, NSZM1159E, svcPrcPMsg);
                }
                prcMtrPkgTMsg.clear();
            }

            if (ZYPCommonFunc.hasValue(svcPrcPMsg.prcTierSvcOfferCd)) {
                String tblNm = "PRC_TIER_SVC_OFFER";
                String cdVal = svcPrcPMsg.prcTierSvcOfferCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcPrcList(msgMap, NSZM1160E, svcPrcPMsg);
                }
            }

            if (ZYPCommonFunc.hasValue(svcPrcPMsg.billToCustCd)) {
                String val = svcPrcPMsg.billToCustCd.getValue();
                billToCustTMsg.setSQLID("990");
                billToCustTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                billToCustTMsg.setConditionValue("billToCustCd01", val);
                if (!isExistTblByCond(billToCustTMsg, val)) {
                    addMsgForSvcPrcList(msgMap, NSZM1153E, svcPrcPMsg);
                }
                billToCustTMsg.clear();
            }

            if (ZYPCommonFunc.hasValue(svcPrcPMsg.sellToCustCd)) {
                String val = svcPrcPMsg.sellToCustCd.getValue();
                sellToCustTMsg.setSQLID("990");
                sellToCustTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                sellToCustTMsg.setConditionValue("sellToCustCd01", val);
                if (!isExistTblByCond(sellToCustTMsg, val)) {
                    addMsgForSvcPrcList(msgMap, NSZM1154E, svcPrcPMsg);
                }
                sellToCustTMsg.clear();
            }
        }
    }

    private boolean isExistPrcCatg(PRC_CATGTMsg prcCatgTMsg, String slsDt) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", this.glblCmpyCd);
        mapParam.put("prcCatgCd", prcCatgTMsg.prcCatgCd);
        mapParam.put("slsDt", slsDt);

        Integer cnt = (Integer) this.ssmBatchClient.queryObject("getCountPrcCatg", mapParam);

        return cnt > 0;
    }

    private boolean checkPrcMtrPkg(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        for (int i = 0; i < inPrmPMsg.svcPrcList.getValidCount(); i++) {
            NSZC115001_svcPrcListPMsg svcPrcPMsg = inPrmPMsg.svcPrcList.no(i);
            if (RQST_TP_DEL.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                continue;
            }
            // mod start 2017/06/14 CSA Defect#18841
            if (!ZYPCommonFunc.hasValue(svcPrcPMsg.prcMtrPkgPk)) {
                continue;
            }
            // mod end 2017/06/14 CSA Defect#18841
            if (!isValidPrcMtrPkg(msgMap, svcPrcPMsg)) {
                addMsgForSvcPrcList(msgMap, NSZM1189E, svcPrcPMsg);
                return false;
            }
        }
        return true;
    }

    private boolean isValidPrcMtrPkg(S21ApiMessageMap msgMap, NSZC115001_svcPrcListPMsg svcPrcPMsg) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        String slsDt = inPrmPMsg.slsDt.getValue();

        List<BigDecimal> mdlIdList = new ArrayList<BigDecimal>();
        if (ZYPCommonFunc.hasValue(svcPrcPMsg.mdlId)) {
            mdlIdList.add(svcPrcPMsg.mdlId.getValue());
        } else {
            // fleet
            mdlIdList = getMdlIdList(inPrmPMsg, svcPrcPMsg);
        }
        if (mdlIdList == null || mdlIdList.size() == 0) {
            return false;
        }

        String prcCatgCd = svcPrcPMsg.maintPrcCatgCd.getValue();
        String prcListTp = getPrcListTp(prcCatgCd);

        DS_CONTRTMsg csdTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(csdTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(csdTMsg.dsContrPk, svcPrcPMsg.dsContrPk);
        csdTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(csdTMsg);
        if (csdTMsg == null) {
            return false;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("prcListTp", prcListTp);
        param.put("prcCatgCd", prcCatgCd);
        param.put("mdlIdList", mdlIdList);
        param.put("prcSvcPlnTp", csdTMsg.prcSvcPlnTpCd);
        param.put("prcSvcContrTp", csdTMsg.prcSvcContrTpCd);
        param.put("prcMtrPkgPk", svcPrcPMsg.prcMtrPkgPk);
        param.put("slsDt", slsDt);

        Integer cnt = (Integer) this.ssmBatchClient.queryObject("getCountPrcMtrPkg", param);

        return cnt > 0;
    }

    private List<BigDecimal> getMdlIdList(NSZC115001PMsg inPrmPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", inPrmPMsg.refCpoOrdNum);
        param.put("configCatgOutbound", CONFIG_CATG.OUTBOUND);
        param.put("dsContrPk", svcPrcPMsg.dsContrPk);

        List<BigDecimal> mdlIdList = this.ssmBatchClient.queryObjectList("getMdlIdList", param);

        return mdlIdList;
    }

    private String getPrcListTp(String prcCatgCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("prcCatgCd", prcCatgCd);

        String prcListTpCd = (String) this.ssmBatchClient.queryObject("getPrcListTp", param);

        return prcListTpCd;
    }

    private void checkDBSvcUsgPrcList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        MDSETMsg mdseTMsg = new MDSETMsg();
        Map<String, String> prcBookMdseMap = new HashMap<String, String>();
        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();

        NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcUsgPrcList.getValidCount(); i++) {
            svcUsgPrcPMsg = inPrmPMsg.svcUsgPrcList.no(i);

            if (RQST_TP_DEL.equals(svcUsgPrcPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.prcListBandCd)) {
                if (!isExistPrcListBand(inPrmPMsg, svcUsgPrcPMsg)) {
                    addMsgForSvcUsgPrcList(msgMap, NSZM1161E, svcUsgPrcPMsg);
                }
            }

            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.prcBookMdseCd)) {
                String key = svcUsgPrcPMsg.prcBookMdseCd.getValue();
                if (!prcBookMdseMap.containsKey(key)) {
                    mdseTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                    mdseTMsg.mdseCd.setValue(key);
                    if (!isExistPriceBookItem(mdseTMsg)) {
                        addMsgForSvcUsgPrcList(msgMap, NSZM1201E, svcUsgPrcPMsg);
                        prcBookMdseMap.put(key, NSZM1201E);
                    } else {
                        prcBookMdseMap.put(key, "");
                    }
                    mdseTMsg.clear();
                }
            }

            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.bllgMtrLbCd)) {
                String tblNm = "MTR_LB";
                String cdVal = svcUsgPrcPMsg.bllgMtrLbCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcUsgPrcList(msgMap, NSZM1162E, svcUsgPrcPMsg);
                }
            }

            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.usgMdseCd)) {
                String val = svcUsgPrcPMsg.usgMdseCd.getValue();
                mdseTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                mdseTMsg.mdseCd.setValue(val);
                if (!isExistTblByPK(mdseTMsg)) {
                    addMsgForSvcUsgPrcList(msgMap, NSZM1163E, svcUsgPrcPMsg);
                }
                mdseTMsg.clear();
            }

            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.regMtrLbCd)) {
                String tblNm = "MTR_LB";
                String cdVal = svcUsgPrcPMsg.regMtrLbCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcUsgPrcList(msgMap, NSZM1164E, svcUsgPrcPMsg);
                }
            }

            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.prcSvcTierTpCd)) {
                String tblNm = "PRC_SVC_TIER_TP";
                String cdVal = svcUsgPrcPMsg.prcSvcTierTpCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcUsgPrcList(msgMap, NSZM1165E, svcUsgPrcPMsg);
                }
            }

            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.billToCustCd)) {
                String val = svcUsgPrcPMsg.billToCustCd.getValue();
                billToCustTMsg.setSQLID("990");
                billToCustTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                billToCustTMsg.setConditionValue("billToCustCd01", val);
                if (!isExistTblByCond(billToCustTMsg, val)) {
                    addMsgForSvcUsgPrcList(msgMap, NSZM1153E, svcUsgPrcPMsg);
                }
                billToCustTMsg.clear();
            }

            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.sellToCustCd)) {
                String val = svcUsgPrcPMsg.sellToCustCd.getValue();
                sellToCustTMsg.setSQLID("990");
                sellToCustTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                sellToCustTMsg.setConditionValue("sellToCustCd01", val);
                if (!isExistTblByCond(sellToCustTMsg, val)) {
                    addMsgForSvcUsgPrcList(msgMap, NSZM1154E, svcUsgPrcPMsg);
                }
                sellToCustTMsg.clear();
            }
        }
    }

    private boolean isExistPriceBookItem(MDSETMsg mdseTMsg) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", this.glblCmpyCd);
        mapParam.put("itemTpCtxTpCd", "PRC_BOOK_MDSE_QLFY_ITEM_TP");
        mapParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        mapParam.put("mdseCd", mdseTMsg.mdseCd);

        Integer cnt = (Integer) this.ssmBatchClient.queryObject("getCountPriceBookItem", mapParam);

        return cnt > 0;
    }

    private boolean isExistPrcListBand(NSZC115001PMsg inPrmPMsg, NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg) {
        String tblNm = "PRC_LIST_BAND";
        String cdVal = svcUsgPrcPMsg.prcListBandCd.getValue();
        if (!isExistCdTbl(tblNm, cdVal)) {
            return false;
        }

        NSZC115001_svcPrcListPMsg svcPrcPMsg = getPrcPMsgByMdlId(inPrmPMsg, svcUsgPrcPMsg);
        String prcListStruTp = getPrcListStruTpFromPrcCatg(svcPrcPMsg.maintPrcCatgCd.getValue(), inPrmPMsg.slsDt.getValue());

        if (PRC_LIST_STRU_TP.SERVICE.equals(prcListStruTp)) {
            return true;

        } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTp)) {
            return true;

        } else if (PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(prcListStruTp)) {
            return isExistPrcListBandForSupplyProgram(inPrmPMsg, svcPrcPMsg, svcUsgPrcPMsg);
        }
        return false;
    }

    private boolean isExistPrcListBandForSupplyProgram(NSZC115001PMsg inPrmPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg) {

        NSZC115001_svcDtlListPMsg svcDtlPMsg = getSvcDtlPMsg(inPrmPMsg, svcUsgPrcPMsg);
        if (svcDtlPMsg == null) {
            return false;
        }

        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", this.glblCmpyCd);
        mapParam.put("slsDt", inPrmPMsg.slsDt);

        mapParam.put("prcCatgCd", svcPrcPMsg.maintPrcCatgCd);
        mapParam.put("mdlId", svcPrcPMsg.mdlId);
        mapParam.put("prcMtrPkgPk", svcPrcPMsg.prcMtrPkgPk);
        mapParam.put("prcSvcPlnTpCd", svcDtlPMsg.prcSvcPlnTpCd);
        mapParam.put("prcSvcContrTpCd", svcDtlPMsg.prcSvcContrTpCd);
        mapParam.put("bllgMtrLbCd", svcUsgPrcPMsg.bllgMtrLbCd);
        mapParam.put("termMth", svcDtlPMsg.toPerMthNum.getValue().subtract(svcDtlPMsg.fromPerMthNum.getValue()).add(BigDecimal.ONE));

        mapParam.put("prcListBandCd", svcUsgPrcPMsg.prcListBandCd);

        Integer cnt = (Integer) this.ssmBatchClient.queryObject("getCountPrcListBandForSupplyProgram", mapParam);

        return cnt > 0;
    }

    private NSZC115001_svcDtlListPMsg getSvcDtlPMsg(NSZC115001PMsg inPrmPMsg, NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg) {
        BigDecimal shellLineNum = svcUsgPrcPMsg.shellLineNum.getValue();
        BigDecimal mdlId = svcUsgPrcPMsg.mdlId.getValue();
        if (!ZYPCommonFunc.hasValue(shellLineNum) || !ZYPCommonFunc.hasValue(mdlId)) {
            return null;
        }
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            NSZC115001_svcDtlListPMsg svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.shellLineNum)) {
                continue;
            }
            if (shellLineNum.compareTo(svcDtlPMsg.shellLineNum.getValue()) != 0) {
                continue;
            }
            return inPrmPMsg.svcDtlList.no(i);
        }
        return null;
    }

    private NSZC115001_svcPrcListPMsg getPrcPMsgByMdlId(NSZC115001PMsg inPrmPMsg, NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg) {
        if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.mdlId)) {
            return inPrmPMsg.svcPrcList.no(0);
        }
        BigDecimal usgMdlId = svcUsgPrcPMsg.mdlId.getValue();
        String dsOrdPosnNum = svcUsgPrcPMsg.dsOrdPosnNum.getValue();

        for (int i = 0; i < inPrmPMsg.svcPrcList.getValidCount(); i++) {
            NSZC115001_svcPrcListPMsg svcPrcPMsg = inPrmPMsg.svcPrcList.no(i);

            if (!ZYPCommonFunc.hasValue(svcPrcPMsg.mdlId)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
                if (svcPrcPMsg.mdlId.getValue().compareTo(usgMdlId) == 0 && dsOrdPosnNum.equals(svcPrcPMsg.dsOrdPosnNum.getValue())) {
                    return svcPrcPMsg;
                }
            } else {
                if (svcPrcPMsg.mdlId.getValue().compareTo(usgMdlId) == 0 && !ZYPCommonFunc.hasValue(svcPrcPMsg.dsOrdPosnNum)) {
                    return svcPrcPMsg;
                }
            }
        }
        return new NSZC115001_svcPrcListPMsg();
    }

    private void checkDBSvcAddlBasePrcList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        String slsDt = inPrmPMsg.slsDt.getValue();
        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
        Map<String, String> prcCatgMap = new HashMap<String, String>();

        NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcAddlBasePrcList.getValidCount(); i++) {
            svcAddlBasePrcPMsg = inPrmPMsg.svcAddlBasePrcList.no(i);

            if (RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.crRebilCd)) {
                String tblNm = "CR_REBIL";
                String cdVal = svcAddlBasePrcPMsg.crRebilCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForAddlBasePrcList(msgMap, NSZM1156E, svcAddlBasePrcPMsg);
                }
            }

            String cpoOrdNum = svcAddlBasePrcPMsg.cpoOrdNum.getValue();
            String cpoDtlLineNum = svcAddlBasePrcPMsg.cpoDtlLineNum.getValue();
            String cpoDtlLineSubNum = svcAddlBasePrcPMsg.cpoDtlLineSubNum.getValue();

            cpoDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            cpoDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);
            cpoDtlTMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
            cpoDtlTMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);
            if (!isExistTblByPK(cpoDtlTMsg)) {
                StringBuilder val = new StringBuilder();
                val.append(cpoOrdNum).append(",");
                val.append(cpoDtlLineNum).append(",");
                val.append(cpoDtlLineSubNum);
                addMsgForAddlBasePrcList(msgMap, NSZM1166E, svcAddlBasePrcPMsg);
            }
            cpoDtlTMsg.clear();

            String[] mdseTpCtxTp = null;
            String prcCatgErrMsgId = null;
            if (SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE.equals(svcAddlBasePrcPMsg.svcPrcCatgCd.getValue())) {
                prcCatgErrMsgId = NSZM1204E;
            } else {
                mdseTpCtxTp = new String[] {MDSE_TP_CTX_TP.CPO_SERVICE_CONFIGURATION_ITEMS };
                prcCatgErrMsgId = NSZM1203E;
            }
            if (!isValidItem(cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum, mdseTpCtxTp)) {
                addMsgForAddlBasePrcList(msgMap, NSZM1219E, svcAddlBasePrcPMsg);
            }

            if (ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.addlBasePrcCatgCd)) {
                String key = svcAddlBasePrcPMsg.addlBasePrcCatgCd.getValue();
                if (!prcCatgMap.containsKey(key)) {
                    prcCatgTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                    prcCatgTMsg.prcCatgCd.setValue(key);
                    if (!isExistPrcCatg(prcCatgTMsg, slsDt)) {
                        addMsgForAddlBasePrcList(msgMap, prcCatgErrMsgId, svcAddlBasePrcPMsg);
                        prcCatgMap.put(key, prcCatgErrMsgId);
                    } else {
                        prcCatgMap.put(key, "");
                    }
                    prcCatgTMsg.clear();
                }
            }
        }
    }

    private boolean isValidItem(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum, String[] mdseTpCtxTp) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("cpoDtlLineNum", cpoDtlLineNum);
        params.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        params.put("mdseTpCtxTp", mdseTpCtxTp);

        Integer cnt = (Integer) this.ssmBatchClient.queryObject("getCountValidItem", params);

        return cnt > 0;
    }

    private void checkDBSvcAddlChrgPrcList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        String slsDt = inPrmPMsg.slsDt.getValue();
        PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
        MDSETMsg mdseTMsg = new MDSETMsg();
        ORD_TAKE_MDSETMsg otmTMsg = new ORD_TAKE_MDSETMsg();
        Map<String, String> prcCatgMap = new HashMap<String, String>();

        NSZC115001_svcAddlChrgPrcListPMsg svcAddlChrgPrcPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcAddlChrgPrcList.getValidCount(); i++) {
            svcAddlChrgPrcPMsg = inPrmPMsg.svcAddlChrgPrcList.no(i);

            if (RQST_TP_DEL.equals(svcAddlChrgPrcPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(svcAddlChrgPrcPMsg.addlChrgPrcCatgCd)) {
                String key = svcAddlChrgPrcPMsg.addlChrgPrcCatgCd.getValue();
                if (!prcCatgMap.containsKey(key)) {
                    prcCatgTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                    prcCatgTMsg.prcCatgCd.setValue(key);
                    if (!isExistPrcCatg(prcCatgTMsg, slsDt)) {
                        addMsgForSvcAddlChrgPrcList(msgMap, NSZM1205E, svcAddlChrgPrcPMsg);
                        prcCatgMap.put(key, NSZM1205E);
                    } else {
                        prcCatgMap.put(key, "");
                    }
                    prcCatgTMsg.clear();
                }
            }

            if (ZYPCommonFunc.hasValue(svcAddlChrgPrcPMsg.addlChrgMdseCd)) {
                String val = svcAddlChrgPrcPMsg.addlChrgMdseCd.getValue();
                mdseTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                mdseTMsg.mdseCd.setValue(val);
                MDSETMsg dmiTMsg = (MDSETMsg) S21FastTBLAccessor.findByKey(mdseTMsg);
                if (dmiTMsg == null) {
                    ORD_TAKE_MDSETMsg otmValTMsg = null;
                    if (val.length() <= 8) {
                        otmTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                        otmTMsg.ordTakeMdseCd.setValue(val);
                        otmValTMsg = (ORD_TAKE_MDSETMsg) S21FastTBLAccessor.findByKey(otmTMsg);
                    }
                    if (otmValTMsg == null) {
                        addMsgForSvcAddlChrgPrcList(msgMap, NSZM1167E, svcAddlChrgPrcPMsg);
                    } else {
                        dmiTMsg = new MDSETMsg();
                        dmiTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(dmiTMsg.mdseCd, otmValTMsg.mdseCd);
                        dmiTMsg = (MDSETMsg) S21FastTBLAccessor.findByKey(dmiTMsg);
                    }
                }
                if (dmiTMsg == null || !MDSE_ITEM_TP.MAINTENANCE_CHARGES.equals(dmiTMsg.mdseItemTpCd.getValue())) {
                    addMsgForSvcAddlChrgPrcList(msgMap, NSZM1167E, svcAddlChrgPrcPMsg);
                }
                mdseTMsg.clear();
                otmTMsg.clear();
            }

            if (ZYPCommonFunc.hasValue(svcAddlChrgPrcPMsg.crRebilCd)) {
                String tblNm = "CR_REBIL";
                String cdVal = svcAddlChrgPrcPMsg.crRebilCd.getValue();
                if (!isExistCdTbl(tblNm, cdVal)) {
                    addMsgForSvcAddlChrgPrcList(msgMap, NSZM1156E, svcAddlChrgPrcPMsg);
                }
            }
        }
    }

    private void checkComb(S21ApiMessageMap msgMap) {
        checkCombSvcConfigRefList(msgMap);
        checkCombSvcDtlList(msgMap);
        checkCombSvcPrcList(msgMap);
        checkCombSvcUsgPrcList(msgMap);
    }

    private void checkRegistAddlBasePrcList(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        if (!isRental(inPrmPMsg)) {
            return;
        }

        List<BigDecimal> shellLineNumList = new ArrayList<BigDecimal>(inPrmPMsg.svcAddlBasePrcList.getValidCount());
        for (int i = 0; i < inPrmPMsg.svcAddlBasePrcList.getValidCount(); i++) {
            BigDecimal shellLineNum = inPrmPMsg.svcAddlBasePrcList.no(i).shellLineNum.getValue();
            if (!ZYPCommonFunc.hasValue(shellLineNum)) {
                continue;
            }
            if (!shellLineNumList.contains(shellLineNum)) {
                shellLineNumList.add(shellLineNum);
            }
        }
        for (BigDecimal shellLineNum : shellLineNumList) {
            List<String> cAubcLineNumList = new ArrayList<String>(inPrmPMsg.svcAddlBasePrcList.getValidCount());
            List<String> cRebcLineNumList = new ArrayList<String>(inPrmPMsg.svcAddlBasePrcList.getValidCount());

            getLineNumListForRentalCheck(inPrmPMsg, cAubcLineNumList, cRebcLineNumList, shellLineNum);

            // Accessory Charge
            checkExistsAubc(msgMap, cAubcLineNumList, shellLineNum);
            // Rental Equipment Charge Base & Accessory
            checkExistsRebc(msgMap, cRebcLineNumList, shellLineNum);
        }

    }

    private void getLineNumListForRentalCheck(NSZC115001PMsg inPrmPMsg, List<String> aubcLineNumList, List<String> rebcLineNumList, BigDecimal shellLineNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", inPrmPMsg.refCpoOrdNum);

        param.put("svcPrcCatgRentalEq", SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE);
        param.put("mdseTpCtxCpoSvcAddlBaseItems", MDSE_TP_CTX_TP.CPO_SERVICE_ADDITIONAL_BASE_ITEMS);

        param.put("mdseTpCtxTpForRentalEquip", new String[] {MDSE_TP_CTX_TP.CPO_SERVICE_ADDITIONAL_BASE_ITEMS, MDSE_TP_CTX_TP.CPO_SERVICE_CONFIGURATION_ITEMS });
        param.put("ordCatgCtxTp", "RENTAL_ORDER");
        param.put("shellLineNum", shellLineNum);
        // 2019/05/08 QC#50174 Add Start
        param.put("ordLineStsCancel", ORD_LINE_STS.CANCELLED);
        param.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.ORDER);
        // 2019/05/08 QC#50174 Add End

        List<Map<String, String>> rsltList = this.ssmBatchClient.queryObjectList("getLineNumListForRentalCheck", param);
        if (rsltList == null) {
            return;
        }
        for (Map<String, String> rslt : rsltList) {
            boolean isAubc = ZYPConstant.FLG_ON_Y.equals(rslt.get("ACSRY_FLG"));
            boolean isRebc = ZYPConstant.FLG_ON_Y.equals(rslt.get("RENTAL_FLG"));
            String cLineNum = S21StringUtil.concatStrings(rslt.get("CPO_DTL_LINE_NUM"), ".", rslt.get("CPO_DTL_LINE_SUB_NUM"));
            if (isAubc) {
                aubcLineNumList.add(cLineNum);
            }
            if (isRebc) {
                rebcLineNumList.add(cLineNum);
            }
        }

        return;
    }

    private void checkExistsRebc(S21ApiMessageMap msgMap, List<String> rebcLineNumList, BigDecimal svcLineNum) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        List<String> pRebcLineNumList = new ArrayList<String>(inPrmPMsg.svcAddlBasePrcList.getValidCount());

        for (int i = 0; i < inPrmPMsg.svcAddlBasePrcList.getValidCount(); i++) {
            NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg = inPrmPMsg.svcAddlBasePrcList.no(i);
            if (RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                continue;
            }
            if (SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE.equals(svcAddlBasePrcPMsg.svcPrcCatgCd.getValue())) {
                pRebcLineNumList.add(S21StringUtil.concatStrings(svcAddlBasePrcPMsg.cpoDtlLineNum.getValue(), ".", svcAddlBasePrcPMsg.cpoDtlLineSubNum.getValue()));
            }
        }
        if (!pRebcLineNumList.containsAll(rebcLineNumList)) {
            setErrStatus(msgMap, NSZM1217E); //mod u.kim 2018/03/06 QC#24056
        }

    }

    private void checkExistsAubc(S21ApiMessageMap msgMap, List<String> aubcLineNumList, BigDecimal svcLineNum) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        List<String> pAubcLineNumList = new ArrayList<String>(inPrmPMsg.svcAddlBasePrcList.getValidCount());

        for (int i = 0; i < inPrmPMsg.svcAddlBasePrcList.getValidCount(); i++) {
            NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg = inPrmPMsg.svcAddlBasePrcList.no(i);
            if (RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                continue;
            }
            if (SVC_PRC_CATG.ACC_UNIT_BASE_CHARGE.equals(svcAddlBasePrcPMsg.svcPrcCatgCd.getValue())) {
                pAubcLineNumList.add(S21StringUtil.concatStrings(svcAddlBasePrcPMsg.cpoDtlLineNum.getValue(), ".", svcAddlBasePrcPMsg.cpoDtlLineSubNum.getValue()));
            }
        }
        if (!pAubcLineNumList.containsAll(aubcLineNumList)) {
            setErrStatus(msgMap, NSZM1216E); //mod u.kim 2018/03/06 QC#24056
        }

    }

    private boolean isRental(NSZC115001PMsg inPrmPMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("ordCatgCtxTpCd", "RENTAL_ORDER");
        param.put("cpoOrdNum", inPrmPMsg.refCpoOrdNum);

        Integer cnt = (Integer) this.ssmBatchClient.queryObject("cntRentalOrder", param);
        if (cnt == null) {
            return false;
        }

        return cnt > 0;
    }

    private void checkCombSvcUsgPrcList(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        BigDecimal fctNum = ZYPCodeDataUtil.getNumConstValue(NSZC115001Constant.NSAL0320_MTR_MULT_RATE_FCT_NUM, this.glblCmpyCd);

        NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg = null;
        Map<String, Integer> tierCntMap = new HashMap<String, Integer>();
        Map<String, TreeMap<BigDecimal, BigDecimal>> tierMap = new HashMap<String, TreeMap<BigDecimal, BigDecimal>>();
        Map<BigDecimal, NSZC115001_svcDtlListPMsg> cpoSvcDtlMap = new HashMap<BigDecimal, NSZC115001_svcDtlListPMsg>();
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            NSZC115001_svcDtlListPMsg csdPMsg = inPrmPMsg.svcDtlList.no(i);
            if (!ZYPCommonFunc.hasValue(csdPMsg.shellLineNum)) {
                continue;
            }
            cpoSvcDtlMap.put(csdPMsg.shellLineNum.getValue(), csdPMsg);
        }
        boolean hasError = false;
        for (int i = 0; i < inPrmPMsg.svcUsgPrcList.getValidCount(); i++) {
            svcUsgPrcPMsg = inPrmPMsg.svcUsgPrcList.no(i);
            if (RQST_TP_DEL.equals(svcUsgPrcPMsg.xxRqstTpCd.getValue())) {
                continue;
            }
            // add start 2017/06/14 CSA Defect#18841
            if (!isFleet(inPrmPMsg, svcUsgPrcPMsg.shellLineNum.getValue()) && !ZYPCommonFunc.hasValue(svcUsgPrcPMsg.dsOrdPosnNum)) {
                continue;
            }
            // add end 2017/06/14 CSA Defect#18841
            NSZC115001_svcDtlListPMsg csdPMsg = cpoSvcDtlMap.get(svcUsgPrcPMsg.shellLineNum.getValue());
            if (!ZYPCommonFunc.hasValue(csdPMsg.usgBllgCycleCd)) {
                continue;
            }
            boolean isTier = isTier(inPrmPMsg, svcUsgPrcPMsg, cpoSvcDtlMap);
            if (isTier) {
                if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.prcSvcTierTpCd)) {
                    String dsOrdPosnNum = svcUsgPrcPMsg.dsOrdPosnNum.getValue();
                    if (dsOrdPosnNum == null || DS_CONTR_CATG.FLEET.equals(csdPMsg.dsContrCatgCd)) {
                        dsOrdPosnNum = "";
                    }
                    String mdlStr = "";
                    if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.mdlId)) {
                        mdlStr = svcUsgPrcPMsg.mdlId.getValue().toPlainString();
                    }
                    String tierKey = S21StringUtil.concatStrings(svcUsgPrcPMsg.shellLineNum.getValue(), ",", mdlStr, ",", dsOrdPosnNum, ",", svcUsgPrcPMsg.bllgMtrLbCd.getValue());
                    Integer count = 0;
                    if (tierCntMap.containsKey(tierKey)) {
                        count = tierCntMap.get(tierKey) + 1;
                    }
                    tierCntMap.put(tierKey, count);

                    TreeMap<BigDecimal, BigDecimal> cntMap = new TreeMap<BigDecimal, BigDecimal>();
                    if (tierMap.containsKey(tierKey)) {
                        cntMap = tierMap.get(tierKey);
                    }
                    BigDecimal minCnt = svcUsgPrcPMsg.minCopyVolCnt.getValue();
                    BigDecimal maxCnt = svcUsgPrcPMsg.maxCopyVolCnt.getValue();

                    if (ZYPCommonFunc.hasValue(minCnt)) {
                        cntMap.put(minCnt, maxCnt);
                        tierMap.put(tierKey, cntMap);
                    }

                    if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.minCopyVolCnt)) {
                        addMsgForSvcUsgPrcList(msgMap, NSZM1197E, svcUsgPrcPMsg);
                        hasError = true;
                    } else if (MAX_TIER_CNT.compareTo(minCnt) < 0) {
                        addMsgForSvcUsgPrcList(msgMap, NSZM1206E, svcUsgPrcPMsg);
                        hasError = true;
                    }
                    if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.maxCopyVolCnt)) {
                        addMsgForSvcUsgPrcList(msgMap, NSZM1198E, svcUsgPrcPMsg);
                        hasError = true;
                    } else if (MAX_TIER_CNT.compareTo(svcUsgPrcPMsg.maxCopyVolCnt.getValue()) < 0) {
                        addMsgForSvcUsgPrcList(msgMap, NSZM1207E, svcUsgPrcPMsg);
                        hasError = true;
                    }
                    if (!hasError && minCnt.compareTo(svcUsgPrcPMsg.maxCopyVolCnt.getValue()) >= 0) {
                        addMsgForSvcUsgPrcList(msgMap, NSZM1209E, svcUsgPrcPMsg);
                        hasError = true;
                    }
                    if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.xsMtrAmtRate)) {
                        addMsgForSvcUsgPrcList(msgMap, NSZM1199E, svcUsgPrcPMsg);
                    } else if (MAX_XS_MTR_AMT.compareTo(svcUsgPrcPMsg.xsMtrAmtRate.getValue()) < 0) {
                        addMsgForSvcUsgPrcList(msgMap, NSZM1208E, svcUsgPrcPMsg);
                    }
                }
            // non-tier
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(svcUsgPrcPMsg.actvFlg.getValue()) && ZYPCommonFunc.hasValue(csdPMsg.usgBllgCycleCd) && ZYPCommonFunc.hasValue(svcUsgPrcPMsg.usgMdseCd) && !ZYPCommonFunc.hasValue(svcUsgPrcPMsg.regMtrLbCd)) {
                    if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.copyInclPrcQty)) {
                        addMsgForSvcUsgPrcList(msgMap, NSZM1191E, svcUsgPrcPMsg);
                    }
                    if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.xsMtrAmtRate)) {
                        addMsgForSvcUsgPrcList(msgMap, NSZM1192E, svcUsgPrcPMsg);
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.contrMtrMultRate)) {
                BigDecimal rmin = svcUsgPrcPMsg.contrMtrMultRate.getValue().remainder(fctNum);
                if (rmin.compareTo(BigDecimal.ZERO) != 0) {
                    addMsgForSvcUsgPrcList(msgMap, NSZM1186E, svcUsgPrcPMsg);
                    continue;
                }
            }
        }

        if (hasError) {
            return;
        }
    }

    private boolean isTier(NSZC115001PMsg inPrmPMsg, NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg, Map<BigDecimal, NSZC115001_svcDtlListPMsg> cpoSvcDtlMap) {

        if (inPrmPMsg.svcPrcList.getValidCount() == 0) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.shellLineNum)) {
            return false;
        }
        BigDecimal shellLineNum = svcUsgPrcPMsg.shellLineNum.getValue();
        String dsOrdPosnNum = svcUsgPrcPMsg.dsOrdPosnNum.getValue();
        NSZC115001_svcDtlListPMsg csdPMsg = cpoSvcDtlMap.get(shellLineNum);
        boolean isFleet = DS_CONTR_CATG.FLEET.equals(csdPMsg.dsContrCatgCd.getValue());
        if (isFleet) {
            for (int i = 0; i < inPrmPMsg.svcPrcList.getValidCount(); i++) {
                NSZC115001_svcPrcListPMsg svcPrcPMsg = inPrmPMsg.svcPrcList.no(i);
                // add start 2017/06/14 CSA Defect#18841
                if (!isFleet(inPrmPMsg, svcPrcPMsg.shellLineNum.getValue()) && !ZYPCommonFunc.hasValue(svcPrcPMsg.dsOrdPosnNum)) {
                    continue;
                }
                // add end 2017/06/14 CSA Defect#18841
                if (shellLineNum.compareTo(svcPrcPMsg.shellLineNum.getValue()) != 0) {
                    continue;
                }
                return ZYPCommonFunc.hasValue(svcPrcPMsg.prcTierSvcOfferCd.getValue());
            }
            return false;
        }
        for (int i = 0; i < inPrmPMsg.svcPrcList.getValidCount(); i++) {
            NSZC115001_svcPrcListPMsg svcPrcPMsg = inPrmPMsg.svcPrcList.no(i);
            // add start 2017/06/14 CSA Defect#18841
            if (!isFleet(inPrmPMsg, svcPrcPMsg.shellLineNum.getValue()) && !ZYPCommonFunc.hasValue(svcPrcPMsg.dsOrdPosnNum)) {
                continue;
            }
            // add end 2017/06/14 CSA Defect#18841
            if (shellLineNum.compareTo(svcPrcPMsg.shellLineNum.getValue()) != 0) {
                continue;
            }
            if (!dsOrdPosnNum.equals(svcPrcPMsg.dsOrdPosnNum.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.mdlId)) {
                return false;
            }
            if (svcUsgPrcPMsg.mdlId.getValue().compareTo(svcPrcPMsg.mdlId.getValue()) == 0 && dsOrdPosnNum.equals(svcPrcPMsg.dsOrdPosnNum.getValue())) {
                return ZYPCommonFunc.hasValue(svcPrcPMsg.prcTierSvcOfferCd.getValue());
            }
        }
        return false;
    }

    private void checkCombSvcDtlList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();

        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        NSZC115001_svcPrcListPMsg svcPrcPMsg = null;
        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;

        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);

            if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            // 2018/05/07 QC#22482 Add Start
            if (isManOvrd(svcDtlPMsg)) {
                continue;
            }
            // 2018/05/07 QC#22482 Add End

            // 11)-1.From Period Month Number < 1
            if (BigDecimal.ONE.compareTo(svcDtlPMsg.fromPerMthNum.getValue()) > 0) {
                addMsgForSvcDtlList(msgMap, NSZM1184E, svcDtlPMsg);
                continue;
            }

            // 11)-2.From Period Month Number > To Period Month Number
            if (svcDtlPMsg.fromPerMthNum.getValue().compareTo(svcDtlPMsg.toPerMthNum.getValue()) > 0) {
                addMsgForSvcDtlList(msgMap, NSZM1185E, svcDtlPMsg);
                continue;
            }

            if (ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.manContrOvrdFlg.getValue())) {
                if (!ZYPCommonFunc.hasValue(svcDtlPMsg.manContrOvrdRsnCd) || !ZYPCommonFunc.hasValue(svcDtlPMsg.manContrOvrdCmntTxt)) {
                    addMsgForSvcDtlList(msgMap, NSZM1168E, svcDtlPMsg);
                }
                continue;
            }

            BigDecimal shellLineNum = svcDtlPMsg.shellLineNum.getValue();
            String contrCatgCd = svcDtlPMsg.dsContrCatgCd.getValue();

            boolean existFlg = false;
            for (int j = 0; j < inPrmPMsg.svcPrcList.getValidCount(); j++) {
                svcPrcPMsg = inPrmPMsg.svcPrcList.no(j);
                if (RQST_TP_DEL.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                    continue;
                }
                // add start 2017/06/14 CSA Defect#18841
                if (!isFleet(inPrmPMsg, svcPrcPMsg.shellLineNum.getValue()) && !ZYPCommonFunc.hasValue(svcPrcPMsg.dsOrdPosnNum)) {
                    continue;
                }
                // add end 2017/06/14 CSA Defect#18841
                if (shellLineNum.compareTo(svcPrcPMsg.shellLineNum.getValue()) != 0) {
                    continue;
                }

                existFlg = true;

                // 4) Check of Aggregate Contract
                if (DS_CONTR_CATG.AGGREGATE.equals(contrCatgCd)) {
                    if (!ZYPCommonFunc.hasValue(svcDtlPMsg.usgBllgCycleCd)) {
                        continue;
                    }
                    BigDecimal mdlId = svcPrcPMsg.mdlId.getValue();

                    // QC#29248
                    if (!NWXC150001DsCheck.hasBillableCounter(//
                            glblCmpyCd, mdlId, svcPrcPMsg.prcMtrPkgPk.getValue(), inPrmPMsg.slsDt.getValue())) {
                        continue;
                    }

                    String prcTierSvcOfferCd = svcPrcPMsg.prcTierSvcOfferCd.getValue();
                    boolean isTiered = ZYPCommonFunc.hasValue(prcTierSvcOfferCd);

                    switch (checkCombBllgMtr(shellLineNum, mdlId, inPrmPMsg.svcUsgPrcList, isTiered)) {

                        case ERR_NSZM1171E:
                            addMsgForSvcDtlList(msgMap, NSZM1171E, svcDtlPMsg);
                            break;

                        case ERR_NSZM1173E:
                            addMsgForSvcDtlList(msgMap, NSZM1173E, svcDtlPMsg);
                            break;

                        case ERR_NSZM1174E:
                            addMsgForSvcDtlList(msgMap, NSZM1174E, svcDtlPMsg);
                            break;

                        default:
                            // this case is normal
                    }

                } else if (DS_CONTR_CATG.FLEET.equals(contrCatgCd)) {

                    // 10) Model consistency checks in the case of Fleet Contract

                    for (int m = 0; m < inPrmPMsg.svcConfigRefList.getValidCount(); m++) {
                        svcConfigRefPMsg = inPrmPMsg.svcConfigRefList.no(m);
                        if (RQST_TP_DEL.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                            continue;
                        }
                        if (shellLineNum.compareTo(svcConfigRefPMsg.shellLineNum.getValue()) != 0) {
                            continue;
                        }

                        cpoDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                        cpoDtlTMsg.cpoOrdNum.setValue(svcConfigRefPMsg.cpoOrdNum.getValue());
                        cpoDtlTMsg.cpoDtlLineNum.setValue(svcConfigRefPMsg.cpoDtlLineNum.getValue());
                        cpoDtlTMsg.cpoDtlLineSubNum.setValue(svcConfigRefPMsg.cpoDtlLineSubNum.getValue());
                        BigDecimal mdlId = getMdlIdByCpoDtl(cpoDtlTMsg);

                        // QC#29248
                        boolean hasBillableCounter //
                        = NWXC150001DsCheck.hasBillableCounter(this.glblCmpyCd, mdlId, svcPrcPMsg.prcMtrPkgPk.getValue(), inPrmPMsg.slsDt.getValue());
                        if (hasBillableCounter // QC#29248
                                && ZYPCommonFunc.hasValue(svcDtlPMsg.usgBllgCycleCd)) {
                            PRC_MTR_PKG_BLLG_MTRTMsg prcMtrPkgBllgTMsg = new PRC_MTR_PKG_BLLG_MTRTMsg();
                            ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgTMsg.glblCmpyCd, this.glblCmpyCd);
                            ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgTMsg.prcMtrPkgPk, svcPrcPMsg.prcMtrPkgPk);
                            ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgTMsg.mdlId, mdlId);

                            List<String> mtrLbList = getMtrLbByBllgMtr(prcMtrPkgBllgTMsg, inPrmPMsg.slsDt.getValue());
                            if (mtrLbList == null || mtrLbList.size() == 0) {
                                addMsgForSvcConfigRefList(msgMap, NSZM1175E, svcConfigRefPMsg);
                                continue;
                            }
                        }
                        existFlg = true;
                    }

                    if (!existFlg) {
                        addMsgForSvcDtlList(msgMap, NSZM1175E, svcDtlPMsg);
                        continue;
                    }
                }
            }

            // 9) Contract condition check
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk)) {
                continue;
            }

            DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
            dsContrTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            dsContrTMsg.dsContrPk.setValue(svcDtlPMsg.dsContrPk.getValue());
            dsContrTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(dsContrTMsg);
            if (dsContrTMsg == null) {
                addMsgForSvcDtlList(msgMap, NSZM1176E, svcDtlPMsg);
                continue;
            }

            // START 2018/01/04 M.Kidokoro [QC#23167,ADD]
            if (!this.beforeBillByTPMap.containsKey(svcDtlPMsg.dsContrPk.getValue())) {
                this.beforeBillByTPMap.put(svcDtlPMsg.dsContrPk.getValue(), dsContrTMsg.billByTpCd.getValue());
            }
            // END 2018/01/04 M.Kidokoro [QC#23167,ADD]

            String dsContrStsCd = dsContrTMsg.dsContrStsCd.getValue();
            if (DS_CONTR_STS.EXPIRED.equals(dsContrStsCd) || DS_CONTR_STS.CANCELLED.equals(dsContrStsCd) || DS_CONTR_STS.TERMINATED.equals(dsContrStsCd)) {
                addMsgForSvcDtlList(msgMap, NSZM1177E, svcDtlPMsg);
                continue;
            }

            // 11) "From Period Month Number" and integrity check of "To Period Month Number".
            BigDecimal termMthNum = svcDtlPMsg.toPerMthNum.getValue().subtract(svcDtlPMsg.fromPerMthNum.getValue()).add(BigDecimal.ONE);

            if (!checkBllgCycle(svcDtlPMsg.baseBllgCycleCd.getValue(), termMthNum)) {
                addMsgForSvcDtlList(msgMap, NSZM1178E, svcDtlPMsg);
                continue;
            }

            if (ZYPCommonFunc.hasValue(svcDtlPMsg.usgBllgCycleCd)) {
                if (!checkBllgCycle(svcDtlPMsg.usgBllgCycleCd.getValue(), termMthNum)) {
                    addMsgForSvcDtlList(msgMap, NSZM1179E, svcDtlPMsg);
                    continue;
                }
            }
        }
    }

    private int checkCombBllgMtr(BigDecimal shellLineNum, BigDecimal mdlId, NSZC115001_svcUsgPrcListPMsgArray svcUsgPrcList, boolean isTiered) {

        Map<String, BigDecimal> xsMtrAmtRateMap = new HashMap<String, BigDecimal>();
        Map<String, Integer> tierCntMap = new HashMap<String, Integer>();
        NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg = null;
        boolean existFlg = false;

        for (int k = 0; k < svcUsgPrcList.getValidCount(); k++) {
            svcUsgPrcPMsg = svcUsgPrcList.no(k);
            if (isTiered && !ZYPCommonFunc.hasValue(svcUsgPrcPMsg.prcSvcTierTpCd)) {
                continue;
            }
            if (RQST_TP_DEL.equals(svcUsgPrcPMsg.xxRqstTpCd.getValue())) {
                continue;
            }
            if (shellLineNum.compareTo(svcUsgPrcPMsg.shellLineNum.getValue()) != 0 || mdlId.compareTo(svcUsgPrcPMsg.mdlId.getValue()) != 0) {
                continue;
            }
            existFlg = true;

            // exclude hard counter
            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.regMtrLbCd)) {
                continue;
            }

            String bllgMtrLbCd = svcUsgPrcPMsg.bllgMtrLbCd.getValue();
            if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.xsMtrAmtRate)) {
                continue;
            }
            BigDecimal xsMtrAmtRate = svcUsgPrcPMsg.xsMtrAmtRate.getValue();

            String tier = "";
            if (isTiered || ZYPCommonFunc.hasValue(svcUsgPrcPMsg.prcSvcTierTpCd)) {
                tier = svcUsgPrcPMsg.prcSvcTierTpCd.getValue();
                if (!tierCntMap.containsKey(bllgMtrLbCd)) {
                    tierCntMap.put(bllgMtrLbCd, 1);
                } else {
                    tierCntMap.put(bllgMtrLbCd, tierCntMap.get(bllgMtrLbCd) + 1);
                }
            }

            String key = createCacheKey(bllgMtrLbCd, tier);
            if (!xsMtrAmtRateMap.containsKey(key)) {
                xsMtrAmtRateMap.put(key, xsMtrAmtRate);
                continue;
            }
            if (xsMtrAmtRate.compareTo(xsMtrAmtRateMap.get(key)) != 0) {
                return ERR_NSZM1173E;
            }
        }

        if (!existFlg) {
            return ERR_NSZM1171E;
        }

        if (!isTiered || tierCntMap.isEmpty()) {
            return 0;
        }

        int prevTierCnt = 0;
        for (int tierCnt : tierCntMap.values()) {
            if (prevTierCnt != 0 && prevTierCnt != tierCnt) {
                return ERR_NSZM1174E;
            }
            prevTierCnt = tierCnt;
        }

        return 0;
    }

    private boolean checkBllgCycle(String bllgCycleCd, BigDecimal termMthNum) {
        BLLG_CYCLETMsg bllgCycleTMsg = new BLLG_CYCLETMsg();
        bllgCycleTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        bllgCycleTMsg.bllgCycleCd.setValue(bllgCycleCd);

        bllgCycleTMsg = (BLLG_CYCLETMsg) S21FastTBLAccessor.findByKey(bllgCycleTMsg);
        if (bllgCycleTMsg == null) {
            return false;
        }

        if (!ZYPCommonFunc.hasValue(bllgCycleTMsg.bllgCycleMthAot)) {
            return false;
        }

        if (BigDecimal.ZERO.compareTo(termMthNum.remainder(bllgCycleTMsg.bllgCycleMthAot.getValue())) != 0) {
            return false;
        }

        return true;
    }

    private void checkCombSvcConfigRefList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();

        TreeMap<String, List<BigDecimal>> shellLineNumMap = new TreeMap<String, List<BigDecimal>>();
        List<BigDecimal> shellLineNumList = null;
        List<String> errCpoDtlKeyList = new ArrayList<String>();

        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;
        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        NSZC115001_svcPrcListPMsg svcPrcPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcConfigRefList.getValidCount(); i++) {
            svcConfigRefPMsg = inPrmPMsg.svcConfigRefList.no(i);

            if (RQST_TP_DEL.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            // 2018/05/07 QC#22482 Add Start
            if (isManOvrd(inPrmPMsg, svcConfigRefPMsg.shellLineNum.getValue())) {
                continue;
            }
            // 2018/05/07 QC#22482 Add End

            String cpoOrdNum = svcConfigRefPMsg.cpoOrdNum.getValue();
            String cpoDtlLineNum = svcConfigRefPMsg.cpoDtlLineNum.getValue();
            String cpoDtlLineSubNum = svcConfigRefPMsg.cpoDtlLineSubNum.getValue();

            BigDecimal dbMdlId = null;
            cpoDtlTMsg.clear();
            cpoDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            cpoDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);
            cpoDtlTMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
            cpoDtlTMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);
            dbMdlId = getMdlIdByCpoDtl(cpoDtlTMsg);
            if (dbMdlId == null) {
                addMsgForSvcConfigRefList(msgMap, NSZM1188E, svcConfigRefPMsg);
                continue;
            }

            // 12)-1.Setup for overlap check of the "Period Month"
            String key = createCacheKey(cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
            if (shellLineNumMap.containsKey(key)) {
                shellLineNumList = shellLineNumMap.get(key);
            } else {
                shellLineNumList = new ArrayList<BigDecimal>();
            }
            shellLineNumList.add(svcConfigRefPMsg.shellLineNum.getValue());
            shellLineNumMap.put(key, shellLineNumList);

            BigDecimal shellLineNum = svcConfigRefPMsg.shellLineNum.getValue();
            boolean existFlg = false;
            String dsContrCatgCd = null;
            for (int j = 0; j < inPrmPMsg.svcDtlList.getValidCount(); j++) {
                svcDtlPMsg = inPrmPMsg.svcDtlList.no(j);
                if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                    continue;
                }
                if (shellLineNum.compareTo(svcDtlPMsg.shellLineNum.getValue()) != 0) {
                    continue;
                }
                dsContrCatgCd = svcDtlPMsg.dsContrCatgCd.getValue();
                existFlg = true;
                break;
            }
            if (!existFlg) {
                errCpoDtlKeyList.add(key);
                addMsgForSvcConfigRefList(msgMap, NSZM1169E, svcConfigRefPMsg);
                continue;
            }
            if (svcDtlPMsg != null && !ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.addAsryFlg.getValue())) {
                if (!isExistsMainMachine(svcConfigRefPMsg, inPrmPMsg)) {
                    addMsgForSvcConfigRefList(msgMap, NSZM1218E, svcConfigRefPMsg);
                }
            }

            // QC#29248 del
//            // START 2017/08/18 [QC#20651, ADD]
//            if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.mtrReadMethCd)) {
//                if (ZYPCommonFunc.hasValue(svcDtlPMsg.usgBllgCycleCd) && isMeteredModel(inPrmPMsg, svcDtlPMsg, svcConfigRefPMsg)) {
//                    addMsgForSvcConfigRefList(msgMap, NSZM1133E, svcConfigRefPMsg);
//                }
//            }
//            // END   2017/08/18 [QC#20651, ADD]

            if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                continue;
            }

            // 2) Consistency check of Model and CPO
            if (!isFleet(inPrmPMsg, shellLineNum)) {
                String dsOrdPosnNum = svcConfigRefPMsg.dsOrdPosnNum.getValue();
                BigDecimal prmMdlId = null;
                for (int k = 0; k < inPrmPMsg.svcPrcList.getValidCount(); k++) {
                    svcPrcPMsg = inPrmPMsg.svcPrcList.no(k);
                    if (RQST_TP_DEL.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                        continue;
                    }
                    // add start 2017/06/14 CSA Defect#18841
                    if (!isFleet(inPrmPMsg, svcPrcPMsg.shellLineNum.getValue()) && !ZYPCommonFunc.hasValue(svcPrcPMsg.dsOrdPosnNum)) {
                        continue;
                    }
                    // add end 2017/06/14 CSA Defect#18841
                    if (!dsOrdPosnNum.equals(svcPrcPMsg.dsOrdPosnNum.getValue())) {
                        continue;
                    }
                    prmMdlId = svcPrcPMsg.mdlId.getValue();
                    break;
                }
                if (prmMdlId == null) {
                    errCpoDtlKeyList.add(key);
                    addMsgForSvcConfigRefList(msgMap, NSZM1172E, svcConfigRefPMsg);
                    continue;
                }

                if (prmMdlId.compareTo(dbMdlId) != 0) {
                    errCpoDtlKeyList.add(key);
                    addMsgForSvcConfigRefList(msgMap, NSZM1181E, svcConfigRefPMsg);
                    continue;
                }
            }
        }
    }

    private void checkCombSvcPrcList(S21ApiMessageMap msgMap) {

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        String cpoOrdNum = inPrmPMsg.refCpoOrdNum.getValue();

        PRC_MTR_PKG_BLLG_MTRTMsg prcMtrPkgBllgTMsg = new PRC_MTR_PKG_BLLG_MTRTMsg();
        Map<String, Integer> tierCntByMtrLbMap = new HashMap<String, Integer>();

        NSZC115001_svcPrcListPMsg svcPrcPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcPrcList.getValidCount(); i++) {
            svcPrcPMsg = inPrmPMsg.svcPrcList.no(i);

            if (RQST_TP_DEL.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            BigDecimal shellLineNum = svcPrcPMsg.shellLineNum.getValue();
            BigDecimal mdlId = svcPrcPMsg.mdlId.getValue();
            String dsOrdPosnNum = svcPrcPMsg.dsOrdPosnNum.getValue();

            boolean existFlg = false;
            String dsContrCatgCd = null;
            String usgBllgCycleCd = null;
            NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
            for (int l = 0; l < inPrmPMsg.svcDtlList.getValidCount(); l++) {
                svcDtlPMsg = inPrmPMsg.svcDtlList.no(l);
                if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                    continue;
                }
                if (shellLineNum.compareTo(svcDtlPMsg.shellLineNum.getValue()) != 0) {
                    continue;
                }
                dsContrCatgCd = svcDtlPMsg.dsContrCatgCd.getValue();
                usgBllgCycleCd = svcDtlPMsg.usgBllgCycleCd.getValue();
                existFlg = true;
                break;
            }
            if (!existFlg) {
                addMsgForSvcPrcList(msgMap, NSZM1170E, svcPrcPMsg);
                continue;
            }
            // add start 2017/06/14 CSA Defect#18841
            if (!isFleet(inPrmPMsg, svcPrcPMsg.shellLineNum.getValue()) && !ZYPCommonFunc.hasValue(svcPrcPMsg.dsOrdPosnNum)) {
                continue;
            }
            // add end 2017/06/14 CSA Defect#18841
            if (!ZYPCommonFunc.hasValue(usgBllgCycleCd)) {
                continue;
            }
            // 2018/05/07 QC#22482 Add Start
            if (!ZYPCommonFunc.hasValue(svcPrcPMsg.prcMtrPkgPk)) {
                continue;
            }
            // 2018/05/07 QC#22482 Add End

            BigDecimal val = svcPrcPMsg.prcMtrPkgPk.getValue();
            BigDecimal val2 = svcPrcPMsg.mdlId.getValue();
            prcMtrPkgBllgTMsg.setSQLID("001");
            prcMtrPkgBllgTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            prcMtrPkgBllgTMsg.setConditionValue("prcMtrPkgPk01", val);
            ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgTMsg.prcMtrPkgPk, val);
            ZYPEZDItemValueSetter.setValue(prcMtrPkgBllgTMsg.mdlId, val2);
            boolean fltFlg = DS_CONTR_CATG.FLEET.equals(dsContrCatgCd);
            List<String> mtrLbList = null;
            if (fltFlg) {
                List<BigDecimal> mdlIdList = new ArrayList<BigDecimal>();
                for (int ix = 0; ix < inPrmPMsg.svcConfigRefList.getValidCount(); ix++) {
                    if (RQST_TP_DEL.equals(inPrmPMsg.svcConfigRefList.no(ix).xxRqstTpCd.getValue())) {
                        continue;
                    }
                    if (shellLineNum.compareTo(inPrmPMsg.svcConfigRefList.no(ix).shellLineNum.getValue()) == 0) {
                        Map<String, BigDecimal> mdlIdMap = getMdlIdFromDsCpoConfig(cpoOrdNum, inPrmPMsg.svcConfigRefList.no(ix).dsOrdPosnNum.getValue());
                        mdlIdList.add(mdlIdMap.get("MDL_ID"));
                    }
                }
                if (inPrmPMsg.svcConfigRefList.getValidCount() == 0) {
                    mdlIdList = getMdlIdFromDsCpoConfigForPrcOnly(svcPrcPMsg);
                }
                if (mdlIdList == null || mdlIdList.isEmpty()) {
                    addMsgForSvcPrcList(msgMap, NSZM1159E, svcPrcPMsg);
                    continue;
                }
                mtrLbList = getMtrLbByBllgMtrForFleet(prcMtrPkgBllgTMsg, mdlIdList.toArray(new BigDecimal[0]), inPrmPMsg.slsDt.getValue());
            } else {
                mtrLbList = getMtrLbByBllgMtr(prcMtrPkgBllgTMsg, inPrmPMsg.slsDt.getValue());
            }

            boolean tierFlg = ZYPCommonFunc.hasValue(svcPrcPMsg.prcTierSvcOfferCd);

            // 7) integrity check of the Meter Read Package
            List<String> inpMtrLbList = new ArrayList<String>();
            NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg = null;
            for (int j = 0; j < inPrmPMsg.svcUsgPrcList.getValidCount(); j++) {
                svcUsgPrcPMsg = inPrmPMsg.svcUsgPrcList.no(j);
                if (RQST_TP_DEL.equals(svcUsgPrcPMsg.xxRqstTpCd.getValue())) {
                    continue;
                }
                // add start 2017/06/14 CSA Defect#18841
                if (!fltFlg && !ZYPCommonFunc.hasValue(svcUsgPrcPMsg.dsOrdPosnNum)) {
                    continue;
                }
                // add end 2017/06/14 CSA Defect#18841
                if (shellLineNum.compareTo(svcUsgPrcPMsg.shellLineNum.getValue()) != 0) {
                    continue;
                }
                if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.mdlId) && mdlId.compareTo(svcUsgPrcPMsg.mdlId.getValue()) != 0) {
                    continue;
                }
                if (!fltFlg && ZYPCommonFunc.hasValue(svcUsgPrcPMsg.dsOrdPosnNum) && !dsOrdPosnNum.equals(svcUsgPrcPMsg.dsOrdPosnNum.getValue())) {
                    continue;
                }

                if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.bllgMtrLbCd)) {
                    if (!inpMtrLbList.contains(svcUsgPrcPMsg.bllgMtrLbCd.getValue())) {
                        inpMtrLbList.add(svcUsgPrcPMsg.bllgMtrLbCd.getValue());
                    }
                }

                if (tierFlg) {
                    String bllgMtrLbCd = svcUsgPrcPMsg.bllgMtrLbCd.getValue();
                    int tierCnt = 1;
                    if (tierCntByMtrLbMap.containsKey(bllgMtrLbCd)) {
                        tierCnt = tierCntByMtrLbMap.get(bllgMtrLbCd) + 1;
                    }
                    tierCntByMtrLbMap.put(bllgMtrLbCd, tierCnt);
                }
            }

            if (mtrLbList.size() != inpMtrLbList.size()) {
                addMsgForSvcPrcList(msgMap, NSZM1182E, svcPrcPMsg);
                continue;
            }

            boolean rmvFlg = false;
            for (String inpMtrLb : inpMtrLbList) {
                rmvFlg = mtrLbList.remove(inpMtrLb);
                if (!rmvFlg) {
                    break;
                }
            }

            if (inpMtrLbList.size() > 0 && !rmvFlg) {
                addMsgForSvcPrcList(msgMap, NSZM1162E, svcPrcPMsg);
                continue;
            }

            if (!tierFlg) {
                continue;
            }

            // 8) Tier number of check
            boolean tierCntFlg = true;
            for (int tierCnt : tierCntByMtrLbMap.values()) {
                // if (tierCnt <= 1) {
                if (tierCnt < 1) {
                    tierCntFlg = false;
                    break;
                }
            }

            if (!tierCntFlg) {
                addMsgForSvcPrcList(msgMap, NSZM1183E, svcPrcPMsg);
                continue;
            }
        }
    }

    private List<BigDecimal> getMdlIdFromDsCpoConfigForPrcOnly(NSZC115001_svcPrcListPMsg svcPrcPMsg) {
        List<BigDecimal> mdlIdList = new ArrayList<BigDecimal>();
        List<Map<String, BigDecimal>> rsList = getMdlIdListFromDsCpoConfigForPrcOnly(svcPrcPMsg.dsContrPk.getValue());
        if (rsList == null) {
            return mdlIdList;
        }
        for (Map<String, BigDecimal> rsMap : rsList) {
            BigDecimal mdlId = rsMap.get("MDL_ID");
            if (!mdlIdList.contains(mdlId)) {
                mdlIdList.add(mdlId);
            }
        }
        return mdlIdList;
    }

    private List<Map<String, BigDecimal>> getMdlIdListFromDsCpoConfigForPrcOnly(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("configCatgCd", CONFIG_CATG.OUTBOUND);

        List<Map<String, BigDecimal>> rslt = this.ssmBatchClient.queryObjectList("getMdlIdFromDsCpoConfigForPrcOnly", param);
        if (rslt.size() == 0) {
            return null;
        }
        return rslt;
    }

    private void regist(S21ApiMessageMap msgMap) {

        registDsContr(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        registSvcMemo(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        registDsContrDtlForShell(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        registDsContrDtlForFltAgg(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        registDsContrDtlForMachine(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        registDsContrDtlForAsry(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        removeDsContrDtlForShell(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        removeDsContrDtlForFltAgg(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        removeUsg(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        registUsg(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        registUsgForAgg(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        // add start 2017/06/12 CSA Defect#18873
        registShortageShell(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }
        // add end 2017/06/12 CSA Defect#18873

        registDsContrAddlChrgForRental(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        registDsContrAddlChrgForNormal(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        removeAsryAddlPoCardForRemovedMachine(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

//        updateDsContrDtlForAggPrc(msgMap);
//        if (msgMap.getMsgMgr().isXxMsgId()) {
//            return;
//        }

        updateBaseTotal(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        registDsContrTaxDtl(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        registSvcTermCond(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        registDsContrCrCardPo(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        registDsContrRnwEscl(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        // Register SCHD_CRAT_TMPL, SCHD_CRAT_TMPL_LINE
        registSchdCratTmplList(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        // Remove SCHD_CRAT_TMPL, SCHD_CRAT_TMPL_LINE
        removeSchdCratTmplWOConfig(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        // START 2018/02/20 K.Kojima [QC#24234,ADD]
        updateDsContrDtlForAsry(msgMap);
        // END 2018/02/20 K.Kojima [QC#24234,ADD]

        // START 2018/01/04 M.Kidooro [QC#23167,ADD]
        updateBllgMtrBillToCust(msgMap);
        // END 2018/01/04 M.Kidooro [QC#23167,ADD]

        // QC#20336(L3#397)
        updateSvcTermCondQtyCap();

        //
        checkForAfterRegistration(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }
    }

    // QC#20336(L3#397)
    private void updateSvcTermCondQtyCap() {
        for (Entry<BigDecimal, BigDecimal> pk : this.updateCapQtyPkMap.entrySet()) {
            SVC_TERM_CONDTMsg tMsg = new SVC_TERM_CONDTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcTermCondPk, pk.getKey());
            tMsg = (SVC_TERM_CONDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
            if (tMsg == null) {
                continue;
            }

            BigDecimal capQty = getQtyContrCapQty(pk.getValue());
            if (ZYPCommonFunc.hasValue(capQty)) {
                ZYPEZDItemValueSetter.setValue(tMsg.svcTermAttrbMapValCd, capQty.toPlainString());
                S21ApiTBLAccessor.update(tMsg);
            }
        }
    }

    private void checkForAfterRegistration(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            NSZC115001_svcDtlListPMsg svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);
            if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                continue;
            }
            // 2018/07/09 S21_NA#26528 Del Start
            // if (isErrorNumOfTierLine(inPrmPMsg, svcDtlPMsg)) {
                // setErrStatus(msgMap, NSZM1200E); //mod u.kim 2018/03/06 QC#24056
            // }
            // 2018/07/09 S21_NA#26528 Del End
            if (DS_CONTR_CATG.AGGREGATE.equals(svcDtlPMsg.dsContrCatgCd.getValue())) {
                if (getCountMtrPkgForAggregate(inPrmPMsg, svcDtlPMsg) > 1) {
                    setErrStatus(msgMap, NSZM1194E); //mod u.kim 2018/03/06 QC#24056
                }
                if (getCountTierInfoForAggregate(inPrmPMsg, svcDtlPMsg) > 1) {
                    setErrStatus(msgMap, NSZM1196E); //mod u.kim 2018/03/06 QC#24056
                    continue;
                }
                if (isErrorNumOfExcessChargeError(inPrmPMsg, svcDtlPMsg)) {
                    setErrStatus(msgMap, NSZM1195E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }

        for (int i = 0; i < inPrmPMsg.svcUsgPrcList.getValidCount(); i++) {
            NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg = inPrmPMsg.svcUsgPrcList.no(i);
            if (RQST_TP_DEL.equals(svcUsgPrcPMsg.xxRqstTpCd.getValue())) {
                continue;
            }
            if (ZYPConstant.FLG_OFF_N.equals(svcUsgPrcPMsg.actvFlg.getValue())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.prcSvcTierTpCd)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.regMtrLbCd)) {
                continue;
            }
            if (isOverlappingTerm(svcUsgPrcPMsg, inPrmPMsg)) {
                setErrStatus(msgMap, NSZM1180E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private boolean isOverlappingTerm(NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg, NSZC115001PMsg inPrmPMsg) {
        NSZC115001_svcDtlListPMsg svcDtlPMsg = new NSZC115001_svcDtlListPMsg();
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);
            if (S21StringUtil.isEquals(svcUsgPrcPMsg.dsContrPk.getValue().toPlainString(), svcDtlPMsg.dsContrPk.getValue().toPlainString())) {
                break;
            }
        }
        if (!ZYPCommonFunc.hasValue(svcDtlPMsg.fromPerMthNum) || !ZYPCommonFunc.hasValue(svcDtlPMsg.toPerMthNum) || !ZYPCommonFunc.hasValue(svcUsgPrcPMsg.dsOrdPosnNum)) {
            return false;
        }
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("cpoOrdNum", inPrmPMsg.refCpoOrdNum);
        params.put("shellLineNum", svcUsgPrcPMsg.shellLineNum);
        params.put("fromMth", svcDtlPMsg.fromPerMthNum);
        params.put("toMth", svcDtlPMsg.toPerMthNum);
        params.put("bllgMtrLbCd", svcUsgPrcPMsg.bllgMtrLbCd);
        params.put("dsOrdPosnNum", svcUsgPrcPMsg.dsOrdPosnNum);

        Integer cnt = (Integer) this.ssmBatchClient.queryObject("cntOverlappingTerm", params);
        return cnt > 0;
    }

    private boolean isErrorNumOfExcessChargeError(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", inPrmPMsg.refCpoOrdNum.getValue());
        param.put("shellLineNum", svcDtlPMsg.shellLineNum.getValue());
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.BASE_AND_USAGE);
        param.put("dsContrCatgAggregate", DS_CONTR_CATG.AGGREGATE);
        param.put("minNum", 1);

        List<Map<String, Object>> rsltList = this.ssmBatchClient.queryObjectList("getNumOfExcessChargeError", param);

        return rsltList.size() > 0;
    }

    private int getCountMtrPkgForAggregate(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", inPrmPMsg.refCpoOrdNum.getValue());
        param.put("shellLineNum", svcDtlPMsg.shellLineNum.getValue());
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.BASE_AND_USAGE);

        List<Map<String, Object>> rsltList = this.ssmBatchClient.queryObjectList("getCountMtrPkgForAggregate", param);

        return rsltList.size();
    }

    private boolean isErrorNumOfTierLine(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", inPrmPMsg.refCpoOrdNum.getValue());
        param.put("shellLineNum", svcDtlPMsg.shellLineNum.getValue());
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.BASE_AND_USAGE);
        param.put("minNum", 2);

        List<Map<String, Object>> rsltList = this.ssmBatchClient.queryObjectList("getNumOfTierLineError", param);

        return rsltList.size() > 0;
    }

    private int getCountTierInfoForAggregate(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", inPrmPMsg.refCpoOrdNum.getValue());
        param.put("shellLineNum", svcDtlPMsg.shellLineNum.getValue());
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.BASE_AND_USAGE);

        List<Map<String, Object>> rsltList = this.ssmBatchClient.queryObjectList("getTierInfoForAggregate", param);

        return rsltList.size();
    }

    private void registDsContr(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<DS_CONTRTMsg> dsContrTMsgNewList = new ArrayList<DS_CONTRTMsg>();
        List<DS_CONTRTMsg> dsContrTMsgModList = new ArrayList<DS_CONTRTMsg>();
        List<DS_CONTRTMsg> dsContrTMsgDelList = new ArrayList<DS_CONTRTMsg>();
        List<BigDecimal> delDsContrPkList = new ArrayList<BigDecimal>();
        // START 2017/12/11 K.Kojima [QC#19955,ADD]
        List<BigDecimal> changeBaseBllgCycleDsContrPkList = new ArrayList<BigDecimal>();
        List<BigDecimal> changeMtrBllgCycleDsContrPkList = new ArrayList<BigDecimal>();
        // END 2017/12/11 K.Kojima [QC#19955,ADD]

        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);

            boolean addContrFlg = false;
            if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk_AD)) {
                addContrFlg = true;

                // START 2017/08/29 [QC#20665, MOD]
                // if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk) && svcDtlPMsg.dsContrPk_AD.getValue().compareTo(svcDtlPMsg.dsContrPk.getValue()) != 0) {
                //     convertContrPk(msgMap, svcDtlPMsg);
                // }
                //
                // ZYPEZDItemValueSetter.setValue(svcDtlPMsg.dsContrPk, svcDtlPMsg.dsContrPk_AD.getValue());
                if (countupEffectiveConfigRef(inPrmPMsg, svcDtlPMsg) > 0) {
                    if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk) && svcDtlPMsg.dsContrPk_AD.getValue().compareTo(svcDtlPMsg.dsContrPk.getValue()) != 0) {
                        convertContrPk(msgMap, svcDtlPMsg);
                    }
                }

                if (!RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue()) || countupEffectiveConfigRef(inPrmPMsg, svcDtlPMsg) > 0) {
                    ZYPEZDItemValueSetter.setValue(svcDtlPMsg.dsContrPk, svcDtlPMsg.dsContrPk_AD.getValue());
                }
                // END  2017/08/29 [QC#20665, MOD]
            }

            DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
            BigDecimal dsContrPk = null;
            if (RQST_TP_NEW.equals(svcDtlPMsg.xxRqstTpCd.getValue()) && !addContrFlg) {
                dsContrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_SQ);
                svcDtlPMsg.dsContrPk.setValue(dsContrPk);

                // DS_CONTR
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, dsContrPk);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrSqNum, "00");
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrTpCd, DS_CONTR_TP.SERVICE);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrStsCd, DS_CONTR_STS.ORDER);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.altPayerCustCd, svcDtlPMsg.soldToCustLocCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrCatgCd, svcDtlPMsg.dsContrCatgCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.pmtTermCashDiscCd, getPmtTermCashDiscCd(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcContrRefCmntTxt, inPrmPMsg.refCpoOrdNum);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.bllgApvlReqFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.ccyCd, getCcyCd());
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.preInvReqFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.rnwCpltFlg, ZYPConstant.FLG_OFF_N);
                // START 2022/03/22 [QC#59683, ADD]
                String dsInvTgtrTpCd = getDsInvTgtrTpCd(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue(), svcDtlPMsg.dsContrCatgCd.getValue());
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsInvTgtrTpCd, dsInvTgtrTpCd);
                // END   2022/03/22 [QC#59683, ADD]
                // mod start 2017/08/09 QC#18799
//                ZYPEZDItemValueSetter.setValue(dsContrTMsg.invSeptBaseUsgFlg, getInvSeptBaseUsgFlg(inPrmPMsg));
                // mod start 2017/09/01 QC#20882
//                ZYPEZDItemValueSetter.setValue(dsContrTMsg.invSeptBaseUsgFlg, getInvSeptBaseUsgFlg(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue()));
                // START 2022/03/22 [QC#59683, MOD]
//                ZYPEZDItemValueSetter.setValue(dsContrTMsg.invSeptBaseUsgFlg, getInvSeptBaseUsgFlg(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue(), svcDtlPMsg.dsContrCatgCd.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.invSeptBaseUsgFlg, getInvSeptBaseUsgFlgByTgtrTp(dsInvTgtrTpCd));
                // END   2022/03/22 [QC#59683, MOD]
                // mod end 2017/09/01 QC#20882
                // mod end 2017/08/09 QC#18799
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrCratDt, inPrmPMsg.slsDt);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrCratPsnCd, getDsContrCratPsnCd(EZDDBCICarrier.getUserID()));
                // mod start 2017/06/22 CSA Defect#19350
//                ZYPEZDItemValueSetter.setValue(dsContrTMsg.leaseCmpyCd, getLeaseCmpyCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue(), svcDtlPMsg.soldToCustLocCd.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.leaseCmpyCd, getLeaseCmpyCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
                // mod end 2017/06/22 CSA Defect#19350
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsAcctNum, svcDtlPMsg.sellToCustCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.tocCd, getTocCd(inPrmPMsg));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.baseChrgToLeaseCmpyFlg, getBaseChrgToLeaseCmpyFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.usgChrgToLeaseCmpyFlg, getUsgChrgToLeaseCmpyFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.mtrEstTpCd, getMtrEstTpCd(inPrmPMsg));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.prcAllocByMachQtyFlg, getPrcAllocByMachQtyFlg(inPrmPMsg, svcDtlPMsg.dsContrCatgCd.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcContrBrCd, getSvcContrBrCd(inPrmPMsg.refCpoOrdNum.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrClsCd, getDsContrClsCd(inPrmPMsg));
                // START 2018/03/13 K.Kojima [QC#24263,DEL]
                // ZYPEZDItemValueSetter.setValue(dsContrTMsg.ctacPsnPk, getCtacPsnPk(inPrmPMsg.refCpoOrdNum.getValue()));
                // END 2018/03/13 K.Kojima [QC#24263,DEL]
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcLineBizCd, getSvcLineBizCd(inPrmPMsg));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrSrcTpCd, DS_CONTR_SRC_TP.CPO);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrEdiCd, getDsContrEdiCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcPgmMdseCd, svcDtlPMsg.svcPgmMdseCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.contrAdminPsnCd, getContrAdminPsnCd(inPrmPMsg.refCpoOrdNum.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.baseBllgCycleCd, svcDtlPMsg.baseBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.mtrBllgCycleCd, svcDtlPMsg.usgBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.prcSvcContrTpCd, svcDtlPMsg.prcSvcContrTpCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.prcSvcPlnTpCd, svcDtlPMsg.prcSvcPlnTpCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.billByTpCd, svcDtlPMsg.billByTpCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.cpoSvcAgmtVerNum, svcDtlPMsg.cpoSvcAgmtVerNum);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.manContrOvrdFlg, getFlgValue(svcDtlPMsg.manContrOvrdFlg.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcPrcCatgCd, SVC_PRC_CATG.MAIN_UNIT_BASE_CHARGE);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.crRebilCd, svcDtlPMsg.crRebilCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.applyEquipBillToFlg, getFlgValue(svcDtlPMsg.applyEquipBillToFlg.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.fixTermInMthAot, svcDtlPMsg.fixTermInMthAot);
                // add start 2017/06/19 CSA Defect#19036
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.shellLineNum, svcDtlPMsg.shellLineNum);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.fromPerMthNum, svcDtlPMsg.fromPerMthNum);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.toPerMthNum, svcDtlPMsg.toPerMthNum);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.cpoOrdNum, inPrmPMsg.refCpoOrdNum);
               // add end 2017/06/19 CSA Defect#19036
                // 2018/08/23 S21_NA#25105 Add Start
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.addlBasePrcCatgCd, svcDtlPMsg.addlBasePrcCatgCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.rntlPrcCatgCd, svcDtlPMsg.rntlPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.addlChrgPrcCatgCd, svcDtlPMsg.addlChrgPrcCatgCd);
                // 2018/08/23 S21_NA#25105 Add End

                dsContrTMsgNewList.add(dsContrTMsg);
                continue;
            }

            dsContrTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            dsContrTMsg.dsContrPk.setValue(svcDtlPMsg.dsContrPk.getValue());
            dsContrTMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrTMsg);
            if (dsContrTMsg == null) {
                setErrStatus(msgMap, NSZM1226E); //mod u.kim 2018/03/06 QC#24056
                continue;
            }

            // START 2017/08/28  [QC#20665, MOD]
            // if (RQST_TP_MOD.equals(svcDtlPMsg.xxRqstTpCd.getValue()) || addContrFlg) {
            //     modDsContrTMsg(dsContrTMsgModList, inPrmPMsg, svcDtlPMsg, dsContrTMsg);
            if (RQST_TP_MOD.equals(svcDtlPMsg.xxRqstTpCd.getValue()) && !addContrFlg) {
                // START 2017/12/11 K.Kojima [QC#19955,ADD]
                if (ZYPCommonFunc.hasValue(dsContrTMsg.baseBllgCycleCd) && ZYPCommonFunc.hasValue(svcDtlPMsg.baseBllgCycleCd) && !dsContrTMsg.baseBllgCycleCd.getValue().equals(svcDtlPMsg.baseBllgCycleCd.getValue())) {
                    changeBaseBllgCycleDsContrPkList.add(dsContrTMsg.dsContrPk.getValue());
                }
                if (ZYPCommonFunc.hasValue(dsContrTMsg.mtrBllgCycleCd) && ZYPCommonFunc.hasValue(svcDtlPMsg.usgBllgCycleCd) && !dsContrTMsg.mtrBllgCycleCd.getValue().equals(svcDtlPMsg.usgBllgCycleCd.getValue())) {
                    changeMtrBllgCycleDsContrPkList.add(dsContrTMsg.dsContrPk.getValue());
                }
                // END 2017/12/11 K.Kojima [QC#19955,ADD]
                // START 2018/02/20 K.Kojima [QC#24234,ADD]
                if (ZYPCommonFunc.hasValue(dsContrTMsg.altPayerCustCd) && ZYPCommonFunc.hasValue(svcDtlPMsg.soldToCustLocCd) && !dsContrTMsg.altPayerCustCd.getValue().equals(svcDtlPMsg.soldToCustLocCd.getValue())) {
                    this.updateBillToCustCd.put(dsContrTMsg.dsContrPk.getValue(), svcDtlPMsg.soldToCustLocCd.getValue());
                }
                // END 2018/02/20 K.Kojima [QC#24234,ADD]
                modDsContrTMsg(dsContrTMsgModList, inPrmPMsg, svcDtlPMsg, dsContrTMsg);
            } else if (addContrFlg && (RQST_TP_MOD.equals(svcDtlPMsg.xxRqstTpCd.getValue()) || RQST_TP_NEW.equals(svcDtlPMsg.xxRqstTpCd.getValue())) ) {
                modDsContrTMsgForAddContr(msgMap, dsContrTMsgNewList, dsContrTMsgModList, dsContrTMsgDelList, inPrmPMsg, svcDtlPMsg, dsContrTMsg);
            // END   2017/08/28  [QC#20665, MOD]
            } else if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(svcDtlPMsg.shellLineNum)) {
                    BigDecimal shellLineNum = getShellLineNum(inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.dsContrPk.getValue());
                    ZYPEZDItemValueSetter.setValue(svcDtlPMsg.shellLineNum, shellLineNum);
                }

                if (isExistsAddContr(inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.shellLineNum.getValue(), svcDtlPMsg.dsContrPk.getValue())) {
                    this.delShellLineNumListForAddContr.add(svcDtlPMsg.shellLineNum.getValue());
                    // START 2017/08/28 [QC#20665, DEL]
                    // add start 2017/06/19 CSA Defect#19036
                    // if (!clearDsContrCpoOrdNum(svcDtlPMsg.dsContrPk.getValue())) {
                    //     msgMap.addXxMsgId(NSZM1224E);
                    // }
                    // add end 2017/06/19 CSA Defect#19036
                    // END   2017/08/28 [QC#20665, DEL]
                    continue;
                }

                delDsContrPkList.add(svcDtlPMsg.dsContrPk.getValue());
                if (!ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.delFlg_PI.getValue())) {
                    dsContrTMsgDelList.add(dsContrTMsg);
                }
            }

            dsContrPk = svcDtlPMsg.dsContrPk.getValue();
        }

        List<DS_CONTR_RECTMsg> dsContrRecTMsgList = new ArrayList<DS_CONTR_RECTMsg>();

        if (!dsContrTMsgNewList.isEmpty()) {
            if (checkDsContr(msgMap, dsContrTMsgNewList)) {
                int result = S21FastTBLAccessor.insert(dsContrTMsgNewList.toArray(new DS_CONTRTMsg[0]));
                if (result != dsContrTMsgNewList.size()) {
                    setErrStatus(msgMap, NSZM1223E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }

        for (DS_CONTRTMsg dsContrTMsg : dsContrTMsgNewList) {
            String docId = getDsContrDocId(dsContrTMsg.dsContrPk.getValue(), inPrmPMsg);

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_NEW, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_RECTMsg dsContrRecTMsg = new DS_CONTR_RECTMsg();
            EZDMsg.copy(dsContrTMsg, null, dsContrRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrRecTMsgList.add(dsContrRecTMsg);
        }

        if (!dsContrTMsgModList.isEmpty()) {
            if (checkDsContr(msgMap, dsContrTMsgModList)) {
                int result = S21FastTBLAccessor.update(dsContrTMsgModList.toArray(new DS_CONTRTMsg[0]));
                if (result != dsContrTMsgModList.size()) {
                    setErrStatus(msgMap, NSZM1224E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }

        for (DS_CONTRTMsg dsContrTMsg : dsContrTMsgModList) {
            String docId = getDsContrDocId(dsContrTMsg.dsContrPk.getValue(), inPrmPMsg);

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_MOD, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_RECTMsg dsContrRecTMsg = new DS_CONTR_RECTMsg();
            EZDMsg.copy(dsContrTMsg, null, dsContrRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrRecTMsgList.add(dsContrRecTMsg);
        }

        if (!dsContrTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrTMsgDelList.toArray(new DS_CONTRTMsg[0]));
            if (result != dsContrTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1225E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        for (DS_CONTRTMsg dsContrTMsg : dsContrTMsgDelList) {
            String docId = getDsContrDocId(dsContrTMsg.dsContrPk.getValue(), inPrmPMsg);

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_RECTMsg dsContrRecTMsg = new DS_CONTR_RECTMsg();
            EZDMsg.copy(dsContrTMsg, null, dsContrRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrRecTMsgList.add(dsContrRecTMsg);
        }

        if (!dsContrRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrRecTMsgList.toArray(new DS_CONTR_RECTMsg[0]));
            if (result != dsContrRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1263E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        if (!delDsContrPkList.isEmpty()) {
            delDsContrChildren(delDsContrPkList, msgMap);
        }

        if (!this.delShellLineNumListForAddContr.isEmpty()) {
            delDsContrChildrenForAddContr(this.delShellLineNumListForAddContr, msgMap);
        }

        // START 2017/12/11 K.Kojima [QC#19955,ADD]
        if (changeBaseBllgCycleDsContrPkList.size() > 0) {
            changeBaseBLlgCycle(changeBaseBllgCycleDsContrPkList, msgMap);
        }
        if (changeMtrBllgCycleDsContrPkList.size() > 0) {
            changeMtrBLlgCycle(changeMtrBllgCycleDsContrPkList, msgMap);
        }
        // END 2017/12/11 K.Kojima [QC#19955,ADD]
    }

    private BigDecimal getShellLineNum(String cpoOrdNum, BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("dsContrPk", dsContrPk);

        return (BigDecimal) this.ssmBatchClient.queryObject("getShellLineNum", param);
    }

    private boolean isExistsAddContr(String cpoOrdNum, BigDecimal shellLineNum, BigDecimal dsContrPk) {
        // mod start 2017/06/19 QC#19036
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        //param.put("shellLineNum", shellLineNum);
        param.put("dsContrPk", dsContrPk);
        String[] dsContrCtrlStsCdList = new String[] {DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.ORDER };
        param.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
        // mod end 2017/06/19 QC#19036

        BigDecimal dsContrDtlPk = (BigDecimal) this.ssmBatchClient.queryObject("getAddContrDtlPk", param);
        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            return true;
        }
        return false;
    }

    private void modDsContrTMsg(List<DS_CONTRTMsg> dsContrTMsgModList, NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, DS_CONTRTMsg dsContrTMsg) {

        if (isSameValueDsContrTMsg(inPrmPMsg, svcDtlPMsg, dsContrTMsg)) {
            // del start 2017/06/19 CSA Defect#19036
            //svcDtlPMsg.xxRqstTpCd.clear();
            // del end 2017/06/19 CSA Defect#19036
            return;
        }

        ZYPEZDItemValueSetter.setValue(dsContrTMsg.altPayerCustCd, svcDtlPMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrCatgCd, svcDtlPMsg.dsContrCatgCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.pmtTermCashDiscCd, getPmtTermCashDiscCd(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcContrRefCmntTxt, inPrmPMsg.refCpoOrdNum);
        // START 2022/03/22 [QC#59683, ADD]
        String dsInvTgtrTpCd = getDsInvTgtrTpCd(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue(), svcDtlPMsg.dsContrCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsInvTgtrTpCd, dsInvTgtrTpCd);
        // END   2022/03/22 [QC#59683, ADD]
        // mod start 2017/08/09 QC#18799
//        ZYPEZDItemValueSetter.setValue(dsContrTMsg.invSeptBaseUsgFlg, getInvSeptBaseUsgFlg(inPrmPMsg));
        // mod start 2017/09/01 QC#20882
//        ZYPEZDItemValueSetter.setValue(dsContrTMsg.invSeptBaseUsgFlg, getInvSeptBaseUsgFlg(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue()));
        // START 2022/03/22 [QC#59683, MOD]
//        ZYPEZDItemValueSetter.setValue(dsContrTMsg.invSeptBaseUsgFlg, getInvSeptBaseUsgFlg(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue(), svcDtlPMsg.dsContrCatgCd.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.invSeptBaseUsgFlg, getInvSeptBaseUsgFlgByTgtrTp(dsInvTgtrTpCd));
        // END   2022/03/22 [QC#59683, MOD]
        // mod end 2017/09/01 QC#20882
        // mod end 2017/08/09 QC#18799
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrLastUpdDt, inPrmPMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrLastUpdPsnCd, getDsContrCratPsnCd(EZDDBCICarrier.getUserID()));
        // mod start 2017/06/22 CSA Defect#19350
//        ZYPEZDItemValueSetter.setValue(dsContrTMsg.leaseCmpyCd, getLeaseCmpyCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue(), svcDtlPMsg.soldToCustLocCd.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.leaseCmpyCd, getLeaseCmpyCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
        // mod end 2017/06/22 CSA Defect#19350
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsAcctNum, svcDtlPMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.tocCd, getTocCd(inPrmPMsg));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.baseChrgToLeaseCmpyFlg, getBaseChrgToLeaseCmpyFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.usgChrgToLeaseCmpyFlg, getUsgChrgToLeaseCmpyFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.mtrEstTpCd, getMtrEstTpCd(inPrmPMsg));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.prcAllocByMachQtyFlg, getPrcAllocByMachQtyFlg(inPrmPMsg, svcDtlPMsg.dsContrCatgCd.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcContrBrCd, getSvcContrBrCd(inPrmPMsg.refCpoOrdNum.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrClsCd, getDsContrClsCd(inPrmPMsg));
        // START 2018/03/13 K.Kojima [QC#24263,DEL]
        // ZYPEZDItemValueSetter.setValue(dsContrTMsg.ctacPsnPk, getCtacPsnPk(inPrmPMsg.refCpoOrdNum.getValue()));
        // END 2018/03/13 K.Kojima [QC#24263,DEL]
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcLineBizCd, getSvcLineBizCd(inPrmPMsg));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrEdiCd, getDsContrEdiCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcPgmMdseCd, svcDtlPMsg.svcPgmMdseCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.contrAdminPsnCd, getContrAdminPsnCd(inPrmPMsg.refCpoOrdNum.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.baseBllgCycleCd, svcDtlPMsg.baseBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.mtrBllgCycleCd, svcDtlPMsg.usgBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.prcSvcContrTpCd, svcDtlPMsg.prcSvcContrTpCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.prcSvcPlnTpCd, svcDtlPMsg.prcSvcPlnTpCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.billByTpCd, svcDtlPMsg.billByTpCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.cpoSvcAgmtVerNum, svcDtlPMsg.cpoSvcAgmtVerNum);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.manContrOvrdFlg, getFlgValue(svcDtlPMsg.manContrOvrdFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.crRebilCd, svcDtlPMsg.crRebilCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.applyEquipBillToFlg, getFlgValue(svcDtlPMsg.applyEquipBillToFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.fixTermInMthAot, svcDtlPMsg.fixTermInMthAot);
        // add start 2017/06/19 CSA Defect#19036
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.shellLineNum, svcDtlPMsg.shellLineNum);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.fromPerMthNum, svcDtlPMsg.fromPerMthNum);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.toPerMthNum, svcDtlPMsg.toPerMthNum);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.cpoOrdNum, inPrmPMsg.refCpoOrdNum);
        // add end 2017/06/19 CSA Defect#19036
        // 2018/08/23 S21_NA#25105 Add Start
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.addlBasePrcCatgCd, svcDtlPMsg.addlBasePrcCatgCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.rntlPrcCatgCd, svcDtlPMsg.rntlPrcCatgCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.addlChrgPrcCatgCd, svcDtlPMsg.addlChrgPrcCatgCd);
        // 2018/08/23 S21_NA#25105 Add End

        dsContrTMsgModList.add(dsContrTMsg);
    }

    private boolean isSameValueDsContrTMsg(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, DS_CONTRTMsg dsContrTMsg) {

        if (!S21StringUtil.isEquals(svcDtlPMsg.soldToCustLocCd.getValue(), dsContrTMsg.altPayerCustCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.dsContrCatgCd.getValue(), dsContrTMsg.dsContrCatgCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getPmtTermCashDiscCd(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue()), dsContrTMsg.pmtTermCashDiscCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(inPrmPMsg.refCpoOrdNum.getValue(), dsContrTMsg.svcContrRefCmntTxt.getValue())) {
            return false;
        }
        // START 2022/03/22 [QC#59683, ADD]
        String dsInvTgtrTpCd = getDsInvTgtrTpCd(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue(), svcDtlPMsg.dsContrCatgCd.getValue());
        if (!S21StringUtil.isEquals(dsInvTgtrTpCd, dsContrTMsg.dsInvTgtrTpCd.getValue())) {
            return false;
        }
        // END   2022/03/22 [QC#59683, ADD]
        // mod start 2017/08/09 QC#18799
//        if (!S21StringUtil.isEquals(getInvSeptBaseUsgFlg(inPrmPMsg), dsContrTMsg.invSeptBaseUsgFlg.getValue())) {
        // mod start 2017/09/01 QC#20882
//        if (!S21StringUtil.isEquals(getInvSeptBaseUsgFlg(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue()), dsContrTMsg.invSeptBaseUsgFlg.getValue())) {
        // START 2022/03/22 [QC#59683, MOD]
//        if (!S21StringUtil.isEquals(getInvSeptBaseUsgFlg(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue(), svcDtlPMsg.dsContrCatgCd.getValue()), dsContrTMsg.invSeptBaseUsgFlg.getValue())) {
        if (!S21StringUtil.isEquals(getInvSeptBaseUsgFlgByTgtrTp(dsInvTgtrTpCd), dsContrTMsg.invSeptBaseUsgFlg.getValue())) {
        // END   2022/03/22 [QC#59683, MOD]
        // mod end 2017/09/01 QC#20882
        // mod end 2017/08/09 QC#18799
            return false;
        }
        // mod start 2017/06/22 CSA Defect#19350
//        if (!S21StringUtil.isEquals(getLeaseCmpyCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue(), svcDtlPMsg.soldToCustLocCd.getValue()), dsContrTMsg.leaseCmpyCd.getValue())) {
        if (!S21StringUtil.isEquals(getLeaseCmpyCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()), dsContrTMsg.leaseCmpyCd.getValue())) {
        // mod end 2017/06/22 CSA Defect#19350
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.sellToCustCd.getValue(), dsContrTMsg.dsAcctNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getTocCd(inPrmPMsg), dsContrTMsg.tocCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getBaseChrgToLeaseCmpyFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()), dsContrTMsg.baseChrgToLeaseCmpyFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getUsgChrgToLeaseCmpyFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()), dsContrTMsg.usgChrgToLeaseCmpyFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getMtrEstTpCd(inPrmPMsg), dsContrTMsg.mtrEstTpCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getPrcAllocByMachQtyFlg(inPrmPMsg, svcDtlPMsg.dsContrCatgCd.getValue()), dsContrTMsg.prcAllocByMachQtyFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getSvcContrBrCd(inPrmPMsg.refCpoOrdNum.getValue()), dsContrTMsg.svcContrBrCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getDsContrClsCd(inPrmPMsg), dsContrTMsg.dsContrClsCd.getValue())) {
            return false;
        }
        // START 2018/03/13 K.Kojima [QC#24263,DEL]
        // if (!isEqualBigDecimal(getCtacPsnPk(inPrmPMsg.refCpoOrdNum.getValue()), dsContrTMsg.ctacPsnPk.getValue())) {
        //     return false;
        // }
        // END 2018/03/13 K.Kojima [QC#24263,DEL]
        if (!S21StringUtil.isEquals(getSvcLineBizCd(inPrmPMsg), dsContrTMsg.svcLineBizCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getDsContrEdiCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()), dsContrTMsg.dsContrEdiCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.svcPgmMdseCd.getValue(), dsContrTMsg.svcPgmMdseCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getContrAdminPsnCd(inPrmPMsg.refCpoOrdNum.getValue()), dsContrTMsg.contrAdminPsnCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.baseBllgCycleCd.getValue(), dsContrTMsg.baseBllgCycleCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.usgBllgCycleCd.getValue(), dsContrTMsg.mtrBllgCycleCd.getValue())) {
            return false;
        }
        // 2018/12/19 QC#29636 Del Start
//      if (!isEqualBigDecimal(svcDtlPMsg.maxUplftPct.getValue(), dsContrTMsg.maxPrcUpRatio.getValue())) {
//          return false;
//      }
      // 2018/12/19 QC#29636 Del End
        if (!S21StringUtil.isEquals(svcDtlPMsg.prcSvcContrTpCd.getValue(), dsContrTMsg.prcSvcContrTpCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.prcSvcPlnTpCd.getValue(), dsContrTMsg.prcSvcPlnTpCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.billByTpCd.getValue(), dsContrTMsg.billByTpCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.cpoSvcAgmtVerNum.getValue(), dsContrTMsg.cpoSvcAgmtVerNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getFlgValue(svcDtlPMsg.manContrOvrdFlg.getValue()), dsContrTMsg.manContrOvrdFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.crRebilCd.getValue(), dsContrTMsg.crRebilCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getFlgValue(svcDtlPMsg.applyEquipBillToFlg.getValue()), dsContrTMsg.applyEquipBillToFlg.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcDtlPMsg.fixTermInMthAot.getValue(), dsContrTMsg.fixTermInMthAot.getValue())) {
            return false;
        }
        // add start 2017/06/19 CSA Defect#19036
        if (!isEqualBigDecimal(svcDtlPMsg.shellLineNum.getValue(), dsContrTMsg.shellLineNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()), dsContrTMsg.billWithEquipFlg.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcDtlPMsg.fromPerMthNum.getValue(), dsContrTMsg.fromPerMthNum.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcDtlPMsg.toPerMthNum.getValue(), dsContrTMsg.toPerMthNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(inPrmPMsg.refCpoOrdNum.getValue(), dsContrTMsg.cpoOrdNum.getValue())) {
            return false;
        }
        // add end 2017/06/19 CSA Defect#19036
        // 2018/08/24 S21_NA#25015 Add Start
        if (!S21StringUtil.isEquals(svcDtlPMsg.addlBasePrcCatgCd.getValue(), dsContrTMsg.addlBasePrcCatgCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.rntlPrcCatgCd.getValue(), dsContrTMsg.rntlPrcCatgCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.addlChrgPrcCatgCd.getValue(), dsContrTMsg.addlChrgPrcCatgCd.getValue())) {
            return false;
        }
        // 2018/08/24 S21_NA#25015 Add End

        return true;
    }

    private void registSvcMemo(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<SVC_MEMOTMsg> svcMemoTMsgNewList = new ArrayList<SVC_MEMOTMsg>();
        List<SVC_MEMOTMsg> svcMemoTMsgModList = new ArrayList<SVC_MEMOTMsg>();
        List<SVC_MEMOTMsg> svcMemoTMsgDelList = new ArrayList<SVC_MEMOTMsg>();

        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);

            SVC_MEMOTMsg svcMemoTMsg = null;
            if (RQST_TP_NEW.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.manContrOvrdFlg.getValue())) {
                    continue;
                }

                // SVC_MEMO
                // START 2017/07/28 [QC#20088, MOD]
                // createSvcMemoTMsg(svcMemoTMsgNewList, svcDtlPMsg);
                createSvcMemoTMsg(svcMemoTMsgNewList, inPrmPMsg, svcDtlPMsg);
                // END   2017/07/28 [QC#20088, MOD]
                continue;
            }

            if (RQST_TP_MOD.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.manContrOvrdFlg.getValue())) {
                    continue;
                }

                svcMemoTMsg = getSvcMemoTMsg(svcDtlPMsg.dsContrPk.getValue());
                if (svcMemoTMsg == null) {
                    // START 2017/07/28 [QC#20088, MOD]
                    // createSvcMemoTMsg(svcMemoTMsgNewList, svcDtlPMsg);
                    createSvcMemoTMsg(svcMemoTMsgNewList, inPrmPMsg, svcDtlPMsg);
                    // END   2017/07/28 [QC#20088, MOD]
                    continue;
                }

                modSvcMemoTMsg(svcMemoTMsgModList, svcDtlPMsg, svcMemoTMsg);
            } else if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                if (this.delShellLineNumListForAddContr.contains(svcDtlPMsg.shellLineNum.getValue())) {
                    continue;
                }

                svcMemoTMsg = getSvcMemoTMsg(svcDtlPMsg.dsContrPk.getValue());
                if (svcMemoTMsg == null) {
                    continue;
                }

                svcMemoTMsgDelList.add(svcMemoTMsg);
            }
        }

        List<SVC_MEMO_RECTMsg> svcMemoRecTMsgList = new ArrayList<SVC_MEMO_RECTMsg>();

        if (!svcMemoTMsgNewList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(svcMemoTMsgNewList.toArray(new SVC_MEMOTMsg[0]));
            if (result != svcMemoTMsgNewList.size()) {
                setErrStatus(msgMap, NSZM1251E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        for (SVC_MEMOTMsg svcMemoTMsg : svcMemoTMsgNewList) {
            String docId = getSvcMemoDocId(svcMemoTMsg, inPrmPMsg);

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_NEW, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            SVC_MEMO_RECTMsg svcMemoRecTMsg = new SVC_MEMO_RECTMsg();
            EZDMsg.copy(svcMemoTMsg, null, svcMemoRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(svcMemoRecTMsg.bizProcLogPk, bizProcLogPk);
            svcMemoRecTMsgList.add(svcMemoRecTMsg);
        }

        if (!svcMemoTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.update(svcMemoTMsgModList.toArray(new SVC_MEMOTMsg[0]));
            if (result != svcMemoTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1252E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        for (SVC_MEMOTMsg svcMemoTMsg : svcMemoTMsgModList) {
            String docId = getSvcMemoDocId(svcMemoTMsg, inPrmPMsg);

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_MOD, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            SVC_MEMO_RECTMsg svcMemoRecTMsg = new SVC_MEMO_RECTMsg();
            EZDMsg.copy(svcMemoTMsg, null, svcMemoRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(svcMemoRecTMsg.bizProcLogPk, bizProcLogPk);
            svcMemoRecTMsgList.add(svcMemoRecTMsg);
        }

        if (!svcMemoTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(svcMemoTMsgDelList.toArray(new SVC_MEMOTMsg[0]));
            if (result != svcMemoTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1253E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        for (SVC_MEMOTMsg svcMemoTMsg : svcMemoTMsgDelList) {
            String docId = getSvcMemoDocId(svcMemoTMsg, inPrmPMsg);

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            SVC_MEMO_RECTMsg svcMemoRecTMsg = new SVC_MEMO_RECTMsg();
            EZDMsg.copy(svcMemoTMsg, null, svcMemoRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(svcMemoRecTMsg.bizProcLogPk, bizProcLogPk);
            svcMemoRecTMsgList.add(svcMemoRecTMsg);
        }
        if (!svcMemoRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(svcMemoRecTMsgList.toArray(new SVC_MEMO_RECTMsg[0]));
            if (result != svcMemoRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1266E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private void createSvcMemoTMsg(List<SVC_MEMOTMsg> svcMemoTMsgNewList, NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg) {
        BigDecimal svcMemoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ);

        SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoPk, svcMemoPk);
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.OVERRIDE_SHELL_CONTRACT);
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcCmntTxt, svcDtlPMsg.manContrOvrdCmntTxt);
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.dsContrPk, svcDtlPMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdUsrId, getDsContrCratPsnCd(EZDDBCICarrier.getUserID()));
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(TS_STORE_PATTERN));
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoRsnCd, svcDtlPMsg.manContrOvrdRsnCd);
        // START 2017/07/28 [QC#20088, ADD]
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTrxNm, SVC_MEMO_TRX_NM_CPO_ORD_NUM);
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTrxNum, inPrmPMsg.refCpoOrdNum);
        // END   2017/07/28 [QC#20088, ADD]

        svcMemoTMsgNewList.add(svcMemoTMsg);
    }

    private void modSvcMemoTMsg(List<SVC_MEMOTMsg> svcMemoTMsgModList, NSZC115001_svcDtlListPMsg svcDtlPMsg, SVC_MEMOTMsg svcMemoTMsg) {

        if (isSameValueSvcMemoTMsg(svcDtlPMsg, svcMemoTMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcCmntTxt, svcDtlPMsg.manContrOvrdCmntTxt);
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdUsrId, getDsContrCratPsnCd(EZDDBCICarrier.getUserID()));
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(TS_STORE_PATTERN));
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoRsnCd, svcDtlPMsg.manContrOvrdRsnCd);

        svcMemoTMsgModList.add(svcMemoTMsg);
    }

    private boolean isSameValueSvcMemoTMsg(NSZC115001_svcDtlListPMsg svcDtlPMsg, SVC_MEMOTMsg svcMemoTMsg) {

        if (!S21StringUtil.isEquals(svcDtlPMsg.manContrOvrdCmntTxt.getValue(), svcMemoTMsg.svcCmntTxt.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.manContrOvrdRsnCd.getValue(), svcMemoTMsg.svcMemoRsnCd.getValue())) {
            return false;
        }

        return true;
    }

    private void registDsContrDtlForShell(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgNewList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgDelList = new ArrayList<DS_CONTR_DTLTMsg>();
        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        NSZC115001_svcPrcListPMsg svcPrcPMsg = null;

        // DS_CONTR_DTL(Shell)
        for (int i = 0; i < inPrmPMsg.svcPrcList.getValidCount(); i++) {
            svcPrcPMsg = inPrmPMsg.svcPrcList.no(i);

            if (ZYPCommonFunc.hasValue(svcPrcPMsg.dsOrdPosnNum)) {
                continue;
            }

            BigDecimal shellLineNum = svcPrcPMsg.shellLineNum.getValue();
            BigDecimal dsContrPk = svcPrcPMsg.dsContrPk.getValue();
            BigDecimal dsContrDtlPk = svcPrcPMsg.dsContrDtlPk.getValue();

            if (RQST_TP_DEL.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                dsContrPk = getDsContrPkFromDsContrDtl(dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(svcPrcPMsg.dsContrPk, dsContrPk);
            } else {
                dsContrPk = getDsContrPk(inPrmPMsg.svcDtlList, shellLineNum, dsContrPk);
                svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, shellLineNum);
                if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                    dsContrDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_DTL_SQ);
                }
                setDsContrDtlGrpNumToMap(inPrmPMsg.refCpoOrdNum.getValue(), shellLineNum, svcPrcPMsg.mdlId.getValue(), dsContrDtlPk);
            }

            DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
            if (RQST_TP_NEW.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(svcPrcPMsg.dsContrPk, dsContrPk);
                ZYPEZDItemValueSetter.setValue(svcPrcPMsg.dsContrDtlPk, dsContrDtlPk);

                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrPk, dsContrPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlTpCd, DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.ORDER);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoOrdNum, inPrmPMsg.refCpoOrdNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsOrdTpCd, getDdsOrdTpCd(inPrmPMsg));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgCycleCd, svcDtlPMsg.baseBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, svcPrcPMsg.basePrcDealAmt);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcFuncAmt, NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), inPrmPMsg.slsDt.getValue(), svcPrcPMsg.basePrcDealAmt.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.supprCrFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcMtrPkgPk, svcPrcPMsg.prcMtrPkgPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.shellLineNum, shellLineNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addContrFlg, getAddContrFlg(svcDtlPMsg.dsContrPk_AD.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addAsryFlg, getFlgValue(svcDtlPMsg.addAsryFlg.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mdlId, svcPrcPMsg.mdlId);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcCatgCd, svcPrcPMsg.maintPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.corpAdvPrcFlg, getCorpAdvPrcFlg(inPrmPMsg.slsDt.getValue(), svcPrcPMsg.prcMtrPkgPk.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlGrpNum, dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.fromPerMthNum, svcDtlPMsg.fromPerMthNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.toPerMthNum, svcDtlPMsg.toPerMthNum);

                // START 2019/01/18 [QC#29782, ADD]
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrCratTpCd, DS_CONTR_CRAT_TP.SHELL);
                // END 2019/01/18 [QC#29782, ADD]

                dsContrDtlTMsgNewList.add(dsContrDtlTMsg);
                continue;
            }

            dsContrDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            dsContrDtlTMsg.dsContrDtlPk.setValue(svcPrcPMsg.dsContrDtlPk.getValue());
            dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
            if (dsContrDtlTMsg == null) {
                setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
            }

            if (RQST_TP_MOD.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                modDsContrDtlTMsgForShell(dsContrDtlTMsgModList, inPrmPMsg, svcDtlPMsg, svcPrcPMsg, dsContrDtlTMsg);

            } else if (RQST_TP_DEL.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                dsContrDtlTMsgDelList.add(dsContrDtlTMsg);
            }
        }

        List<DS_CONTR_DTL_RECTMsg> dsContrDtlRecTMsgList = new ArrayList<DS_CONTR_DTL_RECTMsg>();

        if (!dsContrDtlTMsgNewList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrDtlTMsgNewList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgNewList.size()) {
                setErrStatus(msgMap, NSZM1227E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgNewList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_NEW, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlTMsgModList.isEmpty()) {
            addDsContrDtlPkListForUsgRemoval(dsContrDtlTMsgModList);
            int result = S21FastTBLAccessor.update(dsContrDtlTMsgModList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1228E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgModList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_MOD, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlTMsgDelList.isEmpty()) {
            addDsContrDtlPkListForUsgRemoval(dsContrDtlTMsgDelList);
            int result = S21FastTBLAccessor.removeLogical(dsContrDtlTMsgDelList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1229E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgDelList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrDtlRecTMsgList.toArray(new DS_CONTR_DTL_RECTMsg[0]));
            if (result != dsContrDtlRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1264E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private void modDsContrDtlTMsgForShell(List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList, NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {

        if (isSameValueDsContrDtlTMsgForShell(inPrmPMsg, svcDtlPMsg, svcPrcPMsg, dsContrDtlTMsg)) {
            this.dsContrDtlPkListForUsgRemoval.add(dsContrDtlTMsg.dsContrDtlPk.getValue());
            return;
        }

        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoOrdNum, inPrmPMsg.refCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsOrdTpCd, getDdsOrdTpCd(inPrmPMsg));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgCycleCd, svcDtlPMsg.baseBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, svcPrcPMsg.basePrcDealAmt);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcFuncAmt, NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), inPrmPMsg.slsDt.getValue(), svcPrcPMsg.basePrcDealAmt.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcMtrPkgPk, svcPrcPMsg.prcMtrPkgPk);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.shellLineNum, svcPrcPMsg.shellLineNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addContrFlg, getAddContrFlg(svcDtlPMsg.dsContrPk_AD.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addAsryFlg, getFlgValue(svcDtlPMsg.addAsryFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mdlId, svcPrcPMsg.mdlId);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcCatgCd, svcPrcPMsg.maintPrcCatgCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.corpAdvPrcFlg, getCorpAdvPrcFlg(inPrmPMsg.slsDt.getValue(), svcPrcPMsg.prcMtrPkgPk.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.fromPerMthNum, svcDtlPMsg.fromPerMthNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.toPerMthNum, svcDtlPMsg.toPerMthNum);

        dsContrDtlTMsgModList.add(dsContrDtlTMsg);
    }

    private boolean isSameValueDsContrDtlTMsgForShell(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {

        if (!S21StringUtil.isEquals(inPrmPMsg.refCpoOrdNum.getValue(), dsContrDtlTMsg.cpoOrdNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getDdsOrdTpCd(inPrmPMsg), dsContrDtlTMsg.dsOrdTpCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.baseBllgCycleCd.getValue(), dsContrDtlTMsg.baseBllgCycleCd.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcPrcPMsg.basePrcDealAmt.getValue(), dsContrDtlTMsg.basePrcDealAmt.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcPrcPMsg.prcMtrPkgPk.getValue(), dsContrDtlTMsg.prcMtrPkgPk.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcPrcPMsg.shellLineNum.getValue(), dsContrDtlTMsg.shellLineNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getAddContrFlg(svcDtlPMsg.dsContrPk_AD.getValue()), dsContrDtlTMsg.addContrFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getFlgValue(svcDtlPMsg.addAsryFlg.getValue()), dsContrDtlTMsg.addAsryFlg.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcPrcPMsg.mdlId.getValue(), dsContrDtlTMsg.mdlId.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()), dsContrDtlTMsg.billWithEquipFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcPrcPMsg.maintPrcCatgCd.getValue(), dsContrDtlTMsg.prcCatgCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getCorpAdvPrcFlg(inPrmPMsg.slsDt.getValue(), svcPrcPMsg.prcMtrPkgPk.getValue()), dsContrDtlTMsg.corpAdvPrcFlg.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcDtlPMsg.fromPerMthNum.getValue(), dsContrDtlTMsg.fromPerMthNum.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcDtlPMsg.toPerMthNum.getValue(), dsContrDtlTMsg.toPerMthNum.getValue())) {
            return false;
        }

        return true;
    }

    private void registDsContrDtlForFltAgg(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgNewList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgDelList = new ArrayList<DS_CONTR_DTLTMsg>();
        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        NSZC115001_svcPrcListPMsg svcPrcPMsg = null;
        BigDecimal shellLineNumPrev = BigDecimal.ONE.negate();
        String dsContrCatgCd = null;
        String dsContrDtlTpCd = null;
        boolean usgExistFlg = true;
        // 2018/05/07 QC#22482 Del Start
//        boolean isAggregate = false;
        // 2018/05/07 QC#22482 Del End

        // DS_CONTR_DTL(Fleet, Aggregate)
        for (int i = 0; i < inPrmPMsg.svcPrcList.getValidCount(); i++) {
            svcPrcPMsg = inPrmPMsg.svcPrcList.no(i);
            // 2018/05/07 QC#22482 Add Start
            boolean isAggregate = false;
            // 2018/05/07 QC#22482 Add End

            if (RQST_TP_DEL.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            BigDecimal shellLineNum = svcPrcPMsg.shellLineNum.getValue();

            if (ZYPCommonFunc.hasValue(shellLineNum) && shellLineNum.compareTo(shellLineNumPrev) == 0) {
                continue;
            }

            shellLineNumPrev = shellLineNum;
            svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, shellLineNum);
            dsContrCatgCd = svcDtlPMsg.dsContrCatgCd.getValue();

            if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                dsContrDtlTpCd = DS_CONTR_DTL_TP.FLEET;
            } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                dsContrDtlTpCd = DS_CONTR_DTL_TP.AGGREGATE;
                isAggregate = true;
            } else {
                continue;
            }

            DS_CONTR_DTLTMsg dsContrDtlTMsg = null;
            BigDecimal dsContrPk = svcDtlPMsg.dsContrPk.getValue();
            BigDecimal dsContrDtlPk = getFltAggDsContrDtlPkByDB(dsContrPk);
            if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
                dsContrDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                dsContrDtlTMsg.dsContrDtlPk.setValue(dsContrDtlPk);
                dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
                if (dsContrDtlTMsg == null) {
                    setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
                    continue;
                }
            } else {
                dsContrDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_DTL_SQ);
            }
            setFltAggDsContrDtlPkToMap(inPrmPMsg.refCpoOrdNum.getValue(), shellLineNum, dsContrDtlPk);

            if (dsContrDtlTMsg == null) {
                dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrPk, dsContrPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlTpCd, dsContrDtlTpCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.ORDER);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoOrdNum, inPrmPMsg.refCpoOrdNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsOrdTpCd, getDdsOrdTpCd(inPrmPMsg));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgCycleCd, svcDtlPMsg.baseBllgCycleCd);
                // mod start 2017/08/09 QC#18799
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, getBaseBllgTmgCd(svcDtlPMsg, svcPrcPMsg));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, getBaseBllgTmgCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg));
                // mod end 2017/08/09 QC#18799
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrBllgDay, getContrBllgDay(inPrmPMsg));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgTmgCd, BLLG_TMG_TP.ARREARS);
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, aggregateToNull(svcPrcPMsg.basePrcDealAmt.getValue(), isAggregate));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, BigDecimal.ZERO);
                // mod start 2017/06/22 CSA Defect#19340
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, getBaseBillToCustCd(svcDtlPMsg, svcPrcPMsg));
                // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, getBaseBillToCustCdForFltAgg(inPrmPMsg, svcDtlPMsg, svcPrcPMsg, !isAggregate));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, getBaseBillToCustCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg));
                // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
                // mod end 2017/06/22 CSA Defect#19340
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgDay, getMtrBllgDay(inPrmPMsg, usgExistFlg));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.supprCrFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrRnwTotCnt, BigDecimal.ZERO);
                // START 2018/03/13 K.Kojima [QC#24263,DEL]
                // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.ctacPsnPk, getCtacPsnPk(inPrmPMsg.refCpoOrdNum.getValue()));
                // END 2018/03/13 K.Kojima [QC#24263,DEL]
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcPgmMdseCd, svcDtlPMsg.svcPgmMdseCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcMtrPkgPk, svcPrcPMsg.prcMtrPkgPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseDplyPerEndDay, getBaseDplyPerEndDay(inPrmPMsg));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrDplyPerEndDay, getMtrDplyPerEndDay(inPrmPMsg, usgExistFlg));
                // mod start 2017/06/30 CSA Defect#19700
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.shipToCustCd, getDefShipToCustCd(svcDtlPMsg, svcPrcPMsg, !isAggregate));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.shipToCustCd, getDefShipToCustCd(inPrmPMsg, !isAggregate));
                // mod end 2017/06/30 CSA Defect#19700
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addContrFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addAsryFlg, ZYPConstant.FLG_OFF_N);
                //ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcPrcCatgCd, SVC_PRC_CATG.MAIN_UNIT_BASE_CHARGE);
                // mod start 2017/06/27 CSA Defect#19560
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
                // mod end 2017/06/27 CSA Defect#19560
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcCatgCd, aggregateToNull(svcPrcPMsg.maintPrcCatgCd.getValue(), isAggregate));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dealPrcListPrcAmt, aggregateToNull(svcPrcPMsg.dealPrcListPrcAmt.getValue(), isAggregate));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.corpAdvPrcFlg, getCorpAdvPrcFlg(inPrmPMsg.slsDt.getValue(), svcPrcPMsg.prcMtrPkgPk.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.sellToCustCd, aggregateToNull(getSellToCustCd(svcDtlPMsg, svcPrcPMsg), isAggregate));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.fromPerMthNum, svcDtlPMsg.fromPerMthNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.toPerMthNum, svcDtlPMsg.toPerMthNum);

                // START 2019/01/18 [QC#29782, ADD]
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrCratTpCd, DS_CONTR_CRAT_TP.SHELL);
                // END 2019/01/18 [QC#29782, ADD]

                dsContrDtlTMsgNewList.add(dsContrDtlTMsg);
                continue;
            }

            modDsContrDtlTMsgForFltAgg(dsContrDtlTMsgModList, inPrmPMsg, svcDtlPMsg, svcPrcPMsg, isAggregate, dsContrDtlTMsg);
        }

        // START 2017/08/03 [QC#20378, ADD]
        // DS_CONTR_DTL(Fleet, Aggregate) -- Service Price List is empty.
        if (inPrmPMsg.svcPrcList.getValidCount() == 0) {
            for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
                svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);
                dsContrCatgCd = svcDtlPMsg.dsContrCatgCd.getValue();
                if (!RQST_TP_MOD.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                    continue;
                }

                if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                    dsContrDtlTpCd = DS_CONTR_DTL_TP.FLEET;
                } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                    dsContrDtlTpCd = DS_CONTR_DTL_TP.AGGREGATE;
                    // START 2018/01/04 M.Kidokoro [QC#23167, DEL]
                    // isAggregate = true;
                    // END 2018/01/04 M.Kidokoro [QC#23167, DEL]
                } else {
                    continue;
                }

                DS_CONTR_DTLTMsg dsContrDtlTMsg = null;
                BigDecimal dsContrPk = svcDtlPMsg.dsContrPk.getValue();
                BigDecimal dsContrDtlPk = getFltAggDsContrDtlPkByDB(dsContrPk);
                if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                    dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
                    dsContrDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                    dsContrDtlTMsg.dsContrDtlPk.setValue(dsContrDtlPk);
                    dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
                    if (dsContrDtlTMsg == null) {
                        setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
                        continue;
                    }
                } else {
                    continue;
                }

                BigDecimal shellLineNum = inPrmPMsg.svcDtlList.no(i).shellLineNum.getValue();
                setFltAggDsContrDtlPkToMap(inPrmPMsg.refCpoOrdNum.getValue(), shellLineNum, dsContrDtlPk);
                // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//                modDsContrDtlTMsgForFltAgg(dsContrDtlTMsgModList, inPrmPMsg, svcDtlPMsg, isAggregate, dsContrDtlTMsg);
                modDsContrDtlTMsgForFltAgg(dsContrDtlTMsgModList, inPrmPMsg, svcDtlPMsg, dsContrDtlTMsg);
                // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
            }
        }
        // END   2017/08/03 [QC#20378, ADD]

        List<DS_CONTR_DTL_RECTMsg> dsContrDtlRecTMsgList = new ArrayList<DS_CONTR_DTL_RECTMsg>();

        if (!dsContrDtlTMsgNewList.isEmpty()) {
            if (checkDsContrDtl(msgMap, dsContrDtlTMsgNewList)) {
                int result = S21FastTBLAccessor.insert(dsContrDtlTMsgNewList.toArray(new DS_CONTR_DTLTMsg[0]));
                if (result != dsContrDtlTMsgNewList.size()) {
                    setErrStatus(msgMap, NSZM1227E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }

        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgNewList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_NEW, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlTMsgModList.isEmpty()) {
            if (checkDsContrDtl(msgMap, dsContrDtlTMsgModList)) {
                addDsContrDtlPkListForUsgRemoval(dsContrDtlTMsgModList);
                int result = S21FastTBLAccessor.update(dsContrDtlTMsgModList.toArray(new DS_CONTR_DTLTMsg[0]));
                if (result != dsContrDtlTMsgModList.size()) {
                    setErrStatus(msgMap, NSZM1228E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }

        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgModList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_MOD, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlTMsgDelList.isEmpty()) {
            addDsContrDtlPkListForUsgRemoval(dsContrDtlTMsgDelList);
            int result = S21FastTBLAccessor.removeLogical(dsContrDtlTMsgDelList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1229E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgDelList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrDtlRecTMsgList.toArray(new DS_CONTR_DTL_RECTMsg[0]));
            if (result != dsContrDtlRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1264E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private void modDsContrDtlTMsgForFltAgg(List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList, NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, boolean isAggregate, DS_CONTR_DTLTMsg dsContrDtlTMsg) {

        if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk_AD)) {
            return;
        }
        if (isSameValueDsContrDtlTMsgForFltAgg(inPrmPMsg, svcDtlPMsg, svcPrcPMsg, isAggregate, dsContrDtlTMsg)) {
            this.dsContrDtlPkListForUsgRemoval.add(dsContrDtlTMsg.dsContrDtlPk.getValue());
            return;
        }

        boolean usgExistFlg = true;
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoOrdNum, inPrmPMsg.refCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsOrdTpCd, getDdsOrdTpCd(inPrmPMsg));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgCycleCd, svcDtlPMsg.baseBllgCycleCd);
        // mod start 2017/08/09 QC#18799
//        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, getBaseBllgTmgCd(svcDtlPMsg, svcPrcPMsg));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, getBaseBllgTmgCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg));
        // mod end 2017/08/09 QC#18799
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrBllgDay, getContrBllgDay(inPrmPMsg));
//        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, aggregateToNull(svcPrcPMsg.basePrcDealAmt.getValue(), isAggregate));
        // mod start 2017/06/22 CSA Defect#19340
//        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, getBaseBillToCustCd(svcDtlPMsg, svcPrcPMsg));
        // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, getBaseBillToCustCdForFltAgg(inPrmPMsg, svcDtlPMsg, svcPrcPMsg, !isAggregate));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, getBaseBillToCustCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg));
        // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
        // mod end 2017/06/22 CSA Defect#19340
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgDay, getMtrBllgDay(inPrmPMsg, usgExistFlg));
        // START 2018/03/13 K.Kojima [QC#24263,DEL]
        // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.ctacPsnPk, getCtacPsnPk(inPrmPMsg.refCpoOrdNum.getValue()));
        // END 2018/03/13 K.Kojima [QC#24263,DEL]
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcPgmMdseCd, svcDtlPMsg.svcPgmMdseCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcMtrPkgPk, svcPrcPMsg.prcMtrPkgPk);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseDplyPerEndDay, getBaseDplyPerEndDay(inPrmPMsg));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrDplyPerEndDay, getMtrDplyPerEndDay(inPrmPMsg, usgExistFlg));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcCatgCd, aggregateToNull(svcPrcPMsg.maintPrcCatgCd.getValue(), isAggregate));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dealPrcListPrcAmt, aggregateToNull(svcPrcPMsg.dealPrcListPrcAmt.getValue(), isAggregate));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.corpAdvPrcFlg, getCorpAdvPrcFlg(inPrmPMsg.slsDt.getValue(), svcPrcPMsg.prcMtrPkgPk.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.sellToCustCd, aggregateToNull(getSellToCustCd(svcDtlPMsg, svcPrcPMsg), isAggregate));
        // add start 2017/06/27 CSA Defect#19560
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
        // add end 2017/06/27 CSA Defect#19560
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.fromPerMthNum, svcDtlPMsg.fromPerMthNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.toPerMthNum, svcDtlPMsg.toPerMthNum);

        dsContrDtlTMsgModList.add(dsContrDtlTMsg);
    }

    private boolean isSameValueDsContrDtlTMsgForFltAgg(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, boolean isAggregate, DS_CONTR_DTLTMsg dsContrDtlTMsg) {

        boolean usgExistFlg = true;
        if (!S21StringUtil.isEquals(inPrmPMsg.refCpoOrdNum.getValue(), dsContrDtlTMsg.cpoOrdNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getDdsOrdTpCd(inPrmPMsg), dsContrDtlTMsg.dsOrdTpCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.baseBllgCycleCd.getValue(), dsContrDtlTMsg.baseBllgCycleCd.getValue())) {
            return false;
        }
        // mod start 2017/08/09 QC#18799
//        if (!S21StringUtil.isEquals(getBaseBllgTmgCd(svcDtlPMsg, svcPrcPMsg), dsContrDtlTMsg.baseBllgTmgCd.getValue())) {
        if (!S21StringUtil.isEquals(getBaseBllgTmgCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg), dsContrDtlTMsg.baseBllgTmgCd.getValue())) {
        // mod end 2017/08/09 QC#18799
            return false;
        }
        if (!S21StringUtil.isEquals(getContrBllgDay(inPrmPMsg), dsContrDtlTMsg.contrBllgDay.getValue())) {
            return false;
        }
//        if (!isEqualBigDecimal(aggregateToNull(svcPrcPMsg.basePrcDealAmt.getValue(), isAggregate), aggregateToNull(dsContrDtlTMsg.basePrcDealAmt.getValue(), isAggregate))) {
//            return false;
//        }
        // mod start 2017/06/22 CSA Defect#19340
//        if (!S21StringUtil.isEquals(getBaseBillToCustCd(svcDtlPMsg, svcPrcPMsg), dsContrDtlTMsg.baseBillToCustCd.getValue())) {
        // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//        if (!S21StringUtil.isEquals(getBaseBillToCustCdForFltAgg(inPrmPMsg, svcDtlPMsg, svcPrcPMsg, !isAggregate), dsContrDtlTMsg.baseBillToCustCd.getValue())) {
        if (!S21StringUtil.isEquals(getBaseBillToCustCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg), dsContrDtlTMsg.baseBillToCustCd.getValue())) {
        // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
        // mod end 2017/06/22 CSA Defect#19340
            return false;
        }
        if (!S21StringUtil.isEquals(getMtrBllgDay(inPrmPMsg, usgExistFlg), dsContrDtlTMsg.mtrBllgDay.getValue())) {
            return false;
        }
        // START 2018/03/13 K.Kojima [QC#24263,DEL]
        // if (!isEqualBigDecimal(getCtacPsnPk(inPrmPMsg.refCpoOrdNum.getValue()), dsContrDtlTMsg.ctacPsnPk.getValue())) {
        //     return false;
        // }
        // END 2018/03/13 K.Kojima [QC#24263,DEL]
        if (!S21StringUtil.isEquals(svcDtlPMsg.svcPgmMdseCd.getValue(), dsContrDtlTMsg.svcPgmMdseCd.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcPrcPMsg.prcMtrPkgPk.getValue(), dsContrDtlTMsg.prcMtrPkgPk.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getBaseDplyPerEndDay(inPrmPMsg), dsContrDtlTMsg.baseDplyPerEndDay.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getMtrDplyPerEndDay(inPrmPMsg, usgExistFlg), dsContrDtlTMsg.mtrDplyPerEndDay.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(aggregateToNull(svcPrcPMsg.maintPrcCatgCd.getValue(), isAggregate), dsContrDtlTMsg.prcCatgCd.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(aggregateToNull(svcPrcPMsg.dealPrcListPrcAmt.getValue(), isAggregate), dsContrDtlTMsg.dealPrcListPrcAmt.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getCorpAdvPrcFlg(inPrmPMsg.slsDt.getValue(), svcPrcPMsg.prcMtrPkgPk.getValue()), dsContrDtlTMsg.corpAdvPrcFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(aggregateToNull(getSellToCustCd(svcDtlPMsg, svcPrcPMsg), isAggregate), dsContrDtlTMsg.sellToCustCd.getValue())) {
            return false;
        }
        // add start 2017/06/27 CSA Defect#19560
        if (!S21StringUtil.isEquals(getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()), dsContrDtlTMsg.billWithEquipFlg.getValue())) {
            return false;
        }
        // add end 2017/06/27 CSA Defect#19560
        if (!isEqualBigDecimal(svcDtlPMsg.fromPerMthNum.getValue(), dsContrDtlTMsg.fromPerMthNum.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcDtlPMsg.toPerMthNum.getValue(), dsContrDtlTMsg.toPerMthNum.getValue())) {
            return false;
        }

        return true;
    }

    // START 2017/08/03 [QC#20378, ADD]
    // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//    private void modDsContrDtlTMsgForFltAgg(List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList, NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, boolean isAggregate, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
    private void modDsContrDtlTMsgForFltAgg(List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList, NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
    // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
        if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk_AD)) {
            return;
        }
        // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//        if (isSameValueDsContrDtlTMsgForFltAgg(inPrmPMsg, svcDtlPMsg, isAggregate, dsContrDtlTMsg)) {
        if (isSameValueDsContrDtlTMsgForFltAgg(inPrmPMsg, svcDtlPMsg, dsContrDtlTMsg)) {
        // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
            return;
        }

        // START 2017/09/04 [QC#20847, ADD]
        this.dsContrDtlPkListForUsgRemoval.add(dsContrDtlTMsg.dsContrDtlPk.getValue());
        // END   2017/09/04 [QC#20847, ADD]

        // START 2017/08/15 [QC#20378-2, MOD]
        boolean usgExistFlg = true;
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoOrdNum, inPrmPMsg.refCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsOrdTpCd, getDdsOrdTpCd(inPrmPMsg));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgCycleCd, svcDtlPMsg.baseBllgCycleCd);
        // mod start 2017/08/09 QC#18799
//      ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, getBaseBllgTmgCd(svcDtlPMsg, null));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, getBaseBllgTmgCd(inPrmPMsg, svcDtlPMsg, null));
        // mod end 2017/08/09 QC#18799
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrBllgDay, getContrBllgDay(inPrmPMsg));
        // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, getBaseBillToCustCdForFltAggForMod(inPrmPMsg, svcDtlPMsg, dsContrDtlTMsg, !isAggregate));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, getBaseBillToCustCdForMod(inPrmPMsg, svcDtlPMsg, null, dsContrDtlTMsg));
        // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgDay, getMtrBllgDay(inPrmPMsg, usgExistFlg));
        // START 2018/03/13 K.Kojima [QC#24263,DEL]
        // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.ctacPsnPk, getCtacPsnPk(inPrmPMsg.refCpoOrdNum.getValue()));
        // END 2018/03/13 K.Kojima [QC#24263,DEL]
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcPgmMdseCd, svcDtlPMsg.svcPgmMdseCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseDplyPerEndDay, getBaseDplyPerEndDay(inPrmPMsg));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrDplyPerEndDay, getMtrDplyPerEndDay(inPrmPMsg, usgExistFlg));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.fromPerMthNum, svcDtlPMsg.fromPerMthNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.toPerMthNum, svcDtlPMsg.toPerMthNum);
        // END   2017/08/15 [QC#20378-2, MOD]

        dsContrDtlTMsgModList.add(dsContrDtlTMsg);
    }

    // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//    private boolean isSameValueDsContrDtlTMsgForFltAgg(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, boolean isAggregate, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
    private boolean isSameValueDsContrDtlTMsgForFltAgg(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
    // END 2018/01/04 M.Kidokoro [QC#23167, MOD]

        // START 2017/08/15 [QC#20378-2, MOD]
        boolean usgExistFlg = true;
        if (!S21StringUtil.isEquals(inPrmPMsg.refCpoOrdNum.getValue(), dsContrDtlTMsg.cpoOrdNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getDdsOrdTpCd(inPrmPMsg), dsContrDtlTMsg.dsOrdTpCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.baseBllgCycleCd.getValue(), dsContrDtlTMsg.baseBllgCycleCd.getValue())) {
            return false;
        }
        // mod start 2017/08/09 QC#18799
//      if (!S21StringUtil.isEquals(getBaseBllgTmgCd(svcDtlPMsg, null), dsContrDtlTMsg.baseBllgTmgCd.getValue())) {
        if (!S21StringUtil.isEquals(getBaseBllgTmgCd(inPrmPMsg, svcDtlPMsg, null), dsContrDtlTMsg.baseBllgTmgCd.getValue())) {
        // mod end 2017/08/09 QC#18799
            return false;
        }
        if (!S21StringUtil.isEquals(getContrBllgDay(inPrmPMsg), dsContrDtlTMsg.contrBllgDay.getValue())) {
            return false;
        }
        // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//        if (!S21StringUtil.isEquals(getBaseBillToCustCdForFltAggForMod(inPrmPMsg, svcDtlPMsg, dsContrDtlTMsg, !isAggregate), dsContrDtlTMsg.baseBillToCustCd.getValue())) {
        if (!S21StringUtil.isEquals(getBaseBillToCustCdForMod(inPrmPMsg, svcDtlPMsg, null, dsContrDtlTMsg), dsContrDtlTMsg.baseBillToCustCd.getValue())) {
        // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
            return false;
        }
        if (!S21StringUtil.isEquals(getMtrBllgDay(inPrmPMsg, usgExistFlg), dsContrDtlTMsg.mtrBllgDay.getValue())) {
            return false;
        }
        // START 2018/03/13 K.Kojima [QC#24263,DEL]
        // if (!isEqualBigDecimal(getCtacPsnPk(inPrmPMsg.refCpoOrdNum.getValue()), dsContrDtlTMsg.ctacPsnPk.getValue())) {
        //     return false;
        // }
        // END 2018/03/13 K.Kojima [QC#24263,DEL]
        if (!S21StringUtil.isEquals(svcDtlPMsg.svcPgmMdseCd.getValue(), dsContrDtlTMsg.svcPgmMdseCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getBaseDplyPerEndDay(inPrmPMsg), dsContrDtlTMsg.baseDplyPerEndDay.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getMtrDplyPerEndDay(inPrmPMsg, usgExistFlg), dsContrDtlTMsg.mtrDplyPerEndDay.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()), dsContrDtlTMsg.billWithEquipFlg.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcDtlPMsg.fromPerMthNum.getValue(), dsContrDtlTMsg.fromPerMthNum.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcDtlPMsg.toPerMthNum.getValue(), dsContrDtlTMsg.toPerMthNum.getValue())) {
            return false;
        }
        // END   2017/08/15 [QC#20378-2, MOD]

        return true;
    }
    // END   2017/08/03 [QC#20378, ADD]

    // START 2017/08/15 [QC#20378-2, ADD]
    // START 2018/01/04 M.Kidokoro [QC#23167, DEL]
    // private String getBaseBillToCustCdForFltAggForMod(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, boolean isFleet) {
    //     if (isFleet) {
    //         getBillByFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue());
    //         if (ZYPConstant.FLG_ON_Y.equals(this.baseChrgToLeaseCmpyFlg)) {
    //             String leaseCmpyCd = getLeaseCmpyCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue());
    //             if (ZYPCommonFunc.hasValue(leaseCmpyCd)) {
    //                 return leaseCmpyCd;
    //             }
    //         }
    //     }
    // 
    //     return dsContrDtlTMsg.baseBillToCustCd.getValue();
    // }
    // END 2018/01/04 M.Kidokoro [QC#23167, DEL]
    // END   2017/08/15 [QC#20378-2, ADD]

    // mod start 2017/06/30 CSA Defect#19700
//    private String getDefShipToCustCd(NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, boolean isFleet) {
    private String getDefShipToCustCd(NSZC115001PMsg inPrmPMsg, boolean isFleet) {
        if (!isFleet) {
            return null;
        }

//        String billToCustCd = getBaseBillToCustCd(svcDtlPMsg, svcPrcPMsg);
//        String dsAcctNum = svcDtlPMsg.sellToCustCd.getValue();
//        // del start 2017/06/22 CSA Defect#19340
////        if (svcPrcPMsg != null && ZYPCommonFunc.hasValue(svcPrcPMsg.sellToCustCd)) {
////            dsAcctNum = svcPrcPMsg.sellToCustCd.getValue();
////        }
//        // del end 2017/06/22 CSA Defect#19340
//
//        NMZC610001PMsg pMsg = new NMZC610001PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
//        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, dsAcctNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, billToCustCd);
//        new NMZC610001().execute(pMsg, onBatchType);
//        if (S21ApiUtil.isXxMsgId(pMsg)) {
//            List<String> msgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
//            for (String msgId : msgIdList) {
//                if (msgId.endsWith("E")) {
//                    return null;
//                }
//            }
//        }
//        if (pMsg.DefaultBillShipList.getValidCount() > 0) {
//            return pMsg.DefaultBillShipList.no(0).shipToCustCd.getValue();
//        }
//        return null;

        CPOTMsg cpoTMsg = getCPOTMsg(inPrmPMsg);
        if (cpoTMsg == null) {
            return null;
        } else {
            return cpoTMsg.addShipToCustCd.getValue();
        }
    }
    // mod end 2017/06/30 CSA Defect#19700

    private void registDsContrDtlForMachine(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgNewList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgDelList = new ArrayList<DS_CONTR_DTLTMsg>();
        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        NSZC115001_svcPrcListPMsg svcPrcPMsg = null;
        boolean usgExistFlg = false;
        boolean isFleet = false;
        boolean isAccessory = false;

        // DS_CONTR_DTL(Machine, Add Accessory)
        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcConfigRefList.getValidCount(); i++) {
            svcConfigRefPMsg = inPrmPMsg.svcConfigRefList.no(i);
            BigDecimal shellLineNum = svcConfigRefPMsg.shellLineNum.getValue();
            String dsOrdPosnNum = svcConfigRefPMsg.dsOrdPosnNum.getValue();
            BigDecimal dsContrPk = svcConfigRefPMsg.dsContrPk.getValue();
            BigDecimal dsContrDtlPk = svcConfigRefPMsg.dsContrDtlPk.getValue();
            BigDecimal prntDsContrDtlPk = getFltAggDsContrDtlPkByMap(svcConfigRefPMsg.cpoOrdNum.getValue(), shellLineNum);
            isFleet = isFleet(inPrmPMsg, shellLineNum);
            isAccessory = false;
            String svcPrcCatgCd = SVC_PRC_CATG.MAIN_UNIT_BASE_CHARGE;

            CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
            cpoDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            cpoDtlTMsg.cpoOrdNum.setValue(svcConfigRefPMsg.cpoOrdNum.getValue());
            cpoDtlTMsg.cpoDtlLineNum.setValue(svcConfigRefPMsg.cpoDtlLineNum.getValue());
            cpoDtlTMsg.cpoDtlLineSubNum.setValue(svcConfigRefPMsg.cpoDtlLineSubNum.getValue());
            BigDecimal mdlId = getMdlIdByCpoDtl(cpoDtlTMsg);

            if (RQST_TP_DEL.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                dsContrPk = getDsContrPkFromDsContrDtl(svcConfigRefPMsg.dsContrDtlPk.getValue());
                ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.dsContrPk, dsContrPk);

                svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, shellLineNum);
                if (svcDtlPMsg != null && RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                    continue;
                }
            } else {
                dsContrPk = getDsContrPk(inPrmPMsg.svcDtlList, shellLineNum, dsContrPk);
                svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, shellLineNum);

                if (ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.addAsryFlg.getValue())) {
                    if (!isExistsMainMachine(svcConfigRefPMsg, inPrmPMsg)) {
                        isAccessory = true;
                        prntDsContrDtlPk = getPrntDsContrDtlPk(dsContrPk, svcConfigRefPMsg.svcConfigMstrPk.getValue());
                        // START 2023/11/08 R.Jin [QC#62108, DEL]
                        // START 2023/07/05 T.Kojima [QC#61472, ADD]
//                        if (prntDsContrDtlPk == null) {
//                            addMsgForSvcDtlList(msgMap, NSZM1406E, svcDtlPMsg);
//                            continue;
//                        }
                        // END 2023/06/26 T.Kojima [QC#61472, ADD]
                        // END 2023/11/08 R.Jin [QC#62108, DEL]
                        svcPrcCatgCd = SVC_PRC_CATG.ACC_UNIT_BASE_CHARGE;
                    }
                }

                if (isAccessory && inPrmPMsg.svcAddlBasePrcList.getValidCount() > 0) {
                    continue;
                }

                svcPrcPMsg = getSvcPrcListItem(inPrmPMsg.svcPrcList, shellLineNum, dsOrdPosnNum, mdlId, isFleet);
                if (RQST_TP_NEW.equals(svcConfigRefPMsg.xxRqstTpCd.getValue()) && svcPrcPMsg == null) {
                    svcPrcPMsg = new NSZC115001_svcPrcListPMsg();
                }
                usgExistFlg = isExistUsg(inPrmPMsg, svcConfigRefPMsg, mdlId, isFleet);
            }

            DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
            if (RQST_TP_NEW.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.dsContrPk, dsContrPk);
                ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.dsContrDtlPk, dsContrDtlPk);

                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrPk, dsContrPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlTpCd, getDsContrDtlTpCdForMachine(usgExistFlg, isAccessory));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.ORDER);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoOrdNum, svcConfigRefPMsg.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoDtlLineNum, svcConfigRefPMsg.cpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoDtlLineSubNum, svcConfigRefPMsg.cpoDtlLineSubNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsOrdTpCd, getDdsOrdTpCd(inPrmPMsg));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcConfigMstrPk, svcConfigRefPMsg.svcConfigMstrPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prntDsContrDtlPk, prntDsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgCycleCd, fleetToNull(svcDtlPMsg.baseBllgCycleCd.getValue(), isFleet));
                // mod start 2017/08/09 QC#18799
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, fleetToNull(getBaseBllgTmgCd(svcDtlPMsg, svcPrcPMsg), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, fleetToNull(getBaseBllgTmgCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg), isFleet));
                // mod end 2017/08/09 QC#18799
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrBllgDay, fleetToNull(getContrBllgDay(inPrmPMsg), isFleet));
                // START 2018/07/23 K.Kitachi [QC#26589, ADD]
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgCycleCd, fleetToNull(svcDtlPMsg.usgBllgCycleCd.getValue(), isFleet));
                // END 2018/07/23 K.Kitachi [QC#26589, ADD]
                if (!isAccessory) {
                    ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgTmgCd, fleetToNull(BLLG_TMG_TP.ARREARS, isFleet));
                }
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, fleetToZero(svcPrcPMsg.basePrcDealAmt.getValue(), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcFuncAmt, fleetToZero(NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), inPrmPMsg.slsDt.getValue(), svcPrcPMsg.basePrcDealAmt.getValue()), isFleet));
                // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, fleetToNull(getBaseBillToCustCd(svcDtlPMsg, svcPrcPMsg), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, fleetToNull(getBaseBillToCustCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg), isFleet));
                // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgDay, fleetToNull(getMtrBllgDay(inPrmPMsg, usgExistFlg), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.supprCrFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrReadMethCd, svcConfigRefPMsg.mtrReadMethCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrRnwTotCnt, BigDecimal.ZERO);
                // START 2018/03/13 K.Kojima [QC#24263,DEL]
                // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.ctacPsnPk, fleetToNull(getCtacPsnPk(svcConfigRefPMsg.cpoOrdNum.getValue()), isFleet));
                // END 2018/03/13 K.Kojima [QC#24263,DEL]
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcPgmMdseCd, fleetToNull(svcDtlPMsg.svcPgmMdseCd.getValue(), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcMtrPkgPk, fleetToNull(svcPrcPMsg.prcMtrPkgPk.getValue(), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseDplyPerEndDay, fleetToNull(getBaseDplyPerEndDay(inPrmPMsg), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrDplyPerEndDay, fleetToNull(getMtrDplyPerEndDay(inPrmPMsg, usgExistFlg), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.shellLineNum, shellLineNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addContrFlg, getAddContrFlg(svcDtlPMsg.dsContrPk_AD.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addAsryFlg, getFlgValue(svcDtlPMsg.addAsryFlg.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mdlId, mdlId);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcPrcCatgCd, svcPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcCatgCd, fleetToNull(svcPrcPMsg.maintPrcCatgCd.getValue(), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.crRebilCd, svcConfigRefPMsg.crRebilCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dealPrcListPrcAmt, fleetToNull(svcPrcPMsg.dealPrcListPrcAmt.getValue(), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.corpAdvPrcFlg, getCorpAdvPrcFlg(inPrmPMsg.slsDt.getValue(), svcPrcPMsg.prcMtrPkgPk.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.sellToCustCd, fleetToNull(getSellToCustCd(svcDtlPMsg, svcPrcPMsg), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlGrpNum, getDsContrDtlGrpNumByMap(svcConfigRefPMsg.cpoOrdNum.getValue(), shellLineNum, mdlId, isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.fromPerMthNum, svcDtlPMsg.fromPerMthNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.toPerMthNum, svcDtlPMsg.toPerMthNum);

                // START 2019/01/18 [QC#29782, ADD]
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrCratTpCd, DS_CONTR_CRAT_TP.SHELL);
                // END 2019/01/18 [QC#29782, ADD]

                dsContrDtlTMsgNewList.add(dsContrDtlTMsg);
                continue;
            }

            dsContrDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            dsContrDtlTMsg.dsContrDtlPk.setValue(svcConfigRefPMsg.dsContrDtlPk.getValue());
            dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
            if (dsContrDtlTMsg == null) {
                setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
            }

            if (RQST_TP_MOD.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                modDsContrDtlTMsgForMachine(dsContrDtlTMsgModList, inPrmPMsg, svcDtlPMsg, svcConfigRefPMsg, svcPrcPMsg, mdlId, isFleet, usgExistFlg, isAccessory, dsContrDtlTMsg);

            } else if (RQST_TP_DEL.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                dsContrDtlTMsgDelList.add(dsContrDtlTMsg);
            }
        }

        List<DS_CONTR_DTL_RECTMsg> dsContrDtlRecTMsgList = new ArrayList<DS_CONTR_DTL_RECTMsg>();

        if (!dsContrDtlTMsgNewList.isEmpty()) {
            if (checkDsContrDtl(msgMap, dsContrDtlTMsgNewList)) {
                int result = S21FastTBLAccessor.insert(dsContrDtlTMsgNewList.toArray(new DS_CONTR_DTLTMsg[0]));
                if (result != dsContrDtlTMsgNewList.size()) {
                    setErrStatus(msgMap, NSZM1227E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }

        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgNewList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_NEW, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlTMsgModList.isEmpty()) {
            if (checkDsContrDtl(msgMap, dsContrDtlTMsgModList)) {
                int result = S21FastTBLAccessor.update(dsContrDtlTMsgModList.toArray(new DS_CONTR_DTLTMsg[0]));
                if (result != dsContrDtlTMsgModList.size()) {
                    setErrStatus(msgMap, NSZM1228E); //mod u.kim 2018/03/06 QC#24056
                }
            }
            updateOtherDsContrDtl(msgMap, dsContrDtlTMsgModList);
        }

        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgModList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_MOD, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlTMsgDelList.isEmpty()) {
            addDsContrDtlPkListForUsgRemoval(dsContrDtlTMsgDelList);
            addDsContrDtlPkListForAsryAddlRemoval(dsContrDtlTMsgDelList);
            int result = S21FastTBLAccessor.removeLogical(dsContrDtlTMsgDelList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1229E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgDelList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrDtlRecTMsgList.toArray(new DS_CONTR_DTL_RECTMsg[0]));
            if (result != dsContrDtlRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1264E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private void modDsContrDtlTMsgForMachine(List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList, NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, BigDecimal mdlId, boolean isFleet, boolean usgExistFlg, boolean isAccessory, DS_CONTR_DTLTMsg dsContrDtlTMsg) {

        if (svcPrcPMsg != null) {
            this.dsContrDtlPkListForUsgRemoval.add(dsContrDtlTMsg.dsContrDtlPk.getValue());
        }

        if (isSameValueDsContrDtlTMsgForMachine(inPrmPMsg, svcDtlPMsg, svcConfigRefPMsg, svcPrcPMsg, mdlId, isFleet, usgExistFlg, isAccessory, dsContrDtlTMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlTpCd, getDsContrDtlTpCdForMachine(usgExistFlg, isAccessory));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoOrdNum, svcConfigRefPMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoDtlLineNum, svcConfigRefPMsg.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoDtlLineSubNum, svcConfigRefPMsg.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsOrdTpCd, getDdsOrdTpCd(inPrmPMsg));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcConfigMstrPk, svcConfigRefPMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgCycleCd, fleetToNull(svcDtlPMsg.baseBllgCycleCd.getValue(), isFleet));
        // mod start 2017/08/09 QC#18799
//        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, fleetToNull(getBaseBllgTmgCd(svcDtlPMsg, svcPrcPMsg), isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, fleetToNull(getBaseBllgTmgCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg), isFleet));
        // mod end 2017/08/09 QC#18799
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrBllgDay, fleetToNull(getContrBllgDay(inPrmPMsg), isFleet));
        // START 2018/07/23 K.Kitachi [QC#26589, ADD]
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgCycleCd, fleetToNull(svcDtlPMsg.usgBllgCycleCd.getValue(), isFleet));
        // END 2018/07/23 K.Kitachi [QC#26589, ADD]
        // mod start 2017/06/22 CSA Defect#19340
//        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, fleetToNull(getBaseBillToCustCd(svcDtlPMsg, svcPrcPMsg), isFleet));
        // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, fleetToNull(getBaseBillToCustCdForMod(svcDtlPMsg, svcPrcPMsg, dsContrDtlTMsg), isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, fleetToNull(getBaseBillToCustCdForMod(inPrmPMsg, svcDtlPMsg, svcPrcPMsg, dsContrDtlTMsg), isFleet));
        // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
        // mod end 2017/06/22 CSA Defect#19340
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgDay, fleetToNull(getMtrBllgDay(inPrmPMsg, usgExistFlg), isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrReadMethCd, svcConfigRefPMsg.mtrReadMethCd);
        // START 2018/03/13 K.Kojima [QC#24263,DEL]
        // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.ctacPsnPk, fleetToNull(getCtacPsnPk(svcConfigRefPMsg.cpoOrdNum.getValue()), isFleet));
        // END 2018/03/13 K.Kojima [QC#24263,DEL]
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcPgmMdseCd, fleetToNull(svcDtlPMsg.svcPgmMdseCd.getValue(), isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseDplyPerEndDay, fleetToNull(getBaseDplyPerEndDay(inPrmPMsg), isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrDplyPerEndDay, fleetToNull(getMtrDplyPerEndDay(inPrmPMsg, usgExistFlg), isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.shellLineNum, svcConfigRefPMsg.shellLineNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addContrFlg, getAddContrFlg(svcDtlPMsg.dsContrPk_AD.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addAsryFlg, getFlgValue(svcDtlPMsg.addAsryFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mdlId, mdlId);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.crRebilCd, svcConfigRefPMsg.crRebilCd);
        // mod start 2017/06/22 CSA Defect#19340
//        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.sellToCustCd, fleetToNull(getSellToCustCd(svcDtlPMsg, svcPrcPMsg), isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.sellToCustCd, fleetToNull(getSellToCustCdForMod(svcDtlPMsg, svcPrcPMsg, dsContrDtlTMsg), isFleet));
        // mod end 2017/06/22 CSA Defect#19340
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.fromPerMthNum, svcDtlPMsg.fromPerMthNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.toPerMthNum, svcDtlPMsg.toPerMthNum);

        if (svcPrcPMsg != null) {
            if (!isAccessory) {
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prntDsContrDtlPk, getFltAggDsContrDtlPkByMap(svcConfigRefPMsg.cpoOrdNum.getValue(), svcConfigRefPMsg.shellLineNum.getValue()));
            }
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, fleetToZero(svcPrcPMsg.basePrcDealAmt.getValue(), isFleet));
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcFuncAmt, fleetToZero(NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), inPrmPMsg.slsDt.getValue(), svcPrcPMsg.basePrcDealAmt.getValue()), isFleet));
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcMtrPkgPk, fleetToNull(svcPrcPMsg.prcMtrPkgPk.getValue(), isFleet));
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcCatgCd, fleetToNull(svcPrcPMsg.maintPrcCatgCd.getValue(), isFleet));
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dealPrcListPrcAmt, fleetToNull(svcPrcPMsg.dealPrcListPrcAmt.getValue(), isFleet));
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.corpAdvPrcFlg, getCorpAdvPrcFlg(inPrmPMsg.slsDt.getValue(), svcPrcPMsg.prcMtrPkgPk.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlGrpNum, getDsContrDtlGrpNumByMap(svcConfigRefPMsg.cpoOrdNum.getValue(), svcConfigRefPMsg.shellLineNum.getValue(), mdlId, isFleet));
        }

        dsContrDtlTMsgModList.add(dsContrDtlTMsg);
    }

    private boolean isSameValueDsContrDtlTMsgForMachine(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, BigDecimal mdlId, boolean isFleet, boolean usgExistFlg, boolean isAccessory, DS_CONTR_DTLTMsg dsContrDtlTMsg) {

        if (!S21StringUtil.isEquals(getDsContrDtlTpCdForMachine(usgExistFlg, isAccessory), dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcConfigRefPMsg.cpoOrdNum.getValue(), dsContrDtlTMsg.cpoOrdNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcConfigRefPMsg.cpoDtlLineNum.getValue(), dsContrDtlTMsg.cpoDtlLineNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcConfigRefPMsg.cpoDtlLineSubNum.getValue(), dsContrDtlTMsg.cpoDtlLineSubNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getDdsOrdTpCd(inPrmPMsg), dsContrDtlTMsg.dsOrdTpCd.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcConfigRefPMsg.svcConfigMstrPk.getValue(), dsContrDtlTMsg.svcConfigMstrPk.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(fleetToNull(svcDtlPMsg.baseBllgCycleCd.getValue(), isFleet), dsContrDtlTMsg.baseBllgCycleCd.getValue())) {
            return false;
        }
        // mod start 2017/08/09 QC#18799
//        if (!S21StringUtil.isEquals(fleetToNull(getBaseBllgTmgCd(svcDtlPMsg, svcPrcPMsg), isFleet), dsContrDtlTMsg.baseBllgTmgCd.getValue())) {
        if (!S21StringUtil.isEquals(fleetToNull(getBaseBllgTmgCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg), isFleet), dsContrDtlTMsg.baseBllgTmgCd.getValue())) {
        // mod end 2017/08/09 QC#18799
            return false;
        }
        if (!S21StringUtil.isEquals(fleetToNull(getContrBllgDay(inPrmPMsg), isFleet), dsContrDtlTMsg.contrBllgDay.getValue())) {
            return false;
        }
        // START 2018/07/23 K.Kitachi [QC#26589, ADD]
        if (!S21StringUtil.isEquals(fleetToNull(svcDtlPMsg.usgBllgCycleCd.getValue(), isFleet), dsContrDtlTMsg.mtrBllgCycleCd.getValue())) {
            return false;
        }
        // END 2018/07/23 K.Kitachi [QC#26589, ADD]
        // mod start 2017/06/22 CSA Defect#19340
//        if (!S21StringUtil.isEquals(fleetToNull(getBaseBillToCustCd(svcDtlPMsg, svcPrcPMsg), isFleet), dsContrDtlTMsg.baseBillToCustCd.getValue())) {
        // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//        if (!S21StringUtil.isEquals(fleetToNull(getBaseBillToCustCdForMod(svcDtlPMsg, svcPrcPMsg, dsContrDtlTMsg), isFleet), dsContrDtlTMsg.baseBillToCustCd.getValue())) {
        if (!S21StringUtil.isEquals(fleetToNull(getBaseBillToCustCdForMod(inPrmPMsg, svcDtlPMsg, svcPrcPMsg, dsContrDtlTMsg), isFleet), dsContrDtlTMsg.baseBillToCustCd.getValue())) {
        // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
        // mod end 2017/06/22 CSA Defect#19340
            return false;
        }
        if (!S21StringUtil.isEquals(fleetToNull(getMtrBllgDay(inPrmPMsg, usgExistFlg), isFleet), dsContrDtlTMsg.mtrBllgDay.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcConfigRefPMsg.mtrReadMethCd.getValue(), dsContrDtlTMsg.mtrReadMethCd.getValue())) {
            return false;
        }
        // START 2018/03/13 K.Kojima [QC#24263,DEL]
        // if (!isEqualBigDecimal(fleetToNull(getCtacPsnPk(svcConfigRefPMsg.cpoOrdNum.getValue()), isFleet), dsContrDtlTMsg.ctacPsnPk.getValue())) {
        //     return false;
        // }
        // END 2018/03/13 K.Kojima [QC#24263,DEL]
        if (!S21StringUtil.isEquals(fleetToNull(svcDtlPMsg.svcPgmMdseCd.getValue(), isFleet), dsContrDtlTMsg.svcPgmMdseCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(fleetToNull(getBaseDplyPerEndDay(inPrmPMsg), isFleet), dsContrDtlTMsg.baseDplyPerEndDay.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(fleetToNull(getMtrDplyPerEndDay(inPrmPMsg, usgExistFlg), isFleet), dsContrDtlTMsg.mtrDplyPerEndDay.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcConfigRefPMsg.shellLineNum.getValue(), dsContrDtlTMsg.shellLineNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getAddContrFlg(svcDtlPMsg.dsContrPk_AD.getValue()), dsContrDtlTMsg.addContrFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getFlgValue(svcDtlPMsg.addAsryFlg.getValue()), dsContrDtlTMsg.addAsryFlg.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(mdlId, dsContrDtlTMsg.mdlId.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()), dsContrDtlTMsg.billWithEquipFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcConfigRefPMsg.crRebilCd.getValue(), dsContrDtlTMsg.crRebilCd.getValue())) {
            return false;
        }
        // mod start 2017/06/22 CSA Defect#19340
//        if (!S21StringUtil.isEquals(fleetToNull(getSellToCustCd(svcDtlPMsg, svcPrcPMsg), isFleet), dsContrDtlTMsg.sellToCustCd.getValue())) {
        if (!S21StringUtil.isEquals(fleetToNull(getSellToCustCdForMod(svcDtlPMsg, svcPrcPMsg, dsContrDtlTMsg), isFleet), dsContrDtlTMsg.sellToCustCd.getValue())) {
        // mod end 2017/06/22 CSA Defect#19340
            return false;
        }
        if (!isEqualBigDecimal(svcDtlPMsg.fromPerMthNum.getValue(), dsContrDtlTMsg.fromPerMthNum.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcDtlPMsg.toPerMthNum.getValue(), dsContrDtlTMsg.toPerMthNum.getValue())) {
            return false;
        }

        if (svcPrcPMsg != null) {
            if (!isAccessory) {
                if (!isEqualBigDecimal(getFltAggDsContrDtlPkByMap(svcConfigRefPMsg.cpoOrdNum.getValue(), dsContrDtlTMsg.shellLineNum.getValue()), dsContrDtlTMsg.prntDsContrDtlPk.getValue())) {
                    return false;
                }
            }
            if (!isEqualBigDecimal(fleetToZero(svcPrcPMsg.basePrcDealAmt.getValue(), isFleet), dsContrDtlTMsg.basePrcDealAmt.getValue())) {
                return false;
            }
            if (!isEqualBigDecimal(fleetToNull(svcPrcPMsg.prcMtrPkgPk.getValue(), isFleet), dsContrDtlTMsg.prcMtrPkgPk.getValue())) {
                return false;
            }
            if (!S21StringUtil.isEquals(fleetToNull(svcPrcPMsg.maintPrcCatgCd.getValue(), isFleet), dsContrDtlTMsg.prcCatgCd.getValue())) {
                return false;
            }
            if (!isEqualBigDecimal(fleetToNull(svcPrcPMsg.dealPrcListPrcAmt.getValue(), isFleet), dsContrDtlTMsg.dealPrcListPrcAmt.getValue())) {
                return false;
            }
            if (!S21StringUtil.isEquals(getCorpAdvPrcFlg(inPrmPMsg.slsDt.getValue(), svcPrcPMsg.prcMtrPkgPk.getValue()), dsContrDtlTMsg.corpAdvPrcFlg.getValue())) {
                return false;
            }
            if (!isEqualBigDecimal(getDsContrDtlGrpNumByMap(svcConfigRefPMsg.cpoOrdNum.getValue(), svcConfigRefPMsg.shellLineNum.getValue(), mdlId, isFleet), dsContrDtlTMsg.dsContrDtlGrpNum.getValue())) {
                return false;
            }
        }

        return true;
    }

    private void updateOtherDsContrDtl(S21ApiMessageMap msgMap, List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList) {
        BigDecimal shellLineNumPrev = BigDecimal.ONE.negate();
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgModList) {
            BigDecimal shellLineNum = dsContrDtlTMsg.shellLineNum.getValue();

            if (ZYPCommonFunc.hasValue(shellLineNum) && shellLineNum.compareTo(shellLineNumPrev) == 0) {
                continue;
            }

            shellLineNumPrev = shellLineNum;
            String cpoOrdNum = dsContrDtlTMsg.cpoOrdNum.getValue();
            BigDecimal fromPerMthNum = dsContrDtlTMsg.fromPerMthNum.getValue();
            BigDecimal toPerMthNum = dsContrDtlTMsg.toPerMthNum.getValue();
            String billWithEquipFlg = dsContrDtlTMsg.billWithEquipFlg.getValue();

            // DS_CONTR_DTL
            // 2018/05/07 QC#22482 Mod Start
//            List<BigDecimal> dsContrDtlPkList = getNeedToUpdateMthDsContrDtl(cpoOrdNum, shellLineNum, fromPerMthNum, toPerMthNum, billWithEquipFlg);
//            for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
//                DS_CONTR_DTLTMsg dsContrDtlTMsgMod = new DS_CONTR_DTLTMsg();
//                dsContrDtlTMsgMod.glblCmpyCd.setValue(this.glblCmpyCd);
//                dsContrDtlTMsgMod.dsContrDtlPk.setValue(dsContrDtlPk);
//                dsContrDtlTMsgMod = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsgMod);
//                if (dsContrDtlTMsgMod == null) {
//                    setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
//                    continue;
//                }
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgMod.fromPerMthNum, fromPerMthNum);
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgMod.toPerMthNum, toPerMthNum);
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgMod.billWithEquipFlg, billWithEquipFlg);
//                S21FastTBLAccessor.update(dsContrDtlTMsgMod);
//            }
            if (ZYPCommonFunc.hasValue(fromPerMthNum) && ZYPCommonFunc.hasValue(toPerMthNum)) {
                List<BigDecimal> dsContrDtlPkList = getNeedToUpdateMthDsContrDtl(cpoOrdNum, shellLineNum, fromPerMthNum, toPerMthNum, billWithEquipFlg);
                for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
                    DS_CONTR_DTLTMsg dsContrDtlTMsgMod = new DS_CONTR_DTLTMsg();
                    dsContrDtlTMsgMod.glblCmpyCd.setValue(this.glblCmpyCd);
                    dsContrDtlTMsgMod.dsContrDtlPk.setValue(dsContrDtlPk);
                    dsContrDtlTMsgMod = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsgMod);
                    if (dsContrDtlTMsgMod == null) {
                        setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
                        continue;
                    }
                    ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgMod.fromPerMthNum, fromPerMthNum);
                    ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgMod.toPerMthNum, toPerMthNum);
                    ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgMod.billWithEquipFlg, billWithEquipFlg);
                    S21FastTBLAccessor.update(dsContrDtlTMsgMod);
                }
            }
            // 2018/05/07 QC#22482 Mod End

            // DS_CONTR_ADDL_CHRG
            List<BigDecimal> dsContrAddlChrgPkList = getNeedToUpdateBillWithEquipFlgDsContrAddlChrg(cpoOrdNum, shellLineNum, billWithEquipFlg);
            for (BigDecimal dsContrAddlChrgPk : dsContrAddlChrgPkList) {
                DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg = new DS_CONTR_ADDL_CHRGTMsg();
                dsContrAddlChrgTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                dsContrAddlChrgTMsg.dsContrAddlChrgPk.setValue(dsContrAddlChrgPk);
                dsContrAddlChrgTMsg = (DS_CONTR_ADDL_CHRGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrAddlChrgTMsg);
                if (dsContrAddlChrgTMsg == null) {
                    setErrStatus(msgMap, NSZM1240E); //mod u.kim 2018/03/06 QC#24056
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.billWithEquipFlg, billWithEquipFlg);
                S21FastTBLAccessor.update(dsContrAddlChrgTMsg);
            }
        }
    }

    private List<BigDecimal> getNeedToUpdateMthDsContrDtl(String cpoOrdNum, BigDecimal shellLineNum, BigDecimal fromPerMthNum, BigDecimal toPerMthNum, String billWithEquipFlg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("shellLineNum", shellLineNum);
        param.put("fromPerMthNum", fromPerMthNum);
        param.put("toPerMthNum", toPerMthNum);
        param.put("billWithEquipFlg", billWithEquipFlg);
        return this.ssmBatchClient.queryObjectList("getNeedToUpdateMthDsContrDtl", param);
    }

    private List<BigDecimal> getNeedToUpdateBillWithEquipFlgDsContrAddlChrg(String cpoOrdNum, BigDecimal shellLineNum, String billWithEquipFlg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("shellLineNum", shellLineNum);
        param.put("billWithEquipFlg", billWithEquipFlg);
        return this.ssmBatchClient.queryObjectList("getNeedToUpdateBillWithEquipFlgDsContrAddlChrg", param);
    }

    private void registDsContrDtlForAsry(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        checkRegistAddlBasePrcList(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgNewList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgDelList = new ArrayList<DS_CONTR_DTLTMsg>();
        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;
        NSZC115001_svcPrcListPMsg svcPrcPMsg = null;
        NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg = null;
        boolean isFleet = false;

        // DS_CONTR_DTL(Accessory)
        for (int i = 0; i < inPrmPMsg.svcAddlBasePrcList.getValidCount(); i++) {
            svcAddlBasePrcPMsg = inPrmPMsg.svcAddlBasePrcList.no(i);

            if (!RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue()) && !ADDL_CHRG_CATG.ACCESSORY.equals(svcAddlBasePrcPMsg.addlChrgCatgCd.getValue())) {
                continue;
            } else if (RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue()) && !ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.dsContrDtlPk)) {
                continue;
            }

            BigDecimal shellLineNum = svcAddlBasePrcPMsg.shellLineNum.getValue();
            BigDecimal dsContrPk = svcAddlBasePrcPMsg.dsContrPk.getValue();
            BigDecimal dsContrDtlPk = svcAddlBasePrcPMsg.dsContrDtlPk.getValue();
            isFleet = isFleet(inPrmPMsg, shellLineNum);

            BigDecimal mdlId = null;
            BigDecimal svcConfigMstrPk = null;
            BigDecimal prntDsContrDtlPk = null;

            if (RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                dsContrPk = getDsContrPkFromDsContrDtl(dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(svcAddlBasePrcPMsg.dsContrPk, dsContrPk);
            } else {
                CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                cpoDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                cpoDtlTMsg.cpoOrdNum.setValue(svcAddlBasePrcPMsg.cpoOrdNum.getValue());
                cpoDtlTMsg.cpoDtlLineNum.setValue(svcAddlBasePrcPMsg.cpoDtlLineNum.getValue());
                cpoDtlTMsg.cpoDtlLineSubNum.setValue(svcAddlBasePrcPMsg.cpoDtlLineSubNum.getValue());
                mdlId = getMdlIdByCpoDtl(cpoDtlTMsg);
                svcConfigMstrPk = getSvcConfigMstrPkByCpoDtl(cpoDtlTMsg);

                dsContrPk = getDsContrPk(inPrmPMsg.svcDtlList, shellLineNum, dsContrPk);
                svcConfigRefPMsg = getSvcConfigRefListItem(inPrmPMsg.svcConfigRefList, shellLineNum, svcConfigMstrPk);
                svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, shellLineNum);
                svcPrcPMsg = getSvcPrcListItem(inPrmPMsg.svcPrcList, shellLineNum, svcConfigRefPMsg.dsOrdPosnNum.getValue(), mdlId, isFleet);
                if (svcPrcPMsg == null) {
                    svcPrcPMsg = new NSZC115001_svcPrcListPMsg();
                }

                if (ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.addAsryFlg.getValue()) && !isExistsMainMachine(svcConfigRefPMsg, inPrmPMsg)) {
                    // START 2017/10/17 [QC#21851, DEL]
                    // dsContrDtlPk = svcConfigRefPMsg.dsContrDtlPk.getValue();
                    // END   2017/10/17 [QC#21851, DEL]
                    prntDsContrDtlPk = getPrntDsContrDtlPk(dsContrPk, svcConfigMstrPk);
                    // START 2023/11/08 R.Jin [QC#62108, DEL]
                    // START 2023/07/05 T.Kojima [QC#61472, ADD]
//                    if (prntDsContrDtlPk == null) {
//                        addMsgForSvcDtlList(msgMap, NSZM1406E, svcDtlPMsg);
//                        continue;
//                    }
                    // END 2023/07/05 T.Kojima [QC#61472, ADD]
                    // END 2023/11/08 R.Jin [QC#62108, DEL]
                } else {
                    prntDsContrDtlPk = svcConfigRefPMsg.dsContrDtlPk.getValue();
                }
            }

            DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
            if (RQST_TP_NEW.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue()) && !ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                dsContrDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_DTL_SQ);
                ZYPEZDItemValueSetter.setValue(svcAddlBasePrcPMsg.dsContrPk, dsContrPk);
                ZYPEZDItemValueSetter.setValue(svcAddlBasePrcPMsg.dsContrDtlPk, dsContrDtlPk);

                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrPk, dsContrPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlTpCd, DS_CONTR_DTL_TP.ACCESSORIES);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.ORDER);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoOrdNum, svcAddlBasePrcPMsg.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoDtlLineNum, svcAddlBasePrcPMsg.cpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoDtlLineSubNum, svcAddlBasePrcPMsg.cpoDtlLineSubNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsOrdTpCd, getDdsOrdTpCd(inPrmPMsg));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcConfigMstrPk, svcConfigMstrPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prntDsContrDtlPk, prntDsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgCycleCd, fleetToNull(svcDtlPMsg.baseBllgCycleCd.getValue(), isFleet));
                // mod start 2017/08/09 QC#18799
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, fleetToNull(getBaseBllgTmgCd(svcDtlPMsg, svcPrcPMsg), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, fleetToNull(getBaseBllgTmgCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg), isFleet));
                // mod end 2017/08/09 QC#18799
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrBllgDay, fleetToNull(getContrBllgDay(inPrmPMsg), isFleet));
                //ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgTmgCd, fleetToNull(BLLG_TMG_TP.ARREARS, isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, fleetToZero(svcAddlBasePrcPMsg.addlBasePrcDealAmt.getValue(), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcFuncAmt, fleetToZero(NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), inPrmPMsg.slsDt.getValue(), svcAddlBasePrcPMsg.addlBasePrcDealAmt.getValue()), isFleet));
                // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, fleetToNull(getBaseBillToCustCd(svcDtlPMsg, svcPrcPMsg), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, fleetToNull(getBaseBillToCustCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg), isFleet));
                // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
                //ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgDay, fleetToNull(getMtrBllgDay(inPrmPMsg, usgExistFlg), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.supprCrFlg, ZYPConstant.FLG_OFF_N);
                //ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrReadMethCd, svcConfigRefPMsg.mtrReadMethCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrRnwTotCnt, BigDecimal.ZERO);
                // START 2018/03/13 K.Kojima [QC#24263,DEL]
                // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.ctacPsnPk, fleetToNull(getCtacPsnPk(svcAddlBasePrcPMsg.cpoOrdNum.getValue()), isFleet));
                // END 2018/03/13 K.Kojima [QC#24263,DEL]
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
                // START 2018/08/22 [QC#22821, MOD]
                String svcPgmMdseCd = svcDtlPMsg.svcPgmMdseCd.getValue();
                String svcLineBizCd = getSvcLineBizCd(inPrmPMsg);
                CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                cpoDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                cpoDtlTMsg.cpoOrdNum.setValue(svcAddlBasePrcPMsg.cpoOrdNum.getValue());
                cpoDtlTMsg.cpoDtlLineNum.setValue(svcAddlBasePrcPMsg.cpoDtlLineNum.getValue());
                cpoDtlTMsg.cpoDtlLineSubNum.setValue(svcAddlBasePrcPMsg.cpoDtlLineSubNum.getValue());
                String rntlFlg = ZYPConstant.FLG_OFF_N;
                if (isRental(inPrmPMsg)) {
                    rntlFlg = ZYPConstant.FLG_ON_Y;
                }
                // START 2019/01/18 [QC#29782, MOD]
                // svcPgmMdseCd = getAccSvcPgmMdseCd(svcPgmMdseCd, this.glblCmpyCd, svcLineBizCd, cpoDtlTMsg, rntlFlg);
                svcPgmMdseCd = getAccSvcPgmMdseCd(svcPgmMdseCd, this.glblCmpyCd, svcLineBizCd, cpoDtlTMsg, rntlFlg, getPrntSvcPgmMdseCd(prntDsContrDtlPk));
                // END 2019/01/18 [QC#29782, MOD]
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcPgmMdseCd, fleetToNull(svcPgmMdseCd, isFleet));
                // END   2018/08/22 [QC#22821, MOD]
                //ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcMtrPkgPk, fleetToNull(svcPrcPMsg.prcMtrPkgPk, isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseDplyPerEndDay, fleetToNull(getBaseDplyPerEndDay(inPrmPMsg), isFleet));
                //ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrDplyPerEndDay, fleetToNull(getMtrDplyPerEndDay(inPrmPMsg, usgExistFlg), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.shellLineNum, shellLineNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addContrFlg, getAddContrFlg(svcDtlPMsg.dsContrPk_AD.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addAsryFlg, getFlgValue(svcDtlPMsg.addAsryFlg.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mdlId, mdlId);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcPrcCatgCd, SVC_PRC_CATG.ACC_UNIT_BASE_CHARGE);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcListEquipConfigNum, svcAddlBasePrcPMsg.prcListEquipConfigNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcCatgCd, fleetToNull(svcAddlBasePrcPMsg.addlBasePrcCatgCd.getValue(), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.crRebilCd, svcAddlBasePrcPMsg.crRebilCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dealPrcListPrcAmt, fleetToNull(svcAddlBasePrcPMsg.dealPrcListPrcAmt.getValue(), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.corpAdvPrcFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.sellToCustCd, fleetToNull(getSellToCustCd(svcDtlPMsg, svcPrcPMsg), isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlGrpNum, getDsContrDtlGrpNumByMap(svcAddlBasePrcPMsg.cpoOrdNum.getValue(), shellLineNum, mdlId, isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.fromPerMthNum, svcDtlPMsg.fromPerMthNum);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.toPerMthNum, svcDtlPMsg.toPerMthNum);

                // START 2019/01/18 [QC#29782, ADD]
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrCratTpCd, DS_CONTR_CRAT_TP.SHELL);
                // END 2019/01/18 [QC#29782, ADD]

                dsContrDtlTMsgNewList.add(dsContrDtlTMsg);
                continue;
            }

            dsContrDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            dsContrDtlTMsg.dsContrDtlPk.setValue(dsContrDtlPk);
            dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
            if (dsContrDtlTMsg == null) {
                setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
                continue;
            }

            if (RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                dsContrDtlTMsgDelList.add(dsContrDtlTMsg);
            } else {
                modDsContrDtlTMsgForAsry(dsContrDtlTMsgModList, inPrmPMsg, svcDtlPMsg, svcConfigRefPMsg, svcPrcPMsg, svcAddlBasePrcPMsg, mdlId, svcConfigMstrPk, prntDsContrDtlPk, isFleet, dsContrDtlTMsg);
            }
        }

        List<DS_CONTR_DTL_RECTMsg> dsContrDtlRecTMsgList = new ArrayList<DS_CONTR_DTL_RECTMsg>();

        if (!dsContrDtlTMsgNewList.isEmpty()) {
            if (checkDsContrDtl(msgMap, dsContrDtlTMsgNewList)) {
                int result = S21FastTBLAccessor.insert(dsContrDtlTMsgNewList.toArray(new DS_CONTR_DTLTMsg[0]));
                if (result != dsContrDtlTMsgNewList.size()) {
                    setErrStatus(msgMap, NSZM1227E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }

        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgNewList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_NEW, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlTMsgModList.isEmpty()) {
            if (checkDsContrDtl(msgMap, dsContrDtlTMsgModList)) {
                int result = S21FastTBLAccessor.update(dsContrDtlTMsgModList.toArray(new DS_CONTR_DTLTMsg[0]));
                if (result != dsContrDtlTMsgModList.size()) {
                    setErrStatus(msgMap, NSZM1228E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }

        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgModList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_MOD, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrDtlTMsgDelList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1229E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgDelList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrDtlRecTMsgList.toArray(new DS_CONTR_DTL_RECTMsg[0]));
            if (result != dsContrDtlRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1264E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private void modDsContrDtlTMsgForAsry(List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList, NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg, BigDecimal mdlId, BigDecimal svcConfigMstrPk, BigDecimal prntDsContrDtlPk, boolean isFleet, DS_CONTR_DTLTMsg dsContrDtlTMsg) {

        if (isSameValueDsContrDtlTMsgForAsry(inPrmPMsg, svcDtlPMsg, svcConfigRefPMsg, svcPrcPMsg, svcAddlBasePrcPMsg, mdlId, svcConfigMstrPk, prntDsContrDtlPk, isFleet, dsContrDtlTMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoOrdNum, svcAddlBasePrcPMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoDtlLineNum, svcAddlBasePrcPMsg.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoDtlLineSubNum, svcAddlBasePrcPMsg.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsOrdTpCd, getDdsOrdTpCd(inPrmPMsg));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcConfigMstrPk, svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prntDsContrDtlPk, prntDsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgCycleCd, fleetToNull(svcDtlPMsg.baseBllgCycleCd.getValue(), isFleet));
        // mod start 2017/08/09 QC#18799
//        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, fleetToNull(getBaseBllgTmgCd(svcDtlPMsg, svcPrcPMsg), isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, fleetToNull(getBaseBllgTmgCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg), isFleet));
        // mod end 2017/08/09 QC#18799
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrBllgDay, fleetToNull(getContrBllgDay(inPrmPMsg), isFleet));
        //ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgTmgCd, fleetToNull(BLLG_TMG_TP.ARREARS, isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, fleetToZero(svcAddlBasePrcPMsg.addlBasePrcDealAmt.getValue(), isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcFuncAmt, fleetToZero(NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), inPrmPMsg.slsDt.getValue(), svcAddlBasePrcPMsg.addlBasePrcDealAmt.getValue()), isFleet));
        // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, fleetToNull(getBaseBillToCustCd(svcDtlPMsg, svcPrcPMsg), isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, fleetToNull(getBaseBillToCustCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg), isFleet));
        // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
        //ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgDay, fleetToNull(getMtrBllgDay(inPrmPMsg, usgExistFlg), isFleet));
        //ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrReadMethCd, svcConfigRefPMsg.mtrReadMethCd);
        // START 2018/03/13 K.Kojima [QC#24263,DEL]
        // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.ctacPsnPk, fleetToNull(getCtacPsnPk(svcAddlBasePrcPMsg.cpoOrdNum.getValue()), isFleet));
        // END 2018/03/13 K.Kojima [QC#24263,DEL]
        // START 2018/08/22 [QC#22821, MOD]
        String svcPgmMdseCd = svcDtlPMsg.svcPgmMdseCd.getValue();
        String svcLineBizCd = getSvcLineBizCd(inPrmPMsg);
        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        cpoDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        cpoDtlTMsg.cpoOrdNum.setValue(svcAddlBasePrcPMsg.cpoOrdNum.getValue());
        cpoDtlTMsg.cpoDtlLineNum.setValue(svcAddlBasePrcPMsg.cpoDtlLineNum.getValue());
        cpoDtlTMsg.cpoDtlLineSubNum.setValue(svcAddlBasePrcPMsg.cpoDtlLineSubNum.getValue());
        String rntlFlg = ZYPConstant.FLG_OFF_N;
        if (isRental(inPrmPMsg)) {
            rntlFlg = ZYPConstant.FLG_ON_Y;
        }
        // START 2019/01/18 [QC#29782, MOD]
        // svcPgmMdseCd = getAccSvcPgmMdseCd(svcPgmMdseCd, this.glblCmpyCd, svcLineBizCd, cpoDtlTMsg, rntlFlg);
        svcPgmMdseCd = getAccSvcPgmMdseCd(svcPgmMdseCd, this.glblCmpyCd, svcLineBizCd, cpoDtlTMsg, rntlFlg, getPrntSvcPgmMdseCd(prntDsContrDtlPk));
        // END 2019/01/18 [QC#29782, MOD]
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcPgmMdseCd, fleetToNull(svcPgmMdseCd, isFleet));
        // END   2018/08/22 [QC#22821, MOD]
        //ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcMtrPkgPk, fleetToNull(svcPrcPMsg.prcMtrPkgPk, isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseDplyPerEndDay, fleetToNull(getBaseDplyPerEndDay(inPrmPMsg), isFleet));
        //ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrDplyPerEndDay, fleetToNull(getMtrDplyPerEndDay(inPrmPMsg, usgExistFlg), isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.shellLineNum, svcAddlBasePrcPMsg.shellLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addContrFlg, getAddContrFlg(svcDtlPMsg.dsContrPk_AD.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addAsryFlg, getFlgValue(svcDtlPMsg.addAsryFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mdlId, mdlId);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcListEquipConfigNum, svcAddlBasePrcPMsg.prcListEquipConfigNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcCatgCd, fleetToNull(svcAddlBasePrcPMsg.addlBasePrcCatgCd.getValue(), isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.crRebilCd, svcAddlBasePrcPMsg.crRebilCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dealPrcListPrcAmt, fleetToNull(svcAddlBasePrcPMsg.dealPrcListPrcAmt.getValue(), isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.sellToCustCd, fleetToNull(getSellToCustCd(svcDtlPMsg, svcPrcPMsg), isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlGrpNum, getDsContrDtlGrpNumByMap(svcAddlBasePrcPMsg.cpoOrdNum.getValue(), svcAddlBasePrcPMsg.shellLineNum.getValue(), mdlId, isFleet));
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.fromPerMthNum, svcDtlPMsg.fromPerMthNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.toPerMthNum, svcDtlPMsg.toPerMthNum);

        dsContrDtlTMsgModList.add(dsContrDtlTMsg);
    }

    private boolean isSameValueDsContrDtlTMsgForAsry(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg, BigDecimal mdlId, BigDecimal svcConfigMstrPk, BigDecimal prntDsContrDtlPk, boolean isFleet, DS_CONTR_DTLTMsg dsContrDtlTMsg) {

        if (!S21StringUtil.isEquals(svcAddlBasePrcPMsg.cpoOrdNum.getValue(), dsContrDtlTMsg.cpoOrdNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcAddlBasePrcPMsg.cpoDtlLineNum.getValue(), dsContrDtlTMsg.cpoDtlLineNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcAddlBasePrcPMsg.cpoDtlLineSubNum.getValue(), dsContrDtlTMsg.cpoDtlLineSubNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getDdsOrdTpCd(inPrmPMsg), dsContrDtlTMsg.dsOrdTpCd.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcConfigMstrPk, dsContrDtlTMsg.svcConfigMstrPk.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(prntDsContrDtlPk, dsContrDtlTMsg.prntDsContrDtlPk.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(fleetToNull(svcDtlPMsg.baseBllgCycleCd.getValue(), isFleet), dsContrDtlTMsg.baseBllgCycleCd.getValue())) {
            return false;
        }
        // mod start 2017/08/09 QC#18799
//        if (!S21StringUtil.isEquals(fleetToNull(getBaseBllgTmgCd(svcDtlPMsg, svcPrcPMsg), isFleet), dsContrDtlTMsg.baseBllgTmgCd.getValue())) {
        if (!S21StringUtil.isEquals(fleetToNull(getBaseBllgTmgCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg), isFleet), dsContrDtlTMsg.baseBllgTmgCd.getValue())) {
        // mod end 2017/08/09 QC#18799
            return false;
        }
        if (!S21StringUtil.isEquals(fleetToNull(getContrBllgDay(inPrmPMsg), isFleet), dsContrDtlTMsg.contrBllgDay.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(fleetToZero(svcAddlBasePrcPMsg.addlBasePrcDealAmt.getValue(), isFleet), dsContrDtlTMsg.basePrcDealAmt.getValue())) {
            return false;
        }
        // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//        if (!S21StringUtil.isEquals(fleetToNull(getBaseBillToCustCd(svcDtlPMsg, svcPrcPMsg), isFleet), dsContrDtlTMsg.baseBillToCustCd.getValue())) {
        if (!S21StringUtil.isEquals(fleetToNull(getBaseBillToCustCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg), isFleet), dsContrDtlTMsg.baseBillToCustCd.getValue())) {
        // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
            return false;
        }
        // START 2018/03/13 K.Kojima [QC#24263,DEL]
        // if (!isEqualBigDecimal(fleetToNull(getCtacPsnPk(svcAddlBasePrcPMsg.cpoOrdNum.getValue()), isFleet), dsContrDtlTMsg.ctacPsnPk.getValue())) {
        //     return false;
        // }
        // END 2018/03/13 K.Kojima [QC#24263,DEL]
        if (!S21StringUtil.isEquals(fleetToNull(svcDtlPMsg.svcPgmMdseCd.getValue(), isFleet), dsContrDtlTMsg.svcPgmMdseCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(fleetToNull(getBaseDplyPerEndDay(inPrmPMsg), isFleet), dsContrDtlTMsg.baseDplyPerEndDay.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcAddlBasePrcPMsg.shellLineNum.getValue(), dsContrDtlTMsg.shellLineNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getAddContrFlg(svcDtlPMsg.dsContrPk_AD.getValue()), dsContrDtlTMsg.addContrFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getFlgValue(svcDtlPMsg.addAsryFlg.getValue()), dsContrDtlTMsg.addAsryFlg.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(mdlId, dsContrDtlTMsg.mdlId.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()), dsContrDtlTMsg.billWithEquipFlg.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcAddlBasePrcPMsg.prcListEquipConfigNum.getValue(), dsContrDtlTMsg.prcListEquipConfigNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(fleetToNull(svcAddlBasePrcPMsg.addlBasePrcCatgCd.getValue(), isFleet), dsContrDtlTMsg.prcCatgCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcAddlBasePrcPMsg.crRebilCd.getValue(), dsContrDtlTMsg.crRebilCd.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(fleetToNull(svcAddlBasePrcPMsg.dealPrcListPrcAmt.getValue(), isFleet), dsContrDtlTMsg.dealPrcListPrcAmt.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(fleetToNull(getSellToCustCd(svcDtlPMsg, svcPrcPMsg), isFleet), dsContrDtlTMsg.sellToCustCd.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(getDsContrDtlGrpNumByMap(svcAddlBasePrcPMsg.cpoOrdNum.getValue(), svcAddlBasePrcPMsg.shellLineNum.getValue(), mdlId, isFleet), dsContrDtlTMsg.dsContrDtlGrpNum.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcDtlPMsg.fromPerMthNum.getValue(), dsContrDtlTMsg.fromPerMthNum.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcDtlPMsg.toPerMthNum.getValue(), dsContrDtlTMsg.toPerMthNum.getValue())) {
            return false;
        }

        return true;
    }

    private void updateDsContrDtlForAggPrc(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList = new ArrayList<DS_CONTR_DTLTMsg>();
        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        String dsContrCatgCd = null;

        // DS_CONTR_DTL(Aggregate)
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);
            dsContrCatgCd = svcDtlPMsg.dsContrCatgCd.getValue();

            if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                continue;
            }

            BigDecimal dsContrPk = svcDtlPMsg.dsContrPk.getValue();
            BigDecimal dsContrDtlPk = getFltAggDsContrDtlPkByDB(dsContrPk);
            if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                continue;
            }

            DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
            dsContrDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            dsContrDtlTMsg.dsContrDtlPk.setValue(dsContrDtlPk);
            dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
            if (dsContrDtlTMsg == null) {
                setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
                continue;
            }

            BigDecimal basePrcDealAmt = getBasePrcDealAmtForAgg(dsContrPk);
            if (!isEqualBigDecimal(basePrcDealAmt, dsContrDtlTMsg.basePrcDealAmt.getValue())) {
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, basePrcDealAmt);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcFuncAmt, NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), inPrmPMsg.slsDt.getValue(), dsContrDtlTMsg.basePrcDealAmt.getValue()));
                dsContrDtlTMsgModList.add(dsContrDtlTMsg);
            }
        }

        if (!dsContrDtlTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.update(dsContrDtlTMsgModList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1228E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        List<DS_CONTR_DTL_RECTMsg> dsContrDtlRecTMsgList = new ArrayList<DS_CONTR_DTL_RECTMsg>();
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgModList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_MOD, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrDtlRecTMsgList.toArray(new DS_CONTR_DTL_RECTMsg[0]));
            if (result != dsContrDtlRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1264E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private BigDecimal getBasePrcDealAmtForAgg(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlTpCd_Aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        param.put("dsContrDtlTpCd_Shell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        param.put("dsContrDtlStsCd_Terminated", DS_CONTR_DTL_STS.TERMINATED);
        param.put("dsContrDtlStsCd_Expired", DS_CONTR_DTL_STS.EXPIRED);
        param.put("dsContrDtlStsCd_Cancelled", DS_CONTR_DTL_STS.CANCELLED);
        return (BigDecimal) this.ssmBatchClient.queryObject("getBasePrcDealAmtForAgg", param);
    }

    private void removeDsContrDtlForShell(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgDelList = new ArrayList<DS_CONTR_DTLTMsg>();

        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);
            if (!RQST_TP_MOD.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            // mod start 2017/06/22 CSA Defect#19330
            List<BigDecimal> dsContrDtlPkList = getDsContrDtlPkForRemoveShell(inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.shellLineNum.getValue(), svcDtlPMsg.dsContrCatgCd.getValue(), svcDtlPMsg.addAsryFlg.getValue());
            // mod end 2017/06/22 CSA Defect#19330
            for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
                DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
                dsContrDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                dsContrDtlTMsg.dsContrDtlPk.setValue(dsContrDtlPk);
                dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
                if (dsContrDtlTMsg == null) {
                    setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
                    continue;
                }
                dsContrDtlTMsgDelList.add(dsContrDtlTMsg);
            }
        }

        if (!dsContrDtlTMsgDelList.isEmpty()) {
            addDsContrDtlPkListForUsgRemoval(dsContrDtlTMsgDelList);
            int result = S21FastTBLAccessor.removeLogical(dsContrDtlTMsgDelList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1229E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // DS_CONTR_DTL_REC
        List<DS_CONTR_DTL_RECTMsg> dsContrDtlRecTMsgList = new ArrayList<DS_CONTR_DTL_RECTMsg>();
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgDelList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrDtlRecTMsgList.toArray(new DS_CONTR_DTL_RECTMsg[0]));
            if (result != dsContrDtlRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1264E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    // mod start 2017/06/22 CSA Defect#19330
    private List<BigDecimal> getDsContrDtlPkForRemoveShell(String cpoOrdNum, BigDecimal shellLineNum, String dsContrCatgCd, String addAsryFlg) {
    // mod end 2017/06/22 CSA Defect#19330
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("shellLineNum", shellLineNum);
        param.put("dsContrDtlTpCd_Shell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            param.put("fleetFlg", ZYPConstant.FLG_ON_Y);
        } else {
            param.put("fleetFlg", ZYPConstant.FLG_OFF_N);
        }
        // add start 2017/06/22 CSA Defect#19330
        if (ZYPCommonFunc.hasValue(addAsryFlg)) {
            param.put("addAsryFlg", addAsryFlg);
        } else {
            param.put("addAsryFlg", ZYPConstant.FLG_OFF_N);
        }
        // add end 2017/06/22 CSA Defect#19330

        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrDtlPkForRemoveShell", param);
    }

    private void removeDsContrDtlForFltAgg(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgDelList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgDelList = new ArrayList<DS_CONTR_CR_CARD_POTMsg>();
        // add start 2020/03/24 QC#54318
        List<DS_CONTR_RNW_ESCLTMsg> dsContrRnwEsclTMsgDelList = new ArrayList<DS_CONTR_RNW_ESCLTMsg>();
        // add end 2020/03/24 QC#54318

        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);

            String dsContrCatgCd = svcDtlPMsg.dsContrCatgCd.getValue();
            if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && !DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                continue;
            }

            if (RQST_TP_NEW.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            // DS_CONTR_DTL(Fleet, Aggregate)
            BigDecimal dsContrDtlPk = getDsContrDtlPkForRemoveFltAgg(svcDtlPMsg.dsContrPk.getValue());
            if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                continue;
            }

            DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
            dsContrDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            dsContrDtlTMsg.dsContrDtlPk.setValue(dsContrDtlPk);
            dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
            if (dsContrDtlTMsg == null) {
                setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
                continue;
            }
            dsContrDtlTMsgDelList.add(dsContrDtlTMsg);

            // DS_CONTR_CR_CARD_PO(Fleet, Aggregate)
            DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
            dsContrCrCardPoTMsg.setSQLID("003");
            dsContrCrCardPoTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            dsContrCrCardPoTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
            DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsg);
            for (int j = 0; j < dsContrCrCardPoTMsgArray.getValidCount(); j++) {
                dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArray.no(j));
            }

            // add start 2020/03/24 QC#54318
            // DS_CONTR_RNW_ESCL(Fleet, Aggregate)
            DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg = new DS_CONTR_RNW_ESCLTMsg();
            dsContrRnwEsclTMsg.setSQLID("003");
            dsContrRnwEsclTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            dsContrRnwEsclTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
            DS_CONTR_RNW_ESCLTMsgArray dsContrRnwEsclTMsgArray = (DS_CONTR_RNW_ESCLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrRnwEsclTMsg);
            for (int j = 0; j < dsContrRnwEsclTMsgArray.getValidCount(); j++) {
                dsContrRnwEsclTMsgDelList.add(dsContrRnwEsclTMsgArray.no(j));
            }
            // add end 2020/03/24 QC#54318
        }

        // remove DS_CONTR_DTL
        if (!dsContrDtlTMsgDelList.isEmpty()) {
            addDsContrDtlPkListForUsgRemoval(dsContrDtlTMsgDelList);
            int result = S21FastTBLAccessor.removeLogical(dsContrDtlTMsgDelList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1229E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // DS_CONTR_DTL_REC
        List<DS_CONTR_DTL_RECTMsg> dsContrDtlRecTMsgList = new ArrayList<DS_CONTR_DTL_RECTMsg>();
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgDelList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrDtlRecTMsgList.toArray(new DS_CONTR_DTL_RECTMsg[0]));
            if (result != dsContrDtlRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1264E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // remove DS_CONTR_CR_CARD_PO
        if (!dsContrCrCardPoTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrCrCardPoTMsgDelList.toArray(new DS_CONTR_CR_CARD_POTMsg[0]));
            if (result != dsContrCrCardPoTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1245E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // add start 2020/03/24 QC#54318
        // remove DS_CONTR_RNW_ESCL
        if (!dsContrRnwEsclTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrRnwEsclTMsgDelList.toArray(new DS_CONTR_RNW_ESCLTMsg[0]));
            if (result != dsContrRnwEsclTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1245E);
            }
        }
        // add end 2020/03/24 QC#54318
    }

    private BigDecimal getDsContrDtlPkForRemoveFltAgg(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlTpCd_Fleet", DS_CONTR_DTL_TP.FLEET);
        param.put("dsContrDtlTpCd_Aggregate", DS_CONTR_DTL_TP.AGGREGATE);

        return (BigDecimal) this.ssmBatchClient.queryObject("getDsContrDtlPkForRemoveFltAgg", param);
    }

    private void removeUsg(S21ApiMessageMap msgMap) {
        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        List<CONTR_XS_COPYTMsg> contrXsCopyTMsgList = new ArrayList<CONTR_XS_COPYTMsg>();
        List<CONTR_PHYS_BLLG_MTR_RELNTMsg> contrPhysBllgMtrRelnTMsgList = new ArrayList<CONTR_PHYS_BLLG_MTR_RELNTMsg>();

        for (BigDecimal dsContrDtlPk : this.dsContrDtlPkListForUsgRemoval) {
            // DS_CONTR_BLLG_MTR
            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = getDsContrBllgMtrList(dsContrDtlPk);
            if (dsContrBllgMtrTMsgArray != null) {
                for (int i = 0; i < dsContrBllgMtrTMsgArray.getValidCount(); i++) {
                    dsContrBllgMtrTMsgList.add(dsContrBllgMtrTMsgArray.no(i));

                    // CONTR_XS_COPY
                    CONTR_XS_COPYTMsgArray contrXsCopyTMsgArray = getContrXsCopy(dsContrBllgMtrTMsgArray.no(i).dsContrBllgMtrPk.getValue());
                    if (contrXsCopyTMsgArray != null) {
                        for (int j = 0; j < contrXsCopyTMsgArray.getValidCount(); j++) {
                            contrXsCopyTMsgList.add(contrXsCopyTMsgArray.no(j));
                        }
                    }
                }
            }

            // CONTR_PHYS_BLLG_MTR_RELN
            CONTR_PHYS_BLLG_MTR_RELNTMsgArray contrPhysBllgMtrRelnTMsgArray = getContrPhysBllgMtrRelnByDsContrDtlPk(dsContrDtlPk);
            if (contrPhysBllgMtrRelnTMsgArray != null) {
                for (int i = 0; i < contrPhysBllgMtrRelnTMsgArray.getValidCount(); i++) {
                    contrPhysBllgMtrRelnTMsgList.add(contrPhysBllgMtrRelnTMsgArray.no(i));
                }
            }
        }

        // remove DS_CONTR_BLLG_MTR
        if (!dsContrBllgMtrTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrBllgMtrTMsgList.toArray(new DS_CONTR_BLLG_MTRTMsg[0]));
            if (result != dsContrBllgMtrTMsgList.size()) {
                setErrStatus(msgMap, NSZM1232E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // remove CONTR_XS_COPY
        if (!contrXsCopyTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(contrXsCopyTMsgList.toArray(new CONTR_XS_COPYTMsg[0]));
            if (result != contrXsCopyTMsgList.size()) {
                setErrStatus(msgMap, NSZM1236E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // remove CONTR_PHYS_BLLG_MTR_RELN
        if (!contrPhysBllgMtrRelnTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(contrPhysBllgMtrRelnTMsgList.toArray(new CONTR_PHYS_BLLG_MTR_RELNTMsg[0]));
            if (result != contrPhysBllgMtrRelnTMsgList.size()) {
                setErrStatus(msgMap, NSZM1234E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private void registUsg(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgNewList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        List<CONTR_XS_COPYTMsg> contrXsCopyTMsgNewList = new ArrayList<CONTR_XS_COPYTMsg>();
        List<CONTR_PHYS_BLLG_MTR_RELNTMsg> contrPhysBllgMtrRelnTMsgNewList = new ArrayList<CONTR_PHYS_BLLG_MTR_RELNTMsg>();
        String cpoOrdNum = inPrmPMsg.refCpoOrdNum.getValue();
        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg = null;
        NSZC115001_svcPrcListPMsg svcPrcPMsg = null;
        BigDecimal dsContrBllgMtrPk = null;
        Map<String, BigDecimal> dsContrBllgMtrPkMap = new HashMap<String, BigDecimal>();

        for (int i = 0; i < inPrmPMsg.svcUsgPrcList.getValidCount(); i++) {
            svcUsgPrcPMsg = inPrmPMsg.svcUsgPrcList.no(i);

            if (RQST_TP_DEL.equals(svcUsgPrcPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            BigDecimal shellLineNum = svcUsgPrcPMsg.shellLineNum.getValue();
            BigDecimal mdlId = svcUsgPrcPMsg.mdlId.getValue();
            String dsOrdPosnNum = svcUsgPrcPMsg.dsOrdPosnNum.getValue();
            BigDecimal dsContrPk = svcUsgPrcPMsg.dsContrPk.getValue();
            if (!ZYPCommonFunc.hasValue(dsContrPk)) {
                dsContrPk = getDsContrPk(inPrmPMsg.svcDtlList, shellLineNum, dsContrPk);
                ZYPEZDItemValueSetter.setValue(svcUsgPrcPMsg.dsContrPk, dsContrPk);
            }
            BigDecimal dsContrDtlPk = svcUsgPrcPMsg.dsContrDtlPk.getValue();
            svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, shellLineNum);

            // add start 2017/06/14 CSA Defect#18821
            if (svcDtlPMsg == null || !ZYPCommonFunc.hasValue(svcDtlPMsg.usgBllgCycleCd)) {
                continue;
            }
            // add end 2017/06/14 CSA Defect#18821

            boolean isFleet = isFleet(inPrmPMsg, shellLineNum);
            boolean isShellLine = false;
            String dsContrCatgCd = getDsContrCatgCd(inPrmPMsg, shellLineNum);
            String bllgMtrLbCd = getBllgMtrLbCd(svcUsgPrcPMsg.bllgMtrLbCd.getValue(), dsContrCatgCd);
            boolean isAddContr = false;
            if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk_AD)) {
                isAddContr = true;
            }

            if (ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
                // Machine
                if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                    dsContrDtlPk = getDsContrDtlPkByConfig(inPrmPMsg.svcConfigRefList, shellLineNum, dsOrdPosnNum);
                }
                svcPrcPMsg = getSvcPrcListItem(inPrmPMsg.svcPrcList, shellLineNum, dsOrdPosnNum, mdlId, isFleet);
            } else {
                // Shell Line
                isShellLine = true;
                if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                    dsContrDtlPk = getDsContrDtlPkForShellByMap(cpoOrdNum, shellLineNum, mdlId);
                }
                svcPrcPMsg = getSvcPrcPMsgByDsContrDtlPkForShell(inPrmPMsg, dsContrDtlPk);
            }

            if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.regMtrLbCd) && ZYPCommonFunc.hasValue(svcUsgPrcPMsg.usgMdseCd)) {
                // DS_CONTR_BLLG_MTR
                DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = new DS_CONTR_BLLG_MTRTMsg();
                dsContrBllgMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_MTR_SQ);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrDtlPk, dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMtrLbCd, bllgMtrLbCd);
                // mod start 2017/06/22 CSA Defect#19340
//                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMtrBillToCustCd, getBllgMtrBillToCustCd(svcDtlPMsg, svcPrcPMsg, svcUsgPrcPMsg));
                // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
//                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMtrBillToCustCd, getBllgMtrBillToCustCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg, svcUsgPrcPMsg, isFleet));
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMtrBillToCustCd, getBllgMtrBillToCustCd(inPrmPMsg, svcDtlPMsg, svcPrcPMsg, svcUsgPrcPMsg));
                // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
                // mod end 2017/06/22 CSA Defect#19340
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMtrBllgCycleCd, svcDtlPMsg.usgBllgCycleCd);
                // mod start 2018/08/22 QC#23920
                if (isAddContr && DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.xsChrgTpCd, getXsChrgTpCdForAgg(svcDtlPMsg.dsContrPk_AD.getValue(), bllgMtrLbCd));
                } else {
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.xsChrgTpCd, getXsChrgTpCd(svcPrcPMsg.prcTierSvcOfferCd.getValue()));
                }
                // mod end 2018/08/22 QC#23920
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.ORDER);
                // START 2018/03/13 K.Kojima [QC#24263,DEL]
                // ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.ctacPsnPk, getCtacPsnPk(inPrmPMsg.refCpoOrdNum.getValue()));
                // END 2018/03/13 K.Kojima [QC#24263,DEL]
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.intgMdseCd, svcUsgPrcPMsg.usgMdseCd);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.prcListBandCd, svcUsgPrcPMsg.prcListBandCd);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.prcBookMdseCd, svcUsgPrcPMsg.prcBookMdseCd);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.sellToCustCd, svcUsgPrcPMsg.sellToCustCd);
                // QC#25030
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgFreeCopyCnt, svcUsgPrcPMsg.bllgFreeCopyCnt);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMinCnt, svcUsgPrcPMsg.bllgMinCnt);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMinAmtRate, svcUsgPrcPMsg.bllgMinAmtRate);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgRollOverRatio, svcUsgPrcPMsg.bllgRollOverRatio);

                dsContrBllgMtrTMsgNewList.add(dsContrBllgMtrTMsg);

                String strDsContrDtlPk = ZYPCommonFunc.leftPad(String.valueOf(dsContrDtlPk), LENGTH_OF_PK, STR_ZERO);
                String key = createCacheKey(strDsContrDtlPk, bllgMtrLbCd);
                dsContrBllgMtrPkMap.put(key, dsContrBllgMtrPk);

                // copy Fleet Line (Shell Line -> Fleet Line)
                copyDsContrBllgMtrForFlt(dsContrCatgCd, dsContrBllgMtrTMsgNewList, dsContrBllgMtrPkMap, inPrmPMsg.refCpoOrdNum.getValue(), shellLineNum, dsContrBllgMtrTMsg, isAddContr);

                // copy Fleet Line (Shell Line -> Machine Line)
                copyDsContrBllgMtrForFltMachine(dsContrCatgCd, dsContrBllgMtrTMsgNewList, dsContrBllgMtrPkMap, inPrmPMsg, svcPrcPMsg.prcMtrPkgPk.getValue(), svcUsgPrcPMsg.bllgMtrLbCd.getValue(), dsContrBllgMtrTMsg);
            }

            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.regMtrLbCd)) {
                String strDsContrDtlPk = ZYPCommonFunc.leftPad(String.valueOf(dsContrDtlPk), LENGTH_OF_PK, STR_ZERO);
                String key = createCacheKey(strDsContrDtlPk, bllgMtrLbCd);
                dsContrBllgMtrPk = dsContrBllgMtrPkMap.get(key);

                // CONTR_PHYS_BLLG_MTR_RELN
                CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrRelnTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
                BigDecimal contrPhysBllgMtrRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_PHYS_BLLG_MTR_RELN_SQ);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.contrPhysBllgMtrRelnPk, contrPhysBllgMtrRelnPk);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.dsContrDtlPk, dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.contrMtrMultRate, svcUsgPrcPMsg.contrMtrMultRate);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.bllgMtrLbCd, bllgMtrLbCd);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.bllblFlg, ZYPConstant.FLG_ON_Y);
                // mod start 2017/08/29 CSA Defect#20831
//                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.bllgMtrLvlNum, getBllgMtrLvlNum(svcUsgPrcPMsg.bllgMtrLbCd.getValue()));
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.bllgMtrLvlNum, getBllgMtrLvlNum(svcPrcPMsg, svcUsgPrcPMsg.regMtrLbCd.getValue(), bllgMtrLbCd, mdlId, inPrmPMsg.slsDt.getValue()));
                // mod end 2017/08/29 CSA Defect#20831
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.regMtrLbCd, svcUsgPrcPMsg.regMtrLbCd);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.actvFlg, svcUsgPrcPMsg.actvFlg);
                contrPhysBllgMtrRelnTMsgNewList.add(contrPhysBllgMtrRelnTMsg);

                // copy Fleet Line (Shell Line -> Fleet Line)
                copyContrPhysBllgMtrRelnForFlt(dsContrCatgCd, contrPhysBllgMtrRelnTMsgNewList, dsContrBllgMtrPkMap, inPrmPMsg.refCpoOrdNum.getValue(), shellLineNum, contrPhysBllgMtrRelnTMsg);

                // copy Fleet Line (Shell Line -> Machine Line)
                copyContrPhysBllgMtrRelnForFltMachine(dsContrCatgCd, contrPhysBllgMtrRelnTMsgNewList, dsContrBllgMtrPkMap, inPrmPMsg, svcPrcPMsg.prcMtrPkgPk.getValue(), svcUsgPrcPMsg.bllgMtrLbCd.getValue(), contrPhysBllgMtrRelnTMsg);
            }

            if ((ZYPCommonFunc.hasValue(svcUsgPrcPMsg.copyInclPrcQty) || ZYPCommonFunc.hasValue(svcUsgPrcPMsg.minCopyVolCnt)) && (!isFleet || (isFleet && isShellLine))) {
                String strDsContrDtlPk = ZYPCommonFunc.leftPad(String.valueOf(dsContrDtlPk), LENGTH_OF_PK, STR_ZERO);
                String key = createCacheKey(strDsContrDtlPk, bllgMtrLbCd);
                dsContrBllgMtrPk = dsContrBllgMtrPkMap.get(key);

                // CONTR_XS_COPY
                CONTR_XS_COPYTMsg contrXsCopyTMsg = new CONTR_XS_COPYTMsg();
                BigDecimal contrXsCopyPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_XS_COPY_SQ);
                ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.contrXsCopyPk, contrXsCopyPk);
                ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
                ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.xsMtrCopyQty, getXsMtrCopyQty(svcUsgPrcPMsg.copyInclPrcQty.getValue(), svcUsgPrcPMsg.minCopyVolCnt.getValue()));
                ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.xsMtrAmtRate, svcUsgPrcPMsg.xsMtrAmtRate);
                ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.xsMtrFirstFlg, getXsMtrFirstFlg(svcUsgPrcPMsg.prcSvcTierTpCd.getValue()));
                ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.prcSvcTierTpCd, svcUsgPrcPMsg.prcSvcTierTpCd);
                ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.maxCopyVolCnt, svcUsgPrcPMsg.maxCopyVolCnt);
                contrXsCopyTMsgNewList.add(contrXsCopyTMsg);

                // copy Fleet Line (Shell Line -> Fleet Line)
                copyContrXsCopycontrXsCopyForFlt(dsContrCatgCd, contrXsCopyTMsgNewList, dsContrBllgMtrPkMap, inPrmPMsg.refCpoOrdNum.getValue(), shellLineNum, bllgMtrLbCd, contrXsCopyTMsg);
            }
        }

        if (!dsContrBllgMtrTMsgNewList.isEmpty()) {
            if (checkDsContrBllgMtr(msgMap, dsContrBllgMtrTMsgNewList)) {
                int result = S21FastTBLAccessor.insert(dsContrBllgMtrTMsgNewList.toArray(new DS_CONTR_BLLG_MTRTMsg[0]));
                if (result != dsContrBllgMtrTMsgNewList.size()) {
                    setErrStatus(msgMap, NSZM1231E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }

        if (!contrPhysBllgMtrRelnTMsgNewList.isEmpty()) {
            if (checkContrPhysBllgMtrReln(msgMap, contrPhysBllgMtrRelnTMsgNewList)) {
                int result = S21FastTBLAccessor.insert(contrPhysBllgMtrRelnTMsgNewList.toArray(new CONTR_PHYS_BLLG_MTR_RELNTMsg[0]));
                if (result != contrPhysBllgMtrRelnTMsgNewList.size()) {
                    setErrStatus(msgMap, NSZM1233E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }

        if (!contrXsCopyTMsgNewList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(contrXsCopyTMsgNewList.toArray(new CONTR_XS_COPYTMsg[0]));
            if (result != contrXsCopyTMsgNewList.size()) {
                setErrStatus(msgMap, NSZM1235E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private NSZC115001_svcPrcListPMsg getSvcPrcPMsgByDsContrDtlPkForShell(NSZC115001PMsg inPrmPMsg, BigDecimal dsContrDtlPk) {
        if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            return null;
        }
        for (int i = 0; i < inPrmPMsg.svcPrcList.getValidCount(); i++) {
            NSZC115001_svcPrcListPMsg svcPrcPMsg = inPrmPMsg.svcPrcList.no(i);
            if (!ZYPCommonFunc.hasValue(svcPrcPMsg.dsContrDtlPk)) {
                continue;
            }
            if (dsContrDtlPk.compareTo(svcPrcPMsg.dsContrDtlPk.getValue()) == 0) {
                return svcPrcPMsg;
            }
        }
        return null;
    }

    private void registUsgForAgg(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgNewList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        List<CONTR_XS_COPYTMsg> contrXsCopyTMsgNewList = new ArrayList<CONTR_XS_COPYTMsg>();
        List<CONTR_PHYS_BLLG_MTR_RELNTMsg> contrPhysBllgMtrRelnTMsgNewList = new ArrayList<CONTR_PHYS_BLLG_MTR_RELNTMsg>();
        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        Map<String, BigDecimal> dsContrBllgMtrPkMap = new HashMap<String, BigDecimal>();

        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);

            String dsContrCatgCd = svcDtlPMsg.dsContrCatgCd.getValue();
            if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                continue;
            }

            if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            // add start 2018/08/22 QC#23920
            if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk_AD)) {
                continue;
            }
            // add end 2018/08/22 QC#23920

            // START 2017/09/04 [QC#20847, ADD]
            int svcUsgCnt = 0;
            BigDecimal dsContrDtlPkFltAgg = getFltAggDsContrDtlPkByMap(inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.shellLineNum.getValue());
            if (!ZYPCommonFunc.hasValue(dsContrDtlPkFltAgg)) {
                continue;
            }
            for (int j = 0; j < inPrmPMsg.svcUsgPrcList.getValidCount(); j++) {
                NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg = inPrmPMsg.svcUsgPrcList.no(j);
                BigDecimal shellLineNum = svcUsgPrcPMsg.shellLineNum.getValue();
                if (RQST_TP_DEL.equals(svcUsgPrcPMsg.xxRqstTpCd.getValue())) {
                    continue;
                }
                if (shellLineNum.compareTo(svcDtlPMsg.shellLineNum.getValue()) != 0) {
                    continue;
                }
                svcUsgCnt++;
            }

            if (!this.dsContrDtlPkListForUsgRemoval.contains(dsContrDtlPkFltAgg) && svcUsgCnt == 0) {
                continue;
            }
            // END   2017/09/04 [QC#20847, ADD]

            BigDecimal dsContrPk = svcDtlPMsg.dsContrPk.getValue();
            List<Map<String, Object>> dsContrBllgMtrList = getDsContrBllgMtrListForAggLine(dsContrPk);
            for (Map<String, Object> dsContrBllgMtrMap : dsContrBllgMtrList) {
                // DS_CONTR_BLLG_MTR
                DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = new DS_CONTR_BLLG_MTRTMsg();
                BigDecimal dsContrBllgMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_MTR_SQ);
                BigDecimal dsContrDtlPk = getFltAggDsContrDtlPkByMap(inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.shellLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrDtlPk, dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMtrLbCd, (String) dsContrBllgMtrMap.get("BLLG_MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMtrBillToCustCd, (String) dsContrBllgMtrMap.get("BLLG_MTR_BILL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMtrBllgCycleCd, (String) dsContrBllgMtrMap.get("BLLG_MTR_BLLG_CYCLE_CD"));
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.xsChrgTpCd, (String) dsContrBllgMtrMap.get("XS_CHRG_TP_CD"));
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.ORDER);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.ctacPsnPk, (BigDecimal) dsContrBllgMtrMap.get("CTAC_PSN_PK"));
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.intgMdseCd, (String) dsContrBllgMtrMap.get("INTG_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
                //ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.prcListBandCd, svcUsgPrcPMsg.prcListBandCd);
                //ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.prcBookMdseCd, svcUsgPrcPMsg.prcBookMdseCd);
                //ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.sellToCustCd, svcUsgPrcPMsg.sellToCustCd);

                // QC#25030
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMinCnt, (BigDecimal) dsContrBllgMtrMap.get("BLLG_MIN_CNT"));
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMinAmtRate, (BigDecimal) dsContrBllgMtrMap.get("BLLG_MIN_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgFreeCopyCnt, (BigDecimal) dsContrBllgMtrMap.get("BLLG_FREE_COPY_CNT"));
                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgRollOverRatio, (BigDecimal) dsContrBllgMtrMap.get("BLLG_ROLL_OVER_RATIO"));

                dsContrBllgMtrTMsgNewList.add(dsContrBllgMtrTMsg);

                String strDsContrDtlPk = ZYPCommonFunc.leftPad(String.valueOf(dsContrDtlPk), LENGTH_OF_PK, STR_ZERO);
                String key = createCacheKey(strDsContrDtlPk, dsContrBllgMtrTMsg.bllgMtrLbCd.getValue());
                dsContrBllgMtrPkMap.put(key, dsContrBllgMtrPk);
            }

            List<Map<String, Object>> contrPhysBllgMtrRelnList = getContrPhysBllgMtrRelnListForAggLine(dsContrPk);
            for (Map<String, Object> contrPhysBllgMtrRelnMap : contrPhysBllgMtrRelnList) {
                BigDecimal dsContrDtlPk = getFltAggDsContrDtlPkByMap(inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.shellLineNum.getValue());
                String strDsContrDtlPk = ZYPCommonFunc.leftPad(String.valueOf(dsContrDtlPk), LENGTH_OF_PK, STR_ZERO);
                String key = createCacheKey(strDsContrDtlPk, (String) contrPhysBllgMtrRelnMap.get("BLLG_MTR_LB_CD"));
                BigDecimal dsContrBllgMtrPk = dsContrBllgMtrPkMap.get(key);

                // CONTR_PHYS_BLLG_MTR_RELN
                CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrRelnTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
                BigDecimal contrPhysBllgMtrRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_PHYS_BLLG_MTR_RELN_SQ);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.contrPhysBllgMtrRelnPk, contrPhysBllgMtrRelnPk);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.dsContrDtlPk, dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.contrMtrMultRate, (BigDecimal) contrPhysBllgMtrRelnMap.get("CONTR_MTR_MULT_RATE"));
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.bllgMtrLbCd, (String) contrPhysBllgMtrRelnMap.get("BLLG_MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.bllblFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.bllgMtrLvlNum, (String) contrPhysBllgMtrRelnMap.get("BLLG_MTR_LVL_NUM"));
                //ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.regMtrLbCd, svcUsgPrcPMsg.regMtrLbCd);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.actvFlg, ZYPConstant.FLG_ON_Y);
                contrPhysBllgMtrRelnTMsgNewList.add(contrPhysBllgMtrRelnTMsg);
            }

            List<Map<String, Object>> contrXsCopyList = getContrXsCopyListForAggLine(dsContrPk);
            for (Map<String, Object> contrXsCopyMap : contrXsCopyList) {
                BigDecimal dsContrDtlPk = getFltAggDsContrDtlPkByMap(inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.shellLineNum.getValue());
                String strDsContrDtlPk = ZYPCommonFunc.leftPad(String.valueOf(dsContrDtlPk), LENGTH_OF_PK, STR_ZERO);
                String key = createCacheKey(strDsContrDtlPk, (String) contrXsCopyMap.get("BLLG_MTR_LB_CD"));
                BigDecimal dsContrBllgMtrPk = dsContrBllgMtrPkMap.get(key);

                // CONTR_XS_COPY
                CONTR_XS_COPYTMsg contrXsCopyTMsg = new CONTR_XS_COPYTMsg();
                BigDecimal contrXsCopyPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_XS_COPY_SQ);
                ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.contrXsCopyPk, contrXsCopyPk);
                ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
                ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.xsMtrCopyQty, (BigDecimal) contrXsCopyMap.get("XS_MTR_COPY_QTY"));
                ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.xsMtrAmtRate, (BigDecimal) contrXsCopyMap.get("XS_MTR_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.xsMtrFirstFlg, (String) contrXsCopyMap.get("XS_MTR_FIRST_FLG"));
                contrXsCopyTMsgNewList.add(contrXsCopyTMsg);
            }
        }

        if (!dsContrBllgMtrTMsgNewList.isEmpty()) {
            if (checkDsContrBllgMtr(msgMap, dsContrBllgMtrTMsgNewList)) {
                int result = S21FastTBLAccessor.insert(dsContrBllgMtrTMsgNewList.toArray(new DS_CONTR_BLLG_MTRTMsg[0]));
                if (result != dsContrBllgMtrTMsgNewList.size()) {
                    setErrStatus(msgMap, NSZM1231E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }

        if (!contrPhysBllgMtrRelnTMsgNewList.isEmpty()) {
            if (checkContrPhysBllgMtrReln(msgMap, contrPhysBllgMtrRelnTMsgNewList)) {
                int result = S21FastTBLAccessor.insert(contrPhysBllgMtrRelnTMsgNewList.toArray(new CONTR_PHYS_BLLG_MTR_RELNTMsg[0]));
                if (result != contrPhysBllgMtrRelnTMsgNewList.size()) {
                    setErrStatus(msgMap, NSZM1233E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }

        if (!contrXsCopyTMsgNewList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(contrXsCopyTMsgNewList.toArray(new CONTR_XS_COPYTMsg[0]));
            if (result != contrXsCopyTMsgNewList.size()) {
                setErrStatus(msgMap, NSZM1235E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    // add start 2017/06/12 CSA Defect#18873
    private void registShortageShell(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        NSZC115001_svcPrcListPMsg svcPrcPMsg = null;
        BigDecimal shellLineNumPrev = null;
        boolean shellExistFlg = false;

        if (inPrmPMsg.svcPrcList.getValidCount() == 0) {
            return;
        }

        for (int i = 0; i < inPrmPMsg.svcPrcList.getValidCount(); i++) {
            svcPrcPMsg = inPrmPMsg.svcPrcList.no(i);
            BigDecimal shellLineNum = svcPrcPMsg.shellLineNum.getValue();

            if (!ZYPCommonFunc.hasValue(shellLineNumPrev)) {
                shellLineNumPrev = shellLineNum;
            }

            if (isFleet(inPrmPMsg, shellLineNum)) {
                shellExistFlg = true;
                continue;
            }

            if (RQST_TP_DEL.equals(svcPrcPMsg.xxRqstTpCd.getValue())) {
                shellExistFlg = true;
                continue;
            }

            if (!ZYPCommonFunc.hasValue(svcPrcPMsg.dsOrdPosnNum)) {
                shellExistFlg = true;
            }

            if (ZYPCommonFunc.hasValue(shellLineNum) && shellLineNum.compareTo(shellLineNumPrev) == 0) {
                continue;
            }

            if (!shellExistFlg) {
                registShortageShellData(msgMap, shellLineNumPrev);
            }
            shellExistFlg = false;
            shellLineNumPrev = shellLineNum;
        }

        if (!shellExistFlg) {
            registShortageShellData(msgMap, shellLineNumPrev);
        }
    }

    private void registShortageShellData(S21ApiMessageMap msgMap, BigDecimal shellLineNum) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgNewList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgNewList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        List<CONTR_XS_COPYTMsg> contrXsCopyTMsgNewList = new ArrayList<CONTR_XS_COPYTMsg>();
        List<CONTR_PHYS_BLLG_MTR_RELNTMsg> contrPhysBllgMtrRelnTMsgNewList = new ArrayList<CONTR_PHYS_BLLG_MTR_RELNTMsg>();
        Map<String, BigDecimal> dsContrBllgMtrPkMap = new HashMap<String, BigDecimal>();

        List<BigDecimal> dsContrDtlPkList = getDsContrDtlPkForShortageShell(inPrmPMsg.refCpoOrdNum.getValue(), shellLineNum);
        for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
            dsContrDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            dsContrDtlTMsg.dsContrDtlPk.setValue(dsContrDtlPk);
            dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(dsContrDtlTMsg);
            if (dsContrDtlTMsg == null) {
                continue;
            }

            // DS_CONTR_DTL(Shell)
            BigDecimal dsContrDtlPkNew = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_DTL_SQ);
            DS_CONTR_DTLTMsg dsContrDtlTMsgNew = new DS_CONTR_DTLTMsg();

            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.dsContrDtlPk, dsContrDtlPkNew);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.dsContrPk, dsContrDtlTMsg.dsContrPk);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.dsContrDtlTpCd, DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.dsContrDtlStsCd, DS_CONTR_DTL_STS.ORDER);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.cpoOrdNum, dsContrDtlTMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.dsOrdTpCd, dsContrDtlTMsg.dsOrdTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.baseBllgCycleCd, dsContrDtlTMsg.baseBllgCycleCd);
            // START 2018/02/26 M.Naito [QC#23934,MOD]
            if (!CPO_SRC_TP.DEAL_CONFIGURATOR.equals(inPrmPMsg.cpoSrcTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.basePrcDealAmt, dsContrDtlTMsg.basePrcDealAmt);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.basePrcFuncAmt, dsContrDtlTMsg.basePrcFuncAmt);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.prcMtrPkgPk, dsContrDtlTMsg.prcMtrPkgPk);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.prcCatgCd, dsContrDtlTMsg.prcCatgCd);
            } else {
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.basePrcDealAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.basePrcFuncAmt, BigDecimal.ZERO);
            }
            // END 2018/02/26 M.Naito [QC#23934,MOD]
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.supprCrFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.mtrHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.contrHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.bllgHldFlg, ZYPConstant.FLG_OFF_N);
            // START 2018/02/26 M.Naito [QC#23934,MOD]
//            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.prcMtrPkgPk, dsContrDtlTMsg.prcMtrPkgPk);
            // END 2018/02/26 M.Naito [QC#23934,MOD]
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.shellLineNum, shellLineNum);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.addContrFlg, dsContrDtlTMsg.addContrFlg);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.addAsryFlg, dsContrDtlTMsg.addAsryFlg);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.mdlId, dsContrDtlTMsg.mdlId);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.billWithEquipFlg, dsContrDtlTMsg.billWithEquipFlg);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
            // START 2018/02/26 M.Naito [QC#23934,MOD]
//            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.prcCatgCd, dsContrDtlTMsg.prcCatgCd);
            // END 2018/02/26 M.Naito [QC#23934,MOD]
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.corpAdvPrcFlg, dsContrDtlTMsg.corpAdvPrcFlg);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.dsContrDtlGrpNum, dsContrDtlPkNew);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.fromPerMthNum, dsContrDtlTMsg.fromPerMthNum);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.toPerMthNum, dsContrDtlTMsg.toPerMthNum);
            // START 2019/01/30 [QC#30137, ADD]
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgNew.dsContrCratTpCd, DS_CONTR_CRAT_TP.SHELL);
            // END 2019/01/30 [QC#30137, ADD]
            dsContrDtlTMsgNewList.add(dsContrDtlTMsgNew);

            // DS_CONTR_DTL(Machine, Accessory)
            List<BigDecimal> dsContrDtlPkModList = getDsContrDtlPkByMdlId(inPrmPMsg.refCpoOrdNum.getValue(), shellLineNum, dsContrDtlTMsg.mdlId.getValue());
            for (BigDecimal dsContrDtlPkMod : dsContrDtlPkModList) {
                DS_CONTR_DTLTMsg dsContrDtlTMsgMod = new DS_CONTR_DTLTMsg();
                dsContrDtlTMsgMod.glblCmpyCd.setValue(this.glblCmpyCd);
                dsContrDtlTMsgMod.dsContrDtlPk.setValue(dsContrDtlPkMod);
                dsContrDtlTMsgMod = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(dsContrDtlTMsgMod);
                if (dsContrDtlTMsgMod == null) {
                    continue;
                }

                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsgMod.dsContrDtlGrpNum, dsContrDtlPkNew);
                dsContrDtlTMsgModList.add(dsContrDtlTMsgMod);
            }

            // DS_CONTR_BLLG_MTR
            // START 2018/02/26 M.Naito [QC#23934,MOD]
            if (!CPO_SRC_TP.DEAL_CONFIGURATOR.equals(inPrmPMsg.cpoSrcTpCd.getValue())) {
                DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = getDsContrBllgMtrList(dsContrDtlPk);
                for (int i = 0; i < dsContrBllgMtrTMsgArray.getValidCount(); i++) {
                    DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(i);

                    BigDecimal dsContrBllgMtrPkNew = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_MTR_SQ);
                    DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsgNew = new DS_CONTR_BLLG_MTRTMsg();
                    EZDMsg.copy(dsContrBllgMtrTMsg, null, dsContrBllgMtrTMsgNew, null);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsgNew.dsContrBllgMtrPk, dsContrBllgMtrPkNew);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsgNew.dsContrDtlPk, dsContrDtlPkNew);
                    dsContrBllgMtrTMsgNewList.add(dsContrBllgMtrTMsgNew);

                    String strDsContrDtlPk = ZYPCommonFunc.leftPad(String.valueOf(dsContrDtlPkNew), LENGTH_OF_PK, STR_ZERO);
                    String key = createCacheKey(strDsContrDtlPk, dsContrBllgMtrTMsgNew.bllgMtrLbCd.getValue());
                    dsContrBllgMtrPkMap.put(key, dsContrBllgMtrPkNew);

                    // CONTR_XS_COPY
                    CONTR_XS_COPYTMsgArray contrXsCopyTMsgArray = getContrXsCopy(dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
                    for (int j = 0; j < contrXsCopyTMsgArray.getValidCount(); j++) {
                        CONTR_XS_COPYTMsg contrXsCopyTMsg = contrXsCopyTMsgArray.no(j);

                        BigDecimal contrXsCopyPkNew = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_XS_COPY_SQ);
                        CONTR_XS_COPYTMsg contrXsCopyTMsgNew = new CONTR_XS_COPYTMsg();
                        EZDMsg.copy(contrXsCopyTMsg, null, contrXsCopyTMsgNew, null);
                        ZYPEZDItemValueSetter.setValue(contrXsCopyTMsgNew.contrXsCopyPk, contrXsCopyPkNew);
                        ZYPEZDItemValueSetter.setValue(contrXsCopyTMsgNew.dsContrBllgMtrPk, dsContrBllgMtrPkNew);
                        contrXsCopyTMsgNewList.add(contrXsCopyTMsgNew);
                    }
                }

                // CONTR_PHYS_BLLG_MTR_RELN
                CONTR_PHYS_BLLG_MTR_RELNTMsgArray contrPhysBllgMtrRelnArray = getContrPhysBllgMtrRelnByDsContrDtlPk(dsContrDtlPk);
                for (int i = 0; i < contrPhysBllgMtrRelnArray.getValidCount(); i++) {
                    CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrRelnTMsg = contrPhysBllgMtrRelnArray.no(i);

                    String strDsContrDtlPk = ZYPCommonFunc.leftPad(String.valueOf(dsContrDtlPkNew), LENGTH_OF_PK, STR_ZERO);
                    String key = createCacheKey(strDsContrDtlPk, contrPhysBllgMtrRelnTMsg.bllgMtrLbCd.getValue());
                    BigDecimal dsContrBllgMtrPkNew = dsContrBllgMtrPkMap.get(key);

                    BigDecimal contrPhysBllgMtrRelnPkNew = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_PHYS_BLLG_MTR_RELN_SQ);
                    CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrRelnTMsgNew = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
                    EZDMsg.copy(contrPhysBllgMtrRelnTMsg, null, contrPhysBllgMtrRelnTMsgNew, null);
                    ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsgNew.contrPhysBllgMtrRelnPk, contrPhysBllgMtrRelnPkNew);
                    ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsgNew.dsContrDtlPk, dsContrDtlPkNew);
                    ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsgNew.dsContrBllgMtrPk, dsContrBllgMtrPkNew);
                    contrPhysBllgMtrRelnTMsgNewList.add(contrPhysBllgMtrRelnTMsgNew);
                }
            }
            // END 2018/02/26 M.Naito [QC#23934,MOD]
        }

        if (!dsContrDtlTMsgNewList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrDtlTMsgNewList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgNewList.size()) {
                setErrStatus(msgMap, NSZM1227E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        if (!dsContrDtlTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.update(dsContrDtlTMsgModList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1228E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // START 2018/02/26 M.Naito [QC#23934,MOD]
        if (!CPO_SRC_TP.DEAL_CONFIGURATOR.equals(inPrmPMsg.cpoSrcTpCd.getValue())) {
            if (!dsContrBllgMtrTMsgNewList.isEmpty()) {
                int result = S21FastTBLAccessor.insert(dsContrBllgMtrTMsgNewList.toArray(new DS_CONTR_BLLG_MTRTMsg[0]));
                if (result != dsContrBllgMtrTMsgNewList.size()) {
                    setErrStatus(msgMap, NSZM1231E); //mod u.kim 2018/03/06 QC#24056
                }
            }

            if (!contrPhysBllgMtrRelnTMsgNewList.isEmpty()) {
                int result = S21FastTBLAccessor.insert(contrPhysBllgMtrRelnTMsgNewList.toArray(new CONTR_PHYS_BLLG_MTR_RELNTMsg[0]));
                if (result != contrPhysBllgMtrRelnTMsgNewList.size()) {
                    setErrStatus(msgMap, NSZM1233E); //mod u.kim 2018/03/06 QC#24056
                }
            }

            if (!contrXsCopyTMsgNewList.isEmpty()) {
                int result = S21FastTBLAccessor.insert(contrXsCopyTMsgNewList.toArray(new CONTR_XS_COPYTMsg[0]));
                if (result != contrXsCopyTMsgNewList.size()) {
                    setErrStatus(msgMap, NSZM1235E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }
        // END 2018/02/26 M.Naito [QC#23934,MOD]
    }

    private List<BigDecimal> getDsContrDtlPkForShortageShell(String cpoOrdNum, BigDecimal shellLineNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("shellLineNum", shellLineNum);
        param.put("dsContrDtlTpCd_Accessory", DS_CONTR_DTL_TP.ACCESSORIES);
        param.put("dsContrDtlTpCd_Shell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        return this.ssmBatchClient.queryObjectList("getDsContrDtlPkForShortageShell", param);
    }

    private List<BigDecimal> getDsContrDtlPkByMdlId(String cpoOrdNum, BigDecimal shellLineNum, BigDecimal mdlId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("shellLineNum", shellLineNum);
        param.put("mdlId", mdlId);
        return this.ssmBatchClient.queryObjectList("getDsContrDtlPkByMdlId", param);
    }
    // add end 2017/06/12 CSA Defect#18873

    // START 2018/03/13 M.Naito [QC#23378, MOD]
    private void registDsContrAddlChrgForRental(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList = new ArrayList<DS_CONTR_DTLTMsg>();
//        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgDelList = new ArrayList<DS_CONTR_DTLTMsg>();
//        List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgTMsgNewList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();
//        List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgTMsgModList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();
//        List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgTMsgDelList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();
        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg = null;

        // DS_CONTR_ADDL_CHRG(Rental)
        for (int i = 0; i < inPrmPMsg.svcAddlBasePrcList.getValidCount(); i++) {
            svcAddlBasePrcPMsg = inPrmPMsg.svcAddlBasePrcList.no(i);

            // Mod Start 2018/07/27 QC#27492
//            if (!RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue()) && !ADDL_CHRG_CATG.RENTAL.equals(svcAddlBasePrcPMsg.addlChrgCatgCd.getValue())) {
            if (RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue()) || !ADDL_CHRG_CATG.RENTAL.equals(svcAddlBasePrcPMsg.addlChrgCatgCd.getValue())) {
                continue;
//            } else if (RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue()) && !ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.dsContrAddlChrgPk)) {
//                continue;
            }
            // Mod End 2018/07/27 QC#27492

            BigDecimal shellLineNum = svcAddlBasePrcPMsg.shellLineNum.getValue();
            BigDecimal dsContrPk = svcAddlBasePrcPMsg.dsContrPk.getValue();
            BigDecimal dsContrDtlPk = svcAddlBasePrcPMsg.dsContrDtlPk.getValue();
            // BigDecimal dsContrAddlChrgPk = svcAddlBasePrcPMsg.dsContrAddlChrgPk.getValue();

//            if (RQST_TP_NEW.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
            dsContrPk = getDsContrPk(inPrmPMsg.svcDtlList, shellLineNum, dsContrPk);
            dsContrDtlPk = getDsContrDtlPkByConfigOrAddlBase(inPrmPMsg.svcConfigRefList, inPrmPMsg.svcAddlBasePrcList, shellLineNum, svcAddlBasePrcPMsg.cpoDtlLineNum.getValue(), svcAddlBasePrcPMsg.cpoDtlLineSubNum.getValue());
            svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, shellLineNum);
//            }

            // update DS_CONTR_DTL Machine and Accessory line for Rental
            // get DS_CONTR_DTL Machine and Accessory line
            DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
            dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
            if (dsContrDtlTMsg == null) {
                setErrStatus(msgMap, NSZM1228E);
                continue;
            }

            // get DS_CONTR_CATG_CD
            DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, dsContrPk);
            dsContrTMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(dsContrTMsg);
            if (dsContrTMsg == null) {
                setErrStatus(msgMap, NSZM1228E);
                continue;
            }
            String dsContrCatgCd = dsContrTMsg.dsContrCatgCd.getValue();

            BigDecimal basePrcDealAmt = null;
            if (RQST_TP_NEW.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue()) || RQST_TP_MOD.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                // update Rental Price / Price List
                BigDecimal rntlPrcAmt = svcAddlBasePrcPMsg.addlBasePrcDealAmt.getValue();
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.rntlPrcAmt, rntlPrcAmt);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.rntlPrcCatgCd, svcAddlBasePrcPMsg.addlBasePrcCatgCd);

                // update Base Price
                if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                    basePrcDealAmt = dsContrDtlTMsg.basePrcDealAmt.getValue().add(rntlPrcAmt);
                    ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, basePrcDealAmt);
                }

                dsContrDtlTMsgModList.add(dsContrDtlTMsg);
            } else if (RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                // update Base Price
                if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                    basePrcDealAmt = dsContrDtlTMsg.basePrcDealAmt.getValue().subtract(dsContrDtlTMsg.rntlPrcAmt.getValue());
                    ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, basePrcDealAmt);
                }
                dsContrDtlTMsg.rntlPrcAmt.clear();
                dsContrDtlTMsg.rntlPrcCatgCd.clear();
                dsContrDtlTMsgModList.add(dsContrDtlTMsg);
            }
        }

        if (!dsContrDtlTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.update(dsContrDtlTMsgModList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1228E);
            }
        }
    }
//            DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg = new DS_CONTR_ADDL_CHRGTMsg();
//            if (RQST_TP_NEW.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
//                dsContrAddlChrgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_ADDL_CHRG_SQ);
//                ZYPEZDItemValueSetter.setValue(svcAddlBasePrcPMsg.dsContrPk, dsContrPk);
//                ZYPEZDItemValueSetter.setValue(svcAddlBasePrcPMsg.dsContrDtlPk, dsContrDtlPk);
//                ZYPEZDItemValueSetter.setValue(svcAddlBasePrcPMsg.dsContrAddlChrgPk, dsContrAddlChrgPk);
//
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.glblCmpyCd, this.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dsContrAddlChrgPk, dsContrAddlChrgPk);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dsContrPk, dsContrPk);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dsContrDtlPk, dsContrDtlPk);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgTpCd, getAddlChrgTpCdForRental(dsContrDtlPk));
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.svcBillByTpCd, SVC_BILL_BY_TP.BASE);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgInvTpCd, ADDL_CHRG_INV_TP.BASE);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.ccyCd, getCcyCd());
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgFlatDealPrcAmt, svcAddlBasePrcPMsg.addlBasePrcDealAmt);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgFlatFuncPrcAmt, NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), inPrmPMsg.slsDt.getValue(), svcAddlBasePrcPMsg.addlBasePrcDealAmt.getValue()));
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.printDtlFlg, getPrintDtlFlgForRentalAddlChrg(inPrmPMsg));
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.bllgCycleCd, svcDtlPMsg.baseBllgCycleCd);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgInvdFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.prcCatgCd, svcAddlBasePrcPMsg.addlBasePrcCatgCd);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.svcPrcCatgCd, svcAddlBasePrcPMsg.svcPrcCatgCd);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.crRebilCd, svcAddlBasePrcPMsg.crRebilCd);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgCatgCd, svcAddlBasePrcPMsg.addlChrgCatgCd);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dealPrcListPrcAmt, svcAddlBasePrcPMsg.dealPrcListPrcAmt);
//                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.prcListEquipConfigNum, svcAddlBasePrcPMsg.prcListEquipConfigNum);
//
//                dsContrAddlChrgTMsgNewList.add(dsContrAddlChrgTMsg);
//                continue;
//            }
//
//            dsContrAddlChrgTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
//            dsContrAddlChrgTMsg.dsContrAddlChrgPk.setValue(svcAddlBasePrcPMsg.dsContrAddlChrgPk.getValue());
//            dsContrAddlChrgTMsg = (DS_CONTR_ADDL_CHRGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrAddlChrgTMsg);
//            if (dsContrAddlChrgTMsg == null) {
//                setErrStatus(msgMap, NSZM1240E); //mod u.kim 2018/03/06 QC#24056
//            }
//
//            if (RQST_TP_MOD.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
//                svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, shellLineNum);
//                modDsContrAddlChrgTMsgForRental(dsContrAddlChrgTMsgModList, inPrmPMsg, svcDtlPMsg, svcAddlBasePrcPMsg, dsContrAddlChrgTMsg);
//
//            } else if (RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
//                dsContrAddlChrgTMsgDelList.add(dsContrAddlChrgTMsg);
//            }
//        }
//
//        List<DS_CONTR_ADDL_CHRG_RECTMsg> dsContrAddlChrgRecTMsgList = new ArrayList<DS_CONTR_ADDL_CHRG_RECTMsg>();
//
//        if (!dsContrAddlChrgTMsgNewList.isEmpty()) {
//            int result = S21FastTBLAccessor.insert(dsContrAddlChrgTMsgNewList.toArray(new DS_CONTR_ADDL_CHRGTMsg[0]));
//            if (result != dsContrAddlChrgTMsgNewList.size()) {
//                setErrStatus(msgMap, NSZM1237E); //mod u.kim 2018/03/06 QC#24056
//            }
//        }
//
//        for (DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg : dsContrAddlChrgTMsgNewList) {
//            String docId = getDsContrAddlChrgDocId(dsContrAddlChrgTMsg);
//
//            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_NEW, docId, inPrmPMsg.refCpoOrdNum.getValue());
//            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
//                continue;
//            }
//            DS_CONTR_ADDL_CHRG_RECTMsg dsContrAddlChrgRecTMsg = new DS_CONTR_ADDL_CHRG_RECTMsg();
//            EZDMsg.copy(dsContrAddlChrgTMsg, null, dsContrAddlChrgRecTMsg, null);
//            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgRecTMsg.bizProcLogPk, bizProcLogPk);
//            dsContrAddlChrgRecTMsgList.add(dsContrAddlChrgRecTMsg);
//        }
//
//        if (!dsContrAddlChrgTMsgModList.isEmpty()) {
//            int result = S21FastTBLAccessor.update(dsContrAddlChrgTMsgModList.toArray(new DS_CONTR_ADDL_CHRGTMsg[0]));
//            if (result != dsContrAddlChrgTMsgModList.size()) {
//                setErrStatus(msgMap, NSZM1238E); //mod u.kim 2018/03/06 QC#24056
//            }
//        }
//
//        for (DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg : dsContrAddlChrgTMsgModList) {
//            String docId = getDsContrAddlChrgDocId(dsContrAddlChrgTMsg);
//
//            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_MOD, docId, inPrmPMsg.refCpoOrdNum.getValue());
//            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
//                continue;
//            }
//            DS_CONTR_ADDL_CHRG_RECTMsg dsContrAddlChrgRecTMsg = new DS_CONTR_ADDL_CHRG_RECTMsg();
//            EZDMsg.copy(dsContrAddlChrgTMsg, null, dsContrAddlChrgRecTMsg, null);
//            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgRecTMsg.bizProcLogPk, bizProcLogPk);
//            dsContrAddlChrgRecTMsgList.add(dsContrAddlChrgRecTMsg);
//        }
//
//        if (!dsContrAddlChrgTMsgDelList.isEmpty()) {
//            int result = S21FastTBLAccessor.removeLogical(dsContrAddlChrgTMsgDelList.toArray(new DS_CONTR_ADDL_CHRGTMsg[0]));
//            if (result != dsContrAddlChrgTMsgDelList.size()) {
//                setErrStatus(msgMap, NSZM1239E); //mod u.kim 2018/03/06 QC#24056
//            }
//        }
//
//        for (DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg : dsContrAddlChrgTMsgDelList) {
//            String docId = getDsContrAddlChrgDocId(dsContrAddlChrgTMsg);
//
//            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
//            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
//                continue;
//            }
//            DS_CONTR_ADDL_CHRG_RECTMsg dsContrAddlChrgRecTMsg = new DS_CONTR_ADDL_CHRG_RECTMsg();
//            EZDMsg.copy(dsContrAddlChrgTMsg, null, dsContrAddlChrgRecTMsg, null);
//            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgRecTMsg.bizProcLogPk, bizProcLogPk);
//            dsContrAddlChrgRecTMsgList.add(dsContrAddlChrgRecTMsg);
//        }
//
//        if (!dsContrAddlChrgRecTMsgList.isEmpty()) {
//            int result = S21FastTBLAccessor.insert(dsContrAddlChrgRecTMsgList.toArray(new DS_CONTR_ADDL_CHRG_RECTMsg[0]));
//            if (result != dsContrAddlChrgRecTMsgList.size()) {
//                setErrStatus(msgMap, NSZM1265E); //mod u.kim 2018/03/06 QC#24056
//            }
//        }
//    }
    // END 2018/03/13 M.Naito [QC#23378, MOD]

    private String getDsContrDocId(BigDecimal dsContrPk, NSZC115001PMsg inPrmPMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);

        List<BigDecimal> rslt = this.ssmBatchClient.queryObjectList("getDsContrDocId", param);
        if (rslt.size() > 0) {
            // START 2017/07/06 [QC#19736, MOD]
            BigDecimal docId = rslt.get(0);
            if (docId != null) {
                return rslt.get(0).toPlainString();
            }
            // END   2017/07/06 [QC#19736, MOD]
        }

        if (inPrmPMsg == null || inPrmPMsg.svcDtlList.getValidCount() == 0) {
            return null;
        }

        if (!ZYPCommonFunc.hasValue(inPrmPMsg.svcDtlList.no(0).shellLineNum)) {
            return null;
        }

        return inPrmPMsg.svcDtlList.no(0).shellLineNum.getValue().toPlainString();
    }

    private String getSvcMemoDocId(SVC_MEMOTMsg svcMemoTMsg, NSZC115001PMsg inPrmPMsg) {
        String result = getDsContrDocId(svcMemoTMsg.dsContrPk.getValue(), inPrmPMsg);
        if (!ZYPCommonFunc.hasValue(result)) {
            return null;
        }
        return result + DOC_ID_DLMT + svcMemoTMsg.svcMemoRsnCd.getValue();
    }

    private String getDsContrDtlDocId(BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("docIdDlmt", DOC_ID_DLMT);

        List<String> rslt = this.ssmBatchClient.queryObjectList("getDsContrDtlDocId", param);
        if (rslt.size() == 0) {
            return null;
        }

        return rslt.get(0);
    }

    private String getDsContrAddlChrgDocId(DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg) {
        String result = getDsContrDtlDocId(dsContrAddlChrgTMsg.dsContrDtlPk.getValue());
        if (!ZYPCommonFunc.hasValue(result)) {
            return null;
        }

        return result + DOC_ID_DLMT + dsContrAddlChrgTMsg.addlChrgCatgCd.getValue();
    }

    // START 2018/03/13 M.Naito [QC#23378, DEL]
//    private void modDsContrAddlChrgTMsgForRental(List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgTMsgModList, NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg, DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg) {
//
//        if (isSameValueDsContrAddlChrgTMsgForRental(inPrmPMsg, svcDtlPMsg, svcAddlBasePrcPMsg, dsContrAddlChrgTMsg)) {
//            return;
//        }
//
//        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgFlatDealPrcAmt, svcAddlBasePrcPMsg.addlBasePrcDealAmt);
//        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgFlatFuncPrcAmt, NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), inPrmPMsg.slsDt.getValue(), svcAddlBasePrcPMsg.addlBasePrcDealAmt.getValue()));
//        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.printDtlFlg, getPrintDtlFlgForRentalAddlChrg(inPrmPMsg));
//        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.bllgCycleCd, svcDtlPMsg.baseBllgCycleCd);
//        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.prcCatgCd, svcAddlBasePrcPMsg.addlBasePrcCatgCd);
//        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.svcPrcCatgCd, svcAddlBasePrcPMsg.svcPrcCatgCd);
//        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
//        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.crRebilCd, svcAddlBasePrcPMsg.crRebilCd);
//        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgCatgCd, svcAddlBasePrcPMsg.addlChrgCatgCd);
//        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dealPrcListPrcAmt, svcAddlBasePrcPMsg.dealPrcListPrcAmt);
//        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.prcListEquipConfigNum, svcAddlBasePrcPMsg.prcListEquipConfigNum);
//
//        dsContrAddlChrgTMsgModList.add(dsContrAddlChrgTMsg);
//    }
//
//    private boolean isSameValueDsContrAddlChrgTMsgForRental(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg, DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg) {
//
//        if (!isEqualBigDecimal(svcAddlBasePrcPMsg.addlBasePrcDealAmt.getValue(), dsContrAddlChrgTMsg.addlChrgFlatDealPrcAmt.getValue())) {
//            return false;
//        }
//        if (!S21StringUtil.isEquals(getPrintDtlFlgForRentalAddlChrg(inPrmPMsg), dsContrAddlChrgTMsg.printDtlFlg.getValue())) {
//            return false;
//        }
//        if (!S21StringUtil.isEquals(svcDtlPMsg.baseBllgCycleCd.getValue(), dsContrAddlChrgTMsg.bllgCycleCd.getValue())) {
//            return false;
//        }
//        if (!S21StringUtil.isEquals(svcAddlBasePrcPMsg.addlBasePrcCatgCd.getValue(), dsContrAddlChrgTMsg.prcCatgCd.getValue())) {
//            return false;
//        }
//        if (!S21StringUtil.isEquals(svcAddlBasePrcPMsg.svcPrcCatgCd.getValue(), dsContrAddlChrgTMsg.svcPrcCatgCd.getValue())) {
//            return false;
//        }
//        if (!S21StringUtil.isEquals(getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()), dsContrAddlChrgTMsg.billWithEquipFlg.getValue())) {
//            return false;
//        }
//        if (!S21StringUtil.isEquals(svcAddlBasePrcPMsg.crRebilCd.getValue(), dsContrAddlChrgTMsg.crRebilCd.getValue())) {
//            return false;
//        }
//        if (!S21StringUtil.isEquals(svcAddlBasePrcPMsg.addlChrgCatgCd.getValue(), dsContrAddlChrgTMsg.addlChrgCatgCd.getValue())) {
//            return false;
//        }
//        if (!isEqualBigDecimal(svcAddlBasePrcPMsg.dealPrcListPrcAmt.getValue(), dsContrAddlChrgTMsg.dealPrcListPrcAmt.getValue())) {
//            return false;
//        }
//        if (!isEqualBigDecimal(svcAddlBasePrcPMsg.prcListEquipConfigNum.getValue(), dsContrAddlChrgTMsg.prcListEquipConfigNum.getValue())) {
//            return false;
//        }
//
//        return true;
//    }
    // END 2018/03/13 M.Naito [QC#23378, DEL]

    private void registDsContrAddlChrgForNormal(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgTMsgNewList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();
        List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgTMsgModList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();
        List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgTMsgDelList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();
        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        NSZC115001_svcAddlChrgPrcListPMsg svcAddlChrgPrcPMsg = null;

        // DS_CONTR_ADDL_CHRG(Normal)
        for (int i = 0; i < inPrmPMsg.svcAddlChrgPrcList.getValidCount(); i++) {
            svcAddlChrgPrcPMsg = inPrmPMsg.svcAddlChrgPrcList.no(i);

            BigDecimal shellLineNum = svcAddlChrgPrcPMsg.shellLineNum.getValue();
            BigDecimal dsContrPk = svcAddlChrgPrcPMsg.dsContrPk.getValue();
            BigDecimal dsContrDtlPk = svcAddlChrgPrcPMsg.dsContrDtlPk.getValue();
            BigDecimal dsContrAddlChrgPk = svcAddlChrgPrcPMsg.dsContrAddlChrgPk.getValue();

            if (RQST_TP_NEW.equals(svcAddlChrgPrcPMsg.xxRqstTpCd.getValue())) {
                dsContrPk = getDsContrPk(inPrmPMsg.svcDtlList, shellLineNum, dsContrPk);
                dsContrDtlPk = getDsContrDtlPkByConfigOrAddlBase(inPrmPMsg.svcConfigRefList, inPrmPMsg.svcAddlBasePrcList, shellLineNum, svcAddlChrgPrcPMsg.cpoDtlLineNum.getValue(), svcAddlChrgPrcPMsg.cpoDtlLineSubNum.getValue());
                svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, shellLineNum);
            }

            DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg = new DS_CONTR_ADDL_CHRGTMsg();
            if (RQST_TP_NEW.equals(svcAddlChrgPrcPMsg.xxRqstTpCd.getValue())) {
                dsContrAddlChrgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_ADDL_CHRG_SQ);
                ZYPEZDItemValueSetter.setValue(svcAddlChrgPrcPMsg.dsContrPk, dsContrPk);
                ZYPEZDItemValueSetter.setValue(svcAddlChrgPrcPMsg.dsContrDtlPk, dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(svcAddlChrgPrcPMsg.dsContrAddlChrgPk, dsContrAddlChrgPk);

                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dsContrAddlChrgPk, dsContrAddlChrgPk);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dsContrPk, dsContrPk);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dsContrDtlPk, dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgTpCd, svcAddlChrgPrcPMsg.addlChrgMdseCd);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.svcBillByTpCd, SVC_BILL_BY_TP.BASE);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgInvTpCd, ADDL_CHRG_INV_TP.BASE);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.ccyCd, getCcyCd());
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgFlatDealPrcAmt, svcAddlChrgPrcPMsg.addlChrgPrcDealAmt);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgFlatFuncPrcAmt, NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), inPrmPMsg.slsDt.getValue(), svcAddlChrgPrcPMsg.addlChrgPrcDealAmt.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.printDtlFlg, getPrintDtlFlgForNormalAddlChrg(inPrmPMsg, svcAddlChrgPrcPMsg.printDtlFlg.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.bllgCycleCd, svcDtlPMsg.baseBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgInvdFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.prcCatgCd, svcAddlChrgPrcPMsg.addlChrgPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.svcPrcCatgCd, svcAddlChrgPrcPMsg.svcPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.crRebilCd, svcAddlChrgPrcPMsg.crRebilCd);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgCatgCd, ADDL_CHRG_CATG.NORMAL);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dealPrcListPrcAmt, svcAddlChrgPrcPMsg.dealPrcListPrcAmt);

                dsContrAddlChrgTMsgNewList.add(dsContrAddlChrgTMsg);
                continue;
            }

            dsContrAddlChrgTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            dsContrAddlChrgTMsg.dsContrAddlChrgPk.setValue(svcAddlChrgPrcPMsg.dsContrAddlChrgPk.getValue());
            dsContrAddlChrgTMsg = (DS_CONTR_ADDL_CHRGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrAddlChrgTMsg);
            if (dsContrAddlChrgTMsg == null) {
                setErrStatus(msgMap, NSZM1240E); //mod u.kim 2018/03/06 QC#24056
            }

            if (RQST_TP_MOD.equals(svcAddlChrgPrcPMsg.xxRqstTpCd.getValue())) {
                svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, shellLineNum);
                modDsContrAddlChrgTMsgForNormal(dsContrAddlChrgTMsgModList, inPrmPMsg, svcDtlPMsg, svcAddlChrgPrcPMsg, dsContrAddlChrgTMsg);

            } else if (RQST_TP_DEL.equals(svcAddlChrgPrcPMsg.xxRqstTpCd.getValue())) {
                dsContrAddlChrgTMsgDelList.add(dsContrAddlChrgTMsg);
            }
        }

        List<DS_CONTR_ADDL_CHRG_RECTMsg> dsContrAddlChrgRecTMsgList = new ArrayList<DS_CONTR_ADDL_CHRG_RECTMsg>();

        if (!dsContrAddlChrgTMsgNewList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrAddlChrgTMsgNewList.toArray(new DS_CONTR_ADDL_CHRGTMsg[0]));
            if (result != dsContrAddlChrgTMsgNewList.size()) {
                setErrStatus(msgMap, NSZM1237E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        for (DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg : dsContrAddlChrgTMsgNewList) {
            String docId = getDsContrAddlChrgDocId(dsContrAddlChrgTMsg);

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_NEW, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_ADDL_CHRG_RECTMsg dsContrAddlChrgRecTMsg = new DS_CONTR_ADDL_CHRG_RECTMsg();
            EZDMsg.copy(dsContrAddlChrgTMsg, null, dsContrAddlChrgRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrAddlChrgRecTMsgList.add(dsContrAddlChrgRecTMsg);
        }

        if (!dsContrAddlChrgTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.update(dsContrAddlChrgTMsgModList.toArray(new DS_CONTR_ADDL_CHRGTMsg[0]));
            if (result != dsContrAddlChrgTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1238E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        for (DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg : dsContrAddlChrgTMsgModList) {
            String docId = getDsContrAddlChrgDocId(dsContrAddlChrgTMsg);

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_MOD, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_ADDL_CHRG_RECTMsg dsContrAddlChrgRecTMsg = new DS_CONTR_ADDL_CHRG_RECTMsg();
            EZDMsg.copy(dsContrAddlChrgTMsg, null, dsContrAddlChrgRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrAddlChrgRecTMsgList.add(dsContrAddlChrgRecTMsg);
        }

        if (!dsContrAddlChrgTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrAddlChrgTMsgDelList.toArray(new DS_CONTR_ADDL_CHRGTMsg[0]));
            if (result != dsContrAddlChrgTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1239E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        for (DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg : dsContrAddlChrgTMsgDelList) {
            String docId = getDsContrAddlChrgDocId(dsContrAddlChrgTMsg);

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_ADDL_CHRG_RECTMsg dsContrAddlChrgRecTMsg = new DS_CONTR_ADDL_CHRG_RECTMsg();
            EZDMsg.copy(dsContrAddlChrgTMsg, null, dsContrAddlChrgRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrAddlChrgRecTMsgList.add(dsContrAddlChrgRecTMsg);
        }

        if (!dsContrAddlChrgRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrAddlChrgRecTMsgList.toArray(new DS_CONTR_ADDL_CHRG_RECTMsg[0]));
            if (result != dsContrAddlChrgRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1265E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private void modDsContrAddlChrgTMsgForNormal(List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgTMsgModList, NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcAddlChrgPrcListPMsg svcAddlChrgPrcPMsg, DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg) {

        if (isSameValueDsContrAddlChrgTMsgForNormal(inPrmPMsg, svcDtlPMsg, svcAddlChrgPrcPMsg, dsContrAddlChrgTMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgTpCd, svcAddlChrgPrcPMsg.addlChrgMdseCd);
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgFlatDealPrcAmt, svcAddlChrgPrcPMsg.addlChrgPrcDealAmt);
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgFlatFuncPrcAmt, NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), inPrmPMsg.slsDt.getValue(), svcAddlChrgPrcPMsg.addlChrgPrcDealAmt.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.printDtlFlg, getPrintDtlFlgForNormalAddlChrg(inPrmPMsg, svcAddlChrgPrcPMsg.printDtlFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.bllgCycleCd, svcDtlPMsg.baseBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.prcCatgCd, svcAddlChrgPrcPMsg.addlChrgPrcCatgCd);
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.svcPrcCatgCd, svcAddlChrgPrcPMsg.svcPrcCatgCd);
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.crRebilCd, svcAddlChrgPrcPMsg.crRebilCd);
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dealPrcListPrcAmt, svcAddlChrgPrcPMsg.dealPrcListPrcAmt);

        dsContrAddlChrgTMsgModList.add(dsContrAddlChrgTMsg);
    }

    private boolean isSameValueDsContrAddlChrgTMsgForNormal(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcAddlChrgPrcListPMsg svcAddlChrgPrcPMsg, DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg) {

        if (!S21StringUtil.isEquals(svcAddlChrgPrcPMsg.addlChrgMdseCd.getValue(), dsContrAddlChrgTMsg.addlChrgTpCd.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcAddlChrgPrcPMsg.addlChrgPrcDealAmt.getValue(), dsContrAddlChrgTMsg.addlChrgFlatDealPrcAmt.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getPrintDtlFlgForNormalAddlChrg(inPrmPMsg, svcAddlChrgPrcPMsg.printDtlFlg.getValue()), dsContrAddlChrgTMsg.printDtlFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcDtlPMsg.baseBllgCycleCd.getValue(), dsContrAddlChrgTMsg.bllgCycleCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcAddlChrgPrcPMsg.addlChrgPrcCatgCd.getValue(), dsContrAddlChrgTMsg.prcCatgCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcAddlChrgPrcPMsg.svcPrcCatgCd.getValue(), dsContrAddlChrgTMsg.svcPrcCatgCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()), dsContrAddlChrgTMsg.billWithEquipFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(svcAddlChrgPrcPMsg.crRebilCd.getValue(), dsContrAddlChrgTMsg.crRebilCd.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(svcAddlChrgPrcPMsg.dealPrcListPrcAmt.getValue(), dsContrAddlChrgTMsg.dealPrcListPrcAmt.getValue())) {
            return false;
        }

        return true;
    }

    private void removeAsryAddlPoCardForRemovedMachine(S21ApiMessageMap msgMap) {
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgDelList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgTMsgDelList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();
        List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgDelList = new ArrayList<DS_CONTR_CR_CARD_POTMsg>();
        // add start 2020/03/24 QC#54318
        List<DS_CONTR_RNW_ESCLTMsg> dsContrRnwEsclTMsgDelList = new ArrayList<DS_CONTR_RNW_ESCLTMsg>();
        // add end 2020/03/24 QC#54318

        for (BigDecimal dsContrDtlPk : this.dsContrDtlPkListForAsryAddlRemoval) {
            // DS_CONTR_DTL(Accessory)
            DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = getDsContrDtlListByPrntDsContrDtlPk(dsContrDtlPk);
            if (dsContrDtlTMsgArray != null) {
                for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
                    dsContrDtlTMsgDelList.add(dsContrDtlTMsgArray.no(i));
                    BigDecimal dsContrDtlPkAcc = dsContrDtlTMsgArray.no(i).dsContrDtlPk.getValue();

                    // DS_CONTR_ADDL_CHRG(Accessory)
                    DS_CONTR_ADDL_CHRGTMsgArray dsContrAddlChrgTMsgArray = getDsContrAddlChrgByDsContrDtlPk(dsContrDtlPkAcc);
                    if (dsContrAddlChrgTMsgArray != null) {
                        for (int j = 0; j < dsContrAddlChrgTMsgArray.getValidCount(); j++) {
                            dsContrAddlChrgTMsgDelList.add(dsContrAddlChrgTMsgArray.no(j));
                        }
                    }

                    // DS_CONTR_CR_CARD_PO(Accessory)
                    DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
                    dsContrCrCardPoTMsg.setSQLID("003");
                    dsContrCrCardPoTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                    dsContrCrCardPoTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPkAcc);
                    DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsg);
                    for (int j = 0; j < dsContrCrCardPoTMsgArray.getValidCount(); j++) {
                        dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArray.no(j));
                    }

                    // add start 2020/03/24 QC#54318
                    // DS_CONTR_RNW_ESCL(Accessory)
                    DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg = new DS_CONTR_RNW_ESCLTMsg();
                    dsContrRnwEsclTMsg.setSQLID("003");
                    dsContrRnwEsclTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                    dsContrRnwEsclTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPkAcc);
                    DS_CONTR_RNW_ESCLTMsgArray dsContrRnwEsclTMsgArray = (DS_CONTR_RNW_ESCLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrRnwEsclTMsg);
                    for (int j = 0; j < dsContrRnwEsclTMsgArray.getValidCount(); j++) {
                        dsContrRnwEsclTMsgDelList.add(dsContrRnwEsclTMsgArray.no(j));
                    }
                    // add end 2020/03/24 QC#54318
                }
            }

            // DS_CONTR_ADDL_CHRG(Machine)
            DS_CONTR_ADDL_CHRGTMsgArray dsContrAddlChrgTMsgArray = getDsContrAddlChrgByDsContrDtlPk(dsContrDtlPk);
            if (dsContrAddlChrgTMsgArray != null) {
                for (int i = 0; i < dsContrAddlChrgTMsgArray.getValidCount(); i++) {
                    dsContrAddlChrgTMsgDelList.add(dsContrAddlChrgTMsgArray.no(i));
                }
            }
        }

        // remove DS_CONTR_DTL
        if (!dsContrDtlTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrDtlTMsgDelList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1229E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        // DS_CONTR_DTL_REC
        List<DS_CONTR_DTL_RECTMsg> dsContrDtlRecTMsgList = new ArrayList<DS_CONTR_DTL_RECTMsg>();
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgDelList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrDtlRecTMsgList.toArray(new DS_CONTR_DTL_RECTMsg[0]));
            if (result != dsContrDtlRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1264E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // remove DS_CONTR_ADDL_CHRG
        if (!dsContrAddlChrgTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrAddlChrgTMsgDelList.toArray(new DS_CONTR_ADDL_CHRGTMsg[0]));
            if (result != dsContrAddlChrgTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1239E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // DS_CONTR_ADDL_CHRG_REC
        List<DS_CONTR_ADDL_CHRG_RECTMsg> dsContrAddlChrgRecTMsgList = new ArrayList<DS_CONTR_ADDL_CHRG_RECTMsg>();
        for (DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg : dsContrAddlChrgTMsgDelList) {
            String docId = getDsContrAddlChrgDocId(dsContrAddlChrgTMsg);

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_ADDL_CHRG_RECTMsg dsContrAddlChrgRecTMsg = new DS_CONTR_ADDL_CHRG_RECTMsg();
            EZDMsg.copy(dsContrAddlChrgTMsg, null, dsContrAddlChrgRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrAddlChrgRecTMsgList.add(dsContrAddlChrgRecTMsg);
        }

        if (!dsContrAddlChrgRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrAddlChrgRecTMsgList.toArray(new DS_CONTR_ADDL_CHRG_RECTMsg[0]));
            if (result != dsContrAddlChrgRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1265E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // remove DS_CONTR_CR_CARD_PO
        if (!dsContrCrCardPoTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrCrCardPoTMsgDelList.toArray(new DS_CONTR_CR_CARD_POTMsg[0]));
            if (result != dsContrCrCardPoTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1245E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // add start 2020/03/24 QC#54318
        // remove DS_CONTR_RNW_ESCL
        if (!dsContrRnwEsclTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrRnwEsclTMsgDelList.toArray(new DS_CONTR_RNW_ESCLTMsg[0]));
            if (result != dsContrRnwEsclTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1245E);
            }
        }
        // add end 2020/03/24 QC#54318
    }

    private DS_CONTR_DTLTMsgArray getDsContrDtlListByPrntDsContrDtlPk(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg param = new DS_CONTR_DTLTMsg();
        param.setSQLID("202");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("prntDsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(param);
    }

    private DS_CONTR_ADDL_CHRGTMsgArray getDsContrAddlChrgByDsContrDtlPk(BigDecimal dsContrDtlPk) {
        DS_CONTR_ADDL_CHRGTMsg param = new DS_CONTR_ADDL_CHRGTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_ADDL_CHRGTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(param);
    }

    private void registDsContrTaxDtl(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        String slsDt = inPrmPMsg.slsDt.getValue();
        String cpoOrdNum = inPrmPMsg.refCpoOrdNum.getValue();

        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            BigDecimal shellLineNum = inPrmPMsg.svcDtlList.no(i).shellLineNum.getValue();
            deleteTaxRecord(cpoOrdNum, shellLineNum);

            // 2018/05/07 QC#22482 Add Start
            if (isManOvrd(inPrmPMsg.svcDtlList.no(i))) {
                continue;
            }
            // 2018/05/07 QC#22482 Add End

            // START 2017/06/19 K.Kitachi [QC#18855, MOD]
//            List<Map<String, Object>> prcgPrmList = getPrcgApiParams(cpoOrdNum, shellLineNum, slsDt);
            List<Map<String, Object>> prcgPrmList;
            if (DS_CONTR_CATG.FLEET.equals(inPrmPMsg.svcDtlList.no(i).dsContrCatgCd.getValue())) {
                prcgPrmList = getPrcgApiParamsForFleet(cpoOrdNum, shellLineNum, slsDt);
            } else {
                prcgPrmList = getPrcgApiParams(cpoOrdNum, shellLineNum, slsDt);
            }
            // END 2017/06/19 K.Kitachi [QC#18855, MOD]
            if (prcgPrmList == null || prcgPrmList.isEmpty()) {
                // mod start 2017/06/22 CSA Defect#18855
//                return;
                continue;
                // mod end 2017/06/22 CSA Defect#18855
            }

            NWZC157001PMsg prcgPMsgDtl = null;
            NWZC157001PMsg prcgPMsgAddl = null;

            BigDecimal dsContrPk = null;
            for (Map<String, Object> prcgPrmMap : prcgPrmList) {
                dsContrPk = (BigDecimal) prcgPrmMap.get("DS_CONTR_PK");

                if (ZYPCommonFunc.hasValue((BigDecimal) prcgPrmMap.get("DS_CONTR_DTL_PK"))) {
                    if (prcgPMsgDtl == null) {
                        prcgPMsgDtl = getPrcgHdrPMsg(slsDt, prcgPrmMap);
                    }
                    setPrcgLinePMsg(prcgPMsgDtl, prcgPrmMap);

                } else if (ZYPCommonFunc.hasValue((BigDecimal) prcgPrmMap.get("DS_CONTR_ADDL_CHRG_PK"))) {
                    if (prcgPMsgAddl == null) {
                        prcgPMsgAddl = getPrcgHdrPMsg(slsDt, prcgPrmMap);
                    }
                    setPrcgLinePMsg(prcgPMsgAddl, prcgPrmMap);
                }
            }
            if (prcgPMsgDtl == null && prcgPMsgAddl == null) {
                continue;
            }

            registTaxRecord(msgMap, dsContrPk, cpoOrdNum, shellLineNum, prcgPMsgDtl, prcgPMsgAddl);
        }
    }

    private void deleteTaxRecord(String cpoOrdNum, BigDecimal shellLineNum) {
        DS_CONTR_TAX_DTLTMsg keyTMsg = new DS_CONTR_TAX_DTLTMsg();
        keyTMsg.setSQLID("001");
        keyTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        keyTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        keyTMsg.setConditionValue("dsContrTaxLineNum01", shellLineNum);

        DS_CONTR_TAX_DTLTMsgArray dsContrTaxDtlTMsgAry = (DS_CONTR_TAX_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(keyTMsg);

        DS_CONTR_TAX_DTLTMsg[] recTMsg = new DS_CONTR_TAX_DTLTMsg[dsContrTaxDtlTMsgAry.getValidCount()];
        for (int i = 0; i < dsContrTaxDtlTMsgAry.getValidCount(); i++) {
            recTMsg[i] = dsContrTaxDtlTMsgAry.no(i);
        }
        if (dsContrTaxDtlTMsgAry.length() > 0) {
            S21FastTBLAccessor.removePhysical(recTMsg);
        }
    }

    private List<Map<String, Object>> getPrcgApiParams(String cpoOrdNum, BigDecimal shellLineNum, String slsDt) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("shellLineNum", shellLineNum);
        param.put("dsContrDtlTpCd_Aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        param.put("addlChrgCatgcd_Rental", ADDL_CHRG_CATG.RENTAL);
        param.put("addlChrgCatgcd_Normal", ADDL_CHRG_CATG.NORMAL);
        param.put("slsDt", slsDt);
        // START 2017/12/27 U.Kim [QC#23104, ADD] 
        param.put("aftDeclPntDigitNum", getDealCcyDigit());
        // END 2017/12/27 U.Kim [QC#23104, ADD]
        List<Map<String, Object>> rslt = this.ssmBatchClient.queryObjectList("getPrcgApiParams", param);

        return rslt;
    }

    // START 2017/06/19 K.Kitachi [QC#18855, ADD]
    private List<Map<String, Object>> getPrcgApiParamsForFleet(String cpoOrdNum, BigDecimal shellLineNum, String slsDt) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("shellLineNum", shellLineNum);
        param.put("dsContrDtlTpCd_Shell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        param.put("dsContrDtlTpCd_Fleet", DS_CONTR_DTL_TP.FLEET);
        param.put("addlChrgCatgcd_Rental", ADDL_CHRG_CATG.RENTAL);
        param.put("addlChrgCatgcd_Normal", ADDL_CHRG_CATG.NORMAL);
        param.put("slsDt", slsDt);
        param.put("dummyCpoDtlLineNum", DUMMY_CPO_DTL_LINE_NUM);
        param.put("dummyCpoDtlLineSubNum", DUMMY_CPO_DTL_LINE_SUB_NUM);
        // START 2017/12/27 U.Kim [QC#23104, ADD] 
        param.put("aftDeclPntDigitNum", getDealCcyDigit());
        // END 2017/12/27 U.Kim [QC#23104, ADD]

        List<Map<String, Object>> rslt = this.ssmBatchClient.queryObjectList("getPrcgApiParamsForFleet", param);

        return rslt;
    }
    // END 2017/06/19 K.Kitachi [QC#18855, ADD]

    private NWZC157001PMsg getPrcgHdrPMsg(String slsDt, Map<String, Object> prcgPrmMap) {

        NWZC157001PMsg prcgPMsg = new NWZC157001PMsg();

        // Header
        prcgPMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        prcgPMsg.xxModeCd.setValue(NWZC157001.RECALC);
        prcgPMsg.prcBaseDt.setValue(slsDt);
        prcgPMsg.prcCtxTpCd.setValue(PRC_CTX_TP.SERVICE_PRICING);
        ZYPEZDItemValueSetter.setValue(prcgPMsg.lineBizTpCd, (String) prcgPrmMap.get("LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(prcgPMsg.dsAcctNum, (String) prcgPrmMap.get("DS_ACCT_NUM"));
        prcgPMsg.taxCalcFlg.setValue(ZYPConstant.FLG_ON_Y);
        prcgPMsg.taxFlg.setValue(ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(prcgPMsg.leasePrchOptCd, (String) prcgPrmMap.get("LEASE_PRCH_OPT_CD"));
        prcgPMsg.xxPrcCatgOpFlg.setValue(ZYPConstant.FLG_OFF_N);
        prcgPMsg.xxSmryLineFlg.setValue(ZYPConstant.FLG_ON_Y);

        ZYPEZDItemValueSetter.setValue(prcgPMsg.dsOrdCatgCd, (String) prcgPrmMap.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(prcgPMsg.dsOrdTpCd, (String) prcgPrmMap.get("DS_ORD_TP_CD"));

        return prcgPMsg;
    }

    private void setPrcgLinePMsg(NWZC157001PMsg prcgPMsg, Map<String, Object> prcgPrmMap) {

        // Line
        int ix = prcgPMsg.NWZC157002PMsg.getValidCount();
        NWZC157002PMsg prcgLinePMsg = prcgPMsg.NWZC157002PMsg.no(ix);
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.trxLineNum, (String) prcgPrmMap.get("CPO_DTL_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.trxLineSubNum, (String) prcgPrmMap.get("CPO_DTL_LINE_SUB_NUM"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.billToCustCd, (String) prcgPrmMap.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.shipToCustCd, (String) prcgPrmMap.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.dsAcctNum_SH, (String) prcgPrmMap.get("DS_ACCT_NUM_SH"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.dsAcctNum_BL, (String) prcgPrmMap.get("DS_ACCT_NUM_BL"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.ccyCd, (String) prcgPrmMap.get("CCY_CD"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.mdseCd, (String) prcgPrmMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.pkgUomCd, (String) prcgPrmMap.get("PKG_UOM_CD"));
        prcgLinePMsg.ordQty.setValue(BigDecimal.ONE);
        prcgLinePMsg.ordCustUomQty.setValue(BigDecimal.ONE);
        prcgLinePMsg.invQty.setValue(BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.firstLineAddr_SH, (String) prcgPrmMap.get("FIRST_LINE_ADDR_SH"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.scdLineAddr_SH, (String) prcgPrmMap.get("SCD_LINE_ADDR_SH"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.ctyAddr_SH, (String) prcgPrmMap.get("CTY_ADDR_SH"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.stCd_SH, (String) prcgPrmMap.get("ST_CD_SH"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.cntyNm_SH, (String) prcgPrmMap.get("CNTY_NM_SH"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.postCd_SH, (String) prcgPrmMap.get("POST_CD_SH"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.ctryCd_SH, (String) prcgPrmMap.get("CTRY_CD_SH"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.slsRepOrSlsTeamTocCd, (String) prcgPrmMap.get("SLS_REP_OR_SLS_TEAM_TOC_CD"));
        // START 2017/07/05 K.Kim [QC#19672, DEL]
//        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.rtlWhCd, (String) prcgPrmMap.get("RTL_WH_CD"));
        // END 2017/07/05 K.Kim [QC#19672, DEL]
        prcgLinePMsg.xxPrcCloFlg.setValue(ZYPConstant.FLG_OFF_N);
        ix++;
        prcgPMsg.NWZC157002PMsg.setValidCount(ix);

        // Price Category
        ix = prcgLinePMsg.NWZC157003PMsg.getValidCount();
        NWZC157003PMsg prcgCatgPMsg = prcgLinePMsg.NWZC157003PMsg.no(ix);
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.trxLineNum, (String) prcgPrmMap.get("CPO_DTL_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(prcgLinePMsg.trxLineSubNum, (String) prcgPrmMap.get("CPO_DTL_LINE_SUB_NUM"));
        prcgCatgPMsg.configCatgCd.setValue(LINE_CONFIG);
        prcgCatgPMsg.prcDtlGrpCd.setValue(PRC_DTL_GRP.BASE_PRICE);
        ZYPEZDItemValueSetter.setValue(prcgCatgPMsg.prcCatgCd, (String) prcgPrmMap.get("PRC_CATG_CD"));
        prcgCatgPMsg.prcCondManEntryFlg.setValue(ZYPConstant.FLG_OFF_N);
        prcgCatgPMsg.prcCondManAddFlg.setValue(ZYPConstant.FLG_OFF_N);
        prcgCatgPMsg.prcCondManDelFlg.setValue(ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcgCatgPMsg.pkgUomCd, (String) prcgPrmMap.get("PKG_UOM_CD"));
        prcgCatgPMsg.prcCondUnitCd.setValue(PRC_COND_UNIT.AMT);
        ZYPEZDItemValueSetter.setValue(prcgCatgPMsg.autoPrcCcyCd, (String) prcgPrmMap.get("CCY_CD"));
        ZYPEZDItemValueSetter.setValue(prcgCatgPMsg.autoPrcAmtRate, (BigDecimal) prcgPrmMap.get("AUTO_PRC_AMT_RATE"));
        ZYPEZDItemValueSetter.setValue(prcgCatgPMsg.calcPrcAmtRate, (BigDecimal) prcgPrmMap.get("CALC_PRC_AMT_RATE"));
        ZYPEZDItemValueSetter.setValue(prcgCatgPMsg.unitPrcAmt, (BigDecimal) prcgPrmMap.get("UNIT_PRC_AMT"));
        ix++;
        prcgLinePMsg.NWZC157003PMsg.setValidCount(ix);
    }

    private BigDecimal[] calcTotTaxAmt(S21ApiMessageMap msgMap, NWZC157001PMsg prcgPMsg) {

        BigDecimal[] totTaxAmt = new BigDecimal[2];
        totTaxAmt[0] = BigDecimal.ZERO;
        totTaxAmt[1] = BigDecimal.ZERO;

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        String slsDt = inPrmPMsg.slsDt.getValue();

        // Call Pricing API
        NWZC157001 prcgApi = new NWZC157001();
        prcgApi.execute(prcgPMsg, this.onBatchType);

        if (prcgPMsg.xxMsgIdList.getValidCount() > 0) {
            if (addToMyMsgMap(msgMap, prcgPMsg)) {
                return null;
            }
        }

        NWZC157002PMsg prcgLinePMsg = prcgPMsg.NWZC157002PMsg.no(0);
        if (prcgLinePMsg.xxMsgIdList.getValidCount() > 0) {
            if (addToMyMsgMap(msgMap, prcgLinePMsg)) {
                return null;
            }
        }

        BigDecimal dealTaxAmt = BigDecimal.ZERO;
        for (int i = 0; i < prcgPMsg.NWZC157004PMsg.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(prcgPMsg.NWZC157004PMsg.no(i).xxTotTaxAmt)) {
                dealTaxAmt = dealTaxAmt.add(prcgPMsg.NWZC157004PMsg.no(i).xxTotTaxAmt.getValue());
            }
        }

        // Exchange Deal to Func
        BigDecimal funcTaxAmt = NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, prcgLinePMsg.ccyCd.getValue(), slsDt, dealTaxAmt);
        if (!ZYPCommonFunc.hasValue(funcTaxAmt)) {
            funcTaxAmt = BigDecimal.ZERO;
        }

        totTaxAmt[0] = dealTaxAmt;
        totTaxAmt[1] = funcTaxAmt;

        return totTaxAmt;
    }

    private void registTaxRecord(S21ApiMessageMap msgMap, BigDecimal dsContrPk, String cpoOrdNum, BigDecimal shellLineNum, NWZC157001PMsg prcgPMsgDtl, NWZC157001PMsg prcgPMsgAddl) {
        BigDecimal[] totTaxAmtDtl = new BigDecimal[] {BigDecimal.ZERO, BigDecimal.ZERO };
        if (prcgPMsgDtl != null) {
            totTaxAmtDtl = calcTotTaxAmt(msgMap, prcgPMsgDtl);
            if (totTaxAmtDtl == null) {
                return;
            }
        }

        BigDecimal[] totTaxAmtAddl = new BigDecimal[] {BigDecimal.ZERO, BigDecimal.ZERO };
        if (prcgPMsgAddl != null) {
            totTaxAmtAddl = calcTotTaxAmt(msgMap, prcgPMsgAddl);
            if (totTaxAmtAddl == null) {
                return;
            }
        }

        BigDecimal dealTotTaxAmt = BigDecimal.ZERO;
        dealTotTaxAmt = totTaxAmtDtl[0].add(totTaxAmtAddl[0]);

        BigDecimal funcTotTaxAmt = BigDecimal.ZERO;
        funcTotTaxAmt = totTaxAmtDtl[1].add(totTaxAmtAddl[1]);

        // Registration DS Contract TAX Detail
        DS_CONTR_TAX_DTLTMsg dsContrTaxDtlTMsg = new DS_CONTR_TAX_DTLTMsg();
        dsContrTaxDtlTMsg.setSQLID("001");
        dsContrTaxDtlTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        dsContrTaxDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        dsContrTaxDtlTMsg.setConditionValue("dsContrTaxLineNum01", shellLineNum);

        DS_CONTR_TAX_DTLTMsgArray dsContrTaxDtlTMsgAry = (DS_CONTR_TAX_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrTaxDtlTMsg);

        if (dsContrTaxDtlTMsgAry.length() > 0) {
            dsContrTaxDtlTMsg = dsContrTaxDtlTMsgAry.no(0);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.dsContrTaxPct, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.dealDsContrTaxAmt, dealTotTaxAmt);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.funcDsContrTaxAmt, funcTotTaxAmt);
            S21FastTBLAccessor.update(dsContrTaxDtlTMsg);
        } else {
            dsContrTaxDtlTMsg = new DS_CONTR_TAX_DTLTMsg();
            BigDecimal dsContrTaxDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_TAX_DTL_SQ);

            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.dsContrTaxDtlPk, dsContrTaxDtlPk);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.dsContrPk, dsContrPk);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.cpoOrdNum, cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.dsContrTaxLineNum, shellLineNum);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.dsContrTaxPct, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.dealDsContrTaxAmt, dealTotTaxAmt);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.funcDsContrTaxAmt, funcTotTaxAmt);

            S21FastTBLAccessor.insert(dsContrTaxDtlTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrTaxDtlTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1249E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private void registSvcTermCond(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<SVC_TERM_CONDTMsg> svcTermCondTMsgNewList = new ArrayList<SVC_TERM_CONDTMsg>();
        List<SVC_TERM_CONDTMsg> svcTermCondTMsgDelList = new ArrayList<SVC_TERM_CONDTMsg>();
        String slsDt = inPrmPMsg.slsDt.getValue();

        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);

            boolean addContrFlg = false;
            if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk_AD)) {
                addContrFlg = true;
            }

            BigDecimal dsContrPk = svcDtlPMsg.dsContrPk.getValue();
            if (RQST_TP_NEW.equals(svcDtlPMsg.xxRqstTpCd.getValue()) && !addContrFlg) {
                // Default Term&Condition
                // Contract Level
                if (!NSXC002001SvcTermCond.isExistTermCondForContrLvl(this.glblCmpyCd, dsContrPk)) {
                    List<DefSvcTermCondInfoBean> defSvcTermCondInfoBeanList = NSXC002001SvcTermCond.getTermCondInfoForContrLvl(this.glblCmpyCd, slsDt, svcDtlPMsg.svcPgmMdseCd.getValue());
                    // 2018/05/07 QC#22482 Add Start
                    if (defSvcTermCondInfoBeanList == null) {
                        continue;
                    }
                    // 2018/05/07 QC#22482 Add End
                    for (DefSvcTermCondInfoBean defSvcTermCondInfoBean : defSvcTermCondInfoBeanList) {
                        SVC_TERM_CONDTMsg svcTermCondTMsg = createSvcTermCondTMsg(dsContrPk, null, defSvcTermCondInfoBean.getSvcTermCondAttrbPk(), defSvcTermCondInfoBean.getSvcTermAttrbMapValCd());
                        svcTermCondTMsgNewList.add(svcTermCondTMsg);
                    }
                }
            } else if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                if (this.delShellLineNumListForAddContr.contains(svcDtlPMsg.shellLineNum.getValue())) {
                    continue;
                }

                setSvcTermCondForHdr(svcTermCondTMsgDelList, dsContrPk);
            }
        }

        BigDecimal capQtySvcTermCondAttrbPk = ZYPCodeDataUtil.getNumConstValue("SVC_TERM_COND_ATTR_CAP_QTY_PK", this.glblCmpyCd); // QC#20336(L3#397)

        Map<String, List<DefSvcTermCondInfoBean>> defSvcTermCondMap = new HashMap<String, List<DefSvcTermCondInfoBean>>();
        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcConfigRefList.getValidCount(); i++) {
            svcConfigRefPMsg = inPrmPMsg.svcConfigRefList.no(i);
            svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, svcConfigRefPMsg.shellLineNum.getValue());

            if (ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.addAsryFlg.getValue())) {
                if (!isExistsMainMachine(svcConfigRefPMsg, inPrmPMsg)) {
                    continue;
                }
            }

            BigDecimal dsContrPk = svcConfigRefPMsg.dsContrPk.getValue();
            BigDecimal dsContrDtlPk = svcConfigRefPMsg.dsContrDtlPk.getValue();

            boolean isQC = SPLY_AGMT_DOC_TP.QUANTITY_CONTRACT.equals(getSplyAgmtDocTp(inPrmPMsg,svcConfigRefPMsg,svcDtlPMsg)); // QC#20336(L3#397)

            if (RQST_TP_NEW.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                // Machine Level
                if (!NSXC002001SvcTermCond.isExistTermCondForMachLvl(this.glblCmpyCd, dsContrPk, dsContrDtlPk)) {
                    String svcPgmMdseCd = svcDtlPMsg.svcPgmMdseCd.getValue();

                    if (ZYPCommonFunc.hasValue(svcPgmMdseCd)) {
                        List<DefSvcTermCondInfoBean> defSvcTermCondInfoBeanList = null;
                        if (defSvcTermCondMap.containsKey(svcPgmMdseCd)) {
                            defSvcTermCondInfoBeanList = defSvcTermCondMap.get(svcPgmMdseCd);
                        } else {
                            defSvcTermCondInfoBeanList = NSXC002001SvcTermCond.getTermCondInfoForMachLvl(this.glblCmpyCd, slsDt, svcPgmMdseCd);
                            defSvcTermCondMap.put(svcPgmMdseCd, defSvcTermCondInfoBeanList);
                        }

                        for (DefSvcTermCondInfoBean defSvcTermCondInfoBean : defSvcTermCondInfoBeanList) {
                            SVC_TERM_CONDTMsg svcTermCondTMsg = createSvcTermCondTMsg(dsContrPk, dsContrDtlPk, defSvcTermCondInfoBean.getSvcTermCondAttrbPk(), defSvcTermCondInfoBean.getSvcTermAttrbMapValCd());
                            // QC#20336(L3#397)
                            if (isQC && ZYPCommonFunc.hasValue(capQtySvcTermCondAttrbPk) //
                                    && capQtySvcTermCondAttrbPk.compareTo(svcTermCondTMsg.svcTermCondAttrbPk.getValue()) == 0) {
                                updateCapQtyPkMap.put(svcTermCondTMsg.svcTermCondPk.getValue(), dsContrDtlPk);
                            }
                            svcTermCondTMsgNewList.add(svcTermCondTMsg);
                        }
                    }
                }
            } else if (RQST_TP_DEL.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                if (svcDtlPMsg != null && RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                    continue;
                }

                setSvcTermCondForDtl(svcTermCondTMsgDelList, dsContrDtlPk);
            // 2018/09/10 S21_NA#28104 Add Start
            } else if (RQST_TP_MOD.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                BigDecimal svcTermCondpk = getSvcTermCondPk(dsContrPk, dsContrDtlPk, capQtySvcTermCondAttrbPk);
                if (isQC && ZYPCommonFunc.hasValue(svcTermCondpk)) {
                    updateCapQtyPkMap.put(svcTermCondpk, dsContrDtlPk);
                }
            }
            //  2018/09/10 S21_NA#28104 Add End
        }

        if (!svcTermCondTMsgNewList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(svcTermCondTMsgNewList.toArray(new SVC_TERM_CONDTMsg[0]));
            if (result != svcTermCondTMsgNewList.size()) {
                setErrStatus(msgMap, NSZM1241E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        if (!svcTermCondTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(svcTermCondTMsgDelList.toArray(new SVC_TERM_CONDTMsg[0]));
            if (result != svcTermCondTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1242E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    // QC#20336(L3#397)
    private String getSplyAgmtDocTp(NSZC115001PMsg inPrmPMsg, NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg) {

        // 2018/05/07 QC#22482 Add Start
        if (!ZYPCommonFunc.hasValue(svcDtlPMsg.fromPerMthNum) || !ZYPCommonFunc.hasValue(svcDtlPMsg.toPerMthNum)) {
            return null;
        }
        // 2018/05/07 QC#22482 Add End

        NSZC115001_svcPrcListPMsg svcPrcPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcPrcList.getValidCount(); i++) {
            if (svcConfigRefPMsg.shellLineNum.getValue().equals(inPrmPMsg.svcPrcList.no(i).shellLineNum.getValue()) //
                    && svcConfigRefPMsg.dsOrdPosnNum.getValue().equals(inPrmPMsg.svcPrcList.no(i).dsOrdPosnNum.getValue())) {
                svcPrcPMsg = inPrmPMsg.svcPrcList.no(i);
                break;
            }
        }
        if (svcPrcPMsg == null) {
            return null;
        }
        NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg = new NSZC115001_svcUsgPrcListPMsg();
        for (int i = 0; i < inPrmPMsg.svcUsgPrcList.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(inPrmPMsg.svcUsgPrcList.no(i).actvFlg.getValue())) {
                continue;
            }
            if (svcPrcPMsg.dsOrdPosnNum.getValue().equals(inPrmPMsg.svcUsgPrcList.no(i).dsOrdPosnNum.getValue())) {
                svcUsgPrcPMsg = inPrmPMsg.svcUsgPrcList.no(i);
                break;
            }
        }
        if (svcUsgPrcPMsg == null) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("mdlId", svcPrcPMsg.mdlId);
        param.put("prcMtrPkgPk", svcPrcPMsg.prcMtrPkgPk);
        param.put("prcSvcPlnTpCd", svcDtlPMsg.prcSvcPlnTpCd);
        param.put("prcSvcContrTpCd", svcDtlPMsg.prcSvcContrTpCd);
        param.put("bllgMtrLbCd", svcUsgPrcPMsg.bllgMtrLbCd);
        param.put("prcCatgCd", svcPrcPMsg.maintPrcCatgCd);
        param.put("slsDt", inPrmPMsg.slsDt);
        param.put("termMthAot", svcDtlPMsg.toPerMthNum.getValue().subtract(svcDtlPMsg.fromPerMthNum.getValue()).add(BigDecimal.ONE));

        return (String) this.ssmBatchClient.queryObject("getSplyAgmtDocTpCd", param);
    }

    // QC#20336(L3#397)
    private BigDecimal getQtyContrCapQty(BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);

        return (BigDecimal) this.ssmBatchClient.queryObject("getQtyContrCapQty", param);
    }

    private SVC_TERM_CONDTMsg createSvcTermCondTMsg(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal svcTermCondAttrbPk, String svcTermAttrbMapValCd) {
        BigDecimal svcTermCondPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_SQ);
        SVC_TERM_CONDTMsg svcTermCondTMsg = new SVC_TERM_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.svcTermCondPk, svcTermCondPk);
        ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.svcTermCondAttrbPk, svcTermCondAttrbPk);
        ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.svcTermAttrbMapValCd, svcTermAttrbMapValCd);
        return svcTermCondTMsg;
    }

    private void setSvcTermCondForHdr(List<SVC_TERM_CONDTMsg> svcTermCondTMsgList, BigDecimal dsContrPk) {
        SVC_TERM_CONDTMsg svcTermCondTMsg = new SVC_TERM_CONDTMsg();
        svcTermCondTMsg.setSQLID("201");
        svcTermCondTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        svcTermCondTMsg.setConditionValue("dsContrPk01", dsContrPk);

        SVC_TERM_CONDTMsgArray svcTermCondTMsgArray = (SVC_TERM_CONDTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(svcTermCondTMsg);
        if (svcTermCondTMsgArray.length() == 0) {
            return;
        }

        for (int i = 0; i < svcTermCondTMsgArray.getValidCount(); i++) {
            svcTermCondTMsgList.add(svcTermCondTMsgArray.no(i));
        }
    }

    private void setSvcTermCondForDtl(List<SVC_TERM_CONDTMsg> svcTermCondTMsgList, BigDecimal dsContrDtlPk) {
        SVC_TERM_CONDTMsg svcTermCondTMsg = new SVC_TERM_CONDTMsg();
        svcTermCondTMsg.setSQLID("005");
        svcTermCondTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        svcTermCondTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);

        SVC_TERM_CONDTMsgArray svcTermCondTMsgArray = (SVC_TERM_CONDTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(svcTermCondTMsg);
        if (svcTermCondTMsgArray.length() == 0) {
            return;
        }

        for (int i = 0; i < svcTermCondTMsgArray.getValidCount(); i++) {
            svcTermCondTMsgList.add(svcTermCondTMsgArray.no(i));
        }
    }
    
    // 2018/09/10 S21_NA#28104 Add Start
    private BigDecimal getSvcTermCondPk(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal capQtySvcTermCondAttrbPk){
        SVC_TERM_CONDTMsg svcTermCondTMsg = new SVC_TERM_CONDTMsg();
        svcTermCondTMsg.setSQLID("001");
        svcTermCondTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        svcTermCondTMsg.setConditionValue("dsContrPk01", dsContrPk);
        svcTermCondTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        svcTermCondTMsg.setConditionValue("svcTermCondAttrbPk01", capQtySvcTermCondAttrbPk);
        
        SVC_TERM_CONDTMsgArray svcTermCondTMsgArray = (SVC_TERM_CONDTMsgArray) S21ApiTBLAccessor.findByCondition(svcTermCondTMsg);
        if (svcTermCondTMsgArray.length() == 0) {
            return null;
        } else{
            return svcTermCondTMsgArray.no(0).svcTermCondPk.getValue();
        }
    }
    // 2018/09/10 S21_NA#28104 Add End
    
    private void registDsContrCrCardPo(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgNewList = new ArrayList<DS_CONTR_CR_CARD_POTMsg>();
        List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgModList = new ArrayList<DS_CONTR_CR_CARD_POTMsg>();
        List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgDelList = new ArrayList<DS_CONTR_CR_CARD_POTMsg>();
        // START 2019/01/10 S.Kitamura[QC#26928, MOD]
        // Map<BigDecimal, DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgMapForHdr = new HashMap<BigDecimal, DS_CONTR_CR_CARD_POTMsg>();
        // Map<BigDecimal, DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgMapForAcc = new HashMap<BigDecimal, DS_CONTR_CR_CARD_POTMsg>();
        Map<BigDecimal, DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgPoMapForHdr = new HashMap<BigDecimal, DS_CONTR_CR_CARD_POTMsg>();
        Map<BigDecimal, DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgPoMapForAcc = new HashMap<BigDecimal, DS_CONTR_CR_CARD_POTMsg>();
        Map<BigDecimal, DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgCrCardMapForHdr = new HashMap<BigDecimal, DS_CONTR_CR_CARD_POTMsg>();
        Map<BigDecimal, DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgCrCardMapForAcc = new HashMap<BigDecimal, DS_CONTR_CR_CARD_POTMsg>();
        // END 2019/01/10 S.Kitamura[QC#26928, MOD]
        String crCardCustRefNum = null;
        String crCardExprYrMth = null;
        DS_CR_CARDTMsg dsCrCardTMsg = getDsCrCardTMsg(inPrmPMsg);
        if (dsCrCardTMsg != null) {
            crCardCustRefNum = dsCrCardTMsg.crCardCustRefNum.getValue();
            // START 2017/06/19 [QC#19324, MOD]
            crCardExprYrMth = dsCrCardTMsg.crCardExprYrMth.getValue();
            // END 2017/06/19 [QC#19324, MOD]
        }

        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;
        NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg = null;

        // DS_CONTR_CR_CARD_PO(Machine)
        for (int i = 0; i < inPrmPMsg.svcConfigRefList.getValidCount(); i++) {
            svcConfigRefPMsg = inPrmPMsg.svcConfigRefList.no(i);
            svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, svcConfigRefPMsg.shellLineNum.getValue());

            BigDecimal dsContrPk = svcConfigRefPMsg.dsContrPk.getValue();
            BigDecimal dsContrDtlPk = svcConfigRefPMsg.dsContrDtlPk.getValue();
            String custIssPoNum = svcConfigRefPMsg.custIssPoNum.getValue();
            // START 2019/01/18 [QC#29081, DEL]
//            // START 2017/08/02 [QC#19792, ADD]
//            if (ZYPCommonFunc.hasValue(custIssPoNum) && custIssPoNum.length() > MAX_LENGTH_CUST_ISS_PO_NUM) {
//                custIssPoNum = custIssPoNum.substring(0, MAX_LENGTH_CUST_ISS_PO_NUM);
//            }
//            // END   2017/08/02 [QC#19792, ADD]
            // END   2019/01/18 [QC#29081, DEL]
            String custIssPoDt = svcConfigRefPMsg.custIssPoDt.getValue();
            // 2018/04/09 QC#20162 add start
            String dsPoExprDt = svcConfigRefPMsg.dsPoExprDt.getValue();
            // 2018/04/09 QC#20162 add end

            DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
            if (RQST_TP_NEW.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                // START 2019/01/10 S.Kitamura[QC#26928, MOD]
                // if (!needToRegistCrCardPo(crCardCustRefNum, crCardExprYrMth, custIssPoNum, custIssPoDt)) {
                //    continue;
                // }
                // 2018/04/09 QC#20162 mod start
//              dsContrCrCardPoTMsg = createDsContrCrCardPoTMsgLvl2(dsContrPk, dsContrDtlPk, crCardCustRefNum, crCardExprYrMth, custIssPoNum, custIssPoDt, svcDtlPMsg);
                // dsContrCrCardPoTMsg = createDsContrCrCardPoTMsgLvl2(dsContrPk, dsContrDtlPk, crCardCustRefNum, crCardExprYrMth, custIssPoNum, custIssPoDt, svcDtlPMsg, dsPoExprDt);
                // 2018/04/09 QC#20162 mod end
                // if (!dsContrCrCardPoTMsgMapForHdr.containsKey(dsContrPk)) {
                //     dsContrCrCardPoTMsgMapForHdr.put(dsContrPk, dsContrCrCardPoTMsg);
                // }
                // dsContrCrCardPoTMsgMapForAcc.put(dsContrDtlPk, dsContrCrCardPoTMsg);
                // dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsg);
                if (needToRegistPo(custIssPoNum, dsPoExprDt)) {
                    createDsContrCrCardPoTMsgLv2ForPo(dsContrPk, dsContrDtlPk, custIssPoNum, custIssPoDt, svcDtlPMsg, dsPoExprDt, dsContrCrCardPoTMsgPoMapForHdr, 
                                                      dsContrCrCardPoTMsgPoMapForAcc, dsContrCrCardPoTMsgNewList);
                }
                if (needToRegistCrCard(crCardCustRefNum, crCardExprYrMth)) {
                    createDsContrCrCardPoTMsgLv2ForCrCard(dsContrPk, dsContrDtlPk, crCardCustRefNum, crCardExprYrMth, svcDtlPMsg, dsContrCrCardPoTMsgCrCardMapForHdr, 
                                                          dsContrCrCardPoTMsgCrCardMapForAcc, dsContrCrCardPoTMsgNewList);
                }
                // END 2019/01/10 S.Kitamura[QC#26928, MOD]
            } else if (RQST_TP_MOD.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                // START 2019/05/23 W.Honda[QC#50157, MOD]
                // START 2019/01/10 S.Kitamura[QC#26928, MOD]
                // dsContrCrCardPoTMsg.setSQLID("201");
                // dsContrCrCardPoTMsg.setSQLID("202");
                // dsContrCrCardPoTMsg.setConditionValue("poFromDt01", ZYPDateUtil.getSalesDate());
                // dsContrCrCardPoTMsg.setConditionValue("poDt01", ZYPDateUtil.getSalesDate());
                // END 2019/01/10 S.Kitamura[QC#26928, MOD]
                // dsContrCrCardPoTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                // dsContrCrCardPoTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
                // DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsg);
                BigDecimal dsContrCrCardPoPk = getExistCrCardPO(dsContrPk, dsContrDtlPk);
                // if (dsContrCrCardPoTMsgArray.getValidCount() == 0) {
                if (!ZYPCommonFunc.hasValue(dsContrCrCardPoPk)) {
                    // START 2019/01/10 S.Kitamura[QC#26928, MOD]
                    // if (!needToRegistCrCardPo(crCardCustRefNum, crCardExprYrMth, custIssPoNum, custIssPoDt)) {
                    //     continue;
                    // }

                    // 2018/04/09 QC#20162 mod start
//                    dsContrCrCardPoTMsg = createDsContrCrCardPoTMsgLvl2(dsContrPk, dsContrDtlPk, crCardCustRefNum, crCardExprYrMth, custIssPoNum, custIssPoDt, svcDtlPMsg);
                    // dsContrCrCardPoTMsg = createDsContrCrCardPoTMsgLvl2(dsContrPk, dsContrDtlPk, crCardCustRefNum, crCardExprYrMth, custIssPoNum, custIssPoDt, svcDtlPMsg, dsPoExprDt);
                    // 2018/04/09 QC#20162 mod end

                    // if (!dsContrCrCardPoTMsgMapForHdr.containsKey(dsContrPk)) {
                    //     dsContrCrCardPoTMsgMapForHdr.put(dsContrPk, dsContrCrCardPoTMsg);
                    // }
                    // dsContrCrCardPoTMsgMapForAcc.put(dsContrDtlPk, dsContrCrCardPoTMsg);
                    // dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsg);
                    if (needToRegistPo(custIssPoNum, dsPoExprDt)) {
                        createDsContrCrCardPoTMsgLv2ForPo(dsContrPk, dsContrDtlPk, custIssPoNum, custIssPoDt, svcDtlPMsg, dsPoExprDt, dsContrCrCardPoTMsgPoMapForHdr, 
                                                          dsContrCrCardPoTMsgPoMapForAcc, dsContrCrCardPoTMsgNewList);
                    }
                    // END 2019/01/10 S.Kitamura[QC#26928, MOD]
                } else {
                    // START 2019/01/10 S.Kitamura[QC#26928, MOD]
                    // if (!needToRegistCrCardPo(crCardCustRefNum, crCardExprYrMth, custIssPoNum, custIssPoDt)) {
                    //     dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArray.no(0));
                    //     continue;
                    // }
                    dsContrCrCardPoTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                    dsContrCrCardPoTMsg.dsContrCrCardPoPk.setValue(dsContrCrCardPoPk);
                    dsContrCrCardPoTMsg = (DS_CONTR_CR_CARD_POTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrCrCardPoTMsg);
                    if (needToRegistPo(custIssPoNum, dsPoExprDt)) {
//                        dsContrCrCardPoTMsg = dsContrCrCardPoTMsgArray.no(0);
                        if (!dsContrCrCardPoTMsgPoMapForHdr.containsKey(dsContrPk)) {
                            dsContrCrCardPoTMsgPoMapForHdr.put(dsContrPk, dsContrCrCardPoTMsg);
                        }
                        dsContrCrCardPoTMsgPoMapForAcc.put(dsContrDtlPk, dsContrCrCardPoTMsg);
                        // 2018/04/09 QC#20162 mod start
//                        modDsContrCrCardPo(dsContrCrCardPoTMsgModList, crCardCustRefNum, crCardExprYrMth, custIssPoNum, custIssPoDt, svcDtlPMsg, dsContrCrCardPoTMsg);
                        modDsContrCrCardPo(dsContrCrCardPoTMsgModList, null, null, custIssPoNum, custIssPoDt, svcDtlPMsg, dsContrCrCardPoTMsg, dsPoExprDt);
                        // 2018/04/09 QC#20162 mod end
                    } else {
//                        dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArray.no(0));
                        dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsg);
                    }
                    // END 2019/01/10 S.Kitamura[QC#26928, MOD]
                }
                // START 2019/01/10 S.Kitamura[QC#26928, ADD]
                DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgCrCard = new DS_CONTR_CR_CARD_POTMsg();
                dsContrCrCardPoTMsgCrCard.setSQLID("203");
                dsContrCrCardPoTMsgCrCard.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                dsContrCrCardPoTMsgCrCard.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
                DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsgCrCard);
                if (dsContrCrCardPoTMsgArray.getValidCount() == 0) {
                    if (needToRegistCrCard(crCardCustRefNum, crCardExprYrMth)) {
                        createDsContrCrCardPoTMsgLv2ForCrCard(dsContrPk, dsContrDtlPk, crCardCustRefNum, crCardExprYrMth, svcDtlPMsg, dsContrCrCardPoTMsgCrCardMapForHdr, dsContrCrCardPoTMsgCrCardMapForAcc, dsContrCrCardPoTMsgNewList);
                    }
                } else {
                    if (needToRegistCrCard(crCardCustRefNum, crCardExprYrMth)) {
                        dsContrCrCardPoTMsgCrCard = dsContrCrCardPoTMsgArray.no(0);
                        if (!dsContrCrCardPoTMsgCrCardMapForHdr.containsKey(dsContrPk)) {
                            dsContrCrCardPoTMsgCrCardMapForHdr.put(dsContrPk, dsContrCrCardPoTMsgCrCard);
                        }
                        dsContrCrCardPoTMsgCrCardMapForAcc.put(dsContrDtlPk, dsContrCrCardPoTMsgCrCard);
                        modDsContrCrCardPo(dsContrCrCardPoTMsgModList, crCardCustRefNum, crCardExprYrMth, null, null, svcDtlPMsg, dsContrCrCardPoTMsgCrCard, null);
                    } else {
                        dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArray.no(0));
                    }
                }
                // END 2019/01/10 S.Kitamura[QC#26928, ADD]
                // END 2019/05/23 W.Honda[QC#50157, MOD]
            } else if (RQST_TP_DEL.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                if (svcDtlPMsg != null && RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                    continue;
                }

                dsContrCrCardPoTMsg.setSQLID("003");
                dsContrCrCardPoTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                dsContrCrCardPoTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
                DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsg);
                for (int j = 0; j < dsContrCrCardPoTMsgArray.getValidCount(); j++) {
                    dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArray.no(j));
                }
            }
        }

        if (inPrmPMsg.svcAddlBasePrcList.getValidCount() == 0) {
            setUpdateAsryDsContrCrCardPo(msgMap, dsContrCrCardPoTMsgModList, dsContrCrCardPoTMsgDelList);
        }

        // DS_CONTR_CR_CARD_PO(Accessory)
        for (int i = 0; i < inPrmPMsg.svcAddlBasePrcList.getValidCount(); i++) {
            svcAddlBasePrcPMsg = inPrmPMsg.svcAddlBasePrcList.no(i);

            if (!RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue()) && !ADDL_CHRG_CATG.ACCESSORY.equals(svcAddlBasePrcPMsg.addlChrgCatgCd.getValue())) {
                continue;
            } else if (RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue()) && !ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.dsContrDtlPk)) {
                continue;
            }

            BigDecimal shellLineNum = svcAddlBasePrcPMsg.shellLineNum.getValue();
            BigDecimal dsContrPk = svcAddlBasePrcPMsg.dsContrPk.getValue();
            BigDecimal dsContrDtlPk = svcAddlBasePrcPMsg.dsContrDtlPk.getValue();
            BigDecimal svcConfigMstrPk = null;
            BigDecimal prntDsContrDtlPk = null;

            if (!RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                cpoDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                cpoDtlTMsg.cpoOrdNum.setValue(svcAddlBasePrcPMsg.cpoOrdNum.getValue());
                cpoDtlTMsg.cpoDtlLineNum.setValue(svcAddlBasePrcPMsg.cpoDtlLineNum.getValue());
                cpoDtlTMsg.cpoDtlLineSubNum.setValue(svcAddlBasePrcPMsg.cpoDtlLineSubNum.getValue());
                svcConfigMstrPk = getSvcConfigMstrPkByCpoDtl(cpoDtlTMsg);

                dsContrPk = getDsContrPk(inPrmPMsg.svcDtlList, shellLineNum, dsContrPk);
                svcConfigRefPMsg = getSvcConfigRefListItem(inPrmPMsg.svcConfigRefList, shellLineNum, svcConfigMstrPk);
                svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, shellLineNum);
                
                // mod start 2017/06/22 CSA Defect#19330
                //if (ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.addAsryFlg.getValue())) {
                //    prntDsContrDtlPk = getPrntDsContrDtlPk(dsContrPk, svcConfigMstrPk);
                //} else {
                //    prntDsContrDtlPk = svcConfigRefPMsg.dsContrDtlPk.getValue();
                //}
                if (ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.addAsryFlg.getValue())) {
                    continue;
                }
                prntDsContrDtlPk = svcConfigRefPMsg.dsContrDtlPk.getValue();
                // mod end 2017/06/22 CSA Defect#19330

            }

            DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
            if (RQST_TP_NEW.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                // START 2019/01/10 S.Kitamura[QC#26928, MOD]
                if (dsContrCrCardPoTMsgPoMapForAcc.containsKey(prntDsContrDtlPk)) {
                    DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForAcc = dsContrCrCardPoTMsgPoMapForAcc.get(prntDsContrDtlPk);
                    EZDMsg.copy(dsContrCrCardPoTMsgForAcc, null, dsContrCrCardPoTMsg, null);
                    BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrDtlPk, dsContrDtlPk);
                    dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsg);
                }
                DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgCrCard = new DS_CONTR_CR_CARD_POTMsg();
                if (dsContrCrCardPoTMsgCrCardMapForAcc.containsKey(prntDsContrDtlPk)) {
                    DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForAcc = dsContrCrCardPoTMsgCrCardMapForAcc.get(prntDsContrDtlPk);
                    EZDMsg.copy(dsContrCrCardPoTMsgForAcc, null, dsContrCrCardPoTMsgCrCard, null);
                    BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgCrCard.dsContrCrCardPoPk, dsContrCrCardPoPk);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgCrCard.dsContrDtlPk, dsContrDtlPk);
                    dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsgCrCard);
                }
                // END 2019/01/10 S.Kitamura[QC#26928, MOD]
            } else if (RQST_TP_MOD.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                // START 2019/05/23 W.Honda[QC#50157, MOD]
                // START 2019/01/10 S.Kitamura[QC#26928, MOD]
                // dsContrCrCardPoTMsg.setSQLID("201");
                // dsContrCrCardPoTMsg.setSQLID("202");
                // dsContrCrCardPoTMsg.setConditionValue("poFromDt01", ZYPDateUtil.getSalesDate());
                // dsContrCrCardPoTMsg.setConditionValue("poDt01", ZYPDateUtil.getSalesDate());
                // END 2019/01/10 S.Kitamura[QC#26928, MOD]
                // dsContrCrCardPoTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                // dsContrCrCardPoTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
                // DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsg);
                BigDecimal dsContrCrCardPoPkMod = getExistCrCardPO(dsContrPk, dsContrDtlPk);
                // if (dsContrCrCardPoTMsgArray.getValidCount() == 0) {
                if (!ZYPCommonFunc.hasValue(dsContrCrCardPoPkMod)) {
                    // START 2019/01/10 S.Kitamura[QC#26928, MOD]
                    if (dsContrCrCardPoTMsgPoMapForAcc.containsKey(prntDsContrDtlPk)) {
                        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForAcc = dsContrCrCardPoTMsgPoMapForAcc.get(prntDsContrDtlPk);
                        EZDMsg.copy(dsContrCrCardPoTMsgForAcc, null, dsContrCrCardPoTMsg, null);
                        BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
                        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
                        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrDtlPk, dsContrDtlPk);
                        dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsg);
                    }
                    // END 2019/01/10 S.Kitamura[QC#26928, MOD]
                } else {
                    // START 2019/01/10 S.Kitamura[QC#26928, MOD]
                    dsContrCrCardPoTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                    dsContrCrCardPoTMsg.dsContrCrCardPoPk.setValue(dsContrCrCardPoPkMod);
                    dsContrCrCardPoTMsg = (DS_CONTR_CR_CARD_POTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrCrCardPoTMsg);
                    if (dsContrCrCardPoTMsgPoMapForAcc.containsKey(prntDsContrDtlPk)) {
                        // dsContrCrCardPoTMsg = dsContrCrCardPoTMsgArray.no(0);
                        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForAcc = dsContrCrCardPoTMsgPoMapForAcc.get(prntDsContrDtlPk);
                        // 2018/04/09 QC#20162 mod start
//                        modDsContrCrCardPo(dsContrCrCardPoTMsgModList, dsContrCrCardPoTMsgForAcc.crCardCustRefNum.getValue(), dsContrCrCardPoTMsgForAcc.crCardExprYrMth.getValue(), dsContrCrCardPoTMsgForAcc.custPoNum.getValue(), dsContrCrCardPoTMsgForAcc.poDt.getValue(), svcDtlPMsg, dsContrCrCardPoTMsg);
                        //modDsContrCrCardPo(dsContrCrCardPoTMsgModList, dsContrCrCardPoTMsgForAcc.crCardCustRefNum.getValue(), dsContrCrCardPoTMsgForAcc.crCardExprYrMth.getValue(), dsContrCrCardPoTMsgForAcc.custPoNum.getValue(), dsContrCrCardPoTMsgForAcc.custIssPoDt.getValue(), svcDtlPMsg, dsContrCrCardPoTMsg, dsContrCrCardPoTMsgForAcc.poDt.getValue());
                        modDsContrCrCardPo(dsContrCrCardPoTMsgModList, null, null, dsContrCrCardPoTMsgForAcc.custPoNum.getValue(), dsContrCrCardPoTMsgForAcc.custIssPoDt.getValue(), svcDtlPMsg, dsContrCrCardPoTMsg, dsContrCrCardPoTMsgForAcc.poDt.getValue());
                        // 2018/04/09 QC#20162 mod end
                    } else {
                        // dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArray.no(0));
                        dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsg);
                    }
                    // END 2019/01/10 S.Kitamura[QC#26928, MOD]
                }
                // START 2019/01/10 S.Kitamura[QC#26928, ADD]
                DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgCrCard = new DS_CONTR_CR_CARD_POTMsg();
                dsContrCrCardPoTMsgCrCard.setSQLID("203");
                dsContrCrCardPoTMsgCrCard.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                dsContrCrCardPoTMsgCrCard.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
                // dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsgCrCard);
                DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsgCrCard);
                if (dsContrCrCardPoTMsgArray.getValidCount() == 0) {
                    if (dsContrCrCardPoTMsgCrCardMapForAcc.containsKey(prntDsContrDtlPk)) {
                        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForAcc = dsContrCrCardPoTMsgCrCardMapForAcc.get(prntDsContrDtlPk);
                        EZDMsg.copy(dsContrCrCardPoTMsgForAcc, null, dsContrCrCardPoTMsgCrCard, null);
                        BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
                        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgCrCard.dsContrCrCardPoPk, dsContrCrCardPoPk);
                        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgCrCard.dsContrDtlPk, dsContrDtlPk);
                        dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsgCrCard);
                    }
                } else {
                    if (dsContrCrCardPoTMsgCrCardMapForAcc.containsKey(prntDsContrDtlPk)) {
                        dsContrCrCardPoTMsgCrCard = dsContrCrCardPoTMsgArray.no(0);
                        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForAcc = dsContrCrCardPoTMsgCrCardMapForAcc.get(prntDsContrDtlPk);
                        modDsContrCrCardPo(dsContrCrCardPoTMsgModList, dsContrCrCardPoTMsgForAcc.crCardCustRefNum.getValue(), dsContrCrCardPoTMsgForAcc.crCardExprYrMth.getValue(), null, null, svcDtlPMsg, dsContrCrCardPoTMsgCrCard, null);
                    } else {                        
                        dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArray.no(0));
                    }
                }
                // END 2019/01/10 S.Kitamura[QC#26928, ADD]
                // END 2019/05/23 W.Honda[QC#50157, MOD]
            } else if (RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                dsContrCrCardPoTMsg.setSQLID("003");
                dsContrCrCardPoTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                dsContrCrCardPoTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
                DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsg);
                for (int j = 0; j < dsContrCrCardPoTMsgArray.getValidCount(); j++) {
                    dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArray.no(j));
                }
            }
        }

        // DS_CONTR_CR_CARD_PO(Header)
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);

            boolean addContrFlg = false;
            if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk_AD)) {
                addContrFlg = true;
            }

            BigDecimal dsContrPk = svcDtlPMsg.dsContrPk.getValue();
            DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
            if (RQST_TP_NEW.equals(svcDtlPMsg.xxRqstTpCd.getValue()) && !addContrFlg) {
                // START 2019/01/10 S.Kitamura[QC#26928, MOD]
//              if (!dsContrCrCardPoTMsgMapForHdr.containsKey(dsContrPk)) {
//                  continue;
//              }
//              DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr = dsContrCrCardPoTMsgMapForHdr.get(dsContrPk);
//              EZDMsg.copy(dsContrCrCardPoTMsgForHdr, null, dsContrCrCardPoTMsg, null);
//              BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
//              ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
//              ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrMachLvlNum, "1");
//              dsContrCrCardPoTMsg.dsContrDtlPk.clear();
//              dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsg);
                if (dsContrCrCardPoTMsgPoMapForHdr.containsKey(dsContrPk)) {
                    DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr = dsContrCrCardPoTMsgPoMapForHdr.get(dsContrPk);
                    EZDMsg.copy(dsContrCrCardPoTMsgForHdr, null, dsContrCrCardPoTMsg, null);
                    BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrMachLvlNum, "1");
                    dsContrCrCardPoTMsg.dsContrDtlPk.clear();
                    dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsg);
                }
                DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgCrCard = new DS_CONTR_CR_CARD_POTMsg();
                if (dsContrCrCardPoTMsgCrCardMapForHdr.containsKey(dsContrPk)) {
                    DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr = dsContrCrCardPoTMsgCrCardMapForHdr.get(dsContrPk);
                    EZDMsg.copy(dsContrCrCardPoTMsgForHdr, null, dsContrCrCardPoTMsgCrCard, null);
                    BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgCrCard.dsContrCrCardPoPk, dsContrCrCardPoPk);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgCrCard.dsContrMachLvlNum, "1");
                    dsContrCrCardPoTMsgCrCard.dsContrDtlPk.clear();
                    dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsgCrCard);
                }
                // END 2019/01/10 S.Kitamura[QC#26928, MOD]
                // Fleet, Aggregate Line
                // START 2017/07/10 [QC#18985-2, DEL]
                // copyDsContrCrCardPoForFltAgg(dsContrCatgCd, dsContrCrCardPoTMsgNewList, inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.shellLineNum.getValue(), dsContrCrCardPoTMsgForHdr);
                // END   2017/07/10 [QC#18985-2, DEL]
            } else if (RQST_TP_MOD.equals(svcDtlPMsg.xxRqstTpCd.getValue()) || addContrFlg) {
                // START 2019/05/23 W.Honda[QC#50157, MOD]
                // START 2019/01/10 S.Kitamura[QC#26928, MOD]
                //dsContrCrCardPoTMsg.setSQLID("004");
                // dsContrCrCardPoTMsg.setSQLID("204");
                // dsContrCrCardPoTMsg.setConditionValue("poFromDt01", ZYPDateUtil.getSalesDate());
                // dsContrCrCardPoTMsg.setConditionValue("poDt01", ZYPDateUtil.getSalesDate());
                // END 2019/01/10 S.Kitamura[QC#26928, MOD]
                // dsContrCrCardPoTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                // dsContrCrCardPoTMsg.setConditionValue("dsContrPk01", dsContrPk);
                // DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsg);
                BigDecimal dsContrCrCardPoPkMod = getExistCrCardPO(dsContrPk, null);
                // if (dsContrCrCardPoTMsgArray.getValidCount() == 0) {
                if (!ZYPCommonFunc.hasValue(dsContrCrCardPoPkMod)) {
                    // START 2019/01/10 S.Kitamura[QC#26928, MOD]
//                    if (!dsContrCrCardPoTMsgMapForHdr.containsKey(dsContrPk)) {
//                        continue;
//                    }
//
//                    DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr = dsContrCrCardPoTMsgMapForHdr.get(dsContrPk);
//                    EZDMsg.copy(dsContrCrCardPoTMsgForHdr, null, dsContrCrCardPoTMsg, null);
//                    BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
//                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
//                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrMachLvlNum, "1");
//                    dsContrCrCardPoTMsg.dsContrDtlPk.clear();
//                    dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsg);                    
                    if (dsContrCrCardPoTMsgPoMapForHdr.containsKey(dsContrPk)) {
                        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr = dsContrCrCardPoTMsgPoMapForHdr.get(dsContrPk);
                        EZDMsg.copy(dsContrCrCardPoTMsgForHdr, null, dsContrCrCardPoTMsg, null);
                        BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
                        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
                        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrMachLvlNum, "1");
                        dsContrCrCardPoTMsg.dsContrDtlPk.clear();
                        dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsg);
                    }
                    // END 2019/01/10 S.Kitamura[QC#26928, MOD]
                    // Fleet, Aggregate
                    // START 2017/07/10 [QC#18985-2, DEL]
                    // copyDsContrCrCardPoForFltAgg(dsContrCatgCd, dsContrCrCardPoTMsgNewList, inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.shellLineNum.getValue(), dsContrCrCardPoTMsgForHdr);
                    // END   2017/07/10 [QC#18985-2, DEL]
                } else if (!addContrFlg) {
                    // START 2019/01/10 S.Kitamura[QC#26928, MOD]
//                    if (!dsContrCrCardPoTMsgMapForHdr.containsKey(dsContrPk)) {
//                        dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArray.no(0));
//                        // START 2017/06/20 [QC#18985-1, ADD]
//                        BigDecimal dsContrDtlPkForFltAgg = getFltAggDsContrDtlPkByDB(dsContrPk);
//                        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForFltAgg = new DS_CONTR_CR_CARD_POTMsg();
//                        dsContrCrCardPoTMsgForFltAgg.setSQLID("201");
//                        dsContrCrCardPoTMsgForFltAgg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//                        dsContrCrCardPoTMsgForFltAgg.setConditionValue("dsContrDtlPk01", dsContrDtlPkForFltAgg);
//                        DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArrayForFltAgg = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsgForFltAgg);
//                        if (dsContrCrCardPoTMsgArrayForFltAgg.getValidCount() > 0) {
//                            dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArrayForFltAgg.no(0));
//                        }
//                        // END   2017/06/20 [QC#18985-1, ADD]
//                        continue;
//                    }
//
//                    dsContrCrCardPoTMsg = dsContrCrCardPoTMsgArray.no(0);
//                    DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr = dsContrCrCardPoTMsgMapForHdr.get(dsContrPk);
//                    // 2018/04/09 QC#20162 mod start
////                    modDsContrCrCardPo(dsContrCrCardPoTMsgModList, dsContrCrCardPoTMsgForHdr.crCardCustRefNum.getValue(), dsContrCrCardPoTMsgForHdr.crCardExprYrMth.getValue(), dsContrCrCardPoTMsgForHdr.custPoNum.getValue(), dsContrCrCardPoTMsgForHdr.poDt.getValue(), svcDtlPMsg, dsContrCrCardPoTMsg);
//                    modDsContrCrCardPo(dsContrCrCardPoTMsgModList, dsContrCrCardPoTMsgForHdr.crCardCustRefNum.getValue(), dsContrCrCardPoTMsgForHdr.crCardExprYrMth.getValue(), dsContrCrCardPoTMsgForHdr.custPoNum.getValue(), dsContrCrCardPoTMsgForHdr.custIssPoDt.getValue(), svcDtlPMsg, dsContrCrCardPoTMsg, dsContrCrCardPoTMsgForHdr.poDt.getValue());
                    dsContrCrCardPoTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                    dsContrCrCardPoTMsg.dsContrCrCardPoPk.setValue(dsContrCrCardPoPkMod);
                    dsContrCrCardPoTMsg = (DS_CONTR_CR_CARD_POTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrCrCardPoTMsg);
                    if (dsContrCrCardPoTMsgPoMapForHdr.containsKey(dsContrPk)) {
                        // dsContrCrCardPoTMsg = dsContrCrCardPoTMsgArray.no(0);
                        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr = dsContrCrCardPoTMsgPoMapForHdr.get(dsContrPk);
                        // 2018/04/09 QC#20162 mod start
//                        modDsContrCrCardPo(dsContrCrCardPoTMsgModList, dsContrCrCardPoTMsgForHdr.crCardCustRefNum.getValue(), dsContrCrCardPoTMsgForHdr.crCardExprYrMth.getValue(), dsContrCrCardPoTMsgForHdr.custPoNum.getValue(), dsContrCrCardPoTMsgForHdr.poDt.getValue(), svcDtlPMsg, dsContrCrCardPoTMsg);
                        modDsContrCrCardPo(dsContrCrCardPoTMsgModList, null, null, dsContrCrCardPoTMsgForHdr.custPoNum.getValue(), dsContrCrCardPoTMsgForHdr.custIssPoDt.getValue(),
                                           svcDtlPMsg, dsContrCrCardPoTMsg, dsContrCrCardPoTMsgForHdr.poDt.getValue());
                    } else {
                        // dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArray.no(0));
                        dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsg);
                        BigDecimal dsContrDtlPkForFltAgg = getFltAggDsContrDtlPkByDB(dsContrPk);
                        if (ZYPCommonFunc.hasValue(dsContrDtlPkForFltAgg)) {
                            BigDecimal dsContrCrCardPoPkForFltAggMod = getExistCrCardPO(dsContrPk, dsContrDtlPkForFltAgg);
                            DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForFltAgg = new DS_CONTR_CR_CARD_POTMsg();
                            // dsContrCrCardPoTMsgForFltAgg.setSQLID("202");
                            // dsContrCrCardPoTMsgForFltAgg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                            // dsContrCrCardPoTMsgForFltAgg.setConditionValue("dsContrDtlPk01", dsContrDtlPkForFltAgg);
                            // dsContrCrCardPoTMsg.setConditionValue("poFromDt01", ZYPDateUtil.getSalesDate());
                            // dsContrCrCardPoTMsg.setConditionValue("poDt01", ZYPDateUtil.getSalesDate());
                            // DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArrayForFltAgg = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsgForFltAgg);
                            dsContrCrCardPoTMsgForFltAgg.glblCmpyCd.setValue(this.glblCmpyCd);
                            dsContrCrCardPoTMsgForFltAgg.dsContrCrCardPoPk.setValue(dsContrCrCardPoPkForFltAggMod);
                            dsContrCrCardPoTMsgForFltAgg = (DS_CONTR_CR_CARD_POTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrCrCardPoTMsgForFltAgg);
                            // if (dsContrCrCardPoTMsgArrayForFltAgg.getValidCount() > 0) {
                            //     dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArrayForFltAgg.no(0));
                            // }
                            if (dsContrCrCardPoTMsgForFltAgg != null) {
                                dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgForFltAgg);
                            }
                        }
                    }
                    // 2018/04/09 QC#20162 mod start
                    // END 2019/01/10 S.Kitamura[QC#26928, MOD]
                    // Fleet, Aggregate Line
                    // START 2017/07/10 [QC#18985-2, DEL]
                    // START 2017/06/15 [QC#18985, MOD]
                    // modDsContrCrCardPoForFltAgg(dsContrCatgCd, dsContrCrCardPoTMsgModList, svcDtlPMsg, inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.shellLineNum.getValue(), dsContrCrCardPoTMsgForHdr);
                    // modDsContrCrCardPoForFltAgg(dsContrCatgCd, dsContrCrCardPoTMsgNewList, dsContrCrCardPoTMsgModList, svcDtlPMsg, inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.shellLineNum.getValue(), dsContrCrCardPoTMsgForHdr);
                    // END   2017/06/15 [QC#18985, MOD]
                    // END   2017/07/10 [QC#18985-2, DEL]
                }
                // START 2019/01/10 S.Kitamura[QC#26928, ADD]
                // END 2019/05/23 W.Honda[QC#50157, MOD]
                DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgCrCard = new DS_CONTR_CR_CARD_POTMsg();
                dsContrCrCardPoTMsgCrCard.setSQLID("205");
                dsContrCrCardPoTMsgCrCard.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                dsContrCrCardPoTMsgCrCard.setConditionValue("dsContrPk01", dsContrPk);
                // dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsgCrCard);
                DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsgCrCard);
                if (dsContrCrCardPoTMsgArray.getValidCount() == 0) {
                    if (dsContrCrCardPoTMsgCrCardMapForHdr.containsKey(dsContrPk)) {
                        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr = dsContrCrCardPoTMsgCrCardMapForHdr.get(dsContrPk);
                        EZDMsg.copy(dsContrCrCardPoTMsgForHdr, null, dsContrCrCardPoTMsgCrCard, null);
                        BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
                        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgCrCard.dsContrCrCardPoPk, dsContrCrCardPoPk);
                        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgCrCard.dsContrMachLvlNum, "1");
                        dsContrCrCardPoTMsgCrCard.dsContrDtlPk.clear();
                        dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsgCrCard);
                    }
                } else if (!addContrFlg) {
                    if (dsContrCrCardPoTMsgCrCardMapForHdr.containsKey(dsContrPk)) {
                        dsContrCrCardPoTMsgCrCard = dsContrCrCardPoTMsgArray.no(0);
                        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr = dsContrCrCardPoTMsgCrCardMapForHdr.get(dsContrPk);
//                        modDsContrCrCardPo(dsContrCrCardPoTMsgModList, dsContrCrCardPoTMsgForHdr.crCardCustRefNum.getValue(), dsContrCrCardPoTMsgForHdr.crCardExprYrMth.getValue(), dsContrCrCardPoTMsgForHdr.custPoNum.getValue(), dsContrCrCardPoTMsgForHdr.poDt.getValue(), svcDtlPMsg, dsContrCrCardPoTMsg);
                        modDsContrCrCardPo(dsContrCrCardPoTMsgModList, dsContrCrCardPoTMsgForHdr.crCardCustRefNum.getValue(), dsContrCrCardPoTMsgForHdr.crCardExprYrMth.getValue(),
                                           null, null, svcDtlPMsg, dsContrCrCardPoTMsgCrCard, null);
                    } else {
                        dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArray.no(0));
                        BigDecimal dsContrDtlPkForFltAgg = getFltAggDsContrDtlPkByDB(dsContrPk);
                        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForFltAgg = new DS_CONTR_CR_CARD_POTMsg();
                        dsContrCrCardPoTMsgForFltAgg.setSQLID("203");
                        dsContrCrCardPoTMsgForFltAgg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                        dsContrCrCardPoTMsgForFltAgg.setConditionValue("dsContrDtlPk01", dsContrDtlPkForFltAgg);
                        DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArrayForFltAgg = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsgForFltAgg);
                        if (dsContrCrCardPoTMsgArrayForFltAgg.getValidCount() > 0) {
                            dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArrayForFltAgg.no(0));
                        }
                    }
                }
                // END 2019/01/10 S.Kitamura[QC#26928, ADD]                
            } else if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                if (this.delShellLineNumListForAddContr.contains(svcDtlPMsg.shellLineNum.getValue())) {
                    continue;
                }

                dsContrCrCardPoTMsg.setSQLID("001");
                dsContrCrCardPoTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                dsContrCrCardPoTMsg.setConditionValue("dsContrPk01", dsContrPk);
                DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsg);
                for (int j = 0; j < dsContrCrCardPoTMsgArray.getValidCount(); j++) {
                    dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArray.no(j));
                }
            }
        }

        // START 2017/07/10 [QC#18985-2, ADD]
        // DS_CONTR_CR_CARD_PO(Fleet, Aggregate Line)
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);
            BigDecimal dsContrPk = svcDtlPMsg.dsContrPk.getValue();
            String dsContrCatgCd = svcDtlPMsg.dsContrCatgCd.getValue();

            if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && !DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk_AD)) {
                continue;
            }

            if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            BigDecimal dsContrDtlPkForFltAgg = getFltAggDsContrDtlPkByDB(dsContrPk);

            if (isDeleteDsContrCrCardPoForHeader(dsContrPk, dsContrCrCardPoTMsgDelList) && ZYPCommonFunc.hasValue(dsContrDtlPkForFltAgg)) {
                // delete
                DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForFltAgg = new DS_CONTR_CR_CARD_POTMsg();
                dsContrCrCardPoTMsgForFltAgg.setSQLID("201");
                dsContrCrCardPoTMsgForFltAgg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                dsContrCrCardPoTMsgForFltAgg.setConditionValue("dsContrDtlPk01", dsContrDtlPkForFltAgg);
                DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArrayForFltAgg = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByCondition(dsContrCrCardPoTMsgForFltAgg);
                if (dsContrCrCardPoTMsgArrayForFltAgg.getValidCount() > 0) {
                    dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArrayForFltAgg.no(0));
                    continue;
                }
            }
            // START 2019/01/10 S.Kitamura[QC#26928, MOD]
            // DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr = findDsContrCrCardPoForHeader(dsContrPk, dsContrCrCardPoTMsgNewList, dsContrCrCardPoTMsgModList);
            DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr = findDsContrCrCardPoForHeaderPo(dsContrPk, dsContrCrCardPoTMsgNewList, dsContrCrCardPoTMsgModList);
//            if (dsContrCrCardPoTMsgForHdr == null) {
//                continue;
//            }
//            if (ZYPCommonFunc.hasValue(dsContrDtlPkForFltAgg)) {
            if (dsContrCrCardPoTMsgForHdr != null && ZYPCommonFunc.hasValue(dsContrDtlPkForFltAgg)) {
            // END 2019/01/10 S.Kitamura[QC#26928, MOD]
                DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForFltAgg = new DS_CONTR_CR_CARD_POTMsg();
                // START 2019/05/23 W.Honda[QC#50157, MOD]
                // START 2019/01/10 S.Kitamura[QC#26928, MOD]
                //dsContrCrCardPoTMsgForFltAgg.setSQLID("201");
                // dsContrCrCardPoTMsgForFltAgg.setSQLID("202");
                // dsContrCrCardPoTMsgForFltAgg.setConditionValue("poFromDt01", ZYPDateUtil.getSalesDate());
                // dsContrCrCardPoTMsgForFltAgg.setConditionValue("poDt01", ZYPDateUtil.getSalesDate());
                // END 2019/01/10 S.Kitamura[QC#26928, MOD]
                // dsContrCrCardPoTMsgForFltAgg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                // dsContrCrCardPoTMsgForFltAgg.setConditionValue("dsContrDtlPk01", dsContrDtlPkForFltAgg);
                // DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArrayForFltAgg = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByCondition(dsContrCrCardPoTMsgForFltAgg);
                BigDecimal dsContrCrCardPoPkMod = getExistCrCardPO(dsContrPk, dsContrDtlPkForFltAgg);
                //if (dsContrCrCardPoTMsgArrayForFltAgg.getValidCount() > 0) {
                if (dsContrCrCardPoPkMod != null) {
                    // update
                    // START 2019/01/10 S.Kitamura[QC#26928, MOD]
                    // 2018/04/09 QC#20162 mod start
//                    modDsContrCrCardPo(dsContrCrCardPoTMsgModList, dsContrCrCardPoTMsgForHdr.crCardCustRefNum.getValue(), dsContrCrCardPoTMsgForHdr.crCardExprYrMth.getValue(), dsContrCrCardPoTMsgForHdr.custPoNum.getValue(),
//                            dsContrCrCardPoTMsgForHdr.poDt.getValue(), svcDtlPMsg, dsContrCrCardPoTMsgArrayForFltAgg.no(0));
//                    modDsContrCrCardPo(dsContrCrCardPoTMsgModList, dsContrCrCardPoTMsgForHdr.crCardCustRefNum.getValue(), dsContrCrCardPoTMsgForHdr.crCardExprYrMth.getValue(), dsContrCrCardPoTMsgForHdr.custPoNum.getValue(),
//                            dsContrCrCardPoTMsgForHdr.custIssPoDt.getValue(), svcDtlPMsg, dsContrCrCardPoTMsgArrayForFltAgg.no(0), dsContrCrCardPoTMsgForHdr.poDt.getValue());
                    dsContrCrCardPoTMsgForFltAgg.glblCmpyCd.setValue(this.glblCmpyCd);
                    dsContrCrCardPoTMsgForFltAgg.dsContrCrCardPoPk.setValue(dsContrCrCardPoPkMod);
                    dsContrCrCardPoTMsgForFltAgg = (DS_CONTR_CR_CARD_POTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrCrCardPoTMsgForFltAgg);
                    modDsContrCrCardPo(dsContrCrCardPoTMsgModList, null, null, dsContrCrCardPoTMsgForHdr.custPoNum.getValue(),
                            //dsContrCrCardPoTMsgForHdr.custIssPoDt.getValue(), svcDtlPMsg, dsContrCrCardPoTMsgArrayForFltAgg.no(0), dsContrCrCardPoTMsgForHdr.poDt.getValue());
                            dsContrCrCardPoTMsgForHdr.custIssPoDt.getValue(), svcDtlPMsg, dsContrCrCardPoTMsgForFltAgg, dsContrCrCardPoTMsgForHdr.poDt.getValue());
                    // 2018/04/09 QC#20162 mod end
                    // END 2019/01/10 S.Kitamura[QC#26928, MOD]

                    // END 2019/05/23 W.Honda[QC#50157, MOD]

                } else {
                    // insert
                    dsContrCrCardPoTMsgForFltAgg = new DS_CONTR_CR_CARD_POTMsg();
                    EZDMsg.copy(dsContrCrCardPoTMsgForHdr, null, dsContrCrCardPoTMsgForFltAgg, null);
                    BigDecimal dsContrCrCardPoPkForFltAgg = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgForFltAgg.dsContrCrCardPoPk, dsContrCrCardPoPkForFltAgg);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgForFltAgg.dsContrDtlPk, dsContrDtlPkForFltAgg);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgForFltAgg.dsContrMachLvlNum, "2");
                    dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsgForFltAgg);
                }

            // Mod Start 2019/01/23 QC#26928
            // } else {
            } else if (dsContrCrCardPoTMsgForHdr != null) {
            // Mod Start 2019/01/23 QC#26928

                // Fleet, Aggregate Line is not exists.
                dsContrDtlPkForFltAgg = getFltAggDsContrDtlPkByMap(inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.shellLineNum.getValue());
                if (!ZYPCommonFunc.hasValue(dsContrDtlPkForFltAgg)) {
                    continue;
                }

                // insert
                DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForFltAgg = new DS_CONTR_CR_CARD_POTMsg();
                EZDMsg.copy(dsContrCrCardPoTMsgForHdr, null, dsContrCrCardPoTMsgForFltAgg, null);
                BigDecimal dsContrCrCardPoPkForFltAgg = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
                ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgForFltAgg.dsContrCrCardPoPk, dsContrCrCardPoPkForFltAgg);
                ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgForFltAgg.dsContrDtlPk, dsContrDtlPkForFltAgg);
                ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgForFltAgg.dsContrMachLvlNum, "2");
                dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsgForFltAgg);
            }
            // START 2019/01/10 S.Kitamura[QC#26928, ADD]
            DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr2 = findDsContrCrCardPoForHeaderCrCard(dsContrPk, dsContrCrCardPoTMsgNewList, dsContrCrCardPoTMsgModList);
            if (dsContrCrCardPoTMsgForHdr2 != null && ZYPCommonFunc.hasValue(dsContrDtlPkForFltAgg)) {
                DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForFltAgg = new DS_CONTR_CR_CARD_POTMsg();
                dsContrCrCardPoTMsgForFltAgg.setSQLID("203");
                dsContrCrCardPoTMsgForFltAgg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                dsContrCrCardPoTMsgForFltAgg.setConditionValue("dsContrDtlPk01", dsContrDtlPkForFltAgg);
                DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArrayForFltAgg = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByCondition(dsContrCrCardPoTMsgForFltAgg);
                if (dsContrCrCardPoTMsgArrayForFltAgg.getValidCount() > 0) {
                    modDsContrCrCardPo(dsContrCrCardPoTMsgModList, dsContrCrCardPoTMsgForHdr2.crCardCustRefNum.getValue(), dsContrCrCardPoTMsgForHdr2.crCardExprYrMth.getValue(), null,
                                      null, svcDtlPMsg, dsContrCrCardPoTMsgArrayForFltAgg.no(0), null);
                } else {
                    // insert
                    dsContrCrCardPoTMsgForFltAgg = new DS_CONTR_CR_CARD_POTMsg();
                    EZDMsg.copy(dsContrCrCardPoTMsgForHdr2, null, dsContrCrCardPoTMsgForFltAgg, null);
                    BigDecimal dsContrCrCardPoPkForFltAgg = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgForFltAgg.dsContrCrCardPoPk, dsContrCrCardPoPkForFltAgg);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgForFltAgg.dsContrDtlPk, dsContrDtlPkForFltAgg);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgForFltAgg.dsContrMachLvlNum, "2");
                    dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsgForFltAgg);
                }
            // Mod Start 2019/01/23 QC#26928
            // } else  {
            } else if (dsContrCrCardPoTMsgForHdr2 != null) {
            // Mod End 2019/01/23 QC#26928
                // Fleet, Aggregate Line is not exists.
                dsContrDtlPkForFltAgg = getFltAggDsContrDtlPkByMap(inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.shellLineNum.getValue());
                if (!ZYPCommonFunc.hasValue(dsContrDtlPkForFltAgg)) {
                    continue;
                }
                // insert
                DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForFltAgg = new DS_CONTR_CR_CARD_POTMsg();
                EZDMsg.copy(dsContrCrCardPoTMsgForHdr2, null, dsContrCrCardPoTMsgForFltAgg, null);
                BigDecimal dsContrCrCardPoPkForFltAgg = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
                ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgForFltAgg.dsContrCrCardPoPk, dsContrCrCardPoPkForFltAgg);
                ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgForFltAgg.dsContrDtlPk, dsContrDtlPkForFltAgg);
                ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgForFltAgg.dsContrMachLvlNum, "2");
                dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsgForFltAgg);
            }
            // END 2019/01/10 S.Kitamura[QC#26928, ADD]
        }
        // END   2017/07/10 [QC#18985-2, ADD]

        if (!dsContrCrCardPoTMsgNewList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrCrCardPoTMsgNewList.toArray(new DS_CONTR_CR_CARD_POTMsg[0]));
            if (result != dsContrCrCardPoTMsgNewList.size()) {
                setErrStatus(msgMap, NSZM1243E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        if (!dsContrCrCardPoTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.update(dsContrCrCardPoTMsgModList.toArray(new DS_CONTR_CR_CARD_POTMsg[0]));
            if (result != dsContrCrCardPoTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1244E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        if (!dsContrCrCardPoTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrCrCardPoTMsgDelList.toArray(new DS_CONTR_CR_CARD_POTMsg[0]));
            if (result != dsContrCrCardPoTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1245E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    // START 2019/01/10 S.Kitamura [QC#26928, MOD]
//    private boolean needToRegistCrCardPo(String crCardCustRefNum, String crCardExprYrMth, String custIssPoNum, String custIssPoDt) {
//        if (ZYPCommonFunc.hasValue(crCardCustRefNum) || ZYPCommonFunc.hasValue(crCardExprYrMth) || ZYPCommonFunc.hasValue(custIssPoNum) || ZYPCommonFunc.hasValue(custIssPoDt)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    private boolean needToRegistPo(String custIssPoNum, String dsPoExprDt) {
        if (ZYPCommonFunc.hasValue(custIssPoNum) && ZYPCommonFunc.hasValue(dsPoExprDt)) {
            return true;
        } else {
            return false;
        }
    }
    private boolean needToRegistCrCard(String crCardCustRefNum, String crCardExprYrMth) {
        if (ZYPCommonFunc.hasValue(crCardCustRefNum) && ZYPCommonFunc.hasValue(crCardExprYrMth)) {
            return true;
        } else {
            return false;
        }
    }
    // END 2019/01/10 S.Kitamura [QC#26928, MOD]
    // START 2019/01/10 S.Kitamura[QC#26928, MOD]
    // START 2017/07/10 [QC#18985-2, ADD]
//    private DS_CONTR_CR_CARD_POTMsg findDsContrCrCardPoForHeader(BigDecimal dsContrPk, List<DS_CONTR_CR_CARD_POTMsg> newList, List<DS_CONTR_CR_CARD_POTMsg> modList) {
//        // header record is created.
//        for (DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg : newList) {
//            if (ZYPCommonFunc.hasValue(dsContrPk) && dsContrPk.equals(dsContrCrCardPoTMsg.dsContrPk.getValue())
//                    && "1".equals(dsContrCrCardPoTMsg.dsContrMachLvlNum.getValue())) {
//                return dsContrCrCardPoTMsg;
//            }
//        }
//
//        // header record is modified.
//        for (DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg : modList) {
//            if (ZYPCommonFunc.hasValue(dsContrPk) && dsContrPk.equals(dsContrCrCardPoTMsg.dsContrPk.getValue())
//                    && "1".equals(dsContrCrCardPoTMsg.dsContrMachLvlNum.getValue())) {
//                return dsContrCrCardPoTMsg;
//            }
//        }
//
//        // header record is not modified.
//        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
//        dsContrCrCardPoTMsg.setSQLID("004");
//        dsContrCrCardPoTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        dsContrCrCardPoTMsg.setConditionValue("dsContrPk01", dsContrPk);
//        DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsg);
//        if (dsContrCrCardPoTMsgArray.getValidCount() > 0) {
//            return dsContrCrCardPoTMsgArray.no(0);
//        }
//
//        return null;
//    }
    private DS_CONTR_CR_CARD_POTMsg findDsContrCrCardPoForHeaderPo(BigDecimal dsContrPk, List<DS_CONTR_CR_CARD_POTMsg> newList, List<DS_CONTR_CR_CARD_POTMsg> modList) {
        // header record is created.
        for (DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg : newList) {
            if (ZYPCommonFunc.hasValue(dsContrPk) && dsContrPk.equals(dsContrCrCardPoTMsg.dsContrPk.getValue())
                    && "1".equals(dsContrCrCardPoTMsg.dsContrMachLvlNum.getValue())
                    && "N".equals(dsContrCrCardPoTMsg.crCardFlg.getValue())) {
                return dsContrCrCardPoTMsg;
            }
        }
        // header record is modified.
        for (DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg : modList) {
            if (ZYPCommonFunc.hasValue(dsContrPk) && dsContrPk.equals(dsContrCrCardPoTMsg.dsContrPk.getValue())
                    && "1".equals(dsContrCrCardPoTMsg.dsContrMachLvlNum.getValue())
                    && "N".equals(dsContrCrCardPoTMsg.crCardFlg.getValue())) {
                return dsContrCrCardPoTMsg;
            }
        }
        // header record is not modified.
        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
        // START 2019/05/23 W.Honda[QC#50157, MOD]
        // dsContrCrCardPoTMsg.setSQLID("204");
        // dsContrCrCardPoTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        // dsContrCrCardPoTMsg.setConditionValue("dsContrPk01", dsContrPk);
        // dsContrCrCardPoTMsg.setConditionValue("poFromDt01", ZYPDateUtil.getSalesDate());
        // dsContrCrCardPoTMsg.setConditionValue("poDt01", ZYPDateUtil.getSalesDate());
        // DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsg);
        // if (dsContrCrCardPoTMsgArray.getValidCount() > 0) {
        //     return dsContrCrCardPoTMsgArray.no(0);
        // }
        BigDecimal dsContrCrCardPoPkMod = getExistCrCardPO(dsContrPk, null);
        dsContrCrCardPoTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        dsContrCrCardPoTMsg.dsContrCrCardPoPk.setValue(dsContrCrCardPoPkMod);
        dsContrCrCardPoTMsg = (DS_CONTR_CR_CARD_POTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrCrCardPoTMsg);
        if (dsContrCrCardPoTMsg != null) {
            return dsContrCrCardPoTMsg;
        }
        // END 2019/05/23 W.Honda[QC#50157, MOD]

        return null;
    }
    private DS_CONTR_CR_CARD_POTMsg findDsContrCrCardPoForHeaderCrCard(BigDecimal dsContrPk, List<DS_CONTR_CR_CARD_POTMsg> newList, List<DS_CONTR_CR_CARD_POTMsg> modList) {
        // header record is created.
        for (DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg : newList) {
            if (ZYPCommonFunc.hasValue(dsContrPk) && dsContrPk.equals(dsContrCrCardPoTMsg.dsContrPk.getValue())
                    && "1".equals(dsContrCrCardPoTMsg.dsContrMachLvlNum.getValue())
                    && "Y".equals(dsContrCrCardPoTMsg.crCardFlg.getValue())) {
                return dsContrCrCardPoTMsg;
            }
        }
        // header record is modified.
        for (DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg : modList) {
            if (ZYPCommonFunc.hasValue(dsContrPk) && dsContrPk.equals(dsContrCrCardPoTMsg.dsContrPk.getValue())
                    && "1".equals(dsContrCrCardPoTMsg.dsContrMachLvlNum.getValue())
                    && "Y".equals(dsContrCrCardPoTMsg.crCardFlg.getValue())) {
                return dsContrCrCardPoTMsg;
            }
        }
        // header record is not modified.
        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
        dsContrCrCardPoTMsg.setSQLID("205");
        dsContrCrCardPoTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        dsContrCrCardPoTMsg.setConditionValue("dsContrPk01", dsContrPk);
        DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsg);
        if (dsContrCrCardPoTMsgArray.getValidCount() > 0) {
            return dsContrCrCardPoTMsgArray.no(0);
        }
        return null;
    }
    // END 2019/01/10 S.Kitamura[QC#26928, MOD]
    private boolean isDeleteDsContrCrCardPoForHeader(BigDecimal dsContrPk, List<DS_CONTR_CR_CARD_POTMsg> delList) {
        for (DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg : delList) {
            if (ZYPCommonFunc.hasValue(dsContrPk) && dsContrPk.equals(dsContrCrCardPoTMsg.dsContrPk.getValue())
                    && "1".equals(dsContrCrCardPoTMsg.dsContrMachLvlNum.getValue())) {
                return true;
            }
        }
        return false;
    }
    // END   2017/07/10 [QC#18985-2, ADD]

// START 2017/07/10 [QC#18985-2, DEL]
//    private void copyDsContrCrCardPoForFltAgg(String dsContrCatgCd, List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgNewList, String cpoOrdNum, BigDecimal shellLineNum, DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr) {
//        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) || DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
//            // START 2017/06/20 [QC#18985-1, MOD]
//            BigDecimal dsContrDtlPkForFltAgg = getFltAggDsContrDtlPkByMap(cpoOrdNum, shellLineNum);
//            if (dsContrDtlPkForFltAgg == null) {
//                BigDecimal dsContrPk = dsContrCrCardPoTMsgForHdr.dsContrPk.getValue();
//                dsContrDtlPkForFltAgg = getFltAggDsContrDtlPkByDB(dsContrPk);
//                if (dsContrDtlPkForFltAgg == null) {
//                    return;
//                }
//            }
//            // END   2017/06/20 [QC#18985-1, MOD]
//            DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForFltAgg = new DS_CONTR_CR_CARD_POTMsg();
//            dsContrCrCardPoTMsgForFltAgg.setSQLID("201");
//            dsContrCrCardPoTMsgForFltAgg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//            dsContrCrCardPoTMsgForFltAgg.setConditionValue("dsContrDtlPk01", dsContrDtlPkForFltAgg);
//            DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArrayForFltAgg = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByCondition(dsContrCrCardPoTMsgForFltAgg);
//            if (dsContrCrCardPoTMsgArrayForFltAgg.getValidCount() == 0) {
//                dsContrCrCardPoTMsgForFltAgg = new DS_CONTR_CR_CARD_POTMsg();
//                EZDMsg.copy(dsContrCrCardPoTMsgForHdr, null, dsContrCrCardPoTMsgForFltAgg, null);
//                BigDecimal dsContrCrCardPoPkForFltAgg = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
//                ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgForFltAgg.dsContrCrCardPoPk, dsContrCrCardPoPkForFltAgg);
//                ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgForFltAgg.dsContrDtlPk, dsContrDtlPkForFltAgg);
//                dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsgForFltAgg);
//            }
//        }
//    }
//
//    // START 2017/06/15 [QC#18985, ADD]
//    // private void modDsContrCrCardPoForFltAgg(String dsContrCatgCd, List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgModList, NSZC115001_svcDtlListPMsg svcDtlPMsg, String cpoOrdNum, BigDecimal shellLineNum, DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr) {
//    private void modDsContrCrCardPoForFltAgg(String dsContrCatgCd, List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgNewList, List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgModList, NSZC115001_svcDtlListPMsg svcDtlPMsg, String cpoOrdNum, BigDecimal shellLineNum, DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForHdr) {
//    // END   2017/06/15 [QC#18985, ADD]
//        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) || DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
//            BigDecimal dsContrDtlPkForFltAgg = getFltAggDsContrDtlPkByMap(cpoOrdNum, shellLineNum);
//            DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgForFltAgg = new DS_CONTR_CR_CARD_POTMsg();
//            dsContrCrCardPoTMsgForFltAgg.setSQLID("201");
//            dsContrCrCardPoTMsgForFltAgg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//            dsContrCrCardPoTMsgForFltAgg.setConditionValue("dsContrDtlPk01", dsContrDtlPkForFltAgg);
//            DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArrayForFltAgg = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsgForFltAgg);
//            if (dsContrCrCardPoTMsgArrayForFltAgg.getValidCount() > 0) {
//                dsContrCrCardPoTMsgForFltAgg = dsContrCrCardPoTMsgArrayForFltAgg.no(0);
//                modDsContrCrCardPo(dsContrCrCardPoTMsgModList, dsContrCrCardPoTMsgForHdr.crCardCustRefNum.getValue(), dsContrCrCardPoTMsgForHdr.crCardExprYrMth.getValue(), dsContrCrCardPoTMsgForHdr.custPoNum.getValue(), dsContrCrCardPoTMsgForHdr.poDt.getValue(), svcDtlPMsg, dsContrCrCardPoTMsgForFltAgg);
//            // START 2017/06/15 [QC#18985, ADD]
//            } else {
//                if (getFltAggDsContrDtlPkByMap(cpoOrdNum, shellLineNum) != null) {
//                    dsContrCrCardPoTMsgForFltAgg = createDsContrCrCardPoTMsgLvl2(dsContrCrCardPoTMsgForHdr.dsContrPk.getValue() , dsContrDtlPkForFltAgg, dsContrCrCardPoTMsgForHdr.crCardCustRefNum.getValue(), dsContrCrCardPoTMsgForHdr.crCardExprYrMth.getValue(), dsContrCrCardPoTMsgForHdr.custPoNum.getValue(), dsContrCrCardPoTMsgForHdr.poDt.getValue(), svcDtlPMsg);
//                    dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsgForFltAgg);
//                }
//            }
//            // END   2017/06/15 [QC#18985, ADD]
//        }
//    }
// END   2017/07/10 [QC#18985-2, DEL]

    // START 2019/01/10 S.Kitamura [QC#26928, ADD]
    private void createDsContrCrCardPoTMsgLv2ForPo(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String custIssPoNum, String custIssPoDt, NSZC115001_svcDtlListPMsg svcDtlPMsg, String dsPoExprDt, 
                                                    Map<BigDecimal, DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgPoMapForHdr, Map<BigDecimal, DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgPoMapForAcc, 
                                                    List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgNewList){
        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = createDsContrCrCardPoTMsgLvl2(dsContrPk, dsContrDtlPk, null, null, custIssPoNum, custIssPoDt, svcDtlPMsg, dsPoExprDt, ZYPConstant.FLG_OFF_N);
        if (!dsContrCrCardPoTMsgPoMapForHdr.containsKey(dsContrPk)) {
            dsContrCrCardPoTMsgPoMapForHdr.put(dsContrPk, dsContrCrCardPoTMsg);
        }
        dsContrCrCardPoTMsgPoMapForAcc.put(dsContrDtlPk, dsContrCrCardPoTMsg);
        dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsg);
    }
    private void createDsContrCrCardPoTMsgLv2ForCrCard(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String crCardCustRefNum, String crCardExprYrMth, NSZC115001_svcDtlListPMsg svcDtlPMsg,
                                                        Map<BigDecimal, DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgCrCardMapForHdr, Map<BigDecimal, DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgCrCardMapForAcc, 
                                                        List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgNewList){
        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = createDsContrCrCardPoTMsgLvl2(dsContrPk, dsContrDtlPk, crCardCustRefNum, crCardExprYrMth, null, null, svcDtlPMsg, null, ZYPConstant.FLG_ON_Y);
        if (!dsContrCrCardPoTMsgCrCardMapForHdr.containsKey(dsContrPk)) {
            dsContrCrCardPoTMsgCrCardMapForHdr.put(dsContrPk, dsContrCrCardPoTMsg);
        }
        dsContrCrCardPoTMsgCrCardMapForAcc.put(dsContrDtlPk, dsContrCrCardPoTMsg);
        dsContrCrCardPoTMsgNewList.add(dsContrCrCardPoTMsg);
    }
    // END 2019/01/10 S.Kitamura [QC#26928, ADD]

// 2018/04/09 QC#20162 mod start
//    private DS_CONTR_CR_CARD_POTMsg createDsContrCrCardPoTMsgLvl2(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String crCardCustRefNum, String crCardExprYrMth, String custIssPoNum, String custIssPoDt, NSZC115001_svcDtlListPMsg svcDtlPMsg) {
    // START 2019/01/10 S.Kitamura[QC#26928, MOD]
    // private DS_CONTR_CR_CARD_POTMsg createDsContrCrCardPoTMsgLvl2(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String crCardCustRefNum, String crCardExprYrMth, String custIssPoNum, String custIssPoDt, NSZC115001_svcDtlListPMsg svcDtlPMsg, String dsPoExprDt) {
    private DS_CONTR_CR_CARD_POTMsg createDsContrCrCardPoTMsgLvl2(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String crCardCustRefNum, String crCardExprYrMth, String custIssPoNum, String custIssPoDt, NSZC115001_svcDtlListPMsg svcDtlPMsg, String dsPoExprDt, String crCardFlg) {
    // END 2019/01/10 S.Kitamura[QC#26928, MOD]
// 2018/04/09 QC#20162 mod start
        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
        BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.crCardCustRefNum, crCardCustRefNum);
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.crCardExprYrMth, crCardExprYrMth);
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.custPoNum, custIssPoNum);
        // 2018/04/09 QC#20162 mod start
//        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.poDt, custIssPoDt);
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.poDt, dsPoExprDt);
        // 2018/04/09 QC#20162 mod end
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.leaseCmpyFlg, getLeaseCmpyFlg(svcDtlPMsg.sellToCustCd.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrMachLvlNum, "2");
        // 2018/04/09 QC#20162 add start
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.custIssPoDt, custIssPoDt);
        // START 2019/01/10 S.Kitamura[QC#26928, ADD]
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.poFromDt, custIssPoDt);
        // END 2019/01/10 S.Kitamura[QC#26928, ADD]
        // 2018/04/09 QC#20162 add end
        // START 2019/01/10 S.Kitamura[QC#26928, ADD]
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.crCardFlg, crCardFlg);
        // END 2019/01/10 S.Kitamura[QC#26928, ADD]
        return dsContrCrCardPoTMsg;
    }
// 2018/04/09 QC#20162 mod start
//    private void modDsContrCrCardPo(List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgModList, String crCardCustRefNum, String crCardExprYrMth, String custIssPoNum, String custIssPoDt, NSZC115001_svcDtlListPMsg svcDtlPMsg, DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg) {
    private void modDsContrCrCardPo(List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgModList, String crCardCustRefNum, String crCardExprYrMth, String custIssPoNum, String custIssPoDt, NSZC115001_svcDtlListPMsg svcDtlPMsg, DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg, String dsPoExprDt) {
// 2018/04/09 QC#20162 mod end
        if (isSameValueDsContrCrCardPo(crCardCustRefNum, crCardExprYrMth, custIssPoNum, custIssPoDt, svcDtlPMsg, dsContrCrCardPoTMsg, dsPoExprDt)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.crCardCustRefNum, crCardCustRefNum);
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.crCardExprYrMth, crCardExprYrMth);
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.custPoNum, custIssPoNum);
        // 2018/04/09 QC#20162 mod start
//        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.poDt, custIssPoDt);
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.poDt, dsPoExprDt);
        // 2018/04/09 QC#20162 mod end
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.leaseCmpyFlg, getLeaseCmpyFlg(svcDtlPMsg.sellToCustCd.getValue()));
        // 2018/04/09 QC#20162 add start
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.custIssPoDt, custIssPoDt);
        // 2018/04/09 QC#20162 add end
        // START 2019/01/10 S.Kitamura[QC#26928, ADD]
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.poFromDt, custIssPoDt);
        // END 2019/01/10 S.Kitamura[QC#26928, MOD]

        dsContrCrCardPoTMsgModList.add(dsContrCrCardPoTMsg);
    }

// 2018/04/09 QC#20162 mod start
//    private boolean isSameValueDsContrCrCardPo(String crCardCustRefNum, String crCardExprYrMth, String custIssPoNum, String custIssPoDt, NSZC115001_svcDtlListPMsg svcDtlPMsg, DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg) {
    private boolean isSameValueDsContrCrCardPo(String crCardCustRefNum, String crCardExprYrMth, String custIssPoNum, String custIssPoDt, NSZC115001_svcDtlListPMsg svcDtlPMsg, DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg, String dsPoExprDt) {
// 2018/04/09 QC#20162 mod END

        if (!S21StringUtil.isEquals(crCardCustRefNum, dsContrCrCardPoTMsg.crCardCustRefNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(crCardExprYrMth, dsContrCrCardPoTMsg.crCardExprYrMth.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(custIssPoNum, dsContrCrCardPoTMsg.custPoNum.getValue())) {
            return false;
        }
        // 2018/04/09 QC#20162 mod start
//        if (!S21StringUtil.isEquals(custIssPoDt, dsContrCrCardPoTMsg.poDt.getValue())) {
        if (!S21StringUtil.isEquals(dsPoExprDt, dsContrCrCardPoTMsg.poDt.getValue())) {
        // 2018/04/09 QC#20162 mod end
            return false;
        }
        if (!S21StringUtil.isEquals(getLeaseCmpyFlg(svcDtlPMsg.sellToCustCd.getValue()), dsContrCrCardPoTMsg.leaseCmpyFlg.getValue())) {
            return false;
        }
        // 2018/04/09 QC#20162 add start
        if (!S21StringUtil.isEquals(custIssPoDt, dsContrCrCardPoTMsg.custIssPoDt.getValue())) {
            return false;
        }
        // 2018/04/09 QC#20162 add end

        return true;
    }

    private void setUpdateAsryDsContrCrCardPo(S21ApiMessageMap msgMap, List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgModList, List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgDelList) {
        List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgModListCopy = new ArrayList<DS_CONTR_CR_CARD_POTMsg>(dsContrCrCardPoTMsgModList);
        List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgDelListCopy = new ArrayList<DS_CONTR_CR_CARD_POTMsg>(dsContrCrCardPoTMsgDelList);

        for (DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg : dsContrCrCardPoTMsgModListCopy) {
            BigDecimal dsContrDtlPk = dsContrCrCardPoTMsg.dsContrDtlPk.getValue();
            if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                continue;
            }

            String crCardCustRefNum = dsContrCrCardPoTMsg.crCardCustRefNum.getValue();
            String crCardExprYrMth = dsContrCrCardPoTMsg.crCardExprYrMth.getValue();
            String custPoNum = dsContrCrCardPoTMsg.custPoNum.getValue();
            String poDt = dsContrCrCardPoTMsg.poDt.getValue();
            // 2018/04/09 QC#20162 add start
            String custIssPoDt =  dsContrCrCardPoTMsg.custIssPoDt.getValue();
            // 2018/04/09 QC#20162 add end

            List<BigDecimal> dsContrCrCardPoPkList = getNeedToUpdateAsryDsContrCrCardPo(dsContrDtlPk, crCardCustRefNum, crCardExprYrMth, custPoNum, poDt);
            for (BigDecimal dsContrCrCardPoPk : dsContrCrCardPoPkList) {
                DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgMod = new DS_CONTR_CR_CARD_POTMsg();
                dsContrCrCardPoTMsgMod.glblCmpyCd.setValue(this.glblCmpyCd);
                dsContrCrCardPoTMsgMod.dsContrCrCardPoPk.setValue(dsContrCrCardPoPk);
                dsContrCrCardPoTMsgMod = (DS_CONTR_CR_CARD_POTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrCrCardPoTMsgMod);
                if (dsContrCrCardPoTMsgMod != null) {
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgMod.crCardCustRefNum, crCardCustRefNum);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgMod.crCardExprYrMth, crCardExprYrMth);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgMod.custPoNum, custPoNum);
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgMod.poDt, poDt);
                    // 2018/04/09 QC#20162 add start
                    ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsgMod.custIssPoDt, custIssPoDt);
                    // 2018/04/09 QC#20162 add end
                    dsContrCrCardPoTMsgModList.add(dsContrCrCardPoTMsgMod);
                }
            }
        }

        for (DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg : dsContrCrCardPoTMsgDelListCopy) {
            BigDecimal dsContrDtlPk = dsContrCrCardPoTMsg.dsContrDtlPk.getValue();
            if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                continue;
            }

            List<BigDecimal> dsContrCrCardPoPkList = getDsContrCrCardPoForAsry(dsContrDtlPk);
            for (BigDecimal dsContrCrCardPoPk : dsContrCrCardPoPkList) {
                DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsgDel = new DS_CONTR_CR_CARD_POTMsg();
                dsContrCrCardPoTMsgDel.glblCmpyCd.setValue(this.glblCmpyCd);
                dsContrCrCardPoTMsgDel.dsContrCrCardPoPk.setValue(dsContrCrCardPoPk);
                dsContrCrCardPoTMsgDel = (DS_CONTR_CR_CARD_POTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrCrCardPoTMsgDel);
                if (dsContrCrCardPoTMsgDel != null) {
                    dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgDel);
                }
            }
        }
    }

    private List<BigDecimal> getNeedToUpdateAsryDsContrCrCardPo(BigDecimal dsContrDtlPk, String crCardCustRefNum, String crCardExprYrMth, String custPoNum, String poDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("crCardCustRefNum", crCardCustRefNum);
        param.put("crCardExprYrMth", crCardExprYrMth);
        param.put("custPoNum", custPoNum);
        param.put("poDt", poDt);
        return this.ssmBatchClient.queryObjectList("getNeedToUpdateAsryDsContrCrCardPo", param);
    }

    private List<BigDecimal> getDsContrCrCardPoForAsry(BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        return this.ssmBatchClient.queryObjectList("getDsContrCrCardPoForAsry", param);
    }

    // mod start 2020/03/24 QC#54318
    private void registDsContrRnwEscl(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<DS_CONTR_RNW_ESCLTMsg>dsContrRnwEsclTMsgNewList = new ArrayList<DS_CONTR_RNW_ESCLTMsg>();
        List<DS_CONTR_RNW_ESCLTMsg>dsContrRnwEsclTMsgModList = new ArrayList<DS_CONTR_RNW_ESCLTMsg>();
        List<DS_CONTR_RNW_ESCLTMsg>dsContrRnwEsclTMsgDelList = new ArrayList<DS_CONTR_RNW_ESCLTMsg>();
        // START 2018/05/24 [QC#26062, ADD]
        getDoNotRnwUpliftSvcPgmTpCd();
        // END   2018/05/24 [QC#26062, ADD]

        Map<BigDecimal, DS_CONTR_RNW_ESCLTMsg> dsContrRnwEsclLvl1 = new HashMap<BigDecimal, DS_CONTR_RNW_ESCLTMsg>();
        List<BigDecimal> modDsContrDtlPkList = new ArrayList<BigDecimal>();
        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;
        NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg = null;

        // DS_CONTR_RNW_ESCL(Header)
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);

            boolean addContrFlg = false;
            if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk_AD)) {
                // Add Contract
                //continue;
                addContrFlg = true;
            }

            BigDecimal dsContrPk = svcDtlPMsg.dsContrPk.getValue();
            String cpoOrdNum = inPrmPMsg.refCpoOrdNum.getValue();

            if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                if (this.delShellLineNumListForAddContr.contains(svcDtlPMsg.shellLineNum.getValue())) {
                    continue;
                }

                DS_CONTR_RNW_ESCLTMsgArray dsContrRnwEsclTMsgArray = getDsContrRnwEsclTMsgArray(svcDtlPMsg.dsContrPk.getValue());
                for (int j = 0; j < dsContrRnwEsclTMsgArray.getValidCount(); j++) {
                    dsContrRnwEsclTMsgDelList.add(dsContrRnwEsclTMsgArray.no(j));
                }
                continue;
            }

            String contrAutoRnwTpCd = CONTR_AUTO_RNW_TP.DO_NOT_RENEW;
            String rnwPrcMethCd = null;
            BigDecimal befEndRnwDaysAot = null;
            // START 2018/08/22 K.Kojima [QC#27867,MOD]
            // BigDecimal rnwPrcUpRatio = null;
            BigDecimal basePrcUpRatio = null;
            BigDecimal mtrPrcUpRatio = null;
            // END 2018/08/22 K.Kojima [QC#27867,MOD]
            String contrUplftTpCd = CONTR_UPLFT_TP.DO_NOT_UPLIFT;
            String uplftPrcMethCd = null;
            BigDecimal uplftBasePrcUpRatio = null;
            BigDecimal uplftMtrPrcUpRatio = null;
            // add start 2017/08/09 QC#18799
            BigDecimal befEndUplftDaysAot = null;
            // add end 2017/08/09 QC#18799

            boolean isExistNextContr = isExistNextContr(cpoOrdNum, svcDtlPMsg.shellLineNum.getValue());
            // START 2018/05/24 [QC#26062, MOD]
            boolean isDoNotRenewUp = isDoNotRenewUpLift(svcDtlPMsg.svcPgmMdseCd.getValue());
            getDsContrIntfcDefRule(inPrmPMsg);
            String prcSvcPlnTpCd = svcDtlPMsg.prcSvcPlnTpCd.getValue();
            if (!isExistNextContr && !isDoNotRenewUp) {
                contrAutoRnwTpCd = getContrAutoRnwTpCd(cpoOrdNum, prcSvcPlnTpCd);
                // START 2017/06/21 K.Kitachi [QC#18068, MOD]
//                contrUplftTpCd = getContrUplftTpCd(inPrmPMsg);
                contrUplftTpCd = getContrUplftTpCd(inPrmPMsg, svcDtlPMsg);
                // END 2017/06/21 K.Kitachi [QC#18068, MOD]
            }
            // END   2018/05/24 [QC#26062, MOD]

            if (!CONTR_AUTO_RNW_TP.DO_NOT_RENEW.equals(contrAutoRnwTpCd)) {
                rnwPrcMethCd = getRnwPrcMethCd(cpoOrdNum);
                befEndRnwDaysAot = getBefEndRnwDaysAot(inPrmPMsg);
                // START 2018/08/22 K.Kojima [QC#27867,MOD]
                // rnwPrcUpRatio = getRnwPrcUpRatio(inPrmPMsg, svcDtlPMsg);
                if (rnwPrcMethCd != null && RNW_PRC_METH.MARKUP_PERCENT.equals(rnwPrcMethCd)) {
                    basePrcUpRatio = getRnwPrcUpRatio(inPrmPMsg, svcDtlPMsg, true);
                    mtrPrcUpRatio = getRnwPrcUpRatio(inPrmPMsg, svcDtlPMsg, false);
                }
                // END 2018/08/22 K.Kojima [QC#27867,MOD]
            }

            if (!CONTR_UPLFT_TP.DO_NOT_UPLIFT.equals(contrUplftTpCd)) {
                uplftPrcMethCd = getUplftPrcMethCd(inPrmPMsg);
                // add start 2017/08/09 QC#18799
                befEndUplftDaysAot = getBefEndUplftDaysAot(inPrmPMsg);
                // add end 2017/08/09 QC#18799
                // START 2017/06/21 K.Kitachi [QC#18068, MOD]
//                uplftBasePrcUpRatio = getUplftBasePrcUpRatio(inPrmPMsg);
//                uplftMtrPrcUpRatio = getUplftMtrPrcUpRatio(inPrmPMsg);
                if (UPLFT_PRC_METH.MARKUP_PERCENT.equals(uplftPrcMethCd)) {
                    uplftBasePrcUpRatio = getUplftBasePrcUpRatio(inPrmPMsg, svcDtlPMsg);
                    uplftMtrPrcUpRatio = getUplftMtrPrcUpRatio(inPrmPMsg, svcDtlPMsg);
                }
                // END 2017/06/21 K.Kitachi [QC#18068, MOD]
            }

            // START 2017/06/23 K.Kitachi [QC#18068, MOD]
            DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg = new DS_CONTR_RNW_ESCLTMsg();
            BigDecimal dsContrRnwEsclPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_RNW_ESCL_SQ);

            // DS_CONTR_RNW_ESCL
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrRnwEsclPk, dsContrRnwEsclPk);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrPk, dsContrPk);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrMachLvlNum, "1");
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.contrAutoRnwTpCd, contrAutoRnwTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.rnwPrcMethCd, rnwPrcMethCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.befEndRnwDaysAot, befEndRnwDaysAot);
            // START 2018/08/22 K.Kojima [QC#27867,MOD]
            // ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.basePrcUpRatio, rnwPrcUpRatio);
            // ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.mtrPrcUpRatio, rnwPrcUpRatio);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.basePrcUpRatio, basePrcUpRatio);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.mtrPrcUpRatio, mtrPrcUpRatio);
            // END 2018/08/22 K.Kojima [QC#27867,MOD]
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.contrUplftTpCd, contrUplftTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.uplftPrcMethCd, uplftPrcMethCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.uplftBasePrcUpRatio, uplftBasePrcUpRatio);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.uplftMtrPrcUpRatio, uplftMtrPrcUpRatio);
            // mod start 2017/08/09 QC#18799
//            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.uplftBefEndRnwDaysAot, befEndRnwDaysAot);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.uplftBefEndRnwDaysAot, befEndUplftDaysAot);
            // mod end 2017/08/09 QC#18799
            // START 2018/11/15 [QC#28638, ADD]
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.fixTermInMthAot, svcDtlPMsg.fixTermInMthAot);
            // END 2018/11/15 [QC#28638, ADD]
            setUplftFlg(svcDtlPMsg, dsContrRnwEsclTMsg);

           // START 2017/09/06 M.Naito [QC#18724, ADD]
            setUplftFlgFromPlnTp(svcDtlPMsg, dsContrRnwEsclTMsg);
            // END 2017/09/06 M.Naito [QC#18724, ADD]

            if (RQST_TP_NEW.equals(svcDtlPMsg.xxRqstTpCd.getValue()) && !addContrFlg) {
                dsContrRnwEsclTMsgNewList.add(dsContrRnwEsclTMsg);
            } else if (RQST_TP_MOD.equals(svcDtlPMsg.xxRqstTpCd.getValue()) && !addContrFlg) {
                DS_CONTR_RNW_ESCLTMsg curDsContrRnwEsclTMsg = getDsContrRnwEsclTMsg(svcDtlPMsg.dsContrPk.getValue());
                if (curDsContrRnwEsclTMsg == null) {
                    dsContrRnwEsclTMsgNewList.add(dsContrRnwEsclTMsg);
                } else {
                    modDsContrRnwEsclTMsg(dsContrRnwEsclTMsgModList, curDsContrRnwEsclTMsg, dsContrRnwEsclTMsg);

                    DS_CONTR_RNW_ESCLTMsgArray dsContrRnwEsclTMsgArrayLv2 = getDsContrRnwEsclTMsgArrayLvl2(dsContrPk);
                    for (int j = 0; j < dsContrRnwEsclTMsgArrayLv2.getValidCount(); j++) {
                        DS_CONTR_RNW_ESCLTMsg curDsContrRnwEsclTMsgLv2 = dsContrRnwEsclTMsgArrayLv2.no(j);
                        modDsContrRnwEsclTMsg(dsContrRnwEsclTMsgModList, curDsContrRnwEsclTMsgLv2, dsContrRnwEsclTMsg);
                        if (!modDsContrDtlPkList.contains(curDsContrRnwEsclTMsgLv2.dsContrDtlPk.getValue())) {
                            modDsContrDtlPkList.add(curDsContrRnwEsclTMsgLv2.dsContrDtlPk.getValue());
                        }
                    }
                }
            }
            // END 2017/06/23 K.Kitachi [QC#18068, MOD]
            if (!dsContrRnwEsclLvl1.containsKey(dsContrPk)) {
                dsContrRnwEsclLvl1.put(dsContrPk, dsContrRnwEsclTMsg);
            }
        }

        // DS_CONTR_RNW_ESCL(Machine)
        for (int i = 0; i < inPrmPMsg.svcConfigRefList.getValidCount(); i++) {
            svcConfigRefPMsg = inPrmPMsg.svcConfigRefList.no(i);
            svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, svcConfigRefPMsg.shellLineNum.getValue());

            if (DS_CONTR_CATG.FLEET.equals(svcDtlPMsg.dsContrCatgCd.getValue())) {
                continue;
            }

            BigDecimal dsContrPk = svcConfigRefPMsg.dsContrPk.getValue();
            BigDecimal dsContrDtlPk = svcConfigRefPMsg.dsContrDtlPk.getValue();

            DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg = new DS_CONTR_RNW_ESCLTMsg();
            if (RQST_TP_DEL.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                if (svcDtlPMsg != null && RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                    continue;
                }
                DS_CONTR_RNW_ESCLTMsgArray dsContrRnwEsclTMsgArray = getDsContrRnwEsclTMsgArrayDtl(dsContrDtlPk);
                for (int j = 0; j < dsContrRnwEsclTMsgArray.getValidCount(); j++) {
                    dsContrRnwEsclTMsgDelList.add(dsContrRnwEsclTMsgArray.no(j));
                }
                continue;
            }

            if (dsContrRnwEsclLvl1.containsKey(dsContrPk)) {
                DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsgLvl1 = dsContrRnwEsclLvl1.get(dsContrPk);
                EZDMsg.copy(dsContrRnwEsclTMsgLvl1, null, dsContrRnwEsclTMsg, null);
                BigDecimal dsContrRnwEsclPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_RNW_ESCL_SQ);
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrRnwEsclPk, dsContrRnwEsclPk);
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrMachLvlNum, "2");
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrDtlPk, dsContrDtlPk);
            } else {
                continue;
            }

            if (RQST_TP_NEW.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                dsContrRnwEsclTMsgNewList.add(dsContrRnwEsclTMsg);
            } else if (RQST_TP_MOD.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                DS_CONTR_RNW_ESCLTMsg curDsContrRnwEsclTMsg = getDsContrRnwEsclTMsgLvl2(dsContrPk, dsContrDtlPk);
                if (curDsContrRnwEsclTMsg == null) {
                    dsContrRnwEsclTMsgNewList.add(dsContrRnwEsclTMsg);
                } else {
                    if (!modDsContrDtlPkList.contains(curDsContrRnwEsclTMsg.dsContrDtlPk.getValue())) {
                        modDsContrRnwEsclTMsg(dsContrRnwEsclTMsgModList, curDsContrRnwEsclTMsg, dsContrRnwEsclTMsg);
                        modDsContrDtlPkList.add(curDsContrRnwEsclTMsg.dsContrDtlPk.getValue());
                    }
                }
            }
        }

        // DS_CONTR_RNW_ESCL(Accessory)
        for (int i = 0; i < inPrmPMsg.svcAddlBasePrcList.getValidCount(); i++) {
            svcAddlBasePrcPMsg = inPrmPMsg.svcAddlBasePrcList.no(i);
            svcDtlPMsg = getSvcDtlListItem(inPrmPMsg.svcDtlList, svcAddlBasePrcPMsg.shellLineNum.getValue());

            if (DS_CONTR_CATG.FLEET.equals(svcDtlPMsg.dsContrCatgCd.getValue())) {
                continue;
            }

            BigDecimal dsContrPk = getDsContrPk(inPrmPMsg.svcDtlList, svcAddlBasePrcPMsg.shellLineNum.getValue(), svcAddlBasePrcPMsg.dsContrPk.getValue());
            BigDecimal dsContrDtlPk = svcAddlBasePrcPMsg.dsContrDtlPk.getValue();

            DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg = new DS_CONTR_RNW_ESCLTMsg();
            if (RQST_TP_DEL.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                if (svcDtlPMsg != null && RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                    continue;
                }
                DS_CONTR_RNW_ESCLTMsgArray dsContrRnwEsclTMsgArray = getDsContrRnwEsclTMsgArrayDtl(dsContrDtlPk);
                for (int j = 0; j < dsContrRnwEsclTMsgArray.getValidCount(); j++) {
                    dsContrRnwEsclTMsgDelList.add(dsContrRnwEsclTMsgArray.no(j));
                }
                continue;
            }

            if (dsContrRnwEsclLvl1.containsKey(dsContrPk)) {
                DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsgLvl1 = dsContrRnwEsclLvl1.get(dsContrPk);
                EZDMsg.copy(dsContrRnwEsclTMsgLvl1, null, dsContrRnwEsclTMsg, null);
                BigDecimal dsContrRnwEsclPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_RNW_ESCL_SQ);
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrRnwEsclPk, dsContrRnwEsclPk);
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrMachLvlNum, "2");
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrDtlPk, dsContrDtlPk);
            } else {
                continue;
            }

            if (RQST_TP_NEW.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                dsContrRnwEsclTMsgNewList.add(dsContrRnwEsclTMsg);
            } else if (RQST_TP_MOD.equals(svcAddlBasePrcPMsg.xxRqstTpCd.getValue())) {
                DS_CONTR_RNW_ESCLTMsg curDsContrRnwEsclTMsg = getDsContrRnwEsclTMsgLvl2(dsContrPk, dsContrDtlPk);
                if (curDsContrRnwEsclTMsg == null) {
                    dsContrRnwEsclTMsgNewList.add(dsContrRnwEsclTMsg);
                } else {
                    if (!modDsContrDtlPkList.contains(curDsContrRnwEsclTMsg.dsContrDtlPk.getValue())) {
                        modDsContrRnwEsclTMsg(dsContrRnwEsclTMsgModList, curDsContrRnwEsclTMsg, dsContrRnwEsclTMsg);
                        modDsContrDtlPkList.add(curDsContrRnwEsclTMsg.dsContrDtlPk.getValue());
                    }
                }
            }
        }

        // DS_CONTR_RNW_ESCL(Fleet/Aggregate Line)
        for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);
            BigDecimal dsContrPk = svcDtlPMsg.dsContrPk.getValue();
            String dsContrCatgCd = svcDtlPMsg.dsContrCatgCd.getValue();

            if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && !DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk_AD)) {
                continue;
            }
            if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            BigDecimal dsContrDtlPkForFltAgg = getFltAggDsContrDtlPkByDB(dsContrPk);
            if (!ZYPCommonFunc.hasValue(dsContrDtlPkForFltAgg)) {
                continue;
            }

            DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg = new DS_CONTR_RNW_ESCLTMsg();
            if (dsContrRnwEsclLvl1.containsKey(dsContrPk)) {
                DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsgLvl1 = dsContrRnwEsclLvl1.get(dsContrPk);
                EZDMsg.copy(dsContrRnwEsclTMsgLvl1, null, dsContrRnwEsclTMsg, null);
                BigDecimal dsContrRnwEsclPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_RNW_ESCL_SQ);
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrRnwEsclPk, dsContrRnwEsclPk);
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrMachLvlNum, "2");
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrDtlPk, dsContrDtlPkForFltAgg);
            } else {
                continue;
            }

            if (RQST_TP_NEW.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                dsContrRnwEsclTMsgNewList.add(dsContrRnwEsclTMsg);
            } else if (RQST_TP_MOD.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                DS_CONTR_RNW_ESCLTMsg curDsContrRnwEsclTMsg = getDsContrRnwEsclTMsgLvl2(dsContrPk, dsContrDtlPkForFltAgg);
                if (curDsContrRnwEsclTMsg == null) {
                    dsContrRnwEsclTMsgNewList.add(dsContrRnwEsclTMsg);
                } else {
                    if (!modDsContrDtlPkList.contains(curDsContrRnwEsclTMsg.dsContrDtlPk.getValue())) {
                        modDsContrRnwEsclTMsg(dsContrRnwEsclTMsgModList, curDsContrRnwEsclTMsg, dsContrRnwEsclTMsg);
                        modDsContrDtlPkList.add(curDsContrRnwEsclTMsg.dsContrDtlPk.getValue());
                    }
                }
            }
        }

        if (!dsContrRnwEsclTMsgNewList.isEmpty()) {
            if (checkDsContrRnwEscl(msgMap, dsContrRnwEsclTMsgNewList)) {
                int result = S21FastTBLAccessor.insert(dsContrRnwEsclTMsgNewList.toArray(new DS_CONTR_RNW_ESCLTMsg[0]));
                if (result != dsContrRnwEsclTMsgNewList.size()) {
                    setErrStatus(msgMap, NSZM1246E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }

        if (!dsContrRnwEsclTMsgModList.isEmpty()) {
            if (checkDsContrRnwEscl(msgMap, dsContrRnwEsclTMsgModList)) {
                int result = S21FastTBLAccessor.update(dsContrRnwEsclTMsgModList.toArray(new DS_CONTR_RNW_ESCLTMsg[0]));
                if (result != dsContrRnwEsclTMsgModList.size()) {
                    setErrStatus(msgMap, NSZM1247E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }

        if (!dsContrRnwEsclTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrRnwEsclTMsgDelList.toArray(new DS_CONTR_RNW_ESCLTMsg[0]));
            if (result != dsContrRnwEsclTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1248E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }
    // mod end 2020/03/24 QC#54318

    // START 2017/06/23 K.Kitachi [QC#18068, MOD]
    private void modDsContrRnwEsclTMsg(List<DS_CONTR_RNW_ESCLTMsg> dsContrRnwEsclTMsgModList, DS_CONTR_RNW_ESCLTMsg curDsContrRnwEsclTMsg, DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg) {

        if (isSameValueDsContrRnwEsclTMsg(curDsContrRnwEsclTMsg, dsContrRnwEsclTMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.contrAutoRnwTpCd, dsContrRnwEsclTMsg.contrAutoRnwTpCd);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.rnwPrcMethCd, dsContrRnwEsclTMsg.rnwPrcMethCd);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.befEndRnwDaysAot, dsContrRnwEsclTMsg.befEndRnwDaysAot);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.basePrcUpRatio, dsContrRnwEsclTMsg.basePrcUpRatio);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.mtrPrcUpRatio, dsContrRnwEsclTMsg.mtrPrcUpRatio);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.contrUplftTpCd, dsContrRnwEsclTMsg.contrUplftTpCd);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.uplftPrcMethCd, dsContrRnwEsclTMsg.uplftPrcMethCd);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.uplftBasePrcUpRatio, dsContrRnwEsclTMsg.uplftBasePrcUpRatio);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.uplftMtrPrcUpRatio, dsContrRnwEsclTMsg.uplftMtrPrcUpRatio);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.firstYrContrUplftFlg, dsContrRnwEsclTMsg.firstYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.scdYrContrUplftFlg, dsContrRnwEsclTMsg.scdYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.thirdYrContrUplftFlg, dsContrRnwEsclTMsg.thirdYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.frthYrContrUplftFlg, dsContrRnwEsclTMsg.frthYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.fifthYrContrUplftFlg, dsContrRnwEsclTMsg.fifthYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.sixthYrContrUplftFlg, dsContrRnwEsclTMsg.sixthYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.svnthYrContrUplftFlg, dsContrRnwEsclTMsg.svnthYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.eighthYrContrUplftFlg, dsContrRnwEsclTMsg.eighthYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.ninthYrContrUplftFlg, dsContrRnwEsclTMsg.ninthYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.tenthYrContrUplftFlg, dsContrRnwEsclTMsg.tenthYrContrUplftFlg);
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.uplftBefEndRnwDaysAot, dsContrRnwEsclTMsg.uplftBefEndRnwDaysAot);
        // START 2018/11/15 [QC#28638, ADD]
        ZYPEZDItemValueSetter.setValue(curDsContrRnwEsclTMsg.fixTermInMthAot, dsContrRnwEsclTMsg.fixTermInMthAot);
        // END 2018/11/15 [QC#28638, ADD]

        dsContrRnwEsclTMsgModList.add(curDsContrRnwEsclTMsg);
    }

    private boolean isSameValueDsContrRnwEsclTMsg(DS_CONTR_RNW_ESCLTMsg curDsContrRnwEsclTMsg, DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg) {

        if (!S21StringUtil.isEquals(curDsContrRnwEsclTMsg.contrAutoRnwTpCd.getValue(), dsContrRnwEsclTMsg.contrAutoRnwTpCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(curDsContrRnwEsclTMsg.rnwPrcMethCd.getValue(), dsContrRnwEsclTMsg.rnwPrcMethCd.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(curDsContrRnwEsclTMsg.befEndRnwDaysAot.getValue(), dsContrRnwEsclTMsg.befEndRnwDaysAot.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(curDsContrRnwEsclTMsg.basePrcUpRatio.getValue(), dsContrRnwEsclTMsg.basePrcUpRatio.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(curDsContrRnwEsclTMsg.mtrPrcUpRatio.getValue(), dsContrRnwEsclTMsg.mtrPrcUpRatio.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(curDsContrRnwEsclTMsg.contrUplftTpCd.getValue(), dsContrRnwEsclTMsg.contrUplftTpCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(curDsContrRnwEsclTMsg.uplftPrcMethCd.getValue(), dsContrRnwEsclTMsg.uplftPrcMethCd.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(curDsContrRnwEsclTMsg.uplftBasePrcUpRatio.getValue(), dsContrRnwEsclTMsg.uplftBasePrcUpRatio.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(curDsContrRnwEsclTMsg.uplftMtrPrcUpRatio.getValue(), dsContrRnwEsclTMsg.uplftMtrPrcUpRatio.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(curDsContrRnwEsclTMsg.firstYrContrUplftFlg.getValue(), dsContrRnwEsclTMsg.firstYrContrUplftFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(curDsContrRnwEsclTMsg.scdYrContrUplftFlg.getValue(), dsContrRnwEsclTMsg.scdYrContrUplftFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(curDsContrRnwEsclTMsg.thirdYrContrUplftFlg.getValue(), dsContrRnwEsclTMsg.thirdYrContrUplftFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(curDsContrRnwEsclTMsg.frthYrContrUplftFlg.getValue(), dsContrRnwEsclTMsg.frthYrContrUplftFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(curDsContrRnwEsclTMsg.fifthYrContrUplftFlg.getValue(), dsContrRnwEsclTMsg.fifthYrContrUplftFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(curDsContrRnwEsclTMsg.sixthYrContrUplftFlg.getValue(), dsContrRnwEsclTMsg.sixthYrContrUplftFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(curDsContrRnwEsclTMsg.svnthYrContrUplftFlg.getValue(), dsContrRnwEsclTMsg.svnthYrContrUplftFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(curDsContrRnwEsclTMsg.eighthYrContrUplftFlg.getValue(), dsContrRnwEsclTMsg.eighthYrContrUplftFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(curDsContrRnwEsclTMsg.ninthYrContrUplftFlg.getValue(), dsContrRnwEsclTMsg.ninthYrContrUplftFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(curDsContrRnwEsclTMsg.tenthYrContrUplftFlg.getValue(), dsContrRnwEsclTMsg.tenthYrContrUplftFlg.getValue())) {
            return false;
        }
        if (!isEqualBigDecimal(curDsContrRnwEsclTMsg.uplftBefEndRnwDaysAot.getValue(), dsContrRnwEsclTMsg.uplftBefEndRnwDaysAot.getValue())) {
            return false;
        }
        // START 2018/11/15 [QC#28638, ADD]
        if (!isEqualBigDecimal(curDsContrRnwEsclTMsg.fixTermInMthAot.getValue(), dsContrRnwEsclTMsg.fixTermInMthAot.getValue())) {
            return false;
        }
        // END 2018/11/15 [QC#28638, ADD]
        return true;
    }
    // END 2017/06/23 K.Kitachi [QC#18068, MOD]

    private void updateBaseTotal(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList = new ArrayList<DS_CONTR_DTLTMsg>();
        String cpoOrdNum = inPrmPMsg.refCpoOrdNum.getValue();
        List<BigDecimal> rsList = getUpdateBaseTotalTarget(cpoOrdNum);
        if (rsList == null) {
            return;
        }
        for (BigDecimal shellLineNum : rsList) {
            BigDecimal totBasePrcDealAmt = getBaseTotalAmt(cpoOrdNum, shellLineNum);
            if (totBasePrcDealAmt == null) {
                totBasePrcDealAmt = BigDecimal.ZERO;
            }
            BigDecimal totBasePrcFuncAmt = NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), inPrmPMsg.slsDt.getValue(), totBasePrcDealAmt);

            DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
            tMsg.setSQLID("201");
            tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            tMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
            tMsg.setConditionValue("shellLineNum01", shellLineNum);
            DS_CONTR_DTLTMsgArray tMsgArray = (DS_CONTR_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                DS_CONTR_DTLTMsg dsContrDtlTMsg = tMsgArray.no(i);
                if (ZYPCommonFunc.hasValue(totBasePrcDealAmt) && ZYPCommonFunc.hasValue(dsContrDtlTMsg.totBasePrcDealAmt) && totBasePrcDealAmt.compareTo(dsContrDtlTMsg.totBasePrcDealAmt.getValue()) == 0) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.totBasePrcDealAmt, totBasePrcDealAmt);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.totBasePrcFuncAmt, totBasePrcFuncAmt);
                dsContrDtlTMsgModList.add(dsContrDtlTMsg);
            }
        }

        if (!dsContrDtlTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.update(dsContrDtlTMsgModList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1228E); //mod u.kim 2018/03/06 QC#24056
            }

//            updateCpoSvcDtlRecForBaseTotal(tMsg, msgMap);
        }

        // DS_CONTR_DTL_REC
        List<DS_CONTR_DTL_RECTMsg> dsContrDtlRecTMsgList = new ArrayList<DS_CONTR_DTL_RECTMsg>();
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgModList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_MOD, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrDtlRecTMsgList.toArray(new DS_CONTR_DTL_RECTMsg[0]));
            if (result != dsContrDtlRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1264E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    // START 2018/03/23 K.Kojima [QC#24458,ADD]
    private void updateBaseTotalForPhysDel(S21ApiMessageMap msgMap, String cpoOrdNum, String slsDt) {
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<BigDecimal> rsList = getUpdateBaseTotalTarget(cpoOrdNum);
        if (rsList == null) {
            return;
        }
        for (BigDecimal shellLineNum : rsList) {
            BigDecimal totBasePrcDealAmt = getBaseTotalAmt(cpoOrdNum, shellLineNum);
            if (totBasePrcDealAmt == null) {
                totBasePrcDealAmt = BigDecimal.ZERO;
            }
            BigDecimal totBasePrcFuncAmt = NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), slsDt, totBasePrcDealAmt);

            DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
            tMsg.setSQLID("201");
            tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            tMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
            tMsg.setConditionValue("shellLineNum01", shellLineNum);
            DS_CONTR_DTLTMsgArray tMsgArray = (DS_CONTR_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                DS_CONTR_DTLTMsg dsContrDtlTMsg = tMsgArray.no(i);
                if (ZYPCommonFunc.hasValue(totBasePrcDealAmt) && ZYPCommonFunc.hasValue(dsContrDtlTMsg.totBasePrcDealAmt) && totBasePrcDealAmt.compareTo(dsContrDtlTMsg.totBasePrcDealAmt.getValue()) == 0) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.totBasePrcDealAmt, totBasePrcDealAmt);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.totBasePrcFuncAmt, totBasePrcFuncAmt);
                dsContrDtlTMsgModList.add(dsContrDtlTMsg);
            }
        }

        if (!dsContrDtlTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.update(dsContrDtlTMsgModList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1228E);
            }
        }

        // DS_CONTR_DTL_REC
        List<DS_CONTR_DTL_RECTMsg> dsContrDtlRecTMsgList = new ArrayList<DS_CONTR_DTL_RECTMsg>();
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgModList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_MOD, docId, cpoOrdNum);
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrDtlRecTMsgList.toArray(new DS_CONTR_DTL_RECTMsg[0]));
            if (result != dsContrDtlRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1264E);
            }
        }
    }
    // END 2018/03/23 K.Kojima [QC#24458,ADD]

    private List<BigDecimal> getUpdateBaseTotalTarget(String cpoOrdNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);

        List<BigDecimal> rslt = this.ssmBatchClient.queryObjectList("getUpdateBaseTotalTarget", param);
        if (rslt.size() == 0) {
            return null;
        }
        return rslt;
    }

    private BigDecimal getBaseTotalAmt(String cpoOrdNum, BigDecimal shellLineNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("shellLineNum", shellLineNum);
        param.put("dsContrCatgCd_Fleet", DS_CONTR_CATG.FLEET);
        param.put("dsContrDtlTpCd_Fleet", DS_CONTR_DTL_TP.FLEET);
        param.put("dsContrDtlTpCd_Aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        param.put("dsContrDtlTpCd_Shell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        // START 2017/12/27 U.Kim [QC#23104, ADD]
        param.put("aftDeclPntDigitNum", getDealCcyDigit());
        // END 2017/12/27 U.Kim [QC#23104, ADD]
        // START 2018/04/10 M.Naito [QC#23378,ADD]
        param.put("dsContrDtlTpCdMach", new String[] { DS_CONTR_DTL_TP.BASE_AND_USAGE, DS_CONTR_DTL_TP.BASE_ONLY });
        // END 2018/04/10 M.Naito [QC#23378,ADD]
        return (BigDecimal) this.ssmBatchClient.queryObject("getBaseTotalAmt", param);
    }

    private String getDealCcyCd(String prcCatgCd) {
        PRC_CATGTMsg tMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, prcCatgCd);

        tMsg = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return null;
        }
        return tMsg.ccyCd.getValue();
    }

    private boolean isEqualBigDecimal(BigDecimal val1, BigDecimal val2) {
        if (ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {
            return (val1.compareTo(val2) == 0);
        }
        if (ZYPCommonFunc.hasValue(val1) || ZYPCommonFunc.hasValue(val2)) {
            return false;
        }
        return true;
    }

    private BigDecimal getDsContrPkFromDsContrDtl(BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);

        List<Map<String, BigDecimal>> rslt = this.ssmBatchClient.queryObjectList("getDsContrPkFromDsContrDtl", param);
        if (rslt.size() == 0) {
            return null;
        }
        return rslt.get(0).get("DS_CONTR_PK");
    }

    private BigDecimal printBizProcLog(String rqstTpCd, String docId, String prntDocId) {

        S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();

        bizLogMsg.setSubSysId(SUB_SYS_ID);
        bizLogMsg.setProcId(PROC_ID);
        bizLogMsg.setDocTpCd(DOC_TP);
        bizLogMsg.setEventId(createEventId(rqstTpCd));
        bizLogMsg.setDocId(docId);
        bizLogMsg.setPrntDocId(prntDocId);

        BIZ_PROC_LOGTMsg inMsg = S21BusinessProcessLog.getBizProcLog(bizLogMsg);
        S21ApiTBLAccessor.insert(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            return null;
        }
        return inMsg.bizProcLogPk.getValue();
    }

    private void delDsContrChildren(List<BigDecimal> delDsContrPkList, S21ApiMessageMap msgMap) {

        String[] condList = new String[] {"glblCmpyCd", "dsContrPk" };

        List<DS_CONTR_DTLTMsg> delDsContrDtlTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_ADDL_CHRGTMsg> delDsContrAddlChrgTMsgList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();

        for (BigDecimal dsContrPk : delDsContrPkList) {
            // DS_CONTR_DTL
            DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
            dsContrDtlTMsg.setSQLID("001");
            dsContrDtlTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            dsContrDtlTMsg.setConditionValue("dsContrPk01", dsContrPk);
            DS_CONTR_DTLTMsgArray dsContrDtlTMsgAry = (DS_CONTR_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrDtlTMsg);
            if (dsContrDtlTMsgAry.getValidCount() == 0) {
                // mod start 2017/06/12 CSA Defect#18948
                continue;
                // mod end 2017/06/12 CSA Defect#18948
            }

            for (int i = 0; i < dsContrDtlTMsgAry.getValidCount(); i++) {
                dsContrDtlTMsg = dsContrDtlTMsgAry.no(i);
                this.dsContrDtlPkListForUsgRemoval.add(dsContrDtlTMsg.dsContrDtlPk.getValue());
                delDsContrDtlTMsgList.add(dsContrDtlTMsg);
            }

            dsContrDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            dsContrDtlTMsg.dsContrPk.setValue(dsContrPk);
            int res = S21FastTBLAccessor.logicalRemoveByPartialValue(dsContrDtlTMsg, condList);
            if (res < 0) {
                setErrStatus(msgMap, NSZM1229E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            // DS_CONTR_BLLG_MTR, CONTR_PHYS_BLLG_MTR_RELN, CONTR_XS_COPY
            removeUsg(msgMap);
            this.dsContrDtlPkListForUsgRemoval.clear();
            if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                return;
            }

            // DS_CONTR_ADDL_CHRG
            DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg = new DS_CONTR_ADDL_CHRGTMsg();
            dsContrAddlChrgTMsg.setSQLID("201");
            dsContrAddlChrgTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            dsContrAddlChrgTMsg.setConditionValue("dsContrPk01", dsContrPk);
            DS_CONTR_ADDL_CHRGTMsgArray dsContrAddlChrgTMsgAry = (DS_CONTR_ADDL_CHRGTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrAddlChrgTMsg);
            if (dsContrAddlChrgTMsgAry.getValidCount() == 0) {
                // mod start 2017/06/12 CSA Defect#18948
                continue;
                // mod end 2017/06/12 CSA Defect#18948
            }

            for (int i = 0; i < dsContrAddlChrgTMsgAry.getValidCount(); i++) {
                delDsContrAddlChrgTMsgList.add(dsContrAddlChrgTMsgAry.no(i));
            }

            dsContrAddlChrgTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            dsContrAddlChrgTMsg.dsContrPk.setValue(dsContrPk);
            res = S21FastTBLAccessor.logicalRemoveByPartialValue(dsContrAddlChrgTMsg, condList);
            if (res < 0) {
                setErrStatus(msgMap, NSZM1239E); //mod u.kim 2018/03/06 QC#24056
                return;
            }
        }

        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        List<DS_CONTR_DTL_RECTMsg> dsContrDtlRecTMsgList = new ArrayList<DS_CONTR_DTL_RECTMsg>();
        List<DS_CONTR_ADDL_CHRG_RECTMsg> dsContrAddlChrgRecTMsgList = new ArrayList<DS_CONTR_ADDL_CHRG_RECTMsg>();

        // DS_CONTR_DTL_REC
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : delDsContrDtlTMsgList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrDtlRecTMsgList.toArray(new DS_CONTR_DTL_RECTMsg[0]));
            if (result != dsContrDtlRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1264E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // DS_CONTR_ADDL_CHRG_REC
        for (DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg : delDsContrAddlChrgTMsgList) {
            String docId = getDsContrAddlChrgDocId(dsContrAddlChrgTMsg);

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_ADDL_CHRG_RECTMsg dsContrAddlChrgRecTMsg = new DS_CONTR_ADDL_CHRG_RECTMsg();
            EZDMsg.copy(dsContrAddlChrgTMsg, null, dsContrAddlChrgRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrAddlChrgRecTMsgList.add(dsContrAddlChrgRecTMsg);
        }

        if (!dsContrAddlChrgRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrAddlChrgRecTMsgList.toArray(new DS_CONTR_ADDL_CHRG_RECTMsg[0]));
            if (result != dsContrAddlChrgRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1265E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private void delDsContrChildrenForAddContr(List<BigDecimal> delShellLineNumList, S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        String cpoOrdNum = inPrmPMsg.refCpoOrdNum.getValue();
        String[] condList = new String[] {"glblCmpyCd", "cpoOrdNum", "shellLineNum" };

        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgDelList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgTMsgDelList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();
        List<SVC_TERM_CONDTMsg> svcTermCondTMsgDelList = new ArrayList<SVC_TERM_CONDTMsg>();
        List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgDelList = new ArrayList<DS_CONTR_CR_CARD_POTMsg>();
        // add start 2020/03/24 QC#54318
        List<DS_CONTR_RNW_ESCLTMsg> dsContrRnwEsclTMsgDelList = new ArrayList<DS_CONTR_RNW_ESCLTMsg>();
        // add end 2020/03/24 QC#54318

        for (BigDecimal shellLineNum : delShellLineNumList) {
            // DS_CONTR_DTL
            DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
            dsContrDtlTMsg.setSQLID("201");
            dsContrDtlTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            dsContrDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
            dsContrDtlTMsg.setConditionValue("shellLineNum01", shellLineNum);
            DS_CONTR_DTLTMsgArray dsContrDtlTMsgAry = (DS_CONTR_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrDtlTMsg);
            if (dsContrDtlTMsgAry.getValidCount() == 0) {
                return;
            }

            for (int i = 0; i < dsContrDtlTMsgAry.getValidCount(); i++) {
                dsContrDtlTMsg = dsContrDtlTMsgAry.no(i);
                BigDecimal dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();
                this.dsContrDtlPkListForUsgRemoval.add(dsContrDtlPk);
                dsContrDtlTMsgDelList.add(dsContrDtlTMsg);

                // DS_CONTR_ADDL_CHRG
                DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg = new DS_CONTR_ADDL_CHRGTMsg();
                dsContrAddlChrgTMsg.setSQLID("001");
                dsContrAddlChrgTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                dsContrAddlChrgTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
                DS_CONTR_ADDL_CHRGTMsgArray dsContrAddlChrgTMsgAry = (DS_CONTR_ADDL_CHRGTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrAddlChrgTMsg);
                for (int j = 0; j < dsContrAddlChrgTMsgAry.getValidCount(); j++) {
                    dsContrAddlChrgTMsgDelList.add(dsContrAddlChrgTMsgAry.no(j));
                }

                // SVC_TERM_COND
                setSvcTermCondForDtl(svcTermCondTMsgDelList, dsContrDtlPk);

                // DS_CONTR_CR_CARD_PO
                DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
                dsContrCrCardPoTMsg.setSQLID("003");
                dsContrCrCardPoTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                dsContrCrCardPoTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
                DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsg);
                for (int j = 0; j < dsContrCrCardPoTMsgArray.getValidCount(); j++) {
                    dsContrCrCardPoTMsgDelList.add(dsContrCrCardPoTMsgArray.no(j));
                }

                // add start 2020/03/24 QC#54318
                // DS_CONTR_RNW_ESCL
                DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg = new DS_CONTR_RNW_ESCLTMsg();
                dsContrRnwEsclTMsg.setSQLID("003");
                dsContrRnwEsclTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                dsContrRnwEsclTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
                DS_CONTR_RNW_ESCLTMsgArray dsContrRnwEsclTMsgArray = (DS_CONTR_RNW_ESCLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrRnwEsclTMsg);
                for (int j = 0; j < dsContrRnwEsclTMsgArray.getValidCount(); j++) {
                    dsContrRnwEsclTMsgDelList.add(dsContrRnwEsclTMsgArray.no(j));
                }
                // add end 2020/03/24 QC#54318
            }

            dsContrDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            dsContrDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);
            dsContrDtlTMsg.shellLineNum.setValue(shellLineNum);
            int res = S21FastTBLAccessor.logicalRemoveByPartialValue(dsContrDtlTMsg, condList);
            if (res < 0) {
                setErrStatus(msgMap, NSZM1229E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            // DS_CONTR_BLLG_MTR, CONTR_PHYS_BLLG_MTR_RELN, CONTR_XS_COPY
            removeUsg(msgMap);
            this.dsContrDtlPkListForUsgRemoval.clear();
            if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                return;
            }
        }

        // DS_CONTR_ADDL_CHRG
        if (!dsContrAddlChrgTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrAddlChrgTMsgDelList.toArray(new DS_CONTR_ADDL_CHRGTMsg[0]));
            if (result != dsContrAddlChrgTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1238E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // SVC_TERM_COND
        if (!svcTermCondTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(svcTermCondTMsgDelList.toArray(new SVC_TERM_CONDTMsg[0]));
            if (result != svcTermCondTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1242E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // DS_CONTR_CR_CARD_PO
        if (!dsContrCrCardPoTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrCrCardPoTMsgDelList.toArray(new DS_CONTR_CR_CARD_POTMsg[0]));
            if (result != dsContrCrCardPoTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1245E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // add start 2020/03/24 QC#54318
        // DS_CONTR_RNW_ESCL
        if (!dsContrRnwEsclTMsgDelList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrRnwEsclTMsgDelList.toArray(new DS_CONTR_RNW_ESCLTMsg[0]));
            if (result != dsContrRnwEsclTMsgDelList.size()) {
                setErrStatus(msgMap, NSZM1245E);
            }
        }
        // add end 2020/03/24 QC#54318

        List<DS_CONTR_DTL_RECTMsg> dsContrDtlRecTMsgList = new ArrayList<DS_CONTR_DTL_RECTMsg>();
        List<DS_CONTR_ADDL_CHRG_RECTMsg> dsContrAddlChrgRecTMsgList = new ArrayList<DS_CONTR_ADDL_CHRG_RECTMsg>();

        // DS_CONTR_DTL_REC
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgDelList) {
            if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
                continue;
            }
            String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
            EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrDtlRecTMsgList.add(dsContrDtlRecTMsg);
        }

        if (!dsContrDtlRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrDtlRecTMsgList.toArray(new DS_CONTR_DTL_RECTMsg[0]));
            if (result != dsContrDtlRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1264E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // DS_CONTR_ADDL_CHRG_REC
        for (DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg : dsContrAddlChrgTMsgDelList) {
            String docId = getDsContrAddlChrgDocId(dsContrAddlChrgTMsg);

            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_DEL, docId, inPrmPMsg.refCpoOrdNum.getValue());
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_ADDL_CHRG_RECTMsg dsContrAddlChrgRecTMsg = new DS_CONTR_ADDL_CHRG_RECTMsg();
            EZDMsg.copy(dsContrAddlChrgTMsg, null, dsContrAddlChrgRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgRecTMsg.bizProcLogPk, bizProcLogPk);
            dsContrAddlChrgRecTMsgList.add(dsContrAddlChrgRecTMsg);
        }

        if (!dsContrAddlChrgRecTMsgList.isEmpty()) {
            int result = S21FastTBLAccessor.insert(dsContrAddlChrgRecTMsgList.toArray(new DS_CONTR_ADDL_CHRG_RECTMsg[0]));
            if (result != dsContrAddlChrgRecTMsgList.size()) {
                setErrStatus(msgMap, NSZM1265E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private String getDsContrCatgCdFromDB(String cpoOrdNum, BigDecimal shellLineNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("shellLineNum", shellLineNum);

        return (String) this.ssmBatchClient.queryObject("getDsContrCatgCd", param);
    }

    private DS_CONTR_DTLTMsg getDsContrDtlTMsgForFltAgg(BigDecimal dsContrPk, String dsContrCatgCd) {
        if (!ZYPCommonFunc.hasValue(dsContrPk)) {
            return null;
        }

        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        dsContrDtlTMsg.setSQLID("006");
        dsContrDtlTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        dsContrDtlTMsg.setConditionValue("dsContrPk01", dsContrPk);
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            dsContrDtlTMsg.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.FLEET);
        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            dsContrDtlTMsg.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.AGGREGATE);
        }
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgAry = (DS_CONTR_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrDtlTMsg);
        if (dsContrDtlTMsgAry.getValidCount() > 0) {
            return dsContrDtlTMsgAry.no(0);
        }
        return null;
    }

    private String createEventId(String rqstTpCd) {

        StringBuilder eventId = new StringBuilder();
        eventId.append("Shell");
        eventId.append(" ");

        if (RQST_TP_NEW.equals(rqstTpCd)) {
            eventId.append(EVENT_NEW);

        } else if (RQST_TP_MOD.equals(rqstTpCd)) {
            eventId.append(EVENT_MOD);

        } else if (RQST_TP_DEL.equals(rqstTpCd)) {
            eventId.append(EVENT_DEL);

        } else {
            // for Search
            eventId.append("%");
        }

        return eventId.toString();
    }

    private BigDecimal getDsContrPk(NSZC115001_svcDtlListPMsgArray svcDtlList, BigDecimal shellLineNum, BigDecimal pDsContrPk) {
        BigDecimal dsContrPk = pDsContrPk;
        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        for (int j = 0; j < svcDtlList.getValidCount(); j++) {
            svcDtlPMsg = svcDtlList.no(j);
            if (shellLineNum.compareTo(svcDtlPMsg.shellLineNum.getValue()) != 0) {
                continue;
            }
            if (RQST_TP_NEW.equals(svcDtlPMsg.xxRqstTpCd.getValue()) || !ZYPCommonFunc.hasValue(dsContrPk)) {
                dsContrPk = svcDtlPMsg.dsContrPk.getValue();
            }
            break;
        }
        return dsContrPk;
    }

    private boolean isExistCdTbl(String tblNm, String cdVal) {
        return ZYPCodeDataUtil.findByCode(tblNm, this.glblCmpyCd, cdVal) != null;
    }

    private boolean isExistTblByPK(EZDTMsg tMsg) {
        return S21FastTBLAccessor.findByKey(tMsg) != null;
    }

    private boolean isExistTblByCond(EZDTMsg tMsg, Object val) {

        String key = createCacheKey(tMsg.getTableName(), val);

        if (this.tMsgByCondMap.contains(key)) {
            return this.tMsgByCondMap.get(key).length() > 0;
        }

        EZDTMsgArray tMsgAry = S21ApiTBLAccessor.findByCondition(tMsg);
        this.tMsgByCondMap.put(key, tMsgAry);

        return tMsgAry.length() > 0;
    }

    private EZDTMsgArray searchTblByCond(EZDTMsg tMsg, Object val) {

        String key = createCacheKey(tMsg.getTableName(), val);

        if (this.tMsgByCondMap.contains(key)) {
            return this.tMsgByCondMap.get(key);
        }

        EZDTMsgArray tMsgAry = S21ApiTBLAccessor.findByCondition(tMsg);
        this.tMsgByCondMap.put(key, tMsgAry);

        return tMsgAry;
    }

    private BigDecimal getMdlIdByCpoDtl(CPO_DTLTMsg cpoDtlTMsg) {
        String key = createCacheKey(cpoDtlTMsg.cpoOrdNum.getValue(), cpoDtlTMsg.cpoDtlLineNum.getValue(), cpoDtlTMsg.cpoDtlLineSubNum.getValue());

        Map<String, Object> rslt = (Map<String, Object>) this.ssmBatchClient.queryObject("getMdlIdByCpoDtl", cpoDtlTMsg);
        BigDecimal mdlId = null;
        BigDecimal svcConfigMstrPk = null;
        if (rslt != null) {
            mdlId = (BigDecimal) rslt.get("MDL_ID");
            svcConfigMstrPk = (BigDecimal) rslt.get("SVC_CONFIG_MSTR_PK");
        }
        this.mdlIdByCpoMap.put(key, mdlId);
        this.svcConfigMstrPkByCpoMap.put(key, svcConfigMstrPk);

        return mdlId;
    }

    private BigDecimal getSvcConfigMstrPkByCpoDtl(CPO_DTLTMsg cpoDtlTMsg) {
        getMdlIdByCpoDtl(cpoDtlTMsg);
        String key = createCacheKey(cpoDtlTMsg.cpoOrdNum.getValue(), cpoDtlTMsg.cpoDtlLineNum.getValue(), cpoDtlTMsg.cpoDtlLineSubNum.getValue());
        return this.svcConfigMstrPkByCpoMap.get(key);
    }

    private List<String> getMtrLbByBllgMtr(PRC_MTR_PKG_BLLG_MTRTMsg prcMtrPkgBllgTMsg, String slsDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("prcMtrPkgPk", prcMtrPkgBllgTMsg.prcMtrPkgPk.getValue());
        param.put("mdlId", prcMtrPkgBllgTMsg.mdlId.getValue());
        param.put("slsDt", slsDt);

        List<String> rslt = this.ssmBatchClient.queryObjectList("getMtrLbByBllgMtr", param);
        if (rslt == null || rslt.size() == 0 || rslt.get(0) == null) {
            return new ArrayList<String>();
        }

        return new ArrayList<String>(rslt);
    }

    private List<String> getMtrLbByBllgMtrForFleet(PRC_MTR_PKG_BLLG_MTRTMsg prcMtrPkgBllgTMsg, BigDecimal[] mdlIdAry, String slsDt) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("prcMtrPkgPk", prcMtrPkgBllgTMsg.prcMtrPkgPk.getValue());
        param.put("mdlIdList", mdlIdAry);
        param.put("slsDt", slsDt);

        List<String> rslt = this.ssmBatchClient.queryObjectList("getMtrLbByBllgMtrForFleet", param);

        return new ArrayList<String>(rslt);
    }

    private Map<String, BigDecimal> getMdlIdFromDsCpoConfig(String cpoOrdNum, String dsOrdPosnNum) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("dsOrdPosnNum", dsOrdPosnNum);
        param.put("configCatgCd", CONFIG_CATG.OUTBOUND);

        List<Map<String, BigDecimal>> rslt = this.ssmBatchClient.queryObjectList("getMdlIdFromDsCpoConfig", param);
        if (rslt.size() == 0) {
            return null;
        }
        return rslt.get(0);
    }

    private String getPrcListStruTpFromPrcCatg(String prcCatgCd, String slsDt) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("prcCatgCd", prcCatgCd);
        param.put("slsDt", slsDt);
        param.put("rownum1", BigDecimal.ONE);

        String prcListStruTpCd = (String) this.ssmBatchClient.queryObject("getPrcListStruTpFromPrcCatg", param);

        return prcListStruTpCd;
    }

    private boolean addToMyMsgMap(S21ApiMessageMap msgMap, EZDPMsg pMsg) {
        boolean isError = false;
        for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(pMsg)) {
            msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            if (msg.getXxMsgid().endsWith("E")) {
                isError = true;
            }
        }
        return isError;
    }

    private boolean isFleet(NSZC115001PMsg inPrmPMsg, BigDecimal shellLineNum) {

        String dsContrCatgCd = null;
        for (int j = 0; j < inPrmPMsg.svcDtlList.getValidCount(); j++) {
            NSZC115001_svcDtlListPMsg svcDtlPMsg = inPrmPMsg.svcDtlList.no(j);
            if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                continue;
            }
            if (shellLineNum.compareTo(svcDtlPMsg.shellLineNum.getValue()) != 0) {
                continue;
            }
            dsContrCatgCd = svcDtlPMsg.dsContrCatgCd.getValue();
            if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                return true;
            }
        }
        return false;
    }

    private String getDsContrCatgCd(NSZC115001PMsg inPrmPMsg, BigDecimal shellLineNum) {
        for (int j = 0; j < inPrmPMsg.svcDtlList.getValidCount(); j++) {
            NSZC115001_svcDtlListPMsg svcDtlPMsg = inPrmPMsg.svcDtlList.no(j);
            if (RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                continue;
            }
            if (shellLineNum.compareTo(svcDtlPMsg.shellLineNum.getValue()) != 0) {
                continue;
            }
            return svcDtlPMsg.dsContrCatgCd.getValue();
        }
        return null;
    }

    // START 2018/07/09 K.Kojima [QC#27135,DEL]
    // private List<Map<String, String>> getCpoDtlLineNumByMdlId(String cpoOrdNum, BigDecimal mdlId, String dsOrdPosnNum) {
    //     Map<String, Object> param = new HashMap<String, Object>();
    //     param.put("glblCmpyCd", this.glblCmpyCd);
    //     param.put("cpoOrdNum", cpoOrdNum);
    //     if (ZYPCommonFunc.hasValue(mdlId)) {
    //         param.put("mdlId", mdlId);
    //     }
    //     if (ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
    //         param.put("dsOrdPosnNum", dsOrdPosnNum);
    //     }
    // 
    //     List<Map<String, String>> rslt = this.ssmBatchClient.queryObjectList("getCpoDtlLineNumByMdlId", param);
    //     if (rslt.size() == 0) {
    //         return null;
    //     }
    //     return rslt;
    // }
    // END 2018/07/09 K.Kojima [QC#27135,DEL]

    private boolean checkDclnSvc(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        // Header Level Check
        if (isDclnSvcHdr(inPrmPMsg)) {
            for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
                NSZC115001_svcDtlListPMsg svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);

                if (!RQST_TP_DEL.equals(svcDtlPMsg.xxRqstTpCd.getValue())) {
                    // Error
                    setErrStatus(msgMap, NSZM1210E); //mod u.kim 2018/03/06 QC#24056
                    return false;
                }
            }
        }

        // Config Level Check
        Set<String> dclnSvcPosnSet = getDclnSvcPosnList(inPrmPMsg);
        if (dclnSvcPosnSet.size() == 0) {
            // Skip
            return true;
        }

        for (int i = 0; i < inPrmPMsg.svcConfigRefList.getValidCount(); i++) {
            NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = inPrmPMsg.svcConfigRefList.no(i);

            if (!RQST_TP_DEL.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                if (dclnSvcPosnSet.contains(svcConfigRefPMsg.dsOrdPosnNum.getValue())) {
                    // Error
                    setErrStatus(msgMap, NSZM1210E); //mod u.kim 2018/03/06 QC#24056
                    return false;
                }
            }
        }
        return true;

    }

    private boolean isDclnSvcHdr(NSZC115001PMsg inPrmPMsg) {
        CPOTMsg cpo = getCPOTMsg(inPrmPMsg);
        if (cpo != null && ZYPConstant.CHKBOX_ON_1.equals(cpo.dclnSvcCd.getValue())) {
            return true;
        }
        return false;
    }

    private Set<String> getDclnSvcPosnList(NSZC115001PMsg inPrmPMsg) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", inPrmPMsg.refCpoOrdNum.getValue());
        param.put("dclnSvcCd", ZYPConstant.CHKBOX_ON_1);
        param.put("configCatgOutbound", CONFIG_CATG.OUTBOUND);

        List<String> dclnSvcPosnList = this.ssmBatchClient.queryObjectList("getDclnSvcPosnList", param);

        Set<String> dclnSvcPosnSet = new HashSet<String>();
        for (String dsOrdPosnNum : dclnSvcPosnList) {
            dclnSvcPosnSet.add(dsOrdPosnNum);
        }
        return dclnSvcPosnSet;

    }

    private void setOldSplyAgmtPlnPk(S21ApiMessageMap msgMap) {
        NSZC115001PMsg pMsg = (NSZC115001PMsg) msgMap.getPmsg();
        for (int i = 0; i < pMsg.svcPrcList.getValidCount(); i++) {
            NSZC115001_svcPrcListPMsg svcPrcPMsg = pMsg.svcPrcList.no(i);

            if (!ZYPCommonFunc.hasValue(svcPrcPMsg.dsContrDtlPk)) {
                continue;
            }

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("dsContrDtlPk", svcPrcPMsg.dsContrDtlPk.getValue());

            // get query parameter
            List<Map<String, Object>> paramRslt = this.ssmBatchClient.queryObjectList("getSplyAgmtParam", param);

            if (paramRslt.size() == 0) {
                continue;
            }

            param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);

            // from detail
            param.put("prcCatgCd", paramRslt.get(0).get("MAINT_PRC_CATG_CD"));
            param.put("prcSvcPlnTpCd", paramRslt.get(0).get("PRC_SVC_PLN_TP_CD"));
            param.put("prcSvcContrTpCd", paramRslt.get(0).get("PRC_SVC_CONTR_TP_CD"));
            // from price
            param.put("mdlId", paramRslt.get(0).get("MDL_ID"));
            param.put("prcMtrPkgPk", paramRslt.get(0).get("PRC_MTR_PKG_PK"));
            // from usage
            param.put("bllgMtrLbCd", paramRslt.get(0).get("BLLG_MTR_LB_CD"));
            param.put("prcListBandCd", paramRslt.get(0).get("PRC_LIST_BAND_CD"));

            // get Old Supply Agreement Plan ID
            List<BigDecimal> oldPlnPkRslt = this.ssmBatchClient.queryObjectList("getSplyAgmtPlnPk", param);

            if (oldPlnPkRslt != null && oldPlnPkRslt.size() > 0) {
                // put Old Supply Agreement Plan ID
                this.oldSplyAgmtPlnPkMap.put(svcPrcPMsg.dsContrDtlPk.getValue(), oldPlnPkRslt.get(0));
            }
        }
    }

    private void setNewSplyAgmtPlnInfo(S21ApiMessageMap msgMap) {
        NSZC115001PMsg pMsg = (NSZC115001PMsg) msgMap.getPmsg();

        for (int i = 0; i < pMsg.svcPrcList.getValidCount(); i++) {
            NSZC115001_svcPrcListPMsg svcPrcPMsg = pMsg.svcPrcList.no(i);

            if (!ZYPCommonFunc.hasValue(svcPrcPMsg.dsOrdPosnNum)) {
                continue;
            }

            if (!ZYPCommonFunc.hasValue(svcPrcPMsg.dsContrDtlPk)) {
                NSZC115001_svcConfigRefListPMsg svcConfigRefListPMsg = getDsContrDtlPkByDsOrdPosnNum(pMsg.svcConfigRefList, svcPrcPMsg);
                if (svcConfigRefListPMsg == null) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(svcPrcPMsg.dsContrPk, svcConfigRefListPMsg.dsContrPk);
                ZYPEZDItemValueSetter.setValue(svcPrcPMsg.dsContrDtlPk, svcConfigRefListPMsg.dsContrDtlPk);
            }

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("dsContrDtlPk", svcPrcPMsg.dsContrDtlPk.getValue());

            // get query parameter
            List<Map<String, Object>> paramRslt = this.ssmBatchClient.queryObjectList("getSplyAgmtParam", param);

            if (paramRslt.size() == 0) {
                continue;
            }

            String prcCatgCd = (String) paramRslt.get(0).get("MAINT_PRC_CATG_CD");
            String prcTpCd = getPrcListTp(prcCatgCd);
            if (!PRC_LIST_TP.EASYPACII_OPTIMIZEIT.equals(prcTpCd)) {
                continue;
            }

            param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            // from detail
            param.put("prcCatgCd", paramRslt.get(0).get("MAINT_PRC_CATG_CD"));
            param.put("prcSvcPlnTpCd", paramRslt.get(0).get("PRC_SVC_PLN_TP_CD"));
            param.put("prcSvcContrTpCd", paramRslt.get(0).get("PRC_SVC_CONTR_TP_CD"));
            param.put("termMthNum", paramRslt.get(0).get("TERM_MTH_NUM"));
            // from price
            param.put("mdlId", paramRslt.get(0).get("MDL_ID"));
            param.put("prcMtrPkgPk", paramRslt.get(0).get("PRC_MTR_PKG_PK"));
            // from usage
            param.put("bllgMtrLbCd", paramRslt.get(0).get("BLLG_MTR_LB_CD"));
            param.put("prcListBandCd", paramRslt.get(0).get("PRC_LIST_BAND_CD"));
            // other
            param.put("numberOfMonths", MTH_12);

            param.put("dsContrDtlPk", paramRslt.get(0).get("DS_CONTR_DTL_PK"));
            param.put("dsContrPk", svcPrcPMsg.dsContrPk.getValue());
            param.put("cpoOrdNum", pMsg.refCpoOrdNum.getValue());

            // QC#27005
            param.put("slsDt", pMsg.slsDt);

            // get New Supply Agreement Plan Info(Template)
            List<Map<String, Object>> newPlnInfoRslt = this.ssmBatchClient.queryObjectList("getSplyAgmtPlnInfo", param);

            if (newPlnInfoRslt != null && newPlnInfoRslt.size() > 0) {
                // put New Supply Agreement Plan Info(Template)
                this.newSplyAgmtPlnPkMap.put(svcPrcPMsg.dsContrDtlPk.getValue(), newPlnInfoRslt);
            }

        }
    }

    private void registSchdCratTmplList(S21ApiMessageMap msgMap) {
        NSZC115001PMsg pMsg = (NSZC115001PMsg) msgMap.getPmsg();
        if (DS_CONTR_CATG.FLEET.equals(pMsg.svcDtlList.no(0).dsContrCatgCd.getValue())) {
            return;
        }
        setNewSplyAgmtPlnInfo(msgMap);

        List<BigDecimal> delList = new ArrayList<BigDecimal>();
        List<List<Map<String, Object>>> insList = new ArrayList<List<Map<String, Object>>>();
        if (this.newSplyAgmtPlnPkMap != null && this.newSplyAgmtPlnPkMap.size() > 0) {
            for (Entry<BigDecimal, List<Map<String, Object>>> newSplyAgmtPln : this.newSplyAgmtPlnPkMap.entrySet()) {
                BigDecimal newDsContrDtlPk = newSplyAgmtPln.getKey();
                List<Map<String, Object>> newSplyAgmtPlnInfo = newSplyAgmtPln.getValue();
                BigDecimal newSplyAgmtPlnPk = (BigDecimal) newSplyAgmtPlnInfo.get(0).get("SPLY_AGMT_PLN_PK");

                // Matched dsContrDtlPk
                if (this.oldSplyAgmtPlnPkMap.containsKey(newDsContrDtlPk)) {
                    // Same or Mod
                    BigDecimal oldSplyAgmtPlnPk = this.oldSplyAgmtPlnPkMap.get(newDsContrDtlPk);

                    // (1) Both of Plan ID is null(Do Nothing)
                    if (!ZYPCommonFunc.hasValue(newSplyAgmtPlnPk) && !ZYPCommonFunc.hasValue(oldSplyAgmtPlnPk)) {
                        this.oldSplyAgmtPlnPkMap.remove(newDsContrDtlPk);
                        continue;

                        // (2) New Plan ID is null(Delete Only)
                    } else if (!ZYPCommonFunc.hasValue(newSplyAgmtPlnPk) && ZYPCommonFunc.hasValue(oldSplyAgmtPlnPk)) {
                        delList.add(newDsContrDtlPk);
                        this.oldSplyAgmtPlnPkMap.remove(newDsContrDtlPk);

                        // (3) Old Plan ID is null(Insert Only)
                    } else if (ZYPCommonFunc.hasValue(newSplyAgmtPlnPk) && !ZYPCommonFunc.hasValue(oldSplyAgmtPlnPk)) {
                        insList.add(newSplyAgmtPlnInfo);
                        this.oldSplyAgmtPlnPkMap.remove(newDsContrDtlPk);

                    } else if (ZYPCommonFunc.hasValue(newSplyAgmtPlnPk) && ZYPCommonFunc.hasValue(oldSplyAgmtPlnPk)) {
                        // (4) Modified(Delete and Insert)
                        if (!isEqualBigDecimal(newSplyAgmtPlnPk, oldSplyAgmtPlnPk)) {
                            delList.add(newDsContrDtlPk);
                            insList.add(newSplyAgmtPlnInfo);
                            this.oldSplyAgmtPlnPkMap.remove(newDsContrDtlPk);
                            // (5) Same(Do Nothing)
                        } else if (isEqualBigDecimal(newSplyAgmtPlnPk, oldSplyAgmtPlnPk)) {
                            this.oldSplyAgmtPlnPkMap.remove(newDsContrDtlPk);
                            continue;
                        }
                    } else {
                        // Un Expected Case !!!
                    }
                    // Un Matched dsContrDtlPk(New Plan ID Only)
                } else {
                    // (6) New Plan ID is not null(Insert Only)
                    if (ZYPCommonFunc.hasValue(newSplyAgmtPlnPk)) {
                        insList.add(newSplyAgmtPlnInfo);
                        // (7) New Plan ID is null(Do Nothing)
                    } else if (!ZYPCommonFunc.hasValue(newSplyAgmtPlnPk)) {
                        continue;
                    }
                }
            }
        }

        // Un Matched dsContrDtlPk(Old Plan ID Only)
        if (this.oldSplyAgmtPlnPkMap != null && this.oldSplyAgmtPlnPkMap.size() > 0) {
            List<BigDecimal> removePks = new ArrayList<BigDecimal>(this.oldSplyAgmtPlnPkMap.size()); // L3
            for (Entry<BigDecimal, BigDecimal> oldSplyAgmtPln : this.oldSplyAgmtPlnPkMap.entrySet()) {
                BigDecimal oldDsContrDtlPk = oldSplyAgmtPln.getKey();
                BigDecimal oldSplyAgmtPlnPk = oldSplyAgmtPln.getValue();

                // (8) Old Plan ID is not null(Delete Only)
                if (ZYPCommonFunc.hasValue(oldSplyAgmtPlnPk)) {
                    delList.add(oldDsContrDtlPk);
                    removePks.add(oldDsContrDtlPk); // L3
//                    this.oldSplyAgmtPlnPkMap.remove(oldDsContrDtlPk); // L3
                    // (9) Old Plan ID is null(Do Nothing)
                } else if (!ZYPCommonFunc.hasValue(oldSplyAgmtPlnPk)) {
                    removePks.add(oldDsContrDtlPk); // L3
//                    this.oldSplyAgmtPlnPkMap.remove(oldDsContrDtlPk); // L3
                    continue;
                }
            }
            // L3
            for (BigDecimal pk : removePks) {
                this.oldSplyAgmtPlnPkMap.remove(pk);
            }
        }

        delSchdCratTmplList(delList, msgMap);
        registSchdCratTmplList(insList, msgMap);
    }

    private void delSchdCratTmplList(List<BigDecimal> delList, S21ApiMessageMap msgMap) {
        // Delete
        delSchdCratTmpl(delList, msgMap);
        delSchdCratTmplLine(delList, msgMap);
    }

    private void delSchdCratTmpl(List<BigDecimal> delList, S21ApiMessageMap msgMap) {
        // Delete
        for (BigDecimal dsContrDtlPk : delList) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("dsContrDtlPk", dsContrDtlPk);

            List<BigDecimal> delTmplPkList = this.ssmBatchClient.queryObjectList("getSchdCratTmplPkForDelete", param);

            if (delTmplPkList == null || delTmplPkList.size() == 0) {
                continue;
            }

            for (BigDecimal schdCratTmplPk : delTmplPkList) {
                SCHD_CRAT_TMPLTMsg tMsg = new SCHD_CRAT_TMPLTMsg();
                tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                tMsg.schdCratTmplPk.setValue(schdCratTmplPk);

                tMsg = (SCHD_CRAT_TMPLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
                if (tMsg != null) {
                    S21ApiTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        setErrStatus(msgMap, NSZM1221E); //mod u.kim 2018/03/06 QC#24056
                    }
                }
            }
        }
        return;
    }

    private void delSchdCratTmplLine(List<BigDecimal> delList, S21ApiMessageMap msgMap) {
        // Delete
        for (BigDecimal dsContrDtlPk : delList) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("dsContrDtlPk", dsContrDtlPk);

            List<Map<String, BigDecimal>> delTmplLinePkList = this.ssmBatchClient.queryObjectList("getSchdCratTmplLinePkForDelete", param);

            if (delTmplLinePkList == null || delTmplLinePkList.size() == 0) {
                continue;
            }

            for (Map<String, BigDecimal> schdCratTmplLinePk : delTmplLinePkList) {
                SCHD_CRAT_TMPL_LINETMsg tMsg = new SCHD_CRAT_TMPL_LINETMsg();
                tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                tMsg.schdCratTmplPk.setValue(schdCratTmplLinePk.get("SCHD_CRAT_TMPL_PK"));
                tMsg.schdCratTmplLineNum.setValue(schdCratTmplLinePk.get("SCHD_CRAT_TMPL_LINE_NUM"));

                tMsg = (SCHD_CRAT_TMPL_LINETMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
                if (tMsg != null) {
                    S21ApiTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        setErrStatus(msgMap, NSZM1222E); //mod u.kim 2018/03/06 QC#24056
                    }
                }
            }
        }
        return;
    }

    private void registSchdCratTmplList(List<List<Map<String, Object>>> insList, S21ApiMessageMap msgMap) {
        // Insert
        if (insList != null && insList.size() > 0) {
            callCreationOfScheduleViaTheDealConfiguratorApi(insList, msgMap);
        }
    }

    private void callCreationOfScheduleViaTheDealConfiguratorApi(List<List<Map<String, Object>>> insList, S21ApiMessageMap msgMap) {
        if (insList == null || insList.size() == 0) {
            return;
        }

        for (List<Map<String, Object>> scheduleTemplateList : insList) {
            BigDecimal dsContrDtlPk = (BigDecimal) scheduleTemplateList.get(0).get("DS_CONTR_DTL_PK");
            BigDecimal dsContrPk = (BigDecimal) scheduleTemplateList.get(0).get("DS_CONTR_PK");
            BigDecimal splyBaseAmt = (BigDecimal) scheduleTemplateList.get(0).get("SPLY_BASE_AMT");
            BigDecimal qtyContrCapQty = (BigDecimal) scheduleTemplateList.get(0).get("QTY_CONTR_CAP_QTY");
            String refCpoOrdNum = (String) scheduleTemplateList.get(0).get("CPO_ORD_NUM");
            String splyAgmtPlnTpCd = (String) scheduleTemplateList.get(0).get("SPLY_AGMT_PLN_TP_CD");// S21_NA#22258 ADD K.Ishizuka

            NWZC172001PMsg schdTmplPMsg = new NWZC172001PMsg();
            ZYPEZDItemValueSetter.setValue(schdTmplPMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(schdTmplPMsg.xxRqstTpCd, NWZC172001Constant.RQST_TP_TMPL);
            ZYPEZDItemValueSetter.setValue(schdTmplPMsg.dsContrPk, dsContrPk);
            ZYPEZDItemValueSetter.setValue(schdTmplPMsg.dsContrDtlPk, dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(schdTmplPMsg.splyBaseAmt, splyBaseAmt);
            ZYPEZDItemValueSetter.setValue(schdTmplPMsg.qtyContrCapQty, qtyContrCapQty);
            ZYPEZDItemValueSetter.setValue(schdTmplPMsg.refCpoOrdNum, refCpoOrdNum);

            Map<String, String> cpoInfo = getCpoInfo(refCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(schdTmplPMsg.dsOrdTpCd, cpoInfo.get("DS_ORD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(schdTmplPMsg.custIssPoNum, cpoInfo.get("CUST_ISS_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(schdTmplPMsg.custIssPoDt, cpoInfo.get("CUST_ISS_PO_DT"));
            ZYPEZDItemValueSetter.setValue(schdTmplPMsg.sysSrcCd, cpoInfo.get("SYS_SRC_CD"));
            ZYPEZDItemValueSetter.setValue(schdTmplPMsg.sysSrcRefNum, cpoInfo.get("SYS_SRC_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(schdTmplPMsg.cpoSrcTpCd, cpoInfo.get("CPO_SRC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(schdTmplPMsg.ordSrcRefNum, cpoInfo.get("ORD_SRC_REF_NUM"));

            // START 2017/08/09 K.Kim [QC#20228, ADD]
            //Map<String, String> ordCatgBizCtxInfo = getOrdCatgBizCtxInfo(cpoInfo.get("DS_ORD_CATG_CD"), cpoInfo.get("DS_ORD_TP_CD"));// S21_NA#22258 MOD K.Ishizuka
            Map<String, String> ordCatgBizCtxInfo = getOrdCatgBizCtxInfo(cpoInfo.get("DS_ORD_CATG_CD"), cpoInfo.get("DS_ORD_TP_CD"), splyAgmtPlnTpCd);// S21_NA#22258 MOD K.Ishizuka 
            if (ordCatgBizCtxInfo != null){
                ZYPEZDItemValueSetter.setValue(schdTmplPMsg.dsOrdTpCd, ordCatgBizCtxInfo.get("SCD_BIZ_CTX_ATTRB_TXT"));
            }
            // END 2017/08/09 K.Kim [QC#20228, ADD]

            BigDecimal termMthNum = getTermMthNum(dsContrDtlPk);
            // 2020/03/10 QC#56125 Del Start
//            BigDecimal termPerMonths = null;
//            if (termMthNum != null) {
//                // START 2019/02/26 K.Fujimoto [QC#30525,MOD]
//                // termPerMonths = termMthNum.divide(MTH_12).setScale(0, BigDecimal.ROUND_DOWN);
//                termPerMonths = termMthNum.divide(MTH_12, 0, BigDecimal.ROUND_DOWN);
//                // END   2019/02/26 K.Fujimoto [QC#30525,MOD]
//            }
             // 2020/03/10 QC#56125 Del End

            List<NWZC172002PMsg> linePMsgList = new ArrayList<NWZC172002PMsg>();
            for (Map<String, Object> scheduleTemplate : scheduleTemplateList) {
                NWZC172002PMsg schdTmplLinePMsg = new NWZC172002PMsg();
                String mdseCd = (String) scheduleTemplate.get("MDSE_CD");
                BigDecimal otmShipQty = (BigDecimal) scheduleTemplate.get("SPLY_AGMT_PLN_QTY");
                BigDecimal firstShipQty = (BigDecimal) scheduleTemplate.get("SPLY_AGMT_PLN_FIRST_QTY");
                String shpgIntvlCd = (String) scheduleTemplate.get("SPLY_AGMT_FREQ_TP_CD");

                BigDecimal shpgIntvlMthNum = getShpgIntvlMthNum(shpgIntvlCd);
                // 2020/03/10 QC#56125 Del Start
//                BigDecimal monthsPerIntvl = null;
//                if (shpgIntvlMthNum != null) {
//                    if (BigDecimal.ZERO.compareTo(shpgIntvlMthNum) == 0) {
//                        monthsPerIntvl = BigDecimal.ZERO;
//                    } else {
//                        // START 2019/02/26 K.Fujimoto [QC#30525,MOD]
//                        // monthsPerIntvl = MTH_12.divide(shpgIntvlMthNum).setScale(0, BigDecimal.ROUND_DOWN);
//                        monthsPerIntvl = MTH_12.divide(shpgIntvlMthNum, 0, BigDecimal.ROUND_DOWN);
//                        // START 2019/02/26 K.Fujimoto [QC#30525,MOD]
//                    }
//                }
                // 2020/03/10 QC#56125 Del End

                BigDecimal schdAllwQty = null;
                if (termMthNum != null && shpgIntvlMthNum != null) {
                    // 2020/03/10 QC#56125 Mod Start
                    // schdAllwQty = otmShipQty.multiply(monthsPerIntvl).multiply(termPerMonths);
                    if (BigDecimal.ZERO.compareTo(shpgIntvlMthNum) == 0) {
                        schdAllwQty = BigDecimal.ZERO;
                    } else {
                        BigDecimal count = termMthNum.divide(shpgIntvlMthNum, 0, BigDecimal.ROUND_DOWN);
                        schdAllwQty = otmShipQty.multiply(count);
                    }
                    // 2020/03/10 QC#56125 Mod End
                }

                ZYPEZDItemValueSetter.setValue(schdTmplLinePMsg.dsContrDtlPk, dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(schdTmplLinePMsg.mdseCd, mdseCd);
                ZYPEZDItemValueSetter.setValue(schdTmplLinePMsg.schdAllwQty, schdAllwQty);
                ZYPEZDItemValueSetter.setValue(schdTmplLinePMsg.otmShipQty, otmShipQty);
                ZYPEZDItemValueSetter.setValue(schdTmplLinePMsg.shpgIntvlCd, shpgIntvlCd);
                ZYPEZDItemValueSetter.setValue(schdTmplLinePMsg.firstShipQty, firstShipQty);

                linePMsgList.add(schdTmplLinePMsg);
            }
            // execute API
            (new NWZC172001()).execute(schdTmplPMsg, linePMsgList, this.onBatchType);
            if (S21ApiUtil.isXxMsgId(schdTmplPMsg)) {
                List<String> msgIdList = S21ApiUtil.getXxMsgIdList(schdTmplPMsg);
                for (String msgId : msgIdList) {
                    setErrStatus(msgMap, msgId); //mod u.kim 2018/03/06 QC#24056;
                }
            }
            for (NWZC172002PMsg linePMsg : linePMsgList) {
                if (S21ApiUtil.isXxMsgId(linePMsg)) {
                    List<String> msgIdList = S21ApiUtil.getXxMsgIdList(linePMsg);
                    for (String msgId : msgIdList) {
                        setErrStatus(msgMap, msgId); //mod u.kim 2018/03/06 QC#24056;
                    }
                }
            }
        }
        return;
    }

    private BigDecimal getTermMthNum(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
        tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        tMsg.dsContrDtlPk.setValue(dsContrDtlPk);
        tMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.fromPerMthNum) && ZYPCommonFunc.hasValue(tMsg.toPerMthNum)) {
            return tMsg.toPerMthNum.getValue().subtract(tMsg.fromPerMthNum.getValue()).add(BigDecimal.ONE);
        }
        return null;
    }

    private BigDecimal getShpgIntvlMthNum(String shpgIntvlCd) {
        SHPG_INTVLTMsg tMsg = new SHPG_INTVLTMsg();
        tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        tMsg.shpgIntvlCd.setValue(shpgIntvlCd);
        tMsg = (SHPG_INTVLTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.shpgIntvlMthNum.getValue();
        }
        return null;
    }

    private Map<String, String> getCpoInfo(String refCpoOrdNum) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", refCpoOrdNum);

        List<Map<String, String>> cpoInfoList = this.ssmBatchClient.queryObjectList("getCpoInfo", param);

        if (cpoInfoList == null || cpoInfoList.size() == 0) {
            return null;
        }

        return cpoInfoList.get(0);
    }

    // START 2017/08/09 K.Kim [QC#20228, ADD]
    //private Map<String, String> getOrdCatgBizCtxInfo(String dsOrdCatgCd, String dsOrdTpCd) { // S21_NA#22258 MOD K.Ishizuka
    private Map<String, String> getOrdCatgBizCtxInfo(String dsOrdCatgCd, String dsOrdTpCd, String splyAgmtPlnTpCd) { // S21_NA#22258 MOD K.Ishizuka
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsOrdCatgCd", dsOrdCatgCd);
        param.put("dsOrdTpCd", dsOrdTpCd);
        param.put("splyAgmtPlnTpCd", splyAgmtPlnTpCd);// S21_NA#22258 ADD K.Ishizuka
        param.put("ordCatgCtxTpCd", STR_CONST_SCHD_ORD_RSN_MAP);

        List<Map<String, String>> ordCatgBizCtxInfoList = this.ssmBatchClient.queryObjectList("getOrdCatgBizCtxInfo", param);

        if (ordCatgBizCtxInfoList == null || ordCatgBizCtxInfoList.size() == 0) {
            return null;
        }

        return ordCatgBizCtxInfoList.get(0);
    }
    // END 2017/08/09 K.Kim [QC#20228, ADD]

    private void removeSchdCratTmplWOConfig(S21ApiMessageMap msgMap) {
        NSZC115001PMsg pMsg = (NSZC115001PMsg) msgMap.getPmsg();
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("refCpoOrdNum", pMsg.refCpoOrdNum.getValue());

        List<Map<String, BigDecimal>> delTmplList = this.ssmBatchClient.queryObjectList("getSchdCratTmplWOConfig", param);

        if (delTmplList == null || delTmplList.size() == 0) {
            return;
        }

        for (Map<String, BigDecimal> schdCratTmplLinePk : delTmplList) {
            SCHD_CRAT_TMPLTMsg tMsg = new SCHD_CRAT_TMPLTMsg();
            tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            tMsg.schdCratTmplPk.setValue(schdCratTmplLinePk.get("SCHD_CRAT_TMPL_PK"));
            tMsg = (SCHD_CRAT_TMPLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
            if (tMsg != null) {
                S21ApiTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    setErrStatus(msgMap, NSZM1221E); //mod u.kim 2018/03/06 QC#24056
                }
            }

            SCHD_CRAT_TMPL_LINETMsg lineTMsg = new SCHD_CRAT_TMPL_LINETMsg();
            lineTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            lineTMsg.schdCratTmplPk.setValue(schdCratTmplLinePk.get("SCHD_CRAT_TMPL_PK"));
            lineTMsg.schdCratTmplLineNum.setValue(schdCratTmplLinePk.get("SCHD_CRAT_TMPL_LINE_NUM"));

            lineTMsg = (SCHD_CRAT_TMPL_LINETMsg) S21ApiTBLAccessor.findByKeyForUpdate(lineTMsg);
            if (lineTMsg != null) {
                S21ApiTBLAccessor.logicalRemove(lineTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(lineTMsg.getReturnCode())) {
                    setErrStatus(msgMap, NSZM1222E); //mod u.kim 2018/03/06 QC#24056
                }
            }
        }
    }

    private void addMsgForSvcDtlList(S21ApiMessageMap msgMap, String msgId, NSZC115001_svcDtlListPMsg pMsg) {
        setErrStatus(msgMap, msgId); //mod u.kim 2018/03/06 QC#24056;
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, msgId);
    }

    private void addMsgForSvcConfigRefList(S21ApiMessageMap msgMap, String msgId, NSZC115001_svcConfigRefListPMsg pMsg) {
        setErrStatus(msgMap, msgId); //mod u.kim 2018/03/06 QC#24056;
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, msgId);
    }

    private void addMsgForSvcPrcList(S21ApiMessageMap msgMap, String msgId, NSZC115001_svcPrcListPMsg pMsg) {
        setErrStatus(msgMap, msgId); //mod u.kim 2018/03/06 QC#24056;
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, msgId);
    }

    private void addMsgForSvcUsgPrcList(S21ApiMessageMap msgMap, String msgId, NSZC115001_svcUsgPrcListPMsg pMsg) {
        setErrStatus(msgMap, msgId); //mod u.kim 2018/03/06 QC#24056;
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, msgId);
    }

    private void addMsgForAddlBasePrcList(S21ApiMessageMap msgMap, String msgId, NSZC115001_svcAddlBasePrcListPMsg pMsg) {
        setErrStatus(msgMap, msgId); //mod u.kim 2018/03/06 QC#24056;
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, msgId);
    }

    private void addMsgForSvcAddlChrgPrcList(S21ApiMessageMap msgMap, String msgId, NSZC115001_svcAddlChrgPrcListPMsg pMsg) {
        setErrStatus(msgMap, msgId); //mod u.kim 2018/03/06 QC#24056;
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, msgId);
    }

    // START 2017/08/25 U.Kim [QC#18345, ADD]
    // START 2017/08/31 U.Kim [QC#18345-2, DEL]
    // private void addMsgForSvcAddContr(S21ApiMessageMap msgMap, String msgId, NSZC115001_svcDtlListPMsg pMsg) {
        // String dsContrNum = "";
        // DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
        // ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, pMsg.dsContrPk_AD);
        // tMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(tMsg);
        // if (tMsg != null) {
        //     dsContrNum = tMsg.dsContrNum.getValue();
        // }
        // msgMap.addXxMsgIdWithPrm(msgId, new String[] {dsContrNum });
      //  ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, msgId);
    //}
    // END 2017/08/31 U.Kim [QC#18345-2, DEL]
    // END 2017/08/25 U.Kim [QC#18345, ADD]

    private DS_CONTR_INTFC_DEF_RULETMsg getDsContrIntfcDefRule(NSZC115001PMsg inPrmPMsg) {
        if (this.dsContrIntfcDefRuleTMsg != null) {
            return this.dsContrIntfcDefRuleTMsg;
        }

        DS_CONTR_INTFC_DEF_RULETMsg param = new DS_CONTR_INTFC_DEF_RULETMsg();
        // mod start 2017/08/08 QC#18799
        // START 2017/07/26 K.Kojima [QC#18203,MOD]
        // param.setSQLID("001");
        // param.setSQLID("201");
        // END 2017/07/26 K.Kojima [QC#18203,MOD]
        param.setSQLID("202");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("contrIntfcSrcTpCd01", CONTR_INTFC_SRC_TP.ORDER);
        param.setConditionValue("dsContrClsCd01", getDsContrClsCd(inPrmPMsg));
        param.setConditionValue("svcLineBizCd01", getSvcLineBizCd(inPrmPMsg));
        param.setConditionValue("effFromDt01", inPrmPMsg.slsDt.getValue());
        param.setConditionValue("enblFlg01", ZYPConstant.FLG_ON_Y);
        // mod end 2017/08/08 QC#18799
        DS_CONTR_INTFC_DEF_RULETMsgArray list = (DS_CONTR_INTFC_DEF_RULETMsgArray) S21ApiTBLAccessor.findByCondition(param);
        if (list.getValidCount() > 0) {
            this.dsContrIntfcDefRuleTMsg = (DS_CONTR_INTFC_DEF_RULETMsg) list.get(0);
            return this.dsContrIntfcDefRuleTMsg;
        } else {
            return null;
        }
    }

    private String getPmtTermCashDiscCd(NSZC115001PMsg inPrmPMsg, String sellToCustCd) {
        DS_CR_CARDTMsg dsCrCardTMsg = getDsCrCardTMsg(inPrmPMsg);
        if (dsCrCardTMsg != null) {
            return PMT_TERM_CASH_DISC.CREDIT_CARD;
        }

        DS_ACCT_CR_PRFLTMsg param = new DS_ACCT_CR_PRFLTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsAcctNum01", sellToCustCd);
        DS_ACCT_CR_PRFLTMsgArray list = (DS_ACCT_CR_PRFLTMsgArray) S21ApiTBLAccessor.findByCondition(param);
        if (list.getValidCount() > 0) {
            return list.no(0).pmtTermCashDiscCd.getValue();
        } else {
            return null;
        }
    }

    private String getCcyCd() {
        if (this.ccyCd != null) {
            return this.ccyCd;
        }

        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        tMsg = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            this.ccyCd = tMsg.stdCcyCd.getValue();
            return this.ccyCd;
        } else {
            return null;
        }
    }

    // START 2017/12/27 U.Kim [QC#23104, ADD]
    private BigDecimal getDealCcyDigit() {
        if (this.dealCcyDigit != null) {
            return this.dealCcyDigit;
        }

        CCYTMsg ccyTMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(ccyTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ccyTMsg.ccyCd, getCcyCd());
        ccyTMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(ccyTMsg);
        if (ccyTMsg != null) {
            this.dealCcyDigit = ccyTMsg.aftDeclPntDigitNum.getValue();
            return this.dealCcyDigit;
        } else {
            return BigDecimal.ZERO;
        }
    }
    // END 2017/12/27 U.Kim [QC#23104, ADD]

    // mod start 2017/08/09 QC#18799
    // mod start 2017/09/01 QC#20882
    // START 2022/03/22 [QC#59683, MOD]
//    private String getInvSeptBaseUsgFlg(NSZC115001PMsg inPrmPMsg, String sellToCustCd, String dsContrCatgCd) {
    private String getDsInvTgtrTpCd(NSZC115001PMsg inPrmPMsg, String sellToCustCd, String dsContrCatgCd) {
    // END   2022/03/22 [QC#59683, MOD]
    // mod end 2017/09/01 QC#20882
//        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
//        if (dsContrIntfcDefRuleTMsg != null) {
//            return getFlgValue(dsContrIntfcDefRuleTMsg.invSeptBaseUsgFlg.getValue());
//        } else {
//            return ZYPConstant.FLG_OFF_N;
//        }
        // START 2018/01/30 M.Naito [QC#21349, DEL]
        // add start 2017/09/01 QC#20882
//        if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
//            return ZYPConstant.FLG_OFF_N;
//        }
        // add end 2017/09/01 QC#20882
        // END 2018/01/30 M.Naito [QC#21349, DEL]

        // START 2022/03/22 [QC#59683, ADD]
        String dsInvTgtrTpCd = null;
        // END   2022/03/22 [QC#59683, ADD]
        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
        if (dsContrIntfcDefRuleTMsg != null) {
            if (ZYPConstant.FLG_ON_Y.equals(dsContrIntfcDefRuleTMsg.invSeptBaseUsgMstrFlg.getValue())) {
                SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
                sellToCustTMsg.setSQLID("990");
                sellToCustTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                sellToCustTMsg.setConditionValue("sellToCustCd01", sellToCustCd);
                SELL_TO_CUSTTMsgArray sellToCustTMsgAry = (SELL_TO_CUSTTMsgArray) searchTblByCond(sellToCustTMsg, sellToCustCd);
                if (sellToCustTMsgAry.length() > 0) {
                    // START 2022/03/22 [QC#59683, MOD]
//                    if (ZYPConstant.FLG_ON_Y.equals(sellToCustTMsgAry.no(0).dsBillTgtrFlg.getValue())) {
                    dsInvTgtrTpCd = sellToCustTMsgAry.no(0).dsInvTgtrTpCd.getValue();
//                        return ZYPConstant.FLG_OFF_N;
//                    } else {
//                        return ZYPConstant.FLG_ON_Y;
//                    }
                    // END   2022/03/22 [QC#59683, MOD]
                }
            } else {
                // START 2022/03/22 [QC#59683, MOD]
//                return dsContrIntfcDefRuleTMsg.invSeptBaseUsgFlg.getValue();
                dsInvTgtrTpCd = dsContrIntfcDefRuleTMsg.dsInvTgtrTpCd.getValue();
                // END   2022/03/22 [QC#59683, MOD]
            }
        }
        // START 2022/03/22 [QC#59683, MOD]
//        return ZYPConstant.FLG_OFF_N;
        if (!ZYPCommonFunc.hasValue(dsInvTgtrTpCd) || (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && DS_INV_TGTR_TP.INVOICE_INDIVIDUALLY.equals(dsInvTgtrTpCd))) {
            dsInvTgtrTpCd = DS_INV_TGTR_TP.BILL_ALL_BASE_TOGETHER_AND_ALL_USAGE_TOGETHER;
        }
        return dsInvTgtrTpCd;
        // END   2022/03/22 [QC#59683, MOD]
    }
    // mod end 2017/08/09 QC#18799

    private String getDsContrCratPsnCd(String userID) {
        if (userID != null) {
            if (userID.length() > LENGTH_OF_PSN_CD) {
                return userID.substring(0, LENGTH_OF_PSN_CD);
            } else {
                return userID;
            }
        } else {
            return null;
        }
    }

    private String getFlgValue(String flg) {
        if (ZYPConstant.FLG_ON_Y.equals(flg)) {
            return ZYPConstant.FLG_ON_Y;
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }

    private CPOTMsg getCPOTMsg(NSZC115001PMsg inPrmPMsg) {
        if (this.cpoTMsg != null) {
            return this.cpoTMsg;
        }

        CPOTMsg tMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, inPrmPMsg.refCpoOrdNum);
        this.cpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(tMsg);
        return this.cpoTMsg;
    }

    private String getTocCd(NSZC115001PMsg inPrmPMsg) {
        if (this.tocCd != null) {
            return this.tocCd;
        }

        // START 2017/11/20 [QC#21698, MOD]
        String emsdTocCd = getEmsdTocd(inPrmPMsg);
        if (ZYPCommonFunc.hasValue(emsdTocCd)) {
            return this.tocCd = emsdTocCd;
        }

        CPOTMsg cpoTMsg = getCPOTMsg(inPrmPMsg);
        if (cpoTMsg == null || !ZYPCommonFunc.hasValue(cpoTMsg.slsRepTocCd)) {
            return null;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("tocCd", cpoTMsg.slsRepTocCd.getValue());
        // START 2018/01/09 M.Kidokoro [QC#20635,ADD]
        param.put("svcLineBizCd", getSvcLineBizCd(inPrmPMsg));
        // END 2018/01/09 M.Kidokoro [QC#20635,ADD]
        String tocCd = (String) this.ssmBatchClient.queryObject("getTocCd", param);

        if (ZYPCommonFunc.hasValue(tocCd)) {
            this.tocCd = tocCd;
        } else {
            this.tocCd = cpoTMsg.slsRepTocCd.getValue();
        }
        // END 2017/11/20 [QC#21698, MOD]
        return this.tocCd;
    }

    // START 2017/11/20 [QC#21698, ADD]
    private String getEmsdTocd(NSZC115001PMsg inPrmPMsg) {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", inPrmPMsg.refCpoOrdNum);
        param.put("emsdContrBrRep", ORD_CATG_CTX_TP_EMSD_CONTR_BR_REP);
        return (String) this.ssmBatchClient.queryObject("getEmsdTocd", param);

    }
    // END 2017/11/20 [QC#21698, MOD]

    private String getMtrEstTpCd(NSZC115001PMsg inPrmPMsg) {
        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
        if (dsContrIntfcDefRuleTMsg != null) {
            return dsContrIntfcDefRuleTMsg.mtrEstTpCd.getValue();
        } else {
            return null;
        }
    }

    private String getPrcAllocByMachQtyFlg(NSZC115001PMsg inPrmPMsg, String dsContrCatgCd) {
        if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            return ZYPConstant.FLG_OFF_N;
        }

        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
        if (dsContrIntfcDefRuleTMsg != null) {
            return getFlgValue(dsContrIntfcDefRuleTMsg.prcAllocByMachQtyFlg.getValue());
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }

    private void getContractBranch(String cpoOrdNum) {
        if (this.svcContrBrCd != null && this.contrAdminPsnCd != null) {
            return;
        }

        // START 2017/11/20 [QC#21700, MOD]
        Map<String, Object> eMsdtargetMap = getEmsdContractBranch(cpoOrdNum);
        if (eMsdtargetMap != null) {
            this.svcContrBrCd = (String) eMsdtargetMap.get("SVC_CONTR_BR_CD_OCBC");
            this.contrAdminPsnCd = (String) eMsdtargetMap.get("CONTR_ADMIN_PSN_CD_OCBC");
            return;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        // START 2018/01/09 M.Kidokoro [QC#20635,ADD]
        param.put("svcLineBizCd", this.svcLineBizCd);
        // END 2018/01/09 M.Kidokoro [QC#20635,ADD]
        Map<String, Object> targetMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getContractBranch", param);

        if (targetMap != null) {
            this.svcContrBrCd = (String) targetMap.get("SVC_CONTR_BR_CD_SCCBM");
            this.contrAdminPsnCd = (String) targetMap.get("CONTR_ADMIN_PSN_CD");
        } else {
            this.svcContrBrCd = null;
            this.contrAdminPsnCd = null;
        }
        // END 2017/11/20 [QC#21700, MOD]
    }

    // START 2017/11/20 [QC#21700, ADD]
    private Map<String, Object> getEmsdContractBranch(String cpoOrdNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("emsdContrBrRep", ORD_CATG_CTX_TP_EMSD_CONTR_BR_REP);
        
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getEmsdContractBranch", param);
    }
    // START 2017/11/20 [QC#21700, ADD]

    private String getSvcContrBrCd(String cpoOrdNum) {
        getContractBranch(cpoOrdNum);
        return this.svcContrBrCd;
    }

    private String getContrAdminPsnCd(String cpoOrdNum) {
        getContractBranch(cpoOrdNum);
        return this.contrAdminPsnCd;
    }

    private String getDsContrClsCd(NSZC115001PMsg inPrmPMsg) {
        // mod start 2017/08/09 QC#18799
//        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
//        if (dsContrIntfcDefRuleTMsg != null) {
//            return dsContrIntfcDefRuleTMsg.dsContrClsCd.getValue();
//        } else {
//            return null;
//        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", inPrmPMsg.refCpoOrdNum.getValue());
        param.put("svcContrCatg", ORD_CATG_CTX_TP_SVC_CONTR_CATG);
        return (String) this.ssmBatchClient.queryObject("getDsContrClsCd", param);
        // mod end 2017/08/09 QC#18799
    }

    // START 2018/03/13 K.Kojima [QC#24263,DEL]
    // private BigDecimal getCtacPsnPk(String cpoOrdNum) {
    //     if (this.ctacPsnPk != null) {
    //         return this.ctacPsnPk;
    //     }
    // 
    //     Map<String, Object> param = new HashMap<String, Object>();
    //     param.put("glblCmpyCd", this.glblCmpyCd);
    //     param.put("cpoOrdNum", cpoOrdNum);
    //     param.put("billToContact", CTAC_TP.BILL_TO_CONTACT);
    //     this.ctacPsnPk = (BigDecimal) this.ssmBatchClient.queryObject("getCtacPsnPk", param);
    //     return this.ctacPsnPk;
    // }
    // END 2018/03/13 K.Kojima [QC#24263,DEL]

    private String getSvcLineBizCd(NSZC115001PMsg inPrmPMsg) {
        if (this.svcLineBizCd != null) {
            return this.svcLineBizCd;
        }

        CPOTMsg cpoTMsg = getCPOTMsg(inPrmPMsg);
        if (cpoTMsg == null) {
            return null;
        }

        // mod start 2017/10/05 QC#21542
//        DS_ORD_TP_PROC_DFNTMsg param = new DS_ORD_TP_PROC_DFNTMsg();
//        param.setSQLID("001");
//        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        param.setConditionValue("dsOrdTpCd01", cpoTMsg.dsOrdTpCd.getValue());
//        DS_ORD_TP_PROC_DFNTMsgArray list = (DS_ORD_TP_PROC_DFNTMsgArray) S21ApiTBLAccessor.findByCondition(param);
//        if (list.getValidCount() > 0) {
//            this.svcLineBizCd = list.no(0).lineBizTpCd.getValue();
//            return this.svcLineBizCd;
//        } else {
//            return null;
//        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsOrdTpCd", cpoTMsg.dsOrdTpCd.getValue());
        this.svcLineBizCd = (String) this.ssmBatchClient.queryObject("getSvcLineBizCd", param);
        return this.svcLineBizCd;
        // mod end 2017/10/05 QC#21542
    }

    private String getDsContrEdiCd(NSZC115001PMsg inPrmPMsg, String billByTpCd) {
        // START 2017/08/09 K.Kojima [QC#20527,DEL]
        // if (ZYPConstant.FLG_ON_Y.equals(getBaseChrgToLeaseCmpyFlg(inPrmPMsg, billByTpCd)) || ZYPConstant.FLG_ON_Y.equals(getUsgChrgToLeaseCmpyFlg(inPrmPMsg, billByTpCd))) {
        //     return DS_CONTR_EDI.CFS;
        // } else {
        //     return DS_CONTR_EDI.OTHER;
        // }
        // END 2017/08/09 K.Kojima [QC#20527,DEL]
        // START 2017/12/05 K.Kojima [QC#21551,ADD]
        // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
        // if (BILL_BY_TP.ALT_LEASE.equals(billByTpCd)) {
        if (!BILL_BY_TP.CFS.equals(billByTpCd) && !BILL_BY_TP.CFS_BASE_ONLY.equals(billByTpCd)) {
        // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
            // START 2018/06/11 T.Ogura [QC#24852,MOD]
//            return DS_CONTR_EDI.OTHER;
            return null;
            // END   2018/06/11 T.Ogura [QC#24852,MOD]
        }
        // END 2017/12/05 K.Kojima [QC#21551,ADD]
        // START 2017/08/09 K.Kojima [QC#20527,ADD]
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", inPrmPMsg.refCpoOrdNum.getValue());
        param.put("ordCatgCtxTpContrCfsEdi", "CONTR_CFS_EDI");
        param.put("flgY", ZYPConstant.FLG_ON_Y);
        BigDecimal countrCfsEdi = (BigDecimal) this.ssmBatchClient.queryObject("countContrCfsEdi", param);
        // START 2017/12/05 K.Kojima [QC#21551,MOD]
        // if (countrCfsEdi != null && countrCfsEdi.compareTo(BigDecimal.ZERO) > 0) {
        //     return DS_CONTR_EDI.CFS;
        // } else {
        //     return DS_CONTR_EDI.OTHER;
        // }
        if (countrCfsEdi == null || countrCfsEdi.compareTo(BigDecimal.ZERO) == 0) {
            // START 2018/06/11 T.Ogura [QC#24852,MOD]
//            return DS_CONTR_EDI.OTHER;
            return null;
            // END   2018/06/11 T.Ogura [QC#24852,MOD]
        }
        // END 2017/12/05 K.Kojima [QC#21551,MOD]
        // END 2017/08/09 K.Kojima [QC#20527,ADD]
        // START 2017/12/05 K.Kojima [QC#21551,ADD]
        if (!ZYPConstant.FLG_ON_Y.equals(getBaseChrgToLeaseCmpyFlg(inPrmPMsg, billByTpCd)) && !ZYPConstant.FLG_ON_Y.equals(getUsgChrgToLeaseCmpyFlg(inPrmPMsg, billByTpCd))) {
            // START 2018/06/11 T.Ogura [QC#24852,MOD]
//            return DS_CONTR_EDI.OTHER;
            return null;
            // END   2018/06/11 T.Ogura [QC#24852,MOD]
        }
        return DS_CONTR_EDI.CFS;
        // END 2017/12/05 K.Kojima [QC#21551,ADD]
    }

    private void getBillByFlg(NSZC115001PMsg inPrmPMsg, String billByTpCd) {
        // START 2018/01/04 M.Kidokoro [QC#23167,ADD]
        this.baseChrgToLeaseCmpyFlg = null;
        this.usgChrgToLeaseCmpyFlg = null;

        if (this.baseChrgToLeaseCmpyFlgMap.containsKey(billByTpCd)) {
            this.baseChrgToLeaseCmpyFlg = this.baseChrgToLeaseCmpyFlgMap.get(billByTpCd);
        }
        if (this.usgChrgToLeaseCmpyFlgMap.containsKey(billByTpCd)) {
            this.usgChrgToLeaseCmpyFlg = this.usgChrgToLeaseCmpyFlgMap.get(billByTpCd);
        }
        // END 2018/01/04 M.Kidokoro [QC#23167,ADD]

        if (this.baseChrgToLeaseCmpyFlg != null && this.usgChrgToLeaseCmpyFlg != null) {
            return;
        }

        CPOTMsg cpoTMsg = getCPOTMsg(inPrmPMsg);
        if (cpoTMsg == null) {
            return;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsOrdCatgCd", cpoTMsg.dsOrdCatgCd.getValue());
        // START 2018/02/13 K.Kojima [QC#23357,ADD]
        param.put("dsOrdTpCd", cpoTMsg.dsOrdTpCd.getValue());
        // END 2018/02/13 K.Kojima [QC#23357,ADD]
        param.put("billByTpCd", billByTpCd);
        Map<String, Object> targetMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getBillByFlg", param);
        if (targetMap != null) {
            this.baseChrgToLeaseCmpyFlg = (String) targetMap.get("BILL_BY_BASE_FLG");
            this.usgChrgToLeaseCmpyFlg = (String) targetMap.get("BILL_BY_USG_FLG");
            // START 2018/01/04 M.Kidokoro [QC#23167,ADD]
            this.baseChrgToLeaseCmpyFlgMap.put(billByTpCd, this.baseChrgToLeaseCmpyFlg);
            this.usgChrgToLeaseCmpyFlgMap.put(billByTpCd, this.usgChrgToLeaseCmpyFlg);
            // END 2018/01/04 M.Kidokoro [QC#23167,ADD]
        } else {
            this.baseChrgToLeaseCmpyFlg = null;
            this.usgChrgToLeaseCmpyFlg = null;
        }
    }

    private String getBaseChrgToLeaseCmpyFlg(NSZC115001PMsg inPrmPMsg, String billByTpCd) {
        getBillByFlg(inPrmPMsg, billByTpCd);
        return getFlgValue(this.baseChrgToLeaseCmpyFlg);
    }

    private String getUsgChrgToLeaseCmpyFlg(NSZC115001PMsg inPrmPMsg, String billByTpCd) {
        getBillByFlg(inPrmPMsg, billByTpCd);
        return getFlgValue(this.usgChrgToLeaseCmpyFlg);
    }

    // mod start 2017/06/22 CSA Defect#19350
//    private String getLeaseCmpyCd(NSZC115001PMsg inPrmPMsg, String billByTpCd, String soldToCustLocCd) {
    private String getLeaseCmpyCd(NSZC115001PMsg inPrmPMsg, String billByTpCd) {
        getBillByFlg(inPrmPMsg, billByTpCd);
        if (ZYPConstant.FLG_ON_Y.equals(this.baseChrgToLeaseCmpyFlg) || ZYPConstant.FLG_ON_Y.equals(this.usgChrgToLeaseCmpyFlg)) {
//            return soldToCustLocCd;
            // mod start 2017/07/21 QC#19962
//            CPOTMsg cpoTMsg = getCPOTMsg(inPrmPMsg);
//            if (cpoTMsg == null) {
//                return null;
//            } else {
//                return cpoTMsg.billToCustCd.getValue();
//            }
            Map<String, Object> leaseCmpyMap = getContrLeaseCmpy(inPrmPMsg.refCpoOrdNum.getValue(), billByTpCd);
            if (leaseCmpyMap == null) {
                return null;
            }
            return (String) leaseCmpyMap.get("CONTR_LEASE_BILL_TO_CUST_CD");
            // mod end   2017/07/21 QC#19962
        } else {
            return null;
        }
    }
    // mod end 2017/06/22 CSA Defect#19350

    private SVC_MEMOTMsg getSvcMemoTMsg(BigDecimal dsContrPk) {
        SVC_MEMOTMsg param = new SVC_MEMOTMsg();
        // START 2017/07/28 [QC#20088, MOD]
        // param.setSQLID("007");
        param.setSQLID("013");
        // END   2017/07/28 [QC#20088, MOD]
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("svcMemoCatgCd01", SVC_MEMO_CATG.CONTRACT_MEMO);
        param.setConditionValue("svcMemoTpCd01", SVC_MEMO_TP.OVERRIDE_SHELL_CONTRACT);
        param.setConditionValue("dsContrPk01", dsContrPk);
        // START 2017/07/28 [QC#20088, ADD]
        param.setConditionValue("svcMemoTrxNm01", SVC_MEMO_TRX_NM_CPO_ORD_NUM);
        // END   2017/07/28 [QC#20088, ADD]
        SVC_MEMOTMsgArray list = (SVC_MEMOTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(param);
        if (list.getValidCount() > 0) {
            return (SVC_MEMOTMsg) list.no(0);
        } else {
            return null;
        }
    }

    private NSZC115001_svcDtlListPMsg getSvcDtlListItem(NSZC115001_svcDtlListPMsgArray svcDtlList, BigDecimal shellLineNum) {
        if (!ZYPCommonFunc.hasValue(shellLineNum)) {
            return null;
        }

        NSZC115001_svcDtlListPMsg svcDtlPMsg = null;
        for (int i = 0; i < svcDtlList.getValidCount(); i++) {
            svcDtlPMsg = svcDtlList.no(i);
            if (ZYPCommonFunc.hasValue(svcDtlPMsg.shellLineNum) && shellLineNum.compareTo(svcDtlPMsg.shellLineNum.getValue()) == 0) {
                return svcDtlPMsg;
            }
        }
        return null;
    }

    private NSZC115001_svcPrcListPMsg getSvcPrcListItem(NSZC115001_svcPrcListPMsgArray svcPrcList, BigDecimal shellLineNum, String dsOrdPosnNum, BigDecimal mdlId, boolean isFleet) {
        if (!ZYPCommonFunc.hasValue(shellLineNum) || (!isFleet && !ZYPCommonFunc.hasValue(mdlId)) || !ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
            return null;
        }

        NSZC115001_svcPrcListPMsg svcPrcPMsg = null;
        for (int i = 0; i < svcPrcList.getValidCount(); i++) {
            svcPrcPMsg = svcPrcList.no(i);
            if (!isFleet && !ZYPCommonFunc.hasValue(svcPrcPMsg.mdlId)) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(svcPrcPMsg.shellLineNum)) {
                continue;
            }

            if (isFleet) {
                if (shellLineNum.compareTo(svcPrcPMsg.shellLineNum.getValue()) == 0) {
                    return svcPrcPMsg;
                }
            }
            // 2018/05/07 QC#22482 Mod Start
//            if (mdlId.compareTo(svcPrcPMsg.mdlId.getValue()) == 0 && dsOrdPosnNum.equals(svcPrcPMsg.dsOrdPosnNum.getValue()) && shellLineNum.compareTo(svcPrcPMsg.shellLineNum.getValue()) == 0) {
//                return svcPrcPMsg;
//            }
            if (ZYPCommonFunc.hasValue(svcPrcPMsg.mdlId) && ZYPCommonFunc.hasValue(svcPrcPMsg.dsOrdPosnNum) && ZYPCommonFunc.hasValue(svcPrcPMsg.shellLineNum)) {
                if (mdlId.compareTo(svcPrcPMsg.mdlId.getValue()) == 0 && dsOrdPosnNum.equals(svcPrcPMsg.dsOrdPosnNum.getValue()) && shellLineNum.compareTo(svcPrcPMsg.shellLineNum.getValue()) == 0) {
                    return svcPrcPMsg;
                }
            }
            // 2018/05/07 QC#22482 Mod End
        }
        return null;
    }

    private NSZC115001_svcConfigRefListPMsg getSvcConfigRefListItem(NSZC115001_svcConfigRefListPMsgArray svcConfigRefList, BigDecimal shellLineNum, BigDecimal svcConfigMstrPk) {
        if (!ZYPCommonFunc.hasValue(shellLineNum) || !ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            return null;
        }

        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;
        for (int i = 0; i < svcConfigRefList.getValidCount(); i++) {
            svcConfigRefPMsg = svcConfigRefList.no(i);
            if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.shellLineNum)) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.svcConfigMstrPk)) {
                continue;
            }

            if (shellLineNum.compareTo(svcConfigRefPMsg.shellLineNum.getValue()) == 0 && svcConfigMstrPk.compareTo(svcConfigRefPMsg.svcConfigMstrPk.getValue()) == 0) {
                return svcConfigRefPMsg;
            }
        }
        return null;
    }

    private String getDsContrDtlTpCdForMachine(boolean usgExistFlg, boolean isAccessory) {
        if (isAccessory) {
            return DS_CONTR_DTL_TP.ACCESSORIES;
        }

        if (usgExistFlg) {
            return DS_CONTR_DTL_TP.BASE_AND_USAGE;
        } else {
            return DS_CONTR_DTL_TP.BASE_ONLY;
        }
    }

    private String getDdsOrdTpCd(NSZC115001PMsg inPrmPMsg) {
        CPOTMsg cpoTMsg = getCPOTMsg(inPrmPMsg);
        if (cpoTMsg != null) {
            return cpoTMsg.dsOrdTpCd.getValue();
        } else {
            return null;
        }
    }

    // mod start 2017/08/09 QC#18799
    private String getBaseBllgTmgCd(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg) {
        String sellToCustCd = svcDtlPMsg.sellToCustCd.getValue();
        // del start 2017/06/22 CSA Defect#19340
//        if (svcPrcPMsg != null && ZYPCommonFunc.hasValue(svcPrcPMsg.sellToCustCd)) {
//            sellToCustCd = svcPrcPMsg.sellToCustCd.getValue();
//        }
        // del end 2017/06/22 CSA Defect#19340

//        if (!ZYPCommonFunc.hasValue(sellToCustCd)) {
//            return BLLG_TMG_TP.ADVANCE;
//        }
        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
        sellToCustTMsg.setSQLID("990");
        sellToCustTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        sellToCustTMsg.setConditionValue("sellToCustCd01", sellToCustCd);
        SELL_TO_CUSTTMsgArray sellToCustTMsgAry = (SELL_TO_CUSTTMsgArray) searchTblByCond(sellToCustTMsg, sellToCustCd);
        if (sellToCustTMsgAry.length() > 0 && ZYPCommonFunc.hasValue(sellToCustTMsgAry.no(0).defBaseTpCd)) {
            return sellToCustTMsgAry.no(0).defBaseTpCd.getValue();
        }

        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
        if (dsContrIntfcDefRuleTMsg != null && ZYPCommonFunc.hasValue(dsContrIntfcDefRuleTMsg.baseBllgTmgCd)) {
            return dsContrIntfcDefRuleTMsg.baseBllgTmgCd.getValue();
        }
        return BLLG_TMG_TP.ADVANCE;
    }
    // mod end 2017/08/09 QC#18799

    private String getContrBllgDay(NSZC115001PMsg inPrmPMsg) {
        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
        if (dsContrIntfcDefRuleTMsg != null) {
            return dsContrIntfcDefRuleTMsg.contrBllgDay.getValue();
        } else {
            return null;
        }
    }

    private String getMtrBllgDay(NSZC115001PMsg inPrmPMsg, boolean usgExistFlg) {
        if (!usgExistFlg) {
            return null;
        }

        return getContrBllgDay(inPrmPMsg);
    }

    // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
    // private String getBaseBillToCustCd(NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg) {
    private String getBaseBillToCustCd(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg) {
    // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
        // START 2018/01/04 M.Kidokoro [QC#23167, ADD]
        getBillByFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(this.baseChrgToLeaseCmpyFlg)) {
            String leaseCmpyCd = getLeaseCmpyCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue());
            if (ZYPCommonFunc.hasValue(leaseCmpyCd)) {
                return leaseCmpyCd;
            }
        }
        // END 2018/01/04 M.Kidokoro [QC#23167, ADD]
        // START 2018/02/20 K.Kojima [QC#24234,ADD]
        if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk)) {
            if (this.updateBillToCustCd.containsKey(svcDtlPMsg.dsContrPk.getValue())) {
                return this.updateBillToCustCd.get(svcDtlPMsg.dsContrPk.getValue());
            }
        }
        // END 2018/02/20 K.Kojima [QC#24234,ADD]
        if (svcPrcPMsg != null && ZYPCommonFunc.hasValue(svcPrcPMsg.billToCustCd)) {
            return svcPrcPMsg.billToCustCd.getValue();
        } else {
            return svcDtlPMsg.soldToCustLocCd.getValue();
        }
    }

    // add start 2017/06/22 CSA Defect#19340
    // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
    // private String getBaseBillToCustCdForMod(NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
    private String getBaseBillToCustCdForMod(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
    // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
        // START 2018/01/04 M.Kidokoro [QC#23167, ADD]
        getBillByFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(this.baseChrgToLeaseCmpyFlg)) {
            String leaseCmpyCd = getLeaseCmpyCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue());
            if (ZYPCommonFunc.hasValue(leaseCmpyCd)) {
                return leaseCmpyCd;
            }
        }
        String billByTpCdB = this.beforeBillByTPMap.get(svcDtlPMsg.dsContrPk.getValue());
        String billByTpCdA = svcDtlPMsg.billByTpCd.getValue();
        boolean changeBillByTp = true;
        if (ZYPCommonFunc.hasValue(billByTpCdA) && ZYPCommonFunc.hasValue(billByTpCdA) && billByTpCdA.equals(billByTpCdB)) {
            changeBillByTp = false;
        }
        // END 2018/01/04 M.Kidokoro [QC#23167, ADD]
        // START 2018/02/20 K.Kojima [QC#24234,ADD]
        boolean changeHeaderBillToCustCd = this.updateBillToCustCd.containsKey(svcDtlPMsg.dsContrPk.getValue());
        // END 2018/02/20 K.Kojima [QC#24234,ADD]
        // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
        // if (svcPrcPMsg == null) {
        // START 2018/02/20 K.Kojima [QC#24234,MOD]
        // if (svcPrcPMsg == null && changeBillByTp == false) {
        if (svcPrcPMsg == null && changeBillByTp == false && changeHeaderBillToCustCd == false) {
        // END 2018/02/20 K.Kojima [QC#24234,MOD]
        // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
            return dsContrDtlTMsg.baseBillToCustCd.getValue();
        } else {
            // START 2018/02/20 K.Kojima [QC#24234,ADD]
            if (changeHeaderBillToCustCd) {
                return this.updateBillToCustCd.get(svcDtlPMsg.dsContrPk.getValue());
            }
            // END 2018/02/20 K.Kojima [QC#24234,ADD]
            // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
            // if (ZYPCommonFunc.hasValue(svcPrcPMsg.billToCustCd)) {
            if (svcPrcPMsg != null && ZYPCommonFunc.hasValue(svcPrcPMsg.billToCustCd)) {
            // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
                return svcPrcPMsg.billToCustCd.getValue();
            } else {
                return svcDtlPMsg.soldToCustLocCd.getValue();
            }
        }
    }
    // add end 2017/06/22 CSA Defect#19340

    // START 2018/01/04 M.Kidokoro [QC#23167, DEL]
    // private String getBaseBillToCustCdForFltAgg(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, boolean isFleet) {
    //     if (isFleet) {
    //         getBillByFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue());
    //         if (ZYPConstant.FLG_ON_Y.equals(this.baseChrgToLeaseCmpyFlg)) {
    //             String leaseCmpyCd = getLeaseCmpyCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue());
    //             if (ZYPCommonFunc.hasValue(leaseCmpyCd)) {
    //                 return leaseCmpyCd;
    //             }
    //         }
    //     }
    // 
    //     return getBaseBillToCustCd(svcDtlPMsg, svcPrcPMsg);
    // }
    // END 2018/01/04 M.Kidokoro [QC#23167, DEL]

    // mod start 2017/06/22 CSA Defect#19340
//    private String getBllgMtrBillToCustCd(NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg) {
    // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
    // private String getBllgMtrBillToCustCd(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg, boolean isFleet) {
    private String getBllgMtrBillToCustCd(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg) {
    // END 2018/01/04 M.Kidokoro [QC#23167, ADD]
        // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
        // if (isFleet) {
        //     getBillByFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue());
        //     if (ZYPConstant.FLG_ON_Y.equals(this.usgChrgToLeaseCmpyFlg)) {
        //         String leaseCmpyCd = getLeaseCmpyCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue());
        //         if (ZYPCommonFunc.hasValue(leaseCmpyCd)) {
        //             return leaseCmpyCd;
        //         }
        //     }
        // }
        getBillByFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(this.usgChrgToLeaseCmpyFlg)) {
            String leaseCmpyCd = getLeaseCmpyCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue());
            if (ZYPCommonFunc.hasValue(leaseCmpyCd)) {
                return leaseCmpyCd;
            }
        }
        // END 2018/01/04 M.Kidokoro [QC#23167, MOD]

        // START 2018/01/04 M.Kidokoro [QC#23167, MOD]
        // if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.billToCustCd)) {
        //     return svcUsgPrcPMsg.billToCustCd.getValue();
        // } else if (ZYPCommonFunc.hasValue(svcPrcPMsg.billToCustCd)) {
        //     return svcPrcPMsg.billToCustCd.getValue();
        // } else {
        //     return svcDtlPMsg.soldToCustLocCd.getValue();
        // }
        if (svcUsgPrcPMsg != null && ZYPCommonFunc.hasValue(svcUsgPrcPMsg.billToCustCd)) {
            return svcUsgPrcPMsg.billToCustCd.getValue();
        } else if (svcPrcPMsg != null && ZYPCommonFunc.hasValue(svcPrcPMsg.billToCustCd)) {
            return svcPrcPMsg.billToCustCd.getValue();
        } else {
            return svcDtlPMsg.soldToCustLocCd.getValue();
        }
        // END 2018/01/04 M.Kidokoro [QC#23167, MOD]
    }
    // mod end 2017/06/22 CSA Defect#19340

    private boolean isExistUsg(NSZC115001PMsg inPrmPMsg, NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg, BigDecimal mdlId, boolean isFleet) {

        BigDecimal prcMtrPkgPk = null;
        // add start 2017/06/14 CSA Defect#18821
        if (ZYPCommonFunc.hasValue(svcConfigRefPMsg.shellLineNum)) {
            for (int i = 0; i < inPrmPMsg.svcDtlList.getValidCount(); i++) {
                NSZC115001_svcDtlListPMsg svcDtlPMsg = inPrmPMsg.svcDtlList.no(i);
                if (!ZYPCommonFunc.hasValue(svcDtlPMsg.shellLineNum)) {
                    continue;
                }
                if (svcConfigRefPMsg.shellLineNum.getValue().compareTo(svcDtlPMsg.shellLineNum.getValue()) == 0) {
                    if (!ZYPCommonFunc.hasValue(svcDtlPMsg.usgBllgCycleCd)) {
                        return false;
                    }
                }
            }

            // QC#29248 add
            for (int i = 0; i < inPrmPMsg.svcPrcList.getValidCount(); i++) {
                NSZC115001_svcPrcListPMsg svcPrcPMsg = inPrmPMsg.svcPrcList.no(i);
                if (!ZYPCommonFunc.hasValue(svcPrcPMsg.shellLineNum)) {
                    continue;
                }
                if (svcConfigRefPMsg.shellLineNum.getValue().compareTo(svcPrcPMsg.shellLineNum.getValue()) == 0) {
                    prcMtrPkgPk = svcPrcPMsg.prcMtrPkgPk.getValue();
                    break;
                }
            }
        }
        // add end 2017/06/14 CSA Defect#18821

        if (isFleet) {
            for (int i = 0; i < inPrmPMsg.svcUsgPrcList.getValidCount(); i++) {
                NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg = inPrmPMsg.svcUsgPrcList.no(i);
                // QC#29248 mod
                if (NWXC150001DsCheck.hasBillableCounter(glblCmpyCd, mdlId, prcMtrPkgPk, inPrmPMsg.slsDt.getValue()) //
                        && ZYPCommonFunc.hasValue(svcConfigRefPMsg.shellLineNum) //
                        && ZYPCommonFunc.hasValue(svcUsgPrcPMsg.shellLineNum) //
                        && svcConfigRefPMsg.shellLineNum.getValue().compareTo(svcUsgPrcPMsg.shellLineNum.getValue()) == 0) {
                    DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
                    ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, mdlId);
                    dsMdlTMsg = (DS_MDLTMsg) S21FastTBLAccessor.findByKey(dsMdlTMsg);
                    if (dsMdlTMsg != null && ZYPConstant.FLG_ON_Y.equals(dsMdlTMsg.mtrReqMdlFlg.getValue())) {
                        return true;
                    }
                }
            }
        } else {
            for (int i = 0; i < inPrmPMsg.svcUsgPrcList.getValidCount(); i++) {
                NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg = inPrmPMsg.svcUsgPrcList.no(i);
                if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.shellLineNum) || !ZYPCommonFunc.hasValue(svcConfigRefPMsg.dsOrdPosnNum)) {
                    continue;
                }
                if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.shellLineNum) || !ZYPCommonFunc.hasValue(svcUsgPrcPMsg.dsOrdPosnNum)) {
                    continue;
                }
                if (svcConfigRefPMsg.shellLineNum.getValue().compareTo(svcUsgPrcPMsg.shellLineNum.getValue()) == 0 && svcConfigRefPMsg.dsOrdPosnNum.getValue().equals(svcUsgPrcPMsg.dsOrdPosnNum.getValue())) {
                    return true;
                }
            }
        }

        if (!RQST_TP_MOD.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
            return false;
        }

        if (inPrmPMsg.svcUsgPrcList.getValidCount() > 0) {
            return false;
        }

        // from DB
        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        dsContrDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        dsContrDtlTMsg.dsContrDtlPk.setValue(svcConfigRefPMsg.dsContrDtlPk.getValue());
        dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(dsContrDtlTMsg);
        if (dsContrDtlTMsg != null) {
            if (DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                return true;
            }
        }

        return false;
    }

    private String getBaseDplyPerEndDay(NSZC115001PMsg inPrmPMsg) {
        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
        if (dsContrIntfcDefRuleTMsg != null) {
            return dsContrIntfcDefRuleTMsg.contrCloDay.getValue();
        } else {
            return null;
        }
    }

    private String getMtrDplyPerEndDay(NSZC115001PMsg inPrmPMsg, boolean usgExistFlg) {
        if (!usgExistFlg) {
            return null;
        }

        return getBaseDplyPerEndDay(inPrmPMsg);
    }

    private String getCorpAdvPrcFlg(String slsDt, BigDecimal prcMtrPkgPk) {
        if (!ZYPCommonFunc.hasValue(prcMtrPkgPk)) {
            return ZYPConstant.FLG_OFF_N;
        }

        PRC_MTR_PKGTMsg prcMtrPkgTMsg = new PRC_MTR_PKGTMsg();
        prcMtrPkgTMsg.setSQLID("001");
        prcMtrPkgTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prcMtrPkgTMsg.setConditionValue("prcMtrPkgPk01", prcMtrPkgPk);
        prcMtrPkgTMsg.setConditionValue("effFromDt01", slsDt);
        prcMtrPkgTMsg.setConditionValue("effThruDt01", slsDt);
        PRC_MTR_PKGTMsgArray prcMtrPkgTMsgAry = (PRC_MTR_PKGTMsgArray) searchTblByCond(prcMtrPkgTMsg, prcMtrPkgPk);
        if (prcMtrPkgTMsgAry.length() > 0) {
            return getFlgValue(prcMtrPkgTMsgAry.no(0).corpAdvPrcFlg.getValue());
        } else{
            return ZYPConstant.FLG_OFF_N;
        }
    }

    private String getSellToCustCd(NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg) {
        if (svcPrcPMsg != null && ZYPCommonFunc.hasValue(svcPrcPMsg.sellToCustCd)) {
            return svcPrcPMsg.sellToCustCd.getValue();
        } else {
            return svcDtlPMsg.sellToCustCd.getValue();
        }
    }

    // add start 2017/06/22 CSA Defect#19340
    private String getSellToCustCdForMod(NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcPrcListPMsg svcPrcPMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        if (svcPrcPMsg == null) {
            return dsContrDtlTMsg.sellToCustCd.getValue();
        } else {
            if (ZYPCommonFunc.hasValue(svcPrcPMsg.sellToCustCd)) {
                return svcPrcPMsg.sellToCustCd.getValue();
            } else {
                return svcDtlPMsg.sellToCustCd.getValue();
            }
        }
    }
    // add end 2017/06/22 CSA Defect#19340

    private BigDecimal getPrntDsContrDtlPk(BigDecimal dsContrPk, BigDecimal svcConfigMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        param.put("dsContrDtlTpCd_Accessory", DS_CONTR_DTL_TP.ACCESSORIES);
        // START 2019/02/05 [QC#30244, ADD]
        param.put("dsContrCratTpSupply", DS_CONTR_CRAT_TP.SUPPLY);
        param.put("dsContrCratTpOther", DS_CONTR_CRAT_TP.OTHER);
        // END 2019/02/05 [QC#30244, ADD]
        // START 2023/11/08 R.Jin [QC#62108, MOD]
        // START 2023/07/05 T.Kojima [QC#61472, ADD]
//        param.put("cancelled", DS_CONTR_DTL_STS.CANCELLED);
//        param.put("terminated", DS_CONTR_DTL_STS.TERMINATED);
//        param.put("expired", DS_CONTR_DTL_STS.EXPIRED);
        // END 2023/07/05 T.Kojima [QC#61472, ADD]
        param.put("dsContrDtlStsCdActive", DS_CONTR_DTL_STS.ACTIVE);
        // END 2023/11/08 R.Jin [QC#62108, MOD]
        return (BigDecimal) this.ssmBatchClient.queryObject("getPrntDsContrDtlPk", param);
    }

    // START 2019/01/18 [QC#29782, ADD]
    private String getPrntSvcPgmMdseCd(BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        return (String) this.ssmBatchClient.queryObject("getPrntSvcPgmMdseCd", param);
    }
    // END 2019/01/18 [QC#29782, ADD]

    private String fleetToNull(String val, boolean isFleet) {
        if (isFleet) {
            return null;
        } else {
            return val;
        }
    }

    private BigDecimal fleetToNull(BigDecimal val, boolean isFleet) {
        if (isFleet) {
            return null;
        } else {
            return val;
        }
    }

    private BigDecimal fleetToZero(BigDecimal val, boolean isFleet) {
        if (isFleet) {
            return BigDecimal.ZERO;
        } else {
            return val;
        }
    }

    private String aggregateToNull(String val, boolean isAggregate) {
        return fleetToNull(val, isAggregate);
    }

    private BigDecimal aggregateToNull(BigDecimal val, boolean isAggregate) {
        return fleetToNull(val, isAggregate);
    }

    private void setDsContrDtlGrpNumToMap(String cpoOrdNum, BigDecimal shellLineNum, BigDecimal mdlId, BigDecimal dsContrDtlGrpNum) {
        String strShellLineNum = ZYPCommonFunc.leftPad(String.valueOf(shellLineNum), LENGTH_OF_SHELL_LINE_NUM, STR_ZERO);
        String strMdlId = ZYPCommonFunc.leftPad(String.valueOf(mdlId), LENGTH_OF_MODEL_ID, STR_ZERO);
        String key = createCacheKey(cpoOrdNum, strShellLineNum, strMdlId);
        this.dsContrDtlGrpNumMap.put(key, dsContrDtlGrpNum);
    }

    private BigDecimal getDsContrDtlGrpNumByMap(String cpoOrdNum, BigDecimal shellLineNum, BigDecimal mdlId, boolean isFleet) {
        if (isFleet) {
            mdlId = null;
        }
        String strShellLineNum = ZYPCommonFunc.leftPad(String.valueOf(shellLineNum), LENGTH_OF_SHELL_LINE_NUM, STR_ZERO);
        String strMdlId = ZYPCommonFunc.leftPad(String.valueOf(mdlId), LENGTH_OF_MODEL_ID, STR_ZERO);
        String key = createCacheKey(cpoOrdNum, strShellLineNum, strMdlId);
        return this.dsContrDtlGrpNumMap.get(key);
    }

    private BigDecimal getDsContrDtlPkForShellByMap(String cpoOrdNum, BigDecimal shellLineNum, BigDecimal mdlId) {
        return getDsContrDtlGrpNumByMap(cpoOrdNum, shellLineNum, mdlId, false);
    }

    private void setFltAggDsContrDtlPkToMap(String cpoOrdNum, BigDecimal shellLineNum, BigDecimal dsContrDtlPk) {
        String strShellLineNum = ZYPCommonFunc.leftPad(String.valueOf(shellLineNum), LENGTH_OF_SHELL_LINE_NUM, STR_ZERO);
        String key = createCacheKey(cpoOrdNum, strShellLineNum);
        this.fltAggDsContrDtlPkMap.put(key, dsContrDtlPk);
    }

    private BigDecimal getFltAggDsContrDtlPkByMap(String cpoOrdNum, BigDecimal shellLineNum) {
        String strShellLineNum = ZYPCommonFunc.leftPad(String.valueOf(shellLineNum), LENGTH_OF_SHELL_LINE_NUM, STR_ZERO);
        String key = createCacheKey(cpoOrdNum, strShellLineNum);
        return this.fltAggDsContrDtlPkMap.get(key);
    }

    private BigDecimal getFltAggDsContrDtlPkByDB(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlTpCd_Fleet", DS_CONTR_DTL_TP.FLEET);
        param.put("dsContrDtlTpCd_Aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        return (BigDecimal) this.ssmBatchClient.queryObject("getFltAggDsContrDtlPkByDB", param);
    }

    private void addDsContrDtlPkListForUsgRemoval(List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList) {
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgList) {
            this.dsContrDtlPkListForUsgRemoval.add(dsContrDtlTMsg.dsContrDtlPk.getValue());
        }
    }

    private void addDsContrDtlPkListForAsryAddlRemoval(List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList) {
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgList) {
            this.dsContrDtlPkListForAsryAddlRemoval.add(dsContrDtlTMsg.dsContrDtlPk.getValue());
        }
    }

    private DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrList(BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg prmTMsg = new DS_CONTR_BLLG_MTRTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BLLG_MTRTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(prmTMsg);
    }

    private CONTR_XS_COPYTMsgArray getContrXsCopy(BigDecimal dsContrBllgMtrPk) {
        CONTR_XS_COPYTMsg prmTMsg = new CONTR_XS_COPYTMsg();
        prmTMsg.setSQLID("002");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        return (CONTR_XS_COPYTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(prmTMsg);
    }

    private CONTR_PHYS_BLLG_MTR_RELNTMsgArray getContrPhysBllgMtrRelnByDsContrDtlPk(BigDecimal dsContrDtlPk) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg prmTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(prmTMsg);
    }

    private String getXsChrgTpCd(String xsChrgTpCd) {
        if (ZYPCommonFunc.hasValue(xsChrgTpCd)) {
            if (this.xsChrgTpConvMap != null && this.xsChrgTpConvMap.keySet().contains(xsChrgTpCd)) {
                XS_CHRG_TPTMsg tMsg = (XS_CHRG_TPTMsg) this.xsChrgTpConvMap.get(xsChrgTpCd);
                return tMsg.xsChrgTpCd.getValue();
            } else {
                return null;
            }
        } else {
            return XS_CHRG_TP.RANGE;
        }
    }

    private BigDecimal getXsMtrCopyQty(BigDecimal copyInclPrcQty, BigDecimal minCopyVolCnt) {
        if (ZYPCommonFunc.hasValue(minCopyVolCnt)) {
            return minCopyVolCnt.subtract(BigDecimal.ONE);
        } else {
            return copyInclPrcQty;
        }
    }

    private String getXsMtrFirstFlg(String prcSvcTierTpCd) {
        if (!ZYPCommonFunc.hasValue(prcSvcTierTpCd) || PRC_SVC_TIER_TP.BASE.equals(prcSvcTierTpCd)) {
            return ZYPConstant.FLG_ON_Y;
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }

    private BigDecimal getDsContrDtlPkByConfig(NSZC115001_svcConfigRefListPMsgArray svcConfigRefList, BigDecimal shellLineNum, String dsOrdPosnNum) {
        if (!ZYPCommonFunc.hasValue(shellLineNum) || !ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
            return null;
        }

        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;
        for (int i = 0; i < svcConfigRefList.getValidCount(); i++) {
            svcConfigRefPMsg = svcConfigRefList.no(i);
            if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.shellLineNum)) {
                continue;
            }

            if (shellLineNum.compareTo(svcConfigRefPMsg.shellLineNum.getValue()) == 0 && dsOrdPosnNum.equals(svcConfigRefPMsg.dsOrdPosnNum.getValue())) {
                return svcConfigRefPMsg.dsContrDtlPk.getValue();
            }
        }
        return null;
    }

    private BigDecimal getDsContrDtlPkByConfigOrAddlBase(NSZC115001_svcConfigRefListPMsgArray svcConfigRefList, NSZC115001_svcAddlBasePrcListPMsgArray svcAddlBasePrcList, BigDecimal shellLineNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        if (!ZYPCommonFunc.hasValue(shellLineNum) || !ZYPCommonFunc.hasValue(cpoDtlLineNum) || !ZYPCommonFunc.hasValue(cpoDtlLineSubNum)) {
            return null;
        }

        // Machine
        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;
        for (int i = 0; i < svcConfigRefList.getValidCount(); i++) {
            svcConfigRefPMsg = svcConfigRefList.no(i);
            if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.shellLineNum)) {
                continue;
            }

            if (shellLineNum.compareTo(svcConfigRefPMsg.shellLineNum.getValue()) == 0 && cpoDtlLineNum.equals(svcConfigRefPMsg.cpoDtlLineNum.getValue()) && cpoDtlLineSubNum.equals(svcConfigRefPMsg.cpoDtlLineSubNum.getValue())) {
                return svcConfigRefPMsg.dsContrDtlPk.getValue();
            }
        }

        // Accessory
        NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrcPMsg = null;
        for (int i = 0; i < svcAddlBasePrcList.getValidCount(); i++) {
            svcAddlBasePrcPMsg = svcAddlBasePrcList.no(i);
            if (!ZYPCommonFunc.hasValue(svcAddlBasePrcPMsg.shellLineNum)) {
                continue;
            }

            if (!ADDL_CHRG_CATG.ACCESSORY.equals(svcAddlBasePrcPMsg.addlChrgCatgCd.getValue())) {
                continue;
            }

            if (shellLineNum.compareTo(svcAddlBasePrcPMsg.shellLineNum.getValue()) == 0 && cpoDtlLineNum.equals(svcAddlBasePrcPMsg.cpoDtlLineNum.getValue()) && cpoDtlLineSubNum.equals(svcAddlBasePrcPMsg.cpoDtlLineSubNum.getValue())) {
                return svcAddlBasePrcPMsg.dsContrDtlPk.getValue();
            }
        }

        return null;
    }

    // mod start 2017/08/29 CSA Defect#20831
//    private String getBllgMtrLvlNum(String mtrLbCd) {
//        MTR_LBTMsg mtrLbTMsg = new MTR_LBTMsg();
//        ZYPEZDItemValueSetter.setValue(mtrLbTMsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(mtrLbTMsg.mtrLbCd, mtrLbCd);
//        mtrLbTMsg = (MTR_LBTMsg) S21CodeTableAccessor.findByKey(mtrLbTMsg);
//        if (mtrLbTMsg != null) {
//            return mtrLbTMsg.bllgMtrLvlNum.getValue();
//        } else {
//            return null;
//        }
    private String getBllgMtrLvlNum(NSZC115001_svcPrcListPMsg svcPrcPMsg, String mdlMtrLbCd, String bllgMtrLbCd, BigDecimal mdlId, String slsDt) {
        List<BigDecimal> mdlIdList = null;
        if (ZYPCommonFunc.hasValue(mdlId)) {
            mdlIdList = new ArrayList<BigDecimal>();
            mdlIdList.add(mdlId);
        } else {
            mdlIdList = getMdlIdFromDsCpoConfigForPrcOnly(svcPrcPMsg);
        }
        BigDecimal[] mdlIdAry = mdlIdList.toArray(new BigDecimal[0]);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("mdlMtrLbCd", mdlMtrLbCd);
        param.put("bllgMtrLbCd", bllgMtrLbCd);
        param.put("mdlIdList", mdlIdAry);
        param.put("slsDt", slsDt);
        return (String) this.ssmBatchClient.queryObject("getBllgMtrLvlNum", param);
    }
    // mod end 2017/08/29 CSA Defect#20831

    private void copyDsContrBllgMtrForFlt(String dsContrCatgCd, List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgNewList, Map<String, BigDecimal> dsContrBllgMtrPkMap, String cpoNum, BigDecimal shellLineNum, DS_CONTR_BLLG_MTRTMsg fromTMsg, boolean isAddContr) {
        if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            return;
        }

        BigDecimal dsContrDtlPk = getFltAggDsContrDtlPkByMap(cpoNum, shellLineNum);

        if (isAddContr) {
            if (isExistDsContrBllgMtr(dsContrDtlPk, fromTMsg.bllgMtrLbCd.getValue())) {
                return;
            }
        }

        BigDecimal dsContrBllgMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_MTR_SQ);

        DS_CONTR_BLLG_MTRTMsg toTMsg = new DS_CONTR_BLLG_MTRTMsg();
        EZDMsg.copy(fromTMsg, null, toTMsg, null);
        ZYPEZDItemValueSetter.setValue(toTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        ZYPEZDItemValueSetter.setValue(toTMsg.dsContrDtlPk, dsContrDtlPk);
        dsContrBllgMtrTMsgNewList.add(toTMsg);

        String strDsContrDtlPk = ZYPCommonFunc.leftPad(String.valueOf(dsContrDtlPk), LENGTH_OF_PK, STR_ZERO);
        String key = createCacheKey(strDsContrDtlPk, toTMsg.bllgMtrLbCd.getValue());
        dsContrBllgMtrPkMap.put(key, dsContrBllgMtrPk);
    }

    private boolean isExistDsContrBllgMtr(BigDecimal dsContrDtlPk, String bllgMtrLbCd) {
        DS_CONTR_BLLG_MTRTMsg param = new DS_CONTR_BLLG_MTRTMsg();
        param.setSQLID("002");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        param.setConditionValue("bllgMtrLbCd01", bllgMtrLbCd);
        DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByCondition(param);
        if (dsContrBllgMtrTMsgArray.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    private void copyDsContrBllgMtrForFltMachine(String dsContrCatgCd, List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgNewList, Map<String, BigDecimal> dsContrBllgMtrPkMap, NSZC115001PMsg inPrmPMsg, BigDecimal prcMtrPkgPk, String bllgMtrLbCd, DS_CONTR_BLLG_MTRTMsg fromTMsg) {
        if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            return;
        }

        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;
        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();

        for (int i = 0; i < inPrmPMsg.svcConfigRefList.getValidCount(); i++) {
            svcConfigRefPMsg = inPrmPMsg.svcConfigRefList.no(i);

            cpoDtlTMsg.clear();
            cpoDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            cpoDtlTMsg.cpoOrdNum.setValue(svcConfigRefPMsg.cpoOrdNum.getValue());
            cpoDtlTMsg.cpoDtlLineNum.setValue(svcConfigRefPMsg.cpoDtlLineNum.getValue());
            cpoDtlTMsg.cpoDtlLineSubNum.setValue(svcConfigRefPMsg.cpoDtlLineSubNum.getValue());
            BigDecimal mdlId = getMdlIdByCpoDtl(cpoDtlTMsg);

            if (!hasBllgMtr(prcMtrPkgPk, mdlId, inPrmPMsg.slsDt.getValue(), bllgMtrLbCd)) {
                continue;
            }

            BigDecimal dsContrBllgMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_MTR_SQ);
            BigDecimal dsContrDtlPk = svcConfigRefPMsg.dsContrDtlPk.getValue();

            DS_CONTR_BLLG_MTRTMsg toTMsg = new DS_CONTR_BLLG_MTRTMsg();
            EZDMsg.copy(fromTMsg, null, toTMsg, null);
            ZYPEZDItemValueSetter.setValue(toTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
            ZYPEZDItemValueSetter.setValue(toTMsg.dsContrDtlPk, dsContrDtlPk);
            toTMsg.bllgMtrBillToCustCd.clear();
            toTMsg.bllgMtrBllgCycleCd.clear();
            toTMsg.ctacPsnPk.clear();
            toTMsg.sellToCustCd.clear();
            dsContrBllgMtrTMsgNewList.add(toTMsg);

            String strDsContrDtlPk = ZYPCommonFunc.leftPad(String.valueOf(dsContrDtlPk), LENGTH_OF_PK, STR_ZERO);
            String key = createCacheKey(strDsContrDtlPk, toTMsg.bllgMtrLbCd.getValue());
            dsContrBllgMtrPkMap.put(key, dsContrBllgMtrPk);
        }
    }

    private boolean hasBllgMtr(BigDecimal prcMtrPkgPk, BigDecimal mdlId, String slsDt, String bllgMtrLbCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("prcMtrPkgPk", prcMtrPkgPk);
        param.put("mdlId", mdlId);
        param.put("slsDt", slsDt);
        List<String> bllgMtrList = this.ssmBatchClient.queryObjectList("getMtrLbByBllgMtr", param);
        if (bllgMtrList != null && bllgMtrList.contains(bllgMtrLbCd)) {
            return true;
        }
        return false;
    }

    private void copyContrPhysBllgMtrRelnForFlt(String dsContrCatgCd, List<CONTR_PHYS_BLLG_MTR_RELNTMsg> contrPhysBllgMtrRelnTMsgNewList, Map<String, BigDecimal> dsContrBllgMtrPkMap, String cpoNum, BigDecimal shellLineNum, CONTR_PHYS_BLLG_MTR_RELNTMsg fromTMsg) {
        if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            return;
        }

        BigDecimal dsContrDtlPk = getFltAggDsContrDtlPkByMap(cpoNum, shellLineNum);
        String strDsContrDtlPk = ZYPCommonFunc.leftPad(String.valueOf(dsContrDtlPk), LENGTH_OF_PK, STR_ZERO);
        String key = createCacheKey(strDsContrDtlPk, fromTMsg.bllgMtrLbCd.getValue());
        BigDecimal dsContrBllgMtrPk = dsContrBllgMtrPkMap.get(key);
        if (!ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
            return;
        }

        BigDecimal contrPhysBllgMtrRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_PHYS_BLLG_MTR_RELN_SQ);

        CONTR_PHYS_BLLG_MTR_RELNTMsg toTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        EZDMsg.copy(fromTMsg, null, toTMsg, null);
        ZYPEZDItemValueSetter.setValue(toTMsg.contrPhysBllgMtrRelnPk, contrPhysBllgMtrRelnPk);
        ZYPEZDItemValueSetter.setValue(toTMsg.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(toTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        contrPhysBllgMtrRelnTMsgNewList.add(toTMsg);
    }

    private void copyContrPhysBllgMtrRelnForFltMachine(String dsContrCatgCd, List<CONTR_PHYS_BLLG_MTR_RELNTMsg> contrPhysBllgMtrRelnTMsgNewList, Map<String, BigDecimal> dsContrBllgMtrPkMap, NSZC115001PMsg inPrmPMsg, BigDecimal prcMtrPkgPk, String bllgMtrLbCd, CONTR_PHYS_BLLG_MTR_RELNTMsg fromTMsg) {
        if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            return;
        }

        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;
        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();

        for (int i = 0; i < inPrmPMsg.svcConfigRefList.getValidCount(); i++) {
            svcConfigRefPMsg = inPrmPMsg.svcConfigRefList.no(i);

            cpoDtlTMsg.clear();
            cpoDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            cpoDtlTMsg.cpoOrdNum.setValue(svcConfigRefPMsg.cpoOrdNum.getValue());
            cpoDtlTMsg.cpoDtlLineNum.setValue(svcConfigRefPMsg.cpoDtlLineNum.getValue());
            cpoDtlTMsg.cpoDtlLineSubNum.setValue(svcConfigRefPMsg.cpoDtlLineSubNum.getValue());
            BigDecimal mdlId = getMdlIdByCpoDtl(cpoDtlTMsg);

            if (!hasBllgMtr(prcMtrPkgPk, mdlId, inPrmPMsg.slsDt.getValue(), bllgMtrLbCd)) {
                continue;
            }

            BigDecimal contrPhysBllgMtrRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_PHYS_BLLG_MTR_RELN_SQ);
            BigDecimal dsContrDtlPk = svcConfigRefPMsg.dsContrDtlPk.getValue();
            String strDsContrDtlPk = ZYPCommonFunc.leftPad(String.valueOf(dsContrDtlPk), LENGTH_OF_PK, STR_ZERO);
            String key = createCacheKey(strDsContrDtlPk, fromTMsg.bllgMtrLbCd.getValue());
            BigDecimal dsContrBllgMtrPk = dsContrBllgMtrPkMap.get(key);

            CONTR_PHYS_BLLG_MTR_RELNTMsg toTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
            EZDMsg.copy(fromTMsg, null, toTMsg, null);
            ZYPEZDItemValueSetter.setValue(toTMsg.contrPhysBllgMtrRelnPk, contrPhysBllgMtrRelnPk);
            ZYPEZDItemValueSetter.setValue(toTMsg.dsContrDtlPk, dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(toTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
            contrPhysBllgMtrRelnTMsgNewList.add(toTMsg);
        }
    }

    private void copyContrXsCopycontrXsCopyForFlt(String dsContrCatgCd, List<CONTR_XS_COPYTMsg> contrXsCopyTMsgNewList, Map<String, BigDecimal> dsContrBllgMtrPkMap, String cpoNum, BigDecimal shellLineNum, String bllgMtrLbCd, CONTR_XS_COPYTMsg fromTMsg) {
        if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            return ;
        }

        BigDecimal dsContrDtlPk = getFltAggDsContrDtlPkByMap(cpoNum, shellLineNum);
        String strDsContrDtlPk = ZYPCommonFunc.leftPad(String.valueOf(dsContrDtlPk), LENGTH_OF_PK, STR_ZERO);
        String key = createCacheKey(strDsContrDtlPk, bllgMtrLbCd);
        BigDecimal dsContrBllgMtrPk = dsContrBllgMtrPkMap.get(key);
        if (!ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
            return;
        }

        BigDecimal contrXsCopyPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_XS_COPY_SQ);

        CONTR_XS_COPYTMsg toTMsg = new CONTR_XS_COPYTMsg();
        EZDMsg.copy(fromTMsg, null, toTMsg, null);
        ZYPEZDItemValueSetter.setValue(toTMsg.contrXsCopyPk, contrXsCopyPk);
        ZYPEZDItemValueSetter.setValue(toTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        contrXsCopyTMsgNewList.add(toTMsg);
    }

    private String getBllgMtrLbCd(String bllgMtrLbCd, String dsContrCatgCd) {
        //START 2017/08/07 K.Kasai [QC#18193,18195,MOD]
//        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
//            return bllgMtrLbCd;
//        }
//
//        MTR_LBTMsg mtrLbTMsg = new MTR_LBTMsg();
//        ZYPEZDItemValueSetter.setValue(mtrLbTMsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(mtrLbTMsg.mtrLbCd, bllgMtrLbCd);
//        mtrLbTMsg = (MTR_LBTMsg) S21CodeTableAccessor.findByKey(mtrLbTMsg);
//        if (mtrLbTMsg != null) {
//            if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
//                return mtrLbTMsg.fleetMtrLbCd.getValue();
//            } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
//                return mtrLbTMsg.aggrMtrLbCd.getValue();
//            }
//        }
//
//        return null;
        return bllgMtrLbCd;
        //END 2017/08/07 K.Kasai [QC#18193,18195,MOD]
    }

    private List<Map<String, Object>> getDsContrBllgMtrListForAggLine(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.BASE_AND_USAGE);
        return this.ssmBatchClient.queryObjectList("getDsContrBllgMtrListForAggLine", param);
    }

    private List<Map<String, Object>> getContrPhysBllgMtrRelnListForAggLine(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.BASE_AND_USAGE);
        return this.ssmBatchClient.queryObjectList("getContrPhysBllgMtrRelnListForAggLine", param);
    }

    private List<Map<String, Object>> getContrXsCopyListForAggLine(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.BASE_AND_USAGE);
        return this.ssmBatchClient.queryObjectList("getContrXsCopyListForAggLine", param);
    }

    private String getAddContrFlg(BigDecimal dsContrPk) {
        if (ZYPCommonFunc.hasValue(dsContrPk)) {
            return ZYPConstant.FLG_ON_Y;
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }

    private String getPrintDtlFlgForRentalAddlChrg(NSZC115001PMsg inPrmPMsg) {
        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
        if (dsContrIntfcDefRuleTMsg != null) {
            return getFlgValue(dsContrIntfcDefRuleTMsg.printDtlFlg.getValue());
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }

    private String getPrintDtlFlgForNormalAddlChrg(NSZC115001PMsg inPrmPMsg, String printDtlFlg) {
        if (ZYPCommonFunc.hasValue(printDtlFlg)) {
            return printDtlFlg;
        }

        return getPrintDtlFlgForRentalAddlChrg(inPrmPMsg);
    }

    private String getAddlChrgTpCdForRental(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
        dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(dsContrDtlTMsg);
        if (dsContrDtlTMsg == null) {
            return null;
        }

        if (DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
            return getRentalAddlChrgTpCdForAsry();
        } else {
            return getRentalAddlChrgTpCdForMachine();
        }
    }

    private String getRentalAddlChrgTpCdForAsry() {
        if (this.rentalAddlChrgTpCdAcc == null) {
            this.rentalAddlChrgTpCdAcc = ZYPCodeDataUtil.getVarCharConstValue(SPCL_RENTAL_ADDL_CHRG_TP_ACC, this.glblCmpyCd);
        }
        return this.rentalAddlChrgTpCdAcc;
    }

    private String getRentalAddlChrgTpCdForMachine() {
        if (this.rentalAddlChrgTpCdMac == null) {
            this.rentalAddlChrgTpCdMac = ZYPCodeDataUtil.getVarCharConstValue(SPCL_RENTAL_ADDL_CHRG_TP_MACH, this.glblCmpyCd);
        }
        return this.rentalAddlChrgTpCdMac;
    }
    // START 2018/05/24 [QC#26062, ADD]
    private String getDoNotRnwUpliftSvcPgmTpCd() {
        if (this.doNotRnwUpliftSvcPgmTp == null) {
            this.doNotRnwUpliftSvcPgmTp = new ArrayList<String>();
            String doNotRnwUpliftSvcPgmTpVarChar = ZYPCodeDataUtil.getVarCharConstValue(SPCL_DO_NOT_RNW_UPLIFT_SVC_PGM_TP, this.glblCmpyCd);
            if (ZYPCommonFunc.hasValue(doNotRnwUpliftSvcPgmTpVarChar)) {
                String[] constValList = doNotRnwUpliftSvcPgmTpVarChar.split(SVC_PGM_TP_DLMT);
                this.doNotRnwUpliftSvcPgmTp = Arrays.asList(constValList);
            }
        }
        return this.rentalAddlChrgTpCdMac;
    }
    // END   2018/05/24 [QC#26062, ADD]

    private DS_CR_CARDTMsg getDsCrCardTMsg(NSZC115001PMsg inPrmPMsg) {
        CPOTMsg cpo = getCPOTMsg(inPrmPMsg);
        if (cpo == null || !ZYPCommonFunc.hasValue(cpo.dsCrCardPk)) {
            return null;
        }

        DS_CR_CARDTMsg dsCrCardTMsg = new DS_CR_CARDTMsg();
        ZYPEZDItemValueSetter.setValue(dsCrCardTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCrCardTMsg.dsCrCardPk, cpo.dsCrCardPk);
        return (DS_CR_CARDTMsg) S21FastTBLAccessor.findByKey(dsCrCardTMsg);
    }

    private String getLeaseCmpyFlg(String sellToCustCd) {
        if (!ZYPCommonFunc.hasValue(sellToCustCd)) {
            return ZYPConstant.FLG_OFF_N;
        }

        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
        sellToCustTMsg.setSQLID("990");
        sellToCustTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        sellToCustTMsg.setConditionValue("sellToCustCd01", sellToCustCd);
        SELL_TO_CUSTTMsgArray sellToCustTMsgAry = (SELL_TO_CUSTTMsgArray) searchTblByCond(sellToCustTMsg, sellToCustCd);
        if (sellToCustTMsgAry == null || sellToCustTMsgAry.length() == 0) {
            return ZYPConstant.FLG_OFF_N;
        }

        DS_ACCT_CLSTMsg dsAcctClsTMsg = new DS_ACCT_CLSTMsg();
        ZYPEZDItemValueSetter.setValue(dsAcctClsTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAcctClsTMsg.dsAcctClsCd, sellToCustTMsgAry.no(0).dsAcctClsCd);
        dsAcctClsTMsg = (DS_ACCT_CLSTMsg) S21CodeTableAccessor.findByKey(dsAcctClsTMsg);
        if (dsAcctClsTMsg != null) {
            return getFlgValue(dsAcctClsTMsg.leaseCmpyFlg.getValue());
        }

        return ZYPConstant.FLG_OFF_N;
    }

    private boolean isExistNextContr(String cpoOrdNum, BigDecimal shellLineNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("shellLineNum", shellLineNum);
        BigDecimal cnt = (BigDecimal) this.ssmBatchClient.queryObject("cntNextContr", param);
        if (BigDecimal.ZERO.compareTo(cnt) < 0) {
            return true;
        }
        return false;
    }

    private DS_CONTR_RNW_ESCLTMsgArray getDsContrRnwEsclTMsgArray(BigDecimal dsContrPk) {
        DS_CONTR_RNW_ESCLTMsg param = new DS_CONTR_RNW_ESCLTMsg();
        param.setSQLID("201");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrPk01", dsContrPk);
        return (DS_CONTR_RNW_ESCLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(param);
    }

    private DS_CONTR_RNW_ESCLTMsg getDsContrRnwEsclTMsg(BigDecimal dsContrPk) {
        DS_CONTR_RNW_ESCLTMsg param = new DS_CONTR_RNW_ESCLTMsg();
        param.setSQLID("004");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrPk01", dsContrPk);
        DS_CONTR_RNW_ESCLTMsgArray dsContrRnwEsclTMsgArray = (DS_CONTR_RNW_ESCLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(param);
        if (dsContrRnwEsclTMsgArray.getValidCount() == 0) {
            return null;
        } else {
            return dsContrRnwEsclTMsgArray.no(0);
        }
    }

    // add start 2020/03/24 QC#54318
    private DS_CONTR_RNW_ESCLTMsgArray getDsContrRnwEsclTMsgArrayDtl(BigDecimal dsContrDtlPk) {
        DS_CONTR_RNW_ESCLTMsg param = new DS_CONTR_RNW_ESCLTMsg();
        param.setSQLID("003");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_RNW_ESCLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(param);
    }

    private DS_CONTR_RNW_ESCLTMsgArray getDsContrRnwEsclTMsgArrayLvl2(BigDecimal dsContrPk) {
        DS_CONTR_RNW_ESCLTMsg param = new DS_CONTR_RNW_ESCLTMsg();
        param.setSQLID("202");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrPk01", dsContrPk);
        param.setConditionValue("dsContrMachLvlNum01", "2");
        return (DS_CONTR_RNW_ESCLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(param);
    }

    private DS_CONTR_RNW_ESCLTMsg getDsContrRnwEsclTMsgLvl2(BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        DS_CONTR_RNW_ESCLTMsg param = new DS_CONTR_RNW_ESCLTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrPk01", dsContrPk);
        param.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        param.setConditionValue("dsContrMachLvlNum01", "2");
        DS_CONTR_RNW_ESCLTMsgArray dsContrRnwEsclTMsgArray = (DS_CONTR_RNW_ESCLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(param);
        if (dsContrRnwEsclTMsgArray.getValidCount() == 0) {
            return null;
        } else {
            return dsContrRnwEsclTMsgArray.no(0);
        }
    }
    // add end 2020/03/24 QC#54318

    private boolean isExistTrnsfAmt(String cpoOrdNum) {
        if (this.isExistTrnsfAmtFlg != null) {
            return ZYPConstant.FLG_ON_Y.equals(this.isExistTrnsfAmtFlg);
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        BigDecimal cnt = (BigDecimal) this.ssmBatchClient.queryObject("cntTrnsfAmt", param);
        if (BigDecimal.ZERO.compareTo(cnt) < 0) {
            this.isExistTrnsfAmtFlg = ZYPConstant.FLG_ON_Y;
            return true;
        }
        this.isExistTrnsfAmtFlg = ZYPConstant.FLG_OFF_N;
        return false;
    }

    //START 2018/05/24 [QC#26062, Mod]
    private String getContrAutoRnwTpCd(String cpoOrdNum, String prcSvcPlnTpCd) {
        if (!PRC_SVC_PLN_TP.FIXED.equals(prcSvcPlnTpCd)) {
            return CONTR_AUTO_RNW_TP.DO_NOT_RENEW;
        }
        if (isExistTrnsfAmt(cpoOrdNum)) {
            return CONTR_AUTO_RNW_TP.MANUAL_RENEW_BASE_OVERAGE;
        }
        String contrAutoRnwTpCd = CONTR_AUTO_RNW_TP.DO_NOT_RENEW;
        if (this.dsContrIntfcDefRuleTMsg != null && ZYPCommonFunc.hasValue(this.dsContrIntfcDefRuleTMsg.contrAutoRnwTpCd)) {
            contrAutoRnwTpCd = this.dsContrIntfcDefRuleTMsg.contrAutoRnwTpCd.getValue();
        }
        return contrAutoRnwTpCd;
    }
    //End   2018/05/24 [QC#26062, Mod]

    private String getRnwPrcMethCd(String cpoOrdNum) {
        //START 2017/08/23 U.Kim [QC#18085, DEL]
        //if (isExistTrnsfAmt(cpoOrdNum)) {
            //return null;
        //} else {
        //END 2017/08/23 U.Kim [QC#18085, DEL]
        //START   2018/05/24 [QC#26062, DEL]
//            return RNW_PRC_METH.MARKUP_PERCENT;
        //END     2018/05/24 [QC#26062, DEL]
        //START 2017/08/23 U.Kim [QC#18085, DEL]
        //}
        //END 2017/08/23 U.Kim [QC#18085, DEL]
        //START   2018/05/24 [QC#26062, Add]
        String rnwPrcMethCd = RNW_PRC_METH.MARKUP_PERCENT;
        if (this.dsContrIntfcDefRuleTMsg != null && ZYPCommonFunc.hasValue(this.dsContrIntfcDefRuleTMsg.contrAutoRnwTpCd)) {
            rnwPrcMethCd = this.dsContrIntfcDefRuleTMsg.rnwPrcMethCd.getValue();
        }
        return rnwPrcMethCd;
        //END     2018/05/24 [QC#26062, Add]
    }

    private BigDecimal getBefEndRnwDaysAot(NSZC115001PMsg inPrmPMsg) {
        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
        if (dsContrIntfcDefRuleTMsg != null) {
            return dsContrIntfcDefRuleTMsg.befEndRnwDaysAot.getValue();
        } else {
            return null;
        }
    }

    // START 2018/08/22 K.Kojima [QC#27867,MOD]
    // private BigDecimal getRnwPrcUpRatio(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg) {
    private BigDecimal getRnwPrcUpRatio(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, boolean baseFlg) {
    // END 2018/08/22 K.Kojima [QC#27867,MOD]
        CPOTMsg cpo = getCPOTMsg(inPrmPMsg);
        if (cpo == null) {
            return null;
        }

        String dsOrdCatgCd = cpo.dsOrdCatgCd.getValue();
        // START 2019/06/17 W.Honda [QC#50842,ADD]
        String dsOrdTpCd = cpo.dsOrdTpCd.getValue();
        // END 2019/06/17 W.Honda [QC#50842,ADD]

        // START 2019/06/17 W.Honda [QC#50842,MOD]
//        if (isDsOrdCatgCd(ORD_CATG_CTX_TP_CD_EXCL_RENEWAL_MARKUP, dsOrdCatgCd)) {
        if (isDsOrdCatgCd(ORD_CATG_CTX_TP_CD_EXCL_RENEWAL_MARKUP, dsOrdCatgCd, dsOrdTpCd)) {
        // END 2019/06/17 W.Honda [QC#50842,MOD]
            return new BigDecimal(0);
        // START 2019/06/17 W.Honda [QC#50842,MOD]
//        } else if (isDsOrdCatgCd(ORD_CATG_CTX_TP_CD_RENTAL_SHELL_REQUIRED, dsOrdCatgCd)) {
        } else if (isDsOrdCatgCd(ORD_CATG_CTX_TP_CD_RENTAL_SHELL_REQUIRED, dsOrdCatgCd, dsOrdTpCd)) {
        // END 2019/06/17 W.Honda [QC#50842,MOD]
            CONTR_INTFC_RNW_MAPTMsg contrIntfcRnwMapTmsg = getRnwPrcUpRatioForRentalAndLease();
            if (contrIntfcRnwMapTmsg != null) {
                return contrIntfcRnwMapTmsg.rnwPrcUpRatio.getValue();
            } else {
                return null;
            }
            // START 2018/05/24 [QC#26062, DEL]
//        } else if (ZYPCommonFunc.hasValue(svcDtlPMsg.prcSvcPlnTpCd)) {
//            RNW_RATIO_BY_TERMTMsg rnwRatioTmsg = getRnwRatioByTerm(svcDtlPMsg.prcSvcPlnTpCd.getValue());
//            if (rnwRatioTmsg != null) {
//                return rnwRatioTmsg.rnwPrcUpRatio.getValue();
//            } else {
//                return null;
//            }
            // END   2018/05/24 [QC#26062, DEL]
        // START 2019/06/17 W.Honda [QC#50842,MOD]
//        } else if (isDsOrdCatgCd(ORD_CATG_CTX_TP_CD_LEASE_ORDER, dsOrdCatgCd)) {
        } else if (isDsOrdCatgCd(ORD_CATG_CTX_TP_CD_LEASE_ORDER, dsOrdCatgCd, dsOrdTpCd)) {
        // END 2019/06/17 W.Honda [QC#50842,MOD]
            CONTR_INTFC_RNW_MAPTMsg contrIntfcRnwMapTmsg = getRnwPrcUpRatioForRentalAndLease();
            if (contrIntfcRnwMapTmsg != null) {
                return contrIntfcRnwMapTmsg.rnwPrcUpRatio.getValue();
            } else {
                return null;
            }
        } else {
            // START 2018/08/22 K.Kojima [QC#27867,MOD]
            // CONTR_INTFC_RNW_MAPTMsg contrIntfcRnwMapTmsg = getRnwPrcUpRatioForOther();
            // if (contrIntfcRnwMapTmsg != null) {
            //     return contrIntfcRnwMapTmsg.rnwPrcUpRatio.getValue();
            // } else {
            //     return null;
            // }
            DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
            if (dsContrIntfcDefRuleTMsg != null) {
                if (baseFlg) {
                    return dsContrIntfcDefRuleTMsg.basePrcUpRatio.getValue();
                } else {
                    return dsContrIntfcDefRuleTMsg.mtrPrcUpRatio.getValue();
                }
            } else {
                return null;
            }
            // END 2018/08/22 K.Kojima [QC#27867,MOD]
        }
    }

    // START 2019/06/17 W.Honda [QC#50842,MOD]
//    private boolean isDsOrdCatgCd(String ordCatgCtxTpCd, String dsOrdCatgCd) {
    private boolean isDsOrdCatgCd(String ordCatgCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd) {
        if (ordCatgCtxTpCd == null) {
            return false;
        }

//        ORD_CATG_BIZ_CTXTMsgArray tmsgList = getOrdCatgBizCtx(ordCatgCtxTpCd);
//        for (int i = 0; i < tmsgList.getValidCount(); i++) {
//            ORD_CATG_BIZ_CTXTMsg tmsg = (ORD_CATG_BIZ_CTXTMsg) tmsgList.get(i);
//            if (tmsg.dsOrdCatgCd.getValue().equals(dsOrdCatgCd)) {
//                return true;
//            }
//        }

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("004");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ordCatgCtxTpCd01", ordCatgCtxTpCd);
        condition.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);
        condition.setConditionValue("dsOrdTpCd01", dsOrdTpCd);

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return true;
        }

        return false;
    }

//    private ORD_CATG_BIZ_CTXTMsgArray getOrdCatgBizCtx(String ordCatgCtxTpCd) {
//        ORD_CATG_BIZ_CTXTMsg param = new ORD_CATG_BIZ_CTXTMsg();
//        param.setSQLID("001");
//        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        param.setConditionValue("ordCatgCtxTpCd01", ordCatgCtxTpCd);
//        return (ORD_CATG_BIZ_CTXTMsgArray) searchTblByCond(param, ordCatgCtxTpCd);
//    }
    // END 2019/06/17 W.Honda [QC#50842,ADD]

    private CONTR_INTFC_RNW_MAPTMsg getRnwPrcUpRatioForRentalAndLease() {
        CONTR_INTFC_RNW_MAPTMsg param = new CONTR_INTFC_RNW_MAPTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("rntlRnwFlg01", ZYPConstant.FLG_ON_Y);
        param.setConditionValue("enblFlg01", ZYPConstant.FLG_ON_Y);
        CONTR_INTFC_RNW_MAPTMsgArray tmsgList = (CONTR_INTFC_RNW_MAPTMsgArray) searchTblByCond(param, "001");
        if (tmsgList.getValidCount() > 0) {
            return (CONTR_INTFC_RNW_MAPTMsg) tmsgList.get(0);
        } else {
            return null;
        }
    }

    private RNW_RATIO_BY_TERMTMsg getRnwRatioByTerm(String prcSvcPlnTpCd) {
        RNW_RATIO_BY_TERMTMsg param = new RNW_RATIO_BY_TERMTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("prcSvcPlnTpCd01", prcSvcPlnTpCd);
        param.setConditionValue("enblFlg01", ZYPConstant.FLG_ON_Y);

        RNW_RATIO_BY_TERMTMsgArray tmsgList = (RNW_RATIO_BY_TERMTMsgArray) searchTblByCond(param, prcSvcPlnTpCd);
        if (tmsgList.getValidCount() > 0) {
            return (RNW_RATIO_BY_TERMTMsg) tmsgList.get(0);
        } else {
            return null;
        }
    }

    // START 2018/08/22 K.Kojima [QC#27867,DEL]
    // private CONTR_INTFC_RNW_MAPTMsg getRnwPrcUpRatioForOther() {
    //     CONTR_INTFC_RNW_MAPTMsg param = new CONTR_INTFC_RNW_MAPTMsg();
    //     param.setSQLID("002");
    //     param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
    //     param.setConditionValue("rntlRnwFlg01", ZYPConstant.FLG_OFF_N);
    //     param.setConditionValue("leaseRnwFlg01", ZYPConstant.FLG_OFF_N);
    //     param.setConditionValue("enblFlg01", ZYPConstant.FLG_ON_Y);
    //     CONTR_INTFC_RNW_MAPTMsgArray tmsgList = (CONTR_INTFC_RNW_MAPTMsgArray) searchTblByCond(param, "002");
    //     if (tmsgList.getValidCount() > 0) {
    //         return (CONTR_INTFC_RNW_MAPTMsg) tmsgList.get(0);
    //     } else {
    //         return null;
    //     }
    // }
    // END 2018/08/22 K.Kojima [QC#27867,DEL]

    // START 2017/06/21 K.Kitachi [QC#18068, MOD]
//    private String getContrUplftTpCd(NSZC115001PMsg inPrmPMsg) {
    private String getContrUplftTpCd(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg) {
        String contrUplftTpCd = CONTR_UPLFT_TP.DO_NOT_UPLIFT;
        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
        //START 2018/05/24 [QC#26062, Mod]
        String prcSvcPlnTpCd = svcDtlPMsg.prcSvcPlnTpCd.getValue();
        if (!ZYPCommonFunc.hasValue(prcSvcPlnTpCd)) {
            return contrUplftTpCd;
        }
        if (!PRC_SVC_PLN_TP.STANDARD.equals(prcSvcPlnTpCd) && !PRC_SVC_PLN_TP.STD_2YR_FIXED.equals(prcSvcPlnTpCd)) {
            return contrUplftTpCd;
        }
        if (dsContrIntfcDefRuleTMsg != null && ZYPCommonFunc.hasValue(dsContrIntfcDefRuleTMsg.contrUplftTpCd)) {
//            return dsContrIntfcDefRuleTMsg.contrUplftTpCd.getValue();
            contrUplftTpCd = dsContrIntfcDefRuleTMsg.contrUplftTpCd.getValue();
//        } else {
//            return CONTR_UPLFT_TP.DO_NOT_UPLIFT;
        }
        return contrUplftTpCd;
//        DS_COND_CONSTTMsgArray dsCondConstTMsgArray = getDsCondConst(NSZC1150_SHELL_UPLFT, prcSvcPlnTpCd);
//        if (dsCondConstTMsgArray.getValidCount() == 0) {
//            return contrUplftTpCd;
//        }
//        CONTR_UPLFT_TPTMsgArray contrUplftTpTMsgArray = getContrUplftTp(dsCondConstTMsgArray.no(0).dsCondConstValTxt_01.getValue(), dsCondConstTMsgArray.no(0).dsCondConstValTxt_02.getValue());
//        if (contrUplftTpTMsgArray.getValidCount() == 0) {
//            return contrUplftTpCd;
//        }
//        return contrUplftTpTMsgArray.no(0).contrUplftTpCd.getValue();
        //END   2018/05/24 [QC#26062, Mod]
    }

    private String getUplftPrcMethCd(NSZC115001PMsg inPrmPMsg) {
        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
        if (dsContrIntfcDefRuleTMsg != null) {
            return dsContrIntfcDefRuleTMsg.uplftPrcMethCd.getValue();
        } else {
            return UPLFT_PRC_METH.MARKUP_PERCENT;
        }
    }

    // add start 2017/08/09 QC#18799
    private BigDecimal getBefEndUplftDaysAot(NSZC115001PMsg inPrmPMsg) {
        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
        if (dsContrIntfcDefRuleTMsg != null && ZYPCommonFunc.hasValue(dsContrIntfcDefRuleTMsg.befEndUplftDaysAot)) {
            return dsContrIntfcDefRuleTMsg.befEndUplftDaysAot.getValue();
        }
        return BigDecimal.ZERO;
    }
    // add end 2017/08/09 QC#18799

//    private BigDecimal getUplftBasePrcUpRatio(NSZC115001PMsg inPrmPMsg) {
    private BigDecimal getUplftBasePrcUpRatio(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg) {
        BigDecimal uplftBasePrcUpRatio = null;
        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
        if (dsContrIntfcDefRuleTMsg != null) {
//            return dsContrIntfcDefRuleTMsg.uplftBasePrcUpRatio.getValue();
            uplftBasePrcUpRatio = dsContrIntfcDefRuleTMsg.uplftBasePrcUpRatio.getValue();
//        } else {
//            return null;
        }
        RNW_RATIO_BY_TERMTMsg rnwRatioByTermTMsg = getRnwRatioByTerm(svcDtlPMsg.prcSvcPlnTpCd.getValue());
        if (rnwRatioByTermTMsg == null) {
            return uplftBasePrcUpRatio;
        }
        return rnwRatioByTermTMsg.rnwPrcUpRatio.getValue();
    }

//    private BigDecimal getUplftMtrPrcUpRatio(NSZC115001PMsg inPrmPMsg) {
    private BigDecimal getUplftMtrPrcUpRatio(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg) {
        BigDecimal uplftMtrPrcUpRatio = null;
        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(inPrmPMsg);
        if (dsContrIntfcDefRuleTMsg != null) {
//            return dsContrIntfcDefRuleTMsg.uplftMtrPrcUpRatio.getValue();
            uplftMtrPrcUpRatio = dsContrIntfcDefRuleTMsg.uplftMtrPrcUpRatio.getValue();
//        } else {
//            return null;
        }
        RNW_RATIO_BY_TERMTMsg rnwRatioByTermTMsg = getRnwRatioByTerm(svcDtlPMsg.prcSvcPlnTpCd.getValue());
        if (rnwRatioByTermTMsg == null) {
            return uplftMtrPrcUpRatio;
        }
        return rnwRatioByTermTMsg.rnwPrcUpRatio.getValue();
    }
    // END 2017/06/21 K.Kitachi [QC#18068, MOD]

    // B/w Equipment Mode
    private void executeBwEquip(S21ApiMessageMap msgMap) {
        checkSnglChkForBwEquip(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        NSZC115001PMsg pMsg = (NSZC115001PMsg) msgMap.getPmsg();
        List<Map<String, Object>> updLst;
        BigDecimal dsContrDtlPk;
        BigDecimal dsContrAddlChrgPk;
        for (int i = 0; i < pMsg.svcConfigRefList.getValidCount(); i++) {
            updLst = getContrForBwEquip(pMsg.svcConfigRefList.no(i));
            for (Map<String, Object> updMap : updLst) {
                dsContrDtlPk = (BigDecimal) updMap.get("DS_CONTR_DTL_PK");
                dsContrAddlChrgPk = (BigDecimal) updMap.get("DS_CONTR_ADDL_CHRG_PK");
                // mod start 2017/06/06 CSA Defect#18846
                if (!updateContrDtlForBwEquip(dsContrDtlPk, msgMap, pMsg.svcConfigRefList.no(i).cpoOrdNum.getValue())) {
                    return;
                }
                if (!updateContrAddChrgForBwEquip(dsContrAddlChrgPk, msgMap, pMsg.svcConfigRefList.no(i).cpoOrdNum.getValue())) {
                    return;
                }
                // mod end 2017/06/06 CSA Defect#18846
            }
        }
    }

    private void checkSnglChkForBwEquip(S21ApiMessageMap msgMap) {
        NSZC115001PMsg pMsg = (NSZC115001PMsg) msgMap.getPmsg();

        for (int i = 0; i < pMsg.svcConfigRefList.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(pMsg.svcConfigRefList.no(i).cpoOrdNum)) {
                setErrStatus(msgMap, NSZM0402E); //mod u.kim 2018/03/06 QC#24056
            }

            if (!ZYPCommonFunc.hasValue(pMsg.svcConfigRefList.no(i).cpoDtlLineNum)) {
                setErrStatus(msgMap, NSZM0403E); //mod u.kim 2018/03/06 QC#24056
            }

            if (!ZYPCommonFunc.hasValue(pMsg.svcConfigRefList.no(i).cpoDtlLineSubNum)) {
                setErrStatus(msgMap, NSZM0404E); //mod u.kim 2018/03/06 QC#24056
           }
        }
    }

    private List<Map<String, Object>> getContrForBwEquip(NSZC115001_svcConfigRefListPMsg listPMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", listPMsg.cpoOrdNum.getValue());
        param.put("cpoOrdDtlLineNum", listPMsg.cpoDtlLineNum.getValue());
        param.put("cpoOrdDtlLineSubNum", listPMsg.cpoDtlLineSubNum.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContrForBwEquip", param);
    }

    // mod start 2017/06/06 CSA Defect#18846
    private boolean updateContrDtlForBwEquip(BigDecimal dsContrDtlPk, S21ApiMessageMap msgMap, String cpoOrdNum) {
        if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            return true;
        }

        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
        dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(dsContrDtlTMsg);
        if (dsContrDtlTMsg == null) {
            setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
            return false;
        }

        List<DS_CONTR_DTLTMsg> dsContrDtlList = new ArrayList<DS_CONTR_DTLTMsg>();
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipInvdFlg, ZYPConstant.FLG_ON_Y);
        dsContrDtlList.add(dsContrDtlTMsg);
        int result = S21FastTBLAccessor.update(dsContrDtlList.toArray(new DS_CONTR_DTLTMsg[0]));
        if (result != dsContrDtlList.size()) {
            setErrStatus(msgMap, NSZM1228E); //mod u.kim 2018/03/06 QC#24056
            return false;
        }

        // DS_CONTR_DTL_REC
        if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
            return true;
        }
        String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());

        BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_MOD, docId, cpoOrdNum);
        if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
            return true;
        }
        DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
        EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
        ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
        S21ApiTBLAccessor.insert(dsContrDtlRecTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlRecTMsg.getReturnCode())) {
            setErrStatus(msgMap, NSZM1264E); //mod u.kim 2018/03/06 QC#24056
            return false;
        }
        return true;
    }
    // mod end 2017/06/06 CSA Defect#18846

    // mod start 2017/06/06 CSA Defect#18846
    private boolean updateContrAddChrgForBwEquip(BigDecimal dsContrAddlChrgPk, S21ApiMessageMap msgMap, String cpoOrdNum) {
        if (!ZYPCommonFunc.hasValue(dsContrAddlChrgPk)) {
            return true;
        }

        DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg = new DS_CONTR_ADDL_CHRGTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dsContrAddlChrgPk, dsContrAddlChrgPk);
        dsContrAddlChrgTMsg = (DS_CONTR_ADDL_CHRGTMsg) S21ApiTBLAccessor.findByKey(dsContrAddlChrgTMsg);
        if (dsContrAddlChrgTMsg == null) {
            setErrStatus(msgMap, NSZM1240E); //mod u.kim 2018/03/06 QC#24056
            return false;
        }

        List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.billWithEquipInvdFlg, ZYPConstant.FLG_ON_Y);
        dsContrAddlChrgList.add(dsContrAddlChrgTMsg);
        int result = S21FastTBLAccessor.update(dsContrAddlChrgList.toArray(new DS_CONTR_ADDL_CHRGTMsg[0]));
        if (result != dsContrAddlChrgList.size()) {
            setErrStatus(msgMap, NSZM1238E); //mod u.kim 2018/03/06 QC#24056
            return false;
        }

        // DS_CONTR_ADDL_CHRG_REC
        String docId = getDsContrAddlChrgDocId(dsContrAddlChrgTMsg);

        BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_MOD, docId, cpoOrdNum);
        if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
            return true;
        }
        DS_CONTR_ADDL_CHRG_RECTMsg dsContrAddlChrgRecTMsg = new DS_CONTR_ADDL_CHRG_RECTMsg();
        EZDMsg.copy(dsContrAddlChrgTMsg, null, dsContrAddlChrgRecTMsg, null);
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgRecTMsg.bizProcLogPk, bizProcLogPk);
        S21ApiTBLAccessor.insert(dsContrAddlChrgRecTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrAddlChrgRecTMsg.getReturnCode())) {
            setErrStatus(msgMap, NSZM1265E); //mod u.kim 2018/03/06 QC#24056
            return false;
        }

        return true;
    }
    // mod end 2017/06/06 CSA Defect#18846

    private NSZC115001_svcConfigRefListPMsg getDsContrDtlPkByDsOrdPosnNum(NSZC115001_svcConfigRefListPMsgArray svcConfigRefList, NSZC115001_svcPrcListPMsg svcPrcPMsg) {
        if (!ZYPCommonFunc.hasValue(svcPrcPMsg.shellLineNum) || !ZYPCommonFunc.hasValue(svcPrcPMsg.dsOrdPosnNum)) {
            return null;
        }

        for (int i = 0; i < svcConfigRefList.getValidCount(); i++) {
            NSZC115001_svcConfigRefListPMsg svcConfigRefListPMsg = svcConfigRefList.no(i);
            if (ZYPCommonFunc.hasValue(svcConfigRefListPMsg.shellLineNum) && svcPrcPMsg.shellLineNum.getValue().compareTo(svcConfigRefListPMsg.shellLineNum.getValue()) == 0 && svcPrcPMsg.dsOrdPosnNum.getValue().equals(svcConfigRefListPMsg.dsOrdPosnNum.getValue())) {
                return svcConfigRefListPMsg;
            }
        }

        return null;
    }

    // Copy Mode
    private void executeCopy(S21ApiMessageMap msgMap) {
        checkSnglChkForCopy(msgMap);
        if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
            return;
        }

        NSZC115001PMsg pMsg = (NSZC115001PMsg) msgMap.getPmsg();
        for (int i = 0; i < pMsg.svcConfigRefList.getValidCount(); i++) {
            if(!copyContr(msgMap, i)) {
                return;
            }
        }
    }

    private void checkSnglChkForCopy(S21ApiMessageMap msgMap) {
        NSZC115001PMsg pMsg = (NSZC115001PMsg) msgMap.getPmsg();

        for (int i = 0; i < pMsg.svcConfigRefList.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(pMsg.svcConfigRefList.no(i).cpoOrdNum)) {
                setErrStatus(msgMap, NSZM0402E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private boolean copyContr(S21ApiMessageMap msgMap, int rowNum) {
        NSZC115001PMsg pMsg = (NSZC115001PMsg) msgMap.getPmsg();
        String slsDt = pMsg.slsDt.getValue();
        String origCpoOrdNum = pMsg.refCpoOrdNum.getValue();
        String newCpoOrdNum = pMsg.svcConfigRefList.no(rowNum).cpoOrdNum.getValue();
        // Get DS_CONTR
        List<BigDecimal> dsContrList = getDsContrForCopy(origCpoOrdNum);
        BigDecimal newDsContrPk;
        for (BigDecimal origDsContrPk : dsContrList) {
            // Copy DS_CONTR
            DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, origDsContrPk);
            dsContrTMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrTMsg);
            if (dsContrTMsg == null) {
                setErrStatus(msgMap, NSZM1226E); //mod u.kim 2018/03/06 QC#24056
                return false;
            }
            // START 2017/09/16 [QC#21007, MOD]
            // newDsContrPk = copyForDsContr(msgMap, dsContrTMsg, slsDt, newCpoOrdNum);
            // if (msgMap.getMsgMgr().isXxMsgId()) {
            //     return false;
            // }
            if (isExistsAddContr(origCpoOrdNum, null, origDsContrPk) && !origCpoOrdNum.equals(dsContrTMsg.cpoOrdNum.getValue())) {
                newDsContrPk = origDsContrPk;
            } else {
                newDsContrPk = copyForDsContr(msgMap, dsContrTMsg, slsDt, newCpoOrdNum);
                if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                    return false;
                }
            }
            // END   2017/09/16 [QC#21007, MOD]
            
            Map<BigDecimal, BigDecimal> newOrigDsContrDtlPkReln = new HashMap<BigDecimal, BigDecimal>(); // S21_NA Add QC#21393

            //QC#25030
            boolean isAggregate = DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue());
            BigDecimal aggBllgFreeCopyCnt = null;
            if (isAggregate) {
                aggBllgFreeCopyCnt = getAggBllgFreeCopyCnt(dsContrTMsg.dsContrPk.getValue());
            }

            // Get DS_CONTR_DTL
            List<Map<String, Object>> dsContrDtlList = getDsContrDtlForCopy(origDsContrPk, origCpoOrdNum, newCpoOrdNum);
            BigDecimal origDsContrDtlPk = null;
            BigDecimal newDsContrDtlPk = null;
            BigDecimal lineDsContrDtlPk = null;
            BigDecimal machDsContrDtlPk = null;
            for (Map<String, Object> dsContrDtlMap : dsContrDtlList) {
                origDsContrDtlPk = (BigDecimal) dsContrDtlMap.get("DS_CONTR_DTL_PK");
                // Copy DS_CONTR_DTL
                DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, origDsContrDtlPk);
                dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
                if (dsContrDtlTMsg == null) {
                    setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
                    return false;
                }
                // START 2017/10/20 [QC#21484, MOD]
                newDsContrDtlPk = copyForDsContrDtl(msgMap, dsContrDtlTMsg, dsContrDtlMap, newDsContrPk, newCpoOrdNum, lineDsContrDtlPk, machDsContrDtlPk, dsContrTMsg.dsContrCatgCd.getValue());
                // END 2017/10/20 [QC#21484, MOD]
                newOrigDsContrDtlPkReln.put(origDsContrDtlPk, newDsContrDtlPk); // S21_NA Add QC#21393
                if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                    return false;
                }
                if (isFltAggLine(dsContrDtlTMsg)) {
                    lineDsContrDtlPk = newDsContrDtlPk;
                } else if (isMainMach(dsContrDtlTMsg)) {
                    machDsContrDtlPk = newDsContrDtlPk;
                }

                // Get DS_CONTR_BLLG_MTR
                List<BigDecimal> dsContrBllgMtrList = getDsContrBllgMtrForCopy(origDsContrDtlPk);
                BigDecimal newDsContrBllgMtrPk;
                for (BigDecimal origDsContrBllgMtrPk : dsContrBllgMtrList) {
                    // Copy DS_CONTR_BLLG_MTR
                    DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = new DS_CONTR_BLLG_MTRTMsg();
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrBllgMtrPk, origDsContrBllgMtrPk);
                    dsContrBllgMtrTMsg = (DS_CONTR_BLLG_MTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrBllgMtrTMsg);
                    if (dsContrBllgMtrTMsg == null) {
                        setErrStatus(msgMap, NSZM1255E); //mod u.kim 2018/03/06 QC#24056
                        return false;
                    }
                    // QC#25030
                    if (isAggregate //
                            && DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue()) //
                            && ZYPCommonFunc.hasValue(aggBllgFreeCopyCnt) //
                            && !ZYPCommonFunc.hasValue(dsContrBllgMtrTMsg.bllgFreeCopyCnt)) {
                        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgFreeCopyCnt, aggBllgFreeCopyCnt);
                        aggBllgFreeCopyCnt = null;
                    }
                    newDsContrBllgMtrPk = copyForDsContrBllgMtr(msgMap, dsContrBllgMtrTMsg, newDsContrDtlPk);
                    if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                        return false;
                    }

                    // Copy CONTR_PHYS_BLLG_MTR_RELN
                    copyForContrPhysBllgMtrReln(msgMap, origDsContrBllgMtrPk, newDsContrDtlPk, newDsContrBllgMtrPk);
                    if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                        return false;
                    }

                    // Copy CONTR_XS_COPY
                    copyForContrXsCopy(msgMap, origDsContrBllgMtrPk, newDsContrBllgMtrPk);
                    if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                        return false;
                    }
                }

                // Copy DS_CONTR_ADDL_CHRG
                copyForDsContrAddlChrg(msgMap, origDsContrPk, origDsContrDtlPk, newDsContrPk, newDsContrDtlPk, newCpoOrdNum);
                if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                    return false;
                }

                // add start 2020/03/24 QC#54318
                // Copy DS_CONTR_RNW_ESCL
                copyForDsContrRnwEscl(msgMap, origDsContrPk, origDsContrDtlPk, newDsContrPk, newDsContrDtlPk);
                if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                    return false;
                }
                // add end 2020/03/24 QC#54318

                // Copy DS_CONTR_CR_CARD_PO
                copyForDsContrCrCardPo(msgMap, origDsContrPk, origDsContrDtlPk, newDsContrPk, newDsContrDtlPk);
                if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                    return false;
                }

                // Copy SVC_TERM_COND
                copyForSvcTermCond(msgMap, origDsContrPk, origDsContrDtlPk, newDsContrPk, newDsContrDtlPk);
                if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                    return false;
                }
            }

            // START 2018/07/06 K.Kitachi [QC#26886, ADD]
            if (origDsContrPk.compareTo(newDsContrPk) != 0) {
            // END 2018/07/06 K.Kitachi [QC#26886, ADD]

                // DS_CONTR_REC
                registDsContrRec(msgMap, newDsContrPk, newCpoOrdNum);
                if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                    return false;
                }

                // Copy SVC_MEMO
                copyForSvcMemo(msgMap, origDsContrPk, newDsContrPk, newCpoOrdNum);
                if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                    return false;
                }

                // Copy DS_CONTR_RNW_ESCL
                copyForDsContrRnwEscl(msgMap, origDsContrPk, newDsContrPk);
                if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                    return false;
                }

                // Copy DS_CONTR_TAX_DTL
                copyForDsContrTaxDtl(msgMap, origDsContrPk, origCpoOrdNum, newDsContrPk, newCpoOrdNum);
                if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                    return false;
                }

                // Copy DS_CONTR_CR_CARD_PO Header
                copyForDsContrCrCardPo(msgMap, origDsContrPk, newDsContrPk);
                if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                    return false;
                }

                // Copy SVC_TERM_COND Header
                copyForSvcTermCond(msgMap, origDsContrPk, newDsContrPk);
                if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                    return false;
                }

            // START 2018/07/06 K.Kitachi [QC#26886, ADD]
            }
            // END 2018/07/06 K.Kitachi [QC#26886, ADD]

            // S21_NA Add Start QC#21393
            // Copy SCHD_CRAT_TMPL, SCHD_CRAT_TMPL_LINE 
            copyForSchdCratTmpl(msgMap, origDsContrPk, origCpoOrdNum, newDsContrPk, newCpoOrdNum, newOrigDsContrDtlPkReln);
            if (existsErrMsgFlg) { //mod u.kim 2018/03/06 QC#24056
                return false;
            }
            // S21_NA Add End QC#21393
        }
        return true;
    }

    private BigDecimal getAggBllgFreeCopyCnt(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        return (BigDecimal) this.ssmBatchClient.queryObject("getAggBllgFreeCopyCnt", param);
    }

    private List<BigDecimal> getDsContrForCopy(String cpoOrdNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrForCopy", param);
    }

    private BigDecimal copyForDsContr(S21ApiMessageMap msgMap, DS_CONTRTMsg inMsg, String slsDt, String newCpoOrdNum) {
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        BigDecimal newDsContrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_SQ);
        // set value
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, inMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, newDsContrPk);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrSqNum, inMsg.dsContrSqNum);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrTpCd, inMsg.dsContrTpCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrStsCd, DS_CONTR_STS.ORDER);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.altPayerCustCd, inMsg.altPayerCustCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrCatgCd, inMsg.dsContrCatgCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.pmtTermCashDiscCd, inMsg.pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcContrRefCmntTxt, newCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.bllgApvlReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.ccyCd, inMsg.ccyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.preInvReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.rnwCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.invSeptBaseUsgFlg, inMsg.invSeptBaseUsgFlg);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrCratDt, slsDt);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrCratPsnCd, getDsContrCratPsnCd(EZDDBCICarrier.getUserID()));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.leaseCmpyCd, inMsg.leaseCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsAcctNum, inMsg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.tocCd, inMsg.tocCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.baseChrgToLeaseCmpyFlg, inMsg.baseChrgToLeaseCmpyFlg);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.usgChrgToLeaseCmpyFlg, inMsg.usgChrgToLeaseCmpyFlg);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.mtrEstTpCd, inMsg.mtrEstTpCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.prcAllocByMachQtyFlg, inMsg.prcAllocByMachQtyFlg);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcContrBrCd, inMsg.svcContrBrCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrClsCd, inMsg.dsContrClsCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.ctacPsnPk, inMsg.ctacPsnPk);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcLineBizCd, inMsg.svcLineBizCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrSrcTpCd, DS_CONTR_SRC_TP.CPO);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrEdiCd, inMsg.dsContrEdiCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcPgmMdseCd, inMsg.svcPgmMdseCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.contrAdminPsnCd, inMsg.contrAdminPsnCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.baseBllgCycleCd, inMsg.baseBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.mtrBllgCycleCd, inMsg.mtrBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.prcSvcContrTpCd, inMsg.prcSvcContrTpCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.prcSvcPlnTpCd, inMsg.prcSvcPlnTpCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.billByTpCd, inMsg.billByTpCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.cpoSvcAgmtVerNum, inMsg.cpoSvcAgmtVerNum);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.manContrOvrdFlg, inMsg.manContrOvrdFlg);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcPrcCatgCd, inMsg.svcPrcCatgCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.crRebilCd, inMsg.crRebilCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.applyEquipBillToFlg, inMsg.applyEquipBillToFlg);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.fixTermInMthAot, inMsg.fixTermInMthAot);
        // add start 2017/06/19 CSA Defect#19036
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.shellLineNum, inMsg.shellLineNum);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.billWithEquipFlg, getFlgValue(inMsg.billWithEquipFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.fromPerMthNum, inMsg.fromPerMthNum);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.toPerMthNum, inMsg.toPerMthNum);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.cpoOrdNum, newCpoOrdNum);
       // add end 2017/06/19 CSA Defect#19036
        // 2018/08/27 S21_NA#25105 Add Start
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.addlBasePrcCatgCd, inMsg.addlBasePrcCatgCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.rntlPrcCatgCd, inMsg.rntlPrcCatgCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.addlChrgPrcCatgCd, inMsg.addlChrgPrcCatgCd);
        // 2018/08/27 S21_NA#25105 Add End
        // START 2022/03/22 [QC#59683, ADD]
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsInvTgtrTpCd, inMsg.dsInvTgtrTpCd);
        // END   2022/03/22 [QC#59683, ADD]

        S21ApiTBLAccessor.insert(dsContrTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
            setErrStatus(msgMap, NSZM1223E); //mod u.kim 2018/03/06 QC#24056
            return null;
        }

        return newDsContrPk;
    }

    private void registDsContrRec(S21ApiMessageMap msgMap, BigDecimal dsContrPk, String cpoOrdNum) {
        NSZC115001PMsg pMsg = (NSZC115001PMsg) msgMap.getPmsg();
        // DS_CONTR_REC
        String docId = getDsContrDocId(dsContrPk, pMsg);
        BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_NEW, docId, cpoOrdNum);
        if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
            return;
        }

        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, dsContrPk);
        dsContrTMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(dsContrTMsg);
        if (dsContrTMsg == null) {
            setErrStatus(msgMap, NSZM1263E); //mod u.kim 2018/03/06 QC#24056
            return;
        }

        DS_CONTR_RECTMsg dsContrRecTMsg = new DS_CONTR_RECTMsg();
        EZDMsg.copy(dsContrTMsg, null, dsContrRecTMsg, null);
        ZYPEZDItemValueSetter.setValue(dsContrRecTMsg.bizProcLogPk, bizProcLogPk);

        S21ApiTBLAccessor.insert(dsContrRecTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrRecTMsg.getReturnCode())) {
            setErrStatus(msgMap, NSZM1263E); //mod u.kim 2018/03/06 QC#24056
        }
    }

    private List<Map<String, Object>> getDsContrDtlForCopy(BigDecimal dsContrPk, String origCpoOrdNum, String newCpoOrdNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("cpoOrdNum", origCpoOrdNum);
        param.put("newCpoOrdNum", newCpoOrdNum);
        param.put("dsContrDtlTpShell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        param.put("dsContrDtlTpFlt", DS_CONTR_DTL_TP.FLEET);
        param.put("dsContrDtlTpAgg", DS_CONTR_DTL_TP.AGGREGATE);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrDtlForCopy", param);
    }


    // START 2017/10/20 [QC#21484, MOD]
    private BigDecimal copyForDsContrDtl(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg inMsg, Map<String, Object> dsContrDtlMap, BigDecimal newDsContrPk, String newCpoOrdNum, BigDecimal lineDsContrDtlPk, BigDecimal machDsContrDtlPk, String dsContrCatgCd) {
    // END 2017/10/20 [QC#21484, MOD]
        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        BigDecimal newDsContrDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_DTL_SQ);
        // set value
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, inMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, newDsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrPk, newDsContrPk);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlTpCd, inMsg.dsContrDtlTpCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.ORDER);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoOrdNum, newCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoDtlLineNum, inMsg.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.cpoDtlLineSubNum, inMsg.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsOrdTpCd, inMsg.dsOrdTpCd);
        BigDecimal svcConfigMstrPk = (BigDecimal) dsContrDtlMap.get("NEW_SVC_CONFIG_MSTR_PK");
        if (ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcConfigMstrPk, svcConfigMstrPk);
        }
        if (isMainMach(dsContrDtlTMsg)) {
            // START 2017/09/25 [QC#21007, MOD]
            // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prntDsContrDtlPk, lineDsContrDtlPk);
            if (ZYPCommonFunc.hasValue(lineDsContrDtlPk)) {
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prntDsContrDtlPk, lineDsContrDtlPk);
            } else {
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prntDsContrDtlPk, inMsg.prntDsContrDtlPk);
            }
            // END   2017/09/25 [QC#21007, MOD]
        } else if (isAcc(dsContrDtlTMsg)) {
            // START 2017/09/25 [QC#21007, MOD]
            // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prntDsContrDtlPk, machDsContrDtlPk);
            if (ZYPCommonFunc.hasValue(machDsContrDtlPk)) {
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prntDsContrDtlPk, machDsContrDtlPk);
            } else {
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prntDsContrDtlPk, inMsg.prntDsContrDtlPk);
            }
            // END   2017/09/25 [QC#21007, MOD]
        }
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgCycleCd, inMsg.baseBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, inMsg.baseBllgTmgCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrBllgDay, inMsg.contrBllgDay);
        // START 2017/10/20 [QC#21484, MOD]
        // START 2017/09/25 [QC#21007, MOD]
        // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgTmgCd, BLLG_TMG_TP.ARREARS);
//        if (isMainMach(dsContrDtlTMsg) || isFltAggLine(dsContrDtlTMsg)) {
//            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgTmgCd, fleetToNull(BLLG_TMG_TP.ARREARS, DS_CONTR_DTL_TP.FLEET.equals(inMsg.dsContrDtlTpCd.getValue())));
//        } 
        // END   2017/09/25 [QC#21007, MOD]
        if (isMainMach(dsContrDtlTMsg)) {
            if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgTmgCd, BLLG_TMG_TP.ARREARS);
            }
        } else if (isFltAggLine(dsContrDtlTMsg)) {
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgTmgCd, BLLG_TMG_TP.ARREARS);
        } 
        // END   2017/10/20 [QC#21484, MOD]

        // START 2019/11/22 [QC#51325, MOD]
        // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, inMsg.basePrcDealAmt);
        // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcFuncAmt, inMsg.basePrcFuncAmt);
        if (!DS_CONTR_DTL_STS.ORDER.equals(inMsg.dsContrDtlStsCd.getValue())) {
            if (ZYPCommonFunc.hasValue(inMsg.basePrcDealAmt) && ZYPCommonFunc.hasValue(inMsg.rntlPrcAmt)) {
                BigDecimal basePrcDealAmt = inMsg.basePrcDealAmt.getValue();
                BigDecimal rntlPrcAmt = inMsg.rntlPrcAmt.getValue();
                basePrcDealAmt = basePrcDealAmt.add(rntlPrcAmt);
                BigDecimal basePrcFuncAmt = NSXC003001Exchange.calcFuncFromDeal(glblCmpyCd, getCcyCd(), ZYPDateUtil.getSalesDate(), basePrcDealAmt);

                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, basePrcDealAmt);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcFuncAmt, basePrcFuncAmt);
            } else {
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, inMsg.basePrcDealAmt);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcFuncAmt, inMsg.basePrcFuncAmt);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, inMsg.basePrcDealAmt);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcFuncAmt, inMsg.basePrcFuncAmt);
        }
        // END   2019/11/22 [QC#51325, MOD]
        // add start 2017/07/10 QC#19818
        if (DS_CONTR_DTL_TP.FLEET.equals(inMsg.dsContrDtlTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.basePrcDealAmt, BigDecimal.ZERO);
            dsContrDtlTMsg.basePrcFuncAmt.clear();
        }
        // add end   2017/07/10 QC#19818
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBillToCustCd, inMsg.baseBillToCustCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.usgBillToCustCd, inMsg.usgBillToCustCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrCloDay, inMsg.mtrCloDay);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrBllgDay, inMsg.mtrBllgDay);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.supprCrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrReadMethCd, inMsg.mtrReadMethCd);
        // START 2017/09/25 [QC#21007, MOD]
        // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrRnwTotCnt, BigDecimal.ZERO);
        if (!isShellLine(dsContrDtlTMsg)) {
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrRnwTotCnt, BigDecimal.ZERO);
        }
        // END   2017/09/25 [QC#21007, MOD]
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.ctacPsnPk, inMsg.ctacPsnPk);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcPgmMdseCd, inMsg.svcPgmMdseCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcMtrPkgPk, inMsg.prcMtrPkgPk);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.uplftEffFromDt, inMsg.uplftEffFromDt);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseDplyPerEndDay, inMsg.baseDplyPerEndDay);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mtrDplyPerEndDay, inMsg.mtrDplyPerEndDay);
        // START 2017/06/22 K.Kitachi [QC#19394, ADD]
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.shipToCustCd, inMsg.shipToCustCd);
        // END 2017/06/22 K.Kitachi [QC#19394, ADD]
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.shellLineNum, inMsg.shellLineNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addContrFlg, inMsg.addContrFlg);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.addAsryFlg, inMsg.addAsryFlg);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.mdlId, inMsg.mdlId);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcPrcCatgCd, inMsg.svcPrcCatgCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipFlg, inMsg.billWithEquipFlg);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcListEquipConfigNum, inMsg.prcListEquipConfigNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.prcCatgCd, inMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.crRebilCd, inMsg.crRebilCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dealPrcListPrcAmt, inMsg.dealPrcListPrcAmt);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.corpAdvPrcFlg, inMsg.corpAdvPrcFlg);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.sellToCustCd, inMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlGrpNum, inMsg.dsContrDtlGrpNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.fromPerMthNum, inMsg.fromPerMthNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.toPerMthNum, inMsg.toPerMthNum);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.totBasePrcDealAmt, inMsg.totBasePrcDealAmt);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.totBasePrcFuncAmt, inMsg.totBasePrcFuncAmt);
        // START 2018/07/09 K.Kim [QC#26611, ADD]
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.rntlPrcAmt, inMsg.rntlPrcAmt);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.rntlPrcCatgCd, inMsg.rntlPrcCatgCd);
        // END 2018/07/09 K.Kim [QC#26611, ADD]
        // QC#29371 2019/01/29 Add Start
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrCratTpCd, inMsg.dsContrCratTpCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.svcInvMergeTpCd, inMsg.svcInvMergeTpCd);
        // QC#29371 2019/01/29 Add End

        S21ApiTBLAccessor.insert(dsContrDtlTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTMsg.getReturnCode())) {
            setErrStatus(msgMap, NSZM1227E); //mod u.kim 2018/03/06 QC#24056
            return null;
        }

        // DS_CONTR_DTL_REC
        if (isFltAggLine(dsContrDtlTMsg) || isShellLine(dsContrDtlTMsg)) {
            return newDsContrDtlPk;
        }
        String docId = getDsContrDtlDocId(dsContrDtlTMsg.dsContrDtlPk.getValue());
        BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_NEW, docId, newCpoOrdNum);
        if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
            return newDsContrDtlPk;
        }
        DS_CONTR_DTL_RECTMsg dsContrDtlRecTMsg = new DS_CONTR_DTL_RECTMsg();
        EZDMsg.copy(dsContrDtlTMsg, null, dsContrDtlRecTMsg, null);
        ZYPEZDItemValueSetter.setValue(dsContrDtlRecTMsg.bizProcLogPk, bizProcLogPk);
        S21ApiTBLAccessor.insert(dsContrDtlRecTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlRecTMsg.getReturnCode())) {
            setErrStatus(msgMap, NSZM1264E); //mod u.kim 2018/03/06 QC#24056
            return null;
        }

        return newDsContrDtlPk;
    }

    private boolean isShellLine(DS_CONTR_DTLTMsg inMsg) {
        if (DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL.equals(inMsg.dsContrDtlTpCd.getValue())) {
            return true;
        }
        return false;
    }

    private boolean isFltAggLine(DS_CONTR_DTLTMsg inMsg) {
        if (DS_CONTR_DTL_TP.FLEET.equals(inMsg.dsContrDtlTpCd.getValue())
                || DS_CONTR_DTL_TP.AGGREGATE.equals(inMsg.dsContrDtlTpCd.getValue())) {
            return true;
        }
        return false;
    }

    private boolean isMainMach(DS_CONTR_DTLTMsg inMsg) {
        if (DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(inMsg.dsContrDtlTpCd.getValue())
                || DS_CONTR_DTL_TP.BASE_ONLY.equals(inMsg.dsContrDtlTpCd.getValue())
                || DS_CONTR_DTL_TP.USAGE_ONLY.equals(inMsg.dsContrDtlTpCd.getValue())) {
            return true;
        }
        return false;
    }

    private boolean isAcc(DS_CONTR_DTLTMsg inMsg) {
        if (DS_CONTR_DTL_TP.ACCESSORIES.equals(inMsg.dsContrDtlTpCd.getValue())) {
            return true;
        }
        return false;
    }

    private List<BigDecimal> getDsContrBllgMtrForCopy(BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrBllgMtrForCopy", param);
    }

    private BigDecimal copyForDsContrBllgMtr(S21ApiMessageMap msgMap, DS_CONTR_BLLG_MTRTMsg inMsg, BigDecimal newDsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = new DS_CONTR_BLLG_MTRTMsg();
        BigDecimal newDsContrBllgMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_MTR_SQ);
        // set value
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.glblCmpyCd, inMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrBllgMtrPk, newDsContrBllgMtrPk);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrDtlPk, newDsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMtrLbCd, inMsg.bllgMtrLbCd);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMtrBillToCustCd, inMsg.bllgMtrBillToCustCd);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMtrBllgCycleCd, inMsg.bllgMtrBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.xsChrgTpCd, inMsg.xsChrgTpCd);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.ORDER);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.ctacPsnPk, inMsg.ctacPsnPk);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.intgMdseCd, inMsg.intgMdseCd);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.prcListBandCd, inMsg.prcListBandCd);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.prcBookMdseCd, inMsg.prcBookMdseCd);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.sellToCustCd, inMsg.sellToCustCd);

        // QC#25030
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgFreeCopyCnt, inMsg.bllgFreeCopyCnt);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMinAmtRate, inMsg.bllgMinAmtRate);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMinCnt, inMsg.bllgMinCnt);
        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgRollOverRatio, inMsg.bllgRollOverRatio);

        S21ApiTBLAccessor.insert(dsContrBllgMtrTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrBllgMtrTMsg.getReturnCode())) {
            setErrStatus(msgMap, NSZM1231E); //mod u.kim 2018/03/06 QC#24056
            return null;
        }
        return newDsContrBllgMtrPk;
    }

    private void copyForContrPhysBllgMtrReln(S21ApiMessageMap msgMap, BigDecimal origDsContrBllgMtrPk, BigDecimal newDsContrDtlPk, BigDecimal newDsContrBllgMtrPk) {
        // Get CONTR_PHYS_BLLG_MTR_RELN
        List<BigDecimal> contrPhysBllgMtrRelnList = getContrPhysBllgMtrRelnForCopy(origDsContrBllgMtrPk);
        for (BigDecimal origContrPhysBllgMtrRelnPk : contrPhysBllgMtrRelnList) {
            CONTR_PHYS_BLLG_MTR_RELNTMsg inMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.contrPhysBllgMtrRelnPk, origContrPhysBllgMtrRelnPk);
            inMsg = (CONTR_PHYS_BLLG_MTR_RELNTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                setErrStatus(msgMap, NSZM1256E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrRelnTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
            BigDecimal newContrPhysBllgMtrRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_PHYS_BLLG_MTR_RELN_SQ);
            // set value
            ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.glblCmpyCd, inMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.contrPhysBllgMtrRelnPk, newContrPhysBllgMtrRelnPk);
            ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.dsContrDtlPk, newDsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.contrMtrMultRate, inMsg.contrMtrMultRate);
            ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.bllgMtrLbCd, inMsg.bllgMtrLbCd);
            ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.dsContrBllgMtrPk, newDsContrBllgMtrPk);
            ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.bllblFlg, inMsg.bllblFlg);
            ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.regMtrLbCd, inMsg.regMtrLbCd);
            ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.actvFlg, inMsg.actvFlg);
            // add start 2017/08/29 CSA Defect#20831
            ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTMsg.bllgMtrLvlNum, inMsg.bllgMtrLvlNum);
            // add end 2017/08/29 CSA Defect#20831

            S21ApiTBLAccessor.insert(contrPhysBllgMtrRelnTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrPhysBllgMtrRelnTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1233E); //mod u.kim 2018/03/06 QC#24056
                return;
            }
        }
    }

    private List<BigDecimal> getContrPhysBllgMtrRelnForCopy(BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getContrPhysBllgMtrRelnForCopy", param);
    }

    private void copyForContrXsCopy(S21ApiMessageMap msgMap, BigDecimal origDsContrBllgMtrPk, BigDecimal newDsContrBllgMtrPk) {
        // Get CONTR_XS_COPY
        List<BigDecimal> contrXsCopyList = getContrXsCopyForCopy(origDsContrBllgMtrPk);
        for (BigDecimal origContrXsCopyPk : contrXsCopyList) {
            CONTR_XS_COPYTMsg inMsg = new CONTR_XS_COPYTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.contrXsCopyPk, origContrXsCopyPk);
            inMsg = (CONTR_XS_COPYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                setErrStatus(msgMap, NSZM1257E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            CONTR_XS_COPYTMsg contrXsCopyTMsg = new CONTR_XS_COPYTMsg();
            BigDecimal newContrXsCopyPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_XS_COPY_SQ);
            // set value
            ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.glblCmpyCd, inMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.contrXsCopyPk, newContrXsCopyPk);
            ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.dsContrBllgMtrPk, newDsContrBllgMtrPk);
            ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.xsMtrCopyQty, inMsg.xsMtrCopyQty);
            ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.xsMtrAmtRate, inMsg.xsMtrAmtRate);
            ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.xsMtrFirstFlg, inMsg.xsMtrFirstFlg);
            ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.prcSvcTierTpCd, inMsg.prcSvcTierTpCd);
            ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.maxCopyVolCnt, inMsg.maxCopyVolCnt);

            S21ApiTBLAccessor.insert(contrXsCopyTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrXsCopyTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1235E); //mod u.kim 2018/03/06 QC#24056
                return;
            }
        }
    }

    private List<BigDecimal> getContrXsCopyForCopy(BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getContrXsCopyForCopy", param);
    }

    private void copyForDsContrAddlChrg(S21ApiMessageMap msgMap, BigDecimal origDsContrPk, BigDecimal origDsContrDtlPk, BigDecimal newDsContrPk, BigDecimal newDsContrDtlPk, String newCpoOrdNum) {
        // Get DS_CONTR_ADDL_CHRG
        List<BigDecimal> dsContrAddlChrgList = getDsContrAddlChrgForCopy(origDsContrPk, origDsContrDtlPk);
        for (BigDecimal origDsContrAddlChrgPk : dsContrAddlChrgList) {
            DS_CONTR_ADDL_CHRGTMsg inMsg = new DS_CONTR_ADDL_CHRGTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrAddlChrgPk, origDsContrAddlChrgPk);
            inMsg = (DS_CONTR_ADDL_CHRGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                setErrStatus(msgMap, NSZM1240E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg = new DS_CONTR_ADDL_CHRGTMsg();
            BigDecimal newDsContrAddlChrgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_ADDL_CHRG_SQ);
            // set value
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.glblCmpyCd, inMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dsContrAddlChrgPk, newDsContrAddlChrgPk);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dsContrPk, newDsContrPk);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dsContrDtlPk, newDsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgTpCd, inMsg.addlChrgTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.svcBillByTpCd, inMsg.svcBillByTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgInvTpCd, inMsg.addlChrgInvTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.ccyCd, inMsg.ccyCd);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgFlatDealPrcAmt, inMsg.addlChrgFlatDealPrcAmt);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgFlatFuncPrcAmt, inMsg.addlChrgFlatFuncPrcAmt);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.printDtlFlg, inMsg.printDtlFlg);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.bllgCycleCd, inMsg.bllgCycleCd);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgInvdFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.prcCatgCd, inMsg.prcCatgCd);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.svcPrcCatgCd, inMsg.svcPrcCatgCd);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.billWithEquipFlg, inMsg.billWithEquipFlg);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.crRebilCd, inMsg.crRebilCd);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.addlChrgCatgCd, inMsg.addlChrgCatgCd);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dealPrcListPrcAmt, inMsg.dealPrcListPrcAmt);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.prcListEquipConfigNum, inMsg.prcListEquipConfigNum);

            S21ApiTBLAccessor.insert(dsContrAddlChrgTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrAddlChrgTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1237E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            
            // DS_CONTR_ADDL_CHRG_REC
            String docId = getDsContrAddlChrgDocId(dsContrAddlChrgTMsg);
            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_NEW, docId, newCpoOrdNum);
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            DS_CONTR_ADDL_CHRG_RECTMsg dsContrAddlChrgRecTMsg = new DS_CONTR_ADDL_CHRG_RECTMsg();
            EZDMsg.copy(dsContrAddlChrgTMsg, null, dsContrAddlChrgRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgRecTMsg.bizProcLogPk, bizProcLogPk);
            S21ApiTBLAccessor.insert(dsContrAddlChrgRecTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrAddlChrgRecTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1265E); //mod u.kim 2018/03/06 QC#24056
                return;
            }
        }
    }

    private List<BigDecimal> getDsContrAddlChrgForCopy(BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("svcBillByTpCd", SVC_BILL_BY_TP.BASE);
        param.put("addlChrgInvTpCd", ADDL_CHRG_INV_TP.BASE);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrAddlChrgForCopy", param);
    }

    // add start 2020/03/24 QC#54318
    private void copyForDsContrRnwEscl(S21ApiMessageMap msgMap, BigDecimal origDsContrPk, BigDecimal origDsContrDtlPk, BigDecimal newDsContrPk, BigDecimal newDsContrDtlPk) {
        // Get DS_CONTR_RNW_ESCL
        List<BigDecimal> dsContrRnwEsclList = getDsContrRnwEsclForCopy(origDsContrPk, origDsContrDtlPk);
        for (BigDecimal origDsContrRnwEsclPk : dsContrRnwEsclList) {
            DS_CONTR_RNW_ESCLTMsg inMsg = new DS_CONTR_RNW_ESCLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrRnwEsclPk, origDsContrRnwEsclPk);
            inMsg = (DS_CONTR_RNW_ESCLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                setErrStatus(msgMap, NSZM1261E);
                return;
            }
    
            DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg = new DS_CONTR_RNW_ESCLTMsg();
            BigDecimal newDsContrRnwEsclPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_RNW_ESCL_SQ);
            // set value
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.glblCmpyCd, inMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrRnwEsclPk, newDsContrRnwEsclPk);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrPk, newDsContrPk);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrDtlPk, newDsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrMachLvlNum, inMsg.dsContrMachLvlNum);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.contrAutoRnwTpCd, inMsg.contrAutoRnwTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.rnwPrcMethCd, inMsg.rnwPrcMethCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.befEndRnwDaysAot, inMsg.befEndRnwDaysAot);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.basePrcUpRatio, inMsg.basePrcUpRatio);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.mtrPrcUpRatio, inMsg.mtrPrcUpRatio);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.contrUplftTpCd, inMsg.contrUplftTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.uplftPrcMethCd, inMsg.uplftPrcMethCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.uplftBasePrcUpRatio, inMsg.uplftBasePrcUpRatio);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.uplftMtrPrcUpRatio, inMsg.uplftMtrPrcUpRatio);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.firstYrContrUplftFlg, inMsg.firstYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.scdYrContrUplftFlg, inMsg.scdYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.thirdYrContrUplftFlg, inMsg.thirdYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.frthYrContrUplftFlg, inMsg.frthYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.fifthYrContrUplftFlg, inMsg.fifthYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.sixthYrContrUplftFlg, inMsg.sixthYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.svnthYrContrUplftFlg, inMsg.svnthYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.eighthYrContrUplftFlg, inMsg.eighthYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.ninthYrContrUplftFlg, inMsg.ninthYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.tenthYrContrUplftFlg, inMsg.tenthYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.uplftBefEndRnwDaysAot, inMsg.uplftBefEndRnwDaysAot);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.fixTermInMthAot, inMsg.fixTermInMthAot);
    
            S21ApiTBLAccessor.insert(dsContrRnwEsclTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrRnwEsclTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1246E);
                return;
            }
        }
    }

    private List<BigDecimal> getDsContrRnwEsclForCopy(BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrRnwEsclForCopy", param);
    }
    // add end 2020/03/24 QC#54318

    private void copyForDsContrCrCardPo(S21ApiMessageMap msgMap, BigDecimal origDsContrPk, BigDecimal origDsContrDtlPk, BigDecimal newDsContrPk, BigDecimal newDsContrDtlPk) {
        // Get DS_CONTR_CR_CARD_PO
        List<BigDecimal> dsContrCrCardPoList = getDsContrCrCardPoForCopy(origDsContrPk, origDsContrDtlPk);
        for (BigDecimal origDsContrCrCardPoPk : dsContrCrCardPoList) {
            DS_CONTR_CR_CARD_POTMsg inMsg = new DS_CONTR_CR_CARD_POTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrCrCardPoPk, origDsContrCrCardPoPk);
            inMsg = (DS_CONTR_CR_CARD_POTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                setErrStatus(msgMap, NSZM1258E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
            BigDecimal newDsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
            // set value
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.glblCmpyCd, inMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrCrCardPoPk, newDsContrCrCardPoPk);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrPk, newDsContrPk);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrDtlPk, newDsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.crCardCustRefNum, inMsg.crCardCustRefNum);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.crCardExprYrMth, inMsg.crCardExprYrMth);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.custPoNum, inMsg.custPoNum);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.poDt, inMsg.poDt);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.leaseCmpyFlg, inMsg.leaseCmpyFlg);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrMachLvlNum, inMsg.dsContrMachLvlNum);
            // 2018/04/09 QC#20162 add start
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.custIssPoDt, inMsg.custIssPoDt);
            // 2018/04/09 QC#20162 add start
            // START 2019/01/10 S.Kitamura[QC#26928, ADD]
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.poFromDt, inMsg.poFromDt);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.crCardFlg, inMsg.crCardFlg);
            // END 2019/01/10 S.Kitamura[QC#26928, ADD]

            S21ApiTBLAccessor.insert(dsContrCrCardPoTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrCrCardPoTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1243E); //mod u.kim 2018/03/06 QC#24056
                return;
            }
        }
    }

    private List<BigDecimal> getDsContrCrCardPoForCopy(BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrCrCardPoForCopy", param);
    }

    private void copyForSvcTermCond(S21ApiMessageMap msgMap, BigDecimal origDsContrPk, BigDecimal origDsContrDtlPk, BigDecimal newDsContrPk, BigDecimal newDsContrDtlPk) {
        // Get SVC_TERM_COND
        List<BigDecimal> svcTermCondList = getSvcTermCondForCopy(origDsContrPk, origDsContrDtlPk);
        for (BigDecimal origSvcTermCondPk : svcTermCondList) {
            SVC_TERM_CONDTMsg inMsg = new SVC_TERM_CONDTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.svcTermCondPk, origSvcTermCondPk);
            inMsg = (SVC_TERM_CONDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                setErrStatus(msgMap, NSZM1259E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            SVC_TERM_CONDTMsg svcTermCondTMsg = new SVC_TERM_CONDTMsg();
            BigDecimal newSvcTermCondPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_SQ);
            // set value
            ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.glblCmpyCd, inMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.svcTermCondPk, newSvcTermCondPk);
            ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.dsContrPk, newDsContrPk);
            ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.dsContrDtlPk, newDsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.svcTermCondAttrbPk, inMsg.svcTermCondAttrbPk);
            ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.svcTermAttrbMapValCd, inMsg.svcTermAttrbMapValCd);

            S21ApiTBLAccessor.insert(svcTermCondTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTermCondTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1241E); //mod u.kim 2018/03/06 QC#24056
                return;
            }
        }
    }

    private List<BigDecimal> getSvcTermCondForCopy(BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getSvcTermCondForCopy", param);
    }

    private void copyForSvcMemo(S21ApiMessageMap msgMap, BigDecimal origDsContrPk, BigDecimal newDsContrPk, String newCpoOrdNum) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        // Get SVC_MEMO
        List<BigDecimal> svcMemoList = getSvcMemoForCopy(origDsContrPk);
        for (BigDecimal origSvcMemoPk : svcMemoList) {
            SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.svcMemoPk, origSvcMemoPk);
            inMsg = (SVC_MEMOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                setErrStatus(msgMap, NSZM1260E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
            BigDecimal newSvcMemoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ);
            // set value
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.glblCmpyCd, inMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoPk, newSvcMemoPk);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoCatgCd, inMsg.svcMemoCatgCd);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTpCd, inMsg.svcMemoTpCd);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcCmntTxt, inMsg.svcCmntTxt);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.dsContrPk, newDsContrPk);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdUsrId, getDsContrCratPsnCd(EZDDBCICarrier.getUserID()));
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(TS_STORE_PATTERN));
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoRsnCd, inMsg.svcMemoRsnCd);
            // START 2017/07/28 [QC#20088, ADD]
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTrxNm, SVC_MEMO_TRX_NM_CPO_ORD_NUM);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTrxNum, newCpoOrdNum);
            // END   2017/07/28 [QC#20088, ADD]

            S21ApiTBLAccessor.insert(svcMemoTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcMemoTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1251E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            String docId = getSvcMemoDocId(svcMemoTMsg, inPrmPMsg);
            BigDecimal bizProcLogPk = printBizProcLog(RQST_TP_NEW, docId, newCpoOrdNum);
            if (!ZYPCommonFunc.hasValue(bizProcLogPk)) {
                continue;
            }
            SVC_MEMO_RECTMsg svcMemoRecTMsg = new SVC_MEMO_RECTMsg();
            EZDMsg.copy(svcMemoTMsg, null, svcMemoRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(svcMemoRecTMsg.bizProcLogPk, bizProcLogPk);
            S21ApiTBLAccessor.insert(svcMemoRecTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcMemoRecTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1266E); //mod u.kim 2018/03/06 QC#24056
                return;
            }
        }
    }

    private List<BigDecimal> getSvcMemoForCopy(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("svcMemoCatgCd", SVC_MEMO_CATG.CONTRACT_MEMO);
        param.put("svcMemoTpCd", SVC_MEMO_TP.OVERRIDE_SHELL_CONTRACT);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getSvcMemoForCopy", param);
    }

    private void copyForDsContrRnwEscl(S21ApiMessageMap msgMap, BigDecimal origDsContrPk, BigDecimal newDsContrPk) {
        // Get DS_CONTR_RNW_ESCL
        List<BigDecimal> dsContrRnwEsclList = getDsContrRnwEsclForCopy(origDsContrPk);
        for (BigDecimal origDsContrRnwEsclPk : dsContrRnwEsclList) {
            DS_CONTR_RNW_ESCLTMsg inMsg = new DS_CONTR_RNW_ESCLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrRnwEsclPk, origDsContrRnwEsclPk);
            inMsg = (DS_CONTR_RNW_ESCLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                setErrStatus(msgMap, NSZM1261E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg = new DS_CONTR_RNW_ESCLTMsg();
            BigDecimal newDsContrRnwEsclPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_RNW_ESCL_SQ);
            // set value
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.glblCmpyCd, inMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrRnwEsclPk, newDsContrRnwEsclPk);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrPk, newDsContrPk);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrMachLvlNum, inMsg.dsContrMachLvlNum);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.contrAutoRnwTpCd, inMsg.contrAutoRnwTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.rnwPrcMethCd, inMsg.rnwPrcMethCd);
            // START 2017/06/22 K.Kitachi [QC#19441, ADD]
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.befEndRnwDaysAot, inMsg.befEndRnwDaysAot);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.basePrcUpRatio, inMsg.basePrcUpRatio);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.mtrPrcUpRatio, inMsg.mtrPrcUpRatio);
            // END 2017/06/22 K.Kitachi [QC#19441, ADD]
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.contrUplftTpCd, inMsg.contrUplftTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.uplftPrcMethCd, inMsg.uplftPrcMethCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.uplftBasePrcUpRatio, inMsg.uplftBasePrcUpRatio);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.uplftMtrPrcUpRatio, inMsg.uplftMtrPrcUpRatio);
            // START 2017/06/29 K.Kitachi [QC#18068, MOD]
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.firstYrContrUplftFlg, inMsg.firstYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.scdYrContrUplftFlg, inMsg.scdYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.thirdYrContrUplftFlg, inMsg.thirdYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.frthYrContrUplftFlg, inMsg.frthYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.fifthYrContrUplftFlg, inMsg.fifthYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.sixthYrContrUplftFlg, inMsg.sixthYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.svnthYrContrUplftFlg, inMsg.svnthYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.eighthYrContrUplftFlg, inMsg.eighthYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.ninthYrContrUplftFlg, inMsg.ninthYrContrUplftFlg);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.tenthYrContrUplftFlg, inMsg.tenthYrContrUplftFlg);
            // END 2017/06/29 K.Kitachi [QC#18068, MOD]
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.uplftBefEndRnwDaysAot, inMsg.uplftBefEndRnwDaysAot);
            // START 2018/11/15 [QC#28638, ADD]
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.fixTermInMthAot, inMsg.fixTermInMthAot);
            // END 2018/11/15 [QC#28638, ADD]

            S21ApiTBLAccessor.insert(dsContrRnwEsclTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrRnwEsclTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1246E); //mod u.kim 2018/03/06 QC#24056
                return;
            }
        }
    }

    private List<BigDecimal> getDsContrRnwEsclForCopy(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrRnwEsclForCopy", param);
    }

    private void copyForDsContrTaxDtl(S21ApiMessageMap msgMap, BigDecimal origDsContrPk, String origCpoOrdNum, BigDecimal newDsContrPk, String newCpoOrdNum) {
        // Get DS_CONTR_TAX_DTL
        List<BigDecimal> dsContrTaxDtlList = getDsContrTaxDtlForCopy(origDsContrPk, origCpoOrdNum);
        for (BigDecimal origDsContrTaxDtlPk : dsContrTaxDtlList) {
            DS_CONTR_TAX_DTLTMsg inMsg = new DS_CONTR_TAX_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrTaxDtlPk, origDsContrTaxDtlPk);
            inMsg = (DS_CONTR_TAX_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                setErrStatus(msgMap, NSZM1262E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            DS_CONTR_TAX_DTLTMsg dsContrTaxDtlTMsg = new DS_CONTR_TAX_DTLTMsg();
            BigDecimal newDsContrTaxDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_TAX_DTL_SQ);
            // set value
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.glblCmpyCd, inMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.dsContrTaxDtlPk, newDsContrTaxDtlPk);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.dsContrPk, newDsContrPk);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.cpoOrdNum, newCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.dsContrTaxLineNum, inMsg.dsContrTaxLineNum);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.dsContrTaxPct, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.dealDsContrTaxAmt, inMsg.dealDsContrTaxAmt);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.funcDsContrTaxAmt, inMsg.funcDsContrTaxAmt);

            S21ApiTBLAccessor.insert(dsContrTaxDtlTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrTaxDtlTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1249E); //mod u.kim 2018/03/06 QC#24056
                return;
            }
        }
    }

    private List<BigDecimal> getDsContrTaxDtlForCopy(BigDecimal dsContrPk, String cpoOrdNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("cpoOrdNum", cpoOrdNum);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrTaxDtlForCopy", param);
    }

    private void copyForDsContrCrCardPo(S21ApiMessageMap msgMap, BigDecimal origDsContrPk, BigDecimal newDsContrPk) {
        // Get DS_CONTR_CR_CARD_PO
        List<BigDecimal> dsContrCrCardPoList = getDsContrCrCardPoForCopy(origDsContrPk);
        for (BigDecimal origDsContrCrCardPoPk : dsContrCrCardPoList) {
            DS_CONTR_CR_CARD_POTMsg inMsg = new DS_CONTR_CR_CARD_POTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrCrCardPoPk, origDsContrCrCardPoPk);
            inMsg = (DS_CONTR_CR_CARD_POTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                setErrStatus(msgMap, NSZM1258E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
            BigDecimal newDsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
            // set value
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.glblCmpyCd, inMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrCrCardPoPk, newDsContrCrCardPoPk);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrPk, newDsContrPk);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.crCardCustRefNum, inMsg.crCardCustRefNum);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.crCardExprYrMth, inMsg.crCardExprYrMth);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.custPoNum, inMsg.custPoNum);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.poDt, inMsg.poDt);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.leaseCmpyFlg, inMsg.leaseCmpyFlg);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrMachLvlNum, inMsg.dsContrMachLvlNum);
            // 2018/04/09 QC#20162 add start
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.custIssPoDt, inMsg.custIssPoDt);
            // 2018/04/09 QC#20162 add end
            // START 2019/01/28 T.Tomita[QC#26928, ADD]
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.poFromDt, inMsg.poFromDt);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.crCardFlg, inMsg.crCardFlg);
            // END 2019/01/10 T.Tomita[QC#26928, ADD]

            S21ApiTBLAccessor.insert(dsContrCrCardPoTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrCrCardPoTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1243E); //mod u.kim 2018/03/06 QC#24056
                return;
            }
        }
    }

    private List<BigDecimal> getDsContrCrCardPoForCopy(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrCrCardPoForCopy", param);
    }

    private void copyForSvcTermCond(S21ApiMessageMap msgMap, BigDecimal origDsContrPk, BigDecimal newDsContrPk) {
        // Get SVC_TERM_COND
        List<BigDecimal> svcTermCondList = getSvcTermCondForCopy(origDsContrPk);
        for (BigDecimal origSvcTermCondPk : svcTermCondList) {
            SVC_TERM_CONDTMsg inMsg = new SVC_TERM_CONDTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.svcTermCondPk, origSvcTermCondPk);
            inMsg = (SVC_TERM_CONDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                setErrStatus(msgMap, NSZM1259E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            SVC_TERM_CONDTMsg svcTermCondTMsg = new SVC_TERM_CONDTMsg();
            BigDecimal newSvcTermCondPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_SQ);
            // set value
            ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.glblCmpyCd, inMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.svcTermCondPk, newSvcTermCondPk);
            ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.dsContrPk, newDsContrPk);
            ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.svcTermCondAttrbPk, inMsg.svcTermCondAttrbPk);
            ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.svcTermAttrbMapValCd, inMsg.svcTermAttrbMapValCd);

            S21ApiTBLAccessor.insert(svcTermCondTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTermCondTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1241E); //mod u.kim 2018/03/06 QC#24056
                return;
            }
        }
    }

    private List<BigDecimal> getSvcTermCondForCopy(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getSvcTermCondForCopy", param);
    }
    
    // S21_NA Add Start QC#21393
    private void copyForSchdCratTmpl(S21ApiMessageMap msgMap, BigDecimal origDsContrPk, String origCpoOrdNum, BigDecimal newDsContrPk, String newCpoOrdNum, Map<BigDecimal, BigDecimal> newOrigDsContrDtlPkReln) {
        // Get SCHD_CRAT_TMPL
        List<BigDecimal> schdCratTmplList = getSchdCratTmplForCopy(origDsContrPk, origCpoOrdNum);
        BigDecimal newschdCratTmplPk = null;
        for (BigDecimal schdCratTmplPk : schdCratTmplList) {
            SCHD_CRAT_TMPLTMsg tmplInMsg = new SCHD_CRAT_TMPLTMsg();
            ZYPEZDItemValueSetter.setValue(tmplInMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmplInMsg.schdCratTmplPk, schdCratTmplPk);
            tmplInMsg = (SCHD_CRAT_TMPLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmplInMsg);
            if (tmplInMsg == null) {
                setErrStatus(msgMap, NSZM1303E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            SCHD_CRAT_TMPLTMsg schdCratTmplTMsg = new SCHD_CRAT_TMPLTMsg();
            newschdCratTmplPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SCHD_CRAT_TMPL_SQ);
            // set value
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.glblCmpyCd, tmplInMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.schdCratTmplPk, newschdCratTmplPk);
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.dsContrPk, newDsContrPk);
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.refCpoOrdNum, newCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.lgContrDaysAot, tmplInMsg.lgContrDaysAot);
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.wtyPerDaysAot, tmplInMsg.wtyPerDaysAot);
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.dsOrdTpCd, tmplInMsg.dsOrdTpCd);
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.custIssPoNum, tmplInMsg.custIssPoNum);
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.custIssPoDt, tmplInMsg.custIssPoDt);
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.sysSrcCd, tmplInMsg.sysSrcCd);
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.sysSrcRefNum, tmplInMsg.sysSrcRefNum);
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.schdCratTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.cpoSrcTpCd, CPO_SRC_TP.COPY);
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.ordSrcRefNum, origCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.splyBaseAmt, tmplInMsg.splyBaseAmt);
            ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.qtyContrCapQty, tmplInMsg.qtyContrCapQty);

            S21ApiTBLAccessor.insert(schdCratTmplTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdCratTmplTMsg.getReturnCode())) {
                setErrStatus(msgMap, NSZM1304E); //mod u.kim 2018/03/06 QC#24056
                return;
            }

            // Get SCHD_CRAT_TMPL_LINE
            List<BigDecimal> schdCratTmplLineList = getSchdCratTmplLineForCopy(schdCratTmplPk);
            for (BigDecimal schdCratTmplLineNum : schdCratTmplLineList) {
                SCHD_CRAT_TMPL_LINETMsg tmplLineInMsg = new SCHD_CRAT_TMPL_LINETMsg();
                ZYPEZDItemValueSetter.setValue(tmplLineInMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmplLineInMsg.schdCratTmplPk, schdCratTmplPk);
                ZYPEZDItemValueSetter.setValue(tmplLineInMsg.schdCratTmplLineNum, schdCratTmplLineNum);
                tmplLineInMsg = (SCHD_CRAT_TMPL_LINETMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmplLineInMsg);
                if (tmplLineInMsg == null) {
                    setErrStatus(msgMap, NSZM1305E); //mod u.kim 2018/03/06 QC#24056 
                    return;
                }

                SCHD_CRAT_TMPL_LINETMsg schdCratTmplLineTMsg = new SCHD_CRAT_TMPL_LINETMsg();
                // set value
                ZYPEZDItemValueSetter.setValue(schdCratTmplLineTMsg.glblCmpyCd, tmplLineInMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(schdCratTmplLineTMsg.schdCratTmplPk, newschdCratTmplPk);
                ZYPEZDItemValueSetter.setValue(schdCratTmplLineTMsg.schdCratTmplLineNum, schdCratTmplLineNum);
                ZYPEZDItemValueSetter.setValue(schdCratTmplLineTMsg.mdseCd, tmplLineInMsg.mdseCd);
                ZYPEZDItemValueSetter.setValue(schdCratTmplLineTMsg.schdAllwQty, tmplLineInMsg.schdAllwQty);
                ZYPEZDItemValueSetter.setValue(schdCratTmplLineTMsg.shpgIntvlCd, tmplLineInMsg.shpgIntvlCd);
                ZYPEZDItemValueSetter.setValue(schdCratTmplLineTMsg.otmShipQty, tmplLineInMsg.otmShipQty);
                ZYPEZDItemValueSetter.setValue(schdCratTmplLineTMsg.firstShipQty, tmplLineInMsg.firstShipQty);
                ZYPEZDItemValueSetter.setValue(schdCratTmplLineTMsg.dsContrDtlPk, newOrigDsContrDtlPkReln.get(tmplLineInMsg.dsContrDtlPk.getValue()));

                S21ApiTBLAccessor.insert(schdCratTmplLineTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdCratTmplLineTMsg.getReturnCode())) {
                    setErrStatus(msgMap, NSZM1306E); //mod u.kim 2018/03/06 QC#24056
                    return;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private List<BigDecimal> getSchdCratTmplForCopy(BigDecimal dsContrPk, String cpoOrdNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("cpoOrdNum", cpoOrdNum);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getSchdCratTmplForCopy", param);
    }
    
    @SuppressWarnings("unchecked")
    private List<BigDecimal> getSchdCratTmplLineForCopy(BigDecimal schdCratTmplPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("schdCratTmplPk", schdCratTmplPk);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getSchdCratTmplLineForCopy", param);
    }
    // S21_NA Add End QC#21393

    private boolean checkDsContr(S21ApiMessageMap msgMap, List<DS_CONTRTMsg> dsContrTMsgList) {
        boolean result = true;
        for (DS_CONTRTMsg dsContrTMsg : dsContrTMsgList) {
            // 2018/05/07 QC#22482 Add Start
            if (ZYPConstant.FLG_ON_Y.equals(dsContrTMsg.manContrOvrdFlg.getValue())) {
                continue;
            }
            // 2018/05/07 QC#22482 Add End
            if (!ZYPCommonFunc.hasValue(dsContrTMsg.pmtTermCashDiscCd)) {
                setErrStatus(msgMap, NSZM1268E); //mod u.kim 2018/03/06 QC#24056
                result = false;
            }
            if (!ZYPCommonFunc.hasValue(dsContrTMsg.svcContrBrCd)) {
                setErrStatus(msgMap, NSZM1269E); //mod u.kim 2018/03/06 QC#24056
                result = false;
            }
            if (!ZYPCommonFunc.hasValue(dsContrTMsg.dsContrClsCd)) {
                setErrStatus(msgMap, NSZM1267E); //mod u.kim 2018/03/06 QC#24056
                result = false;
            }
            if (!ZYPCommonFunc.hasValue(dsContrTMsg.svcLineBizCd)) {
                setErrStatus(msgMap, NSZM1270E); //mod u.kim 2018/03/06 QC#24056
                result = false;
            }
            if (!ZYPCommonFunc.hasValue(dsContrTMsg.contrAdminPsnCd)) {
                setErrStatus(msgMap, NSZM1271E); //mod u.kim 2018/03/06 QC#24056
                result = false;
            }
        }
        return result;
    }

    private boolean checkDsContrDtl(S21ApiMessageMap msgMap, List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList) {
        boolean result = true;
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgList) {
            if (DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(dsContrDtlTMsg.shipToCustCd)) {
                    setErrStatus(msgMap, NSZM1287E); //mod u.kim 2018/03/06 QC#24056
                    result = false;
                }
            }

            DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, dsContrDtlTMsg.dsContrPk.getValue());
            dsContrTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(dsContrTMsg);
            if (dsContrTMsg == null) {
                continue;
            }

            if (isUnderFleet(dsContrTMsg, dsContrDtlTMsg)) {
                continue;
            }

            DS_CONTR_DTL_TPTMsg dsContrDtlTpTMsg = new DS_CONTR_DTL_TPTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrDtlTpTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTpTMsg.dsContrDtlTpCd, dsContrDtlTMsg.dsContrDtlTpCd);
            dsContrDtlTpTMsg = (DS_CONTR_DTL_TPTMsg) S21CodeTableAccessor.findByKey(dsContrDtlTpTMsg);
            if (dsContrDtlTpTMsg == null) {
                continue;
            }

            if (ZYPConstant.FLG_ON_Y.equals(dsContrDtlTpTMsg.baseChrgFlg.getValue())) {
                if (!ZYPCommonFunc.hasValue(dsContrDtlTMsg.contrBllgDay)) {
                    setErrStatus(msgMap, NSZM1273E); //mod u.kim 2018/03/06 QC#24056
                    result = false;
                }
            }
            if (ZYPConstant.FLG_ON_Y.equals(dsContrDtlTpTMsg.usgChrgFlg.getValue())) {
                if (!ZYPCommonFunc.hasValue(dsContrDtlTMsg.mtrBllgDay)) {
                    setErrStatus(msgMap, NSZM1273E); //mod u.kim 2018/03/06 QC#24056
                    result = false;
                }
            }
        }
        return result;
    }

    private boolean checkDsContrBllgMtr(S21ApiMessageMap msgMap, List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgList) {
        boolean result = true;
        for (DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg : dsContrBllgMtrTMsgList) {
            if (isShellLine(dsContrBllgMtrTMsg.dsContrDtlPk.getValue())) {
                continue;
            }

            if (!ZYPCommonFunc.hasValue(dsContrBllgMtrTMsg.bllgMtrLbCd)) {
                setErrStatus(msgMap, NSZM1272E); //mod u.kim 2018/03/06 QC#24056
                result = false;
            }
        }
        return result;
    }

    private boolean checkContrPhysBllgMtrReln(S21ApiMessageMap msgMap, List<CONTR_PHYS_BLLG_MTR_RELNTMsg> contrPhysBllgMtrRelnTMsgList) {
        boolean result = true;
        BigDecimal spcRate = BigDecimal.ONE.negate();
        BigDecimal minRate = ZYPCodeDataUtil.getNumConstValue(NSAL0320_MTR_MULT_RATE_MIN_NUM, this.glblCmpyCd);
        BigDecimal maxRate = ZYPCodeDataUtil.getNumConstValue(NSAL0320_MTR_MULT_RATE_MAX_NUM, this.glblCmpyCd);
        BigDecimal fctNum = ZYPCodeDataUtil.getNumConstValue(NSAL0320_MTR_MULT_RATE_FCT_NUM, this.glblCmpyCd);

        for (CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrRelnTMsg : contrPhysBllgMtrRelnTMsgList) {
            if (isShellLine(contrPhysBllgMtrRelnTMsg.dsContrDtlPk.getValue())) {
                continue;
            }

            if (ZYPConstant.FLG_OFF_N.equals(contrPhysBllgMtrRelnTMsg.bllblFlg.getValue())) {
                continue;
            }

            BigDecimal multRate = contrPhysBllgMtrRelnTMsg.contrMtrMultRate.getValue();
            if (!ZYPCommonFunc.hasValue(multRate)) {
                continue;
            }
            if (multRate.compareTo(spcRate) != 0) {
                if (multRate.compareTo(minRate) < 0 || multRate.compareTo(maxRate) > 0) {
                    setErrStatus(msgMap, NSZM1186E); //mod u.kim 2018/03/06 QC#24056
                    result = false;
                }
                if (multRate.remainder(fctNum).compareTo(BigDecimal.ZERO) != 0) {
                    setErrStatus(msgMap, NSZM1186E); //mod u.kim 2018/03/06 QC#24056
                    result = false;
                }
            }
        }
        return result;
    }

    private boolean checkDsContrRnwEscl(S21ApiMessageMap msgMap, List<DS_CONTR_RNW_ESCLTMsg> dsContrRnwEsclTMsgList) {
        boolean result = true;
        for (DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg : dsContrRnwEsclTMsgList) {
            // Renewal Check
            if (isAutoRnw(dsContrRnwEsclTMsg.contrAutoRnwTpCd.getValue())) {
                if (!NSXC001001ContrValidation.checkAutoRnwMeth(this.glblCmpyCd, dsContrRnwEsclTMsg.contrAutoRnwTpCd.getValue(), dsContrRnwEsclTMsg.rnwPrcMethCd.getValue())) {
                    setErrStatus(msgMap, NSZM1274E); //mod u.kim 2018/03/06 QC#24056
                    result = false;
                } else {
                    if (!NSXC001001ContrValidation.checkBasePrcUpRatioMandatory(this.glblCmpyCd, dsContrRnwEsclTMsg.contrAutoRnwTpCd.getValue(), dsContrRnwEsclTMsg.rnwPrcMethCd.getValue(), dsContrRnwEsclTMsg.basePrcUpRatio.getValue())) {
                        setErrStatus(msgMap, NSZM1275E); //mod u.kim 2018/03/06 QC#24056
                        result = false;
                    }

                    if (!NSXC001001ContrValidation.checkBasePrcUpRatioNotAcceptable(this.glblCmpyCd, dsContrRnwEsclTMsg.contrAutoRnwTpCd.getValue(), dsContrRnwEsclTMsg.rnwPrcMethCd.getValue(), dsContrRnwEsclTMsg.basePrcUpRatio.getValue())) {
                        setErrStatus(msgMap, NSZM1276E); //mod u.kim 2018/03/06 QC#24056
                        result = false;
                    }

                    if (!NSXC001001ContrValidation.checkUsgPrcUpRatioMandatory(this.glblCmpyCd, dsContrRnwEsclTMsg.contrAutoRnwTpCd.getValue(), dsContrRnwEsclTMsg.rnwPrcMethCd.getValue(), dsContrRnwEsclTMsg.mtrPrcUpRatio.getValue())) {
                        setErrStatus(msgMap, NSZM1277E); //mod u.kim 2018/03/06 QC#24056
                        result = false;
                    }

                    if (!NSXC001001ContrValidation.checkUsgPrcUpRatioNotAcceptable(this.glblCmpyCd, dsContrRnwEsclTMsg.contrAutoRnwTpCd.getValue(), dsContrRnwEsclTMsg.rnwPrcMethCd.getValue(), dsContrRnwEsclTMsg.mtrPrcUpRatio.getValue())) {
                        setErrStatus(msgMap, NSZM1278E); //mod u.kim 2018/03/06 QC#24056
                        result = false;
                    }
                }
            }

            // Uplift Check
            if (!ZYPCommonFunc.hasValue(dsContrRnwEsclTMsg.contrUplftTpCd)) {
                setErrStatus(msgMap, NSZM1279E); //mod u.kim 2018/03/06 QC#24056
                result = false;
                continue;
            }

            if (isUplft(dsContrRnwEsclTMsg.contrUplftTpCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(dsContrRnwEsclTMsg.uplftPrcMethCd)) {
                    setErrStatus(msgMap, NSZM1280E); //mod u.kim 2018/03/06 QC#24056
                    result = false;
                } else {
                    if (!NSXC001001ContrValidation.checkBaseUplftRatioMandatory(this.glblCmpyCd, dsContrRnwEsclTMsg.contrUplftTpCd.getValue(), dsContrRnwEsclTMsg.uplftPrcMethCd.getValue(), dsContrRnwEsclTMsg.uplftBasePrcUpRatio.getValue())) {
                        setErrStatus(msgMap, NSZM1281E); //mod u.kim 2018/03/06 QC#24056
                        result = false;
                    }

                    if (!NSXC001001ContrValidation.checkBaseUplftRatioNotAcceptable(this.glblCmpyCd, dsContrRnwEsclTMsg.contrUplftTpCd.getValue(), dsContrRnwEsclTMsg.uplftPrcMethCd.getValue(), dsContrRnwEsclTMsg.uplftBasePrcUpRatio.getValue())) {
                        setErrStatus(msgMap, NSZM1282E); //mod u.kim 2018/03/06 QC#24056
                        result = false;
                    }

                    if (!NSXC001001ContrValidation.checkUsgUplftRatioMandatory(this.glblCmpyCd, dsContrRnwEsclTMsg.contrUplftTpCd.getValue(), dsContrRnwEsclTMsg.uplftPrcMethCd.getValue(), dsContrRnwEsclTMsg.uplftMtrPrcUpRatio.getValue())) {
                        setErrStatus(msgMap, NSZM1283E); //mod u.kim 2018/03/06 QC#24056
                        result = false;
                    }

                    if (!NSXC001001ContrValidation.checkUsgUplftRatioNotAcceptable(this.glblCmpyCd, dsContrRnwEsclTMsg.contrUplftTpCd.getValue(), dsContrRnwEsclTMsg.uplftPrcMethCd.getValue(), dsContrRnwEsclTMsg.uplftMtrPrcUpRatio.getValue())) {
                        setErrStatus(msgMap, NSZM1284E); //mod u.kim 2018/03/06 QC#24056
                        result = false;
                    }
                }

                if (!ZYPCommonFunc.hasValue(dsContrRnwEsclTMsg.uplftBefEndRnwDaysAot)) {
                    setErrStatus(msgMap, NSZM1285E); //mod u.kim 2018/03/06 QC#24056
                    result = false;
                }
            }
        }
        return result;
    }

    private boolean isUnderFleet(DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        if (!DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
            return false;
        }
        if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
            return false;
        }
        return true;
    }

    private boolean isShellLine(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, dsContrDtlPk);
        tMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            if (DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL.equals(tMsg.dsContrDtlTpCd.getValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean isAutoRnw(String contrAutoRnwTpCd) {
        CONTR_AUTO_RNW_TPTMsg tMsg = new CONTR_AUTO_RNW_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.contrAutoRnwTpCd, contrAutoRnwTpCd);
        tMsg = (CONTR_AUTO_RNW_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);
        if (tMsg != null) {
            if (ZYPConstant.FLG_ON_Y.equals(tMsg.autoRnwFlg.getValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean isUplft(String contrUplftTpCd) {
        CONTR_UPLFT_TPTMsg tMsg = new CONTR_UPLFT_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.contrUplftTpCd, contrUplftTpCd);
        tMsg = (CONTR_UPLFT_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);
        if (tMsg != null) {
            if (ZYPConstant.FLG_ON_Y.equals(tMsg.uplftBaseFlg.getValue()) || ZYPConstant.FLG_ON_Y.equals(tMsg.uplftUsgFlg.getValue())) {
                return true;
            }
        }
        return false;
    }

    private void convertContrPk(S21ApiMessageMap msgMap, NSZC115001_svcDtlListPMsg svcDtlPMsg) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();

        String dsContrCatgCd = svcDtlPMsg.dsContrCatgCd.getValue();
        String cpoOrdNum = inPrmPMsg.refCpoOrdNum.getValue();
        BigDecimal shellLineNum = svcDtlPMsg.shellLineNum.getValue();
        BigDecimal dsContrPkFrom = svcDtlPMsg.dsContrPk.getValue();
        BigDecimal dsContrPkTo = svcDtlPMsg.dsContrPk_AD.getValue();

        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgModList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgTMsgModList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();
        List<DS_CONTR_TAX_DTLTMsg> dsContrTaxDtlTMsgModList = new ArrayList<DS_CONTR_TAX_DTLTMsg>();
        List<SVC_TERM_CONDTMsg> svcTermCondTMsgModList = new ArrayList<SVC_TERM_CONDTMsg>();
        List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgModList = new ArrayList<DS_CONTR_CR_CARD_POTMsg>();
        // add start 2020/03/24 QC#54318
        List<DS_CONTR_RNW_ESCLTMsg> dsContrRnwEsclTMsgModList = new ArrayList<DS_CONTR_RNW_ESCLTMsg>();
        // add end 2020/03/24 QC#54318
        List<SCHD_CRAT_TMPLTMsg> schdCratTmplTMsgModList = new ArrayList<SCHD_CRAT_TMPLTMsg>();

        // SVC_MEMO
        SVC_MEMOTMsg svcMemoTMsg = getSvcMemoTMsg(dsContrPkFrom);
        if (svcMemoTMsg != null) {
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.dsContrPk, dsContrPkTo);
            S21FastTBLAccessor.update(svcMemoTMsg);
        }

        // DS_CONTR_DTL
        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        dsContrDtlTMsg.setSQLID("201");
        dsContrDtlTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        dsContrDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        dsContrDtlTMsg.setConditionValue("shellLineNum01", shellLineNum);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgAry = (DS_CONTR_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrDtlTMsg);
        for (int i = 0; i < dsContrDtlTMsgAry.getValidCount(); i++) {
            dsContrDtlTMsg = dsContrDtlTMsgAry.no(i);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrPk, dsContrPkTo);
            dsContrDtlTMsgModList.add(dsContrDtlTMsg);

            BigDecimal dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();

            // DS_CONTR_ADDL_CHRG
            DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg = new DS_CONTR_ADDL_CHRGTMsg();
            dsContrAddlChrgTMsg.setSQLID("001");
            dsContrAddlChrgTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            dsContrAddlChrgTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
            DS_CONTR_ADDL_CHRGTMsgArray dsContrAddlChrgTMsgAry = (DS_CONTR_ADDL_CHRGTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrAddlChrgTMsg);
            for (int j = 0; j < dsContrAddlChrgTMsgAry.getValidCount(); j++) {
                dsContrAddlChrgTMsg = dsContrAddlChrgTMsgAry.no(j);
                ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dsContrPk, dsContrPkTo);
                dsContrAddlChrgTMsgModList.add(dsContrAddlChrgTMsg);
            }

            // SVC_TERM_COND
            SVC_TERM_CONDTMsg svcTermCondTMsg = new SVC_TERM_CONDTMsg();
            svcTermCondTMsg.setSQLID("005");
            svcTermCondTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            svcTermCondTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
            SVC_TERM_CONDTMsgArray svcTermCondTMsgArray = (SVC_TERM_CONDTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(svcTermCondTMsg);
            for (int j = 0; j < svcTermCondTMsgArray.getValidCount(); j++) {
                svcTermCondTMsg = svcTermCondTMsgArray.no(j);
                ZYPEZDItemValueSetter.setValue(svcTermCondTMsg.dsContrPk, dsContrPkTo);
                svcTermCondTMsgModList.add(svcTermCondTMsg);
            }

            // DS_CONTR_CR_CARD_PO
            DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
            dsContrCrCardPoTMsg.setSQLID("003");
            dsContrCrCardPoTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            dsContrCrCardPoTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
            DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrCrCardPoTMsg);
            for (int j = 0; j < dsContrCrCardPoTMsgArray.getValidCount(); j++) {
                dsContrCrCardPoTMsg = dsContrCrCardPoTMsgArray.no(j);
                ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTMsg.dsContrPk, dsContrPkTo);
                dsContrCrCardPoTMsgModList.add(dsContrCrCardPoTMsg);
            }

            // add start 2020/03/24 QC#54318
            // DS_CONTR_RNW_ESCL
            DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg = new DS_CONTR_RNW_ESCLTMsg();
            dsContrRnwEsclTMsg.setSQLID("003");
            dsContrRnwEsclTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            dsContrRnwEsclTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
            DS_CONTR_RNW_ESCLTMsgArray dsContrRnwEsclTMsgArray = (DS_CONTR_RNW_ESCLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrRnwEsclTMsg);
            for (int j = 0; j < dsContrRnwEsclTMsgArray.getValidCount(); j++) {
                dsContrRnwEsclTMsg = dsContrRnwEsclTMsgArray.no(j);
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.dsContrPk, dsContrPkTo);
                dsContrRnwEsclTMsgModList.add(dsContrRnwEsclTMsg);
            }
            // add end 2020/03/24 QC#54318
        }

        // DS_CONTR_TAX_DTL
        DS_CONTR_TAX_DTLTMsg dsContrTaxDtlTMsg = new DS_CONTR_TAX_DTLTMsg();
        dsContrTaxDtlTMsg.setSQLID("001");
        dsContrTaxDtlTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        dsContrTaxDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        dsContrTaxDtlTMsg.setConditionValue("dsContrTaxLineNum01", shellLineNum);
        DS_CONTR_TAX_DTLTMsgArray dsContrTaxDtlTMsgAry = (DS_CONTR_TAX_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsContrTaxDtlTMsg);
        for (int i = 0; i < dsContrTaxDtlTMsgAry.getValidCount(); i++) {
            dsContrTaxDtlTMsg = dsContrTaxDtlTMsgAry.no(i);
            ZYPEZDItemValueSetter.setValue(dsContrTaxDtlTMsg.dsContrPk, dsContrPkTo);
            dsContrTaxDtlTMsgModList.add(dsContrTaxDtlTMsg);
        }

        // SCHD_CRAT_TMPL
        if (dsContrDtlTMsgAry.getValidCount() > 0) {
            SCHD_CRAT_TMPL_LINETMsg schdCratTmplLineTMsg = new SCHD_CRAT_TMPL_LINETMsg();
            schdCratTmplLineTMsg.setSQLID("001");
            schdCratTmplLineTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            schdCratTmplLineTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlTMsgAry.no(0).dsContrDtlPk.getValue());
            SCHD_CRAT_TMPL_LINETMsgArray schdCratTmplLineTMsgAry = (SCHD_CRAT_TMPL_LINETMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(schdCratTmplLineTMsg);
            if (schdCratTmplLineTMsgAry.getValidCount() > 0) {
                SCHD_CRAT_TMPLTMsg schdCratTmplTMsg = new SCHD_CRAT_TMPLTMsg();
                schdCratTmplTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                schdCratTmplTMsg.schdCratTmplPk.setValue(schdCratTmplLineTMsgAry.no(0).schdCratTmplPk.getValue());
                schdCratTmplTMsg = (SCHD_CRAT_TMPLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(schdCratTmplTMsg);
                if (schdCratTmplTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(schdCratTmplTMsg.dsContrPk, dsContrPkTo);
                    schdCratTmplTMsgModList.add(schdCratTmplTMsg);
                }
            }
        }

        // DS_CONTR_DTL
        if (!dsContrDtlTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.update(dsContrDtlTMsgModList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (result != dsContrDtlTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1228E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // DS_CONTR_ADDL_CHRG
        if (!dsContrAddlChrgTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.update(dsContrAddlChrgTMsgModList.toArray(new DS_CONTR_ADDL_CHRGTMsg[0]));
            if (result != dsContrAddlChrgTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1238E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // SVC_TERM_COND
        if (!svcTermCondTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.update(svcTermCondTMsgModList.toArray(new SVC_TERM_CONDTMsg[0]));
            if (result != svcTermCondTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1288E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // DS_CONTR_CR_CARD_PO
        if (!dsContrCrCardPoTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.update(dsContrCrCardPoTMsgModList.toArray(new DS_CONTR_CR_CARD_POTMsg[0]));
            if (result != dsContrCrCardPoTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1244E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // add start 2020/03/24 QC#54318
        // DS_CONTR_RNW_ESCL
        if (!dsContrRnwEsclTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.removeLogical(dsContrRnwEsclTMsgModList.toArray(new DS_CONTR_RNW_ESCLTMsg[0]));
            if (result != dsContrRnwEsclTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1245E);
            }
        }
        // add end 2020/03/24 QC#54318

        // DS_CONTR_TAX_DTL
        if (!dsContrTaxDtlTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.update(dsContrTaxDtlTMsgModList.toArray(new DS_CONTR_TAX_DTLTMsg[0]));
            if (result != dsContrTaxDtlTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1289E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        // SCHD_CRAT_TMPL
        if (!schdCratTmplTMsgModList.isEmpty()) {
            int result = S21FastTBLAccessor.update(schdCratTmplTMsgModList.toArray(new SCHD_CRAT_TMPLTMsg[0]));
            if (result != schdCratTmplTMsgModList.size()) {
                setErrStatus(msgMap, NSZM1290E); //mod u.kim 2018/03/06 QC#24056
            }
        }

        NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcConfigRefList.getValidCount(); i++) {
            svcConfigRefPMsg = inPrmPMsg.svcConfigRefList.no(i);
            if (!RQST_TP_MOD.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(svcConfigRefPMsg.shellLineNum) && shellLineNum.compareTo(svcConfigRefPMsg.shellLineNum.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.dsContrPk, dsContrPkTo);
            }
        }

        // START 2017/08/28 [QC#20665, DEL]
        // add start 2017/06/19 CSA Defect#19036
        // if (!clearDsContrCpoOrdNum(dsContrPkFrom)) {
        //     msgMap.addXxMsgId(NSZM1224E);
        // }
        // add end 2017/06/19 CSA Defect#19036
        // END   2017/08/28 [QC#20665, DEL]
    }

    private BigDecimal nullToZero(BigDecimal val) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return BigDecimal.ZERO;
        }
        return val;
    }

    // Physical Delete Mode
    private void executePhysDel(S21ApiMessageMap msgMap) {
        if (checkSnglChkForPhysDel(msgMap)) {
            return;
        }

        NSZC115001PMsg pMsg = (NSZC115001PMsg) msgMap.getPmsg();
        // START 2018/03/23 K.Kojima [QC#24458,ADD]
        List<String> cpoOrdNumList = new ArrayList<String>();
        // END 2018/03/23 K.Kojima [QC#24458,ADD]
        for (int i = 0; i < pMsg.svcConfigRefList.getValidCount(); i++) {
            // Delete DS Contract Detail
            List<BigDecimal> dsContrPkList = physDelForDetail(pMsg.svcConfigRefList.no(i));
            if (ZYPCommonFunc.hasValue(pMsg.svcConfigRefList.no(i).xxMsgId)) {
                return;
            }

            for (BigDecimal dsContrPk : dsContrPkList) {
                // Delete DS Contract
                physDelForHeader(pMsg.svcConfigRefList.no(i), dsContrPk);
            }
            // START 2018/03/23 K.Kojima [QC#24458,ADD]
            if (!cpoOrdNumList.contains(pMsg.svcConfigRefList.no(i).cpoOrdNum.getValue())) {
                cpoOrdNumList.add(pMsg.svcConfigRefList.no(i).cpoOrdNum.getValue());
            }
            // END 2018/03/23 K.Kojima [QC#24458,ADD]
        }
        // START 2018/03/23 K.Kojima [QC#24458,ADD]
        for (String cpoOrdNum : cpoOrdNumList) {
            updateBaseTotalForPhysDel(msgMap, cpoOrdNum, pMsg.slsDt.getValue());
        }
        // END 2018/03/23 K.Kojima [QC#24458,ADD]
    }

    private boolean checkSnglChkForPhysDel(S21ApiMessageMap msgMap) {
        NSZC115001PMsg pMsg = (NSZC115001PMsg) msgMap.getPmsg();
        boolean isErr = false;
        // START 2018/03/23 K.Kojima [QC#24458,ADD]
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            setErrStatus(msgMap, NSZM0002E);
            isErr = true;
        }
        // END 2018/03/23 K.Kojima [QC#24458,ADD]
        for (int i = 0; i < pMsg.svcConfigRefList.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(pMsg.svcConfigRefList.no(i).cpoOrdNum)) {
                ZYPEZDItemValueSetter.setValue(pMsg.svcConfigRefList.no(i).xxMsgId, NSZM0402E);
                isErr = true;
            }

            if (!ZYPCommonFunc.hasValue(pMsg.svcConfigRefList.no(i).cpoDtlLineNum)) {
                ZYPEZDItemValueSetter.setValue(pMsg.svcConfigRefList.no(i).xxMsgId, NSZM0403E);
                isErr = true;
            }

            if (!ZYPCommonFunc.hasValue(pMsg.svcConfigRefList.no(i).cpoDtlLineSubNum)) {
                ZYPEZDItemValueSetter.setValue(pMsg.svcConfigRefList.no(i).xxMsgId, NSZM0404E);
                isErr = true;
           }
        }

        return isErr;
    }

    private List<BigDecimal> physDelForDetail(NSZC115001_svcConfigRefListPMsg linePMsg) {
        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>();
        String cpoOrdNum = linePMsg.cpoOrdNum.getValue();
        String cpoDtlLineNum = linePMsg.cpoDtlLineNum.getValue();
        String cpoDtlLineSubNum = linePMsg.cpoDtlLineSubNum.getValue();
        // get DS_CONTR_DTL
        // START 2018/09/19 K.Kitachi [QC#28164, MOD]
//        DS_CONTR_DTLTMsgArray dsContrDtlArray = getDsContrDtlToOrd(cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
//        for (int i = 0; i < dsContrDtlArray.getValidCount(); i++) {
//            if (!DS_CONTR_DTL_STS.ORDER.equals(dsContrDtlArray.no(i).dsContrDtlStsCd.getValue())) {
//                ZYPEZDItemValueSetter.setValue(linePMsg.xxMsgId, NSZM1286E);
//                return null;
//            }
//
//            physDelForDsContrDtl(dsContrDtlArray.no(i), linePMsg);
//            if (!dsContrPkList.contains(dsContrDtlArray.no(i).dsContrPk.getValue())) {
//                dsContrPkList.add(dsContrDtlArray.no(i).dsContrPk.getValue());
//            }
//        }
        List<BigDecimal> dsContrDtlPkList = getDsContrDtlPkListForAcc(cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
        for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
            dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
            if (!DS_CONTR_DTL_STS.ORDER.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(linePMsg.xxMsgId, NSZM1286E);
                return null;
            }

            physDelForDsContrDtl(dsContrDtlTMsg, linePMsg);
        }
        dsContrDtlPkList = getDsContrDtlPkListExstConfig(cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
        for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
            dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
            if (!DS_CONTR_DTL_STS.ORDER.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(linePMsg.xxMsgId, NSZM1286E);
                return null;
            }

            physDelForDsContrDtl(dsContrDtlTMsg, linePMsg);
        }
        dsContrDtlPkList = getDsContrDtlPkListNotExstConfig(cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
        for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
            dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
            if (!DS_CONTR_DTL_STS.ORDER.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(linePMsg.xxMsgId, NSZM1286E);
                return null;
            }

            physDelForDsContrDtl(dsContrDtlTMsg, linePMsg);
            if (!dsContrPkList.contains(dsContrDtlTMsg.dsContrPk.getValue())) {
                dsContrPkList.add(dsContrDtlTMsg.dsContrPk.getValue());
            }
        }
        // END 2018/09/19 K.Kitachi [QC#28164, MOD]

        return dsContrPkList;
    }

    private void physDelForDsContrDtl(DS_CONTR_DTLTMsg dsContrDtlTMsg, NSZC115001_svcConfigRefListPMsg linePMsg) {
        // DS_CONTR_DTL
        S21ApiTBLAccessor.remove(dsContrDtlTMsg);

        // DS_CONTR_DTL_REC
        DS_CONTR_DTL_RECTMsg dsContrRec = new DS_CONTR_DTL_RECTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrRec.glblCmpyCd, dsContrDtlTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrRec.dsContrPk, dsContrDtlTMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(dsContrRec.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
        S21FastTBLAccessor.removeByPartialValue(dsContrRec, new String[]{"glblCmpyCd", "dsContrPk", "dsContrDtlPk"});

        DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrArray = getDsContrBllgMtr(dsContrDtlTMsg.dsContrDtlPk.getValue());
        CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrReln;
        CONTR_XS_COPYTMsg contrXsCopy;
        for (int i = 0; i < dsContrBllgMtrArray.getValidCount(); i++) {
            // DS_CONTR_BLLG_MTR
            S21ApiTBLAccessor.remove(dsContrBllgMtrArray.no(i));

            // CONTR_PHYS_BLLG_MTR_RELN
            contrPhysBllgMtrReln = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrReln.glblCmpyCd, dsContrBllgMtrArray.no(i).glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrReln.dsContrBllgMtrPk, dsContrBllgMtrArray.no(i).dsContrBllgMtrPk);
            S21FastTBLAccessor.removeByPartialValue(contrPhysBllgMtrReln, new String[]{"glblCmpyCd", "dsContrBllgMtrPk"});

            // CONTR_XS_COPY
            contrXsCopy = new CONTR_XS_COPYTMsg();
            ZYPEZDItemValueSetter.setValue(contrXsCopy.glblCmpyCd, dsContrBllgMtrArray.no(i).glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(contrXsCopy.dsContrBllgMtrPk, dsContrBllgMtrArray.no(i).dsContrBllgMtrPk);
            S21FastTBLAccessor.removeByPartialValue(contrXsCopy, new String[]{"glblCmpyCd", "dsContrBllgMtrPk"});
        }

        // DS_CONTR_ADDL_CHRG
        DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrg = new DS_CONTR_ADDL_CHRGTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrg.glblCmpyCd, dsContrDtlTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrg.dsContrPk, dsContrDtlTMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
        S21FastTBLAccessor.removeByPartialValue(dsContrAddlChrg, new String[]{"glblCmpyCd", "dsContrPk", "dsContrDtlPk"});

        // DS_CONTR_ADDL_CHRG_REC
        DS_CONTR_ADDL_CHRG_RECTMsg dsContrAddlChrgRec = new DS_CONTR_ADDL_CHRG_RECTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgRec.glblCmpyCd, dsContrDtlTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgRec.dsContrPk, dsContrDtlTMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(dsContrAddlChrgRec.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
        S21FastTBLAccessor.removeByPartialValue(dsContrAddlChrgRec, new String[]{"glblCmpyCd", "dsContrPk", "dsContrDtlPk"});

        // add start 2020/03/24 QC#54318
        // DS_CONTR_RNW_ESCL
        DS_CONTR_RNW_ESCLTMsg dsContrRnwEscl = new DS_CONTR_RNW_ESCLTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrRnwEscl.glblCmpyCd, dsContrDtlTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrRnwEscl.dsContrPk, dsContrDtlTMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(dsContrRnwEscl.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
        S21FastTBLAccessor.removeByPartialValue(dsContrRnwEscl, new String[] {"glblCmpyCd", "dsContrPk", "dsContrDtlPk"});
        // add end 2020/03/24 QC#54318

        // DS_CONTR_CR_CARD_PO
        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPo = new DS_CONTR_CR_CARD_POTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPo.glblCmpyCd, dsContrDtlTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPo.dsContrPk, dsContrDtlTMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPo.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
        S21FastTBLAccessor.removeByPartialValue(dsContrCrCardPo, new String[]{"glblCmpyCd", "dsContrPk", "dsContrDtlPk"});

        // SVC_TERM_COND
        SVC_TERM_CONDTMsg svcTermCond = new SVC_TERM_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(svcTermCond.glblCmpyCd, dsContrDtlTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcTermCond.dsContrPk, dsContrDtlTMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(svcTermCond.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
        S21FastTBLAccessor.removeByPartialValue(svcTermCond, new String[]{"glblCmpyCd", "dsContrPk", "dsContrDtlPk"});
    }

    private void physDelForHeader(NSZC115001_svcConfigRefListPMsg linePMsg, BigDecimal dsContrPk) {
        // exists Machine / Accessory Line
        if (BigDecimal.ZERO.compareTo(countDsContrDtlMachAcc(dsContrPk)) < 0) {
            return;
        }

        // Detail
        DS_CONTR_DTLTMsgArray dsContrDtlArray = getDsContrDtlToContrPk(dsContrPk);
        for (int i = 0; i < dsContrDtlArray.getValidCount(); i++) {
            physDelForDsContrDtl(dsContrDtlArray.no(i), linePMsg);
        }

        // DS_CONTR Status Check
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, dsContrPk);
        dsContrTMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(dsContrTMsg);
        if (dsContrTMsg == null || !DS_CONTR_STS.ORDER.equals(dsContrTMsg.dsContrStsCd.getValue())) {
            return;
        }

        // DS_CONTR
        S21ApiTBLAccessor.remove(dsContrTMsg);

        // DS_CONTR_REC
        DS_CONTR_RECTMsg dsContrRec = new DS_CONTR_RECTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrRec.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrRec.dsContrPk, dsContrPk);
        S21FastTBLAccessor.removeByPartialValue(dsContrRec, new String[]{"glblCmpyCd", "dsContrPk"});

        // SVC_MEMO
        SVC_MEMOTMsg svcMemo = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(svcMemo.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMemo.dsContrPk, dsContrPk);
        S21FastTBLAccessor.removeByPartialValue(svcMemo, new String[]{"glblCmpyCd", "dsContrPk"});

        // SVC_MEMO_REC
        SVC_MEMO_RECTMsg svcMemoRec = new SVC_MEMO_RECTMsg();
        ZYPEZDItemValueSetter.setValue(svcMemoRec.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMemoRec.dsContrPk, dsContrPk);
        S21FastTBLAccessor.removeByPartialValue(svcMemoRec, new String[]{"glblCmpyCd", "dsContrPk"});

        // DS_CONTR_RNW_ESCL
        DS_CONTR_RNW_ESCLTMsg dsContrRnwEscl = new DS_CONTR_RNW_ESCLTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrRnwEscl.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrRnwEscl.dsContrPk, dsContrPk);
        S21FastTBLAccessor.removeByPartialValue(dsContrRnwEscl, new String[]{"glblCmpyCd", "dsContrPk"});

        // DS_CONTR_TAX_DTL 
        DS_CONTR_TAX_DTLTMsg dsContrTaxDtl = new DS_CONTR_TAX_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrTaxDtl.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTaxDtl.dsContrPk, dsContrPk);
        S21FastTBLAccessor.removeByPartialValue(dsContrTaxDtl, new String[]{"glblCmpyCd", "dsContrPk"});

        // DS_CONTR_CR_CARD_PO
        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPo = new DS_CONTR_CR_CARD_POTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPo.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrCrCardPo.dsContrPk, dsContrPk);
        S21FastTBLAccessor.removeByPartialValue(dsContrCrCardPo, new String[]{"glblCmpyCd", "dsContrPk"});

        // SVC_TERM_COND
        SVC_TERM_CONDTMsg svcTermCond = new SVC_TERM_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(svcTermCond.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcTermCond.dsContrPk, dsContrPk);
        S21FastTBLAccessor.removeByPartialValue(svcTermCond, new String[]{"glblCmpyCd", "dsContrPk"});
    }

    private DS_CONTR_DTLTMsgArray getDsContrDtlToContrPk(BigDecimal dsContrPk) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrPk01", dsContrPk);

        return (DS_CONTR_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    private DS_CONTR_DTLTMsgArray getDsContrDtlToOrd(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        inMsg.setSQLID("203");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        inMsg.setConditionValue("cpoDtlLineNum01", cpoDtlLineNum);
        inMsg.setConditionValue("cpoDtlLineSubNum01", cpoDtlLineSubNum);

        return (DS_CONTR_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    private DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtr(BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);

        return (DS_CONTR_BLLG_MTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    private BigDecimal countDsContrDtlMachAcc(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        List<String> dsContrDtlTpList = new ArrayList<String>();
        dsContrDtlTpList.add(DS_CONTR_DTL_TP.BASE_AND_USAGE);
        dsContrDtlTpList.add(DS_CONTR_DTL_TP.BASE_ONLY);
        dsContrDtlTpList.add(DS_CONTR_DTL_TP.USAGE_ONLY);
        dsContrDtlTpList.add(DS_CONTR_DTL_TP.ACCESSORIES);
        param.put("dsContrDtlTpList", dsContrDtlTpList);

        return (BigDecimal) this.ssmBatchClient.queryObject("countDsContrDtlMachAcc", param);
    }

    // add start 2017/06/19 CSA Defect#19036
    private boolean clearDsContrCpoOrdNum(BigDecimal dsContrPk) {
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, dsContrPk);
        dsContrTMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrTMsg);
        if (dsContrTMsg != null && ZYPCommonFunc.hasValue(dsContrTMsg.cpoOrdNum)) {
            dsContrTMsg.cpoOrdNum.clear();
            S21FastTBLAccessor.update(dsContrTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }
    // add end 2017/06/19 CSA Defect#19036

    // START 2017/06/21 K.Kitachi [QC#18068, ADD]
    private DS_COND_CONSTTMsgArray getDsCondConst(String dsCondConstGrpId, String dsCondConstCd) {
        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsCondConstGrpId01", dsCondConstGrpId);
        inMsg.setConditionValue("dsCondConstCd01", dsCondConstCd);

        return (DS_COND_CONSTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    private CONTR_UPLFT_TPTMsgArray getContrUplftTp(String uplftBaseFlg, String uplftUsgFlg) {
        CONTR_UPLFT_TPTMsg inTMsg = new CONTR_UPLFT_TPTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inTMsg.setConditionValue("uplftBaseFlg01", uplftBaseFlg);
        inTMsg.setConditionValue("uplftUsgFlg01", uplftUsgFlg);

        return (CONTR_UPLFT_TPTMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);
    }
    // END 2017/06/21 K.Kitachi [QC#18068, ADD]

    // START 2017/06/23 K.Kitachi [QC#18068, ADD]
    private void setUplftFlg(NSZC115001_svcDtlListPMsg svcDtlPMsg, DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg) {
        // set default
        ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.firstYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.scdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.thirdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.frthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.fifthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.sixthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.svnthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.eighthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.ninthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.tenthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);

        String prcSvcPlnTpCd = svcDtlPMsg.prcSvcPlnTpCd.getValue();
        if (!ZYPCommonFunc.hasValue(prcSvcPlnTpCd)) {
            return;
        }
        DS_COND_CONSTTMsgArray dsCondConstTMsgArray = getDsCondConst(NSZC1150_SHELL_UPLFT, prcSvcPlnTpCd);
        if (dsCondConstTMsgArray.getValidCount() == 0) {
            return;
        }
        if (!ZYPConstant.FLG_ON_Y.equals(dsCondConstTMsgArray.no(0).dsCondConstValTxt_01.getValue()) && !ZYPConstant.FLG_ON_Y.equals(dsCondConstTMsgArray.no(0).dsCondConstValTxt_02.getValue())) {
            return;
        }
        BigDecimal defContrUplftTermAot = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_DEF_CONTR_UPLFT_TERM_AOT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(defContrUplftTermAot)) {
            defContrUplftTermAot = DEF_CONTR_UPLFT_TERM_AOT;
        }
        int termAot = defContrUplftTermAot.intValue();
        int durCnt = calcDuration(svcDtlPMsg);
        if (termAot > durCnt) {
            termAot = durCnt;
        }
        if (termAot > 10) {
            termAot = 10;
        }
        switch (termAot) {
            case 10:
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.tenthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 9:
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.ninthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 8:
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.eighthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 7:
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.svnthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 6:
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.sixthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 5:
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.fifthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 4:
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.frthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 3:
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.thirdYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 2:
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.scdYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 1:
                ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.firstYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            default:
        }
    }

    private int calcDuration(NSZC115001_svcDtlListPMsg svcDtlPMsg) {
        BigDecimal fromPerMthNum = svcDtlPMsg.fromPerMthNum.getValue();
        BigDecimal toPerMthNum = svcDtlPMsg.toPerMthNum.getValue();
        if (!ZYPCommonFunc.hasValue(fromPerMthNum) || !ZYPCommonFunc.hasValue(toPerMthNum)) {
            return 0;
        }
        int termMth = toPerMthNum.intValue() - fromPerMthNum.intValue() + 1;
        int durCnt = termMth / 12;
        if (termMth % 12 == 0) {
            durCnt--;
        }
        return durCnt;
    }
    // END 2017/06/23 K.Kitachi [QC#18068, ADD]
    // add start 2017/07/21 QC#19962
    private Map<String, Object> getContrLeaseCmpy(String cpoOrdNum, String billByTpCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("billByTpCd", billByTpCd);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getContrLeaseCmpy", param);
    }
    // add end   2017/07/21 QC#19962

    // QC#29248 del
//    // START 2017/08/18 [QC#20651, ADD}
//    private boolean isMeteredModel(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg) {
//
//        if (ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.addAsryFlg.getValue())) {
//            return false;
//        }
//
//        BigDecimal mtrGrpPk = getMtrGrpPk(inPrmPMsg, svcConfigRefPMsg);
//
//        return ZYPCommonFunc.hasValue(mtrGrpPk);
//    }
//
//    private BigDecimal getMtrGrpPk(NSZC115001PMsg inPrmPMsg, NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg) {
//        Map<String, Object> inparam = new HashMap<String, Object>();
//        inparam.put("glblCmpyCd", inPrmPMsg.glblCmpyCd.getValue());
//        inparam.put("cpoOrdNum", svcConfigRefPMsg.cpoOrdNum.getValue());
//        inparam.put("cpoDtlLineNum", svcConfigRefPMsg.cpoDtlLineNum.getValue());
//        inparam.put("cpoDtlLineSubNum", svcConfigRefPMsg.cpoDtlLineSubNum.getValue());
//
//        BigDecimal mtrGrpPk = (BigDecimal) this.ssmBatchClient.queryObject("getMtrGrpPk", inparam);
//
//        return mtrGrpPk;
//    }
//    // END   2017/08/18 [QC#20651, ADD]

    // START 2017/08/24 [QC#20665, ADD]
    private int countupEffectiveConfigRef(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg) {
        NSZC115001_svcConfigRefListPMsgArray svcConfigRefPMsgList = inPrmPMsg.svcConfigRefList;
        String cpoOrdNum = inPrmPMsg.refCpoOrdNum.getValue();
        BigDecimal shellLineNum = svcDtlPMsg.shellLineNum.getValue();
        int cntConfigRef = 0;

        for (int i = 0; i < svcConfigRefPMsgList.getValidCount(); i++) {
            NSZC115001_svcConfigRefListPMsg svcConfigRefPMsg = svcConfigRefPMsgList.no(i);

            if (RQST_TP_DEL.equals(svcConfigRefPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            if (!cpoOrdNum.equals(svcConfigRefPMsg.cpoOrdNum.getValue())) {
                continue;
            }

            if (shellLineNum.compareTo(svcConfigRefPMsg.shellLineNum.getValue()) != 0) {
                continue;
            }

            if (!ZYPCommonFunc.hasValue(svcConfigRefPMsg.dsOrdPosnNum.getValue())) {
                continue;
            }

            cntConfigRef++;
        }

        return cntConfigRef;
    }

    private void modDsContrTMsgForAddContr(S21ApiMessageMap msgMap, List<DS_CONTRTMsg> dsContrTMsgNewList, List<DS_CONTRTMsg> dsContrTMsgModList, List<DS_CONTRTMsg> dsContrTMsgDelList, NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, DS_CONTRTMsg dsContrTMsg) {

        int cntConfigRef = countupEffectiveConfigRef(inPrmPMsg, svcDtlPMsg);
        if (cntConfigRef > 0) {
            DS_CONTRTMsg dsContrTMsgAddContrHdr = getDsContrAddContrHeaderOnly(inPrmPMsg.glblCmpyCd.getValue(),
                    dsContrTMsg.dsContrNum.getValue(), inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.shellLineNum.getValue());
            if (dsContrTMsgAddContrHdr != null) {
                dsContrTMsgDelList.add(dsContrTMsgAddContrHdr);
            }
            // START 2017/08/31 [QC#20665-1, ADD]
            if (!ZYPCommonFunc.hasValue(dsContrTMsg.cpoOrdNum)) {
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.prcSvcContrTpCd, svcDtlPMsg.prcSvcContrTpCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.prcSvcPlnTpCd, svcDtlPMsg.prcSvcPlnTpCd);
                ZYPEZDItemValueSetter.setValue(dsContrTMsg.billByTpCd, svcDtlPMsg.billByTpCd);

                dsContrTMsgModList.add(dsContrTMsg);
            }
            // END   2017/08/31 [QC#20665-1, ADD]
            return;
        }

        DS_CONTRTMsg dsContrTMsgAddContrHdr = getDsContrAddContrHeaderOnly(inPrmPMsg.glblCmpyCd.getValue(),
                dsContrTMsg.dsContrNum.getValue(), inPrmPMsg.refCpoOrdNum.getValue(), svcDtlPMsg.shellLineNum.getValue());

        if (RQST_TP_NEW.equals(svcDtlPMsg.xxRqstTpCd.getValue()) || dsContrTMsgAddContrHdr == null) {

            // create new DS_CONTR record.
            dsContrTMsgAddContrHdr = new DS_CONTRTMsg();
            BigDecimal dsContrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_SQ);
            svcDtlPMsg.dsContrPk.setValue(dsContrPk);

            // DS_CONTR
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.dsContrPk, dsContrPk);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.dsContrSqNum, "00");
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.dsContrTpCd, DS_CONTR_TP.SERVICE);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.dsContrStsCd, DS_CONTR_STS.ORDER);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.altPayerCustCd, svcDtlPMsg.soldToCustLocCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.dsContrCatgCd, svcDtlPMsg.dsContrCatgCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.pmtTermCashDiscCd, getPmtTermCashDiscCd(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.svcContrRefCmntTxt, inPrmPMsg.refCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.bllgApvlReqFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.ccyCd, getCcyCd());
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.preInvReqFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.rnwCpltFlg, ZYPConstant.FLG_OFF_N);
            // START 2022/03/22 [QC#59683, ADD]
            String dsInvTgtrTpCd = getDsInvTgtrTpCd(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue(), svcDtlPMsg.dsContrCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.dsInvTgtrTpCd, dsInvTgtrTpCd);
            // END   2022/03/22 [QC#59683, ADD]
            // mod start 2017/09/01 QC#20882
//            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.invSeptBaseUsgFlg, getInvSeptBaseUsgFlg(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue()));
            // START 2022/03/22 [QC#59683, MOD]
//            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.invSeptBaseUsgFlg, getInvSeptBaseUsgFlg(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue(), svcDtlPMsg.dsContrCatgCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.invSeptBaseUsgFlg, getInvSeptBaseUsgFlgByTgtrTp(dsInvTgtrTpCd));
            // END   2022/03/22 [QC#59683, MOD]
            // mod end 2017/09/01 QC#20882
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.dsContrCratDt, inPrmPMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.dsContrCratPsnCd, getDsContrCratPsnCd(EZDDBCICarrier.getUserID()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.leaseCmpyCd, getLeaseCmpyCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.dsAcctNum, svcDtlPMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.tocCd, getTocCd(inPrmPMsg));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.baseChrgToLeaseCmpyFlg, getBaseChrgToLeaseCmpyFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.usgChrgToLeaseCmpyFlg, getUsgChrgToLeaseCmpyFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.mtrEstTpCd, getMtrEstTpCd(inPrmPMsg));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.prcAllocByMachQtyFlg, getPrcAllocByMachQtyFlg(inPrmPMsg, svcDtlPMsg.dsContrCatgCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.svcContrBrCd, getSvcContrBrCd(inPrmPMsg.refCpoOrdNum.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.dsContrClsCd, getDsContrClsCd(inPrmPMsg));
            // START 2018/03/13 K.Kojima [QC#24263,DEL]
            // ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.ctacPsnPk, getCtacPsnPk(inPrmPMsg.refCpoOrdNum.getValue()));
            // END 2018/03/13 K.Kojima [QC#24263,DEL]
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.svcLineBizCd, getSvcLineBizCd(inPrmPMsg));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.dsContrSrcTpCd, DS_CONTR_SRC_TP.CPO);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.dsContrEdiCd, getDsContrEdiCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.mtrHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.contrHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.bllgHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.svcPgmMdseCd, svcDtlPMsg.svcPgmMdseCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.contrAdminPsnCd, getContrAdminPsnCd(inPrmPMsg.refCpoOrdNum.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.baseBllgCycleCd, svcDtlPMsg.baseBllgCycleCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.mtrBllgCycleCd, svcDtlPMsg.usgBllgCycleCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.prcSvcContrTpCd, svcDtlPMsg.prcSvcContrTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.prcSvcPlnTpCd, svcDtlPMsg.prcSvcPlnTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.billByTpCd, svcDtlPMsg.billByTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.cpoSvcAgmtVerNum, svcDtlPMsg.cpoSvcAgmtVerNum);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.manContrOvrdFlg, getFlgValue(svcDtlPMsg.manContrOvrdFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.svcPrcCatgCd, SVC_PRC_CATG.MAIN_UNIT_BASE_CHARGE);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.crRebilCd, svcDtlPMsg.crRebilCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.applyEquipBillToFlg, getFlgValue(svcDtlPMsg.applyEquipBillToFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.fixTermInMthAot, svcDtlPMsg.fixTermInMthAot);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.shellLineNum, svcDtlPMsg.shellLineNum);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.fromPerMthNum, svcDtlPMsg.fromPerMthNum);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.toPerMthNum, svcDtlPMsg.toPerMthNum);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.cpoOrdNum, inPrmPMsg.refCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.dsContrNum, dsContrTMsg.dsContrNum);

            dsContrTMsgNewList.add(dsContrTMsgAddContrHdr);

        } else {
            if (isSameValueDsContrTMsg(inPrmPMsg, svcDtlPMsg, dsContrTMsg)
                    && isSameValueDsContrTMsgForAddContr(inPrmPMsg, svcDtlPMsg, dsContrTMsg, dsContrTMsgAddContrHdr)) {
                return;
            }

            // update DS_CONTR record.
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.altPayerCustCd, svcDtlPMsg.soldToCustLocCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrCatgCd, svcDtlPMsg.dsContrCatgCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.pmtTermCashDiscCd, getPmtTermCashDiscCd(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcContrRefCmntTxt, inPrmPMsg.refCpoOrdNum);
            // START 2022/03/22 [QC#59683, ADD]
            String dsInvTgtrTpCd = getDsInvTgtrTpCd(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue(), svcDtlPMsg.dsContrCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsInvTgtrTpCd, dsInvTgtrTpCd);
            // END   2022/03/22 [QC#59683, ADD]
            // mod start 2017/09/01 QC#20882
//            ZYPEZDItemValueSetter.setValue(dsContrTMsg.invSeptBaseUsgFlg, getInvSeptBaseUsgFlg(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue()));
            // START 2022/03/22 [QC#59683, MOD]
//            ZYPEZDItemValueSetter.setValue(dsContrTMsg.invSeptBaseUsgFlg, getInvSeptBaseUsgFlg(inPrmPMsg, svcDtlPMsg.sellToCustCd.getValue(), svcDtlPMsg.dsContrCatgCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.invSeptBaseUsgFlg, getInvSeptBaseUsgFlgByTgtrTp(dsInvTgtrTpCd));
            // END   2022/03/22 [QC#59683, MOD]
            // mod end 2017/09/01 QC#20882
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrLastUpdDt, inPrmPMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrLastUpdPsnCd, getDsContrCratPsnCd(EZDDBCICarrier.getUserID()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.leaseCmpyCd, getLeaseCmpyCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsAcctNum, svcDtlPMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.tocCd, getTocCd(inPrmPMsg));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.baseChrgToLeaseCmpyFlg, getBaseChrgToLeaseCmpyFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.usgChrgToLeaseCmpyFlg, getUsgChrgToLeaseCmpyFlg(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.mtrEstTpCd, getMtrEstTpCd(inPrmPMsg));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.prcAllocByMachQtyFlg, getPrcAllocByMachQtyFlg(inPrmPMsg, svcDtlPMsg.dsContrCatgCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcContrBrCd, getSvcContrBrCd(inPrmPMsg.refCpoOrdNum.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrClsCd, getDsContrClsCd(inPrmPMsg));
            // START 2018/03/13 K.Kojima [QC#24263,DEL]
            // ZYPEZDItemValueSetter.setValue(dsContrTMsg.ctacPsnPk, getCtacPsnPk(inPrmPMsg.refCpoOrdNum.getValue()));
            // END 2018/03/13 K.Kojima [QC#24263,DEL]
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcLineBizCd, getSvcLineBizCd(inPrmPMsg));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrEdiCd, getDsContrEdiCd(inPrmPMsg, svcDtlPMsg.billByTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.svcPgmMdseCd, svcDtlPMsg.svcPgmMdseCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.contrAdminPsnCd, getContrAdminPsnCd(inPrmPMsg.refCpoOrdNum.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.baseBllgCycleCd, svcDtlPMsg.baseBllgCycleCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.mtrBllgCycleCd, svcDtlPMsg.usgBllgCycleCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.prcSvcContrTpCd, svcDtlPMsg.prcSvcContrTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.prcSvcPlnTpCd, svcDtlPMsg.prcSvcPlnTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.billByTpCd, svcDtlPMsg.billByTpCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.cpoSvcAgmtVerNum, svcDtlPMsg.cpoSvcAgmtVerNum);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.manContrOvrdFlg, getFlgValue(svcDtlPMsg.manContrOvrdFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.crRebilCd, svcDtlPMsg.crRebilCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.applyEquipBillToFlg, getFlgValue(svcDtlPMsg.applyEquipBillToFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.fixTermInMthAot, svcDtlPMsg.fixTermInMthAot);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.shellLineNum, svcDtlPMsg.shellLineNum);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.billWithEquipFlg, getFlgValue(svcDtlPMsg.billWithEquipFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.fromPerMthNum, svcDtlPMsg.fromPerMthNum);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.toPerMthNum, svcDtlPMsg.toPerMthNum);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.cpoOrdNum, inPrmPMsg.refCpoOrdNum);

            ZYPEZDItemValueSetter.setValue(dsContrTMsgAddContrHdr.dsContrNum, dsContrTMsg.dsContrNum);

            dsContrTMsgModList.add(dsContrTMsgAddContrHdr);
        }
    }

    private DS_CONTRTMsg getDsContrAddContrHeaderOnly(String glblCmpyCd, String dsContrNum, String cpoOrdNum, BigDecimal shellLineNum) {
        Map<String, Object> inparam = new HashMap<String, Object>();
        inparam.put("glblCmpyCd", glblCmpyCd);
        inparam.put("dsContrNum", dsContrNum);
        inparam.put("cpoOrdNum", cpoOrdNum);
        inparam.put("shellLineNum", shellLineNum);
        inparam.put("dsContrStsCd", DS_CONTR_STS.ORDER);

        BigDecimal dsContrPk = (BigDecimal) this.ssmBatchClient.queryObject("getDsContrPkAddContrHeaderonly", inparam);
        if (dsContrPk != null) {
            DS_CONTRTMsg inTMsg = new DS_CONTRTMsg();
            inTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            inTMsg.dsContrPk.setValue(dsContrPk);
            return (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);
        }
        return null;
    }

    private boolean isSameValueDsContrTMsgForAddContr(NSZC115001PMsg inPrmPMsg, NSZC115001_svcDtlListPMsg svcDtlPMsg, DS_CONTRTMsg dsContrTMsg, DS_CONTRTMsg dsContrTMsgAddContrHdr) {

        if (!S21StringUtil.isEquals(dsContrTMsg.dsContrNum.getValue(), dsContrTMsgAddContrHdr.dsContrNum.getValue())) {
            return false;
        }

        return false;
    }
    // END   2017/08/24 [QC#20665, ADD]

    // START 2017/09/06 M.Naito [QC#18724, ADD]
    private void setUplftFlgFromPlnTp(NSZC115001_svcDtlListPMsg svcDtlPMsg, DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg) {

        if (!ZYPCommonFunc.hasValue(svcDtlPMsg.prcSvcPlnTpCd)) {
            return;
        }

        PRC_SVC_PLN_TPTMsg inTMsg = new PRC_SVC_PLN_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.prcSvcPlnTpCd, svcDtlPMsg.prcSvcPlnTpCd);
        PRC_SVC_PLN_TPTMsg outTMsg = (PRC_SVC_PLN_TPTMsg) S21FastTBLAccessor.findByKey(inTMsg);

        int contrFixedYearsAot = outTMsg.contrFixedYearsAot.getValue().intValue();

        if (contrFixedYearsAot > 1) {
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.firstYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 2) {
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.scdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 3) {
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.thirdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 4) {
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.frthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 5) {
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.fifthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 6) {
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.sixthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 7) {
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.svnthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 8) {
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.eighthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 9) {
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.ninthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
        if (contrFixedYearsAot > 10) {
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTMsg.tenthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        }
    }
    // END 2017/09/06 M.Naito [QC#18724, ADD]

    // START 2017/12/11 K.Kojima [QC#19955,ADD]
    private void changeBaseBLlgCycle(List<BigDecimal> changeBaseBllgCycleDsContrPkList, S21ApiMessageMap msgMap) {
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgTMsgList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();

        for (BigDecimal dsContrPk : changeBaseBllgCycleDsContrPkList) {
            List<Map<String, Object>> dsContrDtlList = getChangeBllgCycleDetailPk(dsContrPk);
            if (dsContrDtlList != null) {
                for (Map<String, Object> map : dsContrDtlList) {
                    DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, (BigDecimal) map.get("DS_CONTR_DTL_PK"));
                    dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
                    if (dsContrDtlTMsg == null) {
                        setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
                        return;
                    }
                    ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgCycleCd, (String) map.get("BASE_BLLG_CYCLE_CD"));
                    dsContrDtlTMsgList.add(dsContrDtlTMsg);
                }
            }
            List<Map<String, Object>> dsContrAddlChrgList = getChangeBllgCycleAddlChrgPk(dsContrPk);
            if (dsContrAddlChrgList != null) {
                for (Map<String, Object> map : dsContrAddlChrgList) {
                    DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg = new DS_CONTR_ADDL_CHRGTMsg();
                    ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.dsContrAddlChrgPk, (BigDecimal) map.get("DS_CONTR_ADDL_CHRG_PK"));
                    dsContrAddlChrgTMsg = (DS_CONTR_ADDL_CHRGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrAddlChrgTMsg);
                    if (dsContrAddlChrgTMsg == null) {
                        setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
                        return;
                    }
                    ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTMsg.bllgCycleCd, (String) map.get("BASE_BLLG_CYCLE_CD"));
                    dsContrAddlChrgTMsgList.add(dsContrAddlChrgTMsg);
                }
            }
        }
        if (dsContrDtlTMsgList.size() > 0) {
            int result = S21FastTBLAccessor.update(dsContrDtlTMsgList.toArray(new DS_CONTR_DTLTMsg[dsContrDtlTMsgList.size()]));
            if (result != dsContrDtlTMsgList.size()) {
                setErrStatus(msgMap, NSZM1228E); //mod u.kim 2018/03/06 QC#24056
            }
        }
        if (dsContrAddlChrgTMsgList.size() > 0) {
            int result = S21FastTBLAccessor.update(dsContrAddlChrgTMsgList.toArray(new DS_CONTR_ADDL_CHRGTMsg[dsContrAddlChrgTMsgList.size()]));
            if (result != dsContrAddlChrgTMsgList.size()) {
                setErrStatus(msgMap, NSZM1238E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private void changeMtrBLlgCycle(List<BigDecimal> changeMtrBllgCycleDsContrPkList, S21ApiMessageMap msgMap) {
        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();

        for (BigDecimal dsContrPk : changeMtrBllgCycleDsContrPkList) {
            List<Map<String, Object>> dsContrBllgMtrList = getChangeBllgCycleBllgMtrPk(dsContrPk);
            if (dsContrBllgMtrList != null) {
                for (Map<String, Object> map : dsContrBllgMtrList) {
                    DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = new DS_CONTR_BLLG_MTRTMsg();
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrBllgMtrPk, (BigDecimal) map.get("DS_CONTR_BLLG_MTR_PK"));
                    dsContrBllgMtrTMsg = (DS_CONTR_BLLG_MTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrBllgMtrTMsg);
                    if (dsContrBllgMtrTMsg == null) {
                        setErrStatus(msgMap, NSZM1230E); //mod u.kim 2018/03/06 QC#24056
                        return;
                    }
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMtrBllgCycleCd, (String) map.get("MTR_BLLG_CYCLE_CD"));
                    dsContrBllgMtrTMsgList.add(dsContrBllgMtrTMsg);
                }
            }
        }
        if (dsContrBllgMtrTMsgList.size() > 0) {
            int result = S21FastTBLAccessor.update(dsContrBllgMtrTMsgList.toArray(new DS_CONTR_BLLG_MTRTMsg[dsContrBllgMtrTMsgList.size()]));
            if (result != dsContrBllgMtrTMsgList.size()) {
                setErrStatus(msgMap, NSZM1231E); //mod u.kim 2018/03/06 QC#24056
            }
        }
    }

    private List<Map<String, Object>> getChangeBllgCycleDetailPk(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getChangeBllgCycleDetailPk", param);
    }

    private List<Map<String, Object>> getChangeBllgCycleBllgMtrPk(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getChangeBllgCycleBllgMtrPk", param);
    }

    private List<Map<String, Object>> getChangeBllgCycleAddlChrgPk(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getChangeBllgCycleAddlChrgPk", param);
    }
    // END 2017/12/11 K.Kojima [QC#19955,ADD]

    // START 2018/01/04 M.Kidokoro [QC#23167,ADD]
    private void updateBllgMtrBillToCust(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        if (!PROC_MODE_MOD.equals(inPrmPMsg.xxProcMd.getValue())) {
            return;
        }
        for (int detailCount = 0; detailCount < inPrmPMsg.svcDtlList.getValidCount(); detailCount++) {
            NSZC115001_svcDtlListPMsg svcDtlPMsg = inPrmPMsg.svcDtlList.no(detailCount);
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk_AD)) {
                continue;
            }
            // START 2018/02/20 K.Kojima [QC#24234,ADD]
            if (!this.updateBillToCustCd.containsKey(svcDtlPMsg.dsContrPk.getValue())) {
                // END 2018/02/20 K.Kojima [QC#24234,ADD]
                String billByTpCdB = this.beforeBillByTPMap.get(svcDtlPMsg.dsContrPk.getValue());
                String billByTpCdA = svcDtlPMsg.billByTpCd.getValue();
                if (!ZYPCommonFunc.hasValue(billByTpCdA)) {
                    continue;
                }
                if (!ZYPCommonFunc.hasValue(billByTpCdB)) {
                    continue;
                }
                if (billByTpCdA.equals(billByTpCdB)) {
                    continue;
                }
                // START 2018/02/20 K.Kojima [QC#24234,ADD]
            }
            // END 2018/02/20 K.Kojima [QC#24234,ADD]
            List<BigDecimal> updateBllgMtrPkList = getUpdateBllgMtrPkList(svcDtlPMsg.dsContrPk.getValue());
            for (int mtrCount = 0; mtrCount < updateBllgMtrPkList.size(); mtrCount++) {
                DS_CONTR_BLLG_MTRTMsg tMsg = new DS_CONTR_BLLG_MTRTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrPk, updateBllgMtrPkList.get(mtrCount));
                tMsg = (DS_CONTR_BLLG_MTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
                if (tMsg == null) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrBillToCustCd, getBllgMtrBillToCustCd(inPrmPMsg, svcDtlPMsg, null, null));
                S21ApiTBLAccessor.update(tMsg);
            }
        }
    }

    private List<BigDecimal> getUpdateBllgMtrPkList(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getUpdateBllgMtrPkList", param);
    }
    // END 2018/01/04 M.Kidokoro [QC#23167,ADD]

    // START 2018/02/20 K.Kojima [QC#24234,ADD]
    private void updateDsContrDtlForAsry(S21ApiMessageMap msgMap) {
        NSZC115001PMsg inPrmPMsg = (NSZC115001PMsg) msgMap.getPmsg();
        if (!PROC_MODE_MOD.equals(inPrmPMsg.xxProcMd.getValue())) {
            return;
        }
        for (int detailCount = 0; detailCount < inPrmPMsg.svcDtlList.getValidCount(); detailCount++) {
            NSZC115001_svcDtlListPMsg svcDtlPMsg = inPrmPMsg.svcDtlList.no(detailCount);
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk_AD)) {
                continue;
            }
            if (!this.updateBillToCustCd.containsKey(svcDtlPMsg.dsContrPk.getValue())) {
                String billByTpCdB = this.beforeBillByTPMap.get(svcDtlPMsg.dsContrPk.getValue());
                String billByTpCdA = svcDtlPMsg.billByTpCd.getValue();
                if (!ZYPCommonFunc.hasValue(billByTpCdA)) {
                    continue;
                }
                if (!ZYPCommonFunc.hasValue(billByTpCdB)) {
                    continue;
                }
                if (billByTpCdA.equals(billByTpCdB)) {
                    continue;
                }
            }
            List<Map<String, Object>> updateDsContrDtlPkForAccesoryList = getUpdateDsContrDtlPkForAccesory(svcDtlPMsg.dsContrPk.getValue());
            for (int dtlPkCount = 0; dtlPkCount < updateDsContrDtlPkForAccesoryList.size(); dtlPkCount++) {
                DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, (BigDecimal) updateDsContrDtlPkForAccesoryList.get(dtlPkCount).get("DS_CONTR_DTL_PK"));
                tMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
                if (tMsg == null) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(tMsg.baseBillToCustCd, (String) updateDsContrDtlPkForAccesoryList.get(dtlPkCount).get("BASE_BILL_TO_CUST_CD"));
                S21ApiTBLAccessor.update(tMsg);
            }
        }
    }

    private List<Map<String, Object>> getUpdateDsContrDtlPkForAccesory(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlTpAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getUpdateDsContrDtlPkForAccesory", param);
    }
    // END 2018/02/20 K.Kojima [QC#24234,ADD]

    // START 2018/03/06 U.Kim [QC#24056, ADD]
    private void setErrStatus(S21ApiMessageMap msgMap, String msgId){
        msgMap.addXxMsgId(msgId);
        if(msgId.endsWith("E")){
            existsErrMsgFlg = true;
        }
    }
    // END 2018/03/06 U.Kim [QC#24056, ADD]
    // 2018/05/07 QC#22482 Add Start
    private boolean isManOvrd(NSZC115001_svcDtlListPMsg svcDtlPMsg) {

        return ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.manContrOvrdFlg.getValue());
    }

    private boolean isManOvrd(NSZC115001PMsg inPrmPMsg, BigDecimal shellLineNum) {

        for (int j = 0; j < inPrmPMsg.svcDtlList.getValidCount(); j++) {
            NSZC115001_svcDtlListPMsg svcDtlPMsg = inPrmPMsg.svcDtlList.no(j);
            if (shellLineNum.compareTo(svcDtlPMsg.shellLineNum.getValue()) != 0) {
                continue;
            }
            if (isManOvrd(svcDtlPMsg)) {
                return true;
            }
        }
        return false;
    }
    // 2018/05/07 QC#22482 Add End
    // START 2018/05/24 [QC#26062, ADD]
    boolean isDoNotRenewUpLift(String svcPgmMdseCd) {
        if (!ZYPCommonFunc.hasValue(svcPgmMdseCd)) {
            return true;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcPgmMdseCd", svcPgmMdseCd);
        Map<String, Object> svcPgmTpCdMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getSvcPgmTpCd", param);
        if (svcPgmTpCdMap == null || svcPgmTpCdMap.isEmpty()) {
            return true;
        }
        String wtyTpcd = (String)svcPgmTpCdMap.get("SVC_WTY_TP_CD");
        if (ZYPCommonFunc.hasValue(wtyTpcd)) {
            return true;
        }
        if (this.doNotRnwUpliftSvcPgmTp == null) {
            return true;
        }
        if (this.doNotRnwUpliftSvcPgmTp.contains((String)svcPgmTpCdMap.get("SVC_PGM_TP_CD"))) {
            return true;
        }
        return false;
    }
    // END   2018/05/24 [QC#26062, ADD]

    // add start 2018/08/22 QC#23920
    private boolean checkTierCntForAgg(NSZC115001_svcDtlListPMsg svcDtlPMsg, NSZC115001PMsg inPrmPMsg) {
        if (inPrmPMsg.svcUsgPrcList.getValidCount() == 0) {
            return true;
        }

        Map<String, Integer> aggTierCntMap = new HashMap<String, Integer>();
        Map<String, Integer> prmTierCntMap = new HashMap<String, Integer>();

        List<Map<String, Object>> contrXsCopyList = getContrXsCopyForAgg(svcDtlPMsg.dsContrPk_AD.getValue());
        for (int i = 0; i < contrXsCopyList.size(); i++) {
            String key = (String) contrXsCopyList.get(i).get("BLLG_MTR_LB_CD");
            BigDecimal count = (BigDecimal) contrXsCopyList.get(i).get("CNT");
            aggTierCntMap.put(key, Integer.valueOf(count.intValue()));
        }

        NSZC115001_svcUsgPrcListPMsg svcUsgPrcPMsg = null;
        for (int i = 0; i < inPrmPMsg.svcUsgPrcList.getValidCount(); i++) {
            svcUsgPrcPMsg = inPrmPMsg.svcUsgPrcList.no(i);
            if (RQST_TP_DEL.equals(svcUsgPrcPMsg.xxRqstTpCd.getValue())) {
                continue;
            }
            if (svcDtlPMsg.shellLineNum.getValue().compareTo(svcUsgPrcPMsg.shellLineNum.getValue()) != 0) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(svcUsgPrcPMsg.regMtrLbCd)) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(svcUsgPrcPMsg.mdlId) || !ZYPCommonFunc.hasValue(svcUsgPrcPMsg.dsOrdPosnNum) || !ZYPCommonFunc.hasValue(svcUsgPrcPMsg.bllgMtrLbCd) || !ZYPCommonFunc.hasValue(svcUsgPrcPMsg.xsMtrAmtRate)) {
                continue;
            }
            String key = S21StringUtil.concatStrings(svcUsgPrcPMsg.mdlId.getValue(), ",", svcUsgPrcPMsg.dsOrdPosnNum.getValue(), ",", svcUsgPrcPMsg.bllgMtrLbCd.getValue());
            Integer count = 1;
            if (prmTierCntMap.containsKey(key)) {
                count = prmTierCntMap.get(key) + 1;
            }
            prmTierCntMap.put(key, count);
        }
        for (Map.Entry<String, Integer> entry : prmTierCntMap.entrySet()) {
            String[] key = entry.getKey().split(",");
            Integer prmCount = entry.getValue();
            Integer aggCount = aggTierCntMap.get(key[2]);
            if (aggCount == null || prmCount.compareTo(aggCount) != 0) {
                return false;
            }
        }
        return true;
    }

    private List<Map<String, Object>> getContrXsCopyForAgg(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlTpAgg", DS_CONTR_DTL_TP.AGGREGATE);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContrXsCopyForAgg", param);
    }

    private String getXsChrgTpCdForAgg(BigDecimal dsContrPk, String bllgMtrLbCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlTpAgg", DS_CONTR_DTL_TP.AGGREGATE);
        param.put("bllgMtrLbCd", bllgMtrLbCd);
        return (String) this.ssmBatchClient.queryObject("getXsChrgTpCdForAgg", param);
    }
    // add end 2018/08/22 QC#23920
    // START 2018/08/22 [QC#22821, ADD]
    private String getAccMdseCd(CPO_DTLTMsg cpoDtlTMsg) {
        String accMdseCd = "";
        Map<String, Object> rslt = (Map<String, Object>) this.ssmBatchClient.queryObject("getAccMdseCd", cpoDtlTMsg);
        if (rslt != null) {
            accMdseCd = (String) rslt.get("MDSE_CD");
        }

        return accMdseCd;
    }

    // START 2019/01/18 [QC#29782, MOD]
    // public String getAccSvcPgmMdseCd(String defSvcPgmMdseCd, String glblCmpyCd, String svcLineBizCd, CPO_DTLTMsg cpoDtlTMsg, String rntlFlg) {
    public String getAccSvcPgmMdseCd(String defSvcPgmMdseCd, String glblCmpyCd, String svcLineBizCd, CPO_DTLTMsg cpoDtlTMsg, String rntlFlg, String prntSvcPgmMdseCd) {
        String accMdseCd = getAccMdseCd(cpoDtlTMsg);
        // NSXC001001GetAccSvcPgmMdse nsxc001001GetAccSvcPgmMdse = new NSXC001001GetAccSvcPgmMdse();
        // String svcPgmMdseCd = nsxc001001GetAccSvcPgmMdse.getAccSvcPgmMdse(glblCmpyCd, svcLineBizCd, accMdseCd, rntlFlg);
        NSXC001001DefaultSvcPgmGetter nsxc001001DefaultSvcPgmGetter = new NSXC001001DefaultSvcPgmGetter();
        String svcPgmMdseCd = nsxc001001DefaultSvcPgmGetter.getAccSvcPgmMdse(glblCmpyCd, svcLineBizCd, accMdseCd, rntlFlg, prntSvcPgmMdseCd);
        if (!ZYPCommonFunc.hasValue(svcPgmMdseCd)) {
            svcPgmMdseCd = defSvcPgmMdseCd;
        }
        return svcPgmMdseCd;
    }
    // END   2018/08/22 [QC#22821, ADD]
    // END 2019/01/18 [QC#29782, MOD]

    // START 2018/09/19 K.Kitachi [QC#28164, ADD]
    private List<BigDecimal> getDsContrDtlPkListForAcc(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("cpoDtlLineNum", cpoDtlLineNum);
        param.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrDtlPkListForAcc", param);
    }

    private List<BigDecimal> getDsContrDtlPkListExstConfig(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("cpoDtlLineNum", cpoDtlLineNum);
        param.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrDtlPkListExstConfig", param);
    }

    private List<BigDecimal> getDsContrDtlPkListNotExstConfig(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("cpoDtlLineNum", cpoDtlLineNum);
        param.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrDtlPkListNotExstConfig", param);
    }
    // END 2018/09/19 K.Kitachi [QC#28164, ADD]

    // START 2019/05/23 W.Honda [QC#50157, ADD]
    private BigDecimal getExistCrCardPO(BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            param.put("dsContrDtlPk", dsContrDtlPk);
        }
        return (BigDecimal) this.ssmBatchClient.queryObject("getExistDsCrCardPO", param);
    }
    // END 2019/05/23 W.Honda [QC#50157, ADD]
    
    // START 2022/03/22 [QC#59683, ADD]
    private String getInvSeptBaseUsgFlgByTgtrTp(String dsInvTgtrTpCd) {
        if (!ZYPCommonFunc.hasValue(dsInvTgtrTpCd)) {
            return ZYPConstant.FLG_OFF_N;
        }
        DS_INV_TGTR_TPTMsg tMsg = new DS_INV_TGTR_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsInvTgtrTpCd, dsInvTgtrTpCd);
        tMsg = (DS_INV_TGTR_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.invSeptBaseUsgFlg.getValue();
        }
        return ZYPConstant.FLG_OFF_N;
    }
    // END   2022/03/22 [QC#59683, ADD]

}
