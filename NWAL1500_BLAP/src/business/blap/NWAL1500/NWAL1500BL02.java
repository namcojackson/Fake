/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500;

import static business.blap.NWAL1500.constant.NWAL1500Constant.BILL_EVENT_LIST;
import static business.blap.NWAL1500.constant.NWAL1500Constant.DEF_LSE_BO_LOC_ADDR;
import static business.blap.NWAL1500.constant.NWAL1500Constant.DEF_LSE_BO_LOC_CITY_ADDR;
import static business.blap.NWAL1500.constant.NWAL1500Constant.DEF_LSE_BO_LOC_NM;
import static business.blap.NWAL1500.constant.NWAL1500Constant.DEF_LSE_BO_LOC_POST_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.DEF_LSE_BO_LOC_ST_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.DROP_SHIP_WH;
import static business.blap.NWAL1500.constant.NWAL1500Constant.DS_DROP_SHIP_WH_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.ENTER;
import static business.blap.NWAL1500.constant.NWAL1500Constant.HEADER_STS_NM_CANCELLED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.HEADER_STS_NM_CLOSED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LEASE_BYOT_MDSE_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_CANCELLED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_CLOSED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NLCL1000_DROP_SHIP_RTL_WH_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NOT_ALLOC_WH_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NO_SLCT_DTL;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1500_CONFIG_LINE_LIMIT_CNT;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1500_LINE_LIMIT_CNT;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1500_ORD_LINE_CANCELLED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1500_RENTAL_CONV_DEFWH;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1660_MODE_REFERENCE;
import static business.blap.NWAL1500.constant.NWAL1500Constant.ORDER_LINE_FILTER_STEP_2;
import static business.blap.NWAL1500.constant.NWAL1500Constant.ORD_LINE_STS_TO_BE_CANCELLED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.PKG_UOM_FOR_PRC;
import static business.blap.NWAL1500.constant.NWAL1500Constant.RCV_SER_TAKE_FLG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.RTL_WH_CATG_VIRTUAL_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.SERVICE_EXCHANGE_RTRN_RSN_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.SHIP_EVENT_LIST;
import static business.blap.NWAL1500.constant.NWAL1500Constant.SOLD_EVENT_LIST;
import static business.blap.NWAL1500.constant.NWAL1500Constant.SHPG_SER_TAKE_FLG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.SPACE;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_ADDL_HEADER;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_HEADER;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_BILL_TO_LOC;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_BILL_TO_NM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_BILL_TO_NUM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CATG;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CMPT_LINE;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_COMP_LINE;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_FRT_TERMS;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_ITEM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_LINE;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_MAIN_UNIT;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_PRC_CHG;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_RSN;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SBST_ITEM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SHIP_TO_NM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SHIP_TO_NUM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SLS_REP_CD;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SLS_REP_NM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SOLD_TO_LOC;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SOLD_TO_NM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SOLD_TO_NUM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_WH;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0036E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0037E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0100E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0136I;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0181E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0504E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0626E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0666E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0667E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0682E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0683E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0688E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0694E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0766E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0771I;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0772E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0781E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0871E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0872E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0907E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0910E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0914E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0952E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0986E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NZZM0000E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.ZZM8100I;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import business.blap.NWAL1500.common.NWAL1500CommonLogic;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForAddlHeader;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForAutoAdd;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForCategory;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForConfigId;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForCopy;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForCustomer;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForDownload;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForInit;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForLineCancel;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForLineConfig;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForLineControl;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForMeterEntry;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForMultiWin;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForPagination;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForPriceChanges;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForPricing;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForSalesCredit;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForSaveSubmit;
import business.blap.NWAL1500.common.NWAL1500CommonLogicForSpecialInstruction;
import business.blap.NWAL1500.constant.NWAL1500Constant;
import business.blap.NWAL1500.constant.NWAL1500MsgConstant;
import business.blap.NWAL1610.constant.NWAL1610Constant;
import business.blap.NWAL1620.constant.NWAL1620Constant;
import business.db.CCYTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_SRC_TPTMsg;
import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.PRC_CATGTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.parts.NLZC215001PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC181001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs.NWZC150001CpouInsBizProcLog;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001CommonReturnReason;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_ENTRY_ACT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/24   Fujitsu         S.Takami        Create          N/A
 * 2015/09/24   Fujitsu         T.Yoshida       Create          N/A
 * 2015/12/16   Fujitsu         Y.Kanefusa      Update          #2013
 * 2016/01/04   Fujitsu         T.Ishii         Update          #955
 * 2016/02/09   Fujitsu         S.Takami        Update          S21_NA#3398
 * 2016/02/09   Fujitsu         M.Hara          Update          QC#2478
 * 2016/02/10   Fujitsu         S.Takami        Update          QC#3398 Reopen
 * 2016/02/15   Fujitsu         T.Murai         Update          QC#2176
 * 2016/02/15   Fujitsu         Y.Taoka         Update          QC#1694
 * 2016/02/19   Fujitsu         S.Takami        Update          QC#2166 (Move out doProcess_NWAL1500_NWAL2000() to 06)
 *                                                              Move doProcess_NWAL1500Scrn00_OpenWin_LineCancel() to
 *                                                              NWAL1500CommonLogicForLineCancel
 * 2016/02/20   Fujitsu         S.Takami        Update          QC#1840 (Collapse not data delete from B, D)
 * 2016/02/23   Fujitsu         S.Takami        Update          S21_NA#1859
 * 2016/02/23   Fujitsu         S.Ohki          Update          QC#1866
 * 2016/02/24   Fujitsu         M.Hara          Update          QC#4078
 * 2016/02/24   Fujitsu         S.Takami        Update          S21_NA#4033
 * 2016/02/24   Fujitsu         K.Sato          Update          S21_NA#3239
 * 2016/02/26   Fujitsu         K.Sato          Update          S21_NA#1729
 * 2016/02/29   Fujitsu         M.Nakamura      Update          S21_NA#1693/1700
 * 2016/03/03   Fujitsu         S.Ohki          Update          S21_NA#5000
 * 2016/03/05   Fujitsu         T.Ishii         Update          S21_NA#5000#80
 * 2016/03/05   Fujitsu         S.Takami        Update          S21_NA#5000#85
 * 2016/03/07   Fujitsu         M.Nakamura      Update          S21_NA#5000#78
 * 2016/03/15   Fujitsu         S.Takami        Update          S21_NA#2096 Reopen, S21_NA#4691
 * 2016/03/25   Fujitsu         S.Takami        Update          S21_NA#4693
 * 2016/03/30   Fujitsu         S.Takami        Update          S21_NA#5523
 * 2016/04/01   Fujitsu         S.Takami        Update          S21_NA#5523-2
 * 2016/04/05   Fujitsu         T.Ishii         Update          S21_NA#6306
 * 2016/04/08   Fujitsu         S.Takami        Update          S21_NA#5356
 * 2016/04/20   Fujitsu         S.Takami        Update          S21_NA#5605
 * 2016/04/26   Fujitsu         M.Yamada        Update          S21_NA#6312
 * 2016/04/28   Fujitsu         M.Yamada        Update          S21_NA#6312-2
 * 2016/05/10   Fujitsu         M.Yamada        Update          S21_NA#6148
 * 2016/05/11   Fujitsu         T.Yoshida       Update          S21_NA#8166
 * 2016/05/12   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/05/18   Fujitsu         T.Murai         Update          S21_NA#8317, 6719
 * 2016/05/18   Fujitsu         S.Takami        Update          S21_NA#8144-3
 * 2016/05/19   Fujitsu         M.Hara          Update          S21_NA#8450
 * 2016/05/23   Fujitsu         T.Murai         Update          S21_NA#8317
 * 2016/05/24   Fujitsu         Y.Kanefusa      Update          S21_NA#8267
 * 2016/05/24   Fujitsu         S.Takami        Update          S21_NA#8617
 * 2016/05/25   Fujitsu         T.Murai         Update          S21_NA#7337
 * 2016/05/31   Fujitsu         T.Murai         Update          S21_NA#9104,9189
 * 2016/07/04   Fujitsu         S.Takami        Update          S21_NA#7645
 * 2016/07/05   Fujitsu         T.Yoshida       Update          S21_NA#10321
 * 2016/07/05   Fujitsu         M.Hara          Update          S21_NA#7441
 * 2016/07/11   Fujitsu         S.Takami        Update          S21_NA#7821
 * 2016/07/27   Fujitsu         S.Takami        Update          S21_NA#4691-2
 * 2016/08/01   Fujitsu         S.Takami        Update          S21_NA#4691-3
 * 2016/07/29   Fujitsu         T.Mizuki        Update          S21_NA#12607
 * 2016/08/04   Fujitsu         T.Yoshdia       Update          S21_NA#11619,13026
 * 2016/08/09   Fujitsu         S.Iidaka        Update          S21_NA#11592
 * 2016/08/10   Fujitsu         Y.Taoka         Update          S21_NA#8388
 * 2016/08/23   Fujitsu         S.Takami        Update          S21_NA#13504
 * 2016/08/26   Fujitsu         M.Yamada        Update          S21_NA#10754
 * 2016/08/26   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2016/08/31   Fujitsu         S.Takami        Update          S21_NA#10535
 * 2016/09/05   Fujitsu         S.Takami        Update          S21_NA#6523
 * 2016/09/13   Fujitsu         Y.Kanefusa      Update          S21_NA#14436
 * 2016/09/20   Fujitsu         S.Takami        Update          S21_NA#8279
 * 2016/09/21   Fujitsu         S.Takami        Update          S21_NA#10274
 * 2016/09/26   Fujitsu         Y.Kanefusa      Update          S21_NA#14266
 * 2016/09/28   Fujitsu         S.Takami        Update          S21_NA#8659
 * 2016/09/29   Fujitsu         Y.Taoka         Update          S21_NA#14805
 * 2016/10/05   Fujitsu         S.Takami        Update          S21_NA#7645-3
 * 2016/10/13   Fujitsu         N.Sugiura       Update          S21_NA#7700
 * 2016/10/26   Fujitsu         S.Takami        Update          S21_NA#15587
 * 2016/10/31   Fujitsu         S.Takami        Update          S21_NA#15541
 * 2016/11/08   Fujitsu         S.Iidaka        Update          S21_NA#7749
 * 2016/11/09   Fujitsu         S.Takami        Update          S21_NA#15746
 * 2016/11/10   Fujitsu         S.Takami        Update          S21_NA#15746-2
 * 2016/11/11   Fujitsu         S.Takami        Update          S21_NA#9864-2
 * 2016/11/08   Fujitsu         S.Iidaka        Update          S21_NA#7749-2
 * 2016/11/30   Fujitsu         S.Ohki          Update          S21_NA#16098
 * 2017/01/27   Fujitsu         R.Nakamura      Update          S21_NA#17257
 * 2017/01/31   Fujitsu         R.Nakamura      Update          S21_NA#17186
 * 2017/02/21   Fujitsu         Y.Kanefusa      Update          S21_NA#17474
 * 2017/02/24   Fujitsu         Y.Kanefusa      Update          S21_NA#13688
 * 2017/03/02   Fujitsu         S.Takami        Update          S21_NA#17714-2
 * 2017/03/13   Fujitsu         T.Aoi           Update          S21_NA#16987
 * 2017/07/07   Fujitsu         A.Kosai         Update          S21_NA#19266
 * 2017/07/13   Fujitsu         S.Takami        Update          S21_NA#19912
 * 2017/07/21   Fujitsu         S.Takami        Update          S21_NA#19847
 * 2017/08/07   Fujitsu         T.Ogura         Update          Sol#249
 * 2017/08/09   Fujitsu         W.Honda         Update          S21_NA#20093
 * 2017/08/21   Fujitsu         S.Takami        Update          S21_NA#19917
 * 2017/09/01   Fujitsu         T.Ogura         Update          S21_NA#19749
 * 2017/09/21   Fujitsu         T.Murai         Update          S21_NA#16346(Sel#373)
 * 2017/09/22   Fujitsu         T.Ogura         Update          S21_NA#21336
 * 2017/10/04   Fujitsu         S.Takami        Update          S21_NA#21300
 * 2017/11/02   Fujitsu         T.Aoi           Update          S21_NA#20146(Sol#92)
 * 2017/11/15   Fujitsu         T.Aoi           Update          S21_NA#22604
 * 2017/11/16   Fujitsu         K.Ishizuka      Update          S21_NA#22300
 * 2017/11/17   Fujitsu         A.Kosai         Update          S21_NA#22252
 * 2017/11/21   Fujitsu         A.Kosai         Update          S21_NA#22555
 * 2017/11/28   Fujitsu         Y.Kanefusa      Update          S21_NA#22789
 * 2017/12/01   Fujitsu         A.Kosai         Update          S21_NA#21580
 * 2017/12/07   Fujitsu         A.Kosai         Update          S21_NA#21621
 * 2017/12/26   Fujitsu         S.Takami        Update          S21_NA#22986
 * 2017/12/26   Fujitsu         Y.Kanefusa      Update          S21_NA#22371
 * 2018/01/04   Fujitsu         K.Ishizuka      Update          S21_NA#21503
 * 2018/01/10   Fujitsu         Y.Kanefusa      Update          S21_NA#22372
 * 2018/01/11   Fujitsu         S.Takami        Update          S21_NA#23329
 * 2018/01/26   Fujitsu         K.Ishizuka      Update          S21_NA#23140
 * 2018/01/25   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/01/30   Fujitsu         M.Ohno          Update          S21_NA#23565
 * 2018/02/07   Fujitsu         Y.Kanefusa      Update          S21_NA#24008
 * 2018/03/14   Fujitsu         M.Ohno          Update          S21_NA#24117-1
 * 2018/03/15   Fujitsu         S.Takami        Update          S21_NA#19808-2 (bizMsg.I -> glblMsg.I without any comments)
 * 2018/03/16   Fujitsu         S.Takami        Update          S21_NA#19808-3
 * 2018/03/19   Fujitsu         K.Ishizuka      Update          S21_NA#24459
 * 2018/03/20   Fujitsu         A.Kosai         Update          S21_NA#24840
 * 2018/03/20   Fujitsu         S.Takami        Update          S21_NA#24842
 * 2018/03/27   Fujitsu         S.Takami        Update          S21_NA#25060
 * 2018/03/28   Fujitsu         S.Takami        Update          S21_NA#25102
 * 2018/04/02   Fujitsu         S.Ohki          Update          S21_NA#25179
 * 2018/04/05   Fujitsu         K.Ishizuka      Update          S21_NA#24098
 * 2018/04/11   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * 2018/04/17   Fujitsu         A.Kosai         Update          S21_NA#25230
 * 2018/05/16   Fujitsu         M.Ohno          Update          S21_NA#25144
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/06/07   Fujitsu         Y.Kanefusa      Update          S21_NA#26415
 * 2018/06/29   Fujitsu         S.Takami        Update          S21_NA#27071
 * 2018/07/03   Fujitsu         A.Kosai         Update          S21_NA#26908
 * 2018/07/03   Fujitsu         H.Kumagai       Update          QC#26932
 * 2018/07/05   Fujitsu         M.Ishii         Update          QC#25616
 * 2018/07/12   Fujitsu         S.Takami        Update          S21_NA#26895-2
 * 2018/07/11   Fujitsu         T.Noguchi       Update          S21_NA#26713
 * 2018/07/20   Fujitsu         Mz.Takahashi    Update          S21_NA#26188
 * 2018/07/27   Fujitsu         T.Noguchi       Update          S21_NA#14307
 * 2018/07/31   Fujitsu         Y.Kanefusa      Update          S21_NA#27478
 * 2018/08/01   Fujitsu         M.Ohno          Upadte          S21_NA#26414
 * 2018/08/02   Fujitsu         H.Kumagai       Update          QC#26072
 * 2018/08/02   Fujitsu         M.Ohno          Update          S21_NA#26665
 * 2018/08/03   Fujitsu         K.Ishizuka      Update          S21_NA#27040
 * 2018/08/14   Fujitsu         K.IShizuka      Update          S21_NA#27718
 * 2018/08/21   Fujitsu         S.Takami        Update          S21_NA#27642
 * 2018/08/21   Fujitsu         S.Takami        Update          S21_NA#26767
 * 2018/08/24   Fujitsu         T.Noguchi       Update          S21_NA#27202
 * 2018/08/28   Fujitsu         H.Kumagai       Update          QC#26329
 * 2018/09/20   Fujitsu         S.Takami        Update          S21_NA#28199
 * 2018/10/24   Fujitsu         H.Kumagai       Update          QC#28866
 * 2018/10/25   Fujitsu         T.Aoi           Update          S21_NA#28897
 * 2018/11/26   Fujitsu         Y.Kanefusa      Update          S21_NA#29343
 * 2018/12/12   Fujitsu         S.Kosaka        Update          QC#29315
 * 2018/12/21   Fujitsu         M.Ohno          Update          S21_NA#29315
 * 2019/01/08   Fujitsu         K.Ishizuka      Update          S21_NA#29789
 * 2019/02/22   Fujitsu         K.Ishizuka      Update          S21_NA#30449
 * 2019/04/15   Fujitsu         K.Ishizuka      Update          S21_NA#31184
 * 2019/05/24   Fujitsu         Mz.Takahashi    Update          QC#50043
 * 2019/06/10   Fujitsu         Y.Kanefusa      Update          S21_NA#50776
 * 2019/07/05   Fujitsu         S.Takami        Update          S21_NA#51151
 * 2019/07/11   Fujitsu         T.Noguchi       Update          S21_NA#51287
 * 2019/07/17   Fujitsu         R.Nakamura      Update          S21_NA#51700
 * 2019/07/17   Fujitsu         R.Nakamura      Update          S21_NA#52164
 * 2019/07/18   Fujitsu         K.Kato          Update          S21_NA#51327
 * 2019/07/31   Fujitsu         Y.Kanefusa      Update          S21_NA#52264
 * 2019/08/01   Fujitsu         M.Ohno          Update          S21_NA#52156
 * 2019/09/19   Fujitsu         T.Noguchi       Update          S21_NA#53585
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 * 2019/11/21   Fujitsu         S.Takami        Update          S21_NA#54202
 * 2019/12/13   Fujitsu         S.Iidaka        Update          S21_NA#53013
 * 2019/12/16   Fujitsu         Y.Kanefusa      Update          S21_NA#52416
 * 2020/01/07   Fujitsu         M.Ohno          Update          S21_NA#54996
 * 2020/02/28   Fujitsu         C.Hara          Update          QC#55976
 * 2020/03/16   Fujitsu         C.Hara          Update          QC#56132
 * 2020/04/14   Fujitsu         Y.Kanefusa      Update          S21_NA#56558
 * 2022/05/26   CITS            A.Cullano       Update          QC#60048
 * 2022/08/01   CITS            F.Fadriquela    Update          QC#60273
 * 2022/09/09   CITS            R.Azucena       Update          QC#60548
 * 2023/02/07   Hitachi         S.Fujita        Update          QC#61010
 * 2023/06/06   Hitachi         T.Doi           Update          CSA-QC#61465
 * 2024/02/21   CITS            T.Miki          Update          CSA QC#63577
 * </pre>
 */
public class NWAL1500BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
            NWAL1500SMsg glblMsg = (NWAL1500SMsg) sMsg;

            bizMsg.setCommitSMsg(true); // 2016/02/09 S21_NA#3398

            NWAL1500CommonLogic.clearDeleteActionStatus(bizMsg); // 2016/09/20 S21_NA#8279 Add

            // 2018/01/25 S21_NA#19808 Add Start
            // Save Current Page Data to Global Message
//            copyBizDataToGlobalData(bizMsg, glblMsg);
            // 2018/01/25 S21_NA#19808 Add End

            if ("NWAL1500_INIT".equals(screenAplID)) {
                String bkCpoOrdNum = bizMsg.cpoOrdNum.getValue();
                doProcess_NWAL1500_INIT(bizMsg, glblMsg);
                if (ZYPCommonFunc.hasValue(bkCpoOrdNum)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, bkCpoOrdNum);
                    doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg);
                }
            } else if ("NWAL1500Scrn00_Order_Search".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_CMN_Reset(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_CMN_Save".equals(screenAplID)) {
                String xxDplyTab = bizMsg.xxDplyTab.getValue(); // 2016/02/24 S21_NA#4033 Add
                doProcess_NWAL1500Scrn00_CMN_Save(bizMsg, glblMsg);
                bizMsg.xxDplyTab.setValue(xxDplyTab); // 2016/02/24 S21_NA#4033 Add
            } else if ("NWAL1500Scrn00_CMN_Submit".equals(screenAplID)) {
                String xxDplyTab = bizMsg.xxDplyTab.getValue(); // 2016/02/24 S21_NA#4033 Add
                doProcess_NWAL1500Scrn00_CMN_Submit(bizMsg, glblMsg);
                bizMsg.xxDplyTab.setValue(xxDplyTab); // 2016/02/24 S21_NA#4033 Add
            } else if ("NWAL1500Scrn00_TAB_Header".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_TAB_Header(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_TAB_Addl_Header".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_TAB_Addl_Header(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_TAB_Line_Config".equals(screenAplID)) {
                // ZYPGUITableColumn.getColData(bizMsg, glblMsg, LINE_HDR_ID);// For Performance QC#10321 Del
                doProcess_NWAL1500Scrn00_TAB_Line_Config(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_TAB_RMA".equals(screenAplID)) {
                // ZYPGUITableColumn.getColData(bizMsg, glblMsg, RMA_HDR_ID);// For Performance QC#10321 Del
                doProcess_NWAL1500Scrn00_TAB_RMA(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_Line_Config_Add".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_Line_Config_Add(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_Line_Add".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_Line_Add(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_Line_Collapsed".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_Line_Collapsed(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_Line_Expand".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_Line_Expand(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_Line_All_Collapsed".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_Line_All_Collapsed(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_Line_All_Expand".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_Line_All_Expand(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromCategory".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromCategory(bizMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromReason".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromReason(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromSalesRepCode".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSalesRepCode(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromSalesRepName".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSalesRepName(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromBillToName".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromBillToName(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromBillToAccount".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromBillToAccount(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromBillToLocation".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromBillToLocation(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromShipToName".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromShipToName(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromShipToAccount".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromShipToAccount(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromShipToLocation".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromShipToLocation(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromSoldToName".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSoldToName(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromSoldToAccount".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSoldToAccount(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromSoldToLocation".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSoldToLocation(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromItem".equals(screenAplID)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.srchOrigItemFlg_MF, ZYPConstant.FLG_OFF_N); // S21_NA#9761 ADD
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromItem(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromQty".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromQty(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromSellPrice".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSellPrice(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromFreightTerms".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromFreightTerms(bizMsg);
            } else if ("NWAL1500Scrn00_OnChange_ShpgSvcLvlCd".equals(screenAplID)) { // QC#13688 2017/02/24 Add
                doProcess_NWAL1500Scrn00_OnChange_ShpgSvcLvlCd(bizMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromPaymentTerms".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromPaymentTerms(bizMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromCarrierServiceLevel".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromCarrierServiceLevel(bizMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromCSMPNumber".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromCSMPNumber(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromDealerRefNumber".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromDealerRefNumber(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromAssnProgramName".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromAssnProgramName(bizMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromAssnProgramNumber".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromAssnProgramNumber(bizMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromPriceList".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromPriceList(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromFloorPriceList".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromFloorPriceList(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromSubstituteItem".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSubstituteItem(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromConfigId".equals(screenAplID)) { // 2018/01/04 S21_NA#21503 ADD
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromConfigId(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnBlur_ChangeWH".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_ChangeWH(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_OnBlur_ChangeSubWH".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_ChangeSubWH(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_OnBlur_OrdLogTpNm".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnBlur_OrdLogTpNm(bizMsg);
            } else if ("NWAL1500Scrn00_OnChange_LineCategory".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnChange_LineCategory(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnChange_UOM".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnChange_UOM(bizMsg, glblMsg); // 2018/03/06 S21_NA#19808
            } else if ("NWAL1500Scrn00_OnChange_ReturnReason".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnChange_ReturnReason(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_Calculation_Order_Amount".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_Calculation_Order_Amount(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_Auto_AddSupply".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_Auto_AddSupply(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_Auto_AddRMA".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_Auto_AddRMA(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OpenWin_Copy".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_Copy(bizMsg);
            } else if ("NWAL1500Scrn00_OpenWin_Holds".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_Holds(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OpenWin_DeliveryInfo".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_DeliveryInfo(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OpenWin_Buyout".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_Buyout(bizMsg);
            } else if ("NWAL1500Scrn00_OpenWin_CarrierServiceLevel".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_CarrierServiceLevel(bizMsg);
            } else if ("NWAL1500Scrn00_OpenWin_LineCancel".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_LineCancel(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OpenWin_PriceChanges".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_PriceChanges(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OpenWin_MeterEntry".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_MeterEntry(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OpenWin_MassUpdate".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_MassUpdate(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OpenWin_ChangeOrderModification".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_ChangeOrderModification(bizMsg);
            } else if ("NWAL1500Scrn00_OpenWin_SpecialInstruction".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_SpecialInstruction(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OpenWin_ShipToChange".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_ShipToChange(bizMsg);
            } else if ("NWAL1500Scrn00_OpenWin_SalesCredit".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_SalesCredit(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OpenWin_ConfigID".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_ConfigID(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OpenWin_CopyLine".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_CopyLine(bizMsg);
            } else if ("NWAL1500Scrn00_OpenWin_OrderReason".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_OrderReason(bizMsg);
            } else if ("NWAL1500Scrn00_OpenWin_Salesrep".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_Salesrep(bizMsg);
            } else if ("NWAL1500Scrn00_OpenWin_AssnProgram".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_AssnProgram(bizMsg);
            } else if ("NWAL1500Scrn00_Order_Cancel".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_Order_Cancel(bizMsg, glblMsg);
            } else if ("NWAL1500_NLCL1000".equals(screenAplID)) {
                doProcess_NWAL1500_NLCL1000(bizMsg);
            } else if ("NWAL1500_NMAL6050".equals(screenAplID)) {
                doProcess_NWAL1500_NMAL6050(bizMsg, glblMsg);
            } else if ("NWAL1500_NMAL6760".equals(screenAplID)) {
                doProcess_NWAL1500_NMAL6760(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500_NMAL6800".equals(screenAplID)) {
                doProcess_NWAL1500_NMAL6800(bizMsg, glblMsg);
            } else if ("NWAL1500_NWAL0140".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL0140(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500_NWAL1130".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL1130(bizMsg, glblMsg);
            } else if ("NWAL1500_NWAL1600".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL1600(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500_NWAL1610".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL1610(bizMsg, glblMsg);
            } else if ("NWAL1500_NWAL1620".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL1620(bizMsg, glblMsg);
            } else if ("NWAL1500_NWAL1630".equals(screenAplID)) { // 2018/03/05 S21_NA#19808 Add
                NWAL1500_NWAL1630(bizMsg, glblMsg);
            } else if ("NWAL1500_NWAL1660".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL1660(bizMsg, glblMsg);
            } else if ("NWAL1500_NWAL1760".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL1760(bizMsg, glblMsg);
            } else if ("NWAL1500_NWAL1870".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL1870(bizMsg, glblMsg);
            } else if ("NWAL1500_NWAL2000".equals(screenAplID)) {
                // 2016/02/20 S21_NA#2166 Mod Start
                // doProcess_NWAL1500_NWAL2000(bizMsg, glblMsg);
                // String xxDplyTab = bizMsg.xxDplyTab.getValue(); 2018/01/25 S21_NA#19808 Del
                doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg);
                // bizMsg.xxDplyTab.setValue(xxDplyTab);
                // 2016/02/20 S21_NA#2166 Mod End 2018/01/25 S21_NA#19808 Del
            } else if ("NWAL1500_NWAL2090".equals(screenAplID)) {
                doProcess_NWAL1500_NWAL2090(bizMsg, glblMsg);
            } else if ("NWAL1500_NSAL1240".equals(screenAplID)) {
                doProcess_NWAL1500_NSAL1240(bizMsg, glblMsg);
            } else if ("NWAL1500_NWAL1890".equals(screenAplID)) {   // 2017/08/07 Sol#249 Add
                doProcess_NWAL1500_NWAL1890(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnChange_OrderEntryAction".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnChange_OrderEntryAction(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnChange_OrderEntryActionAddlInfo".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnChange_OrderEntryAction(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnChange_OrderEntryActionDelivery".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnChange_OrderEntryAction(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnChange_OrderEntryActionTool".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OnChange_OrderEntryAction(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_Book".equals(screenAplID)) {
                String xxDplyTab = bizMsg.xxDplyTab.getValue(); // 2016/02/24 S21_NA#4033 Add
                doProcess_NWAL1500Scrn00_CMN_Submit(bizMsg, glblMsg);
                bizMsg.xxDplyTab.setValue(xxDplyTab); // 2016/02/24 S21_NA#4033 Add
            } else if ("NWAL1500Scrn00_CMN_Download".equals(screenAplID)) { // S21_NA#1623
                doProcess_NWAL1500Scrn00_CMN_Download(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnChange_ConfigAction".equals(screenAplID)) { // S21_NA#6312
                doProcess_NWAL1500Scrn00_OnChange_ConfigAction(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnChange_RmaConfigAction".equals(screenAplID)) { // S21_NA#6312
                doProcess_NWAL1500Scrn00_OnChange_RmaConfigAction(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_OnChange_LineSource".equals(screenAplID)) { // S21_NA#8317 Add
                doProcess_NWAL1500Scrn00_OnChange_LineSource(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_OnChange_HddRemoval".equals(screenAplID)) { // S21_NA#8317 Add
                doProcess_NWAL1500Scrn00_OnChange_HddRemoval(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_OnChange_DclnSvcCdConfig".equals(screenAplID)) {    // S21_NA#8388
                doProcess_NWAL1500Scrn00_OnChange_DclnSvcCdConfig(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808
            } else if ("NWAL1500Scrn00_OnChange_DclnSvcCdHdr".equals(screenAplID)) {    // S21_NA#8388
                doProcess_NWAL1500Scrn00_OnChange_DclnSvcCdHdr(bizMsg, glblMsg); // 2018/03/06 S21_NA#19808 Mod
            } else if ("NWAL1500Scrn00_ConfigJump".equals(screenAplID)) { // 2018/01/25 S21_NA#19808 Add Start
                doProcess_NWAL1500Scrn00_ConfigJump(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_PageJump(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NWAL1500Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_PagePrev(bizMsg, glblMsg);  // 2018/01/25 S21_NA#19808 Add End
            // 2018/07/27 S21_NA#14307 Add Start
            } else if ("NWAL1500Scrn00_OpenWin_SoldTo".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_SoldTo(bizMsg);
            } else if ("NWAL1500Scrn00_OpenWin_BillTo".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_BillTo(bizMsg);
            } else if ("NWAL1500Scrn00_OpenWin_ShipTo".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_OpenWin_ShipTo(bizMsg);
            // 2018/07/27 S21_NA#14307 Add End
            } else if ("NWAL1500Scrn00_OnBlur_DeriveFromSerial".equals(screenAplID)) { // 2018/08/21 S21_NA#26767 Add Start
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSerial(bizMsg, glblMsg);
                // 2018/08/21 S21_NA#26767 Add End
            // 2024/02/21 CSA QC#63577 Add Start
            } else if ("NWAL1500Scrn00_Pricing".equals(screenAplID)) {
                doProcess_NWAL1500Scrn00_Pricing(bizMsg, glblMsg);
            // 2024/02/21 CSA QC#63577 Add End
            } else {
                return;
            }
            // 2023/02/07 QC#61010 Add Start
            setFlagFromItems(bizMsg, glblMsg);
            // 2023/02/07 QC#61010 Add End
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL1500_INIT(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        doProcess_NWAL1500_INIT(bizMsg, glblMsg, getGlobalCompanyCode(), getContextUserInfo().getUserId(), getUserProfileService());
    }

    private static void doProcess_NWAL1500_INIT(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String glblCmpyCd, String userId, S21UserProfileService userProfSvc) {

        /**
         * Initial Screen Objects.
         */
        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(bizMsg.D);
        ZYPTableUtil.clear(bizMsg.F);
        ZYPTableUtil.clear(bizMsg.G);
        ZYPTableUtil.clear(bizMsg.H);
        ZYPTableUtil.clear(glblMsg.I);
        // 2018/01/25 S21_NA#19808 Del Start
//        ZYPTableUtil.clear(bizMsg.J); // For Performance QC#8166 Add
//        ZYPTableUtil.clear(bizMsg.K); // For Performance QC#8166 Add
        // 2018/01/25 S21_NA#19808 Del End
        ZYPTableUtil.clear(bizMsg.L);
        ZYPTableUtil.clear(bizMsg.P); // 2018/03/19 S21_NA#24459 Add

        // 2018/01/25 S21_NA#19808 Add Start
        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);
        ZYPTableUtil.clear(glblMsg.D);
        ZYPTableUtil.clear(glblMsg.F);
        ZYPTableUtil.clear(glblMsg.G);
        ZYPTableUtil.clear(glblMsg.H);
        // 2018/01/25 S21_NA#19808 Add End
        /**
         * Setting Initial Values.
         */
        // Common
        String slsDt = ZYPDateUtil.getSalesDate();
        bizMsg.glblCmpyCd.setValue(glblCmpyCd);
        bizMsg.slsDt.setValue(slsDt);
        // QC#4078
        bizMsg.adminPsnCd.setValue(userId);
        bizMsg.xxYesNoCd_CA.setValue(ZYPConstant.FLG_OFF_N);
        NWAL1500CommonLogic.setAuthority(bizMsg, userProfSvc);

        // 2017/08/07 Sol#249 Add Start
        bizMsg.xxResFltrFlg_FP.setValue(ZYPConstant.FLG_OFF_N);
        // 2017/08/07 Sol#249 Add End

        // Header - Order Information
        bizMsg.ordDt.setValue(slsDt);

        // Header - Order Pricing Summary
        final Set<EZDCBigDecimalItem> amtItemList = new HashSet<EZDCBigDecimalItem>();
        amtItemList.add(bizMsg.xxTotBaseAmt_LN);
        amtItemList.add(bizMsg.xxTotTaxAmt_LN);
        amtItemList.add(bizMsg.xxTotFrtAmt_LN);
        amtItemList.add(bizMsg.xxTotAmt_LN);
        amtItemList.add(bizMsg.xxTotBaseAmt_MT);
        amtItemList.add(bizMsg.xxTotTaxAmt_MT);
        amtItemList.add(bizMsg.xxTotFrtAmt_MT);
        amtItemList.add(bizMsg.xxTotAmt_MT);
        amtItemList.add(bizMsg.xxTotBaseAmt_RT);
        amtItemList.add(bizMsg.xxTotTaxAmt_RT);
        amtItemList.add(bizMsg.xxTotFrtAmt_RT);
        amtItemList.add(bizMsg.xxTotAmt_RT);
        amtItemList.add(bizMsg.xxTotBaseAmt);
        amtItemList.add(bizMsg.xxTotTaxAmt);
        amtItemList.add(bizMsg.xxTotFrtAmt);
        amtItemList.add(bizMsg.xxTotAmt);
        amtItemList.add(bizMsg.xxTotInvApplyAmt);
        amtItemList.add(bizMsg.xxTotInvPct);

        for (EZDCBigDecimalItem amtItem : amtItemList) {
            amtItem.setValue(BigDecimal.ZERO);
        }

        // Header Tab - Customer Information
        bizMsg.dropShipFlg.setValue(ZYPConstant.FLG_OFF_N);

        // Header Tab - Header Details
        // Header Tab - Order Source Details
        CPO_SRC_TPTMsg rsltTMsg = (CPO_SRC_TPTMsg) ZYPCodeDataUtil.findByCode("CPO_SRC_TP", glblCmpyCd, CPO_SRC_TP.ORDER_ENTRY_SCREEN);
        if (null != rsltTMsg) {
            bizMsg.cpoSrcTpDescTxt.setValue(rsltTMsg.cpoSrcTpDescTxt.getValue());
            bizMsg.cpoSrcTpCd.setValue(rsltTMsg.cpoSrcTpCd.getValue());
        }

        // Addl Header Tab - Requested Delivery Date
        bizMsg.addRddDt.setValue(slsDt);

        // Create Combo box
        NWAL1500CommonLogicForInit.setScreenListData(bizMsg, glblMsg, glblCmpyCd);

        // Other - Function Currency Digit Number
        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        glblTMsg.glblCmpyCd.setValue(glblCmpyCd);
        glblTMsg = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(glblTMsg);
        if (glblTMsg != null) {
            CCYTMsg ccyMsg = new CCYTMsg();
            ccyMsg.glblCmpyCd.setValue(glblCmpyCd);
            ccyMsg.ccyCd.setValue(glblTMsg.stdCcyCd.getValue());
            ccyMsg = (CCYTMsg) S21FastTBLAccessor.findByKey(ccyMsg);
            if (ccyMsg != null) {
                bizMsg.aftDeclPntDigitNum.setValue(ccyMsg.aftDeclPntDigitNum.getValueInt());
            }
        }

        // For Performance QC#8166 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_TB, NWAL1500CommonLogic.getVarCharConstVal(bizMsg, ORD_LINE_STS_TO_BE_CANCELLED));
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_CA, NWAL1500CommonLogic.getVarCharConstVal(bizMsg, NWAL1500_ORD_LINE_CANCELLED));
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_PU, NWAL1500CommonLogic.getVarCharConstVal(bizMsg, PKG_UOM_FOR_PRC));
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_NH, NWAL1500CommonLogic.getVarCharConstVal(bizMsg, NOT_ALLOC_WH_CD));
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_LB, NWAL1500CommonLogic.getVarCharConstVal(bizMsg, LEASE_BYOT_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(bizMsg.numConstVal_CC, ZYPCodeDataUtil.getNumConstValue(NWAL1500_CONFIG_LINE_LIMIT_CNT, glblCmpyCd)); // QC#10321-23 Add
        ZYPEZDItemValueSetter.setValue(bizMsg.numConstVal_LC, ZYPCodeDataUtil.getNumConstValue(NWAL1500_LINE_LIMIT_CNT, glblCmpyCd)); // QC#10321-23 Add
        // For Performance QC#8166 Add End
    }

    // QC#4078
    private void doProcess_NWAL1500Scrn00_Order_Search(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg, getGlobalCompanyCode(), getContextUserInfo().getUserId(), getUserProfileService());
    }

    /**
     * <pre>
     * Do Order Search Process
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param glblCmpyCd Global Company Code
     * @param userId Userid
     * @param userProfSvc User Profile Service Object
     * </pre>
     */
    public static void doProcess_NWAL1500Scrn00_Order_Search(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String glblCmpyCd, String userId, S21UserProfileService userProfSvc) {

        // 2018/01/25 S21_NA#19808 Add Start
        String xxDplyTab = bizMsg.xxDplyTab.getValue();
        if (!ZYPCommonFunc.hasValue(xxDplyTab)) {
            xxDplyTab = TAB_HEADER;
        }
        int curLineConfigPageNum = 1;
        if (ZYPCommonFunc.hasValue(bizMsg.xxPageShowCurNum_LL)) {
            curLineConfigPageNum = bizMsg.xxPageShowCurNum_LL.getValueInt();
        }
        int curRmaPageNum = 1;
        if (ZYPCommonFunc.hasValue(bizMsg.xxPageShowCurNum_RL)) {
            curRmaPageNum = bizMsg.xxPageShowCurNum_RL.getValueInt();
        }
        // // 2018/01/25 S21_NA#19808 Add End
        // String cpoOrdNum = ZYPCommonFunc.leftPad(bizMsg.cpoOrdNum.getValue(), 6, "0");
        String cpoOrdNum = bizMsg.cpoOrdNum.getValue();

        /**************************************************
         * Initial Screen Objects
         **************************************************/
        // For Performance QC#10321 Mod Start
        // // QC#4078
        // doProcess_NWAL1500_INIT(bizMsg, glblMsg, glblCmpyCd, userId, userProfSvc);
        // ZYPTableUtil.clear(glblMsg.A);
        // ZYPTableUtil.clear(glblMsg.B);
        // ZYPTableUtil.clear(glblMsg.C);
        // ZYPTableUtil.clear(glblMsg.D);
        // ZYPTableUtil.clear(glblMsg.F);
        // ZYPTableUtil.clear(glblMsg.G);
        // ZYPTableUtil.clear(glblMsg.H);
        // ZYPTableUtil.clear(glblMsg.I);
        // // QC743
        // // ZYPTableUtil.clear(glblMsg.J);
        // // ZYPTableUtil.clear(glblMsg.K);
        // // S21_NA#1952
        // // back up for $ button.
        // ZYPTableUtil.clear(glblMsg.T);
        // ZYPTableUtil.clear(glblMsg.U);
        // ZYPTableUtil.clear(glblMsg.V);
        // ZYPTableUtil.clear(glblMsg.W);
        // ZYPTableUtil.clear(glblMsg.X);
        NWAL1500CommonLogic.initProcessForSearch(bizMsg, glblMsg);
        // For Performance QC#10321 Mod End

        // OrderNumber
        // 2017/08/07 Sol#249 Mod Start
//        final Map<String, String> queryParam = new HashMap<String, String>();
        final Map<String, Object> queryParam = new HashMap<String, Object>();
        // 2017/08/07 Sol#249 Mod End
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("configCatgCdOutbound", CONFIG_CATG.OUTBOUND);
        queryParam.put("configCatgCdInbound", CONFIG_CATG.INBOUND);
        queryParam.put("backOrder", NWAL1500Constant.STS_NM_BACK_ORDER); // 2016/10/05 S21_NA#7645-3 Add
        // queryParam.put("invLineCatgCd", INV_LINE_CATG.ITEM);        // QC#27478 2018/07/31 Add // QC#27718 2018/08/14 Del
        queryParam.put("rwsCanceled", RWS_STS.CANCELED);// 2018/10/25 S21_NA#28897 Add

        setFilterValueCommon(queryParam, bizMsg);   // 2017/08/07 Sol#249 Add

        // For Performance QC#8166 Del Start
        // // OutBound
        // final Map<String, String> queryParamOutBound = new HashMap<String, String>();
        // queryParamOutBound.put("glblCmpyCd", glblCmpyCd);
        // queryParamOutBound.put("cpoOrdNum", cpoOrdNum);
        // queryParamOutBound.put("configCatgCd", CONFIG_CATG.OUTBOUND);

        // InBound(for Return)
        // final Map<String, String> queryParamInBound = new HashMap<String, String>();
        // queryParamInBound.put("glblCmpyCd", glblCmpyCd);
        // queryParamInBound.put("cpoOrdNum", cpoOrdNum);
        // queryParamInBound.put("configCatgCd", CONFIG_CATG.INBOUND);
        // For Performance QC#8166 Del End

        /**************************************************
         * CPO
         **************************************************/
        S21SsmEZDResult ssmResult = NWAL1500QueryForSearch.getInstance().getCpoViewInfo(queryParam, bizMsg);

        if (ssmResult.isCodeNotFound()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, cpoOrdNum);
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }
        // QC#5127 2016/10/21 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_BK, bizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.leaseCmpyPoNum_BK, bizMsg.leaseCmpyPoNum);
        // QC#5127 2016/10/21 Add End
        ZYPEZDItemValueSetter.setValue(bizMsg.ovrdPmtTermFlg, NWXC150001DsCheck.getOvrdPmtTermFlg(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustCd.getValue()));         // QC#17474 2017/02/21 Add
        ZYPEZDItemValueSetter.setValue(bizMsg.leaseTermMthAot_EM, bizMsg.leaseTermMthAot); // QC#22789 2017/11/28 Add
        // 2017/12/07 S21_NA#21621 Add Start
        if (S21StringUtil.isEquals(bizMsg.dropShipFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.addShipToLocNm, bizMsg.shipToLocNm);
        }
        // 2017/12/07 S21_NA#21621 Add End
        // 2018/01/26 S21_NA#23140 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, NWAL1500CommonLogicForCustomer.cmbnAddr(bizMsg, SPACE));
        ZYPEZDItemValueSetter.setValue(bizMsg.entShipToCustLocAddr, NWAL1500CommonLogicForCustomer.cmbnAddr(bizMsg, ENTER));
        // 2018/01/26 S21_NA#23140 Add End

        // 2018/12/12 QC#29315 Add Start
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd) && ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsBizAreaCd, NWXC150001DsCheck.getDsBizArea(glblCmpyCd, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue(), bizMsg.dsOrdRsnCd.getValue()));
        }
        // 2018/12/12 QC#29315 Add End

        // 2018/01/25 S21_NA#19808 Add Start
        /**************************************************
         * store back up data for $ button.
         **************************************************/
//        EZDMsg.copy(bizMsg, "", glblMsg, "D");
        EZDMsg.copy(bizMsg, null, glblMsg, null);
        // 2018/01/25 S21_NA#19808 Add End
        // QC#27039
        if (ORD_HDR_STS.VALIDATED.equals(bizMsg.ordHdrStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdCatgDescTxt_D, bizMsg.dsOrdCatgDescTxt);
            ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdTpCd_D, bizMsg.dsOrdTpCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.sellToCustCd_D, bizMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.negoDealAmt_D, bizMsg.negoDealAmt);
            ZYPEZDItemValueSetter.setValue(glblMsg.slsRepTocCd_D, bizMsg.slsRepTocCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.custIssPoNum_D, bizMsg.custIssPoNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.frtCondDescTxt_D, bizMsg.frtCondDescTxt);
            ZYPEZDItemValueSetter.setValue(glblMsg.spclHdlgTpCd_D, bizMsg.spclHdlgTpCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.shpgSvcLvlCd_D, bizMsg.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.prcContrNum_D, bizMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.leaseTermMthAot_D, bizMsg.leaseTermMthAot);
            ZYPEZDItemValueSetter.setValue(glblMsg.leasePmtFreqCd_D, bizMsg.leasePmtFreqCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.leaseTermMthAot_DE, bizMsg.leaseTermMthAot_EM);
        }

        // 2018/08/21 S21_NA#26767 Add Start
        NWAL1500CommonLogicForLineConfig.setConfigType(bizMsg);
        NWAL1500CommonLogicForLineConfig.setReturnReason(bizMsg);
        // 2018/08/21 S21_NA#26767 Add End
        /**************************************************
         * DS_CONFIG
         **************************************************/
        // For Performance QC#8166 Mod Start
        // S21SsmEZDResult ssmResultOut = NWAL1500QueryForSearch.getInstance().getConfigInfo(queryParamOutBound, bizMsg.A);
        // S21SsmEZDResult ssmResultIn = NWAL1500QueryForSearch.getInstance().getConfigRtrnInfo(queryParamInBound, bizMsg.C);
        ssmResult = NWAL1500QueryForSearch.getInstance().getConfigInfo(queryParam, glblMsg.J); // 2018/01/25 S21_NA#19808 Mod Start

        if (ssmResult.isCodeNormal()) {
            int lineConfigVldCnt = 0;
            int rmaConfigVldCnt = 0;
            for (int i = 0; i < glblMsg.J.getValidCount(); i++) {
                NWAL1500_JSMsg configMsg = glblMsg.J.no(i); // 2018/01/25 S21_NA#19808 Mod
                NWAL1500CommonLogic.cmbnAddrForConf(configMsg, SPACE); // 2018/01/26 S21_NA#23140 Add

                if (CONFIG_CATG.OUTBOUND.equals(configMsg.configCatgCd_JC.getValue())) {
                    NWAL1500_ASMsg lineConfigMsg = glblMsg.A.no(lineConfigVldCnt); // 2018/01/25 S21_NA#19808 Mod
                    EZDMsg.copy(configMsg, "JC", lineConfigMsg, "LC");
                    NWAL1500CommonLogic.setConfigNewFlg(bizMsg, lineConfigMsg);
                    // 2016/08/26 S21_NA#9806 Add Start
                    S21SsmEZDResult ssmResultForCsmp = NWAL1500QueryForSearch.getInstance().getOutboundConfigCsmpInfo(bizMsg, lineConfigMsg);
                    if (ssmResultForCsmp.isCodeNormal()) {
                        Map<String, String> csmpInfoMap = (Map<String, String>) ssmResultForCsmp.getResultObject();
                        if (csmpInfoMap != null) {
                            ZYPEZDItemValueSetter.setValue(lineConfigMsg.csmpContrNum_LC, csmpInfoMap.get("CSMP_CONTR_NUM"));
                            ZYPEZDItemValueSetter.setValue(lineConfigMsg.dlrRefNum_LC, csmpInfoMap.get("DLR_REF_NUM"));
                            ZYPEZDItemValueSetter.setValue(lineConfigMsg.csmpPrcListCd_LC, csmpInfoMap.get("CSMP_PRC_LIST_CD"));
                            ZYPEZDItemValueSetter.setValue(lineConfigMsg.prcCatgNm_LC, csmpInfoMap.get("PRC_CATG_NM"));
                        }
                    }
                    // 2016/08/26 S21_NA#9806 Add End
                    // 2017/12/08 S21_NA#21621 Add Start
                    if (ZYPConstant.FLG_ON_Y.equals(configMsg.dropShipFlg_JC.getValue())) {
                        ZYPEZDItemValueSetter.setValue(lineConfigMsg.addShipToLocNm_LC, configMsg.shipToLocNm_JC);
                    }
                    // 2017/12/08 S21_NA#21621 Add End
                    EZDMsg.copy(lineConfigMsg, "LC", lineConfigMsg, "DB"); // 2018/03/12 S21_NA#19808 Add
                    lineConfigVldCnt++;
                } else {
                    NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(rmaConfigVldCnt); // 2018/01/25 S21_NA#19808 Mod
                    EZDMsg.copy(configMsg, "JC", rmaConfigMsg, "RC");
                    NWAL1500CommonLogic.setRmaConfigNewFlg(bizMsg, rmaConfigMsg);
                    // 2016/08/26 S21_NA#9806 Add Start
                    S21SsmEZDResult ssmResultForCsmp = NWAL1500QueryForSearch.getInstance().getInboundConfigCsmpInfo(bizMsg, rmaConfigMsg);
                    if (ssmResultForCsmp.isCodeNormal()) {
                        Map<String, String> csmpInfoMap = (Map<String, String>) ssmResultForCsmp.getResultObject();
                        if (csmpInfoMap != null) {
                            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.csmpContrNum_RC, csmpInfoMap.get("CSMP_CONTR_NUM"));
                            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.dlrRefNum_RC, csmpInfoMap.get("DLR_REF_NUM"));
                            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.csmpPrcListCd_RC, csmpInfoMap.get("CSMP_PRC_LIST_CD"));
                            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.prcCatgNm_RC, csmpInfoMap.get("PRC_CATG_NM"));
                        }
                    }
                    // 2016/08/26 S21_NA#9806 Add End
                    // 2017/12/08 S21_NA#21621 Add Start
                    if (ZYPConstant.FLG_ON_Y.equals(configMsg.dropShipFlg_JC.getValue())) {
                        ZYPEZDItemValueSetter.setValue(rmaConfigMsg.addShipToLocNm_RC, configMsg.shipToLocNm_JC);
                    }
                    // 2017/12/08 S21_NA#21621 Add End
                    EZDMsg.copy(rmaConfigMsg, "RC", rmaConfigMsg, "DB"); // 2018/03/12 S21_NA#19808 Add
                    rmaConfigVldCnt++;
                }
            }

            glblMsg.A.setValidCount(lineConfigVldCnt); // 2018/01/25 S21_NA#19808 Mod
            glblMsg.C.setValidCount(rmaConfigVldCnt); // 2018/01/25 S21_NA#19808 Mod
        }

        // for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //     NWAL1500_ACMsg configLineMsg = bizMsg.A.no(i);
        //     NWAL1500CommonLogic.setConfigNewFlg(bizMsg, configLineMsg);
        // }
        // for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
        //     NWAL1500_CCMsg rmaConfigLineMsg = bizMsg.C.no(i);
        //     NWAL1500CommonLogic.setRmaConfigNewFlg(bizMsg, rmaConfigLineMsg);
        // }
        // For Performance QC#8166 Mod End

        /**************************************************
         * CPO_DTL
         **************************************************/
        // OUTBOUND
        // For Performance QC#8166 Mod Start
        // if (!ssmResultOut.isCodeNotFound()) {
        if (glblMsg.A.getValidCount() > 0) { //2018/01/25 S21_NA#19808 Mod
        // For Performance QC#8166 Mod End
            ssmResult = NWAL1500QueryForSearch.getInstance().getCpoDtlViewInfo(queryParam, glblMsg.B); //2018/01/25 S21_NA#19808 Mod
            // QC#24245 2018/06/13 Del Start
            //if (ssmResult.isCodeNotFound()) {
            //    ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, cpoOrdNum);
            //    bizMsg.setMessageInfo(NZZM0000E);
            //    return;
            //}
            // QC#24245 2018/06/13 Del End
            // 2016/07/04 S21_NA#7645 Add Start
            // Map<String, BigDecimal> boQtyMap = new HashMap<String, BigDecimal>(); 2016/10/05 S21_NA#7645-3 Del
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i); // 2018/01/25 S21_NA#19808 Mod

                // 2016/08/22 S21_NA#12462 Add Start
                if (!ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LD)) {
                    ZYPEZDItemValueSetter.setValue(lineMsg.ordLineStsDescTxt_LD, lineMsg.ordLineStsDescTxt_LL);
                }
                // 2016/08/22 S21_NA#12462 Add End
                EZDMsg.copy(lineMsg, "LL", lineMsg, "DB"); // 2018/01/25 S21_NA#19808 Mod
                // 2016/10/05 S21_NA#7645-3 Del Start
//                // 2016/07/27 S21_NA#4691-2 Add Start
//                BigDecimal lineBoQty = NWAL1500QueryForLineConfig.getInstance().getValidateQtyOnDtlLine(bizMsg, lineMsg);
//                if (lineBoQty == null) {
//                    lineBoQty = BigDecimal.ZERO;
//                }
//                boolean hasValidatedQty = lineBoQty.compareTo(BigDecimal.ZERO) > 0;
//                if (!hasValidatedQty) {
//                    lineMsg.boQty_LL.setValue(BigDecimal.ZERO);
//                    continue;
//                } else {
//                    lineMsg.boQty_LL.setValue(lineBoQty);
//                }
//                // 2016/07/27 S21_NA#4691-2 Add End
//                String boQtyKey = NWAL1500CommonLogicForLineConfig.getBoQtyKey(bizMsg, lineMsg);
//                BigDecimal boQty = boQtyMap.get(boQtyKey);
//                if (boQty == null) {
//                    boolean isHaveBoQty = NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, lineMsg); // 2016/07/27 S21_NA#4691-2 Add Return Value
//                    boQty = lineMsg.boQty_LL.getValue();
//                    if (isHaveBoQty) { // 2016/07/27 S21_NA#4691-2 Add Condition
//                        if (ZYPCommonFunc.hasValue(boQty)) {
//                            boQtyMap.put(boQtyKey, boQty);
//                        } else {
//                            boQtyMap.put(boQtyKey, BigDecimal.ZERO);
//                        }
//                    } // 2016/07/27 S21_NA#4691-2 Add Condition
//                } else {
//                    lineMsg.boQty_LL.setValue(boQty);
//                }
                // 2016/10/05 S21_NA#7645-3 Del End
            }
            // 2016/07/04 S21_NA#7645 Add End
        }
        
        // 

        // INBOUND
        // For Performance QC#8166 Mod Start
        // if (!ssmResultIn.isCodeNotFound()) {
        if (glblMsg.C.getValidCount() > 0) { // 2018/01/25 S21_NA#19808 Mod
        // For Performance QC#8166 Mod End
            ssmResult = NWAL1500QueryForSearch.getInstance().getCpoRtrnDtlViewInfo(queryParam, glblMsg.D); // 2018/01/25 S21_NA#19808 Mod
            // QC#24245 2018/06/13 Del Start
            //if (ssmResult.isCodeNotFound()) {
            //    ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, cpoOrdNum);
            //    bizMsg.setMessageInfo(NZZM0000E);
            //    return;
            //}
            // QC#24245 2018/06/13 Del End
            // 2018/03/12 S21_NA#19808 Add Start
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);
                EZDMsg.copy(rmaLineMsg, "RL", rmaLineMsg, "DB");
            }
            // 2018/03/12 S21_NA#19808 Add Start
        }

        // 2018/08/02 S21_NA#26665 add start
        CPO_SRC_TPTMsg cpoSrcTpTMsg = new CPO_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.cpoSrcTpCd, CPO_SRC_TP.ORDER_ENTRY_SCREEN);

        cpoSrcTpTMsg = (CPO_SRC_TPTMsg) S21CodeTableAccessor.findByKey(cpoSrcTpTMsg);
        // 2018/08/02 S21_NA#26665 add end

        // QC#24245 2018/06/13 Add Start
        if (glblMsg.A.getValidCount() > 0) {
            for (int i= 0; i < glblMsg.A.getValidCount(); i++) { 
                NWAL1500_ASMsg configLineMsg = glblMsg.A.no(i);
                String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_LC.getValue();
                int maxLineNum = NWAL1500CommonLogicForLineControl.getMaxLineNum(glblMsg.B, dsOrdPosnNum);
                if(maxLineNum == 0){
                    int addLineRowForB = NWAL1500CommonLogicForLineControl.getAddLineRow(glblMsg.B, dsOrdPosnNum);
                    NWAL1500_BSMsg addLineForB = (NWAL1500_BSMsg) NWAL1500CommonLogicForLineControl.insertNewLine(glblMsg.B, addLineRowForB);
                    if (addLineForB != null) {
                        // set up new line for J
                        addLineForB.dsOrdPosnNum_LL.setValue(dsOrdPosnNum);
                        addLineForB.dsCpoLineNum_LL.setValue(maxLineNum + 1);
                        addLineForB.xxLineNum_LL.setValue(NWAL1500CommonLogic.concatLineNum(addLineForB));
                        addLineForB.dsOrdLineCatgCd_LL.setValue(bizMsg.dsOrdLineCatgCd_LD.getValue());
                        addLineForB.prcBaseDt_LL.setValue(bizMsg.slsDt.getValue());
                        addLineForB.rddDt_LL.setValue(bizMsg.addRddDt.getValue());
                        addLineForB.prcCatgNm_LL.setValue(bizMsg.prcCatgNm.getValue());
                        addLineForB.flPrcListNm_LL.setValue(bizMsg.flPrcListNm.getValue());
                        addLineForB.invQty_LL.setValue(BigDecimal.ZERO); // S21_NA#2013
                        ZYPEZDItemValueSetter.setValue(addLineForB.custIssPoNum_LL, bizMsg.custIssPoNum);
                        ZYPEZDItemValueSetter.setValue(addLineForB.custIssPoDt_LL, bizMsg.custIssPoDt);
                        // 2018/08/02 S21_NA#26665 mod start
//                        ZYPEZDItemValueSetter.setValue(addLineForB.cpoSrcTpDescTxt_LL, bizMsg.cpoSrcTpDescTxt);
                        ZYPEZDItemValueSetter.setValue(addLineForB.cpoSrcTpDescTxt_LL, cpoSrcTpTMsg.cpoSrcTpDescTxt);
                        // 2018/08/02 S21_NA#26665 mod end
                        ZYPEZDItemValueSetter.setValue(addLineForB.ordSrcRefNum_LL, bizMsg.ordSrcRefNum);
                        NWAL1500_ASMsg configLineSMsg = new NWAL1500_ASMsg();
                        EZDMsg.copy(configLineMsg, null, configLineSMsg, null);
                        copyCsmpData(bizMsg, glblMsg, configLineSMsg, addLineForB);
                    }
                }
            }
        }
        if (glblMsg.C.getValidCount() > 0) {
            for (int i= 0; i < glblMsg.C.getValidCount(); i++) { 
                NWAL1500_CSMsg configLineMsg = glblMsg.C.no(i);
                String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_RC.getValue();
                int maxLineNum = NWAL1500CommonLogicForLineControl.getMaxLineNum(glblMsg.D, dsOrdPosnNum);
                if(maxLineNum == 0){
                    int addLineRowForK = NWAL1500CommonLogicForLineControl.getAddLineRow(glblMsg.D, dsOrdPosnNum);
                    NWAL1500_DSMsg addLineForD = (NWAL1500_DSMsg) NWAL1500CommonLogicForLineControl.insertNewLine(glblMsg.D, addLineRowForK);
                    if (addLineForD != null) {
                        // set up new line for K
                        addLineForD.dsOrdPosnNum_RL.setValue(dsOrdPosnNum);
                        addLineForD.dsCpoLineNum_RL.setValue(maxLineNum + 1);
                        addLineForD.xxLineNum_RL.setValue(NWAL1500CommonLogic.concatLineNum(addLineForD));
                        NWAL1500CommonLogic.setDefaultLineCatgPullDownForNewRma(bizMsg, addLineForD);
                        addLineForD.dsOrdLineCatgCd_RL.setValue(bizMsg.dsOrdLineCatgCd_RD.getValue());
                        addLineForD.prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
                        addLineForD.rqstPickUpDt_RL.setValue(bizMsg.addRddDt.getValue());
                        addLineForD.prcCatgNm_RL.setValue(bizMsg.prcCatgNm.getValue());
                        addLineForD.flPrcListNm_RL.setValue(bizMsg.flPrcListNm.getValue());
                        addLineForD.rtrnQty_RL.setValue(BigDecimal.ZERO);
                        addLineForD.cancQty_RL.setValue(BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(addLineForD.custIssPoNum_RL, bizMsg.custIssPoNum);
                        ZYPEZDItemValueSetter.setValue(addLineForD.custIssPoDt_RL, bizMsg.custIssPoDt);
                        // 2018/08/02 S21_NA#26665 mod start
//                        ZYPEZDItemValueSetter.setValue(addLineForD.cpoSrcTpDescTxt_RL, bizMsg.cpoSrcTpDescTxt);
                        ZYPEZDItemValueSetter.setValue(addLineForD.cpoSrcTpDescTxt_RL, cpoSrcTpTMsg.cpoSrcTpDescTxt);
                        // 2018/08/02 S21_NA#26665 mod end
                        ZYPEZDItemValueSetter.setValue(addLineForD.ordSrcRefNum_RL, bizMsg.ordSrcRefNum);
                        NWAL1500_CSMsg configLineSMsg = new NWAL1500_CSMsg();
                        EZDMsg.copy(configLineMsg, null, configLineSMsg, null);
                        copyCsmpData(bizMsg, configLineSMsg, addLineForD);
                    }
                }
            }
        }
        // QC#24245 2018/06/13 Add End

        // 2019/08/01 QC#52156 Add Start
        // Check Approved PR
        // 2020/01/07 QC#54996 Add Start
        boolean openPRFlg = false;
        // 2020/01/07 QC#54996 Add End
        ssmResult = NWAL1500QueryForSearch.getInstance().getApprovedPRLineNum(bizMsg);
        if (ssmResult.isCodeNormal()) {
            List<Map<String,String>> lineNumList = (List<Map<String,String>>)ssmResult.getResultObject();
            for (Map<String, String> lineNumMap : lineNumList) {
                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    if (lineNumMap.get("TRX_LINE_NUM").equals(glblMsg.B.no(i).cpoDtlLineNum_LL.getValue()) // 
                            && lineNumMap.get("TRX_LINE_SUB_NUM").equals(glblMsg.B.no(i).cpoDtlLineSubNum_LL.getValue())) {
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).xxDplyCtrlFlg_LL, ZYPConstant.FLG_ON_Y);
                        // 2020/01/07 QC#54996 Add Start
                        openPRFlg = true;
                        // 2020/01/07 QC#54996 Add End
                    }
                }
            }
        }
        // 2019/08/01 QC#52156 Add End

        // 2020/01/07 QC#54996 Add Start
        if (NWXC150001DsCheck.isAvalOrderCtxType(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.OPEN_SO_PR_CTRL_FRT_INFO, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue(), bizMsg.dsOrdRsnCd.getValue())) {
            if (openPRFlg) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_FI, ZYPConstant.FLG_ON_Y);
            } else {
                ssmResult = NWAL1500QueryForSearch.getInstance().getOpenSO(bizMsg);
                if((Integer) ssmResult.getResultObject() > 0) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_FI, ZYPConstant.FLG_ON_Y);
                }
            }
        
        }
        // 2020/01/07 QC#54996 Add End

        /**************************************************
         * SalesCredit
         **************************************************/
        // 2017/11/02 S21_NA#20146 Mod Start
        // Header
        //NWAL1500QueryForSearch.getInstance().getSalesCreditForHeader(queryParam, bizMsg.F);
        ssmResult = NWAL1500QueryForSearch.getInstance().getSalesCreditForHeader(queryParam, bizMsg.F);
        List<Map<String, Object>> dummyRepList = new ArrayList<Map<String, Object>>(0);
        if (ssmResult.isCodeNormal()) {

            for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
                NWAL1500_FCMsg slsCrMsg = bizMsg.F.no(i);

                String fstBizCtxAttbTxt = NWAL1500Query.getInstance().getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.GL_SGMT_DPLY_ORD_TP, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
                if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                    if (fstBizCtxAttbTxt.equals(slsCrMsg.lineBizRoleTpCd_FS.getValue()) && ZYPConstant.FLG_OFF_N.equals(slsCrMsg.slsCrQuotFlg_FS.getValue())) {

                        List<Map<String, Object>> slsRepList = NWAL1500CommonLogic.getSalesRepList(bizMsg.glblCmpyCd.getValue(), slsCrMsg.slsRepTocCd_FS.getValue());
                        if (slsRepList != null && slsRepList.size() != 0) {
                            for (Map<String, Object> slsRepMap : slsRepList) {
                                dummyRepList.add(slsRepMap);
                            }
                        }
                    }
                }
            }
            NWAL1500CommonLogic.clearGLSegment(bizMsg);
            if (dummyRepList != null && dummyRepList.size() != 0) {
                NWAL1500CommonLogic.setGLSegment(bizMsg, dummyRepList.get(0));
            }
        }
        // 2017/11/02 S21_NA#20146 Mod End

        // For Performance QC#8166 Del Start
        // // OUTBOUND
        // NWAL1500QueryForSearch.getInstance().getSalesCreditForConfigOut(queryParamOutBound, bizMsg.G);
        // // INBOUND
        // NWAL1500QueryForSearch.getInstance().getSalesCreditForConfigIn(queryParamInBound, bizMsg.H);
        // For Performance QC#8166 Del End

        // For Performance QC#8166 Add Start
        // Config
        ssmResult = NWAL1500QueryForSearch.getInstance().getSalesCreditForConfig(queryParam, glblMsg.K); // 2018/01/25 S21_NA#19808 Mod

        if (ssmResult.isCodeNormal()) {
            int lineSlsCrVldCnt = 0;
            int rmaSlsCrVldCnt = 0;
            for (int i = 0; i < glblMsg.K.getValidCount(); i++) {  // 2018/01/25 S21_NA#19808 Mod
                NWAL1500_KSMsg slsCrMsg = glblMsg.K.no(i);  // 2018/01/25 S21_NA#19808 Mod

                if (CONFIG_CATG.OUTBOUND.equals(slsCrMsg.configCatgCd_KS.getValue())) {
                    NWAL1500_GCMsg lineSlsCrMsg = bizMsg.G.no(lineSlsCrVldCnt);
                    EZDMsg.copy(slsCrMsg, "KS", lineSlsCrMsg, "GS");
                    lineSlsCrVldCnt++;
                } else {
                    NWAL1500_HCMsg rmaSlsCrMsg = bizMsg.H.no(rmaSlsCrVldCnt);
                    EZDMsg.copy(slsCrMsg, "KS", rmaSlsCrMsg, "HS");
                    rmaSlsCrVldCnt++;
                }
            }

            bizMsg.G.setValidCount(lineSlsCrVldCnt);
            bizMsg.H.setValidCount(rmaSlsCrVldCnt);
        }
        // For Performance QC#8166 Add End

        /**************************************************
         * Price
         **************************************************/
        // 2018/03/15 S21_NA#19808 Mod Start
        S21SsmEZDResult ssmRslt = NWAL1500QueryForSearch.getInstance().getPriceInfo(queryParam, glblMsg.I);
        int rsltCnt = ssmRslt.getQueryResultCount();
        glblMsg.xxDtlCnt_LP.setValue(rsltCnt);
        // 2018/03/15 S21_NA#19808 Mod End

        /**************************************************
         * Shipping Plan
         **************************************************/
        if (!ORD_HDR_STS.SAVED.equals(bizMsg.ordHdrStsCd.getValue())) {
            NWAL1500QueryForSearch.getInstance().getShippingPlanInfo(queryParam, glblMsg.S);
        }

        // START 2016/08/08 S21_NA#11592 Add
        /**************************************************
         * Order Line Values Set
         **************************************************/
        // NWAL1500QueryForSearch.getInstance().getOrdLineValSetInfo(bizMsg.glblCmpyCd.getValue(), bizMsg.P); // 2018/03/19 S21_NA#24459 Del
        // END   2016/08/08 S21_NA#11592 Add

        // Copy
        // 2018/01/25 S21_NA#19808 Mod Start
//        // For Performance QC#11619,13026 Mod Start
//        // EZDMsg.copy(bizMsg, null, glblMsg, null);
//        EZDMsg.fastCopy(bizMsg, glblMsg);
//        // For Performance QC#11619,13026 Mod End

        // 2018/01/25 S21_NA#19808 Del Start
//        EZDMsg.fastCopy(glblMsg.A, bizMsg.A);
//        EZDMsg.fastCopy(glblMsg.B, bizMsg.B);
//        EZDMsg.fastCopy(glblMsg.C, bizMsg.C);
//        EZDMsg.fastCopy(glblMsg.A, bizMsg.A);
        // 2018/01/25 S21_NA#19808 Del End
        // 2018/01/25 S21_NA#19808 Mod End

        /**************************************************
         * Create pulldown lists.
         **************************************************/
        String ordDt = bizMsg.ordDt.getValue();
        NWAL1500CommonLogic.createSubRsnCdPulldown(bizMsg, ordDt);
        NWAL1500CommonLogic.createLineCatgPulldown(bizMsg, ordDt);
        NWAL1500CommonLogic.createLineCatgPulldownForRma(bizMsg, glblMsg, ordDt); // 2018/01/25 S21_NA#19808 Mod
        NWAL1500CommonLogicForCategory.setShpgSvcLvlPullDown(bizMsg); // QC#10754

        // For Performance QC#8166 Del Start
        // // Create Combo box
        // NWAL1500CommonLogicForInit.setScreenListData(bizMsg, glblMsg, glblCmpyCd);
        // For Performance QC#8166 Del End

        // For Performance QC#8166 Add Start
        Map<String, List<Map<String, String>>> lineUomCacheMap = new HashMap<String, List<Map<String, String>>>();
        Map<String, List<Map<String, String>>> rmaUomCacheMap = new HashMap<String, List<Map<String, String>>>();
        // For Performance QC#8166 Add End

        // 2018/01/25 S21_NA#19808 Del Start TODO
//        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            final NWAL1500_BCMsg lineMsg = bizMsg.B.no(i);
//            NWAL1500CommonLogic.setUomPullDownForConfLineFromCache(bizMsg, lineMsg, lineUomCacheMap); // For Performance QC#8166 Mod
//
//            // NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, lineMsg); // 2016/03/15 S21_NA#4691 Add End S21_NA#7645 Del
//        }
//        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
//            final NWAL1500_DCMsg lineMsg = bizMsg.D.no(i);
//            NWAL1500CommonLogic.setUomPullDownForRmaFromCache(bizMsg, lineMsg, rmaUomCacheMap); // For Performance QC#8166 Mod
//        }
        // 2018/01/25 S21_NA#19808 Del End TODO

        /**************************************************
         * store background lines.
         **************************************************/
        // QC743
        // EZDMsg.copy(bizMsg.B, null, glblMsg.J, null);
        // EZDMsg.copy(bizMsg.D, null, glblMsg.K, null);

        // S21_NA#1952
        /**************************************************
         * store back up data for $ button.
         **************************************************/
        // 2018/01/25 S21_NA#19808 Del Start
//        EZDMsg.copy(bizMsg, "", glblMsg, "D");
        // 2018/01/25 S21_NA#19808 Deld End

        // 2018/01/25 S21_NA#19808 Del Start
//        // For Performance QC#11619,13026 Mod Start
//        // EZDMsg.copy(bizMsg.A, null, glblMsg.T, null);
//        // EZDMsg.copy(bizMsg.B, null, glblMsg.U, null);
//        // EZDMsg.copy(bizMsg.C, null, glblMsg.V, null);
//        // EZDMsg.copy(bizMsg.D, null, glblMsg.W, null);
//        // EZDMsg.copy(glblMsg.I, null, glblMsg.X, null);
//        EZDMsg.fastCopy(bizMsg.A, glblMsg.T);
//        EZDMsg.fastCopy(bizMsg.B, glblMsg.U);
//        EZDMsg.fastCopy(bizMsg.C, glblMsg.V);
//        EZDMsg.fastCopy(bizMsg.D, glblMsg.W);
//        EZDMsg.fastCopy(glblMsg.I, glblMsg.X);
//        // For Performance QC#11619,13026 Mod End
        // 2018/01/25 S21_NA#19808 Del End
        // 2018/01/25 S21_NA#19808 Add Start
        EZDMsg.fastCopy(glblMsg.A, glblMsg.T);
        EZDMsg.fastCopy(glblMsg.B, glblMsg.U);
        EZDMsg.fastCopy(glblMsg.C, glblMsg.V);
        EZDMsg.fastCopy(glblMsg.D, glblMsg.W);
        EZDMsg.fastCopy(glblMsg.I, glblMsg.X);
        // 2018/01/25 S21_NA#19808 Add End

        // 2017/08/07 Sol#249 Add Start
        if(S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.xxResFltrFlg_FP.getValue())) {

            queryParam.put("xxResFltrFlg", ZYPConstant.FLG_ON_Y);

            /**************************************************
             * DS_CONFIG
             **************************************************/
            for(int i = 0; i < glblMsg.A.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                glblMsg.A.no(i).xxResFltrFlg_LC.clear(); // 2018/01/25 S21_NA#19808 Mod
            }

            for(int i = 0; i < glblMsg.C.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                glblMsg.C.no(i).xxResFltrFlg_RC.clear(); // 2018/01/25 S21_NA#19808 Mod
            }

            ZYPTableUtil.clear(glblMsg.J); // 2018/01/25 S21_NA#19808 Mod
            ssmResult = NWAL1500QueryForSearch.getInstance().getConfigInfo(queryParam, glblMsg.J); // 2018/01/25 S21_NA#19808 Mod

            if (ssmResult.isCodeNormal()) {

                if(S21StringUtil.isEquals(TAB_LINE_CONFIG, bizMsg.xxDplyTab.getValue())) {
                    for(int i = 0; i < glblMsg.A.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                        NWAL1500_ASMsg conf_LineConfMsg = glblMsg.A.no(i); // 2018/01/25 S21_NA#19808 Mod
                        boolean equalsFlg = false;
                        for(int j = 0; j < glblMsg.J.getValidCount(); j++) { // 2018/01/25 S21_NA#19808 Mod
                            NWAL1500_JSMsg bk_configMsg = glblMsg.J.no(j); // 2018/01/25 S21_NA#19808 Mod
                            if (CONFIG_CATG.OUTBOUND.equals(bk_configMsg.configCatgCd_JC.getValue())) {
                                if(S21StringUtil.isEquals(bk_configMsg.dsOrdPosnNum_JC.getValue(), conf_LineConfMsg.dsOrdPosnNum_LC.getValue())) {
                                    equalsFlg = true;
                                    break;
                                }
                            }
                        }
                        if(!equalsFlg) {
                            ZYPEZDItemValueSetter.setValue(conf_LineConfMsg.xxResFltrFlg_LC, ZYPConstant.FLG_ON_Y);
                        }
                    }
                }

                if(S21StringUtil.isEquals(TAB_RMA, bizMsg.xxDplyTab.getValue())) {
                    for(int i = 0; i < glblMsg.C.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                        NWAL1500_CSMsg conf_RmaMsg = glblMsg.C.no(i);  // 2018/01/25 S21_NA#19808 Mod
                        boolean equalsFlg = false;
                        for(int j = 0; j < glblMsg.J.getValidCount(); j++) { // 2018/01/25 S21_NA#19808 Mod
                            NWAL1500_JSMsg bk_configMsg = glblMsg.J.no(j); // 2018/01/25 S21_NA#19808 Mod
                            if (CONFIG_CATG.INBOUND.equals(bk_configMsg.configCatgCd_JC.getValue())) {
                                if(S21StringUtil.isEquals(bk_configMsg.dsOrdPosnNum_JC.getValue(), conf_RmaMsg.dsOrdPosnNum_RC.getValue())) {
                                    equalsFlg = true;
                                    break;
                                }
                            }
                        }
                        if(!equalsFlg) {
                            ZYPEZDItemValueSetter.setValue(conf_RmaMsg.xxResFltrFlg_RC, ZYPConstant.FLG_ON_Y);
                        }
                    }
                }
            } else {
                if(S21StringUtil.isEquals(TAB_LINE_CONFIG, bizMsg.xxDplyTab.getValue())) {
                    for(int i = 0; i < glblMsg.A.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxResFltrFlg_LC, ZYPConstant.FLG_ON_Y); // 2018/01/25 S21_NA#19808 Mod
                    }
                }

                if(S21StringUtil.isEquals(TAB_RMA, bizMsg.xxDplyTab.getValue())) {
                    for(int i = 0; i < glblMsg.C.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                        ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).xxResFltrFlg_RC, ZYPConstant.FLG_ON_Y); // 2018/01/25 S21_NA#19808 Mod
                    }
                }
            }

            /**************************************************
             * CPO_DTL
             **************************************************/

            // clone bizMsg
             NWAL1500SMsg bk_glblMsg = (NWAL1500SMsg)glblMsg.clone(); // 2018/01/25 S21_NA#19808 Mod

            // 2018/01/25 S21_NA#19808 Mod glblMsg => glblMsg
            for(int i = 0; i < glblMsg.B.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                glblMsg.B.no(i).xxResFltrFlg_LL.clear(); // 2018/01/25 S21_NA#19808 Mod
            }
            for(int i = 0; i < glblMsg.D.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                glblMsg.D.no(i).xxResFltrFlg_RL.clear(); // 2018/01/25 S21_NA#19808 Mod
            }

            if(S21StringUtil.isEquals(TAB_LINE_CONFIG, bizMsg.xxDplyTab.getValue())) {

                boolean isShowInclLine = S21StringUtil.isEquals(bizMsg.xxShowInclLineFlg_FL.getValue(), ZYPConstant.FLG_ON_Y);

                if(isHideLine(isConfFilter_LineConf(bizMsg), isShowInclLine, isLineFilter_LineConf(bizMsg))) {
                    for(int i = 0; i < glblMsg.B.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).xxResFltrFlg_LL, ZYPConstant.FLG_ON_Y); // 2018/01/25 S21_NA#19808 Mod
                    }
                } else {
                    if(isConfFilter_LineConf(bizMsg) && isShowInclLine && !isLineFilter_LineConf(bizMsg)) {
                        for(int i = 0; i < glblMsg.B.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).xxResFltrFlg_LL, ZYPConstant.FLG_ON_Y); // 2018/01/25 S21_NA#19808 Mod
                        }
                        for(int i = 0; i < glblMsg.B.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                            String dsOrdPosnNum_List = glblMsg.B.no(i).dsOrdPosnNum_LL.getValue(); // 2018/01/25 S21_NA#19808 Mod
                            for(int j = 0; j < glblMsg.A.getValidCount(); j++) { // 2018/01/25 S21_NA#19808 Mod
                                String dsOrdPosnNum_Conf = glblMsg.A.no(j).dsOrdPosnNum_LC.getValue(); // 2018/01/25 S21_NA#19808 Mod
                                String xxResFltrFlg_Conf = glblMsg.A.no(j).xxResFltrFlg_LC.getValue(); // 2018/01/25 S21_NA#19808 Mod
                                    if (S21StringUtil.isEquals(dsOrdPosnNum_Conf, dsOrdPosnNum_List)
                                            && !S21StringUtil.isEquals(xxResFltrFlg_Conf, ZYPConstant.FLG_ON_Y)) {
                                        glblMsg.B.no(i).xxResFltrFlg_LL.clear(); // 2018/01/25 S21_NA#19808 Mod
                                        break;
                                    }
                                }
                        }
                    } else {

                        // OUTBOUND
                        if (glblMsg.A.getValidCount() > 0) { // 2018/01/25 S21_NA#19808 Mod
                            ZYPTableUtil.clear(bk_glblMsg.B); // 2018/01/25 S21_NA#19808 Mod
                            ssmResult = NWAL1500QueryForSearch.getInstance().getCpoDtlViewInfo(queryParam, bk_glblMsg.B); // 2018/01/25 S21_NA#19808 Mod
                            for(int j = 0; j < glblMsg.B.getValidCount(); j++) { // 2018/01/25 S21_NA#19808 Mod
                                NWAL1500_BSMsg line_LineConfMsg = glblMsg.B.no(j); // 2018/01/25 S21_NA#19808 Mod
                                boolean equalsFlg = false;
                                for(int i = 0; i < bk_glblMsg.B.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                                    NWAL1500_BSMsg bk_line_LineConfMsg = bk_glblMsg.B.no(i); // 2018/01/25 S21_NA#19808 Mod
                                    if(S21StringUtil.isEquals(bk_line_LineConfMsg.xxLineNum_LL.getValue(), line_LineConfMsg.xxLineNum_LL.getValue())) {
                                        equalsFlg = true;
                                        break;
                                    }
                                }
                                if(!equalsFlg) {
                                    ZYPEZDItemValueSetter.setValue(line_LineConfMsg.xxResFltrFlg_LL, ZYPConstant.FLG_ON_Y);
                                }
                            }
                        }
                    }
                }
            }

            if(S21StringUtil.isEquals(TAB_RMA, bizMsg.xxDplyTab.getValue())) {

                boolean isShowInclLine = S21StringUtil.isEquals(bizMsg.xxShowInclLineFlg_FR.getValue(), ZYPConstant.FLG_ON_Y);

                if(isHideLine(isConfFilter_Rma(bizMsg), isShowInclLine, isLineFilter_Rma(bizMsg))) {
                    for(int i = 0; i < glblMsg.D.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                        ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).xxResFltrFlg_RL, ZYPConstant.FLG_ON_Y); // 2018/01/25 S21_NA#19808 Mod
                    }
                } else {
                    if(isConfFilter_Rma(bizMsg) && isShowInclLine && !isLineFilter_Rma(bizMsg)) {
                        for(int i = 0; i < glblMsg.D.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).xxResFltrFlg_RL, ZYPConstant.FLG_ON_Y); // 2018/01/25 S21_NA#19808 Mod
                        }
                        for(int i = 0; i < glblMsg.D.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                            String dsOrdPosnNum_List = glblMsg.D.no(i).dsOrdPosnNum_RL.getValue(); // 2018/01/25 S21_NA#19808 Mod
                            for(int j = 0; j < glblMsg.C.getValidCount(); j++) { // 2018/01/25 S21_NA#19808 Mod
                                String dsOrdPosnNum_Conf = glblMsg.C.no(j).dsOrdPosnNum_RC.getValue(); // 2018/01/25 S21_NA#19808 Mod
                                String xxResFltrFlg_Conf = glblMsg.C.no(j).xxResFltrFlg_RC.getValue(); // 2018/01/25 S21_NA#19808 Mod
                                    if (S21StringUtil.isEquals(dsOrdPosnNum_Conf, dsOrdPosnNum_List)
                                            && !S21StringUtil.isEquals(xxResFltrFlg_Conf, ZYPConstant.FLG_ON_Y)) {
                                        glblMsg.D.no(i).xxResFltrFlg_RL.clear(); // 2018/01/25 S21_NA#19808 Mod
                                        break;
                                    }
                                }
                        }
                    } else {

                        // INBOUND
                        if (glblMsg.C.getValidCount() > 0) { // 2018/01/25 S21_NA#19808 Mod
                            ZYPTableUtil.clear(bk_glblMsg.D); // 2018/01/25 S21_NA#19808 Mod
                            ssmResult = NWAL1500QueryForSearch.getInstance().getCpoRtrnDtlViewInfo(queryParam, bk_glblMsg.D); // 2018/01/25 S21_NA#19808 Mod
                            for(int j = 0; j < glblMsg.D.getValidCount(); j++) { // 2018/01/25 S21_NA#19808 Mod
                                NWAL1500_DSMsg line_RmaMsg = glblMsg.D.no(j); // 2018/01/25 S21_NA#19808 Mod
                                boolean equalsFlg = false;
                                for(int i = 0; i < bk_glblMsg.D.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                                    NWAL1500_DSMsg bk_line_RmaMsg = bk_glblMsg.D.no(i); // 2018/01/25 S21_NA#19808 Mod
                                    if(S21StringUtil.isEquals(bk_line_RmaMsg.xxLineNum_RL.getValue(), line_RmaMsg.xxLineNum_RL.getValue())) {
                                        equalsFlg = true;
                                        break;
                                    }
                                }
                                if(!equalsFlg) {
                                    ZYPEZDItemValueSetter.setValue(line_RmaMsg.xxResFltrFlg_RL, ZYPConstant.FLG_ON_Y);
                                }
                            }
                        }
                    }
                }
            }
        }
        // 2017/08/07 Sol#249 Add End

        /**************************************************
         * set edit flag.
         **************************************************/
        // 2016/10/13 S21_NA#7700 Add Start
        // For Performance QC#8166 Del Start
         ssmResult = NWAL1500QueryForSearch.getInstance().getOrderEntryEditFlag(bizMsg);
         if (!ssmResult.isCodeNotFound()) {
             List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
             Map<String, String> map = (Map<String, String>) resultList.get(0);
             ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryScrEdtblFlg, map.get("ORD_ENTRY_SCR_EDTBL_FLG"));
         }
        // For Performance QC#8166 Del End
        // 2016/10/13 S21_NA#7700 Add End

        /**************************************************
         * Ship To Customer Address Info (for Header Hidden)
         **************************************************/
        // S21_NA#6719 Del Start
        // ssmResult = NWAL1500QueryForCustomer.getInstance().getShipToCustAddr(bizMsg, bizMsg.shipToCustCd.getValue());
        // if (!ssmResult.isCodeNotFound()) {
        // Map<String, String> shipToCustAddrInfo = (Map<String, String>) ssmResult.getResultObject();
        // ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm, shipToCustAddrInfo.get("ADDL_LOC_NM"));
        // ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr, shipToCustAddrInfo.get("FIRST_LINE_ADDR"));
        // ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr, shipToCustAddrInfo.get("SCD_LINE_ADDR"));
        // ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr, shipToCustAddrInfo.get("THIRD_LINE_ADDR"));
        // ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr, shipToCustAddrInfo.get("FRTH_LINE_ADDR"));
        // ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstRefCmntTxt, shipToCustAddrInfo.get("FIRST_REF_CMNT_TXT"));
        // ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdRefCmntTxt, shipToCustAddrInfo.get("SCD_REF_CMNT_TXT"));
        // ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr, shipToCustAddrInfo.get("CTY_ADDR"));
        // ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd, shipToCustAddrInfo.get("ST_CD"));
        // ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd, shipToCustAddrInfo.get("POST_CD"));
        // ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm, shipToCustAddrInfo.get("PROV_NM"));
        // ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd, shipToCustAddrInfo.get("CTRY_CD"));
        // ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm, shipToCustAddrInfo.get("CNTY_NM"));
        // }
        // S21_NA#6719 Del End

        // 2016/02/10 QC#1616 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSrchNum, bizMsg.cpoOrdNum);
        // 2016/02/10 QC#1616 Add End

        // 2016/02/29 QC#1693/1700 Start
        getAllOrdCatgBizCtx(bizMsg);
        // 2016/02/29 QC#1693/1700 End

        getOrdEntryCancelAvailable(bizMsg);

        // 2018/01/25 S21_NA#19808 Add Start
        bizMsg.xxDplyTab.setValue(xxDplyTab);
        // Set First Page Data.
        if (glblMsg.B.getValidCount() > 0) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NWAL1500CommonLogic.setUomPullDownForConfLineFromCache(bizMsg, glblMsg.B.no(i), lineUomCacheMap); // For Performance QC#8166 Mod
                // NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, lineMsg); // 2016/03/15 S21_NA#4691 Add End S21_NA#7645 Del
            }
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, curLineConfigPageNum);
        }
        if (glblMsg.D.getValidCount() > 0) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                NWAL1500CommonLogic.setUomPullDownForRmaFromCache(bizMsg, glblMsg.D.no(i), rmaUomCacheMap); // For Performance QC#8166 Mod // 2018/01/25 S21_NA#19808
            }
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, curRmaPageNum);
        }
        // 2018/01/25 S21_NA#19808 Add End
    }
 
    // 2017/08/07 Sol#249 Add Start
    private static boolean isConfFilter_LineConf(NWAL1500CMsg bizMsg) {

        // Config Level Filter Value(Line Config TAB)
        if(ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxConfigIdSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxMdlSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxBillToAcctNmSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxBillToAcctCdSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxBillToLocCdSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxShipToAcctNmSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxShipToAcctCdSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxShipToLocCdSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxSoldToAcctNmSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxSoldToAcctCdSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxSoldToLocCdSrchTxt_FL)) {
            return true;
        }
        return false;
    }
    // 2017/08/07 Sol#249 Add End

    // 2017/08/07 Sol#249 Add Start
    private static boolean isConfFilter_Rma(NWAL1500CMsg bizMsg) {

        // Config Level Filter Value(RMA TAB)
        if(ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxConfigIdSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxMdlSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxBillToAcctNmSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxBillToAcctCdSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxBillToLocCdSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxShipToAcctNmSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxShipToAcctCdSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxShipToLocCdSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxSoldToAcctNmSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxSoldToAcctCdSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxSoldToLocCdSrchTxt_FR)) {
            return true;
        }
        return false;
    }
    // 2017/08/07 Sol#249 Add End

    // 2017/08/07 Sol#249 Add Start
    private static boolean isLineFilter_LineConf(NWAL1500CMsg bizMsg) {

        // Line Level Filter Value(Line Config TAB)
        if(ZYPCommonFunc.hasValue(bizMsg.xxLineNum_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxLineStsSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxOrdItemSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxRtlWhSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxRtlSwhSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxCpoSrcTpSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxOrdSrcRefNumSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxSbstItemSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxSerNumSrchTxt_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxShowInclCloLineFlg_FL)
                || ZYPCommonFunc.hasValue(bizMsg.xxShowInclCancLineFlg_FL)) {
            return true;
        }
        return false;
    }
    // 2017/08/07 Sol#249 Add End

    // 2017/08/07 Sol#249 Add Start
    private static boolean isLineFilter_Rma(NWAL1500CMsg bizMsg) {

        // Line Level Filter Value(RMA TAB)
        if(ZYPCommonFunc.hasValue(bizMsg.xxLineNum_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxLineStsSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxOrdItemSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxRtrnRsnSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxRtlWhSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxRtlSwhSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxOrdSrcRefNumSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxSerNumSrchTxt_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxShowInclCloLineFlg_FR)
                || ZYPCommonFunc.hasValue(bizMsg.xxShowInclCancLineFlg_FR)) {
            return true;
        }
        return false;
    }
    // 2017/08/07 Sol#249 Add End

    // 2017/08/07 Sol#249 Add Start
    private static boolean isHideLine(boolean isConfFilter, boolean isShowInclLine, boolean isLineFilter) {

        if (isConfFilter && !isShowInclLine && !isLineFilter) {
            return true;
        } else if (!isConfFilter && isShowInclLine && !isLineFilter) {
            return true;
        } else if (!isConfFilter && !isShowInclLine && isLineFilter) {
            return false;
        } else if (isConfFilter && isShowInclLine && !isLineFilter) {
            return false;
        } else if (isConfFilter && !isShowInclLine && isLineFilter) {
            return true;
        } else if (isConfFilter && isShowInclLine && isLineFilter) {
            return false;
        } else if (!isConfFilter && isShowInclLine && isLineFilter) {
            return false;
        }
        return true;
    }
    // 2017/08/07 Sol#249 Add End

    // 2017/08/07 Sol#249 Add Start
    /**
     * <pre>
     * set Filter Value Common.
     * @param params Map<String, String>
     * @param bizMsg bizMsg Business Message
     * </pre>
     */
    private static void setFilterValueCommon(Map<String, Object> queryParam, NWAL1500CMsg bizMsg) {

        queryParam.put("xxResFltrFlg", ZYPConstant.FLG_OFF_N);
        if(S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.xxResFltrFlg_FP.getValue())) {
            if(S21StringUtil.isEquals(TAB_LINE_CONFIG, bizMsg.xxDplyTab.getValue())) {
                queryParam.put("xxDplyTab", TAB_LINE_CONFIG);
            }
            if(S21StringUtil.isEquals(TAB_RMA, bizMsg.xxDplyTab.getValue())) {
                queryParam.put("xxDplyTab", TAB_RMA);
            }
            if(isConfFilter_LineConf(bizMsg)) {
                queryParam.put("xxConfig_LineConfigFlg", ZYPConstant.FLG_ON_Y);
            } else {
                queryParam.put("xxConfig_LineConfigFlg", ZYPConstant.FLG_OFF_N);
            }
            if(isConfFilter_Rma(bizMsg)) {
                queryParam.put("xxConfig_RmaFlg", ZYPConstant.FLG_ON_Y);
            } else {
                queryParam.put("xxConfig_RmaFlg", ZYPConstant.FLG_OFF_N);
            }
            if(isLineFilter_LineConf(bizMsg)) {
                queryParam.put("xxLine_LineConfigFlg", ZYPConstant.FLG_ON_Y);
            } else {
                queryParam.put("xxLine_LineConfigFlg", ZYPConstant.FLG_OFF_N);
            }
            if(isLineFilter_Rma(bizMsg)) {
                queryParam.put("xxLine_RmaFlg", ZYPConstant.FLG_ON_Y);
            } else {
                queryParam.put("xxLine_RmaFlg", ZYPConstant.FLG_OFF_N);
            }
            queryParam.put("xxConfigCatgCd_Out", CONFIG_CATG.OUTBOUND);
            queryParam.put("xxConfigCatgCd_In", CONFIG_CATG.INBOUND);
            if(S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.xxShowInclCloLineFlg_FL.getValue())){
                queryParam.put("xxShowInclCloLineFlg_FL", ZYPConstant.FLG_ON_Y);
            } else {
                queryParam.put("xxShowInclCloLineFlg_FL", ZYPConstant.FLG_OFF_N);
            }
            if(S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.xxShowInclCloLineFlg_FR.getValue())){
                queryParam.put("xxShowInclCloLineFlg_FR", ZYPConstant.FLG_ON_Y);
            } else {
                queryParam.put("xxShowInclCloLineFlg_FR", ZYPConstant.FLG_OFF_N);
            }
            if(S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.xxShowInclCancLineFlg_FL.getValue())){
                queryParam.put("xxShowInclCancLineFlg_FL", ZYPConstant.FLG_ON_Y);
            } else {
                queryParam.put("xxShowInclCancLineFlg_FL", ZYPConstant.FLG_OFF_N);
            }
            if(S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.xxShowInclCancLineFlg_FR.getValue())){
                queryParam.put("xxShowInclCancLineFlg_FR", ZYPConstant.FLG_ON_Y);
            } else {
                queryParam.put("xxShowInclCancLineFlg_FR", ZYPConstant.FLG_OFF_N);
            }

            setFilterValueConfigLevel_LineConfig(queryParam, bizMsg);   // Config Level Filter Value(Line Config TAB)
            setFilterValueLineLevel_LineConfig(queryParam, bizMsg);     // Line Level Filter Value(Line Config TAB)
            setFilterValueConfigLevel_Rma(queryParam, bizMsg);          // Config Level Filter Value(RMA TAB)
            setFilterValueLineLevel_Rma(queryParam, bizMsg);            // Line Level Filter Value(RMA TAB)
        }
    }
    // 2017/08/07 Sol#249 Add End

    // 2017/08/07 Sol#249 Add Start
    /**
     * <pre>
     * set Filter Value(Config Level for Line Config TAB).
     * @param params Map<String, Object>
     * @param bizMsg bizMsg Business Message
     * </pre>
     */
    private static void setFilterValueConfigLevel_LineConfig(Map<String, Object> params, NWAL1500CMsg bizMsg) {

        // Config Level Filter Value(Line Config TAB)
        params.put("dsOrdPosnNum_FL", bizMsg.dsOrdPosnNum_FL.getValue());
        params.put("xxConfigIdSrchTxt_FL", bizMsg.xxConfigIdSrchTxt_FL.getValue());
        params.put("xxMdlSrchTxt_FL", getSplitBySemicolon(bizMsg.xxMdlSrchTxt_FL.getValue()));
        params.put("xxBillToAcctNmSrchTxt_FL", bizMsg.xxBillToAcctNmSrchTxt_FL.getValue());
        params.put("xxBillToAcctCdSrchTxt_FL", bizMsg.xxBillToAcctCdSrchTxt_FL.getValue());
        params.put("xxBillToLocCdSrchTxt_FL", bizMsg.xxBillToLocCdSrchTxt_FL.getValue());
        params.put("xxShipToAcctNmSrchTxt_FL", bizMsg.xxShipToAcctNmSrchTxt_FL.getValue());
        params.put("xxShipToAcctCdSrchTxt_FL", bizMsg.xxShipToAcctCdSrchTxt_FL.getValue());
        params.put("xxShipToLocCdSrchTxt_FL", bizMsg.xxShipToLocCdSrchTxt_FL.getValue());
        params.put("xxSoldToAcctNmSrchTxt_FL", bizMsg.xxSoldToAcctNmSrchTxt_FL.getValue());
        params.put("xxSoldToAcctCdSrchTxt_FL", bizMsg.xxSoldToAcctCdSrchTxt_FL.getValue());
        params.put("xxSoldToLocCdSrchTxt_FL", bizMsg.xxSoldToLocCdSrchTxt_FL.getValue());
    }
    // 2017/08/07 Sol#249 Add End

    // 2017/08/07 Sol#249 Add Start
    /**
     * <pre>
     * set Filter Value(Config Level for RMA TAB).
     * @param params Map<String, String>
     * @param bizMsg bizMsg Business Message
     * </pre>
     */
    private static void setFilterValueConfigLevel_Rma(Map<String, Object> params, NWAL1500CMsg bizMsg) {

        // Config Level Filter Value(RMA TAB)
        params.put("dsOrdPosnNum_FR", bizMsg.dsOrdPosnNum_FR.getValue());
        params.put("xxConfigIdSrchTxt_FR", bizMsg.xxConfigIdSrchTxt_FR.getValue());
        params.put("xxMdlSrchTxt_FR", getSplitBySemicolon(bizMsg.xxMdlSrchTxt_FR.getValue()));
        params.put("xxBillToAcctNmSrchTxt_FR", bizMsg.xxBillToAcctNmSrchTxt_FR.getValue());
        params.put("xxBillToAcctCdSrchTxt_FR", bizMsg.xxBillToAcctCdSrchTxt_FR.getValue());
        params.put("xxBillToLocCdSrchTxt_FR", bizMsg.xxBillToLocCdSrchTxt_FR.getValue());
        params.put("xxShipToAcctNmSrchTxt_FR", bizMsg.xxShipToAcctNmSrchTxt_FR.getValue());
        params.put("xxShipToAcctCdSrchTxt_FR", bizMsg.xxShipToAcctCdSrchTxt_FR.getValue());
        params.put("xxShipToLocCdSrchTxt_FR", bizMsg.xxShipToLocCdSrchTxt_FR.getValue());
        params.put("xxSoldToAcctNmSrchTxt_FR", bizMsg.xxSoldToAcctNmSrchTxt_FR.getValue());
        params.put("xxSoldToAcctCdSrchTxt_FR", bizMsg.xxSoldToAcctCdSrchTxt_FR.getValue());
        params.put("xxSoldToLocCdSrchTxt_FR", bizMsg.xxSoldToLocCdSrchTxt_FR.getValue());
    }
    // 2017/08/07 Sol#249 Add End

    // 2017/08/07 Sol#249 Add Start
    /**
     * <pre>
     * set Filter Value(Line Level for Line Config TAB).
     * @param params Map<String, String>
     * @param bizMsg bizMsg Business Message
     * </pre>
     */
    private static void setFilterValueLineLevel_LineConfig(Map<String, Object> params, NWAL1500CMsg bizMsg) {

        // Line Level Filter Value(Line Config TAB)
        params.put("xxLineNum_FL", bizMsg.xxLineNum_FL.getValue());
        params.put("xxLineStsSrchTxt_FL", getSplitBySemicolon(bizMsg.xxLineStsSrchTxt_FL.getValue()));
        params.put("xxOrdItemSrchTxt_FL", getSplitBySemicolon(bizMsg.xxOrdItemSrchTxt_FL.getValue()));
        params.put("xxRtlWhSrchTxt_FL", bizMsg.xxRtlWhSrchTxt_FL.getValue());
        params.put("xxRtlSwhSrchTxt_FL", getSplitBySemicolon(bizMsg.xxRtlSwhSrchTxt_FL.getValue()));
        params.put("xxCpoSrcTpSrchTxt_FL", bizMsg.xxCpoSrcTpSrchTxt_FL.getValue());
        params.put("xxOrdSrcRefNumSrchTxt_FL", bizMsg.xxOrdSrcRefNumSrchTxt_FL.getValue());
        params.put("xxSbstItemSrchTxt_FL", bizMsg.xxSbstItemSrchTxt_FL.getValue());
        params.put("xxSerNumSrchTxt_FL", bizMsg.xxSerNumSrchTxt_FL.getValue());
    }
    // 2017/08/07 Sol#249 Add End

    // 2017/08/07 Sol#249 Add Start
    /**
     * <pre>
     * set Filter Value(Line Level for RMA TAB).
     * @param params Map<String, String>
     * @param bizMsg bizMsg Business Message
     * </pre>
     */
    private static void setFilterValueLineLevel_Rma(Map<String, Object> params, NWAL1500CMsg bizMsg) {

        // Line Level Filter Value(RMA TAB)
        params.put("xxLineNum_FR", bizMsg.xxLineNum_FR.getValue());
        params.put("xxLineStsSrchTxt_FR", getSplitBySemicolon(bizMsg.xxLineStsSrchTxt_FR.getValue()));
        params.put("xxOrdItemSrchTxt_FR", getSplitBySemicolon(bizMsg.xxOrdItemSrchTxt_FR.getValue()));
        params.put("xxRtrnRsnSrchTxt_FR", bizMsg.xxRtrnRsnSrchTxt_FR.getValue());
        params.put("xxRtlWhSrchTxt_FR", bizMsg.xxRtlWhSrchTxt_FR.getValue());
        params.put("xxRtlSwhSrchTxt_FR", getSplitBySemicolon(bizMsg.xxRtlSwhSrchTxt_FR.getValue()));
        params.put("xxOrdSrcRefNumSrchTxt_FR", bizMsg.xxOrdSrcRefNumSrchTxt_FR.getValue());
        params.put("xxSerNumSrchTxt_FR", bizMsg.xxSerNumSrchTxt_FR.getValue());
    }
    // 2017/08/07 Sol#249 Add End

    // 2017/08/07 Sol#249 Add Start
    /**
     * <pre>
     * get Split By Semicolon.
     * @param param String
     * return String[]
     * </pre>
     */
    private static String[] getSplitBySemicolon(String param) {
        String[] strList = param.split(";");
        if(strList.length == 0 | (strList.length == 1 && !ZYPCommonFunc.hasValue(strList[0]))) {
            return null;
        }
        return strList;
    }
    // 2017/08/07 Sol#249 Add End

    private void doProcess_NWAL1500Scrn00_CMN_Clear(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        doProcess_NWAL1500_INIT(bizMsg, glblMsg);
    }

    private void doProcess_NWAL1500Scrn00_CMN_Reset(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg);
        } else {
            doProcess_NWAL1500_INIT(bizMsg, glblMsg);
        }
    }

    private void doProcess_NWAL1500Scrn00_CMN_Save(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        // 2016/02/23 S21_NA#3239 Mod Start
        if (bizMsg.getMessageInfo() != null && bizMsg.getMessageInfo().getCode().endsWith("W")) {
            doNothing();
        } else {
            doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg);
        }
//        doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg);
        // 2016/02/23 S21_NA#3239 Mod End

    }

    private void doProcess_NWAL1500Scrn00_CMN_Submit(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        // 2016/02/26 S21_NA#1729 Mod Start
        if (bizMsg.getMessageInfo() != null && bizMsg.getMessageInfo().getCode().endsWith("W")) {
            doNothing();
        } else {
            doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg);
        }
        // 2016/02/26 S21_NA#1729 Mod End
    }

    /**
     * doProcess_NWAL1500Scrn00_TAB_Header
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     */
    private void doProcess_NWAL1500Scrn00_TAB_Header(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        NWAL1500CommonLogic.clearMeaninglesDetail(bizMsg, glblMsg);
//        NWAL1500CommonLogic.copyActionPulldownHeader(bizMsg, glblMsg);

        copyBizDataToGlobalData(bizMsg, glblMsg);
    }

    /**
     * doProcess_NWAL1500Scrn00_TAB_Addl_Header
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     */
    private void doProcess_NWAL1500Scrn00_TAB_Addl_Header(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        NWAL1500CommonLogic.clearMeaninglesDetail(bizMsg, glblMsg);
//        NWAL1500CommonLogic.copyActionPulldownHeader(bizMsg, glblMsg);
        copyBizDataToGlobalData(bizMsg, glblMsg);
    }

    private void doProcess_NWAL1500Scrn00_TAB_Line_Config(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
        if (!NWAL1500CommonLogic.checkMandatoryAddlHeaderTab(bizMsg)) {
            return;
        }
        
        // 2018/03/19 S21_NA#22459 Add Start
        if (bizMsg.P.getValidCount() == 0) {
            NWAL1500QueryForSearch.getInstance().getOrdLineValSetInfo(bizMsg.glblCmpyCd.getValue(), bizMsg.P);
        }
        // 2018/03/19 S21_NA#22459 Add End

        NWAL1500CommonLogic.clearMeaninglesDetailOfRma(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Add
        NWAL1500CommonLogic.clearInvisibleChkOfRma(bizMsg, glblMsg); // 2018/04/05 S21_NA#24098 Add
        if (glblMsg.A.getValidCount() == 0) { // 2018/01/25 S21_NA#19808 Mod
            // 2016/10/13 S21_NA#7700 Add Start
            final String hdrStsNm = bizMsg.ordHdrStsDescTxt.getValue();
            if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm)) {
                return;
            }
            if (ZYPCommonFunc.hasValue(bizMsg.ordEntryScrEdtblFlg)
                    && ZYPConstant.FLG_OFF_N.equals(bizMsg.ordEntryScrEdtblFlg.getValue())) {
                return;
            }
            // 2016/10/13 S21_NA#7700 Add End

            // Create New Configuration Line.
            NWAL1500_ASMsg configLineMsg = glblMsg.A.no(0); // 2018/01/25 S21_NA#19808 Mod
            createNewConfigLine(bizMsg, glblMsg, configLineMsg); // 2018/01/25 S21_NA#19808 Mod
            configLineMsg.xxPageNum_LC.setValue(1); // 2018/01/25 S21_NA#19808 Mod
            glblMsg.A.setValidCount(1); // 2018/01/25 S21_NA#19808 Mod

            // Create New Line.
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(0);
            lineMsg.dsOrdPosnNum_LL.setValue(configLineMsg.dsOrdPosnNum_LC.getValue());
            lineMsg.dsCpoLineNum_LL.setValue(1);
            lineMsg.xxLineNum_LL.setValue(NWAL1500CommonLogic.concatLineNum(lineMsg));
            lineMsg.prcBaseDt_LL.setValue(bizMsg.slsDt.getValue());
            // lineMsg.rddDt_LL.setValue(bizMsg.slsDt.getValue()); // S21_NA#5000#80
            lineMsg.rddDt_LL.setValue(bizMsg.addRddDt.getValue()); // S21_NA#5000#80
            // S21_NA#8450
            lineMsg.dsOrdLineCatgCd_LL.setValue(bizMsg.dsOrdLineCatgCd_LD.getValue());
            lineMsg.prcCatgNm_LL.setValue(bizMsg.prcCatgNm.getValue());
            lineMsg.flPrcListNm_LL.setValue(bizMsg.flPrcListNm.getValue());
            // ADD START 2015/12/16 #2013
            lineMsg.invQty_LL.setValue(BigDecimal.ZERO);
            // ADD END 2015/12/15 #2013
            // copyCsmpData(configLineMsg, lineMsg); // 2016/08/26 S21_NA#9806 Add
            copyCsmpData(bizMsg, glblMsg, configLineMsg, lineMsg); // QC#22371 2017/12/26 Mod // 2018/01/25 S21_NA#19808 Mod
            lineMsg.xxPageNum_LL.setValue(1); // 2018/01/25 S21_NA#19808 Add
            // 2018/08/02 S21_NA#26665 add start
            CPO_SRC_TPTMsg cpoSrcTpTMsg = new CPO_SRC_TPTMsg();
            ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.cpoSrcTpCd, CPO_SRC_TP.ORDER_ENTRY_SCREEN);

            cpoSrcTpTMsg = (CPO_SRC_TPTMsg) S21CodeTableAccessor.findByKey(cpoSrcTpTMsg);
            ZYPEZDItemValueSetter.setValue(lineMsg.cpoSrcTpDescTxt_LL, cpoSrcTpTMsg.cpoSrcTpDescTxt);
            // 2018/08/02 S21_NA#26665 add end
            glblMsg.B.setValidCount(1); // 2018/01/25 S21_NA#19808 Mod

            // QC#22371 2017/12/26 Add Start
            EZDMsg.copy(configLineMsg, null, bizMsg.A.no(0),null);
            bizMsg.A.setValidCount(1);

            EZDMsg.copy(lineMsg, null, bizMsg.B.no(0),null);
            bizMsg.B.setValidCount(1);
            // QC#22371 2017/12/26 Add Start
            // QC743
            // NWAL1500_JSMsg lineJMsg = glblMsg.J.no(0);
            // EZDMsg.copy(lineMsg, null, lineJMsg, null);
            // glblMsg.J.setValidCount(1);

            // 2018/01/25 S21_NA#19808 Add Start
            bizMsg.dsOrdPosnNum_AS.setValue("1");
            bizMsg.dsOrdPosnNum_AT.setValue("1");
            bizMsg.xxPageShowFromNum_LL.setValue(1);
            bizMsg.xxPageShowToNum_LL.setValue(1);
            bizMsg.xxPageShowOfNum_LL.setValue(1);
            bizMsg.xxPageShowCurNum_LL.setValue(1);
            // 2018/01/25 S21_NA#19808 Add End
        } else {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                NWAL1500_ASMsg configLineMsg = glblMsg.A.no(i);  // 2018/01/25 S21_NA#19808 Mod
                if (!ZYPCommonFunc.hasValue(configLineMsg.shipToCustCd_LC)) {
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustCd_LC, bizMsg.shipToCustCd);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.dropShipFlg_LC, bizMsg.dropShipFlg);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToLocNm_LC, bizMsg.shipToLocNm);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToAddlLocNm_LC, bizMsg.shipToAddlLocNm);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstLineAddr_LC, bizMsg.shipToFirstLineAddr);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdLineAddr_LC, bizMsg.shipToScdLineAddr);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToThirdLineAddr_LC, bizMsg.shipToThirdLineAddr);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFrthLineAddr_LC, bizMsg.shipToFrthLineAddr);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstRefCmntTxt_LC, bizMsg.shipToFirstRefCmntTxt);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdRefCmntTxt_LC, bizMsg.shipToScdRefCmntTxt);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtyAddr_LC, bizMsg.shipToCtyAddr);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToStCd_LC, bizMsg.shipToStCd);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToPostCd_LC, bizMsg.shipToPostCd);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtryCd_LC, bizMsg.shipToCtryCd);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCntyNm_LC, bizMsg.shipToCntyNm);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToProvNm_LC, bizMsg.shipToProvNm);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctCd_LC, bizMsg.shipToCustAcctCd); // 2016/03/05 S21_NA#5000#85 Add
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctNm_LC, bizMsg.shipToCustAcctNm); // 2016/03/05 S21_NA#5000#85 Add
                    // 2017/12/08 S21_NA#21621 Add Start
                    ZYPEZDItemValueSetter.setValue(configLineMsg.addShipToLocNm_LC, bizMsg.addShipToLocNm);
                    // 2017/12/08 S21_NA#21621 Add End
                }

                if (!ZYPCommonFunc.hasValue(configLineMsg.billToCustCd_LC)) {
                    ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustCd_LC, bizMsg.billToCustCd);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctCd_LC, bizMsg.billToCustAcctCd); // 2016/03/05 S21_NA#5000#85 Add
                    ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctNm_LC, bizMsg.billToCustAcctNm); // 2016/03/05 S21_NA#5000#85 Add
                }

                // Copy Sales Credit
                if (!NWAL1500CommonLogicForSalesCredit.isExistsSlsCr(bizMsg, configLineMsg)) {
                    NWAL1500CommonLogicForSalesCredit.copyHdrSlsCrInfo(bizMsg, configLineMsg);
                }

                NWAL1500CommonLogic.setConfigNewFlg(bizMsg, configLineMsg);
                // 2018/05/20 S21_NA#25604 Add Start
                // 2019/12/13 S21_NA#53013 Mod Start
                if (CPO_SRC_TP.DEAL_CONFIGURATOR.equals(bizMsg.cpoSrcTpCd.getValue())) {
                    // Skip Model Derivation
                } else {
                    NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, configLineMsg);
                }
                // 2019/12/13 S21_NA#53013 Mod End
                // 2018/05/20 S21_NA#25604 Add End
            }

            for (int i = 0; i < glblMsg.B.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i); // 2018/01/25 S21_NA#19808 Mod
                if (!ZYPCommonFunc.hasValue(lineMsg.prcCatgNm_LL)) {
                    ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgNm_LL, bizMsg.prcCatgNm);
                }
                if (!ZYPCommonFunc.hasValue(lineMsg.flPrcListNm_LL)) {
                    ZYPEZDItemValueSetter.setValue(lineMsg.flPrcListNm_LL, bizMsg.flPrcListNm);
                }
            }
            // 2018/01/25 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_LL.getValueInt());
            // 2018/01/25 S21_NA#19808 Add Start
        }

        // QC743
        // if (bizMsg.B.getValidCount() == 1 && !ZYPCommonFunc.hasValue(bizMsg.B.no(0).mdseCd_LL)) {
        //    EZDMsg.copy(bizMsg.B, null, glblMsg.J, null);
        // }

//        NWAL1500CommonLogic.copyActionPulldownLine(bizMsg, glblMsg);

        // 2018/01/25 S21_NA#19808 Del Start
        /// For Performance QC#11619,13026 Add Start
//        bizMsg.setSkipCommitSMsg(true);
        // For Performance QC#11619,13026 Add End
        // 2018/01/25 S21_NA#19808 Del end
    }

    private void doProcess_NWAL1500Scrn00_TAB_RMA(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
        if (!NWAL1500CommonLogic.checkMandatoryAddlHeaderTab(bizMsg)) {
            return;
        }

        NWAL1500CommonLogic.clearMeaninglesDetailOfLineConfig(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Add
        NWAL1500CommonLogic.clearInvisibleChkOfLineConfig(bizMsg, glblMsg); // 2018/04/05 S21_NA#24098 Add
        if (glblMsg.C.getValidCount() == 0) {  // 2018/01/25 S21_NA#19808 Mod

            final String hdrStsNm = bizMsg.ordHdrStsDescTxt.getValue();
            if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm)) {
                return;
            }
            if (ZYPCommonFunc.hasValue(bizMsg.ordEntryScrEdtblFlg)
                    && ZYPConstant.FLG_OFF_N.equals(bizMsg.ordEntryScrEdtblFlg.getValue())) {
                return;
            }

            // Create New Configuration Line.
            NWAL1500_CSMsg configLineMsg = glblMsg.C.no(0);  // 2018/01/25 S21_NA#19808 Mod
            createNewConfigLine(bizMsg, glblMsg, configLineMsg);  // 2018/01/25 S21_NA#19808 Mod
            configLineMsg.xxPageNum_RC.setValue(1); // 2018/01/25 S21_NA#19808 Mod
            glblMsg.C.setValidCount(1); // 2018/01/25 S21_NA#19808 Mod

            // Create New Line.
            NWAL1500_DSMsg lineMsg = glblMsg.D.no(0); // 2018/01/25 S21_NA#19808 Mod
            lineMsg.dsOrdPosnNum_RL.setValue(configLineMsg.dsOrdPosnNum_RC.getValue());
            lineMsg.dsCpoLineNum_RL.setValue(1);
            lineMsg.xxLineNum_RL.setValue(NWAL1500CommonLogic.concatLineNum(lineMsg));
            lineMsg.prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
            lineMsg.rqstPickUpDt_RL.setValue(bizMsg.slsDt.getValue());
            // lineMsg.rqstPickUpDt_RL.setValue(bizMsg.slsDt.getValue()); // S21_NA#5000#80
            lineMsg.rqstPickUpDt_RL.setValue(bizMsg.addRddDt.getValue()); // S21_NA#5000#80
            // S21_NA#8450
            lineMsg.dsOrdLineCatgCd_RL.setValue(bizMsg.dsOrdLineCatgCd_RD.getValue());
            lineMsg.prcCatgNm_RL.setValue(bizMsg.prcCatgNm.getValue());
            lineMsg.flPrcListNm_RL.setValue(bizMsg.flPrcListNm.getValue());
            // ADD START 2015/12/16 #2013
            lineMsg.rtrnQty_RL.setValue(BigDecimal.ZERO);
            // ADD END 2015/12/15 #2013
            lineMsg.cancQty_RL.setValue(BigDecimal.ZERO);
            // copyCsmpData(configLineMsg, lineMsg); // 2016/08/26 S21_NA#9806 Add
            copyCsmpData(bizMsg, configLineMsg, lineMsg); // QC#22371 2017/12/26 Mod 
            lineMsg.xxPageNum_RL.setValue(1); // 2018/01/25 S21_NA#19808 Add
            // 2018/08/02 S21_NA#26665 add start
            CPO_SRC_TPTMsg cpoSrcTpTMsg = new CPO_SRC_TPTMsg();
            ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.cpoSrcTpCd, CPO_SRC_TP.ORDER_ENTRY_SCREEN);

            cpoSrcTpTMsg = (CPO_SRC_TPTMsg) S21CodeTableAccessor.findByKey(cpoSrcTpTMsg);
            ZYPEZDItemValueSetter.setValue(lineMsg.cpoSrcTpDescTxt_RL, cpoSrcTpTMsg.cpoSrcTpDescTxt);
            // 2018/08/02 S21_NA#26665 add end
            glblMsg.D.setValidCount(1); // 2018/01/25 S21_NA#19808 Mod

            // QC743
            // NWAL1500_KSMsg lineKMsg = glblMsg.K.no(0);
            // EZDMsg.copy(lineMsg, null, lineKMsg, null);
            // glblMsg.K.setValidCount(1);

            // 2018/01/25 S21_NA#19808 Add Start
            EZDMsg.copy(configLineMsg, null, bizMsg.C.no(0), null);
            bizMsg.C.setValidCount(1);

            EZDMsg.copy(lineMsg, null, bizMsg.D.no(0), null);
            bizMsg.D.setValidCount(1);

            bizMsg.dsOrdPosnNum_CS.setValue("1");
            bizMsg.dsOrdPosnNum_CT.setValue("1");
            bizMsg.xxPageShowFromNum_RL.setValue(1);
            bizMsg.xxPageShowToNum_RL.setValue(1);
            bizMsg.xxPageShowOfNum_RL.setValue(1);
            bizMsg.xxPageShowCurNum_RL.setValue(1);
            // 2018/01/25 S21_NA#19808 Add End

        } else {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                NWAL1500_CSMsg configLineMsg = glblMsg.C.no(i); // 2018/01/25 S21_NA#19808 Mod
                if (!ZYPCommonFunc.hasValue(configLineMsg.shipToCustCd_RC)) {
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustCd_RC, bizMsg.shipToCustCd);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.dropShipFlg_RC, bizMsg.dropShipFlg);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToLocNm_RC, bizMsg.shipToLocNm);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToAddlLocNm_RC, bizMsg.shipToAddlLocNm);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstLineAddr_RC, bizMsg.shipToFirstLineAddr);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdLineAddr_RC, bizMsg.shipToScdLineAddr);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToThirdLineAddr_RC, bizMsg.shipToThirdLineAddr);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFrthLineAddr_RC, bizMsg.shipToFrthLineAddr);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstRefCmntTxt_RC, bizMsg.shipToFirstRefCmntTxt);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdRefCmntTxt_RC, bizMsg.shipToScdRefCmntTxt);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtyAddr_RC, bizMsg.shipToCtyAddr);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToStCd_RC, bizMsg.shipToStCd);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToPostCd_RC, bizMsg.shipToPostCd);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtryCd_RC, bizMsg.shipToCtryCd);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCntyNm_RC, bizMsg.shipToCntyNm);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToProvNm_RC, bizMsg.shipToProvNm);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctCd_RC, bizMsg.shipToCustAcctCd); // 2016/03/05 S21_NA#5000#85 Add
                    ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctNm_RC, bizMsg.shipToCustAcctNm); // 2016/03/05 S21_NA#5000#85 Add
                    // 2017/12/08 S21_NA#21621 Add Start
                    ZYPEZDItemValueSetter.setValue(configLineMsg.addShipToLocNm_RC, bizMsg.addShipToLocNm);
                    // 2017/12/08 S21_NA#21621 Add End
                }

                if (!ZYPCommonFunc.hasValue(configLineMsg.billToCustCd_RC)) {
                    ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustCd_RC, bizMsg.billToCustCd);
                    ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctCd_RC, bizMsg.billToCustAcctCd); // 2016/03/05 S21_NA#5000#85 Add
                    ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctNm_RC, bizMsg.billToCustAcctNm); // 2016/03/05 S21_NA#5000#85 Add
                }

                // Copy Sales Credit
                if (!NWAL1500CommonLogicForSalesCredit.isExistsSlsCr(bizMsg, configLineMsg)) {
                    NWAL1500CommonLogicForSalesCredit.copyHdrSlsCrInfo(bizMsg, configLineMsg);
                }

                NWAL1500CommonLogic.setRmaConfigNewFlg(bizMsg, configLineMsg);
            }

            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                NWAL1500_DCMsg lineMsg = bizMsg.D.no(i);
                if (!ZYPCommonFunc.hasValue(lineMsg.prcCatgNm_RL)) {
                    ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgNm_RL, bizMsg.prcCatgNm);
                }
                if (!ZYPCommonFunc.hasValue(lineMsg.flPrcListNm_RL)) {
                    ZYPEZDItemValueSetter.setValue(lineMsg.flPrcListNm_RL, bizMsg.flPrcListNm);
                }
            }
            // 2018/01/25 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_RL.getValueInt());
            // 2018/01/25 S21_NA#19808 Add Start
        }

        // QC743
        // if (bizMsg.D.getValidCount() == 1 && !ZYPCommonFunc.hasValue(bizMsg.D.no(0).mdseCd_RL)) {
        //     EZDMsg.copy(bizMsg.D, null, glblMsg.K, null);
        // }

//        NWAL1500CommonLogic.copyActionPulldownLine(bizMsg, glblMsg);

        // 2018/01/25 S21_NA#19808 Del Start
//        /// For Performance QC#11619,13026 Add Start
//        bizMsg.setSkipCommitSMsg(true);
//        // For Performance QC#11619,13026 Add End
        // 2018/01/25 S21_NA#19808 Del End
    }

    private void doProcess_NWAL1500Scrn00_Line_Config_Add(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2018/08/02 S21_NA#26665 add start
        CPO_SRC_TPTMsg cpoSrcTpTMsg = new CPO_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.cpoSrcTpCd, CPO_SRC_TP.ORDER_ENTRY_SCREEN);

        cpoSrcTpTMsg = (CPO_SRC_TPTMsg) S21CodeTableAccessor.findByKey(cpoSrcTpTMsg);
        // 2018/08/02 S21_NA#26665 add end

        // 2018/01/25 S21_NA#19808 Mod bizMsg => glblMsg
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            if (glblMsg.A.length() < glblMsg.A.getValidCount() + 1) {
                bizMsg.setMessageInfo(NWAM0100E);
                return;
            }

            // 2018/01/25 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            // 2018/01/25 S21_NA#19808 Add End

            // Create New Configuration Line.
            NWAL1500_ASMsg configLineMsg = glblMsg.A.no(glblMsg.A.getValidCount());
            createNewConfigLine(bizMsg, glblMsg, configLineMsg); // 2018/01/25 S21_NA#19808 Mod
            glblMsg.A.setValidCount(glblMsg.A.getValidCount() + 1);

            if (glblMsg.B.length() < glblMsg.B.getValidCount() + 1) { // 2018/01/25 S21_NA#19808 Mod
                bizMsg.setMessageInfo(NWAM0100E);
                return;
            }

            // Create New Line.
            // 2018/01/25 S21_NA#19808 Add Start
            int dtlIdx = glblMsg.B.getValidCount();
            // 2018/01/25 S21_NA#19808 Add End
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(glblMsg.B.getValidCount()); // 2018/01/25 S21_NA#19808 Mod
            lineMsg.dsOrdPosnNum_LL.setValue(configLineMsg.dsOrdPosnNum_LC.getValue());
            lineMsg.dsCpoLineNum_LL.setValue(1);
            lineMsg.xxLineNum_LL.setValue(NWAL1500CommonLogic.concatLineNum(lineMsg));
            // S21_NA#8450
            lineMsg.dsOrdLineCatgCd_LL.setValue(bizMsg.dsOrdLineCatgCd_LD.getValue());
            lineMsg.prcBaseDt_LL.setValue(bizMsg.slsDt.getValue());
            // lineMsg.rddDt_LL.setValue(bizMsg.slsDt.getValue()); // S21_NA#5000#80
            lineMsg.rddDt_LL.setValue(bizMsg.addRddDt.getValue()); // S21_NA#5000#80
            lineMsg.prcCatgNm_LL.setValue(bizMsg.prcCatgNm.getValue());
            lineMsg.flPrcListNm_LL.setValue(bizMsg.flPrcListNm.getValue());
            // ADD START 2015/12/16 #2013
            lineMsg.invQty_LL.setValue(BigDecimal.ZERO);
            // ADD END 2015/12/15 #2013
            // 2018/08/02 S21_NA#26665 add start
            ZYPEZDItemValueSetter.setValue(lineMsg.cpoSrcTpDescTxt_LL, cpoSrcTpTMsg.cpoSrcTpDescTxt);
            // 2018/08/02 S21_NA#26665 add end
            // copyCsmpData(configLineMsg, lineMsg); // 2016/08/26 S21_NA#9806 Add
            copyCsmpData(bizMsg, glblMsg, configLineMsg, lineMsg); // QC#22371 2017/12/26 Mod // 2018/01/25 S21_NA#19808 Mod
            glblMsg.B.setValidCount(glblMsg.B.getValidCount() + 1);

            // 2018/01/25 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            int pageNum = NWAL1500CommonLogicForPagination.getNewLinePage(bizMsg, lineMsg, dtlIdx);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, pageNum);
            // 2018/01/25 S21_NA#19808 Add End
            // QC743
            // EZDMsg.copy(lineMsg, null,
            // glblMsg.J.no(glblMsg.J.getValidCount()), null);
            // glblMsg.J.setValidCount(glblMsg.J.getValidCount() + 1);

        } else {
            if (glblMsg.C.length() < glblMsg.C.getValidCount() + 1) { // 2018/01/25 S21_NA#19808 Mod
                bizMsg.setMessageInfo(NWAM0100E);
                return;
            }

            // 2018/01/25 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            // 2018/01/25 S21_NA#19808 Add End

            // Create New Configuration Line.
            NWAL1500_CSMsg configLineMsg = glblMsg.C.no(glblMsg.C.getValidCount()); // 2018/01/25 S21_NA#19808 Mod
            createNewConfigLine(bizMsg, glblMsg, configLineMsg); // 2018/01/25 S21_NA#19808 Mod
            glblMsg.C.setValidCount(glblMsg.C.getValidCount() + 1);  // 2018/01/25 S21_NA#19808 Mod

            if (glblMsg.D.length() < glblMsg.D.getValidCount() + 1) {  // 2018/01/25 S21_NA#19808 Mod
                bizMsg.setMessageInfo(NWAM0100E);
                return;
            }

            // Create New Line.
            // 2018/01/25 S21_NA#19808 Add Start
            int dtlIdx = glblMsg.D.getValidCount();
            // 2018/01/25 S21_NA#19808 Add End
            NWAL1500_DSMsg lineMsg = glblMsg.D.no(glblMsg.D.getValidCount());  // 2018/01/25 S21_NA#19808 Mod
            lineMsg.dsOrdPosnNum_RL.setValue(configLineMsg.dsOrdPosnNum_RC.getValue());
            lineMsg.dsCpoLineNum_RL.setValue(1);
            lineMsg.xxLineNum_RL.setValue(NWAL1500CommonLogic.concatLineNum(lineMsg));
            // S21_NA#8450
            lineMsg.dsOrdLineCatgCd_RL.setValue(bizMsg.dsOrdLineCatgCd_RD.getValue());
            lineMsg.prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
            // lineMsg.rqstPickUpDt_RL.setValue(bizMsg.slsDt.getValue()); // S21_NA#5000#80
            lineMsg.rqstPickUpDt_RL.setValue(bizMsg.addRddDt.getValue()); // S21_NA#5000#80
            lineMsg.prcCatgNm_RL.setValue(bizMsg.prcCatgNm.getValue());
            lineMsg.flPrcListNm_RL.setValue(bizMsg.flPrcListNm.getValue());
            // ADD START 2015/12/16 #2013
            lineMsg.rtrnQty_RL.setValue(BigDecimal.ZERO);
            // ADD END 2015/12/15 #2013
            lineMsg.cancQty_RL.setValue(BigDecimal.ZERO);
            // 2018/08/01 S21_NA#26414 add start
            // 2018/08/02 S21_NA#26665 add start
            ZYPEZDItemValueSetter.setValue(lineMsg.cpoSrcTpDescTxt_RL, cpoSrcTpTMsg.cpoSrcTpDescTxt);
            // 2018/08/02 S21_NA#26665 add end
            if (NWXC150001DsCheck.isAvalOrderCtxType( //
                    bizMsg.glblCmpyCd.getValue(), //
                    ORD_CATG_CTX_TP.SERVICE_EXCHANGE_ORDER_VALUE_SET, //
                    bizMsg.dsOrdCatgCd.getValue(), //
                    bizMsg.dsOrdTpCd.getValue(), //
                    bizMsg.dsOrdRsnCd.getValue())) {
                String defaultRsnCd = ZYPCodeDataUtil.getVarCharConstValue(SERVICE_EXCHANGE_RTRN_RSN_CD, bizMsg.glblCmpyCd.getValue());
                if (ZYPCommonFunc.hasValue(defaultRsnCd)) {
                    lineMsg.rtrnRsnCd_RL.setValue(defaultRsnCd);
                }
            }
            // 2018/08/01 S21_NA#26414 add end

            // copyCsmpData(configLineMsg, lineMsg); // 2016/08/26 S21_NA#9806 Add
            copyCsmpData(bizMsg, configLineMsg, lineMsg); // QC#22371 2017/12/26 Mod  // 2018/01/25 S21_NA#19808 Mod
            glblMsg.D.setValidCount(glblMsg.D.getValidCount() + 1);

            // 2018/01/25 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            int pageNum = NWAL1500CommonLogicForPagination.getNewLinePage(bizMsg, lineMsg, dtlIdx);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, pageNum);
            // 2018/01/25 S21_NA#19808 Add End
            // QC743
            // EZDMsg.copy(lineMsg, null,
            // glblMsg.K.no(glblMsg.K.getValidCount()), null);
            // glblMsg.K.setValidCount(glblMsg.K.getValidCount() + 1);
        }
    }

    private void doProcess_NWAL1500Scrn00_Line_Add(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2018/08/02 S21_NA#26665 add start
        CPO_SRC_TPTMsg cpoSrcTpTMsg = new CPO_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.cpoSrcTpCd, CPO_SRC_TP.ORDER_ENTRY_SCREEN);

        cpoSrcTpTMsg = (CPO_SRC_TPTMsg) S21CodeTableAccessor.findByKey(cpoSrcTpTMsg);
        // 2018/08/02 S21_NA#26665 add end
        boolean isContrSupOrd = isContrSupOrd(bizMsg); // 2019/02/22 S21_NA#30449 Add

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {

            // Line Config TAB

            if (glblMsg.B.length() < glblMsg.B.getValidCount() + 1) { // 2018/01/25 S21_NA#19808 Mod
                bizMsg.setMessageInfo(NWAM0100E);
                return;
            }

            // 2018/01/25 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            // 2018/01/25 S21_NA#19808 Add End

            // QC743
            // if (glblMsg.J.length() < glblMsg.J.getValidCount() + 1) {
            //     bizMsg.setMessageInfo(NWAM0100E);
            //     return;
            // }

            List<Integer> selectRows = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_LC", "Y");

            if (selectRows.size() == 0 && glblMsg.A.getValidCount() == 1) {
                selectRows.add(0);
            }

            // 2019/07/18 S21_NA#51327 Add Start
//            if (DS_ORD_CATG.RENTAL_CSA.equals(glblMsg.dsOrdCatgCd.getValue())) {
//                for (Integer selectRow : selectRows) {
//                    NWAL1500_ASMsg configLineMsg = glblMsg.A.no(selectRow);
//
//                    if (checkCreatedContract(bizMsg, configLineMsg)) {
//                    	configLineMsg.xxChkBox_LC.setErrorInfo(1, NWAM0970E);
//                        NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
//                        NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, configLineMsg.xxPageNum_LC.getValueInt());
//                        return;
//                    }
//                }
//            }
            // 2019/07/18 S21_NA#51327 Add End

            for (Integer selectRow : selectRows) {

                NWAL1500_ASMsg configLineMsg = glblMsg.A.no(selectRow);
                if (ZYPConstant.FLG_ON_Y.equals(configLineMsg.xxSmryLineFlg_L.getValue())) {
                    continue;
                }
                String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_LC.getValue();

                // store B to J by configuration.
                // QC743
                // NWAL1500CommonLogicForLineControl.storeLineByConfig(glblMsg.J, bizMsg.B, dsOrdPosnNum);

                // get max line number by configuration for J
                // QC743
                // int maxLineNum = NWAL1500CommonLogicForLineControl.getMaxLineNum(glblMsg.J, dsOrdPosnNum);
                int maxLineNum = NWAL1500CommonLogicForLineControl.getMaxLineNum(glblMsg.B, dsOrdPosnNum); // 2018/01/25 S21_NA#19808 Mod

                // add row index by configuration for J
                // QC743
                // int addLineRowForJ = NWAL1500CommonLogicForLineControl.getAddLineRow(glblMsg.J, dsOrdPosnNum);
                int addLineRowForB = NWAL1500CommonLogicForLineControl.getAddLineRow(glblMsg.B, dsOrdPosnNum);

                // insert and get new line for J
                // QC743
                // NWAL1500_JSMsg addLineForJ = (NWAL1500_JSMsg) NWAL1500CommonLogicForLineControl.insertNewLine(glblMsg.J, addLineRowForJ);
                NWAL1500_BSMsg addLineForB = (NWAL1500_BSMsg) NWAL1500CommonLogicForLineControl.insertNewLine(glblMsg.B, addLineRowForB); // 2018/01/25 S21_NA#19808 Mod
                if (addLineForB != null) {

                    // set up new line for J
                    addLineForB.dsOrdPosnNum_LL.setValue(dsOrdPosnNum);
                    addLineForB.dsCpoLineNum_LL.setValue(maxLineNum + 1);
                    addLineForB.xxLineNum_LL.setValue(NWAL1500CommonLogic.concatLineNum(addLineForB));
                    // S21_NA#8450
                    addLineForB.dsOrdLineCatgCd_LL.setValue(bizMsg.dsOrdLineCatgCd_LD.getValue());
                    addLineForB.prcBaseDt_LL.setValue(bizMsg.slsDt.getValue());
                    // addLineForJ.rddDt_LL.setValue(bizMsg.slsDt.getValue()); // S21_NA#5000#80
                    addLineForB.rddDt_LL.setValue(bizMsg.addRddDt.getValue()); // S21_NA#5000#80
                    addLineForB.prcCatgNm_LL.setValue(bizMsg.prcCatgNm.getValue());
                    addLineForB.flPrcListNm_LL.setValue(bizMsg.flPrcListNm.getValue());
                    // addLineForB.dlrRefNum_LL.setValue(bizMsg.dlrRefNum.getValue()); 2016/08/26 S21_NA#9806 Del
                    // addLineForB.csmpContrNum_LL.setValue(bizMsg.csmpContrNum.getValue()); 2016/08/26 S21_NA#9806 Del
                    addLineForB.invQty_LL.setValue(BigDecimal.ZERO); // S21_NA#2013
                    // Add 2016/03/07 S21_NA#5000#78 Start
                    ZYPEZDItemValueSetter.setValue(addLineForB.custIssPoNum_LL, bizMsg.custIssPoNum);
                    ZYPEZDItemValueSetter.setValue(addLineForB.custIssPoDt_LL, bizMsg.custIssPoDt);
                    // 2018/08/02 S21_NA#26665 mod start
//                    ZYPEZDItemValueSetter.setValue(addLineForB.cpoSrcTpDescTxt_LL, bizMsg.cpoSrcTpDescTxt);
                    ZYPEZDItemValueSetter.setValue(addLineForB.cpoSrcTpDescTxt_LL, cpoSrcTpTMsg.cpoSrcTpDescTxt);
                    // 2018/08/02 S21_NA#26665 mod end
                    ZYPEZDItemValueSetter.setValue(addLineForB.ordSrcRefNum_LL, bizMsg.ordSrcRefNum);
                    // Add 2016/03/07 S21_NA#5000#78 End
                    // copyCsmpData(configLineMsg, addLineForB); // 2016/08/26 S21_NA#9806 Add
                    // 2018/01/25 S21_NA#19808 Add Start
                    NWAL1500_ASMsg configLineSMsg = new NWAL1500_ASMsg();
                    EZDMsg.copy(configLineMsg, null, configLineSMsg, null);
                    // 2018/01/25 S21_NA#19808 Add End
                    copyCsmpData(bizMsg, glblMsg, configLineSMsg, addLineForB); // QC#22371 2017/12/26 Mod // 2018/01/25 S21_NA#19808 Mod
                    // 2019/02/22 S21_NA#30449 Add Start
                    if (isContrSupOrd) {
                        setContrData(glblMsg.B, dsOrdPosnNum, addLineForB);
                    }
                    // 2019/02/22 S21_NA#30449 Add End
                    // 2018/01/25 S21_NA#19808 Add Start
                    NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
                    int pageNum = NWAL1500CommonLogicForPagination.getNewLinePage(bizMsg, glblMsg, addLineForB);
                    NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, pageNum);
                    // 2018/01/25 S21_NA#19808 Add End
                 // QC743
                 // // add row index by configuration for B
                 // int addLineRowForB = NWAL1500CommonLogicForLineControl.getAddLineRow(bizMsg.B, dsOrdPosnNum);
                 // 
                 // // insert and get new line for B
                 // NWAL1500_BCMsg addLineForB = (NWAL1500_BCMsg) NWAL1500CommonLogicForLineControl.insertNewLine(bizMsg.B, addLineRowForB);
                 // 
                 // // copy J to B
                 // if (addLineForB != null) {
                 //     EZDMsg.copy(addLineForJ, null, addLineForB, null);
                 // }
                }
            }

        } else {

            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            // RMA tab

            if (glblMsg.D.length() < glblMsg.D.getValidCount() + 1) {
                bizMsg.setMessageInfo(NWAM0100E);
                return;
            }

            // 2018/01/25 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            // 2018/01/25 S21_NA#19808 Add End

            // QC743
            // if (glblMsg.K.length() < glblMsg.K.getValidCount() + 1) {
            //     bizMsg.setMessageInfo(NWAM0100E);
            //     return;
            // }

            List<Integer> selectRows = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", "Y");
            if (selectRows.size() == 0 && glblMsg.C.getValidCount() == 1) {
                selectRows.add(0);
            }

            for (Integer selectRow : selectRows) {

                NWAL1500_CSMsg configLineMsg = glblMsg.C.no(selectRow);
                if (ZYPConstant.FLG_ON_Y.equals(configLineMsg.xxSmryLineFlg_R.getValue())) {
                    continue;
                }
                String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_RC.getValue();

                // store D to K by configuration.
                // QC743
                // NWAL1500CommonLogicForLineControl.storeLineByConfig(glblMsg.K, bizMsg.D, dsOrdPosnNum);
                // NWAL1500CommonLogicForLineControl.storeLineByConfig(glblMsg.K, bizMsg.D, dsOrdPosnNum);

                // add row index by configuration for K
                // QC743
                // int addLineRowForK = NWAL1500CommonLogicForLineControl.getAddLineRow(glblMsg.K, dsOrdPosnNum);
                int addLineRowForK = NWAL1500CommonLogicForLineControl.getAddLineRow(glblMsg.D, dsOrdPosnNum); // 2018/01/25 S21_NA#19808 Mod

                // get max line number by configuration for K
                // QC743
                // int maxLineNum = NWAL1500CommonLogicForLineControl.getMaxLineNum(glblMsg.K, dsOrdPosnNum);
                int maxLineNum = NWAL1500CommonLogicForLineControl.getMaxLineNum(glblMsg.D, dsOrdPosnNum); // 2018/01/25 S21_NA#19808 Mod

                // insert and get new line for K
                // QC743
                // NWAL1500_KSMsg addLineForK = (NWAL1500_KSMsg) NWAL1500CommonLogicForLineControl.insertNewLine(glblMsg.K, addLineRowForK);
                NWAL1500_DSMsg addLineForD = (NWAL1500_DSMsg) NWAL1500CommonLogicForLineControl.insertNewLine(glblMsg.D, addLineRowForK); // 2018/01/25 S21_NA#19808 Mod
                if (addLineForD != null) {

                    // set up new line for K
                    addLineForD.dsOrdPosnNum_RL.setValue(dsOrdPosnNum);
                    addLineForD.dsCpoLineNum_RL.setValue(maxLineNum + 1);
                    addLineForD.xxLineNum_RL.setValue(NWAL1500CommonLogic.concatLineNum(addLineForD));
                    NWAL1500CommonLogic.setDefaultLineCatgPullDownForNewRma(bizMsg, addLineForD); // Add 2017/09/21 S21_NA#16346
                    // S21_NA#8450
                    addLineForD.dsOrdLineCatgCd_RL.setValue(bizMsg.dsOrdLineCatgCd_RD.getValue());
                    addLineForD.prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
                    // addLineForK.rqstPickUpDt_RL.setValue(bizMsg.slsDt.getValue()); // S21_NA#5000#80
                    addLineForD.rqstPickUpDt_RL.setValue(bizMsg.addRddDt.getValue()); // S21_NA#5000#80
                    addLineForD.prcCatgNm_RL.setValue(bizMsg.prcCatgNm.getValue());
                    addLineForD.flPrcListNm_RL.setValue(bizMsg.flPrcListNm.getValue());
                    addLineForD.rtrnQty_RL.setValue(BigDecimal.ZERO);
                    addLineForD.cancQty_RL.setValue(BigDecimal.ZERO);
                    // addLineForD.dlrRefNum_RL.setValue(bizMsg.dlrRefNum.getValue()); 2016/08/26 S21_NA#9806 Del
                    // addLineForD.csmpContrNum_RL.setValue(bizMsg.csmpContrNum.getValue()); 2016/08/26 S21_NA#9806 Del
                    // Add 2016/03/07 S21_NA#5000#78 Start
                    ZYPEZDItemValueSetter.setValue(addLineForD.custIssPoNum_RL, bizMsg.custIssPoNum);
                    ZYPEZDItemValueSetter.setValue(addLineForD.custIssPoDt_RL, bizMsg.custIssPoDt);
                    // 2018/08/02 S21_NA#26665 mod start
//                    ZYPEZDItemValueSetter.setValue(addLineForD.cpoSrcTpDescTxt_RL, bizMsg.cpoSrcTpDescTxt);
                    ZYPEZDItemValueSetter.setValue(addLineForD.cpoSrcTpDescTxt_RL, cpoSrcTpTMsg.cpoSrcTpDescTxt);
                    // 2018/08/02 S21_NA#26665 mod end
                    ZYPEZDItemValueSetter.setValue(addLineForD.ordSrcRefNum_RL, bizMsg.ordSrcRefNum);
                    // Add 2016/03/07 S21_NA#5000#78 End
                    // copyCsmpData(configLineMsg, addLineForD); // 2016/08/26 S21_NA#9806 Add
                    // 2018/08/01 S21_NA#26414 add start
                    if (NWXC150001DsCheck.isAvalOrderCtxType( //
                            bizMsg.glblCmpyCd.getValue(), //
                            ORD_CATG_CTX_TP.SERVICE_EXCHANGE_ORDER_VALUE_SET, //
                            bizMsg.dsOrdCatgCd.getValue(), //
                            bizMsg.dsOrdTpCd.getValue(), //
                            bizMsg.dsOrdRsnCd.getValue())) {
                        String defaultRsnCd = ZYPCodeDataUtil.getVarCharConstValue(SERVICE_EXCHANGE_RTRN_RSN_CD, bizMsg.glblCmpyCd.getValue());
                        if (ZYPCommonFunc.hasValue(defaultRsnCd)) {
                            ZYPEZDItemValueSetter.setValue(addLineForD.rtrnRsnCd_RL, defaultRsnCd);
                        }
                    }
                    // 2018/08/01 S21_NA#26414 add end
                    // 2018/01/25 S21_NA#19808 Add Start
                    NWAL1500_CSMsg configLineSMsg = new NWAL1500_CSMsg();
                    EZDMsg.copy(configLineMsg, null, configLineSMsg, null);
                    // 2018/01/25 S21_NA#19808 Add End
                    copyCsmpData(bizMsg, configLineSMsg, addLineForD); // QC#22371 2017/12/26 Mod // 2018/01/25 S21_NA#19808 Mod

                    // 2018/01/25 S21_NA#19808 Add Start
                    NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
                    int pageNum = NWAL1500CommonLogicForPagination.getNewLinePage(bizMsg, glblMsg, addLineForD);
                    NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, pageNum);
                    // 2018/01/25 S21_NA#19808 Add End
                 // QC743
                 // // add row index by configuration for D
                 // int addLineRowForD = NWAL1500CommonLogicForLineControl.getAddLineRow(bizMsg.D, dsOrdPosnNum);
                 // 
                 // // insert and get new line for D
                 // NWAL1500_DCMsg addLineForD = (NWAL1500_DCMsg) NWAL1500CommonLogicForLineControl.insertNewLine(bizMsg.D, addLineRowForD);
                 // 
                 // // copy K to D
                 // if (addLineForD != null) {
                 //     EZDMsg.copy(addLineForK, null, addLineForD, null);
                 // }
                }
            }
        }

        // if (true) {
        // return;
        // }
        // if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
        // if (bizMsg.B.length() < bizMsg.B.getValidCount() + 1) {
        // bizMsg.setMessageInfo(NWAM0100E);
        // return;
        // }
        //
        // List<Integer> selectRows =
        // ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_LC", "Y");
        // for (Integer selectRow : selectRows) {
        // NWAL1500CMsg cloneMsg = (NWAL1500CMsg) bizMsg.clone();
        //
        // NWAL1500_ACMsg configLineMsg = bizMsg.A.no(selectRow);
        // if
        // (ZYPConstant.FLG_ON_Y.equals(configLineMsg.xxSmryLineFlg_L.getValue()))
        // {
        // continue;
        // }
        // String posnNum = configLineMsg.dsOrdPosnNum_LC.getValue();
        //
        // for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
        // if
        // (posnNum.equals(bizMsg.B.no(i).dsOrdPosnNum_LL.getValue()))
        // {
        // int lastIdx = 0;
        // for (int j = i; j < bizMsg.B.getValidCount(); j++) {
        // if
        // (posnNum.equals(bizMsg.B.no(j).dsOrdPosnNum_LL.getValue()))
        // {
        // lastIdx = j;
        // } else {
        // break;
        // }
        // }
        // bizMsg.B.setValidCount(bizMsg.B.getValidCount() + 1);
        //
        // for (int j = lastIdx + 1; j < bizMsg.B.getValidCount();
        // j++) {
        // EZDMsg.copy(cloneMsg.B.no(j), null, bizMsg.B.no(j + 1),
        // null);
        // }
        //
        // bizMsg.B.no(lastIdx + 1).clear();
        // NWAL1500_BCMsg addLineMsg = bizMsg.B.no(lastIdx + 1);
        // addLineMsg.dsOrdPosnNum_LL.setValue(posnNum);
        // addLineMsg.dsCpoLineNum_LL.setValue(bizMsg.B.no(lastIdx).dsCpoLineNum_LL.getValueInt()
        // + 1);
        // addLineMsg.xxLineNum_LL.setValue(NWAL1500CommonLogic.concatLineNum(addLineMsg));
        // addLineMsg.dsOrdLineCatgCd_LL.setValue(bizMsg.dsOrdLineCatgCd_PM.getValue());
        // addLineMsg.prcBaseDt_LL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.rddDt_LL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.prcCatgNm_LL.setValue(bizMsg.prcCatgNm.getValue());
        // addLineMsg.flPrcListNm_LL.setValue(bizMsg.flPrcListNm.getValue());
        // // ADD START 2015/12/16 #2013
        // addLineMsg.invQty_LL.setValue(BigDecimal.ZERO);
        // // ADD END 2015/12/15 #2013
        //
        // int lineIndex =
        // NWAL1500CommonLogicForConfigId.getAddLineRow(glblMsg.J,
        // posnNum);
        // EZDMsg line =
        // NWAL1500CommonLogicForConfigId.insertNewLine(glblMsg.J,
        // lineIndex);
        // if (line != null) {
        // EZDMsg.copy(addLineMsg, null, line, null);
        // }
        // break;
        //
        // } else if (Integer.parseInt(posnNum) <
        // Integer.parseInt(bizMsg.B.no(i).dsOrdPosnNum_LL.getValue()))
        // {
        // bizMsg.B.setValidCount(bizMsg.B.getValidCount() + 1);
        // for (int j = i; j < bizMsg.B.getValidCount(); j++) {
        // EZDMsg.copy(cloneMsg.B.no(j), null, bizMsg.B.no(j + 1),
        // null);
        // }
        //
        // bizMsg.B.no(i).clear();
        // NWAL1500_BCMsg addLineMsg = bizMsg.B.no(i);
        // addLineMsg.dsOrdPosnNum_LL.setValue(posnNum);
        // addLineMsg.dsCpoLineNum_LL.setValue(1);
        // addLineMsg.xxLineNum_LL.setValue(NWAL1500CommonLogic.concatLineNum(addLineMsg));
        // addLineMsg.dsOrdLineCatgCd_LL.setValue(bizMsg.dsOrdLineCatgCd_PM.getValue());
        // addLineMsg.prcBaseDt_LL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.rddDt_LL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.prcCatgNm_LL.setValue(bizMsg.prcCatgNm.getValue());
        // addLineMsg.flPrcListNm_LL.setValue(bizMsg.flPrcListNm.getValue());
        // // ADD START 2015/12/16 #2013
        // addLineMsg.invQty_LL.setValue(BigDecimal.ZERO);
        // // ADD END 2015/12/15 #2013
        //
        // int lineIndex =
        // NWAL1500CommonLogicForConfigId.getAddLineRow(glblMsg.J,
        // posnNum);
        // EZDMsg line =
        // NWAL1500CommonLogicForConfigId.insertNewLine(glblMsg.J,
        // lineIndex);
        // if (line != null) {
        // EZDMsg.copy(addLineMsg, null, line, null);
        // }
        //
        // break;
        // }
        //
        // if (i == bizMsg.B.getValidCount() - 1) {
        // NWAL1500_BCMsg addLineMsg =
        // bizMsg.B.no(bizMsg.B.getValidCount());
        // addLineMsg.dsOrdPosnNum_LL.setValue(posnNum);
        // addLineMsg.dsCpoLineNum_LL.setValue(1);
        // addLineMsg.xxLineNum_LL.setValue(NWAL1500CommonLogic.concatLineNum(addLineMsg));
        // addLineMsg.dsOrdLineCatgCd_LL.setValue(bizMsg.dsOrdLineCatgCd_PM.getValue());
        // addLineMsg.prcBaseDt_LL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.rddDt_LL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.prcCatgNm_LL.setValue(bizMsg.prcCatgNm.getValue());
        // addLineMsg.flPrcListNm_LL.setValue(bizMsg.flPrcListNm.getValue());
        // // ADD START 2015/12/16 #2013
        // addLineMsg.invQty_LL.setValue(BigDecimal.ZERO);
        // // ADD END 2015/12/15 #2013
        // bizMsg.B.setValidCount(bizMsg.B.getValidCount() + 1);
        //
        // int lineIndex =
        // NWAL1500CommonLogicForConfigId.getAddLineRow(glblMsg.J,
        // posnNum);
        // EZDMsg line =
        // NWAL1500CommonLogicForConfigId.insertNewLine(glblMsg.J,
        // lineIndex);
        // if (line != null) {
        // EZDMsg.copy(addLineMsg, null, line, null);
        // }
        // break;
        // }
        // }
        //
        // if (bizMsg.B.getValidCount() == 0) {
        // NWAL1500_BCMsg addLineMsg =
        // bizMsg.B.no(bizMsg.B.getValidCount());
        // addLineMsg.dsOrdPosnNum_LL.setValue(posnNum);
        // addLineMsg.dsCpoLineNum_LL.setValue(1);
        // addLineMsg.xxLineNum_LL.setValue(NWAL1500CommonLogic.concatLineNum(addLineMsg));
        // addLineMsg.dsOrdLineCatgCd_LL.setValue(bizMsg.dsOrdLineCatgCd_PM.getValue());
        // addLineMsg.prcBaseDt_LL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.rddDt_LL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.prcCatgNm_LL.setValue(bizMsg.prcCatgNm.getValue());
        // addLineMsg.flPrcListNm_LL.setValue(bizMsg.flPrcListNm.getValue());
        // // ADD START 2015/12/16 #2013
        // addLineMsg.invQty_LL.setValue(BigDecimal.ZERO);
        // // ADD END 2015/12/15 #2013
        // bizMsg.B.setValidCount(bizMsg.B.getValidCount() + 1);
        //
        // int lineIndex =
        // NWAL1500CommonLogicForConfigId.getAddLineRow(glblMsg.J,
        // posnNum);
        // EZDMsg line =
        // NWAL1500CommonLogicForConfigId.insertNewLine(glblMsg.J,
        // lineIndex);
        // if (line != null) {
        // EZDMsg.copy(addLineMsg, null, line, null);
        // }
        // }
        // }
        // // set model edit flag
        // NWAL1500CommonLogicForLineConfig.setModelEditFlag(bizMsg.A,
        // glblMsg.J);
        // } else {
        // if (bizMsg.D.length() < bizMsg.D.getValidCount() + 1) {
        // bizMsg.setMessageInfo(NWAM0100E);
        // return;
        // }
        //
        // List<Integer> selectRows =
        // ZYPTableUtil.getSelectedRows(bizMsg.C, "xxChkBox_RC", "Y");
        // for (Integer selectRow : selectRows) {
        // NWAL1500CMsg cloneMsg = (NWAL1500CMsg) bizMsg.clone();
        //
        // NWAL1500_CCMsg configLineMsg = bizMsg.C.no(selectRow);
        // if
        // (ZYPConstant.FLG_ON_Y.equals(configLineMsg.xxSmryLineFlg_R.getValue()))
        // {
        // continue;
        // }
        // String posnNum = configLineMsg.dsOrdPosnNum_RC.getValue();
        //
        // for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
        // if
        // (posnNum.equals(bizMsg.D.no(i).dsOrdPosnNum_RL.getValue()))
        // {
        // int lastIdx = 0;
        // for (int j = i; j < bizMsg.D.getValidCount(); j++) {
        // if
        // (posnNum.equals(bizMsg.D.no(j).dsOrdPosnNum_RL.getValue()))
        // {
        // lastIdx = j;
        // } else {
        // break;
        // }
        // }
        // bizMsg.D.setValidCount(bizMsg.D.getValidCount() + 1);
        //
        // for (int j = lastIdx + 1; j < bizMsg.D.getValidCount();
        // j++) {
        // EZDMsg.copy(cloneMsg.D.no(j), null, bizMsg.D.no(j + 1),
        // null);
        // }
        //
        // bizMsg.D.no(lastIdx + 1).clear();
        // NWAL1500_DCMsg addLineMsg = bizMsg.D.no(lastIdx + 1);
        // addLineMsg.dsOrdPosnNum_RL.setValue(posnNum);
        // addLineMsg.dsCpoLineNum_RL.setValue(bizMsg.D.no(lastIdx).dsCpoLineNum_RL.getValueInt()
        // + 1);
        // addLineMsg.xxLineNum_RL.setValue(NWAL1500CommonLogic.concatLineNum(addLineMsg));
        // addLineMsg.dsOrdLineCatgCd_RL.setValue(bizMsg.dsOrdLineCatgCd_PM.getValue());
        // addLineMsg.prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.rqstPickUpDt_RL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.prcCatgNm_RL.setValue(bizMsg.prcCatgNm.getValue());
        // addLineMsg.flPrcListNm_RL.setValue(bizMsg.flPrcListNm.getValue());
        // addLineMsg.rtrnQty_RL.setValue(BigDecimal.ZERO);
        // addLineMsg.cancQty_RL.setValue(BigDecimal.ZERO);
        //
        // int lineIndex =
        // NWAL1500CommonLogicForConfigId.getAddLineRow(glblMsg.K,
        // posnNum);
        // EZDMsg line =
        // NWAL1500CommonLogicForConfigId.insertNewLine(glblMsg.K,
        // lineIndex);
        // if (line != null) {
        // EZDMsg.copy(addLineMsg, null, line, null);
        // }
        // break;
        //
        // } else if (Integer.parseInt(posnNum) <
        // Integer.parseInt(bizMsg.D.no(i).dsOrdPosnNum_RL.getValue()))
        // {
        // bizMsg.D.setValidCount(bizMsg.D.getValidCount() + 1);
        // for (int j = i; j < bizMsg.D.getValidCount(); j++) {
        // EZDMsg.copy(cloneMsg.D.no(j), null, bizMsg.D.no(j + 1),
        // null);
        // }
        //
        // bizMsg.D.no(i).clear();
        // NWAL1500_DCMsg addLineMsg = bizMsg.D.no(i);
        // addLineMsg.dsOrdPosnNum_RL.setValue(posnNum);
        // addLineMsg.dsCpoLineNum_RL.setValue(1);
        // addLineMsg.xxLineNum_RL.setValue(NWAL1500CommonLogic.concatLineNum(addLineMsg));
        // addLineMsg.dsOrdLineCatgCd_RL.setValue(bizMsg.dsOrdLineCatgCd_PM.getValue());
        // addLineMsg.prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.rqstPickUpDt_RL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.prcCatgNm_RL.setValue(bizMsg.prcCatgNm.getValue());
        // addLineMsg.flPrcListNm_RL.setValue(bizMsg.flPrcListNm.getValue());
        // addLineMsg.rtrnQty_RL.setValue(BigDecimal.ZERO);
        // addLineMsg.cancQty_RL.setValue(BigDecimal.ZERO);
        //
        // int lineIndex =
        // NWAL1500CommonLogicForConfigId.getAddLineRow(glblMsg.K,
        // posnNum);
        // EZDMsg line =
        // NWAL1500CommonLogicForConfigId.insertNewLine(glblMsg.K,
        // lineIndex);
        // if (line != null) {
        // EZDMsg.copy(addLineMsg, null, line, null);
        // }
        //
        // break;
        // }
        //
        // if (i == bizMsg.D.getValidCount() - 1) {
        // NWAL1500_DCMsg addLineMsg =
        // bizMsg.D.no(bizMsg.D.getValidCount());
        // addLineMsg.dsOrdPosnNum_RL.setValue(posnNum);
        // addLineMsg.dsCpoLineNum_RL.setValue(1);
        // addLineMsg.xxLineNum_RL.setValue(NWAL1500CommonLogic.concatLineNum(addLineMsg));
        // addLineMsg.dsOrdLineCatgCd_RL.setValue(bizMsg.dsOrdLineCatgCd_PM.getValue());
        // addLineMsg.prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.rqstPickUpDt_RL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.prcCatgNm_RL.setValue(bizMsg.prcCatgNm.getValue());
        // addLineMsg.flPrcListNm_RL.setValue(bizMsg.flPrcListNm.getValue());
        // addLineMsg.rtrnQty_RL.setValue(BigDecimal.ZERO);
        // addLineMsg.cancQty_RL.setValue(BigDecimal.ZERO);
        // bizMsg.D.setValidCount(bizMsg.D.getValidCount() + 1);
        //
        // int lineIndex =
        // NWAL1500CommonLogicForConfigId.getAddLineRow(glblMsg.K,
        // posnNum);
        // EZDMsg line =
        // NWAL1500CommonLogicForConfigId.insertNewLine(glblMsg.K,
        // lineIndex);
        // if (line != null) {
        // EZDMsg.copy(addLineMsg, null, line, null);
        // }
        // break;
        // }
        // }
        //
        // if (bizMsg.D.getValidCount() == 0) {
        // NWAL1500_DCMsg addLineMsg =
        // bizMsg.D.no(bizMsg.D.getValidCount());
        // addLineMsg.dsOrdPosnNum_RL.setValue(posnNum);
        // addLineMsg.dsCpoLineNum_RL.setValue(1);
        // addLineMsg.xxLineNum_RL.setValue(NWAL1500CommonLogic.concatLineNum(addLineMsg));
        // addLineMsg.dsOrdLineCatgCd_RL.setValue(bizMsg.dsOrdLineCatgCd_PM.getValue());
        // addLineMsg.prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.rqstPickUpDt_RL.setValue(bizMsg.slsDt.getValue());
        // addLineMsg.prcCatgNm_RL.setValue(bizMsg.prcCatgNm.getValue());
        // addLineMsg.flPrcListNm_RL.setValue(bizMsg.flPrcListNm.getValue());
        // addLineMsg.rtrnQty_RL.setValue(BigDecimal.ZERO);
        // addLineMsg.cancQty_RL.setValue(BigDecimal.ZERO);
        // bizMsg.D.setValidCount(bizMsg.D.getValidCount() + 1);
        //
        // int lineIndex =
        // NWAL1500CommonLogicForConfigId.getAddLineRow(glblMsg.K,
        // posnNum);
        // EZDMsg line =
        // NWAL1500CommonLogicForConfigId.insertNewLine(glblMsg.K,
        // lineIndex);
        // if (line != null) {
        // EZDMsg.copy(addLineMsg, null, line, null);
        // }
        // }
        // }
        // }
    }

    private void doProcess_NWAL1500Scrn00_Line_Collapsed(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2018/01/25 S21_NA#19808 Mod Start bizMsg -> glblMsg without any comments.
        final int eventLine = bizMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            // 2019/07/11 S21_NA#51287 Add Start
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            // 2019/07/11 S21_NA#51287 Add End
            NWAL1500_ACMsg configLineMsg = bizMsg.A.no(eventLine);
            if (ZYPConstant.FLG_ON_Y.equals(configLineMsg.xxSmryLineFlg_L.getValue())) {
                return;
            }

            String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_LC.getValue();
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                NWAL1500_BCMsg lineMsg = bizMsg.B.no(i);
                if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                    NWAL1500CommonLogicForPagination.copyLineDataToSMsg(lineMsg, glblMsg);
                }
            }
            int firstLineIdx = -1;
            for (int i = 0; i < glblMsg.B.getValidCount();i ++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
                if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                    if (firstLineIdx >= 0) {
                        lineMsg.xxSmryLineFlg_LL.setValue(ZYPConstant.FLG_ON_Y);
                    } else {
                        lineMsg.xxSmryLineFlg_LL.clear();
                        firstLineIdx++;
                    }
                }
            }
            configLineMsg.xxSmryLineFlg_L.setValue(ZYPConstant.FLG_ON_Y);
            NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(configLineMsg, glblMsg);

            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500_ASMsg configSMsg = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.A, dsOrdPosnNum);
            int pageNum = configSMsg.xxPageNum_LC.getValueInt();
            if (pageNum <= 0) {
                pageNum = 1;
            }
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, pageNum);
        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())){
            // 2019/07/11 S21_NA#51287 Add Start
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            // 2019/07/11 S21_NA#51287 Add End
            NWAL1500_CCMsg configLineMsg = bizMsg.C.no(eventLine);
            if (ZYPConstant.FLG_ON_Y.equals(configLineMsg.xxSmryLineFlg_R.getValue())) {
                return;
            }

            String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_RC.getValue();
            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                NWAL1500_DCMsg lineMsg = bizMsg.D.no(i);
                if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_RL.getValue())) {
                    NWAL1500CommonLogicForPagination.copyRmaLineDataToSMsg(lineMsg, glblMsg);
                }
            }
            int firstLineIdx = -1;
            for (int i = 0; i < glblMsg.D.getValidCount();i ++) {
                NWAL1500_DSMsg lineMsg = glblMsg.D.no(i);
                if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_RL.getValue())) {
                    if (firstLineIdx >= 0) {
                        lineMsg.xxSmryLineFlg_RL.setValue(ZYPConstant.FLG_ON_Y);
                    } else {
                        lineMsg.xxSmryLineFlg_RL.clear();
                        firstLineIdx++;
                    }
                }
            }
            configLineMsg.xxSmryLineFlg_R.setValue(ZYPConstant.FLG_ON_Y);
            NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(configLineMsg, glblMsg);

            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500_CSMsg configSMsg = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.C, dsOrdPosnNum);
            int pageNum = configSMsg.xxPageNum_RC.getValueInt();
            if (pageNum <= 0) {
                pageNum = 1;
            }
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, pageNum);
        }
    }

    private void doProcess_NWAL1500Scrn00_Line_Expand(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2018/01/25 S21_NA#19808 Mod Start bizMsg -> glblMsg without any comments.
        final int eventLine = bizMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            // 2019/07/11 S21_NA#51287 Add Start
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            // 2019/07/11 S21_NA#51287 Add End
            NWAL1500_ACMsg configLineMsg = bizMsg.A.no(eventLine);
            if (ZYPConstant.FLG_OFF_N.equals(configLineMsg.xxSmryLineFlg_L.getValue()) //
                    || !ZYPCommonFunc.hasValue(configLineMsg.xxSmryLineFlg_L)) {
                return;
            }

            String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_LC.getValue();
            for (int i = 0; i < glblMsg.B.getValidCount();i ++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
                if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                    lineMsg.xxSmryLineFlg_LL.clear();
                }
            }
            NWAL1500_ASMsg configSMsg = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.A, dsOrdPosnNum);
            configSMsg.xxSmryLineFlg_L.clear();

            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            configSMsg = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.A, dsOrdPosnNum);
            int pageNum = configSMsg.xxPageNum_LC.getValueInt();
            if (pageNum <= 0) {
                pageNum = 1;
            }
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, pageNum);
        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())){
            // 2019/07/11 S21_NA#51287 Add Start
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            // 2019/07/11 S21_NA#51287 Add End
            NWAL1500_CCMsg configLineMsg = bizMsg.C.no(eventLine);
            if (ZYPConstant.FLG_OFF_N.equals(configLineMsg.xxSmryLineFlg_R.getValue()) //
                    || !ZYPCommonFunc.hasValue(configLineMsg.xxSmryLineFlg_R)) {
                return;
            }

            String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_RC.getValue();
            for (int i = 0; i < glblMsg.D.getValidCount();i ++) {
                NWAL1500_DSMsg lineMsg = glblMsg.D.no(i);
                if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_RL.getValue())) {
                    lineMsg.xxSmryLineFlg_RL.clear();
                }
            }
            NWAL1500_CSMsg configSMsg = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.C, dsOrdPosnNum);
            configSMsg.xxSmryLineFlg_R.clear();

            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            configSMsg = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.C, dsOrdPosnNum);
            int pageNum = configSMsg.xxPageNum_RC.getValueInt();
            if (pageNum <= 0) {
                pageNum = 1;
            }
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, pageNum);
        }
    }

    private void doProcess_NWAL1500Scrn00_Line_All_Collapsed(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2018/01/25 S21_NA#19808 Mod without comments.
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                NWAL1500_BCMsg lineMsg = bizMsg.B.no(i);
                NWAL1500_ACMsg configMsg = NWAL1500CommonLogic.getParentConfig(bizMsg, lineMsg);
                boolean isCollapsed = configMsg != null && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxSmryLineFlg_L.getValue());
                if (!isCollapsed) {
                    NWAL1500CommonLogicForPagination.copyLineDataToSMsg(lineMsg, glblMsg);
                }
            }
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                glblMsg.A.no(i).xxSmryLineFlg_L.setValue(ZYPConstant.FLG_ON_Y);
                collapseDetectedConfig(glblMsg, TAB_LINE_CONFIG, glblMsg.A.no(i).dsOrdPosnNum_LC.getValue());
            }
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, 1);
        } else {
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                NWAL1500_DCMsg rmaLineMsg = bizMsg.D.no(i);
                NWAL1500_CCMsg rmaConfigMsg = NWAL1500CommonLogic.getParentConfig(bizMsg, rmaLineMsg);
                boolean isCollapsed = rmaConfigMsg != null && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rmaConfigMsg.xxSmryLineFlg_R.getValue());
                if (!isCollapsed) {
                    NWAL1500CommonLogicForPagination.copyRmaLineDataToSMsg(rmaLineMsg, glblMsg);
                }
            }
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                glblMsg.C.no(i).xxSmryLineFlg_R.setValue(ZYPConstant.FLG_ON_Y);
                collapseDetectedConfig(glblMsg, TAB_RMA, glblMsg.C.no(i).dsOrdPosnNum_RC.getValue());
            }
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, 1);
        }
    }

    private void doProcess_NWAL1500Scrn00_Line_All_Expand(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2018/01/25 S21_NA#19808 Mod without comments.
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                glblMsg.B.no(i).xxSmryLineFlg_LL.clear();
            }
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                glblMsg.A.no(i).xxSmryLineFlg_L.clear();
            }
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, 1);
        } else {
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                glblMsg.D.no(i).xxSmryLineFlg_RL.clear();
            }
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                glblMsg.C.no(i).xxSmryLineFlg_R.clear();
            }
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, 1);
        }
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromCategory(NWAL1500CMsg bizMsg) {

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.initProcSpecialInstructionForClear(bizMsg);
        // 2018/07/27 S21_NA#14307 Add End

        if (!NWAL1500CommonLogicForCategory.checkCatg(bizMsg)) {
            return;
        }

        bizMsg.dsOrdTpDescTxt.clear();

        // 2016/02/29 QC#1693/1700 Start
        getAllOrdCatgBizCtx(bizMsg);
        // 2016/02/29 QC#1693/1700 End
        getOrdEntryCancelAvailable(bizMsg); // 2016/10/13 S21_NA#7700 Add

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.cmnProcForSpecialInstruction(bizMsg, null, null);
        // 2018/07/27 S21_NA#14307 Add End
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromReason(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {  // 2018/01/25 S21_NA#19808 Mod

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.initProcSpecialInstructionForClear(bizMsg);
        // 2018/07/27 S21_NA#14307 Add End

        if (!NWAL1500QueryForCategory.getInstance().isExistDsOrdCatg(bizMsg)) {
            bizMsg.dsOrdTpDescTxt.clear();
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CATG });
            return;
        }

        if (!NWAL1500CommonLogicForCategory.checkRsn(bizMsg)) {
            return;
        }

        // Create Pulldown
        String slsDt = bizMsg.slsDt.getValue();
        NWAL1500CommonLogic.createSubRsnCdPulldown(bizMsg, slsDt);
        String primaryLineCatgCd = NWAL1500CommonLogic.createLineCatgPulldown(bizMsg, slsDt);
        String primaryLineCatgRmaCd = NWAL1500CommonLogic.createLineCatgPulldownForRma(bizMsg, glblMsg, slsDt); // 2018/01/25 S21_NA#19808 Mod

        // PullDown Initial Value
        bizMsg.dsOrdRsnCd.clear();
        NWAL1500CommonLogicForCategory.setInitValueForLineCatgPulldown(glblMsg, primaryLineCatgCd, primaryLineCatgRmaCd); // 2018/01/25 S21_NA#19808 Mod

        // Set Default Data
        NWAL1500CommonLogicForCategory.deriveDefaultData(bizMsg, glblMsg, slsDt); // 2018/01/25 S21_NA#19808

        // 2016/02/29 QC#1693/1700 Start
        getAllOrdCatgBizCtx(bizMsg);
        // 2016/02/29 QC#1693/1700 End
        getOrdEntryCancelAvailable(bizMsg); // 2016/10/13 S21_NA#7700 Add

        // 2018/12/12 QC#29315 Add Start
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd) && ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsBizAreaCd, NWXC150001DsCheck.getDsBizArea(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue(), bizMsg.dsOrdRsnCd.getValue()));
        }
        // 2018/12/12 QC#29315 Add End

        // 2016/03/25 S21_NA#4693 Add Start
        String dropShipAvalFlg = NWAL1500CommonLogic.getDropShipAvalFlg(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        bizMsg.dropShipAvalFlg.setValue(dropShipAvalFlg);

        if (ZYPConstant.FLG_OFF_N.equals(dropShipAvalFlg)) {
            NWAL1500CommonLogic.resetDropShipAddress(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808
        }
        // 2016/03/25 S21_NA#4693 Add Start
        NWAL1500CommonLogic.setModelNameAfterDeriveCategoryOrReason(bizMsg, glblMsg); // 2017/03/02 S21_NA#17714-2 Add Start // 2018/01/25 S21_NA#19808 Mod

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.cmnProcForSpecialInstruction(bizMsg, null, null);
        // 2018/07/27 S21_NA#14307 Add End
        // 2018/08/21 S21_NA#26767 Add Start
        NWAL1500CommonLogicForLineConfig.setConfigType(bizMsg);
        NWAL1500CommonLogicForLineConfig.setReturnReason(bizMsg);
        // 2018/08/21 S21_NA#26767 Add End
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSalesRepCode(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.psnNum)) { // 2016/05/11 S21_NA#7861 Mod slsRepPsnCd -> psnNum
            bizMsg.slsRepTocCd.clear();
            bizMsg.slsRepTocNm.clear();
            bizMsg.coaBrCd.clear();
            bizMsg.coaBrDescTxt.clear();
            bizMsg.coaExtnCd.clear();
            bizMsg.coaExtnDescTxt.clear();
            bizMsg.xxScrItem54Txt_CB.clear();
            bizMsg.xxScrItem54Txt_CE.clear();

            // Delete All Sales Credit
            NWAL1500CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);

            return;
        }

        S21SsmEZDResult ssmResult = NWAL1500QueryForCategory.getInstance().getDsOrdTpList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdTpDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_RSN });
            return;
        }

        String writerSlsRepTocCd = NWAL1500CommonLogicForSalesCredit.getSlsRepCd(bizMsg, false, MSG_PARAM_SLS_REP_CD);
        if (!ZYPCommonFunc.hasValue(writerSlsRepTocCd)) {
            return;
        }

        // 2017/11/15 S21_NA#222604 Mod Start
        if (NWAL1500CommonLogicForSalesCredit.isExistQuoteRep(bizMsg)) {
            // Delete All Sales Credit
            NWAL1500CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);
            NWAL1500CommonLogic.clearGLSegment(bizMsg);
        }
        // 2017/11/15 S21_NA#22604 Mod End

        // Set Writer Sales Credit
        NWAL1500CommonLogicForSalesCredit.setWriterSlsCreditInfo(bizMsg, glblMsg, writerSlsRepTocCd);
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSalesRepName(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.slsRepTocNm)) {
            bizMsg.psnNum.clear(); // 2016/05/11 S21_NA#7861 Mod slsRepPsnCd -> psnNum
            bizMsg.slsRepTocCd.clear();
            bizMsg.coaBrCd.clear();
            bizMsg.coaBrDescTxt.clear();
            bizMsg.coaExtnCd.clear();
            bizMsg.coaExtnDescTxt.clear();
            bizMsg.xxScrItem54Txt_CB.clear();
            bizMsg.xxScrItem54Txt_CE.clear();

            // Delete All Sales Credit
            NWAL1500CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);

            return;
        }

        S21SsmEZDResult ssmResult = NWAL1500QueryForCategory.getInstance().getDsOrdTpList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdTpDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_RSN });
            return;
        }

        String writerSlsRepTocCd = NWAL1500CommonLogicForSalesCredit.getSlsRepCd(bizMsg, true, MSG_PARAM_SLS_REP_NM);
        if (!ZYPCommonFunc.hasValue(writerSlsRepTocCd)) {
            return;
        }

        // 2018/01/30 S21_NA#23565 mod start
        if (NWAL1500CommonLogicForSalesCredit.isExistQuoteRep(bizMsg)) {
            // Delete All Sales Credit
            NWAL1500CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);
            NWAL1500CommonLogic.clearGLSegment(bizMsg);
        }
        // 2018/01/30 S21_NA#23565 mod end

        // Set Writer Sales Credit
        NWAL1500CommonLogicForSalesCredit.setWriterSlsCreditInfo(bizMsg, glblMsg, writerSlsRepTocCd);
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromBillToName(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808

        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctIndex = bizMsg.xxCellIdx.getValueInt();
        EZDCStringItem billToCustAcctNm = null;
        String billToCustCd = null; // 2016/09/28 S21_NA#8659 Add

        // 2018/01/25 S21_NA#19808 Add Start
        NWAL1500_ACMsg configMsg = null;
        NWAL1500_CCMsg rmaConfigMsg = null;
        // 2018/01/25 S21_NA#19808 Add End
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            billToCustAcctNm = bizMsg.A.no(slctIndex).billToCustAcctNm_LC;
            // 2018/07/27 S21_NA#14307 Del Start
            //billToCustCd = bizMsg.A.no(slctIndex).billToCustCd_LC.getValue(); // 2016/09/28 S21_NA#8659 Add
            // 2018/07/27 S21_NA#14307 Del End
            configMsg = bizMsg.A.no(slctIndex); // 2018/01/25 S21_NA#19808
        } else if (TAB_RMA.equals(dplyTab)) {
            billToCustAcctNm = bizMsg.C.no(slctIndex).billToCustAcctNm_RC;
            // 2018/07/27 S21_NA#14307 Del Start
            //billToCustCd = bizMsg.C.no(slctIndex).billToCustCd_RC.getValue(); // 2016/09/28 S21_NA#8659 Add
            // 2018/07/27 S21_NA#14307 Del End
            rmaConfigMsg = bizMsg.C.no(slctIndex); // 2018/01/25 S21_NA#19808
        } else {
            billToCustAcctNm = bizMsg.billToCustAcctNm;
            // 2018/07/27 S21_NA#14307 Del Start
            //billToCustCd = bizMsg.billToCustCd.getValue(); // 2016/09/28 S21_NA#8659 Add
            // 2018/07/27 S21_NA#14307 Del End
        }

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.initProcSpecialInstruction(bizMsg, configMsg, rmaConfigMsg, null);
        // 2018/07/27 S21_NA#14307 Add End
 
        SELL_TO_CUSTTMsg sellToCustTMsg = NWAL1500CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, true, billToCustAcctNm, MSG_PARAM_BILL_TO_NM);
        if (sellToCustTMsg == null) {
            return;
        }

        // 2016/09/28 S21_NA#8659 Mod Start
        // NWAL1500CommonLogicForCustomer.cmnProcForDeriveFromBillTo(bizMsg, dsAcctCustTMsg);
        NWAL1500CommonLogicForCustomer.cmnProcForDeriveFromBillTo(bizMsg, glblMsg, sellToCustTMsg, billToCustCd); // 2018/01/25 S21_NA#19808
        // 2016/09/28 S21_NA#8659 Mod End
        // 2018/01/25 S21_NA#19808 Add Start
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(configMsg, glblMsg);
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(rmaConfigMsg, glblMsg);
        }
        // 2018/01/25 S21_NA#19808 Add End

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.cmnProcForSpecialInstruction(bizMsg, configMsg, rmaConfigMsg);
        // 2018/07/27 S21_NA#14307 Add End
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromBillToAccount(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808

        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctIndex = bizMsg.xxCellIdx.getValueInt();
        EZDCStringItem billToCustAcctCd = null;
        String billToCustCd = null; // 2016/09/28 S21_NA#8659 Add

        // 2018/01/25 S21_NA#19808 Add Start
        NWAL1500_ACMsg configMsg = null;
        NWAL1500_CCMsg rmaConfigMsg = null;
        // 2018/01/25 S21_NA#19808 Add End
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            billToCustAcctCd = bizMsg.A.no(slctIndex).billToCustAcctCd_LC;
            configMsg = bizMsg.A.no(slctIndex); // 2018/01/25 S21_NA#19808
            // 2017/07/13 S21_NA#19912 Del Start
//            billToCustCd = bizMsg.A.no(slctIndex).billToCustCd_LC.getValue(); // 2016/09/28 S21_NA#8659 Add
            // 2017/07/13 S21_NA#19912 Del Start
        } else if (TAB_RMA.equals(dplyTab)) {
            billToCustAcctCd = bizMsg.C.no(slctIndex).billToCustAcctCd_RC;
            rmaConfigMsg = bizMsg.C.no(slctIndex); // 2018/01/25 S21_NA#19808
            // 2017/07/13 S21_NA#19912 Del Start
//            billToCustCd = bizMsg.C.no(slctIndex).billToCustCd_RC.getValue(); // 2016/09/28 S21_NA#8659 Add
            // 2017/07/13 S21_NA#19912 Del End
        } else {
            billToCustAcctCd = bizMsg.billToCustAcctCd;
            // 2017/07/13 S21_NA#19912 Del Start
//            billToCustCd = bizMsg.billToCustCd.getValue(); // 2016/09/28 S21_NA#8659 Add
            // 2017/07/13 S21_NA#19912 Del End
        }

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.initProcSpecialInstruction(bizMsg, configMsg, rmaConfigMsg, null);
        // 2018/07/27 S21_NA#14307 Add End

        SELL_TO_CUSTTMsg sellToCustTMsg = NWAL1500CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, false, billToCustAcctCd, MSG_PARAM_BILL_TO_NUM);
        if (sellToCustTMsg == null) {
            return;
        }

        // 2016/09/28 S21_NA#8659 Mod Start
        // NWAL1500CommonLogicForCustomer.cmnProcForDeriveFromBillTo(bizMsg, dsAcctCustTMsg);
        NWAL1500CommonLogicForCustomer.cmnProcForDeriveFromBillTo(bizMsg, glblMsg, sellToCustTMsg, billToCustCd); // 2018/01/25 S21_NA#19808
        // 2016/09/28 S21_NA#8659 Mod End

        // 2018/01/25 S21_NA#19808 Add Start
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(configMsg, glblMsg);
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(rmaConfigMsg, glblMsg);
        }
        // 2018/01/25 S21_NA#19808 Add End

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.cmnProcForSpecialInstruction(bizMsg, configMsg, rmaConfigMsg);
        // 2018/07/27 S21_NA#14307 Add End
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromBillToLocation(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808

        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctIndex = bizMsg.xxCellIdx.getValueInt();
        EZDCStringItem billToCustCd = null;
        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500_ACMsg confMsg = null;
        NWAL1500_CCMsg rmaConfMsg = null;
        // 2018/07/27 S21_NA#14307 Add End

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            billToCustCd = bizMsg.A.no(slctIndex).billToCustCd_LC;
            confMsg = bizMsg.A.no(slctIndex); // 2018/07/27 S21_NA#14307 Add
        } else if (TAB_RMA.equals(dplyTab)) {
            billToCustCd = bizMsg.C.no(slctIndex).billToCustCd_RC;
            rmaConfMsg = bizMsg.C.no(slctIndex); // 2018/07/27 S21_NA#14307 Add
        } else {
            billToCustCd = bizMsg.billToCustCd;
        }

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.initProcSpecialInstruction(bizMsg, confMsg, rmaConfMsg, bizMsg.billToCustCd_BK);
        // 2018/07/27 S21_NA#14307 Add End

        Map<String, String> billToInfo = NWAL1500CommonLogicForCustomer.getBillToInfo(bizMsg, billToCustCd, MSG_PARAM_BILL_TO_LOC);
        if (billToInfo == null) {
            return;
        }

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            //NWAL1500_ACMsg confMsg = bizMsg.A.no(slctIndex); // 2018/07/27 S21_NA#14307 Del
            ZYPEZDItemValueSetter.setValue(confMsg.billToCustAcctCd_LC, billToInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(confMsg.billToCustAcctNm_LC, billToInfo.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(confMsg.billToCustCd_LC, billToInfo.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(confMsg.billToCustLocAddr_LC, billToInfo.get("BILL_TO_ADDR"));
            NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(confMsg, glblMsg); // 2018/01/25 S21_NA#19808
        } else if (TAB_RMA.equals(dplyTab)) {
            //NWAL1500_CCMsg rmaConfMsg = bizMsg.C.no(slctIndex);  // 2018/07/27 S21_NA#14307 Del
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.billToCustAcctCd_RC, billToInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.billToCustAcctNm_RC, billToInfo.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.billToCustCd_RC, billToInfo.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.billToCustLocAddr_RC, billToInfo.get("BILL_TO_ADDR"));
            NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(rmaConfMsg, glblMsg); // 2018/01/25 S21_NA#19808
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, billToInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, billToInfo.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd, billToInfo.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, billToInfo.get("BILL_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.entBillToCustLocAddr, billToInfo.get("ENT_BILL_TO_ADDR"));
            NWAL1500CommonLogicForCategory.deriveDefaultPmtTerm(bizMsg);
            NWAL1500CommonLogicForCustomer.deriveDefaultBillToInfoForDtlFld(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808
        }
 
        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.cmnProcForSpecialInstruction(bizMsg, confMsg, rmaConfMsg);
        // 2018/07/27 S21_NA#14307 Add End
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromShipToName(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod

        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctIndex = bizMsg.xxCellIdx.getValueInt();
        EZDCStringItem shipToCustAcctNm = null;
        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500_ACMsg confMsg = null;
        NWAL1500_CCMsg rmaConfMsg = null;
        // 2018/07/27 S21_NA#14307 Add End

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            shipToCustAcctNm = bizMsg.A.no(slctIndex).shipToCustAcctNm_LC;
            confMsg = bizMsg.A.no(slctIndex); // 2018/07/27 S21_NA#14307 Add
        } else if (TAB_RMA.equals(dplyTab)) {
            shipToCustAcctNm = bizMsg.C.no(slctIndex).shipToCustAcctNm_RC;
            rmaConfMsg = bizMsg.C.no(slctIndex); // 2018/07/27 S21_NA#14307 Add
        } else {
            shipToCustAcctNm = bizMsg.shipToCustAcctNm;
        }

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.initProcSpecialInstruction(bizMsg, confMsg, rmaConfMsg, null);
        // 2018/07/27 S21_NA#14307 Add End

        // 2018/01/25 S21_NA#19808 Add Start
        // 2018/07/27 S21_NA#14307 Del Start
        //NWAL1500_ACMsg confMsg = null;
        //NWAL1500_CCMsg rmaConfMsg = null;
        // 2018/07/27 S21_NA#14307 Del End
        // 2018/01/25 S21_NA#19808 Add End
        SELL_TO_CUSTTMsg sellToCustTMsg = NWAL1500CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, true, shipToCustAcctNm, MSG_PARAM_SHIP_TO_NM);
        if (sellToCustTMsg == null) {
            return;
        }

        String acctNum = sellToCustTMsg.sellToCustCd.getValue();
        String acctNm = sellToCustTMsg.dsAcctNm.getValue();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            //confMsg = bizMsg.A.no(slctIndex); // 2018/07/27 S21_NA#14307 Del
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCustAcctCd_LC, acctNum);
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCustAcctNm_LC, acctNm);
            confMsg.shipToCustCd_LC.clear(); // 2018/07/27 S21_NA#14307 Add
        } else if (TAB_RMA.equals(dplyTab)) {
            // rmaConfMsg = bizMsg.C.no(slctIndex); // 2018/07/27 S21_NA#14307 Del
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCustAcctCd_RC, acctNum);
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCustAcctNm_RC, acctNm);
            rmaConfMsg.shipToCustCd_RC.clear(); // 2018/07/27 S21_NA#14307 Add
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, acctNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, acctNm);
            bizMsg.shipToCustCd.clear(); // 2018/07/27 S21_NA#14307 Add
        }

        NWAL1500CommonLogicForCustomer.cmnProcForDeriveFromShipTo(bizMsg, glblMsg, true); // 2018/01/25 S21_NA#19808 Mod

        // Add Start 2017/02/07 QC#17257
        updateBaseCmptFlg(bizMsg, glblMsg, slctIndex); // 2018/01/25 S21_NA#19808 Mod
        // Add End 2017/02/07 QC#17257
        // 2018/01/25 S21_NA#19808 Add Start
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(confMsg, glblMsg);
            // 2018/09/20 S21_NA#28199 Add Start
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
            // 2018/09/20 S21_NA#28199 Add End
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(rmaConfMsg, glblMsg);
            // 2018/09/20 S21_NA#28199 Add Start
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
            // 2018/09/20 S21_NA#28199 Add End
        }
        // 2018/01/25 S21_NA#19808 Add End

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.cmnProcForSpecialInstruction(bizMsg, confMsg, rmaConfMsg);
        // 2018/07/27 S21_NA#14307 Add End
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromShipToAccount(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod

        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctIndex = bizMsg.xxCellIdx.getValueInt();
        EZDCStringItem shipToCustAcctCd = null;

        // 2018/01/25 S21_NA#19808 Add
        NWAL1500_ACMsg confMsg = null;
        NWAL1500_CCMsg rmaConfMsg = null;
        // 2018/01/25 S21_NA#19808 Add
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            shipToCustAcctCd = bizMsg.A.no(slctIndex).shipToCustAcctCd_LC;
            confMsg = bizMsg.A.no(slctIndex); // 2018/07/27 S21_NA#14307 Add
        } else if (TAB_RMA.equals(dplyTab)) {
            shipToCustAcctCd = bizMsg.C.no(slctIndex).shipToCustAcctCd_RC;
            rmaConfMsg = bizMsg.C.no(slctIndex); // 2018/07/27 S21_NA#14307 Add
        } else {
            shipToCustAcctCd = bizMsg.shipToCustAcctCd;
        }

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.initProcSpecialInstruction(bizMsg, confMsg, rmaConfMsg, null);
        // 2018/07/27 S21_NA#14307 Add End

        SELL_TO_CUSTTMsg sellToCustTMsg = NWAL1500CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, false, shipToCustAcctCd, MSG_PARAM_SHIP_TO_NUM);
        if (sellToCustTMsg == null) {
            return;
        }

        String acctNum = sellToCustTMsg.sellToCustCd.getValue();
        String acctNm = sellToCustTMsg.dsAcctNm.getValue();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            //confMsg = bizMsg.A.no(slctIndex); // 2018/07/27 S21_NA#14307 Del
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCustAcctCd_LC, acctNum);
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCustAcctNm_LC, acctNm);
            confMsg.shipToCustCd_LC.clear(); // 2017/07/13 S21_NA#19912 Add
        } else if (TAB_RMA.equals(dplyTab)) {
            //rmaConfMsg = bizMsg.C.no(slctIndex); // 2018/07/27 S21_NA#14307 Del
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCustAcctCd_RC, acctNum);
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCustAcctNm_RC, acctNm);
            rmaConfMsg.shipToCustCd_RC.clear(); // 2017/07/13 S21_NA#19912 Add
       } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, acctNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, acctNm);
            bizMsg.shipToCustCd.clear(); // 2017/07/13 S21_NA#19912 Add
        }

        NWAL1500CommonLogicForCustomer.cmnProcForDeriveFromShipTo(bizMsg, glblMsg, true); // 2018/01/25 S21_NA#19808 Mod

        // Add Start 2017/02/07 QC#17257
        updateBaseCmptFlg(bizMsg, glblMsg, slctIndex); // 2018/01/25 S21_NA#19808 Mod
        // Add End 2017/02/07 QC#17257
        // 2018/01/25 S21_NA#19808 Add Start
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(confMsg, glblMsg);
            // 2018/09/20 S21_NA#28199 Add Start
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
            // 2018/09/20 S21_NA#28199 Add End
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(rmaConfMsg, glblMsg);
            // 2018/09/20 S21_NA#28199 Add Start
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
            // 2018/09/20 S21_NA#28199 Add End
        }
        // 2018/01/25 S21_NA#19808 Add End

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.cmnProcForSpecialInstruction(bizMsg, confMsg, rmaConfMsg);
        // 2018/07/27 S21_NA#14307 Add End
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromShipToLocation(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod

        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctIndex = bizMsg.xxCellIdx.getValueInt();
        EZDCStringItem shipToCustCd = null;
        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500_ACMsg confMsg = null;
        NWAL1500_CCMsg rmaConfMsg = null;
        // 2018/07/27 S21_NA#14307 Add End

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            shipToCustCd = bizMsg.A.no(slctIndex).shipToCustCd_LC;
            confMsg = bizMsg.A.no(slctIndex); // 2018/07/27 S21_NA#14307 Add
        } else if (TAB_RMA.equals(dplyTab)) {
            shipToCustCd = bizMsg.C.no(slctIndex).shipToCustCd_RC;
            rmaConfMsg = bizMsg.C.no(slctIndex); // 2018/07/27 S21_NA#14307 Add
        } else {
            shipToCustCd = bizMsg.shipToCustCd;
        }

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.initProcSpecialInstruction(bizMsg, confMsg, rmaConfMsg, bizMsg.shipToCustCd_BK);
        // 2018/07/27 S21_NA#14307 Add End

        Map<String, String> shipToInfo = NWAL1500CommonLogicForCustomer.getShipToInfo(bizMsg, shipToCustCd);
        if (shipToInfo == null) {
            return;
        }

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            //NWAL1500_ACMsg confMsg = bizMsg.A.no(slctIndex); // 2018/07/27 S21_NA#14307 Del
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCustAcctCd_LC, shipToInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCustAcctNm_LC, shipToInfo.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCustCd_LC, shipToInfo.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToAddlLocNm_LC, shipToInfo.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCustLocAddr_LC, shipToInfo.get("SHIP_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToFirstLineAddr_LC, shipToInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToScdLineAddr_LC, shipToInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToThirdLineAddr_LC, shipToInfo.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToFrthLineAddr_LC, shipToInfo.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToFirstRefCmntTxt_LC, shipToInfo.get("FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToScdRefCmntTxt_LC, shipToInfo.get("SCD_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCtyAddr_LC, shipToInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToStCd_LC, shipToInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToPostCd_LC, shipToInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToProvNm_LC, shipToInfo.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCtryCd_LC, shipToInfo.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCntyNm_LC, shipToInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(confMsg.dropShipFlg_LC, ZYPConstant.FLG_OFF_N);
            // 2017/12/08 S21_NA#21621 Add Start
            confMsg.addShipToLocNm_LC.clear();
            // 2017/12/08 S21_NA#21621 Add End
            // 2018/09/20 S21_NA#28199 Add Start
            NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(confMsg, glblMsg);
            // 2018/09/20 S21_NA#28199 Add End
        } else if (TAB_RMA.equals(dplyTab)) {
            //NWAL1500_CCMsg rmaConfMsg = bizMsg.C.no(slctIndex); // 2018/07/27 S21_NA#14307 Del
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCustAcctCd_RC, shipToInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCustAcctNm_RC, shipToInfo.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCustCd_RC, shipToInfo.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToAddlLocNm_RC, shipToInfo.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCustLocAddr_RC, shipToInfo.get("SHIP_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToFirstLineAddr_RC, shipToInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToScdLineAddr_RC, shipToInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToThirdLineAddr_RC, shipToInfo.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToFrthLineAddr_RC, shipToInfo.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToFirstRefCmntTxt_RC, shipToInfo.get("FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToScdRefCmntTxt_RC, shipToInfo.get("SCD_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCtyAddr_RC, shipToInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToStCd_RC, shipToInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToPostCd_RC, shipToInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToProvNm_RC, shipToInfo.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCtryCd_RC, shipToInfo.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCntyNm_RC, shipToInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.dropShipFlg_RC, ZYPConstant.FLG_OFF_N);
            // 2017/12/08 S21_NA#21621 Add Start
            rmaConfMsg.addShipToLocNm_RC.clear();
            // 2017/12/08 S21_NA#21621 Add End
            // 2018/09/20 S21_NA#28199 Add Start
            NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(rmaConfMsg, glblMsg);
            // 2018/09/20 S21_NA#28199 Add End
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, shipToInfo.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, shipToInfo.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm, shipToInfo.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, shipToInfo.get("SHIP_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.entShipToCustLocAddr, shipToInfo.get("ENT_SHIP_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr, shipToInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr, shipToInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr, shipToInfo.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr, shipToInfo.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstRefCmntTxt, shipToInfo.get("FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdRefCmntTxt, shipToInfo.get("SCD_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr, shipToInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd, shipToInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd, shipToInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm, shipToInfo.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd, shipToInfo.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm, shipToInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
            // 2017/12/07 S21_NA#21621 Add Start
            bizMsg.addShipToLocNm.clear();
            // 2017/12/07 S21_NA#21621 Add End
        }

         NWAL1500CommonLogicForCustomer.cmnProcForDeriveFromShipTo(bizMsg, glblMsg, false); // 2018/01/25 S21_NA#19808 Mod

        // Add Start 2017/02/07 QC#17257
        updateBaseCmptFlg(bizMsg, glblMsg, slctIndex); // 2018/01/25 S21_NA#19808 Mod
        // Add End 2017/02/07 QC#17257

        // 2018/09/20 S21_NA#28199 Add Start
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
        }
        // 2018/09/20 S21_NA#28199 Add End
        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.cmnProcForSpecialInstruction(bizMsg, confMsg, rmaConfMsg);
        // 2018/07/27 S21_NA#14307 Add End
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSoldToName(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.initProcSpecialInstruction(bizMsg, null, null, null);
        // 2018/07/27 S21_NA#14307 Add End

        SELL_TO_CUSTTMsg sellToCustTMsg = NWAL1500CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, true, bizMsg.soldToCustAcctNm, MSG_PARAM_SOLD_TO_NM);
        if (sellToCustTMsg == null) {
            return;
        }

        // 2018/07/27 S21_NA#14307 Add Start
        bizMsg.soldToCustLocCd.clear();
        // 2018/07/27 S21_NA#14307 Add End
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, sellToCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, sellToCustTMsg.dsAcctNm);
        NWAL1500CommonLogicForCustomer.cmnProcForDeriveFromSoldTo(bizMsg, glblMsg, true); // 2018/01/25 S21_NA#19808 Mod

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.cmnProcForSpecialInstruction(bizMsg, null, null);
        // 2018/07/27 S21_NA#14307 Add End
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSoldToAccount(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.initProcSpecialInstruction(bizMsg, null, null, bizMsg.sellToCustCd_BK);
        // 2018/07/27 S21_NA#14307 Add End

        SELL_TO_CUSTTMsg sellToCustTMsg = NWAL1500CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, false, bizMsg.sellToCustCd, MSG_PARAM_SOLD_TO_NUM);
        if (sellToCustTMsg == null) {
            return;
        }

        // 2017/07/13 S21_NA#19912 Add Start
        bizMsg.soldToCustLocCd.clear();
        // 2017/07/13 S21_NA#19912 Add End
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, sellToCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, sellToCustTMsg.dsAcctNm);
        NWAL1500CommonLogicForCustomer.cmnProcForDeriveFromSoldTo(bizMsg, glblMsg, true); // 2018/01/25 S21_NA#19808 Mod

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.cmnProcForSpecialInstruction(bizMsg, null, null);
        // 2018/07/27 S21_NA#14307 Add End
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSoldToLocation(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod

        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.initProcSpecialInstruction(bizMsg, null, null, null);
        // 2018/07/27 S21_NA#14307 Add End

        Map<String, String> billToInfo = NWAL1500CommonLogicForCustomer.getBillToInfo(bizMsg, bizMsg.soldToCustLocCd, MSG_PARAM_SOLD_TO_LOC);
        if (billToInfo == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, billToInfo.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, billToInfo.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, billToInfo.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, billToInfo.get("BILL_TO_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.entSoldToCustLocAddr, billToInfo.get("ENT_BILL_TO_ADDR"));

        NWAL1500CommonLogicForCustomer.cmnProcForDeriveFromSoldTo(bizMsg, glblMsg, false); // 2018/01/25 S21_NA#19808 Mod
 
        // 2018/07/27 S21_NA#14307 Add Start
        NWAL1500CommonLogicForSpecialInstruction.cmnProcForSpecialInstruction(bizMsg, null, null);
        // 2018/07/27 S21_NA#14307 Add End
    }

    // private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromModel(NWAL1500CMsg bizMsg,
    // NWAL1500SMsg glblMsg) {
    //
    // NWAL1500_ACMsg confMsg = bizMsg.A.no(bizMsg.xxCellIdx.getValueInt());
    //
    // Map<String, Object> mdlIdInfo = NWAL1500CommonLogicForLineConfig.getMdlIdInfo(bizMsg,
    // confMsg);
    // if (mdlIdInfo == null) {
    // return;
    // }
    // BigDecimal mdlId = (BigDecimal) mdlIdInfo.get("T_MDL_ID");
    // ZYPEZDItemValueSetter.setValue(confMsg.mdlId_LC, mdlId);
    // ZYPEZDItemValueSetter.setValue(confMsg.mdlDescTxt_LC, (String) mdlIdInfo.get("T_MDL_NM"));
    //
    // NWAL1500CommonLogicForLineConfig.deriveItemFromModel(bizMsg, glblMsg, confMsg);
    // }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromItem(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int slctLineIndex = bizMsg.xxCellIdx.getValueInt();
        // 2018/01/25 S21_NA#19808 Add Start
        String dplyTab = bizMsg.xxDplyTab.getValue();
        NWAL1500_BCMsg bizLineMsg = null;
        NWAL1500_DCMsg bizRmaLineMsg = null;
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            bizLineMsg = bizMsg.B.no(slctLineIndex);
            slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizLineMsg, glblMsg);
            // QC#29343 2018/11/26 Mod Start
            // Add Start 2018/08/02 QC#26072
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            //EZDMsg.copy(bizMsg.A, null, glblMsg.A, null);
            // Add End 2018/08/02 QC#26072
            // QC#29343 2018/11/26 Mod End
            EZDMsg.copy(bizLineMsg, null, glblMsg.B.no(slctLineIndex), null);
        } else {
            bizRmaLineMsg = bizMsg.D.no(slctLineIndex);
            slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizRmaLineMsg, glblMsg);
            // QC#29343 2018/11/26 Mod Start
            // Add Start 2018/08/02 QC#26072
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            //EZDMsg.copy(bizMsg.C, null, glblMsg.C, null);
            // Add End 2018/08/02 QC#26072
            // QC#29343 2018/11/26 Mod End
            EZDMsg.copy(bizRmaLineMsg, null, glblMsg.D.no(slctLineIndex), null);
        }
        // 2018/01/25 S21_NA#19808 Add End
        int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(dplyTab, glblMsg, slctLineIndex); // 2018/01/25 S21_NA#19808 Mod

        EZDSStringItem inputMdseValue = null; // 2018/01/25 S21_NA#19808 Mod
        EZDSStringItem custMdseCd = null; // 2018/01/25 S21_NA#19808 Mod
        EZDSStringItem mnfItemCd = null; // 2018/01/25 S21_NA#19808 Mod
        EZDSStringItem origMdseCd = null; // 2018/01/25 S21_NA#19808 Mod
        EZDSStringItem srchOrigItemFlg = null; // 2016/09/29 S21_NA#14805 ADD  // 2018/01/25 S21_NA#19808 Mod
        EZDSBigDecimalItem ordCustUomQty = null; // 2017/03/02 S21_NA#17714-2 Add  // 2018/01/25 S21_NA#19808 Mod

        // 2016/09/21 S21_NA#10274 Add Start
        String storeOrdLineStsCd = null;
        String storeOrdLineStsDescTxt = null;
        String storeOrdLineStsDescTxtOpt = null;
        String storeOrigUpdtDt = null;
        String storeOrigTimeZone = null;
        String storeBkMdseCd = null;
        String storeBkDtlLineNum = null;
        String storeBkDtlLineSubNum = null;
        // 2016/09/21 S21_NA#10274 Add End
        // 2018/01/25 S21_NA#19808 Add Start
        String storeUpdTsDplyTxt = null;
        String storeUpdUsrNm = null;
        String storeDsCpoDtlUpdUsrId = null; // 2018/08/21 S21_NA#27642 Add
        // 2018/01/25 S21_NA#19808 Add End
        String cpoSrcTpDescTxt = null;

        // 2018/01/25 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {

            // 2016/11/09 S21_NA#15746 Add Start
            if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum) //
                    && ZYPCommonFunc.hasValue(glblMsg.B.no(slctLineIndex).ordLineStsDescTxt_LL)) {
                if (isRelatedShellItem(bizMsg, glblMsg.B.no(slctLineIndex))) {
                    // 2018/01/25 S21_NA#19808 Del Start
//                    NWAL1500_BSMsg glblLineMsg = getGlblLineMsg(bizMsg, glblMsg, glblMsg.B.no(slctLineIndex));
//                    if (glblLineMsg != null) {
//                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).mdseCd_LL, glblLineMsg.mdseCd_LL);
//                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).mdseDescShortTxt_LL, glblLineMsg.mdseDescShortTxt_LL);
//                    } else {
//                        glblMsg.B.no(slctLineIndex).mdseCd_LL.clear();
//                        glblMsg.B.no(slctLineIndex).mdseDescShortTxt_LL.clear();
//                    }
                    // 2018/01/25 S21_NA#19808 Del End
                    // 2018/01/25 S21_NA#19808 Mod End
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).mdseCd_LL, glblMsg.B.no(slctLineIndex).mdseCd_DB);
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).mdseDescShortTxt_LL, glblMsg.B.no(slctLineIndex).mdseDescShortTxt_DB);
                    // 2018/01/25 S21_NA#19808 Mod End
                    glblMsg.B.no(slctLineIndex).mdseCd_LL.setErrorInfo(1, NWAM0910E);
                    return;
                }
            }
            // 2016/11/09 S21_NA#15746 Add End

            // S21_NA#1634 start
            // store values
            String storeMdseCd = glblMsg.B.no(slctLineIndex).mdseCd_LL.getValue();
            String storeDsOrdPosnNum = glblMsg.B.no(slctLineIndex).dsOrdPosnNum_LL.getValue();
            BigDecimal storeDsCpoLineNum = glblMsg.B.no(slctLineIndex).dsCpoLineNum_LL.getValue();
            String storeCpoDtlLineNum = glblMsg.B.no(slctLineIndex).cpoDtlLineNum_LL.getValue(); // 2016/08/23 S21_NA#13504 Add
            String storeCpoDtlLineSubNum = glblMsg.B.no(slctLineIndex).cpoDtlLineSubNum_LL.getValue(); // 2016/08/23 S21_NA#13504 Add
            storeOrdLineStsCd = glblMsg.B.no(slctLineIndex).ordLineStsCd_LL.getValue(); // 2016/09/21 S21_NA#10274 Add
            storeOrdLineStsDescTxt = glblMsg.B.no(slctLineIndex).ordLineStsDescTxt_LL.getValue(); // 2016/09/21 S21_NA#10274
            storeOrdLineStsDescTxtOpt = glblMsg.B.no(slctLineIndex).ordLineStsDescTxt_LD.getValue(); // 2016/09/21 S21_NA#10274
            storeBkMdseCd = glblMsg.B.no(slctLineIndex).mdseCd_LB.getValue(); // 2016/09/21 S21_NA#10274
            storeBkDtlLineNum = glblMsg.B.no(slctLineIndex).cpoDtlLineNum_LB.getValue(); // 2016/09/21 S21_NA#10274
            storeBkDtlLineSubNum = glblMsg.B.no(slctLineIndex).cpoDtlLineSubNum_LB.getValue(); // 2016/09/21 S21_NA#10274
            BigDecimal storeUomQty = null;
            BigDecimal storeOrdQty = null;
            String storeOrgMdseCd = null;
            String storeRtlWhNm = null;
            String storeRtlSwhNm = null;
            String storeSrchOrigItemFlgLL = glblMsg.B.no(slctLineIndex).srchOrigItemFlg_LL.getValue(); // 2016/09/29 S21_NA#14805 ADD
            // 2018/01/25 S21_NA#19808 Add Start
            storeUpdTsDplyTxt = glblMsg.B.no(slctLineIndex).updTsDplyTxt_LL.getValue();
            storeUpdUsrNm =  glblMsg.B.no(slctLineIndex).updUsrNm_LL.getValue();
            storeDsCpoDtlUpdUsrId = glblMsg.B.no(slctLineIndex).dsCpoDtlUpdUsrId_LL.getValue(); // 2018/08/21 S21_NA#27642 Add
            // 2018/01/25 S21_NA#19808 Add End
            // 2018/08/02 S21_NA#26665 add start
            cpoSrcTpDescTxt = glblMsg.B.no(slctLineIndex).cpoSrcTpDescTxt_LL.getValue();
            // 2018/08/02 S21_NA#26665 add end
            // 2019/02/22 S21_NA#30449 Add Start
            boolean isContrSupOrd = isContrSupOrd(bizMsg);
            String dsContrNum = null;
            BigDecimal svcMachMstrPk = null;
            if (isContrSupOrd) {
                dsContrNum = glblMsg.B.no(slctLineIndex).dsContrNum_LL.getValue();
                svcMachMstrPk = glblMsg.B.no(slctLineIndex).svcMachMstrPk_LL.getValue();
            }
            // 2019/02/22 S21_NA#30449 Add End

            String executeSupersede = bizMsg.xxPopPrm_P0.getValue();
            if (ZYPCommonFunc.hasValue(bizMsg.mdseCd_SS) && ZYPConstant.FLG_ON_Y.equals(executeSupersede)) {
                storeUomQty = glblMsg.B.no(slctLineIndex).ordCustUomQty_LL.getValue();
                storeOrdQty = glblMsg.B.no(slctLineIndex).ordQty_LL.getValue();
                storeOrgMdseCd = glblMsg.B.no(slctLineIndex).origMdseCd_LL.getValue();
                storeRtlWhNm = glblMsg.B.no(slctLineIndex).rtlWhNm_LL.getValue();
                storeRtlSwhNm = glblMsg.B.no(slctLineIndex).rtlSwhNm_LL.getValue();
            }

            // 2018/03/20 S21_NA#24842 Add Start
            boolean isConfigExists = NWAL1500CommonLogicForLineConfig.getConfigExist(bizMsg.glblCmpyCd.getValue(), glblMsg.A.no(slctConfIndex).configTpCd_LC.getValue());
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(slctConfIndex).svcConfigMstrPk_LC)) {
                SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = NWAL1500CommonLogicForSaveSubmit.getSvcConfigData(bizMsg, glblMsg.A.no(slctConfIndex).svcConfigMstrPk_LC.getValue());
                if (svcConfigMstrTMsg == null //
                        || !ZYPCommonFunc.hasValue(svcConfigMstrTMsg.mdlId)) {
                    isConfigExists = false;
                }
            }
            // 2018/03/20 S21_NA#24842 Add End

            if (!isConfigExists) { // 2018/03/20 S21_NA#24842 Add Condition
                // Clear Model Information
                glblMsg.A.no(slctConfIndex).mdlId_LC.clear();
                glblMsg.A.no(slctConfIndex).mdlNm_LC.clear();
                glblMsg.A.no(slctConfIndex).mdlDescTxt_LC.clear();
                // Add 2016/03/07 S21_NA#5000#78 Start
                glblMsg.A.no(slctConfIndex).mdlGrpDescTxt_LC.clear();
                glblMsg.A.no(slctConfIndex).svcSegDescTxt_LC.clear();
                glblMsg.A.no(slctConfIndex).svcIstlReqFlg_LC.clear();
                glblMsg.A.no(slctConfIndex).siteSrvyReqFlg_LC.clear();
                // Add 2016/03/07 S21_NA#5000#78 End
            } // 2018/03/20 S21_NA#24842 Add Condition

            // Clear line -> set initial value
            glblMsg.B.no(slctLineIndex).clear();
            glblMsg.B.no(slctLineIndex).dsOrdPosnNum_LL.setValue(storeDsOrdPosnNum);
            glblMsg.B.no(slctLineIndex).dsCpoLineNum_LL.setValue(storeDsCpoLineNum);
            glblMsg.B.no(slctLineIndex).xxLineNum_LL.setValue(NWAL1500CommonLogic.concatLineNum(glblMsg.B.no(slctLineIndex)));
            // 2018/08/02 S21_NA#26665 add star
            glblMsg.B.no(slctLineIndex).cpoSrcTpDescTxt_LL.setValue(cpoSrcTpDescTxt);
            // 2018/08/02 S21_NA#26665 add end
            // 2016/08/23 S21_NA#13504 Add Start
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).cpoDtlLineNum_LL, storeCpoDtlLineNum);
            if (ZYPCommonFunc.hasValue(storeCpoDtlLineNum)) {
                boolean isSetItem = NWAL1500CommonLogic.isSetMerchandise(bizMsg.glblCmpyCd.getValue(), storeMdseCd);
                if (isSetItem) {
                    glblMsg.B.no(slctLineIndex).cpoDtlLineSubNum_LL.setValue("000");
                } else if (S21StringUtil.isEquals("000", storeCpoDtlLineSubNum)) {
                    glblMsg.B.no(slctLineIndex).cpoDtlLineSubNum_LL.setValue("001");
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).cpoDtlLineSubNum_LL, storeCpoDtlLineSubNum);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).cpoDtlLineSubNum_LL, storeCpoDtlLineSubNum);
            }
            // 2016/08/23 S21_NA#13504 Add End
            // S21_NA#8450
            glblMsg.B.no(slctLineIndex).dsOrdLineCatgCd_LL.setValue(bizMsg.dsOrdLineCatgCd_LD.getValue());
            glblMsg.B.no(slctLineIndex).prcBaseDt_LL.setValue(bizMsg.slsDt.getValue());
            // glblMsg.B.no(slctLineIndex).rddDt_LL.setValue(bizMsg.slsDt.getValue()); // S21_NA#5000#80
            glblMsg.B.no(slctLineIndex).rddDt_LL.setValue(bizMsg.addRddDt.getValue()); // S21_NA#5000#80
            glblMsg.B.no(slctLineIndex).prcCatgNm_LL.setValue(bizMsg.prcCatgNm.getValue());
            glblMsg.B.no(slctLineIndex).flPrcListNm_LL.setValue(bizMsg.flPrcListNm.getValue());
            // 2016/08/30 S21_NA#9806 Mod Start
//            glblMsg.B.no(slctLineIndex).dlrRefNum_LL.setValue(bizMsg.dlrRefNum.getValue());
//            glblMsg.B.no(slctLineIndex).csmpContrNum_LL.setValue(bizMsg.csmpContrNum.getValue());
//             copyCsmpData(glblMsg.A.no(slctConfIndex), glblMsg.B.no(slctLineIndex));
            copyCsmpData(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex), glblMsg.B.no(slctLineIndex)); // QC#22371 2017/12/26 Mod // 2018/01/25 S21_NA#19808 Mod
            // 2016/08/30 S21_NA#9806 Mod End
            glblMsg.B.no(slctLineIndex).invQty_LL.setValue(BigDecimal.ZERO);

            // Set Store Data
            glblMsg.B.no(slctLineIndex).mdseCd_LL.setValue(storeMdseCd);
            if (ZYPCommonFunc.hasValue(bizMsg.mdseCd_SS) && ZYPConstant.FLG_ON_Y.equals(executeSupersede)) {
                glblMsg.B.no(slctLineIndex).ordCustUomQty_LL.setValue(storeUomQty);
                glblMsg.B.no(slctLineIndex).ordQty_LL.setValue(storeOrdQty);
                glblMsg.B.no(slctLineIndex).origMdseCd_LL.setValue(storeOrgMdseCd);
                glblMsg.B.no(slctLineIndex).rtlWhNm_LL.setValue(storeRtlWhNm);
                glblMsg.B.no(slctLineIndex).rtlSwhNm_LL.setValue(storeRtlSwhNm);
            }
            // S21_NA#1634 end
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).srchOrigItemFlg_LL, storeSrchOrigItemFlgLL); // 2016/09/29 S21_NA#14805 ADD

            inputMdseValue = glblMsg.B.no(slctLineIndex).mdseCd_LL;
            custMdseCd = glblMsg.B.no(slctLineIndex).custMdseCd_LL;
            mnfItemCd = glblMsg.B.no(slctLineIndex).mnfItemCd_LL;
            origMdseCd = glblMsg.B.no(slctLineIndex).origMdseCd_LL;
            srchOrigItemFlg = glblMsg.B.no(slctLineIndex).srchOrigItemFlg_LL; // 2016/09/29 S21_NA#14805 ADD
            ordCustUomQty = glblMsg.B.no(slctLineIndex).ordCustUomQty_LL; // 2017/03/02 S21_NA#17714-2 Add

            // 2016/09/21 S21_NA#10274 Add Start
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).ordLineStsCd_LL, storeOrdLineStsCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).ordLineStsDescTxt_LL, storeOrdLineStsDescTxt);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).ordLineStsDescTxt_LD, storeOrdLineStsDescTxtOpt);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).mdseCd_LB, storeBkMdseCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).cpoDtlLineNum_LB, storeBkDtlLineNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).cpoDtlLineSubNum_LB, storeBkDtlLineSubNum);
            // 2016/09/21 S21_NA#10274 Add End
            // 2018/01/25 S21_NA#19808 Add Start
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).updTsDplyTxt_LL, storeUpdTsDplyTxt);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).updUsrNm_LL, storeUpdUsrNm);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).dsCpoDtlUpdUsrId_LL, storeDsCpoDtlUpdUsrId); // 2018/08/21 S21_NA#27642 Add
            // 2018/01/25 S21_NA#19808 Add End
            // 2019/02/22 S21_NA#30449 Add Start
            if (isContrSupOrd) {
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).dsContrNum_LL, dsContrNum);
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).svcMachMstrPk_LL, svcMachMstrPk);
            }
            // 2019/02/22 S21_NA#30449 Add End
        } else {

            // S21_NA#1634 start
            // store values
            String storeMdseCd = glblMsg.D.no(slctLineIndex).mdseCd_RL.getValue();
            String storeDsOrdPosnNum = glblMsg.D.no(slctLineIndex).dsOrdPosnNum_RL.getValue();
            BigDecimal storeDsCpoLineNum = glblMsg.D.no(slctLineIndex).dsCpoLineNum_RL.getValue();
            String storeCpoDtlLineNum = glblMsg.D.no(slctLineIndex).cpoDtlLineNum_RL.getValue(); // 2016/08/23 S21_NA#13504 Add
            String storeCpoDtlLineSubNum = glblMsg.D.no(slctLineIndex).cpoDtlLineSubNum_RL.getValue(); // 2016/08/23 S21_NA#13504 Add
            storeOrdLineStsCd = glblMsg.D.no(slctLineIndex).ordLineStsCd_RL.getValue(); // 2016/09/21 S21_NA#10274 Add
            storeOrdLineStsDescTxt = glblMsg.D.no(slctLineIndex).rtrnLineStsDescTxt_RL.getValue(); // 2016/09/21 S21_NA#10274 Add
            storeOrdLineStsDescTxtOpt = glblMsg.D.no(slctLineIndex).rtrnLineStsDescTxt_RB.getValue(); // 2016/09/21 S21_NA#10274 Add
            storeOrigUpdtDt = glblMsg.D.no(slctLineIndex).ezUpTime_RL.getValue(); // 2016/09/21 S21_NA#10274 Add
            storeOrigTimeZone = glblMsg.D.no(slctLineIndex).ezUpTimeZone_RL.getValue(); // 2016/09/21 S21_NA#10274 Add
            storeBkMdseCd = glblMsg.D.no(slctLineIndex).mdseCd_RB.getValue(); // 2016/09/21 S21_NA#10274
            storeBkDtlLineNum = glblMsg.D.no(slctLineIndex).cpoDtlLineNum_RB.getValue(); // 2016/09/21 S21_NA#10274
            storeBkDtlLineSubNum = glblMsg.D.no(slctLineIndex).cpoDtlLineSubNum_RB.getValue(); // 2016/09/21 S21_NA#10274

            // 2018/01/25 S21_NA#19808 Add Start
            storeUpdTsDplyTxt = glblMsg.D.no(slctLineIndex).updTsDplyTxt_RL.getValue();
            storeUpdUsrNm =  glblMsg.D.no(slctLineIndex).updUsrNm_RL.getValue();
            // 2018/01/25 S21_NA#19808 Add End
            String storeSrchOrigItemFlgRL = glblMsg.D.no(slctLineIndex).srchOrigItemFlg_RL.getValue(); // QC#26415 2018/06/07 Add
            // 2018/08/02 S21_NA#26665 add start
            cpoSrcTpDescTxt = glblMsg.D.no(slctLineIndex).cpoSrcTpDescTxt_RL.getValue();
            // 2018/08/02 S21_NA#26665 add end

            // clear line -> set initial value
            glblMsg.D.no(slctLineIndex).clear();
            glblMsg.D.no(slctLineIndex).dsOrdPosnNum_RL.setValue(storeDsOrdPosnNum);
            glblMsg.D.no(slctLineIndex).dsCpoLineNum_RL.setValue(storeDsCpoLineNum);
            glblMsg.D.no(slctLineIndex).xxLineNum_RL.setValue(NWAL1500CommonLogic.concatLineNum(glblMsg.D.no(slctLineIndex)));
            // 2018/08/02 S21_NA#26665 add start
            glblMsg.D.no(slctLineIndex).cpoSrcTpDescTxt_RL.setValue(cpoSrcTpDescTxt);
            // 2018/08/02 S21_NA#26665 add end
            // 2016/08/23 S21_NA#13504 Add Start
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).cpoDtlLineNum_RL, storeCpoDtlLineNum);
            if (ZYPCommonFunc.hasValue(storeCpoDtlLineNum)) {
                boolean isSetItem = NWAL1500CommonLogic.isSetMerchandise(bizMsg.glblCmpyCd.getValue(), storeMdseCd);
                if (isSetItem) {
                    glblMsg.D.no(slctLineIndex).cpoDtlLineSubNum_RL.setValue("000");
                } else if (S21StringUtil.isEquals("000", storeCpoDtlLineSubNum)) {
                    glblMsg.D.no(slctLineIndex).cpoDtlLineSubNum_RL.setValue("001");
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).cpoDtlLineSubNum_RL, storeCpoDtlLineSubNum);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).cpoDtlLineSubNum_RL, storeCpoDtlLineSubNum);
            }
            // 2016/08/23 S21_NA#13504 Add End
            // S21_NA#8450
            glblMsg.D.no(slctLineIndex).dsOrdLineCatgCd_RL.setValue(bizMsg.dsOrdLineCatgCd_RD.getValue());
            glblMsg.D.no(slctLineIndex).prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
            // glblMsg.D.no(slctLineIndex).rqstPickUpDt_RL.setValue(bizMsg.slsDt.getValue()); // S21_NA#5000#80
            glblMsg.D.no(slctLineIndex).rqstPickUpDt_RL.setValue(bizMsg.addRddDt.getValue()); // S21_NA#5000#80
            glblMsg.D.no(slctLineIndex).prcCatgNm_RL.setValue(bizMsg.prcCatgNm.getValue());
            glblMsg.D.no(slctLineIndex).flPrcListNm_RL.setValue(bizMsg.flPrcListNm.getValue());
            glblMsg.D.no(slctLineIndex).rtrnQty_RL.setValue(BigDecimal.ZERO);
            glblMsg.D.no(slctLineIndex).cancQty_RL.setValue(BigDecimal.ZERO);
            // 2016/08/30 S21_NA#9806 Mod Start
//            glblMsg.D.no(slctLineIndex).dlrRefNum_RL.setValue(bizMsg.dlrRefNum.getValue());
//            glblMsg.D.no(slctLineIndex).csmpContrNum_RL.setValue(bizMsg.csmpContrNum.getValue());
            // copyCsmpData(glblMsg.C.no(slctConfIndex), glblMsg.D.no(slctLineIndex));
            copyCsmpData(bizMsg, glblMsg.C.no(slctConfIndex), glblMsg.D.no(slctLineIndex)); // QC#22371 2017/12/26 Mod
            // 2016/08/30 S21_NA#9806 Mod Start

            // Set Store Data
            glblMsg.D.no(slctLineIndex).mdseCd_RL.setValue(storeMdseCd);
            // S21_NA#1634 end

            inputMdseValue = glblMsg.D.no(slctLineIndex).mdseCd_RL;
            custMdseCd = glblMsg.D.no(slctLineIndex).custMdseCd_RL;
            mnfItemCd = glblMsg.D.no(slctLineIndex).mnfItemCd_RL;
            origMdseCd = glblMsg.D.no(slctLineIndex).origMdseCd_RL;
            ordCustUomQty = glblMsg.D.no(slctLineIndex).ordCustUomQty_RL; // 2017/03/02 S21_NA#17714-2 Add

            // 2016/09/21 S21_NA#10274 Add Start
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).ordLineStsCd_RL, storeOrdLineStsCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).rtrnLineStsDescTxt_RL, storeOrdLineStsDescTxt);
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).rtrnLineStsDescTxt_RB, storeOrdLineStsDescTxtOpt);
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).ezUpTime_RL, storeOrigUpdtDt);
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).ezUpTimeZone_RL, storeOrigTimeZone);
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).mdseCd_RB, storeBkMdseCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).cpoDtlLineNum_RB, storeBkDtlLineNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).cpoDtlLineSubNum_RB, storeBkDtlLineSubNum);
            // 2016/09/21 S21_NA#10274 Add End
            // 2018/01/25 S21_NA#19808 Add Start
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).updTsDplyTxt_RL, storeUpdTsDplyTxt);
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).updUsrNm_RL, storeUpdUsrNm);
            // 2018/01/25 S21_NA#19808 Add End
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).srchOrigItemFlg_RL, storeSrchOrigItemFlgRL); // QC#26415 2018/06/07 Add
            srchOrigItemFlg = glblMsg.D.no(slctLineIndex).srchOrigItemFlg_RL; // QC#26415 2018/06/07 Add
        }

        String inputMdseCd = NWAL1500CommonLogicForLineConfig.checkMdseCd(bizMsg, inputMdseValue, custMdseCd, mnfItemCd, origMdseCd, srchOrigItemFlg);
        if (!ZYPCommonFunc.hasValue(inputMdseCd)) {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                EZDMsg.copy(glblMsg.B.no(slctLineIndex), null, bizLineMsg, null);
            } else if (TAB_RMA.equals(dplyTab)) {
                EZDMsg.copy(glblMsg.D.no(slctLineIndex), null, bizRmaLineMsg, null);
            }
            return;
        }

        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), inputMdseCd);
        // 2017/03/13 S21_NA#16987 Mod Start
        if (mdseTMsg != null) {
            if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.sellHldFlg.getValue()) || ZYPConstant.FLG_OFF_N.equals(mdseTMsg.custOrdEnblFlg.getValue())) {
                inputMdseValue.setErrorInfo(1, NWAM0037E);
                if (TAB_LINE_CONFIG.equals(dplyTab)) {
                    EZDMsg.copy(glblMsg.B.no(slctLineIndex), null, bizLineMsg, null);
                } else if (TAB_RMA.equals(dplyTab)) {
                    EZDMsg.copy(glblMsg.D.no(slctLineIndex), null, bizRmaLineMsg, null);
                }
                return;
            }
        } else {
            inputMdseValue.setErrorInfo(1, NWAM0037E);
            return;
        }
        // 2017/03/13 S21_NA#16987 Mod End
        if (!NWAL1500CommonLogicForLineConfig.setItemInfoForOnBlurItem(bizMsg, glblMsg, slctConfIndex, slctLineIndex, inputMdseCd, mdseTMsg)) { // 2018/01/25 S21_NA#19808 Mod
            return;
        }

        String mdseCd = mdseTMsg.mdseCd.getValue();
        BigDecimal eachQty = NWAL1500CommonLogicForLineConfig.setEachQty(bizMsg, glblMsg, slctLineIndex, mdseCd); // 2018/01/25 S21_NA#19808 Mod
        if (eachQty != null && !NWAL1500CommonLogicForLineConfig.checkQtyForSerializedItem(bizMsg, mdseCd, eachQty.intValue())) {
            // inputMdseValue.setErrorInfo(1, NWAM0772E); 2017/03/02 S21_NA#17714-2 Del
            ordCustUomQty.setErrorInfo(1, NWAM0772E); // 2017/03/02 S21_NA#17714-2 Add
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                EZDMsg.copy(glblMsg.B.no(slctLineIndex), null, bizLineMsg, null);
            } else if (TAB_RMA.equals(dplyTab)) {
                EZDMsg.copy(glblMsg.D.no(slctLineIndex), null, bizRmaLineMsg, null);
            }
            return;
        }

        NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
        // 2018/07/05 QC#25616 Add Start
        NWAL1500CommonLogicForLineConfig.deriveLineFloorPrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
        // 2018/07/05 QC#25616 Add End
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {

            // 2018/03/20 S21_NA#24842 Add Start
            NWAL1500_ASMsg configMsg = glblMsg.A.no(slctConfIndex);
            NWAL1500_ACMsg configBizMsg = NWAL1500CommonLogic.getParentConfig(bizMsg, bizLineMsg);
            EZDMsg.copy(configBizMsg, null, configMsg, null);

            boolean isError = NWAL1500CommonLogicForLineConfig.isAddItemHasErrorWithModel(bizMsg, configMsg, glblMsg.B.no(slctLineIndex));
            if (isError) {
                glblMsg.B.no(slctLineIndex).mdseCd_LL.setErrorInfo(1, NWAM0952E, new String[] {configBizMsg.mdlNm_LC.getValue()});
                EZDMsg.copy(glblMsg.B.no(slctLineIndex), null, bizLineMsg, null);
                return;
            }
            // 2018/03/20 S21_NA#24842 Add End
            // 2018/01/25 S21_NA#19808 Del Start =>
//            // QC743
//            // int lineIndex = NWAL1500CommonLogicForLineConfig.getLineIndex(glblMsg.J, bizMsg.B.no(slctLineIndex).dsOrdPosnNum_LL.getValue(), bizMsg.B.no(slctLineIndex).dsCpoLineNum_LL.getValue(),
//            //         bizMsg.B.no(slctLineIndex).dsCpoLineSubNum_LL.getValue());
//            int lineIndex = NWAL1500CommonLogicForLineConfig.getLineIndex(bizMsg.B, bizMsg.B.no(slctLineIndex).dsOrdPosnNum_LL.getValue(), bizMsg.B.no(slctLineIndex).dsCpoLineNum_LL.getValue(),
//                    bizMsg.B.no(slctLineIndex).dsCpoLineSubNum_LL.getValue());
//
//            String posnNum = bizMsg.B.no(slctLineIndex).dsOrdPosnNum_LL.getValue();
//            // QC743
//            // NWAL1500CommonLogicForLineControl.storeLineByConfig(glblMsg.J, bizMsg.B, posnNum); // S21_NA#1670
//            // if (lineIndex < 0) {
//            //    int insertRow = NWAL1500CommonLogicForConfigId.getAddLineRow(glblMsg.J, posnNum);
//            //    EZDMsg line = NWAL1500CommonLogicForConfigId.insertNewLine(glblMsg.J, insertRow);
//            //    lineIndex = insertRow;
//            // } else {
//            //     // set current component
//            //     EZDMsg.copy(bizMsg.B.no(slctLineIndex), null, glblMsg.J.no(lineIndex), null);
//            // }
//            if (lineIndex < 0) {
//                int insertRow = NWAL1500CommonLogicForConfigId.getAddLineRow(bizMsg.B, posnNum);
//                // EZDMsg line = NWAL1500CommonLogicForConfigId.insertNewLine(bizMsg.B, insertRow);
//                lineIndex = insertRow;
//            }
            // 2018/01/25 S21_NA#19808 Del End <=
            String posnNum = glblMsg.B.no(slctLineIndex).dsOrdPosnNum_LL.getValue(); // 2018/01/25 S21_NA#19808

            // for set merchandise
            // QC743
            // NWAL1500CommonLogicForLineControl.setChildLine(bizMsg, glblMsg, glblMsg.J, lineIndex);
            // 2016/11/11 S21_NA#9864-2 Mod Start
            // NWAL1500CommonLogicForLineControl.setChildLine(bizMsg, glblMsg, bizMsg.B, lineIndex);
            if (NWAL1500CommonLogicForLineControl.setChildLine(bizMsg, glblMsg, glblMsg.B, slctLineIndex) < 0) { // 2018/01/25 S21_NA#19808 Mod
                glblMsg.B.no(slctLineIndex).mdseCd_LL.setErrorInfo(1, NWAM0914E, new String[] {bizMsg.B.no(slctLineIndex).mdseCd_LL.getValue()});
                EZDMsg.copy(glblMsg.B.no(slctLineIndex), null, bizLineMsg, null);
                return;
            }
            // 2016/11/11 S21_NA#9864-2 Mod End

            // QC743
            // // prepare screen
            // NWAL1500CommonLogicForLineControl.prepareLineS2C(bizMsg.B, glblMsg.J, posnNum);

//            String configTp = glblMsg.A.no(slctConfIndex).configTpCd_LC.getValue(); // 2018/01/25 S21_NA#19808 Del
            // if (!CONFIG_TP.EXISTING.equals(configTp) &&
            // !CONFIG_TP.ADD_TO_CONFIG.equals(configTp)) {
            // S21_NA#955
            // Out bound Y N N
//            if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configTp, CONFIG_CATG.OUTBOUND, true, false, false)) { // 2016/01/25 S21_NA#3507
              // 2016/11/08 S21_NA#7749 Del
//            if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configTp, CONFIG_CATG.OUTBOUND, true, false, true)) {
                // clear base component flag
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                if (S21StringUtil.isEquals(posnNum, glblMsg.B.no(i).dsOrdPosnNum_LL.getValue())) { // 2018/01/25 S21_NA#19808 Mod
                    glblMsg.B.no(i).baseCmptFlg_LL.clear();
                }
             }
                // 2016/07/11 S21_NA#7821 Mod Start
//                for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//                    if (S21StringUtil.isEquals(posnNum, bizMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
//                        NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, slctConfIndex, i);
//                    }
//                }
            // 2018/05/20 S21_NA#25604 Del Start
//            // 2018/01/11 S21_NA#23329 Mod Start
////            NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, slctConfIndex);
//            int baseCompIndex = NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex)); // 2018/01/25 S21_NA#19808 Mod
//            boolean needCallMdlApi = true;
//            if (baseCompIndex < 0 //
//                    && NWXC150001DsCheck.isModelSoftware(glblMsg.A.no(slctConfIndex).svcMdlTpCd_LC.getValue()) // 2018/01/25 S21_NA#19808 Mod
//                    && ZYPCommonFunc.hasValue(glblMsg.A.no(slctConfIndex).prntMdseCd_LC)) { // 2018/01/25 S21_NA#19808 Mod
//                // 2018/03/14 S21_NA#24117-1 mod start
//                NWAL1500CommonLogicForLineConfig.setSoftModelParent(bizMsg, glblMsg, slctConfIndex, glblMsg.A.no(slctConfIndex).prntMdseCd_LC.getValue(), false); // 2018/01/25 S21_NA#19808 Mod
//                // 2018/03/14 S21_NA#24117-1 mod end
//                needCallMdlApi = false;
//            }
//            // 2018/01/11 S21_NA#23329 Mod End
//                // 2016/07/11 S21_NA#7821 Mod End
//              // 2016/11/08 S21_NA#7749 Del
////            }
//            NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, slctConfIndex); // 2018/01/25 S21_NA#19808 Mod
            // 2018/05/20 S21_NA#25604 Del End
            // 2018/05/20 S21_NA#25604 Add Start
            NWAL1500CommonLogicForLineConfig.deriveBaseComponentFlagAndModel(bizMsg, glblMsg, slctConfIndex);
            NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, slctConfIndex);
            // 2018/05/20 S21_NA#25604 Add End
            // 2018/01/11 S21_NA#23329 Del Start
//            NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, slctConfIndex);
            // 2018/01/11 S21_NA#23329 Del End
            // 2018/05/20 S21_NA#25604 Del Start
//            // 2018/01/11 S21_NA#23329 Add Start
//            if (needCallMdlApi) {
//                 NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex)); // 2018/01/25 S21_NA#19808 Mod
//            }
//            // 2018/01/11 S21_NA#23329 Add End
            // 2018/05/20 S21_NA#25604 Del End

            // 2016/09/21 S21_NA#10274 Add Start
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).ordLineStsCd_LL, storeOrdLineStsCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).ordLineStsDescTxt_LL, storeOrdLineStsDescTxt);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).ordLineStsDescTxt_LD, storeOrdLineStsDescTxtOpt);
            // 2016/09/21 S21_NA#10274 Add End
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);  // 2018/01/25 S21_NA#19808 Mod
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_LL.getValueInt());  // 2018/01/25 S21_NA#19808 Mod

            // 2018/08/21 S21_NA#26767 Add Start
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.shpgSerTakeFlg.getValue())) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSerial(bizMsg, glblMsg);
            } else {
                if (ZYPCommonFunc.hasValue(glblMsg.B.no(slctLineIndex).ordCustUomQty_LL)) {
                    if (NWAL1500CommonLogicForLineConfig.setDefaultLineCategoryForNonSerial(bizMsg, glblMsg, glblMsg.B.no(slctLineIndex))) {
                        NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
                        NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_LL.getValueInt());
                    }
                }
            }
            // 2018/08/21 S21_NA#26767 Add End
        } else {

            // 2018/01/25 S21_NA#19808 Del Start =>
//            // QC743
//            // int lineIndex = NWAL1500CommonLogicForLineConfig.getLineIndex(glblMsg.K, bizMsg.D.no(slctLineIndex).dsOrdPosnNum_RL.getValue(), bizMsg.D.no(slctLineIndex).dsCpoLineNum_RL.getValue(),
//            //         bizMsg.D.no(slctLineIndex).dsCpoLineSubNum_RL.getValue());
//            int lineIndex = NWAL1500CommonLogicForLineConfig.getLineIndex(bizMsg.D, bizMsg.D.no(slctLineIndex).dsOrdPosnNum_RL.getValue(), bizMsg.D.no(slctLineIndex).dsCpoLineNum_RL.getValue(),
//                    bizMsg.D.no(slctLineIndex).dsCpoLineSubNum_RL.getValue());
//
//            // String posnNum = bizMsg.D.no(slctLineIndex).dsOrdPosnNum_RL.getValue();
//            // QC743
//            // NWAL1500CommonLogicForLineControl.storeLineByConfig(glblMsg.K, bizMsg.D, posnNum); // S21_NA#1670
//            // // set current component
//            // if (lineIndex < 0) {
//            //     int insertRow = NWAL1500CommonLogicForConfigId.getAddLineRow(glblMsg.K, posnNum);
//            //     EZDMsg line = NWAL1500CommonLogicForConfigId.insertNewLine(glblMsg.K, insertRow);
//            //     EZDMsg.copy(bizMsg.D.no(slctLineIndex), null, line, null);
//            //     lineIndex = insertRow;
//            //  } else {
//            //      // set current component
//            //      EZDMsg.copy(bizMsg.D.no(slctLineIndex), null, glblMsg.K.no(lineIndex), null);
//            //  }
//            // if (lineIndex < 0) {
//                // int insertRow = NWAL1500CommonLogicForConfigId.getAddLineRow(bizMsg.D, posnNum);
//                // EZDMsg line = NWAL1500CommonLogicForConfigId.insertNewLine(bizMsg.D, insertRow);
//            // }
//
//            // for set merchandise
//            // QC743
//            //NWAL1500CommonLogicForLineControl.setChildLine(bizMsg, glblMsg, glblMsg.K, lineIndex);
            // 2018/01/25 S21_NA#19808 Del End <=
            NWAL1500CommonLogicForLineControl.setChildLine(bizMsg, glblMsg, glblMsg.D, slctLineIndex); // 2018/01/25 S21_NA#19808 Mod

            // prepare screen
            // QC743
            // NWAL1500CommonLogicForLineControl.prepareLineS2C(bizMsg.D, glblMsg.K, posnNum);

            // 2017/11/21 S21_NA#22555 Add Start
            String posnNum = glblMsg.D.no(slctLineIndex).dsOrdPosnNum_RL.getValue(); // 2018/01/25 S21_NA#19808 Mod
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {  // 2018/01/25 S21_NA#19808 Mod
                if (S21StringUtil.isEquals(posnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue())) { // 2018/01/25 S21_NA#19808 Mod
                    glblMsg.D.no(i).baseCmptFlg_RL.clear(); // 2018/01/25 S21_NA#19808 Mod
                }
            }
            Map<String, Map<String, String>> baseComponentMap = new HashMap<String, Map<String, String>>();
            NWAL1500CommonLogic.setBaseComponentFlagForRma(bizMsg, glblMsg, slctConfIndex, baseComponentMap, true); // 2018/01/25 S21_NA#19808 Mod
            // 2017/11/21 S21_NA#22555 Add End

            NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, slctConfIndex); // 2018/01/25 S21_NA#19808 
            // 2017/11/21 S21_NA#22555 Add Start
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.C.no(slctConfIndex).xxYesNoCd_RC)) {
                NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.C.no(slctConfIndex)); // 2018/01/25 S21_NA#19808 
            }
            // 2017/11/21 S21_NA#22555 Add End

            // 2016/09/21 S21_NA#10274 Add Start
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).ordLineStsCd_RL, storeOrdLineStsCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).rtrnLineStsDescTxt_RL, storeOrdLineStsDescTxt);
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(slctLineIndex).rtrnLineStsDescTxt_RB, storeOrdLineStsDescTxtOpt);
            // 2016/09/21 S21_NA#10274 Add End
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);  // 2018/01/25 S21_NA#19808 Mod
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_RL.getValueInt());  // 2018/01/25 S21_NA#19808 Mod

            // 2018/08/21 S21_NA#26767 Add Start
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.shpgSerTakeFlg.getValue())) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSerial(bizMsg, glblMsg);
            } else {
                if (ZYPCommonFunc.hasValue(glblMsg.D.no(slctLineIndex).ordCustUomQty_RL)) {
                    if (NWAL1500CommonLogicForLineConfig.setDefaultReturnReasonForNonSerial(bizMsg, glblMsg, glblMsg.D.no(slctLineIndex))) {
                        NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
                        NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_RL.getValueInt());

                        doProcess_NWAL1500Scrn00_OnChange_ReturnReason(bizMsg, glblMsg);
                    }
                }
            }
            // 2018/08/21 S21_NA#26767 Add End
        }
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromQty(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int slctLineIndex = bizMsg.xxCellIdx.getValueInt();
        // 2018/01/25 S21_NA#19808 Add Start
        String dplyTab = bizMsg.xxDplyTab.getValue();
        NWAL1500_BCMsg bizLineMsg = null;
        NWAL1500_DCMsg bizRmaLineMsg = null;
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            bizLineMsg = bizMsg.B.no(slctLineIndex);
            slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizLineMsg, glblMsg);
            EZDMsg.copy(bizLineMsg, null, glblMsg.B.no(slctLineIndex), null);
            BigDecimal qty = bizLineMsg.ordCustUomQty_LL.getValue();
            
        } else {
            bizRmaLineMsg = bizMsg.D.no(slctLineIndex);
            slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizRmaLineMsg, glblMsg);
            EZDMsg.copy(bizRmaLineMsg, null, glblMsg.D.no(slctLineIndex), null);
            BigDecimal qty = bizRmaLineMsg.ordCustUomQty_RL.getValue();
        }
        // 2018/01/25 S21_NA#19808 Add End
        int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(dplyTab, glblMsg, slctLineIndex); // 2018/01/25 S21_NA#19808
//        String dplyTab = bizMsg.xxDplyTab.getValue(); 2018/01/25 S21_NA#19808 Del
        // 2016/11/08 S21_NA#7749 Add
        String configTpCd = glblMsg.A.no(slctConfIndex).configTpCd_LC.getValue(); // 2018/01/25 S21_NA#19808
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N); // 2019/04/15 S21_NA#31184 Add Start

        // 2018/01/25 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        EZDSStringItem inputMdseCd = null;
        EZDSStringItem inputUomCd = null;
        EZDSBigDecimalItem inputUomQty = null;
        EZDSBigDecimalItem inputEachQty = null;
        EZDSBigDecimalItem ordCustUomQty = null; // 2017/03/02 S21_NA#17714-2 Add

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineIndex);
            inputMdseCd = lineMsg.mdseCd_LL;
            inputUomCd = lineMsg.custUomCd_LL;
            inputUomQty = lineMsg.ordCustUomQty_LL;
            inputEachQty = lineMsg.ordQty_LL;
            ordCustUomQty = lineMsg.ordCustUomQty_LL; //  2017/03/02 S21_NA#17714-2 Add
        } else {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(slctLineIndex);
            inputMdseCd = rmaLineMsg.mdseCd_RL;
            inputUomCd = rmaLineMsg.custUomCd_RL;
            inputUomQty = rmaLineMsg.ordCustUomQty_RL;
            inputEachQty = rmaLineMsg.ordQty_RL;
            ordCustUomQty = rmaLineMsg.ordCustUomQty_RL; //  2017/03/02 S21_NA#17714-2 Add
        }

        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), inputMdseCd.getValue());

        if (mdseTMsg == null) {
            inputUomQty.clear();
            inputMdseCd.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_ITEM });
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                EZDMsg.copy(glblMsg.B.no(slctLineIndex), null, bizLineMsg, null);
            } else if (TAB_RMA.equals(dplyTab)) {
                EZDMsg.copy(glblMsg.D.no(slctLineIndex), null, bizRmaLineMsg, null);
            }
            return;
        }
        // 2017/12/01 S21_NA#21580 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.sellHldFlg.getValue()) || ZYPConstant.FLG_OFF_N.equals(mdseTMsg.custOrdEnblFlg.getValue())) {
            inputUomQty.clear();
            inputMdseCd.setErrorInfo(1, NWAM0037E);
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                EZDMsg.copy(glblMsg.B.no(slctLineIndex), null, bizLineMsg, null);
            } else if (TAB_RMA.equals(dplyTab)) {
                EZDMsg.copy(glblMsg.D.no(slctLineIndex), null, bizRmaLineMsg, null);
            }
            return;
        }
        // 2017/12/01 S21_NA#21580 Add End

        String mdseCd = mdseTMsg.mdseCd.getValue();
        BigDecimal eachQty = NWAL1500CommonLogicForLineConfig.getEachQty(bizMsg, mdseCd, inputUomCd.getValue(), inputUomQty.getValue());
        ZYPEZDItemValueSetter.setValue(inputEachQty, eachQty);

//        if (TAB_LINE_CONFIG.equals(dplyTab) && eachQty != null && !NWAL1500CommonLogicForLineConfig.checkQtyForSerializedItem(bizMsg, mdseCd, eachQty.intValue())) { 2016/04/08 S21_NA#5356 Need check on RMA Tab
        if (eachQty != null && !NWAL1500CommonLogicForLineConfig.checkQtyForSerializedItem(bizMsg, mdseCd, eachQty.intValue())) {
            // inputMdseCd.setErrorInfo(1, NWAM0772E); 2017/03/02 S21_NA#17714-2 Del
            ordCustUomQty.setErrorInfo(1, NWAM0772E); // 2017/03/02 S21_NA#17714-2 Add
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                EZDMsg.copy(glblMsg.B.no(slctLineIndex), null, bizLineMsg, null);
            } else if (TAB_RMA.equals(dplyTab)) {
                EZDMsg.copy(glblMsg.D.no(slctLineIndex), null, bizRmaLineMsg, null);
            }
            return;
        }

        // 2016/11/08 S21_NA#7749 Add Start
        if (NWXC150001DsCheck.isIBQtyFrceConfigTp(bizMsg.glblCmpyCd.getValue(), configTpCd, CONFIG_CATG.OUTBOUND)) {
            if (NWXC150001DsCheck.chekcQtyForIBCtrlItem(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue(), bizMsg.dsOrdRsnCd.getValue(), mdseCd, glblMsg.B.no(slctLineIndex).ordQty_LL.getValueInt())) {
                inputMdseCd.setErrorInfo(1, NWAM0907E);
                if (TAB_LINE_CONFIG.equals(dplyTab)) {
                    EZDMsg.copy(glblMsg.B.no(slctLineIndex), null, bizLineMsg, null);
                } else if (TAB_RMA.equals(dplyTab)) {
                    EZDMsg.copy(glblMsg.D.no(slctLineIndex), null, bizRmaLineMsg, null);
                }
                return; // 2016/11/15 S21_NA#7749-2
            }
        }
        // 2016/11/08 S21_NA#7749 Add Start

        // S21_NA QC#3649 Add Start
        // Call Default WH API
        // Mod Start 2017/01/31 QC#17257
//        if (TAB_LINE_CONFIG.equals(dplyTab) && !NWAL1500CommonLogicForLineConfig.callDefWhApi(bizMsg, slctConfIndex, slctLineIndex, mdseCd)) {
        // 2017/09/01 QC#19749 Del Start
//        if (!NWAL1500CommonLogicForLineConfig.callDefWhApi(bizMsg, slctConfIndex, slctLineIndex, mdseCd)) {
//        // Mod Start 2017/01/31 QC#17257
//            return;
//        }
        // 2017/09/01 QC#19749 Del End
        // 2018/09/20 S21_NA#28199 Add Start
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex));
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500_DSMsg rmaLine = glblMsg.D.no(slctLineIndex);
            String dsOrdPosnNum = rmaLine.dsOrdPosnNum_RL.getValue();
            List<String> posnNums = new ArrayList<String>();
            posnNums.add(dsOrdPosnNum);
            NWAL1500CommonLogicForLineConfig.updateBaseCmptFlg(bizMsg, glblMsg, posnNums, false);
        }
        // 2018/09/20 S21_NA#28199 Add End
        NWAL1500CommonLogic.setForceDummyWh(bizMsg, glblMsg.B.no(slctLineIndex)); // 2018/03/16 S21_NA#24459 Add
        // 2017/09/22 QC#21336 Add Start
        if (TAB_LINE_CONFIG.equals(dplyTab) && !NWAL1500CommonLogicForLineConfig.callDefWhApi(bizMsg, glblMsg, slctConfIndex, slctLineIndex, mdseCd)) { // 2018/01/25 S21_NA#19808 Mod
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                EZDMsg.copy(glblMsg.B.no(slctLineIndex), null, bizLineMsg, null);
            } else if (TAB_RMA.equals(dplyTab)) {
                EZDMsg.copy(glblMsg.D.no(slctLineIndex), null, bizRmaLineMsg, null);
            }
            return;
        }
        // 2017/09/22 QC#21336 Add End

        // Call Supersede API
        if (TAB_LINE_CONFIG.equals(dplyTab) && !NWAL1500CommonLogicForLineConfig.callSupersedeApi(bizMsg, glblMsg.B.no(slctLineIndex), mdseCd)) { // 2018/01/25 S21_NA#19808 Mod
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                EZDMsg.copy(glblMsg.B.no(slctLineIndex), null, bizLineMsg, null);
            } else if (TAB_RMA.equals(dplyTab)) {
                EZDMsg.copy(glblMsg.D.no(slctLineIndex), null, bizRmaLineMsg, null);
            }
            return;
        }
        // S21_NA QC#3649 Add End

        // Call Pricing API
        NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);

        // QC#22372 2018/01/10 Add Start
        // Call Pricing API For Floor Price
        NWAL1500CommonLogicForLineConfig.deriveLineFloorPrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
        // QC#22372 2018/01/10 Add End

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500_BSMsg line = glblMsg.B.no(slctLineIndex);
            String dsOrdPosnNum = line.dsOrdPosnNum_LL.getValue();
            BigDecimal dsCpoLineNum = line.dsCpoLineNum_LL.getValue();
            BigDecimal dsCpoLineSubNum = line.dsCpoLineSubNum_LL.getValue();
            //QC743
            //NWAL1500CommonLogicForLineControl.storeLineByConfig(glblMsg.J, glblMsg.B, dsOrdPosnNum);

            // for set merchandise
            // int lineIndex = NWAL1500CommonLogicForLineControl.getLineRow(glblMsg.J, dsOrdPosnNum, dsCpoLineNum, dsCpoLineSubNum);
            // NWAL1500CommonLogicForLineControl.setChildLine(bizMsg, glblMsg, glblMsg.J, lineIndex);
            // NWAL1500CommonLogicForLineControl.prepareLineS2C(glblMsg.B, glblMsg.J, dsOrdPosnNum);
            // int lineIndex = NWAL1500CommonLogicForLineControl.getLineRow(glblMsg.B, dsOrdPosnNum, dsCpoLineNum, dsCpoLineSubNum);  2018/01/25 S21_NA#19808 Del
            // NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, glblMsg.B.no(lineIndex)); // 2016/03/15 S21_NA#4691 Add -> 2016/10/05 S21_NA#7645-3 Del
            //NWAL1500CommonLogicForLineControl.setChildLine(bizMsg, glblMsg, glblMsg.B, lineIndex);
            // NWAL1500CommonLogicForLineControl.setChildUpdate(glblMsg.B, lineIndex); 2016/03/15 S21_NA#4691 Change Interface
            NWAL1500CommonLogicForLineControl.setChildUpdate(bizMsg, glblMsg.B, slctLineIndex); // 2018/01/25 S21_NA#19808 Mod

            // Out bound Y N N
              // 2016/11/08 S21_NA#7749 Del
//            if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), glblMsg.A.no(slctConfIndex).configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, true, false, false)) {

                // 2016/07/11 S21_NA#7821 Mod Start
//                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
//                    if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
//                        NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, slctConfIndex, i);
//                    }
//                }
            // 2018/09/20 S21_NA#28199 Del Start
//            NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex)); // 2018/01/25 S21_NA#19808 Mod
            // 2018/09/20 S21_NA#28199 Del End
                // 2016/07/11 S21_NA#7821 Mod End
              // 2016/11/08 S21_NA#7749 Del
//            }
            NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, slctConfIndex); // 2018/01/25 S21_NA#19808 Mod
            // 2018/01/25 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
            // 2018/01/25 S21_NA#19808 Add End
            // 2018/08/21 S21_NA#26767 Add Start
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.shpgSerTakeFlg.getValue())) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSerial(bizMsg, glblMsg);
            } else {
                if (ZYPCommonFunc.hasValue(glblMsg.B.no(slctLineIndex).ordCustUomQty_LL)) {
                    if (NWAL1500CommonLogicForLineConfig.setDefaultLineCategoryForNonSerial(bizMsg, glblMsg, glblMsg.B.no(slctLineIndex))) {
                        NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
                        NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_LL.getValueInt());
                    }
                }
            }
            // 2018/08/21 S21_NA#26767 Add End

        } else {
            NWAL1500_DSMsg rmaLine = glblMsg.D.no(slctLineIndex);
            String dsOrdPosnNum = rmaLine.dsOrdPosnNum_RL.getValue();
            BigDecimal dsCpoLineNum = rmaLine.dsCpoLineNum_RL.getValue();
            BigDecimal dsCpoLineSubNum = rmaLine.dsCpoLineSubNum_RL.getValue();
            //QC743
            //NWAL1500CommonLogicForLineControl.storeLineByConfig(glblMsg.K, glblMsg.D, dsOrdPosnNum);

            // for set merchandise
            //QC743
            //int lineIndex = NWAL1500CommonLogicForLineControl.getLineRow(glblMsg.K, dsOrdPosnNum, dsCpoLineNum, dsCpoLineSubNum);
            //NWAL1500CommonLogicForLineControl.setChildLine(bizMsg, glblMsg, glblMsg.K, lineIndex);
            //NWAL1500CommonLogicForLineControl.prepareLineS2C(glblMsg.D, glblMsg.K, dsOrdPosnNum);
//            int lineIndex = NWAL1500CommonLogicForLineControl.getLineRow(glblMsg.D, dsOrdPosnNum, dsCpoLineNum, dsCpoLineSubNum); // 2018/01/25 S21_NA#19808 Del
            // NWAL1500CommonLogicForLineControl.setChildLine(bizMsg, glblMsg, glblMsg.D, lineIndex);
            // NWAL1500CommonLogicForLineControl.setChildUpdate(glblMsg.D, lineIndex); 2016/10/31 S21_NA#15541 Del
            NWAL1500CommonLogicForLineControl.setChildUpdate(bizMsg, glblMsg.D, slctLineIndex); // 2016/10/31 S21_NA#15541 Add // 2018/01/25 S21_NA#19808 Mod

            NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, slctConfIndex); // 2018/01/25 S21_NA#19808 Mod

            // 2018/09/20 S21_NA#28199 Del Start
//            // Add Start 2017/01/27 QC#17257
//            List<String> posnNums = new ArrayList<String>();
//            posnNums.add(dsOrdPosnNum);
//            NWAL1500CommonLogicForLineConfig.updateBaseCmptFlg(bizMsg, glblMsg, posnNums, false); // 2018/01/25 S21_NA#19808 Mod
//            // Add End 2017/01/27 QC#17257
            // 2018/09/20 S21_NA#28199 Del End

            // 2018/01/25 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
            // 2018/01/25 S21_NA#19808 Add End
            // 2018/08/21 S21_NA#26767 Add Start
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.shpgSerTakeFlg.getValue())) {
                doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSerial(bizMsg, glblMsg);
            } else {
                if (ZYPCommonFunc.hasValue(glblMsg.D.no(slctLineIndex).mdseCd_RL)) {
                    if (NWAL1500CommonLogicForLineConfig.setDefaultReturnReasonForNonSerial(bizMsg, glblMsg, glblMsg.D.no(slctLineIndex))) {
                        NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
                        NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_RL.getValueInt());

                        doProcess_NWAL1500Scrn00_OnChange_ReturnReason(bizMsg, glblMsg);
                    }
                }
            }
            // 2018/08/21 S21_NA#26767 Add End
        }
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSellPrice(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int slctLineIndex = bizMsg.xxCellIdx.getValueInt();
        // 2018/01/25 S21_NA#19808 Add Start
        String dplyTab = bizMsg.xxDplyTab.getValue();
        NWAL1500_BCMsg bizLineMsg = null;
        NWAL1500_DCMsg bizRmaLineMsg = null;
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            bizLineMsg = bizMsg.B.no(slctLineIndex);
            slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizLineMsg, glblMsg);
            EZDMsg.copy(bizLineMsg, null, glblMsg.B.no(slctLineIndex), null);
        } else {
            bizRmaLineMsg = bizMsg.D.no(slctLineIndex);
            slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizRmaLineMsg, glblMsg);
            EZDMsg.copy(bizRmaLineMsg, null, glblMsg.D.no(slctLineIndex), null);
        }
        // 2018/01/25 S21_NA#19808 Add End
        int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(dplyTab, glblMsg, slctLineIndex); // 2018/01/25 S21_NA#19808 Mod

        EZDSStringItem inputMdseCd = null;

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineIndex);
            inputMdseCd = lineMsg.mdseCd_LL;
        } else {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(slctLineIndex);
            inputMdseCd = rmaLineMsg.mdseCd_RL;
        }

        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), inputMdseCd.getValue());

        if (mdseTMsg == null) {
            inputMdseCd.setErrorInfo(1, NWAM0037E);
            return;
        }
        // 2017/12/01 S21_NA#21580 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.sellHldFlg.getValue()) || ZYPConstant.FLG_OFF_N.equals(mdseTMsg.custOrdEnblFlg.getValue())) {
            inputMdseCd.setErrorInfo(1, NWAM0037E);
            return;
        }
        // 2017/12/01 S21_NA#21580 Add End

        NWAL1500CommonLogicForLineConfig.deriveLineManPrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500CommonLogic.checkPriceRange(glblMsg.B.no(slctLineIndex), NWAL1500CommonLogic.getBasePriceDataFromBizlMsg(glblMsg.B.no(slctLineIndex), bizMsg, glblMsg)); // 2018/01/25 S21_NA#19808 Mod
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808
        } else {
            NWAL1500CommonLogic.checkPriceRange(glblMsg.D.no(slctLineIndex), NWAL1500CommonLogic.getBasePriceDataFromBizlMsg(glblMsg.D.no(slctLineIndex), bizMsg, glblMsg)); // 2018/01/25 S21_NA#19808 Mod
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808
        }
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromFreightTerms(NWAL1500CMsg bizMsg) {

        Map<String, String> freightTermInfo = NWAL1500CommonLogicForAddlHeader.getFreightTermInfo(bizMsg);
        if (freightTermInfo == null) {
            bizMsg.frtCondCd.clear();
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, freightTermInfo.get("FRT_COND_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondDescTxt, freightTermInfo.get("FRT_COND_DESC_TXT"));

        // 2016/03/04 S21_NA#1679 Add Start
        NWAL1500CommonLogicForCategory.setShpgSvcLvlPullDown(bizMsg);
        // 2016/03/04 S21_NA#1679 Add End
        // 2018/12/21 S21_NA#29315 Del Start
//        NWAL1500CommonLogic.deriveDefaultCarrSvcLvl(bizMsg); // QC#13688 2017/02/24 Add
        // 2018/12/21 S21_NA#29315 Del End
        // 2019/01/08 S21_NA#29789 Add Start
        if (ZYPCommonFunc.hasValue(bizMsg.frtCondCd) && !FRT_COND.COLLECT.equals(bizMsg.frtCondCd.getValue())) {
            bizMsg.carrAcctNum.clear();
        }
        // 2019/01/08 S21_NA#29789 Add End
    }

    // QC#13688 2017/02/24 Add Start
    private void doProcess_NWAL1500Scrn00_OnChange_ShpgSvcLvlCd(NWAL1500CMsg bizMsg) {
       // 2018/12/21 S21_NA#29315 Del Start
//        NWAL1500CommonLogic.deriveDefaultCarrSvcLvl(bizMsg);
       // 2018/12/21 S21_NA#29315 Del End
    }
    // QC#13688 2017/02/24 Add End

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromPaymentTerms(NWAL1500CMsg bizMsg) {

        Map<String, String> paymentTermInfo = NWAL1500CommonLogicForAddlHeader.getPaymentTermInfo(bizMsg);
        if (paymentTermInfo == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscCd, paymentTermInfo.get("PMT_TERM_CASH_DISC_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscDescTxt, paymentTermInfo.get("PMT_TERM_CASH_DISC_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.pmtCcFlg, paymentTermInfo.get("PMT_CC_FLG"));
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromCarrierServiceLevel(NWAL1500CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForCategory.getInstance().getDsOrdTpList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdTpDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_RSN });
            return;
        }

        ssmResult = NWAL1500QueryForSaveSubmit.getInstance().checkFreightCondText(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.frtCondDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_FRT_TERMS });
            return;
        }

        Map<String, String> carrSvcLvlInfo = NWAL1500CommonLogicForAddlHeader.getCarrSvcLvlInfo(bizMsg);
        if (carrSvcLvlInfo == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlCd, carrSvcLvlInfo.get("CARR_SVC_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlDescTxt, carrSvcLvlInfo.get("CARR_SVC_LVL_DESC_TXT"));
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromCSMPNumber(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod

        int cellIdx = bizMsg.xxCellIdx.getValueInt(); // 2016/08/31 S21_NA#10535 Add
        String dplyTab = bizMsg.xxDplyTab.getValue(); // 2016/08/31 S21_NA#10535 Add
        // 2016/08/31 S21_NA#10535 Add Start
        if (cellIdx >= 0) {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                if (!ZYPCommonFunc.hasValue(bizMsg.A.no(cellIdx).csmpContrNum_LC)) {
                    clearCsmpData(glblMsg, bizMsg.A.no(cellIdx)); // 2018/01/25 S21_NA#19808 Mod
                    // 2018/06/29 S21_NA#27071 Add Start
                    clearBizMsgCsmpNum(bizMsg, bizMsg.A.no(cellIdx).dsOrdPosnNum_LC.getValue());
                    // 2018/06/29 S21_NA#27071 Add End
                    return;
                }
            } else if (TAB_RMA.equals(dplyTab)) {
                if (!ZYPCommonFunc.hasValue(bizMsg.C.no(cellIdx).csmpContrNum_RC)) {
                    clearCsmpData(glblMsg, bizMsg.C.no(cellIdx)); // 2018/01/25 S21_NA#19808 Mod
                    // 2018/06/29 S21_NA#27071 Add Start
                    clearBizMsgCsmpNum(bizMsg, bizMsg.C.no(cellIdx).dsOrdPosnNum_RC.getValue());
                    // 2018/06/29 S21_NA#27071 Add End
                    return;
                }
            }
        }
        // 2016/08/31 S21_NA#10535 Add End
        Map<String, String> csmpContrInfo = NWAL1500CommonLogicForAddlHeader.getCsmpContrInfo(bizMsg, true, true);
        if (csmpContrInfo == null) {
            return;
        }

        // 2016/08/26 S21_NA#9806 Mod Start
//        ZYPEZDItemValueSetter.setValue(bizMsg.csmpContrNum, csmpContrInfo.get("CSMP_NUM"));
//        ZYPEZDItemValueSetter.setValue(bizMsg.dlrRefNum, csmpContrInfo.get("DLR_REF_NUM"));

//        int cellIdx = bizMsg.xxCellIdx.getValueInt(); 2016/08/31 S21_NA#10535 Del
        String csmpContrNum = csmpContrInfo.get("CSMP_NUM");
        String dlrRefNum = csmpContrInfo.get("DLR_REF_NUM");
        if (cellIdx < 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.csmpContrNum, csmpContrNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.dlrRefNum, dlrRefNum);
            // QC#22371 2017/12/26 Add Start
            // 2018/01/25 S21_NA#19808 Mod bizMsg.A, C => glblMsg.A, C
            for(int i = 0; i< glblMsg.A.getValidCount(); i++){
                NWAL1500_ASMsg configLineMsg = glblMsg.A.no(i);
                ZYPEZDItemValueSetter.setValue(configLineMsg.csmpContrNum_LC, csmpContrNum);
                ZYPEZDItemValueSetter.setValue(configLineMsg.dlrRefNum_LC, dlrRefNum);
                setCsmpPriceListForConfig(bizMsg, configLineMsg);
                setDefaultLineCsmpData(bizMsg, glblMsg, configLineMsg); // 2018/01/25 S21_NA#19808 Mod
            }
            for(int i = 0; i< glblMsg.C.getValidCount(); i++){
                NWAL1500_CSMsg configLineMsg = glblMsg.C.no(i);
                ZYPEZDItemValueSetter.setValue(configLineMsg.csmpContrNum_RC, csmpContrNum);
                ZYPEZDItemValueSetter.setValue(configLineMsg.dlrRefNum_RC, dlrRefNum);
                setCsmpPriceListForConfig(bizMsg, configLineMsg);
                setDefaultLineCsmpData(bizMsg, glblMsg, configLineMsg); // 2018/01/25 S21_NA#19808 Mod
            }
            // QC#22371 2017/12/26 Add End
        } else {
            // String dplyTab = bizMsg.xxDplyTab.getValue(); 2016/08/31 S21_NA#10535 Del
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cellIdx).csmpContrNum_LC, csmpContrNum);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cellIdx).dlrRefNum_LC, dlrRefNum);

                setCsmpPriceListForConfig(bizMsg, bizMsg.A.no(cellIdx));
                // 2018/01/25 S21_NA#19808 Add Start
                NWAL1500_ASMsg glblConfigMsg = NWAL1500CommonLogicForPagination.getGlobalConfigMsgFromBizMsg(glblMsg, bizMsg.A.no(cellIdx));
                ZYPEZDItemValueSetter.setValue(glblConfigMsg.csmpContrNum_LC, csmpContrNum);
                ZYPEZDItemValueSetter.setValue(glblConfigMsg.dlrRefNum_LC, dlrRefNum);
                // 2018/01/25 S21_NA#19808 Add End
                setDefaultLineCsmpData(bizMsg, glblMsg, glblConfigMsg);  // 2018/01/25 S21_NA#19808 Mod
            } else if (TAB_RMA.equals(dplyTab)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cellIdx).csmpContrNum_RC, csmpContrNum);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cellIdx).dlrRefNum_RC, dlrRefNum);

                setCsmpPriceListForConfig(bizMsg, bizMsg.C.no(cellIdx));
                // 2018/01/25 S21_NA#19808 Add Start
                NWAL1500_CSMsg glblRmaConfigMsg = NWAL1500CommonLogicForPagination.getGlobalConfigMsgFromBizMsg(glblMsg, bizMsg.C.no(cellIdx));
                ZYPEZDItemValueSetter.setValue(glblRmaConfigMsg.csmpContrNum_RC, csmpContrNum);
                ZYPEZDItemValueSetter.setValue(glblRmaConfigMsg.dlrRefNum_RC, dlrRefNum);
                // 2018/01/25 S21_NA#19808 Add End
                setDefaultLineCsmpData(bizMsg, glblMsg, glblRmaConfigMsg); // 2018/01/25 S21_NA#19808 Mod
            }
        }
        // 2016/08/26 S21_NA#9806 Add End
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromDealerRefNumber(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod

        int cellIdx = bizMsg.xxCellIdx.getValueInt(); // 2016/08/31 S21_NA#10535 Add
        String dplyTab = bizMsg.xxDplyTab.getValue(); // 2016/08/31 S21_NA#10535 Add
        // 2016/08/31 S21_NA#10535 Add Start
        if (cellIdx >= 0) {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                if (!ZYPCommonFunc.hasValue(bizMsg.A.no(cellIdx).dlrRefNum_LC)) {
                    clearCsmpData(glblMsg, bizMsg.A.no(cellIdx)); // 2018/01/25 S21_NA#19808 Mod
                    return;
                }
            } else if (TAB_RMA.equals(dplyTab)) {
                if (!ZYPCommonFunc.hasValue(bizMsg.C.no(cellIdx).dlrRefNum_RC)) {
                    clearCsmpData(glblMsg, bizMsg.C.no(cellIdx)); // 2018/01/25 S21_NA#19808
                    return;
                }
            }
        }
        // 2016/08/31 S21_NA#10535 Add End
        Map<String, String> csmpContrInfo = NWAL1500CommonLogicForAddlHeader.getCsmpContrInfo(bizMsg, true, false);
        if (csmpContrInfo == null) {
            return;
        }

        // 2016/08/26 S21_NA#9806 Mod Start
//        ZYPEZDItemValueSetter.setValue(bizMsg.csmpContrNum, csmpContrInfo.get("CSMP_NUM"));
//        ZYPEZDItemValueSetter.setValue(bizMsg.dlrRefNum, csmpContrInfo.get("DLR_REF_NUM"));

        // int cellIdx = bizMsg.xxCellIdx.getValueInt(); 2016/08/31 S21_NA#10535 Del
        String csmpContrNum = csmpContrInfo.get("CSMP_NUM");
        String dlrRefNum = csmpContrInfo.get("DLR_REF_NUM");
        if (cellIdx < 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.csmpContrNum, csmpContrNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.dlrRefNum, dlrRefNum);
            // QC#22371 2017/12/26 Add Start
            // 2018/01/25 S21_NA#19808 Mod bizMsg.A, C => glblMsg.A, C
            for(int i = 0; i< glblMsg.A.getValidCount(); i++){
                NWAL1500_ASMsg configLineMsg = glblMsg.A.no(i);
                ZYPEZDItemValueSetter.setValue(configLineMsg.csmpContrNum_LC, csmpContrNum);
                ZYPEZDItemValueSetter.setValue(configLineMsg.dlrRefNum_LC, dlrRefNum);
                setCsmpPriceListForConfig(bizMsg, configLineMsg);
                setDefaultLineCsmpData(bizMsg, glblMsg, configLineMsg); // 2018/01/25 S21_NA#19808 Mod
            }
            for(int i = 0; i< glblMsg.C.getValidCount(); i++){
                NWAL1500_CSMsg configLineMsg = glblMsg.C.no(i);
                ZYPEZDItemValueSetter.setValue(configLineMsg.csmpContrNum_RC, csmpContrNum);
                ZYPEZDItemValueSetter.setValue(configLineMsg.dlrRefNum_RC, dlrRefNum);
                setCsmpPriceListForConfig(bizMsg, configLineMsg);
                setDefaultLineCsmpData(bizMsg, glblMsg, configLineMsg); // 2018/01/25 S21_NA#19808 Mod
            }
            // QC#22371 2017/12/26 Add End
        } else {
            // String dplyTab = bizMsg.xxDplyTab.getValue(); 2016/08/31 S21_NA#10535 Del
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cellIdx).csmpContrNum_LC, csmpContrNum);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cellIdx).dlrRefNum_LC, dlrRefNum);

                setCsmpPriceListForConfig(bizMsg, bizMsg.A.no(cellIdx));
                // 2018/01/25 S21_NA#19808 Add Start
                NWAL1500_ASMsg glblConfigMsg = NWAL1500CommonLogicForPagination.getGlobalConfigMsgFromBizMsg(glblMsg, bizMsg.A.no(cellIdx));
                ZYPEZDItemValueSetter.setValue(glblConfigMsg.csmpContrNum_LC, csmpContrNum);
                ZYPEZDItemValueSetter.setValue(glblConfigMsg.dlrRefNum_LC, dlrRefNum);
                // 2018/01/25 S21_NA#19808 Add End
                setDefaultLineCsmpData(bizMsg, glblMsg, glblConfigMsg); // 2018/01/25 S21_NA#19808 Mod
            } else if (TAB_RMA.equals(dplyTab)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cellIdx).csmpContrNum_RC, csmpContrNum);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cellIdx).dlrRefNum_RC, dlrRefNum);

                setCsmpPriceListForConfig(bizMsg, bizMsg.C.no(cellIdx));
                // 2018/01/25 S21_NA#19808 Add Start
                NWAL1500_CSMsg glblRmaConfigMsg = NWAL1500CommonLogicForPagination.getGlobalConfigMsgFromBizMsg(glblMsg, bizMsg.C.no(cellIdx));
                ZYPEZDItemValueSetter.setValue(glblRmaConfigMsg.csmpContrNum_RC, csmpContrNum);
                ZYPEZDItemValueSetter.setValue(glblRmaConfigMsg.dlrRefNum_RC, dlrRefNum);
                // 2018/01/25 S21_NA#19808 Add End
                setDefaultLineCsmpData(bizMsg, glblMsg, glblRmaConfigMsg); // 2018/01/25 S21_NA#19808 Mod
            }
        }
        // 2016/08/26 S21_NA#9806 Mod End
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromAssnProgramName(NWAL1500CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForCategory.getInstance().getDsOrdTpList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdTpDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_RSN });
            return;
        }

        Map<String, String> prcContrInfo = NWAL1500CommonLogicForAddlHeader.getPrcContrInfo(bizMsg, true);
        if (prcContrInfo == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNm, prcContrInfo.get("PRC_CONTR_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNum, prcContrInfo.get("PRC_CONTR_NUM"));
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromAssnProgramNumber(NWAL1500CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForCategory.getInstance().getDsOrdTpList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdTpDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_RSN });
            return;
        }

        Map<String, String> prcContrInfo = NWAL1500CommonLogicForAddlHeader.getPrcContrInfo(bizMsg, false);
        if (prcContrInfo == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNm, prcContrInfo.get("PRC_CONTR_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNum, prcContrInfo.get("PRC_CONTR_NUM"));
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromPriceList(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2018/01/25 S21_NA#19808 Mod
        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctLineIndex = bizMsg.xxCellIdx.getValueInt();
        // 2018/01/25 S21_NA#19808 Add Start
        NWAL1500_BCMsg bizLineMsg = null;
        NWAL1500_DCMsg bizRmaLineMsg = null;
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            bizLineMsg = bizMsg.B.no(slctLineIndex);
            slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizLineMsg, glblMsg);
            EZDMsg.copy(bizLineMsg, null, glblMsg.B.no(slctLineIndex), null);
        } else if (TAB_RMA.equals(dplyTab)) {
            bizRmaLineMsg = bizMsg.D.no(slctLineIndex);
            slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizRmaLineMsg, glblMsg);
            EZDMsg.copy(bizRmaLineMsg, null, glblMsg.D.no(slctLineIndex), null);
        }
        // 2018/01/25 S21_NA#19808 Add End

        List<String> prcCatgCdList = NWAL1500CommonLogic.getPrcCatgCdList(bizMsg, true);
        if (prcCatgCdList == null) {
            return;
        }

        // Call NWZC1570 Pricing API
        NWZC157001PMsg prcApiPMsg = NWAL1500CommonLogic.callPricingApiOfGetPriceListMode(bizMsg, PRC_CTX_TP.SALES_PRICING, true);
        if (prcApiPMsg == null) {
            return;
        }

        String prcCatgNm = NWAL1500CommonLogic.checkContainPrcList(bizMsg, prcCatgCdList, prcApiPMsg, true);
        if (!ZYPCommonFunc.hasValue(prcCatgNm)) {
            return;
        }

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(slctLineIndex).prcCatgNm_LL, prcCatgNm);
            int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(dplyTab, glblMsg, slctLineIndex); // 2018/01/25 S21_NA#19808 Mod
            NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
            NWAL1500CommonLogicForPagination.copyGlblMsgToBizDtlMsg(glblMsg.B.no(slctLineIndex), bizMsg); // 2018/01/25 S21_NA#19808 Add
        } else if (TAB_RMA.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(slctLineIndex).prcCatgNm_RL, prcCatgNm);
            int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(dplyTab, glblMsg, slctLineIndex); // 2018/01/25 S21_NA#19808 Mod
            NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
            NWAL1500CommonLogicForPagination.copyGlblMsgToBizDtlMsg(glblMsg.D.no(slctLineIndex), bizMsg); // 2018/01/25 S21_NA#19808 Add
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm, prcCatgNm);
            NWAL1500CommonLogic.deriveAllLinePrice(bizMsg, glblMsg, prcCatgNm);
        }
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromFloorPriceList(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctLineIndex = bizMsg.xxCellIdx.getValueInt();
        // 2018/01/25 S21_NA#19808 Add Start
        NWAL1500_BCMsg bizLineMsg = null;
        NWAL1500_DCMsg bizRmaLineMsg = null;
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            bizLineMsg = bizMsg.B.no(slctLineIndex);
            slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizLineMsg, glblMsg);
            EZDMsg.copy(bizLineMsg, null, glblMsg.B.no(slctLineIndex), null);
        } else if (TAB_RMA.equals(dplyTab)) {
            bizRmaLineMsg = bizMsg.D.no(slctLineIndex);
            slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizRmaLineMsg, glblMsg);
            EZDMsg.copy(bizRmaLineMsg, null, glblMsg.D.no(slctLineIndex), null);
        }
        // 2018/01/25 S21_NA#19808 Add End

        List<String> flPrcCatgCdList = NWAL1500CommonLogic.getPrcCatgCdList(bizMsg, false);
        if (flPrcCatgCdList == null) {
            return;
        }

        // Call NWZC1570 Pricing API
        NWZC157001PMsg prcApiPMsg = NWAL1500CommonLogic.callPricingApiOfGetPriceListMode(bizMsg, PRC_CTX_TP.FLOOR_PRICE, true);
        if (prcApiPMsg == null) {
            return;
        }

        String prcCatgNm = NWAL1500CommonLogic.checkContainPrcList(bizMsg, flPrcCatgCdList, prcApiPMsg, false);
        if (!ZYPCommonFunc.hasValue(prcCatgNm)) {
            return;
        }

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(slctLineIndex).flPrcListNm_LL, prcCatgNm);
            // QC#22372 2018/01/10 Add Start
            int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(dplyTab, glblMsg, slctLineIndex); // 2018/01/25 S21_NA#19808 Mod
            NWAL1500CommonLogicForLineConfig.deriveLineFloorPrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
            // QC#22372 2018/01/10 Add End
            NWAL1500CommonLogicForPagination.copyGlblMsgToBizDtlMsg(glblMsg.B.no(slctLineIndex), bizMsg); // 2018/01/25 S21_NA#19808 Add
        } else if (TAB_RMA.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(slctLineIndex).flPrcListNm_RL, prcCatgNm);
            // QC#22372 2018/01/10 Add Start
            int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(dplyTab, glblMsg, slctLineIndex); // 2018/01/25 S21_NA#19808 Mod
            NWAL1500CommonLogicForLineConfig.deriveLineFloorPrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
            // QC#22372 2018/01/10 Add End
            NWAL1500CommonLogicForPagination.copyGlblMsgToBizDtlMsg(glblMsg.D.no(slctLineIndex), bizMsg); // 2018/01/25 S21_NA#19808 Add
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.flPrcListNm, prcCatgNm);
            NWAL1500CommonLogic.deriveAllLineFloorPrice(bizMsg, glblMsg, prcCatgNm);
        }
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSubstituteItem(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2019/11/21 S21_NA#54202 Add Start
        String screenAplID = bizMsg.getScreenAplID();
        boolean isOnBlurSubstItem = "NWAL1500Scrn00_OnBlur_DeriveFromSubstituteItem".equals(screenAplID);
        // 2019/11/21 S21_NA#54202 Add End
        NWAL1500_BCMsg lineMsg = bizMsg.B.no(bizMsg.xxCellIdx.getValueInt());

        // 2019/11/21 S21_NA#54202 Mod Start
//        String sbstMdseCd = NWAL1500CommonLogicForLineConfig.getSbstMdseCd(bizMsg, lineMsg);
//        // 2016/07/28 S21_NA#1698,3235 Mode Start
//        if (!ZYPCommonFunc.hasValue(sbstMdseCd)) {
//            lineMsg.sbstMdseCd_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_SBST_ITEM });
//            return;
//        }
//
////        ZYPEZDItemValueSetter.setValue(lineMsg.sbstMdseCd_LL, sbstMdseCd);
//        // 2016/07/28 S21_NA#1698,3235 Mode End
        String sbstMdseCd = null;
        if (isOnBlurSubstItem && ZYPCommonFunc.hasValue(lineMsg.sbstMdseCd_LL)) {
            sbstMdseCd = NWAL1500CommonLogicForLineConfig.getSbstMdseCd(bizMsg, lineMsg);
            if (!ZYPCommonFunc.hasValue(sbstMdseCd)) {
                lineMsg.sbstMdseCd_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_SBST_ITEM });
                return;
            }
        }
        // 2019/11/21 S21_NA#54202 Mod Start

        // 2016/11/30 S21_NA#16098 Add Start
        // 2018/01/25 S21_NA#19808 Del
//        int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg, bizMsg.xxCellIdx.getValueInt());
        // 2018/01/25 S21_NA#19808 Del End

        // 2018/01/25 S21_NA#19808 Add Start
        String dplyTab = bizMsg.xxDplyTab.getValue();

        int slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(lineMsg, glblMsg);
        EZDMsg.copy(lineMsg, null, glblMsg.B.no(slctLineIndex), null);
        int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(dplyTab, glblMsg, slctLineIndex); // 2018/01/25 S21_NA#19808 Mod
        // 2018/01/25 S21_NA#19808 Add End

        // 2019/11/21 S21_NA#54202 Mod Start
//        if (NWAL1500CommonLogicForLineConfig.isChangeMdlAndShellReln(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex))){
//            lineMsg.sbstMdseCd_LL.setErrorInfo(1, NWAM0910E);
//        }
        NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex));
        NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_LL.getValueInt());
        // 2019/11/21 S21_NA#54202 Mod End
        // 2016/11/30 S21_NA#16098 Add End
    }

    // 2018/01/04 S21_NA#21503 ADD START
    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromConfigId(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        int slctIdx = bizMsg.xxCellIdx.getValueInt();
        NWAL1500_ACMsg configMsg = bizMsg.A.no(slctIdx);
        // QC#24245 2018/06/13 Del Start
        // configMsg.xxYesNoCd_LC.setValue(ZYPConstant.FLG_ON_Y);
        // QC#24245 2018/06/13 Del End
        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configMsg.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, true, false)) {
            String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
            int slctConfIndex = NWAL1500CommonLogicForConfigId.getConfigIndex(glblMsg.A, dsOrdPosnNum); // 2018/01/25 S21_NA#19808
            EZDMsg.copy(configMsg, null, glblMsg.A.no(slctConfIndex), null);
            NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex)); // 2018/01/25 S21_NA#19808
            NWAL1500CommonLogicForPagination.copyGlblMsgToBizDtlMsg(glblMsg.A.no(slctConfIndex), bizMsg); // 2018/01/25 S21_NA#19808 Add
        }

    }
    // 2018/01/04 S21_NA#21503 ADD END

    private void doProcess_NWAL1500Scrn00_OnBlur_ChangeWH(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808

        int slctLineIndex = bizMsg.xxCellIdx.getValueInt();
        // 2018/01/25 S21_NA#19808 Add Start
        String dplyTab = bizMsg.xxDplyTab.getValue();
        NWAL1500_BCMsg bizLineMsg = null;
        NWAL1500_DCMsg bizRmaLineMsg = null;
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            bizLineMsg = bizMsg.B.no(slctLineIndex);
            slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizLineMsg, glblMsg);
            EZDMsg.copy(bizLineMsg, null, glblMsg.B.no(slctLineIndex), null);
        } else {
            bizRmaLineMsg = bizMsg.D.no(slctLineIndex);
            slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizRmaLineMsg, glblMsg);
            EZDMsg.copy(bizRmaLineMsg, null, glblMsg.D.no(slctLineIndex), null);
        }
        // 2018/01/25 S21_NA#19808 Add End
        int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(dplyTab, glblMsg, slctLineIndex); // 2018/01/25 S21_NA#19808 Mod

        // 2018/01/25 bizMsg.B, D => glblMsg.B, D
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            // Copy Current Page Data to Global Message
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            // 2018/07/12 S21_NA#26895-2 Add End
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineIndex);

            Map<String, String> whInfo = NWAL1500CommonLogicForLineConfig.getWhInfo(bizMsg, lineMsg.rtlWhNm_LL);
            if (whInfo == null) {
                lineMsg.rtlWhCd_LL.clear();
                EZDMsg.copy(lineMsg, null, bizLineMsg, null);
                return;
            }

            ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhCd_LL, whInfo.get("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhNm_LL, whInfo.get("RTL_WH_NM"));

            // Set Warehouse For Component
            String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
            BigDecimal dsCpoLineNum = lineMsg.dsCpoLineNum_LL.getValue();
            // NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, lineMsg); // 2016/03/15 S21_NA#4691 Add -> 2016/10/05 S21_NA#7645-3 Del
            for (int i = slctLineIndex + 1; i < glblMsg.B.getValidCount(); i++) {
                if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(i).dsOrdPosnNum_LL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, glblMsg.B.no(i).dsCpoLineNum_LL.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).rtlWhCd_LL, lineMsg.rtlWhCd_LL);
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).rtlWhNm_LL, lineMsg.rtlWhNm_LL);
                    // NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, glblMsg.B.no(i)); // 2016/03/15 S21_NA#4691 Add -> 2016/10/05 S21_NA#7645-3 Del
                } else {
                    break;
                }
            }
            // 2018/09/20 S21_NA#28199 Add Start
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.baseCmptFlg_LL.getValue())) {
                NWAL1500CommonLogic.setRtlWhCdAsBaseComponent(bizMsg, glblMsg, dsOrdPosnNum, CONFIG_CATG.OUTBOUND);
            }
            // 2018/09/20 S21_NA#28199 Add End
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Add
        } else {
            // 2018/07/12 S21_NA#26895-2 Add Start
            // Copy Current Page Data to Global Message
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            // 2018/07/12 S21_NA#26895-2 Add End
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(slctLineIndex);
            Map<String, String> whInfo = NWAL1500CommonLogicForLineConfig.getWhInfo(bizMsg, rmaLineMsg.rtlWhNm_RL);
            if (whInfo == null) {
                rmaLineMsg.rtlWhCd_RL.clear();
                EZDMsg.copy(rmaLineMsg, null, bizRmaLineMsg, null);
                return;
            }

            String whCd = whInfo.get("RTL_WH_CD");
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlWhCd_RL, whCd);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlWhNm_RL, whInfo.get("RTL_WH_NM"));

            // 2020/03/16 QC#56132 Add Start
            if (RTL_WH_CATG_VIRTUAL_CD.equals(whInfo.get("RTL_WH_CATG_CD"))) {
                rmaLineMsg.rtlWhCd_RL.clear();
                rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, NWAM0626E, new String[] {});
                EZDMsg.copy(rmaLineMsg, null, bizRmaLineMsg, null);
                return;
            }
            // 2020/03/16 QC#56132 Add End

            // call NLZC2150 Get Default Sub Warehouse API
            // Mod Start 2017/01/31 QC#17186
//            NLZC215001PMsg defSubWhApiPMsg = NWAL1500CommonLogicForLineConfig.callDefSubWhApi(bizMsg, rmaLineMsg.mdseCd_RL.getValue(), whCd);
            // START 2022/09/09 R.Azucena [QC#60548 MOD]
            // NLZC215001PMsg defSubWhApiPMsg = NWAL1500CommonLogicForLineConfig.callDefSubWhApi(bizMsg, rmaLineMsg.mdseCd_RL.getValue(), whCd, rmaLineMsg.svcMachMstrPk_RL.getValue());
            String mainItemMdseCd = rmaLineMsg.mdseCd_RL.getValue();
            MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), mainItemMdseCd);
            List<String> catgCdList = Arrays.asList(MDSE_CATG.SUPPLY, MDSE_CATG.PARTS);

            if (catgCdList.contains(mdseTMsg.mdseCatgCd.getValue())
                    && ZYPConstant.FLG_OFF_N.equals(rmaLineMsg.baseCmptFlg_RL.getValue())
                    && ZYPCommonFunc.hasValue(rmaLineMsg.svcMachMstrPk_RL)
                    && ZYPCommonFunc.hasValue(rmaLineMsg.dplyLineRefNum_RL)) {
                for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                    if (glblMsg.D.no(i).xxLineNum_RL.getValue().equals(rmaLineMsg.dplyLineRefNum_RL.getValue())) {
                        mainItemMdseCd = glblMsg.D.no(i).mdseCd_RL.getValue();
                        break;
                    }
                }
            }

            NLZC215001PMsg defSubWhApiPMsg = NWAL1500CommonLogicForLineConfig.callDefSubWhApi(bizMsg, mainItemMdseCd, whCd, rmaLineMsg.svcMachMstrPk_RL.getValue());
            // END 2022/09/09 R.Azucena [QC#60548 MOD]
            // Mod End 2017/01/31 QC#17186
            if (defSubWhApiPMsg == null) {
                rmaLineMsg.rtlSwhCd_RL.clear();
                EZDMsg.copy(rmaLineMsg, null, bizRmaLineMsg, null);
                return;
            }
            String rtlSwhCd = defSubWhApiPMsg.destRtlSwhCd.getValue();
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhCd_RL, rtlSwhCd);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhNm_RL, NWAL1500CommonLogicForLineConfig.getSubWhNm(bizMsg, whCd, rtlSwhCd));
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.thirdPtyDspTpCd_RL, defSubWhApiPMsg.thirdPtyDspTpCd);
            String dsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
            BigDecimal dsCpoLineNum = rmaLineMsg.dsCpoLineNum_RL.getValue();
            for (int i = slctLineIndex + 1; i < glblMsg.D.getValidCount(); i++) {
                if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, glblMsg.D.no(i).dsCpoLineNum_RL.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).rtlWhCd_RL, rmaLineMsg.rtlWhCd_RL);
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).rtlWhNm_RL, rmaLineMsg.rtlWhNm_RL);
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).rtlSwhCd_RL, rmaLineMsg.rtlSwhCd_RL);
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).rtlSwhNm_RL, rmaLineMsg.rtlSwhNm_RL);
                } else {
                    break;
                }
            }

            // Add Start 2017/02/03 QC#17257
            List<String> posnNums = new ArrayList<String>();
            posnNums.add(dsOrdPosnNum);
            // QC#56558 2020/04/14 Mod Start
            // NWAL1500CommonLogicForLineConfig.updateBaseCmptFlg(bizMsg, glblMsg, posnNums, false); // 2018/01/25 S21_NA#19808 Mod
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rmaLineMsg.baseCmptFlg_RL.getValue())) {
                NWAL1500CommonLogicForLineConfig.updateBaseCmptFlg(bizMsg, glblMsg, posnNums, false); // 2018/01/25 S21_NA#19808 Mod
            }
            // QC#56558 2020/04/14 Mod End
            // Add End 2017/02/03 QC#17257
            // QC#22371 2017/12/26 Add Start
//            int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg, slctLineIndex); // 2018/01/25 S21_NA#19808 Del
            setDefaultLineCsmpData(bizMsg, glblMsg, glblMsg.C.no(slctConfIndex)); // 2018/01/25 S21_NA#19808 Mod
            // QC#22371 2017/12/26 Add End
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Add
        }
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_ChangeSubWH(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod

        int slctLineIndex = bizMsg.xxCellIdx.getValueInt();
        // 2017/11/17 S21_NA#22252 Mod Start
        //NWAL1500_BCMsg lineMsg = bizMsg.B.no(slctLineIndex);

        //S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getWhInfoWithRsn(bizMsg, lineMsg.rtlWhNm_LL.getValue());
        //if (ssmResult.isCodeNotFound()) {
        //    lineMsg.rtlWhNm_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_WH });
        //    return;
        //}

        //Map<String, String> rtlSubWhInfo = NWAL1500CommonLogicForLineConfig.getRtlSubWhInfo(bizMsg, lineMsg);
        //if (rtlSubWhInfo == null) {
        //    return;
        //}

        //ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhCd_LL, rtlSubWhInfo.get("RTL_SWH_CD"));
        //ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhNm_LL, rtlSubWhInfo.get("RTL_SWH_NM"));

        //// Set Warehouse For Component
        //String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
        //BigDecimal dsCpoLineNum = lineMsg.dsCpoLineNum_LL.getValue();
        //// NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, lineMsg); // 2016/03/15 S21_NA#4691 Add -> 2016/10/05 S21_NA#7645-3 Del
        //for (int i = slctLineIndex + 1; i < bizMsg.B.getValidCount(); i++) {
        //    if (S21StringUtil.isEquals(dsOrdPosnNum, bizMsg.B.no(i).dsOrdPosnNum_LL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, bizMsg.B.no(i).dsCpoLineNum_LL.getValue()) == 0) {
        //        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).rtlSwhCd_LL, lineMsg.rtlSwhCd_LL);
        //        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).rtlSwhNm_LL, lineMsg.rtlSwhNm_LL);
        //        // NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, bizMsg.B.no(i)); // 2016/03/15 S21_NA#4691 Add -> 2016/10/05 S21_NA#7645-3 Del
        //    } else {
        //        break;
        //    }
        //}
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500_BCMsg lineMsg = bizMsg.B.no(slctLineIndex);

            S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getWhInfoWithRsn(bizMsg, lineMsg.rtlWhNm_LL.getValue());
            if (ssmResult.isCodeNotFound()) {
                lineMsg.rtlWhNm_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_WH });
                return;
            }

            Map<String, String> rtlSubWhInfo = NWAL1500CommonLogicForLineConfig.getRtlSubWhInfo(bizMsg, lineMsg);
            if (rtlSubWhInfo == null) {
                return;
            }

            ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhCd_LL, rtlSubWhInfo.get("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhNm_LL, rtlSubWhInfo.get("RTL_SWH_NM"));

            // Set Warehouse For Component
            String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
            BigDecimal dsCpoLineNum = lineMsg.dsCpoLineNum_LL.getValue();
            // 2018/01/25 S21_NA#19808 Add Start
            slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(lineMsg, glblMsg);
            EZDMsg.copy(lineMsg, null, glblMsg.B.no(slctLineIndex), null);
            // 2018/01/25 S21_NA#19808 Add End
            // NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, lineMsg); // 2016/03/15 S21_NA#4691 Add -> 2016/10/05 S21_NA#7645-3 Del
            // 2018/01/25 S21_NA#19808 Mod bizMsg.B => glblMsg.B
            for (int i = slctLineIndex + 1; i < glblMsg.B.getValidCount(); i++) {
                if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(i).dsOrdPosnNum_LL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, glblMsg.B.no(i).dsCpoLineNum_LL.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).rtlSwhCd_LL, lineMsg.rtlSwhCd_LL);
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).rtlSwhNm_LL, lineMsg.rtlSwhNm_LL);
                    // NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, glblMsg.B.no(i)); // 2016/03/15 S21_NA#4691 Add -> 2016/10/05 S21_NA#7645-3 Del
                } else {
                    break;
                }
            }
            // QC#22371 2017/12/26 Add Start
            int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, slctLineIndex); // 2018/01/25 S21_NA#19808 Mod
            setDefaultLineCsmpData(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex)); // 2018/01/25 S21_NA#19808 Mod
            // QC#22371 2017/12/26 Add End
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Add
        } else {
            NWAL1500_DCMsg rmaLineMsg = bizMsg.D.no(slctLineIndex);

            S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getWhInfoWithRsn(bizMsg, rmaLineMsg.rtlWhNm_RL.getValue());
            if (ssmResult.isCodeNotFound()) {
                rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_WH });
                return;
            }

            Map<String, String> rtlSubWhInfo = NWAL1500CommonLogicForLineConfig.getRtlSubWhInfo(bizMsg, rmaLineMsg);
            if (rtlSubWhInfo == null) {
                return;
            }

            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhCd_RL, rtlSubWhInfo.get("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhNm_RL, rtlSubWhInfo.get("RTL_SWH_NM"));

            // Set Warehouse For Component
            String dsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
            BigDecimal dsCpoLineNum = rmaLineMsg.dsCpoLineNum_RL.getValue();
            // 2018/01/25 S21_NA#19808 Add Start
            slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(rmaLineMsg, glblMsg);
            EZDMsg.copy(rmaLineMsg, null, glblMsg.D.no(slctLineIndex), null);
            // 2018/01/25 S21_NA#19808 Add End
            // NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, lineMsg); // 2016/03/15 S21_NA#4691 Add -> 2016/10/05 S21_NA#7645-3 Del
            // 2018/01/25 S21_NA#19808 Mod bizMsg.D => glblMsg.D
            for (int i = slctLineIndex + 1; i < glblMsg.D.getValidCount(); i++) {
                if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, glblMsg.D.no(i).dsCpoLineNum_RL.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).rtlSwhCd_RL, rmaLineMsg.rtlSwhCd_RL);
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).rtlSwhNm_RL, rmaLineMsg.rtlSwhNm_RL);
                    // NWAL1500CommonLogicForLineConfig.calcBoQty(bizMsg, bizMsg.B.no(i)); // 2016/03/15 S21_NA#4691 Add -> 2016/10/05 S21_NA#7645-3 Del
                } else {
                    break;
                }
            }
            // QC#22371 2017/12/26 Add Start
            int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, slctLineIndex); // 2018/01/25 S21_NA#19808 Mod
            setDefaultLineCsmpData(bizMsg, glblMsg, glblMsg.C.no(slctConfIndex)); // 2018/01/25 S21_NA#19808 Mod
            // QC#22371 2017/12/26 Add End
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Add
        }
        // 2017/11/17 S21_NA#22252 Mod End
    }

    private void doProcess_NWAL1500Scrn00_OnBlur_OrdLogTpNm(NWAL1500CMsg bizMsg) {
        NWAL1500CommonLogic.checkOrdLogTp(bizMsg);
    }

    private void doProcess_NWAL1500Scrn00_OnChange_LineCategory(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int idx = bizMsg.xxCellIdx_BB.getValueInt();

        // S21_NA#8317 Add Start
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
        // S21_NA#8317 Add End

            NWAL1500_BCMsg bcMsg = bizMsg.B.no(idx);
            // 2016/08/26 S21_NA#9806 Add Start
            int glblIdx = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bcMsg, glblMsg);
            NWAL1500_BSMsg glblLineMsg = glblMsg.B.no(glblIdx);
            // 2016/08/26 S21_NA#9806 Add End

            // START 2022/08/01 F.Fadriquela [QC#60273, ADD]
            int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndexInBizMsg(bizMsg, idx);

            slctConfIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizMsg.A.no(slctConfIndex), glblMsg);
            NWAL1500_ASMsg confMsg = glblMsg.A.no(slctConfIndex);

            if (CONFIG_TP.TO_SALES_CONVERSION.equals(confMsg.configTpCd_LC.getValue()) && ZYPCommonFunc.hasValue(confMsg.svcConfigMstrPk_LC)) {
                String oldDsOrdLineCatg = glblLineMsg.dsOrdLineCatgCd_DB.getValue();
                String cpoSrcTp = ZYPCodeDataUtil.getName(CPO_SRC_TP.class, getGlobalCompanyCode(), CPO_SRC_TP.LOAN_WORKBENCH);
                if (ZYPCommonFunc.hasValue(oldDsOrdLineCatg) && !oldDsOrdLineCatg.equals(bcMsg.dsOrdLineCatgCd_LL.getValue()) && cpoSrcTp.equals(glblLineMsg.cpoSrcTpDescTxt_LL.getValue())) {
                    glblLineMsg.dsOrdLineCatgCd_LL.setErrorInfo(1, NWAM0986E);
                    EZDMsg.copy(glblLineMsg, null, bcMsg, null);
                    return;
                }
            }
            // END 2022/08/01 F.Fadriquela [QC#60273, ADD]

            if (DS_ORD_LINE_CATG.LEASE_BUYOUT.equals(bcMsg.dsOrdLineCatgCd_LL.getValue())) {
                bizMsg.dsOrdLineCatgCd_XX.setValue(DS_ORD_LINE_CATG.LEASE_BUYOUT);

                String leaseByotMdseCd = ZYPCodeDataUtil.getVarCharConstValue(LEASE_BYOT_MDSE_CD, bizMsg.glblCmpyCd.getValue());
                if (ZYPCommonFunc.hasValue(leaseByotMdseCd)) {
                    bizMsg.xxCellIdx.setValue(idx);
                    bcMsg.mdseCd_LL.setValue(leaseByotMdseCd);
                    bcMsg.rtlWhNm_LL.clear();
                    bcMsg.rtlSwhNm_LL.clear();
                    bcMsg.baseCmptFlg_LL.clear();
                    // 2018/01/25 S21_NA#19808 Add Start
                    glblLineMsg.mdseCd_LL.setValue(leaseByotMdseCd);
                    glblLineMsg.rtlWhNm_LL.clear();
                    glblLineMsg.rtlSwhNm_LL.clear();
                    glblLineMsg.baseCmptFlg_LL.clear();
                    // 2018/01/25 S21_NA#19808 Add End
                    doProcess_NWAL1500Scrn00_OnBlur_DeriveFromItem(bizMsg, glblMsg);
                }
            } else {
                bcMsg.splyTpCd_LL.clear();
                bcMsg.splyNm_LL.clear();
                // 2019/10/04 S21_NA#51372 Add Start
                bcMsg.prntVndNm_LL.clear();
                // 2019/10/04 S21_NA#51372 Add End
                bcMsg.splyFirstAddr_LL.clear();
                bcMsg.splyCtyAddr_LL.clear();
                bcMsg.splyStCd_LL.clear();
                bcMsg.splyPostCd_LL.clear();
                // 2018/01/25 S21_NA#19808 Add Start
                glblLineMsg.splyTpCd_LL.clear();
                glblLineMsg.splyNm_LL.clear();
                // 2019/10/04 S21_NA#51372 Add Start
                glblLineMsg.prntVndNm_LL.clear();
                // 2019/10/04 S21_NA#51372 Add End
                glblLineMsg.splyFirstAddr_LL.clear();
                glblLineMsg.splyCtyAddr_LL.clear();
                glblLineMsg.splyStCd_LL.clear();
                glblLineMsg.splyPostCd_LL.clear();
                // 2018/01/25 S21_NA#19808 Add End

                boolean leaseBuyOutFlg = false;
                // 2018/01/25 S21_NA#19808 bizMsg.B => glblMsg.B
                for (int n = 0; n < glblMsg.B.getValidCount(); n++) {
//                    bcMsg = bizMsg.B.no(n);
                    if (DS_ORD_LINE_CATG.LEASE_BUYOUT.equals(glblMsg.B.no(n).dsOrdLineCatgCd_LL.getValue())) {
                        leaseBuyOutFlg = true;
                        break;
                    }
                }

                if (leaseBuyOutFlg) {
                    bizMsg.dsOrdLineCatgCd_XX.setValue(DS_ORD_LINE_CATG.LEASE_BUYOUT);
                } else {
                    bizMsg.dsOrdLineCatgCd_XX.clear();
                }

                // S21_NA#8317 Add Start
                String dsOrdPosnNum = bizMsg.B.no(idx).dsOrdPosnNum_LL.getValue();
                BigDecimal dsCpoLineNum = bizMsg.B.no(idx).dsCpoLineNum_LL.getValue();
                for (int i = idx + 1; i < bizMsg.B.getValidCount(); i++) {
                    if (S21StringUtil.isEquals(dsOrdPosnNum, bizMsg.B.no(i).dsOrdPosnNum_LL.getValue()) //
                            && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, bizMsg.B.no(i).dsCpoLineNum_LL.getValue()) == 0) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsOrdLineCatgCd_LL, bizMsg.B.no(idx).dsOrdLineCatgCd_LL);
                    } else {
                        break;
                    }
                }
                // 2018/01/25 S21_NA#19808 bizMsg.B => glblMsg.B
                for (int i = glblIdx + 1; i < glblMsg.B.getValidCount(); i++) {
                    if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(i).dsOrdPosnNum_LL.getValue()) //
                            && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, glblMsg.B.no(i).dsCpoLineNum_LL.getValue()) == 0) {
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).dsOrdLineCatgCd_LL, glblMsg.B.no(idx).dsOrdLineCatgCd_LL);
                    } else {
                        break;
                    }
                }
                // S21_NA#8317 Add End

            }

            // START 2016/08/08 S21_NA#11592 Add
            bcMsg = bizMsg.B.no(idx);
            String dummyWhCd = NWAL1500CommonLogicForLineConfig.getForceDummyWhByDsOrdLineCatgCd(
                    bizMsg.glblCmpyCd.getValue(), //
                    bcMsg.dsOrdLineCatgCd_LL.getValue());
            if (ZYPCommonFunc.hasValue(dummyWhCd)) {
                bcMsg.rtlWhCd_LL.clear();
                bcMsg.rtlWhNm_LL.clear();
                bcMsg.rtlSwhCd_LL.clear();
                bcMsg.rtlSwhNm_LL.clear();

                // 2018/01/25 S21_NA#19808 Add Start
                glblLineMsg.rtlWhCd_LL.clear();
                glblLineMsg.rtlWhNm_LL.clear();
                glblLineMsg.rtlSwhCd_LL.clear();
                glblLineMsg.rtlSwhNm_LL.clear();
                // 2018/01/25 S21_NA#19808 Add End

                String dummyWhNm = NWAL1500CommonLogicForLineConfig.getWhNm(bizMsg, dummyWhCd);
                ZYPEZDItemValueSetter.setValue(bcMsg.rtlWhCd_LL, dummyWhCd);
                ZYPEZDItemValueSetter.setValue(bcMsg.rtlWhNm_LL, dummyWhNm);

                // 2018/01/25 S21_NA#19808 Add Start
                ZYPEZDItemValueSetter.setValue(glblLineMsg.rtlWhCd_LL, dummyWhCd);
                ZYPEZDItemValueSetter.setValue(glblLineMsg.rtlWhNm_LL, dummyWhNm);
                // 2018/01/25 S21_NA#19808 Add End

                // SET Component
                String dsOrdPosnNum = bcMsg.dsOrdPosnNum_LL.getValue();
                BigDecimal dsCpoLineNum = bizMsg.B.no(idx).dsCpoLineNum_LL.getValue();

                for (int i = idx + 1; i < bizMsg.B.getValidCount(); i++) {
                    if (S21StringUtil.isEquals(dsOrdPosnNum, bizMsg.B.no(i).dsOrdPosnNum_LL.getValue()) //
                            && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, bizMsg.B.no(i).dsCpoLineNum_LL.getValue()) == 0) {
                        bizMsg.B.no(i).rtlWhCd_LL.clear();
                        bizMsg.B.no(i).rtlWhNm_LL.clear();
                        bizMsg.B.no(i).rtlSwhCd_LL.clear();
                        bizMsg.B.no(i).rtlSwhNm_LL.clear();
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).rtlWhCd_LL, dummyWhCd);
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).rtlWhNm_LL, dummyWhNm);
                    } else {
                        break;
                    }
                }
                // 2018/01/25 S21_NA#19808 Add Start (Global Message)
                for (int i = glblIdx + 1; i < glblMsg.B.getValidCount(); i++) {
                    if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(i).dsOrdPosnNum_LL.getValue()) //
                            && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, glblMsg.B.no(i).dsCpoLineNum_LL.getValue()) == 0) {
                        glblMsg.B.no(i).rtlWhCd_LL.clear();
                        glblMsg.B.no(i).rtlWhNm_LL.clear();
                        glblMsg.B.no(i).rtlSwhCd_LL.clear();
                        glblMsg.B.no(i).rtlSwhNm_LL.clear();
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).rtlWhCd_LL, dummyWhCd);
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).rtlWhNm_LL, dummyWhNm);
                    } else {
                        break;
                    }
                }
                // 2018/01/25 S21_NA#19808 Add End (Global Message)
           // QC#14266 2016/09/26 Mod Start
           //} else {
           //     bizMsg.B.no(idx).rtlWhNm_LL.clear();
           //
           //     // SET Component
           //     String dsOrdPosnNum = bcMsg.dsOrdPosnNum_LL.getValue();
           //     BigDecimal dsCpoLineNum = bizMsg.B.no(idx).dsCpoLineNum_LL.getValue();
           //     for (int i = idx + 1; i < bizMsg.B.getValidCount(); i++) {
           //         if (S21StringUtil.isEquals(dsOrdPosnNum, bizMsg.B.no(i).dsOrdPosnNum_LL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, bizMsg.B.no(i).dsCpoLineNum_LL.getValue()) == 0) {
           //             bizMsg.B.no(i).rtlWhNm_LL.clear();
           //         } else {
           //             break;
           //         }
           //     }
           // QC#14266 2016/09/26 Mod End
            }
            // END   2016/08/08 S21_NA#11592 Add

        // S21_NA#8317 Add Start
        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {

            NWAL1500_DCMsg rmaLineMsg = bizMsg.D.no(idx);
            // 2018/01/25 S21_NA#19808 Add Start
            int glblIdx = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(rmaLineMsg, glblMsg);
            NWAL1500_DSMsg glblRmaLineMsg = glblMsg.D.no(glblIdx);
            EZDMsg.copy(rmaLineMsg, null, glblRmaLineMsg, null);
            // 2018/01/25 S21_NA#19808 Add End

            String dsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
            BigDecimal dsCpoLineNum = rmaLineMsg.dsCpoLineNum_RL.getValue();

            for (int i = idx + 1; i < bizMsg.D.getValidCount(); i++) {
                if (S21StringUtil.isEquals(dsOrdPosnNum, bizMsg.D.no(i).dsOrdPosnNum_RL.getValue()) //
                        && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, bizMsg.D.no(i).dsCpoLineNum_RL.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).dsOrdLineCatgCd_RL, rmaLineMsg.dsOrdLineCatgCd_RL);
                } else {
                    break;
                }
            }
            // 2018/01/25 S21_NA#19808 Add Start (Global Message)
            for (int i = glblIdx + 1; i < glblMsg.D.getValidCount(); i++) {
                if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue()) //
                        && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, glblMsg.D.no(i).dsCpoLineNum_RL.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).dsOrdLineCatgCd_RL, rmaLineMsg.dsOrdLineCatgCd_RL);
                } else {
                    break;
                }
            }
            // 2018/01/25 S21_NA#19808 Add End (Global Message)
        }
        // S21_NA#8317 Add End

    }

    private void doProcess_NWAL1500Scrn00_OnChange_UOM(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/03/06 S21_NA#19808

        int slctLineIndex = bizMsg.xxCellIdx_BB.getValueInt();

        EZDCStringItem inputMdseCd = null;
        EZDCStringItem inputUomCd = null;
        EZDCBigDecimalItem inputUomQty = null;
        EZDCBigDecimalItem inputEachQty = null;

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500_BCMsg lineMsg = bizMsg.B.no(slctLineIndex);
            inputMdseCd = lineMsg.mdseCd_LL;
            inputUomCd = lineMsg.custUomCd_LL;
            inputUomQty = lineMsg.ordCustUomQty_LL;
            inputEachQty = lineMsg.ordQty_LL;
        } else {
            NWAL1500_DCMsg rmaLineMsg = bizMsg.D.no(slctLineIndex);
            inputMdseCd = rmaLineMsg.mdseCd_RL;
            inputUomCd = rmaLineMsg.custUomCd_RL;
            inputUomQty = rmaLineMsg.ordCustUomQty_RL;
            inputEachQty = rmaLineMsg.ordQty_RL;
        }

        if (!ZYPCommonFunc.hasValue(inputMdseCd) || !ZYPCommonFunc.hasValue(inputUomQty)) {
            return;
        }

        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), inputMdseCd.getValue());
        if (null == mdseTMsg) {
            inputMdseCd.setErrorInfo(1, NWAM0036E);
            return;
        }

        BigDecimal eachQty = NWAL1500CommonLogicForLineConfig.getEachQty(bizMsg, mdseTMsg.mdseCd.getValue(), inputUomCd.getValue(), inputUomQty.getValue());
        ZYPEZDItemValueSetter.setValue(inputEachQty, eachQty);

        // 2018/03/06 S21_NA#19808 Add Start
        // Update Global Data
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500CommonLogicForPagination.copyLineDataToSMsg(bizMsg.B.no(slctLineIndex), glblMsg);
        } else {
            NWAL1500CommonLogicForPagination.copyRmaLineDataToSMsg(bizMsg.D.no(slctLineIndex), glblMsg);
        }
        // 2018/03/06 S21_NA#19808 Add End
    }

    private void doProcess_NWAL1500Scrn00_OnChange_ReturnReason(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod

        int slctLineIndex = bizMsg.xxCellIdx.getValueInt();
        int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndexInBizMsg(bizMsg, slctLineIndex);

        // 2018/03/28 S21_NA#25102 Add Start
        NWAL1500_DCMsg rmaLineBizMsg = bizMsg.D.no(slctLineIndex);
        // 2018/03/28 S21_NA#25102 Add End
        // 2018/01/25 S21_NA#19808 Add Start
        slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizMsg.D.no(slctLineIndex), glblMsg);
        slctConfIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizMsg.C.no(slctConfIndex), glblMsg);
//        int glblSlctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, glblSlctLineIndex);
        // 2018/01/25 S21_NA#19808 Add End

        NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(slctLineIndex);
        // 2018/03/28 S21_NA#25102 Add Start
        EZDMsg.copy(rmaLineBizMsg, null, rmaLineMsg, null);
        // 2018/03/28 S21_NA#25102 Add End
        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), rmaLineMsg.mdseCd_RL.getValue());

        if (mdseTMsg == null) {
            rmaLineMsg.ordCustUomQty_RL.clear();
            rmaLineMsg.mdseCd_RL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_ITEM });
            return;
        }

        // Call Default WH API
        // QC#55162 2019/12/16 Mod Start
        // NWAL1500CommonLogicForLineConfig.callDefWhApi(bizMsg, glblMsg, slctConfIndex, slctLineIndex, mdseTMsg.mdseCd.getValue()); // 2018/01/25 S21_NA#19808 Add
        if (NWAL1500CommonLogicForLineControl.isNotProtectedFieldForRmaTabByLineSts(rmaLineMsg)) {
            NWAL1500CommonLogicForLineConfig.callDefWhApi(bizMsg, glblMsg, slctConfIndex, slctLineIndex, mdseTMsg.mdseCd.getValue());
        }
        // QC#55162 2019/12/16 Mod End
        // 2016/08/26 S21_NA#9806 Add Start
//        EZDMsg.copy(bizMsg.D.no(slctLineIndex), null, glblMsg.D.no(glblSlctLineIndex), null);
        // 2016/08/26 S21_NA#9806 Add End
        // NWAL1500CommonLogicForLineControl.setChildUpdate(bizMsg.D, slctLineIndex); 2016/10/31 S21_NA#15541 Del
        NWAL1500CommonLogicForLineControl.setChildUpdate(bizMsg, bizMsg.D, bizMsg.xxCellIdx.getValueInt()); // 2016/10/31 S21_NA#15541 Add // 2018/01/25 S21_NA#19808 Mod
        NWAL1500CommonLogicForLineControl.setChildUpdate(bizMsg, glblMsg.D, slctLineIndex); // 2016/10/31 S21_NA#15541 Add // 2018/01/25 S21_NA#19808 Mod

        // Add Start 2017/01/27 QC#17257
        List<String> posnNums = new ArrayList<String>();
        posnNums.add(glblMsg.D.no(slctLineIndex).dsOrdPosnNum_RL.getValue());
        NWAL1500CommonLogicForLineConfig.updateBaseCmptFlg(bizMsg, glblMsg, posnNums, false); // 2018/01/25 S21_NA#19808 Mod
        // Add End 2017/01/27 QC#17257
        // 2018/03/28 S21_NA#25102 Add Start
        NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
        // 2018/03/28 S21_NA#25102 Add End
    }

    private void doProcess_NWAL1500Scrn00_OpenWin_Copy(NWAL1500CMsg bizMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, ZYPCommonFunc.leftPad(bizMsg.cpoOrdNum.getValue(), 6, "0"));
        S21SsmEZDResult ssmRslt = NWAL1500QueryForSearch.getInstance().isExistOrder(bizMsg);

        // mod start 2016/07/29 CSA S21_NA#12607
        // check Search finished yet
        if (!ZYPCommonFunc.hasValue(bizMsg.ordDt_DP)) {
            ssmRslt = NWAL1500QueryForSearch.getInstance().cntRebillOrder(bizMsg);
            if (ssmRslt.isCodeNormal()) {
                Map<String, BigDecimal> rsltMap = (Map<String, BigDecimal>) ssmRslt.getResultObject();
                // check CreditRebill order
                if (rsltMap.get("CD_CNT").compareTo(BigDecimal.ZERO) > 0 || rsltMap.get("CR_CNT").compareTo(BigDecimal.ZERO) > 0) {
                    bizMsg.setMessageInfo(NWAM0871E);
                    return;
                }
            }
        }
        // mod end 2016/07/29 CSA S21_NA#12607

        // QC#2176
        if (ssmRslt.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);
        }
    }

    private void doProcess_NWAL1500Scrn00_OpenWin_Holds(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        bizMsg.configCatgCd_AW.clear();
        ZYPTableUtil.clear(bizMsg.E);

        // 2018/03/07 S21_NA#19808 Add Start
        NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
        // 2018/03/07 S21_NA#19808 Add End

        // 2018/03/07 S21_NA#19808 Mod Start
//        List<Integer> checkedConfLineList = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
//        List<Integer> checkedLineList = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
//        List<Integer> checkedRmaConfLineList = ZYPTableUtil.getSelectedRows(bizMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
//        List<Integer> checkedRmaLineList = ZYPTableUtil.getSelectedRows(bizMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
        List<Integer> checkedConfLineList = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
        List<Integer> checkedLineList = ZYPTableUtil.getSelectedRows(glblMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
        List<Integer> checkedRmaConfLineList = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
        List<Integer> checkedRmaLineList = ZYPTableUtil.getSelectedRows(glblMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
        // 2018/03/07 S21_NA#19808 Mod End

        // 2017/08/21 S21_NA#19917 Add Start
        String xxDplyTab = bizMsg.xxDplyTab.getValue();

        if (S21StringUtil.isEquals(TAB_LINE_CONFIG, xxDplyTab)) {
            if (checkedConfLineList.size() == 0 && checkedLineList.size() == 0) {
                bizMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_LINE });
                return;
            }
        } else if (S21StringUtil.isEquals(TAB_RMA, xxDplyTab)) {
            if (checkedRmaConfLineList.size() == 0 && checkedRmaLineList.size() == 0) {
                bizMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_LINE });
                return;
            }
        }
        // 2017/08/21 S21_NA#19917 Add End

        // 2017/08/21 S21_NA#19917 Del Start
//        if (!NWAL1500CommonLogicForMultiWin.checkDuplicateForHold(bizMsg, checkedConfLineList, checkedLineList, checkedRmaConfLineList, checkedRmaLineList)) {
//            return;
//        }
        // 2017/08/21 S21_NA#19917 Del End

        if (!NWAL1500CommonLogicForMultiWin.checkUnregisteredConfLineForHold(bizMsg, glblMsg, checkedConfLineList, checkedRmaConfLineList)) { // 2018/03/07 S21_NA#19808 Add Start
            return;
        }

        if (!NWAL1500CommonLogicForMultiWin.checkUnregisteredLineForHold(bizMsg, glblMsg, checkedLineList, checkedRmaLineList)) { // 2018/03/07 S21_NA#19808 Add Start
            return;
        }

        NWAL1500CommonLogicForMultiWin.setOutParamForHold(bizMsg, glblMsg, checkedConfLineList, checkedLineList, checkedRmaConfLineList, checkedRmaLineList); // 2018/03/07 S21_NA#19808 Add Start
    }

    private void doProcess_NWAL1500Scrn00_OpenWin_DeliveryInfo(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        bizMsg.dsOrdPosnNum_AW.clear();
        bizMsg.configCatgCd_AW.clear();
        ZYPTableUtil.clear(bizMsg.E);

        // QC#2478
        String xxDplyTab = bizMsg.xxDplyTab.getValue();
        if (Arrays.asList(NWAL1500Constant.TAB_LINE_CONFIG, NWAL1500Constant.TAB_RMA).contains(xxDplyTab)) {
            // 2018/03/12 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            // 2018/03/12 S21_NA#19808 Add End
            List<Integer> checkedLineList = null;
            List<Integer> checkedRmaLineList = null;
            List<Integer> checkedLineConfList = null;
            List<Integer> checkedRmaLineConfList = null;

            if (NWAL1500Constant.TAB_LINE_CONFIG.equals(xxDplyTab)) {
                checkedLineList = ZYPTableUtil.getSelectedRows(glblMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
                checkedLineConfList = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            } else {
                checkedRmaLineList = ZYPTableUtil.getSelectedRows(glblMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
                checkedRmaLineConfList = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            }

            // 2018/07/23 S21_NA#26188 Add Start
            if (!NWAL1500CommonLogicForMultiWin.checkSelectedConfigForDelyInfo(bizMsg, glblMsg, checkedLineConfList, checkedRmaLineConfList)){
                return;
            }
            // 2018/07/23 S21_NA#26188 Add End

            if (!NWAL1500CommonLogicForMultiWin.checkSelectedLineForDelyInfo(bizMsg, glblMsg, checkedLineList, checkedRmaLineList)) { // 2018/03/07 S21_NA#19808 Mod
                return;
            }

            NWAL1500CommonLogicForMultiWin.setOutParamForDelyInfo(bizMsg, glblMsg, checkedLineConfList, checkedRmaLineConfList); // 2018/03/07 S21_NA#19808 Mod
        }
    }

    private void doProcess_NWAL1500Scrn00_OpenWin_Buyout(NWAL1500CMsg bizMsg) {

        // 2016/08/09 S21_NA#20093 Mod Start
//        NWAL1500_BCMsg lineMsg = bizMsg.B.no(bizMsg.xxCellIdx.getValueInt());

        NWAL1500CommonLogicForLineConfig.setSoldToInfo(bizMsg);

//        NWAL1500CommonLogicForLineConfig.setBillToInfo(bizMsg, lineMsg);
        // bizMsg.xxPopPrm_P5:"SELL_TO_CUST_CD" is not set. because not necessary.
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P6, NWAL1500CommonLogic.getVarCharConstVal(bizMsg, DEF_LSE_BO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P7, NWAL1500CommonLogic.getVarCharConstVal(bizMsg, DEF_LSE_BO_LOC_ADDR));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P8, NWAL1500CommonLogic.getVarCharConstVal(bizMsg, DEF_LSE_BO_LOC_CITY_ADDR));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P9, NWAL1500CommonLogic.getVarCharConstVal(bizMsg, DEF_LSE_BO_LOC_ST_CD));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_PA, NWAL1500CommonLogic.getVarCharConstVal(bizMsg, DEF_LSE_BO_LOC_POST_CD));
        // bizMsg.xxPopPrm_PB:"billToLocCd" is not set. because not necessary.
        // 2016/08/09 S21_NA#20093 Mod End
    }

    private void doProcess_NWAL1500Scrn00_Calculation_Order_Amount(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        copyBizDataToGlobalData(bizMsg, glblMsg);

        NWAL1500CommonLogicForPricing.calculationOrderAmount(bizMsg, glblMsg);

        // QC#8267 2016/05/24 Add Start
        // if (!NWAL1500CommonLogic.checkPriceRangeAll(bizMsg, glblMsg)) {
        //     return;
        // }
        // QC#8267 2016/05/24 Add End

        // 2016/02/23 S21_NA#1859 Add Start
        if (glblMsg.I.getValidCount() != glblMsg.X.getValidCount()) {
            glblMsg.X.clear();
            EZDMsg.copy(glblMsg.I, null, glblMsg.X, null);
        }
        // 2016/02/23 S21_NA#1859 Add End
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCalcDt, bizMsg.slsDt);
        bizMsg.setMessageInfo(ZZM8100I);
        bizMsg.xxBtnFlg_PR.setValue(ZYPConstant.FLG_ON_Y); // 2016/03/30 S21_NA#5523
        bizMsg.dsCpoPrcInd.setValue(ZYPConstant.FLG_ON_1); // 2016/09/05 S21_NA#6523 Add

        copyGlobalDataToBizData(glblMsg, bizMsg);
    }

    private void doProcess_NWAL1500Scrn00_Auto_AddSupply(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2016/02/22 S21_NA#1866 Mod Start
        NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y); // 2018/01/25 S21_NA#19808 Mod

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NWAL1500_ASMsg confMsg = glblMsg.A.no(i);
            
            // QC#24008 2018/02/07 Add Start
            if(CONFIG_TP.ADD_TO_CONFIG.equals(confMsg.configTpCd_LC.getValue())){
                continue;
            }
            // QC#24008 2018/02/07 Add End
            // 2017/11/16 S21_NA#22300 MOD START
            if(CONFIG_TP.TO_SALES_CONVERSION.equals(confMsg.configTpCd_LC.getValue())){
                continue;
            }
            // 2017/11/16 S21_NA#22300 MOD END

            if (checkList.size() == 0 || ZYPConstant.FLG_ON_Y.equals(confMsg.xxChkBox_LC.getValue())) {

                // call NWZC1810 Auto Add Supply API
                NWZC181001PMsg autoAddSplyApiPMsg = NWAL1500CommonLogicForAutoAdd.callAutoAddSplyApi(bizMsg, confMsg);
                if (autoAddSplyApiPMsg == null) {
                    return;
                }

                if (autoAddSplyApiPMsg.NWZC181002PMsg != null && autoAddSplyApiPMsg.NWZC181002PMsg.getValidCount() > 0) {
                    NWAL1500CommonLogicForAutoAdd.deriveSplyItem(bizMsg, glblMsg, confMsg, i,  autoAddSplyApiPMsg.NWZC181002PMsg);
                }
            }
        }
        NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
        // 2016/02/22 S21_NA#1866 Mod End
    }

    private void doProcess_NWAL1500Scrn00_Auto_AddRMA(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        boolean checkZeroFlg = NWAL1500CommonLogicForAutoAdd.doProcessAutoAddRMA(bizMsg, glblMsg, glblMsg.D); // 2018/01/25 S21_NA#19808 Mod

        // S21_NA#9104 Add Start
        if (checkZeroFlg) {
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {

                NWAL1500_CCMsg configMsg = bizMsg.C.no(i);
                configMsg.xxChkBox_RC.clear();
            }
            for (int j = 0; j < bizMsg.D.getValidCount(); j++) {
                NWAL1500_DCMsg lineMsg = bizMsg.D.no(j);
                lineMsg.xxChkBox_RL.clear();
            }

            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {

                NWAL1500_CSMsg configMsg = glblMsg.C.no(i);
                configMsg.xxChkBox_RC.clear();
            }
            for (int j = 0; j < glblMsg.D.getValidCount(); j++) {
                NWAL1500_DSMsg lineMsg = glblMsg.D.no(j);
                lineMsg.xxChkBox_RL.clear();
            }
        }
        // S21_NA#9104 Add End
    }

    private void doProcess_NWAL1500_NSAL1240(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        NWAL1500CommonLogicForAutoAdd.doProcessConfiId(bizMsg, glblMsg);
    }

    @SuppressWarnings("unchecked")
    private void doProcess_NWAL1500Scrn00_OpenWin_CarrierServiceLevel(NWAL1500CMsg bizMsg) {

        S21SsmEZDResult ssmRslt = NWAL1500QueryForSaveSubmit.getInstance().checkFreightCondText(bizMsg);

        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, rsltMapList.get(0).get("FRT_COND_CD"));
        } else {
            bizMsg.frtCondDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_FRT_TERMS });
        }
    }

    private void doProcess_NWAL1500Scrn00_OpenWin_OrderReason(NWAL1500CMsg bizMsg) {

        if (!NWAL1500QueryForCategory.getInstance().isExistDsOrdCatg(bizMsg)) {
            bizMsg.dsOrdTpDescTxt.clear();
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CATG });
        }
    }

    private void doProcess_NWAL1500Scrn00_OpenWin_Salesrep(NWAL1500CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForCategory.getInstance().getDsOrdTpList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdTpDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_RSN });
            return;
        }
    }

    private void doProcess_NWAL1500Scrn00_OpenWin_AssnProgram(NWAL1500CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForCategory.getInstance().getDsOrdTpList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdTpDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_RSN });
            return;
        }
    }

    // 2017/08/07 Sol#249 Add Start
    private void doProcess_NWAL1500Scrn00_OpenWin_OrderLineFilter(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        String msgId = "";

        if (S21StringUtil.isEquals(ORDER_LINE_FILTER_STEP_2, bizMsg.xxYesNoCd_FP.getValue())) {
            bizMsg.xxYesNoCd_FP.setValue(ZYPConstant.FLG_OFF_N);
        } else {
            msgId = NWAM0136I;
            bizMsg.xxYesNoCd_FP.setValue(ORDER_LINE_FILTER_STEP_2);
        }

        if (ZYPCommonFunc.hasValue(msgId)) {
            bizMsg.setMessageInfo(msgId);
            return;
        }

        String dplyTab = bizMsg.xxDplyTab.getValue();

        if (TAB_LINE_CONFIG.equals(dplyTab) && glblMsg.A.getValidCount() == 0
                || TAB_RMA.equals(dplyTab) && glblMsg.C.getValidCount() == 0) {
            bizMsg.setMessageInfo(NWAM0694E);
        }
    }
    // 2017/08/07 Sol#249 Add End

    // 2017/07/03 QC#26932 Add Start
    private void doProcess_NWAL1500Scrn00_OpenWin_ReturnAuthrization(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        ZYPTableUtil.clear(bizMsg.Q);
        S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getReturnAuthorizationMailAddress(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue());
        List<Map<String, Object>> rsltMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        int i = 0;
        for (Map<String, Object> map : rsltMapList) {
            ZYPEZDItemValueSetter.setValue(bizMsg.Q.no(i).ctacPsnEmlAddr_QL, (String) map.get("CTAC_PSN_EML_ADDR"));
            i++;
            if (i > bizMsg.Q.length()) {
                break;
            }
        }
    }
    // 2017/07/03 QC#26932 Add End

    // 2017/08/07 Sol#249 Add Start
    private void doProcess_NWAL1500_NWAL1890(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg);
    }
    // 2017/08/07 Sol#249 Add End

    private void doProcess_NWAL1500Scrn00_Order_Cancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
          // 2016/10/13 S21_NA#7700 Del Start
//        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum) && ZYPCommonFunc.hasValue(bizMsg.ezUpTime)) {
//            doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg);
//        } else {
//            doProcess_NWAL1500_INIT(bizMsg, glblMsg);
//        }
          // 2016/10/13 S21_NA#7700 Del End
        // 2016/10/13 S21_NA#7700 Add Start
        // 2017/10/04 S21_NA#21300 Mod Start
//        if (!NWAL1500CommonLogic.isEnableToCancelForOutbound(bizMsg, glblMsg)) {
//            bizMsg.setMessageInfo(NWAM0672E);
//            return;
//        }
        String errMsg = NWAL1500CommonLogic.isEnableToCancelForOutbound(bizMsg, glblMsg);
        if (!errMsg.isEmpty()) {
            bizMsg.setMessageInfo(errMsg);
        }
        // 2016/10/13 S21_NA#7700 Add End
        // 2017/10/04 S21_NA#21300 Mod End
    }

    private void doProcess_NWAL1500_NWAL0140(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod

        int slctLine = bizMsg.xxCellIdx.getValueInt();

        if (NO_SLCT_DTL == slctLine) {
            if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
                NWAL1500CommonLogicForCustomer.deriveDefaultShipToInfoForDtlFld(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
            }
        }
    }

    private void doProcess_NWAL1500_NWAL1130(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        String scrEventNm = bizMsg.xxScrEventNm.getValue();

        if ("OpenWin_OrderCategory".equals(scrEventNm) || "OnBlur_DeriveFromCategory".equals(scrEventNm)) {
            doProcess_NWAL1500Scrn00_OnBlur_DeriveFromCategory(bizMsg);
        } else if ("OpenWin_OrderReason".equals(scrEventNm) || "OnBlur_DeriveFromReason".equals(scrEventNm)) {
            doProcess_NWAL1500Scrn00_OnBlur_DeriveFromReason(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
        } else if ("OpenWin_Salesrep".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepCode".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepName".equals(scrEventNm)) {
            doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSalesRepCode(bizMsg, glblMsg);
        } else if ("OpenWin_Warehouse".equals(scrEventNm) || "OnBlur_ChangeWH".equals(scrEventNm)) {
            doProcess_NWAL1500Scrn00_OnBlur_ChangeWH(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
        } else if ("OpenWin_SubWarehouse".equals(scrEventNm) || "OnBlur_ChangeSubWH".equals(scrEventNm)) {
            doProcess_NWAL1500Scrn00_OnBlur_ChangeSubWH(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808 Mod
        } else if ("OpenWin_FreightTerms".equals(scrEventNm)) { // 2016/03/04 S21_NA#1679 Add
            NWAL1500CommonLogicForCategory.setShpgSvcLvlPullDown(bizMsg);
            // 2018/12/21 S21_NA#29315 Del Start
//            NWAL1500CommonLogic.deriveDefaultCarrSvcLvl(bizMsg); // QC#13688 2017/02/24 Add
            // 2018/12/21 S21_NA#29315 Del END
            // 2019/01/08 S21_NA#29789 Add Start
            if (ZYPCommonFunc.hasValue(bizMsg.frtCondCd) && !FRT_COND.COLLECT.equals(bizMsg.frtCondCd.getValue())) {
                bizMsg.carrAcctNum.clear();
            }
            // 2019/01/08 S21_NA#29789 Add End
        } else if ("OpenWin_CSMPNumber".equals(scrEventNm) //
                || "OnBlur_DeriveFromCSMPNumber".equals(scrEventNm) //
                || "OnBlur_DeriveFromDealerRefNumber" .equals(scrEventNm)) { // 2016/08/26 S21_NA#9806 Add
            int cellIdx = bizMsg.xxCellIdx.getValueInt();
            String dplyTab = bizMsg.xxDplyTab.getValue();
            if (cellIdx < 0) {
                // 2017/07/21 S21_NA#19847 Add Start
                if (ZYPCommonFunc.hasValue(bizMsg.csmpContrNum) //
                        || ZYPCommonFunc.hasValue(bizMsg.dlrRefNum)) {
                    NWAL1500CommonLogicForCustomer.deriveDefaultAssPg(bizMsg);
                }
                // 2017/07/21 S21_NA#19847 Add End
                return;
            }
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                setCsmpPriceListForConfig(bizMsg, bizMsg.A.no(cellIdx));
                setDefaultLineCsmpData(bizMsg, glblMsg, bizMsg.A.no(cellIdx)); // 2018/01/25 S21_NA#19808 Mod
            } else if (TAB_RMA.equals(dplyTab)) {
                setCsmpPriceListForConfig(bizMsg, bizMsg.C.no(cellIdx));
                setDefaultLineCsmpData(bizMsg, glblMsg, bizMsg.C.no(cellIdx)); // 2018/01/25 S21_NA#19808 Mod
            }
        } else if ("OpenWin_SubstituteItem".equals(scrEventNm)) { // 2019/11/21 S21_NA#54202 Add Start
            doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSubstituteItem(bizMsg, glblMsg);
            // 2019/11/21 S21_NA#54202 Add End
        }
    }

    private void doProcess_NWAL1500_NWAL1600(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod

        if (TAB_HEADER.equals(bizMsg.xxDplyTab.getValue()) || TAB_ADDL_HEADER.equals(bizMsg.xxDplyTab.getValue())) {
            boolean isModifiedHdrSlsCrInfo = NWAL1500CommonLogicForSalesCredit.copySlsCrForHdrFromPopup(bizMsg);

            // Header All Deleted
            if (NWAL1500CommonLogicForSalesCredit.isParamDtAllDeleted(bizMsg.O)) {
                bizMsg.slsRepTocCd.clear();
                bizMsg.slsRepTocNm.clear();
                bizMsg.psnNum.clear(); // 2016/05/11 S21_NA#7861 Mod slsRepPsnCd -> psnNum
                bizMsg.coaBrCd.clear();
                bizMsg.coaBrDescTxt.clear();
                bizMsg.coaExtnCd.clear();
                bizMsg.coaExtnDescTxt.clear();
                bizMsg.xxScrItem54Txt_CB.clear();
                bizMsg.xxScrItem54Txt_CE.clear();

                // Delete All Sales Credit
                NWAL1500CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);
                // NWAL1500CommonLogicForSalesCredit.allSlsCrInfoSetDeleteMode(bizMsg);
                return;
            }

            // Copy To Config
            if (isModifiedHdrSlsCrInfo) {
                // bizMsg.G.clear(); 2015/12/07 S21_NA#1550 Del
                for (int i = 0; i < glblMsg.A.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                    // NWAL1500CommonLogicForSalesCredit.copyParamSlsCrInfo(bizMsg, bizMsg.A.no(i)); 2016/05/24 S21_NA#8617 Del
                    // 2016/05/24 S21_NA#8617 Add // 2018/01/25 S21_NA#19808 Add Start
                    NWAL1500_ACMsg configBizMsg = new NWAL1500_ACMsg();
                    EZDMsg.copy(glblMsg.A.no(i), null, configBizMsg, null);
                    // 2016/05/24 S21_NA#8617 Add // 2018/01/25 S21_NA#19808 Add End
                    NWAL1500CommonLogicForSalesCredit.copyParamSlsCrInfoFromModifyHeader(bizMsg, configBizMsg); // 2018/01/25 S21_NA#19808 Mod
                }

                // bizMsg.H.clear(); 2015/12/07 S21_NA#1550 Del
                for (int i = 0; i < glblMsg.C.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
                    // NWAL1500CommonLogicForSalesCredit.copyParamSlsCrInfo(bizMsg, bizMsg.C.no(i)); 2016/05/24 S21_NA#8617 Del
                    // 2016/05/24 S21_NA#8617 Add // 2018/01/25 S21_NA#19808 Add Start
                    NWAL1500_CCMsg configBizMsg = new NWAL1500_CCMsg();
                    EZDMsg.copy(glblMsg.C.no(i), null, configBizMsg, null);
                    // 2016/05/24 S21_NA#8617 Add // 2018/01/25 S21_NA#19808 Add End
                    NWAL1500CommonLogicForSalesCredit.copyParamSlsCrInfoFromModifyHeader(bizMsg, configBizMsg); // 2016/05/24 S21_NA#8617 Add // 2018/01/25 S21_NA#19808 Mod
                }
            }
        } else {
            // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
            // NWAL1500CommonLogicForSalesCredit.setSlsCreditOutPutParamToBizMsg(bizMsg);
            NWAL1500CommonLogicForSalesCredit.setSlsCreditOutPutParamToBizMsg(bizMsg, glblMsg);
            // END 2023/06/06 T.Doi [CSA-QC#61465, MOD]
        }
    }

    private void doProcess_NWAL1500_NWAL1610(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        List<Integer> checkListItemConfig = new ArrayList<Integer>();
        List<Integer> checkListItemLine = new ArrayList<Integer>();
        List<Integer> checkListRMAConfig = new ArrayList<Integer>();
        List<Integer> checkListRMALine = new ArrayList<Integer>();
        List<String> notHardAllocWhCd = NWAL1500CommonLogic.getVarCharConstDataList(bizMsg.glblCmpyCd.getValue(), NWAL1500Constant.NOT_ALLOC_WH_CD);
        List<String> allowRddChangeWhList = NWAL1500CommonLogic.getVarCharConstDataList(bizMsg.glblCmpyCd.getValue(), NWAL1500Constant.NWAL1500_ALLOW_RDD_CHANGE_WH); // 2016/03/15 S21_NA#2096 Add

        // 2017/12/26 S21_NA#22986 Add Start
        String notIttHldOrdLineSrcCd = null;
        // 2017/12/26 S21_NA#22986 Add End
        // QC#22371 2017/12/26 Add Start
        List<Integer> configPool = new ArrayList<Integer>();
        // QC#22371 2017/12/26 Add End

        // 2018/01/25 S21_NA#19808 Mod bizMsg => glblMsg
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            checkListItemConfig = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            checkListItemLine = ZYPTableUtil.getSelectedRows(glblMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
        } else {
            checkListRMAConfig = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            checkListRMALine = ZYPTableUtil.getSelectedRows(glblMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
        }

        // 2016/08/30 S21_NA#9806 Add Start
        String csmpContrNum = "";
        String dlrRefNum = "";
        String csmpPrcListCd = "";
        String csmpPrcCatgNm = "";

        if (checkListItemConfig.size() > 0 //
                || checkListRMAConfig.size() > 0) {

            csmpContrNum = bizMsg.xxPopPrm_PC.getValue();
            dlrRefNum = bizMsg.xxPopPrm_PJ.getValue();

            if (ZYPCommonFunc.hasValue(csmpContrNum) //
                    || ZYPCommonFunc.hasValue(dlrRefNum)) {
                NWZC157001PMsg prcApiMsg = new NWZC157001PMsg();
                getPrcApiParamForCsmpPrcList(prcApiMsg, bizMsg, csmpContrNum, dlrRefNum);
                Map<String, String> rsltMap = getCsmpPrcList(prcApiMsg, bizMsg);

                csmpPrcListCd = rsltMap.get("prcCatgCd");
                csmpPrcCatgNm = rsltMap.get("prcCatgNm");
            }
        }
        // 2016/08/30 S21_NA#9806 Add End

        if (checkListItemConfig.size() > 0) {
            String shipToCustCd = bizMsg.xxPopPrm_P1.getValue();
            Map<String, String> resultMapShip = null;
            if (ZYPCommonFunc.hasValue(shipToCustCd)) {
                resultMapShip = NWAL1500Query.getInstance().getDropShipInfo(bizMsg.glblCmpyCd.getValue(), shipToCustCd);
            }

            // S21_NA#6148
            String billToCustCd = bizMsg.xxPopPrm_P2.getValue();
            S21SsmEZDResult resultBill = null;
            Map<String, String> resultMapBill = null;
            if (ZYPCommonFunc.hasValue(billToCustCd)) {
                resultBill //
                = NWAL1500QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, billToCustCd);
                if (resultBill.isCodeNormal()) {
                    resultMapBill = (Map<String, String>) resultBill.getResultObject();
                }
            }

            for (int n : checkListItemConfig) {
                // 2019/07/11 S21_NA#51287 Add Start
                if (!NWAL1500CommonLogicForLineControl.isNotProtectedLineTabFieldByConfigSts(glblMsg, glblMsg.A.no(n))) {
                    continue;
                }
                // 2019/07/11 S21_NA#51287 Add End
                if (ZYPCommonFunc.hasValue(shipToCustCd)) {
                    String bkShipToCustCd = glblMsg.A.no(n).shipToCustCd_LC.getValue();
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(n).shipToCustCd_LC, shipToCustCd);
                    if (!S21StringUtil.isEquals(bkShipToCustCd, shipToCustCd)) {
                        NWAL1500CommonLogic.setDsInfo(glblMsg.A.no(n), resultMapShip);
                        NWAL1500CommonLogic.clearControlFields(glblMsg, glblMsg.A.no(n));
                        // 2018/09/20 S21_NA#28199 Add Start
                        NWAL1500CommonLogic.deriveDefaultWhForConfig(bizMsg, glblMsg, n, true);
                        // 2018/09/20 S21_NA#28199 Add End
                    }
                }
                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_P2)) {
                    String bkBillToCustCd = glblMsg.A.no(n).billToCustCd_LC.getValue();
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(n).billToCustCd_LC, bizMsg.xxPopPrm_P2);
                    // S21_NA#6148
                    if (!S21StringUtil.isEquals(bkBillToCustCd, billToCustCd)) {
                        NWAL1500CommonLogic.setDsInfoBill(glblMsg.A.no(n), resultMapBill);
                    }
                }
                // 2016/08/30 S21_NA#9806 Add Start
                boolean isMod = false;
                if (ZYPCommonFunc.hasValue(csmpContrNum) //
                        && !S21StringUtil.isEquals(csmpContrNum, glblMsg.A.no(n).csmpContrNum_LC.getValue())) {
                    glblMsg.A.no(n).csmpContrNum_LC.setValue(csmpContrNum);
                    isMod = true;
                }
                if (ZYPCommonFunc.hasValue(dlrRefNum) //
                        && !S21StringUtil.isEquals(dlrRefNum, glblMsg.A.no(n).dlrRefNum_LC.getValue())) {
                    glblMsg.A.no(n).dlrRefNum_LC.setValue(dlrRefNum);
                    isMod = true;
                }
                if (isMod) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(n).csmpPrcListCd_LC, csmpPrcListCd);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(n).prcCatgNm_LC, csmpPrcCatgNm);

                    setDefaultLineCsmpData(bizMsg, glblMsg, glblMsg.A.no(n));
                }
                // 2016/08/30 S21_NA#9806 Add End
            }
        }
        boolean isError = false;
        BigDecimal eachQty = null;
        if (checkListItemLine.size() > 0) {
            // 2018/09/20 S21_NA#28199 Add Start
            List<String> updRtlWhConfigList = new ArrayList<String>(0);
            if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_P6)) {
                String rtlWhCd = bizMsg.xxPopPrm_P6.getValue();
                for (int slctLineIdx : checkListItemLine) {
                    NWAL1500_BSMsg line = glblMsg.B.no(slctLineIdx);
                    // Mod Start 2019/07/29 QC#52164
                    if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, line.baseCmptFlg_LL.getValue()) //
//                            && NWAL1500CommonLogicForLineControl.isNotProtectedFieldForLineTabByLineSts(line)) {
                            && NWAL1500CommonLogicForLineControl.isNotProtectedFieldForLineTabByLineSts(line) && !ZYPCommonFunc.hasValue(line.crRebilCd_LL)) {
                        // QC#52264 2019/07/31 Add Start
                        if(ZYPCommonFunc.hasValue(line.svcMachMstrPk_LL)){
                            continue;
                        }
                        // QC#52264 2019/07/31 Mod End
                        ZYPEZDItemValueSetter.setValue(line.rtlWhCd_LL, rtlWhCd);
                        ZYPEZDItemValueSetter.setValue(line.rtlWhNm_LL, NWAL1500CommonLogicForLineConfig.getWhNm(bizMsg, rtlWhCd));
                        NWAL1500CommonLogic.setRtlWhCdAsBaseComponent(bizMsg, glblMsg, line.dsOrdPosnNum_LL.getValue(), CONFIG_CATG.OUTBOUND);
                        updRtlWhConfigList.add(line.dsOrdPosnNum_LL.getValue());
                    }
                    // Mod End 2019/07/29 QC#52164
                }
            }
            // 2018/09/20 S21_NA#28199 Add End
            for (int n : checkListItemLine) {
                isError = false;
                NWAL1500_BSMsg line = glblMsg.B.no(n);
                // 2018/03/08 S21_NA#19808 Add Start
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, line.xxResFltrFlg_LL.getValue())) {
                    continue;
                }
                // 2018/03/08 S21_NA#19808 Add End
                // 2016/10/26 S21_NA#15587 Add Start
                MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), line.mdseCd_LL.getValue());
                boolean isSet = false;
                boolean isMdseTangible = true;
                if (mdseTMsg == null) {
                    line.ordQty_LL.clear();
                    line.mdseCd_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_ITEM });
                    isError = true;
                } else {
                    isSet = S21StringUtil.isEquals(MDSE_TP.SET, mdseTMsg.mdseTpCd.getValue());
                    isMdseTangible = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.invtyCtrlFlg.getValue());
                }
                // 2016/10/26 S21_NA#15587 Add End
                boolean editable = NWAL1500CommonLogicForLineControl.isNotProtectedFieldForLineTabByLineSts(line); // 2018/08/03 S21_NA#27040 Add 
                // if (ZYPCommonFunc.hasValue(bizMsg.ordQty_AW)) {
                if (ZYPCommonFunc.hasValue(bizMsg.ordQty_AW) && editable) { // 2018/08/03 S21_NA#27040 Add Condition
                    ZYPEZDItemValueSetter.setValue(line.ordCustUomQty_LL, bizMsg.ordQty_AW);
                    // MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), line.mdseCd_LL.getValue()); 2016/10/26 S21_NA#15587 Del
                    if (mdseTMsg == null) {
                        line.ordQty_LL.clear();
                        line.mdseCd_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_ITEM });
                        isError = true;
                    }

                    if (!isError) {
                        eachQty = NWAL1500CommonLogicForLineConfig.getEachQty(bizMsg, mdseTMsg.mdseCd.getValue(), line.custUomCd_LL.getValue(), line.ordCustUomQty_LL.getValue());
                        ZYPEZDItemValueSetter.setValue(line.ordQty_LL, eachQty);
                        if (eachQty == null) {
                            isError = true;
                        }
                    }

                    if (!isError) {
                        if (!NWAL1500CommonLogicForLineConfig.checkQtyForSerializedItem(bizMsg, mdseTMsg, eachQty.intValue())) { // 2018/03/08 S21_NA#19808
                            // line.mdseCd_LL.setErrorInfo(1, NWAM0772E); 2017/03/02 S21_NA#17714-2 Del
                            line.ordCustUomQty_LL.setErrorInfo(1, NWAM0772E); // 2017/03/02 S21_NA#17714-2 Add
                            isError = true;
                        }
                    }

//                    NWAL1500CommonLogicForLineControl.setChildLine(bizMsg, glblMsg, glblMsg.B, n);

                }
                // if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_P4)) {
                // 2019/07/05 S21_NA#51151 Add Start
                boolean isSetUpLeaseBuouttem = ZYPCommonFunc.hasValue(ZYPCodeDataUtil.getVarCharConstValue(LEASE_BYOT_MDSE_CD, bizMsg.glblCmpyCd.getValue()));
                boolean isLeaseBuout = DS_ORD_LINE_CATG.LEASE_BUYOUT.equals(bizMsg.xxPopPrm_P4.getValue());
                boolean isRelatedShellItem = isRelatedShellItem(bizMsg, glblMsg.B.no(n));
                boolean isProhibitChangeLineCategory =  isSetUpLeaseBuouttem && isLeaseBuout && isRelatedShellItem;
                // 2019/07/05 S21_NA#51151 Add End
                // 2019/07/05 S21_NA#51151 Mod Start
//                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_P4) && editable) { // 2018/08/03 S21_NA#27040 Add Condition
                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_P4) && editable && !isProhibitChangeLineCategory) {
                    // 2019/07/05 S21_NA#51151 Mod End
                    ZYPEZDItemValueSetter.setValue(line.dsOrdLineCatgCd_LL, bizMsg.xxPopPrm_P4);
                    // QC#14436 2016/09/13 Add Start
                    // 2019/07/05 S21_NA#51151 Del Start
//                    bizMsg.xxCellIdx_BB.setValue(n);
                    // 2019/07/05 S21_NA#51151 Del End
                    // 2019/07/05 S21_NA#51151 Add Start
                    int lineIdx = n % bizMsg.B.length();
                    int pageNum = n / bizMsg.B.length() + 1;
                    int curPageNum = bizMsg.xxPageShowCurNum_LL.getValueInt();
                    NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, pageNum);
                    EZDMsg.copy(line, null, bizMsg.B.no(lineIdx), null);
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(lineIdx).dsOrdLineCatgCd_LL, bizMsg.xxPopPrm_P4);
                    bizMsg.xxCellIdx_BB.setValue(lineIdx);
                    // 2019/07/05 S21_NA#51151 Add End
                    bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
                    doProcess_NWAL1500Scrn00_OnChange_LineCategory(bizMsg, glblMsg);
                    // QC#14436 2016/09/13 Add End
                    // 2019/07/05 S21_NA#51151 Add Start
                    EZDMsg.copy(bizMsg.B.no(lineIdx), null, line, null);
                    bizMsg.xxPageShowCurNum_LL.setValue(curPageNum);
                    // 2019/07/05 S21_NA#51151 Add End
                }
                // if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_P5)) {
                // Mod Start 2019/07/27 QC#52164
//                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_P5) && editable) { // 2018/08/03 S21_NA#27040 Add Condition
                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_P5) && editable && !ZYPCommonFunc.hasValue(line.crRebilCd_LL)) {
                    ZYPEZDItemValueSetter.setValue(line.ordLineSrcCd_LL, bizMsg.xxPopPrm_P5);
                }
                // Mod End 2019/07/27 QC#52164
                String rtlWhCd = bizMsg.xxPopPrm_P6.getValue();
                // 2018/09/20 S21_NA#28199 Add Start
                boolean isAlreadySet = updRtlWhConfigList.contains(line.dsOrdPosnNum_LL.getValue());
                // 2018/09/20 S21_NA#28199 Add End
                // if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                // Mod Start 2019/07/27 QC#52164
//                if (ZYPCommonFunc.hasValue(rtlWhCd) && editable && !isAlreadySet) { // 2018/08/03 S21_NA#27040 Add Condition 2018/09/20 S21_NA#28199 Add Condition !isAlreadySet
                if (ZYPCommonFunc.hasValue(rtlWhCd) && editable && !isAlreadySet && !ZYPCommonFunc.hasValue(line.crRebilCd_LL)) {
                    // QC#52264 2019/07/31 Add Start
                    if(ZYPCommonFunc.hasValue(line.svcMachMstrPk_LL)){
                        continue;
                    }
                    // QC#52264 2019/07/31 Mod End
                    ZYPEZDItemValueSetter.setValue(line.rtlWhCd_LL, rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(line.rtlWhNm_LL, NWAL1500CommonLogicForLineConfig.getWhNm(bizMsg, rtlWhCd));
                }
                // Mod End 2019/07/27 QC#52164
                String rtlSubWhCd = bizMsg.xxPopPrm_P7.getValue();
                int slctConfIndex = NWAL1500CommonLogicForLineControl.getConfigIndex(glblMsg.A, line.dsOrdPosnNum_LL.getValue()); // 2018/03/08 S21_NA#19808
                // if (ZYPCommonFunc.hasValue(rtlSubWhCd)) {
                // Mod Start 2019/07/27 QC#52164
//                if (ZYPCommonFunc.hasValue(rtlSubWhCd) && editable) { // 2018/08/03 S21_NA#27040 Add Condition
                if (ZYPCommonFunc.hasValue(rtlSubWhCd) && editable && !ZYPCommonFunc.hasValue(line.crRebilCd_LL)) {
                    ZYPEZDItemValueSetter.setValue(line.rtlSwhCd_LL, rtlSubWhCd);
                    ZYPEZDItemValueSetter.setValue(line.rtlSwhNm_LL, NWAL1500CommonLogicForLineConfig.getSubWhNm(bizMsg, rtlWhCd, rtlSubWhCd));
                    // QC#22371 2017/12/26 Add Start
//                    int slctConfIndex = NWAL1500CommonLogicForLineControl.getConfigIndex(glblMsg.A, line.dsOrdPosnNum_LL.getValue()); 2018/03/08 S21_NA#19808
                    if(!configPool.contains(slctConfIndex)){
                        configPool.add(slctConfIndex);
                    }
                    // QC#22371 2017/12/26 Add End
                }
                // Mod End 2019/07/27 QC#52164
                // if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_P9)) {
                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_P9) && editable) { // 2018/08/03 S21_NA#27040 Add Condition
                    ZYPEZDItemValueSetter.setValue(line.sbstMdseCd_LL, bizMsg.xxPopPrm_P9);
                }
                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_PA)) {
                    ZYPEZDItemValueSetter.setValue(line.prcCatgNm_LL, bizMsg.xxPopPrm_PA);
                }
                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_PB)) {
                    ZYPEZDItemValueSetter.setValue(line.flPrcListNm_LL, bizMsg.xxPopPrm_PB);
                }
                if (ZYPCommonFunc.hasValue(bizMsg.prcBaseDt_AW)) {
                    ZYPEZDItemValueSetter.setValue(line.prcBaseDt_LL, bizMsg.prcBaseDt_AW);
                }
                // if (ZYPCommonFunc.hasValue(bizMsg.rddDt_AW)) {
                if (ZYPCommonFunc.hasValue(bizMsg.rddDt_AW) && editable) { // 2018/08/03 S21_NA#27040 Add Condition
                    rtlWhCd = NWAL1500CommonLogicForLineConfig.getWhCd(bizMsg, line);

                    // 2016/03/15 S21_NA#2096 Add Start
                    boolean isNotAlloc = notHardAllocWhCd.contains(rtlWhCd);
                    boolean isAllowMassUp = allowRddChangeWhList.contains(rtlWhCd);
                    boolean allowOverWrite = false;
                    if (!isNotAlloc) {
                        allowOverWrite = true;
                    } else if (isNotAlloc && isAllowMassUp) {
                        allowOverWrite = true;
                    }
                    // 2016/03/15 S21_NA#2096 Add End

                    // 2016/03/15 S21_NA#2096 Mod Start
//                    if (NWAL1500CommonLogic.isMdseTangible(bizMsg.glblCmpyCd.getValue(), line.mdseCd_LL.getValue())
//                            && !notHardAllocWhCd.contains(rtlWhCd)) {
//                        ZYPEZDItemValueSetter.setValue(line.rddDt_LL, bizMsg.rddDt_AW);
//                    }
                    // 2016/10/26 S21_NA#15587 Mod Start
//                    if (NWAL1500CommonLogic.isMdseTangible(bizMsg.glblCmpyCd.getValue(), line.mdseCd_LL.getValue())
//                            && allowOverWrite) {
//                        ZYPEZDItemValueSetter.setValue(line.rddDt_LL, bizMsg.rddDt_AW);
//                    }
                    if ((isMdseTangible || isSet) && allowOverWrite) {
                        ZYPEZDItemValueSetter.setValue(line.rddDt_LL, bizMsg.rddDt_AW);
                    }
                    // 2016/10/26 S21_NA#15587 Mod End
                    // 2016/03/15 S21_NA#2096 Mod Start
                }
                // 2017/12/26 S21_NA#22986 Add Start
                // QC#52416 2019/12/16 Mod Start
                // if (!isSet && !isMdseTangible) {
                if (!isSet && !isMdseTangible && !ZYPCommonFunc.hasValue(line.crRebilCd_LL)) {
                // QC#52416 2019/12/16 Mod End
                    // 2019/09/19 S21_NA#53585 Add Start
                    boolean isClearWh = true;
                    for (int j = 0; j < bizMsg.P.getValidCount(); j++) {
                        if (bizMsg.P.no(j).dsOrdLineCatgCd_P.getValue().equals(line.dsOrdLineCatgCd_LL.getValue())) {
                            isClearWh = false;
                            break;
                        }
                    }
                    if (isClearWh) {
                    // 2019/09/19 S21_NA#53585 Add End
                        line.rtlWhCd_LL.clear();
                        line.rtlWhNm_LL.clear();
                        line.rtlSwhCd_LL.clear();
                        line.rtlSwhNm_LL.clear();
                        line.invtyLocCd_LL.clear();
                    // 2019/09/19 S21_NA#53585 Add Start
                    }
                    // 2019/09/19 S21_NA#53585 Add End

                    if (NWXC150001DsCheck.isOrdLineSrcItt(bizMsg.glblCmpyCd.getValue(), line.ordLineSrcCd_LL.getValue())) {
                        if (notIttHldOrdLineSrcCd == null) {
                            notIttHldOrdLineSrcCd = NWXC150001DsCheck.getMinOrdLineSrcNotIttHld(bizMsg.glblCmpyCd.getValue());
                        }
                        ZYPEZDItemValueSetter.setValue(line.ordLineSrcCd_LL, notIttHldOrdLineSrcCd);
                    }
                }
                // 2017/12/26 S21_NA#22986 Add End
//                int slctConfIndex = NWAL1500CommonLogicForLineControl.getConfigIndex(glblMsg.A, line.dsOrdPosnNum_LL.getValue()); 2018/03/08 S21_NA#19808
                // Call Pricing API
                if (!isError) {
                    if (ZYPCommonFunc.hasValue(bizMsg.ordQty_AW) || ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_PA)) {
                        NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, n);
                    }
                }
                // QC#22372 2018/01/10 Add Start
                // Call Pricing API For Floor Price
                if (!isError) {
                    if (ZYPCommonFunc.hasValue(bizMsg.ordQty_AW) || ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_PB)) {
                        NWAL1500CommonLogicForLineConfig.deriveLineFloorPrice(bizMsg, glblMsg, slctConfIndex, n);
                    }
                }
                // QC#22372 2018/01/10 Add End
                // NWAL1500CommonLogicForLineControl.setChildUpdate(glblMsg.B, n); 2016/03/15 S21_NA#4691 Change Interface
                NWAL1500CommonLogicForLineControl.setChildUpdate(bizMsg, glblMsg.B, n);
                // QC#22371 2017/12/26 Add Start
                if (configPool.size() > 0) {
                    for (Integer configIndex : configPool) {
                        setDefaultLineCsmpData(bizMsg, glblMsg, glblMsg.A.no(configIndex));

                    }
                }
                // QC#22371 2017/12/26 Add End
            }
        }

        if (checkListRMAConfig.size() > 0) {
            String shipToCustCd = bizMsg.xxPopPrm_P1.getValue();
            Map<String, String> resultMap = null;
            if (ZYPCommonFunc.hasValue(shipToCustCd)) {
                resultMap = NWAL1500Query.getInstance().getDropShipInfo(bizMsg.glblCmpyCd.getValue(), shipToCustCd);
            }

            // S21_NA#6148
            String billToCustCd = bizMsg.xxPopPrm_P2.getValue();
            S21SsmEZDResult resultBill = null;
            Map<String, String> resultMapBill = null;
            if (ZYPCommonFunc.hasValue(billToCustCd)) {
                resultBill //
                = NWAL1500QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, billToCustCd);
                if (resultBill.isCodeNormal()) {
                    resultMapBill = (Map<String, String>) resultBill.getResultObject();
                }
            }

            for (int n : checkListRMAConfig) {
                // 2019/07/11 S21_NA#51287 Add Start
                if (!NWAL1500CommonLogicForLineControl.isNotProtectedRMATabFieldByConfigSts(glblMsg, glblMsg.C.no(n))) {
                    continue;
                }
                // 2019/07/11 S21_NA#51287 Add End
                if (ZYPCommonFunc.hasValue(shipToCustCd)) {
                    if (NWAL1500CommonLogicForLineControl.isNotProtectedRMATabShiptoFieldByConfigSts(glblMsg, glblMsg.C.no(n))) { // 2019/07/11 S21_NA#51287 Add
                        String bkShipToCustCd = glblMsg.C.no(n).shipToCustCd_RC.getValue();
                        ZYPEZDItemValueSetter.setValue(glblMsg.C.no(n).shipToCustCd_RC, shipToCustCd);
                        if (!S21StringUtil.isEquals(bkShipToCustCd, shipToCustCd)) {
                            NWAL1500CommonLogic.setDsInfo(glblMsg.C.no(n), resultMap);
                            NWAL1500CommonLogic.clearRMAControlFields(glblMsg, glblMsg.C.no(n));
                            // 2018/09/20 S21_NA#28199 Add Start
                            NWAL1500CommonLogic.deriveDefaultWhForConfig(bizMsg, glblMsg, n, false);
                            // 2018/09/20 S21_NA#28199 Add End
                        }
                    } // 2019/07/11 S21_NA#51287 Add
                }
                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_P2)) {
                    String bkBillToCustCd = glblMsg.C.no(n).billToCustCd_RC.getValue();
                    ZYPEZDItemValueSetter.setValue(glblMsg.C.no(n).billToCustCd_RC, bizMsg.xxPopPrm_P2);
                    // S21_NA#6148
                    if (!S21StringUtil.isEquals(bkBillToCustCd, billToCustCd)) {
                        NWAL1500CommonLogic.setDsInfoBill(glblMsg.C.no(n), resultMapBill);
                    }
                }
                // 2016/08/30 S21_NA#9806 Add Start
                boolean isMod = false;
                if (ZYPCommonFunc.hasValue(csmpContrNum) //
                        && !S21StringUtil.isEquals(csmpContrNum, glblMsg.C.no(n).csmpContrNum_RC.getValue())) {
                    glblMsg.C.no(n).csmpContrNum_RC.setValue(csmpContrNum);
                    isMod = true;
                }
                if (ZYPCommonFunc.hasValue(dlrRefNum) //
                        && !S21StringUtil.isEquals(dlrRefNum, glblMsg.C.no(n).dlrRefNum_RC.getValue())) {
                    glblMsg.C.no(n).dlrRefNum_RC.setValue(dlrRefNum);
                    isMod = true;
                }
                if (isMod) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.C.no(n).csmpPrcListCd_RC, csmpPrcListCd);
                    ZYPEZDItemValueSetter.setValue(glblMsg.C.no(n).prcCatgNm_RC, csmpPrcCatgNm);

                    setDefaultLineCsmpData(bizMsg, glblMsg, glblMsg.C.no(n));
                }
                // 2016/08/30 S21_NA#9806 Add End
            }
        }

        if (checkListRMALine.size() > 0) {
            for (int n : checkListRMALine) {
                isError = false;
                NWAL1500_DSMsg line = glblMsg.D.no(n);
                // 2018/03/08 S21_NA#19808 Add Start
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, line.xxResFltrFlg_RL.getValue())) {
                    continue;
                }
                // 2018/03/08 S21_NA#19808 Add End
                boolean editable = NWAL1500CommonLogicForLineControl.isNotProtectedFieldForRmaTabByLineSts(line); // 2018/08/03 S21_NA#27040 Add 
                // if (ZYPCommonFunc.hasValue(bizMsg.ordQty_AW)) {
                if (ZYPCommonFunc.hasValue(bizMsg.ordQty_AW) && editable) { // 2018/08/03 S21_NA#27040 Add Condition
                    ZYPEZDItemValueSetter.setValue(line.ordCustUomQty_RL, bizMsg.ordQty_AW);
                    if (BigDecimal.ZERO.compareTo(bizMsg.ordQty_AW.getValue()) < 0) {
                        ZYPEZDItemValueSetter.setValue(line.ordCustUomQty_RL, bizMsg.ordQty_AW.getValue().multiply(BigDecimal.valueOf(-1)));
                    }
                    MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), line.mdseCd_RL.getValue());
                    if (mdseTMsg == null) {
                        line.ordQty_RL.clear();
                        line.mdseCd_RL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_ITEM });
                        isError = true;
                    }

                    if (!isError) {
                        eachQty = NWAL1500CommonLogicForLineConfig.getEachQty(bizMsg, mdseTMsg.mdseCd.getValue(), line.custUomCd_RL.getValue(), line.ordCustUomQty_RL.getValue());
                        ZYPEZDItemValueSetter.setValue(line.ordQty_RL, eachQty);
                        if (eachQty == null) {
                            isError = true;
                        }
                    }

                    // 2016/04/08 S21_NA#5356 Add Start
                    if (!isError) {
                        if (!NWAL1500CommonLogicForLineConfig.checkQtyForSerializedItem(bizMsg, mdseTMsg, eachQty.intValue())) { // 2018/03/08 S21_NA#19808
                            // line.mdseCd_RL.setErrorInfo(1, NWAM0772E); 2017/03/02 S21_NA#17714-2 Del
                            line.ordCustUomQty_RL.setErrorInfo(1, NWAM0772E); // 2017/03/02 S21_NA#17714-2 Add
                            isError = true;
                        }
                    }
                    // 2016/04/08 S21_NA#5356 Add End

//                    NWAL1500CommonLogicForLineControl.setChildLine(bizMsg, glblMsg, glblMsg.D, n);
                }
                // 2018/08/28 Mod Start QC#26329
                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_P4)) {
                    if(!ZYPCommonFunc.hasValue(line.rtrnLineStsDescTxt_RL.getValue())){
                        ZYPEZDItemValueSetter.setValue(line.dsOrdLineCatgCd_RL, bizMsg.xxPopPrm_P4);
                        // QC#14436 2016/09/13 Add Start
                        NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(n);

                        String dsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
                        BigDecimal dsCpoLineNum = rmaLineMsg.dsCpoLineNum_RL.getValue();

                        for (int i = n + 1; i < glblMsg.D.getValidCount(); i++) {
                            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue()) //
                                    && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, glblMsg.D.no(i).dsCpoLineNum_RL.getValue()) == 0) {
                                ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).dsOrdLineCatgCd_RL, rmaLineMsg.dsOrdLineCatgCd_RL);
                            } else {
                                break;
                            }
                        }
                    } else {
                        String rmaLineStsNm = line.rtrnLineStsDescTxt_RL.getValue();
                        if (!LINE_STS_NM_CLOSED.equals(rmaLineStsNm)) {
                            ZYPEZDItemValueSetter.setValue(line.dsOrdLineCatgCd_RL, bizMsg.xxPopPrm_P4);
                            // QC#14436 2016/09/13 Add Start
                            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(n);

                            String dsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
                            BigDecimal dsCpoLineNum = rmaLineMsg.dsCpoLineNum_RL.getValue();

                            for (int i = n + 1; i < glblMsg.D.getValidCount(); i++) {
                                if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue()) //
                                        && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, glblMsg.D.no(i).dsCpoLineNum_RL.getValue()) == 0) {
                                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).dsOrdLineCatgCd_RL, rmaLineMsg.dsOrdLineCatgCd_RL);
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                    // QC#14436 2016/09/13 Add End
                }
                int slctConfIndex = NWAL1500CommonLogicForLineControl.getConfigIndex(glblMsg.C, line.dsOrdPosnNum_RL.getValue());
                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_P8)) {
                    if(!ZYPCommonFunc.hasValue(line.rtrnLineStsDescTxt_RL.getValue())){
                        ZYPEZDItemValueSetter.setValue(line.rtrnRsnCd_RL, bizMsg.xxPopPrm_P8);
                        NWAL1500CommonLogicForLineConfig.callDefWhApi(bizMsg, glblMsg, slctConfIndex, n, line.mdseCd_RL.getValue()); // 2018/01/25 S21_NA#19808 Add
                    } else {
                        String rmaLineStsNm = line.rtrnLineStsDescTxt_RL.getValue();
                        if (!LINE_STS_NM_CLOSED.equals(rmaLineStsNm)) {
                            ZYPEZDItemValueSetter.setValue(line.rtrnRsnCd_RL, bizMsg.xxPopPrm_P8);
                            // QC#55162 2019/12/16 Add Start
                            // NWAL1500CommonLogicForLineConfig.callDefWhApi(bizMsg, glblMsg, slctConfIndex, n, line.mdseCd_RL.getValue()); // 2018/01/25 S21_NA#19808 Add
                            if (editable) {
                                NWAL1500CommonLogicForLineConfig.callDefWhApi(bizMsg, glblMsg, slctConfIndex, n, line.mdseCd_RL.getValue());
                            }
                            // QC#55162 2019/12/16 Add End
                        }
                    }
                }
                // 2018/08/28 Mod End QC#26329
                String rtlWhCd = bizMsg.xxPopPrm_P6.getValue();
                // if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                if (ZYPCommonFunc.hasValue(rtlWhCd) && editable) { // 2018/08/03 S21_NA#27040 Add Condition
                    ZYPEZDItemValueSetter.setValue(line.rtlWhCd_RL, rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(line.rtlWhNm_RL, NWAL1500CommonLogicForLineConfig.getWhNm(bizMsg, rtlWhCd));

                    // 2017/11/17 S21_NA#22252 Del Start
                    //String mdseCd = line.mdseCd_RL.getValue();
                    //if (ZYPCommonFunc.hasValue(mdseCd)) {
                    //    // Mod Start 2017/01/31 QC#17186
                    ////    NLZC215001PMsg defSubWhApiPMsg = NWAL1500CommonLogicForLineConfig.callDefSubWhApi(bizMsg, mdseCd, rtlWhCd);
                    //    NLZC215001PMsg defSubWhApiPMsg = NWAL1500CommonLogicForLineConfig.callDefSubWhApi(bizMsg, mdseCd, rtlWhCd, line.svcMachMstrPk_RL.getValue());
                    //    // Mod End 2017/01/31 QC#17186
                    //    if (defSubWhApiPMsg != null) {
                    //        String rtlSwhCd = defSubWhApiPMsg.destRtlSwhCd.getValue();
                    //        ZYPEZDItemValueSetter.setValue(line.rtlSwhCd_RL, rtlSwhCd);
                    //        ZYPEZDItemValueSetter.setValue(line.rtlSwhNm_RL, NWAL1500CommonLogicForLineConfig.getSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
                    //        ZYPEZDItemValueSetter.setValue(line.thirdPtyDspTpCd_RL, defSubWhApiPMsg.thirdPtyDspTpCd);
                    //    }
                    //}
                    // 2017/11/17 S21_NA#22252 Del End
                }
                // 2017/11/17 S21_NA#22252 Add Start
                String rtlSubWhCd = bizMsg.xxPopPrm_P7.getValue();
                // if (ZYPCommonFunc.hasValue(rtlSubWhCd)) {
                if (ZYPCommonFunc.hasValue(rtlSubWhCd) && editable) { // 2018/08/03 S21_NA#27040 Add Condition
                    ZYPEZDItemValueSetter.setValue(line.rtlSwhCd_RL, rtlSubWhCd);
                    ZYPEZDItemValueSetter.setValue(line.rtlSwhNm_RL, NWAL1500CommonLogicForLineConfig.getSubWhNm(bizMsg, rtlWhCd, rtlSubWhCd));
                    // QC#22371 2017/12/26 Add Start
                    if(!configPool.contains(slctConfIndex)){
                        configPool.add(slctConfIndex);
                    }
                    // QC#22371 2017/12/26 Add End
                }
                // 2017/11/17 S21_NA#22252 Add End
//                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_P8)) {
//                    ZYPEZDItemValueSetter.setValue(line.rtrnRsnCd_RL, bizMsg.xxPopPrm_P8);
//                }
                // 2018/08/28 Mod Start QC#26329
                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_PA)) {
                    if(!ZYPCommonFunc.hasValue(line.rtrnLineStsDescTxt_RL.getValue())){
                        ZYPEZDItemValueSetter.setValue(line.prcCatgNm_RL, bizMsg.xxPopPrm_PA);
                    } else {
                        String rmaLineStsNm = line.rtrnLineStsDescTxt_RL.getValue();
                        if (!LINE_STS_NM_CLOSED.equals(rmaLineStsNm)) {
                            ZYPEZDItemValueSetter.setValue(line.prcCatgNm_RL, bizMsg.xxPopPrm_PA);
                        }
                    }
                }
                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_PB)) {
                    if(!ZYPCommonFunc.hasValue(line.rtrnLineStsDescTxt_RL.getValue())){
                        ZYPEZDItemValueSetter.setValue(line.flPrcListNm_RL, bizMsg.xxPopPrm_PB);
                    } else {
                        String rmaLineStsNm = line.rtrnLineStsDescTxt_RL.getValue();
                        if (!LINE_STS_NM_CLOSED.equals(rmaLineStsNm)) {
                            ZYPEZDItemValueSetter.setValue(line.flPrcListNm_RL, bizMsg.xxPopPrm_PB);
                        }
                    }
                }
                if (ZYPCommonFunc.hasValue(bizMsg.prcBaseDt_AW)) {
                    if(!ZYPCommonFunc.hasValue(line.rtrnLineStsDescTxt_RL.getValue())){
                        ZYPEZDItemValueSetter.setValue(line.prcBaseDt_RL, bizMsg.prcBaseDt_AW);
                    } else {
                        String rmaLineStsNm = line.rtrnLineStsDescTxt_RL.getValue();
                        if (!LINE_STS_NM_CLOSED.equals(rmaLineStsNm)) {
                            ZYPEZDItemValueSetter.setValue(line.prcBaseDt_RL, bizMsg.prcBaseDt_AW);
                        }
                    }
                }
                // 2018/08/28 Mod End QC#26329
                // if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_PD)) {
                if (ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_PD) && editable) { // 2018/08/03 S21_NA#27040 Add Condition
                    ZYPEZDItemValueSetter.setValue(line.hddRmvCd_RL, bizMsg.xxPopPrm_PD);
                }
                // S21_NA#9189 Add Start
                // if (ZYPCommonFunc.hasValue(bizMsg.rqstPickUpDt_AW)) {
                if (ZYPCommonFunc.hasValue(bizMsg.rqstPickUpDt_AW) && editable) { // 2018/08/03 S21_NA#27040 Add Condition
                    ZYPEZDItemValueSetter.setValue(line.rqstPickUpDt_RL, bizMsg.rqstPickUpDt_AW);
                }
                // S21_NA#9189 Add End
                // Call Pricing API
                if (!isError) {
                    if (ZYPCommonFunc.hasValue(bizMsg.ordQty_AW) || ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_PA)) {
                        NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, n);
                    }
                }
                // QC#22372 2018/01/10 Add Start
                // Call Pricing API For Floor Price
                if (!isError) {
                    if (ZYPCommonFunc.hasValue(bizMsg.ordQty_AW) || ZYPCommonFunc.hasValue(bizMsg.xxPopPrm_PB)) {
                        NWAL1500CommonLogicForLineConfig.deriveLineFloorPrice(bizMsg, glblMsg, slctConfIndex, n);
                    }
                }
                // QC#22372 2018/01/10 Add End
                // NWAL1500CommonLogicForLineControl.setChildUpdate(glblMsg.D, n); 2016/10/31 S21_NA#15541 Del
                NWAL1500CommonLogicForLineControl.setChildUpdate(bizMsg, glblMsg.D, n); // 2016/10/31 S21_NA#15541 Add
                // QC#22371 2017/12/26 Add Start
                if (configPool.size() > 0) {
                    for (Integer configIndex : configPool) {
                        setDefaultLineCsmpData(bizMsg, glblMsg, glblMsg.C.no(configIndex));

                    }
                }
                // QC#22371 2017/12/26 Add End
            }
        }
        String dplyTab = bizMsg.xxDplyTab.getValue();
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
        }
    }

    private void doProcess_NWAL1500_NWAL1620(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500CommonLogic.clearMeaninglesDetailOfLineConfig(bizMsg, glblMsg);
        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500CommonLogic.clearMeaninglesDetailOfRma(bizMsg, glblMsg);
        }
        if (NWAL1620Constant.COPY_MODE.equals(bizMsg.xxPopPrm_P0.getValue())) {
            if (NWAL1620Constant.HEADER_MODE.equals(bizMsg.xxPopPrm_P1.getValue())) {
                // QC#4078
                // execute search method
                String ordNum = bizMsg.xxSrchNum.getValue();

                doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg);
                if ("E".equals(bizMsg.getMessageKind())) {
                    return;
                }

                // Set New CpoOrderNum in Msg.
                bizMsg.setMessageInfo(NWAM0771I, new String[] {ordNum });

                bizMsg.xxDplyTab.setValue(TAB_HEADER);

                return;
            }
            NWAL1500CommonLogicForCopy.editCopyScrn(bizMsg, glblMsg);

        } else {
            NWAL1500CommonLogicForCopy.editCopyFromScrn(bizMsg, glblMsg);
        }
    }

    private void doProcess_NWAL1500_NMAL6050(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        String scrEventNm = bizMsg.xxScrEventNm.getValue();

        if ("OpenWin_PaymentTerms".equals(scrEventNm) || "OnBlur_DeriveFromPaymentTerms".equals(scrEventNm)) {
            doProcess_NWAL1500Scrn00_OnBlur_DeriveFromPaymentTerms(bizMsg);
        }
    }

    private void doProcess_NWAL1500_NMAL6760(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod

        String scrEventNm = bizMsg.xxScrEventNm.getValue();
        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctLine = bizMsg.xxCellIdx.getValueInt();

        // 2018/01/25 S21_NA#19808 Add Start
        NWAL1500_ACMsg configMsg = null;
        NWAL1500_CCMsg rmaConfigMsg = null;
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            configMsg = bizMsg.A.no(slctLine);
        } else if (TAB_RMA.equals(dplyTab)) {
            rmaConfigMsg = bizMsg.C.no(slctLine);
        }
        // 2018/01/25 S21_NA#19808 Add End
        if (BILL_EVENT_LIST.contains(scrEventNm)) {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(slctLine).billToCustLocAddr_LC, NWAL1500CommonLogicForCustomer.cmbnAddr(bizMsg, SPACE));
            } else if (TAB_RMA.equals(dplyTab)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(slctLine).billToCustLocAddr_RC, NWAL1500CommonLogicForCustomer.cmbnAddr(bizMsg, SPACE));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, NWAL1500CommonLogicForCustomer.cmbnAddr(bizMsg, SPACE));
                ZYPEZDItemValueSetter.setValue(bizMsg.entBillToCustLocAddr, NWAL1500CommonLogicForCustomer.cmbnAddr(bizMsg, ENTER));
                NWAL1500CommonLogicForCategory.deriveDefaultPmtTerm(bizMsg);
                NWAL1500CommonLogicForCustomer.deriveDefaultBillToInfoForDtlFld(bizMsg, glblMsg); // 2018/01/25 S21_NA#19808
            }

        } else if (SHIP_EVENT_LIST.contains(scrEventNm)) {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(slctLine).shipToCustLocAddr_LC, NWAL1500CommonLogicForCustomer.cmbnAddr(bizMsg, SPACE));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(slctLine).dropShipFlg_LC, ZYPConstant.FLG_OFF_N);
                // 2017/12/08 S21_NA#21621 Add Start
                bizMsg.A.no(slctLine).addShipToLocNm_LC.clear();
                // 2017/12/08 S21_NA#21621 Add End
                // 2018/09/20 S21_NA#28199 Add Start
                NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(configMsg, glblMsg);
                // 2018/09/20 S21_NA#28199 Add End
            } else if (TAB_RMA.equals(dplyTab)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(slctLine).shipToCustLocAddr_RC, NWAL1500CommonLogicForCustomer.cmbnAddr(bizMsg, SPACE));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(slctLine).dropShipFlg_RC, ZYPConstant.FLG_OFF_N);
                // 2017/12/08 S21_NA#21621 Add Start
                bizMsg.C.no(slctLine).addShipToLocNm_RC.clear();
                // 2017/12/08 S21_NA#21621 Add End
                // 2018/09/20 S21_NA#28199 Add Start
                NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(rmaConfigMsg, glblMsg);
                // 2018/09/20 S21_NA#28199 Add End
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, NWAL1500CommonLogicForCustomer.cmbnAddr(bizMsg, SPACE));
                ZYPEZDItemValueSetter.setValue(bizMsg.entShipToCustLocAddr, NWAL1500CommonLogicForCustomer.cmbnAddr(bizMsg, ENTER));
                ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
                // 2017/12/07 S21_NA#21621 Add Start
                bizMsg.addShipToLocNm.clear();
                // 2017/12/07 S21_NA#21621 Add End
            }
            NWAL1500CommonLogicForCustomer.cmnProcForDeriveFromShipTo(bizMsg, glblMsg, false); // 2018/01/25 S21_NA#19808 Mod

            // Add Start 2017/02/07 QC#17257
            updateBaseCmptFlg(bizMsg, glblMsg, slctLine); // 2018/01/25 S21_NA#19808 Mod
            // Add End 2017/02/07 QC#17257

        } else if (SOLD_EVENT_LIST.contains(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, NWAL1500CommonLogicForCustomer.cmbnAddr(bizMsg, SPACE));
            ZYPEZDItemValueSetter.setValue(bizMsg.entSoldToCustLocAddr, NWAL1500CommonLogicForCustomer.cmbnAddr(bizMsg, ENTER));
            NWAL1500CommonLogicForCustomer.cmnProcForDeriveFromSoldTo(bizMsg, glblMsg, false); // 2018/01/25 S21_NA#19808 Mod
        }

        // 2018/01/25 S21_NA#19808 Add Start
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(configMsg, glblMsg);
            // 2018/09/20 S21_NA#28199 Add Start
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
            // 2018/09/20 S21_NA#28199 Add End
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(rmaConfigMsg, glblMsg);
            // 2018/09/20 S21_NA#28199 Add Start
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
            // 2018/09/20 S21_NA#28199 Add End
        }
        // 2018/01/25 S21_NA#19808 Add End
    }

    private void doProcess_NWAL1500_NMAL6800(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        doProcess_NWAL1500Scrn00_OnBlur_DeriveFromItem(bizMsg, glblMsg);
    }

    private void doProcess_NWAL1500_NLCL1000(NWAL1500CMsg bizMsg) {

        for (int i = 0; i < bizMsg.M.getValidCount(); i++) {
            NWAL1500_MCMsg popupMsg = bizMsg.M.no(i);
            // 2016/03/07 S21_NA#5000#86 Mod Start
            // int slctLineNum = Integer.parseInt(popupMsg.xxScrItem20Txt_P1.getValue());
            int slctLineNum = -1;
            String popXxLineNum = popupMsg.xxScrItem20Txt_P1.getValue();
            for (slctLineNum = 0; slctLineNum < bizMsg.B.getValidCount(); slctLineNum++) {
                String xxLineNum = bizMsg.B.no(slctLineNum).xxLineNum_LL.getValue();
                if (popXxLineNum.equals(xxLineNum)) {
                    break;
                }
            }
            if (slctLineNum >= bizMsg.B.getValidCount()) {
                continue;
            }
            // 2016/03/07 S21_NA#5000#86 Mod End
            String rtlWhCd = popupMsg.rtlWhCd_P1.getValue();
            String rtlSwhCd = popupMsg.rtlSwhCd_P1.getValue();

            NWAL1500_BCMsg slctLineMsg = bizMsg.B.no(slctLineNum);
            // 2018/08/24 S21_NA#27202 Mod Start
            //ZYPEZDItemValueSetter.setValue(slctLineMsg.rtlWhCd_LL, rtlWhCd);
            //ZYPEZDItemValueSetter.setValue(slctLineMsg.rtlWhNm_LL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
            //ZYPEZDItemValueSetter.setValue(slctLineMsg.rtlSwhCd_LL, rtlSwhCd);
            //ZYPEZDItemValueSetter.setValue(slctLineMsg.rtlSwhNm_LL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
            //ZYPEZDItemValueSetter.setValue(slctLineMsg.invtyLocCd_LL, popupMsg.invtyLocCd_P1);
            if (ZYPConstant.FLG_OFF_N.equals(slctLineMsg.xxReadOnlyFlg_LL.getValue())) {
                String rtlWhNm = NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd);
                if (S21StringUtil.isEmpty(rtlWhNm)) {
                    rtlWhCd = ZYPCodeDataUtil.getVarCharConstValue(DS_DROP_SHIP_WH_CD, bizMsg.glblCmpyCd.getValue());
                    rtlWhNm = NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd);
                }
                ZYPEZDItemValueSetter.setValue(slctLineMsg.rtlWhCd_LL, rtlWhCd);
                ZYPEZDItemValueSetter.setValue(slctLineMsg.rtlWhNm_LL, rtlWhNm);
                ZYPEZDItemValueSetter.setValue(slctLineMsg.rtlSwhCd_LL, rtlSwhCd);
                ZYPEZDItemValueSetter.setValue(slctLineMsg.rtlSwhNm_LL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
                ZYPEZDItemValueSetter.setValue(slctLineMsg.invtyLocCd_LL, popupMsg.invtyLocCd_P1);
            }
            // 2018/08/24 S21_NA#27202 Mod End

            if (!ZYPCommonFunc.hasValue(slctLineMsg.ordLineStsCd_LL)) {
                ZYPEZDItemValueSetter.setValue(slctLineMsg.sbstMdseCd_LL, popupMsg.mdseCd_PS);
            }
        }
    }

    private void createNewConfigLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg configLineMsg) { // 2018/01/25 S21_NA#19808 Mod

        // Create New Configuration Line.
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustCd_LC, bizMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctCd_LC, bizMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctNm_LC, bizMsg.shipToCustAcctNm);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustLocAddr_LC, bizMsg.xxAllLineAddr_SH);
        ZYPEZDItemValueSetter.setValue(configLineMsg.dropShipFlg_LC, bizMsg.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustCd_LC, bizMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctCd_LC, bizMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctNm_LC, bizMsg.billToCustAcctNm);
        ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustLocAddr_LC, bizMsg.xxAllLineAddr_BT);
        ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustLocCd_LC, bizMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.sellToCustCd_LC, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustAcctNm_LC, bizMsg.soldToCustAcctNm);
        ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustLocAddr_LC, bizMsg.xxAllLineAddr_SE);
        // 2017/12/08 S21_NA#21621 Add Start
        ZYPEZDItemValueSetter.setValue(configLineMsg.addShipToLocNm_LC, bizMsg.addShipToLocNm);
        // 2017/12/08 S21_NA#21621 Add End

        ZYPEZDItemValueSetter.setValue(configLineMsg.dsOrdPosnNum_LC, Integer.toString(glblMsg.A.getValidCount() + 1));

        // Ship To Information from Header.
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToLocNm_LC, bizMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToAddlLocNm_LC, bizMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstLineAddr_LC, bizMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdLineAddr_LC, bizMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToThirdLineAddr_LC, bizMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFrthLineAddr_LC, bizMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstRefCmntTxt_LC, bizMsg.shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdRefCmntTxt_LC, bizMsg.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtyAddr_LC, bizMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToStCd_LC, bizMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToPostCd_LC, bizMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtryCd_LC, bizMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCntyNm_LC, bizMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToProvNm_LC, bizMsg.shipToProvNm);

        configLineMsg.xxSmryLineFlg_L.setValue(ZYPConstant.FLG_OFF_N);
        // Add 2016/03/07 S21_NA#5000#78 Start
        configLineMsg.dsCpoConfigHldFlg_LC.setValue(ZYPConstant.FLG_OFF_N);
        // Add 2016/03/07 S21_NA#5000#78 End

        NWAL1500CommonLogicForSalesCredit.copyHdrSlsCrInfo(bizMsg, configLineMsg);

        NWAL1500CommonLogic.setConfigNewFlg(bizMsg, configLineMsg);

        // S21_NA#8388 ADD
        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.dclnSvcCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(configLineMsg.dclnSvcCd_LC, bizMsg.dclnSvcCd);
        }

        // 2016/08/26 S21_NA#9806 Add Start
        ZYPEZDItemValueSetter.setValue(configLineMsg.csmpContrNum_LC, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(configLineMsg.dlrRefNum_LC, bizMsg.dlrRefNum);

        setCsmpPriceListForConfig(bizMsg, configLineMsg);
        // 2016/08/26 S21_NA#9806 Add End
    }

    private void createNewConfigLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_CSMsg configLineMsg) { // 2018/01/25 S21_NA#19808 Mod

        // Create New Configuration Line.
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustCd_RC, bizMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctCd_RC, bizMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctNm_RC, bizMsg.shipToCustAcctNm);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustLocAddr_RC, bizMsg.xxAllLineAddr_SH);
        ZYPEZDItemValueSetter.setValue(configLineMsg.dropShipFlg_RC, bizMsg.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustCd_RC, bizMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctCd_RC, bizMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctNm_RC, bizMsg.billToCustAcctNm);
        ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustLocAddr_RC, bizMsg.xxAllLineAddr_BT);
        ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustLocCd_RC, bizMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.sellToCustCd_RC, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustAcctNm_RC, bizMsg.soldToCustAcctNm);
        ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustLocAddr_RC, bizMsg.xxAllLineAddr_SE);
        // 2017/12/08 S21_NA#21621 Add Start
        ZYPEZDItemValueSetter.setValue(configLineMsg.addShipToLocNm_RC, bizMsg.addShipToLocNm);
        // 2017/12/08 S21_NA#21621 Add End

        ZYPEZDItemValueSetter.setValue(configLineMsg.dsOrdPosnNum_RC, Integer.toString(glblMsg.C.getValidCount() + 1));

        // // Ship To Information from Header.
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToLocNm_RC, bizMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToAddlLocNm_RC, bizMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstLineAddr_RC, bizMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdLineAddr_RC, bizMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToThirdLineAddr_RC, bizMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFrthLineAddr_RC, bizMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstRefCmntTxt_RC, bizMsg.shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdRefCmntTxt_RC, bizMsg.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtyAddr_RC, bizMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToStCd_RC, bizMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToPostCd_RC, bizMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtryCd_RC, bizMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCntyNm_RC, bizMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(configLineMsg.shipToProvNm_RC, bizMsg.shipToProvNm);

        configLineMsg.xxSmryLineFlg_R.setValue(ZYPConstant.FLG_OFF_N);

        NWAL1500CommonLogicForSalesCredit.copyHdrSlsCrInfo(bizMsg, configLineMsg);

        NWAL1500CommonLogic.setRmaConfigNewFlg(bizMsg, configLineMsg);

        // 2016/08/26 S21_NA#9806 Add Start
        ZYPEZDItemValueSetter.setValue(configLineMsg.csmpContrNum_RC, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(configLineMsg.dlrRefNum_RC, bizMsg.dlrRefNum);

        setCsmpPriceListForConfig(bizMsg, configLineMsg);
        // 2016/08/26 S21_NA#9806 Add End
    }

    private void doProcess_NWAL1500Scrn00_OpenWin_LineCancel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int page = NWAL1500CommonLogicForLineCancel.prepareOpenCancelPopup(bizMsg, glblMsg);
        // 2018/05/16 S21_NA#25144 add start
        if (page > 0) {
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, page);
            } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
                NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, page);
            }
        }
        // 2018/05/16 S21_NA#25144 add end
    }

    private void doProcess_NWAL1500_NWAL2090(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg);
    }

    private void doProcess_NWAL1500Scrn00_OpenWin_PriceChanges(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        List<Integer> selectedRows = new ArrayList<Integer>();
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
            selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
        }

        // QC#22965 2018/04/11 Del Start
        //if (selectedRows.size() == 0) {
        //    bizMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_LINE });
        //    return;
        //}
        // QC#22965 2018/04/11 Del Start

        if (selectedRows.size() > 1) {
            bizMsg.setMessageInfo(NWAM0683E);
            return;
        }

        if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
            if (selectedRows.size() != 0) { // QC#22965 2018/04/11 Add
                if (NWAL1500CommonLogicForPriceChanges.isSetComponent(bizMsg.D, selectedRows.get(0))) {
                    bizMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_PRC_CHG });
                    return;
                }
            }
        }
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            // QC#22965 2018/04/11 Mod Start
            //  NWAL1500CommonLogicForPriceChanges.setParameters(bizMsg, glblMsg, bizMsg.B.no(selectedRows.get(0)), glblMsg.I);
            if (selectedRows.size() == 0) {
                NWAL1500CommonLogicForPriceChanges.setParametersForHeader(bizMsg, glblMsg, glblMsg.I, CONFIG_CATG.OUTBOUND);
            } else {
                NWAL1500CommonLogicForPriceChanges.setParameters(bizMsg, glblMsg, bizMsg.B.no(selectedRows.get(0)), glblMsg.I);
            }
            // QC#22965 2018/04/11 Mod End
        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
            // QC#22965 2018/04/11 Mod End
            // NWAL1500CommonLogicForPriceChanges.setParameters(bizMsg, glblMsg, bizMsg.D.no(selectedRows.get(0)), glblMsg.I);
            if (selectedRows.size() == 0) {
                NWAL1500CommonLogicForPriceChanges.setParametersForHeader(bizMsg, glblMsg, glblMsg.I, CONFIG_CATG.INBOUND);
            } else {
                NWAL1500CommonLogicForPriceChanges.setParameters(bizMsg, glblMsg, bizMsg.D.no(selectedRows.get(0)), glblMsg.I);
            }
            // QC#22965 2018/04/11 Mod Start
        }
    }

    private void doProcess_NWAL1500_NWAL1660(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // QC#50776 2019/06/10 Mod Start
        if (NWAL1660_MODE_REFERENCE.equals(bizMsg.xxModeCd_N.getValue())) {
            return;
        }
        // QC#50776 2019/06/10 Mod End
        // 2018/01/25 S21_NA#19808 Mod bizMsg.B, D => glblMsg.B, D
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
            // QC#22965 2018/04/11 Mod Start
           //if (!ORD_LINE_STS.CANCELLED.equals(glblMsg.B.no(selectedRows.get(0)).ordLineStsCd_LL.getValue())) { // QC#1620
            //   NWAL1500CommonLogicForPriceChanges.setScreenItems(bizMsg, glblMsg, glblMsg.B.no(selectedRows.get(0)), glblMsg.I);
            //    NWAL1500CommonLogic.checkPriceRange(glblMsg.B.no(selectedRows.get(0)), NWAL1500CommonLogic.getBasePriceDataFromBizlMsg(glblMsg.B.no(selectedRows.get(0)), bizMsg, glblMsg));
            //
            //    NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
            //}
            if (selectedRows.size() > 0) {
                if (!ORD_LINE_STS.CANCELLED.equals(glblMsg.B.no(selectedRows.get(0)).ordLineStsCd_LL.getValue())) { // QC#1620
                    NWAL1500CommonLogicForPriceChanges.setScreenItems(bizMsg, glblMsg, glblMsg.B.no(selectedRows.get(0)), glblMsg.I);
                    NWAL1500CommonLogic.checkPriceRange(glblMsg.B.no(selectedRows.get(0)), NWAL1500CommonLogic.getBasePriceDataFromBizlMsg(glblMsg.B.no(selectedRows.get(0)), bizMsg, glblMsg));

                    NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
                }
            } else {
                NWAL1500CommonLogicForPriceChanges.setScreenItemsForHeader(bizMsg, glblMsg, glblMsg.I, CONFIG_CATG.OUTBOUND);
                NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
            }
            // QC#22965 2018/04/11 Mod End
        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);;
            // QC#22965 2018/04/11 Mod Start
            //if (!RTRN_LINE_STS.CANCELLED.equals(glblMsg.D.no(selectedRows.get(0)).ordLineStsCd_RL.getValue())) { // QC#1620
            //    NWAL1500CommonLogicForPriceChanges.setScreenItems(bizMsg, glblMsg, glblMsg.D.no(selectedRows.get(0)), glblMsg.I);
            //    NWAL1500CommonLogic.checkPriceRange(glblMsg.D.no(selectedRows.get(0)), NWAL1500CommonLogic.getBasePriceDataFromBizlMsg(glblMsg.D.no(selectedRows.get(0)), bizMsg, glblMsg));
            //
            //    NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
            //}
            if (selectedRows.size() > 0) {
                if (!RTRN_LINE_STS.CANCELLED.equals(glblMsg.D.no(selectedRows.get(0)).ordLineStsCd_RL.getValue())) { // QC#1620
                    NWAL1500CommonLogicForPriceChanges.setScreenItems(bizMsg, glblMsg, glblMsg.D.no(selectedRows.get(0)), glblMsg.I);
                    NWAL1500CommonLogic.checkPriceRange(glblMsg.D.no(selectedRows.get(0)), NWAL1500CommonLogic.getBasePriceDataFromBizlMsg(glblMsg.D.no(selectedRows.get(0)), bizMsg, glblMsg));

                    NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
                }
            } else {
                NWAL1500CommonLogicForPriceChanges.setScreenItemsForHeader(bizMsg, glblMsg, glblMsg.I, CONFIG_CATG.INBOUND);
                NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
            }
            // QC#22965 2018/04/11 Mod End
        }
    }

    private void doProcess_NWAL1500_NWAL1760(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        String scrEventNm = bizMsg.xxScrEventNm.getValue();
        if ("OpenWin_PriceList".equals(scrEventNm) || "OnBlur_DeriveFromPriceList".equals(scrEventNm)) {
            doProcess_NWAL1500Scrn00_OnBlur_DeriveFromPriceList(bizMsg, glblMsg);
        } else {
            doProcess_NWAL1500Scrn00_OnBlur_DeriveFromFloorPriceList(bizMsg, glblMsg);
        }
    }

    // S21_NA QC#3649 Add Start
    private void doProcess_NWAL1500_NWAL1870(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int slctLineIndex = bizMsg.xxCellIdx.getValueInt();
        NWAL1500_BCMsg lineMsg = bizMsg.B.no(slctLineIndex);
        // 2018/01/25 S21_NA#19808 Add Start
        slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizMsg.B.no(slctLineIndex), glblMsg);
        // 2018/01/25 S21_NA#19808 Add End
        int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, slctLineIndex);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N); // 2019/04/15 S21_NA#31184 Add Start

        String executeSupersede = bizMsg.xxPopPrm_P0.getValue();
        if (ZYPConstant.FLG_ON_Y.equals(executeSupersede)) {
            ZYPEZDItemValueSetter.setValue(lineMsg.origMdseCd_LL, lineMsg.mdseCd_LL);
            // 2018/04/17 S21_NA#25230 Mod Start
//            ZYPEZDItemValueSetter.setValue(lineMsg.mdseCd_LL, bizMsg.mdseCd_SS);
            ZYPEZDItemValueSetter.setValue(lineMsg.mdseCd_LL,
                    NWAL1500CommonLogic.getOrdTakeMdseCd(getGlobalCompanyCode(), bizMsg.mdseCd_SS.getValue()));
            // 2018/04/17 S21_NA#25230 Mod End
            doProcess_NWAL1500Scrn00_OnBlur_DeriveFromItem(bizMsg, glblMsg);

        } else {
            // Call Pricing API
            NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);

            String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
            BigDecimal dsCpoLineNum = lineMsg.dsCpoLineNum_LL.getValue();
            BigDecimal dsCpoLineSubNum = lineMsg.dsCpoLineSubNum_LL.getValue();
            // QC743
            // NWAL1500CommonLogicForLineControl.storeLineByConfig(glblMsg.J, bizMsg.B, dsOrdPosnNum);

            // for set merchandise
            // QC743
            // int lineIndex = NWAL1500CommonLogicForLineControl.getLineRow(glblMsg.J, dsOrdPosnNum, dsCpoLineNum, dsCpoLineSubNum);
            // NWAL1500CommonLogicForLineControl.setChildLine(bizMsg, glblMsg, glblMsg.J, lineIndex);
            // NWAL1500CommonLogicForLineControl.prepareLineS2C(bizMsg.B, glblMsg.J, dsOrdPosnNum);
            int lineIndex = NWAL1500CommonLogicForLineControl.getLineRow(glblMsg.B, dsOrdPosnNum, dsCpoLineNum, dsCpoLineSubNum); // 2018/01/25 S21_NA#19808 Mod
            NWAL1500CommonLogicForLineControl.setChildLine(bizMsg, glblMsg, glblMsg.B, lineIndex); // 2018/01/25 S21_NA#19808 Mod

            // Out bound Y N N
              // 2016/11/08 S21_NA#7749 Del
//            if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(slctConfIndex).configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, true, false, false)) {
                // 2016/07/11 S21_NA#7821 Mod Start
//                for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//                    if (S21StringUtil.isEquals(dsOrdPosnNum, bizMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
//                        NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, slctConfIndex, i);
//                    }
//                }
            NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex)); // 2018/01/25 S21_NA#19808 Mod
                // 2016/07/11 S21_NA#7821 Mod End
              // 2016/11/08 S21_NA#7749 Del
//            }
            NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, slctConfIndex); // 2018/01/25 S21_NA#19808 Mod

            // Set Supersede Lock Flag
            ZYPEZDItemValueSetter.setValue(lineMsg.supdLockFlg_LL, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(slctLineIndex).supdLockFlg_LL, ZYPConstant.FLG_ON_Y);

            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
        }
    }
    // S21_NA QC#3649 Add End

    private void doProcess_NWAL1500Scrn00_OpenWin_MeterEntry(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

        if (selectedRows.size() == 0) {
            bizMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_COMP_LINE });
            return;
        }

        if (selectedRows.size() > 1) {
            bizMsg.setMessageInfo(NWAM0666E, new String[] {MSG_PARAM_COMP_LINE });
            return;
        }
        NWAL1500_BCMsg bMsg = bizMsg.B.no(selectedRows.get(0));
        if (!S21StringUtil.isEquals(bMsg.baseCmptFlg_LL.getValue(), ZYPConstant.FLG_ON_Y)) {
            bMsg.xxChkBox_LL.setErrorInfo(1, NWAM0667E, new String[] {MSG_PARAM_MAIN_UNIT });
            return;
        }
        if (!NWAL1500CommonLogicForMeterEntry.isProceccorbleLine(bizMsg, bMsg.dsOrdLineCatgCd_LL.getValue())) {
            bMsg.xxChkBox_LL.setErrorInfo(1, NWAM0682E);
            return;
        }
    }

    private void doProcess_NWAL1500Scrn00_OpenWin_MassUpdate(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2019/07/05 S21_NA#51151 Add Start
        String xxDplyTab = bizMsg.xxDplyTab.getValue();
        if (NWAL1500CommonLogicForPagination.isTabLineConfig(xxDplyTab)) {
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
        } else if (NWAL1500CommonLogicForPagination.isTabRma(xxDplyTab)) {
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
        }
        // 2019/07/05 S21_NA#51151 Add End
        // 2019/07/11 S21_NA#51287 Mod Start
        //List<Integer> checkListItemConfig = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
        //List<Integer> checkListItemLine = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
        //List<Integer> checkListRMAConfig = ZYPTableUtil.getSelectedRows(bizMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
        //List<Integer> checkListRMALine = ZYPTableUtil.getSelectedRows(bizMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
        List<Integer> checkListItemConfig = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
        List<Integer> checkListItemLine = ZYPTableUtil.getSelectedRows(glblMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
        List<Integer> checkListRMAConfig = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
        List<Integer> checkListRMALine = ZYPTableUtil.getSelectedRows(glblMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
        // 2019/07/11 S21_NA#51287 Mod End

        // 2019/07/05 S21_NA#51151 Add Start
        if (hasErrorForMasUpdate(bizMsg, glblMsg)) {
            return;
        }
        // 2019/07/05 S21_NA#51151 Add End
        if (checkListItemConfig.size() > 0) {
            // 2019/07/11 S21_NA#51287 Mod Start
            //for (int i : checkListItemConfig) {
            //    BigDecimal count = NWAL1500Query.getInstance().cntBackOrderForItemConfig(bizMsg, bizMsg.A.no(i));
            //    if (count.compareTo(BigDecimal.ZERO) != 0) {
            //        bizMsg.setMessageInfo(NWAM0689E);
            //        return;
            //    }
            //}
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P0, NWAL1610Constant.CONFIG_MODE);
            // 2019/07/11 S21_NA#51287 Mod End
        } else if (checkListItemLine.size() > 0) {
            // 2019/07/11 S21_NA#51287 Mod Start
            //for (int i : checkListItemConfig) {
            //    BigDecimal count = NWAL1500Query.getInstance().cntBackOrderForItemLine(bizMsg, bizMsg.B.no(i));
            //    if (count.compareTo(BigDecimal.ZERO) != 0) {
            //        bizMsg.setMessageInfo(NWAM0689E);
            //        return;
            //    }
            //}
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P0, NWAL1610Constant.LINE_MODE);
            // 2019/07/11 S21_NA#51287 Mod End
        } else if (checkListRMAConfig.size() > 0) {
            // 2019/07/11 S21_NA#51287 Mod Start
            //for (int i : checkListItemConfig) {
            //    BigDecimal count = NWAL1500Query.getInstance().cntBackOrderForRMAConfig(bizMsg, bizMsg.C.no(i));
            //    if (count.compareTo(BigDecimal.ZERO) != 0) {
            //        bizMsg.setMessageInfo(NWAM0689E);
            //        return;
            //    }
            //}
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P0, NWAL1610Constant.CONFIG_MODE);
        } else if (checkListRMALine.size() > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P0, NWAL1610Constant.RMA_MODE);
        // 2019/07/11 S21_NA#51287 Mod End
         // 2018/08/28 Del Start QC#26329
//        } else {
//            for (int i : checkListRMALine) {
//                BigDecimal count = NWAL1500Query.getInstance().cntBackOrderForRMALine(bizMsg, bizMsg.D.no(i));
//                if (count.compareTo(BigDecimal.ZERO) != 0) {
//                    bizMsg.setMessageInfo(NWAM0689E);
//                    return;
//                }
//            }
         // 2018/08/28 Del End QC#26329
        }
        String dplyTab = bizMsg.xxDplyTab.getValue();
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
        }
    }

    private void doProcess_NWAL1500Scrn00_OpenWin_ChangeOrderModification(NWAL1500CMsg bizMsg) {

        NWAL1500CommonLogic.checkForChangeOrderModification(bizMsg);
        return;
    }

    // 2016/02/26 S21_NA#966 Add Start-----------
    private void doProcess_NWAL1500Scrn00_AttachMent(NWAL1500CMsg bizMsg) {
       // NWAL1500CommonLogic.checkAttachMentOrderNum(bizMsg); // S21_NA#7337 Del
    }
    // 2016/02/26 S21_NA#966 Add End-----------

    private void doProcess_NWAL1500Scrn00_OpenWin_SpecialInstruction(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2018/07/27 S21_NA#14307 Mod Start
        //// 2018/07/11 S21_NA#26713 Add Start
        //String funcCategoryId = NWAL1500Query.getInstance().getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        //ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P2, funcCategoryId);
        //// 2018/07/11 S21_NA#26713 Add End
        //String transactionType = NWAL1500CommonLogicForSpecialInstruction.getTrxRuleTp(bizMsg);
        //ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P3, transactionType);
        NWAL1500CommonLogicForSpecialInstruction.initProcSpecialInstructionForClear(bizMsg);

        NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
        String dplyTab = bizMsg.xxDplyTab.getValue();

        NWAL1500CMsg paramBizMsg = null;
        NWAL1500_ACMsg configMsg = null;
        NWAL1500_CCMsg rmaConfigMsg = null;
        if (TAB_LINE_CONFIG.equals(dplyTab)) { // Line Config
            if (glblMsg.A.getValidCount() == 1) {
                configMsg = bizMsg.A.no(0);
                paramBizMsg = bizMsg;
            } else {
                List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
                if (selectedRows.size() > 0) {
                    configMsg = new NWAL1500_ACMsg();
                    NWAL1500_ASMsg configGlblMsg = glblMsg.A.no(selectedRows.get(0));
                    ZYPEZDItemValueSetter.setValue(configMsg.sellToCustCd_LC, configGlblMsg.sellToCustCd_LC);
                    ZYPEZDItemValueSetter.setValue(configMsg.billToCustCd_LC, configGlblMsg.billToCustCd_LC);
                    ZYPEZDItemValueSetter.setValue(configMsg.shipToCustCd_LC, configGlblMsg.shipToCustCd_LC);
                    paramBizMsg = bizMsg;
                }
            }
        } else if (TAB_RMA.equals(dplyTab)) { // RMA
            if (glblMsg.C.getValidCount() == 1) {
                rmaConfigMsg = bizMsg.C.no(0);
                paramBizMsg = bizMsg;
            } else {
                List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
                if (selectedRows.size() > 0) {
                    rmaConfigMsg = new NWAL1500_CCMsg();
                    NWAL1500_CSMsg rmaGlblMsg = glblMsg.C.no(selectedRows.get(0));
                    ZYPEZDItemValueSetter.setValue(rmaConfigMsg.sellToCustCd_RC, rmaGlblMsg.sellToCustCd_RC);
                    ZYPEZDItemValueSetter.setValue(rmaConfigMsg.billToCustCd_RC, rmaGlblMsg.billToCustCd_RC);
                    ZYPEZDItemValueSetter.setValue(rmaConfigMsg.shipToCustCd_RC, rmaGlblMsg.shipToCustCd_RC);
                    paramBizMsg = bizMsg;
                }
            }
        } else { // Header
            paramBizMsg = bizMsg;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        NWAL1500CommonLogicForSpecialInstruction.cmnProcForSpecialInstruction(paramBizMsg, configMsg, rmaConfigMsg);
        // 2018/07/27 S21_NA#14307 Mod End
    }

    private void doProcess_NWAL1500Scrn00_OpenWin_ShipToChange(NWAL1500CMsg bizMsg) {

        NWAL1500CommonLogicForCustomer.checkExistCustomer(bizMsg);
    }

    private void doProcess_NWAL1500Scrn00_OpenWin_SalesCredit(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // NWAL1500CommonLogicForSalesCredit.checkAndSetSalesCredit(bizMsg, glblMsg);

        // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
        String dplyTab = bizMsg.xxDplyTab.getValue();
        List<Integer> checkList = new ArrayList<Integer>();
        bizMsg.xxComnScrColValTxt_P2.clear();

        if (NWAL1500CommonLogicForPagination.isTabLineConfig(dplyTab)) {
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            checkList = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
        } else if (NWAL1500CommonLogicForPagination.isTabRma(dplyTab)) {
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            checkList = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
        } else {
            return;
        }

        if (checkList.size() == 0) {
            bizMsg.setMessageInfo(NWAM0667E, new String[] {"Config Line" });
            return;
        }

        StringBuilder dsOrdPosnNumTxt = null;
        for (int index : checkList) {
            String dsOrdPosnNum = "";
            if (NWAL1500CommonLogicForPagination.isTabLineConfig(dplyTab)) {
                NWAL1500_ASMsg configMsg = glblMsg.A.no(index);
                dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
            } else if (NWAL1500CommonLogicForPagination.isTabRma(dplyTab)) {
                NWAL1500_CSMsg configMsg = glblMsg.C.no(index);
                dsOrdPosnNum = configMsg.dsOrdPosnNum_RC.getValue();
            }
            if (dsOrdPosnNumTxt != null) {
                dsOrdPosnNumTxt.append(NWAL1500Constant.COMMA);
                dsOrdPosnNumTxt.append(dsOrdPosnNum);
            } else {
                dsOrdPosnNumTxt = new StringBuilder();
                dsOrdPosnNumTxt.append(dsOrdPosnNum);
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnScrColValTxt_P2, dsOrdPosnNumTxt.toString());
        // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]
    }

    private void doProcess_NWAL1500Scrn00_OpenWin_ConfigID(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        NWAL1500CommonLogicForConfigId.setParameter(bizMsg, glblMsg);
    }

    // 2016/02/10 S21_NA#3398 Add Start
    private void doProcess_NWAL1500Scrn00_OpenWin_CopyLine(NWAL1500CMsg bizMsg) {
        // QC#24245 2018/06/13 Add Start
        //BigDecimal svcConfigMstrPk = null;
        //
        //NWAL1500_ACMsg lineConfigMsg = null;
        //NWAL1500_CCMsg rmaConfigMsg = null;
        //if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
        //    lineConfigMsg = bizMsg.A.no(bizMsg.xxCellIdx.getValueInt());
        //    svcConfigMstrPk = lineConfigMsg.svcConfigMstrPk_LC.getValue();
        //} else {
        //    rmaConfigMsg = bizMsg.C.no(bizMsg.xxCellIdx.getValueInt());
        //    svcConfigMstrPk = rmaConfigMsg.svcConfigMstrPk_RC.getValue();
        //}
        //SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = new SVC_CONFIG_MSTRTMsg();
        //ZYPEZDItemValueSetter.setValue(svcConfigMstrTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        //ZYPEZDItemValueSetter.setValue(svcConfigMstrTMsg.svcConfigMstrPk, svcConfigMstrPk);
        //
        //SVC_CONFIG_MSTRTMsg svcConfigMstrTMsgRslt = (SVC_CONFIG_MSTRTMsg) S21FastTBLAccessor.findByKey(svcConfigMstrTMsg);
        //if (null != svcConfigMstrTMsgRslt) {
        //    if (null != lineConfigMsg) {
        //        lineConfigMsg.xxChkBox_LC.setErrorInfo(1, NWAM0758E);
        //    } else {
        //        rmaConfigMsg.xxChkBox_RC.setErrorInfo(1, NWAM0758E);
        //    }
        //}
        // QC#24245 2018/06/13 Add End
    }
    // 2016/02/10 S21_NA#3398 Add End

    /**
     * doProcess_NWAL1500Scrn00_OnChange_OrderEntryAction
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     */
    private void doProcess_NWAL1500Scrn00_OnChange_OrderEntryAction(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2019/05/24 QC#50043 Add Start
        NWAL1500CommonLogic.outputEntryActBizProctLog(bizMsg);
        // 2019/05/24 QC#50043 Add End

        String ordEntryActCd = bizMsg.ordEntryActCd.getValue();
        // 2018/10/24 Add Start QC#28866
        String screenAplID = bizMsg.getScreenAplID();
        NWAL1500CommonLogic.outputordEntryActLog(screenAplID, ordEntryActCd);
        // 2018/10/24 Add End QC#28866
        if (ORD_ENTRY_ACT.CANCEL.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_Order_Cancel(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.ORDER_COPY.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_OpenWin_Copy(bizMsg);

        } else if (ORD_ENTRY_ACT.HOLDS.equals(ordEntryActCd) || ORD_ENTRY_ACT.HOLDS_2.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_OpenWin_Holds(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.CHANGE_ORDER_MODIFICATION.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_OpenWin_ChangeOrderModification(bizMsg);

        } else if (ORD_ENTRY_ACT.ATTACHMENT.equals(ordEntryActCd)) {
            // 2016/02/26 S21_NA#966 Add Start-----------
            doProcess_NWAL1500Scrn00_AttachMent(bizMsg);
            // 2016/02/26 S21_NA#966 Add End-------------

        } else if (ORD_ENTRY_ACT.PRICING.equals(ordEntryActCd) || ORD_ENTRY_ACT.PRICING_2.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_Calculation_Order_Amount(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.DELIVERY_OR_INVOICE.equals(ordEntryActCd) || ORD_ENTRY_ACT.DELIVERY_OR_INVOICE_2.equals(ordEntryActCd)) {
            return;

        // 2018/07/27 S21_NA#14307 Add Start
        // } else if (ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS.equals(ordEntryActCd)) {
        } else if (ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS.equals(ordEntryActCd) || ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS_2.equals(ordEntryActCd)) {
        // 2018/07/27 S21_NA#14307 Add End
            doProcess_NWAL1500Scrn00_OpenWin_SpecialInstruction(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.VIEW_WORKFLOW.equals(ordEntryActCd)) {
            return;
        } else if (ORD_ENTRY_ACT.EDI_ATTRIBUTES.equals(ordEntryActCd) || ORD_ENTRY_ACT.EDI_ATTRIBUTES_2.equals(ordEntryActCd)) {
            // 2018/07/03 S21_NA#26908 Mod Start
//            // TODO
//            return;
            doProcess_NWAL2260Scrn00_OpenWin_ImptAttr(bizMsg, glblMsg);
            // 2018/07/03 S21_NA#26908 Mod End

        } else if (ORD_ENTRY_ACT.SALES_CREDIT.equals(ordEntryActCd) || ORD_ENTRY_ACT.REP_SALES_CREDIT.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_OpenWin_SalesCredit(bizMsg, glblMsg);

        // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
        } else if (ORD_ENTRY_ACT.VIEW_ALL_SALES_CREDIT.equals(ordEntryActCd) || ORD_ENTRY_ACT.VIEW_ALL_SALES_CREDIT_2.equals(ordEntryActCd)) {
            return;
         // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]

        // 2018/07/20 S21_NA#26188 Mod Start
        } else if (ORD_ENTRY_ACT.D_I_S_C_LINE_UPDATE.equals(ordEntryActCd) || ORD_ENTRY_ACT.D_I_S_C_MASS_APPLY.equals(ordEntryActCd)) {
        // 2018/07/20 S21_NA#26188 Mod End
            doProcess_NWAL1500Scrn00_OpenWin_DeliveryInfo(bizMsg, glblMsg); // 2016/03/03 S21_NA#5000 Mod

        } else if (ORD_ENTRY_ACT.PROFITABILITY_QA.equals(ordEntryActCd) || ORD_ENTRY_ACT.PROFITABILITY_QA_2.equals(ordEntryActCd)) {
            return;

        } else if (ORD_ENTRY_ACT.DATA_INTEGRITY_DI.equals(ordEntryActCd) || ORD_ENTRY_ACT.DATA_INTEGRITY_DI_2.equals(ordEntryActCd)) {
            // 2016/03/30 S21_NA#5523 Mod Start
            return; // 2016/04/01 S21_NA#5523-2 Alive
            // 2016/04/01 S21_NA#5523-2 Del Start
//            boolean isError = false;
//            boolean isAlreadyPricing = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.xxBtnFlg_PR.getValue());
//            if (!isAlreadyPricing) {
//                isError = true;
//            }
//            boolean isNeedPricing = NWAL1500CommonLogicForSaveSubmit.getInstance().needRePricing(bizMsg, glblMsg);
//            if (isNeedPricing) {
//                isError = true;
//            }
//            if (isError) {
//                bizMsg.ordEntryActCd_AC.setErrorInfo(1, NWAM0673E, new String[] {"Pricing" });
//                bizMsg.setMessageInfo(NWAM0673E, new String[] {"Pricing" });
//                return;
//            }
            // 2016/04/01 S21_NA#5523-2 Del End
            // 2016/03/30 S21_NA#5523 Mod End

        // 2018/07/20 S21_NA#26188 Del Start
        //} else if (ORD_ENTRY_ACT.SERVICE_PRICING.equals(ordEntryActCd) || ORD_ENTRY_ACT.SERVICE_PRICING_2.equals(ordEntryActCd)) {
        //    return;
        // 2018/07/20 S21_NA#26188 Del End
            
        } else if (ORD_ENTRY_ACT.CANCEL_2.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_OpenWin_LineCancel(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.DELETE.equals(ordEntryActCd)) {
            // 2016/04/20 S21_NA#5605 Add Start
            boolean procRslt = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.xxPopPrm_PJ.getValue());
            boolean isHderSaved = NWAL1500CommonLogicForSaveSubmit.isHdrSaved(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue());
            if (procRslt && isHderSaved) {
                String xxDplyTab = bizMsg.xxDplyTab.getValue();
                String ordEntryAct = bizMsg.ordEntryActCd.getValue();
                // 2016/05/18 S21_NA#8144-3 Add Start
                boolean isLineConfigTab = S21StringUtil.isEquals(TAB_LINE_CONFIG, xxDplyTab);
                boolean isRmaTab = S21StringUtil.isEquals(TAB_RMA, xxDplyTab);
                List<String> smryFlgList = new ArrayList<String>(0);
                if (isLineConfigTab) {
                    for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                        String smryFlg = null;
                        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxSmryLineFlg_L)) {
                            smryFlg = new String(bizMsg.A.no(i).xxSmryLineFlg_L.getValue());
                        } else {
                            smryFlg = new String("");
                        }
                        smryFlgList.add(smryFlg);
                    }
                } else if (isRmaTab) {
                    for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                        String smryFlg = null;
                        if (ZYPCommonFunc.hasValue(bizMsg.C.no(i).xxSmryLineFlg_R)) {
                            smryFlg = new String(bizMsg.C.no(i).xxSmryLineFlg_R.getValue());
                        } else {
                            smryFlg = new String("");
                        }
                        smryFlgList.add(smryFlg);
                    }
                }
                // 2016/05/18 S21_NA#8144-3 Add End
                //TODO doProcess_NWAL1500Scrn00_Order_Search(bizMsg, glblMsg);
                bizMsg.xxDplyTab.setValue(xxDplyTab);
                bizMsg.ordEntryActCd.setValue(ordEntryAct);
                // 2016/05/18 S21_NA#8144-3 Add Start
                int n = 0;
                for (String smryFlg : smryFlgList) {
                    if (isLineConfigTab && n >= bizMsg.A.getValidCount()) {
                        break;
                    } else if (isRmaTab && n >= bizMsg.C.getValidCount()) {
                        break;
                    }
                    if ("".equals(smryFlg)) {
                        smryFlg = null;
                    }
                    if (isLineConfigTab) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(n).xxSmryLineFlg_L, smryFlg);
                    } else if (isRmaTab) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(n).xxSmryLineFlg_R, smryFlg);
                    }
                    n++;
                }
                // 2016/05/18 S21_NA#8144-3 Add End

                // 2016/07/11 S21_NA#7821 Add Start
                if (S21StringUtil.isEquals(TAB_LINE_CONFIG, xxDplyTab)) {
                    if (glblMsg.A.getValidCount() == 0) { // 2018/01/25 S21_NA#19808 Mod
                        // Create New Configuration Line.
                        NWAL1500_ASMsg configLineMsg = glblMsg.A.no(0); // 2018/01/25 S21_NA#19808 Mod
                        createNewConfigLine(bizMsg, glblMsg, configLineMsg); // 2018/01/25 S21_NA#19808 Mod
                        glblMsg.A.setValidCount(1); // 2018/01/25 S21_NA#19808 Mod

                        // Create New Line.
                        NWAL1500_BSMsg lineMsg = glblMsg.B.no(0); // 2018/01/25 S21_NA#19808 Mod
                        lineMsg.dsOrdPosnNum_LL.setValue(configLineMsg.dsOrdPosnNum_LC.getValue());
                        lineMsg.dsCpoLineNum_LL.setValue(1);
                        lineMsg.xxLineNum_LL.setValue(NWAL1500CommonLogic.concatLineNum(lineMsg));
                        lineMsg.prcBaseDt_LL.setValue(bizMsg.slsDt.getValue());
                        lineMsg.rddDt_LL.setValue(bizMsg.addRddDt.getValue()); // S21_NA#5000#80
                        lineMsg.dsOrdLineCatgCd_LL.setValue(bizMsg.dsOrdLineCatgCd_LD.getValue());
                        lineMsg.prcCatgNm_LL.setValue(bizMsg.prcCatgNm.getValue());
                        lineMsg.flPrcListNm_LL.setValue(bizMsg.flPrcListNm.getValue());
                        lineMsg.invQty_LL.setValue(BigDecimal.ZERO);
                        glblMsg.B.setValidCount(1); // 2018/01/25 S21_NA#19808 Mod

                        // 2018/01/25 S21_NA#19808 Add Start
                        NWAL1500CommonLogicForPagination.setPageData(bizMsg, glblMsg, TAB_LINE_CONFIG, 1);
                        // 2018/01/25 S21_NA#19808 Add End
                    }
                } else if (S21StringUtil.isEquals(TAB_RMA, xxDplyTab)) {
                    if (glblMsg.C.getValidCount() == 0) { // 2018/01/25 S21_NA#19808 Mod
                        // Create New Configuration Line.
                        NWAL1500_CSMsg configLineMsg = glblMsg.C.no(0); // 2018/01/25 S21_NA#19808 Mod
                        createNewConfigLine(bizMsg, glblMsg, configLineMsg); // 2018/01/25 S21_NA#19808 Mod
                        glblMsg.C.setValidCount(1); // 2018/01/25 S21_NA#19808 Mod

                        // Create New Line.
                        NWAL1500_DSMsg lineMsg = glblMsg.D.no(0); // 2018/01/25 S21_NA#19808 Mod
                        lineMsg.dsOrdPosnNum_RL.setValue(configLineMsg.dsOrdPosnNum_RC.getValue());
                        lineMsg.dsCpoLineNum_RL.setValue(1);
                        lineMsg.xxLineNum_RL.setValue(NWAL1500CommonLogic.concatLineNum(lineMsg));
                        lineMsg.prcBaseDt_RL.setValue(bizMsg.slsDt.getValue());
                        lineMsg.rqstPickUpDt_RL.setValue(bizMsg.slsDt.getValue());
                        lineMsg.rqstPickUpDt_RL.setValue(bizMsg.addRddDt.getValue());
                        lineMsg.dsOrdLineCatgCd_RL.setValue(bizMsg.dsOrdLineCatgCd_RD.getValue());
                        lineMsg.prcCatgNm_RL.setValue(bizMsg.prcCatgNm.getValue());
                        lineMsg.flPrcListNm_RL.setValue(bizMsg.flPrcListNm.getValue());
                        lineMsg.rtrnQty_RL.setValue(BigDecimal.ZERO);
                        lineMsg.cancQty_RL.setValue(BigDecimal.ZERO);
                        glblMsg.D.setValidCount(1); // 2018/01/25 S21_NA#19808 Mod

                        // 2018/01/25 S21_NA#19808 Add Start
                        NWAL1500CommonLogicForPagination.setPageData(bizMsg, glblMsg, TAB_RMA, 1);
                        // 2018/01/25 S21_NA#19808 Add End
                    }
                }
                // 2016/07/11 S21_NA#7821 Add End
            }
            // S21_NA#8388 ADD START
            String xxDplyTab = bizMsg.xxDplyTab.getValue();
            if (S21StringUtil.isEquals(TAB_LINE_CONFIG, xxDplyTab)) {
                // 2018/01/25 S21_NA#19808 bizMsg.A => glblMsg.A
                if (0 < glblMsg.A.getValidCount()) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dclnSvcCd, NWAL1500CommonLogic.getDclnSvcChkBoxValHdrFromConfig(bizMsg, glblMsg)); // 2018/01/25 S21_NA#19808
                }
            }
            // S21_NA#8388 ADD END
            // 2016/04/20 S21_NA#5605 Add End
            // Add Start 2019/07/17 QC#51700
            if (S21StringUtil.isEquals(TAB_RMA, xxDplyTab)) {
                NWAL1500CommonLogic.clearMeaninglesDetail(bizMsg, glblMsg);
            }
            // Add End 2019/07/17 QC#51700

        } else if (ORD_ENTRY_ACT.ORDER_LINE_COPY.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_OpenWin_CopyLine(bizMsg);

        } else if (ORD_ENTRY_ACT.SUPPLY_AUTO_ADD.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_Auto_AddSupply(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.MASS_UPDATE.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_OpenWin_MassUpdate(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.ALL_COLLAPSE.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_Line_All_Collapsed(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.EXPAND_ALL.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_Line_All_Expand(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.OUTBOUND_CONFIG_SELECT_ALL.equals(ordEntryActCd)) {
            // 2018/01/25 S21_NA#19808
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                    if (!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, glblMsg.A.no(i).xxResFltrFlg_LC.getValue())) {
                        glblMsg.A.no(i).xxChkBox_LC.setValue(ZYPConstant.CHKBOX_ON_Y);
                    }
                }
                for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                    bizMsg.A.no(i).xxChkBox_LC.setValue(ZYPConstant.CHKBOX_ON_Y);
                }
            } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
                for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                    if (!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, glblMsg.C.no(i).xxResFltrFlg_RC.getValue())) {
                        glblMsg.C.no(i).xxChkBox_RC.setValue(ZYPConstant.CHKBOX_ON_Y);
                    }
                }
                for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                    bizMsg.C.no(i).xxChkBox_RC.setValue(ZYPConstant.CHKBOX_ON_Y);
                }
            }
            return;

        } else if (ORD_ENTRY_ACT.LINES_SELECT_ALL.equals(ordEntryActCd)) {
            // 2018/01/25 S21_NA#19808
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    if (!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, glblMsg.B.no(i).xxResFltrFlg_LL.getValue())) {
                        glblMsg.B.no(i).xxChkBox_LL.setValue(ZYPConstant.CHKBOX_ON_Y);
                    }
                }
                for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                    bizMsg.B.no(i).xxChkBox_LL.setValue(ZYPConstant.CHKBOX_ON_Y);
                }
            } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
                for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                    if (!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, glblMsg.D.no(i).xxResFltrFlg_RL.getValue())) {
                        glblMsg.D.no(i).xxChkBox_RL.setValue(ZYPConstant.CHKBOX_ON_Y);
                    }
                }
                for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                    bizMsg.D.no(i).xxChkBox_RL.setValue(ZYPConstant.CHKBOX_ON_Y);
                }
            }
            return;

        } else if (ORD_ENTRY_ACT.OUTBOUND_CONFIG_UNSELECT_ALL.equals(ordEntryActCd)) {
            // 2018/01/25 S21_NA#19808
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                    glblMsg.A.no(i).xxChkBox_LC.clear();
                }
                for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                    bizMsg.A.no(i).xxChkBox_LC.clear();
                }
            } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
                for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                    glblMsg.C.no(i).xxChkBox_RC.clear();
                }
                for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                    bizMsg.C.no(i).xxChkBox_RC.clear();
                }
            }
            return;

        } else if (ORD_ENTRY_ACT.LINES_UNSELECT_ALL.equals(ordEntryActCd)) {
            // 2018/01/25 S21_NA#19808
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    glblMsg.B.no(i).xxChkBox_LL.clear();
                }
                for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                    bizMsg.B.no(i).xxChkBox_LL.clear();
                }
            } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
                for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                    glblMsg.D.no(i).xxChkBox_RL.clear();
                }
                for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                    bizMsg.D.no(i).xxChkBox_RL.clear();
                }
            }
            return;

        } else if (ORD_ENTRY_ACT.RMA_AUTO_ADD.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_Auto_AddRMA(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.BUYOUT.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_OpenWin_Buyout(bizMsg);

        } else if (ORD_ENTRY_ACT.ADDITIONAL_LINE_INFORMATION.equals(ordEntryActCd)) {
            // 2020/02/28 QC#55976 Mod Start
            // doProcess_NWAL1500Scrn00_Line_Add(bizMsg, glblMsg);
            return;
            // 2020/02/28 QC#55976 Mod End

        } else if (ORD_ENTRY_ACT.CONTROL_FIELDS.equals(ordEntryActCd)) {
            return;

        } else if (ORD_ENTRY_ACT.WH_INVENTORY_INQUIRY.equals(ordEntryActCd)) {
            // 2016/07/05 S21_NA#7441 Add Start
            doProcess_NWAL1500Scrn00_OpenWin_StockOverview(bizMsg, glblMsg); // 2018/03/05 S21_NA#19808 Mod
            // 2016/07/05 S21_NA#7441 Add End

        } else if (ORD_ENTRY_ACT.PRICE_CHANGE.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_OpenWin_PriceChanges(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.FINAL_METER.equals(ordEntryActCd)) {
            doProcess_NWAL1500Scrn00_OpenWin_MeterEntry(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.ADD_CONFIG.equals(ordEntryActCd)) { // S21_NA#6306
            doProcess_NWAL1500Scrn00_Line_Config_Add(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.ADD_LINE.equals(ordEntryActCd)) { // S21_NA#6306
            doProcess_NWAL1500Scrn00_Line_Add(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.LINE_FILTER.equals(ordEntryActCd)) {  // 2017/08/07 Sol#249 Add
            doProcess_NWAL1500Scrn00_OpenWin_OrderLineFilter(bizMsg, glblMsg);

        } else if (ORD_ENTRY_ACT.RETURN_AUTHORIZATION.equals(ordEntryActCd)) { // 2018/07/03 QC#26932 Add
            doProcess_NWAL1500Scrn00_OpenWin_ReturnAuthrization(bizMsg, glblMsg);
        }
    }

    // 2016/02/29 QC#1693/1700 Start
    private static void getAllOrdCatgBizCtx(NWAL1500CMsg bizMsg) {
        String resultFlg = NWAL1500Query.getInstance().getOrdCatgBizCtx(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.ORDER_LOG_AVAILABLE, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_LT, ZYPConstant.FLG_ON_Y);
        } else {
            bizMsg.xxDplyCtrlFlg_LT.clear();
        }
        resultFlg = NWAL1500Query.getInstance().getOrdCatgBizCtx(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.OM_END_LEASE_FIN_OPTIONS, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_LD, ZYPConstant.FLG_ON_Y);
        } else {
            bizMsg.xxDplyCtrlFlg_LD.clear();
        }
        // QC#22789 2017/11/28 Add Start
        // 2018/03/20 S21_NA#24840 Mod Start
//        resultFlg = NWAL1500Query.getInstance().getOrdCatgBizCtx(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        resultFlg = NWAL1500Query.getInstance().getOrdCatgBizCtxScdAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        // 2018/03/20 S21_NA#24840 Mod End
        if (ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_EM, ZYPConstant.FLG_ON_Y);
            bizMsg.xxDplyCtrlFlg_LD.clear();
        } else {
            bizMsg.xxDplyCtrlFlg_EM.clear();
        }
        // QC#22789 2017/11/28 Add End
        // 2017/11/02 S21_NA#20146 Add Start
        resultFlg = NWAL1500Query.getInstance().getOrdCatgBizCtx(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.GL_SGMT_DPLY_ORD_TP, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_GS, ZYPConstant.FLG_ON_Y);
            bizMsg.xxDplyCtrlFlg_LD.clear();
        } else {
            bizMsg.xxDplyCtrlFlg_GS.clear();
        }
        // 2017/11/02 S21_NA#20146 Add End
    }
    // 2016/02/29 QC#1693/1700 End
    // 2016/10/13 S21_NA#7700 Add Start
    private static void getOrdEntryCancelAvailable(NWAL1500CMsg bizMsg) {

        BigDecimal count = NWAL1500Query.getInstance().isOrdEntryCancelAvailable(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.ORD_ENTRY_CANCEL_NOT_AVAILABLE, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        if (count.compareTo(BigDecimal.ZERO) == 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_CA, ZYPConstant.FLG_ON_Y);

        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_CA, ZYPConstant.FLG_OFF_N);
        }

    }
    // 2016/10/13 S21_NA#7700 Add End
    // S21_NA#1623 add start
    private void doProcess_NWAL1500Scrn00_CMN_Download(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {

            NWAL1500CommonLogicForDownload.downloadForOutbound(bizMsg, glblMsg);

        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {

            NWAL1500CommonLogicForDownload.downloadForInbound(bizMsg, glblMsg);

        }
    }
    // S21_NA#1623 add end

    // S21_NA#6312
    private void doProcess_NWAL1500Scrn00_OnChange_ConfigAction(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        int ix = bizMsg.xxCellIdx.getValueInt();
        NWAL1500_ACMsg configMsg = bizMsg.A.no(ix);
        NWAL1500CommonLogic.setConfigNewFlg(bizMsg, configMsg);
        if (ZYPConstant.FLG_ON_Y.equals(configMsg.configNewFlg_LC.getValue())) {
            configMsg.svcConfigMstrPk_LC.clear();
        }
        // QC#26415 2018/06/07 Add Start
        configMsg.svcConfigMstrPk_LC.clear();
        // QC#26415 2018/06/07 Add End
        // 2018/01/25 S21_NA#19808 Add Start
        NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(configMsg, glblMsg);
        // 2018/01/25 S21_NA#19808 Add End
    }

    // S21_NA#6312
    private void doProcess_NWAL1500Scrn00_OnChange_RmaConfigAction(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        int ix = bizMsg.xxCellIdx.getValueInt();
        NWAL1500_CCMsg rmaConfigMsg = bizMsg.C.no(ix);
        NWAL1500CommonLogic.setRmaConfigNewFlg(bizMsg, rmaConfigMsg);
        if (ZYPConstant.FLG_ON_Y.equals(rmaConfigMsg.configNewFlg_RC.getValue())) {
            rmaConfigMsg.svcConfigMstrPk_RC.clear();
        }
        // 2018/01/25 S21_NA#19808 Add Start
        NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(rmaConfigMsg, glblMsg);
        // 2018/01/25 S21_NA#19808 Add End
    }

    // S21_NA#8317 Add Start
    private void doProcess_NWAL1500Scrn00_OnChange_LineSource(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod
        // Screen
        int idx = bizMsg.xxCellIdx_BB.getValueInt();

        String dsOrdPosnNum = bizMsg.B.no(idx).dsOrdPosnNum_LL.getValue();
        BigDecimal dsCpoLineNum = bizMsg.B.no(idx).dsCpoLineNum_LL.getValue();
        for (int i = idx + 1; i < bizMsg.B.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, bizMsg.B.no(i).dsOrdPosnNum_LL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, bizMsg.B.no(i).dsCpoLineNum_LL.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ordLineSrcCd_LL, bizMsg.B.no(idx).ordLineSrcCd_LL);
            } else {
                break;
            }
        }

        // 2018/01/25 S21_NA#19808 Add Start
        // Global Message
        int glblIdx = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizMsg.B.no(idx), glblMsg);

        EZDMsg.copy(bizMsg.B.no(idx), null, glblMsg.B.no(glblIdx), null);
        // 2018/01/25 S21_NA#19808 Add End
        dsOrdPosnNum = glblMsg.B.no(glblIdx).dsOrdPosnNum_LL.getValue();
        dsCpoLineNum = glblMsg.B.no(glblIdx).dsCpoLineNum_LL.getValue();
        for (int i = glblIdx + 1; i < glblMsg.B.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(i).dsOrdPosnNum_LL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, glblMsg.B.no(i).dsCpoLineNum_LL.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).ordLineSrcCd_LL, glblMsg.B.no(idx).ordLineSrcCd_LL);
            } else {
                break;
            }
        }
        // 2018/01/25 S21_NA#19808 Add End
    }

    private void doProcess_NWAL1500Scrn00_OnChange_HddRemoval(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 Mod
        // Screen
        int idx = bizMsg.xxCellIdx_BB.getValueInt();

        NWAL1500_DCMsg rmaLineMsg = bizMsg.D.no(idx);

        String dsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
        BigDecimal dsCpoLineNum = rmaLineMsg.dsCpoLineNum_RL.getValue();
        for (int i = idx + 1; i < bizMsg.D.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, bizMsg.D.no(i).dsOrdPosnNum_RL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, bizMsg.D.no(i).dsCpoLineNum_RL.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).hddRmvCd_RL, rmaLineMsg.hddRmvCd_RL);
            } else {
                break;
            }
        }

        // 2018/01/25 S21_NA#19808 Add Start
        // Global
        NWAL1500CommonLogicForPagination.copyRmaLineDataToSMsg(rmaLineMsg, glblMsg);
        int glblIdx = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(rmaLineMsg, glblMsg);
        NWAL1500_DSMsg rmaLineSMsg = glblMsg.D.no(glblIdx);
        // 2018/01/25 S21_NA#19808 Add End

        dsOrdPosnNum = rmaLineSMsg.dsOrdPosnNum_RL.getValue();
        dsCpoLineNum = rmaLineSMsg.dsCpoLineNum_RL.getValue();
        for (int i = glblIdx + 1; i < glblMsg.D.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, glblMsg.D.no(i).dsCpoLineNum_RL.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).hddRmvCd_RL, rmaLineMsg.hddRmvCd_RL);
            } else {
                break;
            }
        }
        // 2018/01/25 S21_NA#19808 Add End
    }
    // S21_NA#8317 Add End

    // 2016/07/05 S21_NA#7441 Add Start
    @SuppressWarnings("unchecked")
    private void doProcess_NWAL1500Scrn00_OpenWin_StockOverview(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/03/05 S21_NA#19808 Mod
        // 2018/03/05 S21_NA#19808 Mod bizMsg.B -> glblMsg.B
        NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

        // 2018/03/05 S21_NA#19808 Add Start
        if (selectedRows.size() == 0 || selectedRows.isEmpty()) {
            bizMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_CMPT_LINE});
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
            return;
        }
        for (int slctLineNum : selectedRows) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineNum);
            String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();
            if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
                lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0781E);
                NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, lineMsg.xxPageNum_LL.getValueInt());
                return;
            }
        }
        // 2018/03/05 S21_NA#19808 Add End

        NWAL1500_BSMsg bMsg;
        String rtlWhNm, rtlSwhNm;
        Map<String, String> rsltMap;
        for (Integer selRow : selectedRows) {
            bMsg = glblMsg.B.no(selRow);
            if (ZYPCommonFunc.hasValue(bMsg.rtlWhCd_LL) && ZYPCommonFunc.hasValue(bMsg.rtlSwhCd_LL)) {
                continue;
            }

            rtlWhNm = bMsg.rtlWhNm_LL.getValue();
            rtlSwhNm = bMsg.rtlSwhNm_LL.getValue();

            S21SsmEZDResult smsResult = NWAL1500QueryForSaveSubmit.getInstance().getRtlWh(bizMsg.glblCmpyCd.getValue(), rtlWhNm, rtlSwhNm, bizMsg.slsDt.getValue(), true);

            if (smsResult.isCodeNormal()) {
                rsltMap = (Map<String, String>) smsResult.getResultObject();

                ZYPEZDItemValueSetter.setValue(bMsg.rtlWhCd_LL, rsltMap.get("RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(bMsg.rtlSwhCd_LL, rsltMap.get("RTL_SWH_CD"));
            }
        }
        // 2018/03/05 S21_NA#19808 Add Start
        // Set Parameter Message
        ZYPTableUtil.clear(bizMsg.M);
        List<Integer> selectedRowsForParam = editCheckListForSet(ZYPTableUtil.getSelectedRows(glblMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y), glblMsg.B);
        int validCnt = 0;
        for (; validCnt < selectedRowsForParam.size() && validCnt < bizMsg.M.length(); validCnt++) {
            int slctLineNum = selectedRowsForParam.get(validCnt);
            NWAL1500_BSMsg slctLineMsg = glblMsg.B.no(slctLineNum);

            // set detailList
            NWAL1500_MCMsg detailMsg = bizMsg.M.no(validCnt);

            // Add Start NA QC#2177
            StringBuilder dispLineNum = new StringBuilder();
            dispLineNum.append(slctLineMsg.dsOrdPosnNum_LL.getValue());
            dispLineNum.append(".");
            dispLineNum.append(slctLineMsg.dsCpoLineNum_LL.getValue());
            // 2016/03/07 S21_NA#5000#86 Add Start
            if (ZYPCommonFunc.hasValue(slctLineMsg.dsCpoLineSubNum_LL)) {
                dispLineNum.append(".");
                dispLineNum.append(slctLineMsg.dsCpoLineSubNum_LL.getValue());
            }
            // 2016/03/07 S21_NA#5000#86 Add End
            // Add End NA QC#2177

            ZYPEZDItemValueSetter.setValue(detailMsg.xxScrItem20Txt_P1, dispLineNum.toString());

            // 2016/07/05 S21_NA#7441 Mod Start
            // 2016/08/23 S21_NA#12024 Mod Start
            List<String> dropShipWHCodeList = getDropShipWHCodeList(bizMsg);
            if ((ZYPCommonFunc.hasValue(slctLineMsg.rtlWhCd_LL) && ZYPCommonFunc.hasValue(slctLineMsg.rtlSwhCd_LL))
                    || (ZYPCommonFunc.hasValue(slctLineMsg.rtlWhCd_LL) && dropShipWHCodeList.contains(slctLineMsg.rtlWhCd_LL.getValue()))) {
                ZYPEZDItemValueSetter.setValue(detailMsg.rtlWhCd_P1, slctLineMsg.rtlWhCd_LL);
                ZYPEZDItemValueSetter.setValue(detailMsg.rtlSwhCd_P1, slctLineMsg.rtlSwhCd_LL);
            } else if (ZYPCommonFunc.hasValue(slctLineMsg.invtyLocCd_LL)) {
                ZYPEZDItemValueSetter.setValue(detailMsg.invtyLocCd_P1, slctLineMsg.invtyLocCd_LL);
            }
            // 2016/07/05 S21_NA#7441 Mod End

            NWAL1500_ASMsg configMsg = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.A, slctLineMsg.dsOrdPosnNum_LL.getValue());
            ZYPEZDItemValueSetter.setValue(detailMsg.shipToCustCd_P1, configMsg.shipToCustCd_LC.getValue());

            ZYPEZDItemValueSetter.setValue(detailMsg.mdseCd_P1, slctLineMsg.mdseCd_LL);
            ZYPEZDItemValueSetter.setValue(detailMsg.ordQty_P1, slctLineMsg.ordQty_LL);
            ZYPEZDItemValueSetter.setValue(detailMsg.rddDt_P1, slctLineMsg.rddDt_LL);
        }
        bizMsg.M.setValidCount(validCnt);

        NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg); // 2018/03/05 S21_NA#19808
        // 2018/03/05 S21_NA#19808 Add End

    }
    // 2016/07/05 S21_NA#7441 Add End

    // S21_NA#8388 ADD START
    private void doProcess_NWAL1500Scrn00_OnChange_DclnSvcCdConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808

        NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg); // 2018/03/16 S21_NA#19808-3 Add
        ZYPEZDItemValueSetter.setValue(bizMsg.dclnSvcCd, NWAL1500CommonLogic.getDclnSvcChkBoxValHdrFromConfig(bizMsg, glblMsg)); // 2018/01/25 S21_NA#19808

    }

    private void doProcess_NWAL1500Scrn00_OnChange_DclnSvcCdHdr(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/03/06 S21_NA#19808 Mod

        // 2018/03/06 S21_NA#19808 bizMsg.A, B -> glblMsg.A, B
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg config = glblMsg.A.no(i);
            // Is all cancel line in the config?
            boolean allCancelLine = true;
            for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(j);
                if (lineMsg.dsOrdPosnNum_LL.getValue().equals(config.dsOrdPosnNum_LC.getValue())) {
                    if (!NWAL1500CommonLogic.isCancelLine(bizMsg, lineMsg)) {
                        allCancelLine = false;
                        break;
                    }
                }
            }
            if (allCancelLine) {
                // all cancel line in the config
                // Skip
                continue;
            }
            if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.dclnSvcCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(config.dclnSvcCd_LC, ZYPConstant.CHKBOX_ON_Y);
            } else {
                config.dclnSvcCd_LC.clear();
            }
            // 2018/03/27 S21_NA#25060 Add Start
            NWAL1500_ACMsg configBizMsg = NWAL1500CommonLogic.getParentConfig(bizMsg.A, config.dsOrdPosnNum_LC.getValue());
            // 2018/04/02 S21_NA#25179 Add Start
            if (configBizMsg == null) {
            	continue;
            }
            // 2018/04/02 S21_NA#25179 Add End
            if (ZYPCommonFunc.hasValue(config.dclnSvcCd_LC)) {
                ZYPEZDItemValueSetter.setValue(configBizMsg.dclnSvcCd_LC, config.dclnSvcCd_LC);
            } else {
                configBizMsg.dclnSvcCd_LC.clear();
            }
            // 2018/03/27 S21_NA#25060 Add End
        }
    }
    // S21_NA#8388 ADD END

    // 2016/08/26 S21_NA#9806 Add Start
    private void setCsmpPriceListForConfig(NWAL1500CMsg bizMsg, EZDMsg configMsg) { // 2018/01/25 S21_NA#19808 Mod

        NWZC157001PMsg prcApiMsg = new NWZC157001PMsg();
        getPricingAPIMsgForCsmpPrcList(prcApiMsg, bizMsg, configMsg);

        Map<String, String> rsltMap = getCsmpPrcList(prcApiMsg, bizMsg);

        String prcCatgCd = rsltMap.get("prcCatgCd");
        String prcCatgNm = rsltMap.get("prcCatgNm");

        if (configMsg instanceof NWAL1500_ACMsg) {
            NWAL1500_ACMsg lineConfigMsg = (NWAL1500_ACMsg) configMsg;
            ZYPEZDItemValueSetter.setValue(lineConfigMsg.csmpPrcListCd_LC, prcCatgCd);
            ZYPEZDItemValueSetter.setValue(lineConfigMsg.prcCatgNm_LC, prcCatgNm);
        } else if (configMsg instanceof NWAL1500_CCMsg) {
            NWAL1500_CCMsg rmaConfigMsg = (NWAL1500_CCMsg) configMsg;
            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.csmpPrcListCd_RC, prcCatgCd);
            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.prcCatgNm_RC, prcCatgNm);
        } else if (configMsg instanceof NWAL1500_ASMsg) {
            NWAL1500_ASMsg lineConfigMsg = (NWAL1500_ASMsg) configMsg;
            ZYPEZDItemValueSetter.setValue(lineConfigMsg.csmpPrcListCd_LC, prcCatgCd);
            ZYPEZDItemValueSetter.setValue(lineConfigMsg.prcCatgNm_LC, prcCatgNm);
        } else if (configMsg instanceof NWAL1500_CSMsg) {
            NWAL1500_CSMsg rmaConfigMsg = (NWAL1500_CSMsg) configMsg;
            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.csmpPrcListCd_RC, prcCatgCd);
            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.prcCatgNm_RC, prcCatgNm);
        }

        return;
    }

    /**
     * <pre>
     * get csmp price category code and price category name
     * @param prcApiMsg Pricing Api Param for csmp pricing list
     * @param bizMsg Business Message
     * @return Map...key: prcCatgCd -> for price category code of CSMP
     * key: prcCatgNm -> for price category name
     * </pre>
     */
    private Map<String, String> getCsmpPrcList(NWZC157001PMsg prcApiMsg, NWAL1500CMsg bizMsg) {

        Map<String, String> rsltMap = new HashMap<String, String>();
        NWZC157001 pricingApi = new NWZC157001();
        pricingApi.execute(prcApiMsg, ONBATCH_TYPE.ONLINE);

        boolean isErrorPrcApi = false;
        if (prcApiMsg.xxMsgIdList.getValidCount() > 0 //
                || prcApiMsg.xxPrcList.getValidCount() < 1 // 
                || prcApiMsg.xxPrcList.getValidCount() > 1) {
            isErrorPrcApi = true;
        }

        String prcCatgCd = "";
        String prcCatgNm = "";
        if (!isErrorPrcApi) {
            prcCatgCd = prcApiMsg.xxPrcList.no(0).prcCatgCd.getValue();
            prcCatgNm = getPrcCatgNm(bizMsg.glblCmpyCd.getValue(), prcApiMsg.xxPrcList.no(0).prcCatgCd.getValue());
        }

        if (ZYPCommonFunc.hasValue(prcCatgCd) //
                && ZYPCommonFunc.hasValue(prcCatgNm)) {
            rsltMap.put("prcCatgCd", prcCatgCd);
            rsltMap.put("prcCatgNm", prcCatgNm);
        }
        return rsltMap;
    }
    private void getPricingAPIMsgForCsmpPrcList(NWZC157001PMsg prcApiMsg, NWAL1500CMsg bizMsg, EZDMsg configMsg) { // 2018/01/25 S21_NA#19808 Mod

        String csmpContrNum = null;
        String dlrRefNum = null;

        if (configMsg instanceof NWAL1500_ACMsg) {
            NWAL1500_ACMsg lineConfigMsg = (NWAL1500_ACMsg) configMsg;
            csmpContrNum = lineConfigMsg.csmpContrNum_LC.getValue();
            dlrRefNum = lineConfigMsg.dlrRefNum_LC.getValue();
        } else if (configMsg instanceof NWAL1500_CCMsg) {
            NWAL1500_CCMsg rmaConfigMsg = (NWAL1500_CCMsg) configMsg;
            csmpContrNum = rmaConfigMsg.csmpContrNum_RC.getValue();
            dlrRefNum = rmaConfigMsg.dlrRefNum_RC.getValue();
        } else if (configMsg instanceof NWAL1500_ASMsg) {
            NWAL1500_ASMsg lineConfigMsg = (NWAL1500_ASMsg) configMsg;
            csmpContrNum = lineConfigMsg.csmpContrNum_LC.getValue();
            dlrRefNum = lineConfigMsg.dlrRefNum_LC.getValue();
        } else if (configMsg instanceof NWAL1500_CSMsg) {
            NWAL1500_CSMsg rmaConfigMsg = (NWAL1500_CSMsg) configMsg;
            csmpContrNum = rmaConfigMsg.csmpContrNum_RC.getValue();
            dlrRefNum = rmaConfigMsg.dlrRefNum_RC.getValue();
        }
        getPrcApiParamForCsmpPrcList(prcApiMsg, bizMsg, csmpContrNum, dlrRefNum);
    }

    private void getPrcApiParamForCsmpPrcList(NWZC157001PMsg prcApiMsg, NWAL1500CMsg bizMsg, String csmpContrNum, String dlrRefNum) {

        ZYPEZDItemValueSetter.setValue(prcApiMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        // 2017/07/07 S21_NA#19266 Mod Start
//        ZYPEZDItemValueSetter.setValue(prcApiMsg.prcBaseDt, bizMsg.ordDt);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.prcBaseDt, bizMsg.slsDt);
        // 2017/07/07 S21_NA#19266 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiMsg.prcCtxTpCd, PRC_CTX_TP.CSMP_CREDIT);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.csmpNum, csmpContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.dlrRefNum, dlrRefNum);

        return;
    }

    private String getPrcCatgNm(String glblCmpyCd, String prcCatgCd) {

        PRC_CATGTMsg prcCatgMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(prcCatgMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCatgMsg.prcCatgCd, prcCatgCd);

        PRC_CATGTMsg prcCatgRsltMsg = (PRC_CATGTMsg) S21CacheTBLAccessor.findByKey(prcCatgMsg);
        if (prcCatgRsltMsg != null) {
            return prcCatgRsltMsg.prcCatgNm.getValue();
        } else {
            return "";
        }
    }

    private void setDefaultLineCsmpData(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ACMsg configLineMsg) { // 2018/01/25 S21_NA#19808 Mod
        NWAL1500_ASMsg configMsg = NWAL1500CommonLogicForPagination.getGlobalConfigMsgFromBizMsg(glblMsg, configLineMsg);

        setDefaultLineCsmpData(bizMsg, glblMsg, configMsg);
    }

    private void setDefaultLineCsmpData(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_CCMsg configLineMsg) { // 2018/01/25 S21_NA#19808 Mod
        NWAL1500_CSMsg configMsg = NWAL1500CommonLogicForPagination.getGlobalConfigMsgFromBizMsg(glblMsg, configLineMsg);

        setDefaultLineCsmpData(bizMsg, glblMsg, configMsg);
    }

    private void setDefaultLineCsmpData(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg configLineMsg) { // 2018/01/25 S21_NA#19808 Mod

        // 2018/01/25 S21_NA#19808 Mod bizMsg.B => glblMsg.B
        String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_LC.getValue();
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            if (!S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                continue;
            }
            // copyCsmpData(configLineMsg, lineMsg);
            copyCsmpData(bizMsg, glblMsg, configLineMsg, lineMsg); // QC#22371 2017/12/26 Mod // 2018/01/25 S21_NA#19808 Mod
        }
    }

    private void setDefaultLineCsmpData(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_CSMsg configLineMsg) { // 2018/01/25 S21_NA#19808 Mod

        // 2018/01/25 S21_NA#19808 Mod glblMsg.D => glblMsg.D
        String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_RC.getValue();
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg lineMsg = glblMsg.D.no(i);
            if (!S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_RL.getValue())) {
                continue;
            }
            // copyCsmpData(configLineMsg, lineMsg);
            copyCsmpData(bizMsg, glblMsg, configLineMsg, lineMsg); // QC#22371 2017/12/26 Mod // 2018/01/25 S21_NA#19808 Mod
        }
    }

    // QC#22371 2017/12/26 Mod Start
//    private void copyCsmpData(NWAL1500_ACMsg configLineMsg, NWAL1500_BCMsg lineMsg) {
//
//        ZYPEZDItemValueSetter.setValue(lineMsg.csmpContrNum_LL, configLineMsg.csmpContrNum_LC);
//        ZYPEZDItemValueSetter.setValue(lineMsg.dlrRefNum_LL, configLineMsg.dlrRefNum_LC);
//        ZYPEZDItemValueSetter.setValue(lineMsg.csmpPrcListCd_LL, configLineMsg.csmpPrcListCd_LC);
//    }

    private void copyCsmpData(NWAL1500CMsg bizMsg, NWAL1500_ACMsg configLineMsg, NWAL1500_BCMsg lineMsg) {

        if (!NWAL1500CommonLogic.isCSMPExcl(bizMsg, lineMsg.rtlSwhCd_LL.getValue())) {
            ZYPEZDItemValueSetter.setValue(lineMsg.csmpContrNum_LL, configLineMsg.csmpContrNum_LC);
            ZYPEZDItemValueSetter.setValue(lineMsg.dlrRefNum_LL, configLineMsg.dlrRefNum_LC);
            ZYPEZDItemValueSetter.setValue(lineMsg.csmpPrcListCd_LL, configLineMsg.csmpPrcListCd_LC);
        } else {
            lineMsg.csmpContrNum_LL.clear();
            lineMsg.dlrRefNum_LL.clear();
            lineMsg.csmpPrcListCd_LL.clear();
        }
    }

    // 2018/01/25 S21_NA#19808 Add Start
    private static void copyCsmpData(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg configLineMsg, NWAL1500_BSMsg lineMsg) { // 2018/01/25 S21_NA#19808 Mod

        if (!NWAL1500CommonLogic.isCSMPExcl(bizMsg, lineMsg.rtlSwhCd_LL.getValue())) {
            ZYPEZDItemValueSetter.setValue(lineMsg.csmpContrNum_LL, configLineMsg.csmpContrNum_LC);
            ZYPEZDItemValueSetter.setValue(lineMsg.dlrRefNum_LL, configLineMsg.dlrRefNum_LC);
            ZYPEZDItemValueSetter.setValue(lineMsg.csmpPrcListCd_LL, configLineMsg.csmpPrcListCd_LC);
        } else {
            lineMsg.csmpContrNum_LL.clear();
            lineMsg.dlrRefNum_LL.clear();
            lineMsg.csmpPrcListCd_LL.clear();
        }
    }
    // 2018/01/25 S21_NA#19808 Add End

//    private void copyCsmpData(NWAL1500_CCMsg configLineMsg, NWAL1500_DCMsg lineMsg) {
//
//        ZYPEZDItemValueSetter.setValue(lineMsg.csmpContrNum_RL, configLineMsg.csmpContrNum_RC);
//        ZYPEZDItemValueSetter.setValue(lineMsg.dlrRefNum_RL, configLineMsg.dlrRefNum_RC);
//        ZYPEZDItemValueSetter.setValue(lineMsg.csmpPrcListCd_RL, configLineMsg.csmpPrcListCd_RC);
//    }

    private void copyCsmpData(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_CSMsg configLineMsg, NWAL1500_DSMsg lineMsg) { // 2018/01/25 S21_NA#19808 Mod

        if (!NWAL1500CommonLogic.isCSMPExcl(bizMsg, lineMsg.rtlSwhCd_RL.getValue())) {
            ZYPEZDItemValueSetter.setValue(lineMsg.csmpContrNum_RL, configLineMsg.csmpContrNum_RC);
            ZYPEZDItemValueSetter.setValue(lineMsg.dlrRefNum_RL, configLineMsg.dlrRefNum_RC);
            ZYPEZDItemValueSetter.setValue(lineMsg.csmpPrcListCd_RL, configLineMsg.csmpPrcListCd_RC);
        } else {
            lineMsg.csmpContrNum_RL.clear();
            lineMsg.dlrRefNum_RL.clear();
            lineMsg.csmpPrcListCd_RL.clear();
        }
    }

    // 2018/01/25 S21_NA#19808 Add Start
    private static void copyCsmpData(NWAL1500CMsg bizMsg, NWAL1500_CSMsg configLineMsg, NWAL1500_DSMsg lineMsg) {

        if (!NWAL1500CommonLogic.isCSMPExcl(bizMsg, lineMsg.rtlSwhCd_RL.getValue())) {
            ZYPEZDItemValueSetter.setValue(lineMsg.csmpContrNum_RL, configLineMsg.csmpContrNum_RC);
            ZYPEZDItemValueSetter.setValue(lineMsg.dlrRefNum_RL, configLineMsg.dlrRefNum_RC);
            ZYPEZDItemValueSetter.setValue(lineMsg.csmpPrcListCd_RL, configLineMsg.csmpPrcListCd_RC);
        } else {
            lineMsg.csmpContrNum_RL.clear();
            lineMsg.dlrRefNum_RL.clear();
            lineMsg.csmpPrcListCd_RL.clear();
        }
    }
    // 2018/01/25 S21_NA#19808 Add End
    // 2016/08/26 S21_NA#9806 Add End
    // QC#22371 2017/12/26 Mod End

    // 2016/08/31 S21_NA#10535 Add Start
    private void clearCsmpData(NWAL1500SMsg glblMsg, NWAL1500_ACMsg configLineMsg) { // 2018/01/25 S21_NA#19808 Mod

        String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_LC.getValue();
        configLineMsg.csmpContrNum_LC.clear();
        configLineMsg.dlrRefNum_LC.clear();
        configLineMsg.csmpPrcListCd_LC.clear();
        configLineMsg.prcCatgNm_LC.clear();

        // 2018/01/25 S21_NA#19808 Add Start
        NWAL1500_ASMsg glblConfigLineMsg = NWAL1500CommonLogicForPagination.getGlobalConfigMsgFromBizMsg(glblMsg, configLineMsg);
        glblConfigLineMsg.csmpContrNum_LC.clear();
        glblConfigLineMsg.dlrRefNum_LC.clear();
        glblConfigLineMsg.csmpPrcListCd_LC.clear();
        glblConfigLineMsg.prcCatgNm_LC.clear();
        // 2018/01/25 S21_NA#19808 Add End

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) { // 2018/01/25 S21_NA#19808 Mod
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            if (!S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                continue;
            }
            clearCsmpData(/* configLineMsg, */ lineMsg);  // 2018/01/25 S21_NA#19808 Mod
        }
    }

    private void clearCsmpData(NWAL1500SMsg glblMsg, NWAL1500_CCMsg configLineMsg) { // 2018/01/25 S21_NA#19808 Mod

        String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_RC.getValue();
        configLineMsg.csmpContrNum_RC.clear();
        configLineMsg.dlrRefNum_RC.clear();
        configLineMsg.csmpPrcListCd_RC.clear();
        configLineMsg.prcCatgNm_RC.clear();

        // 2018/01/25 S21_NA#19808 Add Start
        NWAL1500_CSMsg glblConfigLineMsg = NWAL1500CommonLogicForPagination.getGlobalConfigMsgFromBizMsg(glblMsg, configLineMsg);
        glblConfigLineMsg.csmpContrNum_RC.clear();
        glblConfigLineMsg.dlrRefNum_RC.clear();
        glblConfigLineMsg.csmpPrcListCd_RC.clear();
        glblConfigLineMsg.prcCatgNm_RC.clear();
        // 2018/01/25 S21_NA#19808 Add End

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg lineMsg = glblMsg.D.no(i);
            if (!S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_RL.getValue())) {
                continue;
            }
            clearCsmpData(/* configLineMsg, */ lineMsg);  // 2018/01/25 S21_NA#19808 Mod
        }
    }

    private void clearCsmpData(/* NWAL1500_ASMsg configLineMsg, */ NWAL1500_BSMsg lineMsg) { // 2018/01/25 S21_NA#19808 Mod

        lineMsg.csmpContrNum_LL.clear();
        lineMsg.dlrRefNum_LL.clear();
        lineMsg.csmpPrcListCd_LL.clear();
    }

    private void clearCsmpData(/* NWAL1500_CSMsg configLineMsg, */ NWAL1500_DSMsg lineMsg) { // 2018/01/25 S21_NA#19808 Mod

        lineMsg.csmpContrNum_RL.clear();
        lineMsg.dlrRefNum_RL.clear();
        lineMsg.csmpPrcListCd_RL.clear();
    }
    // 2016/08/31 S21_NA#10535 Add End
    private void doNothing() {
        return;
    }

    // 2016/11/09 S21_NA#15746 Add Start
    private boolean isRelatedShellItem(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg) { // 2018/01/25 S21_NA#19808 Mod

        BigDecimal shellCnt = NWAL1500QueryForLineConfig.getInstance().getRelatedShellDataCount(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue(), lineMsg.cpoDtlLineNum_LL.getValue(), lineMsg.cpoDtlLineSubNum_LL.getValue());
        if (BigDecimal.ZERO.compareTo(shellCnt) == 0) {
            return false;
        } else {
            return true;
        }
    }

    private NWAL1500_BSMsg getGlblLineMsg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_BCMsg lineMsg) {

        NWAL1500_BSMsg rslt = null;
        String cpoDtlLineNum = lineMsg.cpoDtlLineNum_LL.getValue();
        String cpoDtlLineSubNum = lineMsg.cpoDtlLineSubNum_LL.getValue();

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            if (S21StringUtil.isEquals(cpoDtlLineNum, glblMsg.B.no(i).cpoDtlLineNum_LL.getValue()) //
                    && S21StringUtil.isEquals(cpoDtlLineSubNum, glblMsg.B.no(i).cpoDtlLineSubNum_LL.getValue())) {
                rslt = glblMsg.B.no(i);
                break;
            }
        }
        if (rslt == null) {
            CPO_DTLTMsg cpoDtlMsg = new CPO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(cpoDtlMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlMsg.cpoOrdNum, bizMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlMsg.cpoDtlLineNum, cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);

            cpoDtlMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(cpoDtlMsg);
            if (cpoDtlMsg != null) {
                rslt = new NWAL1500_BSMsg();
                ZYPEZDItemValueSetter.setValue(rslt.cpoDtlLineNum_LL, cpoDtlMsg.cpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(rslt.cpoDtlLineSubNum_LL, cpoDtlMsg.cpoDtlLineSubNum);
                ZYPEZDItemValueSetter.setValue(rslt.mdseCd_LL, cpoDtlMsg.mdseCd);

                MDSETMsg mdseMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), cpoDtlMsg.mdseCd.getValue());
                if (mdseMsg != null) {
                    ZYPEZDItemValueSetter.setValue(rslt.mdseDescShortTxt_LL, mdseMsg.mdseDescShortTxt);
                }
            }
        }
        return rslt; // 2016/11/09 S21_NA#15746-2 Mod
    }
    // 2016/11/09 S21_NA#15746 Add End

    // Add Start 2017/02/07 QC#17257
    private void updateBaseCmptFlg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int selectIndex) { // 2018/01/25 S21_NA#19808 Mod

        List<String> posnNums = new ArrayList<String>();
        String getPosnNum = null; 
        String dplyTab = bizMsg.xxDplyTab.getValue();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            return;
        } else if (TAB_RMA.equals(dplyTab)) {
            getPosnNum = bizMsg.C.no(selectIndex).dsOrdPosnNum_RC.getValue();
            posnNums.add(getPosnNum);
        } else {
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                getPosnNum = bizMsg.C.no(i).dsOrdPosnNum_RC.getValue();
                if (!posnNums.contains(getPosnNum)) {
                    posnNums.add(getPosnNum);
                }
            }
        }
        NWAL1500CommonLogicForLineConfig.updateBaseCmptFlg(bizMsg, glblMsg, posnNums, false);
    }
    // Add End 2017/02/07 QC#17257

    // 2018/01/25 S21_NA#19808 Add Start
    private void doProcess_NWAL1500Scrn00_ConfigJump(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        String xxDplyTab = bizMsg.xxDplyTab.getValue();
        String jumptoPositionNum = "";
        if (NWAL1500CommonLogicForPagination.isTabLineConfig(xxDplyTab)) {
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            jumptoPositionNum = bizMsg.dsOrdPosnNum_AS.getValue();
        } else if (NWAL1500CommonLogicForPagination.isTabRma(xxDplyTab)) {
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            jumptoPositionNum = bizMsg.dsOrdPosnNum_CS.getValue();
        }
        NWAL1500CommonLogicForPagination.jumpToSpecifinedConfig(bizMsg, glblMsg, jumptoPositionNum, xxDplyTab);
    }

    private void doProcess_NWAL1500Scrn00_PageJump(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        String xxDplyTab = bizMsg.xxDplyTab.getValue();
        if (NWAL1500CommonLogicForPagination.isTabLineConfig(xxDplyTab)) {
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_LL.getValueInt());
        } else if (NWAL1500CommonLogicForPagination.isTabRma(xxDplyTab)) {
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, bizMsg.xxPageShowCurNum_RL.getValueInt());
        }
    }

    private void doProcess_NWAL1500Scrn00_PageNext(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        String xxDplyTab = bizMsg.xxDplyTab.getValue();
        int pageNum = 0;
        if (NWAL1500CommonLogicForPagination.isTabLineConfig(xxDplyTab)) {
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            pageNum = (bizMsg.xxPageShowToNum_LL.getValueInt() + 1) / bizMsg.B.length() + 1;
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, pageNum);
        } else if (NWAL1500CommonLogicForPagination.isTabRma(xxDplyTab)) {
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            pageNum = (bizMsg.xxPageShowToNum_RL.getValueInt() + 1) / bizMsg.D.length() + 1;
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, pageNum);
        }
    }

    private void doProcess_NWAL1500Scrn00_PagePrev(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        String xxDplyTab = bizMsg.xxDplyTab.getValue();
        int pageNum = 0;
        if (NWAL1500CommonLogicForPagination.isTabLineConfig(xxDplyTab)) {
            NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
            pageNum = (bizMsg.xxPageShowFromNum_LL.getValueInt() - bizMsg.B.length()) / bizMsg.B.length() + 1;
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, pageNum);
        } else if (NWAL1500CommonLogicForPagination.isTabRma(xxDplyTab)) {
            NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
            pageNum = (bizMsg.xxPageShowFromNum_RL.getValueInt() - bizMsg.D.length()) / bizMsg.D.length() + 1;
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, pageNum);
        }
    }

    private void copyBizDataToGlobalData(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
    }

    private void copyGlobalDataToBizData(NWAL1500SMsg glblMsg, NWAL1500CMsg bizMsg) {

        int lineConfPageNum = bizMsg.xxPageShowCurNum_LL.getValueInt();
        if (lineConfPageNum <= 0) {
            lineConfPageNum = 1;
        }
        NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, lineConfPageNum);

        int rmaPageNum = bizMsg.xxPageShowCurNum_RL.getValueInt();
        if (rmaPageNum <= 0) {
            rmaPageNum = 1;
        }
        NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, rmaPageNum);
    }

    private void collapseDetectedConfig(NWAL1500SMsg glblMsg, String xxDplyTab, String dsOrdPosnNum) {

        if (TAB_LINE_CONFIG.equals(xxDplyTab)) {
            int slctLine = -1;
            for(int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
                if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                    if (slctLine >= 0) {
                        lineMsg.xxSmryLineFlg_LL.setValue(ZYPConstant.FLG_ON_Y);
                    } else {
                        slctLine = i;
                        lineMsg.xxSmryLineFlg_LL.clear();
                    }
                }
            }
        } else if(TAB_RMA.equals(xxDplyTab)) {
            int slctLine = -1;
            for(int i = 0; i < glblMsg.D.getValidCount(); i++) {
                NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);
                if (S21StringUtil.isEquals(dsOrdPosnNum, rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                    if (slctLine >= 0) {
                        rmaLineMsg.xxSmryLineFlg_RL.setValue(ZYPConstant.FLG_ON_Y);
                    } else {
                        slctLine = i;
                        rmaLineMsg.xxSmryLineFlg_RL.clear();
                    }
                }
            }
        }
    }

    private static List<Integer> editCheckListForSet( //
            List<Integer> checkList, NWAL1500_BSMsgArray lineMsgArray) {
        List<Integer> rtnList = new ArrayList<Integer>();
        for (int ix : checkList) {
            NWAL1500_BSMsg lineMsg = lineMsgArray.no(ix);
            // is not Set parent item
            if (!"000".equals(lineMsg.cpoDtlLineSubNum_LL.getValue())) {
                rtnList.add(ix);
                continue;
            }

            StringBuilder dispLineNum = new StringBuilder();
            dispLineNum.append(lineMsg.dsOrdPosnNum_LL.getValue());
            dispLineNum.append(".");
            dispLineNum.append(lineMsg.dsCpoLineNum_LL.getValue());
            if (ZYPCommonFunc.hasValue(lineMsg.dsCpoLineSubNum_LL)) {
                dispLineNum.append(".");
                dispLineNum.append(lineMsg.dsCpoLineSubNum_LL.getValue());
            }
            for (int ixC = ix + 1; ixC < lineMsgArray.getValidCount(); ixC++) {
                //
                NWAL1500_BSMsg cmpLineMsg = lineMsgArray.no(ixC);
                StringBuilder compLineNum = new StringBuilder();
                compLineNum.append(cmpLineMsg.dsOrdPosnNum_LL.getValue());
                compLineNum.append(".");
                compLineNum.append(cmpLineMsg.dsCpoLineNum_LL.getValue());
                if (ZYPCommonFunc.hasValue(cmpLineMsg.dsCpoLineSubNum_LL)) {
                    compLineNum.append(".");
                    compLineNum.append(cmpLineMsg.dsCpoLineSubNum_LL.getValue());
                }
                //
                if (compLineNum.toString().startsWith(dispLineNum.toString())) {
                    rtnList.add(ixC);
                }
            }
        }
        return rtnList;
    }

    private static List<String> getDropShipWHCodeList(NWAL1500CMsg bizMsg) {
        List<String> externalLocList = new ArrayList<String>();
        String externalLocConstValue = ZYPCodeDataUtil.getVarCharConstValue(NLCL1000_DROP_SHIP_RTL_WH_CD, bizMsg.glblCmpyCd.getValue());
        if (externalLocConstValue != null) {
            String[] externalLocConstArr = externalLocConstValue.split(",");
            for (int i = 0; i < externalLocConstArr.length; i++) {
                externalLocList.add(externalLocConstArr[i]);
            }
        } else {
            externalLocList.add(DROP_SHIP_WH);
        }
        return externalLocList;
    }

    private void NWAL1500_NWAL1630(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int slctIndex = bizMsg.xxCellIdx.getValueInt();
        String dplyTab = bizMsg.xxDplyTab.getValue();
        NWAL1500_BCMsg lineMsg = null;
        NWAL1500_DCMsg rmaLineMsg = null;

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            lineMsg = bizMsg.B.no(slctIndex);
            NWAL1500CommonLogicForPagination.copyLineDataToSMsg(lineMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
        } else if (TAB_RMA.equals(dplyTab)) {
            rmaLineMsg = bizMsg.D.no(slctIndex);
            NWAL1500CommonLogicForPagination.copyRmaLineDataToSMsg(rmaLineMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
        }
    }
    // 2018/01/25 S21_NA#19808 Add End
    // 2018/06/29 S21_NA#27071 Add Start
    private void clearBizMsgCsmpNum(NWAL1500CMsg bizMsg, String dsOrdPosnNum) {
        if (S21StringUtil.isEquals(TAB_LINE_CONFIG, bizMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                NWAL1500_BCMsg lineMsg = bizMsg.B.no(i);
                if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                    lineMsg.csmpContrNum_LL.clear();
                    lineMsg.dlrRefNum_LL.clear();
                    lineMsg.csmpPrcListCd_LL.clear();
                }
            }
        } else if (S21StringUtil.isEquals(TAB_RMA, bizMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                NWAL1500_DCMsg rmaLineMsg = bizMsg.D.no(i);
                if (S21StringUtil.isEquals(dsOrdPosnNum, rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                    rmaLineMsg.csmpContrNum_RL.clear();
                    rmaLineMsg.dlrRefNum_RL.clear();
                    rmaLineMsg.csmpPrcListCd_RL.clear();
                }
            }
        }
    }
    // 2018/06/29 S21_NA#27071 Add End
    // 2018/07/03 S21_NA#26908 Add Start
    private void doProcess_NWAL2260Scrn00_OpenWin_ImptAttr(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        bizMsg.ordSrcRefNum_EA.clear();
        bizMsg.cpoSrcTpCd_EA.clear();
        bizMsg.dsImptOrdPk_EA.clear();
        bizMsg.xxReadOnlyFlg_EA.clear();
        bizMsg.dsImptOrdDtlPk_EA.clear();
        bizMsg.ordSrcRefLineNum_EA.clear();
        bizMsg.ordSrcRefLineSubNum_EA.clear();

        NWAL1500CommonLogicForPagination.saveLineConfigDataToGlbl(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);

        String dplyTab = bizMsg.xxDplyTab.getValue();
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

        if (TAB_LINE_CONFIG.equals(dplyTab)) {

            if (selectedRows.size() > 1) {
                bizMsg.setMessageInfo(NWAM0666E, new String[] {"Component Line" });
                return;
            }
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.ordSrcRefNum_EA, bizMsg.ordSrcRefNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSrcTpCd_EA, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsImptOrdPk_EA, bizMsg.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxReadOnlyFlg_EA, ZYPConstant.FLG_ON_Y);

        if (selectedRows.size() == 1) {

            NWAL1500_BSMsg lineMsg = glblMsg.B.no(selectedRows.get(0));
            DS_IMPT_ORD_DTLTMsg imptDtl = NWAL1500CommonLogicForLineConfig.getImptOrdDtl(bizMsg, lineMsg);

            if (imptDtl != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsImptOrdDtlPk_EA, imptDtl.dsImptOrdDtlPk);
                ZYPEZDItemValueSetter.setValue(bizMsg.ordSrcRefLineNum_EA, lineMsg.ordSrcRefLineNum_LL);
                ZYPEZDItemValueSetter.setValue(bizMsg.ordSrcRefLineSubNum_EA, lineMsg.ordSrcRefLineSubNum_LL);
            }
        }
    }
    // 2018/07/03 S21_NA#26908 Add End

    // 2018/07/27 S21_NA#14307 Add Start
    /**
     * do Process (OpenWin_SoldTo)
     * @param bizMsg NWAL1500CMsg
     */
    private void doProcess_NWAL1500Scrn00_OpenWin_SoldTo(NWAL1500CMsg bizMsg) {

        NWAL1500CommonLogicForCustomer.setAccountSearchPopupParam(bizMsg);
    }
    
    /**
     * do Process (OpenWin_BillTo)
     * @param bizMsg NWAL1500CMsg
     */
    private void doProcess_NWAL1500Scrn00_OpenWin_BillTo(NWAL1500CMsg bizMsg) {

        NWAL1500CommonLogicForCustomer.setAccountSearchPopupParam(bizMsg);
    }
    
    /**
     * do Process (OpenWin_ShipTo)
     * @param bizMsg NWAL1500CMsg
     */
    private void doProcess_NWAL1500Scrn00_OpenWin_ShipTo(NWAL1500CMsg bizMsg) {

        NWAL1500CommonLogicForCustomer.setAccountSearchPopupParam(bizMsg);
    }
    // 2018/07/27 S21_NA#14307 Add End
    // 2018/08/21 S21_NA#26767 Add Start
    private void doProcess_NWAL1500Scrn00_OnBlur_DeriveFromSerial(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (S21StringUtil.isEquals(TAB_LINE_CONFIG, bizMsg.xxDplyTab.getValue())) {
            setupDefaultingLineCatgOnBlurItemCode(bizMsg, glblMsg);
        } else if (S21StringUtil.isEquals(TAB_RMA, bizMsg.xxDplyTab.getValue())) {
            setupDefaultingRtrnRsnOnBlurItemCode(bizMsg, glblMsg);
        }
    }

    // 2023/02/07 QC#61010 Add Start
    private void setFlagFromItems(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.B.no(i).mdseCd_LL)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).serTakeFlg_LL, ZYPConstant.FLG_ON_Y);
            } else {
                Map<String, Object> ssmResult = NWAL1500Query.getInstance().getSerialTakeFlag(glblCmpyCd, bizMsg.B.no(i).mdseCd_LL.getValue());
                if(ssmResult != null) {
                    if (ZYPConstant.FLG_ON_Y.equals((String) ssmResult.get(RCV_SER_TAKE_FLG))|| ZYPConstant.FLG_ON_Y.equals((String) ssmResult.get(SHPG_SER_TAKE_FLG))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).serTakeFlg_LL,ZYPConstant.FLG_ON_Y);
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).serTakeFlg_LL,ZYPConstant.FLG_OFF_N);
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).serTakeFlg_LL,ZYPConstant.FLG_ON_Y);
                }
            }
        }
    }
    // 2023/02/07 QC#61010 Add End

    private void setupDefaultingLineCatgOnBlurItemCode(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int slctLineIndex = bizMsg.xxCellIdx.getValueInt();
        NWAL1500_BCMsg lineBizMsg = bizMsg.B.no(slctLineIndex);

        int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndexInBizMsg(bizMsg, slctLineIndex);
        NWAL1500_ACMsg confBizMsg = bizMsg.A.no(slctConfIndex);

        slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizMsg.B.no(slctLineIndex), glblMsg);
        slctConfIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizMsg.A.no(slctConfIndex), glblMsg);

        NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineIndex);
        NWAL1500_ASMsg confMsg = glblMsg.A.no(slctConfIndex);

        EZDMsg.copy(lineBizMsg, null, lineMsg, null);
        EZDMsg.copy(confBizMsg, null, confMsg, null);

        if (!NWAL1500CommonLogicForLineConfig.isConversionConfigTp(bizMsg.glblCmpyCd.getValue(), confMsg.configTpCd_LC.getValue())) {
            clearLineSvcMachMstrPk(bizMsg, glblMsg, slctLineIndex);
            return;
        }

        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), confMsg.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, true, false)) {
            if (!ZYPCommonFunc.hasValue(lineMsg.mdseCd_LL) //
                    || !ZYPCommonFunc.hasValue(lineMsg.ordCustUomQty_LL) //
                    || !ZYPCommonFunc.hasValue(lineMsg.serNum_LL)) {
                clearLineSvcMachMstrPk(bizMsg, glblMsg, slctLineIndex);
                return;
            }
            Map<String, Object> smmMap = NWXC150001CommonReturnReason.getSvcMachMstrPkAndConfigIdBySerNumAndMdseCd(//
                    bizMsg.glblCmpyCd.getValue(), //
                    bizMsg.slsDt.getValue(), //
                    lineBizMsg.serNum_LL.getValue(), //
                    lineBizMsg.mdseCd_LL.getValue());
            if (smmMap == null) {
                clearLineSvcMachMstrPk(bizMsg, glblMsg, slctLineIndex);
                return;
            }
            BigDecimal svcMachMstrPk = (BigDecimal) smmMap.get("SVC_MACH_MSTR_PK");
            if (svcMachMstrPk == null) {
                clearLineSvcMachMstrPk(bizMsg, glblMsg, slctLineIndex);
                return;
            }
            lineMsg.svcMachMstrPk_LL.setValue(svcMachMstrPk);
            BigDecimal svcConfigMstrPk = (BigDecimal)smmMap.get("SVC_CONFIG_MSTR_PK");
            if (svcConfigMstrPk != null) {
                NWAL1500_ASMsg configMsg = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.A, lineMsg.dsOrdPosnNum_LL.getValue());
                if (configMsg != null && !ZYPCommonFunc.hasValue(configMsg.svcConfigMstrPk_LC)) {
                    configMsg.svcConfigMstrPk_LC.setValue(svcConfigMstrPk);
                    ZYPEZDItemValueSetter.setValue(configMsg.mdlId_LC, (BigDecimal) smmMap.get("MDL_ID"));
                    ZYPEZDItemValueSetter.setValue(configMsg.mdlDescTxt_LC, (String) smmMap.get("T_MDL_NM"));
                    ZYPEZDItemValueSetter.setValue(configMsg.mdlNm_LC, (String) smmMap.get("T_MDL_NM"));
                }
            }
            // Defaulting Line Category
            boolean rslt = NWAL1500CommonLogicForLineConfig.defaultingLineCateg(bizMsg, confMsg, lineMsg);
            if (rslt) {
                ZYPEZDItemValueSetter.setValue(lineBizMsg.dsOrdLineCatgCd_LL, lineMsg.dsOrdLineCatgCd_LL);
                String rtlWhCd = ZYPCodeDataUtil.getVarCharConstValue(NWAL1500_RENTAL_CONV_DEFWH, bizMsg.glblCmpyCd.getValue());
                if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                    ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhCd_LL, rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhNm_LL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
                    lineMsg.rtlSwhCd_LL.clear();
                    lineMsg.rtlSwhNm_LL.clear();
                }
                NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
                NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
                return;
            }
        } else {
            clearLineSvcMachMstrPk(bizMsg, glblMsg, slctLineIndex);
        }
        return;
    }

    private void setupDefaultingRtrnRsnOnBlurItemCode(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        int slctLineIndex = bizMsg.xxCellIdx.getValueInt();
        NWAL1500_DCMsg rmaLineBizMsg = bizMsg.D.no(slctLineIndex);

        int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndexInBizMsg(bizMsg, slctLineIndex);
        NWAL1500_CCMsg rmaConfBizMsg = bizMsg.C.no(slctConfIndex);

        slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizMsg.D.no(slctLineIndex), glblMsg);
        slctConfIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(bizMsg.C.no(slctConfIndex), glblMsg);

        NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(slctLineIndex);
        NWAL1500_CSMsg rmaConfMsg = glblMsg.C.no(slctConfIndex);

        EZDMsg.copy(rmaLineBizMsg, null, rmaLineMsg, null);
        EZDMsg.copy(rmaConfBizMsg, null, rmaConfMsg, null);

        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), rmaConfMsg.configTpCd_RC.getValue(), CONFIG_CATG.INBOUND, false, true, false)) {
            if (!ZYPCommonFunc.hasValue(rmaLineMsg.mdseCd_RL) //
                    || !ZYPCommonFunc.hasValue(rmaLineMsg.ordCustUomQty_RL) //
                    || !ZYPCommonFunc.hasValue(rmaLineMsg.serNum_RL)) {
                clearRmaLineSvcMachMstrPk(bizMsg, glblMsg, slctLineIndex);
                return;
            }
            Map<String, Object> smmMap = NWXC150001CommonReturnReason.getSvcMachMstrPkAndConfigIdBySerNumAndMdseCd(//
                    bizMsg.glblCmpyCd.getValue(), //
                    bizMsg.slsDt.getValue(), //
                    rmaLineBizMsg.serNum_RL.getValue(), //
                    rmaLineBizMsg.mdseCd_RL.getValue());
            if (smmMap == null) {
                clearRmaLineSvcMachMstrPk(bizMsg, glblMsg, slctLineIndex);
                return;
            }
            BigDecimal svcMachMstrPk = (BigDecimal) smmMap.get("SVC_MACH_MSTR_PK");
            if (svcMachMstrPk == null) {
                clearRmaLineSvcMachMstrPk(bizMsg, glblMsg, slctLineIndex);
                return;
            }
            rmaLineMsg.svcMachMstrPk_RL.setValue(svcMachMstrPk);
            BigDecimal svcConfigMstrPk = (BigDecimal)smmMap.get("SVC_CONFIG_MSTR_PK");
            if (svcConfigMstrPk != null) {
                NWAL1500_CSMsg rmaConfigMsg = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.C, rmaLineMsg.dsOrdPosnNum_RL.getValue());
                if (rmaConfigMsg != null && !ZYPCommonFunc.hasValue(rmaConfigMsg.svcConfigMstrPk_RC)) {
                    rmaConfigMsg.svcConfigMstrPk_RC.setValue(svcConfigMstrPk);
                    ZYPEZDItemValueSetter.setValue(rmaConfigMsg.mdlId_RC, (BigDecimal) smmMap.get("MDL_ID"));
                    ZYPEZDItemValueSetter.setValue(rmaConfigMsg.mdlDescTxt_RC, (String) smmMap.get("T_MDL_NM"));
                    ZYPEZDItemValueSetter.setValue(rmaConfigMsg.mdlNm_RC, (String) smmMap.get("T_MDL_NM"));
                }
            }
            // Defaulting Return Reason
            if (!ZYPCommonFunc.hasValue(rmaLineMsg.rtrnRsnCd_RL)) {
                boolean rslt = NWAL1500CommonLogicForLineConfig.defaultingRtrnRsn(bizMsg, rmaLineMsg);
                if (rslt) {
                    ZYPEZDItemValueSetter.setValue(rmaLineBizMsg.rtrnRsnCd_RL, rmaLineMsg.rtrnRsnCd_RL);
                    doProcess_NWAL1500Scrn00_OnChange_ReturnReason(bizMsg, glblMsg);
                    return;
                }
            }
        } else {
            clearRmaLineSvcMachMstrPk(bizMsg, glblMsg, slctLineIndex);
        }
        return;
    }

    private void clearLineSvcMachMstrPk(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctLineIndex) {

        glblMsg.B.no(slctLineIndex).svcMachMstrPk_LL.clear();

        NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
    }

    private void clearRmaLineSvcMachMstrPk(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctLineIndex) {

        glblMsg.D.no(slctLineIndex).svcMachMstrPk_RL.clear();
        // START 2022/05/26 A.Cullano [QC#60048, ADD]
        glblMsg.D.no(slctLineIndex).dsContrNum_RL.clear();
        // END 2022/05/26 A.Cullano [QC#60048, ADD]

        NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
    }
    // 2018/08/21 S21_NA#26767 Add End
    
    // 2019/02/22 S21_NA#30449 Add Start
    private static void setContrData(NWAL1500_BSMsgArray lineList, String dsOrdPosnNum, NWAL1500_BSMsg lineMsg) {

        String preContrNum = "";
        BigDecimal preSvcMachMstrPk = new BigDecimal(-1);
        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL1500_BSMsg line = lineList.no(i);
            if (Integer.parseInt(line.dsOrdPosnNum_LL.getValue()) != Integer.parseInt(dsOrdPosnNum)) {
                continue;
            } else if (S21StringUtil.isEquals(line.cpoDtlLineNum_LL.getValue(), lineMsg.cpoDtlLineNum_LL.getValue()) && //
                    S21StringUtil.isEquals(line.cpoDtlLineSubNum_LL.getValue(), lineMsg.cpoDtlLineSubNum_LL.getValue())) {
                continue;
            } else if (!ZYPCommonFunc.hasValue(line.ordLineStsDescTxt_LL)) {
                return;
            } else if (!ZYPCommonFunc.hasValue(line.dsContrNum_LL) || !ZYPCommonFunc.hasValue(line.svcMachMstrPk_LL)) {
                return;
            }

            if (ZYPCommonFunc.hasValue(preContrNum) && ZYPCommonFunc.hasValue(preSvcMachMstrPk)) {
                if (preContrNum.compareTo(line.dsContrNum_LL.getValue()) != 0 || //
                        preSvcMachMstrPk.compareTo(line.svcMachMstrPk_LL.getValue()) != 0) {
                    return;
                }
            }
            preContrNum = line.dsContrNum_LL.getValue();
            preSvcMachMstrPk = line.svcMachMstrPk_LL.getValue();
        }
        ZYPEZDItemValueSetter.setValue(lineMsg.dsContrNum_LL, preContrNum);
        ZYPEZDItemValueSetter.setValue(lineMsg.svcMachMstrPk_LL, preSvcMachMstrPk);
    }

    private static boolean isContrSupOrd(NWAL1500CMsg bizMsg) {
        String resultFlg = NWAL1500Query.getInstance().getOrdCatgBizCtx(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CONTRACT_SUPPLY_ORDER_CATEGORY,//
                bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());

        if (ZYPCommonFunc.hasValue(resultFlg)) {
            return true;
        }
        return false;
    }
    // 2019/02/22 S21_NA#30449 Add End
    // 2019/07/05 S21_NA#51151 Add Start
    private boolean hasErrorForMasUpdate(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        boolean hasErrorForMasUpdate = false;
        String dplyTab = bizMsg.xxDplyTab.getValue();

        int errIdx = -1;
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            List<Integer> checkListItemConfig = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            List<Integer> checkListItemLine = ZYPTableUtil.getSelectedRows(glblMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
            if (checkListItemConfig.size() == 0 && checkListItemLine.size() == 0) {
                bizMsg.setMessageInfo(NWAM0504E, null);
                return true;
            }
            if (checkListItemConfig.size() > 0 && checkListItemLine.size() > 0) {
                bizMsg.setMessageInfo(NWAM0688E, null);
                return true;
            }
            // ADD START 2016/02/12 #1864
            if (checkListItemConfig.size() == 1 || checkListItemLine.size() == 1) {
                bizMsg.setMessageInfo(NWAM0766E, null);
                return true;
            }
            // ADD END 2016/02/12 #1864
            // ADD START 2016/03/03 #2176
            for(int checkLine : checkListItemLine){
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(checkLine);
                String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();
                if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
                    lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0781E);
                    hasErrorForMasUpdate = true;
                    if (errIdx < 0) {
                        errIdx = checkLine;
                    }
                }
            }

            // S21_NA#11700
            // check Credit Rebill Order
            //// Config
            for(int checkLine : checkListItemConfig){
                NWAL1500_ASMsg configMsg = glblMsg.A.no(checkLine);
                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
                    if (isCreditRebillLine(lineMsg)){
                        configMsg.xxChkBox_LC.setErrorInfo(1, NWAM0872E);
                        hasErrorForMasUpdate = true;
                        if (errIdx < 0) {
                            errIdx = checkLine;
                        }
                    }
                }

            }
            //// Line
            for(int checkLine : checkListItemLine){
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(checkLine);
                if (isCreditRebillLine(lineMsg)){
                    lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0872E);
                    hasErrorForMasUpdate = true;
                    if (errIdx < 0) {
                        errIdx = checkLine;
                    }
                }
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            List<Integer> checkListRMAConfig = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            List<Integer> checkListRMALine = ZYPTableUtil.getSelectedRows(glblMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
            if (checkListRMAConfig.size() == 0 && checkListRMALine.size() == 0) {
                bizMsg.setMessageInfo(NWAM0504E, null);
                return true;
            }
            if (checkListRMAConfig.size() > 0 && checkListRMALine.size() > 0) {
                bizMsg.setMessageInfo(NWAM0688E, null);
                return true;
            }
            if (checkListRMAConfig.size() == 1 || checkListRMALine.size() == 1) {
                bizMsg.setMessageInfo(NWAM0766E, null);
                return true;
            }
            for(int checkLine : checkListRMALine){
                NWAL1500_DSMsg lineMsg = glblMsg.D.no(checkLine);
                String lineStsNm = lineMsg.rtrnLineStsDescTxt_RL.getValue();
                if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
                    lineMsg.xxChkBox_RL.setErrorInfo(1, NWAM0781E);
                    hasErrorForMasUpdate = true;
                    if (errIdx < 0) {
                        errIdx = checkLine;
                    }
                }
                if(ZYPCommonFunc.hasValue(lineMsg.dsCpoLineSubNum_RL)){
                    lineMsg.xxChkBox_RL.setErrorInfo(1, NWAL1500MsgConstant.NWAM0783E, new String[]{NWAL1500MsgConstant.MSG_PARAM_SET_COMPONENT});
                    hasErrorForMasUpdate = true;
                    if (errIdx < 0) {
                        errIdx = checkLine;
                    }
                }
            }
        }
        if (errIdx >= 0) {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                int errPage = errIdx / bizMsg.B.length() + 1;
                NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, errPage);
            } else if (TAB_RMA.equals(dplyTab)) {
                int errPage = errIdx / bizMsg.D.length() + 1;
                NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, errPage);
            }
        }
        return hasErrorForMasUpdate;
    }

    private static boolean isCreditRebillLine(NWAL1500_BSMsg lineMsg) {

        if (CR_REBIL.CREDIT.equals(lineMsg.crRebilCd_LL.getValue())) {
            return true;
        }
        return false;
    }
    // 2019/07/05 S21_NA#51151 Add End

    // 2019/07/18 S21_NA#51327 Add Start
//    private boolean checkCreatedContract(NWAL1500CMsg bizMsg, NWAL1500_ASMsg configLineMsg) {
//    	S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().checkCreatedContract(bizMsg, configLineMsg);
//        List<Map<String, Object>> rsltMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//        if (rsltMapList != null && rsltMapList.size() > 0) {
//            return true;
//        }
//        return false;
//    }
    // 2019/07/18 S21_NA#51327 Add End
    
    //2024/02/21 CSA QC#63577 Add Start
    private void doProcess_NWAL1500Scrn00_Pricing(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg){
        
        S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();
        
        bizLogMsg.setSubSysId(NWAL1500Constant.SUB_SYS_ID);
        bizLogMsg.setProcId(NWAL1500Constant.PROCESS_ID);
        bizLogMsg.setDocTpCd(NWAL1500Constant.DOCUMENT_TYPE);
        bizLogMsg.setEventId(NWAL1500Constant.EVENT_ID_ENTRY_ACTION);
        bizLogMsg.setPrntDocId(bizMsg.cpoOrdNum.getValue());
        bizLogMsg.setBizProcCmntTxt_01(bizMsg.xxDplyTab.getValue());
        bizLogMsg.setBizProcCmntTxt_02("Pricing");
        bizLogMsg.setBizProcCmntTxt_03("");
        NWZC150001CpouInsBizProcLog cpouInsBizProcLog = new NWZC150001CpouInsBizProcLog();
        cpouInsBizProcLog.printBizProcessLog(Arrays.asList(bizLogMsg));
 
        doProcess_NWAL1500Scrn00_Calculation_Order_Amount(bizMsg, glblMsg);
    }
    //2024/02/21 CSA QC#63577 Add End
}
