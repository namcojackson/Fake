/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC153001;

import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.DOC_TP_OM;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.DOC_TP_RT;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0002E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0023E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0024E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0035E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0036E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0041E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0062E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0088E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0090E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0097E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0098E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0100E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0101E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0109E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0110E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0111E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0112E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0114E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0115E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0117E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0124E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0148E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0157E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0161E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0163E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0164E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0201E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0209E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0342E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0346E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0453E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0704E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0821E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM0925E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1034E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1500E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1501E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1506E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1507E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1597E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1598E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1741E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1742E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1744E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1745E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1746E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1747E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1748E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1750E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1751E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM1752E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM2020E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.NWZM2057E;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.ORD_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.ORD_CHNG;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.ORD_CREATE;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.ORD_SAVE;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.PARENT_LINE_SUB_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RECEIVED;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_DTL_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_DTL_MOD;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_DTL_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_DTL_RECEIVE;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_DTL_RWC_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_DTL_RWS_CREATE;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_DTL_SAVE;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_DTL_SHORTAGE_CLOSED;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_HDR_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_HDR_MOD;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_HDR_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_HDR_SAVE;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_HDR_UPD_HDR_AMT;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_HDR_UPD_STS;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_HDR_VAL;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RWS_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RWS_CREATE;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.SHORTAGE_RECEIVED;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.SUB_SYS_ID_NWZ;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.SUPPLY_SUB_WH_CD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.logicalRemove;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDMsg;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BILL_TO_CUSTTMsg;
import business.db.CCYTMsg;
import business.db.CHNG_RSN_TPTMsg;
import business.db.CNTYTMsg;
import business.db.CONFIG_TPTMsg;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.CPO_ORD_TPTMsg;
import business.db.CPO_RTRN_CALC_BASE_RECTMsg;
import business.db.CPO_RTRN_PRC_CALC_BASETMsg;
import business.db.CPO_RTRN_PRC_CALC_BASETMsgArray;
import business.db.CPO_SRC_TPTMsg;
import business.db.CTRYTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsgArray;
import business.db.DS_CPO_RTRN_DTL_RECTMsg;
import business.db.DS_CPO_RTRN_PRC_DTLTMsg;
import business.db.DS_ORD_LINE_PROC_DFNTMsg;
import business.db.HLDTMsg;
import business.db.HLDTMsgArray;
import business.db.INVTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsgArray;
import business.db.ORD_FUFL_LVLTMsg;
import business.db.ORD_PROC_TPTMsg;
import business.db.PKG_UOMTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_HDRTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.STK_STSTMsg;
import business.db.STTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SVC_MACH_MSTR_HISTTMsg;
import business.db.SYS_SRCTMsg;
import business.db.THIRD_PTY_DSP_TPTMsg;
import business.db.TOCTMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC153001PMsg;
import business.parts.NWZC153001_hldListPMsg;
import business.parts.NWZC153001_ordRtrnDtlListPMsg;
import business.parts.NWZC153002PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001EditPriceAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001EditPriceAmountInfo;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001RateData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001UnitPriceData;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001NumberingUtil;
import com.canon.cusa.s21.common.NWX.NWXC150001.cache.ConfigTpCache;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMA_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;

/**
 * <pre>
 * DS CPO Return Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         C.Yokoi         Create          N/A
 * 2015/12/16   Fujitsu         S.Takami        Update          S21_NA#2007
 * 2015/12/24   Fujitsu         T.Ishii         Update          S21_NA#1952
 * 2015/12/24   Fujitsu         T.Ishii         Update          S21_NA#1957
 * 2015/12/25   Fujitsu         S.Takami        Update          S21_NA#2470
 * 2015/12/25   Fujitsu         S.Takami        Update          S21_NA#2503
 * 2016/01/07   Fujitsu         S.Takami        Update          S21_NA#2588
 * 2016/01/07   Fujitsu         S.Takami        Update          S21_NA#2592
 * 2016/01/08   Fujitsu         S.Takami        Update          S21_NA#2899
 * 2016/01/14   Fujitsu         S.Takami        Update          S21_NA#2996
 * 2016/01/18   Fujitsu         S.Takami        Update          S21_NA#2996 (For Reopen)
 * 2016/01/19   Fujitsu         S.Takami        Update          S21_NA#2588 (For Reopen)
 * 2016/01/21   Fujitsu         S.Takami        Update          S21_NA#2996 (Spec. Fixed)
 * 2016/02/15   Fujitsu         S.Takami        Update          S21_NA#1618 (New CPO_ORD_NUM)
 * 2016/02/18   Fujitsu         S.Takami        Update          S21_NA#2336
 * 2016/02/22   Fujitsu         S.Takami        Update          S21_NA#2830, S21_NA#3848
 * 2016/02/24   Fujitsu         S.Takami        Update          S21_NA#3429
 * 2016/02/24   Fujitsu         S.Ohki          Update          S21_NA#3479
 * 2016/02/26   Fujitsu         S.Ohki          Update          S21_NA#3328
 * 2016/02/26   Fujitsu         T.Ishii         Update          S21_NA#3960
 * 2016/02/29   Fujitsu         T.Ishii         Update          S21_NA#4323
 * 2016/03/01   Fujitsu         S.Ohki          Update          S21_NA#3328
 * 2016/03/02   Fujitsu         T.Ishii         Update          S21_NA#4374
 * 2016/03/03   Fujitsu         Y.Taoka         Update          S21_NA#4564
 * 2016/03/25   Fujitsu         S.Yamamoto      Update          S21_NA#6063
 * 2016/04/05   Fujitsu         S.Takami        Update          S21_NA#5519-2
 * 2016/04/07   Fujitsu         S.Takami        Update          S21_NA#5331
 * 2016/04/19   Fujitsu         S.Takami        Update          S21_NA#5394
 * 2016/04/20   Fujitsu         Y.Taoka         Update          S21_NA#7349
 * 2016/04/26   Fujitsu         S.Takami        Update          S21_NA#7338
 * 2016/05/10   Fujitsu         Y.Taoka         Update          S21_NA#7606
 * 2016/06/02   Fujitsu         Y.Taoka         Update          S21_NA#7606
 * 2016/06/02   Fujitsu         M.Yamada        Update          S21_NA#5395
 * 2016/06/03   Fujitsu         K.Sato          Update          S21_NA#9275
 * 2016/06/06   Fujitsu         K.Sato          Update          S21_NA#9374
 * 2016/06/09   Fujitsu         S.Takami        Update          S21_NA#5331-2
 * 2016/06/20   Fujitsu         S.Takami        Update          S21_NA#5331-3
 * 2016/06/28   Fujitsu         Y.Taoka         Update          S21_NA10893
 * 2016/07/08   Fujitsu         M.Hara          Update          S21_NA#11406
 * 2016/07/12   Fujitsu         M.Hara          Update          S21_NA#7356
 * 2016/07/12   Fujitsu         H.Ikeda         Update          S21_NA#10418
 * 2016/07/26   Fujitsu         T.Mizuki        Update          S21_NA#8854
 * 2016/08/01   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2016/09/05   Fujitsu         W.Honda         Update          S21_NA#12435
 * 2016/09/08   Fujitsu         S.Takami        Update          S21_NA#6100
 * 2016/09/27   Fujitsu         S.Takami        Update          S21_NA#9102
 * 2016/10/06   Fujitsu         S.Takami        Update          S21_NA#9102-2
 * 2016/10/07   Fujitsu         W.Honda         Update          S21_NA#9205
 * 2016/10/26   Fujitsu         Y.Kanefusa      Update          S21_NA#12481
 * 2016/11/04   Fujitsu         S.Takami        Update          S21_NA#14833
 * 2016/11/18   Fujitsu         S.Takami        Update          S21_NA#16107
 * 2016/12/05   Fujitsu         S.Ohki          Update          S21_NA#16305
 * 2016/12/14   Fujitsu         T.Yoshida       Update          S21_NA#15837
 * 2016/12/15   Fujitsu         W.Honda         Update          S21_NA#16588
 * 2016/12/20   Fujitsu         S.Ohki          Update          S21_NA#15898-2
 * 2016/12/21   Fujitsu         N.Aoyama        Update          S21_NA#16416
 * 2017/01/24   Fujitsu         R.Nakamura      Update          S21_NA#17257
 * 2017/02/15   Fujitsu         S.Takami        Update          S21_NA#17207
 * 2017/03/01   Fujitsu         N.Aoyama        Update          S21_NA#16575
 * 2017/03/07   Fujitsu         S.Takami        Update          S21_NA#17993
 * 2017/03/14   Fujitsu         T.Ishii         Update          S21_NA#18003
 * 2017/04/20   Fujitsu         S.Iidaka        Update          S21_NA#18286
 * 2017/04/28   Fujitsu         S.Takami        Update          S21_NA#RS#5881
 * 2017/06/21   Fujitsu         R.Nakamura      Update          S21_NA#19407
 * 2017/06/27   Fujitsu         R.Nakamura      Update          S21_NA#19585
 * 2017/07/03   Fujitsu         R.Nakamura      Update          S21_NA#19709
 * 2017/09/14   CITS            T.Kikuhara      Update          QC#18673(L3)
 * 2017/09/29   Fujitsu         A.Kosai         Update          S21_NA#21151
 * 2017/10/16   Fujitsu         R.Nakamura      Update          S21_NA#21507
 * 2017/11/17   Fujitsu         A.Kosai         Update          S21_NA#22252
 * 2017/12/01   Fujitsu         A.Kosai         Update          S21_NA#22343
 * 2017/12/05   Fujitsu         T.Aoi           Update          S21_NA#22693
 * 2018/01/11   Fujitsu         A.Kosai         Update          S21_NA#22372
 * 2018/02/23   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/15   Fujitsu         R.Nakamura      Update          S21_NA#24258
 * 2018/03/28   Fujitsu         S.Takami        Update          S21_NA#25097
 * 2018/04/20   Fujitsu         K.Ishizuka      Update          S21_NA#23708
 * 2018/06/07   Fujitsu         Y.Kanefusa      Update          S21_NA#26415
 * 2018/06/28   Fujitsu         M.Yamada        Update          QC#25227
 * 2018/07/12   Fujitsu         S.Takami        Update          S21_NA#26895-2
 * 2018/07/30   Fujitsu         M.Ohno          Update          S21_NA#26413
 * 2018/08/07   Fujitsu         S.Takami        Update          S21_NA#25404
 * 2018/08/14   Fujitsu         K.Ishizuka      Update          S21_NA#27718
 * 2018/08/23   Fujitsu         Mz.Takahashi    Update          QC#27243
 * 2018/08/30   Fujitsu         S.Takami        Update          S21_NA#26895-3
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 * 2018/11/05   Fujitsu         Mz.Takahashi    Update          QC#29040
 * 2018/11/15   Fujitsu         K.Ishizuka      Update          S21_NA#27299
 * 2018/12/18   Fujitsu         K.Ishizuka      Update          S21_NA#29561
 * 2019/03/29   Fujitsu         M.Ohno          Update          QC#30700
 * 2019/04/03   Fujitsu         Hd.Sugawara     Update          QC#31018
 * 2019/04/04   Fujitsu         Mz.Takahashi    Update          QC#31053
 * 2020/01/10   Fujitsu         K.Kato          Update          QC#55364
 * 2020/04/14   Fujitsu         Y.Kanefusa      Update          S21_NA#56558
 * 2020/05/25   Fujitsu         C.Hara          Update          QC#56558-1
 * 2022/08/16   CITS            F.Fadriquela    Update          QC#60341
 *</pre>
 */
public class NWZC153001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchType;

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmClient;

    /** Max Amount of Digits for BigDecimal */
    private static final BigDecimal MAX_AMT = new BigDecimal("99999999999.9999");

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /** TimeStamp 2016/09/05 S21_NA#6100 Add */
//    private String curTime = null;
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    /**
     * Local Data Cache Object.
     */
    private final LocalCache localCache = new LocalCache();

    /** Cache DB Access */
    private static NWZC153001_CacheFindByKey cacheDBAccess = NWZC153001_CacheFindByKey.getInstance();

    /**
     * constructor
     */
    public NWZC153001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsg NWZC153001PMsg
     * @param linePMsgList List<NWZC153002PMsg>
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(final NWZC153001PMsg pMsg, final List<NWZC153002PMsg> linePMsgList, final ONBATCH_TYPE onBatchTp) {
        this.msgMap = new S21ApiMessageMap(pMsg);
        this.onBatchType = onBatchTp;
        // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//        this.curTime = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN); // 2016/09/08 S21_NA#6100 Add
        // 2017/04/07 S21_NA#Review structure Lv.2 Del End

        initializeResData(pMsg, linePMsgList); // 2016/02/18 S21_NA#2336
        doProcess(pMsg, linePMsgList);

        // 2016/02/18 S21_NA#2336 Add Start
        // check Error
        List<NWZC153002PMsg> errLinePMsgList = checkError(linePMsgList);
        linePMsgList.clear();
        if (errLinePMsgList.size() > 0) {
            linePMsgList.addAll(errLinePMsgList);
        }
        // 2016/02/18 S21_NA#2336 Add End

        msgMap.flush();
    }

    /**
     * doProcess
     * @param param NWZC153001PMsg
     * @param lineParams List<NWZC153002PMsg>
     */
    private void doProcess(NWZC153001PMsg pMsg, final List<NWZC153002PMsg> linePMsgList) {

        // 2017/04/26 S21_NA#Review structure Lv.2 Add Start
        final boolean isValidHdrRqst = RQST_HDR_VAL.equals(pMsg.xxRqstTpCd.getValue());
        String origXxRqstHdrTpCd = null;
        List<String> origDtlRqstModeList = null;
        if (isValidHdrRqst) {
            origXxRqstHdrTpCd = pMsg.xxRqstTpCd.getValue();
            pMsg.xxRqstTpCd.setValue(RQST_HDR_NEW);
            origDtlRqstModeList = new ArrayList<String>(pMsg.ordRtrnDtlList.getValidCount());

            for (int i = 0; i < pMsg.ordRtrnDtlList.getValidCount(); i++) {
                origDtlRqstModeList.add(pMsg.ordRtrnDtlList.no(i).xxRqstTpCd.getValue());
                if (!S21StringUtil.isEquals(RQST_DTL_CANCEL, pMsg.ordRtrnDtlList.no(i).xxRqstTpCd.getValue())) {
                    pMsg.ordRtrnDtlList.no(i).xxRqstTpCd.setValue(RQST_DTL_NEW);
                }
            }
        }
        // 2017/04/26 S21_NA#Review structure Lv.2 Add End
        // ===================================================================
        // 0. pre-processing
        // ===================================================================
        final String xxRqstHdrTpCd = pMsg.xxRqstTpCd.getValue();
        // 2016/01/19 S21_NA#2588 (For Reopen) Del Start
        // 2016/01/07 S21_NA#2588 Add Start
//        if (RQST_HDR_UPD_HDR_AMT.equals(xxRqstHdrTpCd)) {
//            execUpdCpoAmtMode(pMsg);
//            return;
//        }
        // 2016/01/07 S21_NA#2588 Add End
        // 2016/01/14 S21_NA#2996 Add Start
        // 2016/01/19 S21_NA#2588 (For Reopen) Del End
        CPO_DTLTMsgArray cpoDtlTMsgArray = null;
        if (ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
            cpoDtlTMsgArray = getRelatedCpoDtlArray(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue());
        }
        // 2016/01/14 S21_NA#2996 Add End
        final boolean isSaveHdrRqst = RQST_HDR_SAVE.equals(xxRqstHdrTpCd);
        final boolean isNewHdrRqst = RQST_HDR_NEW.equals(xxRqstHdrTpCd);
        final boolean isCancelHdrRqst = RQST_HDR_CANCEL.equals(xxRqstHdrTpCd);
        final boolean isModifyHdrRqst = RQST_HDR_MOD.equals(xxRqstHdrTpCd);
        final boolean isUpdStsHdrRqst = RQST_HDR_UPD_STS.equals(xxRqstHdrTpCd);
        // final boolean isPureReturn = isPureReturn(pMsg); // 2016/01/14 S21_NA#2996 Del
        final boolean isPureReturn = isPureReturn(pMsg, cpoDtlTMsgArray);  // 2016/01/14 S21_NA#2996 Add

        // 2016/01/19 S21_NA#2588 (For Reopen) Add Start
        boolean isOrdValidated = false; // 2016/01/20 S21_NA#2996 Add
        boolean isOrderBooked = false;
        CPOTMsg cpoTMsg = (CPOTMsg) getCpoTMsg(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue());
        if (null != cpoTMsg) {
            isOrdValidated = ORD_HDR_STS.VALIDATED.equals(String.valueOf(cpoTMsg.ordHdrStsCd.getValue()));
            isOrderBooked = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cpoTMsg.ordBookFlg.getValue());
        }

        if (RQST_HDR_UPD_HDR_AMT.equals(xxRqstHdrTpCd) || (!isPureReturn && isNewHdrRqst && isOrdValidated)) {
            // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//            execUpdCpoAmtMode(pMsg);
            // 2017/04/07 S21_NA#Review structure Lv.2 Del End

            if (isOrdValidated) {
                // ===================================================================
                // 13. Allocation for Service Machine Master
                // ===================================================================
                final NWZC153001CpoBean cpoBean = new NWZC153001CpoBean(pMsg);
                registAlloc(cpoBean, linePMsgList);
            }
            return;
        }
        // 2016/01/19 S21_NA#2588 (For Reopen) Add End
        // Check Parameter
        if (!checkParam(pMsg, linePMsgList, isSaveHdrRqst, isNewHdrRqst, isCancelHdrRqst, isModifyHdrRqst, isUpdStsHdrRqst)) {
            return;
        }

        // Check Master
        checkMaster(pMsg, linePMsgList, isSaveHdrRqst, isNewHdrRqst, isModifyHdrRqst);
        if (hasError(pMsg) || hasError(linePMsgList)) {
            return;
        }

        // ===================================================================
        // 1. Set Data
        // ===================================================================
        final NWZC153001CpoBean cpoBean = new NWZC153001CpoBean(pMsg);
        if (!isUpdStsHdrRqst) {
            setData(cpoBean, linePMsgList);
        }

        if (hasError(pMsg) || hasError(linePMsgList)) {
            return;
        }

        // ===================================================================
        // 2. Check Relations
        // ===================================================================
        if (!isCancelHdrRqst) {
            checkRelation(pMsg, cpoBean, linePMsgList, isSaveHdrRqst, isNewHdrRqst, isUpdStsHdrRqst);
        }

        if (hasError(pMsg) || hasError(linePMsgList)) {
            return;
        }

        // 2016/09/27 S21_NA#9102 Add Start
        int errCnt = 0;
        LinkedHashMap<String, List<NWZC153001DetailBean>> cpoConfigMap = createConfigMap(cpoBean);
        for (List<NWZC153001DetailBean> cpoDtlBeanList : cpoConfigMap.values()) {
            if (isSetDiffrentRefNum(cpoDtlBeanList, linePMsgList)) {
                errCnt++;
            }
        }
        if (errCnt > 0) {
            return;
        }
        // 2016/09/27 S21_NA#9102 Add End

        // ===================================================================
        // 3. Edit Amount
        // ===================================================================
        if (isSaveHdrRqst || isNewHdrRqst || isModifyHdrRqst) {
            editAmount(pMsg, cpoBean, linePMsgList);
        }

        if (hasError(pMsg) || hasError(linePMsgList)) {
            return;
        }

        // 2017/04/26 S21_NA#Review structure Lv.2 Add Start
        if (isValidHdrRqst) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, origXxRqstHdrTpCd);

            for (int i = 0; i < pMsg.ordRtrnDtlList.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(pMsg.ordRtrnDtlList.no(i).xxRqstTpCd, origDtlRqstModeList.get(i));
            }
            return;
        }
        // 2017/04/26 S21_NA#Review structure Lv.2 Add end

        // ===================================================================
        // 4. Number Unique CPO Number
        // ===================================================================
        // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
//            // 2016/02/15 S21_NA#1618 Mod Start
//            // String orderNum = ZYPNumbering.getUniqueID(pMsg.glblCmpyCd.getValue(), SQ_CPO_NUM);
//            String orderNum = ZYPMaxTenDigitsNumbering.getUniqueID("CPO_ORD_NUM");
//            // 2016/02/15 S21_NA#1618 Mod End
//            cpoBean.setCpoOrdNum(orderNum);
//        } else { 2017/04/07 S21_NA#Review structure Lv.2 Del End
            cpoBean.setCpoOrdNum(pMsg.cpoOrdNum.getValue());
//        } 2017/04/07 S21_NA#Review structure Lv.2 Del

        // ===================================================================
        // 5. DB Access processing (DS_CPO_RTRN_DTL)
        // ===================================================================
        // 2016/04/26 S21_NA#7338 Mod Start
        // registDsCpoRtrnDtl(pMsg, cpoBean, linePMsgList);
        registDsCpoRtrnDtl(pMsg, cpoBean, linePMsgList, isOrderBooked, cpoTMsg);
        // 2016/04/26 S21_NA#7338 Mod End

        // 2016/09/27 S21_NA#9102 Del Start
//        // 2016/07/06 S21_NA#7755 Add Start
//        if (!isUpdStsHdrRqst) {
//            updateBaseCmptFlg(pMsg, cpoBean, linePMsgList);
//        }
//        // 2016/07/06 S21_NA#7755 Add End
        // 2016/09/27 S21_NA#9102 Del Start

        if (hasError(pMsg) || hasError(linePMsgList)) {
            return;
        }

        // ===================================================================
        // 6. DB Access processing (CPO)
        // ===================================================================
        // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//        boolean isInsertForCPO = true;
//        //if (!ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum_BK())) { 2015/12/16 S21_NA#2007 Del
//        if (ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum_BK())) { // 2015/12/16 S21_NA#2007 Add
//            isInsertForCPO = false;
//        }
        // 2017/04/07 S21_NA#Review structure Lv.2 Del End

        if (!isUpdStsHdrRqst) {
            // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//            // 6.1 DB Access CPO
//            registCpo(pMsg, cpoBean, isInsertForCPO, isSaveHdrRqst, isNewHdrRqst, isCancelHdrRqst, isModifyHdrRqst, isPureReturn);
//            if (hasError(pMsg) || hasError(linePMsgList)) {
//                return;
//            }
//
//            // 6.2 DB Access CPO
//            if (isPureReturn) {
//                registDsCpo(pMsg, cpoBean, isInsertForCPO, isSaveHdrRqst, isNewHdrRqst, isModifyHdrRqst, isOrdValidated);
//                if (hasError(pMsg) || hasError(linePMsgList)) {
//                    return;
//                }
//            }
            // 2017/04/07 S21_NA#Review structure Lv.2 Del End

            // 2015/12/16 S21_NA#2007 Add Start
            if (isPureReturn && !ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
                ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
            }
            // 2015/12/16 S21_NA#2007 Add End
        }

        // ===================================================================
        // 7. DB Access processing (RTRN_PRC_CALC_BASE)
        // ===================================================================
        if (!isUpdStsHdrRqst) {
            registRtnPrcCalc(cpoBean, linePMsgList);

            if (hasError(pMsg) || hasError(linePMsgList)) {
                return;
            }
        }

        // ===================================================================
        // 8. DB Access processing (RTRN_PRC_DTL)
        // ===================================================================
        // S21_NA#11406
//        if (!isSaveHdrRqst && !isUpdStsHdrRqst) {
        if (!isUpdStsHdrRqst) {
            registDsCpoRtrnPrcDtl(cpoBean, linePMsgList);

            if (hasError(pMsg) || hasError(linePMsgList)) {
                return;
            }
        }

        // ===================================================================
        // 9. Create Business Process Log
        // ===================================================================
        // if (!isUpdStsHdrRqst) {  2015/12/25 S21_NA#2503 Delete Condition
            // Insert Header Level Business Process Log
            // 2016/01/14 S21_NA#2996 Mod Start
            //registHdrBizProcLog(cpoBean, isSaveHdrRqst, isNewHdrRqst, isCancelHdrRqst, isModifyHdrRqst);
        // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//            registHdrBizProcLog(cpoBean, isSaveHdrRqst, isNewHdrRqst, isCancelHdrRqst, isModifyHdrRqst, cpoDtlTMsgArray);
        // 2017/04/07 S21_NA#Review structure Lv.2 Del End
            // 2016/01/14 S21_NA#2996 Mod Start
            if (hasError(pMsg)) {
                return;
            } // 2015/12/25 S21_NA#2503 Delete Condition

            // Insert Line Level Business Process Log
            registDtlBizProcLog(cpoBean, linePMsgList, xxRqstHdrTpCd); // 2016/02/23 S21_NA#3328 Mod
            if (hasError(linePMsgList)) {
                return;
            }
        // }

        // ===================================================================
        // 10. DB Access processing (Hold)
        // ===================================================================
        if (isModifyHdrRqst) {
            // update Hold Quantity
            changeHldOrdQty(cpoBean, linePMsgList);
            if (hasError(linePMsgList)) {
                return;
            }

            // insert Hold List
            registHold(cpoBean, linePMsgList);
            if (hasError(linePMsgList)) {
                return;
            }
        }

        // 2017/04/28 S21_NA#RS#5881 Del Start
//        // ===================================================================
//        // 11. Return Validation
//        // ===================================================================
//        // if (!isSaveHdrRqst && !isCancelHdrRqst) { 2016/01/14 S21_NA#2996 Mod Condition
//        //if (isPureReturn && (!isSaveHdrRqst && !isCancelHdrRqst)) { // 2016/01/14 S21_NA#2996 Mod Condition
//        if (isPureReturn && (!isSaveHdrRqst && !isCancelHdrRqst) && !isOrderBooked) { // 2016/04/19 S21_NA#5394 Mod Condition
//            validateOrder(cpoBean, linePMsgList, pMsg, isNewHdrRqst, isModifyHdrRqst);
//        }
        // 2017/04/28 S21_NA#RS#5881 Del End

        // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//        // ===================================================================
//        // 12. Insert History (XXXX_REC)
//        // ===================================================================
//        if (ZYPCommonFunc.hasValue(cpoBean.getBizProcLogPk())) {
//            // Insert CPO_REC
//            insCpoRec(cpoBean);
//            if (hasError(pMsg)) {
//                return;
//            }
//        }
        // 2017/04/07 S21_NA#Review structure Lv.2 Del End

        // Insert DS_CPO_RTRN_DTL_REC
        if (!isUpdStsHdrRqst) { // 2015/12/25 S21_NA#2503 Add Condition
            insDsCpoRtrnDtlRec(cpoBean, linePMsgList);
            if (hasError(linePMsgList)) {
                return;
            }
        } // 2015/12/25 S21_NA#2503 Add Condition

        // 2016/02/23 S21_NA#3479 Mod Start
        // Insert DS_CPO_RTRN_DTL_REC
        if (!isUpdStsHdrRqst) {
            insDsRtrnPrcCalcBase(cpoBean, linePMsgList);
            if (hasError(linePMsgList)) {
                return;
            }
        }
        // 2016/02/23 S21_NA#3479 Mod End

        // ===================================================================
        // 13. Allocation for Service Machine Master
        // ===================================================================
        registAlloc(cpoBean, linePMsgList);
    }

    /**
     * The input data is posted in the output item. When RequestType
     * of Save and Detail is entire Cancel, RequestType of Header
     * doesn't return OrderNumber.
     * @param pMsg NWZC153001PMsg
     * @param linePMsgList List<NWZC153002PMsg>
     */
    private static void initializeResData(NWZC153001PMsg pMsg, List<NWZC153002PMsg> linePMsgList) {
        // Make List<DWZC001002PMsg>
        for (int i = 0; i < pMsg.ordRtrnDtlList.getValidCount(); i++) {

            final NWZC153002PMsg linePMsg = new NWZC153002PMsg();

            final NWZC153001_ordRtrnDtlListPMsg copyFromLinePMsg = pMsg.ordRtrnDtlList.no(i);
            ZYPEZDItemValueSetter.setValue(linePMsg.dsCpoRtrnLineNum, copyFromLinePMsg.dsCpoRtrnLineNum);
            ZYPEZDItemValueSetter.setValue(linePMsg.dsCpoRtrnLineSubNum, copyFromLinePMsg.dsCpoRtrnLineSubNum);

            linePMsgList.add(linePMsg);
        }
    }

    /**
     * checkParam
     * @param pMsg NWZC153001PMsg
     * @param linePMsgList final List<NWZC153002PMsg>
     * @param isSaveHdrRqst boolean
     * @param isNewHdrRqst boolean
     * @param isCancelHdrRqst boolean
     * @param isModifyHdrRqst boolean
     * @param isUpdStsHdrRqst boolean
     * @return boolean
     */
    private boolean checkParam(NWZC153001PMsg pMsg, final List<NWZC153002PMsg> linePMsgList, boolean isSaveHdrRqst, boolean isNewHdrRqst, boolean isCancelHdrRqst, boolean isModifyHdrRqst, boolean isUpdStsHdrRqst) {
        if (pMsg.ordRtrnDtlList.getValidCount() < 1) {
            msgMap.addXxMsgId(NWZM0821E);
            return false;
        }

        // Common Check Parameter
        checkParamForCommonCpo(pMsg, isSaveHdrRqst, isNewHdrRqst, isCancelHdrRqst, isModifyHdrRqst, isUpdStsHdrRqst);
        checkParamForCommonDtl(pMsg, linePMsgList);
        checkParamForHld(pMsg, linePMsgList);

        if (hasError(pMsg) || hasError(linePMsgList)) {
            return false;
        }

        // Check Combination of Request Type in Parameter
        checkCombiOfRqstTp(pMsg, linePMsgList, isSaveHdrRqst, isNewHdrRqst, isCancelHdrRqst, isModifyHdrRqst, isUpdStsHdrRqst);
        if (hasError(linePMsgList)) {
            return false;
        }

        // Individual Check Parameter
        checkIndividualParamsForCpo(pMsg, isCancelHdrRqst, isModifyHdrRqst, isUpdStsHdrRqst);
        checkIndividualParamForDtl(pMsg, linePMsgList);
        if (hasError(pMsg) || hasError(linePMsgList)) {
            return false;
        }

        return true;
    }

    /**
     * checkParamForCommonCpo
     * @param pMsg NWZC153001PMsg
     * @param isSaveHdrRqst boolean
     * @param isNewHdrRqst boolean
     * @param isCancelHdrRqst boolean
     * @param isModifyHdrRqst boolean
     * @param isUpdStsHdrRqst boolean
     */
    private void checkParamForCommonCpo(NWZC153001PMsg pMsg, boolean isSaveHdrRqst, boolean isNewHdrRqst, boolean isCancelHdrRqst, boolean isModifyHdrRqst, boolean isUpdStsHdrRqst) {
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0163E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            msgMap.addXxMsgId(NWZM0346E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxRqstTpCd)) {
            msgMap.addXxMsgId(NWZM0157E);

        } else if (!isSaveHdrRqst && !isNewHdrRqst && !isModifyHdrRqst && !isCancelHdrRqst && !isUpdStsHdrRqst) {
            msgMap.addXxMsgId(NWZM0209E);
        }
    }

    /**
     * checkParamForCommonDtl
     * @param pMsg NWZC153001PMsg
     * @param linePMsgList final List<NWZC153002PMsg>
     */
    private void checkParamForCommonDtl(NWZC153001PMsg pMsg, final List<NWZC153002PMsg> linePMsgList) {
        for (int idx = 0; idx < pMsg.ordRtrnDtlList.getValidCount(); idx++) {
            NWZC153001_ordRtrnDtlListPMsg ordRtrnDtlPMsg = pMsg.ordRtrnDtlList.no(idx);

            if (!ZYPCommonFunc.hasValue(ordRtrnDtlPMsg.dsCpoRtrnLineNum)) {
                setErrMsgIdForCpoDtl(NWZM0100E, linePMsgList, idx);
            }
            if (!ZYPCommonFunc.hasValue(ordRtrnDtlPMsg.dsCpoRtrnLineSubNum)) {
                setErrMsgIdForCpoDtl(NWZM0101E, linePMsgList, idx);
            }

            String dtlRqstTp = ordRtrnDtlPMsg.xxRqstTpCd.getValue();
            if (!ZYPCommonFunc.hasValue(dtlRqstTp)) {
                setErrMsgIdForCpoDtl(NWZM0164E, linePMsgList, idx);
            // mod start 2016/07/26 CSA S21_NA#8854
            } else if (!RQST_DTL_SAVE.equals(dtlRqstTp) && !RQST_DTL_NEW.equals(dtlRqstTp) && !RQST_DTL_MOD.equals(dtlRqstTp) && !RQST_DTL_CANCEL.equals(dtlRqstTp) && !RQST_DTL_RWS_CREATE.equals(dtlRqstTp)
                    && !RQST_DTL_RWC_CANCEL.equals(dtlRqstTp) && !RQST_DTL_RECEIVE.equals(dtlRqstTp) && !RQST_DTL_SHORTAGE_CLOSED.equals(dtlRqstTp)) {
            // mod end 2016/07/26 CSA S21_NA#8854
                setErrMsgIdForCpoDtl(NWZM0209E, linePMsgList, idx);
            }
        }
    }

    /**
     * checkParamForHld
     * @param pMsg NWZC153001PMsg
     * @param linePMsgList final List<NWZC153002PMsg>
     */
    private void checkParamForHld(NWZC153001PMsg pMsg, final List<NWZC153002PMsg> linePMsgList) {
        if (pMsg.hldList.getValidCount() < 1) {
            return;
        }

        NWZC153001_hldListPMsg hldList = null;
        for (int i = 0; i < pMsg.hldList.getValidCount(); i++) {
            hldList = pMsg.hldList.no(i);

            // set error if Request type is null
            if (!ZYPCommonFunc.hasValue(hldList.xxRqstTpCd)) {
                for (int idx = 0; idx < pMsg.ordRtrnDtlList.getValidCount(); idx++) {

                    if (hldList.dsCpoRtrnLineNum.getValue().equals(pMsg.ordRtrnDtlList.no(idx).dsCpoRtrnLineNum.getValue()) && hldList.dsCpoRtrnLineSubNum.getValue().equals(pMsg.ordRtrnDtlList.no(idx).dsCpoRtrnLineSubNum.getValue())) {
                        // set error
                        setErrMsgIdForCpoDtl(NWZM1034E, linePMsgList, idx);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Check combination of request type in Header and Detail.
     * @param pMsg NWZC153001PMsg
     * @param linePMsgList List<NWZC153002PMsg>
     * @param isSaveHdrRqst boolean
     * @param isNewHdrRqst boolean
     * @param isCancelHdrRqst boolean
     * @param isModifyHdrRqst boolean
     * @param isUpdStsHdrRqst boolean
     */
    private void checkCombiOfRqstTp(NWZC153001PMsg pMsg, final List<NWZC153002PMsg> linePMsgList, boolean isSaveHdrRqst, boolean isNewHdrRqst, boolean isCancelHdrRqst, boolean isModifyHdrRqst, boolean isUpdStsHdrRqst) {

        NWZC153001_ordRtrnDtlListPMsg ordRtrnDtlPMsg = null;
        for (int idx = 0; idx < pMsg.ordRtrnDtlList.getValidCount(); idx++) {
            ordRtrnDtlPMsg = pMsg.ordRtrnDtlList.no(idx);

            if (isSaveHdrRqst) {
                if (!RQST_DTL_SAVE.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue()) && !RQST_DTL_CANCEL.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue())) {
                    setErrMsgIdForCpoDtl(NWZM0209E, linePMsgList, idx);
                    continue;
                }
            }

            if (isNewHdrRqst) {
                if (!RQST_DTL_NEW.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue())) {
                    setErrMsgIdForCpoDtl(NWZM0209E, linePMsgList, idx);
                    continue;
                }
            }

            if (isCancelHdrRqst) {
                if (!RQST_DTL_CANCEL.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue())) {
                    setErrMsgIdForCpoDtl(NWZM0209E, linePMsgList, idx);
                    continue;
                }
            }

            if (isModifyHdrRqst) {
                if (!RQST_DTL_NEW.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue()) && !RQST_DTL_CANCEL.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue()) && !RQST_DTL_MOD.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue())) {
                    setErrMsgIdForCpoDtl(NWZM0209E, linePMsgList, idx);
                    continue;
                }
            }

            if (isUpdStsHdrRqst) {
                // mod start 2016/07/26 CSA S21_NA#8854
                if (!RQST_DTL_RWS_CREATE.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue()) && !RQST_DTL_RWC_CANCEL.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue()) 
                        && !RQST_DTL_RECEIVE.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue()) && !RQST_DTL_SHORTAGE_CLOSED.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue())) {
                // mod end 2016/07/26 CSA S21_NA#8854
                    setErrMsgIdForCpoDtl(NWZM0209E, linePMsgList, idx);
                }
            }
        }
    }

    /**
     * check Parameter For Cpo.
     * @param pMsg NWZC153001PMsg
     * @param isCancelHdrRqst boolean
     * @param isModifyHdrRqst boolean
     * @param isUpdStsHdrRqst boolean
     */
    private void checkIndividualParamsForCpo(NWZC153001PMsg pMsg, boolean isCancelHdrRqst, boolean isModifyHdrRqst, boolean isUpdStsHdrRqst) {
        // Modify, Cancel or Update Status
        if (isModifyHdrRqst || isCancelHdrRqst || isUpdStsHdrRqst) {
            if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
                msgMap.addXxMsgId(NWZM0002E);
            }
        }

        if (isCancelHdrRqst || isUpdStsHdrRqst) {
            return;
        }

        // Modify, Save or New
        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdTpCd)) {
            msgMap.addXxMsgId(NWZM0097E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.addShipToCustCd)) {
            msgMap.addXxMsgId(NWZM0201E);
        }

        if (isModifyHdrRqst) {
            return;
        }

        // SAVE or New
        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdTpCd)) {
            msgMap.addXxMsgId(NWZM0097E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.addShipToCustCd)) {
            msgMap.addXxMsgId(NWZM0201E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.cpoSrcTpCd)) {
            msgMap.addXxMsgId(NWZM0098E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.sysSrcCd)) {
            msgMap.addXxMsgId(NWZM0453E);
        }

    }

    /**
     * check Parameter For Detail.
     * @param pMsg NWZC153001PMsg
     * @param linePMsgList List<NWZC153002PMsg>
     */
    private void checkIndividualParamForDtl(NWZC153001PMsg pMsg, final List<NWZC153002PMsg> linePMsgList) {
        // 2018/07/30 S21_NA#26413 Add Start
        CPOTMsg cpoMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoMsg.cpoOrdNum, pMsg.cpoOrdNum);
        cpoMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(cpoMsg);
        // 2018/07/30 S21_NA#26413 Add End

        for (int idx = 0; idx < pMsg.ordRtrnDtlList.getValidCount(); idx++) {
            NWZC153001_ordRtrnDtlListPMsg ordRtrnDtlPMsg = pMsg.ordRtrnDtlList.no(idx);
            String dtlRqstTp = ordRtrnDtlPMsg.xxRqstTpCd.getValue();

            // RWS Create or RWS Cancel
            // mod start 2016/07/26 CSA S21_NA#8854
            if (RQST_DTL_RWS_CREATE.equals(dtlRqstTp) || RQST_DTL_RWC_CANCEL.equals(dtlRqstTp) || RQST_DTL_SHORTAGE_CLOSED.equals(dtlRqstTp)) {
            // mod end 2016/07/26 CSA S21_NA#8854
                continue;
            }

            // RWS Receive
            if (RQST_DTL_RECEIVE.equals(dtlRqstTp)) {
                if (!ZYPCommonFunc.hasValue(ordRtrnDtlPMsg.rtrnQty)) {
                    setErrMsgIdForCpoDtl(NWZM0342E, linePMsgList, idx);
                }
                continue;
            }

            // Cancel, Save, New or Modify
            if (!ZYPCommonFunc.hasValue(ordRtrnDtlPMsg.mdseCd)) {
                setErrMsgIdForCpoDtl(NWZM0035E, linePMsgList, idx);
            }
            if (!ZYPCommonFunc.hasValue(ordRtrnDtlPMsg.ordQty)) {
                setErrMsgIdForCpoDtl(NWZM0041E, linePMsgList, idx);
            }

            if (RQST_DTL_CANCEL.equals(dtlRqstTp)) {
                continue;
            }

            // Save, New or Modify
            if (!ZYPCommonFunc.hasValue(ordRtrnDtlPMsg.shipToCustCd)) {
                setErrMsgIdForCpoDtl(NWZM0201E, linePMsgList, idx);
            }
            // 2018/07/30 S21_NA#26413 Mod Start
//          if (!ZYPCommonFunc.hasValue(ordRtrnDtlPMsg.rtrnRsnCd))) {
            // 2018/12/18 S21_NA#29561 Mod Start
            // if (!ZYPCommonFunc.hasValue(ordRtrnDtlPMsg.rtrnRsnCd) && !CPO_SRC_TP.COPY.equals(cpoMsg.cpoSrcTpCd.getValue())) {
            // if (!ZYPCommonFunc.hasValue(ordRtrnDtlPMsg.rtrnRsnCd) && (cpoMsg != null && (!CPO_SRC_TP.COPY.equals(cpoMsg.cpoSrcTpCd.getValue()) && !CPO_SRC_TP.CREDIT.equals(cpoMsg.cpoSrcTpCd.getValue())) || //
            //         !S21StringUtil.isEquals(CPO_SRC_TP.CREDIT, ordRtrnDtlPMsg.cpoSrcTpCd.getValue()))) {
            if (!ZYPCommonFunc.hasValue(ordRtrnDtlPMsg.rtrnRsnCd)) { //  2018/12/18 S21_NA#29561-1 Mod
                if(!((cpoMsg != null && CPO_SRC_TP.COPY.equals(cpoMsg.cpoSrcTpCd.getValue())) || S21StringUtil.isEquals(CPO_SRC_TP.CREDIT, ordRtrnDtlPMsg.cpoSrcTpCd.getValue()))) {
            // 2018/07/30 S21_NA#26413 Mod End
                // 2018/12/18 S21_NA#29561 Mod End
                    setErrMsgIdForCpoDtl(NWZM1506E, linePMsgList, idx); //S21_NA#18286
                }
            }
        }
    }

    /**
     * check Master.
     * @param pMsg NWZC153001PMsg
     * @param linePMsgList List<NWZC153002PMsg>
     * @param isSaveHdrRqst boolean
     * @param isNewHdrRqst boolean
     * @param isModifyHdrRqst boolean
     */
    private void checkMaster(NWZC153001PMsg pMsg, final List<NWZC153002PMsg> linePMsgList, boolean isSaveHdrRqst, boolean isNewHdrRqst, boolean isModifyHdrRqst) {
        // Check Master for order Header
        // Save, New or Modify Mode
        if (isSaveHdrRqst || isNewHdrRqst || isModifyHdrRqst) {
            checkMstrCommonHdr(pMsg, isModifyHdrRqst);
        }

        // Check Master for Order Detail
        NWZC153001_ordRtrnDtlListPMsg ordRtrnDtlPMsg = null;
        for (int idx = 0; idx < pMsg.ordRtrnDtlList.getValidCount(); idx++) {
            ordRtrnDtlPMsg = pMsg.ordRtrnDtlList.no(idx);

            // skip if request mode is not target of master check
            // mod start 2016/07/26 CSA S21_NA#8854
            if (RQST_DTL_CANCEL.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue()) || RQST_DTL_RWS_CREATE.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue()) || RQST_DTL_RWC_CANCEL.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue())
                    || RQST_DTL_RECEIVE.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue()) || RQST_DTL_SHORTAGE_CLOSED.equals(ordRtrnDtlPMsg.xxRqstTpCd.getValue())) {
            // mod end 2016/07/26 CSA S21_NA#8854
                continue;
            }

            // Save, New or Modify Mode
            checkMstrCommonDtl(pMsg, linePMsgList, ordRtrnDtlPMsg, idx);
        }
    }

    /**
     * check Common Master for Detail.
     * @param pMsg NWZC153001PMsg
     * @param linePMsgList List<NWZC153002PMsg>
     * @param ordRtrnDtlPMsg NWZC153001_ordRtrnDtlListPMsg
     * @param idx int
     */
    private void checkMstrCommonDtl(NWZC153001PMsg pMsg, final List<NWZC153002PMsg> linePMsgList, NWZC153001_ordRtrnDtlListPMsg ordRtrnDtlPMsg, int idx) {
        // CPO Order Type
        if (!existsCpoOrdTp(pMsg.glblCmpyCd.getValue(), ordRtrnDtlPMsg.cpoOrdTpCd.getValue())) {
            setErrMsgIdForCpoDtl(NWZM0124E, linePMsgList, idx);
        }
        // Merchandise Code
        if (!existsMdseCd(pMsg.glblCmpyCd.getValue(), ordRtrnDtlPMsg.mdseCd.getValue())) {
            setErrMsgIdForCpoDtl(NWZM0036E, linePMsgList, idx);
        }
        // 2016/02/18 S21_NA#2336 Del Start
        // Original Merchandise Code
//        if (!existsOrigMdseCd(pMsg.glblCmpyCd.getValue(), ordRtrnDtlPMsg.origMdseCd.getValue())) {
//            setErrMsgIdForCpoDtl(NWZM0125E, linePMsgList, idx);
//        }
        // 2016/02/18 S21_NA#2336 Del End
        // Ship To Customer
        if (!existsShipToCust(pMsg.glblCmpyCd.getValue(), ordRtrnDtlPMsg.shipToCustCd.getValue())) {
            setErrMsgIdForCpoDtl(NWZM0023E, linePMsgList, idx);
        }
        // Ship To Country Code
        if (!existsShipToCtryCd(pMsg.glblCmpyCd.getValue(), ordRtrnDtlPMsg.shipToCtryCd.getValue())) {
            setErrMsgIdForCpoDtl(NWZM0090E, linePMsgList, idx);
        }
        // Ship To State Code
        if (!existsShipToStCd(pMsg.glblCmpyCd.getValue(), ordRtrnDtlPMsg.shipToStCd.getValue())) {
            setErrMsgIdForCpoDtl(NWZM0088E, linePMsgList, idx);
        }
        // Currency Code
        if (!existsCcyCd(pMsg.glblCmpyCd.getValue(), ordRtrnDtlPMsg.ccyCd.getValue())) {
            setErrMsgIdForCpoDtl(NWZM0062E, linePMsgList, idx);
        }
        // Stock Status Code
        if (!existsStkStsCd(pMsg.glblCmpyCd.getValue(), ordRtrnDtlPMsg.stkStsCd.getValue())) {
            setErrMsgIdForCpoDtl(NWZM0062E, linePMsgList, idx);
        }
        // Payment Term Cash Discount Code
        if (!existsPmtTermCashDescCd(pMsg.glblCmpyCd.getValue(), ordRtrnDtlPMsg.pmtTermCashDiscCd.getValue())) {
            setErrMsgIdForCpoDtl(NWZM0062E, linePMsgList, idx);
        }
        // Salesrep or Sales Team Toc Code
        if (!existsTocCd(pMsg.glblCmpyCd.getValue(), ordRtrnDtlPMsg.slsRepOrSlsTeamTocCd.getValue())) {
            setErrMsgIdForCpoDtl(NWZM0062E, linePMsgList, idx);
        }
        // Custom UOM Code
        if (!existsCustomUomCd(pMsg.glblCmpyCd.getValue(), ordRtrnDtlPMsg.custUomCd.getValue())) {
            setErrMsgIdForCpoDtl(NWZM0062E, linePMsgList, idx);
        }
    }

    /**
     * * check Master Common Header.
     * @param pMsg NWZC153001PMsg
     * @param isModifyHdrRqst boolean
     */
    private void checkMstrCommonHdr(NWZC153001PMsg pMsg, boolean isModifyHdrRqst) {
        // CPO order Type Code
        if (!existsCpoOrdTp(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdTpCd.getValue())) {
            msgMap.addXxMsgId(NWZM0109E);
        }
        // Order Fulfill Level Code
        if (!existsOrdFuflLvlCd(pMsg.glblCmpyCd.getValue(), pMsg.ordFuflLvlCd.getValue())) {
            msgMap.addXxMsgId(NWZM0111E);
        }
        // Bill To Customer Code
        if (!existsBillToCustCd(pMsg.glblCmpyCd.getValue(), pMsg.billToCustCd.getValue())) {
            msgMap.addXxMsgId(NWZM0112E);
        }
        // Sell To Customer Code
        if (!existsSellToCustCd(pMsg.glblCmpyCd.getValue(), pMsg.sellToCustCd.getValue())) {
            msgMap.addXxMsgId(NWZM0024E);
        }
        // Ship To Customer Code
        if (!existsShipToCust(pMsg.glblCmpyCd.getValue(), pMsg.addShipToCustCd.getValue())) {
            msgMap.addXxMsgId(NWZM0114E);
        }
        // Ship To Country Code
        if (!existsShipToCtryCd(pMsg.glblCmpyCd.getValue(), pMsg.addShipToCtryCd.getValue())) {
            msgMap.addXxMsgId(NWZM0117E);
        }
        // Ship To State Code
        if (!existsShipToStCd(pMsg.glblCmpyCd.getValue(), pMsg.addShipToStCd.getValue())) {
            msgMap.addXxMsgId(NWZM0115E);
        }
        // Payment Term Cash Discount Code
        if (!existsPmtTermCashDescCd(pMsg.glblCmpyCd.getValue(), pMsg.addPmtTermCashDiscCd.getValue())) {
            msgMap.addXxMsgId(NWZM0925E);
        }

        if (isModifyHdrRqst) {
            return;
        }

        // CPO Source Type Code
        if (!existsCpoSrcTpCd(pMsg.glblCmpyCd.getValue(), pMsg.cpoSrcTpCd.getValue())) {
            msgMap.addXxMsgId(NWZM0110E);
        }
        // System Source Code
        if (!existsSysSrcCd(pMsg.glblCmpyCd.getValue(), pMsg.sysSrcCd.getValue())) {
            msgMap.addXxMsgId(NWZM0704E);
        }
    }

    /**
     * Data setting
     * @param cpoBean NWZC153001CpoBean
     * @param resPMsgList List<NWZC1530002PMsg>
     */
    private void setData(NWZC153001CpoBean cpoBean, final List<NWZC153002PMsg> linePMsgList) {
        /** Order Header **/
        // CPO Order TimeStamp
        cpoBean.setCpoOrdTs(cpoBean.getSlsDt());

        // Add Ship To XX
        if (ZYPConstant.FLG_OFF_N.equals(cpoBean.getAddDropShipFlg())) {
            setShipToCustToCpoHdr(cpoBean);
        }

        // Payer Customer Code
        if (!ZYPCommonFunc.hasValue(cpoBean.getPayerCustCd())) {
            cpoBean.setPayerCustCd(cpoBean.getBillToCustCd());
        }

        // Customer Issue PO Date
        if (ZYPCommonFunc.hasValue(cpoBean.getCustIssPoNum())) {
            if (!ZYPCommonFunc.hasValue(cpoBean.getCustIssPoDt())) {
                cpoBean.setCustIssPoDt(cpoBean.getSlsDt());
            }
        }

        // Drop Ship Flag
        if (!ZYPCommonFunc.hasValue(cpoBean.getAddDropShipFlg())) {
            cpoBean.setAddDropShipFlg(ZYPConstant.FLG_OFF_N);
        }

        // Sell To First Reference Comment Text, Sell To Second
        // Reference Comment Text
        if (!ZYPCommonFunc.hasValue(cpoBean.getSellToFirstRefCmntTxt()) || !ZYPCommonFunc.hasValue(cpoBean.getSellToScdRefCmntTxt())) {
            setSellToCustToCpoHdr(cpoBean);
        }

        /** Order Detail **/
        int parentIdx = 0;
        NWZC153001DetailBean cpoDtlBean = null;
        String prntDtlLineNum = "";
        for (int dtlIdx = 0, end = cpoBean.getDtlBeanList().size(); dtlIdx < end; dtlIdx++) {
            cpoDtlBean = cpoBean.getDtlBeanList().get(dtlIdx);

            // Copy Parent Detail to Child Detail
            if (PARENT_LINE_SUB_NUM.equals(cpoDtlBean.getDsCpoRtrnLineSubNum())) {
                parentIdx = dtlIdx;
                prntDtlLineNum = cpoDtlBean.getDsCpoRtrnLineNum();
            } else {
                if (prntDtlLineNum.equals(cpoDtlBean.getDsCpoRtrnLineNum())) {
                    setParentDtlToChildDtl(cpoBean, cpoDtlBean, parentIdx);
                    continue;
                } else {
                    prntDtlLineNum = "";
                }
            }

            // CPO Order Type Code
            if (!ZYPCommonFunc.hasValue(cpoDtlBean.getCpoOrdTpCd())) {
                cpoDtlBean.setCpoOrdTpCd(cpoBean.getCpoOrdTpCd());
            }

            // Merchandise
            setMdseToCpoDtl(cpoDtlBean);

            // Request Pickup Date
            if (!ZYPCommonFunc.hasValue(cpoDtlBean.getRqstPickUpDt())) {
                cpoDtlBean.setRqstPickUpDt(cpoBean.getAddRddDt());
            }

            // Stock Status Code
            if (!ZYPCommonFunc.hasValue(cpoDtlBean.getStkStsCd())) {
                cpoDtlBean.setStkStsCd(STK_STS.GOOD);
            }

            //QC#18673(L3) ADD START
            // 2016/02/18 QC#55364 Mod Start
            //setStkStsByThirdPtyDspTp(cpoDtlBean);
            if (!DS_ORD_LINE_CATG.LOAN_RETURNS.equals(cpoDtlBean.getDsOrdLineCatgCd())) {
                setStkStsByThirdPtyDspTp(cpoDtlBean);
            }
            // 2016/02/18 QC#55364 Mod End
            //QC#18673(L3) ADD END

            // Customer UOM Code
            if (!ZYPCommonFunc.hasValue(cpoDtlBean.getCustUomCd())) {
                cpoDtlBean.setCustUomCd(PKG_UOM.EACH);
            }

            // Tax Flag
            if (!ZYPCommonFunc.hasValue(cpoDtlBean.getTaxFlg())) {
                cpoDtlBean.setTaxFlg(ZYPConstant.FLG_ON_Y);
            }

            // Price Timing Code
            cpoDtlBean.setPrcTmgCd(PRC_COND.ORDER_TIME);

            // Exchange Rate
            cpoDtlBean.setExchRate(getExchangeRate(cpoBean.getGlblCmpyCd(), cpoDtlBean.getCcyCd(), cpoBean.getSlsDt()));

            // Permenant Term Cash Discount Code
            if (!ZYPCommonFunc.hasValue(cpoDtlBean.getPmtTermCashDiscCd())) {
                cpoDtlBean.setPmtTermCashDiscCd(cpoBean.getAddPmtTermCashDiscCd());
            }

            // Term Code
            setTermCd(cpoDtlBean);

            // Cancel Quantity
            if (RQST_DTL_CANCEL.equals(cpoDtlBean.getXxRqstTpCd())) {
                setCancelOrdQty(cpoBean, cpoDtlBean);
            }
        }
    }

    /**
     * set ShipToCust To Cpo Header
     * @param cpoBean NWZC153001CpoBean
     */
    private void setShipToCustToCpoHdr(NWZC153001CpoBean cpoBean) {
        SHIP_TO_CUSTTMsgArray shipToResult = (SHIP_TO_CUSTTMsgArray) getShipToTMsg(cpoBean.getGlblCmpyCd(), cpoBean.getAddShipToCustCd(), null);
        if (shipToResult.getValidCount() < 1) {
            return;
        }
        cpoBean.setAddShipToLocNm(shipToResult.no(0).locNm.getValue());
        cpoBean.setAddShipToAddlLocNm(shipToResult.no(0).addlLocNm.getValue());
        cpoBean.setAddShipToFirstLineAddr(shipToResult.no(0).firstLineAddr.getValue());
        cpoBean.setAddShipToScdLineAddr(shipToResult.no(0).scdLineAddr.getValue());
        cpoBean.setAddShipToThirdLineAddr(shipToResult.no(0).thirdLineAddr.getValue());
        cpoBean.setAddShipToFrthLineAddr(shipToResult.no(0).frthLineAddr.getValue());
        cpoBean.setAddShipToCtyAddr(shipToResult.no(0).ctyAddr.getValue());
        cpoBean.setAddShipToStCd(shipToResult.no(0).stCd.getValue());
        cpoBean.setAddShipToProvNm(shipToResult.no(0).provNm.getValue());
        cpoBean.setAddShipToPostCd(shipToResult.no(0).postCd.getValue());
        cpoBean.setAddShipToCtryCd(shipToResult.no(0).ctryCd.getValue());
        cpoBean.setAddShipTo01RefCmntTxt(shipToResult.no(0).firstRefCmntTxt.getValue());
        cpoBean.setAddShipTo02RefCmntTxt(shipToResult.no(0).scdRefCmntTxt.getValue());

        if (ZYPCommonFunc.hasValue(shipToResult.no(0).cntyPk)) {
            CNTYTMsg cntyResult = (CNTYTMsg) getCntyTMsg(cpoBean.getGlblCmpyCd(), shipToResult.no(0).cntyPk.getValue());

            if (cntyResult != null) {
                cpoBean.setAddShipToCntyNm(cntyResult.cntyNm.getValue());
            }
        }
    }

    /**
     * set Sell To Customer To CPO Header
     * @param cpoBean NWZC153001CpoBean
     */
    private void setSellToCustToCpoHdr(NWZC153001CpoBean cpoBean) {
        SELL_TO_CUSTTMsgArray sellToResult = (SELL_TO_CUSTTMsgArray) getSellToCustTMsg(cpoBean.getGlblCmpyCd(), cpoBean.getSellToCustCd(), null);
        if (sellToResult.getValidCount() < 1) {
            return;
        }

        if (!ZYPCommonFunc.hasValue(cpoBean.getSellToFirstRefCmntTxt())) {
            cpoBean.setSellToFirstRefCmntTxt(sellToResult.no(0).firstRefCmntTxt.getValue());
        }
        if (!ZYPCommonFunc.hasValue(cpoBean.getSellToScdRefCmntTxt())) {
            cpoBean.setSellToScdRefCmntTxt(sellToResult.no(0).scdRefCmntTxt.getValue());
        }
    }

    /**
     * set Merchandise To Cpo Detail
     * @param cpoDtlBean NWZC153001DetailBean
     */
    private void setMdseToCpoDtl(NWZC153001DetailBean cpoDtlBean) {
//        // Merchandise Code
//        ORD_TAKE_MDSETMsg ordTakeMdseTmsg = (ORD_TAKE_MDSETMsg) getOrdTakeMdseTMsg(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getMdseCd());
//        if (ordTakeMdseTmsg != null) {
//            cpoDtlBean.setMdseCd(ordTakeMdseTmsg.mdseCd.getValue());
//        }
//
//        // Original Merchandise Code
//        if (!ZYPCommonFunc.hasValue(cpoDtlBean.getOrigMdseCd())) {
//            cpoDtlBean.setOrigMdseCd(cpoDtlBean.getMdseCd());
//        }

        // Merchandise Name
//        MDSETMsg mdseTmsg = (MDSETMsg) getMdseTMsg(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getMdseCd());
//        if (mdseTmsg != null) {
//            cpoDtlBean.setMdseNm(mdseTmsg.mdseNm.getValue());
//        }
//
//        // Original Merchandise Name
//        if (!ZYPCommonFunc.hasValue(cpoDtlBean.getOrigMdseCd())) {
//            if (mdseTmsg != null) {
//                cpoDtlBean.setOrigMdseNm(mdseTmsg.mdseNm.getValue());
//            }
//        } else {
//            MDSETMsg mdseTmsgByOrigMdseCd = (MDSETMsg) getMdseTMsg(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getOrigMdseCd());
//            if (mdseTmsgByOrigMdseCd != null) {
//                cpoDtlBean.setOrigMdseNm(mdseTmsgByOrigMdseCd.mdseNm.getValue());
//            }
//        }

        String origMdseCd = cpoDtlBean.getMdseCd();
        MDSETMsg mdseTmsg = NWXMdseTMsgThreadLocalCache.getInstance().get(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getMdseCd());
        if (mdseTmsg != null) {
            cpoDtlBean.setMdseCd(mdseTmsg.mdseCd.getValue());
            cpoDtlBean.setMdseNm(mdseTmsg.mdseNm.getValue());
            // 2018/04/20 S221_NA#23708 Add Start
            if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.instlBaseCtrlFlg.getValue())) {
                cpoDtlBean.setSvcConfigMstrPk(null);
            }
            // 2018/04/20 S221_NA#23708 Add End
            cpoDtlBean.setInvtyCtrlFlg(mdseTmsg.invtyCtrlFlg.getValue()); // 2018/11/20 S21_NA#27299 ADd
            if (!S21StringUtil.isEquals(origMdseCd, mdseTmsg.mdseCd.getValue())) {
                cpoDtlBean.setOrigMdseCd(origMdseCd);
                MDSETMsg mdseTmsgByOrigMdseCd = (MDSETMsg) getMdseTMsg(cpoDtlBean.getGlblCmpyCd(), origMdseCd);
                if (null == mdseTmsgByOrigMdseCd) {
                    cpoDtlBean.setOrigMdseNm(mdseTmsg.mdseNm.getValue());
                } else {
                    cpoDtlBean.setOrigMdseNm(mdseTmsgByOrigMdseCd.mdseNm.getValue());
                }
            }
        }
    }

    /**
     * getExchangeRate
     * @param glblCmpyCd String
     * @param ccyCd String
     * @param slsDt String
     * @return BigDecimal
     */
    private BigDecimal getExchangeRate(String glblCmpyCd, String ccyCd, String slsDt) {
        // cache
        final StringBuilder cacheKeySb = new StringBuilder();
        cacheKeySb.append(glblCmpyCd).append(",");
        cacheKeySb.append(ccyCd).append(",");
        cacheKeySb.append(slsDt);

        final String cacheKey = cacheKeySb.toString();

        BigDecimal exchangeRate = localCache.exchangeRateCache.get(cacheKey);
        if (exchangeRate == null) {
            NWXC001001RateData rateData = NWXC001001Exchange.getRate(glblCmpyCd, ccyCd, slsDt);
            if (rateData != null) {
                exchangeRate = rateData.getActlExchRate();
                localCache.exchangeRateCache.put(cacheKey, exchangeRate);
            }
        }
        return exchangeRate;
    }

    /**
     * set Term Code for PMT_TERM_CASH_DISC
     * @param cpoDtlBean NWZC153001DetailBean
     */
    private void setTermCd(NWZC153001DetailBean cpoDtlBean) {
        PMT_TERM_CASH_DISCTMsg pmtTermTmsg = (PMT_TERM_CASH_DISCTMsg) getPmtTermCashDiscTMsg(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getPmtTermCashDiscCd());

        if (pmtTermTmsg != null) {
            cpoDtlBean.setPmtTermCd(pmtTermTmsg.pmtTermCd.getValue());
            cpoDtlBean.setCashDiscTermCd(pmtTermTmsg.cashDiscTermCd.getValue());
        }
    }

    /**
     * set Parent Detail To Child Detail
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     * @param parentIdx int
     */
    private void setParentDtlToChildDtl(NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean, int parentIdx) {

        // Merchandise
        setMdseToCpoDtl(cpoDtlBean); // S21_NA#4374

        cpoDtlBean.setPmtTermCd(cpoBean.getDtlBeanList().get(parentIdx).getPmtTermCd()); // S21_NA#4374
        cpoDtlBean.setPmtTermCashDiscCd(cpoBean.getDtlBeanList().get(parentIdx).getPmtTermCashDiscCd());
        cpoDtlBean.setCashDiscTermCd(cpoBean.getDtlBeanList().get(parentIdx).getCashDiscTermCd());
        cpoDtlBean.setCcyCd(cpoBean.getDtlBeanList().get(parentIdx).getCcyCd());
        cpoDtlBean.setTaxFlg(cpoBean.getDtlBeanList().get(parentIdx).getTaxFlg());
        cpoDtlBean.setCpoOrdTpCd(cpoBean.getDtlBeanList().get(parentIdx).getCpoOrdTpCd());
        cpoDtlBean.setPrcTmgCd(cpoBean.getDtlBeanList().get(parentIdx).getPrcTmgCd());
        cpoDtlBean.setManPrcFlg(cpoBean.getDtlBeanList().get(parentIdx).getManPrcFlg());
        cpoDtlBean.setSetMdseCd(cpoBean.getDtlBeanList().get(parentIdx).getMdseCd());
        cpoDtlBean.setInvtyLocCd(cpoBean.getDtlBeanList().get(parentIdx).getInvtyLocCd());
        cpoDtlBean.setRqstPickUpDt(cpoBean.getDtlBeanList().get(parentIdx).getRqstPickUpDt());
        cpoDtlBean.setExchRate(cpoBean.getDtlBeanList().get(parentIdx).getExchRate());
        cpoDtlBean.setDsOrdLineCatgCd(cpoBean.getDtlBeanList().get(parentIdx).getDsOrdLineCatgCd());
        cpoDtlBean.setOrdLineSrcCd(cpoBean.getDtlBeanList().get(parentIdx).getOrdLineSrcCd());
        cpoDtlBean.setRtlWhCd(cpoBean.getDtlBeanList().get(parentIdx).getRtlWhCd());
        cpoDtlBean.setRtlSwhCd(cpoBean.getDtlBeanList().get(parentIdx).getRtlSwhCd());
        cpoDtlBean.setPrcBaseDt(cpoBean.getDtlBeanList().get(parentIdx).getPrcBaseDt());
        cpoDtlBean.setPrcCatgCd(cpoBean.getDtlBeanList().get(parentIdx).getPrcCatgCd());
        cpoDtlBean.setFlPrcListCd(cpoBean.getDtlBeanList().get(parentIdx).getFlPrcListCd());
        cpoDtlBean.setSlsRepOrSlsTeamTocCd(cpoBean.getDtlBeanList().get(parentIdx).getSlsRepOrSlsTeamTocCd());
        cpoDtlBean.setCrRebilCd(cpoBean.getDtlBeanList().get(parentIdx).getCrRebilCd());
        cpoDtlBean.setOrigCpoOrdNum(cpoBean.getDtlBeanList().get(parentIdx).getOrigCpoOrdNum());
//        cpoDtlBean.setOrigCpoDtlLineNum(cpoBean.getDtlBeanList().get(parentIdx).getOrigCpoDtlLineNum()); // S21_NA#7606 // Delete 2016/06/02
//        cpoDtlBean.setOrigCpoDtlLineSubNum(cpoBean.getDtlBeanList().get(parentIdx).getOrigCpoDtlLineSubNum()); // S21_NA#7606 // Delete 2016/06/02
        cpoDtlBean.setOrigInvNum(cpoBean.getDtlBeanList().get(parentIdx).getOrigInvNum());
        cpoDtlBean.setDplyLineRefNum(cpoBean.getDtlBeanList().get(parentIdx).getDplyLineRefNum());
        cpoDtlBean.setCsmpContrNum(cpoBean.getDtlBeanList().get(parentIdx).getCsmpContrNum());
        cpoDtlBean.setDlrRefNum(cpoBean.getDtlBeanList().get(parentIdx).getDlrRefNum());
        cpoDtlBean.setCsmpPrcListCd(cpoBean.getDtlBeanList().get(parentIdx).getCsmpPrcListCd());
        cpoDtlBean.setHddRmvCd(cpoBean.getDtlBeanList().get(parentIdx).getHddRmvCd());
        cpoDtlBean.setRtrnRsnCd(cpoBean.getDtlBeanList().get(parentIdx).getRtrnRsnCd());
        cpoDtlBean.setTrxCd(cpoBean.getDtlBeanList().get(parentIdx).getTrxCd());
        cpoDtlBean.setTrxRsnCd(cpoBean.getDtlBeanList().get(parentIdx).getTrxRsnCd());
        cpoDtlBean.setThirdPtyDspTpCd(cpoBean.getDtlBeanList().get(parentIdx).getThirdPtyDspTpCd());
    }

    /**
     * set Cancel Order Quantity
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     */
    private void setCancelOrdQty(NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean) {
        DS_CPO_RTRN_DTLTMsg rtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) getDsCpoRtrnDtlTMsg(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum(), cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum());

        if (rtrnDtlTMsg != null) {
            cpoDtlBean.setOrdQty(rtrnDtlTMsg.ordQty.getValue());
            cpoDtlBean.setOrdCustUomQty(rtrnDtlTMsg.ordCustUomQty.getValue());
        }
    }

    /**
     * checkRelation
     * @param pMsg NWZC153001PMsg
     * @param cpoBean NWZC153001CpoBean
     * @param linePMsgList final List<NWZC153002PMsg>
     * @param isSaveHdrRqst boolean
     * @param isNewHdrRqst boolean
     * @param isUpdStsHdrRqst boolean
     */
    private void checkRelation(NWZC153001PMsg pMsg, NWZC153001CpoBean cpoBean, final List<NWZC153002PMsg> linePMsgList, boolean isSaveHdrRqst, boolean isNewHdrRqst, boolean isUpdStsHdrRqst) {
        for (int dtlIdx = 0; dtlIdx < cpoBean.getDtlBeanList().size(); dtlIdx++) {
            NWZC153001DetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(dtlIdx);

            // Check Receive Quantity
            if (isUpdStsHdrRqst) {
                checkReceiveQty(cpoDtlBean, linePMsgList, dtlIdx);
                continue;
            }

            // Check Merchandise Allowance for RMA
            // 2017/12/01 S21_NA#22343 Mod Start
            //checkMdseRmaAllow(cpoDtlBean, linePMsgList, dtlIdx);
            if (!isSaveHdrRqst) {
                checkMdseRmaAllow(cpoDtlBean, linePMsgList, dtlIdx);
            }
            // 2017/12/01 S21_NA#22343 Mod End

            // Check Combinations of Merchandise and UOM
            checkCombiMdseUom(cpoDtlBean, linePMsgList, dtlIdx);

            // Check Sell Hold of Sold To Account
            checkReadyForOrdSellToCust(cpoBean.getGlblCmpyCd(), cpoBean.getSellToCustCd(), pMsg);

            if (isNewHdrRqst || isSaveHdrRqst) {
                continue;
            }

            // Check Amount Change
            checkChngOfQty(cpoDtlBean, linePMsgList, dtlIdx);
        }
    }

    /**
     * checkReceiveQty
     * @param cpoDtlBean NWZC153001DetailBean
     * @param linePMsgList final List<NWZC153002PMsg>
     * @param dtlIdx int
     */
    private void checkReceiveQty(NWZC153001DetailBean cpoDtlBean, final List<NWZC153002PMsg> linePMsgList, int dtlIdx) {
        DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) getDsCpoRtrnDtlTMsg(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getCpoOrdNum(), cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum());

        if (dsCpoRtrnDtlTMsg == null || !ZYPCommonFunc.hasValue(dsCpoRtrnDtlTMsg.ordQty.getValue())) {
            if (ZYPCommonFunc.hasValue(cpoDtlBean.getOrdQty())) {
                setErrMsgIdForCpoDtl(NWZM1501E, linePMsgList, dtlIdx);
            }
            return;
        }

        if (!ZYPCommonFunc.hasValue(cpoDtlBean.getOrdQty())) {
            return;
        }

        // set error if entered quantity is over registered Quantity
        if (dsCpoRtrnDtlTMsg.ordQty.getValue().compareTo(cpoDtlBean.getOrdQty()) < 0) {
            setErrMsgIdForCpoDtl(NWZM1501E, linePMsgList, dtlIdx);
            return;
        }

        // set error if sum of return quantity is over registered
        // Quantity
        BigDecimal totalRtrnQty = add(dsCpoRtrnDtlTMsg.rtrnQty.getValue(), cpoDtlBean.getRtrnQty());
        if (dsCpoRtrnDtlTMsg.ordQty.getValue().compareTo(totalRtrnQty) < 0) {
            setErrMsgIdForCpoDtl(NWZM1501E, linePMsgList, dtlIdx);
        }
    }

    /**
     * check Merchandise RMA Allowance
     * @param cpoDtlBean NWZC153001DetailBean
     * @param linePMsgList List<NWZC153002PMsg>
     * @param dtlIdx int
     */
    private void checkMdseRmaAllow(NWZC153001DetailBean cpoDtlBean, List<NWZC153002PMsg> linePMsgList, int dtlIdx) {
        MDSETMsg mdseTMsg = (MDSETMsg) getMdseTMsg(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getMdseCd());

        if (mdseTMsg != null) {
            if (!RMA_REQ_TP.RMA_REQUIRED.equals(mdseTMsg.rmaReqTpCd.getValue())) {
                setErrMsgIdForCpoDtl(NWZM1507E, linePMsgList, dtlIdx);
            }
        }
    }

    /**
     * check Combinations of Merchandise code and UOM
     * @param cpoDtlBean NWZC153001DetailBean
     * @param linePMsgList List<NWZC153002PMsg>
     * @param dtlIdx int
     */
    private void checkCombiMdseUom(NWZC153001DetailBean cpoDtlBean, List<NWZC153002PMsg> linePMsgList, int dtlIdx) {
        MDSE_STORE_PKGTMsgArray mdseStorePkgTMsgArray = getMdseStorPkgTMsgArray(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getCustUomCd(), cpoDtlBean.getMdseCd(), APVL_STS.SUBMITTED);

        BigDecimal remainder = ZERO;
        if (mdseStorePkgTMsgArray.getValidCount() > 0) {
            remainder = cpoDtlBean.getOrdQty().remainder(mdseStorePkgTMsgArray.no(0).inEachQty.getValue());
        }

        if (remainder.compareTo(ZERO) != 0) {
            setErrMsgIdForCpoDtl(NWZM0161E, linePMsgList, dtlIdx);
        }
    }

    /**
     * check whether Sell To Customer are Ready for Order taking
     * @param glblCmpyCd String
     * @param sellToCustCd String
     * @param pMsg NWZC153001PMsg
     */
    private void checkReadyForOrdSellToCust(String glblCmpyCd, String sellToCustCd, NWZC153001PMsg pMsg) {
        SELL_TO_CUSTTMsgArray sellToCustTmsg = (SELL_TO_CUSTTMsgArray) getSellToCustTMsg(glblCmpyCd, sellToCustCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        if (sellToCustTmsg.getValidCount() < 1) {
            msgMap.addXxMsgId(NWZM0024E);
        }
    }

    /**
     * check Change of quantity
     * @param glblCmpyCd String
     * @param sellToCustCd String
     * @param pMsg NWZC153001PMsg
     */
    private void checkChngOfQty(NWZC153001DetailBean cpoDtlBean, List<NWZC153002PMsg> linePMsgList, int dtlIdx) {
        DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) getDsCpoRtrnDtlTMsg(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getCpoOrdNum(), cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum());

        if (dsCpoRtrnDtlTMsg == null) {
            return;
        }

        if (ZYPCommonFunc.hasValue(dsCpoRtrnDtlTMsg.rtrnQty) && ZYPCommonFunc.hasValue(cpoDtlBean.getOrdQty())) {
            BigDecimal ordQty = cpoDtlBean.getOrdQty(); // 2015/01/07 S21_NA#2592 Add
            BigDecimal rtrnQty = dsCpoRtrnDtlTMsg.rtrnQty.getValue(); // 2015/01/07 S21_NA#2592 Add
            ordQty = ordQty.abs();
            rtrnQty = rtrnQty.abs();
//            if (dsCpoRtrnDtlTMsg.rtrnQty.getValue().compareTo(cpoDtlBean.getOrdQty()) > 0) {// 2015/01/07 S21_NA#2592 Mod Condition
            if (rtrnQty.compareTo(ordQty) > 0) {
                setErrMsgIdForCpoDtl(NWZM1500E, linePMsgList, dtlIdx);
            }
        }
    }

    /**
     * edit Amount
     * @param pMsg NWZC153001PMsg
     * @param cpoBean NWZC153001CpoBean
     * @param linePMsgList List<NWZC153002PMsg>
     */
    private void editAmount(NWZC153001PMsg pMsg, NWZC153001CpoBean cpoBean, List<NWZC153002PMsg> linePMsgList) {

        // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
        // set Initial Amount
//        initializeAmount(cpoBean);
//        setAmountFromCpoDtl(cpoBean);
        // setAmountFromCpoRtrnDtl(cpoBean); 2016/01/07 S21_NA#2588 Del
        // 2017/04/07 S21_NA#Review structure Lv.2 Del End

        List<NWZC153001DiscountBean> discPrcList = null;
        for (int idx = 0; idx < cpoBean.getDtlBeanList().size(); idx++) {
            NWZC153001DetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(idx);

            BigDecimal entDealGrsUnitPrcAmt = BigDecimal.ZERO; // 2015/12/17 S21_NA#2007 Add
            BigDecimal entDealNetUnitPrcAmt = BigDecimal.ZERO; // 2015/12/17 S21_NA#2007 Add
            BigDecimal entCpoDtlDealNetAmt = BigDecimal.ZERO; 
            BigDecimal entCpoDtlDealSlsAmt = BigDecimal.ZERO; 
            for (int prcIdx = 0; prcIdx < cpoBean.getPrcCalcList().size(); prcIdx++) {
                NWZC153001PrcCalcBean prcCalcList = cpoBean.getPrcCalcList().get(prcIdx);

                if (!(prcCalcList.getDsCpoRtrnLineNum().equals(cpoDtlBean.getDsCpoRtrnLineNum()) && prcCalcList.getDsCpoRtrnLineSubNum().equals(cpoDtlBean.getDsCpoRtrnLineSubNum()))) {
                    continue;
                }

                // set Deal Gross Unit Price Amount
                if (PRC_DTL_GRP.BASE_PRICE.equals(prcCalcList.getPrcDtlGrpCd()) && ZYPConstant.FLG_OFF_N.equals(prcCalcList.getPrcCondManDelFlg())) {
                    // BigDecimal entDealGrsUnitPrcAmt = add(cpoDtlBean.getEntDealGrsUnitPrcAmt(), prcCalcList.getUnitPrcAmt()); 2015/12/17 S21_NA#2007 Del
                    entDealGrsUnitPrcAmt = add(entDealGrsUnitPrcAmt, prcCalcList.getUnitPrcAmt()); // 2015/12/17 S21_NA#2007 Add
                    entDealNetUnitPrcAmt = add(entDealNetUnitPrcAmt, prcCalcList.getUnitPrcAmt()); // 2015/12/17 S21_NA#2007 Add
                    // cpoDtlBean.setEntDealGrsUnitPrcAmt(entDealGrsUnitPrcAmt); 2015/12/17 S21_NA#2007 Del
                    // QC#9694 2016/08/01 Mod Start
                    entCpoDtlDealNetAmt = add(entCpoDtlDealNetAmt, prcCalcList.getCalcPrcAmtRate());
                    entCpoDtlDealSlsAmt = add(entCpoDtlDealSlsAmt, prcCalcList.getCalcPrcAmtRate());
                    // QC#9694 2016/08/01 Mod End
                }

                // set Deal Net Unit Price Amount
                if (PRC_DTL_GRP.DISCOUNT.equals(prcCalcList.getPrcDtlGrpCd()) && ZYPConstant.FLG_OFF_N.equals(prcCalcList.getPrcCondManDelFlg())) {
                    // BigDecimal entDealNetUnitPrcAmt = subtract(cpoDtlBean.getEntDealGrsUnitPrcAmt(), prcCalcList.getUnitPrcAmt()); 2015/12/17 S21_NA#2007 Del
                    // cpoDtlBean.setEntDealNetUnitPrcAmt(entDealNetUnitPrcAmt); 2015/12/17 S21_NA#2007 Del
                    entDealNetUnitPrcAmt = subtract(entDealNetUnitPrcAmt, prcCalcList.getUnitPrcAmt()); // 2015/12/17 S21_NA#2007
                    // QC#9694 2016/08/01 Add Start
                    entCpoDtlDealNetAmt = subtract(entCpoDtlDealNetAmt, prcCalcList.getCalcPrcAmtRate());
                    // QC#9694 2016/08/01 Add End
                }
                // QC#9694 2016/08/01 Add Start
                if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(prcCalcList.getPrcDtlGrpCd()) && ZYPConstant.FLG_OFF_N.equals(prcCalcList.getPrcCondManDelFlg())) {
                    entCpoDtlDealNetAmt = subtract(entCpoDtlDealNetAmt, prcCalcList.getCalcPrcAmtRate());
                }
                // QC#9694 2016/08/01 Add End
            }
            cpoDtlBean.setEntDealGrsUnitPrcAmt(entDealGrsUnitPrcAmt); // 2015/12/17 S21_NA#2007
            cpoDtlBean.setEntDealNetUnitPrcAmt(entDealNetUnitPrcAmt); // 2015/12/17 S21_NA#2007
            cpoDtlBean.setEntCpoDtlDealNetAmt(entCpoDtlDealNetAmt);   // QC#9694 2016/08/01
            cpoDtlBean.setEntCpoDtlDealSlsAmt(entCpoDtlDealSlsAmt);;  // QC#9694 2016/08/01

            // call Edit price Amount Information
            NWXC001001UnitPriceData unitPrcData = callNWXC001001EditPriceAmount(cpoBean, cpoDtlBean, linePMsgList, idx);

            if (hasError(linePMsgList)) {
                return;
            }

            NWXC001001UnitPriceData unitPrcDataForAmt = callNWXC001001EditPriceAmountForAmt(cpoBean, cpoDtlBean, linePMsgList, idx);

            if (hasError(linePMsgList)) {
                return;
            }

            setUnitPrcAmt(cpoBean, cpoBean.getDtlBeanList().get(idx), unitPrcData);

            // set each price amount for return
            setPrcAmtForRtrnDtl(cpoDtlBean, unitPrcDataForAmt);

            // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//            // calculate price amount to CPO
//            // setPrcAmtForCPO(cpoBean, cpoDtlBean);  2015/12/17 S21_NA#2007 Del
//            // 2015/12/17 S21_NA#2007 Add Start
//            if (!isCancelDtl(cpoDtlBean)) {
//                setPrcAmtForCPO(cpoBean, cpoDtlBean);
//            }
//            // 2015/12/17 S21_NA#2007 Add End
            // 2017/04/07 S21_NA#Review structure Lv.2 Del End

            // get DiscountPrice (dealPerUnitFixAmt,
            // dealPrmoNetUnitPrcAmt and dealSalesPercentNum)
            discPrcList = getDiscountPrcAmt(cpoBean, cpoDtlBean, cpoDtlBean.getEntDealGrsUnitPrcAmt());
            cpoDtlBean.setDiscPrcList(discPrcList);

            // get FuncPrice and SetComponentPrice For Discount
            for (NWZC153001DiscountBean discPrcData : discPrcList) {
                // QC#12481 2016/10/26 Mod Start
                //NWXC001001UnitPriceData prmoNetUnitPrcAmtData = callNWXC001001EditPriceAmount(cpoBean, cpoDtlBean, linePMsgList, idx);
                //  get dealPerUnitFixAmt (FuncPrice and SetComponentPrice)

                //if (hasError(linePMsgList)) {
                //    return;
                //}

                BigDecimal dealPerUnitFixAmt = discPrcData.getDealPerUnitFixAmt();
                NWXC001001UnitPriceData perUnitFixAmtData = callNWXC001001EditPriceAmount(cpoBean, cpoDtlBean, dealPerUnitFixAmt, dealPerUnitFixAmt, linePMsgList, idx);
                if (hasError(linePMsgList)) {
                    return;
                }

                //  get dealPrmoNetUnitPrcAmt (FuncPrice and SetComponentPrice)
                BigDecimal dealPrmoNetUnitPrcAmt = discPrcData.getDealPrmoNetUnitPrcAmt();
                NWXC001001UnitPriceData prmoNetUnitPrcAmtData = callNWXC001001EditPriceAmount(cpoBean, cpoDtlBean, dealPrmoNetUnitPrcAmt, dealPrmoNetUnitPrcAmt, linePMsgList, idx);
                if (hasError(linePMsgList)) {
                    return;
                }

                // map to PRC_DTL
                //calcPrcDtlPriceAmount(discPrcData, unitPrcData, prmoNetUnitPrcAmtData, linePMsgList, idx);
                calcPrcDtlPriceAmount(discPrcData, perUnitFixAmtData, prmoNetUnitPrcAmtData, linePMsgList, idx);
                if (hasError(linePMsgList)) {
                    return;
                }
                // QC#12481 2016/10/26 Mod End
            }
        }

        // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
        // Calculate Discount Amount
//        calcCpoDiscAmt(cpoBean);
        // 2017/04/07 S21_NA#Review structure Lv.2 Del End
    }

    /**
     * set Unit Price Amount
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     * @param unitPrcData NWXC001001UnitPriceData
     */
    private void setUnitPrcAmt(NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean, NWXC001001UnitPriceData unitPrcData) {
        cpoDtlBean.setFuncGrsUnitPrcAmt(unitPrcData.getFuncGrsUnitPrcAmt());
        cpoDtlBean.setEntFuncGrsUnitPrcAmt(unitPrcData.getFuncGrsUnitPrcAmt());
        cpoDtlBean.setEntFuncNetUnitPrcAmt(unitPrcData.getFuncNetUnitPrcAmt());

        cpoDtlBean.setDealGrsUnitPrcAmt(unitPrcData.getDealGrsUnitPrcAmt());
        cpoDtlBean.setEntDealGrsUnitPrcAmt(unitPrcData.getDealGrsUnitPrcAmt());
        cpoDtlBean.setEntDealNetUnitPrcAmt(unitPrcData.getDealNetUnitPrcAmt());

//        setPrcAmtForRtrnDtl(cpoDtlBean);
//        if (!RQST_DTL_CANCEL.equals(cpoDtlBean.getXxRqstTpCd())) { 2015/12/18 S21_NA#2007 Del Start
//            setPrcAmtForCPO(cpoBean, cpoDtlBean);
//        } 2015/12/18 S21_NA#2007 Del End
    }

    /**
     * set Discount Price Amount
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     * @param dealGrsUnitPrcAmt BigDecimal
     * @return List<NWZC153001DiscountBean>
     */
    private List<NWZC153001DiscountBean> getDiscountPrcAmt(NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean, BigDecimal dealGrsUnitPrcAmt) {
        List<NWZC153001DiscountBean> discPrcList = new ArrayList<NWZC153001DiscountBean>();
        BigDecimal dealPrmoNetUnitPrcAmt = dealGrsUnitPrcAmt;

        // 2018/11/05 QC#29040 Add Start
        String dsCpoRtrnLineNum = cpoDtlBean.getDsCpoRtrnLineNum();
        String dsCpoRtrnLineSubNum = cpoDtlBean.getDsCpoRtrnLineSubNum();
        // 2018/11/05 QC#29040 Add End

        int index = 0;
        for (int i = 0; i < cpoBean.getPrcCalcList().size(); i++) {
            NWZC153001PrcCalcBean priceData = cpoBean.getPrcCalcList().get(i);

            //if (!PRC_DTL_GRP.DISCOUNT.equals(priceData.getPrcDtlGrpCd()) || !ZYPConstant.FLG_OFF_N.equals(priceData.getPrcCondTpCd())) {
            if (!PRC_DTL_GRP.DISCOUNT.equals(priceData.getPrcDtlGrpCd()) || !ZYPConstant.FLG_OFF_N.equals(priceData.getPrcCondManDelFlg())) {
                continue;
            }

            // 2018/11/05 QC#29040 Add Start    
            if (!(dsCpoRtrnLineNum.equals(priceData.getDsCpoRtrnLineNum()) &&  dsCpoRtrnLineSubNum.equals(priceData.getDsCpoRtrnLineSubNum()))){
                continue;
            }
            // 2018/11/05 QC#29040 Add End

            NWZC153001DiscountBean discPrcData = new NWZC153001DiscountBean();
            index = index + 1;
            discPrcData.setCpoLinePrcNum(BigDecimal.valueOf(index));

            // UnitPrice=UnitPrice(init=Gross)-DealPerUnitFixAmount
            BigDecimal dealPerUnitFixAmt = priceData.getUnitPrcAmt();
            discPrcData.setDealPerUnitFixAmt(dealPerUnitFixAmt);

            dealPrmoNetUnitPrcAmt = subtract(dealPrmoNetUnitPrcAmt, dealPerUnitFixAmt);
            discPrcData.setDealPrmoNetUnitPrcAmt(dealPrmoNetUnitPrcAmt);

            // Promotion: return value is minus
            if (PRC_COND_UNIT.PCT.equals(priceData.getPrcCondUnitCd())) {
                if (hasValue(priceData.getManPrcAmtRate())) {
                    discPrcData.setDealSlsPctNum(priceData.getManPrcAmtRate());
                } else {
                    discPrcData.setDealSlsPctNum(priceData.getAutoPrcAmtRate());
                }
            }
            discPrcList.add(discPrcData);
        }
        return discPrcList;
    }

    /**
     * calculate Price Detail Price Amount
     * @param discPrcData NWZC153001DiscountBean
     * @param perUnitFixAmtData NWXC001001UnitPriceData
     * @param prmoNetUnitPrcAmtData NWXC001001UnitPriceData
     * @param resPMsgList List<NWZC153002PMsg>
     * @param i int
     */
    private void calcPrcDtlPriceAmount(NWZC153001DiscountBean discPrcData, NWXC001001UnitPriceData perUnitFixAmtData, NWXC001001UnitPriceData prmoNetUnitPrcAmtData, List<NWZC153002PMsg> resPMsgList, int i) {
        BigDecimal dealPerUnitFixAmt = perUnitFixAmtData.getDealGrsUnitPrcAmt();
        discPrcData.setDealPerUnitFixAmt(checkAmount(dealPerUnitFixAmt, resPMsgList, i).negate());

        BigDecimal funcPerUnitFixAmt = perUnitFixAmtData.getFuncGrsUnitPrcAmt();
        discPrcData.setFuncPerUnitFixAmt(checkAmount(funcPerUnitFixAmt, resPMsgList, i).negate());

        BigDecimal dealPrmoNetUnitPrcAmt = prmoNetUnitPrcAmtData.getDealGrsUnitPrcAmt();
        discPrcData.setDealPrmoNetUnitPrcAmt(checkAmount(dealPrmoNetUnitPrcAmt, resPMsgList, i));

        BigDecimal funcPrmoNetUnitPrcAmt = prmoNetUnitPrcAmtData.getFuncGrsUnitPrcAmt();
        discPrcData.setFuncPrmoNetUnitPrcAmt(checkAmount(funcPrmoNetUnitPrcAmt, resPMsgList, i));
    }

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * calculate Cpo Discount Amount
//     * @param cpoBean NWZC153001CpoBean
//     */
//    private void calcCpoDiscAmt(NWZC153001CpoBean cpoBean) {
//        // 2016/12/05 S21_NA#16305 Mod Start
////        BigDecimal entCpoTotDealDiscAmt = subtract(cpoBean.getEntCpoTotDealSlsAmt(), cpoBean.getEntCpoTotDealNetAmt());
//        BigDecimal entCpoTotDealDiscAmt = subtract(cpoBean.getEntCpoTotDealNetAmt(), cpoBean.getEntCpoTotDealSlsAmt());
//        // 2016/12/05 S21_NA#16305 Mod End
//        cpoBean.setEntCpoTotDealDiscAmt(entCpoTotDealDiscAmt);
//
//        // 2016/12/05 S21_NA#16305 Mod Start
////        BigDecimal entCpoTotFuncDiscAmt = subtract(cpoBean.getEntCpoTotFuncSlsAmt(), cpoBean.getEntCpoTotFuncNetAmt());
//        BigDecimal entCpoTotFuncDiscAmt = subtract(cpoBean.getEntCpoTotFuncNetAmt(), cpoBean.getEntCpoTotFuncSlsAmt());
//        // 2016/12/05 S21_NA#16305 Mod End
//        cpoBean.setEntCpoTotFuncDiscAmt(entCpoTotFuncDiscAmt);
//
//        // 2016/12/05 S21_NA#16305 Mod Start
////        BigDecimal cpoTotDealDiscAmt = subtract(cpoBean.getCpoTotDealSlsAmt(), cpoBean.getCpoTotDealNetAmt());
//        BigDecimal cpoTotDealDiscAmt = subtract(cpoBean.getCpoTotDealNetAmt(), cpoBean.getCpoTotDealSlsAmt());
//        // 2016/12/05 S21_NA#16305 Mod End
//        cpoBean.setCpoTotDealDiscAmt(cpoTotDealDiscAmt);
//
//        // 2016/12/05 S21_NA#16305 Mod Start
////        BigDecimal cpoTotFuncDiscAmt = subtract(cpoBean.getCpoTotFuncSlsAmt(), cpoBean.getCpoTotFuncNetAmt());
//        BigDecimal cpoTotFuncDiscAmt = subtract(cpoBean.getCpoTotFuncNetAmt(), cpoBean.getCpoTotFuncSlsAmt());
//        // 2016/12/05 S21_NA#16305 Mod End
//        cpoBean.setCpoTotFuncDiscAmt(cpoTotFuncDiscAmt);
//
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * initializeAmount
//     * @param cpoBean NWZC153001CpoBean
//     */
//    private void initializeAmount(NWZC153001CpoBean cpoBean) {
//        cpoBean.setEntCpoTotDealNetAmt(ZERO);
//        cpoBean.setEntCpoTotDealNetAmt(ZERO);
//        cpoBean.setEntCpoTotFuncSlsAmt(ZERO);
//        cpoBean.setEntCpoTotFuncNetAmt(ZERO);
//        cpoBean.setCpoTotDealSlsAmt(ZERO);
//        cpoBean.setCpoTotDealNetAmt(ZERO);
//        cpoBean.setCpoTotFuncSlsAmt(ZERO);
//        cpoBean.setCpoTotFuncNetAmt(ZERO);
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * set Amount From Cpo Detail
//     * @param cpoBean NWZC153001CpoBean
//     */
//    private void setAmountFromCpoDtl(NWZC153001CpoBean cpoBean) {
//        CPO_DTLTMsgArray cpoDtlTMsgArray = getCpoDtlTMsgArray(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
//
//        for (int i = 0; i < cpoDtlTMsgArray.length(); i++) {
//            CPO_DTLTMsg cpoDtlTMsg = cpoDtlTMsgArray.no(i);
//
//            if (ORD_LINE_STS.CANCELLED.equals(cpoDtlTMsg.ordLineStsCd.getValue())) {
//                continue;
//            }
//
//            BigDecimal entCpoTotDealSlsAmt = add(cpoBean.getEntCpoTotDealSlsAmt(), cpoDtlTMsg.entCpoDtlDealSlsAmt.getValue());
//            cpoBean.setEntCpoTotDealSlsAmt(entCpoTotDealSlsAmt);
//
//            BigDecimal entCpoTotDealNetAmt = add(cpoBean.getEntCpoTotDealNetAmt(), cpoDtlTMsg.entCpoDtlDealNetAmt.getValue());
//            cpoBean.setEntCpoTotDealNetAmt(entCpoTotDealNetAmt);
//
//            BigDecimal entCpoTotFuncSlsAmt = add(cpoBean.getEntCpoTotFuncSlsAmt(), cpoDtlTMsg.entCpoDtlFuncSlsAmt.getValue());
//            cpoBean.setEntCpoTotFuncSlsAmt(entCpoTotFuncSlsAmt);
//
//            BigDecimal entCpoTotFuncNetAmt = add(cpoBean.getEntCpoTotFuncNetAmt(), cpoDtlTMsg.entCpoDtlFuncNetAmt.getValue());
//            cpoBean.setEntCpoTotFuncNetAmt(entCpoTotFuncNetAmt);
//
//            BigDecimal cpoTotDealSlsAmt = add(cpoBean.getCpoTotDealSlsAmt(), cpoDtlTMsg.cpoDtlDealSlsAmt.getValue());
//            cpoBean.setCpoTotDealSlsAmt(cpoTotDealSlsAmt);
//
//            BigDecimal cpoTotDealNetAmt = add(cpoBean.getCpoTotDealNetAmt(), cpoDtlTMsg.cpoDtlDealNetAmt.getValue());
//            cpoBean.setCpoTotDealNetAmt(cpoTotDealNetAmt);
//
//            BigDecimal cpoTotFuncSlsAmt = add(cpoBean.getCpoTotFuncSlsAmt(), cpoDtlTMsg.cpoDtlFuncSlsAmt.getValue());
//            cpoBean.setCpoTotFuncSlsAmt(cpoTotFuncSlsAmt);
//
//            BigDecimal cpoTotFuncNetAmt = add(cpoBean.getCpoTotFuncNetAmt(), cpoDtlTMsg.cpoDtlFuncNetAmt.getValue());
//            cpoBean.setCpoTotFuncNetAmt(cpoTotFuncNetAmt);
//        }
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * set Amount From Cpo Return Detail
//     * @param cpoBean NWZC153001CpoBean
//     */
//    private void setAmountFromCpoRtrnDtl(NWZC153001CpoBean cpoBean) {
//        DS_CPO_RTRN_DTLTMsgArray cpoRtrnDtlTMsgArray = getDsCpoRtrnDtlTMsgArray(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
//
//        for (int i = 0; i < cpoRtrnDtlTMsgArray.length(); i++) {
//            DS_CPO_RTRN_DTLTMsg cpoRtrnDtlTMsg = cpoRtrnDtlTMsgArray.no(i);
//
//            if (RTRN_LINE_STS.CANCELLED.equals(cpoRtrnDtlTMsg.rtrnLineStsCd.getValue())) {
//                continue;
//            }
//
//            BigDecimal entCpoTotDealSlsAmt = add(cpoBean.getEntCpoTotDealSlsAmt(), cpoRtrnDtlTMsg.entCpoDtlDealSlsAmt.getValue());
//            cpoBean.setEntCpoTotDealSlsAmt(entCpoTotDealSlsAmt);
//
//            BigDecimal entCpoTotDealNetAmt = add(cpoBean.getEntCpoTotDealNetAmt(), cpoRtrnDtlTMsg.entCpoDtlDealNetAmt.getValue());
//            cpoBean.setEntCpoTotDealNetAmt(entCpoTotDealNetAmt);
//
//            BigDecimal entCpoTotFuncSlsAmt = add(cpoBean.getEntCpoTotFuncSlsAmt(), cpoRtrnDtlTMsg.entCpoDtlFuncSlsAmt.getValue());
//            cpoBean.setEntCpoTotFuncSlsAmt(entCpoTotFuncSlsAmt);
//
//            BigDecimal entCpoTotFuncNetAmt = add(cpoBean.getEntCpoTotFuncNetAmt(), cpoRtrnDtlTMsg.entCpoDtlFuncNetAmt.getValue());
//            cpoBean.setEntCpoTotFuncNetAmt(entCpoTotFuncNetAmt);
//
//            BigDecimal cpoTotDealSlsAmt = add(cpoBean.getCpoTotDealSlsAmt(), cpoRtrnDtlTMsg.cpoDtlDealSlsAmt.getValue());
//            cpoBean.setCpoTotDealSlsAmt(cpoTotDealSlsAmt);
//
//            BigDecimal cpoTotDealNetAmt = add(cpoBean.getCpoTotDealNetAmt(), cpoRtrnDtlTMsg.cpoDtlDealNetAmt.getValue());
//            cpoBean.setCpoTotDealNetAmt(cpoTotDealNetAmt);
//
//            BigDecimal cpoTotFuncSlsAmt = add(cpoBean.getCpoTotFuncSlsAmt(), cpoRtrnDtlTMsg.cpoDtlFuncSlsAmt.getValue());
//            cpoBean.setCpoTotFuncSlsAmt(cpoTotFuncSlsAmt);
//
//            BigDecimal cpoTotFuncNetAmt = add(cpoBean.getCpoTotFuncNetAmt(), cpoRtrnDtlTMsg.cpoDtlFuncNetAmt.getValue());
//            cpoBean.setCpoTotFuncNetAmt(cpoTotFuncNetAmt);
//        }
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    /**
     * set Price Amount For Return Detail
     * @param cpoBean NWZC153001CpoBean
     */
    private void setPrcAmtForRtrnDtl(NWZC153001DetailBean cpoDtlBean, NWXC001001UnitPriceData unitPrcData) {
//        CCYTMsg dealCcyTMsg = (CCYTMsg) getCcyTMsg(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getCcyCd()); // QC#9694 2016/08/01
//        int dealCcyScale = dealCcyTMsg.aftDeclPntDigitNum.getValueInt(); // QC#9694 2016/08/01

//        BigDecimal entCpoDtlDealNetAmt = multiplyWithRound(cpoDtlBean.getEntDealNetUnitPrcAmt(), cpoDtlBean.getOrdQty(), dealCcyScale); // QC#9694 2016/08/01
        BigDecimal entCpoDtlDealNetAmt = unitPrcData.getDealNetUnitPrcAmt();
        cpoDtlBean.setEntCpoDtlDealNetAmt(entCpoDtlDealNetAmt);

//        BigDecimal entCpoDtlDealSlsAmt = multiplyWithRound(cpoDtlBean.getEntDealGrsUnitPrcAmt(), cpoDtlBean.getOrdQty(), dealCcyScale); // QC#9694 2016/08/01
        BigDecimal entCpoDtlDealSlsAmt = unitPrcData.getDealGrsUnitPrcAmt();
        cpoDtlBean.setEntCpoDtlDealSlsAmt(entCpoDtlDealSlsAmt);

//        BigDecimal cpoDtlDealNetAmt = multiplyWithRound(cpoDtlBean.getEntDealNetUnitPrcAmt(), cpoDtlBean.getOrdQty(), dealCcyScale); // QC#9694 2016/08/01
        BigDecimal cpoDtlDealNetAmt = unitPrcData.getDealNetUnitPrcAmt();
        cpoDtlBean.setCpoDtlDealNetAmt(cpoDtlDealNetAmt);

//        BigDecimal cpoDtlDealSlsAmt = multiplyWithRound(cpoDtlBean.getEntDealGrsUnitPrcAmt(), cpoDtlBean.getOrdQty(), dealCcyScale); // QC#9694 2016/08/01
        BigDecimal cpoDtlDealSlsAmt = unitPrcData.getDealGrsUnitPrcAmt();
        cpoDtlBean.setCpoDtlDealSlsAmt(cpoDtlDealSlsAmt);

//        GLBL_CMPYTMsg glblCmpyTMsg = (GLBL_CMPYTMsg) getGlblCmpyTMsg(cpoDtlBean.getGlblCmpyCd()); // QC#9694 2016/08/01
//        CCYTMsg stdrdCcyTMsg = (CCYTMsg) getCcyTMsg(cpoDtlBean.getGlblCmpyCd(), glblCmpyTMsg.stdCcyCd.getValue()); // QC#9694 2016/08/01
//        int stdrdCcyScale = stdrdCcyTMsg.aftDeclPntDigitNum.getValueInt(); // QC#9694 2016/08/01

        // 2015/12/17 S21_NA#2007 Mod Start
//        BigDecimal entCpoDtlFuncNetAmt = multiplyWithRound(cpoDtlBean.getFuncDealNetUnitPrcAmt(), cpoDtlBean.getOrdQty(), stdrdCcyScale);
//        cpoDtlBean.setEntCpoDtlDealNetAmt(entCpoDtlFuncNetAmt);
//
//        BigDecimal entCpoDtlFuncSlsAmt = multiplyWithRound(cpoDtlBean.getFuncDealGrsUnitPrcAmt(), cpoDtlBean.getOrdQty(), stdrdCcyScale);
//        cpoDtlBean.setEntCpoDtlDealNetAmt(entCpoDtlFuncSlsAmt);
//        BigDecimal entCpoDtlFuncNetAmt = multiplyWithRound(cpoDtlBean.getEntFuncNetUnitPrcAmt(), cpoDtlBean.getOrdQty(), stdrdCcyScale); // QC#9694 2016/08/01
        BigDecimal entCpoDtlFuncNetAmt = unitPrcData.getFuncNetUnitPrcAmt();
        cpoDtlBean.setEntCpoDtlFuncNetAmt(entCpoDtlFuncNetAmt);

//        BigDecimal entCpoDtlFuncSlsAmt = multiplyWithRound(cpoDtlBean.getFuncGrsUnitPrcAmt(), cpoDtlBean.getOrdQty(), stdrdCcyScale); // QC#9694 2016/08/01
        BigDecimal entCpoDtlFuncSlsAmt = unitPrcData.getFuncGrsUnitPrcAmt();
        cpoDtlBean.setEntCpoDtlFuncSlsAmt(entCpoDtlFuncSlsAmt);
        // 2015/12/17 S21_NA#2007 Mod End

//        BigDecimal cpoDtlFuncNetAmt = multiplyWithRound(cpoDtlBean.getEntFuncNetUnitPrcAmt(), cpoDtlBean.getOrdQty(), stdrdCcyScale); 2015/12/17 S21_NA#2007 Del
//        BigDecimal cpoDtlFuncNetAmt = multiplyWithRound(cpoDtlBean.getEntFuncNetUnitPrcAmt(), cpoDtlBean.getOrdQty(), stdrdCcyScale);  // QC#9694 2016/08/01
        BigDecimal cpoDtlFuncNetAmt = unitPrcData.getFuncNetUnitPrcAmt();
        cpoDtlBean.setCpoDtlFuncNetAmt(cpoDtlFuncNetAmt);

//        BigDecimal cpoDtlFuncSlsAmt = multiplyWithRound(cpoDtlBean.getFuncGrsUnitPrcAmt(), cpoDtlBean.getOrdQty(), stdrdCcyScale); // QC#9694 2016/08/01
        BigDecimal cpoDtlFuncSlsAmt = unitPrcData.getFuncGrsUnitPrcAmt();
        cpoDtlBean.setCpoDtlFuncSlsAmt(cpoDtlFuncSlsAmt);
    }

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * set Price Amount For CPO
//     * @param cpoBean NWZC153001CpoBean
//     * @param cpoDtlBean NWZC153001DetailBean
//     */
//    private void setPrcAmtForCPO(NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean) {
//
//        BigDecimal entCpoTotDealSlsAmt = add(cpoBean.getEntCpoTotDealSlsAmt(), cpoDtlBean.getEntCpoDtlDealSlsAmt());
//        cpoBean.setEntCpoTotDealSlsAmt(entCpoTotDealSlsAmt);
//
//        BigDecimal entCpoTotDealNetAmt = add(cpoBean.getEntCpoTotDealNetAmt(), cpoDtlBean.getEntCpoDtlDealNetAmt());
//        cpoBean.setEntCpoTotDealNetAmt(entCpoTotDealNetAmt);
//
//        BigDecimal entCpoTotFuncSlsAmt = add(cpoBean.getEntCpoTotFuncSlsAmt(), cpoDtlBean.getEntCpoDtlFuncSlsAmt());
//        cpoBean.setEntCpoTotFuncSlsAmt(entCpoTotFuncSlsAmt);
//
//        BigDecimal entCpoTotFuncNetAmt = add(cpoBean.getEntCpoTotFuncNetAmt(), cpoDtlBean.getEntCpoDtlFuncNetAmt());
//        cpoBean.setEntCpoTotFuncNetAmt(entCpoTotFuncNetAmt);
//
//        BigDecimal cpoTotDealSlsAmt = add(cpoBean.getCpoTotDealSlsAmt(), cpoDtlBean.getCpoDtlDealSlsAmt());
//        cpoBean.setCpoTotDealSlsAmt(cpoTotDealSlsAmt);
//
//        BigDecimal cpoTotDealNetAmt = add(cpoBean.getCpoTotDealNetAmt(), cpoDtlBean.getCpoDtlDealNetAmt());
//        cpoBean.setCpoTotDealNetAmt(cpoTotDealNetAmt);
//
//        BigDecimal cpoTotFuncSlsAmt = add(cpoBean.getCpoTotFuncSlsAmt(), cpoDtlBean.getCpoDtlFuncSlsAmt());
//        cpoBean.setCpoTotFuncSlsAmt(cpoTotFuncSlsAmt);
//
//        BigDecimal cpoTotFuncNetAmt = add(cpoBean.getCpoTotFuncNetAmt(), cpoDtlBean.getCpoDtlFuncNetAmt());
//        cpoBean.setCpoTotFuncNetAmt(cpoTotFuncNetAmt);
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    /**
     * add value of BigDecimal
     * @param bc1 BigDecimal
     * @param bc2 BigDecimal
     * @return BigDecimal
     */
    private static BigDecimal add(BigDecimal bc1, BigDecimal bc2) {
        if (!hasValue(bc1)) {
            bc1 = ZERO;
        }
        if (!hasValue(bc2)) {
            bc2 = ZERO;
        }
        return bc1.add(bc2);
    }

    /**
     * subtract value of BigDecimal
     * @param bc1 BigDecimal
     * @param bc2 BigDecimal
     * @return BigDecimal
     */
    private static BigDecimal subtract(BigDecimal bc1, BigDecimal bc2) {
        if (!hasValue(bc1)) {
            bc1 = ZERO;
        }
        if (!hasValue(bc2)) {
            bc2 = ZERO;
        }
        return bc1.subtract(bc2);
    }

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * multiply and round up value of BigDecimal
//     * @param bc1 BigDecimal
//     * @param bc2 BigDecimal
//     * @param ccyScale BigDecimal
//     * @return BigDecimal
//     */
//    private static BigDecimal multiplyWithRound(BigDecimal bc1, BigDecimal bc2, int ccyScale) {
//        if (!hasValue(bc1)) {
//            bc1 = ZERO;
//        }
//        if (!hasValue(bc2)) {
//            bc2 = ZERO;
//        }
//        return bc1.multiply(bc2).setScale(ccyScale, RoundingMode.HALF_UP);
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    /**
     * Over digit number check (for details)
     * 
     * <pre>
     * When numerical value exceeds the digit number, the error message is set and returned.
     * When the digit number is not exceeded, numerical value is set to the storage location.
     * </pre>
     * @param val BigDecimal
     * @param resPMsgList List<DWZC001002PMsg>
     * @param i int
     */
    private BigDecimal checkAmount(BigDecimal val, List<NWZC153002PMsg> resPMsgList, int i) {
        if (isMaxAmtOver(val)) {
            setErrMsgIdForCpoDtl(NWZM0148E, resPMsgList, i);
        }
        return val;
    }

    /**
     * isMaxAmtOver
     * @param bc BigDecimal
     * @return boolean
     */
    private static boolean isMaxAmtOver(BigDecimal bc) {
        return MAX_AMT.compareTo(bc) == -1;
    }

    /**
     * call NWXC001001EditPriceAmount
     * @param cpoBean NWZC153001CpoBean
     * @param NWZC153001DetailBean dtlLine NWZC153001DetailBean
     * @param List<NWZC153002PMsg> linePMsgList List<NWZC153002PMsg>
     * @param index int
     * @return NWXC001001UnitPriceData
     */
    private static NWXC001001UnitPriceData callNWXC001001EditPriceAmount(NWZC153001CpoBean cpoBean, NWZC153001DetailBean dtlLine, List<NWZC153002PMsg> linePMsgList, int index) {
        NWXC001001EditPriceAmountInfo editPrcAmtInfo = new NWXC001001EditPriceAmountInfo();

        editPrcAmtInfo.setGlblCmpyCd(cpoBean.getGlblCmpyCd());
        editPrcAmtInfo.setMdseCd(dtlLine.getMdseCd());
        editPrcAmtInfo.setSlsDt(cpoBean.getSlsDt());
        editPrcAmtInfo.setCcyCd(dtlLine.getCcyCd());
        editPrcAmtInfo.setDealGrsPrcAmt(dtlLine.getEntDealGrsUnitPrcAmt());
        editPrcAmtInfo.setDealNetPrcAmt(dtlLine.getEntDealNetUnitPrcAmt());

        editPrcAmtInfo = NWXC001001EditPriceAmount.getCmpsnPriceList(editPrcAmtInfo);

        for (String errorID : editPrcAmtInfo.getXxMsgIdList()) {
            setErrMsgIdForCpoDtl(errorID, linePMsgList, index);
        }

        return editPrcAmtInfo.getUnitPrcData();
    }

    // QC#12481 2016/10/26 Add Start
    /**
     * NWXC001001UnitPriceData
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     * @param grsPrice BigDecimal
     * @param netPrice BigDecimal
     * @param linePMsgList List<NWZC153002PMsg>
     * @param index int
     * @return NWXC001001UnitPriceData
     */
    private NWXC001001UnitPriceData callNWXC001001EditPriceAmount(NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean, BigDecimal grsPrice, BigDecimal netPrice, List<NWZC153002PMsg> linePMsgList, int index) {

        NWXC001001EditPriceAmountInfo editPrcAmtInfo = new NWXC001001EditPriceAmountInfo();

        editPrcAmtInfo.setGlblCmpyCd(cpoBean.getGlblCmpyCd());
        editPrcAmtInfo.setMdseCd(cpoDtlBean.getMdseCd());
        editPrcAmtInfo.setSlsDt(cpoBean.getSlsDt());
        editPrcAmtInfo.setCcyCd(cpoDtlBean.getCcyCd());
        editPrcAmtInfo.setDealGrsPrcAmt(grsPrice);
        editPrcAmtInfo.setDealNetPrcAmt(netPrice);

        editPrcAmtInfo = NWXC001001EditPriceAmount.getCmpsnPriceList(editPrcAmtInfo);

        for (String errorID : editPrcAmtInfo.getXxMsgIdList()) {
            setErrMsgIdForCpoDtl(errorID, linePMsgList, index);
        }

        return editPrcAmtInfo.getUnitPrcData();
    }
    // QC#12481 2016/10/26 Add End

    /**
     * call NWXC001001EditPriceAmount
     * @param cpoBean NWZC153001CpoBean
     * @param NWZC153001DetailBean dtlLine NWZC153001DetailBean
     * @param List<NWZC153002PMsg> linePMsgList List<NWZC153002PMsg>
     * @param index int
     * @return NWXC001001UnitPriceData
     */
    private static NWXC001001UnitPriceData callNWXC001001EditPriceAmountForAmt(NWZC153001CpoBean cpoBean, NWZC153001DetailBean dtlLine, List<NWZC153002PMsg> linePMsgList, int index) {
        NWXC001001EditPriceAmountInfo editPrcAmtInfo = new NWXC001001EditPriceAmountInfo();

        editPrcAmtInfo.setGlblCmpyCd(cpoBean.getGlblCmpyCd());
        editPrcAmtInfo.setMdseCd(dtlLine.getMdseCd());
        editPrcAmtInfo.setSlsDt(cpoBean.getSlsDt());
        editPrcAmtInfo.setCcyCd(dtlLine.getCcyCd());
        editPrcAmtInfo.setDealGrsPrcAmt(dtlLine.getEntCpoDtlDealSlsAmt());
        editPrcAmtInfo.setDealNetPrcAmt(dtlLine.getEntCpoDtlDealNetAmt());

        editPrcAmtInfo = NWXC001001EditPriceAmount.getCmpsnPriceList(editPrcAmtInfo);

        for (String errorID : editPrcAmtInfo.getXxMsgIdList()) {
            setErrMsgIdForCpoDtl(errorID, linePMsgList, index);
        }

        return editPrcAmtInfo.getUnitPrcData();
    }

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * register Cpo
//     * @param pMsg NWZC153001PMsg
//     * @param cpoBean NWZC153001CpoBean
//     * @param isInsertForCPO boolean
//     * @param isSaveHdrRqst boolean
//     * @param isNewHdrRqst boolean
//     * @param isCancelHdrRqst boolean
//     * @param isModifyHdrRqst boolean
//     */
//    private void registCpo(NWZC153001PMsg pMsg, NWZC153001CpoBean cpoBean, boolean isInsertForCPO, boolean isSaveHdrRqst, boolean isNewHdrRqst, boolean isCancelHdrRqst, boolean isModifyHdrRqst, boolean isPureReturn) {
//        // INSERT
//        if (isInsertForCPO) {
//            if (isSaveHdrRqst || isNewHdrRqst) {
//                CPOTMsg insCpoTMsg = new CPOTMsg();
//                addInsCpoTMsg(cpoBean, insCpoTMsg, isSaveHdrRqst, isNewHdrRqst);
//
//                // 2015/12/16 S21_NA#2007 Add TODO which one should bet set exact recored??
//                if (!ZYPCommonFunc.hasValue(insCpoTMsg.cpoHldFlg)) {
//                    insCpoTMsg.cpoHldFlg.setValue(ZYPConstant.FLG_OFF_N);
//                }
//                if (!ZYPCommonFunc.hasValue(insCpoTMsg.cpoCancFlg)) {
//                    insCpoTMsg.cpoCancFlg.setValue(ZYPConstant.FLG_OFF_N);
//                }
//                if (!ZYPCommonFunc.hasValue(insCpoTMsg.sendInvFlg)) {
//                    insCpoTMsg.sendInvFlg.setValue(ZYPConstant.FLG_OFF_N);
//                }
//                if (!ZYPCommonFunc.hasValue(insCpoTMsg.submtFlg)) {
//                    insCpoTMsg.submtFlg.setValue(ZYPConstant.FLG_OFF_N);
//                }
//                // 2015/12/16 S21_NA#2007 End
//                // cacheDBAccess.insertWithCache(insCpoTMsg); 2016/04/19 S21_NA#5394 Del
//                S21ApiTBLAccessor.insert(insCpoTMsg); // 2016/04/19 S21_NA#5394 Add
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insCpoTMsg.getReturnCode())) {
//                    msgMap.addXxMsgId(NWZM1599E);
//                }
//            }
//            return;
//        }
//
//        // UPDATE
//        // CPOTMsg updCpoTMsg = new CPOTMsg();
//        CPOTMsg updCpoTMsg = (CPOTMsg) getCpoTMsgForUpdate(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
//
//        if (updCpoTMsg == null) {
//            msgMap.addXxMsgId(NWZM1368E);
//            return;
//        }
//
//        // 2016/01/14 S21_NA#2996 Mod Start
////        if (isPureReturn) {
////            addUpdCpoTMsg(cpoBean, updCpoTMsg, isSaveHdrRqst, isNewHdrRqst, isCancelHdrRqst, isModifyHdrRqst);
////        } else {
////            addUpdCpoTMsgForModifyAmt(cpoBean, updCpoTMsg, isCancelHdrRqst);
////        }
//            // Cancel Mode
//        if (isCancelHdrRqst) {
//            String updateMode = chckUpdTypeForCancelCpo(cpoBean);
//
////            if (UPD_MODE_CANC_OPEN_DTL.equals(updateMode)) {
////                addCpoTmsg(cpoBean, cpoTMsg);
////                return;
////            }
//            if (UPD_MODE_CANC_ALL_CANC.equals(updateMode)) {
//                ZYPEZDItemValueSetter.setValue(updCpoTMsg.ordHdrStsCd, ORD_HDR_STS.CANCELLED);
//                ZYPEZDItemValueSetter.setValue(updCpoTMsg.cpoHldFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(updCpoTMsg.openFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(updCpoTMsg.cpoCancFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(updCpoTMsg.cpoCancDt, cpoBean.getSlsDt());
//
//                // 2016/01/18 S21_NA#2996 (For Reopen) Add Start
//                getHdrAmoutForNotCanceled(pMsg, updCpoTMsg);
//                // 2016/01/18 S21_NA#2996 (For Reopen) Add End
//            }
//            if (UPD_MODE_CANC_CLOSED.equals(updateMode)) {
//                ZYPEZDItemValueSetter.setValue(updCpoTMsg.ordHdrStsCd, ORD_HDR_STS.CLOSED);
//                ZYPEZDItemValueSetter.setValue(updCpoTMsg.openFlg, ZYPConstant.FLG_OFF_N);
//            }
//        } else {
//            if (isPureReturn) {
//                addUpdCpoTMsg(cpoBean, updCpoTMsg, isSaveHdrRqst, isNewHdrRqst, isCancelHdrRqst, isModifyHdrRqst);
//            } else {
//                addUpdCpoTMsgForModifyAmt(cpoBean, updCpoTMsg, isCancelHdrRqst);
//            }
//        }
//        // 2016/01/14 S21_NA#2996 Mod End
//        S21ApiTBLAccessor.update(updCpoTMsg);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updCpoTMsg.getReturnCode())) {
//            msgMap.addXxMsgId(NWZM1368E);
//            return;
//        }
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * registDsCpo
//     * @param pMsg NWZC153001PMsg
//     * @param cpoBean NWZC153001CpoBean
//     * @param isInsertForCPO boolean
//     * @param isSaveHdrRqst boolean
//     * @param isNewHdrRqst boolean
//     * @param isCancelHdrRqst boolean
//     * @param isModifyHdrRqst boolean
//     * @param isOrdValidated boolean
//     */
//    private void registDsCpo(NWZC153001PMsg pMsg, NWZC153001CpoBean cpoBean, boolean isInsertForCPO, boolean isSaveHdrRqst, boolean isNewHdrRqst, boolean isModifyHdrRqst, boolean isOrdValidated) {
//        // INSERT
//        if (isInsertForCPO) {
//            if (isSaveHdrRqst || isNewHdrRqst) {
//                CPOTMsg insDsCpoTMsg = new CPOTMsg();
//                addDsCpoTmsg(cpoBean, insDsCpoTMsg);
//
//                // 2015/12/16 S21_NA#2007 Add TODO which one should bet set exact recored??
//                insDsCpoTMsg.glblCmpyCd.setValue(cpoBean.getGlblCmpyCd());
//                if (!ZYPCommonFunc.hasValue(insDsCpoTMsg.diChkHldFlg)) {
//                    insDsCpoTMsg.diChkHldFlg.setValue(ZYPConstant.FLG_OFF_N);
//                }
//                if (!ZYPCommonFunc.hasValue(insDsCpoTMsg.wfRejFlg)) {
//                    insDsCpoTMsg.wfRejFlg.setValue(ZYPConstant.FLG_OFF_N);
//                }
//                if (!ZYPCommonFunc.hasValue(insDsCpoTMsg.ordBookFlg)) {
//                    insDsCpoTMsg.ordBookFlg.setValue(ZYPConstant.FLG_OFF_N);
//                }
//                // 2015/12/16 S21_NA#2007 End
//
//                // 2016/09/08 S21_NA#6100 Add Start
//                ZYPEZDItemValueSetter.setValue(insDsCpoTMsg.dsCpoPrcInd, cpoBean.getDsCpoPrcInd());
//                ZYPEZDItemValueSetter.setValue(insDsCpoTMsg.dsCpoCratTs, this.curTime);
//                ZYPEZDItemValueSetter.setValue(insDsCpoTMsg.dsCpoCratUsrId, cpoBean.getAdminPsnCd());
//                ZYPEZDItemValueSetter.setValue(insDsCpoTMsg.dsCpoUpdTs, this.curTime);
//                ZYPEZDItemValueSetter.setValue(insDsCpoTMsg.dsCpoUpdUsrId, cpoBean.getAdminPsnCd());
//                if (isNewHdrRqst) {
//                    ZYPEZDItemValueSetter.setValue(insDsCpoTMsg.ordBookReqTs, this.curTime);
//                    ZYPEZDItemValueSetter.setValue(insDsCpoTMsg.ordBookReqUsrId, cpoBean.getAdminPsnCd());
//                }
//                // 2016/09/08 S21_NA#6100 Add End
//
//                // cacheDBAccess.insertWithCache(insDsCpoTMsg); 2016/04/19 S21_NA#5394 Del
//                S21ApiTBLAccessor.insert(insDsCpoTMsg); // 2016/04/19 S21_NA#5394 Add
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insDsCpoTMsg.getReturnCode())) {
//                    msgMap.addXxMsgId(NWZM1599E);
//                }
//            }
//            return;
//        }
//
//        // UPDATE
//        if (isSaveHdrRqst || isNewHdrRqst || isModifyHdrRqst) {
//
//            if (isModifyHdrRqst) {
////                if (!isCloseCpoForModify(cpoBean)) {
//                if (isCloseCpoForModify(cpoBean)) {
//                    return;
//                }
//            }
//
//            CPOTMsg updCpoTMsg = (CPOTMsg) getCpoTMsgForUpdate(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
//
//            if (updCpoTMsg == null) {
//                msgMap.addXxMsgId(NWZM1368E);
//                return;
//            }
//
//            addDsCpoTmsg(cpoBean, updCpoTMsg);
//            // 2015/12/24 S21_NA#1957 Add TODO which one should bet set exact recored??
//            updCpoTMsg.glblCmpyCd.setValue(cpoBean.getGlblCmpyCd());
//            if (!ZYPCommonFunc.hasValue(updCpoTMsg.diChkHldFlg)) {
//                updCpoTMsg.diChkHldFlg.setValue(ZYPConstant.FLG_OFF_N);
//            }
//            if (!ZYPCommonFunc.hasValue(updCpoTMsg.wfRejFlg)) {
//                updCpoTMsg.wfRejFlg.setValue(ZYPConstant.FLG_OFF_N);
//            }
//            if (!ZYPCommonFunc.hasValue(updCpoTMsg.ordBookFlg)) {
//                updCpoTMsg.ordBookFlg.setValue(ZYPConstant.FLG_OFF_N);
//            }
//            // 2015/12/24 S21_NA#1957 End
//
//            // 2016/09/08 S21_NA#6100 Add Start
//            ZYPEZDItemValueSetter.setValue(updCpoTMsg.dsCpoPrcInd, cpoBean.getDsCpoPrcInd());
//            ZYPEZDItemValueSetter.setValue(updCpoTMsg.dsCpoUpdTs, this.curTime);
//            ZYPEZDItemValueSetter.setValue(updCpoTMsg.dsCpoUpdUsrId, cpoBean.getAdminPsnCd());
//            if (isNewHdrRqst || isOrdValidated) {
//                ZYPEZDItemValueSetter.setValue(updCpoTMsg.ordBookReqTs, this.curTime);
//                ZYPEZDItemValueSetter.setValue(updCpoTMsg.ordBookReqUsrId, cpoBean.getAdminPsnCd());
//            }
//            // 2016/09/08 S21_NA#6100 Add End
//
//            // cacheDBAccess.updateWithCache(updCpoTMsg); 2016/04/19 S21_NA#5394 Del
//            S21ApiTBLAccessor.update(updCpoTMsg); // 2016/04/19 S21_NA#5394 Add
//
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updCpoTMsg.getReturnCode())) {
//                msgMap.addXxMsgId(NWZM1368E);
//            }
//        }
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    /**
     * <pre>
     * registDsCpoRtrnDtl
     * @param pMsg NWZC153001PMsg
     * @param cpoBean NWZC153001CpoBean
     * @param linePMsgList List<NWZC153002PMsg>
     * @param isOrderBooked true: Header already booked false: not header booked. <- 2016/04/26 S21_NA#7338 Add
     * @param cpoTMsg Related CPO data.
     * </pre>
     */
    private void registDsCpoRtrnDtl(NWZC153001PMsg pMsg, NWZC153001CpoBean cpoBean, List<NWZC153002PMsg> linePMsgList, boolean isOrderBooked, CPOTMsg cpoTMsg) {

        // 2016/09/27 S21_NA#9102 Add Start
        updateBaseCmptFlg(pMsg, cpoBean);
        // 2016/09/27 S21_NA#9102 Add End

        // 2016/12/20 S21_NA#15898-2 Add Start
        if (!chkSlctLineHddRmv(cpoBean, linePMsgList)) {
            return;
        }
        // 2016/12/20 S21_NA#15898-2 Add End

        NWZC153001DetailBean cpoDtlBean = null;
        for (int idx = 0; idx < cpoBean.getDtlBeanList().size(); idx++) {
            cpoDtlBean = cpoBean.getDtlBeanList().get(idx);

            String glblCmpyCd = cpoDtlBean.getGlblCmpyCd();
            String cpoOrdNum = cpoDtlBean.getCpoOrdNum();
            String dsCpoRtrnLineNum = cpoDtlBean.getDsCpoRtrnLineNum();
            String dsCpoRtrnLineSubNum = cpoDtlBean.getDsCpoRtrnLineSubNum();

            // get Update
            DS_CPO_RTRN_DTLTMsg updRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) getDsCpoRtrnDtlTMsgForUpdate(glblCmpyCd, cpoOrdNum, dsCpoRtrnLineNum, dsCpoRtrnLineSubNum);

            if (updRtrnDtlTMsg != null) {
                cpoDtlBean.setSerNum_BK(updRtrnDtlTMsg.serNum.getValue());
                cpoDtlBean.setSvcMachMstrPk_BK(updRtrnDtlTMsg.svcMachMstrPk.getValue());

                if (S21StringUtil.isEquals(RTRN_LINE_STS.ENTERED, updRtrnDtlTMsg.rtrnLineStsCd.getValue())) { // QC#25227
                    // Adjust Set line. 2016/11/04 S21_NA#14833 Add
                    adjustSetDtl(pMsg, updRtrnDtlTMsg);
                }
            }

            // Save Mode
            if (RQST_DTL_SAVE.equals(cpoDtlBean.getXxRqstTpCd())) {
                if (updRtrnDtlTMsg == null) {
                    cpoDtlBean.setCpoOrdNum(cpoBean.getCpoOrdNum());

                    DS_CPO_RTRN_DTLTMsg insRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.ENTERED);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.openFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.submtFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.baseCmptFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.taxFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.cpoDtlCancFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.cpoDtlHldFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.manPrcFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.manPmtFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.convCompFlg, ZYPConstant.FLG_OFF_N);
                    setPhysicalDisplayStatusCd(insRtrnDtlTMsg, cpoTMsg);

                    // 2015/1216 S21_NA#2007 Add Start
                    // cpoDtlBean.setRtrnLineStsCd(RTRN_LINE_STS.ENTERED);
                    cpoDtlBean.setOpenFlg(ZYPConstant.FLG_ON_Y);
                    // cpoDtlBean.setSubmtFlg(ZYPConstant.FLG_OFF_N);
                    // cpoDtlBean.setBaseCmptFlg(ZYPConstant.FLG_OFF_N); 2016/09/27 S21_NA#9102 Del
                    // 2016/02/22 S21_NA#2830 Mod Start
                    // cpoDtlBean.setDropShipFlg(ZYPConstant.FLG_OFF_N);
                    if (!ZYPCommonFunc.hasValue(cpoDtlBean.getDropShipFlg())) {
                        cpoDtlBean.setDropShipFlg(ZYPConstant.FLG_OFF_N);
                    }
                    // 2016/02/22 S21_NA#2830 Mod End
                    cpoDtlBean.setTaxFlg(ZYPConstant.FLG_OFF_N);
                    cpoDtlBean.setCpoDtlCancFlg(ZYPConstant.FLG_OFF_N);
                    // cpoDtlBean.setCpoDtlHldFlg(ZYPConstant.FLG_OFF_N);
                    cpoDtlBean.setManPrcFlg(ZYPConstant.FLG_OFF_N);
                    // cpoDtlBean.setManPmtFlg(ZYPConstant.FLG_OFF_N);
                    // cpoDtlBean.setConvCompFlg(ZYPConstant.FLG_OFF_N);
                    // 2015/1216 S21_NA#2007 Add End
                    if (!insertDsCpoRtrnDtl(cpoDtlBean, insRtrnDtlTMsg, linePMsgList, idx)) {
                        return;
                    }
                } else {
                    setPhysicalDisplayStatusCd(updRtrnDtlTMsg, cpoTMsg);
                    if (!updateDsCpoRtrnDtl(cpoDtlBean, updRtrnDtlTMsg, linePMsgList, idx)) {
                        return;
                    }
                }
                continue;
            }

            // New Mode
            if (RQST_DTL_NEW.equals(cpoDtlBean.getXxRqstTpCd())) {
                if (updRtrnDtlTMsg == null) {
                    cpoDtlBean.setCpoOrdNum(cpoBean.getCpoOrdNum());

                    DS_CPO_RTRN_DTLTMsg insRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.cpoOrdTs, cpoBean.getCpoOrdTs());
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.cpoOrdSubmtTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.ENTERED);
                    // 2016/04/26 S21_NA#7338 Add Start
                    if (isOrderBooked) {
                        ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.BOOKED);
                    }
                    setPhysicalDisplayStatusCd(insRtrnDtlTMsg, cpoTMsg);
                    // 2016/04/26 S21_NA#7338 Add End
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.submtFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.openFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.baseCmptFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.taxFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.cpoDtlCancFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.cpoDtlHldFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.manPrcFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.manPmtFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insRtrnDtlTMsg.convCompFlg, ZYPConstant.FLG_OFF_N);

                    // 2015/1216 S21_NA#2007 Add Start
                    // cpoDtlBean.setRtrnLineStsCd(RTRN_LINE_STS.ENTERED);
                    cpoDtlBean.setOpenFlg(ZYPConstant.FLG_ON_Y);
                    // cpoDtlBean.setSubmtFlg(ZYPConstant.FLG_OFF_N);
                    // cpoDtlBean.setBaseCmptFlg(ZYPConstant.FLG_OFF_N); 2016/09/27 S21_NA#9102 Del
                    cpoDtlBean.setDropShipFlg(ZYPConstant.FLG_OFF_N);
                    cpoDtlBean.setTaxFlg(ZYPConstant.FLG_OFF_N);
                    cpoDtlBean.setCpoDtlCancFlg(ZYPConstant.FLG_OFF_N);
                    // cpoDtlBean.setCpoDtlHldFlg(ZYPConstant.FLG_OFF_N);
                    cpoDtlBean.setManPrcFlg(ZYPConstant.FLG_OFF_N);
                    // cpoDtlBean.setManPmtFlg(ZYPConstant.FLG_OFF_N);
                    // cpoDtlBean.setConvCompFlg(ZYPConstant.FLG_OFF_N);
                    // 2015/1216 S21_NA#2007 Add End
                    if (!insertDsCpoRtrnDtl(cpoDtlBean, insRtrnDtlTMsg, linePMsgList, idx)) {
                        return;
                    }

                } else {
                    ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
                    ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.cpoOrdTs, cpoBean.getCpoOrdTs());
                    ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.cpoOrdSubmtTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                    ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.ENTERED);
                    // 2016/04/26 S21_NA#7338 Add Start
                    if (isOrderBooked) {
                        ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.BOOKED);
                    }
                    setPhysicalDisplayStatusCd(updRtrnDtlTMsg, cpoTMsg);
                    // 2016/04/26 S21_NA#7338 Add End
                    ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.submtFlg, ZYPConstant.FLG_ON_Y);

                    if (!updateDsCpoRtrnDtl(cpoDtlBean, updRtrnDtlTMsg, linePMsgList, idx)) {
                        return;
                    }
                }
                continue;
            }

            // Modify Mode
            if (RQST_DTL_MOD.equals(cpoDtlBean.getXxRqstTpCd())) {
                if (updRtrnDtlTMsg != null) {
                    // 2019/03/15 S21_NA#30700 Mod Start
//                    if (RTRN_LINE_STS.PARTIALLY_RECEIVED.equals(updRtrnDtlTMsg.rtrnLineStsCd.getValue()) && updRtrnDtlTMsg.ordQty.getValue().compareTo(updRtrnDtlTMsg.rtrnQty.getValue()) == 0) {
                    if (RTRN_LINE_STS.PARTIALLY_RECEIVED.equals(updRtrnDtlTMsg.rtrnLineStsCd.getValue()) && (updRtrnDtlTMsg.ordQty.getValue().compareTo(updRtrnDtlTMsg.rtrnQty.getValue()) == 0) || cpoDtlBean.getOrdQty().compareTo(updRtrnDtlTMsg.rtrnQty.getValue()) == 0) {

                        ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.RECEIVED);
                        setPhysicalDisplayStatusCd(updRtrnDtlTMsg, cpoTMsg);
                        if (!updateDsCpoRtrnDtl(cpoDtlBean, updRtrnDtlTMsg, linePMsgList, idx)) {
                            return;
                        }
                    } else { // 2016/04/19 S21_NA#5394 Add Start
                        setPhysicalDisplayStatusCd(updRtrnDtlTMsg, cpoTMsg);
                        if (!updateDsCpoRtrnDtl(cpoDtlBean, updRtrnDtlTMsg, linePMsgList, idx)) {
                            return;
                        }
                    } // 2016/04/19 S21_NA#5394 Add End
                }
                continue;
            }

            // Cancel Mode
            if (RQST_DTL_CANCEL.equals(cpoDtlBean.getXxRqstTpCd())) {
                if (updRtrnDtlTMsg != null) {
                    // 2019/03/15 S21_NA#30700 Add Start
                    if (RTRN_LINE_STS.PARTIALLY_RECEIVED.equals(updRtrnDtlTMsg.rtrnLineStsCd.getValue()) && (updRtrnDtlTMsg.ordQty.getValue().compareTo(updRtrnDtlTMsg.rtrnQty.getValue()) == 0) || cpoDtlBean.getOrdQty().compareTo(updRtrnDtlTMsg.rtrnQty.getValue()) == 0) {
                        ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.RECEIVED);
                    // 2019/03/15 S21_NA#30700 Add End
                    } else {
                        // 2018/08/07 S21_NA#25404 Add Start
                        String curStsCd = updRtrnDtlTMsg.rtrnLineStsCd.getValue();
                        // 2018/08/07 S21_NA#25404 Add End
                        ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.CANCELLED);
                        ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.cpoDtlHldFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.openFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.cpoDtlCancFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.cpoDtlCancDt, cpoBean.getSlsDt());
                        // 2018/08/07 S21_NA#25404 Mod Start
                        // ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.cancQty,
                        // updRtrnDtlTMsg.ordQty);
                        if (!RTRN_LINE_STS.CANCELLED.equals(curStsCd)) {
                            ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.cancQty, updRtrnDtlTMsg.cancQty.getValue().add(updRtrnDtlTMsg.ordQty.getValue()));
                        }
                        // START 2022/08/16 F.Fadriquela [QC#60341, ADD]
                        final HLDTMsg updTMsgKey = new HLDTMsg();
                        updTMsgKey.setSQLID("031");
                        updTMsgKey.setConditionValue("glblCmpyCd01", cpoDtlBean.getGlblCmpyCd());
                        updTMsgKey.setConditionValue("cpoOrdNum01", cpoDtlBean.getCpoOrdNum());
                        updTMsgKey.setConditionValue("cpoDtlLineNum01", cpoDtlBean.getDsCpoRtrnLineNum());
                        updTMsgKey.setConditionValue("cpoDtlLineSubNum01", cpoDtlBean.getDsCpoRtrnLineSubNum());
                        updTMsgKey.setConditionValue("trxSrcTpCd01", TRX_SRC_TP.WHOLE_SALES_RETURN);

                        final HLDTMsgArray hldTMsgArray = (HLDTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(updTMsgKey);

                        for (int i = 0; i < hldTMsgArray.getValidCount(); i++) {
                            // ***** [logicalRemove]
                            logicalRemove(hldTMsgArray.no(i));
                        }
                        // END 2022/08/16 F.Fadriquela [QC#60341, ADD]
                    }
                    // 2018/08/07 S21_NA#25404 Mod End
                    setPhysicalDisplayStatusCd(updRtrnDtlTMsg, cpoTMsg);

                    // 2016/01/14 S21_NA#2996 Mod Start
                    // 2015/1216 S21_NA#2007 Add Start
//                    cpoDtlBean.setOpenFlg(ZYPConstant.FLG_OFF_N);
//                    cpoDtlBean.setCpoDtlCancFlg(ZYPConstant.FLG_ON_Y);
//                    // 2015/1216 S21_NA#2007 Add End
//
//                    if (!updateDsCpoRtrnDtl(cpoDtlBean, updRtrnDtlTMsg, linePMsgList, idx)) {
//                        return;
//                    }
                    
                    S21ApiTBLAccessor.update(updRtrnDtlTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updRtrnDtlTMsg.getReturnCode())) {
                        setErrMsgIdForCpoDtl(NWZM1598E, linePMsgList, idx);
                        return;
                    }
                    // 2016/01/14 S21_NA#2996 Mod End
                }
                continue;
            }

            // RWS Creation Mode
            if (RQST_DTL_RWS_CREATE.equals(cpoDtlBean.getXxRqstTpCd())) {
                if (updRtrnDtlTMsg != null) {
                    if (RTRN_LINE_STS.PARTIALLY_RECEIVED.equals(updRtrnDtlTMsg.rtrnLineStsCd.getValue())) {
                        continue;
                    }

                    ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.RWS_CREATED);
                    setPhysicalDisplayStatusCd(updRtrnDtlTMsg, cpoTMsg);
                    // 2015/12/25 S21_NA#2503 Mod Start
//                    if (!updateDsCpoRtrnDtl(cpoDtlBean, updRtrnDtlTMsg, linePMsgList, idx)) {
//                        return;
//                    }
                    S21ApiTBLAccessor.update(updRtrnDtlTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updRtrnDtlTMsg.getReturnCode())) {
                        setErrMsgIdForCpoDtl(NWZM1598E, linePMsgList, idx);
                        return;
                    }
                    // 2015/12/25 S21_NA#2503 Mod End
                    // 2016/06/06 S21_NA#9374 Mod Start
                    if (hasValue(updRtrnDtlTMsg.setMdseCd)) {
                        if (0 == getSetCmptStsCnt(glblCmpyCd, cpoOrdNum, dsCpoRtrnLineNum, PARENT_LINE_SUB_NUM, RTRN_LINE_STS.RWS_CREATED)) {
                            // get Update
                            DS_CPO_RTRN_DTLTMsg updSetRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) getDsCpoRtrnDtlTMsgForUpdate(glblCmpyCd, cpoOrdNum, dsCpoRtrnLineNum, PARENT_LINE_SUB_NUM);
                            ZYPEZDItemValueSetter.setValue(updSetRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.RWS_CREATED);
                            setPhysicalDisplayStatusCd(updSetRtrnDtlTMsg, cpoTMsg);
                            S21ApiTBLAccessor.update(updSetRtrnDtlTMsg);
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updSetRtrnDtlTMsg.getReturnCode())) {
                                setErrMsgIdForCpoDtl(NWZM1598E, linePMsgList, idx);
                                return;
                            }
                        }
                    }
                    // 2016/06/06 S21_NA#9374 Mod End
                }
                continue;
            }

            // RWS Cancel Mode
            if (RQST_DTL_RWC_CANCEL.equals(cpoDtlBean.getXxRqstTpCd())) {
                if (updRtrnDtlTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.RWS_CANCELLED);
                    setPhysicalDisplayStatusCd(updRtrnDtlTMsg, cpoTMsg);
                    // 2016/03/25 S21_NA#6063 Mod Start
//                    if (!updateDsCpoRtrnDtl(cpoDtlBean, updRtrnDtlTMsg, linePMsgList, idx)) {
//                        return;
//                    }
                    S21ApiTBLAccessor.update(updRtrnDtlTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updRtrnDtlTMsg.getReturnCode())) {
                        setErrMsgIdForCpoDtl(NWZM1598E, linePMsgList, idx);
                        return;
                    }
                    // 2015/12/25 S21_NA#6063 Mod End
                    // 2016/06/06 S21_NA#9374 Mod Start
                    if (hasValue(updRtrnDtlTMsg.setMdseCd)) {
                        if (0 == getSetCmptStsCnt(glblCmpyCd, cpoOrdNum, dsCpoRtrnLineNum, PARENT_LINE_SUB_NUM, RTRN_LINE_STS.RWS_CANCELLED)) {
                            // get Update
                            DS_CPO_RTRN_DTLTMsg updSetRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) getDsCpoRtrnDtlTMsgForUpdate(glblCmpyCd, cpoOrdNum, dsCpoRtrnLineNum, PARENT_LINE_SUB_NUM);
                            ZYPEZDItemValueSetter.setValue(updSetRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.RWS_CANCELLED);
                            setPhysicalDisplayStatusCd(updSetRtrnDtlTMsg, cpoTMsg);
                            S21ApiTBLAccessor.update(updSetRtrnDtlTMsg);
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updSetRtrnDtlTMsg.getReturnCode())) {
                                setErrMsgIdForCpoDtl(NWZM1598E, linePMsgList, idx);
                                return;
                            }
                        }
                    }
                    // 2016/06/06 S21_NA#9374 Mod End
                }
                continue;
            }

            // Receive Mode
            if (RQST_DTL_RECEIVE.equals(cpoDtlBean.getXxRqstTpCd())) {
                if (updRtrnDtlTMsg != null) {

                    // 2016/06/06 S21_NA#9374 Mod Start
                    boolean rtrnLineStsRcvFlg = false;
                    BigDecimal rtrnQty = add(updRtrnDtlTMsg.rtrnQty.getValue(), cpoDtlBean.getRtrnQty());
//                    if (cpoDtlBean.getRtrnQty().compareTo(updRtrnDtlTMsg.ordQty.getValue()) == 0) {
                    if (rtrnQty.compareTo(updRtrnDtlTMsg.ordQty.getValue()) == 0) {
                        ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.RECEIVED);
                        ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.rtrnQty, rtrnQty);
                        rtrnLineStsRcvFlg = true;

                    } else {
//                        BigDecimal rtrnQty = add(updRtrnDtlTMsg.rtrnQty.getValue(), cpoDtlBean.getRtrnQty());
                        ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.PARTIALLY_RECEIVED);
                        ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.rtrnQty, rtrnQty);
                    }

                    // 2016/03/25 S21_NA#6063 Mod Start
//                    if (!updateDsCpoRtrnDtl(cpoDtlBean, updRtrnDtlTMsg, linePMsgList, idx)) {
//                        return;
//                    }
                    setPhysicalDisplayStatusCd(updRtrnDtlTMsg, cpoTMsg);
                    S21ApiTBLAccessor.update(updRtrnDtlTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updRtrnDtlTMsg.getReturnCode())) {
                        setErrMsgIdForCpoDtl(NWZM1598E, linePMsgList, idx);
                        return;
                    }
                    // 2015/12/25 S21_NA#6063 Mod End
                    if (hasValue(updRtrnDtlTMsg.setMdseCd) && rtrnLineStsRcvFlg) {
                        if (0 == getSetCmptStsCnt(glblCmpyCd, cpoOrdNum, dsCpoRtrnLineNum, PARENT_LINE_SUB_NUM, RTRN_LINE_STS.RECEIVED)) {
                            // get Update
                            DS_CPO_RTRN_DTLTMsg updSetRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) getDsCpoRtrnDtlTMsgForUpdate(glblCmpyCd, cpoOrdNum, dsCpoRtrnLineNum, PARENT_LINE_SUB_NUM);
                            ZYPEZDItemValueSetter.setValue(updSetRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.RECEIVED);
                            setPhysicalDisplayStatusCd(updSetRtrnDtlTMsg, cpoTMsg);

                            // 2018/08/23 QC#27243 Add Start
                            if (hasValue(updSetRtrnDtlTMsg.ordQty)){
                                if (hasValue(updSetRtrnDtlTMsg.rtrnQty)){
                                    if (!updSetRtrnDtlTMsg.rtrnQty.getValue().equals(updSetRtrnDtlTMsg.ordQty.getValue())){
                                        setValue(updSetRtrnDtlTMsg.rtrnQty, updSetRtrnDtlTMsg.ordQty.getValue());
                                    }
                                } else {
                                    setValue(updSetRtrnDtlTMsg.rtrnQty, updSetRtrnDtlTMsg.ordQty.getValue());
                                }
                            }
                            // 2018/08/23 QC#27243 Add End

                            S21ApiTBLAccessor.update(updSetRtrnDtlTMsg);
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updSetRtrnDtlTMsg.getReturnCode())) {
                                setErrMsgIdForCpoDtl(NWZM1598E, linePMsgList, idx);
                                return;
                            }
                        }
                    }
                    // 2016/06/06 S21_NA#9374 Mod End
                }
                continue;
            }
            // mod start 2016/07/26 CSA S21_NA#8854
            // Shortage Closed
            if (RQST_DTL_SHORTAGE_CLOSED.equals(cpoDtlBean.getXxRqstTpCd())) {
                if (updRtrnDtlTMsg != null) {
                    // 2019/03/29 QC#30700 Mod Start
//                    ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.CLOSED);
                    ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.rtrnLineStsCd, RTRN_LINE_STS.RECEIVED);
                    ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.cpoDtlHldFlg, ZYPConstant.FLG_OFF_N);
//                    ZYPEZDItemValueSetter.setValue(updRtrnDtlTMsg.openFlg, ZYPConstant.FLG_OFF_N);
                     // 2019/03/29 QC#30700 Mod End
                    setPhysicalDisplayStatusCd(updRtrnDtlTMsg, cpoTMsg);

                    S21ApiTBLAccessor.update(updRtrnDtlTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updRtrnDtlTMsg.getReturnCode())) {
                        setErrMsgIdForCpoDtl(NWZM1598E, linePMsgList, idx);
                        return;
                    }
                }
            }
            // mod end 2016/07/26 CSA S21_NA#8854
        }
    }

    /**
     * updateDsCpoRtrnDtl
     * @param cpoDtlBean NWZC153001DetailBean
     * @param rtrnDtlTMsg NWZC15300DS_CPO_RTRN_DTLTMsg1PMsg
     * @param linePMsgList List<NWZC153002PMsg>
     * @param idx int
     */
    private boolean updateDsCpoRtrnDtl(NWZC153001DetailBean cpoDtlBean, DS_CPO_RTRN_DTLTMsg rtrnDtlTMsg, List<NWZC153002PMsg> linePMsgList, int idx) {
        addCommoncpoDtlBeanTMsg(cpoDtlBean, rtrnDtlTMsg);

        // cacheDBAccess.updateWithCache(rtrnDtlTMsg); 2016/04/19 S21_NA#5394 Del
        S21ApiTBLAccessor.update(rtrnDtlTMsg); // 2016/04/19 S21_NA#5394 Add
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtrnDtlTMsg.getReturnCode())) {
            setErrMsgIdForCpoDtl(NWZM1598E, linePMsgList, idx);
            return false;
        }
        return true;
    }

    /**
     * insertDsCpoRtrnDtl
     * @param cpoDtlBean NWZC153001DetailBean
     * @param rtrnDtlTMsg NWZC15300DS_CPO_RTRN_DTLTMsg1PMsg
     * @param linePMsgList List<NWZC153002PMsg>
     * @param idx int
     */
    private boolean insertDsCpoRtrnDtl(NWZC153001DetailBean cpoDtlBean, DS_CPO_RTRN_DTLTMsg rtrnDtlTMsg, List<NWZC153002PMsg> linePMsgList, int idx) {
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.glblCmpyCd, cpoDtlBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.cpoOrdNum, cpoDtlBean.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dsCpoRtrnLineNum, cpoDtlBean.getDsCpoRtrnLineNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dsCpoRtrnLineSubNum, cpoDtlBean.getDsCpoRtrnLineSubNum());

        addCommoncpoDtlBeanTMsg(cpoDtlBean, rtrnDtlTMsg);

        // cacheDBAccess.insertWithCache(rtrnDtlTMsg); 2016/04/19 S21_NA#5394 Del
        S21ApiTBLAccessor.insert(rtrnDtlTMsg); // 2016/04/19 S21_NA#5394 Add
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtrnDtlTMsg.getReturnCode())) {
            setErrMsgIdForCpoDtl(NWZM1597E, linePMsgList, idx);
            return false;
        }
        return true;
    }

    /**
     * addCommoncpoDtlBeanTMsg
     * @param cpoDtlBean NWZC153001DetailBean
     * @param rtrnDtlTMsg DS_CPO_RTRN_DTLTMsg
     */
    private void addCommoncpoDtlBeanTMsg(NWZC153001DetailBean cpoDtlBean, DS_CPO_RTRN_DTLTMsg rtrnDtlTMsg) {
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.cpoOrdTpCd, cpoDtlBean.getCpoOrdTpCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.mdseCd, cpoDtlBean.getMdseCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.mdseNm, cpoDtlBean.getMdseNm()); // 2015/12/17 S21_NA#2007 Add
        // S21_NA#7356
        if (ZYPCommonFunc.hasValue(cpoDtlBean.getOrigMdseCd())) {
            ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.origMdseCd, cpoDtlBean.getOrigMdseCd());
            ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.origMdseNm, cpoDtlBean.getOrigMdseNm());
        } else {
            ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.origMdseCd, cpoDtlBean.getMdseCd());
        }
        // QC#26415 2018/06/07 Add Start
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.origMdseCd, cpoDtlBean.getOrigMdseCd());
        // QC#26415 2018/06/07 Add End
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.setMdseCd, cpoDtlBean.getSetMdseCd()); // S21_NA#4374
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dropShipFlg, cpoDtlBean.getDropShipFlg());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToCustCd, cpoDtlBean.getShipToCustCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToLocNm, cpoDtlBean.getShipToLocNm());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToAddlLocNm, cpoDtlBean.getShipToAddlLocNm());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToFirstLineAddr, cpoDtlBean.getShipToFirstLineAddr());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToScdLineAddr, cpoDtlBean.getShipToScdLineAddr());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToThirdLineAddr, cpoDtlBean.getShipToThirdLineAddr());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToFrthLineAddr, cpoDtlBean.getShipToFrthLineAddr());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToCtyAddr, cpoDtlBean.getShipToCtyAddr());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToStCd, cpoDtlBean.getShipToStCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToProvNm, cpoDtlBean.getShipToProvNm());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToPostCd, cpoDtlBean.getShipToPostCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToCtryCd, cpoDtlBean.getShipToCtryCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToCntyNm, cpoDtlBean.getShipToCntyNm());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToFirstRefCmntTxt, cpoDtlBean.getShipToFirstRefCmntTxt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.shipToScdRefCmntTxt, cpoDtlBean.getShipToScdRefCmntTxt());
        // 2018/08/07 S21_NA#25404 Add Start
        BigDecimal beforeOrdQty = rtrnDtlTMsg.ordQty.getValue();
        // 2018/08/07 S21_NA#25404 Add End
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.ordQty, cpoDtlBean.getOrdQty());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.ordCustUomQty, cpoDtlBean.getOrdCustUomQty());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.invtyLocCd, cpoDtlBean.getInvtyLocCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.entDealNetUnitPrcAmt, cpoDtlBean.getEntDealNetUnitPrcAmt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.entCpoDtlDealNetAmt, cpoDtlBean.getCpoDtlDealNetAmt()); // 2015/12/17 S21_NA#2007 Add
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.entCpoDtlDealSlsAmt, cpoDtlBean.getCpoDtlDealSlsAmt()); // 2015/12/17 S21_NA#2007 Add

        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.entFuncNetUnitPrcAmt, cpoDtlBean.getEntFuncNetUnitPrcAmt()); // 2015/12/17 S21_NA#2007 Add
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.entCpoDtlFuncNetAmt, cpoDtlBean.getCpoDtlFuncNetAmt()); // 2015/12/17 S21_NA#2007 Add
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.entCpoDtlFuncSlsAmt, cpoDtlBean.getCpoDtlFuncSlsAmt()); // 2015/12/17 S21_NA#2007 Add

        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.cpoDtlDealNetAmt, cpoDtlBean.getCpoDtlDealNetAmt()); // 2015/12/17 S21_NA#2007 Add
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.cpoDtlDealSlsAmt, cpoDtlBean.getCpoDtlDealSlsAmt()); // 2015/12/17 S21_NA#2007 Add

        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.cpoDtlFuncNetAmt, cpoDtlBean.getCpoDtlFuncNetAmt()); // 2015/12/17 S21_NA#2007 Add
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.cpoDtlFuncSlsAmt, cpoDtlBean.getCpoDtlFuncSlsAmt()); // 2015/12/17 S21_NA#2007 Add

        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.ccyCd, cpoDtlBean.getCcyCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.rqstPickUpDt, cpoDtlBean.getRqstPickUpDt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.stkStsCd, cpoDtlBean.getStkStsCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.pmtTermCashDiscCd, cpoDtlBean.getPmtTermCashDiscCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.pmtTermCd, cpoDtlBean.getPmtTermCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.cashDiscTermCd, cpoDtlBean.getCashDiscTermCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.slsRepOrSlsTeamTocCd, cpoDtlBean.getSlsRepOrSlsTeamTocCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.cpoDtlCancFlg, cpoDtlBean.getCpoDtlCancFlg());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.custMdseCd, cpoDtlBean.getCustMdseCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.custUomCd, cpoDtlBean.getCustUomCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.manPrcFlg, cpoDtlBean.getManPrcFlg());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.taxFlg, cpoDtlBean.getTaxFlg());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.machConfigNum, cpoDtlBean.getMachConfigNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dsOrdPosnNum, cpoDtlBean.getDsOrdPosnNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.svcConfigMstrPk, cpoDtlBean.getSvcConfigMstrPk());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.unitNetWt, cpoDtlBean.getUnitNetWt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dsContrNum, cpoDtlBean.getDsContrNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dsContrSqNum, cpoDtlBean.getDsContrSqNum());
        // 2016/10/07 S21_NA#9205 Del Start
//        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.svcMachMstrPk, cpoDtlBean.getSvcMachMstrPk());
        // 2016/10/07 S21_NA#9205 Del end
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dsCpoConfigPk, cpoDtlBean.getDsCpoConfigPk());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dsCpoLineNum, cpoDtlBean.getDsCpoLineNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dsCpoLineSubNum, cpoDtlBean.getDsCpoLineSubNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dsOrdLineCatgCd, cpoDtlBean.getDsOrdLineCatgCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.ordLineSrcCd, cpoDtlBean.getOrdLineSrcCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.rtlWhCd, cpoDtlBean.getRtlWhCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.rtlSwhCd, cpoDtlBean.getRtlSwhCd());
        // 2016/10/07 S21_NA#9205 Mod Start
//        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.serNum, cpoDtlBean.getSerNum());
        if (ZYPCommonFunc.hasValue(cpoDtlBean.getSerNum())) {
//            rtrnDtlTMsg.serNum.clear();
//            rtrnDtlTMsg.svcMachMstrPk.clear();
//        } else {
            if (!cpoDtlBean.getSerNum().equals(rtrnDtlTMsg.serNum.getValue())) {
                ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.serNum, cpoDtlBean.getSerNum());
                // QC#16575 2017/03/01 UPD START
                // SVC_MACH_MSTRTMsgArray svcMachMstrTmsgAry = (SVC_MACH_MSTRTMsgArray) getSvcMachMstrTMsg(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getSerNum());
                SVC_MACH_MSTRTMsgArray svcMachMstrTmsgAry = (SVC_MACH_MSTRTMsgArray) getSvcMachMstrTMsg(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getSerNum(), cpoDtlBean.getMdseCd());
                // QC#16575 2017/03/01 UPD E N D
                if (svcMachMstrTmsgAry.length() > 0) {
                    ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.svcMachMstrPk, svcMachMstrTmsgAry.no(0).svcMachMstrPk);
                    cpoDtlBean.setSvcMachMstrPk(svcMachMstrTmsgAry.no(0).svcMachMstrPk.getValue());
                }
            }
        } else {
            if (ZYPCommonFunc.hasValue(cpoDtlBean.getSvcMachMstrPk())) {
                ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.svcMachMstrPk, cpoDtlBean.getSvcMachMstrPk());
            } else {
                rtrnDtlTMsg.svcMachMstrPk.clear();
            }
        }
        // 2016/12/15 S21_NA#16588 Mod end
        // 2016/10/07 S21_NA#9205 Mod end
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.flPrcListCd, cpoDtlBean.getFlPrcListCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dealPrcListPrcAmt, cpoDtlBean.getDealPrcListPrcAmt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.funcPrcListPrcAmt, cpoDtlBean.getFuncPrcListPrcAmt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.refCpoDtlLineNum, cpoDtlBean.getRefCpoDtlLineNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.refCpoDtlLineSubNum, cpoDtlBean.getRefCpoDtlLineSubNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dplyLineRefNum, cpoDtlBean.getDplyLineRefNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.crRebilCd, cpoDtlBean.getCrRebilCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.prcBaseDt, cpoDtlBean.getPrcBaseDt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.prcCatgCd, cpoDtlBean.getPrcCatgCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.csmpContrNum, cpoDtlBean.getCsmpContrNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dlrRefNum, cpoDtlBean.getDlrRefNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.csmpPrcListCd, cpoDtlBean.getCsmpPrcListCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.bllgAttrbCustAcctCd, cpoDtlBean.getBllgAttrbCustAcctCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.firstBllgAttrbNm, cpoDtlBean.getFirstBllgAttrbNm());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.firstBllgAttrbValTxt, cpoDtlBean.getFirstBllgAttrbValTxt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.scdBllgAttrbNm, cpoDtlBean.getScdBllgAttrbNm());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.scdBllgAttrbValTxt, cpoDtlBean.getScdBllgAttrbValTxt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.thirdBllgAttrbNm, cpoDtlBean.getThirdBllgAttrbNm());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.thirdBllgAttrbValTxt, cpoDtlBean.getThirdBllgAttrbValTxt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.frthBllgAttrbNm, cpoDtlBean.getFrthBllgAttrbNm());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.frthBllgAttrbValTxt, cpoDtlBean.getFrthBllgAttrbValTxt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.fifthBllgAttrbNm, cpoDtlBean.getFifthBllgAttrbNm());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.fifthBllgAttrbValTxt, cpoDtlBean.getFifthBllgAttrbValTxt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.sixthBllgAttrbNm, cpoDtlBean.getSixthBllgAttrbNm());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.sixthBllgAttrbValTxt, cpoDtlBean.getSixthBllgAttrbValTxt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.ordSrcRefLineNum, cpoDtlBean.getOrdSrcRefLineNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.ordSrcRefLineSubNum, cpoDtlBean.getOrdSrcRefLineSubNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.baseCmptFlg, cpoDtlBean.getBaseCmptFlg());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.trxCd, cpoDtlBean.getTrxCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.trxRsnCd, cpoDtlBean.getTrxRsnCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.funcSvcRevTrnsfAmt, cpoDtlBean.getFuncSvcRevTrnsfAmt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dealSvcRevTrnsfAmt, cpoDtlBean.getDealSvcRevTrnsfAmt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.funcSvcCostTrnsfAmt, cpoDtlBean.getFuncSvcCostTrnsfAmt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dealSvcCostTrnsfAmt, cpoDtlBean.getDealSvcCostTrnsfAmt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.funcManFlAdjAmt, cpoDtlBean.getFuncManFlAdjAmt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dealManFlAdjAmt, cpoDtlBean.getDealManFlAdjAmt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.funcManRepRevAdjAmt, cpoDtlBean.getFuncManRepRevAdjAmt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.dealManRepRevAdjAmt, cpoDtlBean.getDealManRepRevAdjAmt());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.ordUpldRefNum, cpoDtlBean.getOrdUpldRefNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.hddRmvCd, cpoDtlBean.getHddRmvCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.rtrnRsnCd, cpoDtlBean.getRtrnRsnCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.thirdPtyDspTpCd, cpoDtlBean.getThirdPtyDspTpCd());

        // Mod Start 2019/04/03 QC#31018
        //ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.rtrnQty, cpoDtlBean.getRtrnQty());
        if (!RQST_DTL_MOD.equals(cpoDtlBean.getXxRqstTpCd())) {
            ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.rtrnQty, cpoDtlBean.getRtrnQty());
        }
        // Mod End 2019/04/03 QC#31018

        // 2015/12/17 S21_NA#2007 Add Start
        if (isCancelDtl(cpoDtlBean)) {
            // 2018/08/07 S21_NA#25404 Mod Start
//            ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.cancQty, cpoDtlBean.getOrdQty());
            ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.cancQty, rtrnDtlTMsg.cancQty.getValue().add(cpoDtlBean.getOrdQty()));
            // 2018/08/07 S21_NA#25404 Mod End
        } else {
            // 2018/08/07 S21_NA#25404 Mod Start
//            ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.cancQty, BigDecimal.ZERO);
            if (ZYPCommonFunc.hasValue(cpoDtlBean.getChngRsnTpCd())) {
                BigDecimal cancQty = beforeOrdQty.subtract(cpoDtlBean.getOrdQty());
                ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.cancQty, rtrnDtlTMsg.cancQty.getValue().add(cancQty));
            } else if (rtrnDtlTMsg.cancQty.getValue() == null) {
                rtrnDtlTMsg.cancQty.setValue(BigDecimal.ZERO);
            }
            // 2018/08/07 S21_NA#25404 Mod End
        }
        // 2015/12/17 S21_NA#2007 Add End
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.rtrnRsnCd, cpoDtlBean.getRtrnRsnCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.thirdPtyDspTpCd, cpoDtlBean.getThirdPtyDspTpCd());

        Set<EZDTStringItem> flgItemList = new HashSet<EZDTStringItem>();
        flgItemList.add(rtrnDtlTMsg.baseCmptFlg);
        flgItemList.add(rtrnDtlTMsg.dropShipFlg);
        flgItemList.add(rtrnDtlTMsg.taxFlg);
        flgItemList.add(rtrnDtlTMsg.submtFlg);
        flgItemList.add(rtrnDtlTMsg.openFlg);
        flgItemList.add(rtrnDtlTMsg.cpoDtlCancFlg);
        flgItemList.add(rtrnDtlTMsg.cpoDtlHldFlg);
        flgItemList.add(rtrnDtlTMsg.manPrcFlg);
        flgItemList.add(rtrnDtlTMsg.manPmtFlg);
        flgItemList.add(rtrnDtlTMsg.convCompFlg);
        for (EZDTStringItem flgItem : flgItemList) {
            if (!hasValue(flgItem)) {
                ZYPEZDItemValueSetter.setValue(flgItem, ZYPConstant.FLG_OFF_N);
            }
        }

        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.origCpoOrdNum, cpoDtlBean.getOrigCpoOrdNum()); // 2016/04/20 S21_NA#7349 Add
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.origCpoDtlLineNum, cpoDtlBean.getOrigCpoDtlLineNum()); // S21_NA#7606
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.origCpoDtlLineSubNum, cpoDtlBean.getOrigCpoDtlLineSubNum()); // S21_NA#7606

        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.origInvNum, cpoDtlBean.getOrigInvNum()); // S21_NA#CreditRebill Req

        // Add Start 2017/10/16 QC#21507
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.cpoSrcTpCd, cpoDtlBean.getCpoSrcTpCd());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.ordSrcRefNum, cpoDtlBean.getOrdSrcRefNum());
        // Add End 2017/10/16 QC#21507

        // 2018/01/11 S21_NA#22372 Add Start
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.funcUnitFlPrcAmt, cpoDtlBean.getFuncUnitFlPrcAmt());
        // 2018/01/11 S21_NA#22372 Add End
        // 2018/08/14 S21_NA#27718 Add Start
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.origInvLineNum, cpoDtlBean.getOrigInvLineNum());
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.origInvLineSubNum, cpoDtlBean.getOrigInvLineSubNum());
        // 2018/08/14 S21_NA#27718 Add End
        ZYPEZDItemValueSetter.setValue(rtrnDtlTMsg.origInvtyLocCd, cpoDtlBean.getOrigInvyLocCd()); // 2018/11/15 S21_NA#27299 Add
    }

    // 2016/07/06 S21_NA#7755 Add Start
    // 2016/09/27 S21_NA#9102 Del Start
//    private void updateBaseCmptFlg(NWZC153001PMsg pMsg, NWZC153001CpoBean cpoBean, final List<NWZC153002PMsg> linePMsgList) {
//
//        if (!isOrderRetailEquipment(cpoBean)) {
//            return;
//        }
//
//        DS_CPO_RTRN_DTLTMsg updTMsgKey = new DS_CPO_RTRN_DTLTMsg();
//
//        updTMsgKey.setSQLID("001");
//        updTMsgKey.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
//        updTMsgKey.setConditionValue("cpoOrdNum01", cpoBean.getCpoOrdNum());
//
//        DS_CPO_RTRN_DTLTMsgArray cpoRtrnDtlTMsgArray = (DS_CPO_RTRN_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(updTMsgKey);
//        if (cpoRtrnDtlTMsgArray == null || cpoRtrnDtlTMsgArray.getValidCount() == 0) {
//            return;
//        }
//
//        LinkedHashMap<String, List<DS_CPO_RTRN_DTLTMsg>> cpoConfigMap = createConfigMap(cpoRtrnDtlTMsgArray);
//        DS_CPO_RTRN_DTLTMsg baseCmptFlgTMsg;
//        for (List<DS_CPO_RTRN_DTLTMsg> cpoRtrnDtlList : cpoConfigMap.values()) {
//            baseCmptFlgTMsg = getBaseCmptFlg(cpoRtrnDtlList);
//
//            if (baseCmptFlgTMsg != null) {
//                updateCpoRtrnDtlBaseCmptFlg(cpoRtrnDtlList, baseCmptFlgTMsg, linePMsgList);
//            }
//        }
//    }
    // 2016/09/27 S21_NA#9102 Del End
    // 2016/09/27 S21_NA#9102 Add Start
    private void updateBaseCmptFlg(NWZC153001PMsg pMsg, NWZC153001CpoBean cpoBean) {

        // Add Start 2017/02/01 QC#17256
        clearBaseCmptFlg(cpoBean);
        // Add End 2017/02/01 QC#17256

        if (!isOrderRetailEquipment(cpoBean)) {
            // 2016/09/27 S21_NA#9102 Add Start
            // Del Start 2017/02/01 QC#17256
//            clearBaseCmptFlg(cpoBean);
            // Del End 2017/02/01 QC#17256

            LinkedHashMap<String, List<NWZC153001DetailBean>> cpoConfigMap = createConfigMap(cpoBean);
            for (List<NWZC153001DetailBean> cpoDtlBeanList : cpoConfigMap.values()) {
                setDefaultRefNumAndSubWh(cpoDtlBeanList, null, pMsg.glblCmpyCd.getValue()); // 2018/07/12 S21_NA#26895-2 Add glblCmpyCd
            }
            // 2016/09/27 S21_NA#9102 Add End
            return;
        }

        LinkedHashMap<String, List<NWZC153001DetailBean>> cpoConfigMap = createConfigMap(cpoBean);
        // Add Start 2017/02/02 QC#17349
        String glblCmpyCd = cpoBean.getGlblCmpyCd();
        String dsOrdCatgCd = cpoBean.getDsOrdCatgCd();
        String dsOrdTpCd = cpoBean.getDsOrdTpCd();
        // Add End 2017/02/02 QC#17349
        for (List<NWZC153001DetailBean> cpoDtlBeanList : cpoConfigMap.values()) {
            // Add Start 2017/02/02 QC#17349
            BigDecimal svcConfigMstrPk = null;
            String configTCd = null;
            String postCd = null;
            if (cpoDtlBeanList.size() > 0) {
                svcConfigMstrPk = cpoDtlBeanList.get(0).getSvcConfigMstrPk();
                configTCd = cpoDtlBeanList.get(0).getConfigTpCd();
                postCd = cpoDtlBeanList.get(0).getShipToPostCd();
            }
            // Add End 2017/02/02 QC#17349
            // Mod Start 2017/02/02 QC#17349
//            NWZC153001DetailBean baseCmptCpoDtlBean = getBaseCmptFlg(cpoDtlBeanList);
            NWZC153001DetailBean baseCmptCpoDtlBean = getBaseCmptFlg(cpoDtlBeanList, glblCmpyCd, configTCd, dsOrdCatgCd, dsOrdTpCd, postCd, svcConfigMstrPk);
            // Mod End 2017/02/02 QC#17349

            setDefaultRefNumAndSubWh(cpoDtlBeanList, baseCmptCpoDtlBean, pMsg.glblCmpyCd.getValue());  // 2018/07/12 S21_NA#26895-2 Add glblCmpyCd
        }
    }
    // 2016/09/27 S21_NA#9102 Add End

    private boolean isOrderRetailEquipment(NWZC153001CpoBean cpoBean) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        params.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);
        params.put("dsOrdCatgCd", cpoBean.getDsOrdCatgCd());
        params.put("dsOrdTpCd", cpoBean.getDsOrdTpCd());
        params.put("dsOrdRsnCd", cpoBean.getDsOrdRsnCd());

        Integer result = (Integer) ssmClient.queryObject("isExistOrdCatg", params);

        return 0 < result;

    }

    // 2017/02/15 S21_NA#17207 Add Start
    private boolean isOrderSuppliesOrder(NWZC153001CpoBean cpoBean) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        params.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        params.put("dsOrdCatgCd", cpoBean.getDsOrdCatgCd());
        params.put("dsOrdTpCd", cpoBean.getDsOrdTpCd());
        params.put("dsOrdRsnCd", cpoBean.getDsOrdRsnCd());

        Integer result = (Integer) ssmClient.queryObject("isExistOrdCatg", params);

        return 0 < result;

    }
    // 2017/02/15 S21_NA#17207 Add End

    // 2016/09/27 S21_NA#9102 Mod Start
//    private LinkedHashMap<String, List<DS_CPO_RTRN_DTLTMsg>> createConfigMap(DS_CPO_RTRN_DTLTMsgArray cpoRtrnDtlTMsgArray) {
//        LinkedHashMap<String, List<DS_CPO_RTRN_DTLTMsg>> cpoConfigMap = new LinkedHashMap<String, List<DS_CPO_RTRN_DTLTMsg>> ();
//        List<DS_CPO_RTRN_DTLTMsg> cpoRtrnDtlList;
//        DS_CPO_RTRN_DTLTMsg cpoRtrnDtlTMsg;
//        String dsOrdPosnNum;
//
//        for (int i=0; i < cpoRtrnDtlTMsgArray.length(); i++ ) {
//            cpoRtrnDtlTMsg = cpoRtrnDtlTMsgArray.no(i);
//
//            dsOrdPosnNum = cpoRtrnDtlTMsg.dsOrdPosnNum.getValue();
//
//            if (!cpoConfigMap.containsKey(dsOrdPosnNum)) {
//                cpoRtrnDtlList = new ArrayList<DS_CPO_RTRN_DTLTMsg>();
//                cpoConfigMap.put(dsOrdPosnNum, cpoRtrnDtlList);
//            }
//            cpoRtrnDtlList = cpoConfigMap.get(dsOrdPosnNum);
//            cpoRtrnDtlList.add(cpoRtrnDtlTMsg);
//        }
//
//        return cpoConfigMap;
//    }

    private LinkedHashMap<String, List<NWZC153001DetailBean>> createConfigMap(NWZC153001CpoBean cpoBean) {

        LinkedHashMap<String, List<NWZC153001DetailBean>> cpoConfigMap = new LinkedHashMap<String, List<NWZC153001DetailBean>> ();
        List<NWZC153001DetailBean> cpoRtrnDtlList = null;
        String dsOrdPosnNum;

        List<NWZC153001DetailBean> cpoDtlBeanList = cpoBean.getDtlBeanList();
        for (NWZC153001DetailBean cpoDtlBean : cpoDtlBeanList) {
            dsOrdPosnNum = cpoDtlBean.getDsOrdPosnNum();

            if (!cpoConfigMap.containsKey(dsOrdPosnNum)) {
                cpoRtrnDtlList = new ArrayList<NWZC153001DetailBean>();
                cpoConfigMap.put(dsOrdPosnNum, cpoRtrnDtlList);
            }
            cpoRtrnDtlList = cpoConfigMap.get(dsOrdPosnNum);
            
            cpoRtrnDtlList.add(cpoDtlBean);
        }

        return cpoConfigMap;
    }
    // 2016/09/27 S21_NA#9102 Mod End

    // 2016/09/27 S21_NA#9102 Mod Start
//    private DS_CPO_RTRN_DTLTMsg getBaseCmptFlg(List<DS_CPO_RTRN_DTLTMsg> cpoRtrnDtlList) {
//
//        DS_CPO_RTRN_DTLTMsg baseCmptFlgTMsg = null;
//        MDSETMsg mdse;
//        String instlBaseCtrlFlg, mdseTpCtxTpCd;
//
//        for (DS_CPO_RTRN_DTLTMsg cpoRtrnDtl : cpoRtrnDtlList ) {
//            mdse = NWXMdseTMsgThreadLocalCache.getInstance().get(cpoRtrnDtl.glblCmpyCd.getValue(), cpoRtrnDtl.mdseCd.getValue());
//
//            if (mdse == null) {
//                continue;
//            }
//            if (S21StringUtil.isEquals(mdse.mdseTpCd.getValue(), MDSE_TP.SET)) {
//                continue;
//            }
//
//            // get base component flag
//            Map<String, Object> ssmParam = new HashMap<String, Object>();
//            ssmParam.put("glblCmpyCd", cpoRtrnDtl.glblCmpyCd.getValue());
//            ssmParam.put("mdseCd", mdse.mdseCd.getValue());
//            ssmParam.put("mdseTpCtxTpCd", "MAIN_MACH");
//            Map<?, ?> baseComponentFlag = (Map<?, ?>) ssmClient.queryObject("getBaseComponentFlag", ssmParam);
//            if (baseComponentFlag == null) {
//                continue;
//            }
//
//            instlBaseCtrlFlg = (String) baseComponentFlag.get("INSTL_BASE_CTRL_FLG");
//            mdseTpCtxTpCd = (String) baseComponentFlag.get("MDSE_TP_CTX_TP_CD");
//
//            if (S21StringUtil.isNotEmpty(mdseTpCtxTpCd)) {
//
//                // main machine
//                return cpoRtrnDtl;
//            }
//
//            if (baseCmptFlgTMsg != null) {
//                continue;
//            }
//
//            if (S21StringUtil.isEquals(instlBaseCtrlFlg, ZYPConstant.FLG_ON_Y)) {
//
//                // first install base
//                baseCmptFlgTMsg = cpoRtrnDtl;
//            }
//        }
//
//        return baseCmptFlgTMsg;
//    }

    private NWZC153001DetailBean getBaseCmptFlg(List<NWZC153001DetailBean> cpoDtlBeanList, String glblCmpyCd, String configTCd, String dsOrdCatgCd, String dsOrdTpCd, String postCd, BigDecimal svcConfigMstrPk) {

        NWZC153001DetailBean baseCmptFlgCpoDtlBean = null;
        MDSETMsg mdse;
        String instlBaseCtrlFlg = null;
        String mdseTpCtxTpCd = null;
        // Add Start 2017/02/02 QC#17349
        String mdseCd = null;
        String slsDt = null;
        String dsRtrnRsnCd = null;
        BigDecimal ordQty = null;
        BigDecimal svcMachMstrPk = null;
        String rtlWhCd = null;
        String serNum = null;
        boolean isDefWhRequested = true;
        boolean isBaseCompornent = false;

        if (getConfigExist(glblCmpyCd, configTCd)) {
            baseCmptFlgCpoDtlBean = new NWZC153001DetailBean();
            NWZC153001DetailBean notBaseCmptFlgCpoDtlBean = null;

            for (NWZC153001DetailBean cpoDtlBean : cpoDtlBeanList) {

                // Set Paramater
                slsDt = ZYPDateUtil.getSalesDate();
                dsRtrnRsnCd = cpoDtlBean.getRtrnRsnCd();
                mdseCd = cpoDtlBean.getMdseCd();
                ordQty = cpoDtlBean.getOrdQty();
                svcMachMstrPk = cpoDtlBean.getSvcMachMstrPk();
                rtlWhCd = cpoDtlBean.getRtlWhCd();
                serNum = cpoDtlBean.getSerNum();

                Map<String, Object> getBaseCmpnt = NWXC150001DsCheck.getBaseComponentInfoForRma(//
                        glblCmpyCd, slsDt, dsOrdCatgCd, dsOrdTpCd, dsRtrnRsnCd, postCd, mdseCd, ordQty, //
                        svcConfigMstrPk, svcMachMstrPk, rtlWhCd, serNum, isDefWhRequested);

                // Check Base Compornent Flag
                if (S21StringUtil.isEquals((String) getBaseCmpnt.get("BASE_CMPT_FLG"), ZYPConstant.FLG_ON_Y)) {
                    isBaseCompornent = true;
                    baseCmptFlgCpoDtlBean.setBaseCmptFlg((String) getBaseCmpnt.get("BASE_CMPT_FLG"));
                    if (!ZYPCommonFunc.hasValue(cpoDtlBean.getSvcMachMstrPk())) { // 2018/03/28 S21_NA#25097 Mod (Add condition)
                        baseCmptFlgCpoDtlBean.setSvcMachMstrPk((BigDecimal) getBaseCmpnt.get("SVC_MACH_MSTR_PK"));
                    } else {
                        baseCmptFlgCpoDtlBean.setSvcMachMstrPk(cpoDtlBean.getSvcMachMstrPk());
                    } // 2018/03/28 S21_NA#25097 Mod (Add condition)
                    if (!ZYPCommonFunc.hasValue(cpoDtlBean.getRtlWhCd()) //
                            || !ZYPCommonFunc.hasValue(cpoDtlBean.getRtlSwhCd())) { // 2018/03/28 S21_NA#25097 Mod (Add condition)
                        baseCmptFlgCpoDtlBean.setRtlWhCd((String) getBaseCmpnt.get("RTL_WH_CD"));
                        baseCmptFlgCpoDtlBean.setRtlSwhCd((String) getBaseCmpnt.get("RTL_SWH_CD"));
                    } else {
                        baseCmptFlgCpoDtlBean.setRtlWhCd(cpoDtlBean.getRtlWhCd());
                        baseCmptFlgCpoDtlBean.setRtlSwhCd(cpoDtlBean.getRtlSwhCd());
                    } // 2018/03/28 S21_NA#25097 Mod (Add condition)
                    baseCmptFlgCpoDtlBean.setThirdPtyDspTpCd((String) getBaseCmpnt.get("THIRD_PTY_DSP_TP_CD"));
                    baseCmptFlgCpoDtlBean.setDsOrdPosnNum(cpoDtlBean.getDsOrdPosnNum());
                    baseCmptFlgCpoDtlBean.setDsCpoLineNum(cpoDtlBean.getDsCpoLineNum());
                    baseCmptFlgCpoDtlBean.setDsCpoLineSubNum(cpoDtlBean.getDsCpoLineSubNum());
                    // Add Start 2017/07/03 QC#19709
                    if (!ZYPCommonFunc.hasValue(cpoDtlBean.getRtlWhCd()) //
                            || !ZYPCommonFunc.hasValue(cpoDtlBean.getRtlSwhCd())) { // 2018/03/28 S21_NA#25097 Mod (Add condition)
                        baseCmptFlgCpoDtlBean.setInvtyLocCd((String) getBaseCmpnt.get("INVTY_LOC_CD"));
                    } else {
                        baseCmptFlgCpoDtlBean.setInvtyLocCd(cpoDtlBean.getInvtyLocCd());
                    } // 2018/03/28 S21_NA#25097 Mod (Add condition)
                    // Add End 2017/07/03 QC#19709
                    cpoDtlBean.setBaseCmptFlg(ZYPConstant.FLG_ON_Y);
                }

                // Check Service Machine Master PK
                if (!ZYPCommonFunc.hasValue(cpoDtlBean.getSvcMachMstrPk())) {
                    cpoDtlBean.setSvcMachMstrPk((BigDecimal) getBaseCmpnt.get("SVC_MACH_MSTR_PK_FOR_COMPONENT"));
                }

                if (isDefWhRequested && ZYPCommonFunc.hasValue((String) getBaseCmpnt.get("RTL_WH_CD"))) {
                    if (null == notBaseCmptFlgCpoDtlBean) {
                        notBaseCmptFlgCpoDtlBean = new NWZC153001DetailBean();
                    }
                    if (!ZYPCommonFunc.hasValue(cpoDtlBean.getRtlWhCd()) //
                            || !ZYPCommonFunc.hasValue(cpoDtlBean.getRtlSwhCd())) { // 2018/03/28 S21_NA#25097 Mod (Add condition)
                        notBaseCmptFlgCpoDtlBean.setRtlWhCd((String) getBaseCmpnt.get("RTL_WH_CD"));
                        notBaseCmptFlgCpoDtlBean.setRtlSwhCd((String) getBaseCmpnt.get("RTL_SWH_CD"));
                    } else {
                        notBaseCmptFlgCpoDtlBean.setRtlWhCd(cpoDtlBean.getRtlWhCd());
                        notBaseCmptFlgCpoDtlBean.setRtlSwhCd(cpoDtlBean.getRtlSwhCd());
                    } // 2018/03/28 S21_NA#25097 Mod (Add condition)
                    notBaseCmptFlgCpoDtlBean.setThirdPtyDspTpCd((String) getBaseCmpnt.get("THIRD_PTY_DSP_TP_CD"));
                    // Add Start 2017/07/03 QC#19709
                    if (!ZYPCommonFunc.hasValue(cpoDtlBean.getRtlWhCd()) //
                            || !ZYPCommonFunc.hasValue(cpoDtlBean.getRtlSwhCd())) { // 2018/03/28 S21_NA#25097 Mod (Add condition)
                        notBaseCmptFlgCpoDtlBean.setInvtyLocCd((String) getBaseCmpnt.get("INVTY_LOC_CD"));
                    } else {
                        notBaseCmptFlgCpoDtlBean.setInvtyLocCd(cpoDtlBean.getInvtyLocCd());
                    }
                    // Add End 2017/07/03 QC#19709
                    isDefWhRequested = false;
                }
            }

            if (isBaseCompornent) {
                return baseCmptFlgCpoDtlBean;
            } else {
                return notBaseCmptFlgCpoDtlBean;
            }
        }
        // Add End 2017/02/02 QC#17349

        for (NWZC153001DetailBean cpoDtlBean : cpoDtlBeanList) {
            mdse = NWXMdseTMsgThreadLocalCache.getInstance().get(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getMdseCd());

            if (mdse == null) {
                continue;
            }
            if (S21StringUtil.isEquals(mdse.mdseTpCd.getValue(), MDSE_TP.SET)) {
                continue;
            }

            // get base component flag
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", cpoDtlBean.getGlblCmpyCd());
            ssmParam.put("mdseCd", mdse.mdseCd.getValue());
            ssmParam.put("mdseTpCtxTpCd", "MAIN_MACH");
            Map<?, ?> baseComponentFlag = (Map<?, ?>) ssmClient.queryObject("getBaseComponentFlag", ssmParam);
            if (baseComponentFlag == null) {
                continue;
            }

            instlBaseCtrlFlg = (String) baseComponentFlag.get("INSTL_BASE_CTRL_FLG");
            mdseTpCtxTpCd = (String) baseComponentFlag.get("MDSE_TP_CTX_TP_CD");

            if (S21StringUtil.isNotEmpty(mdseTpCtxTpCd)) {

                // main machine
                cpoDtlBean.setBaseCmptFlg(ZYPConstant.FLG_ON_Y);
                cpoDtlBean.setDplyLineRefNum(null);
                return cpoDtlBean;
            }

            if (baseCmptFlgCpoDtlBean != null) {
                continue;
            }

            if (S21StringUtil.isEquals(instlBaseCtrlFlg, ZYPConstant.FLG_ON_Y)) {

                // first install base
                baseCmptFlgCpoDtlBean = cpoDtlBean;
            }
        }

        if (baseCmptFlgCpoDtlBean != null) {
            baseCmptFlgCpoDtlBean.setBaseCmptFlg(ZYPConstant.FLG_ON_Y);
            baseCmptFlgCpoDtlBean.setDplyLineRefNum(null);
        }

        return baseCmptFlgCpoDtlBean;
    }
    // 2016/09/27 S21_NA#9102 Mod End

    // 2016/09/27 S21_NA#9102 Del Start
//    private boolean updateCpoRtrnDtlBaseCmptFlg(List<DS_CPO_RTRN_DTLTMsg> cpoRtrnDtList, DS_CPO_RTRN_DTLTMsg baseCmptTMsg, final List<NWZC153002PMsg> linePMsgList) {
//
//        Boolean doUpdate;
//        for (DS_CPO_RTRN_DTLTMsg cpoRtrnDtlTMsg : cpoRtrnDtList) {
//            doUpdate = false;
//            if (cpoRtrnDtlTMsg == baseCmptTMsg && !S21StringUtil.isEquals(cpoRtrnDtlTMsg.baseCmptFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
//                doUpdate = true;
//                ZYPEZDItemValueSetter.setValue(cpoRtrnDtlTMsg.baseCmptFlg, ZYPConstant.FLG_ON_Y);
//            } else if (cpoRtrnDtlTMsg == baseCmptTMsg && !S21StringUtil.isEquals(cpoRtrnDtlTMsg.baseCmptFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
//                ZYPEZDItemValueSetter.setValue(cpoRtrnDtlTMsg.baseCmptFlg, ZYPConstant.FLG_OFF_N);
//                doUpdate = true;
//            }
//
//            if (doUpdate) {
//                S21ApiTBLAccessor.update(cpoRtrnDtlTMsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoRtrnDtlTMsg.getReturnCode())) {
//
//                    int idx = getLinePMsgListIndexFromCpoRtrnDtl(linePMsgList, cpoRtrnDtlTMsg);
//
//                    if (idx >= 0) {
//                        setErrMsgIdForCpoDtl(NWZM1598E, linePMsgList, idx);
//                    } else {
//                        this.msgMap.addXxMsgId(NWZM1598E);
//                    }
//
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }
    // 2016/09/27 S21_NA#9102 Del Start

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    private int getLinePMsgListIndexFromCpoRtrnDtl(final List<NWZC153002PMsg> linePMsgList, DS_CPO_RTRN_DTLTMsg cpoRtrnDtlTMsg) {
//        int retIdx = -1;
//
//        NWZC153002PMsg pMsg;
//        for (int i = 0; i < linePMsgList.size(); i++) {
//            pMsg = linePMsgList.get(i);
//            if (S21StringUtil.isEquals(pMsg.dsCpoRtrnLineNum.getValue(), cpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue())
//                    && S21StringUtil.isEquals(pMsg.dsCpoRtrnLineSubNum.getValue(), cpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue())) {
//                retIdx = i;
//                break;
//            }
//        }
//
//        return retIdx;
//    }
//    // 2016/07/06 S21_NA#7755 Add End
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * addUpdCpoTMsg
//     * @param NWZC153001CpoBean NWZC153001CpoBean
//     * @param cpoTMsg CPOTMsg
//     * @param isSaveHdrRqst boolean
//     * @param isNewHdrRqst boolean
//     * @param isCancelHdrRqst boolean
//     * @param isModifyHdrRqst boolean
//     */
//    private void addUpdCpoTMsg(NWZC153001CpoBean cpoBean, CPOTMsg cpoTMsg, boolean isSaveHdrRqst, boolean isNewHdrRqst, boolean isCancelHdrRqst, boolean isModifyHdrRqst) {
//        // Save Mode
//        if (isSaveHdrRqst) {
//            cpoBean.setOrdHdrStsCd(ORD_HDR_STS.SAVED);
//            addCpoTmsg(cpoBean, cpoTMsg);
//            return;
//        }
//
//        // New Mode
//        if (isNewHdrRqst) {
//            ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdTs, cpoBean.getCpoOrdTs());
//            cpoBean.setOrdHdrStsCd(ORD_HDR_STS.VALIDATED);
//            ZYPEZDItemValueSetter.setValue(cpoTMsg.submtFlg, ZYPConstant.FLG_ON_Y);
//            addCpoTmsg(cpoBean, cpoTMsg);
//
//            return;
//        }
//
//        // Modify Mode
//        if (isModifyHdrRqst) {
//            if (isCloseCpoForModify(cpoBean)) {
//                cpoBean.setOrdHdrStsCd(ORD_HDR_STS.CLOSED);
//                ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrStsCd, cpoBean.getOrdHdrStsCd());
//                ZYPEZDItemValueSetter.setValue(cpoTMsg.openFlg, ZYPConstant.FLG_OFF_N);
//            }
//            return;
//        }
//
//        // Cancel Mode
//        if (isCancelHdrRqst) {
//            String updateMode = chckUpdTypeForCancelCpo(cpoBean);
//
//            if (UPD_MODE_CANC_OPEN_DTL.equals(updateMode)) {
//                addCpoTmsg(cpoBean, cpoTMsg);
//                return;
//            }
//            if (UPD_MODE_CANC_ALL_CANC.equals(updateMode)) {
//                cpoBean.setOrdHdrStsCd(ORD_HDR_STS.CANCELLED);
//                ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrStsCd, cpoBean.getOrdHdrStsCd());
//                ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoHldFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(cpoTMsg.openFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoCancFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoCancDt, cpoBean.getSlsDt());
//                addCpoTmsg(cpoBean, cpoTMsg);
//                return;
//            }
//            if (UPD_MODE_CANC_CLOSED.equals(updateMode)) {
//                cpoBean.setOrdHdrStsCd(ORD_HDR_STS.CLOSED);
//                ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrStsCd, cpoBean.getOrdHdrStsCd());
//                ZYPEZDItemValueSetter.setValue(cpoTMsg.openFlg, ZYPConstant.FLG_OFF_N);
//                addCpoTmsg(cpoBean, cpoTMsg);
//            }
//        }
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * check if is Close Status for Modify Mode
//     * @param cpoBean NWZC153001CpoBean
//     * @return boolean
//     */
//    private boolean isCloseCpoForModify(NWZC153001CpoBean cpoBean) {
//        // check has Cancelled or Closed DS_CPO_DTL
//        CPO_DTLTMsgArray cpoDtlTMsgArray = getCpoDtlTMsgArray(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
//        for (int i = 0; i < cpoDtlTMsgArray.getValidCount(); i++) {
//            if (!ORD_LINE_STS.CANCELLED.equals(cpoDtlTMsgArray.no(i).ordLineStsCd.getValue()) && !ORD_LINE_STS.CLOSED.equals(cpoDtlTMsgArray.no(i).ordLineStsCd.getValue())) {
//                return false;
//            }
//        }
//
//        // check has Cancelled or Closed in DS_CPO_RTRN_DTL
//        DS_CPO_RTRN_DTLTMsgArray rtrnDtlTMsgArray = getDsCpoRtrnDtlTMsgArray(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
//        for (int i = 0; i < rtrnDtlTMsgArray.getValidCount(); i++) {
//            String rtrnLineStsCd = rtrnDtlTMsgArray.no(i).rtrnLineStsCd.getValue();
//
//            if (!RTRN_LINE_STS.CANCELLED.equals(rtrnDtlTMsgArray.no(i).rtrnLineStsCd.getValue()) && !RTRN_LINE_STS.CLOSED.equals(rtrnDtlTMsgArray.no(i).rtrnLineStsCd.getValue())) {
//                return false;
//            }
//        }
//
//        return true;
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * check Update Type For Cancel Cpo
//     * @param cpoBean NWZC153001CpoBean
//     * @return String
//     */
//    private String chckUpdTypeForCancelCpo(NWZC153001CpoBean cpoBean) {
//        boolean isAllDtlCancel = true;
//        boolean hasOpenDtl = false;
//
//        // check has Cancelled or Closed DS_CPO_DTL
//        CPO_DTLTMsgArray cpoDtlTMsgArray = getCpoDtlTMsgArray(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
//        for (int i = 0; i < cpoDtlTMsgArray.getValidCount(); i++) {
//            String ordLineStsCd = cpoDtlTMsgArray.no(i).ordLineStsCd.getValue();
//            if (!ORD_LINE_STS.CANCELLED.equals(ordLineStsCd)) {
//                isAllDtlCancel = false;
//                if (!ORD_LINE_STS.CLOSED.equals(ordLineStsCd)) {
//                    hasOpenDtl = true;
//                }
//            }
//
//            if (!isAllDtlCancel && hasOpenDtl) {
//                break;
//            }
//        }
//
//        boolean isAllRtrnDtlCancel = true;
//        // 2016/02/24 S21_NA#3429 Mod Start
//        // boolean isAllRtrnDtlClosed = false;
//        boolean hasOpenRtrnDtl = false;
//        // 2016/02/24 S21_NA#3429 Mod End
//
//        // check has Cancelled or Closed in DS_CPO_RTRN_DTL
//        DS_CPO_RTRN_DTLTMsgArray rtrnDtlTMsgArray = getDsCpoRtrnDtlTMsgArray(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
//        for (int i = 0; i < rtrnDtlTMsgArray.getValidCount(); i++) {
//            String rtrnLineStsCd = rtrnDtlTMsgArray.no(i).rtrnLineStsCd.getValue();
//
//            if (!RTRN_LINE_STS.CANCELLED.equals(rtrnLineStsCd)) {
//                isAllRtrnDtlCancel = false;
//                if (!RTRN_LINE_STS.CLOSED.equals(rtrnLineStsCd)) {
//                    // 2016/02/24 S21_NA#3429 Mod Start
//                    // isAllRtrnDtlClosed = false;
//                    hasOpenRtrnDtl = true;
//                    // 2016/02/24 S21_NA#3429 Mod End
//                }
//            }
//            // 2016/02/24 S21_NA#3429 Mod Start
//            // if (!isAllDtlCancel && isAllRtrnDtlClosed) {
//            if (!isAllRtrnDtlCancel && hasOpenRtrnDtl) { // 2016/02/24 S21_NA#3429 Mod End
//                break;
//            }
//        }
//
//        // return cancel type
//        // 2016/02/24 S21_NA#3429 Mod Start
////        if (hasOpenDtl && !isAllRtrnDtlClosed) {
////            return UPD_MODE_CANC_OPEN_DTL;
////        }
////        if (isAllDtlCancel && isAllRtrnDtlCancel) {
////            return UPD_MODE_CANC_ALL_CANC;
////        }
////        if (!hasOpenDtl && isAllRtrnDtlClosed) {
////            return UPD_MODE_CANC_CLOSED;
////        }
//        if (hasOpenDtl && hasOpenRtrnDtl) {
//            return UPD_MODE_CANC_OPEN_DTL;
//        }
//        if (isAllDtlCancel && isAllRtrnDtlCancel) {
//            return UPD_MODE_CANC_ALL_CANC;
//        }
//        if (!hasOpenDtl && !hasOpenRtrnDtl) {
//            return UPD_MODE_CANC_CLOSED;
//        }
//        // 2016/02/24 S21_NA#3429 Mod End
//        return null;
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * addCpoTmsg
//     * @param cpoBean NWZC153001CpoBean
//     * @param cpoTMsg CPOTMsg
//     * @param isSaveHdrRqst boolean
//     * @param isNewHdrRqst boolean
//     */
//    private void addInsCpoTMsg(NWZC153001CpoBean cpoBean, CPOTMsg cpoTMsg, boolean isSaveHdrRqst, boolean isNewHdrRqst) {
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.openFlg, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
//
//        if (isSaveHdrRqst) {
//            cpoBean.setOrdHdrStsCd(ORD_HDR_STS.SAVED);
//            ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrStsCd, cpoBean.getOrdHdrStsCd());
//        } else if (isNewHdrRqst) {
//            cpoBean.setOrdHdrStsCd(ORD_HDR_STS.VALIDATED);
//            ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrStsCd, cpoBean.getOrdHdrStsCd());
//            ZYPEZDItemValueSetter.setValue(cpoTMsg.submtFlg, ZYPConstant.FLG_ON_Y);
//        }
//        addCpoTmsg(cpoBean, cpoTMsg);
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * addCpoTmsg
//     * @param cpoBean NWZC153001CpoBean
//     * @param cpoTMsg CPOTMsg
//     */
//    private void addCpoTmsg(NWZC153001CpoBean cpoBean, CPOTMsg cpoTMsg) {
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdTpCd, cpoBean.getCpoOrdTpCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.custIssPoNum, cpoBean.getCustIssPoNum());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoSrcTpCd, cpoBean.getCpoSrcTpCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordFuflLvlCd, cpoBean.getOrdFuflLvlCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.billToCustCd, cpoBean.getBillToCustCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.sellToCustCd, cpoBean.getSellToCustCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.sellToFirstRefCmntTxt, cpoBean.getSellToFirstRefCmntTxt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.sellToScdRefCmntTxt, cpoBean.getSellToScdRefCmntTxt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.adminPsnCd, cpoBean.getAdminPsnCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.payerCustCd, cpoBean.getPayerCustCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addRddDt, cpoBean.getAddRddDt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addRsdDt, cpoBean.getAddRsdDt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShpgSvcLvlCd, cpoBean.getAddShpgSvcLvlCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addFrtChrgToCd, cpoBean.getAddFrtChrgToCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addFrtChrgMethCd, cpoBean.getAddFrtChrgMethCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addDropShipFlg, cpoBean.getAddDropShipFlg());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipToCustCd, cpoBean.getAddShipToCustCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipToLocNm, cpoBean.getAddShipToLocNm());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipToAddlLocNm, cpoBean.getAddShipToAddlLocNm());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipToFirstLineAddr, cpoBean.getAddShipToFirstLineAddr());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipToScdLineAddr, cpoBean.getAddShipToScdLineAddr());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipToThirdLineAddr, cpoBean.getAddShipToThirdLineAddr());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipToFrthLineAddr, cpoBean.getAddShipToFrthLineAddr());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipToCtyAddr, cpoBean.getAddShipToCtyAddr());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipToStCd, cpoBean.getAddShipToStCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipToProvNm, cpoBean.getAddShipToProvNm());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipToPostCd, cpoBean.getAddShipToPostCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipToCtryCd, cpoBean.getAddShipToCtryCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipToCntyNm, cpoBean.getAddShipToCntyNm());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipTo01RefCmntTxt, cpoBean.getAddShipTo01RefCmntTxt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addShipTo02RefCmntTxt, cpoBean.getAddShipTo02RefCmntTxt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addPmtTermCashDiscCd, cpoBean.getAddPmtTermCashDiscCd());
//        // ZYPEZDItemValueSetter.setValue(cpoTMsg.chngRsnTpCd,cpoBean.getChngRsnTpCd());
//        // ZYPEZDItemValueSetter.setValue(cpoTMsg.bizProcCmntTxt,cpoBean.getBizProcCmntTxt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.sysSrcCd, cpoBean.getSysSrcCd());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdTs, cpoBean.getCpoOrdTs());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.custIssPoDt, cpoBean.getCustIssPoDt());
//        // ZYPEZDItemValueSetter.setValue(cpoTMsg.origCpoOrdNum,cpoBean.getOrigCpoOrdNum());
//        // ZYPEZDItemValueSetter.setValue(cpoTMsg.origInvNum,cpoBean.getOrigInvNum());
//
//        // 2015/12/17 S21_NA#2007 Add Start
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealSlsAmt, cpoBean.getEntCpoTotDealSlsAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealDiscAmt, cpoBean.getEntCpoTotDealDiscAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealNetAmt, cpoBean.getEntCpoTotDealNetAmt());
//
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncSlsAmt, cpoBean.getEntCpoTotFuncSlsAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncDiscAmt, cpoBean.getEntCpoTotFuncDiscAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncNetAmt, cpoBean.getEntCpoTotFuncNetAmt());
//
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealSlsAmt, cpoBean.getCpoTotDealSlsAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealDiscAmt, cpoBean.getCpoTotDealDiscAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealNetAmt, cpoBean.getCpoTotDealNetAmt());
//
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncNetAmt, cpoBean.getCpoTotFuncNetAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncSlsAmt, cpoBean.getCpoTotFuncSlsAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncDiscAmt, cpoBean.getCpoTotFuncDiscAmt());
//        // 2015/12/17 S21_NA#2007 Add End
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrStsCd, cpoBean.getOrdHdrStsCd()); // 2015/12/17 S21_NA#2470 Add End
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addOrigCpoOrdNum,cpoBean.getOrigInvNum()); // S21_NA10893 Add
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.reBillPairCpoOrdNum,cpoBean.getOrigInvNum()); // S21_NA10893 Add
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * addCpoTmsg
//     * @param cpoBean NWZC153001CpoBean
//     * @param cpoTMsg CPOTMsg
//     */
//    private void addUpdCpoTMsgForModifyAmt(NWZC153001CpoBean cpoBean, CPOTMsg cpoTMsg, boolean isCancelHdrRqst) {
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealSlsAmt, cpoBean.getEntCpoTotDealSlsAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealDiscAmt, cpoBean.getEntCpoTotDealDiscAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealNetAmt, cpoBean.getEntCpoTotDealNetAmt());
//
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncSlsAmt, cpoBean.getEntCpoTotFuncSlsAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncDiscAmt, cpoBean.getEntCpoTotFuncDiscAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncNetAmt, cpoBean.getEntCpoTotFuncNetAmt());
//
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealSlsAmt, cpoBean.getCpoTotDealSlsAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealDiscAmt, cpoBean.getCpoTotDealDiscAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealNetAmt, cpoBean.getCpoTotDealNetAmt());
//
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncNetAmt, cpoBean.getCpoTotFuncNetAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncSlsAmt, cpoBean.getCpoTotFuncSlsAmt());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncDiscAmt, cpoBean.getCpoTotFuncDiscAmt());
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * addDsCpoTmsg
//     * @param cpoBean NWZC153001CpoBean
//     * @param dsCpoTMsg CPOTMsg
//     */
//    private void addDsCpoTmsg(NWZC153001CpoBean cpoBean, CPOTMsg dsCpoTMsg) {
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.carrAcctNum, cpoBean.getCarrAcctNum());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.ordSgnDt, cpoBean.getOrdSgnDt());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.orgRqstDelyDt, cpoBean.getOrgRqstDelyDt());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.invRcpntCustCd, cpoBean.getInvRcpntCustCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.dsOrdTpCd, cpoBean.getDsOrdTpCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.dsOrdRsnCd, cpoBean.getDsOrdRsnCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.dsPmtMethCd, cpoBean.getDsPmtMethCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.prcBaseDt, cpoBean.getPrcBaseDt());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.prcCalcDt, cpoBean.getPrcCalcDt());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.dsOrdCatgCd, cpoBean.getDsOrdCatgCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.billToCustAcctCd, cpoBean.getBillToCustAcctCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.shipToCustAcctCd, cpoBean.getShipToCustAcctCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.soldToCustLocCd, cpoBean.getSoldToCustLocCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.negoDealAmt, cpoBean.getNegoDealAmt());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.slsRepTocCd, cpoBean.getSlsRepTocCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.prcCatgCd, cpoBean.getPrcCatgCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.flPrcListCd, cpoBean.getFlPrcListCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.aquNum, cpoBean.getAquNum());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.ordSrcImptDt, cpoBean.getOrdSrcImptDt());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.ordSrcRefNum, cpoBean.getOrdSrcRefNum());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.prcContrNum, cpoBean.getPrcContrNum());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.loanPerDaysAot, cpoBean.getLoanPerDaysAot());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.csmpContrNum, cpoBean.getCsmpContrNum());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.leaseCmpyPoNum, cpoBean.getLeaseCmpyPoNum());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.leasePrchOptCd, cpoBean.getLeasePrchOptCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.leaseTermCd, cpoBean.getLeaseTermCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.leaseTermMthAot, cpoBean.getLeaseTermMthAot()); //S21_NA#4564
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.leasePmtFreqCd, cpoBean.getLeasePmtFreqCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.leaseTotPmtAmt, cpoBean.getLeaseTotPmtAmt()); //S21_NA#4564
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.ordLogTpCd, cpoBean.getOrdLogTpCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.dlrRefNum, cpoBean.getDlrRefNum());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.frtCondCd, cpoBean.getFrtCondCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.carrSvcLvlCd, cpoBean.getCarrSvcLvlCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.spclHdlgTpCd, cpoBean.getSpclHdlgTpCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.prePmtChkNum, cpoBean.getPrePmtChkNum());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.prePmtAmt, cpoBean.getPrePmtAmt());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.prePmtTpCd, cpoBean.getPrePmtTpCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.crRebilRsnCatgCd, cpoBean.getCrRebilRsnCatgCd());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.dsCrCardPk, cpoBean.getDsCrCardPk());
//        // ZYPEZDItemValueSetter.setValue(dsCpoTMsg.origCpoOrdNum,cpoBean.getOrigCpoOrdNum());
//        // ZYPEZDItemValueSetter.setValue(dsCpoTMsg.origInvNum,cpoBean.getOrigInvNum());
//        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.diChkHldFlg, cpoBean.getDiChkHldFlg()); // S21_NA#1957
//        // S21_NA#1952 start
//        BigDecimal cpoUpdVrsnNum = dsCpoTMsg.cpoUpdVrsnNum.getValue();
//        if (!ZYPCommonFunc.hasValue(cpoUpdVrsnNum)) {
//            setValue(dsCpoTMsg.cpoUpdVrsnNum, BigDecimal.ONE);
//        } else {
//            if (ZYPConstant.FLG_ON_Y.equals(cpoBean.getXxValUpdFlg())) {
//                setValue(dsCpoTMsg.cpoUpdVrsnNum, cpoUpdVrsnNum.add(BigDecimal.ONE));
//            } else {
//                setValue(dsCpoTMsg.cpoUpdVrsnNum, cpoUpdVrsnNum);
//            }
//        }
//        // S21_NA#1952 end
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    /**
     * getUpdateOrdPrcCalcBase
     * @param cpoBean NWZC153001CpoBean
     * @param linePMsgList List<NWZC153002PMsg>
     */
    private static void registRtnPrcCalc(NWZC153001CpoBean cpoBean, List<NWZC153002PMsg> linePMsgList) {
        for (NWZC153001DetailBean cpoDtlBean : cpoBean.getDtlBeanList()) {

            if (RQST_DTL_CANCEL.equals(cpoDtlBean.getXxRqstTpCd())) {
                continue;
            }

            final String cpoOrdNum = cpoBean.getCpoOrdNum();
            final String rtrnLineNum = cpoDtlBean.getDsCpoRtrnLineNum();
            final String rtrnLineSubNum = cpoDtlBean.getDsCpoRtrnLineSubNum();
            final String dtlRqstTpCd = cpoDtlBean.getXxRqstTpCd();

            // get Price Calculate List
            List<NWZC153001PrcCalcBean> linePriceList = getLinePriceList(cpoBean, rtrnLineNum, rtrnLineSubNum);

            // --------------------------------------------------
            // INSERT
            // --------------------------------------------------
            if (!ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum_BK())) {
                if (!RQST_DTL_SAVE.equals(dtlRqstTpCd) && !RQST_DTL_NEW.equals(dtlRqstTpCd)) {
                    continue;
                }

                // create insert Data
                for (NWZC153001PrcCalcBean priceData : linePriceList) {
                    //if (!insertRtrnCalcBase(cpoBean, priceData, linePMsgList, cpoDtlBean.getDsCpoLineNum(), cpoDtlBean.getDsCpoLineSubNum())) { 2016/01/08 S21_NA#2899
                    if (!insertRtrnCalcBase(cpoBean, priceData, linePMsgList, cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum())) {
                        return;
                    }
                }
                continue;
            }

            // --------------------------------------------------
            // MODIFY
            // --------------------------------------------------
            final CPO_RTRN_PRC_CALC_BASETMsgArray rtrnPrcCalcBaseTMsgArray = //
                (CPO_RTRN_PRC_CALC_BASETMsgArray) getRtrnPrcCalcBaseTMsg( //
                    cpoBean.getGlblCmpyCd(), //
                    cpoOrdNum, //
                    rtrnLineNum, //
                    rtrnLineSubNum, //
                    true); // 2016/06/20 S21_NA#5331-3

            // 2015/12/25 S21_NA#2470 Update Start
//            String rtrnCalcBaseRegistMode = getRtrnCalcBaseRegistMode(rtrnPrcCalcBaseTMsgArray, linePriceList);
//
//            // Insert Mode
//            if (INS_MODE.equals(rtrnCalcBaseRegistMode)) {
//                for (NWZC153001PrcCalcBean priceData : linePriceList) {
//                    if (!insertRtrnCalcBase(cpoBean, priceData, linePMsgList, cpoDtlBean.getDsCpoLineNum(), cpoDtlBean.getDsCpoLineSubNum())) {
//                        return;
//                    }
//                }
//                continue;
//            }
//
//            // Update Mode
//            if (UPD_MODE.equals(rtrnCalcBaseRegistMode)) {
//                for (int idx = 0; idx < rtrnPrcCalcBaseTMsgArray.length(); idx++) {
//                    CPO_RTRN_PRC_CALC_BASETMsg updPriceTMsg = (CPO_RTRN_PRC_CALC_BASETMsg) rtrnPrcCalcBaseTMsgArray.get(idx);
//
//                    for (NWZC153001PrcCalcBean priceData : linePriceList) {
//                        if (updPriceTMsg.cpoRtrnPrcCalcBasePk.getValue().compareTo(priceData.getCpoRtrnPrcCalcBasePk()) == 0) {
//
//                            addRtrnPrcCalcBaseTmsg(priceData, updPriceTMsg);
//                            cacheDBAccess.updateWithCache(updPriceTMsg);
//
//                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updPriceTMsg.getReturnCode())) {
//                                setErrMsgIdForCpoDtl(NWZM1745E, linePMsgList, cpoDtlBean.getDsCpoLineNum(), cpoDtlBean.getDsCpoLineSubNum());
//                                return;
//                            }
//                            break;
//                        }
//                    }
//                }
//                continue;
//            }
//
//            // Delete Mode
//            for (int idx = 0; idx < rtrnPrcCalcBaseTMsgArray.getValidCount(); idx++) {
//                CPO_RTRN_PRC_CALC_BASETMsg delPriceTMsg = (CPO_RTRN_PRC_CALC_BASETMsg) rtrnPrcCalcBaseTMsgArray.get(idx);
//                S21ApiTBLAccessor.logicalRemove(delPriceTMsg);
//
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(delPriceTMsg.getReturnCode())) {
//                    setErrMsgIdForCpoDtl(NWZM1746E, linePMsgList, cpoDtlBean.getDsCpoLineNum(), cpoDtlBean.getDsCpoLineSubNum());
//                    return;
//                }
//                break;
//            }
            for (NWZC153001PrcCalcBean priceData : linePriceList) {
                CPO_RTRN_PRC_CALC_BASETMsg updCpoRtrnPrcCalcBaseTMsg = getRtrnCalcBaseRecord(rtrnPrcCalcBaseTMsgArray, priceData);

                // Insert Record
                if (null == updCpoRtrnPrcCalcBaseTMsg) {
                    // if (!insertRtrnCalcBase(cpoBean, priceData, linePMsgList, cpoDtlBean.getDsCpoLineNum(), cpoDtlBean.getDsCpoLineSubNum())) { 2016/01/08 S21_NA#2899
                    if (!insertRtrnCalcBase(cpoBean, priceData, linePMsgList, cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum())) {
                        return;
                    }
                } else {
                    // Update
                    addRtrnPrcCalcBaseTmsg(priceData, updCpoRtrnPrcCalcBaseTMsg);
                    // cacheDBAccess.updateWithCache(updCpoRtrnPrcCalcBaseTMsg); 2016/04/19 S21_NA#5394 Del
                    S21ApiTBLAccessor.update(updCpoRtrnPrcCalcBaseTMsg); // 2016/04/19 S21_NA#5394 Add

                  if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updCpoRtrnPrcCalcBaseTMsg.getReturnCode())) {
                      // 2016/01/08 S21_NA#2899 Mod Start
                      // setErrMsgIdForCpoDtl(NWZM1745E, linePMsgList, cpoDtlBean.getDsCpoLineNum(), cpoDtlBean.getDsCpoLineSubNum());
                      setErrMsgIdForCpoDtl(NWZM1745E, linePMsgList, cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum());
                      // 2016/01/08 S21_NA#2899 Mod End
                      return;
                  }
                }
            }
            // Delete
            List<CPO_RTRN_PRC_CALC_BASETMsg> deleteCpoRtrnPrcCalcBaseList = getToBeDeletedRtrnCalcBase(rtrnPrcCalcBaseTMsgArray, linePriceList);
            for (CPO_RTRN_PRC_CALC_BASETMsg deleteCpoRtrnPrcCalcBase : deleteCpoRtrnPrcCalcBaseList) {
                S21ApiTBLAccessor.logicalRemove(deleteCpoRtrnPrcCalcBase);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(deleteCpoRtrnPrcCalcBase.getReturnCode())) {
                    // 2016/01/08 S21_NA#2899 Mod Start
                    // setErrMsgIdForCpoDtl(NWZM1746E, linePMsgList, cpoDtlBean.getDsCpoLineNum(), cpoDtlBean.getDsCpoLineSubNum());
                    setErrMsgIdForCpoDtl(NWZM1746E, linePMsgList, cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum());
                    // 2016/01/08 S21_NA#2899 Mod End
                    return;
                }
            }
            // 2015/12/25 S21_NA#2470 Update End
        }
    }

    // 2015/12/25 S21_NA#2470 Delete Start
    /**
     * getRtrnCalcBaseRegistMode
     * @param cpoBean NWZC153001CpoBean
     * @param rtrnPrcCalcBaseTMsgArray CPO_RTRN_PRC_CALC_BASETMsgArray
     * @param linePriceList List<NWZC153001PrcCalcBean>
     * @return String
     */
//    private static String getRtrnCalcBaseRegistMode(CPO_RTRN_PRC_CALC_BASETMsgArray rtrnPrcCalcBaseTMsgArray, List<NWZC153001PrcCalcBean> linePriceList) {
//        boolean isDelete = false;
//        if (rtrnPrcCalcBaseTMsgArray != null) {
//            for (int tmsgIdx = 0; tmsgIdx < rtrnPrcCalcBaseTMsgArray.length(); tmsgIdx++) {
//
//                if (ZYPCommonFunc.hasValue(rtrnPrcCalcBaseTMsgArray.no(tmsgIdx).specCondPrcPk)) {
//                    isDelete = true;
//                } else {
//                    continue;
//                }
//
//                for (NWZC153001PrcCalcBean prcCalc : linePriceList) {
//                    if (ZYPCommonFunc.hasValue(prcCalc.getSpecCondPrcPk())) {
//                        if (rtrnPrcCalcBaseTMsgArray.no(tmsgIdx).specCondPrcPk.getValue().compareTo(prcCalc.getSpecCondPrcPk()) == 0) {
//                            return UPD_MODE;
//                        }
//                    }
//                }
//            }
//        }
//
//        if (isDelete) {
//            return DEL_MODE;
//        }
//        return INS_MODE;
//    }
    // 2015/12/25 S21_NA#2470 Delete End

    // 2015/12/25 S21_NA#2470 Add Start
    private static CPO_RTRN_PRC_CALC_BASETMsg getRtrnCalcBaseRecord(CPO_RTRN_PRC_CALC_BASETMsgArray rtrnPrcCalcBaseTMsgArray, NWZC153001PrcCalcBean prcCalc) {
        for (int tmsgIdx = 0; tmsgIdx < rtrnPrcCalcBaseTMsgArray.length(); tmsgIdx++) {
            CPO_RTRN_PRC_CALC_BASETMsg rtrnPrcTMsg = rtrnPrcCalcBaseTMsgArray.no(tmsgIdx);
            String specCondPrcPkTMsg = String.valueOf(rtrnPrcTMsg.specCondPrcPk.getValue());
            String specCondPrcPkPrm = String.valueOf(prcCalc.getSpecCondPrcPk());
            if (specCondPrcPkTMsg.equals(specCondPrcPkPrm) && S21StringUtil.isEquals(rtrnPrcTMsg.prcDtlGrpCd.getValue(), prcCalc.getPrcDtlGrpCd())) {
                return rtrnPrcTMsg;
            }
        }
        return null;
    }

    private static List<CPO_RTRN_PRC_CALC_BASETMsg> getToBeDeletedRtrnCalcBase(CPO_RTRN_PRC_CALC_BASETMsgArray rtrnPrcCalcBaseTMsgArray, List<NWZC153001PrcCalcBean> linePriceList) {
        List<CPO_RTRN_PRC_CALC_BASETMsg> deleteCpoRtrnPrcCalcBaseList = new ArrayList<CPO_RTRN_PRC_CALC_BASETMsg>(0);
        for (int tmsgIdx = 0; tmsgIdx < rtrnPrcCalcBaseTMsgArray.length(); tmsgIdx++) {
            CPO_RTRN_PRC_CALC_BASETMsg rtrnPrcTMsg = rtrnPrcCalcBaseTMsgArray.no(tmsgIdx);
            boolean isLookedUp = false;

            for (NWZC153001PrcCalcBean prcCalc : linePriceList) {
                String specCondPrcPkTMsg = String.valueOf(rtrnPrcTMsg.specCondPrcPk.getValue());
                String specCondPrcPkPrm = String.valueOf(prcCalc.getSpecCondPrcPk());
                if (specCondPrcPkTMsg.equals(specCondPrcPkPrm) && S21StringUtil.isEquals(rtrnPrcTMsg.prcDtlGrpCd.getValue(), prcCalc.getPrcDtlGrpCd())) {
                    isLookedUp = true;
                    break;
                }
            }
            if (!isLookedUp) {
                deleteCpoRtrnPrcCalcBaseList.add(rtrnPrcTMsg);
            }
        }
        return deleteCpoRtrnPrcCalcBaseList;
    }
    // 2015/12/25 S21_NA#2470 Add End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * getUpdateOrdPrcCalcBase
//     * @param cpoBean NWZC153001CpoBean
//     * @param rtrnPrcCalcBaseTMsgArray CPO_RTRN_PRC_CALC_BASETMsgArray
//     * @param priceData NWZC153001_prcCalcListPMsg
//     * @return CPO_RTRN_PRC_CALC_BASETMsg
//     */
//    private static CPO_RTRN_PRC_CALC_BASETMsg getUpdateOrdPrcCalcBase(NWZC153001CpoBean cpoBean, CPO_RTRN_PRC_CALC_BASETMsgArray rtrnPrcCalcBaseTMsgArray, NWZC153001_prcCalcListPMsg priceData) {
//        for (int i = 0; i < rtrnPrcCalcBaseTMsgArray.length(); i++) {
//            final CPO_RTRN_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg = rtrnPrcCalcBaseTMsgArray.no(i);
//
//            if (isEqualsPriceKey(cpoBean, priceData, ordPrcCalcBaseTMsg)) {
//                return ordPrcCalcBaseTMsg;
//            }
//        }
//        return null;
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * isRemovePriceTMsg
//     * @param cpoBean NWZC153001CpoBean
//     * @param linePriceList List<NWZC153001_prcCalcListPMsg>
//     * @param reqTMsg CPO_RTRN_PRC_CALC_BASETMsg
//     * @return boolean
//     */
//    private static boolean isRemovePriceTMsg(NWZC153001CpoBean cpoBean, List<NWZC153001_prcCalcListPMsg> linePriceList, CPO_RTRN_PRC_CALC_BASETMsg reqTMsg) {
//        for (NWZC153001_prcCalcListPMsg priceData : linePriceList) {
//            if (isEqualsPriceKey(cpoBean, priceData, reqTMsg)) {
//                return false;
//            }
//        }
//        return true;
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * isEqualsPriceKey
//     * @param cpoBean NWZC153001CpoBean
//     * @param priceData NWZC153001_prcCalcListPMsg
//     * @param reqTMsg CPO_RTRN_PRC_CALC_BASETMsg
//     * @return boolean
//     */
//    private static boolean isEqualsPriceKey(NWZC153001CpoBean cpoBean, NWZC153001_prcCalcListPMsg priceData, CPO_RTRN_PRC_CALC_BASETMsg reqTMsg) {
//
//        if (!(cpoBean.getGlblCmpyCd().equals(reqTMsg.glblCmpyCd.getValue()))) {
//            return false;
//        }
//
//        if (!(cpoBean.getCpoOrdNum().equals(reqTMsg.cpoOrdNum.getValue()))) {
//            return false;
//        }
//
//        if (!(priceData.dsCpoRtrnLineNum.getValue().equals(reqTMsg.dsCpoRtrnLineNum.getValue()))) {
//            return false;
//        }
//
//        if (!(priceData.dsCpoRtrnLineSubNum.getValue().equals(reqTMsg.dsCpoRtrnLineSubNum.getValue()))) {
//            return false;
//        }
//
//        if (!(priceData.prcCondTpCd.getValue().equals(reqTMsg.prcCondTpCd.getValue()))) {
//            return false;
//        }
//        return true;
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    /**
     * getLinePriceList
     * @param cpoBean NWZC153001CpoBean
     * @param rtrnDtlLineNum String
     * @param rtrnDtlLineSubNum String
     * @return List<NWZC153001_prcCalcListPMsg>
     */
    private static List<NWZC153001PrcCalcBean> getLinePriceList(NWZC153001CpoBean cpoBean, String rtrnDtlLineNum, String rtrnDtlLineSubNum) {

        List<NWZC153001PrcCalcBean> linePriceList = new ArrayList<NWZC153001PrcCalcBean>();

        // for (NWZC153001PrcCalcBean priceData : linePriceList) { 2015/12/16 S21_NA#2007 Del
        List<NWZC153001PrcCalcBean> linePriceListOrig = cpoBean.getPrcCalcList(); // 2015/12/16 S21_NA#2007 Add
        for (NWZC153001PrcCalcBean priceData : linePriceListOrig) { // 2015/12/16 S21_NA#2007 Add
            if (rtrnDtlLineNum.equals(priceData.getDsCpoRtrnLineNum()) && rtrnDtlLineSubNum.equals(priceData.getDsCpoRtrnLineSubNum())) {
                linePriceList.add(priceData);
            }
        }

        return linePriceList;
    }

    /**
     * insertRtrnCalcBase
     * @param cpoBean NWZC153001CpoBean
     * @param priceData NWZC153001_prcCalcListPMsg
     * @param List<NWZC153002PMsg> linePMsgList
     * @param lineNum BigDecimal
     * @param lineSubNum BigDecimal
     * @return boolean
     */
//    private static boolean insertRtrnCalcBase(NWZC153001CpoBean cpoBean, NWZC153001PrcCalcBean prcCalcBean, List<NWZC153002PMsg> linePMsgList, BigDecimal lineNum, BigDecimal lineSubNum) { 2016/01/08 S21_NA#2899 Mod
    private static boolean insertRtrnCalcBase(NWZC153001CpoBean cpoBean, NWZC153001PrcCalcBean prcCalcBean, List<NWZC153002PMsg> linePMsgList, String lineNum, String lineSubNum) {
        CPO_RTRN_PRC_CALC_BASETMsg rtrnPrcCalcBaseTMsg = new CPO_RTRN_PRC_CALC_BASETMsg();

        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.cpoRtrnPrcCalcBasePk, prcCalcBean.getCpoRtrnPrcCalcBasePk());
        // 2015/12/16 S21_NA#2007 Add Start
        if (!ZYPCommonFunc.hasValue(rtrnPrcCalcBaseTMsg.cpoRtrnPrcCalcBasePk)) {
            rtrnPrcCalcBaseTMsg.cpoRtrnPrcCalcBasePk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_RTRN_PRC_CALC_BASE_SQ));
        }
        // 2015/12/16 S21_NA#2007 Add End
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd()); // 2015/12/16 S21_NA#2007

        rtrnPrcCalcBaseTMsg = addRtrnPrcCalcBaseTmsg(prcCalcBean, rtrnPrcCalcBaseTMsg);

        // cacheDBAccess.insertWithCache(rtrnPrcCalcBaseTMsg); 2016/04/19 S21_NA#5394 Del
        S21ApiTBLAccessor.insert(rtrnPrcCalcBaseTMsg); // 2016/04/19 S21_NA#5394 Add
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtrnPrcCalcBaseTMsg.getReturnCode())) {
            setErrMsgIdForCpoDtl(NWZM1744E, linePMsgList, lineNum, lineSubNum);
            return false;
        }
        return true;
    }

    /**
     * addRtrnPrcCalcBaseTmsg
     * @param priceData NWZC153001_prcCalcListPMsg
     * @param rtrnPrcCalcBaseTMsg CPO_RTRN_PRC_CALC_BASETMsg
     * @return CPO_RTRN_PRC_CALC_BASETMsg
     */
    private static CPO_RTRN_PRC_CALC_BASETMsg addRtrnPrcCalcBaseTmsg(NWZC153001PrcCalcBean priceData, CPO_RTRN_PRC_CALC_BASETMsg rtrnPrcCalcBaseTMsg) {
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.dsCpoRtrnLineNum, priceData.getDsCpoRtrnLineNum());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.dsCpoRtrnLineSubNum, priceData.getDsCpoRtrnLineSubNum());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.prcCondTpCd, priceData.getPrcCondTpCd());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.prcDtlGrpCd, priceData.getPrcDtlGrpCd());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.prcJrnlGrpCd, priceData.getPrcJrnlGrpCd());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.pkgUomCd, priceData.getPkgUomCd());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.prcCondUnitCd, priceData.getPrcCondUnitCd());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.prcCalcMethCd, priceData.getPrcCalcMethCd());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.dsMdsePrcPk, priceData.getDsMdsePrcPk());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.specCondPrcPk, priceData.getSpecCondPrcPk());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.frtPerWtPk, priceData.getFrtPerWtPk());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.prcCondManEntryFlg, priceData.getPrcCondManEntryFlg());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.prcCondManAddFlg, priceData.getPrcCondManAddFlg());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.prcCondManDelFlg, priceData.getPrcCondManDelFlg());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.autoPrcAmtRate, priceData.getAutoPrcAmtRate());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.maxPrcAmtRate, priceData.getMaxPrcAmtRate());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.minPrcAmtRate, priceData.getMinPrcAmtRate());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.manPrcAmtRate, priceData.getManPrcAmtRate());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.calcPrcAmtRate, priceData.getCalcPrcAmtRate());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.unitPrcAmt, priceData.getUnitPrcAmt());
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.autoPrcCcyCd, priceData.getAutoPrcCcyCd());
        // QC#9700 2018/09/03 Add Start
        if (ZYPCommonFunc.hasValue(priceData.getPrcRuleApplyFlg())) {
            ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.prcRuleApplyFlg, priceData.getPrcRuleApplyFlg());
        } else {
            ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
        }
        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.prcRulePrcdPk, priceData.getPrcRulePrcdPk());
        // QC#9700  2018/09/03 Add End
        return rtrnPrcCalcBaseTMsg;
    }

    /**
     * registDsCpoRtrnPrcDtl
     * @param cpoBean DWZC001001Bean
     * @param pMsg NWZC153001PMsg
     * @param linePMsgList List<NWZC153002PMsg>
     * @return boolean
     */
    private boolean registDsCpoRtrnPrcDtl(NWZC153001CpoBean cpoBean, List<NWZC153002PMsg> linePMsgList) {
        for (NWZC153001DetailBean cpoDtlBean : cpoBean.getDtlBeanList()) {

            if (RQST_DTL_CANCEL.equals(cpoDtlBean.getXxRqstTpCd())) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum_BK())) {
                if (!RQST_DTL_SAVE.equals(cpoDtlBean.getXxRqstTpCd()) && !RQST_DTL_NEW.equals(cpoDtlBean.getXxRqstTpCd()) && !RQST_DTL_MOD.equals(cpoDtlBean.getXxRqstTpCd())) {
                    continue;
                }

                // delete
                // 2015/12/25 S21_NA##2470 Update Start
//                DS_CPO_RTRN_PRC_DTLTMsgArray resRtrnPrcDtlTMsgArray = getDsCpoRtrnPrcDtlTMsgArray(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getCpoOrdNum(), cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum());
//                for (int i = 0; i < resRtrnPrcDtlTMsgArray.length(); i++) {
//                DS_CPO_RTRN_PRC_DTLTMsg resRtrnPrcDtlTMsg = resRtrnPrcDtlTMsgArray.no(i);
                List<DS_CPO_RTRN_PRC_DTLTMsg> resRtrnPrcDtlTMsgList = getDsCpoRtrnPrcDtlTMsgArray(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getCpoOrdNum(), cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum());
                for (DS_CPO_RTRN_PRC_DTLTMsg resRtrnPrcDtlTMsgForDel : resRtrnPrcDtlTMsgList) {
                    DS_CPO_RTRN_PRC_DTLTMsg resRtrnPrcDtlTMsg = (DS_CPO_RTRN_PRC_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(resRtrnPrcDtlTMsgForDel);
                    // 2015/12/25 S21_NA##2470 Update End
                    cacheDBAccess.logicalRemoveWithCache(resRtrnPrcDtlTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(resRtrnPrcDtlTMsg.getReturnCode())) {
                        // 2016/01/08 S21_NA#2899 Mod Start
                        // setErrMsgIdForCpoDtl(NWZM1748E, linePMsgList, cpoDtlBean.getDsCpoLineNum(), cpoDtlBean.getDsCpoLineSubNum());
                        setErrMsgIdForCpoDtl(NWZM1748E, linePMsgList, cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum());
                        // 2016/01/08 S21_NA#2899 Mod End
                        return false;
                    }
                }

                // insert
                if (!insertDsCpoRtrnPrcDtl(cpoBean.getCpoOrdNum_BK(), cpoBean, cpoDtlBean, linePMsgList)) {
                    return false;
                }
            } else {
                if (!RQST_DTL_SAVE.equals(cpoDtlBean.getXxRqstTpCd()) && !RQST_DTL_NEW.equals(cpoDtlBean.getXxRqstTpCd())) {
                    continue;
                }

                // insert
                if (!insertDsCpoRtrnPrcDtl(cpoBean.getCpoOrdNum(), cpoBean, cpoDtlBean, linePMsgList)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * insert DsCpoRtrnPrcDtl
     * @param ordNum String
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     * @param pMsg NWZC153001PMsg
     * @param linePMsgList List<NWZC153002PMsg>
     * @return boolean
     */
    private static boolean insertDsCpoRtrnPrcDtl(String ordNum, NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean, List<NWZC153002PMsg> linePMsgList) {
        // has Discount Price List
        if (cpoDtlBean.getDiscPrcList().size() > 0) {
            for (NWZC153001DiscountBean discPrcData : cpoDtlBean.getDiscPrcList()) {
                final DS_CPO_RTRN_PRC_DTLTMsg reqPrcDtlTMsg = setRtrnPrcDtlTMsg(ordNum, cpoBean, cpoDtlBean, discPrcData);

                // cacheDBAccess.insertWithCache(reqPrcDtlTMsg); 2016/04/19 S21_NA#5394 Del
                S21ApiTBLAccessor.insert(reqPrcDtlTMsg); // 2016/04/19 S21_NA#5394 Add
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(reqPrcDtlTMsg.getReturnCode())) {
                    // 2016/01/08 S21_NA#2899 Mod Start
                    // setErrMsgIdForCpoDtl(NWZM1747E, linePMsgList, cpoDtlBean.getDsCpoLineNum(), cpoDtlBean.getDsCpoLineSubNum());
                    setErrMsgIdForCpoDtl(NWZM1747E, linePMsgList, cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum());
                    // 2016/01/08 S21_NA#2899 Mod End
                    return false;
                }
            }
            // does not have Discount Price List
        } else {
            final DS_CPO_RTRN_PRC_DTLTMsg reqPrcDtlTMsg = setRtrnPrcDtlTMsg(ordNum, cpoBean, cpoDtlBean, null);

            // cacheDBAccess.insertWithCache(reqPrcDtlTMsg); 2016/04/19 S21_NA#5394 Del
            S21ApiTBLAccessor.insert(reqPrcDtlTMsg); // 2016/04/19 S21_NA#5394 Add
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(reqPrcDtlTMsg.getReturnCode())) {
                // 2016/01/08 S21_NA#2899 Mod Start
                //setErrMsgIdForCpoDtl(NWZM1747E, linePMsgList, cpoDtlBean.getDsCpoLineNum(), cpoDtlBean.getDsCpoLineSubNum());
                setErrMsgIdForCpoDtl(NWZM1747E, linePMsgList, cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum());
                // 2016/01/08 S21_NA#2899 Mod End
                return false;
            }
        }
        return true;
    }

    /**
     * setRtrnPrcDtlTMsg
     * @param cpoOrdNum String
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     * @return PRC_DTLTMsg NWZC153001DiscountBean
     */
    private static DS_CPO_RTRN_PRC_DTLTMsg setRtrnPrcDtlTMsg(String cpoOrdNum, NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean, NWZC153001DiscountBean discPrcData) {

        final DS_CPO_RTRN_PRC_DTLTMsg rtrnPrcDtlTMsg = new DS_CPO_RTRN_PRC_DTLTMsg();

        rtrnPrcDtlTMsg.glblCmpyCd.setValue(cpoBean.getGlblCmpyCd());
        rtrnPrcDtlTMsg.dsCpoRtrnPrcDtlPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_RTRN_PRC_DTL_SQ));

        rtrnPrcDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);
        rtrnPrcDtlTMsg.dsCpoRtrnLineNum.setValue(cpoDtlBean.getDsCpoRtrnLineNum());
        rtrnPrcDtlTMsg.dsCpoRtrnLineSubNum.setValue(cpoDtlBean.getDsCpoRtrnLineSubNum());
        rtrnPrcDtlTMsg.prcDt.setValue(cpoBean.getSlsDt());
        rtrnPrcDtlTMsg.shipUnitQty.setValue(cpoDtlBean.getOrdQty());

        rtrnPrcDtlTMsg.dealUnitPrcAmt.setValue(cpoDtlBean.getDealGrsUnitPrcAmt());
        rtrnPrcDtlTMsg.dealLastNetUnitPrcAmt.setValue(cpoDtlBean.getEntDealNetUnitPrcAmt());
        rtrnPrcDtlTMsg.dealNetAmt.setValue(cpoDtlBean.getEntCpoDtlDealNetAmt());
        rtrnPrcDtlTMsg.funcUnitPrcAmt.setValue(cpoDtlBean.getFuncGrsUnitPrcAmt());
        rtrnPrcDtlTMsg.funcLastNetUnitPrcAmt.setValue(cpoDtlBean.getEntFuncNetUnitPrcAmt());
        rtrnPrcDtlTMsg.funcNetAmt.setValue(cpoDtlBean.getEntCpoDtlFuncNetAmt());

        if (discPrcData != null) {
            // 2018/02/23 S21_NA#19808 Del Start
//            rtrnPrcDtlTMsg.cpoLinePrcNum.setValue(String.valueOf(discPrcData.getCpoLinePrcNum()));
            // 2018/02/23 S21_NA#19808 Del End
            // 2018/02/23 S21_NA#19808 Add Start
            String cpoLinePrcNum = String.valueOf(discPrcData.getCpoLinePrcNum());
            cpoLinePrcNum = NWXC150001NumberingUtil.getNextCpoDtlLineNum(cpoLinePrcNum);
            rtrnPrcDtlTMsg.cpoLinePrcNum.setValue(cpoLinePrcNum);
            // 2018/02/23 S21_NA#19808 Add End
            rtrnPrcDtlTMsg.dealDiscAmt.setValue(discPrcData.getDealPerUnitFixAmt().multiply(cpoDtlBean.getOrdQty()));
            rtrnPrcDtlTMsg.dealPrmoNetUnitPrcAmt.setValue(discPrcData.getDealPrmoNetUnitPrcAmt());
            rtrnPrcDtlTMsg.funcDiscAmt.setValue(discPrcData.getFuncPerUnitFixAmt().multiply(cpoDtlBean.getOrdQty()));
            rtrnPrcDtlTMsg.funcPrmoNetUnitPrcAmt.setValue(discPrcData.getFuncPrmoNetUnitPrcAmt());
            rtrnPrcDtlTMsg.dealPerUnitFixAmt.setValue(discPrcData.getDealPerUnitFixAmt());
            rtrnPrcDtlTMsg.dealSlsPctNum.setValue(discPrcData.getDealSlsPctNum());
            rtrnPrcDtlTMsg.funcPerUnitFixAmt.setValue(discPrcData.getFuncPerUnitFixAmt());
        } else {
            rtrnPrcDtlTMsg.dealPrmoNetUnitPrcAmt.setValue(cpoDtlBean.getEntDealNetUnitPrcAmt());
            rtrnPrcDtlTMsg.funcPrmoNetUnitPrcAmt.setValue(cpoDtlBean.getEntFuncNetUnitPrcAmt());
        }

        setDefaultValues(rtrnPrcDtlTMsg); // QC#12481 2016/10/26 Add

        return rtrnPrcDtlTMsg;
    }

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * addInsHdrBizProcLog
//     * @param pMsg NWZC153001PMsg
//     * @param cpoBean NWZC153001CpoBean
//     * @param isSaveHdrRqst boolean
//     * @param isNewHdrRqst boolean
//     * @param isCancelHdrRqst boolean
//     * @param isModifyHdrRqst boolean
//     * @param cpoDtlTMsgArray CPO_DTLTMsg array 2016/01/14 S21_NA#2996 Add
//     */
//    private void registHdrBizProcLog(NWZC153001CpoBean cpoBean, boolean isSaveHdrRqst, boolean isNewHdrRqst, boolean isCancelHdrRqst, boolean isModifyHdrRqst, CPO_DTLTMsgArray cpoDtlTMsgArray) {
//        String cpoOrdNum = null;
//        String eventId = null;
//
//        if (ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum_BK())) {
//            if (!isSaveHdrRqst && !isNewHdrRqst && !isModifyHdrRqst && !isCancelHdrRqst) {
//                return;
//            }
//            // 2016/01/14 S21_NA#2996 Mod Start
//            // eventId = getHdrEventID(cpoBean, isSaveHdrRqst, isNewHdrRqst, isCancelHdrRqst, isModifyHdrRqst);
//            eventId = getHdrEventID(cpoBean, isSaveHdrRqst, isNewHdrRqst, isCancelHdrRqst, isModifyHdrRqst, cpoDtlTMsgArray);
//            // 2016/01/14 S21_NA#2996 Mod End
//            cpoOrdNum = cpoBean.getCpoOrdNum_BK();
//        } else {
//            if (!isSaveHdrRqst && !isNewHdrRqst) {
//                return;
//            }
//            eventId = ORD_CREATE;
//            cpoOrdNum = cpoBean.getCpoOrdNum();
//        }
//
//        // insert BIZ_PROC_LOG
//        // 2016/02/24S21_NA#3429 Mod Start
//        // insertBizProcLog(cpoOrdNum, eventId, null);
//
//        // cpoBean.setBizProcLogPk(getMaxBizProcLogPk(cpoOrdNum, null, eventId, null, null));
//        if (null != eventId) {
//            insertBizProcLog(cpoOrdNum, eventId, null, DOC_TP_OM, cpoBean); // QC#5395 / 2016/02/23 S21_NA#3328 Mod
//
//            cpoBean.setBizProcLogPk(getMaxBizProcLogPk(cpoOrdNum, null, eventId, null, null, DOC_TP_OM)); // 2016/02/23 S21_NA#3328 Mod
//        }
//        // 2016/02/24S21_NA#3429 Mod End
//    }

//    /**
//     * getEventID
//     * @param cpoBean NWZC153001CpoBean
//     * @param isSaveHdrRqst boolean
//     * @param isNewHdrRqst boolean
//     * @param isCancelHdrRqst boolean
//     * @param isModifyHdrRqst boolean
//     * @param cpoDtlTMsgArray CPO_DTLTMsg array 2016/01/14 S21_NA#2996 Add
//     */
//    private String getHdrEventID(NWZC153001CpoBean cpoBean, boolean isSaveHdrRqst, boolean isNewHdrRqst, boolean isCancelHdrRqst, boolean isModifyHdrRqst, CPO_DTLTMsgArray cpoDtlTMsgArray) {
//        if (isSaveHdrRqst || isNewHdrRqst) {
//            if (isBizProcLogRegistered(cpoBean.getCpoOrdNum())) {
//                return ORD_SAVE;
//            } else {
//                return ORD_CREATE;
//            }
//        }
//
//        if (isModifyHdrRqst) {
//            if (ORD_HDR_STS.CLOSED.equals(cpoBean.getOrdHdrStsCd())) {
//                return ORD_CLS;
//            } else {
//                return ORD_CHNG;
//            }
//        }
//
//        if (isCancelHdrRqst) {
//            if (ORD_HDR_STS.CLOSED.equals(cpoBean.getOrdHdrStsCd())) {
//                return ORD_CLS;
//            }
//            if (ORD_HDR_STS.CANCELLED.equals(cpoBean.getOrdHdrStsCd())) {
//                return ORD_CANCEL;
//            }
//            // 2016/01/14 S21_NA#2996 Add Start
//            // Check All Cpo Detail Cancelled
//            if (isAllRelatedCpoDtlClosed(cpoDtlTMsgArray)) {
//                return ORD_CLS;
//            }
//            if (isAllRelatedCpoDtlCancelled(cpoDtlTMsgArray)) {
//                return ORD_CANCEL;
//            }
//            // 2016/01/14 S21_NA#2996 Add End
//            // 2016/02/24 S21_NA#3429 Add Start
//            // Check All DS Cpo Return Detail Cancelled
//            if (ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum())) {
//                DS_CPO_RTRN_DTLTMsgArray dsCpoRtrnDtlTMsgArray = getDsCpoRtrnDtlTMsgArray(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
//                if (isAllRelatedDsCpoRtrnDtlClosed(dsCpoRtrnDtlTMsgArray)) {
//                    return ORD_CLS;
//                }
//                if (isAllRelatedDsCpoRtrnDtlCancelled(dsCpoRtrnDtlTMsgArray)) {
//                    return ORD_CANCEL;
//                }
//            }
//            // 2016/02/24 S21_NA#3429 Add End
//        }
//        return null;
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    /**
     * registDtlBizProcLog
     * @param cpoBean NWZC153001CpoBean
     * @param linePMsgList List<NWZC153002PMsg>
     * @param isSaveHdrRqst boolean
     * @param isNewHdrRqst boolean
     * @param isModifyHdrRqst boolean
     */
    private void registDtlBizProcLog(NWZC153001CpoBean cpoBean, List<NWZC153002PMsg> linePMsgList, String xxRqstHdrTpCd) { // 2016/03/01 S21_NA#3328 Mod

        for (int idx = 0; idx < cpoBean.getDtlBeanList().size(); idx++) {
            NWZC153001DetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(idx);

            String cpoOrdNum = null;
            String eventId = null;
            if (ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum_BK())) {
                eventId = getDtlEventId(cpoDtlBean, xxRqstHdrTpCd); // 2016/03/01 S21_NA#3328 Mod
                cpoOrdNum = cpoBean.getCpoOrdNum_BK();
            } else {
                if (!RQST_HDR_SAVE.equals(xxRqstHdrTpCd) && !RQST_HDR_NEW.equals(xxRqstHdrTpCd)) { // 2016/03/01 S21_NA#3328 Mod
                    return;
                }
                eventId = ORD_CREATE;
                cpoOrdNum = cpoBean.getCpoOrdNum();
            }

            // insert BIZ_PROC_LOG
            String docId = getDtlDocId(cpoDtlBean);
            insertBizProcLog(cpoOrdNum, eventId, docId, DOC_TP_RT, cpoBean, cpoDtlBean); // QC#5395 / 2016/02/23 S21_NA#3328 Mod // 2018/08/07 S21_NA#25404 Add cpoDtlBean

            // get Business Process Log Pk
            BigDecimal maxLineBizProcLogPk = getMaxBizProcLogPk(cpoOrdNum, docId, eventId, cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum(), DOC_TP_RT); // 2016/02/23 S21_NA#3328 Mod
            cpoDtlBean.setBizProcLogPk(maxLineBizProcLogPk);
        }
    }

	// QC#5395
    private String getBizProcCmntTxt_01(String glblCmpyCd, String chngRsnTpCd) { // 2018/08/07 S21_NA#25404 Change Interface, modify without any comments
        if (!hasValue(chngRsnTpCd)) {
            return "";
        }
        CHNG_RSN_TPTMsg chngRsnTpTMsg = new CHNG_RSN_TPTMsg();
        chngRsnTpTMsg.glblCmpyCd.setValue(glblCmpyCd);
        chngRsnTpTMsg.chngRsnTpCd.setValue(chngRsnTpCd);
        chngRsnTpTMsg = (CHNG_RSN_TPTMsg) findByKeyWithCache(chngRsnTpTMsg);
        if (chngRsnTpTMsg == null) {
            return "";
        }
        return chngRsnTpTMsg.chngRsnTpNm.getValue();
    }

    // 2016/03/01 S21_NA#3328 Mod Start
    /**
     * getDtlEventId
     * @param cpoDtlBean NWZC153001DetailBean
     * @param isSaveHdrRqst boolean
     * @param isNewHdrRqst boolean
     * @param isModifyHdrRqst boolean
     */
    private String getDtlEventId(NWZC153001DetailBean cpoDtlBean, String xxRqstHdrTpCd) {

        String rqstTpCd = cpoDtlBean.getXxRqstTpCd();

        if (RQST_HDR_SAVE.equals(xxRqstHdrTpCd)) {
            if (RQST_DTL_CANCEL.equals(rqstTpCd)) {
                return ORD_CANCEL;
            } else {
                return ORD_SAVE;
            }
        } else if (RQST_HDR_NEW.equals(xxRqstHdrTpCd)) {
            return ORD_CREATE;
        } else if (RQST_HDR_CANCEL.equals(xxRqstHdrTpCd)) {
            return ORD_CANCEL;
        } else if (RQST_HDR_MOD.equals(xxRqstHdrTpCd)) {
            if (RQST_DTL_NEW.equals(rqstTpCd)) {
                return ORD_CREATE;
            } else if (RQST_DTL_CANCEL.equals(rqstTpCd)) {
                return ORD_CANCEL;
            } else {
                // 2018/08/07 S21_NA#25404 Add Start
                if (ZYPCommonFunc.hasValue(cpoDtlBean.getChngRsnTpCd()) || ZYPCommonFunc.hasValue(cpoDtlBean.getBizProcCmntTxt())) {
                    return ORD_CANCEL;
                }
                // 2018/08/07 S21_NA#25404 Add End
                return ORD_CHNG;
            }
        } else {
            // 2016/07/12 S21_NA#10418 Mod Start
            if (RQST_DTL_RWS_CREATE.equals(rqstTpCd)) {
                //return ORD_CREATE;
                return RWS_CREATE;
            } else if (RQST_DTL_RWC_CANCEL.equals(rqstTpCd)) {
                //return ORD_CANCEL;
                return RWS_CANCEL;
            // mod start 2016/07/26 CSA S21_NA#8854
            } else if (RQST_DTL_RECEIVE.equals(rqstTpCd)) {
                return RECEIVED;
            } else {
                return SHORTAGE_RECEIVED;
            }
            // mod end 2016/07/26 CSA S21_NA#8854
            // 2016/07/12 S21_NA#10418 Mod End
        }
    }
    // 2016/03/01 S21_NA#3328 Mod End

    /**
     * setDtlDocId
     * @param cpoDtlBean NWZC153001DetailBean
     */
    private String getDtlDocId(NWZC153001DetailBean cpoDtlBean) {
        StringBuilder docID = new StringBuilder();
        docID.append(cpoDtlBean.getDsCpoRtrnLineNum()).append(".").append(cpoDtlBean.getDsCpoRtrnLineSubNum());
        return docID.toString();
    }

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * isBizProcLogRegistered
//     * @param ordNum String
//     */
//    private boolean isBizProcLogRegistered(String ordNum) {
//        final Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("parentDocID", ordNum);
//        ssmParam.put("subSysID", SUB_SYS_ID_NWZ);
//        ssmParam.put("procID", DOC_TP_OM);
//        ssmParam.put("docTpCd", DOC_TP_OM);
//        ssmParam.put("rowNumOne", BigDecimal.ONE);
//
//        BigDecimal bizProcLogPk = (BigDecimal) ssmClient.queryObject("getBizProcLogPk", ssmParam);
//
//        if (ZYPCommonFunc.hasValue(bizProcLogPk)) {
//            return true;
//        }
//        return false;
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    /**
     * getMaxBizProcLogPk
     * @param ordNum String
     * @param docId String
     * @param eventId String
     * @param rtrnLineNum String
     * @param rtrnLineSubNum String
     */
    private BigDecimal getMaxBizProcLogPk(String ordNum, String docId, String eventId, String rtrnLineNum, String rtrnLineSubNum, String docTpCd) { // 2016/02/23 S21_NA#3328 Mod
        final Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("parentDocID", ordNum);
        ssmParam.put("eventID", eventId);
        ssmParam.put("subSysID", SUB_SYS_ID_NWZ);
        ssmParam.put("procID", DOC_TP_OM);
        ssmParam.put("docTpCd", docTpCd); // 2016/02/23 S21_NA#3328 Mod

        if (ZYPCommonFunc.hasValue(docId)) {
            ssmParam.put("docID", docId);
        }
        if (ZYPCommonFunc.hasValue(rtrnLineNum)) {
            ssmParam.put("rtrnLineNum", rtrnLineNum);
        }
        if (ZYPCommonFunc.hasValue(rtrnLineSubNum)) {
            ssmParam.put("rtrnLineSubNum", rtrnLineSubNum);
        }

        if (ZYPCommonFunc.hasValue(docId)) {
            return (BigDecimal) ssmClient.queryObject("getMaxBizProcLogPkForDtl", ssmParam);
        } else {
            return (BigDecimal) ssmClient.queryObject("getMaxBizProcLogPkForHdr", ssmParam);
        }
    }

    /**
     * insertBizProcLog
     * @param cpoOrdNum String
     * @param eventId   String
     * @param docId     String
     * @param docTpCd   String
     * @param cpoBean   NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean 2018/08/07 S21_NA#25404 Add
     */
    private void insertBizProcLog(String cpoOrdNum, String eventId, String docId, String docTpCd, NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean) { // 2016/02/25 S21_NA#3328 Mod 
        S21BusinessProcessLogMsg bizProcLog = new S21BusinessProcessLogMsg();

        bizProcLog.setSubSysId(SUB_SYS_ID_NWZ);
        bizProcLog.setProcId(DOC_TP_OM);
        bizProcLog.setDocTpCd(docTpCd); // 2016/02/25 S21_NA#3328 Mod
        bizProcLog.setPrntDocId(cpoOrdNum);
        bizProcLog.setEventId(eventId);
        if (ZYPCommonFunc.hasValue(docId)) {
            bizProcLog.setDocId(docId);
        }
        // 2018/08/07 S21_NA#25404 Del Start
//        bizProcLog.setBizProcCmntTxt_01(getBizProcCmntTxt_01(cpoBean)); // QC#5395
//        bizProcLog.setBizProcCmntTxt_02(cpoBean.getBizProcCmntTxt());   // QC#5395
        // 2018/08/07 S21_NA#25404 Del End

        // 2018/08/07 S21_NA#25404 Add Start
        String bizProcCmntTxt_01 = "";
        String bizProcCmntTxt_02 = "";
        if (ZYPCommonFunc.hasValue(cpoBean.getChngRsnTpCd())) {
            bizProcCmntTxt_01 = getBizProcCmntTxt_01(cpoBean.getGlblCmpyCd(), cpoBean.getChngRsnTpCd());
        } else if (ZYPCommonFunc.hasValue(cpoDtlBean.getChngRsnTpCd())) {
            bizProcCmntTxt_01 = getBizProcCmntTxt_01(cpoBean.getGlblCmpyCd(), cpoDtlBean.getChngRsnTpCd());
        }
        if (ZYPCommonFunc.hasValue(cpoBean.getBizProcCmntTxt())) {
            bizProcCmntTxt_02 = cpoBean.getBizProcCmntTxt();
        } else if (ZYPCommonFunc.hasValue(cpoDtlBean.getBizProcCmntTxt())) {
            bizProcCmntTxt_02 = cpoDtlBean.getBizProcCmntTxt();
        }
        bizProcLog.setBizProcCmntTxt_01(bizProcCmntTxt_01); // QC#5395
        bizProcLog.setBizProcCmntTxt_02(bizProcCmntTxt_02);   // QC#5395
        // 2018/08/07 S21_NA#25404 Add End

        S21BusinessProcessLog.print(bizProcLog);
    }

    /**
     * changeHldOrdQty
     * @param cpoBean NWZC153001CpoBean
     * @param linePMsgList List<NWZC153002PMsg>
     */
    private void changeHldOrdQty(NWZC153001CpoBean cpoBean, List<NWZC153002PMsg> linePMsgList) {
        for (int idx = 0; idx < cpoBean.getDtlBeanList().size(); idx++) {
            NWZC153001DetailBean detailBean = cpoBean.getDtlBeanList().get(idx);

            HLDTMsg updHldTMsg = getUpdHoldTMsg(detailBean);
            if (updHldTMsg == null) {
                // 2016/01/07 S21_NA#S21_NA#2592 Mod Start
                // setErrMsgIdForCpoDtl(NWZM1752E, linePMsgList, detailBean.getDsCpoLineNum(), detailBean.getDsCpoLineSubNum());
                //return;
                continue;
                // 2016/01/07 S21_NA#S21_NA#2592 Mod Start
            }

            if (!detailBean.getOrdQty().equals(updHldTMsg.hldQty.getValue())) {
                ZYPEZDItemValueSetter.setValue(updHldTMsg.hldQty, detailBean.getOrdQty());
            }

            // cacheDBAccess.updateWithCache(updHldTMsg); 2016/04/19 S21_NA#5394 Del
            S21ApiTBLAccessor.update(updHldTMsg); //  2016/04/19 S21_NA#5394 Add
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updHldTMsg.getReturnCode())) {
                // 2016/01/08 S21_NA#2899 Mod Start
                //setErrMsgIdForCpoDtl(NWZM1752E, linePMsgList, detailBean.getDsCpoLineNum(), detailBean.getDsCpoLineSubNum());
                setErrMsgIdForCpoDtl(NWZM1752E, linePMsgList, detailBean.getDsCpoRtrnLineNum(), detailBean.getDsCpoRtrnLineSubNum());
                // 2016/01/08 S21_NA#2899 Mod End
                return;
            }
        }
    }

    /**
     * registHold
     * @param cpoBean NWZC153001CpoBean
     * @param linePMsgList List<NWZC153002PMsg>
     */
    private void registHold(NWZC153001CpoBean cpoBean, List<NWZC153002PMsg> linePMsgList) {
        for (int idx = 0; idx < cpoBean.getHldList().size(); idx++) {
            NWZC153001HldBean hldBean = cpoBean.getHldList().get(idx);
            HLDTMsg insTMsgKey = setInsHoldTMsg(cpoBean, hldBean);

            // cacheDBAccess.insertWithCache(insTMsgKey); 2016/04/19 S21_NA#5394 Del
            S21ApiTBLAccessor.insert(insTMsgKey); // 2016/04/19 S21_NA#5394 Add

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insTMsgKey.getReturnCode())) {
                for (int dtlIdx = 0; dtlIdx < cpoBean.getDtlBeanList().size(); dtlIdx++) {
                    NWZC153001DetailBean dtlBean = cpoBean.getDtlBeanList().get(dtlIdx);
                    if (dtlBean.getDsCpoRtrnLineNum().equals(hldBean.getDsCpoRtrnLineNum()) && dtlBean.getDsCpoRtrnLineSubNum().equals(hldBean.getDsCpoRtrnLineSubNum())) {
                        // 2016/01/08 S21_NA#2899 Mod Start
                        // setErrMsgIdForCpoDtl(NWZM1750E, linePMsgList, dtlBean.getDsCpoLineNum(), dtlBean.getDsCpoLineSubNum());
                        setErrMsgIdForCpoDtl(NWZM1750E, linePMsgList, dtlBean.getDsCpoRtrnLineNum(), dtlBean.getDsCpoRtrnLineSubNum());
                        // 2016/01/08 S21_NA#2899 Mod End
                        break;
                    }
                }
            }
        }
    }

    /**
     * getUpdHoldTMsg
     * @param cpoDtlBean NWZC153001DetailBean
     */
    private HLDTMsg getUpdHoldTMsg(NWZC153001DetailBean cpoDtlBean) {

        HLDTMsg updTMsgKey = new HLDTMsg();

        // S21_NA#3960
        updTMsgKey.setSQLID("005");
        updTMsgKey.setConditionValue("glblCmpyCd01", cpoDtlBean.getGlblCmpyCd());
        updTMsgKey.setConditionValue("cpoOrdNum01", cpoDtlBean.getCpoOrdNum());
        updTMsgKey.setConditionValue("cpoDtlLineNum01", cpoDtlBean.getDsCpoRtrnLineNum());
        updTMsgKey.setConditionValue("cpoDtlLineSubNum01", cpoDtlBean.getDsCpoRtrnLineSubNum());

        final HLDTMsgArray hldTMsgArray = (HLDTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(updTMsgKey);
        if (hldTMsgArray == null || hldTMsgArray.getValidCount() == 0) {
            return null;
        } else {
            for (int i = 0; i < hldTMsgArray.getValidCount(); i++) {
                if (S21StringUtil.isEquals(hldTMsgArray.no(i).trxSrcTpCd.getValue(), TRX_SRC_TP.WHOLE_SALES_RETURN)) {
                    return hldTMsgArray.no(i);
                }
            }
        }

        return null;

        // S21_NA#3960
        // ZYPEZDItemValueSetter.setValue(updTMsgKey.glblCmpyCd,
        // cpoDtlBean.getGlblCmpyCd());
        // ZYPEZDItemValueSetter.setValue(updTMsgKey.cpoOrdNum,
        // cpoDtlBean.getCpoOrdNum());
        // ZYPEZDItemValueSetter.setValue(updTMsgKey.cpoDtlLineNum,
        // cpoDtlBean.getDsCpoRtrnLineNum());
        // ZYPEZDItemValueSetter.setValue(updTMsgKey.cpoDtlLineSubNum,
        // cpoDtlBean.getDsCpoRtrnLineSubNum());
        // ZYPEZDItemValueSetter.setValue(updTMsgKey.trxSrcTpCd,
        // TRX_SRC_TP.WHOLE_SALES_RETURN);
        //
        // HLDTMsg updTMsg = (HLDTMsg)
        // S21FastTBLAccessor.findByKeyForUpdateAPI(updTMsgKey);
        // if (updTMsg == null ||
        // !S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode()))
        // {
        // return null;
        // }
        //
        // return updTMsg;
    }

    /**
     * setInsHoldTMsg
     * @param cpoBean NWZC153001CpoBean
     * @param NWZC153001HldBean NWZC153001HldBean
     * @return HLDTMsg
     */
    private HLDTMsg setInsHoldTMsg(NWZC153001CpoBean cpoBean, NWZC153001HldBean hldBean) {
        HLDTMsg insTMsgKey = new HLDTMsg();

        ZYPEZDItemValueSetter.setValue(insTMsgKey.hldPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.HLD_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsgKey.cpoOrdNum, cpoBean.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(insTMsgKey.cpoDtlLineNum, hldBean.getDsCpoRtrnLineNum());
        ZYPEZDItemValueSetter.setValue(insTMsgKey.cpoDtlLineSubNum, hldBean.getDsCpoRtrnLineSubNum());
        ZYPEZDItemValueSetter.setValue(insTMsgKey.hldRsnCd, hldBean.getHldRsnCd());
        ZYPEZDItemValueSetter.setValue(insTMsgKey.hldDt, cpoBean.getSlsDt());
        ZYPEZDItemValueSetter.setValue(insTMsgKey.relFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsgKey.trxSrcTpCd, TRX_SRC_TP.WHOLE_SALES_RETURN); // S21_NA#3960

        BigDecimal ordQty = getOrdQty(cpoBean, hldBean.getDsCpoRtrnLineNum(), hldBean.getDsCpoRtrnLineSubNum());
        ZYPEZDItemValueSetter.setValue(insTMsgKey.hldQty, ordQty);
        return insTMsgKey;
    }

    /**
     * getOrdQty
     * @param cpoBean NWZC153001CpoBean
     * @param dsCpoRtrnLineNum String
     * @param dsCpoRtrnLineSubNum String
     * @return BigDecimal
     */
    private BigDecimal getOrdQty(NWZC153001CpoBean cpoBean, String dsCpoRtrnLineNum, String dsCpoRtrnLineSubNum) {
        for (int index = 0; index < cpoBean.getDtlBeanList().size(); index++) {
            NWZC153001DetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(index);

            if (dsCpoRtrnLineNum.equals(cpoDtlBean.getDsCpoLineNum()) && dsCpoRtrnLineSubNum.equals(cpoDtlBean.getDsCpoRtrnLineSubNum())) {
                return cpoDtlBean.getOrdQty();
            }
        }
        return null;
    }

    // 2017/04/28 S21_NA#RS#5881 Del Start
//    /**
//     * validateOrder
//     * @param cpoBean NWZC153001CpoBean
//     * @param linePMsgList List<NWZC153002PMsg>
//     * @param pMsg linePMsgList
//     * @param isModifyHdrRqst boolean
//     * @param isNewHdrRqst boolean
//     */
//    private void validateOrder(NWZC153001CpoBean cpoBean, List<NWZC153002PMsg> linePMsgList, NWZC153001PMsg pMsg, boolean isNewHdrRqst, boolean isModifyHdrRqst) {
//        boolean isTargetOfOrdValid = isTargetOfOrdValidation(cpoBean);
//        if (!ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum_BK()) && !isTargetOfOrdValid) {
//            return;
//        }
//
//        if (isNewHdrRqst) {
//            callNWZC40001RtrnValid(cpoBean, linePMsgList);
//            return;
//        }
//
//        if (isModifyHdrRqst) {
//            NWZC153001DetailBean dtlBean = null;
//            for (int idx = 0; idx < cpoBean.getDtlBeanList().size(); idx++) {
//                dtlBean = cpoBean.getDtlBeanList().get(idx);
//                if (!RQST_DTL_NEW.equals(dtlBean.getXxRqstTpCd()) && !RQST_DTL_MOD.equals(dtlBean.getXxRqstTpCd())) {
//                    continue;
//                }
//
//                if (!callNWZC40001RtrnValid(cpoBean, dtlBean, linePMsgList, idx)) {
//                    return;
//                }
//            }
//        }
//    }
//
//    /**
//     * isTargetOfOrdValidation
//     * @param cpoBean NWZC153001CpoBean
//     * @return boolean
//     */
//    private boolean isTargetOfOrdValidation(NWZC153001CpoBean cpoBean) {
//        final FindCondition fc = new FindCondition("001");
//        fc.addCondition("glblCmpyCd01", cpoBean.getGlblCmpyCd());
//        fc.addCondition("cpoOrdNum01", cpoBean.getCpoOrdNum());
//        CPO_DTLTMsgArray cpoDtlTMsgArray = localCache.cpoDtlCache.getTMsgArray(fc);
//
//        if (cpoDtlTMsgArray.getValidCount() < 0) {
//            return true;
//        }
//
//        for (int i = 0; i < cpoDtlTMsgArray.length(); i++) {
//            CPO_DTLTMsg cpoDtlTMsg = (CPO_DTLTMsg) cpoDtlTMsgArray.get(i);
//            if (!ORD_LINE_STS.CANCELLED.equals(cpoDtlTMsg.ordLineStsCd.getValue())) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * call NWXC001001EditPriceAmount
//     * @param cpoBean NWZC153001CpoBean
//     * @param linePMsgList List<NWZC153002PMsg>
//     */
//    private void callNWZC40001RtrnValid(NWZC153001CpoBean cpoBean, List<NWZC153002PMsg> linePMsgList) {
//        NWZC400001PMsg rtrnValidPMsg = new NWZC400001PMsg();
//
//        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
//        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.slsDt, cpoBean.getSlsDt());
//        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.xxProcMd, RTRN_VALID_MODE_ORD_VALID);
//        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.cpoOrdNum_I, cpoBean.getCpoOrdNum());
//
//        NWZC400001 rtrnValidApi = new NWZC400001();
//        rtrnValidApi.execute(rtrnValidPMsg, this.onBatchType);
//
//        if (rtrnValidPMsg.xxMsgIdList.getValidCount() > 0) {
//            for (int j = 0; j < rtrnValidPMsg.xxMsgIdList.getValidCount(); j++) {
//                String xxMsgId = rtrnValidPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
//                msgMap.addXxMsgId(xxMsgId);
//            }
//        }
//    }
//
//    /**
//     * call NWXC001001EditPriceAmount
//     * @param cpoBean NWZC153001CpoBean
//     * @param dtlBean NWZC153001DetailBean
//     * @param linePMsgList List<NWZC153002PMsg>
//     * @param idx int
//     * @return BigDecimal
//     */
//    private boolean callNWZC40001RtrnValid(NWZC153001CpoBean cpoBean, NWZC153001DetailBean dtlBean, List<NWZC153002PMsg> linePMsgList, int idx) {
//        NWZC400001PMsg rtrnValidPMsg = new NWZC400001PMsg();
//
//        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
//        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.slsDt, cpoBean.getSlsDt());
//        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.xxProcMd, RTRN_VALID_MODE_ORD_VALID);
//        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.cpoOrdNum_I, cpoBean.getCpoOrdNum());
//        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.dsCpoRtrnLineNum_I, dtlBean.getDsCpoRtrnLineNum());
//        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.dsCpoRtrnLineSubNum_I, dtlBean.getDsCpoRtrnLineSubNum());
//
//        NWZC400001 rtrnValidApi = new NWZC400001();
//        rtrnValidApi.execute(rtrnValidPMsg, this.onBatchType);
//
//        if (rtrnValidPMsg.xxMsgIdList.getValidCount() > 0) {
//            for (int j = 0; j < rtrnValidPMsg.xxMsgIdList.getValidCount(); j++) {
//                String xxMsgId = rtrnValidPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
//                msgMap.addXxMsgId(xxMsgId);
//            }
//            return false;
//        }
//        return true;
//    }

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * insCpoRec
//     * @param cpoBean NWZC153001CpoBean
//     */
//    private void insCpoRec(NWZC153001CpoBean cpoBean) {
//        // Insert CPO_REC
//        CPOTMsg cpoTMsg = (CPOTMsg) getCpoTMsg(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
//        CPO_RECTMsg insCpoRecTMsg = new CPO_RECTMsg();
//
//        EZDMsg.copy(cpoTMsg, null, insCpoRecTMsg, null);
//        ZYPEZDItemValueSetter.setValue(insCpoRecTMsg.bizProcLogPk, cpoBean.getBizProcLogPk());
//
//        // insert
//        S21FastTBLAccessor.insert(insCpoRecTMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insCpoRecTMsg.getReturnCode())) {
//            msgMap.addXxMsgId(NWZM1739E);
//        }
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    /**
     * insDsCpoRtrnDtlRec
     * @param cpoBean NWZC153001CpoBean
     * @param linePMsgList List<NWZC153002PMsg>
     */
    private void insDsCpoRtrnDtlRec(NWZC153001CpoBean cpoBean, List<NWZC153002PMsg> linePMsgList) {
        for (NWZC153001DetailBean cpoDtlBean : cpoBean.getDtlBeanList()) {
            // Insert DS_CPO_RTRN_DTL_REC
            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) getDsCpoRtrnDtlTMsg(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum(), cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum());
            DS_CPO_RTRN_DTL_RECTMsg insDsCpoRecTMsg = new DS_CPO_RTRN_DTL_RECTMsg();

            EZDMsg.copy(dsCpoRtrnDtlTMsg, null, insDsCpoRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(insDsCpoRecTMsg.bizProcLogPk, cpoDtlBean.getBizProcLogPk());

            // 2016/04/07 S21_NA#5331 Add Start
            if (!ZYPCommonFunc.hasValue(cpoDtlBean.getBizProcLogPk())) {
                return;
            }
            // Duplication Check
            DS_CPO_RTRN_DTL_RECTMsg prevInsDsCpoRecTMsg = (DS_CPO_RTRN_DTL_RECTMsg) S21ApiTBLAccessor.findByKey(insDsCpoRecTMsg);
            if (null != prevInsDsCpoRecTMsg) {
                return;
            }
            if (!ZYPCommonFunc.hasValue(insDsCpoRecTMsg.baseCmptFlg)) {
                insDsCpoRecTMsg.baseCmptFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (!ZYPCommonFunc.hasValue(insDsCpoRecTMsg.dropShipFlg)) {
                insDsCpoRecTMsg.dropShipFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (!ZYPCommonFunc.hasValue(insDsCpoRecTMsg.taxFlg)) {
                insDsCpoRecTMsg.taxFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (!ZYPCommonFunc.hasValue(insDsCpoRecTMsg.submtFlg)) {
                insDsCpoRecTMsg.submtFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (!ZYPCommonFunc.hasValue(insDsCpoRecTMsg.openFlg)) {
                insDsCpoRecTMsg.openFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (!ZYPCommonFunc.hasValue(insDsCpoRecTMsg.cpoDtlCancFlg)) {
                insDsCpoRecTMsg.cpoDtlCancFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (!ZYPCommonFunc.hasValue(insDsCpoRecTMsg.cpoDtlHldFlg)) {
                insDsCpoRecTMsg.cpoDtlHldFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (!ZYPCommonFunc.hasValue(insDsCpoRecTMsg.manPrcFlg)) {
                insDsCpoRecTMsg.manPrcFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (!ZYPCommonFunc.hasValue(insDsCpoRecTMsg.manPmtFlg)) {
                insDsCpoRecTMsg.manPmtFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (!ZYPCommonFunc.hasValue(insDsCpoRecTMsg.convCompFlg)) {
                insDsCpoRecTMsg.convCompFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            // 2016/04/07 S21_NA#5331 Add End

            // insert
            // cacheDBAccess.insertWithCache(insDsCpoRecTMsg); 2016/04/19 S21_NA#5394 Del
            S21ApiTBLAccessor.insert(insDsCpoRecTMsg); // 2016/04/19 S21_NA#5394 Add
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insDsCpoRecTMsg.getReturnCode())) {
                // 2016/01/08 S21_NA#2899 Mod Start
                // setErrMsgIdForCpoDtl(NWZM1741E, linePMsgList, cpoDtlBean.getDsCpoLineNum(), cpoDtlBean.getDsCpoLineSubNum());
                setErrMsgIdForCpoDtl(NWZM1741E, linePMsgList, cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum());
                // 2016/01/08 S21_NA#2899 Mod End
                // 2016/04/07 S21_NA#5331 Add Start
                String rtrnCode = insDsCpoRecTMsg.getReturnCode();
                S21LoggerFactory.getLogger(NWZC153001.class).info(System.currentTimeMillis() + " - [@@@@@] NWZC153001#insertWithCache DB Error Code: [" + rtrnCode + "]");
                // 2016/04/07 S21_NA#5331 Add End
                return;
            }
        }
    }

    /**
     * insDsRtrnPrcCalcBase
     * @param cpoBean NWZC153001CpoBean
     * @param linePMsgList List<NWZC153002PMsg>
     */
    private void insDsRtrnPrcCalcBase(NWZC153001CpoBean cpoBean, List<NWZC153002PMsg> linePMsgList) {
        for (NWZC153001DetailBean cpoDtlBean : cpoBean.getDtlBeanList()) {

            // 2016/06/20 S21_NA#5331-3 Add Start
            if (!ZYPCommonFunc.hasValue(cpoDtlBean.getBizProcLogPk())) {
                continue;
            }
            // 2016/06/20 S21_NA#5331-3 Add End
            final CPO_RTRN_PRC_CALC_BASETMsgArray rtrnPrcCalcBaseTMsgArray = //
                (CPO_RTRN_PRC_CALC_BASETMsgArray) getRtrnPrcCalcBaseTMsg( //
                    cpoDtlBean.getGlblCmpyCd(), //
                    cpoDtlBean.getCpoOrdNum(), //
                    cpoDtlBean.getDsCpoRtrnLineNum(), //
                    cpoDtlBean.getDsCpoRtrnLineSubNum(), //
                    false); // 2016/06/20 S21_NA#5331-3

            if (rtrnPrcCalcBaseTMsgArray != null) {
//                for (int i = 0; i < rtrnPrcCalcBaseTMsgArray.length(); i++) {  2016/06/09 S21_NA#5331-2 Mod
                for (int i = 0; i < rtrnPrcCalcBaseTMsgArray.getValidCount(); i++) {
                    final CPO_RTRN_PRC_CALC_BASETMsg rtrnPrcCalcBaseTMsg = rtrnPrcCalcBaseTMsgArray.no(i);
                    CPO_RTRN_CALC_BASE_RECTMsg insDsCpoRecTMsg = new CPO_RTRN_CALC_BASE_RECTMsg();

                    EZDMsg.copy(rtrnPrcCalcBaseTMsg, null, insDsCpoRecTMsg, null);
                    ZYPEZDItemValueSetter.setValue(insDsCpoRecTMsg.bizProcLogPk, cpoDtlBean.getBizProcLogPk()); // 2016/02/23 S21_NA#3479 Mod
                    // QC#9700  2018/09/03 Add Start
                    if(!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, insDsCpoRecTMsg.prcRuleApplyFlg.getValue())){
                        continue;
                    }
                    // QC#9700  2018/09/03 Add End

                    // insert
                    // cacheDBAccess.insertWithCache(insDsCpoRecTMsg); 2016/04/19 S21_NA#5394 Del
                    S21ApiTBLAccessor.insert(insDsCpoRecTMsg); // 2016/04/19 S21_NA#5394 Add
                    // 2016/06/20 S21_NA#5331-3 Add Start
                    if (S21FastTBLAccessor.RTNCD_DUPLICATE.equals(insDsCpoRecTMsg.getReturnCode())) {
                        S21ApiTBLAccessor.update(insDsCpoRecTMsg);
                    }
                    // 2016/06/20 S21_NA#5331-3 Add End
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insDsCpoRecTMsg.getReturnCode())) {
                        // 2016/01/08 S21_NA#2899 Mod Start
                        // setErrMsgIdForCpoDtl(NWZM1742E, linePMsgList, cpoDtlBean.getDsCpoLineNum(), cpoDtlBean.getDsCpoLineSubNum());
                        setErrMsgIdForCpoDtl(NWZM1742E, linePMsgList, cpoDtlBean.getDsCpoRtrnLineNum(), cpoDtlBean.getDsCpoRtrnLineSubNum());
                        // 2016/01/08 S21_NA#2899 Mod End
                        return;
                    }
                }
            }
        }
    }

    /**
     * registAlloc
     * @param cpoBean NWZC153001CpoBean
     * @param linePMsgList List<NWZC153002PMsg>
     */
    private void registAlloc(NWZC153001CpoBean cpoBean, List<NWZC153002PMsg> linePMsgList) {

        // 2017/02/15 S21_NA#17207 Add Start
        if (isOrderSuppliesOrder(cpoBean)) {
            return;
        }
        // 2017/02/15 S21_NA#17207 Add End

        NWZC153001DetailBean cpoDtlBean = null;
        for (int dtlIdx = 0; dtlIdx < cpoBean.getDtlBeanList().size(); dtlIdx++) {
            cpoDtlBean = cpoBean.getDtlBeanList().get(dtlIdx);

            // if request mode is New, Modify or Cancel, call Machine
            // master update API
            // mod start 2016/07/26 CSA S21_NA#8854
            if (RQST_DTL_SAVE.equals(cpoDtlBean.getXxRqstTpCd()) || RQST_DTL_RECEIVE.equals(cpoDtlBean.getXxRqstTpCd()) || RQST_DTL_RWC_CANCEL.equals(cpoDtlBean.getXxRqstTpCd()) || RQST_DTL_RWS_CREATE.equals(cpoDtlBean.getXxRqstTpCd()) || RQST_DTL_SHORTAGE_CLOSED.equals(cpoDtlBean.getXxRqstTpCd())) {
            // mod end 2016/07/26 CSA S21_NA#8854
                continue;
            }

            // Skip if New or Modify mode but does not have Serial
            // Number
            if (RQST_DTL_MOD.equals(cpoDtlBean.getXxRqstTpCd()) || RQST_DTL_NEW.equals(cpoDtlBean.getXxRqstTpCd())) {
                // 2016/06/03 S21_NA#9275 Mod Start
//              if (!ZYPCommonFunc.hasValue(cpoDtlBean.getSerNum())) {
                if (!ZYPCommonFunc.hasValue(cpoDtlBean.getSerNum()) && !ZYPCommonFunc.hasValue(cpoDtlBean.getSvcMachMstrPk())) {
                    continue;
                }
                // 2016/06/03 S21_NA#9275 Mod End
            }

            // 2016/06/03 S21_NA#9275 Mod Start
//          SVC_MACH_MSTRTMsgArray svcMachMstrTmsgAry = (SVC_MACH_MSTRTMsgArray) getSvcMachMstrTMsg(cpoDtlBean);
            SVC_MACH_MSTRTMsgArray svcMachMstrTmsgAry = new SVC_MACH_MSTRTMsgArray();
            if (ZYPCommonFunc.hasValue(cpoDtlBean.getSerNum())) {
                // 2016/09/05 S21_NA#12435 Mod Start
//                svcMachMstrTmsgAry = (SVC_MACH_MSTRTMsgArray) getSvcMachMstrTMsg(cpoDtlBean);
                // QC#16575 2017/03/01 UPD START
                // svcMachMstrTmsgAry = (SVC_MACH_MSTRTMsgArray) getSvcMachMstrTMsg(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getSerNum());
                svcMachMstrTmsgAry = (SVC_MACH_MSTRTMsgArray) getSvcMachMstrTMsg(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getSerNum(), cpoDtlBean.getMdseCd());
                // QC#16575 2017/03/01 UPD E N D
                // 2016/09/05 S21_NA#12435 Mod End
            } else {
                SVC_MACH_MSTRTMsg svcMachMstr = new SVC_MACH_MSTRTMsg();
                ZYPEZDItemValueSetter.setValue(svcMachMstr.glblCmpyCd, cpoDtlBean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(svcMachMstr.svcMachMstrPk, cpoDtlBean.getSvcMachMstrPk_BK());
                svcMachMstr = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(svcMachMstr);
                SVC_MACH_MSTRTMsg [] svcMachMstrList = new SVC_MACH_MSTRTMsg []{svcMachMstr};
                svcMachMstrTmsgAry.setMsgList(svcMachMstrList);
                svcMachMstrTmsgAry.setValidCount(1);
            }
            // 2016/06/03 S21_NA#9275 Mod End

            if (svcMachMstrTmsgAry.getValidCount() > 1) {
                setErrMsgIdForCpoDtl(NWZM1751E, linePMsgList, dtlIdx);
                break;
            }

            for (int machMstrIdx = 0; machMstrIdx < svcMachMstrTmsgAry.getValidCount(); machMstrIdx++) {
                // Request Mode : New
                if (RQST_DTL_NEW.equals(cpoDtlBean.getXxRqstTpCd())) {
                    // 2016/12/21 S21_NA#16416 UPD START
                    // if (!registAllocForSvcMachMstr(cpoBean, cpoDtlBean, svcMachMstrTmsgAry.no(machMstrIdx).svcMachMstrPk.getValue())) {
                    // 2017/09/29 S21_NA#21151 Del Start
                    //if (!registAllocForSvcMachMstr(cpoBean, cpoDtlBean, cpoDtlBean.getSvcMachMstrPk())) {
                    //    // 2016/12/21 S21_NA#16416 UPD E N D
                    //    return;
                    //}
                    // 2017/09/29 S21_NA#21151 Del End
                    continue;
                }

                // Request Mode : Modify
                if (RQST_DTL_MOD.equals(cpoDtlBean.getXxRqstTpCd())) {
                    if (!cpoDtlBean.getSerNum_BK().equals(cpoDtlBean.getSerNum())) {
                        // 2016/09/05 S21_NA#12435 Add Start
                        SVC_MACH_MSTRTMsgArray oldSvcMachMstrTmsgAry = new SVC_MACH_MSTRTMsgArray();
                        if (ZYPCommonFunc.hasValue(cpoDtlBean.getSerNum_BK())) {
                            // QC#16575 2017/03/01 UPD START
                            // oldSvcMachMstrTmsgAry = (SVC_MACH_MSTRTMsgArray) getSvcMachMstrTMsg(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getSerNum_BK());
                            oldSvcMachMstrTmsgAry = (SVC_MACH_MSTRTMsgArray) getSvcMachMstrTMsg(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getSerNum_BK(), cpoDtlBean.getMdseCd());
                            // QC#16575 2017/03/01 UPD E N D
                        } else {
                            SVC_MACH_MSTRTMsg oldSvcMachMstr = new SVC_MACH_MSTRTMsg();
                            ZYPEZDItemValueSetter.setValue(oldSvcMachMstr.glblCmpyCd, cpoDtlBean.getGlblCmpyCd());
                            ZYPEZDItemValueSetter.setValue(oldSvcMachMstr.svcMachMstrPk, cpoDtlBean.getSvcMachMstrPk());
                            oldSvcMachMstr = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(oldSvcMachMstr);
                            SVC_MACH_MSTRTMsg [] oldSvcMachMstrList = new SVC_MACH_MSTRTMsg []{oldSvcMachMstr};
                            oldSvcMachMstrTmsgAry.setMsgList(oldSvcMachMstrList);
                            oldSvcMachMstrTmsgAry.setValidCount(1);
                        }

                        if (svcMachMstrTmsgAry.getValidCount() > 1) {
                            setErrMsgIdForCpoDtl(NWZM1751E, linePMsgList, dtlIdx);
                            continue;
                        }
                        // S21_NA#18003 add start
                        if (oldSvcMachMstrTmsgAry.getValidCount() > 0) {
                            // 2016/09/05 S21_NA#12435 Add End
                            // 2017/09/29 S21_NA#21151 Mod Start
                            //if (!cancelAllocForSvcMachMstr(cpoBean, cpoDtlBean, oldSvcMachMstrTmsgAry.no(machMstrIdx))) { // 2016/04/05 S21_NA#5519-2 Modify I/F of cancelAllocForSvcMachMstr
                            //    return;
                            //}
                            if (isExistsSvcMachMstr(cpoDtlBean)) {
                                if (!cancelAllocForSvcMachMstr(cpoBean, cpoDtlBean, oldSvcMachMstrTmsgAry.no(machMstrIdx))) { // 2016/04/05 S21_NA#5519-2 Modify I/F of cancelAllocForSvcMachMstr
                                    return;
                                }
                            }
                            // 2017/09/29 S21_NA#21151 Mod End
                        }
                        // S21_NA#18003 add end

                        // 2017/09/29 S21_NA#21151 Del Start
                        //if (!registAllocForSvcMachMstr(cpoBean, cpoDtlBean, svcMachMstrTmsgAry.no(machMstrIdx).svcMachMstrPk.getValue())) {
                        //    return;
                        //}
                        // 2017/09/29 S21_NA#21151 Del End
                        continue;
                    }
                }

                // 2016/01/21 S21_NA#2996 Mod Start
                // Request Mode : Cancel
                //if (RQST_DTL_CANCEL.equals(cpoDtlBean.getXxRqstTpCd())) {
                //    if (!cancelAllocForSvcMachMstr(cpoBean, cpoDtlBean, svcMachMstrTmsgAry.no(machMstrIdx).svcMachMstrPk.getValue())) {
                //        return;
                //    }
                //}
                String smmCpoOrdNum = String.valueOf(svcMachMstrTmsgAry.no(machMstrIdx).cpoOrdNum.getValue());
                String smmCpoDtlLineNum = String.valueOf(svcMachMstrTmsgAry.no(machMstrIdx).cpoDtlLineNum.getValue());
                String smmCpoDtlLineSubNum = String.valueOf(svcMachMstrTmsgAry.no(machMstrIdx).cpoDtlLineSubNum.getValue());
                // 2016/04/05 S21_NA#5519-2 Add Start
                String smmTrxHdrNum = String.valueOf(svcMachMstrTmsgAry.no(machMstrIdx).trxHdrNum.getValue());
                String smmTrxLineNum = String.valueOf(svcMachMstrTmsgAry.no(machMstrIdx).trxLineNum.getValue());
                String smmTrxLineSubNum = String.valueOf(svcMachMstrTmsgAry.no(machMstrIdx).trxLineSubNum.getValue());
                // 2016/04/05 S21_NA#5519-2 Add End

                String allocFlg = String.valueOf(svcMachMstrTmsgAry.no(machMstrIdx).svcMachMaintAvalFlg.getValue());

                String cpoOrdNum = cpoBean.getCpoOrdNum();
                String cpoDtlLineNum = cpoDtlBean.getDsCpoRtrnLineNum();
                String cpoDtlLineSubNum = cpoDtlBean.getDsCpoRtrnLineSubNum();
                boolean isSavedDtl = ZYPCommonFunc.hasValue(cpoOrdNum);
                isSavedDtl &= ZYPCommonFunc.hasValue(cpoDtlLineNum);
                isSavedDtl &= ZYPCommonFunc.hasValue(cpoDtlLineSubNum);

                // 2016/04/05 S21_NA#5519-2 Add Start
                boolean isSameCpo = cpoOrdNum.equals(smmCpoOrdNum) && cpoDtlLineNum.equals(smmCpoDtlLineNum) && cpoDtlLineSubNum.equals(smmCpoDtlLineSubNum);
                boolean isSameTxt = cpoOrdNum.equals(smmTrxHdrNum) && cpoDtlLineNum.equals(smmTrxLineNum) && cpoDtlLineSubNum.equals(smmTrxLineSubNum);
                // 2016/04/05 S21_NA#5519-2 Add End

//                if (isSavedDtl //
//                        && ZYPConstant.FLG_OFF_N.equals(allocFlg) //
//                        && cpoOrdNum.equals(smmCpoOrdNum) //
//                        && cpoDtlLineNum.equals(smmCpoDtlLineNum) //
//                        && cpoDtlLineSubNum.equals(smmCpoDtlLineSubNum)) { // 2016/04/05 S21_NA#5519-2 Mod Condition
                if (isSavedDtl && ZYPConstant.FLG_OFF_N.equals(allocFlg) && (isSameCpo || isSameTxt)) {
                    if (RQST_DTL_CANCEL.equals(cpoDtlBean.getXxRqstTpCd())) {
                        // 2017/09/29 S21_NA#21151 Mod Start
                        //if (!cancelAllocForSvcMachMstr(cpoBean, cpoDtlBean, svcMachMstrTmsgAry.no(machMstrIdx))) { // 2016/04/05 S21_NA#5519-2 Modify I/F of cancelAllocForSvcMachMstr
                        //    return;
                        //}
                        if (isExistsSvcMachMstr(cpoDtlBean)) {
                            if (!cancelAllocForSvcMachMstr(cpoBean, cpoDtlBean, svcMachMstrTmsgAry.no(machMstrIdx))) { // 2016/04/05 S21_NA#5519-2 Modify I/F of cancelAllocForSvcMachMstr
                                return;
                            }
                        }
                        // 2017/09/29 S21_NA#21151 Mod End
                    }
                }
                // 2016/01/21 S21_NA#2996 Mod Start
            }
        }
    }

    /**
     * getSvcMachMstrTMsg
     * @param glblCmpyCd String
     * @param serNum String
     * @param mdseCd String
     * @return EZDTMsgArray
     */
    // QC#16575 2017/03/01 UPD START
    // 2016/09/05 S21_NA#12435 Mod Start
//    private EZDTMsgArray getSvcMachMstrTMsg(NWZC153001DetailBean cpoDtlBean) {
    // private EZDTMsgArray getSvcMachMstrTMsg(String glblCmpyCd, String serNum) {
    private EZDTMsgArray getSvcMachMstrTMsg(String glblCmpyCd, String serNum, String mdseCd) {
        SVC_MACH_MSTRTMsg svcMachMstrTmsgKey = new SVC_MACH_MSTRTMsg();

        //svcMachMstrTmsgKey.setSQLID("002");
        svcMachMstrTmsgKey.setSQLID("020");
//        svcMachMstrTmsgKey.setConditionValue("glblCmpyCd01", cpoDtlBean.getGlblCmpyCd());
//        svcMachMstrTmsgKey.setConditionValue("serNum01", cpoDtlBean.getSerNum());
        svcMachMstrTmsgKey.setConditionValue("glblCmpyCd01", glblCmpyCd);
        svcMachMstrTmsgKey.setConditionValue("serNum01", serNum);
        svcMachMstrTmsgKey.setConditionValue("mdseCd01",  mdseCd + '%');

        return S21ApiTBLAccessor.findByCondition(svcMachMstrTmsgKey);
    }
    // 2016/09/05 S21_NA#12435 Mod End
    // QC#16575 2017/03/01 UPD E N D

    /**
     * registAllocForSvcMachMstr
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     * @param svcMachMstrPk BigDecimal
     * @return boolean
     */
    private boolean registAllocForSvcMachMstr(NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean, BigDecimal svcMachMstrPk) {
        NSZC001001PMsg rmaMachMstrUpdMsg = new NSZC001001PMsg();
        addRmaModeMachMstrUpdMsg(cpoBean, cpoDtlBean, svcMachMstrPk, rmaMachMstrUpdMsg);
//        if (!callMachMstrUpdApi(rmaMachMstrUpdMsg, this.onBatchType)) {
        if (!callMachMstrUpdApi(rmaMachMstrUpdMsg)) {
            return false;
        }

        NSZC001001PMsg allocOnMachMstrUpdMsg = new NSZC001001PMsg();
        addAllocOnModeMachMstrUpdMsg(cpoBean, cpoDtlBean, svcMachMstrPk, allocOnMachMstrUpdMsg);
//        if (!callMachMstrUpdApi(allocOnMachMstrUpdMsg, this.onBatchType)) {
        if (!callMachMstrUpdApi(allocOnMachMstrUpdMsg)) {
            return false;
        }
        return true;
    }

    /**
     * addRmaModeMachMstrUpdMsg
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     * @param svcMachMstrPk BigDecimal
     */
    private void addRmaModeMachMstrUpdMsg(NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean, BigDecimal svcMachMstrPk, NSZC001001PMsg machMstrUpdMsg) {
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.glblCmpyCd, cpoDtlBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.slsDt, cpoBean.getSlsDt());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.xxModeCd, ProcessMode.RMA.code);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.stkStsCd, cpoDtlBean.getStkStsCd());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.rtrnToWhCd, cpoDtlBean.getInvtyLocCd());
        // S21_NA#4323 start
        if (ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum_BK())) {
            ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxHdrNum, cpoBean.getCpoOrdNum_BK());
        } else {
            ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxHdrNum, cpoBean.getCpoOrdNum());
        }
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxLineNum, cpoDtlBean.getDsCpoRtrnLineNum());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxLineSubNum, cpoDtlBean.getDsCpoRtrnLineSubNum());
        // if (ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum_BK())) {
        // ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.cpoOrdNum,
        // cpoBean.getCpoOrdNum_BK());
        // } else {
        // ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.cpoOrdNum,
        // cpoBean.getCpoOrdNum());
        // }
        // ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.cpoDtlLineNum,
        // cpoDtlBean.getDsCpoRtrnLineNum());
        // ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.cpoDtlLineSubNum,
        // cpoDtlBean.getDsCpoRtrnLineSubNum());
        // S21_NA#4323 end
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_TRANSIT);
    }

    /**
     * callAddHoldInfoAPI
     * @param machMstrUpdMsg NSZC001001PMsg
     * @return boolean
     */
//    private boolean callMachMstrUpdApi(NSZC001001PMsg machMstrUpdMsg, ONBATCH_TYPE onBatchTp) {
    private boolean callMachMstrUpdApi(NSZC001001PMsg machMstrUpdMsg) {
        NSZC001001 machMstrUpdApi = new NSZC001001();
        machMstrUpdApi.execute(machMstrUpdMsg, this.onBatchType);

        boolean isApiResultSuccess = true;
        if (machMstrUpdMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < machMstrUpdMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId((String) machMstrUpdMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                isApiResultSuccess = false;
            }
        }
        return isApiResultSuccess;
    }

    /**
     * addAllocOnModeMachMstrUpdMsg
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     * @param svcMachMstrPk BigDecimal
     */
    private void addAllocOnModeMachMstrUpdMsg(NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean, BigDecimal svcMachMstrPk, NSZC001001PMsg machMstrUpdMsg) {
        machMstrUpdMsg.glblCmpyCd.setValue(cpoDtlBean.getGlblCmpyCd());
        machMstrUpdMsg.slsDt.setValue(cpoBean.getSlsDt());
        machMstrUpdMsg.xxModeCd.setValue(ProcessMode.ALLOCATION_ON.code);
        machMstrUpdMsg.svcMachMstrPk.setValue(svcMachMstrPk);
        if (ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum_BK())) {
            ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxHdrNum, cpoBean.getCpoOrdNum_BK());
        } else {
            ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxHdrNum, cpoBean.getCpoOrdNum());
        }
        machMstrUpdMsg.trxLineNum.setValue(cpoDtlBean.getDsCpoRtrnLineNum());
        machMstrUpdMsg.trxLineSubNum.setValue(cpoDtlBean.getDsCpoRtrnLineSubNum());
    }

    /**
     * <pre>
     * cancelAllocForSvcMachMstr
     * 2016/04/05 S21_NA#5519-2 Modify I/F
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     * @param svcMachMstr SVC_MACH_MSTRTMsg
     * @return boolean
     * </pre>
     */
//    private boolean cancelAllocForSvcMachMstr(NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean, BigDecimal svcMachMstrPk) {
    private boolean cancelAllocForSvcMachMstr(NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean, SVC_MACH_MSTRTMsg svcMachMstr) {
        NSZC001001PMsg rmaCancMachMstrUpdMsg = new NSZC001001PMsg();
        addRmaCancModeMachMstrUpdMsg(cpoBean, cpoDtlBean, svcMachMstr, rmaCancMachMstrUpdMsg); // 2016/04/05 S21_NA#5519-2 Modify I/F
//        if (!callMachMstrUpdApi(rmaCancMachMstrUpdMsg, onBatchType)) {
        if (!callMachMstrUpdApi(rmaCancMachMstrUpdMsg)) {
            return false;
        }

        NSZC001001PMsg allocOffMachMstrUpdMsg = new NSZC001001PMsg();
        addAllocOffModeMachMstrUpdMsg(cpoBean, cpoDtlBean, svcMachMstr.svcMachMstrPk.getValue(), allocOffMachMstrUpdMsg);
//        if (!callMachMstrUpdApi(allocOffMachMstrUpdMsg, onBatchType)) {
        if (!callMachMstrUpdApi(allocOffMachMstrUpdMsg)) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * addRmaCancModeMachMstrUpdMsg
     * 2016/04/05 S21_NA#5519-2 Modify I/F
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     * @param svcMachMstr SVC_MACH_MSTRTMsg
     * @param machMstrUpdMsg NSZC001001PMsg
     * </pre>
     */
//    private void addRmaCancModeMachMstrUpdMsg(NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean, BigDecimal svcMachMstrPk, NSZC001001PMsg machMstrUpdMsg) {
    private void addRmaCancModeMachMstrUpdMsg(NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean, SVC_MACH_MSTRTMsg svcMachMstr, NSZC001001PMsg machMstrUpdMsg) {

        BigDecimal svcMachMstrPk = svcMachMstr.svcMachMstrPk.getValue();
        String latestStatusBeforeW4R = getLatestStatusBeforeWaitingForRemoval(cpoDtlBean.getGlblCmpyCd(), svcMachMstrPk); // 2016/02/24 S21_NA#3429 Add

        machMstrUpdMsg.glblCmpyCd.setValue(cpoDtlBean.getGlblCmpyCd());
        machMstrUpdMsg.slsDt.setValue(cpoBean.getSlsDt());
        machMstrUpdMsg.xxModeCd.setValue(ProcessMode.RMA_CANCEL.code);
        machMstrUpdMsg.svcMachMstrPk.setValue(svcMachMstrPk);

        // 2016/02/24 S21_NA#3429 Mod Start
        // machMstrUpdMsg.svcMachMstrStsCd.setValue(SVC_MACH_MSTR_STS.INSTALLED);
        machMstrUpdMsg.svcMachMstrStsCd.setValue(latestStatusBeforeW4R);
        // 2016/02/24 S21_NA#3429 Mod Start

        // 2016/04/05 S21_NA#5519-2 Add Start
        if (ZYPCommonFunc.hasValue(svcMachMstr.shpgPlnNum)) {
            SHPG_PLNTMsg shpgPln = new SHPG_PLNTMsg();
            ZYPEZDItemValueSetter.setValue(shpgPln.glblCmpyCd, svcMachMstr.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgPln.shpgPlnNum, svcMachMstr.shpgPlnNum);

            shpgPln = (SHPG_PLNTMsg) S21ApiTBLAccessor.findByKey(shpgPln);
            ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.cpoOrdNum, shpgPln.trxHdrNum);
            ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.cpoDtlLineNum, shpgPln.trxLineNum);
            ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.cpoDtlLineSubNum, shpgPln.trxLineSubNum);
        }
        // 2016/04/05 S21_NA#5519-2 Add End

        machMstrUpdMsg.stkStsCd.setValue(cpoDtlBean.getStkStsCd());
        if (ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum_BK())) {
            ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxHdrNum, cpoBean.getCpoOrdNum_BK());
        } else {
            ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxHdrNum, cpoBean.getCpoOrdNum());
        }
        machMstrUpdMsg.trxLineNum.setValue(cpoDtlBean.getDsCpoRtrnLineNum());
        machMstrUpdMsg.trxLineSubNum.setValue(cpoDtlBean.getDsCpoRtrnLineSubNum());
        machMstrUpdMsg.svcMachUsgStsCd.setValue(SVC_MACH_USG_STS.AT_CUSTOMER);
    }

    /**
     * addAllocOffModeMachMstrUpdMsg
     * @param pMsg NWZC155001PMsg
     * @param cpoBean NWZC153001CpoBean
     * @param cpoDtlBean NWZC153001DetailBean
     * @param svcMachMstrPk BigDecimal
     * @param machMstrUpdMsg NSZC001001PMsg
     */
    private void addAllocOffModeMachMstrUpdMsg(NWZC153001CpoBean cpoBean, NWZC153001DetailBean cpoDtlBean, BigDecimal svcMachMstrPk, NSZC001001PMsg machMstrUpdMsg) {
        machMstrUpdMsg.glblCmpyCd.setValue(cpoDtlBean.getGlblCmpyCd());
        machMstrUpdMsg.slsDt.setValue(cpoBean.getSlsDt());
        machMstrUpdMsg.xxModeCd.setValue(ProcessMode.ALLOCATION_OFF.code);
        machMstrUpdMsg.svcMachMstrPk.setValue(svcMachMstrPk);
    }

    /**
     * The error code is stored in the CPO Detail area.
     * @param errMsgId String(Message code)
     * @param linePMsgList List<NWZC153002PMsg>
     * @param line int
     */
    private static void setErrMsgIdForCpoDtl(String errMsgId, List<NWZC153002PMsg> linePMsgList, int line) {
        linePMsgList.get(line).xxMsgIdList.setValidCount(linePMsgList.get(line).xxMsgIdList.getValidCount() + 1);
        linePMsgList.get(line).xxMsgIdList.no(linePMsgList.get(line).xxMsgIdList.getValidCount() - 1).xxMsgId.setValue(errMsgId);
    }

    /**
     * The error code is stored in the CPO Detail area.
     * @param errMsgId String(Message code)
     * @param linePMsgList List<NWZC153002PMsg>
     * @param dsCpoLineNum BigDecimal
     * @param dsCpoLineSubNum BigDecimal
     */
    // 2016/01/08 #2899 Mod Start
//    private static boolean insertRtrnCalcBase(NWZC153001CpoBean cpoBean, NWZC153001PrcCalcBean prcCalcBean, List<NWZC153002PMsg> linePMsgList, BigDecimal lineNum, BigDecimal lineSubNum) {
//        CPO_RTRN_PRC_CALC_BASETMsg rtrnPrcCalcBaseTMsg = new CPO_RTRN_PRC_CALC_BASETMsg();
//
//        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.cpoRtrnPrcCalcBasePk, prcCalcBean.getCpoRtrnPrcCalcBasePk());
//        // 2015/12/16 S21_NA#2007 Add Start
//        if (!ZYPCommonFunc.hasValue(rtrnPrcCalcBaseTMsg.cpoRtrnPrcCalcBasePk)) {
//            rtrnPrcCalcBaseTMsg.cpoRtrnPrcCalcBasePk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_RTRN_PRC_CALC_BASE_SQ));
//        }
//        // 2015/12/16 S21_NA#2007 Add End
//        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
//        ZYPEZDItemValueSetter.setValue(rtrnPrcCalcBaseTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd()); // 2015/12/16 S21_NA#2007
//
//        rtrnPrcCalcBaseTMsg = addRtrnPrcCalcBaseTmsg(prcCalcBean, rtrnPrcCalcBaseTMsg);
//
//        cacheDBAccess.insertWithCache(rtrnPrcCalcBaseTMsg);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtrnPrcCalcBaseTMsg.getReturnCode())) {
//            setErrMsgIdForCpoDtl(NWZM1744E, linePMsgList, lineNum, lineSubNum);
//            return false;
//        }
//        return true;
//    }
    private static void setErrMsgIdForCpoDtl(String errMsgId, List<NWZC153002PMsg> linePMsgList, String dsCpoRtrnLineNum, String dsCpoRtrnLineSubNum) {
        boolean findFlg = false;
        for (int idx = 0; idx < linePMsgList.size(); idx++) {
            NWZC153002PMsg linePmsg = linePMsgList.get(idx);
            String listDsCpoRtrnLineNum = linePmsg.dsCpoRtrnLineNum.getValue();
            String listDsCpoRtrnLineSubNum = linePmsg.dsCpoRtrnLineSubNum.getValue();
            if (dsCpoRtrnLineNum.equals(listDsCpoRtrnLineNum) && dsCpoRtrnLineSubNum.equals(listDsCpoRtrnLineSubNum)) {
                int validCnt = linePmsg.xxMsgIdList.getValidCount();
                linePmsg.xxMsgIdList.no(validCnt).xxMsgId.setValue(errMsgId);
                linePmsg.xxMsgIdList.setValidCount(validCnt + 1);
                findFlg = true;
            }
        }

        if (findFlg) {
            return;
        }

        NWZC153002PMsg linePmsg = new NWZC153002PMsg();
        ZYPEZDItemValueSetter.setValue(linePmsg.dsCpoRtrnLineNum, dsCpoRtrnLineNum);
        ZYPEZDItemValueSetter.setValue(linePmsg.dsCpoRtrnLineSubNum, dsCpoRtrnLineSubNum);
        linePmsg.xxMsgIdList.no(0).xxMsgId.setValue(errMsgId);
        linePmsg.xxMsgIdList.setValidCount(1);
        linePMsgList.add(linePmsg);
    }
    // 2016/01/08 #2899 Mod End

    /**
     * hasError
     * @param pMsg NWZC153001PMsg
     * @return boolean
     */
    private boolean hasError(NWZC153001PMsg pMsg) {
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * hasError.
     * @param linePMsgList List<NWZC153002PMsg>
     * @return boolean no error. )
     */
    private boolean hasError(List<NWZC153002PMsg> linePMsgList) {
        for (NWZC153002PMsg linePmsg : linePMsgList) {
            if (linePmsg.xxMsgIdList.getValidCount() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * findByKeyWithCache
     * @param reqTMsg EZDTMsg
     * @return EZDTMsg
     */
    private static EZDTMsg findByKeyWithCache(EZDTMsg reqTMsg) {
        return S21CacheTBLAccessor.findByKey(reqTMsg);
    }

    /**
     * getOrdTakeMdseTMsg
     * @param glblCmpyCd String
     * @param cd String
     * @return EZDTMsg
     */
//    private static EZDTMsg getOrdTakeMdseTMsg(String glblCmpyCd, String cd) {
//        if (!ZYPCommonFunc.hasValue(cd)) {
//            return null;
//        }
//
//        final ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
//        tMsg.glblCmpyCd.setValue(glblCmpyCd);
//        tMsg.ordTakeMdseCd.setValue(cd);
//
//        return findByKeyWithCache(tMsg);
//    }

    /**
     * getShipToTMsg
     * @param glblCmpyCd String
     * @param cd String
     * @param rgstStsCd String
     * @return EZDTMsgArray
     */
    private static EZDTMsgArray getShipToTMsg(String glblCmpyCd, String cd, String rgstStsCd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return null;
        }

        final SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
        if (ZYPCommonFunc.hasValue(rgstStsCd)) {
            tMsg.setSQLID("003");
        } else {
            tMsg.setSQLID("004");
        }
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("shipToCustCd01", cd);
        if (ZYPCommonFunc.hasValue(rgstStsCd)) {
            tMsg.setConditionValue("rgtnStsCd01", rgstStsCd);
        }

        return S21ApiTBLAccessor.findByCondition(tMsg);
    }

    /**
     * getCntyTMsg
     * @param glblCmpyCd String
     * @param pk BigDecimal
     * @return EZDTMsg
     */
    private static EZDTMsg getCntyTMsg(String glblCmpyCd, BigDecimal pk) {
        if (!ZYPCommonFunc.hasValue(pk)) {
            return null;
        }

        final CNTYTMsg tMsg = new CNTYTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.cntyPk.setValue(pk);

        return findByKeyWithCache(tMsg);
    }

    /**
     * getSellToCustTMsg
     * @param glblCmpyCd String
     * @param cd String
     * @param rgstStsCd String
     * @return EZDTMsgArray
     */
    private static EZDTMsgArray getSellToCustTMsg(String glblCmpyCd, String cd, String rgstStsCd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return null;
        }

        final SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();

        if (ZYPCommonFunc.hasValue(rgstStsCd)) {
            tMsg.setSQLID("029");
        } else {
            tMsg.setSQLID("013");
        }

        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("sellToCustCd01", cd);
        if (ZYPCommonFunc.hasValue(rgstStsCd)) {
            tMsg.setConditionValue("rgtnStsCd01", rgstStsCd);
        }

        return S21ApiTBLAccessor.findByCondition(tMsg);
    }

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * getCpoTMsgForUpdate
//     * @param glblCmpyCd String
//     * @param cpoNum String
//     * @return EZDTMsg
//     */
//    private static EZDTMsg getCpoTMsgForUpdate(String glblCmpyCd, String cpoNum) {
//        CPOTMsg updTMsgKey = new CPOTMsg();
//
//        ZYPEZDItemValueSetter.setValue(updTMsgKey.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(updTMsgKey.cpoOrdNum, cpoNum);
//
//        CPOTMsg updTMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(updTMsgKey);
//        if (updTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
//            return null;
//        }
//        return updTMsg;
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    /**
     * getCpoTMsg
     * @param glblCmpyCd String
     * @param cd String
     * @return EZDTMsg
     */
    private static EZDTMsg getCpoTMsg(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return null;
        }

        final CPOTMsg tMsg = new CPOTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.cpoOrdNum.setValue(cd);

//        return findByKeyWithCache(tMsg);
        return S21FastTBLAccessor.findByKey(tMsg);
    }

    /**
     * getMdseTMsg
     * @param glblCmpyCd String
     * @param cd String
     * @return EZDTMsg
     */
    private static EZDTMsg getMdseTMsg(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return null;
        }

        final MDSETMsg tMsg = new MDSETMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.mdseCd.setValue(cd);

        return findByKeyWithCache(tMsg);
    }

    /**
     * getpmtTermCashDiscTMsg
     * @param glblCmpyCd String
     * @param cd String
     * @return EZDTMsg
     */
    private static EZDTMsg getPmtTermCashDiscTMsg(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return null;
        }

        final PMT_TERM_CASH_DISCTMsg tMsg = new PMT_TERM_CASH_DISCTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.pmtTermCashDiscCd.setValue(cd);

        return findByKeyWithCache(tMsg);
    }

    /**
     * existsCpoOrdTp
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsCpoOrdTp(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final CPO_ORD_TPTMsg tMsg = new CPO_ORD_TPTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.cpoOrdTpCd.setValue(cd);

        return findByKeyWithCache(tMsg) != null;
    }

    /**
     * existsMdseCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsMdseCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final MDSETMsg tMsg = new MDSETMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.mdseCd.setValue(cd);

        return findByKeyWithCache(tMsg) != null;
    }

    // 2016/02/18 S21_NA#2336 Del Start
    /**
     * existsOrigMdseCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
//    private static boolean existsOrigMdseCd(String glblCmpyCd, String cd) {
//        if (!ZYPCommonFunc.hasValue(cd)) {
//            return true;
//        }
//
//        final MDSETMsg tMsg = new MDSETMsg();
//
//        tMsg.setSQLID("078");
//        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        tMsg.setConditionValue("origMdseCd01", cd);
//
//        return S21ApiTBLAccessor.findByCondition(tMsg).getValidCount() > 0;
//    }
    // 2016/02/18 S21_NA#2336 Del End

    /**
     * existsShipToCust
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsShipToCust(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("shipToCustCd01", cd);

        return S21ApiTBLAccessor.findByCondition(tMsg).getValidCount() > 0;
    }

    /**
     * existsShipToCtryCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsShipToCtryCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final CTRYTMsg tMsg = new CTRYTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.ctryCd.setValue(cd);

        return findByKeyWithCache(tMsg) != null;
    }

    /**
     * existsShipToStCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsShipToStCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final STTMsg tMsg = new STTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.stCd.setValue(cd);

        return findByKeyWithCache(tMsg) != null;
    }

    /**
     * existsCcyCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsCcyCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final CCYTMsg tMsg = new CCYTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.ccyCd.setValue(cd);

        return findByKeyWithCache(tMsg) != null;
    }

    /**
     * existsStkStsCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsStkStsCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final STK_STSTMsg tMsg = new STK_STSTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.stkStsCd.setValue(cd);

        return findByKeyWithCache(tMsg) != null;
    }

    /**
     * existsPmtTermCashDescCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsPmtTermCashDescCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final PMT_TERM_CASH_DISCTMsg tMsg = new PMT_TERM_CASH_DISCTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.pmtTermCashDiscCd.setValue(cd);

        return findByKeyWithCache(tMsg) != null;
    }

    /**
     * existsTocCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsTocCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final TOCTMsg tMsg = new TOCTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.tocCd.setValue(cd);

        return findByKeyWithCache(tMsg) != null;
    }

    /**
     * existsCustomUomCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsCustomUomCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final PKG_UOMTMsg tMsg = new PKG_UOMTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.pkgUomCd.setValue(cd);

        return findByKeyWithCache(tMsg) != null;
    }

    /**
     * existsCpoSrcTpCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private boolean existsCpoSrcTpCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final CPO_SRC_TPTMsg tMsg = new CPO_SRC_TPTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.cpoSrcTpCd.setValue(cd);

        return findByKeyWithCache(tMsg) != null;
    }

    /**
     * existsOrdFuflLvlCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsOrdFuflLvlCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final ORD_FUFL_LVLTMsg tMsg = new ORD_FUFL_LVLTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.ordFuflLvlCd.setValue(cd);

        return findByKeyWithCache(tMsg) != null;
    }

    /**
     * existsBillToCustCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsBillToCustCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final BILL_TO_CUSTTMsg tMsg = new BILL_TO_CUSTTMsg();

        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("billToCustCd01", cd);

        return S21ApiTBLAccessor.findByCondition(tMsg).getValidCount() > 0;
    }

    /**
     * existsSellToCustCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private static boolean existsSellToCustCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();

        tMsg.setSQLID("013");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("sellToCustCd01", cd);

        return S21ApiTBLAccessor.findByCondition(tMsg).getValidCount() > 0;
    }

    /**
     * existsSysSrcCd
     * @param glblCmpyCd String
     * @param cd String
     * @return boolean
     */
    private boolean existsSysSrcCd(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return true;
        }

        final SYS_SRCTMsg tMsg = new SYS_SRCTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.sysSrcCd.setValue(cd);

        return findByKeyWithCache(tMsg) != null;
    }

    /**
     * getDsCpoRtrnDtlTMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param rtrnLineNum String
     * @param rtrnLineSubNum String
     * @return EZDTMsg
     */
    private static EZDTMsg getDsCpoRtrnDtlTMsg(String glblCmpyCd, String cpoNum, String rtrnLineNum, String rtrnLineSubNum) {
        if (!ZYPCommonFunc.hasValue(cpoNum) && (!ZYPCommonFunc.hasValue(rtrnLineNum))) {
            return null;
        }

        final DS_CPO_RTRN_DTLTMsg tMsg = new DS_CPO_RTRN_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, cpoNum);
        ZYPEZDItemValueSetter.setValue(tMsg.dsCpoRtrnLineNum, rtrnLineNum);
        ZYPEZDItemValueSetter.setValue(tMsg.dsCpoRtrnLineSubNum, rtrnLineSubNum);

//        return findByKeyWithCache(tMsg);
        return S21FastTBLAccessor.findByKey(tMsg);
    }

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * getCcyTMsg
//     * @param glblCmpyCd String
//     * @param ccyCd String
//     * @return EZDTMsg
//     */
//    private static EZDTMsg getCcyTMsg(String glblCmpyCd, String ccyCd) {
//        if (!ZYPCommonFunc.hasValue(ccyCd)) {
//            return null;
//        }
//
//        final CCYTMsg tMsg = new CCYTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, ccyCd);
//
//        return findByKeyWithCache(tMsg);
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * getGlblCmpyTMsg
//     * @param glblCmpyCd String
//     * @return EZDTMsg
//     */
//    private static EZDTMsg getGlblCmpyTMsg(String glblCmpyCd) {
//        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
//            return null;
//        }
//
//        final GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//
//        return findByKeyWithCache(tMsg);
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    /**
     * getDsCpoRtrnDtlTMsgForUpdate
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param rtrnLineNum String
     * @param rtrnLineSubNum String
     * @return EZDTMsg
     */
    private static EZDTMsg getDsCpoRtrnDtlTMsgForUpdate(String glblCmpyCd, String cpoNum, String rtrnLineNum, String rtrnLineSubNum) {
        DS_CPO_RTRN_DTLTMsg updTMsgKey = new DS_CPO_RTRN_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(updTMsgKey.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(updTMsgKey.cpoOrdNum, cpoNum);
        ZYPEZDItemValueSetter.setValue(updTMsgKey.dsCpoRtrnLineNum, rtrnLineNum);
        ZYPEZDItemValueSetter.setValue(updTMsgKey.dsCpoRtrnLineSubNum, rtrnLineSubNum);

        DS_CPO_RTRN_DTLTMsg updTMsg = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(updTMsgKey);
        if (updTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return null;
        }
        return updTMsg;
    }

    /**
     * getRtrnPrcCalcBaseTMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param rtrnLineNum String
     * @param rtrnLineSubNum String
     * @return CPO_RTRN_PRC_CALC_BASETMsgArray
     */
    private static CPO_RTRN_PRC_CALC_BASETMsgArray getRtrnPrcCalcBaseTMsg(String glblCmpyCd, String cpoOrdNum, String rtrnLineNum, String rtrnLineSubNum, boolean updFlg) { // 2016/06/20 S21_NA#5331-3
        CPO_RTRN_PRC_CALC_BASETMsg updTMsgKey = new CPO_RTRN_PRC_CALC_BASETMsg();

        updTMsgKey.setSQLID("001");
        updTMsgKey.setConditionValue("glblCmpyCd01", glblCmpyCd);
        updTMsgKey.setConditionValue("cpoOrdNum01", cpoOrdNum);
        updTMsgKey.setConditionValue("dsCpoRtrnLineNum01", rtrnLineNum);
        updTMsgKey.setConditionValue("dsCpoRtrnLineSubNum01", rtrnLineSubNum);

		// 2016/06/20 S21_NA#5331-3 Mod Start
        // CPO_RTRN_PRC_CALC_BASETMsgArray updTMsg = (CPO_RTRN_PRC_CALC_BASETMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(updTMsgKey);
        CPO_RTRN_PRC_CALC_BASETMsgArray updTMsg = null;
        if (updFlg) {
            updTMsg = (CPO_RTRN_PRC_CALC_BASETMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(updTMsgKey);
        } else {
            updTMsg = (CPO_RTRN_PRC_CALC_BASETMsgArray) S21ApiTBLAccessor.findByCondition(updTMsgKey);
        }
		// 2016/06/20 S21_NA#5331-3 Mod End

        return updTMsg;
    }

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * getCpoDtlTMsgArray
//     * @param glblCmpyCd String
//     * @param cpoOrdNum String
//     * @return CPO_DTLTMsgArray
//     */
//    private CPO_DTLTMsgArray getCpoDtlTMsgArray(String glblCmpyCd, String cpoOrdNum) {
//        FindCondition fc = new FindCondition("001");
//        fc.addCondition("glblCmpyCd01", glblCmpyCd);
//        fc.addCondition("cpoOrdNum01", cpoOrdNum);
//
//        return (CPO_DTLTMsgArray) localCache.cpoDtlCache.getTMsgArray(fc);
//    }
//
//    /**
//     * getDsCpoRtrnDtlTMsgArray
//     * @param glblCmpyCd String
//     * @param cpoOrdNum String
//     * @return DS_CPO_RTRN_DTLTMsgArray
//     */
//    private DS_CPO_RTRN_DTLTMsgArray getDsCpoRtrnDtlTMsgArray(String glblCmpyCd, String cpoOrdNum) {
//        FindCondition fc = new FindCondition("001");
//        fc.addCondition("glblCmpyCd01", glblCmpyCd);
//        fc.addCondition("cpoOrdNum01", cpoOrdNum);
//
//        return (DS_CPO_RTRN_DTLTMsgArray) localCache.dsCpoRtrnDtlCache.getTMsgArray(fc);
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    /**
     * <pre>
     * getDsCpoRtrnDtlTMsgArray
     * rewrite original, then there is no update comments.
     * </pre>
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param rtrnLineNum String
     * @param rtrnLineSubNum String
     * @return DS_CPO_RTRN_PRC_DTLTMsgArray
     */
    private List<DS_CPO_RTRN_PRC_DTLTMsg> getDsCpoRtrnPrcDtlTMsgArray(String glblCmpyCd, String cpoOrdNum, String rtrnLineNum, String rtrnLineSubNum) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("glblCmpyCd", glblCmpyCd);
        queryMap.put("cpoOrdNum", cpoOrdNum);
        queryMap.put("dsCpoRtrnLineNum", rtrnLineNum);
        queryMap.put("dsCpoRtrnLineSubNum", rtrnLineSubNum);

        return (List<DS_CPO_RTRN_PRC_DTLTMsg>) ssmClient.queryObjectList("getDsCpoRtrnPrcDtlTMsgArray", queryMap);
    }

    /**
     * getDsCpoRtrnDtlTMsgArray
     * @param glblCmpyCd String
     * @param custUomCd String
     * @param mdseCd String
     * @param pkgApvlStsCd String
     * @return MDSE_STORE_PKGTMsgArray
     */
    private MDSE_STORE_PKGTMsgArray getMdseStorPkgTMsgArray(String glblCmpyCd, String custUomCd, String mdseCd, String pkgApvlStsCd) {
        final FindCondition fc = new FindCondition("001");
        fc.addCondition("glblCmpyCd01", glblCmpyCd);
        fc.addCondition("pkgUomCd01", custUomCd);
        fc.addCondition("mdseCd01", mdseCd);
        fc.addCondition("qtyPkgApvlStsCd01", APVL_STS.SUBMITTED);

        return (MDSE_STORE_PKGTMsgArray) localCache.mdseStorePkgCache.getTMsgArray(fc);
    }

    /**
     * Local data chache class. used by master data, transaction
     * data...
     */
    private static final class LocalCache {
        /** Cache for CPO_DTL */
//        private final CpoDtlCache cpoDtlCache = new CpoDtlCache();

        /** Cache for DS_CPO_RTRN_DTL */
//        private final DsCpoRtrnDtlCache dsCpoRtrnDtlCache = new DsCpoRtrnDtlCache();

        /** Cache for MDSE_STORE_PKG */
        private final MdseStorePkgCache mdseStorePkgCache = new MdseStorePkgCache();

        /** Cache for S21LRU */
        private final S21LRUMap<String, BigDecimal> exchangeRateCache = new S21LRUMap<String, BigDecimal>();
    }

    // 2015/12/17 S21_NA#2007 Add Start
    private boolean isCancelDtl(NWZC153001DetailBean cpoDtlBean) {
        String xxRqstTpCd = getRtrnLineRqstTpCd(cpoDtlBean);
        String rtrnLineStsCd = getRtrnLineStsCd(cpoDtlBean);

        boolean rslt = NWZC153001Constant.RQST_DTL_CANCEL.equals(xxRqstTpCd);
        rslt |= RTRN_LINE_STS.CANCELLED.equals(rtrnLineStsCd);

        return rslt;
    }
    private String getRtrnLineRqstTpCd(NWZC153001DetailBean cpoDtlBean) {
        String xxRqstTpCd = cpoDtlBean.getXxRqstTpCd();
        if (null == xxRqstTpCd) {
            xxRqstTpCd = "";
        }
        return xxRqstTpCd;
    }

    private String getRtrnLineStsCd(NWZC153001DetailBean cpoDtlBean) {
        String rtrnLineStsCd = cpoDtlBean.getRtrnLineStsCd();
        if (null == rtrnLineStsCd) {
            rtrnLineStsCd = "";
        }
        return rtrnLineStsCd;
    }

//    private boolean isPureReturn(NWZC153001PMsg pMsg) { 2016/01/14 S21_NA#2996 Add 2nd parameter
    private boolean isPureReturn(NWZC153001PMsg pMsg, CPO_DTLTMsgArray cpoDtlTMsgArray) {
//        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd) || !ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
//            return false;
//        }

        // 2016/01/14 S21_NA#2996 Add Start
        if (ZYPCommonFunc.hasValue(pMsg.xxScrEdtTpCd)) {
            if (NWZC153001Constant.SCRN_EDT_TP_PURE.equals(pMsg.xxScrEdtTpCd.getValue())) {
                return true;
            } else {
                return false;
            }
        }
        // 2016/01/14 S21_NA#2996 Del Start
        // 2016/01/14 S21_NA#2996 Add End
        //CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        //cpoDtlTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        //cpoDtlTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());
        //cpoDtlTMsg.setSQLID("008");

        //CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(cpoDtlTMsg);

        //int cntValidCpoDtl = 0;
        //for (int i = 0; i < cpoDtlTMsgArray.getValidCount(); i++) {
        //  CPO_DTLTMsg cpoDtl = cpoDtlTMsgArray.no(i);
        //  if (!ORD_LINE_STS.CANCELLED.equals(cpoDtl.ordLineStsCd.getValue())) {
        //      cntValidCpoDtl++;
        //      break;
        //  }
        //}
        //return cntValidCpoDtl == 0;
        // 2016/01/14 S21_NA#2996 Del End
        // 2016/01/14 S21_NA#2996 Add Start
        if (null == cpoDtlTMsgArray) {
            return true;
        }
        boolean isAllRelatedCpoDtlCancelled = isAllRelatedCpoDtlCancelled(cpoDtlTMsgArray);
        if (isAllRelatedCpoDtlCancelled) {
            return true;
        }
        return false;
        // 2016/01/14 S21_NA#2996 Add End
    }
    // 2015/12/17 S21_NA#2007 Add End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    // 2016/01/07 S21_NA#2588 Add Start
//    private void execUpdCpoAmtMode(NWZC153001PMsg pMsg) {
//        boolean errFlg = false;
//        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
//            msgMap.addXxMsgId(NWZM0163E);
//            errFlg = true;
//        }
//        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
//            msgMap.addXxMsgId(NWZM0002E);
//            errFlg = true;
//        }
//        if (errFlg) {
//            return;
//        }
//
//        // Calculate Header Amount
//        NWZC153001CpoBean cpoBean = new NWZC153001CpoBean(pMsg);
//        initializeAmount(cpoBean);
//        setAmountFromCpoDtl(cpoBean);
//        setAmountFromCpoRtrnDtl(cpoBean);
//
//        // Discount Amout
//        calcCpoDiscAmt(cpoBean);
//
//        // UPDATE
//        CPOTMsg updCpoTMsg = new CPOTMsg();
//        updCpoTMsg = (CPOTMsg) getCpoTMsgForUpdate(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
//
//        if (null == updCpoTMsg) {
//            msgMap.addXxMsgId(NWZM1368E);
//            return;
//        }
//
//        addUpdCpoTMsgForModifyAmt(cpoBean, updCpoTMsg, false);
//
//        S21ApiTBLAccessor.update(updCpoTMsg);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updCpoTMsg.getReturnCode())) {
//            msgMap.addXxMsgId(NWZM1368E);
//            return;
//        }
//        return;
//    }
//    // 2016/01/07 S21_NA#2588 Add End
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2016/01/14 S21_NA#2588 Add Start
    private boolean isAllRelatedCpoDtlCancelled(CPO_DTLTMsgArray cpoDtlTMsgArray) {
        if (null == cpoDtlTMsgArray) {
            return false;
        }
        int validCnt = cpoDtlTMsgArray.getValidCount();
        // 2016/02/24 S21_NA#3429 Add Start
        if (0 == validCnt) {
            return false;
        }
        // 2016/02/24 S21_NA#3429 Add End
        int cancelledCnt = 0;
        for (int i = 0; i < validCnt; i++) {
            CPO_DTLTMsg cpoDtl = cpoDtlTMsgArray.no(i);
            if (ORD_LINE_STS.CANCELLED.equals(cpoDtl.ordLineStsCd.getValue())) {
                cancelledCnt++;
            }
        }
        return validCnt == cancelledCnt;
    }

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    private boolean isAllRelatedCpoDtlClosed(CPO_DTLTMsgArray cpoDtlTMsgArray) {
//        if (null == cpoDtlTMsgArray) {
//            return false;
//        }
//        int validCnt = cpoDtlTMsgArray.getValidCount();
//        // 2016/02/24 S21_NA#3429 Add Start
//        if (0 == validCnt) {
//            return false;
//        }
//        // 2016/02/24 S21_NA#3429 Add End
//        int cancelledCnt = 0;
//        for (int i = 0; i < validCnt; i++) {
//            CPO_DTLTMsg cpoDtl = cpoDtlTMsgArray.no(i);
//            if (ORD_LINE_STS.CLOSED.equals(cpoDtl.ordLineStsCd.getValue())) {
//                cancelledCnt++;
//            }
//        }
//        return validCnt == cancelledCnt;
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    private CPO_DTLTMsgArray getRelatedCpoDtlArray(String glblCmpyCd, String cpoOrdNum) {
        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        cpoDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        cpoDtlTMsg.setSQLID("008");

        return (CPO_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(cpoDtlTMsg);
    }
    // 2016/01/14 S21_NA#2588 Add End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    // 2016/01/18 S21_NA#2966 Add Start
//    private void getHdrAmoutForNotCanceled(NWZC153001PMsg pMsg, CPOTMsg cpoTMsg) {
//        Map<String, String> queryMap = new HashMap<String, String>();
//        queryMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        queryMap.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
//        queryMap.put("ordLineStsCancel", ORD_LINE_STS.CANCELLED);
//
//        List<Map<String, Object>> queryRsltList = (List<Map<String, Object>>) ssmClient.queryObjectList("getHdrAmoutForNotCanceled", queryMap);
//
//        if (null == queryRsltList || queryRsltList.isEmpty()) {
//            cpoTMsg.entCpoTotDealSlsAmt.setValue(BigDecimal.ZERO);
//            cpoTMsg.entCpoTotDealDiscAmt.setValue(BigDecimal.ZERO);
//            cpoTMsg.entCpoTotDealNetAmt.setValue(BigDecimal.ZERO);
//            cpoTMsg.entCpoTotFuncSlsAmt.setValue(BigDecimal.ZERO);
//            cpoTMsg.entCpoTotFuncDiscAmt.setValue(BigDecimal.ZERO);
//            cpoTMsg.entCpoTotFuncNetAmt.setValue(BigDecimal.ZERO);
//            cpoTMsg.cpoTotDealSlsAmt.setValue(BigDecimal.ZERO);
//            cpoTMsg.cpoTotDealDiscAmt.setValue(BigDecimal.ZERO);
//            cpoTMsg.cpoTotDealNetAmt.setValue(BigDecimal.ZERO);
//            cpoTMsg.cpoTotFuncNetAmt.setValue(BigDecimal.ZERO);
//            cpoTMsg.cpoTotFuncSlsAmt.setValue(BigDecimal.ZERO);
//            cpoTMsg.cpoTotFuncDiscAmt.setValue(BigDecimal.ZERO);
//        } else {
//            BigDecimal entCpoTotDealSlsAmt = BigDecimal.ZERO;
//            BigDecimal entCpoTotDealDiscAmt = BigDecimal.ZERO;
//            BigDecimal entCpoTotDealNetAmt = BigDecimal.ZERO;
//            BigDecimal entCpoTotFuncSlsAmt = BigDecimal.ZERO;
//            BigDecimal entCpoTotFuncDiscAmt = BigDecimal.ZERO;
//            BigDecimal entCpoTotFuncNetAmt = BigDecimal.ZERO;
//            BigDecimal cpoTotDealSlsAmt = BigDecimal.ZERO;
//            BigDecimal cpoTotDealDiscAmt = BigDecimal.ZERO;
//            BigDecimal cpoTotDealNetAmt = BigDecimal.ZERO;
//            BigDecimal cpoTotFuncNetAmt = BigDecimal.ZERO;
//            BigDecimal cpoTotFuncSlsAmt = BigDecimal.ZERO;
//            BigDecimal cpoTotFuncDiscAmt = BigDecimal.ZERO;
//
//            for (Map<String, Object> queryRslt : queryRsltList) {
//                entCpoTotDealSlsAmt = entCpoTotDealSlsAmt.add(getBigDecimalValue(queryRslt, "ENT_CPO_TOT_DEAL_SLS_AMT"));
//                entCpoTotDealDiscAmt = entCpoTotDealDiscAmt.add(getBigDecimalValue(queryRslt, "ENT_CPO_TOT_DEAL_DISC_AMT"));
//                entCpoTotDealNetAmt = entCpoTotDealNetAmt.add(getBigDecimalValue(queryRslt, "ENT_CPO_TOT_DEAL_NET_AMT"));
//                entCpoTotFuncSlsAmt = entCpoTotFuncSlsAmt.add(getBigDecimalValue(queryRslt, "ENT_CPO_TOT_FUNC_SLS_AMT"));
//                entCpoTotFuncDiscAmt = entCpoTotFuncDiscAmt.add(getBigDecimalValue(queryRslt, "ENT_CPO_TOT_FUNC_DISC_AMT"));
//                entCpoTotFuncNetAmt = entCpoTotFuncNetAmt.add(getBigDecimalValue(queryRslt, "ENT_CPO_TOT_FUNC_NET_AMT"));
//                cpoTotDealSlsAmt = cpoTotDealSlsAmt.add(getBigDecimalValue(queryRslt, "CPO_TOT_DEAL_SLS_AMT"));
//                cpoTotDealDiscAmt = cpoTotDealDiscAmt.add(getBigDecimalValue(queryRslt, "CPO_TOT_DEAL_DISC_AMT"));
//                cpoTotDealNetAmt = cpoTotDealNetAmt.add(getBigDecimalValue(queryRslt, "CPO_TOT_DEAL_NET_AMT"));
//                cpoTotFuncNetAmt = cpoTotFuncNetAmt.add(getBigDecimalValue(queryRslt, "CPO_TOT_FUNC_NET_AMT"));
//                cpoTotFuncSlsAmt = cpoTotFuncSlsAmt.add(getBigDecimalValue(queryRslt, "CPO_TOT_FUNC_SLS_AMT"));
//                cpoTotFuncDiscAmt = cpoTotFuncDiscAmt.add(getBigDecimalValue(queryRslt, "CPO_TOT_FUNC_DISC_AMT"));
//            }
//            cpoTMsg.entCpoTotDealSlsAmt.setValue(entCpoTotDealSlsAmt);
//            cpoTMsg.entCpoTotDealDiscAmt.setValue(entCpoTotDealDiscAmt);
//            cpoTMsg.entCpoTotDealNetAmt.setValue(entCpoTotDealNetAmt);
//            cpoTMsg.entCpoTotFuncSlsAmt.setValue(entCpoTotFuncSlsAmt);
//            cpoTMsg.entCpoTotFuncDiscAmt.setValue(entCpoTotFuncDiscAmt);
//            cpoTMsg.entCpoTotFuncNetAmt.setValue(entCpoTotFuncNetAmt);
//            cpoTMsg.cpoTotDealSlsAmt.setValue(cpoTotDealSlsAmt);
//            cpoTMsg.cpoTotDealDiscAmt.setValue(cpoTotDealDiscAmt);
//            cpoTMsg.cpoTotDealNetAmt.setValue(cpoTotDealNetAmt);
//            cpoTMsg.cpoTotFuncNetAmt.setValue(cpoTotFuncNetAmt);
//            cpoTMsg.cpoTotFuncSlsAmt.setValue(cpoTotFuncSlsAmt);
//            cpoTMsg.cpoTotFuncDiscAmt.setValue(cpoTotFuncDiscAmt);
//        }
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    private BigDecimal getBigDecimalValue(Map<String, Object> map, String key) {
//        if (null == map.get(key)) {
//            return BigDecimal.ZERO;
//        } else {
//            return (BigDecimal) map.get(key);
//        }
//    }
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End

    // 2016/01/18 S21_NA#2966 Add Start

    // 2016/02/18 S21_NA#2336 Add Start
    private List<NWZC153002PMsg> checkError(List<NWZC153002PMsg> linePMsgList) {

        List<NWZC153002PMsg> errLinePMsgList = new ArrayList<NWZC153002PMsg>(0);
        for (NWZC153002PMsg linePMsg : linePMsgList) {
            if (linePMsg.xxMsgIdList.getValidCount() > 0) {
                NWZC153002PMsg errLinePMsg = new NWZC153002PMsg();
                EZDMsg.copy(linePMsg, null, errLinePMsg, null);
                errLinePMsgList.add(errLinePMsg);
            }
        }
        return errLinePMsgList;
    }
    // 2016/02/18 S21_NA#2336 Add End

    // 2016/02/24 S21_NA#3429 Add Start
    private String getLatestStatusBeforeWaitingForRemoval(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        List<SVC_MACH_MSTR_HISTTMsg> svcMachMstrHistList = getSvcMachMstrHist(glblCmpyCd, svcMachMstrPk);
        // 2017/03/07 S21_NA#17993 Mod Start
//        if (svcMachMstrHistList.size() < 2) {
//            return SVC_MACH_MSTR_STS.INSTALLED;
//        }
//
//        for (int recCnt = 0; recCnt < svcMachMstrHistList.size(); recCnt++) {
//            SVC_MACH_MSTR_HISTTMsg svcMachMstrHistTMsg = svcMachMstrHistList.get(recCnt);
//            String svcMachMstrStsCd = svcMachMstrHistTMsg.svcMachMstrStsCd.getValue();
//            if (SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachMstrStsCd)) {
//                recCnt++; // For Next Waintig For Remove (Alloc FLog 'Y')
//                recCnt++; // For Before Waiting For Removal
//                if (recCnt < svcMachMstrHistList.size()) {
//                    // Return status before Waiting For Removal.
//                    return svcMachMstrHistList.get(recCnt).svcMachMstrStsCd.getValue();
//                }
//                break;
//            }
//        }
//        return SVC_MACH_MSTR_STS.INSTALLED;
        List<String> changeToStsList = new ArrayList<String>(3);
        changeToStsList.add(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        changeToStsList.add(SVC_MACH_MSTR_STS.INSTALLED);
        changeToStsList.add(SVC_MACH_MSTR_STS.DEALER_SERVICE);

        String rtrnStatus = "";
        for (int recCnt = 0; recCnt < svcMachMstrHistList.size(); recCnt++) {
            SVC_MACH_MSTR_HISTTMsg svcMachMstrHistTMsg = svcMachMstrHistList.get(recCnt);
            String svcMachMstrStsCd = svcMachMstrHistTMsg.svcMachMstrStsCd.getValue();
            if (SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachMstrStsCd)) {
                for (; recCnt < svcMachMstrHistList.size(); recCnt++) {
                    String curSvcMachMstrStsCd = svcMachMstrHistList.get(recCnt).svcMachMstrStsCd.getValue();
                    if (changeToStsList.contains(curSvcMachMstrStsCd)) {
                        rtrnStatus = curSvcMachMstrStsCd;
                        break;
                    }
                }
            }
            if (S21StringUtil.isNotEmpty(rtrnStatus)) {
                break;
            }
        }
        return rtrnStatus;
        // 2017/03/07 S21_NA#17993 Mod End
    }
    private List<SVC_MACH_MSTR_HISTTMsg> getSvcMachMstrHist(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", glblCmpyCd);
        queryMap.put("svcMachMstrPk", svcMachMstrPk);

        return (List<SVC_MACH_MSTR_HISTTMsg>) ssmClient.queryObjectList("getSvcMachMstrHist", queryMap);
    }

    // 2017/04/07 S21_NA#Review structure Lv.2 Del Start
//    private boolean isAllRelatedDsCpoRtrnDtlClosed(DS_CPO_RTRN_DTLTMsgArray dsCpoRtrnDtlTMsgArray) {
//        if (null == dsCpoRtrnDtlTMsgArray) {
//            return false;
//        }
//        int validCnt = dsCpoRtrnDtlTMsgArray.getValidCount();
//
//        if (0 == validCnt) {
//            return false;
//        }
//
//        int closedCnt = 0;
//        for (int i = 0; i < validCnt; i++) {
//            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtl = dsCpoRtrnDtlTMsgArray.no(i);
//            if (RTRN_LINE_STS.CLOSED.equals(dsCpoRtrnDtl.rtrnLineStsCd.getValue())) {
//                closedCnt++;
//            }
//        }
//        return validCnt == closedCnt;
//    }
//
//    private boolean isAllRelatedDsCpoRtrnDtlCancelled(DS_CPO_RTRN_DTLTMsgArray dsCpoRtrnDtlTMsgArray) {
//        if (null == dsCpoRtrnDtlTMsgArray) {
//            return false;
//        }
//        int validCnt = dsCpoRtrnDtlTMsgArray.getValidCount();
//
//        if (0 == validCnt) {
//            return false;
//        }
//
//        int canceledCnt = 0;
//        for (int i = 0; i < validCnt; i++) {
//            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtl = dsCpoRtrnDtlTMsgArray.no(i);
//            if (RTRN_LINE_STS.CANCELLED.equals(dsCpoRtrnDtl.rtrnLineStsCd.getValue())) {
//                canceledCnt++;
//            }
//        }
//        return validCnt == canceledCnt;
//    }
//    // 2016/02/24 S21_NA#3429 Add End
    // 2017/04/07 S21_NA#Review structure Lv.2 Del End
    // 2016/06/06 S21_NA#9374 Add Start
    private Integer getSetCmptStsCnt(String glblCmpyCd, String cpoOrdNum, String dsCpoRtrnLineNum, String dsCpoRtrnLineSubNum, String rtrnLineStsCd) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("dsCpoRtrnLineNum", dsCpoRtrnLineNum);
        ssmParam.put("dsCpoRtrnLineSubNum", dsCpoRtrnLineSubNum);
        ssmParam.put("rtrnLineStsCd", rtrnLineStsCd);

        return (Integer) ssmClient.queryObject("getSetCmptStsCnt", ssmParam);
    }
    // 2016/06/06 S21_NA#9374 Add End

    // 2016/09/27 S21_NA#9102 Add Start
//  private void setDefaultRefNum(List<NWZC153001DetailBean> cpoDtlBeanList, NWZC153001DetailBean baseCmptCpoDtlBean) { // S21_NA#15837 Mod
//    private void setDefaultRefNumAndSubWh(List<NWZC153001DetailBean> cpoDtlBeanList, NWZC153001DetailBean baseCmptCpoDtlBean) { // S21_NA#15837 Mod
    private void setDefaultRefNumAndSubWh(List<NWZC153001DetailBean> cpoDtlBeanList, NWZC153001DetailBean baseCmptCpoDtlBean, String glblCmpyCd) { // 2018/07/12 S21_NA#26895-2 Mod

        String dplyLineRefNum = null;
        String refCpoDtlLineNum = null;
        String refCpoDtlLineSubNum = null;
        // Add Start 2017/06/21 QC#19407
        String thirdPtyDspTpCd = null;
        // Add End 2017/06/21 QC#19407
        for (NWZC153001DetailBean cpoDtlBean : cpoDtlBeanList) {
            if (ZYPCommonFunc.hasValue(cpoDtlBean.getDplyLineRefNum()) //
                    && !ZYPCommonFunc.hasValue(dplyLineRefNum)) {
                dplyLineRefNum = cpoDtlBean.getDplyLineRefNum();
            }
            if (baseCmptCpoDtlBean == null //
                    && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cpoDtlBean.getBaseCmptFlg())) {
                baseCmptCpoDtlBean = cpoDtlBean;
            }
        }
        // Mod Start 2017/02/01 QC#17257
//        if (dplyLineRefNum == null && baseCmptCpoDtlBean != null) {
        if (dplyLineRefNum == null && baseCmptCpoDtlBean != null && ZYPCommonFunc.hasValue(baseCmptCpoDtlBean.getDsCpoLineNum())) {
        // Mod End 2017/02/01 QC#17257
            dplyLineRefNum = baseCmptCpoDtlBean.getDsOrdPosnNum() + "." + String.valueOf(baseCmptCpoDtlBean.getDsCpoLineNum());
            if (ZYPCommonFunc.hasValue(baseCmptCpoDtlBean.getDsCpoLineSubNum())) {
                dplyLineRefNum = dplyLineRefNum + "." + String.valueOf(baseCmptCpoDtlBean.getDsCpoLineSubNum());
            }
        }

        if (dplyLineRefNum != null) {
            NWZC153001DetailBean refCpoDtlBean = getRefCpoDtlBean(cpoDtlBeanList, dplyLineRefNum);
            if (refCpoDtlBean != null) {
                refCpoDtlLineNum = refCpoDtlBean.getDsCpoRtrnLineNum();
                refCpoDtlLineSubNum = refCpoDtlBean.getDsCpoRtrnLineSubNum();
                // Add Start 2017/06/21 QC#19407
                thirdPtyDspTpCd = refCpoDtlBean.getThirdPtyDspTpCd();
                // Add End 2017/06/21 QC#19407
            }
        }

        for (NWZC153001DetailBean cpoDtlBean : cpoDtlBeanList) {
            // Del Start 2017/02/02 QC#17349
//            boolean isRefBean = false;
//            if (S21StringUtil.isEquals(cpoDtlBean.getDsCpoRtrnLineNum(), refCpoDtlLineNum) //
//                    && S21StringUtil.isEquals(cpoDtlBean.getDsCpoRtrnLineSubNum(), refCpoDtlLineSubNum)) {
//                isRefBean = true;
//            }
            // Del End 2017/02/02 QC#17349
            // Mod Start 2017/02/02 QC#17349
//            if (isRefBean) {
            if (S21StringUtil.isEquals(cpoDtlBean.getBaseCmptFlg(), ZYPConstant.FLG_ON_Y) //
                    || (baseCmptCpoDtlBean != null //
                            && !S21StringUtil.isEquals(baseCmptCpoDtlBean.getBaseCmptFlg(), ZYPConstant.FLG_ON_Y))) {
            // Mod End 2017/02/02 QC#17349
                cpoDtlBean.setDplyLineRefNum(null);
                cpoDtlBean.setRefCpoDtlLineNum(null);
                cpoDtlBean.setRefCpoDtlLineSubNum(null);
                // Del Start 2017/06/27 QC#19585
//                cpoDtlBean.setThirdPtyDspTpCd(null);
                // Del End 2017/06/27 QC#19585
            } else {
                cpoDtlBean.setDplyLineRefNum(dplyLineRefNum);
                cpoDtlBean.setRefCpoDtlLineNum(refCpoDtlLineNum);
                cpoDtlBean.setRefCpoDtlLineSubNum(refCpoDtlLineSubNum);
                // Add Start 2017/06/21 QC#19407
                cpoDtlBean.setThirdPtyDspTpCd(thirdPtyDspTpCd);
                // Add End 2017/06/21 QC#19407
            }

            // S21_NA#15837 Add Start
            // Mod Start 2017/01/24 QC#17257
//            if (baseCmptCpoDtlBean != null && CONFIG_TP.RETURN_NEW.equals(cpoDtlBean.getConfigTpCd())) {
            if (baseCmptCpoDtlBean != null) {
                // 2018/11/20 S21_NA#27299 Add Start
                // if (!SUPPLY_SUB_WH_CD.equals(cpoDtlBean.getRtlSwhCd())) {
                if (!SUPPLY_SUB_WH_CD.equals(cpoDtlBean.getRtlSwhCd()) && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cpoDtlBean.getInvtyCtrlFlg())) {
                    // 2018/11/20 S21_NA#27299 Add End
                    // cpoDtlBean.setRtlWhCd(baseCmptCpoDtlBean.getRtlWhCd()); // QC#56558 2020/04/14 Add // 2020/05/25 QC#56558-1 Del
                    cpoDtlBean.setRtlSwhCd(baseCmptCpoDtlBean.getRtlSwhCd());
                    // Add Start 2017/06/21 QC#19407
                    cpoDtlBean.setThirdPtyDspTpCd(baseCmptCpoDtlBean.getThirdPtyDspTpCd());
                    // Add End 2017/06/21 QC#19407
                    // Add Start 2017/07/03 QC#19709
                    // Mod Start 2018/03/15 QC#24258
                    if (ZYPCommonFunc.hasValue(cpoDtlBean.getRtlWhCd()) //
                            && ZYPCommonFunc.hasValue(baseCmptCpoDtlBean.getRtlWhCd()) //
                            && cpoDtlBean.getRtlWhCd().equals(baseCmptCpoDtlBean.getRtlWhCd())) {
                        StringBuilder invtyLocCd = new StringBuilder();
                        invtyLocCd.append(cpoDtlBean.getRtlWhCd());
                        invtyLocCd.append(baseCmptCpoDtlBean.getRtlSwhCd());
                        cpoDtlBean.setInvtyLocCd(invtyLocCd.toString());
                    // 2020/05/25 QC#56558-1 Add Start
                    } else {
                      StringBuilder invtyLocCd = new StringBuilder();
                      invtyLocCd.append(cpoDtlBean.getRtlWhCd());
                      invtyLocCd.append(cpoDtlBean.getRtlSwhCd());
                      cpoDtlBean.setInvtyLocCd(invtyLocCd.toString());
                    // 2020/05/25 QC#56558-1 Add End
                        // 2018/08/30 S21_NA#26895-3 Del Start --> Delete else statements.
//                    } else {
//                        cpoDtlBean.setInvtyLocCd(baseCmptCpoDtlBean.getInvtyLocCd());
                        // 2018/08/30 S21_NA#26895-3 Del End
                        // 2018/08/30 S21_NA#26895-3 Del Start
//                        // 2018/07/12 S21_NA#26895-2 Mod Start
//                        boolean avalOverWriteRtlWh = true;
//                        boolean isBaseRebill = S21StringUtil.isEquals(CR_REBIL.REBILL, baseCmptCpoDtlBean.getCrRebilCd());
//                        boolean isRebill = !S21StringUtil.isEquals(CR_REBIL.REBILL, cpoDtlBean.getCrRebilCd());
//
//                        if (ZYPCommonFunc.hasValue(cpoDtlBean.getRtlWhCd())) {
//                            avalOverWriteRtlWh = NWXC150001DsCheck.isOrdLineSrcItt(glblCmpyCd, cpoDtlBean.getRtlWhCd());
//                        }
//
//                        if (avalOverWriteRtlWh && isBaseRebill && !isRebill) {
//                            avalOverWriteRtlWh = false;
//                        }
//                        if (avalOverWriteRtlWh) {
//                            cpoDtlBean.setInvtyLocCd(baseCmptCpoDtlBean.getInvtyLocCd());
//                            cpoDtlBean.setRtlWhCd(baseCmptCpoDtlBean.getRtlWhCd());
//                            cpoDtlBean.setRtlSwhCd(baseCmptCpoDtlBean.getRtlSwhCd());
//                        } else {
//                            String rtlWhCd = cpoDtlBean.getRtlWhCd();
//                            String rtlSwhCd = cpoDtlBean.getRtlSwhCd();
//                            if (!isBaseRebill) {
//                                rtlSwhCd = baseCmptCpoDtlBean.getRtlSwhCd();
//                            }
//                            StringBuilder invtyLocCd = new StringBuilder();
//                            invtyLocCd.append(rtlWhCd);
//                            invtyLocCd.append(rtlSwhCd);
//                            cpoDtlBean.setInvtyLocCd(invtyLocCd.toString());
//                            if (!S21StringUtil.isEquals(rtlSwhCd, cpoDtlBean.getRtlSwhCd())) {
//                                cpoDtlBean.setRtlSwhCd(rtlSwhCd);
//                            }
//                            invtyLocCd = null;
//                        }
//                        // 2018/07/12 S21_NA#26895-2 Mod End
                        // 2018/08/30 S21_NA#26895-3 Del End
                    }
                    // Mod End 2018/03/15 QC#24258
                    // Add Start 2017/07/03 QC#19709
                }
            }
            // Mod End 2017/01/24 QC#17257
            // S21_NA#15837 Add End
        }
    }

    // QC#12481 2016/10/26 Add Start
    private static void setDefaultValues(DS_CPO_RTRN_PRC_DTLTMsg prcDtlTMsg) {

        // QTY, AMT
        final Set<EZDTBigDecimalItem> amtItemList = new HashSet<EZDTBigDecimalItem>();
        amtItemList.add(prcDtlTMsg.shipUnitQty);
        amtItemList.add(prcDtlTMsg.dealUnitPrcAmt);
        amtItemList.add(prcDtlTMsg.dealLastNetUnitPrcAmt);
        amtItemList.add(prcDtlTMsg.dealNetAmt);
        amtItemList.add(prcDtlTMsg.dealDiscAmt);
        amtItemList.add(prcDtlTMsg.dealPrmoNetUnitPrcAmt);
        amtItemList.add(prcDtlTMsg.dealPerUnitFixAmt);
        amtItemList.add(prcDtlTMsg.dealSlsPctNum);
        amtItemList.add(prcDtlTMsg.funcPerUnitFixAmt);
        amtItemList.add(prcDtlTMsg.funcUnitPrcAmt);
        amtItemList.add(prcDtlTMsg.funcLastNetUnitPrcAmt);
        amtItemList.add(prcDtlTMsg.funcNetAmt);
        amtItemList.add(prcDtlTMsg.funcDiscAmt);
        amtItemList.add(prcDtlTMsg.funcPrmoNetUnitPrcAmt);

        for (EZDTBigDecimalItem amtItem : amtItemList) {
            if (!hasValue(amtItem)) {
                setValue(amtItem, ZERO);
            }
        }
    }
    // QC#12481 2016/10/26 Add End

    private NWZC153001DetailBean getRefCpoDtlBean(List<NWZC153001DetailBean> cpoDtlBeanList, String dplyLineRefNum) {

        // 2016/10/06 S21_NA#9102-2 Del Start
//        String divRefNum[] = dplyLineRefNum.split("\\.");
//        BigDecimal refDtlLineNum = new BigDecimal("-1");
//        BigDecimal refDtlLineSubNum = new BigDecimal("-1");
//        if (divRefNum.length >= 2) {
//            refDtlLineNum = new BigDecimal(divRefNum[1]);
//        }
//        if (divRefNum.length >= 3) {
//            refDtlLineSubNum = new BigDecimal(divRefNum[2]);
//        }
//        for (NWZC153001DetailBean cpoDtlBean : cpoDtlBeanList) {
//            BigDecimal dsCpoLineNum = cpoDtlBean.getDsCpoLineNum();
//            BigDecimal dsCpoLineSubNum = cpoDtlBean.getDsCpoLineSubNum();
//            boolean isRefTmsg = false;
//            if (divRefNum.length == 2) {
//                isRefTmsg = dsCpoLineNum != null && refDtlLineNum.compareTo(dsCpoLineNum) == 0;
//            }
//            if (divRefNum.length == 3) {
//                isRefTmsg = dsCpoLineNum != null && refDtlLineNum.compareTo(dsCpoLineNum) == 0;
//                isRefTmsg = isRefTmsg && dsCpoLineSubNum != null && refDtlLineSubNum.compareTo(dsCpoLineSubNum) == 0;
//            }
//            if (isRefTmsg) {
//                return cpoDtlBean;
//            }
//        }
        // 2016/10/06 S21_NA#9102-2 Del End
        // 2016/10/06 S21_NA#9102-2 Add Start
        for (NWZC153001DetailBean cpoDtlBean : cpoDtlBeanList) {
            BigDecimal dsCpoLineNum = cpoDtlBean.getDsCpoLineNum();
            BigDecimal dsCpoLineSubNum = cpoDtlBean.getDsCpoLineSubNum();
            String checkLineNum = cpoDtlBean.getDsOrdPosnNum() + "." + String.valueOf(dsCpoLineNum);
            if (ZYPCommonFunc.hasValue(dsCpoLineSubNum)) {
                checkLineNum = checkLineNum + "." + String.valueOf(dsCpoLineSubNum);
            }
            boolean isRefBean = S21StringUtil.isEquals(dplyLineRefNum, checkLineNum);
            if (isRefBean) {
                return cpoDtlBean;
            }
        }
        // 2016/10/06 S21_NA#9102-2 Add End
        return null;
    }

    private void clearBaseCmptFlg(NWZC153001CpoBean cpoBean) {

        List<NWZC153001DetailBean> cpoDtlBeanList = cpoBean.getDtlBeanList();
        for (NWZC153001DetailBean cpoDtlBean : cpoDtlBeanList) {
            cpoDtlBean.setBaseCmptFlg(ZYPConstant.FLG_OFF_N);
            // Add Start 2017/02/06 AC#17257
            cpoDtlBean.setDplyLineRefNum("");
            // Add End 2017/02/06 AC#17257
        }
        return;
    }

    private boolean isSetDiffrentRefNum(List<NWZC153001DetailBean> cpoDtlBeanList, List<NWZC153002PMsg> linePMsgList) {

        List<String> refNumList = new ArrayList<String>(0);
        List<NWZC153001DetailBean> hasRefNumBeanList = new ArrayList<NWZC153001DetailBean>(0);
        for (NWZC153001DetailBean cpoDtlBean : cpoDtlBeanList) {
            String lineRefNum = cpoDtlBean.getDplyLineRefNum();
            boolean isSetRefNum = ZYPCommonFunc.hasValue(lineRefNum);
            if (isSetRefNum) {
                if (!refNumList.contains(lineRefNum)) {
                    refNumList.add(lineRefNum);
                }
                hasRefNumBeanList.add(cpoDtlBean);
            }
        }
        if (refNumList.size() > 1) {
            for (NWZC153001DetailBean hasRefNumBean : hasRefNumBeanList) {
                int idx = getLinePMsgListIndexFromCpoRtrnDtl(linePMsgList, hasRefNumBean);
                setErrMsgIdForCpoDtl(NWZM2020E, linePMsgList, idx);
            }
            return true;
        }
        return false;
    }

    private int getLinePMsgListIndexFromCpoRtrnDtl(final List<NWZC153002PMsg> linePMsgList, NWZC153001DetailBean cpoDtlBean) {
        int retIdx = -1;

        NWZC153002PMsg pMsg;
        for (int i = 0; i < linePMsgList.size(); i++) {
            pMsg = linePMsgList.get(i);
            if (S21StringUtil.isEquals(pMsg.dsCpoRtrnLineNum.getValue(), cpoDtlBean.getDsCpoRtrnLineNum())
                    && S21StringUtil.isEquals(pMsg.dsCpoRtrnLineSubNum.getValue(), cpoDtlBean.getDsCpoRtrnLineSubNum())) {
                retIdx = i;
                break;
            }
        }

        return retIdx;
    }
    // 2016/09/27 S21_NA#9102 Add End

    // 2016/11/04 S21_NA#14833 Add Start
    private void adjustSetDtl(NWZC153001PMsg pMsg, DS_CPO_RTRN_DTLTMsg updRtrnDtlTMsg) {

        // 2016/11/18 S21_NA#16107 Add Start
        String xxRqstTpCd = pMsg.xxRqstTpCd.getValue();
        if (!ZYPCommonFunc.hasValue(xxRqstTpCd)) {
            return;
        }
        List<String> noOperationModeList = new ArrayList<String>();
        noOperationModeList.add(NWZC153001Constant.RQST_HDR_UPD_STS);
        noOperationModeList.add(NWZC153001Constant.RQST_HDR_CANCEL);
        noOperationModeList.add(NWZC153001Constant.RQST_HDR_UPD_HDR_AMT);

        if (noOperationModeList.contains(xxRqstTpCd)) {
            return;
        }
        // 2016/11/18 S21_NA#16107 Add End
        String dsCpoRtrnLineNum = updRtrnDtlTMsg.dsCpoRtrnLineNum.getValue();
        String dsCpoRtrnLineSubNum = updRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue();

        if (!"001".equals(dsCpoRtrnLineSubNum)) {
            return;
        }
        int paramSetCnt = countParameterSet(pMsg, updRtrnDtlTMsg);
        DS_CPO_RTRN_DTLTMsgArray dsCpoRtrnDtlForUpdateArray = getSetRtrnDtl(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue(), dsCpoRtrnLineNum);
        if (paramSetCnt > 1) {
            if (dsCpoRtrnDtlForUpdateArray.getValidCount() > paramSetCnt) {
                for (int i = paramSetCnt; i < dsCpoRtrnDtlForUpdateArray.getValidCount(); i++) {
                    DS_CPO_RTRN_DTLTMsg delDsCpoRtrnDtlTMsg = dsCpoRtrnDtlForUpdateArray.no(i);
                    EZDTBLAccessor.remove(delDsCpoRtrnDtlTMsg);
                }
            }
        } else {
            if (dsCpoRtrnDtlForUpdateArray.getValidCount() > 1) {
                for (int i = 0; i < dsCpoRtrnDtlForUpdateArray.getValidCount(); i++) {
                    DS_CPO_RTRN_DTLTMsg delDsCpoRtrnDtlTMsg = dsCpoRtrnDtlForUpdateArray.no(i);
                    if (!S21StringUtil.isEquals(dsCpoRtrnLineSubNum, delDsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue())) {
                        EZDTBLAccessor.remove(delDsCpoRtrnDtlTMsg);
                    }
                }
            }
        }
    }

    private int countParameterSet(NWZC153001PMsg pMsg, DS_CPO_RTRN_DTLTMsg updRtrnDtlTMsg) {

        int rsltCnt = 0;
        String dsCpoRtrnLineNum = updRtrnDtlTMsg.dsCpoRtrnLineNum.getValue();
        BigDecimal dsCpoLineNum = updRtrnDtlTMsg.dsCpoLineNum.getValue();
        String dsOrdPosnNum = updRtrnDtlTMsg.dsOrdPosnNum.getValue();
        for (int i = 0; i < pMsg.ordRtrnDtlList.getValidCount(); i++) {
            NWZC153001_ordRtrnDtlListPMsg ordRtrnDtlMsg = pMsg.ordRtrnDtlList.no(i);
            if (S21StringUtil.isEquals(ordRtrnDtlMsg.dsCpoRtrnLineNum.getValue(), dsCpoRtrnLineNum) //
                    || (S21StringUtil.isEquals(dsOrdPosnNum, ordRtrnDtlMsg.dsOrdPosnNum.getValue()) //
                            && ZYPCommonFunc.hasValue(ordRtrnDtlMsg.dsCpoLineNum) //
                            && dsCpoLineNum.compareTo(ordRtrnDtlMsg.dsCpoLineNum.getValue()) == 0)) {
                rsltCnt++;
            }
        }
        return rsltCnt;
    }

    private DS_CPO_RTRN_DTLTMsgArray getSetRtrnDtl(String glblCmpyCd, String cpoOrdNum, String dsCpoRtrnLineNum) {

        DS_CPO_RTRN_DTLTMsg queryCond = new DS_CPO_RTRN_DTLTMsg();

        queryCond.setSQLID("003");
        queryCond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        queryCond.setConditionValue("cpoOrdNum01", cpoOrdNum);
        queryCond.setConditionValue("dsCpoRtrnLineNum01", dsCpoRtrnLineNum);

        if (this.onBatchType.compareTo(ONBATCH_TYPE.ONLINE) == 0) {
            return (DS_CPO_RTRN_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(queryCond);
        } else {
            return (DS_CPO_RTRN_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdate(queryCond);
        }
    }
    // 2016/11/04 S21_NA#14833 Add End

    // 2016/12/20 S21_NA#15898-2 Add Start
    /**
     * <pre>
     * chkSlctLineHddRmv
     * @param pMsg NWZC153001PMsg
     * @param linePMsgList List<NWZC153002PMsg>
     * @return boolean
     * </pre>
     */
    private boolean chkSlctLineHddRmv(NWZC153001CpoBean cpoBean, List<NWZC153002PMsg> linePMsgList) {
        NWZC153001DetailBean chkCpoDtlBean = null;
        boolean isHddRmvSuccessFlg = true;
        for (int idx = 0; idx < cpoBean.getDtlBeanList().size(); idx++) {
            chkCpoDtlBean = cpoBean.getDtlBeanList().get(idx);
            if (!ZYPConstant.FLG_ON_Y.equals(chkCpoDtlBean.getBaseCmptFlg()) && ZYPCommonFunc.hasValue(chkCpoDtlBean.getHddRmvCd())) {
                setErrMsgIdForCpoDtl(NWZM2057E, linePMsgList, idx);
                isHddRmvSuccessFlg = false;
            }
        }
        return isHddRmvSuccessFlg;
    }
    // 2016/12/20 S21_NA#15898-2 Add End

    // Add Start 2016/01/27 QC#17349
    private boolean getConfigExist(String glblCmpyCd, String configTpCd) {

        CONFIG_TPTMsg configTp = ConfigTpCache.getInstance().getTMsgByKey(glblCmpyCd, configTpCd);

        if (configTp == null) {
            return false;
        }
        if (S21StringUtil.isEquals(configTp.configNewFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            return false;
        }

        return true;
    }
    // Add End 2016/01/27 QC#17349


    /**
     * <pre>
     * Set Physical Status Code for DS_CPO_RTRN_DTL
     * </pre>
     * @param updRtrnDtlTMsg DS_CPO_RTRN_DTL for Update
     * @param xxRqstTpCd request type
     * @param isBooked true: header already booked, false: header not yet booked.
     */
    private void setPhysicalDisplayStatusCd(DS_CPO_RTRN_DTLTMsg updRtrnDtlTMsg, CPOTMsg cpoTMsg) {

        String rtrnLineStsCd = updRtrnDtlTMsg.rtrnLineStsCd.getValue();

        if (S21StringUtil.isEquals(RTRN_LINE_STS.ENTERED, rtrnLineStsCd)) {
            updRtrnDtlTMsg.rtrnLineDplyStsCd.setValue(RTRN_LINE_DPLY_STS.ENTERED);
        } else if (S21StringUtil.isEquals(RTRN_LINE_STS.BOOKED, rtrnLineStsCd)) {
            // Mod Start 2019/04/04 QC#31053
            updRtrnDtlTMsg.rtrnLineDplyStsCd.setValue(RTRN_LINE_DPLY_STS.PENDING_RETURN);
            // updRtrnDtlTMsg.rtrnLineDplyStsCd.setValue(RTRN_LINE_DPLY_STS.BOOKED);
            // Mod End 2019/04/04 QC#31053
        } else if (S21StringUtil.isEquals(RTRN_LINE_STS.RWS_CREATED, rtrnLineStsCd)) {
            updRtrnDtlTMsg.rtrnLineDplyStsCd.setValue(RTRN_LINE_DPLY_STS.PENDING_RETURN);
        } else if (S21StringUtil.isEquals(RTRN_LINE_STS.RWS_CANCELLED, rtrnLineStsCd)) {
            updRtrnDtlTMsg.rtrnLineDplyStsCd.setValue(RTRN_LINE_DPLY_STS.RWS_CANCELLED);
        } else if (S21StringUtil.isEquals(RTRN_LINE_STS.PARTIALLY_RECEIVED, rtrnLineStsCd)) {
            updRtrnDtlTMsg.rtrnLineDplyStsCd.setValue(RTRN_LINE_DPLY_STS.PARTIAL_RECEIVED);
        } else if (S21StringUtil.isEquals(RTRN_LINE_STS.RECEIVED, rtrnLineStsCd)) {
            String rtrnLineDplyStsCd = getPhysicalStatusCdAtReceived(updRtrnDtlTMsg, cpoTMsg);
            updRtrnDtlTMsg.rtrnLineDplyStsCd.setValue(rtrnLineDplyStsCd);
        } else if (S21StringUtil.isEquals(RTRN_LINE_STS.CLOSED, rtrnLineStsCd)) {
            String rtrnLineDplyStsCd = getPhysicalStatusCdAtClosed(updRtrnDtlTMsg);
            updRtrnDtlTMsg.rtrnLineDplyStsCd.setValue(rtrnLineDplyStsCd);
        } else if (S21StringUtil.isEquals(RTRN_LINE_STS.CANCELLED, rtrnLineStsCd)) {
            updRtrnDtlTMsg.rtrnLineDplyStsCd.setValue(RTRN_LINE_DPLY_STS.CANCELLED);
        }
    }

    private String getPhysicalStatusCdAtReceived(DS_CPO_RTRN_DTLTMsg updRtrnDtlTMsg, CPOTMsg cpoTMsg) {

        RWS_HDRTMsgArray relatedRwsHdrArray = getRelatedRwsHdrList(updRtrnDtlTMsg);
        if (relatedRwsHdrArray == null || relatedRwsHdrArray.getValidCount() == 0) {
            return "";
        }
        if (!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, updRtrnDtlTMsg.openFlg.getValue())) {
            return "";
        }

        boolean isPendingInspection = false;
        boolean isBillingHold = false;
        boolean isClosed = false;
        boolean isPendingInvoice = false;
        for (int i = 0; i < relatedRwsHdrArray.getValidCount(); i++) {
            RWS_HDRTMsg rwsHdrTMsg = relatedRwsHdrArray.no(i);

            String rwsStsCd = rwsHdrTMsg.rwsStsCd.getValue();
            if (S21StringUtil.isEquals(RWS_STS.PRINTED, rwsStsCd) //
                    || S21StringUtil.isEquals(RWS_STS.RECEIVING, rwsStsCd)) {
                isPendingInspection = true;
            }
            if (S21StringUtil.isEquals(RWS_STS.RECEIVED, rwsStsCd)) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cpoTMsg.cpoHldFlg.getValue())) {
                    isBillingHold = true;
                } else {
                    isPendingInvoice = true;
                }
                DS_ORD_LINE_PROC_DFNTMsg dsOrdLineProcDfnTMsg = new DS_ORD_LINE_PROC_DFNTMsg();
                ZYPEZDItemValueSetter.setValue(dsOrdLineProcDfnTMsg.glblCmpyCd, updRtrnDtlTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsOrdLineProcDfnTMsg.dsOrdTpCd, cpoTMsg.dsOrdTpCd);
                ZYPEZDItemValueSetter.setValue(dsOrdLineProcDfnTMsg.dsOrdLineCatgCd, updRtrnDtlTMsg.dsOrdLineCatgCd);
                dsOrdLineProcDfnTMsg = (DS_ORD_LINE_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(dsOrdLineProcDfnTMsg);
                if (dsOrdLineProcDfnTMsg != null) {
                    ORD_PROC_TPTMsg ordProcTpTMsg = new ORD_PROC_TPTMsg();
                    ZYPEZDItemValueSetter.setValue(ordProcTpTMsg.glblCmpyCd, dsOrdLineProcDfnTMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(ordProcTpTMsg.ordProcTpCd, dsOrdLineProcDfnTMsg.ordProcTpCd);
                    ordProcTpTMsg = (ORD_PROC_TPTMsg) S21CacheTBLAccessor.findByKey(ordProcTpTMsg);
                    if (ordProcTpTMsg != null && S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, ordProcTpTMsg.invCratProcFlg.getValue())) {
                        isClosed = true;
                    }
                }
            }
        }
        if (isPendingInspection) {
            return RTRN_LINE_DPLY_STS.PENDING_INSPECTION;
        } else if (isBillingHold) {
            return RTRN_LINE_DPLY_STS.BILLING_HOLD;
        } else if (isClosed) {
            return RTRN_LINE_DPLY_STS.CLOSED;
        } else if (isPendingInvoice) {
            return RTRN_LINE_DPLY_STS.PENDING_INVOICE;
        } else {
            return "";
        }
    }

    private RWS_HDRTMsgArray getRelatedRwsHdrList(DS_CPO_RTRN_DTLTMsg updRtrnDtlTMsg) {

        RWS_HDRTMsg rwsHdrTMsgKey = new RWS_HDRTMsg();
        rwsHdrTMsgKey.setSQLID("013");
        rwsHdrTMsgKey.setConditionValue("glblCmpyCd01", updRtrnDtlTMsg.glblCmpyCd.getValue());
        rwsHdrTMsgKey.setConditionValue("trxOrdNum01", updRtrnDtlTMsg.cpoOrdNum.getValue());

        return (RWS_HDRTMsgArray) S21ApiTBLAccessor.findByCondition(rwsHdrTMsgKey);
    }

    private String getPhysicalStatusCdAtClosed(DS_CPO_RTRN_DTLTMsg updRtrnDtlTMsg) {

        if (!S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, updRtrnDtlTMsg.openFlg.getValue())) {
            return "";
        }
        if (ZYPCommonFunc.hasValue(updRtrnDtlTMsg.invNum)) {
            INVTMsg invTMsg = new INVTMsg();
            ZYPEZDItemValueSetter.setValue(invTMsg.glblCmpyCd, updRtrnDtlTMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invTMsg.invNum, updRtrnDtlTMsg.invNum);
            invTMsg = (INVTMsg) S21FastTBLAccessor.findByKey(invTMsg);
            if (invTMsg != null && S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, invTMsg.fnlzInvFlg.getValue())) {
                return RTRN_LINE_DPLY_STS.PENDING_INVOICE;
            } else {
                return RTRN_LINE_DPLY_STS.CLOSED;
            }
        }
        return "";
    }

    //QC#18673(L3) ADD START
    private void setStkStsByThirdPtyDspTp(NWZC153001DetailBean cpoDtlBean) {

        if (!ZYPCommonFunc.hasValue(cpoDtlBean.getThirdPtyDspTpCd())) {
            return;
        }

        THIRD_PTY_DSP_TPTMsg tMsg = new THIRD_PTY_DSP_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cpoDtlBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(tMsg.thirdPtyDspTpCd, cpoDtlBean.getThirdPtyDspTpCd());
        tMsg = (THIRD_PTY_DSP_TPTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.stkStsCd)) {
            cpoDtlBean.setStkStsCd(tMsg.stkStsCd.getValue());
        }

    }
    //QC#18673(L3) ADD END

    // 2017/09/29 S21_NA#21151 Add Start
    private boolean isExistsSvcMachMstr(NWZC153001DetailBean cpoDtlBean) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", cpoDtlBean.getGlblCmpyCd());
        ssmParam.put("svcMachMstrPk", cpoDtlBean.getSvcMachMstrPk());
        ssmParam.put("cpoOrdNum", cpoDtlBean.getCpoOrdNum());

        return (Integer) ssmClient.queryObject("isExistsSvcMachMstr", ssmParam) > 0;
    }
    // 2017/09/29 S21_NA#21151 Add End

}
