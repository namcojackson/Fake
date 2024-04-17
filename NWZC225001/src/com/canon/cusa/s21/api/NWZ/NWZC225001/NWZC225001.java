/**
 * <Pre>Copyright (c) 2016-2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC225001;

import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.BDL_PMT_BUNDLED;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.DS_IMPT_SVC_TP_PRODUCT;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.DS_IMPT_SVC_TP_SERVICE;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.INTFC_RSLT_ERROR;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.INTFC_RSLT_NORMAL;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.LEASE_BYOT_MDSE_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.LOAN_CONV_LINE_CRAT;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.LOAN_ORD_ACTION_LOAN_RETURN;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.LOAN_ORD_ACTION_LOAN_SELL;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.MDSE_CD_SHORT_LENGTH;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.MODE_CREATE_ORDER;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.MODE_VALIDATE;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NUM_CONST_TIER_CNT_MAX_VAL;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWAM0702E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWAM0728E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWAM0729E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWAM0730E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWAM0969E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWAM2640W;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWZM0012E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWZM0013E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWZM0188E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWZM0978E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWZM1905E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWZM2200E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWZM2201E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWZM2202E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWZM2203E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.NWZM2305E;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.ORDER_CONFIG;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.ORDER_CONTACT_PERSON;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.ORDER_DELIVERY_INFORMATION;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.ORDER_INSTALL_INFORMATION;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.ORDER_LINE;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.ORDER_RMA_LINE;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.ORDER_SALES_CREDIT;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.ORDER_SITE_SURVEY;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.ORD_LINE_STS_NM_CLOSED;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.ORD_LINE_STS_NM_ON_LOAN;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.ORD_LINE_STS_NM_PENDING_FULFILLMENT;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.PERCENT;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.SERVICE_ADDITIONAL_BASE_CHARGE;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.SERVICE_CONFIG_REFERENCE;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.SERVICE_LINE;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.SUPPLY_PROGRAM;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.SUPPLY_PROGRAM_LINE;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.SVC_ISTL_RULE_NUM_NO_INSTALL;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.TIME_END_POS;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.TIME_START_POS;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.VAL_SET_CODE_ORDER_LOG_AVAILABLE;
import static com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.YYYYMMDDHHMNSSFFF;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDPBigDecimalItem;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CNTYTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_SRC_TPTMsg;
import business.db.DELY_TRNSP_OPTTMsg;
import business.db.DS_IMPT_DTL_EXT_ATTRBTMsg;
import business.db.DS_IMPT_EXT_ATTRBTMsg;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_IMPT_ORD_CONFIGTMsg;
import business.db.DS_IMPT_ORD_CTAC_PSNTMsg;
import business.db.DS_IMPT_ORD_DELY_INFOTMsg;
import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.DS_IMPT_ORD_ERRTMsg;
import business.db.DS_IMPT_ORD_ISTL_INFOTMsg;
import business.db.DS_IMPT_ORD_RTRN_DTLTMsg;
import business.db.DS_IMPT_ORD_SITE_SRVYTMsg;
import business.db.DS_IMPT_ORD_SLS_CRTMsg;
import business.db.DS_IMPT_PRC_CALC_BASETMsg;
import business.db.DS_IMPT_RTRN_PRC_CALCTMsg;
import business.db.DS_IMPT_SCHD_TMPLTMsg;
import business.db.DS_IMPT_SCHD_TMPL_LINETMsg;
import business.db.DS_IMPT_SVC_ADDL_BASETMsg;
import business.db.DS_IMPT_SVC_ADDL_CHRGTMsg;
import business.db.DS_IMPT_SVC_CONFIG_REFTMsg;
import business.db.DS_IMPT_SVC_DTLTMsg;
import business.db.DS_IMPT_SVC_PRCTMsg;
import business.db.DS_IMPT_SVC_PRC_TAX_DTLTMsg;
import business.db.DS_IMPT_SVC_USG_PRCTMsg;
import business.db.DS_MDLTMsg;
import business.db.DS_ORD_LINE_PROC_DFNTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.FRT_CONDTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.db.PRC_CATGTMsg;
import business.db.PRC_CONTRTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SPLY_AGMT_PLNTMsg;
import business.db.SPLY_AGMT_PLNTMsgArray;
import business.db.SVC_ISTL_RULETMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SVC_PRVD_PTYTMsg;
import business.db.SWHTMsg;
import business.db.TOCTMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC180001PMsg;
import business.parts.NWZC225001PMsg;
import business.parts.NWZC225001_configPMsg;
import business.parts.NWZC225001_ctacPsnPMsg;
import business.parts.NWZC225001_dlvyInfoPMsg;
import business.parts.NWZC225001_dlvyInfoPMsgArray;
import business.parts.NWZC225001_dtlPMsg;
import business.parts.NWZC225001_istlInfoPMsg;
import business.parts.NWZC225001_istlInfoPMsgArray;
import business.parts.NWZC225001_ordHdrPMsg;
import business.parts.NWZC225001_rtrnDtlPMsg;
import business.parts.NWZC225001_siteSrvyPMsg;
import business.parts.NWZC225001_slsCrPMsg;
import business.parts.NWZC225001_splyPgmLinePMsg;
import business.parts.NWZC225001_splyPgmPMsg;
import business.parts.NWZC225001_svcAddlBasePrcPMsg;
import business.parts.NWZC225001_svcAddlChrgPrcPMsg;
import business.parts.NWZC225001_svcConfigRefPMsg;
import business.parts.NWZC225001_svcDtlPMsg;
import business.parts.NWZC225001_svcPrcPMsg;
import business.parts.NWZC225001_svcUsgPrcPMsg;
import business.parts.NWZC225001_xxMsgPrmListPMsg;
import business.parts.NWZC226001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.SCHED_LIST;
import com.canon.cusa.s21.api.NWZ.NWZC225001.constant.NWZC225001Constant.SVC_LIST;
import com.canon.cusa.s21.api.NWZ.NWZC226001.NWZC226001;
import com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmoutData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Result;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Util;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_DELY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * NWZC2250 : Deal Configuration Order Creation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/21   Fujitsu         T.Ishii         Create          N/A
 * 2017/01/25   Fujitsu         M.Yamada        Update          QC#16142
 * 2017/02/03   Fujitsu         M.Yamada        Update          QC#17436, 17427
 * 2017/02/15   Fujitsu         M.Ohno          Update          QC#17623, 17616
 * 2017/02/20   Fujitsu         M.Ohno          Update          QC#17585
 * 2017/02/23   Fujitsu         M.Yamada        Update          QC#17701
 * 2017/02/24   Fujitsu         S.Iidaka        Update          QC#16184
 * 2017/02/27   Fujitsu         M.Yamada        Update          QC#17668
 * 2017/03/02   Fujitsu         S.Ohki          Update          QC#17867
 * 2017/03/03   Fujitsu         M.Yamada        Update          QC#17591, 17592
 * 2017/03/22   Fujitsu         M.Yamada        Update          QC#17592
 * 2017/04/03   Fujitsu         S.Iidaka        Update          QC#17974
 * 2017/04/17   Fujitsu         S.Iidaka        Update          QC#18344
 * 2017/06/02   Fujitsu         N.Aoyama        Update          QC#18625
 * 2017/06/05   Fujitsu         N.Aoyama        Update          QC#18212
 * 2017/07/13   Fujitsu         K.Ishizuka      Update          QC#18806(L3#372)
 * 2017/07/25   Fujitsu         R.Nakamura      Update          QC#20114
 * 2017/08/15   Fujitsu         S.Iidaka        Update          QC#20584
 * 2017/08/24   Fujitsu         S.Iidaka        Update          QC#20740
 * 2017/08/25   Fujitsu         S.Iidaka        Update          QC#20627-1
 * 2017/08/28   Fujitsu         S.Iidaka        Update          QC#20740-1
 * 2017/08/30   Fujitsu         R.Nakamura      Update          QC#20822
 * 2017/09/01   Fujitsu         S.Iidaka        Update          QC#20792-1
 * 2017/09/06   Fujitsu         S.Iidaka        Update          QC#20790
 * 2017/09/14   Fujitsu         R.Nakamura      Update          QC#20787
 * 2017/09/25   Fujitsu         A.Kosai         Update          QC#20799
 * 2017/09/28   Fujitsu         S.Iidaka        Update          QC#21384
 * 2017/11/16   Fujitsu         S.Takami        Update          QC#20768
 * 2017/11/21   Fujitsu         Y.Kanefusa      Update          QC#22658
 * 2017/11/21   Fujitsu         S.Takami        Update          QC#21360
 * 2017/12/05   Fujitsu         M.Ohno          Update          QC#22527
 * 2018/01/04   Fujitsu         S.Takami        Update          QC#23323
 * 2018/01/10   Fujitsu         N.Sugiura       Update          QC#18460
 * 2018/02/05   Fujitsu         S.Ohki          Update          QC#23444
 * 2018/04/02   Fujitsu         M.Ohno          Update          QC#20162
 * 2018/04/03   Fujitsu         M.Ohno          Update          QC#24980
 * 2018/04/11   Fujitsu         M.Yamada        Update          QC#25217
 * 2018/05/07   Fujitsu         M.Yamada        Update          QC#25030
 * 2018/05/21   Fujitsu         K.Ishizuka      Update          QC#26068
 * 2018/05/29   Fujitsu         N.Sugiura       Update          QC#23118
 * 2018/06/04   Fujitsu         M.Yamada        Update          QC#25137
 * 2018/07/05   Fujitsu         K.Ishizuka      Update          QC#26528
 * 2018/07/17   Fujitsu         A.Kosai         Update          QC#26828
 * 2018/08/02   Fujitsu         M.Ohno          Update          QC#26665
 * 2018/08/21   Fujitsu         Hd.Sugawara     Update          QC#27489
 * 2018/12/18   Fujitsu         K.Ishizuka      Update          QC#29580
 * 2019/01/25   Fujitsu         M.Ishii         Update          QC#29980
 * 2019/01/29   Fujitsu         K.Ishizuka      Update          QC#30022
 * 2019/02/14   Fujitsu         K.Ishizuka      Update          QC#30340
 * 2019/07/09   Fujitsu         M.Ishii         Update          QC#51277
 * 2019/07/17   Fujitsu         M.Ohno          Update          QC#51647
 * 2019/08/16   Fujitsu         K.Kato          Update          QC#52669
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 * 2019/10/25   Fujitsu         Mz.Takahashi    Update          QC#53993
 * 2021/01/30   CITS            K.Ogino         Update          QC#58230
 * 2021/01/05   CITS            K.Ogino         Update          QC#60971
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 *</pre>
 */
public class NWZC225001 extends S21ApiCommonBase {

    /** isRental : if rental order then true */
    private boolean isRental = false;

    // 2017/11/16 QC#20768 Add Start
    /** Sales Date */
    private String slsDt = "";
    // 2017/11/16 QC#20768 Add End

    // 2017/12/05 QC#22527 add start
    /** isLoan */
    private boolean isLoan = false;

    /** Currency Code */
    private String ccyCd = null;

    /** configOrigOrdMap */
    private Map<BigDecimal, String> configOrigOrdMap = new HashMap<BigDecimal, String>();
    // 2017/12/05 QC#22527 add end
    // constructor

    // 2019/07/17 QC#51647 Add Start
    /** Check Error Flag */
    private boolean errorCheckFlg = false;
    // 2019/07/17 QC#51647 Add EndS

    public NWZC225001() {

        super();
    }

    /**
     * Deal Configuration Order Creation API
     * 
     * <pre>
     * Create the Deal Configuration Order.
     * 
     * @param param         NWZC225001PMsg
     * @param onBatchType   ONBATCH_TP
     * </pre>
     */
    public void execute(final NWZC225001PMsg param, final ONBATCH_TYPE onBatchType) {

        // Create Message Map
        final S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        param.xxIntfcRsltCd.setValue(INTFC_RSLT_NORMAL);

        try {

            // check input parameters
            checkParam(msgMap);
            if (msgMap.getMsgMgr().isXxMsgId()) {

                param.xxIntfcRsltCd.setValue(INTFC_RSLT_ERROR);
                return;
            }

            // 2019/07/19 QC#51647 Add Start
            if (MODE_VALIDATE.equals(param.xxModeCd.getValue())) {
                errorCheckFlg = true;
            }
            // 2019/07/19 QC#51647 Add End

            // 2017/11/16 QC#20768 Add Start
            slsDt = param.slsDt.getValue();
            // 2017/11/16 QC#20768 Add End
            // create staging.
            // QC#60971 Add ONBATCH_TYPE
            BigDecimal dsImptOrdPk = createStaging(msgMap, onBatchType);
            if (msgMap.getMsgMgr().isXxMsgId()) {

                param.xxIntfcRsltCd.setValue(INTFC_RSLT_ERROR);
                return;
            }

            // validate(10), create order(20) & auto order create.
            if (isCallOrderCreation(param.glblCmpyCd.getValue(), param.xxModeCd.getValue())) {

                createOrder(msgMap, dsImptOrdPk, onBatchType);
                if (msgMap.getMsgMgr().isXxMsgId()) {

                    // Mod Start 2017/06/05 QC#18212
                    if (msgMap.getMsgMgr().hasXxMsgId(NWZM2202E)) {
                        param.xxIntfcRsltCd.setValue(INTFC_RSLT_NORMAL);
                    } else {
                        param.xxIntfcRsltCd.setValue(INTFC_RSLT_ERROR);
                    }
                    // Mod Start 2017/06/05 QC#18212
                    return;
                }
            }

        } catch (EZDDBRecordLockedException e) {

            throw e;
        } finally {

            // Flush Message Map.
            msgMap.flush();
        }
    }

    private static boolean isCallOrderCreation(String glblCmpyCd, String xxModeCd) {

        if (S21StringUtil.isEquals(xxModeCd, MODE_VALIDATE)) {

            return true;
        }

        if (!S21StringUtil.isEquals(xxModeCd, MODE_CREATE_ORDER)) {

            return false;
        }

        CPO_SRC_TPTMsg cpoSrcTp = new CPO_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cpoSrcTp.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoSrcTp.cpoSrcTpCd, CPO_SRC_TP.DEAL_CONFIGURATOR);
        cpoSrcTp = (CPO_SRC_TPTMsg) S21FastTBLAccessor.findByKey(cpoSrcTp);
        if (cpoSrcTp == null) {

            return false;
        }
        return S21StringUtil.isEquals(cpoSrcTp.autoOrdImptFlg.getValue(), ZYPConstant.FLG_ON_Y);
    }

    private void checkParam(S21ApiMessageMap msgMap) {

        NWZC225001PMsg inPrmPMsg = (NWZC225001PMsg) msgMap.getPmsg();

        // global company code is required.
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.glblCmpyCd)) {

            setMsgIdWithParam(msgMap, null, NWZM0188E);
        }

        // sales date is required.
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.slsDt)) {

            setMsgIdWithParam(msgMap, null, NWZM0978E);
        }

        // mode is required.
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.xxModeCd)) {

            setMsgIdWithParam(msgMap, null, NWZM0012E);
        }

        // valid mode.
        if (!Arrays.asList(MODE_VALIDATE, MODE_CREATE_ORDER).contains(inPrmPMsg.xxModeCd.getValue())) {

            setMsgIdWithParam(msgMap, null, NWZM0013E);
        }

        if (inPrmPMsg.ordHdr != null && inPrmPMsg.ordHdr.getValidCount() == 1) {

            // order source reference number is required.
            if (!ZYPCommonFunc.hasValue(inPrmPMsg.ordHdr.no(0).ordSrcRefNum)) {

                setMsgIdWithParam(msgMap, null, NWZM1905E);
            }
        }
        // 2019/01/29 S21_NA#30022 Add Start
        if (inclNonImptLineConfig(inPrmPMsg)) {
            setMsgIdWithParam(msgMap, null, NWZM2305E);
        }
        // 2019/01/29 S21_NA#30022 Add End
    }
    
    // 2019/01/29 S21_NA#30022 Add Start
    private boolean inclNonImptLineConfig(NWZC225001PMsg inPrmPMsg) {
        boolean res = false;
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        for (int i = 0; i < inPrmPMsg.config.getValidCount(); i++) {
            NWZC225001_configPMsg configSrc = inPrmPMsg.config.no(i);
            if (ZYPCommonFunc.hasValue(configSrc.dsOrdPosnNum)) {
                map.put(configSrc.dsOrdPosnNum.getValue(), false);
            }
        }
        for (int i = 0; i < inPrmPMsg.dtl.getValidCount(); i++) {
            NWZC225001_dtlPMsg lineSrc = inPrmPMsg.dtl.no(i);
            if (ZYPCommonFunc.hasValue(lineSrc.dsOrdPosnNum) && ZYPCommonFunc.hasValue(lineSrc.imptLineFlg)) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineSrc.imptLineFlg.getValue())) {
                    map.put(lineSrc.dsOrdPosnNum.getValue(), true);
                }
            }
        }
        if (!map.containsValue(true)) {
            res = true;
        }
        return res;
    }
    // 2019/01/29 S21_NA#30022 Add End
    // QC#60971 Add ONBATCH_TYPE
    private BigDecimal createStaging(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NWZC225001PMsg pMsg = (NWZC225001PMsg) msgMap.getPmsg();

        /**
         * order
         */

        // ****************************************************
        // create import order
        // ****************************************************
        List<DS_IMPT_ORDTMsg> headerList = createDsImptOrd(msgMap);
        if (headerList.size() != 1) {

            setMsgIdWithParam(msgMap, null, NWZM2203E);
            return null;
        }

        // ****************************************************
        // already registered ?
        // ****************************************************
        for (DS_IMPT_ORDTMsg header : headerList) {
            isRental = isRental(header);

            NWXC220001Result<Map<String, List<BigDecimal>>> result = NWXC220001.checkDuplicateOrdSrcRefNum(pMsg.glblCmpyCd.getValue(), header.ordSrcRefNum.getValue(), CPO_SRC_TP.DEAL_CONFIGURATOR, null);
            Map<String, List<BigDecimal>> duplicateDsImptPkMap = result.getResultObject();

            // delete import staging.
            if (!deleteDuplicateDsImpt(pMsg.glblCmpyCd.getValue(), duplicateDsImptPkMap)) {

                setMsgIdWithParam(msgMap, null, NWAM0730E, "DS_IMPT_ORD");
                continue;
            }
            List<NWXC220001ErrorInfo> errorList = result.getErrInfoList();
            if (errorList.size() > 0) {

                for (NWXC220001ErrorInfo error : errorList) {

                    setMsgIdWithParam(msgMap, null, error.getErrMsgId(), error.getParamArray());
                }
                continue;
            }
        }

        // ****************************************************
        // create import order configuration
        // ****************************************************
        List<DS_IMPT_ORD_CONFIGTMsg> configList = createDsImptOrdConfig(msgMap, headerList);

        // ****************************************************
        // create import order sales credit
        // ****************************************************
        List<DS_IMPT_ORD_SLS_CRTMsg> salesCeditList = createDsImptOrdSlsCr(msgMap, configList);

        // ****************************************************
        // create import order install information
        // ****************************************************
        // List<DS_IMPT_ORD_ISTL_INFOTMsg> installList =
        // createDsImptOrdIstlInfo(msgMap, configList);
        List<DS_IMPT_ORD_ISTL_INFOTMsg> installList = createDsImptOrdIstlInfo(msgMap, configList, headerList); // 2017/02/15
        // S21_NA#17616
        // Mod

        // ****************************************************
        // create import order delivery information
        // ****************************************************
        List<DS_IMPT_ORD_DELY_INFOTMsg> deliveryList = createDsImptOrdDelyInfo(msgMap, configList);

        // ****************************************************
        // create import order site survey
        // ****************************************************
        List<DS_IMPT_ORD_SITE_SRVYTMsg> siteSurveyList = createDsImptOrdSiteSrvy(msgMap, configList);

        // 2017/11/16 QC#20768 Add Start
        setFrtCond(headerList, siteSurveyList);
        // 2017/11/16 QC#20768 Add End

        // ****************************************************
        // create import order contact person
        // ****************************************************
        List<DS_IMPT_ORD_CTAC_PSNTMsg> contactList = createDsImptOrdCtacPsn(msgMap, configList);

        // Add Start 2018/08/21 QC#27489
        // Used Sercice Machine Master PK List
        // If loan conversion, receive Line and RMA
        // (Always only Line or only RMA)
        List<BigDecimal> usedSvcMachMstrPkList = new ArrayList<BigDecimal>(0);
        // Add End 2018/08/21 QC#27489

        // ****************************************************
        // create import order line
        // ****************************************************
        // Mod Start 2018/08/21 QC#27489
        //List<DS_IMPT_ORD_DTLTMsg> lineList = createDsImptOrdDtl(msgMap, headerList, configList);
        //List<DS_IMPT_ORD_DTLTMsg> lineList = createDsImptOrdDtl(msgMap, headerList, configList, usedSvcMachMstrPkList);
        // QC#60971 Add ONBATCH_TYPE
        List<DS_IMPT_ORD_DTLTMsg> lineList = createDsImptOrdDtl(msgMap, headerList, configList, usedSvcMachMstrPkList, onBatchType);
        // Mod End 2018/08/21 QC#27489

        // ****************************************************
        // create import order return line
        // ****************************************************
        // Mod Start 2018/08/21 QC#27489
        //List<DS_IMPT_ORD_RTRN_DTLTMsg> rmaLineList = createDsImptOrdRtrnDtl(msgMap, headerList, configList);
        List<DS_IMPT_ORD_RTRN_DTLTMsg> rmaLineList = createDsImptOrdRtrnDtl(msgMap, headerList, configList, usedSvcMachMstrPkList);
        // Mod End 2018/08/21 QC#27489

        // ****************************************************
        // pricing
        // ****************************************************
        NWZC157001PMsg prcApiPMsg = callPricing(msgMap, headerList.get(0), configList, lineList, rmaLineList);

        // ****************************************************
        // create import price calculation base
        // ****************************************************
        List<DS_IMPT_PRC_CALC_BASETMsg> calcBaseList = createDsImptPrcCalcBase(msgMap, prcApiPMsg, lineList);

        // ****************************************************
        // create import price calculation base
        // ****************************************************
        List<DS_IMPT_RTRN_PRC_CALCTMsg> calcBaseRmaList = createDsImptRtrnPrcCalc(msgMap, prcApiPMsg, rmaLineList, lineList.size());

        /**
         * service
         */
        /** lbrSvcDtlList */
        Map<BigDecimal, NWZC225001_svcDtlPMsg> lbrSvcDtlMap = new HashMap<BigDecimal, NWZC225001_svcDtlPMsg>();
        // ****************************************************
        // create import service line
        // ****************************************************
        List<BigDecimal> bdlSvcLineNumList = new ArrayList<BigDecimal>();
        List<DS_IMPT_SVC_DTLTMsg> serviceLineList = createDsImptSvcDtlForService(msgMap, headerList, lbrSvcDtlMap, bdlSvcLineNumList);

        // ****************************************************
        // create import service configuration reference
        // ****************************************************
        // ****************************************************
        // create import service price
        // ****************************************************
        // ****************************************************
        // create import service usage price
        // ****************************************************
        // 2018/04/03 QC#24980 mod start
//        Map<String, Object> svcPrcListMap = getSvcPrcListMap(pMsg.glblCmpyCd.getValue(), headerList.get(0).dsOrdCatgCd.getValue(), headerList.get(0).dsOrdTpCd.getValue(), pMsg.slsDt.getValue());
        List<Map<String, Object>> svcPrcListMap = getSvcPrcListMap(pMsg.glblCmpyCd.getValue(), headerList.get(0).dsOrdCatgCd.getValue(), headerList.get(0).dsOrdTpCd.getValue(), pMsg.slsDt.getValue());
        // 2018/04/03 QC#24980 mod end
        Map<SVC_LIST, List<?>> serviceMap //
        = createDsImptSvcConfigRef(msgMap, serviceLineList, lineList, svcPrcListMap, lbrSvcDtlMap, bdlSvcLineNumList);

        @SuppressWarnings("unchecked")
        List<DS_IMPT_SVC_CONFIG_REFTMsg> configRefList = (List<DS_IMPT_SVC_CONFIG_REFTMsg>) serviceMap.get(SVC_LIST.CONFIG_REF);
        @SuppressWarnings("unchecked")
        List<DS_IMPT_SVC_PRCTMsg> servicePriceList = (List<DS_IMPT_SVC_PRCTMsg>) serviceMap.get(SVC_LIST.PRC);
        @SuppressWarnings("unchecked")
        List<DS_IMPT_SVC_USG_PRCTMsg> usageList = (List<DS_IMPT_SVC_USG_PRCTMsg>) serviceMap.get(SVC_LIST.USG_PRC);

        // ****************************************************
        // create import service additional base price
        // ****************************************************
        // Mod Start 2017/08/30 QC#20822
        List<DS_IMPT_SVC_ADDL_BASETMsg> additionalBaseList //
//        = createDsImptSvcAddlBase(msgMap, headerList, serviceLineList, lineList, lbrSvcDtlMap, bdlSvcLineNumList);
        = createDsImptSvcAddlBase(msgMap, headerList, serviceLineList, lineList, lbrSvcDtlMap, svcPrcListMap, bdlSvcLineNumList);
        // Mod End 2017/08/30 QC#20822

        // ****************************************************
        // create import service additional charge price
        // ****************************************************
        List<DS_IMPT_SVC_ADDL_CHRGTMsg> additionalChargeList //
        = createDsImptSvcAddlChrg(msgMap, headerList, serviceLineList, lineList, lbrSvcDtlMap, svcPrcListMap, bdlSvcLineNumList);

        /**
         * schedule template
         */

        // ****************************************************
        // create import supply program
        // ****************************************************
        // ****************************************************
        // create import supply program line
        // ****************************************************
        // Mod Start 2017/08/30 QC#20822
//        Map<SCHED_LIST, List<?>> shedTmplMap = createDsImptShedTmpl(msgMap, headerList, serviceLineList, configRefList, svcPrcListMap, servicePriceList, lineList);
        // Map<SCHED_LIST, List<?>> shedTmplMap = createDsImptShedTmpl(msgMap, headerList, serviceLineList, configRefList, svcPrcListMap, servicePriceList, additionalBaseList, lineList); // 2018/12/17 S21_NA#29580 Mod
        Map<SCHED_LIST, List<?>> shedTmplMap = createDsImptShedTmpl(msgMap, headerList, serviceLineList, configRefList, svcPrcListMap, servicePriceList, additionalBaseList, lineList, lbrSvcDtlMap, bdlSvcLineNumList);
        // Mod End 2017/08/30 QC#20822
        @SuppressWarnings("unchecked")
        List<DS_IMPT_SCHD_TMPLTMsg> supplyProgramList = (List<DS_IMPT_SCHD_TMPLTMsg>) shedTmplMap.get(SCHED_LIST.TMPL);
        @SuppressWarnings("unchecked")
        List<DS_IMPT_SCHD_TMPL_LINETMsg> supplyProgramLineList = (List<DS_IMPT_SCHD_TMPL_LINETMsg>) shedTmplMap.get(SCHED_LIST.TMPL_LINE);

        /**
         * insert staging
         */

        /**
         * order
         */

        // ****************************************************
        // create import order
        // ****************************************************
        for (DS_IMPT_ORDTMsg data : headerList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import order configuration
        // ****************************************************
        for (DS_IMPT_ORD_CONFIGTMsg data : configList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import order sales credit
        // ****************************************************
        for (DS_IMPT_ORD_SLS_CRTMsg data : salesCeditList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import order install information
        // ****************************************************
        for (DS_IMPT_ORD_ISTL_INFOTMsg data : installList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import order delivery information
        // ****************************************************
        for (DS_IMPT_ORD_DELY_INFOTMsg data : deliveryList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import order site survey
        // ****************************************************
        for (DS_IMPT_ORD_SITE_SRVYTMsg data : siteSurveyList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import order contact person
        // ****************************************************
        for (DS_IMPT_ORD_CTAC_PSNTMsg data : contactList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import order line
        // ****************************************************
        for (DS_IMPT_ORD_DTLTMsg data : lineList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import order return line
        // ****************************************************
        for (DS_IMPT_ORD_RTRN_DTLTMsg data : rmaLineList) {

            insert(msgMap, data);
        }
        // ****************************************************
        // create import price calculation base
        // ****************************************************
        for (DS_IMPT_PRC_CALC_BASETMsg data : calcBaseList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import price calculation base
        // ****************************************************
        for (DS_IMPT_RTRN_PRC_CALCTMsg data : calcBaseRmaList) {

            insert(msgMap, data);
        }

        /**
         * service
         */

        // ****************************************************
        // create import service line
        // ****************************************************
        for (DS_IMPT_SVC_DTLTMsg data : serviceLineList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import service configuration reference
        // ****************************************************
        for (DS_IMPT_SVC_CONFIG_REFTMsg data : configRefList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import service price
        // ****************************************************
        for (DS_IMPT_SVC_PRCTMsg data : servicePriceList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import service usage price
        // ****************************************************
        for (DS_IMPT_SVC_USG_PRCTMsg data : usageList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import service additional base price
        // ****************************************************
        for (DS_IMPT_SVC_ADDL_BASETMsg data : additionalBaseList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import service additional charge price
        // ****************************************************
        for (DS_IMPT_SVC_ADDL_CHRGTMsg data : additionalChargeList) {

            insert(msgMap, data);
        }

        /** QC#16142 LBR(Rental Shell) */
        for (NWZC225001_svcDtlPMsg svcDtlPMsg : lbrSvcDtlMap.values()) {
            // BigDecimal dsImptSvcLineNum = entry.getKey();
            // NWZC225001_svcDtlPMsg svcDtlPMsg = entry.getValue();
            if (!ZYPCommonFunc.hasValue(svcDtlPMsg.bdlPmtCd)) {
                continue;
            }
            if (bdlSvcLineNumList.contains(svcDtlPMsg.dsImptSvcLineNum.getValue()) //
                    && DS_IMPT_SVC_TP_SERVICE.equals(svcDtlPMsg.dsImptSvcTpCd.getValue())) {
                continue;
            }
            // BigDecimal imptSvcLineNum =
            // svcDtlPMsg.dsImptSvcLineNum.getValue();
            // if
            // (BDL_PMT_BUNDLED.equals(svcDtlPMsg.bdlPmtCd.getValue()))
            // {
            // imptSvcLineNum =
            // svcDtlPMsg.refImptSvcLineNum.getValue();
            // }
            List<DS_IMPT_SVC_ADDL_BASETMsg> lbrAdditionalBaseList //
            = createLbrDsImptSvcAddlBase(msgMap, headerList, serviceLineList, lineList, svcDtlPMsg, bdlSvcLineNumList);

            for (DS_IMPT_SVC_ADDL_BASETMsg data : lbrAdditionalBaseList) {
                insert(msgMap, data);
            }

        }

        updateBaseTotalAmount(serviceLineList);

        /**
         * schedule template
         */

        // ****************************************************
        // create import supply program
        // ****************************************************
        for (DS_IMPT_SCHD_TMPLTMsg data : supplyProgramList) {

            insert(msgMap, data);
        }

        // ****************************************************
        // create import supply program line
        // ****************************************************
        for (DS_IMPT_SCHD_TMPL_LINETMsg data : supplyProgramLineList) {

            insert(msgMap, data);
        }

        // START 2023/09/05 K.Watanabe [QC#53408, ADD]
        // ****************************************************
        // update import order install information
        // ****************************************************
        List<DS_IMPT_ORD_ISTL_INFOTMsg> updateInstallList = new ArrayList<DS_IMPT_ORD_ISTL_INFOTMsg>();
        for (DS_IMPT_ORD_ISTL_INFOTMsg data : installList) {
            for (int i = 0; i < pMsg.istlInfo.getValidCount(); i++) {
                NWZC225001_istlInfoPMsg source = pMsg.istlInfo.no(i);
                DS_IMPT_ORD_CONFIGTMsg config = getConfig(configList, source.dsOrdPosnNum.getValue());

                if (data.dsImptOrdPk.getValue().compareTo(config.dsImptOrdPk.getValue()) != 0 || data.dsImptOrdConfigPk.getValue().compareTo(config.dsImptOrdConfigPk.getValue()) != 0) {
                    continue;
                }

                // START 2023/12/12 K.Watanabe [QC#61300, ADD]
                if (S21StringUtil.isEquals(config.configCatgCd.getValue(), CONFIG_CATG.INBOUND)) {
                    Map<String, Object> deinstallInfoMap = NWZC225001Query.getInstance().getDeinstallInfo(source.ordSrcRefNum.getValue(), config);
                    if (deinstallInfoMap != null) {
                        boolean updateFlag = false;
                        String svcBySvcPrvdPtyCd = (String) deinstallInfoMap.get("SVC_BY_SVC_PRVD_PTY_CD");
                        String svcDeinsRuleNum = (String) deinstallInfoMap.get("SVC_DEINS_RULE_NUM");
                        if (!ZYPCommonFunc.hasValue(data.istlBySvcPrvdPtyCd) && ZYPCommonFunc.hasValue(svcBySvcPrvdPtyCd)) {
                            ZYPEZDItemValueSetter.setValue(data.istlBySvcPrvdPtyCd, svcBySvcPrvdPtyCd);
                            updateFlag = true;
                        }
                        if (ZYPCommonFunc.hasValue(svcDeinsRuleNum)) {
                            ZYPEZDItemValueSetter.setValue(data.svcIstlRuleNum, svcDeinsRuleNum);
                            updateFlag = true;
                        } else {
                            BigDecimal dsCpoConfigPk = (BigDecimal) deinstallInfoMap.get("DS_IMPT_ORD_CONFIG_PK");
                            BigDecimal svcMachMstrPk = (BigDecimal) deinstallInfoMap.get("SVC_MACH_MSTR_PK");
                            Map<String, Object> svcDeinsRuleForMdseMap = NWZC225001Query.getInstance().getMdlSvcDeinsRuleNum(config.glblCmpyCd.getValue(), dsCpoConfigPk, svcMachMstrPk);
                            if (svcDeinsRuleForMdseMap == null) {
                                Map<String, Object> svcDeinsRuleForRtrnDtlMap = NWZC225001Query.getInstance().getMdseSvcDeinsRuleNum(config.glblCmpyCd.getValue(), dsCpoConfigPk);
                                if (svcDeinsRuleForRtrnDtlMap != null) {
                                    String svcDeinsRuleNumForRtrnDtl = (String) svcDeinsRuleForRtrnDtlMap.get("SVC_DEINS_RULE_NUM");
                                    ZYPEZDItemValueSetter.setValue(data.svcIstlRuleNum, svcDeinsRuleNumForRtrnDtl);
                                    updateFlag = true;
                                }
                            }
                        }
                        if (updateFlag) {
                            updateInstallList.add(data);
                        }
                    }
                }
                // END 2023/12/12 K.Watanabe [QC#61300, ADD]

                Map<String, Object> defaultAccessoryInstType = NWZC225001Query.getInstance().getDefaultAccessoryInstTypeWithConfNum(source.ordSrcRefNum.getValue(), config);
                if (defaultAccessoryInstType != null) {

                    String defaultAccessoryInstTypeNum = (String) defaultAccessoryInstType.get("SVC_ISTL_RULE_NUM");

                    if (ZYPCommonFunc.hasValue(defaultAccessoryInstTypeNum)) {
                        ZYPEZDItemValueSetter.setValue(data.svcIstlRuleNum, defaultAccessoryInstTypeNum);
                        updateInstallList.add(data);
                    } else { 
                        ZYPEZDItemValueSetter.setValue(data.svcIstlRuleNum, SVC_ISTL_RULE_NUM_NO_INSTALL);
                        updateInstallList.add(data);
                    }
                }
            }
        }

        for (DS_IMPT_ORD_ISTL_INFOTMsg data : updateInstallList) {

            update(msgMap, data);
        }
        // END 2023/09/05 K.Watanabe [QC#53408, ADD]

        BigDecimal dsImptOrdPk = headerList.get(0).dsImptOrdPk.getValue();
        return dsImptOrdPk;
    }

    // QC#16142
    private void updateBaseTotalAmount(List<DS_IMPT_SVC_DTLTMsg> serviceLineList) {
        for (DS_IMPT_SVC_DTLTMsg tMsg : serviceLineList) {
            tMsg = (DS_IMPT_SVC_DTLTMsg) S21ApiTBLAccessor.findByKey(tMsg);
            Map<String, Object> rsltMap = getBaseTotalAmt(tMsg);
            if (rsltMap == null) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(tMsg.totBasePrcDealAmt, (BigDecimal) rsltMap.get("BASE_TOT_DEAL_AMT"));
            ZYPEZDItemValueSetter.setValue(tMsg.totBasePrcDealAmt, (BigDecimal) rsltMap.get("BASE_TOT_FUNC_AMT"));
            S21ApiTBLAccessor.update(tMsg);
        }

    }

    // QC#16142
    private boolean isRental(DS_IMPT_ORDTMsg header) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", header.glblCmpyCd);
        ssmParam.put("ordCatgCtxTpRentalOrder", "RENTAL_ORDER");
        ssmParam.put("dsOrdCatgCd", header.dsOrdCatgCd);
        ssmParam.put("dsOrdTpCd", header.dsOrdTpCd);
        ssmParam.put("dsOrdRsnCd", header.dsOrdRsnCd);

        return (BigDecimal.ZERO.compareTo(NWZC225001Query.getInstance().queryBigDecimal("getCountRentalOrderCategory", ssmParam)) < 0);
    }

    private boolean deleteDuplicateDsImpt(String glblCmpyCd, Map<String, List<BigDecimal>> duplicateDsImptPkMap) {

        List<BigDecimal> dsImptOrdPkList = duplicateDsImptPkMap.get("dsImptOrdPk");
        for (BigDecimal dsImptOrdPk : dsImptOrdPkList) {

            DS_IMPT_ORDTMsg dsImptOrd = new DS_IMPT_ORDTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrd.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrd.dsImptOrdPk, dsImptOrdPk);
            dsImptOrd = (DS_IMPT_ORDTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsImptOrd);
            if (dsImptOrd == null) {

                return false;
            }
            EZDTBLAccessor.logicalRemove(dsImptOrd);
            if (!S21StringUtil.isEquals(dsImptOrd.getReturnCode(), EZDTBLAccessor.RTNCD_NORMAL)) {

                return false;
            }

            DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfig = new DS_IMPT_ORD_CONFIGTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdConfig.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdConfig, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_DTLTMsg dsImptOrdDtl = new DS_IMPT_ORD_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdDtl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdDtl.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdDtl, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_RTRN_DTLTMsg dsImptOrdRtrnDtl = new DS_IMPT_ORD_RTRN_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdRtrnDtl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdRtrnDtl.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdRtrnDtl, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCr = new DS_IMPT_ORD_SLS_CRTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCr.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdSlsCr, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_CTAC_PSNTMsg dsImptOrdCtacPsn = new DS_IMPT_ORD_CTAC_PSNTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdCtacPsn.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdCtacPsn.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdCtacPsn, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_SITE_SRVYTMsg dsImptOrdSiteSrvy = new DS_IMPT_ORD_SITE_SRVYTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdSiteSrvy.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdSiteSrvy.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdSiteSrvy, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_DELY_INFOTMsg dsImptOrdDelyInfo = new DS_IMPT_ORD_DELY_INFOTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfo.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfo.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdDelyInfo, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_ISTL_INFOTMsg dsImptOrdIstlInfo = new DS_IMPT_ORD_ISTL_INFOTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdIstlInfo.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdIstlInfo.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdIstlInfo, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_PRC_CALC_BASETMsg dsImptPrcCalcBase = new DS_IMPT_PRC_CALC_BASETMsg();
            ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBase.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBase.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptPrcCalcBase, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_RTRN_PRC_CALCTMsg dsImptRtrnPrcCalc = new DS_IMPT_RTRN_PRC_CALCTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptRtrnPrcCalc.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptRtrnPrcCalc.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptRtrnPrcCalc, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_SVC_DTLTMsg dsImptSvcDtl = new DS_IMPT_SVC_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSvcDtl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSvcDtl.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSvcDtl, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_SVC_CONFIG_REFTMsg dsImptSvcConfigRef = new DS_IMPT_SVC_CONFIG_REFTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSvcConfigRef.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSvcConfigRef.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSvcConfigRef, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_SVC_ADDL_BASETMsg dsImptSvcAddlBase = new DS_IMPT_SVC_ADDL_BASETMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSvcAddlBase.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSvcAddlBase.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSvcAddlBase, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_SVC_ADDL_CHRGTMsg dsImptSvcAddlChrg = new DS_IMPT_SVC_ADDL_CHRGTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSvcAddlChrg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSvcAddlChrg.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSvcAddlChrg, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_SVC_PRC_TAX_DTLTMsg dsImptSvcPrcTaxDtl = new DS_IMPT_SVC_PRC_TAX_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSvcPrcTaxDtl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSvcPrcTaxDtl.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSvcPrcTaxDtl, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_EXT_ATTRBTMsg dsImptExtAttrb = new DS_IMPT_EXT_ATTRBTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptExtAttrb.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptExtAttrb.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptExtAttrb, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_DTL_EXT_ATTRBTMsg dsImptDtlExtAttrb = new DS_IMPT_DTL_EXT_ATTRBTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptDtlExtAttrb.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptDtlExtAttrb.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptDtlExtAttrb, new String[] {"glblCmpyCd", "dsImptOrdPk" });

            DS_IMPT_ORD_ERRTMsg dsImptOrdErr = new DS_IMPT_ORD_ERRTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptOrdErr.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdPk, dsImptOrdPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptOrdErr, new String[] {"glblCmpyCd", "dsImptOrdPk" });
        }

        List<BigDecimal> dsImptSvcDtlPkList = duplicateDsImptPkMap.get("dsImptSvcDtlPk");
        for (BigDecimal dsImptSvcDtlPk : dsImptSvcDtlPkList) {

            DS_IMPT_SVC_PRCTMsg dsImptSvcPrc = new DS_IMPT_SVC_PRCTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSvcPrc.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSvcPrc.dsImptSvcDtlPk, dsImptSvcDtlPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSvcPrc, new String[] {"glblCmpyCd", "dsImptSvcDtlPk" });

            DS_IMPT_SVC_USG_PRCTMsg dsImptSvcUsgPrc = new DS_IMPT_SVC_USG_PRCTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSvcUsgPrc.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSvcUsgPrc.dsImptSvcDtlPk, dsImptSvcDtlPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSvcUsgPrc, new String[] {"glblCmpyCd", "dsImptSvcDtlPk" });

            DS_IMPT_SCHD_TMPLTMsg dsImptSchdTmpl = new DS_IMPT_SCHD_TMPLTMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSchdTmpl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSchdTmpl.dsImptSvcDtlPk, dsImptSvcDtlPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSchdTmpl, new String[] {"glblCmpyCd", "dsImptSvcDtlPk" });
        }

        List<BigDecimal> dsImptSchdTmplPkList = duplicateDsImptPkMap.get("dsImptSchdTmplPk");
        for (BigDecimal dsImptSchdTmplPk : dsImptSchdTmplPkList) {

            DS_IMPT_SCHD_TMPL_LINETMsg dsImptSchdTmplLine = new DS_IMPT_SCHD_TMPL_LINETMsg();
            ZYPEZDItemValueSetter.setValue(dsImptSchdTmplLine.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsImptSchdTmplLine.dsImptSchdTmplPk, dsImptSchdTmplPk);
            S21FastTBLAccessor.logicalRemoveByPartialValue(dsImptSchdTmplLine, new String[] {"glblCmpyCd", "dsImptSchdTmplPk" });
        }
        return true;
    }

    private List<DS_IMPT_PRC_CALC_BASETMsg> createDsImptPrcCalcBase(S21ApiMessageMap msgMap, NWZC157001PMsg prcApiPMsg, List<DS_IMPT_ORD_DTLTMsg> lineList) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_PRC_CALC_BASETMsg> targetList = new ArrayList<DS_IMPT_PRC_CALC_BASETMsg>();

        int detailIndex = 0;
        for (DS_IMPT_ORD_DTLTMsg line : lineList) {

            detailIndex++;
            NWZC157002PMsg detailPrice = null;
            for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {

                NWZC157002PMsg nwzc15700102 = prcApiPMsg.NWZC157002PMsg.no(i);

                if (!S21StringUtil.isEquals(nwzc15700102.configCatgCd.getValue(), CONFIG_CATG.OUTBOUND)) {
                    continue;
                }

                if (!S21StringUtil.isEquals(nwzc15700102.trxLineNum.getValue(), NWXC220001Util.convNumToZ99(detailIndex))) {
                    continue;
                }
                if (!S21StringUtil.isEquals(nwzc15700102.trxLineSubNum.getValue(), "001")) {
                    continue;
                }
                detailPrice = nwzc15700102;
                break;
            }

            if (detailPrice == null) {

                setMsgIdWithParam(msgMap, null, NWZM1905E);
                continue;
            }

            BigDecimal xxUnitNetPrcAmt = detailPrice.NWZC157003PMsg.no(0).unitPrcAmt.getValue();
            ZYPEZDItemValueSetter.setValue(line.dealPrcListPrcAmt, xxUnitNetPrcAmt);
            ZYPEZDItemValueSetter.setValue(line.funcPrcListPrcAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), line.ccyCd.getValue(), xxUnitNetPrcAmt, msg.slsDt.getValue()));

            for (int i = 0; i < detailPrice.NWZC157003PMsg.getValidCount(); i++) {

                NWZC157003PMsg calcBase = detailPrice.NWZC157003PMsg.no(i);

                DS_IMPT_PRC_CALC_BASETMsg dsImptPrcCalcBase = new DS_IMPT_PRC_CALC_BASETMsg();

                EZDMsg.copy(calcBase, null, dsImptPrcCalcBase, null);
                BigDecimal dsImptPrcCalcBasePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_PRC_CALC_BASE_SQ);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBase.glblCmpyCd, msg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBase.dsImptPrcCalcBasePk, dsImptPrcCalcBasePk);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBase.dsImptOrdPk, line.dsImptOrdPk);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBase.dsImptOrdDtlPk, line.dsImptOrdDtlPk);

                targetList.add(dsImptPrcCalcBase);
            }
        }

        return targetList;
    }

    private List<DS_IMPT_RTRN_PRC_CALCTMsg> createDsImptRtrnPrcCalc(S21ApiMessageMap msgMap, NWZC157001PMsg prcApiPMsg, List<DS_IMPT_ORD_RTRN_DTLTMsg> rmaLineList, int startIndex) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_RTRN_PRC_CALCTMsg> targetList = new ArrayList<DS_IMPT_RTRN_PRC_CALCTMsg>();

        NWZC157002PMsg detailPrice = null;
        int detailIndex = startIndex;
        for (DS_IMPT_ORD_RTRN_DTLTMsg rmaLine : rmaLineList) {

            for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {

                NWZC157002PMsg nwzc15700102 = prcApiPMsg.NWZC157002PMsg.no(i);

                if (!S21StringUtil.isEquals(nwzc15700102.configCatgCd.getValue(), CONFIG_CATG.INBOUND)) {

                    continue;
                }

                if (!S21StringUtil.isEquals(nwzc15700102.trxLineNum.getValue(), NWXC220001Util.convNumToZ99(detailIndex))) {

                    continue;
                }
                if (!S21StringUtil.isEquals(nwzc15700102.trxLineSubNum.getValue(), "001")) {

                    continue;
                }
                detailPrice = nwzc15700102;
                break;
            }

            if (detailPrice == null) {

                continue;
            }
            for (int i = 0; i < detailPrice.NWZC157003PMsg.getValidCount(); i++) {

                NWZC157003PMsg calcBase = detailPrice.NWZC157003PMsg.no(i);

                DS_IMPT_RTRN_PRC_CALCTMsg dsImptRtrnPrcCalc = new DS_IMPT_RTRN_PRC_CALCTMsg();

                EZDMsg.copy(calcBase, null, dsImptRtrnPrcCalc, null);
                BigDecimal dsImptRtrnPrcCalcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_RTRN_PRC_CALC_SQ);
                ZYPEZDItemValueSetter.setValue(dsImptRtrnPrcCalc.glblCmpyCd, msg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptRtrnPrcCalc.dsImptRtrnPrcCalcPk, dsImptRtrnPrcCalcPk);
                ZYPEZDItemValueSetter.setValue(dsImptRtrnPrcCalc.dsImptOrdPk, rmaLine.dsImptOrdPk);
                ZYPEZDItemValueSetter.setValue(dsImptRtrnPrcCalc.dsImptOrdRtrnDtlPk, rmaLine.dsImptOrdRtrnDtlPk);

                targetList.add(dsImptRtrnPrcCalc);
            }
            detailIndex++;
        }
        return targetList;
    }

    private void insert(S21ApiMessageMap msgMap, EZDTMsg tmsg) {

        S21FastTBLAccessor.insert(tmsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {

            setMsgIdWithParam(msgMap, null, NWAM0728E, tmsg.getTableName());
        }
    }

    // START 2023/09/05 K.Watanabe [QC#53408, ADD]
    private void update(S21ApiMessageMap msgMap, EZDTMsg tmsg) {

        S21FastTBLAccessor.update(tmsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {

            setMsgIdWithParam(msgMap, null, NWAM0729E, tmsg.getTableName());
        }
    }
    // END 2023/09/05 K.Watanabe [QC#53408, ADD]

    /**
     * order
     */

    // ****************************************************
    // create import order
    // ****************************************************
    private List<DS_IMPT_ORDTMsg> createDsImptOrd(S21ApiMessageMap msgMap) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_ORDTMsg> targetList = new ArrayList<DS_IMPT_ORDTMsg>();

        for (int i = 0; i < msg.ordHdr.getValidCount(); i++) {

            NWZC225001_ordHdrPMsg source = msg.ordHdr.no(i);

            DS_IMPT_ORDTMsg target = new DS_IMPT_ORDTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_ORD_SQ));
            ZYPEZDItemValueSetter.setValue(target.cpoSrcTpCd, CPO_SRC_TP.DEAL_CONFIGURATOR);
            ZYPEZDItemValueSetter.setValue(target.ordSrcImptTs, ZYPDateUtil.getCurrentSystemTime(YYYYMMDDHHMNSSFFF));
            ZYPEZDItemValueSetter.setValue(target.ordSrcRefNum, source.ordSrcRefNum);

            if (isCallOrderCreation(msg.glblCmpyCd.getValue(), msg.xxModeCd.getValue())) {

                ZYPEZDItemValueSetter.setValue(target.imptStsCd, IMPT_STS.NOT_PROCESSED);
            } else {

                ZYPEZDItemValueSetter.setValue(target.imptStsCd, IMPT_STS.PENDING_OM_REVIEW);
            }

            ZYPEZDItemValueSetter.setValue(target.sysSrcCd, SYS_SRC.S21_ORDER);
            ZYPEZDItemValueSetter.setValue(target.dsOrdCatgCd, source.dsOrdCatgCd);
            ZYPEZDItemValueSetter.setValue(target.dsOrdTpCd, source.dsOrdTpCd);
            target.dsOrdRsnCd.clear();

            DS_ORD_TPTMsg dsOrdTp = getDsOrdTp(msg.glblCmpyCd.getValue(), source.dsOrdTpCd.getValue());
            if (dsOrdTp != null) {

                // derive CPO order type from DS order type
                ZYPEZDItemValueSetter.setValue(target.cpoOrdTpCd, dsOrdTp.cpoOrdTpCd);
            }
            ZYPEZDItemValueSetter.setValue(target.custIssPoNum, source.custIssPoNum);
            ZYPEZDItemValueSetter.setValue(target.custIssPoDt, source.custIssPoDt);

            // bill to
            ZYPEZDItemValueSetter.setValue(target.billToCustAcctCd, source.billToCustAcctCd);
            BILL_TO_CUSTTMsg billTo = getBillToCustByLocNum(msg.glblCmpyCd.getValue(), source.billToLocNum.getValue(), msg.slsDt.getValue());
            if (billTo != null) {

                // bill to customer code
                ZYPEZDItemValueSetter.setValue(target.billToCustCd, billTo.billToCustCd);
            }

            // sold to
            ZYPEZDItemValueSetter.setValue(target.sellToCustCd, source.sellToCustCd);
            BILL_TO_CUSTTMsg soldTo = getBillToCustByLocNum(msg.glblCmpyCd.getValue(), source.soldToLocNum.getValue(), msg.slsDt.getValue());
            if (soldTo != null) {

                // bill to customer code
                ZYPEZDItemValueSetter.setValue(target.soldToCustLocCd, soldTo.billToCustCd);
            }

            // ship to
            ZYPEZDItemValueSetter.setValue(target.shipToCustAcctCd, source.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(target.dropShipFlg, ZYPConstant.FLG_OFF_N);
            SHIP_TO_CUSTTMsg shipTo = getShipToCustByLocNum(msg.glblCmpyCd.getValue(), source.shipToLocNum.getValue(), msg.slsDt.getValue());
            if (shipTo != null) {

                ZYPEZDItemValueSetter.setValue(target.shipToCustCd, shipTo.shipToCustCd);
                ZYPEZDItemValueSetter.setValue(target.shipToLocNm, shipTo.locNm);
                ZYPEZDItemValueSetter.setValue(target.shipToAddlLocNm, shipTo.addlLocNm);
                ZYPEZDItemValueSetter.setValue(target.shipToFirstLineAddr, shipTo.firstLineAddr);
                ZYPEZDItemValueSetter.setValue(target.shipToScdLineAddr, shipTo.scdLineAddr);
                ZYPEZDItemValueSetter.setValue(target.shipToThirdLineAddr, shipTo.thirdLineAddr);
                ZYPEZDItemValueSetter.setValue(target.shipToFrthLineAddr, shipTo.frthLineAddr);
                ZYPEZDItemValueSetter.setValue(target.shipToCtyAddr, shipTo.ctyAddr);
                ZYPEZDItemValueSetter.setValue(target.shipToStCd, shipTo.stCd);
                ZYPEZDItemValueSetter.setValue(target.shipToProvNm, shipTo.provNm);

                CNTYTMsg cnty = getCnty(msg.glblCmpyCd.getValue(), shipTo.cntyPk.getValue());
                if (cnty != null) {

                    ZYPEZDItemValueSetter.setValue(target.shipToCntyNm, cnty.cntyNm);
                }
                ZYPEZDItemValueSetter.setValue(target.shipToPostCd, shipTo.postCd);
                ZYPEZDItemValueSetter.setValue(target.shipToCtryCd, shipTo.ctryCd);
                ZYPEZDItemValueSetter.setValue(target.shipTo01RefCmntTxt, shipTo.firstRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(target.shipTo02RefCmntTxt, shipTo.scdRefCmntTxt);
            }

            // administrator person
            ZYPEZDItemValueSetter.setValue(target.adminPsnCd, source.adminPsnCd);

            // RDD form delivery info
            ZYPEZDItemValueSetter.setValue(target.rddDt, getRddDt(msg.dlvyInfo));

            // order type default value
            DS_ORD_TP_PROC_DFNTMsg dsOrdTpPrcDfn = getDsOrdTpPrcDfn(msg.glblCmpyCd.getValue(), source.dsOrdTpCd.getValue(), msg.slsDt.getValue());
            if (dsOrdTpPrcDfn != null) {

                // 2017/11/16 QC#20768 Del Start
//                ZYPEZDItemValueSetter.setValue(target.frtCondCd, dsOrdTpPrcDfn.frtCondCd);
//                FRT_CONDTMsg frtCond = getFrtCond(msg.glblCmpyCd.getValue(), dsOrdTpPrcDfn.frtCondCd.getValue());
//                if (frtCond != null) {
//
//                    ZYPEZDItemValueSetter.setValue(target.frtChrgToCd, frtCond.frtChrgToCd);
//                    ZYPEZDItemValueSetter.setValue(target.frtChrgMethCd, frtCond.frtChrgMethCd);
//                }
                // 2017/11/16 QC#20768 Del End
                ZYPEZDItemValueSetter.setValue(target.prcCatgCd, dsOrdTpPrcDfn.defPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(target.flPrcListCd, dsOrdTpPrcDfn.defPrcCatgCd);
                // 2018/01/04 QC#23323 Del Start
//                ZYPEZDItemValueSetter.setValue(target.shpgSvcLvlCd, dsOrdTpPrcDfn.defShpgSvcLvlCd);
                // 2018/01/04 QC#23323 Del End
                if (S21StringUtil.isEquals(dsOrdTpPrcDfn.defShpgSvcLvlCd.getValue(), FRT_COND.COLLECT)) {

                    ZYPEZDItemValueSetter.setValue(target.carrSvcLvlCd, dsOrdTpPrcDfn.defCarrSvcLvlCd);
                }
            }

            target.spclHdlgTpCd.clear();
            target.carrCd.clear();
            target.carrAcctNum.clear();

            // additional payment term cash discount code (from bill
            // to)
            ZYPEZDItemValueSetter.setValue(target.addPmtTermCashDiscCd, getPmtTermCashDiscCdFromBillTo(msg.glblCmpyCd.getValue(), target.billToCustCd.getValue(), target.billToCustAcctCd.getValue()));

            target.dsPmtMethCd.clear();
            target.prePmtChkNum.clear();
            target.prePmtAmt.clear();
            target.prePmtTpCd.clear();
            ZYPEZDItemValueSetter.setValue(target.prcBaseDt, msg.slsDt);
            target.negoDealAmt.clear();

            // price contract
            PRC_CONTRTMsg prcContr = getPrcContr(msg.glblCmpyCd.getValue(), source.prcContrPk.getValue());
            if (prcContr != null) {

                ZYPEZDItemValueSetter.setValue(target.prcContrNum, prcContr.prcContrNum);
            }

            ZYPEZDItemValueSetter.setValue(target.csmpContrNum, source.csmpContrNum);
            ZYPEZDItemValueSetter.setValue(target.dlrRefNum, source.dlrRefNum);
            ZYPEZDItemValueSetter.setValue(target.ordSgnDt, source.ordSgnDt);
            ZYPEZDItemValueSetter.setValue(target.aquNum, source.aquNum);
            ZYPEZDItemValueSetter.setValue(target.slsRepTocCd, source.slsRepTocCd);
            ZYPEZDItemValueSetter.setValue(target.loanPerDaysAot, source.loanPerDaysAot);
            ZYPEZDItemValueSetter.setValue(target.leaseCmpyPoNum, source.leaseCmpyPoNum);
            target.leaseTermCd.clear(); // never used
            ZYPEZDItemValueSetter.setValue(target.leasePmtFreqCd, source.leasePmtFreqCd);
            ZYPEZDItemValueSetter.setValue(target.leaseTermMthAot, source.leaseTermMthAot);
            // S21_NA#18806(L3#372) MOD START
            if (ZYPCommonFunc.hasValue(source.ordLogTpCd)) {
                ZYPEZDItemValueSetter.setValue(target.ordLogTpCd, source.ordLogTpCd);
            } else {
                ZYPEZDItemValueSetter.setValue(target.ordLogTpCd, getOrdLogTp(msg.glblCmpyCd.getValue(), source.dsOrdCatgCd.getValue(), source.dsOrdTpCd.getValue()));
            }
            // S21_NA#18806(L3#372) MOD END
            target.crRebilRsnCatgCd.clear();
            target.origOrdNum.clear();
            ZYPEZDItemValueSetter.setValue(target.sendInvFlg, ZYPConstant.FLG_ON_Y);
            target.reBillPairCpoOrdNum.clear();
            ZYPEZDItemValueSetter.setValue(target.ordHdrEdtblFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(target.leasePrchOptCd, source.leaseEndTermPrchOptCd);
            target.invCmntTxt.clear();
            target.leaseTotPmtAmt.clear();
            // ZYPEZDItemValueSetter.setValue(target.dclnSvcCd,
            // ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(target.dclnSvcCd, ZYPConstant.FLG_ON_1); // 2017/02/15
            // S21_NA#17628
            // Mod
            target.cpoOrdNum.clear();
            ZYPEZDItemValueSetter.setValue(target.maintOnlyFlg, getFlagValue(source.maintOnlyFlg.getValue()));

            targetList.add(target);

            // 2017/12/05 QC#22527 add start
            checkIsLoanFromHeader(target);
            // 2017/12/05 QC#22527 add end
        }

        return targetList;
    }

    // ****************************************************
    // create import order configuration
    // ****************************************************
    private List<DS_IMPT_ORD_CONFIGTMsg> createDsImptOrdConfig(S21ApiMessageMap msgMap, List<DS_IMPT_ORDTMsg> headerList) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_ORD_CONFIGTMsg> targetList = new ArrayList<DS_IMPT_ORD_CONFIGTMsg>();

        DS_IMPT_ORDTMsg header = getHeader(headerList);
        if (header == null) {

            setMsgIdWithParam(msgMap, null, NWZM1905E);
            return targetList;
        }

        for (int i = 0; i < msg.config.getValidCount(); i++) {

            NWZC225001_configPMsg source = msg.config.no(i);

            DS_IMPT_ORD_CONFIGTMsg target = new DS_IMPT_ORD_CONFIGTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdConfigPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_ORD_CONFIG_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, header.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(target.dsOrdPosnNum, source.dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue(target.configCatgCd, source.configCatgCd);
            ZYPEZDItemValueSetter.setValue(target.configTpCd, source.configTpCd);
            ZYPEZDItemValueSetter.setValue(target.svcConfigMstrPk, source.svcConfigMstrPk);
            // 2017/06/02 QC#18625 UPD START
            // ZYPEZDItemValueSetter.setValue(target.mdlId,
            // source.mdlId);
            if (BigDecimal.ZERO.compareTo(source.mdlId.getValue()) == 0) {
                target.mdlId.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(target.mdlId, source.mdlId);
            }
            // 2017/06/02 QC#18625 UPD E N D
            ZYPEZDItemValueSetter.setValue(target.mdlDescTxt, getMdlDescTxt(msg.glblCmpyCd.getValue(), source.mdlId.getValue()));

            // bill to (same as header value)
            ZYPEZDItemValueSetter.setValue(target.billToCustAcctCd, header.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(target.billToCustLocCd, header.billToCustCd);

            // ship to
            ZYPEZDItemValueSetter.setValue(target.shipToCustAcctCd, source.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(target.dropShipFlg, ZYPConstant.FLG_OFF_N);
            SHIP_TO_CUSTTMsg shipTo = getShipToCustByLocNum(msg.glblCmpyCd.getValue(), source.shipToLocNum.getValue(), msg.slsDt.getValue());
            if (shipTo != null) {

                ZYPEZDItemValueSetter.setValue(target.shipToCustLocCd, shipTo.shipToCustCd);
                ZYPEZDItemValueSetter.setValue(target.shipToLocNm, shipTo.locNm);
                ZYPEZDItemValueSetter.setValue(target.shipToAddlLocNm, shipTo.addlLocNm);
                ZYPEZDItemValueSetter.setValue(target.shipToFirstLineAddr, shipTo.firstLineAddr);
                ZYPEZDItemValueSetter.setValue(target.shipToScdLineAddr, shipTo.scdLineAddr);
                ZYPEZDItemValueSetter.setValue(target.shipToThirdLineAddr, shipTo.thirdLineAddr);
                ZYPEZDItemValueSetter.setValue(target.shipToFrthLineAddr, shipTo.frthLineAddr);
                ZYPEZDItemValueSetter.setValue(target.shipToFirstRefCmntTxt, shipTo.firstRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(target.shipToScdRefCmntTxt, shipTo.scdRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(target.shipToCtyAddr, shipTo.ctyAddr);
                ZYPEZDItemValueSetter.setValue(target.shipToStCd, shipTo.stCd);
                ZYPEZDItemValueSetter.setValue(target.shipToProvNm, shipTo.provNm);

                CNTYTMsg cnty = getCnty(msg.glblCmpyCd.getValue(), shipTo.cntyPk.getValue());
                if (cnty != null) {

                    ZYPEZDItemValueSetter.setValue(target.shipToCntyNm, cnty.cntyNm);
                }
                ZYPEZDItemValueSetter.setValue(target.shipToPostCd, shipTo.postCd);
                ZYPEZDItemValueSetter.setValue(target.shipToCtryCd, shipTo.ctryCd);
                ZYPEZDItemValueSetter.setValue(target.shipToAcctNm, getShipToAcctNm(msg.glblCmpyCd.getValue(), target.shipToCustLocCd.getValue()));
            }
            ZYPEZDItemValueSetter.setValue(target.configCratTs, header.ordSrcImptTs);
            ZYPEZDItemValueSetter.setValue(target.svcSlnSq, source.svcSlnSq);
            ZYPEZDItemValueSetter.setValue(target.svcSlnNm, source.svcSlnNm);

            target.somConfigId.clear();
            target.addrLbTxt.clear();

            ZYPEZDItemValueSetter.setValue(target.dclnSvcCd, source.dclnSvcCd);
            ZYPEZDItemValueSetter.setValue(target.svcSlnNum, source.svcSlnNum);
            ZYPEZDItemValueSetter.setValue(target.refOrdPosnNum, source.refOrdPosnNum);

            if (!S21StringUtil.isEquals(target.dclnSvcCd.getValue(), ZYPConstant.FLG_ON_1)) {

                ZYPEZDItemValueSetter.setValue(header.dclnSvcCd, ZYPConstant.FLG_OFF_0);
            }

            // QC#58230
            ZYPEZDItemValueSetter.setValue(target.prodCondCd, source.prodCondCd);
            if (!ZYPCommonFunc.hasValue(target.svcConfigMstrPk) && ZYPCommonFunc.hasValue(target.prodCondCd)) {
                ZYPEZDItemValueSetter.setValue(target.configTpCd, CONFIG_TP.NEW);
            }

            targetList.add(target);

            // 2017/12/05 QC#22527 add start
            // 2018/07/17 QC#26828 Mod Start
//            if (!isLoan && CONFIG_TP.TO_SALES_CONVERSION.equals(target.configTpCd)) {
            if (!isLoan && CONFIG_TP.TO_SALES_CONVERSION.equals(target.configTpCd.getValue())) {
            // 2018/07/17 QC#26828 Mod End
                isLoan = true;
            }
        }

        if (isLoan) {
            for (DS_IMPT_ORD_CONFIGTMsg configTMsg : targetList) {
                getOrigOrd(configTMsg);
            }
        }
        // 2017/12/05 QC#22527 add end

        return targetList;
    }

    // ****************************************************
    // create import order sales credit
    // ****************************************************
    private List<DS_IMPT_ORD_SLS_CRTMsg> createDsImptOrdSlsCr(S21ApiMessageMap msgMap, List<DS_IMPT_ORD_CONFIGTMsg> configList) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_ORD_SLS_CRTMsg> targetList = new ArrayList<DS_IMPT_ORD_SLS_CRTMsg>();

        // Mod Start 2017/07/25 QC#20114
//        DS_IMPT_ORD_SLS_CRTMsg slsCrForHeader = null;
        List<DS_IMPT_ORD_SLS_CRTMsg> slsCrForHeaderList = new ArrayList<DS_IMPT_ORD_SLS_CRTMsg>();
        // Mod End 2017/07/25 QC#20114
        String minPosition = null;
        for (int i = 0; i < msg.slsCr.getValidCount(); i++) {

            NWZC225001_slsCrPMsg source = msg.slsCr.no(i);

            DS_IMPT_ORD_CONFIGTMsg config = getConfig(configList, source.dsOrdPosnNum.getValue());
            if (config == null) {

                // error
                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, null, null), NWAM0702E, ORDER_SALES_CREDIT, ORDER_CONFIG);
                continue;
            }
            DS_IMPT_ORD_SLS_CRTMsg target = new DS_IMPT_ORD_SLS_CRTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdSlsCrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_ORD_SLS_CR_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, config.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdConfigPk, config.dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(target.slsRepTocCd, source.slsRepTocCd);
            ZYPEZDItemValueSetter.setValue(target.slsRepRoleTpCd, source.slsRepRoleTpCd);
            ZYPEZDItemValueSetter.setValue(target.slsRepCrPct, source.slsRepCrPct);
            ZYPEZDItemValueSetter.setValue(target.slsCrQuotFlg, source.slsCrQuotFlg);
            
            // 2019/01/25 QC#29980 Add Start
            String primRepRoleFlg = null;

            LINE_BIZ_ROLE_TPTMsg inRole = new LINE_BIZ_ROLE_TPTMsg();
            ZYPEZDItemValueSetter.setValue(inRole.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inRole.ezCancelFlag, ZYPConstant.FLG_OFF_0);
            ZYPEZDItemValueSetter.setValue(inRole.lineBizRoleTpCd, source.slsRepRoleTpCd);
            LINE_BIZ_ROLE_TPTMsg outRole = (LINE_BIZ_ROLE_TPTMsg) EZDTBLAccessor.findByKey(inRole);

            if (outRole != null) {
                primRepRoleFlg = outRole.primRepRoleFlg.getValue();
            }

            // 2019/07/17 QC#51647 Mod Start
//            if (ZYPCommonFunc.hasValue(primRepRoleFlg)) {
            if (ZYPCommonFunc.hasValue(primRepRoleFlg) && errorCheckFlg) {
            // 2019/07/17 QC#51647 Mod End
                if (ZYPConstant.FLG_ON_Y.equals(primRepRoleFlg)) {
                    if (!ZYPCommonFunc.hasValue(source.slsRepCrPct) || BigDecimal.ZERO.compareTo(source.slsRepCrPct.getValue()) == 0) {
                        setMsgIdWithParam(msgMap, null, NWAM0969E, source.slsRepTocCd.getValue());
                        continue;
                    }
                }
            }
            // 2019/01/25 QC#29980 Add End

            // percent
            // 2019/07/17 QC#51647 Mod Start
//            if (S21StringUtil.isEquals(source.slsCrQuotFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            if (S21StringUtil.isEquals(source.slsCrQuotFlg.getValue(), ZYPConstant.FLG_ON_Y) && errorCheckFlg) {
            // 2019/07/17 QC#51647 Mod Start

                if (!ZYPCommonFunc.hasValue(source.slsRepCrPct) || BigDecimal.ZERO.compareTo(source.slsRepCrPct.getValue()) == 0) {

                    // skip
                    String message = S21MessageFunc.clspGetMessage(NWAM2640W, new String[] {source.slsRepRoleTpCd.getValue() });
                    S21InfoLogOutput.println(String.format("%s config#:%s, unitId:%s", message, source.dsOrdPosnNum.getValue()));
                    continue;
                }
            }

            // for configuration
            targetList.add(target);

            // for header
            if (!ZYPCommonFunc.hasValue(source.dsOrdPosnNum)) {

                continue;
            }
            // Mod Start 2017/07/25 QC#20114
            // 2019/07/19 QC#51647 Mod Start
            BigDecimal slsRepCrPct = source.slsRepCrPct.getValue();
//            if (S21StringUtil.isEmpty(minPosition)) {
//            if (S21StringUtil.isEmpty(minPosition) && BigDecimal.ZERO.compareTo(slsRepCrPct) < 0) {
            if (S21StringUtil.isEmpty(minPosition) && (BigDecimal.ZERO.compareTo(slsRepCrPct) < 0 || ZYPConstant.FLG_ON_Y.equals(primRepRoleFlg))) {

                minPosition = source.dsOrdPosnNum.getValue();
//                slsCrForHeader = target;
                slsCrForHeaderList.add(target);
            }
//            if (String.format(minPosition, "%06s").compareTo(String.format(source.dsOrdPosnNum.getValue(), "%06s")) > 0) {
            else if (String.format(minPosition, "%06s").compareTo(String.format(source.dsOrdPosnNum.getValue(), "%06s")) > 0 //
                    && (BigDecimal.ZERO.compareTo(slsRepCrPct) < 0 || ZYPConstant.FLG_ON_Y.equals(primRepRoleFlg))) {
//                    && BigDecimal.ZERO.compareTo(slsRepCrPct) < 0) {

//                slsCrForHeader = target;
                slsCrForHeaderList.clear();
                slsCrForHeaderList.add(target);
            } else if (String.format(minPosition, "%06s").compareTo(String.format(source.dsOrdPosnNum.getValue(), "%06s")) == 0 //
                    && (BigDecimal.ZERO.compareTo(slsRepCrPct) < 0 || ZYPConstant.FLG_ON_Y.equals(primRepRoleFlg))) {
//                    && BigDecimal.ZERO.compareTo(slsRepCrPct) < 0) {
                slsCrForHeaderList.add(target);
            }
            // 2019/07/19 QC#51647 Mod End
            // Mod End 2017/07/25 QC#20114
        }

        // for header
        // Mod Start 2017/07/25 QC#20114
//        if (slsCrForHeader != null) {
        for (DS_IMPT_ORD_SLS_CRTMsg slsCrForHeader : slsCrForHeaderList) {

            DS_IMPT_ORD_SLS_CRTMsg target = new DS_IMPT_ORD_SLS_CRTMsg();
            EZDMsg.copy(slsCrForHeader, null, target, null);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdSlsCrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_ORD_SLS_CR_SQ));
            target.dsImptOrdConfigPk.clear();

            targetList.add(target);
        }
        // Mod End 2017/07/25 QC#20114

        return targetList;
    }

    private List<DS_IMPT_ORD_ISTL_INFOTMsg> createDsImptOrdIstlInfo(S21ApiMessageMap msgMap, List<DS_IMPT_ORD_CONFIGTMsg> configList, List<DS_IMPT_ORDTMsg> headerList) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_ORD_ISTL_INFOTMsg> targetList = new ArrayList<DS_IMPT_ORD_ISTL_INFOTMsg>();

        // 2017/02/15 S21_NA#17616 Add Start
        DS_IMPT_ORDTMsg header = getHeader(headerList);
        if (header == null) {

            setMsgIdWithParam(msgMap, null, NWZM1905E);
            return targetList;
        }
        // 2017/02/15 S21_NA#17616 Add End

        // 2017/08/15 S21_NA#20584 Add Start
        // if istlInfo is not enough then add istlInfo
        for (int i = 0; i < msg.config.getValidCount(); i++) {

            NWZC225001_configPMsg config = msg.config.no(i);
            if (!hasIstlInfo(msg.istlInfo, config.dsOrdPosnNum.getValue())) {

                NWZC225001_istlInfoPMsg addIstlInfo = msg.istlInfo.no(msg.istlInfo.getValidCount());
                ZYPEZDItemValueSetter.setValue(addIstlInfo.ordSrcRefNum, config.ordSrcRefNum);
                ZYPEZDItemValueSetter.setValue(addIstlInfo.dsOrdPosnNum, config.dsOrdPosnNum);
                // 2017/08/28 S21_NA#20740-1 Add Start
                ZYPEZDItemValueSetter.setValue(addIstlInfo.rqstIstlDt, msg.slsDt);
                // 2017/08/28 S21_NA#20740-1 Add End
                msg.istlInfo.setValidCount(msg.istlInfo.getValidCount() + 1);
            }
        }
        // 2017/08/15 S21_NA#20584 Add End

        for (int i = 0; i < msg.istlInfo.getValidCount(); i++) {

            NWZC225001_istlInfoPMsg source = msg.istlInfo.no(i);

            // 2017/08/28 S21_NA#20740-1 Add Start
            if(!ZYPCommonFunc.hasValue(source.rqstIstlDt)) {
                ZYPEZDItemValueSetter.setValue(source.rqstIstlDt, msg.slsDt);
            }
            // 2017/08/28 S21_NA#20740-1 Add End

            DS_IMPT_ORD_CONFIGTMsg config = getConfig(configList, source.dsOrdPosnNum.getValue());
            if (config == null) {

                // error
                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, null, null), NWAM0702E, ORDER_INSTALL_INFORMATION, ORDER_CONFIG);
                continue;
            }

            DS_IMPT_ORD_ISTL_INFOTMsg target = new DS_IMPT_ORD_ISTL_INFOTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdIstlInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_ORD_ISTL_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, config.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdConfigPk, config.dsImptOrdConfigPk);

            // 2017/02/15 S21_NA#17616 Add Start
            if (!ZYPCommonFunc.hasValue(source.svcIstlRuleNum) && ZYPCommonFunc.hasValue(config.mdlId)) {
                DS_MDLTMsg dsMdl = new DS_MDLTMsg();
                ZYPEZDItemValueSetter.setValue(dsMdl.glblCmpyCd, msg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsMdl.mdlId, config.mdlId);
                // 2017/09/25 QC#20799 Mod Start
                //dsMdl = (DS_MDLTMsg) S21FastTBLAccessor.findByKey(dsMdl);
                dsMdl = (DS_MDLTMsg) S21CacheTBLAccessor.findByKey(dsMdl);
                // 2017/09/25 QC#20799 Mod End

                if (dsMdl != null //
                        && S21StringUtil.isEquals(dsMdl.rgtnStsCd.getValue(), RGTN_STS.READY_FOR_ORDER_TAKING) //
                        && S21StringUtil.isEquals(dsMdl.mdlActvFlg.getValue(), ZYPConstant.FLG_ON_Y)) {

                    if (S21StringUtil.isEquals(config.configCatgCd.getValue(), CONFIG_CATG.OUTBOUND)) {
                        ZYPEZDItemValueSetter.setValue(target.svcIstlRuleNum, dsMdl.svcIstlRuleNum);
                    } else {
                        ZYPEZDItemValueSetter.setValue(target.svcIstlRuleNum, dsMdl.svcDeinsRuleNum);
                    }
                } else {
                    target.svcIstlRuleNum.clear();
                }
            } else { // 2017/02/15 S21_NA#17616 Add End
                ZYPEZDItemValueSetter.setValue(target.svcIstlRuleNum, source.svcIstlRuleNum);
            }

            // 2017/02/15 S21_NA#17616 Add Start
            DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfn = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfn.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfn.dsOrdTpCd, header.dsOrdTpCd);
            dsOrdTpProcDfn = (DS_ORD_TP_PROC_DFNTMsg) EZDTBLAccessor.findByKey(dsOrdTpProcDfn);

            if (dsOrdTpProcDfn != null) {
                ZYPEZDItemValueSetter.setValue(target.istlDivCd, dsOrdTpProcDfn.lineBizTpCd.getValue());
            } else { // 2017/02/15 S21_NA#17616 Add End
                target.istlDivCd.clear();
            }

            ZYPEZDItemValueSetter.setValue(target.istlTechCd, source.istlTechCd);
            // ZYPEZDItemValueSetter.setValue(target.rqstIstlDt, source.rqstInstlDt); // QC#20740
            //            target.rqstIstlTm.clear(); // QC#25137
            // 2019/02/14 S21_NA#30340 Mod Start
            // ZYPEZDItemValueSetter.setValue(target.rqstIstlTm, source.rqstIstlTm); // QC#25137 
            ZYPEZDItemValueSetter.setValue(target.rqstIstlTm, getChangeTime(source.rqstIstlTm.getValue(), config.shipToCtryCd.getValue(), config.shipToPostCd.getValue(), msg.slsDt.getValue()));
            // 2019/02/14 S21_NA#30340 Mod End
            target.istlAddlCmntTxt.clear();

            // 2019/10/25 QC#53993 Add Start
            boolean isApplyRule = false;
            // 2019/10/25 QC#53993 Add End

            // 2017/08/25 S21_NA#20627-1 Add Start
            if(ZYPCommonFunc.hasValue(header.dsOrdCatgCd)) {

                String dsOrdTpCd = null;
                String dsOrdRsnCd = null;

                if(ZYPCommonFunc.hasValue(header.dsOrdTpCd)) {
                    dsOrdTpCd = header.dsOrdTpCd.getValue();
                }

                if(ZYPCommonFunc.hasValue(header.dsOrdRsnCd)) {
                    dsOrdRsnCd = header.dsOrdRsnCd.getValue();
                }

                if (!isOrderRetailEquipment(header.glblCmpyCd.getValue(), header.dsOrdCatgCd.getValue(), dsOrdTpCd, dsOrdRsnCd)) {

                    // Outbound Config
                    if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, config.configCatgCd.getValue())) {

                        target.istlDivCd.clear();
                        ZYPEZDItemValueSetter.setValue(target.svcIstlRuleNum, SVC_ISTL_RULE_NUM_NO_INSTALL);

                        // 2019/10/25 QC#53993 Add Start
                        isApplyRule = true;
                        // 2019/10/25 QC#53993 Add End
                    }
                }
            }
            // 2017/08/25 S21_NA#20627-1 Add End

            // 2017/09/25 S21_NA#20799 Add Start
            DS_MDLTMsg dsMdl = new DS_MDLTMsg();
            ZYPEZDItemValueSetter.setValue(dsMdl.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsMdl.mdlId, config.mdlId);
            dsMdl = (DS_MDLTMsg) S21CacheTBLAccessor.findByKey(dsMdl);

            if (dsMdl == null) {

                if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, config.configCatgCd.getValue())) {

                    target.istlDivCd.clear();
                    ZYPEZDItemValueSetter.setValue(target.svcIstlRuleNum, SVC_ISTL_RULE_NUM_NO_INSTALL);

                    // 2019/10/25 QC#53993 Add Start
                    isApplyRule = true;
                    // 2019/10/25 QC#53993 Add End
                }
            }
            // 2017/09/25 S21_NA#20799 Add End

            // 2019/10/25 QC#53993 Add Start
            ZYPEZDItemValueSetter.setValue(target.istlBySvcPrvdPtyCd, source.istlBySvcPrvdPtyCd);
            ZYPEZDItemValueSetter.setValue(target.svcBySvcPrvdPtyCd, source.svcBySvcPrvdPtyCd);

            // To Be Install by -> Install Type, Install Division
            if (!isApplyRule){
                if (ZYPCommonFunc.hasValue(source.istlBySvcPrvdPtyCd)){
                    SVC_PRVD_PTYTMsg param = new SVC_PRVD_PTYTMsg();

                    ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, msg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(param.svcPrvdPtyCd, source.istlBySvcPrvdPtyCd);
                    ZYPEZDItemValueSetter.setValue(param.toBeIstlByFlg, ZYPConstant.FLG_ON_Y);

                    SVC_PRVD_PTYTMsg spp = (SVC_PRVD_PTYTMsg)S21FastTBLAccessor.findByKey(param);

                    if (spp != null) {

                        // Install Type
                        if (ZYPCommonFunc.hasValue(spp.svcIstlRuleNum)) {
                            ZYPEZDItemValueSetter.setValue(target.svcIstlRuleNum, spp.svcIstlRuleNum);
                        }

                        // Install Division
                        if (ZYPCommonFunc.hasValue(target.svcIstlRuleNum) && SVC_ISTL_RULE_NUM_NO_INSTALL.equals(target.svcIstlRuleNum.getValue())) {
                            target.istlDivCd.clear();
                        } else {
                            ZYPEZDItemValueSetter.setValue(target.istlDivCd, spp.lineBizTpCd);
                        }
                    }
                }
            }
            // 2019/10/25 QC#53993 Add End

            targetList.add(target);
        }

        return targetList;
    }

    private List<DS_IMPT_ORD_DELY_INFOTMsg> createDsImptOrdDelyInfo(S21ApiMessageMap msgMap, List<DS_IMPT_ORD_CONFIGTMsg> configList) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_ORD_DELY_INFOTMsg> targetList = new ArrayList<DS_IMPT_ORD_DELY_INFOTMsg>();

        for (int i = 0; i < msg.dlvyInfo.getValidCount(); i++) {

            NWZC225001_dlvyInfoPMsg source = msg.dlvyInfo.no(i);

            DS_IMPT_ORD_CONFIGTMsg config = getConfig(configList, source.dsOrdPosnNum.getValue());
            if (config == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, null, null), NWAM0702E, ORDER_DELIVERY_INFORMATION, ORDER_CONFIG);
                continue;
            }

            DS_IMPT_ORD_DELY_INFOTMsg target = new DS_IMPT_ORD_DELY_INFOTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdDelyInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_ORD_DELY_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, config.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdConfigPk, config.dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(target.dsDelyTpCd, DS_DELY_TP.INSTALLATION);
            // ZYPEZDItemValueSetter.setValue(target.rddDt, source.rddDt); // QC#20740
            // 2018/01/10 QC#18460 Mod Start
            // ZYPEZDItemValueSetter.setValue(target.opsFromHourMn, source.opsFromHourMn);
            // ZYPEZDItemValueSetter.setValue(target.opsToHourMn, source.opsToHourMn);
            ZYPEZDItemValueSetter.setValue(target.opsFromHourMn, getChangeTime(source.opsFromHourMn.getValue(), config.shipToCtryCd.getValue(), config.shipToPostCd.getValue(), msg.slsDt.getValue()));
            ZYPEZDItemValueSetter.setValue(target.opsToHourMn, getChangeTime(source.opsToHourMn.getValue(), config.shipToCtryCd.getValue(), config.shipToPostCd.getValue(), msg.slsDt.getValue()));
            // 2018/01/10 QC#18460 Mod End
            ZYPEZDItemValueSetter.setValue(target.loadDockAvalFlg, source.loadDockAvalFlg);
            ZYPEZDItemValueSetter.setValue(target.stairCrawReqFlg, source.stairCrawReqFlg);
            ZYPEZDItemValueSetter.setValue(target.stairCrawNum, source.stairCrawNum);
            ZYPEZDItemValueSetter.setValue(target.elevAvalFlg, source.elevAvalFlg);
            ZYPEZDItemValueSetter.setValue(target.delyAddlCmntTxt, source.delyAddlCmntTxt);

            targetList.add(target);
        }

        return targetList;
    }

    private List<DS_IMPT_ORD_SITE_SRVYTMsg> createDsImptOrdSiteSrvy(S21ApiMessageMap msgMap, List<DS_IMPT_ORD_CONFIGTMsg> configList) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_ORD_SITE_SRVYTMsg> targetList = new ArrayList<DS_IMPT_ORD_SITE_SRVYTMsg>();

        for (int i = 0; i < msg.siteSrvy.getValidCount(); i++) {

            NWZC225001_siteSrvyPMsg source = msg.siteSrvy.no(i);

            DS_IMPT_ORD_CONFIGTMsg config = getConfig(configList, source.dsOrdPosnNum.getValue());
            if (config == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, null, null), NWAM0702E, ORDER_SITE_SURVEY, ORDER_CONFIG);
                continue;
            }

            DS_IMPT_ORD_SITE_SRVYTMsg target = new DS_IMPT_ORD_SITE_SRVYTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdSiteSrvyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_ORD_SITE_SRVY_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, config.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdConfigPk, config.dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(target.cmpyInfoAptBldgNm, source.cmpyInfoAptBldgNm);
            ZYPEZDItemValueSetter.setValue(target.cmpyInfoFlNm, source.cmpyInfoFlNm);
            ZYPEZDItemValueSetter.setValue(target.cmpyInfoDeptNm, source.cmpyInfoDeptNm);
            ZYPEZDItemValueSetter.setValue(target.cmpyInfoCtacNm, source.cmpyInfoCtacNm);
            ZYPEZDItemValueSetter.setValue(target.cmpyInfoTelNum, source.cmpyInfoTelNum);
            ZYPEZDItemValueSetter.setValue(target.otsdStepNum, source.otsdStepNum);
            ZYPEZDItemValueSetter.setValue(target.insdStepNum, source.insdStepNum);
            ZYPEZDItemValueSetter.setValue(target.stairCrawReqFlg, source.stairCrawReqFlg);
            ZYPEZDItemValueSetter.setValue(target.flgtStairNum, source.flgtStairNum);
            ZYPEZDItemValueSetter.setValue(target.elevAvalFlg, source.elevAvalFlg);
            // 2018/01/10 QC#18460 Mod Start
            // ZYPEZDItemValueSetter.setValue(target.elevAvalFromHourMn, source.elevAvalFromHourMn);
            // ZYPEZDItemValueSetter.setValue(target.elevAvalToHourMn, source.elevAvalToHourMn);
            ZYPEZDItemValueSetter.setValue(target.elevAvalFromHourMn, getChangeTime(source.elevAvalFromHourMn.getValue(), config.shipToCtryCd.getValue(), config.shipToPostCd.getValue(), msg.slsDt.getValue()));
            ZYPEZDItemValueSetter.setValue(target.elevAvalToHourMn, getChangeTime(source.elevAvalToHourMn.getValue(), config.shipToCtryCd.getValue(), config.shipToPostCd.getValue(), msg.slsDt.getValue()));
            // 2018/01/10 QC#18460 Mod End
            ZYPEZDItemValueSetter.setValue(target.elevApptReqFlg, source.elevApptReqFlg);
            ZYPEZDItemValueSetter.setValue(target.elevCtacTelNum, source.elevCtacTelNum);
            ZYPEZDItemValueSetter.setValue(target.elevProtReqFlg, source.elevProtReqFlg);
            ZYPEZDItemValueSetter.setValue(target.elevWdt, source.elevWdt);
            ZYPEZDItemValueSetter.setValue(target.elevDepthNum, source.elevDepthNum);
            ZYPEZDItemValueSetter.setValue(target.elevCtacPsnNm, source.elevCtacPsnNm);
            ZYPEZDItemValueSetter.setValue(target.elevCapWt, source.elevCapWt);
            ZYPEZDItemValueSetter.setValue(target.elevDoorHgt, source.elevDoorHgt);
            ZYPEZDItemValueSetter.setValue(target.elevDoorWdt, source.elevDoorWdt);
            ZYPEZDItemValueSetter.setValue(target.stairAndLdgWdt, source.stairAndLdgWdt);
            ZYPEZDItemValueSetter.setValue(target.crdrWdt, source.crdrWdt);
            ZYPEZDItemValueSetter.setValue(target.doorWdt, source.doorWdt);
            ZYPEZDItemValueSetter.setValue(target.loadDockAvalFlg, source.loadDockAvalFlg);
            ZYPEZDItemValueSetter.setValue(target.loadDockHgt, source.loadDockHgt);
            ZYPEZDItemValueSetter.setValue(target.trctrAndTrailAccsFlg, source.trctrAndTrailAccsFlg);
            ZYPEZDItemValueSetter.setValue(target.bldgEntDoorHgt, source.bldgEntDoorHgt);
            ZYPEZDItemValueSetter.setValue(target.bldgEntDoorWdt, source.bldgEntDoorWdt);
            // 2018/01/10 QC#18460 Mod Start
            // ZYPEZDItemValueSetter.setValue(target.loadDockAvalFromHourMn, source.loadDockAvalFromHourMn);
            // ZYPEZDItemValueSetter.setValue(target.loadDockAvalToHourMn, source.loadDockAvalToHourMn);
            // ZYPEZDItemValueSetter.setValue(target.carrDelyTmHourMn, source.carrDelyTmHourMn);
            ZYPEZDItemValueSetter.setValue(target.loadDockAvalFromHourMn, getChangeTime(source.loadDockAvalFromHourMn.getValue(), config.shipToCtryCd.getValue(), config.shipToPostCd.getValue(), msg.slsDt.getValue()));
            ZYPEZDItemValueSetter.setValue(target.loadDockAvalToHourMn, getChangeTime(source.loadDockAvalToHourMn.getValue(), config.shipToCtryCd.getValue(), config.shipToPostCd.getValue(), msg.slsDt.getValue()));
            ZYPEZDItemValueSetter.setValue(target.carrDelyTmHourMn, getChangeTime(source.carrDelyTmHourMn.getValue(), config.shipToCtryCd.getValue(), config.shipToPostCd.getValue(), msg.slsDt.getValue()));
            // 2018/01/10 QC#18460 Mod End
            ZYPEZDItemValueSetter.setValue(target.delyTrnspOptCd, source.delyTrnspOptCd);
            ZYPEZDItemValueSetter.setValue(target.siteSrvyAddlCmntTxt, source.siteSrvyAddlCmntTxt);

            targetList.add(target);
        }

        return targetList;
    }

    private List<DS_IMPT_ORD_CTAC_PSNTMsg> createDsImptOrdCtacPsn(S21ApiMessageMap msgMap, List<DS_IMPT_ORD_CONFIGTMsg> configList) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_ORD_CTAC_PSNTMsg> targetList = new ArrayList<DS_IMPT_ORD_CTAC_PSNTMsg>();

        for (int i = 0; i < msg.ctacPsn.getValidCount(); i++) {

            NWZC225001_ctacPsnPMsg source = msg.ctacPsn.no(i);

            DS_IMPT_ORD_CONFIGTMsg config = getConfig(configList, source.dsOrdPosnNum.getValue());
            if (config == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, null, null), NWAM0702E, ORDER_CONTACT_PERSON, ORDER_CONFIG);
                continue;
            }

            DS_IMPT_ORD_CTAC_PSNTMsg target = new DS_IMPT_ORD_CTAC_PSNTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_ORD_CTAC_PSN_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, config.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdConfigPk, config.dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(target.ctacPsnTpCd, source.ctacPsnTpCd);
            ZYPEZDItemValueSetter.setValue(target.ctacPsnOvrdFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(target.ctacPsnFirstNm, source.ctacPsnFirstNm);
            ZYPEZDItemValueSetter.setValue(target.ctacPsnMidNm, source.ctacPsnMidNm);
            ZYPEZDItemValueSetter.setValue(target.ctacPsnLastNm, source.ctacPsnLastNm);
            ZYPEZDItemValueSetter.setValue(target.ctacPsnEmlAddr, source.ctacPsnEmlAddr);
            ZYPEZDItemValueSetter.setValue(target.ctacPsnExtnNum, source.ctacPsnExtnNum);
            ZYPEZDItemValueSetter.setValue(target.ctacPsnTelNum, source.ctacPsnTelNum);
            ZYPEZDItemValueSetter.setValue(target.ctacPsnFaxNum, source.ctacPsnFaxNum);
            ZYPEZDItemValueSetter.setValue(target.ctacPsnCmntTxt, source.ctacPsnCmntTxt);
            ZYPEZDItemValueSetter.setValue(target.ctacPsnCellPhoNum, source.ctacPsnCellPhoNum);
            ZYPEZDItemValueSetter.setValue(target.ctacPsnPk, source.ctacPsnPk);

            targetList.add(target);
        }

        return targetList;
    }

    // Mod Start 2018/08/21 QC#27489
    // QC#60971 Add ONBATCH_TYPE
    //private List<DS_IMPT_ORD_DTLTMsg> createDsImptOrdDtl(S21ApiMessageMap msgMap, List<DS_IMPT_ORDTMsg> headerList, List<DS_IMPT_ORD_CONFIGTMsg> configList) {
    private List<DS_IMPT_ORD_DTLTMsg> createDsImptOrdDtl(S21ApiMessageMap msgMap, List<DS_IMPT_ORDTMsg> headerList, List<DS_IMPT_ORD_CONFIGTMsg> configList, //
            List<BigDecimal> usedSvcMachMstrPkList, ONBATCH_TYPE onBatchType) {
        // Mod End 2018/08/21 QC#27489

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_ORD_DTLTMsg> targetList = new ArrayList<DS_IMPT_ORD_DTLTMsg>();

        DS_IMPT_ORDTMsg header = getHeader(headerList);
        if (header == null) {

            return targetList;
        }

        // 2017/12/05 QC#22527 add start
        // Del Start 2018/08/21 QC#27489
        //List<BigDecimal> usedSvcMachMstrPkList = new ArrayList<BigDecimal>(0); // QC#18360 Add
        // Del End 2018/08/21 QC#27489

        // Add Start 2018/08/21 QC#27489
        List<BigDecimal> lienAndRtrnSvcMachMstrPkList = new ArrayList<BigDecimal>(0); // QC#18360 Add

        for (int i = 0; i < msg.rtrnDtl.getValidCount(); i++) {
            // If loan conversion, receive Line and RMA
            // (Always only Line or only RMA)
            NWZC225001_rtrnDtlPMsg source = msg.rtrnDtl.no(i);
            if (ZYPCommonFunc.hasValue(source.svcMachMstrPk)) {
                lienAndRtrnSvcMachMstrPkList.add(source.svcMachMstrPk.getValue());
            }
        }
        // Add End 2018/08/21 QC#27489

        Map<BigDecimal, List<String>> origOrdMdseListMap = new HashMap<BigDecimal, List<String>>();

        List<String> lineValSet = new ArrayList<String>();
        Map<String, Object> valusetSsmParan = new HashMap<String, Object>();
        valusetSsmParan.put("glblCmpyCd", header.glblCmpyCd.getValue());
        valusetSsmParan.put("ordLineCtxTpCd", LOAN_CONV_LINE_CRAT); 
        valusetSsmParan.put("actionNm", LOAN_ORD_ACTION_LOAN_SELL);
        lineValSet = NWZC225001Query.getInstance().queryStringList("getLineValSet", valusetSsmParan); 
        // 2017/12/05 QC#22527 add end

        // QC#58230
        Map<String, String> prodCondSwhMap = new HashMap<String, String>();
        for (int i = 0; i < msg.config.getValidCount(); i++) {

            NWZC225001_configPMsg source = msg.config.no(i);

            if (!ZYPCommonFunc.hasValue(source.prodCondCd)) {
                continue;
            }

            Map<String, Object> prodConstSwhParam = new HashMap<String, Object>();
            prodConstSwhParam.put("glblCmpyCd", header.glblCmpyCd.getValue());
            prodConstSwhParam.put("dsCondConstGrpId", "DC_PROD_COND_CD");
            prodConstSwhParam.put("prodCondCd", source.prodCondCd.getValue());
            String swhCd = NWZC225001Query.getInstance().queryString("getProdCondCdSwh", prodConstSwhParam);

            if (ZYPCommonFunc.hasValue(swhCd)) {
                prodCondSwhMap.put(source.dsOrdPosnNum.getValue(), swhCd);
            }
        }
        
        for (int i = 0; i < msg.dtl.getValidCount(); i++) {

            NWZC225001_dtlPMsg source = msg.dtl.no(i);

            DS_IMPT_ORD_CONFIGTMsg config = getConfig(configList, source.dsOrdPosnNum.getValue());
            if (config == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, null, null), NWAM0702E, ORDER_LINE, ORDER_CONFIG);
                continue;
            }

            DS_IMPT_ORD_DTLTMsg target = new DS_IMPT_ORD_DTLTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_ORD_DTL_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, config.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(target.ordSrcRefLineNum, source.ordSrcRefLineNum);
            ZYPEZDItemValueSetter.setValue(target.ordSrcRefLineSubNum, "001");

            // item
            ZYPEZDItemValueSetter.setValue(target.mdseCd, source.mdseCd);
            MDSETMsg mdse = getMdse(msg.glblCmpyCd.getValue(), source.mdseCd.getValue());
            if (mdse != null) {

                ZYPEZDItemValueSetter.setValue(target.mdseNm, mdse.mdseNm);
            }
            target.origMdseCd.clear();
            target.origMdseNm.clear();
            target.setMdseCd.clear();

            // ship to
            ZYPEZDItemValueSetter.setValue(target.dropShipFlg, config.dropShipFlg);
            ZYPEZDItemValueSetter.setValue(target.shipToCustCd, config.shipToCustLocCd);
            ZYPEZDItemValueSetter.setValue(target.shipToLocNm, config.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(target.shipToAddlLocNm, config.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(target.shipToFirstLineAddr, config.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(target.shipToScdLineAddr, config.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(target.shipToThirdLineAddr, config.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(target.shipToFrthLineAddr, config.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(target.shipToFirstRefCmntTxt, config.shipToFirstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(target.shipToScdRefCmntTxt, config.shipToScdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(target.shipToCtyAddr, config.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(target.shipToStCd, config.shipToStCd);
            ZYPEZDItemValueSetter.setValue(target.shipToProvNm, config.shipToProvNm);
            ZYPEZDItemValueSetter.setValue(target.shipToCntyNm, config.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(target.shipToPostCd, config.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(target.shipToCtryCd, config.shipToCtryCd);

            // quantity
            ZYPEZDItemValueSetter.setValue(target.ordQty, source.ordQty);
            ZYPEZDItemValueSetter.setValue(target.ordCustUomQty, source.ordQty);

            target.invtyLocCd.clear();
            ZYPEZDItemValueSetter.setValue(target.prcCatgCd, header.prcCatgCd);
            ZYPEZDItemValueSetter.setValue(target.flPrcListCd, header.flPrcListCd);

            // derive by pricing
            target.dealPrcListPrcAmt.clear();
            target.funcPrcListPrcAmt.clear();

            // deal
            ZYPEZDItemValueSetter.setValue(target.entDealNetUnitPrcAmt, source.entDealNetUnitPrcAmt);
            // 2017/09/01 QC#20792-1 Mod Start
            if(ZYPCommonFunc.hasValue(source.entDealNetUnitPrcAmt) && ZYPCommonFunc.hasValue(source.ordQty)) {

                ZYPEZDItemValueSetter.setValue(target.cpoDtlDealNetAmt, source.entDealNetUnitPrcAmt.getValue().multiply(source.ordQty.getValue()));
                ZYPEZDItemValueSetter.setValue(target.cpoDtlDealSlsAmt, source.entDealNetUnitPrcAmt.getValue().multiply(source.ordQty.getValue()));
            } else {

                ZYPEZDItemValueSetter.setValue(target.cpoDtlDealNetAmt, source.entDealNetUnitPrcAmt);
                ZYPEZDItemValueSetter.setValue(target.cpoDtlDealSlsAmt, source.entDealNetUnitPrcAmt);
            }
            // 2017/09/01 QC#20792-1 Mod End

            // function
            ZYPEZDItemValueSetter.setValue(target.entFuncNetUnitPrcAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), CCY.US_DOLLAR, target.entDealNetUnitPrcAmt.getValue(), msg.slsDt.getValue()));
            ZYPEZDItemValueSetter.setValue(target.cpoDtlFuncNetAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), CCY.US_DOLLAR, target.cpoDtlDealNetAmt.getValue(), msg.slsDt.getValue()));
            ZYPEZDItemValueSetter.setValue(target.cpoDtlFuncSlsAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), CCY.US_DOLLAR, target.cpoDtlDealSlsAmt.getValue(), msg.slsDt.getValue()));

            ZYPEZDItemValueSetter.setValue(target.prcBaseDt, msg.slsDt);
            ZYPEZDItemValueSetter.setValue(target.ccyCd, CCY.US_DOLLAR);

            target.exchRate.clear();

            ZYPEZDItemValueSetter.setValue(target.rddDt, getRddDt(msg.dlvyInfo, source.dsOrdPosnNum.getValue()));
            target.rsdDt.clear();

            // 2017/11/21 QC#21360 Del Start
//            ZYPEZDItemValueSetter.setValue(target.shipCpltCd, ZYPConstant.FLG_OFF_N);
            // 2017/11/21 QC#21360 Del End
            ZYPEZDItemValueSetter.setValue(target.uomCpltFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(target.stkStsCd, STK_STS.GOOD);

            target.slsRepOrSlsTeamTocCd.clear();
            target.custMdseCd.clear();

            ZYPEZDItemValueSetter.setValue(target.custUomCd, PKG_UOM.EACH);

            target.ediStsCd.clear();
            target.ediNum.clear();
            target.ediSubNum.clear();
            target.origCpoOrdNum.clear();
            target.origInvNum.clear();

            ZYPEZDItemValueSetter.setValue(target.setItemShipCpltFlg, ZYPConstant.FLG_OFF_N);

            ZYPEZDItemValueSetter.setValue(target.dsOrdPosnNum, source.dsOrdPosnNum);

            // unit net weight
            ZYPEZDItemValueSetter.setValue(target.unitNetWt, getUnitNetWt(msg.glblCmpyCd.getValue(), target.mdseCd.getValue(), target.custUomCd.getValue(), target.ordCustUomQty.getValue()));

            ZYPEZDItemValueSetter.setValue(target.configItemFlg, ZYPConstant.FLG_OFF_N);

            ZYPEZDItemValueSetter.setValue(target.baseCmptFlg, getFlagValue(source.baseCmptFlg.getValue()));

            // customer install flag from install information
            ZYPEZDItemValueSetter.setValue(target.custIstlFlg, getCustIstlFlg(msg.glblCmpyCd.getValue(), msg.istlInfo, source.dsOrdPosnNum.getValue()));

            ZYPEZDItemValueSetter.setValue(target.svcConfigMstrPk, config.svcConfigMstrPk);

            // service machine master by serial.
            // QC#22658 2017/11/20 Mod Start
            // ZYPEZDItemValueSetter.setValue(target.svcMachMstrPk, getSvcMachMstrPkBySerNum(msg.glblCmpyCd.getValue(), source.serNum.getValue(), msg.slsDt.getValue()));
            ZYPEZDItemValueSetter.setValue(target.svcMachMstrPk, getSvcMachMstrPkBySerNum(msg.glblCmpyCd.getValue(), source.mdseCd.getValue(), source.serNum.getValue(), msg.slsDt.getValue()));
            // QC#22658 2017/11/20 Mod End

            ZYPEZDItemValueSetter.setValue(target.serNum, source.serNum);

            // 2017/09/28 S21_NA#21384 Add Start
            if (!isLoanUpgradeOrAddAsry(config)) {
                // 2017/09/28 S21_NA#21384 Add End

                // QC#18360 Add Start
                BigDecimal svcMachMstrPk = null;
                if (!ZYPCommonFunc.hasValue(target.serNum) && !ZYPCommonFunc.hasValue(target.svcMachMstrPk)) {
                    if (ZYPCommonFunc.hasValue(config.svcConfigMstrPk.getValue()) && ZYPCommonFunc.hasValue(target.mdseCd)) {

                        // Mod Start 2018/08/21 QC#27489
                        //SVC_MACH_MSTRTMsg tMsg = getSvcMachMstrByConfigAndMdse(msg.glblCmpyCd.getValue(), msg.slsDt.getValue(), config.svcConfigMstrPk.getValue(), target.mdseCd.getValue(), usedSvcMachMstrPkList);
                        SVC_MACH_MSTRTMsg tMsg = getSvcMachMstrByConfigAndMdse(msg.glblCmpyCd.getValue(), msg.slsDt.getValue(), config.svcConfigMstrPk.getValue(), target.mdseCd.getValue(), lienAndRtrnSvcMachMstrPkList);
                        // Mod End 2018/08/21 QC#27489
                        if (tMsg != null) {
                            ZYPEZDItemValueSetter.setValue(target.svcMachMstrPk, tMsg.svcMachMstrPk);
                            ZYPEZDItemValueSetter.setValue(target.serNum, tMsg.serNum);
                        }
                    }

                } else if (ZYPCommonFunc.hasValue(target.serNum)) {
                    // QC#22658 2017/11/20 Mod Start
                    //svcMachMstrPk = getSvcMachMstrPkBySerNum(msg.glblCmpyCd.getValue(), target.serNum.getValue(), msg.slsDt.getValue());
                    svcMachMstrPk = getSvcMachMstrPkBySerNum(msg.glblCmpyCd.getValue(), target.mdseCd.getValue(), target.serNum.getValue(), msg.slsDt.getValue());
                    ZYPEZDItemValueSetter.setValue(target.svcMachMstrPk, svcMachMstrPk);
                    // QC#22658 2017/11/20 Mod End

                } else if (ZYPCommonFunc.hasValue(target.svcMachMstrPk)) {
                    svcMachMstrPk = target.svcMachMstrPk.getValue();
                }

                if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                    usedSvcMachMstrPkList.add(svcMachMstrPk);
                    // Add Start 2018/08/21 QC#27489
                    lienAndRtrnSvcMachMstrPkList.add(svcMachMstrPk);
                    // Add End 2018/08/21 QC#27489
                } else if (ZYPCommonFunc.hasValue(target.svcMachMstrPk)) {
                    usedSvcMachMstrPkList.add(target.svcMachMstrPk.getValue());
                    // Add Start 2018/08/21 QC#27489
                    lienAndRtrnSvcMachMstrPkList.add(target.svcMachMstrPk.getValue());
                    // Add End 2018/08/21 QC#27489
                }
                // QC#18360 Add End

                // 2017/09/28 S21_NA#21384 Add Start
            }
            // 2017/09/28 S21_NA#21384 Add End

            target.dsContrNum.clear();
            target.dsContrSqNum.clear();
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdConfigPk, config.dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(target.dsOrdLineCatgCd, source.dsOrdLineCatgCd);
            target.ordLineSrcCd.clear();
            target.rtlWhCd.clear();

            if (prodCondSwhMap.containsKey(source.dsOrdPosnNum.getValue()) && ZYPConstant.FLG_ON_Y.equals(source.imptLineFlg.getValue())) {

                ZYPEZDItemValueSetter.setValue(target.rtlSwhCd, prodCondSwhMap.get(source.dsOrdPosnNum.getValue()));

                // QC#60971
                if (("NEW".equals(config.prodCondCd.getValue()) || "REMAN".equals(config.prodCondCd.getValue()))) {
                    // Def WH API
                    NWZC180001PMsg pMsg = new NWZC180001PMsg();

                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, header.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_OTBD);
                    ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, header.dsOrdCatgCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, header.dsOrdTpCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, target.mdseCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.postCd, header.shipToPostCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.ordQty, target.ordQty);

                    // call NWZC1800 Default WH API
                    new NWZC180001().execute(pMsg, onBatchType);

                    if (!(S21ApiUtil.getXxMsgList(pMsg).size() > 0)) {
                        String defWhNew = "NEW";
                        String rtlSwhCd = pMsg.rtlSwhCd.getValue();
                        if (ZYPCommonFunc.hasValue(rtlSwhCd)) {
                            SWHTMsg tMsg = new SWHTMsg();
                            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, header.glblCmpyCd);
                            ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, rtlSwhCd);
        
                            tMsg = (SWHTMsg) S21CacheTBLAccessor.findByKey(tMsg);
                            if (tMsg != null) {
                                int costPct = tMsg.mdseInvtyCostPct.getValueInt();
                                if (100 == costPct) {
                                    ZYPEZDItemValueSetter.setValue(target.rtlSwhCd, tMsg.rtlSwhCd);
                                } else {
                                    ZYPEZDItemValueSetter.setValue(target.rtlSwhCd, defWhNew);
                                }
                            } else {
                                ZYPEZDItemValueSetter.setValue(target.rtlSwhCd, defWhNew);
                            }
                        } else {
                            ZYPEZDItemValueSetter.setValue(target.rtlSwhCd, defWhNew);
                        }
                    }
                }

                if (mdse != null && ZYPConstant.FLG_OFF_N.equals(mdse.invtyCtrlFlg.getValue()) && !MDSE_TP.SET.equals(mdse.mdseTpCd.getValue())) {

                    target.rtlSwhCd.clear();
                // QC#58230-1
                } else if (mdse != null) {

                    Map<String, Object> prodConstSwhSplyParam = new HashMap<String, Object>();
                    prodConstSwhSplyParam.put("glblCmpyCd", header.glblCmpyCd.getValue());
                    prodConstSwhSplyParam.put("dsCondConstGrpId", "DC_PROD_COND_CD");
                    //prodConstSwhSplyParam.put("prodCondCd", "SUPPLY");
                    String prodCondCd = ZYPCodeDataUtil.getName(COA_PROJ.class, header.glblCmpyCd.getValue(), mdse.coaMdseTpCd.getValue());
                    prodConstSwhSplyParam.put("prodCondCd", prodCondCd);
                    String swhCd = NWZC225001Query.getInstance().queryString("getProdCondCdSwh", prodConstSwhSplyParam);

                    if (ZYPCommonFunc.hasValue(swhCd)) {
                        ZYPEZDItemValueSetter.setValue(target.rtlSwhCd, swhCd);
                    }
                }

            } else {
                target.rtlSwhCd.clear();
            }

            target.refCpoDtlLineNum.clear();
            target.refCpoDtlLineSubNum.clear();
            target.dplyLineRefNum.clear();
            target.crRebilCd.clear();

            // supply type
            ZYPEZDItemValueSetter.setValue(target.splyTpCd, source.splyTpCd);
            // 2019/10/04 S21_NA#51372 Mod Start
            //ZYPEZDItemValueSetter.setValue(target.splyNm, source.splyNm);
            ZYPEZDItemValueSetter.setValue(target.prntVndNm, source.splyNm);
            // 2019/10/04 S21_NA#51372 Mod End
            ZYPEZDItemValueSetter.setValue(target.splyFirstAddr, source.splyFirstAddr);
            ZYPEZDItemValueSetter.setValue(target.splyCtyAddr, source.splyCtyAddr);
            ZYPEZDItemValueSetter.setValue(target.splyStCd, source.splyStCd);
            ZYPEZDItemValueSetter.setValue(target.splyPostCd, source.splyPostCd);

            // CSMP
            ZYPEZDItemValueSetter.setValue(target.csmpContrNum, source.csmpContrNum);
            ZYPEZDItemValueSetter.setValue(target.dlrRefNum, source.dlrRefNum);
            target.csmpPrcListCd.clear();

            target.rntlTrmnDt.clear();

            // billing attribute
            target.firstBllgAttrbNm.clear();
            target.firstBllgAttrbValTxt.clear();
            target.scdBllgAttrbNm.clear();
            target.scdBllgAttrbValTxt.clear();
            target.thirdBllgAttrbNm.clear();
            target.thirdBllgAttrbValTxt.clear();
            target.frthBllgAttrbNm.clear();
            target.frthBllgAttrbValTxt.clear();
            target.fifthBllgAttrbNm.clear();
            target.fifthBllgAttrbValTxt.clear();
            target.sixthBllgAttrbNm.clear();
            target.sixthBllgAttrbValTxt.clear();

            // substitute
            target.sbstMdseCd.clear();

            // super session lock Y
            ZYPEZDItemValueSetter.setValue(target.supdLockFlg, ZYPConstant.FLG_ON_Y);

            target.prcListEquipConfigNum.clear();

            ZYPEZDItemValueSetter.setValue(target.imptLineFlg, source.imptLineFlg);

            // COA
            target.coaCmpyCd.clear();
            target.coaAfflCd.clear();
            target.coaBrCd.clear();
            target.coaChCd.clear();
            target.coaCcCd.clear();
            target.coaAcctCd.clear();
            target.coaProjCd.clear();
            target.coaExtnCd.clear();
            target.coaProdCd.clear();

            ZYPEZDItemValueSetter.setValue(target.preExistFlg, getFlagValue(source.preExistFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(target.finItemLineFlg, getFlagValue(source.finItemLineFlg.getValue()));

            // 2017/12/05 QC#22527 add start
            String leaseMdseCd = ZYPCodeDataUtil.getVarCharConstValue(LEASE_BYOT_MDSE_CD, target.glblCmpyCd.getValue());
            String dsOrdLineCatgCd = null;
            boolean isLoanConvAddFlg = true;

            // lease
            if (ZYPCommonFunc.hasValue(leaseMdseCd) && leaseMdseCd.equals(target.mdseCd.getValue())) {
                // 2019/08/16 QC#52669 Mod start
                //dsOrdLineCatgCd = DS_ORD_LINE_CATG.MERCHANDISE;
                dsOrdLineCatgCd = DS_ORD_LINE_CATG.LEASE_BUYOUT;
                // 2019/08/16 QC#52669 Mod End

            } else if (isLoan && CONFIG_TP.TO_SALES_CONVERSION.equals(config.configTpCd.getValue())) {
                // to Sales
                if (!origOrdMdseListMap.containsKey(config.dsImptOrdConfigPk.getValue())) {
                    origOrdMdseListMap.put(config.dsImptOrdConfigPk.getValue(), getOrigOrdMdseList(config));
                }

                List<String> origOrdMdseList = origOrdMdseListMap.get(config.dsImptOrdConfigPk.getValue());
                String targetMdseCd = target.mdseCd.getValue();
                if (targetMdseCd.length() > MDSE_CD_SHORT_LENGTH) {
                    targetMdseCd = targetMdseCd.substring(0, MDSE_CD_SHORT_LENGTH);
                }
                // addLine or ConversionLine
                for (String mdseCd : origOrdMdseList) {

                    if (mdseCd.equals(targetMdseCd)) {
                        isLoanConvAddFlg = false;
                        origOrdMdseList.remove(mdseCd);

                        // IB or NotIB
                        boolean isIB = false;
                        if (!ZYPCommonFunc.hasValue(configOrigOrdMap.get(config.dsImptOrdConfigPk.getValue()))) {
                            break;
                        }
                        SVC_MACH_MSTRTMsg condition = new SVC_MACH_MSTRTMsg();
                        condition.setSQLID("019");
                        condition.setConditionValue("glblCmpyCd01", target.glblCmpyCd.getValue());
                        condition.setConditionValue("svcConfigMstrPk01", config.svcConfigMstrPk.getValue());
                        condition.setConditionValue("cpoOrdNum01", configOrigOrdMap.get(config.dsImptOrdConfigPk.getValue()));

                        SVC_MACH_MSTRTMsgArray tmsgArray = (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(condition);
                        if (tmsgArray == null || tmsgArray.length() <= 0) {
                            break;
                        }

                        for (int j = 0; j < tmsgArray.length(); j++) {

                            SVC_MACH_MSTRTMsg svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) tmsgArray.get(j);
                            String svcMdseCd = svcMachMstrTMsg.mdseCd.getValue();

                            if (mdseCd.length() == MDSE_CD_SHORT_LENGTH && svcMdseCd.length() > MDSE_CD_SHORT_LENGTH) {
                                svcMdseCd = svcMdseCd.substring(0, MDSE_CD_SHORT_LENGTH);
                            }

                            if (mdseCd.equals(svcMdseCd)) {
                                // 2018/07/18 S21_NA#26828 Mod Start
//                                isIB = true;
//                                dsOrdLineCatgCd = lineValSet.get(0);
//                                break;
                                CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, svcMachMstrTMsg.glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, svcMachMstrTMsg.cpoOrdNum);
                                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, svcMachMstrTMsg.cpoDtlLineNum);
                                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, svcMachMstrTMsg.cpoDtlLineSubNum);
                                cpoDtlTMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(cpoDtlTMsg);
                                if (cpoDtlTMsg != null && DS_ORD_LINE_CATG.LOAN_ORDERS.equals(cpoDtlTMsg.dsOrdLineCatgCd.getValue())) {
                                    isIB = true;
                                    dsOrdLineCatgCd = lineValSet.get(0);
                                    break;
                                }
                                // 2018/07/18 S21_NA#26828 Mod End
                            }
                        }

                        if (!isIB && lineValSet.size() >= 2) {
                            dsOrdLineCatgCd = lineValSet.get(1);
                        }
                        break;
                    }
                }

                if (isLoanConvAddFlg) {
                    dsOrdLineCatgCd = DS_ORD_LINE_CATG.MERCHANDISE;
                }

            } else if (isLoan || !ZYPCommonFunc.hasValue(configOrigOrdMap.get(config.dsImptOrdConfigPk.getValue()))) {
                // new Loan Order
                NWXC220001Result<Map<String, Object>> result = NWXC220001.getPrimDsOrdLineCatg(target.glblCmpyCd.getValue(), header.dsOrdTpCd.getValue(), msg.slsDt.getValue(), DS_ORD_LINE_DRCTN.OUTBOUND);
                dsOrdLineCatgCd = (String) result.getResultObject().get("DS_ORD_LINE_CATG_CD");

                ALL_MDSE_VTMsg allMdseV = getAllMdseV(target.glblCmpyCd.getValue(), target.mdseCd.getValue());

                if (allMdseV != null) {
                    if (!ZYPConstant.FLG_ON_Y.equals(allMdseV.invtyCtrlFlg.getValue()) || !ZYPConstant.FLG_ON_Y.equals(allMdseV.instlBaseCtrlFlg.getValue())) {
                        dsOrdLineCatgCd = DS_ORD_LINE_CATG.MERCHANDISE;
                    }
                }

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", target.glblCmpyCd.getValue());
                ssmParam.put("mdseCd", target.mdseCd.getValue() + PERCENT);
                ssmParam.put("mdseTpCtxTpCd", MDSE_TP_CTX_TP.DEFAULT_LINE_CATEGORY);
                String mdseValSetLineCatgCd = NWZC225001Query.getInstance().queryString("getMdseValSet", ssmParam);

                if (ZYPCommonFunc.hasValue(mdseValSetLineCatgCd)) {
                    dsOrdLineCatgCd = mdseValSetLineCatgCd;
                }
            } else {
                // other (default)
                NWXC220001Result<Map<String, Object>> result = NWXC220001.getPrimDsOrdLineCatg(target.glblCmpyCd.getValue(), header.dsOrdTpCd.getValue(), msg.slsDt.getValue(), DS_ORD_LINE_DRCTN.OUTBOUND);
                dsOrdLineCatgCd = (String) result.getResultObject().get("DS_ORD_LINE_CATG_CD");
            }
            // Add Start 2019/07/09 QC#51277
            DS_ORD_LINE_PROC_DFNTMsg lineCatgChkTMsg = new DS_ORD_LINE_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(lineCatgChkTMsg.glblCmpyCd, target.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(lineCatgChkTMsg.dsOrdTpCd, header.dsOrdTpCd);
            ZYPEZDItemValueSetter.setValue(lineCatgChkTMsg.dsOrdLineCatgCd, dsOrdLineCatgCd);
            lineCatgChkTMsg = (DS_ORD_LINE_PROC_DFNTMsg) EZDTBLAccessor.findByKey(lineCatgChkTMsg);
            if (lineCatgChkTMsg == null) {
                // set prime line category
                NWXC220001Result<Map<String, Object>> result = NWXC220001.getPrimDsOrdLineCatg(target.glblCmpyCd.getValue(), header.dsOrdTpCd.getValue(), msg.slsDt.getValue(), DS_ORD_LINE_DRCTN.OUTBOUND);
                dsOrdLineCatgCd = (String) result.getResultObject().get("DS_ORD_LINE_CATG_CD");
            }
            // Add End 2019/07/09 QC#51277
            ZYPEZDItemValueSetter.setValue(target.dsOrdLineCatgCd, dsOrdLineCatgCd);
            // 2017/12/05 QC#22527 add end

            // Add Start 2018/08/21 QC#27489
            if (!isLoanUpgradeOrAddAsry(config)) {
                int ordQty = target.ordQty.getValueInt();

                if (!ZYPCommonFunc.hasValue(target.serNum) && ordQty > 1) {
                    if (ZYPCommonFunc.hasValue(config.svcConfigMstrPk.getValue()) && ZYPCommonFunc.hasValue(target.mdseCd)) {

                        // Get all service machine master PK
                        List<Map<String, Object>> svcMachMstrPkList = getAllSvcMachMstrByConfigAndMdse(msg.glblCmpyCd.getValue(), msg.slsDt.getValue(), //
                                config.svcConfigMstrPk.getValue(), target.mdseCd.getValue(), lienAndRtrnSvcMachMstrPkList);

                        int loopCnt = ordQty - 1;
                        if (loopCnt > svcMachMstrPkList.size()) {
                            loopCnt = svcMachMstrPkList.size();
                        }

                        boolean svcMachMstrPkFlag = false;
                        if (loopCnt > 0) {
                            int cnt = 0;

                            svcMachMstrPkFlag = true;

                            ZYPEZDItemValueSetter.setValue(target.ordQty, BigDecimal.ONE);
                            ZYPEZDItemValueSetter.setValue(target.ordCustUomQty, BigDecimal.ONE);

                            target.cpoDtlDealNetAmt.clear();
                            target.cpoDtlDealSlsAmt.clear();
                            target.cpoDtlFuncNetAmt.clear();
                            target.cpoDtlFuncSlsAmt.clear();
                            ZYPEZDItemValueSetter.setValue(target.cpoDtlDealNetAmt, source.entDealNetUnitPrcAmt.getValue().multiply(target.ordQty.getValue()));
                            ZYPEZDItemValueSetter.setValue(target.cpoDtlDealSlsAmt, source.entDealNetUnitPrcAmt.getValue().multiply(target.ordQty.getValue()));
                            ZYPEZDItemValueSetter.setValue(target.cpoDtlFuncNetAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), CCY.US_DOLLAR, target.cpoDtlDealNetAmt.getValue(), msg.slsDt.getValue()));
                            ZYPEZDItemValueSetter.setValue(target.cpoDtlFuncSlsAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), CCY.US_DOLLAR, target.cpoDtlDealSlsAmt.getValue(), msg.slsDt.getValue()));

                            while (cnt < loopCnt) {
                                DS_IMPT_ORD_DTLTMsg copyTarget = new DS_IMPT_ORD_DTLTMsg();
                                EZDMsg.copy(target, null, copyTarget, null);

                                copyTarget.dsImptOrdDtlPk.clear();
                                copyTarget.cpoDtlDealNetAmt.clear();
                                copyTarget.cpoDtlDealSlsAmt.clear();
                                copyTarget.cpoDtlFuncNetAmt.clear();
                                copyTarget.cpoDtlFuncSlsAmt.clear();
                                ZYPEZDItemValueSetter.setValue(copyTarget.dsImptOrdDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_ORD_DTL_SQ));
                                ZYPEZDItemValueSetter.setValue(copyTarget.cpoDtlDealNetAmt, source.entDealNetUnitPrcAmt.getValue().multiply(copyTarget.ordQty.getValue()));
                                ZYPEZDItemValueSetter.setValue(copyTarget.cpoDtlDealSlsAmt, source.entDealNetUnitPrcAmt.getValue().multiply(copyTarget.ordQty.getValue()));
                                ZYPEZDItemValueSetter.setValue(copyTarget.cpoDtlFuncNetAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), CCY.US_DOLLAR, copyTarget.cpoDtlDealNetAmt.getValue(), msg.slsDt.getValue()));
                                ZYPEZDItemValueSetter.setValue(copyTarget.cpoDtlFuncSlsAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), CCY.US_DOLLAR, copyTarget.cpoDtlDealSlsAmt.getValue(), msg.slsDt.getValue()));
                                BigDecimal svcMachMstrPk = (BigDecimal) svcMachMstrPkList.get(cnt).get("SVC_MACH_MSTR_PK");
                                ZYPEZDItemValueSetter.setValue(copyTarget.svcMachMstrPk, svcMachMstrPk);
                                usedSvcMachMstrPkList.add(svcMachMstrPk);
                                lienAndRtrnSvcMachMstrPkList.add(svcMachMstrPk);
                                targetList.add(copyTarget);

                                cnt++;
                            }
                        } else {
                            if (ZYPCommonFunc.hasValue(target.svcMachMstrPk)) {
                                svcMachMstrPkFlag = true;

                                ZYPEZDItemValueSetter.setValue(target.ordQty, BigDecimal.ONE);
                                ZYPEZDItemValueSetter.setValue(target.ordCustUomQty, BigDecimal.ONE);

                                target.cpoDtlDealNetAmt.clear();
                                target.cpoDtlDealSlsAmt.clear();
                                target.cpoDtlFuncNetAmt.clear();
                                target.cpoDtlFuncSlsAmt.clear();
                                ZYPEZDItemValueSetter.setValue(target.cpoDtlDealNetAmt, source.entDealNetUnitPrcAmt.getValue().multiply(target.ordQty.getValue()));
                                ZYPEZDItemValueSetter.setValue(target.cpoDtlDealSlsAmt, source.entDealNetUnitPrcAmt.getValue().multiply(target.ordQty.getValue()));
                                ZYPEZDItemValueSetter.setValue(target.cpoDtlFuncNetAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), CCY.US_DOLLAR, target.cpoDtlDealNetAmt.getValue(), msg.slsDt.getValue()));
                                ZYPEZDItemValueSetter.setValue(target.cpoDtlFuncSlsAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), CCY.US_DOLLAR, target.cpoDtlDealSlsAmt.getValue(), msg.slsDt.getValue()));
                            }
                        }

                        // Count of No svcMachMstrPk(additional)
                        int restCnt = ordQty - 1 - loopCnt;

                        if (restCnt > 0 && svcMachMstrPkFlag) {
                            DS_IMPT_ORD_DTLTMsg restTarget = new DS_IMPT_ORD_DTLTMsg();
                            EZDMsg.copy(target, null, restTarget, null);

                            restTarget.dsImptOrdDtlPk.clear();
                            restTarget.cpoDtlDealNetAmt.clear();
                            restTarget.cpoDtlDealSlsAmt.clear();
                            restTarget.cpoDtlFuncNetAmt.clear();
                            restTarget.cpoDtlFuncSlsAmt.clear();
                            restTarget.svcMachMstrPk.clear();
                            ZYPEZDItemValueSetter.setValue(restTarget.dsImptOrdDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_ORD_DTL_SQ));
                            ZYPEZDItemValueSetter.setValue(restTarget.ordQty, new BigDecimal(restCnt));
                            ZYPEZDItemValueSetter.setValue(restTarget.ordCustUomQty, new BigDecimal(restCnt));
                            ZYPEZDItemValueSetter.setValue(restTarget.cpoDtlDealNetAmt, source.entDealNetUnitPrcAmt.getValue().multiply(restTarget.ordQty.getValue()));
                            ZYPEZDItemValueSetter.setValue(restTarget.cpoDtlDealSlsAmt, source.entDealNetUnitPrcAmt.getValue().multiply(restTarget.ordQty.getValue()));
                            ZYPEZDItemValueSetter.setValue(restTarget.cpoDtlFuncNetAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), CCY.US_DOLLAR, restTarget.cpoDtlDealNetAmt.getValue(), msg.slsDt.getValue()));
                            ZYPEZDItemValueSetter.setValue(restTarget.cpoDtlFuncSlsAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), CCY.US_DOLLAR, restTarget.cpoDtlDealSlsAmt.getValue(), msg.slsDt.getValue()));

                            if (isLoan && CONFIG_TP.TO_SALES_CONVERSION.equals(config.configTpCd.getValue())) {
                                ZYPEZDItemValueSetter.setValue(restTarget.dsOrdLineCatgCd, DS_ORD_LINE_CATG.MERCHANDISE);
                            }

                            targetList.add(restTarget);
                        }
                    }
                }
            }
            // Add End 2018/08/21 QC#27489

            targetList.add(target);
        }

        return targetList;
    }

    // Mod Start 2018/08/21 QC#27489
    //private List<DS_IMPT_ORD_RTRN_DTLTMsg> createDsImptOrdRtrnDtl(S21ApiMessageMap msgMap, List<DS_IMPT_ORDTMsg> headerList, List<DS_IMPT_ORD_CONFIGTMsg> configList) {
    private List<DS_IMPT_ORD_RTRN_DTLTMsg> createDsImptOrdRtrnDtl(S21ApiMessageMap msgMap, List<DS_IMPT_ORDTMsg> headerList, List<DS_IMPT_ORD_CONFIGTMsg> configList, //
            List<BigDecimal> usedSvcMachMstrPkList) {
        // Mod End 2018/08/21 QC#27489

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_ORD_RTRN_DTLTMsg> targetList = new ArrayList<DS_IMPT_ORD_RTRN_DTLTMsg>();

        DS_IMPT_ORDTMsg header = getHeader(headerList);
        if (header == null) {

            return targetList;
        }

        // Del Start 2018/08/21 QC#27489
        //List<BigDecimal> usedSvcMachMstrPkList = new ArrayList<BigDecimal>(0); // QC#17974
        // Del End 2018/08/21 QC#27489
        // Add

        for (int i = 0; i < msg.rtrnDtl.getValidCount(); i++) {

            NWZC225001_rtrnDtlPMsg source = msg.rtrnDtl.no(i);

            DS_IMPT_ORD_CONFIGTMsg config = getConfig(configList, source.dsOrdPosnNum.getValue());
            if (config == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, null, null), NWAM0702E, ORDER_RMA_LINE, ORDER_CONFIG);
                continue;
            }

            DS_IMPT_ORD_RTRN_DTLTMsg target = new DS_IMPT_ORD_RTRN_DTLTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdRtrnDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_ORD_RTRN_DTL_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, config.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(target.ordSrcRefLineNum, source.ordSrcRefLineNum);
            ZYPEZDItemValueSetter.setValue(target.ordSrcRefLineSubNum, "001");
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdConfigPk, config.dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(target.dsOrdPosnNum, source.dsOrdPosnNum);

            // QC#16184 Mod Start
            ZYPEZDItemValueSetter.setValue(target.dsCpoLineCatgCd, source.dsOrdLineCatgCd);
            if (!ZYPCommonFunc.hasValue(target.dsCpoLineCatgCd)) {
                ZYPEZDItemValueSetter.setValue(target.dsCpoLineCatgCd, DS_ORD_LINE_CATG.RMA_NO_CREDIT);
            }
            // QC#16184 Mod End

            // 2017/12/05 QC#22527 add start
            if (CONFIG_TP.RETURN_EXISTING_IB.equals(config.configTpCd.getValue()) && ZYPCommonFunc.hasValue(configOrigOrdMap.get(config.dsImptOrdConfigPk.getValue()))) {

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", config.glblCmpyCd.getValue());
                ssmParam.put("ordLineCtxTpCd", LOAN_CONV_LINE_CRAT);
                ssmParam.put("actionNm", LOAN_ORD_ACTION_LOAN_RETURN);

                String lineValSet = NWZC225001Query.getInstance().queryString("getLineValSet", ssmParam);
                if(ZYPCommonFunc.hasValue(lineValSet)) {
                    ZYPEZDItemValueSetter.setValue(target.dsCpoLineCatgCd, lineValSet);
                }
            }
            // 2017/12/05 QC#22527 add end

            ZYPEZDItemValueSetter.setValue(target.ordLineSrcCd, ORD_LINE_SRC.RETURN);

            // item
            ZYPEZDItemValueSetter.setValue(target.mdseCd, source.mdseCd);
            MDSETMsg mdse = getMdse(msg.glblCmpyCd.getValue(), source.mdseCd.getValue());
            if (mdse != null) {

                ZYPEZDItemValueSetter.setValue(target.mdseNm, mdse.mdseNm);
            }
            target.origMdseCd.clear();
            target.origMdseNm.clear();
            target.custMdseCd.clear();
            target.setMdseCd.clear();

            ZYPEZDItemValueSetter.setValue(target.baseCmptFlg, getFlagValue(source.baseCmptFlg.getValue()));

            // ship to
            ZYPEZDItemValueSetter.setValue(target.dropShipFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(target.shipToCustCd, config.shipToCustLocCd);
            ZYPEZDItemValueSetter.setValue(target.shipToLocNm, config.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(target.shipToAddlLocNm, config.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(target.shipToFirstLineAddr, config.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(target.shipToScdLineAddr, config.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(target.shipToThirdLineAddr, config.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(target.shipToFrthLineAddr, config.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(target.shipToFirstRefCmntTxt, config.shipToFirstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(target.shipToScdRefCmntTxt, config.shipToScdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(target.shipToCtyAddr, config.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(target.shipToStCd, config.shipToStCd);
            ZYPEZDItemValueSetter.setValue(target.shipToProvNm, config.shipToProvNm);
            ZYPEZDItemValueSetter.setValue(target.shipToCntyNm, config.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(target.shipToPostCd, config.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(target.shipToCtryCd, config.shipToCtryCd);

            // UOM, quantity
            ZYPEZDItemValueSetter.setValue(target.custUomCd, PKG_UOM.EACH);
            ZYPEZDItemValueSetter.setValue(target.ordQty, source.ordQty);
            ZYPEZDItemValueSetter.setValue(target.ordCustUomQty, source.ordQty);

            // warehouse
            target.invtyLocCd.clear();
            target.rtlWhCd.clear();
            target.rtlSwhCd.clear();

            ZYPEZDItemValueSetter.setValue(target.stkStsCd, STK_STS.GOOD);

            // price
            ZYPEZDItemValueSetter.setValue(target.prcBaseDt, msg.slsDt);
            ZYPEZDItemValueSetter.setValue(target.prcCatgCd, header.prcCatgCd);
            ZYPEZDItemValueSetter.setValue(target.flPrcListCd, header.flPrcListCd);

            // amount
            ZYPEZDItemValueSetter.setValue(target.entDealNetUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(target.entFuncNetUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(target.cpoDtlDealNetAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(target.cpoDtlDealSlsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(target.cpoDtlFuncNetAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(target.cpoDtlFuncSlsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(target.dealPrcListPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(target.funcPrcListPrcAmt, BigDecimal.ZERO);

            ZYPEZDItemValueSetter.setValue(target.ccyCd, CCY.US_DOLLAR);
            target.exchRate.clear();

            target.slsRepOrSlsTeamTocCd.clear();

            ZYPEZDItemValueSetter.setValue(target.serNum, source.serNum);

            // QC#20740 Add Start
            // target.rqstPickUpDt.clear();
            for (int j = 0; j < msg.istlInfo.getValidCount(); j++) {

                NWZC225001_istlInfoPMsg istlInfo = msg.istlInfo.no(j);
                if(S21StringUtil.isEquals(source.dsOrdPosnNum.getValue(), istlInfo.dsOrdPosnNum.getValue())) {
                    ZYPEZDItemValueSetter.setValue(target.rqstPickUpDt, istlInfo.rqstIstlDt);
                }
            }
            // QC#20740 Add End

            target.origCpoOrdNum.clear();
            target.origInvNum.clear();
            // target.svcConfigMstrPk.clear();
            target.dsContrNum.clear();
            target.dsContrSqNum.clear();

            ZYPEZDItemValueSetter.setValue(target.svcConfigMstrPk, config.svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(target.svcMachMstrPk, source.svcMachMstrPk);

            // QC#17974 Add Start
            // QC#18344 Del Start
            // if (ZYPCommonFunc.hasValue(target.mdseCd) &&
            // isNeedSerialNum(msg.glblCmpyCd.getValue(),
            // target.mdseCd.getValue()) &&
            // !ZYPCommonFunc.hasValue(target.serNum)) {
            // setMsgIdWithParam(msgMap, null, NWZM2223E);
            // }
            // QC#18344 Del End

            BigDecimal svcMachMstrPk = null;
            
            if (!ZYPCommonFunc.hasValue(target.serNum) && !ZYPCommonFunc.hasValue(target.svcMachMstrPk)) {
                if (ZYPCommonFunc.hasValue(config.svcConfigMstrPk.getValue()) && ZYPCommonFunc.hasValue(target.mdseCd)) {

                    // 2017/04/18 S21_NA#18360 Add Start
                    SVC_MACH_MSTRTMsg tMsg = getSvcMachMstrByConfigAndMdse(msg.glblCmpyCd.getValue(), msg.slsDt.getValue(), config.svcConfigMstrPk.getValue(), target.mdseCd.getValue(), usedSvcMachMstrPkList);
                    if (tMsg != null) {
                        ZYPEZDItemValueSetter.setValue(target.svcMachMstrPk, tMsg.svcMachMstrPk);
                        ZYPEZDItemValueSetter.setValue(target.serNum, tMsg.serNum);
                    }
                    // 2017/04/18 S21_NA#18360 Add End
                }

            } else if (ZYPCommonFunc.hasValue(target.serNum)) {
                svcMachMstrPk = getSvcMachMstrPkBySerNum(msg.glblCmpyCd.getValue(), target.mdseCd.getValue(), target.serNum.getValue(), msg.slsDt.getValue());
                ZYPEZDItemValueSetter.setValue(target.svcMachMstrPk, svcMachMstrPk);

            } else if (ZYPCommonFunc.hasValue(target.svcMachMstrPk)) {
                svcMachMstrPk = target.svcMachMstrPk.getValue();
            }

            if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                usedSvcMachMstrPkList.add(svcMachMstrPk);
            } else if (ZYPCommonFunc.hasValue(target.svcMachMstrPk)) {
                usedSvcMachMstrPkList.add(target.svcMachMstrPk.getValue());
            }
            // QC#17974 Add End

            target.refCpoDtlLineNum.clear();
            target.refCpoDtlLineSubNum.clear();
            target.dplyLineRefNum.clear();
            target.csmpContrNum.clear();
            target.dlrRefNum.clear();
            target.csmpPrcListCd.clear();
            target.hddRmvCd.clear();

            ZYPEZDItemValueSetter.setValue(target.rtrnRsnCd, source.rtrnRsnCd);

            target.unitNetWt.clear();
            target.firstBllgAttrbNm.clear();
            target.firstBllgAttrbValTxt.clear();
            target.scdBllgAttrbNm.clear();
            target.scdBllgAttrbValTxt.clear();
            target.thirdBllgAttrbNm.clear();
            target.thirdBllgAttrbValTxt.clear();
            target.frthBllgAttrbNm.clear();
            target.frthBllgAttrbValTxt.clear();
            target.fifthBllgAttrbNm.clear();
            target.fifthBllgAttrbValTxt.clear();
            target.sixthBllgAttrbNm.clear();
            target.sixthBllgAttrbValTxt.clear();

            // Add Start 2018/08/21 QC#27489
            int ordQty = Math.abs(target.ordQty.getValueInt());

            if (!ZYPCommonFunc.hasValue(target.serNum) && ordQty > 1) {
                if (ZYPCommonFunc.hasValue(config.svcConfigMstrPk.getValue()) && ZYPCommonFunc.hasValue(target.mdseCd)) {

                    // Get all service machine master PK
                    List<Map<String, Object>> svcMachMstrPkList = getAllSvcMachMstrByConfigAndMdse(msg.glblCmpyCd.getValue(), msg.slsDt.getValue(), //
                            config.svcConfigMstrPk.getValue(), target.mdseCd.getValue(), usedSvcMachMstrPkList);

                    if (svcMachMstrPkList.size() > 0) {
                        int cnt = 0;
                        int loopCnt = ordQty - 1;

                        ZYPEZDItemValueSetter.setValue(target.ordQty, new BigDecimal(-1));
                        ZYPEZDItemValueSetter.setValue(target.ordCustUomQty, new BigDecimal(-1));

                        while (cnt < loopCnt) {
                            DS_IMPT_ORD_RTRN_DTLTMsg copyTarget = new DS_IMPT_ORD_RTRN_DTLTMsg();
                            EZDMsg.copy(target, null, copyTarget, null);

                            BigDecimal copySvcMachMstrPk = (BigDecimal) svcMachMstrPkList.get(cnt).get("SVC_MACH_MSTR_PK");
                            ZYPEZDItemValueSetter.setValue(copyTarget.svcMachMstrPk, copySvcMachMstrPk);
                            usedSvcMachMstrPkList.add(copySvcMachMstrPk);

                            ZYPEZDItemValueSetter.setValue(copyTarget.dsImptOrdRtrnDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_ORD_RTRN_DTL_SQ));

                            targetList.add(copyTarget);

                            cnt++;
                        }
                    }
                }
            }
            // Add End 2018/08/21 QC#27489

            targetList.add(target);
        }

        return targetList;
    }

    // QC#17974 Add Start
    private SVC_MACH_MSTRTMsg getSvcMachMstrByConfigAndMdse(String glblCmpyCd, String slsDt, BigDecimal svcConfigMstrPk, String mdseCd, List<BigDecimal> usedMachMstrPkList) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);
        ssmParam.put("mdseCd", mdseCd + PERCENT);

        if (usedMachMstrPkList != null && usedMachMstrPkList.size() > 0) {
            ssmParam.put("usedMachMstrPkList", usedMachMstrPkList);
        }

        BigDecimal svcMachMstrPk = (BigDecimal) NWZC225001Query.getInstance().queryBigDecimal("getSvcMachMstrPkByConfigAndMdse", ssmParam);

        if (svcMachMstrPk.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }

        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);

        return (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    // QC#17974 Add End

    // Add Start 2018/08/21 QC#27489
    private List<Map<String, Object>> getAllSvcMachMstrByConfigAndMdse(String glblCmpyCd, String slsDt, BigDecimal svcConfigMstrPk, String mdseCd, List<BigDecimal> usedMachMstrPkList) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);
        ssmParam.put("mdseCd", mdseCd + PERCENT);

        if (usedMachMstrPkList != null && usedMachMstrPkList.size() > 0) {
            ssmParam.put("usedMachMstrPkList", usedMachMstrPkList);
        }

        List<Map<String, Object>> svcMachMstrPkList = NWZC225001Query.getInstance().queryMapList("getAllSvcMachMstrPkByConfigAndMdse", ssmParam);

        return svcMachMstrPkList;
    }
    // Add End 2018/08/21 QC#27489

    private NWZC157001PMsg callPricing(S21ApiMessageMap msgMap, DS_IMPT_ORDTMsg header, List<DS_IMPT_ORD_CONFIGTMsg> configList, List<DS_IMPT_ORD_DTLTMsg> lineList, List<DS_IMPT_ORD_RTRN_DTLTMsg> rmaLineList) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

        prcApiPMsg = setHeader(msg, prcApiPMsg, header);
        prcApiPMsg = setLineConfig(msg, prcApiPMsg, header, configList, lineList);
        prcApiPMsg = setRMA(msg, prcApiPMsg, header, configList, rmaLineList);

        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.BATCH);

        return prcApiPMsg;

    }

    private NWZC157001PMsg setHeader(NWZC225001PMsg msg, NWZC157001PMsg prcApiPMsg, DS_IMPT_ORDTMsg dsImptOrd) {

        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, msg.glblCmpyCd.getValue());
        // 2018/08/02 S21_NA#26665 mod start
//        prcApiPMsg.xxModeCd.setValue(NWZC157001.GET_ORDER_ALL);
        prcApiPMsg.xxModeCd.setValue(NWZC157001.GET_BASE_PRICE);
        // 2018/08/02 S21_NA#26665 mod end
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, msg.slsDt.getValue());
        prcApiPMsg.prcCtxTpCd.setValue(PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, msg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, dsImptOrd.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, dsImptOrd.dsOrdTpCd);

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpPrcDfn = getDsOrdTpPrcDfn(msg.glblCmpyCd.getValue(), dsImptOrd.dsOrdTpCd.getValue(), msg.slsDt.getValue());
        if (dsOrdTpPrcDfn != null) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, dsOrdTpPrcDfn.lineBizTpCd);
        }

        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, dsImptOrd.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, dsImptOrd.cpoSrcTpCd);
        prcApiPMsg.trxHdrNum.clear();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, dsImptOrd.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.negoDealAmt, dsImptOrd.negoDealAmt);
        // Mod Start 2017/09/06 QC#20790
//        prcApiPMsg.taxCalcFlg.setValue(ZYPConstant.FLG_ON_Y);
        prcApiPMsg.taxCalcFlg.setValue(ZYPConstant.FLG_OFF_N);
        // Mod End 2017/09/06 QC#20790
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, dsImptOrd.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.leasePrchOptCd, dsImptOrd.leasePrchOptCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);

        return prcApiPMsg;
    }

    private NWZC157001PMsg setLineConfig(NWZC225001PMsg msg, NWZC157001PMsg prcApiPMsg, DS_IMPT_ORDTMsg dsImptOrd, List<DS_IMPT_ORD_CONFIGTMsg> configList, List<DS_IMPT_ORD_DTLTMsg> lineList) {

        int detailIndex = prcApiPMsg.NWZC157002PMsg.getValidCount();

        for (DS_IMPT_ORD_DTLTMsg line : lineList) {

            DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfig = getConfig(configList, line.dsOrdPosnNum.getValue());

            if (dsImptOrdConfig == null) {

                continue;
            }

            String trxLineNum = NWXC220001Util.convNumToZ99(detailIndex + 1);

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).trxLineNum, trxLineNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).trxLineSubNum, "001");
            prcApiPMsg.NWZC157002PMsg.no(detailIndex).configCatgCd.setValue(CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).billToCustCd, dsImptOrdConfig.billToCustLocCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).shipToCustCd, dsImptOrdConfig.shipToCustLocCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).dsAcctNum_SH, dsImptOrdConfig.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).dsAcctNum_BL, dsImptOrdConfig.billToCustAcctCd);

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).prcCatgCd, dsImptOrd.prcCatgCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).csmpNum, line.csmpContrNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).dlrRefNum, line.dlrRefNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).prcContrNum, dsImptOrd.prcContrNum);

            TOCTMsg toc = new TOCTMsg();
            ZYPEZDItemValueSetter.setValue(toc.glblCmpyCd, msg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(toc.tocCd, dsImptOrd.slsRepTocCd.getValue());
            toc = (TOCTMsg) EZDTBLAccessor.findByKey(toc);
            if (toc != null) {

                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).coaBrCd, toc.coaBrCd);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).coaExtnCd, toc.coaExtnCd);
            }
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).ccyCd, CCY.US_DOLLAR);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).mdseCd, line.mdseCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).pkgUomCd, PKG_UOM.EACH);

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).dsOrdLineCatgCd, line.dsOrdLineCatgCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).ordQty, line.ordQty);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).ordCustUomQty, line.ordQty);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).mdlId, dsImptOrdConfig.mdlId);

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).ctyAddr_SH, dsImptOrdConfig.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).stCd_SH, dsImptOrdConfig.shipToStCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).ctryCd_SH, dsImptOrdConfig.shipToCtryCd);

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).frtCondCd, dsImptOrd.frtCondCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).shpgSvcLvlCd, dsImptOrd.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).xxPrcCloFlg, ZYPConstant.FLG_OFF_N);

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).dsOrdPosnNum, line.dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).xxUnitPrc, line.entDealNetUnitPrcAmt);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).entCpoDtlDealSlsAmt, line.entDealNetUnitPrcAmt);

            detailIndex++;
            prcApiPMsg.NWZC157002PMsg.setValidCount(detailIndex);
        }
        return prcApiPMsg;
    }

    private NWZC157001PMsg setRMA(NWZC225001PMsg msg, NWZC157001PMsg prcApiPMsg, DS_IMPT_ORDTMsg dsImptOrd, List<DS_IMPT_ORD_CONFIGTMsg> configList, List<DS_IMPT_ORD_RTRN_DTLTMsg> rmaLineList) {

        int detailIndex = prcApiPMsg.NWZC157002PMsg.getValidCount();

        for (DS_IMPT_ORD_RTRN_DTLTMsg rmaLine : rmaLineList) {

            String trxLineNum = NWXC220001Util.convNumToZ99(detailIndex);

            DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfig = getConfig(configList, rmaLine.dsOrdPosnNum.getValue());

            if (dsImptOrdConfig == null) {

                continue;
            }

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).trxLineNum, trxLineNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).trxLineSubNum, "001");
            prcApiPMsg.NWZC157002PMsg.no(detailIndex).configCatgCd.setValue(CONFIG_CATG.INBOUND);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).billToCustCd, dsImptOrdConfig.billToCustLocCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).shipToCustCd, dsImptOrdConfig.shipToCustLocCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).dsAcctNum_SH, dsImptOrdConfig.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).dsAcctNum_BL, dsImptOrdConfig.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).prcCatgCd, dsImptOrd.prcCatgCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).prcContrNum, dsImptOrd.prcContrNum);

            TOCTMsg toc = new TOCTMsg();
            ZYPEZDItemValueSetter.setValue(toc.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(toc.tocCd, dsImptOrd.slsRepTocCd.getValue());
            toc = (TOCTMsg) EZDTBLAccessor.findByKey(toc);
            if (toc != null) {

                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).coaBrCd, toc.coaBrCd);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).coaExtnCd, toc.coaExtnCd);
            }
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).ccyCd, CCY.US_DOLLAR);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).mdseCd, rmaLine.mdseCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).pkgUomCd, PKG_UOM.EACH);

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).dsOrdLineCatgCd, rmaLine.dsCpoLineCatgCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).ordQty, rmaLine.ordQty);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).ordCustUomQty, rmaLine.ordQty);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).mdlId, dsImptOrdConfig.mdlId);

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).ctyAddr_SH, dsImptOrdConfig.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).stCd_SH, dsImptOrdConfig.shipToStCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).ctryCd_SH, dsImptOrdConfig.shipToCtryCd);

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).frtCondCd, dsImptOrd.frtCondCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).shpgSvcLvlCd, dsImptOrd.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).xxPrcCloFlg, ZYPConstant.FLG_OFF_N);

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).dsOrdPosnNum, rmaLine.dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).xxUnitPrc, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(detailIndex).prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);

            detailIndex++;
            prcApiPMsg.NWZC157002PMsg.setValidCount(detailIndex);
        }

        return prcApiPMsg;
    }

    private List<DS_IMPT_SVC_DTLTMsg> createDsImptSvcDtlForService(//
            S21ApiMessageMap msgMap, List<DS_IMPT_ORDTMsg> headerList, Map<BigDecimal, NWZC225001_svcDtlPMsg> lbrSvcDtlMap, List<BigDecimal> bdlSvcLineNumList) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_SVC_DTLTMsg> targetList = new ArrayList<DS_IMPT_SVC_DTLTMsg>();

        DS_IMPT_ORDTMsg header = getHeader(headerList);
        if (header == null) {

            return targetList;
        }

        Map<BigDecimal, NWZC225001_svcDtlPMsg> svcTpSvcDtlMap = new HashMap<BigDecimal, NWZC225001_svcDtlPMsg>(msg.svcDtl.getValidCount());
        for (int i = 0; i < msg.svcDtl.getValidCount(); i++) {
            NWZC225001_svcDtlPMsg source = msg.svcDtl.no(i);
            if (isRental && DS_IMPT_SVC_TP_SERVICE.equals(source.dsImptSvcTpCd.getValue())) {
                // svcLineNumMap.put(source.refImptSvcLineNum.getValue(),
                // source.dsImptSvcLineNum.getValue());
                svcTpSvcDtlMap.put(source.refImptSvcLineNum.getValue(), source);
            }
        }

        for (int i = 0; i < msg.svcDtl.getValidCount(); i++) {

            NWZC225001_svcDtlPMsg source = msg.svcDtl.no(i);
            /** QC#16142 LBR(Rental Shell) */
            if (isRental && DS_IMPT_SVC_TP_PRODUCT.equals(source.dsImptSvcTpCd.getValue())) {
                NWZC225001_svcDtlPMsg svcTpSvcDtlPMsg = svcTpSvcDtlMap.get(source.dsImptSvcLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(source.refImptSvcLineNum, svcTpSvcDtlPMsg.dsImptSvcLineNum.getValue());
                lbrSvcDtlMap.put(source.dsImptSvcLineNum.getValue(), source);
                if (isBundled(source, svcTpSvcDtlPMsg)) {
                    bdlSvcLineNumList.add(source.dsImptSvcLineNum.getValue());
                    continue;
                }
            }

            DS_IMPT_SVC_DTLTMsg target = new DS_IMPT_SVC_DTLTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_DTL_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, header.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcLineNum, source.dsImptSvcLineNum);
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcMdseCd, source.dsImptSvcMdseCd);
            ZYPEZDItemValueSetter.setValue(target.prcSvcContrTpCd, source.prcSvcContrTpCd);
            ZYPEZDItemValueSetter.setValue(target.prcSvcPlnTpCd, source.prcSvcPlnTpCd);
            ZYPEZDItemValueSetter.setValue(target.dsContrCatgCd, source.dsContrCatgCd);
            ZYPEZDItemValueSetter.setValue(target.baseBllgCycleCd, source.baseBllgCycleCd);
            ZYPEZDItemValueSetter.setValue(target.usgBllgCycleCd, source.usgBllgCycleCd);

            // Mod Start 2017/09/14 QC#20787
//            // 2017/02/20 S21_NA#17585 Mod Start
//            BLLG_CYCLETMsg bllgCycle = new BLLG_CYCLETMsg();
//            ZYPEZDItemValueSetter.setValue(bllgCycle.glblCmpyCd, msg.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(bllgCycle.bllgCycleCd, target.baseBllgCycleCd);
//            bllgCycle = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bllgCycle);
//            if (bllgCycle != null) {
//
//                BigDecimal fromPerMthNum = ((source.fromPerNum.getValue().subtract(BigDecimal.ONE)).multiply(bllgCycle.bllgCycleMthAot.getValue())).add(BigDecimal.ONE);
//                ZYPEZDItemValueSetter.setValue(target.fromPerMthNum, fromPerMthNum);
//
//                BigDecimal toPerMthNum = source.toPerNum.getValue().multiply(bllgCycle.bllgCycleMthAot.getValue());
//                ZYPEZDItemValueSetter.setValue(target.toPerMthNum, toPerMthNum);
//            }
//            // ZYPEZDItemValueSetter.setValue(target.fromPerMthNum,
//            // source.fromPerNum);
//            // ZYPEZDItemValueSetter.setValue(target.toPerMthNum,
//            // source.toPerNum);
//            // 2017/02/20 S21_NA#17585 Mod End
            ZYPEZDItemValueSetter.setValue(target.fromPerMthNum, source.fromPerMthNum);
            ZYPEZDItemValueSetter.setValue(target.toPerMthNum, source.toPerMthNum);
            // Mod End 2017/09/14 QC#20787

            target.termMthAot.clear();
            ZYPEZDItemValueSetter.setValue(target.billWithEquipFlg, getFlagValue(source.billWithEquipFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(target.billByTpCd, source.billByTpCd);

            // sold to
            ZYPEZDItemValueSetter.setValue(target.sellToCustCd, source.sellToCustCd);
            BILL_TO_CUSTTMsg soldTo = getBillToCustByLocNum(msg.glblCmpyCd.getValue(), source.soldToLocNum.getValue(), msg.slsDt.getValue());
            if (soldTo != null) {

                ZYPEZDItemValueSetter.setValue(target.soldToCustLocCd, soldTo.billToCustCd);
            }

            target.svcAgmtVrsnNum.clear();
            ZYPEZDItemValueSetter.setValue(target.manContrOvrdFlg, getFlagValue(source.manContrOvrdFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(target.manContrOvrdRsnCd, source.manContrOvrdRsnCd);
            ZYPEZDItemValueSetter.setValue(target.manContrOvrdCmntTxt, source.manContrOvrdCmntTxt);
            ZYPEZDItemValueSetter.setValue(target.dsContrPk, source.dsContrPk);
            ZYPEZDItemValueSetter.setValue(target.useEquipBillToFlg, ZYPConstant.FLG_OFF_N);
            target.svcPrcCatgCd.clear();
            target.totBasePrcDealAmt.clear();
            target.totBasePrcFuncAmt.clear();
            ZYPEZDItemValueSetter.setValue(target.fixTermInMthAot, source.fixTermInMthAot);
            ZYPEZDItemValueSetter.setValue(target.maxUplftPct, source.maxUplftPct);
            ZYPEZDItemValueSetter.setValue(target.cpoSvcLineActCd, source.cpoSvcLineActCd);

            targetList.add(target);
        }

        return targetList;
    }

    /**
     * @param source
     * @param svcTpSvcDtlPMsg
     * @return
     */
    private boolean isBundled(NWZC225001_svcDtlPMsg source, NWZC225001_svcDtlPMsg svcTpSvcDtlPMsg) {
        if (BDL_PMT_BUNDLED.equals(source.bdlPmtCd.getValue())) {
            return true;
        }

        if (!S21StringUtil.isEquals(source.dsImptSvcMdseCd.getValue(), svcTpSvcDtlPMsg.dsImptSvcMdseCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(source.prcSvcContrTpCd.getValue(), svcTpSvcDtlPMsg.prcSvcContrTpCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(source.prcSvcPlnTpCd.getValue(), svcTpSvcDtlPMsg.prcSvcPlnTpCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(source.dsContrCatgCd.getValue(), svcTpSvcDtlPMsg.dsContrCatgCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(source.baseBllgCycleCd.getValue(), svcTpSvcDtlPMsg.baseBllgCycleCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(source.usgBllgCycleCd.getValue(), svcTpSvcDtlPMsg.usgBllgCycleCd.getValue())) {
            return false;
        }
        if (!isEquals(source.fromPerNum, svcTpSvcDtlPMsg.fromPerNum)) {
            return false;
        }
        if (!isEquals(source.toPerNum, svcTpSvcDtlPMsg.toPerNum)) {
            return false;
        }
        if (!S21StringUtil.isEquals(source.billWithEquipFlg.getValue(), svcTpSvcDtlPMsg.billWithEquipFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(source.billByTpCd.getValue(), svcTpSvcDtlPMsg.billByTpCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(source.soldToLocNum.getValue(), svcTpSvcDtlPMsg.soldToLocNum.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(source.sellToCustCd.getValue(), svcTpSvcDtlPMsg.sellToCustCd.getValue())) {
            return false;
        }
        if (!isEquals(source.dsContrPk, svcTpSvcDtlPMsg.dsContrPk)) {
            return false;
        }
        if (!S21StringUtil.isEquals(source.manContrOvrdFlg.getValue(), svcTpSvcDtlPMsg.manContrOvrdFlg.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(source.manContrOvrdRsnCd.getValue(), svcTpSvcDtlPMsg.manContrOvrdRsnCd.getValue())) {
            return false;
        }
        if (!S21StringUtil.isEquals(source.manContrOvrdCmntTxt.getValue(), svcTpSvcDtlPMsg.manContrOvrdCmntTxt.getValue())) {
            return false;
        }

        if (!isEquals(source.fixTermInMthAot, svcTpSvcDtlPMsg.fixTermInMthAot)) {
            return false;
        }
        if (!isEquals(source.maxUplftPct, svcTpSvcDtlPMsg.maxUplftPct)) {
            return false;
        }
        if (!S21StringUtil.isEquals(source.cpoSvcLineActCd.getValue(), svcTpSvcDtlPMsg.cpoSvcLineActCd.getValue())) {
            return false;
        }

        return true;
    }

    private boolean isEquals(EZDPBigDecimalItem item1, EZDPBigDecimalItem item2) {
        return isEquals(item1.getValue(), item2.getValue());
    }

    private boolean isEquals(BigDecimal val1, BigDecimal val2) {
        if (ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {
            return true;
        }
        return val1.compareTo(val2) == 0;
    }

    private Map<SVC_LIST, List<?>> createDsImptSvcConfigRef(//
            S21ApiMessageMap msgMap //
            , List<DS_IMPT_SVC_DTLTMsg> serviceLineList //
            , List<DS_IMPT_ORD_DTLTMsg> lineList //
            , List<Map<String, Object>> svcPrcListMap //
            , Map<BigDecimal, NWZC225001_svcDtlPMsg> lbrSvcDtlMap //
            , List<BigDecimal> bdlSvcLineNumList) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        Map<SVC_LIST, List<?>> serviceMap = new HashMap<SVC_LIST, List<?>>();

        List<DS_IMPT_SVC_CONFIG_REFTMsg> targetList = new ArrayList<DS_IMPT_SVC_CONFIG_REFTMsg>();
        List<DS_IMPT_SVC_PRCTMsg> servicePriceList = new ArrayList<DS_IMPT_SVC_PRCTMsg>();
        List<DS_IMPT_SVC_USG_PRCTMsg> usageList = new ArrayList<DS_IMPT_SVC_USG_PRCTMsg>();

        serviceMap.put(SVC_LIST.CONFIG_REF, targetList);
        serviceMap.put(SVC_LIST.PRC, servicePriceList);
        serviceMap.put(SVC_LIST.USG_PRC, usageList);

        // tier type code
        List<String> prcSvcTierTpCdList = getPrcSvcTierTpCdList(msg.glblCmpyCd.getValue());

        for (int i = 0; i < msg.svcConfigRef.getValidCount(); i++) {

            NWZC225001_svcConfigRefPMsg source = msg.svcConfigRef.no(i);
            // QC#16142
            boolean isUnbundled = false;
            if (lbrSvcDtlMap.containsKey(source.dsImptSvcLineNum.getValue())) {
                if (bdlSvcLineNumList.contains(source.dsImptSvcLineNum.getValue())) {
                    continue;
                }
                isUnbundled = true;
            }

            DS_IMPT_SVC_DTLTMsg serviceLine = getServiceLine(serviceLineList, source.dsImptSvcLineNum.getValue());
            if (serviceLine == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, source.dsImptSvcLineNum.getValue(), null), NWAM0702E, SERVICE_CONFIG_REFERENCE, SERVICE_LINE);
                continue;
            }

            DS_IMPT_ORD_DTLTMsg line = getLine(lineList, source.dsOrdPosnNum.getValue(), source.ordSrcRefLineNum.getValue());
            if (line == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), source.ordSrcRefLineNum.getValue(), source.dsImptSvcLineNum.getValue(), null), NWAM0702E, SERVICE_CONFIG_REFERENCE,
                        ORDER_LINE);
                continue;
            }

            DS_IMPT_SVC_CONFIG_REFTMsg target = new DS_IMPT_SVC_CONFIG_REFTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcConfigRefPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_CONFIG_REF_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcDtlPk, serviceLine.dsImptSvcDtlPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, serviceLine.dsImptOrdPk);
            target.svcConfigMstrPk.clear();
            ZYPEZDItemValueSetter.setValue(target.custIssPoNum, source.custIssPoNum);
            ZYPEZDItemValueSetter.setValue(target.mtrReadMethCd, source.mtrReadMethCd);
            ZYPEZDItemValueSetter.setValue(target.custIssPoDt, source.custIssPoDt);
            // QC#25030		
//            target.funcSvcRevTrnsfAmt.clear();
//            target.dealSvcRevTrnsfAmt.clear();
//            target.funcSvcCostTrnsfAmt.clear();
//            target.dealSvcCostTrnsfAmt.clear();
            ZYPEZDItemValueSetter.setValue(target.funcSvcRevTrnsfAmt //
                    , calcFuncAmt(//
                            msg.glblCmpyCd.getValue(), getCcyCd(msg), source.dealSvcRevTrnsfAmt.getValue(), msg.slsDt.getValue()));
            ZYPEZDItemValueSetter.setValue(target.dealSvcRevTrnsfAmt, source.dealSvcRevTrnsfAmt);
            ZYPEZDItemValueSetter.setValue(target.funcSvcCostTrnsfAmt //
                    , calcFuncAmt(//
                            msg.glblCmpyCd.getValue(), getCcyCd(msg), source.dealSvcCostTrnsfAmt.getValue(), msg.slsDt.getValue()));
            ZYPEZDItemValueSetter.setValue(target.dealSvcCostTrnsfAmt, source.dealSvcCostTrnsfAmt);
            // 2018/05/29 QC#23118 add start
            if (ZYPCommonFunc.hasValue(target.funcSvcRevTrnsfAmt)) {
                ZYPEZDItemValueSetter.setValue(target.funcSvcRevTrnsfAmt, target.funcSvcRevTrnsfAmt.getValue().negate());
            }
            if (ZYPCommonFunc.hasValue(target.dealSvcRevTrnsfAmt)) {
                ZYPEZDItemValueSetter.setValue(target.dealSvcRevTrnsfAmt, target.dealSvcRevTrnsfAmt.getValue().negate());
            }
            // 2018/05/29 QC#23118 add end

            target.svcPrcCatgCd.clear();
            // 2018/04/02 QC#20162 add start
            ZYPEZDItemValueSetter.setValue(target.dsPoExprDt, source.dsPoExprDt);
            // 2018/04/02 QC#20162 add end

            ZYPEZDItemValueSetter.setValue(target.dsImptOrdDtlPk, line.dsImptOrdDtlPk);


            targetList.add(target);

            // service price
            DS_IMPT_SVC_PRCTMsg servicePrice //
            = createDsImptSvcPrc(//
                    msgMap //
                    , svcPrcListMap //
                    , serviceLine.dsImptSvcDtlPk.getValue() //
                    , target.dsImptSvcConfigRefPk.getValue() //
                    , source.dsImptSvcLineNum.getValue() //
                    , source.dsOrdPosnNum.getValue() //
                    , source.ordSrcRefLineNum.getValue() //
                    , isUnbundled);
            if (servicePrice != null) {

                servicePriceList.add(servicePrice);
                ZYPEZDItemValueSetter.setValue(target.dsImptSvcPrcPk, servicePrice.dsImptSvcPrcPk);

                // service usage price
                List<DS_IMPT_SVC_USG_PRCTMsg> serviceUsageListByPrc //
                = createDsImptSvcUsgPrc(//
                        msgMap //
                        , serviceLine //
                        , servicePrice //
                        , source.dsImptSvcLineNum.getValue() //
                        , source.dsOrdPosnNum.getValue() //
                        , source.ordSrcRefLineNum.getValue() //
                        , prcSvcTierTpCdList //
                        , svcPrcListMap);
                usageList.addAll(serviceUsageListByPrc);
            }
        }

        return serviceMap;
    }

    // QC#25030
    private String getCcyCd(NWZC225001PMsg pMsg) {
        if (ZYPCommonFunc.hasValue(this.ccyCd)) {
            return this.ccyCd;
        }

        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        tMsg = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            this.ccyCd = tMsg.stdCcyCd.getValue();
            return this.ccyCd;
        } else {
            return null;
        }
    }

    private DS_IMPT_SVC_PRCTMsg createDsImptSvcPrc(//
            S21ApiMessageMap msgMap //
            , List<Map<String, Object>> svcPrcListMap //
            , BigDecimal dsImptSvcDtlPk //
            , BigDecimal dsImptSvcConfigRefPk //
            , BigDecimal dsImptSvcLineNum //
            , String dsOrdPosnNum //
            , String ordSrcRefLineNum //
            , boolean isUnbundled) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();
        
        // 2018/07/05 S21_NA#26528 Add Start
        int targetCnt = getTargetPrcHdr(msgMap, dsImptSvcLineNum, dsOrdPosnNum, ordSrcRefLineNum);
        if( targetCnt < 0){
            return null;
        }
        // 2018/07/05 S21_NA#26528 Add End

        //for (int i = 0; i < msg.svcPrc.getValidCount(); i++) { // 2018/07/05 S21_NA#26528 Del Loop

            // NWZC225001_svcPrcPMsg source = msg.svcPrc.no(i);
        NWZC225001_svcPrcPMsg source = msg.svcPrc.no(targetCnt);

        // 2018/07/05 S21_NA#26528 Del Start
//            if (!equalsBigDecimal(source.dsImptSvcLineNum.getValue(), dsImptSvcLineNum)) {
//
//                continue;
//            }
//
//            if (!S21StringUtil.isEquals(source.dsOrdPosnNum.getValue(), dsOrdPosnNum)) {
//
//                continue;
//            }
//
//            if (!S21StringUtil.isEquals(source.ordSrcRefLineNum.getValue(), ordSrcRefLineNum)) {
//
//                continue;
//            }
        // 2018/07/05 S21_NA#26528 Del End

            DS_IMPT_SVC_PRCTMsg target = new DS_IMPT_SVC_PRCTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcPrcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_PRC_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcDtlPk, dsImptSvcDtlPk);
            // 2017/06/02 QC#18625 UPD START
            // ZYPEZDItemValueSetter.setValue(target.mdlId,
            // source.mdlId);
            if (BigDecimal.ZERO.compareTo(source.mdlId.getValue()) == 0) {
                target.mdlId.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(target.mdlId, source.mdlId);
            }
            // 2017/06/02 QC#18625 UPD E N D

            // default price category
            // 2018/04/03 QC#24980 mod start
            String svcPriceList = getDefaultMdlSvcPrc(svcPrcListMap, false , null, msg.glblCmpyCd.getValue());
            // 2018/04/03 QC#24980 mod end
            ZYPEZDItemValueSetter.setValue(target.maintPrcCatgCd, svcPriceList);
            ZYPEZDItemValueSetter.setValue(target.maintFlPrcCatgCd, svcPriceList);

            ZYPEZDItemValueSetter.setValue(target.prcMtrPkgPk, source.prcMtrPkgPk);
            if (isUnbundled) {
                ZYPEZDItemValueSetter.setValue(target.basePrcDealAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(target.basePrcFuncAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(target.dealPrcListPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(target.funcPrcListPrcAmt, BigDecimal.ZERO);
            } else {
                // 2018/07/05 S21_NA#26528 Mod Start
                BigDecimal basePrcDealAmt = calcBasePrc(msgMap, dsImptSvcLineNum, dsOrdPosnNum, ordSrcRefLineNum);
                ZYPEZDItemValueSetter.setValue(target.basePrcDealAmt, basePrcDealAmt);
                ZYPEZDItemValueSetter.setValue(target.basePrcFuncAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), CCY.US_DOLLAR, basePrcDealAmt, msg.slsDt.getValue()));
                // ZYPEZDItemValueSetter.setValue(target.basePrcDealAmt, source.basePrcDealAmt);
                // ZYPEZDItemValueSetter.setValue(target.basePrcFuncAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), CCY.US_DOLLAR, source.basePrcDealAmt.getValue(), msg.slsDt.getValue()));
                // 2018/07/05 S21_NA#26528 Mod End
                ZYPEZDItemValueSetter.setValue(target.dealPrcListPrcAmt, source.basePrcDealAmt);
                ZYPEZDItemValueSetter.setValue(target.funcPrcListPrcAmt, source.basePrcDealAmt);
            }
            ZYPEZDItemValueSetter.setValue(target.corpAdvPrcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(target.prcTierSvcOfferCd, source.prcTierSvcOfferCd);

            // related configuration reference
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcConfigRefPk, dsImptSvcConfigRefPk);

            // QC#17427
            // ZYPEZDItemValueSetter.setValue(target.billToLocNum,
            // source.billToLocNum);
            BILL_TO_CUSTTMsg billTo = getBillToCustByLocNum(msg.glblCmpyCd.getValue(), source.billToLocNum.getValue(), msg.slsDt.getValue());
            if (billTo != null) {
                ZYPEZDItemValueSetter.setValue(target.billToLocNum, billTo.billToCustCd);
            }
            ZYPEZDItemValueSetter.setValue(target.billToCustCd, source.billToCustCd);

            return target;
        // } 2018/07/05 S21_NA#26528 Del Loop

        // return null;
    }
    
    // 2018/07/05 S21_NA#26528 Add Start
    private int getTargetPrcHdr(S21ApiMessageMap msgMap //
            , BigDecimal dsImptSvcLineNum //
            , String dsOrdPosnNum //
            , String ordSrcRefLineNum) {

        Map<Integer, String> targetPrcHdrMap = new HashMap<Integer, String>();

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        for (int i = 0; i < msg.svcPrc.getValidCount(); i++) {

            NWZC225001_svcPrcPMsg source = msg.svcPrc.no(i);
            if (!equalsBigDecimal(source.dsImptSvcLineNum.getValue(), dsImptSvcLineNum)) {

                continue;
            }
            if (!S21StringUtil.isEquals(source.dsOrdPosnNum.getValue(), dsOrdPosnNum)) {

                continue;
            }
            if (!S21StringUtil.isEquals(source.ordSrcRefLineNum.getValue(), ordSrcRefLineNum)) {

                continue;
            }
            String prcTierSvcOfferCd = "";
            if (ZYPCommonFunc.hasValue(source.prcTierSvcOfferCd)) {
                prcTierSvcOfferCd = source.prcTierSvcOfferCd.getValue();
            }
            targetPrcHdrMap.put(i, prcTierSvcOfferCd);
        }

        int target = -1;
        for (Map.Entry<Integer, String> targetHdr : targetPrcHdrMap.entrySet()) {
            if (target < 0) {
                target = targetHdr.getKey();
            } else if (ZYPCommonFunc.hasValue(targetHdr.getValue())) {
                target = targetHdr.getKey();
            }
        }

        return target;
    }

    private BigDecimal calcBasePrc(S21ApiMessageMap msgMap //
            , BigDecimal dsImptSvcLineNum //
            , String dsOrdPosnNum //
            , String ordSrcRefLineNum) {

        BigDecimal value = BigDecimal.ZERO;

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        for (int i = 0; i < msg.svcPrc.getValidCount(); i++) {

            NWZC225001_svcPrcPMsg source = msg.svcPrc.no(i);
            if (!equalsBigDecimal(source.dsImptSvcLineNum.getValue(), dsImptSvcLineNum)) {

                continue;
            }
            if (!S21StringUtil.isEquals(source.dsOrdPosnNum.getValue(), dsOrdPosnNum)) {

                continue;
            }
            if (!S21StringUtil.isEquals(source.ordSrcRefLineNum.getValue(), ordSrcRefLineNum)) {

                continue;
            }

            if (ZYPCommonFunc.hasValue(source.basePrcDealAmt)) {
                value = value.add(source.basePrcDealAmt.getValue());
            }
        }
        return value;
    }
    // 2018/07/05 S21_NA#26528 Add End

    private List<DS_IMPT_SVC_USG_PRCTMsg> createDsImptSvcUsgPrc(//
            S21ApiMessageMap msgMap //
            , DS_IMPT_SVC_DTLTMsg dsImptSvcDtl //
            , DS_IMPT_SVC_PRCTMsg dsImptSvcPrc //
            , BigDecimal dsImptSvcLineNum //
            , String dsOrdPosnNum //
            , String ordSrcRefLineNum //
            , List<String> prcSvcTierTpCdList //
            , List<Map<String, Object>> svcPrcListMap) { // // 2018/04/03 QC#24980 mod

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();
        // List<String> spryPgmKeyList = new ArrayList<String>();
        // for (int i = 0; i < msg.splyPgm.getValidCount(); i++) {
        // NWZC225001_splyPgmPMsg splyPgmMsg = msg.splyPgm.no(i);
        // spryPgmKeyList.add(S21StringUtil.concatStrings(//
        // splyPgmMsg.dsImptSvcLineNum.getValue() //
        // , ",", splyPgmMsg.dsOrdPosnNum.getValue() //
        // , ",", splyPgmMsg.ordSrcRefLineNum.getValue()));
        // }
        Map<String, NWZC225001_splyPgmPMsg> spryPgmKeyMap = new HashMap<String, NWZC225001_splyPgmPMsg>();
        for (int i = 0; i < msg.splyPgm.getValidCount(); i++) {
            NWZC225001_splyPgmPMsg splyPgmMsg = msg.splyPgm.no(i);
            spryPgmKeyMap.put(S21StringUtil.concatStrings(//
                    splyPgmMsg.dsImptSvcLineNum.getValue() //
                    , ",", splyPgmMsg.dsOrdPosnNum.getValue() //
                    , ",", splyPgmMsg.ordSrcRefLineNum.getValue()), splyPgmMsg);
        }

        List<DS_IMPT_SVC_USG_PRCTMsg> targetList = new ArrayList<DS_IMPT_SVC_USG_PRCTMsg>();

        for (int i = 0; i < msg.svcUsgPrc.getValidCount(); i++) {

            NWZC225001_svcUsgPrcPMsg source = msg.svcUsgPrc.no(i);

            if (!equalsBigDecimal(source.dsImptSvcLineNum.getValue(), dsImptSvcLineNum)) {

                continue;
            }

            if (!S21StringUtil.isEquals(source.dsOrdPosnNum.getValue(), dsOrdPosnNum)) {

                continue;
            }

            if (!S21StringUtil.isEquals(source.ordSrcRefLineNum.getValue(), ordSrcRefLineNum)) {

                continue;
            }
            String keyStr = S21StringUtil.concatStrings(//
                    source.dsImptSvcLineNum.getValue() //
                    , ",", source.dsOrdPosnNum.getValue() //
                    , ",", source.ordSrcRefLineNum.getValue());
            boolean isExistSplyPgm = spryPgmKeyMap.containsKey(keyStr);
            String splyAgmtPlnShortNm = null;
            if (isExistSplyPgm) {
                splyAgmtPlnShortNm = spryPgmKeyMap.get(keyStr).splyAgmtPlnShortNm.getValue();
            }

            DS_IMPT_SVC_USG_PRCTMsg target = new DS_IMPT_SVC_USG_PRCTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcUsgPrcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_USG_PRC_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcDtlPk, dsImptSvcDtl.dsImptSvcDtlPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcPrcPk, dsImptSvcPrc.dsImptSvcPrcPk);

            // band code & price book merchandise code
            String maintPrcCatgCd = dsImptSvcPrc.maintPrcCatgCd.getValue();
            if (isExistSplyPgm) {
             // 2018/04/03 QC#24980 mod start
                maintPrcCatgCd = getDefaultMdlSvcPrc(svcPrcListMap, isExistSplyPgm, splyAgmtPlnShortNm, msg.glblCmpyCd.getValue());
             // 2018/04/03 QC#24980 mod end
            }
            Map<String, Object> prcListBandCdPrcBookMdseCd //
            = getPrcListBandCdPrcBookMdseCd(//
                    msg.glblCmpyCd.getValue() //
                    , maintPrcCatgCd //
                    , dsImptSvcPrc.mdlId.getValue() //
                    , dsImptSvcPrc.prcMtrPkgPk.getValue() //
                    , dsImptSvcDtl.prcSvcPlnTpCd.getValue() //
                    , dsImptSvcDtl.prcSvcContrTpCd.getValue() //
                    , source.bllgMtrLbCd.getValue() //
                    , msg.slsDt.getValue() //
                    , splyAgmtPlnShortNm);
            if (prcListBandCdPrcBookMdseCd != null) {

                ZYPEZDItemValueSetter.setValue(target.prcListBandCd, (String) prcListBandCdPrcBookMdseCd.get("PRC_LIST_BAND_CD"));
                ZYPEZDItemValueSetter.setValue(target.prcBookMdseCd, (String) prcListBandCdPrcBookMdseCd.get("MDSE_CD"));
            }

            ZYPEZDItemValueSetter.setValue(target.bllgMtrLbCd, source.bllgMtrLbCd);

            // usage merchandise code
            if (!ZYPCommonFunc.hasValue(source.regMtrLbCd)) {

                ZYPEZDItemValueSetter.setValue(target.usgMdseCd, getUsgMdseCd(msg.glblCmpyCd.getValue(), source.bllgMtrLbCd.getValue(), msg.slsDt.getValue()));
                ZYPEZDItemValueSetter.setValue(target.mlyCopyInclPrcQty, source.copyInclPrcQty);
                ZYPEZDItemValueSetter.setValue(target.xsMtrAmtRate, source.xsMtrAmtRate);
            }

            ZYPEZDItemValueSetter.setValue(target.regMtrLbCd, source.regMtrLbCd);
            ZYPEZDItemValueSetter.setValue(target.contrMtrMultRate, source.contrMtrMultRate);
            target.prcSvcTierTpCd.clear();

            // QC#17427
            // ZYPEZDItemValueSetter.setValue(target.billToLocNum,
            // source.billToLocNum);
            BILL_TO_CUSTTMsg billTo = getBillToCustByLocNum(msg.glblCmpyCd.getValue(), source.billToLocNum.getValue(), msg.slsDt.getValue());
            if (billTo != null) {
                ZYPEZDItemValueSetter.setValue(target.billToLocNum, billTo.billToCustCd);
            }
            ZYPEZDItemValueSetter.setValue(target.billToCustCd, source.billToCustCd);

            // QC#25030
            ZYPEZDItemValueSetter.setValue(target.bllgFreeCopyCnt, source.bllgFreeCopyCnt);
            ZYPEZDItemValueSetter.setValue(target.bllgMinCnt, source.bllgMinCnt);
            ZYPEZDItemValueSetter.setValue(target.bllgMinAmtRate, source.bllgMinAmtRate);
            ZYPEZDItemValueSetter.setValue(target.bllgRollOverRatio, source.bllgRollOverRatio);

            if (ZYPCommonFunc.hasValue(target.regMtrLbCd) || !ZYPCommonFunc.hasValue(dsImptSvcPrc.prcTierSvcOfferCd)) {

                // no tier offered
                targetList.add(target);
            } else if (!ZYPCommonFunc.hasValue(target.regMtrLbCd)) {

                // add tier line
                int tierLineNum = 0;
                if (ZYPCommonFunc.hasValue(source.prcTierLineNum)) {

                    // 2017/03/02 S21_NA#17867 Mod Start
                    // tierLineNum =
                    // source.prcTierLineNum.getValueInt();
                    if (source.prcTierLineNum.getValueInt() > 0) {
                        tierLineNum = source.prcTierLineNum.getValueInt() - 1;
                    } else {
                        tierLineNum = source.prcTierLineNum.getValueInt();
                    }
                    // 2017/03/02 S21_NA#17867 Mod End
                }

                // 2017/03/02 S21_NA#17867 Mod Start
                // if (tierLineNum == 1) {
                if (tierLineNum == 0) {
                    // 2017/03/02 S21_NA#17867 Mod End

                    // billing meter
                    DS_IMPT_SVC_USG_PRCTMsg billingLine = new DS_IMPT_SVC_USG_PRCTMsg();
                    EZDMsg.copy(target, null, billingLine, null);
                    ZYPEZDItemValueSetter.setValue(billingLine.dsImptSvcUsgPrcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_USG_PRC_SQ));
                    billingLine.mlyCopyInclPrcQty.clear();
                    billingLine.xsMtrAmtRate.clear();
                    targetList.add(billingLine);
                }
                
                // 2018/07/05 S21_NA#26528 Add Start
                if(ZYPCommonFunc.hasValue(source.copyInclPrcQty) && BigDecimal.ZERO.compareTo(source.copyInclPrcQty.getValue()) != 0 ){
                    // billing meter
                    DS_IMPT_SVC_USG_PRCTMsg billingLine = new DS_IMPT_SVC_USG_PRCTMsg();
                    EZDMsg.copy(target, null, billingLine, null);
                    ZYPEZDItemValueSetter.setValue(billingLine.dsImptSvcUsgPrcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_USG_PRC_SQ));
                    billingLine.mlyCopyInclPrcQty.clear();
                    billingLine.usgMdseCd.clear();
                    billingLine.xsMtrAmtRate.clear();
                    ZYPEZDItemValueSetter.setValue(billingLine.xsMtrAmtRate, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(billingLine.minCopyVolCnt, BigDecimal.ONE);
                    ZYPEZDItemValueSetter.setValue(billingLine.maxCopyVolCnt, source.copyInclPrcQty);
                    if (prcSvcTierTpCdList != null && prcSvcTierTpCdList.size() >= tierLineNum + 1) {

                        ZYPEZDItemValueSetter.setValue(billingLine.prcSvcTierTpCd, prcSvcTierTpCdList.get(tierLineNum));
                    }
                    targetList.add(billingLine);
                    tierLineNum++;
                }
                // 2018/07/05 S21_NA#26528 Add End

                // 2018/07/05 S21_NA#26528 Mod Start
                if(ZYPCommonFunc.hasValue(source.copyInclPrcQty) && BigDecimal.ZERO.compareTo(source.copyInclPrcQty.getValue()) != 0){
                    ZYPEZDItemValueSetter.setValue(target.minCopyVolCnt, source.copyInclPrcQty.getValue().add(BigDecimal.ONE));
                } else if (BigDecimal.ZERO.compareTo(source.minCopyVolCnt.getValue()) == 0){
                    ZYPEZDItemValueSetter.setValue(target.minCopyVolCnt, BigDecimal.ONE);
                } else{
                    ZYPEZDItemValueSetter.setValue(target.minCopyVolCnt, source.minCopyVolCnt);
                }
                // tier
                // ZYPEZDItemValueSetter.setValue(target.minCopyVolCnt, source.minCopyVolCnt);
                // 2018/07/05 S21_NA#26528 Mod End
                
                // 2017/03/02 S21_NA#17867 Mod Start
                // ZYPEZDItemValueSetter.setValue(target.maxCopyVolCnt,
                // source.maxCopyVolCnt);
                // 2018/07/05 S21_NA#26528 Mod Start
                // if (i == msg.svcUsgPrc.getValidCount() - 1 && source.maxCopyVolCnt.getValueInt() == 0) {
                if (source.maxCopyVolCnt.getValueInt() == 0) {
                    // 2018/07/05 S21_NA#26528 Mod End
                    BigDecimal maxValue = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_TIER_CNT_MAX_VAL, msg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(target.maxCopyVolCnt, maxValue);
                } else {
                    ZYPEZDItemValueSetter.setValue(target.maxCopyVolCnt, source.maxCopyVolCnt);
                }
                // 2017/03/02 S21_NA#17867 Mod End

                DS_IMPT_SVC_USG_PRCTMsg tierLine = new DS_IMPT_SVC_USG_PRCTMsg();
                EZDMsg.copy(target, null, tierLine, null);
                ZYPEZDItemValueSetter.setValue(tierLine.dsImptSvcUsgPrcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_USG_PRC_SQ));
                tierLine.regMtrLbCd.clear();
                tierLine.mlyCopyInclPrcQty.clear();
                tierLine.usgMdseCd.clear();
                if (prcSvcTierTpCdList != null && prcSvcTierTpCdList.size() >= tierLineNum + 1) {

                    ZYPEZDItemValueSetter.setValue(tierLine.prcSvcTierTpCd, prcSvcTierTpCdList.get(tierLineNum));
                }
                targetList.add(tierLine);

                // 2017/03/02 S21_NA#17867 Del
                // if (tierLineNum == 1) {
                //
                // // add first tier line
                // DS_IMPT_SVC_USG_PRCTMsg firstTierLine = new
                // DS_IMPT_SVC_USG_PRCTMsg();
                // EZDMsg.copy(tierLine, null, firstTierLine, null);
                // ZYPEZDItemValueSetter.setValue(firstTierLine.dsImptSvcUsgPrcPk,
                // ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_USG_PRC_SQ));
                // firstTierLine.regMtrLbCd.clear();
                // firstTierLine.mlyCopyInclPrcQty.clear();
                // firstTierLine.usgMdseCd.clear();
                // if (prcSvcTierTpCdList != null &&
                // prcSvcTierTpCdList.size() >= 0 + 1) {
                //
                // ZYPEZDItemValueSetter.setValue(firstTierLine.prcSvcTierTpCd,
                // prcSvcTierTpCdList.get(0));
                // }
                // ZYPEZDItemValueSetter.setValue(firstTierLine.minCopyVolCnt,
                // BigDecimal.ONE);
                //
                // if (ZYPCommonFunc.hasValue(tierLine.minCopyVolCnt)
                // &&
                // !equalsBigDecimal(tierLine.minCopyVolCnt.getValue(),
                // BigDecimal.ZERO)) {
                //
                // ZYPEZDItemValueSetter.setValue(firstTierLine.maxCopyVolCnt,
                // tierLine.minCopyVolCnt.getValue().subtract(BigDecimal.ONE));
                // } else {
                //
                // ZYPEZDItemValueSetter.setValue(firstTierLine.maxCopyVolCnt,
                // BigDecimal.ONE);
                // }
                // targetList.add(firstTierLine);
                // }
                // 2017/03/02 S21_NA#17867 Del
            }
        }

        return targetList;
    }

    private List<DS_IMPT_SVC_ADDL_BASETMsg> createDsImptSvcAddlBase(//
            S21ApiMessageMap msgMap //
            , List<DS_IMPT_ORDTMsg> headerList //
            , List<DS_IMPT_SVC_DTLTMsg> serviceLineList //
            , List<DS_IMPT_ORD_DTLTMsg> lineList //
            , Map<BigDecimal //
            , NWZC225001_svcDtlPMsg> lbrSvcDtlMap //
            , List<Map<String, Object>> svcPrcListMap // 2018/04/03 QC#24980 mod
            , List<BigDecimal> bdlSvcLineNumList) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_SVC_ADDL_BASETMsg> targetList = new ArrayList<DS_IMPT_SVC_ADDL_BASETMsg>();

        DS_IMPT_ORDTMsg header = getHeader(headerList);
        if (header == null) {

            return targetList;
        }

        for (int i = 0; i < msg.svcAddlBasePrc.getValidCount(); i++) {

            NWZC225001_svcAddlBasePrcPMsg source = msg.svcAddlBasePrc.no(i);
            // QC#16142
            boolean isUnbundled = false;
            if (lbrSvcDtlMap.containsKey(source.dsImptSvcLineNum.getValue())) {
                if (bdlSvcLineNumList.contains(source.dsImptSvcLineNum.getValue())) {
                    continue;
                }
                isUnbundled = true;
            }

            DS_IMPT_SVC_DTLTMsg serviceLine = getServiceLine(serviceLineList, source.dsImptSvcLineNum.getValue());
            if (serviceLine == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, source.dsImptSvcLineNum.getValue(), null), NWAM0702E, SERVICE_ADDITIONAL_BASE_CHARGE, SERVICE_LINE);
                continue;
            }

            DS_IMPT_ORD_DTLTMsg line = getLine(lineList, source.dsOrdPosnNum.getValue(), source.ordSrcRefLineNum.getValue());
            if (line == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), source.ordSrcRefLineNum.getValue(), source.dsImptSvcLineNum.getValue(), null), NWAM0702E,
                        SERVICE_ADDITIONAL_BASE_CHARGE, ORDER_LINE);
                continue;
            }

            DS_IMPT_SVC_ADDL_BASETMsg target = new DS_IMPT_SVC_ADDL_BASETMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcAddlBasePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_ADDL_BASE_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcDtlPk, serviceLine.dsImptSvcDtlPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdDtlPk, line.dsImptOrdDtlPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, line.dsImptOrdPk);

            // price
            // Mod Start 2017/08/30 QC#20822
//            DS_ORD_TP_PROC_DFNTMsg dsOrdTpPrcDfn = getDsOrdTpPrcDfn(msg.glblCmpyCd.getValue(), header.dsOrdTpCd.getValue(), msg.slsDt.getValue());
            // 2018/04/03 QC#24980 mod start
            String svcPriceList = getDefaultMdlSvcPrc(svcPrcListMap, false, null, msg.glblCmpyCd.getValue());
            // 2018/04/03 QC#24980 mod end
            // Mod End 2017/08/30 QC#20822
            if (isUnbundled) {
                ZYPEZDItemValueSetter.setValue(target.addlBasePrcDealAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(target.addlBasePrcFuncAmt, BigDecimal.ZERO);
                // Mod Start 2017/08/30 QC#20822
//                if (dsOrdTpPrcDfn != null) {
                if (null != svcPriceList) {
                    // price category code
//                    ZYPEZDItemValueSetter.setValue(target.addlBasePrcCatgCd, dsOrdTpPrcDfn.defPrcCatgCd);
                    ZYPEZDItemValueSetter.setValue(target.addlBasePrcCatgCd, svcPriceList);
                }
                // Mod End 2017/08/30 QC#20822
            } else {
                ZYPEZDItemValueSetter.setValue(target.addlBasePrcDealAmt, source.addlBasePrcDealAmt);
                // Mod Start 2017/08/30 QC#20822
//                if (dsOrdTpPrcDfn != null) {
                if (null != svcPriceList) {

                    // price category code
//                    ZYPEZDItemValueSetter.setValue(target.addlBasePrcCatgCd, dsOrdTpPrcDfn.defPrcCatgCd);
                    ZYPEZDItemValueSetter.setValue(target.addlBasePrcCatgCd, svcPriceList);

                    // function amount
                    if (ZYPCommonFunc.hasValue(target.addlBasePrcCatgCd)) {

                        PRC_CATGTMsg prcCatg = new PRC_CATGTMsg();
                        ZYPEZDItemValueSetter.setValue(prcCatg.glblCmpyCd, msg.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(prcCatg.prcCatgCd, target.addlBasePrcCatgCd);
                        prcCatg = (PRC_CATGTMsg) EZDTBLAccessor.findByKey(prcCatg);
                        if (prcCatg != null) {

                            ZYPEZDItemValueSetter.setValue(target.addlBasePrcFuncAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), prcCatg.ccyCd.getValue(), source.addlBasePrcDealAmt.getValue(), msg.slsDt.getValue()));
                        }
                    }
                }
                // Mod End 2017/08/30 QC#20822
            }
            target.dealPrcListPrcAmt.clear();
            target.funcPrcListPrcAmt.clear();
            // target.svcPrcCatgCd.clear();
            ZYPEZDItemValueSetter.setValue(target.svcPrcCatgCd, SVC_PRC_CATG.ACC_UNIT_BASE_CHARGE);
            target.prcListEquipConfigNum.clear();

            targetList.add(target);
        }

        return targetList;
    }

    private List<DS_IMPT_SVC_ADDL_CHRGTMsg> createDsImptSvcAddlChrg(//
            S21ApiMessageMap msgMap //
            , List<DS_IMPT_ORDTMsg> headerList //
            , List<DS_IMPT_SVC_DTLTMsg> serviceLineList //
            , List<DS_IMPT_ORD_DTLTMsg> lineList //
            , Map<BigDecimal, NWZC225001_svcDtlPMsg> lbrSvcDtlMap //
            , List<Map<String, Object>> svcPrcListMap // 2018/04/03 QC#24980 mod start
            , List<BigDecimal> bdlSvcLineNumList) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_SVC_ADDL_CHRGTMsg> targetList = new ArrayList<DS_IMPT_SVC_ADDL_CHRGTMsg>();

        DS_IMPT_ORDTMsg header = getHeader(headerList);
        if (header == null) {

            return targetList;
        }

        for (int i = 0; i < msg.svcAddlChrgPrc.getValidCount(); i++) {

            NWZC225001_svcAddlChrgPrcPMsg source = msg.svcAddlChrgPrc.no(i);
            // QC#16142
            boolean isUnbundled = false;
            if (lbrSvcDtlMap.containsKey(source.dsImptSvcLineNum.getValue())) {
                if (bdlSvcLineNumList.contains(source.dsImptSvcLineNum.getValue())) {
                    continue;
                }
                isUnbundled = true;
            }

            DS_IMPT_SVC_DTLTMsg serviceLine = getServiceLine(serviceLineList, source.dsImptSvcLineNum.getValue());
            if (serviceLine == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, source.dsImptSvcLineNum.getValue(), null), NWAM0702E, SERVICE_ADDITIONAL_BASE_CHARGE, SERVICE_LINE);
                continue;
            }

            DS_IMPT_ORD_DTLTMsg line = getLine(lineList, source.dsOrdPosnNum.getValue(), source.ordSrcRefLineNum.getValue());
            if (line == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), source.ordSrcRefLineNum.getValue(), source.dsImptSvcLineNum.getValue(), null), NWAM0702E,
                        SERVICE_ADDITIONAL_BASE_CHARGE, ORDER_LINE);
                continue;
            }

            DS_IMPT_SVC_ADDL_CHRGTMsg target = new DS_IMPT_SVC_ADDL_CHRGTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcAddlChrgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_ADDL_CHRG_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcDtlPk, serviceLine.dsImptSvcDtlPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdDtlPk, line.dsImptOrdDtlPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, line.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(target.addlChrgMdseCd, source.addlChrgMdseCd);

            // price category code
            ZYPEZDItemValueSetter.setValue(target.addlChrgPrcCatgCd, (String) svcPrcListMap.get(0).get("THIRD_BIZ_CTX_ATTRB_TXT"));
            // price
            if (isUnbundled) {
                ZYPEZDItemValueSetter.setValue(target.addlChrgPrcDealAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(target.addlChrgPrcFuncAmt, BigDecimal.ZERO);
            } else {
                ZYPEZDItemValueSetter.setValue(target.addlChrgPrcDealAmt, source.addlChrgPrcDealAmt);
                // DS_ORD_TP_PROC_DFNTMsg dsOrdTpPrcDfn =
                // getDsOrdTpPrcDfn(msg.glblCmpyCd.getValue(),
                // header.dsOrdTpCd.getValue(), msg.slsDt.getValue());
                // if (dsOrdTpPrcDfn != null) {

                // function amount
                if (ZYPCommonFunc.hasValue(target.addlChrgPrcCatgCd)) {

                    PRC_CATGTMsg prcCatg = new PRC_CATGTMsg();
                    ZYPEZDItemValueSetter.setValue(prcCatg.glblCmpyCd, msg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(prcCatg.prcCatgCd, target.addlChrgPrcCatgCd);
                    prcCatg = (PRC_CATGTMsg) EZDTBLAccessor.findByKey(prcCatg);
                    if (prcCatg != null) {

                        ZYPEZDItemValueSetter.setValue(target.addlChrgPrcFuncAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), prcCatg.ccyCd.getValue(), source.addlChrgPrcDealAmt.getValue(), msg.slsDt.getValue()));
                    }
                }
                // }
            }

            target.dealPrcListPrcAmt.clear();
            target.funcPrcListPrcAmt.clear();
            // 2018/05/22 S21_NA#26068 Add Start
            // target.svcPrcCatgCd.clear();
            ZYPEZDItemValueSetter.setValue(target.svcPrcCatgCd, SVC_PRC_CATG.MAIN_UNIT_ADDITIONAL_CHARGE);
            // 2018/05/22 S21_NA#26068 Add End

            ZYPEZDItemValueSetter.setValue(target.printDtlFlg, getFlagValue(source.printDtlFlg.getValue()));

            targetList.add(target);
        }

        return targetList;
    }

    // 2018/12/17 S21_NA#29580 Mod Start
//    private Map<SCHED_LIST, List<?>> createDsImptShedTmpl( //
//            S21ApiMessageMap msgMap //
//            , List<DS_IMPT_ORDTMsg> headerList //
//            , List<DS_IMPT_SVC_DTLTMsg> serviceLineList //
//            , List<DS_IMPT_SVC_CONFIG_REFTMsg> configRefList //
//            , List<Map<String, Object>> svcPrcListMap // 2018/04/03 QC#24980 mod start
//            , List<DS_IMPT_SVC_PRCTMsg> svcPrcList //
//            , List<DS_IMPT_SVC_ADDL_BASETMsg> additionalBaseList
//            , List<DS_IMPT_ORD_DTLTMsg> lineList) {
    private Map<SCHED_LIST, List<?>> createDsImptShedTmpl( //
            S21ApiMessageMap msgMap //
            , List<DS_IMPT_ORDTMsg> headerList //
            , List<DS_IMPT_SVC_DTLTMsg> serviceLineList //
            , List<DS_IMPT_SVC_CONFIG_REFTMsg> configRefList //
            , List<Map<String, Object>> svcPrcListMap // 2018/04/03 QC#24980 mod start
            , List<DS_IMPT_SVC_PRCTMsg> svcPrcList //
            , List<DS_IMPT_SVC_ADDL_BASETMsg> additionalBaseList
            , List<DS_IMPT_ORD_DTLTMsg> lineList
            , Map<BigDecimal, NWZC225001_svcDtlPMsg> lbrSvcDtlMap //
            ,  List<BigDecimal> bdlSvcLineNumList) {
        // 2018/12/17 S21_NA#29580 Mod End

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        Map<SCHED_LIST, List<?>> shedTmplMap = new HashMap<SCHED_LIST, List<?>>();

        List<DS_IMPT_SCHD_TMPLTMsg> targetList = new ArrayList<DS_IMPT_SCHD_TMPLTMsg>();
        List<DS_IMPT_SCHD_TMPL_LINETMsg> supplyProgramLineList = new ArrayList<DS_IMPT_SCHD_TMPL_LINETMsg>();

        shedTmplMap.put(SCHED_LIST.TMPL, targetList);
        shedTmplMap.put(SCHED_LIST.TMPL_LINE, supplyProgramLineList);

        DS_IMPT_ORDTMsg header = getHeader(headerList);
        if (header == null) {

            return shedTmplMap;
        }

        for (int i = 0; i < msg.splyPgm.getValidCount(); i++) {

            NWZC225001_splyPgmPMsg source = msg.splyPgm.no(i);
            
            // 2018/12/17 S21_NA#29580 Add Start
            if (lbrSvcDtlMap.containsKey(source.dsImptSvcLineNum.getValue())) {
                if (bdlSvcLineNumList.contains(source.dsImptSvcLineNum.getValue())) {
                    continue;
                }
            }
            // 2018/12/17 S21_NA#29580 Add End

            DS_IMPT_SVC_DTLTMsg serviceLine = getServiceLine(serviceLineList, source.dsImptSvcLineNum.getValue());
            if (serviceLine == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, source.dsImptSvcLineNum.getValue(), null), NWAM0702E, SUPPLY_PROGRAM, SERVICE_LINE);
                continue;
            }

            DS_IMPT_SCHD_TMPLTMsg target = new DS_IMPT_SCHD_TMPLTMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptSchdTmplPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SCHD_TMPL_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcDtlPk, serviceLine.dsImptSvcDtlPk);
            target.refCpoOrdNum.clear();
            ZYPEZDItemValueSetter.setValue(target.dsOrdTpCd, source.dsOrdTpCd);
            target.custIssPoNum.clear();
            target.custIssPoDt.clear();
            ZYPEZDItemValueSetter.setValue(target.sysSrcCd, SYS_SRC.S21_ORDER);
            ZYPEZDItemValueSetter.setValue(target.sysSrcRefNum, source.ordSrcRefNum);
            ZYPEZDItemValueSetter.setValue(target.cratTs, header.ordSrcImptTs);
            ZYPEZDItemValueSetter.setValue(target.splyAgmtDocTpCd, source.splyAgmtDocTpCd);
            ZYPEZDItemValueSetter.setValue(target.qtyContrCapQty, source.qtyContrCapQty);
            target.splyAgmtPlnPk.clear();
            ZYPEZDItemValueSetter.setValue(target.splyAgmtPlnShortNm, source.splyAgmtPlnShortNm);
            ZYPEZDItemValueSetter.setValue(target.splyBaseAmt, source.splyBaseAmt);

            targetList.add(target);

            supplyProgramLineList.addAll(//
                    createDsImptShedTmplLine(//
                            msgMap, serviceLine, configRefList, lineList, target.dsImptSchdTmplPk.getValue(), source.dsOrdPosnNum.getValue()));

            // Mod Start 2017/08/30 QC#20822
//            setSvcPrcPrcCatgCd(svcPrcListMap, svcPrcList, serviceLine.dsImptSvcDtlPk.getValue());
            // 2018/04/03 QC#24980 mod start
            setSvcPrcPrcCatgCd(msgMap, svcPrcListMap, svcPrcList, additionalBaseList, serviceLine.dsImptSvcDtlPk.getValue(),  source.splyAgmtPlnShortNm.getValue());
            // 2018/04/03 QC#24980 mod end
            // Mod End 2017/08/30 QC#20822
        }

        return shedTmplMap;
    }

    private void setSvcPrcPrcCatgCd( //
            S21ApiMessageMap msgMap //
            , List<Map<String, Object>> svcPrcListMap // 2018/04/03 QC#24980 mod
            , List<DS_IMPT_SVC_PRCTMsg> svcPrcList //
            , List<DS_IMPT_SVC_ADDL_BASETMsg> additionalBaseList //
            , BigDecimal dsImptSvcDtlPk
            , String splyAgmtPlnShortNm) { // 2018/04/03 QC#24980 add

        // Add Start 2017/08/30 QC#20822
        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();
        // Add End 2017/08/30 QC#20822
        for (DS_IMPT_SVC_PRCTMsg svcPrc : svcPrcList) {

            if (!equalsBigDecimal(svcPrc.dsImptSvcDtlPk.getValue(), dsImptSvcDtlPk)) {

                continue;
            }
            ZYPEZDItemValueSetter.setValue(svcPrc.maintPrcCatgCd, getDefaultMdlSvcPrc(svcPrcListMap, true, splyAgmtPlnShortNm, msg.glblCmpyCd.getValue()));
            ZYPEZDItemValueSetter.setValue(svcPrc.maintFlPrcCatgCd, svcPrc.maintPrcCatgCd);
        }

        // Add Start 2017/08/30 QC#20822
        for (DS_IMPT_SVC_ADDL_BASETMsg additionalBase : additionalBaseList) {

            if (!equalsBigDecimal(additionalBase.dsImptSvcDtlPk.getValue(), dsImptSvcDtlPk)) {

                continue;
            }
            ZYPEZDItemValueSetter.setValue(additionalBase.addlBasePrcCatgCd, getDefaultMdlSvcPrc(svcPrcListMap, true, splyAgmtPlnShortNm, msg.glblCmpyCd.getValue()));

            if (ZYPCommonFunc.hasValue(additionalBase.addlBasePrcCatgCd)) {

                PRC_CATGTMsg prcCatg = new PRC_CATGTMsg();
                ZYPEZDItemValueSetter.setValue(prcCatg.glblCmpyCd, msg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(prcCatg.prcCatgCd, additionalBase.addlBasePrcCatgCd);
                prcCatg = (PRC_CATGTMsg) EZDTBLAccessor.findByKey(prcCatg);
                if (prcCatg != null) {

                    ZYPEZDItemValueSetter.setValue(additionalBase.addlBasePrcFuncAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), prcCatg.ccyCd.getValue(), additionalBase.addlBasePrcDealAmt.getValue(), msg.slsDt.getValue()));
                }
            }
        }
        // Add End 2017/08/30 QC#20822
    }

    private List<DS_IMPT_SCHD_TMPL_LINETMsg> createDsImptShedTmplLine(//
            S21ApiMessageMap msgMap //
            , DS_IMPT_SVC_DTLTMsg dsImptSvcDtl //
            , List<DS_IMPT_SVC_CONFIG_REFTMsg> configRefList //
            , List<DS_IMPT_ORD_DTLTMsg> lineList //
            , BigDecimal dsImptSchdTmplPk //
            , String dsOrdPosnNum) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_SCHD_TMPL_LINETMsg> targetList = new ArrayList<DS_IMPT_SCHD_TMPL_LINETMsg>();

        for (int i = 0; i < msg.splyPgmLine.getValidCount(); i++) {

            NWZC225001_splyPgmLinePMsg source = msg.splyPgmLine.no(i);

            if (!equalsBigDecimal(dsImptSvcDtl.dsImptSvcLineNum.getValue(), source.dsImptSvcLineNum.getValue())) {

                continue;
            }
            if (!S21StringUtil.isEquals(dsOrdPosnNum, source.dsOrdPosnNum.getValue())) {
                continue;
            }

            DS_IMPT_ORD_DTLTMsg line = getLine(lineList, source.dsOrdPosnNum.getValue(), source.ordSrcRefLineNum.getValue());
            if (line == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), source.ordSrcRefLineNum.getValue(), source.dsImptSvcLineNum.getValue(), null), NWAM0702E, SUPPLY_PROGRAM_LINE,
                        ORDER_LINE);
                continue;
            }

            DS_IMPT_SVC_CONFIG_REFTMsg configRef = getConfigRef(configRefList, dsImptSvcDtl.dsImptSvcDtlPk.getValue(), line.dsImptOrdDtlPk.getValue());
            if (configRef == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), source.ordSrcRefLineNum.getValue(), source.dsImptSvcLineNum.getValue(), null), NWAM0702E, SUPPLY_PROGRAM_LINE,
                        SERVICE_CONFIG_REFERENCE);
                continue;
            }

            DS_IMPT_SCHD_TMPL_LINETMsg target = new DS_IMPT_SCHD_TMPL_LINETMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptSchdTmplLinePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SCHD_TMPL_LINE_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptSchdTmplPk, dsImptSchdTmplPk);
            ZYPEZDItemValueSetter.setValue(target.schdTmplLineNum, source.schdTmplLineNum);
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcConfigRefPk, configRef.dsImptSvcConfigRefPk);
            ZYPEZDItemValueSetter.setValue(target.mdseCd, source.mdseCd);
            ZYPEZDItemValueSetter.setValue(target.schdAllwQty, source.schdAllwQty);
            ZYPEZDItemValueSetter.setValue(target.shpgIntvlCd, source.shpgIntvlCd);
            ZYPEZDItemValueSetter.setValue(target.schdShpgQty, source.schdShpgQty);
            ZYPEZDItemValueSetter.setValue(target.firstShipQty, source.firstShipQty);

            targetList.add(target);
        }

        return targetList;
    }

    private void createOrder(S21ApiMessageMap msgMap, BigDecimal dsImptOrdPk, ONBATCH_TYPE onBatchType) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        NWZC226001PMsg param = new NWZC226001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, msg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.slsDt, msg.slsDt);
        ZYPEZDItemValueSetter.setValue(param.dsImptOrdPk, dsImptOrdPk);
        if (S21StringUtil.isEquals(msg.xxModeCd.getValue(), MODE_VALIDATE)) {

            ZYPEZDItemValueSetter.setValue(param.xxModeCd, NWZC226001Constant.MODE_VALIDATE);
        } else if (S21StringUtil.isEquals(msg.xxModeCd.getValue(), MODE_CREATE_ORDER)) {

            ZYPEZDItemValueSetter.setValue(param.xxModeCd, NWZC226001Constant.MODE_CREATE_ORDER);
        }

        new NWZC226001().execute(param, onBatchType);

        for (int i = 0; i < param.xxMsgPrmList.getValidCount(); i++) {

            NWZC225001_xxMsgPrmListPMsg msgPrm = new NWZC225001_xxMsgPrmListPMsg();
            EZDMsg.copy(param.xxMsgPrmList.no(i), null, msgPrm, null);
            setMsgIdWithParam(msgMap, msgPrm, param.xxMsgPrmList.no(i).dsImptOrdErrMsgId.getValue());
        }

        if (param.xxMsgPrmList.getValidCount() == 0) {

            if (!ZYPCommonFunc.hasValue(param.cpoOrdNum)) {

                // invalid case
                if (S21StringUtil.isEquals(msg.xxModeCd.getValue(), MODE_CREATE_ORDER)) {

                    setMsgIdWithParam(msgMap, null, NWZM2200E);
                } else {

                    setMsgIdWithParam(msgMap, null, NWZM2201E);
                }

                return;
            }

            // success
            if (S21StringUtil.isEquals(msg.xxModeCd.getValue(), MODE_CREATE_ORDER)) {

                ZYPEZDItemValueSetter.setValue(msg.cpoOrdNum, param.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(msg.dsCpoCratTs, param.dsCpoCratTs);

                // update status & CPO order number
                DS_IMPT_ORDTMsg dsImptOrd = new DS_IMPT_ORDTMsg();
                ZYPEZDItemValueSetter.setValue(dsImptOrd.glblCmpyCd, msg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrd.dsImptOrdPk, dsImptOrdPk);
                dsImptOrd = (DS_IMPT_ORDTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(dsImptOrd);
                if (dsImptOrd != null) {

                    ZYPEZDItemValueSetter.setValue(dsImptOrd.cpoOrdNum, msg.cpoOrdNum);
                    ZYPEZDItemValueSetter.setValue(dsImptOrd.imptStsCd, IMPT_STS.SUCCESS);
                    S21FastTBLAccessor.update(dsImptOrd);
                    if (!S21StringUtil.isEquals(dsImptOrd.getReturnCode(), S21FastTBLAccessor.RTNCD_NORMAL)) {

                        // message for update failure
                        setMsgIdWithParam(msgMap, null, NWZM2200E);
                        return;
                    }
                }
            } else {

                // message for validation successfully
                setMsgIdWithParam(msgMap, null, NWZM2202E);
                return;
            }
        }
        return;
    }

    private static DS_ORD_TPTMsg getDsOrdTp(String glblCmpyCd, String dsOrdTpCd) {

        if (S21StringUtil.isEmpty(dsOrdTpCd)) {

            return null;
        }

        DS_ORD_TPTMsg tMsg = new DS_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);

        DS_ORD_TPTMsg outTMsg = (DS_ORD_TPTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (outTMsg != null) {

            return outTMsg;
        }

        return null;
    }

    private static BILL_TO_CUSTTMsg getBillToCustByLocNum(String glblCmpyCd, String locNum, String slsDt) {

        if (S21StringUtil.isEmpty(locNum)) {

            return null;
        }

        BILL_TO_CUSTTMsg tMsg = new BILL_TO_CUSTTMsg();
        tMsg.setSQLID("060");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("locNum01", locNum);
        BILL_TO_CUSTTMsgArray tMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {

            BILL_TO_CUSTTMsg outTMsg = tMsgArray.no(i);

            if (slsDt.compareTo(outTMsg.effFromDt.getValue()) < 0) {

                continue;
            }

            if (ZYPCommonFunc.hasValue(outTMsg.effThruDt) && slsDt.compareTo(outTMsg.effThruDt.getValue()) > 0) {

                continue;
            }

            if (S21StringUtil.isEquals(outTMsg.rgtnStsCd.getValue(), RGTN_STS.READY_FOR_ORDER_TAKING)) {

                return outTMsg;
            }
        }

        return null;
    }

    private static SHIP_TO_CUSTTMsg getShipToCustByLocNum(String glblCmpyCd, String locNum, String slsDt) {

        if (S21StringUtil.isEmpty(locNum)) {

            return null;
        }

        SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
        tMsg.setSQLID("048");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("locNum01", locNum);
        SHIP_TO_CUSTTMsgArray tMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {

            SHIP_TO_CUSTTMsg outTMsg = tMsgArray.no(i);

            if (slsDt.compareTo(outTMsg.effFromDt.getValue()) < 0) {

                continue;
            }

            if (ZYPCommonFunc.hasValue(outTMsg.effThruDt) && slsDt.compareTo(outTMsg.effThruDt.getValue()) > 0) {

                continue;
            }

            if (S21StringUtil.isEquals(outTMsg.rgtnStsCd.getValue(), RGTN_STS.READY_FOR_ORDER_TAKING)) {

                return outTMsg;
            }
        }

        return null;
    }

    private static DS_ORD_TP_PROC_DFNTMsg getDsOrdTpPrcDfn(String glblCmpyCd, String dsOrdTpCd, String slsDt) {

        if (S21StringUtil.isEmpty(dsOrdTpCd)) {

            return null;
        }

        DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);

        DS_ORD_TP_PROC_DFNTMsg outTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (outTMsg != null) {

            if (slsDt.compareTo(outTMsg.effFromDt.getValue()) < 0) {

                return null;
            }

            if (ZYPCommonFunc.hasValue(outTMsg.effThruDt) && slsDt.compareTo(outTMsg.effThruDt.getValue()) > 0) {

                return null;
            }

            if (S21StringUtil.isEquals(outTMsg.actvFlg.getValue(), ZYPConstant.FLG_ON_Y)) {

                return outTMsg;
            }
        }
        return null;
    }

    private static FRT_CONDTMsg getFrtCond(String glblCmpyCd, String frtCondCd) {

        if (S21StringUtil.isEmpty(frtCondCd)) {

            return null;
        }

        FRT_CONDTMsg tMsg = new FRT_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.frtCondCd, frtCondCd);

        FRT_CONDTMsg outTMsg = (FRT_CONDTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (outTMsg != null) {

            return outTMsg;
        }
        return null;
    }

    private static CNTYTMsg getCnty(String glblCmpyCd, BigDecimal cntyPk) {

        if (cntyPk == null) {

            return null;
        }

        CNTYTMsg tMsg = new CNTYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cntyPk, cntyPk);

        CNTYTMsg outTMsg = (CNTYTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (outTMsg != null) {

            return outTMsg;
        }
        return null;
    }

    private static PRC_CONTRTMsg getPrcContr(String glblCmpyCd, BigDecimal prcContrPk) {

        if (prcContrPk == null) {

            return null;
        }

        PRC_CONTRTMsg tMsg = new PRC_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcContrPk, prcContrPk);

        PRC_CONTRTMsg outTMsg = (PRC_CONTRTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (outTMsg != null) {

            return outTMsg;
        }
        return null;
    }

    private static String getRddDt(NWZC225001_dlvyInfoPMsgArray dlvyInfoArray) {

        if (dlvyInfoArray == null) {

            return null;
        }

        String minPosition = null;
        String firstRddDt = null;
        for (int i = 0; i < dlvyInfoArray.getValidCount(); i++) {

            NWZC225001_dlvyInfoPMsg dlvyInfo = dlvyInfoArray.no(i);

            if (!ZYPCommonFunc.hasValue(dlvyInfo.dsOrdPosnNum)) {

                continue;
            }

            if (S21StringUtil.isEmpty(minPosition)) {

                minPosition = dlvyInfo.dsOrdPosnNum.getValue();
                firstRddDt = dlvyInfo.rddDt.getValue();
            }

            if (String.format(minPosition, "%06s").compareTo(String.format(dlvyInfo.dsOrdPosnNum.getValue(), "%06s")) > 0) {

                firstRddDt = dlvyInfo.rddDt.getValue();
            }
        }

        return firstRddDt;
    }

    private static String getPmtTermCashDiscCdFromBillTo(String glblCmpyCd, String billToCustCd, String billToCustAcctCd) {

        if (S21StringUtil.isEmpty(billToCustCd) && S21StringUtil.isEmpty(billToCustAcctCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("billToCustAcctCd", billToCustAcctCd);

        return NWZC225001Query.getInstance().queryString("getPmtTermCashDiscCdFromBillTo", ssmParam);
    }

    private static DS_IMPT_ORDTMsg getHeader(List<DS_IMPT_ORDTMsg> headerList) {

        if (headerList == null) {

            return null;
        }
        if (headerList.size() == 0) {

            return null;
        }

        return headerList.get(0);
    }

    private static String getMdlDescTxt(String glblCmpyCd, BigDecimal mdlId) {

        // 2017/06/02 QC#18625 UPD START
        // if (mdlId == null) {
        if (mdlId == null || BigDecimal.ZERO.compareTo(mdlId) == 0) {
            // 2017/06/02 QC#18625 UPD E N D
            return null;
        }

        DS_MDLTMsg tMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, mdlId);

        // 2017/09/25 QC#20799 Mod Start
        //DS_MDLTMsg outTMsg = (DS_MDLTMsg) S21FastTBLAccessor.findByKey(tMsg);
        DS_MDLTMsg outTMsg = (DS_MDLTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        // 2017/09/25 QC#20799 Mod End
        if (outTMsg != null) {

            return outTMsg.mdlDescTxt.getValue();
        }
        return null;
    }

    private static String getShipToAcctNm(String glblCmpyCd, String shipToCustCd) {

        if (S21StringUtil.isEmpty(shipToCustCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shipToCustCd", shipToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return NWZC225001Query.getInstance().queryString("getShipToAcctNm", ssmParam);
    }

    private static DS_IMPT_ORD_CONFIGTMsg getConfig(List<DS_IMPT_ORD_CONFIGTMsg> configList, String dsOrdPosnNum) {

        if (S21StringUtil.isEmpty(dsOrdPosnNum)) {

            return null;
        }

        for (DS_IMPT_ORD_CONFIGTMsg config : configList) {

            if (!ZYPCommonFunc.hasValue(config.dsOrdPosnNum)) {

                continue;
            }

            if (S21StringUtil.isEquals(dsOrdPosnNum, config.dsOrdPosnNum.getValue())) {

                return config;
            }
        }

        return null;
    }

    @SuppressWarnings("unused")
    private static String getSlsCrQuoteFlg(String glblCmpyCd, String slsRepRoleTpCd) {

        String slsCrQuotFlg = ZYPConstant.FLG_OFF_N;
        if (S21StringUtil.isEmpty(slsRepRoleTpCd)) {

            return slsCrQuotFlg;
        }
        LINE_BIZ_ROLE_TPTMsg lineBizRoleTp = new LINE_BIZ_ROLE_TPTMsg();
        ZYPEZDItemValueSetter.setValue(lineBizRoleTp.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(lineBizRoleTp.lineBizRoleTpCd, slsRepRoleTpCd);
        lineBizRoleTp = (LINE_BIZ_ROLE_TPTMsg) S21FastTBLAccessor.findByKey(lineBizRoleTp);
        if (lineBizRoleTp != null) {

            slsCrQuotFlg = lineBizRoleTp.slsCrQuotFlg.getValue();
        }
        return slsCrQuotFlg;
    }

    private static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        if (S21StringUtil.isEmpty(mdseCd)) {

            return null;
        }

        MDSETMsg mdse = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdse == null) {

            return null;
        }

        if (!S21StringUtil.isEquals(mdse.rgtnStsCd.getValue(), RGTN_STS.READY_FOR_ORDER_TAKING)) {

            return null;
        }

        return mdse;
    }

    private static String getRddDt(NWZC225001_dlvyInfoPMsgArray dlvyInfoArray, String dsOrdPosnNum) {

        if (S21StringUtil.isEmpty(dsOrdPosnNum)) {

            return null;
        }

        for (int i = 0; i < dlvyInfoArray.getValidCount(); i++) {

            NWZC225001_dlvyInfoPMsg dlvyInfo = dlvyInfoArray.no(i);
            if (S21StringUtil.isEquals(dlvyInfo.dsOrdPosnNum.getValue(), dsOrdPosnNum)) {

                return dlvyInfo.rddDt.getValue();
            }
        }
        return null;
    }

    private static BigDecimal getUnitNetWt(String glblCmpyCd, String mdseCd, String custUomCd, BigDecimal ordCustUomQty) {

        if (S21StringUtil.isEmpty(mdseCd)) {

            return null;
        }

        if (S21StringUtil.isEmpty(custUomCd)) {

            return null;
        }

        if (ordCustUomQty == null) {

            return null;
        }

        MDSE_STORE_PKGTMsg mdseStorePkg = getMdseStorePkg(glblCmpyCd, mdseCd, custUomCd);
        if (mdseStorePkg == null) {

            return null;
        }
        if (!ZYPCommonFunc.hasValue(mdseStorePkg.inPoundWt)) {

            return null;
        }

        return mdseStorePkg.inPoundWt.getValue().multiply(ordCustUomQty);
    }

    private static MDSE_STORE_PKGTMsg getMdseStorePkg(String glblCmpyCd, String mdseCd, String custUomCd) {

        if (S21StringUtil.isEmpty(mdseCd)) {

            return null;
        }

        if (S21StringUtil.isEmpty(custUomCd)) {

            return null;
        }

        MDSETMsg mdse = getMdse(glblCmpyCd, mdseCd);
        if (mdse == null) {

            return null;
        }

        MDSE_STORE_PKGTMsg tMsg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdse.mdseCd.getValue());

        MDSE_STORE_PKGTMsg outTMsg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (outTMsg != null) {

            return outTMsg;
        }

        return null;
    }

    private static String getCustIstlFlg(String glblCmpyCd, NWZC225001_istlInfoPMsgArray istlInfoArray, String dsOrdPosnNum) {

        if (S21StringUtil.isEmpty(dsOrdPosnNum)) {

            return ZYPConstant.FLG_OFF_N;
        }

        for (int i = 0; i < istlInfoArray.getValidCount(); i++) {

            NWZC225001_istlInfoPMsg istlInfo = istlInfoArray.no(i);
            if (S21StringUtil.isEquals(istlInfo.dsOrdPosnNum.getValue(), dsOrdPosnNum)) {

                if (!ZYPCommonFunc.hasValue(istlInfo.svcIstlRuleNum)) {

                    return ZYPConstant.FLG_OFF_N;
                }

                SVC_ISTL_RULETMsg svcIstlRule = new SVC_ISTL_RULETMsg();
                ZYPEZDItemValueSetter.setValue(svcIstlRule.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(svcIstlRule.svcIstlRuleNum, istlInfo.svcIstlRuleNum);
                svcIstlRule = (SVC_ISTL_RULETMsg) EZDTBLAccessor.findByKey(svcIstlRule);
                if (svcIstlRule == null) {

                    return ZYPConstant.FLG_OFF_N;
                }
                return getFlagValue(svcIstlRule.svcCustIstlFlg.getValue());
            }
        }

        return ZYPConstant.FLG_OFF_N;
    }

    private static BigDecimal getSvcMachMstrPkBySerNum(String glblCmpyCd, String mdseCd, String serNum, String slsDt) {

        if (S21StringUtil.isEmpty(serNum)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd + PERCENT); // QC#22658 2017/11/20 Add
        ssmParam.put("serNum", serNum);
        ssmParam.put("slsDt", slsDt);

        return NWZC225001Query.getInstance().queryBigDecimal("getSvcMachMstrPkBySerNum", ssmParam);
    }

    private static String getFlagValue(String flag) {

        if (!S21StringUtil.isEquals(flag, ZYPConstant.FLG_ON_Y)) {

            return ZYPConstant.FLG_OFF_N;
        }
        return ZYPConstant.FLG_ON_Y;
    }

    private static BigDecimal calcFuncAmt(String glblCmpyCd, String dealCcyCd, BigDecimal dealAmt, String slsDt) {

        if (S21StringUtil.isEmpty(dealCcyCd)) {

            return null;
        }

        if (dealAmt == null) {

            return null;
        }

        BigDecimal funcAmt = null;
        NWXC001001ExchangeData exchData = new NWXC001001ExchangeData();
        exchData.setGlblCmpyCd(glblCmpyCd);
        exchData.setCcyCd(dealCcyCd);
        exchData.setSlsDt(slsDt);
        List<NWXC001001ExchangePriceData> priceDataList = new ArrayList<NWXC001001ExchangePriceData>();
        NWXC001001ExchangeAmoutData exchAmtData = new NWXC001001ExchangeAmoutData();

        NWXC001001ExchangeAmount grsAmt = new NWXC001001ExchangeAmount();
        grsAmt.setDealAmt(dealAmt);

        exchAmtData.setGrsAmt(grsAmt);
        priceDataList.add(exchAmtData);

        exchData.setPriceData(priceDataList);

        NWXC001001Exchange.exchFuncUnitPrice(exchData);
        if (!exchData.getXxMsgIdList().isEmpty()) {

            return null;
        }
        for (int i = 0; i < exchData.getPriceData().size(); i++) {

            NWXC001001ExchangePriceData prcData = exchData.getPriceData().get(i);
            for (int j = 0; j < prcData.getAmountList().size(); j++) {

                funcAmt = prcData.getAmountList().get(j).getFuncAmt();
            }
        }
        return funcAmt;
    }

    private static DS_IMPT_ORD_DTLTMsg getLine(List<DS_IMPT_ORD_DTLTMsg> lineList, String dsOrdPosnNum, String ordSrcRefLineNum) {

        for (DS_IMPT_ORD_DTLTMsg line : lineList) {

            if (!S21StringUtil.isEquals(line.dsOrdPosnNum.getValue(), dsOrdPosnNum)) {

                continue;
            }

            if (!S21StringUtil.isEquals(line.ordSrcRefLineNum.getValue(), ordSrcRefLineNum)) {

                continue;
            }

            return line;
        }

        return null;
    }

    private static boolean equalsBigDecimal(BigDecimal value1, BigDecimal value2) {

        if (value1 == null) {

            if (value2 == null) {

                return true;
            } else {

                return false;
            }
        } else if (value2 == null) {

            return false;
        } else {

            return value1.compareTo(value2) == 0;
        }
    }

//    private static Map<String, Object> getSvcPrcListMap(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd, String slsDt) {
    private static List<Map<String, Object>> getSvcPrcListMap(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd, String slsDt) {

        // 2018/04/03 QC#24980 mod start
//        Map<String, Object> svcPrcListMap = new HashMap<String, Object>();
        List<Map<String, Object>> svcPrcListMap = new ArrayList<Map<String, Object>>();
        // 2018/04/03 QC#24980 mod end

        if (S21StringUtil.isNotEmpty(dsOrdCatgCd)) {

            Map<String, Object> ssmParm = new HashMap<String, Object>();

            ssmParm.put("glblCmpyCd", glblCmpyCd);
            ssmParm.put("dsOrdCatgCd", dsOrdCatgCd);
            ssmParm.put("dsOrdTpCd", dsOrdTpCd);
            ssmParm.put("ordCatgCtxTpCd", "DEAL_CONFIG_DEF_PRC_LIST");

            // 2018/04/03 QC#24980 mod start
//            Map<String, Object> defaultSvcPrc = NWZC225001Query.getInstance().queryMap("getDefaultSvcPrcList", ssmParm);
            List<Map<String, Object>> defaultSvcPrc = NWZC225001Query.getInstance().queryMapList("getDefaultSvcPrcList", ssmParm);

            if (defaultSvcPrc != null && !defaultSvcPrc.isEmpty()) {
                for (int i = 0; i <defaultSvcPrc.size(); i++) {
                    svcPrcListMap.add(defaultSvcPrc.get(i));
                }
            }
            // 2018/04/03 QC#24980 mod end
        }

        if (S21StringUtil.isNotEmpty(dsOrdTpCd)) {

            Map<String, Object> ssmParm = new HashMap<String, Object>();

            ssmParm.put("glblCmpyCd", glblCmpyCd);
            ssmParm.put("dsOrdTpCd", dsOrdTpCd);
            ssmParm.put("slsDt", slsDt);
            ssmParm.put("actvFlg", ZYPConstant.FLG_ON_Y);

            Map<String, Object> defaultMdlSvcPrc = NWZC225001Query.getInstance().queryMap("getDefaultMdlSvcPrc", ssmParm);

            if (defaultMdlSvcPrc != null && !defaultMdlSvcPrc.isEmpty()) {

                // 2018/04/03 QC#24980 mod start
                if (svcPrcListMap.size() > 0) {
                    svcPrcListMap.get(0).put("DEF_MAINT_PRC_CATG_CD", defaultMdlSvcPrc.get("DEF_MAINT_PRC_CATG_CD"));
                } else {
                    svcPrcListMap.add(defaultMdlSvcPrc);
                }
                // 2018/04/03 QC#24980 mod end
            }
        }

        // 2018/04/03 QC#24980 mod start
        if (svcPrcListMap.size() <= 0) {
            svcPrcListMap.add(new HashMap<String, Object>());
        }
        // 2018/04/03 QC#24980 add start

        return svcPrcListMap;
    }

    // 2018/04/03 QC#24980 mod start
//    private static String getDefaultMdlSvcPrc(Map<String, Object> svcPrcListMap, boolean hasSchdTmpl) {
    private static String getDefaultMdlSvcPrc(List<Map<String, Object>> svcPrcListMap, boolean hasSchdTmpl, String splyAgmtPlnShortNm, String glblCmpyCd) {
    // 2018/04/03 QC#24980 mod end

        String svcPrcList = null;

        // 2018/04/03 QC#24980 mod start
        if (hasSchdTmpl) {
            if (ZYPCommonFunc.hasValue(splyAgmtPlnShortNm)) {
                SPLY_AGMT_PLNTMsg tMsg = new SPLY_AGMT_PLNTMsg();
                tMsg.setSQLID("002");
                tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                tMsg.setConditionValue("splyAgmtPlnShortNm01", splyAgmtPlnShortNm);

                SPLY_AGMT_PLNTMsgArray tMsgArray = (SPLY_AGMT_PLNTMsgArray)S21ApiTBLAccessor.findByCondition(tMsg);

                if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
                    String splyAgmtPlnTpCd = tMsgArray.no(0).splyAgmtPlnTpCd.getValue();

                    for (Map<String, Object> svcPrcMap : svcPrcListMap) {

                        if (splyAgmtPlnTpCd.equals(svcPrcMap.get("FRTH_BIZ_CTX_ATTRB_TXT"))) {
                            svcPrcList = (String) svcPrcMap.get("SCD_BIZ_CTX_ATTRB_TXT");
                        }
                    }
                }
            } else {
                svcPrcList = (String) svcPrcListMap.get(0).get("SCD_BIZ_CTX_ATTRB_TXT");
            }

//            svcPrcList = (String) svcPrcListMap.get("SCD_BIZ_CTX_ATTRB_TXT");
        } else {

//            svcPrcList = (String) svcPrcListMap.get("FIRST_BIZ_CTX_ATTRB_TXT");
            svcPrcList = (String) svcPrcListMap.get(0).get("FIRST_BIZ_CTX_ATTRB_TXT");
        }
        if (S21StringUtil.isEmpty(svcPrcList)) {

//            svcPrcList = (String) svcPrcListMap.get("DEF_MAINT_PRC_CATG_CD");
            svcPrcList = (String) svcPrcListMap.get(0).get("DEF_MAINT_PRC_CATG_CD");
        }
        // 2018/04/03 QC#24980 mod end

        return svcPrcList;
    }

    private DS_IMPT_SVC_DTLTMsg getServiceLine(List<DS_IMPT_SVC_DTLTMsg> serviceLineList, BigDecimal dsImptSvcLineNum) {

        for (DS_IMPT_SVC_DTLTMsg serviceLine : serviceLineList) {

            if (equalsBigDecimal(serviceLine.dsImptSvcLineNum.getValue(), dsImptSvcLineNum)) {

                return serviceLine;
            }
        }

        return null;
    }

    private static List<String> getPrcSvcTierTpCdList(String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);

        return NWZC225001Query.getInstance().queryStringList("getPrcSvcTierTpCdList", ssmParam);
    }

    private static String getUsgMdseCd(String glblCmpyCd, String bllgMtrLbCd, String slsDt) {

        if (S21StringUtil.isEmpty(bllgMtrLbCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mtrLbCd", bllgMtrLbCd);
        ssmParam.put("slsDt", slsDt);

        return NWZC225001Query.getInstance().queryString("getUsgMdseCd", ssmParam);
    }

    private static Map<String, Object> getPrcListBandCdPrcBookMdseCd(//
            String glblCmpyCd //
            , String maintPrcCatgCd //
            , BigDecimal mdlId //
            , BigDecimal prcMtrPkgPk //
            , String prcSvcPlnTpCd //
            , String prcSvcContrTpCd //
            , String bllgMtrLbCd //
            , String slsDt //
            , String splyAgmtPlnShortNm) {

        if (S21StringUtil.isEmpty(maintPrcCatgCd)) {

            return null;
        }

        if (mdlId == null) {

            return null;
        }

        if (prcMtrPkgPk == null) {

            return null;
        }

        if (S21StringUtil.isEmpty(prcSvcPlnTpCd)) {

            return null;
        }

        if (S21StringUtil.isEmpty(prcSvcContrTpCd)) {

            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prcCatgCd", maintPrcCatgCd);
        ssmParam.put("mdlId", mdlId);
        ssmParam.put("prcMtrPkgPk", prcMtrPkgPk);
        ssmParam.put("prcSvcPlnTpCd", prcSvcPlnTpCd);
        ssmParam.put("prcSvcContrTpCd", prcSvcContrTpCd);
        ssmParam.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmParam.put("prcRateTp", PRC_RATE_TP.CPC);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("splyAgmtPlnShortNm", splyAgmtPlnShortNm);

        return NWZC225001Query.getInstance().queryMap("getPrcListBandCdPrcBookMdseCd", ssmParam);
    }

    private static DS_IMPT_SVC_CONFIG_REFTMsg getConfigRef(List<DS_IMPT_SVC_CONFIG_REFTMsg> configRefList, BigDecimal dsImptSvcDtlPk, BigDecimal dsImptOrdDtlPk) {

        for (DS_IMPT_SVC_CONFIG_REFTMsg configRef : configRefList) {

            if (!equalsBigDecimal(configRef.dsImptSvcDtlPk.getValue(), dsImptSvcDtlPk)) {

                continue;
            }

            if (!equalsBigDecimal(configRef.dsImptOrdDtlPk.getValue(), dsImptOrdDtlPk)) {

                continue;
            }

            return configRef;
        }
        return null;
    }

    private static NWZC225001_xxMsgPrmListPMsg createMsgPrm(String ordSrcRefNum, String dsOrdPosnNum, String ordSrcRefLineNum, BigDecimal dsImptSvcLineNum, String bllgMtrLbCd) {

        NWZC225001_xxMsgPrmListPMsg msgPrm = new NWZC225001_xxMsgPrmListPMsg();

        ZYPEZDItemValueSetter.setValue(msgPrm.ordSrcRefNum, ordSrcRefNum);
        ZYPEZDItemValueSetter.setValue(msgPrm.dsOrdPosnNum, dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(msgPrm.ordSrcRefLineNum, ordSrcRefLineNum);
        ZYPEZDItemValueSetter.setValue(msgPrm.dsImptSvcLineNum, dsImptSvcLineNum);
        ZYPEZDItemValueSetter.setValue(msgPrm.bllgMtrLbCd, bllgMtrLbCd);

        return msgPrm;
    }

    private static void setMsgIdWithParam(S21ApiMessageMap msgMap, NWZC225001_xxMsgPrmListPMsg msgPrm, String msgId, String... param) {

        NWZC225001PMsg inPrmPMsg = (NWZC225001PMsg) msgMap.getPmsg();
        if (msgPrm == null) {

            msgPrm = new NWZC225001_xxMsgPrmListPMsg();
        }
        if (!ZYPCommonFunc.hasValue(msgPrm.dsImptOrdErrTxt)) {

            ZYPEZDItemValueSetter.setValue(msgPrm.dsImptOrdErrTxt, S21MessageFunc.clspGetMessage(msgId, param));
        }

        int validCount = inPrmPMsg.xxMsgIdList.getValidCount();
        if (validCount >= inPrmPMsg.xxMsgIdList.length()) {

            return;
        }

        NWZC225001_xxMsgPrmListPMsg xxMsgPrm = inPrmPMsg.xxMsgPrmList.no(validCount);
        EZDMsg.copy(msgPrm, null, xxMsgPrm, null);
        msgMap.addXxMsgIdWithPrm(msgId, param);
        inPrmPMsg.xxMsgPrmList.setValidCount(validCount + 1);
    }

    // QC#16142
    private Map<String, Object> getBaseTotalAmt(DS_IMPT_SVC_DTLTMsg tMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", tMsg.glblCmpyCd);
        param.put("dsImptOrdPk", tMsg.dsImptOrdPk);
        param.put("dsImptSvcLineNum", tMsg.dsImptSvcLineNum);
        param.put("contractPeriod", BLLG_CYCLE.CONTRACT_PERIOD);

        List<Map<String, Object>> rslt //
        = NWZC225001Query.getInstance().queryMapList("getBaseTotalAmt", param);
        if (rslt.size() == 0) {
            return null;
        }
        return rslt.get(0);
    }

    // QC#16142
    private List<DS_IMPT_SVC_ADDL_BASETMsg> createLbrDsImptSvcAddlBase(//
            S21ApiMessageMap msgMap //
            , List<DS_IMPT_ORDTMsg> headerList //
            , List<DS_IMPT_SVC_DTLTMsg> serviceLineList //
            , List<DS_IMPT_ORD_DTLTMsg> lineList //
            , NWZC225001_svcDtlPMsg svcDtlPMsg //
            , List<BigDecimal> bdlSvcLineNumList) {

        NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();

        List<DS_IMPT_SVC_ADDL_BASETMsg> targetList = new ArrayList<DS_IMPT_SVC_ADDL_BASETMsg>();

        DS_IMPT_ORDTMsg header = getHeader(headerList);
        if (header == null) {

            return targetList;
        }

        Boolean isBundled = false;
        BigDecimal targetSvcLineNum = svcDtlPMsg.dsImptSvcLineNum.getValue();
        if (bdlSvcLineNumList.contains(targetSvcLineNum)) {
            targetSvcLineNum = svcDtlPMsg.refImptSvcLineNum.getValue();
            isBundled = true;
        }
        for (int i = 0; i < msg.svcPrc.getValidCount(); i++) {

            NWZC225001_svcPrcPMsg source = msg.svcPrc.no(i);
            if (!equalsBigDecimal(svcDtlPMsg.dsImptSvcLineNum.getValue(), source.dsImptSvcLineNum.getValue())) {
                continue;
            }

            DS_IMPT_SVC_DTLTMsg serviceLine = getServiceLine(serviceLineList, targetSvcLineNum);
            if (serviceLine == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, source.dsImptSvcLineNum.getValue(), null), NWAM0702E, SERVICE_ADDITIONAL_BASE_CHARGE, SERVICE_LINE);
                continue;
            }

            DS_IMPT_ORD_DTLTMsg line = getLine(lineList, source.dsOrdPosnNum.getValue(), source.ordSrcRefLineNum.getValue());
            if (line == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), source.ordSrcRefLineNum.getValue(), source.dsImptSvcLineNum.getValue(), null), NWAM0702E,
                        SERVICE_ADDITIONAL_BASE_CHARGE, ORDER_LINE);
                continue;
            }

            DS_IMPT_SVC_ADDL_BASETMsg target = new DS_IMPT_SVC_ADDL_BASETMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcAddlBasePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_ADDL_BASE_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcDtlPk, serviceLine.dsImptSvcDtlPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdDtlPk, line.dsImptOrdDtlPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, line.dsImptOrdPk);

            // price
            DS_ORD_TP_PROC_DFNTMsg dsOrdTpPrcDfn = getDsOrdTpPrcDfn(msg.glblCmpyCd.getValue(), header.dsOrdTpCd.getValue(), msg.slsDt.getValue());
            ZYPEZDItemValueSetter.setValue(target.addlBasePrcDealAmt, source.basePrcDealAmt);
            if (dsOrdTpPrcDfn != null) {

                // price category code
                ZYPEZDItemValueSetter.setValue(target.addlBasePrcCatgCd, dsOrdTpPrcDfn.defPrcCatgCd);

                // function amount
                if (ZYPCommonFunc.hasValue(target.addlBasePrcCatgCd)) {

                    PRC_CATGTMsg prcCatg = new PRC_CATGTMsg();
                    ZYPEZDItemValueSetter.setValue(prcCatg.glblCmpyCd, msg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(prcCatg.prcCatgCd, target.addlBasePrcCatgCd);
                    prcCatg = (PRC_CATGTMsg) EZDTBLAccessor.findByKey(prcCatg);
                    if (prcCatg != null) {

                        ZYPEZDItemValueSetter.setValue(target.addlBasePrcFuncAmt, calcFuncAmt(msg.glblCmpyCd.getValue(), prcCatg.ccyCd.getValue(), source.basePrcDealAmt.getValue(), msg.slsDt.getValue()));
                    }
                }
            }
            target.dealPrcListPrcAmt.clear();
            target.funcPrcListPrcAmt.clear();
            ZYPEZDItemValueSetter.setValue(target.svcPrcCatgCd, SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE);
            target.prcListEquipConfigNum.clear();

            targetList.add(target);

            if (isBundled) {
                continue;
            }
            DS_IMPT_SVC_ADDL_BASETMsg targetS = new DS_IMPT_SVC_ADDL_BASETMsg();
            EZDTMsg.copy(target, null, targetS, null);
            ZYPEZDItemValueSetter.setValue(targetS.dsImptSvcAddlBasePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_ADDL_BASE_SQ));
            DS_IMPT_SVC_DTLTMsg serviceLineS = getServiceLine(serviceLineList, svcDtlPMsg.refImptSvcLineNum.getValue());
            if (serviceLineS == null) {
                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, source.dsImptSvcLineNum.getValue(), null), NWAM0702E, SERVICE_ADDITIONAL_BASE_CHARGE, SERVICE_LINE);
                continue;
            }
            ZYPEZDItemValueSetter.setValue(targetS.dsImptSvcDtlPk, serviceLineS.dsImptSvcDtlPk);
            targetS.addlBasePrcDealAmt.setValue(BigDecimal.ZERO);
            targetS.addlBasePrcFuncAmt.setValue(BigDecimal.ZERO);
            targetList.add(targetS);
        }

        for (int i = 0; i < msg.svcAddlBasePrc.getValidCount(); i++) {

            NWZC225001_svcAddlBasePrcPMsg source = msg.svcAddlBasePrc.no(i);
            BigDecimal addlBasePrcDealAmt = source.addlBasePrcDealAmt.getValue();

            if (!equalsBigDecimal(svcDtlPMsg.dsImptSvcLineNum.getValue(), source.dsImptSvcLineNum.getValue())) {
                continue;
            }
            // if
            // (equalsBigDecimal(svcDtlPMsg.dsImptSvcLineNum.getValue(),
            // source.dsImptSvcLineNum.getValue())) {
            // addlBasePrcDealAmt =
            // source.addlBasePrcDealAmt.getValue();
            // } else if
            // (equalsBigDecimal(svcDtlPMsg.refImptSvcLineNum.getValue(),
            // source.dsImptSvcLineNum.getValue())) {
            // addlBasePrcDealAmt = BigDecimal.ZERO;
            // } else {
            // continue;
            // }

            DS_IMPT_SVC_DTLTMsg serviceLine = getServiceLine(serviceLineList, targetSvcLineNum);
            if (serviceLine == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, source.dsImptSvcLineNum.getValue(), null), NWAM0702E, SERVICE_ADDITIONAL_BASE_CHARGE, SERVICE_LINE);
                continue;
            }

            DS_IMPT_ORD_DTLTMsg line = getLine(lineList, source.dsOrdPosnNum.getValue(), source.ordSrcRefLineNum.getValue());
            if (line == null) {

                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), source.ordSrcRefLineNum.getValue(), source.dsImptSvcLineNum.getValue(), null), NWAM0702E,
                        SERVICE_ADDITIONAL_BASE_CHARGE, ORDER_LINE);
                continue;
            }

            DS_IMPT_SVC_ADDL_BASETMsg target = new DS_IMPT_SVC_ADDL_BASETMsg();

            ZYPEZDItemValueSetter.setValue(target.glblCmpyCd, msg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcAddlBasePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_ADDL_BASE_SQ));
            ZYPEZDItemValueSetter.setValue(target.dsImptSvcDtlPk, serviceLine.dsImptSvcDtlPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdDtlPk, line.dsImptOrdDtlPk);
            ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk, line.dsImptOrdPk);

            // price
            DS_ORD_TP_PROC_DFNTMsg dsOrdTpPrcDfn = getDsOrdTpPrcDfn(msg.glblCmpyCd.getValue(), header.dsOrdTpCd.getValue(), msg.slsDt.getValue());
            ZYPEZDItemValueSetter.setValue(target.addlBasePrcDealAmt, addlBasePrcDealAmt);
            if (dsOrdTpPrcDfn != null) {

                // price category code
                ZYPEZDItemValueSetter.setValue(target.addlBasePrcCatgCd, dsOrdTpPrcDfn.defPrcCatgCd);

                // function amount
                if (ZYPCommonFunc.hasValue(target.addlBasePrcCatgCd)) {

                    PRC_CATGTMsg prcCatg = new PRC_CATGTMsg();
                    ZYPEZDItemValueSetter.setValue(prcCatg.glblCmpyCd, msg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(prcCatg.prcCatgCd, target.addlBasePrcCatgCd);
                    prcCatg = (PRC_CATGTMsg) EZDTBLAccessor.findByKey(prcCatg);
                    if (prcCatg != null) {

                        ZYPEZDItemValueSetter.setValue(//
                                target.addlBasePrcFuncAmt //
                                , calcFuncAmt(msg.glblCmpyCd.getValue(), prcCatg.ccyCd.getValue(), addlBasePrcDealAmt, msg.slsDt.getValue()));
                    }
                }
            }
            target.dealPrcListPrcAmt.clear();
            target.funcPrcListPrcAmt.clear();
            ZYPEZDItemValueSetter.setValue(target.svcPrcCatgCd, SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE);
            target.prcListEquipConfigNum.clear();

            targetList.add(target);

            if (isBundled) {
                continue;
            }
            DS_IMPT_SVC_ADDL_BASETMsg targetS = new DS_IMPT_SVC_ADDL_BASETMsg();
            EZDTMsg.copy(target, null, targetS, null);
            ZYPEZDItemValueSetter.setValue(targetS.dsImptSvcAddlBasePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_ADDL_BASE_SQ));
            DS_IMPT_SVC_DTLTMsg serviceLineS = getServiceLine(serviceLineList, svcDtlPMsg.refImptSvcLineNum.getValue());
            if (serviceLineS == null) {
                setMsgIdWithParam(msgMap, createMsgPrm(source.ordSrcRefNum.getValue(), source.dsOrdPosnNum.getValue(), null, source.dsImptSvcLineNum.getValue(), null), NWAM0702E, SERVICE_ADDITIONAL_BASE_CHARGE, SERVICE_LINE);
                continue;
            }
            ZYPEZDItemValueSetter.setValue(targetS.dsImptSvcDtlPk, serviceLineS.dsImptSvcDtlPk);
            targetS.addlBasePrcDealAmt.setValue(BigDecimal.ZERO);
            targetS.addlBasePrcFuncAmt.setValue(BigDecimal.ZERO);
            targetList.add(targetS);
        }

        return targetList;
    }

    // S21_NA#18806(L3#372) ADD START
    private static String getOrdLogTp(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {

        if (S21StringUtil.isEmpty(dsOrdCatgCd) && S21StringUtil.isEmpty(dsOrdTpCd)) {
            return null;
        }

        Map<String, Object> ssmParm = new HashMap<String, Object>();

        ssmParm.put("glblCmpyCd", glblCmpyCd);
        ssmParm.put("ordCatgCtxTpCd", VAL_SET_CODE_ORDER_LOG_AVAILABLE);
        ssmParm.put("dsOrdCatgCd", dsOrdCatgCd);
        ssmParm.put("dsOrdTpCd", dsOrdTpCd);

        Map<String, Object> defaultLogTp = NWZC225001Query.getInstance().queryMap("getDefaultLogTp", ssmParm);
        if(defaultLogTp == null) {
            return null;
        }

        return (String) defaultLogTp.get("FIRST_BIZ_CTX_ATTRB_TXT");
    }
    // S21_NA#18806(L3#372) END START

    // private DS_IMPT_SVC_DTLTMsg createLbrDsImptSvcDtlForService(//
    // S21ApiMessageMap msgMap, List<DS_IMPT_ORDTMsg> headerList,
    // NWZC225001_svcDtlPMsg source) {
    //
    // NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();
    //
    // DS_IMPT_SVC_DTLTMsg target = new DS_IMPT_SVC_DTLTMsg();
    //
    // DS_IMPT_ORDTMsg header = getHeader(headerList);
    // if (header == null) {
    // return null;
    // }
    //
    // // NWZC225001_svcDtlPMsg source = msg.svcDtl.no(i);
    //
    // ZYPEZDItemValueSetter.setValue(target.glblCmpyCd,
    // msg.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(target.dsImptSvcDtlPk,
    // ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_DTL_SQ));
    // ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk,
    // header.dsImptOrdPk);
    // ZYPEZDItemValueSetter.setValue(target.dsImptSvcLineNum,
    // source.dsImptSvcLineNum);
    // ZYPEZDItemValueSetter.setValue(target.dsImptSvcMdseCd,
    // source.dsImptSvcMdseCd);
    // ZYPEZDItemValueSetter.setValue(target.prcSvcContrTpCd,
    // source.prcSvcContrTpCd);
    // ZYPEZDItemValueSetter.setValue(target.prcSvcPlnTpCd,
    // source.prcSvcPlnTpCd);
    // ZYPEZDItemValueSetter.setValue(target.dsContrCatgCd,
    // source.dsContrCatgCd);
    // ZYPEZDItemValueSetter.setValue(target.baseBllgCycleCd,
    // source.baseBllgCycleCd);
    // ZYPEZDItemValueSetter.setValue(target.usgBllgCycleCd,
    // source.usgBllgCycleCd);
    // ZYPEZDItemValueSetter.setValue(target.fromPerMthNum,
    // source.fromPerNum);
    // ZYPEZDItemValueSetter.setValue(target.toPerMthNum,
    // source.toPerNum);
    // target.termMthAot.clear();
    // ZYPEZDItemValueSetter.setValue(target.billWithEquipFlg,
    // getFlagValue(source.billWithEquipFlg.getValue()));
    // ZYPEZDItemValueSetter.setValue(target.billByTpCd,
    // source.billByTpCd);
    //
    // // sold to
    // ZYPEZDItemValueSetter.setValue(target.sellToCustCd,
    // source.sellToCustCd);
    // BILL_TO_CUSTTMsg soldTo =
    // getBillToCustByLocNum(msg.glblCmpyCd.getValue(),
    // source.soldToLocNum.getValue(), msg.slsDt.getValue());
    // if (soldTo != null) {
    //
    // ZYPEZDItemValueSetter.setValue(target.soldToCustLocCd,
    // soldTo.billToCustCd);
    // }
    //
    // target.svcAgmtVrsnNum.clear();
    // ZYPEZDItemValueSetter.setValue(target.manContrOvrdFlg,
    // getFlagValue(source.manContrOvrdFlg.getValue()));
    // ZYPEZDItemValueSetter.setValue(target.manContrOvrdRsnCd,
    // source.manContrOvrdRsnCd);
    // ZYPEZDItemValueSetter.setValue(target.manContrOvrdCmntTxt,
    // source.manContrOvrdCmntTxt);
    // ZYPEZDItemValueSetter.setValue(target.dsContrPk,
    // source.dsContrPk);
    // ZYPEZDItemValueSetter.setValue(target.useEquipBillToFlg,
    // ZYPConstant.FLG_OFF_N);
    // target.svcPrcCatgCd.clear();
    // target.totBasePrcDealAmt.clear();
    // target.totBasePrcFuncAmt.clear();
    // ZYPEZDItemValueSetter.setValue(target.fixTermInMthAot,
    // source.fixTermInMthAot);
    // ZYPEZDItemValueSetter.setValue(target.maxUplftPct,
    // source.maxUplftPct);
    // ZYPEZDItemValueSetter.setValue(target.cpoSvcLineActCd,
    // source.cpoSvcLineActCd);
    //
    // return target;
    // }

    // private Map<SVC_LIST, List<?>> createLbrDsImptSvcConfigRef(//
    // S21ApiMessageMap msgMap, List<DS_IMPT_SVC_DTLTMsg>
    // serviceLineList, List<DS_IMPT_ORD_DTLTMsg> lineList,
    // Map<String, Object> svcPrcListMap) {
    //
    // NWZC225001PMsg msg = (NWZC225001PMsg) msgMap.getPmsg();
    //
    // Map<SVC_LIST, List<?>> serviceMap = new HashMap<SVC_LIST,
    // List<?>>();
    //
    // List<DS_IMPT_SVC_CONFIG_REFTMsg> targetList = new
    // ArrayList<DS_IMPT_SVC_CONFIG_REFTMsg>();
    // List<DS_IMPT_SVC_PRCTMsg> servicePriceList = new
    // ArrayList<DS_IMPT_SVC_PRCTMsg>();
    // // List<DS_IMPT_SVC_USG_PRCTMsg> usageList = new
    // ArrayList<DS_IMPT_SVC_USG_PRCTMsg>();
    //
    // serviceMap.put(SVC_LIST.CONFIG_REF, targetList);
    // serviceMap.put(SVC_LIST.PRC, servicePriceList);
    // // serviceMap.put(SVC_LIST.USG_PRC, usageList);
    // //
    // // // tier type code
    // // List<String> prcSvcTierTpCdList =
    // getPrcSvcTierTpCdList(msg.glblCmpyCd.getValue());
    //
    // for (int i = 0; i < msg.svcConfigRef.getValidCount(); i++) {
    //
    // NWZC225001_svcConfigRefPMsg source = msg.svcConfigRef.no(i);
    //
    // DS_IMPT_SVC_DTLTMsg serviceLine =
    // getServiceLine(serviceLineList,
    // source.dsImptSvcLineNum.getValue());
    // if (serviceLine == null) {
    // setMsgIdWithParam(msgMap,
    // createMsgPrm(source.ordSrcRefNum.getValue(),
    // source.dsOrdPosnNum.getValue(), null,
    // source.dsImptSvcLineNum.getValue(), null), NWAM0702E,
    // SERVICE_CONFIG_REFERENCE, SERVICE_LINE);
    // continue;
    // }
    //
    // DS_IMPT_ORD_DTLTMsg line = getLine(lineList,
    // source.dsOrdPosnNum.getValue(),
    // source.ordSrcRefLineNum.getValue());
    // if (line == null) {
    // setMsgIdWithParam(//
    // msgMap, createMsgPrm(source.ordSrcRefNum.getValue(),
    // source.dsOrdPosnNum.getValue(),
    // source.ordSrcRefLineNum.getValue(),
    // source.dsImptSvcLineNum.getValue(), null), NWAM0702E,
    // SERVICE_CONFIG_REFERENCE, ORDER_LINE);
    // continue;
    // }
    //
    // DS_IMPT_SVC_CONFIG_REFTMsg target = new
    // DS_IMPT_SVC_CONFIG_REFTMsg();
    //
    // ZYPEZDItemValueSetter.setValue(target.glblCmpyCd,
    // msg.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(target.dsImptSvcConfigRefPk,
    // ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_IMPT_SVC_CONFIG_REF_SQ));
    // ZYPEZDItemValueSetter.setValue(target.dsImptSvcDtlPk,
    // serviceLine.dsImptSvcDtlPk);
    // ZYPEZDItemValueSetter.setValue(target.dsImptOrdPk,
    // serviceLine.dsImptOrdPk);
    // target.svcConfigMstrPk.clear();
    // ZYPEZDItemValueSetter.setValue(target.custIssPoNum,
    // source.custIssPoNum);
    // ZYPEZDItemValueSetter.setValue(target.mtrReadMethCd,
    // source.mtrReadMethCd);
    // ZYPEZDItemValueSetter.setValue(target.custIssPoDt,
    // source.custIssPoDt);
    // target.funcSvcRevTrnsfAmt.clear();
    // target.dealSvcRevTrnsfAmt.clear();
    // target.funcSvcCostTrnsfAmt.clear();
    // target.dealSvcCostTrnsfAmt.clear();
    // target.svcPrcCatgCd.clear();
    //
    // ZYPEZDItemValueSetter.setValue(target.dsImptOrdDtlPk,
    // line.dsImptOrdDtlPk);
    //
    // targetList.add(target);
    //
    // // service price
    // DS_IMPT_SVC_PRCTMsg servicePrice = createDsImptSvcPrc(msgMap,
    // svcPrcListMap, serviceLine.dsImptSvcDtlPk.getValue(),
    // target.dsImptSvcConfigRefPk.getValue(),
    // source.dsImptSvcLineNum.getValue(),
    // source.dsOrdPosnNum.getValue(),
    // source.ordSrcRefLineNum.getValue());
    // if (servicePrice != null) {
    //
    // servicePriceList.add(servicePrice);
    // ZYPEZDItemValueSetter.setValue(target.dsImptSvcPrcPk,
    // servicePrice.dsImptSvcPrcPk);
    //
    // // // service usage price
    // // List<DS_IMPT_SVC_USG_PRCTMsg> serviceUsageListByPrc =
    // createDsImptSvcUsgPrc(msgMap, serviceLine, servicePrice,
    // source.dsImptSvcLineNum.getValue(),
    // source.dsOrdPosnNum.getValue(),
    // source.ordSrcRefLineNum.getValue(),
    // // prcSvcTierTpCdList);
    // // usageList.addAll(serviceUsageListByPrc);
    // }
    // }
    //
    // return serviceMap;
    // }

    // 2017/08/15 S21_NA#20584 Add Start
    private boolean hasIstlInfo(NWZC225001_istlInfoPMsgArray istlInfoArray, String dsOrdPosnNum) {

        if (S21StringUtil.isEmpty(dsOrdPosnNum)) {

            return false;
        }

        for (int i = 0; i < istlInfoArray.getValidCount(); i++) {

            NWZC225001_istlInfoPMsg istlInfo = istlInfoArray.no(i);
            if (S21StringUtil.isEquals(istlInfo.dsOrdPosnNum.getValue(), dsOrdPosnNum)) {

                return true;
            }
        }

        return false;
    }
    // 2017/08/15 S21_NA#20584 Add End

    // 2017/08/25 S21_NA#20627-1 Add Start
    private boolean isOrderRetailEquipment(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd) {

        return NWXC220001.isExistOrdCatg(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd, dsOrdTpCd, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS, false);
    }
    // 2017/08/25 S21_NA#20627-1 Add End

    // 2017/09/28 S21_NA#21384 Add Start
    private boolean isLoanUpgradeOrAddAsry(DS_IMPT_ORD_CONFIGTMsg config) {
        if (CONFIG_TP.ADD_TO_CONFIG.equals(config.configTpCd.getValue())) {
            return true;
        }

        if (CONFIG_TP.NEW.equals(config.configTpCd.getValue()) && ZYPCommonFunc.hasValue(config.svcConfigMstrPk)) {
            return true;
        }

        return false;
    }
    // 2017/09/28 S21_NA#21384 Add End

    // 2017/11/16 QC#20768 Add Start
    private void setFrtCond(List<DS_IMPT_ORDTMsg> headerList, List<DS_IMPT_ORD_SITE_SRVYTMsg> siteSurveyList) {

        for (DS_IMPT_ORDTMsg headerTMsg : headerList) {
            setDefaultFrtCond(headerTMsg, siteSurveyList);
        }
    }

    private void setDefaultFrtCond(DS_IMPT_ORDTMsg headerTMsg, List<DS_IMPT_ORD_SITE_SRVYTMsg> siteSurveyList) {

        String frtCondCd = headerTMsg.frtCondCd.getValue();
        // 2018/01/04 QC#23323 Add Start
        String shpgSvcLvlCd = headerTMsg.shpgSvcLvlCd.getValue();
        // 2018/01/04 QC#23323 Add End

        if (!ZYPCommonFunc.hasValue(frtCondCd) //
                || !ZYPCommonFunc.hasValue(shpgSvcLvlCd)) { // 2018/01/04 QC#23323 Add
            DS_IMPT_ORD_SITE_SRVYTMsg siteSrvyTMsg = getFirstSiteSrvyTMsg(headerTMsg, siteSurveyList);
            if (siteSrvyTMsg != null) {
                // 2018/01/04 QC#23323 Mod Start
//                frtCondCd = getFrtCondCdFromFirstSiteSrvy(headerTMsg, siteSrvyTMsg);
                DELY_TRNSP_OPTTMsg delyTrnspOptTMsg = getFrtCondCdFromFirstSiteSrvy(headerTMsg, siteSrvyTMsg);
                if (delyTrnspOptTMsg != null){ 
                    if (!ZYPCommonFunc.hasValue(frtCondCd)) {
                        frtCondCd = delyTrnspOptTMsg.defFrtCondCd.getValue();
                    }
                    if (!ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {
                        shpgSvcLvlCd = delyTrnspOptTMsg.defShpgSvcLvlCd.getValue();
                    }
                }
                // 2018/01/04 QC#23323 Mod End
            }
    
            if (!ZYPCommonFunc.hasValue(frtCondCd)) {
                DS_ORD_TP_PROC_DFNTMsg dsOrdTpPrcDfn = getDsOrdTpPrcDfn(headerTMsg.glblCmpyCd.getValue(), headerTMsg.dsOrdTpCd.getValue(), slsDt);
                if (dsOrdTpPrcDfn != null) {
                    frtCondCd = dsOrdTpPrcDfn.frtCondCd.getValue();
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(headerTMsg.frtCondCd, frtCondCd);
        // 2018/01/04 QC#23323 Add Start
        ZYPEZDItemValueSetter.setValue(headerTMsg.shpgSvcLvlCd, shpgSvcLvlCd);
        // 2018/01/04 QC#23323 Add End
        FRT_CONDTMsg frtCond = getFrtCond(headerTMsg.glblCmpyCd.getValue(), frtCondCd);
        if (frtCond != null) {
            ZYPEZDItemValueSetter.setValue(headerTMsg.frtChrgToCd, frtCond.frtChrgToCd);
            ZYPEZDItemValueSetter.setValue(headerTMsg.frtChrgMethCd, frtCond.frtChrgMethCd);
        }
    }

    private DS_IMPT_ORD_SITE_SRVYTMsg getFirstSiteSrvyTMsg(DS_IMPT_ORDTMsg headerTMsg, List<DS_IMPT_ORD_SITE_SRVYTMsg> siteSurveyList) {

        BigDecimal dsImptOrdPk = headerTMsg.dsImptOrdPk.getValue();
        for (DS_IMPT_ORD_SITE_SRVYTMsg siteSurveyTMsg : siteSurveyList) {
            BigDecimal dsImptOrdPkFromSrvy = siteSurveyTMsg.dsImptOrdPk.getValue();
            if (dsImptOrdPkFromSrvy == null) {
                continue;
            }
            if (dsImptOrdPk.compareTo(dsImptOrdPkFromSrvy) == 0) {
                return siteSurveyTMsg;
            }
        }
        return null;
    }

//    private String getFrtCondCdFromFirstSiteSrvy(DS_IMPT_ORDTMsg  headerTMsg, DS_IMPT_ORD_SITE_SRVYTMsg siteSrvyTMsg){ 2018/01/04 QC#23323 Mod Interface
    private DELY_TRNSP_OPTTMsg getFrtCondCdFromFirstSiteSrvy(DS_IMPT_ORDTMsg  headerTMsg, DS_IMPT_ORD_SITE_SRVYTMsg siteSrvyTMsg){


        if (siteSrvyTMsg == null) {
            return null;
        }

        if (!ZYPCommonFunc.hasValue(siteSrvyTMsg.delyTrnspOptCd)) {
            return null;
        }

        DELY_TRNSP_OPTTMsg delyTrnspOptTMsg = new DELY_TRNSP_OPTTMsg();
        ZYPEZDItemValueSetter.setValue(delyTrnspOptTMsg.glblCmpyCd, headerTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(delyTrnspOptTMsg.delyTrnspOptCd, siteSrvyTMsg.delyTrnspOptCd);

        // 2018/01/04 QC#23323 Mod Start
//        delyTrnspOptTMsg = (DELY_TRNSP_OPTTMsg) S21CacheTBLAccessor.findByKey(delyTrnspOptTMsg);
//        if (delyTrnspOptTMsg == null) {
//            return null;
//        }
//        return delyTrnspOptTMsg.defFrtCondCd.getValue();

        return (DELY_TRNSP_OPTTMsg) S21CacheTBLAccessor.findByKey(delyTrnspOptTMsg);
        // 2018/01/04 QC#23323 Mod End
    }
    // 2017/11/16 QC#20768 Add End

    // 2017/12/05 QC#22527 add start
    private void checkIsLoanFromHeader(DS_IMPT_ORDTMsg headerTMsg) {
        Map<String, Object> loanCxtCntParam = new HashMap<String, Object>();
        loanCxtCntParam.put("glblCmpyCd", headerTMsg.glblCmpyCd.getValue());
        loanCxtCntParam.put("dsOrdTpCd", headerTMsg.dsOrdTpCd.getValue());
        loanCxtCntParam.put("dsOrdRsnCd", headerTMsg.dsOrdRsnCd.getValue());
        loanCxtCntParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.LOAN_ORDER_VALUE_SET);
        // 2018/02/05 QC#23444 add Start
        loanCxtCntParam.put("dsOrdCatgCd", headerTMsg.dsOrdCatgCd.getValue());
        // 2018/02/05 QC#23444 add End

        if ((BigDecimal.ZERO.compareTo(NWZC225001Query.getInstance().queryBigDecimal("countOrdCatgBizCtx", loanCxtCntParam)) < 0)) {
            isLoan = true;
        }
    }
    
    private void getOrigOrd(DS_IMPT_ORD_CONFIGTMsg configTMsg) {
        if (!ZYPCommonFunc.hasValue(configTMsg.svcConfigMstrPk)) {
            configOrigOrdMap.put(configTMsg.dsImptOrdConfigPk.getValue(), "");
            return;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", configTMsg.glblCmpyCd.getValue());
        ssmParam.put("configCatgCd", CONFIG_CATG.OUTBOUND);
        ssmParam.put("svcConfigMstrPk", configTMsg.svcConfigMstrPk.getValue());
 
        List<String> ordHdrStsCdList = new ArrayList<String>();
        List<String> ordLineStsCdList = new ArrayList<String>();
        ordHdrStsCdList.add(ORD_HDR_STS.CLOSED);
        ordHdrStsCdList.add(ORD_HDR_STS.CANCELLED);
        ssmParam.put("ordHdrStsCdList", ordHdrStsCdList);

        ordLineStsCdList.add(ORD_LINE_STS.CANCELLED);
        ssmParam.put("ordLineStsCdList", ordLineStsCdList);

        String origCpoNum = NWZC225001Query.getInstance().queryString("getOrigOrd", ssmParam);

        if (ZYPCommonFunc.hasValue(origCpoNum)) {
            configOrigOrdMap.put(configTMsg.dsImptOrdConfigPk.getValue(), origCpoNum);
        } else {
            configOrigOrdMap.put(configTMsg.dsImptOrdConfigPk.getValue(), "");
        }
    }
    
    private List<String> getOrigOrdMdseList(DS_IMPT_ORD_CONFIGTMsg configTMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", configTMsg.glblCmpyCd.getValue());
        ssmParam.put("origCpoOrdNum", configOrigOrdMap.get(configTMsg.dsImptOrdConfigPk.getValue()));

        List<String> ordLineStsNmList = new ArrayList<String>();
        ordLineStsNmList.add(ORD_LINE_STS_NM_ON_LOAN);
        ordLineStsNmList.add(ORD_LINE_STS_NM_CLOSED);
        ordLineStsNmList.add(ORD_LINE_STS_NM_PENDING_FULFILLMENT);
        ssmParam.put("ordLineStsNmList", ordLineStsNmList);

        ssmParam.put("svcConfigMstrPk", configTMsg.svcConfigMstrPk.getValue());
        ssmParam.put("isSearchCondOnLoanFlg", ZYPConstant.FLG_OFF_N);

        List<String> ordHdrStsCdList = new ArrayList<String>();
        ordHdrStsCdList.add(ORD_HDR_STS.CLOSED);
        ordHdrStsCdList.add(ORD_HDR_STS.CANCELLED);
        ssmParam.put("ordHdrStsCdList", ordHdrStsCdList);

        List<String> ordLineStsCdList = new ArrayList<String>();
        ordLineStsCdList.add(ORD_LINE_STS.CANCELLED);
        ssmParam.put("ordLineStsCdList", ordLineStsCdList);

        List<String> rtrnLineStsCdList = new ArrayList<String>();
        rtrnLineStsCdList.add(ORD_LINE_STS.CLOSED);
        rtrnLineStsCdList.add(ORD_LINE_STS.CANCELLED);
        ssmParam.put("rtrnLineStsCdList", rtrnLineStsCdList);

        List<String> result = NWZC225001Query.getInstance().queryStringList("getConversableItems", ssmParam);

        List<String> loanMdseList = new ArrayList<String>();
        for (String item : result) {
            if (item.length() > MDSE_CD_SHORT_LENGTH) {
                item = item.substring(0, MDSE_CD_SHORT_LENGTH);
            }
            loanMdseList.add(item);
        }
        return loanMdseList;

    }

    private ALL_MDSE_VTMsg getAllMdseV(String glblCmpyCd, String mdseCd) {
        ALL_MDSE_VTMsg tmsg = new ALL_MDSE_VTMsg();
        tmsg.setSQLID("003");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tmsg.setConditionValue("mdseCd01", mdseCd);

        ALL_MDSE_VTMsgArray tmsgArray = (ALL_MDSE_VTMsgArray) S21ApiTBLAccessor.findByCondition(tmsg);
        if (tmsgArray.length() == 0) {
            return null;
        }
        return (ALL_MDSE_VTMsg) tmsgArray.get(0);
    }

    /**
     * select merchandise data from merchandise master using NWXMdseTMsgThreadLocalCache#get()
     * @param glblCmpyCd global company code
     * @param mdseCd merchandise code
     * @return merchandise master data
     */
    public static MDSETMsg getOrdTakeMdse(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            MDSETMsg queryMdseTMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(queryMdseTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(queryMdseTMsg.mdseCd, mdseCd);

            mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(queryMdseTMsg);
            if (mdseTMsg == null) {

                ORD_TAKE_MDSETMsg ordTakeMdseMsg = new ORD_TAKE_MDSETMsg();
                ordTakeMdseMsg.setSQLID("002");
                ordTakeMdseMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                ordTakeMdseMsg.setConditionValue("ordTakeMdseCd01", mdseCd);

                ORD_TAKE_MDSETMsgArray ordTakeMdseMsgArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(ordTakeMdseMsg);
                if (ordTakeMdseMsgArray != null && ordTakeMdseMsgArray.getValidCount() > 0) {
                    ZYPEZDItemValueSetter.setValue(queryMdseTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(queryMdseTMsg.mdseCd, ordTakeMdseMsgArray.no(0).mdseCd);

                    mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(queryMdseTMsg);
                }
            }
        }
        return mdseTMsg;
    }
    // 2017/12/05 QC#22527 add end
    private static void log(Object... args){
        StringBuffer sb = new StringBuffer();
        for (Object s : args){
            if (s == null) {
                sb.append("null");
            } else {
                sb.append(s.toString());
            }
        }
        System.out.println("************NWZC2250 : " + sb.toString());
    }
    // 2018/01/10 QC#18460 Add Start
    /**
     * getChangeTime
     * @param cMsg NWAL2240CMsg
     * @param time String
     * @return time String
     */
    public static String getChangeTime(String time, String ctryCd, String postCd, String slsDt) {

        String wkTime = time;
        if (ZYPCommonFunc.hasValue(time)) {
            time = slsDt + time;

            SvcTimeZoneInfo svcTimeZoneInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, time, ctryCd, postCd);

            if (svcTimeZoneInfo != null) {
                time = S21StringUtil.subStringByLength(svcTimeZoneInfo.getDateTime(), TIME_START_POS, TIME_END_POS);
            } else {
                time = wkTime;
            }

        }

        return time;
    }
    // 2018/01/10 QC#18460 Add End
}
