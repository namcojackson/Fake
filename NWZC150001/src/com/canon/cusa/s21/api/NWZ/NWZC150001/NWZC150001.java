/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001;

import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.DATE_TIME_PATTERN;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_CHANGE_MODIFICATION;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_COPY_ALL;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_COPY_ALL_RETURN;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_COPY_HEADER;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_DELETE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_SAVE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_SUBMIT;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_VALID;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0011E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0073E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0074E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0081E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0346E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0977E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1368E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1946E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1950E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1951W;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1952E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1992E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1993E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1994E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1996E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1997E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2011E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2012E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2324E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2325W;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.PMTC_APVL_STS_APPLOVED;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.PMTC_PROC_STS_SUCCESS;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_CTAC_PSN_DELETE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_CTAC_PSN_MODIFY;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_CTAC_PSN_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_DELY_INFO_DELETE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_DELY_INFO_MODIFY;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_DELY_INFO_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_DTL_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_DTL_MODIFY;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_DTL_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_RTRN_DTL_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_RTRN_DTL_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_SITE_SRVY_DELETE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_SITE_SRVY_MODIFY;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_SITE_SRVY_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RTL_WH_BILL_ONLY;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.STR_COMMA;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.VAR_CHAR_CONST_NM_ITT_HOLD_RELEASE_MEMO;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.AUTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.CPO_VTMsg;
import business.db.CPO_VTMsgArray;
import business.db.CR_CARD_TRXTMsg;
import business.db.CR_CARD_TRXTMsgArray;
import business.db.DS_CPO_ISTL_INFOTMsg;
import business.db.DS_CPO_ISTL_INFOTMsgArray;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsgArray;
import business.db.DS_CR_CARDTMsg;
import business.db.DS_MDLTMsg;
import business.db.HLDTMsg;
import business.db.HLDTMsgArray;
import business.db.PRC_DTLTMsg;
import business.db.PRC_DTLTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.parts.NPZC103001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NSZC115001PMsg;
import business.parts.NWZC033001PMsg;
import business.parts.NWZC042001PMsg;
import business.parts.NWZC044001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_cpoCtacPsnInfoListPMsg;
import business.parts.NWZC150001_cpoDlvyInfoListPMsg;
import business.parts.NWZC150001_cpoIstlInfoListPMsg;
import business.parts.NWZC150001_cpoSlsCrPMsg;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150001_siteSrvInfoListPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC152001PMsg;
import business.parts.NWZC155001PMsg;
import business.parts.NWZC164001PMsg;
import business.parts.NWZC177001PMsg;
import business.parts.NWZC183001PMsg;
import business.parts.NWZC203001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NSZ.NSZC115001.NSZC115001;
import com.canon.cusa.s21.api.NSZ.NSZC115001.constant.NSZC115001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC033001.NWZC033001;
import com.canon.cusa.s21.api.NWZ.NWZC042001.NWZC042001;
import com.canon.cusa.s21.api.NWZ.NWZC044001.NWZC044001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.NWZC150001CpoConfMain;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.NWZC150001CpouDoValidation;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.NWZC150001CpouUpdateOrderData;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouHldBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.derive.NWZC150001CpouEditAmount;
import com.canon.cusa.s21.api.NWZ.NWZC150001.validation.NWZC150001ForDsCheck;
import com.canon.cusa.s21.api.NWZ.NWZC152001.NWZC152001;
import com.canon.cusa.s21.api.NWZ.NWZC155001.NWZC155001;
import com.canon.cusa.s21.api.NWZ.NWZC164001.NWZC164001;
import com.canon.cusa.s21.api.NWZ.NWZC177001.NWZC177001;
import com.canon.cusa.s21.api.NWZ.NWZC183001.NWZC183001;
import com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_REL_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TXT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 * <pre>
 * DS CPO Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         M.Yamada        Create          CSA
 * 2015/11/18   Fujitsu         S.Takami        Update          QC#799
 * 2015/11/19   Fujitsu         T.Ishii         Update          S21_NA#852
 * 2015/11/19   Fujitsu         S.Takami        Update          S21_NA#894
 * 2015/11/20   Fujitsu         S.Takami        Update          S21_NA#1006
 * 2015/11/30   Fujitsu         S.Takami        Update          S21_NA#1081
 * 2015/12/03   Fujitsu         T.Ishii         Update          S21_NA#1405
 * 2015/12/08   Fujitsu         T.Ishii         Update          S21_NA#1331
 * 2015/12/11   Fujitsu         S.Takami        Update          S21_NA#1809
 * 2015/12/14   Fujitsu         T.Ishii         Update          S21_NA#1880
 * 2015/12/15   Fujitsu         T.Ishii         Update          S21_NA#1957
 * 2015/12/15   Fujitsu         T.Ishii         Update          S21_NA#1952
 * 2015/12/16   Fujitsu         S.Takami        Update          S21_NA#2007
 * 2015/12/17   Fujitsu         T.Yoshida       Update          S21_NA#1987
 * 2015/12/17   Fujitsu         T.Ishii         Update          S21_NA#2024
 * 2015/12/17   Fujitsu         S.Takami        Update          S21_NA#2007
 * 2015/12/18   Fujitsu         T.Yoshida       Update          S21_NA#1643
 * 2015/12/21   Fujitsu         S.Takami        Update          S21_NA#2189
 * 2015/12/24   Fujitsu         S.Takami        Update          S21_NA#2411, S21_NA#2123
 * 2016/01/04   Fujitsu         S.Takami        Update          S21_NA#2596
 * 2016/01/06   Fujitsu         T.Ishii         Update          S21_NA#2595
 * 2016/01/06   Fujitsu         S.Takami        Update          S21_NA#2582
 * 2016/01/07   Fujitsu         S.Takami        Update          S21_NA#2588
 * 2016/01/08   Fujitsu         S.Takami        Update          S21_NA#2899
 * 2016/01/13   Fujitsu         S.Takami        Update          S21_NA#2726
 * 2016/01/14   Fujitsu         S.Takami        Update          S21_NA#2996
 * 2016/01/14   Fujitsu         S.Takami        Update          S21_NA#3163
 * 2016/01/25   Fujitsu         S.Yamamoto      Update          S21_NA#3505
 * 2016/01/26   Fujitsu         S.Takami        Update          S21_NA#3740
 * 2016/01/28   Fujitsu         S.Yamamoto      Update          S21_NA#3869
 * 2016/02/02   Fujitsu         S.Takami        Update          S21_NA#3375, #3970
 * 2016/02/10   Fujitsu         Y.Kanefusa      Update          S21_NA#3250
 * 2016/02/12   Fujitsu         T.ishii         Update          S21_NA#4098
 * 2016/02/17   Fujitsu         T.Yoshida       Update          S21_NA#1703
 * 2016/02/18   Fujitsu         Y.Kanefusa      Update          S21_NA#4212
 * 2016/02/22   Fujitsu         M.Ohno          Update          S21_NA#3112
 * 2016/02/23   Fujitsu         M.Hara          Update          S21_NA#4078
 * 2016/02/24   Fujitsu         K.Sato          Update          S21_NA#3239
 * 2016/02/25   Fujitsu         S.Takami        Update          S21_NA#4440
 * 2016/02/25   Fujitsu         K.Sato          Update          S21_NA#1704
 * 2016/03/04   Fujitsu         S.Takami        Update          S21_NA#5000 (#28)
 * 2016/03/16   Fujitsu         S.Takami        Update          S21_NA#5519
 * 2016/04/05   Fujitsu         T.Yoshida       Update          S21_NA#6568
 * 2016/04/15   Fujitsu         S.Takami        Update          S21_NA#5321
 * 2016/04/18   Fujitsu         S.Takami        Update          S21_NA#5321-2
 * 2016/04/19   Fujitsu         Y.Taoka         Update          S21_NA#7263,S21_NA#7271
 * 2016/04/22   Fujitsu         S.Takami        Update          S21_NA#7449, S21_NA#7132
 * 2016/04/28   Fujitsu         S.Takami        Update          S21_NA#7516
 * 2016/04/28   Fujitsu         S.Takami        Update          S21_NA#7631
 * 2016/05/09   Fujitsu         T.Ishii         Update          S21_NA#8018
 * 2016/05/09   Fujitsu         Y.Taoka         Update          S21_NA#7750
 * 2016/05/13   Fujitsu         M.Hara          Update          S21_NA#8297
 * 2016/05/13   Fujitsu         S.Takami        Update          S21_NA#8072
 * 2016/05/17   Fujitsu         Y.Taoka         Update          S21_NA#8372
 * 2016/05/31   Fujitsu         T.Yoshida       Update          S21_NA#8166
 * 2016/06/02   Fujitsu         T.Murai         Update          S21_NA#9107
 * 2016/06/03   Fujitsu         M.Yamada        Update          S21_NA#5395
 * 2016/06/02   Fujitsu         S.Takami        Update          S21_NA#8464
 * 2016/06/06   Fujitsu         S.Takami        Update          S21_NA#9278
 * 2016/06/07   Fujitsu         S.Yamamoto      Update          S21_NA#9273
 * 2016/06/09   Fujitsu         S.Takami        Update          S21_NA#9426
 * 2016/06/09   Fujitsu         S.Takami        Update          S21_NA#9499
 * 2016/06/13   Fujitsu         S.Iidaka        Update          S21_NA#9649
 * 2016/06/16   Fujitsu         S.Takami        Update          S21_NA#9855
 * 2016/06/17   Fujitsu         S.Takami        Update          S21_NA#9679
 * 2016/06/21   Fujitsu         Y.Kanefusa      Update          S21_NA#9437
 * 2016/07/01   Fujitsu         S.Takami        Update          S21_NA#9426-2
 * 2016/07/04   Fujitsu         S.Takami        Update          S21_NA#7821
 * 2016/07/12   Fujitsu         S.Takami        Update          S21_NA#9426-4
 * 2016/07/13   Fujitsu         M.Hara          Update          S21_NA#11629
 * 2016/07/13   Fujitsu         S.Yamamoto      Update          S21_NA#11737
 * 2016/07/19   Fujitsu         Y.Taoka         Update          S21_NA#11297
 * 2016/07/19   Fujitsu         S.Takami        Update          S21_NA#11866
 * 2016/07/21   Fujitsu         S.Takami        Update          S21_NA#9228
 * 2016/07/22   Fujitsu         S.Takami        Update          S21_NA#11970
 * 2016/07/26   Fujitsu         S.Takami        Update          S21_NA#11866-2
 *                                                  (Some Fixing code on S21_NA#11866 are deleted without any comments.)
 * 2016/07/26   Fujitsu         S.Takami        Update          S21_NA#11982
 * 2016/07/28   Fujitsu         S.Takami        Update          S21_NA#11982-2
 * 2016/07/29   Fujitsu         Y.Taoka         Update          S21_NA#11297
 * 2016/07/29   Fujitsu         T.Ishii         Update          S21_NA#11280
 * 2016/08/01   Fujitsu         S.Takami        Update          S21_NA#12637
 * 2016/08/01   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2016/08/04   Fujitsu         T.Yoshida       Update          S21_NA#12895
 * 2016/08/08   Fujitsu         S.Takami        Update          S21_NA#8300
 * 2016/08/10   Fujitsu         S.Takami        Update          S21_NA#5394
 * 2016/08/22   Fujitsu         S.Takami        Update          S21_NA#5394-2
 * 2016/08/24   Fujitsu         Y.Taoka         Update          S21_NA#11630
 * 2016/08/29   Fujitsu         H.Nagashima     Update          S21_NA#12154
 * 2016/08/30   SRAA            K.Aratani       Update          S21_NA#14021
 * 2016/08/31   Fujitsu         T.Murai         Update          S21_NA#11547
 * 2016/09/05   Fujitsu         W.Honda         Update          S21_NA#12435
 * 2016/09/05   Fujitsu         S.Takami        Update          S21_NA#6100
 * 2016/09/08   Fujitsu         M.Yamada        Update          S21_NA#5568
 * 2016/09/13   Fujitsu         M.Yamada        Update          S21_NA#10755
 * 2016/09/14   Fujitsu         Y.Taoka         Update          S21_NA#8003
 * 2016/09/16   Fujitsu         Y.Taoka         Update          S21_NA#13796
 * 2016/09/21   Fujitsu         Y.Taoka         Update          S21_NA#14107
 * 2016/09/23   Fujitsu         T.Yoshida       Update          S21_NA#10321-20
 * 2016/09/28   Fujitsu         S.Takami        Update          S21_NA#14264
 * 2016/09/29   Fujitsu         S.Takami        Update          S21_NA#14867
 * 2016/09/30   Fujitsu         H.Ikeda         Update          S21_NA#14329
 * 2016/10/04   Fujitsu         S.Takami        Update          S21_NA#9215
 * 2016/10/04   Fujitsu         N.Sugiura       Update          S21_NA#13170
 * 2016/10/06   Fujitsu         S.Iidaka        Update          S21_NA#13905
 * 2016/10/06   Fujitsu         Y.Kanefusa      Update          S21_NA#14608
 * 2016/10/11   Fujitsu         S.Iidaka        Update          S21_NA#13022
 * 2016/10/13   Fujitsu         S.Takami        Update          S21_NA#7924-2
 * 2016/10/18   Fujitsu         S.Takami        Update          S21_NA#9362
 * 2016/10/25   Fujitsu         S.Takami        Update          S21_NA#9705
 * 2016/10/27   Fujitsu         Y.Kanefusa      Update          S21_NA#10979
 * 2016/11/01   Fujitsu         S.Iidaka        Update          S21_NA#10965
 * 2016/11/17   Fujitsu         M.Yamada        Update          S21_NA#14815
 * 2016/12/14   Fujitsu         W.Honda         Update          S21_NA#16588
 * 2016/12/26   Fujitsu         S.Takami        Update          S21_NA#16321
 * 2016/12/27   Fujitsu         S.Ohki          Update          S21_NA#13768-2
 * 2017/01/11   Fujitsu         M.Ohno          Update          S21_NA#16973
 * 2017/01/12   Fujitsu         M.Ohno          Update          S21_NA#16655
 * 2017/01/19   Fujitsu         M.Ohno          Update          S21_NA#13768-3
 * 2017/02/17   Fujitsu         T.Yoshida       Update          S21_NA#17536
 * 2017/02/21   Fujitsu         Y.Kanefusa      Update          S21_NA#17474
 * 2017/02/23   Fujitsu         T.Yoshida       Update          S21_NA#17251 
 * 2017/02/22   Fujitsu         N.Aoyama        Update          S21_NA#16575
 * 2017/03/02   Fujitsu         S.Takami        Update          S21_NA#17714-2
 * 2017/03/03   Fujitsu         S.Takami        Update          S21_NA#17714-3
 * 2017/03/09   Fujitsu         S.Takami        Update          S21_NA#17979
 * 2017/03/14   Fujitsu         T.Aoi           Update          S21_NA#16987
 * 2017/06/13   Fujitsu         H.Ikeda         Update          QC#18820
 * 2017/06/13   Fujitsu         S.Takami        Update          S21_NA#18869-2
 * 2017/06/16   Fujitsu         S.Takami        Update          S21_NA#19288
 * 2017/06/30   Fujitsu         S.Takami        Update          S21_NA#18811
 * 2017/07/03   Fujitsu         S.Takami        Update          S21_NA#19737
 * 2017/07/10   Fujitsu         S.Takami        Update          S21_NA#19828
 * 2017/08/15   Fujitsu         N.Sugiura       Update          S21_NA#16452
 * 2017/09/19   Fujitsu         T.Murai         Update          S21_NA#18859(Sol#154)
 * 2017/10/16   Fujitsu         S.Takami        Update          S21_NA#21808
 * 2017/10/16   Fujitsu         S.Takami        Update          S21_NA#21267
 * 2017/10/20   Fujitsu         H.Sugawara      Update          QC#21773
 * 2017/10/26   Fujitsu         S.Takami        Update          S21_NA#21820
 * 2017/11/07   Fujitsu         S.Takami        Update          S21_NA#22351
 * 2017/11/09   Fujitsu         S.Takami        Update          S21_NA#22091
 * 2017/11/21   Fujitsu         W.Honda         Update          S21_NA#22708
 * 2018/01/31   Fujitsu         M.Ohno          Update          S21_NA#23563
 * 2018/03/14   Fujitsu         K.Ishizuka      Update          S21_NA#24458
 * 2018/04/17   Fujitsu         M.Ohno          Update          S21_NA#24458
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/06/25   Fujitsu         T.Aoi           Update          S21_NA#20154
 * 2018/07/10   Fujitsu         A.Kosai         Update          S21_NA#25797
 * 2018/07/23   Fujitsu         S.Takami        Update          S21_NA#24745
 * 2018/08/01   Fujitsu         Mz.Takahashi    Update          S21_NA#27087
 * 2018/08/02   Fujitsu         S.Takami        Update          S21_NA#25404
 * 2019/02/08   Fujitsu         K.Ishizuka      Update          S21_NA#30270
 * 2019/03/27   Fujitsu         R.Nakamura      Update          S21_NA#30940
 * 2019/06/03   Fujitsu         C.Hara          Update          QC#50555
 * 2019/08/02   Fujitsu         T.Noguchi       Update          S21_NA#52127
 * 2019/09/27   Fujitsu         R.Matsuki       Update          QC#53593
 * 2019/11/06   Fujitsu         S.Kosaka        Update          QC#54413
 * 2019/11/12   Fujitsu         S.Kosaka        Update          QC#54413-1
 * 2019/11/22   Fujitsu         C.Hara          Update          QC#54213
 * 2019/12/14   Fujitsu         Mz.Takahashi    Update          QC#53588
 * 2020/06/09   CITS            K.Ogino         Update          QC#56636
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 * 2023/05/11   Hitachi         T.Doi           Update          CSA-QC#61246
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * 2023/10/13   Hitachi         D.Yoshida       Update          QC#61077
 * 2023/12/06   CITS            K.Ikeda         Update          QC#61281
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 * </pre>
 */

public class NWZC150001 extends S21ApiCommonBase {

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    /** Online Batch Type */
//    private ONBATCH_TYPE onBatchType = null;
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    /** Global Company Code : set from pMsg */
    private String glblCmpyCd = null;

    /** Sales Date : set from pMsg */
    private String slsDt = null;

    /** Message ID Manager */
    private S21ApiMessageIdMgr msgIdMgr = null;

    /** TimeStamp 2016/09/05 S21_NA#6100 Add */
    private String curTime = null;
    
    /** Online Batch Type */
    private ONBATCH_TYPE onBatchType = null;

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    /** DS_CPO_CONFIG Array for Cache */
//    private List<DS_CPO_CONFIGTMsg> dsCpoConfigList = null;
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    /** Service Machine Master Data Chache 2016/09/28 S21_NA#14264 Add */
//    private SvcMachCache svcMachCache = new SvcMachCache();
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2017/04/11 S21_NA#Review structure Lv.2 Add Start
    private NWZC150001CpouBean cpouBean = null;

    private NWZC150001CpouLocalCache localCache = null;
    // 2017/04/11 S21_NA#Review structure Lv.2 Add End

    // debug
//    private static final S21Logger logger = S21LoggerFactory.getLogger(NWZC150001.class);
    // debug

    // S21_NA#3375 Add Start
    /** Convert Request Mode */
    private final Map<String, String> cnvDlvyInfoMode = new HashMap<String, String>() {
        {
            put(RQST_TP_DELY_INFO_NEW, NWZC183001Constant.MODE_NEW);
            put(RQST_TP_DELY_INFO_MODIFY, NWZC183001Constant.MODE_MOD);
            put(RQST_TP_DELY_INFO_DELETE, NWZC183001Constant.MODE_DEL);
        }
    };
    // private final Map<String, String> cnvIstlInfoMode = new HashMap<String, String>() {
    //     {
    //         put(RQST_TP_INSTL_INFO_NEW, NWZC183001Constant.MODE_NEW);
    //         put(RQST_TP_INSTL_INFO_MODIFY, NWZC183001Constant.MODE_MOD);
    //         put(RQST_TP_INSTL_INFO_DELETE, NWZC183001Constant.MODE_DEL);
    //     }
    // };
    private final Map<String, String> cnvSiteSrvInfoMode = new HashMap<String, String>() {
        {
            put(RQST_TP_SITE_SRVY_NEW, NWZC183001Constant.MODE_NEW);
            put(RQST_TP_SITE_SRVY_MODIFY, NWZC183001Constant.MODE_MOD);
            put(RQST_TP_SITE_SRVY_DELETE, NWZC183001Constant.MODE_DEL);
        }
    };
    private final Map<String, String> cnvCtacMode = new HashMap<String, String>() {
        {
            put(RQST_TP_CTAC_PSN_NEW, NWZC183001Constant.MODE_NEW);
            put(RQST_TP_CTAC_PSN_MODIFY, NWZC183001Constant.MODE_MOD);
            put(RQST_TP_CTAC_PSN_DELETE, NWZC183001Constant.MODE_DEL);
        }
    };
    // S21_NA#3375 Add End

    // 2016/07/12 S21_NA#9426-4 Add Start
    /** voided flag */
    private boolean isAlreadyVoidCrCardTrx = false;

    /** Authorized and Completed Flag */
    private boolean isAlreadyAuthComp = false;
    // 2016/07/12 S21_NA#9426-4 Add End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    // For Performance QC#8166 Add Start
//    private Map<Map<String, String>, Boolean> ordCatgCacheMap = null;
//    // For Performance QC#8166 Add End
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    /** MDSE List */
//    List<MDSETMsg> dsMdseInfoList = null; // 2016/07/22 S21_NA#11970 Add
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    // 2016/11/01 S21_NA#10965 Add Start
//    private boolean isRetailEquipOrder = false;
//    // 2016/11/01 S21_NA#10965 Add End
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2017/04/26 S21_NA#Review structure Lv.2 Add Start
    private String origXxModeCd = null;
    // 2017/04/26 S21_NA#Review structure Lv.2 Add End

    // 2017/05/15 S21_NA#Review structure Lv.2 Add Start
    private NWZC150001DsCpoCommonBean commonBean = null;
    // 2017/05/15 S21_NA#Review structure Lv.2 Add End

    // 2017/06/13 S21_NA#18869-2 Add Start
    /** Instance of NWZC150001CpouUpdateOrderData */
    private NWZC150001CpouUpdateOrderData updOrdDataInstance = null;
    // 2017/06/13 S21_NA#18869-2 Add End

    /** Constructor */
    public NWZC150001() {
        super();
        // 2017/05/15 S21_NA#Review structure Lv.2 Mod Start
//        ordCatgCacheMap = new HashMap<Map<String, String>, Boolean>();
        commonBean = new NWZC150001DsCpoCommonBean(this);
        // 2017/05/15 S21_NA#Review structure Lv.2 Mod End
    }

    /**
     * <pre>
     * DS CPO Update API.
     * @param inPMsg        NWZC150001PMsg
     * @param resPMsg2List  List<NWZC150002PMsg>
     * @param resPMsg3List  List<NWZC150003PMsg>
     * @param prmOnBatchType ONBATCH_TYPE
     * </pre>
     */
    public void execute(final NWZC150001PMsg inPMsg //
            , final List<NWZC150002PMsg> resPMsg2List //
            , final List<NWZC150003PMsg> resPMsg3List //
            , final ONBATCH_TYPE prmOnBatchType) {

        // debug
//        logger.debug("-- DS CPO Update API --");
//        logger.debug("--  Input Parameter:");
//        logger.debug(inPMsg);
//        for (int i = 0; i < resPMsg2List.size(); i++) {
//            NWZC150002PMsg p2Msg = resPMsg2List.get(i);
//            logger.debug(" --- NWZC150002PMsg:" + i + " ---");
//            logger.debug(p2Msg);
//        }
//        for (int i = 0; i < resPMsg3List.size(); i++) {
//            NWZC150003PMsg p3Msg = resPMsg3List.get(i);
//            logger.debug(" --- NWZC150003PMsg:" + i + " ---");
//            logger.debug(p3Msg);
//        }
        // debug

        this.localCache = new NWZC150001CpouLocalCache();
        
        this.onBatchType = prmOnBatchType;

        // 2016/07/04 S21_NA#7821 Add Start
        if (S21StringUtil.isEquals(MODE_DELETE, inPMsg.xxModeCd.getValue())) {
            new NWZC150001ForDelete().execute(inPMsg, resPMsg2List, resPMsg3List, prmOnBatchType);
            return;
        }
        // 2016/07/04 S21_NA#7821 Add End

        // 2017/04/25 S21_NA#Review structure Lv.2 Add Start
        List<String> modeCopy = new ArrayList<String>(2);
        modeCopy.add(MODE_COPY_HEADER);
        modeCopy.add(MODE_COPY_ALL);
        modeCopy.add(MODE_COPY_ALL_RETURN); // 2017/09/19 S21_NA#18859 Add
        if (modeCopy.contains(inPMsg.xxModeCd.getValue())) {
            // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//            new NWZC150001ForOrderCopy().execute(inPMsg, resPMsg2List, resPMsg3List, prmOnBatchType);
            new NWZC150001ForOrderCopy().execute(inPMsg, resPMsg2List, resPMsg3List, prmOnBatchType, localCache);
            // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
            return;
        }
        // 2017/04/25 S21_NA#Review structure Lv.2 Add End

        // 2017/05/08 S21_NA#Review structure Lv.2 Add Start
        if (S21StringUtil.isEquals(MODE_CHANGE_MODIFICATION, inPMsg.xxModeCd.getValue())) {
            new NWZC150001ForChgOrdModification().execute(inPMsg, resPMsg2List, resPMsg3List, prmOnBatchType);
            return;
        }

        List<String> origDtlModeCdList = null;
        if (S21StringUtil.isEquals(MODE_VALID, inPMsg.xxModeCd.getValue())) {
            origXxModeCd = inPMsg.xxModeCd.getValue();
            inPMsg.xxModeCd.setValue(MODE_SUBMIT);

            origDtlModeCdList = new ArrayList<String>(inPMsg.A.getValidCount());
            for (int i = 0; i < inPMsg.A.getValidCount(); i++) {
                origDtlModeCdList.add(inPMsg.A.no(i).xxRqstTpCd_A1.getValue());
                if (!S21StringUtil.isEquals(RQST_TP_DTL_CANCEL, inPMsg.A.no(i).xxRqstTpCd_A1.getValue())) {
                    inPMsg.A.no(i).xxRqstTpCd_A1.setValue(RQST_TP_DTL_NEW);
                }
            }
        }
        // 2017/05/08 S21_NA#Review structure Lv.2 Add End

        // 2016/07/12 S21_NA#9426-4 Add Start
        this.isAlreadyVoidCrCardTrx = false;
        this.isAlreadyAuthComp = false;
        // 2016/07/12 S21_NA#9426-4 Add End
        NWZC150001PMsg pMsg = new NWZC150001PMsg();
        try {
            EZDMsg.copy(inPMsg, null, pMsg, null);

            msgIdMgr = new S21ApiMessageIdMgr();
            commonBean.setOnBatchType(prmOnBatchType);
            this.glblCmpyCd = pMsg.glblCmpyCd.getValue();
            this.slsDt = pMsg.slsDt.getValue();
            this.curTime = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN); // 2016/09/05 S21_NA#6100 Add

            doProcess(pMsg, resPMsg2List, resPMsg3List);

            // S21_NA#11630 ADD START
            if (!hasError(pMsg, resPMsg2List, resPMsg3List) && isProcCancelCreditRebill(pMsg)) {
                extraCancelCreditRebillPairOrder(pMsg);
            }
            // S21_NA#11630 ADD END

        } finally {
            inPMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount());
            EZDMsg.copy(pMsg, null, inPMsg, null);
            super.updateMessage(inPMsg, msgIdMgr);

            if (S21StringUtil.isEquals(MODE_VALID, origXxModeCd)) {
                inPMsg.xxModeCd.setValue(origXxModeCd);

                if (origDtlModeCdList != null) {
                    for (int i = 0; i < inPMsg.A.getValidCount(); i++) {
                        ZYPEZDItemValueSetter.setValue(inPMsg.A.no(i).xxRqstTpCd_A1, origDtlModeCdList.get(i));
                    }
                }
            }
            // debug
//            logger.debug("-- DS CPO Update API --");
//            logger.debug("--  Output Parameter:");
//            logger.debug(inPMsg);
//            for (int i = 0; i < resPMsg2List.size(); i++) {
//                NWZC150002PMsg p2Msg = resPMsg2List.get(i);
//                logger.debug(" --- NWZC150002PMsg:" + i + " ---");
//                logger.debug(p2Msg);
//            }
//            for (int i = 0; i < resPMsg3List.size(); i++) {
//                NWZC150003PMsg p3Msg = resPMsg3List.get(i);
//                logger.debug(" --- NWZC150003PMsg:" + i + " ---");
//                logger.debug(p3Msg);
//            }
//            logger.debug("--  End --");
            // debug
        }

    }

    private void doProcess(NWZC150001PMsg pMsg //
            , List<NWZC150002PMsg> resPMsg2List //
            , List<NWZC150003PMsg> resPMsg3List) {

        // Move S21_NA#14107
//        execDsCheck(pMsg, resPMsg2List, resPMsg3List);
//        if (isErrorExists(pMsg, resPMsg2List, resPMsg3List)) {
//            return;
//        }

        // 2019/09/27 QC#53593 ADD START
        String slsDt = ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue());
        // 2019/09/27 QC#53593 ADD END

        CPOTMsg cpoTMsg = new CPOTMsg();
        if (ZYPCommonFunc.hasValue(pMsg.cpoOrdNum) && ZYPCommonFunc.hasValue(glblCmpyCd)) { // MOD S21_NA#14107
            ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, pMsg.cpoOrdNum);
            cpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKey(cpoTMsg);
            if (!existsCpo(cpoTMsg)) {
                this.msgIdMgr.addXxMsgId(NWZM0073E, pMsg);
            }
        }

        // START 2023/10/13 [QC#61077 ,ADD]
        initOrdForChngOrdRsnCtg(pMsg, resPMsg2List, cpoTMsg);
        // END   2023/10/13 [QC#61077 ,ADD]

        // Move S21_NA#14107
        // 2019/09/27 QC#53593 MOD START
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//        NWZC150001ForDsCheck.execDsCheck(pMsg, resPMsg2List, resPMsg3List, cpoTMsg);
//        NWZC150001ForDsCheck.execDsCheck(pMsg, resPMsg2List, resPMsg3List, cpoTMsg, this.localCache, this.msgIdMgr, this.commonBean);
        NWZC150001ForDsCheck.execDsCheck(pMsg, resPMsg2List, resPMsg3List, cpoTMsg, this.localCache, this.msgIdMgr, this.commonBean, slsDt);
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
        // 2019/09/27 QC#53593 MOD END
        if (isErrorExists(pMsg, resPMsg2List, resPMsg3List)) {
            return;
        }

        // 2018/07/23 S21_NA#24745 Add Start
        NWZC150001ForAJEValid.getInstance().execute(pMsg, resPMsg2List, resPMsg3List, this.onBatchType);
        if (isErrorExists(pMsg, resPMsg2List, resPMsg3List)) {
            return;
        }
        // 2018/07/23 S21_NA#24745 Add End

        // 2017/04/11 S21_NA#Review structure Lv.2 Add Start
        this.cpouBean = new NWZC150001CpouBean(pMsg);
        // 2017/04/26 S21_NA#Review structure Lv.2 Add Start
        if (S21StringUtil.isEquals(MODE_VALID, this.origXxModeCd)) {
            this.cpouBean.setOnlyValidationFlg(ZYPConstant.FLG_ON_Y);
        }
        // 2017/04/26 S21_NA#Review structure Lv.2 Add End
        setDefaultBeanData(pMsg, cpouBean, cpoTMsg);
        if (!S21StringUtil.isEquals(MODE_VALID, this.origXxModeCd)) {
            NWZC150001Common.numberingCpoOrdNum(cpouBean, pMsg);
        }

        // Validation same as Cpo Update API
        NWZC150001CpouDoValidation cpouDoValidation = new NWZC150001CpouDoValidation(this.localCache);

        // 2019/09/27 QC#53593 MOD START
//        cpouDoValidation.executeValidation(pMsg, cpouBean, resPMsg2List, msgIdMgr, commonBean.getOnBatchType());
        cpouDoValidation.executeValidation(pMsg, cpouBean, resPMsg2List, msgIdMgr, commonBean.getOnBatchType(), slsDt);
        // 2019/09/27 QC#53593 MOD END
        if (isErrorExists(pMsg, resPMsg2List, resPMsg3List)) {
            return;
        }
        // 2017/04/11 S21_NA#Review structure Lv.2 Add End
        // 2017/04/26 S21_NA#Review structure Lv.2 Add Start
        if (S21StringUtil.isEquals(MODE_VALID, this.origXxModeCd)) {
            if (pMsg.rtnDtl.getValidCount() > 0) {
                // Call Return Update API Validation Mode
                boolean isPureReturn = !(pMsg.A.getValidCount() > 0);
                // 2017/06/13 S21_NA#18869-2 Mod Start
//                NWZC150001CpouUpdateOrderData.getInstance().callRtrnUpdateAPI(pMsg, //
//                        cpouBean, //
//                        resPMsg3List, //
//                        cpoTMsg, //
//                        isPureReturn, //
//                        false, //
//                        NWZC150001Constant.CALL_SEQ_VALID);
                getUpdOrdDataInstance().callRtrnUpdateAPI(pMsg, //
                      cpouBean, //
                      resPMsg3List, //
                      cpoTMsg, //
                      isPureReturn, //
                      false, //
                      NWZC150001Constant.CALL_SEQ_VALID);
                // 2017/06/13 S21_NA#18869-2 Mod End
            }
            return;
        }

        // 2017/04/26 S21_NA#Review structure Lv.2 Add End

        if (MODE_SAVE.equals(pMsg.xxModeCd.getValue())) {
            execSaveMode(pMsg, resPMsg2List, resPMsg3List, cpoTMsg); // S21_NA#1957
            // 2016/07/21 S21_NA#9228 Add Start
            if (isPayMethodCreditCardAndAmountMinusError(pMsg, true)) {
                return;
            }
            // 2016/07/21 S21_NA#9228 Add End

            // 2023/12/06 QC#61281 K.Ikeda START
            if(isPayMethodNotCreditCardAndAmountNotZeroError(pMsg, true)) {
              return;
            }
            // 2023/12/06 QC#61281 K.Ikeda END
            // if (!this.msgIdMgr.isXxMsgId()) { S21_NA#2007 Mod
            if (!isErrorExists(pMsg, resPMsg2List, resPMsg3List)) { // S21_NA#2007 Mod
                callOrdWorkflowAPI(pMsg);
            }
        } else if (MODE_SUBMIT.equals(pMsg.xxModeCd.getValue())) {
            execSubmitMode(pMsg, resPMsg2List, resPMsg3List, cpoTMsg);
        } else if (MODE_CANCEL.equals(pMsg.xxModeCd.getValue())) {
            execCancelMode(pMsg, resPMsg2List, resPMsg3List, cpoTMsg, this.cpouBean); // QC#22369
            // START 2023/05/11 T.Doi [CSA-QC#61246, ADD]
            if (!isErrorExists(pMsg, resPMsg2List, resPMsg3List)) {
                execCancelPrchReq(pMsg, resPMsg2List);
            }
            // END 2023/05/11 T.Doi [CSA-QC#61246, ADD]
        }

    }

    private void execCancelMode(//
            NWZC150001PMsg pMsg //
            , List<NWZC150002PMsg> resPMsg2List //
            , List<NWZC150003PMsg> resPMsg3List //
            , CPOTMsg cpoTMsg //
            , NWZC150001CpouBean uBean) { // QC#22369

        // 2017/04/13 S21_NA#Review structure Lv.2 Mod Start
        // 2016/06/09 S21_NA#9426 Add Start
        if (voidAuthCompletedCrCardTrx(pMsg)) {
            return;
        }
        // 2016/06/09 S21_NA#9426 Add End
        updateForCancel(pMsg, resPMsg2List, resPMsg3List, cpoTMsg); // S21_NA#13796 MOD
        if (ORD_HDR_STS.VALIDATED.equals(cpoTMsg.ordHdrStsCd.getValue())) { // S21_NA#1331
            if (callCpoUpdateForCancelAPI(pMsg, resPMsg2List, resPMsg3List, cpoTMsg, uBean)) { // QC#22369
                return;
            }

            // 2016/06/02 S21_NA#9273 Modify Start
            // IB allocation for conversion order only.
            if (!registAlloc(pMsg, resPMsg2List)) {
                return;
            }
            // 2016/06/02 S21_NA#9273 Modify End
        }

//        boolean isPureReturn = isPureReturn(pMsg, true); // 2016/01/14 S21_NA#2996 Add 2nd parameter
//        if (isPureReturn) {
//            if (callRtrnUpdateAPI(pMsg, resPMsg3List, cpoTMsg, isPureReturn, false, NWZC150001Constant.CALL_SEQ_CANCEL)) { // S21_NA#1952, S21_NA#2996 Add CALL_SEQ_CANCEL
//                return;
//            }
//        } else {
//            // 2016/06/09 S21_NA#9426 Add Start
//            if (voidAuthCompletedCrCardTrx(pMsg)) {
//                return;
//            }
//            // 2016/06/09 S21_NA#9426 Add End
//            updateForCancel(pMsg, resPMsg2List, resPMsg3List, cpoTMsg); // S21_NA#13796 MOD
//            if (ORD_HDR_STS.VALIDATED.equals(cpoTMsg.ordHdrStsCd.getValue())) { // S21_NA#1331
//                if (callCpoUpdateForCancelAPI(pMsg, resPMsg2List, resPMsg3List, cpoTMsg)) {
//                    return;
//                }
//
//                // 2016/06/02 S21_NA#9273 Modify Start
//                // IB allocation for conversion order only.
//                if (!registAlloc(pMsg, resPMsg2List)) {
//                    return;
//                }
//                // 2016/06/02 S21_NA#9273 Modify End
//
//                if (callRtrnUpdateAPI(pMsg, resPMsg3List, cpoTMsg, isPureReturn, false, NWZC150001Constant.CALL_SEQ_CANCEL)) { // S21_NA#1952 S21_NA#2996 Add CALL_SEQ_CANCEL
//                    return;
//                }
//            }
//        }
        // 2017/04/13 S21_NA#Review structure Lv.2 Mod End
        // 2019/12/14 QC#53588 Mod Start
        if (callOrdProcWorkflowCancelAPI(pMsg, resPMsg2List, cpoTMsg, AUTO)) {
        //if (callOrdProcWorkflowCancelAPI(pMsg, resPMsg2List, cpoTMsg, FORCE_CANCEL)) {
            return;
        }
        // 2019/12/14 QC#53588 Mod End
    }

    private boolean callOrdProcWorkflowCancelAPI(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, CPOTMsg cpoTMsg, String xxModeCd) {
        NWZC177001PMsg WorkFlowControlPMsg = new NWZC177001PMsg();

        ZYPEZDItemValueSetter.setValue(WorkFlowControlPMsg.cpoOrdNum, pMsg.cpoOrdNum);

        // 2018/08/01 S21_NA#27087 Add Start
        ZYPEZDItemValueSetter.setValue(WorkFlowControlPMsg.xxModeCd, xxModeCd);
        // 2018/08/01 S21_NA#27087 Add End

        NWZC177001 WorkFlowControlAPI = new NWZC177001();
        WorkFlowControlAPI.execute(WorkFlowControlPMsg, commonBean.getOnBatchType());

        if (WorkFlowControlPMsg.xxMsgIdList.getValidCount() > 0) {
            for (int j = 0; j < WorkFlowControlPMsg.xxMsgIdList.getValidCount(); j++) {
                String xxMsgId = WorkFlowControlPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                this.msgIdMgr.addXxMsgId(xxMsgId, pMsg);
            }
            return true;
        }
        return false;
    }

// QC#22369
//    private boolean callCpoUpdateForCancelAPI(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List, CPOTMsg cpoTMsg) {
////        if (pMsg.A.getValidCount() == 0) { 2017/06/16 S21_NA#19288 Modd Condition
//        if (pMsg.A.getValidCount() == 0 //
//                && pMsg.rtnDtl.getValidCount() == 0) {
//            return false;
//        }
//
////        NWZC150001CpouBean cpouBean = new NWZC150001CpouBean(pMsg);
//
//        cpouBean.setRqstTpCd(NWZC150001CpouConstant.CPO_CANCEL);
//        cpouBean.setCpoOrdTpCd(NWXC150001DsCheck.getCpoOrdTpCdFromDsOrdTp(glblCmpyCd, pMsg.dsOrdTpCd.getValue()));
//        cpouBean.setInvRcpntCustCd(cpouBean.getBillToCustCd());
//        cpouBean.setSlsRepTocCd(pMsg.slsRepCd.getValue());
//
//        NWZC150001CpouHldBean hldBean = new NWZC150001CpouHldBean();
//        hldBean.setXxRqstTpCd(NWZC150001CpouConstant.REQUEST_TYPE_NEW);
//        cpouBean.addHldBeanList(hldBean);
//
//        for (NWZC150001CpouDetailBean cpouDtlBean : cpouBean.getDtlBeanList()) {
//            if (!ZYPCommonFunc.hasValue(cpouDtlBean.getSlsRepOrSlsTeamTocCd())) {
//                cpouDtlBean.setSlsRepOrSlsTeamTocCd(pMsg.slsRepCd.getValue());
//            }
//            cpouDtlBean.setPmtTermCashDiscCd(cpouBean.getAddPmtTermCashDiscCd());
//            cpouDtlBean.setCashDiscTermCd(cpouBean.getAddCashDiscTermCd());
//            cpouDtlBean.setFrtChrgToCd(cpouBean.getAddFrtChrgToCd());
//            cpouDtlBean.setFrtChrgMethCd(cpouBean.getAddFrtChrgMethCd());
//            cpouDtlBean.setShpgSvcLvlCd(cpouBean.getAddShpgSvcLvlCd());
//        }
//
//        // new NWZC150001CpouExecCpoUpd().execute(pMsg, cpouBean, pMsg2List, onBatchType);
//        // 2017/06/13 S21_NA#18869-2 Mod Start
////        NWZC150001CpouUpdateOrderData.getInstance().updateOrderData(pMsg, this.cpouBean, pMsg2List, pMsg3List, msgIdMgr, commonBean.getOnBatchType());
//        getUpdOrdDataInstance().updateOrderData(pMsg, this.cpouBean, pMsg2List, pMsg3List, msgIdMgr, commonBean.getOnBatchType());
//        // 2017/06/13 S21_NA#18869-2 Mod End
//
//        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
//
//            final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
//            if (xxMsgId.endsWith("E")) {
//                return true;
//            }
//        }
//
//        for (NWZC150002PMsg resPMsg : pMsg2List) {
//            
//            for (int i = 0; i < resPMsg.xxMsgIdList.getValidCount(); i++) {
//
//                final String xxMsgId = resPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
//                if (xxMsgId.endsWith("E")) {
//                    return true;
//                }
//            }
//        }
//        
//        return false;
//    }

    // QC#22369
    private boolean callCpoUpdateForCancelAPI(//
            NWZC150001PMsg pMsg //
            , List<NWZC150002PMsg> pMsg2List //
            , List<NWZC150003PMsg> pMsg3List //
            , CPOTMsg cpoTMsg //
            , NWZC150001CpouBean uBean) {
      if (pMsg.A.getValidCount() == 0 //
              && pMsg.rtnDtl.getValidCount() == 0) {
          return false;
      }

      uBean.setRqstTpCd(NWZC150001CpouConstant.CPO_CANCEL);
      uBean.setCpoOrdTpCd(NWXC150001DsCheck.getCpoOrdTpCdFromDsOrdTp(glblCmpyCd, pMsg.dsOrdTpCd.getValue()));
      uBean.setInvRcpntCustCd(uBean.getBillToCustCd());
      uBean.setSlsRepTocCd(pMsg.slsRepCd.getValue());

      NWZC150001CpouHldBean hldBean = new NWZC150001CpouHldBean();
      hldBean.setXxRqstTpCd(NWZC150001CpouConstant.REQUEST_TYPE_NEW);
      uBean.addHldBeanList(hldBean);

      for (NWZC150001CpouDetailBean cpouDtlBean : uBean.getDtlBeanList()) {
          if (!ZYPCommonFunc.hasValue(cpouDtlBean.getSlsRepOrSlsTeamTocCd())) {
              cpouDtlBean.setSlsRepOrSlsTeamTocCd(pMsg.slsRepCd.getValue());
          }
          cpouDtlBean.setPmtTermCashDiscCd(uBean.getAddPmtTermCashDiscCd());
          cpouDtlBean.setCashDiscTermCd(uBean.getAddCashDiscTermCd());
          cpouDtlBean.setFrtChrgToCd(uBean.getAddFrtChrgToCd());
          cpouDtlBean.setFrtChrgMethCd(uBean.getAddFrtChrgMethCd());
          cpouDtlBean.setShpgSvcLvlCd(uBean.getAddShpgSvcLvlCd());
      }

      getUpdOrdDataInstance().updateOrderData(pMsg, uBean, pMsg2List, pMsg3List, msgIdMgr, commonBean.getOnBatchType());

      for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

          final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
          if (xxMsgId.endsWith("E")) {
              return true;
          }
      }

      for (NWZC150002PMsg resPMsg : pMsg2List) {
          
          for (int i = 0; i < resPMsg.xxMsgIdList.getValidCount(); i++) {

              final String xxMsgId = resPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
              if (xxMsgId.endsWith("E")) {
                  return true;
              }
          }
      }
      
      return false;
  }

    private void updateForCancel(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List, CPOTMsg cpoTMsg) { // S21_NA#13796 MOD
        if (ORD_HDR_STS.SAVED.equals(cpoTMsg.ordHdrStsCd.getValue()) //
                || ORD_HDR_STS.VALIDATED.equals(cpoTMsg.ordHdrStsCd.getValue())) { // QC#22369
            updateLineCancel(pMsg, pMsg2List);
            updateLineCancelRtrn(pMsg, pMsg3List); // S21_NA#13796 ADD 
        }
        if (NWXC150001DsCheck.checkAllLineCancelled(glblCmpyCd, pMsg.cpoOrdNum.getValue())
                && NWXC150001DsCheck.checkAllRmaLineCancelled(glblCmpyCd, pMsg.cpoOrdNum.getValue())) { // S21_NA#13796 MOD
            updateHeaderCancel(pMsg, cpoTMsg);
        }
    }

    private void updateHeaderCancel(NWZC150001PMsg pMsg, CPOTMsg cpoTMsg) {
        CPOTMsg tMsg = (CPOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cpoTMsg);
        if (tMsg == null) {
            this.msgIdMgr.addXxMsgId(NWZM0073E, pMsg);
            return;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.ordHdrStsCd, ORD_HDR_STS.CANCELLED);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.openFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoCancFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoCancDt, slsDt);

        // 2017/07/10 S21_NA#19828 Mod Start
//        S21ApiTBLAccessor.updateSelectionField(tMsg, new String[] {"ordHdrStsCd", "cpoHldFlg", "openFlg", "cpoCancFlg", "cpoCancDt" });
        ZYPEZDItemValueSetter.setValue(tMsg.ordHdrDplyStsCd, ORD_HDR_DPLY_STS.CANCELLED);
        S21ApiTBLAccessor.updateSelectionField(tMsg, new String[] {"ordHdrStsCd", "cpoHldFlg", "openFlg", "cpoCancFlg", "cpoCancDt", "ordHdrDplyStsCd"});
        // 2017/07/10 S21_NA#19828 Mod End
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            this.msgIdMgr.addXxMsgId(NWZM1368E, pMsg);
            return;
        }
    }

    private void updateLineCancel(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List) {
        // 2018/03/14 S21_NA#24458 Add Start
        NSZC115001PMsg shellApiPMsg = new NSZC115001PMsg();
        ZYPEZDItemValueSetter.setValue(shellApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        shellApiPMsg.xxProcMd.setValue(NSZC115001Constant.PROC_MODE_PHYS_DEL);
        // 2018/04/17 S21_NA#24458-2 add start
        ZYPEZDItemValueSetter.setValue(shellApiPMsg.slsDt, pMsg.slsDt);
        // 2018/04/17 S21_NA#24458-2 add end
        int shellApiValCnt = 0;
        // 2018/03/14 S21_NA#24458 Add End
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            NWZC150002PMsg pMsg2 = new NWZC150002PMsg();

            if (RQST_TP_DTL_CANCEL.equals(aPMsg.xxRqstTpCd_A1.getValue())) {
                CPO_DTLTMsg tMsg = new CPO_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, pMsg.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);

                tMsg = (CPO_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
                if (tMsg == null) {
                    NWZC150001Common.setMsgId2(pMsg2, NWZM0074E);
                    ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, aPMsg.dsOrdPosnNum_A1);
                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                    return;
                }
                ZYPEZDItemValueSetter.setValue(tMsg.ordLineStsCd, ORD_LINE_STS.CANCELLED);
                // 2017/07/10 S21_NA#19828 Mod Start
//                S21ApiTBLAccessor.updateSelectionField(tMsg, new String[] {"ordLineStsCd" });
                ZYPEZDItemValueSetter.setValue(tMsg.ordLineDplyStsCd, ORD_LINE_DPLY_STS.CANCELLED);
                S21ApiTBLAccessor.updateSelectionField(tMsg, new String[] {"ordLineStsCd", "ordLineDplyStsCd"});
                // 2017/07/10 S21_NA#19828 Mod End
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    NWZC150001Common.setMsgId2(pMsg2, NWZM0081E);
                    ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, aPMsg.dsOrdPosnNum_A1);
                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                    return;
                }
                // 2018/03/14 S21_NA#24458 Add Start
                shellApiPMsg.svcConfigRefList.no(shellApiValCnt).cpoOrdNum.setValue(pMsg.cpoOrdNum.getValue());
                shellApiPMsg.svcConfigRefList.no(shellApiValCnt).cpoDtlLineNum.setValue(aPMsg.cpoDtlLineNum_A1.getValue());
                shellApiPMsg.svcConfigRefList.no(shellApiValCnt).cpoDtlLineSubNum.setValue(aPMsg.cpoDtlLineSubNum_A1.getValue());
                shellApiValCnt++;
                // 2018/03/14 S21_NA#24458 Add End
            }
        }
        // 2018/03/14 S21_NA#24458 Add Start
        if (shellApiValCnt > 0) {
            shellApiPMsg.svcConfigRefList.setValidCount(shellApiValCnt);

            new NSZC115001().execute(shellApiPMsg, onBatchType);
        }
        // 2018/03/14 S21_NA#24458 Add End
    }

    // S21_NA#13796 ADD START
    private void updateLineCancelRtrn(NWZC150001PMsg pMsg, List<NWZC150003PMsg> pMsg3List) {
        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rtrnPMsg = pMsg.rtnDtl.no(i);
            NWZC150003PMsg pMsg3 = new NWZC150003PMsg();

            if (RQST_TP_DTL_CANCEL.equals(rtrnPMsg.xxRqstTpCd_B1.getValue())) {
                DS_CPO_RTRN_DTLTMsg tMsg = new DS_CPO_RTRN_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, pMsg.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(tMsg.dsCpoRtrnLineNum, rtrnPMsg.cpoDtlLineNum_B1);
                ZYPEZDItemValueSetter.setValue(tMsg.dsCpoRtrnLineSubNum, rtrnPMsg.cpoDtlLineSubNum_B1);

                tMsg = (DS_CPO_RTRN_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
                if (tMsg == null) {
                    NWZC150001Common.setMsgId3(pMsg3, NWZM2012E);
                    ZYPEZDItemValueSetter.setValue(pMsg3.dsOrdPosnNum, rtrnPMsg.dsOrdPosnNum_B1);
                    ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtrnPMsg.cpoDtlLineNum_B1);
                    ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtrnPMsg.cpoDtlLineSubNum_B1);
                    return;
                }
                ZYPEZDItemValueSetter.setValue(tMsg.rtrnLineStsCd, RTRN_LINE_STS.CANCELLED);
                ZYPEZDItemValueSetter.setValue(tMsg.openFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlCancFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlCancDt, pMsg.slsDt.getValue());
                // 2018/08/02 S21_NA#25404 Mod Start
//                ZYPEZDItemValueSetter.setValue(tMsg.cancQty, rtrnPMsg.ordQty_B1);
                ZYPEZDItemValueSetter.setValue(tMsg.cancQty, tMsg.cancQty.getValue().add(rtrnPMsg.ordQty_B1.getValue()));
                // 2018/08/02 S21_NA#25404 Mod End
                // 2017/07/10 S21_NA#19828 Mod Start
//                S21ApiTBLAccessor.updateSelectionField(tMsg, new String[] {"rtrnLineStsCd" ,"openFlg", "cpoDtlCancFlg", "cpoDtlCancDt", "cancQty"});
                ZYPEZDItemValueSetter.setValue(tMsg.rtrnLineDplyStsCd, RTRN_LINE_DPLY_STS.CANCELLED);
                S21ApiTBLAccessor.updateSelectionField(tMsg, new String[] {"rtrnLineStsCd" ,"openFlg", "cpoDtlCancFlg", "cpoDtlCancDt", "cancQty", "rtrnLineDplyStsCd"});
                // 2017/07/10 S21_NA#19828 Mod End
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    NWZC150001Common.setMsgId3(pMsg3, NWZM2011E);
                    ZYPEZDItemValueSetter.setValue(pMsg3.dsOrdPosnNum, rtrnPMsg.dsOrdPosnNum_B1);
                    ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtrnPMsg.cpoDtlLineNum_B1);
                    ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtrnPMsg.cpoDtlLineSubNum_B1);
                    return;
                }
            }
        }
    }
    // S21_NA#13796 ADD END

    private void execSubmitMode(//
            NWZC150001PMsg pMsg //
            , List<NWZC150002PMsg> resPMsg2List //
            , List<NWZC150003PMsg> resPMsg3List //
            , CPOTMsg cpoTMsg) {
        // 2016/07/12 S21_NA#9426-4 Add Start
        if (!S21StringUtil.isEquals(DS_PMT_METH.CREDIT_CARD, pMsg.dsPmtMethCd.getValue())) {
            voidAuthCompletedCrCardTrx(pMsg);
        } else { // 2016/07/28 S21_NA#11982-2 Add Start
            this.isAlreadyAuthComp = true;
        } // 2016/07/28 S21_NA#11982-2 Add End
        // 2016/07/12 S21_NA#9426-4 Add End

        writePerformanceProfilingLogStart(getClass(), "execSubmitMode");

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxValUpdFlg.getValue())) {
            execSaveMode(pMsg, resPMsg2List, resPMsg3List, cpoTMsg); // S21_NA#1957
            // 2015/12/24 S21_NA#2411 Add Start
            if (isErrorExists(pMsg, resPMsg2List, resPMsg3List)) {
                return;
            }
            // 2015/12/24 S21_NA#2411 Add End
            // S21_NA#1401
            if (ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
                ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, pMsg.cpoOrdNum);
                cpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKey(cpoTMsg);
                if (!existsCpo(cpoTMsg)) {
                    // 2019/08/02 S21_NA#52127 Mod Start
                    //this.msgIdMgr.addXxMsgId(NWZM0073E, pMsg);
                    return;
                    // 2019/08/02 S21_NA#52127 Mod End
                }
            } else {
                return;
            }
        }
        // 2016/04/28 S21_NA#7631 Add Start
        boolean needDiCheck = NWXC150001DsCheck.isNecessaryDICheck(glblCmpyCd, pMsg.dsOrdTpCd.getValue());
        // 2016/04/28 S21_NA#7631 Add End
        // 2017/10/26 S21_NA#21820 Add Start
        boolean isCheckDiChecked = NWXC150001DsCheck.checkDiChecked(glblCmpyCd, pMsg.cpoOrdNum.getValue(), cpoTMsg.cpoUpdVrsnNum.getValue());
        // After Booked Order, if no val update and DI Check Hold Flag is on, this order should be DI Checked onace again.
        boolean isNeedForceDiCheck = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cpoTMsg.diChkHldFlg.getValue()) //
                && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cpoTMsg.ordBookFlg.getValue()) //
                && !S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, pMsg.xxValUpdFlg.getValue());
        // 2017/10/26 S21_NA#21820 Add End
        // 2017/10/16 S21_NA#21267 Add Start
        if (isCreditOrder(pMsg)) {
            needDiCheck = false;
        }
        // 2017/10/16 S21_NA#21267 Add End
//        if (NWXC150001DsCheck.checkDiChecked(glblCmpyCd, pMsg.cpoOrdNum.getValue(), dsCpoTMsg.cpoUpdVrsnNum.getValue())) { 2016/04/28 S21_NA#7631 Mod Condition
//        if (needDiCheck && NWXC150001DsCheck.checkDiChecked(glblCmpyCd, pMsg.cpoOrdNum.getValue(), cpoTMsg.cpoUpdVrsnNum.getValue())) {
        if (needDiCheck && (isCheckDiChecked || isNeedForceDiCheck)) { // 2017/10/26 S21_NA#21820 Change Condition
            final NWZC155001PMsg diCheckApiMsg = new NWZC155001PMsg();
            writePerformanceProfilingLogStart(getClass(), "callDiCheckAPI");

            if (callDiCheckAPI(pMsg, diCheckApiMsg)) {
                return;
            }
            writePerformanceProfilingLogEnd(getClass(), "callDiCheckAPI");
            if (isHoldRequired(pMsg, cpoTMsg)) {

                // S21_NA#1880 start
                for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                    NWZC150001_APMsg aPMsg = pMsg.A.no(i);

                    if (RQST_TP_DTL_NEW.equals(aPMsg.xxRqstTpCd_A1.getValue())) {
                        if (callAddHoldAPI(pMsg, aPMsg.cpoDtlLineNum_A1.getValue(), aPMsg.cpoDtlLineSubNum_A1.getValue(), false)) {
                            return;
                        }
                    }
                }
                for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
                    NWZC150001_rtnDtlPMsg aPMsg = pMsg.rtnDtl.no(i);

                    if (RQST_TP_RTRN_DTL_NEW.equals(aPMsg.xxRqstTpCd_B1.getValue())) {
                        if (callAddHoldAPI(pMsg, aPMsg.cpoDtlLineNum_B1.getValue(), aPMsg.cpoDtlLineSubNum_B1.getValue(), true)) {
                            return;
                        }
                    }
                }
                // S21_NA#1880 end
            }

            if (ZYPConstant.FLG_ON_Y.equals(diCheckApiMsg.diChkHldFlg.getValue())) {
                // 2017/07/03 S21_NA#19737 Mod Start
//                // Add Start 2017/01/12 M.Ohno S21_NA#16655
//                if (!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, pMsg.xxValUpdFlg.getValue())) {
//                    CPOTMsg dsCpoTMsg4Upd = new CPOTMsg();
//                    EZDMsg.copy(cpoTMsg, null, dsCpoTMsg4Upd, null);
//
//                    dsCpoTMsg4Upd = (CPOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsCpoTMsg4Upd);
//                    if (dsCpoTMsg4Upd != null && //
//                            (!ZYPCommonFunc.hasValue(dsCpoTMsg4Upd.ordBookReqUsrId) || !ZYPCommonFunc.hasValue(dsCpoTMsg4Upd.ordBookReqTs))) {
//                        ZYPEZDItemValueSetter.setValue(dsCpoTMsg4Upd.dsCpoUpdUsrId, pMsg.usrId.getValue());
//                        ZYPEZDItemValueSetter.setValue(dsCpoTMsg4Upd.dsCpoUpdTs, this.curTime);
//                        ZYPEZDItemValueSetter.setValue(dsCpoTMsg4Upd.ordBookReqUsrId, pMsg.usrId.getValue());
//                        ZYPEZDItemValueSetter.setValue(dsCpoTMsg4Upd.ordBookReqTs, this.curTime);
//                        dsCpoTMsg4Upd.ordHdrDplyStsCd.setValue(ORD_HDR_DPLY_STS.DI_CHECK_HOLD);
//
//                        S21ApiTBLAccessor.update(dsCpoTMsg4Upd);
//                    }
//                }
//                // Add End   2017/01/12 M.Ohno S21_NA#16655
                if (!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, pMsg.xxValUpdFlg.getValue()) //
                        || !isAlreadyBookRequested(pMsg, cpoTMsg)) {
                    updateCpoAsBookRequested(pMsg, cpoTMsg);
                }
                // 2017/07/03 S21_NA#19737 Mod End
                return;
            }
        } else {
            // S21_NA#1957
            if (ZYPConstant.FLG_ON_Y.equals(cpoTMsg.diChkHldFlg.getValue())) {
                // 2017/07/03 S21_NA#19737 Add Start
                if (!isAlreadyBookRequested(pMsg, cpoTMsg)) {
                    updateCpoAsBookRequested(pMsg, cpoTMsg);
                }
                // 2017/07/03 S21_NA#19737 Add End
                return;
            }
        }

        // 2016/07/21 S21_NA#9228 Add Start
        if (isPayMethodCreditCardAndAmountMinusError(pMsg, false)) {
            return;
        }
        // 2016/07/21 S21_NA#9228 Add End
        
        // 2023/12/06 QC#61281 K.Ikeda START
        if (isPayMethodNotCreditCardAndAmountNotZeroError(pMsg, false)) {
            return;
        }
        // 2023/12/06 QC#61281 K.Ikeda START

        // 2016/07/26 S21_NA#11982 Move Start
//        // 2016/06/09 S21_NA#9426 Add Start
//        writePerformanceProfilingLogStart(getClass(), "crCardAuth");
//        if (crCardAuth(pMsg)) {
//            return;
//        }
//        writePerformanceProfilingLogEnd(getClass(), "crCardAuth");
//        // 2016/06/09 S21_NA#9426 Add End
        // 2016/07/26 S21_NA#11982 Move End
        if (ORD_HDR_STS.SAVED.equals(cpoTMsg.ordHdrStsCd.getValue())) {
            // 2017/04/13 S21_NA#Review structure Lv.2 Mod Start
            writePerformanceProfilingLogStart(getClass(), "callCpoUpdate2ndAPI");
            if (callCpoUpdate2ndAPI(pMsg, resPMsg2List, resPMsg3List, cpoTMsg)) { // 2016/09/05 S21_NA#6100 Add
                return;
            }
            writePerformanceProfilingLogEnd(getClass(), "callCpoUpdate2ndAPI");

            // IB allocation for conversion order only.
            if (!registAlloc(pMsg, resPMsg2List)) {
                return;
            }

//            boolean isPureReturn = isPureReturn(pMsg);
//            if (isPureReturn) {
//                writePerformanceProfilingLogStart(getClass(), "callRtrnUpdateAPI");
//                if (callRtrnUpdateAPI(pMsg, resPMsg3List, cpoTMsg, isPureReturn, false, NWZC150001Constant.CALL_SEQ_SUBMIT)) { //S21_NA#1952, 2411
//                    return;
//                }
//                writePerformanceProfilingLogEnd(getClass(), "callRtrnUpdateAPI");
//            } else {
//                writePerformanceProfilingLogStart(getClass(), "callCpoUpdate2ndAPI");
////                if (callCpoUpdate2ndAPI(pMsg, resPMsg2List, cpoTMsg)) { 2016/09/05 S21_NA#6100 Del
//                if (callCpoUpdate2ndAPI(pMsg, resPMsg2List, resPMsg3List, cpoTMsg)) { // 2016/09/05 S21_NA#6100 Add
//                    return;
//                }
//                writePerformanceProfilingLogEnd(getClass(), "callCpoUpdate2ndAPI");
//
//                // 2016/06/02 S21_NA#9273 Modify Start
//                // IB allocation for conversion order only.
//                if (!registAlloc(pMsg, resPMsg2List)) {
//                    return;
//                }
//                // 2016/06/02 S21_NA#9273 Modify End
//
//                // 2016/01/06 S21_NA#2588 Add Start
////                if (callRtrnUpdateAPIForCalcHdrAmt(pMsg)) { 2016/01/14 S21_NA#2996 Mod
//                writePerformanceProfilingLogStart(getClass(), "callRtrnUpdateAPI");
//                if (callRtrnUpdateAPI(pMsg, resPMsg3List, cpoTMsg, isPureReturn, false, NWZC150001Constant.CALL_SEQ_SUBMIT)) { // 2016/01/14 S21_NA#2996 Mod
//                    return;
//                }
//                writePerformanceProfilingLogEnd(getClass(), "callRtrnUpdateAPI");
//                // 2016/01/06 S21_NS#2588 Add End
//            }
            // 2017/04/13 S21_NA#Review structure Lv.2 Mod End
        }

        if (ORD_HDR_STS.VALIDATED.equals(cpoTMsg.ordHdrStsCd.getValue())) {
            CPOTMsg tMsg = new CPOTMsg();
            tMsg = (CPOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cpoTMsg);
            if (tMsg == null) {
                this.msgIdMgr.addXxMsgId(NWZM0073E, pMsg);
                return;
            }
            ZYPEZDItemValueSetter.setValue(tMsg.wfRejFlg, ZYPConstant.FLG_OFF_N);
            S21ApiTBLAccessor.updateSelectionField(tMsg, new String[] {"wfRejFlg" });
        }

        // S21_NA#11280 add start
        List<NWZC150001_APMsg> modifiedLisePMsgList = new ArrayList<NWZC150001_APMsg>();
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {

            NWZC150001_APMsg dsCpoLinePMsg = pMsg.A.no(i);
            if (S21StringUtil.isEquals(dsCpoLinePMsg.xxRqstTpCd_A1.getValue(), RQST_TP_DTL_MODIFY)) {

                modifiedLisePMsgList.add(dsCpoLinePMsg);
            }
        }
        if (modifiedLisePMsgList.size() > 0) {

            List<Map<String, Object>> ittOutboundHoldList = getITTOutboundHoldListForRelease(pMsg.cpoOrdNum.getValue());
            if (ittOutboundHoldList != null && ittOutboundHoldList.size() > 0) {

                // release ITT out bound hold
                if (callHoldReleaseAPIForIttOutboundHold(pMsg, modifiedLisePMsgList, ittOutboundHoldList)) {

                    return;
                }
            }
        }
        // S21_NA#11280 add end

        writePerformanceProfilingLogStart(getClass(), "callOrdHdrWorkflowCtrlAPI");
        if (callOrdHdrWorkflowCtrlAPI(pMsg, resPMsg2List, cpoTMsg)) {
            return;
        }
        writePerformanceProfilingLogEnd(getClass(), "callOrdHdrWorkflowCtrlAPI");
        // S21_NA#1880
        // if (isHoldRequired(pMsg, dsCpoTMsg)) {
        // if (callAddHoldAPI(pMsg)) {
        // return;
        // }
        // }

        // 2016/07/26 S21_NA#11982 Move to here Start
        writePerformanceProfilingLogStart(getClass(), "crCardAuth");

        // 2016/07/28 S21_NA#11982-2 Add Start
        if (S21StringUtil.isEquals(DS_PMT_METH.CREDIT_CARD, pMsg.dsPmtMethCd.getValue())) {
            this.isAlreadyAuthComp = false;
        }
        // 2016/07/28 S21_NA#11982-2 Add End

        if (crCardAuth(pMsg)) {
            return;
        }
        writePerformanceProfilingLogEnd(getClass(), "crCardAuth");
        // 2016/07/26 S21_NA#11982 Move to here End
        writePerformanceProfilingLogEnd(getClass(), "execSubmitMode");
    }

    // S21_NA#11280 add start
    private List<Map<String, Object>> getITTOutboundHoldListForRelease(String cpoOrdNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("hldRsnCd", HLD_RSN.ITT_OUTBOUND_HOLD);
        ssmParam.put("cpoOrdNum", cpoOrdNum);

        return NWZC150001Query.getInstance().getITTOutboundHoldListForRelease(ssmParam);
    }

    private boolean callHoldReleaseAPIForIttOutboundHold(NWZC150001PMsg pMsg, List<NWZC150001_APMsg> modifiedLisePMsgList, List<Map<String, Object>> ittOutboundHoldList) {

        boolean result = false;

        List<NWZC033001PMsg> nWZC033001PMsgList = new ArrayList<NWZC033001PMsg>();
        String ittOutboundHoldReleaseMemo = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_ITT_HOLD_RELEASE_MEMO, glblCmpyCd);
        for (Map<String, Object> ittOutboundHold : ittOutboundHoldList) {

            String cpoOrdNum = (String) ittOutboundHold.get("CPO_ORD_NUM");
            String cpoDtlLineNum = (String) ittOutboundHold.get("CPO_DTL_LINE_NUM");
            String cpoDtlLineSubNum = (String) ittOutboundHold.get("CPO_DTL_LINE_SUB_NUM");
            for (NWZC150001_APMsg modifiedLisePMsg : modifiedLisePMsgList) {

                if (!S21StringUtil.isEquals(pMsg.cpoOrdNum.getValue(), cpoOrdNum)) {

                    // cpo order number is incorrect.
                    continue;
                }
                if (!S21StringUtil.isEquals(modifiedLisePMsg.cpoDtlLineNum_A1.getValue(), cpoDtlLineNum)) {

                    // cpo order line number is incorrect.
                    continue;
                }
                if (!S21StringUtil.isEquals(modifiedLisePMsg.cpoDtlLineSubNum_A1.getValue(), cpoDtlLineSubNum)) {

                    // cpo order line sub number is incorrect.
                    continue;
                }

                NWZC033001PMsg nWZC033001PMsg = new NWZC033001PMsg();

                ZYPEZDItemValueSetter.setValue(nWZC033001PMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(nWZC033001PMsg.slsDt, this.slsDt);
                ZYPEZDItemValueSetter.setValue(nWZC033001PMsg.hldRelRsnCd, HLD_REL_RSN.AUTO);
                ZYPEZDItemValueSetter.setValue(nWZC033001PMsg.hldPk, (BigDecimal) ittOutboundHold.get("HLD_PK"));
                ZYPEZDItemValueSetter.setValue(nWZC033001PMsg.cpoOrdNum, cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(nWZC033001PMsg.cpoDtlLineNum, cpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(nWZC033001PMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
                ZYPEZDItemValueSetter.setValue(nWZC033001PMsg.relMemoTxt, S21StringUtil.subStringByLength(ittOutboundHoldReleaseMemo, 0, 400));

                nWZC033001PMsgList.add(nWZC033001PMsg);
            }
        }

        if (nWZC033001PMsgList.size() > 0) {

            // relese ITT out bound hold.
            (new NWZC033001()).execute(nWZC033001PMsgList, commonBean.getOnBatchType());

            // API error check
            for (NWZC033001PMsg nWZC033001PMsg : nWZC033001PMsgList) {

                List<String> ml = S21ApiUtil.getXxMsgIdList(nWZC033001PMsg);
                for (String msgId : ml) {

                    this.msgIdMgr.addXxMsgId(msgId, pMsg);
                    result = true;
                }
            }
        }

        return result;
    }

    // S21_NA#11280 add end

    private boolean callDiCheckAPI(NWZC150001PMsg pMsg, NWZC155001PMsg diCheckApiMsg) {

        boolean rtn = false;
        ZYPEZDItemValueSetter.setValue(diCheckApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(diCheckApiMsg.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(diCheckApiMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(diCheckApiMsg.diChkSubmtPsnCd, pMsg.usrId);
        ZYPEZDItemValueSetter.setValue(diCheckApiMsg.diJobId, pMsg.bizAppId);

        String ordDt = pMsg.cpoOrdTs.getValue();
        int dtLen = diCheckApiMsg.getAttr("ordDt").getDigit();
        if (ZYPCommonFunc.hasValue(ordDt) && ordDt.length() >= dtLen) {
            ZYPEZDItemValueSetter.setValue(diCheckApiMsg.ordDt, ordDt.substring(0, dtLen));
        } else {
            ZYPEZDItemValueSetter.setValue(diCheckApiMsg.ordDt, pMsg.slsDt);
        }

        new NWZC155001().execute(diCheckApiMsg, commonBean.getOnBatchType());

        if (!S21ApiUtil.isXxMsgId(diCheckApiMsg)) {
            return rtn;
        }
        List<String> ml = S21ApiUtil.getXxMsgIdList(diCheckApiMsg);
        for (String msgId : ml) {
            this.msgIdMgr.addXxMsgId(msgId, pMsg);
            if (msgId.endsWith("E")) {
                rtn = true;
            }
        }
        return rtn;
    }

    private boolean callOrdHdrWorkflowCtrlAPI(NWZC150001PMsg msg, List<NWZC150002PMsg> resPMsg2List, CPOTMsg cpoTMsg) {

        // 2016/08/10 S21_NA#5394 Add Start
        if (isAlreadyStartedWorkFlow(msg)) {
            boolean rslt = callOrdProcWorkflowCancelAPI(msg, resPMsg2List, cpoTMsg, CANCEL); // 2016/08/22 S21_NA#5394-2 Add result
            // 2016/08/22 S21_NA#5394-2 Add Start
            if (rslt) {
                return rslt;
            }
            // 2016/08/22 S21_NA#5394-2 Add End
        }
        // 2016/08/10 S21_NA#5394 Add End
        NWZC164001PMsg WorkFlowControlPMsg = new NWZC164001PMsg();

        ZYPEZDItemValueSetter.setValue(WorkFlowControlPMsg.glblCmpyCd, msg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(WorkFlowControlPMsg.cpoOrdNum, msg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(WorkFlowControlPMsg.slsDt, msg.slsDt);

        NWZC164001 WorkFlowControlAPI = new NWZC164001();
        WorkFlowControlAPI.execute(WorkFlowControlPMsg, commonBean.getOnBatchType());

        if (WorkFlowControlPMsg.xxMsgIdList.getValidCount() > 0) {
            for (int j = 0; j < WorkFlowControlPMsg.xxMsgIdList.getValidCount(); j++) {
                String xxMsgId = WorkFlowControlPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                this.msgIdMgr.addXxMsgId(xxMsgId, msg);
            }
            return true;
        }
        return false;
    }

    private boolean callCpoUpdate2ndAPI(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List, CPOTMsg cpoTMsg) { // 2016/09/05 S21_NA#6100 Add DS_CPOTMsg dsCpoTMsg

//        NWZC150001CpouBean cpouBean = new NWZC150001CpouBean(pMsg);

        cpouBean.setRqstTpCd(NWZC150001CpouConstant.CPO_SUBMIT);

        if (!ZYPCommonFunc.hasValue(cpouBean.getCpoOrdNum())) {
            cpouBean.setOrgRqstDelyDt(pMsg.addRddDt.getValue());
        }
        cpouBean.setCpoOrdTpCd(NWXC150001DsCheck.getCpoOrdTpCdFromDsOrdTp(glblCmpyCd, pMsg.dsOrdTpCd.getValue()));
        cpouBean.setInvRcpntCustCd(cpouBean.getBillToCustCd());
        cpouBean.setSlsRepTocCd(pMsg.slsRepCd.getValue());
        cpouBean.setXxValUpdFlg(ZYPConstant.FLG_OFF_N);

        String usrId = pMsg.usrId.getValue();

        int idx = 0;
        for (NWZC150001CpouDetailBean cpouDtlBean : cpouBean.getDtlBeanList()) {

            cpouDtlBean.setUomCpltFlg(ZYPConstant.FLG_OFF_N);
            cpouDtlBean.setDtlRqstTpCd(pMsg.A.no(idx).xxRqstTpCd_A1.getValue());

            if (!ZYPCommonFunc.hasValue(cpouDtlBean.getSlsRepOrSlsTeamTocCd())) {
                cpouDtlBean.setSlsRepOrSlsTeamTocCd(pMsg.slsRepCd.getValue());
            }
            cpouDtlBean.setPmtTermCashDiscCd(cpouBean.getAddPmtTermCashDiscCd());
            cpouDtlBean.setCashDiscTermCd(cpouBean.getAddCashDiscTermCd());
            cpouDtlBean.setFrtChrgToCd(cpouBean.getAddFrtChrgToCd());
            cpouDtlBean.setFrtChrgMethCd(cpouBean.getAddFrtChrgMethCd());
            cpouDtlBean.setShpgSvcLvlCd(cpouBean.getAddShpgSvcLvlCd());
            cpouDtlBean.setCarrAcctNum(cpouBean.getCarrAcctNum());

            if (ZYPCommonFunc.hasValue(cpouDtlBean.getSerNum())) {
                if (!ZYPCommonFunc.hasValue(cpouDtlBean.getSvcMachMstrPk())) {
                    BigDecimal svcMachMstrPk = NWZC150001Query.getInstance().getSvcMachMstrPk(glblCmpyCd, cpouDtlBean.getSerNum(), cpouDtlBean.getMdseCd());
                    cpouDtlBean.setSvcMachMstrPk(svcMachMstrPk);
                }
            }

            CPO_DTLTMsg dsCpoDtlMsg = getCurrentDsCpoDtlMsg(cpouBean, cpouDtlBean);
            if (dsCpoDtlMsg == null) {
                cpouDtlBean.setDsCpoDtlCratUsrId(usrId);
                cpouDtlBean.setDsCpoDtlCratTs(this.curTime);
            } else {
                cpouDtlBean.setDsCpoDtlCratUsrId(dsCpoDtlMsg.dsCpoDtlCratUsrId.getValue());
                cpouDtlBean.setDsCpoDtlCratTs(dsCpoDtlMsg.dsCpoDtlCratTs.getValue());
            }

            cpouDtlBean.setDsCpoDtlUpdUsrId(usrId);
            cpouDtlBean.setDsCpoDtlUpdTs(this.curTime);
            cpouDtlBean.setConvProcStsCd(getConvProcStsCd(pMsg, cpouDtlBean));

            for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
                NWZC150001_cpoConfigPMsg configMsg = pMsg.cpoConfig.no(i);
                if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configMsg.configCatgCd.getValue())) {
                    if (S21StringUtil.isEquals(cpouDtlBean.getDsOrdPosnNum(), configMsg.dsOrdPosnNum.getValue())) {
                        cpouDtlBean.setDsCpoConfigPk(configMsg.dsCpoConfigPk.getValue());
                    }
                }
            }
            idx++;
        }
        // 2017/04/28 S21_NA#RS#5881 Mod Start
//        if ((pMsg.rtnDtl.getValidCount() > 0)) {
//            cpouBean.setCpoRtrnDtlUpdFlg(ZYPConstant.FLG_ON_Y);
//        } else {
//            cpouBean.setCpoRtrnDtlUpdFlg(ZYPConstant.FLG_OFF_N);
//        }
        cpouBean.setCpoRtrnDtlUpdFlg(ZYPConstant.FLG_OFF_N);
        // 2017/04/28 S21_NA#RS#5881 Mod End

        NWZC150001CpouHldBean hldBean = new NWZC150001CpouHldBean();
        hldBean.setXxRqstTpCd(NWZC150001CpouConstant.REQUEST_TYPE_NEW);
        hldBean.setHldRsnCd(HLD_RSN.BOOK);
        cpouBean.addHldBeanList(hldBean);

        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
            cpouBean.setDsCpoCratUsrId(usrId);
            cpouBean.setDsCpoCratTs(this.curTime);
        } else {
            cpouBean.setDsCpoCratUsrId(cpoTMsg.dsCpoCratUsrId.getValue());
            cpouBean.setDsCpoCratTs(cpoTMsg.dsCpoCratTs.getValue());
        }
        cpouBean.setDsCpoUpdUsrId(usrId);
        cpouBean.setDsCpoUpdTs(this.curTime);

        cpouBean.setOrdBookReqUsrId(usrId);
        cpouBean.setOrdBookReqTs(this.curTime);

//        new NWZC150001CpouExecCpoUpd().execute(pMsg, cpouBean, pMsg2List, onBatchType);
        // 2017/06/13 S21_NA#18869-2 Mod Start
//        NWZC150001CpouUpdateOrderData.getInstance().updateOrderData(pMsg, this.cpouBean, pMsg2List, pMsg3List, msgIdMgr, commonBean.getOnBatchType());
        getUpdOrdDataInstance().updateOrderData(pMsg, this.cpouBean, pMsg2List, pMsg3List, msgIdMgr, commonBean.getOnBatchType());
        // 2017/06/13 S21_NA#18869-2 Mod End

        boolean isCpoUpdApiOutMsgError = isCpoUpdApiOutMsgError(pMsg2List);

        if (pMsg.xxMsgIdList.getValidCount() == 0 && !isCpoUpdApiOutMsgError) {
            // 2017/04/17 S21_NA#Review structure Lv.1 Del Start
//            editAmountForPrcDtl(pMsg, cpouBean, pMsg2List);
            // 2017/04/17 S21_NA#Review structure Lv.1 Del End
            return false;
        }

        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

            final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (xxMsgId.endsWith("E")) {
                return true;
            }
        }

        for (NWZC150002PMsg resPMsg : pMsg2List) {

            for (int i = 0; i < resPMsg.xxMsgIdList.getValidCount(); i++) {

                final String xxMsgId = resPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (xxMsgId.endsWith("E")) {
                    return true;
                }
            }
        }
        // 2017/04/17 S21_NA#Review structure Lv.1 Del Start
//        editAmountForPrcDtl(pMsg, cpouBean, pMsg2List);
        // 2017/04/17 S21_NA#Review structure Lv.1 Del End
        return false;
    }

    private boolean callAddHoldAPI(NWZC150001PMsg pMsg, String cpoDtlLineNum, String cpoDtlLineSubNum, boolean isCallRtrn) { // Mod S21_NA#17536
        final NWZC044001PMsg addHolddApiMsg = new NWZC044001PMsg();

        ZYPEZDItemValueSetter.setValue(addHolddApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(addHolddApiMsg.cpoOrdNum, pMsg.cpoOrdNum);
        // S21_NA#1880
        ZYPEZDItemValueSetter.setValue(addHolddApiMsg.cpoDtlLineNum, cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(addHolddApiMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(addHolddApiMsg.hldRsnCd, HLD_RSN.ADD_LINE_HOLD);
        ZYPEZDItemValueSetter.setValue(addHolddApiMsg.slsDt, pMsg.slsDt);

        // Add Start S21_NA#17536
        if (isCallRtrn) {
            ZYPEZDItemValueSetter.setValue(addHolddApiMsg.trxSrcTpCd, TRX_SRC_TP.WHOLE_SALES_RETURN);
        }
        // Add End S21_NA#17536

        new NWZC044001().execute(addHolddApiMsg, commonBean.getOnBatchType());

        if (!S21ApiUtil.isXxMsgId(addHolddApiMsg)) {
            return false;
        }
        List<String> ml = S21ApiUtil.getXxMsgIdList(addHolddApiMsg);
        for (String msgId : ml) {
            this.msgIdMgr.addXxMsgId(msgId, pMsg);
        }

        for (int i = 0; i < addHolddApiMsg.xxMsgIdList.getValidCount(); i++) {

            final String xxMsgId = addHolddApiMsg.xxMsgIdList.no(i).xxMsgId.getValue();

            if (xxMsgId.endsWith("E")) {
                return true;
            }
        }
        return false;
    }

    private boolean isHoldRequired(NWZC150001PMsg pMsg, CPOTMsg dsCpoTMsg) {
        // 2017/10/16 S21_NA#21808 Del Start
//        if (ZYPConstant.FLG_OFF_N.equals(dsCpoTMsg.ordBookFlg.getValue())) {
//            return false;
//        }
//        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
//            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
//
//            if (RQST_TP_DTL_NEW.equals(aPMsg.xxRqstTpCd_A1.getValue())) {
//                return true;
//            }
//        }
//        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
//            NWZC150001_rtnDtlPMsg aPMsg = pMsg.rtnDtl.no(i);
//
//            if (RQST_TP_RTRN_DTL_NEW.equals(aPMsg.xxRqstTpCd_B1.getValue())) {
//                return true;
//            }
//        }
        // 2017/10/16 S21_NA#21808 Del End
        return false;
    }

    private void execSaveMode(NWZC150001PMsg pMsg, List<NWZC150002PMsg> resPMsg2List, List<NWZC150003PMsg> resPMsg3List, CPOTMsg cpoTMsg) { // S21_NA#1957

        writePerformanceProfilingLogStart(getClass(), "callCpoConfigUpdateAPI");
        String cpoOrdNum = cpouBean.getCpoOrdNum_A1();
        if (!ZYPCommonFunc.hasValue(cpoOrdNum)) {
            cpoOrdNum = cpouBean.getCpoOrdNum();
        }
        boolean isCpoOrdNumEmpty = !ZYPCommonFunc.hasValue(pMsg.cpoOrdNum);
        if (isCpoOrdNumEmpty) {
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, cpoOrdNum);
        }
        boolean isError = false;
        isError = callCpoConfigUpdateAPI(pMsg);
        if (isCpoOrdNumEmpty) {
            pMsg.cpoOrdNum.clear();
        }
        if (isError) {
            return;
        }
        writePerformanceProfilingLogEnd(getClass(), "callCpoConfigUpdateAPI");

        // 2017/04/13 S21_NA#Review structure Lv.2 Mod Start
        writePerformanceProfilingLogStart(getClass(), "callCpoUpdateAPI");
        if (callCpoUpdateAPI(pMsg, resPMsg2List, resPMsg3List, cpoTMsg)) { // S21_NA#1957
            return;
        }
        writePerformanceProfilingLogEnd(getClass(), "callCpoUpdateAPI");
        if (!ZYPCommonFunc.hasValue(cpoTMsg.cpoOrdNum)) {
            CPOTMsg cpoTMsgCond = new CPOTMsg();
            cpoTMsgCond.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            cpoTMsgCond.cpoOrdNum.setValue(pMsg.cpoOrdNum.getValue());
            cpoTMsgCond = (CPOTMsg) S21FastTBLAccessor.findByKey(cpoTMsgCond);
            EZDMsg.copy(cpoTMsgCond, null, cpoTMsg, null); 
        }

//        boolean isPureReturn = isPureReturn(pMsg);
//        if (isPureReturn) {
//            writePerformanceProfilingLogStart(getClass(), "callRtrnUpdateAPI");
//            if (callRtrnUpdateAPI(pMsg, resPMsg3List, cpoTMsg, isPureReturn, true)) { // S21_NA#1952
//                return;
//            }
//            writePerformanceProfilingLogEnd(getClass(), "callRtrnUpdateAPI");
//        } else {
//            writePerformanceProfilingLogStart(getClass(), "callCpoUpdateAPI");
//            if (callCpoUpdateAPI(pMsg, resPMsg2List, resPMsg3List, cpoTMsg)) { // S21_NA#1957
//                return;
//            }
//            writePerformanceProfilingLogEnd(getClass(), "callCpoUpdateAPI");
//            // 2015/12/18 S21_NA#2189 Add Start
//            if (!ZYPCommonFunc.hasValue(cpoTMsg.cpoOrdNum)) {
//                CPOTMsg cpoTMsgCond = new CPOTMsg();
//                cpoTMsgCond.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
//                cpoTMsgCond.cpoOrdNum.setValue(pMsg.cpoOrdNum.getValue());
//                cpoTMsgCond = (CPOTMsg) S21FastTBLAccessor.findByKey(cpoTMsgCond);
//                EZDMsg.copy(cpoTMsgCond, null, cpoTMsg, null); 
//            }
//            // 2015/12/18 S21_NA#2189 Add End
//            writePerformanceProfilingLogStart(getClass(), "callRtrnUpdateAPI");
//            if (callRtrnUpdateAPI(pMsg, resPMsg3List, cpoTMsg, isPureReturn, false)) { // S21_NA#1952
//                return;
//            }
//            writePerformanceProfilingLogEnd(getClass(), "callRtrnUpdateAPI");
//        }
        // 2017/04/13 S21_NA#Review structure Lv.2 Mod End
        writePerformanceProfilingLogStart(getClass(), "callInvoiceCommentAPI");
        if (callInvoiceCommentAPI(pMsg)) {
            return;
        }
        writePerformanceProfilingLogEnd(getClass(), "callInvoiceCommentAPI");
        writePerformanceProfilingLogStart(getClass(), "callSlsCrUpdateAPI");
        if (callSlsCrUpdateAPI(pMsg)) {
            return;
        }
        writePerformanceProfilingLogEnd(getClass(), "callSlsCrUpdateAPI");
        if (callDiSsCtacUpdateAPI(pMsg)) {
            return;
        }

        // 2017/05/10 S21_NA#Review structure Lv.2 No calling Shell API Start
//        if (S21StringUtil.isEquals(ZYPCodeDataUtil.getVarCharConstValue("USE_NWZC227001", glblCmpyCd), ZYPConstant.FLG_ON_Y)) {
//
//            if (callShellUpdateAPI(pMsg)) {
//
//                return;
//            }
//        } else {
//
//            if (callShellAPI(pMsg)) {
//
//                return;
//            }
//        }
        // 2017/05/10 S21_NA#Review structure Lv.2 No calling Shell API End

        // 2016/06/09 S21_NA#9426 Add Start
        if (ORD_HDR_STS.VALIDATED.equals(cpoTMsg.ordHdrStsCd.getValue())) {
            boolean crCardValidRslt = false;
            if (S21StringUtil.isEquals(DS_PMT_METH.CREDIT_CARD, pMsg.dsPmtMethCd.getValue())) {
                // 2016/07/12 S21_NA#9426-4 Mod Start
//                DS_CPO_VTMsg dsCpoVTMsg = getDsCpoVRecord(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue());
//                if (null != dsCpoVTMsg) {
//                    BigDecimal ordAmt = dsCpoVTMsg.ordTotDealSubTotAmt.getValue();
//                    if (null == ordAmt) {
//                        ordAmt = BigDecimal.ZERO;
//                    }
//
//                    // get CR_CARD_TRX record
//                    boolean isAlreadyCrCardAuth = false;
//                    List<CR_CARD_TRXTMsg> crCardTrxTMsgList = getAutorizedCrCardTrx(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue());
//                    if (!crCardTrxTMsgList.isEmpty()) {
//                        for (CR_CARD_TRXTMsg crCardTrxTMsg : crCardTrxTMsgList) {
//                            if (ordAmt.compareTo(crCardTrxTMsg.crCardAuthAmt.getValue()) == 0) {
//                                isAlreadyCrCardAuth = true;
//                                break;
//                            }
//                        }
//                        if (!isAlreadyCrCardAuth) {
//                            crCardValidRslt = crCardAuth(pMsg);
//                        }
//                    }
//                }
                crCardValidRslt = crCardAuth(pMsg);
                // 2016/07/12 S21_NA#9426-4 Mod End
            } else {
                crCardValidRslt = voidAuthCompletedCrCardTrx(pMsg);
            }
            if (crCardValidRslt) {
                return;
            }
        }
        // 2016/06/09 S21_NA#9426 Add Start
    }

    private boolean callInvoiceCommentAPI(NWZC150001PMsg pMsg) {
        // 2016/10/11 S21_NA#13022 Mod Start
//        if (!ZYPCommonFunc.hasValue(pMsg.invCmntTxt)) {
//            return false;
//        }
        // 2016/10/11 S21_NA#13022 Mod End

        NWZC042001PMsg atPMsg = new NWZC042001PMsg();

        ZYPEZDItemValueSetter.setValue(atPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(atPMsg.cpoOrdNum, pMsg.cpoOrdNum);
        int i = 0;
        for (String msgTxtInfoTxt : separeteInvoiceComment(pMsg.invCmntTxt.getValue())) {
            // 2015/12/02 S21_NA#1293 Update Start
            // ZYPEZDItemValueSetter.setValue(atPMsg.A.no(i++).txtTpCd, TXT_TP.INVOICE_COMMENT);
            // ZYPEZDItemValueSetter.setValue(atPMsg.A.no(i++).msgTxtInfoTxt, msgTxtInfoTxt);
            ZYPEZDItemValueSetter.setValue(atPMsg.A.no(i).txtTpCd, TXT_TP.INVOICE_COMMENT);
            ZYPEZDItemValueSetter.setValue(atPMsg.A.no(i).msgTxtInfoTxt, msgTxtInfoTxt);
            i++;
            // 2015/12/02 S21_NA#1293 Update End
        }
        atPMsg.A.setValidCount(i);

        NWZC042001 atApi = new NWZC042001();
        atApi.execute(atPMsg, commonBean.getOnBatchType());

        if (S21ApiUtil.isXxMsgId(atPMsg)) {
            List<String> ml = S21ApiUtil.getXxMsgIdList(atPMsg);
            for (String m : ml) {
                this.msgIdMgr.addXxMsgId(m, pMsg);
            }
            return true;
        }
        return false;
    }

    private List<String> separeteInvoiceComment(String invCmtTxt) {
        List<String> ls = new ArrayList<String>();
        // 2016/10/11 S21_NA#13022 Add Start
        if (!ZYPCommonFunc.hasValue(invCmtTxt)) {
            ls.add("");
            return ls;
        }
        // 2016/10/11 S21_NA#13022 Add End
        for (int i = 0; i < invCmtTxt.length(); i += 65) {
            String s = S21StringUtil.subStringByLength(invCmtTxt, i, 65);
            ls.add(s);
        }
        return ls;
    }

    private boolean callOrdWorkflowAPI(NWZC150001PMsg pMsg) {
        // TODO callOrdWorkflowAPI
        return false;
    }

    // 2017/05/10 S21_NA#Review structure Lv.2 No calling Shell API Start
//    private boolean callShellAPI(NWZC150001PMsg pMsg) {
//        if (pMsg.cpoSvcDtl.getValidCount() == 0) {
//            return false;
//        }
//        // TODO 2017/04/03 S21_NA#Review structure Lv.1 Del Start temporary modify Start
//        return false;
//        NWZC154001PMsg shellPMsg = new NWZC154001PMsg();
//        EZDMsg.copy(pMsg.cpoSvcAddlBasePrc, null, shellPMsg.cpoSvcAddlBasePrcList, null);
//        EZDMsg.copy(pMsg.cpoSvcAddlChrgPrc, null, shellPMsg.cpoSvcAddlChrgPrcList, null);
//        EZDMsg.copy(pMsg.cpoSvcConfigRef, null, shellPMsg.cpoSvcConfigRefList, null);
//        // 2016/10/18 S21_NA#9362 Mod Start
//        // EZDMsg.copy(pMsg.cpoSvcDtl, null, shellPMsg.cpoSvcDtlList, null);
//        int cpoSvcDtlCnt = 0;
//        for (; cpoSvcDtlCnt < pMsg.cpoSvcDtl.getValidCount(); cpoSvcDtlCnt++) {
//            NWZC150001_cpoSvcDtlPMsg cpoSvcDtlPMsg = pMsg.cpoSvcDtl.no(cpoSvcDtlCnt);
//            if (!ZYPCommonFunc.hasValue(cpoSvcDtlPMsg.addAsryFlg)) {
//                cpoSvcDtlPMsg.addAsryFlg.setValue(ZYPConstant.FLG_OFF_N);
//            }
//            EZDMsg.copy(cpoSvcDtlPMsg, null, shellPMsg.cpoSvcDtlList.no(cpoSvcDtlCnt), null);
//        }
//        shellPMsg.cpoSvcDtlList.setValidCount(cpoSvcDtlCnt);
//        // 2016/10/18 S21_NA#9362 Mod End
//        EZDMsg.copy(pMsg.cpoSvcPrc, null, shellPMsg.cpoSvcPrcList, null);
//        EZDMsg.copy(pMsg.cpoSvcUsgPrc, null, shellPMsg.cpoSvcUsgPrcList, null);
//
//        ZYPEZDItemValueSetter.setValue(shellPMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(shellPMsg.refCpoOrdNum, pMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(shellPMsg.slsDt, slsDt);
//        ZYPEZDItemValueSetter.setValue(shellPMsg.xxProcMd, NWZC154001Constant.PROC_MODE_NEW);
//        shellPMsg.cpoSvcPk.clear();
//
//        // 2016/02/23 S21_NA#4078 Add Start
//        NWZC150001_cpoSvcConfigRefPMsg cpoSvcConfigRefPMsg;
//        NWZC154001_cpoSvcConfigRefListPMsg apiCpoSvcConfigRefPMsg;
//        BigDecimal svcConfigMstrPk;
//        for (int i = 0; i < shellPMsg.cpoSvcConfigRefList.getValidCount(); i++) {
//
//            cpoSvcConfigRefPMsg = pMsg.cpoSvcConfigRef.no(i);
//            apiCpoSvcConfigRefPMsg = shellPMsg.cpoSvcConfigRefList.no(i);
//
//            if (!ZYPCommonFunc.hasValue(apiCpoSvcConfigRefPMsg.dsOrdPosnNum)) {
//                if (ZYPCommonFunc.hasValue(cpoSvcConfigRefPMsg.ordLinePosnNum)) {
//
//                    ZYPEZDItemValueSetter.setValue(apiCpoSvcConfigRefPMsg.dsOrdPosnNum, cpoSvcConfigRefPMsg.ordLinePosnNum.getValue().toString());
//                } else {
//
//                    ZYPEZDItemValueSetter.setValue(apiCpoSvcConfigRefPMsg.dsOrdPosnNum, "0");
//                }
//            }
//
//            if (!ZYPCommonFunc.hasValue(apiCpoSvcConfigRefPMsg.cpoOrdNum)) {
//                ZYPEZDItemValueSetter.setValue(apiCpoSvcConfigRefPMsg.cpoOrdNum, pMsg.cpoOrdNum);
//            }
//
//            svcConfigMstrPk = getSvcConfigMstrPkFromPMsg(pMsg, apiCpoSvcConfigRefPMsg.cpoDtlLineNum.getValue(), apiCpoSvcConfigRefPMsg.cpoDtlLineSubNum.getValue());
//            if (ZYPCommonFunc.hasValue(svcConfigMstrPk) && svcConfigMstrPk.compareTo(BigDecimal.valueOf(-1)) > 0) {
//                ZYPEZDItemValueSetter.setValue(apiCpoSvcConfigRefPMsg.svcConfigMstrPk, svcConfigMstrPk);
//            }
//        }
//
//        NWZC150001_cpoSvcPrcPMsg cpoSvcPrcPMsg;
//        NWZC154001_cpoSvcPrcListPMsg apiCpoSvcPrcList;
//        for (int i = 0; i < shellPMsg.cpoSvcPrcList.getValidCount(); i++) {
//            cpoSvcPrcPMsg = pMsg.cpoSvcPrc.no(i);
//            apiCpoSvcPrcList = shellPMsg.cpoSvcPrcList.no(i);
//
//            if (!ZYPCommonFunc.hasValue(apiCpoSvcPrcList.dsOrdPosnNum)) {
//                if (ZYPCommonFunc.hasValue(cpoSvcPrcPMsg.ordLinePosnNum)) {
//                    ZYPEZDItemValueSetter.setValue(apiCpoSvcPrcList.dsOrdPosnNum, cpoSvcPrcPMsg.ordLinePosnNum.getValue().toString());
//                } else {
//                    ZYPEZDItemValueSetter.setValue(apiCpoSvcPrcList.dsOrdPosnNum, "0");
//                }
//            }
//        }
//
//        NWZC150001_cpoSvcUsgPrcPMsg cpoSvcUsgPrcPMsg;
//        NWZC154001_cpoSvcUsgPrcListPMsg apiCpoSvcUsgPrcListPMsg;
//        for (int i = 0; i < shellPMsg.cpoSvcUsgPrcList.getValidCount(); i++) {
//            cpoSvcUsgPrcPMsg = pMsg.cpoSvcUsgPrc.no(i);
//            apiCpoSvcUsgPrcListPMsg = shellPMsg.cpoSvcUsgPrcList.no(i);
//
//            ZYPEZDItemValueSetter.setValue(apiCpoSvcUsgPrcListPMsg.copyInclPrcQty, cpoSvcUsgPrcPMsg.mlyCopyInclPrcQty);
//        }
//
//        NWZC154001_cpoSvcAddlBasePrcListPMsg apiCpoSvcAddlBasePrcListPMsg;
//        for (int i = 0; i < shellPMsg.cpoSvcAddlBasePrcList.getValidCount(); i++) {
//            apiCpoSvcAddlBasePrcListPMsg = shellPMsg.cpoSvcAddlBasePrcList.no(i);
//
//            if (!ZYPCommonFunc.hasValue(apiCpoSvcAddlBasePrcListPMsg.cpoOrdNum)) {
//                ZYPEZDItemValueSetter.setValue(apiCpoSvcAddlBasePrcListPMsg.cpoOrdNum, pMsg.cpoOrdNum);
//            }
//        }
//
//        NWZC154001_cpoSvcAddlChrgPrcListPMsg apiCpoSvcAddlChrgPrcListPMsg;
//        for (int i = 0; i < shellPMsg.cpoSvcAddlChrgPrcList.getValidCount(); i++) {
//            apiCpoSvcAddlChrgPrcListPMsg = shellPMsg.cpoSvcAddlChrgPrcList.no(i);
//
//            if (!ZYPCommonFunc.hasValue(apiCpoSvcAddlChrgPrcListPMsg.cpoOrdNum)) {
//                ZYPEZDItemValueSetter.setValue(apiCpoSvcAddlChrgPrcListPMsg.cpoOrdNum, pMsg.cpoOrdNum);
//            }
//        }
//        // 2016/02/23 S21_NA#4078 Add End
//
//        new NWZC154001().execute(shellPMsg, onBatchType);
//
//        if (!S21ApiUtil.isXxMsgId(shellPMsg)) {
//            return false;
//        }
//        boolean rtn = false;
//        List<String> ml = S21ApiUtil.getXxMsgIdList(shellPMsg);
//        int msgIndex = 0; // S21_NA#15796
//        for (String msgId : ml) {
//            setMsgId(pMsg, msgId);
//            if (msgId.endsWith("E")) {
//                rtn = true;
//            }
//            // S21_NA#15796 add start
//            if (msgIndex <= shellPMsg.xxMsgPrmList.getValidCount() - 1) {
//
//                ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount() - 1).xxRsltModeCd, NWZC150001Constant.RESULT_TP_SHELL);
//                ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount() - 1).xxLineNum, shellPMsg.xxMsgPrmList.no(msgIndex).xxMsgPrmTxt);
//            }
//            msgIndex++;
//            // S21_NA#15796 add start
//        }
//        return rtn;
//        // TODO 2017/04/03 S21_NA#Review structure Lv.1 Del Start temporary modify End
//    }

//    private boolean callShellUpdateAPI(NWZC150001PMsg pMsg) {
//
//        if (pMsg.cpoSvcDtl.getValidCount() == 0) {
//
//            return false;
//        }
//
//        NWZC227001PMsg shellPMsg = new NWZC227001PMsg();
//
//        // *********************************
//        // API header information
//        // *********************************
//        ZYPEZDItemValueSetter.setValue(shellPMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(shellPMsg.refCpoOrdNum, pMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(shellPMsg.slsDt, slsDt);
//        ZYPEZDItemValueSetter.setValue(shellPMsg.xxProcMd, NWZC227001Constant.PROC_MODE_NEW);
//        shellPMsg.cpoSvcPk.clear();
//
//        // *********************************
//        // service detail
//        // *********************************
//        int cpoSvcDtlCnt = 0;
//        for (; cpoSvcDtlCnt < pMsg.cpoSvcDtl.getValidCount(); cpoSvcDtlCnt++) {
//
//            NWZC150001_cpoSvcDtlPMsg cpoSvcDtlPMsg = pMsg.cpoSvcDtl.no(cpoSvcDtlCnt);
//            if (!ZYPCommonFunc.hasValue(cpoSvcDtlPMsg.addAsryFlg)) {
//
//                cpoSvcDtlPMsg.addAsryFlg.setValue(ZYPConstant.FLG_OFF_N);
//            }
//            EZDMsg.copy(cpoSvcDtlPMsg, null, shellPMsg.cpoSvcDtlList.no(cpoSvcDtlCnt), null);
//        }
//        shellPMsg.cpoSvcDtlList.setValidCount(cpoSvcDtlCnt);
//
//        // *********************************
//        // service configuration reference
//        // *********************************
//        EZDMsg.copy(pMsg.cpoSvcConfigRef, null, shellPMsg.cpoSvcConfigRefList, null);
//        // prepare CPO#, configuration#
//        // derive service configuration master
//        BigDecimal svcConfigMstrPk;
//        for (int i = 0; i < shellPMsg.cpoSvcConfigRefList.getValidCount(); i++) {
//
//            NWZC150001_cpoSvcConfigRefPMsg cpoSvcConfigRefPMsg = pMsg.cpoSvcConfigRef.no(i);
//            NWZC227001_cpoSvcConfigRefListPMsg apiCpoSvcConfigRefPMsg = shellPMsg.cpoSvcConfigRefList.no(i);
//            if (!ZYPCommonFunc.hasValue(apiCpoSvcConfigRefPMsg.dsOrdPosnNum)) {
//
//                // convert order line position# -> order position
//                // number
//                if (ZYPCommonFunc.hasValue(cpoSvcConfigRefPMsg.ordLinePosnNum)) {
//
//                    ZYPEZDItemValueSetter.setValue(apiCpoSvcConfigRefPMsg.dsOrdPosnNum, cpoSvcConfigRefPMsg.ordLinePosnNum.getValue().toString());
//                } else {
//
//                    // for fleet or relation error.
//                    ZYPEZDItemValueSetter.setValue(apiCpoSvcConfigRefPMsg.dsOrdPosnNum, "0");
//                }
//            }
//
//            if (!ZYPCommonFunc.hasValue(apiCpoSvcConfigRefPMsg.cpoOrdNum)) {
//
//                // set CPO# from order
//                ZYPEZDItemValueSetter.setValue(apiCpoSvcConfigRefPMsg.cpoOrdNum, pMsg.cpoOrdNum);
//            }
//
//            // derive service configuration master from line#
//            svcConfigMstrPk = getSvcConfigMstrPkFromPMsg(pMsg, apiCpoSvcConfigRefPMsg.cpoDtlLineNum.getValue(), apiCpoSvcConfigRefPMsg.cpoDtlLineSubNum.getValue());
//            if (ZYPCommonFunc.hasValue(svcConfigMstrPk) && svcConfigMstrPk.compareTo(BigDecimal.valueOf(-1)) > 0) {
//
//                ZYPEZDItemValueSetter.setValue(apiCpoSvcConfigRefPMsg.svcConfigMstrPk, svcConfigMstrPk);
//            }
//        }
//
//        // *********************************
//        // service price
//        // *********************************
//        EZDMsg.copy(pMsg.cpoSvcPrc, null, shellPMsg.cpoSvcPrcList, null);
//        // prepare CPO#, configuration#
//        for (int i = 0; i < shellPMsg.cpoSvcPrcList.getValidCount(); i++) {
//
//            NWZC150001_cpoSvcPrcPMsg cpoSvcPrcPMsg = pMsg.cpoSvcPrc.no(i);
//            NWZC227001_cpoSvcPrcListPMsg apiCpoSvcPrcList = shellPMsg.cpoSvcPrcList.no(i);
//
//            // convert order line position# -> order position number
//            if (!ZYPCommonFunc.hasValue(apiCpoSvcPrcList.dsOrdPosnNum)) {
//
//                if (ZYPCommonFunc.hasValue(cpoSvcPrcPMsg.ordLinePosnNum)) {
//
//                    ZYPEZDItemValueSetter.setValue(apiCpoSvcPrcList.dsOrdPosnNum, cpoSvcPrcPMsg.ordLinePosnNum.getValue().toString());
//                } else {
//
//                    // for fleet or relation error.
//                    ZYPEZDItemValueSetter.setValue(apiCpoSvcPrcList.dsOrdPosnNum, "0");
//                }
//            }
//        }
//
//        // *********************************
//        // service usage price
//        // *********************************
//        EZDMsg.copy(pMsg.cpoSvcUsgPrc, null, shellPMsg.cpoSvcUsgPrcList, null);
//        for (int i = 0; i < shellPMsg.cpoSvcUsgPrcList.getValidCount(); i++) {
//
//            NWZC150001_cpoSvcUsgPrcPMsg cpoSvcUsgPrcPMsg = pMsg.cpoSvcUsgPrc.no(i);
//            NWZC227001_cpoSvcUsgPrcListPMsg apiCpoSvcUsgPrcListPMsg = shellPMsg.cpoSvcUsgPrcList.no(i);
//
//            // convert order line position# -> order position number
//            if (!ZYPCommonFunc.hasValue(apiCpoSvcUsgPrcListPMsg.dsOrdPosnNum)) {
//
//                if (ZYPCommonFunc.hasValue(cpoSvcUsgPrcPMsg.ordLinePosnNum)) {
//
//                    ZYPEZDItemValueSetter.setValue(apiCpoSvcUsgPrcListPMsg.dsOrdPosnNum, cpoSvcUsgPrcPMsg.ordLinePosnNum.getValue().toString());
//                } else {
//
//                    // for fleet or relation error.
//                    ZYPEZDItemValueSetter.setValue(apiCpoSvcUsgPrcListPMsg.dsOrdPosnNum, "0");
//                }
//            }
//
//            ZYPEZDItemValueSetter.setValue(apiCpoSvcUsgPrcListPMsg.copyInclPrcQty, cpoSvcUsgPrcPMsg.mlyCopyInclPrcQty);
//        }
//
//        // *********************************
//        // service additional base price
//        // *********************************
//        EZDMsg.copy(pMsg.cpoSvcAddlBasePrc, null, shellPMsg.cpoSvcAddlBasePrcList, null);
//        for (int i = 0; i < shellPMsg.cpoSvcAddlBasePrcList.getValidCount(); i++) {
//
//            NWZC227001_cpoSvcAddlBasePrcListPMsg apiCpoSvcAddlBasePrcListPMsg = shellPMsg.cpoSvcAddlBasePrcList.no(i);
//
//            if (!ZYPCommonFunc.hasValue(apiCpoSvcAddlBasePrcListPMsg.cpoOrdNum)) {
//
//                ZYPEZDItemValueSetter.setValue(apiCpoSvcAddlBasePrcListPMsg.cpoOrdNum, pMsg.cpoOrdNum);
//            }
//        }
//
//        // *********************************
//        // service additional charge price
//        // *********************************
//        EZDMsg.copy(pMsg.cpoSvcAddlChrgPrc, null, shellPMsg.cpoSvcAddlChrgPrcList, null);
//        for (int i = 0; i < shellPMsg.cpoSvcAddlChrgPrcList.getValidCount(); i++) {
//            NWZC227001_cpoSvcAddlChrgPrcListPMsg apiCpoSvcAddlChrgPrcListPMsg = shellPMsg.cpoSvcAddlChrgPrcList.no(i);
//
//            if (!ZYPCommonFunc.hasValue(apiCpoSvcAddlChrgPrcListPMsg.cpoOrdNum)) {
//
//                ZYPEZDItemValueSetter.setValue(apiCpoSvcAddlChrgPrcListPMsg.cpoOrdNum, pMsg.cpoOrdNum);
//            }
//        }
//
//        new NWZC227001().execute(shellPMsg, onBatchType);
//
//        if (!S21ApiUtil.isXxMsgId(shellPMsg)) {
//            return false;
//        }
//        boolean result = false;
//        List<String> ml = S21ApiUtil.getXxMsgIdList(shellPMsg);
//        int msgIndex = 0;
//        for (String msgId : ml) {
//
//            setMsgId(pMsg, msgId);
//
//            if (msgId.endsWith("E")) {
//
//                result = true;
//            }
//            if (msgIndex <= shellPMsg.xxMsgPrmList.getValidCount() - 1) {
//
//                ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount() - 1).xxRsltModeCd, NWZC150001Constant.RESULT_TP_SHELL);
//                ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount() - 1).xxLineNum, shellPMsg.xxMsgPrmList.no(msgIndex).xxMsgPrmTxt);
//            }
//            msgIndex++;
//        }
//        return result;
//    }
    // 2017/05/10 S21_NA#Review structure Lv.2 No calling Shell API End

    private boolean callDiSsCtacUpdateAPI(NWZC150001PMsg pMsg) {
        // QC#14608 2016/10/06 Add Start
        //if (pMsg.cpoDlvyInfoList.getValidCount() //
        //        + pMsg.cpoIstlInfoList.getValidCount() //
        //        + pMsg.siteSrvInfoList.getValidCount() //
        //        + pMsg.cpoCtacPsnInfoList.getValidCount() == 0) {
        //    return false;
        //}
        // QC#14608 2016/10/06 Add End

        final NWZC183001PMsg diSsCtacUpdApiMsg = new NWZC183001PMsg();
        ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoOrdNum, pMsg.cpoOrdNum);
        // 2016/02/02 S21_NA#3375 Mod Start
//        for (int i = 0; i < pMsg.cpoDlvyInfoList.getValidCount(); i++) {
//            EZDMsg.copy(pMsg.cpoDlvyInfoList.no(i), null, diSsCtacUpdApiMsg.cpoDelyInfoList.no(i), null);
//        }
//        for (int i = 0; i < pMsg.cpoIstlInfoList.getValidCount(); i++) {
//            EZDMsg.copy(pMsg.cpoIstlInfoList.no(i), null, diSsCtacUpdApiMsg.cpoInstInfoList.no(i), null);
//        }
//        for (int i = 0; i < pMsg.siteSrvInfoList.getValidCount(); i++) {
//            EZDMsg.copy(pMsg.siteSrvInfoList.no(i), null, diSsCtacUpdApiMsg.siteSrvyInfoList.no(i), null);
//        }
//        for (int i = 0; i < pMsg.cpoCtacPsnInfoList.getValidCount(); i++) {
//            EZDMsg.copy(pMsg.cpoCtacPsnInfoList.no(i), null, diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(i), null);
//        }
        int prmCnt = 0;
        NWZC150001_cpoDlvyInfoListPMsg cpoDelyInfoPMsg;
        BigDecimal configPk;
        for (int i = 0; i < pMsg.cpoDlvyInfoList.getValidCount(); i++, prmCnt++) {
            EZDMsg.copy(pMsg.cpoDlvyInfoList.no(i), null, diSsCtacUpdApiMsg.cpoDelyInfoList.no(prmCnt), null);
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoDelyInfoList.no(prmCnt).xxModeCd, cnvDlvyInfoMode.get(pMsg.cpoDlvyInfoList.no(i).xxRqstTpCd.getValue()));
            // S21_NA#4078 Mod
            cpoDelyInfoPMsg = pMsg.cpoDlvyInfoList.no(i);
            configPk = getDsCpoConfigPkFromPMsg(pMsg, cpoDelyInfoPMsg.dsOrdPosnNum.getValue(), cpoDelyInfoPMsg.configCatgCd.getValue());
            if (configPk.compareTo(BigDecimal.valueOf(-1)) > 0) {
                ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoDelyInfoList.no(prmCnt).dsCpoConfigPk, configPk);
            }
        }
        diSsCtacUpdApiMsg.cpoDelyInfoList.setValidCount(prmCnt);
        prmCnt = 0;
        NWZC150001_cpoIstlInfoListPMsg cpoInstlInfoPMsg;

        // 2017/11/09 S21_NA#22091 Add Start
        createCpoIstlInfoList(pMsg);
        // 2017/11/09 S21_NA#22091 Add End

        for (int i = 0; i < pMsg.cpoIstlInfoList.getValidCount(); i++, prmCnt++) {
            EZDMsg.copy(pMsg.cpoIstlInfoList.no(i), null, diSsCtacUpdApiMsg.cpoInstInfoList.no(prmCnt), null);
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoInstInfoList.no(prmCnt).xxModeCd, cnvDlvyInfoMode.get(pMsg.cpoIstlInfoList.no(i).xxRqstTpCd.getValue()));
            // S21_NA#4078 Mod
            cpoInstlInfoPMsg = pMsg.cpoIstlInfoList.no(i);
            configPk = getDsCpoConfigPkFromPMsg(pMsg, cpoInstlInfoPMsg.dsOrdPosnNum.getValue(), cpoInstlInfoPMsg.configCatgCd.getValue());
            if (configPk.compareTo(BigDecimal.valueOf(-1)) > 0) {
                ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoInstInfoList.no(prmCnt).dsCpoConfigPk, configPk);
            }
        }
        diSsCtacUpdApiMsg.cpoInstInfoList.setValidCount(prmCnt);
        prmCnt = 0;
        NWZC150001_siteSrvInfoListPMsg cpoSiteSrvInfoPMsg;
        for (int i = 0; i < pMsg.siteSrvInfoList.getValidCount(); i++, prmCnt++) {
            EZDMsg.copy(pMsg.siteSrvInfoList.no(i), null, diSsCtacUpdApiMsg.siteSrvyInfoList.no(prmCnt), null);
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.siteSrvyInfoList.no(prmCnt).xxModeCd, cnvSiteSrvInfoMode.get(pMsg.siteSrvInfoList.no(i).xxRqstTpCd.getValue()));
            // S21_NA#4078 Mod
            cpoSiteSrvInfoPMsg = pMsg.siteSrvInfoList.no(i);
            configPk = getDsCpoConfigPkFromPMsg(pMsg, cpoSiteSrvInfoPMsg.dsOrdPosnNum.getValue(), cpoSiteSrvInfoPMsg.configCatgCd.getValue());
            if (configPk.compareTo(BigDecimal.valueOf(-1)) > 0) {
                ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.siteSrvyInfoList.no(prmCnt).dsCpoConfigPk, configPk);
            }
        }
        diSsCtacUpdApiMsg.siteSrvyInfoList.setValidCount(prmCnt);
        prmCnt = 0;
        NWZC150001_cpoCtacPsnInfoListPMsg cpoCtacPsnInfoPMsg;
        for (int i = 0; i < pMsg.cpoCtacPsnInfoList.getValidCount(); i++, prmCnt++) {
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(prmCnt).xxModeCd, cnvCtacMode.get(pMsg.cpoCtacPsnInfoList.no(i).xxRqstTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(prmCnt).dsCpoCtacPsnPk, pMsg.cpoCtacPsnInfoList.no(i).dsCpoCtacPsnPk);
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(prmCnt).ctacPsnPk, pMsg.cpoCtacPsnInfoList.no(i).ctacPsnPk);
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(prmCnt).dsCpoConfigPk, pMsg.cpoCtacPsnInfoList.no(i).dsCpoConfigPk);
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(prmCnt).ctacPsnTpCd, pMsg.cpoCtacPsnInfoList.no(i).ctacTpCd);
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(prmCnt).ctacPsnFirstNm, pMsg.cpoCtacPsnInfoList.no(i).firstNm);
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(prmCnt).ctacPsnLastNm, pMsg.cpoCtacPsnInfoList.no(i).lastNm);
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(prmCnt).ctacPsnEmlAddr, pMsg.cpoCtacPsnInfoList.no(i).emlAddr);
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(prmCnt).ctacPsnTelNum, pMsg.cpoCtacPsnInfoList.no(i).telNum);
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(prmCnt).ctacPsnFaxNum, pMsg.cpoCtacPsnInfoList.no(i).faxNum);
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(prmCnt).ctacPsnSortNum, pMsg.cpoCtacPsnInfoList.no(i).sortNum);
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(prmCnt).ctacPsnExtnNum, pMsg.cpoCtacPsnInfoList.no(i).ctacPsnExtnNum);
            // 2017/08/15 S21_NA#16452 Add Start
            String ctacCustRefTpCd = "";
            if (ZYPCommonFunc.hasValue(pMsg.cpoCtacPsnInfoList.no(i).ctacCustRefTpCd)) {
                ctacCustRefTpCd = pMsg.cpoCtacPsnInfoList.no(i).ctacCustRefTpCd.getValue();
            } else {
                ctacCustRefTpCd = CTAC_CUST_REF_TP.SHIP_TO;
            }
            ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(prmCnt).ctacCustRefTpCd, ctacCustRefTpCd);
            // 2017/08/15 S21_NA#16452 Add End

            // S21_NA#4078 Mod
            cpoCtacPsnInfoPMsg = pMsg.cpoCtacPsnInfoList.no(i);
            configPk = getDsCpoConfigPkFromPMsg(pMsg, cpoCtacPsnInfoPMsg.dsOrdPosnNum.getValue(), cpoCtacPsnInfoPMsg.configCatgCd.getValue());
            if (configPk.compareTo(BigDecimal.valueOf(-1)) > 0) {
                ZYPEZDItemValueSetter.setValue(diSsCtacUpdApiMsg.cpoCtacPsnInfoList.no(prmCnt).dsCpoConfigPk, configPk);
            }
        }
        diSsCtacUpdApiMsg.cpoCtacPsnInfoList.setValidCount(prmCnt);
        // 2016/02/02 S21_NA#3375 Mod End

        new NWZC183001().execute(diSsCtacUpdApiMsg, commonBean.getOnBatchType());

        if (!S21ApiUtil.isXxMsgId(diSsCtacUpdApiMsg)) {
            return false;
        }
        List<String> ml = S21ApiUtil.getXxMsgIdList(diSsCtacUpdApiMsg);
        for (String msgId : ml) {
            this.msgIdMgr.addXxMsgId(msgId, pMsg);
        }

        for (int i = 0; i < diSsCtacUpdApiMsg.xxMsgIdList.getValidCount(); i++) {
            final String xxMsgId = diSsCtacUpdApiMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (xxMsgId.endsWith("E")) {
                return true;
            }
        }
        return false;
    }

    private boolean callSlsCrUpdateAPI(NWZC150001PMsg pMsg) {
        if (pMsg.cpoSlsCr.getValidCount() == 0) {
            return false;
        }
        // 2017/06/30 S21_NA#18811 Add Start
        // If there is no Sales Credit for Config, add Sales Credit Data for specified Config.
        List<NWZC150001_cpoConfigPMsg> noSlsCrConfigList = new ArrayList<NWZC150001_cpoConfigPMsg>(0);
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            boolean hasConfigData = false;
            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(i);
            for (int j = 0; j < pMsg.cpoSlsCr.getValidCount(); j++) {
                NWZC150001_cpoSlsCrPMsg cpoSlsCrPMsg = pMsg.cpoSlsCr.no(j);
                if (ZYPCommonFunc.hasValue(configPMsg.dsCpoConfigPk) //
                        && ZYPCommonFunc.hasValue(cpoSlsCrPMsg.dsCpoConfigPk) //
                        && configPMsg.dsCpoConfigPk.getValue().compareTo(cpoSlsCrPMsg.dsCpoConfigPk.getValue()) == 0) {
                    hasConfigData = true;
                    break;
                }
                if (S21StringUtil.isEquals(configPMsg.dsOrdPosnNum.getValue(), cpoSlsCrPMsg.dsOrdPosnNum.getValue()) //
                        && S21StringUtil.isEquals(configPMsg.configCatgCd.getValue(), cpoSlsCrPMsg.configCatgCd.getValue())) {
                    hasConfigData = true;
                    break;
                }
            }
            if (!hasConfigData) {
                noSlsCrConfigList.add(configPMsg);
            }
        }
        List<NWZC150001_cpoSlsCrPMsg> addCpoSlsCrPMsgList = new ArrayList<NWZC150001_cpoSlsCrPMsg>(0);
        for (NWZC150001_cpoConfigPMsg configPMsg : noSlsCrConfigList) {
            for (int i = 0; i < pMsg.cpoSlsCr.getValidCount(); i++) {
                NWZC150001_cpoSlsCrPMsg cpoSlsCrPMsg = pMsg.cpoSlsCr.no(i);
                if (ZYPCommonFunc.hasValue(cpoSlsCrPMsg.dsOrdPosnNum) //
                        || ZYPCommonFunc.hasValue(cpoSlsCrPMsg.dsCpoConfigPk)) {
                    continue;
                }
                NWZC150001_cpoSlsCrPMsg addCpoSlsCrPMsg = new NWZC150001_cpoSlsCrPMsg();
                EZDMsg.copy(cpoSlsCrPMsg, null, addCpoSlsCrPMsg, null);
                ZYPEZDItemValueSetter.setValue(addCpoSlsCrPMsg.dsCpoConfigPk, configPMsg.dsCpoConfigPk);
                ZYPEZDItemValueSetter.setValue(addCpoSlsCrPMsg.dsOrdPosnNum, configPMsg.dsOrdPosnNum);
                ZYPEZDItemValueSetter.setValue(addCpoSlsCrPMsg.configCatgCd, configPMsg.configCatgCd);
                addCpoSlsCrPMsg.xxRqstTpCd.setValue(NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                addCpoSlsCrPMsgList.add(addCpoSlsCrPMsg);
            }
        }
        int crIdx = pMsg.cpoSlsCr.getValidCount();
        for (NWZC150001_cpoSlsCrPMsg addCpoSlsCrPMsg : addCpoSlsCrPMsgList) {
            if (crIdx >= pMsg.cpoSlsCr.length()) {
                break;
            }
            EZDMsg.copy(addCpoSlsCrPMsg, null, pMsg.cpoSlsCr.no(crIdx), null);
            crIdx++;
        }
        pMsg.cpoSlsCr.setValidCount(crIdx);
        // 2017/06/30 S21_NA#18811 Add End

        final NWZC152001PMsg slsCrUpdApiMsg = new NWZC152001PMsg();
        ZYPEZDItemValueSetter.setValue(slsCrUpdApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(slsCrUpdApiMsg.cpoOrdNum, pMsg.cpoOrdNum);
        int i = 0;
        for (;i < pMsg.cpoSlsCr.getValidCount(); i++) {
            // 2015/11/20 S21_NA#1006 Add Start
            if (ZYPCommonFunc.hasValue(pMsg.cpoSlsCr.no(i).dsOrdPosnNum)) {
                // BigDecimal dsCpoConfigPk = getDsCpoConfigPkFromPMsg(pMsg, pMsg.cpoSlsCr.no(i).dsOrdPosnNum.getValue()); S21_NA#2189 Mod Parameter
                BigDecimal dsCpoConfigPk = getDsCpoConfigPkFromPMsg(pMsg, pMsg.cpoSlsCr.no(i).dsOrdPosnNum.getValue(), pMsg.cpoSlsCr.no(i).configCatgCd.getValue());
                if (dsCpoConfigPk.compareTo(BigDecimal.valueOf(-1)) > 0) {
                    pMsg.cpoSlsCr.no(i).dsCpoConfigPk.setValue(dsCpoConfigPk);
                }
            }
            // 2015/11/20 S21_NA#1006 Add End
            EZDMsg.copy(pMsg.cpoSlsCr.no(i), "", slsCrUpdApiMsg.salesRepList.no(i), "A");
        }
        slsCrUpdApiMsg.salesRepList.setValidCount(i);
        // debug
//        logger.debug("-- callSlsCrUpdateAPI --");
//        logger.debug("--  Input Parameter:");
//        logger.debug(slsCrUpdApiMsg);
        // debug

        new NWZC152001().execute(slsCrUpdApiMsg, commonBean.getOnBatchType());
        // debug
//        logger.debug("--  Output Parameter:");
//        logger.debug(slsCrUpdApiMsg);
//        logger.debug("-- callSlsCrUpdateAPI End --");
        // debug

        if (!S21ApiUtil.isXxMsgId(slsCrUpdApiMsg)) {
            return false;
        }
        List<String> ml = S21ApiUtil.getXxMsgIdList(slsCrUpdApiMsg);
        for (String msgId : ml) {
            this.msgIdMgr.addXxMsgId(msgId, pMsg);
        }

        for (i = 0; i < slsCrUpdApiMsg.xxMsgIdList.getValidCount(); i++) {
            final String xxMsgId = slsCrUpdApiMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (xxMsgId.endsWith("E")) {
                return true;
            }
        }
        return false;
    }

    private boolean callCpoConfigUpdateAPI(NWZC150001PMsg pMsg) {
        if (pMsg.cpoConfig.getValidCount() == 0) {
            return false;
        }

        // 2018/06/25 QC#20154 Mod Start
        //new NWZC150001CpoConfMain().execute(pMsg, commonBean.getOnBatchType())
        new NWZC150001CpoConfMain().execute(pMsg, commonBean.getOnBatchType(), cpouBean);
        // 2018/06/25 QC#20154 Mod End

        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

            final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();

            if (xxMsgId.endsWith("E")) {
                return true;
            }
        }
        return false;
    }

    // 2017/04/13 S21_NA#Review structure Lv.2 Del, moved to NWZC150001CpouUpdateOrderData.java Start
//    // 2015/12/24 S21_NA#2411 Add Start
//    private boolean callRtrnUpdateAPI(NWZC150001PMsg pMsg, List<NWZC150003PMsg> pMsg3List, CPOTMsg cpoTMsg, boolean isPureReturn, boolean isVersionCheckUpRequired) {
//        return callRtrnUpdateAPI(pMsg, pMsg3List, cpoTMsg, isPureReturn, isVersionCheckUpRequired, NWZC150001Constant.CALL_SEQ_SAVE);
//    }
//    // 2015/12/24 S21_NA#2411 Add End

//    /**
//     * <pre>
//     * 
//     * </pre>
//     * @param pMsg API Parameter
//     * @param pMsg3List return error message
//     * @param cpoTMsg CPO record
//     * @param isPureReturn true: return only order false: Outbound / Inbound mixed order
//     * @param isVersionCheckUpRequired true: set version check false: not
//     * @param callSeq 8 bit mode, 2nd from last means save, last bit means submit.<br>
//     * ex: 0x03 means save and submit, ex: 0x02 only save, 0x01, only submit
//     * @return
//     */
//    private boolean callRtrnUpdateAPI(NWZC150001PMsg pMsg, List<NWZC150003PMsg> pMsg3List, CPOTMsg cpoTMsg, boolean isPureReturn, boolean isVersionCheckUpRequired, char callSeq) {
//        if (pMsg.rtnDtl.getValidCount() == 0) {
//            return false;
//        }
//
//        String cpoOrdTpCd = NWXC150001DsCheck.getCpoOrdTpCdFromDsOrdTp(glblCmpyCd, pMsg.dsOrdTpCd.getValue());
//
//        final NWZC153001PMsg ruPMsg = new NWZC153001PMsg();
//        final List<NWZC153002PMsg> cpoRtnUpdApiOutMsgList = new ArrayList<NWZC153002PMsg>();
//
//        EZDMsg.copy(pMsg, null, ruPMsg, null);
//        ZYPEZDItemValueSetter.setValue(ruPMsg.slsRepTocCd, pMsg.slsRepCd); // 2015/12/17 S21_NA#2007 Add
//        ZYPEZDItemValueSetter.setValue(ruPMsg.cpoOrdTpCd, cpoOrdTpCd);
//
//        int idx = 0;
//        // Copy Return Detail
//        for (; idx < pMsg.rtnDtl.getValidCount(); idx++) {
//            EZDMsg.copy(pMsg.rtnDtl.no(idx), "B1", ruPMsg.ordRtrnDtlList.no(idx), "");
//
//            ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(idx).dsCpoRtrnLineNum, pMsg.rtnDtl.no(idx).cpoDtlLineNum_B1);
//            ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(idx).dsCpoRtrnLineSubNum, pMsg.rtnDtl.no(idx).cpoDtlLineSubNum_B1);
//
//            if (!ZYPCommonFunc.hasValue(ruPMsg.ordRtrnDtlList.no(idx).slsRepOrSlsTeamTocCd)) {
//                ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(idx).slsRepOrSlsTeamTocCd, pMsg.slsRepCd);
//            }
//            ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(idx).pmtTermCashDiscCd, ruPMsg.addPmtTermCashDiscCd);
//            ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(idx).cashDiscTermCd, ruPMsg.addPmtTermCashDiscCd);
//            ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(idx).cpoOrdTpCd, cpoOrdTpCd);
//
//            for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
//                NWZC150001_cpoConfigPMsg configMsg = pMsg.cpoConfig.no(i);
//                if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configMsg.configCatgCd.getValue())) {
//                    if (S21StringUtil.isEquals(ruPMsg.ordRtrnDtlList.no(idx).dsOrdPosnNum.getValue(), configMsg.dsOrdPosnNum.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(idx).dsCpoConfigPk, configMsg.dsCpoConfigPk);
//                    }
//                }
//            }
//        }
//        ruPMsg.ordRtrnDtlList.setValidCount(idx);
//
//        // Copy Return Price Calc List
//        for (idx = 0; idx < pMsg.rtnPriceList.getValidCount(); idx++) {
//            EZDMsg.copy(pMsg.rtnPriceList.no(idx), "", ruPMsg.prcCalcList.no(idx), "");
//            ZYPEZDItemValueSetter.setValue(ruPMsg.prcCalcList.no(idx).cpoRtrnPrcCalcBasePk, pMsg.rtnPriceList.no(idx).ordPrcCalcBasePk);
//            ZYPEZDItemValueSetter.setValue(ruPMsg.prcCalcList.no(idx).dsCpoRtrnLineNum, pMsg.rtnPriceList.no(idx).cpoDtlLineNum);
//            ZYPEZDItemValueSetter.setValue(ruPMsg.prcCalcList.no(idx).dsCpoRtrnLineSubNum, pMsg.rtnPriceList.no(idx).cpoDtlLineSubNum);
//        }
//        ruPMsg.prcCalcList.setValidCount(idx);
//
//        // 2016/01/14 S21_NA#2996 Add Start
//        if (isPureReturn) {
//            ruPMsg.xxScrEdtTpCd.setValue(NWZC153001Constant.SCRN_EDT_TP_PURE);
//        } else {
//            ruPMsg.xxScrEdtTpCd.setValue(NWZC153001Constant.SCRN_EDT_TP_MIXED);
//        }
//        // 2016/01/14 S21_NA#2996 Add End
//        if (isSaveMode(pMsg, cpoTMsg)) {
//            // 2015/12/24 S21_NA#2411 Mod Start
////            ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_SAVE);
//            boolean isSubmit = (callSeq & NWZC150001Constant.CALL_SEQ_SUBMIT) > 0x00;
//            boolean isSave = (callSeq & NWZC150001Constant.CALL_SEQ_SAVE) > 0x00;
//            boolean isCancel = (callSeq & NWZC150001Constant.CALL_SEQ_CANCEL) > 0x00; // 2016/01/14 S21_NA#2996 Add
//            if (isSave) {
//                ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_SAVE);
//                for (int i = 0; i < ruPMsg.ordRtrnDtlList.getValidCount(); i++) {
//                    String xxRqstTpCd = ruPMsg.ordRtrnDtlList.no(i).xxRqstTpCd.getValue();
//                    if (!NWZC153001Constant.RQST_DTL_CANCEL.equals(xxRqstTpCd) && !NWZC153001Constant.RQST_DTL_SAVE.equals(xxRqstTpCd)) {
//                        ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(i).xxRqstTpCd, NWZC153001Constant.RQST_DTL_SAVE);
//                    }
//                }
//            } else if (isSubmit) {
//                ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_NEW);
//            } else if (isCancel) { // 2016/01/14 S21_NA#2996 Add
//                if (isPureReturn) {
//                    ZYPEZDItemValueSetter.setValue(ruPMsg.xxScrEdtTpCd, NWZC153001Constant.SCRN_EDT_TP_PURE);
//                } else {
//                    ZYPEZDItemValueSetter.setValue(ruPMsg.xxScrEdtTpCd, NWZC153001Constant.SCRN_EDT_TP_MIXED);
//                }
//                ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_CANCEL);
//            } else {
//                ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_SAVE);
//            }
//            // 2015/12/24 S21_NA#2411 Mod End
//        }
//        if (ORD_HDR_STS.VALIDATED.equals(cpoTMsg.ordHdrStsCd.getValue())) {
//            if ((callSeq & NWZC150001Constant.CALL_SEQ_CANCEL) > 0x00) {// 2016/01/14 S21_NA#2996 Add Condition
//                ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_CANCEL);
//            } else {
//                ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_MOD);
//            }
//        }
//        // 2016/01/14 S21_NA#2996 Add Start
//        if (ORD_HDR_STS.CANCELLED.equals(cpoTMsg.ordHdrStsCd.getValue())) {
//            ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_CANCEL);
//        }
//        // 2016/01/14 S21_NA#2996 Add End
//        ZYPEZDItemValueSetter.setValue(ruPMsg.carrAcctNum, pMsg.carrAcctNum);
//        ZYPEZDItemValueSetter.setValue(ruPMsg.invRcpntCustCd, ruPMsg.billToCustCd);
//        // 2015/12/16 S21_NA#2007 Del Start
//        // for (int i = 0; i < ruPMsg.ordRtrnDtlList.getValidCount(); i++) {
//        //     NWZC153001_ordRtrnDtlListPMsg orPMsg = ruPMsg.ordRtrnDtlList.no(i);
//        //
//        //     if (!ZYPCommonFunc.hasValue(orPMsg.slsRepOrSlsTeamTocCd)) {
//        //         ZYPEZDItemValueSetter.setValue(orPMsg.slsRepOrSlsTeamTocCd, pMsg.slsRepCd);
//        //     }
//        //     ZYPEZDItemValueSetter.setValue(orPMsg.pmtTermCashDiscCd, ruPMsg.addPmtTermCashDiscCd);
//        //     ZYPEZDItemValueSetter.setValue(orPMsg.cashDiscTermCd, ruPMsg.addPmtTermCashDiscCd);
//        // }
//        // for (int i = 0; i < ruPMsg.prcCalcList.getValidCount(); i++) {
//        //     NWZC153001_prcCalcListPMsg pc153PMsg = ruPMsg.prcCalcList.no(i);
//        //     NWZC150001_rtnPriceListPMsg rp150PMsg = pMsg.rtnPriceList.no(i);
//
//        //      EZDMsg.copy(rp150PMsg, null, pc153PMsg, null);
//        //}
//        // 2015/12/16 S21_NA#2007 Del End
//
//        // S21_NA#1952 start
//        if (isPureReturn & isVersionCheckUpRequired) {
//            // pure return only.
//            // version up required only.
//            ZYPEZDItemValueSetter.setValue(ruPMsg.xxValUpdFlg, pMsg.xxValUpdFlg);
//            // S21_NA#1957 start
//            if (S21StringUtil.isEquals(pMsg.xxValUpdFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
//                ZYPEZDItemValueSetter.setValue(ruPMsg.diChkHldFlg, ZYPConstant.FLG_OFF_N);
//            } else {
//                ZYPEZDItemValueSetter.setValue(ruPMsg.diChkHldFlg, cpoTMsg.diChkHldFlg);
//            }
//            // S21_NA#1957 end
//        } else {
//            ZYPEZDItemValueSetter.setValue(ruPMsg.xxValUpdFlg, ZYPConstant.FLG_OFF_N);
//        }
//        // S21_NA#1952 end
//
//        new NWZC153001().execute(ruPMsg, cpoRtnUpdApiOutMsgList, onBatchType);
//
//        if (S21ApiUtil.isXxMsgId(ruPMsg)) {
//            List<String> ml = S21ApiUtil.getXxMsgIdList(ruPMsg);
//            for (String msgId : ml) {
//                setMsgId(pMsg, msgId);
//            }
//        }
//
//        boolean rtrnDtlErrFlg = false;
//        for (int i = 0; i < cpoRtnUpdApiOutMsgList.size(); i++) {
//            NWZC150003PMsg resPMsg = new NWZC150003PMsg();
//            EZDMsg.copy(cpoRtnUpdApiOutMsgList.get(i), null, resPMsg, null);
//            // 2016/01/08 S21_NA#2899 Add Start
//            ZYPEZDItemValueSetter.setValue(resPMsg.cpoDtlLineNum, cpoRtnUpdApiOutMsgList.get(i).dsCpoRtrnLineNum);
//            ZYPEZDItemValueSetter.setValue(resPMsg.cpoDtlLineSubNum, cpoRtnUpdApiOutMsgList.get(i).dsCpoRtrnLineSubNum);
//
//            NWZC153002PMsg cpoUpdApiOutMsg = cpoRtnUpdApiOutMsgList.get(i);
//            for (int j = 0; j < cpoUpdApiOutMsg.xxMsgIdList.getValidCount(); j++) {
//                final String xxMsgId = cpoUpdApiOutMsg.xxMsgIdList.no(j).xxMsgId.getValue();
//                if (xxMsgId.endsWith("E")) {
//                    rtrnDtlErrFlg = true;
//                }
//            }
//            // 2016/01/08 S21_NA#2899 Add End
//            pMsg3List.add(resPMsg);
//
//        }
//
//        for (int i = 0; i < ruPMsg.xxMsgIdList.getValidCount(); i++) {
//
//            final String xxMsgId = ruPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
//            if (xxMsgId.endsWith("E")) {
//                return true;
//            }
//        }
//        // 2016/01/08 S21_NA#2899 Mod Start
////        for (NWZC153002PMsg cpoUpdApiOutMsg : cpoRtnUpdApiOutMsgList) {
////
////            for (int i = 0; i < cpoUpdApiOutMsg.xxMsgIdList.getValidCount(); i++) {
////
////                final String xxMsgId = cpoUpdApiOutMsg.xxMsgIdList.no(i).xxMsgId.getValue();
////                if (xxMsgId.endsWith("E")) {
////                    return true;
////                }
////            }
////        }
//        if (rtrnDtlErrFlg) {
//            return true;
//        }
//        // 2016/01/08 S21_NA#2899 Mod End
//        // 2015/12/16 S21_NA#2007 Add Start
//        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, ruPMsg.cpoOrdNum);
//        }
//        // 2015/12/16 S21_NA#2007 Add End
//        return false;
//    }
    // 2017/04/13 S21_NA#Review structure Lv.2 Del, moved to NWZC150001CpouUpdateOrderData.java End

    private boolean callCpoUpdateAPI(NWZC150001PMsg pMsg, List<NWZC150002PMsg> resPMsg2List, List<NWZC150003PMsg> resPMsg3List, CPOTMsg cpoTMsg) {
//        if (pMsg.A.getValidCount() == 0) {
//            return false;
//        }

//        NWZC150001CpouBean cpouBean = new NWZC150001CpouBean(pMsg);

        if (isSaveMode(pMsg, cpoTMsg)) {
            cpouBean.setRqstTpCd(NWZC150001CpouConstant.CPO_SAVE);
        }
        if (ORD_HDR_STS.VALIDATED.equals(cpoTMsg.ordHdrStsCd.getValue())) {
            cpouBean.setRqstTpCd(NWZC150001CpouConstant.CPO_MODIFY);
        }

        cpouBean.setCpoOrdTpCd(NWXC150001DsCheck.getCpoOrdTpCdFromDsOrdTp(glblCmpyCd, pMsg.dsOrdTpCd.getValue()));
        cpouBean.setSlsRepTocCd(pMsg.slsRepCd.getValue());
        if (!ZYPCommonFunc.hasValue(cpouBean.getCpoOrdNum())) {
            cpouBean.setOrgRqstDelyDt(pMsg.addRddDt.getValue());
        }
        cpouBean.setInvRcpntCustCd(cpouBean.getBillToCustCd());
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxValUpdFlg.getValue())) {
            cpouBean.setXxValUpdFlg(pMsg.xxValUpdFlg.getValue());
        } else {
            cpouBean.setDiChkHldFlg(cpoTMsg.diChkHldFlg.getValue());
        }

        String usrId = pMsg.usrId.getValue();

        for (NWZC150001CpouDetailBean cpouDtlBean : cpouBean.getDtlBeanList()) {

            cpouDtlBean.setUomCpltFlg(ZYPConstant.FLG_OFF_N);

            if (!ZYPCommonFunc.hasValue(cpouDtlBean.getSlsRepOrSlsTeamTocCd())) {
                cpouDtlBean.setSlsRepOrSlsTeamTocCd(pMsg.slsRepCd.getValue());
            }
            cpouDtlBean.setPmtTermCashDiscCd(cpouBean.getAddPmtTermCashDiscCd());
            cpouDtlBean.setCashDiscTermCd(cpouBean.getAddCashDiscTermCd());
            cpouDtlBean.setFrtChrgToCd(cpouBean.getAddFrtChrgToCd());
            cpouDtlBean.setFrtChrgMethCd(cpouBean.getAddFrtChrgMethCd());
            cpouDtlBean.setShpgSvcLvlCd(cpouBean.getAddShpgSvcLvlCd());
            cpouDtlBean.setCarrAcctNum(cpouBean.getCarrAcctNum());
            if (isSaveMode(pMsg, cpoTMsg)) {
                if (!NWZC150001Constant.RQST_TP_DTL_CANCEL.equals(cpouDtlBean.getDtlRqstTpCd())) {
                    cpouDtlBean.setDtlRqstTpCd(NWZC150001CpouConstant.CPO_DTL_SAVE);
                }
            }

            if (ZYPCommonFunc.hasValue(cpouDtlBean.getSerNum())) {
                if (!ZYPCommonFunc.hasValue(cpouDtlBean.getSvcMachMstrPk())) {
                    BigDecimal svcMachMstrPk = NWZC150001Query.getInstance().getSvcMachMstrPk(glblCmpyCd, cpouDtlBean.getSerNum(), cpouDtlBean.getMdseCd());
                    cpouDtlBean.setSvcMachMstrPk(svcMachMstrPk);
                }
            }

            CPO_DTLTMsg dsCpoDtlMsg = getCurrentDsCpoDtlMsg(cpouBean, cpouDtlBean);

            if (dsCpoDtlMsg == null) {
                cpouDtlBean.setDsCpoDtlCratUsrId(usrId);
                cpouDtlBean.setDsCpoDtlCratTs(this.curTime);
            } else {
                cpouDtlBean.setDsCpoDtlCratUsrId(dsCpoDtlMsg.dsCpoDtlCratUsrId.getValue());
                cpouDtlBean.setDsCpoDtlCratTs(dsCpoDtlMsg.dsCpoDtlCratTs.getValue());
            }
            cpouDtlBean.setDsCpoDtlUpdUsrId(usrId);
            cpouDtlBean.setDsCpoDtlUpdTs(this.curTime);
            cpouDtlBean.setConvProcStsCd(getConvProcStsCd(pMsg, cpouDtlBean));

            for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
                NWZC150001_cpoConfigPMsg configMsg = pMsg.cpoConfig.no(i);
                if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configMsg.configCatgCd.getValue())) {
                    if (S21StringUtil.isEquals(cpouDtlBean.getDsOrdPosnNum(), configMsg.dsOrdPosnNum.getValue())) {
                        cpouDtlBean.setDsCpoConfigPk(configMsg.dsCpoConfigPk.getValue());
                    }
                }
            }
            // Add QC#56636
            if (CPO_ORD_TP.EXPENSE.equals(cpoTMsg.cpoOrdTpCd.getValue())) {
                cpouDtlBean.setCoaCmpyCd(dsCpoDtlMsg.coaCmpyCd.getValue());
                cpouDtlBean.setCoaAfflCd(dsCpoDtlMsg.coaAfflCd.getValue());
                cpouDtlBean.setCoaBrCd(dsCpoDtlMsg.coaBrCd.getValue());
                cpouDtlBean.setCoaChCd(dsCpoDtlMsg.coaChCd.getValue());
                cpouDtlBean.setCoaCcCd(dsCpoDtlMsg.coaCcCd.getValue());
                cpouDtlBean.setCoaAcctCd(dsCpoDtlMsg.coaAcctCd.getValue());
                cpouDtlBean.setCoaProjCd(dsCpoDtlMsg.coaProjCd.getValue());
                cpouDtlBean.setCoaExtnCd(dsCpoDtlMsg.coaExtnCd.getValue());
                cpouDtlBean.setCoaProdCd(dsCpoDtlMsg.coaProdCd.getValue());
            }
        }

        // 2017/04/28 S21_NA#RS#5881 Mod Start
//        if ((pMsg.rtnDtl.getValidCount() > 0)) {
//            cpouBean.setCpoRtrnDtlUpdFlg(ZYPConstant.FLG_ON_Y);
//        } else {
//            cpouBean.setCpoRtrnDtlUpdFlg(ZYPConstant.FLG_OFF_N);
//        }
        cpouBean.setCpoRtrnDtlUpdFlg(ZYPConstant.FLG_OFF_N);
        // 2017/04/28 S21_NA#RS#5881 Mod End

        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
            cpouBean.setDsCpoCratUsrId(usrId);
            cpouBean.setDsCpoCratTs(this.curTime);
        } else {
            cpouBean.setDsCpoCratUsrId(cpoTMsg.dsCpoCratUsrId.getValue());
            cpouBean.setDsCpoCratTs(cpoTMsg.dsCpoCratTs.getValue());
        }
        cpouBean.setDsCpoUpdUsrId(usrId);
        cpouBean.setDsCpoUpdTs(this.curTime);

        boolean isModeSubmit = S21StringUtil.isEquals(MODE_SUBMIT, pMsg.xxModeCd.getValue());
        boolean isValidated = S21StringUtil.isEquals(ORD_HDR_STS.VALIDATED, cpoTMsg.ordHdrStsCd.getValue());
        // 2017/06/13 QC#18820 Mod Start
        boolean isModeSave = false;
        if (S21StringUtil.isEquals(MODE_SAVE, pMsg.xxModeCd.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(cpoTMsg.diChkHldFlg.getValue()) //
                    && isAlreadyBookRequested(pMsg, cpoTMsg)) { // 2017/07/03 S21_NA#19737 Add Condition isAlreadyBookRequested(cpoTMsg)
                isModeSave = true;
            }
        }
        if (isModeSubmit || isValidated || isModeSave) {
        // 2017/06/13 QC#18820 Mod End
            cpouBean.setOrdBookReqUsrId(usrId);
            cpouBean.setOrdBookReqTs(this.curTime);
        }

//        new NWZC150001CpouExecCpoUpd().execute(pMsg, cpouBean, resPMsg2List, onBatchType);
        // 2017/06/13 S21_NA#18869-2 Mod Start
//        NWZC150001CpouUpdateOrderData.getInstance().updateOrderData(pMsg, this.cpouBean, resPMsg2List, resPMsg3List, msgIdMgr, commonBean.getOnBatchType());
        getUpdOrdDataInstance().updateOrderData(pMsg, this.cpouBean, resPMsg2List, resPMsg3List, msgIdMgr, commonBean.getOnBatchType());
        // 2017/06/13 S21_NA#18869-2 Mod End

        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

            final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (xxMsgId.endsWith("E")) {
                return true;
            }
        }

        for (NWZC150002PMsg resPMsg : resPMsg2List) {

            for (int i = 0; i < resPMsg.xxMsgIdList.getValidCount(); i++) {

                final String xxMsgId = resPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (xxMsgId.endsWith("E")) {
                    return true;
                }
            }
        }

        for (NWZC150003PMsg resPMsg : resPMsg3List) {

            for (int i = 0; i < resPMsg.xxMsgIdList.getValidCount(); i++) {

                final String xxMsgId = resPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (xxMsgId.endsWith("E")) {
                    return true;
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum) && ZYPCommonFunc.hasValue(cpouBean.getCpoOrdNum_A1())) {
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, cpouBean.getCpoOrdNum_A1());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdTs, cpouBean.getCpoOrdTs());
        // 2017/04/17 S21_NA#Review structure Lv.1 Del Start
//        editAmountForPrcDtl(pMsg, cpouBean, resPMsg2List);
        // 2017/04/17 S21_NA#Review structure Lv.1 Del End
        return false;
    }

    private boolean isSaveMode(NWZC150001PMsg pMsg, CPOTMsg cpoTMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
            return true;
        }
        return (ORD_HDR_STS.SAVED.equals(cpoTMsg.ordHdrStsCd.getValue()));
    }

    // 2017/05/10 S21_NA#Review structure Lv.2 Del Start
//    private boolean isPureReturn(NWZC150001PMsg pMsg) {
//        // 2016/01/14 S21_NA#2996 delete logic to to new isPureReturn
//        return isPureReturn(pMsg, false);
//    }
//
//    /**
//     * <pre>
//     * Check calling without outbound order.
//     * (add parameter chekCancel for S21_NA#2996
//     * @param pMsg DS CPO Update API Parameter
//     * @param chkCancel true: check all cancel of out bound false: no check all cancel of out bound.
//     * @return
//     * </pre>
//     */
//    private boolean isPureReturn(NWZC150001PMsg pMsg, boolean chkCancel) {
//        int cancelLineCnt = 0;
//        if (pMsg.rtnDtl.getValidCount() == 0) {
//            return false;
//        }
//
//        // 2016/04/19 Add #7263 Start
//        if (ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
//            BigDecimal cpoDtlCnt = NWZC150001Query.getInstance().getCpoDtlCount(pMsg);
//            if (cpoDtlCnt != null && 0 < cpoDtlCnt.compareTo(BigDecimal.ZERO)) {
//                // It is not pure return
//                return false;
//            } else if (0 == pMsg.A.getValidCount()){
//                return true;
//            }
//        }
//        // 2015/12/24 S21_NA#2411 Mod Start
//        // return (pMsg.A.getValidCount() == 0);
//        if (0 == pMsg.A.getValidCount()) {
//            return true;
//        }
//        // 2016/04/19 Add #7263 End
//
//        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
//            String lineRqst = pMsg.A.no(i).xxRqstTpCd_A1.getValue();
//            if (!RQST_TP_DTL_CANCEL.equals(lineRqst)) {
//                return false;
//            } else { // 2016/01/14 S21_NA#2996 Add
//                cancelLineCnt++;
//            }
//        }
//        if (chkCancel) { // 2016/01/14 S21_NA#2996 Add
//            if (cancelLineCnt == pMsg.A.getValidCount()) {
//                return false;
//            } else {
//                return true;
//            }
//        } else {
//            return true;
//        }
//        // 2015/12/24 S21_NA#2411 Mod End
//    }
    // 2017/05/10 S21_NA#Review structure Lv.2 Del End
    private boolean existsCpo(CPOTMsg tMsg) {
        if (tMsg == null) {
            return false;
        }
        if (ORD_HDR_STS.CANCELLED.equals(tMsg.ordHdrStsCd.getValue())) {
            return false;
        }
        if (ORD_HDR_STS.CLOSED.equals(tMsg.ordHdrStsCd.getValue())) {
            return false;
        }
        return true;
    }

    private boolean isErrorExists(NWZC150001PMsg pMsg //
            , List<NWZC150002PMsg> pMsg2List //
            , List<NWZC150003PMsg> pMsg3List) {
        if (msgIdMgr.isXxMsgId()) {
            return true;
        }
        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            String msdId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (msdId.endsWith("E")) {
                return true;
            }
        }
        for (NWZC150002PMsg pMsg2 : pMsg2List) {
            // mod start 2023/04/25 QC#61337
            //if (S21ApiUtil.isXxMsgId(pMsg2)) {
            //    return true;
            //}
            for (int i = 0; i < pMsg2.xxMsgIdList.getValidCount(); i++) {
                String msdId = pMsg2.xxMsgIdList.no(i).xxMsgId.getValue();
                if (msdId.endsWith("E")) {
                    return true;
                }
            }
            // mod end 2023/04/25 QC#61337
        }
        for (NWZC150003PMsg pMsg3 : pMsg3List) {
            // mod start 2023/04/25 QC#61337
            //if (S21ApiUtil.isXxMsgId(pMsg3)) {
            //    return true;
            //}
            for (int i = 0; i < pMsg3.xxMsgIdList.getValidCount(); i++) {
                String msdId = pMsg3.xxMsgIdList.no(i).xxMsgId.getValue();
                if (msdId.endsWith("E")) {
                    return true;
                }
            }
            // mod end 2023/04/25 QC#61337
        }
        return false;
    }

    //    private void execOrderCheck(NWZC150001PMsg pMsg //
    //            , List<NWZC150002PMsg> pMsg2List //
    //            , List<NWZC150003PMsg> pMsg3List) {
    //        execDsCheck(pMsg, pMsg2List, pMsg3List);
    //        //        if (isErrorExists(pMsg, pMsg2List, pMsg3List)) {
    //        //            return;
    //        //        }
    //
    //    }

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * <pre>
//     * @param pMsg2List     pMsg2List
//     * @param aPMsg         aPMsg
//     * @param msgId         msgId
//     * </pre>
//     */
//    public static void addMsgId2List(List<NWZC150002PMsg> pMsg2List, NWZC150001_APMsg aPMsg, String msgId) {
//        NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
//        setMsgId2(pMsg2, msgId);
//        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
//        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
//        pMsg2List.add(pMsg2);
//    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    // 2016/10/13 S21_NA#7924-2 Add Start
//    public static void addMsgId2List(List<NWZC150002PMsg> pMsg2List, String dsOrdPosnNum, String msgId) {
//        NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
//        NWZC150001Common.setMsgId2(pMsg2, msgId);
//        ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, dsOrdPosnNum);
//        pMsg2List.add(pMsg2);
//    }
//    // 2016/10/13 S21_NA#7924-2 Add End
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

//    private void addMsgId2List(List<NWZC150002PMsg> pMsg2List, NWZC150001_APMsg aPMsg, String msgId, String msgParam[]) {
//        NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
//        setMsgId2(pMsg2, msgId, msgParam);
//        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
//        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
//        pMsg2List.add(pMsg2);
//    }
    // 2015/12/01  S21_NA#1006 Add End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    // 2016/10/13 S21_NA#7924-2 Add Start
//    public static void addMsgId3List(List<NWZC150003PMsg> pMsg3List, String dsOrdPosnNum, String msgId) {
//        NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
//        setMsgId3(pMsg3, msgId);
//        ZYPEZDItemValueSetter.setValue(pMsg3.dsOrdPosnNum, dsOrdPosnNum);
//        pMsg3List.add(pMsg3);
//    }
//    // 2016/10/13 S21_NA#7924-2 Add End
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    /*
     * del 20151113 private void otherCsmpCheck(// NWZC150001PMsg pMsg
     * // , List<NWZC150002PMsg> pMsg2List // , List<NWZC150003PMsg>
     * pMsg3List) { if (!ZYPCommonFunc.hasValue(pMsg.csmpContrNum) //
     * && !ZYPCommonFunc.hasValue(pMsg.dlrRefNum)) { return; } String
     * defPrcCatgCd =
     * NWZC150001Query.getInstance().getDefPrcCatgCd(glblCmpyCd,
     * pMsg.dsOrdTpCd.getValue(), pMsg.slsDt.getValue());
     * NWZC157001PMsg prcApiPMsg // = callPricingApiForGetPrcList(//
     * pMsg // , NWZC157001.GET_PRICE_LIST // , PRC_CTX_TP.CSMP_CREDIT
     * // , ZYPConstant.FLG_ON_Y); if
     * (S21ApiUtil.isXxMsgId(prcApiPMsg)) { List<String> ml =
     * S21ApiUtil.getXxMsgIdList(prcApiPMsg); for (String msgId : ml)
     * { setMsgId(pMsg, msgId); } for (int i = 0; i <
     * prcApiPMsg.xxMsgIdList.getValidCount(); i++) {
     * EZDDebugOutput.println(1, // S21MessageFunc.clspGetMessage(//
     * prcApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue() // , new
     * String[] {prcApiPMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue()
     * // , prcApiPMsg.xxMsgIdList.no(i).xxMsgPrmTxt_1.getValue() // ,
     * prcApiPMsg.xxMsgIdList.no(i).xxMsgPrmTxt_2.getValue() // }),
     * this); } return; } List<String> csmpPrcList =
     * getCsmpPrcList(prcApiPMsg); for (int i = 0; i <
     * pMsg.A.getValidCount(); i++) { NWZC150002PMsg pMsg2 = new
     * NWZC150002PMsg(); NWZC150001_APMsg aPMsg = pMsg.A.no(i); if
     * (NWXC150001DsCheck.checkOtherCsmpRelation(defPrcCatgCd,
     * aPMsg.csmpPrcListCd_A1.getValue(), csmpPrcList)) {
     * setMsgId2(pMsg2, NWZM1490E);
     * ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum,
     * aPMsg.cpoDtlLineNum_A1);
     * ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum,
     * aPMsg.cpoDtlLineSubNum_A1); pMsg2List.add(pMsg2); } } for (int
     * i = 0; i < pMsg.rtnDtl.getValidCount(); i++) { NWZC150003PMsg
     * pMsg3 = new NWZC150003PMsg(); NWZC150001_rtnDtlPMsg rtnDtlPMsg
     * = pMsg.rtnDtl.no(i); if
     * (NWXC150001DsCheck.checkOtherCsmpRelation(defPrcCatgCd,
     * rtnDtlPMsg.csmpPrcListCd_B1.getValue(), csmpPrcList)) {
     * setMsgId3(pMsg3, NWZM1490E);
     * ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum,
     * rtnDtlPMsg.cpoDtlLineNum_B1);
     * ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum,
     * rtnDtlPMsg.cpoDtlLineSubNum_B1); pMsg3List.add(pMsg3); } } }
     * private List<String> getCsmpPrcList(NWZC157001PMsg prcApiPMsg)
     * { List<String> prcList = new ArrayList<String>(); for (int i =
     * 0; i < prcApiPMsg.xxPrcList.getValidCount(); i++) {
     * prcList.add(prcApiPMsg.xxPrcList.no(i).prcCatgCd.getValue()); }
     * return prcList; }
     */

    /** 2016/08/01 S21_NA#12637 Del Start
**    private NWZC150001_cpoConfigPMsg getConfigPMsg(NWZC150001_APMsg aPMsg, NWZC150001_cpoConfigPMsgArray configPMsgArray) {
**        for (int i = 0; i < configPMsgArray.getValidCount(); i++) {
**            NWZC150001_cpoConfigPMsg configPMsg = configPMsgArray.no(i);
**            if (aPMsg.dsOrdPosnNum_A1.getValue().equals(configPMsg.dsOrdPosnNum.getValue())) {
**                return configPMsg;
**            }
**        }
**        return new NWZC150001_cpoConfigPMsg();
**    }
**
**    private NWZC150001_cpoConfigPMsg getConfigPMsgForRtrn(NWZC150001_rtnDtlPMsg rtnDtlPMsg, NWZC150001_cpoConfigPMsgArray configPMsgArray) {
**        for (int i = 0; i < configPMsgArray.getValidCount(); i++) {
**            NWZC150001_cpoConfigPMsg configPMsg = configPMsgArray.no(i);
**            if (rtnDtlPMsg.dsOrdPosnNum_B1.getValue().equals(configPMsg.dsOrdPosnNum.getValue())
**                    && CONFIG_CATG.INBOUND.equals(configPMsg.configCatgCd.getValue())) {
**                return configPMsg;
**            }
**        }
**        return new NWZC150001_cpoConfigPMsg();
**    }
    2016/08/01 S21_NA#12637 Del End **/
    //
    //    private BigDecimal getConfigMstrPk(NWZC150001_APMsg aPMsg, NWZC150001_cpoConfigPMsgArray configPMsgArray) {
    //        for (int i = 0; i < configPMsgArray.getValidCount(); i++) {
    //            NWZC150001_cpoConfigPMsg configPMsg = configPMsgArray.no(i);
    //            if (aPMsg.dsOrdPosnNum_A1.getValue().equals(configPMsg.dsOrdPosnNum.getValue())) {
    //                return configPMsg.svcConfigMstrPk.getValue();
    //            }
    //        }
    //        return null;
    //    }
    //
    //    private String getConfigTp(NWZC150001_APMsg aPMsg, NWZC150001_cpoConfigPMsgArray configPMsgArray) {
    //        for (int i = 0; i < configPMsgArray.getValidCount(); i++) {
    //            NWZC150001_cpoConfigPMsg configPMsg = configPMsgArray.no(i);
    //            if (aPMsg.dsOrdPosnNum_A1.getValue().equals(configPMsg.dsOrdPosnNum.getValue())) {
    //                return configPMsg.configTpCd.getValue();
    //            }
    //        }
    //        return null;
    //    }

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    public static String getCoaBrCd(String slsRepCd, NWZC150001CpouLocalCache cache) {
//        TOCTMsg tMsg = new TOCTMsg();
//
//        // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
////        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
////        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, slsRepCd);
////
////        tMsg = (TOCTMsg) S21CacheTBLAccessor.findByKey(tMsg);
//        tMsg = cache.tocCache.getTMsgByKey(glblCmpyCd, slsRepCd);
//        // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
//
//        if (tMsg == null) {
//            return "";
//        }
//        return tMsg.coaBrCd.getValue();
//    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    //    private String getSlsRepRoleTpCd(String lineBizTpCd) {
    //        //TODO QA#285
    //        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
    //            return LINE_BIZ_ROLE_TP.ESS_WRITER;
    //        }
    //        if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
    //            return LINE_BIZ_ROLE_TP.LFS_WRITER;
    //        }
    //        if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
    //            return LINE_BIZ_ROLE_TP.PPS_WRITER;
    //        }
    //        return null;
    //    }

//    private void deriveDsCpoLineNumForModRtrnProc(String cpoOrdNum, String dsOrdPosnNum, NWZC150001_rtnDtlPMsgArray aPMsgAry) {
//        for (int i = 0; i < aPMsgAry.getValidCount(); i++) {
//            NWZC150001_rtnDtlPMsg aPMsg = aPMsgAry.no(i);
//            if (!dsOrdPosnNum.equals(aPMsg.dsOrdPosnNum_B1.getValue())) {
//                continue;
//            }
//            if (RQST_TP_DTL_NEW.equals(aPMsg.xxRqstTpCd_B1.getValue())) {
//                ZYPEZDItemValueSetter.setValue(aPMsg.dsCpoLineNum_B1 //
//                        , NWXC150001DsCheck.getNewDsCpoLineNum(glblCmpyCd, cpoOrdNum, dsOrdPosnNum, CONFIG_CATG.INBOUND));
//            }
//        }
//    }

//    private void deriveDsCpoLineNumForModProc(String cpoOrdNum, String dsOrdPosnNum, NWZC150001_APMsgArray aPMsgAry) {
//        for (int i = 0; i < aPMsgAry.getValidCount(); i++) {
//            NWZC150001_APMsg aPMsg = aPMsgAry.no(i);
//            if (!dsOrdPosnNum.equals(aPMsg.dsOrdPosnNum_A1.getValue())) {
//                continue;
//            }
//            if (RQST_TP_DTL_NEW.equals(aPMsg.xxRqstTpCd_A1.getValue())) {
//                ZYPEZDItemValueSetter.setValue(aPMsg.dsCpoLineNum_A1 //
//                        , NWXC150001DsCheck.getNewDsCpoLineNum(glblCmpyCd, cpoOrdNum, dsOrdPosnNum, CONFIG_CATG.OUTBOUND));
//            }
//        }
//    }

    /** 2016/08/01 S21_NA#12637 Delete this method  Start.
//    private void setDefaultConfigId(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List) { This method was replaced by setDefaultConfigIdByEveryConfig()
//
//        // 2016/07/22 S21_NA#11970 Add Start
////        if (!NWXC150001DsCheck.isMachMstrCrat(glblCmpyCd, pMsg.dsOrdTpCd.getValue())) {
////            return;
////        }
//        // 2016/07/22 S21_NA#11970 Add End
//
//        String dsOrdPosnNum = "";
//        // int ixCinfig = 0; 2016/06/02 S21_NA#8464 Del
//        // S21_NA#2595
//        BigDecimal svcConfigMstrPk = null;
//        BigDecimal pickConfigPk = null;
//
//        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
//            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
////            if (!dsOrdPosnNum.equals(aPMsg.dsOrdPosnNum_A1.getValue())) { 2016/07/22 S21_NA#11970 Add Start
//            if (!dsOrdPosnNum.equals(aPMsg.dsOrdPosnNum_A1.getValue()) //
//                    && isMachMstrCratByConfig(pMsg, aPMsg.dsOrdPosnNum_A1.getValue(), i)) {
//                dsOrdPosnNum = aPMsg.dsOrdPosnNum_A1.getValue();
//                // NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(ixCinfig++); 2016/06/02 S21_NA#8464 Del
//                NWZC150001_cpoConfigPMsg configPMsg = getOutBoundConfigPMsg(dsOrdPosnNum, pMsg); // 2016/06/02 S21_NA#8464 Add 
//                String xxRqstTpCd = configPMsg.xxRqstTpCd.getValue(); // 2016/06/02 S21_NA#8464 Add Start
//                boolean isNewConfig = NWZC150001Constant.RQST_TP_CONFIG_NEW.equals(xxRqstTpCd); // 2016/06/02 S21_NA#8464 Add Start
//                boolean isModConfig = NWZC150001Constant.RQST_TP_CONFIG_MODIFY.equals(xxRqstTpCd); // 2016/06/02 S21_NA#8464 Add Start
//
//                CONFIG_TPTMsg tMsg = new CONFIG_TPTMsg();
//                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//                // S21_NA#2024
//                // ZYPEZDItemValueSetter.setValue(tMsg.configTpCd,
//                // pMsg.cpoConfig.no(i).configTpCd);
//                ZYPEZDItemValueSetter.setValue(tMsg.configTpCd, configPMsg.configTpCd);
//
//                svcConfigMstrPk = null;
//                pickConfigPk = null;
//                tMsg = (CONFIG_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
//                if (tMsg != null) {
//
//                    if (ZYPConstant.FLG_ON_Y.equals(tMsg.pickConfigPkReqFlg.getValue())) { // S21_NA#2595
//                        pickConfigPk = configPMsg.svcConfigMstrPk.getValue();
//                        ZYPEZDItemValueSetter.setValue(configPMsg.pickSvcConfigMstrPk, pickConfigPk);
//                    }
//                    if (ZYPConstant.FLG_ON_Y.equals(tMsg.configPkAsgFlg.getValue())) {
//                        // 2016/02/02 S21_NA#3970 Mod Start
//                        // svcConfigMstrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_SQ);
//                        // ZYPEZDItemValueSetter.setValue(configPMsg.svcConfigMstrPk, svcConfigMstrPk);
////                        if (NWZC150001Query.getInstance().isExistOrdCatg(pMsg, ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET) 2016/04/28 S21_NA#7516 Mod Condition
//                        if (isExistOrdCatgFromCache(pMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS)) { // For Performance QC#8166 Mod
//                                // && !ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)  2016/06/02 S21_NA#8464 Del
//                            if (isNewConfig) { // 2016/06/02 S21_NA#8464 Add Condition.
//                                svcConfigMstrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_SQ);
//                                ZYPEZDItemValueSetter.setValue(configPMsg.svcConfigMstrPk, svcConfigMstrPk);
//                            } else if (isModConfig) {
//                                if (ZYPCommonFunc.hasValue(configPMsg.dsCpoConfigPk)) {
//                                    DS_CPO_CONFIGTMsg dsCpoConfigTMsg = getDsCpoConfigTMsg(pMsg.glblCmpyCd.getValue(), configPMsg.dsCpoConfigPk.getValue());
//                                    if (null != dsCpoConfigTMsg) {
//                                        svcConfigMstrPk = dsCpoConfigTMsg.svcConfigMstrPk.getValue();
//                                    }
//                                }
//                                if (null == svcConfigMstrPk) {
//                                    svcConfigMstrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_SQ);
//                                }
//                                ZYPEZDItemValueSetter.setValue(configPMsg.svcConfigMstrPk, svcConfigMstrPk);
//                            } // 2016/06/02 S21_NA#8464 Add Condition.
//                        }
//                        // 2016/02/02 S21_NA#3970 Mod End
//                    }
//                }
//            }
//            // if (BigDecimal.ZERO.compareTo(svcConfigMstrPk) != 0) {
//            // ZYPEZDItemValueSetter.setValue(aPMsg.svcConfigMstrPk_A1,
//            // svcConfigMstrPk);
//            // if (BigDecimal.ZERO.compareTo(pickConfigPk) != 0) {
//            // ZYPEZDItemValueSetter.setValue(aPMsg.pickSvcConfigMstrPk_A1,
//            // pickConfigPk);
//            // }
//            // }
//            // S21_NA#2595
//            if (svcConfigMstrPk != null) {
//                ZYPEZDItemValueSetter.setValue(aPMsg.svcConfigMstrPk_A1, svcConfigMstrPk);
//            }
//            if (pickConfigPk != null) {
//                ZYPEZDItemValueSetter.setValue(aPMsg.pickSvcConfigMstrPk_A1, pickConfigPk);
//            }
//        }
//    }
    2016/08/01 S21_NA#12637 Delete this method End **/

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    // For Performance QC#8166 Add Start
//    public static boolean isExistOrdCatgFromCache(NWZC150001PMsg pMsg, String ordCatgCtxTpCd) {
//
//        // Mapkey
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        params.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
//        params.put("dsOrdCatgCd", pMsg.dsOrdCatgCd.getValue());
//        params.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
//        params.put("dsOrdRsnCd", pMsg.dsOrdRsnCd.getValue());
//
//        if (ordCatgCacheMap.containsKey(params)) {
//            return ordCatgCacheMap.get(params);
//        }
//
//        boolean isExistOrdCatg = NWZC150001Query.getInstance().isExistOrdCatg(pMsg, ordCatgCtxTpCd);
//        ordCatgCacheMap.put(params, isExistOrdCatg);
//
//        return isExistOrdCatg;
//    }
//    // For Performance QC#8166 Add End
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    //    private void getDefaultShppingCarrierServiceLevel(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List) {
    //        if (!NWXC150001DsCheck.isEzPackOrder(glblCmpyCd, pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue())) {
    //            return;
    //        }
    //        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
    //            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
    //            ZYPEZDItemValueSetter.setValue(aPMsg.shpgSvcLvlCd_A1, SHPG_SVC_LVL.PREPAID);
    //            ZYPEZDItemValueSetter.setValue(aPMsg.carrSvcLvlCd_A1, CARR_SVC_LVL.GROUND);
    //        }
    //    }

    //    private void setDefaultCarrierServiceLevel(NWZC150001PMsg pMsg) {
    //        if (ZYPCommonFunc.hasValue(pMsg.carrSvcLvlCd)) {
    //            return;
    //        }
    //
    //        String defaultCarrSvcLvlCd = NWZC150001Query.getInstance().getDefaultCarrSvcLvl(pMsg);
    //        if (ZYPCommonFunc.hasValue(defaultCarrSvcLvlCd)) {
    //            ZYPEZDItemValueSetter.setValue(pMsg.carrSvcLvlCd, defaultCarrSvcLvlCd);
    //            return;
    //        }
    //        setMsgId(pMsg, NWZM1449E);
    //
    //    }

    //    private List<String> getSetLineNumList(NWZC150001_APMsgArray pMsgAry) {
    //        List<String> list = new ArrayList<String>();
    //        for (int i = 0; i < pMsgAry.getValidCount(); i++) {
    //            NWZC150001_APMsg pMsg = pMsgAry.no(i);
    //            if (SET_LINE_SUB_NUM.equals(pMsg.cpoDtlLineSubNum_A1.getValue())) {
    //                list.add(pMsg.cpoDtlLineNum_A1.getValue());
    //            }
    //        }
    //        return list;
    //    }

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * <pre>
//     * Set the message id.
//     * @param pMsg Input parameters.
//     * @param val setting value for Message ID
//     * </pre>
//     */
//    public static void setMsgId(NWZC150001PMsg pMsg, String val) {
//        msgIdMgr.addXxMsgId(val, pMsg);
//    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    public static boolean isXxMsgId() {
//        return msgIdMgr.isXxMsgId();
//    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * <pre>
//     * Set the message id.
//     * @param pMsg2 Input parameters.
//     * @param val setting value for Message ID
//     * </pre>
//     */
//    public static void setMsgId2(NWZC150002PMsg pMsg2, String val) {
//        int ix = pMsg2.xxMsgIdList.getValidCount();
//        if (ix >= pMsg2.xxMsgIdList.length()) {
//            return;
//        }
//        ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgId, val);
//        pMsg2.xxMsgIdList.setValidCount(ix + 1);
//    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * <pre>
//     * Set the message id.
//     * @param pMsg3 Input parameters.
//     * @param val setting value for Message ID
//     * </pre>
//     */
//    public static void setMsgId3(NWZC150003PMsg pMsg3, String val) {
//        int ix = pMsg3.xxMsgIdList.getValidCount();
//        if (ix >= pMsg3.xxMsgIdList.length()) {
//            return;
//        }
//        ZYPEZDItemValueSetter.setValue(pMsg3.xxMsgIdList.no(ix).xxMsgId, val);
//        pMsg3.xxMsgIdList.setValidCount(ix + 1);
//    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2015/11/20 S21_NA#1006 Add Start
    // private BigDecimal getDsCpoConfigPkFromPMsg(NWZC150001PMsg pMsg, String posnNum) { 2015/12/21 S21_NA#2189 add param configCatgCd
    private BigDecimal getDsCpoConfigPkFromPMsg(NWZC150001PMsg pMsg, String posnNum, String configCatgCd) {
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            if (posnNum.equals(pMsg.cpoConfig.no(i).dsOrdPosnNum.getValue()) && configCatgCd.equals(pMsg.cpoConfig.no(i).configCatgCd.getValue())) {
                return pMsg.cpoConfig.no(i).dsCpoConfigPk.getValue();
            }
        }
        return BigDecimal.valueOf(-1);
    }
    // 2015/11/20 S21_NA#1006 Add End

    // 2017/05/10 S21_NA#Review structure Lv.2 Del Start
//    // S21_NA#4078 Add Start
//    private BigDecimal getSvcConfigMstrPkFromPMsg(NWZC150001PMsg pMsg, String cpoDtlLineNum, String cpoDtlLineSubNum) {
//        
//        for (int i = 0; i < pMsg.A.getValidCount(); i++ ) {
//            if (cpoDtlLineNum.equals(pMsg.A.no(i).cpoDtlLineNum_A1.getValue()) && cpoDtlLineSubNum.equals(pMsg.A.no(i).cpoDtlLineSubNum_A1.getValue())) {
//                return pMsg.A.no(i).svcConfigMstrPk_A1.getValue();
//            }
//        }
//        return BigDecimal.valueOf(-1);
//    }
//    // S21_NA#4078 Add End
    // 2017/05/10 S21_NA#Review structure Lv.2 Del End

    // 2016/01/06 S21_NA#2588 Add Start
//    private boolean callRtrnUpdateAPIForCalcHdrAmt(NWZC150001PMsg pMsg) {
//        NWZC153001PMsg rtrnUpdApiPMsg = new NWZC153001PMsg();
//        ZYPEZDItemValueSetter.setValue(rtrnUpdApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(rtrnUpdApiPMsg.cpoOrdNum, pMsg.cpoOrdNum);
//        rtrnUpdApiPMsg.xxRqstTpCd.setValue(NWZC153001Constant.RQST_HDR_UPD_HDR_AMT);
//
//        List<NWZC153002PMsg> linePMsgList = new ArrayList<NWZC153002PMsg>();
//        new NWZC153001().execute(rtrnUpdApiPMsg, linePMsgList, onBatchType);
//
//        boolean errFlg = false;
//        if (S21ApiUtil.isXxMsgId(rtrnUpdApiPMsg)) {
//            List<String> ml = S21ApiUtil.getXxMsgIdList(rtrnUpdApiPMsg);
//            for (String msgId : ml) {
//                setMsgId(pMsg, msgId);
//                if (msgId.endsWith("E")) {
//                    errFlg = true;
//                }
//            }
//        }
//
//        return errFlg;
//    }
    // 2016/01/06 S21_NS#2588 Add End

    // 2016/04/15 S21_NA#5321-2 Del Start
    // 2016/04/15 S21_NA#5321 Add Start
//    private SVC_CONFIG_MSTRTMsg getSvcConfigMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
//
//        SVC_CONFIG_MSTRTMsg tMsg = new SVC_CONFIG_MSTRTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, svcConfigMstrPk);
//
//        return (SVC_CONFIG_MSTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
//    }
    // 2016/04/15 S21_NA#5321 Add End
    // 2016/04/15 S21_NA#5321-2 Del End

    // 2016/06/02 S21_NA#9273 Modify Start
    private boolean registAlloc(NWZC150001PMsg pMsg, List<NWZC150002PMsg> resPMsg2List) {

        // 2018/05/20 S21_NA#25604 Add Start
        boolean isSuplliesOrder = NWXC150001DsCheck.isAvalOrderCtxType(pMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET, pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdRsnCd.getValue());
        // 2018/05/20 S21_NA#25604 Add End
        // For Performance QC#8166 Mod Start
        // if (NWZC150001Query.getInstance().isExistOrdCatg(pMsg, ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET)) { // S21_NA#11737
//        if (NWZC150001Common.isExistOrdCatgFromCache(pMsg, ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET, commonBean)) { 2018/05/20 S21_NA#25604 Mod Condition
        if (isSuplliesOrder) { // 2018/05/20 S21_NA#25604 Mod Condition
        // For Performance QC#8166 Mod End
            return true;
        }

        for (int j = 0; j < pMsg.A.getValidCount(); j++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(j);

            // 2017/11/21 S21_NA#22708 Add Start
            if (ZYPCommonFunc.hasValue(aPMsg.crRebilCd_A1)) {
                continue;
            }
            // 2017/11/21 S21_NA#22708 Add End
            if (!ZYPCommonFunc.hasValue(aPMsg.serNum_A1) && !ZYPCommonFunc.hasValue(aPMsg.svcMachMstrPk_A1)) {
                continue;
            }

            Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(//
                    glblCmpyCd //
                    , aPMsg.serNum_A1.getValue() //
                    // 2017/02/22 QC#16575 ADD START
                    , aPMsg.mdseCd_A1.getValue() //
                    // 2017/02/22 QC#16575 ADD E N D
                    , aPMsg.svcMachMstrPk_A1.getValue());

            if (map == null) {
                continue;
            }

            String svcMachMstrStsCd = (String) map.get("SVC_MACH_MSTR_STS_CD");

            if (!SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd)//
                    && !SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(svcMachMstrStsCd)) {
                continue;
            }

            String svcMachMaintAvalFlg = (String) map.get("ALLC_FLG");

            if (RQST_TP_DTL_CANCEL.equals(aPMsg.xxRqstTpCd_A1.getValue())) {
                if (!ZYPConstant.FLG_OFF_N.equals(svcMachMaintAvalFlg)) {
                    continue;
                }
                if (!cancelAllocForSvcMachMstr(pMsg, aPMsg, resPMsg2List)) {
                    return false;
                }
            } else {
                if (!ZYPConstant.FLG_ON_Y.equals(svcMachMaintAvalFlg)) {
                    continue;
                }
                if (!registAllocForSvcMachMstr(pMsg, aPMsg, resPMsg2List)) {
                    return false;
                }
            }
        }
        return true;
    }
    // 2016/06/02 S21_NA#9273 Modify End

    private boolean registAllocForSvcMachMstr(NWZC150001PMsg pMsg, NWZC150001_APMsg aPMsg, List<NWZC150002PMsg> resPMsg2List) {
        NSZC001001PMsg machMstrUpdMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.slsDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachMstrPk, aPMsg.svcMachMstrPk_A1.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxHdrNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxLineNum, aPMsg.cpoDtlLineNum_A1);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxLineSubNum, aPMsg.cpoDtlLineSubNum_A1);

        NSZC001001 machMstrUpdApi = new NSZC001001();
        machMstrUpdApi.execute(machMstrUpdMsg, commonBean.getOnBatchType());

        boolean isApiResultSuccess = true;
        if (machMstrUpdMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < machMstrUpdMsg.xxMsgIdList.getValidCount(); i++) {
                NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                NWZC150001Common.setMsgId2(pMsg2, machMstrUpdMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, aPMsg.dsOrdPosnNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                resPMsg2List.add(pMsg2);

                isApiResultSuccess = false;
            }
        }
        return isApiResultSuccess;
    }

    private boolean cancelAllocForSvcMachMstr(NWZC150001PMsg pMsg, NWZC150001_APMsg aPMsg, List<NWZC150002PMsg> resPMsg2List) {
        NSZC001001PMsg machMstrUpdMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.slsDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachMstrPk, aPMsg.svcMachMstrPk_A1.getValue());

        NSZC001001 machMstrUpdApi = new NSZC001001();
        machMstrUpdApi.execute(machMstrUpdMsg, commonBean.getOnBatchType());

        boolean isApiResultSuccess = true;
        if (machMstrUpdMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < machMstrUpdMsg.xxMsgIdList.getValidCount(); i++) {
                NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                NWZC150001Common.setMsgId2(pMsg2, machMstrUpdMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, aPMsg.dsOrdPosnNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                resPMsg2List.add(pMsg2);

                isApiResultSuccess = false;
            }
        }
        return isApiResultSuccess;
    }

    private static void writePerformanceProfilingLogStart(Class clazz, String methodNm) {
        // S21_NA#12895 Del Start
        // final StringBuilder sb = new StringBuilder();
        // sb.append("[@@@@@]#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(methodNm).append(" - [START] ").append(System.currentTimeMillis());
        // System.out.println(sb.toString());
        // S21_NA#12895 Del End
    }

    private static void writePerformanceProfilingLogEnd(Class clazz, String methodNm) {
        // S21_NA#12895 Del Start
        // final StringBuilder sb = new StringBuilder();
        // sb.append("[@@@@@]#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(methodNm).append(" - [E N D] ").append(System.currentTimeMillis());
        // System.out.println(sb.toString());
        // S21_NA#12895 Del End
    }

    // 2016/06/09 S21_NA#9426 Add Start
    private boolean crCardAuth(NWZC150001PMsg pMsg) {

//        boolean rslt = false; 2016/12/26 S21_NA#16321 Del
        if (!S21StringUtil.isEquals(DS_PMT_METH.CREDIT_CARD, pMsg.dsPmtMethCd.getValue())) {
            return false;
        }

        // 2016/07/12 S21_NA#9426-4 Add Start
        if (isAlreadyPartialInvoiced(pMsg)) {
            return false;
        }
        // 2016/07/12 S21_NA#9426-4 Add End
        // 2016/07/12 S21_NA#9426-4 Add Start
        if (this.isAlreadyAuthComp) {
            return false;
        }
        // 2016/07/12 S21_NA#9426-4 Add End

        // 2016/07/12 S21_NA#9426-4 Add Start
        List<CR_CARD_TRXTMsg> crCardTrxTMsgList = getAutorizedCrCardTrx(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue());

        // 2016/12/26 S21_NA#16321 Add Start
        if (crCardTrxTMsgList != null && !crCardTrxTMsgList.isEmpty()) {
            // If there are autorized Credit Card data, no action (QC S21_NA#16321)
            return false;
        }
        // 2016/12/26 S21_NA#16321 Add Start

        // 2016/12/26 S21_NA#16321 Del Start
//        DS_CPO_VTMsg dsCpoVTMsg = getDsCpoVRecord(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue());
//        if (null != dsCpoVTMsg) {
//            BigDecimal ordAmt = dsCpoVTMsg.ordTotDealSubTotAmt.getValue();
//            if (null == ordAmt) {
//                ordAmt = BigDecimal.ZERO;
//            }
//
//            // get CR_CARD_TRX record
//            boolean isAlreadyCrCardAuth = false;
//            if (crCardTrxTMsgList != null && !crCardTrxTMsgList.isEmpty()) {
//                for (CR_CARD_TRXTMsg crCardTrxTMsg : crCardTrxTMsgList) {
//                    if (ordAmt.compareTo(crCardTrxTMsg.crCardAuthAmt.getValue()) == 0) {
//                        isAlreadyCrCardAuth = true;
//                        break;
//                    }
//                }
//                if (isAlreadyCrCardAuth) {
//                    return false;
//                }
//            }
//        }
//        // 2016/07/12 S21_NA#9426-4 Add End
        // 2016/12/26 S21_NA#16321 Del End

        DS_CR_CARDTMsg dsCrCardTMsg = new DS_CR_CARDTMsg();
        ZYPEZDItemValueSetter.setValue(dsCrCardTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCrCardTMsg.dsCrCardPk, pMsg.dsCrCardPk);

        dsCrCardTMsg = (DS_CR_CARDTMsg) S21CacheTBLAccessor.findByKey(dsCrCardTMsg);

        if (null == dsCrCardTMsg) {
            // No Credit Card Info, Error
            this.msgIdMgr.addXxMsgId(NWZM1946E, pMsg);
            return true;
        }

        // 2016/12/26 S21_NA#16321 Del Start
//        // get CR_CARD_TRX record
//        // 2016/07/12 S21_NA#9426-4 Del Start
////        List<CR_CARD_TRXTMsg> crCardTrxTMsgList = getAutorizedCrCardTrx(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue());
//        // 2016/07/12 S21_NA#9426-4 Del End
//        if (!crCardTrxTMsgList.isEmpty()) {
//            // Void Mode
//            rslt = voidAuthCompletedCrCardTrx(pMsg, crCardTrxTMsgList);
//        }
//
//        if (rslt) {
//            return rslt;
//        }
        // 2016/12/26 S21_NA#16321 Del End
        // Auto Credit Card
        return crCardAuth(pMsg, dsCrCardTMsg);
    }

    private List<CR_CARD_TRXTMsg> getAutorizedCrCardTrx(String glblCmpyCd, String cpoOrdNum) {

        CR_CARD_TRXTMsg crCardTrxTMsg = new CR_CARD_TRXTMsg();
        //QC#12154 mod Start
//        crCardTrxTMsg.setSQLID("001");
        crCardTrxTMsg.setSQLID("002");
        //QC#12154 mod End
        crCardTrxTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        crCardTrxTMsg.setConditionValue("crCardAuthStsCd01", CR_CARD_AUTH_STS.AUTHORIZED_COMPLETED);
        crCardTrxTMsg.setConditionValue("firstTrxInfoTxt01", cpoOrdNum);
        crCardTrxTMsg.setConditionValue("crCardTrxTpCd01", CR_CARD_TRX_TP.CPO);    //QC#12154 add

        CR_CARD_TRXTMsgArray dbRslt = (CR_CARD_TRXTMsgArray) EZDTBLAccessor.findByCondition(crCardTrxTMsg);
        List<CR_CARD_TRXTMsg> rsltList = new ArrayList<CR_CARD_TRXTMsg>(0);
        for (int i = 0; i < dbRslt.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(dbRslt.no(i).crCardAuthRefNum)) {
                CR_CARD_TRXTMsg rslt = new CR_CARD_TRXTMsg();
                EZDMsg.copy(dbRslt.no(i), null, rslt, null);
                rsltList.add(rslt);
            }
        }
        return rsltList;
    }

    private boolean voidAuthCompletedCrCardTrx(NWZC150001PMsg pMsg) {

        // 2016/07/12 S21_NA#9426-4 Add Start
        if (this.isAlreadyVoidCrCardTrx) {
            return false;
        }
        // 2016/07/12 S21_NA#9426-4 Add End
        List<CR_CARD_TRXTMsg> crCardTrxTMsgList = getAutorizedCrCardTrx(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue());
        if (crCardTrxTMsgList.isEmpty()) {
            // 2016/07/12 S21_NA#9426-4 Add Start
            this.isAlreadyVoidCrCardTrx = true;
            // 2016/07/12 S21_NA#9426-4 Add End
            return false;
        }
        return voidAuthCompletedCrCardTrx(pMsg, crCardTrxTMsgList);
    }
    private boolean voidAuthCompletedCrCardTrx(NWZC150001PMsg pMsg, List<CR_CARD_TRXTMsg> crCardTrxTMsgList) {

        boolean rslt = false;
        for (CR_CARD_TRXTMsg crCardTrxTMsg : crCardTrxTMsgList) {
            rslt = voidAuthCompletedCrCardTrx(pMsg, crCardTrxTMsg);
            if (rslt) {
                break;
            }
        }
        // 2016/07/12 S21_NA#9426-4 Add Start
        if (!rslt) {
            this.isAlreadyVoidCrCardTrx = true;
        }
        // 2016/07/12 S21_NA#9426-4 Add End
        return rslt;
    }

    private boolean voidAuthCompletedCrCardTrx(NWZC150001PMsg pMsg, CR_CARD_TRXTMsg crCardTrxTMsg) {

        NWZC203001PMsg crValidPMsg = new NWZC203001PMsg();
        ZYPEZDItemValueSetter.setValue(crValidPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        crValidPMsg.xxModeCd.setValue(NWZC203001Constant.PROC_MODE_VOID);
        ZYPEZDItemValueSetter.setValue(crValidPMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(crValidPMsg.crCardAuthRefNum, crCardTrxTMsg.crCardAuthRefNum);
        ZYPEZDItemValueSetter.setValue(crValidPMsg.xxPmtcOrdId, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(crValidPMsg.xxPmtcTrxRefIdxCd, crCardTrxTMsg.crCardRefIdxNum);
        ZYPEZDItemValueSetter.setValue(crValidPMsg.crCardTrxPk, crCardTrxTMsg.crCardTrxPk);
        // 2018/07/10 S21_NA#25797 Add Start
        ZYPEZDItemValueSetter.setValue(crValidPMsg.crCardAuthAmt, crCardTrxTMsg.crCardAuthAmt);
        // 2018/07/10 S21_NA#25797 Add End

        // Call Credit Card Validation API
        new NWZC203001().execute(crValidPMsg, commonBean.getOnBatchType());

        // 2016/12/26 S21_NA#16321 Mod Start
        return false;
//        // 2016/07/19 S21_NA#11866 Mod Start
////        boolean isProcSuccess = PMTC_PROC_STS_SUCCESS.equals(crValidPMsg.xxPmtcProcStsCd.getValue());
////        if (isProcSuccess) {
////            return false;
////        } else {
////            for (int i = 0; i < crValidPMsg.xxMsgIdList.getValidCount(); i++) {
////                String msgId = crValidPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
////                if (msgId.endsWith("E")) {
////                    setMsgId(pMsg, msgId);
////                    break;
////                }
////            }
////        }
////        return true;
//        if (isCrCardApiError(pMsg, crValidPMsg)) {
//            return true;
//        } else {
//            return false;
//        }
//        // 2016/07/19 S21_NA#11866 Mod End
        // 2016/12/26 S21_NA#16321 Mod End
    }

    private boolean crCardAuth(NWZC150001PMsg pMsg, DS_CR_CARDTMsg dsCrCardTMsg) {

        CPO_VTMsg dsCpoVTMsg = getDsCpoVRecord(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue());
        if (null == dsCpoVTMsg) {
            this.msgIdMgr.addXxMsgId(NWZM0073E, pMsg);
            return true;
        }

        NWZC203001PMsg crValidPMsg = new NWZC203001PMsg();
        ZYPEZDItemValueSetter.setValue(crValidPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        crValidPMsg.xxModeCd.setValue(NWZC203001Constant.PROC_MODE_GET_AUTH);
        ZYPEZDItemValueSetter.setValue(crValidPMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(crValidPMsg.crCardCustRefNum, dsCrCardTMsg.crCardCustRefNum);
        ZYPEZDItemValueSetter.setValue(crValidPMsg.sellToCustCd, pMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(crValidPMsg.crCardAuthAmt, dsCpoVTMsg.ordTotDealSubTotAmt);
        ZYPEZDItemValueSetter.setValue(crValidPMsg.xxPmtcPrflOrdOvrdCd, NWZC203001Constant.PMTC_PRFL_ORD_OVRD_CD_NO);
        ZYPEZDItemValueSetter.setValue(crValidPMsg.xxPmtcOrdId, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(crValidPMsg.crCardTrxTpCd, CR_CARD_TRX_TP.CPO); // 2016/07/01 S21_NA#9426-2 Add
        ZYPEZDItemValueSetter.setValue(crValidPMsg.firstTrxInfoTxt, pMsg.cpoOrdNum); // 2016/07/01 S21_NA#9426-2 Add

        // Call Credit Card Validation API
        new NWZC203001().execute(crValidPMsg, commonBean.getOnBatchType());

        // 2016/07/19 S21_NA#11866 Mod Start
//        boolean isProcSuccess = PMTC_PROC_STS_SUCCESS.equals(crValidPMsg.xxPmtcProcStsCd.getValue());
//        boolean isApproved = PMTC_APVL_STS_APPLOVED.equals(crValidPMsg.xxPmtcApvlStsNum.getValue());
//        if (isProcSuccess && isApproved) {
//            this.isAlreadyAuthComp = true; // 2016/07/12 S21_NA#9426-4 Add
//            return false;
//        } else {
//            for (int i = 0; i < crValidPMsg.xxMsgIdList.getValidCount(); i++) {
//                String msgId = crValidPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
//                if (msgId.endsWith("E")) {
//                    setMsgId(pMsg, msgId);
//                    break;
//                }
//            }
//        }
//        return true;
        if (isCrCardApiError(pMsg, crValidPMsg)) {
            return true;
        } else {
            this.isAlreadyAuthComp = true;
            return false;
        }
        // 2016/07/19 S21_NA#11866 Mod End
    }

    // 2016/07/19 S21_NA#11866 Add Start
    private boolean isCrCardApiError(NWZC150001PMsg pMsg, NWZC203001PMsg crValidPMsg) {

        boolean isCrCardApiError = false;
        boolean isProcSuccess = S21StringUtil.isEquals(PMTC_PROC_STS_SUCCESS, crValidPMsg.xxPmtcProcStsCd.getValue());
        boolean isEmptyApproved = !ZYPCommonFunc.hasValue(crValidPMsg.xxPmtcApvlStsNum.getValue());
        boolean isApproved = S21StringUtil.isEquals(PMTC_APVL_STS_APPLOVED, crValidPMsg.xxPmtcApvlStsNum.getValue());

        boolean isSuccess = false;
        if (isEmptyApproved && isProcSuccess) {
            isSuccess = true;
        } else if (!isEmptyApproved && isProcSuccess && isApproved) {
            isSuccess = true;
        }

        if (isSuccess) {
            for (int i = 0; i < crValidPMsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = crValidPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (msgId.endsWith("E")) {
                    isCrCardApiError = true;
                    this.msgIdMgr.addXxMsgId(msgId, pMsg);
                }
            }
        } else {
            // int msgCnt = 0; 2016/07/26 S21_NA#11866-2 Del
            for (int i = 0; i < crValidPMsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = crValidPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (msgId.endsWith("E")) {
                    // 2016/07/26 S21_NA#11866-2 Mod Start
//                    msgCnt++;
//                    isCrCardApiError = true;
//                    if (this.crCardMsgList != null) {
//                        this.crCardMsgList.add(msgId);
//                    }
                    isCrCardApiError = true;
                    this.msgIdMgr.addXxMsgId(msgId, pMsg);
                    // 2016/07/26 S21_NA#11866-2 Mod End
                }
            }
            // 2016/07/26 S21_NA#11866-2 Mod Start
//            setMsgId(pMsg, NWZM1950W);
//            if (msgCnt > 0) {
//                isCrCardApiError = true;
//            }
            if (!isCrCardApiError) {
                this.msgIdMgr.addXxMsgId(NWZM1950E, pMsg);
            }
            // 2016/07/26 S21_NA#11866-2 Mod End
        }
        return isCrCardApiError;
    }
    // 2016/07/19 S21_NA#11866 Add End

    private CPO_VTMsg getDsCpoVRecord(String glblCmpyCd, String cpoOrdNum) {

        CPO_VTMsg dsCpoVTMsg = new CPO_VTMsg();
        dsCpoVTMsg.setSQLID("501");
        dsCpoVTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsCpoVTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);

        CPO_VTMsgArray dsCpoVTMsgArray = (CPO_VTMsgArray) EZDTBLAccessor.findByCondition(dsCpoVTMsg);

        if (null == dsCpoVTMsgArray || dsCpoVTMsgArray.getValidCount() == 0) {
            return null;
        }
        return dsCpoVTMsgArray.no(0);
    }
    // 2016/06/09 S21_NA#9426 Add End
    // 2016/07/12 S21_NA#9426-4 Add Start
    private boolean isAlreadyPartialInvoiced(NWZC150001PMsg pMsg) {

        boolean isAlreadyPartialInvoiced = false;
        BigDecimal invoicedCount = NWZC150001Query.getInstance().getShpgPlnCountByStatus(pMsg, SHPG_STS.INVOICED);
        if (invoicedCount == null) {
            invoicedCount = BigDecimal.ZERO;
        }
        if (invoicedCount.compareTo(BigDecimal.ZERO) > 0) {
            isAlreadyPartialInvoiced = true;
        }
        return isAlreadyPartialInvoiced;
    }
    // 2016/07/12 S21_NA#9426-4 Add End

    // 2016/07/21 S21_NA#9228 Add Start
    private boolean isPayMethodCreditCardAndAmountMinusError(NWZC150001PMsg pMsg, boolean isSave) {

        if (!S21StringUtil.isEquals(DS_PMT_METH.CREDIT_CARD, pMsg.dsPmtMethCd.getValue())) {
            return false;
        }
        // Calculation Outbound Amount
        Map<String, Object> ordTotDealNetAmtMap = NWZC150001Query.getInstance().getOrdTotDealNetAmt(pMsg);
        boolean isPayMethodCreditCardAndAmountMinusError = false;
        if (ordTotDealNetAmtMap != null) {
            BigDecimal ordTotDealNetAmt = (BigDecimal) ordTotDealNetAmtMap.get("ORD_TOT_DEAL_NET_AMT");
            if (BigDecimal.ZERO.compareTo(ordTotDealNetAmt) > 0) {
                isPayMethodCreditCardAndAmountMinusError = true;

                String ordHdrStsCd = (String) ordTotDealNetAmtMap.get("ORD_HDR_STS_CD");
                if (S21StringUtil.isEquals(ORD_HDR_STS.VALIDATED, ordHdrStsCd)) {
                    this.msgIdMgr.addXxMsgId(NWZM1952E, pMsg);
                } else {
                    if (isSave) {
                        this.msgIdMgr.addXxMsgId(NWZM1951W, pMsg);
                        isPayMethodCreditCardAndAmountMinusError = false;
                    } else {
                        this.msgIdMgr.addXxMsgId(NWZM1952E, pMsg);
                    }
                }
            }

        }

        return isPayMethodCreditCardAndAmountMinusError;
    }
    // 2016/07/21 S21_NA#9228 Add End
    
    // 2023/12/06 QC#61281 K.Ikeda START 
    private boolean isPayMethodNotCreditCardAndAmountNotZeroError(final NWZC150001PMsg pMsg, final boolean isSave) {

        if (!S21StringUtil.isEquals(DS_PMT_METH.NO_CREDIT_CARD, pMsg.dsPmtMethCd.getValue())) {
            return false;
        }

        // Calculation Outbound Amount
        final Map<String, Object> ordTotDealNetAmtMap = NWZC150001Query.getInstance().getOrdTotDealNetAmt(pMsg);
        boolean isPayMethodNotCreditCardAndAmountNotZeroError = false;
        
        if (ordTotDealNetAmtMap == null) {
            return isPayMethodNotCreditCardAndAmountNotZeroError;
        }
        
        final BigDecimal ordTotDealNetAmt = (BigDecimal) ordTotDealNetAmtMap.get("ORD_TOT_DEAL_NET_AMT");
        if (BigDecimal.ZERO.compareTo(ordTotDealNetAmt) < 0) {
            isPayMethodNotCreditCardAndAmountNotZeroError = true;

            final String ordHdrStsCd = (String) ordTotDealNetAmtMap.get("ORD_HDR_STS_CD");
            if (S21StringUtil.isEquals(ORD_HDR_STS.VALIDATED, ordHdrStsCd)) {
                this.msgIdMgr.addXxMsgId(NWZM2324E, pMsg);
            } else {
                if (isSave) {
                    this.msgIdMgr.addXxMsgId(NWZM2325W, pMsg);
                    isPayMethodNotCreditCardAndAmountNotZeroError = false;
                } else {
                    this.msgIdMgr.addXxMsgId(NWZM2324E, pMsg);
                }
            }
        }
        
        return isPayMethodNotCreditCardAndAmountNotZeroError;
    }
    // 2023/12/06 QC#61281 K.Ikeda END
    
    // 2016/07/21 S21_NA#9228 Add End
    /** 2016/08/01 S21_NA#12637 Del Start
**    private boolean isMachMstrCratByConfig(NWZC150001PMsg pMsg, String dsOrdPosnNum, int i) { this method was replaced by isMachMstrCratByConfig(NWZC150001PMsg pMsg, String dsOrdPosnNum)
**
**        for (int idx = i; idx < pMsg.A.getValidCount(); idx++) {
**            if (S21StringUtil.isEquals(dsOrdPosnNum, pMsg.A.no(idx).dsOrdPosnNum_A1.getValue())) {
**                DS_MDSE_INFOTMsg dsMdseInfoMsg = this.dsMdseInfoList.get(idx);
**                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, dsMdseInfoMsg.instlBaseCtrlFlg.getValue())) {
**                    return true;
**                }
**            }
**        }
**        return false;
**    }
     2016/08/01 S21_NA#12637 Del Start **/
    // 2016/07/22 S21_NA#11970 Add End

    // 2017/04/17 S21_NA#Review structure Lv.1 Del Start, move to cpou.derive.NWZC150001CpouEditAmout.java
    // QC#9694 2016/08/01 Mod Start
//    private void editAmountForPrcDtl(NWZC150001PMsg pMsg, NWZC150001CpouBean cpouBean,  List<NWZC150002PMsg> pMsg2List) {
//
//        String cpoOrdNum = cpouBean.getCpoOrdNum();
//        if (!ZYPCommonFunc.hasValue(cpoOrdNum)) {
//            cpoOrdNum = cpouBean.getCpoOrdNum_A1();
//        }
//
//        NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
//        List<NWZC150001_priceListPMsg> prcList = new ArrayList<NWZC150001_priceListPMsg>();
//        BigDecimal entCpoTotDealSlsAmt = BigDecimal.ZERO;
//        BigDecimal entCpoTotDealNetAmt = BigDecimal.ZERO;
//        BigDecimal entCpoTotFuncSlsAmt = BigDecimal.ZERO;
//        BigDecimal entCpoTotFuncNetAmt = BigDecimal.ZERO;
//        BigDecimal cpoTotDealSlsAmt = BigDecimal.ZERO;
//        BigDecimal cpoTotDealNetAmt = BigDecimal.ZERO;
//        BigDecimal cpoTotFuncSlsAmt = BigDecimal.ZERO;
//        BigDecimal cpoTotFuncNetAmt = BigDecimal.ZERO;
//        BigDecimal grsAmt = BigDecimal.ZERO;
//        BigDecimal netAmt = BigDecimal.ZERO;
//
//        for (NWZC150001CpouDetailBean cpouDtlBean : cpouBean.getDtlBeanList()) {
//
//            grsAmt = BigDecimal.ZERO;
//            netAmt = BigDecimal.ZERO;
//            prcList = getLinePriceList(cpouBean.getPriceList(), cpouDtlBean.getCpoDtlLineNum(), cpouDtlBean.getCpoDtlLineSubNum());
//            for(NWZC150001_priceListPMsg calcBase : prcList){
//                if(ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManDelFlg.getValue())){
//                    continue;
//                }
//                if(PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())){
//                    grsAmt = grsAmt.add(calcBase.calcPrcAmtRate.getValue());
//                    netAmt = netAmt.add(calcBase.calcPrcAmtRate.getValue());
//                }
//                if(PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue())){
//                    netAmt = netAmt.subtract(calcBase.calcPrcAmtRate.getValue());
//                }
//                if(PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(calcBase.prcDtlGrpCd.getValue())){
//                    netAmt = netAmt.subtract(calcBase.calcPrcAmtRate.getValue());
//                }
//            }
//            
//            CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, cpoOrdNum); // 2016/08/10 cpoOrdNum -> cpoOrdNum_A1
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, cpouDtlBean.getCpoDtlLineNum());
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, cpouDtlBean.getCpoDtlLineSubNum());
//
//            cpoDtlTMsg = (CPO_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cpoDtlTMsg);
//            if (cpoDtlTMsg == null) {
//                setMsgId2(pMsg2, NWZM0074E);
//                ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, cpouDtlBean.getDsOrdPosnNum());
//                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, cpouDtlBean.getCpoDtlLineNum());
//                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, cpouDtlBean.getCpoDtlLineSubNum());
//                pMsg2List.add(pMsg2);
//                return;
//            }
//
//            NWXC001001UnitPriceData unitPrcData = callNWXC001001EditPriceAmount(cpouBean, cpouDtlBean, grsAmt, netAmt, pMsg2List);
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.entCpoDtlDealNetAmt, unitPrcData.getDealNetUnitPrcAmt());
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.entCpoDtlDealSlsAmt, unitPrcData.getDealGrsUnitPrcAmt());
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.entCpoDtlFuncNetAmt, unitPrcData.getFuncNetUnitPrcAmt());
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.entCpoDtlFuncSlsAmt, unitPrcData.getFuncGrsUnitPrcAmt());
//
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlDealNetAmt, unitPrcData.getDealNetUnitPrcAmt());
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlDealSlsAmt, unitPrcData.getDealGrsUnitPrcAmt());
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlFuncNetAmt, unitPrcData.getFuncNetUnitPrcAmt());
//            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlFuncSlsAmt, unitPrcData.getFuncGrsUnitPrcAmt());
//
//            S21ApiTBLAccessor.update(cpoDtlTMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoDtlTMsg.getReturnCode())) {
//                setMsgId2(pMsg2, NWZM0081E);
//                ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, cpouDtlBean.getDsOrdPosnNum());
//                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, cpouDtlBean.getCpoDtlLineNum());
//                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, cpouDtlBean.getCpoDtlLineSubNum());
//                return;
//            }
//
//            entCpoTotDealSlsAmt = entCpoTotDealSlsAmt.add(cpoDtlTMsg.entCpoDtlDealSlsAmt.getValue());
//            entCpoTotDealNetAmt = entCpoTotDealNetAmt.add(cpoDtlTMsg.entCpoDtlDealNetAmt.getValue());
//            entCpoTotFuncSlsAmt = entCpoTotFuncSlsAmt.add(cpoDtlTMsg.entCpoDtlFuncSlsAmt.getValue());
//            entCpoTotFuncNetAmt = entCpoTotFuncNetAmt.add(cpoDtlTMsg.entCpoDtlFuncNetAmt.getValue());
//            cpoTotDealSlsAmt = cpoTotDealSlsAmt.add(cpoDtlTMsg.cpoDtlDealSlsAmt.getValue());
//            cpoTotDealNetAmt = cpoTotDealNetAmt.add(cpoDtlTMsg.cpoDtlDealNetAmt.getValue());
//            cpoTotFuncSlsAmt = cpoTotFuncSlsAmt.add(cpoDtlTMsg.cpoDtlFuncNetAmt.getValue());
//            cpoTotFuncNetAmt = cpoTotFuncNetAmt.add(cpoDtlTMsg.cpoDtlFuncSlsAmt.getValue());
//        }
//        CPOTMsg cpoTMsg = new CPOTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);
//        cpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cpoTMsg);
//        if (!existsCpo(cpoTMsg)) {
//            setMsgId(pMsg, NWZM0073E);
//        }
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealSlsAmt, entCpoTotDealSlsAmt);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealNetAmt, entCpoTotDealNetAmt);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncSlsAmt, entCpoTotFuncSlsAmt);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncNetAmt, entCpoTotFuncNetAmt);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealSlsAmt, cpoTotDealSlsAmt);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealNetAmt, cpoTotDealNetAmt);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncSlsAmt, cpoTotFuncSlsAmt);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncNetAmt, cpoTotFuncNetAmt);
//
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealDiscAmt, entCpoTotDealNetAmt.subtract(entCpoTotDealSlsAmt));
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncDiscAmt, entCpoTotFuncNetAmt.subtract(entCpoTotFuncSlsAmt));
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealDiscAmt, cpoTotDealNetAmt.subtract(cpoTotDealSlsAmt));
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncDiscAmt, cpoTotFuncNetAmt.subtract(cpoTotFuncSlsAmt));
//        S21ApiTBLAccessor.update(cpoTMsg);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoTMsg.getReturnCode())) {
//            setMsgId(pMsg, NWZM1368E);
//            return;
//        }
//    }
//
//    private List<NWZC150001_priceListPMsg> getLinePriceList( NWZC150001_priceListPMsgArray calcBaseList, String cpoDtlLineNum, String cpoDtlLineSubNum){
//        List<NWZC150001_priceListPMsg> rtrnList = new ArrayList<NWZC150001_priceListPMsg>();
//        NWZC150001_priceListPMsg calcBase = null;
//        for (int i = 0; i < calcBaseList.getValidCount(); i++) {
//            calcBase =  calcBaseList.no(i);
//            if(S21StringUtil.isEquals(calcBase.cpoDtlLineNum.getValue(), cpoDtlLineNum) 
//                    && S21StringUtil.isEquals(calcBase.cpoDtlLineSubNum.getValue(), cpoDtlLineSubNum)){
//                rtrnList.add(calcBase);
//            }
//        }
//        return rtrnList;
//    }
//
//    private NWXC001001UnitPriceData callNWXC001001EditPriceAmount(NWZC150001CpouBean cpouBean, NWZC150001CpouDetailBean cpouDtlBean, BigDecimal grsPrice, BigDecimal netPrice, List<NWZC150002PMsg> pMsg2List) {
//
//        NWXC001001EditPriceAmountInfo editPrcAmtInfo = new NWXC001001EditPriceAmountInfo();
//
//        editPrcAmtInfo.setGlblCmpyCd(cpouBean.getGlblCmpyCd());
//        editPrcAmtInfo.setMdseCd(cpouDtlBean.getMdseCd());
//        editPrcAmtInfo.setSlsDt(cpouBean.getSlsDt());
//        editPrcAmtInfo.setCcyCd(cpouDtlBean.getCcyCd());
//        editPrcAmtInfo.setDealGrsPrcAmt(grsPrice);
//        editPrcAmtInfo.setDealNetPrcAmt(netPrice);
//
//        editPrcAmtInfo = NWXC001001EditPriceAmount.getCmpsnPriceList(editPrcAmtInfo);
//
//        for (String errorID : editPrcAmtInfo.getXxMsgIdList()) {
//            NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
//            setMsgId2(pMsg2, errorID);
//            ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, cpouDtlBean.getDsOrdPosnNum());
//            ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, cpouDtlBean.getCpoDtlLineNum());
//            ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, cpouDtlBean.getCpoDtlLineSubNum());
//            pMsg2List.add(pMsg2);
//        }
//
//        return editPrcAmtInfo.getUnitPrcData();
//    }
    // QC#9694 2016/08/01 Mod End}
    // 2017/04/17 S21_NA#Review structure Lv.1 Del End, move to cpou.derive.NWZC150001CpouEditAmout.java

    // 2016/08/10 S21_NA#5394 Add Start
    private boolean isAlreadyStartedWorkFlow(NWZC150001PMsg pMsg) {

        BigDecimal wfTrgetCount = NWZC150001Query.getInstance().getWfTargetCount(pMsg);
        if (wfTrgetCount == null) {
            wfTrgetCount = BigDecimal.ZERO;
        }
        if (BigDecimal.ZERO.compareTo(wfTrgetCount) < 0) {
            return true;
        } else {
            return false;
        }
    }
    // 2016/08/10 S21_NA#5394 Add End

    // S21_NA#11630 ADD START
    private boolean isProcCancelCreditRebill(NWZC150001PMsg pMsg) {

        // Mod Start 2017/10/20 QC#21773
        //if (!CPO_SRC_TP.CREDIT_AND_REBILL_ENTRY.equals(pMsg.cpoSrcTpCd.getValue())) {
        if (!CPO_SRC_TP.CREDIT.equals(pMsg.cpoSrcTpCd.getValue()) // 
                && !CPO_SRC_TP.REBILL.equals(pMsg.cpoSrcTpCd.getValue())) {
            // Mod End 2017/10/20 QC#21773
            return false;

        } else if (!S21StringUtil.isEquals(MODE_CANCEL, pMsg.xxModeCd.getValue())) {
            return false;

        } else if (!ZYPCommonFunc.hasValue(pMsg.reBillPairCpoOrdNum)) {
            return false;

        }

        //2019/06/03 QC#50555 Add Start
        CPOTMsg cpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        cpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKey(cpoTMsg);
        if (CPO_SRC_TP.REBILL.equals(pMsg.cpoSrcTpCd.getValue()) && !ORD_HDR_STS.CANCELLED.equals((cpoTMsg.ordHdrStsCd.getValue()))){
            return false;
        }
        //2019/06/03 QC#50555 Add End

        return true;
    }

    private boolean hasError(NWZC150001PMsg pMsg, List<NWZC150002PMsg> resPMsg2List, List<NWZC150003PMsg> resPMsg3List) {

        if (0 < pMsg.xxMsgIdList.getValidCount()) {
            List<String> ml = S21ApiUtil.getXxMsgIdList(pMsg);
            for (String msgId : ml) {
                if (msgId.endsWith("E")) {
                    return true;
                }
            }
        }

        for (NWZC150002PMsg resPMsg2 : resPMsg2List) {
            if (0 < resPMsg2.xxMsgIdList.getValidCount()) {
                List<String> ml = S21ApiUtil.getXxMsgIdList(resPMsg2);
                for (String msgId : ml) {
                    if (msgId.endsWith("E")) {
                        return true;
                    }
                }
            }
        }

        for (NWZC150003PMsg resPMsg3 : resPMsg3List) {
            if (0 < resPMsg3.xxMsgIdList.getValidCount()) {
                List<String> ml = S21ApiUtil.getXxMsgIdList(resPMsg3);
                for (String msgId : ml) {
                    if (msgId.endsWith("E")) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void extraCancelCreditRebillPairOrder(NWZC150001PMsg pMsg) {

        NWZC150001PMsg pairCpoPMsg = new NWZC150001PMsg();

        CPOTMsg cpoTMsg = new CPOTMsg();
        if (chkParamForExtraCancelCreditRebillPairOrder(pMsg, cpoTMsg)) {
            // Has error(s)
            return;
        }

        setPairCpoPMsgForCancel(pMsg, pairCpoPMsg, cpoTMsg);
        // QC#22369
        NWZC150001CpouBean uBean = new NWZC150001CpouBean(pairCpoPMsg);
        if (S21StringUtil.isEquals(MODE_VALID, this.origXxModeCd)) {
            uBean.setOnlyValidationFlg(ZYPConstant.FLG_ON_Y);
        }
        setDefaultBeanData(pairCpoPMsg, uBean, cpoTMsg);
        for (NWZC150001CpouDetailBean uDtlBean : uBean.getDtlBeanList()) {
            uDtlBean.setOrdLineStsCd(ORD_LINE_STS.CANCELLED);
        }

        List<NWZC150002PMsg> resPMsg2List = new ArrayList<NWZC150002PMsg>();
        List<NWZC150003PMsg> resPMsg3List = new ArrayList<NWZC150003PMsg>();
        // 2019/02/08 S21_NA#30270 Add Start
        NWZC150001CpouEditAmount.getInstance().editAmountForWDS(uBean, resPMsg2List, pairCpoPMsg, this.localCache, msgIdMgr);
        if (NWZC150001Common.hasError(resPMsg2List, msgIdMgr)) {
            return;
        }
        // 2019/02/08 S21_NA#30270 Add End
        execCancelMode(pairCpoPMsg, resPMsg2List, resPMsg3List, cpoTMsg, uBean); // QC#22369

        if (hasError(pairCpoPMsg, resPMsg2List, resPMsg3List)) {
            this.msgIdMgr.addXxMsgId(NWZM1997E, pMsg);
        }
    }

    private boolean chkParamForExtraCancelCreditRebillPairOrder(NWZC150001PMsg pMsg, CPOTMsg cpoTMsg) {

        boolean errFlg = false;
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            this.msgIdMgr.addXxMsgId(NWZM0011E, pMsg);
            return true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            this.msgIdMgr.addXxMsgId(NWZM0346E, pMsg);
            return true;
        }
        // Get Credit/Rebill Pair CPO
        CPOTMsg cpoTMsgRs = new CPOTMsg();
        
        ZYPEZDItemValueSetter.setValue(cpoTMsgRs.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoTMsgRs.cpoOrdNum, pMsg.reBillPairCpoOrdNum);
        cpoTMsgRs = (CPOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cpoTMsgRs);
        EZDMsg.copy(cpoTMsgRs, null, cpoTMsg, null);
        if (cpoTMsgRs == null) {
            this.msgIdMgr.addXxMsgId(NWZM1994E, pMsg);
            errFlg = true;
        }
        // 2017/11/07 S21_NA#22351 Del Start
//        if (ORD_HDR_STS.CANCELLED.equals(cpoTMsgRs.ordHdrStsCd.getValue())) {
//            this.msgIdMgr.addXxMsgId(NWZM1995E, pMsg);
//            errFlg = true;
//        }
        // 2017/11/07 S21_NA#22351 Del End
        if (ORD_HDR_STS.CLOSED.equals(cpoTMsgRs.ordHdrStsCd.getValue())) {
            this.msgIdMgr.addXxMsgId(NWZM1996E, pMsg);
            errFlg = true;
        }

        if (!S21StringUtil.isEquals(MODE_CANCEL, pMsg.xxModeCd.getValue())) {
            this.msgIdMgr.addXxMsgId(NWZM0977E, pMsg);
            errFlg = true;
        }

        // Validation for pair CPO
        //// Status check
        BigDecimal count = NWZC150001Query.getInstance().isEnableToCancelForOutbound(pMsg);
        if (count != null && 0 < count.compareTo(BigDecimal.ZERO)) {
            this.msgIdMgr.addXxMsgId(NWZM1992E, pMsg);
            errFlg = true;
        }
        count = NWZC150001Query.getInstance().isEnableToCancelForInbound(pMsg);
        if (count != null && 0 < count.compareTo(BigDecimal.ZERO)) {
            this.msgIdMgr.addXxMsgId(NWZM1993E, pMsg);
            errFlg = true;
        }

        return errFlg;
    }

    
    private void setPairCpoPMsgForCancel(NWZC150001PMsg pMsg, NWZC150001PMsg pairCpoPMsg, CPOTMsg cpoTMsg) {

        EZDMsg.copy(cpoTMsg, null, pairCpoPMsg, null);
        ZYPEZDItemValueSetter.setValue(pairCpoPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pairCpoPMsg.xxModeCd, pMsg.xxModeCd);
        ZYPEZDItemValueSetter.setValue(pairCpoPMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pairCpoPMsg.cpoOrdNum, pMsg.reBillPairCpoOrdNum);

        // Ountbound
        CPO_DTLTMsg cpoDtl = new CPO_DTLTMsg();
        cpoDtl.setSQLID("018");
        cpoDtl.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        cpoDtl.setConditionValue("cpoOrdNum01", pMsg.reBillPairCpoOrdNum.getValue());
        cpoDtl.setConditionValue("ordLineStsCd01", ORD_LINE_STS.CANCELLED);

        CPO_DTLTMsgArray cpoDtlMsgArray = (CPO_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(cpoDtl);

        for (int i = 0; i < cpoDtlMsgArray.getValidCount(); i++) {
            CPO_DTLTMsg cpoDtlTMsg =  cpoDtlMsgArray.no(i);
            NWZC150001_APMsg aPMsg = pairCpoPMsg.A.no(i);
            EZDMsg.copy(cpoDtlTMsg, "", aPMsg, "A1");

            ZYPEZDItemValueSetter.setValue(aPMsg.xxRqstTpCd_A1, RQST_TP_DTL_CANCEL);
            pairCpoPMsg.A.setValidCount(i + 1);
        }

        // Inbound
        DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtl = new DS_CPO_RTRN_DTLTMsg();
        dsCpoRtrnDtl.setSQLID("002"); // Create XML
        dsCpoRtrnDtl.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        dsCpoRtrnDtl.setConditionValue("cpoOrdNum01", pMsg.reBillPairCpoOrdNum.getValue());
        dsCpoRtrnDtl.setConditionValue("rtrnLineStsCd01", RTRN_LINE_STS.CANCELLED);

        DS_CPO_RTRN_DTLTMsgArray dsCpoRtrnDtlMsgArray = (DS_CPO_RTRN_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsCpoRtrnDtl);

        for (int i = 0; i < dsCpoRtrnDtlMsgArray.getValidCount(); i++) {
            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg =  dsCpoRtrnDtlMsgArray.no(i);
            NWZC150001_rtnDtlPMsg rtrnPMsg = pairCpoPMsg.rtnDtl.no(i);
            EZDMsg.copy(dsCpoRtrnDtlTMsg, "", rtrnPMsg, "B1");
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.xxRqstTpCd_B1, RQST_TP_RTRN_DTL_CANCEL);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.cpoDtlLineNum_B1, dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.cpoDtlLineSubNum_B1, dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum);
            pairCpoPMsg.rtnDtl.setValidCount(i + 1);
        }
    }
    // S21_NA#11630 ADD END

    // 2016/09/05 S21_NA#6100 Add Start
    private CPO_DTLTMsg getCurrentDsCpoDtlMsg(NWZC150001CpouBean cpouBean, NWZC150001CpouDetailBean cpouDtlBean) {

        CPO_DTLTMsg dsCpoDtlTMsg = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.glblCmpyCd, cpouBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.cpoOrdNum, cpouBean.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.cpoDtlLineNum, cpouDtlBean.getCpoDtlLineNum());
        ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.cpoDtlLineSubNum, cpouDtlBean.getCpoDtlLineSubNum());

        return (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(dsCpoDtlTMsg);
    }
    // 2016/09/05 S21_NA#6100 Add End


    // 2016/09/29 S21_NA#14867 Add Start
    private boolean isCpoUpdApiOutMsgError(List<NWZC150002PMsg> pMsg2List) {

        for (NWZC150002PMsg resPMsg : pMsg2List) {
            if (S21ApiUtil.isXxMsgId(resPMsg)) {
                return true;
            }
        }
        return false;
    }
    // 2016/09/29 S21_NA#14867 Add End

    // 2016/09/28 S21_NA#14264 Add Start
    public class SvcMachCache {

        private Map<String, Map<String, Object>> serNumMap = new HashMap<String, Map<String, Object>>();
        private Map<BigDecimal, Map<String, Object>> svcMachPkMap = new HashMap<BigDecimal, Map<String, Object>>();

        // 2017/02/22 QC#16575 UPD START
        public Map<String, Object> getSvcMachMstrMap(String serNum, BigDecimal svcMachMstrPk, String mdseCd) {
            // 2017/02/22 QC#16575 UPD E N D

            Map<String, Object> rslt = null;
            // 2017/02/22 QC#16575 UPD START
            if (ZYPCommonFunc.hasValue(serNum) && ZYPCommonFunc.hasValue(mdseCd)) {
                rslt = getSvcMachMstrMap(serNum, mdseCd);
                // 2017/02/22 QC#16575 UPD E N D
            } else if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                rslt = getSvcMachMstrMap(svcMachMstrPk);
            }
            return rslt;
        }

        private Map<String, Object> getSvcMachMstrMap(String serNum, String mdseCd) {

            // Mod Start 2019/03/27 QC#30940
//            Map<String, Object> rslt = this.serNumMap.get(serNum);
            StringBuilder mapKey = new StringBuilder();
            mapKey.append(mdseCd);
            mapKey.append(STR_COMMA);
            mapKey.append(serNum);
            Map<String, Object> rslt = this.serNumMap.get(mapKey.toString());
            // Mod End 2019/03/27 QC#30940
            if (rslt == null) {
                // 2017/02/22 QC#16575 UPD START
                // rslt = getSvcMachMstrData(serNum, null);
                rslt = getSvcMachMstrData(serNum, null, mdseCd);
                // 2017/02/22 QC#16575 UPD START
                if (rslt == null) {
                    rslt = new HashMap<String, Object>();
                }
                // Mod Start 2019/03/27 QC#30940
//                this.serNumMap.put(serNum, rslt);
                this.serNumMap.put(mapKey.toString(), rslt);
                // Mod End 2019/03/27 QC#30940
            }
            return dataNullChk(rslt);
        }

        private Map<String, Object> getSvcMachMstrMap(BigDecimal svcMachMstrPk) {

            Map<String, Object> rslt = this.svcMachPkMap.get(svcMachMstrPk);
            if (rslt == null) {
                // 2017/02/22 QC#16575 UPD START
                // rslt = getSvcMachMstrData(null, svcMachMstrPk);
                rslt = getSvcMachMstrData(null, svcMachMstrPk, null);
                // 2017/02/22 QC#16575 UPD START
                if (rslt == null) {
                    rslt = new HashMap<String, Object>();
                }
                this.svcMachPkMap.put(svcMachMstrPk, rslt);
            }
            return dataNullChk(rslt);
        }

        // 2017/02/22 QC#16575 UPD START
        // private Map<String, Object> getSvcMachMstrData(String serNum, BigDecimal svcMachMstrPk) {
        private Map<String, Object> getSvcMachMstrData(String serNum, BigDecimal svcMachMstrPk, String mdseCd) {

            // return NWXC150001DsCheck.getSerNumInfo(glblCmpyCd, serNum, svcMachMstrPk);
            return NWXC150001DsCheck.getSerNumInfo(glblCmpyCd, serNum, mdseCd, svcMachMstrPk);
            // 2017/02/22 QC#16575 UPD E N D
            
        }

        private Map<String, Object> dataNullChk(Map<String, Object> rslt) {

            if (rslt != null) {
                BigDecimal svcMachMstrPk = (BigDecimal) rslt.get("SVC_MACH_MSTR_PK");
                if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                    return null;
                }
            }
            return rslt;
        }
    }
    // 2016/09/28 S21_NA#14264 Add End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    // 2016/10/13 S21_NA#7924-2 Add Start
//    public static boolean isAvalableTocCd(String glblCmpyCd, String tocCd) {
//
//        S21_ORGTMsg s21OrgMsg = new S21_ORGTMsg();
//        ZYPEZDItemValueSetter.setValue(s21OrgMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(s21OrgMsg.tocCd, tocCd);
//        s21OrgMsg = (S21_ORGTMsg) S21CacheTBLAccessor.findByKey(s21OrgMsg);
//        if (s21OrgMsg == null) {
//            return false;
//        }
//        return true;
//    }
//    // 2016/10/13 S21_NA#7924-2 Add End
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // QC#16425 2016/12/14 Add Start
    private String getConvProcStsCd(NWZC150001PMsg pMsg, NWZC150001CpouDetailBean cpouDtlBean){
        BigDecimal count = NWZC150001Query.getInstance().getConvProcStsCd(pMsg, cpouDtlBean);
        if(count.compareTo(BigDecimal.ZERO) == 0){
            return null;
        }
        return ZYPConstant.FLG_OFF_0;
    }
    // QC#16425 2016/12/14 Add End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    public static boolean getIsRetailEquipOrder() {
//        return isRetailEquipOrder;
//    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    public static void setIsRetailEquipOrder(boolean checkResult) {
//        isRetailEquipOrder = checkResult;
//    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    public static ONBATCH_TYPE getOnBatchType() {
//        return onBatchType;
//    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    public static List<MDSETMsg> getDsMdseInfoList() {
//        return dsMdseInfoList;
//    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    public static void setDsMdseInfoList(List<MDSETMsg> msgList) {
//        dsMdseInfoList = msgList;
//    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    // 2017/05/15 S21_NA#Review structure Lv.2 Del Start
//    public static SvcMachCache getSvcMachCache() {
//        return svcMachCache;
//    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Del End

    private void setDefaultBeanData(NWZC150001PMsg pMsg, NWZC150001CpouBean cpouBean, CPOTMsg cpoTMsg) {

        if (isSaveMode(pMsg, cpoTMsg)) {
            cpouBean.setRqstTpCd(NWZC150001CpouConstant.CPO_SAVE);
        }
        if (ORD_HDR_STS.VALIDATED.equals(cpoTMsg.ordHdrStsCd.getValue())) {
            cpouBean.setRqstTpCd(NWZC150001CpouConstant.CPO_MODIFY);
        }
        // 2017/06/16 S21_NA#19288 Add Start
        if (NWZC150001Constant.MODE_CANCEL.equals(pMsg.xxModeCd.getValue())) {
            cpouBean.setRqstTpCd(NWZC150001CpouConstant.CPO_CANCEL);
        }
        // 2017/06/16 S21_NA#19288 Add End

        cpouBean.setCpoOrdTpCd(NWXC150001DsCheck.getCpoOrdTpCdFromDsOrdTp(glblCmpyCd, pMsg.dsOrdTpCd.getValue()));
        cpouBean.setSlsRepTocCd(pMsg.slsRepCd.getValue());
        if (!ZYPCommonFunc.hasValue(cpouBean.getCpoOrdNum())) {
            cpouBean.setOrgRqstDelyDt(pMsg.addRddDt.getValue());
        }
        cpouBean.setInvRcpntCustCd(cpouBean.getBillToCustCd());
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxValUpdFlg.getValue())) {
            cpouBean.setXxValUpdFlg(pMsg.xxValUpdFlg.getValue());
        } else {
            cpouBean.setDiChkHldFlg(cpoTMsg.diChkHldFlg.getValue());
        }

        String usrId = pMsg.usrId.getValue();

        for (NWZC150001CpouDetailBean cpouDtlBean : cpouBean.getDtlBeanList()) {

            cpouDtlBean.setUomCpltFlg(ZYPConstant.FLG_OFF_N);

            if (!ZYPCommonFunc.hasValue(cpouDtlBean.getSlsRepOrSlsTeamTocCd())) {
                cpouDtlBean.setSlsRepOrSlsTeamTocCd(pMsg.slsRepCd.getValue());
            }
            cpouDtlBean.setPmtTermCashDiscCd(cpouBean.getAddPmtTermCashDiscCd());
            cpouDtlBean.setCashDiscTermCd(cpouBean.getAddCashDiscTermCd());
            cpouDtlBean.setFrtChrgToCd(cpouBean.getAddFrtChrgToCd());
            cpouDtlBean.setFrtChrgMethCd(cpouBean.getAddFrtChrgMethCd());
            cpouDtlBean.setShpgSvcLvlCd(cpouBean.getAddShpgSvcLvlCd());
            cpouDtlBean.setCarrAcctNum(cpouBean.getCarrAcctNum());
            if (isSaveMode(pMsg, cpoTMsg)) {
                if (!NWZC150001Constant.RQST_TP_DTL_CANCEL.equals(cpouDtlBean.getDtlRqstTpCd())) {
                    cpouDtlBean.setDtlRqstTpCd(NWZC150001CpouConstant.CPO_DTL_SAVE);
                }
            }

            if (ZYPCommonFunc.hasValue(cpouDtlBean.getSerNum())) {
                if (!ZYPCommonFunc.hasValue(cpouDtlBean.getSvcMachMstrPk())) {
                    BigDecimal svcMachMstrPk = NWZC150001Query.getInstance().getSvcMachMstrPk(glblCmpyCd, cpouDtlBean.getSerNum(), cpouDtlBean.getMdseCd());
                    cpouDtlBean.setSvcMachMstrPk(svcMachMstrPk);
                }
            }

            CPO_DTLTMsg dsCpoDtlMsg = getCurrentDsCpoDtlMsg(cpouBean, cpouDtlBean);

            if (dsCpoDtlMsg == null) {
                cpouDtlBean.setDsCpoDtlCratUsrId(usrId);
                cpouDtlBean.setDsCpoDtlCratTs(this.curTime);
            } else {
                cpouDtlBean.setDsCpoDtlCratUsrId(dsCpoDtlMsg.dsCpoDtlCratUsrId.getValue());
                cpouDtlBean.setDsCpoDtlCratTs(dsCpoDtlMsg.dsCpoDtlCratTs.getValue());
            }
            cpouDtlBean.setDsCpoDtlUpdUsrId(usrId);
            cpouDtlBean.setDsCpoDtlUpdTs(this.curTime);
            cpouDtlBean.setConvProcStsCd(getConvProcStsCd(pMsg, cpouDtlBean));

            for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
                NWZC150001_cpoConfigPMsg configMsg = pMsg.cpoConfig.no(i);
                if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configMsg.configCatgCd.getValue())) {
                    if (S21StringUtil.isEquals(cpouDtlBean.getDsOrdPosnNum(), configMsg.dsOrdPosnNum.getValue())) {
                        cpouDtlBean.setDsCpoConfigPk(configMsg.dsCpoConfigPk.getValue());
                    }
                }
            }
        }

        // 2017/04/28 S21_NA#RS#5881 Mod Start
//        if ((pMsg.rtnDtl.getValidCount() > 0)) {
//            cpouBean.setCpoRtrnDtlUpdFlg(ZYPConstant.FLG_ON_Y);
//        } else {
//            cpouBean.setCpoRtrnDtlUpdFlg(ZYPConstant.FLG_OFF_N);
//        }
        cpouBean.setCpoRtrnDtlUpdFlg(ZYPConstant.FLG_OFF_N);
        // 2017/04/28 S21_NA#RS#5881 Mod End

        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
            cpouBean.setDsCpoCratUsrId(usrId);
            cpouBean.setDsCpoCratTs(this.curTime);
        } else {
            cpouBean.setDsCpoCratUsrId(cpoTMsg.dsCpoCratUsrId.getValue());
            cpouBean.setDsCpoCratTs(cpoTMsg.dsCpoCratTs.getValue());
        }
        cpouBean.setDsCpoUpdUsrId(usrId);
        cpouBean.setDsCpoUpdTs(this.curTime);

        boolean isModeSubmit = S21StringUtil.isEquals(MODE_SUBMIT, pMsg.xxModeCd.getValue());
        boolean isValidated = S21StringUtil.isEquals(ORD_HDR_STS.VALIDATED, cpoTMsg.ordHdrStsCd.getValue());
        if (isModeSubmit || isValidated) {
            cpouBean.setOrdBookReqUsrId(usrId);
            cpouBean.setOrdBookReqTs(this.curTime);
        }
    }

    // 2017/06/13 S21_NA#18869-2 Add Start
    private NWZC150001CpouUpdateOrderData getUpdOrdDataInstance() {
        if (updOrdDataInstance == null) {
            updOrdDataInstance = new NWZC150001CpouUpdateOrderData();
        }
        return updOrdDataInstance;
    }
    // 2017/06/13 S21_NA#18869-2 Add End

    // 2017/07/03 S21_NA#19737 Add Start
    private boolean isAlreadyBookRequested(NWZC150001PMsg pMsg, CPOTMsg cpoTMsg) {

        if (cpoTMsg == null) {
            return false;
        }
        boolean isBookAction = S21StringUtil.isEquals(MODE_SUBMIT, pMsg.xxModeCd.getValue());
        // 2017/10/26 S21_NA#21820 Add Start
        boolean isValidated = S21StringUtil.isEquals(ORD_HDR_STS.VALIDATED, cpoTMsg.ordHdrStsCd.getValue());
        // 2017/10/26 S21_NA#21820 Add End
        if (isBookAction //
                && !isValidated) { // 2017/10/26 S21_NA#21820 Add
            return false;
        }
        return ZYPCommonFunc.hasValue(cpoTMsg.ordBookReqTs) || ZYPCommonFunc.hasValue(cpoTMsg.ordBookReqUsrId);
    }

    private void updateCpoAsBookRequested(NWZC150001PMsg pMsg, CPOTMsg cpoTMsg) {

        if (cpoTMsg == null) {
            return;
        }
        CPOTMsg dsCpoTMsg4Upd = new CPOTMsg();
        EZDMsg.copy(cpoTMsg, null, dsCpoTMsg4Upd, null);

        dsCpoTMsg4Upd = (CPOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsCpoTMsg4Upd);
        if (dsCpoTMsg4Upd != null) {
            ZYPEZDItemValueSetter.setValue(dsCpoTMsg4Upd.dsCpoUpdUsrId, pMsg.usrId.getValue());
            ZYPEZDItemValueSetter.setValue(dsCpoTMsg4Upd.dsCpoUpdTs, this.curTime);
            // 2019/11/06 QC#54413 Mod Start
//            ZYPEZDItemValueSetter.setValue(dsCpoTMsg4Upd.ordBookReqUsrId, pMsg.usrId.getValue());
//            ZYPEZDItemValueSetter.setValue(dsCpoTMsg4Upd.ordBookReqTs, this.curTime);
            // 2019/11/12 QC#54413-1 Mod Start
            //if (!ZYPCommonFunc.hasValue(cpoTMsg.ordBookReqTs) || !ZYPCommonFunc.hasValue(cpoTMsg.ordBookReqUsrId)) {
            if ((!ZYPCommonFunc.hasValue(cpoTMsg.ordBookReqTs) || !ZYPCommonFunc.hasValue(cpoTMsg.ordBookReqUsrId))
                || (S21StringUtil.isEquals(MODE_SUBMIT, pMsg.xxModeCd.getValue()) && S21StringUtil.isEquals(ORD_HDR_STS.SAVED, cpoTMsg.ordHdrStsCd.getValue()))) {
                    // 2019/11/12 QC#54413-1 Mod End
                ZYPEZDItemValueSetter.setValue(dsCpoTMsg4Upd.ordBookReqUsrId, pMsg.usrId.getValue());
                ZYPEZDItemValueSetter.setValue(dsCpoTMsg4Upd.ordBookReqTs, this.curTime);
            }
            // 2019/11/06 QC#54413 Mod End
            dsCpoTMsg4Upd.ordHdrDplyStsCd.setValue(ORD_HDR_DPLY_STS.DI_CHECK_HOLD);

            S21ApiTBLAccessor.update(dsCpoTMsg4Upd);
        }
    }
    // 2017/07/03 S21_NA#19737 Add End

    // 2017/10/16 S21_NA#21267 Add Start
    private boolean isCreditOrder(NWZC150001PMsg pMsg) {

        boolean isCreditOrder = false;

        for (int n = 0; n < pMsg.A.getValidCount(); n++) {
            if (S21StringUtil.isEquals(CR_REBIL.CREDIT, pMsg.A.no(n).crRebilCd_A1.getValue())) {
                isCreditOrder = true;
                break;
            }
        }
        return isCreditOrder;
    }
    // 2017/10/16 S21_NA#21267 Add End

    // 2017/11/09 S21_NA#22091 Add Start
    private void createCpoIstlInfoList(NWZC150001PMsg pMsg) {

        List<Map<String, String>> mandtTblColMapList = NWZC150001Query.getInstance().getDiChkMandtRec(pMsg);

        if (mandtTblColMapList == null || mandtTblColMapList.isEmpty()) {
            return;
        }
        Map<String, List<String>> tblColMap = new HashMap<String, List<String>>();

        for (Map<String, String> mandtTblColMap : mandtTblColMapList) {
            String curTblName = mandtTblColMap.get("DI_MND_CHK_TBL_NM");
            curTblName = curTblName.toUpperCase();
            String curColName = mandtTblColMap.get("DI_MND_CHK_COL_NM");

            List<String> colNameList = tblColMap.get(curTblName);
            if (colNameList == null) {
                colNameList = new ArrayList<String>(0);
            }
            colNameList.add(curColName);
            tblColMap.put(curTblName, colNameList);
        }

        List<String> mandtColList = tblColMap.get(NWZC150001Constant.DI_CHK_TBL_ISTL_INFO);
        if (mandtColList == null) {
            return;
        }

        List<String> diChkColIstlInfoList = Arrays.asList(NWZC150001Constant.DI_CHK_COL_ISTL_INFO_ARRAY);
        List<String> chkColList = new ArrayList<String>(0);
        for (String mandtCol : mandtColList) {
            if (diChkColIstlInfoList.contains(mandtCol)) {
                chkColList.add(mandtCol);
            }
        }
        if (chkColList.contains(NWZC150001Constant.DI_CHK_COL_ISTL_INFO_ISTL_TP) //
                && !chkColList.contains(NWZC150001Constant.DI_CHK_COL_ISTL_INFO_ISTL_DIV)) {
            chkColList.add(NWZC150001Constant.DI_CHK_COL_ISTL_INFO_ISTL_DIV);
        }

        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            setDefaultIstlInfo(pMsg, pMsg.cpoConfig.no(i), chkColList);
        }
    }

    private void setDefaultIstlInfo(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg cpoConfigPMsg, List<String> chkColList) {

        BigDecimal mdlId = cpoConfigPMsg.mdlId.getValue();
        BigDecimal dsCpoConfigPk = cpoConfigPMsg.dsCpoConfigPk.getValue();
        String configCatgCd = cpoConfigPMsg.configCatgCd.getValue();
        String dsOrdPosnNum = cpoConfigPMsg.dsOrdPosnNum.getValue();
        NWZC150001_cpoIstlInfoListPMsg cpoIstlInfoListPMsg = null;

        int i = 0;
        for (; i < pMsg.cpoIstlInfoList.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, pMsg.cpoIstlInfoList.no(i).dsOrdPosnNum.getValue()) //
                    && S21StringUtil.isEquals(configCatgCd, pMsg.cpoIstlInfoList.no(i).configCatgCd.getValue())) {
                cpoIstlInfoListPMsg = pMsg.cpoIstlInfoList.no(i);
                break;
            }
        }
        boolean isRqstIstlDiv = chkColList.contains(NWZC150001Constant.DI_CHK_COL_ISTL_INFO_ISTL_DIV);
        boolean isRqstIstlTp = chkColList.contains(NWZC150001Constant.DI_CHK_COL_ISTL_INFO_ISTL_TP);

        if (cpoIstlInfoListPMsg == null) {
            DS_CPO_ISTL_INFOTMsgArray dsCpoIstlInfoTMsgArray = getDsCpoIstlInfoByConfigPk(pMsg, cpoConfigPMsg);
            DS_CPO_ISTL_INFOTMsg dsCpoIstlInfoTMsg = null;
            String xxRqstTpCd = RQST_TP_DELY_INFO_MODIFY;
            if (dsCpoIstlInfoTMsgArray == null || dsCpoIstlInfoTMsgArray.getValidCount() == 0) {
                xxRqstTpCd = RQST_TP_DELY_INFO_NEW;
            } else {
                dsCpoIstlInfoTMsg = dsCpoIstlInfoTMsgArray.no(0);
            }
            if (isRqstIstlDiv || isRqstIstlTp) {
                NWZC150001_cpoIstlInfoListPMsg cpoIstlInfoListPMsg4Setup = new NWZC150001_cpoIstlInfoListPMsg();

                cpoIstlInfoListPMsg4Setup.xxRqstTpCd.setValue(xxRqstTpCd);
                ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg4Setup.dsCpoConfigPk, dsCpoConfigPk);
                ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg4Setup.dsOrdPosnNum, dsOrdPosnNum);
                ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg4Setup.configCatgCd, configCatgCd);
                if (dsCpoIstlInfoTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg4Setup.dsCpoIstlInfoPk, dsCpoIstlInfoTMsg.dsCpoIstlInfoPk);
                }
                boolean isSetUp = false;
                if (isRqstIstlTp //
                        && (dsCpoIstlInfoTMsg == null || (dsCpoIstlInfoTMsg != null && !ZYPCommonFunc.hasValue(dsCpoIstlInfoTMsg.svcIstlRuleNum)))) {
                    ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg4Setup.svcIstlRuleNum, getDefaultIstlTp(mdlId, configCatgCd));
                    isSetUp = true;
                }
                // 2019/11/22 QC#54213 Add Start
                CPO_DTLTMsg cdMsg = new CPO_DTLTMsg();
                cdMsg.setSQLID("502");
                cdMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
                cdMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());
                cdMsg.setConditionValue("dsOrdPosnNum01", cpoConfigPMsg.dsOrdPosnNum.getValue());
                CPO_DTLTMsgArray array = (CPO_DTLTMsgArray) EZDTBLAccessor.findByCondition(cdMsg);
                if (array != null && array.length() != 0) {
                    for (int j = 0; j < array.getValidCount(); j++) {
                        if (!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, array.no(j).baseCmptFlg.getValue())) {
                            continue;
                        }
                        String baseRtlWhCd = array.no(j).rtlWhCd.getValue();
                        if (RTL_WH_BILL_ONLY.contains(baseRtlWhCd)) {
                            ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg4Setup.svcIstlRuleNum, NWZC150001Constant.SVC_ISTL_RULE_NUM_NO_INSTALL);
                            isSetUp = true;
                        }
                        break;
                    }
                }
                // 2019/11/22 QC#54213 Add Start

                // START 2023/09/05 K.Watanabe [QC#53408, ADD]
                cdMsg.setConditionValue("configCatgCd01", cpoConfigPMsg.configCatgCd.getValue());

                // START 2023/12/12 K.Watanabe [QC#61300, ADD]
                setDeinsItemItem(cpoIstlInfoListPMsg4Setup, pMsg, cpoConfigPMsg);
                // END   2023/12/12 K.Watanabe [QC#61300, ADD]

                Map<String, Object> defaultAccessoryInstType = NWZC150001Query.getInstance().getDefaultAccessoryInstTypeWithConfNum(pMsg, cpoConfigPMsg);
                if (defaultAccessoryInstType != null) {

                    String defaultAccessoryInstTypeNum = (String) defaultAccessoryInstType.get("SVC_ISTL_RULE_NUM");

                    if (ZYPCommonFunc.hasValue(defaultAccessoryInstTypeNum)) {
                        ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg4Setup.svcIstlRuleNum, defaultAccessoryInstTypeNum);
                    } else { 
                        ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg4Setup.svcIstlRuleNum, NWZC150001Constant.SVC_ISTL_RULE_NUM_NO_INSTALL);
                    }
                }
                // END 2023/09/05 K.Watanabe [QC#53408, ADD]

                boolean isNeedIstlDiv = false;
                if (dsCpoIstlInfoTMsg != null //
                        && !S21StringUtil.isEquals(NWZC150001Constant.SVC_ISTL_RULE_NUM_NO_INSTALL, dsCpoIstlInfoTMsg.svcIstlRuleNum.getValue())) {
                    if (!ZYPCommonFunc.hasValue(dsCpoIstlInfoTMsg.istlDivCd)) {
                        isNeedIstlDiv = true;
                    }
                }
                if (dsCpoIstlInfoTMsg == null) {
                    isNeedIstlDiv = true;
                    if (S21StringUtil.isEquals(NWZC150001Constant.SVC_ISTL_RULE_NUM_NO_INSTALL, cpoIstlInfoListPMsg4Setup.svcIstlRuleNum.getValue())) {
                        isNeedIstlDiv = false;
                    }
                }
                if (isRqstIstlDiv && isNeedIstlDiv) {
                    // 2018/01/31 QC#23563 mod start
                    ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg4Setup.istlDivCd, NWZC150001Query.getInstance().getDefaultIstlDiv(pMsg));
                    // 2018/01/31 QC#23563 mod end
                    isSetUp = true;
                }
                if (isSetUp) {
                    int idx = pMsg.cpoIstlInfoList.getValidCount();
                    EZDMsg.copy(cpoIstlInfoListPMsg4Setup, null, pMsg.cpoIstlInfoList.no(idx), null);
                    idx++;
                    pMsg.cpoIstlInfoList.setValidCount(idx);
                    cpoIstlInfoListPMsg4Setup = null;
                }
            }
        } else {
            if (isRqstIstlTp && !ZYPCommonFunc.hasValue(cpoIstlInfoListPMsg.svcIstlRuleNum)) {
                String svcIstlRuleNum = getDefaultIstlTp(mdlId, configCatgCd);
                ZYPEZDItemValueSetter.setValue(pMsg.cpoIstlInfoList.no(i).svcIstlRuleNum, svcIstlRuleNum);
            }
            if (isRqstIstlDiv && !ZYPCommonFunc.hasValue(cpoIstlInfoListPMsg.istlDivCd)) {
                if (S21StringUtil.isEquals(NWZC150001Constant.SVC_ISTL_RULE_NUM_NO_INSTALL, pMsg.cpoIstlInfoList.no(i).svcIstlRuleNum.getValue())) {
                    pMsg.cpoIstlInfoList.no(i).istlDivCd.clear();
                } else {
                    // 2018/01/31 QC#23563 mod start
                    ZYPEZDItemValueSetter.setValue(pMsg.cpoIstlInfoList.no(i).istlDivCd, NWZC150001Query.getInstance().getDefaultIstlDiv(pMsg));
                    // 2018/01/31 QC#23563 mod end
                }
            }
        }
    }

    // START 2023/12/12 K.Watanabe [QC#61300, ADD]
    /**
     * Set Deinstall Area Item
     * @param NWZC150001_cpoIstlInfoListPMsg cpoIstlInfoListPMsg4Setup
     * @param NWZC150001PMsg pMsg
     * @param NWZC150001_cpoConfigPMsg cpoConfigPMsg
     */
    private void setDeinsItemItem(NWZC150001_cpoIstlInfoListPMsg cpoIstlInfoListPMsg4Setup, NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg cpoConfigPMsg) {
        if (S21StringUtil.isEquals(cpoConfigPMsg.configCatgCd.getValue(), CONFIG_CATG.INBOUND)) {
            Map<String, Object> deinstallInfoMap = NWZC150001Query.getInstance().getDeinstallInfo(pMsg, cpoConfigPMsg);
            if (deinstallInfoMap != null) {
                String svcBySvcPrvdPtyCd = (String) deinstallInfoMap.get("SVC_BY_SVC_PRVD_PTY_CD");
                String svcDeinsRuleNum = (String) deinstallInfoMap.get("SVC_DEINS_RULE_NUM");
                if (!ZYPCommonFunc.hasValue(cpoIstlInfoListPMsg4Setup.istlBySvcPrvdPtyCd) && ZYPCommonFunc.hasValue(svcBySvcPrvdPtyCd)) {
                    ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg4Setup.istlBySvcPrvdPtyCd, svcBySvcPrvdPtyCd);
                }
                if (ZYPCommonFunc.hasValue(svcDeinsRuleNum)) {
                    ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg4Setup.svcIstlRuleNum, svcDeinsRuleNum);
                } else {
                    BigDecimal dsCpoConfigPk = (BigDecimal) deinstallInfoMap.get("DS_CPO_CONFIG_PK");
                    BigDecimal svcMachMstrPk = (BigDecimal) deinstallInfoMap.get("SVC_MACH_MSTR_PK");
                    Map<String, Object> svcDeinsRuleForMdseMap = NWZC150001Query.getInstance().getMdlSvcDeinsRuleNum(pMsg.glblCmpyCd.getValue(), dsCpoConfigPk, svcMachMstrPk);
                    if (svcDeinsRuleForMdseMap == null) {
                        Map<String, Object> svcDeinsRuleForRtrnDtlMap = NWZC150001Query.getInstance().getMdseSvcDeinsRuleNum(pMsg.glblCmpyCd.getValue(), dsCpoConfigPk);
                        if (svcDeinsRuleForRtrnDtlMap != null) {
                            String svcDeinsRuleNumForRtrnDtl = (String) svcDeinsRuleForRtrnDtlMap.get("SVC_DEINS_RULE_NUM");
                            ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg4Setup.svcIstlRuleNum, svcDeinsRuleNumForRtrnDtl);
                        }
                    }
                }
            }
        }
    }
    // END 2023/12/12 K.Watanabe [QC#61300, ADD]

    private String getDefaultIstlTp(BigDecimal mdlId, String configCatgCd) {

        DS_MDLTMsg dsMdlTMsg = getDsMdl(mdlId);
        if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configCatgCd)) {
            return dsMdlTMsg.svcIstlRuleNum.getValue();
        } else if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configCatgCd)) {
            return dsMdlTMsg.svcDeinsRuleNum.getValue();
        }
        return null;
    }
    // 2018/01/31 QC#23563 del start
//    private String getDefaultIstlDiv(NWZC150001PMsg pMsg) {
//
//        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfn = new DS_ORD_TP_PROC_DFNTMsg();
//        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfn.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfn.dsOrdTpCd, pMsg.dsOrdTpCd);
//        dsOrdTpProcDfn = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(dsOrdTpProcDfn);
//
//        if (dsOrdTpProcDfn != null) {
//            return dsOrdTpProcDfn.lineBizTpCd.getValue();
//        }
//        return null;
//    }
    // 2018/01/31 QC#23563 del end

    private DS_CPO_ISTL_INFOTMsgArray getDsCpoIstlInfoByConfigPk(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg cpoConfigPMsg) {

        DS_CPO_ISTL_INFOTMsg dsCpoIstlInfoTMsg = new DS_CPO_ISTL_INFOTMsg();
        dsCpoIstlInfoTMsg.setSQLID("002");
        dsCpoIstlInfoTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        dsCpoIstlInfoTMsg.setConditionValue("dsCpoConfigPk01", cpoConfigPMsg.dsCpoConfigPk.getValue());

        return (DS_CPO_ISTL_INFOTMsgArray) S21ApiTBLAccessor.findByCondition(dsCpoIstlInfoTMsg);
    }

    private DS_MDLTMsg getDsMdl(BigDecimal mdlId) {

        if (!ZYPCommonFunc.hasValue(mdlId)) {
            DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
            dsMdlTMsg.svcIstlRuleNum.setValue(NWZC150001Constant.SVC_ISTL_RULE_NUM_NO_INSTALL);
            // START 2023/12/12 K.Watanabe [QC#61300, MOD]
            //dsMdlTMsg.svcDeinsRuleNum.setValue(NWZC150001Constant.SVC_ISTL_RULE_NUM_NO_INSTALL);
            dsMdlTMsg.svcDeinsRuleNum.setValue(NWZC150001Constant.SVC_DEISTL_RULE_NUM_NO_INSTALL);
            // END   2023/12/12 K.Watanabe [QC#61300, MOD]
            return dsMdlTMsg;
        }

        DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, mdlId);

        dsMdlTMsg = (DS_MDLTMsg) S21CacheTBLAccessor.findByKey(dsMdlTMsg);
        if (dsMdlTMsg == null) {
            dsMdlTMsg = new DS_MDLTMsg();
            dsMdlTMsg.svcIstlRuleNum.setValue(NWZC150001Constant.SVC_ISTL_RULE_NUM_NO_INSTALL);
            // START 2023/12/12 K.Watanabe [QC#61300, MOD]
            //dsMdlTMsg.svcDeinsRuleNum.setValue(NWZC150001Constant.SVC_ISTL_RULE_NUM_NO_INSTALL);
            dsMdlTMsg.svcDeinsRuleNum.setValue(NWZC150001Constant.SVC_DEISTL_RULE_NUM_NO_INSTALL);
            // END   2023/12/12 K.Watanabe [QC#61300, MOD]
        }
        if (dsMdlTMsg != null && !ZYPCommonFunc.hasValue(dsMdlTMsg.svcIstlRuleNum)) {
            dsMdlTMsg.svcIstlRuleNum.setValue(NWZC150001Constant.SVC_ISTL_RULE_NUM_NO_INSTALL);
        }
        if (dsMdlTMsg != null && !ZYPCommonFunc.hasValue(dsMdlTMsg.svcDeinsRuleNum)) {
            // START 2023/12/12 K.Watanabe [QC#61300, MOD]
            //dsMdlTMsg.svcDeinsRuleNum.setValue(NWZC150001Constant.SVC_ISTL_RULE_NUM_NO_INSTALL);
            dsMdlTMsg.svcDeinsRuleNum.setValue(NWZC150001Constant.SVC_DEISTL_RULE_NUM_NO_INSTALL);
            // END   2023/12/12 K.Watanabe [QC#61300, MOD]
        }
        return dsMdlTMsg;
    }
    // 2017/11/09 S21_NA#22091 Add End

    // START 2023/05/11 T.Doi [CSA-QC#61246, ADD]
    private void execCancelPrchReq(NWZC150001PMsg pMsg, List<NWZC150002PMsg> resPMsg2List) {
        List<Map<String, Object>> prchReqDtlKeyList = NWZC150001Query.getInstance().getPrchReqDtlKey(pMsg);

        int cnt = 0;
        NPZC103001PMsg params = null;
        String cpoDtlLineNum = "";
        String cpoDtlLineSubNum = "";

        for (Map<String, Object> prchReqDtlKey : prchReqDtlKeyList) {

            if (cnt == 0) {
                params = new NPZC103001PMsg();
                ZYPEZDItemValueSetter.setValue(params.xxModeCd, NPZC103001Constant.MODE_CANCEL);
                ZYPEZDItemValueSetter.setValue(params.eventId, NPZC103001Constant.EVENT_SUBMIT);
                ZYPEZDItemValueSetter.setValue(params.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(params.prchReqNum, (String) prchReqDtlKey.get("PRCH_REQ_NUM"));
                cpoDtlLineNum = (String) prchReqDtlKey.get("CPO_DTL_LINE_NUM");
                cpoDtlLineSubNum = (String) prchReqDtlKey.get("CPO_DTL_LINE_SUB_NUM");
            }

            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(cnt).prchReqLineNum, (String) prchReqDtlKey.get("PRCH_REQ_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(cnt).prchReqLineSubNum, (BigDecimal) prchReqDtlKey.get("PRCH_REQ_LINE_SUB_NUM"));
            cnt++;
        }

        if (params != null) {
            params.prchReqInfo.setValidCount(cnt);

            NPZC103001 api = new NPZC103001();
            api.execute(params, ONBATCH_TYPE.ONLINE);

            if (params.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < params.xxMsgIdList.getValidCount(); i++) {
                    NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                    NWZC150001Common.setMsgId2(pMsg2, params.xxMsgIdList.no(i).xxMsgId.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, cpoDtlLineNum);
                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, cpoDtlLineSubNum);
                    resPMsg2List.add(pMsg2);
                }
            }
        }
    }
    // END 2023/05/11 T.Doi [CSA-QC#61246, ADD]

    // START 2023/10/13 [QC#61077, ADD]
    private void initOrdForChngOrdRsnCtg(NWZC150001PMsg pMsg, List<NWZC150002PMsg> resPMsg2List, CPOTMsg cpoTMsg) {
        // is order number exists
        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
            return;
        }
        // is exists order
        if (!existsCpo(cpoTMsg)) {
            return;
        }
        // is already book requested
        if (!isAlreadyBookRequested(pMsg, cpoTMsg)) {
            return;
        }
        // changed category or reason
        if (S21StringUtil.isEquals(cpoTMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdCatgCd.getValue()) && S21StringUtil.isEquals(cpoTMsg.dsOrdTpCd.getValue(), pMsg.dsOrdTpCd.getValue())) {
            return;
        }
        // Cancel Workflow
        if (isAlreadyStartedWorkFlow(pMsg)) {
            callOrdProcWorkflowCancelAPI(pMsg, resPMsg2List, cpoTMsg, CANCEL);
        }
        // Revert CPO
        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrStsCd, ORD_HDR_STS.SAVED);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.submtFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrDplyStsCd, ORD_HDR_DPLY_STS.ENTERED);
        S21ApiTBLAccessor.update(cpoTMsg);
        // Revert CPO_DTL
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg dtlPMsg = pMsg.A.no(i);
            CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, pMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, dtlPMsg.cpoDtlLineNum_A1);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, dtlPMsg.cpoDtlLineSubNum_A1);
            cpoDtlTMsg = (CPO_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cpoDtlTMsg);
            if (cpoDtlTMsg == null || (!S21StringUtil.isEquals(cpoDtlTMsg.ordLineStsCd.getValue(), ORD_LINE_STS.VALIDATED))) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.ordLineStsCd, ORD_LINE_STS.SAVED);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.submtFlg, ZYPConstant.FLG_OFF_N);
            S21ApiTBLAccessor.update(cpoDtlTMsg);
            // Delete SHPG_PLN
            SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
            shpgPlnTMsg.setSQLID("004");
            shpgPlnTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            shpgPlnTMsg.setConditionValue("trxHdrNum01", cpoDtlTMsg.cpoOrdNum.getValue());
            shpgPlnTMsg.setConditionValue("trxLineNum01", cpoDtlTMsg.cpoDtlLineNum.getValue());
            shpgPlnTMsg.setConditionValue("trxLineSubNum01", cpoDtlTMsg.cpoDtlLineSubNum.getValue());
            SHPG_PLNTMsgArray shpgPlnTMsgList = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(shpgPlnTMsg);
            for (int j = 0; j < shpgPlnTMsgList.getValidCount(); j++) {
                shpgPlnTMsg = shpgPlnTMsgList.no(j);
                S21ApiTBLAccessor.logicalRemove(shpgPlnTMsg);
                // Delete PRC_DTL
                PRC_DTLTMsg prcDtlTMsg = new PRC_DTLTMsg();
                prcDtlTMsg.setSQLID("005");
                prcDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                prcDtlTMsg.setConditionValue("shpgPlnNum01", shpgPlnTMsg.shpgPlnNum.getValue());
                PRC_DTLTMsgArray prcDtlTMsgs = (PRC_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(prcDtlTMsg);
                for (int k = 0; k < prcDtlTMsgs.getValidCount(); k++) {
                    prcDtlTMsg = prcDtlTMsgs.no(k);
                    S21ApiTBLAccessor.logicalRemove(prcDtlTMsg);
                }
            }
        }
        // Delete HLD
        HLDTMsg hldTMsg = new HLDTMsg();
        hldTMsg.setSQLID("004");
        hldTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        hldTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());
        HLDTMsgArray hldTMsgs = (HLDTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(hldTMsg);
        for (int l = 0; l < hldTMsgs.getValidCount(); l++) {
            hldTMsg = hldTMsgs.no(l);
            S21ApiTBLAccessor.logicalRemove(hldTMsg);
        }
    }
    // END   2023/10/13 [QC#61077, ADD]
}
