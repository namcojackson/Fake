/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.blap.NWAL1500.constant.NWAL1500Constant.IDX_3;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LEASE_BYOT_MDSE_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_CANCELLED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NOT_ALLOC_WH_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1500_ORD_LINE_CANCELLED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1500_RENTAL_CONV_DEFWH;
import static business.blap.NWAL1500.constant.NWAL1500Constant.ORD_LINE_STS_TO_BE_CANCELLED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.PERIOD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.PKG_UOM_FOR_PRC;
import static business.blap.NWAL1500.constant.NWAL1500Constant.SHPG_CMT_TXT_LIMIT_SIZE;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TO_BE_CANCELLED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.XX_MODE_CD_EMSD;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CUST_PO_NUM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_FLR_PRC_LIST;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_LOG_TYPE;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_PRC_LIST;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_RSN;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0181E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0672E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0757W;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0896E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0939E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0946E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0972E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0981W;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM8458E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.ZZM9000E;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCItem;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSItem;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500ItemNameList;
import business.blap.NWAL1500.NWAL1500Query;
import business.blap.NWAL1500.NWAL1500QueryForLineConfig;
import business.blap.NWAL1500.NWAL1500QueryForSaveSubmit;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ACMsg;
import business.blap.NWAL1500.NWAL1500_ACMsgArray;
import business.blap.NWAL1500.NWAL1500_ASMsg;
import business.blap.NWAL1500.NWAL1500_ASMsgArray;
import business.blap.NWAL1500.NWAL1500_BCMsg;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.blap.NWAL1500.NWAL1500_BSMsgArray;
import business.blap.NWAL1500.NWAL1500_CCMsg;
import business.blap.NWAL1500.NWAL1500_CSMsg;
import business.blap.NWAL1500.NWAL1500_CSMsgArray;
import business.blap.NWAL1500.NWAL1500_DCMsg;
import business.blap.NWAL1500.NWAL1500_DSMsg;
import business.blap.NWAL1500.NWAL1500_DSMsgArray;
import business.blap.NWAL1500.NWAL1500_GCMsgArray;
import business.blap.NWAL1500.NWAL1500_HCMsgArray;
import business.blap.NWAL1500.NWAL1500_ISMsg;
import business.blap.NWAL1500.NWAL1500_JSMsg;
import business.blap.NWAL1500.NWAL1500_PCMsg;
import business.blap.NWAL1500.NWAL1500_SSMsg;
import business.blap.NWAL1500.constant.NWAL1500Constant;
import business.blap.NWAL1500.constant.NWAL1500MsgConstant;
import business.db.CNTYTMsg;
import business.db.COA_BRTMsg;
import business.db.COA_BRTMsgArray;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.CPO_SRC_TPTMsg;
import business.db.CPO_SRC_TPTMsgArray;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.FRT_CONDTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsgArray;
import business.db.MDSETMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.ORD_ENTRY_ACTTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.db.PRC_CALC_EXCL_SWHTMsg;
import business.db.PRC_CATGTMsg;
import business.db.PRC_COND_TPTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SPEC_COND_PRCTMsg;
import business.db.SPEC_COND_PRCTMsgArray;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC260001_defSlsRepListPMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_ShippingDefaultInfoListPMsg;
import business.parts.NMZC611001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157001_xxPrcListPMsgArray;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;
import business.parts.NWZC180001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC611001.NMZC611001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs.NWZC150001CpouInsBizProcLog;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001SvcMdlFunc;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_ENTRY_ACT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CALC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         S.Takami        Create          N/A
 * 2015/09/28   Fujitsu         T.Yoshida       Create          N/A
 * 2015/11/19   Fujitsu         S.Takami        Update          S21_NA#853
 * 2015/12/10   Fujitsu         Y.Kanefusa      Update          S21_NA#1364
 * 2016/01/06   Fujitsu         T.Ishii         Update          S21_NA#2522
 * 2016/01/19   Fujitsu         S.Takami        Update          S21_NA#3339
 * 2016/01/26   Fujitsu         S.Takami        Update          S21_NA#3553
 * 2016/01/28   Fujitsu         S.Takami        Update          S21_NA#3254
 * 2016/02/15   Fujitsu         M.Hara          Update          S21_NA#1692
 * 2016/02/17   Fujitsu         Y.Taoka         Update          S21_NA#1694/4375
 * 2016/02/26   Fujitsu         M.Suzuki        Update          S21_NA#966
 * 2016/03/03   Fujitsu         Y.Taoka         Update          S21_NA#1620
 * 2016/03/05   Fujitsu         T.Ishii         Update          S21_NA#5000#84
 * 2016/03/25   Fujitsu         S.Takami        Update          S21_NA#4693
 * 2016/04/08   Fujitsu         S.Takami        Update          S21_NA#5356
 * 2016/04/13   Fujitsu         S.Takami        Update          S21_NA#6971
 * 2016/04/11   Fujitsu         S.Takami        Update          S21_NA#3236-3
 * 2016/04/26   Fujitsu         M.Yamada        Update          S21_NA#6312
 * 2016/05/10   Fujitsu         M.Yamada        Update          S21_NA#6148
 * 2016/05/11   Fujitsu         T.Yoshida       Update          S21_NA#8166
 * 2016/05/12   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/05/18   Fujitsu         T.Murai         Update          S21_NA#8317, 5337
 * 2016/05/19   Fujitsu         M.Hara          Update          S21_NA#8450
 * 2016/05/30   Fujitsu         T.Murai         Update          S21_NA#7353
 * 2016/06/06   Fujitsu         Y.Kanefusa      Update          S21_NA#6480
 * 2016/06/10   Fujitsu         Y.Kanefusa      Update          S21_NA#9482
 * 2016/06/16   Fujitsu         S.Takami        Update          S21_NA#9855
 * 2016/07/05   Fujitsu         T.Yoshida       Update          S21_NA#10321
 * 2016/07/08   Fujitsu         T.Yoshida       Update          S21_NA#11618
 * 2016/07/19   Fujitsu         T.Yoshida       Update          S21_NA#3834
 * 2016/07/11   Fujitsu         S.Takami        Update          S21_NA#7821
 * 2016/08/04   Fujitsu         S.Takami        Update          S21_NA#13012
 * 2016/08/05   Fujitsu         T.Yoshida       Update          S21_NA#13106
 * 2016/08/09   Fujitsu         Y.Taoka         Update          S21_NA#8388
 * 2016/08/12   SRAA            K.Aratani       Update          S21_NA#13428
 * 2016/08/23   Fujitsu         Y.Taoka         Update          S21_NA#8388
 * 2016/08/30   Fujitsu         S.Takami        Update          S21_NA#13948
 * 2016/09/02   Fujitsu         Y.Taoka         Update          S21_NA#7942
 * 2016/09/05   Fujitsu         W.Honda         Update          S21_NA#12435
 * 2016/09/20   Fujitsu         S.Takami        Update          S21_NA#8279
 * 2016/09/21   Fujitsu         S.Takami        Update          S21_NA#10274
 * 2016/09/27   Fujitsu         T.Yoshida       Update          S21_NA#14698
 * 2016/09/27   Fujitsu         N.Sugiura       Update          S21_NA#9192
 * 2016/09/28   Fujitsu         M.Ohno          Update          S21_NA#12598
 * 2016/10/03   Fujitsu         S.Takami        Update          S21_NA#7131
 * 2016/10/14   Fujitsu         S.Takami        Update          S21_NA#13948-2
 * 2016/10/18   Fujitsu         S.Ohki          Update          S21_NA#15054
 * 2016/10/19   Fujitsu         Y.Kanefusa      Update          S21_NA#14330
 * 2016/10/21   Fujitsu         S.Ohki          Update          S21_NA#15489
 * 2016/11/04   Fujitsu         S.Takami        Update          S21_NA#15703
 * 2016/11/11   Fujitsu         S.Takami        Update          S21_NA#9864-2
 * 2016/12/05   Fujitsu         T.Yoshida       Update          S21_NA#15889
 * 2017/01/12   Fujitsu         M.Ohno          Update          S21_NA#16655
 * 2017/01/30   Fujitsu         R.Nakamura      Update          S21_NA#17186
 * 2017/02/24   Fujitsu         S.Takami        Update          S21_NA#17714
 * 2017/02/24   Fujitsu         Y.Kanefusa      Update          S21_NA#13688
 * 2017/03/02   Fujitsu         S.Takami        Update          S21_NA#17714-2
 * 2017/03/09   Fujitsu         W.Honda         Update          S21_NA#16855
 * 2017/05/30   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 (avoid same parson on sales credit)
 * 2017/06/14   Fujitsu         S.Takami        Update          S21_NA#18623
 * 2017/06/16   Fujitsu         S.Takami        Update          S21_NA#19005
 * 2017/06/19   Fujitsu         N.Aoyama        Update          S21_NA#19311
 * 2017/06/29   Fujitsu         Y.Kanefusa      Update          S21_NA#19603
 * 2017/07/11   Fujitsu         A.Kosai         Update          S21_NA#19782
 * 2017/07/11   Fujitsu         S.Takami        Update          S21_NA#19779
 * 2017/08/15   Fujitsu         S.Takami        Update          S21_NA#20377
 * 2017/09/11   Fujitsu         S.Takami        Update          S21_NA#20505
 * 2017/09/21   Fujitsu         T.Murai         Update          S21_NA#16346(Sol#373)
 * 2017/09/25   Fujitsu         Y.Kanefusa      Update          S21_NA#21106
 * 2017/10/04   Fujitsu         S.Takami        Update          S21_NA#21300
 * 2017/10/05   Fujitsu         R.Nakamura      Update          S21_NA#21356
 * 2017/10/13   Fujitsu         S.Takami        Update          S21_NA#21267
 * 2017/10/18   Fujitsu         S.Takami        Update          S21_NA#21708
 * 2017/10/18   Fujitsu         T.Aoi           Update          S21_NA#21730
 * 2017/10/20   Fujitsu         H.Sugawara      Update          QC#21773
 * 2017/10/31   Fujitsu         Y.Kanefusa      Update          S21_NA#22031
 * 2017/11/02   Fujitsu         Y.Kanefusa      Update          S21_NA#22238
 * 2017/11/02   Fujitsu         T.Aoi           Update          S21_NA#20146(Sol#92)
 * 2017/11/24   Fujitsu         Y.Kanefusa      Update          S21_NA#22789
 * 2017/12/07   Fujitsu         A.Kosai         Update          S21_NA#21621
 * 2017/12/12   Fujitsu         A.Kosai         Update          S21_NA#19804(Sol#349)
 * 2018/01/10   Fujitsu         Y.Kanefusa      Update          S21_NA#22372
 * 2018/01/11   Fujitsu         S.Takami        Update          S21_NA#23329
 * 2018/01/26   Fujitsu         K.Ishizuka      Update          S21_NA#23140
 * 2018/01/29   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/12   Fujitsu         A.Kosai         Update          S21_NA#20297(Sol#379)
 * 2018/03/15   Fujitsu         S.Takami        Update          S21_NA#19808-2(glblMsg.I->glblMsg.I without any comments)
 * 2018/03/16   Fujitsu         S.Takami        Update          S21_NA#19808-3(fixing: getDclnSvcChkBoxValHdrFromConfig)
 * 2018/03/19   Fujitsu         K.Ishizuka      Update          S21_NA#24459
 * 2018/03/27   Fujitsu         S.Takami        Update          S21_NA#25060
 * 2018/04/03   Fujitsu         K.Ishizuka      Update          S21_NA#24860
 * 2018/04/05   Fujitsu         K.Ishizuka      Update          S21_NA#24098
 * 2018/04/11   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * 2018/04/18   Fujitsu         K.Ishizuka      Update          S21_NA#25418
 * 2018/04/24   Fujitsu         S.Takami        Update          S21_NA#25534
 * 2018/05/16   Fujitsu         M.Ohno          Update          S21_NA#25144
 * 2018/05/21   Fujitsu         M.Ohno          Update          S21_NA#25454
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/06/21   Fujitsu         S.Takami        Update          S21_NA#25151
 * 2018/06/25   Fujitsu         A.Kosai         Update          S21_NA#25227
 * 2018/06/25   Fujitsu         Mz.Takahashi    Update          S21_NA#23726
 * 2018/07/09   Fujitsu         S.Takami        Update          S21_NA#26895
 * 2018/07/13   Fujitsu         S.Takami        Update          S21_NA#27228
 * 2018/07/19   Fujitsu         T.Aoi           Update          S21_NA#27227
 * 2018/08/02   Fujitsu         M.Ohno          Update          S21_NA#26665
 * 2018/08/06   Fujitsu         S.Takami        Update          S21_NA#27642
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 * 2018/09/11   Fujitsu         K.Ishizuka      Update          S21_NA#25352
 * 2018/09/20   Fujitsu         S.Takami        Update          S21_NA#28199
 * 2018/09/26   Fujitsu         K.Ishizuka      Update          S21_NA#28482
 * 2018/09/27   Fujitsu         S.Takami        Update          S21_NA#28199-2
 * 2018/10/24   Fujitsu         H.Kumagai       Update          QC#28866
 * 2018/12/12   Fujitsu         S.Kosaka        Update          QC#29315
 * 2018/12/18   Fujitsu         K.Ishizuka      Update          S21_NA#29561
 * 2019/01/11   Fujitsu         K.Ishizuka      Update          S21_NA#29811
 * 2019/01/30   Fujitsu         M.Ishii         Update          QC#30036
 * 2019/02/15   Fujitsu         K.Kato          Update          QC#30308
 * 2019/02/25   Fujitsu         S.Kosaka        Update          QC#30372
 * 2019/05/23   Fujitsu         Mz.Takahashi    Update          QC#50043
 * 2019/06/03   Fujitsu         C.Hara          Update          QC#50555
 * 2019/07/25   Fujitsu         R.Nakamura      Update          QC#52072
 * 2019/07/31   Fujitsu         Y.Kanefusa      Update          S21_NA#52264
 * 2019/08/09   Fujitsu         M.Ohno          Update          S21_NA#52472
 * 2019/09/18   Fujitsu         R.Nakamura      Update          S21_NA#53018
 * 2019/12/16   Fujitsu         Y.Kanefusa      Update          S21_NA#52416
 * 2019/12/19   Fujitsu         Y.Kanefusa      Update          S21_NA#55162
 * 2020/01/08   Fujitsu         C.Hara          Update          QC#54197-1
 * 2020/04/27   CITS            K.Ogino         Update          QC#56638
 * 2022/02/17   Fujitsu         C.Hara          Update          QC#59693
 * 2023/05/11   CITS            R.Azucena       Update          QC#61514
 * 2023/08/04   CITS            K.Ikeda         Update          QC#61691
 *</pre>
 */
public class NWAL1500CommonLogic {

    /** Log Out */
    private static final boolean LOG_OUT = false;

    /**
     * Set Authority
     * @param bizMsg NWAL1500CMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setAuthority(NWAL1500CMsg bizMsg, S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);

        int funcIdCnt = 0;
        for (String functionId : functionIds) {
            bizMsg.L.no(funcIdCnt++).xxFuncId.setValue(functionId);
        }
        bizMsg.L.setValidCount(funcIdCnt);
    }

    /**
     * create Sub Reason Code PullDown
     * @param bizMsg NWAL1500CMsg
     * @param effDt Effective Date (YYYYMMDD)
     */
    @SuppressWarnings("unchecked")
    public static void createSubRsnCdPulldown(NWAL1500CMsg bizMsg, String effDt) {

        bizMsg.dsOrdRsnCd_CD.clear();
        bizMsg.dsOrdRsnDescTxt_NM.clear();

        S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getDsOrdRsnList(bizMsg, effDt);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdRsnCd_CD.no(i), resultMap.get("DS_ORD_RSN_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdRsnDescTxt_NM.no(i), resultMap.get("DS_ORD_RSN_DESC_TXT"));
            }
        }
    }

    /**
     * create Line Category PullDown
     * @param bizMsg NWAL1500CMsg
     * @param effDt Effective Date (YYYYMMDD)
     * @return Primary Line Catgory Code
     */
    @SuppressWarnings("unchecked")
    public static String createLineCatgPulldown(NWAL1500CMsg bizMsg, String effDt) {

        bizMsg.dsOrdLineCatgCd_CD.clear();
        bizMsg.dsOrdLineCatgDescTxt_NM.clear();

        S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getDsOrdLineCatgList(bizMsg, effDt, DS_ORD_LINE_DRCTN.OUTBOUND);
        String primaryLineCatg = null;

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgCd_CD.no(i), resultMap.get("DS_ORD_LINE_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgDescTxt_NM.no(i), resultMap.get("DS_ORD_LINE_CATG_DESC_TXT"));

                if (!ZYPCommonFunc.hasValue(primaryLineCatg)) {
                    if (ZYPConstant.FLG_ON_Y.equals(resultMap.get("PRIM_LINE_CATG_FLG"))) {
                        primaryLineCatg = resultMap.get("DS_ORD_LINE_CATG_CD");
                        // S21_NA#8450
                        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgCd_LD, primaryLineCatg);
                    }
                }
            }
        }

        return primaryLineCatg;
    }

    /**
     * create Line Category PullDown For RMA
     * @param bizMsg NWAL1500CMsg
     * @param effDt Effective Date (YYYYMMDD)
     * @return Primary Line Catgory Code
     */
    @SuppressWarnings("unchecked")
    public static String createLineCatgPulldownForRma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String effDt) { // 2018/01/29 S21_NA#19808 Mod

        // Mod Start 2017/09/21 S21_NA#16346
        bizMsg.dsOrdLineCatgCd_HR.clear();
        bizMsg.dsOrdLineCatgDescTxt_HR.clear();
        // bizMsg.dsOrdLineCatgCd_CR.clear();
        // bizMsg.dsOrdLineCatgDescTxt_NR.clear();
        // Mod End 2017/09/21 S21_NA#16346

        S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getDsOrdLineCatgList(bizMsg, effDt, DS_ORD_LINE_DRCTN.INBOUND);
        String primaryLineCatg = null;

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                // Mod Start 2017/09/21 S21_NA#16346
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgCd_HR.no(i), resultMap.get("DS_ORD_LINE_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgDescTxt_HR.no(i), resultMap.get("DS_ORD_LINE_CATG_DESC_TXT"));
                // ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgCd_CR.no(i), resultMap.get("DS_ORD_LINE_CATG_CD"));
                // ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgDescTxt_NR.no(i), resultMap.get("DS_ORD_LINE_CATG_DESC_TXT"));
                // Mod End 2017/09/21 S21_NA#16346

                if (!ZYPCommonFunc.hasValue(primaryLineCatg)) {
                    if (ZYPConstant.FLG_ON_Y.equals(resultMap.get("PRIM_LINE_CATG_FLG"))) {
                        primaryLineCatg = resultMap.get("DS_ORD_LINE_CATG_CD");
                        // S21_NA#8450
                        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgCd_RD, primaryLineCatg);
                    }
                }
            }
        }

        // Add Start 2017/09/21 S21_NA#16346
        for (int rmaIdx = 0; rmaIdx < glblMsg.D.getValidCount(); rmaIdx++) { // 2018/01/29 S21_NA#19808 Mod
            NWAL1500_DSMsg rmaMsg = glblMsg.D.no(rmaIdx); // 2018/01/29 S21_NA#19808 Mod

            if (ZYPCommonFunc.hasValue(rmaMsg.origCpoOrdNum_RL) //
                    && ZYPCommonFunc.hasValue(rmaMsg.origCpoDtlLineNum_RL) //
                    && ZYPCommonFunc.hasValue(rmaMsg.origCpoDtlLineSubNum_RL)) {
                S21SsmEZDResult ssmResultLine = NWAL1500Query.getInstance().getDsOrdLineCatgInbound(bizMsg, rmaMsg, effDt);
                if (ssmResult.isCodeNormal()) {
                    List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResultLine.getResultObject();
                    for (int i = 0; i < resultList.size(); i++) {
                        Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                        ZYPEZDItemValueSetter.setValue(rmaMsg.dsOrdLineCatgCd_CR.no(i), resultMap.get("DS_ORD_LINE_CATG_CD"));
                        ZYPEZDItemValueSetter.setValue(rmaMsg.dsOrdLineCatgDescTxt_NR.no(i), resultMap.get("DS_ORD_LINE_CATG_DESC_TXT"));
                    }
                }
            } else {
                setDefaultLineCatgPullDownForNewRma(bizMsg, rmaMsg);
            }
        }
        // Add End 2017/09/21 S21_NA#16346 
        return primaryLineCatg;
    }

    // Add Start 2017/09/21 S21_NA#16346
    /**
     * setDefaultLineCatgPullDownForNewRma
     * @param bizMsg NWAL1500CMsg
     * @param rmaMsg NWAL1500_DCMsg
     */
    public static void setDefaultLineCatgPullDownForNewRma(NWAL1500CMsg bizMsg, NWAL1500_DSMsg rmaMsg) { // 2018/01/29 S21_NA#19808 Mod
        for (int i = 0; i < bizMsg.dsOrdLineCatgCd_HR.length(); i++) {
            ZYPEZDItemValueSetter.setValue(rmaMsg.dsOrdLineCatgCd_CR.no(i), bizMsg.dsOrdLineCatgCd_HR.no(i));
            ZYPEZDItemValueSetter.setValue(rmaMsg.dsOrdLineCatgDescTxt_NR.no(i), bizMsg.dsOrdLineCatgDescTxt_HR.no(i));
        }
    }
    // Add End 2017/09/21 S21_NA#16346
    /**
     * <pre>
     * concat position number and line number.
     * </pre>
     * @param lineMsg line message
     * @return concatenated sring (Position + "." + Line)
     */
    public static String concatLineNum(EZDMsg lineMsg) {

        String dsOrdPosnNum = null;
        BigDecimal dsCpoLineNum = null;
        BigDecimal dsCpoLineSubNum = null;

        if (lineMsg instanceof NWAL1500_BCMsg) {
            NWAL1500_BCMsg bizLineMsg = (NWAL1500_BCMsg) lineMsg;
            dsOrdPosnNum = bizLineMsg.dsOrdPosnNum_LL.getValue();
            dsCpoLineNum = bizLineMsg.dsCpoLineNum_LL.getValue();
            dsCpoLineSubNum = bizLineMsg.dsCpoLineSubNum_LL.getValue();

        } else if (lineMsg instanceof NWAL1500_BSMsg) {
            NWAL1500_BSMsg glblLineMsg = (NWAL1500_BSMsg) lineMsg;
            dsOrdPosnNum = glblLineMsg.dsOrdPosnNum_LL.getValue();
            dsCpoLineNum = glblLineMsg.dsCpoLineNum_LL.getValue();
            dsCpoLineSubNum = glblLineMsg.dsCpoLineSubNum_LL.getValue();

        } else if (lineMsg instanceof NWAL1500_DCMsg) {
            NWAL1500_DCMsg bizLineMsg = (NWAL1500_DCMsg) lineMsg;
            dsOrdPosnNum = bizLineMsg.dsOrdPosnNum_RL.getValue();
            dsCpoLineNum = bizLineMsg.dsCpoLineNum_RL.getValue();
            dsCpoLineSubNum = bizLineMsg.dsCpoLineSubNum_RL.getValue();

        } else if (lineMsg instanceof NWAL1500_DSMsg) {
            NWAL1500_DSMsg glblLineMsg = (NWAL1500_DSMsg) lineMsg;
            dsOrdPosnNum = glblLineMsg.dsOrdPosnNum_RL.getValue();
            dsCpoLineNum = glblLineMsg.dsCpoLineNum_RL.getValue();
            dsCpoLineSubNum = glblLineMsg.dsCpoLineSubNum_RL.getValue();

//        } else if (lineMsg instanceof NWAL1500_JSMsg) { 2018/01/29 S21_NA#19808 Del Start
//            NWAL1500_JSMsg glblLineMsg = (NWAL1500_JSMsg) lineMsg;
//            dsOrdPosnNum = glblLineMsg.dsOrdPosnNum_LL.getValue();
//            dsCpoLineNum = glblLineMsg.dsCpoLineNum_LL.getValue();
//            dsCpoLineSubNum = glblLineMsg.dsCpoLineSubNum_LL.getValue();
//
//        } else if (lineMsg instanceof NWAL1500_KSMsg) {
//            NWAL1500_KSMsg glblLineMsg = (NWAL1500_KSMsg) lineMsg;
//            dsOrdPosnNum = glblLineMsg.dsOrdPosnNum_RL.getValue();
//            dsCpoLineNum = glblLineMsg.dsCpoLineNum_RL.getValue();
//            dsCpoLineSubNum = glblLineMsg.dsCpoLineSubNum_RL.getValue(); 2018/01/29 S21_NA#19808 Del End
        }

        StringBuilder xxLineNum = new StringBuilder();
        xxLineNum.append(dsOrdPosnNum);
        xxLineNum.append(PERIOD);
        xxLineNum.append(dsCpoLineNum);
        if (dsCpoLineSubNum != null) {
            xxLineNum.append(PERIOD);
            xxLineNum.append(dsCpoLineSubNum);
        }
        return xxLineNum.toString();
    }

    /**
     * select merchandise master data with registration status code.
     * @param glblCmpyCd global company code
     * @param mdseCd merchandise code
     * @return merchandise master data
     */
    public static MDSETMsg getMdseWithRgtnStsCd(String glblCmpyCd, String mdseCd) {

        final MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdseCd);

        if (mdseTMsg == null) {
            return null;
        }

        final String rgtnStsCd = mdseTMsg.rgtnStsCd.getValue();
        if (asList(RGTN_STS.READY_FOR_ORDER_TAKING, RGTN_STS.TERMINATED).contains(rgtnStsCd)) {
            return mdseTMsg;
        } else {
            return null;
        }
    }

    /**
     * select merchandise data from merchandise master using NWXMdseTMsgThreadLocalCache#get()
     * @param glblCmpyCd global company code
     * @param mdseCd merchandise code
     * @return merchandise master data
     */
    public static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

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

    /**
     * Derive Default Sales Rep Data
     * @param bizMsg Business Message
     * @return Succeed derive : true
     */
    public static boolean deriveDefaultSlsRep(NWAL1500CMsg bizMsg) {

        // 2016/10/18 S21_NA#15054 Add Start
        bizMsg.slsRepTocCd.clear();
        bizMsg.slsRepTocNm.clear();
        bizMsg.psnNum.clear();
        bizMsg.coaBrCd.clear();
        bizMsg.coaBrDescTxt.clear();
        bizMsg.coaExtnCd.clear();
        bizMsg.coaExtnDescTxt.clear();
        bizMsg.xxScrItem54Txt_CB.clear();
        bizMsg.xxScrItem54Txt_CE.clear();

        // Delete All Sales Credit
        NWAL1500CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);
        // 2016/10/18 S21_NA#15054 Add End

        // call NMZC2600 Default Sales Rep API
        NMZC260001PMsg nMZC260001PMsg = new NMZC260001PMsg();
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustCd, bizMsg.shipToCustCd);
        // 2018/04/02 S21_NA#24860 Add Start
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        // 2018/04/02 S21_NA#24860 Add End
        // 2020/04/27 QC#56638 Add Start
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.billToCustCd, bizMsg.soldToCustLocCd);
        // 2020/04/27 QC#56638 Add End        
        // 2017/11/02 S21_NA#20146 Add Start
        String resultFlg = NWAL1500Query.getInstance().getOrdCatgBizCtx(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
            ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.xxModeCd, XX_MODE_CD_EMSD);
            ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustAcctCd, bizMsg.shipToCustAcctCd);
        }
        // 2017/11/02 S21_NA#20146 Add End

        if (!callDefSlsRepApi(bizMsg, nMZC260001PMsg, bizMsg.shipToCustCd.getValue())) {
            return false;
        }

        String curLineBizTpCd = bizMsg.lineBizTpCd.getValue();
        // 2017/03/09 S21_NA#16855 Add Start
        // 2017/12/12 S21_NA#19804(Sol#349) Mod Start
//        String trtyGrpTpCd = getTrtyGrpTpCdFromDsOrdTpCd(bizMsg);
        String trtyGrpTpTxt = getTrtyGrpTpTxtFromDsOrdTpCd(bizMsg);
        List<String> trtyGrpTpCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(trtyGrpTpTxt)) {
            trtyGrpTpCdList = Arrays.asList(trtyGrpTpTxt.split(","));
        }
        // 2017/12/12 S21_NA#19804(Sol#349) Mod End
        List<String> targetWriterList = getTargetWriterList(bizMsg.glblCmpyCd.getValue());
        // 2017/03/09 S21_NA#16855 Add End
        List<NMZC260001_defSlsRepListPMsg> matchLobSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();
        // 2017/12/12 S21_NA#19804(Sol#349) Add Start
        List<NMZC260001_defSlsRepListPMsg> matchLobRoleSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();
        // 2017/12/12 S21_NA#19804(Sol#349) Add End

        NMZC260001_defSlsRepListPMsgArray defSlsRepMsgArray = nMZC260001PMsg.defSlsRepList;
        // 2017/05/30 2017/05/30 S21_NA#Review structure Lv.2 Add Start
        List<String> psnCdList = new ArrayList<String>(0);
        // 2017/05/30 2017/05/30 S21_NA#Review structure Lv.2 Add End
        // 2017/11/02 S21_NA#20146 Add Start
        List<Map<String, Object>> dummyRepList = new ArrayList<Map<String, Object>>(0);
        // 2017/11/02 S21_NA#20146 Add End
        for (int i = 0; i < defSlsRepMsgArray.getValidCount(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = defSlsRepMsgArray.no(i);

            // 2018/04/18 S21_NA#25418 Add Start
            if(ZYPCommonFunc.hasValue(defSlsRepPMsg.xxRsltFlg) && ZYPConstant.FLG_ON_Y.equals(defSlsRepPMsg.xxRsltFlg.getValue())//
                    && !matchLobRoleSlsRepPMsgList.isEmpty()){
                continue;
            }
            // 2018/04/18 S21_NA#25418 Add End  

            // 2017/05/30 2017/05/30 S21_NA#Review structure Lv.2 Add Start
            if (ZYPCommonFunc.hasValue(defSlsRepPMsg.psnCd)) {
                if (psnCdList.contains(defSlsRepPMsg.psnCd.getValue())) {
                    continue;
                } else {
                    psnCdList.add(defSlsRepPMsg.psnCd.getValue());
                }
            }
            // 2017/05/30 2017/05/30 S21_NA#Review structure Lv.2 Add End
            // 2017/03/09 S21_NA#16855 Mod Start
//            if (defSlsRepPMsg.lineBizTpCd.getValue().equals(curLineBizTpCd)) {
            // 2017/12/12 S21_NA#19804(Sol#349) Mod Start
//            if (defSlsRepPMsg.lineBizTpCd.getValue().equals(curLineBizTpCd)
//                    && (!ZYPCommonFunc.hasValue(trtyGrpTpCd)
//                            || trtyGrpTpCd.equals(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {
            if ((trtyGrpTpCdList.isEmpty() && defSlsRepPMsg.lineBizTpCd.getValue().equals(curLineBizTpCd))
                    || (trtyGrpTpCdList.size() > 0 && trtyGrpTpCdList.contains(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {
            // 2017/12/12 S21_NA#19804(Sol#349) Mod End
            // 2017/03/09 S21_NA#16855 Mod End
                matchLobSlsRepPMsgList.add(defSlsRepPMsg);

                String lineBizRoleTpCd = defSlsRepPMsg.lineBizRoleTpCd.getValue();
                // 2017/03/09 S21_NA#16855 Mod Start
//                if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd)) {
                if (targetWriterList.contains(lineBizRoleTpCd)) {
                // 2017/03/09 S21_NA#16855 Mod End
                    // 2017/12/12 S21_NA#19804(Sol#349) Mod Start
//                    deriveDefaultSlsRepForHdr(bizMsg, defSlsRepPMsg.tocCd.getValue());
                    matchLobRoleSlsRepPMsgList.add(defSlsRepPMsg);
                    // 2017/12/12 S21_NA#19804(Sol#349) Mod End
                }
            }

            // 2017/11/02 S21_NA#20146 Add Start
            String fstBizCtxAttbTxt = NWAL1500Query.getInstance().getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.GL_SGMT_DPLY_ORD_TP, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
            if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                if (fstBizCtxAttbTxt.equals(defSlsRepPMsg.lineBizRoleTpCd.getValue())
                        && ZYPConstant.FLG_OFF_N.equals(defSlsRepPMsg.slsCrQuotFlg.getValue())) {

                    List<Map<String, Object>> slsRepList = getSalesRepList(bizMsg.glblCmpyCd.getValue(), defSlsRepPMsg.tocCd.getValue());
                    if (slsRepList != null && slsRepList.size() != 0) {
                        for (Map<String, Object> slsRepMap : slsRepList) {
                            dummyRepList.add(slsRepMap);
                        }
                    }
                }
            }
            // 2017/11/02 S21_NA#20146 Add End
        }
        // 2017/11/02 S21_NA#20146 Add Start
        clearGLSegment(bizMsg);
        if (dummyRepList != null && dummyRepList.size() != 0) {
            setGLSegment(bizMsg, dummyRepList.get(0));
        }
        // 2017/11/02 S21_NA#20146 Add End

        // 2017/12/12 S21_NA#19804(Sol#349) Add Start
        if (defSlsRepMsgArray.getValidCount() > 0) {
            if (matchLobRoleSlsRepPMsgList.size() == 1) {
                deriveDefaultSlsRepForHdr(bizMsg, matchLobRoleSlsRepPMsgList.get(0).tocCd.getValue());
            } else {
                // 2020/04/27 QC#56638 Add Start
                if (isSlsReqDef(bizMsg)) {
                    bizMsg.setMessageInfo(NWAM0757W);
                } else {
                    bizMsg.setMessageInfo(NWAM0981W);
                }
                // 2020/04/27 QC#56638 Add End
            }
        }
        // 2017/12/12 S21_NA#19804(Sol#349) Add End

        if (matchLobSlsRepPMsgList.size() > 0) {
            // 2017/03/09 S21_NA#16855 Mod Start
//            setDefaultSlsRep(bizMsg, matchLobSlsRepPMsgList);
            setDefaultSlsRep(bizMsg, matchLobSlsRepPMsgList, targetWriterList);
            // 2017/03/09 S21_NA#16855 Mod End
        }

        // 2016/10/21 S21_NA#15489 Del Start
//        // 2016/10/18 S21_NA#15054 Add Start
//        // Set Writer Sales Credit
//        String writerSlsRepTocCd = NWAL1500CommonLogicForSalesCredit.getSlsRepCd(bizMsg, false, MSG_PARAM_SLS_REP_CD);
//        if (!ZYPCommonFunc.hasValue(writerSlsRepTocCd)) {
//            return false;
//        }
//
//        // Delete All Sales Credit
//        NWAL1500CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);
//        NWAL1500CommonLogicForSalesCredit.setWriterSlsCreditInfo(bizMsg, writerSlsRepTocCd);
//        // 2016/10/18 S21_NA#15054 Add End
        // 2016/10/21 S21_NA#15489 Del End

        return true;
    }

    /**
     * Set Default Sales Rep
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg Global Message
     * @param slsRepPMsgList List<NMZC260001_defSlsRepListPMsg>
     */
    // 2017/03/09 S21_NA#16855 Mod Start
//    private static void setDefaultSlsRep(NWAL1500CMsg bizMsg, List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList) {
    private static void setDefaultSlsRep(NWAL1500CMsg bizMsg, List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList, List<String> targetWriterList) {

        // delete Existing Sales Rep Info
        NWAL1500CommonLogicForSalesCredit.delExistingSlsRep(bizMsg);

        // set New Sales Rep
//        NWAL1500CommonLogicForSalesCredit.setNewSlsRep(bizMsg, slsRepPMsgList);
        NWAL1500CommonLogicForSalesCredit.setNewSlsRep(bizMsg, slsRepPMsgList, targetWriterList);
    }
    // 2017/03/09 S21_NA#16855 Mod End

    // 2017/03/09 S21_NA#16855 Add Start

    /**
     * get Target Writer List
     */
    private static List<String> getTargetWriterList(String glblCmpyCd) {

        LINE_BIZ_ROLE_TPTMsg lineBizRoleTpTMsg = new LINE_BIZ_ROLE_TPTMsg();
        lineBizRoleTpTMsg.setSQLID("001");
        lineBizRoleTpTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        lineBizRoleTpTMsg.setConditionValue("primRepRoleFlg01", ZYPConstant.FLG_ON_Y);
        LINE_BIZ_ROLE_TPTMsgArray tMsgArray = (LINE_BIZ_ROLE_TPTMsgArray) EZDTBLAccessor.findByCondition(lineBizRoleTpTMsg);
        List<String> targetWriterList = new ArrayList<String>();
        if (tMsgArray != null && tMsgArray.length() > 0) {
            for (int i = 0; i < tMsgArray.length(); i++) {
                LINE_BIZ_ROLE_TPTMsg tMsg = (LINE_BIZ_ROLE_TPTMsg) tMsgArray.get(i);
                targetWriterList.add(tMsg.lineBizRoleTpCd.getValue());
            }
        }

        return targetWriterList;
    }
    // 2017/03/09 S21_NA#16855 Add End

    // S21_NA#15889 Add Start
    /**
     * Derive Default Sales Rep Data For Config
     * @param bizMsg Business Message
     * @param slctIndex Select Index
     * @param isCalledLineConfig Called Line Config Tab
     */
    public static void deriveDefaultSlsRepForConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctIndex, boolean isCalledLineConfig) { // 2018/01/30 S21_NA#19808 Mod

        // 2018/07/19 S21_NA#27227 Add Start
        CPOTMsg cpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, bizMsg.cpoOrdNum);
        cpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(cpoTMsg);

        if (cpoTMsg != null && ZYPConstant.FLG_ON_Y.equals(cpoTMsg.ordBookFlg.getValue())) {
            return;
        }
        // 2018/07/19 S21_NA#27227 Add End
        // 2018/01/30 S21_NA#19808 Mod bizMsg.A, C => glblMsg.A, C
        String confPosnNum = null;
        String shipToCustCd = null;
        // 2020/04/27 QC#56638 Add Start
        String soldToCustCd = null;
        if (isCalledLineConfig) {
            confPosnNum = glblMsg.A.no(slctIndex).dsOrdPosnNum_LC.getValue();
            shipToCustCd = glblMsg.A.no(slctIndex).shipToCustCd_LC.getValue();
            soldToCustCd = glblMsg.A.no(slctIndex).soldToCustLocCd_LC.getValue();
        } else {
            confPosnNum = glblMsg.C.no(slctIndex).dsOrdPosnNum_RC.getValue();
            shipToCustCd = glblMsg.C.no(slctIndex).shipToCustCd_RC.getValue();
            soldToCustCd = glblMsg.C.no(slctIndex).soldToCustLocCd_RC.getValue();
        }
        // 2020/04/27 QC#56638 Add End        

        // call NMZC2600 Default Sales Rep API
        NMZC260001PMsg nMZC260001PMsg = new NMZC260001PMsg();
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustCd, shipToCustCd);
        // 2018/04/02 S21_NA#24860 Add Start
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        // 2018/04/02 S21_NA#24860 Add End
        // 2020/04/27 QC#56638 Add Start
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.billToCustCd, soldToCustCd);
        // 2020/04/27 QC#56638 Add End
        // 2017/11/02 S21_NA#20146 Add Start
        String resultFlg = NWAL1500Query.getInstance().getOrdCatgBizCtx(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
            ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.xxModeCd, XX_MODE_CD_EMSD);
            ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustAcctCd, glblMsg.A.no(slctIndex).shipToCustAcctCd_LC);
        }
        // 2017/11/02 S21_NA#20146 Add End

        if (!callDefSlsRepApi(bizMsg, nMZC260001PMsg, shipToCustCd)) {
            return;
        }

        String curLineBizTpCd = bizMsg.lineBizTpCd.getValue();
        List<NMZC260001_defSlsRepListPMsg> matchLobSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();

        // 2018/07/19 S21_NA#27227 Add Start
        String trtyGrpTpTxt = getTrtyGrpTpTxtFromDsOrdTpCd(bizMsg);
        List<String> trtyGrpTpCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(trtyGrpTpTxt)) {
            trtyGrpTpCdList = Arrays.asList(trtyGrpTpTxt.split(","));
        }
        List<String> targetWriterList = getTargetWriterList(bizMsg.glblCmpyCd.getValue());
        List<NMZC260001_defSlsRepListPMsg> matchLobRoleSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();
        List<String> psnCdList = new ArrayList<String>(0);
        // 2018/07/19 S21_NA#27227 Add End

        NMZC260001_defSlsRepListPMsgArray defSlsRepMsgArray = nMZC260001PMsg.defSlsRepList;
        for (int i = 0; i < defSlsRepMsgArray.getValidCount(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = defSlsRepMsgArray.no(i);

            // 2018/07/19 S21_NA#27227 Mod Start
            if(ZYPCommonFunc.hasValue(defSlsRepPMsg.xxRsltFlg) && ZYPConstant.FLG_ON_Y.equals(defSlsRepPMsg.xxRsltFlg.getValue())//
                    && !matchLobRoleSlsRepPMsgList.isEmpty()){
                continue;
            }
            if (ZYPCommonFunc.hasValue(defSlsRepPMsg.psnCd)) {
                if (psnCdList.contains(defSlsRepPMsg.psnCd.getValue())) {
                    continue;
                } else {
                    psnCdList.add(defSlsRepPMsg.psnCd.getValue());
                }
            }

            //if (defSlsRepPMsg.lineBizTpCd.getValue().equals(curLineBizTpCd)) {
            if ((trtyGrpTpCdList.isEmpty() && defSlsRepPMsg.lineBizTpCd.getValue().equals(curLineBizTpCd))
                    || (trtyGrpTpCdList.size() > 0 && trtyGrpTpCdList.contains(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {

                matchLobSlsRepPMsgList.add(defSlsRepPMsg);

                String lineBizRoleTpCd = defSlsRepPMsg.lineBizRoleTpCd.getValue();
                if (targetWriterList.contains(lineBizRoleTpCd)) {
                    matchLobRoleSlsRepPMsgList.add(defSlsRepPMsg);
                }
            }
            // 2018/07/19 S21_NA#27227 Mod End
        }

        if (matchLobSlsRepPMsgList.size() > 0) {
            NWAL1500CommonLogicForSalesCredit.delAllSlsCreditInfoForConfig(bizMsg, confPosnNum, isCalledLineConfig);
            NWAL1500CommonLogicForSalesCredit.setNewSlsRepForConfig(bizMsg, matchLobSlsRepPMsgList, confPosnNum, isCalledLineConfig);
        }
    }
    // S21_NA#15889 Add End

    /**
     * Call NMZC2600 Default Sales Rep API
     * @param bizMsg NWAL1500CMsg
     * @param pMsg NMZC260001PMsg
     * @param shipToCustCd Ship To Customer Code
     * @return has API error : false
     */
    private static boolean callDefSlsRepApi(NWAL1500CMsg bizMsg, NMZC260001PMsg pMsg, String shipToCustCd) {

        // call NMZC2600 Dafault Sales Rep API
        new NMZC260001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();

                if (msgId.endsWith("E")) {
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    return false;
                // 2020/04/27 QC#56638 Add Start
                } else if (ZYPCommonFunc.hasValue(shipToCustCd) && msgId.endsWith(NWAM0757W)) {
                    bizMsg.setMessageInfo(NWAM0757W);
                } else if (ZYPCommonFunc.hasValue(pMsg.billToCustCd) && msgId.endsWith(NWAM0981W)) {
                    bizMsg.setMessageInfo(NWAM0981W);
                }
                boolean isShipBase = isSlsReqDef(bizMsg);
                if (isShipBase && ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) && msgId.endsWith("W")) {
                    bizMsg.setMessageInfo(NWAM0757W);
                } else if (!isShipBase && ZYPCommonFunc.hasValue(pMsg.billToCustCd) && msgId.endsWith("W")) {
                    bizMsg.setMessageInfo(NWAM0981W);
                }
                // 2020/04/27 QC#56638 Add End
            }
        }

        return true;
    }

    /**
     * Derive Default Sales Rep Data For Header
     * @param bizMsg NWAL1500CMsg
     * @param primRepTocCd Primary Rep TOC Code
     */
    @SuppressWarnings("unchecked")
    public static void deriveDefaultSlsRepForHdr(NWAL1500CMsg bizMsg, String primRepTocCd) {

        if (ZYPCommonFunc.hasValue(primRepTocCd)) {
            S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getPrimSlsRepInfo(bizMsg, primRepTocCd);

            if (ssmResult.isCodeNormal()) {
                Map<String, String> resultMap = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocNm, resultMap.get("TOC_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocCd, primRepTocCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, resultMap.get("COA_BR_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDescTxt, resultMap.get("COA_BR_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd, resultMap.get("COA_EXTN_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnDescTxt, resultMap.get("COA_EXTN_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CB, resultMap.get("COA_BR_ITEM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CE, resultMap.get("COA_EXTN_ITEM"));
                // 2016/05/12 S21_NA#7861 Mod Start
                //ZYPEZDItemValueSetter.setValue(bizMsg.slsRepPsnCd, resultMap.get("PSN_CD")); // 2016/03/28 Add
                ZYPEZDItemValueSetter.setValue(bizMsg.psnNum, resultMap.get("PSN_NUM"));
                // 2016/05/12 S21_NA#7861 Mod End
            }
        }
    }

    /**
     * Derive Default Carrier Service Level
     * @param bizMsg NWAL1500CMsg
     * @return No API Error : true
     */
    public static boolean deriveDefaultCarrSvcLvl(NWAL1500CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd) || !ZYPCommonFunc.hasValue(bizMsg.shpgSvcLvlCd)) {
            return true;
        }
        
        // 2018/06/25 S21_NA#23726 Del Start
        //if (!FRT_COND.COLLECT.equals(bizMsg.frtCondCd.getValue())) { // QC#13688 2017/02/24 Add
        //    return true;
        //}
        // 2018/06/25 S21_NA#23726 Del End

        NMZC611001PMsg defCarrApiPMsg = callDefaultCarrierApi(bizMsg);

        if (S21ApiUtil.isXxMsgId(defCarrApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(defCarrApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        }

        String vndCd = defCarrApiPMsg.vndCd_O.getValue();
        if (ZYPCommonFunc.hasValue(vndCd)) {
            // QC#13688 2017/02/24 Add Start
            // ZYPEZDItemValueSetter.setValue(bizMsg.carrAcctNum,
            // vndCd);

            // QC#23726 20180628 Mod Start
            if (FRT_COND.COLLECT.equals(bizMsg.frtCondCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.carrAcctNum, defCarrApiPMsg.dsCarrAcctNum.getValue());
            } else {
                bizMsg.carrAcctNum.clear();
            }
            //ZYPEZDItemValueSetter.setValue(bizMsg.carrAcctNum, defCarrApiPMsg.dsCarrAcctNum.getValue());
            // QC#23726 20180628 Mod End
            
            // QC#13688 2017/02/24 Add End
            S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getCarrSvcLvlDescTxt(bizMsg, vndCd);
            if (ssmResult.isCodeNormal()) {
                String carrSvcLvlDescTxt = (String) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlDescTxt, carrSvcLvlDescTxt);
            }
        }

        return true;
    }

    /**
     * Call NMZC6110 Default Carrier API
     * @param bizMsg NWAL1500CMsg
     * @return NMZC611001PMsg
     */
    public static NMZC611001PMsg callDefaultCarrierApi(NWAL1500CMsg bizMsg) {

        NMZC611001PMsg pMsg = new NMZC611001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, bizMsg.shipToCustAcctCd);

        // 2018/12/12 QC#29315 Add Start
        SHIP_TO_CUSTTMsg condition = NWAL1500CommonLogicForCustomer.getShipToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.shipToCustCd.getValue());
        if (condition != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.locNum, condition.locNum);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsBizAreaCd, bizMsg.dsBizAreaCd);
        // 2018/12/12 QC#29315 Add End

        new NMZC611001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        return pMsg;
    }

    /**
     * Derive Default PO No.
     * @param bizMsg NWAL1500CMsg
     * @return No API Error : true
     */
    public static boolean deriveDefaultPO(NWAL1500CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            return true;
        }

        NMZC610001PMsg custInfoApiPMsg = callCustomerInfomationGetApi(bizMsg);

        if (S21ApiUtil.isXxMsgId(custInfoApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        }

        if (custInfoApiPMsg.TransactionRuleList.getValidCount() > 0) {
            String defCustIssPoNum = custInfoApiPMsg.TransactionRuleList.no(0).dsBlktPoNum.getValue();
            if (ZYPCommonFunc.hasValue(defCustIssPoNum)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum, defCustIssPoNum);
            }
        }

        return true;
    }

    /**
     * Call Customer Information Get API.
     * @param bizMsg NWAL1500CMsg
     * @return NMZC610001PMsg
     */
    private static NMZC610001PMsg callCustomerInfomationGetApi(NWAL1500CMsg bizMsg) {

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
        ZYPEZDItemValueSetter.setValue(pMsg.dsTrxRuleTpCd, getTrxRuleTp(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, bizMsg.sellToCustCd);
        // 2019/01/30 QC#30036 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, bizMsg.soldToCustLocCd);
        // 2019/01/30 QC#30036 Add End
        // 2019/02/15 QC#30308 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        // 2019/02/15 QC#30308 Add End
        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        return pMsg;
    }

    private static String getTrxRuleTp(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {

        ORD_CATG_BIZ_CTXTMsg tMsg = getOrdCatgBizCtx(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd);

        if (tMsg == null) {
            return null;
        }
        return tMsg.firstBizCtxAttrbTxt.getValue();
    }

    private static ORD_CATG_BIZ_CTXTMsg getOrdCatgBizCtx(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {

        ORD_CATG_BIZ_CTXTMsg inTMsg = new ORD_CATG_BIZ_CTXTMsg();
        inTMsg.setSQLID("005");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("ordCatgCtxTpCd01A", NWAL1500Constant.EQUIPMENT_ORDER);
        inTMsg.setConditionValue("ordCatgCtxTpCd01B", NWAL1500Constant.SUPPLIES_ORDER);
        inTMsg.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);
        inTMsg.setConditionValue("dsOrdTpCd01", dsOrdTpCd);
        ORD_CATG_BIZ_CTXTMsgArray array = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);

        if (array == null || array.length() == 0) {
            return null;
        }
        return array.no(0);
    }

    /**
     * Derive Default Price List
     * @param bizMsg NWAL1500CMsg
     * @return has API Error : false
     */
    public static boolean deriveDefaultPrcList(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/29 S21_NA#19808

        if (!ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            return true;
        }

        // call NWZC1570 Pricing API
        NWZC157001PMsg prcApiPMsg = callPricingApiOfGetPriceListMode(bizMsg, PRC_CTX_TP.SALES_PRICING, false);
        if (prcApiPMsg == null) {
            return false;
        }

        // set Default Price List
        NWZC157001_xxPrcListPMsgArray prcListArray = prcApiPMsg.xxPrcList;
        if (prcListArray.getValidCount() == 1) {
            String defPrcList = getPrcCatgNm(bizMsg, prcListArray.no(0).prcCatgCd.getValue());

            if (ZYPCommonFunc.hasValue(defPrcList)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm, defPrcList);
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd, prcListArray.no(0).prcCatgCd);
                // 2018/01/29 S21_NA#19808 bizMsg.B, D => glblMsg.B, D
                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).prcCatgNm_LL, defPrcList);
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).prcCatgCd_LL, prcListArray.no(0).prcCatgCd);
                }
                for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).prcCatgNm_RL, defPrcList);
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).prcCatgCd_RL, prcListArray.no(0).prcCatgCd);
                }
            }
        }

        return true;
    }

    /**
     * Call NWZC1570 Pricing API (01:Get Price List Mode)
     * @param bizMsg NWAL1500CMsg
     * @param prcCtxTp Price Ctx Type
     * @param isCallPrcListField Called Price List Field
     * @return NWZC157001PMsg
     */
    public static NWZC157001PMsg callPricingApiOfGetPriceListMode(NWAL1500CMsg bizMsg, String prcCtxTp, boolean isCallPrcListField) {

        NWZC157001PMsg pMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.prcCtxTpCd, prcCtxTp);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.csmpNum, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.dlrRefNum, bizMsg.dlrRefNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.coaBrCd, getCoaBrCd(bizMsg));
        if (isCallPrcListField) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);
        }

        // call NWZC1570 Pricing API
        new NWZC157001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                return null;
            }
        }

        return pMsg;
    }

    /**
     * Get Price Category Desc Text
     * @param bizMsg NWAL1500CMsg
     * @param prcCatgCd Price Category Code
     * @return Price Category Desc Text
     */
    public static String getPrcCatgNm(NWAL1500CMsg bizMsg, String prcCatgCd) {

        PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, prcCatgCd);

        // For Performance QC#11618 Mod Start
        // prcCatgTMsg = (PRC_CATGTMsg) EZDTBLAccessor.findByKey(prcCatgTMsg);
        prcCatgTMsg = (PRC_CATGTMsg) S21CacheTBLAccessor.findByKey(prcCatgTMsg);
        // For Performance QC#11618 Mod End

        if (prcCatgTMsg == null) {
            return null;
        }

        return prcCatgTMsg.prcCatgNm.getValue();
    }

    /**
     * Get COA Branch Code
     * @param bizMsg NWAL1500CMsg
     * @return COA Branch Code
     */
    private static String getCoaBrCd(NWAL1500CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.coaBrDescTxt)) {
            return null;
        }

        final COA_BRTMsg condition = new COA_BRTMsg();
        condition.setSQLID("803");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("coaBrDescTxt01", bizMsg.coaBrDescTxt.getValue());

        COA_BRTMsgArray tMsgArray = (COA_BRTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tMsgArray.getValidCount() == 0) {
            return null;
        }

        return tMsgArray.no(0).coaBrCd.getValue();
    }

    /**
     * Derive Default Warehouse
     * @param bizMsg Business Message
     * @return Succeed drive : true
     */
    public static boolean deriveDefaultWh(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/29 S21_NA#19808 Mod

        // 2018/01/29 S21_NA#19808 Mod bizMsg.B, D => glblMsg.B, D
        // 2018/09/20 S21_NA#28199 Del Start
//        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
//            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
//
//            if (!ZYPCommonFunc.hasValue(lineMsg.mdseCd_LL) || !ZYPCommonFunc.hasValue(lineMsg.ordQty_LL)) {
//                continue;
//            }
//
//            NWZC180001PMsg pMsg = new NWZC180001PMsg();
//            if (!NWAL1500CommonLogic.callDefWhApiForLineConf(pMsg, bizMsg, lineMsg.dsOrdPosnNum_LL.getValue(), lineMsg.mdseCd_LL.getValue(), lineMsg.ordQty_LL.getValue())) {
//                return false;
//            }
//
//            String rtlWhCd = pMsg.rtlWhCd.getValue();
//            String rtlSwhCd = pMsg.rtlSwhCd.getValue();
//
//            ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhCd_LL, rtlWhCd);
//            ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhNm_LL, getRtlWhNm(bizMsg, rtlWhCd));
//            ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhCd_LL, rtlSwhCd);
//            ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhNm_LL, getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
//            ZYPEZDItemValueSetter.setValue(lineMsg.ordLineSrcCd_LL, pMsg.ordLineSrcCd);
//        }
        // 2018/09/20 S21_NA#28199 Del End
        // 2018/09/20 S21_NA#28199 Add Start
        List<NWZC180001PMsg> pMsgList = new ArrayList<NWZC180001PMsg>(0);
        for (int slctConfIndex = 0; slctConfIndex < glblMsg.A.getValidCount(); slctConfIndex++) {
            pMsgList.clear();
            if (!NWAL1500CommonLogic.callDefWhApiForLineConf(pMsgList, bizMsg, glblMsg, glblMsg.A.no(slctConfIndex).dsOrdPosnNum_LC.getValue(), null)) {
                return false;
            }
            NWAL1500CommonLogic.setDefWh(bizMsg, glblMsg, pMsgList);
        }
        // 2018/09/20 S21_NA#28199 Add End

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);

            if (!ZYPCommonFunc.hasValue(rmaLineMsg.mdseCd_RL) || !ZYPCommonFunc.hasValue(rmaLineMsg.ordQty_RL) || !ZYPCommonFunc.hasValue(rmaLineMsg.rtrnRsnCd_RL)) {
                continue;
            }
            // QC#55162 2019/12/16 Add Start
            if (!NWAL1500CommonLogicForLineControl.isNotProtectedFieldForRmaTabByLineSts(rmaLineMsg)) {
                continue;
            }
            // QC#55162 2019/12/16 Add End

            NWZC180001PMsg pMsg = new NWZC180001PMsg();
            if (!NWAL1500CommonLogic.callDefWhApiForRma(pMsg, bizMsg, glblMsg, rmaLineMsg)) { // 2018/09/20 S21_NA#28199 Add Param glblMsg
                return false;
            }

            String rtlWhCd = pMsg.rtlWhCd.getValue();
            String rtlSwhCd = pMsg.rtlSwhCd.getValue();

            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlWhCd_RL, rtlWhCd);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlWhNm_RL, getRtlWhNm(bizMsg, rtlWhCd));
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhCd_RL, rtlSwhCd);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhNm_RL, getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.thirdPtyDspTpCd_RL, pMsg.thirdPtyDspTpCd); // 2016/11/04 S21_NA#15703 Add
        }

        return true;
    }

    // S21_NA#15889 Add Start
    /**
     * Derive Default Warehouse For Congig
     * @param bizMsg Business Message
     * @param slctIndex Select Index
     * @param isCalledLineConfig Called Line Config Tab
     */
    public static void deriveDefaultWhForConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctIndex, boolean isCalledLineConfig) { // 2018/01/29 S21_NA#19808 Mod

        // 2018/01/29 S21_NA#19808 Mod glblMsg.A, B, C, D => glblMsg.A, B, C, D
        if (isCalledLineConfig) {
            String confPosnNum = glblMsg.A.no(slctIndex).dsOrdPosnNum_LC.getValue();
            // 2018/09/20 S21_NA#28199 Del Start
//            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
//                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
//
//                if (!confPosnNum.equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
//                    continue;
//                } else if (!ZYPCommonFunc.hasValue(lineMsg.mdseCd_LL) || !ZYPCommonFunc.hasValue(lineMsg.ordQty_LL)) {
//                    continue;
//                }
//
//                NWZC180001PMsg pMsg = new NWZC180001PMsg();
//                if (!NWAL1500CommonLogic.callDefWhApiForLineConf(pMsg, bizMsg, lineMsg.dsOrdPosnNum_LL.getValue(), lineMsg.mdseCd_LL.getValue(), lineMsg.ordQty_LL.getValue())) {
//                    return;
//                }
//
//                String rtlWhCd = pMsg.rtlWhCd.getValue();
//                String rtlSwhCd = pMsg.rtlSwhCd.getValue();
//
//                ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhCd_LL, rtlWhCd);
//                ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhNm_LL, getRtlWhNm(bizMsg, rtlWhCd));
//                ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhCd_LL, rtlSwhCd);
//                ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhNm_LL, getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
//                ZYPEZDItemValueSetter.setValue(lineMsg.ordLineSrcCd_LL, pMsg.ordLineSrcCd);
//            }
            // 2018/09/20 S21_NA#28199 Del End
            // 2018/09/20 S21_NA#28199 Add Start
            List<NWZC180001PMsg> pMsgList = new ArrayList<NWZC180001PMsg>(0);
            if (!NWAL1500CommonLogic.callDefWhApiForLineConf(pMsgList, bizMsg, glblMsg, confPosnNum, null)) {
                return;
            }
            NWAL1500CommonLogic.setDefWh(bizMsg, glblMsg, pMsgList);
            // 2018/09/20 S21_NA#28199 Add End
        } else {
            String confPosnNum = glblMsg.C.no(slctIndex).dsOrdPosnNum_RC.getValue();
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);

                if (!confPosnNum.equals(rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                    continue;
                } else if (!ZYPCommonFunc.hasValue(rmaLineMsg.mdseCd_RL) || !ZYPCommonFunc.hasValue(rmaLineMsg.ordQty_RL) || !ZYPCommonFunc.hasValue(rmaLineMsg.rtrnRsnCd_RL)) {
                    continue;
                }

                NWZC180001PMsg pMsg = new NWZC180001PMsg();
                if (!NWAL1500CommonLogic.callDefWhApiForRma(pMsg, bizMsg, glblMsg, rmaLineMsg)) { // 2018/09/20 S21_NA#28199 Add Param glblMsg
                    return;
                }

                String rtlWhCd = pMsg.rtlWhCd.getValue();
                String rtlSwhCd = pMsg.rtlSwhCd.getValue();

                ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlWhCd_RL, rtlWhCd);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlWhNm_RL, getRtlWhNm(bizMsg, rtlWhCd));
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhCd_RL, rtlSwhCd);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhNm_RL, getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.thirdPtyDspTpCd_RL, pMsg.thirdPtyDspTpCd);
            }
        }
    }
    // S21_NA#15889 Add End

    // 2018/09/20 S21_NA#28199 Del Start
//    /**
//     * Call NWZC1800 Default WH API For Line Config
//     * @param pMsg NWZC180001PMsg
//     * @param bizMsg NWAL1500CMsg
//     * @param dsOrdPosnNum DS Order Position Number
//     * @param mdseCd MDSE Code
//     * @param ordQty Order Qty
//     * @return has API error : false
//     */
//    public static boolean callDefWhApiForLineConf(NWZC180001PMsg pMsg, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String dsOrdPosnNum, String mdseCd, BigDecimal ordQty) { // 2018/09/20 S21_NA#28199 Add Param glblMsg
//        return callDefWhApiForLineConf(pMsg, bizMsg, glblMsg, dsOrdPosnNum, mdseCd, ordQty, null); // 2018/09/20 S21_NA#28199 Add Param glblMsg
//    }
    // 2018/09/20 S21_NA#28199 Del End

    /**
     * <pre>
     * Call NWZC1800 Default WH API For Line Config
     * @param pMsg NWZC180001PMsg
     * @param bizMsg NWAL1500CMsg
     * @param dsOrdPosnNum DS Order Position Number
     * @param mdseCd MDSE Code
     * @param ordQty Order Qty
     * @param svcMachMstrPk Service Machine Master Primary Key
     * @return has API error : false
     * </pre>
     */
//    public static boolean callDefWhApiForLineConf(NWZC180001PMsg pMsg, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String dsOrdPosnNum, String mdseCd, BigDecimal ordQty, BigDecimal svcMachMstrPk) { 2018/09/20 S21_NA#28199 Change I/F
    public static boolean callDefWhApiForLineConf(List<NWZC180001PMsg> pMsgList, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String dsOrdPosnNum, NWAL1500_BSMsg lineMsg) {

        // 2018/09/20 S21_NA#28199 Modify Logic without any comments.
        NWAL1500_ASMsg configMsg = getParentConfig(glblMsg.A, dsOrdPosnNum);
        // 2018/09/27 S21_NA#28199-2 Mod Start
//        boolean isOrderNew = NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), //
//                configMsg.configTpCd_LC.getValue(), //
//                CONFIG_CATG.OUTBOUND, //
//                true, //
//                false, //
//                false);
        boolean isOrderNew = true;
        // 2018/09/27 S21_NA#28199-2 Mod End

        NWAL1500_BSMsg baseCmptLineMsg = null;
        String confPostCd = configMsg.shipToPostCd_LC.getValue();

        if (isOrderNew) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NWAL1500_BSMsg curLineMsg = glblMsg.B.no(i);
                if (S21StringUtil.isEquals(dsOrdPosnNum, curLineMsg.dsOrdPosnNum_LL.getValue()) //
                        && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, curLineMsg.baseCmptFlg_LL.getValue())) {
                    baseCmptLineMsg = curLineMsg;
                    break;
                }
            }
        }
        if (pMsgList == null) {
            pMsgList = new ArrayList<NWZC180001PMsg>(0);
        }
        if (lineMsg == null) {
            for (int i = 0; i< glblMsg.B.getValidCount(); i++) {
                lineMsg = glblMsg.B.no(i);
                if (!S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                    continue;
                }
                if (!ZYPCommonFunc.hasValue(lineMsg.mdseCd_LL) // 2018/09/27 S21_NA#28199-2 Add Condition
                        || !needsWh(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue()) //
                        || !ZYPCommonFunc.hasValue(configMsg.shipToCustCd_LC)) {
                    continue;
                }
                NWZC180001PMsg pMsg = createDfltWhApiMsg(bizMsg, lineMsg, confPostCd, baseCmptLineMsg);
                pMsgList.add(pMsg);
            }
        } else {
            if (!ZYPCommonFunc.hasValue(lineMsg.mdseCd_LL) // 2018/09/27 S21_NA#28199-2 Add Condition
                    || !needsWh(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue()) //
                    || !ZYPCommonFunc.hasValue(configMsg.shipToCustCd_LC)) {
                return true;
            }
            NWZC180001PMsg pMsg = createDfltWhApiMsg(bizMsg, lineMsg, confPostCd, baseCmptLineMsg);
            pMsgList.add(pMsg);
        }

        // call NWZC1800 Default WH API
        if (!pMsgList.isEmpty()) {
            new NWZC180001().execute(pMsgList, ONBATCH_TYPE.ONLINE);
        }

        for (NWZC180001PMsg pMsg : pMsgList) {
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
    
                    if (msgId.endsWith("E")) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
    
    // 2018/09/26 S21_NA#28482 Add Start
    /**
     * Call NWZC1800 Default WH API For RMA
     * @param pMsg NWZC180001PMsg
     * @param bizMsg NWAL1500CMsg
     * @param dsOrdPosnNum DS Order Position Number
     * @param mdseCd MDSE Code
     * @param ordQty Order Qty
     * @param svcMachMstrPk Service Machine Master Primary Key
     * @return has API error : false
     * </pre>
     */
    public static boolean callDefWhApiForRma(List<NWZC180001PMsg> pMsgList, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String dsOrdPosnNum, String mdseCd, BigDecimal ordQty, BigDecimal svcMachMstrPk) { 

        NWZC180001PMsg pMsg = new NWZC180001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_INBD);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, getConfPostCd(glblMsg, dsOrdPosnNum, true));
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, ordQty);
        String rtrnRsnCd = "";
        for (int i = 0; i < bizMsg.rtrnRsnCd_CD.length(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.rtrnRsnCd_CD.no(i))) {
                rtrnRsnCd = bizMsg.rtrnRsnCd_CD.no(i).getValue();
                break;
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.dsRtrnRsnCd, rtrnRsnCd);
        if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
        }
        pMsgList.add(pMsg);
        // call NMZC2600 Dafault Sales Rep API
        new NWZC180001().execute(pMsgList, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        }
        return true;
    }
    // 2018/09/26 S21_NA#28482 Add End

    // 
    /**
     * Call NWZC1800 Default WH API For RMA
     * @param pMsg NWZC180001PMsg
     * @param bizMsg NWAL1500CMsg
     * @param rmaLineMsg NWAL1500_DCMsg
     * @return has API error : false
     */
    public static boolean callDefWhApiForRma(NWZC180001PMsg pMsg, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_DSMsg rmaLineMsg) { // 2018/01/29 S21_NA#19808 Mod  // 2018/09/20 S21_NA#28199 Add Param glblMsg

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_INBD);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        // Mod Start 2017/02/01 QC#17257
        if (ZYPCommonFunc.hasValue(rmaLineMsg.rtrnRsnCd_RL)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsRtrnRsnCd, rmaLineMsg.rtrnRsnCd_RL);
        } else {
            String rtrnRsnCd = "";
            for (int i = 0; i < bizMsg.rtrnRsnCd_CD.length(); i++) {
                if (ZYPCommonFunc.hasValue(bizMsg.rtrnRsnCd_CD.no(i))) {
                    rtrnRsnCd = bizMsg.rtrnRsnCd_CD.no(i).getValue();
                    ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtrnRsnCd_RL, rtrnRsnCd);
                    break;
                }
            }
            ZYPEZDItemValueSetter.setValue(pMsg.dsRtrnRsnCd, rtrnRsnCd);
        }
        // Mod End 2017/02/01 QC#17257
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, rmaLineMsg.mdseCd_RL);
        // 2018/09/20 S21_NA#28199 Mod Start
//        ZYPEZDItemValueSetter.setValue(pMsg.postCd, getConfPostCd(bizMsg, rmaLineMsg.dsOrdPosnNum_RL.getValue(), false));
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, getConfPostCd(glblMsg, rmaLineMsg.dsOrdPosnNum_RL.getValue(), false));
        // 2018/09/20 S21_NA#28199 Mod End
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, rmaLineMsg.ordQty_RL);
        // Add Start 2017/01/30 QC#17186
        BigDecimal svcMachMstrPk = rmaLineMsg.svcMachMstrPk_RL.getValue();
        if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
        }
        // Add End 2017/01/30 QC#17186

        // call NMZC2600 Dafault Sales Rep API
        new NWZC180001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Get Config Post Code
     * @param glblMsg NWAL1500SMsg
     * @param dsOrdPosnNum DS Order Position Number
     * @param isLineConfFlag Called Line Config
     */
    private static String getConfPostCd(NWAL1500SMsg glblMsg, String dsOrdPosnNum, boolean isLineConfFlag) { // 2018/09/20 S21_NA#28199 Mod bizMsg -> glblMsg

        // 2018/09/20 S21_NA#28199 bizMsg -> glblMsg without any comments.
        if (isLineConfFlag) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                NWAL1500_ASMsg aSMsg = glblMsg.A.no(i);
                if (dsOrdPosnNum.equals(aSMsg.dsOrdPosnNum_LC.getValue())) {
                    return aSMsg.shipToPostCd_LC.getValue();
                }
            }
        } else {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                NWAL1500_CSMsg cSMsg = glblMsg.C.no(i);
                if (dsOrdPosnNum.equals(cSMsg.dsOrdPosnNum_RC.getValue())) {
                    return cSMsg.shipToPostCd_RC.getValue();
                }
            }
        }

        return null;
    }

    /**
     * Get Retail Warehouse Name
     * @param bizMsg NWAL1500CMsg
     * @param rtlWhCd Retail Warehouse Code
     * @return Retail Warehouse Name
     */
    public static String getRtlWhNm(NWAL1500CMsg bizMsg, String rtlWhCd) {

        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, rtlWhCd);
        rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);

        if (rtlWhTMsg == null) {
            return null;
        }

        return rtlWhTMsg.rtlWhNm.getValue();
    }

    /**
     * Get Retail Sub Warehouse Name
     * @param bizMsg NWAL1500CMsg
     * @param rtlWhCd Retail Warehouse Code
     * @param rtlSwhCd Retail Sub Warehouse Code
     * @return Retail Sub Warehouse Name
     */
    public static String getRtlSubWhNm(NWAL1500CMsg bizMsg, String rtlWhCd, String rtlSwhCd) {

        RTL_SWHTMsg rtlSubWhTMsg = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlSubWhTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlSubWhTMsg.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(rtlSubWhTMsg.rtlSwhCd, rtlSwhCd);
        rtlSubWhTMsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSubWhTMsg);

        if (rtlSubWhTMsg == null) {
            return null;
        }

        return rtlSubWhTMsg.rtlSwhNm.getValue();
    }

    /**
     * <pre>
     * Set Base Component flag on Line data related to parameter NWAL1500_ACMsg (Config data)
     * If some line was set up Base Component flag already, this method do nothing.
     * </pre>
     * @param bizMsg Business Message
     * @param slctConfIndex index of target CPO Config (the index of bizMsg.A)
     */
//    public static void setBaseComponentFlag(NWAL1500CMsg bizMsg, int slctConfIndex, int slctLineIndex) {
//    public static void setBaseComponentFlag(NWAL1500CMsg bizMsg, int slctConfIndex) {
    public static int setBaseComponentFlag(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg confMsg) { // 2018/01/11 S21_NA#23329 Mod Interface // 2018/01/29 S21_NA#19808 Mod

        // 2017/02/24 S21_NA#17714 Add Start
        boolean isReset = setAllNForBaseCompFLg(bizMsg, glblMsg, null); // 2018/01/29 S21_NA#19808 Mod
        if (isReset) {
            return -1;
        }
        // 2017/02/24 S21_NA#17714 Add End
        // 2018/01/29 S21_NA#19808 Del Start
//        NWAL1500_ACMsg confMsg = bizMsg.A.no(slctConfIndex);
        // 2018/01/29 S21_NA#19808 Del End
        // 2016/07/11 S21_NA#7821 Mod Start
//        NWAL1500_BCMsg lineMsg = bizMsg.B.no(slctLineIndex);
//
//        boolean srchFlg = false;
//        String dsOrdPosnNum = confMsg.dsOrdPosnNum_LC.getValue();
//        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
//        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, bizMsg.B.no(slctLineIndex).mdseCd_LL.getValue());
//
//        if (mdseTMsg == null || S21StringUtil.isEquals(mdseTMsg.mdseTpCd.getValue(), MDSE_TP.SET)) { // S21_NA#1724
//            lineMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
//            return;
//        }
//        String mdseCd = mdseTMsg.mdseCd.getValue();
//
//        NWAL1500_BCMsg mainMachineBcMsg = null;
//        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            mainMachineBcMsg = bizMsg.B.no(i);
//            // 2016/04/11 S21_NA#3236-3 Add Start
//            if (NWAL1500CommonLogic.isCancelLine(bizMsg, mainMachineBcMsg)) {
//                continue;
//            }
//            // 2016/04/11 S21_NA#3236-3 Add Start
//            // 2016/07/11 S21_NA#7821 Add Start
//            if (isForDelete && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mainMachineBcMsg.xxChkBox_LL.getValue())) {
//                continue;
//            }
//            // 2016/07/11 S21_NA#7821 Add End
//            if (dsOrdPosnNum.equals(mainMachineBcMsg.dsOrdPosnNum_LL.getValue()) //
//                    && ZYPConstant.FLG_ON_Y.equals(mainMachineBcMsg.baseCmptFlg_LL.getValue()) //
//                    && i != slctLineIndex) {
//                srchFlg = true;
//                break;
//            }
//        }
//
//        if (srchFlg) {
//            MDSETMsg mdseTMsgMain = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mainMachineBcMsg.mdseCd_LL.getValue());
//            String baseMdseCd = mdseTMsgMain.mdseCd.getValue();
//            boolean isBaseMdseMain = isMainMachine(glblCmpyCd, baseMdseCd, false);
//            if (isBaseMdseMain) {
//                lineMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
//            } else {
//                boolean isMainMachine = isMainMachine(glblCmpyCd, mdseCd, false);
//                if (isMainMachine) {
//                    lineMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_ON_Y);
//                    mainMachineBcMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
//                } else {
//                    lineMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
//                }
//            }
//        } else {
//            boolean isMainMachine = isMainMachine(glblCmpyCd, mdseCd, true);
//            if (isMainMachine) {
//                lineMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_ON_Y);
//            } else {
//                lineMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
//            }
//        }

        // 2016/08/04 S21_NA#13012 Add Start
        int baseCmptIdx = NWAL1500CommonLogicForSaveSubmit.getInstance().getBaseComponentIndex(bizMsg, glblMsg, confMsg, null, false); // 2018/01/29 S21_NA#19808 Mod
        // 2016/08/04 S21_NA#13012 Add Endt

        String dsOrdPosnNum = confMsg.dsOrdPosnNum_LC.getValue();
        // 2016/08/04 S21_NA#13012 Del Start
//        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
//
//        int valsetIdx = -1;
//        int instlBaseIdx = -1;
//        Map<String, Map<String, String>> mdseBaseCmptFlgMap = new HashMap<String, Map<String, String>>();
//        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            if (!S21StringUtil.isEquals(dsOrdPosnNum, bizMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
//                continue;
//            }
//            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, bizMsg.B.no(i).mdseCd_LL.getValue());
//            if (mdseTMsg == null) {
//                continue;
//            }
//            String mdseCd = mdseTMsg.mdseCd.getValue();
//            Map<String, String> analyzeMap = mdseBaseCmptFlgMap.get(mdseCd);
//            String instlBaseCtrlFlg = null;
//            String mdseTpCtxTpCd = null;
//            if (analyzeMap == null) {
//                S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getBaseComponentFlag(glblCmpyCd, mdseCd);
//                if (ssmResult.isCodeNormal()) {
//                    analyzeMap = (Map<String, String>) ssmResult.getResultObject();
//                    mdseBaseCmptFlgMap.put(mdseCd, (Map<String, String>) ssmResult.getResultObject());
//                    instlBaseCtrlFlg = analyzeMap.get("INSTL_BASE_CTRL_FLG");
//                    mdseTpCtxTpCd = analyzeMap.get("MDSE_TP_CTX_TP_CD");
//                }
//            } else {
//                instlBaseCtrlFlg = analyzeMap.get("INSTL_BASE_CTRL_FLG");
//                mdseTpCtxTpCd = analyzeMap.get("MDSE_TP_CTX_TP_CD");
//            }
//            if (valsetIdx == -1 && ZYPCommonFunc.hasValue(mdseTpCtxTpCd)) {
//                valsetIdx = i;
//            } else if (instlBaseIdx == -1 && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instlBaseCtrlFlg)) {
//                instlBaseIdx = i;
//            }
//        }
//
//        int baseCmptIdx = -1;
//        if (valsetIdx >= 0) {
//            baseCmptIdx = valsetIdx;
//        } else if (instlBaseIdx >= 0) {
//            baseCmptIdx = instlBaseIdx;
//        }
        // 2016/08/04 S21_NA#13012 Del End

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) { // 2018/01/29 S21_NA#19808 Mod bizMsg.B => glblMsg.B
            if (!S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
                continue;
            }
            if (i == baseCmptIdx) {
                glblMsg.B.no(i).baseCmptFlg_LL.setValue(ZYPConstant.FLG_ON_Y);
                glblMsg.B.no(i).dplyLineRefNum_LL.clear();
            } else {
                glblMsg.B.no(i).baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
        // 2016/07/11 S21_NA#7821 Mod End
        // 2018/01/11 S21_NA#23329 Add Start
        return baseCmptIdx;
        // 2018/01/11 S21_NA#23329 Add End
    }

    // 2016/07/11 S21_NA#7821 Add Start
    /**
     * <pre>
     * set Base Component flag for RMA line.
     * @param bizMsg Business Message
     * @param slctConfIndex selected Config for RMA index
     * @param baseComponentMap Base Component Map
     * @param delFlg true: delete Mode false: ordinal Mode
     * </pre>
     */
    public static void setBaseComponentFlagForRma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, Map<String, Map<String, String>> baseComponentMap, boolean delFlg) { // 2018/01/29 S21_NA#19808 Mod

        // 2018/01/29 S21_NA#19808 Mod bizMsg.C, D => glblMsg.C, D
        NWAL1500_CSMsg confMsg = glblMsg.C.no(slctConfIndex);
        String dsOrdPosnNum = confMsg.dsOrdPosnNum_RC.getValue();
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        // 2018/05/20 S21_NA#25604 Del Start
//
//        int valsetIdx = -1;
//        int instlBaseIdx = -1;
//        // Map<String, Map<String, String>> mdseBaseCmptFlgMap = new HashMap<String, Map<String, String>>();
//        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
//            if (!S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue())) {
//                continue;
//            }
//            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, glblMsg.D.no(i).mdseCd_RL.getValue());
//            if (mdseTMsg == null) {
//                continue;
//            }
//            // 2016/08/04 S21_NA#13012 Add Start
//            if (S21StringUtil.isEquals(MDSE_TP.SET, mdseTMsg.mdseTpCd.getValue()) //
//                    || !S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.invtyCtrlFlg.getValue())) {
//                continue;
//            }
//            if (delFlg && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, glblMsg.D.no(i).xxChkBox_RL.getValue())) {
//                continue;
//            }
//            // 2016/08/04 S21_NA#13012 Add Start
//            String mdseCd = mdseTMsg.mdseCd.getValue();
//
//            String instlBaseCtrlFlg = null;
//            String mdseTpCtxTpCd = null;
//
//            if (baseComponentMap != null) {
//                Map<String, String> baseComponent = baseComponentMap.get(mdseCd);
//                if (baseComponent == null) {
//                    S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getBaseComponentFlag(glblCmpyCd, mdseCd);
//                    if (ssmResult.isCodeNormal()) {
//                        baseComponent = (Map<String, String>) ssmResult.getResultObject();
//                        baseComponentMap.put(mdseCd, baseComponent);
//                        instlBaseCtrlFlg = baseComponent.get("INSTL_BASE_CTRL_FLG");
//                        mdseTpCtxTpCd = baseComponent.get("MDSE_TP_CTX_TP_CD");
//                    }
//                } else {
//                    instlBaseCtrlFlg = baseComponent.get("INSTL_BASE_CTRL_FLG");
//                    mdseTpCtxTpCd = baseComponent.get("MDSE_TP_CTX_TP_CD");
//                }
//            } else {
//                S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getBaseComponentFlag(glblCmpyCd, mdseCd);
//                if (ssmResult.isCodeNormal()) {
//                    Map<String, String> baseComponent = (Map<String, String>) ssmResult.getResultObject();
//                    instlBaseCtrlFlg = baseComponent.get("INSTL_BASE_CTRL_FLG");
//                    mdseTpCtxTpCd = baseComponent.get("MDSE_TP_CTX_TP_CD");
//                }
//            }
//
//            if (valsetIdx == -1 && ZYPCommonFunc.hasValue(mdseTpCtxTpCd)) {
//                valsetIdx = i;
//            } else if (instlBaseIdx == -1 && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, instlBaseCtrlFlg)) {
//                instlBaseIdx = i;
//            }
//        }
//
//        int baseCmptIdx = -1;
//        if (valsetIdx >= 0) {
//            baseCmptIdx = valsetIdx;
//        } else if (instlBaseIdx >= 0) {
//            baseCmptIdx = instlBaseIdx;
//        }
//
//        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
//            if (!S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue())) {
//                continue;
//            }
//            if (i == baseCmptIdx) {
//                glblMsg.D.no(i).baseCmptFlg_RL.setValue(ZYPConstant.FLG_ON_Y);
//                glblMsg.D.no(i).dplyLineRefNum_RL.clear();
//            } else {
//                glblMsg.D.no(i).baseCmptFlg_RL.setValue(ZYPConstant.FLG_OFF_N);
//            }
//        }
        // 2018/05/20 S21_NA#25604 Del End
        // 2018/05/20 S21_NA#25604 Add Start
        boolean isProcDelete = NWAL1500CommonLogicForLineConfig.isProcDelete(bizMsg);
        boolean isProcCancel = NWAL1500CommonLogicForLineConfig.isProcCancel(bizMsg); // 2018/09/11 S21_NA#25352 Add
        List<String> itemCdList = new ArrayList<String>(0);
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            if (!S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue())) {
                continue;
            }
            if (S21StringUtil.isEquals(RTRN_LINE_STS.CANCELLED, glblMsg.D.no(i).ordLineStsCd_RL.getValue())) {
                continue;
            }
            // 2018/09/11 S21_NA#25352 Mod Start
            // if (isProcDelete && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, glblMsg.D.no(i).xxChkBox_RL.getValue())) {
            if ((isProcDelete || isProcCancel) && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, glblMsg.D.no(i).xxChkBox_RL.getValue())) {
                // 2018/09/11 S21_NA#25352 Mod End
                continue;
            }
            itemCdList.add(glblMsg.D.no(i).mdseCd_RL.getValue());
        }
        String baseCmptMdseCd = NWXC150001SvcMdlFunc.getBaseCmptItem(glblCmpyCd, itemCdList);
        if (!ZYPCommonFunc.hasValue(baseCmptMdseCd)) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                if (!S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue())) {
                    continue;
                }
                glblMsg.D.no(i).baseCmptFlg_RL.setValue(ZYPConstant.FLG_OFF_N);
            }
            return;
        }
        boolean isSetBaseCmptFlg = false;
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            if (!S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue())) {
                continue;
            }
            if (!isSetBaseCmptFlg //
                    && NWXC150001DsCheck.isNearEqualsItem(glblCmpyCd, baseCmptMdseCd, glblMsg.D.no(i).mdseCd_RL.getValue())) {
                glblMsg.D.no(i).baseCmptFlg_RL.setValue(ZYPConstant.FLG_ON_Y);
                glblMsg.D.no(i).dplyLineRefNum_RL.clear(); // 2018/09/11 S21_NA#25352 Add
                isSetBaseCmptFlg = true;
            } else {
                glblMsg.D.no(i).baseCmptFlg_RL.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
        // 2018/05/20 S21_NA#25604 Add End
    }
    // 2016/07/11 S21_NA#7821 Add End
    // 2016/07/11 S21_NA#7821 Del Start
//    @SuppressWarnings("unchecked")
//    private static boolean isMainMachine(String glblCmpyCd, String mdseCd, boolean isCheckInstlBaseCtrlFlg) {
//
//        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getBaseComponentFlag(glblCmpyCd, mdseCd);
//        Map<String, String> rsltMap = (Map<String, String>) ssmRslt.getResultObject();
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
    // 2016/07/11 S21_NA#7821 Del Start

    // S21_NA#12598 Add Start
    /**
     * <pre>
     * Compare EZDCItem and EZDSItem.
     * the type of orig and type of target should be same.
     * if both have no value, this method returns true.
     * if one of them does'nt have value, this method returns false;
     * ex)
     * OK: orig: EZDCStringItem. target: EZDSStringItem
     * NG: orig: EZDCBigDecimalItem. target: EZDSStringItem
     * </pre>
     * @param orig compare original (EZDCItem; EZDCStringItem, EZDCDateItem, EZDCBigDecimalItem)
     * @param target compare to (EZDSItem; EZDSStringItem, EZDSDateItem, EZDSBigDecimalItem)
     * @return true: orig and target have same value false: orig and target have different value
     */
    public static boolean isEqualsCheckboxEZDItem(EZDCItem orig, EZDSItem target) {
        if (!ZYPCommonFunc.hasValue(orig)) {
            ZYPEZDItemValueSetter.setValue((EZDCStringItem) orig, ZYPConstant.FLG_OFF_N);
        }

        return isEqualsEZDItem(orig, target);
    }
    // S21_NA#12598 Add End
    // 2018/01/29 S21_NA#19808 Add Start
    /**
     * <pre>
     * Compare EZDCItem and EZDSItem.
     * the type of orig and type of target should be same.
     * if both have no value, this method returns true.
     * if one of them does'nt have value, this method returns false;
     * ex)
     * OK: orig: EZDCStringItem. target: EZDSStringItem
     * NG: orig: EZDCBigDecimalItem. target: EZDSStringItem
     * </pre>
     * @param orig compare original (EZDCItem; EZDCStringItem, EZDCDateItem, EZDCBigDecimalItem)
     * @param target compare to (EZDSItem; EZDSStringItem, EZDSDateItem, EZDSBigDecimalItem)
     * @return true: orig and target have same value false: orig and target have different value
     */
    public static boolean isEqualsCheckboxEZDItem(EZDSItem orig, EZDSItem target) {
        if (!ZYPCommonFunc.hasValue(orig)) {
            ZYPEZDItemValueSetter.setValue((EZDSStringItem) orig, ZYPConstant.FLG_OFF_N);
        }

        return isEqualsEZDItem(orig, target);
    }
    // 2018/01/29 S21_NA#19808 Add End
    /**
     * <pre>
     * Compare EZDCItem and EZDSItem.
     * the type of orig and type of target should be same.
     * if both have no value, this method returns true.
     * if one of them does'nt have value, this method returns false;
     * ex)
     * OK: orig: EZDCStringItem. target: EZDSStringItem
     * NG: orig: EZDCBigDecimalItem. target: EZDSStringItem
     * </pre>
     * @param orig compare original (EZDCItem; EZDCStringItem, EZDCDateItem, EZDCBigDecimalItem)
     * @param target compare to (EZDSItem; EZDSStringItem, EZDSDateItem, EZDSBigDecimalItem)
     * @return true: orig and target have same value false: orig and target have different value
     */
    public static boolean isEqualsEZDItem(EZDCItem orig, EZDSItem target) {

        if (!ZYPCommonFunc.hasValue(orig) && !ZYPCommonFunc.hasValue(target)) {
            return true;
        }
        if ((!ZYPCommonFunc.hasValue(orig) && ZYPCommonFunc.hasValue(target)) || (ZYPCommonFunc.hasValue(orig) && !ZYPCommonFunc.hasValue(target))) {
            return false;
        }
        if (orig instanceof EZDCStringItem && target instanceof EZDSStringItem) {
            return ((EZDCStringItem) orig).getValue().equals(((EZDSStringItem) target).getValue());
        }
        if (orig instanceof EZDCDateItem && target instanceof EZDSDateItem) {
            return ((EZDCDateItem) orig).getValue().equals(((EZDSDateItem) target).getValue());
        }
        if (orig instanceof EZDCBigDecimalItem && target instanceof EZDSBigDecimalItem) {
            int compRslt = ((EZDCBigDecimalItem) orig).getValue().compareTo(((EZDSBigDecimalItem) target).getValue());
            if (0 == compRslt) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    // 2018/01/29 S21_NA#19808 Add Start
    /**
     * <pre>
     * Compare EZDSItem and EZDSItem.
     * the type of orig and type of target should be same.
     * if both have no value, this method returns true.
     * if one of them does'nt have value, this method returns false;
     * ex)
     * OK: orig: EZDSStringItem. target: EZDSStringItem
     * NG: orig: EZDSBigDecimalItem. target: EZDSStringItem
     * </pre>
     * @param orig compare original (EZDSItem; EZDSStringItem, EZDSDateItem, EZDSBigDecimalItem)
     * @param target compare to (EZDSItem; EZDSStringItem, EZDSDateItem, EZDSBigDecimalItem)
     * @return true: orig and target have same value false: orig and target have different value
     */
    public static boolean isEqualsEZDItem(EZDSItem orig, EZDSItem target) {

        if (!ZYPCommonFunc.hasValue(orig) && !ZYPCommonFunc.hasValue(target)) {
            return true;
        }
        if ((!ZYPCommonFunc.hasValue(orig) && ZYPCommonFunc.hasValue(target)) || (ZYPCommonFunc.hasValue(orig) && !ZYPCommonFunc.hasValue(target))) {
            return false;
        }
        if (orig instanceof EZDSStringItem && target instanceof EZDSStringItem) {
            return ((EZDSStringItem) orig).getValue().equals(((EZDSStringItem) target).getValue());
        }
        if (orig instanceof EZDSDateItem && target instanceof EZDSDateItem) {
            return ((EZDSDateItem) orig).getValue().equals(((EZDSDateItem) target).getValue());
        }
        if (orig instanceof EZDSBigDecimalItem && target instanceof EZDSBigDecimalItem) {
            int compRslt = ((EZDSBigDecimalItem) orig).getValue().compareTo(((EZDSBigDecimalItem) target).getValue());
            if (0 == compRslt) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * <pre>
     * Compare EZDSItem and EZDCItem.
     * the type of orig and type of target should be same.
     * if both have no value, this method returns true.
     * if one of them does'nt have value, this method returns false;
     * ex)
     * OK: orig: EZDSStringItem. target: EZDCStringItem
     * NG: orig: EZDSBigDecimalItem. target: EZDCStringItem
     * </pre>
     * @param orig compare original (EZDSItem; EZDSStringItem, EZDSDateItem, EZDSBigDecimalItem)
     * @param target compare to (EZDCItem; EZDCStringItem, EZDCDateItem, EZDCBigDecimalItem)
     * @return true: orig and target have same value false: orig and target have different value
     */
    public static boolean isEqualsEZDItem(EZDSItem orig, EZDCItem target) {

        if (!ZYPCommonFunc.hasValue(orig) && !ZYPCommonFunc.hasValue(target)) {
            return true;
        }
        if ((!ZYPCommonFunc.hasValue(orig) && ZYPCommonFunc.hasValue(target)) || (ZYPCommonFunc.hasValue(orig) && !ZYPCommonFunc.hasValue(target))) {
            return false;
        }
        if (orig instanceof EZDSStringItem && target instanceof EZDCStringItem) {
            return ((EZDSStringItem) orig).getValue().equals(((EZDCStringItem) target).getValue());
        }
        if (orig instanceof EZDSDateItem && target instanceof EZDCDateItem) {
            return ((EZDSDateItem) orig).getValue().equals(((EZDCDateItem) target).getValue());
        }
        if (orig instanceof EZDSBigDecimalItem && target instanceof EZDCBigDecimalItem) {
            int compRslt = ((EZDSBigDecimalItem) orig).getValue().compareTo(((EZDCBigDecimalItem) target).getValue());
            if (0 == compRslt) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    // 2018/01/29 S21_NA#19808 Add End

    /**
     * <pre>
     * Compare EZDCItem and EZDCItem.
     * the type of orig and type of target should be same.
     * if both have no value, this method returns true.
     * if one of them does'nt have value, this method returns false;
     * ex)
     * OK: orig: EZDCStringItem. target: EZDCStringItem
     * NG: orig: EZDCBigDecimalItem. target: EZDCStringItem
     * </pre>
     * @param orig compare original (EZDCItem; EZDCStringItem, EZDCDateItem, EZDCBigDecimalItem)
     * @param target compare to (EZDCItem; EZDCStringItem, EZDCDateItem, EZDCBigDecimalItem)
     * @return true: orig and target have same value false: orig and target have different value
     */
    public static boolean isEqualsEZDItem(EZDCItem orig, EZDCItem target) {

        if (!ZYPCommonFunc.hasValue(orig) && !ZYPCommonFunc.hasValue(target)) {
            return true;
        }
        if ((!ZYPCommonFunc.hasValue(orig) && ZYPCommonFunc.hasValue(target)) || (ZYPCommonFunc.hasValue(orig) && !ZYPCommonFunc.hasValue(target))) {
            return false;
        }
        if (orig instanceof EZDCStringItem && target instanceof EZDCStringItem) {
            return ((EZDCStringItem) orig).getValue().equals(((EZDCStringItem) target).getValue());
        }
        if (orig instanceof EZDCDateItem && target instanceof EZDCDateItem) {
            return ((EZDCDateItem) orig).getValue().equals(((EZDCDateItem) target).getValue());
        }
        if (orig instanceof EZDCBigDecimalItem && target instanceof EZDCBigDecimalItem) {
            int compRslt = ((EZDCBigDecimalItem) orig).getValue().compareTo(((EZDCBigDecimalItem) target).getValue());
            if (0 == compRslt) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Compare cpo update time, timezone and current cpo update time timezone
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @return return true: Modified by other user, false: not modified.
     */
    public static boolean isModifiedByOtherUser(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (isNewCreationMode(bizMsg)) { // S21_NA#5000#84
            return false;
        }

        // 2017/06/16 S21_NA#19005 Mod Start
//        String origUpdtDt = glblMsg.ezUpTime.getValue();
//        String origTimeZone = glblMsg.ezUpTimeZone.getValue();
        String origUpdTsDplyTxt = glblMsg.updTsDplyTxt.getValue();
        // 2018/08/06 S21_NA#27642 Mod Start
//        String origUpdUsrNm = glblMsg.updUsrNm.getValue();
        String origUpdUsrNm = glblMsg.dsCpoUpdUsrId.getValue();
        // 2018/08/06 S21_NA#27642 Mod Start
        // 2017/06/16 S21_NA#19005 Mod End

        // 2017/06/16 S21_NA#19005 Mod Start
        // 2018/06/21 S21_NA#25151 Restore Start
        CPOTMsg cpoTMsg = new CPOTMsg();
        cpoTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        cpoTMsg.cpoOrdNum.setValue(bizMsg.cpoOrdNum.getValue());

        CPOTMsg rsltCpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(cpoTMsg);
        // 2018/06/21 S21_NA#25151 Restore End
//        String curUpdtDt = rsltCpoTMsg.ezUpTime.getValue();
//        String curTimeZone = rsltCpoTMsg.ezUpTimeZone.getValue();
//        return !ZYPDateUtil.isSameTimeStamp(origUpdtDt, origTimeZone, curUpdtDt, curTimeZone);
        // 2018/06/21 S21_NA#25151 Del Start
//        CPO_VTMsg curCpoVTMsgCond = new CPO_VTMsg();
//        curCpoVTMsgCond.setSQLID("501");
//        curCpoVTMsgCond.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        curCpoVTMsgCond.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
//
//        CPO_VTMsgArray cpoVTMsgArray = (CPO_VTMsgArray) EZDTBLAccessor.findByCondition(curCpoVTMsgCond);
//        if (cpoVTMsgArray == null || cpoVTMsgArray.getValidCount() == 0) {
//            return true;
//        }
//        CPO_VTMsg curCpoVTMsg = cpoVTMsgArray.no(0);
//        if (!S21StringUtil.isEquals(origUpdTsDplyTxt, curCpoVTMsg.updTsDplyTxt.getValue()) //
//                || !S21StringUtil.isEquals(origUpdUsrNm, curCpoVTMsg.updUsrNm.getValue())) {
//            return true;
//        }
        // 2018/06/21 S21_NA#25151 Del End

        // 2018/06/21 S21_NA#25151 Add Start
        if (rsltCpoTMsg == null) {
            return true;
        }
        if (!isSameUpdTsTxtAndUpdTs(origUpdTsDplyTxt, rsltCpoTMsg.dsCpoUpdTs.getValue()) //
                || !isSameUpdateUser(origUpdUsrNm, rsltCpoTMsg.dsCpoUpdUsrId.getValue())) {
            return true;
        }
        // 2018/06/21 S21_NA#25151 Add End
        // 2018/06/21 S21_NA#25151 Mod Start
//        CPO_DTL_VTMsg curCpoDtlVTMsgCond = new CPO_DTL_VTMsg();
//        curCpoDtlVTMsgCond.setSQLID("501");
//        curCpoDtlVTMsgCond.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        curCpoDtlVTMsgCond.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
//
//        CPO_DTL_VTMsgArray cpoDtlVTMsgArray = (CPO_DTL_VTMsgArray) EZDTBLAccessor.findByCondition(curCpoDtlVTMsgCond);

        CPO_DTLTMsg curCpoDtlTMsgCond = new CPO_DTLTMsg();
        curCpoDtlTMsgCond.setSQLID("001");
        curCpoDtlTMsgCond.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        curCpoDtlTMsgCond.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());

        CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) EZDTBLAccessor.findByCondition(curCpoDtlTMsgCond);
        // 2018/06/21 S21_NA#25151 Mod End

//        int dtlGlblMsgCnt = glblMsg.B.getValidCount(); 2018/01/29 S21_NA#19808 Del

        // 2018/01/29 S21_NA#19808 Del Start
//        int dbDtlCnt = 0;
//        if (cpoDtlVTMsgArray == null) {
//            dbDtlCnt = 0;
//        } else {
//            dbDtlCnt = cpoDtlVTMsgArray.getValidCount();
//        }
//        if (dtlGlblMsgCnt == 0 && dbDtlCnt == 0) {
//            return false;
//        }
//        if (dtlGlblMsgCnt != dbDtlCnt) {
//            return true;
//        }
        // 2018/01/29 S21_NA#19808 Del End

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            // 2018/06/21 S21_NA#25151 Mod Start
//            CPO_DTL_VTMsg cpoDtlVTMsg = getCurDbDtlData(lineMsg, cpoDtlVTMsgArray);
            CPO_DTLTMsg cpoDtlTMsg = getCurDbDtlData(lineMsg, cpoDtlTMsgArray);
            // 2018/06/21 S21_NA#25151 Mod End
            // 2018/01/29 S21_NA#19808 Add Start
            if (cpoDtlTMsg == null) { // 2018/06/21 S21_NA#25151 Mod Without comments.
                continue;
            }
            // 2018/01/29 S21_NA#19808 Add End
            if (isModifiledLine(lineMsg, cpoDtlTMsg)) { // 2018/06/21 S21_NA#25151 Mod Without comments.
                return true;
            }
        }
        return false;
        // 2017/06/16 S21_NA#19005 Mod End
    }

    /**
     * Compare shpg update time, timezone and current shpg update time timezone
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @return boolean
     */
    public static boolean isModifiedByOtherUserForOutbound(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // QC#14698 Del Start
//        NWAL1500_SSMsg lineSMsg;
//        String origUpdtDt;
//        String origTimeZone;
//        String curUpdtDt;
//        String curTimeZone;
//        SHPG_PLNTMsg inTMsg = new SHPG_PLNTMsg();
//
//        for (int i = 0; i < glblMsg.S.getValidCount(); i++) {
//            lineSMsg = glblMsg.S.no(i);
//            origUpdtDt = lineSMsg.ezUpTime_SP.getValue();
//            origTimeZone = lineSMsg.ezUpTimeZone_SP.getValue();
//
//            inTMsg.clear();
//            inTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//            inTMsg.shpgPlnNum.setValue(lineSMsg.shpgPlnNum_SP.getValue());
//            SHPG_PLNTMsg outTMsg = (SHPG_PLNTMsg) S21FastTBLAccessor.findByKey(inTMsg);
//            if (outTMsg == null) {
//                return false;
//            }
//            curUpdtDt = outTMsg.ezUpTime.getValue();
//            curTimeZone = outTMsg.ezUpTimeZone.getValue();
//            if (!ZYPDateUtil.isSameTimeStamp(origUpdtDt, origTimeZone, curUpdtDt, curTimeZone)) {
//                return true;
//            }
//        }
//        return false;
        // QC#14698 Del End

        // QC#14698 Add Start
        if (glblMsg.S.getValidCount() == 0) {
            return false;
        }

        S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getShpgPlnList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            return false;
        }

        List<Map<String, String>> shpgPlnList = (List<Map<String, String>>) ssmResult.getResultObject();

        for (int i = 0; i < glblMsg.S.getValidCount(); i++) {
            NWAL1500_SSMsg shpgPLnMsg = glblMsg.S.no(i);
            String origShpgPlnNum = shpgPLnMsg.shpgPlnNum_SP.getValue();
            String origUpdtDt = shpgPLnMsg.ezUpTime_SP.getValue();
            String origTimeZone = shpgPLnMsg.ezUpTimeZone_SP.getValue();

            for (Map<String, String> shpgPlnInfo : shpgPlnList) {
                String curShpgPlnNum = shpgPlnInfo.get("SHPG_PLN_NUM");
                String curUpdtDt = shpgPlnInfo.get("EZUPTIME");
                String curTimeZone = shpgPlnInfo.get("EZUPTIMEZONE");

                if (ZYPCommonFunc.hasValue(origShpgPlnNum) && origShpgPlnNum.equals(curShpgPlnNum)) {
                    if (!ZYPDateUtil.isSameTimeStamp(origUpdtDt, origTimeZone, curUpdtDt, curTimeZone)) {
                        return true;
                    }
                    break;
                }
            }
        }

        return false;
        // QC#14698 Add End
    }

    /**
     * Compare ds_cpo_rtrn_dtl update time, timezone and current ds_cpo_rtrn_dtl update time timezone
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @return boolean
     */
    public static boolean isModifiedByOtherUserForInbound(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        NWAL1500_DSMsg lineSMsg; // 2018/01/29 S21_NA#19808 Mod
        String origUpdtDt;
        String origTimeZone;
        String curUpdtDt;
        String curTimeZone;
        DS_CPO_RTRN_DTLTMsg inTMsg = new DS_CPO_RTRN_DTLTMsg();
        DS_CPO_RTRN_DTLTMsg outTMsg = new DS_CPO_RTRN_DTLTMsg();
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) { // 2018/01/29 S21_NA#19808 Mod
            lineSMsg = glblMsg.D.no(i); // 2018/01/29 S21_NA#19808 Mod
            if (!ZYPCommonFunc.hasValue(lineSMsg.rtrnLineStsDescTxt_RL)) { // 2016/09/21 S21_NA#10274 Add Start // 2018/01/29 S21_NA#19808 Mod
                continue;
            } // 2016/09/21 S21_NA#10274 Add End
            origUpdtDt = lineSMsg.ezUpTime_RL.getValue(); // 2018/01/29 S21_NA#19808 Mod
            origTimeZone = lineSMsg.ezUpTimeZone_RL.getValue(); // 2018/01/29 S21_NA#19808 Mod

            inTMsg.clear();
            inTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            inTMsg.cpoOrdNum.setValue(bizMsg.cpoOrdNum.getValue());
            inTMsg.dsCpoRtrnLineNum.setValue(lineSMsg.cpoDtlLineNum_RL.getValue()); // 2018/01/29 S21_NA#19808 Mod
            inTMsg.dsCpoRtrnLineSubNum.setValue(lineSMsg.cpoDtlLineSubNum_RL.getValue()); // 2018/01/29 S21_NA#19808 Mod

            outTMsg = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKey(inTMsg);
            if (outTMsg == null) {
                continue;
            }
            curUpdtDt = outTMsg.ezUpTime.getValue();
            curTimeZone = outTMsg.ezUpTimeZone.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(origUpdtDt, origTimeZone, curUpdtDt, curTimeZone)) {
                return true;
            }
        }
        //QC743
//        for (int i = 0; i < glblMsg.K.getValidCount(); i++) {
//            lineKMsg = glblMsg.K.no(i);
//            origUpdtDt = lineKMsg.ezUpTime_RL.getValue();
//            origTimeZone = lineKMsg.ezUpTimeZone_RL.getValue();
//
//            inTMsg.clear();
//            inTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//            inTMsg.cpoOrdNum.setValue(bizMsg.cpoOrdNum.getValue());
//            inTMsg.dsCpoRtrnLineNum.setValue(lineKMsg.cpoDtlLineNum_RL.getValue());
//            inTMsg.dsCpoRtrnLineSubNum.setValue(lineKMsg.cpoDtlLineSubNum_RL.getValue());
//
//            outTMsg = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKey(inTMsg);
//            if (outTMsg == null) {
//                continue;
//            }
//            curUpdtDt = outTMsg.ezUpTime.getValue();
//            curTimeZone = outTMsg.ezUpTimeZone.getValue();
//            if (!ZYPDateUtil.isSameTimeStamp(origUpdtDt, origTimeZone, curUpdtDt, curTimeZone)) {
//                return true;
//            }
//        }

        return false;
    }

    /**
     * Get Package Uom PullDown data list for Config Line.
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_BCMsg
     */
    @SuppressWarnings("unchecked")
    public static void setUomPullDownForConfLine(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        lineMsg.pkgUomCd_CD.clear();
        lineMsg.pkgUomDescTxt_NM.clear();
        MDSETMsg mdseTMsg = getMdse(glblCmpyCd, lineMsg.mdseCd_LL.getValue());
        if (mdseTMsg == null) {
            return;
        }
        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getPkgUomPullDownList(glblCmpyCd, mdseTMsg.mdseCd.getValue());

        int n = 0;
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> rsltList = (List<Map<String, String>>) ssmRslt.getResultObject();
            for (Map<String, String> rslt : rsltList) {
                String pkgUomCd = rslt.get("PKG_UOM_CD");
                String pkgUomDescTxt = rslt.get("PKG_UOM_DESC_TXT");

                lineMsg.pkgUomCd_CD.no(n).setValue(pkgUomCd);
                lineMsg.pkgUomDescTxt_NM.no(n).setValue(pkgUomDescTxt);
                n++;
                if (n > lineMsg.pkgUomCd_CD.length()) {
                    break;
                }
            }
        }
    }

    // For Performance QC#8166 Add Start
    /**
     * Set Package Uom PullDown data list for Config Line.
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_BCMsg
     * @param cacheMap Cache Map
     */
    @SuppressWarnings("unchecked")
    public static void setUomPullDownForConfLineFromCache(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg, Map<String, List<Map<String, String>>> cacheMap) { // 2018/01/29 S21_NA#19808 Mod

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        MDSETMsg mdseTMsg = getMdse(glblCmpyCd, lineMsg.mdseCd_LL.getValue());

        if (mdseTMsg == null) {
            return;
        }

        List<Map<String, String>> rsltList = null;
        String mdseCd = mdseTMsg.mdseCd.getValue();

        if (cacheMap.containsKey(mdseCd)) {
            rsltList = cacheMap.get(mdseCd);
        } else {
            S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getPkgUomPullDownList(glblCmpyCd, mdseCd);
            if (ssmRslt.isCodeNormal()) {
                rsltList = (List<Map<String, String>>) ssmRslt.getResultObject();
                cacheMap.put(mdseCd, rsltList);
            } else {
                return;
            }
        }

        lineMsg.pkgUomCd_CD.clear();
        lineMsg.pkgUomDescTxt_NM.clear();
        int n = 0;
        for (Map<String, String> rslt : rsltList) {
            String pkgUomCd = rslt.get("PKG_UOM_CD");
            String pkgUomDescTxt = rslt.get("PKG_UOM_DESC_TXT");

            lineMsg.pkgUomCd_CD.no(n).setValue(pkgUomCd);
            lineMsg.pkgUomDescTxt_NM.no(n).setValue(pkgUomDescTxt);
            n++;
            if (n > lineMsg.pkgUomCd_CD.length()) {
                break;
            }
        }
    }
    // For Performance QC#8166 Add End

    /**
     * Set Package Uom PullDown data list for RMA.
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_DCMsg
     */
    @SuppressWarnings("unchecked")
    public static void setUomPullDownForRma(NWAL1500CMsg bizMsg, NWAL1500_DCMsg lineMsg) {

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        MDSETMsg mdseTMsg = getMdse(glblCmpyCd, lineMsg.mdseCd_RL.getValue());

        if (mdseTMsg == null) {
            return;
        }

        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getPkgUomPullDownList(glblCmpyCd, mdseTMsg.mdseCd.getValue());

        lineMsg.pkgUomCd_CR.clear();
        lineMsg.pkgUomDescTxt_CN.clear();
        int n = 0;
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> rsltList = (List<Map<String, String>>) ssmRslt.getResultObject();
            for (Map<String, String> rslt : rsltList) {
                String pkgUomCd = rslt.get("PKG_UOM_CD");
                String pkgUomDescTxt = rslt.get("PKG_UOM_DESC_TXT");

                lineMsg.pkgUomCd_CR.no(n).setValue(pkgUomCd);
                lineMsg.pkgUomDescTxt_CN.no(n).setValue(pkgUomDescTxt);
                n++;
                if (n > lineMsg.pkgUomCd_CR.length()) {
                    break;
                }
            }
        }
    }

    // For Performance QC#8166 Add Start
    /**
     * Get Package Uom PullDown data list for RMA.
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_DCMsg
     * @param cacheMap CacheMap
     */
    @SuppressWarnings("unchecked")
    public static void setUomPullDownForRmaFromCache(NWAL1500CMsg bizMsg, NWAL1500_DSMsg lineMsg, Map<String, List<Map<String, String>>> cacheMap) { // 2018/01/29 S21_NA#19808 Mod

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        MDSETMsg mdseTMsg = getMdse(glblCmpyCd, lineMsg.mdseCd_RL.getValue());

        if (mdseTMsg == null) {
            return;
        }

        List<Map<String, String>> rsltList = null;
        String mdseCd = mdseTMsg.mdseCd.getValue();

        if (cacheMap.containsKey(mdseCd)) {
            rsltList = cacheMap.get(mdseCd);
        } else {
            S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getPkgUomPullDownList(glblCmpyCd, mdseCd);
            if (ssmRslt.isCodeNormal()) {
                rsltList = (List<Map<String, String>>) ssmRslt.getResultObject();
                cacheMap.put(mdseCd, rsltList);
            } else {
                return;
            }
        }

        lineMsg.pkgUomCd_CR.clear();
        lineMsg.pkgUomDescTxt_CN.clear();
        int n = 0;
        for (Map<String, String> rslt : rsltList) {
            String pkgUomCd = rslt.get("PKG_UOM_CD");
            String pkgUomDescTxt = rslt.get("PKG_UOM_DESC_TXT");

            lineMsg.pkgUomCd_CR.no(n).setValue(pkgUomCd);
            lineMsg.pkgUomDescTxt_CN.no(n).setValue(pkgUomDescTxt);
            n++;
            if (n > lineMsg.pkgUomCd_CR.length()) {
                break;
            }
        }
    }
    // For Performance QC#8166 Add End

    /**
     * Mandatory Check for Header
     * @param bizMsg Business Message
     * @return true: normal false: has error
     */
    public static boolean checkMandatoryHeaderTab(NWAL1500CMsg bizMsg) {

        // For Performance QC#8166 Mod Start
        // NWAL1500ItemNameList itemNameList = NWAL1500ItemNameList.getInstance();
        // itemNameList.setBizMsg(bizMsg);
        NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(bizMsg);
        // For Performance QC#8166 Mod End

        List<EZDCItem> itemList = new ArrayList<EZDCItem>();
        itemList.add(bizMsg.dsOrdCatgDescTxt);
        itemList.add(bizMsg.dsOrdTpDescTxt);
        itemList.add(bizMsg.ordDt);
        itemList.add(bizMsg.billToCustAcctNm);
        itemList.add(bizMsg.billToCustAcctCd);
        itemList.add(bizMsg.billToCustCd);
        itemList.add(bizMsg.shipToCustAcctNm);
        itemList.add(bizMsg.shipToCustAcctCd);
        itemList.add(bizMsg.shipToCustCd);
        itemList.add(bizMsg.soldToCustAcctNm);
        itemList.add(bizMsg.sellToCustCd);
        itemList.add(bizMsg.soldToCustLocCd);
        itemList.add(bizMsg.slsRepTocNm);
        itemList.add(bizMsg.psnNum); // 2016/05/12 S21_NA#7861  Mod slsRepPsnCd -> psnNum
        itemList.add(bizMsg.prcCatgNm);

        // 2019/01/30 QC#30036 Mod Start
//        boolean isNormal = true;
//        for (EZDCItem item : itemList) {
//            if (!ZYPCommonFunc.hasValue(item)) {
//                item.setErrorInfo(1, ZZM9000E, new String[] {itemNameList.getItemName(item) });
//                isNormal = false;
//            }
//        }
//
//        return isNormal;
        boolean isNormal = true;
        if (checkPoNum(bizMsg)) {
            bizMsg.custIssPoNum.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_CUST_PO_NUM });
            isNormal = false;
        }
        for (EZDCItem item : itemList) {
            if (!ZYPCommonFunc.hasValue(item)) {
                item.setErrorInfo(1, ZZM9000E, new String[] {itemNameList.getItemName(item) });
                isNormal = false;
            }
        }

        return isNormal;
        // 2019/01/30 QC#30036 Mod End
    }

    /**
     * Mandatory Check for Additional Header
     * @param bizMsg Business Message
     * @return true: normal false: has error
     */
    public static boolean checkMandatoryAddlHeaderTab(NWAL1500CMsg bizMsg) {

        boolean isNormal = true;

        // For Performance QC#8166 Mod Start
        // NWAL1500ItemNameList itemNameList = NWAL1500ItemNameList.getInstance();
        // itemNameList.setBizMsg(bizMsg);
        NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(bizMsg);
        // For Performance QC#8166 Mod End

        // Map<EZDCItem, String> itemList = new LinkedHashMap<EZDCItem, String>();
        List<EZDCItem> itemList = new ArrayList<EZDCItem>();

        // Freight Information
        itemList.add(bizMsg.frtCondDescTxt);
        itemList.add(bizMsg.shpgSvcLvlCd);
        // Payment Terms Information
        itemList.add(bizMsg.pmtTermCashDiscDescTxt);
        // Additional Details
        itemList.add(bizMsg.flPrcListNm);
        // itemList.add(bizMsg.ordSgnDt);

        for (EZDCItem item : itemList) {
            if (!ZYPCommonFunc.hasValue(item)) {
                item.setErrorInfo(1, ZZM9000E, new String[] {itemNameList.getItemName(item) });
                isNormal = false;
            }
        }

        return isNormal;
    }

    //QC#13428
    /**
     * Limitation Check for Line Config Tab
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @return true: normal false: has error
     */
    public static boolean checkLineCountLimitation(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/29 S21_NA#19808 Mod

        // 2018/01/29 S21_NA#19808 Del Start
//        // QC#10321-23 Mod Start
//        // BigDecimal bdLineLimitCnt = ZYPCodeDataUtil.getNumConstValue(NWAL1500_LINE_LIMIT_CNT, bizMsg.glblCmpyCd.getValue());
//        // BigDecimal bdConfigLineLimitCnt = ZYPCodeDataUtil.getNumConstValue(NWAL1500_CONFIG_LINE_LIMIT_CNT, bizMsg.glblCmpyCd.getValue());
//        BigDecimal bdConfigLimitCnt = bizMsg.numConstVal_CC.getValue();
//        BigDecimal bdLineLimitCnt = bizMsg.numConstVal_LC.getValue();
//        // QC#10321-23 Mod End
        // 2018/01/29 S21_NA#19808 Del End

        NWZC150001PMsg apiPMsg = new NWZC150001PMsg();
//        int configLimitCnt = apiPMsg.cpoConfig.length();
        int lineLimitCnt = apiPMsg.A.length();
        int rmaLineLimitCnt = apiPMsg.rtnDtl.length();
        apiPMsg = null;

        // 2018/01/29 S21_NA#19808 Del Start
//        int lineLimitCnt = 3000;
//        if (bdLineLimitCnt != null) {
//            lineLimitCnt = bdLineLimitCnt.intValue();
//        }
        // 2018/01/29 S21_NA#19808 Del End
        if (glblMsg.B.getValidCount() > lineLimitCnt) { // 2018/01/29 S21_NA#19808 Mod
            //All line count exceeded @.
            bizMsg.setMessageInfo(NWAM8458E, new String[]{String.valueOf(lineLimitCnt)}); // 2018/01/29 S21_NA#19808 Mod
            return false;
        }
        // 2018/01/29 S21_NA#19808 Add Start
        if (glblMsg.D.getValidCount() > rmaLineLimitCnt) { // 2018/01/29 S21_NA#19808 Mod
            //All line count exceeded @.
            bizMsg.setMessageInfo(NWAM8458E, new String[]{String.valueOf(rmaLineLimitCnt)}); // 2018/01/29 S21_NA#19808 Mod
            return false;
        }
        // 2018/01/29 S21_NA#19808 Add End

        // 2018/01/29 S21_NA#19808 Del Start
//        int configLimitCnt = 999;
//        if (bdConfigLimitCnt != null) {
//            configLimitCnt = bdConfigLimitCnt.intValue();
//        }
//        Map<String, BigDecimal> lineCountMap = new java.util.LinkedHashMap<String, BigDecimal>();
//        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            BigDecimal lineCount = (BigDecimal) lineCountMap.get(bizMsg.B.no(i).dsOrdPosnNum_LL.getValue());
//            if (lineCount == null) {
//                lineCount = BigDecimal.ONE;
//            } else {
//                lineCount = lineCount.add(BigDecimal.ONE);
//            }
//            lineCountMap.put(bizMsg.B.no(i).dsOrdPosnNum_LL.getValue(), lineCount);
//        }
//
//        for (Map.Entry<String, BigDecimal> e : lineCountMap.entrySet()) {
//            BigDecimal configLineCount = e.getValue();
//            int configCnt = 0;
//            if (configLineCount != null) {
//                configCnt = configLineCount.intValue();
//            }
//            if (configCnt > configLimitCnt) {
//                // The line count of config[@] exceeded @.
//                bizMsg.setMessageInfo(NWAM8459E, new String[] {e.getKey(), String.valueOf(configLimitCnt) }); // 2018/01/29 S21_NA#19808 Mod
//                return false;
//            }
//        }
        // 2018/01/29 S21_NA#19808 Del End

        return true;
    }

    /**
     * Mandatory Check for Line Config Tab
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @return true: normal false: has error
     */
    public static boolean checkMandatoryLineConfigTab(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2018/01/29 S21_NA#19808 Mod bizMsg => glblMsg Start
        List<EZDSItem> itemList = new ArrayList<EZDSItem>();
        if (glblMsg.A.getValidCount() == 1 && glblMsg.B.getValidCount() == 1) {
            itemList.add(glblMsg.A.no(0).svcConfigMstrPk_LC);
            itemList.add(glblMsg.A.no(0).mdlDescTxt_LC);
            itemList.add(glblMsg.B.no(0).mdseCd_LL);

            boolean isAllEmpty = true;
            for (EZDSItem item : itemList) {
                if (ZYPCommonFunc.hasValue(item)) {
                    isAllEmpty = false;
                }
            }
            if (isAllEmpty) {
                glblMsg.A.clear();
                glblMsg.A.setValidCount(0);
                glblMsg.B.clear();
                glblMsg.B.setValidCount(0);

                glblMsg.G.clear();
                glblMsg.G.setValidCount(0);
                return true;
            }
        }
        // 2018/01/29 S21_NA#19808 Mod bizMsg => glblMsg End
        itemList.clear();

        boolean isNormal = true;

        // For Performance QC#8166 Mod Start
        // NWAL1500ItemNameList itemNameList = NWAL1500ItemNameList.getInstance();
        // itemNameList.setBizMsg(bizMsg);
        NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(glblMsg); //2018/01/29 S21_NA#19808 Mod bizMsg
        // For Performance QC#8166 Mod End

        // 2018/01/29 S21_NA#19808 Mod bizMsg => glblMsg Start
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            itemList.add(glblMsg.A.no(i).configTpCd_LC);
            itemList.add(glblMsg.A.no(i).shipToCustAcctCd_LC);
            itemList.add(glblMsg.A.no(i).shipToCustCd_LC);
            itemList.add(glblMsg.A.no(i).shipToCustAcctNm_LC);
            itemList.add(glblMsg.A.no(i).billToCustAcctCd_LC);
            itemList.add(glblMsg.A.no(i).billToCustCd_LC);
            itemList.add(glblMsg.A.no(i).billToCustAcctNm_LC);

            // 2016/01/19 S21_NA#3339 Add Start
            // QC#14330 2016/10/19 Mod
            //if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(i).configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, true, false)) {
            if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), glblMsg.A.no(i).configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, true, true)) {
                itemList.add(glblMsg.A.no(i).svcConfigMstrPk_LC);
            }
            // 2016/01/19 S21_NA#3339 Add End
        }

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
        	// START 2023/05/11 R.Azucena [QC#61514 MOD]
        	// START 2023/08/04 K.Ikeda [QC#61691 MOD]
            // if (!ORD_LINE_STS.CANCELLED.equals(glblMsg.B.no(i).ordLineStsCd_LL.getValue())) { // QC#1620
            //  if (!ORD_LINE_STS.CANCELLED.equals(glblMsg.B.no(i).ordLineStsCd_LL.getValue()) // QC#1620
        	
            //  		&& !S21StringUtil.isEquals(bizMsg.varCharConstVal_TB.getValue(), glblMsg.B.no(i).ordLineStsDescTxt_LL.getValue())) {
            if (!ORD_LINE_STS.CANCELLED.equals(glblMsg.B.no(i).ordLineStsCd_LL.getValue()) // QC#1620
            		&& !ORD_LINE_STS.CLOSED.equals(glblMsg.B.no(i).ordLineStsCd_LL.getValue())
                	&& !S21StringUtil.isEquals(bizMsg.varCharConstVal_TB.getValue(), glblMsg.B.no(i).ordLineStsDescTxt_LL.getValue())) {
            // END 2023/08/04 K.Ikeda [QC#61691 MOD]
            // END 2023/05/11 R.Azucena [QC#61514 MOD]
                itemList.add(glblMsg.B.no(i).mdseCd_LL);
                itemList.add(glblMsg.B.no(i).ordCustUomQty_LL);
                itemList.add(glblMsg.B.no(i).custUomCd_LL);
                itemList.add(glblMsg.B.no(i).entCpoDtlDealSlsAmt_LL);
                itemList.add(glblMsg.B.no(i).prcCatgNm_LL);
                itemList.add(glblMsg.B.no(i).dsOrdLineCatgCd_LL);
                itemList.add(glblMsg.B.no(i).ordLineSrcCd_LL);
                // 2017/08/15 S21_NA#20377 Add Start
                itemList.add(glblMsg.B.no(i).prcBaseDt_LL);
                // 2017/08/15 S21_NA#20377 Add End

                // if (isMdseTangible(bizMsg.glblCmpyCd.getValue(),
                // bizMsg.B.no(i).mdseCd_LL.getValue())) {
                // S21_NA#2522
                if (needsWh(bizMsg.glblCmpyCd.getValue(), glblMsg.B.no(i).mdseCd_LL.getValue())) {
                    itemList.add(glblMsg.B.no(i).rtlWhNm_LL);
                }
                itemList.add(glblMsg.B.no(i).flPrcListNm_LL);
                itemList.add(glblMsg.B.no(i).funcUnitFlPrcAmt_LL); // QC#22372 2018/01/10 Add
            }
        }

        for (EZDSItem item : itemList) {
            if (!ZYPCommonFunc.hasValue(item)) {
                item.setErrorInfo(1, ZZM9000E, new String[] {itemNameList.getItemName(item) });
                isNormal = false;
            }
        }
        // 2018/01/29 S21_NA#19808 Mod bizMsg => glblMsg End
        // 2018/01/29 S21_NA#19808 Add Start
        List<Integer> errPageList = new ArrayList<Integer>(0);
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            boolean errFlg = false;
            Integer errPage = Integer.valueOf(glblMsg.A.no(i).xxPageNum_LC.getValueInt());

            errFlg = errFlg | glblMsg.A.no(i).configTpCd_LC.isError();
            errFlg = errFlg | glblMsg.A.no(i).shipToCustAcctCd_LC.isError();
            errFlg = errFlg | glblMsg.A.no(i).shipToCustCd_LC.isError();
            errFlg = errFlg | glblMsg.A.no(i).shipToCustAcctNm_LC.isError();
            errFlg = errFlg | glblMsg.A.no(i).billToCustAcctCd_LC.isError();
            errFlg = errFlg | glblMsg.A.no(i).billToCustCd_LC.isError();
            errFlg = errFlg | glblMsg.A.no(i).billToCustAcctNm_LC.isError();
            errFlg = errFlg | glblMsg.A.no(i).svcConfigMstrPk_LC.isError();

            if (errFlg && !errPageList.contains(errPage)) {
                errPageList.add(errPage);
            }
        }

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            boolean errFlg = false;
            Integer errPage = Integer.valueOf(glblMsg.B.no(i).xxPageNum_LL.getValueInt());
            errFlg = errFlg | glblMsg.B.no(i).mdseCd_LL.isError();
            errFlg = errFlg | glblMsg.B.no(i).ordCustUomQty_LL.isError();
            errFlg = errFlg | glblMsg.B.no(i).custUomCd_LL.isError();
            errFlg = errFlg | glblMsg.B.no(i).entCpoDtlDealSlsAmt_LL.isError();
            errFlg = errFlg | glblMsg.B.no(i).prcCatgNm_LL.isError();
            errFlg = errFlg | glblMsg.B.no(i).dsOrdLineCatgCd_LL.isError();
            errFlg = errFlg | glblMsg.B.no(i).ordLineSrcCd_LL.isError();
            errFlg = errFlg | glblMsg.B.no(i).prcBaseDt_LL.isError();
            errFlg = errFlg | glblMsg.B.no(i).rtlWhNm_LL.isError();
            errFlg = errFlg | glblMsg.B.no(i).flPrcListNm_LL.isError();
            errFlg = errFlg | glblMsg.B.no(i).funcUnitFlPrcAmt_LL.isError();

            if (errFlg) {
                glblMsg.B.no(i).xxSmryLineFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
                NWAL1500_ASMsg configMsg = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.A, glblMsg.B.no(i).dsOrdPosnNum_LL.getValue());
                configMsg.xxSmryLineFlg_L.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (errFlg && !errPageList.contains(errPage)) {
                errPageList.add(errPage);
            }
        }
        if (!errPageList.isEmpty()) {
            int errPageNum = errPageList.get(0);
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, errPageNum);
        }
        // 2018/01/29 S21_NA#19808 Add End

        return isNormal;
    }

    /**
     * Mandatory Check for RMA Tab
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @return true: normal false: has error
     */
    public static boolean checkMandatoryRMATab(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        // 2018/01/29 S21_NA#19808 Mod bizMsg => glblMsg Start
        List<EZDSItem> itemList = new ArrayList<EZDSItem>();
        if (glblMsg.C.getValidCount() == 1 && glblMsg.D.getValidCount() == 1) {
            itemList.add(glblMsg.C.no(0).svcConfigMstrPk_RC);
            itemList.add(glblMsg.D.no(0).mdseCd_RL);

            boolean isAllEmpty = true;
            for (EZDSItem item : itemList) {
                if (ZYPCommonFunc.hasValue(item)) {
                    isAllEmpty = false;
                }
            }
            if (isAllEmpty) {
                glblMsg.C.clear();
                glblMsg.C.setValidCount(0);
                glblMsg.D.clear();
                glblMsg.D.setValidCount(0);

                glblMsg.H.clear();
                glblMsg.H.setValidCount(0);
                return true;
            }
        }
        // 2018/01/29 S21_NA#19808 Mod bizMsg => glblMsg End
        itemList.clear();

        boolean isNormal = true;

        // For Performance QC#8166 Mod Start
        // NWAL1500ItemNameList itemNameList = NWAL1500ItemNameList.getInstance();
        // itemNameList.setBizMsg(bizMsg);
        NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(glblMsg); // 2018/01/29 S21_NA#19808 Mod
        // For Performance QC#8166 Mod End

        // 2018/01/29 S21_NA#19808 Mod bizMsg => glblMsg Start
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            itemList.add(glblMsg.C.no(i).configTpCd_RC);
            itemList.add(glblMsg.C.no(i).shipToCustAcctCd_RC);
            itemList.add(glblMsg.C.no(i).shipToCustCd_RC);
            itemList.add(glblMsg.C.no(i).shipToCustAcctNm_RC);
            itemList.add(glblMsg.C.no(i).billToCustAcctCd_RC);
            itemList.add(glblMsg.C.no(i).billToCustCd_RC);
            itemList.add(glblMsg.C.no(i).billToCustAcctNm_RC);
        }

        boolean isEquipmentOrd = NWAL1500CommonLogicForLineConfig.isExistOrdCatg(bizMsg, true); // 2016/04/08 S21_NA#5356 Add

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            if (!RTRN_LINE_STS.CANCELLED.equals(glblMsg.D.no(i).ordLineStsCd_RL.getValue()) // QC#1620
                    // START 2023/05/11 R.Azucena [QC#61514 MOD]
                    // && !RTRN_LINE_STS.CLOSED.equals(glblMsg.D.no(i).ordLineStsCd_RL.getValue())) {  // 2016/09/06 S21_NA#12435 Add
                    && !RTRN_LINE_STS.CLOSED.equals(glblMsg.D.no(i).ordLineStsCd_RL.getValue()) // 2016/09/06 S21_NA#12435 Add
                    && !S21StringUtil.isEquals(bizMsg.varCharConstVal_TB.getValue(), glblMsg.D.no(i).rtrnLineStsDescTxt_RL.getValue())) {
                    // END 2023/05/11 R.Azucena [QC#61514 MOD]
                itemList.add(glblMsg.D.no(i).mdseCd_RL);
                itemList.add(glblMsg.D.no(i).ordCustUomQty_RL);
                itemList.add(glblMsg.D.no(i).custUomCd_RL);
                itemList.add(glblMsg.D.no(i).entCpoDtlDealSlsAmt_RL);
                itemList.add(glblMsg.D.no(i).prcCatgNm_RL);
                itemList.add(glblMsg.D.no(i).dsOrdLineCatgCd_RL);
                // 2016/06/16 S21_NA#9855 Mod Start
                // itemList.add(bizMsg.D.no(i).rtlWhNm_RL);
                if (needsWh(bizMsg.glblCmpyCd.getValue(), glblMsg.D.no(i).mdseCd_RL.getValue())) {
                    itemList.add(glblMsg.D.no(i).rtlWhNm_RL);
                }
                // 2016/06/16 S21_NA#9855 Mod End
                itemList.add(glblMsg.D.no(i).flPrcListNm_RL);
                itemList.add(glblMsg.D.no(i).funcUnitFlPrcAmt_RL); // QC#22372 2018/01/10 Add
                itemList.add(glblMsg.D.no(i).rtrnRsnCd_RL);

                // 2017/09/11 S21_NA#20505 Add Start
                boolean isNeedSerialNum = NWAL1500CommonLogicForSaveSubmit.isNeedSerialNum(bizMsg, glblMsg.D.no(i).mdseCd_RL.getValue());
                // 2017/09/11 S21_NA#20505 Add End
                // 2016/04/08 S21_NA#5356 Add Start
                // 2018/05/21 S21_NA#25454 Mod Start
//                if (isEquipmentOrd && ZYPCommonFunc.hasValue(glblMsg.D.no(i).mdseCd_RL)) {
                if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).mdseCd_RL)) {
                // 2018/05/21 S21_NA#25454 Mod end
                    // 2017/09/11 S21_NA#20505 Del Start
//                    boolean isNeedSerialNum = NWAL1500CommonLogicForSaveSubmit.isNeedSerialNum(bizMsg, bizMsg.D.no(i).mdseCd_RL.getValue());
                    // 2017/09/11 S21_NA#20505 Del End
                    if (isNeedSerialNum) {
                        itemList.add(glblMsg.D.no(i).serNum_RL);
                    }
                }
                // 2016/04/08 S21_NA#5356 Add End
                // 2017/08/15 S21_NA#20377 Add Start
                itemList.add(glblMsg.D.no(i).prcBaseDt_RL);
                // 2017/08/15 S21_NA#20377 Add End
                // 2017/09/11 S21_NA#20505 Add Start
                if (!isNeedSerialNum && ZYPCommonFunc.hasValue(glblMsg.D.no(i).serNum_RL)) {
                    glblMsg.D.no(i).serNum_RL.setErrorInfo(1, NWAM0939E, new String[]{glblMsg.D.no(i).serNum_RL.getValue()});
                }
                // 2017/09/11 S21_NA#20505 Add End
            }
        }

        for (EZDSItem item : itemList) {
            if (!ZYPCommonFunc.hasValue(item)) {
                item.setErrorInfo(1, ZZM9000E, new String[] {itemNameList.getItemName(item) });
                isNormal = false;
            }
        }
        // 2018/01/29 S21_NA#19808 Mod bizMsg => glblMsg End
        // 2018/01/29 S21_NA#19808 Add
        List<Integer> errPageList = new ArrayList<Integer>(0);
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            boolean errFlg = false;
            Integer errPage = Integer.valueOf(glblMsg.C.no(i).xxPageNum_RC.getValueInt());
            errFlg = errFlg | glblMsg.C.no(i).configTpCd_RC.isError();
            errFlg = errFlg | glblMsg.C.no(i).shipToCustAcctCd_RC.isError();
            errFlg = errFlg | glblMsg.C.no(i).shipToCustCd_RC.isError();
            errFlg = errFlg | glblMsg.C.no(i).shipToCustAcctNm_RC.isError();
            errFlg = errFlg | glblMsg.C.no(i).billToCustAcctCd_RC.isError();
            errFlg = errFlg | glblMsg.C.no(i).billToCustCd_RC.isError();
            errFlg = errFlg | glblMsg.C.no(i).billToCustAcctNm_RC.isError();

            if (errFlg && !errPageList.contains(errPage)) {
                errPageList.add(errPage);
            }
        }
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            boolean errFlg = false;
            Integer errPage = Integer.valueOf(glblMsg.D.no(i).xxPageNum_RL.getValueInt());
            errFlg = errFlg | glblMsg.D.no(i).mdseCd_RL.isError();
            errFlg = errFlg | glblMsg.D.no(i).ordCustUomQty_RL.isError();
            errFlg = errFlg | glblMsg.D.no(i).custUomCd_RL.isError();
            errFlg = errFlg | glblMsg.D.no(i).entCpoDtlDealSlsAmt_RL.isError();
            errFlg = errFlg | glblMsg.D.no(i).prcCatgNm_RL.isError();
            errFlg = errFlg | glblMsg.D.no(i).dsOrdLineCatgCd_RL.isError();
            errFlg = errFlg | glblMsg.D.no(i).rtlWhNm_RL.isError();
            errFlg = errFlg | glblMsg.D.no(i).flPrcListNm_RL.isError();
            errFlg = errFlg | glblMsg.D.no(i).funcUnitFlPrcAmt_RL.isError();
            errFlg = errFlg | glblMsg.D.no(i).rtrnRsnCd_RL.isError();

            errFlg = errFlg | glblMsg.D.no(i).serNum_RL.isError();
            errFlg = errFlg | glblMsg.D.no(i).prcBaseDt_RL.isError();

            if (errFlg) {
                glblMsg.D.no(i).xxSmryLineFlg_RL.setValue(ZYPConstant.FLG_OFF_N);
                NWAL1500_CSMsg rmaConfigMsg = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.C, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue());
                rmaConfigMsg.xxSmryLineFlg_R.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (errFlg && !errPageList.contains(errPage)) {
                errPageList.add(errPage);
            }
        }
        if (!errPageList.isEmpty()) {
            int errPageNum = errPageList.get(0);
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, errPageNum);
        }

        return isNormal;
    }
    
    // 2018/12/18 S21_NA#29561 Add Start
    public static boolean chkMndRMA4Credit(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        List<EZDSItem> itemList = new ArrayList<EZDSItem>();
        if (glblMsg.C.getValidCount() == 1 && glblMsg.D.getValidCount() == 1) {
            itemList.add(glblMsg.D.no(0).mdseCd_RL);

            boolean isAllEmpty = true;
            for (EZDSItem item : itemList) {
                if (ZYPCommonFunc.hasValue(item)) {
                    isAllEmpty = false;
                }
            }
            if (isAllEmpty) {
                glblMsg.C.clear();
                glblMsg.C.setValidCount(0);
                glblMsg.D.clear();
                glblMsg.D.setValidCount(0);

                glblMsg.H.clear();
                glblMsg.H.setValidCount(0);
                return true;
            }
        }
        NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(glblMsg);

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            // START 2023/05/11 R.Azucena [QC#61514 MOD]
            // if (!RTRN_LINE_STS.CANCELLED.equals(glblMsg.D.no(i).ordLineStsCd_RL.getValue()) && !RTRN_LINE_STS.CLOSED.equals(glblMsg.D.no(i).ordLineStsCd_RL.getValue())) {
            if (!RTRN_LINE_STS.CANCELLED.equals(glblMsg.D.no(i).ordLineStsCd_RL.getValue()) && !RTRN_LINE_STS.CLOSED.equals(glblMsg.D.no(i).ordLineStsCd_RL.getValue())
                    && !S21StringUtil.isEquals(bizMsg.varCharConstVal_TB.getValue(), glblMsg.D.no(i).rtrnLineStsDescTxt_RL.getValue())) {
            // END 2023/05/11 R.Azucena [QC#61514 MOD]
                if (needsWh(bizMsg.glblCmpyCd.getValue(), glblMsg.D.no(i).mdseCd_RL.getValue())) {
                    itemList.add(glblMsg.D.no(i).rtlWhNm_RL);
                }
                itemList.add(glblMsg.D.no(i).rtrnRsnCd_RL);
            }
        }
        boolean isNormal = true;

        for (EZDSItem item : itemList) {
            if (!ZYPCommonFunc.hasValue(item)) {
                item.setErrorInfo(1, ZZM9000E, new String[] {itemNameList.getItemName(item) });
                isNormal = false;
            }
        }
        List<Integer> errPageList = new ArrayList<Integer>(0);
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            boolean errFlg = false;
            Integer errPage = Integer.valueOf(glblMsg.D.no(i).xxPageNum_RL.getValueInt());
            errFlg = errFlg | glblMsg.D.no(i).rtlWhNm_RL.isError();
            errFlg = errFlg | glblMsg.D.no(i).rtrnRsnCd_RL.isError();
            if (errFlg) {
                glblMsg.D.no(i).xxSmryLineFlg_RL.setValue(ZYPConstant.FLG_OFF_N);
                NWAL1500_CSMsg rmaConfigMsg = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.C, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue());
                rmaConfigMsg.xxSmryLineFlg_R.setValue(ZYPConstant.FLG_OFF_N);
            }
            if (errFlg && !errPageList.contains(errPage)) {
                errPageList.add(errPage);
            }
        }
        if (!errPageList.isEmpty()) {
            int errPageNum = errPageList.get(0);
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, errPageNum);
        }

        return isNormal;
    }
    // 2018/12/18 S21_NA#29561 Add End

    /**
     * Check Input Order Log Type
     * @param bizMsg NWAL1500CMsg
     * @return check OK : true
     */
    @SuppressWarnings("unchecked")
    public static boolean checkOrdLogTp(NWAL1500CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getOrdLogTpList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            // S21_NA#5337 Mod Start
//            bizMsg.ordLogTpDescTxt_NM.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_LOG_TYPE });
            bizMsg.ordLogTpCd.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_LOG_TYPE });
            // S21_NA#5337 Mod End
            return false;
        }

        List<Map<String, String>> ordLogTpList = (List<Map<String, String>>) ssmResult.getResultObject();
        boolean result = (ordLogTpList.size() == 1);
        if (result) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

            ZYPEZDItemValueSetter.setValue(bizMsg.ordLogTpCd, ordLogTpList.get(0).get("ORD_LOG_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.ordLogTpDescTxt_NM, ordLogTpList.get(0).get("ORD_LOG_TP_DESC_TXT").toUpperCase());
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
        }

        return result;
    }

    /**
     * Get "BASE" Price Element from Business Message
     * @param lineMsg Line config Message
     * @param bizMsg BusinessMessage
     * @return BASE price Element (same line number with lineMsg), if there is no "BASE" element, null
     */
    public static NWAL1500_ISMsg getBasePriceDataFromBizlMsg(NWAL1500_BSMsg lineMsg, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/29 S21_NA#19808 
        String xxLineNum = lineMsg.xxLineNum_LL.getValue();
        String cpoDtlLineNum = lineMsg.cpoDtlLineNum_LL.getValue();
        String cpoDtlLineSubNum = lineMsg.cpoDtlLineSubNum_LL.getValue();
        String configCatgCd = CONFIG_CATG.OUTBOUND;
        for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
            NWAL1500_ISMsg priceLine = glblMsg.I.no(i);
            String xxLineNumP = priceLine.xxLineNum_LP.getValue();
            String cpoDtlLineNumP = priceLine.cpoDtlLineNum_LP.getValue();
            String cpoDtlLineSubNumP = priceLine.cpoDtlLineSubNum_LP.getValue();
            String configCatgCdP = priceLine.configCatgCd_LP.getValue();
            if ((xxLineNum.equals(xxLineNumP) || (cpoDtlLineNum.equals(cpoDtlLineNumP) && cpoDtlLineSubNum.equals(cpoDtlLineSubNumP))) && configCatgCd.equals(configCatgCdP)
                    && PRC_DTL_GRP.BASE_PRICE.equals(priceLine.prcDtlGrpCd_LP.getValue())) {
                return priceLine;
            }
        }
        // Set Manual Data
        NWAL1500_ISMsg priceLine = glblMsg.I.no(glblMsg.I.getValidCount());
        if (ZYPCommonFunc.hasValue(lineMsg.entCpoDtlDealSlsAmt_LL)) {
            priceLine.manPrcAmtRate_LP.setValue(lineMsg.entCpoDtlDealSlsAmt_LL.getValue());
        } else {
            priceLine.manPrcAmtRate_LP.setValue(BigDecimal.ZERO);
        }
        priceLine.prcCondTpCd_LP.setValue(PRC_COND_TP.BASE_PRICE);
        priceLine.prcCondManEntryFlg_LP.setValue(ZYPConstant.FLG_OFF_N);
        priceLine.prcCondManAddFlg_LP.setValue(ZYPConstant.FLG_OFF_N);
        priceLine.prcCondManDelFlg_LP.setValue(ZYPConstant.FLG_OFF_N);
        priceLine.prcDtlGrpCd_LP.setValue(PRC_DTL_GRP.BASE_PRICE);
        priceLine.prcCondUnitCd_LP.setValue(PRC_COND_UNIT.AMT);
        priceLine.prcCalcMethCd_LP.setValue(PRC_CALC_METH.EACH_ACCOUNT);
        priceLine.autoPrcCcyCd_LP.setValue(CCY.US_DOLLAR);
        priceLine.autoPrcAmtRate_LP.setValue(BigDecimal.ZERO);
        priceLine.calcPrcAmtRate_LP.setValue(BigDecimal.ZERO);
        priceLine.unitPrcAmt_LP.setValue(BigDecimal.ZERO);

        priceLine.cpoDtlLineNum_LP.setValue(cpoDtlLineNum);
        priceLine.cpoDtlLineSubNum_LP.setValue(cpoDtlLineSubNum);
        priceLine.configCatgCd_LP.setValue(configCatgCd);
        priceLine.pkgUomCd_LP.setValue(lineMsg.custUomCd_LL.getValue());
        ZYPEZDItemValueSetter.setValue(priceLine.specCondPrcPk_LP, getSpecCondPrcPk(bizMsg.glblCmpyCd.getValue(), PRC_COND_TP.BASE_PRICE));
        // QC#9700  2018/09/03 Add Start
        priceLine.prcRuleApplyFlg_LP.setValue(ZYPConstant.FLG_ON_Y);
        // QC#9700  2018/09/03 Add End

        glblMsg.I.setValidCount(glblMsg.I.getValidCount() + 1);

        return priceLine;
    }

    /**
     * Get Base Price Date From Biz LMsg
     * @param rmaLineMsg NWAL1500_DCMsg
     * @param bizMsg NWAL1500CMsg
     * @return NWAL1500_ISMsg
     */
    public static NWAL1500_ISMsg getBasePriceDataFromBizlMsg(NWAL1500_DSMsg rmaLineMsg, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/25 S21_NA#19808 

        String xxLineNum = rmaLineMsg.xxLineNum_RL.getValue();
        String cpoDtlLineNum = rmaLineMsg.cpoDtlLineNum_RL.getValue();
        String cpoDtlLineSubNum = rmaLineMsg.cpoDtlLineSubNum_RL.getValue();
        String configCatgCd = CONFIG_CATG.INBOUND;
        for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
            NWAL1500_ISMsg priceLine = glblMsg.I.no(i);
            String xxLineNumP = priceLine.xxLineNum_LP.getValue();
            String cpoDtlLineNumP = priceLine.cpoDtlLineNum_LP.getValue();
            String cpoDtlLineSubNumP = priceLine.cpoDtlLineSubNum_LP.getValue();
            String configCatgCdP = priceLine.configCatgCd_LP.getValue();
            if ((xxLineNum.equals(xxLineNumP) || (cpoDtlLineNum.equals(cpoDtlLineNumP) && cpoDtlLineSubNum.equals(cpoDtlLineSubNumP))) && configCatgCd.equals(configCatgCdP)
                    && PRC_DTL_GRP.BASE_PRICE.equals(priceLine.prcDtlGrpCd_LP.getValue())) {
                return priceLine;
            }
        }
        // Set Manual Data
        NWAL1500_ISMsg priceLine = glblMsg.I.no(glblMsg.I.getValidCount());
        if (ZYPCommonFunc.hasValue(rmaLineMsg.entCpoDtlDealSlsAmt_RL)) {
            priceLine.manPrcAmtRate_LP.setValue(rmaLineMsg.entCpoDtlDealSlsAmt_RL.getValue());
        } else {
            priceLine.manPrcAmtRate_LP.setValue(BigDecimal.ZERO);
        }
        priceLine.prcCondTpCd_LP.setValue(PRC_COND_TP.BASE_PRICE);
        priceLine.prcCondManEntryFlg_LP.setValue(ZYPConstant.FLG_OFF_N);
        priceLine.prcCondManAddFlg_LP.setValue(ZYPConstant.FLG_OFF_N);
        priceLine.prcCondManDelFlg_LP.setValue(ZYPConstant.FLG_OFF_N);
        priceLine.prcDtlGrpCd_LP.setValue(PRC_DTL_GRP.BASE_PRICE);
        priceLine.prcCondUnitCd_LP.setValue(PRC_COND_UNIT.AMT);
        priceLine.prcCalcMethCd_LP.setValue(PRC_CALC_METH.EACH_ACCOUNT);
        priceLine.autoPrcCcyCd_LP.setValue(CCY.US_DOLLAR);
        priceLine.autoPrcAmtRate_LP.setValue(BigDecimal.ZERO);
        priceLine.calcPrcAmtRate_LP.setValue(BigDecimal.ZERO);
        priceLine.unitPrcAmt_LP.setValue(BigDecimal.ZERO);

        priceLine.cpoDtlLineNum_LP.setValue(cpoDtlLineNum);
        priceLine.cpoDtlLineSubNum_LP.setValue(cpoDtlLineSubNum);
        priceLine.configCatgCd_LP.setValue(configCatgCd);
        priceLine.pkgUomCd_LP.setValue(rmaLineMsg.custUomCd_RL.getValue());
        ZYPEZDItemValueSetter.setValue(priceLine.specCondPrcPk_LP, getSpecCondPrcPk(bizMsg.glblCmpyCd.getValue(), PRC_COND_TP.BASE_PRICE));
        // QC#9700  2018/09/03 Add Start
        priceLine.prcRuleApplyFlg_LP.setValue(ZYPConstant.FLG_ON_Y);
        // QC#9700  2018/09/03 Add End

        glblMsg.I.setValidCount(glblMsg.I.getValidCount() + 1);

        return priceLine;
    }

    /**
     * Get "BASE" Price Element from Global Message
     * @param lineMsg Line config Message
     * @param glblMsg Global Message
     * @return BASE price Element (same line number with lineMsg), if there is no "BASE" element, null
     */
    public static NWAL1500_ISMsg getBasePriceDataFromGlobalMsg(NWAL1500_BCMsg lineMsg, NWAL1500SMsg glblMsg) {
        String xxLineNum = lineMsg.xxLineNum_LL.getValue();
        String cpoDtlLineNum = lineMsg.cpoDtlLineNum_LL.getValue();
        String cpoDtlLineSubNum = lineMsg.cpoDtlLineSubNum_LL.getValue();
        for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
            NWAL1500_ISMsg priceLine = glblMsg.I.no(i);
            String xxLineNumP = priceLine.xxLineNum_LP.getValue();
            String cpoDtlLineNumP = priceLine.cpoDtlLineNum_LP.getValue();
            String cpoDtlLineSubNumP = priceLine.cpoDtlLineSubNum_LP.getValue();
            if ((xxLineNum.equals(xxLineNumP) || (cpoDtlLineNum.equals(cpoDtlLineNumP) && cpoDtlLineSubNum.equals(cpoDtlLineSubNumP))) && PRC_DTL_GRP.BASE_PRICE.equals(priceLine.prcDtlGrpCd_LP.getValue())) {
                return priceLine;
            }
        }
        return null;
    }

    /**
     * Call Pricing API of Get Line Manual Price Mode(05:Recalc)
     * @param bizMsg NWAL1500CMsg
     * @param slctConfIndex int
     * @param slctLineIndex int
     * @return NWZC157001PMsg
     */
    public static NWZC157001PMsg callPricingApiOfGetLineManPriceMode(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, int slctLineIndex) { // 2018/01/29 S21_NA#19808 Mod

        // 2018/01/29 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

        // Header
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
        // QC#9437 2016/06/21 Mod Start
        // ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, NWAL1500CommonLogicForLineConfig.getPrcBaseDt(bizMsg, slctLineIndex));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        // QC#9437 2016/06/21 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, bizMsg.custIssPoNum);
        // 2016/10/03 S21_NA#7131 Mod Start
//        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        // 2016/10/03 S21_NA#7131 Mod Start
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.leasePrchOptCd, bizMsg.leasePrchOptCd);
        // QC#22789 2017/11/28 Mod Start
        // ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot); // QC#4375
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_LD.getValue())) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_EM.getValue())) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot_EM);
        }
        // QC#22789 2017/11/28 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y); // 2016/04/13 S21_NA#6971 Add

        // Detail : Order Line
        NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(0);

        // Pricing Element
        String xxLineNum;
        String cpoDtlLineNum;
        String cpoDtlLineSubNum;
        String configCatgCd;
        int prcCnt = 0;
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500_ASMsg confMsg = glblMsg.A.no(slctConfIndex);
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineIndex);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineNum, lineMsg.cpoDtlLineNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineSubNum, lineMsg.cpoDtlLineSubNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcBaseDt, lineMsg.prcBaseDt_LL); //QC#9482 2016/06/10
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.billToCustCd, confMsg.billToCustCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shipToCustCd, confMsg.shipToCustCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_SH, confMsg.shipToCustAcctCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_BL, confMsg.billToCustAcctCd_LC);
            // Get Price Category Code
            if (ZYPCommonFunc.hasValue(lineMsg.prcCatgNm_LL)) {
                String prcCatgCd = NWAL1500CommonLogicForLineConfig.getPrcCatgCd(bizMsg, lineMsg.prcCatgNm_LL.getValue());
                ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgCd_LL, prcCatgCd);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCatgCd, prcCatgCd);
            }
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.csmpNum, lineMsg.csmpContrNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dlrRefNum, lineMsg.dlrRefNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcContrNum, bizMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaBrCd, bizMsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ccyCd, NWAL1500CommonLogicForLineConfig.getCcyCd(bizMsg, lineMsg));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcListEquipConfigNum, lineMsg.prcListEquipConfigNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdseCd, lineMsg.mdseCd_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.pkgUomCd, lineMsg.custUomCd_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdLineCatgCd, lineMsg.dsOrdLineCatgCd_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordQty, lineMsg.ordQty_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordCustUomQty, lineMsg.ordCustUomQty_LL);
            if (!ZYPCommonFunc.hasValue(lineMsg.invQty_LL)) {
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
            } else {
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, lineMsg.invQty_LL);
            }
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdlId, confMsg.mdlId_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.cntyNm_SH, confMsg.shipToCntyNm_LC); // QC#9192 Add
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctyAddr_SH, confMsg.shipToCtyAddr_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.stCd_SH, confMsg.shipToStCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctryCd_SH, confMsg.shipToCtryCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, NWAL1500CommonLogic.getWriterLineConfigRepCd(bizMsg.G, confMsg.dsOrdPosnNum_LC.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.rtlWhCd, lineMsg.rtlWhCd_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, bizMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaExtnCd, bizMsg.coaExtnCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxUnitPrc, lineMsg.entCpoDtlDealSlsAmt_LL); //QC6480 Add
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y); //QC6480 Add

            xxLineNum = lineMsg.xxLineNum_LL.getValue();
            cpoDtlLineNum = lineMsg.cpoDtlLineNum_LL.getValue();
            cpoDtlLineSubNum = lineMsg.cpoDtlLineSubNum_LL.getValue();
            configCatgCd = CONFIG_CATG.OUTBOUND;
        } else {
            NWAL1500_CSMsg rmaConfMsg = glblMsg.C.no(slctConfIndex);
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(slctLineIndex);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineNum, rmaLineMsg.cpoDtlLineNum_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineSubNum, rmaLineMsg.cpoDtlLineSubNum_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.configCatgCd, CONFIG_CATG.INBOUND);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcBaseDt, rmaLineMsg.prcBaseDt_RL); //QC#9482 2016/06/10
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.billToCustCd, rmaConfMsg.billToCustCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shipToCustCd, rmaConfMsg.shipToCustCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_SH, rmaConfMsg.shipToCustAcctCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_BL, rmaConfMsg.billToCustAcctCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCatgCd, rmaLineMsg.prcCatgCd_RL);
            // Get Price Category Code
            if (ZYPCommonFunc.hasValue(rmaLineMsg.prcCatgNm_RL)) {
                String prcCatgCd = NWAL1500CommonLogicForLineConfig.getPrcCatgCd(bizMsg, rmaLineMsg.prcCatgNm_RL.getValue());
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.prcCatgCd_RL, prcCatgCd);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCatgCd, prcCatgCd);
            }
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcContrNum, bizMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaBrCd, bizMsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ccyCd, NWAL1500CommonLogicForLineConfig.getCcyCdForRma(bizMsg, rmaLineMsg));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdseCd, rmaLineMsg.mdseCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.pkgUomCd, rmaLineMsg.custUomCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdLineCatgCd, rmaLineMsg.dsOrdLineCatgCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordQty, rmaLineMsg.ordQty_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordCustUomQty, rmaLineMsg.ordCustUomQty_RL);
            // 2019/08/09 S21_NA#52472 Mod Start
//            if (!ZYPCommonFunc.hasValue(rmaLineMsg.rtrnQty_RL)) {
            if (!NWAL1500Constant.LINE_STS_NM_CLOSED.equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue())) {
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
            } else {
//                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, rmaLineMsg.rtrnQty_RL);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, rmaLineMsg.ordQty_RL);
            }
            // 2019/08/09 S21_NA#52472 Mod End
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.rtrnRsnCd, rmaLineMsg.rtrnRsnCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdlId, rmaConfMsg.mdlId_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.cntyNm_SH, rmaConfMsg.shipToCntyNm_RC); // QC#9192 Add
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctyAddr_SH, rmaConfMsg.shipToCtyAddr_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.stCd_SH, rmaConfMsg.shipToStCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctryCd_SH, rmaConfMsg.shipToCtryCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, getWriterLineConfigRepCd(bizMsg.H, rmaConfMsg.dsOrdPosnNum_RC.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.rtlWhCd, rmaLineMsg.rtlWhCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, bizMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaExtnCd, bizMsg.coaExtnCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxUnitPrc, rmaLineMsg.entCpoDtlDealSlsAmt_RL); //QC6480 Add
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y); //QC6480 Add

            xxLineNum = rmaLineMsg.xxLineNum_RL.getValue();
            cpoDtlLineNum = rmaLineMsg.cpoDtlLineNum_RL.getValue();
            cpoDtlLineSubNum = rmaLineMsg.cpoDtlLineSubNum_RL.getValue();
            configCatgCd = CONFIG_CATG.INBOUND;
        }
        prcApiPMsg.NWZC157002PMsg.setValidCount(1);

        Map<String, String> prcCondTpMap = new HashMap<String, String>();
        for (int j = 0; j < glblMsg.I.getValidCount(); j++) {
            NWAL1500_ISMsg priceElementMsg = glblMsg.I.no(j);

            String xxLineNumLP = priceElementMsg.xxLineNum_LP.getValue();
            String cpoDtlLineNumLP = priceElementMsg.cpoDtlLineNum_LP.getValue();
            String cpoDtlLineSubNumLP = priceElementMsg.cpoDtlLineSubNum_LP.getValue();
            String configCatgCdLP = priceElementMsg.configCatgCd_LP.getValue();
            if ((xxLineNum.equals(xxLineNumLP) || (cpoDtlLineNum.equals(cpoDtlLineNumLP) && cpoDtlLineSubNum.equals(cpoDtlLineSubNumLP))) && configCatgCd.equals(configCatgCdLP)) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).trxLineNum, priceElementMsg.cpoDtlLineNum_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).trxLineSubNum, priceElementMsg.cpoDtlLineSubNum_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).configCatgCd, priceElementMsg.configCatgCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCondTpCd, priceElementMsg.prcCondTpCd_LP);

                String prcCondTpDescTxt = null;
                if (ZYPCommonFunc.hasValue(priceElementMsg.prcCondTpCd_LP)) {
                    prcCondTpDescTxt = prcCondTpMap.get(priceElementMsg.prcCondTpCd_LP.getValue());
                    if (null == prcCondTpDescTxt) {
                        prcCondTpDescTxt = getPrcCondTpDescTxt(bizMsg.glblCmpyCd.getValue(), priceElementMsg.prcCondTpCd_LP.getValue());
                        if (null != prcCondTpDescTxt) {
                            prcCondTpMap.put(priceElementMsg.prcCondTpCd_LP.getValue(), prcCondTpDescTxt);
                        } else {
                            prcCondTpMap.put(priceElementMsg.prcCondTpCd_LP.getValue(), "");
                        }
                    }
                }

                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCondTpDescTxt, prcCondTpDescTxt);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcDtlGrpCd, priceElementMsg.prcDtlGrpCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcJrnlGrpCd, priceElementMsg.prcJrnlGrpCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCatgCd, priceElementMsg.prcCatgCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCondManEntryFlg, priceElementMsg.prcCondManEntryFlg_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCondManAddFlg, priceElementMsg.prcCondManAddFlg_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCondManDelFlg, priceElementMsg.prcCondManDelFlg_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).pkgUomCd, priceElementMsg.pkgUomCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCondUnitCd, priceElementMsg.prcCondUnitCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCalcMethCd, priceElementMsg.prcCalcMethCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).autoPrcCcyCd, priceElementMsg.autoPrcCcyCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).autoPrcAmtRate, priceElementMsg.autoPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).maxPrcAmtRate, priceElementMsg.maxPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).minPrcAmtRate, priceElementMsg.minPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).manPrcAmtRate, priceElementMsg.manPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).calcPrcAmtRate, priceElementMsg.calcPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).unitPrcAmt, priceElementMsg.unitPrcAmt_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).dsMdsePrcPk, priceElementMsg.dsMdsePrcPk_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).specCondPrcPk, priceElementMsg.specCondPrcPk_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).frtPerWtPk, priceElementMsg.frtPerWtPk_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).ordPrcCalcBasePk, priceElementMsg.ordPrcCalcBasePk_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcRuleApplyFlg, priceElementMsg.prcRuleApplyFlg_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcRulePrcdPk, priceElementMsg.prcRulePrcdPk_LP);
                prcCnt++;
            }
        }
        prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.setValidCount(prcCnt);

        // call NWZC1570 Pricing API
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        return prcApiPMsg;
    }

    /**
     * <pre>
     * Call Pricing API Mode:04 Get Order All.
     * If error message will obtain, this method set error message on Biz Message.
     * </pre>
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param lineClose String
     * @return No Error : true
     */
    public static boolean callPricingApiGetOrderAllMode(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String lineClose) { // 2018/01/29 S21_NA#19808

        // numbering CPO_DTL_LINE_NUM, CPO_DTL_LINE_SUB_NUM
        numberingOrderRma(bizMsg, glblMsg); // 2018/01/29 S21_NA#19808

        Map<String, String> prcCondTpMap = new HashMap<String, String>();

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setHdrParam(bizMsg, prcApiPMsg, NWZC157001.GET_ORDER_ALL);

        setOutboundLineParam(bizMsg, glblMsg, prcApiPMsg, lineClose, prcCondTpMap); // 2018/01/29 S21_NA#19808
        setInboundLineParam(bizMsg, glblMsg, prcApiPMsg, lineClose, prcCondTpMap); // 2018/01/29 S21_NA#19808

        printPriceElem(bizMsg, glblMsg, "******** Before Call Pricing API ********");
        printPricingDetail(prcApiPMsg, "******** Before Call Pricing API ********");
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        }

        // 2018/04/24 S21_NA#25534 Add Start
        int errCnt = 0;
        // 2018/04/24 S21_NA#25534 Add End
        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg priceLine = prcApiPMsg.NWZC157002PMsg.no(i);

            // 2018/04/24 S21_NA#25534 Add Start
            NWAL1500_BSMsg lineMsg = getPricingErrorLineConfig(glblMsg, priceLine);
            NWAL1500_DSMsg rmaLineMsg = getPricingErrorLineRma(glblMsg, priceLine);
            // 2018/04/24 S21_NA#25534 Add End

            if (S21ApiUtil.isXxMsgId(priceLine)) {
                // 2018/05/31 S21_NA#26081 add start
                boolean errFlg = true;
                // 2018/05/31 S21_NA#26081 add end
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (int j = 0; j < msgList.size(); j++) {
                    S21ApiMessage msg = msgList.get(j);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    // 2018/04/24 S21_NA#25534 Del Start
//                    bizMsg.setMessageInfo(msgId, msgPrms);
//                    if (msgId.endsWith("E")) {
//                        return false;
//                    }
                    // 2018/04/24 S21_NA#25534 Del End
                    // 2018/04/24 S21_NA#25534 Add Start
                    if (lineMsg != null) {
                        lineMsg.prcCatgNm_LL.setErrorInfo(1, msgId, msgPrms);
                        if (errCnt == 0) {
                            bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
                            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg, lineMsg.xxPageNum_LL.getValueInt());
                        }
                    } else if (rmaLineMsg != null) {
                        rmaLineMsg.prcCatgNm_RL.setErrorInfo(1, msgId, msgPrms);
                        if (errCnt == 0) {
                            bizMsg.xxDplyTab.setValue(TAB_RMA);
                            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, rmaLineMsg.xxPageNum_RL.getValueInt());
                        }
                    } else {
                        bizMsg.setMessageInfo(msgId, msgPrms);
                    }
                    errCnt++;
                    // 2018/04/24 S21_NA#25534 Add End
                    // 201805/31 S21_NA#26081 add start
                    if (errFlg && msgId.endsWith("E")) {
                        errFlg = false;
                    }
                }
                if (!errFlg) {
                    return false;
                }
                // 201805/31 S21_NA#26081 add end
            }
//            for (int k = 0; k < prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.getValidCount(); k++) {
//                NWZC157003PMsg prcElementPMsg = prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(k);
//                if (S21ApiUtil.isXxMsgId(prcElementPMsg)) {
//                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcElementPMsg);
//                    for (S21ApiMessage msg : msgList) {
//                        String msgId = msg.getXxMsgid();
//                        String[] msgPrms = msg.getXxMsgPrmArray();
//                        bizMsg.setMessageInfo(msgId, msgPrms);
//                        if (msgId.endsWith("E")) {
//                            return false;
//                        }
//                    }
//                }
//            }
        }

        printPricingDetail(prcApiPMsg, "******** After Call Pricing API ********");
        printPricingResult(prcApiPMsg);

        // Set up Price Elements to CMsg
        glblMsg.I.clear();
        glblMsg.I.setValidCount(0);
        // S21_NA#12598 Del Start
        //glblMsg.I.clear();
        //glblMsg.I.setValidCount(0);
        // S21_NA#12598 Del End

        Map<String, NWAL1500_BSMsg> lineMsgMap = new HashMap<String, NWAL1500_BSMsg>(); // 2018/01/29 S21_NA#19808
        Map<String, NWAL1500_DSMsg> rmaLineMsgMap = new HashMap<String, NWAL1500_DSMsg>(); // 2018/01/29 S21_NA#19808
        setHdrTotalAmt(bizMsg, glblMsg, prcApiPMsg, lineMsgMap, rmaLineMsgMap); // 2018/01/29 S21_NA#19808

        // Line Config
        setLineConfigPricingData(bizMsg, glblMsg, prcApiPMsg, lineMsgMap);

        // RMA
        setLineRmaPricingData(bizMsg, glblMsg, prcApiPMsg, rmaLineMsgMap);

        printPriceElem(bizMsg, glblMsg, "******** After Call Pricing API ********");
        return true;
    }

    // QC#22965 2018/04/11 Mod Start
    public static boolean callPricingApiGetOrderManualMode(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String lineClose) { // 2018/01/29 S21_NA#19808

        // numbering CPO_DTL_LINE_NUM, CPO_DTL_LINE_SUB_NUM
        numberingOrderRma(bizMsg, glblMsg); // 2018/01/29 S21_NA#19808

        Map<String, String> prcCondTpMap = new HashMap<String, String>();

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setHdrParam(bizMsg, prcApiPMsg, NWZC157001.MANUAL_PRICE);

        setOutboundLineParam(bizMsg, glblMsg, prcApiPMsg, lineClose, prcCondTpMap); // 2018/01/29 S21_NA#19808
        setInboundLineParam(bizMsg, glblMsg, prcApiPMsg, lineClose, prcCondTpMap); // 2018/01/29 S21_NA#19808

        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        }

        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg priceLine = prcApiPMsg.NWZC157002PMsg.no(i);
            if (S21ApiUtil.isXxMsgId(priceLine)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (int j = 0; j < msgList.size(); j++) {
                    S21ApiMessage msg = msgList.get(j);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    if (msgId.endsWith("E")) {
                        return false;
                    }
                }
            }
        }

        printPricingDetail(prcApiPMsg, "******** After Call Pricing API ********");
        printPricingResult(prcApiPMsg);

        glblMsg.I.clear();
        glblMsg.I.setValidCount(0);

        Map<String, NWAL1500_BSMsg> lineMsgMap = new HashMap<String, NWAL1500_BSMsg>(); // 2018/01/29 S21_NA#19808
        Map<String, NWAL1500_DSMsg> rmaLineMsgMap = new HashMap<String, NWAL1500_DSMsg>(); // 2018/01/29 S21_NA#19808
        setHdrTotalAmt(bizMsg, glblMsg, prcApiPMsg, lineMsgMap, rmaLineMsgMap); // 2018/01/29 S21_NA#19808

        // Line Config
        setLineConfigPricingData(bizMsg, glblMsg, prcApiPMsg, lineMsgMap);

        // RMA
        setLineRmaPricingData(bizMsg, glblMsg, prcApiPMsg, rmaLineMsgMap);

        printPriceElem(bizMsg, glblMsg, "******** After Call Pricing API ********");
        return true;
    }
    // QC#22965 2018/04/11 Mod End
    /**
     * getParentConfig
     * @param bizMsg    NWAL1500CMsg
     * @param lineMsg   NWAL1500_BCMsg
     * @return  NWAL1500_ACMsg
     */
    public static NWAL1500_ACMsg getParentConfig(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {
        String xxLineNum = lineMsg.xxLineNum_LL.getValue();
        String[] xxLineNumArray = xxLineNum.split("\\.");
        String linePosNum = xxLineNumArray[0];
        for (int n = 0; n < bizMsg.A.getValidCount(); n++) {
            String configPosNum = bizMsg.A.no(n).dsOrdPosnNum_LC.getValue();
            if (linePosNum.equals(configPosNum)) {
                return bizMsg.A.no(n);
            }
        }
        return null;
    }

    // 2018/01/29 S21_NA#19808 Add Start
    /**
     * getParentConfig
     * @param glblMsg    NWAL1500SMsg
     * @param lineMsg   NWAL1500_BSMsg
     * @return  NWAL1500_ASMsg
     */
    public static NWAL1500_ASMsg getParentConfig(NWAL1500SMsg glblMsg, NWAL1500_BSMsg lineMsg) {
        String xxLineNum = lineMsg.xxLineNum_LL.getValue();
        String[] xxLineNumArray = xxLineNum.split("\\.");
        String linePosNum = xxLineNumArray[0];
        for (int n = 0; n < glblMsg.A.getValidCount(); n++) {
            String configPosNum = glblMsg.A.no(n).dsOrdPosnNum_LC.getValue();
            if (linePosNum.equals(configPosNum)) {
                return glblMsg.A.no(n);
            }
        }
        return null;
    }
    // 2018/01/29 S21_NA#19808 Add End

    /**
     * getParentConfig(RMA)
     * @param bizMsg    NWAL1500CMsg
     * @param rmaLineMsg    NWAL1500_DCMsg
     * @return  NWAL1500_CCMsg
     */
    public static NWAL1500_CCMsg getParentConfig(NWAL1500CMsg bizMsg, NWAL1500_DCMsg rmaLineMsg) {
        String xxLineNum = rmaLineMsg.xxLineNum_RL.getValue();
        String[] xxLineNumArray = xxLineNum.split("\\.");
        String linePosNum = xxLineNumArray[0];
        for (int n = 0; n < bizMsg.C.getValidCount(); n++) {
            String configPosNum = bizMsg.C.no(n).dsOrdPosnNum_RC.getValue();
            if (linePosNum.equals(configPosNum)) {
                return bizMsg.C.no(n);
            }
        }
        return null;
    }

    // 2018/01/29 S21_NA#19808 Add Start
    /**
     * getParentConfig(RMA)
     * @param glblMsg    NWAL1500SMsg
     * @param rmaLineMsg    NWAL1500_DSMsg
     * @return  NWAL1500_CSMsg
     */
    public static NWAL1500_CSMsg getParentConfig(NWAL1500SMsg glblMsg, NWAL1500_DSMsg rmaLineMsg) {
        String xxLineNum = rmaLineMsg.xxLineNum_RL.getValue();
        String[] xxLineNumArray = xxLineNum.split("\\.");
        String linePosNum = xxLineNumArray[0];
        for (int n = 0; n < glblMsg.C.getValidCount(); n++) {
            String configPosNum = glblMsg.C.no(n).dsOrdPosnNum_RC.getValue();
            if (linePosNum.equals(configPosNum)) {
                return glblMsg.C.no(n);
            }
        }
        return null;
    }

    /**
     * getParentConfig
     * @param configMsgArray Line Config Config Array
     * @param dsOrdPosnNum Position Number
     * @return NWAL1500_ASMsg
     */
    public static NWAL1500_ASMsg getParentConfig(NWAL1500_ASMsgArray configMsgArray, String dsOrdPosnNum) {
        for (int n = 0; n < configMsgArray.getValidCount(); n++) {
            String configPosNum = configMsgArray.no(n).dsOrdPosnNum_LC.getValue();
            if (dsOrdPosnNum.equals(configPosNum)) {
                return configMsgArray.no(n);
            }
        }
        return null;
    }
    // 2018/01/29 S21_NA#19808 Add End

    // 2016/01/08 S21_NA#2726 Add Start
    /**
     * getParentConfig
     * @param configMsgArray RMA Config Array
     * @param dsOrdPosnNum  Position Number
     * @return  NWAL1500_CSMsg
     */
    public static NWAL1500_CSMsg getParentConfig(NWAL1500_CSMsgArray configMsgArray, String dsOrdPosnNum) { // 2018/01/29 S21_NA#19808
        for (int n = 0; n < configMsgArray.getValidCount(); n++) {
            String configPosNum = configMsgArray.no(n).dsOrdPosnNum_RC.getValue();
            if (dsOrdPosnNum.equals(configPosNum)) {
                return configMsgArray.no(n);
            }
        }
        return null;
    }
    // 2016/01/08 S21_NA#2726 Add End

    // 2018/03/27 S21_NA#25060 Add Start
    /**
     * getParentConfig
     * @param configMsgArray Line Config Config Array
     * @param dsOrdPosnNum Position Number
     * @return NWAL1500_ACMsg
     */
    public static NWAL1500_ACMsg getParentConfig(NWAL1500_ACMsgArray configMsgArray, String dsOrdPosnNum) {
        for (int n = 0; n < configMsgArray.getValidCount(); n++) {
            String configPosNum = configMsgArray.no(n).dsOrdPosnNum_LC.getValue();
            if (dsOrdPosnNum.equals(configPosNum)) {
                return configMsgArray.no(n);
            }
        }
        return null;
    }
    // 2018/03/27 S21_NA#25060 Add End
    /**
     * Get Writer Line Config Rep Code(Conf Tab)
     * @param slsCrArrayConfig NWAL1500_GCMsgArray
     * @param dsOrdPosnNum DS Order Position Number
     * @return Writer Line Config Rep Code(Conf Tab)
     */
    public static String getWriterLineConfigRepCd(NWAL1500_GCMsgArray slsCrArrayConfig, String dsOrdPosnNum) {

        for (int n = 0; n < slsCrArrayConfig.getValidCount(); n++) {
            String slsCrDsOrdPosnNum = slsCrArrayConfig.no(n).dsOrdPosnNum_GS.getValue();
            // 2019/02/25 QC#30372 Mod Start
            //String roleTpCd = slsCrArrayConfig.no(n).slsRepRoleTpCd_GS.getValue();
            //if (dsOrdPosnNum.equals(slsCrDsOrdPosnNum) && (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(roleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(roleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(roleTpCd))) {
            if (dsOrdPosnNum.equals(slsCrDsOrdPosnNum)) {
            // 2019/02/25 QC#30372 Mod End
                return slsCrArrayConfig.no(n).slsRepTocCd_GS.getValue();
            }
        }

        return null;
    }

    /**
     * Get Writer Line Config Rep Code(RMA Tab)
     * @param slsCrArrayConfig NWAL1500_HCMsgArray
     * @param dsOrdPosnNum DS Order Position Number
     * @return Writer Line Config Rep Code(Conf Tab)
     */
    public static String getWriterLineConfigRepCd(NWAL1500_HCMsgArray slsCrArrayConfig, String dsOrdPosnNum) {

        for (int n = 0; n < slsCrArrayConfig.getValidCount(); n++) {
            String slsCrDsOrdPosnNum = slsCrArrayConfig.no(n).dsOrdPosnNum_HS.getValue();
            // 2019/02/25 QC#30372 Mod Start
            //String roleTpCd = slsCrArrayConfig.no(n).slsRepRoleTpCd_HS.getValue();
            // 2017/11/02 S21_NA#20146 Mod Start
            //if (dsOrdPosnNum.equals(slsCrDsOrdPosnNum) && (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(roleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(roleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(roleTpCd))) {
            //if (dsOrdPosnNum.equals(slsCrDsOrdPosnNum)
            //        && (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(roleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(roleTpCd)
            //                || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(roleTpCd) || LINE_BIZ_ROLE_TP.EMSD_WRITER.equals(roleTpCd))) {
            if (dsOrdPosnNum.equals(slsCrDsOrdPosnNum)) {
            // 2017/11/02 S21_NA#20146 Mod End
            // 2019/02/25 QC#30372 Mod End
                return slsCrArrayConfig.no(n).slsRepTocCd_HS.getValue();
            }
        }

        return null;
    }

    /**
     * Numbering CPO_DTL_LINE_NUM, CPO_DTL_LINE_SUB_NUM
     * @param bizMsg Business Message
     */
    public static void numberingOrderLineNumber(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/29 S21_NA#19808

        // 2018/01/29 S21_NA#19808 bizMsg.B, DD => glblMsg.A, B, C, D
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            // for LineConfig
            String maxLineNum = "000";
            NWAL1500_BSMsgArray lineArray = glblMsg.B;
            for (int i = 0; i < lineArray.getValidCount(); i++) {
                NWAL1500_BSMsg lineMsg = lineArray.no(i);
                if (ZYPCommonFunc.hasValue(lineMsg.cpoDtlLineNum_LL)) {
                    if (lineMsg.cpoDtlLineNum_LL.getValue().compareTo(maxLineNum) > 0) {
                        maxLineNum = lineMsg.cpoDtlLineNum_LL.getValue();
                    }
                }
            }
            //QC743
            //            NWAL1500_JSMsgArray lineBackArray = glblMsg.J;
            //            for (int i = 0; i < lineBackArray.getValidCount(); i++) {
            //                NWAL1500_JSMsg lineMsg = lineBackArray.no(i);
            //                if (ZYPCommonFunc.hasValue(lineMsg.cpoDtlLineNum_LL)) {
            //                    if (lineMsg.cpoDtlLineNum_LL.getValue().compareTo(maxLineNum) > 0) {
            //                        maxLineNum = lineMsg.cpoDtlLineNum_LL.getValue();
            //                    }
            //                }
            //            }
            String nextLineNum = maxLineNum;
            for (int i = 0; i < lineArray.getValidCount(); i++) {
                NWAL1500_BSMsg lineMsg = lineArray.no(i);
                if (!ZYPCommonFunc.hasValue(lineMsg.cpoDtlLineNum_LL)) {
                    nextLineNum = getNextCpoDtlLineNum(nextLineNum);
                    lineMsg.cpoDtlLineNum_LL.setValue(nextLineNum);
                    if (isSetMerchandise(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue())) {

                        lineMsg.cpoDtlLineSubNum_LL.setValue("000");

                        int subIndex = 1;
                        if (i + subIndex < lineArray.getValidCount()) {
                            int lineSubNum = 1;
                            NWAL1500_BSMsg childLineMsg = lineArray.no(i + subIndex);
                            while (isSameLineByDsCpoLineNum(lineMsg, childLineMsg)) {

                                childLineMsg.cpoDtlLineNum_LL.setValue(nextLineNum);
                                childLineMsg.cpoDtlLineSubNum_LL.setValue(ZYPCommonFunc.leftPad(String.valueOf(lineSubNum++), IDX_3, "0"));

                                subIndex++;
                                if (i + subIndex >= lineArray.getValidCount()) {
                                    break;
                                }
                                childLineMsg = lineArray.no(i + subIndex);
                            }
                            i += (subIndex - 1);
                        }
                    } else {
                        lineMsg.cpoDtlLineSubNum_LL.setValue("001");
                    }
                }
            }
        } else {
            // for RMA
            String maxLineNum = "000";
            NWAL1500_DSMsgArray lineArray = glblMsg.D;
            for (int i = 0; i < lineArray.getValidCount(); i++) {
                NWAL1500_DSMsg lineMsg = lineArray.no(i);
                if (ZYPCommonFunc.hasValue(lineMsg.cpoDtlLineNum_RL)) {
                    if (lineMsg.cpoDtlLineNum_RL.getValue().compareTo(maxLineNum) > 0) {
                        maxLineNum = lineMsg.cpoDtlLineNum_RL.getValue();
                    }
                }
            }
            // QC743
            //            NWAL1500_KSMsgArray lineBackArray = glblMsg.K;
            //            for (int i = 0; i < lineBackArray.getValidCount(); i++) {
            //                NWAL1500_KSMsg lineMsg = lineBackArray.no(i);
            //                if (ZYPCommonFunc.hasValue(lineMsg.cpoDtlLineNum_RL)) {
            //                    if (lineMsg.cpoDtlLineNum_RL.getValue().compareTo(maxLineNum) > 0) {
            //                        maxLineNum = lineMsg.cpoDtlLineNum_RL.getValue();
            //                    }
            //                }
            //            }
            String nextLineNum = maxLineNum;
            for (int i = 0; i < lineArray.getValidCount(); i++) {
                NWAL1500_DSMsg lineMsg = lineArray.no(i);
                if (!ZYPCommonFunc.hasValue(lineMsg.cpoDtlLineNum_RL)) {
                    nextLineNum = getNextCpoDtlLineNum(nextLineNum);
                    lineMsg.cpoDtlLineNum_RL.setValue(nextLineNum);
                    if (isSetMerchandise(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_RL.getValue())) {

                        lineMsg.cpoDtlLineSubNum_RL.setValue("000");

                        int subIndex = 1;
                        if (i + subIndex < lineArray.getValidCount()) {
                            int lineSubNum = 1;
                            NWAL1500_DSMsg childLineMsg = lineArray.no(i + subIndex);
                            while (isSameLineByDsCpoLineNum(lineMsg, childLineMsg)) {

                                childLineMsg.cpoDtlLineNum_RL.setValue(nextLineNum);
                                childLineMsg.cpoDtlLineSubNum_RL.setValue(ZYPCommonFunc.leftPad(String.valueOf(lineSubNum++), IDX_3, "0"));

                                subIndex++;
                                if (i + subIndex >= lineArray.getValidCount()) {
                                    break;
                                }
                                childLineMsg = lineArray.no(i + subIndex);
                            }
                            i += (subIndex - 1);
                        }
                    } else {
                        lineMsg.cpoDtlLineSubNum_RL.setValue("001");
                    }
                }
            }
        }
    }

    /**
     * getNextCpoDtlLineNum
     * @param lineNum   String
     * @return  String
     */
    public static String getNextCpoDtlLineNum(String lineNum) {

        char[] digit1 = S21StringUtil.subStringByLength(lineNum, 0, 1).toCharArray();
        int digit23 = Integer.parseInt(S21StringUtil.subStringByLength(lineNum, 1, 2));

        // increment line number
        digit23++;
        digit23 = digit23 % 100;
        if (digit23 == 0) {
            if (digit1[0] == 0x0039) {
                digit1[0] = 0x0041;
            } else {
                digit1[0]++;
            }
        }

        return String.valueOf(digit1) + ZYPCommonFunc.leftPad(String.valueOf(digit23), 2, "0");
    }

    /**
     * <pre>
     * Numbering CPO_DTL_LINE_NUM, CPO_DTL_LINE_SUB_NUM.
     * for Line Config and RMA, using numberingOrderLineNumber.
     * </pre>
     * @param bizMsg Business Message
     */
    public static void numberingOrderRma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/29 S21_NA#19808
        String tabName = bizMsg.xxDplyTab.getValue();
        // For Line Config
        bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
        numberingOrderLineNumber(bizMsg, glblMsg);
        // For RMA
        bizMsg.xxDplyTab.setValue(TAB_RMA);
        numberingOrderLineNumber(bizMsg, glblMsg);

        bizMsg.xxDplyTab.setValue(tabName);
    }

    private static NWAL1500_BSMsg getLineMsg(NWAL1500SMsg glblMsg, String cpoDtlLineNum, String cpoDtlLineSubNum) { // 2018/01/29 S21_NA#19808
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            if (cpoDtlLineNum.equals(glblMsg.B.no(i).cpoDtlLineNum_LL.getValue()) && cpoDtlLineSubNum.equals(glblMsg.B.no(i).cpoDtlLineSubNum_LL.getValue())) {
                return glblMsg.B.no(i);
            }
        }
        return null;
    }

    private static NWAL1500_DSMsg getRmaLineMsg(NWAL1500SMsg glblMsg, String cpoDtlLineNum, String cpoDtlLineSubNum) { // 2018/01/29 S21_NA#19808
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            if (cpoDtlLineNum.equals(glblMsg.D.no(i).cpoDtlLineNum_RL.getValue()) && cpoDtlLineSubNum.equals(glblMsg.D.no(i).cpoDtlLineSubNum_RL.getValue())) {
                return glblMsg.D.no(i);
            }
        }
        return null;
    }

    /**
     * setHdrParam
     * @param bizMsg    NWAL1500CMsg
     * @param prcApiPMsg    NWZC157001PMsg
     */
    public static void setHdrParam(NWAL1500CMsg bizMsg, NWZC157001PMsg prcApiPMsg, String mode) {
        // Header
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        prcApiPMsg.xxModeCd.setValue(mode);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        prcApiPMsg.prcCtxTpCd.setValue(PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.trxHdrNum, bizMsg.cpoOrdNum);
        // ADD START 2015/12/10 #1364
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.csmpNum, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dlrRefNum, bizMsg.dlrRefNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, getCoaBrCd(bizMsg));
        // ADD END 2015/12/10 #1364
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, bizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.negoDealAmt, bizMsg.negoDealAmt);
        prcApiPMsg.taxCalcFlg.setValue(ZYPConstant.FLG_ON_Y);
        // 2017/06/19 QC#19311 Add Start
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsPmtMethCd, bizMsg.dsPmtMethCd);
        // 2017/06/19 QC#19311 Add End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.leasePrchOptCd, bizMsg.leasePrchOptCd);
        // QC#22789 2017/11/28 Mod Start
        //ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot); // QC#4375
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_LD.getValue())) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_EM.getValue())) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot_EM);
        }
        // QC#22789 2017/11/28 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);
    }

    /**
     * setOutboundLineParam
     * @param bizMsg    NWAL1500CMsg
     * @param prcApiPMsg    NWZC157001PMsg
     * @param lineClose     String
     * @param prcCondTpMap  Map<String, String>
     */
    @SuppressWarnings("unchecked")
    public static void setOutboundLineParam(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWZC157001PMsg prcApiPMsg, String lineClose, Map<String, String> prcCondTpMap) { // 2018/01/29 S21_NA#19808
        // 2018/01/29 S21_NA#19808 bizMsg.B => glblMsg.B
        // Detail: Line Config
        int i = prcApiPMsg.NWZC157002PMsg.getValidCount();
        //        String dsOrdPosnNum = null;

        // 2016/01/28 S21_NA#3254 Add Start
        String ordLineSts = bizMsg.varCharConstVal_TB.getValue();
        // 2016/01/28 S21_NA#3254 Add End

        // For Performance QC#11618 Add Start
        Map<String, String> ccyCdMap = new HashMap<String, String>();
        // For Performance QC#11618 Add End

        // QC#21106 2017/09/25 Add Start
        Map<List, String> rtlWhMap = new HashMap<List, String>();
        for (int lineConfCnt = 0; lineConfCnt < glblMsg.B.getValidCount() && i < prcApiPMsg.NWZC157002PMsg.length(); lineConfCnt++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(lineConfCnt);
            if (ordLineSts.equals(lineMsg.ordLineStsDescTxt_LL.getValue())
                    || ORD_LINE_STS.CANCELLED.equals(lineMsg.ordLineStsCd_LL.getValue())) {
                continue;
            }
            if(!ZYPCommonFunc.hasValue(lineMsg.dplyLineRefNum_LL.getValue()) && ZYPCommonFunc.hasValue(lineMsg.rtlWhCd_LL.getValue())){
                List<String> key = new ArrayList<String>();
                key.add(lineMsg.dsOrdPosnNum_LL.getValue());
                key.add(lineMsg.xxLineNum_LL.getValue());
                // 2017/10/18 S21_NA#21730 Mod Start
                String origInvtyLocCd = lineMsg.origInvtyLocCd_LL.getValue();
                String rtlWhCd = null;
                Map<String, String> map = (Map<String, String>) NWAL1500Query.getInstance().getRtlWh(bizMsg.glblCmpyCd.getValue(), origInvtyLocCd).getResultObject();
                if(map != null){
                    rtlWhCd = map.get("RTL_WH_CD");
                }
                if (ZYPCommonFunc.hasValue(origInvtyLocCd)) {
                    // QC#22031 2017/10/31 Mod Start
                    //rtlWhMap.put(key, origInvtyLocCd);
                    if (isVendorCode(bizMsg.glblCmpyCd.getValue(), origInvtyLocCd)) {
                        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd, NWAL1500Constant.DROP_SHIP_WH);
                    } else {
                        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd, rtlWhCd);
                    }
                    rtlWhMap.put(key, prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd.getValue());
                    // QC#22031 2017/10/31 Mod End
                } else {
                    rtlWhMap.put(key, lineMsg.rtlWhCd_LL.getValue());
                }
                // 2017/10/18 S21_NA#21730 Mod End
            }
        }
        // QC#21106 2017/09/25 Add End
        for (int lineConfCnt = 0; lineConfCnt < glblMsg.B.getValidCount() && i < prcApiPMsg.NWZC157002PMsg.length(); lineConfCnt++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(lineConfCnt);

            // 2016/01/28 S21_NA#3254 Add Start
            if (ordLineSts.equals(lineMsg.ordLineStsDescTxt_LL.getValue())
                    || ORD_LINE_STS.CANCELLED.equals(lineMsg.ordLineStsCd_LL.getValue())) {
                continue;
            }
            // 2016/01/28 S21_NA#3254 Add End

            NWAL1500_ASMsg configLine = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.A, lineMsg.dsOrdPosnNum_LL.getValue());// 2018/01/29 S21_NA#19808

            String xxLineNum = lineMsg.xxLineNum_LL.getValue();
            String[] xxLineNumArray = xxLineNum.split("\\.");
            if (xxLineNumArray.length == 3 && BigDecimal.ZERO.compareTo(new BigDecimal(xxLineNumArray[2])) != 0) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).dsOrdPosnNum, configLine.dsOrdPosnNum_LC); //QC#6432 2016/04/07
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).trxLineNum, lineMsg.cpoDtlLineNum_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).trxLineSubNum, lineMsg.cpoDtlLineSubNum_LL);
            prcApiPMsg.NWZC157002PMsg.no(i).configCatgCd.setValue(CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).prcBaseDt, lineMsg.prcBaseDt_LL); //QC#9482 2016/06/10
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).billToCustCd, configLine.billToCustCd_LC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).shipToCustCd, configLine.shipToCustCd_LC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).dsAcctNum_SH, configLine.shipToCustAcctCd_LC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).dsAcctNum_BL, configLine.billToCustAcctCd_LC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).prcCatgCd, lineMsg.prcCatgCd_LL);
            // 2016/01/26 S21_NA#3553 Add Mod Start
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).csmpNum, lineMsg.csmpContrNum_LL);
            if (ZYPCommonFunc.hasValue(bizMsg.csmpContrNum) && !ZYPCommonFunc.hasValue(lineMsg.csmpContrNum_LL)) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).csmpNum, bizMsg.csmpContrNum);
            } else {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).csmpNum, lineMsg.csmpContrNum_LL);
            }
            // 2016/01/26 S21_NA#3553 Add Mod End
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).dlrRefNum, lineMsg.dlrRefNum_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).prcContrNum, bizMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).coaBrCd, bizMsg.coaBrCd);
            if (ZYPCommonFunc.hasValue(lineMsg.prcCatgNm_LL)) {
                // Sell Price Category
                // For Performance QC#11618 Mod Start
                // S21SsmEZDResult ssmRslt = NWAL1500QueryForSaveSubmit.getInstance().getCcyCdByPriceCatgNm(bizMsg, lineMsg.prcCatgNm_LL.getValue());
                // if (ssmRslt.isCodeNormal()) {
                //     List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
                //     ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).ccyCd, rsltMapList.get(0).get("CCY_CD"));
                // }

                String ccyCd = getCcyCdFromCache(bizMsg, lineMsg.prcCatgNm_LL.getValue(), ccyCdMap);
                if (ZYPCommonFunc.hasValue(ccyCd)) {
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).ccyCd, ccyCd);
                }
                // For Performance QC#11618 Mod End
            }
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).prcListEquipConfigNum, lineMsg.prcListEquipConfigNum_LL); // 2015/12/22 S21_NA#2259
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).mdseCd, lineMsg.mdseCd_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).pkgUomCd, lineMsg.custUomCd_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).dsOrdLineCatgCd, lineMsg.dsOrdLineCatgCd_LL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).ordQty, lineMsg.ordQty_LL); // Nead confirmation!!!!
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).ordCustUomQty, lineMsg.ordCustUomQty_LL); // Nead confirmation!!!
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).invQty, lineMsg.ordCustUomQty_LL);
            // S21_NA#1263
            if (!ZYPCommonFunc.hasValue(lineMsg.invQty_LL)) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).invQty, BigDecimal.ZERO);
            } else {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).invQty, lineMsg.invQty_LL);
            }
            // QC#6492 Start -> QC#11075
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).mdlId, configLine.mdlId_LC);
            //if (ZYPConstant.FLG_ON_Y.equals(lineMsg.baseCmptFlg_LL.getValue())) {
            //    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).mdlId, configLine.mdlId_LC);
            //} else {
            //    prcApiPMsg.NWZC157002PMsg.no(i).mdlId.clear();
            //}

            GLBL_CMPYTMsg glblCmpy = new GLBL_CMPYTMsg();
            glblCmpy.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            // For Performance QC#11618 Mod Start
            // glblCmpy = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(glblCmpy);
            glblCmpy = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpy);
            // For Performance QC#11618 Mod End
            if (glblCmpy.ctryCd.getValue().equals(configLine.shipToCtryCd_LC.getValue())) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).cntyNm_SH, configLine.shipToCntyNm_LC); // QC#9192 Add
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).ctyAddr_SH, configLine.shipToCtyAddr_LC);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).stCd_SH, configLine.shipToStCd_LC);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).postCd_SH, configLine.shipToPostCd_LC);
            }
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).ctryCd_SH, configLine.shipToCtryCd_LC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).slsRepOrSlsTeamTocCd, NWAL1500CommonLogic.getWriterLineConfigRepCd(bizMsg.G, configLine.dsOrdPosnNum_LC.getValue()));
            // QC#21106 2017/09/25 Add Start
            //ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd, lineMsg.rtlWhCd_LL);
            String origInvtyLocCd = lineMsg.origInvtyLocCd_LL.getValue();
            String rtlWhCd = null;
            String rtlSWhCd = null;
            Map<String, String> map = (Map<String, String>) NWAL1500Query.getInstance().getRtlWh(bizMsg.glblCmpyCd.getValue(), origInvtyLocCd).getResultObject();
            if (map != null) {
                rtlWhCd = map.get("RTL_WH_CD");
                rtlSWhCd = map.get("RTL_SWH_CD");
            }
            if(ZYPCommonFunc.hasValue(lineMsg.dplyLineRefNum_LL.getValue()) && !ZYPCommonFunc.hasValue(lineMsg.rtlWhCd_LL.getValue())){
                List<String> key = new ArrayList<String>();
                key.add(lineMsg.dsOrdPosnNum_LL.getValue());
                key.add(lineMsg.dplyLineRefNum_LL.getValue());
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd, rtlWhMap.get(key));
            } else {
                // 2017/10/18 S21_NA#21730 Mod Start
                //ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd, lineMsg.rtlWhCd_LL);
                if (ZYPCommonFunc.hasValue(origInvtyLocCd)) {
                    // QC#22031 2017/10/17 Mod Start
                    //ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd, origInvtyLocCd);
                    if (isVendorCode(bizMsg.glblCmpyCd.getValue(), origInvtyLocCd)) {
                        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd, NWAL1500Constant.DROP_SHIP_WH);
                    } else {
                        //ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd, NWAL1500Query.getInstance().getRtlWh(bizMsg.glblCmpyCd.getValue(), origInvtyLocCd));
                        ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd, rtlWhCd);
                    }
                    // QC#22031 2017/10/17 Mod End
                } else {
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd, lineMsg.rtlWhCd_LL);
                }
                // 2017/10/18 S21_NA#21730 Mod End
            }
            // QC#21106 2017/09/25 Add End
            if (ZYPCommonFunc.hasValue(origInvtyLocCd)) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd, rtlSWhCd);
            } else {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlSwhCd, lineMsg.rtlSwhCd_LL);
            }
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).frtCondCd, bizMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
            // 2017/07/11 S21_NA#19782 Mod Start
//            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).xxPrcCloFlg, lineClose);
            MDSETMsg mdse = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
            // 2017/10/18 S21_NA#21708 Mod Start
//            if (ZYPCommonFunc.hasValue(mdse.svcCovTmplTpCd) && S21StringUtil.isEquals(lineMsg.crRebilCd_LL.getValue(), CR_REBIL.REBILL)) {
            // QC#22238 2017/11/02 Mod Start
            //if (S21StringUtil.isEquals(CR_REBIL.CREDIT, lineMsg.crRebilCd_LL.getValue()) //
            //        || S21StringUtil.isEquals(CR_REBIL.REBILL, lineMsg.crRebilCd_LL.getValue())) { // 2017/10/18 S21_NA#21708 Mod End
            if (ZYPCommonFunc.hasValue(mdse.svcCovTmplTpCd) && S21StringUtil.isEquals(lineMsg.crRebilCd_LL.getValue(), CR_REBIL.REBILL)) {
            // QC#22238 2017/11/02 Mod End
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).xxPrcCloFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).xxPrcCloFlg, lineClose);
            }
            // 2018/08/02 S21_NA#26666 add start
            CPO_SRC_TPTMsg cpoSrcTpTMsg = getCpoSrcTp(lineMsg.cpoSrcTpDescTxt_LL.getValue(), bizMsg.glblCmpyCd.getValue());
            if (ZYPConstant.FLG_ON_Y.equals(cpoSrcTpTMsg.prcFrzFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).xxPrcCloFlg, ZYPConstant.FLG_ON_Y);
            }
            // 2018/08/02 S21_NA#26666 add end

            // 2017/07/11 S21_NA#19782 Mod End
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).coaExtnCd, bizMsg.coaExtnCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).xxUnitPrc, lineMsg.entCpoDtlDealSlsAmt_LL); //QC6480 Add


            // Pricing Element
            String xxLineNumLL = lineMsg.xxLineNum_LL.getValue();
            String cpoDtlLineNumLL = lineMsg.cpoDtlLineNum_LL.getValue();
            String cpoDtlLineSubNumLL = lineMsg.cpoDtlLineSubNum_LL.getValue();
            String configCatgCdLL = CONFIG_CATG.OUTBOUND;
            int prcCnt = 0;

            boolean manualFlg = false;
            for (int j = 0; j < glblMsg.I.getValidCount(); j++) {
                NWAL1500_ISMsg priceElementMsg = glblMsg.I.no(j);

                String xxLineNumLP = priceElementMsg.xxLineNum_LP.getValue();
                String cpoDtlLineNumLP = priceElementMsg.cpoDtlLineNum_LP.getValue();
                String cpoDtlLineSubNumLP = priceElementMsg.cpoDtlLineSubNum_LP.getValue();
                String configCatgCdLP = priceElementMsg.configCatgCd_LP.getValue();
                if ((xxLineNumLL.equals(xxLineNumLP) || (cpoDtlLineNumLL.equals(cpoDtlLineNumLP) && cpoDtlLineSubNumLL.equals(cpoDtlLineSubNumLP))) && configCatgCdLL.equals(configCatgCdLP)) {
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).trxLineNum, priceElementMsg.cpoDtlLineNum_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).trxLineSubNum, priceElementMsg.cpoDtlLineSubNum_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).configCatgCd, priceElementMsg.configCatgCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCondTpCd, priceElementMsg.prcCondTpCd_LP);

                    String prcCondTpDescTxt = null;
                    if (ZYPCommonFunc.hasValue(priceElementMsg.prcCondTpCd_LP)) {
                        prcCondTpDescTxt = prcCondTpMap.get(priceElementMsg.prcCondTpCd_LP.getValue());
                        if (null == prcCondTpDescTxt) {
                            prcCondTpDescTxt = getPrcCondTpDescTxt(bizMsg.glblCmpyCd.getValue(), priceElementMsg.prcCondTpCd_LP.getValue());
                            if (null != prcCondTpDescTxt) {
                                prcCondTpMap.put(priceElementMsg.prcCondTpCd_LP.getValue(), prcCondTpDescTxt);
                            } else {
                                prcCondTpMap.put(priceElementMsg.prcCondTpCd_LP.getValue(), "");
                            }
                        }
                    }

                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCondTpDescTxt, prcCondTpDescTxt);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcDtlGrpCd, priceElementMsg.prcDtlGrpCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcJrnlGrpCd, priceElementMsg.prcJrnlGrpCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCatgCd, priceElementMsg.prcCatgCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCondManEntryFlg, priceElementMsg.prcCondManEntryFlg_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCondManAddFlg, priceElementMsg.prcCondManAddFlg_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCondManDelFlg, priceElementMsg.prcCondManDelFlg_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).pkgUomCd, priceElementMsg.pkgUomCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCondUnitCd, priceElementMsg.prcCondUnitCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCalcMethCd, priceElementMsg.prcCalcMethCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).autoPrcCcyCd, priceElementMsg.autoPrcCcyCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).autoPrcAmtRate, priceElementMsg.autoPrcAmtRate_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).maxPrcAmtRate, priceElementMsg.maxPrcAmtRate_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).minPrcAmtRate, priceElementMsg.minPrcAmtRate_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).manPrcAmtRate, priceElementMsg.manPrcAmtRate_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).calcPrcAmtRate, priceElementMsg.calcPrcAmtRate_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).unitPrcAmt, priceElementMsg.unitPrcAmt_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).dsMdsePrcPk, priceElementMsg.dsMdsePrcPk_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).specCondPrcPk, priceElementMsg.specCondPrcPk_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).frtPerWtPk, priceElementMsg.frtPerWtPk_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).ordPrcCalcBasePk, priceElementMsg.ordPrcCalcBasePk_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcRuleApplyFlg, priceElementMsg.prcRuleApplyFlg_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcRulePrcdPk, priceElementMsg.prcRulePrcdPk_LP);
                    // QC#6480 2016/06/06 Add Start
                    if (PRC_DTL_GRP.BASE_PRICE.equals(priceElementMsg.prcDtlGrpCd_LP.getValue())) {
                        if (lineMsg.entCpoDtlDealSlsAmt_LL.getValue().compareTo(priceElementMsg.autoPrcAmtRate_LP.getValue()) != 0) {
                            manualFlg = true;
                        }
                    }
                    // QC#6480 2016/06/06 Add End
                    prcCnt++;
                }
            }
            prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.setValidCount(prcCnt);
            // QC#6480 2016/06/06 Add Start
            if (manualFlg) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
            }
            // QC#22238 2017/11/02 Add Start
            // QC#6480 2016/06/06 Add End
            if (S21StringUtil.isEquals(CR_REBIL.CREDIT, lineMsg.crRebilCd_LL.getValue()) //
                    || S21StringUtil.isEquals(CR_REBIL.REBILL, lineMsg.crRebilCd_LL.getValue())) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            }
            // QC#22238 2017/11/02 Add End
            i++;
        }
        prcApiPMsg.NWZC157002PMsg.setValidCount(i);
    }

    private static void setInboundLineParam(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWZC157001PMsg prcApiPMsg, String lineClose, Map<String, String> prcCondTpMap) { // 2018/01/29 S21_NA#19808
        // 2018/01/29 S21_NA#19808 bizMsg.D => glblMsg.D
        // Detail: RMA Line Config
        int i = prcApiPMsg.NWZC157002PMsg.getValidCount();

        // 2016/01/28 S21_NA#3254 Add Start
        String ordLineSts = bizMsg.varCharConstVal_TB.getValue();
        // 2016/01/28 S21_NA#3254 Add End

        // For Performance QC#11618 Add Start
        Map<String, String> ccyCdMap = new HashMap<String, String>();
        // For Performance QC#11618 Add End

        // QC#21106 2017/09/25 Add Start
        Map<List, String> rtlWhMap = new HashMap<List, String>();
        for (int lineConfCnt = 0; lineConfCnt < glblMsg.D.getValidCount() && i < prcApiPMsg.NWZC157002PMsg.length(); lineConfCnt++) {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(lineConfCnt);
            if (ordLineSts.equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue())
                    || RTRN_LINE_STS.CANCELLED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())
                    || RTRN_LINE_STS.CLOSED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())) { // 2016/09/06 S21_NA#12435 Add
                continue;
            }
            if(!ZYPCommonFunc.hasValue(rmaLineMsg.dplyLineRefNum_RL.getValue()) && ZYPCommonFunc.hasValue(rmaLineMsg.rtlWhCd_RL.getValue())){
                List<String> key = new ArrayList<String>();
                key.add(rmaLineMsg.dsOrdPosnNum_RL.getValue());
                key.add(rmaLineMsg.xxLineNum_RL.getValue());
                rtlWhMap.put(key, rmaLineMsg.rtlWhCd_RL.getValue());
            }
        }
        // QC#21106 2017/09/25 Add End
        for (int lineConfCnt = 0; lineConfCnt < glblMsg.D.getValidCount() && i < prcApiPMsg.NWZC157002PMsg.length(); lineConfCnt++) {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(lineConfCnt);
            // 2016/01/28 S21_NA#3254 Add Start
            if (ordLineSts.equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue())
                    || RTRN_LINE_STS.CANCELLED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())
                    || RTRN_LINE_STS.CLOSED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())) { // 2016/09/06 S21_NA#12435 Add
                continue;
            }
            // 2016/01/28 S21_NA#3254 Add End
            NWAL1500_CSMsg rmaConfigLine = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.C, rmaLineMsg.dsOrdPosnNum_RL.getValue()); // 2018/01/29 S21_NA#19808 Mod

            String xxLineNum = rmaLineMsg.xxLineNum_RL.getValue();
            String[] xxLineNumArray = xxLineNum.split("\\.");
            if (xxLineNumArray.length == 3 && BigDecimal.ZERO.compareTo(new BigDecimal(xxLineNumArray[2])) != 0) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).dsOrdPosnNum, rmaConfigLine.dsOrdPosnNum_RC); // QC#6432 2016/04/07
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).trxLineNum, rmaLineMsg.cpoDtlLineNum_RL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).trxLineSubNum, rmaLineMsg.cpoDtlLineSubNum_RL);
            prcApiPMsg.NWZC157002PMsg.no(i).configCatgCd.setValue(CONFIG_CATG.INBOUND);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).prcBaseDt, rmaLineMsg.prcBaseDt_RL); //QC#9482 2016/06/10
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).billToCustCd, rmaConfigLine.billToCustCd_RC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).shipToCustCd, rmaConfigLine.shipToCustCd_RC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).dsAcctNum_SH, rmaConfigLine.shipToCustAcctCd_RC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).dsAcctNum_BL, rmaConfigLine.billToCustAcctCd_RC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).prcCatgCd, rmaLineMsg.prcCatgCd_RL);
            // 2016/01/26 S21_NA#3553 Add Mod Start
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).csmpNum, rmaLineMsg.csmpContrNum_RL);
            if (ZYPCommonFunc.hasValue(bizMsg.csmpContrNum) && !ZYPCommonFunc.hasValue(rmaLineMsg.csmpContrNum_RL)) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).csmpNum, bizMsg.csmpContrNum);
            } else {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).csmpNum, rmaLineMsg.csmpContrNum_RL);
            }
            // 2016/01/26 S21_NA#3553 Add Mod End
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).dlrRefNum, rmaLineMsg.dlrRefNum_RL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).prcContrNum, bizMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).coaBrCd, bizMsg.coaBrCd);
            if (ZYPCommonFunc.hasValue(rmaLineMsg.prcCatgNm_RL.getValue())) {
                // Sell Price Category
                // For Performance QC#11618 Mod Start
                // S21SsmEZDResult ssmRslt = NWAL1500QueryForSaveSubmit.getInstance().getCcyCdByPriceCatgNm(bizMsg, rmaLineMsg.prcCatgNm_RL.getValue());
                // if (ssmRslt.isCodeNormal()) {
                //     List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
                //     ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).ccyCd, rsltMapList.get(0).get("CCY_CD"));
                // }

                String ccyCd = getCcyCdFromCache(bizMsg, rmaLineMsg.prcCatgNm_RL.getValue(), ccyCdMap);
                if (ZYPCommonFunc.hasValue(ccyCd)) {
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).ccyCd, ccyCd);
                }
                // For Performance QC#11618 Mod End
            }
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).prcListEquipConfigNum, rmaLineMsg.prcListEquipConfigNum_RL); // 2015/12/22 S21_NA#2259
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).mdseCd, rmaLineMsg.mdseCd_RL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).pkgUomCd, rmaLineMsg.custUomCd_RL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).dsOrdLineCatgCd, rmaLineMsg.dsOrdLineCatgCd_RL);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).ordQty, rmaLineMsg.ordQty_RL); // Nead confirmation!!!!
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).ordCustUomQty, rmaLineMsg.ordCustUomQty_RL); // Nead confirmation!!!
            // ZYPEZDItemValueSetter.setValue(this.prcApiPMsg.NWZC157002PMsg.no(i).invQty, rmaLineMsg.ordCustUomQty_RL);
            // S21_NA#1263
            // 2019/08/09 S21_NA#52472 Mod Start
//            if (!ZYPCommonFunc.hasValue(rmaLineMsg.rtrnQty_RL)) {
            if (!NWAL1500Constant.LINE_STS_NM_CLOSED.equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue())) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).invQty, BigDecimal.ZERO);
            } else {
//                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).invQty, rmaLineMsg.rtrnQty_RL);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).invQty, rmaLineMsg.ordQty_RL);
            }
            // 2019/08/09 S21_NA#52472 Mod End
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtrnRsnCd, rmaLineMsg.rtrnRsnCd_RL);
            // QC#6432 Start ->QC#11075
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).mdlId, rmaConfigLine.mdlId_RC);
            //if (ZYPConstant.FLG_ON_Y.equals(rmaLineMsg.baseCmptFlg_RL)) {
            //    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).mdlId, rmaConfigLine.mdlId_RC);
            //} else {
            //    prcApiPMsg.NWZC157002PMsg.no(i).mdlId.clear();
            //}
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).cntyNm_SH, rmaConfigLine.shipToCntyNm_RC); // QC#9192 Add
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).ctyAddr_SH, rmaConfigLine.shipToCtyAddr_RC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).stCd_SH, rmaConfigLine.shipToStCd_RC);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).postCd_SH, rmaConfigLine.shipToPostCd_RC); // QC#27713 2018/08/22 Add
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).ctryCd_SH, rmaConfigLine.shipToCtryCd_RC);
            // 2017/07/11 S21_NA#19779 Add Start
//            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).slsRepOrSlsTeamTocCd, NWAL1500CommonLogic.getWriterLineConfigRepCd(bizMsg.G, rmaConfigLine.dsOrdPosnNum_RC.getValue()));
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).slsRepOrSlsTeamTocCd, NWAL1500CommonLogic.getWriterLineConfigRepCd(bizMsg.H, rmaConfigLine.dsOrdPosnNum_RC.getValue()));
            // 2017/07/11 S21_NA#19779 Add End
            // QC#21106 2017/09/25 Add Start
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd, rmaLineMsg.rtlWhCd_RL);
            if(ZYPCommonFunc.hasValue(rmaLineMsg.dplyLineRefNum_RL.getValue()) && !ZYPCommonFunc.hasValue(rmaLineMsg.rtlWhCd_RL.getValue())){
                List<String> key = new ArrayList<String>();
                key.add(rmaLineMsg.dsOrdPosnNum_RL.getValue());
                key.add(rmaLineMsg.dplyLineRefNum_RL.getValue());
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd, rtlWhMap.get(key));
            }else{
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).rtlWhCd, rmaLineMsg.rtlWhCd_RL);
            }
            // QC#21106 2017/09/25 Add End
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).frtCondCd, bizMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
//            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).xxPrcCloFlg, lineClose);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).coaExtnCd, bizMsg.coaExtnCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).xxUnitPrc, rmaLineMsg.entCpoDtlDealSlsAmt_RL); //QC6480 Add

            // 2018/08/02 S21_NA#26666 add start
            CPO_SRC_TPTMsg cpoSrcTpTMsg = getCpoSrcTp(rmaLineMsg.cpoSrcTpDescTxt_RL.getValue(), bizMsg.glblCmpyCd.getValue());
            if (ZYPConstant.FLG_ON_Y.equals(cpoSrcTpTMsg.prcFrzFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).xxPrcCloFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).xxPrcCloFlg, lineClose);
            }
            // 2018/08/02 S21_NA#26666 add end

            // Pricing Element
            String xxLineNumRL = rmaLineMsg.xxLineNum_RL.getValue();
            String cpoDtlLineNumRL = rmaLineMsg.cpoDtlLineNum_RL.getValue();
            String cpoDtlLineSubNumRL = rmaLineMsg.cpoDtlLineSubNum_RL.getValue();
            String configCatgCdRL = CONFIG_CATG.INBOUND;
            int prcCnt = 0;

            boolean manualFlg = false;
            for (int j = 0; j < glblMsg.I.getValidCount(); j++) {
                NWAL1500_ISMsg priceElementMsg = glblMsg.I.no(j);

                String xxLineNumLP = priceElementMsg.xxLineNum_LP.getValue();
                String cpoDtlLineNumLP = priceElementMsg.cpoDtlLineNum_LP.getValue();
                String cpoDtlLineSubNumLP = priceElementMsg.cpoDtlLineSubNum_LP.getValue();
                String configCatgCdLP = priceElementMsg.configCatgCd_LP.getValue();
                if ((xxLineNumRL.equals(xxLineNumLP) || (cpoDtlLineNumRL.equals(cpoDtlLineNumLP) && cpoDtlLineSubNumRL.equals(cpoDtlLineSubNumLP))) && configCatgCdRL.equals(configCatgCdLP)) {
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).trxLineNum, priceElementMsg.cpoDtlLineNum_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).trxLineSubNum, priceElementMsg.cpoDtlLineSubNum_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).configCatgCd, priceElementMsg.configCatgCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCondTpCd, priceElementMsg.prcCondTpCd_LP);

                    String prcCondTpDescTxt = null;
                    if (ZYPCommonFunc.hasValue(priceElementMsg.prcCondTpCd_LP)) {
                        prcCondTpDescTxt = prcCondTpMap.get(priceElementMsg.prcCondTpCd_LP.getValue());
                        if (null == prcCondTpDescTxt) {
                            prcCondTpDescTxt = getPrcCondTpDescTxt(bizMsg.glblCmpyCd.getValue(), priceElementMsg.prcCondTpCd_LP.getValue());
                            if (null != prcCondTpDescTxt) {
                                prcCondTpMap.put(priceElementMsg.prcCondTpCd_LP.getValue(), prcCondTpDescTxt);
                            } else {
                                prcCondTpMap.put(priceElementMsg.prcCondTpCd_LP.getValue(), "");
                            }
                        }
                    }

                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCondTpDescTxt, prcCondTpDescTxt);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcDtlGrpCd, priceElementMsg.prcDtlGrpCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcJrnlGrpCd, priceElementMsg.prcJrnlGrpCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCatgCd, priceElementMsg.prcCatgCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCondManEntryFlg, priceElementMsg.prcCondManEntryFlg_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCondManAddFlg, priceElementMsg.prcCondManAddFlg_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCondManDelFlg, priceElementMsg.prcCondManDelFlg_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).pkgUomCd, priceElementMsg.pkgUomCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCondUnitCd, priceElementMsg.prcCondUnitCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcCalcMethCd, priceElementMsg.prcCalcMethCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).autoPrcCcyCd, priceElementMsg.autoPrcCcyCd_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).autoPrcAmtRate, priceElementMsg.autoPrcAmtRate_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).maxPrcAmtRate, priceElementMsg.maxPrcAmtRate_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).minPrcAmtRate, priceElementMsg.minPrcAmtRate_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).manPrcAmtRate, priceElementMsg.manPrcAmtRate_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).calcPrcAmtRate, priceElementMsg.calcPrcAmtRate_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).unitPrcAmt, priceElementMsg.unitPrcAmt_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).dsMdsePrcPk, priceElementMsg.dsMdsePrcPk_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).specCondPrcPk, priceElementMsg.specCondPrcPk_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).frtPerWtPk, priceElementMsg.frtPerWtPk_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).ordPrcCalcBasePk, priceElementMsg.ordPrcCalcBasePk_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcRuleApplyFlg, priceElementMsg.prcRuleApplyFlg_LP);
                    ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(prcCnt).prcRulePrcdPk, priceElementMsg.prcRulePrcdPk_LP);
                    // QC#6480 2016/06/06 Add Start
                    if (PRC_DTL_GRP.BASE_PRICE.equals(priceElementMsg.prcDtlGrpCd_LP.getValue())) {
                        if (rmaLineMsg.entCpoDtlDealSlsAmt_RL.getValue().compareTo(priceElementMsg.autoPrcAmtRate_LP.getValue()) != 0) {
                            manualFlg = true;
                        }
                    }
                    // QC#6480 2016/06/06 Add End

                    prcCnt++;
                }
            }
            prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.setValidCount(prcCnt);
            // QC#6480 2016/06/06 Add Start
            if (manualFlg) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(i).prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
            }
            // QC#6480 2016/06/06 Add End
            i++;
        }
        prcApiPMsg.NWZC157002PMsg.setValidCount(i);
    }

    private static void setHdrTotalAmt(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWZC157001PMsg prcApiPMsg, Map<String, NWAL1500_BSMsg> lineMsgMap, Map<String, NWAL1500_DSMsg> rmaLineMsgMap) { // 2018/01/29 S21_NA#19808
        BigDecimal hdrLinesAmt = BigDecimal.ZERO;
        BigDecimal hdrLinesTax = BigDecimal.ZERO;
        BigDecimal hdrLinesCharge = BigDecimal.ZERO;
        BigDecimal hdrLinesSubTotal = BigDecimal.ZERO;

        BigDecimal rmaLinesAmt = BigDecimal.ZERO;
        BigDecimal rmaLinesTax = BigDecimal.ZERO;
        BigDecimal rmaLinesCharge = BigDecimal.ZERO;
        BigDecimal rmaLinesSubTotal = BigDecimal.ZERO;

        // QC#13106 Mod Start
        // StringBuffer lineMsgKey = new StringBuffer("");
        StringBuilder lineMsgKey = new StringBuilder();
        // QC#13106 Mod End

        for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(i);

            if (CONFIG_CATG.OUTBOUND.equals(prcTotalPMsg.configCatgCd.getValue())) {
                hdrLinesAmt = hdrLinesAmt.add(prcTotalPMsg.xxTotNetPrcAmt.getValue());
                hdrLinesTax = hdrLinesTax.add(prcTotalPMsg.xxTotTaxAmt.getValue());
                hdrLinesCharge = hdrLinesCharge.add(prcTotalPMsg.xxTotFrtAmt.getValue());
                hdrLinesSubTotal = hdrLinesSubTotal.add(prcTotalPMsg.xxTotAmt.getValue());

                lineMsgKey.setLength(0);
                lineMsgKey = lineMsgKey.append(prcTotalPMsg.trxLineNum.getValue());
                lineMsgKey = lineMsgKey.append(".");
                lineMsgKey = lineMsgKey.append(prcTotalPMsg.trxLineSubNum.getValue());

                NWAL1500_BSMsg lineMsg = getLineMsg(glblMsg, prcTotalPMsg.trxLineNum.getValue(), prcTotalPMsg.trxLineSubNum.getValue()); // 2018/01/29 S21_NA#19808
                ZYPEZDItemValueSetter.setValue(lineMsg.entCpoDtlDealSlsAmt_LL, prcTotalPMsg.xxUnitGrsPrcAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.entDealNetUnitPrcAmt_LL, prcTotalPMsg.xxUnitNetPrcAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.cpoDtlDealTaxAmt_LL, prcTotalPMsg.xxTotTaxAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.xxLineTotPrcAmt_LL, prcTotalPMsg.xxTotAmt);
                lineMsgMap.put(lineMsgKey.toString(), lineMsg);
            } else if (CONFIG_CATG.INBOUND.equals(prcTotalPMsg.configCatgCd.getValue())) {
                rmaLinesAmt = rmaLinesAmt.add(prcTotalPMsg.xxTotNetPrcAmt.getValue());
                rmaLinesTax = rmaLinesTax.add(prcTotalPMsg.xxTotTaxAmt.getValue());
                rmaLinesCharge = rmaLinesCharge.add(prcTotalPMsg.xxTotFrtAmt.getValue());
                rmaLinesSubTotal = rmaLinesSubTotal.add(prcTotalPMsg.xxTotAmt.getValue());

                lineMsgKey.setLength(0);
                lineMsgKey = lineMsgKey.append(prcTotalPMsg.trxLineNum.getValue());
                lineMsgKey = lineMsgKey.append(".");
                lineMsgKey = lineMsgKey.append(prcTotalPMsg.trxLineSubNum.getValue());

                NWAL1500_DSMsg rmaLineMsg = getRmaLineMsg(glblMsg, prcTotalPMsg.trxLineNum.getValue(), prcTotalPMsg.trxLineSubNum.getValue()); // 2018/01/29 S21_NA#19808
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.entCpoDtlDealSlsAmt_RL, prcTotalPMsg.xxUnitGrsPrcAmt);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.entDealNetUnitPrcAmt_RL, prcTotalPMsg.xxUnitNetPrcAmt);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.cpoDtlDealTaxAmt_RL, prcTotalPMsg.xxTotTaxAmt);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.xxLineTotPrcAmt_RL, prcTotalPMsg.xxTotAmt);
                rmaLineMsgMap.put(lineMsgKey.toString(), rmaLineMsg);
            }
        }
        // set Header amount: Line
        bizMsg.xxTotBaseAmt_LN.setValue(hdrLinesAmt);
        bizMsg.xxTotTaxAmt_LN.setValue(hdrLinesTax);
        bizMsg.xxTotFrtAmt_LN.setValue(hdrLinesCharge);
        bizMsg.xxTotAmt_LN.setValue(hdrLinesSubTotal);

        // set Header amout: RMA Line
        bizMsg.xxTotBaseAmt_RT.setValue(rmaLinesAmt);
        bizMsg.xxTotTaxAmt_RT.setValue(rmaLinesTax);
        bizMsg.xxTotFrtAmt_RT.setValue(rmaLinesCharge);
        bizMsg.xxTotAmt_RT.setValue(rmaLinesSubTotal);

        // QC#15463 2016/10/21 Add Start
        S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getSvcAmount(bizMsg);
        if (ssmResult.isCodeNormal()) {
            Map< ? , ? > data = (Map< ? , ? >) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotBaseAmt_MT, (BigDecimal) data.get("CPO_SVC_DEAL_NET_AMT"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt_MT, (BigDecimal) data.get("CPO_SVC_DEAL_TAX_AMT"));
        }
        // QC#15463 2016/10/21 Add End
        // set Header Amount: Total
        hdrLinesAmt = bizMsg.xxTotBaseAmt_LN.getValue();
        hdrLinesTax = bizMsg.xxTotTaxAmt_LN.getValue();
        hdrLinesCharge = bizMsg.xxTotFrtAmt_LN.getValue();
        hdrLinesSubTotal = bizMsg.xxTotAmt_LN.getValue();

        hdrLinesAmt = hdrLinesAmt.add(bizMsg.xxTotBaseAmt_MT.getValue());
        hdrLinesTax = hdrLinesTax.add(bizMsg.xxTotTaxAmt_MT.getValue());
        hdrLinesCharge = hdrLinesCharge.add(bizMsg.xxTotFrtAmt_MT.getValue());
        hdrLinesSubTotal = hdrLinesSubTotal.add(bizMsg.xxTotAmt_MT.getValue());

        hdrLinesAmt = hdrLinesAmt.add(bizMsg.xxTotBaseAmt_RT.getValue());
        hdrLinesTax = hdrLinesTax.add(bizMsg.xxTotTaxAmt_RT.getValue());
        hdrLinesCharge = hdrLinesCharge.add(bizMsg.xxTotFrtAmt_RT.getValue());
        hdrLinesSubTotal = hdrLinesSubTotal.add(bizMsg.xxTotAmt_RT.getValue());

        bizMsg.xxTotBaseAmt.setValue(hdrLinesAmt);
        bizMsg.xxTotTaxAmt.setValue(hdrLinesTax);
        bizMsg.xxTotFrtAmt.setValue(hdrLinesCharge);
        bizMsg.xxTotAmt.setValue(hdrLinesSubTotal);

    }

    private static void setLineConfigPricingData(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWZC157001PMsg prcApiPMsg, Map<String, NWAL1500_BSMsg> lineMsgMap) { // 2018/01/29 S21_NA#19808

        int prcElmCnt = glblMsg.I.getValidCount();
        int i = 0;
        // for (i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount() && prcElmCnt < glblMsg.I.length(); i++) {
        for (i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg prcLinePMsg = prcApiPMsg.NWZC157002PMsg.no(i);
            if (!CONFIG_CATG.OUTBOUND.equals(prcLinePMsg.configCatgCd.getValue())) {
                continue;
            }

            // QC#13106 Mod Start
            // StringBuffer lineMsgKey = new StringBuffer("");
            StringBuilder lineMsgKey = new StringBuilder();
            // QC#13106 Mod End
            lineMsgKey = lineMsgKey.append(prcLinePMsg.trxLineNum.getValue());
            lineMsgKey = lineMsgKey.append(".");
            lineMsgKey = lineMsgKey.append(prcLinePMsg.trxLineSubNum.getValue());
            NWAL1500_BSMsg lineMsg = lineMsgMap.get(lineMsgKey.toString()); // 2018/01/29 S21_NA#19808

            for (int j = 0; j < prcLinePMsg.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg prcElementPMsg = prcLinePMsg.NWZC157003PMsg.no(j);
                String configCatgCd = prcElementPMsg.configCatgCd.getValue();
                if (!CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
                    continue;
                }
                NWAL1500_ISMsg prcLineCMsg = glblMsg.I.no(prcElmCnt);
                // S21_NA#1952 NWAL1500_ISMsg prcLineSMsg = glblMsg.I.no(prcElmCnt);

                ZYPEZDItemValueSetter.setValue(prcLineCMsg.configCatgCd_LP, prcElementPMsg.configCatgCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.ordPrcCalcBasePk_LP, prcElementPMsg.ordPrcCalcBasePk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.cpoOrdNum_LP, bizMsg.cpoOrdNum); // S21_NA#2320
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.cpoDtlLineNum_LP, prcElementPMsg.trxLineNum);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.cpoDtlLineSubNum_LP, prcElementPMsg.trxLineSubNum); // 2015/11/19 S21_NA#853
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondTpCd_LP, prcElementPMsg.prcCondTpCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcDtlGrpCd_LP, prcElementPMsg.prcDtlGrpCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcJrnlGrpCd_LP, prcElementPMsg.prcJrnlGrpCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.pkgUomCd_LP, prcElementPMsg.pkgUomCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondUnitCd_LP, prcElementPMsg.prcCondUnitCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCalcMethCd_LP, prcElementPMsg.prcCalcMethCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.dsMdsePrcPk_LP, prcElementPMsg.dsMdsePrcPk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.specCondPrcPk_LP, prcElementPMsg.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.frtPerWtPk_LP, prcElementPMsg.frtPerWtPk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondManEntryFlg_LP, prcElementPMsg.prcCondManEntryFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondManAddFlg_LP, prcElementPMsg.prcCondManAddFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondManDelFlg_LP, prcElementPMsg.prcCondManDelFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.autoPrcAmtRate_LP, prcElementPMsg.autoPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.maxPrcAmtRate_LP, prcElementPMsg.maxPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.minPrcAmtRate_LP, prcElementPMsg.minPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.manPrcAmtRate_LP, prcElementPMsg.manPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.calcPrcAmtRate_LP, prcElementPMsg.calcPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.unitPrcAmt_LP, prcElementPMsg.unitPrcAmt);
                // ZYPEZDItemValueSetter.setValue(prcLineCMsg.coaAcctCd_LP, prcElementPMsg.coaAcctCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.autoPrcCcyCd_LP, prcElementPMsg.autoPrcCcyCd);
                // ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondTpDescTxt_LP, prcElementPMsg.prcCondTpDescTxt);
                // QC#9700  2018/09/03 Add Start
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcRuleApplyFlg_LP, prcElementPMsg.prcRuleApplyFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcRulePrcdPk_LP, prcElementPMsg.prcRulePrcdPk);
                // QC#9700  2018/09/03 Add End

                if (PRC_DTL_GRP.BASE_PRICE.equals(prcElementPMsg.prcDtlGrpCd.getValue())) {
                    if (null != lineMsg) {
                        ZYPEZDItemValueSetter.setValue(lineMsg.dealPrcListPrcAmt_LL, prcElementPMsg.autoPrcAmtRate);
                        ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgCd_LL, prcLinePMsg.prcCatgCd);
                        ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgNm_LL, NWAL1500CommonLogic.getPrcCatgNm(bizMsg, prcLinePMsg.prcCatgCd.getValue()));
                    }
                }
                // S21_NA#1952 EZDMsg.copy(prcLineCMsg, null, prcLineSMsg, null);
                prcElmCnt++;
            }
            if (null != lineMsg) {
                ZYPEZDItemValueSetter.setValue(lineMsg.xxErrFlg_LL, prcLinePMsg.xxErrFlg);
            }
        }
        glblMsg.I.setValidCount(prcElmCnt);
        // S21_NA#1952 glblMsg.I.setValidCount(prcElmCnt);

        // S21_NA#4375
        // Calculate SubTaotal, Charge, Tax and Line Total in ConfigLine/Line
        for (i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            NWZC157004PMsg prcLinePMsg = prcApiPMsg.NWZC157004PMsg.no(i);
            if (!CONFIG_CATG.OUTBOUND.equals(prcLinePMsg.configCatgCd.getValue())) {
                continue;
            }

            // QC#13106 Mod Start
            // StringBuffer lineMsgKey = new StringBuffer("");
            StringBuilder lineMsgKey = new StringBuilder();
            // QC#13106 Mod End
            lineMsgKey = lineMsgKey.append(prcLinePMsg.trxLineNum.getValue());
            lineMsgKey = lineMsgKey.append(".");
            lineMsgKey = lineMsgKey.append(prcLinePMsg.trxLineSubNum.getValue());
            NWAL1500_BSMsg lineMsg = lineMsgMap.get(lineMsgKey.toString()); // 2018/01/29 S21_NA#19808
            if (null != lineMsg) {
                ZYPEZDItemValueSetter.setValue(lineMsg.xxTotBaseAmt_LL, prcLinePMsg.xxTotBaseAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.xxTotDiscAmt_LL, prcLinePMsg.xxTotDiscAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.xxTotFrtAmt_LL, prcLinePMsg.xxTotFrtAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.xxTotTaxAmt_LL, prcLinePMsg.xxTotTaxAmt);
            }
        }
        // 2018/01/29 S21_NA#19808bizMsg.A, B, C, D => glblMsg.A, B, C, D
        for (int l = 0; l < glblMsg.B.getValidCount(); l++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(l);
            if (ORD_LINE_STS.CANCELLED.equals(lineMsg.ordLineStsCd_LL.getValue())) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(lineMsg.lineDealSubTotAmt_LL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.lineDealChrgAmt_LL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.lineDealTaxAmt_LL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.lineDealTotAmt_LL, BigDecimal.ZERO);

            ZYPEZDItemValueSetter.setValue(lineMsg.lineDealSubTotAmt_LL, subtract(lineMsg.xxTotBaseAmt_LL.getValue(), lineMsg.xxTotDiscAmt_LL.getValue()));
            if (ZYPCommonFunc.hasValue(lineMsg.xxTotFrtAmt_LL)) {
                ZYPEZDItemValueSetter.setValue(lineMsg.lineDealChrgAmt_LL, lineMsg.xxTotFrtAmt_LL);
            }
            if (ZYPCommonFunc.hasValue(lineMsg.xxTotTaxAmt_LL)) {
                ZYPEZDItemValueSetter.setValue(lineMsg.lineDealTaxAmt_LL, lineMsg.xxTotTaxAmt_LL);
            }
            BigDecimal totAmt = add(add(lineMsg.lineDealSubTotAmt_LL.getValue(), lineMsg.lineDealChrgAmt_LL.getValue()), lineMsg.lineDealTaxAmt_LL.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.lineDealTotAmt_LL, totAmt);
        }
        // 2018/01/29 S21_NA#19808 bizMsg.A, B => glblMsg.A, B
        for (int c = 0; c < glblMsg.A.getValidCount(); c++) {
            BigDecimal configNet = BigDecimal.ZERO;
            BigDecimal configChrg = BigDecimal.ZERO;
            BigDecimal configTax = BigDecimal.ZERO;
            BigDecimal configTot = BigDecimal.ZERO;
            String dsOrdPosnNum = glblMsg.A.no(c).dsOrdPosnNum_LC.getValue();
            for (int l = 0; l < glblMsg.B.getValidCount(); l++) {
//              if (ORD_LINE_STS.CANCELLED.equals(bizMsg.B.no(i).ordLineStsCd_LL.getValue())) { // QC#3834 Mod
                if (ORD_LINE_STS.CANCELLED.equals(glblMsg.B.no(l).ordLineStsCd_LL.getValue())) { // QC#3834 Mod
                    continue;
                }
                if (dsOrdPosnNum.equals(glblMsg.B.no(l).dsOrdPosnNum_LL.getValue())) {
                    configNet = add(configNet, glblMsg.B.no(l).lineDealSubTotAmt_LL.getValue());
                    configChrg = add(configChrg, glblMsg.B.no(l).lineDealChrgAmt_LL.getValue());
                    configTax = add(configTax, glblMsg.B.no(l).lineDealTaxAmt_LL.getValue());
                    configTot = add(configTot, glblMsg.B.no(l).lineDealTotAmt_LL.getValue());
                }
            }
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(c).configTotDealNetAmt_LC, configNet);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(c).configTotDealChrgAmt_LC, configChrg);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(c).configTotDealTaxAmt_LC, configTax);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(c).configSubTotDealAmt_LC, configTot);
        }
        // Clear
        for (int l = 0; l < bizMsg.B.getValidCount(); l++) {
            glblMsg.B.no(l).xxTotBaseAmt_LL.clear();
            glblMsg.B.no(l).xxTotDiscAmt_LL.clear();
            glblMsg.B.no(l).xxTotFrtAmt_LL.clear();
            glblMsg.B.no(l).xxTotTaxAmt_LL.clear();
        }

        // save business message header data to global data
        boolean isSame = true;

        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            isSame &= isEqualsEZDItem(bizMsg.dsOrdCatgCd, glblMsg.dsOrdCatgCd);
            isSame &= isEqualsEZDItem(bizMsg.dsOrdTpCd, glblMsg.dsOrdTpCd);
            isSame &= isEqualsEZDItem(bizMsg.sellToCustCd, glblMsg.sellToCustCd);
            isSame &= isEqualsEZDItem(bizMsg.negoDealAmt, glblMsg.negoDealAmt);
            isSame &= isEqualsEZDItem(bizMsg.slsRepTocCd, glblMsg.slsRepTocCd);
            isSame &= isEqualsEZDItem(bizMsg.custIssPoNum, glblMsg.custIssPoNum);
            isSame &= isEqualsEZDItem(bizMsg.frtCondCd, glblMsg.frtCondCd);
            isSame &= isEqualsEZDItem(bizMsg.carrSvcLvlCd, glblMsg.carrSvcLvlCd);
            isSame &= isEqualsEZDItem(bizMsg.dsOrdTpCd, glblMsg.dsOrdTpCd);
            isSame &= isEqualsEZDItem(bizMsg.spclHdlgTpCd, glblMsg.spclHdlgTpCd);
            isSame &= isEqualsEZDItem(bizMsg.shpgSvcLvlCd, glblMsg.shpgSvcLvlCd);
            isSame &= isEqualsEZDItem(bizMsg.pmtTermCashDiscCd, glblMsg.pmtTermCashDiscCd);
            isSame &= isEqualsEZDItem(bizMsg.prcContrNum, glblMsg.prcContrNum);
            isSame &= isEqualsEZDItem(bizMsg.leasePrchOptCd, glblMsg.leasePrchOptCd);
            isSame &= isEqualsEZDItem(bizMsg.leaseTermMthAot, glblMsg.leaseTermMthAot);
            isSame &= isEqualsEZDItem(bizMsg.leaseTermMthAot_EM, glblMsg.leaseTermMthAot_EM);
        }
        // S21_NA#1952
        // ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdCatgCd,
        // bizMsg.dsOrdCatgCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdTpCd,
        // bizMsg.dsOrdTpCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.sellToCustCd,
        // bizMsg.sellToCustCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.negoDealAmt,
        // bizMsg.negoDealAmt);
        // ZYPEZDItemValueSetter.setValue(glblMsg.slsRepTocCd,
        // bizMsg.slsRepTocCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.custIssPoNum,
        // bizMsg.custIssPoNum);
        // ZYPEZDItemValueSetter.setValue(glblMsg.frtCondCd,
        // bizMsg.frtCondCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.frtCondDescTxt,
        // bizMsg.frtCondDescTxt);
        // ZYPEZDItemValueSetter.setValue(glblMsg.carrSvcLvlCd,
        // bizMsg.carrSvcLvlCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.carrSvcLvlDescTxt,
        // bizMsg.carrSvcLvlDescTxt);
        // ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdTpCd,
        // bizMsg.dsOrdTpCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.spclHdlgTpCd,
        // bizMsg.spclHdlgTpCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.shpgSvcLvlCd,
        // bizMsg.shpgSvcLvlCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.pmtTermCashDiscCd,
        // bizMsg.pmtTermCashDiscCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.pmtTermCashDiscDescTxt,
        // bizMsg.pmtTermCashDiscDescTxt);
        // ZYPEZDItemValueSetter.setValue(glblMsg.prcContrNum,
        // bizMsg.prcContrNum);
        // ZYPEZDItemValueSetter.setValue(glblMsg.leasePrchOptCd,
        // bizMsg.leasePrchOptCd);

        // save config data to global data
        for (i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum) && glblMsg.A.getValidCount() > i) {
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).svcConfigMstrPk_LC, glblMsg.A.no(i).svcConfigMstrPk_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).mdlId_LC, glblMsg.A.no(i).mdlId_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToCustCd_LC, glblMsg.A.no(i).shipToCustCd_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToCustAcctCd_LC, glblMsg.A.no(i).shipToCustAcctCd_LC);

                isSame &= isEqualsEZDItem(glblMsg.A.no(i).dropShipFlg_LC, glblMsg.A.no(i).dropShipFlg_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToLocNm_LC, glblMsg.A.no(i).shipToLocNm_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToAddlLocNm_LC, glblMsg.A.no(i).shipToAddlLocNm_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToFirstLineAddr_LC, glblMsg.A.no(i).shipToFirstLineAddr_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToScdLineAddr_LC, glblMsg.A.no(i).shipToScdLineAddr_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToThirdLineAddr_LC, glblMsg.A.no(i).shipToThirdLineAddr_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToFrthLineAddr_LC, glblMsg.A.no(i).shipToFrthLineAddr_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToFirstRefCmntTxt_LC, glblMsg.A.no(i).shipToFirstRefCmntTxt_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToScdRefCmntTxt_LC, glblMsg.A.no(i).shipToScdRefCmntTxt_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToPostCd_LC, glblMsg.A.no(i).shipToPostCd_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToCtyAddr_LC, glblMsg.A.no(i).shipToCtyAddr_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToStCd_LC, glblMsg.A.no(i).shipToStCd_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToProvNm_LC, glblMsg.A.no(i).shipToProvNm_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToCtryCd_LC, glblMsg.A.no(i).shipToCtryCd_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).shipToCntyNm_LC, glblMsg.A.no(i).shipToCntyNm_LC);

                isSame &= isEqualsEZDItem(glblMsg.A.no(i).billToCustCd_LC, glblMsg.A.no(i).billToCustCd_LC);
                isSame &= isEqualsEZDItem(glblMsg.A.no(i).billToCustAcctCd_LC, glblMsg.A.no(i).billToCustAcctCd_LC);
            }

            // S21_NA#1952
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsOrdPosnNum_LC,
            // bizMsg.A.no(i).dsOrdPosnNum_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).svcConfigMstrPk_LC,
            // bizMsg.A.no(i).svcConfigMstrPk_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).mdlId_LC,
            // bizMsg.A.no(i).mdlId_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToCustCd_LC,
            // bizMsg.A.no(i).shipToCustCd_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToCustAcctCd_LC,
            // bizMsg.A.no(i).shipToCustAcctCd_LC);
            //
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dropShipFlg_LC,
            // bizMsg.A.no(i).dropShipFlg_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToLocNm_LC,
            // bizMsg.A.no(i).shipToLocNm_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToAddlLocNm_LC,
            // bizMsg.A.no(i).shipToAddlLocNm_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToFirstLineAddr_LC,
            // bizMsg.A.no(i).shipToFirstLineAddr_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToScdLineAddr_LC,
            // bizMsg.A.no(i).shipToScdLineAddr_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToThirdLineAddr_LC,
            // bizMsg.A.no(i).shipToThirdLineAddr_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToFrthLineAddr_LC,
            // bizMsg.A.no(i).shipToFrthLineAddr_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToFirstRefCmntTxt_LC,
            // bizMsg.A.no(i).shipToFirstRefCmntTxt_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToScdRefCmntTxt_LC,
            // bizMsg.A.no(i).shipToScdRefCmntTxt_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToPostCd_LC,
            // bizMsg.A.no(i).shipToPostCd_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToCtyAddr_LC,
            // bizMsg.A.no(i).shipToCtyAddr_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToStCd_LC,
            // bizMsg.A.no(i).shipToStCd_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToProvNm_LC,
            // bizMsg.A.no(i).shipToProvNm_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToCtryCd_LC,
            // bizMsg.A.no(i).shipToCtryCd_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToCntyNm_LC,
            // bizMsg.A.no(i).shipToCntyNm_LC);
            //
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).billToCustCd_LC,
            // bizMsg.A.no(i).billToCustCd_LC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).billToCustAcctCd_LC,
            // bizMsg.A.no(i).billToCustAcctCd_LC);
        }
        // S21_NA#1952 glblMsg.A.setValidCount(i);

        // save line data to global data
        for (i = 0; i < glblMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum) && glblMsg.B.getValidCount() > i) {
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).mdseCd_LL, glblMsg.B.no(i).mdseCd_LL);
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).ordCustUomQty_LL, glblMsg.B.no(i).ordCustUomQty_LL);
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).ordQty_LL, glblMsg.B.no(i).ordQty_LL);
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).custUomCd_LL, glblMsg.B.no(i).custUomCd_LL);
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).entCpoDtlDealSlsAmt_LL, glblMsg.B.no(i).entCpoDtlDealSlsAmt_LL);
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).prcCatgNm_LL, glblMsg.B.no(i).prcCatgNm_LL);
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).dealPrcListPrcAmt_LL, glblMsg.B.no(i).dealPrcListPrcAmt_LL);
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).dsOrdLineCatgCd_LL, glblMsg.B.no(i).dsOrdLineCatgCd_LL);
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).rtlWhCd_LL, glblMsg.B.no(i).rtlWhCd_LL);
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).rtlWhNm_LL, glblMsg.B.no(i).rtlWhNm_LL);
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).rtlSwhCd_LL, glblMsg.B.no(i).rtlSwhCd_LL);
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).rtlSwhNm_LL, glblMsg.B.no(i).rtlSwhNm_LL);
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).csmpContrNum_LL, glblMsg.B.no(i).csmpContrNum_LL);
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).dlrRefNum_LL, glblMsg.B.no(i).dlrRefNum_LL);
                isSame &= isEqualsEZDItem(glblMsg.B.no(i).csmpPrcListCd_LL, glblMsg.B.no(i).csmpPrcListCd_LL);
            }

            // S21_NA#1952
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).cpoDtlLineNum_LL,
            // bizMsg.B.no(i).cpoDtlLineNum_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).cpoDtlLineSubNum_LL,
            // bizMsg.B.no(i).cpoDtlLineSubNum_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).mdseCd_LL,
            // bizMsg.B.no(i).mdseCd_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).ordCustUomQty_LL,
            // bizMsg.B.no(i).ordCustUomQty_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).ordQty_LL,
            // bizMsg.B.no(i).ordQty_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).custUomCd_LL,
            // bizMsg.B.no(i).custUomCd_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).entCpoDtlDealSlsAmt_LL,
            // bizMsg.B.no(i).entCpoDtlDealSlsAmt_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).prcCatgNm_LL,
            // bizMsg.B.no(i).prcCatgNm_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).dealPrcListPrcAmt_LL,
            // bizMsg.B.no(i).dealPrcListPrcAmt_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).dsOrdLineCatgCd_LL,
            // bizMsg.B.no(i).dsOrdLineCatgCd_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).rtlWhCd_LL,
            // bizMsg.B.no(i).rtlWhCd_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).rtlWhNm_LL,
            // bizMsg.B.no(i).rtlWhNm_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).rtlSwhCd_LL,
            // bizMsg.B.no(i).rtlSwhCd_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).rtlSwhNm_LL,
            // bizMsg.B.no(i).rtlSwhNm_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).csmpContrNum_LL,
            // bizMsg.B.no(i).csmpContrNum_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).dlrRefNum_LL,
            // bizMsg.B.no(i).dlrRefNum_LL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).csmpPrcListCd_LL,
            // bizMsg.B.no(i).csmpPrcListCd_LL);
        }
        // S21_NA#1952 glblMsg.B.setValidCount(i);

        if (!isSame && ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            bizMsg.xxEdtModeFlg.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    private static void setLineRmaPricingData(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWZC157001PMsg prcApiPMsg, Map<String, NWAL1500_DSMsg> rmaLineMsgMap) { // 2018/01/29 S21_NA#19808

        int prcElmCnt = glblMsg.I.getValidCount();
        int i = 0;
        // for (i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount() && prcElmCnt < glblMsg.I.length; i++) {
        for (i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg prcLinePMsg = prcApiPMsg.NWZC157002PMsg.no(i);
            if (!CONFIG_CATG.INBOUND.equals(prcLinePMsg.configCatgCd.getValue())) {
                continue;
            }

            // QC#13106 Mod Start
            // StringBuffer lineMsgKey = new StringBuffer("");
            StringBuilder lineMsgKey = new StringBuilder();
            // QC#13106 Mod End
            lineMsgKey = lineMsgKey.append(prcLinePMsg.trxLineNum.getValue());
            lineMsgKey = lineMsgKey.append(".");
            lineMsgKey = lineMsgKey.append(prcLinePMsg.trxLineSubNum.getValue());
            NWAL1500_DSMsg rmaLineMsg = rmaLineMsgMap.get(lineMsgKey.toString()); // 2018/01/29 S21_NA#19808

            for (int j = 0; j < prcLinePMsg.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg prcElementPMsg = prcLinePMsg.NWZC157003PMsg.no(j);
                String configCatgCd = prcElementPMsg.configCatgCd.getValue();
                if (!CONFIG_CATG.INBOUND.equals(configCatgCd)) {
                    continue;
                }
                NWAL1500_ISMsg prcLineCMsg = glblMsg.I.no(prcElmCnt);
                // S21_NA#1952 NWAL1500_ISMsg prcLineSMsg = glblMsg.I.no(prcElmCnt);

                ZYPEZDItemValueSetter.setValue(prcLineCMsg.configCatgCd_LP, prcElementPMsg.configCatgCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.ordPrcCalcBasePk_LP, prcElementPMsg.ordPrcCalcBasePk);
                // ZYPEZDItemValueSetter.setValue(prcLineCMsg.cpoOrdNum_LP, prcElementPMsg.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.cpoDtlLineNum_LP, prcElementPMsg.trxLineNum);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.cpoDtlLineSubNum_LP, prcElementPMsg.trxLineSubNum); // 2015/11/19 S21_NA#853
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondTpCd_LP, prcElementPMsg.prcCondTpCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcDtlGrpCd_LP, prcElementPMsg.prcDtlGrpCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcJrnlGrpCd_LP, prcElementPMsg.prcJrnlGrpCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.pkgUomCd_LP, prcElementPMsg.pkgUomCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondUnitCd_LP, prcElementPMsg.prcCondUnitCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCalcMethCd_LP, prcElementPMsg.prcCalcMethCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.dsMdsePrcPk_LP, prcElementPMsg.dsMdsePrcPk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.specCondPrcPk_LP, prcElementPMsg.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.frtPerWtPk_LP, prcElementPMsg.frtPerWtPk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondManEntryFlg_LP, prcElementPMsg.prcCondManEntryFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondManAddFlg_LP, prcElementPMsg.prcCondManAddFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondManDelFlg_LP, prcElementPMsg.prcCondManDelFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.autoPrcAmtRate_LP, prcElementPMsg.autoPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.maxPrcAmtRate_LP, prcElementPMsg.maxPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.minPrcAmtRate_LP, prcElementPMsg.minPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.manPrcAmtRate_LP, prcElementPMsg.manPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.calcPrcAmtRate_LP, prcElementPMsg.calcPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.unitPrcAmt_LP, prcElementPMsg.unitPrcAmt);
                // ZYPEZDItemValueSetter.setValue(prcLineCMsg.coaAcctCd_LP, prcElementPMsg.coaAcctCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.autoPrcCcyCd_LP, prcElementPMsg.autoPrcCcyCd);
                // ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondTpDescTxt_LP, prcElementPMsg.prcCondTpDescTxt);
                // QC#9700  2018/09/03 Add Start
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcRuleApplyFlg_LP, prcElementPMsg.prcRuleApplyFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcRulePrcdPk_LP, prcElementPMsg.prcRulePrcdPk);
                // QC#9700  2018/09/03 Add End

                if (PRC_DTL_GRP.BASE_PRICE.equals(prcElementPMsg.prcDtlGrpCd.getValue())) {
                    if (null != rmaLineMsg) {
                        ZYPEZDItemValueSetter.setValue(rmaLineMsg.dealPrcListPrcAmt_RL, prcElementPMsg.autoPrcAmtRate);
                        ZYPEZDItemValueSetter.setValue(rmaLineMsg.prcCatgCd_RL, prcLinePMsg.prcCatgCd);
                        ZYPEZDItemValueSetter.setValue(rmaLineMsg.prcCatgNm_RL, NWAL1500CommonLogic.getPrcCatgNm(bizMsg, prcLinePMsg.prcCatgCd.getValue()));
                    }
                }
                // S21_NA#1952 EZDMsg.copy(prcLineCMsg, null, prcLineSMsg, null);
                prcElmCnt++;
            }
            if (null != rmaLineMsg) {
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.xxErrFlg_RL, prcLinePMsg.xxErrFlg);
            }
        }
        glblMsg.I.setValidCount(prcElmCnt);
        // S21_NA#12598 Del Start
        //glblMsg.I.setValidCount(prcElmCnt);
        // S21_NA#12598 Del End

        // S21_NA#4375
        // Calculate SubTaotal, Charge, Tax and Line Total in ConfigLine/Line
        for (i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            NWZC157004PMsg prcLinePMsg = prcApiPMsg.NWZC157004PMsg.no(i);
            if (!CONFIG_CATG.INBOUND.equals(prcLinePMsg.configCatgCd.getValue())) {
                continue;
            }

            // QC#13106 Mod Start
            // StringBuffer lineMsgKey = new StringBuffer("");
            StringBuilder lineMsgKey = new StringBuilder();
            // QC#13106 Mod End
            lineMsgKey = lineMsgKey.append(prcLinePMsg.trxLineNum.getValue());
            lineMsgKey = lineMsgKey.append(".");
            lineMsgKey = lineMsgKey.append(prcLinePMsg.trxLineSubNum.getValue());
            NWAL1500_DSMsg rmaLineMsg = rmaLineMsgMap.get(lineMsgKey.toString()); // 2018/01/29 S21_NA#19808
            if (null != rmaLineMsgMap) {
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.xxTotBaseAmt_RL, prcLinePMsg.xxTotBaseAmt);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.xxTotDiscAmt_RL, prcLinePMsg.xxTotDiscAmt);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.xxTotFrtAmt_RL, prcLinePMsg.xxTotFrtAmt);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.xxTotTaxAmt_RL, prcLinePMsg.xxTotTaxAmt);
            }
        }
        // 2018/01/29 S21_NA#1980 bizMsg.C, D => glblMsg.C, D
        for (int l = 0; l < glblMsg.D.getValidCount(); l++) {
            NWAL1500_DSMsg lineMsg = glblMsg.D.no(l);
            if (RTRN_LINE_STS.CANCELLED.equals(lineMsg.ordLineStsCd_RL.getValue())
                    || RTRN_LINE_STS.CLOSED.equals(lineMsg.ordLineStsCd_RL.getValue())) { // 2016/09/06 S21_NA#12435 Add
                continue;
            }
            ZYPEZDItemValueSetter.setValue(lineMsg.lineDealSubTotAmt_RL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.lineDealChrgAmt_RL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.lineDealTaxAmt_RL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.lineDealTotAmt_RL, BigDecimal.ZERO);

            ZYPEZDItemValueSetter.setValue(lineMsg.lineDealSubTotAmt_RL, subtract(lineMsg.xxTotBaseAmt_RL.getValue(), lineMsg.xxTotDiscAmt_RL.getValue()));
            if (ZYPCommonFunc.hasValue(lineMsg.xxTotFrtAmt_RL)) {
                ZYPEZDItemValueSetter.setValue(lineMsg.lineDealChrgAmt_RL, lineMsg.xxTotFrtAmt_RL);
            }
            if (ZYPCommonFunc.hasValue(lineMsg.xxTotTaxAmt_RL)) {
                ZYPEZDItemValueSetter.setValue(lineMsg.lineDealTaxAmt_RL, lineMsg.xxTotTaxAmt_RL);
            }
            BigDecimal totAmt = add(add(lineMsg.lineDealSubTotAmt_RL.getValue(), lineMsg.lineDealChrgAmt_RL.getValue()), lineMsg.lineDealTaxAmt_RL.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.lineDealTotAmt_RL, totAmt);
        }
        for (int c = 0; c < glblMsg.C.getValidCount(); c++) {
            BigDecimal configNet = BigDecimal.ZERO;
            BigDecimal configChrg = BigDecimal.ZERO;
            BigDecimal configTax = BigDecimal.ZERO;
            BigDecimal configTot = BigDecimal.ZERO;
            String dsOrdPosnNum = glblMsg.C.no(c).dsOrdPosnNum_RC.getValue();
            for (int l = 0; l < glblMsg.D.getValidCount(); l++) {
                if (RTRN_LINE_STS.CANCELLED.equals(glblMsg.D.no(l).ordLineStsCd_RL.getValue())
                        || RTRN_LINE_STS.CLOSED.equals(glblMsg.D.no(l).ordLineStsCd_RL.getValue())) {
                    continue;
                }
                if (dsOrdPosnNum.equals(glblMsg.D.no(l).dsOrdPosnNum_RL.getValue())) {
                    configNet = add(configNet, glblMsg.D.no(l).lineDealSubTotAmt_RL.getValue());
                    configChrg = add(configChrg, glblMsg.D.no(l).lineDealChrgAmt_RL.getValue());
                    configTax = add(configTax, glblMsg.D.no(l).lineDealTaxAmt_RL.getValue());
                    configTot = add(configTot, glblMsg.D.no(l).lineDealTotAmt_RL.getValue());
                }
            }
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(c).configTotDealNetAmt_RC, configNet);
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(c).configTotDealChrgAmt_RC, configChrg);
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(c).configTotDealTaxAmt_RC, configTax);
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(c).configSubTotDealAmt_RC, configTot);
        }
        // Clear
        for (int l = 0; l < glblMsg.D.getValidCount(); l++) {
            glblMsg.D.no(l).xxTotBaseAmt_RL.clear();
            glblMsg.D.no(l).xxTotDiscAmt_RL.clear();
            glblMsg.D.no(l).xxTotFrtAmt_RL.clear();
            glblMsg.D.no(l).xxTotTaxAmt_RL.clear();
        }

        // save business message header data to global data
        boolean isSame = true;

        // if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
        // isSame &= isEqualsEZDItem(bizMsg.dsOrdCatgCd, glblMsg.dsOrdCatgCd);
        // isSame &= isEqualsEZDItem(bizMsg.dsOrdTpCd, glblMsg.dsOrdTpCd);
        // isSame &= isEqualsEZDItem(bizMsg.sellToCustCd, glblMsg.sellToCustCd);
        // isSame &= isEqualsEZDItem(bizMsg.negoDealAmt, glblMsg.negoDealAmt);
        // isSame &= isEqualsEZDItem(bizMsg.slsRepTocCd, glblMsg.slsRepTocCd);
        // isSame &= isEqualsEZDItem(bizMsg.custIssPoNum, glblMsg.custIssPoNum);
        // isSame &= isEqualsEZDItem(bizMsg.frtCondCd, glblMsg.frtCondCd);
        // isSame &= isEqualsEZDItem(bizMsg.carrSvcLvlCd, glblMsg.carrSvcLvlCd);
        // isSame &= isEqualsEZDItem(bizMsg.dsOrdTpCd, glblMsg.dsOrdTpCd);
        // isSame &= isEqualsEZDItem(bizMsg.spclHdlgTpCd, glblMsg.spclHdlgTpCd);
        // isSame &= isEqualsEZDItem(bizMsg.shpgSvcLvlCd, glblMsg.shpgSvcLvlCd);
        // isSame &= isEqualsEZDItem(bizMsg.pmtTermCashDiscCd, glblMsg.pmtTermCashDiscCd);
        // isSame &= isEqualsEZDItem(bizMsg.prcContrNum, glblMsg.prcContrNum);
        // isSame &= isEqualsEZDItem(bizMsg.leasePrchOptCd, glblMsg.leasePrchOptCd);
        // }
        //
        // ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.sellToCustCd, bizMsg.sellToCustCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.negoDealAmt, bizMsg.negoDealAmt);
        // ZYPEZDItemValueSetter.setValue(glblMsg.slsRepTocCd, bizMsg.slsRepTocCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.custIssPoNum, bizMsg.custIssPoNum);
        // ZYPEZDItemValueSetter.setValue(glblMsg.frtCondCd, bizMsg.frtCondCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.frtCondDescTxt, bizMsg.frtCondDescTxt);
        // ZYPEZDItemValueSetter.setValue(glblMsg.carrSvcLvlCd, bizMsg.carrSvcLvlCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.carrSvcLvlDescTxt, bizMsg.carrSvcLvlDescTxt);
        // ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.spclHdlgTpCd, bizMsg.spclHdlgTpCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.pmtTermCashDiscCd, bizMsg.pmtTermCashDiscCd);
        // ZYPEZDItemValueSetter.setValue(glblMsg.pmtTermCashDiscDescTxt,
        // bizMsg.pmtTermCashDiscDescTxt);
        // ZYPEZDItemValueSetter.setValue(glblMsg.prcContrNum, bizMsg.prcContrNum);
        // ZYPEZDItemValueSetter.setValue(glblMsg.leasePrchOptCd, bizMsg.leasePrchOptCd);

        // save config data to global data
        for (i = 0; i < glblMsg.C.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum) && glblMsg.C.getValidCount() > i) {
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).svcConfigMstrPk_RC, glblMsg.C.no(i).svcConfigMstrPk_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).mdlId_RC, glblMsg.C.no(i).mdlId_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToCustCd_RC, glblMsg.C.no(i).shipToCustCd_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToCustAcctCd_RC, glblMsg.C.no(i).shipToCustAcctCd_RC);

                isSame &= isEqualsEZDItem(glblMsg.C.no(i).dropShipFlg_RC, glblMsg.C.no(i).dropShipFlg_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToLocNm_RC, glblMsg.C.no(i).shipToLocNm_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToAddlLocNm_RC, glblMsg.C.no(i).shipToAddlLocNm_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToFirstLineAddr_RC, glblMsg.C.no(i).shipToFirstLineAddr_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToScdLineAddr_RC, glblMsg.C.no(i).shipToScdLineAddr_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToThirdLineAddr_RC, glblMsg.C.no(i).shipToThirdLineAddr_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToFrthLineAddr_RC, glblMsg.C.no(i).shipToFrthLineAddr_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToFirstRefCmntTxt_RC, glblMsg.C.no(i).shipToFirstRefCmntTxt_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToScdRefCmntTxt_RC, glblMsg.C.no(i).shipToScdRefCmntTxt_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToPostCd_RC, glblMsg.C.no(i).shipToPostCd_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToCtyAddr_RC, glblMsg.C.no(i).shipToCtyAddr_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToStCd_RC, glblMsg.C.no(i).shipToStCd_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToProvNm_RC, glblMsg.C.no(i).shipToProvNm_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToCtryCd_RC, glblMsg.C.no(i).shipToCtryCd_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).shipToCntyNm_RC, glblMsg.C.no(i).shipToCntyNm_RC);

                isSame &= isEqualsEZDItem(glblMsg.C.no(i).billToCustCd_RC, glblMsg.C.no(i).billToCustCd_RC);
                isSame &= isEqualsEZDItem(glblMsg.C.no(i).billToCustAcctCd_RC, glblMsg.C.no(i).billToCustAcctCd_RC);
            }

            // S21_NA#1952
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).dsOrdPosnNum_RC,
            // bizMsg.C.no(i).dsOrdPosnNum_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).svcConfigMstrPk_RC,
            // bizMsg.C.no(i).svcConfigMstrPk_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).mdlId_RC,
            // bizMsg.C.no(i).mdlId_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToCustCd_RC,
            // bizMsg.C.no(i).shipToCustCd_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToCustAcctCd_RC,
            // bizMsg.C.no(i).shipToCustAcctCd_RC);
            //
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).dropShipFlg_RC,
            // bizMsg.C.no(i).dropShipFlg_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToLocNm_RC,
            // bizMsg.C.no(i).shipToLocNm_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToAddlLocNm_RC,
            // bizMsg.C.no(i).shipToAddlLocNm_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToFirstLineAddr_RC,
            // bizMsg.C.no(i).shipToFirstLineAddr_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToScdLineAddr_RC,
            // bizMsg.C.no(i).shipToScdLineAddr_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToThirdLineAddr_RC,
            // bizMsg.C.no(i).shipToThirdLineAddr_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToFrthLineAddr_RC,
            // bizMsg.C.no(i).shipToFrthLineAddr_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToFirstRefCmntTxt_RC,
            // bizMsg.C.no(i).shipToFirstRefCmntTxt_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToScdRefCmntTxt_RC,
            // bizMsg.C.no(i).shipToScdRefCmntTxt_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToPostCd_RC,
            // bizMsg.C.no(i).shipToPostCd_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToCtyAddr_RC,
            // bizMsg.C.no(i).shipToCtyAddr_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToStCd_RC,
            // bizMsg.C.no(i).shipToStCd_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToProvNm_RC,
            // bizMsg.C.no(i).shipToProvNm_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToCtryCd_RC,
            // bizMsg.C.no(i).shipToCtryCd_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToCntyNm_RC,
            // bizMsg.C.no(i).shipToCntyNm_RC);
            //
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).billToCustCd_RC,
            // bizMsg.C.no(i).billToCustCd_RC);
            // ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).billToCustAcctCd_RC,
            // bizMsg.C.no(i).billToCustAcctCd_RC);
        }
        // S21_NA#1952 glblMsg.C.setValidCount(i);

        // save line data to global data
        for (i = 0; i < glblMsg.D.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum) && glblMsg.D.getValidCount() > i) {
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).mdseCd_RL, glblMsg.D.no(i).mdseCd_RL);
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).ordCustUomQty_RL, glblMsg.D.no(i).ordCustUomQty_RL);
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).ordQty_RL, glblMsg.D.no(i).ordQty_RL);
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).custUomCd_RL, glblMsg.D.no(i).custUomCd_RL);
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).entCpoDtlDealSlsAmt_RL, glblMsg.D.no(i).entCpoDtlDealSlsAmt_RL);
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).prcCatgNm_RL, glblMsg.D.no(i).prcCatgNm_RL);
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).dealPrcListPrcAmt_RL, glblMsg.D.no(i).dealPrcListPrcAmt_RL);
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).dsOrdLineCatgCd_RL, glblMsg.D.no(i).dsOrdLineCatgCd_RL);
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).rtlWhCd_RL, glblMsg.D.no(i).rtlWhCd_RL);
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).rtlWhNm_RL, glblMsg.D.no(i).rtlWhNm_RL);
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).rtlSwhCd_RL, glblMsg.D.no(i).rtlSwhCd_RL);
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).rtlSwhNm_RL, glblMsg.D.no(i).rtlSwhNm_RL);
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).csmpContrNum_RL, glblMsg.D.no(i).csmpContrNum_RL);
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).dlrRefNum_RL, glblMsg.D.no(i).dlrRefNum_RL);
                isSame &= isEqualsEZDItem(glblMsg.D.no(i).csmpPrcListCd_RL, glblMsg.D.no(i).csmpPrcListCd_RL);
            }

            // S21_NA#1952
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).cpoDtlLineNum_RL,
            // bizMsg.D.no(i).cpoDtlLineNum_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).cpoDtlLineSubNum_RL,
            // bizMsg.D.no(i).cpoDtlLineSubNum_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).mdseCd_RL,
            // bizMsg.D.no(i).mdseCd_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).ordCustUomQty_RL,
            // bizMsg.D.no(i).ordCustUomQty_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).ordQty_RL,
            // bizMsg.D.no(i).ordQty_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).custUomCd_RL,
            // bizMsg.D.no(i).custUomCd_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).entCpoDtlDealSlsAmt_RL,
            // bizMsg.D.no(i).entCpoDtlDealSlsAmt_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).prcCatgNm_RL,
            // bizMsg.D.no(i).prcCatgNm_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).dealPrcListPrcAmt_RL,
            // bizMsg.D.no(i).dealPrcListPrcAmt_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).dsOrdLineCatgCd_RL,
            // bizMsg.D.no(i).dsOrdLineCatgCd_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).rtlWhCd_RL,
            // bizMsg.D.no(i).rtlWhCd_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).rtlWhNm_RL,
            // bizMsg.D.no(i).rtlWhNm_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).rtlSwhCd_RL,
            // bizMsg.D.no(i).rtlSwhCd_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).rtlSwhNm_RL,
            // bizMsg.D.no(i).rtlSwhNm_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).csmpContrNum_RL,
            // bizMsg.D.no(i).csmpContrNum_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).dlrRefNum_RL,
            // bizMsg.D.no(i).dlrRefNum_RL);
            // ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).csmpPrcListCd_RL,
            // bizMsg.D.no(i).csmpPrcListCd_RL);
        }
        // S21_NA#1952 glblMsg.D.setValidCount(i);

        if (!isSame && ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            bizMsg.xxEdtModeFlg.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    public static String getPrcCondTpDescTxt(String glblCmpyCd, String prcCondTpCd) {
        PRC_COND_TPTMsg prcCondTpTMsg = getPrcCondTp(glblCmpyCd, prcCondTpCd);
        if (null != prcCondTpTMsg) {
            return prcCondTpTMsg.prcCondTpDescTxt.getValue();
        }
        return null;
    }

    private static PRC_COND_TPTMsg getPrcCondTp(String glblCmpyCd, String prcCondTpCd) {
        PRC_COND_TPTMsg prcCondTpTMsgKey = new PRC_COND_TPTMsg();
        prcCondTpTMsgKey.glblCmpyCd.setValue(glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCondTpTMsgKey.prcCondTpCd, prcCondTpCd);
        return (PRC_COND_TPTMsg) S21CodeTableAccessor.findByKey(prcCondTpTMsgKey);
    }

    /**
     * Check SHPG_PLN.SHPG_STS_CD for Enable to Order Cancel
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @return boolean
     */
//    public static boolean isEnableToCancelForOutbound(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { 2017/10/04 S21_NA#21300 Mod Interface
    public static String isEnableToCancelForOutbound(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        // 2017/10/04 S21_NA#21300 Mod Start
//        BigDecimal count = NWAL1500Query.getInstance().isEnableToCancelForOutbound(bizMsg);
//        if (count.compareTo(BigDecimal.ZERO) == 0) {
//            return true;
//        }
//        return false;

        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().isEnableToCancelForOutbound(bizMsg);
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmRslt.getResultObject();
        String stsErr = "";
        String strPoErr = "";
        for (Map<String, Object> rslt : rsltList) {
            String tpCd = (String) rslt.get("TP_CD");
            BigDecimal cnt = (BigDecimal) rslt.get("CNT");
            if (cnt == null) {
                cnt = BigDecimal.ZERO;
            }
            if ("1".equals(tpCd) && //
                    BigDecimal.ZERO.compareTo(cnt) < 0) {
                stsErr = NWAM0672E;
            }
            if ("2".equals(tpCd) && //
                    BigDecimal.ZERO.compareTo(cnt) < 0) {
                strPoErr = NWAM0946E;
            }
            if ("3".equals(tpCd) && //
                    BigDecimal.ZERO.compareTo(cnt) < 0) {
                strPoErr = NWAM0946E;
            }
        }
        if (!stsErr.isEmpty()) {
            return stsErr;
        } else if (!strPoErr.isEmpty()) {
            return strPoErr;
        } else {
            return "";
        }
        // 2017/10/04 S21_NA#21300 Mod End
    }

    /**
     * Check DS_CPO_RTRN_DTL.RTRN_LINE_STS_CD for Enable to Order Cancel
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @return boolean
     */
    public static boolean isEnableToCancelForInbound(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        BigDecimal count = NWAL1500Query.getInstance().isEnableToCancelForInbound(bizMsg);
        if (count.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Check for ChangeOrderModification
     * @param bizMsg NWAL1500CMsg
     */
    public static void checkForChangeOrderModification(NWAL1500CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getCpoForChangeOrderModification(bizMsg);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.cpoOrdNum.setErrorInfo(1, NWAL1500MsgConstant.NWAM0142E);
            return;
        }
        Map< ? , ? > data = (Map< ? , ? >) ssmResult.getResultObject();
        String cpoSrcTpCd = (String) data.get("CPO_SRC_TP_CD");
        String ordHdrStsCd = (String) data.get("ORD_HDR_STS_CD");

        if (!ORD_HDR_STS.CLOSED.equals(ordHdrStsCd)) {
            bizMsg.cpoOrdNum.setErrorInfo(1, NWAL1500MsgConstant.NWAM0685E);
            return;
        }
        BigDecimal count = NWAL1500Query.getInstance().checkEnableChangeOrderModification(bizMsg, data);
        if (count.compareTo(BigDecimal.ZERO) != 0) {
            bizMsg.cpoOrdNum.setErrorInfo(1, NWAL1500MsgConstant.NWAM0686E);
            return;
        }

        // Mod Start 2017/10/20 QC#21773
        //if (CPO_SRC_TP.CREDIT_AND_REBILL_ENTRY.equals(cpoSrcTpCd)) {
        if (CPO_SRC_TP.CREDIT.equals(cpoSrcTpCd) //
                || CPO_SRC_TP.REBILL.equals(cpoSrcTpCd)) {
            // Mod End 2017/10/20 QC#21773
            bizMsg.cpoOrdNum.setErrorInfo(1, NWAL1500MsgConstant.NWAM0687E);
            return;
        }
    }

    // 2016/02/26 S21_NA#966 Add Start-----------
    /**
     * checkAttachMentOrderNum
     * @param bizMsg NWAL1500CMsg
     */
    public static void checkAttachMentOrderNum(NWAL1500CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getCpoForChangeOrderModification(bizMsg);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.cpoOrdNum.setErrorInfo(1, NWAL1500MsgConstant.NWAM0142E);
            return;
        }
    }
    // 2016/02/26 S21_NA#966 Add End-------------

    /**
     * compare to BigDecimal
     * @param source source value
     * @param target target value
     * @return result (0, > 0 , < 0)
     */
    public static int compareBigDecimal(BigDecimal source, BigDecimal target) {

        if (source == null) {
            if (target == null) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (target == null) {
                return 1;
            } else {
                return source.compareTo(target);
            }
        }
    }

    /**
     * getCpo
     * @param glblCmpyCd    String
     * @param cpoOrdNum     String
     * @return  CPOTMsg
     */
    public static CPOTMsg getCpo(String glblCmpyCd, String cpoOrdNum) {
        CPOTMsg inTMsg = new CPOTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.cpoOrdNum.setValue(cpoOrdNum);
        CPOTMsg outTMsg = (CPOTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * getCposlsCrAryHdr
//     * @param glblCmpyCd    String
//     * @param cpoOrdNum     String
//     * @return  DS_CPO_SLS_CRTMsgArray
//     */
//    public static DS_CPO_SLS_CRTMsgArray getCposlsCrAryHdr(String glblCmpyCd, String cpoOrdNum) {
//        DS_CPO_SLS_CRTMsg inTMsg = new DS_CPO_SLS_CRTMsg();
//        inTMsg.setSQLID("001");
//        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
//        DS_CPO_SLS_CRTMsgArray array = (DS_CPO_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
//        if (array == null || array.length() == 0) {
//            return null;
//        }
//        return array;
//    }
    // 2017/05/08 S21_NA#Review structure Lv.2 Del End

    // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * getdsCpoIstlInfoHdr
//     * @param glblCmpyCd    String
//     * @param cpoOrdNum     String
//     * @return  DS_CPO_ISTL_INFOTMsgArray
//     */
//    public static DS_CPO_ISTL_INFOTMsgArray getdsCpoIstlInfoHdr(String glblCmpyCd, String cpoOrdNum) {
//        DS_CPO_ISTL_INFOTMsg inTMsg = new DS_CPO_ISTL_INFOTMsg();
//        inTMsg.setSQLID("001");
//        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
//        DS_CPO_ISTL_INFOTMsgArray array = (DS_CPO_ISTL_INFOTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
//        if (array == null || array.length() == 0) {
//            return null;
//        }
//        return array;
//    }
    // 2017/05/08 S21_NA#Review structure Lv.2 Del End

    // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * getdsCpoDelyInfoHdr
//     * @param glblCmpyCd    String
//     * @param cpoOrdNum     String
//     * @return  DS_CPO_DELY_INFOTMsgArray
//     */
//    public static DS_CPO_DELY_INFOTMsgArray getdsCpoDelyInfoHdr(String glblCmpyCd, String cpoOrdNum) {
//        DS_CPO_DELY_INFOTMsg inTMsg = new DS_CPO_DELY_INFOTMsg();
//        inTMsg.setSQLID("001");
//        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
//        DS_CPO_DELY_INFOTMsgArray array = (DS_CPO_DELY_INFOTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
//        if (array == null || array.length() == 0) {
//            return null;
//        }
//        return array;
//    }
    // 2017/05/08 S21_NA#Review structure Lv.2 Del End

    // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * getDsCpoCtacPsn
//     * @param glblCmpyCd    String
//     * @param cpoOrdNum     String
//     * @return  DS_CPO_CTAC_PSNTMsgArray
//     */
//    public static DS_CPO_CTAC_PSNTMsgArray getDsCpoCtacPsn(String glblCmpyCd, String cpoOrdNum) {
//        DS_CPO_CTAC_PSNTMsg inTMsg = new DS_CPO_CTAC_PSNTMsg();
//        inTMsg.setSQLID("009");
//        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
//        DS_CPO_CTAC_PSNTMsgArray array = (DS_CPO_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
//        if (array == null || array.length() == 0) {
//            return null;
//        }
//        return array;
//    }
    // 2017/05/08 S21_NA#Review structure Lv.2 Del End

    // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * getDsSiteSrvyTMsgAry
//     * @param glblCmpyCd    String
//     * @param cpoOrdNum     String
//     * @return  DS_SITE_SRVYTMsgArray
//     */
//    public static DS_SITE_SRVYTMsgArray getDsSiteSrvyTMsgAry(String glblCmpyCd, String cpoOrdNum) {
//        DS_SITE_SRVYTMsg inTMsg = new DS_SITE_SRVYTMsg();
//        inTMsg.setSQLID("005");
//        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
//        DS_SITE_SRVYTMsgArray array = (DS_SITE_SRVYTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
//        if (array == null || array.length() == 0) {
//            return null;
//        }
//        return array;
//    }
    // 2017/05/08 S21_NA#Review structure Lv.2 Del End

    // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * registerCpoForChngOrdModify
//     * @param bizMsg    NWAL1500CMsg
//     * @param cpoOrdNum String
//     * @param cpoTMsg   CPOTMsg
//     * @param adminPsnCd    String
//     * @return  boolean
//     */
//    public static boolean registerCpoForChngOrdModify(NWAL1500CMsg bizMsg, String cpoOrdNum, CPOTMsg cpoTMsg, String adminPsnCd) {
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdTs, bizMsg.slsDt.getValue());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrStsCd, ORD_HDR_STS.SAVED);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.adminPsnCd, adminPsnCd);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealSlsAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealDiscAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealNetAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncSlsAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncDiscAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncNetAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealSlsAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealDiscAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealNetAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncSlsAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncDiscAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncNetAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.addRddDt, bizMsg.slsDt.getValue());
//        cpoTMsg.addRsdDt.clear();
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoHldFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoCancFlg, ZYPConstant.FLG_OFF_N);
//        cpoTMsg.cpoCancDt.clear();
//        cpoTMsg.origOrdNum.clear();
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.sendInvFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.submtFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.openFlg, ZYPConstant.FLG_ON_Y); // S21_NA#7353 Mod 
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoSrcTpCd, CPO_SRC_TP.CHANGE_ORDER_MODIFICATION);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.sysSrcCd, SYS_SRC.S21_ORDER);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.shpgChrgAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.orgRqstDelyDt, bizMsg.slsDt.getValue());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.prcBaseDt, bizMsg.slsDt.getValue());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.prcCalcDt, bizMsg.slsDt.getValue());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.negoDealAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordSrcImptDt, bizMsg.slsDt.getValue());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordSrcRefNum, bizMsg.cpoOrdNum.getValue());
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.diChkHldFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.wfRejFlg, ZYPConstant.FLG_OFF_N);
//        cpoTMsg.ordBookTs.clear();
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordBookFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoUpdVrsnNum, BigDecimal.ONE);
//        cpoTMsg.prePmtChkNum.clear();
//        cpoTMsg.prePmtAmt.clear();
//        cpoTMsg.prePmtTpCd.clear();
//        S21FastTBLAccessor.insert(cpoTMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(cpoTMsg.getReturnCode())) {
//            bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"CPO", cpoOrdNum });
//            return true;
//        }
//        return false;
//    }
    // 2017/05/08 S21_NA#Review structure Lv.2 Del End

    // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * registerDsCposlsCrForChngOrdModify
//     * @param bizMsg        NWAL1500CMsg
//     * @param cpoOrdNum     String
//     * @param dsCposlsCrAry DS_CPO_SLS_CRTMsgArray
//     * @return  boolean
//     */
//    public static boolean registerDsCposlsCrForChngOrdModify(NWAL1500CMsg bizMsg, String cpoOrdNum, DS_CPO_SLS_CRTMsgArray dsCposlsCrAry) {
//        if (dsCposlsCrAry == null) {
//            return false;
//        }
//        DS_CPO_SLS_CRTMsg inTMsg;
//        for (int i = 0; i < dsCposlsCrAry.getValidCount(); i++) {
//            inTMsg = dsCposlsCrAry.no(i);
//            ZYPEZDItemValueSetter.setValue(inTMsg.dsCpoSlsCrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_SLS_CR_SQ));
//            ZYPEZDItemValueSetter.setValue(inTMsg.cpoOrdNum, cpoOrdNum);
//            S21FastTBLAccessor.insert(inTMsg);
//            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"DS_CPO_SLS_CR", cpoOrdNum });
//                return true;
//            }
//        }
//        return false;
//    }
    // 2017/05/08 S21_NA#Review structure Lv.2 Del End

    // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * registerDsCpoIstlInfoForChngOrdModify
//     * @param bizMsg        NWAL1500CMsg
//     * @param cpoOrdNum     String
//     * @param dsCpoIstlInfoAry  DS_CPO_ISTL_INFOTMsgArray
//     * @return  boolean
//     */
//    public static boolean registerDsCpoIstlInfoForChngOrdModify(NWAL1500CMsg bizMsg, String cpoOrdNum, DS_CPO_ISTL_INFOTMsgArray dsCpoIstlInfoAry) {
//        if (dsCpoIstlInfoAry == null) {
//            return false;
//        }
//        DS_CPO_ISTL_INFOTMsg inTMsg;
//        for (int i = 0; i < dsCpoIstlInfoAry.getValidCount(); i++) {
//            inTMsg = dsCpoIstlInfoAry.no(i);
//            // ZYPEZDItemValueSetter.setValue(inTMsg.dsCpoIstlInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_INSTL_SQ)); 2016/10/14 S21_NA#13948-2 Del
//            ZYPEZDItemValueSetter.setValue(inTMsg.dsCpoIstlInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_ISTL_INFO_SQ)); // 2016/10/14 S21_NA#13948-2 Add
//            ZYPEZDItemValueSetter.setValue(inTMsg.cpoOrdNum, cpoOrdNum);
//            S21FastTBLAccessor.insert(inTMsg);
//            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"DS_CPO_ISTL_INFO", cpoOrdNum });
//                return true;
//            }
//        }
//        return false;
//    }
    // 2017/05/08 S21_NA#Review structure Lv.2 Del End

    // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * registerDsCpoDelyInfoForChngOrdModify
//     * @param bizMsg        NWAL1500CMsg
//     * @param cpoOrdNum     String
//     * @param dsCpoDelyInfoAry  DS_CPO_DELY_INFOTMsgArray
//     * @return  boolean
//     */
//    public static boolean registerDsCpoDelyInfoForChngOrdModify(NWAL1500CMsg bizMsg, String cpoOrdNum, DS_CPO_DELY_INFOTMsgArray dsCpoDelyInfoAry) {
//        if (dsCpoDelyInfoAry == null) {
//            return false;
//        }
//        DS_CPO_DELY_INFOTMsg inTMsg;
//        for (int i = 0; i < dsCpoDelyInfoAry.getValidCount(); i++) {
//            inTMsg = dsCpoDelyInfoAry.no(i);
////            ZYPEZDItemValueSetter.setValue(inTMsg.dsCpoDelyInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_DELY_SQ)); 2016/08/30 S21_NA#13948 Del
//            ZYPEZDItemValueSetter.setValue(inTMsg.dsCpoDelyInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_DELY_INFO_SQ)); // 2016/08/30 S21_NA#13948 Add
//            ZYPEZDItemValueSetter.setValue(inTMsg.cpoOrdNum, cpoOrdNum);
//            ZYPEZDItemValueSetter.setValue(inTMsg.rddDt, bizMsg.slsDt.getValue());
//            S21FastTBLAccessor.insert(inTMsg);
//            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"DS_CPO_DELY_INFO", cpoOrdNum });
//                return true;
//            }
//        }
//        return false;
//    }
    // 2017/05/08 S21_NA#Review structure Lv.2 Del End

    // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * registerDsCpoCtacPsnForChngOrdModify
//     * @param bizMsg    NWAL1500CMsg
//     * @param cpoOrdNum String
//     * @param dsCpoCtacPsnAry   DS_CPO_CTAC_PSNTMsgArray
//     * @return  boolean
//     */
//    public static boolean registerDsCpoCtacPsnForChngOrdModify(NWAL1500CMsg bizMsg, String cpoOrdNum, DS_CPO_CTAC_PSNTMsgArray dsCpoCtacPsnAry) {
//        if (dsCpoCtacPsnAry == null) {
//            return false;
//        }
//        DS_CPO_CTAC_PSNTMsg inTMsg;
//        for (int i = 0; i < dsCpoCtacPsnAry.getValidCount(); i++) {
//            inTMsg = dsCpoCtacPsnAry.no(i);
//            ZYPEZDItemValueSetter.setValue(inTMsg.dsCpoCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_CTAC_PSN_SQ));
//            ZYPEZDItemValueSetter.setValue(inTMsg.cpoOrdNum, cpoOrdNum);
//            S21FastTBLAccessor.insert(inTMsg);
//            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"DS_CPO_CTAC_PSN", cpoOrdNum });
//                return true;
//            }
//        }
//        return false;
//    }
    // 2017/05/08 S21_NA#Review structure Lv.2 Del End

    // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//    /**
//     * Register DS Site Survey For Change Order Modify.
//     * @param bizMsg NWAL1500CMsg
//     * @param cpoOrdNum String
//     * @param dsSiteSrvyTMsgAry DS_SITE_SRVYTMsgArray
//     * @return boolean
//     */
//    public static boolean registerDsSiteSrvyForChngOrdModify(NWAL1500CMsg bizMsg, String cpoOrdNum, DS_SITE_SRVYTMsgArray dsSiteSrvyTMsgAry) {
//        if (dsSiteSrvyTMsgAry == null) {
//            return false;
//        }
//        DS_SITE_SRVYTMsg inTMsg;
//        for (int i = 0; i < dsSiteSrvyTMsgAry.getValidCount(); i++) {
//            inTMsg = dsSiteSrvyTMsgAry.no(i);
//            ZYPEZDItemValueSetter.setValue(inTMsg.dsSiteSrvyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SITE_SRVY_SQ));
//            ZYPEZDItemValueSetter.setValue(inTMsg.cpoOrdNum, cpoOrdNum);
//            S21FastTBLAccessor.insert(inTMsg);
//            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
//                bizMsg.setMessageInfo(NWAL1500MsgConstant.NWZM1023E, new String[] {"DS_SITE_SRVY", cpoOrdNum });
//                return true;
//            }
//        }
//        return false;
//    }
    // 2017/05/08 S21_NA#Review structure Lv.2 Del End

    /**
     * <pre>
     * Check merchandise tangible or intangible
     * </pre>
     * @param glblCmpyCd global company Code
     * @param mdseCd merchandise code for check.
     * @return true: tangible or mdseCd is null or mdse ins not in master false: intangible.
     */
    public static boolean isMdseTangible(String glblCmpyCd, String mdseCd) {
        MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdseCd);
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return true;
        }
        if (null == mdseTMsg) {
            return true;
        }
        return ZYPConstant.FLG_ON_Y.equals(mdseTMsg.invtyCtrlFlg.getValue());
    }

    /**
     * getSpecCondPrcPk
     * @param glblCmpyCd String
     * @param prcCondTpCd String
     * @return BigDecimal
     */
    private static BigDecimal getSpecCondPrcPk(String glblCmpyCd, String prcCondTpCd) {
        SPEC_COND_PRCTMsg inTMsg = new SPEC_COND_PRCTMsg();
        inTMsg.setSQLID("004");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("prcCondTpCd01", prcCondTpCd);
        SPEC_COND_PRCTMsgArray array = (SPEC_COND_PRCTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (array == null || array.length() == 0) {
            return BigDecimal.ONE;
        }
        return array.no(0).specCondPrcPk.getValue();
    }

    /**
     * <pre>
     * Set Ds Info
     * </pre>
     * @param config NWAL1500_ASMsg
     * @param resultMap Map<String, String>
     */
    public static void setDsInfo(NWAL1500_ASMsg config, Map<String, String> resultMap) { // 2018/01/29 S21_NA#19808 Mod
        if (resultMap == null) {
            config.shipToCustCd_LC.setErrorInfo(1, NWAM0181E, new String[] {"Ship to Loc" }); // S21_NA#6148
            return;
        }
        ZYPEZDItemValueSetter.setValue(config.shipToFirstLineAddr_LC, resultMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(config.shipToScdLineAddr_LC, resultMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(config.shipToThirdLineAddr_LC, resultMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(config.shipToFrthLineAddr_LC, resultMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(config.shipToFirstRefCmntTxt_LC, resultMap.get("FIRST_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(config.shipToScdRefCmntTxt_LC, resultMap.get("SCD_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(config.shipToCtyAddr_LC, resultMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(config.shipToStCd_LC, resultMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(config.shipToPostCd_LC, resultMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(config.shipToProvNm_LC, resultMap.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(config.shipToCtryCd_LC, resultMap.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(config.shipToCntyNm_LC, resultMap.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(config.dropShipFlg_LC, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(config.shipToCustLocAddr_LC, resultMap.get("SHIP_TO_ADDR"));   // S21_NA#6148
        // QC#26151
        ZYPEZDItemValueSetter.setValue(config.shipToCustAcctCd_LC, resultMap.get("SHIP_TO_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(config.shipToCustAcctNm_LC, resultMap.get("SHIP_TO_ACCT_NM"));
        // 2017/12/08 S21_NA#21621 Add Start
        config.addShipToLocNm_LC.clear();
        // 2017/12/08 S21_NA#21621 Add End
    }

    /**
     * S21_NA#6148
     * <pre>
     * Set Ds Info
     * </pre>
     * @param config NWAL1500_ACMsg
     * @param resultMap Map<String, String>
     */
    public static void setDsInfoBill(NWAL1500_ASMsg config, Map<String, String> resultMap) { // 2018/01/29 S21_NA#19808 Mod
        if (resultMap == null) {
            config.billToCustCd_LC.setErrorInfo(1, NWAM0181E, new String[] {"Bill to Loc" });
            return;
        }
        ZYPEZDItemValueSetter.setValue(config.billToCustLocAddr_LC, resultMap.get("BILL_TO_ADDR"));
        // QC#26151
        ZYPEZDItemValueSetter.setValue(config.billToCustAcctCd_LC, resultMap.get("BILL_TO_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(config.billToCustAcctNm_LC, resultMap.get("BILL_TO_ACCT_NM"));
    }

    /**
     * <pre>
     * Set DS Info
     * </pre>
     * @param config NWAL1500_CCMsg
     * @param resultMap Map<String, String>
     */
    public static void setDsInfo(NWAL1500_CSMsg config, Map<String, String> resultMap) { // 2018/01/29 S21_NA#19808 Mod
        if (resultMap == null) {
            config.shipToCustCd_RC.setErrorInfo(1, NWAM0181E, new String[] {"Ship to Loc" }); // S21_NA#6148
            return;
        }
        ZYPEZDItemValueSetter.setValue(config.shipToFirstLineAddr_RC, resultMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(config.shipToScdLineAddr_RC, resultMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(config.shipToThirdLineAddr_RC, resultMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(config.shipToFrthLineAddr_RC, resultMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(config.shipToFirstRefCmntTxt_RC, resultMap.get("FIRST_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(config.shipToScdRefCmntTxt_RC, resultMap.get("SCD_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(config.shipToCtyAddr_RC, resultMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(config.shipToStCd_RC, resultMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(config.shipToPostCd_RC, resultMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(config.shipToProvNm_RC, resultMap.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(config.shipToCtryCd_RC, resultMap.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(config.shipToCntyNm_RC, resultMap.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(config.dropShipFlg_RC, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(config.shipToCustLocAddr_RC, resultMap.get("SHIP_TO_ADDR"));   // S21_NA#6148
        // QC#26151
        ZYPEZDItemValueSetter.setValue(config.shipToCustAcctCd_RC, resultMap.get("SHIP_TO_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(config.shipToCustAcctNm_RC, resultMap.get("SHIP_TO_ACCT_NM"));
        // 2017/12/08 S21_NA#21621 Add Start
        config.addShipToLocNm_RC.clear();
        // 2017/12/08 S21_NA#21621 Add End
    }

    /**
     * S21_NA#6148
     * <pre>
     * Set DS Info(Bill to)
     * </pre>
     * @param config NWAL1500_ACMsg
     * @param resultMap Map<String, String>
     */
    public static void setDsInfoBill(NWAL1500_CSMsg config, Map<String, String> resultMap) { // 2018/01/29 S21_NA#19808 Mod
        if (resultMap == null) {
            config.billToCustCd_RC.setErrorInfo(1, NWAM0181E, new String[] {"Bill to Loc" });
            return;
        }
        ZYPEZDItemValueSetter.setValue(config.billToCustLocAddr_RC, resultMap.get("BILL_TO_ADDR"));
        // QC#26151
        ZYPEZDItemValueSetter.setValue(config.billToCustAcctCd_RC, resultMap.get("BILL_TO_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(config.billToCustAcctNm_RC, resultMap.get("BILL_TO_ACCT_NM"));
    }


    /**
     * <pre>
     * is warehouse required.
     * </pre>
     * @param glblCmpyCd global company Code
     * @param mdseCd merchandise code for check.
     * @return needs warehouse or not.
     */
    public static boolean needsWh(String glblCmpyCd, String mdseCd) {

        if (isSetMerchandise(glblCmpyCd, mdseCd)) {
            return true;
        }
        if (isMdseTangible(glblCmpyCd, mdseCd)) {
            return true;
        }
        return false;
    }

    // S21_NA#2522 add start
    /**
     * <pre>
     * prepare set to component.
     * </pre>
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     */
    public static void prepareSetToConponent(NWAL1500SMsg glblMsg) { // 2018/01/29 S21_NA#19808 Mod

        // 2018/01/29 S21_NA#19808 Mod bizMsg => glblMsg
        // line configuration
        NWAL1500_BSMsg parentLine = null; // 2018/01/29 S21_NA#19808 Mod
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg currentLine = glblMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(currentLine.dsCpoLineSubNum_LL)) {
                parentLine = currentLine;
            } else {
                // component
                if (parentLine != null) {
                    copyParentValue(parentLine, currentLine);
                    // QC743
                    // NWAL1500CommonLogicForLineControl.storeLine(glblMsg.J, currentLine);
                }
            }
        }

        // RMA
        NWAL1500_DSMsg parentRmaLine = null; // 2018/01/29 S21_NA#19808 Mod
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg currentLine = glblMsg.D.no(i);
            if (!ZYPCommonFunc.hasValue(currentLine.dsCpoLineSubNum_RL)) {
                parentRmaLine = currentLine;
            } else {
                // component
                if (parentRmaLine != null) {
                    copyParentValue(parentRmaLine, currentLine);
                    // QC743
                    // NWAL1500CommonLogicForLineControl.storeLine(glblMsg.K, currentLine);
                }
            }
        }
    }

    private static void copyParentValue(NWAL1500_BSMsg parentLine, NWAL1500_BSMsg childLine) { // 2018/01/29 S21_NA#19808 Mod

        // sell price list
        ZYPEZDItemValueSetter.setValue(childLine.prcCatgCd_LL, parentLine.prcCatgCd_LL);
        ZYPEZDItemValueSetter.setValue(childLine.prcCatgNm_LL, parentLine.prcCatgNm_LL);
        // source
        ZYPEZDItemValueSetter.setValue(childLine.ordLineSrcCd_LL, parentLine.ordLineSrcCd_LL);
        // WH
        ZYPEZDItemValueSetter.setValue(childLine.rtlWhCd_LL, parentLine.rtlWhCd_LL);
        ZYPEZDItemValueSetter.setValue(childLine.rtlWhNm_LL, parentLine.rtlWhNm_LL);
        // sub WH
        ZYPEZDItemValueSetter.setValue(childLine.rtlSwhCd_LL, parentLine.rtlSwhCd_LL);
        ZYPEZDItemValueSetter.setValue(childLine.rtlSwhNm_LL, parentLine.rtlSwhNm_LL);
        // floor price list
        ZYPEZDItemValueSetter.setValue(childLine.flPrcListCd_LL, parentLine.flPrcListCd_LL);
        ZYPEZDItemValueSetter.setValue(childLine.flPrcListNm_LL, parentLine.flPrcListNm_LL);
        // requested date
        ZYPEZDItemValueSetter.setValue(childLine.rddDt_LL, parentLine.rddDt_LL);
        // S21_NA#8317 Add Start
        // Line Category
        ZYPEZDItemValueSetter.setValue(childLine.dsOrdLineCatgCd_LL , parentLine.dsOrdLineCatgCd_LL);
        // S21_NA#8317 Add End
    }

    private static void copyParentValue(NWAL1500_DSMsg parentLine, NWAL1500_DSMsg childLine) { // 2018/01/29 S21_NA#19808 Mod

        // sell price list
        ZYPEZDItemValueSetter.setValue(childLine.prcCatgCd_RL, parentLine.prcCatgCd_RL);
        ZYPEZDItemValueSetter.setValue(childLine.prcCatgNm_RL, parentLine.prcCatgNm_RL);
        // WH
        ZYPEZDItemValueSetter.setValue(childLine.rtlWhCd_RL, parentLine.rtlWhCd_RL);
        ZYPEZDItemValueSetter.setValue(childLine.rtlWhNm_RL, parentLine.rtlWhNm_RL);
        // sub WH
        ZYPEZDItemValueSetter.setValue(childLine.rtlSwhCd_RL, parentLine.rtlSwhCd_RL);
        ZYPEZDItemValueSetter.setValue(childLine.rtlSwhNm_RL, parentLine.rtlSwhNm_RL);
        // floor price list
        ZYPEZDItemValueSetter.setValue(childLine.flPrcListCd_RL, parentLine.flPrcListCd_RL);
        ZYPEZDItemValueSetter.setValue(childLine.flPrcListNm_RL, parentLine.flPrcListNm_RL);
        // requested date
        ZYPEZDItemValueSetter.setValue(childLine.rqstPickUpDt_RL, parentLine.rqstPickUpDt_RL);
        // return reason code
        ZYPEZDItemValueSetter.setValue(childLine.rtrnRsnCd_RL, parentLine.rtrnRsnCd_RL);
        // HDD removable code
        ZYPEZDItemValueSetter.setValue(childLine.hddRmvCd_RL, parentLine.hddRmvCd_RL);
        // line category
        ZYPEZDItemValueSetter.setValue(childLine.dsOrdLineCatgCd_RL, parentLine.dsOrdLineCatgCd_RL);
    }
    // S21_NA#2522 add end

    /**
     * Clear Control Fields
     * @param bizMsg NWAL1500CMsg
     * @param configMsg NWAL1500_ACMsg
     */
    public static void clearControlFields(NWAL1500SMsg glblMsg, NWAL1500_ASMsg configMsg) { // 2018/01/29 S21_NA#19808 Mod
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            if (S21StringUtil.isEquals(configMsg.dsOrdPosnNum_LC.getValue(), lineMsg.dsOrdPosnNum_LL.getValue())) {
                lineMsg.bllgAttrbCustAcctCd_LL.clear();
                lineMsg.firstBllgAttrbNm_LL.clear();
                lineMsg.firstBllgAttrbValTxt_LL.clear();
                lineMsg.scdBllgAttrbNm_LL.clear();
                lineMsg.scdBllgAttrbValTxt_LL.clear();
                lineMsg.thirdBllgAttrbNm_LL.clear();
                lineMsg.thirdBllgAttrbValTxt_LL.clear();
                lineMsg.frthBllgAttrbNm_LL.clear();
                lineMsg.frthBllgAttrbValTxt_LL.clear();
                lineMsg.fifthBllgAttrbNm_LL.clear();
                lineMsg.fifthBllgAttrbValTxt_LL.clear();
                lineMsg.sixthBllgAttrbNm_LL.clear();
                lineMsg.sixthBllgAttrbValTxt_LL.clear();
            }
        }
    }

    /**
     * S21_NA#6148
     * Clear RMA Control Fields
     * @param bizMsg NWAL1500CMsg
     * @param configMsg NWAL1500_CCMsg
     */
    public static void clearRMAControlFields(NWAL1500SMsg glblMsg, NWAL1500_CSMsg configMsg) { // 2018/01/29 S21_NA#19808 Mod
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg lineMsg = glblMsg.D.no(i);
            if (S21StringUtil.isEquals(configMsg.dsOrdPosnNum_RC.getValue(), lineMsg.dsOrdPosnNum_RL.getValue())) {
                lineMsg.bllgAttrbCustAcctCd_RL.clear();
                lineMsg.firstBllgAttrbNm_RL.clear();
                lineMsg.firstBllgAttrbValTxt_RL.clear();
                lineMsg.scdBllgAttrbNm_RL.clear();
                lineMsg.scdBllgAttrbValTxt_RL.clear();
                lineMsg.thirdBllgAttrbNm_RL.clear();
                lineMsg.thirdBllgAttrbValTxt_RL.clear();
                lineMsg.frthBllgAttrbNm_RL.clear();
                lineMsg.frthBllgAttrbValTxt_RL.clear();
                lineMsg.fifthBllgAttrbNm_RL.clear();
                lineMsg.fifthBllgAttrbValTxt_RL.clear();
                lineMsg.sixthBllgAttrbNm_RL.clear();
                lineMsg.sixthBllgAttrbValTxt_RL.clear();
            }
        }
    }

    private static boolean isSameLineByDsCpoLineNum(NWAL1500_BSMsg lineMsg1, NWAL1500_BSMsg lineMsg2) { // 2018/01/29 S21_NA#19808

        if (!S21StringUtil.isEquals(lineMsg1.dsOrdPosnNum_LL.getValue(), lineMsg2.dsOrdPosnNum_LL.getValue())) {
            return false;
        }
        if (compareBigDecimal(lineMsg1.dsCpoLineNum_LL.getValue(), lineMsg2.dsCpoLineNum_LL.getValue()) != 0) { // S21_NA#2905
            return false;
        }
        return true;
    }

    private static boolean isSameLineByDsCpoLineNum(NWAL1500_DSMsg lineMsg1, NWAL1500_DSMsg lineMsg2) {

        if (!S21StringUtil.isEquals(lineMsg1.dsOrdPosnNum_RL.getValue(), lineMsg2.dsOrdPosnNum_RL.getValue())) {
            return false;
        }
        if (compareBigDecimal(lineMsg1.dsCpoLineNum_RL.getValue(), lineMsg2.dsCpoLineNum_RL.getValue()) != 0) { // S21_NA#2905
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * check item is set parent or not.
     * @param glblCmpyCd Global Company Code
     * @param mdseCd Merchandise Code
     * @return true: Set parent false: regular item
     * </pre>
     */
    public static boolean isSetMerchandise(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdse = getMdseWithRgtnStsCd(glblCmpyCd, mdseCd);
        if (mdse == null) {
            return false;
        }
        return S21StringUtil.isEquals(mdse.mdseTpCd.getValue(), MDSE_TP.SET);
    }

    private static void printPriceElem(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String opt) {
        if (!LOG_OUT) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("####---------- Price Element NWAL1500_ISMsg Start ----------####\r\n");
        sb.append(opt + "\r\n");
        for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
            NWAL1500_ISMsg prcElem = glblMsg.I.no(i);
            sb.append("No[" + String.valueOf(i) + "]\r\n");
            sb.append("Config Category Code     :" + prcElem.configCatgCd_LP.getValue() + "\r\n");
            sb.append("Detail Line#             :" + prcElem.cpoDtlLineNum_LP.getValue() + "\r\n");
            sb.append("Detail Line Sub#         :" + prcElem.cpoDtlLineSubNum_LP.getValue() + "\r\n");
            sb.append("Display Line #           :" + prcElem.xxLineNum_LP.getValue() + "\r\n");
            sb.append("Price Detail Group Cd    :" + prcElem.prcDtlGrpCd_LP.getValue() + "\r\n");
            sb.append("Auto Price Amount Rate   :" + String.valueOf(prcElem.autoPrcAmtRate_LP.getValue()) + "\r\n");
            sb.append("Manual Price Amount Rate :" + String.valueOf(prcElem.manPrcAmtRate_LP.getValue()) + "\r\n");
            sb.append("Order Price Calc Base PK :" + prcElem.ordPrcCalcBasePk_LP.getValue() + "\r\n");
            sb.append("Special Cond Price PK    :" + prcElem.specCondPrcPk_LP.getValue() + "\r\n");
            sb.append("Calculate Price Amount Rate :" + String.valueOf(prcElem.calcPrcAmtRate_LP.getValue()) + "\r\n");
        }
        sb.append(opt + "\r\n");
        sb.append("####---------- Price Element NWAL1500_ISMsg End ----------####\r\n");
        System.out.println(sb.toString());
    }

    private static void printPricingDetail(NWZC157001PMsg prcApiPMsg, String opt) {
        if (!LOG_OUT) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("####---------- Pricing API Detail Parameter Result Start ----------####\r\n");
        sb.append(opt + "\r\n");
        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            sb.append("****** Level 2 Start ******\r\n");
            NWZC157002PMsg prcDtlPMsg = prcApiPMsg.NWZC157002PMsg.no(i);

            sb.append("no[" + String.valueOf(i) + "]\r\n");
            sb.append("Config Category:   [" + prcDtlPMsg.configCatgCd.getValue() + "]\r\n");
            sb.append("Detail Line#   :   [" + prcDtlPMsg.trxLineNum.getValue() + "]\r\n");
            sb.append("Detail Sub Line# : [" + prcDtlPMsg.trxLineSubNum.getValue() + "]\r\n");
            sb.append("****** Level 2 End ******\r\n");
            for (int j = 0; j < prcDtlPMsg.NWZC157003PMsg.getValidCount(); j++) {
                sb.append("+-+-+- Level 3 [" + String.valueOf(j) + "] Start\r\n");
                NWZC157003PMsg prcElemPMsg = prcDtlPMsg.NWZC157003PMsg.no(j);
                sb.append("Config Category          = " + String.valueOf(prcElemPMsg.configCatgCd.getValue()) + "\r\n");
                sb.append("Price Detail Group       = " + String.valueOf(prcElemPMsg.prcDtlGrpCd.getValue()) + "\r\n");
                sb.append("Price Category Code      = " + String.valueOf(prcElemPMsg.prcCatgCd.getValue()) + "\r\n");
                sb.append("Auto Price Amount Rete   = " + String.valueOf(prcElemPMsg.autoPrcAmtRate.getValue()) + "\r\n");
                sb.append("Manual Price Amount Rete = " + String.valueOf(prcElemPMsg.manPrcAmtRate.getValue()) + "\r\n");
                sb.append("+-+-+- Level 3 [" + String.valueOf(j) + "] End\r\n");
            }
        }
        sb.append(opt + "\r\n");
        sb.append("####---------- Pricing API Detail Parameter Result End ----------####\r\n");
        System.out.println(sb.toString());
    }

    private static void printPricingResult(NWZC157001PMsg prcApiPMsg) {
        if (!LOG_OUT) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("####---------- Pricing API Result Start ----------####\r\n");
        for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(i);

            sb.append("no[" + String.valueOf(i) + "]\r\n");
            sb.append("Config Category:   [" + prcTotalPMsg.configCatgCd.getValue() + "]\r\n");
            sb.append("Detail Line#   :   [" + prcTotalPMsg.trxLineNum.getValue() + "]\r\n");
            sb.append("Detail Sub Line# : [" + prcTotalPMsg.trxLineSubNum.getValue() + "]\r\n");
            sb.append("Unit Gross Price Amount = " + String.valueOf(prcTotalPMsg.xxUnitGrsPrcAmt.getValue()) + "\r\n");
            sb.append("Unit Net Price Amount   = " + String.valueOf(prcTotalPMsg.xxUnitNetPrcAmt.getValue()) + "\r\n");
            sb.append("Total Tax Amount        = " + String.valueOf(prcTotalPMsg.xxTotTaxAmt.getValue()) + "\r\n");
            sb.append("Total Amount            = " + String.valueOf(prcTotalPMsg.xxTotAmt.getValue()) + "\r\n");
        }
        sb.append("####---------- Pricing API Result End   ----------####\r\n");
        System.out.println(sb.toString());
    }

//    // 
//    /**
//     * Copy Action Pulldown Header
//     * @param bizMsg NWAL1500CMsg
//     * @param glblMsg NWAL1500SMsg
//     */
//    public static void copyActionPulldownHeader(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
//        // Action
//        bizMsg.ordEntryActCd_AC.clear();
//        bizMsg.ordEntryActCd_CA.clear();
//        bizMsg.ordEntryActDescTxt_NA.clear();
//        for (int i = 0; i < glblMsg.ordEntryActCd_HA.length(); i++) {
//            if (ZYPCommonFunc.hasValue(glblMsg.ordEntryActCd_HA.no(i))) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_CA.no(i), glblMsg.ordEntryActCd_HA.no(i));
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_NA.no(i), glblMsg.ordEntryActDescTxt_HA.no(i));
//            } else {
//                break;
//            }
//        }
//        // Addtional
//        bizMsg.ordEntryActCd_IF.clear();
//        bizMsg.ordEntryActCd_CI.clear();
//        bizMsg.ordEntryActDescTxt_NI.clear();
//        for (int i = 0; i < glblMsg.ordEntryActCd_HI.length(); i++) {
//            if (ZYPCommonFunc.hasValue(glblMsg.ordEntryActCd_HI.no(i))) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_CI.no(i), glblMsg.ordEntryActCd_HI.no(i));
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_NI.no(i), glblMsg.ordEntryActDescTxt_HI.no(i));
//            } else {
//                break;
//            }
//        }
//        // DISC
//        bizMsg.ordEntryActCd_DL.clear();
//        bizMsg.ordEntryActCd_CD.clear();
//        bizMsg.ordEntryActDescTxt_ND.clear();
//        for (int i = 0; i < glblMsg.ordEntryActCd_HD.length(); i++) {
//            if (ZYPCommonFunc.hasValue(glblMsg.ordEntryActCd_HD.no(i))) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_CD.no(i), glblMsg.ordEntryActCd_HD.no(i));
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_ND.no(i), glblMsg.ordEntryActDescTxt_HD.no(i));
//            } else {
//                break;
//            }
//        }
//        // Tools
//        bizMsg.ordEntryActCd_TO.clear();
//        bizMsg.ordEntryActCd_CT.clear();
//        bizMsg.ordEntryActDescTxt_NT.clear();
//        for (int i = 0; i < glblMsg.ordEntryActCd_HT.length(); i++) {
//            if (ZYPCommonFunc.hasValue(glblMsg.ordEntryActCd_HT.no(i))) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_CT.no(i), glblMsg.ordEntryActCd_HT.no(i));
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_NT.no(i), glblMsg.ordEntryActDescTxt_HT.no(i));
//            } else {
//                break;
//            }
//        }
//    }
//
//    /**
//     * Copy Action Pulldown Line
//     * @param bizMsg NWAL1500CMsg
//     * @param glblMsg NWAL1500SMsg
//     */
//    public static void copyActionPulldownLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
//        // Action
//        bizMsg.ordEntryActCd_AC.clear();
//        bizMsg.ordEntryActCd_CA.clear();
//        bizMsg.ordEntryActDescTxt_NA.clear();
//        for (int i = 0; i < glblMsg.ordEntryActCd_LA.length(); i++) {
//            if (ZYPCommonFunc.hasValue(glblMsg.ordEntryActCd_LA.no(i))) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_CA.no(i), glblMsg.ordEntryActCd_LA.no(i));
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_NA.no(i), glblMsg.ordEntryActDescTxt_LA.no(i));
//            } else {
//                break;
//            }
//        }
//        // Addtional
//        bizMsg.ordEntryActCd_IF.clear();
//        bizMsg.ordEntryActCd_CI.clear();
//        bizMsg.ordEntryActDescTxt_NI.clear();
//        for (int i = 0; i < glblMsg.ordEntryActCd_LI.length(); i++) {
//            if (ZYPCommonFunc.hasValue(glblMsg.ordEntryActCd_LI.no(i))) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_CI.no(i), glblMsg.ordEntryActCd_LI.no(i));
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_NI.no(i), glblMsg.ordEntryActDescTxt_LI.no(i));
//            } else {
//                break;
//            }
//        }
//        // DISC
//        bizMsg.ordEntryActCd_DL.clear();
//        bizMsg.ordEntryActCd_CD.clear();
//        bizMsg.ordEntryActDescTxt_ND.clear();
//        for (int i = 0; i < glblMsg.ordEntryActCd_LD.length(); i++) {
//            if (ZYPCommonFunc.hasValue(glblMsg.ordEntryActCd_LD.no(i))) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_CD.no(i), glblMsg.ordEntryActCd_LD.no(i));
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_ND.no(i), glblMsg.ordEntryActDescTxt_LD.no(i));
//            } else {
//                break;
//            }
//        }
//        // Tools
//        bizMsg.ordEntryActCd_TO.clear();
//        bizMsg.ordEntryActCd_CT.clear();
//        bizMsg.ordEntryActDescTxt_NT.clear();
//        for (int i = 0; i < glblMsg.ordEntryActCd_LT.length(); i++) {
//            if (ZYPCommonFunc.hasValue(glblMsg.ordEntryActCd_LT.no(i))) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_CT.no(i), glblMsg.ordEntryActCd_LT.no(i));
//                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_NT.no(i), glblMsg.ordEntryActDescTxt_LT.no(i));
//            } else {
//                break;
//            }
//        }
//    }

    /**
     * Get Varchar Const Data List
     * @param glblCmpyCd Global Company Code
     * @param varCharConstKey Const Key
     * @return List<String>
     */
    public static List<String> getVarCharConstDataList(String glblCmpyCd, String varCharConstKey) {

        List<String> varcharConstValueList = new ArrayList<String>(0);

        String varcharConstValue = ZYPCodeDataUtil.getVarCharConstValue(varCharConstKey, glblCmpyCd);
        if (null == varcharConstValue || varcharConstValue.length() == 0) {
            return varcharConstValueList;
        }

        String[] varcharConstValueArray = varcharConstValue.split(",");
        for (String val : varcharConstValueArray) {
            varcharConstValueList.add(val);
        }

        return varcharConstValueList;
    }

    /**
     * Check Price Range All.
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     * @return boolean
     */
    public static boolean checkPriceRangeAll(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        // 2018/01/29 S21_NA#19808 bizMsg.B, D => glblMsg.B, D
        boolean isNormal = true;

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            NWAL1500_ISMsg basePrcIBMsg = getBasePriceDataFromBizlMsg(lineMsg, bizMsg, glblMsg);
            if (!checkPriceRange(lineMsg, basePrcIBMsg)) {
                isNormal = false;
            }
        }

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg lineMsg = glblMsg.D.no(i);
            NWAL1500_ISMsg basePrcIBMsg = getBasePriceDataFromBizlMsg(lineMsg, bizMsg, glblMsg);
            if (!checkPriceRange(lineMsg, basePrcIBMsg)) {
                isNormal = false;
            }
        }
        return isNormal;
    }

    /**
     * Check Price Range.
     * @param lineMsg NWAL1500_BCMsg
     * @param basePrcLineMsg NWAL1500_ISMsg
     * @return boolean
     */
    public static boolean checkPriceRange(NWAL1500_BSMsg lineMsg, NWAL1500_ISMsg basePrcLineMsg) { // 2018/01/25 S21_NA#19808 
        if (!isRangeAmt(lineMsg.entDealNetUnitPrcAmt_LL, basePrcLineMsg.minPrcAmtRate_LP, basePrcLineMsg.maxPrcAmtRate_LP, lineMsg.entCpoDtlDealSlsAmt_LL)) {
            return false;
        }
        return true;
    }

    /**
     * Check Price Range.
     * @param lineMsg NWAL1500_DCMsg
     * @param basePrcLineMsg NWAL1500_ISMsg
     * @return boolean
     */
    public static boolean checkPriceRange(NWAL1500_DSMsg lineMsg, NWAL1500_ISMsg basePrcLineMsg) { // 2018/01/25 S21_NA#19808 
        if (!isRangeAmt(lineMsg.entDealNetUnitPrcAmt_RL, basePrcLineMsg.minPrcAmtRate_LP, basePrcLineMsg.maxPrcAmtRate_LP, lineMsg.entCpoDtlDealSlsAmt_RL)) {
            return false;
        }
        return true;
    }

    private static boolean isRangeAmt(EZDSBigDecimalItem val, EZDSBigDecimalItem minVal, EZDSBigDecimalItem maxVal, EZDSBigDecimalItem msgItem) { // 2018/01/25 S21_NA#19808 
        if (!ZYPCommonFunc.hasValue(val)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(minVal)) {
            if (val.getValue().compareTo(minVal.getValue()) < 0) {
                msgItem.setErrorInfo(2, "NWAM0880W", new String[] {NWAL1500MsgConstant.MSG_PARAM_MIN_PRC, minVal.getValue().toString() }); //S21_NA#7942 MOD
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(maxVal)) {
            if (val.getValue().compareTo(maxVal.getValue()) > 0) {
                msgItem.setErrorInfo(2, "NWAM0880W", new String[] {NWAL1500MsgConstant.MSG_PARAM_MAX_PRC, maxVal.getValue().toString() }); //S21_NA#7942 MOD
                return false;
            }
        }

        return true;
    }

    // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//    // 2016/01/20 S21_NA#966 Add Start-----------
//    /**
//     * registAttachComment
//     * @param bizMsg NWAL1500CMsg
//     * @param newCpoOrdNum String
//     */
//    public static void registAttachComment(NWAL1500CMsg bizMsg, String newCpoOrdNum) {
//        ZYPFileUpDownParameter params = new ZYPFileUpDownParameter();
//        params.setBusinessId(BIZ_ID);
//        params.setAttDataGrp(bizMsg.cpoOrdNum.getValue());
//        params.setAttDataCmnt(bizMsg.xxPopPrm_P2.getValue());
//        params.setBusinessNm(ATTACH_BUSINESS_NM);
//        params.setAttDataNm(ATTACH_DATA_ORD_NUM);
//        params.setAttDocTpCd(ATTACH_DOC_TP_ORDER);
//        ZYPFileUpDown.uploadNote(params);
//        params.setAttDataGrp(newCpoOrdNum);
//        ZYPFileUpDown.uploadNote(params);
//    }
//    // 2016/01/20 S21_NA#966 Add End-------------
    // 2017/05/08 S21_NA#Review structure Lv.2 Del End

    /**
     * Subtract
     * @param val1
     * @param val2
     * @return BigDecimal
     */
    private static BigDecimal subtract(BigDecimal val1, BigDecimal val2) {
        if (!ZYPCommonFunc.hasValue(val1)) {
            val1 = BigDecimal.ZERO;
        }
        if (!ZYPCommonFunc.hasValue(val2)) {
            val2 = BigDecimal.ZERO;
        }
        return val1.subtract(val2);
    }

    /**
     * Add
     * @param val1
     * @param val2
     * @return BigDecimal
     */
    private static BigDecimal add(BigDecimal val1, BigDecimal val2) {
        if (!ZYPCommonFunc.hasValue(val1)) {
            val1 = BigDecimal.ZERO;
        }
        if (!ZYPCommonFunc.hasValue(val2)) {
            val2 = BigDecimal.ZERO;
        }
        return val1.add(val2);
    }

    /**
     * Get Price Category Code List
     * @param bizMsg NWAL1500CMsg
     * @param isCallPrcListField is Called Price List Field
     * @return Price Category Code List
     */
    @SuppressWarnings("unchecked")
    public static List<String> getPrcCatgCdList(NWAL1500CMsg bizMsg, boolean isCallPrcListField) {

        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctLineIndex = bizMsg.xxCellIdx.getValueInt();
        String prcCatgNm = null;

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (isCallPrcListField) {
                prcCatgNm = bizMsg.B.no(slctLineIndex).prcCatgNm_LL.getValue();
            } else {
                prcCatgNm = bizMsg.B.no(slctLineIndex).flPrcListNm_LL.getValue();
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            if (isCallPrcListField) {
                prcCatgNm = bizMsg.D.no(slctLineIndex).prcCatgNm_RL.getValue();
            } else {
                prcCatgNm = bizMsg.D.no(slctLineIndex).flPrcListNm_RL.getValue();
            }
        } else {
            if (isCallPrcListField) {
                prcCatgNm = bizMsg.prcCatgNm.getValue();
            } else {
                prcCatgNm = bizMsg.flPrcListNm.getValue();
            }
        }

        S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getPrcCatgCdList(bizMsg, prcCatgNm);

        if (ssmResult.isCodeNotFound()) {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                if (isCallPrcListField) {
                    bizMsg.B.no(slctLineIndex).prcCatgNm_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_PRC_LIST });
                } else {
                    bizMsg.B.no(slctLineIndex).flPrcListNm_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_FLR_PRC_LIST });
                }
            } else if (TAB_RMA.equals(dplyTab)) {
                if (isCallPrcListField) {
                    bizMsg.D.no(slctLineIndex).prcCatgNm_RL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_PRC_LIST });
                } else {
                    bizMsg.D.no(slctLineIndex).flPrcListNm_RL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_FLR_PRC_LIST });
                }

            } else {
                if (isCallPrcListField) {
                    bizMsg.prcCatgNm.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_PRC_LIST });
                } else {
                    bizMsg.flPrcListNm.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_FLR_PRC_LIST });
                }
            }
            return null;
        }

        return (List<String>) ssmResult.getResultObject();
    }

    /**
     * Check Contain Price List
     * @param bizMsg NWAL1500CMsg
     * @param prcCatgCdList List<String>
     * @param prcApiPMsg NWZC157001PMsg
     * @param isCallPrcListField is Called Price List Field
     * @return Price Category Code List
     */
    public static String checkContainPrcList(NWAL1500CMsg bizMsg, List<String> prcCatgCdList, NWZC157001PMsg prcApiPMsg, boolean isCallPrcListField) {

        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctLineIndex = bizMsg.xxCellIdx.getValueInt();
        int containsCnt = 0;
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        for (int i = 0; i < prcApiPMsg.xxPrcList.getValidCount(); i++) {
            String prcCatgCd = prcApiPMsg.xxPrcList.no(i).prcCatgCd.getValue();
            if (prcCatgCdList.contains(prcCatgCd)) {
                containsCnt++;
                if (containsCnt > 1) {
                    break;
                }
            }
        }

        if (containsCnt == 0) {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                if (isCallPrcListField) {
                    bizMsg.B.no(slctLineIndex).prcCatgNm_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_PRC_LIST });
                } else {
                    bizMsg.B.no(slctLineIndex).flPrcListNm_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_FLR_PRC_LIST });
                }
            } else if (TAB_RMA.equals(dplyTab)) {
                if (isCallPrcListField) {
                    bizMsg.D.no(slctLineIndex).prcCatgNm_RL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_PRC_LIST });
                } else {
                    bizMsg.D.no(slctLineIndex).flPrcListNm_RL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_FLR_PRC_LIST });
                }

            } else {
                if (isCallPrcListField) {
                    // QC#10321 Mod Start
                    // bizMsg.prcCatgNm.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_PRC_LIST });
                    String inputPrcCatgNm = bizMsg.prcCatgNm.getValue();
                    bizMsg.prcCatgNm.clear();
                    bizMsg.prcCatgNm.setErrorInfo(1, NWAM0896E, new String[] {MSG_PARAM_PRC_LIST, MSG_PARAM_RSN, inputPrcCatgNm });
                    // QC#10321 Mod End
                } else {
                    // QC#10321 Mod Start
                    // bizMsg.flPrcListNm.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_FLR_PRC_LIST });
                    String inputFlPrcListNm = bizMsg.flPrcListNm.getValue();
                    bizMsg.flPrcListNm.clear();
                    bizMsg.flPrcListNm.setErrorInfo(1, NWAM0896E, new String[] {MSG_PARAM_FLR_PRC_LIST, MSG_PARAM_RSN, inputFlPrcListNm });
                    // QC#10321 Mod End
                }
            }
            return null;
        } else if (containsCnt > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        String prcCatgCd = null;
        for (int i = 0; i < prcApiPMsg.xxPrcList.getValidCount(); i++) {
            prcCatgCd = prcApiPMsg.xxPrcList.no(i).prcCatgCd.getValue();
            if (prcCatgCdList.contains(prcCatgCd)) {
                break;
            }
        }

        return getPrcCatgNm(bizMsg, prcCatgCd);
    }

    /**
     * Derive Line Price
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     * @param prcCatgNm String
     */
    public static void deriveAllLinePrice(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String prcCatgNm) {

        // 2017/06/14 S21_NA#18623 Add Start
        String curTab = bizMsg.xxDplyTab.getValue();
        bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
        // 2017/06/14 S21_NA#18623 Add End
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) { // 2018/01/29 S21_NA#19808 Mod
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).prcCatgNm_LL, prcCatgNm);
            // QC#19603 2017/06/29 Mod Start
            //int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg, i);
            //NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, i);
            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).dsCpoLineSubNum_LL)) {
                int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, i); // 2018/01/29 S21_NA#19808 Mod
                NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, i);
            }
            // QC#19603 2017/06/29 Mod End
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
        }

        // 2017/06/14 S21_NA#18623 Add Start
        bizMsg.xxDplyTab.setValue(TAB_RMA);
        // 2017/06/14 S21_NA#18623 Add End
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) { // 2018/01/29 S21_NA#19808 Mod
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).prcCatgNm_RL, prcCatgNm);
            // QC#19603 2017/06/29 Mod Start
            //int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg, i);
            //NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, i);
            if (!ZYPCommonFunc.hasValue(glblMsg.D.no(i).dsCpoLineSubNum_RL)) {
                int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, i); // 2018/01/29 S21_NA#19808 Mod
                NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, i);
            }
            // QC#19603 2017/06/29 Mod End
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
        }
        // 2017/06/14 S21_NA#18623 Add Start
        bizMsg.xxDplyTab.setValue(curTab);
        // 2017/06/14 S21_NA#18623 Add End
    }

    /**
     * Derive Line Price
     * @param bizMsg NWAL1500CMsg
     * @param prcCatgNm Price Category Name
     */
    public static void deriveAllLineFloorPrice(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String prcCatgNm) {

        // 2018/01/29 S21_NA#19808 glblMsg.B, D => glblMsg.B, D
        String curTab = bizMsg.xxDplyTab.getValue();
        bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).flPrcListNm_LL, prcCatgNm);
            // QC#22372 2018/01/10 Add Start
            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).dsCpoLineSubNum_LL)) {
                int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, i);  // 2018/01/29 S21_NA#19808 Mod
                NWAL1500CommonLogicForLineConfig.deriveLineFloorPrice(bizMsg, glblMsg, slctConfIndex, i);
            }
            // QC#22372 2018/01/10 Add End
        }

        bizMsg.xxDplyTab.setValue(TAB_RMA);
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).flPrcListNm_RL, prcCatgNm);
            // QC#22372 2018/01/10 Add Start
            if (!ZYPCommonFunc.hasValue(glblMsg.D.no(i).dsCpoLineSubNum_RL)) {
                int slctConfIndex = NWAL1500CommonLogicForLineConfig.getConfIndex(bizMsg.xxDplyTab.getValue(), glblMsg, i); // 2018/01/29 S21_NA#19808 Mod
                NWAL1500CommonLogicForLineConfig.deriveLineFloorPrice(bizMsg, glblMsg, slctConfIndex, i);
            }
            // QC#22372 2018/01/10 Add End
        }
        bizMsg.xxDplyTab.setValue(curTab);
    }

    /**
     * is "New Creation Mode" -  S21_NA#5000#84
     * @param bizMsg - NWAL1500CMsg
     * @return whether new creation mode or not.
     */
    public static boolean isNewCreationMode(NWAL1500CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.ordHdrStsDescTxt)) {

            // If the status is empty, the mode is "New Creation".
            bizMsg.cpoOrdNum.clear();
            return true;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {

            // The CPO# has not been entered yet.
            return true;
        }
        return false;
    }

    // 2016/03/25 S21_NA#4693 Add Start
    /**
     * <pre>
     * Get Drop Ship Avalable Flag from DB Table: DS_ORD_TP_PROC_DFN.
     * @param glblCmpyCd Global Company Code
     * @param dsOrdTpCd DS Order Type Code (it means Order Reason)
     * @return value of DS_ORD_TP_PROC_DFN.DROP_SHIP_AVAL_FLG
     * </pre>
     */
    public static String getDropShipAvalFlg(String glblCmpyCd, String dsOrdTpCd) {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            return ZYPConstant.FLG_OFF_N;
        }
        DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.dsOrdTpCd.setValue(dsOrdTpCd);

//        tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);
        return ZYPConstant.FLG_ON_Y;
//
//        if (null == tMsg) {
//            return ZYPConstant.FLG_OFF_N;
//        }
//        return tMsg.dropShipAvalFlg.getValue();
    }

    /**
     * <pre>
     * If the object's Drop Ship Flag == 'Y', reset Address information from Ship To Customer record.
     * @param bizMsg Business Message
     * </pre>
     */
    public static void resetDropShipAddress(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/29 S21_NA#19808

        Map<String, SHIP_TO_CUSTTMsg> cashShipToCust = new HashMap<String, SHIP_TO_CUSTTMsg>();

        // Header
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dropShipFlg.getValue())) {
            bizMsg.dropShipFlg.setValue(ZYPConstant.FLG_OFF_N);
            // 2017/12/07 S21_NA#21621 Add Start
            bizMsg.addShipToLocNm.clear();
            // 2017/12/07 S21_NA#21621 Add End
            String shipToCustCd = bizMsg.shipToCustCd.getValue();
            SHIP_TO_CUSTTMsg shipToCust = cashShipToCust.get(shipToCustCd);
            if (null == shipToCust && ZYPCommonFunc.hasValue(shipToCustCd)) {
                shipToCust = NWAL1500CommonLogicForCustomer.getShipToCustByCondition(bizMsg.glblCmpyCd.getValue(), shipToCustCd);
                if (null != shipToCust) {
                    cashShipToCust.put(shipToCustCd, shipToCust);
                }
            }
            if (null != shipToCust) {
                setShipToAddress(shipToCust, bizMsg);
            }
        }

        // 2018/01/29 S21_NA#19808 bizMsg.A, C => glblMsg.A, C
        // Config
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg configMsg = glblMsg.A.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(configMsg.dropShipFlg_LC.getValue())) {
                configMsg.dropShipFlg_LC.setValue(ZYPConstant.FLG_OFF_N);
                // 2017/12/08 S21_NA#21621 Add Start
                configMsg.addShipToLocNm_LC.clear();
                // 2017/12/08 S21_NA#21621 Add End
                String shipToCustCd = configMsg.shipToCustCd_LC.getValue();
                SHIP_TO_CUSTTMsg shipToCust = cashShipToCust.get(shipToCustCd);
                if (null == shipToCust && ZYPCommonFunc.hasValue(shipToCustCd)) {
                    shipToCust = NWAL1500CommonLogicForCustomer.getShipToCustByCondition(bizMsg.glblCmpyCd.getValue(), shipToCustCd);
                    if (null != shipToCust) {
                        cashShipToCust.put(shipToCustCd, shipToCust);
                    }
                }
                if (null != shipToCust) {
                    setShipToAddress(shipToCust, configMsg);
                }
            }
        }

        // RMA
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL1500_CSMsg configMsg = glblMsg.C.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(configMsg.dropShipFlg_RC.getValue())) {
                configMsg.dropShipFlg_RC.setValue(ZYPConstant.FLG_OFF_N);
                // 2017/12/08 S21_NA#21621 Add Start
                configMsg.addShipToLocNm_RC.clear();
                // 2017/12/08 S21_NA#21621 Add End
                String shipToCustCd = configMsg.shipToCustCd_RC.getValue();
                SHIP_TO_CUSTTMsg shipToCust = cashShipToCust.get(shipToCustCd);
                if (null == shipToCust && ZYPCommonFunc.hasValue(shipToCustCd)) {
                    shipToCust = NWAL1500CommonLogicForCustomer.getShipToCustByCondition(bizMsg.glblCmpyCd.getValue(), shipToCustCd);
                    if (null != shipToCust) {
                        cashShipToCust.put(shipToCustCd, shipToCust);
                    }
                }
                if (null != shipToCust) {
                    setShipToAddress(shipToCust, configMsg);
                }
            }
        }
    }

    private static void setShipToAddress(SHIP_TO_CUSTTMsg shipToCustTMsg, EZDMsg bizMsgClazz) { // 2018/01/29 S21_NA#19808

        if (bizMsgClazz instanceof NWAL1500CMsg) {
            NWAL1500CMsg bizMsg = (NWAL1500CMsg) bizMsgClazz;
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, shipToCustTMsg.locNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm, shipToCustTMsg.addlLocNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr, shipToCustTMsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr, shipToCustTMsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr, shipToCustTMsg.thirdLineAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr, shipToCustTMsg.frthLineAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstRefCmntTxt, shipToCustTMsg.firstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdRefCmntTxt, shipToCustTMsg.scdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd, shipToCustTMsg.postCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr, shipToCustTMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd, shipToCustTMsg.stCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm, shipToCustTMsg.provNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd, shipToCustTMsg.ctryCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm, getCntyNm(shipToCustTMsg.glblCmpyCd.getValue(), shipToCustTMsg.cntyPk.getValue()));
        } else if (bizMsgClazz instanceof NWAL1500_ASMsg) {
            NWAL1500_ASMsg bizMsg = (NWAL1500_ASMsg) bizMsgClazz;
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm_LC, shipToCustTMsg.locNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm_LC, shipToCustTMsg.addlLocNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_LC, shipToCustTMsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_LC, shipToCustTMsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr_LC, shipToCustTMsg.thirdLineAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr_LC, shipToCustTMsg.frthLineAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstRefCmntTxt_LC, shipToCustTMsg.firstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdRefCmntTxt_LC, shipToCustTMsg.scdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_LC, shipToCustTMsg.postCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_LC, shipToCustTMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_LC, shipToCustTMsg.stCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm_LC, shipToCustTMsg.provNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_LC, shipToCustTMsg.ctryCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_LC, getCntyNm(shipToCustTMsg.glblCmpyCd.getValue(), shipToCustTMsg.cntyPk.getValue()));
        } else if (bizMsgClazz instanceof NWAL1500_CSMsg) {
            NWAL1500_CSMsg bizMsg = (NWAL1500_CSMsg) bizMsgClazz;
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm_RC, shipToCustTMsg.locNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm_RC, shipToCustTMsg.addlLocNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_RC, shipToCustTMsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_RC, shipToCustTMsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr_RC, shipToCustTMsg.thirdLineAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr_RC, shipToCustTMsg.frthLineAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstRefCmntTxt_RC, shipToCustTMsg.firstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdRefCmntTxt_RC, shipToCustTMsg.scdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_RC, shipToCustTMsg.postCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_RC, shipToCustTMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_RC, shipToCustTMsg.stCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm_RC, shipToCustTMsg.provNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_RC, shipToCustTMsg.ctryCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_RC, getCntyNm(shipToCustTMsg.glblCmpyCd.getValue(), shipToCustTMsg.cntyPk.getValue()));
        }
    }

    private static String getCntyNm(String glblCmpyCd, BigDecimal cntyPk) {

        CNTYTMsg tMsg = new CNTYTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.cntyPk.setValue(cntyPk);

        tMsg = (CNTYTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (null != tMsg) {
            return tMsg.cntyNm.getValue();
        }
        return null;
    }
    // 2016/03/25 S21_NA#4693 Add End
    // 2016/04/11 S21_NA#3236-3 Add Start
    /**
     * <pre>
     * Check the line config line is to be canceled or canceled
     * @param bizMsg Business Message
     * @param lineMsg Line Message
     * @return true: To be canceled or canceled false: alive line
     * </pre>
     */
    public static boolean isCancelLine(NWAL1500CMsg bizMsg, EZDMsg ezdLineMsg) { // 2018/01/29 S21_NA#19808 Mod

        String toBeCancelledOrdLineSts = bizMsg.varCharConstVal_TB.getValue();
        String ordLineStsDescTxt = null;
        String ordLineStsCd = null;
        if (ezdLineMsg instanceof NWAL1500_BSMsg) {
            NWAL1500_BSMsg lineMsg = (NWAL1500_BSMsg) ezdLineMsg;
            ordLineStsDescTxt = lineMsg.ordLineStsDescTxt_LL.getValue();
            ordLineStsCd = lineMsg.ordLineStsCd_LL.getValue();
        } else if (ezdLineMsg instanceof NWAL1500_BCMsg) {
            NWAL1500_BCMsg lineMsg = (NWAL1500_BCMsg) ezdLineMsg;
            ordLineStsDescTxt = lineMsg.ordLineStsDescTxt_LL.getValue();
            ordLineStsCd = lineMsg.ordLineStsCd_LL.getValue();
        // 2018/06/25 S21_NA#25227 Add Start
        } else if (ezdLineMsg instanceof NWAL1500_DSMsg) {
            NWAL1500_DSMsg rmaLineMsg = (NWAL1500_DSMsg) ezdLineMsg;
            ordLineStsDescTxt = rmaLineMsg.rtrnLineStsDescTxt_RL.getValue();
            ordLineStsCd = rmaLineMsg.ordLineStsCd_RL.getValue();
        } else if (ezdLineMsg instanceof NWAL1500_DCMsg) {
            NWAL1500_DCMsg rmaLineMsg = (NWAL1500_DCMsg) ezdLineMsg;
            ordLineStsDescTxt = rmaLineMsg.rtrnLineStsDescTxt_RL.getValue();
            ordLineStsCd = rmaLineMsg.ordLineStsCd_RL.getValue();
        // 2018/06/25 S21_NA#25227 Add End
        }
        if (toBeCancelledOrdLineSts.equals(ordLineStsDescTxt)
                || ORD_LINE_STS.CANCELLED.equals(ordLineStsCd)) {
            return true;
        }
        return false;
    }

    // 2018/06/25 S21_NA#25227 Del Start
//    /**
//     * <pre>
//     * Check the rma Line config line is to be canceled or canceled.
//     * @param bizMsg Business Message
//     * @param rmaLineMsg RMA Line message
//     * @return true: To be canceled or canceled false: alive line
//     * </pre>
//     */
//    public static boolean isCancelLine(NWAL1500CMsg bizMsg, NWAL1500_DCMsg rmaLineMsg) {
//
//        String toBeCancelledOrdLineSts = bizMsg.varCharConstVal_TB.getValue();
//        String ordLineStsDescTxt = rmaLineMsg.rtrnLineStsDescTxt_RL.getValue();
//        String ordLineStsCd = rmaLineMsg.ordLineStsCd_RL.getValue();
//        if (toBeCancelledOrdLineSts.equals(ordLineStsDescTxt)
//                || RTRN_LINE_STS.CANCELLED.equals(ordLineStsCd)) {
//            return true;
//        }
//        return false;
//    }
    // 2018/06/25 S21_NA#25227 Del End
    // 2016/04/11 S21_NA#3236-3 Add End

    /**
     * @param bizMsg       Business Message
     * @param rmaConfigMsg NWAL1500_CCMsg
     */
    public static void setRmaConfigNewFlg(NWAL1500CMsg bizMsg, NWAL1500_CCMsg rmaConfigMsg) {
        boolean isActionNew = NWXC150001DsCheck.matchConfigTp(//
                bizMsg.glblCmpyCd.getValue(), rmaConfigMsg.configTpCd_RC.getValue(), CONFIG_CATG.INBOUND, true, false, false);
        if (!ZYPCommonFunc.hasValue(rmaConfigMsg.configTpCd_RC) || isActionNew) {
            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.configNewFlg_RC, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.configNewFlg_RC, ZYPConstant.FLG_OFF_N);
        }
    }

    // 2018/01/29 S21_NA#19808 Add Start
    /**
     * @param bizMsg NWAL1500CMsg
     * @param rmaConfigMsg NWAL1500_CSMsg
     */
    public static void setRmaConfigNewFlg(NWAL1500CMsg bizMsg, NWAL1500_CSMsg rmaConfigMsg) {
        // 2020/01/08 QC#54197-1 Add Start
        if (!ZYPCommonFunc.hasValue(rmaConfigMsg.configTpCd_RC)) {
            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.configTpCd_RC, bizMsg.configTpCd_RD.no(0));
        }
        // 2020/01/08 QC#54197-1 Add End
        boolean isActionNew = NWXC150001DsCheck.matchConfigTp(//
                bizMsg.glblCmpyCd.getValue(), rmaConfigMsg.configTpCd_RC.getValue(), CONFIG_CATG.INBOUND, true, false, false);
        if (!ZYPCommonFunc.hasValue(rmaConfigMsg.configTpCd_RC) || isActionNew) {
            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.configNewFlg_RC, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.configNewFlg_RC, ZYPConstant.FLG_OFF_N);
        }
    }
    // 2018/01/29 S21_NA#19808 Add end

    /**
     * @param bizMsg    Business Message
     * @param configMsg NWAL1500_ACMsg
     */
    public static void setConfigNewFlg(NWAL1500CMsg bizMsg, NWAL1500_ACMsg configMsg) {
        boolean isActionNew = NWXC150001DsCheck.matchConfigTp(//
                bizMsg.glblCmpyCd.getValue(), configMsg.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, true, false, false);
        if (!ZYPCommonFunc.hasValue(configMsg.configTpCd_LC) || isActionNew) {
            ZYPEZDItemValueSetter.setValue(configMsg.configNewFlg_LC, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(configMsg.configNewFlg_LC, ZYPConstant.FLG_OFF_N);
        }
    }

    // 2018/01/29 S21_NA#19808 Add Start
    /**
     * @param bizMsg    NWAL1500CMsg
     * @param configMsg NWAL1500_ASMsg
     */
    public static void setConfigNewFlg(NWAL1500CMsg bizMsg, NWAL1500_ASMsg configMsg) {
        // 2020/01/08 QC#54197-1 Add Start
        if (!ZYPCommonFunc.hasValue(configMsg.configTpCd_LC)) {
            ZYPEZDItemValueSetter.setValue(configMsg.configTpCd_LC, bizMsg.configTpCd_LD.no(0));
        }
        // 2020/01/08 QC#54197-1 Add End
        boolean isActionNew = NWXC150001DsCheck.matchConfigTp(//
                bizMsg.glblCmpyCd.getValue(), configMsg.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, true, false, false);
        if (!ZYPCommonFunc.hasValue(configMsg.configTpCd_LC) || isActionNew) {
            ZYPEZDItemValueSetter.setValue(configMsg.configNewFlg_LC, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(configMsg.configNewFlg_LC, ZYPConstant.FLG_OFF_N);
        }
    }
    // 2018/01/29 S21_NA#19808 Add End

    // For Performance QC#8166 Add Start
    /**
     * Get Varchar Const Value
     * @param bizMsg NWAL1500CMsg
     * @param varCharConstNm Varchar Const Name
     * @return Varchar Const Value
     */
    public static String getVarCharConstVal(NWAL1500CMsg bizMsg, String varCharConstNm) {

        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(varCharConstNm, bizMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(varCharConstVal)) {
            return varCharConstVal;
        }

        if (ORD_LINE_STS_TO_BE_CANCELLED.equals(varCharConstNm)) {
            return TO_BE_CANCELLED;
        } else if (NWAL1500_ORD_LINE_CANCELLED.equals(varCharConstNm)) {
            return LINE_STS_NM_CANCELLED;
        } else if (PKG_UOM_FOR_PRC.equals(varCharConstNm)) {
            return PKG_UOM.EACH;
        }  else if (NOT_ALLOC_WH_CD.equals(varCharConstNm)) {
            return "";
        }  else if (LEASE_BYOT_MDSE_CD.equals(varCharConstNm)) {
            return "";
        }

        return "";
    }
    // For Performance QC#8166 Add End

    // For Performance QC#10321 Add Start
    /**
     * Init Process For Search
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     */
    public static void initProcessForSearch(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(bizMsg.D);
        ZYPTableUtil.clear(bizMsg.F);
        ZYPTableUtil.clear(bizMsg.G);
        ZYPTableUtil.clear(bizMsg.H);
        ZYPTableUtil.clear(glblMsg.I);
        // 2018/01/29 S21_NA#19808 Del Start
//        ZYPTableUtil.clear(bizMsg.J);
//        ZYPTableUtil.clear(bizMsg.K);
        // 2018/01/29 S21_NA#19808 Del End
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_CA, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg_PR, ZYPConstant.FLG_OFF_N);

        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);
        ZYPTableUtil.clear(glblMsg.D);
        ZYPTableUtil.clear(glblMsg.F);
        ZYPTableUtil.clear(glblMsg.G);
        ZYPTableUtil.clear(glblMsg.H);
        ZYPTableUtil.clear(glblMsg.I);
        // 2018/01/29 S21_NA#19808 Add Start
        ZYPTableUtil.clear(glblMsg.J);
        ZYPTableUtil.clear(glblMsg.K);
        // 2018/01/29 S21_NA#19808 Add End
        ZYPTableUtil.clear(glblMsg.T);
        ZYPTableUtil.clear(glblMsg.U);
        ZYPTableUtil.clear(glblMsg.V);
        ZYPTableUtil.clear(glblMsg.W);
        ZYPTableUtil.clear(glblMsg.X);
        glblMsg.clear(); // S21_NA#16655 ADD START
    }
    // For Performance QC#10321 Add End

    // For Performance QC#11613 Add Start
    /**
     * Get CCY Code From Cache
     * @param bizMsg NWAL1500CMsg
     * @param prcCatgNm Price Category Name
     * @param ccyCdMap Cache Map
     * @return CCY Code From Cache
     */
    @SuppressWarnings("unchecked")
    private static String getCcyCdFromCache(NWAL1500CMsg bizMsg, String prcCatgNm, Map<String, String> ccyCdMap) {

        if (ccyCdMap.containsKey(prcCatgNm)) {
            return ccyCdMap.get(prcCatgNm);
        }

        S21SsmEZDResult ssmRslt = NWAL1500QueryForSaveSubmit.getInstance().getCcyCdByPriceCatgNm(bizMsg, prcCatgNm);
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
            String ccyCd = rsltMapList.get(0).get("CCY_CD");
            ccyCdMap.put(prcCatgNm, ccyCd);
            return ccyCd;
        }

        return null;
    }
    // For Performance QC#11613 Add End

    // S21_NA#8388 ADD START
    /**
     * <pre>
     * convert check box value
     * @param chkBoxVal check box value
     * @return check box flag (1 or 0)
     * </pre>
     */
    public static String convDclnSvcCdFromChkBox(String chkBoxVal) {

        if (ZYPConstant.CHKBOX_ON_Y.equals(chkBoxVal)) {
            return ZYPConstant.FLG_ON_1;
        }
        return ZYPConstant.FLG_OFF_0;
    }

    /**
     * <pre>
     * get decline service check box value
     * @param bizMsg bus message
     * @return decline service check box value
     * </pre>
     */
    public static String getDclnSvcChkBoxValHdrFromConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/29 S21_NA#19808

        // 2018/01/29 S21_NA#19808 bizMsg.A, b => glblMsg.A, B
        NWAL1500CommonLogic.clearMeaninglesDetail(bizMsg, glblMsg);
        if (glblMsg.A.getValidCount() == 0) {
            // Keep value
            return bizMsg.dclnSvcCd.getValue();
        }
        int dclnOffCnt = 0;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg configLineMsg = glblMsg.A.no(i);
            // Is all cancel line in the config?
            boolean allCancelLine = true;
            for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(j);
                if (lineMsg.dsOrdPosnNum_LL.getValue().equals(configLineMsg.dsOrdPosnNum_LC.getValue())) {
                    if (!isCancelLine(bizMsg, lineMsg)) {
                        allCancelLine = false;
                        break;
                    }
                }
            }
            if (allCancelLine) {
                // all cancel line in the config
                continue;
            }
            if (!ZYPConstant.CHKBOX_ON_Y.equals(configLineMsg.dclnSvcCd_LC.getValue())) {
                // Check Box:OFF
                dclnOffCnt++;
            }
        }
        if (dclnOffCnt == 0) {
            // Check Boxes are ONs in all Config.
            return ZYPConstant.CHKBOX_ON_Y;
        }
        return null;
    }
    // S21_NA#8388 ADD END

    // 2016/09/20 S21_NA#8279 Add Start
    /**
     * <pre>
     * Clear delete action status.
     * @param bizMsg Business Message
     * </pre>
     */
    public static void clearDeleteActionStatus(NWAL1500CMsg bizMsg) {

        String screenAplID = bizMsg.getScreenAplID();

        boolean isDeleteAction = false;
        if ("NWAL1500Scrn00_OnChange_OrderEntryAction".equals(screenAplID)) {
            String ordEntryAct = bizMsg.ordEntryActCd.getValue();
            if (ORD_ENTRY_ACT.DELETE.equals(ordEntryAct)) {
                isDeleteAction = true;
            }
        }
        if (!isDeleteAction) {
            bizMsg.xxYesNoCd_CA.setValue(ZYPConstant.FLG_OFF_N);
        }
    }
    // 2016/09/20 S21_NA#8279 Add End
    // 2016/11/11 S21_NA#9864-2 Add Start
    /**
     * <pre>
     * Check the order is Loan Order or not
     * @param bizMsg Business Logic
     * @return true: Loan Order (ORD_CATG_CTX_TP = LOAN_ORDER_VALUE_SET), false: not Loan Order
     * <pre>
     */
    public static boolean isLoanOrder(NWAL1500CMsg bizMsg) {

        String rslt = NWAL1500Query.getInstance().getOrdCatgBizCtx(bizMsg.glblCmpyCd.getValue() //
                , ORD_CATG_CTX_TP.LOAN_ORDER_VALUE_SET //
                , bizMsg.dsOrdCatgCd.getValue() //
                , bizMsg.dsOrdTpCd.getValue());
        boolean isLoanOrder = false;
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rslt)) {
            isLoanOrder = true;
        }
        return isLoanOrder;
    }
    // 2016/11/11 S21_NA#9864-2 Add End

    // 2017/02/24 S21_NA#17714 Add Start
    /**
     * <pre>
     * If order is not Retail Equipment Order, this method sets Base Component Flag 'N' for Line config Detail and RMA Detail.
     * @param bizMsg Business Message
     * @param isOrderRetailEquipment true Value: Retail Equipment Order, false: not Retail Equipment Order.
     *    If this parameter is NULL, this method check the this order is Retail Equipment order or not 
     *    using Order Category and Order Reason
     * @return true: set base componet flag all 'N' false: do nothing.
     * </pre>
     */
    public static boolean setAllNForBaseCompFLg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, Boolean isOrderRetailEquipment) { // 2018/01/29 S21_NA#19808 Mod

        if (isOrderRetailEquipment == null) {
            isOrderRetailEquipment = new Boolean(isOrderRetailEquipment(bizMsg));
        }
        boolean resetFlg = false;
        if (!isOrderRetailEquipment.booleanValue()) {
            resetFlg = true;
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                glblMsg.B.no(i).baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
            }
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                glblMsg.D.no(i).baseCmptFlg_RL.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
        return resetFlg;
    }

    /**
     * <pre>
     * Confirmation: Order Category and Order reason are related to Retail Equipment Order or not.
     * @param bizMsg Business Message
     * @return True: related to Retail Equipment Order, False: Not related to Retail Equipment Order
     * </pre>
     */
    public static boolean isOrderRetailEquipment(NWAL1500CMsg bizMsg) {

        return NWAL1500QueryForLineConfig.getInstance().isExistOrdCatg(bizMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS, false);
    }
    // 2017/02/24 S21_NA#17714 Add End

    // 2017/03/02 S21_NA#17714-2 Add Start
    /**
     * <pre>
     * Clear Model information if the order is not Retail Equipment Order
     * @param bizMsg
     * </pre>
     */
    public static void setModelNameAfterDeriveCategoryOrReason(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/29 S21_NA#19808 Mod

        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd) //
                || !ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            return;
        }

        if (NWAL1500CommonLogicForLineConfig.isExistOrdCatg(bizMsg, false)) {
            for (int slctConfIndex = 0; slctConfIndex < glblMsg.A.getValidCount(); slctConfIndex++) {
                NWAL1500_ASMsg configMsg = glblMsg.A.no(slctConfIndex);  // 2018/01/29 S21_NA#19808 Mod
                if (!ZYPCommonFunc.hasValue(configMsg.mdlNm_LC) // 2018/01/29 S21_NA#19808 Mod
                        || !ZYPCommonFunc.hasValue(configMsg.mdlId_LC)) { // 2018/01/29 S21_NA#19808 Mod
                    String bkDplyTab = bizMsg.xxDplyTab.getValue();
                    bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
                    setBaseComponentFlag(bizMsg, glblMsg, configMsg); // 2018/01/29 S21_NA#19808 Mod
                    NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, configMsg); // 2018/01/29 S21_NA#19808 Mod
                    bizMsg.xxDplyTab.setValue(bkDplyTab);
                }
            }
        } else {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                bizMsg.A.no(i).mdlNm_LC.clear();
                bizMsg.A.no(i).mdlId_LC.clear();
            }
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                glblMsg.A.no(i).mdlNm_LC.clear();
                glblMsg.A.no(i).mdlId_LC.clear();
            }
        }
    }
    // 2017/03/02 S21_NA#17714-2 Add End

    // 2017/03/09 S21_NA#16855 Add Start
    /**
     * <pre>
     * Get Territory Group Type Code From DS Order Type Code
     * @param bizMsg buz message
     * @return territory group type code
     * </pre>
     */
    // 2017/12/12 S21_NA#19804(Sol#349) Mod Start
//    public static String getTrtyGrpTpCdFromDsOrdTpCd(NWAL1500CMsg bizMsg) {
    public static String getTrtyGrpTpTxtFromDsOrdTpCd(NWAL1500CMsg bizMsg) {
    // 2017/12/12 S21_NA#19804(Sol#349) Mod Start

        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            return null;
        }

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);

        dsOrdTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(dsOrdTpProcDfnTMsg);
        if (dsOrdTpProcDfnTMsg != null) {
            // 2017/12/12 S21_NA#19804(Sol#349) Mod Start
//            return dsOrdTpProcDfnTMsg.trtyGrpTpCd.getValue();
            return dsOrdTpProcDfnTMsg.trtyGrpTpTxt.getValue();
            // 2017/12/12 S21_NA#19804(Sol#349) Mod End
        }

        return null;
    }
    // 2017/03/09 S21_NA#16855 Add End

    // 2017/06/16 S21_NA#19005 Add Start
    private static CPO_DTLTMsg getCurDbDtlData(NWAL1500_BSMsg lineMsg, CPO_DTLTMsgArray cpoDtlTMsgArray) { // 2018/06/21 S21_NA#25151 Mod I/F

        // 2018/06/21 S21_NA#25151 Mod I/F, update without any comments.
        for (int i = 0; i < cpoDtlTMsgArray.getValidCount(); i++) {
            CPO_DTLTMsg cpoDtlTMsg = cpoDtlTMsgArray.no(i);
            if (S21StringUtil.isEquals(lineMsg.cpoDtlLineNum_LL.getValue(), cpoDtlTMsg.cpoDtlLineNum.getValue()) //
                    && S21StringUtil.isEquals(lineMsg.cpoDtlLineSubNum_LL.getValue(), cpoDtlTMsg.cpoDtlLineSubNum.getValue())) {
                return cpoDtlTMsg;
            }
        }
        return null;
    }

    private static boolean isModifiledLine(NWAL1500_BSMsg lineMsg, CPO_DTLTMsg cpoDtlTMsg) { // 2018/06/21 S21_NA#25151 Mod I/F Without comments.

        // 2018/06/21 S21_NA#25151 Mod Start
//        if (S21StringUtil.isEquals(lineMsg.updTsDplyTxt_LL.getValue(), cpoDtlVTMsg.updTsDplyTxt.getValue()) //
//                && S21StringUtil.isEquals(lineMsg.updUsrNm_LL.getValue(), cpoDtlVTMsg.updUsrNm.getValue())) {
        if (isSameUpdTsTxtAndUpdTs(lineMsg.updTsDplyTxt_LL.getValue(), cpoDtlTMsg.dsCpoDtlUpdTs.getValue()) //
                && isSameUpdateUser(lineMsg.dsCpoDtlUpdUsrId_LL.getValue(), cpoDtlTMsg.dsCpoDtlUpdUsrId.getValue())) { // 2018/08/06 S21_NA#27642  Mod
            // 2018/06/21 S21_NA#25151 Mod End
            return false;
        } else {
            return true;
        }
    }
    // 2017/06/16 S21_NA#19005 Add End

    // 2017/10/04 S21_NA#21300 Add Start
    /**
     * <pre>
     * get ITT Available Cancel PO Status from Varchar Const
     * @param glblCmpyCd Global Company Code
     * @return ITT Available Cancel PO Status
     * </pre>
     */
    public static List<String> getAvalCancStsOfItt(String glblCmpyCd) {

        String avalCancStsOfIttStr = ZYPCodeDataUtil.getVarCharConstValue(NWAL1500Constant.NWAL1500_ITT_AVAL_CANC_PO_STS, glblCmpyCd);
        if (avalCancStsOfIttStr == null) {
            avalCancStsOfIttStr = "01,02,07";
        }

        String[] avalCancStsOfIttArray = avalCancStsOfIttStr.split(",");

        List<String> avalCancStsOfIttList = new ArrayList<String>(0);

        for (String avalCancStsOfIttSts : avalCancStsOfIttArray) {
            avalCancStsOfIttList.add(avalCancStsOfIttSts);
        }
        return avalCancStsOfIttList;
    }

    /**
     * <pre>
     * get ITT Available Cancel Order Line Source Code from Varchar Const
     * @param glblCmpyCd Global Company Code
     * @return ITT Available Cancel Order Line Source Code
     * </pre>
     */
    public static List<String> getAvalCancOrdLineSrcCdOfItt(String glblCmpyCd) {

        String avalCancOrdLineSrcOfIttStr = ZYPCodeDataUtil.getVarCharConstValue(NWAL1500Constant.NWAL1500_ITT_AVAL_CANC_LN_SRC, glblCmpyCd);
        if (avalCancOrdLineSrcOfIttStr == null) {
            avalCancOrdLineSrcOfIttStr = "40";
        }

        String[] avalCancOrdLineSrcOfIttArray = avalCancOrdLineSrcOfIttStr.split(",");

        List<String> avalCancOrdLineSrcOfIttList = new ArrayList<String>(0);

        for (String avalCancOrdLineSrcOfItt : avalCancOrdLineSrcOfIttArray) {
            avalCancOrdLineSrcOfIttList.add(avalCancOrdLineSrcOfItt);
        }
        return avalCancOrdLineSrcOfIttList;
    }
    // 2017/10/04 S21_NA#21300 Add End

    // Add Start 2017/10/05 QC#21356
    public static String getOrdTakeMdseCd(String glblCmpyCd, String mdseCd) {

        String subStrMdseCd = null;
        // 2018/06/21 S21_NA#25151 Del Start
//        if (null != mdseCd && mdseCd.length() > 8) {
//            subStrMdseCd = mdseCd.substring(0, 8);
//        } else {
//            subStrMdseCd = mdseCd;
//        }
//        ORD_TAKE_MDSETMsg ordTakeMdseMsg = new ORD_TAKE_MDSETMsg();
//        ordTakeMdseMsg.setSQLID("002");
//        ordTakeMdseMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        ordTakeMdseMsg.setConditionValue("ordTakeMdseCd01", subStrMdseCd);
//
//        ORD_TAKE_MDSETMsgArray ordTakeMdseMsgArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(ordTakeMdseMsg);
//
//        String ordTakeMdseCd = mdseCd;
//        if (ordTakeMdseMsgArray != null && ordTakeMdseMsgArray.getValidCount() > 0) {
//            ordTakeMdseCd = ordTakeMdseMsgArray.no(0).ordTakeMdseCd.getValue();
//        }
        // 2018/06/21 S21_NA#25151 Del End
        // 2018/06/21 S21_NA#25151 Add Start
        ORD_TAKE_MDSETMsg ordTakeMdseMsg = new ORD_TAKE_MDSETMsg();
        int ordTakeLength = ordTakeMdseMsg.getAttr("ordTakeMdseCd").getDigit();
        if (mdseCd != null && mdseCd.length() > ordTakeLength) {
            subStrMdseCd = mdseCd.substring(0, ordTakeLength);
        } else {
            subStrMdseCd = mdseCd;
        }
        ZYPEZDItemValueSetter.setValue(ordTakeMdseMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeMdseMsg.ordTakeMdseCd, subStrMdseCd);

        ORD_TAKE_MDSETMsg rsltOrdTakeMdseMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseMsg);
        String ordTakeMdseCd = mdseCd;
        if (rsltOrdTakeMdseMsg != null) {
            ordTakeMdseCd = rsltOrdTakeMdseMsg.ordTakeMdseCd.getValue();
        }
        // 2018/06/21 S21_NA#25151 Add End

        return ordTakeMdseCd;
    }
    // Add End 2017/10/05 QC#21356

    // 2017/10/13 S21_NA#21267 Add Start
    /**
     * <pre>
     * Check this order is Credit or not
     * </pre>
     * @param bizMsg NWAL1500CMsg
     * @return true: Credit Order, false: no Credit Order
     */
    public static boolean isOrderCredit(NWAL1500SMsg glblMsg) { // 2018/01/29 S21_NA#19808

        boolean hasCredit = false;
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            if (CR_REBIL.CREDIT.equals(lineMsg.crRebilCd_LL.getValue())) {
                hasCredit = true;
                break;
            }
        }
        return hasCredit;
    }

    /**
     * <pre>
     * If lines are created just only click "TAB", this method will clear these lines.
     * </pre>
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    public static void clearMeaninglesDetail(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/29 S21_NA#19808 Mod

        clearMeaninglesDetailOfLineConfig(bizMsg, glblMsg);
        clearMeaninglesDetailOfRma(bizMsg, glblMsg);

        return;
    }
    // 2017/10/13 S21_NA#21267 Add End
    // 2018/01/29 S21_NA#19808 Add Start
    /**
     * <pre>
     * If lines are created just only click "TAB", this method will clear these lines.
     * This method clear Line Config Tables.
     * </pre>
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    public static void clearMeaninglesDetailOfLineConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        List<EZDCItem> bizItemList = new ArrayList<EZDCItem>();

        if (bizMsg.A.getValidCount() == 1 && bizMsg.B.getValidCount() == 1) {
            bizItemList.add(bizMsg.A.no(0).svcConfigMstrPk_LC);
            bizItemList.add(bizMsg.B.no(0).mdseCd_LL);

            boolean isAllEmpty = true;
            for (EZDCItem item : bizItemList) {
                if (ZYPCommonFunc.hasValue(item)) {
                    isAllEmpty = false;
                }
            }
            if (isAllEmpty) {
                bizMsg.A.clear();
                bizMsg.A.setValidCount(0);
                bizMsg.B.clear();
                bizMsg.B.setValidCount(0);

                bizMsg.G.clear();
                bizMsg.G.setValidCount(0);
            }
        }

        List<EZDSItem> itemList = new ArrayList<EZDSItem>();
        if (glblMsg.A.getValidCount() == 1 && glblMsg.B.getValidCount() == 1) {
            itemList.add(glblMsg.A.no(0).svcConfigMstrPk_LC);
            itemList.add(glblMsg.B.no(0).mdseCd_LL);

            boolean isAllEmpty = true;
            for (EZDSItem item : itemList) {
                if (ZYPCommonFunc.hasValue(item)) {
                    isAllEmpty = false;
                }
            }
            if (isAllEmpty) {
                glblMsg.A.clear();
                glblMsg.A.setValidCount(0);
                glblMsg.B.clear();
                glblMsg.B.setValidCount(0);

                glblMsg.G.clear();
                glblMsg.G.setValidCount(0);
            }
        }
        return;
    }

    /**
     * <pre>
     * If lines are created just only click "TAB", this method will clear these lines.
     * This method clear RMA Tables.
     * </pre>
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    public static void clearMeaninglesDetailOfRma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/29 S21_NA#19808 Mod

        // 2018/01/29 S21_NA#19808 Mod, bizMsg => glblMsg
        List<EZDCItem> bizItemList = new ArrayList<EZDCItem>();

        if (bizMsg.C.getValidCount() == 1 && bizMsg.D.getValidCount() == 1) {
            bizItemList.add(bizMsg.C.no(0).svcConfigMstrPk_RC);
            bizItemList.add(bizMsg.D.no(0).mdseCd_RL);

            boolean isAllEmpty = true;
            for (EZDCItem item : bizItemList) {
                if (ZYPCommonFunc.hasValue(item)) {
                    isAllEmpty = false;
                }
            }
            if (isAllEmpty) {
                bizMsg.C.clear();
                bizMsg.C.setValidCount(0);
                bizMsg.D.clear();
                bizMsg.D.setValidCount(0);

                bizMsg.H.clear();
                bizMsg.H.setValidCount(0);
            }
        }

        List<EZDSItem> itemList = new ArrayList<EZDSItem>();
        if (glblMsg.C.getValidCount() == 1 && glblMsg.D.getValidCount() == 1) {
            itemList.add(glblMsg.C.no(0).svcConfigMstrPk_RC);
            itemList.add(glblMsg.D.no(0).mdseCd_RL);

            boolean isAllEmpty = true;
            for (EZDSItem item : itemList) {
                if (ZYPCommonFunc.hasValue(item)) {
                    isAllEmpty = false;
                }
            }
            if (isAllEmpty) {
                glblMsg.C.clear();
                glblMsg.C.setValidCount(0);
                glblMsg.D.clear();
                glblMsg.D.setValidCount(0);

                glblMsg.H.clear();
                glblMsg.H.setValidCount(0);
            }
        }
        return;
    }
    // 2018/01/29 S21_NA#19808 Add End
    // QC#22031 2017/10/31 Add Start
    private static boolean isVendorCode(String glblCmpyCd, String vndCd) {
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
    // QC#22031 2017/10/31 Add End

    // 2017/11/02 S21_NA#20146 Add Start
    public static  List<Map<String, Object>> getSalesRepList(String glblCmpyCd, String slsRepTocCd) {
        if (!ZYPCommonFunc.hasValue(slsRepTocCd)) {
            return null;
        }
        S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getSalesRepList(glblCmpyCd, slsRepTocCd);
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    public static void clearGLSegment(NWAL1500CMsg bizMsg) {
        bizMsg.psnNum_GS.clear();
        bizMsg.tocNm_GS.clear();
        bizMsg.coaExtnCd_GS.clear();
        bizMsg.coaExtnDescTxt_GS.clear();
        bizMsg.coaBrCd_GS.clear();
        bizMsg.coaBrDescTxt_GS.clear();
        bizMsg.coaCcCd_GS.clear();
        bizMsg.coaCcDescTxt_GS.clear();
    }

    public static void setGLSegment(NWAL1500CMsg bizMsg, Map<String, Object> dummyRepList) {
        ZYPEZDItemValueSetter.setValue(bizMsg.psnNum_GS, (String) dummyRepList.get("PSN_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.tocNm_GS, (String) dummyRepList.get("TOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_GS, (String) dummyRepList.get("COA_EXTN_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnDescTxt_GS, (String) dummyRepList.get("COA_EXTN_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_GS, (String) dummyRepList.get("COA_BR_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDescTxt_GS, (String) dummyRepList.get("COA_BR_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaCcCd_GS, (String) dummyRepList.get("COA_CC_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaCcDescTxt_GS, (String) dummyRepList.get("COA_CC_DESC_TXT"));
    }
    // 2017/11/02 S21_NA#20146 Add End
    // QC#22371 2017/12/26 Add Start
    public static boolean isCSMPExcl(NWAL1500CMsg bizMsg, String rtlSwhCd) {
        if (!ZYPCommonFunc.hasValue(rtlSwhCd)) {
            return false;
        }
        PRC_CALC_EXCL_SWHTMsg inMsg = new PRC_CALC_EXCL_SWHTMsg();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.rtlSwhCd.setValue(rtlSwhCd);
        PRC_CALC_EXCL_SWHTMsg outMsg = (PRC_CALC_EXCL_SWHTMsg) S21CacheTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(outMsg.csmpExclFlg.getValue())) {
            return true;
        }
        return false;
    }
    // QC#22371 2017/12/26 Add End

    // 2018/01/26 S21_NA#23140 Add Start
    public static void cmbnAddrForConf(NWAL1500_JSMsg msg, String connecStr){ // 
        if (ZYPConstant.FLG_ON_Y.equals(msg.dropShipFlg_JC.getValue()) //
                && ZYPCommonFunc.hasValue(msg.shipToAddlLocNm_JC)) {
            StringBuilder sb = new StringBuilder("");
            sb.append(msg.shipToAddlLocNm_JC.getValue());
            sb.append(connecStr);
            sb.append(msg.shipToCustLocAddr_JC.getValue());

            ZYPEZDItemValueSetter.setValue(msg.shipToCustLocAddr_JC, sb.toString());
        }
    }
    // 2018/01/26 S21_NA#23140 Add End

    // 2018/03/12 S21_NA#20297(Sol#379) Add Start
    public static boolean deriveDefaultShippingInfo(NWAL1500CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd)
                && ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)
                && ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {

            NMZC610001PMsg custInfoGetApiMsg = new NMZC610001PMsg();
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_SHIPPING_DEFAULT_INFORMATION);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.slsDt, bizMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.shipToCustCd, bizMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsAcctNum_I1, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);

            new NMZC610001().execute(custInfoGetApiMsg, ONBATCH_TYPE.ONLINE);

            if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {

                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);

                    if (msgId.endsWith("E")) {
                        return false;
                    }
                }
            }

            NMZC610001_ShippingDefaultInfoListPMsg shpgDefInfo = custInfoGetApiMsg.ShippingDefaultInfoList.no(0);
            if (!ZYPCommonFunc.hasValue(shpgDefInfo.frtCondCd)
                    && !ZYPCommonFunc.hasValue(shpgDefInfo.shpgSvcLvlCd)) {

                DS_ORD_TP_PROC_DFNTMsg ordTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
                ZYPEZDItemValueSetter.setValue(ordTpProcDfnTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(ordTpProcDfnTMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);

                ordTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) EZDTBLAccessor.findByKey(ordTpProcDfnTMsg);
                if (ordTpProcDfnTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(shpgDefInfo.frtCondCd, ordTpProcDfnTMsg.frtCondCd);
                    ZYPEZDItemValueSetter.setValue(shpgDefInfo.shpgSvcLvlCd, ordTpProcDfnTMsg.defShpgSvcLvlCd);
                }
            }

            if (ZYPCommonFunc.hasValue(shpgDefInfo.frtCondCd)) {
                FRT_CONDTMsg frtCondTMsg = new FRT_CONDTMsg();
                ZYPEZDItemValueSetter.setValue(frtCondTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(frtCondTMsg.frtCondCd, shpgDefInfo.frtCondCd);
                frtCondTMsg = (FRT_CONDTMsg) S21FastTBLAccessor.findByKey(frtCondTMsg);

                if (frtCondTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, frtCondTMsg.frtCondCd);
                    ZYPEZDItemValueSetter.setValue(bizMsg.frtCondDescTxt, frtCondTMsg.frtCondDescTxt);
                }
            }

            if (ZYPCommonFunc.hasValue(shpgDefInfo.shpgSvcLvlCd)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd, shpgDefInfo.shpgSvcLvlCd);
                NWAL1500CommonLogicForCategory.setShpgSvcLvlPullDown(bizMsg);
            }
        }

        return true;
    }

    public static String getDefaultShippingComment(NWAL1500CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd)
                && ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)
                && ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {

            NMZC610001PMsg custInfoGetApiMsg = new NMZC610001PMsg();
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_INSTRUCTION);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.lineBizTpCd, bizMsg.lineBizTpCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsBizAreaCd, getBizAreaCd(bizMsg));
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsCustMsgTpCd, DS_CUST_MSG_TP.SHIP);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.billToCustCd, bizMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.shipToCustCd, bizMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsAcctNum_I1, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.slsDt, bizMsg.slsDt);

            new NMZC610001().execute(custInfoGetApiMsg, ONBATCH_TYPE.ONLINE);

            if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {

                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);

                    if (msgId.endsWith("E")) {
                        return null;
                    }
                }
            }

            StringBuilder defShpgCmnt = new StringBuilder();
            for (int i = 0; i < custInfoGetApiMsg.InstructionList.getValidCount(); i++) {
                if (defShpgCmnt.length() > 0) {
                    defShpgCmnt.append(System.getProperty("line.separator"));
                }
                defShpgCmnt.append(custInfoGetApiMsg.InstructionList.no(i).dsCustMsgTxt.getValue());
            }

            if (defShpgCmnt.length() > SHPG_CMT_TXT_LIMIT_SIZE) {
                return defShpgCmnt.substring(0, SHPG_CMT_TXT_LIMIT_SIZE);
            } else {
                return defShpgCmnt.toString();
            }
        }

        return null;
    }

    private static String getBizAreaCd(NWAL1500CMsg bizMsg) {

        ORD_CATG_BIZ_CTXTMsg ordCatgBizCtxTMsg = new ORD_CATG_BIZ_CTXTMsg();
        ordCatgBizCtxTMsg.setSQLID("002");
        ordCatgBizCtxTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        ordCatgBizCtxTMsg.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        ordCatgBizCtxTMsg.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        ordCatgBizCtxTMsg.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());

        ORD_CATG_BIZ_CTXTMsgArray ordCatgBizCtxTMsgArray = (ORD_CATG_BIZ_CTXTMsgArray) S21ApiTBLAccessor.findByCondition(ordCatgBizCtxTMsg);

        if (ordCatgBizCtxTMsgArray == null || ordCatgBizCtxTMsgArray.getValidCount() == 0) {
            return null;
        }

        return ordCatgBizCtxTMsgArray.no(0).scdBizCtxAttrbTxt.getValue();
    }
    // 2018/03/12 S21_NA#20297(Sol#379) Add End

    // 2018/03/16 S21_NA#24459 Add Start
    public static void setForceDummyWh(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg){
        if(!ZYPCommonFunc.hasValue(lineMsg.dsOrdLineCatgCd_LL)){
            return;
        }
        for(int i = 0; i < bizMsg.P.getValidCount() ; i++){
            NWAL1500_PCMsg pCMsg = bizMsg.P.no(i);
            if(S21StringUtil.isEquals(lineMsg.dsOrdLineCatgCd_LL.getValue(),pCMsg.dsOrdLineCatgCd_P.getValue())){
                lineMsg.rtlWhCd_LL.clear();
                lineMsg.rtlWhNm_LL.clear();
                lineMsg.rtlSwhCd_LL.clear();
                lineMsg.rtlSwhNm_LL.clear();
                ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhCd_LL, pCMsg.rtlWhCd_P);
                ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhNm_LL, pCMsg.rtlWhNm_P);
                break;
            }
        }
    }
    // 2018/03/16 S21_NA#24459 Add End

    // 2018/04/05 S21_NA#24098 Add Start
    public static void clearInvisibleChkOfRma(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            glblMsg.C.no(i).xxChkBox_RC.clear();
        }
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            bizMsg.C.no(i).xxChkBox_RC.clear();
        }
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            glblMsg.D.no(i).xxChkBox_RL.clear();
        }
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            bizMsg.D.no(i).xxChkBox_RL.clear();
        }
    }

    public static void clearInvisibleChkOfLineConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            glblMsg.A.no(i).xxChkBox_LC.clear();
        }
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            bizMsg.A.no(i).xxChkBox_LC.clear();
        }
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            glblMsg.B.no(i).xxChkBox_LL.clear();
        }
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            bizMsg.B.no(i).xxChkBox_LL.clear();
        }
    }
    // 2018/04/05 S21_NA#24098 Add End

    // 2018/04/24 S21_NA#25534 Add Start
    private static NWAL1500_BSMsg getPricingErrorLineConfig(NWAL1500SMsg glblMsg, NWZC157002PMsg priceLine) {

        if (!isPricingErrorLineConfig(priceLine)) {
            return null;
        }
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            if (S21StringUtil.isEquals(priceLine.trxLineNum.getValue(), lineMsg.cpoDtlLineNum_LL.getValue()) //
                    && S21StringUtil.isEquals(priceLine.trxLineSubNum.getValue(), lineMsg.cpoDtlLineSubNum_LL.getValue())) {
                return lineMsg;
            }
        }
        return null;
    }

    private static NWAL1500_DSMsg getPricingErrorLineRma(NWAL1500SMsg glblMsg, NWZC157002PMsg priceLine) {

        if (!isPricingErrorRmaLine(priceLine)) {
            return null;
        }
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);
            if (S21StringUtil.isEquals(priceLine.trxLineNum.getValue(), rmaLineMsg.cpoDtlLineNum_RL.getValue()) //
                    && S21StringUtil.isEquals(priceLine.trxLineSubNum.getValue(), rmaLineMsg.cpoDtlLineSubNum_RL.getValue())) {
                return rmaLineMsg;
            }
        }
        return null;
    }

    private static boolean isPricingErrorLineConfig(NWZC157002PMsg priceLine) {

        if (CONFIG_CATG.OUTBOUND.equals(priceLine.configCatgCd.getValue())) {
            return true;
        }
        return false;
    }

    private static boolean isPricingErrorRmaLine(NWZC157002PMsg priceLine) {

        if (CONFIG_CATG.INBOUND.equals(priceLine.configCatgCd.getValue())) {
            return true;
        }
        return false;
    }
    // 2018/04/24 S21_NA#25534 Add End

    // 2018/05/16 S21_NA#25114 add start
    public static boolean isRebillTangibleItem(NWAL1500_BSMsg lineMsg, String glblCmpyCd) {
        if (CR_REBIL.REBILL.equals(lineMsg.crRebilCd_LL.getValue())) {
            // MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, lineMsg.mdseCd_LL.getValue()); // 2019/06/03 QC#50555 Del
            // if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.invtyCtrlFlg.getValue())) { // 2019/06/03 QC#50555 Del
                return true;
            // } // 2019/06/03 QC#50555 Del
        }
        return false;
    }
    // 2018/05/16 S21_NA#25114 add end

    // 2018/06/21 S21_NA#25151 Add Start
    /**
     * <pre>
     * compare updTsTxt(MM/DD/YYYY HH24:MI:SS) and updTs(YYYYMMDDHH24MISSFF3).
     * @param updTsTxt MM/DD/YYYY HH24:MI:SS formatted timestamp.
     * @param updTs YYYYMMDDHH24MISSFF3 formatted timestamp.
     * @return true: same time. false: not same time
     * </pre>
     */
    public static boolean isSameUpdTsTxtAndUpdTs(String updTsTxt, String updTs) {

        if (updTsTxt == null && updTs == null) {
            return true;
        } else if (updTsTxt == null && updTs != null) {
            return false;
        } else if (updTsTxt != null && updTs == null) {
            return false;
        }
        String[] updTsTxtArray = updTsTxt.split(" ");
        if (updTsTxtArray.length != 2) {
            return false;
        }
        String[] dateArray = updTsTxtArray[0].split("\\/");
        String [] tsArray = updTsTxtArray[1].split("\\:");
        String formatUpdTsTxt = dateArray[2] + dateArray[0] + dateArray[1] + tsArray[0] + tsArray[1] + tsArray[2];

        String updTsSec = updTs;
        if (updTsSec.length() >= 17) {
            updTsSec = updTsSec.substring(0, 14);
        }
        return S21StringUtil.isEquals(formatUpdTsTxt, updTsSec);
    }

    /**
     * <pre>
     * compare updUserIdName (USERID + ( + First Name + " " + Last Name + ) and userId (Userid)
     * @param updUserIdName userid
     * @param updUserId userid USERID + ( + First Name + " " + Last Name + )
     * @return true: same userid, false: not same userid
     * </pre>
     */
    public static boolean isSameUpdateUser(String updUserIdName, String updUserId) {

        if (updUserIdName == null && updUserId == null) {
            return true;
        } else if (updUserIdName == null && updUserId != null) {
            return false;
        } else if (updUserIdName != null && updUserId == null) {
            return false;
        }
        // 2018/08/06 S21_NA#27642 Mod Start
//        String[] idNameArray = updUserIdName.split("\\(");
//        String searchedUpdUserNm = null;
//        if (idNameArray.length >= 2) {
//            searchedUpdUserNm = idNameArray[0];
//        } else {
//            searchedUpdUserNm = updUserIdName;
//        }
        String searchedUpdUserNm = updUserIdName;
        // 2018/08/06 S21_NA#27642 Mod End
        return S21StringUtil.isEquals(searchedUpdUserNm, updUserId);
    }
    // 2018/06/21 S21_NA#25151 Add End
    // 2018/07/09 S21_NA#26895 Add Start, 2018/09/20 S21_NA#28199 modify logic
    /**
     * <pre>
     * Set line retail warehousecode as base component.
     * at QC#28199, the logic for RMA was deleted.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param dsOrdPosnNum position for satting.
     * @param configCatgCd Config Category Code ("O", or "I", currentlry, this method workgin only for "O": Outbound.
     * </pre>
     */
    @SuppressWarnings("unchecked")
    public static void setRtlWhCdAsBaseComponent(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String dsOrdPosnNum, String configCatgCd) {

        Map<String, String> rtlWhCdMap = new HashMap<String, String>(0);
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configCatgCd)) {
            // 2018/09/27 S21_NA#28199-2 Del Start
//            NWAL1500_ASMsg configMsg = getParentConfig(glblMsg.A, dsOrdPosnNum);
//            if (!NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configMsg.configTpCd_LC.getValue(), configCatgCd, true, false, false)) {
//                return;
//            }
            // 2018/09/27 S21_NA#28199-2 Del End
            int baseCmptIdx = getBaseComponentLineIdx(glblMsg, CONFIG_CATG.OUTBOUND, dsOrdPosnNum);
            if (baseCmptIdx == -1) {
                return;
            }
            NWAL1500_BSMsg baseLineMsg = glblMsg.B.no(baseCmptIdx);
            String rtlWhCd = baseLineMsg.rtlWhCd_LL.getValue();
            String rtlWhNm = baseLineMsg.rtlWhNm_LL.getValue();
            String ordLineSrcCd = baseLineMsg.ordLineSrcCd_LL.getValue(); // 2018/09/27 S21_NA#28199-2 Add
            if (!ZYPCommonFunc.hasValue(rtlWhCd) && !ZYPCommonFunc.hasValue(rtlWhNm)) {
                return;
            }
            if (!ZYPCommonFunc.hasValue(rtlWhCd) && ZYPCommonFunc.hasValue(rtlWhNm)) {
                // Derive rtlWhCd.
                rtlWhCd = rtlWhCdMap.get(rtlWhNm);
                if (rtlWhCd == null) {
                    S21SsmEZDResult ssmRslt = NWAL1500QueryForLineConfig.getInstance().getWhInfoWithRsnOfPerfectMatching(bizMsg, rtlWhNm);
                    if (ssmRslt.isCodeNormal()) {
                        List<String> rtlWhCdList = (List<String>) ssmRslt.getResultObject();
                        rtlWhCd = rtlWhCdList.get(0);
                        rtlWhCdMap.put(rtlWhNm, rtlWhCd);
                    }
                }
                if (rtlWhCd == null) {
                    return;
                }
                baseLineMsg.rtlWhCd_LL.setValue(rtlWhCd);
            }
            // 2018/07/13 S21_NA#27228 Add Start
            if (!NWXC150001DsCheck.isAvalableOverWriteBaseRtlWhCd(glblCmpyCd, rtlWhCd)) {
                return;
            }
            // 2018/07/13 S21_NA#27228 Add End
            boolean isBaseRebill = S21StringUtil.isEquals(CR_REBIL.REBILL, baseLineMsg.crRebilCd_LL.getValue());
            for (int slctLineIndex = 0; slctLineIndex < glblMsg.B.getValidCount(); slctLineIndex++) {
                if (slctLineIndex == baseCmptIdx) {
                    continue;
                }
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineIndex);
                // 2018/09/27 S21_NA#28199-2 Del Start
//                boolean isMdseTangible = isMdseTangible(glblCmpyCd, lineMsg.mdseCd_LL.getValue());
                // 2018/09/27 S21_NA#28199-2 Del End
                // 2018/09/27 S21_NA#28199-2 Add Start
                MDSETMsg mdseTMsg = getMdse(glblCmpyCd, lineMsg.mdseCd_LL.getValue());
                if (mdseTMsg == null) {
                    continue;
                }
                boolean isOverWriteTarget = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.invtyCtrlFlg.getValue()) //
                    || S21StringUtil.isEquals(MDSE_TP.SET, mdseTMsg.mdseTpCd.getValue());
                // 2018/09/27 S21_NA#28199-2 Add End
                if (!S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue()) //
                        || S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.baseCmptFlg_LL.getValue()) //
                        || S21StringUtil.isEquals(ORD_LINE_STS.CANCELLED, lineMsg.ordLineStsCd_LL.getValue()) //
//                        || !isMdseTangible) { 2018/09/27 S21_NA#28199-2 Del
                        || !isOverWriteTarget) { // 2018/09/27 S21_NA#28199-2 Add
                    continue;
                }
                if (!NWXC150001DsCheck.isAvalableOverWriteRtlWhCd(glblCmpyCd, lineMsg.rtlWhCd_LL.getValue())) {
                    continue;
                }
                if (isBaseRebill && !S21StringUtil.isEquals(CR_REBIL.REBILL, lineMsg.crRebilCd_LL.getValue())) {
                    continue;
                }
                // 2022/02/17 QC#59693 Add Start
                if (ZYPCommonFunc.hasValue(mdseTMsg.defSrcWhCd)) {
                    continue;
                }
                // 2022/02/17 QC#59693 Add End
                ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhCd_LL, rtlWhCd);
                ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhNm_LL, rtlWhNm);
                ZYPEZDItemValueSetter.setValue(lineMsg.ordLineSrcCd_LL, ordLineSrcCd); // 2018/09/27 S21_NA#28199-2 Add
            }
            prepareSetToConponent(glblMsg); // 2022/02/17 QC#59693 Add
        } else if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configCatgCd)) { // 2018/09/20 S21_NA#28199 Del Start // 2019/01/11 S21_NA#29811 ReAdd Partially
            NWAL1500_CSMsg rmaConfigMsg = getParentConfig(glblMsg.C, dsOrdPosnNum);
            // if (!NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), rmaConfigMsg.configTpCd_RC.getValue(), configCatgCd, true, false, false)) {
            //     return;
            // }
            // 2019/01/11 S21_NA#29811 Add Start
            // Override When Only Exsisting IB
            if (!NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), rmaConfigMsg.configTpCd_RC.getValue(), configCatgCd, false, true, false)) {
                return;
            }
            // 2019/01/11 S21_NA#29811 Add End
            int baseCmptIdx = getBaseComponentLineIdx(glblMsg, CONFIG_CATG.INBOUND, dsOrdPosnNum);
            if (baseCmptIdx == -1) {
                return;
            }
            NWAL1500_DSMsg baseRmaLineMsg = glblMsg.D.no(baseCmptIdx);
            String rtlWhCd = baseRmaLineMsg.rtlWhCd_RL.getValue();
            String rtlWhNm = baseRmaLineMsg.rtlWhNm_RL.getValue();
            if (!ZYPCommonFunc.hasValue(rtlWhCd) && !ZYPCommonFunc.hasValue(rtlWhNm)) {
                return;
            }
            if (!ZYPCommonFunc.hasValue(rtlWhCd) && ZYPCommonFunc.hasValue(rtlWhNm)) {
                // Derive rtlWhCd.
                rtlWhCd = rtlWhCdMap.get(rtlWhNm);
                if (rtlWhCd == null) {
                    S21SsmEZDResult ssmRslt = NWAL1500QueryForLineConfig.getInstance().getWhInfoWithRsnOfPerfectMatching(bizMsg, rtlWhNm);
                    if (ssmRslt.isCodeNormal()) {
                        List<String> rtlWhCdList = (List<String>) ssmRslt.getResultObject();
                        rtlWhCd = rtlWhCdList.get(0);
                        rtlWhCdMap.put(rtlWhNm, rtlWhCd);
                    }
                }
                if (rtlWhCd == null) {
                    return;
                }
                baseRmaLineMsg.rtlWhCd_RL.setValue(rtlWhCd);
            }
            // 2018/07/13 S21_NA#27228 Add Start
            // if (!NWXC150001DsCheck.isAvalableOverWriteBaseRtlWhCd(glblCmpyCd, rtlWhCd)) {
            //     return;
            // }
            // 2018/07/13 S21_NA#27228 Add End
            boolean isBaseRebill = S21StringUtil.isEquals(CR_REBIL.REBILL, baseRmaLineMsg.crRebilCd_RL.getValue());
            for (int slctLineIndex = 0; slctLineIndex < glblMsg.D.getValidCount(); slctLineIndex++) {
                if (slctLineIndex == baseCmptIdx) {
                    continue;
                }
                NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(slctLineIndex);
                // 2019/01/11 S21_NA#29811 Add Start
                MDSETMsg mdseTMsg = getMdse(glblCmpyCd, rmaLineMsg.mdseCd_RL.getValue());
                if (mdseTMsg == null) {
                    continue;
                }
                boolean isOverWriteTarget = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.invtyCtrlFlg.getValue()) //
                    || S21StringUtil.isEquals(MDSE_TP.SET, mdseTMsg.mdseTpCd.getValue());
                // 2019/01/11 S21_NA#29811 Add End
                // boolean isMdseTangible = isMdseTangible(glblCmpyCd, rmaLineMsg.mdseCd_RL.getValue()); // 2019/01/11 S21_NA#29811 Del
                if (!S21StringUtil.isEquals(dsOrdPosnNum, rmaLineMsg.dsOrdPosnNum_RL.getValue()) //
                        || S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rmaLineMsg.baseCmptFlg_RL.getValue()) //
                        || S21StringUtil.isEquals(ORD_LINE_STS.CANCELLED, rmaLineMsg.ordLineStsCd_RL.getValue()) //
                        // || !isMdseTangible) { // 2019/01/11 S21_NA#29811 Del
                        || !isOverWriteTarget) { // 2019/01/11 S21_NA#29811 Add
                    continue;
                }
                // if (!NWXC150001DsCheck.isAvalableOverWriteRtlWhCd(glblCmpyCd, rmaLineMsg.rtlWhCd_RL.getValue())) {
                //     continue;
                // }
                if (isBaseRebill && !S21StringUtil.isEquals(CR_REBIL.REBILL, rmaLineMsg.crRebilCd_RL.getValue())) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlWhCd_RL, rtlWhCd);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlWhNm_RL, rtlWhNm);
            } // 2018/09/20 S21_NA#28199 Del End // 2019/01/11 S21_NA#29811 ReAdd Partially
        }
    }

    /**
     * <pre>
     * Get Retail Warehouse data from base component line.
     * @param glblMsg NWAL1500SMsg Global Message
     * @param configCatgCd Config Category Code
     * @param dsOrdPosnNum DS Order Position Number
     * @return Map<String, String> Key: "RTL_WH_CD", "RTL_WH_NM". if no base component line, this method returns null.
     * </pre>
     */
    private static int getBaseComponentLineIdx(NWAL1500SMsg glblMsg, String configCatgCd, String dsOrdPosnNum) {

        if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configCatgCd)) {
            for (int slctLineIndex = 0; slctLineIndex < glblMsg.B.getValidCount(); slctLineIndex++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineIndex);
                if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue()) //
                        && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.baseCmptFlg_LL.getValue())) {
                    return slctLineIndex;
                }
            }
        } else if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configCatgCd)) {
            for (int slctLineIndex = 0; slctLineIndex < glblMsg.D.getValidCount(); slctLineIndex++) {
                NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(slctLineIndex);
                if (S21StringUtil.isEquals(dsOrdPosnNum, rmaLineMsg.dsOrdPosnNum_RL.getValue()) //
                        && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rmaLineMsg.baseCmptFlg_RL.getValue())) {
                    return slctLineIndex;
                }
            }
        }
        return -1;
    }
    // 2018/07/09 S21_NA#26895 Add End
    // 2018/08/02 S21_NA#26666 add start
    private static CPO_SRC_TPTMsg getCpoSrcTp(String cpoSrcTpDescTxt, String glblCmpyCd) {
        CPO_SRC_TPTMsg tMsg = new CPO_SRC_TPTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("cpoSrcTpDescTxt01", cpoSrcTpDescTxt);
        CPO_SRC_TPTMsgArray tMsgArray= (CPO_SRC_TPTMsgArray)EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        }
        return null;
    }
    // 2018/08/02 S21_NA#26666 add end

    // 2018/09/20 S21_NA#28199 Add Start
    private static NWZC180001PMsg createDfltWhApiMsg(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg, String confPostCd, NWAL1500_BSMsg baseCmptLineMsg) {

        NWZC180001PMsg pMsg = new NWZC180001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_OTBD);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, lineMsg.mdseCd_LL.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, confPostCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, lineMsg.ordQty_LL);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, lineMsg.svcMachMstrPk_LL);
        ZYPEZDItemValueSetter.setValue(pMsg.baseCmptFlg, lineMsg.baseCmptFlg_LL);
        if (baseCmptLineMsg != null //
                && !S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.baseCmptFlg_LL.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.rtlWhCd_BC, baseCmptLineMsg.rtlWhCd_LL);
            ZYPEZDItemValueSetter.setValue(pMsg.rtlSwhCd_BC, baseCmptLineMsg.rtlSwhCd_LL);
            ZYPEZDItemValueSetter.setValue(pMsg.ordLineSrcCd_BC, baseCmptLineMsg.ordLineSrcCd_LL);
        } else {
            pMsg.rtlWhCd_BC.clear();
            pMsg.rtlSwhCd_BC.clear();
            pMsg.ordLineSrcCd_BC.clear();
        }
        ZYPEZDItemValueSetter.setValue(pMsg.xxLineNum, lineMsg.xxLineNum_LL);
        return pMsg;
    }

    /**
     * <pre>
     * Setup Default WH data for line.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param pMsgList NWZC180001 Defautl WH API (without no error)
     * </pre>
     */
    public static void setDefWh(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<NWZC180001PMsg> pMsgList) {

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            for (NWZC180001PMsg pMsg : pMsgList) {
                // Add Start 2019/07/25 QC#52072
                if (S21StringUtil.isEquals(lineMsg.crRebilCd_LL.getValue(), CR_REBIL.REBILL)) {
                    continue;
                }
                // Add End 2019/07/25 QC#52072
                // QC#52264 2019/07/31 Add Start
                if(ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_LL)){
                    continue;
                }
                // QC#52264 2019/07/31 Mod End
                // QC#52416 2019/12/16 Add Start
                if(!NWAL1500CommonLogicForLineControl.isNotProtectedFieldForLineTabByLineSts(lineMsg)){
                    continue;
                }
                // QC#52416 2019/12/16 Add End

                if (S21StringUtil.isEquals(lineMsg.xxLineNum_LL.getValue(), pMsg.xxLineNum.getValue())) {
                    String rtlWhCd = pMsg.rtlWhCd.getValue();
                    String rtlSwhCd = pMsg.rtlSwhCd.getValue();

                    ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhCd_LL, rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhNm_LL, getRtlWhNm(bizMsg, rtlWhCd));
                    ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhCd_LL, rtlSwhCd);
                    ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhNm_LL, getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
                    ZYPEZDItemValueSetter.setValue(lineMsg.ordLineSrcCd_LL, pMsg.ordLineSrcCd);
                    break;
                }
            }
        }
    }
    // 2018/09/20 S21_NA#28199 Add End

    // 2018/10/24 Add Start QC#28866
    public static void outputordEntryActLog(String screenAplID, String ordEntryActCd) {
        StringBuffer sb = new StringBuffer();

        sb.append(screenAplID + " ORD_ENTRY_ACT:" + ordEntryActCd);
        S21InfoLogOutput.println(sb.toString());
    }
    // 2018/10/24 Add End   QC#28866

    
    // 2019/05/23 QC#50043 Add Start
    public static void outputEntryActBizProctLog(NWAL1500CMsg bizMsg) {
        ORD_ENTRY_ACTTMsg tmsg = new ORD_ENTRY_ACTTMsg();

        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.ordEntryActCd, bizMsg.ordEntryActCd.getValue());

        tmsg = (ORD_ENTRY_ACTTMsg)S21CodeTableAccessor.findByKey(tmsg);

        if (tmsg == null ){
            return;
        }

        S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();

        bizLogMsg.setSubSysId(NWAL1500Constant.SUB_SYS_ID);
        bizLogMsg.setProcId(NWAL1500Constant.PROCESS_ID);
        bizLogMsg.setDocTpCd(NWAL1500Constant.DOCUMENT_TYPE);
        bizLogMsg.setEventId(NWAL1500Constant.EVENT_ID_ENTRY_ACTION);
        bizLogMsg.setPrntDocId(bizMsg.cpoOrdNum.getValue());
        bizLogMsg.setBizProcCmntTxt_01(bizMsg.xxDplyTab.getValue());
        bizLogMsg.setBizProcCmntTxt_02(tmsg.ordEntryActDescTxt.getValue());
        bizLogMsg.setBizProcCmntTxt_03("");

        NWZC150001CpouInsBizProcLog cpouInsBizProcLog = new NWZC150001CpouInsBizProcLog();
        cpouInsBizProcLog.printBizProcessLog(Arrays.asList(bizLogMsg));

    }
    // 2019/05/23 QC#50043　Add End

    // 2019/01/30 QC#30036 Add Start
    public static boolean checkPoNum(NWAL1500CMsg bizMsg) {
        boolean errFlg = false;

        String trxRuleTp = NWAL1500CommonLogic.getTrxRuleTp(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
        ZYPEZDItemValueSetter.setValue(pMsg.dsTrxRuleTpCd, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, bizMsg.soldToCustLocCd);
        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (pMsg.TransactionRuleList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.TransactionRuleList.getValidCount(); i++) {
                String dsCrCardReqFlg = pMsg.TransactionRuleList.no(i).dsPoReqFlg.getValue();
                if (ZYPConstant.FLG_ON_Y.equals(dsCrCardReqFlg)) {
                    if (!ZYPCommonFunc.hasValue(bizMsg.custIssPoNum)) {
                        errFlg = true;
                        break;
                    }
                }
            }
        }
        return errFlg;
    }
    // 2019/01/30 QC#30036 Add End

    // Add Start 2019/09/18 QC#53018
    public static boolean checkBillOnlyLineErr(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        boolean errFlg = false;

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            if (!NWXC150001DsCheck.isReqBaseCmptFlgAtConfigTp(bizMsg.glblCmpyCd.getValue(), glblMsg.A.no(i).configTpCd_LC.getValue())) {
                continue;
            }

            int baseCmptIdx = NWAL1500CommonLogic.getBaseComponentLineIdx(glblMsg, CONFIG_CATG.OUTBOUND, glblMsg.A.no(i).dsOrdPosnNum_LC.getValue());
            if (baseCmptIdx == -1) {
//                return errFlg;
                continue;
            }

            NWAL1500_BSMsg baseLineMsg = glblMsg.B.no(baseCmptIdx);
            String baseRtlWhCd = null;
            if (CR_REBIL.CREDIT.equals(baseLineMsg.crRebilCd_LL.getValue()) //
                    || CR_REBIL.REBILL.equals(baseLineMsg.crRebilCd_LL.getValue())) {
                baseRtlWhCd = baseLineMsg.origInvtyLocCd_LL.getValue();
            } else {
                baseRtlWhCd = baseLineMsg.rtlWhCd_LL.getValue();
            }

            for (int j = 0; j < glblMsg.B.getValidCount(); j++) {

                NWAL1500_BSMsg lineMsg = glblMsg.B.no(j);
                if (S21StringUtil.isEquals(baseLineMsg.dsOrdPosnNum_LL.getValue(), lineMsg.dsOrdPosnNum_LL.getValue())) {

                    String constRtlWhCd = ZYPCodeDataUtil.getVarCharConstValue(NWAL1500_RENTAL_CONV_DEFWH, bizMsg.glblCmpyCd.getValue());
                    String rtlWhCd = null;
                    if (CR_REBIL.CREDIT.equals(lineMsg.crRebilCd_LL.getValue()) //
                            || CR_REBIL.REBILL.equals(lineMsg.crRebilCd_LL.getValue())) {
                        rtlWhCd = lineMsg.origInvtyLocCd_LL.getValue();
                    } else {
                        rtlWhCd = lineMsg.rtlWhCd_LL.getValue();
                    }
                    if (S21StringUtil.isEquals(baseRtlWhCd, constRtlWhCd) && !S21StringUtil.isEquals(baseRtlWhCd, rtlWhCd)) {
                        lineMsg.dsOrdLineCatgCd_LL.setErrorInfo(1, NWAM0972E);
                        errFlg = true;
                    }
                }
            }
        }

        return errFlg;
    }
    // Add End 2019/09/18 QC#53018

    /**
     * 2020/04/27 QC#56638 Add
     * Get Salse Req Defaulting
     * @param bizMsg NWAL1500CMsg
     * @return Boolean
     */
    public static boolean isSlsReqDef(NWAL1500CMsg bizMsg) {

        boolean isShipBase = true;
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd.getValue());
            tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);

            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.baseLocTxt)) {
                if ("Ship To Location".equals(tMsg.baseLocTxt.getValue())) {
                    isShipBase = true;
                } else if ("Sold To Location".equals(tMsg.baseLocTxt.getValue())) {
                    isShipBase = false;
                } else {
                    isShipBase = true;
                }
            }
        }

        return isShipBase;
    }
}
