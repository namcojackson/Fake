/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.blap.NWAL1500.constant.NWAL1500Constant.CNT_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.CNT_HEADER;
import static business.blap.NWAL1500.constant.NWAL1500Constant.CNT_LINE;
import static business.blap.NWAL1500.constant.NWAL1500Constant.CNT_RMA_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.CNT_RMA_LINE;
import static business.blap.NWAL1500.constant.NWAL1500Constant.COMMA;
import static business.blap.NWAL1500.constant.NWAL1500Constant.CPO_DTL_LINE_SUB_NUM_SET_PRNT;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_BOOKED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_PENDING_RETURN;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LOAN_DUMMY_WH_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1500_CANC_PRTL_CANC;
import static business.blap.NWAL1500.constant.NWAL1500Constant.RTL_WH_CATG_VIRTUAL_CD;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_FORMAT_REC_NUM_INFO_DEL;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_DELETED;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NAZM0493E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NLZM2518W;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0181E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0209E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0210E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0311E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0368W;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0400E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0626E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0669E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0671E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0673E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0682E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0765E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0776W;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0779W;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0780I;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0870E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0875E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0892E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0908E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0909E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0913E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0952E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0959E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0963E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0966E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0967E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0975E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0976W;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0987E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0988E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0989E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM8465E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWZM0884E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWZM0885E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWZM0886E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWZM1926W;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWZM2023E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWZM2024E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWZM2025E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWZM2290E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWZM2293E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NZZM0003E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.ZZM9037E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDMsg;
import parts.common.EZDPMsg;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500ItemNameList;
import business.blap.NWAL1500.NWAL1500Query;
import business.blap.NWAL1500.NWAL1500QueryForLineConfig;
import business.blap.NWAL1500.NWAL1500QueryForSaveSubmit;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ASMsg;
import business.blap.NWAL1500.NWAL1500_ASMsgArray;
import business.blap.NWAL1500.NWAL1500_BCMsg;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.blap.NWAL1500.NWAL1500_BSMsgArray;
import business.blap.NWAL1500.NWAL1500_CSMsg;
import business.blap.NWAL1500.NWAL1500_CSMsgArray;
import business.blap.NWAL1500.NWAL1500_DCMsg;
import business.blap.NWAL1500.NWAL1500_DSMsg;
import business.blap.NWAL1500.NWAL1500_DSMsgArray;
import business.blap.NWAL1500.NWAL1500_FCMsg;
import business.blap.NWAL1500.NWAL1500_FSMsg;
import business.blap.NWAL1500.NWAL1500_GCMsg;
import business.blap.NWAL1500.NWAL1500_GCMsgArray;
import business.blap.NWAL1500.NWAL1500_GSMsg;
import business.blap.NWAL1500.NWAL1500_HCMsg;
import business.blap.NWAL1500.NWAL1500_HCMsgArray;
import business.blap.NWAL1500.NWAL1500_HSMsg;
import business.blap.NWAL1500.NWAL1500_ISMsg;
import business.blap.NWAL1500.NWAL1500_ISMsgArray;
import business.blap.NWAL1500.NWAL1500_TSMsg;
import business.blap.NWAL1500.NWAL1500_USMsg;
import business.blap.NWAL1500.NWAL1500_VSMsg;
import business.blap.NWAL1500.NWAL1500_WSMsg;
import business.blap.NWAL1500.NWAL1500_XSMsg;
import business.blap.NWAL1500.constant.NWAL1500Constant;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CAL_RELNTMsg;
import business.db.CAL_RELNTMsgArray;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.CPO_SRC_TPTMsg;
import business.db.CPO_SRC_TPTMsgArray;
import business.db.DS_CPO_DELY_INFOTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_ORD_LINE_CATGTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.db.PRC_CATGTMsg;
import business.db.RTL_WHTMsg;
import business.db.RTRN_RSNTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SVC_MDL_TPTMsg;
import business.db.VAR_CHAR_CONSTTMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC102001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_cpoCtacPsnInfoListPMsg;
import business.parts.NWZC150001_cpoDlvyInfoListPMsg;
import business.parts.NWZC150001_cpoIstlInfoListPMsg;
import business.parts.NWZC150001_cpoSlsCrPMsg;
import business.parts.NWZC150001_priceListPMsg;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150001_rtnPriceListPMsg;
import business.parts.NWZC150001_siteSrvInfoListPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150002_xxMsgIdListPMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC150003_xxMsgIdListPMsg;
import business.parts.NWZC152001PMsg;
import business.parts.NWZC152001_salesRepListPMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC182001PMsg;
import business.parts.NWZC182002PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NSZ.NSZC048001.constant.NSZC048001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC004001.NWZC004001;
import com.canon.cusa.s21.api.NWZ.NWZC102001.NWZC102001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC152001.NWZC152001;
import com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC182001.NWZC182001;
import com.canon.cusa.s21.api.NWZ.NWZC182001.constant.NWZC182001Constant;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmoutData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001SalesRepValidator;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001SalesRep;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001SortDetailPMsg;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001SvcMdlFuncParamBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BACK_ORD_IMPCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_SGN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_ENTRY_ACT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtil;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         S.Takami        Create          n/a
 * 2016/01/04   Fujitsu         T.ishii         Update          S21_NA#955
 * 2016/01/06   Fujitsu         T.ishii         Update          S21_NA#2522
 * 2016/01/13   Fujitsu         S.Takami        Update          S21_NA#2726
 * 2016/01/14   Fujitsu         T.Ishii         Update          S21_NA#2257
 * 2016/01/14   Fujitsu         T.Ishii         Update          S21_NA#2557
 * 2016/01/25   Fujitsu         S.Yamamoto      Update          S21_NA#3505
 * 2016/01/26   Fujitsu         S.Yamamoto      Update          S21_NA#3520
 * 2016/01/26   Fujitsu         S.Yamamoto      Update          S21_NA#3522
 * 2016/01/28   Fujitsu         S.Takami        Update          S21_NA#3254
 * 2016/02/22   Fujitsu         M.Ohno          Update          S21_NA#3112
 * 2016/02/23   Fujitsu         S.Takami        Update          S21_NA#1859
 * 2016/02/23   Fujitsu         T.Ishii         Update          S21_NA#4323
 * 2016/02/24   Fujitsu         M.Hara          Update          QC#4078
 * 2016/02/24   Fujitsu         K.Sato          Update          S21_NA#3239
 * 2016/02/25   Fujitsu         S.Takami        Update          S21_NA#4440
 * 2016/02/25   Fujitsu         K.Sato          Update          S21_NA#1704/1729
 * 2016/03/04   Fujitsu         T.Ishii         Update          S21_NA#2846
 * 2016/03/16   Fujitsu         S.Takami        Update          S21_NA#5519
 * 2016/03/17   Fujitsu         S.Takami        Update          S21_NA#5355
 * 2016/03/30   Fujitsu         S.Takami        Update          S21_NA#5523, S21_NA#5326
 * 2016/04/08   Fujitsu         S.Takami        Update          S21_NA#5356
 * 2016/04/15   Fujitsu         Y.Taoka         Update          S21_NA#7099
 * 2016/04/18   Fujitsu         S.Takami        Update          S21_NA#5832
 * 2016/04/19   Fujitsu         S.Takami        Update          S21_NA#5394
 * 2016/04/20   Fujitsu         S.Takami        Update          S21_NA#5605
 * 2016/04/22   Fujitsu         S.Takami        Update          S21_NA#7132
 * 2016/04/28   Fujitsu         S.Takami        Update          S21_NA#7516
 * 2016/05/09   Fujitsu         M.Yamada        Update          S21_NA#5764
 * 2016/05/10   Fujitsu         S.Takami        Update          S21_NA#7706
 * 2016/05/11   Fujitsu         Y.Taoka         Update          S21_NA#7606
 * 2016/05/11   Fujitsu         T.Yoshida       Update          S21_NA#8166
 * 2016/05/12   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/05/13   Fujitsu         S.Takami        Update          S21_NA#7924
 * 2016/05/17   Fujitsu         S.Takami        Update          S21_NA#8144-2
 * 2016/05/26   Fujitsu         T.Murai         Update          S21_NA#5668,2334
 * 2016/06/02   Fujitsu         S.Takami        Update          S21_NA#9325
 * 2016/06/02   Fujitsu         M.Yamada        Update          S21_NA#5395
 * 2016/06/02   Fujitsu         S.Yamamoto      Update          S21_NA#9277
 * 2016/06/07   Fujitsu         T.Murai         Update          S21_NA#6442
 * 2016/06/17   Fujitsu         S.Takami        Update          S21_NA#9679
 * 2016/06/20   Fujitsu         S.Takami        Update          S21_NA#5331-3
 * 2016/06/21   Fujitsu         Y.Kanefusa      Update          S21_NA#9437
 * 2016/06/28   Fujitsu         Y.Taoka         Update          S21_NA#10893
 * 2016/06/28   Fujitsu         S.Yamamoto      Update          S21_NA#10530
 * 2016/07/04   Fujitsu         S.Takami        Update          S21_NA#7821
 * 2016/07/19   Fujitsu         S.Takami        Update          S21_NA#11866
 * 2016/07/25   Fujitsu         S.Takami        Update          S21_NA#5062, 9789
 * 2016/07/26   Fujitsu         S.Takami        Update          S21_NA#11866-2 (Delete S21_NA#11866 without any comments)
 * 2016/07/28   Fujitsu         S.Takami        Update          S21_NA#5062-2
 * 2016/08/01   Fujitsu         H.Ikeda         Update          S21_NA#5599
 * 2016/08/01   Fujitsu         S.Takami        Update          S21_NA#12637
 * 2016/08/04   Fujitsu         S.Iidaka        Update          S21_NA#12685
 * 2016/08/05   Fujitsu         S.Iidaka        Update          S21_NA#12685
 * 2016/08/04   Fujitsu         S.Takami        Update          S21_NA#13012
 * 2016/08/08   Fujitsu         S.Takami        Update          S21_NA#5062-2
 * 2016/08/09   Fujitsu         S.Takami        Update          S21_NA#4657
 * 2016/08/09   Fujitsu         S.Takami        Update          S21_NA#13175
 * 2016/08/09   Fujitsu         Y.Taoka         Update          S21_NA#8388
 * 2016/08/23   Fujitsu         S.Takami        Update          S21_NA#13504
 * 2016/08/25   Fujitsu         Y.Taoka         Update          S21_NA#13769(S21_NA#8388)
 * 2016/08/30   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2016/09/01   Fujitsu         S.Takami        Update          S21_NA#9806-2
 * 2016/09/05   Fujitsu         W.Honda         Update          S21_NA#12435
 * 2016/09/05   Fujitsu         S.Takami        Update          S21_NA#6100
 * 2016/09/05   Fujitsu         S.Takami        Update          S21_NA#6523
 * 2016/09/07   Fujitsu         Y.Taoka         Update          S21_NA#8196
 * 2016/09/13   Fujitsu         S.Takami        Update          S21_NA#8276
 * 2016/09/13   Fujitsu         M.Yamada        Update          S21_NA#10755
 * 2016/09/16   Fujitsu         T.Yoshida       Update          S21_NA#10321
 * 2016/09/20   Fujitsu         Y.Taoka         Update          S21_NA#14107
 * 2016/09/21   Fujitsu         S.Takami        Update          S21_NA#10274
 * 2016/09/26   Fujitsu         M.Ohno          Update          S21_NA#12598
 * 2016/09/27   Fujitsu         S.Takami        Update          S21_NA#9102
 * 2016/09/29   Fujitsu         Y.Taoka         Update          S21_NA#14805
 * 2016/10/04   Fujitsu         N.Sugiura       Update          S21_NA#13170
 * 2016/10/07   Fujitsu         S.Takami        Update          S21_NA#9102-3
 * 2016/10/11   Fujitsu         K.Sato          Update          S21_NA#11964
 * 2016/10/11   Fujitsu         S.Takami        Update          S21_NA#7924-2
 * 2016/10/18   Fujitsu         S.Iidaka        Update          S21_NA#13107
 * 2016/10/21   Fujitsu         Y.Kanefusa      Update          S21_NA#5127
 * 2016/10/24   Fujitsu         S.Takami        Update          S21_NA#12474
 * 2016/10/25   Fujitsu         S.Takami        Update          S21_NA#9705
 * 2016/10/27   Fujitsu         W.Honda         Update          S21_NA#14897
 * 2016/10/27   Fujitsu         Y.Kanefusa      Update          S21_NA#10979
 * 2016/11/02   Fujitsu         S.Takami        Update          S21_NA#5394-3
 * 2016/11/07   Fujitsu         S.Takami        Update          S21_NA#14186
 * 2016/11/08   Fujitsu         S.Takami        Update          S21_NA#15427
 * 2016/11/08   Fujitsu         S.Iidaka        Update          S21_NA#7749
 * 2016/11/09   Fujitsu         S.Takami        Update          S21_NA#9864
 * 2016/11/10   Fujitsu         S.Takami        Update          S21_NA#15427-2
 * 2016/11/11   Fujitsu         S.Takami        Update          S21_NA#9864-2
 * 2016/11/17   Fujitsu         T.Yoshida       Update          S21_NA#12819
 * 2016/11/29   Fujitsu         S.Ohki          Update          S21_NA#16214
 * 2016/11/30   Fujitsu         S.Ohki          Update          S21_NA#16098
 * 2016/12/02   Fujitsu         M.Ohno          Update          S21_NA#14035
 * 2016/12/05   Fujitsu         T.Yoshida       Update          S21_NA#15889
 * 2016/12/15   Fujitsu         T.Yoshida       Update          S21_NA#15837
 * 2016/12/20   Fujitsu         S.Ohki          Update          S21_NA#15898-2
 * 2016/12/21   Fujitsu         S.Ohki          Update          S21_NA#16392
 * 2016/12/26   Fujitsu         S.Takami        Update          S21_NA#11547
 * 2017/01/19   Fujitsu         M.Ohno          Update          S21_NA#13768-3
 * 2017/02/15   Fujitsu         S.Iidaka        Update          S21_NA#17586
 * 2017/02/21   Fujitsu         T.Yoshida       Update          S21_NA#17713
 * 2017/02/21   Fujitsu         Y.Kanefusa      Update          S21_NA#17474
 * 2017/02/24   Fujitsu         S.Takami        Update          S21_NA#17714
 * 2017/02/28   Fujitsu         S.Takami        Update          S21_NA#17871
 * 2017/03/01   Fujitsu         S.Iidaka        Update          S21_NA#17898
 * 2017/03/02   Fujitsu         S.Takami        Update          S21_NA#17714-2
 * 2017/03/28   Fujitsu         S.Ohki          Update          S21_NA#18103
 * 2017/04/21   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2
 * 2017/05/16   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 (Delete Logic For Copy, no replace comment)
 * 2017/06/06   Fujitsu         S.Takami        Update          S21_NA#16443
 * 2017/06/09   Fujitsu         N.Aoyama        Update          S21_NA#18296
 * 2017/06/14   Fujitsu         S.Takami        Update          S21_NA#18623
 * 2017/06/20   Fujitsu         S.Takami        Update          S21_NA#19288-2
 * 2017/06/28   Fujitsu         S.Takami        Update          S21_NA#19628
 * 2017/06/30   Fujitsu         S.Takami        Update          S21_NA#19266
 * 2017/08/03   Fujitsu         S.Takami        Update          S21_NA#20052
 * 2017/09/21   Fujitsu         T.Murai         Update          S21_NA#18859(Sol#154)
 * 2017/10/05   Fujitsu         R.Nakamura      Update          S21_NA#21356
 * 2017/10/13   Fujitsu         S.Takami        Update          S21_NA#21267
 * 2017/10/18   Fujitsu         A.Kosai         Update          S21_NA#21759
 * 2017/10/20   Fujitsu         T.Aoi           Update          S21_NA#21730
 * 2017/10/24   Fujitsu         W.Honda         Update          S21_NA#21773
 * 2017/11/24   Fujitsu         Y.Kanefusa      Update          S21_NA#22789
 * 2017/12/07   Fujitsu         S.Takami        Update          S21_NA#22794
 * 2017/12/11   Fujitsu         M.Yamada        Update          S21_NA#22919
 * 2017/12/12   Fujitsu         N.Sugiura       Update          S21_NA#20164
 * 2017/12/21   Fujitsu         S.Takami        Update          S21_NA#20050
 * 2018/01/11   Fujitsu         S.Takami        Update          S21_NA#23329
 * 2018/01/31   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/02/13   Fujitsu         M.Ohno          Update          S21_NA#22717
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 2018/03/12   Fujitsu         A.Kosai         Update          S21_NA#20297(Sol#379)
 * 2018/03/14   Fujitsu         M.Ohno          Update          S21_NA#24117-1
 * 2018/03/15   Fujitsu         S.Takami        Update          S21_NA#19808-2(bizMsg.I->glblMsg.I without any comments)
 * 2018/03/20   Fujitsu         S.Takami        Update          S21_NA#24842
 * 2018/03/28   Fujitsu         A.Kosai         Update          S21_NA#24853
 * 2018/03/28   Fujitsu         S.Ohki          Update          S21_NA#24867
 * 2018/04/02   Fujitsu         S.Takami        Update          S21_NA#25043
 * 2018/04/06   Fujitsu         S.Takami        Update          S21_NA#25352
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/06/01   Fujitsu         M.Ohno          Update          S21_NA#26273
 * 2018/06/01   Fujitsu         T.Noguchi       Update          S21_NA#26334
 * 2018/06/13   Fujitsu         Y.Kanefusa      Update          S21_NA#24245
 * 2018/06/18   Fujitsu         S.Takami        Update          S21_NA#26598
 * 2018/06/19   Fujitsu         H.Nagashima     Update          S21_NA#24705
 * 2018/06/21   Fujitsu         S.Takami        Update          S21_NA#25151
 * 2018/06/22   Fujitsu         Y.Kanefusa      Update          S21_NA#26415
 * 2018/06/25   Fujitsu         T.Aoi           Update          S21_NA#20154
 * 2018/06/29   Fujitsu         S.Takami        Update          S21_NA#26598-2
 * 2018/07/19   Fujitsu         T.Aoi           Update          S21_NA#27227
 * 2018/07/23   Fujitsu         S.Takami        Update          S21_NA#24745
 * 2018/07/26   Fujitsu         Y.Kanefusa      Update          S21_NA#27468
 * 2018/08/02   Fujitsu         K.Ishizuka      Update          S21_NA#26181
 * 2018/08/02   Fujitsu         S.Takami        Update          S21_NA#25404
 * 2018/08/02   Fujitsu         M.Ohno          Update          S21_NA#26665
 * 2018/08/09   Fujitsu         S.Takami        Update          S21_NA#26912
 * 2018/08/21   Fujitsu         S.Takami        Update          S21_NA#26767
 * 2018/08/24   Fujitsu         K.Ishizuka      Update          S21_NA#27840
 * 2018/08/30   Fujitsu         Hd.Sugawara     Update          S21_NA#27489
 * 2018/09/04   Fujitsu         T.Aoi           Update          S21_NA#27321
 * 2018/10/10   Fujitsu         K.Ishizuka      Update          S21_NA#28728
 * 2018/10/10   Fujitsu         M.Ohno          Update          S21_NA#28383
 * 2018/10/11   Fujitsu         K.Kato          Update          S21_NA#28598
 * 2018/10/16   Fujitsu         Y.Kanefusa      Update          S21_NA#28772
 * 2018/10/18   Fujitsu         S.Takami        Update          S21_NA#26229
 * 2018/10/25   Fujitsu         T.Aoi           Update          S21_NA#28897
 * 2018/10/25   Fujitsu         T.Aoi           Update          S21_NA#28882
 * 2018/10/29   Fujitsu         Mz.Takahashi    Update          S21_NA#28920
 * 2018/10/31   Fujitsu         K.Kato          Update          S21_NA#28921
 * 2018/11/01   Fujitsu         K.Kato          Update          S21_NA#28928
 * 2018/11/02   Fujitsu         Hd.Sugawara     Update          S21_NA#29017
 * 2018/11/14   Fujitsu         Y.Kanefusa      Update          S21_NA#29138
 * 2018/11/16   Fujitsu         K.Ishizuka      Update          S21_NA#27299
 * 2018/11/27   Fujitsu         Y.Kanefusa      Update          S21_NA#29262
 * 2018/11/28   Fujitsu         Hd.Sugawara     Update          S21_NA#29252
 * 2018/12/05   Fujitsu         S.Kosaka        Update          QC#29345
 * 2018/12/12   Fujitsu         R.Nakamura      Update          S21_NA#29469
 * 2018/12/20   Fujitsu         C.Hara          Update          S21_NA#28928
 * 2018/12/26   Fujitsu         R.Nakamura      Update          S21_NA#29469-1
 * 2019/01/08   Fujitsu         S.Kosaka        Update          QC#29573
 * 2019/01/08   Fujitsu         Y.Kanefusa      Update          S21_NA#29693
 * 2019/01/18   Fujitsu         K.Ishizuka      Update          S21_NA#29983
 * 2019/02/20   Fujitsu         K.Ishizuka      Update          S21_NA#30447
 * 2019/02/22   Fujitsu         K.Ishizuka      Update          S21_NA#30449
 * 2019/03/19   Fujitsu         M.Ohno          Update          S21_NA#30700
 * 2019/04/10   Fujitsu         K.Ishizuka      Update          S21_NA#31140
 * 2019/04/22   Fujitsu         K.Ishizuka      Update          S21_NA#31185
 * 2019/05/29   Fujitsu         R.Nakamura      Update          S21_NA#50405
 * 2019/06/18   Fujitsu         K.Kato          Update          S21_NA#50732
 * 2019/07/30   Fujitsu         Mz.Takahashi    Update          QC#52207
 * 2019/07/30   Fujitsu         T.Noguchi       Update          S21_NA#52201
 * 2019/07/31   Fujitsu         S.Kosaka        Update          QC#51941
 * 2019/08/01   Fujitsu         M.Ohno          Update          S21_NA#52156
 * 2019/08/02   Fujitsu         T.Noguchi       Update          S21_NA#52127
 * 2019/08/08   Fujitsu         M.Ohno          Update          S21_NA#52472
 * 2019/08/20   Fujitsu         T.Noguchi       Update          S21_NA#52619
 * 2019/10/03   Fujitsu         A.Kazuki        Update          QC#53595
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 * 2019/11/01   Fujitsu         W.Honda         Update          S21_NA#54509
 * 2019/11/11   Fujitsu         W.Honda         Update          S21_NA#54509-1
 * 2019/11/13   Fujitsu         R.Nakamura      Update          S21_NA#54138
 * 2019/11/15   Fujitsu         R.Nakamura      Update          S21_NA#54515
 * 2019/11/21   Fujitsu         S.Takami        Update          S21_NA#54202
 * 2019/11/27   Fujitsu         S.Takami        Update          S21_NA#54206
 * 2019/11/29   Fujitsu         S.Takami        Update          S21_NA#54879
 * 2019/12/13   Fujitsu         K.Kato          Update          QC#54230
 * 2020/01/17   Fujitsu         A.Kazuki        Update          QC#55202
 * 2020/01/23   Fujitsu         K.Kato          Update          QC#55207-1
 * 2020/03/16   Fujitsu         C.Hara          Update          QC#56132
 * 2020/04/13   Fujitsu         C.Hara          Update          QC#56374
 * 2020/06/09   CITS            K.Ogino         Update          QC#56978
 * 2022/03/24   Hitachi         K.Kishimoto     Update          QC#59825
 * 2022/08/18   Hitachi         H.Watanabe      Update          QC#60255
 * 2022/10/13   CITS            K.Ogino         Update          QC#58110
 * 2022/11/16   CITS            K.Ogino         Update          QC#60815
 * 2022/12/14   CITS            K.Ogino         Update          QC#60932
 * 2023/02/13   CITS            A.Cullano       Update          QC#61158
 * 2023/05/11   CITS            R.Azucena       Update          QC#61514
 * 2023/07/25   Hitachi         K.Chiba         Update          QC#61199
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 * 2023/12/06   CITS            K.Ikeda         Update          QC#61281
 * 2024/02/29   CITS            D.Tanaka        Update          QC#62427
 * </pre>
 */
public final class NWAL1500CommonLogicForSaveSubmit {

    /**
     * Singleton instance.
     */
    private static final NWAL1500CommonLogicForSaveSubmit MY_INSTANCE = new NWAL1500CommonLogicForSaveSubmit();

    /** Query Interface: NWAL1500QueryFOrSaveSubmit */
    private NWAL1500QueryForSaveSubmit queryForSaveSubmit = null;

    /** DS Order Line Category List of LOAN Conversion */
    private List<String> loanDsOrdLineCatgList = new ArrayList<String>(0); // 2016/11/08 S21_NA#15427 Add

    /** DS Order Line Category List of LOAN Shipment */
    private Map<String, Boolean> loanShipmentDsOrdLineCatgMap = new HashMap<String, Boolean>(0); // 2016/11/09 S21_NA#9864

    // /** Item Name List */
    // private NWAL1500ItemNameList itemNameList = null; // For Performance QC#8166 Del

    /**
     * Constructor.
     */
    private NWAL1500CommonLogicForSaveSubmit() {
//        super();
        this.queryForSaveSubmit = NWAL1500QueryForSaveSubmit.getInstance();
        // this.itemNameList = NWAL1500ItemNameList.getInstance(); // For Performance QC#8166 Del
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500CommonLogicForSaveSubmit getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * If description text is null, clear code.
     * </pre>
     * @param bizMsg Business Message
     */
    public void clearCode(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/31 S21_NA#19808 Mod
        // Header
        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgDescTxt)) {
            bizMsg.dsOrdCatgCd.clear();
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.prcCatgNm)) {
            bizMsg.prcCatgCd.clear();
        }
        // Addl Header
        if (!ZYPCommonFunc.hasValue(bizMsg.frtCondDescTxt)) {
            bizMsg.frtCondCd.clear();
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.carrSvcLvlDescTxt)) {
            bizMsg.carrSvcLvlCd.clear();
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.pmtTermCashDiscDescTxt)) {
            bizMsg.pmtTermCashDiscCd.clear();
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.flPrcListNm)) {
            bizMsg.flPrcListCd.clear();
        }
        // Line Config: Detail
        // 2018/01/31 S21_NA#19808 Mod bizMsg => glblMsg Start
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(lineMsg.prcCatgNm_LL)) {
                lineMsg.prcCatgCd_LL.clear();
            }
            if (!ZYPCommonFunc.hasValue(lineMsg.rtlSwhNm_LL)) {
                lineMsg.rtlSwhCd_LL.clear();
            }
            if (!ZYPCommonFunc.hasValue(lineMsg.flPrcListNm_LL)) {
                lineMsg.flPrcListCd_LL.clear();
            }
        }
        // RMA: Detail
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);
            if (!ZYPCommonFunc.hasValue(rmaLineMsg.prcCatgNm_RL)) {
                rmaLineMsg.prcCatgCd_RL.clear();
            }
            if (!ZYPCommonFunc.hasValue(rmaLineMsg.rtlSwhNm_RL)) {
                rmaLineMsg.rtlSwhCd_RL.clear();
            }
            if (!ZYPCommonFunc.hasValue(rmaLineMsg.flPrcListNm_RL)) {
                rmaLineMsg.flPrcListCd_RL.clear();
            }
        }
        // 2018/01/31 S21_NA#19808 Mod bizMsg => glblMsg End
    }
    /**
     * Master DB exists check for Header Tab.
     * @param bizMsg Business Message
     * @param isCalledBook Called Book Process
     * @return true: error, false: normal end
     */
    public boolean isHeaderTabMasterError(NWAL1500CMsg bizMsg, boolean isCalledBook) {
        boolean rslt = false;

        // For Performance QC#8166 Mod Start
        // this.itemNameList.setBizMsg(bizMsg);
        NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(bizMsg);
        // For Performance QC#8166 Mod End

        // For Performance QC#10321 Add Start
        S21SsmEZDResult ssmRslt = null;
        // For Performance QC#10321 Add End

        if (!isCalledBook) { // For Performance QC#10321 Add
            // DS_ORD_CATG Check
            ssmRslt = this.queryForSaveSubmit.checkDsOrderCategoryDescTxt(bizMsg);
            if (!ssmRslt.isCodeNormal()) {
                bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.dsOrdCatgDescTxt) });
                rslt = true;
            }
        }

        // Header Tab ** Customer Information
        // Bill To (Number)
        ssmRslt = this.queryForSaveSubmit.checkBillToAcctCd(bizMsg);
        if (!ssmRslt.isCodeNormal()) {
            bizMsg.billToCustAcctCd.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.billToCustAcctCd)});
            rslt = true;
        }
        // Bill To (Location)
        ssmRslt = this.queryForSaveSubmit.checkBillToCustCd(bizMsg, bizMsg.billToCustCd.getValue());
        if (!ssmRslt.isCodeNormal()) {
            bizMsg.billToCustCd.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.billToCustCd)});
            rslt = true;
        }
        // Ship To (Number)
        ssmRslt = this.queryForSaveSubmit.checkShipToAcctCd(bizMsg);
        if (!ssmRslt.isCodeNormal()) {
            bizMsg.shipToCustAcctCd.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.shipToCustAcctCd)});
            rslt = true;
        }
        // Ship To (Location)
        ssmRslt = this.queryForSaveSubmit.checkShipToCustCd(bizMsg, bizMsg.shipToCustCd.getValue());
        if (!ssmRslt.isCodeNormal()) {
            bizMsg.shipToCustCd.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.shipToCustCd)});
            rslt = true;
        }
        if (!isCalledBook) { // For Performance QC#10321 Add
            // Sold To (Number)
            ssmRslt = this.queryForSaveSubmit.checkSoldToAcctCd(bizMsg);
            if (!ssmRslt.isCodeNormal()) {
                bizMsg.sellToCustCd.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.sellToCustCd) });
                rslt = true;
            }
        }
        // Sold To (Location)
        ssmRslt = this.queryForSaveSubmit.checkSoldToCustCd(bizMsg);
        if (!ssmRslt.isCodeNormal()) {
            bizMsg.soldToCustLocCd.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.soldToCustLocCd)});
            rslt = true;
        }

        // Header Tab ** Header Information
        if (!isCalledBook) { // For Performance QC#10321 Add
            // Sales Rep
            if (!getTocData(bizMsg)) {
                // 2016/05/11 S21_NA#7861 Mod slsRepPsnCd -> psnNum
                // bizMsg.slsRepTocCd.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.psnNum) }); 2016/10/11 S21_NA#7924-2 Del
                bizMsg.psnNum.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.psnNum) }); // 2016/10/11 S21_NA#7924-2 Add
                rslt = true;
            }
        }
        // Price List
        ssmRslt = this.queryForSaveSubmit.checkPriceCatgNm(bizMsg, bizMsg.prcCatgNm.getValue());
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd, rsltMapList.get(0).get("PRC_CATG_CD"));
        } else {
            bizMsg.prcCatgNm.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.prcCatgNm)});
            rslt = true;
        }
        return rslt;

    }

    /**
     * Master DB exists check forAdditional Header Tab.
     * @param bizMsg Business Message
     * @param isCalledBook Called Book Process
     * @return true: error, false: normal end
     */
    public boolean isAddlHeaderTabMasterError(NWAL1500CMsg bizMsg, boolean isCalledBook) {
        boolean rslt = false;

        // For Performance QC#8166 Add Start
        NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(bizMsg);
        // For Performance QC#8166 Add End

        // For Performance QC#10321 Add Start
        S21SsmEZDResult ssmRslt = null;
        // For Performance QC#10321 Add End

        // Addl Header Tab ** Freight Information
        if (!isCalledBook) { // For Performance QC#10321 Add
            // Freight Terms
            ssmRslt = this.queryForSaveSubmit.checkFreightCondText(bizMsg);
            if (ssmRslt.isCodeNormal()) {
                List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, rsltMapList.get(0).get("FRT_COND_CD"));
            } else {
                bizMsg.frtCondDescTxt.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.frtCondDescTxt) });
                rslt = true;
            }
        }

        // Carrier Service Level
        if (ZYPCommonFunc.hasValue(bizMsg.carrSvcLvlDescTxt)) {
            ssmRslt = this.queryForSaveSubmit.checkCarrierServiceLevelText(bizMsg);
            if (ssmRslt.isCodeNormal()) {
                List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlCd, rsltMapList.get(0).get("CARR_SVC_LVL_CD"));
            } else {
                bizMsg.carrSvcLvlDescTxt.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.carrSvcLvlDescTxt)});
                rslt = true;
            }
        }

        // Addl Header Tab ** Payment Terms Information
        // Payment Terms
        ssmRslt = this.queryForSaveSubmit.checkPaymentTermCashDiscText(bizMsg);
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscCd, rsltMapList.get(0).get("PMT_TERM_CASH_DISC_CD"));
        } else {
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.pmtTermCashDiscDescTxt)});
            rslt = true;
        }

        // Addl Header Tab ** Additional Details
        if (!isCalledBook) { // For Performance QC#10321 Add
            // Association Program
            if (ZYPCommonFunc.hasValue(bizMsg.prcContrNum)) {
                ssmRslt = this.queryForSaveSubmit.checkAssociationProgram(bizMsg);
                if (!ssmRslt.isCodeNormal()) {
                    bizMsg.prcContrNum.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.prcContrNum) });
                    rslt = true;
                }
            }
        }
        // Floor Price List
        ssmRslt = this.queryForSaveSubmit.checkPriceCatgNm(bizMsg, bizMsg.flPrcListNm.getValue());
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.flPrcListCd, rsltMapList.get(0).get("PRC_CATG_CD"));
        } else {
            bizMsg.flPrcListNm.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(bizMsg.flPrcListNm)});
            rslt = true;
        }

        return rslt;
    }

    /**
     * Master DB exists check for Line Config Tab.
     * @param bizMsg Business Message
     * @param isCalledBook Called Book Process
     * @return true: error, false: normal end
     */
    public boolean isLineConfigTabMasterError(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean isCalledBook) { // 2018/01/31 S21_NA#19808 Mod

        // 2018/01/31 S21_NA#19808 Mod bizMsg => glblMsg
        // For Performance QC#8166 Add Start
        NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(glblMsg);
        Map<String, BigDecimal> mdlIdMap = new HashMap<String, BigDecimal>();
        Map<String, Boolean> shipToCustCdMap = new HashMap<String, Boolean>();
        Map<String, Boolean> billToCustCdMap = new HashMap<String, Boolean>();
        // For Performance QC#8166 Add End

        // 2018/01/31 S21_NA#19808 Add Start
        List<Integer> errorPageList = new ArrayList<Integer>(0);
        // 2018/01/31 S21_NA#19808 Add End
        boolean rslt = false;
        Map<String, Boolean> prntModelItemCheckMap = new HashMap<String, Boolean>(); // 2016/09/13 S21_NA#8276 Mod
        for (int n = 0; n < glblMsg.A.getValidCount(); n++) {
            // Config ID
            NWAL1500_ASMsg configMsg = glblMsg.A.no(n);
            // if (ZYPCommonFunc.hasValue(acMsg.svcConfigMstrPk_LC) &&
            // !CONFIG_TP.NEW.equals(acMsg.configTpCd_LC.getValue()))
            // {
            // Out bound Not Y N N
            if (ZYPCommonFunc.hasValue(configMsg.svcConfigMstrPk_LC) && !NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configMsg.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, true, false, false)) { // S21_NA#955
                SVC_CONFIG_MSTRTMsg svcConfTMsg = getSvcConfigData(bizMsg, configMsg.svcConfigMstrPk_LC.getValue());
                // 2019/04/22 S21_NA#31185 Mod Start
                boolean hasShippedLine = false;
                for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                    NWAL1500_BSMsg lineMsg = glblMsg.B.no(j);
                    if (configMsg.dsOrdPosnNum_LC.getValue().equals(lineMsg.dsOrdPosnNum_LL.getValue()) && //
                            ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_LL) && //
                            (S21StringUtil.isEquals(ORD_LINE_STS.SHIPPED, lineMsg.ordLineStsCd_LL.getValue()) || S21StringUtil.isEquals(ORD_LINE_STS.CLOSED, lineMsg.ordLineStsCd_LL.getValue()))) {
                        hasShippedLine = true;
                        break;
                    }
                }
                // if (svcConfTMsg == null) {
                // 2019/08/20 S21_NA#52619 Mod Start
                //if (svcConfTMsg == null && !hasShippedLine) {
                boolean allCanceledLine = true;
                for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                    NWAL1500_BSMsg lineMsg = glblMsg.B.no(j);
                    if (configMsg.dsOrdPosnNum_LC.getValue().equals(lineMsg.dsOrdPosnNum_LL.getValue()) && //
                            (!S21StringUtil.isEquals(ORD_LINE_STS.CANCELLED, lineMsg.ordLineStsCd_LL.getValue()))) {
                        allCanceledLine = false;
                        break;
                    }
                }
                // Add Start 2019/11/13 QC#54138
                boolean crLine = false;
                boolean rebilLine = false;
                for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                    NWAL1500_BSMsg lineMsg = glblMsg.B.no(j);
                    if (configMsg.dsOrdPosnNum_LC.getValue().equals(lineMsg.dsOrdPosnNum_LL.getValue()) && //
                            ZYPCommonFunc.hasValue(lineMsg.crRebilCd_LL)) {
                        // Credit Line
                        if (S21StringUtil.isEquals(CR_REBIL.CREDIT, lineMsg.crRebilCd_LL.getValue())) {
                            crLine = true;
                            break;
                        }
                        // Rebill Line
                        if (S21StringUtil.isEquals(CR_REBIL.REBILL, lineMsg.crRebilCd_LL.getValue())) {
                            rebilLine = true;
                            break;
                        }
                    }
                }
                // Add End 2019/11/13 QC#54138
                // Mod Start 2019/11/13 QC#54138
//                if (svcConfTMsg == null && !hasShippedLine && !allCanceledLine && !isCancelConfigConfigTab(bizMsg, configMsg)) {
                if (svcConfTMsg == null && !hasShippedLine && !allCanceledLine && !isCancelConfigConfigTab(bizMsg, configMsg) && //
                        (!crLine && !rebilLine)) {
                // 2019/08/20 S21_NA#52619 Mod End
                    // 2019/04/22 S21_NA#31185 Mod End
                    configMsg.svcConfigMstrPk_LC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(configMsg.svcConfigMstrPk_LC)});
                    rslt = true;
                    // 2018/01/31 S21_NA#19808 Add Start
                    Integer errPage = Integer.valueOf(configMsg.xxPageNum_LC.getValueInt());
                    if (!errorPageList.contains(errPage)) {
                        errorPageList.add(errPage);
                    }
                    // 2018/01/31 S21_NA#19808 Add End
                }
                // Mod End 2019/11/13 QC#54138
            }

            if (!isCalledBook) { // For Performance QC#10321 Add
                // MDL Text
                // if (ZYPCommonFunc.hasValue(acMsg.mdlDescTxt_LC)) { 2016/03/05 S21_NA#5000#68
                if (ZYPCommonFunc.hasValue(configMsg.mdlNm_LC)) {
                    // ssmRslt = this.queryForSaveSubmit.checkModelNameForConfigLine(bizMsg, acMsg.mdlDescTxt_LC.getValue()); 2016/03/05 S21_NA#5000#68
                    // For Performance QC#8166 Mod Start
                    // ssmRslt = this.queryForSaveSubmit.checkModelNameForConfigLine(bizMsg, acMsg.mdlNm_LC.getValue()); // 2016/03/05 S21_NA#5000#68
                    BigDecimal mdlId = getMdlIdFromCache(bizMsg, configMsg.mdlNm_LC.getValue(), mdlIdMap);
                    if (ZYPCommonFunc.hasValue(mdlId)) {
                        ZYPEZDItemValueSetter.setValue(configMsg.mdlId_LC, mdlId);
                    } else {
                        // acMsg.mdlDescTxt_LC.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(acMsg.mdlDescTxt_LC)}); 2016/03/05
                        // S21_NA#5000#68
                        configMsg.mdlNm_LC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(configMsg.mdlNm_LC) }); // 2016/03/05 S21_NA#5000#68
                        // 2018/01/31 S21_NA#19808 Add Start
                        Integer errPage = Integer.valueOf(configMsg.xxPageNum_LC.getValueInt());
                        if (!errorPageList.contains(errPage)) {
                            errorPageList.add(errPage);
                        }
                        // 2018/01/31 S21_NA#19808 Add End
                        rslt = true;
                    }

                    // if (ssmRslt.isCodeNormal()) {
                    //     List<Map<String, Object>> rsltMapList = (List<Map<String, Object>>) ssmRslt.getResultObject();
                    //     ZYPEZDItemValueSetter.setValue(acMsg.mdlId_LC, (BigDecimal) rsltMapList.get(0).get("T_MDL_ID"));
                    // } else {
                    //     acMsg.mdlDescTxt_LC.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(acMsg.mdlDescTxt_LC)}); 2016/03/05 S21_NA#5000#68
                    //     acMsg.mdlNm_LC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(acMsg.mdlNm_LC)}); // 2016/03/05 S21_NA#5000#68
                    //     rslt = true;
                    // }
                    // For Performance QC#8166 Mod End
                }

                // Ship To Cust
                // For Performance QC#8166 Mod Start
                boolean checkShipToCust = getShipToCustInfoFromCache(bizMsg, configMsg.shipToCustCd_LC.getValue(), shipToCustCdMap);
                if (!checkShipToCust) {
                    configMsg.shipToCustCd_LC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(configMsg.shipToCustCd_LC) });
                    rslt = true;
                    // 2018/01/31 S21_NA#19808 Add Start
                    Integer errPage = Integer.valueOf(configMsg.xxPageNum_LC.getValueInt());
                    if (!errorPageList.contains(errPage)) {
                        errorPageList.add(errPage);
                    }
                    // 2018/01/31 S21_NA#19808 Add End
                }

                // ssmRslt = this.queryForSaveSubmit.checkShipToCustCd(bizMsg, acMsg.shipToCustCd_LC.getValue());
                // if (!ssmRslt.isCodeNormal()) {
                //     acMsg.shipToCustCd_LC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(acMsg.shipToCustCd_LC)});
                //     rslt = true;
                // }
                // For Performance QC#8166 Mod End

                // Bill To Cust
                // For Performance QC#8166 Mod Start
                boolean checkBillToCust = getBillToCustInfoFromCache(bizMsg, configMsg.billToCustCd_LC.getValue(), billToCustCdMap);
                if (!checkBillToCust) {
                    configMsg.billToCustCd_LC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(configMsg.billToCustCd_LC) });
                    rslt = true;
                    // 2018/01/31 S21_NA#19808 Add Start
                    Integer errPage = Integer.valueOf(configMsg.xxPageNum_LC.getValueInt());
                    if (!errorPageList.contains(errPage)) {
                        errorPageList.add(errPage);
                    }
                    // 2018/01/31 S21_NA#19808 Add End
                }

                // ssmRslt = this.queryForSaveSubmit.checkBillToCustCd(bizMsg, acMsg.billToCustCd_LC.getValue());
                // if (!ssmRslt.isCodeNormal()) {
                //     acMsg.billToCustCd_LC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(acMsg.billToCustCd_LC)});
                //     rslt = true;
                // }
                // For Performance QC#8166 Mod End
            }

            // 2016/08/30 S21_NA#9806 Add Start
            if (ZYPCommonFunc.hasValue(configMsg.csmpContrNum_LC) //
                    || ZYPCommonFunc.hasValue(configMsg.dlrRefNum_LC)) { // 2016/09/01 S21_NA#9806-2 Add Condition
                S21SsmEZDResult ssmRsltCsmpChk = NWAL1500QueryForSaveSubmit.getInstance().checkCSMPNum(bizMsg, configMsg);
                boolean isErrCsmp = false;
                if (!ssmRsltCsmpChk.isCodeNormal()) {
                    isErrCsmp = true;
                } else {
                    BigDecimal rsltCsmpChk = (BigDecimal) ssmRsltCsmpChk.getResultObject();
                    if (BigDecimal.ZERO.compareTo(rsltCsmpChk) == 0) {
                        isErrCsmp = true;
                    }
                }
                if (isErrCsmp) {
                    configMsg.csmpContrNum_LC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(configMsg.csmpContrNum_LC)});
                    configMsg.dlrRefNum_LC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(configMsg.dlrRefNum_LC)});
                    // 2018/01/31 S21_NA#19808 Add Start
                    Integer errPage = Integer.valueOf(configMsg.xxPageNum_LC.getValueInt());
                    if (!errorPageList.contains(errPage)) {
                        errorPageList.add(errPage);
                    }
                    // 2018/01/31 S21_NA#19808 Add End
                    rslt = true;
                }
            }
            // 2016/08/30 S21_NA#9806 Add End

            // 2016/09/13 S21_NA#8276 Add Start
            if (checkModelParentItemLineConfig(bizMsg, glblMsg, configMsg, prntModelItemCheckMap)) { // 2018/01/31 S21_NA#19808 Mod
                rslt = true;
            }
            // 2016/09/13 S21_NA#8276 Add End
            // QC#24245 2018/06/13 Add Start
            if (CONFIG_TP.ADD_TO_CONFIG.equals(configMsg.configTpCd_LC.getValue())){
                // Add Start 2018/12/12 QC#29469
                boolean noIBFlg = true;
                // QC#60815 Add
                boolean isAllLineCancelorClose = true;
                // Add End 2018/12/12 QC#29469
                for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                    NWAL1500_BSMsg lineMsg = glblMsg.B.no(j);
                    if (configMsg.dsOrdPosnNum_LC.getValue().equals(lineMsg.dsOrdPosnNum_LL.getValue())) {

                        // QC#60815 Add start
                        if (S21StringUtil.isEquals(ORD_LINE_STS.CANCELLED, lineMsg.ordLineStsCd_LL.getValue()) || S21StringUtil.isEquals(ORD_LINE_STS.CLOSED, lineMsg.ordLineStsCd_LL.getValue())) {
                            continue;
                        }
                        isAllLineCancelorClose = false;
                        // QC#60815 Add End

                        // Add Start 2018/12/12 QC#29469-1
                        MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
                        if (S21StringUtil.isEquals(mdseMsg.instlBaseCtrlFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
                            noIBFlg = false;
                        }
                        // Add End 2018/12/12 QC#29469-1
                        if (S21StringUtil.isEquals("000", lineMsg.cpoDtlLineSubNum_LL.getValue())) {
                            continue;
                        }
                        if (S21StringUtil.isEquals(bizMsg.varCharConstVal_TB.getValue(), lineMsg.ordLineStsDescTxt_LL.getValue())) {
                            continue;
                        }
                        // QC#27468 2018/07/26 Add Start
                        if (ZYPCommonFunc.hasValue(lineMsg.crRebilCd_LL)){
                            continue;
                        }
                        // QC#27468 2018/07/26 Add End
                        // Del Start 2018/12/26 QC#29469-1
//                        MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
                        // Del End 2018/12/26 QC#29469-1
                        if(NWXC150001DsCheck.getCoaMdseTp(bizMsg.glblCmpyCd.getValue(), mdseMsg.mdseCd.getValue()).equals(COA_MDSE_TP.MACHINE)) {
                            lineMsg.mdseCd_LL.setErrorInfo(1, NWAM0959E, new String[] {"Add Config"});
                            rslt = true;
                        }
                        // Add Start 2018/12/12 QC#29469
                        // Del Start 2018/12/26 QC#29469-1
//                        if (S21StringUtil.isEquals(mdseMsg.instlBaseCtrlFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
//                            noIBFlg = false;
//                        }
                        // Del End 2018/12/26 QC#29469-1
                        // Add End 2018/12/12 QC#29469
                    }
                }
                // Add Start 2018/12/12 QC#29469. QC#60815 Mod
//                if (noIBFlg) {
                if (!isAllLineCancelorClose && noIBFlg) {
                    configMsg.configTpCd_LC.setErrorInfo(1, NWAM0963E);
                    rslt = true;
                }
                // Add End 2018/12/12 QC#29469
            }
            // QC#24245 2018/06/13 Add End
        }

        // For Performance QC#8166 Add Start
        Map<String, String> mdseCdMap = new HashMap<String, String>();
        Map<String, String> prcCatgCdMap = new HashMap<String, String>();
        Map<String, String> rtlWhCdMap = new HashMap<String, String>();
        Map<Map<String, String>, String> rtlSwhCdMap = new HashMap<Map<String, String>, String>();
        // For Performance QC#8166 Add End

        for (int n = 0; n < glblMsg.B.getValidCount(); n++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(n);
            // START 2023/05/11 R.Azucena [QC#61514 MOD]
            // if (!ORD_LINE_STS.CANCELLED.equals(glblMsg.B.no(n).ordLineStsCd_LL.getValue())) { // QC#1620 // 2018/01/31 S21_NA#19808 Mod
            if (!ORD_LINE_STS.CANCELLED.equals(glblMsg.B.no(n).ordLineStsCd_LL.getValue()) // QC#1620 // 2018/01/31 S21_NA#19808 Mod
                    && !S21StringUtil.isEquals(bizMsg.varCharConstVal_TB.getValue(), glblMsg.B.no(n).ordLineStsDescTxt_LL.getValue())) {
            // END 2023/05/11 R.Azucena [QC#61514 MOD]
                if (!isCalledBook) { // For Performance QC#10321 Add
                    // Item
                    // For Performance QC#8166 Mod Start
                    String mdseCd = getMdseCdFromCache(bizMsg, lineMsg.mdseCd_LL.getValue(), mdseCdMap);
                    if (!ZYPCommonFunc.hasValue(mdseCd)) {
                        lineMsg.mdseCd_LL.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(lineMsg.mdseCd_LL) });
                        rslt = true;
                        // 2018/01/31 S21_NA#19808 Add Start
                        Integer errPage = Integer.valueOf(lineMsg.xxPageNum_LL.getValueInt());
                        if (!errorPageList.contains(errPage)) {
                            errorPageList.add(errPage);
                        }
                        // 2018/01/31 S21_NA#19808 Add End
                    }
                    // ssmRslt = this.queryForSaveSubmit.checkMdseCdFromAllMdseV(bizMsg, bcMsg.mdseCd_LL.getValue());
                    // if (!ssmRslt.isCodeNormal()) {
                    //     bcMsg.mdseCd_LL.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(bcMsg.mdseCd_LL)});
                    //     rslt = true;
                    // }
                    // For Performance QC#8166 Mod Start

                    // Sell Price Category
                    // For Performance QC#8166 Mod Start
                    String prcCatgCd = getPrcCatgCdFromCache(bizMsg, lineMsg.prcCatgNm_LL.getValue(), prcCatgCdMap);
                    if (ZYPCommonFunc.hasValue(prcCatgCd)) {
                        ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgCd_LL, prcCatgCd);
                    } else {
                        lineMsg.prcCatgNm_LL.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(lineMsg.prcCatgNm_LL)});
                        rslt = true;
                        // 2018/01/31 S21_NA#19808 Add Start
                        Integer errPage = Integer.valueOf(lineMsg.xxPageNum_LL.getValueInt());
                        if (!errorPageList.contains(errPage)) {
                            errorPageList.add(errPage);
                        }
                        // 2018/01/31 S21_NA#19808 Add End
                    }
                    // ssmRslt = this.queryForSaveSubmit.checkPriceCatgNm(bizMsg, bcMsg.prcCatgNm_LL.getValue());
                    // if (ssmRslt.isCodeNormal()) {
                    //     List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
                    //     ZYPEZDItemValueSetter.setValue(bcMsg.prcCatgCd_LL, rsltMapList.get(0).get("PRC_CATG_CD"));
                    // } else {
                    //     bcMsg.prcCatgNm_LL.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(bcMsg.prcCatgNm_LL)});
                    //     rslt = true;
                    // }
                    // For Performance QC#8166 Mod End

                    // Retail WH (Name is displayed, then check with name
                    // if (NWAL1500CommonLogic.isMdseTangible(bizMsg.glblCmpyCd.getValue(), bcMsg.mdseCd_LL.getValue())) {
                    // S21_NA#2522
                    if (NWAL1500CommonLogic.needsWh(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue())) {
                        // For Performance QC#8166 Mod Start
                        String rtlWhCd = getRtlWhCdFromCache(bizMsg, lineMsg.rtlWhNm_LL.getValue(), rtlWhCdMap);
                        if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                            ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhCd_LL, rtlWhCd);
                        } else {
                            lineMsg.rtlWhNm_LL.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(lineMsg.rtlWhCd_LL) });
                            rslt = true;
                            // 2018/01/31 S21_NA#19808 Add Start
                            Integer errPage = Integer.valueOf(lineMsg.xxPageNum_LL.getValueInt());
                            if (!errorPageList.contains(errPage)) {
                                errorPageList.add(errPage);
                            }
                            // 2018/01/31 S21_NA#19808 Add End
                        }
                        // ssmRslt = NWAL1500QueryForLineConfig.getInstance().getWhInfoWithRsn(bizMsg, bcMsg.rtlWhNm_LL.getValue());
                        // if (ssmRslt.isCodeNormal()) {
                        //     List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
                        //     ZYPEZDItemValueSetter.setValue(bcMsg.rtlWhCd_LL, rsltMapList.get(0).get("RTL_WH_CD"));
                        // } else {
                        //     bcMsg.rtlWhNm_LL.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(bcMsg.rtlWhCd_LL)});
                        //     rslt = true;
                        // }
                        // For Performance QC#8166 Mod End

                        // Retail Sub Warehouse
                        if (ZYPCommonFunc.hasValue(lineMsg.rtlSwhNm_LL)) {
                            // For Performance QC#8166 Mod Start
                            String rtlSwhCd = getRtlSwhCdFromCache(bizMsg, lineMsg.rtlWhCd_LL.getValue(), lineMsg.rtlSwhNm_LL.getValue(), rtlSwhCdMap);
                            if (ZYPCommonFunc.hasValue(rtlSwhCd)) {
                                ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhCd_LL, rtlSwhCd);
                            } else {
                                lineMsg.rtlSwhNm_LL.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(lineMsg.rtlSwhCd_LL) });
                                rslt = true;
                                // 2018/01/31 S21_NA#19808 Add Start
                                Integer errPage = Integer.valueOf(lineMsg.xxPageNum_LL.getValueInt());
                                if (!errorPageList.contains(errPage)) {
                                    errorPageList.add(errPage);
                                }
                                // 2018/01/31 S21_NA#19808 Add End
                            }
                            // ssmRslt = this.queryForSaveSubmit.checkRtlSubWhWithName(bizMsg, bcMsg);
                            // if (ssmRslt.isCodeNormal()) {
                            //     Map<String, String> rsltMap = (Map<String, String>) ssmRslt.getResultObject();
                            //     ZYPEZDItemValueSetter.setValue(bcMsg.rtlSwhCd_LL, rsltMap.get("RTL_SWH_CD"));
                            // } else {
                            //     bcMsg.rtlSwhNm_LL.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(bcMsg.rtlSwhCd_LL)});
                            //     rslt = true;
                            // }
                            // For Performance QC#8166 Mod End
                        }
                    } else {
                        // 2015/12/11 S21_NA#1809 Del Start
                        // bcMsg.rtlWhCd_LL.clear();
                        // bcMsg.rtlWhNm_LL.clear();
                        // 2015/12/11 S21_NA#1809 Del End
                        lineMsg.rtlSwhCd_LL.clear();
                        lineMsg.rtlSwhNm_LL.clear();
                        // 2015/12/11 S21_NA#1809 add Start
                        S21SsmEZDResult ssmRslt = this.queryForSaveSubmit.getRtlWh(bizMsg.glblCmpyCd.getValue(), lineMsg.rtlWhNm_LL.getValue(), null, bizMsg.slsDt.getValue(), true);
                        if (ssmRslt.isCodeNormal()) {
                            Map<String, String> rsltMap = (Map<String, String>) ssmRslt.getResultObject();
                            String rtlWhCd = rsltMap.get("RTL_WH_CD");
                            ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhCd_LL, rtlWhCd);
                            String notHardAllocWh = bizMsg.varCharConstVal_NH.getValue();
                            if (ZYPCommonFunc.hasValue(notHardAllocWh)) {
                                List<String> notHardAllocWhList = S21StringUtil.toList(notHardAllocWh);
                                if (!notHardAllocWhList.contains(rtlWhCd)) {
                                    lineMsg.rtlWhCd_LL.clear();
                                    lineMsg.rtlWhNm_LL.clear();
                                }
                            } else {
                                lineMsg.rtlWhCd_LL.clear();
                                lineMsg.rtlWhNm_LL.clear();
                            }
                        } else {
                            lineMsg.rtlWhCd_LL.clear();
                            lineMsg.rtlWhNm_LL.clear();
                        }
                        // 2015/12/11 S21_NA#1809 add End
                    }
                }

                // Machine Serial
                if (ZYPCommonFunc.hasValue(lineMsg.serNum_LL)) {
                    // 2016/02/22 S21_NA#3112 Del Start
                    //ssmRslt = this.queryForSaveSubmit.checkMachSerNum(bizMsg, bcMsg.serNum_LL.getValue(), bcMsg.mdseCd_LL.getValue());
                    //if (!ssmRslt.isCodeNormal()) {
                    // bcMsg.serNum_LL.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(bcMsg.serNum_LL)});
                    // rslt = true;
                    //}
                    // 2016/02/22 S21_NA#3112 Del End
                    doNothing();
                }

                // Substitute Item
                if (ZYPCommonFunc.hasValue(lineMsg.sbstMdseCd_LL)) {
                    // For Performance QC#8166 Mod Start
                    String sbstMdseCd = getMdseCdFromCache(bizMsg, lineMsg.sbstMdseCd_LL.getValue(), mdseCdMap);
                    if (!ZYPCommonFunc.hasValue(sbstMdseCd)) {
                        lineMsg.sbstMdseCd_LL.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(lineMsg.sbstMdseCd_LL)});
                        rslt = true;
                        // 2018/01/31 S21_NA#19808 Add Start
                        Integer errPage = Integer.valueOf(lineMsg.xxPageNum_LL.getValueInt());
                        if (!errorPageList.contains(errPage)) {
                            errorPageList.add(errPage);
                        }
                        // 2018/01/31 S21_NA#19808 Add End
                    }
                    // ssmRslt = this.queryForSaveSubmit.checkMdseCdFromAllMdseV(bizMsg, bcMsg.sbstMdseCd_LL.getValue());
                    // if (!ssmRslt.isCodeNormal()) {
                    //     bcMsg.sbstMdseCd_LL.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(bcMsg.sbstMdseCd_LL)});
                    //     rslt = true;
                    // }
                    // For Performance QC#8166 Mod End
                }

                // Vendor Warehouse?
                if (ZYPCommonFunc.hasValue(lineMsg.vndInvtyLocCd_LL)) {
                    // Do Nothing
                    doNothing();
                }

                // Floor Price List
                if (ZYPCommonFunc.hasValue(lineMsg.flPrcListNm_LL)) {
                    // For Performance QC#8166 Mod Start
                    String flPrcListCd = getPrcCatgCdFromCache(bizMsg, lineMsg.flPrcListNm_LL.getValue(), prcCatgCdMap);
                    if (ZYPCommonFunc.hasValue(flPrcListCd)) {
                        ZYPEZDItemValueSetter.setValue(lineMsg.flPrcListCd_LL, flPrcListCd);
                    } else {
                        lineMsg.flPrcListNm_LL.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(lineMsg.flPrcListNm_LL)});
                        rslt = true;
                        // 2018/01/31 S21_NA#19808 Add Start
                        Integer errPage = Integer.valueOf(lineMsg.xxPageNum_LL.getValueInt());
                        if (!errorPageList.contains(errPage)) {
                            errorPageList.add(errPage);
                        }
                        // 2018/01/31 S21_NA#19808 Add End
                    }
                    // ssmRslt = this.queryForSaveSubmit.checkPriceCatgNm(bizMsg, bcMsg.flPrcListNm_LL.getValue());
                    // if (ssmRslt.isCodeNormal()) {
                    //     List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
                    //     ZYPEZDItemValueSetter.setValue(bcMsg.flPrcListCd_LL, rsltMapList.get(0).get("PRC_CATG_CD"));
                    // } else {
                    //     bcMsg.flPrcListNm_LL.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(bcMsg.flPrcListNm_LL)});
                    //     rslt = true;
                    // }
                    // For Performance QC#8166 Mod End
                }
            }
        }
        // 2018/01/31 S21_NA#19808 Add Start
        if (!errorPageList.isEmpty()) {
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, errorPageList.get(0).intValue());
        }
        // 2018/01/31 S21_NA#19808 Add End
        return rslt;
    }

    /**
     * Master DB exists check for RMA Tab.
     * @param bizMsg Business Message
     * @param isCalledBook Called Book Process
     * @return true: error, false: normal end
     */
    public boolean isRMATabMasterError(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean isCalledBook) { // 2018/01/31 S21_NA#19808 Mod

        // 2018/01/31 S21_NA#19808 Mod bizMsg => glblMsg
        // For Performance QC#8166 Add Start
        NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(glblMsg);
        Map<String, BigDecimal> mdlIdMap = new HashMap<String, BigDecimal>();
        Map<String, Boolean> shipToCustCdMap = new HashMap<String, Boolean>();
        Map<String, Boolean> billToCustCdMap = new HashMap<String, Boolean>();
        // For Performance QC#8166 Add End

        // 2018/01/31 S21_NA#19808 Add Start
        List<Integer> errorPageList = new ArrayList<Integer>(0);
        // 2018/01/31 S21_NA#19808 Add End

        boolean rslt = false;
        for (int n = 0; n < glblMsg.C.getValidCount(); n++) {
            // Config ID
            NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(n);
            // QC#60815 Add Start
            boolean allCloseOrCancelFlg = true;
            for (int j = 0; j < glblMsg.D.getValidCount(); j++) {
                NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(j);
                if (rmaConfigMsg.dsOrdPosnNum_RC.getValue().equals(rmaLineMsg.dsOrdPosnNum_RL.getValue()) && //
                        !(S21StringUtil.isEquals(RTRN_LINE_STS.CANCELLED, rmaLineMsg.ordLineStsCd_RL.getValue()) || S21StringUtil.isEquals(RTRN_LINE_STS.CLOSED, rmaLineMsg.ordLineStsCd_RL.getValue()))) {
                    allCloseOrCancelFlg = false;
                    break;
                }
            }

            if (allCloseOrCancelFlg) {
                continue;
            }
            // QC#60815 Add End
            if (ZYPCommonFunc.hasValue(rmaConfigMsg.svcConfigMstrPk_RC)) {
                SVC_CONFIG_MSTRTMsg svcConfTMsg = getSvcConfigData(bizMsg, rmaConfigMsg.svcConfigMstrPk_RC.getValue());
                // 2019/04/22 S21_NA#31185 Mod Start
                boolean hasReceivedLine = false;
                for (int j = 0; j < glblMsg.D.getValidCount(); j++) {
                    NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(j);
                    if (rmaConfigMsg.dsOrdPosnNum_RC.getValue().equals(rmaLineMsg.dsOrdPosnNum_RL.getValue()) && //
                            (S21StringUtil.isEquals(RTRN_LINE_STS.RECEIVED, rmaLineMsg.ordLineStsCd_RL.getValue()) || S21StringUtil.isEquals(RTRN_LINE_STS.CLOSED, rmaLineMsg.ordLineStsCd_RL.getValue()))) {
                        hasReceivedLine = true;
                        break;
                    }
                }
                // if (svcConfTMsg == null) {
                if (svcConfTMsg == null && !hasReceivedLine) {
                    // 2019/04/22 S21_NA#31185 Mod End
                    rmaConfigMsg.svcConfigMstrPk_RC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(rmaConfigMsg.svcConfigMstrPk_RC)});
                    rslt = true;
                    // 2018/01/31 S21_NA#19808 Add Start
                    Integer errPage = Integer.valueOf(rmaConfigMsg.xxPageNum_RC.getValueInt());
                    if (!errorPageList.contains(errPage)) {
                        errorPageList.add(errPage);
                    }
                    // 2018/01/31 S21_NA#19808 Add End
                }
            }

            if (!isCalledBook) { // For Performance QC#10321 Add
                // MDL Text
                // if (ZYPCommonFunc.hasValue(rmaConfigMsg.mdlDescTxt_RC)) { 2016/03/05 S21_NA#5000#68
                if (ZYPCommonFunc.hasValue(rmaConfigMsg.mdlNm_RC)) {
                    // ssmRslt = this.queryForSaveSubmit.checkModelNameForConfigLine(bizMsg, rmaConfigMsg.mdlDescTxt_RC.getValue()); 2016/03/05 S21_NA#5000#68
                    // For Performance QC#8166 Mod Start
                    // ssmRslt = this.queryForSaveSubmit.checkModelNameForConfigLine(bizMsg, rmaConfigMsg.mdlNm_RC.getValue()); // 2016/03/05 S21_NA#5000#68
                    BigDecimal mdlId = getMdlIdFromCache(bizMsg, rmaConfigMsg.mdlNm_RC.getValue(), mdlIdMap);
                    if (ZYPCommonFunc.hasValue(mdlId)) {
                        ZYPEZDItemValueSetter.setValue(rmaConfigMsg.mdlId_RC, (BigDecimal) mdlId);
                    } else {
                        // rmaConfigMsg.mdlDescTxt_RC.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(rmaConfigMsg.mdlDescTxt_RC)}); 2016/03/05 S21_NA#5000#68
                        rmaConfigMsg.mdlNm_RC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(rmaConfigMsg.mdlNm_RC) }); // 2016/03/05 S21_NA#5000#68
                        rslt = true;
                        // 2018/01/31 S21_NA#19808 Add Start
                        Integer errPage = Integer.valueOf(rmaConfigMsg.xxPageNum_RC.getValueInt());
                        if (!errorPageList.contains(errPage)) {
                            errorPageList.add(errPage);
                        }
                        // 2018/01/31 S21_NA#19808 Add End
                    }

                    // if (ssmRslt.isCodeNormal()) {
                    //     List<Map<String, Object>> rsltMapList = (List<Map<String, Object>>) ssmRslt.getResultObject();
                    //     ZYPEZDItemValueSetter.setValue(rmaConfigMsg.mdlId_RC, (BigDecimal) rsltMapList.get(0).get("T_MDL_ID"));
                    // } else {
                    //     rmaConfigMsg.mdlDescTxt_RC.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(rmaConfigMsg.mdlDescTxt_RC)}); 2016/03/05 S21_NA#5000#68
                    //     rmaConfigMsg.mdlNm_RC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(rmaConfigMsg.mdlNm_RC)}); // 2016/03/05 S21_NA#5000#68
                    //     rslt = true;
                    // }
                    // For Performance QC#8166 Mod End
                }

                // Ship To Cust
                // For Performance QC#8166 Mod Start
                boolean checkShipToCust = getShipToCustInfoFromCache(bizMsg, rmaConfigMsg.shipToCustCd_RC.getValue(), shipToCustCdMap);
                if (!checkShipToCust) {
                    rmaConfigMsg.shipToCustCd_RC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(rmaConfigMsg.shipToCustCd_RC) });
                    rslt = true;
                    // 2018/01/31 S21_NA#19808 Add Start
                    Integer errPage = Integer.valueOf(rmaConfigMsg.xxPageNum_RC.getValueInt());
                    if (!errorPageList.contains(errPage)) {
                        errorPageList.add(errPage);
                    }
                    // 2018/01/31 S21_NA#19808 Add End
                }

                // ssmRslt = this.queryForSaveSubmit.checkShipToCustCd(bizMsg, rmaConfigMsg.shipToCustCd_RC.getValue());
                // if (!ssmRslt.isCodeNormal()) {
                //     rmaConfigMsg.shipToCustCd_RC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(rmaConfigMsg.shipToCustCd_RC)});
                //     rslt = true;
                // }
                // For Performance QC#8166 Mod End

                // Bill To Cust
                // For Performance QC#8166 Mod Start
                boolean checkBillToCust = getBillToCustInfoFromCache(bizMsg, rmaConfigMsg.billToCustCd_RC.getValue(), billToCustCdMap);
                if (!checkBillToCust) {
                    rmaConfigMsg.billToCustCd_RC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(rmaConfigMsg.billToCustCd_RC) });
                    rslt = true;
                    // 2018/01/31 S21_NA#19808 Add Start
                    Integer errPage = Integer.valueOf(rmaConfigMsg.xxPageNum_RC.getValueInt());
                    if (!errorPageList.contains(errPage)) {
                        errorPageList.add(errPage);
                    }
                    // 2018/01/31 S21_NA#19808 Add End
                }

                // ssmRslt = this.queryForSaveSubmit.checkBillToCustCd(bizMsg, rmaConfigMsg.billToCustCd_RC.getValue());
                // if (!ssmRslt.isCodeNormal()) {
                //     rmaConfigMsg.billToCustCd_RC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(rmaConfigMsg.billToCustCd_RC)});
                //     rslt = true;
                // }
                // For Performance QC#8166 Mod End
            }

            // 2016/08/30 S21_NA#9806 Add Start
            if (ZYPCommonFunc.hasValue(rmaConfigMsg.csmpContrNum_RC) //
                    || ZYPCommonFunc.hasValue(rmaConfigMsg.dlrRefNum_RC)) { // 2016/09/01 S21_NA#9806-2 Add Condition
                S21SsmEZDResult ssmRsltCsmpChk = NWAL1500QueryForSaveSubmit.getInstance().checkCSMPNum(bizMsg, rmaConfigMsg);
                boolean isErrCsmp = false;
                if (!ssmRsltCsmpChk.isCodeNormal()) {
                    isErrCsmp = true;
                } else {
                    BigDecimal rsltCsmpChk = (BigDecimal) ssmRsltCsmpChk.getResultObject();
                    if (BigDecimal.ZERO.compareTo(rsltCsmpChk) == 0) {
                        isErrCsmp = true;
                    }
                }
                if (isErrCsmp) {
                    rmaConfigMsg.csmpContrNum_RC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(rmaConfigMsg.csmpContrNum_RC)});
                    rmaConfigMsg.dlrRefNum_RC.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(rmaConfigMsg.dlrRefNum_RC)});
                    rslt = true;
                    // 2018/01/31 S21_NA#19808 Add Start
                    Integer errPage = Integer.valueOf(rmaConfigMsg.xxPageNum_RC.getValueInt());
                    if (!errorPageList.contains(errPage)) {
                        errorPageList.add(errPage);
                    }
                    // 2018/01/31 S21_NA#19808 Add End
                }
            }
            // 2016/08/30 S21_NA#9806 Add End
        }

        // For Performance QC#8166 Add Start
        Map<String, String> mdseCdMap = new HashMap<String, String>();
        Map<String, String> prcCatgCdMap = new HashMap<String, String>();
        Map<String, String> rtlWhCdMap = new HashMap<String, String>();
        Map<Map<String, String>, String> rtlSwhCdMap = new HashMap<Map<String, String>, String>();
        // For Performance QC#8166 Add End

        for (int n = 0; n < glblMsg.D.getValidCount(); n++) {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(n);
            if (!RTRN_LINE_STS.CANCELLED.equals(glblMsg.D.no(n).ordLineStsCd_RL.getValue()) // QC#1620
                    // START 2023/05/11 R.Azucena [QC#61514 MOD]
                    // && !RTRN_LINE_STS.CLOSED.equals(glblMsg.D.no(n).ordLineStsCd_RL.getValue())) { // 2016/09/06 S21_NA#12435 Add
                    && !RTRN_LINE_STS.CLOSED.equals(glblMsg.D.no(n).ordLineStsCd_RL.getValue()) // 2016/09/06 S21_NA#12435 Add
                    && !S21StringUtil.isEquals(bizMsg.varCharConstVal_TB.getValue(), glblMsg.D.no(n).rtrnLineStsDescTxt_RL.getValue())) {
                    // END 2023/05/11 R.Azucena [QC#61514 MOD]

                // Add Start 2019/11/15 QC#54515
                if (checkMultipleIBItem(bizMsg.glblCmpyCd.getValue(), rmaLineMsg, glblMsg.C)) {
                    rmaLineMsg.ordCustUomQty_RL.setErrorInfo(1, NWAM0975E);
                    rslt = true;
                    Integer errPage = Integer.valueOf(rmaLineMsg.xxPageNum_RL.getValueInt());
                    if (!errorPageList.contains(errPage)) {
                        errorPageList.add(errPage);
                    }
                }
                // Add End 2019/11/15 QC#54515

                if (!isCalledBook) { // For Performance QC#10321 Add
                    // Item
                    // For Performance QC#8166 Mod Start
                    String mdseCd = getMdseCdFromCache(bizMsg, rmaLineMsg.mdseCd_RL.getValue(), mdseCdMap);
                    if (!ZYPCommonFunc.hasValue(mdseCd)) {
                        rmaLineMsg.mdseCd_RL.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(rmaLineMsg.mdseCd_RL) });
                        rslt = true;
                        // 2018/01/31 S21_NA#19808 Add Start
                        Integer errPage = Integer.valueOf(rmaLineMsg.xxPageNum_RL.getValueInt());
                        if (!errorPageList.contains(errPage)) {
                            errorPageList.add(errPage);
                        }
                        // 2018/01/31 S21_NA#19808 Add End
                    }
                    // ssmRslt = this.queryForSaveSubmit.checkMdseCdFromAllMdseV(bizMsg, rmaLineMsg.mdseCd_RL.getValue());
                    // if (!ssmRslt.isCodeNormal()) {
                    //     rmaLineMsg.mdseCd_RL.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(rmaLineMsg.mdseCd_RL)});
                    //     rslt = true;
                    // }
                    // For Performance QC#8166 Mod End

                    // Sell Price Category
                    // For Performance QC#8166 Mod Start
                    String prcCatgCd = getPrcCatgCdFromCache(bizMsg, rmaLineMsg.prcCatgNm_RL.getValue(), prcCatgCdMap);
                    if (ZYPCommonFunc.hasValue(prcCatgCd)) {
                        ZYPEZDItemValueSetter.setValue(rmaLineMsg.prcCatgCd_RL, prcCatgCd);
                    } else {
                        rmaLineMsg.prcCatgNm_RL.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(rmaLineMsg.prcCatgNm_RL) });
                        rslt = true;
                        // 2018/01/31 S21_NA#19808 Add Start
                        Integer errPage = Integer.valueOf(rmaLineMsg.xxPageNum_RL.getValueInt());
                        if (!errorPageList.contains(errPage)) {
                            errorPageList.add(errPage);
                        }
                        // 2018/01/31 S21_NA#19808 Add End
                    }
                    // ssmRslt = this.queryForSaveSubmit.checkPriceCatgNm(bizMsg, rmaLineMsg.prcCatgNm_RL.getValue());
                    // if (ssmRslt.isCodeNormal()) {
                    //     List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
                    //     ZYPEZDItemValueSetter.setValue(rmaLineMsg.prcCatgCd_RL, rsltMapList.get(0).get("PRC_CATG_CD"));
                    // } else {
                    //     rmaLineMsg.prcCatgNm_RL.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(rmaLineMsg.prcCatgNm_RL)});
                    //     rslt = true;
                    // }
                    // For Performance QC#8166 Mod End

                    // Retail WH (Name is displayed, then check with name
                    // if (NWAL1500CommonLogic.isMdseTangible(bizMsg.glblCmpyCd.getValue(), rmaLineMsg.mdseCd_RL.getValue())) {
                    // S21_NA#2522
                    if (NWAL1500CommonLogic.needsWh(bizMsg.glblCmpyCd.getValue(), rmaLineMsg.mdseCd_RL.getValue())) {
                        // For Performance QC#8166 Mod Start
                        // 2020/03/16 QC#56132 Mod Start
                        // String rtlWhCd = getRtlWhCdFromCache(bizMsg, rmaLineMsg.rtlWhNm_RL.getValue(), rtlWhCdMap);
                        String rtlWhCd = getRtlWhCdFromCacheForRMA(bizMsg, rmaLineMsg.rtlWhNm_RL, rtlWhCdMap);
                        // 2020/03/16 QC#56132 Mod End
                        if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlWhCd_RL, rtlWhCd);
                        } else {
                            if (rmaLineMsg.rtlWhNm_RL.getErrorCode() == 0) { // 2020/03/16 QC#56132 Add
                                rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(rmaLineMsg.rtlWhCd_RL) });
                            }
                            rslt = true;
                            // 2018/01/31 S21_NA#19808 Add Start
                            Integer errPage = Integer.valueOf(rmaLineMsg.xxPageNum_RL.getValueInt());
                            if (!errorPageList.contains(errPage)) {
                                errorPageList.add(errPage);
                            }
                            // 2018/01/31 S21_NA#19808 Add End
                        }
                        // ssmRslt = NWAL1500QueryForLineConfig.getInstance().getWhInfoWithRsn(bizMsg, rmaLineMsg);
                        // if (ssmRslt.isCodeNormal()) {
                        //     List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
                        //     ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlWhCd_RL, rsltMapList.get(0).get("RTL_WH_CD"));
                        // } else {
                        //     rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(rmaLineMsg.rtlWhCd_RL)});
                        //     rslt = true;
                        // }
                        // For Performance QC#8166 Mod End

                        // Retail Sub Warehouse
                        if (ZYPCommonFunc.hasValue(rmaLineMsg.rtlSwhNm_RL)) {
                            // For Performance QC#8166 Mod Start
                            String rtlSwhCd = getRtlSwhCdFromCache(bizMsg, rmaLineMsg.rtlWhCd_RL.getValue(), rmaLineMsg.rtlSwhNm_RL.getValue(), rtlSwhCdMap);
                            if (ZYPCommonFunc.hasValue(rtlSwhCd)) {
                                ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhCd_RL, rtlSwhCd);
                            } else {
                                rmaLineMsg.rtlSwhNm_RL.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(rmaLineMsg.rtlSwhCd_RL) });
                                rslt = true;
                                // 2018/01/31 S21_NA#19808 Add Start
                                Integer errPage = Integer.valueOf(rmaLineMsg.xxPageNum_RL.getValueInt());
                                if (!errorPageList.contains(errPage)) {
                                    errorPageList.add(errPage);
                                }
                                // 2018/01/31 S21_NA#19808 Add End
                            }
                            // ssmRslt = this.queryForSaveSubmit.checkRtlSubWhWithName(bizMsg, rmaLineMsg);
                            // if (ssmRslt.isCodeNormal()) {
                            //     Map<String, String> rsltMap = (Map<String, String>) ssmRslt.getResultObject();
                            //     ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhCd_RL, rsltMap.get("RTL_SWH_CD"));
                            // } else {
                            //     rmaLineMsg.rtlSwhNm_RL.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(rmaLineMsg.rtlSwhCd_RL)});
                            //     rslt = true;
                            // }
                            // For Performance QC#8166 Mod End
                        }
                    } else {
                        rmaLineMsg.rtlWhCd_RL.clear();
                        rmaLineMsg.rtlWhNm_RL.clear();
                        rmaLineMsg.rtlSwhCd_RL.clear();
                        rmaLineMsg.rtlSwhNm_RL.clear();
                    }
                }

                // Machine Serial
                if (ZYPCommonFunc.hasValue(rmaLineMsg.serNum_RL)) {
                    // 2016/02/22 S21_NA#3112 Del Start
                    //                ssmRslt = this.queryForSaveSubmit.checkMachSerNum(bizMsg, rmaLineMsg.serNum_RL.getValue(), rmaLineMsg.mdseCd_RL.getValue());
                    //                if (!ssmRslt.isCodeNormal()) {
                    //                    rmaLineMsg.serNum_RL.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(rmaLineMsg.serNum_RL)});
                    //                    rslt = true;
                    //                }
                    // 2016/02/22 S21_NA#3112 Del Start
                    doNothing();
                }

                // Floor Price List
                if (ZYPCommonFunc.hasValue(rmaLineMsg.flPrcListNm_RL)) {
                    // For Performance QC#8166 Mod Start
                    String flPrcListCd = getPrcCatgCdFromCache(bizMsg, rmaLineMsg.flPrcListNm_RL.getValue(), prcCatgCdMap);
                    if (ZYPCommonFunc.hasValue(flPrcListCd)) {
                        ZYPEZDItemValueSetter.setValue(rmaLineMsg.flPrcListCd_RL, flPrcListCd);
                    } else {
                        rmaLineMsg.flPrcListNm_RL.setErrorInfo(1, NWAM0181E, new String[] {itemNameList.getItemName(rmaLineMsg.flPrcListNm_RL)});
                        rslt = true;
                        // 2018/01/31 S21_NA#19808 Add Start
                        Integer errPage = Integer.valueOf(rmaLineMsg.xxPageNum_RL.getValueInt());
                        if (!errorPageList.contains(errPage)) {
                            errorPageList.add(errPage);
                        }
                        // 2018/01/31 S21_NA#19808 Add End
                    }
                    // ssmRslt = this.queryForSaveSubmit.checkPriceCatgNm(bizMsg, rmaLineMsg.flPrcListNm_RL.getValue());
                    // if (ssmRslt.isCodeNormal()) {
                    //     List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
                    //     ZYPEZDItemValueSetter.setValue(rmaLineMsg.flPrcListCd_RL, rsltMapList.get(0).get("PRC_CATG_CD"));
                    // } else {
                    //     rmaLineMsg.flPrcListNm_RL.setErrorInfo(1, NWAM0181E, new String[] {this.itemNameList.getItemName(rmaLineMsg.flPrcListNm_RL)});
                    //     rslt = true;
                    // }
                    // For Performance QC#8166 Mod End
                }
            }
        }
        // 2018/01/31 S21_NA#19808 Add Start
        if (!errorPageList.isEmpty()) {
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, errorPageList.get(0).intValue());
        }
        // 2018/01/31 S21_NA#19808 Add End

        return rslt;
    }
    // QC#29693 2019/01/08 Add Start
    public boolean isLineConfigTabPrcAmtError(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        boolean rslt = false;
        List<Integer> errorPageList = new ArrayList<Integer>(0);
        for (int n = 0; n < glblMsg.B.getValidCount(); n++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(n);
            if (!ORD_LINE_STS.CANCELLED.equals(glblMsg.B.no(n).ordLineStsCd_LL.getValue())
                    && !ORD_LINE_STS.CLOSED.equals(glblMsg.B.no(n).ordLineStsCd_LL.getValue())) {
                if (!ZYPCommonFunc.hasValue(lineMsg.crRebilCd_LL)) {
                    DS_ORD_LINE_CATGTMsg dsOrdLineCatgTMsg = getDsOrdLineCatg(bizMsg.glblCmpyCd.getValue(), lineMsg.dsOrdLineCatgCd_LL.getValue());
                    if (S21StringUtil.isEquals(DS_ORD_LINE_SGN.POSITIVE_ONLY, dsOrdLineCatgTMsg.dsOrdLineSgnCd.getValue())) {
                        if (BigDecimal.ZERO.compareTo(lineMsg.entDealNetUnitPrcAmt_LL.getValue()) > 0) {
                            lineMsg.entCpoDtlDealSlsAmt_LL.setErrorInfo(1, NWAM0966E, new String[] {});
                            rslt = true;
                            Integer errPage = Integer.valueOf(lineMsg.xxPageNum_LL.getValueInt());
                            if (!errorPageList.contains(errPage)) {
                                errorPageList.add(errPage);
                            }
                        }
                    } else if (S21StringUtil.isEquals(DS_ORD_LINE_SGN.NEGATIVE_ONLY, dsOrdLineCatgTMsg.dsOrdLineSgnCd.getValue())) {
                        if (BigDecimal.ZERO.compareTo(lineMsg.entDealNetUnitPrcAmt_LL.getValue()) < 0) {
                            lineMsg.entCpoDtlDealSlsAmt_LL.setErrorInfo(1, NWAM0967E, new String[] {});
                            rslt = true;
                            Integer errPage = Integer.valueOf(lineMsg.xxPageNum_LL.getValueInt());
                            if (!errorPageList.contains(errPage)) {
                                errorPageList.add(errPage);
                            }
                        }
                    }
                }
            }
        }
        if (!errorPageList.isEmpty()) {
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, errorPageList.get(0).intValue());
        }
        return rslt;
    }

    public boolean isRMATabPrcAmtError(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        boolean rslt = false;
        List<Integer> errorPageList = new ArrayList<Integer>(0);
        for (int n = 0; n < glblMsg.D.getValidCount(); n++) {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(n);
            if (!RTRN_LINE_STS.CANCELLED.equals(glblMsg.D.no(n).ordLineStsCd_RL.getValue()) 
                    && !RTRN_LINE_STS.CLOSED.equals(glblMsg.D.no(n).ordLineStsCd_RL.getValue())) {
                if (!ZYPCommonFunc.hasValue(rmaLineMsg.crRebilCd_RL)) {
                    DS_ORD_LINE_CATGTMsg dsOrdLineCatgTMsg = getDsOrdLineCatg(bizMsg.glblCmpyCd.getValue(), rmaLineMsg.dsOrdLineCatgCd_RL.getValue());
                    if (S21StringUtil.isEquals(DS_ORD_LINE_SGN.POSITIVE_ONLY, dsOrdLineCatgTMsg.dsOrdLineSgnCd.getValue())) {
                        if (BigDecimal.ZERO.compareTo(rmaLineMsg.entDealNetUnitPrcAmt_RL.getValue()) > 0) {
                            rmaLineMsg.entCpoDtlDealSlsAmt_RL.setErrorInfo(1, NWAM0966E, new String[] {});
                            rslt = true;
                            Integer errPage = Integer.valueOf(rmaLineMsg.xxPageNum_RL.getValueInt());
                            if (!errorPageList.contains(errPage)) {
                                errorPageList.add(errPage);
                            }
                        }
                    } else if (S21StringUtil.isEquals(DS_ORD_LINE_SGN.NEGATIVE_ONLY, dsOrdLineCatgTMsg.dsOrdLineSgnCd.getValue())) {
                        if (BigDecimal.ZERO.compareTo(rmaLineMsg.entDealNetUnitPrcAmt_RL.getValue()) < 0) {
                            rmaLineMsg.entCpoDtlDealSlsAmt_RL.setErrorInfo(1, NWAM0967E, new String[] {});
                            rslt = true;
                            Integer errPage = Integer.valueOf(rmaLineMsg.xxPageNum_RL.getValueInt());
                            if (!errorPageList.contains(errPage)) {
                                errorPageList.add(errPage);
                            }
                        }
                    }
                }
            }
        }
        if (!errorPageList.isEmpty()) {
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, errorPageList.get(0).intValue());
        }
        return rslt;
    }

    private boolean getTocData(NWAL1500CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.slsRepTocCd)) {
            return false;
        }
        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.checkSalesRep(bizMsg.glblCmpyCd.getValue(), bizMsg.slsRepTocCd.getValue());
        if (!ssmResult.isCodeNormal()) {
            return false;
        }
        Map<String, String> resultMap = (Map<String, String>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocNm, resultMap.get("TOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocCd, bizMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, resultMap.get("COA_BR_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDescTxt, resultMap.get("COA_BR_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd, resultMap.get("COA_EXTN_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnDescTxt, resultMap.get("COA_EXTN_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CB, resultMap.get("COA_BR_ITEM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CE, resultMap.get("COA_EXTN_ITEM"));
        return true;
    }
    // QC#29693 2019/01/08 Add End

    /**
     * <pre>
     * Set CSMP Price List
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * </pre>
     */
    public void setCsmpPrcList(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        // Key: Price Base date Value: CSMP Price List
        Map<String, String> prcListMap = new HashMap<String, String>();
        if (ZYPCommonFunc.hasValue(bizMsg.csmpContrNum)
                || ZYPCommonFunc.hasValue(bizMsg.dlrRefNum)) {
            // GetCsmpPriceList
            NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
            prcApiPMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            prcApiPMsg.xxModeCd.setValue(NWZC157001.GET_PRICE_LIST);
            prcApiPMsg.prcCtxTpCd.setValue(PRC_CTX_TP.CSMP_CREDIT); // 40:CSMP Credit
            prcApiPMsg.dsOrdCatgCd.setValue(bizMsg.dsOrdCatgCd.getValue());
            prcApiPMsg.dsOrdTpCd.setValue(bizMsg.dsOrdTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, bizMsg.lineBizTpCd);
            prcApiPMsg.dsAcctNum.setValue(bizMsg.sellToCustCd.getValue());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.csmpNum, bizMsg.csmpContrNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dlrRefNum, bizMsg.dlrRefNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcContrNum, bizMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, bizMsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);
            NWZC157001 prcApi = new NWZC157001();

            for (int n = 0; n < glblMsg.B.getValidCount(); n++) { // 2018/01/31 S21_NA#19808 Mod
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(n); // 2018/01/31 S21_NA#19808 Mod
                if (ZYPCommonFunc.hasValue(lineMsg.csmpPrcListCd_LL)) {
                    continue;
                }
                // QC#9437 2016/06/21 Mod Start
                // String prcBaseDt = bizMsg.ordDt.getValue();
                // QC#9437 2016/06/21 Mod Start
                // if (ZYPCommonFunc.hasValue(lineMsg.prcBaseDt_LL)) {
                //     prcBaseDt = lineMsg.prcBaseDt_LL.getValue();
                // }
                String prcBaseDt = bizMsg.slsDt.getValue();
                // QC#9437 2016/06/21 Mod End
                String csmpPrcList = prcListMap.get(prcBaseDt);
                if (null != csmpPrcList) {
                    if ("".equals(csmpPrcList)) {
                        lineMsg.csmpPrcListCd_LL.clear();
                    } else {
                        lineMsg.csmpPrcListCd_LL.setValue(csmpPrcList);
                        glblMsg.U.no(n).csmpPrcListCd_LL.setValue(csmpPrcList); // 2016/03/01 Order Entry #7 Add
                    }
                } else {
                    prcApiPMsg.prcBaseDt.setValue(prcBaseDt);
                    prcApi.execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);
                    List<String> apiErrMsgList = checkApiMessage(prcApiPMsg);
                    if (apiErrMsgList.isEmpty()) {
                        csmpPrcList = prcApiPMsg.xxPrcList.no(0).prcCatgCd.getValue();
                        prcListMap.put(prcBaseDt, csmpPrcList);
//                    } else {
//                        String msgId = getApiMessageId(apiErrMsgList);
//                        String [] addlMsgArray = getApiMessageAddlInfoArray(apiErrMsgList);
//                        if (null != addlMsgArray) {
//                            bizMsg.csmpContrNum.setErrorInfo(1, msgId, addlMsgArray);
//                        } else {
//                            bizMsg.csmpContrNum.setErrorInfo(1, msgId);
//                        }
//                        return;
                    }
                }
                if (ZYPCommonFunc.hasValue(csmpPrcList)) {
                    lineMsg.csmpPrcListCd_LL.setValue(csmpPrcList);
                    glblMsg.U.no(n).csmpPrcListCd_LL.setValue(csmpPrcList); // 2016/03/01 Order Entry #7 Add
                } else {
                    lineMsg.csmpPrcListCd_LL.clear();
                }
            }

//            for (int n = 0; n < bizMsg.D.getValidCount(); n++) {
//                NWAL1500_DCMsg rmaLineMsg = bizMsg.D.no(n);
//                if (ZYPCommonFunc.hasValue(rmaLineMsg.csmpPrcListCd_RL)) {
//                    continue;
//                }
//                String prcBaseDt = bizMsg.ordDt.getValue();
//                if (ZYPCommonFunc.hasValue(rmaLineMsg.prcBaseDt_RL)) {
//                    prcBaseDt = rmaLineMsg.prcBaseDt_RL.getValue();
//                }
//                String csmpPrcList = prcListMap.get(prcBaseDt);
//                if (null != csmpPrcList) {
//                    if ("".equals(csmpPrcList)) {
//                        rmaLineMsg.csmpPrcListCd_RL.clear();
//                    } else {
//                        rmaLineMsg.csmpPrcListCd_RL.setValue(csmpPrcList);
//                    }
//                } else {
//                    prcApiPMsg.prcBaseDt.setValue(prcBaseDt);
//                    prcApi.execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);
//                    List<String> apiErrMsgList = checkApiMessage(prcApiPMsg);
//                    if (apiErrMsgList.isEmpty()) {
//                        csmpPrcList = prcApiPMsg.xxPrcList.no(0).prcCatgCd.getValue();
//                        prcListMap.put(prcBaseDt, csmpPrcList);
//                    } else {
//                        String msgId = getApiMessageId(apiErrMsgList);
//                        String [] addlMsgArray = getApiMessageAddlInfoArray(apiErrMsgList);
//                        if (null != addlMsgArray) {
//                            bizMsg.csmpContrNum.setErrorInfo(1, msgId, addlMsgArray);
//                        } else {
//                            bizMsg.csmpContrNum.setErrorInfo(1, msgId);
//                        }
//                        return;
//                    }
//                }
//                if (ZYPCommonFunc.hasValue(csmpPrcList)) {
//                    rmaLineMsg.csmpPrcListCd_RL.setValue(csmpPrcList);
//                } else {
//                    rmaLineMsg.csmpPrcListCd_RL.clear();
//                }
//            }
        }
    }

    /**
     * Set Main Machine Flag for every config, every line
     * @param bizMsg Business Message
     */
    public void checkConfigType(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/31 S21_NA#19808 Mod

        // 2018/01/31 S21_NA#19808 Mod bizMsg => glblMsg
        // 2017/02/24 S21_NA#17714 Mod Start
        // boolean isOrderRetailEquipment = isOrderRetailEquipment(bizMsg); 
        boolean isOrderRetailEquipment = NWAL1500CommonLogic.isOrderRetailEquipment(bizMsg);
        NWAL1500CommonLogic.setAllNForBaseCompFLg(bizMsg, glblMsg, new Boolean(isOrderRetailEquipment)); // 2018/01/29 S21_NA#19808 Mod
        // 2017/02/24 S21_NA#17714 Mod End
        Map<String, Map<String, String>> baseComponentMap = new HashMap<String, Map<String, String>>();

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg configMsg = glblMsg.A.no(i);
            String configTp = configMsg.configTpCd_LC.getValue();
            // if (CONFIG_TP.ADD_TO_CONFIG.equals(configTp)) {
            // Out bound N Y N
            if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configTp, CONFIG_CATG.OUTBOUND, false, true, false)) { // S21_NA#955
                boolean isReqBaseCmptFlg = NWXC150001DsCheck.isReqBaseCmptFlgAtConfigTp(bizMsg.glblCmpyCd.getValue(), configTp); // 2016/10/27 S21_NA#14897 Add
                // 2016/10/18 S21_NA#13107 Add Start
                boolean hasRebillLine = false;
                String dsOrdPosnNum = "";
                // 2016/10/18 S21_NA#13107 Add End
                for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                    NWAL1500_BSMsg lineMsg = glblMsg.B.no(j);
                    if (configMsg.dsOrdPosnNum_LC.getValue().equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
                        // 2016/10/18 S21_NA#13107 Add Start
                        if (!dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
                            dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
                            hasRebillLine = hasRebillLine(bizMsg, lineMsg);
                        }
                        if (hasRebillLine) {
                            continue;
                        }
                        // 2024/02/29 QC#62427 D.Tanaka START
//                        if (!isReqBaseCmptFlg) { // 2016/10/27 S21_NA#14897 Add
                        if (!isReqBaseCmptFlg 
                                || CONFIG_TP.TO_SALES_CONVERSION.equals(configTp) && !COA_PROJ.MACHINE.equals(lineMsg.coaMdseTpCd_LL.getValue())) { // 2016/10/27 S21_NA#14897 Add
                        // 2024/02/29 QC#62427 D.Tanaka END
                            lineMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
                        } // 2016/10/27 S21_NA#14897 Add
                    }
                }
                // } else if (CONFIG_TP.NEW.equals(configTp)
                // || (CONFIG_TP.ADD_TO_CONFIG.equals(configTp))) {
                // Out bound Y N Y
            } else if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configTp, CONFIG_CATG.OUTBOUND, true, false, true)) { // S21_NA#955
/*
                for (int j = 0; j < bizMsg.B.getValidCount(); j++) {
                    NWAL1500_BCMsg bcMsg = bizMsg.B.no(j);
                    if (acMsg.dsOrdPosnNum_LC.getValue().equals(bcMsg.dsOrdPosnNum_LL.getValue())) {
                        // 2016/04/28 S21_NA#7516 Add Start
                        // NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, i, j);
                        if (isOrderRetailEquipment(bizMsg)) {
                            NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, i, j);
                        } else {
                            bcMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
                        }
                        // 2016/04/28 S21_NA#7516 Add End
                    }
                }
*/
                // 2016/10/18 S21_NA#13107 Add Start
                boolean hasRebillLine = false;
                boolean isReqBaseCmptFlg = NWXC150001DsCheck.isReqBaseCmptFlgAtConfigTp(bizMsg.glblCmpyCd.getValue(), configTp); // 2016/10/27 S21_NA#14897 Add
                String dsOrdPosnNum = "";
                // 2016/10/18 S21_NA#13107 Add End
                for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                    NWAL1500_BSMsg lineMsg = glblMsg.B.no(j);
                    if (configMsg.dsOrdPosnNum_LC.getValue().equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
                        // 2016/10/18 S21_NA#13107 Add Start
                        if (!dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
                            dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
                            hasRebillLine = hasRebillLine(bizMsg, lineMsg);
                        }
                        if (hasRebillLine) {
                            continue;
                        }
                        // 2016/10/18 S21_NA#13107 Add End
                        if (!isReqBaseCmptFlg) { // 2016/10/27 S21_NA#14897 Add
                            lineMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
                        } // 2016/10/27 S21_NA#14897 Add
                    }
                }

                if (isOrderRetailEquipment) {
                    int baseIndex = getBaseComponentIndex(bizMsg, glblMsg, configMsg, baseComponentMap, false); // 2018/01/31 S21_NA#19808 Mod
                    if (baseIndex > -1) {
                        NWAL1500_BSMsg lineMsg = glblMsg.B.no(baseIndex);
                        if (configMsg.dsOrdPosnNum_LC.getValue().equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
                            lineMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_ON_Y);
                            //bcMsg.dplyLineRefNum_LL.clear();   // QC#10979 2016/10/27 Del
                        }
                    }
                }
            }
        }
    }

    // For Performance QC#8166 Add Start
    /**
     * <pre>
     * get base component index of selected cpo config.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param confMsg selected config.
     * @param baseComponentMap cache map
     * @return base componet index of global message from line messages.
     * </pre>
     */
//    public int getBaseComponentIndex(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg confMsg, Map<String, Map<String, String>> baseComponentMap) { // 2018/01/31 S21_NA#19808 Mod
//        return getBaseComponentIndex(bizMsg, glblMsg, confMsg, baseComponentMap, false); // 2018/01/31 S21_NA#19808 Mod
//    }

    // 2018/01/31 S21_NA#19808 Add Start
    /**
     * <pre>
     * get base component index of selected cpo config.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param confMsg selected config.
     * @param baseComponentMap cache map
     * @return base componet index of global message from line messages.
     * </pre>
     */
//    public int getBaseComponentIndex(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg confMsg, Map<String, Map<String, String>> baseComponentMap) {
//        return getBaseComponentIndex(bizMsg, glblMsg, NWAL1500CommonLogicForPagination.getGlobalConfigMsgFromBizMsg(glblMsg, confMsg), baseComponentMap, false);
//    }

    /**
     * <pre>
     * get base component index of selected cpo config.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param confMsg selected config (Business Message).
     * @param baseComponentMap cache map
     * @param deleteFlg true: delete or cancel. false: ordinal mode
     * @return base componet index of global message from line messages.
     * </pre>
     */
//    public int getBaseComponentIndex(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg confMsg, Map<String, Map<String, String>> baseComponentMap, boolean deleteFlg) {
//        return getBaseComponentIndex(bizMsg, glblMsg, NWAL1500CommonLogicForPagination.getGlobalConfigMsgFromBizMsg(glblMsg, confMsg), baseComponentMap, deleteFlg);
//    }
    // 2018/01/31 S21_NA#19808 Add End
    /**
     * <pre>
     * get base component index of selected cpo config.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param slctConfIndex selected config index.
     * @param baseComponentMap cache map
     * @param deleteFlg true: delete or cancel. false: ordinal mode
     * @return base componet index from line messages.
     */
    public int getBaseComponentIndex(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg confMsg, Map<String, Map<String, String>> baseComponentMap, boolean deleteFlg) { // 2018/01/31 S21_NA#19808 Mod

        // 2018/01/31 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
//        NWAL1500_ASMsg confMsg = glblMsg.A.no(slctConfIndex);

        String dsOrdPosnNum = confMsg.dsOrdPosnNum_LC.getValue();
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();

        // 2018/05/20 S21_NA#25604 Add Start
        List<String> mainMachMdseTpList = NWXC150001DsCheck.getMainMachineMdseTypeList(glblCmpyCd);
        // 2018/05/20 S21_NA#25604 Add End

        int baseComponentIndex = -1;
        int scdBaseComponentIndex = -1;
        NWAL1500_BSMsg mainMachineBSMsg = null;
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            mainMachineBSMsg = glblMsg.B.no(i);

            // 2016/11/29 S21_NA#16214 Add Start
            if (ORD_ENTRY_ACT.DELETE.equals(bizMsg.ordEntryActCd.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(mainMachineBSMsg.xxChkBox_LL.getValue())) {
                    continue;
                }
            }
            // 2016/11/29 S21_NA#16214 Add End

            if (!dsOrdPosnNum.equals(mainMachineBSMsg.dsOrdPosnNum_LL.getValue())) {
                continue;
            }

            if (NWAL1500CommonLogic.isCancelLine(bizMsg, mainMachineBSMsg)) {
                continue;
            }
            // 2016/08/23 S21_NA#13504 Add Start
            if (!ZYPCommonFunc.hasValue(mainMachineBSMsg.mdseCd_LL)) {
                continue;
            }
            // 2016/08/23 S21_NA#13504 Add End

            MDSETMsg mdseTMsgMain = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mainMachineBSMsg.mdseCd_LL.getValue());
            // 2017/06/28 S21_NA#19628 Add Start
            if (mdseTMsgMain == null) {
                continue;
            }
            // 2017/06/28 S21_NA#19628 Add End
            // 2016/08/04 S21_NA#13012 Add Start
            if (S21StringUtil.isEquals(MDSE_TP.SET, mdseTMsgMain.mdseTpCd.getValue())
                    || !S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsgMain.invtyCtrlFlg.getValue())) {
                continue;
            }
            if (deleteFlg && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mainMachineBSMsg.xxChkBox_LL.getValue())) {
                continue;
            }
            // 2016/08/04 S21_NA#13012 Add End
//            String baseMdseCd = mdseTMsgMain.mdseCd.getValue(); 2018/05/20 S21_NA#25604 Del

            // 2016/07/11 S21_NA#7821 Mod Start
//            // 2016/05/17 S21_NA#8144-2 Add Start
//            if (S21StringUtil.isEquals(MDSE_TP.SET, mdseTMsgMain.mdseTpCd.getValue()) //
//                    || S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, mdseTMsgMain.invtyCtrlFlg.getValue())) {
//                continue;
//            }
//            // 2016/05/17 S21_NA#8144-2 Add End
//            if (ZYPConstant.FLG_ON_Y.equals(mainMachineBcMsg.baseCmptFlg_LL.getValue())) {
//                if (isMainMachine(glblCmpyCd, baseMdseCd, false, baseComponentMap)) {
//                    baseComponentIndex = i;
//                    break;
//                }
//            }
//
//            if (scdBaseComponentIndex == -1) {
//                if (isMainMachine(glblCmpyCd, baseMdseCd, true, baseComponentMap)) {
//                    scdBaseComponentIndex = i;
//                }
//            }
            // 2018/05/20 S21_NA#25604 Del Start
//            Map<String, String> rsltMap = null;
//            if (baseComponentMap != null) {
//                rsltMap = baseComponentMap.get(baseMdseCd);
//                if (rsltMap == null) {
//                    S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getBaseComponentFlag(glblCmpyCd, baseMdseCd);
//                    rsltMap = (Map<String, String>) ssmRslt.getResultObject();
//                    baseComponentMap.put(baseMdseCd, rsltMap);
//                }
//            } else {
//                S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getBaseComponentFlag(glblCmpyCd, baseMdseCd);
//                rsltMap = (Map<String, String>) ssmRslt.getResultObject();
//            }
//
//            String mdseTpCtxTpCd = rsltMap.get("MDSE_TP_CTX_TP_CD");
//            String instlBaseCtrlFlg = rsltMap.get("INSTL_BASE_CTRL_FLG");
//            if (ZYPCommonFunc.hasValue(mdseTpCtxTpCd)) {
//                baseComponentIndex = i;
//                break;
//            } else if (scdBaseComponentIndex == -1 && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instlBaseCtrlFlg)) {
//                scdBaseComponentIndex = i;
//            }
//            // 2016/07/11 S21_NA#7821 Mod End
            // 2018/05/20 S21_NA#25604 Del End
            // 2018/05/20 S21_NA#25604 Add Start
            if (mainMachMdseTpList.contains(mdseTMsgMain.coaMdseTpCd.getValue())) {
                baseComponentIndex = i;
                break;
            } else if (scdBaseComponentIndex == -1 && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsgMain.instlBaseCtrlFlg.getValue())) {
                scdBaseComponentIndex = i;
            }
            // 2018/05/20 S21_NA#25604 Add End
        }

        int resultIndex = baseComponentIndex;
        if (resultIndex == -1) {
            // 2018/01/11 S21_NA#23329 Add Start
            // 2018/01/31 S21_NA#19808 Mod Start
//            bizMsg.A.no(slctConfIndex).svcMdlTpCd_LC.clear();
//            bizMsg.A.no(slctConfIndex).prntMdseCd_LC.clear();
            confMsg.svcMdlTpCd_LC.clear();
            confMsg.prntMdseCd_LC.clear();
            // 2018/01/31 S21_NA#19808 Mod End
            for (int n = 0; n < glblMsg.B.getValidCount(); n++) {
                if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(n).dsOrdPosnNum_LL.getValue())) {
                    glblMsg.B.no(n).baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
                }
            }
            // 2018/05/20 S21_NA#25604 Del Start
//            NSZC048001PMsg svcMdlApiPMsg = NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, confMsg); // 2018/01/31 S21_NA#19808 Mod
//            boolean isFoundSoftParent = false;
//            if (svcMdlApiPMsg != null //
//                    && NWXC150001DsCheck.isModelSoftware(bizMsg.glblCmpyCd.getValue(), svcMdlApiPMsg.mdlId.getValue())
//                    && ZYPCommonFunc.hasValue(svcMdlApiPMsg.prntMdseCd)) {
//                String softMdlPrntMdseCd = svcMdlApiPMsg.prntMdseCd.getValue();
//                for(int i = 0; i < glblMsg.B.getValidCount(); i++) {
//                    NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
//                    if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue()) //
//                            && NWXC150001DsCheck.isNearEqualsItem(glblCmpyCd, softMdlPrntMdseCd, lineMsg.mdseCd_LL.getValue())) {
//                        scdBaseComponentIndex = i;
//                        isFoundSoftParent = true;
//                        break;
//                    }
//                }
//                if (!isFoundSoftParent) {
//                    return -1;
//                }
//            }
            // 2018/05/20 S21_NA#25604 Del End
            // 2018/05/20 S21_NA#25604 Add Start
            NWXC150001SvcMdlFuncParamBean funcBean = NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, confMsg); // 2018/01/31 S21_NA#19808 Mod
            boolean isFoundSoftParent = false;
            if (funcBean != null //
                    && funcBean.isSvcMdlTpSoftWare() //
                    && ZYPCommonFunc.hasValue(funcBean.getPrntMdseCd())) {
                String softMdlPrntMdseCd = funcBean.getPrntMdseCd();
                for(int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
                    if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue()) //
                            && NWXC150001DsCheck.isNearEqualsItem(glblCmpyCd, softMdlPrntMdseCd, lineMsg.mdseCd_LL.getValue())) {
                        scdBaseComponentIndex = i;
                        isFoundSoftParent = true;
                        break;
                    }
                }
                if (!isFoundSoftParent) {
                    return -1;
                }
            }
            // 2018/05/20 S21_NA#25604 Add End
            // 2018/01/11 S21_NA#23329 Add End
            resultIndex = scdBaseComponentIndex;
        }
        return resultIndex;
    }

    // 2016/07/11 S21_NA#7821 Del Start
//    @SuppressWarnings("unchecked")
//    private boolean isMainMachine(String glblCmpyCd, String mdseCd, boolean isCheckInstlBaseCtrlFlg, Map<String, Map<String, String>> baseComponentMap) {
//
//        Map<String, String> rsltMap = null;
//
//        if (baseComponentMap.containsKey(mdseCd)) {
//            rsltMap = baseComponentMap.get(mdseCd);
//        } else {
//            S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getBaseComponentFlag(glblCmpyCd, mdseCd);
//            rsltMap = (Map<String, String>) ssmRslt.getResultObject();
//            baseComponentMap.put(mdseCd, rsltMap);
//        }
//
//        String instlBaseCtrlFlg = rsltMap.get("INSTL_BASE_CTRL_FLG");
//        String mdseTpCtxTpCd = rsltMap.get("MDSE_TP_CTX_TP_CD");
//
//        if (ZYPCommonFunc.hasValue(mdseTpCtxTpCd)) {
//            return true;
//        } else if (ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg) && isCheckInstlBaseCtrlFlg) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//    // For Performance QC#8166 Add End
    // 2016/07/11 S21_NA#7821 Del End

//    /**
//     * Relation check for Header Tab
//     * @param bizMsg Business Message
//     * @return true: have error false: no error
//     */
//    public boolean isRelationErrOnHeader(NWAL1500CMsg bizMsg) {
//        itemNameList.setBizMsg(bizMsg);
//
//        // Bill To Location and Bill To Account
//        S21SsmEZDResult ssmRslt = queryForSaveSubmit.checkBillToCustAndBillToAccnt(bizMsg);
//        String[] addlMsg = new String[2];
//        if (ssmRslt.isCodeNormal()) {
//            BigDecimal recCnt = (BigDecimal) ssmRslt.getResultObject();
//            if (BigDecimal.ZERO.compareTo(recCnt) == 0) {
//                addlMsg[0] = itemNameList.getItemName(bizMsg.billToCustCd);
//                addlMsg[1] = itemNameList.getItemName(bizMsg.billToCustAcctCd);
//                // NWAM0697E: Entered [@] has no relationship with the entered [@].
//                bizMsg.billToCustCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//                bizMsg.billToCustAcctCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//                return true;
//            }
//        } else {
//            addlMsg[0] = itemNameList.getItemName(bizMsg.billToCustCd);
//            addlMsg[1] = itemNameList.getItemName(bizMsg.billToCustAcctCd);
//            bizMsg.billToCustCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//            bizMsg.billToCustAcctCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//            return true;
//        }
//        // Ship to Location and Ship To Account
//        ssmRslt = queryForSaveSubmit.checkShipToCustAndShipToAccnt(bizMsg);
//        if (ssmRslt.isCodeNormal()) {
//            BigDecimal recCnt = (BigDecimal) ssmRslt.getResultObject();
//            if (BigDecimal.ZERO.compareTo(recCnt) == 0) {
//                addlMsg[0] = itemNameList.getItemName(bizMsg.shipToCustCd);
//                addlMsg[1] = itemNameList.getItemName(bizMsg.shipToCustAcctCd);
//                bizMsg.shipToCustCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//                bizMsg.shipToCustAcctCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//                return true;
//            }
//        } else {
//            addlMsg[0] = itemNameList.getItemName(bizMsg.shipToCustCd);
//            addlMsg[1] = itemNameList.getItemName(bizMsg.shipToCustAcctCd);
//            bizMsg.shipToCustCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//            bizMsg.shipToCustAcctCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//            return true;
//        }
//        // Sold To Location and Sold to Number
//        ssmRslt = queryForSaveSubmit.checkSoldToLocationAndSoldToAccnt(bizMsg);
//        if (ssmRslt.isCodeNormal()) {
//            BigDecimal recCnt = (BigDecimal) ssmRslt.getResultObject();
//            if (BigDecimal.ZERO.compareTo(recCnt) == 0) {
//                addlMsg[0] = itemNameList.getItemName(bizMsg.soldToCustLocCd);
//                addlMsg[1] = itemNameList.getItemName(bizMsg.sellToCustCd);
//                bizMsg.soldToCustLocCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//                bizMsg.sellToCustCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//                return true;
//            }
//        } else {
//            addlMsg[0] = itemNameList.getItemName(bizMsg.soldToCustLocCd);
//            addlMsg[1] = itemNameList.getItemName(bizMsg.sellToCustCd);
//            bizMsg.soldToCustLocCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//            bizMsg.sellToCustCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//            return true;
//        }
//        // Relation Check Bill To/Ship To/Sold To Eligible Check
//        NMZC610001PMsg custInfoGetApiPMsg = eligibleCustomer(bizMsg);
//        if (custInfoGetApiPMsg.EligibleCheckList.getValidCount() > 0) {
//            NMZC610001_EligibleCheckListPMsg eligPMsg = custInfoGetApiPMsg.EligibleCheckList.no(0);
//            if (ZYPConstant.FLG_OFF_N.equals(eligPMsg.dsAcctRelnBillToFlg_B.getValue())) {
//                addlMsg[0] = itemNameList.getItemName(bizMsg.billToCustCd);
//                addlMsg[1] = itemNameList.getItemName(bizMsg.sellToCustCd);
//                bizMsg.billToCustCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//                bizMsg.sellToCustCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//                return true;
//            }
//            if (ZYPConstant.FLG_OFF_N.equals(eligPMsg.dsAcctRelnShipToFlg_S.getValue())) {
//                addlMsg[0] = itemNameList.getItemName(bizMsg.shipToCustCd);
//                addlMsg[1] = itemNameList.getItemName(bizMsg.sellToCustCd);
//                bizMsg.shipToCustCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//                bizMsg.sellToCustCd.setErrorInfo(1, "NWAM0697E", addlMsg);
//                return true;
//            }
//        }
//
//        return false;
//    }

    /**
     * Relation check for Addl Header Tab
     * @param bizMsg Business Message
     * @return true: have error false: no error
     */
    public boolean isRelationErrOnAddlHeader(NWAL1500CMsg bizMsg) {
        return false;
    }

    /**
     * Relation check for Line Config Tab
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param isSave true: Save Process false: Submit Process
     * @param doNeedRePricing true : NeedRePricing false : do not
     * @param isOrderValidated true : Validated order false: not validated order
     * @return true: have error false: no error
     */
    public boolean isRelationErrOnLineConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean isSave, boolean doNeedRePricing, boolean isOrderValidated) { // 2016/09/05 S21_NA#6523 Add "isOrderValidated"
        // this.itemNameList.setBizMsg(bizMsg); // For Performance QC#8166 Del
        boolean rslt = false;
        boolean isAlreadyPrcOnDb = S21StringUtil.isEquals(ZYPConstant.FLG_ON_1, bizMsg.dsCpoPrcInd.getValue()); // 2016/09/05 S21_NA#6523 Add

        // 2016/09/05 S21_NA#6523 Add Start
        boolean isNeedPricing = false;
        if (doNeedRePricing) {
            isNeedPricing = needRePricing(bizMsg, glblMsg);
            if (isNeedPricing) {
                bizMsg.dsCpoPrcInd.setValue(ZYPConstant.FLG_OFF_0);
            }
        }
        // 2016/09/05 S21_NA#6523 Add Start

        // 17. need re-pricing?
        // if (isModifiedHdr(bizMsg, glblMsg)
        // || bizMsg.B.getValidCount() != glblMsg.B.getValidCount()) {
        // bizMsg.setMessageInfo("NWAM0673E", new String[]
        // {"Price $"});
        // return true;
        // }
        //
        // if (isModifiedDtl(bizMsg, glblMsg)) {
        // bizMsg.setMessageInfo("NWAM0673E", new String[]
        // {"Price $"});
        // return true;
        // }

        // QC#4078 Mod
//        if (doNeedRePricing) { 2016/04/20 S21_NA#5605 Mod Condition
//        if (doNeedRePricing && !isSave) {
        if (doNeedRePricing && (!isSave || isOrderValidated)) {
            if (hasListPriceErrors(bizMsg, glblMsg)) {
                bizMsg.setMessageInfo(NWAM0673E, new String[] {"Pricing" });
                return true;
            }
            // S21_NA#1952
            // 2016/03/30 S21_NA#5523 Mod Start
//            if (needRePricing(bizMsg, glblMsg)) {
//                bizMsg.setMessageInfo(NWAM0673E, new String[] {"Pricing" });
//                return true;
//            }
//            if (!isSave) { 2016/09/05 S21_NA#6523 Mod
            if (!isSave || isOrderValidated) { // 2016/09/05 S21_NA#6523 Mod
                boolean isAlreadyPricing = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.xxBtnFlg_PR.getValue());
                // 2016/09/05 S21_NA#6523 Add Start
                if (isAlreadyPrcOnDb) {
                    isAlreadyPricing = true;
                }
                // 2016/09/05 S21_NA#6523 Add End
                boolean isError = false;
                if (!isAlreadyPricing) {
                    isError = true;
                }
//                if (needRePricing(bizMsg, glblMsg)) { 2016/09/05 S21_NA#6523 Del
                if (isNeedPricing) { // 2016/09/05 S21_NA#6523 Add
                    isError = true;
                }
                // S21_NA#6442 Add Start
                if (CPO_SRC_TP.CHANGE_ORDER_MODIFICATION.equals(bizMsg.cpoSrcTpCd.getValue())) {
                    isError = false;
                }
                // S21_NA#6442 Add End

                if (isError) {
                    bizMsg.ordEntryActCd_AC.setErrorInfo(1, NWAM0673E, new String[] {"Pricing" });
                    bizMsg.setMessageInfo(NWAM0673E, new String[] {"Pricing" });
                    return true;
                }
            }
            // 2016/03/30 S21_NA#5523 Mod End
        }

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) { // 2018/01/31 S21_NA#19808 Add Mod
            if (ORD_LINE_STS.CLOSED.equals(glblMsg.B.no(i).ordLineStsCd_LL.getValue()) // QC#22919 // 2018/01/31 S21_NA#19808 Add Mod
                    || ORD_LINE_STS.CANCELLED.equals(glblMsg.B.no(i).ordLineStsCd_LL.getValue())) { // QC#1620 // 2018/01/31 S21_NA#19808 Add Mod
                continue;
            }
            // 16. Lease Buy Out
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i); // 2018/01/31 S21_NA#19808 Add Mod
            // 2017/08/03 S21_NA#20052 Mod Start
//            if (!isSave && DS_ORD_LINE_CATG.LEASE_BUYOUT.equals(bcMsg.dsOrdLineCatgCd_LL.getValue())) {
            if (DS_ORD_LINE_CATG.LEASE_BUYOUT.equals(lineMsg.dsOrdLineCatgCd_LL.getValue())) { // 2018/01/31 S21_NA#19808 Add Mod
                // 2017/08/03 S21_NA#20052 Mod End
                // 2019/10/04 S21_NA#51372 Mod Start
                //if (!ZYPCommonFunc.hasValue(lineMsg.splyNm_LL)) { // 2018/01/31 S21_NA#19808 Add Mod
                if (!ZYPCommonFunc.hasValue(lineMsg.prntVndNm_LL)) { // 2018/01/31 S21_NA#19808 Add Mod
                // 2019/10/04 S21_NA#51372 Mod End
                    lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0671E, new String [] {"Lease Buyout Information"}); // 2018/01/31 S21_NA#19808 Add Mod
                    rslt = true;
                }
            }
            // 2016/11/08 S21_NA#15427 Add Start
            // 2017/10/24 S21_NA#21773 Mod Start
//            if (!isAvalLineCategory(bizMsg, bcMsg)) { // 2016/11/10 S21_NA#15427-2 I/F Change
            if (!ZYPCommonFunc.hasValue(lineMsg.crRebilCd_LL) // 2018/01/31 S21_NA#19808 Add Mod
                    && !isAvalLineCategory(bizMsg, lineMsg)) { // 2016/11/10 S21_NA#15427-2 I/F Change // 2018/01/31 S21_NA#19808 Add Mod
            // 2017/10/24 S21_NA#21773 Mod End
                lineMsg.dsOrdLineCatgCd_LL.setErrorInfo(1, NWAM0909E); // 2018/01/31 S21_NA#19808 Add Mod
                rslt = true;
            }
            // 2016/11/08 S21_NA#15427 Add End
            // 2016/11/09 S21_NA#9864 Add Start
            if (!isAvalLoanShipment(bizMsg, lineMsg)) { // 2018/01/31 S21_NA#19808 Add Mod
                lineMsg.dsOrdLineCatgCd_LL.setErrorInfo(1, NWAM0913E);
                rslt = true;
            }
            // 2016/11/09 S21_NA#9864 Add End
            // 2019/11/21 S21_NA#54202 Del Start
//            // 2016/11/30 S21_NA#16098 Add Start
//            if (ZYPCommonFunc.hasValue(lineMsg.sbstMdseCd_LL)) {// 2018/01/31 S21_NA#19808 Add Mod
//                // 2017/06/14 S21_NA#18623 Add Start
//                String curTabNm = bizMsg.xxDplyTab.getValue();
//                bizMsg.xxDplyTab.setValue(NWAL1500Constant.TAB_LINE_CONFIG);
//                // 2017/06/14 S21_NA#18623 Add End
//                // 2017/06/14 S21_NA#18623 Mod Start
////                int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg, bizMsg.xxCellIdx.getValueInt());
//                int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, i); // 2018/01/31 S21_NA#19808 Mod
//                // 2017/06/14 S21_NA#18623 Mod End
//                if (NWAL1500CommonLogicForLineConfig.isChangeMdlAndShellReln(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex))) { // 2018/01/31 S21_NA#19808 Mod
//                    lineMsg.sbstMdseCd_LL.setErrorInfo(1, NWAM0910E); // 2018/01/31 S21_NA#19808 Add Mod
//                    rslt = true;
//                }
//                // 2016/11/30 S21_NA#16098 Add Start
//                bizMsg.xxDplyTab.setValue(curTabNm);
//                // 2016/11/30 S21_NA#16098 Add End
//            }
            // 2019/11/21 S21_NA#54202 Del End
            // 2018/03/20 S21_NA#24842 Add Start
            NWAL1500_ASMsg configMsg = NWAL1500CommonLogic.getParentConfig(glblMsg, lineMsg);
            boolean isError = NWAL1500CommonLogicForLineConfig.isAddItemHasErrorWithModel(bizMsg, configMsg, lineMsg);
            if (isError) {
                lineMsg.mdseCd_LL.setErrorInfo(1, NWAM0952E, new String[] {configMsg.mdlNm_LC.getValue()});
                rslt = true;
            }
            // 2018/03/20 S21_NA#24842 Add End
            // 2016/11/30 S21_NA#16098 Add End
            if (!ZYPCommonFunc.hasValue(lineMsg.serNum_LL)) { // 2018/01/31 S21_NA#19808 Add Mod
                continue;
            }
            // QC#55986 2020/03/03 Mod Start
            // String serMdse = S21StringUtil.concatStrings(lineMsg.serNum_LL.getValue(), ",", lineMsg.mdseCd_LL.getValue()); // QC#22919 // 2018/01/31 S21_NA#19808 Add Mod
            String serMdse = S21StringUtil.concatStrings(lineMsg.serNum_LL.getValue(), ",", convertMdseCd(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue())); // QC#22919 // 2018/01/31 S21_NA#19808 Add Mod
            // QC#55986 2020/03/03 Mod End
            // 5. Serial # Check (Outboud)
            for (int j = i + 1; j < glblMsg.B.getValidCount(); j++) { // 2018/01/31 S21_NA#19808 Mod
                NWAL1500_BSMsg compBcMsg = glblMsg.B.no(j); // 2018/01/31 S21_NA#19808 Mod
                if (!ZYPCommonFunc.hasValue(compBcMsg.serNum_LL)) {
                    continue;
                }
                // QC#22919
                if (ORD_LINE_STS.CANCELLED.equals(compBcMsg.ordLineStsCd_LL.getValue()) //
                        || ORD_LINE_STS.CLOSED.equals(compBcMsg.ordLineStsCd_LL.getValue())) {
                    continue;
                }
                // QC#55986 2020/03/03 Mod Start
                // String compSerMdse = S21StringUtil.concatStrings(compBcMsg.serNum_LL.getValue(), ",", compBcMsg.mdseCd_LL.getValue());
                String compSerMdse = S21StringUtil.concatStrings(compBcMsg.serNum_LL.getValue(), ",", convertMdseCd(bizMsg.glblCmpyCd.getValue(), compBcMsg.mdseCd_LL.getValue()));
                // QC#55986 2020/03/03 Mod End
                if (serMdse.equals(compSerMdse)) {
                    lineMsg.serNum_LL.setErrorInfo(1, NWAM0669E, new String[]{lineMsg.xxLineNum_LL.getValue(), compBcMsg.xxLineNum_LL.getValue()}); // 2018/01/31 S21_NA#19808 Add Mod
                    compBcMsg.serNum_LL.setErrorInfo(1, NWAM0669E, new String[]{lineMsg.xxLineNum_LL.getValue(), compBcMsg.xxLineNum_LL.getValue()}); // 2018/01/31 S21_NA#19808 Add Mod
                    rslt = true;
                    break;
                }
            }
            // QC#55986 2020/03/03 Del Start
            // 5. Serial # Check (Inboud)
            //for (int j = i; j < glblMsg.D.getValidCount(); j++) {
            //    NWAL1500_DSMsg compDcMsg = glblMsg.D.no(j);
            //    if (!ZYPCommonFunc.hasValue(compDcMsg.serNum_RL)) {
            //        continue;
            //    }
            //    // QC#22919
            //    if (RTRN_LINE_STS.CANCELLED.equals(compDcMsg.ordLineStsCd_RL.getValue()) //
            //            || RTRN_LINE_STS.CLOSED.equals(compDcMsg.ordLineStsCd_RL.getValue())) {
            //        continue;
            //    }
            //    String compSerMdse = S21StringUtil.concatStrings(compDcMsg.serNum_RL.getValue(), ",", compDcMsg.mdseCd_RL.getValue());
            //    if (serMdse.equals(compSerMdse)) {
            //        lineMsg.serNum_LL.setErrorInfo(1, NWAM0669E, new String[]{"Config:" + lineMsg.xxLineNum_LL.getValue(), "RMA:" + compDcMsg.xxLineNum_RL.getValue()});
            //        if (RTRN_LINE_STS.ENTERED.equals(compDcMsg.ordLineStsCd_RL.getValue())) {
            //            compDcMsg.serNum_RL.setErrorInfo(1, NWAM0669E, new String[] {lineMsg.xxLineNum_LL.getValue(), compDcMsg.xxLineNum_RL.getValue() }); // 2018/01/31 S21_NA#19808 Add Mod
            //        } else {
            //            bizMsg.setMessageInfo(NWAM0669E, new String[] {"Config:" + lineMsg.xxLineNum_LL.getValue(), "RMA:" + compDcMsg.xxLineNum_RL.getValue() }); // 2018/01/31 S21_NA#19808 Add Mod
            //        }
            //        rslt = true;
            //        break;
            //    }
            //}
            // QC#55986 2020/03/03 Del End
        }
        return rslt;
    }

    /**
     * Relation check for RMA Tab
     * @param bizMsg Business Message
     * @return true: have error false: no error
     */
    public boolean isRelationErrOnRMA(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/31 S21_NA#19808 Add Mod
        // this.itemNameList.setBizMsg(bizMsg); // For Performance QC#8166 Del
        boolean rslt = false;
        // 5. Serirl # Check
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) { // 2018/01/31 S21_NA#19808 Add Mod
            NWAL1500_DSMsg lineMsg = glblMsg.D.no(i); // 2018/01/31 S21_NA#19808 Add Mod
            // 2016/11/08 S21_NA#15427 Add Start
            if (!isAvalLineCategory(bizMsg, lineMsg)) { // 2016/11/10 S21_NA#15427-2 I/F Change
                lineMsg.dsOrdLineCatgCd_RL.setErrorInfo(1, NWAM0909E); // 2018/01/31 S21_NA#19808 Add Mod
                rslt = true;
            }
            // 2016/11/08 S21_NA#15427 Add End
            if (!ZYPCommonFunc.hasValue(lineMsg.serNum_RL)) { // 2018/01/31 S21_NA#19808 Add Mod
                continue;
            }
            // QC#22919
            if (RTRN_LINE_STS.CANCELLED.equals(lineMsg.ordLineStsCd_RL.getValue()) // 2018/01/31 S21_NA#19808 Add Mod
                    || RTRN_LINE_STS.CLOSED.equals(lineMsg.ordLineStsCd_RL.getValue())) { // 2018/01/31 S21_NA#19808 Add Mod
                continue;
            }
            // QC#55986 2020/03/03 Mod Start
            // String serMdse = S21StringUtil.concatStrings(lineMsg.serNum_RL.getValue(), ",", lineMsg.mdseCd_RL.getValue()); // QC#22919 // 2018/01/31 S21_NA#19808 Add Mod
            String serMdse = S21StringUtil.concatStrings(lineMsg.serNum_RL.getValue(), ",", convertMdseCd(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_RL.getValue())); // QC#22919 // 2018/01/31 S21_NA#19808 Add Mod
            // QC#55986 2020/03/03 Mod End
            // QC#55986 2020/03/03 Del Start
            // 2016/03/17 S21_NA#5355 Add start
            //for (int j = i + 1; j < glblMsg.B.getValidCount(); j++) {
            //    NWAL1500_BSMsg compDcMsg = glblMsg.B.no(j); // 2018/01/31 S21_NA#19808 Add Mod
            //    if (!ZYPCommonFunc.hasValue(compDcMsg.serNum_LL)) {
            //        continue;
            //    }
            //    // QC#22919
            //    if (ORD_LINE_STS.CANCELLED.equals(compDcMsg.ordLineStsCd_LL.getValue()) //
            //            || ORD_LINE_STS.CLOSED.equals(compDcMsg.ordLineStsCd_LL.getValue())) {
            //        continue;
            //    }
            //    String compSerMdse = S21StringUtil.concatStrings(compDcMsg.serNum_LL.getValue(),",",compDcMsg.mdseCd_LL.getValue());
            //    if (serMdse.equals(compSerMdse)) {
            //        compDcMsg.serNum_LL.setErrorInfo(1, NWAM0669E, new String[]{"RMA:" + lineMsg.xxLineNum_RL.getValue(), "Line:" + compDcMsg.xxLineNum_LL.getValue()});
            //        if (RTRN_LINE_STS.ENTERED.equals(lineMsg.ordLineStsCd_RL.getValue())) { // 2018/01/31 S21_NA#19808 Add Mod
            //            lineMsg.serNum_RL.setErrorInfo(1, NWAM0669E, new String[]{lineMsg.xxLineNum_RL.getValue(), compDcMsg.xxLineNum_LL.getValue()}); // 2018/01/31 S21_NA#19808 Add Mod
            //        } else {
            //            bizMsg.setMessageInfo(NWAM0669E, new String[] {"RMA:" + lineMsg.xxLineNum_RL.getValue(), "Line:" + compDcMsg.xxLineNum_LL.getValue() }); // 2018/01/31 S21_NA#19808 Add Mod
            //        }
            //        rslt = true;
            //        break;
            //    }
            //}
            // 2016/03/17 S21_NA#5355 Add End
            // QC#55986 2020/03/03 Del End
            for (int j = i + 1; j < glblMsg.D.getValidCount(); j++) { // 2018/01/31 S21_NA#19808 Add Mod
                NWAL1500_DSMsg compDcMsg = glblMsg.D.no(j); // 2018/01/31 S21_NA#19808 Add Mod
                if (!ZYPCommonFunc.hasValue(compDcMsg.serNum_RL)) {
                    continue;
                }
                // QC#22919
                if (RTRN_LINE_STS.CANCELLED.equals(compDcMsg.ordLineStsCd_RL.getValue()) //
                        || RTRN_LINE_STS.CLOSED.equals(compDcMsg.ordLineStsCd_RL.getValue())) {
                    continue;
                }
                // QC#55986 2020/03/03 Mod Start
                // String compSerMdse = S21StringUtil.concatStrings(compDcMsg.serNum_RL.getValue(), ",", compDcMsg.mdseCd_RL.getValue());
                String compSerMdse = S21StringUtil.concatStrings(compDcMsg.serNum_RL.getValue(), ",", convertMdseCd(bizMsg.glblCmpyCd.getValue(), compDcMsg.mdseCd_RL.getValue()));
                // QC#55986 2020/03/03 Mod End
                if (serMdse.equals(compSerMdse)) {
                    lineMsg.serNum_RL.setErrorInfo(1, NWAM0669E, new String[]{lineMsg.xxLineNum_RL.getValue(), compDcMsg.xxLineNum_RL.getValue()}); // 2018/01/31 S21_NA#19808 Add Mod
                    compDcMsg.serNum_RL.setErrorInfo(1, NWAM0669E, new String[] {"Line Config:" + lineMsg.xxLineNum_RL.getValue(), "RMA:" + compDcMsg.xxLineNum_RL.getValue() }); // 2018/01/31 S21_NA#19808 Add Mod
                    rslt = true;
                    break;
                }
            }
        }

        // Add Start 2018/11/02 QC#29017
        if (isRmaSerialNumberExistingError(bizMsg, glblMsg)) {
            rslt = true;
        }
        // Add End 2018/11/02 QC#29017

        // Del Start 2018/08/21 QC#27489
        //rslt = isRmaMainUnitWithoutAccesoryError(bizMsg, glblMsg); // 2016/07/25 S21_NA#5062, 9789 Add
        // Del End 2018/08/21 QC#27489
        // Add Start 2018/11/02 QC#29017
        if (isRmaMainUnitWithoutAccesoryError(bizMsg, glblMsg)) {
            rslt = true;
        }
        // Add End 2018/11/02 QC#29017

        return rslt;
    }
    // S21_NA#3649 Del Start
//    /**
//     * Set SuperSede Merchandise Code if the line's SuperSede Lock Flag is 'N'.
//     * @param bizMsg Business Message
//     */
//    public void setSuperSessionMdse(NWAL1500CMsg bizMsg) {
//        for (int n = 0; n < bizMsg.B.getValidCount(); n++) {
//            NWAL1500_BCMsg bcMsg = bizMsg.B.no(n);
//            if (ZYPConstant.FLG_ON_Y.equals(bcMsg.supdLockFlg_LL.getValue())
//                    || ZYPCommonFunc.hasValue(bcMsg.sbstMdseCd_LL)
//                    || ZYPCommonFunc.hasValue(bcMsg.ordLineStsDescTxt_LL)) {
//                continue;
//            }
//            // Vendor Drop? Processed WH?
//            List<String> notHardAllocWhCd = NWAL1500CommonLogic.getVarCharConstDataList(bizMsg.glblCmpyCd.getValue(), NOT_ALLOC_WH_CD);
//            List<String> dropShipRtlWhCd = NWAL1500CommonLogic.getVarCharConstDataList(bizMsg.glblCmpyCd.getValue(), DROP_SHIP_RTL_WH_CD);
//
//            boolean isDropShipRtlWh = dropShipRtlWhCd.contains(bcMsg.rtlWhCd_LL.getValue());
//            boolean isNotHardAllocWh = notHardAllocWhCd.contains(bcMsg.rtlWhCd_LL.getValue());
//            if (!isDropShipRtlWh && isNotHardAllocWh) {
//                return;
//            }
//            // Supersede API
//            callSuperSedeApi(bizMsg, bcMsg, isDropShipRtlWh);
//        }
//    }
    // S21_NA#3649 Del End

    public static SVC_CONFIG_MSTRTMsg getSvcConfigData(NWAL1500CMsg bizMsg, BigDecimal svcConfigMstrPk) {
        SVC_CONFIG_MSTRTMsg key = new SVC_CONFIG_MSTRTMsg();
        key.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(key.svcConfigMstrPk, svcConfigMstrPk);
        return (SVC_CONFIG_MSTRTMsg) S21CacheTBLAccessor.findByKey(key);
    }

//    private RTL_WHTMsg getRtlWarehouseData(NWAL1500CMsg bizMsg, String rtlWhCd) {
//        RTL_WHTMsg key = new RTL_WHTMsg();
//        key.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//        key.rtlWhCd.setValue(rtlWhCd);
//        return (RTL_WHTMsg) S21CacheTBLAccessor.findByKey(key);
//    }

//    private RTL_SWHTMsg getRtlSubWarehouseData(NWAL1500CMsg bizMsg, String rtlWhCd, String rtlSwhCd) {
//        RTL_SWHTMsg key = new RTL_SWHTMsg();
//        key.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//        key.rtlWhCd.setValue(rtlWhCd);
//        key.rtlSwhCd.setValue(rtlSwhCd);
//        return (RTL_SWHTMsg) S21CacheTBLAccessor.findByKey(key);
//    }

//    private NMZC610001PMsg eligibleCustomer(NWAL1500CMsg bizMsg) {
//        NMZC610001PMsg custInfoGetApiPMsg = new NMZC610001PMsg();
//
//        custInfoGetApiPMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//        custInfoGetApiPMsg.xxModeCd.setValue(NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.billToCustCd, bizMsg.billToCustCd);
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.shipToCustCd, bizMsg.shipToCustCd);
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsAcctNum_I2, bizMsg.sellToCustCd); // Original Account
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsAcctNum_I1, bizMsg.billToCustAcctCd);
//
//        NMZC610001 custInfoGetApi = new NMZC610001();
//        custInfoGetApi.execute(custInfoGetApiPMsg, ONBATCH_TYPE.ONLINE);
//        return custInfoGetApiPMsg;
//    }

    private List<String> checkApiMessage(EZDPMsg pMsg) {
        List<String> errMsg = new ArrayList<String>();
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            errMsg.add(msg.getXxMsgid());
            String[] msgPrms = msg.getXxMsgPrmArray();
            for (String addlMsg : msgPrms) {
                errMsg.add(addlMsg);
            }
        } else {
            errMsg.clear();
        }
        return errMsg;
    }

//    private String getApiMessageId(List<String> apiErrMsgList) {
//        if (apiErrMsgList.isEmpty()) {
//            return "";
//        }
//        return apiErrMsgList.get(0);
//    }

//    private String [] getApiMessageAddlInfoArray(List<String> apiErrMsgList) {
//        if (apiErrMsgList.size() == 1) {
//            return null;
//        }
//        int listSize = apiErrMsgList.size() - 1;
//        List<String> addlMsgList = new ArrayList<String>(apiErrMsgList.size() - 1);
//        for (int i = 1; i < apiErrMsgList.size(); i++) {
//            addlMsgList.add(apiErrMsgList.get(i));
//        }
//        return addlMsgList.toArray(new String[listSize]);
//
//    }

    private boolean isModifiedHdr(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean allChkFlg) {
        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.cpoOrdNum, glblMsg.cpoOrdNum)) {
            return true;
        }
        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.dsOrdCatgCd, glblMsg.dsOrdCatgCd)) {
            return true;
        }
        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.dsOrdTpCd, glblMsg.dsOrdTpCd)) {
            return true;
        }
        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.sellToCustCd, glblMsg.sellToCustCd)) {
            return true;
        }
        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.negoDealAmt, glblMsg.negoDealAmt)) {
            return true;
        }
        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.slsRepTocCd, glblMsg.slsRepTocCd)) {
            return true;
        }
        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.custIssPoNum, glblMsg.custIssPoNum)) {
            return true;
        }
        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.spclHdlgTpCd, glblMsg.spclHdlgTpCd)) {
            return true;
        }
        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.shpgSvcLvlCd, glblMsg.shpgSvcLvlCd)) {
            return true;
        }
        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.frtCondDescTxt, glblMsg.frtCondDescTxt)) {
            return true;
        }
        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.carrSvcLvlDescTxt, glblMsg.carrSvcLvlDescTxt)) {
            return true;
        }
        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.pmtTermCashDiscDescTxt, glblMsg.pmtTermCashDiscDescTxt)) {
            return true;
        }
        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.prcContrNum, glblMsg.prcContrNum)) {
            return true;
        }
        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.leasePrchOptCd, glblMsg.leasePrchOptCd)) {
            return true;
        }
        if (allChkFlg && ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.dsOrdRsnCd, glblMsg.dsOrdRsnCd)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.ordDt, glblMsg.ordDt)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.billToCustAcctNm, glblMsg.billToCustAcctNm)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.billToCustAcctCd, glblMsg.billToCustAcctCd)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.billToCustCd, glblMsg.billToCustCd)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.shipToCustAcctNm, glblMsg.shipToCustAcctNm)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.shipToCustAcctCd, glblMsg.shipToCustAcctCd)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.shipToCustCd, glblMsg.shipToCustCd)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.soldToCustAcctNm, glblMsg.soldToCustAcctNm)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.soldToCustLocCd, glblMsg.soldToCustLocCd)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.slsRepTocNm, glblMsg.slsRepTocNm)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.prcCatgNm, glblMsg.prcCatgNm)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.leaseCmpyPoNum, glblMsg.leaseCmpyPoNum)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.custIssPoDt, glblMsg.custIssPoDt)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.aquNum, glblMsg.aquNum)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.ordLogTpCd, glblMsg.ordLogTpCd)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.invCmntTxt, glblMsg.invCmntTxt)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.carrAcctNum, glblMsg.carrAcctNum)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.dsPmtMethCd, glblMsg.dsPmtMethCd)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.prePmtChkNum, glblMsg.prePmtChkNum)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.prePmtTpCd, glblMsg.prePmtTpCd)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.prePmtAmt, glblMsg.prePmtAmt)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.addRddDt, glblMsg.addRddDt)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.flPrcListNm, glblMsg.flPrcListNm)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.loanPerDaysAot, glblMsg.loanPerDaysAot)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.csmpContrNum, glblMsg.csmpContrNum)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.dlrRefNum, glblMsg.dlrRefNum)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.ordSgnDt, glblMsg.ordSgnDt)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.leaseTermMthAot, glblMsg.leaseTermMthAot)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.leasePmtFreqCd, glblMsg.leasePmtFreqCd)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.leaseTotPmtAmt, glblMsg.leaseTotPmtAmt)) {
                return true;
            }
            // QC#22789 2017/11/28 Add Start
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.leaseTermMthAot_EM, glblMsg.leaseTermMthAot_EM)) {
                return true;
            }
            // QC#22789 2017/11/28 Add End
            // S21_NA#8388 ADD
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.dclnSvcCd, glblMsg.dclnSvcCd)) {
                return true;
            }
            // S21_NA#12598 Add Start
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.psnNum, glblMsg.psnNum)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.prcContrNm, glblMsg.prcContrNm)) {
                return true;
            }
            // S21_NA#12598 Add End
            // 2016/10/24 S21_NA#12474 Add Start
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.dsCrCardPk, glblMsg.dsCrCardPk)) {
                return true;
            }
            // 2016/10/24 S21_NA#12474 Add End
            // 2017/12/12 S21_NA#20164 Add Start
            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.sellToFirstRefCmntTxt, glblMsg.sellToFirstRefCmntTxt)) {
                return true;
            }
            // 2017/12/12 S21_NA#20164 Add End
        }
        return false;
    }

    private boolean hasListPriceErrors(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg line = glblMsg.B.no(i);
            if (ZYPCommonFunc.hasValue(line.xxErrFlg_LL)) {
                return true;
            }
        }
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg rmaline = glblMsg.D.no(i);
            if (ZYPCommonFunc.hasValue(rmaline.xxErrFlg_RL)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * check if user must do action pricing or not
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @return true: user must do the action pricing, false: needless
     * </pre>
     */
    public boolean needRePricing(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2017/10/13 S21_NA#21267 Add Start
        if (NWAL1500CommonLogic.isOrderCredit(glblMsg)) { // 2018/01/31 S21_NA#19808
            return false;
        }
        // 2017/10/13 S21_NA#21267 Add End
        if (needRePricingForHeader(bizMsg, glblMsg)) {
            return true;
        }
        if (needRePricingForLineConfig(bizMsg, glblMsg)) {
            return true;
        }
        if (needRePricingForRma(bizMsg, glblMsg)) {
            return true;
        }
        if (needRePricingForPriceList(bizMsg, glblMsg)) {
            return true;
        }
        return false;
    }

    private boolean needRePricingForHeader(NWAL1500CMsg source, NWAL1500SMsg target) { // S21_NA#1952

        // Category
        if (!NWAL1500CommonLogic.isEqualsEZDItem(source.dsOrdCatgDescTxt, target.dsOrdCatgDescTxt_D)) {
            return true;
        }
        // Reason Code
        if (!NWAL1500CommonLogic.isEqualsEZDItem(source.dsOrdTpCd, target.dsOrdTpCd_D)) {
            return true;
        }
        // Sold To Number
        if (!NWAL1500CommonLogic.isEqualsEZDItem(source.sellToCustCd, target.sellToCustCd_D)) {
            return true;
        }
        // Negotiated Deal
        if (!NWAL1500CommonLogic.isEqualsEZDItem(source.negoDealAmt, target.negoDealAmt_D)) {
            return true;
        }
        // Salesrep
        if (!NWAL1500CommonLogic.isEqualsEZDItem(source.slsRepTocCd, target.slsRepTocCd_D)) {
            return true;
        }
        // Customer PO
        if (!NWAL1500CommonLogic.isEqualsEZDItem(source.custIssPoNum, target.custIssPoNum_D)) {
            return true;
        }
        // Freight Terms
        if (!NWAL1500CommonLogic.isEqualsEZDItem(source.frtCondDescTxt, target.frtCondDescTxt_D)) {
            return true;
        }
        // 2016/02/23 S21_NA#1859 Del Start
        // // Carrier Service Level
        // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.carrSvcLvlDescTxt, target.carrSvcLvlDescTxt_D)) {
        //     return true;
        // }
        // 2016/02/23 S21_NA#1859 Del End
        // Special Handling
        if (!NWAL1500CommonLogic.isEqualsEZDItem(source.spclHdlgTpCd, target.spclHdlgTpCd_D)) {
            return true;
        }
        // Service Level
        if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shpgSvcLvlCd, target.shpgSvcLvlCd_D)) {
            return true;
        }
        // 2016/02/23 S21_NA#1859 Del Start
        // // Payment Terms
        // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.pmtTermCashDiscDescTxt, target.pmtTermCashDiscDescTxt_D)) {
        //     return true;
        // }
        // 2016/02/23 S21_NA#1859 Del End
        // Association Program
        if (!NWAL1500CommonLogic.isEqualsEZDItem(source.prcContrNum, target.prcContrNum_D)) {
            return true;
        }
        // 2016/02/23 S21_NA#1859 Del Start
        // // End of Term Purchase Option
        // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.leasePrchOptCd, target.leasePrchOptCd_D)) {
        //     return true;
        // }
        // 2016/02/23 S21_NA#1859 Del Start

        // 2016/02/23 S21_NA#1859 Add Start
        // Lease Term Code
        if (!NWAL1500CommonLogic.isEqualsEZDItem(source.leaseTermMthAot, target.leaseTermMthAot_D)) {
            return true;
        }
        // Lease Payment Frequency Code
        if (!NWAL1500CommonLogic.isEqualsEZDItem(source.leasePmtFreqCd, target.leasePmtFreqCd_D)) {
            return true;
        }
        // 2016/02/23 S21_NA#1859 Add End
        // QC#22789 2017/11/28 Add Start
        if (!NWAL1500CommonLogic.isEqualsEZDItem(source.leaseTermMthAot_EM, target.leaseTermMthAot_DE)) {
            return true;
        }
        // QC#22789 2017/11/28 Add End

        return false;
    }

    private boolean needRePricingForLineConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // S21_NA#1952

        // 2018/01/31 S21_NA#19808 bizMsg.A, B => glblMsg.A, B
        if (glblMsg.A.getValidCount() != glblMsg.T.getValidCount()) { // S21_NA#19808
            return true;
        }
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg source = glblMsg.A.no(i);
            NWAL1500_TSMsg target = glblMsg.T.no(i);
            // 2016/02/23 S21_NA#1859 Del Start
            // // Config ID
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.svcConfigMstrPk_LC, target.svcConfigMstrPk_LC)) {
            //     return true;
            // }
            // 2016/02/23 S21_NA#1859 Del End
            // 2016/02/23 S21_NA#1859 Mod Start
            // Model
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.mdlDescTxt_LC, target.mdlDescTxt_LC)) {
            //     return true;
            // }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.mdlId_LC, target.mdlId_LC)) {
                return true;
            }
            // 2016/02/23 S21_NA#1859 Mod End
            // Ship To
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToCustCd_LC, target.shipToCustCd_LC)) {
                return true;
            }
            // Ship To Account Code
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToCustAcctCd_LC, target.shipToCustAcctCd_LC)) {
                return true;
            }
            // 2016/02/23 S21_NA#1859 Del Start
            // // DS information
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToLocNm_LC, target.shipToLocNm_LC)) {
            //     return true;
            // }
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToAddlLocNm_LC, target.shipToAddlLocNm_LC)) {
            //     return true;
            // }
            // 2016/02/23 S21_NA#1859 Del End
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToFirstLineAddr_LC, target.shipToFirstLineAddr_LC)) {
                return true;
            }
            // 2016/02/23 S21_NA#1859 Del Start
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToScdLineAddr_LC, target.shipToScdLineAddr_LC)) {
            //     return true;
            // }
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToThirdLineAddr_LC, target.shipToThirdLineAddr_LC)) {
            //     return true;
            // }
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToFrthLineAddr_LC, target.shipToFrthLineAddr_LC)) {
            //     return true;
            // }
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToFirstRefCmntTxt_LC, target.shipToFirstRefCmntTxt_LC)) {
            //     return true;
            // }
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToScdRefCmntTxt_LC, target.shipToScdRefCmntTxt_LC)) {
            //     return true;
            // }
            // 2016/02/23 S21_NA#1859 Del End
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToPostCd_LC, target.shipToPostCd_LC)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToCtyAddr_LC, target.shipToCtyAddr_LC)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToStCd_LC, target.shipToStCd_LC)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToProvNm_LC, target.shipToProvNm_LC)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToCtryCd_LC, target.shipToCtryCd_LC)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToCntyNm_LC, target.shipToCntyNm_LC)) {
                return true;
            }
            // Bill To
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.billToCustCd_LC, target.billToCustCd_LC)) {
                return true;
            }
            // Bill To Account Code
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.billToCustAcctCd_LC, target.billToCustAcctCd_LC)) {
                return true;
            }
        }

        if (glblMsg.B.getValidCount() != glblMsg.U.getValidCount()) {
            return true;
        }
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg source = glblMsg.B.no(i);
            if (ZYPCommonFunc.hasValue(source.dsCpoLineSubNum_LL)) { // S21_NA#2522
                continue;
            }
            NWAL1500_USMsg target = glblMsg.U.no(i);
            // Item#
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.mdseCd_LL, target.mdseCd_LL)) {
                return true;
            }
            // Qty
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.ordCustUomQty_LL, target.ordCustUomQty_LL)) {
                return true;
            }
            // UOM
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.custUomCd_LL, target.custUomCd_LL)) {
                return true;
            }
            // Sell Price
//          if (!NWAL1500CommonLogic.isEqualsEZDItem(source.cpoDtlDealGrsAmt_LL, target.cpoDtlDealGrsAmt_LL)) {
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.entCpoDtlDealSlsAmt_LL, target.entCpoDtlDealSlsAmt_LL)) { // S21_NA#2257
                return true;
            }
            // Sell Price List
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.prcCatgNm_LL, target.prcCatgNm_LL)) {
                return true;
            }
            // Line Category
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.dsOrdLineCatgDescTxt_LL, target.dsOrdLineCatgDescTxt_LL)) {
                return true;
            }
            // Warehous
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.rtlWhNm_LL, target.rtlWhNm_LL)) {
                return true;
            }
            // Sub Warehous
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.rtlSwhNm_LL, target.rtlSwhNm_LL)) {
                return true;
            }
            // CSMP Number
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.csmpContrNum_LL, target.csmpContrNum_LL)) {
                return true;
            }
            // CSA Number(Dealer Ref#)
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.dlrRefNum_LL, target.dlrRefNum_LL)) {
                return true;
            }
            // CSMP Price List
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.csmpPrcListCd_LL, target.csmpPrcListCd_LL)) {
                return true;
            }
        }
        return false;
    }

    private boolean needRePricingForRma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // S21_NA#1952
        // 2018/01/31 S21_NA#19808 bizMsg.C, D => glblMsg.C, D
        if (glblMsg.C.getValidCount() != glblMsg.V.getValidCount()) {
            return true;
        }
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL1500_CSMsg source = glblMsg.C.no(i);
            NWAL1500_VSMsg target = glblMsg.V.no(i);
            // 2016/02/23 S21_NA#1859 Del Start
            // // Config ID
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.svcConfigMstrPk_RC, target.svcConfigMstrPk_RC)) {
            //     return true;
            // }
            // 2016/02/23 S21_NA#1859 Del End
            // Model
            // 2016/02/23 S21_NA#1859 Mod Start
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.mdlDescTxt_RC, target.mdlDescTxt_RC)) {
            //     return true;
            // }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.mdlId_RC, target.mdlId_RC)) {
                return true;
            }
            // 2016/02/23 S21_NA#1859 Mod End
            // Ship To
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToCustCd_RC, target.shipToCustCd_RC)) {
                return true;
            }
            // Ship To Account Code
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToCustAcctCd_RC, target.shipToCustAcctCd_RC)) {
                return true;
            }
            // 2016/02/23 S21_NA#1859 Del Start
            // DS information
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToLocNm_RC, target.shipToLocNm_RC)) {
            //     return true;
            // }
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToAddlLocNm_RC, target.shipToAddlLocNm_RC)) {
            //     return true;
            // }
            // 2016/02/23 S21_NA#1859 Del End
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToFirstLineAddr_RC, target.shipToFirstLineAddr_RC)) {
                return true;
            }
            // 2016/02/23 S21_NA#1859 Del Start
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToScdLineAddr_RC, target.shipToScdLineAddr_RC)) {
            //     return true;
            // }
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToThirdLineAddr_RC, target.shipToThirdLineAddr_RC)) {
            //     return true;
            // }
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToFrthLineAddr_RC, target.shipToFrthLineAddr_RC)) {
            //     return true;
            // }
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToFirstRefCmntTxt_RC, target.shipToFirstRefCmntTxt_RC)) {
            //     return true;
            // }
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToScdRefCmntTxt_RC, target.shipToScdRefCmntTxt_RC)) {
            //     return true;
            // }
            // 2016/02/23 S21_NA#1859 Del End
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToPostCd_RC, target.shipToPostCd_RC)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToCtyAddr_RC, target.shipToCtyAddr_RC)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToStCd_RC, target.shipToStCd_RC)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToProvNm_RC, target.shipToProvNm_RC)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToCtryCd_RC, target.shipToCtryCd_RC)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.shipToCntyNm_RC, target.shipToCntyNm_RC)) {
                return true;
            }
            // Bill To
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.billToCustCd_RC, target.billToCustCd_RC)) {
                return true;
            }
            // Bill To Account Code
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.billToCustAcctCd_RC, target.billToCustAcctCd_RC)) {
                return true;
            }
        }
        if (glblMsg.D.getValidCount() != glblMsg.W.getValidCount()) {
            return true;
        }
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg source = glblMsg.D.no(i);
            if (ZYPCommonFunc.hasValue(source.dsCpoLineSubNum_RL)) { // S21_NA#2522
                continue;
            }
            NWAL1500_WSMsg target = glblMsg.W.no(i);
            // Item#
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.mdseCd_RL, target.mdseCd_RL)) {
                return true;
            }
            // Qty
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.ordCustUomQty_RL, target.ordCustUomQty_RL)) {
                return true;
            }
            // UOM
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.custUomCd_RL, target.custUomCd_RL)) {
                return true;
            }
            // Sell Price
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.entCpoDtlDealSlsAmt_RL, target.entCpoDtlDealSlsAmt_RL)) { // S21_NA#2257
                return true;
            }
            // Sell Price List
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.prcCatgNm_RL, target.prcCatgNm_RL)) {
                return true;
            }
            // Line Category
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.dsOrdLineCatgDescTxt_RL, target.dsOrdLineCatgDescTxt_RL)) {
                return true;
            }
            // Warehous
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.rtlWhNm_RL, target.rtlWhNm_RL)) {
                return true;
            }
            // Sub Warehous
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.rtlSwhNm_RL, target.rtlSwhNm_RL)) {
                return true;
            }
            // CSMP Number
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.csmpContrNum_RL, target.csmpContrNum_RL)) {
                return true;
            }
            // CSA Number(Dealer Ref#)
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.dlrRefNum_RL, target.dlrRefNum_RL)) {
                return true;
            }
            // CSMP Price List
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.csmpPrcListCd_RL, target.csmpPrcListCd_RL)) {
                return true;
            }
            // 2016/02/23 S21_NA#1859 Add Start
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.rtrnRsnCd_RL, target.rtrnRsnCd_RL)) {
                return true;
            }
            // 2016/02/23 S21_NA#1859 Add End
        }
        return false;
    }

    private boolean needRePricingForPriceList(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (glblMsg.I.getValidCount() != glblMsg.X.getValidCount()) {
            return true;
        }
        for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
            NWAL1500_ISMsg source = glblMsg.I.no(i);
            NWAL1500_XSMsg target = glblMsg.X.no(i);
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.ordPrcCalcBasePk_LP, target.ordPrcCalcBasePk_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.cpoOrdNum_LP, target.cpoOrdNum_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.cpoDtlLineNum_LP, target.cpoDtlLineNum_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.cpoDtlLineSubNum_LP, target.cpoDtlLineSubNum_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.configCatgCd_LP, target.configCatgCd_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.prcCondTpCd_LP, target.prcCondTpCd_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.prcDtlGrpCd_LP, target.prcDtlGrpCd_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.prcJrnlGrpCd_LP, target.prcJrnlGrpCd_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.prcCatgCd_LP, target.prcCatgCd_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.pkgUomCd_LP, target.pkgUomCd_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.prcCondUnitCd_LP, target.prcCondUnitCd_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.prcCalcMethCd_LP, target.prcCalcMethCd_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.dsMdsePrcPk_LP, target.dsMdsePrcPk_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.specCondPrcPk_LP, target.specCondPrcPk_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.frtPerWtPk_LP, target.frtPerWtPk_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.prcCondManEntryFlg_LP, target.prcCondManEntryFlg_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.prcCondManAddFlg_LP, target.prcCondManAddFlg_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.prcCondManDelFlg_LP, target.prcCondManDelFlg_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.autoPrcAmtRate_LP, target.autoPrcAmtRate_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.maxPrcAmtRate_LP, target.maxPrcAmtRate_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.minPrcAmtRate_LP, target.minPrcAmtRate_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.manPrcAmtRate_LP, target.manPrcAmtRate_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.calcPrcAmtRate_LP, target.calcPrcAmtRate_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.unitPrcAmt_LP, target.unitPrcAmt_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.coaAcctCd_LP, target.coaAcctCd_LP)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(source.autoPrcCcyCd_LP, target.autoPrcCcyCd_LP)) {
                return true;
            }
            // 2016/02/23 S21_NA#1859 Del Start
            // if (!NWAL1500CommonLogic.isEqualsEZDItem(source.prcCondTpDescTxt_LP, target.prcCondTpDescTxt_LP)) {
            //     return true;
            // }
            // 2016/02/23 S21_NA#1859 Del End
        }
        return false;
    }

    // S21_NA#1952
    // private boolean isModifiedDtl(NWAL1500CMsg bizMsg, NWAL1500SMsg
    // glblMsg) {
    // return isModifiedDtl(bizMsg, glblMsg, false);
    // } 
    // QC#58110 Add Param NWZC150001PMsg
    private boolean isModifiedDtl(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean allChkFlg, NWZC150001PMsg cpoUpdApiReqPMsg) {

        // 2018/08/09 S21_NA#26912 Add Start
        copyCsmpData(glblMsg);
        // 2018/08/09 S21_NA#26912 Add End
        // 2018/01/31 S21_NA#19808 Del Start
//        if (!allChkFlg && ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
//            if (bizMsg.A.getValidCount() != glblMsg.A.getValidCount()
//                    || bizMsg.B.getValidCount() != glblMsg.B.getValidCount()
//                    || bizMsg.C.getValidCount() != glblMsg.C.getValidCount()
//                    || bizMsg.D.getValidCount() != glblMsg.D.getValidCount()) {
//                return true;
//            }
//        }
        // 2018/01/31 S21_NA#19808 Del End
        // QC#58110 Mod Start
        if (isModifiedConfig(glblMsg, allChkFlg)) { // 2018/01/31 S21_NA#19808 Mod
            ZYPEZDItemValueSetter.setValue(cpoUpdApiReqPMsg.xxValUpdFlg_I, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(cpoUpdApiReqPMsg.xxValUpdFlg_I, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_OFF_N.equals(cpoUpdApiReqPMsg.xxValUpdFlg_I.getValue()) && isModifiedLine(glblMsg, allChkFlg)) { // 2018/01/31 S21_NA#19808 Mod
            ZYPEZDItemValueSetter.setValue(cpoUpdApiReqPMsg.xxValUpdFlg_I, ZYPConstant.FLG_ON_Y);
        // QC#60932 Mod
        } 
//        else {
//            ZYPEZDItemValueSetter.setValue(cpoUpdApiReqPMsg.xxValUpdFlg_I, ZYPConstant.FLG_OFF_N);
//        }
        if (isModifiedRmaConfig(glblMsg, allChkFlg)) { // 2018/01/31 S21_NA#19808 Mod
            ZYPEZDItemValueSetter.setValue(cpoUpdApiReqPMsg.xxValUpdFlg_O, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(cpoUpdApiReqPMsg.xxValUpdFlg_O, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_OFF_N.equals(cpoUpdApiReqPMsg.xxValUpdFlg_O.getValue()) && isModifiedRmaLine(glblMsg, allChkFlg)) { // 2018/01/31 S21_NA#19808 Mod
            ZYPEZDItemValueSetter.setValue(cpoUpdApiReqPMsg.xxValUpdFlg_O, ZYPConstant.FLG_ON_Y);
        // QC#60932 Mod
        }
//        else {
//            ZYPEZDItemValueSetter.setValue(cpoUpdApiReqPMsg.xxValUpdFlg_O, ZYPConstant.FLG_OFF_N);
//        }

        if (ZYPConstant.FLG_ON_Y.equals(cpoUpdApiReqPMsg.xxValUpdFlg_I.getValue()) || ZYPConstant.FLG_ON_Y.equals(cpoUpdApiReqPMsg.xxValUpdFlg_O.getValue())) {
            return true;
        }
        // QC#58110 Mod End
        // Start: Check difference between Price Calc Base
        // 2018/03/15 S21_NA#19808 Del Start
//        if (glblMsg.I.getValidCount() != glblMsg.I.getValidCount()) {
//            return true;
//        }
//        if (glblMsg.I.getValidCount() == 0 || glblMsg.I.getValidCount() == 0) {
//            return true;
//        }
        // 2018/03/15 S21_NA#19808 Del End
        // 2018/03/15 S21_NA#19808 Add Start
        if (glblMsg.xxDtlCnt_LP.getValueInt() != glblMsg.I.getValidCount()) {
            return true;
        }
        // 2018/03/15 S21_NA#19808 Add End
        if (!compPriceElementsList(glblMsg.I)) { // 2018/03/15 S21_NA#19808 Delete bizMsg.I
            return true;
        }
        // End: Check difference between Price Calc Base
        return false;
    }
    // 2018/01/31 S21_NA#19808 Del Start
//    private boolean isModifiedConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean allChkFlg) {
//
//        // line Config: Config Check
//        // 2016/03/30 S21_NA#5326 Add Start
//        if (bizMsg.A.getValidCount() != glblMsg.A.getValidCount()) {
//            return true;
//        }
//        // 2016/03/30 S21_NA#5326 Add End
//        for (int i = 0; i < bizMsg.A.getValidCount() && i < glblMsg.A.getValidCount(); i++) {
//            NWAL1500_ACMsg acMsg = bizMsg.A.no(i);
//            String dsOrdPosNum = acMsg.dsOrdPosnNum_LC.getValue();
//            for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
//                NWAL1500_ASMsg asMsg = glblMsg.A.no(j);
//                if (!dsOrdPosNum.equals(asMsg.dsOrdPosnNum_LC.getValue())) {
//                    continue;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.svcConfigMstrPk_LC, asMsg.svcConfigMstrPk_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.mdlId_LC, asMsg.mdlId_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToCustAcctCd_LC, asMsg.shipToCustAcctCd_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToCustCd_LC, asMsg.shipToCustCd_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToCustAcctNm_LC, asMsg.shipToCustAcctNm_LC)) {
//                    return true;
//                }
//                // Ship To DS information Start ->
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.dropShipFlg_LC, asMsg.dropShipFlg_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToLocNm_LC, asMsg.shipToLocNm_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToAddlLocNm_LC, asMsg.shipToAddlLocNm_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToFirstLineAddr_LC, asMsg.shipToFirstLineAddr_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToScdLineAddr_LC, asMsg.shipToScdLineAddr_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToThirdLineAddr_LC, asMsg.shipToThirdLineAddr_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToFrthLineAddr_LC, asMsg.shipToFrthLineAddr_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToFirstRefCmntTxt_LC, asMsg.shipToFirstRefCmntTxt_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToScdRefCmntTxt_LC, asMsg.shipToScdRefCmntTxt_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToPostCd_LC, asMsg.shipToPostCd_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToCtyAddr_LC, asMsg.shipToCtyAddr_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToStCd_LC, asMsg.shipToStCd_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToProvNm_LC, asMsg.shipToProvNm_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToCtryCd_LC, asMsg.shipToCtryCd_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.shipToCntyNm_LC, asMsg.shipToCntyNm_LC)) {
//                    return true;
//                }
//                // Ship To DS information End <-
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.billToCustCd_LC, asMsg.billToCustCd_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.billToCustAcctCd_LC, asMsg.billToCustAcctCd_LC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.billToCustAcctNm_LC, asMsg.billToCustAcctNm_LC)) {
//                    return true;
//                }
//                if (allChkFlg) {
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.mdlDescTxt_LC, asMsg.mdlDescTxt_LC)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.mdlNm_LC, asMsg.mdlNm_LC)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.pickSvcConfigMstrPk_LC, asMsg.pickSvcConfigMstrPk_LC)) {
//                        return true;
//                    }
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.dclnSvcCd_LC, asMsg.dclnSvcCd_LC)) { // S21_NA#8388 ADD
//                    return true;
//                }
//                // S21_NA#12598 Add Start
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(acMsg.configTpCd_LC, asMsg.configTpCd_LC)) { // S21_NA#8388 ADD
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
    // 2018/01/31 S21_NA#19808 Del Start

    // 2018/01/31 S21_NA#19808 Add Start
    private boolean isModifiedConfig(NWAL1500SMsg glblMsg, boolean allChkFlg) {

        // line Config: Config Check
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg configMsg = glblMsg.A.no(i);
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.svcConfigMstrPk_LC, configMsg.svcConfigMstrPk_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.mdlId_LC, configMsg.mdlId_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToCustAcctCd_LC, configMsg.shipToCustAcctCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToCustCd_LC, configMsg.shipToCustCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToCustAcctNm_LC, configMsg.shipToCustAcctNm_DB)) {
                return true;
            }
            // Ship To DS information Start ->
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.dropShipFlg_LC, configMsg.dropShipFlg_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToLocNm_LC, configMsg.shipToLocNm_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToAddlLocNm_LC, configMsg.shipToAddlLocNm_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToFirstLineAddr_LC, configMsg.shipToFirstLineAddr_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToScdLineAddr_LC, configMsg.shipToScdLineAddr_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToThirdLineAddr_LC, configMsg.shipToThirdLineAddr_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToFrthLineAddr_LC, configMsg.shipToFrthLineAddr_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToFirstRefCmntTxt_LC, configMsg.shipToFirstRefCmntTxt_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToScdRefCmntTxt_LC, configMsg.shipToScdRefCmntTxt_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToPostCd_LC, configMsg.shipToPostCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToCtyAddr_LC, configMsg.shipToCtyAddr_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToStCd_LC, configMsg.shipToStCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToProvNm_LC, configMsg.shipToProvNm_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToCtryCd_LC, configMsg.shipToCtryCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToCntyNm_LC, configMsg.shipToCntyNm_DB)) {
                return true;
            }
            // Ship To DS information End <-
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.billToCustCd_LC, configMsg.billToCustCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.billToCustAcctCd_LC, configMsg.billToCustAcctCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.billToCustAcctNm_LC, configMsg.billToCustAcctNm_DB)) {
                return true;
            }
            if (allChkFlg) {
                if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.mdlDescTxt_LC, configMsg.mdlDescTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.mdlNm_LC, configMsg.mdlNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.pickSvcConfigMstrPk_LC, configMsg.pickSvcConfigMstrPk_DB)) {
                    return true;
                }
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.dclnSvcCd_LC, configMsg.dclnSvcCd_DB)) { // S21_NA#8388 ADD
                return true;
            }
            // S21_NA#12598 Add Start
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.configTpCd_LC, configMsg.configTpCd_DB)) { // S21_NA#8388 ADD
                return true;
            }
        }
        return false;
    }
    // 2018/01/31 S21_NA#19808 Add End

    // 2018/01/31 S21_NA#19808 Del Start
//    private boolean isModifiedLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean allChkFlg) {
//
//        // line config: line check
//        // 2016/03/30 S21_NA#5326 Add Start
//        if (bizMsg.B.getValidCount() != glblMsg.B.getValidCount()) {
//            return true;
//        }
//        // 2016/03/30 S21_NA#5326 Add End
//        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            NWAL1500_BCMsg bcMsg = bizMsg.B.no(i);
//            String xxLineNum = "C";
//            if (ZYPCommonFunc.hasValue(bcMsg.xxLineNum_LL)) {
//                xxLineNum = bcMsg.xxLineNum_LL.getValue();
//            }
//            String cpoDtlLineNum = "C1";
//            if (ZYPCommonFunc.hasValue(bcMsg.cpoDtlLineNum_LL)) {
//                cpoDtlLineNum = bcMsg.cpoDtlLineNum_LL.getValue();
//            }
//            String cpoDtlLineSubNum = "C2";
//            if (ZYPCommonFunc.hasValue(bcMsg.cpoDtlLineSubNum_LL)) {
//                cpoDtlLineSubNum = bcMsg.cpoDtlLineSubNum_LL.getValue();
//            }
//
//            for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
//                NWAL1500_BSMsg bsMsg = glblMsg.B.no(j);
//                String xxLineNumS = "S";
//                if (ZYPCommonFunc.hasValue(bsMsg.xxLineNum_LL.getValue())) {
//                    xxLineNumS = bsMsg.xxLineNum_LL.getValue();
//                }
//                String cpoDtlLineNumS = "S1";
//                if (ZYPCommonFunc.hasValue(bsMsg.cpoDtlLineNum_LL)) {
//                    cpoDtlLineNumS = bsMsg.cpoDtlLineNum_LL.getValue();
//                }
//                String cpoDtlLineSubNumS = "S2";
//                if (ZYPCommonFunc.hasValue(bsMsg.cpoDtlLineSubNum_LL)) {
//                    cpoDtlLineSubNumS = bsMsg.cpoDtlLineSubNum_LL.getValue();
//                }
//
//                boolean isSameLine = xxLineNum.equals(xxLineNumS);
//                isSameLine |= (cpoDtlLineNum.equals(cpoDtlLineNumS) && cpoDtlLineSubNum.equals(cpoDtlLineSubNumS));
//
//                if (!isSameLine) {
//                    continue;
//                }
//
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.mdseCd_LL, bsMsg.mdseCd_LL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.ordCustUomQty_LL, bsMsg.ordCustUomQty_LL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.custUomCd_LL, bsMsg.custUomCd_LL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.ordQty_LL, bsMsg.ordQty_LL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.entCpoDtlDealSlsAmt_LL, bsMsg.entCpoDtlDealSlsAmt_LL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.prcCatgNm_LL, bsMsg.prcCatgNm_LL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dsOrdLineCatgCd_LL, bsMsg.dsOrdLineCatgCd_LL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.rtlWhNm_LL, bsMsg.rtlWhNm_LL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.rtlSwhNm_LL, bsMsg.rtlSwhNm_LL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.csmpContrNum_LL, bsMsg.csmpContrNum_LL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dlrRefNum_LL, bsMsg.dlrRefNum_LL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.csmpPrcListCd_LL, bsMsg.csmpPrcListCd_LL)) {
//                    return true;
//                }
//
//                if (allChkFlg) {
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.mdseNm_LL, bsMsg.mdseNm_LL)) {
//                        return true;
//                    }
//                    // S21_NA#2334 Add Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.mdseDescShortTxt_LL, bsMsg.mdseDescShortTxt_LL)) {
//                        return true;
//                    }
//                    // S21_NA#2334 Add End
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.origMdseCd_LL, bsMsg.origMdseCd_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.entCpoDtlDealSlsAmt_LL, bsMsg.entCpoDtlDealSlsAmt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.entDealNetUnitPrcAmt_LL, bsMsg.entDealNetUnitPrcAmt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.prcCatgNm_LL, bsMsg.prcCatgNm_LL)) { // S21_NA#1952
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.prcListEquipConfigNum_LL, bsMsg.prcListEquipConfigNum_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.ordLineSrcCd_LL, bsMsg.ordLineSrcCd_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.serNum_LL, bsMsg.serNum_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsCheckboxEZDItem(bcMsg.supdLockFlg_LL, bsMsg.supdLockFlg_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.sbstMdseCd_LL, bsMsg.sbstMdseCd_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.vndInvtyLocCd_LL, bsMsg.vndInvtyLocCd_LL)) {
//                        return true;
//                    }
//                    // S21_NA#1952
//                    // if
//                    // (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.flPrcListCd_LL,
//                    // bsMsg.flPrcListCd_LL)) {
//                    //
//                    // }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.flPrcListNm_LL, bsMsg.flPrcListNm_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dealPrcListPrcAmt_LL, bsMsg.dealPrcListPrcAmt_LL)) {
//                        return true;
//                    }
//                    // QC#22372 2018/01/10 Add Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.funcUnitFlPrcAmt_LL, bsMsg.funcUnitFlPrcAmt_LL)) {
//                        return true;
//                    }
//                    // QC#22372 2018/01/10 Add End
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.cpoDtlDealTaxAmt_LL, bsMsg.cpoDtlDealTaxAmt_LL)) {
//                        return true;
//                    }
//                    // S21_NA#12598 Del Start
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.coaMdseTpDescTxt_LL, bsMsg.coaMdseTpDescTxt_LL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.coaMdseTpCd_LL, bsMsg.coaMdseTpCd_LL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.coaProdDescTxt_LL, bsMsg.coaProdDescTxt_LL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.coaProdCd_LL, bsMsg.coaProdCd_LL)) {
////                        return true;
////                    }
//                    // S21_NA#12598 Del End
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dplyLineRefNum_LL, bsMsg.dplyLineRefNum_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.crRebilCd_LL, bsMsg.crRebilCd_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.prcBaseDt_LL, bsMsg.prcBaseDt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.custMdseCd_LL, bsMsg.custMdseCd_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.rddDt_LL, bsMsg.rddDt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.allocQty_LL, bsMsg.allocQty_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.shipQty_LL, bsMsg.shipQty_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.istlQty_LL, bsMsg.istlQty_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.invQty_LL, bsMsg.invQty_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.cancQty_LL, bsMsg.cancQty_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.cpoDtlDealGrsAmt_LL, bsMsg.cpoDtlDealGrsAmt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dsOrdLineCatgDescTxt_LL, bsMsg.dsOrdLineCatgDescTxt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dsOrdPosnNum_LL, bsMsg.dsOrdPosnNum_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dsCpoConfigPk_LL, bsMsg.dsCpoConfigPk_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dsCpoLineNum_LL, bsMsg.dsCpoLineNum_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dsCpoLineSubNum_LL, bsMsg.dsCpoLineSubNum_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.invtyLocCd_LL, bsMsg.invtyLocCd_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.rtlWhNm_LL, bsMsg.rtlWhNm_LL)) { // S21_NA#1952
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.rtlSwhNm_LL, bsMsg.rtlSwhNm_LL)) { // S21_NA#1952
//                      return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.cpoDtlLineNum_LL, bsMsg.cpoDtlLineNum_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.cpoDtlLineSubNum_LL, bsMsg.cpoDtlLineSubNum_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.splyTpCd_LL, bsMsg.splyTpCd_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.splyNm_LL, bsMsg.splyNm_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.splyFirstAddr_LL, bsMsg.splyFirstAddr_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.splyCtyAddr_LL, bsMsg.splyCtyAddr_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.splyStCd_LL, bsMsg.splyStCd_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.splyPostCd_LL, bsMsg.splyPostCd_LL)) {
//                        return true;
//                    }
//                    // S21_NA#12598 Del Start
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.rntlTrmnDt_LL, bsMsg.rntlTrmnDt_LL)) {
////                        return true;
////                    }
//                    // S21_NA#12598 Del End
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.baseCmptFlg_LL, bsMsg.baseCmptFlg_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dsContrNum_LL, bsMsg.dsContrNum_LL)) {
//                        return true;
//                    }
//                    // S21_NA#12598 Del Start
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dsContrSqNum_LL, bsMsg.dsContrSqNum_LL)) {
////                        return true;
////                    }
//                    // S21_NA#12598 Del Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.svcMachMstrPk_LL, bsMsg.svcMachMstrPk_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.bllgAttrbCustAcctCd_LL, bsMsg.bllgAttrbCustAcctCd_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.firstBllgAttrbNm_LL, bsMsg.firstBllgAttrbNm_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.firstBllgAttrbValTxt_LL, bsMsg.firstBllgAttrbValTxt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.scdBllgAttrbNm_LL, bsMsg.scdBllgAttrbNm_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.scdBllgAttrbValTxt_LL, bsMsg.scdBllgAttrbValTxt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.thirdBllgAttrbNm_LL, bsMsg.thirdBllgAttrbNm_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.thirdBllgAttrbValTxt_LL, bsMsg.thirdBllgAttrbValTxt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.frthBllgAttrbNm_LL, bsMsg.frthBllgAttrbNm_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.frthBllgAttrbValTxt_LL, bsMsg.frthBllgAttrbValTxt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.fifthBllgAttrbNm_LL, bsMsg.fifthBllgAttrbNm_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.fifthBllgAttrbValTxt_LL, bsMsg.fifthBllgAttrbValTxt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.sixthBllgAttrbNm_LL, bsMsg.sixthBllgAttrbNm_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.sixthBllgAttrbValTxt_LL, bsMsg.sixthBllgAttrbValTxt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.ordSrcRefLineNum_LL, bsMsg.ordSrcRefLineNum_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.ordSrcRefLineSubNum_LL, bsMsg.ordSrcRefLineSubNum_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.ordUpldRefNum_LL, bsMsg.ordUpldRefNum_LL)) {
//                        return true;
//                    }
//                    // S21_NA#12598 Del Start
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.xxTotBaseAmt_LL, bsMsg.xxTotBaseAmt_LL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.xxTotDiscAmt_LL, bsMsg.xxTotDiscAmt_LL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.xxTotSpclPrcAmt_LL, bsMsg.xxTotSpclPrcAmt_LL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.xxTotNetDiscAmt_LL, bsMsg.xxTotNetDiscAmt_LL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.xxTotNetPrcAmt_LL, bsMsg.xxTotNetPrcAmt_LL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.xxTotFrtAmt_LL, bsMsg.xxTotFrtAmt_LL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.xxTotSpclChrgAmt_LL, bsMsg.xxTotSpclChrgAmt_LL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.xxTotTaxAmt_LL, bsMsg.xxTotTaxAmt_LL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.xxSlsAmt_LL, bsMsg.xxSlsAmt_LL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.xxTotAmt_LL, bsMsg.xxTotAmt_LL)) {
////                        return true;
////                    }
//                    // S21_NA#12598 Del End
//                    // S21_NA#12598 Add Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.lineDealSubTotAmt_LL, bsMsg.lineDealSubTotAmt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.lineDealChrgAmt_LL, bsMsg.lineDealChrgAmt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.lineDealTaxAmt_LL, bsMsg.lineDealTaxAmt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.lineDealTotAmt_LL, bsMsg.lineDealTotAmt_LL)) {
//                        return true;
//                    }
//                    // S21_NA#12598 Del End
//                    // 2017/10/18 S21_NA#21759 Add Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dealSvcRevTrnsfAmt_LL, bsMsg.dealSvcRevTrnsfAmt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dealSvcCostTrnsfAmt_LL, bsMsg.dealSvcCostTrnsfAmt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dealManFlAdjAmt_LL, bsMsg.dealManFlAdjAmt_LL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(bcMsg.dealManRepRevAdjAmt_LL, bsMsg.dealManRepRevAdjAmt_LL)) {
//                        return true;
//                    }
//                    // 2017/10/18 S21_NA#21759 Add End
//                }
//            }
//        }
//        return false;
//    }
    // 2018/01/31 S21_NA#19808 Del Del

    // 2018/01/31 S21_NA#19808 Add Start
    private boolean isModifiedLine(NWAL1500SMsg glblMsg, boolean allChkFlg) {

        // line config: line check
        for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(j);
//            String xxLineNumS = "S";
//            if (ZYPCommonFunc.hasValue(bsMsg.xxLineNum_LL.getValue())) {
//                xxLineNumS = bsMsg.xxLineNum_LL.getValue();
//            }
//            String cpoDtlLineNumS = "S1";
//            if (ZYPCommonFunc.hasValue(bsMsg.cpoDtlLineNum_LL)) {
//                cpoDtlLineNumS = bsMsg.cpoDtlLineNum_LL.getValue();
//            }
//            String cpoDtlLineSubNumS = "S2";
//            if (ZYPCommonFunc.hasValue(bsMsg.cpoDtlLineSubNum_LL)) {
//                cpoDtlLineSubNumS = bsMsg.cpoDtlLineSubNum_LL.getValue();
//            }

            if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.mdseCd_LL, lineMsg.mdseCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.ordCustUomQty_LL, lineMsg.ordCustUomQty_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.custUomCd_LL, lineMsg.custUomCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.ordQty_LL, lineMsg.ordQty_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.entCpoDtlDealSlsAmt_LL, lineMsg.entCpoDtlDealSlsAmt_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.prcCatgNm_LL, lineMsg.prcCatgNm_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dsOrdLineCatgCd_LL, lineMsg.dsOrdLineCatgCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.rtlWhNm_LL, lineMsg.rtlWhNm_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.rtlSwhNm_LL, lineMsg.rtlSwhNm_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.csmpContrNum_LL, lineMsg.csmpContrNum_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dlrRefNum_LL, lineMsg.dlrRefNum_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.csmpPrcListCd_LL, lineMsg.csmpPrcListCd_DB)) {
                return true;
            }

            if (allChkFlg) {
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.mdseNm_LL, lineMsg.mdseNm_DB)) {
                    return true;
                }
                // S21_NA#2334 Add Start
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.mdseDescShortTxt_LL, lineMsg.mdseDescShortTxt_DB)) {
                    return true;
                }
                // S21_NA#2334 Add End
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.origMdseCd_LL, lineMsg.origMdseCd_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.entCpoDtlDealSlsAmt_LL, lineMsg.entCpoDtlDealSlsAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.entDealNetUnitPrcAmt_LL, lineMsg.entDealNetUnitPrcAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.prcCatgNm_LL, lineMsg.prcCatgNm_DB)) { // S21_NA#1952
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.prcListEquipConfigNum_LL, lineMsg.prcListEquipConfigNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.ordLineSrcCd_LL, lineMsg.ordLineSrcCd_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.serNum_LL, lineMsg.serNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsCheckboxEZDItem(lineMsg.supdLockFlg_LL, lineMsg.supdLockFlg_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.sbstMdseCd_LL, lineMsg.sbstMdseCd_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.vndInvtyLocCd_LL, lineMsg.vndInvtyLocCd_DB)) {
                    return true;
                }
                // S21_NA#1952
                // if
                // (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.flPrcListCd_LL,
                // lineMsg.flPrcListCd_LL)) {
                //
                // }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.flPrcListNm_LL, lineMsg.flPrcListNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dealPrcListPrcAmt_LL, lineMsg.dealPrcListPrcAmt_DB)) {
                    return true;
                }
                // QC#22372 2018/01/10 Add Start
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.funcUnitFlPrcAmt_LL, lineMsg.funcUnitFlPrcAmt_DB)) {
                    return true;
                }
                // QC#22372 2018/01/10 Add End
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.cpoDtlDealTaxAmt_LL, lineMsg.cpoDtlDealTaxAmt_DB)) {
                    return true;
                }
                // S21_NA#12598 Del Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.coaMdseTpDescTxt_LL, lineMsg.coaMdseTpDescTxt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.coaMdseTpCd_LL, lineMsg.coaMdseTpCd_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.coaProdDescTxt_LL, lineMsg.coaProdDescTxt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.coaProdCd_LL, lineMsg.coaProdCd_DB)) {
//                        return true;
//                    }
                // S21_NA#12598 Del End
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dplyLineRefNum_LL, lineMsg.dplyLineRefNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.crRebilCd_LL, lineMsg.crRebilCd_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.prcBaseDt_LL, lineMsg.prcBaseDt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.custMdseCd_LL, lineMsg.custMdseCd_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.rddDt_LL, lineMsg.rddDt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.allocQty_LL, lineMsg.allocQty_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.shipQty_LL, lineMsg.shipQty_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.istlQty_LL, lineMsg.istlQty_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.invQty_LL, lineMsg.invQty_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.cancQty_LL, lineMsg.cancQty_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.cpoDtlDealGrsAmt_LL, lineMsg.cpoDtlDealGrsAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dsOrdLineCatgDescTxt_LL, lineMsg.dsOrdLineCatgDescTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dsOrdPosnNum_LL, lineMsg.dsOrdPosnNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dsCpoConfigPk_LL, lineMsg.dsCpoConfigPk_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dsCpoLineNum_LL, lineMsg.dsCpoLineNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dsCpoLineSubNum_LL, lineMsg.dsCpoLineSubNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.invtyLocCd_LL, lineMsg.invtyLocCd_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.rtlWhNm_LL, lineMsg.rtlWhNm_DB)) { // S21_NA#1952
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.rtlSwhNm_LL, lineMsg.rtlSwhNm_DB)) { // S21_NA#1952
                  return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.cpoDtlLineNum_LL, lineMsg.cpoDtlLineNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.cpoDtlLineSubNum_LL, lineMsg.cpoDtlLineSubNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.splyTpCd_LL, lineMsg.splyTpCd_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.splyNm_LL, lineMsg.splyNm_DB)) {
                    return true;
                }

                // 2019/10/04 S21_NA#51372 Mod Start
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.prntVndNm_LL, lineMsg.prntVndNm_DB)) {
                    return true;
                }
                // 2019/10/04 S21_NA#51372 Mod End
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.splyFirstAddr_LL, lineMsg.splyFirstAddr_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.splyCtyAddr_LL, lineMsg.splyCtyAddr_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.splyStCd_LL, lineMsg.splyStCd_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.splyPostCd_LL, lineMsg.splyPostCd_DB)) {
                    return true;
                }
                // S21_NA#12598 Del Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.rntlTrmnDt_LL, lineMsg.rntlTrmnDt_DB)) {
//                        return true;
//                    }
                // S21_NA#12598 Del End
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.baseCmptFlg_LL, lineMsg.baseCmptFlg_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dsContrNum_LL, lineMsg.dsContrNum_DB)) {
                    return true;
                }
                // S21_NA#12598 Del Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dsContrSqNum_LL, lineMsg.dsContrSqNum_DB)) {
//                        return true;
//                    }
                // S21_NA#12598 Del Start
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.svcMachMstrPk_LL, lineMsg.svcMachMstrPk_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.bllgAttrbCustAcctCd_LL, lineMsg.bllgAttrbCustAcctCd_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.firstBllgAttrbNm_LL, lineMsg.firstBllgAttrbNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.firstBllgAttrbValTxt_LL, lineMsg.firstBllgAttrbValTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.scdBllgAttrbNm_LL, lineMsg.scdBllgAttrbNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.scdBllgAttrbValTxt_LL, lineMsg.scdBllgAttrbValTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.thirdBllgAttrbNm_LL, lineMsg.thirdBllgAttrbNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.thirdBllgAttrbValTxt_LL, lineMsg.thirdBllgAttrbValTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.frthBllgAttrbNm_LL, lineMsg.frthBllgAttrbNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.frthBllgAttrbValTxt_LL, lineMsg.frthBllgAttrbValTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.fifthBllgAttrbNm_LL, lineMsg.fifthBllgAttrbNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.fifthBllgAttrbValTxt_LL, lineMsg.fifthBllgAttrbValTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.sixthBllgAttrbNm_LL, lineMsg.sixthBllgAttrbNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.sixthBllgAttrbValTxt_LL, lineMsg.sixthBllgAttrbValTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.ordSrcRefLineNum_LL, lineMsg.ordSrcRefLineNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.ordSrcRefLineSubNum_LL, lineMsg.ordSrcRefLineSubNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.ordUpldRefNum_LL, lineMsg.ordUpldRefNum_DB)) {
                    return true;
                }
                // S21_NA#12598 Del Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.xxTotBaseAmt_LL, lineMsg.xxTotBaseAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.xxTotDiscAmt_LL, lineMsg.xxTotDiscAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.xxTotSpclPrcAmt_LL, lineMsg.xxTotSpclPrcAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.xxTotNetDiscAmt_LL, lineMsg.xxTotNetDiscAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.xxTotNetPrcAmt_LL, lineMsg.xxTotNetPrcAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.xxTotFrtAmt_LL, lineMsg.xxTotFrtAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.xxTotSpclChrgAmt_LL, lineMsg.xxTotSpclChrgAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.xxTotTaxAmt_LL, lineMsg.xxTotTaxAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.xxSlsAmt_LL, lineMsg.xxSlsAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.xxTotAmt_LL, lineMsg.xxTotAmt_DB)) {
//                        return true;
//                    }
                // S21_NA#12598 Del End
                // S21_NA#12598 Add Start
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.lineDealSubTotAmt_LL, lineMsg.lineDealSubTotAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.lineDealChrgAmt_LL, lineMsg.lineDealChrgAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.lineDealTaxAmt_LL, lineMsg.lineDealTaxAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.lineDealTotAmt_LL, lineMsg.lineDealTotAmt_DB)) {
                    return true;
                }
                // S21_NA#12598 Del End
                // 2017/10/18 S21_NA#21759 Add Start
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dealSvcRevTrnsfAmt_LL, lineMsg.dealSvcRevTrnsfAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dealSvcCostTrnsfAmt_LL, lineMsg.dealSvcCostTrnsfAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dealManFlAdjAmt_LL, lineMsg.dealManFlAdjAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(lineMsg.dealManRepRevAdjAmt_LL, lineMsg.dealManRepRevAdjAmt_DB)) {
                    return true;
                }
                // 2017/10/18 S21_NA#21759 Add End
            }
        }
        return false;
    }
    // 2018/01/31 S21_NA#19808 Add End

    // 2018/01/31 S21_NA#19808 Del Start
//    private boolean isModifiedRmaConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean allChkFlg) {
//
//        // RMA line Config: Config Check
//        // 2016/03/30 S21_NA#5326 Add Start
//        if (bizMsg.C.getValidCount() != glblMsg.C.getValidCount()) {
//            return true;
//        }
//        // 2016/03/30 S21_NA#5326 Add End
//        for (int i = 0; i < bizMsg.C.getValidCount() && i < glblMsg.C.getValidCount(); i++) {
//            NWAL1500_CCMsg rmaConfigCMsg = bizMsg.C.no(i);
//            String dsOrdPosNum = rmaConfigCMsg.dsOrdPosnNum_RC.getValue();
//            for (int j = 0; j < glblMsg.C.getValidCount(); j++) {
//                NWAL1500_CSMsg rmaConfigSMsg = glblMsg.C.no(j);
//                if (!dsOrdPosNum.equals(rmaConfigSMsg.dsOrdPosnNum_RC.getValue())) {
//                    continue;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.svcConfigMstrPk_RC, rmaConfigSMsg.svcConfigMstrPk_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.mdlId_RC, rmaConfigSMsg.mdlId_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToCustCd_RC, rmaConfigSMsg.shipToCustCd_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToCustAcctCd_RC, rmaConfigSMsg.shipToCustAcctCd_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToCustAcctNm_RC, rmaConfigSMsg.shipToCustAcctNm_RC)) {
//                    return true;
//                }
//                // Ship To DS information Start ->
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.dropShipFlg_RC, rmaConfigSMsg.dropShipFlg_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToLocNm_RC, rmaConfigSMsg.shipToLocNm_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToAddlLocNm_RC, rmaConfigSMsg.shipToAddlLocNm_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToFirstLineAddr_RC, rmaConfigSMsg.shipToFirstLineAddr_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToScdLineAddr_RC, rmaConfigSMsg.shipToScdLineAddr_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToThirdLineAddr_RC, rmaConfigSMsg.shipToThirdLineAddr_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToFrthLineAddr_RC, rmaConfigSMsg.shipToFrthLineAddr_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToFirstRefCmntTxt_RC, rmaConfigSMsg.shipToFirstRefCmntTxt_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToScdRefCmntTxt_RC, rmaConfigSMsg.shipToScdRefCmntTxt_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToPostCd_RC, rmaConfigSMsg.shipToPostCd_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToCtyAddr_RC, rmaConfigSMsg.shipToCtyAddr_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToStCd_RC, rmaConfigSMsg.shipToStCd_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToProvNm_RC, rmaConfigSMsg.shipToProvNm_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToCtryCd_RC, rmaConfigSMsg.shipToCtryCd_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.shipToCntyNm_RC, rmaConfigSMsg.shipToCntyNm_RC)) {
//                    return true;
//                }
//                // Ship To DS information End <-
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.billToCustCd_RC, rmaConfigSMsg.billToCustCd_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.billToCustAcctCd_RC, rmaConfigSMsg.billToCustAcctCd_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.billToCustAcctNm_RC, rmaConfigSMsg.billToCustAcctNm_RC)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.billToCustAcctNm_RC, rmaConfigSMsg.billToCustAcctNm_RC)) {
//                    return true;
//                }
//                // S21_NA#12598 Add Start
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.configTpCd_RC, rmaConfigSMsg.configTpCd_RC)) {
//                    return true;
//                }
//                // S21_NA#12598 Add Start
//
//                if (allChkFlg) {
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.mdlDescTxt_RC, rmaConfigSMsg.mdlDescTxt_RC)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.mdlNm_RC, rmaConfigSMsg.mdlNm_RC)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigCMsg.pickSvcConfigMstrPk_RC, rmaConfigSMsg.pickSvcConfigMstrPk_RC)) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
    // 2018/01/31 S21_NA#19808 Del End

    // 2018/01/31 S21_NA#19808 Add Start
    private boolean isModifiedRmaConfig(NWAL1500SMsg glblMsg, boolean allChkFlg) {

        // RMA line Config: Config Check
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL1500_CSMsg rmaConfigSMsg = glblMsg.C.no(i);
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.svcConfigMstrPk_RC, rmaConfigSMsg.svcConfigMstrPk_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.mdlId_RC, rmaConfigSMsg.mdlId_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToCustCd_RC, rmaConfigSMsg.shipToCustCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToCustAcctCd_RC, rmaConfigSMsg.shipToCustAcctCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToCustAcctNm_RC, rmaConfigSMsg.shipToCustAcctNm_DB)) {
                return true;
            }
            // Ship To DS information Start ->
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.dropShipFlg_RC, rmaConfigSMsg.dropShipFlg_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToLocNm_RC, rmaConfigSMsg.shipToLocNm_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToAddlLocNm_RC, rmaConfigSMsg.shipToAddlLocNm_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToFirstLineAddr_RC, rmaConfigSMsg.shipToFirstLineAddr_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToScdLineAddr_RC, rmaConfigSMsg.shipToScdLineAddr_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToThirdLineAddr_RC, rmaConfigSMsg.shipToThirdLineAddr_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToFrthLineAddr_RC, rmaConfigSMsg.shipToFrthLineAddr_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToFirstRefCmntTxt_RC, rmaConfigSMsg.shipToFirstRefCmntTxt_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToScdRefCmntTxt_RC, rmaConfigSMsg.shipToScdRefCmntTxt_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToPostCd_RC, rmaConfigSMsg.shipToPostCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToCtyAddr_RC, rmaConfigSMsg.shipToCtyAddr_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToStCd_RC, rmaConfigSMsg.shipToStCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToProvNm_RC, rmaConfigSMsg.shipToProvNm_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToCtryCd_RC, rmaConfigSMsg.shipToCtryCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.shipToCntyNm_RC, rmaConfigSMsg.shipToCntyNm_DB)) {
                return true;
            }
            // Ship To DS information End <-
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.billToCustCd_RC, rmaConfigSMsg.billToCustCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.billToCustAcctCd_RC, rmaConfigSMsg.billToCustAcctCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.billToCustAcctNm_RC, rmaConfigSMsg.billToCustAcctNm_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.billToCustAcctNm_RC, rmaConfigSMsg.billToCustAcctNm_DB)) {
                return true;
            }
            // S21_NA#12598 Add Start
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.configTpCd_RC, rmaConfigSMsg.configTpCd_DB)) {
                return true;
            }
            // S21_NA#12598 Add Start

            if (allChkFlg) {
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.mdlDescTxt_RC, rmaConfigSMsg.mdlDescTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.mdlNm_RC, rmaConfigSMsg.mdlNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigSMsg.pickSvcConfigMstrPk_RC, rmaConfigSMsg.pickSvcConfigMstrPk_DB)) {
                    return true;
                }
            }
        }
        return false;
    }
    // 2018/01/31 S21_NA#19808 Add End

    // 2018/01/31 S21_NA#19808 Del Start
//    private boolean isModifiedRmaLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean allChkFlg) {
//
//        // RMA line config: line check
//        // 2016/03/30 S21_NA#5326 Add Start
//        if (bizMsg.D.getValidCount() != glblMsg.D.getValidCount()) {
//            return true;
//        }
//        // 2016/03/30 S21_NA#5326 Add End
//        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
//            NWAL1500_DCMsg rmaLineCMsg = bizMsg.D.no(i);
//            String xxLineNum = "C";
//            if (ZYPCommonFunc.hasValue(rmaLineCMsg.xxLineNum_RL)) {
//                xxLineNum = rmaLineCMsg.xxLineNum_RL.getValue();
//            }
//            String cpoDtlLineNum = "C1";
//            if (ZYPCommonFunc.hasValue(rmaLineCMsg.cpoDtlLineNum_RL)) {
//                cpoDtlLineNum = rmaLineCMsg.cpoDtlLineNum_RL.getValue();
//            }
//            String cpoDtlLineSubNum = "C2";
//            if (ZYPCommonFunc.hasValue(rmaLineCMsg.cpoDtlLineSubNum_RL)) {
//                cpoDtlLineSubNum = rmaLineCMsg.cpoDtlLineSubNum_RL.getValue();
//            }
//
//            for (int j = 0; j < glblMsg.D.getValidCount(); j++) { // 2015/12/10 S.Takami Mod: glblMsg.B -> glblMsg.D
//                NWAL1500_DSMsg rmaLineSMsg = glblMsg.D.no(j);
//                String xxLineNumS = "S";
//                if (ZYPCommonFunc.hasValue(rmaLineSMsg.xxLineNum_RL.getValue())) {
//                    xxLineNumS = rmaLineSMsg.xxLineNum_RL.getValue();
//                }
//                String cpoDtlLineNumS = "S1";
//                if (ZYPCommonFunc.hasValue(rmaLineSMsg.cpoDtlLineNum_RL)) {
//                    cpoDtlLineNumS = rmaLineSMsg.cpoDtlLineNum_RL.getValue();
//                }
//                String cpoDtlLineSubNumS = "S2";
//                if (ZYPCommonFunc.hasValue(rmaLineSMsg.cpoDtlLineSubNum_RL)) {
//                    cpoDtlLineSubNumS = rmaLineSMsg.cpoDtlLineSubNum_RL.getValue();
//                }
//
//                boolean isSameLine = xxLineNum.equals(xxLineNumS);
//                isSameLine |= (cpoDtlLineNum.equals(cpoDtlLineNumS) && cpoDtlLineSubNum.equals(cpoDtlLineSubNumS));
//
//                if (!isSameLine) {
//                    continue;
//                }
//                if (!xxLineNum.equals(rmaLineSMsg.xxLineNum_RL.getValue())) {
//                    continue;
//                }
//
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.mdseCd_RL, rmaLineSMsg.mdseCd_RL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.ordCustUomQty_RL, rmaLineSMsg.ordCustUomQty_RL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.custUomCd_RL, rmaLineSMsg.custUomCd_RL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.ordQty_RL, rmaLineSMsg.ordQty_RL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.entCpoDtlDealSlsAmt_RL, rmaLineSMsg.entCpoDtlDealSlsAmt_RL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.prcCatgNm_RL, rmaLineSMsg.prcCatgNm_RL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dsOrdLineCatgCd_RL, rmaLineSMsg.dsOrdLineCatgCd_RL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.rtlWhNm_RL, rmaLineSMsg.rtlWhNm_RL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.rtlSwhNm_RL, rmaLineSMsg.rtlSwhNm_RL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.csmpContrNum_RL, rmaLineSMsg.csmpContrNum_RL)) {
//                    return true;
//                }
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dlrRefNum_RL, rmaLineSMsg.dlrRefNum_RL)) {
//                    return true;
//                }
//                // S21_NA#12598 Del Start
////                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.csmpPrcListCd_RL, rmaLineSMsg.csmpPrcListCd_RL)) {
////                    return true;
////                }
//                // S21_NA#12598 Del End
//                if (allChkFlg) {
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.mdseNm_RL, rmaLineSMsg.mdseNm_RL)) {
//                        return true;
//                    }
//                    // S21_NA#2334 Add Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.mdseDescShortTxt_RL, rmaLineSMsg.mdseDescShortTxt_RL)) {
//                        return true;
//                    }
//                    // S21_NA#2334 Add End
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.origMdseCd_RL, rmaLineSMsg.origMdseCd_RL)) { // S21_NA#14805 DEL
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.ent, rmaLineSMsg.entCpoDtlDealSlsAmt_RL)) {
////                        return true;
////                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.entDealNetUnitPrcAmt_RL, rmaLineSMsg.entDealNetUnitPrcAmt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.prcCatgNm_RL, rmaLineSMsg.prcCatgNm_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.serNum_RL, rmaLineSMsg.serNum_RL)) {
//                        return true;
//                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.supdLockFlg_RL, rmaLineSMsg.supdLockFlg_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.sbstMdseCd_RL, rmaLineSMsg.sbstMdseCd_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.vndInvtyLocCd_RL, rmaLineSMsg.vndInvtyLocCd_RL)) {
////                        return true;
////                    }
//                    // S21_NA#1952
//                    // if
//                    // (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.flPrcListCd_RL,
//                    // rmaLineSMsg.flPrcListCd_RL)) {
//                    // return true;
//                    // }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.flPrcListNm_RL, rmaLineSMsg.flPrcListNm_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dealPrcListPrcAmt_RL, rmaLineSMsg.dealPrcListPrcAmt_RL)) {
//                        return true;
//                    }
//                    // QC#22372 2018/01/10 Add Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.funcUnitFlPrcAmt_RL, rmaLineSMsg.funcUnitFlPrcAmt_RL)) {
//                        return true;
//                    }
//                    // QC#22372 2018/01/10 Add End
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.cpoDtlDealTaxAmt_RL, rmaLineSMsg.cpoDtlDealTaxAmt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.xxLineTotPrcAmt_RL, rmaLineSMsg.xxLineTotPrcAmt_RL)) {
//                        return true;
//                    }
//                    // S21_NA#12598 Del Start
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.coaMdseTpDescTxt_RL, rmaLineSMsg.coaMdseTpDescTxt_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.coaMdseTpCd_RL, rmaLineSMsg.coaMdseTpCd_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.coaProdDescTxt_RL, rmaLineSMsg.coaProdDescTxt_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.coaProdCd_RL, rmaLineSMsg.coaProdCd_RL)) {
////                        return true;
////                    }
//                    // S21_NA#12598 Del End
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dplyLineRefNum_RL, rmaLineSMsg.dplyLineRefNum_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.prcBaseDt_RL, rmaLineSMsg.prcBaseDt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.custMdseCd_RL, rmaLineSMsg.custMdseCd_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.rqstPickUpDt_RL, rmaLineSMsg.rqstPickUpDt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.rtrnRsnCd_RL, rmaLineSMsg.rtrnRsnCd_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.hddRmvCd_RL, rmaLineSMsg.hddRmvCd_RL)) {
//                        return true;
//                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.allocQty_RL, rmaLineSMsg.allocQty_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.shipQty_RL, rmaLineSMsg.shipQty_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.istlQty_RL, rmaLineSMsg.istlQty_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.invQty_RL, rmaLineSMsg.invQty_RL)) {
////                        return true;
////                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.rtrnQty_RL, rmaLineSMsg.rtrnQty_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.cancQty_RL, rmaLineSMsg.cancQty_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.cpoDtlDealGrsAmt_RL, rmaLineSMsg.cpoDtlDealGrsAmt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dsOrdLineCatgDescTxt_RL, rmaLineSMsg.dsOrdLineCatgDescTxt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dsOrdPosnNum_RL, rmaLineSMsg.dsOrdPosnNum_RL)) {
//                        return true;
//                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dsCpoConfigPk_RL, rmaLineSMsg.dsCpoConfigPk_RL)) {
////                        return true;
////                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dsCpoLineNum_RL, rmaLineSMsg.dsCpoLineNum_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dsCpoLineSubNum_RL, rmaLineSMsg.dsCpoLineSubNum_RL)) {
//                        return true;
//                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.invtyLocCd_RL, rmaLineSMsg.invtyLocCd_RL)) {
////                        return true;
////                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.rtlWhNm_RL, rmaLineSMsg.rtlWhNm_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.rtlSwhNm_RL, rmaLineSMsg.rtlSwhNm_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.cpoDtlLineNum_RL, rmaLineSMsg.cpoDtlLineNum_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.cpoDtlLineSubNum_RL, rmaLineSMsg.cpoDtlLineSubNum_RL)) {
//                        return true;
//                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.splyTpCd_RL, rmaLineSMsg.splyTpCd_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.splyNm_RL, rmaLineSMsg.splyNm_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.splyFirstAddr_RL, rmaLineSMsg.splyFirstAddr_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.splyCtyAddr_RL, rmaLineSMsg.splyCtyAddr_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.splyStCd_RL, rmaLineSMsg.splyStCd_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.splyPostCd_RL, rmaLineSMsg.splyPostCd_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.rntlTrmnDt_RL, rmaLineSMsg.rntlTrmnDt_RL)) {
////                        return true;
////                    }
//                     // S21_NA#12598 Del Start
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.baseCmptFlg_RL, rmaLineSMsg.baseCmptFlg_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dsContrNum_RL, rmaLineSMsg.dsContrNum_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dsContrSqNum_RL, rmaLineSMsg.dsContrSqNum_RL)) {
////                        return true;
////                    }
//                    // S21_NA#12598 Del End
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.svcMachMstrPk_RL, rmaLineSMsg.svcMachMstrPk_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.bllgAttrbCustAcctCd_RL, rmaLineSMsg.bllgAttrbCustAcctCd_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.firstBllgAttrbNm_RL, rmaLineSMsg.firstBllgAttrbNm_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.firstBllgAttrbValTxt_RL, rmaLineSMsg.firstBllgAttrbValTxt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.scdBllgAttrbNm_RL, rmaLineSMsg.scdBllgAttrbNm_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.scdBllgAttrbValTxt_RL, rmaLineSMsg.scdBllgAttrbValTxt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.thirdBllgAttrbNm_RL, rmaLineSMsg.thirdBllgAttrbNm_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.thirdBllgAttrbValTxt_RL, rmaLineSMsg.thirdBllgAttrbValTxt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.frthBllgAttrbNm_RL, rmaLineSMsg.frthBllgAttrbNm_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.frthBllgAttrbValTxt_RL, rmaLineSMsg.frthBllgAttrbValTxt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.fifthBllgAttrbNm_RL, rmaLineSMsg.fifthBllgAttrbNm_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.fifthBllgAttrbValTxt_RL, rmaLineSMsg.fifthBllgAttrbValTxt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.sixthBllgAttrbNm_RL, rmaLineSMsg.sixthBllgAttrbNm_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.sixthBllgAttrbValTxt_RL, rmaLineSMsg.sixthBllgAttrbValTxt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.ordSrcRefLineNum_RL, rmaLineSMsg.ordSrcRefLineNum_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.ordSrcRefLineSubNum_RL, rmaLineSMsg.ordSrcRefLineSubNum_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.ordUpldRefNum_RL, rmaLineSMsg.ordUpldRefNum_RL)) {
//                        return true;
//                    }
//                    // S21_NA#12598 Del Start
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.xxTotBaseAmt_RL, rmaLineSMsg.xxTotBaseAmt_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.xxTotDiscAmt_RL, rmaLineSMsg.xxTotDiscAmt_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.xxTotSpclPrcAmt_RL, rmaLineSMsg.xxTotSpclPrcAmt_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.xxTotNetDiscAmt_RL, rmaLineSMsg.xxTotNetDiscAmt_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.xxTotNetPrcAmt_RL, rmaLineSMsg.xxTotNetPrcAmt_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.xxTotFrtAmt_RL, rmaLineSMsg.xxTotFrtAmt_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.xxTotSpclChrgAmt_RL, rmaLineSMsg.xxTotSpclChrgAmt_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.xxTotTaxAmt_RL, rmaLineSMsg.xxTotTaxAmt_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.xxSlsAmt_RL, rmaLineSMsg.xxSlsAmt_RL)) {
////                        return true;
////                    }
////                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.xxTotAmt_RL, rmaLineSMsg.xxTotAmt_RL)) {
////                        return true;
////                    }
//                    // S21_NA#12598 Del Start
//                    // S21_NA#12598 Add Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.lineDealSubTotAmt_RL, rmaLineSMsg.lineDealSubTotAmt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.lineDealChrgAmt_RL, rmaLineSMsg.lineDealChrgAmt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.lineDealTaxAmt_RL, rmaLineSMsg.lineDealTaxAmt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.lineDealTotAmt_RL, rmaLineSMsg.lineDealTotAmt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.hddRmvCd_RL, rmaLineSMsg.hddRmvCd_RL)) {
//                        return true;
//                    }
//                    // S21_NA#12598 Add End
//                    // 2017/10/18 S21_NA#21759 Add Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dealSvcRevTrnsfAmt_RL, rmaLineSMsg.dealSvcRevTrnsfAmt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dealSvcCostTrnsfAmt_RL, rmaLineSMsg.dealSvcCostTrnsfAmt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dealManFlAdjAmt_RL, rmaLineSMsg.dealManFlAdjAmt_RL)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineCMsg.dealManRepRevAdjAmt_RL, rmaLineSMsg.dealManRepRevAdjAmt_RL)) {
//                        return true;
//                    }
//                    // 2017/10/18 S21_NA#21759 Add End
//                }
//            }
//        }
//        return false;
//    }
    // 2018/01/31 S21_NA#19808 Del End

    // 2018/01/31 S21_NA#19808 Add Start
    private boolean isModifiedRmaLine(NWAL1500SMsg glblMsg, boolean allChkFlg) {

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg rmaLineSMsg = glblMsg.D.no(i);

            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.mdseCd_RL, rmaLineSMsg.mdseCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.ordCustUomQty_RL, rmaLineSMsg.ordCustUomQty_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.custUomCd_RL, rmaLineSMsg.custUomCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.ordQty_RL, rmaLineSMsg.ordQty_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.entCpoDtlDealSlsAmt_RL, rmaLineSMsg.entCpoDtlDealSlsAmt_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.prcCatgNm_RL, rmaLineSMsg.prcCatgNm_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dsOrdLineCatgCd_RL, rmaLineSMsg.dsOrdLineCatgCd_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.rtlWhNm_RL, rmaLineSMsg.rtlWhNm_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.rtlSwhNm_RL, rmaLineSMsg.rtlSwhNm_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.csmpContrNum_RL, rmaLineSMsg.csmpContrNum_DB)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dlrRefNum_RL, rmaLineSMsg.dlrRefNum_DB)) {
                return true;
            }
            // S21_NA#12598 Del Start
//                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.csmpPrcListCd_RL, rmaLineSMsg.csmpPrcListCd_DB)) {
//                    return true;
//                }
            // S21_NA#12598 Del End
            if (allChkFlg) {
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.mdseNm_RL, rmaLineSMsg.mdseNm_DB)) {
                    return true;
                }
                // S21_NA#2334 Add Start
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.mdseDescShortTxt_RL, rmaLineSMsg.mdseDescShortTxt_DB)) {
                    return true;
                }
                // S21_NA#2334 Add End
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.origMdseCd_RL, rmaLineSMsg.origMdseCd_DB)) { // S21_NA#14805 DEL
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.ent, rmaLineSMsg.entCpoDtlDealSlsAmt_DB)) {
//                        return true;
//                    }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.entDealNetUnitPrcAmt_RL, rmaLineSMsg.entDealNetUnitPrcAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.prcCatgNm_RL, rmaLineSMsg.prcCatgNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.serNum_RL, rmaLineSMsg.serNum_DB)) {
                    return true;
                }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.supdLockFlg_RL, rmaLineSMsg.supdLockFlg_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.sbstMdseCd_RL, rmaLineSMsg.sbstMdseCd_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.vndInvtyLocCd_RL, rmaLineSMsg.vndInvtyLocCd_DB)) {
//                        return true;
//                    }
                // S21_NA#1952
                // if
                // (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.flPrcListCd_RL,
                // rmaLineSMsg.flPrcListCd_DB)) {
                // return true;
                // }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.flPrcListNm_RL, rmaLineSMsg.flPrcListNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dealPrcListPrcAmt_RL, rmaLineSMsg.dealPrcListPrcAmt_DB)) {
                    return true;
                }
                // QC#22372 2018/01/10 Add Start
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.funcUnitFlPrcAmt_RL, rmaLineSMsg.funcUnitFlPrcAmt_DB)) {
                    return true;
                }
                // QC#22372 2018/01/10 Add End
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.cpoDtlDealTaxAmt_RL, rmaLineSMsg.cpoDtlDealTaxAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.xxLineTotPrcAmt_RL, rmaLineSMsg.xxLineTotPrcAmt_DB)) {
                    return true;
                }
                // S21_NA#12598 Del Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.coaMdseTpDescTxt_RL, rmaLineSMsg.coaMdseTpDescTxt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.coaMdseTpCd_RL, rmaLineSMsg.coaMdseTpCd_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.coaProdDescTxt_RL, rmaLineSMsg.coaProdDescTxt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.coaProdCd_RL, rmaLineSMsg.coaProdCd_DB)) {
//                        return true;
//                    }
                // S21_NA#12598 Del End
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dplyLineRefNum_RL, rmaLineSMsg.dplyLineRefNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.prcBaseDt_RL, rmaLineSMsg.prcBaseDt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.custMdseCd_RL, rmaLineSMsg.custMdseCd_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.rqstPickUpDt_RL, rmaLineSMsg.rqstPickUpDt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.rtrnRsnCd_RL, rmaLineSMsg.rtrnRsnCd_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.hddRmvCd_RL, rmaLineSMsg.hddRmvCd_DB)) {
                    return true;
                }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.allocQty_RL, rmaLineSMsg.allocQty_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.shipQty_RL, rmaLineSMsg.shipQty_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.istlQty_RL, rmaLineSMsg.istlQty_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.invQty_RL, rmaLineSMsg.invQty_DB)) {
//                        return true;
//                    }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.rtrnQty_RL, rmaLineSMsg.rtrnQty_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.cancQty_RL, rmaLineSMsg.cancQty_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.cpoDtlDealGrsAmt_RL, rmaLineSMsg.cpoDtlDealGrsAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dsOrdLineCatgDescTxt_RL, rmaLineSMsg.dsOrdLineCatgDescTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dsOrdPosnNum_RL, rmaLineSMsg.dsOrdPosnNum_DB)) {
                    return true;
                }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dsCpoConfigPk_RL, rmaLineSMsg.dsCpoConfigPk_DB)) {
//                        return true;
//                    }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dsCpoLineNum_RL, rmaLineSMsg.dsCpoLineNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dsCpoLineSubNum_RL, rmaLineSMsg.dsCpoLineSubNum_DB)) {
                    return true;
                }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.invtyLocCd_RL, rmaLineSMsg.invtyLocCd_DB)) {
//                        return true;
//                    }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.rtlWhNm_RL, rmaLineSMsg.rtlWhNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.rtlSwhNm_RL, rmaLineSMsg.rtlSwhNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.cpoDtlLineNum_RL, rmaLineSMsg.cpoDtlLineNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.cpoDtlLineSubNum_RL, rmaLineSMsg.cpoDtlLineSubNum_DB)) {
                    return true;
                }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.splyTpCd_RL, rmaLineSMsg.splyTpCd_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.splyNm_RL, rmaLineSMsg.splyNm_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.splyFirstAddr_RL, rmaLineSMsg.splyFirstAddr_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.splyCtyAddr_RL, rmaLineSMsg.splyCtyAddr_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.splyStCd_RL, rmaLineSMsg.splyStCd_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.splyPostCd_RL, rmaLineSMsg.splyPostCd_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.rntlTrmnDt_RL, rmaLineSMsg.rntlTrmnDt_DB)) {
//                        return true;
//                    }
                 // S21_NA#12598 Del Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.baseCmptFlg_RL, rmaLineSMsg.baseCmptFlg_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dsContrNum_RL, rmaLineSMsg.dsContrNum_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dsContrSqNum_RL, rmaLineSMsg.dsContrSqNum_DB)) {
//                        return true;
//                    }
                // S21_NA#12598 Del End
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.svcMachMstrPk_RL, rmaLineSMsg.svcMachMstrPk_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.bllgAttrbCustAcctCd_RL, rmaLineSMsg.bllgAttrbCustAcctCd_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.firstBllgAttrbNm_RL, rmaLineSMsg.firstBllgAttrbNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.firstBllgAttrbValTxt_RL, rmaLineSMsg.firstBllgAttrbValTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.scdBllgAttrbNm_RL, rmaLineSMsg.scdBllgAttrbNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.scdBllgAttrbValTxt_RL, rmaLineSMsg.scdBllgAttrbValTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.thirdBllgAttrbNm_RL, rmaLineSMsg.thirdBllgAttrbNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.thirdBllgAttrbValTxt_RL, rmaLineSMsg.thirdBllgAttrbValTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.frthBllgAttrbNm_RL, rmaLineSMsg.frthBllgAttrbNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.frthBllgAttrbValTxt_RL, rmaLineSMsg.frthBllgAttrbValTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.fifthBllgAttrbNm_RL, rmaLineSMsg.fifthBllgAttrbNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.fifthBllgAttrbValTxt_RL, rmaLineSMsg.fifthBllgAttrbValTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.sixthBllgAttrbNm_RL, rmaLineSMsg.sixthBllgAttrbNm_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.sixthBllgAttrbValTxt_RL, rmaLineSMsg.sixthBllgAttrbValTxt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.ordSrcRefLineNum_RL, rmaLineSMsg.ordSrcRefLineNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.ordSrcRefLineSubNum_RL, rmaLineSMsg.ordSrcRefLineSubNum_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.ordUpldRefNum_RL, rmaLineSMsg.ordUpldRefNum_DB)) {
                    return true;
                }
                // S21_NA#12598 Del Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.xxTotBaseAmt_RL, rmaLineSMsg.xxTotBaseAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.xxTotDiscAmt_RL, rmaLineSMsg.xxTotDiscAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.xxTotSpclPrcAmt_RL, rmaLineSMsg.xxTotSpclPrcAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.xxTotNetDiscAmt_RL, rmaLineSMsg.xxTotNetDiscAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.xxTotNetPrcAmt_RL, rmaLineSMsg.xxTotNetPrcAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.xxTotFrtAmt_RL, rmaLineSMsg.xxTotFrtAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.xxTotSpclChrgAmt_RL, rmaLineSMsg.xxTotSpclChrgAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.xxTotTaxAmt_RL, rmaLineSMsg.xxTotTaxAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.xxSlsAmt_RL, rmaLineSMsg.xxSlsAmt_DB)) {
//                        return true;
//                    }
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.xxTotAmt_RL, rmaLineSMsg.xxTotAmt_DB)) {
//                        return true;
//                    }
                // S21_NA#12598 Del Start
                // S21_NA#12598 Add Start
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.lineDealSubTotAmt_RL, rmaLineSMsg.lineDealSubTotAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.lineDealChrgAmt_RL, rmaLineSMsg.lineDealChrgAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.lineDealTaxAmt_RL, rmaLineSMsg.lineDealTaxAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.lineDealTotAmt_RL, rmaLineSMsg.lineDealTotAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.hddRmvCd_RL, rmaLineSMsg.hddRmvCd_DB)) {
                    return true;
                }
                // S21_NA#12598 Add End
                // 2017/10/18 S21_NA#21759 Add Start
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dealSvcRevTrnsfAmt_RL, rmaLineSMsg.dealSvcRevTrnsfAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dealSvcCostTrnsfAmt_RL, rmaLineSMsg.dealSvcCostTrnsfAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dealManFlAdjAmt_RL, rmaLineSMsg.dealManFlAdjAmt_DB)) {
                    return true;
                }
                if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaLineSMsg.dealManRepRevAdjAmt_RL, rmaLineSMsg.dealManRepRevAdjAmt_DB)) {
                    return true;
                }
                // 2017/10/18 S21_NA#21759 Add End
            }
        }
        return false;
    }
    // 2018/01/31 S21_NA#19808 Add End

    /**
     * Compare Pricing Element data between CMsg and SMsg
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param bcMsg target Line data
     * @return true: same false: different
     */
    private boolean compPriceElementsList(NWAL1500_ISMsgArray sPriceElementArray) {
        // 2018/03/15 S21_NA#19808 compare between _LP and _DB, without any comments.
        for (int i = 0; i < sPriceElementArray.getValidCount(); i++) {
            NWAL1500_ISMsg sPriceElement = sPriceElementArray.no(i);

            boolean isSame = true;

            isSame = NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.xxLineNum_LP, sPriceElement.xxLineNum_DB);
//                isSame &= NWAL1500CommonLogic.isEqualsEZDItem(cPriceElement.ordPrcCalcBasePk_LP, sPriceElement.ordPrcCalcBasePk_LP);
//                isSame &= NWAL1500CommonLogic.isEqualsEZDItem(cPriceElement.cpoOrdNum_LP, sPriceElement.cpoOrdNum_LP);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.cpoDtlLineNum_LP, sPriceElement.cpoDtlLineNum_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.cpoDtlLineSubNum_LP, sPriceElement.cpoDtlLineSubNum_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.configCatgCd_LP, sPriceElement.configCatgCd_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.prcCondTpCd_LP, sPriceElement.prcCondTpCd_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.prcDtlGrpCd_LP, sPriceElement.prcDtlGrpCd_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.prcJrnlGrpCd_LP, sPriceElement.prcJrnlGrpCd_DB);
//                isSame &= NWAL1500CommonLogic.isEqualsEZDItem(cPriceElement.prcCatgCd_LP, sPriceElement.prcCatgCd_LP);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.pkgUomCd_LP, sPriceElement.pkgUomCd_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.prcCondUnitCd_LP, sPriceElement.prcCondUnitCd_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.prcCalcMethCd_LP, sPriceElement.prcCalcMethCd_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.dsMdsePrcPk_LP, sPriceElement.dsMdsePrcPk_DB);
//                isSame &= NWAL1500CommonLogic.isEqualsEZDItem(cPriceElement.specCondPrcPk_LP, sPriceElement.specCondPrcPk_LP);
//                isSame &= NWAL1500CommonLogic.isEqualsEZDItem(cPriceElement.frtPerWtPk_LP, sPriceElement.frtPerWtPk_LP);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.prcCondManEntryFlg_LP, sPriceElement.prcCondManEntryFlg_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.prcCondManAddFlg_LP, sPriceElement.prcCondManAddFlg_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.prcCondManDelFlg_LP, sPriceElement.prcCondManDelFlg_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.autoPrcAmtRate_LP, sPriceElement.autoPrcAmtRate_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.maxPrcAmtRate_LP, sPriceElement.maxPrcAmtRate_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.minPrcAmtRate_LP, sPriceElement.minPrcAmtRate_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.manPrcAmtRate_LP, sPriceElement.manPrcAmtRate_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.calcPrcAmtRate_LP, sPriceElement.calcPrcAmtRate_DB);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.unitPrcAmt_LP, sPriceElement.unitPrcAmt_DB);
//                isSame &= NWAL1500CommonLogic.isEqualsEZDItem(cPriceElement.coaAcctCd_LP, sPriceElement.coaAcctCd_LP);
            isSame &= NWAL1500CommonLogic.isEqualsEZDItem(sPriceElement.autoPrcCcyCd_LP, sPriceElement.autoPrcCcyCd_DB);
//                isSame &= NWAL1500CommonLogic.isEqualsEZDItem(cPriceElement.prcCondTpDescTxt_LP, sPriceElement.prcCondTpDescTxt_LP);

            if (!isSame) {
                return false;
            }
        }
        return true;
    }

    // S21_NA#3649 Del Start
//    @SuppressWarnings("unchecked")
//    private void callSuperSedeApi(NWAL1500CMsg bizMsg, NWAL1500_BCMsg bcMsg, boolean isDropShipRtlWh) {
//
//        if (!ZYPCommonFunc.hasValue(bcMsg.rtlWhNm_LL) && !ZYPCommonFunc.hasValue(bcMsg.rtlSwhNm_LL)) {
//            return;
//        }
//
//        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
//        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(glblCmpyCd, bcMsg.mdseCd_LL.getValue());
// 
//        // add:get retail warehouse code
//        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getRtlWh(glblCmpyCd, bcMsg.rtlWhNm_LL.getValue(), bcMsg.rtlSwhNm_LL.getValue(), null, false);
//        if (!ssmResult.isCodeNormal()) {
//            return;
//        }
//        Map<Object, String> rtlWh = (Map<Object, String>) ssmResult.getResultObject();
//        ZYPEZDItemValueSetter.setValue(bcMsg.rtlWhCd_LL, (String) rtlWh.get("RTL_WH_CD"));
//        if (ZYPCommonFunc.hasValue(bcMsg.rtlSwhNm_LL)) {
//            ZYPEZDItemValueSetter.setValue(bcMsg.rtlSwhCd_LL, (String) rtlWh.get("RTL_SWH_CD"));
//        }
//
//        String invtyLocCd = bcMsg.rtlWhCd_LL.getValue();
//        if (ZYPCommonFunc.hasValue(bcMsg.rtlSwhCd_LL) && !isDropShipRtlWh) {
//            invtyLocCd = invtyLocCd + bcMsg.rtlSwhCd_LL.getValue();
//        }
//
//        String xxModCd = NWZC206001.SUPD_LIST_MODE;
//        if (isDropShipRtlWh) {
//            xxModCd = NWZC206001.SUPD_LATEST_MODE;
//        }
//
//        NWZC206001PMsg superSedeApiPMsg = new NWZC206001PMsg();
//        superSedeApiPMsg.glblCmpyCd.setValue(glblCmpyCd);
//        superSedeApiPMsg.mdseCd.setValue(mdseTMsg.mdseCd.getValue());
//        superSedeApiPMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());
//        superSedeApiPMsg.xxModeCd.setValue(xxModCd);
//        superSedeApiPMsg.xxAvalOrdFlg.setValue(ZYPConstant.FLG_ON_Y);
//        superSedeApiPMsg.xxAvalPrchFlg.setValue(ZYPConstant.FLG_OFF_N);
//        superSedeApiPMsg.whCd.setValue(invtyLocCd);
//        superSedeApiPMsg.stkStsCd.setValue(STK_STS.GOOD);
//        new NWZC206001().execute(superSedeApiPMsg, ONBATCH_TYPE.ONLINE);
//
//        List<String> errMsg = checkApiMessage(superSedeApiPMsg);
//        if (!errMsg.isEmpty()) {
//            bcMsg.supdLockFlg_LL.setErrorInfo(1, NWAM0189E, new String[] {"SupersedeAPI."});
//            bizMsg.setMessageInfo(ZZM9037E);
//            return;
//        }
//        if (superSedeApiPMsg.A.getValidCount() == 0) {
//            return;
//        }
//        String superSedeMdseCd = "";
//        String superSedeMdseNm = "";
//
//        if (isDropShipRtlWh) {
//            superSedeMdseCd = superSedeApiPMsg.A.no(0).mdseCd.getValue();
//            superSedeMdseNm = superSedeApiPMsg.A.no(0).mdseNm.getValue();
//        } else {
//            for (int n = 0; n < superSedeApiPMsg.A.getValidCount(); n++) {
//                NWZC206001_APMsg aPMsg = superSedeApiPMsg.A.no(n);
//                if (bcMsg.ordQty_LL.getValue().compareTo(aPMsg.invtyAvalQty.getValue()) <= 0) {
//                    superSedeMdseCd = aPMsg.mdseCd.getValue();
//                    superSedeMdseNm = aPMsg.mdseNm.getValue();
//                    break;
//                }
//            }
//            if (superSedeMdseCd.isEmpty()) {
//                int n = superSedeApiPMsg.A.getValidCount() - 1;
//                superSedeMdseCd = superSedeApiPMsg.A.no(n).mdseCd.getValue();
//                superSedeMdseNm = superSedeApiPMsg.A.no(n).mdseNm.getValue();
//            }
//        }
//        bcMsg.origMdseCd_LL.setValue(bcMsg.mdseCd_LL.getValue());
//        String ordTakeMdseCd = getOrdTakeMdseCd(bizMsg.glblCmpyCd.getValue(), superSedeMdseCd); // 2015/12/04 S21_NA#1290
//        bcMsg.mdseCd_LL.setValue(ordTakeMdseCd);
//        bcMsg.mdseNm_LL.setValue(superSedeMdseNm);
//
//        return;
//    }
    // S21_NA#3649 Del End

    /**
     * <pre>
     * call DS CPO Update API
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param isSave true: save Mode, false: Submit Mode
     * @param isModifyReq true: modified false: not
     * @param isCopyOrd true: Copy Order false: not
     * @param cntArray Count Array
     * @return true: normal end false: abnornal end
     */
    public boolean callDsCpoUpdateApi(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean isSave, boolean isModifyReq, int[] cntArray) {

        NWZC150001PMsg cpoUpdApiReqPMsg = new NWZC150001PMsg();
        boolean isNew = true;
        boolean isHdrSaved = false;
        boolean isModified = false;

        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            isNew = false;
            isHdrSaved = isHdrSaved(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue());
        } else {
            isNew = true;
        }

        // For Performance QC#8166 Add Start
        Map<String, String> ccyCdMap = new HashMap<String, String>();
        Map<String, BigDecimal> inPoundWtMap = new HashMap<String, BigDecimal>();
        // For Performance QC#8166 Add End

        if (isSave) {
            // CMN_Save
//            boolean isCallUpdApi = true;
//            if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
//                if (isHdrSaved) {
//                    isCallUpdApi = false;
//                }
//            }

            boolean hdrUpdtFlg = false;
            boolean dtlUpdFlg = false;
            // S21_NA#12598 Mod
            //if (!isCallUpdApi) {
                // check order data
                // S21 _NA#1952 if
                // (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxEdtModeFlg.getValue()))
                // {
            hdrUpdtFlg = isModifiedHdr(bizMsg, glblMsg, true);
            // QC#58110 Mod Start
            dtlUpdFlg = isModifiedDtl(bizMsg, glblMsg, true, cpoUpdApiReqPMsg);

            boolean updtFlg = hdrUpdtFlg | dtlUpdFlg;
            if (!updtFlg) {

                // no call DS CPO Update API
                if (needsToUpdateSlsCr(bizMsg, glblMsg)) { // S21_NA#1745

                    // update sales credit.
                    callSalesCreditUpdateApi(bizMsg); // S21_NA#1745
                }
                return true;
            }
                // S21 _NA#1952 }
            // S21_NA#12598 Mod End
            //}
            // S21_NA#1952
            if (isNew | hdrUpdtFlg | dtlUpdFlg) {
                // QC#4078 Mod
                setCpoUpdApiReqPMsg(NWZC150001Constant.MODE_SAVE, cpoUpdApiReqPMsg, bizMsg, glblMsg, ZYPConstant.FLG_ON_Y, ZYPConstant.FLG_OFF_N); // 2018/01/31 S21_NA#19808
            } else {
                // QC#4078 Mod
                setCpoUpdApiReqPMsg(NWZC150001Constant.MODE_SAVE, cpoUpdApiReqPMsg, bizMsg, glblMsg, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N); // 2018/01/31 S21_NA#19808
            }

            cntArray[CNT_HEADER] = 1;

            // 2016/02/23 S21_NA#3239 Add Start
            ZYPEZDItemValueSetter.setValue(cpoUpdApiReqPMsg.xxWrnSkipFlg_00, bizMsg.xxWrnSkipFlg_00);
            // 2016/02/23 S21_NA#3239 Add ENd
            // 2016/02/04 S21_NA#3255 Del Start
            // has to be cancelled
//            List<String> configPosList = new ArrayList<String>(0);
//            List<String> lineNumList = new ArrayList<String>(0);

//            if (hasToBeCancelLineConf(bizMsg, configPosList, lineNumList)) {
//                boolean isNormal = callDsCpoUpdateApiWithLineCancel(bizMsg, glblMsg, cpoUpdApiReqPMsg, configPosList, lineNumList);
//                if ("E".equals(bizMsg.getMessageKind()) || !isNormal) {
//                    return false;
//                }
//            }
            // 2016/02/04 S21_NA#3255 Del End

            // 2018/01/31 S21_NA#19808 bizMsg => glblMsg
            // Line Config: Config
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                final NWAL1500_ASMsg configMsg = glblMsg.A.no(i);
                String dsOrdPosnNumLC = configMsg.dsOrdPosnNum_LC.getValue();
                // 2016/08/01 S21_NA#5599 mod Start
                boolean cancelFlg = true;
                if (ZYPCommonFunc.hasValue(configMsg.dsOrdPosnNum_LC)) {
                    for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                        final NWAL1500_BSMsg lineMsg = glblMsg.B.no(j);
                        if (dsOrdPosnNumLC.equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
                            if (!ORD_LINE_STS.CANCELLED.equals(lineMsg.ordLineStsCd_LL.getValue()) //
                                    && !ORD_LINE_STS.CLOSED.equals(lineMsg.ordLineStsCd_LL.getValue())) { // QC#14218 2016/09/16
                                cancelFlg = false;
                                break;
                            }
                        }
                    }
                } else {
                    cancelFlg = false;
                }
                if (cancelFlg) {
                    continue;
                }
                // 2016/08/01 S21_NA#5599 mod End

                String rqstMode = "";
                if (ZYPCommonFunc.hasValue(configMsg.dsCpoConfigPk_LC)) {
                    rqstMode = NWZC150001Constant.RQST_TP_CONFIG_MODIFY;
                } else {
                    rqstMode = NWZC150001Constant.RQST_TP_CONFIG_NEW;
                }
                setConfigUpdApiReqDtlPMsg(rqstMode, cpoUpdApiReqPMsg, bizMsg, configMsg);
                cntArray[CNT_CONFIG] = cntArray[CNT_CONFIG] + 1;

                // Line Config: Line
                for (int lineIdx = 0; lineIdx < glblMsg.B.getValidCount(); lineIdx++) {

                    final NWAL1500_BSMsg lineMsg = glblMsg.B.no(lineIdx);
                    if (!S21StringUtil.isEquals(dsOrdPosnNumLC, lineMsg.dsOrdPosnNum_LL.getValue())) {
                        continue;
                    }
                    if (ORD_LINE_STS.CANCELLED.equals(glblMsg.B.no(lineIdx).ordLineStsCd_LL.getValue())) { // QC#1620
                        continue;
                    }
                    if (ORD_LINE_STS.CLOSED.equals(glblMsg.B.no(lineIdx).ordLineStsCd_LL.getValue())) { // QC#14218 2016/09/16 Add
                        continue;
                    }

                    String lineRqstMode = "";
                    // 2016/02/04 S21_NA#3255 Mod Condition  =>
//                    String chkLineNum = getChekLineNum(lineMsg);
    //
//                    if (lineNumList.contains(chkLineNum) 
//                            || (ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL)
//                                    && stringCancelled(bizMsg.glblCmpyCd.getValue()).equals(lineMsg.ordLineStsDescTxt_LL.getValue()))) {
//                        continue;
                    String lineSts = lineMsg.ordLineStsDescTxt_LL.getValue();
                    boolean hasLineSts = ZYPCommonFunc.hasValue(lineSts);

                    String stsToBeCanceled = bizMsg.varCharConstVal_TB.getValue();
                    boolean isLineToBeCanceled = hasLineSts && stsToBeCanceled.equals(lineSts);
                    String stsCanceled = bizMsg.varCharConstVal_CA.getValue();
                    boolean isLineCanceld = hasLineSts && stsCanceled.equals(lineSts);

                    if (hasLineSts && (isLineToBeCanceled || isLineCanceld)) {
                        lineRqstMode = NWZC150001Constant.RQST_TP_DTL_CANCEL; // 2016/02/04 S21_NA#3255 Mod Condition  <=
                    } else if (!isHdrSaved && ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL)) {
                        lineRqstMode = NWZC150001Constant.RQST_TP_DTL_MODIFY;
                    } else if (!isNew && !isHdrSaved && !ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL)) {
                        lineRqstMode = NWZC150001Constant.RQST_TP_DTL_NEW;
                    } else {
                        lineRqstMode = NWZC150001Constant.RQST_TP_DTL_SAVE;
                    }
                    setCpoUpdApiReqDtlPMsg(lineRqstMode, cpoUpdApiReqPMsg, bizMsg, glblMsg, configMsg, lineMsg, ccyCdMap, inPoundWtMap);
                    if (!NWZC150001Constant.RQST_TP_DTL_CANCEL.equals(lineRqstMode)) {
                        cntArray[CNT_LINE] = cntArray[CNT_LINE] + 1;
                    }
                }
            }

            // RMA: Config
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {

                final NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(i);
                String dsOrdPosnNumRC = rmaConfigMsg.dsOrdPosnNum_RC.getValue();
                // 2016/08/01 S21_NA#5599 mod Start
                boolean cancelFlg = true;
                if (ZYPCommonFunc.hasValue(rmaConfigMsg.dsOrdPosnNum_RC)) {
                    for (int j = 0; j < glblMsg.D.getValidCount(); j++) {
                        final NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(j);
                        if (dsOrdPosnNumRC.equals(rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                            if (!ORD_LINE_STS.CANCELLED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())
                                    && !ORD_LINE_STS.CLOSED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())) {  // 2016/09/06 S21_NA#12435 Add
                                cancelFlg = false;
                                break;
                            }
                        }
                    }
                } else {
                    cancelFlg = false;
                }
                if (cancelFlg) {
                    continue;
                }
                // 2016/08/01 S21_NA#5599 mod End

                String rqstMode = "";
                if (ZYPCommonFunc.hasValue(rmaConfigMsg.dsCpoConfigPk_RC)) {
                    rqstMode = NWZC150001Constant.RQST_TP_CONFIG_MODIFY;
                } else {
                    rqstMode = NWZC150001Constant.RQST_TP_CONFIG_NEW;
                }
                setConfigUpdApiReqDtlPMsg(rqstMode, cpoUpdApiReqPMsg, bizMsg, rmaConfigMsg);
                cntArray[CNT_RMA_CONFIG] = cntArray[CNT_RMA_CONFIG] + 1;

                // RMA: Line
                for (int lineIdx = 0; lineIdx < glblMsg.D.getValidCount(); lineIdx++) {

                    final NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(lineIdx);
                    if (!S21StringUtil.isEquals(dsOrdPosnNumRC, rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                        continue;
                    }
                    if (RTRN_LINE_STS.CANCELLED.equals(glblMsg.D.no(lineIdx).ordLineStsCd_RL.getValue()) // QC#1620
                            || RTRN_LINE_STS.CLOSED.equals(glblMsg.D.no(lineIdx).ordLineStsCd_RL.getValue())) { // 2016/09/06 S21_NA#12435 Add
                        continue;
                    }

                    String lineRqstMode = "";
                    if (ZYPCommonFunc.hasValue(rmaLineMsg.rtrnLineStsDescTxt_RL)
                            && bizMsg.varCharConstVal_TB.getValue().equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue())) { // Cancelled Status
                        lineRqstMode = NWZC150001Constant.RQST_TP_DTL_CANCEL;
                    } else if (!isHdrSaved && ZYPCommonFunc.hasValue(rmaLineMsg.rtrnLineStsDescTxt_RL)) {
                        lineRqstMode = NWZC150001Constant.RQST_TP_DTL_MODIFY;
                    } else if (!isNew && !isHdrSaved && !ZYPCommonFunc.hasValue(rmaLineMsg.rtrnLineStsDescTxt_RL)) {
                        lineRqstMode = NWZC150001Constant.RQST_TP_DTL_NEW;
                    } else {
                        lineRqstMode = NWZC150001Constant.RQST_TP_DTL_SAVE;
                    }
                    setCpoUpdApiReqDtlPMsg(lineRqstMode, cpoUpdApiReqPMsg, bizMsg, glblMsg, rmaConfigMsg, rmaLineMsg, ccyCdMap, inPoundWtMap);
                    cntArray[CNT_RMA_LINE] = cntArray[CNT_RMA_LINE] + 1;
                }
            }
            // 2018/01/31 S21_NA#19808 bizMsg => glblMsg End
        } else {
            // CMN_Submit
            // if (!isNew && !isHdrSaved) {
            if (!isNew) {
                isModified = isModifiedHdr(bizMsg, glblMsg, true);
//                isModified |= isModifiedConfig(bizMsg, glblMsg, true); 2015/12/10 S.Takami Del
//                isModified |= isModifiedRmaLine(bizMsg, glblMsg, true); 2015/12/10 S.Takami Del
                isModified |= isModifiedDtl(bizMsg, glblMsg, true, cpoUpdApiReqPMsg); //  2015/12/10 S.Takami Add. QC#58110 Add Param
            }
            // if (!isModified && !isHdrSaved && !isNew) {
            if (isModified | isNew) {
                // QC#4078 Mod
                setCpoUpdApiReqPMsg(NWZC150001Constant.MODE_SUBMIT, cpoUpdApiReqPMsg, bizMsg, glblMsg, ZYPConstant.FLG_ON_Y, ZYPConstant.FLG_OFF_N); // 6th param, Rebill!!! // 2018/01/31 S21_NA#19808
            } else {
                // QC#4078 Mod
                setCpoUpdApiReqPMsg(NWZC150001Constant.MODE_SUBMIT, cpoUpdApiReqPMsg, bizMsg, glblMsg, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N); // 6th param, Rebill!!! // 2018/01/31 S21_NA#19808

                // S21_NA#17713 Add Start
                if (needsToUpdateSlsCr(bizMsg, glblMsg)) {
                    // update sales credit.
                    callSalesCreditUpdateApi(bizMsg);
                }
                // S21_NA#17713 Add End
            }
            cntArray[CNT_HEADER] = 1;

            // 2018/01/31 S21_NA#19808 bizMsg => glblMsg Start
            if (isNew || isModified || isHdrSaved) {
                for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                    final NWAL1500_ASMsg configMsg = glblMsg.A.no(i);
                    String dsOrdPosnNumLC = configMsg.dsOrdPosnNum_LC.getValue();
                    // 2016/08/01 S21_NA#5599 mod Start
                    boolean cancelFlg = true;
                    if (ZYPCommonFunc.hasValue(configMsg.dsOrdPosnNum_LC)) {
                        for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                            final NWAL1500_BSMsg lineMsg = glblMsg.B.no(j);
                            if (dsOrdPosnNumLC.equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
                                // 2017/03/01 S21_NA#17898 Mod Start
//                                if (!(ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL) && bizMsg.varCharConstVal_CA.getValue().equals(lineMsg.ordLineStsDescTxt_LL.getValue()))
//                                        && !ORD_LINE_STS.CLOSED.equals(lineMsg.ordLineStsCd_LL.getValue())) { // QC#14218 2016/09/16 Add
                                if (!ORD_LINE_STS.CANCELLED.equals(lineMsg.ordLineStsCd_LL.getValue())
                                        && !ORD_LINE_STS.CLOSED.equals(lineMsg.ordLineStsCd_LL.getValue())) {
                                // 2017/03/01 S21_NA#17898 Mod End
                                    cancelFlg = false;
                                    break;
                                }
                            }
                        }
                    } else {
                        cancelFlg = false;
                    }
                    if (cancelFlg) {
                        continue;
                    }
                    // 2016/08/01 S21_NA#5599 mod End

                    String rqstMode = "";
                    if (ZYPCommonFunc.hasValue(configMsg.dsCpoConfigPk_LC)) {
                        rqstMode = NWZC150001Constant.RQST_TP_CONFIG_MODIFY;
                    } else {
                        rqstMode = NWZC150001Constant.RQST_TP_CONFIG_NEW;
                    }
                    setConfigUpdApiReqDtlPMsg(rqstMode, cpoUpdApiReqPMsg, bizMsg, configMsg);
                    cntArray[CNT_CONFIG] = cntArray[CNT_CONFIG] + 1;

                    for (int lineIdx = 0; lineIdx < glblMsg.B.getValidCount(); lineIdx++) {

                        final NWAL1500_BSMsg lineMsg = glblMsg.B.no(lineIdx);
                        if (!S21StringUtil.isEquals(dsOrdPosnNumLC, lineMsg.dsOrdPosnNum_LL.getValue())) {
                            continue;
                        }

                        if (ORD_LINE_STS.CANCELLED.equals(glblMsg.B.no(lineIdx).ordLineStsCd_LL.getValue())) { // S21_NA#14107
                            continue;
                        }

//                        if (ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL) && bizMsg.varCharConstVal_CA.getValue().equals(lineMsg.ordLineStsDescTxt_LL.getValue())) {
//                            continue;
//                        }

                        if (ORD_LINE_STS.CLOSED.equals(glblMsg.B.no(lineIdx).ordLineStsCd_LL.getValue())) { // QC#14218 2016/09/16 Add
                            continue;
                        }

                        String lineRqstMode = "";
                        if (isNew || isHdrSaved) {
                            if (ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL)) {
                                if (bizMsg.varCharConstVal_TB.getValue().equals(lineMsg.ordLineStsDescTxt_LL.getValue())) {
                                    lineRqstMode = NWZC150001Constant.RQST_TP_DTL_CANCEL;
                                } else if (S21StringUtil.isEquals(ORD_LINE_STS.VALIDATED, lineMsg.ordLineStsCd_LL.getValue())) { // 2017/04/21 S21_NA#Review structure Lv.2 Add Start
                                    lineRqstMode = NWZC150001Constant.RQST_TP_DTL_MODIFY;
                                } else {
                                    lineRqstMode = NWZC150001Constant.RQST_TP_DTL_NEW;
                                } // 2017/04/21 S21_NA#Review structure Lv.2 Add End
                            } else {
                                lineRqstMode = NWZC150001Constant.RQST_TP_DTL_NEW;
                            }
                        } else if (bizMsg.varCharConstVal_TB.getValue().equals(lineMsg.ordLineStsDescTxt_LL.getValue())) {
                            lineRqstMode = NWZC150001Constant.RQST_TP_DTL_CANCEL;
                        } else if (!isHdrSaved && ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL)) {
                            lineRqstMode = NWZC150001Constant.RQST_TP_DTL_MODIFY;
                        } else {
                            lineRqstMode = NWZC150001Constant.RQST_TP_DTL_NEW;
                        }

                        setCpoUpdApiReqDtlPMsg(lineRqstMode, cpoUpdApiReqPMsg, bizMsg, glblMsg, configMsg, lineMsg, ccyCdMap, inPoundWtMap);
                        if (!NWZC150001Constant.RQST_TP_DTL_CANCEL.equals(lineRqstMode)) {
                            cntArray[CNT_LINE] = cntArray[CNT_LINE] + 1;
                        }
                    }
                }

                for (int i = 0; i < glblMsg.C.getValidCount(); i++) {

                    final NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(i);
                    String dsOrdPosnNumRC = rmaConfigMsg.dsOrdPosnNum_RC.getValue();
                    // 2016/08/01 S21_NA#5599 mod Start
                    boolean cancelFlg = true;
                    if (ZYPCommonFunc.hasValue(rmaConfigMsg.dsOrdPosnNum_RC)) {
                        for (int j = 0; j < glblMsg.D.getValidCount(); j++) {
                            final NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(j);
                            if (dsOrdPosnNumRC.equals(rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                                // 2017/03/01 S21_NA#17898 Mod Start
//                                if (!(ZYPCommonFunc.hasValue(rmaLineMsg.rtrnLineStsDescTxt_RL) && bizMsg.varCharConstVal_CA.getValue().equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue()))
//                                        || RTRN_LINE_STS.CLOSED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())) { // 2016/09/06 S21_NA#12435 Add
                                if (!RTRN_LINE_STS.CANCELLED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())
                                        && !RTRN_LINE_STS.CLOSED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())) {
                                // 2017/03/01 S21_NA#17898 Mod End
                                    cancelFlg = false;
                                    break;
                                }
                            }
                        }
                    } else {
                        cancelFlg = false;
                    }
                    if (cancelFlg) {
                        continue;
                    }
                    // 2016/08/01 S21_NA#5599 mod End

                    String rqstMode = "";
                    if (ZYPCommonFunc.hasValue(rmaConfigMsg.dsCpoConfigPk_RC)) {
                        rqstMode = NWZC150001Constant.RQST_TP_CONFIG_MODIFY;
                    } else {
                        rqstMode = NWZC150001Constant.RQST_TP_CONFIG_NEW;
                    }
                    setConfigUpdApiReqDtlPMsg(rqstMode, cpoUpdApiReqPMsg, bizMsg, rmaConfigMsg);
                    cntArray[CNT_RMA_CONFIG] = cntArray[CNT_RMA_CONFIG] + 1;

                    for (int lineIdx = 0; lineIdx < glblMsg.D.getValidCount(); lineIdx++) {

                        final NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(lineIdx);
                        if (!S21StringUtil.isEquals(dsOrdPosnNumRC, rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                            continue;
                        }

                        // 2017/03/01 S21_NA#17898 mod Start
                        if (RTRN_LINE_STS.CANCELLED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())) {
                            continue;
                        }

//                        if (bizMsg.varCharConstVal_CA.getValue().equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue())) {
//                            continue;
//                        }
    //
                        if (RTRN_LINE_STS.CLOSED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())) {
                            continue;
                        }

//                        // 2016/09/06 S21_NA#12435 Mod Start
//                        if (ZYPCommonFunc.hasValue(rmaLineMsg.rtrnLineStsDescTxt_RL) && bizMsg.varCharConstVal_CA.getValue().equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue())) {
//                        if ((ZYPCommonFunc.hasValue(rmaLineMsg.rtrnLineStsDescTxt_RL) && bizMsg.varCharConstVal_CA.getValue().equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue()))
//                                || RTRN_LINE_STS.CLOSED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())) {
//                        // 2016/09/06 S21_NA#12435 Mod End
//                            continue;
//                        }

                        // 2017/03/01 S21_NA#17898 mod End

                        String lineRqstMode = "";
                        if (isNew || isHdrSaved) {
                            lineRqstMode = NWZC150001Constant.RQST_TP_DTL_NEW;
                        } else if (bizMsg.varCharConstVal_TB.getValue().equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue())) {
                            lineRqstMode = NWZC150001Constant.RQST_TP_DTL_CANCEL;
                        } else if (!isHdrSaved && ZYPCommonFunc.hasValue(rmaLineMsg.rtrnLineStsDescTxt_RL)) {
                            lineRqstMode = NWZC150001Constant.RQST_TP_DTL_MODIFY;
                        } else {
                            lineRqstMode = NWZC150001Constant.RQST_TP_DTL_NEW;
                        }
                        setCpoUpdApiReqDtlPMsg(lineRqstMode, cpoUpdApiReqPMsg, bizMsg, glblMsg, rmaConfigMsg, rmaLineMsg, ccyCdMap, inPoundWtMap);
                        cntArray[CNT_RMA_LINE] = cntArray[CNT_RMA_LINE] + 1;
                    }
                }
                // 2018/01/31 S21_NA#19808 bizMsg => glblMsg End
            }
        }

        setCpoUpdApiReqSalesCredit(cpoUpdApiReqPMsg, bizMsg, false, null, null);

        setCpoUpdApiReqPriceCalcBase(cpoUpdApiReqPMsg, bizMsg, glblMsg, false, null, null);

        // 2017/05/16 S21_NA#Review structure Lv.2 (Delete Logic For Copy) Mod Start
//        if (isCopyOrd) {
//            // S21_NA#4078 Add Start
//            // CPO Delivery
//            setCpoUpdApiReqDelivery(cpoUpdApiReqPMsg, bizMsg);
//
//            // CPO Install
//            setCpoUpdApiReqInstall(cpoUpdApiReqPMsg, bizMsg);
//
//            // CPO Site Survey
//            setCpoUpdApiReqSiteSurvey(cpoUpdApiReqPMsg, bizMsg);
//
//            // CPO Config Contact Person
//            setCpoUpdApiReqCtacPsn(cpoUpdApiReqPMsg, bizMsg);
//
//            // Shell
//            setCpoUpdApiReqShell(cpoUpdApiReqPMsg, bizMsg);
//            // S21_NA#4078 Add End
//
//        // S21_NA#15889 Add Start
//        } else if (!isCopyOrd && ZYPCommonFunc.hasValue(bizMsg.ordHdrStsDescTxt)) {
//            setCpoUpdApiReqDiscForDelete(cpoUpdApiReqPMsg, bizMsg, glblMsg);
//        }
//        // S21_NA#15889 Add End
        if (ZYPCommonFunc.hasValue(bizMsg.ordHdrStsDescTxt)) {
            // 2018/07/19 S21_NA#27227 Mod Start
            //setCpoUpdApiReqDiscForDelete(cpoUpdApiReqPMsg, bizMsg, glblMsg);

            CPOTMsg cpoTMsg = new CPOTMsg();
            ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, bizMsg.cpoOrdNum);
            cpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(cpoTMsg);

            if (cpoTMsg != null && ZYPConstant.FLG_OFF_N.equals(cpoTMsg.ordBookFlg.getValue())) {
                setCpoUpdApiReqDiscForDelete(cpoUpdApiReqPMsg, bizMsg, glblMsg);
            }
            // 2018/07/19 S21_NA#27227 Mod End
        }
        // 2017/05/16 S21_NA#Review structure Lv.2 (Delete Logic For Copy) Mod End

        // 2018/03/12 S21_NA#20297(Sol#379) Add Start
        setCpoUpdApiReqDelivery(cpoUpdApiReqPMsg, bizMsg, glblMsg);
        // 2018/03/12 S21_NA#20297(Sol#379) Add End

        if ("E".equals(bizMsg.getMessageKind())) {
            return false;
        }

        // 2015/12/25 S21_NA#2463 Mod Start
        boolean callApiFlg = true;
        if (isSave && cpoUpdApiReqPMsg.A.getValidCount() == 0 && cpoUpdApiReqPMsg.rtnDtl.getValidCount() == 0) {
            callApiFlg = false;
        }

        // 2017/04/25 S21_NA#Review structure Lv.2 Add Start
        if (isOnlyHeaderSaving(bizMsg, glblMsg, cpoUpdApiReqPMsg, isHdrSaved)) {
            callApiFlg = true;
        }
        // 2017/04/25 S21_NA#Review structure Lv.2 Add End
        // Call DS CPO Update API
        boolean isNormal = true;
        if (callApiFlg) {
            S21ApplicationCacheHolder.put(NWZC004001.Requester.NWAL0010_ORDER_ENTRY_SCREEN_NEW_ENTRY.toString(), Boolean.TRUE);
            isNormal = callApi(bizMsg, glblMsg, cpoUpdApiReqPMsg, isModifyReq); // 2018/01/31 S21_NA#19808 Mod
            if (isNormal) {
                EZDConnectionMgr.getInstance().commit();
            }
        }
        // 2015/12/25 S21_NA#2463 Mod End

        if ("E".equals(bizMsg.getMessageKind()) || !isNormal) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * call DS CPO Update api as Cancel line
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @return true: normal end false: abnormal end
     * </pre>
     */
    public boolean callDsCpoUpdateApiForLineCancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2018/01/31 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D (without any commnets)
        NWZC150001PMsg cpoUpdApiReqPMsg = new NWZC150001PMsg();

        // 2016/04/11 S21_NA#3236-3 Add Start
        List<NWAL1500_ASMsg> editConfigList = new ArrayList<NWAL1500_ASMsg>();
        List<NWAL1500_BSMsg> editLineList = new ArrayList<NWAL1500_BSMsg>();
        List<NWAL1500_CSMsg> editRmaConfigList = new ArrayList<NWAL1500_CSMsg>();
        List<NWAL1500_DSMsg> editRmaLineList = new ArrayList<NWAL1500_DSMsg>();
        // 2016/04/11 S21_NA#3236-3 Add Start

        if (!ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)
                || isHdrSaved(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue())) {
            bizMsg.setMessageInfo(NWAM0682E);
            return false;
        }

        // QC#4078 Mod
        setCpoUpdApiReqPMsg(NWZC150001Constant.MODE_CANCEL, cpoUpdApiReqPMsg, bizMsg, glblMsg, ZYPConstant.FLG_ON_Y, ZYPConstant.FLG_OFF_N); // 2018/01/31 S21_NA#19808
        cpoUpdApiReqPMsg.xxWrnSkipFlg_00.setValue(ZYPConstant.FLG_ON_Y); // 2016/05/13 S21_NA#7924 Add

        // For Performance QC#8166 Add Start
        Map<String, String> ccyCdMap = new HashMap<String, String>();
        Map<String, BigDecimal> inPoundWtMap = new HashMap<String, BigDecimal>();
        // For Performance QC#8166 Add End

        List<String> trgtPosnNumList = new ArrayList<String>(0);
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            final NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);

            // 2016/04/19 S21_NA#5394 Mod Start
//            if (ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL)) {
//                if (stringToBeCancelled(bizMsg.glblCmpyCd.getValue()).equals(lineMsg.ordLineStsDescTxt_LL.getValue())) {
//                    setCpoUpdApiReqDtlPMsg(NWZC150001Constant.RQST_TP_DTL_CANCEL, cpoUpdApiReqPMsg, bizMsg, lineMsg);
//                    if (!trgtPosnNumList.contains(lineMsg.dsOrdPosnNum_LL.getValue())) {
//                        trgtPosnNumList.add(lineMsg.dsOrdPosnNum_LL.getValue());
//                    }
//                }
//            }
            String ordLineStsDescTxt = lineMsg.ordLineStsDescTxt_LL.getValue();
            boolean isAdd = !ZYPCommonFunc.hasValue(ordLineStsDescTxt);
            boolean isCancel = false;
            boolean isPartialCancel = false;
            // 2017/06/20 S21_NA#19288-2 Add Start
            boolean isExists = false;
            // 2017/06/20 S21_NA#19288-2 Add End
            if (!isAdd) {
                if (bizMsg.varCharConstVal_TB.getValue().equals(ordLineStsDescTxt)) {
                    isCancel = true;
                    setCpoUpdApiReqDtlPMsg(NWZC150001Constant.RQST_TP_DTL_CANCEL, cpoUpdApiReqPMsg, bizMsg, glblMsg, NWAL1500CommonLogic.getParentConfig(glblMsg, lineMsg), lineMsg, ccyCdMap, inPoundWtMap); // 2018/01/31 S21_NA#19808 Mod
                } else if (NWAL1500_CANC_PRTL_CANC.equals(ordLineStsDescTxt)) {
                    isPartialCancel = true;
                } else if (!bizMsg.varCharConstVal_TB.getValue().equals(ordLineStsDescTxt) // 2017/06/20 S21_NA#19288-2 Add Start
                        && !S21StringUtil.isEquals(ORD_LINE_STS.CLOSED, lineMsg.ordLineStsCd_LL.getValue())) {
                    isExists = true;
                } // 2017/06/20 S21_NA#19288-2 Add End
            }
            if (isAdd || isCancel || isPartialCancel || isExists) { // 2017/06/20 S21_NA#19288-2 Add "isExists"
                if (!trgtPosnNumList.contains(lineMsg.dsOrdPosnNum_LL.getValue())) {
                    trgtPosnNumList.add(lineMsg.dsOrdPosnNum_LL.getValue());
                }
            }
            // 2016/04/19 S21_NA#5394 Mod End
        }

        // 2016/04/11 S21_NA#3236-3 Add Start
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
            if (trgtPosnNumList.contains(dsOrdPosnNum) //
                    && !NWAL1500CommonLogic.isCancelLine(bizMsg, lineMsg) //
                    && !S21StringUtil.isEquals(ORD_LINE_STS.CLOSED, lineMsg.ordLineStsCd_LL.getValue())) { // 2016/04/11 S21_NA#3236-3 Add Not Closed
                editLineList.add(lineMsg);
            }
        }
        // 2016/04/11 S21_NA#3236-3 Add End

        for (String trgtPosnNum : trgtPosnNumList) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                final NWAL1500_ASMsg configMsg = glblMsg.A.no(i);

                if (trgtPosnNum.equals(configMsg.dsOrdPosnNum_LC.getValue())) {
                    setConfigUpdApiReqDtlPMsg(NWZC150001Constant.RQST_TP_CONFIG_MODIFY, cpoUpdApiReqPMsg, bizMsg, configMsg);
                    editConfigList.add(configMsg); // 2016/04/11 S21_NA#3236-3 Add
                }
            }
        }

        trgtPosnNumList.clear();
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {

            final NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);

            // 2016/04/19 S21_NA#5394 Mod Start
//            if (stringToBeCancelled(bizMsg.glblCmpyCd.getValue()).equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue())) {
//                setCpoUpdApiReqDtlPMsg(NWZC150001Constant.RQST_TP_DTL_CANCEL, cpoUpdApiReqPMsg, bizMsg, rmaLineMsg);
//                if (!trgtPosnNumList.contains(rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
//                    trgtPosnNumList.add(rmaLineMsg.dsOrdPosnNum_RL.getValue());
//                }
//            }
            String rtrnLineStsDescTxt = rmaLineMsg.rtrnLineStsDescTxt_RL.getValue();
            boolean isAdd = !ZYPCommonFunc.hasValue(rtrnLineStsDescTxt);
            boolean isCancel = false;
            boolean isPartialCancel = false;
            // 2017/06/20 S21_NA#19288-2 Add Start
            boolean isExists = false;
            // 2017/06/20 S21_NA#19288-2 Add End
            if (!isAdd) {
                if (bizMsg.varCharConstVal_TB.getValue().equals(rtrnLineStsDescTxt)) {
                    isCancel = true;
                    setCpoUpdApiReqDtlPMsg(NWZC150001Constant.RQST_TP_DTL_CANCEL, cpoUpdApiReqPMsg, bizMsg, glblMsg, NWAL1500CommonLogic.getParentConfig(glblMsg, rmaLineMsg), rmaLineMsg, ccyCdMap, inPoundWtMap);
                } else if (NWAL1500_CANC_PRTL_CANC.equals(rtrnLineStsDescTxt)) {
                    isPartialCancel = true;
                } else if (!bizMsg.varCharConstVal_TB.getValue().equals(rtrnLineStsDescTxt) // 2017/06/20 S21_NA#19288-2 Add Start
                        && !S21StringUtil.isEquals(RTRN_LINE_STS.CLOSED, rmaLineMsg.ordLineStsCd_RL.getValue())) {
                    isExists = true;
                } // 2017/06/20 S21_NA#19288-2 Add End
            }
            if (isAdd || isCancel || isPartialCancel || isExists) { // 2017/06/20 S21_NA#19288-2 Add "isExists"
                if (!trgtPosnNumList.contains(rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                    trgtPosnNumList.add(rmaLineMsg.dsOrdPosnNum_RL.getValue());
                }
            }
            // 2016/04/19 S21_NA#5394 Mod End
        }

        // 2016/04/11 S21_NA#3236-3 Add Start
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);
            String dsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
            if (trgtPosnNumList.contains(dsOrdPosnNum) //
                    && !NWAL1500CommonLogic.isCancelLine(bizMsg, rmaLineMsg) //
                    && !S21StringUtil.isEquals(RTRN_LINE_STS.CLOSED, rmaLineMsg.ordLineStsCd_RL.getValue())) { // 2016/04/11 S21_NA#3236-3 Add Not Closed
                editRmaLineList.add(rmaLineMsg);
            }
        }
        // 2016/04/11 S21_NA#3236-3 Add End

        for (String trgtPosnNum : trgtPosnNumList) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {

                final NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(i);

                if (trgtPosnNum.equals(rmaConfigMsg.dsOrdPosnNum_RC.getValue())) {
                    setConfigUpdApiReqDtlPMsg(NWZC150001Constant.RQST_TP_CONFIG_MODIFY, cpoUpdApiReqPMsg, bizMsg, rmaConfigMsg);
                    editRmaConfigList.add(rmaConfigMsg); // 2016/04/11 S21_NA#3236-3 Add
                }
            }
        }

        setCpoUpdApiReqSalesCredit(cpoUpdApiReqPMsg, bizMsg, false, null, null);

        setCpoUpdApiReqPriceCalcBase(cpoUpdApiReqPMsg, bizMsg, glblMsg, false, null, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            resetStatusBeforCancelProc(bizMsg, glblMsg); // 2016/04/19 S21_NA#5394 Add
            return false;
        }

        // Call DS CPO Update API
        boolean isNormal = true;
        S21ApplicationCacheHolder.put(NWZC004001.Requester.NWAL0010_ORDER_ENTRY_SCREEN_NEW_ENTRY.toString(), Boolean.TRUE);

        // 2016/04/19 S21_NA#5394 Add Start
        boolean isLineCancelCall = cpoUpdApiReqPMsg.A.getValidCount() > 0;
        boolean isRmaCancelCall = cpoUpdApiReqPMsg.rtnDtl.getValidCount() > 0;
        // 2016/04/19 S21_NA#5394 Add End
        // 2016/04/19 S21_NA#5394 Mod Start
//        isNormal = callApi(bizMsg, cpoUpdApiReqPMsg, true);
        if (isLineCancelCall || isRmaCancelCall) {
            isNormal = callApi(bizMsg, glblMsg, cpoUpdApiReqPMsg, true); // 2018/01/31 S21_NA#19808 Mod
        }
        // 2016/04/19 S21_NA#5394 Mod End
        if (isNormal) {
            // 2017/06/20 S21_NA#19288-2 Add Start
            for (int i = editConfigList.size() -1; i >= 0 ; i--) {
                String dsOrdPosnNum = editConfigList.get(i).dsOrdPosnNum_LC.getValue();
                boolean isContain = false;
                for (int j = 0; j < editLineList.size(); j++) {
                    if (S21StringUtil.isEquals(dsOrdPosnNum, editLineList.get(j).dsOrdPosnNum_LL.getValue())) {
                        isContain = true;
                        break;
                    }
                }
                if (!isContain) {
                    editConfigList.remove(i);
                }
            }

            for (int i = editRmaConfigList.size() -1; i >= 0 ; i--) {
                String dsOrdPosnNum = editRmaConfigList.get(i).dsOrdPosnNum_RC.getValue();
                boolean isContain = false;
                for (int j = 0; j < editRmaLineList.size(); j++) {
                    if (S21StringUtil.isEquals(dsOrdPosnNum, editRmaLineList.get(j).dsOrdPosnNum_RL.getValue())) {
                        isContain = true;
                        break;
                    }
                }
                if (!isContain) {
                    editRmaConfigList.remove(i);
                }
            }
            // 2017/06/20 S21_NA#19288-2 Add End

            // 2016/04/11 S21_NA#3236-3 Add Start
            isNormal = callDsCpoUpdateApiForEditedLines(bizMsg, glblMsg, editConfigList, editLineList, editRmaConfigList, editRmaLineList); // 2018/01/31 S21_NA#19808 Mod
            // 2016/04/11 S21_NA#3236-3 Add Start End
            if (isNormal) { // 2016/04/11 S21_NA#3236-3 Add Condition
                EZDConnectionMgr.getInstance().commit();
            } // 2016/04/11 S21_NA#3236-3 Add Condition
        }

        resetStatusBeforCancelProc(bizMsg, glblMsg); // 2016/04/19 S21_NA#5394 Add

        // 2016/11/02 S21_NA#5394-3 Add Start
        // 2019/08/03 S21_NA#52127 Mod Start
        if (!isNormal && bizMsg.getMessageInfo() == null) {
        // 2019/08/03 S21_NA#52127 Mod End
            bizMsg.setMessageInfo(ZZM9037E);
        }
        // 2016/11/02 S21_NA#5394-3 Add End

        if ("E".equals(bizMsg.getMessageKind()) || !isNormal) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Allocation Order
     * @param bizMsg Business Message
     * @return true: normal End false: abnormal End
     * </pre>
     */
    public boolean bookedOrderAllocation(NWAL1500CMsg bizMsg) {
        CPOTMsg dsCpoTMsg = new CPOTMsg();
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String cpoOrdNum = bizMsg.cpoOrdNum.getValue();

        dsCpoTMsg.glblCmpyCd.setValue(glblCmpyCd);
        dsCpoTMsg.cpoOrdNum.setValue(cpoOrdNum);
        dsCpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(dsCpoTMsg);
        if (null == dsCpoTMsg) {
            return false;
        }
        if (!ZYPConstant.FLG_ON_Y.equals(dsCpoTMsg.ordBookFlg.getValue())) {
            return true;
        }
        S21SsmEZDResult ssmRslt = queryForSaveSubmit.checkFutureRddOrCriticEss(bizMsg);
        boolean isFutureRdd = false;

        // Future RDD?
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> futureRddCriticEssList = (List<Map<String, String>>) ssmRslt.getResultObject();
            for (Map<String, String> futureRddCriticEss : futureRddCriticEssList) {
                String ordCatgCtxTpCd = futureRddCriticEss.get("ORD_CATG_CTX_TP_CD");
                if (ORD_CATG_CTX_TP.DAYS_PRIOR_ALLOCATION_CONTROL_ORDERS.equals(ordCatgCtxTpCd)) {
                    isFutureRdd = true;
                }
            }
        }

        // Get DS CPO Config
        ssmRslt = queryForSaveSubmit.getDsConfigPkForAlloc(glblCmpyCd, cpoOrdNum);
        if (ssmRslt.isCodeNotFound()) {
            // No Out Bound Data
            return true;
        }
        List<Map<String, Object>> dsConfigList = (List<Map<String, Object>>) ssmRslt.getResultObject();
        String slsDt = bizMsg.slsDt.getValue();

//        List<NWZC102001PMsg> criticalEssentialAllocList = new ArrayList<NWZC102001PMsg>();
//        List<NWZC102001PMsg> nonCriticalEssentialAllocList = new ArrayList<NWZC102001PMsg>();
        List<NWZC102001PMsg> allocList = new ArrayList<NWZC102001PMsg>();

        NWZC102001 allocApi = new NWZC102001();

        // S21_NA#12819 Add Start
        VAR_CHAR_CONSTTMsg varCharConstTMsg = new VAR_CHAR_CONSTTMsg();
        setValue(varCharConstTMsg.glblCmpyCd,     glblCmpyCd);
        setValue(varCharConstTMsg.varCharConstNm, NWAL1500Constant.NOT_ALLOC_WH_CD);
        varCharConstTMsg = (VAR_CHAR_CONSTTMsg) S21FastTBLAccessor.findByKey(varCharConstTMsg);

        List<String> whLst = null;
        if (varCharConstTMsg != null) {
            final String varCharConstVal = varCharConstTMsg.varCharConstVal.getValue();
            whLst = asList(varCharConstVal.split(COMMA));
        }
        // S21_NA#12819 Add End

        // S21_NA#12819 Add Start
        Map<String, String> calTpCdCache = new HashMap<String, String>();
        // S21_NA#12819 Add End

        for (Map<String, Object> dsConfigData : dsConfigList) {
            BigDecimal dsCpoConfigPk = (BigDecimal) dsConfigData.get("DS_CPO_CONFIG_PK");
            String billToCustCd = (String)  dsConfigData.get("BILL_TO_CUST_LOC_CD");
            // Cpo Detail Data
            S21SsmEZDResult cpoDtlData = queryForSaveSubmit.getLineAllocInfo(glblCmpyCd, cpoOrdNum, dsCpoConfigPk, whLst); // S21_NA#12819 Mod
            if (!cpoDtlData.isCodeNormal()) {
                continue;
            }
            List<Map<String, Object>> cpoDtlDataList = (List<Map<String, Object>>) cpoDtlData.getResultObject();
//            criticalEssentialAllocList.clear();
//            nonCriticalEssentialAllocList.clear();
            allocList.clear();

            boolean roleBackFlg = false;
            for (Map<String, Object> cpoDtlDataMap : cpoDtlDataList) {
                if (isFutureRdd) {
                    BigDecimal daysPriAllocNum = (BigDecimal) cpoDtlDataMap.get("DAYS_PRI_ALLOC_NUM");
                    String invtyLocCd = (String) cpoDtlDataMap.get("INVTY_LOC_CD"); // 2016/01/26 S21_NA#3522
//                    String estimatedDt = S21CalendarUtil.addDay(slsDt, daysPriAllocNum.intValue()); // 2016/01/26 S21_NA#3522
                    String estimatedDt = calcAllocStartedDay(glblCmpyCd, slsDt, daysPriAllocNum, invtyLocCd, calTpCdCache); // S21_NA#12819 Mod
                    String rdd = (String) cpoDtlDataMap.get("RDD_DT");
                    if (null != rdd) {
                        if (rdd.compareTo(estimatedDt) > 0) {
                            break;
                        }
                    }
                }
                String serNum = (String) cpoDtlDataMap.get("SER_NUM");
                BigDecimal svcMachMstrPkFromCpo = (BigDecimal) cpoDtlDataMap.get("SVC_MACH_MSTR_PK");

                if (ZYPCommonFunc.hasValue(serNum) || ZYPCommonFunc.hasValue(svcMachMstrPkFromCpo)) { // QC#9277 Mod
                    S21SsmEZDResult serAllocRslt = queryForSaveSubmit.getSvcMachMaintFlg(glblCmpyCd, serNum, svcMachMstrPkFromCpo);
                    if (serAllocRslt.isCodeNormal()) {
                        Map<String, Object> rslt = (Map<String, Object>) serAllocRslt.getResultObject();
                        boolean isSerAlloc = ZYPConstant.FLG_OFF_N.equals((String) rslt.get("SVC_MACH_MAINT_AVAL_FLG"));
                        if (isSerAlloc) {
                            EZDConnectionMgr.getInstance().rollback();
                            roleBackFlg = true;
                            break;
                        }
                        // Serial Allocation
                        BigDecimal svcMachMstrPk = (BigDecimal) rslt.get("SVC_MACH_MSTR_PK");
                        boolean mmUpdtApiRslt = callSvcMachMstrUpdApi(glblCmpyCd, slsDt, svcMachMstrPk, cpoDtlDataMap, bizMsg);
                        if (!mmUpdtApiRslt) {
                            EZDConnectionMgr.getInstance().rollback();
                            roleBackFlg = true;
                            break;
                        }
                    } else {
                        // 2016/06/28 S21_NA#10530 Add
                        EZDConnectionMgr.getInstance().rollback();
                        roleBackFlg = true;
                        break;
                    }
                }

                // S21_NA#8196 DEL START    
//                boolean isCriticLine = false;
//                if (isCriticEss) {
//                    if (isCriticEssLine(cpoDtlDataMap)) {
//                        isCriticLine = true;
//                    }
//                } else {
//                    isCriticLine = false;
//                }
                // S21_NA#8196 DEL END  

                cpoDtlDataMap.put("BILL_TO_CUST_LOC_CD", billToCustCd);
                NWZC102001PMsg allocApiPMsg = setAllocApiParam(glblCmpyCd, slsDt, cpoDtlDataMap);
                // S21_NA#8196 MOD START       
                allocList.add(allocApiPMsg);
//                if (isCriticLine) {
//                    criticalEssentialAllocList.add(allocApiPMsg);
//                } else {
//                    nonCriticalEssentialAllocList.add(allocApiPMsg);
//                }
                // S21_NA#8196 MOD END 
            }
            if (!roleBackFlg) {
                // S21_NA#8196 DEL START 
//                // Critical / Essential Allocation
//                if (!criticalEssentialAllocList.isEmpty()) {
//                    allocApi.execute(criticalEssentialAllocList, ONBATCH_TYPE.ONLINE);
//                    if (!checkAllocationError(criticalEssentialAllocList, true)) {
////                    if (!checkAllocationError(criticalEssentialAllocList)) { // 2016/01/26 S21_NA#3520
//                        EZDConnectionMgr.getInstance().rollback();
//                    } else {
//                        EZDConnectionMgr.getInstance().commit();
//                    }
//                }
//                // Non Critical Allocation
//                if (!nonCriticalEssentialAllocList.isEmpty()) {
//                    allocApi.execute(nonCriticalEssentialAllocList, ONBATCH_TYPE.ONLINE);
//                    if (!checkAllocationError(nonCriticalEssentialAllocList, false)) {
////                    if (!checkAllocationError(nonCriticalEssentialAllocList)) { // 2016/01/26 S21_NA#3520
//                        EZDConnectionMgr.getInstance().rollback();
//                    } else {
//                        EZDConnectionMgr.getInstance().commit();
//                    }
//                }
                // S21_NA#8196 DEL END 

                // S21_NA#8196 ADD START   
                if (!allocList.isEmpty()) {
                    allocApi.execute(allocList, ONBATCH_TYPE.ONLINE);
                    if (!checkAllocationError(allocList, cpoDtlDataList)) {
                        EZDConnectionMgr.getInstance().rollback();
                    } else {
                        EZDConnectionMgr.getInstance().commit();
                    }
                }
                // S21_NA#8196 ADD END 
            }
        }
        return true;
    }

    private void setCpoUpdApiReqPMsg(String xxRqstTpCd, NWZC150001PMsg pMsg, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String valUpdtFlg, String sendInvFlg) { // 2018/01/31 S21_NA#19808

        String cpoOrdNum = null;
        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            cpoOrdNum = ZYPCommonFunc.leftPad(bizMsg.cpoOrdNum.getValue(), 6, "0");
        }

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, xxRqstTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, xxRqstTpCd);
        // 2017/02/28 S21_NA#17871 Mod Start
//      ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        // 2018/09/04 S21_NA#27321 Mod Start
        //if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum) && ZYPCommonFunc.hasValue(bizMsg.ordDt_DP)) {
        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum) && ZYPCommonFunc.hasValue(bizMsg.ordDt_DP) && !NWZC150001Constant.MODE_CANCEL.equals(xxRqstTpCd)) {
        // 2018/09/04 S21_NA#27321 Mod End
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.ordDt_DP);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        }
        // 2017/02/28 S21_NA#17871 Mod Start
        ZYPEZDItemValueSetter.setValue(pMsg.usrId, bizMsg.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.bizAppId, BIZ_ID);

        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdRsnCd, bizMsg.dsOrdRsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdTpCd, bizMsg.cpoOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoNum, bizMsg.custIssPoNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoDt, bizMsg.custIssPoDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_ORDER);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoSrcTpCd, bizMsg.cpoSrcTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ordFuflLvlCd, bizMsg.ordFuflLvlCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustAcctCd, bizMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, bizMsg.billToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustAcctCd, bizMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, bizMsg.sellToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.soldToCustLocCd, bizMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(pMsg.adminPsnCd, bizMsg.adminPsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addRddDt, bizMsg.addRddDt);
        pMsg.addRsdDt.clear();
//        ZYPEZDItemValueSetter.setValue(pMsg.payerCustCd, bizMsg.billToCustCd.getValue()); No Payer????
        ZYPEZDItemValueSetter.setValue(pMsg.addShpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(pMsg.addDropShipFlg, bizMsg.dropShipFlg.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCustCd, bizMsg.shipToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToLocNm, bizMsg.shipToLocNm.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToAddlLocNm, bizMsg.shipToAddlLocNm.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToFirstLineAddr, bizMsg.shipToFirstLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToScdLineAddr, bizMsg.shipToScdLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToThirdLineAddr, bizMsg.shipToThirdLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToFrthLineAddr, bizMsg.shipToFrthLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtyAddr, bizMsg.shipToCtyAddr.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToStCd, bizMsg.shipToStCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToProvNm, bizMsg.shipToProvNm.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToPostCd, bizMsg.shipToPostCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtryCd, bizMsg.shipToCtryCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCntyNm, bizMsg.shipToCntyNm.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipTo01RefCmntTxt, bizMsg.shipToFirstRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addShipTo02RefCmntTxt, bizMsg.shipToScdRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.addPmtTermCashDiscCd, bizMsg.pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, bizMsg.carrAcctNum);
        ZYPEZDItemValueSetter.setValue(pMsg.ordSgnDt, bizMsg.ordSgnDt);
//        ZYPEZDItemValueSetter.setValue(pMsg.invRcpntCustCd, bizMsg.billToCustCd.getValue()); No Invoice Recipient????
        ZYPEZDItemValueSetter.setValue(pMsg.slsRepCd, bizMsg.slsRepTocCd);
        // QC#4078 Add Start
//        if (isCopyOrd) {
//            ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.prcBaseDt);
//        } else {
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.slsDt);
//        }
        // QC#4078 Add End
        ZYPEZDItemValueSetter.setValue(pMsg.prcCalcDt, bizMsg.prcCalcDt);
//        pMsg.prcCalcDt.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.negoDealAmt, bizMsg.negoDealAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.prcCatgCd, bizMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.flPrcListCd, bizMsg.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(pMsg.csmpContrNum, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.aquNum, bizMsg.aquNum);
        ZYPEZDItemValueSetter.setValue(pMsg.ordSrcImptDt, bizMsg.ordSrcImptDt);
        ZYPEZDItemValueSetter.setValue(pMsg.ordSrcRefNum, bizMsg.ordSrcRefNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.assnPgmCd, bizMsg.assnPgmCd);
        ZYPEZDItemValueSetter.setValue(pMsg.loanPerDaysAot, bizMsg.loanPerDaysAot);
        ZYPEZDItemValueSetter.setValue(pMsg.leaseCmpyPoNum, bizMsg.leaseCmpyPoNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.mktgPgmNum, bizMsg.mktgPgmNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.leaseEndTermPrchOptCd, bizMsg.leaseEndTermPrchOptCd);
        ZYPEZDItemValueSetter.setValue(pMsg.leasePrchOptCd, bizMsg.leasePrchOptCd);
        // QC#22789 2017/11/28 Mod Start
        // ZYPEZDItemValueSetter.setValue(pMsg.leaseTermMthAot, bizMsg.leaseTermMthAot);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_LD.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.leaseTermMthAot, bizMsg.leaseTermMthAot);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_EM.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.leaseTermMthAot, bizMsg.leaseTermMthAot_EM);
        }
        // QC#22789 2017/11/28 Mod End
        ZYPEZDItemValueSetter.setValue(pMsg.leasePmtFreqCd, bizMsg.leasePmtFreqCd);
        ZYPEZDItemValueSetter.setValue(pMsg.leaseTotPmtAmt, bizMsg.leaseTotPmtAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.ordLogTpCd, bizMsg.ordLogTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.csmpContrNum, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.dlrRefNum, bizMsg.dlrRefNum);
        ZYPEZDItemValueSetter.setValue(pMsg.frtCondCd, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(pMsg.carrSvcLvlCd, bizMsg.carrSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(pMsg.spclHdlgTpCd, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsPmtMethCd, bizMsg.dsPmtMethCd);
        ZYPEZDItemValueSetter.setValue(pMsg.prePmtChkNum, bizMsg.prePmtChkNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prePmtAmt, bizMsg.prePmtAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.prePmtTpCd, bizMsg.prePmtTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.crRebilRsnCatgCd, bizMsg.crRebilRsnCatgCd); // S21_NA#10893 Add
        ZYPEZDItemValueSetter.setValue(pMsg.addOrigCpoOrdNum, bizMsg.addOrigCpoOrdNum); // S21_NA#10893 Add
        ZYPEZDItemValueSetter.setValue(pMsg.reBillPairCpoOrdNum, bizMsg.reBillPairCpoOrdNum); // S21_NA#10893 Add
        // 2022/03/24 QC#59825 Add Start 
        if (NWZC150001Constant.MODE_CANCEL.equals(xxRqstTpCd) && CPO_SRC_TP.REBILL.equals(bizMsg.cpoSrcTpCd.getValue())) {
            pMsg.reBillPairCpoOrdNum.clear();
        }
        // 2022/03/24 QC#59825 Add End 
        // 2016/06/02 S21_NA#9325 Add Start
        if (!S21StringUtil.isEquals(DS_PMT_METH.CREDIT_CARD, bizMsg.dsPmtMethCd.getValue())) {
            bizMsg.dsCrCardPk.clear();
        }
        // 2016/06/02 S21_NA#9325 Add End
        ZYPEZDItemValueSetter.setValue(pMsg.dsCrCardPk, bizMsg.dsCrCardPk); // 2016/06/02 S21_NA#9325 Add
        ZYPEZDItemValueSetter.setValue(pMsg.xxValUpdFlg, valUpdtFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.sendInvFlg, sendInvFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.invCmntTxt, bizMsg.invCmntTxt);
        // 2016/03/07 S21_NA#5000#87 Mod Start
//        // 2016/01/04 S21_NA#2095 Add Start
//        if (ZYPCommonFunc.hasValue(bizMsg.ordDt)) {
//            String ordDt = bizMsg.ordDt.getValue();
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdTs, ordDt);
//        }
//        // 2016/01/04 S21_NA#2095 Add End
        String ordDt = bizMsg.ordDt.getValue();
        if (ZYPCommonFunc.hasValue(bizMsg.ordDt_DP)) {
            ordDt = bizMsg.ordDt_DP.getValue();
        }
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdTs, ordDt);
        // 2016/03/07 S21_NA#5000#87 Mod End

        // QC#5395
        if (NWZC150001Constant.MODE_CANCEL.equals(xxRqstTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.chngRsnTpCd, bizMsg.chngRsnTpCd_P1);
            ZYPEZDItemValueSetter.setValue(pMsg.bizProcCmntTxt, bizMsg.bizProcCmntTxt_P1);
        }

        String dclnSvcChkBoxValHdr = NWAL1500CommonLogic.getDclnSvcChkBoxValHdrFromConfig(bizMsg, glblMsg); // S21_NA#8388 ADD
        ZYPEZDItemValueSetter.setValue(pMsg.dclnSvcCd, NWAL1500CommonLogic.convDclnSvcCdFromChkBox(dclnSvcChkBoxValHdr)); // S21_NA#8388 ADD
        ZYPEZDItemValueSetter.setValue(pMsg.dsCpoPrcInd, bizMsg.dsCpoPrcInd); // 2016/09/05 S21_NA#6523 Add
        ZYPEZDItemValueSetter.setValue(pMsg.xxPgFlg_OE, ZYPConstant.FLG_ON_Y); // 2016/09/23 S21_NA#10321-20 Add
        ZYPEZDItemValueSetter.setValue(pMsg.ordSrcImptTs, bizMsg.ordSrcImptTs); // 2016/09/05 S21_NA#11964 Add
        ZYPEZDItemValueSetter.setValue(pMsg.dsImptOrdPk, bizMsg.dsImptOrdPk); // 2017/03/28 S21_NA#18103 Add

        ZYPEZDItemValueSetter.setValue(pMsg.sellToFirstRefCmntTxt, bizMsg.sellToFirstRefCmntTxt); // 2017/12/12 S21_NA#20164 Add
    }

    private void setConfigUpdApiReqDtlPMsg(String rqstMode, NWZC150001PMsg pMsg, NWAL1500CMsg bizMsg, NWAL1500_ASMsg lineMsg) { // 2018/01/31 S21_NA#19808 Mod

        final int dtlPMsgCount = pMsg.cpoConfig.getValidCount();
        final NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(dtlPMsgCount);


        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, rqstMode);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsCpoConfigPk, lineMsg.dsCpoConfigPk_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, lineMsg.dsOrdPosnNum_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, lineMsg.configTpCd_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.svcConfigMstrPk, lineMsg.svcConfigMstrPk_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlId, lineMsg.mdlId_LC);
//        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlDescTxt, lineMsg.mdlNm_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlDescTxt, lineMsg.mdlDescTxt_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustAcctCd, lineMsg.billToCustAcctCd_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustCd, lineMsg.billToCustCd_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustAcctCd, lineMsg.shipToCustAcctCd_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustCd, lineMsg.shipToCustCd_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dropShipFlg, lineMsg.dropShipFlg_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToLocNm, lineMsg.shipToLocNm_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToAddlLocNm, lineMsg.shipToAddlLocNm_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFirstLineAddr, lineMsg.shipToFirstLineAddr_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToScdLineAddr, lineMsg.shipToScdLineAddr_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToThirdLineAddr, lineMsg.shipToThirdLineAddr_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFrthLineAddr, lineMsg.shipToFrthLineAddr_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo01RefCmntTxt, lineMsg.shipToFirstRefCmntTxt_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo02RefCmntTxt, lineMsg.shipToScdRefCmntTxt_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtyAddr, lineMsg.shipToCtyAddr_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToStCd, lineMsg.shipToStCd_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToProvNm, lineMsg.shipToProvNm_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCntyNm, lineMsg.shipToCntyNm_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToPostCd, lineMsg.shipToPostCd_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtryCd, lineMsg.shipToCtryCd_LC);

        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dclnSvcCd, NWAL1500CommonLogic.convDclnSvcCdFromChkBox(lineMsg.dclnSvcCd_LC.getValue())); // S21_NA#8388 ADD
        // 2018/11/16 S21_NA#27299 Add Start
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToCustLocCd, lineMsg.origShipToCustCd_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToLocNm, lineMsg.origShipToLocNm_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToAddlLocNm, lineMsg.origShipToAddlLocNm_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipFirstLineAddr, lineMsg.origShipFirstLineAddr_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipScdLineAddr, lineMsg.origShipScdLineAddr_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipThirdLineAddr, lineMsg.origShipThirdLineAddr_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipFrthLineAddr, lineMsg.origShipFrthLineAddr_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToCtyAddr, lineMsg.origShipToCtyAddr_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToStCd, lineMsg.origShipToStCd_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToCntyNm, lineMsg.origShipToCntyNm_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToPostCd, lineMsg.origShipToPostCd_LC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToCtryCd, lineMsg.origShipToCtryCd_LC);
        // 2018/11/16 S21_NA#27299 Add End

        pMsg.cpoConfig.setValidCount(dtlPMsgCount + 1);
    }

    private void setConfigUpdApiReqDtlPMsg(String rqstMode, NWZC150001PMsg pMsg, NWAL1500CMsg bizMsg, NWAL1500_CSMsg rmaConfigMsg) {

        final int dtlPMsgCount = pMsg.cpoConfig.getValidCount();
        final NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(dtlPMsgCount);

//        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, rqstMode);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsCpoConfigPk, rmaConfigMsg.dsCpoConfigPk_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, rmaConfigMsg.dsOrdPosnNum_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, rmaConfigMsg.configTpCd_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configCatgCd, CONFIG_CATG.INBOUND);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.svcConfigMstrPk, rmaConfigMsg.svcConfigMstrPk_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlId, rmaConfigMsg.mdlId_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlDescTxt, rmaConfigMsg.mdlDescTxt_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustAcctCd, rmaConfigMsg.billToCustAcctCd_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustCd, rmaConfigMsg.billToCustCd_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustAcctCd, rmaConfigMsg.shipToCustAcctCd_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustCd, rmaConfigMsg.shipToCustCd_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dropShipFlg, rmaConfigMsg.dropShipFlg_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToLocNm, rmaConfigMsg.shipToLocNm_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToAddlLocNm, rmaConfigMsg.shipToAddlLocNm_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFirstLineAddr, rmaConfigMsg.shipToFirstLineAddr_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToScdLineAddr, rmaConfigMsg.shipToScdLineAddr_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToThirdLineAddr, rmaConfigMsg.shipToThirdLineAddr_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFrthLineAddr, rmaConfigMsg.shipToFrthLineAddr_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo01RefCmntTxt, rmaConfigMsg.shipToFirstRefCmntTxt_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo02RefCmntTxt, rmaConfigMsg.shipToScdRefCmntTxt_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtyAddr, rmaConfigMsg.shipToCtyAddr_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToStCd, rmaConfigMsg.shipToStCd_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToProvNm, rmaConfigMsg.shipToProvNm_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCntyNm, rmaConfigMsg.shipToCntyNm_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToPostCd, rmaConfigMsg.shipToPostCd_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtryCd, rmaConfigMsg.shipToCtryCd_RC);
        // 2018/11/16 S21_NA#27299 Add Start
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToCustLocCd, rmaConfigMsg.origShipToCustCd_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToLocNm, rmaConfigMsg.origShipToLocNm_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToAddlLocNm, rmaConfigMsg.origShipToAddlLocNm_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipFirstLineAddr, rmaConfigMsg.origShipFirstLineAddr_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipScdLineAddr, rmaConfigMsg.origShipScdLineAddr_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipThirdLineAddr, rmaConfigMsg.origShipThirdLineAddr_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipFrthLineAddr, rmaConfigMsg.origShipFrthLineAddr_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToCtyAddr, rmaConfigMsg.origShipToCtyAddr_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToStCd, rmaConfigMsg.origShipToStCd_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToCntyNm, rmaConfigMsg.origShipToCntyNm_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToPostCd, rmaConfigMsg.origShipToPostCd_RC);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToCtryCd, rmaConfigMsg.origShipToCtryCd_RC);
        // 2018/11/16 S21_NA#27299 Add End
        pMsg.cpoConfig.setValidCount(dtlPMsgCount + 1);
    }

    private void setCpoUpdApiReqDtlPMsg(String xxRqstTpCd, NWZC150001PMsg pMsg, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg configLineMsg, NWAL1500_BSMsg lineMsg, Map<String, String> ccyCdMap, Map<String, BigDecimal> inPoundWtMap) { // 2018/01/31 S21_NA#19808 Mod

        boolean isDeletProc = isDeleteProc(pMsg); // 2016/09/21 S21_NA#10274 Add

        // 2018/01/31 S21_NA#19808 Del Start
//        String[] lineNums = lineMsg.xxLineNum_LL.getValue().split("\\.", 0);
//        int configLineIdx = 0;
//
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//
//            NWAL1500_ACMsg configLineMsg = bizMsg.A.no(i);
//            if (configLineMsg.dsOrdPosnNum_LC.getValue().equals(lineNums[0])) {
//                configLineIdx = i;
//                break;
//            }
//        }
//
//        // Copy
////        ZYPEZDItemValueSetter.setValue(lineMsg.entDealNetUnitPrcAmt_LL, lineMsg.entCpoDtlDealSlsAmt_LL);
//
//        NWAL1500_ACMsg configLineMsg = bizMsg.A.no(configLineIdx);
        // 2018/01/31 S21_NA#19808 Del End

        // 2019/11/11 S21_NA#54509-1 Mod Start
        boolean isLoanWhWithSbstMdseCdFlg = false;
        List<String> loanWhList = NWAL1500CommonLogic.getVarCharConstDataList(bizMsg.glblCmpyCd.getValue(), LOAN_DUMMY_WH_CD);
        if (loanWhList != null
                && loanWhList.contains(lineMsg.rtlWhCd_LL.getValue())
                && ZYPCommonFunc.hasValue(lineMsg.sbstMdseCd_LL.getValue())) {
            isLoanWhWithSbstMdseCdFlg = true;
        }
        // 2019/11/11 S21_NA#54509-1 Mod End

        final int dtlPMsgCount = pMsg.A.getValidCount();

        final NWZC150001_APMsg dtlPMsg = pMsg.A.no(dtlPMsgCount);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_A1, xxRqstTpCd);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoOrdTpCd_A1, bizMsg.cpoOrdTpCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.pmtTermCashDiscCd_A1, bizMsg.pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, bizMsg.slsRepTocCd);

        // QC#55731 2020/03/27 Mod Start
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.dropShipFlg_A1, configLineMsg.dropShipFlg_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCustCd_A1, configLineMsg.shipToCustCd_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToLocNm_A1, configLineMsg.shipToLocNm_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToAddlLocNm_A1, configLineMsg.shipToAddlLocNm_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstLineAddr_A1, configLineMsg.shipToFirstLineAddr_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdLineAddr_A1, configLineMsg.shipToScdLineAddr_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToThirdLineAddr_A1, configLineMsg.shipToThirdLineAddr_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFrthLineAddr_A1, configLineMsg.shipToFrthLineAddr_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtyAddr_A1, configLineMsg.shipToCtyAddr_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToStCd_A1, configLineMsg.shipToStCd_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToProvNm_A1, configLineMsg.shipToProvNm_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToPostCd_A1, configLineMsg.shipToPostCd_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtryCd_A1, configLineMsg.shipToCtryCd_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCntyNm_A1, configLineMsg.shipToCntyNm_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstRefCmntTxt_A1, configLineMsg.shipToFirstRefCmntTxt_LC);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdRefCmntTxt_A1, configLineMsg.shipToScdRefCmntTxt_LC);
        if (CPO_SRC_TP.INVENTORY_REQUEST_ENTRY.equals(bizMsg.cpoSrcTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dropShipFlg_A1, lineMsg.dropShipFlg_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCustCd_A1, lineMsg.shipToCustCd_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToLocNm_A1, lineMsg.shipToLocNm_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToAddlLocNm_A1, lineMsg.shipToAddlLocNm_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstLineAddr_A1, lineMsg.shipToFirstLineAddr_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdLineAddr_A1, lineMsg.shipToScdLineAddr_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToThirdLineAddr_A1, lineMsg.shipToThirdLineAddr_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFrthLineAddr_A1, lineMsg.shipToFrthLineAddr_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtyAddr_A1, lineMsg.shipToCtyAddr_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToStCd_A1, lineMsg.shipToStCd_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToProvNm_A1, lineMsg.shipToProvNm_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToPostCd_A1, lineMsg.shipToPostCd_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtryCd_A1, lineMsg.shipToCtryCd_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCntyNm_A1, lineMsg.shipToCntyNm_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstRefCmntTxt_A1, lineMsg.shipToFirstRefCmntTxt_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdRefCmntTxt_A1, lineMsg.shipToScdRefCmntTxt_LL);
        } else {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dropShipFlg_A1, configLineMsg.dropShipFlg_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCustCd_A1, configLineMsg.shipToCustCd_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToLocNm_A1, configLineMsg.shipToLocNm_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToAddlLocNm_A1, configLineMsg.shipToAddlLocNm_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstLineAddr_A1, configLineMsg.shipToFirstLineAddr_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdLineAddr_A1, configLineMsg.shipToScdLineAddr_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToThirdLineAddr_A1, configLineMsg.shipToThirdLineAddr_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFrthLineAddr_A1, configLineMsg.shipToFrthLineAddr_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtyAddr_A1, configLineMsg.shipToCtyAddr_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToStCd_A1, configLineMsg.shipToStCd_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToProvNm_A1, configLineMsg.shipToProvNm_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToPostCd_A1, configLineMsg.shipToPostCd_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtryCd_A1, configLineMsg.shipToCtryCd_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCntyNm_A1, configLineMsg.shipToCntyNm_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstRefCmntTxt_A1, configLineMsg.shipToFirstRefCmntTxt_LC);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdRefCmntTxt_A1, configLineMsg.shipToScdRefCmntTxt_LC);
        }
        // QC#55731 2020/03/27 Mod End

        // ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd,
        // lineMsg.invtyLocCd_LL);

//        NWZC150001_priceListPMsg priceList = pMsg.priceList.no(dtlPMsgCount);
//        priceList.cpoDtlLineNum.setValue(lineMsg.cpoDtlLineNum_LL.getValue());
//        priceList.cpoDtlLineSubNum.setValue(lineMsg.cpoDtlLineSubNum_LL.getValue());
//        priceList.prcCondTpCd.setValue("PR01");
//        priceList.prcDtlGrpCd.setValue("BASE");
//        priceList.prcJrnlGrpCd.setValue("BASE");
//        priceList.pkgUomCd.setValue("EA");
//        priceList.prcCondUnitCd.setValue("A");
//        priceList.prcCalcMethCd.setValue("EA");
//        priceList.prcCondManEntryFlg.setValue("Y");
//        priceList.prcCondManAddFlg.setValue("N");
//        priceList.prcCondManDelFlg.setValue("N");
//        priceList.autoPrcAmtRate.setValue(BigDecimal.ZERO);
//        priceList.manPrcAmtRate.setValue(lineMsg.entDealNetUnitPrcAmt_LL.getValue());
//        priceList.calcPrcAmtRate.setValue(lineMsg.entDealNetUnitPrcAmt_LL.getValue().multiply(lineMsg.ordQty_LL.getValue()));
//        priceList.unitPrcAmt.setValue(lineMsg.entDealNetUnitPrcAmt_LL.getValue());
//        priceList.autoPrcCcyCd.setValue(CCY.US_DOLLAR);
//        pMsg.priceList.setValidCount(dtlPMsgCount + 1);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum_A1, lineMsg.dsOrdPosnNum_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_A1, lineMsg.dsOrdLineCatgCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordLineSrcCd_A1, lineMsg.ordLineSrcCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_A1, lineMsg.rtlWhCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlSwhCd_A1, lineMsg.rtlSwhCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.serNum_A1, lineMsg.serNum_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.flPrcListCd_A1, lineMsg.flPrcListCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.entCpoDtlFuncNetAmt, lineMsg.entCpoDtlFuncNetAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotTaxAmt_A1, lineMsg.cpoDtlDealTaxAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dplyLineRefNum_A1, lineMsg.dplyLineRefNum_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.crRebilCd_A1, lineMsg.crRebilCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.prcBaseDt_A1, lineMsg.prcBaseDt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.custUomCd_A1, lineMsg.custUomCd_LL);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineNum_A1, lineMsg.cpoDtlLineNum_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineSubNum_A1, lineMsg.cpoDtlLineSubNum_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoOrdTpCd_A1, lineMsg.cpoOrdTpCd_LL);
        // Add Start 2017/10/05 QC#21356
        // 2019/11/11 S21_NA#54509-1 Mod Start
//        String opeMdseCd = getOpeMdseCd(lineMsg, bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue());
//        ZYPEZDItemValueSetter.setValue(lineMsg.mdseCd_LL, opeMdseCd);
//        // Add End 2017/10/05 QC#21356
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, lineMsg.mdseCd_LL);

        if (!isLoanWhWithSbstMdseCdFlg) {
            String opeMdseCd = getOpeMdseCd(lineMsg, bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.mdseCd_LL, opeMdseCd);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, lineMsg.mdseCd_LL);
        } else {
            CPO_DTLTMsg cpoDtlTMsg = getCpoDtlTMsg(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue(), lineMsg.cpoDtlLineNum_LL.getValue(), lineMsg.cpoDtlLineSubNum_LL.getValue());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, cpoDtlTMsg.mdseCd.getValue());
        }
        // 2019/11/11 S21_NA#54509-1 Mod End

        // S21_NA#14805 2016/09/29 MOD START
        if (ZYPCommonFunc.hasValue(lineMsg.custMdseCd_LL)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_A1, lineMsg.mdseCd_LL);
        } else if (ZYPConstant.FLG_ON_Y.equals(lineMsg.srchOrigItemFlg_LL.getValue())) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_A1, lineMsg.mnfItemCd_LL);
        } else {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_A1, lineMsg.origMdseCd_LL);
        }
        // S21_NA#14805 2016/09/29 MOD END

        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_A1, lineMsg.ordQty_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordCustUomQty_A1, lineMsg.ordCustUomQty_LL);
        String invtyLocCd = lineMsg.rtlWhCd_LL.getValue();
        if (ZYPCommonFunc.hasValue(lineMsg.rtlSwhCd_LL)) {
            invtyLocCd = invtyLocCd + lineMsg.rtlSwhCd_LL.getValue();
        }
        ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_A1, invtyLocCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.entDealNetUnitPrcAmt_A1, lineMsg.entDealNetUnitPrcAmt_LL);

        // For Performance QC#8166 Mod Start
        String ccyCd = getCcyCdFromCache(bizMsg, lineMsg.prcCatgCd_LL.getValue(), ccyCdMap);
        if (ZYPCommonFunc.hasValue(ccyCd)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ccyCd_A1, ccyCd);
        }

        // PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
        // prcCatgTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        // ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, lineMsg.prcCatgCd_LL);
        // prcCatgTMsg = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatgTMsg);
        // String ccyCd = "";
        // if (null != prcCatgTMsg) {
        //     ZYPEZDItemValueSetter.setValue(dtlPMsg.ccyCd_A1, prcCatgTMsg.ccyCd);
        //     if (ZYPCommonFunc.hasValue(prcCatgTMsg.ccyCd)) {
        //         ccyCd = prcCatgTMsg.ccyCd.getValue();
        //     }
        // }
        // For Performance QC#8166 Mod End

        ZYPEZDItemValueSetter.setValue(dtlPMsg.rddDt_A1, lineMsg.rddDt_LL);
        dtlPMsg.rsdDt_A1.clear();
        dtlPMsg.shipCpltCd_A1.clear();
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.uomCpltFlg_A1, lineMsg.uomCpltFlg_LL);
        dtlPMsg.stkStsCd_A1.setValue(STK_STS.GOOD);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.pmtTermCashDiscCd_A1, lineMsg.pmtTermCashDiscCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.frtChrgToCd_A1, lineMsg.frtChrgToCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.frtChrgMethCd_A1, lineMsg.frtChrgMethCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.shpgSvcLvlCd_A1, lineMsg.shpgSvcLvlCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.cashDiscTermCd_A1, lineMsg.cashDiscTermCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, getConfigWriter(configLineMsg, bizMsg.G));
        ZYPEZDItemValueSetter.setValue(dtlPMsg.custMdseCd_A1, lineMsg.custMdseCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.custUomCd_A1, lineMsg.custUomCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.ediStsCd_A1, lineMsg.ediStsCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.ediNum_A1, lineMsg.ediNum_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.manPrcFlg_A1, lineMsg.manPrcFlg_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.carrCd_A1, lineMsg.carrCd_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.carrAcctNum_A1, lineMsg.carrAcctNum_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.setItemShipCpltFlg_A1, lineMsg.setItemShipCpltFlg_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.ediSubNum_A1, lineMsg.ediSubNum_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.custIstlFlg_A1, lineMsg.custIstlFlg_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum_A1, configLineMsg.dsOrdPosnNum_LC);

        // For Performance QC#8166 Mod Start
        BigDecimal inPoundWt = getInPoundWtFromCache(bizMsg, lineMsg.mdseCd_LL, inPoundWtMap);
        if (ZYPCommonFunc.hasValue(inPoundWt) && ZYPCommonFunc.hasValue(lineMsg.ordQty_LL)) { // 2016/09/21 S21_NA#10274 Add lineMsg.ordQty_LL
            ZYPEZDItemValueSetter.setValue(dtlPMsg.unitNetWt_A1, inPoundWt.multiply(lineMsg.ordQty_LL.getValue()));
        } else {
            if (!isDeletProc) { // 2016/09/21 S21_NA#10274 Add
                bizMsg.setMessageInfo(ZZM9037E);
                return;
            } // 2016/09/21 S21_NA#10274 Add
        }

        // BigDecimal unitNetWt = getUnitNetWt(bizMsg, lineMsg);
        // if (null == unitNetWt) {
        //     bizMsg.setMessageInfo(ZZM9037E);
        //     return;
        // }
        // ZYPEZDItemValueSetter.setValue(dtlPMsg.unitNetWt_A1, unitNetWt);
        // For Performance QC#8166 Mod End

        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotBaseAmt_A1, lineMsg.xxTotBaseAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotDiscAmt_A1, lineMsg.xxTotDiscAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclPrcAmt_A1, lineMsg.xxTotSpclPrcAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetDiscAmt_A1, lineMsg.xxTotNetDiscAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetPrcAmt_A1, lineMsg.xxTotNetPrcAmt_LL);
        // ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotFrtAmt_A1, lineMsg.xxTotFrtAmt_LL); // S21_NA#2846
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotFrtAmt_A1, lineMsg.lineDealChrgAmt_LL); // S21_NA#2846
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclChrgAmt_A1, lineMsg.xxTotSpclChrgAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotTaxAmt_A1, lineMsg.xxTotTaxAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxSlsAmt_A1, lineMsg.xxSlsAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotAmt_A1, lineMsg.xxTotAmt_LL);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.vndInvtyLocCd_A1, lineMsg.vndInvtyLocCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.frtCondCd_A1, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsContrNum_A1, lineMsg.dsContrNum_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsContrSqNum_A1, lineMsg.dsContrSqNum_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoConfigPk_A1, lineMsg.dsCpoConfigPk_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoLineNum_A1, lineMsg.dsCpoLineNum_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoLineSubNum_A1, lineMsg.dsCpoLineSubNum_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_A1, lineMsg.dsOrdLineCatgCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordLineSrcCd_A1, lineMsg.ordLineSrcCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_A1, lineMsg.rtlWhCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlSwhCd_A1, lineMsg.rtlSwhCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.serNum_A1, lineMsg.serNum_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.flPrcListCd_A1, lineMsg.flPrcListCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcUnitFlPrcAmt_A1, lineMsg.funcUnitFlPrcAmt_LL); // QC#22372 2018/01/10 Add

        if (!ZYPCommonFunc.hasValue(lineMsg.supdLockFlg_LL)) {
            lineMsg.supdLockFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
        }
        // set dtlPMsg.dealPrcListPrcAmt_A1 and dtlPMsg.funcPrcListPrcAmt_A1
        // String msdId = bizMsg.getMessageKind();
        // 2016/01/28 S21_NA#3254 Mod Start
//        setDealAndFuncPrcListPrcAmt(bizMsg, ccyCd, lineMsg, glblMsg.I, dtlPMsg);
//        msdId = bizMsg.getMessageKind();
//        if ("E".equals(bizMsg.getMessageKind())) {
//            return;
//        }

        // 2018/03/28 S21_NA#24853 Add Start
        boolean isItrlOrd = NWXC150001DsCheck.checkInternalOrder(
                bizMsg.glblCmpyCd.getValue(),
                bizMsg.dsOrdTpCd.getValue(),
                null,
                bizMsg.slsDt.getValue());
        // 2018/03/28 S21_NA#24853 Add End

        String toBeCancelledOrdLineSts = bizMsg.varCharConstVal_TB.getValue();
        String ordLineStsDescTxt = lineMsg.ordLineStsDescTxt_LL.getValue();
        String ordLineStsCd = lineMsg.ordLineStsCd_LL.getValue();
        if (!toBeCancelledOrdLineSts.equals(ordLineStsDescTxt) //
                && !ORD_LINE_STS.CANCELLED.equals(ordLineStsCd) //
                && !isDeletProc // 2016/09/21 S21_NA#10274 Add "!isDeletProc"
                && !isItrlOrd) { // 2018/03/28 S21_NA#24853 Add "!isItrlOrd" 
            setDealAndFuncPrcListPrcAmt(bizMsg, ccyCd, lineMsg, glblMsg.I, dtlPMsg);
            // msdId = bizMsg.getMessageKind();
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
        }
        // 2016/01/28 S21_NA#3254 Mod End

//        String lineRefNum = null; 2015/12/24 S21_NA#2009 Del
        String refCpoDtlLineNum = null;
        String refCpoDtlLineSubNum = null;
        // 2015/12/24 S21_NA#2009 Mod Start
//        if (ZYPCommonFunc.hasValue(lineMsg.dplyLineRefNum_LL)) {
//            lineRefNum = lineMsg.dplyLineRefNum_LL.getValue();
//            String[] devStrArray = lineRefNum.split("\\.");
//            if (devStrArray.length == 2) {
//                refCpoDtlLineNum = devStrArray[1];
//            }
//            if (devStrArray.length == 3) {
//                refCpoDtlLineNum = devStrArray[1];
//                refCpoDtlLineSubNum = devStrArray[2];
//            }
//        }
        // QC#10979 2016/10/27 Del Start
        //List<String> dtlLineNumList = getBaseComponentDtlNum(bizMsg, lineMsg.dsOrdPosnNum_LL.getValue(), CONFIG_CATG.OUTBOUND);
        //if (dtlLineNumList.isEmpty()) {
        //    refCpoDtlLineNum = null;
        //    refCpoDtlLineSubNum = null;
        //} else {
        //    if (!isSameLine(dtlLineNumList.get(0), dtlLineNumList.get(1), lineMsg.cpoDtlLineNum_LL.getValue(), lineMsg.cpoDtlLineSubNum_LL.getValue())) {
        //        refCpoDtlLineNum = dtlLineNumList.get(0);
        //        refCpoDtlLineSubNum = dtlLineNumList.get(1);
        //    } else {
        //        refCpoDtlLineNum = null;
        //        refCpoDtlLineSubNum = null;
        //    }
        //}
        // QC#10979 2016/10/27 Del End

        // 2015/12/24 S21_NA#2009 Mod End
        // 2018/06/01 S21_NA#26334 Add Start
        if (isDeletProc) {
            String lineRefNum = null;
            String dplyPosnNum = null;
            String dplyLineNum = null;
            String dplyLineSubNum = null;
            if (ZYPCommonFunc.hasValue(lineMsg.dplyLineRefNum_LL)) {
                lineRefNum = lineMsg.dplyLineRefNum_LL.getValue();
                String[] devStrArray = lineRefNum.split("\\.");
                if (devStrArray.length == 2) {
                    dplyPosnNum = devStrArray[0];
                    dplyLineNum = devStrArray[1];
                }
                if (devStrArray.length == 3) {
                    dplyPosnNum = devStrArray[0];
                    dplyLineNum = devStrArray[1];
                    dplyLineSubNum = devStrArray[2];
                }
            }
            if (dplyLineNum != null) {
                for (int lineIdx = 0; lineIdx < glblMsg.B.getValidCount(); lineIdx++) {
                    NWAL1500_BSMsg refMsg = glblMsg.B.no(lineIdx);
                    if (dplyPosnNum.equals(refMsg.dsOrdPosnNum_LL.getValue()) && dplyLineNum.equals(refMsg.dsCpoLineNum_LL.getValue().toString())) {
                        if ((dplyLineSubNum == null && !ZYPCommonFunc.hasValue(refMsg.dsCpoLineSubNum_LL)) || 
                                (dplyLineSubNum != null && ZYPCommonFunc.hasValue(refMsg.dsCpoLineSubNum_LL) &&
                                        dplyLineSubNum.equals(refMsg.dsCpoLineSubNum_LL.getValue().toString()))) {
                            refCpoDtlLineNum = refMsg.cpoDtlLineNum_LL.getValue();
                            refCpoDtlLineSubNum = refMsg.cpoDtlLineSubNum_LL.getValue();
                            break;
                        }
                    }
                }
            }
        }
        // 2018/06/01 S21_NA#26334 Add End
        ZYPEZDItemValueSetter.setValue(dtlPMsg.refCpoDtlLineNum_A1, refCpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.refCpoDtlLineSubNum_A1, refCpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dplyLineRefNum_A1, lineMsg.dplyLineRefNum_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.crRebilCd_A1, lineMsg.crRebilCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.prcBaseDt_A1, lineMsg.prcBaseDt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.splyTpCd_A1, lineMsg.splyTpCd_LL);
        // 2019/10/04 S21_NA#51372 Mod Start
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.splyNm_A1, lineMsg.splyNm_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.prntVndNm_A1, lineMsg.prntVndNm_LL);
        // 2019/10/04 S21_NA#51372 Mod End
        ZYPEZDItemValueSetter.setValue(dtlPMsg.splyFirstAddr_A1, lineMsg.splyFirstAddr_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.splyCtyAddr_A1, lineMsg.splyCtyAddr_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.splyStCd_A1, lineMsg.splyStCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.splyPostCd_A1, lineMsg.splyPostCd_LL);
        // QC#22371 2017/12/26 Mod Start
        // S21_NA#2557
        // if (ZYPCommonFunc.hasValue(lineMsg.csmpContrNum_LL)) { 2016/08/31 S21_NA#10535 Del
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpContrNum_A1, lineMsg.csmpContrNum_LL);
//        } else { 2016/08/31 S21_NA#10535 Del Start
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpContrNum_A1, bizMsg.csmpContrNum);
//        }
//        if (ZYPCommonFunc.hasValue(lineMsg.dlrRefNum_LL)) {  2016/08/31 S21_NA#10535 Del End
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.dlrRefNum_A1, lineMsg.dlrRefNum_LL);
//        } else {  2016/08/31 S21_NA#10535 Del Start
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.dlrRefNum_A1, bizMsg.dlrRefNum);
//        }  2016/08/31 S21_NA#10535 Del End
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpPrcListCd_A1, lineMsg.csmpPrcListCd_LL);
        if (!NWAL1500CommonLogic.isCSMPExcl(bizMsg, lineMsg.rtlSwhCd_LL.getValue())) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpContrNum_A1, lineMsg.csmpContrNum_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dlrRefNum_A1, lineMsg.dlrRefNum_LL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpPrcListCd_A1, lineMsg.csmpPrcListCd_LL);
        } else {
            dtlPMsg.csmpContrNum_A1.clear();
            dtlPMsg.dlrRefNum_A1.clear();
            dtlPMsg.csmpPrcListCd_A1.clear();
        }
    // QC#22371 2017/12/26 Mod End
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rntlTrmnDt_A1, lineMsg.rntlTrmnDt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.bllgAttrbCustAcctCd_A1, lineMsg.bllgAttrbCustAcctCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.firstBllgAttrbNm_A1, lineMsg.firstBllgAttrbNm_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.firstBllgAttrbValTxt_A1, lineMsg.firstBllgAttrbValTxt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.scdBllgAttrbNm_A1, lineMsg.scdBllgAttrbNm_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.scdBllgAttrbValTxt_A1, lineMsg.scdBllgAttrbValTxt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.thirdBllgAttrbNm_A1, lineMsg.thirdBllgAttrbNm_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.thirdBllgAttrbValTxt_A1, lineMsg.thirdBllgAttrbValTxt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.frthBllgAttrbNm_A1, lineMsg.frthBllgAttrbNm_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.frthBllgAttrbValTxt_A1, lineMsg.frthBllgAttrbValTxt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.fifthBllgAttrbNm_A1, lineMsg.fifthBllgAttrbNm_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.fifthBllgAttrbValTxt_A1, lineMsg.fifthBllgAttrbValTxt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.sixthBllgAttrbNm_A1, lineMsg.sixthBllgAttrbNm_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.sixthBllgAttrbValTxt_A1, lineMsg.sixthBllgAttrbValTxt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.sbstMdseCd_A1, lineMsg.sbstMdseCd_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineNum_A1, lineMsg.ordSrcRefLineNum_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineSubNum_A1, lineMsg.ordSrcRefLineSubNum_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.carrSvcLvlCd_A1, bizMsg.carrSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.supdLockFlg_A1, lineMsg.supdLockFlg_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.prcListEquipConfigNum_A1, lineMsg.prcListEquipConfigNum_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.prcCatgCd_A1, lineMsg.prcCatgCd_LL);
        // Modify Start 2016/04/15 S21_NA#7099
        //      dtlPMsg.loanBalQty_A1.setValue(BigDecimal.ZERO);
        if (ZYPCommonFunc.hasValue(lineMsg.loanBalQty_LL)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.loanBalQty_A1, lineMsg.loanBalQty_LL);
        } else {
            dtlPMsg.loanBalQty_A1.setValue(BigDecimal.ZERO);
        }
        // Modify End 2016/04/15 S21_NA#7099

        // 2016/05/10 S21_NA#7706 Add Start
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcSvcRevTrnsfAmt_A1, lineMsg.funcSvcRevTrnsfAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealSvcRevTrnsfAmt_A1, lineMsg.dealSvcRevTrnsfAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcSvcCostTrnsfAmt_A1, lineMsg.funcSvcCostTrnsfAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealSvcCostTrnsfAmt_A1, lineMsg.dealSvcCostTrnsfAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcManFlAdjAmt_A1, lineMsg.funcManFlAdjAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealManFlAdjAmt_A1, lineMsg.dealManFlAdjAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcManRepRevAdjAmt_A1, lineMsg.funcManRepRevAdjAmt_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealManRepRevAdjAmt_A1, lineMsg.dealManRepRevAdjAmt_LL);
        // 2016/05/10 S21_NA#7706 Add End
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shopItemFlg_A1, lineMsg.shopItemFlg_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordUpldRefNum_A1, lineMsg.ordUpldRefNum_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.baseCmptFlg_A1, lineMsg.baseCmptFlg_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.trxCd_A1, lineMsg.trxCd_LL);
        // 2016/05/13 Mod Start
//      ZYPEZDItemValueSetter.setValue(dtlPMsg.hardDriveEraseInclFlg_A1, lineMsg.hardDriveEraseInclFlg_LL);
//      ZYPEZDItemValueSetter.setValue(dtlPMsg.hardDriveRmvInclFlg_A1, lineMsg.hardDriveRmvInclFlg_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.hardDriveEraseInclFlg_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.hardDriveRmvInclFlg_A1, ZYPConstant.FLG_OFF_N);
      // 2016/05/13 Mod End
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.hardDriveRmvInclFlg_A1, lineMsg.hardDriveRmvInclFlg_LL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.svcConfigMstrPk_A1, configLineMsg.svcConfigMstrPk_LC);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.pickSvcConfigMstrPk_A1, lineMsg.pickSvcConfigMstrPk_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.configFlg_A1, lineMsg.configFlg_LL);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.svcPreIstlShopFlg_A1, lineMsg.svcPreIstlShopFlg_LL);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoOrdNum_A1, lineMsg.origCpoOrdNum_LL); // S21_NA#7606 Add
        ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoDtlLineNum_A1, lineMsg.origCpoDtlLineNum_LL); // S21_NA#7606 Add
        ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoDtlLineSubNum_A1, lineMsg.origCpoDtlLineSubNum_LL); // S21_NA#7606 Add

        ZYPEZDItemValueSetter.setValue(dtlPMsg.svcMachMstrPk_A1, lineMsg.svcMachMstrPk_LL); // S21_NA#9273 Add

        ZYPEZDItemValueSetter.setValue(dtlPMsg.origInvNum_A1, lineMsg.origInvNum_LL); // S21_NA#10893 Add

        ZYPEZDItemValueSetter.setValue(dtlPMsg.origInvtyLocCd_A1, lineMsg.origInvtyLocCd_LL); // 2017/10/20 S21_NA#21730 Add

        // 2018/08/02 S21_NA#26665 add start
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoSrcTpCd_A1, getCpoSrcTpCd(lineMsg.cpoSrcTpDescTxt_LL.getValue(), bizMsg.glblCmpyCd.getValue())); 
        // 2018/08/02 S21_NA#26665 add end
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordLineStsCd_A1, lineMsg.ordLineStsCd_LL); // 2019/04/22 S21_NA#31185 Add

        // 2019/11/01 S21_NA#54509 Mod Start
//        setSubstituteMdse(lineMsg, dtlPMsg); // 2015/12/24 S21_NA#2123 Add
        // 2019/11/11 S21_NA#54509-1 Mod Start
//        setSubstituteMdse(bizMsg.glblCmpyCd.getValue(), lineMsg, dtlPMsg);
        if (!isLoanWhWithSbstMdseCdFlg) {
            setSubstituteMdse(lineMsg, dtlPMsg);
        }
        // 2019/11/11 S21_NA#54509-1 Mod End
        // 2019/11/01 S21_NA#54509 Mod End

        // 2018/08/02 S21_NA#25404 Add Start
        if (S21StringUtil.isEquals(NWAL1500_CANC_PRTL_CANC, lineMsg.ordLineStsDescTxt_LL.getValue())) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.chngRsnTpCd_A1, bizMsg.chngRsnTpCd_P1);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.bizProcCmntTxt_A1, bizMsg.bizProcCmntTxt_P1);
        }
        // 2018/08/02 S21_NA#25404 Add End

        pMsg.A.setValidCount(dtlPMsgCount + 1);
    }

    private void setCpoUpdApiReqDtlPMsg(String xxRqstTpCd, NWZC150001PMsg pMsg, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_CSMsg rmaConfigLineMsg, NWAL1500_DSMsg rmaLineMsg, Map<String, String> ccyCdMap, Map<String, BigDecimal> inPoundWtMap) { // 2018/01/31 S21_NA#19808 Mod

        boolean isDeletProc = isDeleteProc(pMsg); // 2016/09/21 S21_NA#10274 Add

        // 2018/01/31 S21_NA#19808 Del Start
//        String[] lineNums = rmaLineMsg.xxLineNum_RL.getValue().split("\\.", 0);
//        int configLineIdx = 0;
//
//        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
//
//            NWAL1500_CCMsg rmaConfigLineMsg = bizMsg.C.no(i);
//            if (rmaConfigLineMsg.dsOrdPosnNum_RC.getValue().equals(lineNums[0])) {
//                configLineIdx = i;
//                break;
//            }
//        }
//
//        NWAL1500_CCMsg rmaConfigLineMsg = bizMsg.C.no(configLineIdx);
        // 2018/01/31 S21_NA#19808 Del End

        final int dtlPMsgCount = pMsg.rtnDtl.getValidCount();

        final NWZC150001_rtnDtlPMsg dtlPMsg = pMsg.rtnDtl.no(dtlPMsgCount);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_B1, xxRqstTpCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.configTpCd_B1, rmaConfigLineMsg.configTpCd_RC); // 2016/12/15 S21_NA#15837 Add
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineNum_B1, rmaLineMsg.cpoDtlLineNum_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineSubNum_B1, rmaLineMsg.cpoDtlLineSubNum_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoLineNum_B1, rmaLineMsg.dsCpoLineNum_RL); // 2016/10/07 S21_NA#9102-3 Add
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoLineSubNum_B1, rmaLineMsg.dsCpoLineSubNum_RL); // 2016/10/07 S21_NA#9102-3 Add
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum_B1, rmaLineMsg.dsOrdPosnNum_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoConfigPk_B1, rmaConfigLineMsg.dsCpoConfigPk_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_B1, rmaLineMsg.mdseCd_RL);
        // QC#26415 2018/06/22 Mod Start
        // ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_B1, rmaLineMsg.origMdseCd_RL);
        if (ZYPConstant.FLG_ON_Y.equals(rmaLineMsg.srchOrigItemFlg_RL.getValue())) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_B1, rmaLineMsg.mnfItemCd_RL);
        } else {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_B1, rmaLineMsg.origMdseCd_RL);
        }
        // QC#26415 2018/06/22 Mod End
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dropShipFlg_B1, rmaConfigLineMsg.dropShipFlg_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCustCd_B1, rmaConfigLineMsg.shipToCustCd_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToLocNm_B1, rmaConfigLineMsg.shipToLocNm_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToAddlLocNm_B1, rmaConfigLineMsg.shipToAddlLocNm_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstLineAddr_B1, rmaConfigLineMsg.shipToFirstLineAddr_RC);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdLineAddr_B1, rmaConfigLineMsg.shipToScdLineAddr_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToThirdLineAddr_B1, rmaConfigLineMsg.shipToThirdLineAddr_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFrthLineAddr_B1, rmaConfigLineMsg.shipToFrthLineAddr_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtyAddr_B1, rmaConfigLineMsg.shipToCtyAddr_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToStCd_B1, rmaConfigLineMsg.shipToStCd_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToProvNm_B1, rmaConfigLineMsg.shipToProvNm_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToPostCd_B1, rmaConfigLineMsg.shipToPostCd_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtryCd_B1, rmaConfigLineMsg.shipToCtryCd_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCntyNm_B1, rmaConfigLineMsg.shipToCntyNm_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstRefCmntTxt_B1, rmaConfigLineMsg.shipToFirstRefCmntTxt_RC);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdRefCmntTxt_B1, rmaConfigLineMsg.shipToScdRefCmntTxt_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_B1, rmaLineMsg.ordQty_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordCustUomQty_B1, rmaLineMsg.ordCustUomQty_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.custUomCd_B1, rmaLineMsg.custUomCd_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_B1, rmaLineMsg.dsOrdLineCatgCd_RL);
        String invtyLocCd = rmaLineMsg.rtlWhCd_RL.getValue();
        if (ZYPCommonFunc.hasValue(rmaLineMsg.rtlSwhCd_RL)) {
            invtyLocCd = invtyLocCd + rmaLineMsg.rtlSwhCd_RL.getValue();
        }
        ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_B1, invtyLocCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_B1, rmaLineMsg.rtlWhCd_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlSwhCd_B1, rmaLineMsg.rtlSwhCd_RL);
        dtlPMsg.stkStsCd_B1.setValue(STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.prcBaseDt_B1, rmaLineMsg.prcBaseDt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.prcCatgCd_B1, rmaLineMsg.prcCatgCd_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.flPrcListCd_B1, rmaLineMsg.flPrcListCd_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.entDealNetUnitPrcAmt_B1, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_B1, rmaLineMsg.dealPrcListPrcAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcUnitFlPrcAmt_B1, rmaLineMsg.funcUnitFlPrcAmt_RL); // QC#22372 2018/01/10 Add

        // For Performance QC#8166 Mod Start
        String ccyCd = getCcyCdFromCache(bizMsg, rmaLineMsg.prcCatgCd_RL.getValue(), ccyCdMap);
        if (ZYPCommonFunc.hasValue(ccyCd)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ccyCd_B1, ccyCd);
        }

        // PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
        // prcCatgTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        // prcCatgTMsg.prcCatgCd.setValue(rmaLineMsg.prcCatgCd_RL.getValue());
        // prcCatgTMsg = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatgTMsg);
        // String ccyCd = "";
        // if (null != prcCatgTMsg) {
        //     ZYPEZDItemValueSetter.setValue(dtlPMsg.ccyCd_B1, prcCatgTMsg.ccyCd);
        //     if (ZYPCommonFunc.hasValue(prcCatgTMsg.ccyCd)) {
        //         ccyCd = prcCatgTMsg.ccyCd.getValue();
        //     }
        // }
        // For Performance QC#8166 Mod End

        // set dtlPMsg.dealPrcListPrcAmt_B1 and dtlPMsg.funcPrcListPrcAmt_B1
        // 2016/01/28 S21_NA#3254 Mod Start
//        setDealAndFuncPrcListPrcAmt(bizMsg, ccyCd, rmaLineMsg, glblMsg.I, dtlPMsg);
//        if ("E".equals(bizMsg.getMessageKind())) {
//            return;
//        }

        String toBeCancelledOrdLineSts = bizMsg.varCharConstVal_TB.getValue();
        String ordLineStsDescTxt = rmaLineMsg.rtrnLineStsDescTxt_RL.getValue();
        String ordLineStsCd = rmaLineMsg.ordLineStsCd_RL.getValue();
        if (!toBeCancelledOrdLineSts.equals(ordLineStsDescTxt)
                && !ORD_LINE_STS.CANCELLED.equals(ordLineStsCd) //
                && !isDeletProc) { // 2016/09/21 S21_NA#10274 Add "!isDeletProc"
            setDealAndFuncPrcListPrcAmt(bizMsg, ccyCd, rmaLineMsg, glblMsg.I, dtlPMsg);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
        }
        // 2016/01/28 S21_NA#3254 Mod End

        ZYPEZDItemValueSetter.setValue(dtlPMsg.ccyCd_B1, ccyCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_B1, getConfigWriter(rmaConfigLineMsg, bizMsg.H));

        ZYPEZDItemValueSetter.setValue(dtlPMsg.serNum_B1, rmaLineMsg.serNum_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rqstPickUpDt_B1, rmaLineMsg.rqstPickUpDt_RL);
        dtlPMsg.cpoDtlCancFlg_B1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.pmtTermCd_B1, bizMsg.pmtTermCashDiscCd); // For API, PMT_TERM_CASH_DISC_CD?????

        dtlPMsg.machConfigNum_B1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.svcConfigMstrPk_B1, rmaConfigLineMsg.svcConfigMstrPk_RC);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsContrNum_B1, rmaLineMsg.dsContrNum_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsContrSqNum_B1, rmaLineMsg.dsContrSqNum_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.svcMachMstrPk_B1, rmaLineMsg.svcMachMstrPk_RL);

//        String lineRefNum = null; 2015/12/24 S21_NA#2009 Del
        String refCpoDtlLineNum = null;
        String refCpoDtlLineSubNum = null;
        // 2015/12/24 S21_NA#2009 Mod Start
//        if (ZYPCommonFunc.hasValue(rmaLineMsg.dplyLineRefNum_RL)) {
//            lineRefNum = rmaLineMsg.dplyLineRefNum_RL.getValue();
//            String[] devStrArray = lineRefNum.split("\\.");
//            if (devStrArray.length == 2) {
//                refCpoDtlLineNum = devStrArray[1];
//            }
//            if (devStrArray.length == 3) {
//                refCpoDtlLineNum = devStrArray[1];
//                refCpoDtlLineSubNum = devStrArray[2];
//            }
//        }
        // QC#10979 2016/10/27 Del String
        //List<String> dtlLineNumList = getBaseComponentDtlNum(bizMsg, rmaLineMsg.dsOrdPosnNum_RL.getValue(), CONFIG_CATG.INBOUND);
        //if (dtlLineNumList.isEmpty()) {
        //    refCpoDtlLineNum = null;
        //    refCpoDtlLineSubNum = null;
        //} else {
        //    if (!isSameLine(dtlLineNumList.get(0), dtlLineNumList.get(1), rmaLineMsg.cpoDtlLineNum_RL.getValue(), rmaLineMsg.cpoDtlLineSubNum_RL.getValue())) {
        //        refCpoDtlLineNum = dtlLineNumList.get(0);
        //        refCpoDtlLineSubNum = dtlLineNumList.get(1);
        //    } else {
        //        refCpoDtlLineNum = null;
        //        refCpoDtlLineSubNum = null;
        //    }
        //}
        // QC#10979 2016/10/27 Del End
        // 2015/12/24 S21_NA#2009 Mod End
        ZYPEZDItemValueSetter.setValue(dtlPMsg.refCpoDtlLineNum_B1, refCpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.refCpoDtlLineSubNum_B1, refCpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dplyLineRefNum_B1, rmaLineMsg.dplyLineRefNum_RL);
        // QC#22371 2017/12/26 Mod Start
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpContrNum_B1, rmaLineMsg.csmpContrNum_RL);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.dlrRefNum_B1, rmaLineMsg.dlrRefNum_RL);
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpPrcListCd_B1, rmaLineMsg.csmpPrcListCd_RL);
        if (!NWAL1500CommonLogic.isCSMPExcl(bizMsg, rmaLineMsg.rtlSwhCd_RL.getValue())) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpContrNum_B1, rmaLineMsg.csmpContrNum_RL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dlrRefNum_B1, rmaLineMsg.dlrRefNum_RL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpPrcListCd_B1, rmaLineMsg.csmpPrcListCd_RL);
        } else {
            dtlPMsg.csmpContrNum_B1.clear();
            dtlPMsg.dlrRefNum_B1.clear();
            dtlPMsg.csmpPrcListCd_B1.clear();
        }
        // QC#22371 2017/12/26 Mod End
        ZYPEZDItemValueSetter.setValue(dtlPMsg.hddRmvCd_B1, rmaLineMsg.hddRmvCd_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtrnRsnCd_B1, rmaLineMsg.rtrnRsnCd_RL);
        dtlPMsg.taxFlg_B1.setValue(ZYPConstant.FLG_ON_Y);

        // For Performance QC#8166 Mod Start
        BigDecimal inPoundWt = getInPoundWtFromCache(bizMsg, rmaLineMsg.mdseCd_RL, inPoundWtMap);
        if (ZYPCommonFunc.hasValue(inPoundWt) && ZYPCommonFunc.hasValue(rmaLineMsg.ordQty_RL)) { // 2016/09/21 S21_NA#10274 Add rmaLineMsg.ordQty_RL
            ZYPEZDItemValueSetter.setValue(dtlPMsg.lineNetWt_B1, inPoundWt.multiply(rmaLineMsg.ordQty_RL.getValue()));
        } else {
            if (!isDeletProc) { // 2016/09/21 S21_NA#10274 Add
                bizMsg.setMessageInfo(ZZM9037E);
                return;
            } // 2016/09/21 S21_NA#10274 Add
        }

        // BigDecimal unitNetWt = getUnitNetWt(bizMsg, rmaLineMsg);
        // if (null == unitNetWt) {
        //     bizMsg.setMessageInfo(ZZM9037E);
        //     return;
        // }
        // ZYPEZDItemValueSetter.setValue(dtlPMsg.lineNetWt_B1, unitNetWt);
        // For Performance QC#8166 Mod End

        ZYPEZDItemValueSetter.setValue(dtlPMsg.basePrcAmt_B1, rmaLineMsg.xxTotBaseAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.discPrcAmt_B1, rmaLineMsg.xxTotDiscAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclPrcAmt_B1, rmaLineMsg.xxTotSpclPrcAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetDiscAmt_B1, rmaLineMsg.xxTotNetDiscAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.netAmt_B1, rmaLineMsg.xxTotNetPrcAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.totFrtAmt_B1, rmaLineMsg.xxTotFrtAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclChrgAmt_B1, rmaLineMsg.xxTotSpclChrgAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotTaxAmt_B1, rmaLineMsg.xxTotTaxAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.netAmt_B2, rmaLineMsg.xxSlsAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtrnTotAmt_B1, rmaLineMsg.xxTotAmt_RL);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.bllgAttrbCustAcctCd_B1, rmaLineMsg.bllgAttrbCustAcctCd_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.firstBllgAttrbNm_B1, rmaLineMsg.firstBllgAttrbNm_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.firstBllgAttrbValTxt_B1, rmaLineMsg.firstBllgAttrbValTxt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.scdBllgAttrbNm_B1, rmaLineMsg.scdBllgAttrbNm_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.scdBllgAttrbValTxt_B1, rmaLineMsg.scdBllgAttrbValTxt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.thirdBllgAttrbNm_B1, rmaLineMsg.thirdBllgAttrbNm_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.thirdBllgAttrbValTxt_B1, rmaLineMsg.thirdBllgAttrbValTxt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.frthBllgAttrbNm_B1, rmaLineMsg.frthBllgAttrbNm_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.frthBllgAttrbValTxt_B1, rmaLineMsg.frthBllgAttrbValTxt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.fifthBllgAttrbNm_B1, rmaLineMsg.fifthBllgAttrbNm_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.fifthBllgAttrbValTxt_B1, rmaLineMsg.fifthBllgAttrbValTxt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.sixthBllgAttrbNm_B1, rmaLineMsg.sixthBllgAttrbNm_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.sixthBllgAttrbValTxt_B1, rmaLineMsg.sixthBllgAttrbValTxt_RL);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineNum_B1, rmaLineMsg.ordSrcRefLineNum_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineSubNum_B1, rmaLineMsg.ordSrcRefLineSubNum_RL);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcSvcRevTrnsfAmt_B1, rmaLineMsg.funcSvcRevTrnsfAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealSvcRevTrnsfAmt_B1, rmaLineMsg.dealSvcRevTrnsfAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcSvcCostTrnsfAmt_B1, rmaLineMsg.funcSvcCostTrnsfAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealSvcCostTrnsfAmt_B1, rmaLineMsg.dealSvcCostTrnsfAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcManFlAdjAmt_B1, rmaLineMsg.funcManFlAdjAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealManFlAdjAmt_B1, rmaLineMsg.dealManFlAdjAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcManRepRevAdjAmt_B1, rmaLineMsg.funcManRepRevAdjAmt_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealManRepRevAdjAmt_B1, rmaLineMsg.dealManRepRevAdjAmt_RL);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordUpldRefNum_B1, rmaLineMsg.ordUpldRefNum_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.thirdPtyDspTpCd_B1, rmaLineMsg.thirdPtyDspTpCd_RL);
        // 2019/03/19 S21_NA#30700 Mod Start
        //dtlPMsg.rtrnQty_B1.setValue(BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtrnQty_B1, rmaLineMsg.rtrnQty_RL);
        // 2019/03/19 S21_NA#30700 Mod End
        if (!ZYPCommonFunc.hasValue(rmaLineMsg.baseCmptFlg_RL)) {
            rmaLineMsg.baseCmptFlg_RL.setValue(ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(dtlPMsg.baseCmptFlg_B1, rmaLineMsg.baseCmptFlg_RL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.custMdseCd_B1, rmaLineMsg.custMdseCd_RL);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoOrdNum_B1, rmaLineMsg.origCpoOrdNum_RL); // S21_NA#7606 Add
        ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoDtlLineNum_B1, rmaLineMsg.origCpoDtlLineNum_RL); // S21_NA#7606 Add
        ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoDtlLineSubNum_B1, rmaLineMsg.origCpoDtlLineSubNum_RL); // S21_NA#7606 Add
        ZYPEZDItemValueSetter.setValue(dtlPMsg.origInvNum_B1, rmaLineMsg.origInvNum_RL); // S21_NA#10893 Add
        ZYPEZDItemValueSetter.setValue(dtlPMsg.crRebilCd_B1, rmaLineMsg.crRebilCd_RL); //S21_NA#12685 ADD
        ZYPEZDItemValueSetter.setValue(dtlPMsg.refCpoDtlLineNum_B1, rmaLineMsg.refCpoDtlLineNum_RL); //S21_NA#12685 ADD
        ZYPEZDItemValueSetter.setValue(dtlPMsg.refCpoDtlLineSubNum_B1, rmaLineMsg.refCpoDtlLineSubNum_RL); //S21_NA#12685 ADD
       
        // 2018/08/02 S21_NA#25404 Add Start
        if (S21StringUtil.isEquals(NWAL1500_CANC_PRTL_CANC, rmaLineMsg.rtrnLineStsDescTxt_RL.getValue())) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.chngRsnTpCd_B1, bizMsg.chngRsnTpCd_P1);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.bizProcCmntTxt_B1, bizMsg.bizProcCmntTxt_P1);
        }
        // 2018/08/02 S21_NA#25404 Add End
       
        // 2018/08/02 S21_NA#26665 add start
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoSrcTpCd_B1, getCpoSrcTpCd(rmaLineMsg.cpoSrcTpDescTxt_RL.getValue(), bizMsg.glblCmpyCd.getValue())); 
        // 2018/08/02 S21_NA#26665 add end
        ZYPEZDItemValueSetter.setValue(dtlPMsg.origInvtyLocCd_B1, rmaLineMsg.origInvtyLocCd_RL); // 2018/11/16 S21_NA#27299 Add 
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtrnLineStsCd_B1, rmaLineMsg.ordLineStsCd_RL); // 2019/04/10 S21_NA#31140 Add
        pMsg.rtnDtl.setValidCount(dtlPMsgCount + 1);
    }

    private void setCpoUpdApiReqSalesCredit(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg, boolean cancelChk,  List<String> configPosList, List<String> rmaConfigPosList) {
        int slsCrPMsgCount = cpoUpdApiReqPMsg.cpoSlsCr.getValidCount();
        // Header Data
        int n = 0;
        for (; n < bizMsg.F.getValidCount() && slsCrPMsgCount < cpoUpdApiReqPMsg.cpoSlsCr.length(); n++) {
            NWAL1500_FCMsg fCMsg = bizMsg.F.no(n);
            String xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_NEW;
            if (!ZYPCommonFunc.hasValue(fCMsg.xxRqstTpCd_FS) && ZYPCommonFunc.hasValue(fCMsg.dsCpoSlsCrPk_FS)) {
                xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;
            } else if (NWZC150001Constant.RQST_TP_SLS_CR_NEW.equals(fCMsg.xxRqstTpCd_FS.getValue()) && ZYPCommonFunc.hasValue(fCMsg.dsCpoSlsCrPk_FS)) {
                xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;
            } else if (ZYPCommonFunc.hasValue(fCMsg.xxRqstTpCd_FS)) {
                xxReqTpCd = fCMsg.xxRqstTpCd_FS.getValue();
            }
            NWZC150001_cpoSlsCrPMsg slsCrPMsg = cpoUpdApiReqPMsg.cpoSlsCr.no(slsCrPMsgCount);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.xxRqstTpCd, xxReqTpCd);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoSlsCrPk, fCMsg.dsCpoSlsCrPk_FS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsOrdPosnNum, fCMsg.dsOrdPosnNum_FS);
            slsCrPMsg.configCatgCd.clear();
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoConfigPk, fCMsg.dsCpoConfigPk_FS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd, fCMsg.slsRepTocCd_FS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepRoleTpCd, fCMsg.slsRepRoleTpCd_FS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCrPct, fCMsg.slsRepCrPct_FS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsCrQuotFlg, fCMsg.slsCrQuotFlg_FS);

            slsCrPMsgCount++;
        }
        // Config Line
        for (n = 0; n < bizMsg.G.getValidCount() && slsCrPMsgCount < cpoUpdApiReqPMsg.cpoSlsCr.length(); n++) {
            NWAL1500_GCMsg gCMsg = bizMsg.G.no(n);
            if (cancelChk && !configPosList.contains(gCMsg.dsOrdPosnNum_GS.getValue())) {
                continue;
            }
            String xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_NEW;
            if (!ZYPCommonFunc.hasValue(gCMsg.xxRqstTpCd_GS) && ZYPCommonFunc.hasValue(gCMsg.dsCpoSlsCrPk_GS)) {
                xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;
            } else if (NWZC150001Constant.RQST_TP_SLS_CR_NEW.equals(gCMsg.xxRqstTpCd_GS.getValue()) && ZYPCommonFunc.hasValue(gCMsg.dsCpoSlsCrPk_GS)) {
                xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;
            } else if (ZYPCommonFunc.hasValue(gCMsg.xxRqstTpCd_GS)) {
                xxReqTpCd = gCMsg.xxRqstTpCd_GS.getValue();
            }
            NWZC150001_cpoSlsCrPMsg slsCrPMsg = cpoUpdApiReqPMsg.cpoSlsCr.no(slsCrPMsgCount);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.xxRqstTpCd, xxReqTpCd);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoSlsCrPk, gCMsg.dsCpoSlsCrPk_GS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsOrdPosnNum, gCMsg.dsOrdPosnNum_GS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoConfigPk, gCMsg.dsCpoConfigPk_GS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd, gCMsg.slsRepTocCd_GS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepRoleTpCd, gCMsg.slsRepRoleTpCd_GS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCrPct, gCMsg.slsRepCrPct_GS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsCrQuotFlg, gCMsg.slsCrQuotFlg_GS);

            slsCrPMsgCount++;
        }
        // RMA
        for (n = 0; n < bizMsg.H.getValidCount() && slsCrPMsgCount < cpoUpdApiReqPMsg.cpoSlsCr.length(); n++) {
            NWAL1500_HCMsg hCMsg = bizMsg.H.no(n);
//            if (cancelChk && !rmaConfigPosList.contains(hCMsg.dsOrdPosnNum_HS.getValue())) { is it needed???
//                continue;
//            }
            String xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_NEW;
            if (!ZYPCommonFunc.hasValue(hCMsg.xxRqstTpCd_HS) && ZYPCommonFunc.hasValue(hCMsg.dsCpoSlsCrPk_HS)) {
                xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;
            } else if (NWZC150001Constant.RQST_TP_SLS_CR_NEW.equals(hCMsg.xxRqstTpCd_HS.getValue()) && ZYPCommonFunc.hasValue(hCMsg.dsCpoSlsCrPk_HS)) {
                xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;
            } else if (ZYPCommonFunc.hasValue(hCMsg.xxRqstTpCd_HS)) {
                xxReqTpCd = hCMsg.xxRqstTpCd_HS.getValue();
            }
            NWZC150001_cpoSlsCrPMsg slsCrPMsg = cpoUpdApiReqPMsg.cpoSlsCr.no(slsCrPMsgCount);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.xxRqstTpCd, xxReqTpCd);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoSlsCrPk, hCMsg.dsCpoSlsCrPk_HS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsOrdPosnNum, hCMsg.dsOrdPosnNum_HS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.configCatgCd, CONFIG_CATG.INBOUND);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoConfigPk, hCMsg.dsCpoConfigPk_HS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd, hCMsg.slsRepTocCd_HS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepRoleTpCd, hCMsg.slsRepRoleTpCd_HS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCrPct, hCMsg.slsRepCrPct_HS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsCrQuotFlg, hCMsg.slsCrQuotFlg_HS);

            slsCrPMsgCount++;
        }
        cpoUpdApiReqPMsg.cpoSlsCr.setValidCount(slsCrPMsgCount);
    }

    private void setCpoUpdApiReqPriceCalcBase(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean cancelChk, List<String> lineNumList, List<String> ramLineNumList) {
        int priceListPMsgCount = cpoUpdApiReqPMsg.priceList.getValidCount();
        int rtrPriceListPMsgCount = cpoUpdApiReqPMsg.rtnPriceList.getValidCount();

        // Outbound Price List
        for (int n = 0; n < glblMsg.I.getValidCount() && priceListPMsgCount < cpoUpdApiReqPMsg.priceList.length(); n++) {
            NWZC150001_priceListPMsg priceListPMsg = cpoUpdApiReqPMsg.priceList.no(priceListPMsgCount);
            NWAL1500_ISMsg iCMsg = glblMsg.I.no(n);

            String concatDtlLine = iCMsg.cpoDtlLineNum_LP.getValue() + "." + iCMsg.cpoDtlLineSubNum_LP.getValue();
            if (!CONFIG_CATG.OUTBOUND.equals(iCMsg.configCatgCd_LP.getValue())) {
                continue;
            }
            if (cancelChk && !lineNumList.contains(concatDtlLine)) {
                continue;
            }

            ZYPEZDItemValueSetter.setValue(priceListPMsg.ordPrcCalcBasePk, iCMsg.ordPrcCalcBasePk_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, iCMsg.cpoDtlLineNum_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineSubNum, iCMsg.cpoDtlLineSubNum_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondTpCd, iCMsg.prcCondTpCd_LP);
//            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondTpDescTxt, iCMsg.prcCondTpDescTxt_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcDtlGrpCd, iCMsg.prcDtlGrpCd_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcJrnlGrpCd, iCMsg.prcJrnlGrpCd_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, iCMsg.prcCondManEntryFlg_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManAddFlg, iCMsg.prcCondManAddFlg_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManDelFlg, iCMsg.prcCondManDelFlg_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.pkgUomCd, iCMsg.pkgUomCd_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondUnitCd, iCMsg.prcCondUnitCd_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCalcMethCd, iCMsg.prcCalcMethCd_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcAmtRate, iCMsg.autoPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.maxPrcAmtRate, iCMsg.maxPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.minPrcAmtRate, iCMsg.minPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, iCMsg.manPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.calcPrcAmtRate, iCMsg.calcPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.unitPrcAmt, iCMsg.unitPrcAmt_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.dsMdsePrcPk, iCMsg.dsMdsePrcPk_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.specCondPrcPk, iCMsg.specCondPrcPk_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.frtPerWtPk, iCMsg.frtPerWtPk_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcCcyCd, iCMsg.autoPrcCcyCd_LP);
            // QC#9700  2018/09/03 Add Start
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcRuleApplyFlg, iCMsg.prcRuleApplyFlg_LP);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcRulePrcdPk, iCMsg.prcRulePrcdPk_LP);
            // QC#9700  2018/09/03 Add End

            priceListPMsgCount++;
        }
        cpoUpdApiReqPMsg.priceList.setValidCount(priceListPMsgCount);

        // Inbound Price List
        for (int n = 0; n < glblMsg.I.getValidCount() && rtrPriceListPMsgCount < cpoUpdApiReqPMsg.rtnPriceList.length(); n++) {
            NWZC150001_rtnPriceListPMsg rtnPriceListPMsg = cpoUpdApiReqPMsg.rtnPriceList.no(rtrPriceListPMsgCount);
            NWAL1500_ISMsg iCMsg = glblMsg.I.no(n);

            String concatDtlLine = iCMsg.cpoDtlLineNum_LP.getValue() + "." + iCMsg.cpoDtlLineSubNum_LP.getValue();
            if (!CONFIG_CATG.INBOUND.equals(iCMsg.configCatgCd_LP.getValue())) {
                continue;
            }
            if (cancelChk && !lineNumList.contains(concatDtlLine)) {
                continue;
            }

            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.ordPrcCalcBasePk, iCMsg.ordPrcCalcBasePk_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.cpoDtlLineNum, iCMsg.cpoDtlLineNum_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.cpoDtlLineSubNum, iCMsg.cpoDtlLineSubNum_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcCondTpCd, iCMsg.prcCondTpCd_LP);
//            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondTpDescTxt, iCMsg.prcCondTpDescTxt_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcDtlGrpCd, iCMsg.prcDtlGrpCd_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcJrnlGrpCd, iCMsg.prcJrnlGrpCd_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcCondManEntryFlg, iCMsg.prcCondManEntryFlg_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcCondManAddFlg, iCMsg.prcCondManAddFlg_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcCondManDelFlg, iCMsg.prcCondManDelFlg_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.pkgUomCd, iCMsg.pkgUomCd_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcCondUnitCd, iCMsg.prcCondUnitCd_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcCalcMethCd, iCMsg.prcCalcMethCd_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.autoPrcAmtRate, iCMsg.autoPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.maxPrcAmtRate, iCMsg.maxPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.minPrcAmtRate, iCMsg.minPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.manPrcAmtRate, iCMsg.manPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.calcPrcAmtRate, iCMsg.calcPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.unitPrcAmt, iCMsg.unitPrcAmt_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.dsMdsePrcPk, iCMsg.dsMdsePrcPk_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.specCondPrcPk, iCMsg.specCondPrcPk_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.frtPerWtPk, iCMsg.frtPerWtPk_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.autoPrcCcyCd, iCMsg.autoPrcCcyCd_LP);
            // QC#9700  2018/09/03 Add Start
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcRuleApplyFlg, iCMsg.prcRuleApplyFlg_LP);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcRulePrcdPk, iCMsg.prcRulePrcdPk_LP);
            // QC#9700  2018/09/03 Add End

            rtrPriceListPMsgCount++;
        }
        cpoUpdApiReqPMsg.rtnPriceList.setValidCount(rtrPriceListPMsgCount);
    }

    // 2018/03/12 S21_NA#20297(Sol#379) Add Start
    private void setCpoUpdApiReqDelivery(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        String defShpgCmnt = NWAL1500CommonLogic.getDefaultShippingComment(bizMsg);
        if (!ZYPCommonFunc.hasValue(defShpgCmnt)) {
            return;
        }

        boolean hasCmnt = false;
        List<DS_CPO_DELY_INFOTMsg> delyInfoList = new ArrayList<DS_CPO_DELY_INFOTMsg>();
        if (ZYPCommonFunc.hasValue(bizMsg.ordHdrStsDescTxt)) {

            S21SsmEZDResult ssmRslt = queryForSaveSubmit.getDsCpoDelyInfo(bizMsg);
            delyInfoList = (List<DS_CPO_DELY_INFOTMsg>) ssmRslt.getResultObject();

            for (DS_CPO_DELY_INFOTMsg delyInfo : delyInfoList) {
                hasCmnt |= ZYPCommonFunc.hasValue(delyInfo.delyAddlCmntTxt);
            }
        }

        int n = cpoUpdApiReqPMsg.cpoDlvyInfoList.getValidCount();

        if (!ZYPCommonFunc.hasValue(bizMsg.ordHdrStsDescTxt)
                || (ZYPCommonFunc.hasValue(bizMsg.ordHdrStsDescTxt) && delyInfoList.isEmpty())) {

            NWZC150001_cpoDlvyInfoListPMsg dlvyInfoListPMsg = cpoUpdApiReqPMsg.cpoDlvyInfoList.no(n++);
            ZYPEZDItemValueSetter.setValue(dlvyInfoListPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_NEW);
            ZYPEZDItemValueSetter.setValue(dlvyInfoListPMsg.delyAddlCmntTxt, defShpgCmnt);

        } else if (!delyInfoList.isEmpty()) {

            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.shipToCustAcctNm, glblMsg.shipToCustAcctNm)
                    || !NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.shipToCustAcctCd, glblMsg.shipToCustAcctCd)
                    || !NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.shipToCustCd, glblMsg.shipToCustCd)) {

                for (int i = 0; i < n;) {

                    NWZC150001_cpoDlvyInfoListPMsg dlvyInfoListPMsg = cpoUpdApiReqPMsg.cpoDlvyInfoList.no(i++);

                    // override delete mode to modify mode.
                    ZYPEZDItemValueSetter.setValue(dlvyInfoListPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_MODIFY);
                    ZYPEZDItemValueSetter.setValue(dlvyInfoListPMsg.delyAddlCmntTxt, defShpgCmnt);
                }

            } else if (!hasCmnt) {

                for (DS_CPO_DELY_INFOTMsg delyInfo : delyInfoList) {
                    NWZC150001_cpoDlvyInfoListPMsg dlvyInfoListPMsg = cpoUpdApiReqPMsg.cpoDlvyInfoList.no(n++);
                    ZYPEZDItemValueSetter.setValue(dlvyInfoListPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_MODIFY);
                    ZYPEZDItemValueSetter.setValue(dlvyInfoListPMsg.dsCpoDelyInfoPk, delyInfo.dsCpoDelyInfoPk);
                    ZYPEZDItemValueSetter.setValue(dlvyInfoListPMsg.rddDt, delyInfo.rddDt);
                    ZYPEZDItemValueSetter.setValue(dlvyInfoListPMsg.opsFromHourMn, delyInfo.opsFromHourMn);
                    ZYPEZDItemValueSetter.setValue(dlvyInfoListPMsg.opsToHourMn, delyInfo.opsToHourMn);
                    ZYPEZDItemValueSetter.setValue(dlvyInfoListPMsg.loadDockAvalFlg, delyInfo.loadDockAvalFlg);
                    ZYPEZDItemValueSetter.setValue(dlvyInfoListPMsg.stairCrawReqFlg, delyInfo.stairCrawReqFlg);
                    ZYPEZDItemValueSetter.setValue(dlvyInfoListPMsg.stairCrawNum, delyInfo.stairCrawNum);
                    ZYPEZDItemValueSetter.setValue(dlvyInfoListPMsg.elevAvalFlg, delyInfo.elevAvalFlg);
                    ZYPEZDItemValueSetter.setValue(dlvyInfoListPMsg.delyAddlCmntTxt, defShpgCmnt);
                }
            }
        }

        cpoUpdApiReqPMsg.cpoDlvyInfoList.setValidCount(n);
    }
    // 2018/03/12 S21_NA#20297(Sol#379) Add End

    // 2017/05/16 S21_NA#Review structure Lv.2 (Delete Logic For Copy)
//    // QC#4078 Add Start
//    @SuppressWarnings("unchecked")
//    private void setCpoUpdApiReqDelivery(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg) {
//
//        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getCpoDelyInfoByCpoOrdNum(bizMsg.glblCmpyCd.getValue(), bizMsg.xxSrchNum.getValue());
//        if (!ssmResult.isCodeNormal()) {
//            return;
//        }
//
//        List<Map<String, Object>> cpoDelyInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//        int rsltNum = 0;
//        NWZC150001_cpoDlvyInfoListPMsg pMsg;
//        for (Map<String, Object> cpoDelyInfo : cpoDelyInfoList) {
//            pMsg = cpoUpdApiReqPMsg.cpoDlvyInfoList.no(rsltNum);
//
//            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_NEW);
//            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdPosnNum, (String) cpoDelyInfo.get("DS_ORD_POSN_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.configCatgCd, (String) cpoDelyInfo.get("CONFIG_CATG_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.dsDelyTpCd, (String) cpoDelyInfo.get("DS_DELY_TP_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.rddDt, (String) cpoDelyInfo.get("RDD_DT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.opsFromHourMn, (String) cpoDelyInfo.get("OPS_FROM_HOUR_MN"));
//            ZYPEZDItemValueSetter.setValue(pMsg.opsToHourMn, (String) cpoDelyInfo.get("OPS_TO_HOUR_MN"));
//            ZYPEZDItemValueSetter.setValue(pMsg.loadDockAvalFlg, (String) cpoDelyInfo.get("LOAD_DOCK_AVAL_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.stairCrawReqFlg, (String) cpoDelyInfo.get("STAIR_CRAW_REQ_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.stairCrawNum, (String) cpoDelyInfo.get("STAIR_CRAW_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.elevAvalFlg, (String) cpoDelyInfo.get("ELEV_AVAL_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.delyAddlCmntTxt, (String) cpoDelyInfo.get("DELY_ADDL_CMNT_TXT"));
//            rsltNum++;
//        }
//
//        cpoUpdApiReqPMsg.cpoDlvyInfoList.setValidCount(rsltNum);
//    }
//
//    @SuppressWarnings("unchecked")
//    private void setCpoUpdApiReqInstall(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg) {
//
//        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getCpoIstlInfoByCpoOrdNum(bizMsg.glblCmpyCd.getValue(), bizMsg.xxSrchNum.getValue());
//        if (!ssmResult.isCodeNormal()) {
//            return;
//        }
//
//        List<Map<String, Object>> cpoIstlInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//        int rsltNum = 0;
//        NWZC150001_cpoIstlInfoListPMsg pMsg;
//        for (Map<String, Object> cpoInstlInfo : cpoIstlInfoList) {
//            pMsg = cpoUpdApiReqPMsg.cpoIstlInfoList.no(rsltNum);
//
//            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_NEW);
//            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdPosnNum, (String) cpoInstlInfo.get("DS_ORD_POSN_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.configCatgCd, (String) cpoInstlInfo.get("CONFIG_CATG_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.istlDivCd, (String) cpoInstlInfo.get("ISTL_DIV_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.istlTechCd, (String) cpoInstlInfo.get("ISTL_TECH_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.rqstIstlDt, (String) cpoInstlInfo.get("RQST_ISTL_DT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.rqstIstlTm, (String) cpoInstlInfo.get("RQST_ISTL_TM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.istlAddlCmntTxt, (String) cpoInstlInfo.get("ISTL_ADDL_CMNT_TXT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.svcIstlRuleNum, (String) cpoInstlInfo.get("SVC_ISTL_RULE_NUM"));
//            rsltNum++;
//        }
//
//        cpoUpdApiReqPMsg.cpoIstlInfoList.setValidCount(rsltNum);
//    }
//
//    @SuppressWarnings("unchecked")
//    private void setCpoUpdApiReqSiteSurvey(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg) {
//
//        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getCpoSiteSurveyByCpoOrdNum(bizMsg.glblCmpyCd.getValue(), bizMsg.xxSrchNum.getValue());
//        if (!ssmResult.isCodeNormal()) {
//            return;
//        }
//
//        List<Map<String, Object>> siteSrvInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//        int rsltNum = 0;
//        NWZC150001_siteSrvInfoListPMsg pMsg;
//        for (Map<String, Object> cpoSiteSrv : siteSrvInfoList) {
//            pMsg = cpoUpdApiReqPMsg.siteSrvInfoList.no(rsltNum);
//
//            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_NEW);
//            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdPosnNum, (String) cpoSiteSrv.get("DS_ORD_POSN_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.configCatgCd, (String) cpoSiteSrv.get("CONFIG_CATG_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.cmpyInfoAptBldgNm, (String) cpoSiteSrv.get("CMPY_INFO_APT_BLDG_NM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.cmpyInfoFlNm, (String) cpoSiteSrv.get("CMPY_INFO_FL_NM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.cmpyInfoDeptNm, (String) cpoSiteSrv.get("CMPY_INFO_DEPT_NM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.otsdStepNum, (String) cpoSiteSrv.get("OTSD_STEP_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.insdStepNum, (String) cpoSiteSrv.get("INSD_STEP_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.stairCrawReqFlg, (String) cpoSiteSrv.get("STAIR_CRAW_REQ_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.flgtStairNum, (String) cpoSiteSrv.get("FLGT_STAIR_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.elevAvalFlg, (String) cpoSiteSrv.get("ELEV_AVAL_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.elevAvalFromHourMn, (String) cpoSiteSrv.get("ELEV_AVAL_FROM_HOUR_MN"));
//            ZYPEZDItemValueSetter.setValue(pMsg.elevAvalToHourMn, (String) cpoSiteSrv.get("ELEV_AVAL_TO_HOUR_MN"));
//            ZYPEZDItemValueSetter.setValue(pMsg.elevApptReqFlg, (String) cpoSiteSrv.get("ELEV_APPT_REQ_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.elevCtacTelNum, (String) cpoSiteSrv.get("ELEV_CTAC_TEL_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.elevProtReqFlg, (String) cpoSiteSrv.get("ELEV_PROT_REQ_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.elevWdt, (BigDecimal) cpoSiteSrv.get("ELEV_WDT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.elevDepthNum, (BigDecimal) cpoSiteSrv.get("ELEV_DEPTH_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.elevCtacPsnNm, (String) cpoSiteSrv.get("ELEV_CTAC_PSN_NM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.elevCapWt, (BigDecimal) cpoSiteSrv.get("ELEV_CAP_WT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.elevDoorHgt, (BigDecimal) cpoSiteSrv.get("ELEV_DOOR_HGT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.elevDoorWdt, (BigDecimal) cpoSiteSrv.get("ELEV_DOOR_WDT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.crdrWdt, (BigDecimal) cpoSiteSrv.get("CRDR_WDT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.loadDockAvalFlg, (String) cpoSiteSrv.get("LOAD_DOCK_AVAL_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.loadDockHgt, (BigDecimal) cpoSiteSrv.get("LOAD_DOCK_HGT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.trctrAndTrailAccsFlg, (String) cpoSiteSrv.get("TRCTR_AND_TRAIL_ACCS_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.bldgEntDoorHgt, (BigDecimal) cpoSiteSrv.get("BLDG_ENT_DOOR_HGT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.bldgEntDoorWdt, (BigDecimal) cpoSiteSrv.get("BLDG_ENT_DOOR_WDT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.loadDockAvalFromHourMn, (String) cpoSiteSrv.get("LOAD_DOCK_AVAL_FROM_HOUR_MN"));
//            ZYPEZDItemValueSetter.setValue(pMsg.loadDockAvalToHourMn, (String) cpoSiteSrv.get("LOAD_DOCK_AVAL_TO_HOUR_MN"));
//            ZYPEZDItemValueSetter.setValue(pMsg.carrDelyTmHourMn, (String) cpoSiteSrv.get("CARR_DELY_TM_HOUR_MN"));
//            ZYPEZDItemValueSetter.setValue(pMsg.delyTrnspOptCd, (String) cpoSiteSrv.get("DELY_TRNSP_OPT_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.siteSrvyAddlCmntTxt, (String) cpoSiteSrv.get("SITE_SRVY_ADDL_CMNT_TXT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.stairAndLdgWdt, (BigDecimal) cpoSiteSrv.get("STAIR_AND_LDG_WDT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.doorWdt, (BigDecimal) cpoSiteSrv.get("DOOR_WDT"));
//            rsltNum++;
//        }
//
//        cpoUpdApiReqPMsg.siteSrvInfoList.setValidCount(rsltNum);
//    }
//
//    @SuppressWarnings("unchecked")
//    private void setCpoUpdApiReqCtacPsn(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg) {
//
//        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getCpoCtacPsnByCpoOrdNum(bizMsg.glblCmpyCd.getValue(), bizMsg.xxSrchNum.getValue());
//        if (!ssmResult.isCodeNormal()) {
//            return;
//        }
//
//        List<Map<String, Object>> cpoCtacPsnInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//        int rsltNum = 0;
//        NWZC150001_cpoCtacPsnInfoListPMsg pMsg;
//        for (Map<String, Object> cpoCtacPsn : cpoCtacPsnInfoList) {
//            pMsg = cpoUpdApiReqPMsg.cpoCtacPsnInfoList.no(rsltNum);
//
//            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_NEW);
//            ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnPk, (BigDecimal) cpoCtacPsn.get("CTAC_PSN_PK"));
//            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdPosnNum, (String) cpoCtacPsn.get("DS_ORD_POSN_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.configCatgCd, (String) cpoCtacPsn.get("CONFIG_CATG_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.ctacTpCd, (String) cpoCtacPsn.get("CTAC_PSN_TP_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.firstNm, (String) cpoCtacPsn.get("CTAC_PSN_FIRST_NM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.lastNm, (String) cpoCtacPsn.get("CTAC_PSN_LAST_NM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.emlAddr, (String) cpoCtacPsn.get("CTAC_PSN_EML_ADDR"));
//            ZYPEZDItemValueSetter.setValue(pMsg.telNum, (String) cpoCtacPsn.get("CTAC_PSN_TEL_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.faxNum, (String) cpoCtacPsn.get("CTAC_PSN_FAX_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.sortNum, (BigDecimal) cpoCtacPsn.get("CTAC_PSN_SORT_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnExtnNum, (String) cpoCtacPsn.get("CTAC_PSN_EXTN_NUM"));
//
//            rsltNum++;
//        }
//
//        cpoUpdApiReqPMsg.cpoCtacPsnInfoList.setValidCount(rsltNum);
//    }
//
//    private void setCpoUpdApiReqShell(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg) {
//
//        BigDecimal cpoSvcPk = getCpoSvcPkByCpoOrdNum(bizMsg);
//        if (ZYPCommonFunc.hasValue(cpoSvcPk)) {
//
//            setCpoUpdApiReqCpoSvcDtlForShell(cpoUpdApiReqPMsg, bizMsg, cpoSvcPk);
//
//            setCpoSvcPrcForShell(cpoUpdApiReqPMsg, bizMsg, cpoSvcPk);
//
//            setCpoUpdApiReqCpoSvcConfigRefForShell(cpoUpdApiReqPMsg, bizMsg, cpoSvcPk);
//
//            setCpoUpdApiReqCpoSvcUsgPrcForShell(cpoUpdApiReqPMsg, bizMsg, cpoSvcPk);
//
//            setCpoUpdApiReqCpoSvcAddlBasePrcForShell(cpoUpdApiReqPMsg, bizMsg, cpoSvcPk);
//
//            setCpoUpdApiReqCpoSvcAddlChrgPrcForShell(cpoUpdApiReqPMsg, bizMsg, cpoSvcPk);
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    private BigDecimal getCpoSvcPkByCpoOrdNum(NWAL1500CMsg bizMsg) {
//        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getCpoSvcPkByCpoOrdNum(bizMsg.glblCmpyCd.getValue(), bizMsg.xxSrchNum.getValue());
//
//        return (BigDecimal) ssmResult.getResultObject();
//    }
//
//    private void setCpoUpdApiReqCpoSvcDtlForShell(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg, BigDecimal cpoSvcPk) {
//
//        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getCpoSvcDtlForShell(bizMsg.glblCmpyCd.getValue(), cpoSvcPk);
//        if (!ssmResult.isCodeNormal()) {
//            return;
//        }
//
//        List<Map<String, Object>> cpoSvcDtlInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//        int rsltNum = 0;
//        NWZC150001_cpoSvcDtlPMsg pMsg;
//        for (Map<String, Object> cpoDelyInfo : cpoSvcDtlInfoList) {
//            pMsg = cpoUpdApiReqPMsg.cpoSvcDtl.no(rsltNum);
//
//            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_NEW);
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoSvcLineNum, (BigDecimal) cpoDelyInfo.get("CPO_SVC_LINE_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoSvcMdseCd, (String) cpoDelyInfo.get("CPO_SVC_MDSE_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.prcSvcContrTpCd, (String) cpoDelyInfo.get("PRC_SVC_CONTR_TP_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.prcSvcPlnTpCd, (String) cpoDelyInfo.get("PRC_SVC_PLN_TP_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.dsContrCatgCd, (String) cpoDelyInfo.get("DS_CONTR_CATG_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.baseBllgCycleCd, (String) cpoDelyInfo.get("BASE_BLLG_CYCLE_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.usgBllgCycleCd, (String) cpoDelyInfo.get("USG_BLLG_CYCLE_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.fromPerMthNum, (BigDecimal) cpoDelyInfo.get("FROM_PER_MTH_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.toPerMthNum, (BigDecimal) cpoDelyInfo.get("TO_PER_MTH_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.termMthNum, (BigDecimal) cpoDelyInfo.get("TERM_MTH_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.billWithEquipFlg, (String) cpoDelyInfo.get("BILL_WITH_EQUIP_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.billByTpCd, (String) cpoDelyInfo.get("BILL_BY_TP_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.soldToCustLocCd, (String) cpoDelyInfo.get("SOLD_TO_CUST_LOC_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, (String) cpoDelyInfo.get("SELL_TO_CUST_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoSvcAgmtVerNum, (String) cpoDelyInfo.get("CPO_SVC_AGMT_VER_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.manContrOvrdFlg, (String) cpoDelyInfo.get("MAN_CONTR_OVRD_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.manContrOvrdRsnCd, (String) cpoDelyInfo.get("MAN_CONTR_OVRD_RSN_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.manContrOvrdCmntTxt, (String) cpoDelyInfo.get("MAN_CONTR_OVRD_CMNT_TXT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, (BigDecimal) cpoDelyInfo.get("DS_CONTR_PK"));
//            ZYPEZDItemValueSetter.setValue(pMsg.svcPrcCatgCd, (String) cpoDelyInfo.get("SVC_PRC_CATG_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.billWithEquipInvdFlg, (String) cpoDelyInfo.get("BILL_WITH_EQUIP_INVD_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.crRebilCd, (String) cpoDelyInfo.get("CR_REBIL_CD"));
//            // 2016/04/18 S21_NA#5832 Mod Start
//            // ZYPEZDItemValueSetter.setValue(pMsg.contrCratFlg, (String) cpoDelyInfo.get("CONTR_CRAT_FLG"));
//            pMsg.contrCratFlg.setValue(ZYPConstant.FLG_OFF_N);
//            // 2016/04/18 S21_NA#5832 Mod End
//            ZYPEZDItemValueSetter.setValue(pMsg.applyEquipBillToFlg, (String) cpoDelyInfo.get("APPLY_EQUIP_BILL_TO_FLG"));
//            rsltNum++;
//        }
//
//        cpoUpdApiReqPMsg.cpoSvcDtl.setValidCount(rsltNum);
//    }
//
//    @SuppressWarnings("unchecked")
//    private void setCpoSvcPrcForShell(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg, BigDecimal cpoSvcPk) {
//
//        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getCpoSvcPrcForShell(bizMsg.glblCmpyCd.getValue(), cpoSvcPk);
//        if (!ssmResult.isCodeNormal()) {
//            return;
//        }
//
//        List<Map<String, Object>> cpoSvcPrcInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//        int rsltNum = 0;
//        NWZC150001_cpoSvcPrcPMsg pMsg;
//        for (Map<String, Object> cpoSvcPrc : cpoSvcPrcInfoList) {
//            pMsg = cpoUpdApiReqPMsg.cpoSvcPrc.no(rsltNum);
//
//            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_NEW);
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoSvcLineNum, (BigDecimal) cpoSvcPrc.get("CPO_SVC_LINE_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.ordLinePosnNum, (BigDecimal) cpoSvcPrc.get("CPO_SVC_LINE_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.mdlId, (BigDecimal) cpoSvcPrc.get("MDL_ID"));
//            ZYPEZDItemValueSetter.setValue(pMsg.maintPrcCatgCd, (String) cpoSvcPrc.get("MAINT_PRC_CATG_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.maintFlPrcCatgCd, (String) cpoSvcPrc.get("MAINT_FL_PRC_CATG_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.prcMtrPkgPk, (BigDecimal) cpoSvcPrc.get("PRC_MTR_PKG_PK"));
//            ZYPEZDItemValueSetter.setValue(pMsg.basePrcDealAmt, (BigDecimal) cpoSvcPrc.get("BASE_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.basePrcFuncAmt, (BigDecimal) cpoSvcPrc.get("BASE_PRC_FUNC_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.dealPrcListPrcAmt, (BigDecimal) cpoSvcPrc.get("DEAL_PRC_LIST_PRC_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.funcPrcListPrcAmt, (BigDecimal) cpoSvcPrc.get("FUNC_PRC_LIST_PRC_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.prcTierSvcOfferCd, (String) cpoSvcPrc.get("PRC_TIER_SVC_OFFER_CD"));
//            rsltNum++;
//        }
//
//        cpoUpdApiReqPMsg.cpoSvcPrc.setValidCount(rsltNum);
//    }
//
//
//    @SuppressWarnings("unchecked")
//    private void setCpoUpdApiReqCpoSvcConfigRefForShell(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg, BigDecimal cpoSvcPk) {
//
//        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getCpoSvcConfigRefForShell(bizMsg.glblCmpyCd.getValue(), cpoSvcPk);
//        if (!ssmResult.isCodeNormal()) {
//            return;
//        }
//
//        List<Map<String, Object>> cpoSvcConfigRefInfo = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//        int rsltNum = 0;
//        NWZC150001_cpoSvcConfigRefPMsg pMsg;
//        for (Map<String, Object> cpoSvcConfigRef : cpoSvcConfigRefInfo) {
//            pMsg = cpoUpdApiReqPMsg.cpoSvcConfigRef.no(rsltNum);
//
//            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_NEW);
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoSvcLineNum, (BigDecimal) cpoSvcConfigRef.get("CPO_SVC_LINE_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.ordLinePosnNum, new BigDecimal((String) cpoSvcConfigRef.get("DS_ORD_POSN_NUM")));
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum, (String) cpoSvcConfigRef.get("CPO_DTL_LINE_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum, (String) cpoSvcConfigRef.get("CPO_DTL_LINE_SUB_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, (BigDecimal) cpoSvcConfigRef.get("SVC_CONFIG_MSTR_PK"));
//            ZYPEZDItemValueSetter.setValue(pMsg.custIssPoNum, (String) cpoSvcConfigRef.get("CUST_ISS_PO_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.custIssPoDt, (String) cpoSvcConfigRef.get("CUST_ISS_PO_DT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.mtrReadMethCd, (String) cpoSvcConfigRef.get("MTR_READ_METH_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.svcPrcCatgCd, (String) cpoSvcConfigRef.get("SVC_PRC_CATG_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.billWithEquipInvdFlg, (String) cpoSvcConfigRef.get("BILL_WITH_EQUIP_INVD_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.crRebilCd, (String) cpoSvcConfigRef.get("CR_REBIL_CD"));
//            // 2016/04/18 S21_NA#5832 Mod Start
////            ZYPEZDItemValueSetter.setValue(pMsg.contrCratFlg, (String) cpoSvcConfigRef.get("CONTR_CRAT_FLG"));
//            pMsg.contrCratFlg.setValue(ZYPConstant.FLG_OFF_N);
//            // 2016/04/18 S21_NA#5832 Mod Start
//            rsltNum++;
//        }
//
//        cpoUpdApiReqPMsg.cpoSvcConfigRef.setValidCount(rsltNum);
//    }
//
//    @SuppressWarnings("unchecked")
//    private void setCpoUpdApiReqCpoSvcUsgPrcForShell(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg, BigDecimal cpoSvcPk) {
//
//        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getCpoSvcUsgPrcForShell(bizMsg.glblCmpyCd.getValue(), cpoSvcPk);
//        if (!ssmResult.isCodeNormal()) {
//            return;
//        }
//
//        List<Map<String, Object>> cpoSvcUsgPrcInfo = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//        int rsltNum = 0;
//        NWZC150001_cpoSvcUsgPrcPMsg pMsg;
//        for (Map<String, Object> cpoSvcUsgPrc : cpoSvcUsgPrcInfo) {
//            pMsg = cpoUpdApiReqPMsg.cpoSvcUsgPrc.no(rsltNum);
//
//            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_NEW);
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoSvcLineNum, (BigDecimal) cpoSvcUsgPrc.get("CPO_SVC_LINE_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.mdlId, (BigDecimal) cpoSvcUsgPrc.get("MDL_ID"));
//            ZYPEZDItemValueSetter.setValue(pMsg.prcListBandCd, (String) cpoSvcUsgPrc.get("PRC_LIST_BAND_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.prcBookMdseCd, (String) cpoSvcUsgPrc.get("PRC_BOOK_MDSE_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.bllgMtrLbCd, (String) cpoSvcUsgPrc.get("BLLG_MTR_LB_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.usgMdseCd, (String) cpoSvcUsgPrc.get("USG_MDSE_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.regMtrLbCd, (String) cpoSvcUsgPrc.get("REG_MTR_LB_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.mlyCopyInclPrcQty, (BigDecimal) cpoSvcUsgPrc.get("COPY_INCL_PRC_QTY"));
//            ZYPEZDItemValueSetter.setValue(pMsg.xsMtrAmtRate, (BigDecimal) cpoSvcUsgPrc.get("XS_MTR_AMT_RATE"));
//            ZYPEZDItemValueSetter.setValue(pMsg.contrMtrMultRate, (BigDecimal) cpoSvcUsgPrc.get("CONTR_MTR_MULT_RATE"));
//            ZYPEZDItemValueSetter.setValue(pMsg.prcSvcTierTpCd, (String) cpoSvcUsgPrc.get("PRC_SVC_TIER_TP_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.minCopyVolCnt, (BigDecimal) cpoSvcUsgPrc.get("MIN_COPY_VOL_CNT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.maxCopyVolCnt, (BigDecimal) cpoSvcUsgPrc.get("MAX_COPY_VOL_CNT"));
//            rsltNum++;
//        }
//
//        cpoUpdApiReqPMsg.cpoSvcUsgPrc.setValidCount(rsltNum);
//    }
//
//    @SuppressWarnings("unchecked")
//    private void setCpoUpdApiReqCpoSvcAddlBasePrcForShell(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg, BigDecimal cpoSvcPk) {
//
//        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getCpoSvcAddlBasePrcForShell(bizMsg.glblCmpyCd.getValue(), cpoSvcPk);
//        if (!ssmResult.isCodeNormal()) {
//            return;
//        }
//
//        List<Map<String, Object>> cpoSvcAddlBasePrcInfo = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//        int rsltNum = 0;
//        NWZC150001_cpoSvcAddlBasePrcPMsg pMsg;
//        for (Map<String, Object> cpoDelyInfo : cpoSvcAddlBasePrcInfo) {
//            pMsg = cpoUpdApiReqPMsg.cpoSvcAddlBasePrc.no(rsltNum);
//
//            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_NEW);
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoSvcLineNum, (BigDecimal) cpoDelyInfo.get("CPO_SVC_LINE_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum, (String) cpoDelyInfo.get("CPO_DTL_LINE_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum, (String) cpoDelyInfo.get("CPO_DTL_LINE_SUB_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.addlBasePrcCatgCd, (String) cpoDelyInfo.get("ADDL_BASE_PRC_CATG_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.addlBasePrcDealAmt, (BigDecimal) cpoDelyInfo.get("ADDL_BASE_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.addlBasePrcFuncAmt, (BigDecimal) cpoDelyInfo.get("ADDL_BASE_PRC_FUNC_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.prcListEquipConfigNum, (BigDecimal) cpoDelyInfo.get("PRC_LIST_EQUIP_CONFIG_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.addlEquipPrcDealAmt, (BigDecimal) cpoDelyInfo.get("ADDL_EQUIP_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.addlEquipPrcFuncAmt, (BigDecimal) cpoDelyInfo.get("ADDL_EQUIP_PRC_FUNC_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.addlSvcPrcDealAmt, (BigDecimal) cpoDelyInfo.get("ADDL_SVC_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.addlSvcPrcFuncAmt, (BigDecimal) cpoDelyInfo.get("ADDL_SVC_PRC_FUNC_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.dealPrcListPrcAmt, (BigDecimal) cpoDelyInfo.get("DEAL_PRC_LIST_PRC_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.funcPrcListPrcAmt, (BigDecimal) cpoDelyInfo.get("FUNC_PRC_LIST_PRC_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.svcPrcCatgCd, (String) cpoDelyInfo.get("SVC_PRC_CATG_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.billWithEquipInvdFlg, (String) cpoDelyInfo.get("BILL_WITH_EQUIP_INVD_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.crRebilCd, (String) cpoDelyInfo.get("CR_REBIL_CD"));
//
//            rsltNum++;
//        }
//
//        cpoUpdApiReqPMsg.cpoSvcAddlBasePrc.setValidCount(rsltNum);
//    }
//
//    @SuppressWarnings("unchecked")
//    private void setCpoUpdApiReqCpoSvcAddlChrgPrcForShell(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg, BigDecimal cpoSvcPk) {
//
//        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getCpoSvcAddlChrgPrcForShell(bizMsg.glblCmpyCd.getValue(), cpoSvcPk);
//        if (!ssmResult.isCodeNormal()) {
//            return;
//        }
//
//        List<Map<String, Object>> cpoSvcAddlChrgPrcInfo = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//        int rsltNum = 0;
//        NWZC150001_cpoSvcAddlChrgPrcPMsg pMsg;
//        for (Map<String, Object> cpoSvcAddlChrgPrc : cpoSvcAddlChrgPrcInfo) {
//            pMsg = cpoUpdApiReqPMsg.cpoSvcAddlChrgPrc.no(rsltNum);
//
//            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_NEW);
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoSvcLineNum, (BigDecimal) cpoSvcAddlChrgPrc.get("CPO_SVC_LINE_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum, (String) cpoSvcAddlChrgPrc.get("CPO_DTL_LINE_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum, (String) cpoSvcAddlChrgPrc.get("CPO_DTL_LINE_SUB_NUM"));
//            ZYPEZDItemValueSetter.setValue(pMsg.addlChrgPrcCatgCd, (String) cpoSvcAddlChrgPrc.get("ADDL_CHRG_PRC_CATG_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.addlChrgMdseCd, (String) cpoSvcAddlChrgPrc.get("ADDL_CHRG_MDSE_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.addlChrgPrcDealAmt, (BigDecimal) cpoSvcAddlChrgPrc.get("ADDL_CHRG_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.addlChrgPrcFuncAmt, (BigDecimal) cpoSvcAddlChrgPrc.get("ADDL_CHRG_PRC_FUNC_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.dealPrcListPrcAmt, (BigDecimal) cpoSvcAddlChrgPrc.get("DEAL_PRC_LIST_PRC_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.funcPrcListPrcAmt, (BigDecimal) cpoSvcAddlChrgPrc.get("FUNC_PRC_LIST_PRC_AMT"));
//            ZYPEZDItemValueSetter.setValue(pMsg.svcPrcCatgCd, (String) cpoSvcAddlChrgPrc.get("SVC_PRC_CATG_CD"));
//            ZYPEZDItemValueSetter.setValue(pMsg.billWithEquipInvdFlg, (String) cpoSvcAddlChrgPrc.get("BILL_WITH_EQUIP_INVD_FLG"));
//            ZYPEZDItemValueSetter.setValue(pMsg.crRebilCd, (String) cpoSvcAddlChrgPrc.get("CR_REBIL_CD"));
//            rsltNum++;
//        }
//
//        cpoUpdApiReqPMsg.cpoSvcAddlChrgPrc.setValidCount(rsltNum);
//    }
//    // QC#4078 Add End
    // 2017/05/16 S21_NA#Review structure Lv.2 (Delete Logic For Copy) Del End

    private boolean callApi(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWZC150001PMsg cpoUpdApiMsg, boolean isModifyReq) { // 2018/01/31 S21_NA#19808 Mod

        /**************************************************
         * NWZC150001 : DS CPO Update API
         **************************************************/
        final List<NWZC150002PMsg> cpoUpdApiOutMsgList = new ArrayList<NWZC150002PMsg>();
        final List<NWZC150003PMsg> cpoUpdApiOutMsgList03 = new ArrayList<NWZC150003PMsg>();

        NWXC150001SortDetailPMsg.sortLinePmsgAndRtnPmsgByLineNum(cpoUpdApiMsg); // 2016/08/02 S21_NA#12637 Mod
        new NWZC150001().execute(cpoUpdApiMsg, cpoUpdApiOutMsgList, cpoUpdApiOutMsgList03, ONBATCH_TYPE.ONLINE);

        setCpoUpdateApiErrMsg(bizMsg, glblMsg, cpoUpdApiMsg); // 2018/01/31 S21_NA#19808 Mod

        final boolean isNormalEnd = setCpoUpdateApiDtlErrMsg(bizMsg, glblMsg, cpoUpdApiMsg, cpoUpdApiOutMsgList, cpoUpdApiOutMsgList03);
        if (!isNormalEnd) {
            return false;
        }

        if ("E".equals(bizMsg.getMessageKind())) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, cpoUpdApiMsg.cpoOrdNum.getValue());

        // 2017/04/25 S21_NA#Review structure Lv.2 Start
        if(S21StringUtil.isEquals(NWZC150001Constant.MODE_COPY_HEADER, cpoUpdApiMsg.xxModeCd.getValue()) //
                || S21StringUtil.isEquals(NWZC150001Constant.MODE_COPY_ALL_RETURN, cpoUpdApiMsg.xxModeCd.getValue()) // Add 2017/09/21 S21_NA#18859
                || S21StringUtil.isEquals(NWZC150001Constant.MODE_COPY_ALL, cpoUpdApiMsg.xxModeCd.getValue())){
            ZYPEZDItemValueSetter.setValue(bizMsg.xxSrchNum, cpoUpdApiMsg.cpoOrdNum_A.getValue());
        }
        // 2017/04/25 S21_NA#Review structure Lv.2 End

        return true;

    }

    private static void setCpoUpdateApiErrMsg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWZC150001PMsg cpoUpdApiMsg) { // 2018/01/31 S21_NA#19808 Mod

        for (int i = 0; i < cpoUpdApiMsg.xxMsgIdList.getValidCount(); i++) {

            final String xxMsgId = cpoUpdApiMsg.xxMsgIdList.no(i).xxMsgId.getValue();

            if (xxMsgId.endsWith("E") && chkCpoUpdAPIErrIdBCH(xxMsgId)) {
                relationCpoUpdateApiErrMsg(bizMsg, xxMsgId);

            } else {

                if (!NWZC150001CpouConstant.NWZM0135W.equals(xxMsgId)) {
                    bizMsg.setMessageInfo(xxMsgId);
                }
            }
        }
    }


    private static boolean chkCpoUpdAPIErrIdBCH(String msgId) {

        final Set<String> chkIdSet = new HashSet<String>();
        chkIdSet.add(NWZC150001CpouConstant.NWZM0002E);
        chkIdSet.add(NWZC150001CpouConstant.NWZM0024E);
        chkIdSet.add(NWZC150001CpouConstant.NWZM0025E);
        chkIdSet.add(NWZC150001CpouConstant.NWZM0026E);
        chkIdSet.add(NWZC150001CpouConstant.NWZM0028E);
        chkIdSet.add(NWZC150001CpouConstant.NWZM0097E);
        chkIdSet.add(NWZC150001CpouConstant.NWZM0109E);
        chkIdSet.add(NWZC150001CpouConstant.NWZM0112E);
        chkIdSet.add(NWZC150001CpouConstant.NWZM0114E);
        chkIdSet.add(NWZC150001Constant.NWZM2010E);
        chkIdSet.add(NWZC150001Constant.NWZM1459E);
        // 2016/02/25 S21_NA#4440 Add Start
        chkIdSet.add(NWZC150001Constant.NWZM1452E);
        // Del Start 2018/02/26 QC#22967
        //chkIdSet.add(NWZC150001Constant.NWZM1455E);
        // Del End 2018/02/26 QC#22967
        // 2016/02/25 S21_NA#4440 Add End
        chkIdSet.add(NWZC150001Constant.NWZM1952E); // 2016/07/11 S21_NA#7821 Add
        // 2023/12/06 QC#61281 K.Ikeda START
        chkIdSet.add(NWZC150001Constant.NWZM2324E);
        // 2023/12/06 QC#61281 K.Ikeda END
        chkIdSet.add(NWZC150001Constant.NWZM1477E); // 2016/10/04 S21_NA#13170 Add
        chkIdSet.add(NWZC150001Constant.NWZM2033E); // 2016/10/25 S21_NA#9705 Add
        chkIdSet.add(NWZC177001Constant.NWZM1922E); // 2016/11/07 S21_NA#14186 Add Start
        chkIdSet.add(NWZC177001Constant.NWZM2047E); // 2016/11/07 S21_NA#14186 Add Start
        chkIdSet.add(NWZC177001Constant.NWZM2048E); // 2016/11/07 S21_NA#14186 Add Start
        chkIdSet.add(NWZC177001Constant.NWZM2049E); // 2016/11/07 S21_NA#14186 Add Start
        chkIdSet.add(NWZC177001Constant.NWZM2050E); // 2016/11/07 S21_NA#14186 Add Start
        chkIdSet.add(NWZC150001Constant.NWZM2005E); // 2016/12/26 S21_NA#11547 Add
        // Add Start 2018/11/28 QC#29252
        chkIdSet.add(NWZC150001Constant.NWZM1838E);
        // Add End 2018/11/28 QC#29252
        chkIdSet.add(NWZC150001Constant.NWZM1839E); // 2017/02/21 S21_NA#17474 Add
        // Add Start 2018/02/26 QC#22967
        chkIdSet.add(NWZC150001Constant.NWZM2254E);
        chkIdSet.add(NWZC150001Constant.NWZM2255E);
        // Add End 2018/02/26 QC#22967

        // Add Start 2018/10/29 QC#28920
        chkIdSet.add(NWZC150001Constant.NWZM1458E);
        // Add End 2018/10/29 QC#28920

        return chkIdSet.contains(msgId);
    }

    private static void relationCpoUpdateApiErrMsg(NWAL1500CMsg bizMsg, String msgId) {

        boolean errFlg = false;
        if (NWZC150001CpouConstant.NWZM0002E.equals(msgId)) {
            bizMsg.cpoOrdNum.setErrorInfo(1, NWZC150001CpouConstant.NWZM0002E);
            errFlg = true;
        } else if (NWZC150001CpouConstant.NWZM0024E.equals(msgId)) {
            bizMsg.sellToCustCd.setErrorInfo(1, NWZC150001CpouConstant.NWZM0024E);
            errFlg = true;
        } else if (NWZC150001CpouConstant.NWZM0025E.equals(msgId)) {
            bizMsg.billToCustCd.setErrorInfo(1, NWZC150001CpouConstant.NWZM0025E);
            errFlg = true;
        } else if (NWZC150001CpouConstant.NWZM0026E.equals(msgId)) {
            bizMsg.shipToCustCd.setErrorInfo(1, NWZC150001CpouConstant.NWZM0026E);
            errFlg = true;
        } else if (NWZC150001CpouConstant.NWZM0028E.equals(msgId)) {
            bizMsg.dsOrdTpDescTxt.setErrorInfo(1, NWZC150001CpouConstant.NWZM0028E);
            errFlg = true;
        } else if (NWZC150001CpouConstant.NWZM0097E.equals(msgId)) {
            bizMsg.dsOrdTpDescTxt.setErrorInfo(1, NWZC150001CpouConstant.NWZM0097E);
            errFlg = true;
        } else if (NWZC150001CpouConstant.NWZM0109E.equals(msgId)) {
            bizMsg.dsOrdTpDescTxt.setErrorInfo(1, NWZC150001CpouConstant.NWZM0109E);
            errFlg = true;
        } else if (NWZC150001CpouConstant.NWZM0112E.equals(msgId)) {
            bizMsg.billToCustCd.setErrorInfo(1, NWZC150001CpouConstant.NWZM0112E);
            errFlg = true;
        } else if (NWZC150001CpouConstant.NWZM0114E.equals(msgId)) {
            bizMsg.shipToCustCd.setErrorInfo(1, NWZC150001CpouConstant.NWZM0114E);
            errFlg = true;
        } else if (NWZC150001Constant.NWZM2010E.equals(msgId)) { // QC#10755
            bizMsg.carrSvcLvlDescTxt.setErrorInfo(1, NWZC150001Constant.NWZM2010E);
            errFlg = true;
        } else if (NWZC150001Constant.NWZM1459E.equals(msgId)) {
            bizMsg.carrAcctNum.setErrorInfo(1, NWZC150001Constant.NWZM1459E);
            errFlg = true;
        } else if (NWZC150001Constant.NWZM1452E.equals(msgId)) { // 2016/02/25 S21_NA#4440 Add Start
            bizMsg.billToCustCd.setErrorInfo(1, NWZC150001Constant.NWZM1452E);
            bizMsg.billToCustAcctCd.setErrorInfo(1, NWZC150001Constant.NWZM1452E);
            errFlg = true;
        } else if (NWZC150001Constant.NWZM1453E.equals(msgId)) {
            bizMsg.shipToCustCd.setErrorInfo(1, NWZC150001Constant.NWZM1453E);
            bizMsg.shipToCustAcctCd.setErrorInfo(1, NWZC150001Constant.NWZM1453E);
            errFlg = true;
            // Del Start 2018/02/26 QC#22967
        //} else if (NWZC150001Constant.NWZM1455E.equals(msgId)) {
        //    // 2016/10/04 S21_NA#13170 Mod Start
        //    bizMsg.billToCustCd.setErrorInfo(1, NWZC150001Constant.NWZM1455E);
        //    bizMsg.shipToCustCd.setErrorInfo(1, NWZC150001Constant.NWZM1455E);
        //    // 2016/10/04 S21_NA#13170 Mod End
        //    errFlg = true;
        //    // 2016/02/25 S21_NA#4440 Add End
            // Del End 2018/02/26 QC#22967
        } else if (NWZC150001Constant.NWZM1952E.equals(msgId)) {
            bizMsg.dsPmtMethCd.setErrorInfo(1, msgId);
            errFlg = true;
            // 2016/02/25 S21_NA#4440 Add End
            // 2016/10/04 S21_NA#13170 Add Start
        // 2023/12/06 QC#61281 K.Ikeda START
        } else if (NWZC150001Constant.NWZM2324E.equals(msgId)) {
            bizMsg.dsPmtMethCd.setErrorInfo(1, NWZC150001Constant.NWZM2324E);
            errFlg = true;
        // 2023/12/06 QC#61281 K.Ikeda END
        } else if (NWZC150001Constant.NWZM1477E.equals(msgId)) {
            bizMsg.shipToCustAcctCd.setErrorInfo(1, NWZC150001Constant.NWZM1477E);
            errFlg = true;
            // 2016/10/04 S21_NA#13170 Add End
        } else if (NWZC150001Constant.NWZM2033E.equals(msgId)) { // 2016/10/25 S21_NA#9705 Add Start
            bizMsg.ordEntryActCd_IF.setErrorInfo(1, msgId);
            errFlg = true; // 2016/10/25 S21_NA#9705 Add End
        } else if (NWZC177001Constant.NWZM1922E.equals(msgId) // 2016/11/07 S21_NA#14186 Add Start
                || NWZC177001Constant.NWZM2047E.equals(msgId) //
                || NWZC177001Constant.NWZM2048E.equals(msgId) //
                || NWZC177001Constant.NWZM2049E.equals(msgId) //
            || NWZC177001Constant.NWZM2050E.equals(msgId)) {
            // Work Flow Error
            bizMsg.ordEntryActCd_AC.setErrorInfo(1, msgId);
            errFlg = true; // 2016/11/07 S21_NA#14186 Add End
            // Add Start 2018/11/28 QC#29252
        } else if (NWZC150001Constant.NWZM1838E.equals(msgId)) { // QC#17474 2017/02/21 Add
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, msgId);
            // Add End 2018/11/28 QC#29252
        } else if (NWZC150001Constant.NWZM1839E.equals(msgId)) { // QC#17474 2017/02/21 Add
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2005E.equals(msgId)) { // 2016/12/26 S21_NA#11547 Add Start
            bizMsg.dsPmtMethCd.setErrorInfo(1, msgId);
            errFlg = true; // 2016/12/26 S21_NA#11547 Add End
            // Add Start 2018/02/26 QC#22967
        } else if (NWZC150001Constant.NWZM2254E.equals(msgId)) {
            bizMsg.shipToCustCd.setErrorInfo(1, NWZC150001Constant.NWZM2254E);
            bizMsg.soldToCustLocCd.setErrorInfo(1, NWZC150001Constant.NWZM2254E);
            errFlg = true;
        } else if (NWZC150001Constant.NWZM2255E.equals(msgId)) {
            bizMsg.soldToCustLocCd.setErrorInfo(1, NWZC150001Constant.NWZM2255E);
            bizMsg.billToCustCd.setErrorInfo(1, NWZC150001Constant.NWZM2255E);
            errFlg = true;
            // Add End 2018/02/26 QC#22967

            // Add Start 2018/10/29 QC#28920
        } else if (NWZC150001Constant.NWZM1458E.equals(msgId)) {
            bizMsg.frtCondDescTxt.setErrorInfo(1, NWZC150001Constant.NWZM1458E);
            bizMsg.shpgSvcLvlCd.setErrorInfo(1, NWZC150001Constant.NWZM1458E);
            bizMsg.carrSvcLvlDescTxt.setErrorInfo(1, NWZC150001Constant.NWZM1458E);
            errFlg = true;
            // Add End 2018/10/29 QC#28920
        }
        if (errFlg) {
            bizMsg.setMessageInfo(ZZM9037E);
        }
    }
    private boolean setCpoUpdateApiDtlErrMsg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWZC150001PMsg cpoUpdApiMsg, List<NWZC150002PMsg> cpoUpdApiOutMsgList, List<NWZC150003PMsg> cpoUpdApiInMsgList) {

        // 2018/01/31 S21_NA#19808 bizMsg.A, B => glblMsg.A, B
        boolean isNormalEnd = true;

        List<String> errLineConfigConfigList = new ArrayList<String>(0);
        List<String> errRmaConfigList = new ArrayList<String>(0);
        for (NWZC150002PMsg pMsg : cpoUpdApiOutMsgList) {

            final int confIndex = getConfLineIndex(glblMsg.A, pMsg.dsOrdPosnNum.getValue());
            final int sMsgIndex = getLineIndex(glblMsg.B, pMsg.cpoDtlLineNum.getValue(), pMsg.cpoDtlLineSubNum.getValue());

            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                final String[] xxMsgParam = getMsgParamArray(pMsg.xxMsgIdList.no(i));

                if (xxMsgId.endsWith("E") && chkCpoUpdAPIErrIdACHDtl(xxMsgId)) {
                    isNormalEnd = false;
                    relationCpoUpdateApiConfigErrMsg(bizMsg, glblMsg.A.no(confIndex), xxMsgId, cpoUpdApiMsg, xxMsgParam);
                    // 2018/01/31 S21_NA#19808 Add Start
                    if (!errLineConfigConfigList.contains(glblMsg.A.no(confIndex).dsOrdPosnNum_LC.getValue())) {
                        errLineConfigConfigList.add(glblMsg.A.no(confIndex).dsOrdPosnNum_LC.getValue());
                    }
                    // 2018/01/31 S21_NA#19808 Add End
//                } else if (xxMsgId.endsWith("E") && chkCpoUpdAPIErrIdBCHDtl(xxMsgId) || NWZC046001.NWZM0900W.equals(xxMsgId)) {
                } else if (xxMsgId.endsWith("E") && chkCpoUpdAPIErrIdBCHDtl(xxMsgId)) {

                    isNormalEnd = false;

                    final NWAL1500_BSMsg lineMsg = glblMsg.B.no(sMsgIndex);

                    // isComponent?
                    relationCpoUpdateApiDtlErrMsg(bizMsg, lineMsg, xxMsgId, cpoUpdApiMsg, xxMsgParam);
                    // 2018/01/31 S21_NA#19808 Add Start
                    if (!errLineConfigConfigList.contains(glblMsg.B.no(sMsgIndex).dsOrdPosnNum_LL.getValue())) {
                        errLineConfigConfigList.add(glblMsg.B.no(sMsgIndex).dsOrdPosnNum_LL.getValue());
                    }
                    // 2018/01/31 S21_NA#19808 Add End

                } else if (xxMsgId.endsWith("W") && NWZM1926W.equals(xxMsgId)) {
                    // 2016/02/23 S21_NA#3239 Add Start
                    isNormalEnd = false;
                    bizMsg.setMessageInfo(NWAM0776W);
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_00, ZYPConstant.FLG_ON_Y);
                    relationCpoUpdateApiConfigErrMsg(bizMsg, glblMsg.A.no(confIndex), xxMsgId, cpoUpdApiMsg, xxMsgParam); // 2018/01/31 S21_NA#19808 Mod
                    // 2016/02/23 S21_NA#3239 Add End
                    // 2018/01/31 S21_NA#19808 Add Start
                    if (!errLineConfigConfigList.contains(glblMsg.A.no(confIndex).dsOrdPosnNum_LC.getValue())) {
                        errLineConfigConfigList.add(glblMsg.A.no(confIndex).dsOrdPosnNum_LC.getValue());
                    }
                    // 2018/01/31 S21_NA#19808 Add End
                } else if (xxMsgId.endsWith("W") && NLZM2518W.equals(xxMsgId)) {
                    // 2018/06/25 QC#20154 Add Start
                    isNormalEnd = false;
                    // 2019/12/13 QC#54230 Mod Start
                    String msgId = NLZM2518W;
                    if (!isShipToChgToConfig(glblMsg.A.no(confIndex))) {
                        msgId = NWAM0976W;
                    }
                    //bizMsg.setMessageInfo(NLZM2518W);
                    bizMsg.setMessageInfo(msgId);
                    // 2019/12/13 QC#54230 Mod End
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_00, ZYPConstant.FLG_ON_Y);
                    // 2019/12/13 QC#54230 Mod Start
                    //relationCpoUpdateApiConfigErrMsg(bizMsg, glblMsg.A.no(confIndex), xxMsgId, cpoUpdApiMsg, xxMsgParam);
                    relationCpoUpdateApiConfigErrMsg(bizMsg, glblMsg.A.no(confIndex), msgId, cpoUpdApiMsg, xxMsgParam);
                    // 2019/12/13 QC#54230 Mod End
                    if (!errLineConfigConfigList.contains(glblMsg.A.no(confIndex).dsOrdPosnNum_LC.getValue())) {
                        errLineConfigConfigList.add(glblMsg.A.no(confIndex).dsOrdPosnNum_LC.getValue());
                    }
                    // 2018/06/25 QC#20154 Add End
                } else {
                    bizMsg.setMessageInfo(xxMsgId, xxMsgParam);
                }
            }
        }

        // 2018/01/31 S21_NA#19808 Add Start
        // Goto Error Page
        if (!errLineConfigConfigList.isEmpty()) {
            // Reset Filter
            String firstErrorConfig = errLineConfigConfigList.get(0);
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                NWAL1500_ASMsg configMsg = glblMsg.A.no(i);
                if (S21StringUtil.isEquals(firstErrorConfig, configMsg.dsOrdPosnNum_LC.getValue())) {
                    configMsg.xxSmryLineFlg_L.clear();
                    configMsg.xxResFltrFlg_LC.clear();
                    break;
                }
            }
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
                if (S21StringUtil.isEquals(firstErrorConfig, lineMsg.dsOrdPosnNum_LL.getValue())) {
                    lineMsg.xxSmryLineFlg_LL.clear();
                    lineMsg.xxResFltrFlg_LL.clear();
                }
            }
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            int pageNum = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.A, firstErrorConfig).xxPageNum_LC.getValueInt();
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, pageNum);
        }
        // 2018/01/31 S21_NA#19808 Add End
        for (NWZC150003PMsg pMsg : cpoUpdApiInMsgList) {

            final int confIndex = getConfLineIndex(glblMsg.C, pMsg.dsOrdPosnNum.getValue()); // 2018/01/31 S21_NA#19808 Mod
            final int sMsgIndex = getLineIndex(glblMsg.D, pMsg.cpoDtlLineNum.getValue(), pMsg.cpoDtlLineSubNum.getValue()); // 2018/01/31 S21_NA#19808 Mod

            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                final String[] xxMsgParam = getMsgParamArray(pMsg.xxMsgIdList.no(i));

                if (xxMsgId.endsWith("E") && chkCpoUpdAPIErrIdACHDtl(xxMsgId)) {
                    isNormalEnd = false;
                    relationCpoUpdateApiConfigErrMsg(bizMsg, glblMsg.C.no(confIndex), xxMsgId, cpoUpdApiMsg, xxMsgParam); // 2018/01/31 S21_NA#19808 Mod
                    // 2018/04/06 S21_NA#25352 Mod Start
//                    // 2018/01/31 S21_NA#19808 Add Start
//                    if (!errLineConfigConfigList.contains(glblMsg.C.no(confIndex).dsOrdPosnNum_RC.getValue())) {
//                        errLineConfigConfigList.add(glblMsg.C.no(confIndex).dsOrdPosnNum_RC.getValue());
//                    }
//                    // 2018/01/31 S21_NA#19808 Add End
                    if (!errRmaConfigList.contains(glblMsg.C.no(confIndex).dsOrdPosnNum_RC.getValue())) {
                        errRmaConfigList.add(glblMsg.C.no(confIndex).dsOrdPosnNum_RC.getValue());
                    }
                    // 2018/04/06 S21_NA#25352 Mod End
//                } else if (xxMsgId.endsWith("E") && chkCpoUpdAPIErrIdBCHDtl(xxMsgId) || NWZC046001.NWZM0900W.equals(xxMsgId)) {
                } else if (xxMsgId.endsWith("E") && chkCpoUpdAPIErrIdBCHDtl(xxMsgId)) {

                    isNormalEnd = false;

                    final NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(sMsgIndex);

                    // isComponent?
                    relationCpoUpdateApiDtlErrMsg(bizMsg, rmaLineMsg, xxMsgId, cpoUpdApiMsg, xxMsgParam);
                    // 2018/04/06 S21_NA#25352 Mod Start
//                    // 2018/01/31 S21_NA#19808 Add Start
//                    if (!errLineConfigConfigList.contains(glblMsg.D.no(sMsgIndex).dsOrdPosnNum_RL.getValue())) {
//                        errLineConfigConfigList.add(glblMsg.D.no(sMsgIndex).dsOrdPosnNum_RL.getValue());
//                    }
//                    // 2018/01/31 S21_NA#19808 Add End
                    if (!errRmaConfigList.contains(glblMsg.D.no(sMsgIndex).dsOrdPosnNum_RL.getValue())) {
                        errRmaConfigList.add(glblMsg.D.no(sMsgIndex).dsOrdPosnNum_RL.getValue());
                    }
                    // 2018/04/06 S21_NA#25352 Mod End

                } else {
                    bizMsg.setMessageInfo(xxMsgId, xxMsgParam);
                }
            }
        }
        // 2018/01/31 S21_NA#19808 Add Start
        // Goto Error Page
        if (!errRmaConfigList.isEmpty()) {
            // Reset Filter
            String firstErrorConfig = errRmaConfigList.get(0);
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(i);
                if (S21StringUtil.isEquals(firstErrorConfig, rmaConfigMsg.dsOrdPosnNum_RC.getValue())) {
                    rmaConfigMsg.xxSmryLineFlg_R.clear();
                    rmaConfigMsg.xxResFltrFlg_RC.clear();
                    break;
                }
            }
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);
                if (S21StringUtil.isEquals(firstErrorConfig, rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                    rmaLineMsg.xxSmryLineFlg_RL.clear();
                    rmaLineMsg.xxResFltrFlg_RL.clear();
                }
            }
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            int pageNum = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.C, firstErrorConfig).xxPageNum_RC.getValueInt();
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, pageNum);
        }
        // 2018/01/31 S21_NA#19808 Add End

        return isNormalEnd;
    }

    private static int getConfLineIndex(NWAL1500_ASMsgArray lineConfigArray, String dsOrdPosnNum) { // 2018/01/31 S21_NA#19808

        for (int i = 0; i < lineConfigArray.getValidCount(); i++) {
            if (dsOrdPosnNum.equals(lineConfigArray.no(i).dsOrdPosnNum_LC.getValue())) {
                return i;
            }
        }
        return -1;
    }

    private static int getConfLineIndex(NWAL1500_CSMsgArray rmaLineConfigArray, String dsOrdPosnNum) { // 2018/01/31 S21_NA#19808

        for (int i = 0; i < rmaLineConfigArray.getValidCount(); i++) {
            if (dsOrdPosnNum.equals(rmaLineConfigArray.no(i).dsOrdPosnNum_RC.getValue())) {
                return i;
            }
        }
        return -1;
    }

    private static int getLineIndex(NWAL1500_BSMsgArray lineMsgArray, String pCpoDtlLineNum, String pCpoDtlLineSubNum) { // 2018/01/31 S21_NA#19808

        for (int i = 0; i < lineMsgArray.getValidCount(); i++) {

            String cpoDtlLineNum = lineMsgArray.no(i).cpoDtlLineNum_LL.getValue();
            String cpoDtlLineSubNum = lineMsgArray.no(i).cpoDtlLineSubNum_LL.getValue();

            if (pCpoDtlLineNum.equals(cpoDtlLineNum) && pCpoDtlLineSubNum.equals(cpoDtlLineSubNum)) {
                return i;
            }
        }

        return -1;
    }

    private static int getLineIndex(NWAL1500_DSMsgArray rmaLineMsgArray, String pCpoDtlLineNum, String pCpoDtlLineSubNum) { // 2018/01/31 S21_NA#19808

        for (int i = 0; i < rmaLineMsgArray.getValidCount(); i++) {

            String cpoDtlLineNum = rmaLineMsgArray.no(i).cpoDtlLineNum_RL.getValue();
            String cpoDtlLineSubNum = rmaLineMsgArray.no(i).cpoDtlLineSubNum_RL.getValue();

            if (pCpoDtlLineNum.equals(cpoDtlLineNum) && pCpoDtlLineSubNum.equals(cpoDtlLineSubNum)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean chkCpoUpdAPIErrIdACHDtl(String msgId) {
        final List<String> msgList = new ArrayList<String>();
        msgList.add(NSZC048001Constant.NSZM0401E);
        msgList.add(NSZC048001Constant.NSZM0396E);
        msgList.add(NWZC150001Constant.NWZM1794E); // 2016/01/13 S21_NA#2726 Add
        msgList.add(NWZC150001Constant.NWZM1887E); // 2016/01/25 S21_NA#3505 Add
        msgList.add(NWZC150001Constant.NWZM2266E); // QC#24245 2018/06/13 Add
        // 2016/02/23 S21_NA#4440 Add Start
        msgList.add(NWZC150001Constant.NWZM1452E);
        msgList.add(NWZC150001Constant.NWZM1453E);
        // Del Start 2018/02/26 QC#22967
        //msgList.add(NWZC150001Constant.NWZM1455E);
        // Del End 2018/02/26 QC#22967
        // 2016/02/23 S21_NA#4440 Add End
        msgList.add(NWZC150001Constant.NWZM1466E); // 2016/03/16 S21_NA#5519 Add
        msgList.add(NWZC150001Constant.NWZM1477E); // 2016/10/04 S21_NA#13170 Add
        msgList.add(NWZC150001Constant.NWZM2033E); // 2016/10/11 S21_NA#7924-2 Add
        msgList.add(NWZC150001Constant.NWZM2046E); // 2016/12/26 S21_NA#11547 Add
        // 2018/01/11 S21_NA#23329 Add Start
        msgList.add(NWZC150001Constant.NWZM2252E);
        // 2018/01/11 S21_NA#23329 Add End
        // Add Start 2018/02/26 QC#22967
        msgList.add(NWZC150001Constant.NWZM2254E);
        msgList.add(NWZC150001Constant.NWZM2255E);
        // Add End 2018/02/26 QC#22967
        msgList.add(NWZC150001Constant.NWZM1480E); // 2018/03/28 S21_NA#24867 Add
        msgList.add(NWZC150001Constant.NWZM1527E); // 2018/04/02 S21_NA#25043 Add
        msgList.add(NWZC150001Constant.NWZM2262E); // 2018/05/20 S21_NA#25604 Add
        msgList.add(NWZC150001Constant.NWZM2263E); // 2018/05/20 S21_NA#25604 Add
        msgList.add(NWZC150001Constant.NWZM2270E); // QC#24245 2018/06/13 Add
        msgList.add(NWZC150001Constant.NWZM2286E); // QC#28772 2018/10/16 Add
        msgList.add(NWZC150001Constant.NWZM2289E); // QC#28882 2018/10/26 
        return msgList.contains(msgId);
    }
    private static boolean chkCpoUpdAPIErrIdBCHDtl(String msgId) {

        final List<String> msgList = new ArrayList<String>();
        msgList.add(NWZC150001CpouConstant.NWZM0041E);
        msgList.add(NWZC150001CpouConstant.NWZM0042E);
        msgList.add(NWZC150001CpouConstant.NWZM0044E);
        msgList.add(NWZC150001CpouConstant.NWZM0947E);
        msgList.add(NWZC150001CpouConstant.NWZM0048E);
        msgList.add(NWZC150001CpouConstant.NWZM0927E);
        msgList.add(NWZC150001CpouConstant.NWZM0054E);
        msgList.add(NWXC001001SalesRepValidator.MSG_ID_INVALID_SLS_REP);
        msgList.add(NWZC150001CpouConstant.NWZM0458E);
        msgList.add(NWZC150001CpouConstant.NWZM0140E);
        msgList.add(NWZC150001CpouConstant.NWZM0141E);
        msgList.add(NWZC150001CpouConstant.NWZM0142E);
        msgList.add(NWZC150001CpouConstant.NWZM0143E);
        msgList.add(NWZC150001CpouConstant.NWZM0144E);
        msgList.add(NWZC150001CpouConstant.NWZM0148E);
        msgList.add(NWZC150001CpouConstant.NWZM0152E);
        msgList.add(NWZC150001CpouConstant.NWZM0153E);
        msgList.add(NWZC150001CpouConstant.NWZM0161E);
        msgList.add(NWZC150001CpouConstant.NWZM0335E);
        msgList.add(NWZC150001CpouConstant.NWZM0642E);
        msgList.add(NWZC150001CpouConstant.NWZM0263E);
        msgList.add(NWZC150001CpouConstant.NWZM0041E);
        msgList.add(NWZC150001CpouConstant.NWZM0458E);
        msgList.add(NWZC150001CpouConstant.NWZM0418E);
        msgList.add(NWZC150001CpouConstant.NWZM0419E);
        msgList.add(NWZC150001CpouConstant.NWZM0047E);
//        msgList.add(NWZC046001.NWZM0900W);
        msgList.add(NWZC150001CpouConstant.NWZM0942E);
        msgList.add(NWZC150001CpouConstant.NWZM0943E);
        msgList.add(NWZC150001CpouConstant.NWZM1215E);
        msgList.add(NWZC150001CpouConstant.NWZM1216E);
        msgList.add(NWZC150001CpouConstant.NWZM1298E);
        msgList.add(NWZC150001CpouConstant.NWZM1311E);
        msgList.add(NWZC150001CpouConstant.NWZM1312E);
        msgList.add(NWZC150001CpouConstant.NWZM1262E);
        // 2016/01/13 S21_NA#2726 Add Start
        msgList.add(NWZC150001Constant.NWZM1468E);
        msgList.add(NWZC150001Constant.NWZM1469E);
        msgList.add(NWZC150001Constant.NWZM1470E);
        msgList.add(NWZC150001Constant.NWZM1471E);
        msgList.add(NWZC150001Constant.NWZM1472E);
        msgList.add(NWZC150001Constant.NWZM1473E);
        msgList.add(NWZC150001Constant.NWZM1474E);
        msgList.add(NWZC150001Constant.NWZM1475E);
        msgList.add(NWZC150001Constant.NWZM1476E);
        msgList.add(NWZC150001Constant.NWZM1478E);
        msgList.add(NWZC150001Constant.NWZM1484E);
        msgList.add(NWZC150001Constant.NWZM1795E);
        // 2016/01/13 S21_NA#2726 Add End
        msgList.add(NWZC150001Constant.NWZM1889E); // 2016/01/26 S21_NA#3740 Add
        msgList.add(NWZC150001Constant.NWZM1917E);
        // 2016/02/23 S21_NA#3239
        msgList.add(NWZC150001Constant.NWZM1926W);
        // 2016/02/23 S21_NA#3239
        // 2016/02/25 S21_NA#1704 Add Start
        msgList.add(NWZC150001Constant.NWZM1921E);
        // 2016/02/25 S21_NA#1704 Add End
        // 2016/02/28 S21_NA#4291 Add Start
        msgList.add(NWZC150001Constant.NWZM1419E);
        msgList.add(NWZC150001Constant.NWZM1431E);
        msgList.add(NWZC150001Constant.NWZM1432E);
        msgList.add(NWZC150001Constant.NWZM1433E);
        msgList.add(NWZC150001Constant.NWZM1434E);
        msgList.add(NWZC150001Constant.NWZM1420E);
        msgList.add(NWZC150001Constant.NWZM1435E);
//        msgList.add(NWZC150001Constant.NWZM1436E);
//        msgList.add(NWZC150001Constant.NWZM1438E);
        msgList.add(NWZC150001Constant.NWZM1439E);
//        msgList.add(NWZC150001Constant.NWZM1427E);
        msgList.add(NWZC150001Constant.NWZM1593E);
        msgList.add(NWZC150001Constant.NWZM1594E);
        msgList.add(NWZC150001Constant.NWZM1595E);
        // 2016/02/28 S21_NA#4291 Add End
        msgList.add(NWZC150001Constant.NWZM1928E); // 2016/03/16 S21_NA#5519 Add
        msgList.add(NWZC150001Constant.NWZM1530E); // 2016/04/22 S21_NA#7132 Add
        msgList.add(NWZC150001Constant.NWZM1507E); // 2016/05/26 S21_NA#5668 Add

        // 2016/06/17 S21_NA#9679 Add Start
        msgList.add(NWZC150001Constant.NWZM1917E);
        msgList.add(NWZC150001Constant.NWZM1484E);
        msgList.add(NWZC150001Constant.NWZM1485E);
        msgList.add(NWZC150001Constant.NWZM1487E);
        msgList.add(NWZC150001Constant.NWZM1488E);
        msgList.add(NWZC150001Constant.NWZM1489E);
        msgList.add(NWZC150001Constant.NWZM1492E);
        // 2016/06/17 S21_NA#9679 Add End
        msgList.add(NWZC153001Constant.NWZM1742E); // 2016/06/20 S21_NA#5331-3 add
        msgList.add(NWZC153001Constant.NWZM2020E); // 2016/09/27 S21_NA#9102 Add
        msgList.add(NWZC153001Constant.NWZM2057E); // 2016/12/20 S21_NA#15898-2 Add
        msgList.add(NWZC150001Constant.NWZM1526E); // 2017/01/19 S21_NA#13768-3 Add

        // 2017/06/06 S21_NA#16443 Add Start
        msgList.add(NWZC150001CpouConstant.NWZM2229E);
        msgList.add(NWZC150001CpouConstant.NWZM2230E);
        // 2017/06/06 S21_NA#16443 Add End

        // 2017/12/21 S21_NA#20050 Add Start
        msgList.add(NWZC150001Constant.NWZM2250E);
        // 2017/12/21 S21_NA#20050 Add End

        // 2018/02/13 S21_NA#22717 Add Start
        msgList.add(NWZC150001Constant.NWZM2253E);
        // 2018/02/13 S21_NA#22717 Add End
        // QC#24705 2018/06/19 add Start
        msgList.add(NWZC150001Constant.NWZM2264E);
        msgList.add(NWZC150001Constant.NWZM2265E);
        // QC#24705 2018/06/19 add End
        msgList.add(NWZC150001Constant.NWZM2270E); // QC#24245 2018/06/13 Add

        // 2018/07/23 S21_NA#24745 Add Start
        msgList.add(NWZC150001Constant.NWZM2271E);
        msgList.add(NWZC150001Constant.NWZM2272E);
        msgList.add(NWZC150001Constant.NWZM2273E);
        // 2018/07/23 S21_NA#24745 Add End
        msgList.add(NWZC150001Constant.NWZM2275E); // 2018/08/02 S21_NA#26181 Add
        msgList.add(NWZC150001Constant.NWZM2277E); // 2018/08/24 S21_NA#27840 Add

        // 2018/08/21 S21_NA#26767 Add Start
        msgList.add(NWZC150001Constant.NWZM2278E);
        msgList.add(NWZC150001Constant.NWZM2280E);
        msgList.add(NWZC150001Constant.NWZM2281E);
        msgList.add(NWZC150001Constant.NWZM2282E);
        msgList.add(NWZC150001Constant.NWZM2283E);
        msgList.add(NWZC150001Constant.NWZM2284E);
        // 2018/08/21 S21_NA#26767 Add End
        msgList.add(NWZC150001Constant.NWZM2285E); // 2018/10/10 S21_NA#28728 Add
        // 2018/10/18 S21_NA#26229 Add Start
        msgList.add(NWZC150001Constant.NWZM2287E);
        msgList.add(NWZC150001Constant.NWZM2288E); // 2020/04/13 QC#56374 Mod
        // 2018/10/18 S21_NA#26229 Add End
        msgList.add(NWZC150001Constant.NWZM2291E); // 2018/10/31 S21_NA#28921 Add
        msgList.add(NWZC150001Constant.NWZM2292E); // 2018/11/01 S21_NA#28928 Add
        msgList.add(NWZC150001Constant.NWZM2294E); // QC#29262 2018/11/27 Add
        msgList.add(NWZC150001Constant.NWAM0037E); // QC#29345 2018/12/05 Add
        // 2018/12/20 QC#28928 Add Start
        msgList.add(NWZC150001Constant.NWZM2301E);
        msgList.add(NWZC150001Constant.NWZM2302E);
        msgList.add(NWZC150001Constant.NWZM2303E); 
        // 2018/12/20 QC#28928 Add End
        msgList.add(NWZC150001Constant.NWAM0965E); // QC#29573 2019/01/08 Add
        msgList.add(NWZC150001Constant.NWZM2306E); // 2019/02/22 S21_NA#30449
        msgList.add(NWZC150001Constant.NWZM2310E); // 2019/07/30 QC#52201 Add
        // 2019/08/01 S21_NA#52156 Add Start
        msgList.add(NWZC150001CpouConstant.NWZM2311E);
        // 2019/08/01 S21_NA#52156 Add End
        msgList.add(NWZC150001Constant.NWAM0909E); // 2020/01/23 QC#55207-1 Add
        msgList.add(NWZC150001Constant.NWAM0983E); // 2020/06/09 QC#56978


        return msgList.contains(msgId);
    }

    private void relationCpoUpdateApiConfigErrMsg(NWAL1500CMsg bizMsg, NWAL1500_ASMsg confMsg, String msgId, NWZC150001PMsg cpoUpdApiMsg, String[] xxMsgParam) { // 2018/01/31 S21_NA#19808 Mod
        if (NSZC048001Constant.NSZM0401E.equals(msgId)) {
//            confMsg.mdlDescTxt_LC.setErrorInfo(1, msgId, xxMsgParam); 2016/03/05 S21_NA#5000#68
            confMsg.mdlNm_LC.setErrorInfo(1, msgId, xxMsgParam);
        } else if (NSZC048001Constant.NSZM0396E.equals(msgId)) {
//            confMsg.mdlDescTxt_LC.setErrorInfo(1, msgId, xxMsgParam); 2016/03/05 S21_NA#5000#68
            confMsg.mdlNm_LC.setErrorInfo(1, msgId, xxMsgParam);
        } else if (NWZC150001Constant.NWZM1794E.equals(msgId)) { // 2016/01/13 S21_NA#2726
            confMsg.configTpCd_LC.setErrorInfo(1, msgId, xxMsgParam);
        } else if (NWZC150001Constant.NWZM1887E.equals(msgId)) { // 2016/01/25 S21_NA#3505
            confMsg.svcConfigMstrPk_LC.setErrorInfo(1, msgId, xxMsgParam);
        } else if (NWZC150001Constant.NWZM2266E.equals(msgId)) { // QC#24245 2018/06/13 Add
            confMsg.svcConfigMstrPk_LC.setErrorInfo(1, msgId, xxMsgParam);
        } else if (NWZC150001Constant.NWZM1926W.equals(msgId)) { // 2016/02/23 S21_NA#3239
            confMsg.xxChkBox_LC.setErrorInfo(2, msgId, xxMsgParam); // 2016/03/04 S21_NA#5000
        } else if (NWZC150001Constant.NWZM1452E.equals(msgId)) { // 2016/02/25 S21_NA#4440 Add Start
            confMsg.billToCustCd_LC.setErrorInfo(1, NWZC150001Constant.NWZM1452E);
            confMsg.billToCustAcctCd_LC.setErrorInfo(1, NWZC150001Constant.NWZM1452E);
        } else if (NWZC150001Constant.NWZM1453E.equals(msgId)) {
            confMsg.shipToCustCd_LC.setErrorInfo(1, NWZC150001Constant.NWZM1453E);
            confMsg.shipToCustAcctCd_LC.setErrorInfo(1, NWZC150001Constant.NWZM1453E);
        // Del Start 2018/02/26 QC#22967
        //} else if (NWZC150001Constant.NWZM1455E.equals(msgId)) {
        //    // 2016/10/04 S21_NA#13170 Mod Start
        //    confMsg.billToCustCd_LC.setErrorInfo(1, NWZC150001Constant.NWZM1455E);
        //    confMsg.shipToCustCd_LC.setErrorInfo(1, NWZC150001Constant.NWZM1455E);
        //    // 2016/10/04 S21_NA#13170 Mod End
        //    // 2016/02/25 S21_NA#4440 Add End
        // Del End 2018/02/26 QC#22967
        } else if (NWZC150001Constant.NWZM1466E.equals(msgId)) { // 2016/03/16 S21_NA#5519 Add
            confMsg.svcConfigMstrPk_LC.setErrorInfo(1, msgId);
            // 2016/10/04 S21_NA#13170 Add Start
        } else if (NWZC150001Constant.NWZM1477E.equals(msgId)) {
            confMsg.shipToCustAcctCd_LC.setErrorInfo(1, NWZC150001Constant.NWZM1477E);
            // 2016/10/04 S21_NA#13170 Add End
        } else if (NWZC150001Constant.NWZM2033E.equals(msgId)) { // 2016/10/11 S21_NA#7924-2 Add
            confMsg.xxChkBox_LC.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2046E.equals(msgId)) { // 2016/12/26 S21_NA#11547 Add
            confMsg.xxChkBox_LC.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2252E.equals(msgId)) { // 2018/01/11 S21_NA#23329 Add
            confMsg.xxChkBox_LC.setErrorInfo(1, msgId);
            // Add Start 2018/02/26 QC#22967
        } else if (NWZC150001Constant.NWZM2254E.equals(msgId)) {
            confMsg.shipToCustCd_LC.setErrorInfo(1, NWZC150001Constant.NWZM2254E);
        } else if (NWZC150001Constant.NWZM2255E.equals(msgId)) {
            confMsg.billToCustCd_LC.setErrorInfo(1, NWZC150001Constant.NWZM2255E);
            // Add End 2018/02/26 QC#22967
            // 2018/04/02 S21_NA#25043 Add Start
        } else if (NWZC150001Constant.NWZM1527E.equals(msgId)) {
            confMsg.svcConfigMstrPk_LC.setErrorInfo(1, msgId);
            // 2018/04/02 S21_NA#25043 Add End
        } else if (NWZC150001Constant.NWZM2262E.equals(msgId)) { // 2018/05/20 S21_NA#25604 Add Start
            confMsg.svcConfigMstrPk_LC.setErrorInfo(1, msgId);
            // 2018/05/20 S21_NA#25604 Add End
            // 2018/06/25 QC#20154 Add Start
        } else if (NWZC150001Constant.NLZM2518W.equals(msgId)) {
            // 2019/12/13 QC#54230 Add Start
            if (!isShipToChgToConfig(confMsg)) {
                msgId = NWAM0976W;
            }
            // 2019/12/13 QC#54230 Add End
            confMsg.shipToCustAcctNm_LC.setErrorInfo(2, msgId);
            confMsg.shipToCustAcctCd_LC.setErrorInfo(2, msgId);
            confMsg.shipToCustCd_LC.setErrorInfo(2, msgId);
            // 2018/06/25 QC#20154 Add End
            // QC#24245 2018/06/13 Add Start
        } else if (NWZC150001Constant.NWZM2270E.equals(msgId)) {
            confMsg.svcConfigMstrPk_LC.setErrorInfo(1, msgId);
            // QC#24245 2018/06/13 Add End
            // QC#28772 2018/10/16 Add Start
        } else if (NWZC150001Constant.NWZM2286E.equals(msgId)) {
            confMsg.svcConfigMstrPk_LC.setErrorInfo(1, msgId);
            // QC#28772 2018/10/16 Add End
            // 2019/12/13 QC#54230 Add Start
        } else if (NWAM0976W.equals(msgId)) {
            confMsg.shipToCustAcctNm_LC.setErrorInfo(2, msgId);
            confMsg.shipToCustAcctCd_LC.setErrorInfo(2, msgId);
            confMsg.shipToCustCd_LC.setErrorInfo(2, msgId);
            // 2019/12/13 QC#54230 Add End
        }
    }

    private void relationCpoUpdateApiConfigErrMsg(NWAL1500CMsg bizMsg, NWAL1500_CSMsg confMsg, String msgId, NWZC150001PMsg cpoUpdApiMsg, String[] xxMsgParam) { // 2018/01/31 S21_NA#19808 Mod
        if (NSZC048001Constant.NSZM0401E.equals(msgId)) {
//            confMsg.mdlDescTxt_RC.setErrorInfo(1, msgId, xxMsgParam); 2016/03/05 S21_NA#5000#68
            confMsg.mdlNm_RC.setErrorInfo(1, msgId, xxMsgParam);
        } else if (NSZC048001Constant.NSZM0396E.equals(msgId)) {
//            confMsg.mdlDescTxt_RC.setErrorInfo(1, msgId, xxMsgParam); 2016/03/05 S21_NA#5000#68
            confMsg.mdlNm_RC.setErrorInfo(1, msgId, xxMsgParam);
        } else if (NWZC150001Constant.NWZM1794E.equals(msgId)) { // 2016/01/13 S21_NA#2726 Add Start
            confMsg.configTpCd_RC.setErrorInfo(1, msgId, xxMsgParam);
        } else if (NWZC150001Constant.NWZM1926W.equals(msgId)) { // 2016/02/23 S21_NA#3239
            confMsg.xxChkBox_RC.setErrorInfo(2, msgId, xxMsgParam); // 2016/03/04 S21_NA#5000
        } else if (NWZC150001Constant.NWZM1452E.equals(msgId)) { // 2016/02/25 S21_NA#4440 Add Start
            confMsg.billToCustCd_RC.setErrorInfo(1, NWZC150001Constant.NWZM1452E);
            confMsg.billToCustAcctCd_RC.setErrorInfo(1, NWZC150001Constant.NWZM1452E);
        } else if (NWZC150001Constant.NWZM1453E.equals(msgId)) {
            confMsg.shipToCustCd_RC.setErrorInfo(1, NWZC150001Constant.NWZM1453E);
            confMsg.shipToCustAcctCd_RC.setErrorInfo(1, NWZC150001Constant.NWZM1453E);
            // Del Start 2018/02/26 QC#22967
        //} else if (NWZC150001Constant.NWZM1455E.equals(msgId)) {
        //    confMsg.billToCustCd_RC.setErrorInfo(1, NWZC150001Constant.NWZM1455E);
        //    confMsg.shipToCustCd_RC.setErrorInfo(1, NWZC150001Constant.NWZM1455E);
        //    // 2016/02/25 S21_NA#4440 Add End
            // Del End 2018/02/26 QC#22967
        } else if (NWZC150001Constant.NWZM1466E.equals(msgId)) { // 2016/03/16 S21_NA#5519 Add
            confMsg.svcConfigMstrPk_RC.setErrorInfo(1, msgId);
            // 2016/10/04 S21_NA#13170 Add Start
        } else if (NWZC150001Constant.NWZM1477E.equals(msgId)) {
            confMsg.shipToCustAcctCd_RC.setErrorInfo(1, NWZC150001Constant.NWZM1477E);
            // 2016/10/04 S21_NA#13170 Add End
        } else if (NWZC150001Constant.NWZM2033E.equals(msgId)) { // 2016/10/11 S21_NA#7924-2 Add
            confMsg.xxChkBox_RC.setErrorInfo(1, msgId);
            // Add Start 2018/02/26 QC#22967
        } else if (NWZC150001Constant.NWZM2254E.equals(msgId)) {
            confMsg.shipToCustCd_RC.setErrorInfo(1, NWZC150001Constant.NWZM2254E);
        } else if (NWZC150001Constant.NWZM2255E.equals(msgId)) {
            confMsg.billToCustCd_RC.setErrorInfo(1, NWZC150001Constant.NWZM2255E);
            // Add End 2018/02/26 QC#22967
            // 2018/03/28 S21_NA#24867 Add Start
        } else if (NWZC150001Constant.NWZM1480E.equals(msgId)) {
            confMsg.svcConfigMstrPk_RC.setErrorInfo(1, NWZC150001Constant.NWZM1480E);
            // 2018/03/28 S21_NA#24867 Add End
        } else if (NWZC150001Constant.NWZM2263E.equals(msgId)) { // 2018/05/20 S21_NA#25604 Add Start
            confMsg.svcConfigMstrPk_RC.setErrorInfo(1, msgId);
            // 2018/05/20 S21_NA#25604 Add End
            // QC#24245 2018/06/13 Add Start
        } else if (NWZC150001Constant.NWZM2270E.equals(msgId)) {
            confMsg.svcConfigMstrPk_RC.setErrorInfo(1, msgId);
            // QC#24245 2018/06/13 Add End
        } else if (NWZC150001Constant.NWZM2289E.equals(msgId)) {
            confMsg.configTpCd_RC.setErrorInfo(1, msgId);
            // 2018/10/26 S21_NA#28882 Add
        }
    }

    private void relationCpoUpdateApiDtlErrMsg(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg, String msgId, NWZC150001PMsg cpoUpdApiMsg, String[] xxMsgParam) {

        if (NWZC150001CpouConstant.NWZM0041E.equals(msgId)) {
            lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0042E.equals(msgId)) {
            lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0044E.equals(msgId)) {
            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0947E.equals(msgId)) {
            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0048E.equals(msgId)) {
            lineMsg.entCpoDtlDealSlsAmt_LL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0927E.equals(msgId)) {
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0054E.equals(msgId)) {
            bizMsg.slsRepTocCd.setErrorInfo(1, msgId);

        } else if (NWXC001001SalesRepValidator.MSG_ID_INVALID_SLS_REP.equals(msgId)) {
            bizMsg.slsRepTocCd.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0458E.equals(msgId)) {
            bizMsg.carrAcctNum.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0140E.equals(msgId)) {
            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0141E.equals(msgId)) {
            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0142E.equals(msgId)) {
            MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdseWithRgtnStsCd(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
            if (mdseMsg != null) {
                lineMsg.ordCustUomQty_LL.setErrorInfo(1, NWZM0884E, new String[] {String.valueOf(mdseMsg.cpoMinOrdQty.getValue()) });
            } else {
                lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId); // S21_NA#2082
            }

        } else if (NWZC150001CpouConstant.NWZM0143E.equals(msgId)) {
            MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdseWithRgtnStsCd(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
            if (mdseMsg != null) {
                lineMsg.ordCustUomQty_LL.setErrorInfo(1, NWZM0885E, new String[] {String.valueOf(mdseMsg.cpoMaxOrdQty.getValue()) });
            } else {
                lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId); // S21_NA#2082
            }

        } else if (NWZC150001CpouConstant.NWZM0144E.equals(msgId)) {
            MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdseWithRgtnStsCd(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
            if (mdseMsg != null) {
                lineMsg.ordCustUomQty_LL.setErrorInfo(1, NWZM0886E, new String[] {String.valueOf(mdseMsg.cpoIncrOrdQty.getValue()) });
            } else {
                lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId); // S21_NA#2082
            }

        } else if (NWZC150001CpouConstant.NWZM0148E.equals(msgId)) {
            lineMsg.entCpoDtlDealSlsAmt_LL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0152E.equals(msgId)) {
            lineMsg.entCpoDtlDealSlsAmt_LL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0153E.equals(msgId)) {
            lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0161E.equals(msgId)) {
            lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0335E.equals(msgId)) {
            lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0642E.equals(msgId)) {
            bizMsg.slsRepTocCd.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0263E.equals(msgId)) {
            bizMsg.slsRepTocCd.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0041E.equals(msgId)) {
            lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0458E.equals(msgId)) {
            bizMsg.carrAcctNum.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0418E.equals(msgId)) {
            lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0419E.equals(msgId)) {
            lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0047E.equals(msgId)) {
            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);

//        } else if (NWZC046001.NWZM0900W.equals(msgId)) {
//            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
//            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0942E.equals(msgId)) {
            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0943E.equals(msgId)) {
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM1215E.equals(msgId)) {
//            BigDecimal ordQty = cpoUpdApiMsg.ordQty_A1.getValue();
//            BigDecimal ordQty = cpoUpdApiMsg.ordQty_A1.getValue();
//            lineMsg.ordQty_LL.setErrorInfo(1, msgId, new String[] {ordQty.toString() });
//            doNothing(); // 2016/11/02 S21_NA#5394-3 Del
          lineMsg.xxChkBox_LL.setErrorInfo(1, msgId, new String[] {String.valueOf(lineMsg.ordQty_LL.getValueInt()) }); // 2016/11/02 S21_NA#5394-3 Add

        } else if (NWZC150001CpouConstant.NWZM1216E.equals(msgId)) {
            lineMsg.serNum_LL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM1298E.equals(msgId)) {
            lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM1311E.equals(msgId)) {
            lineMsg.mdseCd_LL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM1312E.equals(msgId)) {
            lineMsg.mdseCd_LL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM1262E.equals(msgId)) {
            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
            lineMsg.rtlSwhNm_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1468E.equals(msgId)) { // 2016/01/25 S21_NA#3505 Add
            lineMsg.mdseCd_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1469E.equals(msgId)) { // 2016/01/25 S21_NA#3505 Add
            lineMsg.dsOrdLineCatgCd_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1470E.equals(msgId)) { // 2016/01/25 S21_NA#3505 Add
            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1471E.equals(msgId)) { // 2016/01/25 S21_NA#3505 Add
            lineMsg.serNum_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1472E.equals(msgId)) { // 2016/01/25 S21_NA#3505 Add
            lineMsg.serNum_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1473E.equals(msgId)) { // 2016/01/25 S21_NA#3505 Add
            lineMsg.serNum_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1474E.equals(msgId)) { // 2016/01/25 S21_NA#3505 Add
            lineMsg.serNum_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1475E.equals(msgId)) { // 2016/01/25 S21_NA#3505 Add
            lineMsg.sbstMdseCd_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1476E.equals(msgId)) { // 2016/01/25 S21_NA#3505 Add
            lineMsg.dplyLineRefNum_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1478E.equals(msgId)) { // 2016/01/25 S21_NA#3505 Add
            lineMsg.dsOrdLineCatgCd_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1795E.equals(msgId)) { // 2016/01/25 S21_NA#3505 Add
            lineMsg.mdseCd_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1889E.equals(msgId)) { // 2016/01/26 S21_NA#3740 Add
            lineMsg.dsOrdLineCatgCd_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1484E.equals(msgId)) { // 2016/02/17 S21_NA#1703 Add
            lineMsg.mdseCd_LL.setErrorInfo(1, msgId);
//        } else if (NWZC150001Constant.NWZM1917E.equals(msgId)) { // 2016/02/17 S21_NA#1703 Add -> 2017/03/02 S21_NA#17714-2 Del Start
//            lineMsg.mdseCd_LL.setErrorInfo(1, msgId);  2017/03/02 S21_NA#17714-2 Del End
        } else if (NWZC150001Constant.NWZM1921E.equals(msgId)) { // 2016/02/25 S21_NA#1704 Add
            lineMsg.mdseCd_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1431E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            lineMsg.dsOrdLineCatgCd_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1432E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            lineMsg.ordLineSrcCd_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1433E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1434E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1420E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            lineMsg.flPrcListNm_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1435E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            lineMsg.crRebilCd_LL.setErrorInfo(1, msgId);
//        } else if (NWZC150001Constant.NWZM1436E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
//            lineMsg.splyTpCd_LL.setErrorInfo(1, msgId);
//        } else if (NWZC150001Constant.NWZM1438E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
//            lineMsg.bllgAttrbCustAcctCd_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1439E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            lineMsg.sbstMdseCd_LL.setErrorInfo(1, msgId);
//        } else if (NWZC150001Constant.NWZM1427E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
//            lineMsg.carrSvcLvlCd_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1419E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            lineMsg.prcCatgNm_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1928E.equals(msgId)) {
            lineMsg.serNum_LL.setErrorInfo(1, msgId); // 2016/03/16 S21_NA#5519 Add
        } else if (NWZC150001Constant.NWZM1530E.equals(msgId)) {
            lineMsg.mdseCd_LL.setErrorInfo(1, msgId); // 2016/04/22 S21_NA#7132 Add
        } else if (NWZC150001Constant.NWZM1917E.equals(msgId) //2016/06/17 S21_NA#9679 Add
                || NWZC150001Constant.NWZM1484E.equals(msgId) //
                || NWZC150001Constant.NWZM1485E.equals(msgId) //
                || NWZC150001Constant.NWZM1487E.equals(msgId) //
                // 2018/06/01 S21_NA#26273 del start
//                || NWZC150001Constant.NWZM1488E.equals(msgId) //
//                || NWZC150001Constant.NWZM1489E.equals(msgId) //
//                || NWZC150001Constant.NWZM1492E.equals(msgId) //
                // 2018/06/01 S21_NA#26273 del end
                ) {
            // Qty Check Error
            lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001CpouConstant.NWZM2229E.equals(msgId) //
                || NWZC150001CpouConstant.NWZM2230E.equals(msgId)) { // 2017/06/06 S21_NA#16443 Add Start
            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId); // 2017/06/06 S21_NA#16443 Add End
        } else if (NWZC150001Constant.NWZM2253E.equals(msgId)) { // 2018/02/13 S21_NA#22717 Add Start
            lineMsg.dsOrdLineCatgCd_LL.setErrorInfo(1, msgId); // 2018/02/13 S21_NA#22717 Add End
        // 2018/06/01 S21_NA#26273 add start
        } else if (NWZC150001Constant.NWZM1488E.equals(msgId)) {
            MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdseWithRgtnStsCd(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
            if (mdseMsg != null) {
                lineMsg.ordCustUomQty_LL.setErrorInfo(1, NWZM2023E, new String[] {String.valueOf(mdseMsg.cpoMinOrdQty.getValue()) });
            } else {
                lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId);
            }
        } else if (NWZC150001Constant.NWZM1489E.equals(msgId)) {
            MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdseWithRgtnStsCd(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
            if (mdseMsg != null) {
                lineMsg.ordCustUomQty_LL.setErrorInfo(1, NWZM2024E, new String[] {String.valueOf(mdseMsg.cpoMaxOrdQty.getValue()) });
            } else {
                lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId);
            }
        } else if (NWZC150001Constant.NWZM1492E.equals(msgId)) {
            MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdseWithRgtnStsCd(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
            if (mdseMsg != null) {
                lineMsg.ordCustUomQty_LL.setErrorInfo(1, NWZM2025E, new String[] {String.valueOf(mdseMsg.cpoIncrOrdQty.getValue()) });
            } else {
                lineMsg.ordCustUomQty_LL.setErrorInfo(1, msgId);
            }
        // 2018/06/01 S21_NA#26273 add end
        // QC#24705 2018/06/19 add Start
        } else if (NWZC150001Constant.NWZM2264E.equals(msgId)
                ||  NWZC150001Constant.NWZM2265E.equals(msgId)) {
            lineMsg.entCpoDtlDealSlsAmt_LL.setErrorInfo(1, msgId);
        // QC#24705 2018/06/19 add End
        } else if (NWZC150001Constant.NWZM2270E.equals(msgId)) {
            lineMsg.mdseCd_LL.setErrorInfo(1, msgId);// QC#24245 2018/06/13 Add
        } else if (NWZC150001Constant.NWZM2271E.equals(msgId)) { //2018/07/23 S21_NA#24745 Add Start
            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2272E.equals(msgId)) {
            lineMsg.mdseCd_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2273E.equals(msgId)) {
            // START 2023/02/13 A.Cullano [QC#61158, MOD]
            // lineMsg.mdseCd_LL.setErrorInfo(1, msgId); // 2018/07/23 S21_NA#24745 Add End
            lineMsg.dsOrdLineCatgCd_LL.setErrorInfo(1, msgId);
            // END 2023/02/13 A.Cullano [QC#61158, MOD]
        } else if (NWZC150001Constant.NWZM2277E.equals(msgId)) {
            lineMsg.mdseCd_LL.setErrorInfo(1, msgId); // 2018/08/24 S21_NA#27840 Add
        } else if (NWZC150001Constant.NWZM2285E.equals(msgId)) { // 2018/10/10 S21_NA#28728 Add
            lineMsg.serNum_LL.setErrorInfo(1, msgId); 
        } else if (NWZC150001Constant.NWZM2291E.equals(msgId)) { // 2018/10/31 S21_NA#28921 Add
            lineMsg.serNum_LL.setErrorInfo(1, msgId, xxMsgParam); 
        } else if (NWZC150001Constant.NWZM2294E.equals(msgId)) { // QC#29262 2018/11/27 Add
            lineMsg.ordLineSrcCd_LL.setErrorInfo(1, msgId, xxMsgParam); 
        } else if (NWZC150001Constant.NWZM2310E.equals(msgId)) { // 2019/07/30 S21_NA#52201 Add
            lineMsg.ordLineSrcCd_LL.setErrorInfo(1, msgId, xxMsgParam); 
        } else if (NWZC150001Constant.NWAM0037E.equals(msgId)) { // QC#29345 2018/12/05 Add
            lineMsg.mdseCd_LL.setErrorInfo(1, msgId, xxMsgParam);
        // 2018/12/20 QC#28928 Add Start
        } else if (NWZC150001Constant.NWZM2301E.equals(msgId)){
            lineMsg.serNum_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2302E.equals(msgId)){
            lineMsg.serNum_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2303E.equals(msgId)){
            lineMsg.serNum_LL.setErrorInfo(1, msgId);
        // 2018/12/20 QC#28928 Add End
        } else if (NWZC150001Constant.NWAM0965E.equals(msgId)) { // QC#29573 2019/01/08 Add
            lineMsg.ordLineSrcCd_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2306E.equals(msgId)) { // 2019/02/22 S21_NA#30449 Add
            lineMsg.xxChkBox_LL.setErrorInfo(1, msgId);
        // 2019/08/01 S21_NA#52156 Add Start
        } else if (NWZC150001CpouConstant.NWZM2311E.equals(msgId)) {
            lineMsg.xxChkBox_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWAM0909E.equals(msgId)) { // 2020/01/23 QC#55207-1 Add
            lineMsg.dsOrdLineCatgCd_LL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWAM0983E.equals(msgId)) { // 2020/06/09 QC#56978 NEW Error Code
            lineMsg.rtlWhNm_LL.setErrorInfo(1, msgId, xxMsgParam);
        }
        // 2019/08/01 S21_NA#52156 Add End
    }

    private void relationCpoUpdateApiDtlErrMsg(NWAL1500CMsg bizMsg, NWAL1500_DSMsg rmaLineMsg, String msgId, NWZC150001PMsg cpoUpdApiMsg, String[] xxMsgParam) {

        if (NWZC150001CpouConstant.NWZM0041E.equals(msgId)) {
            rmaLineMsg.ordCustUomQty_RL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0042E.equals(msgId)) {
            rmaLineMsg.ordCustUomQty_RL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0044E.equals(msgId)) {
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
            rmaLineMsg.rtlSwhNm_RL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0947E.equals(msgId)) {
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
            rmaLineMsg.rtlSwhNm_RL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0048E.equals(msgId)) {
            rmaLineMsg.entCpoDtlDealSlsAmt_RL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0927E.equals(msgId)) {
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0054E.equals(msgId)) {
            bizMsg.slsRepTocCd.setErrorInfo(1, msgId);

        } else if (NWXC001001SalesRepValidator.MSG_ID_INVALID_SLS_REP.equals(msgId)) {
            bizMsg.slsRepTocCd.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0458E.equals(msgId)) {
            bizMsg.carrAcctNum.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0140E.equals(msgId)) {
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
            rmaLineMsg.rtlSwhNm_RL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0141E.equals(msgId)) {
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
            rmaLineMsg.rtlSwhNm_RL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0142E.equals(msgId)) {
            MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdseWithRgtnStsCd(bizMsg.glblCmpyCd.getValue(), rmaLineMsg.mdseCd_RL.getValue());
            if (mdseMsg != null) {
                rmaLineMsg.ordCustUomQty_RL.setErrorInfo(1, NWZM0884E, new String[] {String.valueOf(mdseMsg.cpoMinOrdQty.getValue()) });
            } else {
                rmaLineMsg.ordCustUomQty_RL.setErrorInfo(1, msgId); // S21_NA#2082
            }

        } else if (NWZC150001CpouConstant.NWZM0143E.equals(msgId)) {
            MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdseWithRgtnStsCd(bizMsg.glblCmpyCd.getValue(), rmaLineMsg.mdseCd_RL.getValue());
            if (mdseMsg != null) {
                rmaLineMsg.ordCustUomQty_RL.setErrorInfo(1, NWZM0885E, new String[] {String.valueOf(mdseMsg.cpoMaxOrdQty.getValue()) });
            } else {
                rmaLineMsg.ordCustUomQty_RL.setErrorInfo(1, msgId); // S21_NA#2082
            }

        } else if (NWZC150001CpouConstant.NWZM0144E.equals(msgId)) {
            MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdseWithRgtnStsCd(bizMsg.glblCmpyCd.getValue(), rmaLineMsg.mdseCd_RL.getValue());
            if (mdseMsg != null) {
                rmaLineMsg.ordCustUomQty_RL.setErrorInfo(1, NWZM0886E, new String[] {String.valueOf(mdseMsg.cpoIncrOrdQty.getValue()) });
            } else {
                rmaLineMsg.ordCustUomQty_RL.setErrorInfo(1, msgId); // S21_NA#2082
            }

        } else if (NWZC150001CpouConstant.NWZM0148E.equals(msgId)) {
            rmaLineMsg.entCpoDtlDealSlsAmt_RL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0152E.equals(msgId)) {
            rmaLineMsg.entCpoDtlDealSlsAmt_RL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0153E.equals(msgId)) {
            rmaLineMsg.ordCustUomQty_RL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0161E.equals(msgId)) {
            rmaLineMsg.ordCustUomQty_RL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0335E.equals(msgId)) {
            rmaLineMsg.ordCustUomQty_RL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0642E.equals(msgId)) {
            bizMsg.slsRepTocCd.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0263E.equals(msgId)) {
            bizMsg.slsRepTocCd.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0041E.equals(msgId)) {
            rmaLineMsg.ordQty_RL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0458E.equals(msgId)) {
            bizMsg.carrAcctNum.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0418E.equals(msgId)) {
            rmaLineMsg.ordCustUomQty_RL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM0419E.equals(msgId)) {
            rmaLineMsg.mdseCd_RL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0047E.equals(msgId)) {
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
            rmaLineMsg.rtlSwhNm_RL.setErrorInfo(1, msgId);

//        } else if (NWZC046001.NWZM0900W.equals(msgId)) {
//            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
//            rmaLineMsg.rtlSwhNm_RL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0942E.equals(msgId)) {
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
            rmaLineMsg.rtlSwhNm_RL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM0943E.equals(msgId)) {
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM1215E.equals(msgId)) {
//            BigDecimal ordQty = cpoUpdApiMsg.ordQty_A1.getValue();
//            BigDecimal ordQty = cpoUpdApiMsg.ordQty_A1.getValue();
//            rmaLineMsg.ordQty_RL.setErrorInfo(1, msgId, new String[] {ordQty.toString() });
            doNothing();

        } else if (NWZC150001CpouConstant.NWZM1216E.equals(msgId)) {
            rmaLineMsg.serNum_RL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM1298E.equals(msgId)) {
            rmaLineMsg.ordCustUomQty_RL.setErrorInfo(1, msgId); // S21_NA#2082

        } else if (NWZC150001CpouConstant.NWZM1311E.equals(msgId)) {
            rmaLineMsg.mdseCd_RL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM1312E.equals(msgId)) {
            rmaLineMsg.mdseCd_RL.setErrorInfo(1, msgId);

        } else if (NWZC150001CpouConstant.NWZM1262E.equals(msgId)) {
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
            rmaLineMsg.rtlSwhNm_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1468E.equals(msgId)) { // 2016/01/13 S21_NA#2726 Add
            rmaLineMsg.mdseCd_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1469E.equals(msgId)) { // 2016/01/13 S21_NA#2726 Add
            rmaLineMsg.dsOrdLineCatgCd_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1470E.equals(msgId)) { // 2016/01/13 S21_NA#2726 Add
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1471E.equals(msgId)) { // 2016/01/13 S21_NA#2726 Add
            rmaLineMsg.serNum_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1472E.equals(msgId)) { // 2016/01/13 S21_NA#2726 Add
            rmaLineMsg.serNum_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1473E.equals(msgId)) { // 2016/01/13 S21_NA#2726 Add
            rmaLineMsg.serNum_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1476E.equals(msgId)) { // 2016/01/13 S21_NA#2726 Add
            rmaLineMsg.dplyLineRefNum_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1795E.equals(msgId)) { // 2016/01/13 S21_NA#2726 Add
            rmaLineMsg.mdseCd_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1889E.equals(msgId)) { // 2016/01/26 S21_NA#3740 Add
            rmaLineMsg.dsOrdLineCatgCd_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1921E.equals(msgId)) { // 2016/02/25 S21_NA#1704 Add
            rmaLineMsg.mdseCd_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1431E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            rmaLineMsg.dsOrdLineCatgCd_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1433E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1434E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1420E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            rmaLineMsg.flPrcListNm_RL.setErrorInfo(1, msgId);
//        } else if (NWZC150001Constant.NWZM1438E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
//            rmaLineMsg.bllgAttrbCustAcctCd_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1419E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            rmaLineMsg.prcCatgNm_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1593E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            rmaLineMsg.hddRmvCd_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1594E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            rmaLineMsg.rtrnRsnCd_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1595E.equals(msgId)) { // 2016/02/28 S21_NA#4291 Add
            rmaLineMsg.thirdPtyDspTpCd_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1928E.equals(msgId)) { // 2016/03/16 S21_NA#5519 Add
            rmaLineMsg.serNum_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM1530E.equals(msgId)) {
            rmaLineMsg.mdseCd_RL.setErrorInfo(1, msgId); // 2016/04/22 S21_NA#7132 Add
        } else if (NWZC150001Constant.NWZM1507E.equals(msgId)) {
            rmaLineMsg.mdseCd_RL.setErrorInfo(1, msgId); // 2016/05/26 S21_NA#5668 Add
        } else if (NWZC153001Constant.NWZM1742E.equals(msgId)) {
            rmaLineMsg.xxChkBox_RL.setErrorInfo(1, msgId); // 2016/06/20 S21_NA#5331-3 Add
        } else if (NWZC153001Constant.NWZM2020E.equals(msgId)) {
            rmaLineMsg.dplyLineRefNum_RL.setErrorInfo(1, msgId); // 2016/09/27 S21_NA#9102 Add Start
        } else if (NWZC153001Constant.NWZM2057E.equals(msgId)) {
            rmaLineMsg.hddRmvCd_RL.setErrorInfo(1, msgId); // 2016/12/20 S21_NA#15898-2 Add
        } else if (NWZC150001Constant.NWZM1526E.equals(msgId)) {
            rmaLineMsg.mdseCd_RL.setErrorInfo(1, msgId); // 2017/01/19 S21_NA#13768-3 Add
        } else if (NWZC150001Constant.NWZM2250E.equals(msgId)) { //2017/12/21 S21_NA#20050 Add
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2271E.equals(msgId)) { //2018/07/23 S21_NA#24745 Add Start
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2272E.equals(msgId)) {
            rmaLineMsg.mdseCd_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2273E.equals(msgId)) {
            rmaLineMsg.mdseCd_RL.setErrorInfo(1, msgId); // 2018/07/23 S21_NA#24745 Add End
        } else if (NWZC150001Constant.NWZM2275E.equals(msgId)) {
            rmaLineMsg.mdseCd_RL.setErrorInfo(1, msgId); // 2018/08/02 S21_NA#26181 Add
        } else if (NWZC150001Constant.NWZM2278E.equals(msgId)) { // 2018/08/21 S21_NA#26767 Add Start
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2279E.equals(msgId)) {
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2280E.equals(msgId)) {
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2281E.equals(msgId)) {
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2282E.equals(msgId)) {
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2283E.equals(msgId)) {
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2284E.equals(msgId)) {
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId);  // 2018/08/21 S21_NA#26767 Add End
        } else if (NWZC150001Constant.NWZM2287E.equals(msgId)) { // 2018/10/18 S21_NA#26229 Add Start
            rmaLineMsg.dsOrdLineCatgCd_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2288E.equals(msgId)) {
            rmaLineMsg.dsOrdLineCatgCd_RL.setErrorInfo(1, msgId);  // 2018/10/18 S21_NA#26229 Add End
        } else if (NWZC150001Constant.NWZM2292E.equals(msgId)) { // 2018/11/01 S21_NA#28928 Add
            rmaLineMsg.serNum_RL.setErrorInfo(1, msgId); 
        // 2018/12/20 QC#28928 Add Start
        } else if (NWZC150001Constant.NWZM2302E.equals(msgId)){
            rmaLineMsg.serNum_RL.setErrorInfo(1, msgId);
        } else if (NWZC150001Constant.NWZM2303E.equals(msgId)){
            rmaLineMsg.serNum_RL.setErrorInfo(1, msgId);
        // 2018/12/20 QC#28928 Add End
        } 
    }

    private String getConfigWriter(NWAL1500_ASMsg configLineMsg, NWAL1500_GCMsgArray slsCreditConfigLineArray) { // 2018/01/31 S21_NA#19808 Mod
        for (int n = 0; n < slsCreditConfigLineArray.getValidCount(); n++) {
            NWAL1500_GCMsg slsCreditConfigLine = slsCreditConfigLineArray.no(n);
            if (configLineMsg.dsOrdPosnNum_LC.getValue().equals(slsCreditConfigLine.dsOrdPosnNum_GS.getValue())
                    && "WRITER".equals(slsCreditConfigLine.slsRepRoleTpCd_GS.getValue())) { // Code Class SLS_REP_ROLE_TP!!!!!!
                return slsCreditConfigLine.slsRepTocCd_GS.getValue();
            }
        }
        return null;
    }

    private String getConfigWriter(NWAL1500_CSMsg rmaConfigLineMsg, NWAL1500_HCMsgArray slsCreditConfigLineArray) { // 2018/01/31 S21_NA#19808 Mod
        for (int n = 0; n < slsCreditConfigLineArray.getValidCount(); n++) {
            NWAL1500_HCMsg slsCreditConfigLine = slsCreditConfigLineArray.no(n);
            if (rmaConfigLineMsg.dsOrdPosnNum_RC.getValue().equals(slsCreditConfigLine.dsOrdPosnNum_HS.getValue())
                    && "WRITER".equals(slsCreditConfigLine.slsRepRoleTpCd_HS.getValue())) { // Code Class SLS_REP_ROLE_TP!!!!!!
                return slsCreditConfigLine.slsRepTocCd_HS.getValue();
            }
        }
        return null;
    }

//    private BigDecimal getUnitNetWt(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {
//        BigDecimal inPountNetWt = getInPoundNetWt(bizMsg, lineMsg.mdseCd_LL.getValue(), lineMsg.ordQty_LL.getValue());
//        if (null == inPountNetWt) {
//            lineMsg.mdseCd_LL.setErrorInfo(1, NWAM0311E, new String[] {"MDSE_STORE_PKG", lineMsg.mdseCd_LL.getValue()});
//            return null;
//        }
//        return inPountNetWt;
//    }
//
//    private BigDecimal getUnitNetWt(NWAL1500CMsg bizMsg, NWAL1500_DCMsg rmaLineMsg) {
//        BigDecimal inPountNetWt = getInPoundNetWt(bizMsg, rmaLineMsg.mdseCd_RL.getValue(), rmaLineMsg.ordQty_RL.getValue());
//        if (null == inPountNetWt) {
//            rmaLineMsg.mdseCd_RL.setErrorInfo(1, NWAM0311E, new String[] {"MDSE_STORE_PKG", rmaLineMsg.mdseCd_RL.getValue()});
//            return null;
//        }
//        return inPountNetWt;
//    }

    private void setDealAndFuncPrcListPrcAmt(NWAL1500CMsg bizMsg, String dealCcyCd, NWAL1500_BSMsg lineMsg, NWAL1500_ISMsgArray prcElemArray, NWZC150001_APMsg dtlPMsg) { // 2018/01/31 S21_NA#19808 Mod
        if (ZYPCommonFunc.hasValue(lineMsg.dsCpoLineSubNum_LL)) {
            // for set component
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_A1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_A1, BigDecimal.ZERO);
            return;
        }
        NWAL1500_ISMsg prcElemBase = null;
        for (int n = 0; n < prcElemArray.getValidCount(); n++) {
            NWAL1500_ISMsg prcElem = prcElemArray.no(n);
            if (CONFIG_CATG.OUTBOUND.equals(prcElem.configCatgCd_LP.getValue())
                    && lineMsg.cpoDtlLineNum_LL.getValue().equals(prcElem.cpoDtlLineNum_LP.getValue())
                    && lineMsg.cpoDtlLineSubNum_LL.getValue().equals(prcElem.cpoDtlLineSubNum_LP.getValue())
                    && PRC_DTL_GRP.BASE_PRICE.equals(prcElem.prcDtlGrpCd_LP.getValue())) {
                prcElemBase = prcElem;
                break;
            }
        }
        if (null != prcElemBase) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_A1, prcElemBase.autoPrcAmtRate_LP);
            if (dealCcyCd.isEmpty()) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_A1, prcElemBase.autoPrcAmtRate_LP);
            } else {
                // 2019/11/27 S21_NA#54206 Mod Start
//                BigDecimal funcAmt = calcFuncAmt(bizMsg.glblCmpyCd.getValue(), dealCcyCd, lineMsg.prcBaseDt_LL.getValue(), prcElemBase.autoPrcAmtRate_LP.getValue());
                BigDecimal funcAmt = calcFuncAmt(bizMsg.glblCmpyCd.getValue(), dealCcyCd, bizMsg.slsDt.getValue(), prcElemBase.autoPrcAmtRate_LP.getValue());
                // 2019/11/27 S21_NA#54206 Mod End
                if (funcAmt == null) {
                    lineMsg.prcCatgNm_LL.setErrorInfo(1, NWAM0400E, new String[]{dealCcyCd});
                    bizMsg.setMessageInfo(NWAM0673E, new String[] {"Pricing" });
                    return;
                } else {
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_A1, funcAmt);
                }
            }
        } else {
            bizMsg.setMessageInfo(NWAM0673E, new String[] {"Pricing" });
        }
        return;
    }

    private void setDealAndFuncPrcListPrcAmt(NWAL1500CMsg bizMsg, String dealCcyCd, NWAL1500_DSMsg rmaLineMsg, NWAL1500_ISMsgArray prcElemArray, NWZC150001_rtnDtlPMsg dtlPMsg) { // 2018/01/31 S21_NA#19808 Mod
        if (ZYPCommonFunc.hasValue(rmaLineMsg.dsCpoLineSubNum_RL)) {
            // for set component
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_B1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_B1, BigDecimal.ZERO);
            return;
        }
        NWAL1500_ISMsg prcElemBase = null;
        for (int n = 0; n < prcElemArray.getValidCount(); n++) {
            NWAL1500_ISMsg prcElem = prcElemArray.no(n);
            if (CONFIG_CATG.INBOUND.equals(prcElem.configCatgCd_LP.getValue())
                    && rmaLineMsg.cpoDtlLineNum_RL.getValue().equals(prcElem.cpoDtlLineNum_LP.getValue())
                    && rmaLineMsg.cpoDtlLineSubNum_RL.getValue().equals(prcElem.cpoDtlLineSubNum_LP.getValue())
                    && PRC_DTL_GRP.BASE_PRICE.equals(prcElem.prcDtlGrpCd_LP.getValue())) {
                prcElemBase = prcElem;
                break;
            }
        }
        if (null != prcElemBase) {
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.dealPrcListPrcAmt_RL, prcElemBase.autoPrcAmtRate_LP);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_B1, prcElemBase.autoPrcAmtRate_LP);
            if (dealCcyCd.isEmpty()) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_B1, prcElemBase.autoPrcAmtRate_LP);
            } else {
                // 2019/11/27 S21_NA#54206 Mod Start
//               BigDecimal funcAmt = calcFuncAmt(bizMsg.glblCmpyCd.getValue(), dealCcyCd, rmaLineMsg.prcBaseDt_RL.getValue(), prcElemBase.autoPrcAmtRate_LP.getValue());
               BigDecimal funcAmt = calcFuncAmt(bizMsg.glblCmpyCd.getValue(), dealCcyCd, bizMsg.slsDt.getValue(), prcElemBase.autoPrcAmtRate_LP.getValue());
               // 2019/11/27 S21_NA#54206 Mod End
                if (funcAmt == null) {
                    rmaLineMsg.prcCatgNm_RL.setErrorInfo(1, NWAM0400E, new String[]{dealCcyCd});
                    bizMsg.setMessageInfo(NWAM0673E, new String[] {"Pricing" });
                    return;
                } else {
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_B1, funcAmt);
                }
            }
        } else {
            bizMsg.setMessageInfo(NWAM0673E, new String[] {"Pricing" });
        }
        return;
    }

    /**
     * Compare Class for Business Message Pricing elements
     * @author Q05163
     *
     */
//    private class NWAL1500CompareICMsg implements Comparator<NWAL1500_ISMsg> {
//
//        @Override
//        public int compare(NWAL1500_ISMsg o1, NWAL1500_ISMsg o2) {
//            return o1.xxCellIdx_LP.getValue().compareTo(o2.xxCellIdx_LP.getValue());
//        }
//    }

    /**
     * Compare Class for Global Message Pricing elements
     * @author Q05163
     *
     */
//    private class NWAL1500CompareISMsg implements Comparator<NWAL1500_ISMsg> {
//
//        @Override
//        public int compare(NWAL1500_ISMsg o1, NWAL1500_ISMsg o2) {
//            return o1.xxCellIdx_LP.getValue().compareTo(o2.xxCellIdx_LP.getValue());
//        }
//    }

//    private boolean hasToBeCancelLineConf(NWAL1500CMsg bizMsg, List<String> configPosList, List<String> lineNumList) {
//        configPosList.clear();
//        lineNumList.clear();
//
//        boolean hasToBeCancel = false;
//        if (!ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
//            return false;
//        } else {
//            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//                NWAL1500_BCMsg lineMsg = bizMsg.B.no(i);
//                if (bizMsg.varCharConstVal_TB.getValue().equals(lineMsg.ordLineStsDescTxt_LL.getValue())) { // stringToBeCancelled(bizMsg.glblCmpyCd.getValue()) should be VarcharConst
//                    String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
//                    if (!configPosList.contains(dsOrdPosnNum)) {
//                        configPosList.add(dsOrdPosnNum);
//                    }
//                    lineNumList.add(getChekLineNum(lineMsg));
//                    hasToBeCancel = true;
//                }
//            }
//        }
//        return hasToBeCancel;
//    }

//    private boolean callDsCpoUpdateApiWithLineCancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWZC150001PMsg cpoUpdApiReqPMsg, List<String> configPosList, List<String> lineNumList) {
//
//        cpoUpdApiReqPMsg.xxModeCd.setValue(NWZC150001Constant.MODE_CANCEL);
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//
//            final NWAL1500_ACMsg configMsg = bizMsg.A.no(i);
//
//            if (!configPosList.contains(configMsg.dsOrdPosnNum_LC.getValue())) {
//                continue;
//            }
//            String rqstMode = "";
//            if (ZYPCommonFunc.hasValue(configMsg.dsCpoConfigPk_LC)) {
//                rqstMode = NWZC150001Constant.RQST_TP_CONFIG_MODIFY;
//            } else {
//                rqstMode = NWZC150001Constant.RQST_TP_CONFIG_NEW;
//            }
//            setConfigUpdApiReqDtlPMsg(rqstMode, cpoUpdApiReqPMsg, bizMsg, configMsg);
//        }
//
//        // For Performance QC#8166 Add Start
//        Map<String, String> ccyCdMap = new HashMap<String, String>();
//        Map<String, BigDecimal> inPoundWtMap = new HashMap<String, BigDecimal>();
//        // For Performance QC#8166 Add End
//
//        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//
//            final NWAL1500_BCMsg lineMsg = bizMsg.B.no(i);
//
//            String compDtlLine = lineMsg.cpoDtlLineNum_LL.getValue() + "." + lineMsg.cpoDtlLineSubNum_LL.getValue();
//            if (!lineNumList.contains(compDtlLine)) {
//                continue;
//            }
//            setCpoUpdApiReqDtlPMsg(NWZC150001Constant.RQST_TP_DTL_CANCEL, cpoUpdApiReqPMsg, bizMsg, lineMsg, ccyCdMap, inPoundWtMap);
//        }
//
//        setCpoUpdApiReqSalesCredit(cpoUpdApiReqPMsg, bizMsg, true, configPosList, new ArrayList<String>(0));
//
//        setCpoUpdApiReqPriceCalcBase(cpoUpdApiReqPMsg, bizMsg, true, lineNumList, new ArrayList<String>(0));
//
//        boolean isNormal = true;
//        if (cpoUpdApiReqPMsg.A.getValidCount() > 0) {
//
//            S21ApplicationCacheHolder.put(NWZC004001.Requester.NWAL0010_ORDER_ENTRY_SCREEN_NEW_ENTRY.toString(), Boolean.TRUE);
//
//            isNormal = callApi(bizMsg, cpoUpdApiReqPMsg, true);
//            if (isNormal) {
//                EZDConnectionMgr.getInstance().commit();
//            }
//        }
//        return isNormal;
//    }

    /**
     * <pre>
     * get Func Amount
     * @param glblCmpyCd Global Company Code
     * @param dealCcyCd Deal Currency Code
     * @param slsDt Sales Date
     * @param dealAmt deal Amount
     * @return Function Amount
     * </pre>
     */
    public BigDecimal calcFuncAmt(String glblCmpyCd, String dealCcyCd, String slsDt, BigDecimal dealAmt) {

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
//            lineMsg.prcCatgDescTxt_LL.setErrorInfo(1, "NWAM0400E", new String[]{dealCcyCd});
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

//    private BigDecimal getInPoundNetWt(NWAL1500CMsg bizMsg, String mdseCd, BigDecimal ordQty) {
//        String localMdseCd = mdseCd;
//        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), localMdseCd);
//        if (null != mdseTMsg) {
//            localMdseCd = mdseTMsg.mdseCd.getValue();
//        }
//        MDSE_STORE_PKGTMsg strePkgTMsg = new MDSE_STORE_PKGTMsg();
//        strePkgTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//        strePkgTMsg.mdseCd.setValue(localMdseCd);
//        strePkgTMsg.pkgUomCd.setValue(bizMsg.varCharConstVal_PU.getValue());
//
//        strePkgTMsg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKey(strePkgTMsg);
//        if (null == strePkgTMsg) {
//            return null;
//        }
//        BigDecimal inPoundWt = strePkgTMsg.inPoundWt.getValue();
//        return inPoundWt.multiply(ordQty);
//    }

    /**
     * <pre>
     * check headder saved or not
     * @param glblCmpyCd Global Company Code
     * @param cpoOrdNum CPO ORD NUM
     * @return true: Saved false: other
     * <pre>
     */
    public static boolean isHdrSaved(String glblCmpyCd, String cpoOrdNum) {
        if (null == cpoOrdNum || !ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return false;
        }
        CPOTMsg cpoTMsg = new CPOTMsg();
        cpoTMsg.glblCmpyCd.setValue(glblCmpyCd);
        cpoTMsg.cpoOrdNum.setValue(cpoOrdNum);
        cpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(cpoTMsg);
        if (null != cpoTMsg) {
            if (ORD_HDR_STS.SAVED.equals(cpoTMsg.ordHdrStsCd.getValue())) {
                return true;
            }
        }
        return false;
    }

//    private String getChekLineNum(EZDCMsg ezdCMsg) {
//        String rtrnLineNum = "";
//        if (ezdCMsg instanceof NWAL1500_BCMsg) {
//            NWAL1500_BCMsg lineMsg = (NWAL1500_BCMsg) ezdCMsg;
//            rtrnLineNum = lineMsg.cpoDtlLineNum_LL.getValue() + "." + lineMsg.cpoDtlLineSubNum_LL.getValue();
//        } else if (ezdCMsg instanceof NWAL1500_DCMsg) {
//            NWAL1500_DCMsg rmaLineMsg = (NWAL1500_DCMsg) ezdCMsg;
//            rtrnLineNum = rmaLineMsg.cpoDtlLineNum_RL.getValue() + "." + rmaLineMsg.cpoDtlLineSubNum_RL.getValue();
//        }
//        return rtrnLineNum;
//    }

    private boolean callSvcMachMstrUpdApi(String glblCmpyCd, String slsDt, BigDecimal svcMachMstrPk, Map<String, Object> cpoDtlDataMap, NWAL1500CMsg bizMsg) {

        NSZC001001PMsg mmUpdtApiPMsg = setSvcMachUpdtApiParam(glblCmpyCd, slsDt, svcMachMstrPk, cpoDtlDataMap);

        new NSZC001001().execute(mmUpdtApiPMsg, ONBATCH_TYPE.ONLINE);
        if (ZYPCommonFunc.hasValue(mmUpdtApiPMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(mmUpdtApiPMsg);
            S21ApiMessage msg = msgList.get(0);
            bizMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return false;
        } else {
            return true;
        }
    }

    private NSZC001001PMsg setSvcMachUpdtApiParam(String glblCmpyCd, String slsDt, BigDecimal svcMachMstrPk, Map<String, Object> cpoDtlDataMap) {

        NSZC001001PMsg mmUpdtApiPMsg = new NSZC001001PMsg();
        mmUpdtApiPMsg.glblCmpyCd.setValue(glblCmpyCd);
        mmUpdtApiPMsg.slsDt.setValue(slsDt);
        mmUpdtApiPMsg.xxModeCd.setValue(ProcessMode.ALLOCATION_ON.code);
        mmUpdtApiPMsg.svcMachMstrPk.setValue(svcMachMstrPk);

        mmUpdtApiPMsg.stkStsCd.setValue((String) cpoDtlDataMap.get("STK_STS_CD"));

        // S21_NA#4323
        // mmUpdtApiPMsg.cpoOrdNum.setValue((String)
        // cpoDtlDataMap.get("CPO_ORD_NUM"));
        // mmUpdtApiPMsg.cpoDtlLineNum.setValue((String)
        // cpoDtlDataMap.get("CPO_DTL_LINE_NUM"));
        // mmUpdtApiPMsg.cpoDtlLineSubNum.setValue((String)
        // cpoDtlDataMap.get("CPO_DTL_LINE_SUB_NUM"));

        // S21_NA#4323
        ZYPEZDItemValueSetter.setValue(mmUpdtApiPMsg.trxHdrNum, (String) cpoDtlDataMap.get("CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(mmUpdtApiPMsg.trxLineNum, (String) cpoDtlDataMap.get("CPO_DTL_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(mmUpdtApiPMsg.trxLineSubNum, (String) cpoDtlDataMap.get("CPO_DTL_LINE_SUB_NUM"));

        return mmUpdtApiPMsg;
    }

    private boolean isCriticEssLine(Map<String, Object> cpoDtlDataMap) {
        String backOrdImpctTpCd = (String) cpoDtlDataMap.get("BACK_ORD_IMPCT_TP_CD");
        if (!ZYPCommonFunc.hasValue(backOrdImpctTpCd)) {
            return false;
        }
        if (BACK_ORD_IMPCT_TP.CRITICAL.equals(backOrdImpctTpCd)) { // Mod 2016/12/02 M.Ohno S21_NA#14035
        //if (BACK_ORD_IMPCT_TP.CRITICAL.equals(backOrdImpctTpCd) || BACK_ORD_IMPCT_TP.ESSENTIAL.equals(backOrdImpctTpCd)) {
            return true;
        }
        return false;
    }

    private NWZC102001PMsg setAllocApiParam(String glblCmpyCd, String slsDt, Map<String, Object> cpoDtlDataMap) {
        NWZC102001PMsg allocApiPMsg = new NWZC102001PMsg();

        allocApiPMsg.glblCmpyCd.setValue(glblCmpyCd);
        allocApiPMsg.xxOrigFuncTpCd.setValue(NWZC102001.ORG_FUNC_CD_ORDER_ENTRY);
        allocApiPMsg.xxRqstTpCd.setValue(NWZC102001.REQ_TP_NEW);
        allocApiPMsg.xxAllocTpCd.setValue(NWZC102001.ALLOC_TP_CD_HARD_ALLOC);
        allocApiPMsg.xxPrtlAcptFlg.setValue(ZYPConstant.FLG_ON_1);
        allocApiPMsg.xxItemFlipAcptFlg.setValue(ZYPConstant.FLG_ON_1);
        allocApiPMsg.xxWhFlipAcptFlg.setValue(ZYPConstant.FLG_ON_1);
        allocApiPMsg.trxSrcTpCd.setValue(TRX_SRC_TP.WHOLE_SALES);

        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxHdrNum, (String) cpoDtlDataMap.get("CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxLineNum, (String) cpoDtlDataMap.get("CPO_DTL_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxLineSubNum, (String) cpoDtlDataMap.get("CPO_DTL_LINE_SUB_NUM"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.mdseCd, (String) cpoDtlDataMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.cpoOrdTs, (String) cpoDtlDataMap.get("CPO_ORD_SUBMT_TS"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.rddDt, (String) cpoDtlDataMap.get("RDD_DT"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.rsdDt, (String) cpoDtlDataMap.get("RSD_DT"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.expdShipDt, (String) cpoDtlDataMap.get("EXPD_SHIP_DT"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.slsRepTocCd, (String) cpoDtlDataMap.get("SLS_REP_OR_SLS_TEAM_TOC_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.billToCustCd, (String) cpoDtlDataMap.get("BILL_TO_CUST_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.sellToCustCd, (String) cpoDtlDataMap.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.shipToCustCd, (String) cpoDtlDataMap.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.shipToStCd, (String) cpoDtlDataMap.get("SHIP_TO_ST_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.shipToPostCd, (String) cpoDtlDataMap.get("SHIP_TO_POST_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.invtyLocCd, (String) cpoDtlDataMap.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.stkStsCd, (String) cpoDtlDataMap.get("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.frtChrgToCd, (String) cpoDtlDataMap.get("FRT_CHRG_TO_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.frtChrgMethCd, (String) cpoDtlDataMap.get("FRT_CHRG_METH_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.shpgSvcLvlCd, (String) cpoDtlDataMap.get("SHPG_SVC_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.shipCpltCd, (String) cpoDtlDataMap.get("SHIP_CPLT_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.setItemShipCpltFlg, (String) cpoDtlDataMap.get("SET_ITEM_SHIP_CPLT_FLG"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.uomCpltFlg, (String) cpoDtlDataMap.get("UOM_CPLT_FLG"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.uomCd, (String) cpoDtlDataMap.get("CUST_UOM_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxRqstQty, (BigDecimal) cpoDtlDataMap.get("ORD_QTY"));

        allocApiPMsg.slsDt.setValue(slsDt);
        return allocApiPMsg;
    }

    private boolean checkAllocationError(List<NWZC102001PMsg> allocApiPMsgList, List<Map<String, Object>> cpoDtlDataList) {
//    private boolean checkAllocationError(List<NWZC102001PMsg> allocApiPMsgList, boolean isBackOrdImpctProc) {
//    private boolean checkAllocationError(List<NWZC102001PMsg> allocApiPMsgList) { // 2016/01/26 S21_NA#3520
        for (NWZC102001PMsg allocApiPMsg : allocApiPMsgList) {

            // S21_NA#8196 MOD START
            boolean existShpgPlnValidated = existShpgPlnStsLine(allocApiPMsg, SHPG_STS.VALIDATED);
            if (allocApiPMsg.xxMsgIdList.getValidCount() > 0 && existShpgPlnValidated) {
                return false;
            }
            if (isBackOrdImpctProc(cpoDtlDataList, allocApiPMsg)) {
                if (existShpgPlnValidated) {
                    return false;
                }
            }
            // S21_NA#8196 MOD END
            // S21_NA#8196 DEL START   
            // 2016/01/26 S21_NA#3520 Add Start                
//            if (isBackOrdImpctProc) {
//                SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
//                shpgPlnMsg.setSQLID("006");
//                shpgPlnMsg.setConditionValue("glblCmpyCd01", allocApiPMsg.glblCmpyCd.getValue());
//                shpgPlnMsg.setConditionValue("trxHdrNum01", allocApiPMsg.trxHdrNum.getValue());
//                shpgPlnMsg.setConditionValue("trxLineNum01", allocApiPMsg.trxLineNum.getValue());
//                shpgPlnMsg.setConditionValue("trxLineSubNum01", allocApiPMsg.trxLineSubNum.getValue());
//                shpgPlnMsg.setConditionValue("shpgStsCd01", SHPG_STS.VALIDATED);
//
//                SHPG_PLNTMsgArray shpgPlnMsgArray = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByCondition(shpgPlnMsg);
//                if (shpgPlnMsgArray.getValidCount() != 0) {
//                    return false;
//                }
//            }
            // 2016/01/26 S21_NA#3520 Add End
            // S21_NA#8196 DEL END
        }

        return true;
    }

    // S21_NA#8196 ADD START
    private boolean existShpgPlnStsLine(NWZC102001PMsg allocApiPMsg, String shpgStsCd) {

        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        shpgPlnMsg.setSQLID("006");
        shpgPlnMsg.setConditionValue("glblCmpyCd01", allocApiPMsg.glblCmpyCd.getValue());
        shpgPlnMsg.setConditionValue("trxHdrNum01", allocApiPMsg.trxHdrNum.getValue());
        shpgPlnMsg.setConditionValue("trxLineNum01", allocApiPMsg.trxLineNum.getValue());
        shpgPlnMsg.setConditionValue("trxLineSubNum01", allocApiPMsg.trxLineSubNum.getValue());
        shpgPlnMsg.setConditionValue("shpgStsCd01", shpgStsCd);

        SHPG_PLNTMsgArray shpgPlnMsgArray = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByCondition(shpgPlnMsg);

        if (shpgPlnMsgArray.getValidCount() != 0) {
            return true;
        }
        return false;
    }

    private boolean isBackOrdImpctProc(List<Map<String, Object>> cpoDtlDataList, NWZC102001PMsg allocApiPMsg) {

        for (Map<String, Object> cpoDtlDataMap : cpoDtlDataList) {
            if (allocApiPMsg.trxLineNum.getValue().equals((String) cpoDtlDataMap.get("CPO_DTL_LINE_NUM"))
                    && allocApiPMsg.trxLineSubNum.getValue().equals((String) cpoDtlDataMap.get("CPO_DTL_LINE_SUB_NUM"))) {
                return isCriticEssLine(cpoDtlDataMap);
            }
        }
        return false;
    }
    // S21_NA#8196 ADD END

    /**
     * <pre>
     * call DS CPO Update API "Cancel" Mode
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @return true: normal end false: abnormal end
     */
    public boolean callDsCpoUpdateApiForOrderCancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        // 2018/01/31 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        NWZC150001PMsg cpoUpdApiReqPMsg = new NWZC150001PMsg();
        boolean isNormal = true;
        String rqstMode = "";


        // For Performance QC#8166 Add Start
        Map<String, String> ccyCdMap = new HashMap<String, String>();
        Map<String, BigDecimal> inPoundWtMap = new HashMap<String, BigDecimal>();
        // For Performance QC#8166 Add End

        // QC#4078 Mod
        setCpoUpdApiReqPMsg(NWZC150001Constant.MODE_CANCEL, cpoUpdApiReqPMsg, bizMsg, glblMsg, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N); // 2018/01/31 S21_NA#19808
        // Line Config: Config
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            final NWAL1500_ASMsg configMsg = glblMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(configMsg.dsCpoConfigPk_LC)) {
                rqstMode = NWZC150001Constant.RQST_TP_CONFIG_MODIFY;
                setConfigUpdApiReqDtlPMsg(rqstMode, cpoUpdApiReqPMsg, bizMsg, configMsg);
            }

            // Line Config: Line
            for (int lineIdx = 0; lineIdx < glblMsg.B.getValidCount(); lineIdx++) {
                // QC#5395
                if (ORD_LINE_STS.CANCELLED.equals(glblMsg.B.no(lineIdx).ordLineStsCd_LL.getValue())) {
                    continue;
                }
                // QC#5395

                // 2019/06/18 S21_NA#50732 Add Start
                if (ZYPCommonFunc.hasValue(configMsg.dsOrdPosnNum_LC) && ZYPCommonFunc.hasValue(glblMsg.B.no(lineIdx).dsOrdPosnNum_LL)
                    && configMsg.dsOrdPosnNum_LC.getValue().compareTo(glblMsg.B.no(lineIdx).dsOrdPosnNum_LL.getValue()) != 0) {
                    continue;
                }
                // 2019/06/18 S21_NA#50732 Add End

                final NWAL1500_BSMsg lineMsg = glblMsg.B.no(lineIdx);
                if (ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL)) {
                    rqstMode = NWZC150001Constant.RQST_TP_DTL_CANCEL;
                    setCpoUpdApiReqDtlPMsg(rqstMode, cpoUpdApiReqPMsg, bizMsg, glblMsg, configMsg, lineMsg, ccyCdMap, inPoundWtMap);
                }
            }
        }

        // RMA: Config
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            final NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(i);
            if (ZYPCommonFunc.hasValue(rmaConfigMsg.dsCpoConfigPk_RC)) {
                rqstMode = NWZC150001Constant.RQST_TP_CONFIG_MODIFY;
                setConfigUpdApiReqDtlPMsg(rqstMode, cpoUpdApiReqPMsg, bizMsg, rmaConfigMsg);
            }

            // RMA: Line
            for (int lineIdx = 0; lineIdx < glblMsg.D.getValidCount(); lineIdx++) {
                // QC#5395
                if (RTRN_LINE_STS.CANCELLED.equals(glblMsg.D.no(lineIdx).ordLineStsCd_RL.getValue())) {
                    continue;
                }
                // QC#5395

                // 2019/06/18 S21_NA#50732 Add Start
                if (ZYPCommonFunc.hasValue(rmaConfigMsg.dsOrdPosnNum_RC) && ZYPCommonFunc.hasValue(glblMsg.D.no(lineIdx).dsOrdPosnNum_RL)
                    && rmaConfigMsg.dsOrdPosnNum_RC.getValue().compareTo(glblMsg.D.no(lineIdx).dsOrdPosnNum_RL.getValue()) != 0) {
                    continue;
                }
                // 2019/06/18 S21_NA#50732 Add End

                final NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(lineIdx);
                rqstMode = NWZC150001Constant.RQST_TP_DTL_CANCEL;
                if (ZYPCommonFunc.hasValue(rmaLineMsg.rtrnLineStsDescTxt_RL)) {
                    rqstMode = NWZC150001Constant.RQST_TP_RTRN_DTL_CANCEL;
                    setCpoUpdApiReqDtlPMsg(rqstMode, cpoUpdApiReqPMsg, bizMsg, glblMsg, rmaConfigMsg, rmaLineMsg, ccyCdMap, inPoundWtMap);
                }
            }
        }

        setCpoUpdApiReqSalesCreditForCancel(cpoUpdApiReqPMsg, bizMsg);

        setCpoUpdApiReqPriceCalcBaseForCancel(cpoUpdApiReqPMsg, glblMsg);

        boolean outBoundRequest = cpoUpdApiReqPMsg.A.getValidCount() > 0;
        boolean inBoundRequest = cpoUpdApiReqPMsg.rtnDtl.getValidCount() > 0;
        if (outBoundRequest || inBoundRequest) {

            S21ApplicationCacheHolder.put(NWZC004001.Requester.NWAL0010_ORDER_ENTRY_SCREEN_NEW_ENTRY.toString(), Boolean.TRUE);

            isNormal = callApi(bizMsg, glblMsg, cpoUpdApiReqPMsg, true); // 2018/01/31 S21_NA#19808 Mod

            if (isNormal) {
                EZDConnectionMgr.getInstance().commit();
            }
        }

        if ("E".equals(bizMsg.getMessageKind()) || !isNormal) {
            return false;
        }
        return true;
    }

    private void setCpoUpdApiReqSalesCreditForCancel(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg) {
        int slsCrPMsgCount = cpoUpdApiReqPMsg.cpoSlsCr.getValidCount();
        // Header Data
        int n = 0;
        for (; n < bizMsg.F.getValidCount() && slsCrPMsgCount < cpoUpdApiReqPMsg.cpoSlsCr.length(); n++) {
            NWAL1500_FCMsg fCMsg = bizMsg.F.no(n);
            if (ZYPCommonFunc.hasValue(fCMsg.dsCpoSlsCrPk_FS)) {
                NWZC150001_cpoSlsCrPMsg slsCrPMsg = cpoUpdApiReqPMsg.cpoSlsCr.no(slsCrPMsgCount);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.xxRqstTpCd, fCMsg.xxRqstTpCd_FS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoSlsCrPk, fCMsg.dsCpoSlsCrPk_FS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsOrdPosnNum, fCMsg.dsOrdPosnNum_FS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.configCatgCd, "");
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoConfigPk, fCMsg.dsCpoConfigPk_FS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd, fCMsg.slsRepTocCd_FS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepRoleTpCd, fCMsg.slsRepRoleTpCd_FS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCrPct, fCMsg.slsRepCrPct_FS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsCrQuotFlg, fCMsg.slsCrQuotFlg_FS);
                slsCrPMsgCount++;
            }
        }
        // Config Line
        for (; n < bizMsg.G.getValidCount() && slsCrPMsgCount < cpoUpdApiReqPMsg.cpoSlsCr.length(); n++) {
            NWAL1500_GCMsg gCMsg = bizMsg.G.no(n);
            if (ZYPCommonFunc.hasValue(gCMsg.dsCpoSlsCrPk_GS)) {
                NWZC150001_cpoSlsCrPMsg slsCrPMsg = cpoUpdApiReqPMsg.cpoSlsCr.no(slsCrPMsgCount);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.xxRqstTpCd, gCMsg.xxRqstTpCd_GS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoSlsCrPk, gCMsg.dsCpoSlsCrPk_GS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsOrdPosnNum, gCMsg.dsOrdPosnNum_GS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoConfigPk, gCMsg.dsCpoConfigPk_GS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd, gCMsg.slsRepTocCd_GS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepRoleTpCd, gCMsg.slsRepRoleTpCd_GS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCrPct, gCMsg.slsRepCrPct_GS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsCrQuotFlg, gCMsg.slsCrQuotFlg_GS);
                slsCrPMsgCount++;
            }
        }
        // RMA
        for (; n < bizMsg.H.getValidCount() && slsCrPMsgCount < cpoUpdApiReqPMsg.cpoSlsCr.length(); n++) {
            NWAL1500_HCMsg hCMsg = bizMsg.H.no(n);
            if (ZYPCommonFunc.hasValue(hCMsg.dsCpoSlsCrPk_HS)) {
                NWZC150001_cpoSlsCrPMsg slsCrPMsg = cpoUpdApiReqPMsg.cpoSlsCr.no(slsCrPMsgCount);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.xxRqstTpCd, hCMsg.xxRqstTpCd_HS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoSlsCrPk, hCMsg.dsCpoSlsCrPk_HS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsOrdPosnNum, hCMsg.dsOrdPosnNum_HS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.configCatgCd, CONFIG_CATG.INBOUND);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoConfigPk, hCMsg.dsCpoConfigPk_HS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd, hCMsg.slsRepTocCd_HS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepRoleTpCd, hCMsg.slsRepRoleTpCd_HS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCrPct, hCMsg.slsRepCrPct_HS);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsCrQuotFlg, hCMsg.slsCrQuotFlg_HS);
                slsCrPMsgCount++;
            }
        }
        cpoUpdApiReqPMsg.cpoSlsCr.setValidCount(slsCrPMsgCount);
    }

    private void setCpoUpdApiReqPriceCalcBaseForCancel(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500SMsg glblMsg) {
        int priceListPMsgCount = cpoUpdApiReqPMsg.priceList.getValidCount();
        int n = 0;
        for (; n < glblMsg.I.getValidCount() && priceListPMsgCount < cpoUpdApiReqPMsg.priceList.length(); n++) {
            NWAL1500_ISMsg iCMsg = glblMsg.I.no(n);
            if (ZYPCommonFunc.hasValue(iCMsg.ordPrcCalcBasePk_LP)) {
                NWZC150001_priceListPMsg priceListPMsg = cpoUpdApiReqPMsg.priceList.no(priceListPMsgCount);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.ordPrcCalcBasePk, iCMsg.ordPrcCalcBasePk_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, iCMsg.cpoDtlLineNum_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineSubNum, iCMsg.cpoDtlLineSubNum_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondTpCd, iCMsg.prcCondTpCd_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondTpDescTxt, iCMsg.prcCondTpDescTxt_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcDtlGrpCd, iCMsg.prcDtlGrpCd_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcJrnlGrpCd, iCMsg.prcJrnlGrpCd_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, iCMsg.prcCondManEntryFlg_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManAddFlg, iCMsg.prcCondManAddFlg_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManDelFlg, iCMsg.prcCondManDelFlg_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.pkgUomCd, iCMsg.pkgUomCd_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondUnitCd, iCMsg.prcCondUnitCd_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCalcMethCd, iCMsg.prcCalcMethCd_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcAmtRate, iCMsg.autoPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.maxPrcAmtRate, iCMsg.maxPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.minPrcAmtRate, iCMsg.minPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, iCMsg.manPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.calcPrcAmtRate, iCMsg.calcPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.unitPrcAmt, iCMsg.unitPrcAmt_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.dsMdsePrcPk, iCMsg.dsMdsePrcPk_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.specCondPrcPk, iCMsg.specCondPrcPk_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.frtPerWtPk, iCMsg.frtPerWtPk_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcCcyCd, iCMsg.autoPrcCcyCd_LP);
                // QC#9700  2018/09/03 Add Start
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcRuleApplyFlg, iCMsg.prcRuleApplyFlg_LP);
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcRulePrcdPk, iCMsg.prcRulePrcdPk_LP);
                // QC#9700  2018/09/03 Add End
                priceListPMsgCount++;
            }
        }
        cpoUpdApiReqPMsg.priceList.setValidCount(priceListPMsgCount);
    }

    /**
     * <pre>
     * Update CPO for cancel
     * @param bizMsg Business Message
     * @return true: normal end false: abnormal end
     */
    public boolean updateCpoForCancel(NWAL1500CMsg bizMsg) {
        CPOTMsg cpoTMsg = new CPOTMsg();
        cpoTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        cpoTMsg.cpoOrdNum.setValue(bizMsg.cpoOrdNum.getValue());
        cpoTMsg = (CPOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(cpoTMsg);
        if (cpoTMsg == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrStsCd, ORD_HDR_STS.CANCELLED);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.openFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoCancFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoCancDt, bizMsg.slsDt);
        // QC#28598  2018/10/11 Add Start
        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrDplyStsCd, ORD_HDR_DPLY_STS.CANCELLED);
        // QC#28598  2018/10/11 Add End
        S21FastTBLAccessor.update(cpoTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(cpoTMsg.getReturnCode())) {
            // Error
            bizMsg.setMessageInfo(NAZM0493E);
            return false;
        }
        return true;
    }

    private static String[] getMsgParamArray(NWZC150002_xxMsgIdListPMsg xxMsg) {
        List<String> msgParamList = new ArrayList<String>(0);
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_0)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_0.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_1)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_1.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_2)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_2.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_3)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_3.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_4)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_4.getValue());
        }
        if (msgParamList.size() > 0) {
            return msgParamList.toArray(new String[0]);
        } else {
            return new String[]{};
        }
    }

    private static String[] getMsgParamArray(NWZC150003_xxMsgIdListPMsg xxMsg) {
        List<String> msgParamList = new ArrayList<String>(0);
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_0)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_0.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_1)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_1.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_2)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_2.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_3)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_3.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsg.xxMsgPrmTxt_4)) {
            msgParamList.add(xxMsg.xxMsgPrmTxt_4.getValue());
        }
        if (msgParamList.size() > 0) {
            return msgParamList.toArray(new String[0]);
        } else {
            return new String[]{};
        }
    }

    /**
     * <pre>
     * get order take mdse cd.
     * if this method coldn't get order take mdsecd, return parameter mdseCd.
     * (2015/12/04 S21_NA#1290 added)
     * @param glblCmpyCd Global Company code.
     * @param mdseCd Merchandise Code
     * @return Order Take Merchandise Code or parameter mdseCd
     */
    public static String getOrdTakeMdseCd(String glblCmpyCd, String mdseCd) {
        ORD_TAKE_MDSETMsg queryKeyOrdTakeMdse = new ORD_TAKE_MDSETMsg();
        queryKeyOrdTakeMdse.setSQLID("001");
        queryKeyOrdTakeMdse.glblCmpyCd.setValue(glblCmpyCd);
        queryKeyOrdTakeMdse.mdseCd.setValue(mdseCd);

        ORD_TAKE_MDSETMsgArray rsltArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(queryKeyOrdTakeMdse);
        if (0 == rsltArray.getValidCount()) {
            return mdseCd;
        } else {
            return rsltArray.no(0).ordTakeMdseCd.getValue();
        }
    }

//    // 2015/12/24 S21_NA#2009 Add Start
//    private static List<String> getBaseComponentDtlNum(NWAL1500CMsg bizMsg, String dsOrdPosnNum, String configCatgCd) {
//        List<String> dtlLineList = new ArrayList<String>(2);
//        if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
//            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//                NWAL1500_BCMsg lineMsg = bizMsg.B.no(i);
//                if (dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_LL.getValue()) && ZYPConstant.FLG_ON_Y.equals(lineMsg.baseCmptFlg_LL.getValue())) {
//                    dtlLineList.add(lineMsg.cpoDtlLineNum_LL.getValue());
//                    dtlLineList.add(lineMsg.cpoDtlLineSubNum_LL.getValue());
//                }
//            }
//        } else {
//            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
//                NWAL1500_DCMsg rmaLineMsg = bizMsg.D.no(i);
//                if (dsOrdPosnNum.equals(rmaLineMsg.dsOrdPosnNum_RL.getValue()) && ZYPConstant.FLG_ON_Y.equals(rmaLineMsg.baseCmptFlg_RL.getValue())) {
//                    dtlLineList.add(rmaLineMsg.cpoDtlLineNum_RL.getValue());
//                    dtlLineList.add(rmaLineMsg.cpoDtlLineSubNum_RL.getValue());
//                }
//            }
//        }
//        return dtlLineList;
//    }
//
//    private static boolean isSameLine(String origCpoDtlLineNum, String origCpoDtlLineSubNum, String targetCpoDtlLineNum, String targetCpoDtlLineSubNum) {
//        String origLine = origCpoDtlLineNum + origCpoDtlLineSubNum;
//        String targetLine = targetCpoDtlLineNum + targetCpoDtlLineSubNum;
//        return S21StringUtil.isEquals(origLine, targetLine);
//    }
//    // 2015/12/24 S21_NA#2009 Add End

    // 2015/12/24 S21_NA#2123 Add Start
    // 2019/11/01 S21_NA#54485 Mod Start
    // 2019/11/11 S21_NA#54509-1 Mod Start
//    private static void setSubstituteMdse(NWAL1500_BSMsg lineMsg, NWZC150001_APMsg ordDtlPMsg) {
//    private static void setSubstituteMdse(String glblCmpyCd, NWAL1500_BSMsg lineMsg, NWZC150001_APMsg ordDtlPMsg) {
    private static void setSubstituteMdse(NWAL1500_BSMsg lineMsg, NWZC150001_APMsg ordDtlPMsg) {
        // 2019/11/11 S21_NA#54509-1 Mod End
        // 2019/11/01 S21_NA#54485 Mod End
        if (ZYPCommonFunc.hasValue(lineMsg.sbstMdseCd_LL)) {
            // 2019/11/01 S21_NA#54509 Mod Start
            // 2019/11/11 S21_NA#54509-1 Mod Start
//            List<String> loanWhList = NWAL1500CommonLogic.getVarCharConstDataList(glblCmpyCd, LOAN_DUMMY_WH_CD);
//
//            if (loanWhList != null
//                    && loanWhList.contains(lineMsg.rtlWhCd_LL.getValue())) {
//                return;
//            }
            // 2019/11/11 S21_NA#54509-1 Mod End
            // 2019/11/01 S21_NA#54509 Mod End

            ordDtlPMsg.mdseCd_A1.setValue(lineMsg.sbstMdseCd_LL.getValue());
            ordDtlPMsg.origMdseCd_A1.setValue(lineMsg.mdseCd_LL.getValue());
            ordDtlPMsg.sbstMdseCd_A1.setValue(lineMsg.sbstMdseCd_LL.getValue());
        }
    }
    // 2015/12/24 S21_NA#2123 Add End

    // 2016/01/26 S21_NA#3522 Add Start
    /**
     * <pre>
     * Calculate the allocation start date.
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param slsDt Sales Date
     * @param daysPriAllocNum Days Priority Allocation Number
     * @param invtyLocCd Warehouse Code
     * @param calTpCdCache Cache
     * @return allocation start date
     */
    private static String calcAllocStartedDay(String glblCmpyCd, String slsDt, BigDecimal daysPriAllocNum, String invtyLocCd, Map<String, String> calTpCdCache) {

        CAL_RELNTMsg calRelnTMsg = null;
        String calTpCd = null;

        if (ZYPCommonFunc.hasValue(invtyLocCd)) {
            // 1.[ Search CAL_TP_CD Table]
            calRelnTMsg = new CAL_RELNTMsg();
            calRelnTMsg.glblCmpyCd.setValue(glblCmpyCd);
            calRelnTMsg.calSubTpCd.setValue(CAL_SUB_TP.WAREHOUSE_CALENDAR);
            calRelnTMsg.calMultCd.setValue(invtyLocCd);
            calRelnTMsg = (CAL_RELNTMsg) S21CacheTBLAccessor.findByKey(calRelnTMsg);
        }

        if (calRelnTMsg != null) {
            calTpCd = calRelnTMsg.calTpCd.getValue();
        } else {

            // 2.[ Search CAL_TP_CD Table]
            // S21_NA#12819 Mod Start
            calTpCd = calTpCdCache.get(CAL_SUB_TP.COMPANY_CALENDAR);

            if (!ZYPCommonFunc.hasValue(calTpCd)) {
                CAL_RELNTMsg calRelnTKey = new CAL_RELNTMsg();
                calRelnTKey.setSQLID("001");
                calRelnTKey.setConditionValue("glblCmpyCd01", glblCmpyCd);
                calRelnTKey.setConditionValue("calSubTpCd01", CAL_SUB_TP.COMPANY_CALENDAR);
                CAL_RELNTMsgArray calRelnTRcd = (CAL_RELNTMsgArray) EZDTBLAccessor.findByCondition(calRelnTKey);

                if (calRelnTRcd == null || calRelnTRcd.length() == 0) {
                    return null;

                } else if (calRelnTRcd.length() > 1) {
                    // When acquire more than two cases, process is finished as master setting error.
                    return null;
                } else {
                    calTpCd = calRelnTRcd.no(0).calTpCd.getValue();
                    calTpCdCache.put(CAL_SUB_TP.COMPANY_CALENDAR, calTpCd);
                }
            }
            // S21_NA#12819 Mod End
        }

        String startDay = S21CalendarUtil.addDay(slsDt, daysPriAllocNum.intValue());
        String allocStartedDay = getBusinessDay(glblCmpyCd, startDay, calTpCd);
        if (allocStartedDay == null) {
            return null;
        }

        return allocStartedDay;
    }

    /**
     * <pre>
     * Get Business Date
     * </pre>
     * @param Global Company Code String
     * @param Allocation start date String
     * @param Calendar Type Code String
     * @return Business Date
     */
    private static String getBusinessDay(String glblCmpyCd, String startDay, String calTpCd) {

        String businessDay = startDay;
        boolean isBusinessDay = false;

        isBusinessDay = ZYPDateUtil.isBusinessDayEx(glblCmpyCd, calTpCd, startDay);

        if (!isBusinessDay) {
            businessDay = ZYPDateUtil.addBusinessDay(calTpCd, startDay, -1);
        }

        return businessDay;
    }
    // 2016/01/26 S21_NA#3522 Add End

    // 2016/02/10 S21_NA#1684 Add Start
    /**
     * Check Customer PO Fields
     * @param bizMsg NWAL1500CMsg
     * @param isSave true: Save mode false: other
     * @return No Error : true
     */
    public boolean checkCustPoFields(NWAL1500CMsg bizMsg, boolean isSave) { // 2016/08/09 S21_NA#4657 Add param: isSave

        // For Performance QC#8166 Mod Start
        NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(bizMsg);
        // For Performance QC#8166 Mod End

        if (ZYPCommonFunc.hasValue(bizMsg.custIssPoNum)) {
            if (!isSave && !ZYPCommonFunc.hasValue(bizMsg.custIssPoDt)) { // 2016/08/09 S21_NA#4657 Add isSave
                bizMsg.custIssPoDt.setErrorInfo(1, NWAM0210E, new String[] {itemNameList.getItemName(bizMsg.custIssPoDt)});
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.custIssPoDt)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.custIssPoNum)) {
                bizMsg.custIssPoNum.setErrorInfo(1, NWAM0209E, new String[] {itemNameList.getItemName(bizMsg.custIssPoNum)});
                return false;
            }
        }

        return true;
    }
    // 2016/02/10 S21_NA#1684 Add End

    // 2016/02/12 S21_NA#1683 Add Start
    /**
     * Check Customer PO Date
     * @param bizMsg NWAL1500CMsg
     * @return No Error : true
     */
    public boolean checkCustPoDt(NWAL1500CMsg bizMsg) {

        // For Performance QC#8166 Mod Start
        NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(bizMsg);
        // For Performance QC#8166 Mod End

        if (ZYPCommonFunc.hasValue(bizMsg.custIssPoDt)) {
            if (bizMsg.custIssPoDt.getValue().compareTo(bizMsg.ordDt.getValue()) > 0) {
                bizMsg.custIssPoDt.setErrorInfo(1, NWAM0765E, new String[] {itemNameList.getItemName(bizMsg.custIssPoDt), itemNameList.getItemName(bizMsg.ordDt) });
                return false;
            }
        }
        return true;
    }

    /**
     * Check Customer Signed Date
     * @param bizMsg NWAL1500CMsg
     * @return No Error : true
     */
    public boolean checkCustSgnDt(NWAL1500CMsg bizMsg) {

        // For Performance QC#8166 Mod Start
        NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(bizMsg);
        // For Performance QC#8166 Mod End

        if (ZYPCommonFunc.hasValue(bizMsg.ordSgnDt)) {
            if (bizMsg.ordSgnDt.getValue().compareTo(bizMsg.ordDt.getValue()) > 0) {
                bizMsg.ordSgnDt.setErrorInfo(1, NWAM0765E, new String[] {itemNameList.getItemName(bizMsg.ordSgnDt), itemNameList.getItemName(bizMsg.ordDt) });
                return false;
            }
        }
        return true;
    }
    // 2016/02/12 S21_NA#1683 Add End

    // 2016/02/25 S21_NA#1729 Add Start
    /**
     * Check Customer Signed Date
     * @param bizMsg NWAL1500CMsg
     * @return No Error : true
     */
    public boolean checkCustIssPoNum(NWAL1500CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.custIssPoNum)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_01, ZYPConstant.FLG_OFF_N);
            bizMsg.custIssPoNum_BK.clear();
            return true;
        // QC#5127 2016/10/21 Add Start
        } else if (ZYPCommonFunc.hasValue(bizMsg.custIssPoNum_BK) && (bizMsg.custIssPoNum.getValue().equals(bizMsg.custIssPoNum_BK.getValue()))) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_01, ZYPConstant.FLG_OFF_N);
            return true;
        // QC#5127 2016/10/21 Add End
        } else if (ZYPCommonFunc.hasValue(bizMsg.custIssPoNum_BK) && (!bizMsg.custIssPoNum.getValue().equals(bizMsg.custIssPoNum_BK.getValue()))) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_01, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_BK, bizMsg.custIssPoNum);
        }
        if (bizMsg.xxWrnSkipFlg_01.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_BK, bizMsg.custIssPoNum);
            return true;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_BK, bizMsg.custIssPoNum);
        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.cntCustIssPoNum(bizMsg);
        if ((Integer) ssmResult.getResultObject() == 0) {
            return true;
        }

        bizMsg.custIssPoNum.setErrorInfo(2, NWAM0368W);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_01, ZYPConstant.FLG_ON_Y);
        bizMsg.setMessageInfo(NWAM0368W);
        return false;
    }

    /**
     * Check Customer Signed Date
     * @param bizMsg NWAL1500CMsg
     * @return No Error : true
     */
    public boolean checkCntLeaseCmpyPoNum(NWAL1500CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.leaseCmpyPoNum)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_02, ZYPConstant.FLG_OFF_N);
            bizMsg.leaseCmpyPoNum_BK.clear();
            return true;
        // QC#5127 2016/10/21 Add Start
        } else if (ZYPCommonFunc.hasValue(bizMsg.leaseCmpyPoNum_BK) && (bizMsg.leaseCmpyPoNum.getValue().equals(bizMsg.leaseCmpyPoNum_BK.getValue()))) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_02, ZYPConstant.FLG_OFF_N);
            return true;
        // QC#5127 2016/10/21 Add End
        } else if (ZYPCommonFunc.hasValue(bizMsg.leaseCmpyPoNum_BK) && (!bizMsg.leaseCmpyPoNum.getValue().equals(bizMsg.leaseCmpyPoNum_BK.getValue()))) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_02, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bizMsg.leaseCmpyPoNum_BK, bizMsg.leaseCmpyPoNum);
        }
        if (bizMsg.xxWrnSkipFlg_02.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.leaseCmpyPoNum_BK, bizMsg.leaseCmpyPoNum);
            return true;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.leaseCmpyPoNum_BK, bizMsg.leaseCmpyPoNum);
        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.cntLeaseCmpyPoNum(bizMsg);
        if ((Integer) ssmResult.getResultObject() == 0) {
            return true;
        }

        bizMsg.leaseCmpyPoNum.setErrorInfo(2, NWAM0779W);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_02, ZYPConstant.FLG_ON_Y);
        bizMsg.setMessageInfo(NWAM0779W);
        return false;
    }
    // 2016/02/25 S21_NA#1729 Add End

    // S21_NA#1745 Add Start
    /**
     * call sales credit update API
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     * @return true:needs to update
     */
    private boolean needsToUpdateSlsCr(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // Header
        if (bizMsg.F.getValidCount() != glblMsg.F.getValidCount()) {
            return true;
        }
        for (int n = 0; n < bizMsg.F.getValidCount(); n++) {

            NWAL1500_FCMsg fCMsg = bizMsg.F.no(n);
            NWAL1500_FSMsg fSMsg = glblMsg.F.no(n);

            if (!NWAL1500CommonLogic.isEqualsEZDItem(fCMsg.dsCpoSlsCrPk_FS, fSMsg.dsCpoSlsCrPk_FS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(fCMsg.dsOrdPosnNum_FS, fSMsg.dsOrdPosnNum_FS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(fCMsg.dsCpoConfigPk_FS, fSMsg.dsCpoConfigPk_FS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(fCMsg.slsRepTocCd_FS, fSMsg.slsRepTocCd_FS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(fCMsg.slsRepRoleTpCd_FS, fSMsg.slsRepRoleTpCd_FS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(fCMsg.lineBizRoleTpCd_FS, fSMsg.lineBizRoleTpCd_FS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(fCMsg.slsRepCrPct_FS, fSMsg.slsRepCrPct_FS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(fCMsg.slsCrQuotFlg_FS, fSMsg.slsCrQuotFlg_FS)) {
                return true;
            }
        }

        // Config Line
        if (bizMsg.G.getValidCount() != glblMsg.G.getValidCount()) {
            return true;
        }
        for (int n = 0; n < bizMsg.G.getValidCount(); n++) {

            NWAL1500_GCMsg gCMsg = bizMsg.G.no(n);
            NWAL1500_GSMsg gSMsg = glblMsg.G.no(n);

            if (!NWAL1500CommonLogic.isEqualsEZDItem(gCMsg.dsCpoSlsCrPk_GS, gSMsg.dsCpoSlsCrPk_GS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(gCMsg.dsOrdPosnNum_GS, gSMsg.dsOrdPosnNum_GS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(gCMsg.dsCpoConfigPk_GS, gSMsg.dsCpoConfigPk_GS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(gCMsg.slsRepTocCd_GS, gSMsg.slsRepTocCd_GS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(gCMsg.slsRepRoleTpCd_GS, gSMsg.slsRepRoleTpCd_GS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(gCMsg.lineBizRoleTpCd_GS, gSMsg.lineBizRoleTpCd_GS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(gCMsg.slsRepCrPct_GS, gSMsg.slsRepCrPct_GS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(gCMsg.slsCrQuotFlg_GS, gSMsg.slsCrQuotFlg_GS)) {
                return true;
            }
        }

        // RMA
        if (bizMsg.H.getValidCount() != glblMsg.H.getValidCount()) {
            return true;
        }
        for (int n = 0; n < bizMsg.H.getValidCount(); n++) {

            NWAL1500_HCMsg hCMsg = bizMsg.H.no(n);
            NWAL1500_HSMsg hSMsg = glblMsg.H.no(n);

            if (!NWAL1500CommonLogic.isEqualsEZDItem(hCMsg.dsCpoSlsCrPk_HS, hSMsg.dsCpoSlsCrPk_HS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(hCMsg.dsOrdPosnNum_HS, hSMsg.dsOrdPosnNum_HS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(hCMsg.dsCpoConfigPk_HS, hSMsg.dsCpoConfigPk_HS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(hCMsg.slsRepTocCd_HS, hSMsg.slsRepTocCd_HS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(hCMsg.slsRepRoleTpCd_HS, hSMsg.slsRepRoleTpCd_HS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(hCMsg.lineBizRoleTpCd_HS, hSMsg.lineBizRoleTpCd_HS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(hCMsg.slsRepCrPct_HS, hSMsg.slsRepCrPct_HS)) {
                return true;
            }
            if (!NWAL1500CommonLogic.isEqualsEZDItem(hCMsg.slsCrQuotFlg_HS, hSMsg.slsCrQuotFlg_HS)) {
                return true;
            }
        }

        return false;
    }

    /**
     * call sales credit update API
     * @param bizMsg NWAL1500CMsg
     */
    public void callSalesCreditUpdateApi(NWAL1500CMsg bizMsg) {

        NWZC152001PMsg slsCrUpdApiPMsg = new NWZC152001PMsg();
        int slsCrPMsgCount = 0;

        ZYPEZDItemValueSetter.setValue(slsCrUpdApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(slsCrUpdApiPMsg.cpoOrdNum, bizMsg.cpoOrdNum);

        // Header Data
        for (int n = 0; n < bizMsg.F.getValidCount() && slsCrPMsgCount < slsCrUpdApiPMsg.salesRepList.length(); n++) {

            NWAL1500_FCMsg fCMsg = bizMsg.F.no(n);

            String xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_NEW;
            if (!ZYPCommonFunc.hasValue(fCMsg.xxRqstTpCd_FS) && ZYPCommonFunc.hasValue(fCMsg.dsCpoSlsCrPk_FS)) {
                xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;

            } else if (NWZC150001Constant.RQST_TP_SLS_CR_NEW.equals(fCMsg.xxRqstTpCd_FS.getValue()) && ZYPCommonFunc.hasValue(fCMsg.dsCpoSlsCrPk_FS)) {
                xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;

            } else if (ZYPCommonFunc.hasValue(fCMsg.xxRqstTpCd_FS)) {
                xxReqTpCd = fCMsg.xxRqstTpCd_FS.getValue();

            }

            NWZC152001_salesRepListPMsg slsCrPMsg = slsCrUpdApiPMsg.salesRepList.no(slsCrPMsgCount);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.xxRqstTpCd_A, xxReqTpCd);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoSlsCrPk_A, fCMsg.dsCpoSlsCrPk_FS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsOrdPosnNum_A, fCMsg.dsOrdPosnNum_FS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoConfigPk_A, fCMsg.dsCpoConfigPk_FS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd_A, fCMsg.slsRepTocCd_FS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepRoleTpCd_A, fCMsg.slsRepRoleTpCd_FS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCrPct_A, fCMsg.slsRepCrPct_FS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsCrQuotFlg_A, fCMsg.slsCrQuotFlg_FS);

            slsCrPMsgCount++;
        }

        // Config Line
        for (int n = 0; n < bizMsg.G.getValidCount() && slsCrPMsgCount < slsCrUpdApiPMsg.salesRepList.length(); n++) {

            NWAL1500_GCMsg gCMsg = bizMsg.G.no(n);

            String xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_NEW;
            if (!ZYPCommonFunc.hasValue(gCMsg.xxRqstTpCd_GS) && ZYPCommonFunc.hasValue(gCMsg.dsCpoSlsCrPk_GS)) {
                xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;

            } else if (NWZC150001Constant.RQST_TP_SLS_CR_NEW.equals(gCMsg.xxRqstTpCd_GS.getValue()) && ZYPCommonFunc.hasValue(gCMsg.dsCpoSlsCrPk_GS)) {
                xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;

            } else if (ZYPCommonFunc.hasValue(gCMsg.xxRqstTpCd_GS)) {
                xxReqTpCd = gCMsg.xxRqstTpCd_GS.getValue();

            }

            NWZC152001_salesRepListPMsg slsCrPMsg = slsCrUpdApiPMsg.salesRepList.no(slsCrPMsgCount);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.xxRqstTpCd_A, xxReqTpCd);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoSlsCrPk_A, gCMsg.dsCpoSlsCrPk_GS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsOrdPosnNum_A, gCMsg.dsOrdPosnNum_GS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoConfigPk_A, gCMsg.dsCpoConfigPk_GS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd_A, gCMsg.slsRepTocCd_GS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepRoleTpCd_A, gCMsg.slsRepRoleTpCd_GS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCrPct_A, gCMsg.slsRepCrPct_GS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsCrQuotFlg_A, gCMsg.slsCrQuotFlg_GS);

            slsCrPMsgCount++;
        }

        // RMA
        for (int n = 0; n < bizMsg.H.getValidCount() && slsCrPMsgCount < slsCrUpdApiPMsg.salesRepList.length(); n++) {

            NWAL1500_HCMsg hCMsg = bizMsg.H.no(n);

            String xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_NEW;
            if (!ZYPCommonFunc.hasValue(hCMsg.xxRqstTpCd_HS) && ZYPCommonFunc.hasValue(hCMsg.dsCpoSlsCrPk_HS)) {
                xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;

            } else if (NWZC150001Constant.RQST_TP_SLS_CR_NEW.equals(hCMsg.xxRqstTpCd_HS.getValue()) && ZYPCommonFunc.hasValue(hCMsg.dsCpoSlsCrPk_HS)) {
                xxReqTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;

            } else if (ZYPCommonFunc.hasValue(hCMsg.xxRqstTpCd_HS)) {
                xxReqTpCd = hCMsg.xxRqstTpCd_HS.getValue();

            }

            NWZC152001_salesRepListPMsg slsCrPMsg = slsCrUpdApiPMsg.salesRepList.no(slsCrPMsgCount);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.xxRqstTpCd_A, xxReqTpCd);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoSlsCrPk_A, hCMsg.dsCpoSlsCrPk_HS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsOrdPosnNum_A, hCMsg.dsOrdPosnNum_HS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsCpoConfigPk_A, hCMsg.dsCpoConfigPk_HS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd_A, hCMsg.slsRepTocCd_HS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepRoleTpCd_A, hCMsg.slsRepRoleTpCd_HS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCrPct_A, hCMsg.slsRepCrPct_HS);
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsCrQuotFlg_A, hCMsg.slsCrQuotFlg_HS);

            slsCrPMsgCount++;
        }

        if (slsCrPMsgCount == 0) {
            return;
        }

        slsCrUpdApiPMsg.salesRepList.setValidCount(slsCrPMsgCount);

        new NWZC152001().execute(slsCrUpdApiPMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.isXxMsgId(slsCrUpdApiPMsg)) {
            return;
        }

        List<String> ml = S21ApiUtil.getXxMsgIdList(slsCrUpdApiPMsg);
        for (String msgId : ml) {
            bizMsg.setMessageInfo(msgId);
            if (msgId.endsWith("E")) {
                return;
            }
        }
        return;
    }
    // S21_NA#1745 add end

    // 2016/04/08 S21_NA#5356 Add Start
    /**
     * <pre>
     * check serial number mandatory or not
     * @param bizMsg Business Message
     * @param mdseCd Merchandise Code (Item COde)
     * @return true: Serial Take, false: no need serial number
     * </pre>
     */
    public static boolean isNeedSerialNum(NWAL1500CMsg bizMsg, String mdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);

        mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);

        if (null != mdseTMsg && ZYPConstant.FLG_ON_Y.equals(mdseTMsg.shpgSerTakeFlg.getValue())) {
            return true;
        } else {
            return false;
        }
    }
    // 2016/04/08 S21_NA#5356 Add End

    // 2016/04/11 S21_NA#3236-3 Add Start
    private boolean callDsCpoUpdateApiForEditedLines(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<NWAL1500_ASMsg> editConfigList, List<NWAL1500_BSMsg> editLineList, List<NWAL1500_CSMsg> editRmaConfigList,  List<NWAL1500_DSMsg> editRmaLineList) { // 2018/01/31 S21_NA#19808 Mod

        // 2019/07/30 QC#52207 Add Start
        Boolean isWfRect = false;
        // 2019/07/30 QC#52207 Add End

        // 2018/01/31 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        // 2016/08/09 S21_NA#13175 Add Start
        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            CPOTMsg cpoMsg = new CPOTMsg();
            ZYPEZDItemValueSetter.setValue(cpoMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoMsg.cpoOrdNum, bizMsg.cpoOrdNum);
            cpoMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(cpoMsg);
            // if (cpoMsg != null && S21StringUtil.isEquals(ORD_HDR_STS.CANCELLED, cpoMsg.ordHdrStsCd.getValue())) {
            if (cpoMsg != null && (S21StringUtil.isEquals(ORD_HDR_STS.CANCELLED, cpoMsg.ordHdrStsCd.getValue()) ||
                    S21StringUtil.isEquals(ORD_HDR_STS.CLOSED, cpoMsg.ordHdrStsCd.getValue()))) {
                return true;
            }

            // 2019/07/30 QC#52207 Add Start
            if ((cpoMsg != null) && (ZYPConstant.FLG_ON_Y.equals(cpoMsg.wfRejFlg.getValue()))){
                isWfRect = true;
            }
            // 2019/07/30 QC#52207 Add End
        }
        // 2016/08/09 S21_NA#13175 Add End

        NWZC150001PMsg cpoUpdApiReqPMsg = new NWZC150001PMsg();

        // 2019/07/30 QC#52207 Mod Start
        if (isWfRect) {
            setCpoUpdApiReqPMsg(NWZC150001Constant.MODE_SAVE, cpoUpdApiReqPMsg, bizMsg, glblMsg, ZYPConstant.FLG_ON_Y, ZYPConstant.FLG_OFF_N); // 2018/01/31 S21_NA#19808
        } else {
            setCpoUpdApiReqPMsg(NWZC150001Constant.MODE_SUBMIT, cpoUpdApiReqPMsg, bizMsg, glblMsg, ZYPConstant.FLG_ON_Y, ZYPConstant.FLG_OFF_N); // 2018/01/31 S21_NA#19808
        }
        //
        // 2019/07/30 QC#52207 Mod End
        

        // For Performance QC#8166 Add Start
        Map<String, String> ccyCdMap = new HashMap<String, String>();
        Map<String, BigDecimal> inPoundWtMap = new HashMap<String, BigDecimal>();
        // For Performance QC#8166 Add End

        for (NWAL1500_BSMsg lineMsg : editLineList) {
            String rqstMode = NWZC150001Constant.RQST_TP_DTL_NEW;
            if (ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL)) {
                rqstMode = NWZC150001Constant.RQST_TP_DTL_MODIFY;
            }
            setCpoUpdApiReqDtlPMsg(rqstMode, cpoUpdApiReqPMsg, bizMsg, glblMsg, NWAL1500CommonLogic.getParentConfig(glblMsg, lineMsg), lineMsg, ccyCdMap, inPoundWtMap);
        }

        for (NWAL1500_ASMsg configMsg : editConfigList) {
            setConfigUpdApiReqDtlPMsg(NWZC150001Constant.RQST_TP_CONFIG_MODIFY, cpoUpdApiReqPMsg, bizMsg, configMsg);
        }

        for (NWAL1500_DSMsg rmaLineMsg : editRmaLineList) {
            String rqstMode = NWZC150001Constant.RQST_TP_DTL_NEW;
            if (ZYPCommonFunc.hasValue(rmaLineMsg.rtrnLineStsDescTxt_RL)) {
                rqstMode = NWZC150001Constant.RQST_TP_DTL_MODIFY;
            }
            setCpoUpdApiReqDtlPMsg(rqstMode, cpoUpdApiReqPMsg, bizMsg, glblMsg, NWAL1500CommonLogic.getParentConfig(glblMsg, rmaLineMsg), rmaLineMsg, ccyCdMap, inPoundWtMap);
        }

        for (NWAL1500_CSMsg rmaConfigMsg : editRmaConfigList) {
            setConfigUpdApiReqDtlPMsg(NWZC150001Constant.RQST_TP_CONFIG_MODIFY, cpoUpdApiReqPMsg, bizMsg, rmaConfigMsg);
        }

        setCpoUpdApiReqSalesCredit(cpoUpdApiReqPMsg, bizMsg, false, null, null);

        setCpoUpdApiReqPriceCalcBase(cpoUpdApiReqPMsg, bizMsg, glblMsg, false, null, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            return false;
        }

        // Call DS CPO Update API
        return callApi(bizMsg, glblMsg, cpoUpdApiReqPMsg, true); // 2018/01/31 S21_NA#19808 Mod
    }
    // 2016/04/11 S21_NA#3236-3 Add End
    // 2016/04/19 S21_NA#5394 Add Start
    // START 2023/05/11 R.Azucena [QC#61514 MOD]
    /**
     * resetStatusBeforCancelProc
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     */
    // private void resetStatusBeforCancelProc(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
    public static void resetStatusBeforCancelProc(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
    // END 2023/05/11 R.Azucena [QC#61514 MOD]
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            // START 2023/05/11 R.Azucena [QC#61514 MOD]
            // if (isTobeCancelProc(bizMsg, glblMsg.B.no(i).ordLineStsDescTxt_LB.getValue())) {
            if (isTobeCancelProc(bizMsg, glblMsg.B.no(i).ordLineStsDescTxt_LL.getValue())) {
            // END 2023/05/11 R.Azucena [QC#61514 MOD]
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).ordLineStsDescTxt_LL, glblMsg.B.no(i).ordLineStsDescTxt_LB);
                // START 2023/05/11 R.Azucena [QC#61514 ADD]
                for (int bizIdx = 0; bizIdx < bizMsg.B.getValidCount(); bizIdx++) {
                    if (S21StringUtil.isEquals(bizMsg.B.no(bizIdx).xxLineNum_LL.getValue(), glblMsg.B.no(i).xxLineNum_LL.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bizIdx).ordLineStsDescTxt_LL, glblMsg.B.no(i).ordLineStsDescTxt_LB);
                    }
                }
                // END 2023/05/11 R.Azucena [QC#61514 ADD]
                glblMsg.B.no(i).ordLineStsDescTxt_LB.clear();
            }
        }

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            // START 2023/05/11 R.Azucena [QC#61514 MOD]
            // if (isTobeCancelProc(bizMsg, glblMsg.D.no(i).rtrnLineStsDescTxt_RB.getValue())) {
            if (isTobeCancelProc(bizMsg, glblMsg.D.no(i).rtrnLineStsDescTxt_RL.getValue())) {
            // END 2023/05/11 R.Azucena [QC#61514 MOD]
                ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).rtrnLineStsDescTxt_RL, glblMsg.D.no(i).rtrnLineStsDescTxt_RB);
                // START 2023/05/11 R.Azucena [QC#61514 ADD]
                for (int bizIdx = 0; bizIdx < bizMsg.D.getValidCount(); bizIdx++) {
                    if (S21StringUtil.isEquals(bizMsg.D.no(bizIdx).xxLineNum_RL.getValue(), glblMsg.D.no(i).xxLineNum_RL.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(bizIdx).rtrnLineStsDescTxt_RL, glblMsg.D.no(i).rtrnLineStsDescTxt_RB);
                    }
                }
                // END 2023/05/11 R.Azucena [QC#61514 ADD]
                glblMsg.D.no(i).rtrnLineStsDescTxt_RB.clear();
            }
        }
    }

    // START 2023/05/11 R.Azucena [QC#61514 MOD]
    /**
     * isTobeCancelProc
     * @param bizMsg NWAL1500CMsg
     * @param lineStsDescTxt String
     * @return boolean
     */
    // private boolean isTobeCancelProc(NWAL1500CMsg bizMsg, String lineStsDescTxt) {
    private static boolean isTobeCancelProc(NWAL1500CMsg bizMsg, String lineStsDescTxt) {
    // END 2023/05/11 R.Azucena [QC#61514 MOD]
        if (!ZYPCommonFunc.hasValue(lineStsDescTxt)) {
            return false;
        }
        String stsToBeCancel = bizMsg.varCharConstVal_TB.getValue();

        return stsToBeCancel.equals(lineStsDescTxt) | NWAL1500_CANC_PRTL_CANC.equals(lineStsDescTxt);
    }

    // 2016/04/19 S21_NA#5394 Add End
    private static void doNothing() {
        return;
    }
    // 2017/02/24 S21_NA#17714 Del Start. This Method was moved to NWAL1500CommonLogic.java
    // 2016/04/28 S21_NA#7516 Add Start

//    private static boolean isOrderRetailEquipment(NWAL1500CMsg bizMsg) {
//
//        return NWAL1500QueryForLineConfig.getInstance().isExistOrdCatg(bizMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS, false);
//    }
    // 2016/04/28 S21_NA#7516 Add End
    // 2017/02/24 S21_NA#17714 Del End. This Method was moved to NWAL1500CommonLogic.java

    // 20160509 S21_NA#5764
    // Del Start 2018/02/26 QC#22967
//    /**
//     * checkBillShipSoldRelation
//     * @param bizMsg NWAL1500CMsg
//     * @return true: error, false: normal end
//     */
//    public boolean checkBillShipSoldRelation(NWAL1500CMsg bizMsg) {
//        if (NWXC150001DsCheck.checkBillToRalation(//
//                bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustCd.getValue(), bizMsg.billToCustAcctCd.getValue())) {
//            bizMsg.billToCustCd.setErrorInfo(1, NWZC150001Constant.NWZM1452E);
//            bizMsg.billToCustAcctCd.setErrorInfo(1, NWZC150001Constant.NWZM1452E);
//            return true;
//        }
//        if (NWXC150001DsCheck.checkShipToRalation(//
//                bizMsg.glblCmpyCd.getValue(), bizMsg.shipToCustCd.getValue(), bizMsg.shipToCustAcctCd.getValue())) {
//            bizMsg.shipToCustCd.setErrorInfo(1, NWZC150001Constant.NWZM1453E);
//            bizMsg.shipToCustAcctCd.setErrorInfo(1, NWZC150001Constant.NWZM1453E);
//            return true;
//        }
//        if (NWXC150001DsCheck.checkSoldToRalation(//
//                bizMsg.glblCmpyCd.getValue(), bizMsg.soldToCustLocCd.getValue(), bizMsg.sellToCustCd.getValue())) {
//            bizMsg.soldToCustLocCd.setErrorInfo(1, NWZC150001Constant.NWZM1454E);
//            bizMsg.sellToCustCd.setErrorInfo(1, NWZC150001Constant.NWZM1454E);
//            return true;
//        }
//
//        NMZC610001PMsg custInfoGetApiMsg //
//        = NWXC150001DsCheck.callCustInfoGetApi(//
//                bizMsg.glblCmpyCd.getValue() //
//                , bizMsg.billToCustCd.getValue() //
//                // 2017/06/09 S21_NA#18296 Mod Start
//                //, bizMsg.shipToCustCd.getValue() //
//                //, bizMsg.sellToCustCd.getValue() //
//                , bizMsg.sellToCustCd.getValue() //
//                , bizMsg.shipToCustAcctCd.getValue() //
//                // 2017/06/09 S21_NA#18296 Mod End
//                , ONBATCH_TYPE.ONLINE);
//        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
//            List<String> msglst = S21ApiUtil.getXxMsgIdList(custInfoGetApiMsg);
//            for (String msgId : msglst) {
//                bizMsg.setMessageInfo(msgId);
//            }
//            return true;
//        }
//        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
//            NMZC610001_EligibleCheckListPMsg eligiblePMsg //
//            = custInfoGetApiMsg.EligibleCheckList.no(i);
//            // 2016/10/04 S21_NA#13170 Mod Start
//            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
//                    // 2017/06/09 S21_NA#18296 Mod Start
//                    // || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
//                    || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
//                // 2017/06/09 S21_NA#18296 Mod End
//
//                if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue())) {
//                    bizMsg.billToCustCd.setErrorInfo(1, NWZC150001Constant.NWZM1455E);
//                }
//                // 2017/06/09 S21_NA#18296 Mod Start
//                // if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
//                if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
//                    // 2017/06/09 S21_NA#18296 Mod End
//                    bizMsg.shipToCustCd.setErrorInfo(1, NWZC150001Constant.NWZM1455E);
//                }
//
//                return true;
//            }
//            // 2016/10/04 S21_NA#13170 Mod End
//        }
//        return false;
//    }
    // Del End 2018/02/26 QC#22967

    // For Performance QC#8166 Add Start
    /**
     * Get Model ID From Cache
     * @param bizMsg NWAL1500CMsg
     * @param mdlNm Model Name
     * @param mdlIdMap Cache Map
     * @return Model ID From Cache
     */
    @SuppressWarnings("unchecked")
    private BigDecimal getMdlIdFromCache(NWAL1500CMsg bizMsg, String mdlNm, Map<String, BigDecimal> mdlIdMap) {

        if (mdlIdMap.containsKey(mdlNm)) {
            return mdlIdMap.get(mdlNm);
        }

        S21SsmEZDResult ssmRslt = this.queryForSaveSubmit.checkModelNameForConfigLine(bizMsg, mdlNm);
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, Object>> rsltMapList = (List<Map<String, Object>>) ssmRslt.getResultObject();
            BigDecimal mdlId = (BigDecimal) rsltMapList.get(0).get("T_MDL_ID");
            mdlIdMap.put(mdlNm, mdlId);
            return mdlId;
        }

        return null;
    }

    /**
     * Get Ship To Cust Info From Cache
     * @param bizMsg NWAL1500CMsg
     * @param shipToCustCd Ship To Cutomer Code
     * @param shipToCustCdMap Cache Map
     * @return Ship To Cust Info From Cache
     */
    private Boolean getShipToCustInfoFromCache(NWAL1500CMsg bizMsg, String shipToCustCd, Map<String, Boolean> shipToCustCdMap) {

        if (shipToCustCdMap.containsKey(shipToCustCd)) {
            return shipToCustCdMap.get(shipToCustCd);
        }

        S21SsmEZDResult ssmRslt = this.queryForSaveSubmit.checkShipToCustCd(bizMsg, shipToCustCd);
        if (ssmRslt.isCodeNormal()) {
            shipToCustCdMap.put(shipToCustCd, true);
            return true;
        }

        return false;
    }

    /**
     * Get Bill To Cust Info From Cache
     * @param bizMsg NWAL1500CMsg
     * @param billToCustCd Bill To Cutomer Code
     * @param billToCustCdMap Cache Map
     * @return Bill To Cust Info From Cache
     */
    private Boolean getBillToCustInfoFromCache(NWAL1500CMsg bizMsg, String billToCustCd, Map<String, Boolean> billToCustCdMap) {

        if (billToCustCdMap.containsKey(billToCustCd)) {
            return billToCustCdMap.get(billToCustCd);
        }

        S21SsmEZDResult ssmRslt = this.queryForSaveSubmit.checkBillToCustCd(bizMsg, billToCustCd);
        if (ssmRslt.isCodeNormal()) {
            billToCustCdMap.put(billToCustCd, true);
            return true;
        }

        return false;
    }

    /**
     * Get Price Category Code From Cache
     * @param bizMsg NWAL1500CMsg
     * @param prcCatgNm Price Category Name
     * @param prcCatgCdMap Cache Map
     * @return Price Category Code From Cache
     */
    @SuppressWarnings("unchecked")
    private String getPrcCatgCdFromCache(NWAL1500CMsg bizMsg, String prcCatgNm, Map<String, String> prcCatgCdMap) {

        if (prcCatgCdMap.containsKey(prcCatgNm)) {
            return prcCatgCdMap.get(prcCatgNm);
        }

        S21SsmEZDResult ssmRslt = this.queryForSaveSubmit.checkPriceCatgNm(bizMsg, prcCatgNm);
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
            String prcCatgCd = rsltMapList.get(0).get("PRC_CATG_CD");
            prcCatgCdMap.put(prcCatgNm, prcCatgCd);
            return prcCatgCd;
        }

        return null;
    }

    /**
     * Get Retail WH Code From Cache
     * @param bizMsg NWAL1500CMsg
     * @param rtlWhNm Retail WH Name
     * @param rtlWhCdMap Cache Map
     * @return Retail WH Code From Cache
     */
    @SuppressWarnings("unchecked")
    private String getRtlWhCdFromCache(NWAL1500CMsg bizMsg, String rtlWhNm, Map<String, String> rtlWhCdMap) {

        if (rtlWhCdMap.containsKey(rtlWhNm)) {
            return rtlWhCdMap.get(rtlWhNm);
        }

        S21SsmEZDResult ssmRslt = NWAL1500QueryForLineConfig.getInstance().getWhInfoWithRsnOfPerfectMatching(bizMsg, rtlWhNm);
        if (ssmRslt.isCodeNormal()) {
            List<String> rtlWhCdList = (List<String>) ssmRslt.getResultObject();
            String rtlWhCd = rtlWhCdList.get(0);
            rtlWhCdMap.put(rtlWhNm, rtlWhCd);
            return rtlWhCd;
        }

        return null;
    }

    // 2020/03/16 QC#56132 Add Start
    /**
     * Get Retail WH Code From Cache For RMA
     * @param bizMsg NWAL1500CMsg
     * @param rtlWhNm Retail WH Name
     * @param rtlWhCdMap Cache Map
     * @return Retail WH Code From Cache For RMA
     */
    @SuppressWarnings("unchecked")
    private String getRtlWhCdFromCacheForRMA(NWAL1500CMsg bizMsg, EZDSStringItem rtlWhNm, Map<String, String> rtlWhCdMap) {

        if (rtlWhCdMap.containsKey(rtlWhNm.getValue())) {
            return rtlWhCdMap.get(rtlWhNm.getValue());
        }

        S21SsmEZDResult ssmRslt = NWAL1500QueryForLineConfig.getInstance().getWhInfoWithRsnOfPerfectMatchingForRMA(bizMsg, rtlWhNm.getValue());
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> rtlWhCdMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
            String rtlWhCd = rtlWhCdMapList.get(0).get("RTL_WH_CD");
            String rtlWhCatgCd = rtlWhCdMapList.get(0).get("RTL_WH_CATG_CD");
            // 2020/03/16 QC#56132 Add Start
            if (RTL_WH_CATG_VIRTUAL_CD.equals(rtlWhCatgCd)) {
                rtlWhNm.setErrorInfo(1, NWAM0626E, new String[] {});
                return null;
            }
            // 2020/03/16 QC#56132 Add End
            rtlWhCdMap.put(rtlWhNm.getValue(), rtlWhCd);
            return rtlWhCd;
        }

        return null;
    }
    // 2020/03/16 QC#56132 Add End

    /**
     * Get Retail Sub WH Code From Cache
     * @param bizMsg NWAL1500CMsg
     * @param rtlWhCd Retail WH Code
     * @param rtlSwhNm Retail Sub WH Name
     * @param rtlSwhCdMap Cache Map
     * @return Retail Sub WH Code From Cache
     */
    @SuppressWarnings("unchecked")
    private String getRtlSwhCdFromCache(NWAL1500CMsg bizMsg, String rtlWhCd, String rtlSwhNm, Map<Map<String, String>, String> rtlSwhCdMap) {

        Map<String, String> mapKey = new HashMap<String, String>();
        mapKey.put("rtlWhCd", rtlWhCd);
        mapKey.put("rtlSwhNm", rtlSwhNm);

        if (rtlSwhCdMap.containsKey(mapKey)) {
            return rtlSwhCdMap.get(mapKey);
        }

        S21SsmEZDResult ssmRslt = this.queryForSaveSubmit.getSubWhWithNameOfPerfectMatching(bizMsg, rtlWhCd, rtlSwhNm);
        if (ssmRslt.isCodeNormal()) {
            List<String> rtlSwhCdList = (List<String>) ssmRslt.getResultObject();
            String rtlSwhCd = rtlSwhCdList.get(0);
            rtlSwhCdMap.put(mapKey, rtlSwhCd);
            return rtlSwhCd;
        }

        return null;
    }

    /**
     * Get CCY Code From Cache
     * @param bizMsg NWAL1500CMsg
     * @param prcCatgCd Price Category Code
     * @param ccyCdMap Cache Map
     * @return CCY Code From Cache
     */
    private String getCcyCdFromCache(NWAL1500CMsg bizMsg, String prcCatgCd, Map<String, String> ccyCdMap) {

        if (ccyCdMap.containsKey(prcCatgCd)) {
            return ccyCdMap.get(prcCatgCd);
        }

        PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, prcCatgCd);
        prcCatgTMsg = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatgTMsg);

        if (null != prcCatgTMsg && ZYPCommonFunc.hasValue(prcCatgTMsg.ccyCd)) {
            String ccyCd = prcCatgTMsg.ccyCd.getValue();
            ccyCdMap.put(prcCatgCd, ccyCd);
            return ccyCd;
        }

        return "";
    }

    /**
     * Get InPound Weight From Cache
     * @param bizMsg NWAL1500CMsg
     * @param mdseCdStringItem EZDCStringItem
     * @param inPoundWtMap Cache Map
     * @return inPoundWt
     */
    private BigDecimal getInPoundWtFromCache(NWAL1500CMsg bizMsg, EZDSStringItem mdseCdStringItem, Map<String, BigDecimal> inPoundWtMap) {

        String mdseCd = mdseCdStringItem.getValue();
        String localMdseCd = mdseCd;

        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), localMdseCd);
        if (null != mdseTMsg) {
            localMdseCd = mdseTMsg.mdseCd.getValue();
        }

        if (inPoundWtMap.containsKey(localMdseCd)) {
            return inPoundWtMap.get(localMdseCd);
        }

        MDSE_STORE_PKGTMsg strePkgTMsg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(strePkgTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(strePkgTMsg.mdseCd, localMdseCd);
        ZYPEZDItemValueSetter.setValue(strePkgTMsg.pkgUomCd, bizMsg.varCharConstVal_PU);
        strePkgTMsg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKey(strePkgTMsg);

        if (null == strePkgTMsg) {
            mdseCdStringItem.setErrorInfo(1, NWAM0311E, new String[] {"MDSE_STORE_PKG", mdseCd});
            return null;
        }

        BigDecimal inPoundWt = strePkgTMsg.inPoundWt.getValue();
        inPoundWtMap.put(localMdseCd, inPoundWt);
        return inPoundWt;
    }

    /**
     * Get MDSE Code From Cache
     * @param bizMsg NWAL1500CMsg
     * @param mdseCdForKey MDSE Code For Key
     * @param mdseCdMap Cache Map
     * @return MDSE Code From Cache
     */
    @SuppressWarnings("unchecked")
    private String getMdseCdFromCache(NWAL1500CMsg bizMsg, String mdseCdForKey, Map<String, String> mdseCdMap) {

        if (mdseCdMap.containsKey(mdseCdForKey)) {
            return mdseCdMap.get(mdseCdForKey);
        }

        S21SsmEZDResult ssmRslt = this.queryForSaveSubmit.checkMdseCdFromAllMdseV(bizMsg, mdseCdForKey);
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
            String mdseCd = rsltMapList.get(0).get("MDSE_CD");
            mdseCdMap.put(mdseCdForKey, mdseCd);
            return mdseCd;
        }

        return null;
    }
    // For Performance QC#8166 Add End

    // 2016/07/04 S21_NA#7821 Add Start
    /**
     * Call DS CPO Update API for deleting process
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param cntArray For counting array (display processed lines)
     * @return true: normal end false: abnornal end
     */
    public boolean callDsCpoUpdateApiForDelete(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int[] cntArray) {

        // 2018/01/31 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        NWZC150001PMsg cpoUpdApiReqPMsg = new NWZC150001PMsg();
        setCpoUpdApiReqPMsg(NWZC150001Constant.MODE_DELETE, cpoUpdApiReqPMsg, bizMsg, glblMsg, ZYPConstant.FLG_ON_Y, ZYPConstant.FLG_OFF_N); // 2018/01/31 S21_NA#19808

        cntArray[CNT_HEADER] = 1;

        // For Performance QC#8166 Add Start
        Map<String, String> ccyCdMap = new HashMap<String, String>();
        Map<String, BigDecimal> inPoundWtMap = new HashMap<String, BigDecimal>();
        // For Performance QC#8166 Add End

        ZYPEZDItemValueSetter.setValue(cpoUpdApiReqPMsg.xxWrnSkipFlg_00, ZYPConstant.FLG_ON_Y);

        boolean isCallApi = false;
        // Line Config: Config
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            final NWAL1500_ASMsg configMsg = glblMsg.A.no(i);

            String configRqstMode = "";
            if (!ZYPCommonFunc.hasValue(configMsg.dsCpoConfigPk_LC)) {
                continue;
            }
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxChkBox_LC.getValue())) {
                configRqstMode = NWZC150001Constant.RQST_TP_CONFIG_DELETE;
                cntArray[CNT_CONFIG] = cntArray[CNT_CONFIG] + 1;
                isCallApi = true;
            } else {
                configRqstMode = NWZC150001Constant.RQST_TP_CONFIG_MODIFY;
            }
            String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
            // Line Config: Line
            int delLineCnt = 0;
            int lineCnt = 0;
            for (; lineCnt < glblMsg.B.getValidCount(); lineCnt++) {

                final NWAL1500_BSMsg lineMsg = glblMsg.B.no(lineCnt);
                // QC#24245 2018/06/13 Del Start
                //  if (!ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL)) {
                //      continue;
                //  }
                // QC#24245 2018/06/13 Del End
                if (!S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                    continue;
                }
                String rqstMode = "";
                if (S21StringUtil.isEquals(NWZC150001Constant.RQST_TP_CONFIG_DELETE, configRqstMode)) {
                    rqstMode = NWZC150001Constant.RQST_TP_DTL_DELETE;
                    cntArray[CNT_LINE] = cntArray[CNT_LINE] + 1;
                    isCallApi = true;
                } else if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.xxChkBox_LL.getValue())) {
                    rqstMode = NWZC150001Constant.RQST_TP_DTL_DELETE;
                    cntArray[CNT_LINE] = cntArray[CNT_LINE] + 1;
                    isCallApi = true;
                    delLineCnt++;
                } else {
                    rqstMode = NWZC150001Constant.RQST_TP_DTL_SAVE;
                }
                setCpoUpdApiReqDtlPMsg(rqstMode, cpoUpdApiReqPMsg, bizMsg, glblMsg, configMsg, lineMsg, ccyCdMap, inPoundWtMap);
//                if (!NWZC150001Constant.RQST_TP_DTL_CANCEL.equals(rqstMode)) {
//                }
            }
            if (!S21StringUtil.isEquals(NWZC150001Constant.RQST_TP_CONFIG_MODIFY, configRqstMode) //
                    && lineCnt == delLineCnt) {
                configRqstMode = NWZC150001Constant.RQST_TP_CONFIG_DELETE;
                cntArray[CNT_CONFIG] = cntArray[CNT_CONFIG] + 1;
            }
            setConfigUpdApiReqDtlPMsg(configRqstMode, cpoUpdApiReqPMsg, bizMsg, configMsg);
        }

        // RMA: Config
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {

            final NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(i);

            if (!ZYPCommonFunc.hasValue(rmaConfigMsg.dsCpoConfigPk_RC)) {
                continue;
            }
            String rmaConfigRqstMode = "";
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rmaConfigMsg.xxChkBox_RC.getValue())) {
                rmaConfigRqstMode = NWZC150001Constant.RQST_TP_CONFIG_DELETE;
                cntArray[CNT_RMA_CONFIG] = cntArray[CNT_RMA_CONFIG] + 1;
                isCallApi = true;
            } else {
                rmaConfigRqstMode = NWZC150001Constant.RQST_TP_CONFIG_MODIFY;
            }
            String dsOrdPosnNum = rmaConfigMsg.dsOrdPosnNum_RC.getValue();
            // RMA: Line
            int delLineCnt = 0;
            int lineCnt = 0;
            for (; lineCnt < glblMsg.D.getValidCount(); lineCnt++) {

                final NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(lineCnt);
                // QC#24245 2018/06/13 Del Start
                // if (!ZYPCommonFunc.hasValue(rmaLineMsg.rtrnLineStsDescTxt_RL)) {
                //     continue;
                // }
                // QC#24245 2018/06/13 Del End
                if (!S21StringUtil.isEquals(dsOrdPosnNum, rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                    continue;
                }

                String rqstMode = "";
                if (S21StringUtil.isEquals(NWZC150001Constant.RQST_TP_CONFIG_DELETE, rmaConfigRqstMode)) {
                    rqstMode = NWZC150001Constant.RQST_TP_RTRN_DTL_DELETE;
                    cntArray[CNT_RMA_LINE] = cntArray[CNT_RMA_LINE] + 1;
                    isCallApi = true;
                } else if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rmaLineMsg.xxChkBox_RL.getValue())) {
                    rqstMode = NWZC150001Constant.RQST_TP_RTRN_DTL_DELETE;
                    cntArray[CNT_RMA_LINE] = cntArray[CNT_RMA_LINE] + 1;
                    isCallApi = true;
                    delLineCnt++;
                } else {
                    rqstMode = NWZC150001Constant.RQST_TP_DTL_SAVE;
                }
                setCpoUpdApiReqDtlPMsg(rqstMode, cpoUpdApiReqPMsg, bizMsg, glblMsg, rmaConfigMsg, rmaLineMsg, ccyCdMap, inPoundWtMap);
            }
            if (!S21StringUtil.isEquals(NWZC150001Constant.RQST_TP_CONFIG_MODIFY, rmaConfigRqstMode) //
                    && delLineCnt == lineCnt) {
                rmaConfigRqstMode = NWZC150001Constant.RQST_TP_CONFIG_DELETE;
                cntArray[CNT_RMA_CONFIG] = cntArray[CNT_RMA_CONFIG] + 1;
            }
            setConfigUpdApiReqDtlPMsg(rmaConfigRqstMode, cpoUpdApiReqPMsg, bizMsg, rmaConfigMsg);
        }

        if (isCallApi) {
            boolean isNormal = callApi(bizMsg, glblMsg, cpoUpdApiReqPMsg, true); // 2018/01/31 S21_NA#19808 Mod
            if (isNormal) {
                outputDeleteMsg(bizMsg, cntArray);
            } else {
                if ("E".equals(bizMsg.getMessageKind()) || !isNormal) {
                    return false;
                }
            }
        }
        return true;
    }

    // 2016/08/23 S21_NA#13504 Add Start
    /**
     * <pre>
     * Renumber CPO_DTL_LINE_NUM for unsaved line.
     * this method renumber detail line number for line config and RMA.
     * @param bizMsg Business Message
     * </pre>
     */
    public void renumberForSave(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/31 S21_NA#19808 Mod

        // 2018/01/31 S21_NA#19808 Mod bizMsg => glblMsg
        // For Line Config
        String maxCpoDtlLineNum = "000";
        Map<String, String> modifiedDtlLineNumMap = new HashMap<String, String>();
        List<String> savedLineList = new ArrayList<String>();
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            boolean isAlreadySavedLine = isAlreadySavedLine(bizMsg, glblMsg.B.no(i));
            if (isAlreadySavedLine) {
                String lineKey = glblMsg.B.no(i).cpoDtlLineNum_LL.getValue() + glblMsg.B.no(i).cpoDtlLineSubNum_LL.getValue();
                savedLineList.add(lineKey);
            }
            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).dsCpoLineSubNum_LL) && isAlreadySavedLine) {
                String cpoDtlLineNum = glblMsg.B.no(i).cpoDtlLineNum_LL.getValue();
                if (cpoDtlLineNum.compareTo(maxCpoDtlLineNum) > 0) {
                    maxCpoDtlLineNum = cpoDtlLineNum;
                }
            }
        }
        boolean isModifyNum = false;
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            String lineKey = glblMsg.B.no(i).cpoDtlLineNum_LL.getValue() + glblMsg.B.no(i).cpoDtlLineSubNum_LL.getValue();
            boolean isAlreadySavedLine = savedLineList.contains(lineKey);
            if (!isAlreadySavedLine) {
                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).dsCpoLineSubNum_LL)) {
                    maxCpoDtlLineNum = NWAL1500CommonLogic.getNextCpoDtlLineNum(maxCpoDtlLineNum);
                }
                String curCpoDtlLineNum = glblMsg.B.no(i).cpoDtlLineNum_LL.getValue();
                if (!S21StringUtil.isEquals(maxCpoDtlLineNum, curCpoDtlLineNum)) {
                    modifiedDtlLineNumMap.put(curCpoDtlLineNum, maxCpoDtlLineNum);
                    glblMsg.B.no(i).cpoDtlLineNum_LL.setValue(maxCpoDtlLineNum);
                    isModifyNum = true;
                }
            }
        }
        if (isModifyNum) {
            for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
                if (!S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, glblMsg.I.no(i).configCatgCd_LP.getValue())) {
                    continue;
                }
                String curCpoDtlLineNum = glblMsg.I.no(i).cpoDtlLineNum_LP.getValue();
                String newCpoDtlLineNum = modifiedDtlLineNumMap.get(curCpoDtlLineNum);
                if (ZYPCommonFunc.hasValue(newCpoDtlLineNum)) {
                    glblMsg.I.no(i).cpoDtlLineNum_LP.setValue(newCpoDtlLineNum);
                }
            }
        }

        // For RMA
        maxCpoDtlLineNum = "000";
        modifiedDtlLineNumMap.clear();
        savedLineList.clear();
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            boolean isAlreadySavedLine = isAlreadySavedLine(bizMsg, glblMsg.D.no(i));
            if (isAlreadySavedLine) {
                String lineKey = glblMsg.D.no(i).cpoDtlLineNum_RL.getValue() + glblMsg.D.no(i).cpoDtlLineSubNum_RL.getValue();
                savedLineList.add(lineKey);
            }
            if (!ZYPCommonFunc.hasValue(glblMsg.D.no(i).dsCpoLineSubNum_RL) && isAlreadySavedLine) {
                String cpoDtlLineNum = glblMsg.D.no(i).cpoDtlLineNum_RL.getValue();
                if (cpoDtlLineNum.compareTo(maxCpoDtlLineNum) > 0) {
                    maxCpoDtlLineNum = cpoDtlLineNum;
                }
            }
        }
        isModifyNum = false;
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            String lineKey = glblMsg.D.no(i).cpoDtlLineNum_RL.getValue() + glblMsg.D.no(i).cpoDtlLineSubNum_RL.getValue();
            boolean isAlreadySavedLine = savedLineList.contains(lineKey);
            if (!isAlreadySavedLine) {
                if (!ZYPCommonFunc.hasValue(glblMsg.D.no(i).dsCpoLineSubNum_RL)) {
                    maxCpoDtlLineNum = NWAL1500CommonLogic.getNextCpoDtlLineNum(maxCpoDtlLineNum);
                }
                String curCpoDtlLineNum = glblMsg.D.no(i).cpoDtlLineNum_RL.getValue();
                if (!S21StringUtil.isEquals(maxCpoDtlLineNum, curCpoDtlLineNum)) {
                    modifiedDtlLineNumMap.put(curCpoDtlLineNum, maxCpoDtlLineNum);
                    glblMsg.D.no(i).cpoDtlLineNum_RL.setValue(maxCpoDtlLineNum);
                    isModifyNum = true;
                }
            }
        }
        if (isModifyNum) {
            for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
                if (!S21StringUtil.isEquals(CONFIG_CATG.INBOUND, glblMsg.I.no(i).configCatgCd_LP.getValue())) {
                    continue;
                }
                String curCpoDtlLineNum = glblMsg.I.no(i).cpoDtlLineNum_LP.getValue();
                String newCpoDtlLineNum = modifiedDtlLineNumMap.get(curCpoDtlLineNum);
                if (ZYPCommonFunc.hasValue(newCpoDtlLineNum)) {
                    glblMsg.I.no(i).cpoDtlLineNum_LP.setValue(newCpoDtlLineNum);
                }
            }

            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(glblMsg.D.no(i).rtrnLineStsDescTxt_RL)) {
                    String curRefCpoDtlLineNum = glblMsg.D.no(i).refCpoDtlLineNum_RL.getValue();
                    String newRefCpoDtlLineNum = modifiedDtlLineNumMap.get(curRefCpoDtlLineNum);
                    if (ZYPCommonFunc.hasValue(newRefCpoDtlLineNum)) {
                        glblMsg.D.no(i).refCpoDtlLineNum_RL.setValue(newRefCpoDtlLineNum);
                    }
                }
            }
        }
    }

    private boolean isAlreadySavedLine(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg) { // 2018/01/31 S21_NA#19808 Mod

        if (!ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            return false;
        }

        CPO_DTLTMsg cpoDtlMsg = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.cpoOrdNum, bizMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.cpoDtlLineNum, lineMsg.cpoDtlLineNum_LL);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.cpoDtlLineSubNum, lineMsg.cpoDtlLineSubNum_LL);

        CPO_DTLTMsg cpoDtlRsltMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(cpoDtlMsg);
        if (cpoDtlRsltMsg == null) {
            ZYPEZDItemValueSetter.setValue(cpoDtlMsg.cpoDtlLineSubNum, "001");
            cpoDtlRsltMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(cpoDtlMsg);
        }
        if (cpoDtlRsltMsg == null) {
            ZYPEZDItemValueSetter.setValue(cpoDtlMsg.cpoDtlLineSubNum, "000");
            cpoDtlRsltMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(cpoDtlMsg);
        }
        return cpoDtlRsltMsg != null;
    }

    private boolean isAlreadySavedLine(NWAL1500CMsg bizMsg, NWAL1500_DSMsg rmaLineMsg) { // 2018/01/31 S21_NA#19808 Mod

        if (!ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            return false;
        }

        DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlMsg = new DS_CPO_RTRN_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlMsg.cpoOrdNum, bizMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlMsg.dsCpoRtrnLineNum, rmaLineMsg.cpoDtlLineNum_RL);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlMsg.dsCpoRtrnLineSubNum, rmaLineMsg.cpoDtlLineSubNum_RL);

        DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlRsltMsg = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKey(dsCpoRtrnDtlMsg);
        if (dsCpoRtrnDtlRsltMsg == null) {
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlMsg.dsCpoRtrnLineSubNum, "001");
            dsCpoRtrnDtlRsltMsg = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKey(dsCpoRtrnDtlMsg);
        }
        return dsCpoRtrnDtlRsltMsg != null;
    }
    // 2016/08/23 S21_NA#13504 Add End

    private void outputDeleteMsg(NWAL1500CMsg bizMsg, int[] cntArray) {
        // Header + Config + Line + RMA Config + RMA Line.
        int num = cntArray[CNT_CONFIG] + cntArray[CNT_LINE] + cntArray[CNT_RMA_CONFIG] + cntArray[CNT_RMA_LINE];
        String msg = String.format(MSG_FORMAT_REC_NUM_INFO_DEL, cntArray[CNT_CONFIG], cntArray[CNT_LINE], cntArray[CNT_RMA_CONFIG], cntArray[CNT_RMA_LINE]);
        bizMsg.setMessageInfo(NWAM0780I, //
                new String[] {MSG_PARAM_DELETED //
                , String.valueOf(num) //
                , msg});
    }
    // 2016/07/04 S21_NA#7821 Add End

    // 2016/07/25 S21_NA#5062, 9789 Add Start
    private boolean isRmaMainUnitWithoutAccesoryError(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2016/08/08 S21_NA#5062-2 Add Start
        if (isOrderServiceExchange(bizMsg)) {
            return false;
        }
        // 2016/08/08 S21_NA#5062-2 Add End

        // 2019/08/31 S21_NA#53179 Add Start
        if (isExcludeAutoAddRma(bizMsg)) {
            return false;
        }
        // 2019/08/31 S21_NA#53179 Add End

        boolean isRmaMainUnitOnlyError = false;
        Map<String, Map<String, String>> mainMachMap = new HashMap<String, Map<String, String>>();
        for (int slctRmaConfigIdx = 0; slctRmaConfigIdx < glblMsg.C.getValidCount(); slctRmaConfigIdx++) {
            NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(slctRmaConfigIdx);
            // 2018/11/19 S21_NA#27299 Add Start
            boolean isActionNew = NWXC150001DsCheck.matchConfigTp(//
                    bizMsg.glblCmpyCd.getValue(), rmaConfigMsg.configTpCd_RC.getValue(), CONFIG_CATG.INBOUND, true, false, false);
            if (isActionNew) {
                continue;
            }
            // 2018/11/19 S21_NA#27299 Add End
            // Get Config ID Structure
            if (isRmaMainUnitWithoutAccesoryError(bizMsg, glblMsg, rmaConfigMsg, mainMachMap)) {
                isRmaMainUnitOnlyError = true;
            }
        }
        return isRmaMainUnitOnlyError;
    }

    private boolean isRmaMainUnitWithoutAccesoryError(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_CSMsg rmaConfigMsg, Map<String, Map<String, String>> mainMachMap) {

        List<NWAL1500_DSMsg> rmaLineMsgList = getConfigRmaLineList(glblMsg, rmaConfigMsg.dsOrdPosnNum_RC.getValue());

        String configTpCd = rmaConfigMsg.configTpCd_RC.getValue();
        BigDecimal configId = null;
        // 2019/07/31 QC#51941 Add Start
        String existMainMachineFlg = NWZC182001Constant.EXIST_MAIN_MACHINE_IN_LINE_N;
        String mainMachMdseCd = null;
        String mainMachSerNum = null;
        NWZC182001PMsg autoRmaAddparam = new NWZC182001PMsg();
        int ibItemIdx = 0;
        // 2019/07/31 QC#51941 Add End
        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configTpCd, CONFIG_CATG.INBOUND, false, true, false)) {
            if (ZYPCommonFunc.hasValue(rmaConfigMsg.svcConfigMstrPk_RC)) {
                configId = rmaConfigMsg.svcConfigMstrPk_RC.getValue();
                // 2019/07/31 QC#51941 Add Start
                S21SsmEZDResult ssmRsltMainMachine = queryForSaveSubmit.getMainMachine(bizMsg.glblCmpyCd.getValue(), configId);

                if (ssmRsltMainMachine.isCodeNormal()) {
                    List<Map<String, Object>> mainMachList = (List<Map<String, Object>>) ssmRsltMainMachine.getResultObject();
                    Map<String, Object> mainMach = mainMachList.get(0);
                    if (mainMach != null) {
                        mainMachMdseCd = (String) mainMach.get("MDSE_CD");
                        mainMachSerNum = (String) mainMach.get("SER_NUM");
                    }
                }
                // 2019/07/31 QC#51941 Add End
            }
        }

        boolean instlBaseFlg = false;
        String instlBaseSerNum = null;
        boolean mdseValSetFlg = false;
        String mdseValSetSerNum = null;
        // 2017/03/02 QC#16575 ADD START
        String instlBaseMdseCd = null;
        String mdseValSetMdseCd = null;
        // 2017/03/02 QC#16575 ADD E N D

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        for (NWAL1500_DSMsg rmaLineMsg : rmaLineMsgList) {
            // 2019/01/18 S21_NA#29983 Add Start
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rmaLineMsg.rwsOpenFlg_RL.getValue()) // 
                    || S21StringUtil.isEquals(RTRN_LINE_STS.CANCELLED, rmaLineMsg.ordLineStsCd_RL.getValue()) // 
                    || S21StringUtil.isEquals(RTRN_LINE_STS.CLOSED, rmaLineMsg.ordLineStsCd_RL.getValue())) {
                return false;
            }
            // 2019/01/18 S21_NA#29983 Add End
            String mdseCd = rmaLineMsg.mdseCd_RL.getValue();
            MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdse(glblCmpyCd, mdseCd);

            // 2019/07/31 QC#51941 Add Start
            if (ZYPCommonFunc.hasValue(mainMachMdseCd)
                    && ZYPCommonFunc.hasValue(mdseMsg.mdseCd)
                    && mainMachMdseCd.equals(mdseMsg.mdseCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(mainMachSerNum)
                        && !ZYPCommonFunc.hasValue(rmaLineMsg.serNum_RL)) {
                    existMainMachineFlg = NWZC182001Constant.EXIST_MAIN_MACHINE_IN_LINE_Y;
                } else if (ZYPCommonFunc.hasValue(mainMachSerNum)
                        && ZYPCommonFunc.hasValue(rmaLineMsg.serNum_RL)
                        && mainMachSerNum.equals(rmaLineMsg.serNum_RL.getValue())) {
                    existMainMachineFlg = NWZC182001Constant.EXIST_MAIN_MACHINE_IN_LINE_Y;
                }
            }
            // 2019/07/31 QC#51941 Add End
            if (mdseMsg == null) {
                continue;
            }

            // 2016/08/04 S21_NA#13012 Add Start
            if (S21StringUtil.isEquals(MDSE_TP.SET, mdseMsg.mdseTpCd.getValue()) //
                    || !S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseMsg.invtyCtrlFlg.getValue())) {
                continue;
            }
            // 2016/08/04 S21_NA#13012 Add End
            Map<String, String> chkMap = mainMachMap.get(mdseMsg.mdseCd.getValue());
            if (chkMap == null) {
                S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getBaseComponentFlag(glblCmpyCd, mdseMsg.mdseCd.getValue());
                if (!ssmRslt.isCodeNormal()) {
                    mainMachMap.put(mdseMsg.mdseCd.getValue(), new HashMap<String, String>());
                    continue;
                }
                Map<String, String> ssmRsltMap = (Map<String, String>) ssmRslt.getResultObject();
                mainMachMap.put(mdseMsg.mdseCd.getValue(), ssmRsltMap);
                chkMap = ssmRsltMap;
            }
            String instlBaseCtrlFlg = chkMap.get("INSTL_BASE_CTRL_FLG");
            String mdseTpCtxTpCd = chkMap.get("MDSE_TP_CTX_TP_CD");

             if (!ZYPCommonFunc.hasValue(instlBaseCtrlFlg) //
                    && !ZYPCommonFunc.hasValue(mdseTpCtxTpCd)) {
                continue;
            }

            // 2019/07/31 QC#51941 Add Start
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instlBaseCtrlFlg)) {
                ZYPEZDItemValueSetter.setValue(autoRmaAddparam.A.no(ibItemIdx).mdseCd_A, rmaLineMsg.mdseCd_RL);
                ZYPEZDItemValueSetter.setValue(autoRmaAddparam.A.no(ibItemIdx).serNum_A, rmaLineMsg.serNum_RL);
                ibItemIdx++;
                autoRmaAddparam.A.setValidCount(ibItemIdx);
            }
            // 2019/07/31 QC#51941 Add End
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instlBaseCtrlFlg) && !instlBaseFlg) {
                instlBaseFlg = true;
                instlBaseSerNum = rmaLineMsg.serNum_RL.getValue();
                // 2017/03/02 QC#16575 ADD START
                instlBaseMdseCd = rmaLineMsg.mdseCd_RL.getValue();
                // 2017/03/02 QC#16575 ADD E N D
            }
            if (ZYPCommonFunc.hasValue(mdseTpCtxTpCd) && !mdseValSetFlg) {
                mdseValSetFlg = true;
                mdseValSetSerNum = rmaLineMsg.serNum_RL.getValue();
                // 2017/03/02 QC#16575 ADD START
                mdseValSetMdseCd = rmaLineMsg.mdseCd_RL.getValue();
                // 2017/03/02 QC#16575 ADD E N D
            }
        }

        if (configId == null && !instlBaseFlg && !mdseValSetFlg) {
            return false;
        }

        String serNum = null;
        // 2017/03/02 QC#16575 ADD START
        String mdseCd = null;
        // 2017/03/02 QC#16575 ADD E N D

        if (mdseValSetSerNum != null && mdseValSetMdseCd != null) {
            serNum  = mdseValSetSerNum;
            // 2017/03/02 QC#16575 ADD START
            mdseCd = mdseValSetMdseCd;
            // 2017/03/02 QC#16575 ADD E N D
        } else if (instlBaseSerNum != null && instlBaseMdseCd != null) {
            serNum = instlBaseSerNum;
            // 2017/03/02 QC#16575 ADD START
            mdseCd = instlBaseMdseCd;
            // 2017/03/02 QC#16575 ADD E N D
        }

        // 2019/07/31 QC#51941 Del Start
        //NWZC182001PMsg autoRmaAddparam = new NWZC182001PMsg();
        // 2019/07/31 QC#51941 Del End
        ZYPEZDItemValueSetter.setValue(autoRmaAddparam.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(autoRmaAddparam.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(autoRmaAddparam.dsOrdTpCd, bizMsg.dsOrdTpCd);

        if (ZYPCommonFunc.hasValue(configId)) {
            ZYPEZDItemValueSetter.setValue(autoRmaAddparam.svcConfigMstrPk, configId);
            // 2019/07/31 QC#51941 Add Start
            ZYPEZDItemValueSetter.setValue(autoRmaAddparam.serNum, serNum);
            ZYPEZDItemValueSetter.setValue(autoRmaAddparam.mdseCd, mdseCd);
            ZYPEZDItemValueSetter.setValue(autoRmaAddparam.mainMachFlg, existMainMachineFlg);
            // 2019/07/31 QC#51941 Add End
        } else if (ZYPCommonFunc.hasValue(serNum)) {
            ZYPEZDItemValueSetter.setValue(autoRmaAddparam.serNum, serNum);
            // 2017/03/02 QC#16575 ADD START
            ZYPEZDItemValueSetter.setValue(autoRmaAddparam.mdseCd, mdseCd);
            // 2017/03/02 QC#16575 ADD E N D
        } else {
            return false;
        }

        new NWZC182001().execute(autoRmaAddparam, ONBATCH_TYPE.ONLINE);
        // QC#29138 2018/11/14 Add Start
        if (S21ApiUtil.isXxMsgId(autoRmaAddparam)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(autoRmaAddparam);
            S21ApiMessage msg = msgList.get(0);
            rmaConfigMsg.xxChkBox_RC.setErrorInfo(1, msg.getXxMsgid());
            return true;
        }
        // QC#29138 2018/11/14 Add End

        boolean isMainMachineIncl = false;
        boolean isAccesoryNotIncl = false;

        // 2019/11/29 S21_NA#54879 Add Start
        List<NWAL1500RmaCheckComponentBean> rmaCheckComponentBeanAccessoryList = new ArrayList<NWAL1500RmaCheckComponentBean>(0);
        for (NWAL1500_DSMsg rmaLineMsg : rmaLineMsgList) {
            String coaMdseTpCd = getCoaMdseTpCd(glblCmpyCd, rmaLineMsg.mdseCd_RL.getValue());
            if (!S21StringUtil.isEquals(COA_MDSE_TP.MACHINE, coaMdseTpCd)) {
                NWAL1500RmaCheckComponentBean rmaCheckComponentBean = new NWAL1500RmaCheckComponentBean(rmaLineMsg);
                rmaCheckComponentBeanAccessoryList.add(rmaCheckComponentBean);
            }
        }
        // 2019/11/29 S21_NA#54879 Add End

        for (int i = 0; i < autoRmaAddparam.NWZC182002PMsg.getValidCount(); i++) {
            NWZC182002PMsg component = autoRmaAddparam.NWZC182002PMsg.no(i);
            String coaMdseTpCd = getCoaMdseTpCd(glblCmpyCd, component.mdseCd.getValue());
            if (S21StringUtil.isEquals(COA_MDSE_TP.MACHINE, coaMdseTpCd) //
                    && isIncludedInConfig(rmaLineMsgList, glblCmpyCd, component.mdseCd.getValue(), component.serNum.getValue())) { // 2016/07/28 S21_NA#5062-2 Add Param. serNum
                isMainMachineIncl = true;
                // 2019/11/29 S21_NA#54879 Mod Start
//            } else if (!S21StringUtil.isEquals(COA_MDSE_TP.MACHINE, coaMdseTpCd) //
//                    && !isIncludedInConfig(rmaLineMsgList, glblCmpyCd, component.mdseCd.getValue(), component.serNum.getValue())) { // 2016/07/28 S21_NA#5062-2 Add Param. serNum
//                isAccesoryNotIncl = true;
            } else {
                boolean findInRmaOrder = false;
                for (NWAL1500RmaCheckComponentBean rmaCheckComponentBean : rmaCheckComponentBeanAccessoryList) {
                    if (rmaCheckComponentBean.isSameItem(component.mdseCd.getValue(), component.serNum.getValue()) //
                            && !rmaCheckComponentBean.getCheckedFlg()) {
                        rmaCheckComponentBean.setCheckedFlg(true);
                        findInRmaOrder = true;
                        break;
                    }
                }
                if (!findInRmaOrder) {
                    isAccesoryNotIncl = true;
                }
                // 2019/11/29 S21_NA#54879 Mod End
            }
        }
        if (isMainMachineIncl && isAccesoryNotIncl) {
            rmaConfigMsg.xxChkBox_RC.setErrorInfo(1, NWAM0870E);
            return true;
        } else {
            return false;
        }
    }

    private List<NWAL1500_DSMsg> getConfigRmaLineList(NWAL1500SMsg glblMsg, String dsOrdPosnNum) {

        List<NWAL1500_DSMsg> rmaLineMsgList = new ArrayList<NWAL1500_DSMsg>(0);
        for (int slctRmaLineIdx = 0; slctRmaLineIdx < glblMsg.D.getValidCount(); slctRmaLineIdx++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(slctRmaLineIdx).dsOrdPosnNum_RL.getValue())) {
                rmaLineMsgList.add(glblMsg.D.no(slctRmaLineIdx));
            }
        }
        return rmaLineMsgList;
    }

    private boolean isIncludedInConfig(List<NWAL1500_DSMsg> rmaLineMsgList, String glblCmpyCd, String mdseCd, String serNum) { // 2016/07/28 S21_NA#5062-2 Add Param. serNum

        boolean isIncludedInConfig = false;
        for (NWAL1500_DSMsg rmaLineMsg : rmaLineMsgList) {
            String rmaLineMdseCd = rmaLineMsg.mdseCd_RL.getValue();
            // 2016/07/28 S21_NA#5062-2 Add Start
            boolean isSameSerNum = false;
            String rmaSerNum = rmaLineMsg.serNum_RL.getValue();
            if (ZYPCommonFunc.hasValue(serNum) && ZYPCommonFunc.hasValue(rmaSerNum)) {
                if (serNum.equals(rmaSerNum)) {
                    isSameSerNum = true;
                }
            }
            if (!ZYPCommonFunc.hasValue(serNum) && !ZYPCommonFunc.hasValue(rmaSerNum)) {
                isSameSerNum = true;
            }
            if (!ZYPCommonFunc.hasValue(serNum) && ZYPCommonFunc.hasValue(rmaSerNum)) {
                isSameSerNum = true;
            }
            // 2016/07/28 S21_NA#5062-2 Add End
            MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdse(glblCmpyCd, rmaLineMdseCd);
            if (mdseMsg != null //
                    && S21StringUtil.isEquals(mdseCd, mdseMsg.mdseCd.getValue()) //
                    && isSameSerNum) { // 2016/07/28 S21_NA#5062-2 Add
                isIncludedInConfig = true;
            }
        }
        return isIncludedInConfig;
    }

    private String getCoaMdseTpCd(String glblCmpyCd, String mdseCd) {

        MDSETMsg dsMdseInfoMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(dsMdseInfoMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsMdseInfoMsg.mdseCd, mdseCd);

        dsMdseInfoMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(dsMdseInfoMsg);
        if (dsMdseInfoMsg != null) {
            return dsMdseInfoMsg.coaMdseTpCd.getValue();
        } else {
            return "";
        }
    }
    // 2016/07/25 S21_NA#5062, 9789 Add End

    // 2016/08/08 S21_NA#5062-2 Add Start
    private boolean isOrderServiceExchange(NWAL1500CMsg bizMsg) {

        return NWAL1500QueryForLineConfig.getInstance().isExistOrdCatg(bizMsg, ORD_CATG_CTX_TP.SERVICE_EXCHANGE_ORDER_VALUE_SET, false);
    }
    // 2016/08/08 S21_NA#5062-2 Add End

    // S21_NA#13769(S21_NA#8388) ADD START
    /**
     * Check decline service
     * @param bizMsg Business Message
     * @return true: normal end, false: abnornal end
     */
    public boolean checkDclnSvc(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/31 S21_NA#19808 Mod

        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.dclnSvcCd.getValue())) {
            // Get CPO_SVC_DTL
            S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getCntCpoSvcDtl(bizMsg);
            Integer cnt = (Integer) ssmResult.getResultObject();
            if (cnt != null && 0 < cnt) {
                // Exists Shell(CPO_SVC_DTL)
                bizMsg.dclnSvcCd.setErrorInfo(1, NWAM0875E); // Please delete Shell info.
                return false;
            }
        }
        boolean configErr = false;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) { // 2018/01/31 S21_NA#19808 Mod
            NWAL1500_ASMsg config = glblMsg.A.no(i); // 2018/01/31 S21_NA#19808 Mod
            if (ZYPConstant.CHKBOX_ON_Y.equals(config.dclnSvcCd_LC.getValue())) {
                String lineNum = null;
                String lineSubNum = null;
                for (int j = 0; j < glblMsg.B.getValidCount(); j++) { // 2018/01/31 S21_NA#19808 Mod
                    NWAL1500_BSMsg dtl = glblMsg.B.no(j); // 2018/01/31 S21_NA#19808 Mod
                    if (config.dsOrdPosnNum_LC.getValue().equals(dtl.dsOrdPosnNum_LL.getValue()) && ZYPConstant.FLG_ON_Y.equals(dtl.baseCmptFlg_LL.getValue())) {
                        lineNum = dtl.cpoDtlLineNum_LL.getValue();
                        lineSubNum = dtl.cpoDtlLineSubNum_LL.getValue();
                        break;
                    }
                }
                if (!ZYPCommonFunc.hasValue(lineNum) || !ZYPCommonFunc.hasValue(lineSubNum)) {
                    continue;
                }
                // Get CPO_SVC_DTL
                S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getCntCpoSvcConfigRef(bizMsg, lineNum, lineSubNum);
                Integer cnt = (Integer) ssmResult.getResultObject();
                if (cnt != null && 0 < cnt) {
                    // Exists Shell(CPO_SVC_DTL)
                    config.dclnSvcCd_LC.setErrorInfo(1, NWAM0875E); // Please delete Shell info.
                    configErr = true;
                }
            }
        }
        if (configErr) {
            return false;
        }
        return true;
    }

    // 2016/09/13 S21_NA#8276 Add Start
    private boolean checkModelParentItemLineConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg configMsg, Map<String, Boolean> prntModelItemCheckMap) { // 2018/01/31 S21_NA#19808 Mod

//        if (!isOrderRetailEquipment(bizMsg)) { 2017/02/24 S21_NA#17714 Mod Start
        if (!NWAL1500CommonLogic.isOrderRetailEquipment(bizMsg)) { // 2017/02/24 S21_NA#17714 Mod End
            return false;
        }
        boolean rslt = false;
        int baseCmpLineMsgIdx = getBaseCmpLineMsgIdx(configMsg, glblMsg.B);
        if (baseCmpLineMsgIdx < 0) {
            return false;
        }
        String checkPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
        String cpoDtlLine = glblMsg.B.no(baseCmpLineMsgIdx).cpoDtlLineNum_LL.getValue(); // 2018/01/31 S21_NA#19808 Mod
        // 2018/10/09/ QC#28383 add start
        String swMdlFlg = "N";
        if (ZYPCommonFunc.hasValue(configMsg.svcMdlTpCd_LC)) {
            SVC_MDL_TPTMsg tMsg = new SVC_MDL_TPTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcMdlTpCd, configMsg.svcMdlTpCd_LC);
            tMsg = (SVC_MDL_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
            swMdlFlg = tMsg.swMdlFlg.getValue();
        }
        // 2018/10/09/ QC#28383 add end

        // 2018/01/31 S21_NA#19808 Add Start
        List<Integer> errPageList = new ArrayList<Integer>(0);
        // 2018/01/31 S21_NA#19808 Add End
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) { // 2018/01/31 S21_NA#19808 Mod
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i); // 2018/01/31 S21_NA#19808 Mod
            if (i == baseCmpLineMsgIdx) {
                continue;
            }
            if (S21StringUtil.isEquals(cpoDtlLine, lineMsg.cpoDtlLineNum_LL.getValue())) {
                continue;
            }
            if (S21StringUtil.isEquals("000", lineMsg.cpoDtlLineSubNum_LL.getValue())) {
                continue;
            }
            if (!S21StringUtil.isEquals(checkPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                continue;
            }
            // 2018/06/18 S21_NA#26598 Add Start
            if (S21StringUtil.isEquals(bizMsg.varCharConstVal_TB.getValue(), lineMsg.ordLineStsDescTxt_LL.getValue()) //
                    || S21StringUtil.isEquals(ORD_LINE_STS.CANCELLED, lineMsg.ordLineStsCd_LL.getValue())) { // 2018/06/29 S21_NA#26598-2 Add "Canncelld"
                continue;
            }
            // 2018/06/18 S21_NA#26598 Add End
            if (isParentItemOfModel(bizMsg, lineMsg, prntModelItemCheckMap, swMdlFlg)) { // 2018/10/09/ QC#28383 mod
                lineMsg.mdseCd_LL.setErrorInfo(1, NWAM0892E);
                rslt = true;
                // 2018/01/31 S21_NA#19808 Add Start
                Integer errPage = Integer.valueOf(lineMsg.xxPageNum_LL.getValueInt());
                if (!errPageList.contains(errPage)) {
                    errPageList.add(errPage);
                }
                // 2018/01/31 S21_NA#19808 Add End
            }
        }
        if (rslt) {
            glblMsg.B.no(baseCmpLineMsgIdx).mdseCd_LL.setErrorInfo(1, NWAM0892E);
            // 2018/01/31 S21_NA#19808 Add Start
            Integer errPage = Integer.valueOf(glblMsg.B.no(baseCmpLineMsgIdx).xxPageNum_LL.getValueInt());
            if (!errPageList.contains(errPage)) {
                errPageList.add(errPage);
            }
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, errPageList.get(0));
            // 2018/01/31 S21_NA#19808 Add End
        }
        return rslt;
    }

    private int getBaseCmpLineMsgIdx(NWAL1500_ASMsg configMsg, NWAL1500_BSMsgArray lineMsgArray) { // 2018/01/31 S21_NA#19808 Mod

        int slctLineCnt = 0;
        String configPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
        for (; slctLineCnt < lineMsgArray.getValidCount(); slctLineCnt++) {
            String linePosnNum = lineMsgArray.no(slctLineCnt).dsOrdPosnNum_LL.getValue();
            String baseCmpFlg = lineMsgArray.no(slctLineCnt).baseCmptFlg_LL.getValue();
            if (S21StringUtil.isEquals(configPosnNum, linePosnNum) //
                    && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, baseCmpFlg)) {
                return slctLineCnt;
            }
        }
        return -1;
    }

    private boolean isParentItemOfModel(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg, Map<String, Boolean> prntModelItemCheckMap, String swMdlFlg) { // 2018/01/31 S21_NA#19808 Mod

        if (prntModelItemCheckMap == null) {
            prntModelItemCheckMap = new HashMap<String, Boolean>();
        }

        String mdseCd = lineMsg.mdseCd_LL.getValue();
        MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), mdseCd);
        if (mdseMsg != null) {
            mdseCd = mdseMsg.mdseCd.getValue();
        }

        Boolean isParentItemOfModel = prntModelItemCheckMap.get(mdseCd);

        if (isParentItemOfModel == null) {
            S21SsmEZDResult ssmResult = NWAL1500QueryForSaveSubmit.getInstance().checkMdlPrntMdse(bizMsg.glblCmpyCd.getValue(), mdseCd, swMdlFlg); // 2018/10/09 QC#28383 mod
            if (ssmResult.isCodeNormal()) {
                List<BigDecimal> resultCntList = (List<BigDecimal>) ssmResult.getResultObject();
                BigDecimal totalCnt = BigDecimal.ZERO;
                for (BigDecimal resultCnt : resultCntList) {
                    totalCnt = totalCnt.add(resultCnt);
                }
                if (totalCnt.compareTo(BigDecimal.ZERO) > 0) {
                    isParentItemOfModel = Boolean.TRUE;
                    prntModelItemCheckMap.put(mdseCd, Boolean.TRUE);
                } else {
                    isParentItemOfModel = Boolean.FALSE;
                    prntModelItemCheckMap.put(mdseCd, Boolean.FALSE);
                }
            } else {
                isParentItemOfModel = Boolean.FALSE;
                prntModelItemCheckMap.put(mdseCd, Boolean.FALSE);
            }
        }
        return isParentItemOfModel.booleanValue();
    }
    // 2016/09/13 S21_NA#8276 Add End

    // 2016/09/21 S21_NA#10274 Add Start
    private boolean isDeleteProc(NWZC150001PMsg pMsg) {

        return S21StringUtil.isEquals(NWZC150001Constant.MODE_DELETE, pMsg.xxModeCd.getValue());
    }
    // 2016/09/21 S21_NA#10274 Add End

    // 2016/10/18 S21_NA#13107 Add Start
    private boolean hasRebillLine(NWAL1500CMsg bizMsg, NWAL1500_BSMsg bcMsg) { // 2018/01/31 S21_NA#19808 Mod
        CPO_DTLTMsg dsCpoDtlTMsg = new CPO_DTLTMsg();
        dsCpoDtlTMsg.setSQLID("503");
        dsCpoDtlTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        dsCpoDtlTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
        dsCpoDtlTMsg.setConditionValue("dsOrdPosnNum01", bcMsg.dsOrdPosnNum_LL.getValue());

        CPO_DTLTMsgArray dsCpoDtlTMsgArray = (CPO_DTLTMsgArray) EZDTBLAccessor.findByCondition(dsCpoDtlTMsg);

        if (dsCpoDtlTMsgArray.getValidCount() != 0) {
            return true;
        }

        return false;
    }
    // 2016/10/18 S21_NA#13107 Add End
    // 2016/11/08 S21_NA#15427 Add Start
    private boolean isAvalLineCategory(NWAL1500CMsg bizMsg, EZDMsg lineMsg) { // 2016/11/10 S21_NA#15427-2 I/F Change, and Logic Change

        String lineCatgCd = "";
        String origCpoOrdNum = "";
        String origCpoDtlLineNum = "";
        String origCpoDtlLineSubNum = "";

        if (lineMsg instanceof NWAL1500_BCMsg) {
            lineCatgCd = ((NWAL1500_BCMsg) lineMsg).dsOrdLineCatgCd_LL.getValue();
            origCpoOrdNum = ((NWAL1500_BCMsg) lineMsg).origCpoOrdNum_LL.getValue();
            origCpoDtlLineNum = ((NWAL1500_BCMsg) lineMsg).origCpoDtlLineNum_LL.getValue();
            origCpoDtlLineSubNum = ((NWAL1500_BCMsg) lineMsg).origCpoDtlLineSubNum_LL.getValue();
        } else if (lineMsg instanceof NWAL1500_DCMsg) {
            lineCatgCd = ((NWAL1500_DCMsg) lineMsg).dsOrdLineCatgCd_RL.getValue();
            origCpoOrdNum = ((NWAL1500_DCMsg) lineMsg).origCpoOrdNum_RL.getValue();
            origCpoDtlLineNum = ((NWAL1500_DCMsg) lineMsg).origCpoDtlLineNum_RL.getValue();
            origCpoDtlLineSubNum = ((NWAL1500_DCMsg) lineMsg).origCpoDtlLineSubNum_RL.getValue();
        } else if (lineMsg instanceof NWAL1500_BSMsg) {
            lineCatgCd = ((NWAL1500_BSMsg) lineMsg).dsOrdLineCatgCd_LL.getValue();
            origCpoOrdNum = ((NWAL1500_BSMsg) lineMsg).origCpoOrdNum_LL.getValue();
            origCpoDtlLineNum = ((NWAL1500_BSMsg) lineMsg).origCpoDtlLineNum_LL.getValue();
            origCpoDtlLineSubNum = ((NWAL1500_BSMsg) lineMsg).origCpoDtlLineSubNum_LL.getValue();
        } else if (lineMsg instanceof NWAL1500_DSMsg) {
            lineCatgCd = ((NWAL1500_DSMsg) lineMsg).dsOrdLineCatgCd_RL.getValue();
            origCpoOrdNum = ((NWAL1500_DSMsg) lineMsg).origCpoOrdNum_RL.getValue();
            origCpoDtlLineNum = ((NWAL1500_DSMsg) lineMsg).origCpoDtlLineNum_RL.getValue();
            origCpoDtlLineSubNum = ((NWAL1500_DSMsg) lineMsg).origCpoDtlLineSubNum_RL.getValue();
        } else {
            return true;
        }

        if (this.loanDsOrdLineCatgList.isEmpty()) {
            S21SsmEZDResult ssmRslt = NWAL1500QueryForSaveSubmit.getInstance().getLineCategoryCodeFromValueSet(bizMsg.glblCmpyCd.getValue(), ORD_LINE_CTX_TP.LOAN_CONV_LINE, null);
            if (ssmRslt.isCodeNormal()) {
                List<String> ssmRsltList = (List<String>) ssmRslt.getResultObject();
                for (String loanDsLineCatgCd : ssmRsltList) {
                    this.loanDsOrdLineCatgList.add(loanDsLineCatgCd);
                }
            } else {
                this.loanDsOrdLineCatgList.add("_");
            }
        }

        if (this.loanDsOrdLineCatgList.contains(lineCatgCd)) {
            if (ZYPCommonFunc.hasValue(origCpoOrdNum) //
                    && ZYPCommonFunc.hasValue(origCpoDtlLineNum) //
                    && ZYPCommonFunc.hasValue(origCpoDtlLineSubNum)) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
    // 2016/11/08 S21_NA#15427 Add End

    // 2016/11/08 S21_NA#7749 Add Start
    /**
     * SVC_MACH_MSTR check for Config Tab
     * @param bizMsg Business Message
     * @return true: have error false: no error
     */
    public boolean isAllCmptReqErrOnLineConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/31 S21_NA#19808 Mod
        boolean rslt = false;

     // 2018/01/31 S21_NA#19808 Mod bizMsg => glblMsg
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg configMsg = glblMsg.A.no(i);
            String configTpCd = configMsg.configTpCd_LC.getValue();
            String posnNum = configMsg.dsOrdPosnNum_LC.getValue();

            // ALL_CMPT_REQ_FLG ='Y'
            if (NWXC150001DsCheck.isAllCmptReqConfigTp(bizMsg.glblCmpyCd.getValue(), configTpCd, CONFIG_CATG.OUTBOUND)) {
                // get SVC_MACH_MSTR List by Config ID
                BigDecimal svcConfigMstrPk = configMsg.svcConfigMstrPk_LC.getValue();
                SVC_MACH_MSTRTMsgArray tMsgArray = queryForSaveSubmit.getSvcMachMstrByConfigID(bizMsg.glblCmpyCd.getValue(), svcConfigMstrPk);

                // check for contain all component in the line config 
                int matchCount = 0;
                // 2017/12/07 S21_NA#22794 Add Start
                List<SVC_MACH_MSTRTMsg> noCheckSvcMachMstrNoCheckList = new ArrayList<SVC_MACH_MSTRTMsg>(0);
                // 2017/12/07 S21_NA#22794 Add End
                for (int j = 0; j < tMsgArray.getValidCount(); j++) {
                    SVC_MACH_MSTRTMsg tMsg = (SVC_MACH_MSTRTMsg) tMsgArray.get(j);
                    String dbMdseCd = tMsg.mdseCd.getValue();
                    BigDecimal dbSvcMachMstrPk = tMsg.svcMachMstrPk.getValue();
                    String dbSerNum = tMsg.serNum.getValue();

                    // 2017/12/07 S21_NA#22794 Add Start
                    boolean checkedFlg = false;
                    // 2017/12/07 S21_NA#22794 Add End
                    for (int k = 0; k < glblMsg.B.getValidCount(); k++) {
                        NWAL1500_BSMsg lineMsg = glblMsg.B.no(k);
                        // 2017/12/07 S21_NA#22794 Mod Start
//                        String lineMdseCd = tMsg.mdseCd.getValue();
                        String lineMdseCd = lineMsg.mdseCd_LL.getValue();
                        // 2017/12/07 S21_NA#22794 Mod End
                        BigDecimal lineSvcMachMstrPk = lineMsg.svcMachMstrPk_LL.getValue();

                        if (!posnNum.equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
                            continue;
                        }
                        // poputated by popup / after submit
                        if (ZYPCommonFunc.hasValue(lineSvcMachMstrPk)) {
                            // 2017/12/07 S21_NA#22794 Mod Start
//                            if (dbMdseCd.equals(lineMdseCd) && dbSvcMachMstrPk.equals(lineSvcMachMstrPk)) {
                            if (isSameMdseCd(dbMdseCd, lineMdseCd) && dbSvcMachMstrPk.equals(lineSvcMachMstrPk)) {
                                // 2017/12/07 S21_NA#22794 Mod End
                                matchCount++;
                                // 2017/12/07 S21_NA#22794 Add Start
                                checkedFlg = true;
                                // 2017/12/07 S21_NA#22794 Add End
                                break;
                            }
                        // populated by hand
                        } else {
                            // 2017/12/07 S21_NA#22794 Mod Start
//                            if (dbMdseCd.equals(lineMdseCd)) {
                            if (isSameMdseCd(dbMdseCd, lineMdseCd)) {
                                // 2017/12/07 S21_NA#22794 Mod End
                                ZYPEZDItemValueSetter.setValue(lineMsg.svcMachMstrPk_LL, dbSvcMachMstrPk);
                                // 2016/12/07 S21_NA#15934 Mod Start
//                                ZYPEZDItemValueSetter.setValue(lineMsg.serNum_LL, dbSerNum);
                                if (!CPO_DTL_LINE_SUB_NUM_SET_PRNT.equals(lineMsg.cpoDtlLineSubNum_LL.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(lineMsg.serNum_LL, dbSerNum);
                                }
                                // 2016/12/07 S21_NA#15934 Mod End
                                matchCount++;
                                // 2017/12/07 S21_NA#22794 Add Start
                                checkedFlg = true;
                                // 2017/12/07 S21_NA#22794 Add End
                                break;
                            }
                        }
                    }
                    if (!checkedFlg) {
                        noCheckSvcMachMstrNoCheckList.add(tMsg);
                    }
                }

                // 2017/12/07 S21_NA#22794 Add Start
                if (S21StringUtil.isEquals(CPO_SRC_TP.DEAL_CONFIGURATOR, bizMsg.cpoSrcTpCd.getValue()) //
                        && matchCount != tMsgArray.getValidCount()) {
                    // To be returnd from Original Order RMA?
                    S21SsmEZDResult ssmRslt = NWAL1500QueryForSaveSubmit.getInstance().getOtherOrderRmaConfigDetail(bizMsg, configMsg);
                    if (ssmRslt.getQueryResultCount() > 0) {
                        List<DS_CPO_RTRN_DTLTMsg> rtrnDtlTMsgList = (List<DS_CPO_RTRN_DTLTMsg>) ssmRslt.getResultObject();
                        for (SVC_MACH_MSTRTMsg noCheckSvcMachMstr : noCheckSvcMachMstrNoCheckList) {
                            String dbMdseCd = noCheckSvcMachMstr.mdseCd.getValue();
                            String dbSerNum = noCheckSvcMachMstr.serNum.getValue();
                            for (DS_CPO_RTRN_DTLTMsg rtrnDtlTMsg : rtrnDtlTMsgList) {
                                String lineMdseCd = rtrnDtlTMsg.mdseCd.getValue();
                                String lineSerNum = rtrnDtlTMsg.serNum.getValue();
                                if (isSameMdseCd(dbMdseCd, lineMdseCd) //
                                        && S21StringUtil.isEquals(dbSerNum, lineSerNum)) {
                                    matchCount++;
                                    break;
                                }
                            }
                        }
                    }
                }
                // 2017/12/07 S21_NA#22794 Add End

                if (matchCount != tMsgArray.getValidCount()) {
                    configMsg.svcConfigMstrPk_LC.setErrorInfo(1, NWAM0908E);
                    rslt = true;
                    break;
                }
            // ALL_CMPT_REQ_FLG ='N'
            } else {
                //do nothing 
            }
        }
        return rslt;
    }
    // 2016/11/08 S21_NA#7749 Add End

    // 2016/11/09 S21_NA#9864 Add Start
    private boolean isAvalLoanShipment(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg) { // 2018/01/31 S21_NA#19808 Add Mod

        // 2016/11/11 S21_NA#9864-2 Mod Start
//        String rslt = NWAL1500Query.getInstance().getOrdCatgBizCtx(bizMsg.glblCmpyCd.getValue() //
//                , ORD_CATG_CTX_TP.LOAN_ORDER_VALUE_SET //
//                , bizMsg.dsOrdCatgCd.getValue() //
//                , bizMsg.dsOrdTpCd.getValue());
//        boolean isLoanOrder = false;
//        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rslt)) {
//            isLoanOrder = true;
//        }
        boolean isLoanOrder = NWAL1500CommonLogic.isLoanOrder(bizMsg);
        // 2016/11/11 S21_NA#9864-2 Mod End
        if (isLoanOrder) {
            // S21_NA#17586 Mod Start
            if (isLoanShipLineCategory(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdTpCd.getValue(), lineMsg.dsOrdLineCatgCd_LL.getValue(), bizMsg.slsDt.getValue())) {
            // S21_NA#17586 Mod End
                return isInvtyTrackAndIBTrack(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
            }
            return true;
        } else {
            return true;
        }
    }

    // S21_NA#17586 Mod Start
    private boolean isLoanShipLineCategory(String glblCmpyCd, String dsOrdTpCd, String dsOrdLineCatgCd, String slsDt) {
    // S21_NA#17586 Mod End

        Boolean isLoanShipLineCategory = this.loanShipmentDsOrdLineCatgMap.get(dsOrdLineCatgCd);

        if (isLoanShipLineCategory == null) {
            // S21_NA#17586 Mod Start
            S21SsmEZDResult ssmRslt = NWAL1500QueryForSaveSubmit.getInstance().getLoanShipLineCategory(glblCmpyCd, dsOrdTpCd, dsOrdLineCatgCd, slsDt);
            // S21_NA#17586 Mod End
            if (ssmRslt.isCodeNormal()) {
                isLoanShipLineCategory = new Boolean(true);
            } else {
                isLoanShipLineCategory = new Boolean(false);
            }
            this.loanShipmentDsOrdLineCatgMap.put(dsOrdLineCatgCd, isLoanShipLineCategory);
        }

        return isLoanShipLineCategory.booleanValue();
    }
    private boolean isInvtyTrackAndIBTrack(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdse(glblCmpyCd, mdseCd);
        if (mdseMsg != null) {
            // 2016/11/11 S21_NA#9864-2 Add Start
            if (S21StringUtil.isEquals(MDSE_TP.SET, mdseMsg.mdseTpCd.getValue())) {
                return true;
            }
            // 2016/11/11 S21_NA#9864-2 Add End
            return S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseMsg.invtyCtrlFlg.getValue()) //
                && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseMsg.instlBaseCtrlFlg.getValue());
        }
        return false;
    }
    // 2016/11/09 S21_NA#9864 Add End

    // S21_NA#15889 Add Start
    private void setCpoUpdApiReqDiscForDelete(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.shipToCustAcctNm, glblMsg.shipToCustAcctNm)
                || !NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.shipToCustAcctCd, glblMsg.shipToCustAcctCd)
                || !NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.shipToCustCd, glblMsg.shipToCustCd)) {
            setCpoUpdApiReqDeliveryForDelete(cpoUpdApiReqPMsg, bizMsg, null);
            setCpoUpdApiReqInstallForDelete(cpoUpdApiReqPMsg, bizMsg, null);
            setCpoUpdApiReqSiteSurveyForDelete(cpoUpdApiReqPMsg, bizMsg, null);
            setCpoUpdApiReqCtacPsnForDelete(cpoUpdApiReqPMsg, bizMsg, null);
        }

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) { // 2018/01/31 S21_NA#19808 Mod
            NWAL1500_ASMsg configMsg = glblMsg.A.no(i); // 2018/01/31 S21_NA#19808 Mod
            BigDecimal dsCpoConfigPk = configMsg.dsCpoConfigPk_LC.getValue();
//            String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue(); 2018/01/31 S21_NA#19808 Del

            if (!ZYPCommonFunc.hasValue(dsCpoConfigPk)) {
                continue;
            }

            // 2018/01/31 S21_NA#19808 Del Start
//            for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
//                NWAL1500_ASMsg glblConfigMsg = glblMsg.A.no(j);
//
//                if (!dsOrdPosnNum.equals(glblConfigMsg.dsOrdPosnNum_LC.getValue())) {
//                    continue;
//                } else if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToCustAcctCd_LC, glblConfigMsg.shipToCustAcctCd_DB)
//                        || !NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToCustCd_LC, glblConfigMsg.shipToCustCd_DB)
//                        || !NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToCustAcctNm_LC, glblConfigMsg.shipToCustAcctNm_DB)) {
//                    setCpoUpdApiReqDeliveryForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
//                    setCpoUpdApiReqInstallForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
//                    setCpoUpdApiReqSiteSurveyForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
//                    setCpoUpdApiReqCtacPsnForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
//                }
//            }
            // 2018/01/31 S21_NA#19808 Del End
            // 2018/01/31 S21_NA#19808 Add Start
            if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToCustAcctCd_LC, configMsg.shipToCustAcctCd_DB)
                    || !NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToCustCd_LC, configMsg.shipToCustCd_DB)
                    || !NWAL1500CommonLogic.isEqualsEZDItem(configMsg.shipToCustAcctNm_LC, configMsg.shipToCustAcctNm_DB)) {
                setCpoUpdApiReqDeliveryForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
                setCpoUpdApiReqInstallForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
                setCpoUpdApiReqSiteSurveyForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
                setCpoUpdApiReqCtacPsnForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
            }
            // 2018/01/31 S21_NA#19808 Add End
        }

        for (int i = 0; i < glblMsg.C.getValidCount() && i < glblMsg.C.getValidCount(); i++) {
            NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(i);
            BigDecimal dsCpoConfigPk = rmaConfigMsg.dsCpoConfigPk_RC.getValue();
//            String dsOrdPosnNum = rmaConfigMsg.dsOrdPosnNum_RC.getValue(); 2018/01/31 S21_NA#19808 Del

            if (!ZYPCommonFunc.hasValue(dsCpoConfigPk)) {
                continue;
            }

            // 2018/01/31 S21_NA#19808 Del Start
//            for (int j = 0; j < glblMsg.C.getValidCount(); j++) {
//                NWAL1500_CSMsg glblRmaConfigMsg = glblMsg.C.no(j);
//
//                if (!dsOrdPosnNum.equals(glblRmaConfigMsg.dsOrdPosnNum_RC.getValue())) {
//                    continue;
//                } else if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigMsg.shipToCustCd_RC, glblRmaConfigMsg.shipToCustCd_DB)
//                        || !NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigMsg.shipToCustAcctCd_RC, glblRmaConfigMsg.shipToCustAcctCd_DB)
//                        || !NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigMsg.shipToCustAcctNm_RC, glblRmaConfigMsg.shipToCustAcctNm_DB)) {
//                    setCpoUpdApiReqDeliveryForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
//                    setCpoUpdApiReqInstallForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
//                    setCpoUpdApiReqSiteSurveyForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
//                    setCpoUpdApiReqCtacPsnForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
//                }
//            }
            // 2018/01/31 S21_NA#19808 Del End
            // 2018/01/31 S21_NA#19808 Add Start
            if (!NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigMsg.shipToCustCd_RC, rmaConfigMsg.shipToCustCd_DB)
                    || !NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigMsg.shipToCustAcctCd_RC, rmaConfigMsg.shipToCustAcctCd_DB)
                    || !NWAL1500CommonLogic.isEqualsEZDItem(rmaConfigMsg.shipToCustAcctNm_RC, rmaConfigMsg.shipToCustAcctNm_DB)) {
                setCpoUpdApiReqDeliveryForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
                setCpoUpdApiReqInstallForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
                setCpoUpdApiReqSiteSurveyForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
                setCpoUpdApiReqCtacPsnForDelete(cpoUpdApiReqPMsg, bizMsg, dsCpoConfigPk);
            }
            // 2018/01/31 S21_NA#19808 Add End
        }
    }

    @SuppressWarnings("unchecked")
    private void setCpoUpdApiReqDeliveryForDelete(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg, BigDecimal dsCpoConfigPk) {

        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getDsCpoDelyInfoPk(bizMsg, dsCpoConfigPk);
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<BigDecimal> dsCpoDelyInfoPkList = (List<BigDecimal>) ssmResult.getResultObject();
        BigDecimal dsCpoDelyInfoPk = dsCpoDelyInfoPkList.get(0);

        int vldCnt = cpoUpdApiReqPMsg.cpoDlvyInfoList.getValidCount();
        NWZC150001_cpoDlvyInfoListPMsg pMsg = cpoUpdApiReqPMsg.cpoDlvyInfoList.no(vldCnt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.dsCpoDelyInfoPk, dsCpoDelyInfoPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsCpoConfigPk, dsCpoConfigPk); // 2019/02/20 S21_NA#30447 Add
        cpoUpdApiReqPMsg.cpoDlvyInfoList.setValidCount(++vldCnt);
    }

    @SuppressWarnings("unchecked")
    private void setCpoUpdApiReqInstallForDelete(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg, BigDecimal dsCpoConfigPk) {

        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getDsCpoIstlInfoPk(bizMsg, dsCpoConfigPk);
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<BigDecimal> dsCpoIstlInfoPkList = (List<BigDecimal>) ssmResult.getResultObject();
        BigDecimal dsCpoIstlInfoPk = dsCpoIstlInfoPkList.get(0);

        int vldCnt = cpoUpdApiReqPMsg.cpoIstlInfoList.getValidCount();
        NWZC150001_cpoIstlInfoListPMsg pMsg = cpoUpdApiReqPMsg.cpoIstlInfoList.no(vldCnt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.dsCpoIstlInfoPk, dsCpoIstlInfoPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsCpoConfigPk, dsCpoConfigPk); // 2019/02/20 S21_NA#30447 Add
        cpoUpdApiReqPMsg.cpoIstlInfoList.setValidCount(++vldCnt);
    }

    @SuppressWarnings("unchecked")
    private void setCpoUpdApiReqSiteSurveyForDelete(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg, BigDecimal dsCpoConfigPk) {

        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getDsSiteSrvyPk(bizMsg, dsCpoConfigPk);
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<BigDecimal> dsSiteSrvyPkList = (List<BigDecimal>) ssmResult.getResultObject();
        BigDecimal dsSiteSrvyPk = dsSiteSrvyPkList.get(0);

        int vldCnt = cpoUpdApiReqPMsg.siteSrvInfoList.getValidCount();
        NWZC150001_siteSrvInfoListPMsg pMsg = cpoUpdApiReqPMsg.siteSrvInfoList.no(vldCnt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.dsSiteSrvyPk, dsSiteSrvyPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsCpoConfigPk, dsCpoConfigPk); // 2019/02/20 S21_NA#30447 Add
        cpoUpdApiReqPMsg.siteSrvInfoList.setValidCount(++vldCnt);
    }

    @SuppressWarnings("unchecked")
    private void setCpoUpdApiReqCtacPsnForDelete(NWZC150001PMsg cpoUpdApiReqPMsg, NWAL1500CMsg bizMsg, BigDecimal dsCpoConfigPk) {

        S21SsmEZDResult ssmResult = this.queryForSaveSubmit.getDsCpoCtacPsnPkList(bizMsg, dsCpoConfigPk);
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<BigDecimal> dsCpoCtacPsnPkList = (List<BigDecimal>) ssmResult.getResultObject();

        int vldCnt = cpoUpdApiReqPMsg.cpoCtacPsnInfoList.getValidCount();
        for (BigDecimal dsCpoCtacPsnPk : dsCpoCtacPsnPkList) {
            NWZC150001_cpoCtacPsnInfoListPMsg pMsg = cpoUpdApiReqPMsg.cpoCtacPsnInfoList.no(vldCnt);
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELETE);
            ZYPEZDItemValueSetter.setValue(pMsg.dsCpoCtacPsnPk, dsCpoCtacPsnPk);
            ZYPEZDItemValueSetter.setValue(pMsg.dsCpoConfigPk, dsCpoConfigPk);
            vldCnt++;
        }
        cpoUpdApiReqPMsg.cpoCtacPsnInfoList.setValidCount(vldCnt);
    }
    // S21_NA#15889 Add End

    // 2016/12/21 S21_NA#16392 Add Start
    /**
     * Check Rerationship Return Reason and WH
     * @param bizMsg Business Message
     * @return true: normal end, false: abnornal end
     */
    public boolean checkRelnRsnAndWh(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/31 S21_NA#19808 Mod
        boolean isSuccessFlg = true;
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) { // 2018/01/31 S21_NA#19808 Mod
            NWAL1500_DSMsg lineMsg = glblMsg.D.no(i); // 2018/01/31 S21_NA#19808 Mod

            RTRN_RSNTMsg rtrnRsnTMsg = new RTRN_RSNTMsg();
            ZYPEZDItemValueSetter.setValue(rtrnRsnTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtrnRsnTMsg.rtrnRsnCd, lineMsg.rtrnRsnCd_RL); // 2018/01/31 S21_NA#19808 Mod

            rtrnRsnTMsg = (RTRN_RSNTMsg) S21CacheTBLAccessor.findByKey(rtrnRsnTMsg);
            if (rtrnRsnTMsg == null) {
                lineMsg.rtrnRsnCd_RL.setErrorInfo(1, NWAM0311E, new String[] {"RTRN_RSN", lineMsg.rtrnRsnCd_RL.getValue()}); // 2018/01/31 S21_NA#19808 Mod
                isSuccessFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(lineMsg.rtlWhCd_RL)) { // 2018/01/31 S21_NA#19808 Mod
                continue;
            }

            RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, lineMsg.rtlWhCd_RL); // 2018/01/31 S21_NA#19808 Mod

            rtlWhTMsg = (RTL_WHTMsg) S21CacheTBLAccessor.findByKey(rtlWhTMsg);
            if (rtlWhTMsg == null) {
                lineMsg.rtlWhNm_RL.setErrorInfo(1, NWAM0311E, new String[] {"RTL_WH", lineMsg.rtlWhCd_RL.getValue()}); // 2018/01/31 S21_NA#19808 Mod
                isSuccessFlg = false;
            }
            // START 2023/07/25 K.Chiba [QC#61199, DEL]
//            if (!rtlWhTMsg.invtyOwnrCd.getValue().equals(rtrnRsnTMsg.invtyOwnrCd.getValue())) {
//                lineMsg.rtlWhNm_RL.setErrorInfo(1, NWZM2058E, new String[] {rtrnRsnTMsg.invtyOwnrCd.getValue()}); // 2018/01/31 S21_NA#19808 Mod
//                isSuccessFlg = false;
//            }
            // END 2023/07/25 K.Chiba [QC#61199, DEL]
        }
        return isSuccessFlg;
    }
    // 2016/12/21 S21_NA#16392 Add End

    // 2018/10/25 S21_NA#28897 Add Start
    /**
     * Check Change WH for Pending Return
     * @param bizMsg Business Message
     * @return true: normal end, false: abnornal end
     */
    public boolean checkChangeWhForPendingReturn(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        boolean isSuccessFlg = true;
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);

            if (!LINE_STS_NM_PENDING_RETURN.equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue())
                    && !LINE_STS_NM_BOOKED.equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue())) {
                continue;
            }

            String origRtlWh = null;
            String origRtlSwh = null;
            String curRtlWh = null;
            String curRtlSwh = null;

            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlMsg = new DS_CPO_RTRN_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlMsg.cpoOrdNum, bizMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlMsg.dsCpoRtrnLineNum, rmaLineMsg.cpoDtlLineNum_RL);
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlMsg.dsCpoRtrnLineSubNum, rmaLineMsg.cpoDtlLineSubNum_RL);

            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlRsltMsg = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKey(dsCpoRtrnDtlMsg);
            if (dsCpoRtrnDtlRsltMsg != null) {
                if (ZYPCommonFunc.hasValue(dsCpoRtrnDtlRsltMsg.rtlWhCd)) {
                    origRtlWh = dsCpoRtrnDtlMsg.rtlWhCd.getValue();
                }
                if (ZYPCommonFunc.hasValue(dsCpoRtrnDtlRsltMsg.rtlSwhCd))
                    origRtlSwh = dsCpoRtrnDtlMsg.rtlSwhCd.getValue();
            } else {
                return true;
            }

            curRtlWh = rmaLineMsg.rtlWhCd_RL.getValue();
            curRtlSwh = rmaLineMsg.rtlSwhCd_RL.getValue();

            boolean isModRtlWh = !S21StringUtil.isEquals(origRtlWh, curRtlWh);
            boolean isModRtlSwh = !S21StringUtil.isEquals(origRtlSwh, curRtlSwh);

            if (isModRtlWh || isModRtlSwh) {
                S21SsmEZDResult ssmRslt = this.queryForSaveSubmit.checkChangeWhForPendingReturn(bizMsg, rmaLineMsg);
                if (ssmRslt.isCodeNormal()) {
                    if (isModRtlWh) {
                        rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, NWZM2290E);
                    }
                    if (isModRtlSwh) {
                        rmaLineMsg.rtlSwhNm_RL.setErrorInfo(1, NWZM2290E);
                    }
                    isSuccessFlg = false;
                }
            }
        }
        return isSuccessFlg;
    }
    // 2018/10/25 S21_NA#28897 Add End
    // 2019/10/03 QC#53595 Add Start
    /**
     * Check SalesRep assign to correct IS Order 
     * @param bizMsg Business Message
     * @return true: normal end, false: abnornal end
     */
    public boolean checkISOrderBindToISGroup(NWAL1500CMsg bizMsg) {
        boolean isOkFlg = true;
        if("IS WEB".equals(bizMsg.cpoSrcTpDescTxt.getValue())){
            return isOkFlg;
        }
        
        BigDecimal count = NWAL1500Query.getInstance().cntOrdTpCd(bizMsg);
        if(count.compareTo(BigDecimal.ZERO) == 0){
            return isOkFlg;
        }

        count = NWAL1500Query.getInstance().cntAssignedISGroup(bizMsg);
        if (count.compareTo(BigDecimal.ZERO) == 0) {
            isOkFlg = false;
            return isOkFlg;
        }
        return isOkFlg;
    }
    // 2019/10/03 QC#53595 Add End

    // 2017/04/25 S21_NA#Review structure Lv.2 Add Start
    /**
     * Call DS CPO Update API for order copy process
     * @param bizMsg Business Message
     */
    public void callDsCpoUpdateApiForOrderCopy(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/31 S21_NA#19808 Mod

        NWZC150001PMsg cpoUpdApiReqPMsg = new NWZC150001PMsg();

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPopPrm_P3.getValue())) {
            setCpoUpdApiReqPMsg(NWZC150001Constant.MODE_COPY_HEADER, cpoUpdApiReqPMsg, bizMsg, glblMsg, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N); // 2018/01/31 S21_NA#19808
        // Add Start 2017/09/21 S21_NA#18859
        } else if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPopPrm_P5.getValue())) {
            setCpoUpdApiReqPMsg(NWZC150001Constant.MODE_COPY_ALL_RETURN, cpoUpdApiReqPMsg, bizMsg, glblMsg, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N); // 2018/01/31 S21_NA#19808
        // Add End 2017/09/21 S21_NA#18859
        } else {
            setCpoUpdApiReqPMsg(NWZC150001Constant.MODE_COPY_ALL, cpoUpdApiReqPMsg, bizMsg, glblMsg, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N); // 2018/01/31 S21_NA#19808
        }

        // 2017/06/30 S21_NA#19266 Add Start
        ZYPEZDItemValueSetter.setValue(cpoUpdApiReqPMsg.slsDt, bizMsg.slsDt);
        // 2017/06/30 S21_NA#19266 Add End

        // Add Start 2019/05/29 QC#50405
        NWXC150001SalesRep.updateToLatestSalesRep(cpoUpdApiReqPMsg);
        // Add End 2019/05/29 QC#50405

        callApi(bizMsg, glblMsg, cpoUpdApiReqPMsg, true);
    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Add End

    // 2017/05/08 S21_NA#Review structure Lv.2 Add Start
    /**
     * Call DS CPO Update API for order modification process
     * @param bizMsg Business Message
     */
    public void callDsCpoUpdateApiForChngOrdModify(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/31 S21_NA#19808 Mod

        NWZC150001PMsg cpoUpdApiReqPMsg = new NWZC150001PMsg();

        setCpoUpdApiReqPMsg(NWZC150001Constant.MODE_CHANGE_MODIFICATION, cpoUpdApiReqPMsg, bizMsg, glblMsg, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N); // 2018/01/31 S21_NA#19808
        ZYPEZDItemValueSetter.setValue(cpoUpdApiReqPMsg.xxBizProcCmntTxt, bizMsg.xxPopPrm_P2.getValue());

        callApi(bizMsg, glblMsg, cpoUpdApiReqPMsg, true); // 2018/01/31 S21_NA#19808 Mod
    }
    // 2017/05/08 S21_NA#Review structure Lv.2 Add End

    // 2017/04/25 S21_NA#Review structure Lv.2 Add Start
    private boolean isOnlyHeaderSaving(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWZC150001PMsg cpoUpdApiReqPMsg, boolean isHdrSaved) {

        boolean isNewOrd = S21StringUtil.isEmpty(bizMsg.cpoOrdNum.getValue());
        boolean isApiModeSave = S21StringUtil.isEquals(NWZC150001Constant.MODE_SAVE, cpoUpdApiReqPMsg.xxModeCd.getValue());
        boolean isNoDtl = glblMsg.B.getValidCount() == 0 && glblMsg.D.getValidCount() == 0;

        if (isNoDtl && isApiModeSave) {
            if (isNewOrd || isHdrSaved) {
                return true;
            }
        }
        return false;
    }
    // 2017/04/25 S21_NA#Review structure Lv.2 Add End

    // Add Start 2017/10/05 QC#21356
    private String getOpeMdseCd(NWAL1500_BSMsg lineMsg, String glblCmpyCd, String cpoOrdNum) { // 2018/01/31 S21_NA#19808 Mod

        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        // 2018/06/21 S21_NA#25151 Mod Start
//        cpoDtlTMsg.setSQLID("502");
//        cpoDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        cpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
//        cpoDtlTMsg.setConditionValue("dsOrdPosnNum01", lineMsg.dsOrdPosnNum_LL.getValue());
//
//        CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) EZDTBLAccessor.findByCondition(cpoDtlTMsg);
//
//        String inptMdseCd = lineMsg.mdseCd_LL.getValue();
//        if (cpoDtlTMsgArray != null && cpoDtlTMsgArray.getValidCount() > 0) {
//
//            String lineNum = lineMsg.cpoDtlLineNum_LL.getValue();
//            String lineSubNum = lineMsg.cpoDtlLineSubNum_LL.getValue();
//            String checkMdseCd = NWAL1500CommonLogic.getOrdTakeMdseCd(glblCmpyCd, inptMdseCd);
//            for (int i = 0; i < cpoDtlTMsgArray.getValidCount(); i++) {
//                String oriLineNum = cpoDtlTMsgArray.no(i).cpoDtlLineNum.getValue();
//                String oriLineSubNum = cpoDtlTMsgArray.no(i).cpoDtlLineSubNum.getValue();
//                String oriMdseCd = cpoDtlTMsgArray.no(i).mdseCd.getValue();
//
//                if (lineNum.equals(oriLineNum) && lineSubNum.equals(oriLineSubNum) //
//                        && checkMdseCd.equals(NWAL1500CommonLogic.getOrdTakeMdseCd(glblCmpyCd, oriMdseCd))) {
//                    return oriMdseCd;
//                }
//            }
//        }

        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, lineMsg.cpoDtlLineNum_LL);
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, lineMsg.cpoDtlLineSubNum_LL);

        cpoDtlTMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(cpoDtlTMsg);
        String inptMdseCd = lineMsg.mdseCd_LL.getValue();
        if (cpoDtlTMsg != null) {
            String checkMdseCd = NWAL1500CommonLogic.getOrdTakeMdseCd(glblCmpyCd, inptMdseCd);

            String oriMdseCd = cpoDtlTMsg.mdseCd.getValue();

            if (checkMdseCd.equals(NWAL1500CommonLogic.getOrdTakeMdseCd(glblCmpyCd, oriMdseCd))) {
                return oriMdseCd;
            }
        }
        // 2018/06/21 S21_NA#25151 Mod End
        return inptMdseCd;
    }
    // Add End 2017/10/05 QC#21356

    // 2017/12/07 S21_NA#22794 Add Start
    private boolean isSameMdseCd(String mdseCdOrig, String mdseCdComp) {

        if (S21StringUtil.isEquals(mdseCdOrig, mdseCdComp)) {
            return true;
        }
        if (mdseCdOrig != null && mdseCdComp != null) {
            if (mdseCdOrig.startsWith(mdseCdComp)) {
                return true;
            }
            if (mdseCdComp.startsWith(mdseCdOrig)) {
                return true;
            }
        }
        return false; 
    }
    // 2017/12/07 S21_NA#22794 Add End

    // 2018/03/14 S21_NA#24117-1 add start
    public void setSoftModelParentDtl(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg confMsg = glblMsg.A.no(i);

            if (ZYPCommonFunc.hasValue(confMsg.mdlId_LC)) {
                continue;
            }

            int baseCompIndex = NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, glblMsg, confMsg);
            if (baseCompIndex < 0 //
                    && NWXC150001DsCheck.isModelSoftware(confMsg.svcMdlTpCd_LC.getValue())
                    && ZYPCommonFunc.hasValue(confMsg.prntMdseCd_LC)) { 
                NWAL1500CommonLogicForLineConfig.setSoftModelParent(bizMsg, glblMsg, i, confMsg.prntMdseCd_LC.getValue(), true);
                //NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, i);
            }
        }
    }
    // 2018/03/14 S21_NA#24117-1 add end
    
    // 2018/08/02 S21_NA#26665 add start
    public String getCpoSrcTpCd(String cpoSrcTpDescTxt, String glblCmpyCd) {
        CPO_SRC_TPTMsg tMsg = new CPO_SRC_TPTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("cpoSrcTpDescTxt01", cpoSrcTpDescTxt);
        CPO_SRC_TPTMsgArray tMsgArray= (CPO_SRC_TPTMsgArray)EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0).cpoSrcTpCd.getValue();
        }
        return null;
    }
    // 2018/08/02 S21_NA#26665 add end

    // 2018/08/09 S21_NA#26912 Add Start
    private void copyCsmpData(NWAL1500SMsg glblMsg) {

        for (int cnfIdx = 0; cnfIdx < glblMsg.A.getValidCount(); cnfIdx++) {
            NWAL1500_ASMsg configMsg = glblMsg.A.no(cnfIdx);
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
                if (S21StringUtil.isEquals(configMsg.dsOrdPosnNum_LC.getValue(), lineMsg.dsOrdPosnNum_LL.getValue())) {
                    if (ZYPCommonFunc.hasValue(configMsg.csmpContrNum_LC)) {
                        lineMsg.csmpContrNum_LL.setValue(configMsg.csmpContrNum_LC.getValue());
                    } else {
                        lineMsg.csmpContrNum_LL.clear();
                    }
                    if (ZYPCommonFunc.hasValue(configMsg.dlrRefNum_LC)) {
                        lineMsg.dlrRefNum_LL.setValue(configMsg.dlrRefNum_LC.getValue());
                    } else {
                        lineMsg.dlrRefNum_LL.clear();
                    }
                }
            }
        }

        for (int cnfIdx = 0; cnfIdx < glblMsg.C.getValidCount(); cnfIdx++) {
            NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(cnfIdx);
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);
                if (S21StringUtil.isEquals(rmaConfigMsg.dsOrdPosnNum_RC.getValue(), rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                    if (ZYPCommonFunc.hasValue(rmaConfigMsg.csmpContrNum_RC)) {
                        rmaLineMsg.csmpContrNum_RL.setValue(rmaConfigMsg.csmpContrNum_RC.getValue());
                    } else {
                        rmaLineMsg.csmpContrNum_RL.clear();
                    }
                    if (ZYPCommonFunc.hasValue(rmaConfigMsg.dlrRefNum_RC)) {
                        rmaLineMsg.dlrRefNum_RL.setValue(rmaConfigMsg.dlrRefNum_RC.getValue());
                    } else {
                        rmaLineMsg.dlrRefNum_RL.clear();
                    }
                }
            }
        }
    }
    // 2018/08/09 S21_NA#26912 Add End

    // Add Start 2018/11/02 QC#29017
    /**
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     * @return boolean
     */
    private boolean isRmaSerialNumberExistingError(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        boolean isError = false;

        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(i);
            String configTpCd = rmaConfigMsg.configTpCd_RC.getValue();

            if (!CONFIG_TP.RETURN_EXISTING_IB.equals(configTpCd)) {
                continue;
            }
            // QC#29138 2018/11/14 Add Start
            if (!ZYPCommonFunc.hasValue(rmaConfigMsg.svcConfigMstrPk_RC)) {
                continue;
            }
            // QC#29138 2018/11/14 Add End
            

            S21SsmEZDResult ssmRslt = queryForSaveSubmit.getSvcMachMstr(bizMsg.glblCmpyCd.getValue(), rmaConfigMsg.svcConfigMstrPk_RC.getValue());

            if (ssmRslt.isCodeNormal()) {
                List<Map<String, Object>> svcMachMstrList = (List<Map<String, Object>>) ssmRslt.getResultObject();
                List<NWAL1500_DSMsg> rmaLineMsgList = getConfigRmaLineList(glblMsg, rmaConfigMsg.dsOrdPosnNum_RC.getValue());

                for (NWAL1500_DSMsg rmaLineMsg : rmaLineMsgList) {
                    String mdseCd = rmaLineMsg.mdseCd_RL.getValue();
                    String serNum = rmaLineMsg.serNum_RL.getValue();
                    String ordLineStsCd = rmaLineMsg.ordLineStsCd_RL.getValue();

                    if (!ZYPCommonFunc.hasValue(serNum)) {
                        continue;
                    }

                    // 2019/08/08 S21_NA#52472 Mod Start
//                    if (RTRN_LINE_STS.CANCELLED.equals(ordLineStsCd) //
//                            || RTRN_LINE_STS.CLOSED.equals(ordLineStsCd)) {
                    if (RTRN_LINE_STS.CANCELLED.equals(ordLineStsCd) //
                            || RTRN_LINE_STS.CLOSED.equals(ordLineStsCd)
                            || RTRN_LINE_STS.RECEIVED.equals(ordLineStsCd)) {
                        continue;
                    }
                    // 2019/08/08 S21_NA#52472 Mod Start

                    boolean existFlg = false;
                    for (Map<String, Object> svcMachMstrInfo : svcMachMstrList) {
                        String svcMachMstrMdseCd = (String) svcMachMstrInfo.get("MDSE_CD");
                        String svcMachMstrSerNum = (String) svcMachMstrInfo.get("SER_NUM");

                        if (mdseCd.equals(svcMachMstrMdseCd) && serNum.equals(svcMachMstrSerNum)) {
                            existFlg = true;
                            break;
                        }
                    }

                    if (!existFlg) {
                        rmaLineMsg.mdseCd_RL.setErrorInfo(1, NWZM2293E);
                        rmaLineMsg.serNum_RL.setErrorInfo(1, NWZM2293E);
                        isError = true;
                    }
                }
            }
        }

        return isError;
    }
    // Add End 2018/11/02 QC#29017
    // QC#29693 2019/01/08 Add Start
    private DS_ORD_LINE_CATGTMsg getDsOrdLineCatg(String glblCmpyCd, String dsOrdLineCatgCd) {
        DS_ORD_LINE_CATGTMsg inTMsg = new DS_ORD_LINE_CATGTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.dsOrdLineCatgCd.setValue(dsOrdLineCatgCd);
        DS_ORD_LINE_CATGTMsg outTMsg = (DS_ORD_LINE_CATGTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }
    // QC#29693 2019/01/08 Add End

    // 2019/08/20 S21_NA#52619 Add Start
    private boolean isCancelConfigConfigTab(NWAL1500CMsg bizMsg, NWAL1500_ASMsg configMsg) {

        String screenAplID = bizMsg.getScreenAplID();
        
        if (!"NWAL1500_NWAL2000".equals(screenAplID)) {
            return false;
        }

        if (ZYPConstant.FLG_ON_Y.equals(configMsg.xxChkBox_LC.getValue())) {
            return true;
        }
        return false;
    }
    // 2019/08/20 S21_NA#52619 Add End

    // 2019/08/31 S21_NA#53179 Add Start
    private boolean isExcludeAutoAddRma(NWAL1500CMsg bizMsg) {

        return NWAL1500QueryForLineConfig.getInstance().isExistOrdCatg(bizMsg, ORD_CATG_CTX_TP.EXCL_ORD_CATG_FRM_ADD_RMA_TRGT, false);
    }
    // 2019/08/31 S21_NA#53179 Add End

    // 2019/11/11 S21_NA#54509-1 Mod Start
    private CPO_DTLTMsg getCpoDtlTMsg(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        CPO_DTLTMsg TMsg = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(TMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(TMsg.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(TMsg.cpoDtlLineNum, cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(TMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
        return (CPO_DTLTMsg) S21CacheTBLAccessor.findByKey(TMsg);

    }
    // 2019/11/11 S21_NA#54509-1 Mod End

    // Add Start 2019/11/15 QC#54515
    private boolean checkMultipleIBItem(String glblCmpyCd, NWAL1500_DSMsg rmaLineMsg, NWAL1500_CSMsgArray rmaConfigMsgArray) {

        String dsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
        BigDecimal svcConfigMstrPk = null;

        for (int i = 0; i < rmaConfigMsgArray.getValidCount(); i++) {

            NWAL1500_CSMsg rmaConfigMsg = rmaConfigMsgArray.no(i);
            if (S21StringUtil.isEquals(dsOrdPosnNum, rmaConfigMsg.dsOrdPosnNum_RC.getValue())) {

                if (ZYPCommonFunc.hasValue(rmaConfigMsg.svcConfigMstrPk_RC)) {
                    svcConfigMstrPk = rmaConfigMsg.svcConfigMstrPk_RC.getValue();
                    break;
                } else {
                    return false;
                }
            }
        }

        S21SsmEZDResult ssmRslt = ssmRslt = this.queryForSaveSubmit.checkMultipleIBItem(glblCmpyCd, svcConfigMstrPk, rmaLineMsg);
        List<BigDecimal> multIBItemCntList = (List<BigDecimal>) ssmRslt.getResultObject();
        BigDecimal multIBItemCnt = multIBItemCntList.get(0);

        if (multIBItemCnt.compareTo(BigDecimal.ZERO) > 0 && rmaLineMsg.ordQty_RL.getValue().compareTo(BigDecimal.valueOf(-1)) < 0) {
            return true;
        } else {
            return false;
        }

    }
    // Add End 2019/11/15 QC#54515

    // 2019/12/13 QC#54230 Add Start
    /**
     * isShipToChgToConfig
     * @param msgId String
     * @param confMsg NWAL1500_ASMsg
     */
    private boolean isShipToChgToConfig(NWAL1500_ASMsg confMsg) {

        if (!S21StringUtil.isEquals(confMsg.shipToCustAcctCd_LC.getValue(), confMsg.shipToCustAcctCd_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToCustCd_LC.getValue(), confMsg.shipToCustCd_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.dropShipFlg_LC.getValue(), confMsg.dropShipFlg_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToLocNm_LC.getValue(), confMsg.shipToLocNm_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToAddlLocNm_LC.getValue(), confMsg.shipToAddlLocNm_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToFirstLineAddr_LC.getValue(), confMsg.shipToFirstLineAddr_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToScdLineAddr_LC.getValue(), confMsg.shipToScdLineAddr_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToThirdLineAddr_LC.getValue(), confMsg.shipToThirdLineAddr_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToFrthLineAddr_LC.getValue(), confMsg.shipToFrthLineAddr_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToFirstRefCmntTxt_LC.getValue(), confMsg.shipToFirstRefCmntTxt_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToScdRefCmntTxt_LC.getValue(), confMsg.shipToScdRefCmntTxt_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToCtyAddr_LC.getValue(), confMsg.shipToCtyAddr_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToStCd_LC.getValue(), confMsg.shipToStCd_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToProvNm_LC.getValue(), confMsg.shipToProvNm_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToCntyNm_LC.getValue(), confMsg.shipToCntyNm_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToPostCd_LC.getValue(), confMsg.shipToPostCd_DB.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(confMsg.shipToCtryCd_LC.getValue(), confMsg.shipToCtryCd_DB.getValue())) {
            return true;
        }

        return false;
    }
    // 2019/12/13 QC#54230 Add End
    
    // Add Start 2020/01/17 QC#55202
    /**
     * check Invalid Drop Vendor Return.
     * @param glblCmpyCd glblCmpyCd
     * @param String mdseCd
     * @param String returnWH
     * @return true: This RMA line has error.
     */
    public boolean checkInvalidDropVendorReturn(String glblCmpyCd, NWAL1500SMsg glblMsg, String mdseCd, String returnWH) {
        if(!checkIsDefaultWH(glblCmpyCd, returnWH)){
            return false;
        }
        if(!checkHasDefaultWH(glblCmpyCd, mdseCd)){
            return false;
        }
        return true;
    }
    /**
     * check Is Default WH.
     * @param glblCmpyCd glblCmpyCd
     * @param String returnWH
     * @return true: Default WH
     */
    private boolean checkIsDefaultWH(String glblCmpyCd, String returnWH) {
        S21SsmEZDResult ssmRslt  = this.queryForSaveSubmit.checkIsDefaultWH(glblCmpyCd, returnWH);
        List<BigDecimal> checkResultList = (List<BigDecimal>) ssmRslt.getResultObject();
        BigDecimal resultCnt = checkResultList.get(0);
        if (resultCnt.compareTo(BigDecimal.ZERO) == 0){
            return false;
        }else{
            return true;
        }
    }    /**
     * check Is Default WH.
     * @param glblCmpyCd glblCmpyCd
     * @param String returnWH
     * @return true: Default WH
     */
    private boolean checkHasDefaultWH(String glblCmpyCd, String mdseCd) {
        S21SsmEZDResult ssmRslt  = this.queryForSaveSubmit.checkHasDefaultWH(glblCmpyCd, mdseCd);
        List<BigDecimal> checkResultList = (List<BigDecimal>) ssmRslt.getResultObject();
        BigDecimal resultCnt = checkResultList.get(0);
        if (resultCnt.compareTo(BigDecimal.ZERO) == 0){
            return false;
        }else{
            return true;
        }
    }
    // Add End   2020/01/17 QC#55202
    
    // QC#55986 2020/03/03 Add Start
    private String convertMdseCd(String glblCmpyCd, String mdseCd){
        if(mdseCd == null){
            return null;
        }
        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if(mdseTMsg == null){
            return mdseCd;
        }
        return mdseTMsg.mdseCd.getValue();
    } 
    // QC#55986 2020/03/03 Add End

    // ADD START 2022/08/19 H.Watanabe [QC#60255]
    /**
     * checkCarrAcctNumValidation
     * @param cMsg
     * @return true : no error / false : error
     */
    public boolean checkCarrAcctNumValidation(NWAL1500CMsg bizMsg) {
        if (!NWXC150001DsCheck.chkCarrierAccountNumberNeedValidation(bizMsg.glblCmpyCd.getValue(), bizMsg.carrAcctNum.getValue(), bizMsg.shpgSvcLvlCd.getValue(), bizMsg.carrSvcLvlCd.getValue())) {
            bizMsg.carrAcctNum.setErrorInfo(1, NWAM8465E, new String[] {"Carrier Acct Num" });
            return false;
        }
        return true;
    }
    // ADD END   2022/08/19 H.Watanabe [QC#60255]

    // 2023/11/06 QC#61924 Add Start
    /**
     * hasDeactivateAccountOrLocation
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     * @return boolean
     */
    public boolean hasDeactivateAccountOrLocation(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            return true;
        }

        boolean errorFlg = false;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            BILL_TO_CUSTTMsg lcBillToCustTMsg = new BILL_TO_CUSTTMsg();
            lcBillToCustTMsg = getBillToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(i).billToCustCd_LC.getValue());
            if (null != lcBillToCustTMsg) {
                if (ZYPConstant.FLG_ON_Y.equals(lcBillToCustTMsg.deacNewTrxFlg.getValue())) {
                    bizMsg.A.no(i).billToCustCd_LC.setErrorInfo(1, NWAM0987E, new String[] {bizMsg.A.no(i).billToCustCd_LC.getValue() });
                    errorFlg = true;
                }
            }

            SHIP_TO_CUSTTMsg lcShipToCustTMsg = new SHIP_TO_CUSTTMsg();
            lcShipToCustTMsg = getShipToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(i).shipToCustCd_LC.getValue());
            if (null != lcShipToCustTMsg) {
                if (ZYPConstant.FLG_ON_Y.equals(lcShipToCustTMsg.deacNewTrxFlg.getValue())) {
                    bizMsg.A.no(i).shipToCustCd_LC.setErrorInfo(1, NWAM0988E, new String[] {bizMsg.A.no(i).shipToCustCd_LC.getValue() });
                    errorFlg = true;
                }
            }
        }

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            BILL_TO_CUSTTMsg lcBillToCustTMsg = new BILL_TO_CUSTTMsg();
            lcBillToCustTMsg = getBillToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.C.no(i).billToCustCd_RC.getValue());
            if (null != lcBillToCustTMsg) {
                if (ZYPConstant.FLG_ON_Y.equals(lcBillToCustTMsg.deacNewTrxFlg.getValue())) {
                    bizMsg.C.no(i).billToCustCd_RC.setErrorInfo(1, NWAM0987E, new String[] {bizMsg.C.no(i).billToCustCd_RC.getValue() });
                    errorFlg = true;
                }
            }

            SHIP_TO_CUSTTMsg lcShipToCustTMsg = new SHIP_TO_CUSTTMsg();
            lcShipToCustTMsg = getShipToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.C.no(i).shipToCustCd_RC.getValue());
            if (null != lcShipToCustTMsg) {
                if (ZYPConstant.FLG_ON_Y.equals(lcShipToCustTMsg.deacNewTrxFlg.getValue())) {
                    bizMsg.C.no(i).shipToCustCd_RC.setErrorInfo(1, NWAM0988E, new String[] {bizMsg.C.no(i).shipToCustCd_RC.getValue() });
                    errorFlg = true;
                }
            }
        }

        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        billToCustTMsg = getBillToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustCd.getValue());
        if (null != billToCustTMsg) {
            if (ZYPConstant.FLG_ON_Y.equals(billToCustTMsg.deacNewTrxFlg.getValue())) {
                bizMsg.billToCustCd.setErrorInfo(1, NWAM0987E, new String[] {bizMsg.billToCustCd.getValue() });
                errorFlg = true;
            }
        }

        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        shipToCustTMsg = getShipToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.shipToCustCd.getValue());
        if (null != shipToCustTMsg) {
            if (ZYPConstant.FLG_ON_Y.equals(shipToCustTMsg.deacNewTrxFlg.getValue())) {
                bizMsg.shipToCustCd.setErrorInfo(1, NWAM0988E, new String[] {bizMsg.shipToCustCd.getValue() });
                errorFlg = true;
            }
        }

        BILL_TO_CUSTTMsg soldToCustTMsg = new BILL_TO_CUSTTMsg();
        soldToCustTMsg = getBillToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.soldToCustLocCd.getValue());
        if (null != soldToCustTMsg) {
            if (ZYPConstant.FLG_ON_Y.equals(soldToCustTMsg.deacNewTrxFlg.getValue())) {
                bizMsg.soldToCustLocCd.setErrorInfo(1, NWAM0989E, new String[] {bizMsg.soldToCustLocCd.getValue() });
                errorFlg = true;
            }
        }

        if (errorFlg) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * <pre>
     * get Bill To Customer
     * @param glblCmpyCd Global Company Code
     * @param billToCustCd Bill To Customer Code
     * @return BILL_TO_CUSTTMsg, if system can't find BILL_TO_CUST record, return null
     * </pre>
     */
    private BILL_TO_CUSTTMsg getBillToCustByCondition(String glblCmpyCd, String billToCustCd) {

        BILL_TO_CUSTTMsg condition = new BILL_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("billToCustCd01", billToCustCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        BILL_TO_CUSTTMsgArray tmsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * <pre>
     * get Ship To Customer
     * @param glblCmpyCd Global Company Code
     * @param shipToCustCd Ship To Customer Code
     * @return SHIP_TO_CUSTTMsg, if system can't find SHIP_TO_CUST record, return null
     * </pre>
     */
    private SHIP_TO_CUSTTMsg getShipToCustByCondition(String glblCmpyCd, String shipToCustCd) {

        SHIP_TO_CUSTTMsg condition = new SHIP_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("shipToCustCd01", shipToCustCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        SHIP_TO_CUSTTMsgArray tmsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0);
        } else {
            return null;
        }
    }
    // 2023/11/06 QC#61924 Add End
}
