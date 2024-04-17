/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.validation;

import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.DROP_SHIP_WH_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_SAVE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWAM0037E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWAM0909E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWAM0965E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0622E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1450E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1451E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1452E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1453E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1454E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1457E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1458E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1459E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1461E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1462E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1463E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1464E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1465E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1469E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1470E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1473E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1474E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1475E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1476E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1477E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1478E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1480E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1484E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1485E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1487E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1488E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1489E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1492E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1507E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1526E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1527E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1530E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1596E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1794E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1795E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1838E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1839E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1917E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1921E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1928E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2005E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2008E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2010E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2026E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2250E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2254E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2255E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2262E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2263E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2264E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2265E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2269E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2270E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2275E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2277E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2289E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2291E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2294E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2301E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2302E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2303E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2306E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2310E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.PCT_100;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_DTL_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.SET_LINE_SUB_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.VALID_SKIP_MACH_STS;
import static com.canon.cusa.s21.api.NWZ.NWZC167001.constant.NWZC167001Constant.MODE_SUBMIT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDDebugOutput;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.CPO_SRC_TPTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.MDSETMsg;
import business.db.ORD_LINE_VAL_SETTMsg;
import business.db.ORD_LINE_VAL_SETTMsgArray;
import business.db.ORD_PROC_TPTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.RTL_WHTMsg;
import business.db.RTRN_RSNTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;
import business.parts.NMZC610001_TransactionRuleListPMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150001_rtnPriceListPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157001_xxPrcListPMsgArray;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001DsCpoCommonBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Query;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001OrdLineValSetCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation.NWZC150001CpouExistsCdInDbCheck;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001CommonReturnReason;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * DS CPO Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/03/31   Fujitsu         T.Ogura         Create          N/A
 * 2017/06/06   Fujitsu         S.Takami        Update          S21_NA#18459
 * 2017/09/04   Fujitsu         K.Ishizuka      Update          S21_NA#17149(Sol#259)
 * 2017/09/19   Fujitsu         A.Kosai         Update          S21_NA#21010
 * 2017/09/18   Fujitsu         S.Takami        Update          S21_NA#21009
 * 2017/10/12   Fujitsu         A.Kosai         Update          S21_NA#21028
 * 2017/10/17   Fujitsu         A.Kosai         Update          S21_NA#21905
 * 2017/10/26   Fujitsu         R.Nakamura      Update          S21_NA#21323
 * 2017/10/30   Fujitsu         R.Nakamura      Update          S21_NA#22140
 * 2017/11/09   Fujitsu         K.Ishizuka      Update          S21_NA#18426(Sol#123)
 * 2017/11/10   Fujitsu         S.Takami        Update          S21_NA#22478
 * 2017/12/12   CITS            K.Ogino         Update          S21_NA#21129
 * 2017/12/21   Fujitsu         S.Takami        Update          S21_NA#20050
 * 2018/02/01   Fujitsu         A.Kosai         Update          S21_NA#23572
 * 2018/02/01   Fujitsu         T.Aoi           Update          S21_NA#23329(Sol#387)
 * 2018/02/09   Fujitsu         Y.Kanefusa      Update          S21_NA#24065
 * 2018/02/09   Fujitsu         M.Ohno          Update          S21_NA#22717
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          S21_NA#22967
 * 2018/02/28   Fujitsu         M.Ohno          Update          S21_NA#24117
 * 2018/03/15   Fujitsu         K.Ishizuka      Update          S21_NA#24253
 * 2018/03/27   Fujitsu         S.Ohki          Update          S21_NA#24867
 * 2018/03/29   Fujitsu         M.Ohno          Update          S21_NA#25099
 * 2018/04/02   Fujitsu         S.Takami        Update          S21_NA#25043
 * 2018/05/10   Fujitsu         Hd.Sugawara     Update          S21_NA#20343
 * 2018/05/14   Fujitsu         S.Takami        Update          S21_NA#25488
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/06/04   Fujitsu         M.Yamada        Update          S21_NA#25606
 * 2018/06/05   Fujitsu         S.Takami        Update          S21_NA#25151
 * 2018/06/13   Fujitsu         Y.Kanefusa      Update          S21_NA#24245
 * 2018/06/14   Fujitsu         K.Ishizuka      Update          S21_NA#24294
 * 2018/06/18   Fujitsu         H.Nagashima     Update          S21_NA#24705
 * 2018/06/22   Fujitsu         S.Takami        Update          S21_NA#24256
 * 2018/06/25   Fujitsu         Mz.Takahashi    Update          S21_NA#23726
 * 2018/07/02   Fujitsu         K.Ishizuka      Update          S21_NA#26354
 * 2018/07/30   Fujitsu         K.Ishizuka      Update          S21_NA#26181
 * 2018/08/10   Fujitsu         M.Yamada        Update          S21_NA#27596
 * 2018/08/21   Fujitsu         S.Takami        Update          S21_NA#26767
 * 2018/08/24   Fujitsu         K.Ishizuka      Update          S21_NA#27840
 * 2018/09/12   Fujitsu         K.Ishizuka      Update          S21_NA#28182
 * 2018/09/26   Fujitsu         K.Ishizuka      Update          S21_NA#28482
 * 2018/10/05   Fujitsu         M.Ohno          Update          S21_NA#28383
 * 2018/10/10   Fujitsu         K.Ishizuka      Update          S21_NA#28728
 * 2018/10/29   Fujitsu         Hd.Sugawara     Update          S21_NA#28882
 * 2018/10/29   Fujitsu         K.Kato          Update          S21_NA#28921
 * 2018/11/01   Fujitsu         K.Kato          Update          S21_NA#28928
 * 2018/11/14   Fujitsu         K.Kato          Update          S21_NA#29253
 * 2018/11/27   Fujitsu         Y.Kanefusa      Update          S21_NA#29138
 * 2018/11/28   Fujitsu         Hd.Sugawara     Update          S21_NA#29252
 * 2018/12/06   Fujitsu         M.Ishii         Update          S21_NA#29285
 * 2018/12/13   Fujitsu         S.Kosaka        Update          S21_NA#29315
 * 2018/12/20   Fujitsu         C.Hara          Update          S21_NA#28928
 * 2018/12/25   Fujitsu         S.Takami        Update          S21_NA#28921-2
 * 2019/01/08   Fujitsu         S.Kosaka        Update          QC#29753
 * 2019/01/08   Fujitsu         K.Kato          Update          QC#29241
 * 2019/01/21   Fujitsu         K.Ishizuka      Update          S21_NA#29983
 * 2019/02/07   Fujitsu         K.Ishizuka      Update          S21_NA#30157
 * 2019/02/22   Fujitsu         K.Ishizuka      Update          S21_NA#30449
 * 2019/02/28   Fujitsu         K.Ishizuka      Update          S21_NA#30544
 * 2019/03/05   Fujitsu         R.Nakamura      Update          S21_NA#30591
 * 2019/03/17   Fujitsu         K.Ishizuka      Update          S21_NA#30713
 * 2019/04/08   Fujitsu         K.Ishizuka      Update          S21_NA#31111
 * 2019/04/10   Fujitsu         K.Ishizuka      Update          S21_NA#31140
 * 2019/04/24   Fujitsu         K.Ishizuka      Update          S21_NA#31185
 * 2019/06/04   Fujitsu         R.Nakamura      Update          S21_NA#50557
 * 2019/07/30   Fujitsu         T.Noguchi       Update          S21_NA#52201
 * 2019/08/01   Fujitsu         S.Kosaka        Update          QC#52212
 * 2019/08/27   Fujitsu         R.Nakamura      Update          S21_NA#52967
 * 2019/09/27   Fujitsu         R.Matsuki       Update          QC#53593
 * 2019/10/02   Fujitsu         M.Ohno          Update          S21_NA#52988
 * 2020/01/23   Fujitsu         K.Kato          Update          QA#55207-1
 * 2021/04/26   CITS            A.Marte         Update          QC#58646
 * 2021/06/28   CITS            K.Ogino         Update          QC#58901
 * 2022/08/24   CITS            F.Fadriquela    Update          QC#60482
 * 2023/04/11   CITS            I.Fusawa        Update          QC#61384
 * 2023/07/25   Hitachi         K.Chiba         Update          QC#61199
 * 2023/12/06   CITS            K.Ikeda         Update          QC#61281    
 * </pre>
 */
public class NWZC150001ForOtherCheck {
// 2019/09/27 QC#53593 MOD START
//    public static void otherCheck(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List, List<MDSETMsg> mdseTMsgList, List<MDSETMsg> mdseTMsgRtrnList, NWZC150001CpouLocalCache localCache, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) {
    public static void otherCheck(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List, List<MDSETMsg> mdseTMsgList, List<MDSETMsg> mdseTMsgRtrnList, NWZC150001CpouLocalCache localCache, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean, String slsDt) {
// 2019/09/27 QC#53593 MOD START
        otherHeaderCheck(pMsg, localCache, msgIdMgr, commonBean);
//        if (this.msgIdMgr.isXxMsgId()) {
        if (msgIdMgr.isXxMsgId()) {
            return;
        }
        // 2016/01/13 S21_NA#2726 Mod Start
        // otherConfigCheck(pMsg, pMsg2List);
        otherConfigCheck(pMsg, pMsg2List, pMsg3List, msgIdMgr, commonBean);
        // 2016/01/13 S21_NA#2726 Mod End
        // 2016/02/25 S21_NA#1704 Add Start
        otherCheckDuplicateMachine(pMsg, mdseTMsgList, mdseTMsgRtrnList, pMsg2List, pMsg3List, commonBean);
        // 2016/02/25 S21_NA#1704 Add End
        boolean isCustMdseXrefChk = ZYPConstant.FLG_ON_Y.equals(getCustMdseXrefFlg(pMsg)); // QC#14815

// 2019/09/27 QC#53593 MOD START
//        otherDetailCheck(pMsg, pMsg2List, mdseTMsgList, isCustMdseXrefChk, msgIdMgr, commonBean); // QC#14815
//        otherReturnDetailCheck(pMsg, pMsg3List, mdseTMsgRtrnList, isCustMdseXrefChk, msgIdMgr, commonBean); // QC#14815
        otherDetailCheck(pMsg, pMsg2List, mdseTMsgList, isCustMdseXrefChk, msgIdMgr, commonBean, slsDt);
        otherReturnDetailCheck(pMsg, pMsg3List, mdseTMsgRtrnList, isCustMdseXrefChk, msgIdMgr, commonBean, slsDt);
// 2019/09/27 QC#53593 MOD END

        otherSvcExchCheck(pMsg, pMsg2List, pMsg3List, msgIdMgr);
        otherQtyCheck(pMsg, pMsg2List, mdseTMsgList);
        otherQtyCheckForReturn(pMsg, pMsg3List);
        //        otherCsmpCheck(pMsg, pMsg2List, pMsg3List);   del 20151113

        otherEasyPackCheck(pMsg, pMsg2List, mdseTMsgList);
        otherEasyPackCheckForReturn(pMsg, pMsg3List, mdseTMsgRtrnList);

        // 2018/06/14 S21_NA#24294 Mod Start
        // Map<Map<String, String>, Map<String, String>> mdseInfoCacheMap = new HashMap<Map<String, String>, Map<String, String>>();
        // otherMdseStsCheck(pMsg, pMsg2List, mdseInfoCacheMap);
        // otherMdseStsCheckForReturn(pMsg, pMsg3List, mdseInfoCacheMap);
        Map<String, Map<String, String>> mdseItemFlagInfoMap = new HashMap<String, Map<String, String>>();
        otherMdseStsCheck(pMsg, pMsg2List, mdseItemFlagInfoMap);
        otherMdseStsCheckForReturn(pMsg, pMsg3List, mdseItemFlagInfoMap);
        // 2018/06/14 S21_NA#24294 Mod End
        
        // QC#24705 2018/06/18 add Start
        otherManualCreditRebill(pMsg, pMsg2List, msgIdMgr);
        // QC#24705 2018/06/18 add End
    }

    private static void otherHeaderCheck(NWZC150001PMsg pMsg, NWZC150001CpouLocalCache localCache, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) {
        if (NWXC150001DsCheck.checkDsOrdTpAndDsOrdCatgRelation(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdCatgCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1450E, pMsg);
        }
        if (NWXC150001DsCheck.checkCpoOrdTpAndDsOrdRsnRelation(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdRsnCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1451E, pMsg);
        }
        checkBillSellShipRelation(pMsg, msgIdMgr, commonBean);

        //        if (NWXC150001DsCheck.checkFrtTermRelation(//
        //                glblCmpyCd //
        //                , slsDt //
        //                , pMsg.dsOrdTpCd.getValue() //
        //                , pMsg.dsOrdRsnCd.getValue())) {
        //            setMsgId(pMsg, NWZM1456E);
        //        }
        if (NWXC150001DsCheck.checkCarrSvcLvlAndShpgSvcLvlRelation(//
                pMsg.glblCmpyCd.getValue() //
                , pMsg.addShpgSvcLvlCd.getValue() //
                , pMsg.carrSvcLvlCd.getValue())) {
//            setMsgId(pMsg, NWZM1457E);
            msgIdMgr.addXxMsgId(NWZM1457E, pMsg);
        }
        // QC#21129
        if (ZYPCommonFunc.hasValue(pMsg.dsOrdCatgCd) && !DS_ORD_CATG.INTERNAL_CSA.equals(pMsg.dsOrdCatgCd.getValue())) {
            if (NWXC150001DsCheck.checkFrtCondSvcLvlRelation(//
                    pMsg.glblCmpyCd.getValue() //
                    , pMsg.slsDt.getValue() //
                    , pMsg.dsOrdTpCd.getValue() //
                    , pMsg.frtCondCd.getValue() //
                    , pMsg.addShpgSvcLvlCd.getValue() //
            // 2018/12/13 S21_NA#29315 Mod Start
            //        , pMsg.carrSvcLvlCd.getValue())) {
                    , pMsg.carrSvcLvlCd.getValue() //
                    , pMsg.shipToCustAcctCd.getValue() //
                    , NWZC150001Common.getShipToLocNum(pMsg, localCache))) {
            // 2018/12/13 S21_NA#29315 Mod End
                msgIdMgr.addXxMsgId(NWZM1458E, pMsg);
            }
        }
        if (NWXC150001DsCheck.checkCarrSvcLevelRelation(pMsg.glblCmpyCd.getValue(), pMsg.carrSvcLvlCd.getValue(), pMsg.frtCondCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM2010E, pMsg);
        }
        if (NWXC150001DsCheck.checkAddlCarrAcctNumRelation(pMsg.glblCmpyCd.getValue(), pMsg.carrAcctNum.getValue(), pMsg.frtCondCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1459E, pMsg);
        }

        // S21_NA#23726 2018/06/25 Add Start
        boolean flg = NWXC150001DsCheck.checkCustCarrSvcLvlRelation(
                pMsg.glblCmpyCd.getValue(), pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdRsnCd.getValue(), 
                pMsg.shipToCustAcctCd.getValue(), pMsg.carrSvcLvlCd.getValue(), pMsg.frtCondCd.getValue());

        if (flg){
            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM2267E, pMsg);
        }
        // S21_NA#23726 2018/06/25 Add End

        if (!MODE_SAVE.equals(pMsg.xxModeCd.getValue()) && //
                !(CPO_SRC_TP.EMANAGE.equals(pMsg.cpoSrcTpCd.getValue()) && !ZYPCommonFunc.hasValue(pMsg.dsCrCardPk))) { // S21_NA Add S21_NA#18426(Sol#123)
//            if (NWXC150001DsCheck.checkPmntMethRelation(pMsg.addPmtTermCashDiscCd.getValue(), pMsg.dsCrCardPk.getValue())) { 2016/06/06 S21_NA#9278 Mod Condition
            // 2023/12/06 QC#61281 K.Ikeda START
            if (!DS_PMT_METH.NO_CREDIT_CARD.equals(pMsg.dsPmtMethCd.getValue())) {
            // 2023/12/06 QC#61281 K.Ikeda END
                if (NWXC150001DsCheck.checkPmntMethRelation(pMsg.dsPmtMethCd.getValue(), pMsg.dsCrCardPk.getValue())) {    
                    // Mod Start 2016/09/30 S21_NA#14329
                    //setMsgId(pMsg, NWZM1460E);
                    msgIdMgr.addXxMsgId(NWZM2026E, pMsg);
                    // Add End 2016/09/30 S21_NA#14329
                }
            // 2023/12/06 QC#61281 K.Ikeda START
            }
            // 2023/12/06 QC#61281 K.Ikeda END
        }

        // Add Start 2018/11/28 QC#29252
        if (checkCreditCardReq(pMsg, msgIdMgr, commonBean.getOnBatchType())) {
            msgIdMgr.addXxMsgId(NWZM1838E, pMsg);
        }
        // Add End 2018/11/28 QC#29252

        // QC#17474 2017/02/21 Add Start
        if(NWXC150001DsCheck.checkCcReq(pMsg.glblCmpyCd.getValue(), pMsg.billToCustAcctCd.getValue(), pMsg.billToCustCd.getValue(), pMsg.addPmtTermCashDiscCd.getValue())){
            msgIdMgr.addXxMsgId(NWZM1839E, pMsg);
        }
        // QC#17474 2017/02/21 Add End
        // Add Start 2016/08/30 S21_NA#11547
        if (NWXC150001DsCheck.checkPmntMethAndTermRelation(pMsg.glblCmpyCd.getValue(), pMsg.dsPmtMethCd.getValue(), pMsg.addPmtTermCashDiscCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM2005E, pMsg);
        }
        // Add Start 2016/08/30 S21_NA#11547
        if (NWXC150001DsCheck.checkCsmpRelation(pMsg.glblCmpyCd.getValue(), pMsg.csmpContrNum.getValue(), pMsg.sellToCustCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1461E, pMsg);
        }
        if (NWXC150001DsCheck.checkCsaNumRelation(pMsg.glblCmpyCd.getValue(), pMsg.dlrRefNum.getValue(), pMsg.sellToCustCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1462E, pMsg);
        }
        if (NWXC150001DsCheck.checkSalesRepRelation(pMsg.glblCmpyCd.getValue(), pMsg.slsRepCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1463E, pMsg);
        }

        // S21_NA#10321-20 Mod Start
        String calledOrderEntryFlg = pMsg.xxPgFlg_OE.getValue();
        if (!ZYPCommonFunc.hasValue(calledOrderEntryFlg) || ZYPConstant.FLG_OFF_N.equals(calledOrderEntryFlg)) {
            if (NWXC150001DsCheck.checkPrcCatgRelation(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.slsDt.getValue(), pMsg.prcCatgCd.getValue())) {
                NWZC157001PMsg prcApiPMsg //
                = callPricingApiForGetPrcList(//
                        pMsg //
                        , NWZC157001.GET_PRICE_LIST //
                        , PRC_CTX_TP.SALES_PRICING //
                        , ZYPConstant.FLG_ON_Y
                        // 2017/05/11 S21_NA#Review structure Lv.2 Add Start
                        , localCache
                        , commonBean);
                        // 2017/05/11 S21_NA#Review structure Lv.2 Add End

                if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
                    List<String> ml = S21ApiUtil.getXxMsgIdList(prcApiPMsg);
                    for (String msgId : ml) {
                        msgIdMgr.addXxMsgId(msgId, pMsg);
                    }
                    for (int i = 0; i < prcApiPMsg.xxMsgIdList.getValidCount(); i++) {
                        EZDDebugOutput.println(1, //
                                S21MessageFunc.clspGetMessage(//
                                        prcApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue() //
                                        , new String[] {prcApiPMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue() //
                                                , prcApiPMsg.xxMsgIdList.no(i).xxMsgPrmTxt_1.getValue() //
                                                , prcApiPMsg.xxMsgIdList.no(i).xxMsgPrmTxt_2.getValue() //
                                        }), NWZC150001ForOtherCheck.class);
                    }
                    return;
                }
                if (checkIncludePrcCatg(prcApiPMsg.xxPrcList, pMsg.prcCatgCd.getValue())) {
                    msgIdMgr.addXxMsgId(NWZM1464E, pMsg);
                }
            }
            if (NWXC150001DsCheck.checkFlPrcListRelation(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.slsDt.getValue(), pMsg.flPrcListCd.getValue())) {
                NWZC157001PMsg prcApiPMsg //
                = callPricingApiForGetPrcList(//
                        pMsg //
                        , NWZC157001.GET_PRICE_LIST //
                        , PRC_CTX_TP.FLOOR_PRICE //
                        , ZYPConstant.FLG_ON_Y
                        // 2017/05/11 S21_NA#Review structure Lv.2 Add Start
                        , localCache
                        , commonBean);
                        // 2017/05/11 S21_NA#Review structure Lv.2 Add End
                if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
                    List<String> ml = S21ApiUtil.getXxMsgIdList(prcApiPMsg);
                    for (String msgId : ml) {
                        msgIdMgr.addXxMsgId(msgId, pMsg);
                    }
                    for (int i = 0; i < prcApiPMsg.xxMsgIdList.getValidCount(); i++) {
                        EZDDebugOutput.println(1, //
                                S21MessageFunc.clspGetMessage(//
                                        prcApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue() //
                                        , new String[] {prcApiPMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue() //
                                                , prcApiPMsg.xxMsgIdList.no(i).xxMsgPrmTxt_1.getValue() //
                                                , prcApiPMsg.xxMsgIdList.no(i).xxMsgPrmTxt_2.getValue() //
                                        }), NWZC150001ForOtherCheck.class);
                    }
                    return;
                }
                if (checkIncludePrcCatg(prcApiPMsg.xxPrcList, pMsg.flPrcListCd.getValue())) {
                    msgIdMgr.addXxMsgId(NWZM1465E, pMsg);
                }
            }
        }
        // S21_NA#10321-20 Mod End
    }

    /**
     * Other Check for Config
     * @param pMsg DS CPO Update API Parameter
     * @param pMsg2List Outbound Error Message List
     * @param pMsg3List Inbound error Message List<- 2016/01/13 S21_NA#2726 Add
     * @param msgIdMgr S21ApiMessageIdMgr
     * @param pMsg2List commonBean NWZC150001DsCpoCommonBean
     */
    private static void otherConfigCheck(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) {
        List<List<Object>> configIdList = new ArrayList<List<Object>>(); // QC#24245 2018/06/13 Add
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(i);
            NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
            NWZC150003PMsg pMsg3 = new NWZC150003PMsg(); // 2016/01/13 S21_NA#2726 Add
            // checkConfigBillSellShip(pMsg, configPMsg, pMsg2); // 2016/03/16 S21_NA#5519 Add Parameter
            checkConfigBillSellShip(pMsg, configPMsg, pMsg2, pMsg3, msgIdMgr, commonBean); // 2016/03/16 S21_NA#5519 Add Parameter

            if (S21ApiUtil.isXxMsgId(pMsg2)) {
                ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, configPMsg.dsOrdPosnNum);
                pMsg2List.add(pMsg2);
            }
            // 2016/03/16 S21_NA#5519 Add Start
            if (S21ApiUtil.isXxMsgId(pMsg3)) {
                ZYPEZDItemValueSetter.setValue(pMsg3.dsOrdPosnNum, configPMsg.dsOrdPosnNum);
                pMsg3List.add(pMsg3);
            }
            // 2016/03/16 S21_NA#5519 Add End
            // 2016/01/13 S21_NA#2726 Add Start
            if (CONFIG_CATG.INBOUND.equals(configPMsg.configCatgCd.getValue()) && ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)) {
                if (NWXC150001DsCheck.isErrorConfigTpConfigIdRalation(pMsg.glblCmpyCd.getValue(), configPMsg.configTpCd.getValue(), configPMsg.svcConfigMstrPk.getValue())) {
                    NWZC150001Common.setMsgId3(pMsg3, NWZM1794E); // Please select Existing IB or Add To Config as Config Type.
                    ZYPEZDItemValueSetter.setValue(pMsg3.dsOrdPosnNum, configPMsg.dsOrdPosnNum);
                    pMsg3List.add(pMsg3);
                }
            }
            // 2016/01/13 S21_NA#2726 Add End

            checkMultiConfigId(pMsg, pMsg2List, pMsg3List, configPMsg); // 2016/03/16 S21_NA#5519 Add Start

            // QC#24245 2018/06/13 Add Start
            // Mod Start 2019/06/04 QC#50557
            String isLoanFlg = NWZC150001Query.getInstance().getOrdCatgBizCtx( //
                    pMsg.glblCmpyCd.getValue() //
                    , ORD_CATG_CTX_TP.LOAN_ORDER_VALUE_SET //
                    , pMsg.dsOrdCatgCd.getValue() //
                    , pMsg.dsOrdTpCd.getValue());

//            if (ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)) {
            if (ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk) && !ZYPConstant.FLG_ON_Y.equals(isLoanFlg)) {
            // Mod End 2019/06/04 QC#50557
                List<Object> list = new ArrayList<Object>();
                list.add(configPMsg.configCatgCd.getValue());
                list.add(configPMsg.svcConfigMstrPk.getValue());
                if (configIdList.contains(list)) {
                    if (CONFIG_CATG.OUTBOUND.equals(configPMsg.configCatgCd.getValue())) {
                        NWZC150001Common.setMsgId2(pMsg2, NWZM2270E);
                        ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, configPMsg.dsOrdPosnNum);
                        pMsg2List.add(pMsg2);
                    } else {
                        NWZC150001Common.setMsgId3(pMsg3, NWZM2270E);
                        ZYPEZDItemValueSetter.setValue(pMsg3.dsOrdPosnNum, configPMsg.dsOrdPosnNum);
                        pMsg3List.add(pMsg3);
                    }
                } else {
                    configIdList.add(list);
                }
            }
            // QC#24245 2018/06/13 Add End
        }
    }

    private static void otherCheckDuplicateMachine(NWZC150001PMsg pMsg, List<MDSETMsg> mdseTMsgList, List<MDSETMsg> mdseTMsgRtrnList, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List, NWZC150001DsCpoCommonBean commonBean) {
        // For Performance QC#8166 Mod Start
        // if (!NWZC150001Query.getInstance().isExistOrdCatg(pMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS)) {
//        if (!NWZC150001Common.isExistOrdCatgFromCache(pMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS, commonBean)) { 2018/05/20 S21_NA#25604 Mod Condition
        if (!NWXC150001DsCheck.isAvalOrderCtxType(pMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS, pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdRsnCd.getValue())) { // 2018/05/20 S21_NA#25604 Mod Condition
        // For Performance QC#8166 Mod End
            return;
        }

        // Add Start 2019/08/27 QC#52967
        int ordTakeCodeLen = getOrdTakeCodeLen();
        // Add End 2019/08/27 QC#52967

        List<NWZC150001_APMsg> duplMachineList = new ArrayList<NWZC150001_APMsg>(0); // 2016/03/04 S21_NA#5000 (#28) Add
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(i);
            // NWZC150002PMsg pMsg2 = new NWZC150002PMsg();  2016/03/04 S21_NA#5000 (#28) Del

            String configPosnNum = configPMsg.dsOrdPosnNum.getValue();
            duplMachineList.clear(); // 2016/03/04 S21_NA#5000 (#28) Add
            if (CONFIG_CATG.OUTBOUND.equals(configPMsg.configCatgCd.getValue())) {
                // boolean dupFlg = false;  // 2016/03/04 S21_NA#5000 (#28) Del
                // Add Start 2019/08/27 QC#52967
                String mainMdseCd = null;
                boolean isOrdTakeMdse = false;
                if (ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)) {
                    SVC_MACH_MSTRTMsg mainSvcMachMstr = getMainSvcMachMstr(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue());

                    if (null != mainSvcMachMstr) {
                        mainMdseCd = getOrdTakeMdseCd(pMsg.glblCmpyCd.getValue(), mainSvcMachMstr.mdseCd.getValue());

                        if (null != mainMdseCd) {
                            isOrdTakeMdse = true;
                        } else {
                            isOrdTakeMdse = false;
                            mainMdseCd = mainSvcMachMstr.mdseCd.getValue();
                        }
                    }
                }
                // Add End 2019/08/27 QC#52967

                for (int j = 0; j < pMsg.A.getValidCount(); j++) {
                    NWZC150001_APMsg aPMsg = pMsg.A.no(j);
                    MDSETMsg mdseTMsg = mdseTMsgList.get(j);

                    // Add Start 2019/08/27 QC#52967
                    String targetMdseCd = getOutboundMdse(mdseTMsg.mdseCd.getValue(), isOrdTakeMdse, ordTakeCodeLen);
                    // Add End 2019/08/27 QC#52967

                    // Mod Start 2019/08/27 QC#52967
                    if (configPosnNum.equals(aPMsg.dsOrdPosnNum_A1.getValue())
                            && !SET_LINE_SUB_NUM.equals(aPMsg.cpoDtlLineSubNum_A1.getValue())
//                            && NWXC150001DsCheck.getCoaMdseTp(pMsg.glblCmpyCd.getValue(), mdseTMsg.mdseCd.getValue()).equals(COA_MDSE_TP.MACHINE)) {
                            && (NWXC150001DsCheck.getCoaMdseTp(pMsg.glblCmpyCd.getValue(), mdseTMsg.mdseCd.getValue()).equals(COA_MDSE_TP.MACHINE)
                                    || S21StringUtil.isEquals(mainMdseCd, targetMdseCd))) {
                            
                        // 2016/03/04 S21_NA#5000 (#28) Mod Start
                        // if (dupFlg){
                        //     setMsgId2(pMsg2, NWZM1921E);
                        //     pMsg2List.add(pMsg2);
                        //     ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, aPMsg.dsOrdPosnNum_A1);
                        //     ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                        //     ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                        //     continue;
                        // } else {
                        //     dupFlg = true;
                        // }
                        duplMachineList.add(aPMsg);
                        // 2016/03/04 S21_NA#5000 (#28) Mod End
                    }
                    // Mod End 2019/08/27 QC#52967
                }
                // 2016/03/04 S21_NA#5000 (#28) Add Start
                if (duplMachineList.size() > 1) {
                    for (NWZC150001_APMsg aPMsg : duplMachineList) {
                        NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                        NWZC150001Common.setMsgId2(pMsg2, NWZM1921E);
                        ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, aPMsg.dsOrdPosnNum_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                        pMsg2List.add(pMsg2);
                    }
                // QC#24245 2018/06/13 Add Start
                } else {
                    BigDecimal svcConfigMstrPk = configPMsg.svcConfigMstrPk.getValue();
                    boolean atCustFlg = false;
                    if (SVC_MACH_USG_STS.AT_CUSTOMER.equals(NWXC150001DsCheck.getMachineUsageStatus(pMsg.glblCmpyCd.getValue(), svcConfigMstrPk))) {
                        atCustFlg = true;
                        for (int j = 0; j < pMsg.cpoConfig.getValidCount(); j++) {
                            NWZC150001_cpoConfigPMsg rmaConfigPMsg = pMsg.cpoConfig.no(j);
                            // does exist inbound config ?
                            if (CONFIG_CATG.INBOUND.equals(rmaConfigPMsg.configCatgCd.getValue())) {
                                BigDecimal rmaSvcConfigMstrPk = rmaConfigPMsg.svcConfigMstrPk.getValue();
                                if (rmaConfigPMsg.svcConfigMstrPk.getValue() == null) {
                                    rmaSvcConfigMstrPk = BigDecimal.ZERO;
                                }
                                if (svcConfigMstrPk.compareTo(rmaSvcConfigMstrPk) == 0) {
                                    if (hasMainMachineRmaConfig(pMsg, rmaConfigPMsg)) {
                                        atCustFlg = false;
                                    }
                                }
                            }
                        }
                        // does exists outbound line?
                        for(NWZC150001_APMsg apMsg : duplMachineList){
                            if(svcConfigMstrPk.compareTo(apMsg.svcConfigMstrPk_A1.getValue()) == 0){
                                atCustFlg = false;
                            }
                        }
                        int count = duplMachineList.size();
                        if(atCustFlg){
                            count = count + 1;
                        }
                        if (count != 1) {
                            if (duplMachineList.size() > 0)
                                for (NWZC150001_APMsg aPMsg : duplMachineList) {
                                    if (CR_REBIL.REBILL.equals(aPMsg.crRebilCd_A1.getValue())) {
                                        continue;
                                    } else if (CR_REBIL.CREDIT.equals(aPMsg.crRebilCd_A1.getValue())) {
                                        continue;
                                    }
                                    NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                                    NWZC150001Common.setMsgId2(pMsg2, NWZM2269E);
                                    ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, aPMsg.dsOrdPosnNum_A1);
                                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                                    pMsg2List.add(pMsg2);
                                }
                            else {
                                NWZC150001Common.addMsgId2List(pMsg2List, configPMsg.dsOrdPosnNum.getValue(), NWZM2269E);
                            }
                        }
                    }
                // QC#24245 2018/06/13 Add End
                } 
                // 2016/03/04 S21_NA#5000 (#28) Add End
            }
        }
        // 2017/10/12 S21_NA#21028 Del Start
//        List<NWZC150001_rtnDtlPMsg> duplMachineRmaList = new ArrayList<NWZC150001_rtnDtlPMsg>(0); // 2016/03/04 S21_NA#5000 (#28) Add
//        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
//            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(i);
//            // NWZC150003PMsg pMsg3 = new NWZC150003PMsg(); 2016/03/04 S21_NA#5000 (#28) Del
//
//            String configPosnNum = configPMsg.dsOrdPosnNum.getValue();
//            duplMachineRmaList.clear(); // 2016/03/04 S21_NA#5000 (#28) Add
//            if (CONFIG_CATG.INBOUND.equals(configPMsg.configCatgCd.getValue())) {
//                // boolean dupFlg = false; 2016/03/04 S21_NA#5000 (#28) Del
//                for (int j = 0; j < pMsg.rtnDtl.getValidCount(); j++) {
//                    NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(j);
//                    MDSETMsg mdseTMsg = mdseTMsgRtrnList.get(j);
//
//                    if (configPosnNum.equals(rtnDtlPMsg.dsOrdPosnNum_B1.getValue())
//                            && !SET_LINE_SUB_NUM.equals(rtnDtlPMsg.cpoDtlLineSubNum_B1.getValue())
//                            && NWXC150001DsCheck.getCoaMdseTp(pMsg.glblCmpyCd.getValue(), mdseTMsg.mdseCd.getValue()).equals(COA_MDSE_TP.MACHINE)) {
//                        // 2016/03/04 S21_NA#5000 (#28) Mod Start
//                        // if (dupFlg){
//                        //     setMsgId3(pMsg3, NWZM1921E);
//                        //     pMsg3List.add(pMsg3);
//                        //     ZYPEZDItemValueSetter.setValue(pMsg3.dsOrdPosnNum, rtnDtlPMsg.dsOrdPosnNum_B1);
//                        //     ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtnDtlPMsg.cpoDtlLineNum_B1);
//                        //     ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtnDtlPMsg.cpoDtlLineSubNum_B1);
//                        //     continue;
//                        // } else {
//                        //     dupFlg = true;
//                        // }
//                        duplMachineRmaList.add(rtnDtlPMsg);
//                        // 2016/03/04 S21_NA#5000 (#28) Mod End
//                    }
//                }
//                // 2016/03/04 S21_NA#5000 (#28) Add Start
//                if (duplMachineRmaList.size() > 1) {
//                    for (NWZC150001_rtnDtlPMsg rtnDtlPMsg : duplMachineRmaList) {
//                        NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
//                        NWZC150001Common.setMsgId3(pMsg3, NWZM1921E);
//                        ZYPEZDItemValueSetter.setValue(pMsg3.dsOrdPosnNum, rtnDtlPMsg.dsOrdPosnNum_B1);
//                        ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtnDtlPMsg.cpoDtlLineNum_B1);
//                        ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtnDtlPMsg.cpoDtlLineSubNum_B1);
//                        pMsg3List.add(pMsg3);
//                    }
//                }
//                // 2016/03/04 S21_NA#5000 (#28) Add End
//            }
//        }
        // 2017/10/12 S21_NA#21028 Del End
    }

    private static void otherDetailCheck(//
// 2019/09/27 QC#53593 MOD START
//            NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<MDSETMsg> mdseTMsgList, boolean isCustMdseXrefChk, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) { // QC#14815            
            NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<MDSETMsg> mdseTMsgList, boolean isCustMdseXrefChk, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean, String slsDt) {
// 2019/09/27 QC#53593 MOD END
            
        
        // Del Start 2018/05/10 QC#20343
        //List<NWZC150001_APMsg> dsLineNumByConfig = new ArrayList<NWZC150001_APMsg>(); // S21_NA#4098:add
        // Del End 2018/05/10 QC#20343
        // 2016/08/01 S21_NA#12637 Add Start
        for (int slctConfigIdx = 0; slctConfigIdx < pMsg.cpoConfig.getValidCount(); slctConfigIdx++) {
            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(slctConfigIdx);
            if (!S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configPMsg.configCatgCd.getValue())) {
                continue;
            }
            // Del Start 2018/05/10 QC#20343
            //String dsCpoPosnNum = configPMsg.dsOrdPosnNum.getValue();
            //dsLineNumByConfig.clear();
            //for (int slctLineIdx = 0; slctLineIdx < pMsg.A.getValidCount(); slctLineIdx++) {
            //    if (!S21StringUtil.isEquals(dsCpoPosnNum, pMsg.A.no(slctLineIdx).dsOrdPosnNum_A1.getValue())) {
            //        continue;
            //    }
            //    dsLineNumByConfig.add(pMsg.A.no(slctLineIdx));
            //}
            // Del End 2018/05/10 QC#20343
// 2019/09/27 QC#53593 MOD START
            // Mod Start 2018/05/10 QC#20343
            //otherDetailCheck(pMsg, pMsg2List, mdseTMsgList, configPMsg, dsLineNumByConfig, isCustMdseXrefChk, msgIdMgr, commonBean); // QC#14815
//            otherDetailCheck(pMsg, pMsg2List, mdseTMsgList, configPMsg, isCustMdseXrefChk, msgIdMgr, commonBean); // QC#14815
            otherDetailCheck(pMsg, pMsg2List, mdseTMsgList, configPMsg, isCustMdseXrefChk, msgIdMgr, commonBean, slsDt);
            // Mod End 2018/05/10 QC#20343
// 2019/09/27 QC#53593 MOD END
            // 2018/02/28 S21_NA#24117 del start
            // 2016/11/01 S21_NA#10965 Add Start
//            if (commonBean.getIsRetailEquipOrder()) {
//                otherDetailCheckForRtlEquipOrder(pMsg, pMsg2List, mdseTMsgList, configPMsg, commonBean);
//            }
            // 2016/11/01 S21_NA#10965 Add End
            // 2018/02/28 S21_NA#24117 del end
        }
    }

    private static void otherReturnDetailCheck(//
// 2019/09/27 QC#53593 MOD START
//            NWZC150001PMsg pMsg, List<NWZC150003PMsg> pMsg3List, List<MDSETMsg> mdseTMsgRtrnList, boolean isCustMdseXrefChk, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) { // QC#14815
            NWZC150001PMsg pMsg, List<NWZC150003PMsg> pMsg3List, List<MDSETMsg> mdseTMsgRtrnList, boolean isCustMdseXrefChk, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean, String slsDt) {
// 2019/09/27 QC#53593 MOD START
        List<NWZC150001_rtnDtlPMsg> dsLineNumByConfig = new ArrayList<NWZC150001_rtnDtlPMsg>(); // S21_NA#4098:add

        // 2017/12/21 S21_NA#20050 Add Start
        Map<String, Boolean> ownerCfsMap = new HashMap<String, Boolean>(0);
        // 2017/12/21 S21_NA#20050 Add End

        for (int slctConfigIdx = 0; slctConfigIdx < pMsg.cpoConfig.getValidCount(); slctConfigIdx++) {
            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(slctConfigIdx);
            if (!S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configPMsg.configCatgCd.getValue())) {
                continue;
            }
            String dsCpoPosnNum = configPMsg.dsOrdPosnNum.getValue();
            dsLineNumByConfig.clear();
            for (int slctLineIdx = 0; slctLineIdx < pMsg.rtnDtl.getValidCount(); slctLineIdx++) {
                if (!S21StringUtil.isEquals(dsCpoPosnNum, pMsg.rtnDtl.no(slctLineIdx).dsOrdPosnNum_B1.getValue())) {
                    continue;
                }
                dsLineNumByConfig.add(pMsg.rtnDtl.no(slctLineIdx));
            }
// 2019/09/27 QC#53593 MOD START
//            otherReturnDetailCheck(pMsg, pMsg3List, mdseTMsgRtrnList, configPMsg, dsLineNumByConfig, isCustMdseXrefChk, msgIdMgr, commonBean, ownerCfsMap); // QC#14815, 2017/12/21 S21_NA#20050 Add ownerCfsMap
            otherReturnDetailCheck(pMsg, pMsg3List, mdseTMsgRtrnList, configPMsg, dsLineNumByConfig, isCustMdseXrefChk, msgIdMgr, commonBean, ownerCfsMap, slsDt);
// 2019/09/27 QC#53593 MOD END
        }
    }

    private static void otherSvcExchCheck(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List, S21ApiMessageIdMgr msgIdMgr) {

        // 2018/04/02 S21_NA#25043 Add Start
        if (S21StringUtil.isEquals(MODE_SAVE, pMsg.xxModeCd.getValue())) {
            return;
        }
        // 2018/04/02 S21_NA#25043 Add End
        //QC#14021
        //if (!NWXC150001DsCheck.isSvcExch(glblCmpyCd, pMsg.dsOrdCatgCd.getValue())) {
        if (!NWXC150001DsCheck.isSvcExch(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue())) {
            return;
        }
        
        // 2018/07/02 S21_NA#26354 Add Start
        if (CPO_SRC_TP.CONVERSION_ORACLE.equals(pMsg.cpoSrcTpCd.getValue()) || //
                CPO_SRC_TP.CONVERSION_SAP.equals(pMsg.cpoSrcTpCd.getValue())) {
            return;
        }
        // 2018/07/02 S21_NA#26354 Add End

        List<BigDecimal> inSvcConfigPkList = new ArrayList<BigDecimal>();
        List<NWZC150001_cpoConfigPMsg> inConfigPMsgList = new ArrayList<NWZC150001_cpoConfigPMsg>(); // List Of Service Exchange RAM
        // Map<BigDecimal, BigDecimal> inMdlIdMap = new HashMap<BigDecimal, BigDecimal>(); // 2016/12/27 S21_NA#13768-2 Add => 2018/05/20 S21_NA#25604 Del
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(i);
            if (CONFIG_CATG.INBOUND.equals(configPMsg.configCatgCd.getValue()) //
                    // &&
                    // CONFIG_TP.RETURN_EXISTING_IB.equals(configPMsg.configTpCd.getValue())
                    // //
                    // In bound N Y N
                    && NWXC150001DsCheck.matchConfigTp(pMsg.glblCmpyCd.getValue(), configPMsg.configTpCd.getValue(), CONFIG_CATG.INBOUND, false, true, false) // S21_NA#955
                    && ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)) {
                inSvcConfigPkList.add(configPMsg.svcConfigMstrPk.getValue());
                inConfigPMsgList.add(configPMsg);
//                inMdlIdMap.put(configPMsg.svcConfigMstrPk.getValue(), configPMsg.mdlId.getValue()); // 2016/12/27 S21_NA#13768-2 Add  => 2018/05/20 S21_NA#25604 Del
            }
        }
        // 2017/09/19 QC#21010 Del Start
        //if (inSvcConfigPkList.size() == 0) {
        //    msgIdMgr.addXxMsgId(NWZM1525E, pMsg);
        //    return;
        //}
        // 2017/09/19 QC#21010 Del End

        // 2018/03/27 S21_NA#24867 Add Start
        List<BigDecimal> outSvcConfigPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(i);
            if (CONFIG_CATG.OUTBOUND.equals(configPMsg.configCatgCd.getValue())) {
                outSvcConfigPkList.add(configPMsg.svcConfigMstrPk.getValue());
            }
        }

        for (NWZC150001_cpoConfigPMsg cpoConfigPMsg : inConfigPMsgList) {
            if (NWXC150001DsCheck.matchConfigTp(pMsg.glblCmpyCd.getValue(), cpoConfigPMsg.configTpCd.getValue(), CONFIG_CATG.INBOUND, false, true, false) 
                    && !outSvcConfigPkList.contains(cpoConfigPMsg.svcConfigMstrPk.getValue())) {
                NWZC150001Common.addMsgId3List(pMsg3List, cpoConfigPMsg.dsOrdPosnNum.getValue(), NWZM1480E);
                return;
            }
        }
        // 2018/03/27 S21_NA#24867 Add End

        List<String> rtnMdseList = new ArrayList<String>();
        int ix = 0;
        for (BigDecimal svcConfigMstrPk : inSvcConfigPkList) {
            NWZC150001_cpoConfigPMsg cpoConfigPMsg = inConfigPMsgList.get(ix++);
            for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
                NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
                // 2016/04/22 S21_NA#7449 Add Start
                if (ZYPCommonFunc.hasValue(rtnDtlPMsg.serNum_B1) //
                        && !ZYPCommonFunc.hasValue(rtnDtlPMsg.svcMachMstrPk_B1)) {
                    // 2016/06/02 S21_NA#9273 Modify Start
//                    Map<String, Object> svcMachMstrMap = NWXC150001DsCheck.getSerNumInfo(glblCmpyCd, rtnDtlPMsg.serNum_B1.getValue());
                    Map<String, Object> svcMachMstrMap = NWXC150001DsCheck.getSerNumInfo(//
                            pMsg.glblCmpyCd.getValue()//
                            , rtnDtlPMsg.serNum_B1.getValue()//
                            // 2017/02/22 QC#16575 ADD START
                            , rtnDtlPMsg.mdseCd_B1.getValue() //
                            // 2017/02/22 QC#16575 ADD E N D
                            , rtnDtlPMsg.svcMachMstrPk_B1.getValue());
                    // 2016/06/02 S21_NA#9273 Modify End
                    if (null == svcMachMstrMap) {
                        // 2017/01/19 S21_NA#13768-3 Mod Start
                        // setMsgId(pMsg, NWZM1526E);
                        addMsgId3List(pMsg3List, rtnDtlPMsg, NWZM1526E);
                        // 2017/01/19 S21_NA#13768-3 Mod End
                        return;
                    }
                    ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.svcMachMstrPk_B1, (BigDecimal) svcMachMstrMap.get("SVC_MACH_MSTR_PK"));
                    if (!ZYPCommonFunc.hasValue(rtnDtlPMsg.svcConfigMstrPk_B1)) {
                        ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.svcConfigMstrPk_B1, (BigDecimal) svcMachMstrMap.get("SVC_CONFIG_MSTR_PK"));
                    }
                }
                // 2016/04/22 S21_NA#7449 Add End
                if (cpoConfigPMsg.dsOrdPosnNum.getValue().equals(rtnDtlPMsg.dsOrdPosnNum_B1.getValue())) {
                    // 2018/03/27 S21_NA#24867 Add Start
                    if (!ZYPCommonFunc.hasValue(rtnDtlPMsg.svcMachMstrPk_B1)) {
                        addMsgId3List(pMsg3List, rtnDtlPMsg, NWZM1526E);
                        return;
                    }
                    // 2018/03/27 S21_NA#24867 Add End
                    if (!NWXC150001DsCheck.isExistsSvcConfigMstr(//
                            pMsg.glblCmpyCd.getValue() //
                            , svcConfigMstrPk //
                            , rtnDtlPMsg.svcMachMstrPk_B1.getValue())) {
                        // 2017/01/19 S21_NA#13768-3 Mod Start
                        // setMsgId(pMsg, NWZM1526E);
                        addMsgId3List(pMsg3List, rtnDtlPMsg, NWZM1526E);
                        // 2017/01/19 S21_NA#13768-3 Mod End
                        return;
                    }

                    // S21_NA#13768 ADD START
                    String mdseCd8 = getOrdTakeMdse(pMsg.glblCmpyCd.getValue(), rtnDtlPMsg.mdseCd_B1.getValue());
                    if (mdseCd8 == null) {
                        // Error
                        addMsgId3List(pMsg3List, rtnDtlPMsg, NWZM0622E);
                        return;
                    }
                    // S21_NA#13768 ADD END

                    rtnMdseList.add(mdseCd8);
                }
            }
        }

//        List<NWZC150001_cpoConfigPMsg> outConfigPMsgList = new ArrayList<NWZC150001_cpoConfigPMsg>(); // List of out, which same config ID on RMA 2018/05/20 S21_NA#25604 Del
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(i);
            if (CONFIG_CATG.OUTBOUND.equals(configPMsg.configCatgCd.getValue()) //
//                    && CONFIG_TP.ADD_TO_CONFIG.equals(configPMsg.configTpCd.getValue())) {
                    // Out bound N Y N
                    && NWXC150001DsCheck.matchConfigTp(pMsg.glblCmpyCd.getValue(), configPMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, false, true, false)) { // S21_NA#955
                if (inSvcConfigPkList.contains(configPMsg.svcConfigMstrPk.getValue())) {
//                    outConfigPMsgList.add(configPMsg); 2018/05/20 S21_NA#25604 Del
                    // 2018/05/20 S21_NA#25604 Add Start
                    for (NWZC150001_cpoConfigPMsg rmaConfigPMsg : inConfigPMsgList) {
                        if (isSameBigDecimalObject(configPMsg.svcConfigMstrPk.getValue(), rmaConfigPMsg.svcConfigMstrPk.getValue())) {
                            if (hasMainMachineLineConfig(pMsg, configPMsg) && !hasMainMachineRmaConfig(pMsg, rmaConfigPMsg)) {
                                NWZC150001Common.addMsgId3List(pMsg3List, rmaConfigPMsg.dsOrdPosnNum.getValue(), NWZM2263E);
                            }
                            if (!hasMainMachineLineConfig(pMsg, configPMsg) && hasMainMachineRmaConfig(pMsg, rmaConfigPMsg)) {
                                NWZC150001Common.addMsgId2List(pMsg2List, configPMsg.dsOrdPosnNum.getValue(), NWZM2262E);
                            }
                            break;
                        }
                    }
                    // 2018/05/20 S21_NA#25604 Add End
                } else {
                    // 2019/04/08 S21_NA#31111 Add Start
                    if (NWXC150001DsCheck.hasSvcConfigPkInSameTrx(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue(), CONFIG_CATG.INBOUND, configPMsg.svcConfigMstrPk.getValue())) {
                        continue;
                    }
                    // 2019/04/08 S21_NA#31111 Add End
                    // 2018/04/02 S21_NA#25043 Mod Start
//                    msgIdMgr.addXxMsgId(NWZM1527E, pMsg);
//                    return;
                    NWZC150001Common.addMsgId2List(pMsg2List, configPMsg.dsOrdPosnNum.getValue(), NWZM1527E);
                    // 2018/04/02 S21_NA#25043 Mod End
                }
            }
        }

        // 2018/05/20 S21_NA#25604 Del Start
//        for (NWZC150001_cpoConfigPMsg cpoConfigPMsg : outConfigPMsgList) {
//
//            // 2016/12/27 S21_NA#13768-2 Add Start
//            boolean isMdlErrFlg = false;
//            BigDecimal outMdlId = cpoConfigPMsg.mdlId.getValue();
//            BigDecimal inMdlId = inMdlIdMap.get(cpoConfigPMsg.svcConfigMstrPk.getValue());
//
//            if (outMdlId != null && inMdlId != null) {
//                if (outMdlId.compareTo(inMdlId) == 0) {
//                    continue;
//                } else {
//                    isMdlErrFlg = true;
//                }
//            }
//            // 2016/12/27 S21_NA#13768-2 Add End
//
//            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
//                NWZC150001_APMsg aPMsg = pMsg.A.no(i);
//                if (cpoConfigPMsg.dsOrdPosnNum.getValue().equals(aPMsg.dsOrdPosnNum_A1.getValue())) {
//
//                    // S21_NA#13768 ADD START
//                    String mdseCd8 = getOrdTakeMdse(pMsg.glblCmpyCd.getValue(), aPMsg.mdseCd_A1.getValue());
//                    if (mdseCd8 == null) {
//                        // Error
//                        NWZC150001Common.addMsgId2List(pMsg2List, aPMsg, NWZM0622E);
//                        return;
//                    }
//                    // S21_NA#13768 ADD END
//
//                    if (!rtnMdseList.contains(mdseCd8)) {
//                        // 2016/12/27 S21_NA#13768-2 Mod Start
////                        setMsgId(pMsg, NWZM1528E);
////                        return;
//                        if (isMdlErrFlg) {
//                            msgIdMgr.addXxMsgId(NWZM2054E, pMsg);
//                            return;
//                        } else {
//                            msgIdMgr.addXxMsgId(NWZM1528E, pMsg);
//                            return;
//                        }
//                        // 2016/12/27 S21_NA#13768-2 Mod End
//                    }
//                }
//            }
//        }
        // 2018/05/20 S21_NA#25604 Del End

        // 2016/08/08 S21_NA#8300 Del Start
//        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
//            NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
//            if (NWXC150001DsCheck.checkRtnMMOrdTpAndSvcExchRsnRelation(glblCmpyCd, rtnDtlPMsg.svcMachMstrPk_B1.getValue(), pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue())) {
//                setMsgId(pMsg, NWZM1529E);
//                return;
//            }
//        }
        // 2016/08/08 S21_NA#8300 Del End

    }

    private static void otherQtyCheck(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<MDSETMsg> mdseTMsgList) {

        // 2016/02/17 S21_NA#1703 Del Start
        // List<String> coaMdseTpList = NWZC150001Query.getInstance().getCoaMdseTpList(pMsg);
        // String coaMdseTpCd = "";
        // 2016/02/17 S21_NA#1703 Del End

        // 2016/06/17 S21_NA#9679 Add Start
        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        dsOrdTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(dsOrdTpProcDfnTMsg);
        boolean isExtrnlOrd = true;
        if (null != dsOrdTpProcDfnTMsg) {
            ORD_PROC_TPTMsg ordProcTpTMsg = new ORD_PROC_TPTMsg();
            ZYPEZDItemValueSetter.setValue(ordProcTpTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ordProcTpTMsg.ordProcTpCd, dsOrdTpProcDfnTMsg.ordProcTpCd);
            ordProcTpTMsg = (ORD_PROC_TPTMsg) S21CacheTBLAccessor.findByKey(ordProcTpTMsg);
            if (null != ordProcTpTMsg) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, ordProcTpTMsg.itrlOrdProcFlg.getValue())) {
                    isExtrnlOrd = false;
                }
            }
        }
        // 2016/06/17 S21_NA#9679 Add End
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            MDSETMsg mdseTMsg = mdseTMsgList.get(i);

            // 2016/06/07 For Cr/Dr Logic Add Start
            boolean crOrd = S21StringUtil.isEquals(CR_REBIL.CREDIT, aPMsg.crRebilCd_A1.getValue());
            BigDecimal absOrdQty = aPMsg.ordQty_A1.getValue();
            if (crOrd) {
                absOrdQty = absOrdQty.abs();
            }
            // 2016/06/07 For Cr/Dr Logic Add End

            // 2016/02/17 S21_NA#1703 Mod Start
            // coaMdseTpCd = NWXC150001DsCheck.getCoaMdseTp(glblCmpyCd, mdseTMsg.mdseCd.getValue());
            // if (NWXC150001DsCheck.checkMachQty(coaMdseTpList, coaMdseTpCd, aPMsg.ordQty_A1.getValue())) {
            //     setMsgId2(pMsg2, NWZM1483E);
            // }
            // 2016/06/07 For Cr/Dr Logic Mod Start aPMsg.ordQty_A1.getValueInt() -> absOrdQty
            //QC#14021
            //if (NWXC150001DsCheck.chekcQtyForSerializedItem(glblCmpyCd, pMsg.dsOrdCatgCd.getValue(), aPMsg.mdseCd_A1.getValue(), absOrdQty.intValue())) {
            if (NWXC150001DsCheck.chekcQtyForSerializedItem(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdRsnCd.getValue(), aPMsg.mdseCd_A1.getValue(), absOrdQty.intValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1917E);
            }
            // 2016/02/17 S21_NA#1703 Mod End

            if (NWXC150001DsCheck.checkSerialQty(aPMsg.serNum_A1.getValue(), absOrdQty)) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1484E);
            }
            if (NWXC150001DsCheck.checkLicenseItemQty(mdseTMsg.thirdPtyVndDropFlg.getValue(), absOrdQty)) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1485E);
            }
            // 2016/06/13 For Cr/Dr QC#9649 Start
            //if (NWXC150001DsCheck.checkMinusQty(aPMsg.crRebilCd_A1.getValue(), aPMsg.ordQty_A1.getValue())) {
            //    setMsgId2(pMsg2, NWZM1486E);
            //}
            // 2016/06/13 For Cr/Dr QC#9649 End

            // if set component then, skip this check.
            if (!ZYPCommonFunc.hasValue(aPMsg.dsCpoLineSubNum_A1)) {
                if (NWXC150001DsCheck.checkChangeQty(//
                        pMsg.glblCmpyCd.getValue() //
                        , pMsg.cpoOrdNum.getValue() //
                        , aPMsg.cpoDtlLineNum_A1.getValue() //
                        , aPMsg.cpoDtlLineSubNum_A1.getValue() //
                        , absOrdQty)) {
                    NWZC150001Common.setMsgId2(pMsg2, NWZM1487E);
                }
            }
            if (isExtrnlOrd) { // 2016/06/17 S21_NA#9679
                // 2019/01/08 QC#29241 Mod Start
                if (!ZYPCommonFunc.hasValue(aPMsg.crRebilCd_A1)
                        && NWXC150001DsCheck.checkOrdQtyVldFlg(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdTpCd.getValue(), aPMsg.dsOrdLineCatgCd_A1.getValue(), pMsg.slsDt.getValue())) {
                    if (NWXC150001DsCheck.checkMinOrdQty(mdseTMsg.cpoMinOrdQty.getValue(), absOrdQty)) {
                        NWZC150001Common.setMsgId2(pMsg2, NWZM1488E);
                    }
                    if (NWXC150001DsCheck.checkMaxOrdQty(mdseTMsg.cpoMaxOrdQty.getValue(), absOrdQty)) { // S21_NA#1405
                        NWZC150001Common.setMsgId2(pMsg2, NWZM1489E);
                    }
                    if (NWXC150001DsCheck.checkIncrOrdQty(mdseTMsg.cpoIncrOrdQty.getValue(), absOrdQty)) {
                        NWZC150001Common.setMsgId2(pMsg2, NWZM1492E);
                    }
                }
                // 2019/01/08 QC#29241 Mod End
            // 2016/06/07 For Cr/Dr Logic Mod End aPMsg.ordQty_A1.getValueInt() -> absOrdQty 
            } // 2016/06/17 S21_NA#9679

            if (S21ApiUtil.isXxMsgId(pMsg2)) {
                ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, aPMsg.dsOrdPosnNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                pMsg2List.add(pMsg2);
            }
        }
    }

    private static void otherQtyCheckForReturn(NWZC150001PMsg pMsg, List<NWZC150003PMsg> pMsg3List) {
        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);

            if (BigDecimal.ZERO.compareTo(rtnDtlPMsg.ordQty_B1.getValue()) <= 0) {
                addMsgId3List(pMsg3List, rtnDtlPMsg, NWZM1596E); // S21_NA#2189
            }
        }
    }

    private static void otherEasyPackCheck(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<MDSETMsg> mdseTMsgList) {
        if (NWXC150001DsCheck.isEasyPack(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue())) {
            Set<String> setCompSet = new HashSet<String>(); // Add S21_NA#17149(Sol#259) 
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                NWZC150001_APMsg aPMsg = pMsg.A.no(i);
                // Add Start S21_NA#17149(Sol#259) 
                if (setCompSet.contains(aPMsg.cpoDtlLineNum_A1.getValue())) {
                    continue;
                }
                setCompSet.add(aPMsg.cpoDtlLineNum_A1.getValue());
                // Add End S21_NA#17149(Sol#259) 
                MDSETMsg tMsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseTMsgList.get(i).mdseCd.getValue());
                tMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
                if (tMsg == null //
                        || !ZYPCommonFunc.hasValue(tMsg.easyPackTpCd)) {
                    NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                    NWZC150001Common.setMsgId2(pMsg2, NWZM1530E);
                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                    pMsg2List.add(pMsg2);
                    continue;
                }
                if (!NWXC150001DsCheck.isSplyPgmContr(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.billToCustAcctCd.getValue(), pMsg.billToCustCd.getValue())) {
                    NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                    NWZC150001Common.setMsgId2(pMsg2, NWZM2008E); // Mod 2016/09/15 S21_NA#13918 NWZM1532E -> NWZM2008E
                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                    pMsg2List.add(pMsg2);
                }
            }

            //        } else { 2016/04/22 S21_NA#7132 Del Start
            //            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            //                NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            //                DS_MDSE_INFOTMsg tMsg = new DS_MDSE_INFOTMsg();
            //                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            //                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseTMsgList.get(i).mdseCd.getValue());
            //                tMsg = (DS_MDSE_INFOTMsg) S21CacheTBLAccessor.findByKey(tMsg);
            //                if (tMsg == null) {
            //                    continue;
            //                }
            //                if (ZYPCommonFunc.hasValue(tMsg.easyPackTpCd)) {
            //                    NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
            //                    setMsgId2(pMsg2, NWZM1531E);
            //                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
            //                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
            //                    pMsg2List.add(pMsg2);
            //                }
            //            } 2016/04/22 S21_NA#7132 Del End
        }
    }

    private static void otherEasyPackCheckForReturn(NWZC150001PMsg pMsg, List<NWZC150003PMsg> pMsg3List, List<MDSETMsg> mdseTMsgList) {
        if (NWXC150001DsCheck.isEasyPack(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue())) {
            Set<String> setCompSet = new HashSet<String>(); // Add S21_NA#17149(Sol#259) 
            for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
                NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
                // Add Start S21_NA#17149(Sol#259) 
                if (setCompSet.contains(rtnDtlPMsg.cpoDtlLineNum_B1.getValue())) {
                    continue;
                }
                setCompSet.add(rtnDtlPMsg.cpoDtlLineNum_B1.getValue());
                // Add End S21_NA#17149(Sol#259) 
                MDSETMsg tMsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseTMsgList.get(i).mdseCd.getValue());
                tMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
                if (tMsg == null //
                        || !ZYPCommonFunc.hasValue(tMsg.easyPackTpCd)) {
                    NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
                    NWZC150001Common.setMsgId3(pMsg3, NWZM1530E);
                    ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtnDtlPMsg.cpoDtlLineNum_B1);
                    ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtnDtlPMsg.cpoDtlLineSubNum_B1);
                    pMsg3List.add(pMsg3);
                    continue;
                }
                if (!NWXC150001DsCheck.isSplyPgmContr(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.billToCustAcctCd.getValue(), pMsg.billToCustCd.getValue())) {
                    NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
                    NWZC150001Common.setMsgId3(pMsg3, NWZM2008E); // Mod 2016/09/15 S21_NA#13918 NWZM1532E -> NWZM2008E
                    ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtnDtlPMsg.cpoDtlLineNum_B1);
                    ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtnDtlPMsg.cpoDtlLineSubNum_B1);
                    pMsg3List.add(pMsg3);
                }
            }

            //        } else { 2016/04/22 S21_NA#7132 Del Start
            //            for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            //                NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
            //                DS_MDSE_INFOTMsg tMsg = new DS_MDSE_INFOTMsg();
            //                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            //                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseTMsgList.get(i).mdseCd.getValue());
            //                tMsg = (DS_MDSE_INFOTMsg) S21CacheTBLAccessor.findByKey(tMsg);
            //                if (ZYPCommonFunc.hasValue(tMsg.easyPackTpCd)) {
            //                    NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
            //                    setMsgId3(pMsg3, NWZM1531E);
            //                    ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtnDtlPMsg.cpoDtlLineNum_B1);
            //                    ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtnDtlPMsg.cpoDtlLineSubNum_B1);
            //                    pMsg3List.add(pMsg3);
            //                }
            //            } 2016/04/22 S21_NA#7132 Del End
        }
    }

    // 2018/06/14 S21_NA#24294 Mod Start
    // private static void otherMdseStsCheck(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, Map<Map<String, String>, Map<String, String>> mdseInfoCacheMap) {
    private static void otherMdseStsCheck(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, Map<String, Map<String, String>> mdseItemFlagInfoMap) {
       // 2018/06/14 S21_NA#24294 Mod End
        Map<Map<String, String>, Map<String, String>> ordProcTpInfoCacheMap = new HashMap<Map<String, String>, Map<String, String>>();
        Map<Map<String, String>, BigDecimal> costPctInfoCacheMap = new HashMap<Map<String, String>, BigDecimal>();
        // mdseItemFlagInfoMap = NWZC150001Query.getInstance().getMdseItemStsFlagInfo(pMsg.glblCmpyCd.getValue());
        mdseItemFlagInfoMap = NWZC150001Query.getInstance().getMdseItemStsFlagInfo(pMsg.glblCmpyCd.getValue(), mdseItemFlagInfoMap);
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            // 2018/06/14 S21_NA#24294 Mod Start
            MDSETMsg mdseTmsg = NWXMdseTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), aPMsg.mdseCd_A1.getValue());
            // Map<String, String> mdseInfoMap //
            // = NWZC150001Query.getInstance().getMdseItemStsInfo(pMsg.glblCmpyCd.getValue(), aPMsg.mdseCd_A1.getValue(), mdseInfoCacheMap);
            // 2018/06/14 S21_NA#24294 Mod End
            Map<String, String> ordProcTpInfoMap //
            = NWZC150001Query.getInstance().getOrdProcTpInfo(//
                    pMsg.glblCmpyCd.getValue() //
                    , pMsg.dsOrdTpCd.getValue() //
                    , aPMsg.dsOrdLineCatgCd_A1.getValue() //
                    , pMsg.slsDt.getValue() //
                    , ordProcTpInfoCacheMap);

            // 2018/06/14 S21_NA#24294 Mod Start
            // if (ordProcTpInfoMap == null || mdseInfoMap == null) {
            if(ordProcTpInfoMap == null || !mdseItemFlagInfoMap.containsKey(mdseTmsg.mdseItemStsCd.getValue())) {
                // 2018/06/14 S21_NA#24294 Mod End
                NWZC150001Common.addMsgId2List(pMsg2List, aPMsg, NWAM0037E);
                continue;
            }
            Map<String, String> mdseItemFlagInfo = mdseItemFlagInfoMap.get(mdseTmsg.mdseItemStsCd.getValue());
            if (ZYPConstant.FLG_ON_Y.equals((String) ordProcTpInfoMap.get("ITRL_ORD_PROC_FLG"))) {
                continue;
            }
            // 2019/01/08 QC#29753 Add Start
            if(ZYPConstant.FLG_OFF_N.equals((String) mdseItemFlagInfo.get("PRCH_AVAL_FLG"))
                    && NWXC150001DsCheck.checkOrdLineSrcCatg(pMsg.glblCmpyCd.getValue(), aPMsg.ordLineSrcCd_A1.getValue(), ORD_LINE_SRC_CATG.EXTERNAL)) {
                NWZC150001Common.addMsgId2List(pMsg2List, aPMsg, NWAM0965E);
                continue;
            }
            // 2019/01/08 QC#29753 Add End
            if (ZYPConstant.FLG_ON_Y.equals((String) ordProcTpInfoMap.get("CUST_ORD_PROC_FLG"))) {

                // 2016/04/05 S21_NA#6568 Add Start
                // Dummy WH
                if (!ZYPCommonFunc.hasValue(aPMsg.rtlSwhCd_A1)) {
                    continue;
                }
                // 2016/04/05 S21_NA#6568 Add End

                BigDecimal costPct //
                = NWZC150001Query.getInstance().getCostPctInfo(//
                        pMsg.glblCmpyCd.getValue() //
                        , aPMsg.rtlSwhCd_A1.getValue() //
                        , pMsg.slsDt.getValue() //
                        , costPctInfoCacheMap);

                if (PCT_100.compareTo(costPct) == 0) {
                    // 2018/06/14 S21_NA#24294 Mod Start
                    // if (ZYPConstant.FLG_ON_Y.equals((String) mdseInfoMap.get("CUST_ORD_ENTRY_AVAL_FLG"))) {
                    if (ZYPConstant.FLG_ON_Y.equals(mdseItemFlagInfo.get("CUST_ORD_ENTRY_AVAL_FLG"))) {
                    // 2018/06/14 S21_NA#24294 Mod End
                        continue;
                    }
                } else {
                    // 2018/06/14 S21_NA#24294 Mod Start
                    // if (ZYPConstant.FLG_ON_Y.equals((String) mdseInfoMap.get("USED_ONLY_AVAL_FLG"))) {
                    if (ZYPConstant.FLG_ON_Y.equals(mdseItemFlagInfo.get("USED_ONLY_AVAL_FLG"))) {
                    // 2018/06/14 S21_NA#24294 Mod End
                        continue;
                    }
                }
                NWZC150001Common.addMsgId2List(pMsg2List, aPMsg, NWAM0037E);
            }
            if (ZYPConstant.FLG_ON_Y.equals((String) ordProcTpInfoMap.get("WS_ORD_PROC_FLG"))) {
                // 2018/06/14 S21_NA#24294 Mod Start
                // if (ZYPConstant.FLG_ON_Y.equals((String) mdseInfoMap.get("WS_ORD_ENTRY_AVAL_FLG"))) {
                if (ZYPConstant.FLG_ON_Y.equals(mdseItemFlagInfo.get("WS_ORD_ENTRY_AVAL_FLG"))) {
                // 2018/06/14 S21_NA#24294 Mod End
                    continue;
                }
                NWZC150001Common.addMsgId2List(pMsg2List, aPMsg, NWAM0037E);
            }
            // 2017/03/14 S21_NA#16987 Add Start
            // Mod Start 2017/10/30 QC#22140
//            if (aPMsg.mdseCd_A1.getValue() != null) {
            if (aPMsg.mdseCd_A1.getValue() != null && !(CPO_SRC_TP.CREDIT.equals(pMsg.cpoSrcTpCd.getValue()) || CPO_SRC_TP.REBILL.equals(pMsg.cpoSrcTpCd.getValue()))) {
                // 2018/06/14 S21_NA#24294 Mod Start
                // ALL_MDSE_VTMsg allMdseVTMsg = getMdseTMsg(pMsg.glblCmpyCd.getValue(), aPMsg.mdseCd_A1.getValue());
                // if (allMdseVTMsg != null) {
                    // if (ZYPConstant.FLG_ON_Y.equals(allMdseVTMsg.sellHldFlg.getValue()) || ZYPConstant.FLG_OFF_N.equals(allMdseVTMsg.custOrdEnblFlg.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(mdseTmsg.sellHldFlg.getValue()) || ZYPConstant.FLG_OFF_N.equals(mdseTmsg.custOrdEnblFlg.getValue())) {
                        NWZC150001Common.addMsgId2List(pMsg2List, aPMsg, NWAM0037E);
                        continue;
                    }
                // }
                // 2018/06/14 S21_NA#24294 Mod End
            }
            // Mod End 2017/10/30 QC#22140
            // 2017/03/14 S21_NA#16987 Add End
            
            // 2018/08/24 S21_NA#27840 Add Start
            if(aPMsg.mdseCd_A1.getValue() != null && ZYPConstant.FLG_OFF_N.equals((String) mdseItemFlagInfo.get("PRCH_AVAL_FLG"))//
                    && aPMsg.rtlWhCd_A1.getValue() != null && ZYPCodeDataUtil.getVarCharConstValue(DROP_SHIP_WH_CD, pMsg.glblCmpyCd.getValue()).equals(aPMsg.rtlWhCd_A1.getValue())){
                NWZC150001Common.addMsgId2List(pMsg2List, aPMsg, NWZM2277E);
            }
            // 2018/08/24 S21_NA#27840 Add End
        }
    }

    // 2018/06/14 S21_NA#24294 Mod Start
    // private static void otherMdseStsCheckForReturn(NWZC150001PMsg pMsg, List<NWZC150003PMsg> pMsg3List, Map<Map<String, String>, Map<String, String>> mdseInfoCacheMap) {
    private static void otherMdseStsCheckForReturn(NWZC150001PMsg pMsg, List<NWZC150003PMsg> pMsg3List, Map<String, Map<String, String>> mdseItemFlagInfoMap) {
    // 2018/06/14 S21_NA#24294 Mod End

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {

            NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
            // 2018/06/14 S21_NA#24294 Mod Start
            // Map<String, String> mdseInfoMap //
            // = NWZC150001Query.getInstance().getMdseItemStsInfo(pMsg.glblCmpyCd.getValue(), rtnDtlPMsg.mdseCd_B1.getValue(), mdseInfoCacheMap);
            MDSETMsg mdseTmsg = NWXMdseTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), rtnDtlPMsg.mdseCd_B1.getValue());
            Map<String, String> mdseItemFlagInfo = mdseItemFlagInfoMap.get(mdseTmsg.mdseItemStsCd.getValue());
            // if (ZYPConstant.FLG_ON_Y.equals((String) mdseInfoMap.get("CUST_RTRN_AVAL_FLG"))) {
            if (ZYPConstant.FLG_ON_Y.equals(mdseItemFlagInfo.get("CUST_RTRN_AVAL_FLG"))) {
            // 2018/06/14 S21_NA#24294 Mod End
                continue;
            }
            addMsgId3List(pMsg3List, rtnDtlPMsg, NWZM1507E);
        }
    }

    /**
     * QC#14815
     * getCustMdseXrefFlg
     * @param pMsg  NWZC150001PMsg
     * @return  CPO_SRC_TP.CUST_MDSE_XREF_FLG
     */
    private static String getCustMdseXrefFlg(NWZC150001PMsg pMsg) {
        CPO_SRC_TPTMsg tMsg = new CPO_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoSrcTpCd, pMsg.cpoSrcTpCd);

        tMsg = (CPO_SRC_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return null;
        }
        return tMsg.custMdseXrefChkFlg.getValue();
    }

    /**
     * <pre>
     * @param pMsg3List     pMsg3List
     * @param rtnDtlPMsg    rtnDtlPMsg
     * @param msgId         msgId
     * </pre>
     */
    private static void addMsgId3List(List<NWZC150003PMsg> pMsg3List, NWZC150001_rtnDtlPMsg rtnDtlPMsg, String msgId) {
        NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
        NWZC150001Common.setMsgId3(pMsg3, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtnDtlPMsg.cpoDtlLineNum_B1);
        ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtnDtlPMsg.cpoDtlLineSubNum_B1);
        pMsg3List.add(pMsg3);
    }

    private static void checkBillSellShipRelation(NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) {
        if (NWXC150001DsCheck.checkBillToRalation(pMsg.glblCmpyCd.getValue(), pMsg.billToCustCd.getValue(), pMsg.billToCustAcctCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1452E, pMsg);
        }
        if (NWXC150001DsCheck.checkShipToRalation(pMsg.glblCmpyCd.getValue(), pMsg.addShipToCustCd.getValue(), pMsg.shipToCustAcctCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1453E, pMsg);
        }
        if (NWXC150001DsCheck.checkSoldToRalation(pMsg.glblCmpyCd.getValue(), pMsg.soldToCustLocCd.getValue(), pMsg.sellToCustCd.getValue())) {
            msgIdMgr.addXxMsgId(NWZM1454E, pMsg);
        }

        // Mod Start 2018/02/26 QC#22967
        //if (checkBillShipSoldRelation(pMsg, msgIdMgr, commonBean)) {
        //    msgIdMgr.addXxMsgId(NWZM1455E, pMsg);
        //}
        checkBillShipSoldRelation(pMsg, msgIdMgr, commonBean);
        // Mod End 2018/02/26 QC#22967
    }

    // Mod Start 2018/02/26 QC#22967
    ///**
    // * checkBillShipSoldRelation
    // * @param pMsg NWZC150001PMsg
    // * @param msgIdMgr S21ApiMessageIdMgr
    // * @param commonBean NWZC150001DsCpoCommonBean
    // * @return boolean if error then return true.
    // */
    //private static boolean checkBillShipSoldRelation(NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) {
    /**
     * checkBillShipSoldRelation
     * @param pMsg NWZC150001PMsg
     * @param msgIdMgr S21ApiMessageIdMgr
     * @param commonBean NWZC150001DsCpoCommonBean
     */
    private static void checkBillShipSoldRelation(NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) {
        // Mod End 2018/02/26 QC#22967

        // Add Start 2018/02/26 QC#22967
        // Check Sold To(Customer Code) <- Ship To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation( //
                pMsg.soldToCustLocCd.getValue(), pMsg.shipToCustAcctCd.getValue(), //
                pMsg, msgIdMgr, commonBean.getOnBatchType(), NWZM2254E);

        // Check Bill To(Customer Code) <- Sold To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation( //
                pMsg.billToCustCd.getValue(), pMsg.sellToCustCd.getValue(), //
                pMsg, msgIdMgr, commonBean.getOnBatchType(), NWZM2255E);
        // Add End 2018/02/26 QC#22967

        // Del Start 2018/02/26 QC#22967
//        NMZC610001PMsg custInfoGetApiMsg //
//        = NWXC150001DsCheck.callCustInfoGetApi(//
//                pMsg.glblCmpyCd.getValue() //
//                , pMsg.billToCustCd.getValue() //
////                , pMsg.addShipToCustCd.getValue() // 2017/06/06 S21_NA#18459 Del
//                , pMsg.sellToCustCd.getValue() // S21_NA#17251 Del -> 2017/06/06 S21_NA#18459 Add
//                , pMsg.shipToCustAcctCd.getValue() // S21_NA#17251 Add
//                , commonBean.getOnBatchType());
//        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
//            List<String> ml = S21ApiUtil.getXxMsgIdList(custInfoGetApiMsg);
//            for (String msgId : ml) {
//                msgIdMgr.addXxMsgId(msgId, pMsg);
//            }
//            for (int ix = 0; ix < custInfoGetApiMsg.xxMsgIdList.getValidCount(); ix++) {
//                EZDDebugOutput.println(1, //
//                        S21MessageFunc.clspGetMessage(//
//                                custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgId.getValue() //
//                                , new String[] {custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_0.getValue() //
//                                        , custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_1.getValue() //
//                                        , custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_2.getValue() //
//                                }), NWZC150001ForOtherCheck.class);
//            }
//            return true;
//        }
//        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
//            NMZC610001_EligibleCheckListPMsg eligiblePMsg //
//            = custInfoGetApiMsg.EligibleCheckList.no(i);
//            // S21_NA#8372 MOD
//            // 2017/06/06 S21_NA#18459 Mod Start
////            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
////                    || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
//            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
//                    || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
//                // 2017/06/06 S21_NA#18459 Mod End
//                return true;
//            }
//        }
//        return false;
        // Del End 2018/02/26 QC#22967
    }

    // Add Start 2018/02/26 QC#22967
    /**
     * @param billToCustCd String
     * @param acctNum String
     * @param pMsg NWZC150001PMsg
     * @param msgIdMgr S21ApiMessageIdMgr
     * @param onBatchType ONBATCH_TYPE
     * @param errMsgId String
     */
    private static void callCustInfoGetApiForCheckRelation(String billToCustCd, String acctNum, NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr, ONBATCH_TYPE onBatchType, String errMsgId) {
        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApiBillTo( //
                pMsg.glblCmpyCd.getValue(), billToCustCd, acctNum, onBatchType);

        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
            List<String> ml = S21ApiUtil.getXxMsgIdList(custInfoGetApiMsg);
            for (String msgId : ml) {
                msgIdMgr.addXxMsgId(msgId, pMsg);
            }
            msgIdMgr.addXxMsgId(errMsgId, pMsg);
        }

        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);
            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue())) {
                msgIdMgr.addXxMsgId(errMsgId, pMsg);
            }
        }
    }
    // Add End 2018/02/26 QC#22967

    /**
     * <pre>
     * @param pMsg          NWZC150001PMsg
     * @param modeCd        modeCd
     * @param prcCtxTpCd    prcCtxTpCd
     * @param prcCatgOpeFlg prcCatgOpeFlg(when check then set to "Y")
     * @param localCache    NWZC150001CpouLocalCache
     * @param commonBean    NWZC150001DsCpoCommonBean
     * @return  executed result of NWZC157001PMsg
     * </pre>
     */
    private static NWZC157001PMsg callPricingApiForGetPrcList(NWZC150001PMsg pMsg, String modeCd, String prcCtxTpCd, String prcCatgOpeFlg, NWZC150001CpouLocalCache localCache, NWZC150001DsCpoCommonBean commonBean) {
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, modeCd);
        // QC#9437 2016/06/21 Mod Start
        // if (ZYPCommonFunc.hasValue(pMsg.prcBaseDt)) {
        //     ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, pMsg.prcBaseDt);
        // } else {
        //     ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, slsDt);
        // }
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, pMsg.slsDt.getValue());
        // QC#9437 2016/06/21 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, prcCtxTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, pMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd //
                , NWXC150001DsCheck.getLineBizTpCd(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.dsOrdTpCd.getValue())); //QC#4212 Modify
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, pMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.csmpNum, pMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dlrRefNum, pMsg.dlrRefNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcContrNum, pMsg.prcContrNum);
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, NWZC150001.getCoaBrCd(pMsg.slsRepCd.getValue()));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd
                , NWZC150001Common.getCoaBrCd(pMsg.glblCmpyCd.getValue(), pMsg.slsRepCd.getValue(), localCache));
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, prcCatgOpeFlg);

        new NWZC157001().execute(prcApiPMsg, commonBean.getOnBatchType());
        return prcApiPMsg;
    }

    /**
     * checkIncludePrcCatg
     * @param xxPrcList
     * @param value
     * @return if not include then return true.
     */
    private static boolean checkIncludePrcCatg(NWZC157001_xxPrcListPMsgArray xxPrcList, String value) {
        for (int i = 0; i < xxPrcList.getValidCount(); i++) {
            if (value.equals(xxPrcList.no(i).prcCatgCd.getValue())) {
                return false;
            }
        }
        return true;
    }

    private static void checkConfigBillSellShip(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configPMsg, NWZC150002PMsg pMsg2, NWZC150003PMsg pMsg3, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) {
        if (NWXC150001DsCheck.checkBillToRalation(pMsg.glblCmpyCd.getValue(), configPMsg.billToCustCd.getValue(), configPMsg.billToCustAcctCd.getValue())) {
            // Mod Start 2018/02/26 QC#22967
            //NWZC150001Common.setMsgId2(pMsg2, NWZM1452E);
            NWZC150001Common.setMsgIdForConfig(configPMsg, pMsg2, pMsg3, NWZM1452E);
            // Mod End 2018/02/26 QC#22967
        }
        if (NWXC150001DsCheck.checkShipToRalation(pMsg.glblCmpyCd.getValue(), configPMsg.shipToCustCd.getValue(), configPMsg.shipToCustAcctCd.getValue())) {
            // Mod Start 2018/02/26 QC#22967
            //NWZC150001Common.setMsgId2(pMsg2, NWZM1453E);
            NWZC150001Common.setMsgIdForConfig(configPMsg, pMsg2, pMsg3, NWZM1453E);
            // Mod End 2018/02/26 QC#22967
        }

        // Add Start 2018/02/26 QC#22967
        // Check Sold To(Customer Code) <- Ship To(Account Number)
        // relation.
        callCustInfoGetApiForCheckConfigRelation( //
                pMsg.soldToCustLocCd.getValue(), configPMsg.shipToCustAcctCd.getValue(), //
                pMsg, configPMsg, pMsg2, pMsg3, msgIdMgr, commonBean.getOnBatchType(), NWZM2254E);

        // Check Bill To(Customer Code) <- Sold To(Account Number)
        // relation.
        callCustInfoGetApiForCheckConfigRelation( //
                configPMsg.billToCustCd.getValue(), pMsg.sellToCustCd.getValue(), //
                pMsg, configPMsg, pMsg2, pMsg3, msgIdMgr, commonBean.getOnBatchType(), NWZM2255E);
        // Add End 2018/02/26 QC#22967

        // Del Start 2018/02/26 QC#22967
        //NMZC610001PMsg rsltPMsg = NWXC150001DsCheck.callCustInfoGetApi(//
        //        pMsg.glblCmpyCd.getValue() //
        //        , configPMsg.billToCustCd.getValue() //
////                , configPMsg.shipToCustCd.getValue() // 2017/06/06 S21_NA#18459 Del
        //        , pMsg.sellToCustCd.getValue() // S21_NA#17251 Del -> 2017/06/06 S21_NA#18459 Add
        //        , configPMsg.shipToCustAcctCd.getValue() // S21_NA#17251 Add
        //        , commonBean.getOnBatchType());
        //if (S21ApiUtil.isXxMsgId(rsltPMsg)) {
        //    copyMsgId(pMsg, S21ApiUtil.getXxMsgIdList(rsltPMsg), msgIdMgr);
        //    NWZC150001Common.setMsgId2(pMsg2, NWZM1455E);
        //    for (int ix = 0; ix < rsltPMsg.xxMsgIdList.getValidCount(); ix++) {
        //        EZDDebugOutput.println(1, //
        //                S21MessageFunc.clspGetMessage(//
        //                        rsltPMsg.xxMsgIdList.no(ix).xxMsgId.getValue() //
        //                        , new String[] {rsltPMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_0.getValue() //
        //                                , rsltPMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_1.getValue() //
        //                                , rsltPMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_2.getValue() //
        //                        }), NWZC150001ForOtherCheck.class);
        //    }
        //}
        // Del End 2018/02/26 QC#22967

        // 2016/02/25 S21_NA#4440 Add Start
        // Del Start 2018/02/26 QC#22967
        //for (int elgChkCnt = 0; elgChkCnt < rsltPMsg.EligibleCheckList.getValidCount(); elgChkCnt++) {
        //    NMZC610001_EligibleCheckListPMsg eligiblePMsg = rsltPMsg.EligibleCheckList.no(elgChkCnt);
        //    // 2017/06/06 S21_NA#18459 Mod Start
        //    // S21_NA#8372 MOD
////            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
////                    || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
        //    if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
        //            || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
        //        // 2017/06/06 S21_NA#18459 Mod End
        //        NWZC150001Common.setMsgId2(pMsg2, NWZM1455E);
        //    }
        //}
        // Del End 2018/02/26 QC#22967
        // 2016/02/25 S21_NA#4440 Add End

        // 2017/03/09 S21_NA#17979 Del Start This section was moved into the setDefaultConfigIdByEveryConfig()
//        if (NWXC150001DsCheck.checkConfigIdEssential(glblCmpyCd, configPMsg.configTpCd.getValue(), configPMsg.svcConfigMstrPk.getValue())) {
//            // 2016/03/16 S21_NA#5519 Mod Start
//            // setMsgId2(pMsg2, NWZM1466E);
//            if (CONFIG_CATG.OUTBOUND.equals(configPMsg.configCatgCd.getValue())) {
//                getDtlConfigId(pMsg, configPMsg);
//                if (!ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)) {
//                    setMsgId2(pMsg2, NWZM1466E);
//                }
//            } else if (CONFIG_CATG.INBOUND.equals(configPMsg.configCatgCd.getValue())) {
//                // 2016/10/04 S21_NA#9215 Add Start
//                boolean isRetailEquipmentOrder = isExistOrdCatgFromCache(pMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);
//                // 2016/10/04 S21_NA#9215 Add END
//                // 2016/10/04 S21_NA#9215 Add Start
//                if (isRetailEquipmentOrder) { // 2016/10/04 S21_NA#9215 Add End
//                    getRtrnConfigId(pMsg, configPMsg);
//                    if (!ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)) {
//                        setMsgId3(pMsg3, NWZM1466E);
//                    }
//                } // 2016/10/04 S21_NA#9215 Add
//            }
//            // 2016/03/16 S21_NA#5519 Mod End
//        }
        // 2017/03/09 S21_NA#17979 Del End This section was moved into the setDefaultConfigIdByEveryConfig()
        // 2018/03/15 S21_NA#24253 Del Start
        // 2017/11/10 S21_NA#22478 Add Start
        // boolean isCreditOrder = S21StringUtil.isEquals(CPO_SRC_TP.CREDIT, pMsg.cpoSrcTpCd.getValue());
        // boolean isAllDtlRebill = NWZC150001Common.isAllDetailRebillConfig(configPMsg, pMsg);
        // 2017/11/10 S21_NA#22478 Add End
        // if (!isCreditOrder && !isAllDtlRebill // 2017/11/10 S21_NA#22478 Add Condition
                // && NWXC150001DsCheck.checkConfigShiptoLocation(//
                // pMsg.glblCmpyCd.getValue() //
                // , configPMsg.configTpCd.getValue() //
                // , configPMsg.svcConfigMstrPk.getValue() //
                // , configPMsg.shipToCustCd.getValue())) {
            // NWZC150001Common.setMsgId2(pMsg2, NWZM1467E);
        // }
        // 2018/03/15 S21_NA#24253 Del Start
    }

    // Add Start 2018/02/26 QC#22967
    /**
     * @param billToCustCd String
     * @param acctNum String
     * @param pMsg NWZC150001PMsg
     * @param pMsg2 NWZC150002PMsg
     * @param msgIdMgr S21ApiMessageIdMgr
     * @param onBatchType ONBATCH_TYPE
     * @param errMsgId String
     */
    private static void callCustInfoGetApiForCheckConfigRelation(String billToCustCd, String acctNum, //
            NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configPMsg, NWZC150002PMsg pMsg2, NWZC150003PMsg pMsg3, //
            S21ApiMessageIdMgr msgIdMgr, ONBATCH_TYPE onBatchType, String errMsgId) {

        NMZC610001PMsg rsltPMsg = NWXC150001DsCheck.callCustInfoGetApiBillTo( //
                pMsg.glblCmpyCd.getValue(), billToCustCd, acctNum, onBatchType);

        if (S21ApiUtil.isXxMsgId(rsltPMsg)) {
            copyMsgId(pMsg, S21ApiUtil.getXxMsgIdList(rsltPMsg), msgIdMgr);
            NWZC150001Common.setMsgIdForConfig(configPMsg, pMsg2, pMsg3, errMsgId);
        }

        for (int elgChkCnt = 0; elgChkCnt < rsltPMsg.EligibleCheckList.getValidCount(); elgChkCnt++) {
            NMZC610001_EligibleCheckListPMsg eligiblePMsg = rsltPMsg.EligibleCheckList.no(elgChkCnt);
            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue())) {
                NWZC150001Common.setMsgIdForConfig(configPMsg, pMsg2, pMsg3, errMsgId);
            }
        }
    }
    // Add End 2018/02/26 QC#22967

    private static void copyMsgId(NWZC150001PMsg pMsg, List<String> list, S21ApiMessageIdMgr msgIdMgr) {
        for (String msgId : list) {
            msgIdMgr.addXxMsgId(msgId, pMsg);
        }
    }

    private static void checkMultiConfigId(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List, NWZC150001_cpoConfigPMsg configPMsg) {

        String configCatgCd = configPMsg.configCatgCd.getValue();

//        boolean isNeadCheck = false;
//
//        if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
//            isNeadCheck = NWXC150001DsCheck.matchConfigTp(glblCmpyCd, configPMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, false, true, true);
//        } else if (CONFIG_CATG.INBOUND.equals(configCatgCd)) {
//            isNeadCheck = NWXC150001DsCheck.matchConfigTp(glblCmpyCd, configPMsg.configTpCd.getValue(), CONFIG_CATG.INBOUND, false, true, false);
//        }
//        if (!isNeadCheck) {
//            return;
//        }

        String dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();

        // 2017/02/22 QC#16575 UPD START
        // List<String> serNumList = new ArrayList<String>(0);
        List<Map<String, String>> serNumList = new ArrayList<Map<String, String>>(0);
        // 2017/02/22 QC#16575 UPD E N D
        List<NWZC150001_APMsg> serNumDtlList = new ArrayList<NWZC150001_APMsg>(0);
        List<NWZC150001_rtnDtlPMsg> serNumRtnDtlList = new ArrayList<NWZC150001_rtnDtlPMsg>(0);
        if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                NWZC150001_APMsg linePMsg = pMsg.A.no(i);
                String lineDsOrdPosnNum = linePMsg.dsOrdPosnNum_A1.getValue();
                String serNum = linePMsg.serNum_A1.getValue();
                // 2017/02/22 QC#16575 ADD START
                String mdseCd = linePMsg.mdseCd_A1.getValue();
                // 2017/02/22 QC#16575 ADD E N D
                // 2017/02/22 QC#16575 UPD START
                // if (lineDsOrdPosnNum.equals(dsOrdPosnNum) && ZYPCommonFunc.hasValue(serNum)) {
                // serNumList.add(serNum);
                if (lineDsOrdPosnNum.equals(dsOrdPosnNum) && ZYPCommonFunc.hasValue(serNum) && ZYPCommonFunc.hasValue(mdseCd)) {
                    Map<String, String> serNumMap = new HashMap<String, String>();
                    serNumMap.put("serNum", serNum);
                    serNumMap.put("mdseCd", mdseCd);
                    serNumList.add(serNumMap);
                    // 2017/02/22 QC#16575 UPD E N D
                    serNumDtlList.add(pMsg.A.no(i));
                }
            }
        } else if (CONFIG_CATG.INBOUND.equals(configCatgCd)) {
            for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
                NWZC150001_rtnDtlPMsg rtnLinePMsg = pMsg.rtnDtl.no(i);
                String lineDsOrdPosnNum = rtnLinePMsg.dsOrdPosnNum_B1.getValue();
                String serNum = rtnLinePMsg.serNum_B1.getValue();
                // 2017/02/22 QC#16575 ADD START
                String mdseCd = rtnLinePMsg.mdseCd_B1.getValue();
                // 2017/02/22 QC#16575 ADD E N D
                // 2017/02/22 QC#16575 UPD START
                // if (lineDsOrdPosnNum.equals(dsOrdPosnNum) &&
                // ZYPCommonFunc.hasValue(serNum)) {
                // serNumList.add(serNum);
                if (lineDsOrdPosnNum.equals(dsOrdPosnNum) && ZYPCommonFunc.hasValue(serNum) && ZYPCommonFunc.hasValue(mdseCd)) {
                    Map<String, String> serNumMap = new HashMap<String, String>();
                    serNumMap.put("serNum", serNum);
                    serNumMap.put("mdseCd", mdseCd);
                    serNumList.add(serNumMap);
                    // 2017/02/22 QC#16575 UPD E N D
                    serNumRtnDtlList.add(pMsg.rtnDtl.no(i));
                }
            }
        }
        if (serNumList.size() > 1) {
            if (NWZC150001Query.getInstance().isMultipleConfigId(pMsg, serNumList)) {
                if (serNumDtlList.size() > 1) {
                    for (NWZC150001_APMsg dtlPMsg : serNumDtlList) {
                        NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                        NWZC150001Common.setMsgId2(pMsg2, NWZM1928E);
                        ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, dtlPMsg.dsOrdPosnNum_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, dtlPMsg.cpoDtlLineNum_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, dtlPMsg.cpoDtlLineSubNum_A1);
                        pMsg2List.add(pMsg2);
                    }
                } else if (serNumRtnDtlList.size() > 1) {
                    for (NWZC150001_rtnDtlPMsg rtnDtlPMsg : serNumRtnDtlList) {
                        NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
                        NWZC150001Common.setMsgId3(pMsg3, NWZM1928E);
                        ZYPEZDItemValueSetter.setValue(pMsg3.dsOrdPosnNum, rtnDtlPMsg.dsOrdPosnNum_B1);
                        ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtnDtlPMsg.cpoDtlLineNum_B1);
                        ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtnDtlPMsg.cpoDtlLineSubNum_B1);
                        pMsg3List.add(pMsg3);
                    }
                }
            }
        }
    }

    private static void otherDetailCheck(NWZC150001PMsg pMsg, //
            List<NWZC150002PMsg> pMsg2List, //
            List<MDSETMsg> mdseTMsgList, //
            NWZC150001_cpoConfigPMsg configPMsg, //
            // Mod Start 2018/05/10 QC#20343
            //List<NWZC150001_APMsg> dsLineNumByConfig //
            //, boolean isCustMdseXrefChk
            boolean isCustMdseXrefChk
            // Mod End 2018/05/10 QC#20343
            , S21ApiMessageIdMgr msgIdMgr
            , NWZC150001DsCpoCommonBean commonBean
// 2019/09/27 QC#53593 MOD START
            , String slsDt
// 2019/09/27 QC#53593 ADD END
    ) { // 2016/08/01 S21_NA#12637 Mod Interface

        // List<NWZC150001_APMsg> dsLineNumByConfig = new ArrayList<NWZC150001_APMsg>(); // S21_NA#4098:add -> 2016/08/01 S21_NA#12637 Del
        String dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();
        // String prevDsCpoPosnNum = null; // S21_NA#4098:add 2016/08/01 S21_NA#12637 Del
        List<Map<String, String>> componentExistsFlgList = getComponentExistsFlgList(pMsg); // 2016/05/09 Add S21_NA#7750

        // 2018/06/05 S21_NA#25151 Add Start
        List<String> errorRefNumList = new ArrayList<String>(0);
        // 2018/06/05 S21_NA#25151 Add End
        boolean isContrSupOrd = isContrSupOrd(pMsg); // 2019/02/22 S21_NA#30449 Add
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            // 2016/08/01 S21_NA#12637 Add Start
            if (!S21StringUtil.isEquals(dsOrdPosnNum, pMsg.A.no(i).dsOrdPosnNum_A1.getValue())) {
                continue;
            }
            // 2016/08/01 S21_NA#12637 Add End
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
            // Del Start 2017/10/26 QC#21323
//            if (isCustMdseXrefChk) { // QC#14815
//                if (NWXC150001DsCheck.checkDetailMdseRelation(//
//                        pMsg.glblCmpyCd.getValue() //
//                        , aPMsg.mdseCd_A1.getValue() //
//                        , aPMsg.custMdseCd_A1.getValue() //
//                        , pMsg.sellToCustCd.getValue())) {
//                    NWZC150001Common.setMsgId2(pMsg2, NWZM1468E);
//                }
//            }
            // Del End 2017/10/26 QC#21323
            if (NWXC150001DsCheck.checkDetailLineCatgRelation(//
                    pMsg.glblCmpyCd.getValue() //
                    , aPMsg.dsOrdLineCatgCd_A1.getValue() //
                    , pMsg.dsOrdTpCd.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1469E);
            }
            if (NWXC150001DsCheck.checkRetailWhRelation(
                    pMsg.glblCmpyCd.getValue() //
// 2019/09/27 QC#53593 MOD START
//                    , pMsg.slsDt.getValue(), pMsg.dsOrdTpCd.getValue() //
                    , slsDt, pMsg.dsOrdTpCd.getValue() //
// 2019/09/27 QC#53593 MOD END
                    , aPMsg.rtlWhCd_A1.getValue() //
                    , aPMsg.rtlSwhCd_A1.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1470E);
            }

            // For Performance QC#8166 Mod Start
            // boolean cntrSplyOrdFlg = NWZC150001Query.getInstance().isExistOrdCatg(pMsg, ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET); // S21_NA#11737
            // 2018/05/20 S21_NA#25604 Mod Start
//            boolean cntrSplyOrdFlg = NWZC150001Common.isExistOrdCatgFromCache(pMsg, ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET, commonBean);
            boolean cntrSplyOrdFlg = NWXC150001DsCheck.isAvalOrderCtxType(pMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET, pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdRsnCd.getValue());
            // 2018/05/20 S21_NA#25604 Mod End
            // For Performance QC#8166 Mod End

            // 2016/06/02 S21_NA#9273 Modify Start
//            if (ZYPCommonFunc.hasValue(aPMsg.serNum_A1)) {
//                Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(//
//                        glblCmpyCd //
//                        , aPMsg.serNum_A1.getValue());
            if ((ZYPCommonFunc.hasValue(aPMsg.serNum_A1) || ZYPCommonFunc.hasValue(aPMsg.svcMachMstrPk_A1)) && !cntrSplyOrdFlg) { // S21_NA#11737
                // 2016/09/28 S21_NA#14264 Mod Start
//                Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(//
//                        glblCmpyCd //
//                        , aPMsg.serNum_A1.getValue() //
//                        , aPMsg.svcMachMstrPk_A1.getValue());

                // 2017/02/22 QC#16575 UPD START
                // Map<String, Object> map = svcMachCache.getSvcMachMstrMap(aPMsg.serNum_A1.getValue(), aPMsg.svcMachMstrPk_A1.getValue());
                Map<String, Object> map = commonBean.getSvcMachCache().getSvcMachMstrMap(aPMsg.serNum_A1.getValue(), aPMsg.svcMachMstrPk_A1.getValue(), aPMsg.mdseCd_A1.getValue());
                // 2017/02/22 QC#16575 UPD E N D
                // 2016/09/28 S21_NA#14264 Mod End
                // 2016/06/02 S21_NA#9273 Modify End
                if (map == null) {
                    // 2016/02/22 S21_NA#3112 Del Start
//                    setMsgId2(pMsg2, NWZM1471E);
                    // 2016/02/22 S21_NA#3112 Del Start
                } else if (CR_REBIL.REBILL.equals(aPMsg.crRebilCd_A1.getValue())) { // S21_NA#11297
                    // Skip
                // 2017/10/17 S21_NA#21905 Add Start
                } else if (CR_REBIL.CREDIT.equals(aPMsg.crRebilCd_A1.getValue())) {
                    // Skip
                // 2017/10/17 S21_NA#21905 Add End
                } else if (isSameLine(pMsg.cpoOrdNum.getValue(), aPMsg, map)) { // 2019/02/28 S21_NA#30544 Add
                    // Skip
                // START 2022/08/24 F.Fadriquela [QC#60482, ADD]
                } else if (isCpoDtlLineShipped(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue(), aPMsg.cpoDtlLineNum_A1.getValue(), aPMsg.cpoDtlLineSubNum_A1.getValue())) {
                    // Skip
                // END 2022/08/24 F.Fadriquela [QC#60482, ADD]
                } else {
                    // NWZC150001_cpoConfigPMsg configPMsg = getConfigPMsg(aPMsg, pMsg.cpoConfig); 2016/08/01 S21_NA#12637 Del

                    if (checkSerNumForOrderDetail(//
                            pMsg.glblCmpyCd.getValue()//
                            , pMsg.cpoOrdNum.getValue() //
                            , aPMsg//
                            , configPMsg.configTpCd.getValue() //
                            , (String) map.get("SVC_MACH_USG_STS_CD") //
                            , (String) map.get("SVC_MACH_MSTR_STS_CD") //
                            , (String) map.get("ALLC_FLG") //
                            , (String) map.get("TRX_HDR_NUM") // 2016/04/19 S21_NA#7271 Mod
                            , (String) map.get("TRX_LINE_NUM") // 2016/04/19 S21_NA#7271 Mod
                            , (String) map.get("TRX_LINE_SUB_NUM") //2016/04/19 S21_NA#7271 Mod
                            , componentExistsFlgList // 2016/05/09 Add S21_NA#7750
                            ,pMsg2 // 2018/12/20 QC#28928 Add
                            , isValidSkipSts(pMsg, (String) map.get("SVC_MACH_MSTR_STS_CD")))) { // 2018/09/26 S21_NA#28482 Add
                        //NWZC150001Common.setMsgId2(pMsg2, NWZM1472E); // 2018/12/20 QC#28928 Del
                    }
                    if (ZYPCommonFunc.hasValue(configPMsg.pickSvcConfigMstrPk)) { // 2016/01/25 S21_NA#3505
                        // 2019/08/01 QC#52212 Mod Start
                        if (ZYPCommonFunc.hasValue((BigDecimal) map.get("SVC_CONFIG_MSTR_PK"))
                                && NWXC150001DsCheck.checkSerNumSvcConfigMstrPkRelation(//) {
//                        if (NWXC150001DsCheck.checkSerNumSvcConfigMstrPkRelation(//
//                                configPMsg.svcConfigMstrPk.getValue() // 2016/01/25 S21_NA#3505
                                configPMsg.pickSvcConfigMstrPk.getValue() // 2016/01/25 S21_NA#3505
                                , (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"))) {
                            // 2019/08/01 QC#52212 Mod End
                            NWZC150001Common.setMsgId2(pMsg2, NWZM1473E);
                        }
                    // QC#24245 2018/06/13 Add Start
                    } else {
                        // 2018/10/10 S21_NA#28728 Add Condition
                        if (!ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)){
                            // 2018/10/31 S21_NA#28921 Mod Start
                            //NWZC150001Common.setMsgId2(pMsg2, NWZM2285E);
                            // 2018/11/14 S21_NA#29253 Mod Start
                            // NWZC150001Common.setMsgId4(pMsg2, NWZM2291E, new String[] {((BigDecimal) map.get("SVC_CONFIG_MSTR_PK")).toString()});
                            if (ZYPCommonFunc.hasValue((BigDecimal) map.get("SVC_CONFIG_MSTR_PK"))) {
                                // 2018/12/25 S21_NA#28921-2 Mod Start
//                               NWZC150001Common.setMsgId4(pMsg2, NWZM2291E, new String[] {((BigDecimal) map.get("SVC_CONFIG_MSTR_PK")).toString()});
                               NWZC150001Common.setMsgId2(pMsg2, NWZM2291E);
                               // 2018/12/25 S21_NA#28921-2 Mod End
                            }
                            // 2018/11/14 S21_NA#29253 Mod End
                            // 2018/10/31 S21_NA#28921 Mod End
                        } else if (ZYPCommonFunc.hasValue((BigDecimal) map.get("SVC_CONFIG_MSTR_PK"))) {
                            // 2018/12/25 S21_NA#28921-2 Del Start
//                        //if (ZYPCommonFunc.hasValue((BigDecimal) map.get("SVC_CONFIG_MSTR_PK"))) {
//                            if (NWXC150001DsCheck.checkSerNumSvcConfigMstrPkRelation(//
//                                    configPMsg.svcConfigMstrPk.getValue(), (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"))) {
//                                NWZC150001Common.setMsgId2(pMsg2, NWZM1473E);
//                            }
                            // 2018/12/25 S21_NA#28921-2 Del End
                            // 2018/12/25 S21_NA#28921-2 Add Start
                            // New Order
                            if (NWXC150001DsCheck.matchConfigTp(pMsg.glblCmpyCd.getValue(), configPMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, true, false, false)) {
                                NWZC150001Common.setMsgId2(pMsg2, NWZM2291E);
                            } else { // Exists At Warehouse
                                if (NWXC150001DsCheck.checkSerNumSvcConfigMstrPkRelation(//
                                        configPMsg.svcConfigMstrPk.getValue(), (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"))) {
                                    NWZC150001Common.setMsgId2(pMsg2, NWZM1473E);
                                }
                            }
                            // 2018/12/25 S21_NA#28921-2 Add End
                        }
                    // QC#24245 2018/06/13 Add End
                    }
                    // 2018/10/05 S21_NA#28383 mod start
                    // 2018/09/26 S21_NA#28482 Mod Start
                    // if (NWXC150001DsCheck.matchConfigTp(pMsg.glblCmpyCd.getValue(), configPMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, true, false, true)) { // S21_NA#7750
//                    if (!isValidSkipSts(pMsg, (String) map.get("SVC_MACH_MSTR_STS_CD")) && // 
//                            NWXC150001DsCheck.matchConfigTp(pMsg.glblCmpyCd.getValue(), configPMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, true, false, true)) { // S21_NA#7750
                    if (!isValidSkipSts(pMsg, (String) map.get("SVC_MACH_MSTR_STS_CD")) && // 
                            NWXC150001DsCheck.matchConfigTp(pMsg.glblCmpyCd.getValue(), configPMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, true, false, true) && //
                            ZYPConstant.FLG_ON_Y.equals(mdseTMsgList.get(i).invtyCtrlFlg)) {
                    // 2018/09/26 S21_NA#28482 Mod End
                    // 2018/10/05 S21_NA#28383 mod end
                        if (NWXC150001DsCheck.checkSerNumRtlWhRelation(//
                                pMsg.glblCmpyCd.getValue() //
                                , aPMsg.rtlWhCd_A1.getValue() //
                                , aPMsg.rtlSwhCd_A1.getValue() //
                                , (String) map.get("CUR_LOC_NUM"))) {
                            NWZC150001Common.setMsgId2(pMsg2, NWZM1474E);
                        }
                    }
                }
            }
            if (NWXC150001DsCheck.checkSbstRelation(//
                    pMsg.glblCmpyCd.getValue() //
                    //, aPMsg.mdseCd_A1.getValue() // 2015/12/24 S21_NA#2123 Del
                    , aPMsg.origMdseCd_A1.getValue() // 2015/12/24 S21_NA#2123 Add
                    , aPMsg.sbstMdseCd_A1.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1475E);
            }
            // QC#29262 2018/11/27 Add Start
            String dsWhCd = ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_RTL_WH_CD", pMsg.glblCmpyCd.getValue());
            if(S21StringUtil.isEquals(dsWhCd, aPMsg.rtlWhCd_A1.getValue())){
                if(!NWXC150001DsCheck.checkOrdLineSrcCatg(pMsg.glblCmpyCd.getValue(), aPMsg.ordLineSrcCd_A1.getValue(), ORD_LINE_SRC_CATG.EXTERNAL)){
                    NWZC150001Common.setMsgId2(pMsg2, NWZM2294E);
                }
            }
            // QC#29262 2018/11/27 Add End

            // 2019/07/30 QC#52201 Add Start
            String varSubWh = ZYPCodeDataUtil.getVarCharConstValue("EXTERNAL_RTL_SUB_WH_CD", pMsg.glblCmpyCd.getValue());
            List<String> varSubWhList = new ArrayList<String>();
            if (varSubWh != null) {
                String[] varSubWhArray = varSubWh.split(",");
                varSubWhList = Arrays.asList(varSubWhArray);

                if (varSubWhList.size() > 0 && ZYPCommonFunc.hasValue(aPMsg.rtlSwhCd_A1) && !varSubWhList.contains(aPMsg.rtlSwhCd_A1.getValue())) {
                    if(ORD_LINE_SRC.CUSA_DROP_SHIP.equals(aPMsg.ordLineSrcCd_A1.getValue())){
                        NWZC150001Common.setMsgId2(pMsg2, NWZM2310E);
                    }
                }
            }
            // 2019/07/30 QC#52201 Add End

            // 2016/08/01 S21_NA#12637 Del Start
//            if (!S21StringUtil.isEquals(aPMsg.dsOrdPosnNum_A1.getValue(), prevDsCpoPosnNum)) { // S21_NA#4098:add
//                prevDsCpoPosnNum = aPMsg.dsOrdPosnNum_A1.getValue();
//                dsLineNumByConfig.clear();
//                for (int currentLine = i; currentLine < pMsg.A.getValidCount(); currentLine++) {
//                    if (!S21StringUtil.isEquals(prevDsCpoPosnNum, pMsg.A.no(currentLine).dsOrdPosnNum_A1.getValue())) {
//                        break;
//                    }
//                    dsLineNumByConfig.add(pMsg.A.no(currentLine));
//                }
//            }
            // 2016/08/01 S21_NA#12637 Del End

            if (!S21StringUtil.isEquals(aPMsg.xxRqstTpCd_A1.getValue(), RQST_TP_DTL_CANCEL)) { // S21_NA#4098:add
                // Mod Start 2018/05/10 QC#20343
                //if (isExistsValidReferenceLine(dsLineNumByConfig, aPMsg)) {
                if (isExistsValidReferenceLine(pMsg, aPMsg)) {
                    // Mod End 2018/05/10 QC#20343
                    if (NWXC150001DsCheck.checkLineRefNum(//
                            pMsg.glblCmpyCd.getValue() //
                            // S21_NA#4098 derete start
                            // , aPMsg.ordSrcRefLineNum_A1.getValue()
                            // //
                            // , aPMsg.dsOrdPosnNum_A1.getValue() //
                            // , aPMsg.dsCpoLineNum_A1.getValue() //
                            // S21_NA#4098 derete end
                            // S21_NA#4098 add start
                            , aPMsg.dplyLineRefNum_A1.getValue() //
                            , CONFIG_CATG.OUTBOUND //
                            // S21_NA#4098 add end
                            , pMsg.cpoOrdNum.getValue())) {
                        NWZC150001Common.setMsgId2(pMsg2, NWZM1476E);
                    }
                } else {
                    // 2018/06/05 S21_NA#25151 Del Start
//                    // 2018/05/14 S21_NA#25488 Mod Start
////                    NWZC150001Common.setMsgId2(pMsg2, NWZM1476E);
//                    // Check Closed Detail
//                    if (!NWZC150001Common.isReferencedDtlIsClosed(pMsg, aPMsg)) {
//                        NWZC150001Common.setMsgId2(pMsg2, NWZM1476E);
//                    }
//                    // 2018/05/14 S21_NA#25488 Mod End
                    // 2018/06/05 S21_NA#25151 Del End
                    // 2018/06/05 S21_NA#25151 Add Start
                    if (errorRefNumList.contains(aPMsg.dplyLineRefNum_A1.getValue())) {
                        NWZC150001Common.setMsgId2(pMsg2, NWZM1476E);
                    } else {
                        // Check Closed Detail
                        if (!NWZC150001Common.isReferencedDtlIsClosed(pMsg, aPMsg)) {
                            NWZC150001Common.setMsgId2(pMsg2, NWZM1476E);
                            errorRefNumList.add(aPMsg.dplyLineRefNum_A1.getValue());
                        }
                    }
                    // 2018/06/05 S21_NA#25151 Add End
                }
            }
            if (ZYPCommonFunc.hasValue(aPMsg.bllgAttrbCustAcctCd_A1)) {
                NMZC610001PMsg cbaPMsg = callCheckBillingAttribute(pMsg, aPMsg.bllgAttrbCustAcctCd_A1.getValue(), commonBean);
                if (S21ApiUtil.isXxMsgId(cbaPMsg)) {
                    List<String> msgIdList = S21ApiUtil.getXxMsgIdList(cbaPMsg);
                    for (String msgId : msgIdList) {
                        msgIdMgr.addXxMsgId(msgId, pMsg);
                    }
                    for (int ix = 0; ix < cbaPMsg.xxMsgIdList.getValidCount(); ix++) {
                        EZDDebugOutput.println(1, //
                                S21MessageFunc.clspGetMessage(//
                                        cbaPMsg.xxMsgIdList.no(ix).xxMsgId.getValue() //
                                        , new String[] {cbaPMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_0.getValue() //
                                                , cbaPMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_1.getValue() //
                                                , cbaPMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_2.getValue() //
                                        }), NWZC150001ForOtherCheck.class);
                    }
                }
                // 2016/02/25 S21_NA#4440 Add Start
                for (int elgChkCnt = 0; elgChkCnt < cbaPMsg.EligibleCheckList.getValidCount(); elgChkCnt++) {
                    NMZC610001_EligibleCheckListPMsg eligiblePMsg = cbaPMsg.EligibleCheckList.no(elgChkCnt);
                    // S21_NA#8372 MOD
//                    if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
//                            || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
//                        setMsgId2(pMsg2, NWZM1455E);
//                    }
                    if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
                        NWZC150001Common.setMsgId2(pMsg2, NWZM1477E); // 2016/10/04 S21_NA#13170 Mod
                    }
                }
                // 2016/02/25 S21_NA#4440 Add End
            }

            if (NWXC150001DsCheck.checkDealWh(//
                    pMsg.glblCmpyCd.getValue() //
                    , aPMsg.rtlWhCd_A1.getValue() //
                    , aPMsg.dsOrdLineCatgCd_A1.getValue() //
                    , mdseTMsgList.get(i).invtyCtrlFlg.getValue())) {
                NWZC150001Common.setMsgId2(pMsg2, NWZM1478E);
            }
            
            // 2019/02/22 S21_NA#30449 Add Start
            if (isContrSupOrd && (!ZYPCommonFunc.hasValue(aPMsg.dsContrNum_A1) || !ZYPCommonFunc.hasValue(aPMsg.svcMachMstrPk_A1))){
                NWZC150001Common.setMsgId2(pMsg2, NWZM2306E);
            }
            // 2019/02/22 S21_NA#30449 Add End

            // 2018/03/29 S21_NA#25099 del start
            // 2018/02/13 S21_NA#22717 add start
//            if (NWXC150001DsCheck.checkAssetCratTrgt(pMsg.glblCmpyCd.getValue(), //
//                    pMsg.dsOrdTpCd.getValue() //
//                    , aPMsg.dsOrdLineCatgCd_A1.getValue() //
//                    , mdseTMsgList.get(i).coaMdseTpCd.getValue())) {
//                NWZC150001Common.setMsgId2(pMsg2, NWZM2253E);
//            }
            // 2018/02/13 S21_NA#22717 add end
            // 2018/03/29 S21_NA#25099 del end

            // 2020/01/23 QA#55207-1 Add Start
            if (!ZYPCommonFunc.hasValue(aPMsg.crRebilCd_A1)) {
                if (DS_ORD_LINE_CATG.RENTAL_CONVERSION_BILL_ONLY.equals(aPMsg.dsOrdLineCatgCd_A1.getValue())) {
                    if (!CONFIG_TP.TO_SALES_CONVERSION.equals(configPMsg.configTpCd.getValue())) {
                        NWZC150001Common.setMsgId2(pMsg2, NWAM0909E);
                    }
                }
            }
            // 2020/01/23 QA#55207-1 Add End

            if (S21ApiUtil.isXxMsgId(pMsg2)) {
                ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, aPMsg.dsOrdPosnNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                pMsg2List.add(pMsg2);
            }
        }
    }
    
    // 2019/02/28 S21_NA#30544 Add Start
    /**
     * check same line with service machine master
     * @param pCpoOrdNum
     * @param aPMsg
     * @param svcMachInfoMap
     * @return same line : true 
     */
    private static boolean isSameLine(String pCpoOrdNum, NWZC150001_APMsg aPMsg, Map<String, Object> svcMachInfoMap) {
         if(S21StringUtil.isEquals(aPMsg.cpoDtlLineNum_A1.getValue(), (String)svcMachInfoMap.get("CPO_DTL_LINE_NUM")) //
                 && S21StringUtil.isEquals(aPMsg.cpoDtlLineSubNum_A1.getValue(), (String)svcMachInfoMap.get("CPO_DTL_LINE_SUB_NUM")) //
                 && S21StringUtil.isEquals(pCpoOrdNum, (String)svcMachInfoMap.get("CPO_ORD_NUM"))) {
             return true;
         }
         return false;
    }
    // 2019/02/28 S21_NA#30544 Add End
    
    // 2018/09/26 S21_NA#28482 Add Start
    private static boolean isValidSkipSts(NWZC150001PMsg pMsg, String machMstrSts){
        String validSkipSts = ZYPCodeDataUtil.getVarCharConstValue(VALID_SKIP_MACH_STS, pMsg.glblCmpyCd.getValue());
        if(validSkipSts == null){
            return false;
        }
        String[] validSkipStsArray = validSkipSts.split(",");
        for(int i = 0; i < validSkipStsArray.length; i++){
            if(validSkipStsArray[i].equals(machMstrSts)){
                return true;
            }
        }
        return false;
    }
    // 2018/09/26 S21_NA#28482 Add End

    /**
     * getComponentExistsFlgList
     * @param pMsg NWZC150001PMsg
     * @return List<Map<String, String>>
     */
    private static List<Map<String, String>> getComponentExistsFlgList(NWZC150001PMsg pMsg) {

        return NWZC150001Query.getInstance().getComponentExistsFlg(pMsg);
    }

    private static boolean checkSerNumForOrderDetail(//
            String glblCmpyCd//
            , String pCpoOrdNum //
            , NWZC150001_APMsg aPMsg //
            , String configTp //
            , String svcMachUsgStsCd //
            , String svcMachMstrStsCd //
            , String allocFlg //
            , String cpoOrdNum //
            , String cpoDtlLineNum //
            , String cpoDtlLineSubNum
            , List<Map<String, String>> componentExistsFlgList
            , NWZC150002PMsg pMsg2 // 2018/12/20 QC#28928 Add
            , boolean isValidSkip) { // 2018/09/26 S21_NA#28482 Add
        // if (CONFIG_TP.EXISTING.equals(configTp) ||
        // CONFIG_TP.NEW.equals(configTp)) {
        // Out bound Y N Y
//        if (NWXC150001DsCheck.matchConfigTp(glblCmpyCd, configTp, CONFIG_CATG.OUTBOUND, true, false, true)) { // S21_NA#955
        if (NWXC150001DsCheck.matchConfigTp(glblCmpyCd, configTp, CONFIG_CATG.OUTBOUND, true, false, true)) { // S21_NA#3505 // S21_NA#7750
//            if (SVC_MACH_USG_STS.IN_INVENTORY.equals(svcMachUsgStsCd) // S21_NA#3505
//                    || SVC_MACH_USG_STS.RETURNED.equals(svcMachUsgStsCd)) {
//                if (SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd)) {
//                    if (ZYPConstant.FLG_ON_Y.equals(allocFlg)) {
//                        return false;
//                    }
//                    if (aPMsg.cpoDtlLineNum_A1.getValue().equals(cpoDtlLineNum) //
//                            && aPMsg.cpoDtlLineSubNum_A1.getValue().equals(cpoDtlLineSubNum) //
//                            && pCpoOrdNum.equals(cpoOrdNum)) {
//                        return false;
//                    }
//                }
//            }
            // 2018/09/26 S21_NA#28482 Add Start
            if(isValidSkip){
                return false;
            }
            // 2018/09/26 S21_NA#28482 Add End
            if ((SVC_MACH_USG_STS.IN_INVENTORY.equals(svcMachUsgStsCd) && SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd))
//                    || (SVC_MACH_USG_STS.RETURNED.equals(svcMachUsgStsCd) && SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd))) {
                    || SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) { // 2016/05/13 S21_NA#8072 Mod Condition
                if (ZYPConstant.FLG_ON_Y.equals(allocFlg)) {
                    return false;
                }
                if (aPMsg.cpoDtlLineNum_A1.getValue().equals(cpoDtlLineNum) //
                        && aPMsg.cpoDtlLineSubNum_A1.getValue().equals(cpoDtlLineSubNum) //
                        && pCpoOrdNum.equals(cpoOrdNum)) {
                    return false;
                }
                // 2018/12/20 QC#28928 Add Start
                NWZC150001Common.setMsgId2(pMsg2, NWZM2303E);
                return true;
                // 2018/12/20 QC#28928 Add End
            }

//            if (SVC_MACH_USG_STS.AT_CUSTOMER.equals(svcMachUsgStsCd)) { // S21_NA#955 // S21_NA#3505
//                if (SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd)) {
//                    if (aPMsg.cpoDtlLineNum_A1.getValue().equals(cpoDtlLineNum) //
//                            && aPMsg.cpoDtlLineSubNum_A1.getValue().equals(cpoDtlLineSubNum) //
//                            && pCpoOrdNum.equals(cpoOrdNum)) {
//                        return false;
//                    }
//                }
//            }
            NWZC150001Common.setMsgId2(pMsg2, NWZM2301E); // 2018/12/20 QC#28928 Add
            return true;
        } else {
            // 2016/05/09 Mod S21_NA#7750 Start
            boolean rc = true; //true:NG
            if (isMdseAtCust(componentExistsFlgList, aPMsg.dsOrdLineCatgCd_A1.getValue())) {
                if (SVC_MACH_USG_STS.AT_CUSTOMER.equals(svcMachUsgStsCd)) {
                    if (SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd) //
                            || SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(svcMachMstrStsCd)) {
                        if (ZYPConstant.FLG_ON_Y.equals(allocFlg)) {
                            rc = false;
                            return rc; // 2018/12/20 QC#28928 Add
                        }
                        if (aPMsg.cpoDtlLineNum_A1.getValue().equals(cpoDtlLineNum) //
                                && aPMsg.cpoDtlLineSubNum_A1.getValue().equals(cpoDtlLineSubNum) //
                                && pCpoOrdNum.equals(cpoOrdNum)) {
                            rc = false;
                            return rc; // 2018/12/20 QC#28928 Add
                        }
                        // 2018/12/20 QC#28928 Add Start
                        NWZC150001Common.setMsgId2(pMsg2, NWZM2303E);
                        return rc;
                        // 2018/12/20 QC#28928 Add End
                    }
                }

                if (SVC_MACH_USG_STS.IN_TRANSIT.equals(svcMachUsgStsCd)) {
                    if (SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachMstrStsCd)) {
                        if (ZYPConstant.FLG_OFF_N.equals(allocFlg)) {
                            if (aPMsg.cpoDtlLineNum_A1.getValue().equals(cpoDtlLineNum) //
                                    && aPMsg.cpoDtlLineSubNum_A1.getValue().equals(cpoDtlLineSubNum) //
                                    && pCpoOrdNum.equals(cpoOrdNum)) {
                                rc = false;
                                return rc; // 2018/12/20 QC#28928 Add
                            }
                            // 2018/12/20 QC#28928 Add Start
                            NWZC150001Common.setMsgId2(pMsg2, NWZM2303E);
                            return rc;
                            // 2018/12/20 QC#28928 Add End
                        }
                    }
                }
                if (rc) {
                    NWZC150001Common.setMsgId2(pMsg2, NWZM2302E); // 2018/12/20 QC#28928 Add
                    return rc;
                }
            }
            if (isMdseAtWh(componentExistsFlgList, aPMsg.dsOrdLineCatgCd_A1.getValue())) {
                if ((SVC_MACH_USG_STS.IN_INVENTORY.equals(svcMachUsgStsCd) && SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd))
//                        || (SVC_MACH_USG_STS.RETURNED.equals(svcMachUsgStsCd) && SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd))) {
                        || SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) { // 2023/04/11 QC#61384 Mod
                    if (ZYPConstant.FLG_ON_Y.equals(allocFlg)) {
                        rc = false;
                        return rc; // 2018/12/20 QC#28928 Add
                    }
                    if (aPMsg.cpoDtlLineNum_A1.getValue().equals(cpoDtlLineNum) //
                            && aPMsg.cpoDtlLineSubNum_A1.getValue().equals(cpoDtlLineSubNum) //
                            && pCpoOrdNum.equals(cpoOrdNum)) {
                        rc = false;
                        return rc; // 2018/12/20 QC#28928 Add
                    }
                    // 2018/12/20 QC#28928 Add Start
                    NWZC150001Common.setMsgId2(pMsg2, NWZM2303E);
                    return rc;
                    // 2018/12/20 QC#28928 Add End
                }
                if (rc) {
                    NWZC150001Common.setMsgId2(pMsg2, NWZM2301E); // 2018/12/20 QC#28928 Add
                    return rc; 
                }
            }
            return false;
        }
        // 2016/05/09 Mod S21_NA#7750 End
//        return true;
    }

    // for Outbound
    // Mod Start 2018/05/10 QC#20343
    //private static boolean isExistsValidReferenceLine(List<NWZC150001_APMsg> dsLineNumByConfig, NWZC150001_APMsg line) {
    private static boolean isExistsValidReferenceLine(NWZC150001PMsg pMsg, NWZC150001_APMsg line) {
        // Mod End 2018/05/10 QC#20343

        if (!ZYPCommonFunc.hasValue(line.dplyLineRefNum_A1)) {
            return true;
        }

        // Mod Start 2018/05/10 QC#20343
        //for (NWZC150001_APMsg refLine : dsLineNumByConfig) {
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg refLine = pMsg.A.no(i);
            // Mod End 2018/05/10 QC#20343

            if (S21StringUtil.isEquals(refLine.xxRqstTpCd_A1.getValue(), RQST_TP_DTL_CANCEL)) {
                continue;
            }
            String dplyRefLineNum = NWXC150001DsCheck.editDtlLineNum(refLine.dsOrdPosnNum_A1.getValue(), refLine.dsCpoLineNum_A1.getValue(), refLine.dsCpoLineSubNum_A1.getValue());
            if (S21StringUtil.isEquals(dplyRefLineNum, line.dplyLineRefNum_A1.getValue())) {
                // QC#10979 2016/10/27 Add Start
                ZYPEZDItemValueSetter.setValue(line.refCpoDtlLineNum_A1, refLine.cpoDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(line.refCpoDtlLineSubNum_A1, refLine.cpoDtlLineSubNum_A1);
                // QC#10979 2016/10/27 Add End
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * checkBillingAttribute
     * @param pMsg NWZC150001PMsg
     * @param bllgAttrbCustAcctCd String
     * @param commonBean NWZC150001DsCpoCommonBean
     * @return boolean if error then return true.
     * </pre>
     */
    private static NMZC610001PMsg callCheckBillingAttribute(NWZC150001PMsg pMsg, String bllgAttrbCustAcctCd, NWZC150001DsCpoCommonBean commonBean) {

        NMZC610001PMsg cbaPMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(cbaPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(cbaPMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
        ZYPEZDItemValueSetter.setValue(cbaPMsg.dsAcctNum_I1, bllgAttrbCustAcctCd);
        ZYPEZDItemValueSetter.setValue(cbaPMsg.dsAcctNum_I2, pMsg.shipToCustAcctCd);

        NMZC610001 cbaApi = new NMZC610001();
        cbaApi.execute(cbaPMsg, commonBean.getOnBatchType());
        return cbaPMsg;
    }

    /**
     * isMdseAtCust
     * @param componentExistsFlgList List<Map<String, String>>
     * @param dsOrdLineCatgCd String
     * @return boolean
     */
    private static boolean isMdseAtCust(List<Map<String, String>> componentExistsFlgList, String dsOrdLineCatgCd) {
        if (componentExistsFlgList == null || componentExistsFlgList.size() == 0) {
            return false;
        }
        for (Map<String, String> componentExistsFlg : componentExistsFlgList) {
            if (ZYPCommonFunc.hasValue(componentExistsFlg.get("DS_ORD_LINE_CATG_CD")) && dsOrdLineCatgCd.equals(componentExistsFlg.get("DS_ORD_LINE_CATG_CD"))) {
                if (ZYPConstant.FLG_ON_Y.equals(componentExistsFlg.get("FIRST_BIZ_CTX_ATTRB_TXT"))) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * isMdseAtWh
     * @param componentExistsFlgList List<Map<String, String>>
     * @param dsOrdLineCatgCd String
     * @return boolean
     */
    private static boolean isMdseAtWh(List<Map<String, String>> componentExistsFlgList, String dsOrdLineCatgCd) {
        if (componentExistsFlgList == null || componentExistsFlgList.size() == 0) {
            return false;
        }
        for (Map<String, String> componentExistsFlg : componentExistsFlgList) {
            if (ZYPCommonFunc.hasValue(componentExistsFlg.get("DS_ORD_LINE_CATG_CD")) && dsOrdLineCatgCd.equals(componentExistsFlg.get("DS_ORD_LINE_CATG_CD"))) {
                if (ZYPConstant.FLG_ON_Y.equals(componentExistsFlg.get("SCD_BIZ_CTX_ATTRB_TXT"))) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

//    private static void otherDetailCheckForRtlEquipOrder(NWZC150001PMsg pMsg, //
//            List<NWZC150002PMsg> pMsg2List,
//            List<MDSETMsg> mdseTMsgList,
//            NWZC150001_cpoConfigPMsg configPMsg
//            , NWZC150001DsCpoCommonBean commonBean) {
//
//        String dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();
//
//        boolean existsMachOrAsry = false; // for machine or accessory
//        boolean isValidConfig    = false; // for cancelled config
//
//        if (pMsg.A.getValidCount() == 0) {
//            return;
//        }
//
//        // Add Start 2017/01/11 M.Ohno S21_NA#16973
//        if (ZYPCommonFunc.hasValue(configPMsg.mdlId)) {
//            return;
//        }
//        // Add End   2017/01/11 M.Ohno S21_NA#16973
//
//        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
//            if (!S21StringUtil.isEquals(dsOrdPosnNum, pMsg.A.no(i).dsOrdPosnNum_A1.getValue())) {
//                continue;
//            }
//
//            isValidConfig = true;
//            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
//
//            if (isMachOrAsry(aPMsg.mdseCd_A1.getValue(), commonBean)) {
//                return;
//            }
//        }
//
//        if (isValidConfig && !existsMachOrAsry) {
//            NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
//            NWZC150001Common.setMsgId2(pMsg2, NWZM2046E);
//            ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, dsOrdPosnNum);
//            pMsg2List.add(pMsg2);
//        }
//    }
//
//    private static boolean isMachOrAsry(String mdseCd, NWZC150001DsCpoCommonBean commonBean) {
//        ORD_TAKE_MDSETMsg ordTake = new ORD_TAKE_MDSETMsg();
//        int size = ordTake.getAttr("ordTakeMdseCd").getDigit();
//
//        for (MDSETMsg dsMdseInfoTMsg :  commonBean.getDsMdseInfoList()) {
//            if (S21StringUtil.isEquals(dsMdseInfoTMsg.mdseCd.getValue(), mdseCd)) {
//                String mdseItemTpCd = dsMdseInfoTMsg.mdseItemTpCd.getValue();
//                // 2018/02/01 QC#23329 Mod Start
//                //if (MDSE_ITEM_TP.MACHINE.equals(mdseItemTpCd) || MDSE_ITEM_TP.ACCESSORY.equals(mdseItemTpCd)) {
//                if (MDSE_ITEM_TP.MACHINE.equals(mdseItemTpCd) || MDSE_ITEM_TP.ACCESSORY.equals(mdseItemTpCd)//
//                        || MDSE_ITEM_TP.SOFTWARE.equals(mdseItemTpCd) || MDSE_ITEM_TP.SOFTWARE_LICENSE.equals(mdseItemTpCd)) {
//                // 2018/02/01 QC#23329 Mod End
//                    return true;
//                }
//            }
//            String mdse8LenCd = dsMdseInfoTMsg.mdseCd.getValue();
//            if (mdse8LenCd.length() > size) {
//                mdse8LenCd = mdse8LenCd.substring(0, size);
//                if (S21StringUtil.isEquals(mdse8LenCd, mdseCd)) {
//                    String mdseItemTpCd = dsMdseInfoTMsg.mdseItemTpCd.getValue();
//                    // 2018/02/01 QC#23329 Mod Start
//                    //if (MDSE_ITEM_TP.MACHINE.equals(mdseItemTpCd) || MDSE_ITEM_TP.ACCESSORY.equals(mdseItemTpCd)) {
//                    if (MDSE_ITEM_TP.MACHINE.equals(mdseItemTpCd) || MDSE_ITEM_TP.ACCESSORY.equals(mdseItemTpCd)//
//                            || MDSE_ITEM_TP.SOFTWARE.equals(mdseItemTpCd) || MDSE_ITEM_TP.SOFTWARE_LICENSE.equals(mdseItemTpCd)) {
//                    // 2018/02/01 QC#23329 Mod End
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }

    private static void otherReturnDetailCheck(NWZC150001PMsg pMsg, //
            List<NWZC150003PMsg> pMsg3List, //
            List<MDSETMsg> mdseTMsgRtrnList, //
            NWZC150001_cpoConfigPMsg configPMsg, //
            List<NWZC150001_rtnDtlPMsg> dsLineNumByConfig //
            , boolean isCustMdseXrefChk
            , S21ApiMessageIdMgr msgIdMgr
            , NWZC150001DsCpoCommonBean commonBean
            , Map<String, Boolean> ownerCfsMap
// 2019/09/27 QC#53593 MOD START
            , String slsDt
// 2019/09/27 QC#53593 ADD END        
    ) { // 2016/08/01 S21_NA#12637 Mod Interface 2017/12/21 S21_NA#20050 Add ownerCfsMap

        // List<NWZC150001_rtnDtlPMsg> dsLineNumByConfig = new ArrayList<NWZC150001_rtnDtlPMsg>(); // S21_NA#4098:add -> 2016/08/01 S21_NA#12637 Del
        String dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();
        // String prevDsCpoPosnNum = null; // S21_NA#4098:add -> 2016/08/01 S21_NA#12637 Del

        // Add Start 2018/10/29 QC#28882
        boolean isConfigActionChk = NWXC150001DsCheck.matchConfigTp( //
                pMsg.glblCmpyCd.getValue(), configPMsg.configTpCd.getValue(), CONFIG_CATG.INBOUND, //
                true, false, false);
        // Add End 2018/10/29 QC#28882

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            // 2016/08/01 S21_NA#12637 Add Start
            if (!S21StringUtil.isEquals(dsOrdPosnNum, pMsg.rtnDtl.no(i).dsOrdPosnNum_B1.getValue())) {
                continue;
            }
            // 2016/08/01 S21_NA#12637 Add End
            NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
            NWZC150003PMsg pMsg3 = new NWZC150003PMsg();

            // 2016/01/13 S21_NA#2726 Add Start
            // NWZC150001_cpoConfigPMsg configPMsg = getConfigPMsgForRtrn(rtnDtlPMsg, pMsg.cpoConfig); 2016/08/01 S21_NA#12637 Del
            // 2016/01/13 S21_NA#2726 Add Start
            // 2018/12/06 QC#29285 Del Start
//            if (isCustMdseXrefChk) { // QC#14815
//                if (NWXC150001DsCheck.checkDetailMdseRelation(//
//                        pMsg.glblCmpyCd.getValue() //
//                        , rtnDtlPMsg.mdseCd_B1.getValue() //
//                        , rtnDtlPMsg.custMdseCd_B1.getValue() //
//                        , pMsg.sellToCustCd.getValue())) {
//                    NWZC150001Common.setMsgId3(pMsg3, NWZM1468E);
//                }
//            }
            // 2018/12/06 QC#29285 Del End

            if (NWXC150001DsCheck.checkDetailLineCatgRelation(//
                    pMsg.glblCmpyCd.getValue() //
                    , rtnDtlPMsg.dsOrdLineCatgCd_B1.getValue() //
                    , pMsg.dsOrdTpCd.getValue())) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM1469E);
            }
            if (NWXC150001DsCheck.checkRetailWhRelation(//
                    pMsg.glblCmpyCd.getValue() //
// 2019/09/27 QC#53593 MOD START
//                    , pMsg.slsDt.getValue(), pMsg.dsOrdTpCd.getValue() //
                    , slsDt, pMsg.dsOrdTpCd.getValue() //
// 2019/09/27 QC#53593 MOD END
                    , rtnDtlPMsg.rtlWhCd_B1.getValue() //
                    , rtnDtlPMsg.rtlSwhCd_B1.getValue())) {
                NWZC150001Common.setMsgId3(pMsg3, NWZM1470E);
            }
            // 2016/06/02 S21_NA#9273 Modify Start
//            if (ZYPCommonFunc.hasValue(rtnDtlPMsg.serNum_B1)) {
//                Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(//
//                        glblCmpyCd //
//                        , rtnDtlPMsg.serNum_B1.getValue());
            // 2019/04/10 S21_NA#31140 Mod Start
            // if (ZYPCommonFunc.hasValue(rtnDtlPMsg.serNum_B1) || ZYPCommonFunc.hasValue(rtnDtlPMsg.svcMachMstrPk_B1)) {
            if ((ZYPCommonFunc.hasValue(rtnDtlPMsg.serNum_B1) || ZYPCommonFunc.hasValue(rtnDtlPMsg.svcMachMstrPk_B1)) //
                    && !S21StringUtil.isEquals(RTRN_LINE_STS.RECEIVED, rtnDtlPMsg.rtrnLineStsCd_B1.getValue())) {
                // 2019/04/10 S21_NA#31140 Mod End
                // 2018/07/30 S21_NA#26181 Add Start
                if(NWXC150001DsCheck.alreadyExistOpenRmaLine(pMsg.glblCmpyCd.getValue(), rtnDtlPMsg.svcMachMstrPk_B1.getValue(), //
                        rtnDtlPMsg.serNum_B1.getValue(), rtnDtlPMsg.mdseCd_B1.getValue(), pMsg.cpoOrdNum.getValue())) {
                    NWZC150001Common.setMsgId3(pMsg3, NWZM2275E);
                }
                // 2018/07/30 S21_NA#26181 Add End
                Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(//
                        pMsg.glblCmpyCd.getValue() //
                        , rtnDtlPMsg.serNum_B1.getValue()//
                        // 2017/02/22 QC#16575 ADD START
                        , rtnDtlPMsg.mdseCd_B1.getValue() //
                        // 2017/02/22 QC#16575 ADD E N D
                        , rtnDtlPMsg.svcMachMstrPk_B1.getValue());
                // 2016/06/02 S21_NA#9273 Modify End
                if (map == null) {
                    // 2016/02/22 S21_NA#3112 Del Start
//                    setMsgId3(pMsg3, NWZM1471E);
                    // 2016/02/22 S21_NA#3112 Del End
                } else {
                    // NWZC150001_cpoConfigPMsg configPMsg = getConfigPMsgForRtrn(rtnDtlPMsg, pMsg.cpoConfig); S21_NA#2726 Del
                    // 2019/01/21 S21_NA#29983 Mod Start
                    // if (!RTRN_LINE_STS.RECEIVED.equals(rtnDtlPMsg.ordLineSrcCd_B1.getValue())// 2016/09/05 S21_NA#12435 Add
                            // && checkSerNumForReturnDetail(//
                    if(checkSerNumForReturnDetail(//
                            // 2019/01/21 S21_NA#29983 Mod End
                            pMsg.glblCmpyCd.getValue() //
                            , pMsg.cpoOrdNum.getValue() //
                            , rtnDtlPMsg//
                            , configPMsg.configTpCd.getValue() //
                            , (String) map.get("SVC_MACH_USG_STS_CD") //
                            , (String) map.get("SVC_MACH_MSTR_STS_CD") //
                            , (String) map.get("ALLC_FLG") //
                            , (String) map.get("TRX_HDR_NUM") // 2016/04/19 S21_NA#7271 Mod
                            , (String) map.get("TRX_LINE_NUM") // 2016/04/19 S21_NA#7271 Mod
                            ,pMsg3  // 2018/12/20 QC#28928 Add
                            // , (String) map.get("RTRN_LINE_STS_CD") // 2019/01/21 S21_NA#29983 Add // 2019/04/10 S21_NA#31140 Del
                            , (String) map.get("TRX_LINE_SUB_NUM"))) { // 2016/04/19 S21_NA#7271 Mod
                        // 2018/11/01 S21_NA#28928 Mod Start
                        //NWZC150001Common.setMsgId3(pMsg3, NWZM1472E);
                        //NWZC150001Common.setMsgId3(pMsg3, NWZM2292E); // 2018/12/20 QC#28928 Del
                        // 2018/11/01 S21_NA#28928 Mod End
                    }
                    // 2017/12/21 S21_NA#20050 Mod Start
//                    if (ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)) { // 2016/03/16 S21_NA#5519 Add condition
//                        if (NWXC150001DsCheck.checkSerNumSvcConfigMstrPkRelation(// 
//                                configPMsg.svcConfigMstrPk.getValue() //
//                                , (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"))) {
//                            NWZC150001Common.setMsgId3(pMsg3, NWZM1473E);
//                        }
//                    } //2016/03/16 S21_NA#5519 Add condition
                    BigDecimal svcConfigMstrPk = configPMsg.svcConfigMstrPk.getValue();
                    // QC#27596 mod start
//                    if (!ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
//                        svcConfigMstrPk = new BigDecimal("-1");
//                    }
                    BigDecimal dbSvcConfigMstrPk = (BigDecimal) map.get("SVC_CONFIG_MSTR_PK");
                    if (ZYPCommonFunc.hasValue(svcConfigMstrPk) && ZYPCommonFunc.hasValue(dbSvcConfigMstrPk)) {
                        if (NWXC150001DsCheck.checkSerNumSvcConfigMstrPkRelation(// 
                                svcConfigMstrPk, dbSvcConfigMstrPk)) {
                            NWZC150001Common.setMsgId3(pMsg3, NWZM1473E);
                        }
                    }
                    // QC#27596 mod end
                    // 2017/12/21 S21_NA#20050 Mod End
                    //                    if (NWXC150001DsCheck.checkSerNumRtlWhRelation(//
                    //                            glblCmpyCd //
                    //                            , aPMsg.rtlWhCd_B1.getValue() //
                    //                            , aPMsg.rtlSwhCd_B1.getValue() //
                    //                            , (String) map.get("CUR_LOC_NUM"))) {
                    //                        setMsgId3(pMsg3, NWZM1474E);
                    //                    }

                    if (ZYPCommonFunc.hasValue(rtnDtlPMsg.bllgAttrbCustAcctCd_B1)) {
                        NMZC610001PMsg cbaPMsg = callCheckBillingAttribute(pMsg, rtnDtlPMsg.bllgAttrbCustAcctCd_B1.getValue(), commonBean);
                        if (S21ApiUtil.isXxMsgId(cbaPMsg)) {
                            List<String> msgIdList = S21ApiUtil.getXxMsgIdList(cbaPMsg);
                            for (String msgId : msgIdList) {
                                msgIdMgr.addXxMsgId(msgId, pMsg);
                            }
                            for (int ix = 0; ix < cbaPMsg.xxMsgIdList.getValidCount(); ix++) {
                                EZDDebugOutput.println(1, //
                                        S21MessageFunc.clspGetMessage(//
                                                cbaPMsg.xxMsgIdList.no(ix).xxMsgId.getValue() //
                                                , new String[] {cbaPMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_0.getValue() //
                                                        , cbaPMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_1.getValue() //
                                                        , cbaPMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_2.getValue() //
                                                }), NWZC150001ForOtherCheck.class);
                            }
                        }
                        // 2016/02/25 S21_NA#4440 Add Start
                        for (int elgChkCnt = 0; elgChkCnt < cbaPMsg.EligibleCheckList.getValidCount(); elgChkCnt++) {
                            NMZC610001_EligibleCheckListPMsg eligiblePMsg = cbaPMsg.EligibleCheckList.no(elgChkCnt);
                            // S21_NA#8372 MOD
//                            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
//                                    || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
//                                setMsgId3(pMsg3, NWZM1455E);
//                            }
                            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
                                NWZC150001Common.setMsgId3(pMsg3, NWZM1477E); // 2016/10/04 S21_NA#13170 Mod
                            }
                        }
                        // 2016/02/25 S21_NA#4440 Add End
                    }
                    // QC#25606 Del
//                    // 2017/12/21 S21_NA#20050 Add Start
//                    if (hasOwnerError(pMsg, rtnDtlPMsg, (String) map.get("OWNR_ACCT_NUM"), ownerCfsMap)) {
//                        NWZC150001Common.setMsgId3(pMsg3, NWZM2250E);
//                    }
//                    // 2017/12/21 S21_NA#20050 Add End
                    // 2018/08/21 S21_NA#26767 Add Start
                    // String errCode = hasOwnerError(pMsg, rtnDtlPMsg, (String) map.get("OWNR_ACCT_NUM"), ownerCfsMap);
                    // START 2023/07/25 K.Chiba [QC#61199, DEL]
//                    String errCode = hasOwnerError(pMsg, rtnDtlPMsg, (String) map.get("OWNR_ACCT_NUM"), ownerCfsMap, mdseTMsgRtrnList.get(i));
//                    if (ZYPCommonFunc.hasValue(errCode)) {
//                        NWZC150001Common.setMsgId3(pMsg3, errCode);
//                    }
                    // END 2023/07/25 K.Chiba [QC#61199, DEL]

                    // 2018/08/21 S21_NA#26767 Add End

                    // Add Start 2018/10/29 QC#28882
                    // Mod Start 2019/03/05 QC#30591
//                    if (isConfigActionChk) {
                    // if (isConfigActionChk && !isContrSupOrd(pMsg)) { // 2019/03/17 S21_NA#30770 Mod
                    if (isConfigActionChk && !ZYPCommonFunc.hasValue(rtnDtlPMsg.dsContrNum_B1)) {
                        NWZC150001Common.setMsgId3(pMsg3, NWZM2289E);
                    }
                    // Mod End 2019/03/05 QC#30591
                    // Add End 2018/10/29 QC#28882
                }
            }

            // 2016/08/01 S21_NA#12637 Del Start
//            if (!S21StringUtil.isEquals(rtnDtlPMsg.dsOrdPosnNum_B1.getValue(), prevDsCpoPosnNum)) { // S21_NA#4098:add
//                prevDsCpoPosnNum = rtnDtlPMsg.dsOrdPosnNum_B1.getValue();
//                dsLineNumByConfig.clear();
//                for (int currentLine = i; currentLine < pMsg.rtnDtl.getValidCount(); currentLine++) {
//                    if (!S21StringUtil.isEquals(prevDsCpoPosnNum, pMsg.rtnDtl.no(currentLine).dsOrdPosnNum_B1.getValue())) {
//                        break;
//                    }
//                    dsLineNumByConfig.add(pMsg.rtnDtl.no(currentLine));
//                }
//            }
            // 2016/08/01 S21_NA#12637 Del End

            if (!S21StringUtil.isEquals(rtnDtlPMsg.xxRqstTpCd_B1.getValue(), RQST_TP_DTL_CANCEL)) { // S21_NA#4098:add
                if (isExistsValidReferenceLine(dsLineNumByConfig, rtnDtlPMsg)) {
                    if (NWXC150001DsCheck.checkLineRefNum(//
                            pMsg.glblCmpyCd.getValue() //
                            // S21_NA#4098 delete start
                            // ,
                            // rtnDtlPMsg.ordSrcRefLineNum_B1.getValue()
                            // //
                            // , rtnDtlPMsg.dsOrdPosnNum_B1.getValue()
                            // //
                            // , rtnDtlPMsg.dsCpoLineNum_B1.getValue()
                            // //
                            // S21_NA#4098 delete end
                            // S21_NA#4098 add start
                            , rtnDtlPMsg.dplyLineRefNum_B1.getValue() //
                            , CONFIG_CATG.INBOUND //
                            // S21_NA#4098 add end
                            , pMsg.cpoOrdNum.getValue())) {
                        NWZC150001Common.setMsgId3(pMsg3, NWZM1476E);
                    }
                } else {
                    NWZC150001Common.setMsgId3(pMsg3, NWZM1476E);
                }
            }

            // 2016/01/13 S21_NA#2726 Add Start 
            // if (ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)) { // 2019/04/24 S21_NA#31185 Mod
            if (ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk) && !S21StringUtil.isEquals(RTRN_LINE_STS.RECEIVED, rtnDtlPMsg.rtrnLineStsCd_B1.getValue())) {
                String glblCmpyCd = pMsg.glblCmpyCd.getValue();
                String mdseCd = rtnDtlPMsg.mdseCd_B1.getValue();
                BigDecimal svcConfigMstrPk = configPMsg.svcConfigMstrPk.getValue();
                if (NWXC150001DsCheck.isIbInstallableAndNotInConfig(glblCmpyCd, mdseCd, svcConfigMstrPk)) {
                    NWZC150001Common.setMsgId3(pMsg3, NWZM1795E);
                }
            }

            if (S21ApiUtil.isXxMsgId(pMsg3)) {
                ZYPEZDItemValueSetter.setValue(pMsg3.dsOrdPosnNum, rtnDtlPMsg.dsOrdPosnNum_B1);
                ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtnDtlPMsg.cpoDtlLineNum_B1);
                ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtnDtlPMsg.cpoDtlLineSubNum_B1);
                pMsg3List.add(pMsg3);
            }
            // 2016/01/13 S21_NA#2726 Add End
        }

    }

    private static boolean checkSerNumForReturnDetail(//
            String glblCmpyCd //
            , String pCpoOrdNum //
            , NWZC150001_rtnDtlPMsg rtnDtlPMsg //
            , String configTp //
            , String svcMachUsgStsCd //
            , String svcMachMstrStsCd //
            , String allocFlg //
            , String cpoOrdNum //
            , String cpoDtlLineNum //
            , NWZC150003PMsg pMsg3 // 2018/12/20 QC#28928 Add
            // , String rtrnLineStsCd // 2019/01/21 QC#29983 Add // 2019/04/10 S21_NA#31140 Del
            , String cpoDtlLineSubNum) {
        // 2019/04/10 S21_NA#31140 Del Start
        // 2019/01/21 S21_NA#29983 Add Start
        // if (S21StringUtil.isEquals(RTRN_LINE_STS.RECEIVED, rtrnLineStsCd)) {
        //     return false;
        // }
        // 2019/01/21 S21_NA#29983 Add End
        // 2019/04/10 S21_NA#31140 Del End
        // if (CONFIG_TP.RETURN_EXISTING_IB.equals(configTp) ||
        // CONFIG_TP.RETURN_NEW.equals(configTp)) {
        // In bound Y N Y
        if (NWXC150001DsCheck.matchConfigTp(glblCmpyCd, configTp, CONFIG_CATG.INBOUND, true, true, false)) { // S21_NA#955
            if (SVC_MACH_USG_STS.AT_CUSTOMER.equals(svcMachUsgStsCd)) {
                if (SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd) //
                        || SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(svcMachMstrStsCd)) {
                    //if (ZYPConstant.FLG_OFF_N.equals(allocFlg)) { S21_NA#3163 Mod. Condition
                    if (ZYPConstant.FLG_ON_Y.equals(allocFlg)) {
                        return false;
                    }
                    if (rtnDtlPMsg.cpoDtlLineNum_B1.getValue().equals(cpoDtlLineNum) //
                            && rtnDtlPMsg.cpoDtlLineSubNum_B1.getValue().equals(cpoDtlLineSubNum) //
                            && pCpoOrdNum.equals(cpoOrdNum)) {
                        return false;
                    }
                    // 2018/12/20 QC#28928 Add Start
                    NWZC150001Common.setMsgId3(pMsg3, NWZM2303E);
                    return true;
                    // 2018/12/20 QC#28928 Add End
                }
            }

            if (SVC_MACH_USG_STS.IN_TRANSIT.equals(svcMachUsgStsCd)) {
                if (SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachMstrStsCd)) {
                    if (ZYPConstant.FLG_OFF_N.equals(allocFlg)) {
//                        return false; // S21_NA#3505
//                    }
                        if (rtnDtlPMsg.cpoDtlLineNum_B1.getValue().equals(cpoDtlLineNum) //
                                && rtnDtlPMsg.cpoDtlLineSubNum_B1.getValue().equals(cpoDtlLineSubNum) //
                                && pCpoOrdNum.equals(cpoOrdNum)) {
                            return false;
                        }
                        // 2018/12/20 QC#28928 Add Start
                        NWZC150001Common.setMsgId3(pMsg3, NWZM2303E);
                        return true;
                        // 2018/12/20 QC#28928 Add End
                    }
                }
            }
            NWZC150001Common.setMsgId3(pMsg3, NWZM2302E); // 2018/12/20 QC#28928 Add
            return true;
        }

        return true;
    }

    // for Inbound
    private static boolean isExistsValidReferenceLine(List<NWZC150001_rtnDtlPMsg> dsLineNumByConfig, NWZC150001_rtnDtlPMsg line) {

        if (!ZYPCommonFunc.hasValue(line.dplyLineRefNum_B1)) {
            return true;
        }

        for (NWZC150001_rtnDtlPMsg refLine : dsLineNumByConfig) {
            if (S21StringUtil.isEquals(refLine.xxRqstTpCd_B1.getValue(), RQST_TP_DTL_CANCEL)) {
                continue;
            }
            String dplyRefLineNum = NWXC150001DsCheck.editDtlLineNum(refLine.dsOrdPosnNum_B1.getValue(), refLine.dsCpoLineNum_B1.getValue(), refLine.dsCpoLineSubNum_B1.getValue());
            if (S21StringUtil.isEquals(dplyRefLineNum, line.dplyLineRefNum_B1.getValue())) {
                // QC#10979 2016/10/27 Add Start
                ZYPEZDItemValueSetter.setValue(line.refCpoDtlLineNum_B1, refLine.cpoDtlLineNum_B1);
                ZYPEZDItemValueSetter.setValue(line.refCpoDtlLineSubNum_B1, refLine.cpoDtlLineSubNum_B1);
                // QC#10979 2016/10/27 Add End
                return true;
            }
        }
        return false;
    }

    private static String getOrdTakeMdse(String glblCmpyCd, String mdseCd) {

        // 2017/09/18 S21_NA#21009 Add Start
        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        int ordTakeCodeLen = ordTakeMdseTMsg.getAttr("ordTakeMdseCd").getDigit();
        // 2017/09/18 S21_NA#21009 Add End
        String mdseCd8 = null;
        if (ordTakeCodeLen < mdseCd.length()) {
            // 10MDSE -> 8MDSE
            mdseCd8 = S21StringUtil.subStringByLength(mdseCd, 0, ordTakeCodeLen);
        } else {
            mdseCd8 = mdseCd;
        }

        ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, mdseCd8);
        tMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.ordTakeMdseCd.getValue();
        }
        // 2017/09/18 S21_NA#21009 Add Start
        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        if (mdseTMsg != null) {
            return mdseTMsg.mdseCd.getValue();
        }
        // 2017/09/18 S21_NA#21009 Add End
        return null;
    }

    private static ALL_MDSE_VTMsg getMdseTMsg(String glblCmpyCd, String mdseCd) {

        ALL_MDSE_VTMsg cond = new ALL_MDSE_VTMsg();
        ALL_MDSE_VTMsgArray outAllMdseVTMsg = null;
        cond.setSQLID("003");
        cond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cond.setConditionValue("mdseCd01", mdseCd);

        outAllMdseVTMsg = (ALL_MDSE_VTMsgArray) S21ApiTBLAccessor.findByCondition(cond);
        if (outAllMdseVTMsg == null || outAllMdseVTMsg.length() == 0) {
            return null;
        } else {
            return (ALL_MDSE_VTMsg)outAllMdseVTMsg.get(0);
        }
    }

    // QC#25606 Del => 2018/08/21 S21_NA#26767 Reactivate, change return value
    // 2017/12/21 S21_NA#20050 Add Start
    // private static String hasOwnerError(NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg rtnDtlPMsg, String ownrAcctNum, Map<String, Boolean> ownerCfsMap) { // 2018/09/12 S21_NA#28182 Mod
    private static String hasOwnerError(NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg rtnDtlPMsg, String ownrAcctNum, Map<String, Boolean> ownerCfsMap, MDSETMsg mdseTMsg) {

        if (ownrAcctNum == null) {
            return null;
        }

        Boolean isOwnerCfs = ownerCfsMap.get(ownrAcctNum);
        if (isOwnerCfs == null) {
            boolean isOwnerCfsBoolean = NWXC150001DsCheck.isCfsBillToAcct(pMsg.glblCmpyCd.getValue(), //
                    ownrAcctNum, //
                    pMsg.slsDt.getValue());
            isOwnerCfs = new Boolean(isOwnerCfsBoolean);
            ownerCfsMap.put(ownrAcctNum, isOwnerCfs);
        }

        if (!isOwnerCfs.booleanValue()) {
            return null;
        }
        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, rtnDtlPMsg.rtlWhCd_B1);
        rtlWhTMsg = (RTL_WHTMsg) S21CacheTBLAccessor.findByKey(rtlWhTMsg);

        // 2018/02/01 S21_NA#23572 Add Start
        if (rtlWhTMsg == null) {
            // QC#24065 2018/02/09 Mod Start
            //return true;
            return null;
            // QC#24065 2018/02/09 Mod End
        }
        // 2018/02/01 S21_NA#23572 Add End
        // 2018/08/21 S21_NA#26767 Add Start
        boolean isSvcExchamngeLine = NWXC150001CommonReturnReason.avalLeaseCsaReturnRtrnRsn(pMsg.glblCmpyCd.getValue(), rtnDtlPMsg.rtrnRsnCd_B1.getValue());
        if (isSvcExchamngeLine) {
            RTRN_RSNTMsg rtrnRsnTMsg = new RTRN_RSNTMsg();
            ZYPEZDItemValueSetter.setValue(rtrnRsnTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rtrnRsnTMsg.rtrnRsnCd, rtnDtlPMsg.rtrnRsnCd_B1);
            rtrnRsnTMsg = (RTRN_RSNTMsg) S21CacheTBLAccessor.findByKey(rtrnRsnTMsg);
            if (rtrnRsnTMsg != null) {
                if (S21StringUtil.isEquals(rtlWhTMsg.invtyOwnrCd.getValue(), rtrnRsnTMsg.invtyOwnrCd.getValue())) {
                    return null;
                } else {
                    if (S21StringUtil.isEquals(INVTY_OWNR.CSA, rtrnRsnTMsg.invtyOwnrCd.getValue())) {
                        return "NWZM2278E";
                    } else if (S21StringUtil.isEquals(INVTY_OWNR.CFS, rtrnRsnTMsg.invtyOwnrCd.getValue())) {
                        return "NWZM2279E";
                    } else if (S21StringUtil.isEquals(INVTY_OWNR.GMD, rtrnRsnTMsg.invtyOwnrCd.getValue())) {
                        return "NWZM2280E";
                    } else if (S21StringUtil.isEquals(INVTY_OWNR.BSD, rtrnRsnTMsg.invtyOwnrCd.getValue())) {
                        return "NWZM2281E";
                    } else if (S21StringUtil.isEquals(INVTY_OWNR.SHO, rtrnRsnTMsg.invtyOwnrCd.getValue())) {
                        return "NWZM2282E";
                    } else if (S21StringUtil.isEquals(INVTY_OWNR.OTH, rtrnRsnTMsg.invtyOwnrCd.getValue())) {
                        return "NWZM2283E";
                    } else {
                        return "NWZM2284E";
                    }
                }
            }
        }
        // 2018/08/21 S21_NA#26767 Add End

        // START 2021/04/26 A.Marte [QC#58646, MOD]
        //If return reason is CUSTOMER_TRADE_IN ignore checking for Owner
        if (!RTRN_RSN.CUSTOMER_TRADE_IN.equals(rtnDtlPMsg.rtrnRsnCd_B1.getValue()))
        {
            if (isOwnerCfs.booleanValue() //
                    // && !S21StringUtil.isEquals(INVTY_OWNR.CFS, rtlWhTMsg.invtyOwnrCd.getValue())) { // 2018/09/12 S21_NA#28182 Mod
                    && !S21StringUtil.isEquals(INVTY_OWNR.CFS, rtlWhTMsg.invtyOwnrCd.getValue()) && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.instlBaseCtrlFlg.getValue())) {
                return NWZM2250E;
            }
        }
        //default return if no error is encountered
        return null;
        // END 2021/04/26 A.Marte [QC#58646, MOD]
    }
    // 2017/12/21 S21_NA#20050 Add End

    // 2018/05/20 S21_NA#25604 Add Start
    private static boolean hasMainMachineLineConfig(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configPMsg) {

        if (!S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configPMsg.configCatgCd.getValue())) {
            return false;
        }

        // Add Start 2019/08/29 QC#52967
        int ordTakeCodeLen = getOrdTakeCodeLen();
        String mainMdseCd = null;
        boolean isOrdTakeMdse = false;
        if (ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)) {
            SVC_MACH_MSTRTMsg mainSvcMachMstr = getMainSvcMachMstr(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue());

            if (null != mainSvcMachMstr) {
                mainMdseCd = getOrdTakeMdseCd(pMsg.glblCmpyCd.getValue(), mainSvcMachMstr.mdseCd.getValue());

                if (null != mainMdseCd) {
                    isOrdTakeMdse = true;
                } else {
                    isOrdTakeMdse = false;
                    mainMdseCd = mainSvcMachMstr.mdseCd.getValue();
                }
            }
        }
        // Add End 2019/08/29 QC#52967

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            if (S21StringUtil.isEquals(configPMsg.dsOrdPosnNum.getValue(), pMsg.A.no(i).dsOrdPosnNum_A1.getValue())) {
                MDSETMsg mdseTMsg = NWZC150001CpouExistsCdInDbCheck.getMdse(glblCmpyCd, pMsg.A.no(i).mdseCd_A1.getValue());
                if (mdseTMsg != null && NWXC150001DsCheck.isMainMachineType(glblCmpyCd, mdseTMsg.coaMdseTpCd.getValue())) {
                    return true;
                }
            }

            // Add Start 2019/08/29 QC#52967
            String targetMdseCd = getOutboundMdse(pMsg.A.no(i).mdseCd_A1.getValue(), isOrdTakeMdse, ordTakeCodeLen);
            if (S21StringUtil.isEquals(mainMdseCd, targetMdseCd)) {
                return true;
            }
            // Add End 2019/08/29 QC#52967
        }
        return false;
    }

    private static boolean hasMainMachineRmaConfig(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg rmaConfigPMsg) {

        if (!S21StringUtil.isEquals(CONFIG_CATG.INBOUND, rmaConfigPMsg.configCatgCd.getValue())) {
            return false;
        }
        BigDecimal mainSvcMachMstrPk = getMainMachineSvcMachMstrPk(pMsg.glblCmpyCd.getValue(), rmaConfigPMsg.svcConfigMstrPk.getValue());
        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            if (S21StringUtil.isEquals(rmaConfigPMsg.dsOrdPosnNum.getValue(), pMsg.rtnDtl.no(i).dsOrdPosnNum_B1.getValue()) //
                    && isSameBigDecimalObject(mainSvcMachMstrPk, pMsg.rtnDtl.no(i).svcMachMstrPk_B1.getValue())) {
                return true;
            }
        }
        return false;
    }

    private static BigDecimal getMainMachineSvcMachMstrPk(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = getSvcConfigMstr(glblCmpyCd, svcConfigMstrPk);
        if (svcConfigMstrTMsg != null) {
            return svcConfigMstrTMsg.svcMachMstrPk.getValue();
        }
        return null;
    }

    private static SVC_CONFIG_MSTRTMsg getSvcConfigMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        SVC_CONFIG_MSTRTMsg tMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, svcConfigMstrPk);

        return (SVC_CONFIG_MSTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    private static boolean isSameBigDecimalObject(BigDecimal cmp1, BigDecimal cmp2) {

        if (cmp1 == null || cmp2 == null) {
            return false;
        }
        return cmp1.compareTo(cmp2) ==  0;
    }
    // 2018/05/20 S21_NA#25604 Add End

    // QC#24705 2018/06/18 add Start
    private static void otherManualCreditRebill(NWZC150001PMsg pMsg, 
                List<NWZC150002PMsg> pMsg2List,
                S21ApiMessageIdMgr msgIdMgr) {

        // 2018/06/22 S21_NA#24256 Add Start
        NWZC150001OrdLineValSetCache ordLineValSetCache = NWZC150001OrdLineValSetCache.getInstance();
        // 2018/06/22 S21_NA#24256 Add End

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            
            // 2019/02/07 S21_NA#30157 Add Start
            if (ZYPCommonFunc.hasValue(aPMsg.crRebilCd_A1)) {
                continue;
            }
            // 2019/02/07 S21_NA#30157 Add End

            // 2018/06/22 S21_NA#24256 Mod Start
//            ORD_LINE_VAL_SETTMsg ordLineValSet = new ORD_LINE_VAL_SETTMsg();
//            ordLineValSet.setSQLID("001");
//            ordLineValSet.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//            ordLineValSet.setConditionValue("ordLineCtxTpCd01", ORD_LINE_CTX_TP.FORCE_DUMMY_WH);
//            ordLineValSet.setConditionValue("dsOrdLineCatgCd01", aPMsg.dsOrdLineCatgCd_A1.getValue());
//
//            ORD_LINE_VAL_SETTMsgArray ordLineValSetAry = (ORD_LINE_VAL_SETTMsgArray) EZDTBLAccessor.findByCondition(ordLineValSet);

            NWZC150001CpouFindCondition findCondition = new NWZC150001CpouFindCondition("001");
            findCondition.addCondition("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            findCondition.addCondition("ordLineCtxTpCd01", ORD_LINE_CTX_TP.FORCE_DUMMY_WH);
            findCondition.addCondition("dsOrdLineCatgCd01", aPMsg.dsOrdLineCatgCd_A1.getValue());

            ORD_LINE_VAL_SETTMsgArray ordLineValSetAry = ordLineValSetCache.getTMsgArray(findCondition);
            // 2018/06/22 S21_NA#24256 Mod End
            if (ordLineValSetAry.getValidCount() == 0) {
                continue;
            }

            ORD_LINE_VAL_SETTMsg ordLineValSetTMsg = ordLineValSetAry.no(0);
            String crRebilCd = ordLineValSetTMsg.thirdBizCtxAttrbTxt.getValue();
            BigDecimal netPrice = aPMsg.entDealNetUnitPrcAmt_A1.getValue();

            if (CR_REBIL.CREDIT.equals(crRebilCd)
                    && BigDecimal.ZERO.compareTo(netPrice) < 0) {
                NWZC150001Common.addMsgId2List(pMsg2List, aPMsg, NWZM2264E);

            } else if (CR_REBIL.REBILL.equals(crRebilCd)
                    && BigDecimal.ZERO.compareTo(netPrice) > 0) {
                NWZC150001Common.addMsgId2List(pMsg2List, aPMsg, NWZM2265E);
            }

        }
    }
    // QC#24705 2018/06/18 add End

    // Add Start 2018/11/28 QC#29252
    /**
     * checkCreditCardReq
     * @param pMsg NWZC150001PMsg
     * @param glblCmpyCd String
     * @param dsOrdCatgCd String
     * @param dsOrdTpCd String
     * @param dsOrdRsnCd String
     * @param billToCustCd String
     * @param pmtTermCashDiscCd String
     * @param msgIdMgr S21ApiMessageIdMgr
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    private static boolean checkCreditCardReq(NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr, ONBATCH_TYPE onBatchType) {

        if (!MODE_SUBMIT.equals(pMsg.xxModeCd.getValue())) {
            return false;
        }

        // 2019/09/21 S21_NA#52988 Add Start
        if (CPO_SRC_TP.SUPPLY_RELEASE_SCREEN.equals(pMsg.cpoSrcTpCd.getValue())) {

            BigDecimal amt = BigDecimal.ZERO;

            for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {

                NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(i);

                for (int j = 0; j < pMsg.A.getValidCount(); j++) {

                    NWZC150001_APMsg dtlPMsg = pMsg.A.no(j);

                    if (!cpoConfigPMsg.dsOrdPosnNum.getValue().equals(dtlPMsg.dsOrdPosnNum_A1.getValue())) {
                        continue;
                    }

                    if (NWZC150001Query.getInstance().isExistsSplyReln(pMsg, dtlPMsg, cpoConfigPMsg) //
                            || NWZC150001Query.getInstance().isExistsSplyRelnForOrdTakeMdse(pMsg, dtlPMsg, cpoConfigPMsg)) {
                        continue;
                    }

                    amt = amt.add(dtlPMsg.ordQty_A1.getValue().multiply(dtlPMsg.funcPrcListPrcAmt_A1.getValue()));
                }
            }

            if (BigDecimal.ZERO.compareTo(amt) == 0) {
                return false;
            }
        // QC#58901 Add Start
        } else if (CPO_SRC_TP.SCHEDULE_AGREEMENT_ENTRY.equals(pMsg.cpoSrcTpCd.getValue())) {
            BigDecimal amt = BigDecimal.ZERO;
            for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {

                NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(i);

                for (int j = 0; j < pMsg.A.getValidCount(); j++) {

                    NWZC150001_APMsg dtlPMsg = pMsg.A.no(j);

                    if (!cpoConfigPMsg.dsOrdPosnNum.getValue().equals(dtlPMsg.dsOrdPosnNum_A1.getValue())) {
                        continue;
                    }

                    amt = amt.add(dtlPMsg.ordQty_A1.getValue().multiply(dtlPMsg.funcPrcListPrcAmt_A1.getValue()));
                }
            }

            if (BigDecimal.ZERO.compareTo(amt) == 0) {
                return false;
            }
        }
        // QC#58901 End

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String billToCustCd = pMsg.billToCustCd.getValue();
        String pmtTermCashDiscCd = pMsg.addPmtTermCashDiscCd.getValue();

        PMT_TERM_CASH_DISCTMsg pmtTermTMsg = getPmtTerm(glblCmpyCd, pmtTermCashDiscCd);

        if (pmtTermTMsg == null) {
            return false;
        }

        Map<String, Object> resultMap = NWZC150001Query.getInstance().getDsTrxRuleTpCd(pMsg);
        String dsTrxRuleTpCd = (String) resultMap.get("FIRST_BIZ_CTX_ATTRB_TXT");

        NMZC610001PMsg custInfoGetApiMsg = callCustInfoGetApiModeTranByBillTo(glblCmpyCd, dsTrxRuleTpCd, billToCustCd, onBatchType);

        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
            List<String> ml = S21ApiUtil.getXxMsgIdList(custInfoGetApiMsg);
            for (String msgId : ml) {
                msgIdMgr.addXxMsgId(msgId, pMsg);
            }
        }

        for (int i = 0; i < custInfoGetApiMsg.TransactionRuleList.getValidCount(); i++) {
            NMZC610001_TransactionRuleListPMsg tranpMsg = custInfoGetApiMsg.TransactionRuleList.no(i);

            if (ZYPConstant.FLG_ON_Y.equals(tranpMsg.dsCrCardReqFlg.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(pmtTermTMsg.pmtCcFlg.getValue())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @param glblCmpyCd String
     * @param pmtTermCashDiscCd String
     * @return PMT_TERM_CASH_DISCTMsg
     */
    private static PMT_TERM_CASH_DISCTMsg getPmtTerm(String glblCmpyCd, String pmtTermCashDiscCd) {
        PMT_TERM_CASH_DISCTMsg pmtTermTMsg = new PMT_TERM_CASH_DISCTMsg();

        ZYPEZDItemValueSetter.setValue(pmtTermTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmtTermTMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);

        return (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(pmtTermTMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsTrxRuleTpCd String
     * @param billToCustCd String
     * @param onBatchType ONBATCH_TYPE
     * @return NMZC610001PMsg
     */
    private static NMZC610001PMsg callCustInfoGetApiModeTranByBillTo(String glblCmpyCd, String dsTrxRuleTpCd, String billToCustCd, ONBATCH_TYPE onBatchType) {
        NMZC610001PMsg custInfoGetApiMsg = new NMZC610001PMsg();

        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsTrxRuleTpCd, dsTrxRuleTpCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.billToCustCd, billToCustCd);

        new NMZC610001().execute(custInfoGetApiMsg, onBatchType);

        return custInfoGetApiMsg;
    }
    // Add End 2018/11/28 QC#29252
    
    // 2019/02/22 S21_NA#30449 Add Start
    /**
     * is Contract Supply Order
     * @param pMsg NWZC150001PMsg
     * @return contract supply: true, other:false
     */
    private static boolean isContrSupOrd(NWZC150001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.dsOrdCatgCd)) {
            return false;
        }
        String flg = NWZC150001Query.getInstance().getOrdCatgBizCtx(pMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CONTRACT_SUPPLY_ORDER_CATEGORY,
                pMsg.dsOrdCatgCd.getValue(), null);
        if (ZYPCommonFunc.hasValue(flg)) {
            return true;
        }
        return false;
    }
    // 2019/02/22 S21_NA#30449 Add End

    // Add Start 2019/08/27 QC#52967
    private static int getOrdTakeCodeLen() {
        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        return ordTakeMdseTMsg.getAttr("ordTakeMdseCd").getDigit();
    }

    private static SVC_MACH_MSTRTMsg getMainSvcMachMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        BigDecimal mainSvcMachMstrPk = getMainMachineSvcMachMstrPk(glblCmpyCd, svcConfigMstrPk);
        if (null != mainSvcMachMstrPk) {
            SVC_MACH_MSTRTMsg mainSvcMachMstrTMsg = getSvcMachMstr(glblCmpyCd, mainSvcMachMstrPk);

            return mainSvcMachMstrTMsg;
        }

        return null;
    }

    private static SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);

        return (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    private static String getOrdTakeMdseCd(String glblCmpyCd, String mdseCd) {

        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        int ordTakeCodeLen = ordTakeMdseTMsg.getAttr("ordTakeMdseCd").getDigit();
        String mdseCd8 = null;
        if (ordTakeCodeLen < mdseCd.length()) {
            // 10MDSE -> 8MDSE
            mdseCd8 = S21StringUtil.subStringByLength(mdseCd, 0, ordTakeCodeLen);
        } else {
            mdseCd8 = mdseCd;
        }

        ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, mdseCd8);
        tMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.ordTakeMdseCd.getValue();
        }

        return null;
    }

    private static String getOutboundMdse(String mdseCd, boolean isOrdTakeMdse, int ordTakeCodeLen) {

        if (isOrdTakeMdse) {
            // 10MDSE -> 8MDSE
            if (ordTakeCodeLen < mdseCd.length()) {
                return S21StringUtil.subStringByLength(mdseCd, 0, ordTakeCodeLen);
            }
        }

        return mdseCd;
    }
    // Add End 2019/08/27 QC#52967

    /**
     * add value of BigDecimal
     * @param bc1 BigDecimal
     * @param bc2 BigDecimal
     * @return BigDecimal
     */
    private static BigDecimal add(BigDecimal bc1, BigDecimal bc2) {
        if (!ZYPCommonFunc.hasValue(bc1)) {
            bc1 = BigDecimal.ZERO;
        }
        if (!ZYPCommonFunc.hasValue(bc2)) {
            bc2 = BigDecimal.ZERO;
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
        if (!ZYPCommonFunc.hasValue(bc1)) {
            bc1 = BigDecimal.ZERO;
        }
        if (!ZYPCommonFunc.hasValue(bc2)) {
            bc2 = BigDecimal.ZERO;
        }
        return bc1.subtract(bc2);
    }

    // START 2022/08/24 F.Fadriquela [QC#60482, ADD]
    /**
     * isCpoDtlLineShipped
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param cpoDtlLineNum String
     * @param cpoDtlLineSubNum String
     * @return boolean
     */
    private static boolean isCpoDtlLineShipped(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        SHPG_PLNTMsgArray isShpgPlnShipped = null;
        shpgPlnTMsg.setSQLID("038");
        shpgPlnTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        shpgPlnTMsg.setConditionValue("shpgStsCd01", SHPG_STS.SHIPPED);
        shpgPlnTMsg.setConditionValue("trxHdrNum01", cpoOrdNum);
        shpgPlnTMsg.setConditionValue("trxLineNum01", cpoDtlLineNum);
        shpgPlnTMsg.setConditionValue("trxLineSubNum01", cpoDtlLineSubNum);

        isShpgPlnShipped = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByCondition(shpgPlnTMsg);

        if (isShpgPlnShipped == null || isShpgPlnShipped.length() == 0) {
            return false;
        }

        return true;
    }
    // END 2022/08/24 F.Fadriquela [QC#60482, ADD]
}
