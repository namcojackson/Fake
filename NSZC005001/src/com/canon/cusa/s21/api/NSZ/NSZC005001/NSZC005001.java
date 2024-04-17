/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC005001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDPMsg;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CCYTMsg;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.FSRTMsg;
import business.db.FSR_CHRGTMsg;
import business.db.FSR_CHRG_DTLTMsg;
import business.db.FSR_CHRG_DTLTMsgArray;
import business.db.FSR_EVENTTMsg;
import business.db.FSR_EXPTMsg;
import business.db.FSR_EXPTMsgArray;
import business.db.FSR_ISTL_CHK_LISTTMsg;
import business.db.FSR_ISTL_CHK_LISTTMsgArray;
import business.db.FSR_USGTMsg;
import business.db.FSR_USGTMsgArray;
import business.db.FSR_USG_SRVYTMsg;
import business.db.FSR_USG_SRVYTMsgArray;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.FSR_VISIT_TASKTMsg;
import business.db.FSR_VISIT_TM_EVENTTMsg;
import business.db.FSR_VISIT_TM_EVENTTMsgArray;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.INVTYTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.SVC_BILL_TPTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MOD_SEND_CLICKTMsg;
import business.db.SVC_MOD_STSTMsg;
import business.db.SVC_PBLM_CRCT_TPTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import business.db.SVC_SUPPL_TASKTMsg;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASKTMsgArray;
import business.db.SVC_TM_EVENTTMsg;
import business.db.SVC_TM_EVENTTMsgArray;
import business.parts.NLZC002001PMsg;
import business.parts.NSXC001001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NSZC005001PMsg;
import business.parts.NSZC005001_xxChargesListPMsg;
import business.parts.NSZC005001_xxExpenseListPMsg;
import business.parts.NSZC005001_xxFsrIstlChkListPMsg;
import business.parts.NSZC005001_xxFsrUsgListPMsg;
import business.parts.NSZC005001_xxMtrReadListPMsg;
import business.parts.NSZC005001_xxPartSurveyListPMsg;
import business.parts.NSZC005001_xxPartSurveyListPMsgArray;
import business.parts.NSZC005001_xxTmEventListPMsg;
import business.parts.NSZC005001_xxVisitTaskListPMsg;
import business.parts.NSZC005002PMsg;
import business.parts.NSZC006001PMsg;
import business.parts.NSZC006001_xxInvLineListPMsg;
import business.parts.NSZC010001PMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC045001PMsg;
import business.parts.NSZC061001PMsg;
import business.parts.NSZC061001_xxDrumChrgListPMsg;
import business.parts.NSZC061001_xxExpMdseListPMsg;
import business.parts.NSZC061001_xxPartsChrgListPMsg;
import business.parts.NSZC061001_xxSvcTaskChrgListPMsg;
import business.parts.NSZC067001PMsg;
import business.parts.NWZC033001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NSZ.NSZC005001.constant.NSZC005001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC006001.NSZC006001;
import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001;
import com.canon.cusa.s21.api.NSZ.NSZC061001.NSZC061001;
import com.canon.cusa.s21.api.NSZ.NSZC061001.constant.NSZC061001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC067001.NSZC067001;
import com.canon.cusa.s21.api.NWZ.NWZC033001.NWZC033001;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetMblIntfcInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetMblIntfcInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_ARTH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CNTR_RESET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TEST_COPY_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FSR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MBL_INTFC_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ASG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CHRG_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MOD_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TM_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TASK_PRINT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Service Dispatch Completion API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   SRA             E.Inada         Create          N/A
 * 2013/07/23   Fujitsu         S.Nakai         Update          QC1418
 * 2013/08/28   SRA             E.Inada         Update          QC1809
 * 2013/09/11   SRA             Y.Chen          Update          QC2208
 * 2013/09/12   Hitachi         T.Yonekura      Update          QC2237
 * 2013/10/01   SRA             E.Inada         Update          QC2523
 * 2013/10/24   SRA             E.Inada         Update          QC2888
 * 2014/02/27   Fujitsu         Y.Kamide        Update          N/A
 * 2014/03/17   Fujitsu         S.Katafuchi     Update          Defect#39
 * 2014/06/05   Hitachi         T.Aoyagi        Update          CSA-186
 * 08/20/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 * 10/14/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK Bugfix
 * 10/21/2015   Hitachi         A.Kohinata      Update          NA#Renewal
 * 2015/12/09   Hitachi         K.Yamada        Update          CSA QC#1708
 * 2015/12/21   Hitachi         K.Yamada        Update          CSA QC#1924
 * 2015/12/24   Hitachi         A.Kohinata      Update          CSA QC#921
 * 01/19/2016   Fujitsu         S.Nakai         Update          CSA QC#2750
 * 02/08/2016   Hitachi         T.Iwamoto       Update          CSA QC#2863
 * 02/17/2016   Hitachi         T.Iwamoto       Update          CSA QC#4134
 * 02/19/2016   Hitachi         T.Iwamoto       Update          CSA QC#2749,2695
 * 03/02/2016   Hitachi         T.Iwamoto       Update          CSA QC#4644
 * 03/03/2016   Hitachi         T.Iwamoto       Update          CSA QC#3390
 * 03/17/2016   Hitachi         T.Tomita        Update          CSA QC#5588
 * 04/01/2016   Hitachi         T.Iwamoto       Update          CSA QC#5083,6020
 * 04/08/2016   Hitachi         T.Iwamoto       Update          CSA QC#6692
 * 04/19/2016   Hitachi         T.Iwamoto       Update          CSA QC#7004
 * 04/20/2016   Hitachi         T.Iwamoto       Update          CSA QC#4469
 * 04/26/2016   Hitachi         T.Iwamoto       Update          CSA QC#7519
 * 04/28/2016   Hitachi         T.Iwamoto       Update          CSA QC#7732,7737
 * 04/29/2016   Fujitsu         S.Nakai         Update          CSA QC#7804
 * 05/19/2016   Hitachi         T.Iwamoto       Update          CSA QC#8457
 * 05/20/2016   Hitachi         T.Iwamoto       Update          CSA QC#8480
 * 05/24/2016   Hitachi         T.Iwamoto       Update          CSA QC#8636
 * 05/31/2016   Hitachi         T.Iwamoto       Update          CSA QC#9144
 * 06/07/2016   Hitachi         T.Iwamoto       Update          CSA QC#9218
 * 06/28/2016   Hitachi         T.Iwamoto       Update          CSA QC#10790
 * 07/01/2016   Hitachi         T.Iwamoto       Update          CSA QC#11185,9677
 * 07/06/2016   Hitachi         T.Iwamoto       Update          CSA QC#11284
 * 08/02/2016   Hitachi         T.Iwamoto       Update          CSA QC#12794
 * 08/22/2016   Hitachi         T.Iwamoto       Update          CSA QC#13611
 * 08/26/2016   Hitachi         K.Kasai         Update          CSA QC#13661
 * 09/07/2016   Hitachi         A.Kohinata      Update          CSA QC#13661
 * 09/12/2016   Hitachi         T.Kanasaka      Update          CSA QC#14206
 * 09/14/2016   Hitachi         T.Kanasaka      Update          CSA QC#13860,13987
 * 10/03/2016   Hitachi         T.Tomita        Update          CSA QC#11896
 * 10/05/2016   Hitachi         T.Kanasaka      Update          CSA QC#12274
 * 10/13/2016   Hitachi         T.Kanasaka      Update          CSA QC#13987
 * 10/20/2016   Hitachi         Y.Takeno        Update          CSA QC#14682
 * 10/20/2016   Hitachi         Y.Takeno        Update          CSA QC#14252
 * 11/04/2016   Hitachi         Y.Takeno        Update          CSA QC#15732
 * 11/30/2016   Hitachi         K.Yamada        Update          CSA QC#15782
 * 12/01/2016   Hitachi         T.Tomita        Update          CSA QC#14252
 * 12/13/2016   Hitachi         T.Tomita        Update          CSA QC#14252
 * 12/14/2016   Hitachi         K.Ochiai        Update          CSA QC#15735
 * 12/19/2016   Hitachi         K.Kishimoto     Update          CSA QC#16512
 * 01/11/2017   Hitachi         A.Kohinata      Update          CSA QC#17028
 * 01/13/2017   Hitachi         A.Kohinata      Update          CSA QC#17108,17117
 * 01/15/2017   Hitachi         T.Tomita        Update          CSA QC#17108,17129
 * 01/17/2017   Hitachi         K.Kitachi       Update          CSA QC#16512
 * 01/26/2017   Hitachi         A.Kohinata      Update          CSA QC#17114
 * 01/30/2017   Hitachi         Y.Takeno        Update          CSA QC#17291
 * 02/13/2017   Hitachi         A.Kohinata      Update          CSA QC#17109
 * 04/07/2017   Hitachi         K.Kitachi       Update          CSA QC#18052
 * 05/08/2017   Hitachi         K.Kitachi       Update          CSA QC#18052
 * 06/16/2017   Hitachi         K.Kitachi       Update          CSA QC#19081
 * 06/23/2017   Hitachi         T.Tomita        Update          CSA QC#19206
 * 06/23/2017   Hitachi         K.Ochiai        Update          CSA QC#19493
 * 06/27/2017   Hitachi         N.Arai          Update          CSA QC#19125
 * 07/03/2017   Hitachi         K.Kojima        Update          CSA QC#19732
 * 07/21/2017   Hitachi         K.Kojima        Update          CSA QC#19963
 * 08/02/2017   Hitachi         T.Tomita        Update          CSA QC#20082
 * 08/04/2017   Hitachi         K.Kitachi       Update          CSA QC#20255
 * 2017/08/28   Hitachi         K.Kitachi       Update          CSA QC#18476
 * 2017/08/31   Hitachi         T.Tomita        Update          CSA QC#19354
 * 2017/09/01   Hitachi         T.Tomita        Update          CSA QC#20865
 * 2017/09/01   Hitachi         T.Tomita        Update          CSA QC#20672
 * 2017/09/07   Hitachi         A.Kohinata      Update          CSA QC#15134
 * 2017/10/13   Hitachi         K.Kim           Update          CSA QC#17430
 * 2017/10/18   Hitachi         U.Kim           Update          CSA QC#21794
 * 2017/10/25   CITS            M.Naito         Update          CSA QC#22061
 * 2017/10/27   Hitachi         T.Tomita        Update          CSA QC#22116
 * 2017/11/22   Hitachi         T.Tomita        Update          CSA QC#22073
 * 2017/12/04   Hitachi         U.Kim           Update          CSA QC#19907
 * 2017/12/25   Hitachi         U.Kim           Update          CSA QC#22524
 * 2017/12/28   CITS            M.Naito         Update          CSA QC#18646
 * 2018/01/15   Hitachi         K.Ochiai        Update          CSA QC#18646
 * 2018/01/15   Hitachi         U.Kim           Update          CSA QC#19702
 * 2018/03/22   CITS            M.Naito         Update          CSA QC#18713
 * 2018/03/30   Hitachi         T.Tomita        Update          CSA QC#16339
 * 2018/05/14   CITS            M.Naito         Update          CSA QC#23898
 * 2018/06/01   CITS            M.Naito         Update          CSA QC#26272
 * 2018/06/07   CITS            M.Naito         Update          CSA QC#22434
 * 2018/06/25   Hitachi         T.Tomita        Update          CSA QC#26799
 * 2018/06/29   Hitachi         A.Kohinata      Update          CSA QC#26795
 * 2018/08/20   Hitachi         K.Kitachi       Update          CSA QC#27460
 * 2018/08/20   CITS            T.Wada          Update          CSA QC#27882
 * 2018/09/10   Hitachi         K.Kojima        Update          CSA QC#26149
 * 2018/09/26   Fujitsu         W.Honda         Update          CSA QC#28381
 * 2018/10/04   Hitachi         K.Kojima        Update          CSA QC#28545
 * 2018/10/18   Hitachi         T.Tomita        Update          CSA QC#28862
 * 2018/11/28   Hitachi         K.Fujimoto      Update          CSA QC#28647
 * 2018/12/06   Hitachi         S.Kitamura      Update          CSA QC#29042
 * 2018/12/12   Hitachi         K.Kim           Update          CSA QC#28633
 * 2018/12/13   Hitachi         K.Kitachi       Update          CSA QC#28635
 * 2018/12/26   Hitachi         K.Kitachi       Update          CSA QC#29723
 * 2019/01/10   Hitachi         K.Fujimoto      Update          CSA QC#26861
 * 2019/02/06   Hitachi         K.Fujimoto      Update          CSA QC#30250
 * 2019/02/13   Hitachi         K.Fujimoto      Update          CSA QC#30310
 * 2019/02/26   Hitachi         K.Fujimoto      Update          CSA QC#30307
 * 2019/03/28   Hitachi         K.Fujimoto      Update          CSA QC#30546
 * 2019/04/19   Hitachi         A.Kohinata      Update          CSA QC#31238
 * 2019/07/30   Hitachi         K.Fujimoto      Update          CSA QC#52220
 * 2019/08/09   Hitachi         A.Kohinata      Update          CSA QC#52105
 * 2019/08/20   Hitachi         K.Kim           Update          CSA QC#52380
 * 2019/08/30   Hitachi         K.Fujimoto      Update          CSA QC#52433
 * 2019/08/30   Hitachi         K.Fujimoto      Update          CSA QC#52128
 * 2019/10/15   Hitachi         K.Fujimoto      Update          CSA QC#54115
 * 2019/10/24   Hitachi         K.Fujimoto      Update          CSA QC#53441
 * 2019/11/18   Hitachi         K.Fujimoto      Update          CSA QC#54391
 * 2019/11/27   Hitachi         A.Kohinata      Update          CSA QC#54837
 * 2019/12/16   Hitachi         Y.Takeno        Update          CSA QC#52752
 * 2019/12/19   Hitachi         Y.Takeno        Update          CSA QC#52752-1
 * 2019/12/24   Hitachi         Y.Takeno        Update          CSA QC#52752-1
 * 2020/03/03   Hitachi         A.Kohinata      Update          CSA QC#56123
 * 2020/04/07   Hitachi         K.Kim           Update          CSA QC#56457
 * 2020/04/08   Hitachi         A.Kohinata      Update          CSA QC#56328
 * 2020/04/13   Hitachi         K.Kim           Update          CSA QC#56498
 * 2020/05/18   Hitachi         K.Sakurai       Update          CSA QC#55328
 * 2020/05/19   Hitachi         K.Yamada        Update          CSA QC#56811
 * 2020/05/25   Hitachi         K.Sakurai       Update          CSA QC#56605
 * 2020/05/29   Hitachi         K.Kitachi       Update          CSA QC#56645
 * 2020/06/01   Hitachi         K.Kitachi       Update          CSA QC#56859
 * 2020/06/01   Hitachi         K.Kitachi       Update          CSA QC#56271
 * 2020/06/10   Hitachi         K.Yamada        Update          CSA QC#57126
 * 2020/06/22   Hitachi         K.Kitachi       Update          CSA QC#57230
 * 2020/07/29   Hitachi         K.Kitachi       Update          CSA QC#57410
 * 2021/09/10   CITS            R.Cabral        Update          CSA QC#58979
 * 2022/02/10   Hitachi         D.Yoshida       Update          CSA QC#57338
 * 2022/04/15   Hitachi         D.Yoshida       Update          CSA QC#59911
 * 2022/04/27   Hitachi         K.Kitachi       Update          CSA QC#59895
 * 2022/05/25   Hitachi         K.Kitachi       Update          CSA QC#59987
 * 2022/12/01   Hitachi         K.Kitachi       Update          CSA QC#60882
 * 2023/02/28   CITS            T.Wada          Update          CSA QC#61231
 * 2023/04/12   Hitachi         K.Kitachi       Update          CSA QC#61311
 * 2023/07/14   Hitachi         K.Watanabe      Update          CSA QC#61310
 * 2023/09/22   Hitachi         K.Watanabe      Update          CSA QC#61310
 * 2024/01/25   CITS            T.Aizawa        Update          CSA QC#63504
 *</pre>
 */
public class NSZC005001 extends S21ApiCommonBase implements NSZC005001Constant {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** onBatchType */
    private ONBATCH_TYPE onbatchType = null;

    /** System Time stamp */
    private String sysTs = null;

    /** System Date Time */
    private String sysDtTm = null;

    /** System Date */
    private String sysDt = null;

    /** System Time */
    private String sysTm = null;

    /** FSR complete flag */
    private boolean fsrCompleteFlg = false;

    /** completionBean */
    private CompletionBean completionBean = null;

    /** isDebreifError */
    private boolean isDebreifError = false;

    // Add Start 2019/01/10 K.Fujimoto QC#26861
    /** Current FSR Status **/
    private String currentFsrSts = null;
    // Add End   2019/01/10 K.Fujimoto QC#26861

    // add start 2015/12/16 CSA Defect#978
    /** result fsr status */
    private String resultFsrStsCd = null;
    // add end 2015/12/16 CSA Defect#978

    // add start 2016/02/08 CSA Defect#2863
    /** Force Update Flag */
    private boolean frceUpdFlg = false;
    // add end 2015/02/08 CSA Defect#2863

    // START 2018/01/15 U.Kim [QC#19702, ADD]
    /** Waiting Second for find by key */
    private int waitSecUpdTaskCompletion;

    // END 2018/01/15 U.Kim [QC#19702, ADD]

    /**
     * NSZC005001
     */
    public NSZC005001() {
        super();

        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsgList List<NSZC005001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC005001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {

        for (NSZC005001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute
     * @param pMsg NSZC005001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC005001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        // START 2018/01/15 U.Kim [QC#19702, ADD]
        setWaitSecUpdTaskOther(pMsg.glblCmpyCd.getValue());
        // END 2018/01/15 U.Kim [QC#19702, ADD]

        // add start 2015/12/17 CSA Defect#978
        if (!setMode(pMsg, onBatchType)) {
            return;
        }
        // add end 2015/12/17 CSA Defect#978

        if (MODE_DEBRIEF.equals(pMsg.xxModeCd.getValue())) {
            execDebriefMode(pMsg, onBatchType);
        } else if (MODE_CHARGABLE.equals(pMsg.xxModeCd.getValue())) {
            execChargableMode(pMsg, onBatchType);
        } else if (MODE_INSTALL_CHECK.equals(pMsg.xxModeCd.getValue())) {
            execInstallCheckMode(pMsg, onBatchType);
        }
    }

    private boolean setMode(NSZC005001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        // add start 2015/12/16 CSA Defect#978
        if (!hasValue(pMsg.xxModeCd)) {
            setValue(pMsg.xxModeCd, MODE_DEBRIEF);
        }
        if (!MODE_DEBRIEF.equals(pMsg.xxModeCd.getValue()) && !MODE_CHARGABLE.equals(pMsg.xxModeCd.getValue()) && !MODE_INSTALL_CHECK.equals(pMsg.xxModeCd.getValue())) {
            setErrMsg(pMsg, NSZM0039E);
            return false;
        }
        // add start 2016/02/08 CSA Defect#2863
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.frceUpdFlg.getValue())) {
            this.frceUpdFlg = true;
        }
        // add end 2016/02/08 CSA Defect#2863

        return true;
        // add end 2015/12/16 CSA Defect#978
    }

    // add start 2015/12/16 CSA Defect#978
    private void execDebriefMode(NSZC005001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        // (1) Init
        if (!init(pMsg, onBatchType)) {
            return;
        }
        // add start 2015/12/16 CSA Defect#978
        // Delete existing FSR Charge
        if (!deleteFsrChrg(pMsg)) {
            return;
        }
        // add end 2015/12/16 CSA Defect#978

        // (2) Recall Check
        if (!checkRecall(pMsg)) {
            return;
        }
        // (3) MeterCount Validation
        // START 2022/12/01 K.Kitachi [QC#60882, MOD]
//        if (!meterCountValidation(pMsg)) {
//            return;
//        }
        if (pMsg.xxMtrReadList.getValidCount() > 0 && !ZYPConstant.FLG_OFF_N.equals(getMtrReqMdlFlg(pMsg))) {
            String svcTaskNum = this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue();
            S21StopWatch sw = new S21StopWatch();
            S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0050 : meterCountValidation Start." + " Task#" + svcTaskNum);
            sw.start();
            boolean meterCountValidation = meterCountValidation(pMsg);
            sw.stop();
            S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0050 : meterCountValidation End." + " Task#" + svcTaskNum);
            S21InfoLogOutput.println(String.format("CHK_CPLT_TM : NSZC0050 : meterCountValidation Elapsed Time [%s]", sw.getElapsedMilliSec()));
            if (!meterCountValidation) {
                return;
            }
        }
        // END 2022/12/01 K.Kitachi [QC#60882, MOD]
        // (4) InterruptTask Check
        if (!checkInterruptTask(pMsg)) {
            return;
        }
        // (5) Create Follow up Call
        if (!createFollowUpCall(pMsg)) {
            return;
        }
        // (6) IWR Status Check
        if (!checkIwrStatus(pMsg)) {
            return;
        }
        // (6) Install Check Check
        // del start 2016/05/31 CSA Defect#9144
        // if (!checkDebriefError(pMsg, DEBRIEF_CHK_ISTALL)) {
        //     return;
        // }
        // del end 2016/05/31 CSA Defect#9144
        // (7) VisitTask Complete Check
        checkVisitTaskCplt(pMsg);

        // add start 2017/01/13 CSA Defect#17117
        if (!updateSvcTaskStsCplt(pMsg)) {
            return;
        }
        // add end 2017/01/13 CSA Defect#17117

        // START 2018/10/04 K.Kojima [QC#28545,ADD]
        if (!updateSvcTaskPhoneFix(pMsg)) {
            return;
        }
        // END 2018/10/04 K.Kojima [QC#28545,ADD]

        // (8) Confirm Usage Parts
        // Mod Start 2018/11/28 K.Fujimoto QC#28647
        // if (fsrCompleteFlg) {
        if(pMsg.xxFsrUsgList.getValidCount() > 0){
        // Mod End   2018/11/28 K.Fujimoto QC#28647
            if (!confirmUsageParts(pMsg)) {
                return;
            }
        }
        // (9) Calculation Price
        if (!calculatePrice(pMsg)) {
            return;
        }
        // (10) Insert FSR Visit Task
        if (!insertFsrVisitTask(pMsg)) {
            return;
        }
        // (11) Insert FSR Expence
        if (!insertFsrExp(pMsg)) {
            return;
        }
        // (12) Insert FSR Usage
        if (!insertFsrUsg(pMsg)) {
            return;
        }
        // (13) Modification Status Update
// del start 2016/08/02 CSA Defect#12794 // TODO Temp
//        if (fsrCompleteFlg) {
//            if (!updateModProcSts(pMsg)) {
//                return;
//            }
//        }
// del end 2016/08/02 CSA Defect#12794
        // (14) Insert FSR Visit Time Event
        if (!insertFsrVisitTmEvent(pMsg)) {
            return;
        }
        // (15) Update FSR Visit
        if (!updateFsrVisit(pMsg)) {
            return;
        }
        // (16) Update Service Task
        if (!updateSvcTask(pMsg)) {
            return;
        }
        // (17) Update FSR
        if (!updateFsr(pMsg)) {
            return;
        }
        // (18) Insert FSR Event
        // mod start 2017/02/13 CSA Defect#17109
        if (!SVC_TASK_STS.CLOSED.equals(this.resultFsrStsCd)) {
            if (!insertFsrEvent(pMsg)) {
                return;
            }
        }
        // mod end 2017/02/13 CSA Defect#17109
        //Add Start 2018/11/28 K.Fujimoto QC#28647
        // (19) Update Inventory
        if(!updateInvtyForDebrief(pMsg,this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue())){
            return;
        }
        //Add End   2018/11/28 K.Fujimoto QC#28647

        // START 2023/02/28 T.Wada    [QC#61231, ADD]
        String svcTaskNum = this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue();
        SVC_TASKTMsg svcTask = getSvcTaskFindByKey(pMsg.glblCmpyCd.getValue(),svcTaskNum);
        if (SVC_TASK_STS.COMPLETED.equals(svcTask.svcTaskStsCd.getValue()) || SVC_TASK_STS.CLOSED.equals(svcTask.svcTaskStsCd.getValue())) {
            if (!updateModProcStsForChargable(pMsg, this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue())) {
                return;
            }
        }
        // END 2023/02/28 T.Wada    [QC#61231, ADD]

        // add start 2015/12/16 CSA Defect#978
        // mod start 2017/02/13 CSA Defect#17109
        if (SVC_TASK_STS.PENDING_CHARGE.equals(this.resultFsrStsCd) || SVC_TASK_STS.CLOSED.equals(this.resultFsrStsCd)) {
            if (!insertFsrChrg(pMsg)) {
                return;
            }
            // START 2023/02/28 T.Wada    [QC#61231, DEL]
            // START 2022/05/25 K.Kitachi [QC#59987, ADD]
//            if (!updateModProcStsForChargable(pMsg, this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue())) {
//                return;
//            }
            // END 2022/05/25 K.Kitachi [QC#59987, ADD]
            // END 2023/02/28 T.Wada    [QC#61231, DEL]
        }
        // mod end 2017/02/13 CSA Defect#17109
        // add end 2015/12/16 CSA Defect#978

        // add start 2017/02/13 CSA Defect#17109
        // START 2017/04/07 K.Kitachi [QC#18052, MOD]
//        if (SVC_TASK_STS.CLOSED.equals(this.resultFsrStsCd)) {
        // Mod Start 2018/11/28 K.Fujimoto QC#28647
        // if (SVC_TASK_STS.CLOSED.equals(this.resultFsrStsCd) && ZYPConstant.FLG_OFF_N.equals(this.completionBean.getDsSvcCallTpTMsg().svcIstlReqFlg.getValue())) {
        if ((SVC_TASK_STS.CLOSED.equals(this.resultFsrStsCd) 
                && ZYPConstant.FLG_OFF_N.equals(this.completionBean.getDsSvcCallTpTMsg().svcIstlReqFlg.getValue()))
                || this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
        // Mod End   2018/11/28 K.Fujimoto QC#28647
        // END 2017/04/07 K.Kitachi [QC#18052, MOD]
            updateStsForDebrief(pMsg);
        }
        // add end 2017/02/13 CSA Defect#17109
    }

    // add start 2015/12/16 CSA Defect#978
    private void execChargableMode(NSZC005001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        // (1) Init
        if (!initChargable(pMsg, onBatchType)) {
            return;
        }

        // mod start 2016/02/08 CSA Defect#2863
        if (!frceUpdFlg) {
            // Insert/Update/Delete FSR charge
            if (!updateFsrChrg(pMsg)) {
                return;
            }
            // Create One time invoice
            if (!createOneTimeInvoice(pMsg)) {
                return;
            }
        }
        // mod end 2016/02/08 CSA Defect#2863

        // Update Status
        if (!updateSts(pMsg)) {
            return;
        }
    }
    private void execInstallCheckMode(NSZC005001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        // (1) Init
        if (!initInstallCheck(pMsg, onBatchType)) {
            return;
        }
        // (2) Insert FSR Install Check
        if (!insertFsrInstallCheckList(pMsg)) {
            return;
        }
        // Add Start 2018/10/18 QC#28862
        if (isDeInstall(pMsg.glblCmpyCd.getValue(), pMsg.xxFsrIstlChkList.no(0).svcTaskNum.getValue())) {
            return;
        }
        // Add End 2018/10/18 QC#28862
        // (3) checkDebriefError
        // del start 2016/05/31 CSA Defect#9144
        // if (!checkDebriefError(pMsg, DEBRIEF_CHK_ISTALL)) {
        //     return;
        // }
        // del end 2016/05/31 CSA Defect#9144
        // (7) VisitTask Complete Check
        checkVisitTaskCplt(pMsg);
        // START 2015/12/14 K.Ochiai [QC#15735, ADD]
        if (!checkSerNum(pMsg)) {
            return;
        }
        // END 2015/12/14 K.Ochiai [QC#15735, ADD]

        // add start 2017/01/13 CSA Defect#17117
        if (!updateSvcTaskStsCplt(pMsg)) {
            return;
        }
        // add end 2017/01/13 CSA Defect#17117

        // (4) Confirm Usage Parts
        // Mod Start 2018/11/28 K.Fujimoto QC#28647
        // if (fsrCompleteFlg) {
        if(pMsg.xxFsrUsgList.getValidCount() > 0){
        // Mod End   2018/11/28 K.Fujimoto QC#28647
            if (!confirmUsageParts(pMsg)) {
                return;
            }
            // (5) Calculation Price
            if (!calculatePrice(pMsg)) {
                return;
            }
            // (6) Insert FSR Usage
            if (!insertFsrUsg(pMsg)) {
                return;
            }
        } else {
            //check only current usage
            if (!checkDebriefError(pMsg, DEBRIEF_CHK_USG_CHRG)) {
                return;
            }
        }
        if (!checkDebriefError(pMsg, DEBRIEF_CHK_LBOR_CHRG)) {
            return;
        }
        // (8) Update FSR Visit
        if (!updateFsrVisitForIstlChk(pMsg)) {
            return;
        }
        // (9) Update Service Task
        if (!updateSvcTask(pMsg)) {
            return;
        }
        // (10) Update FSR
        if (!updateFsr(pMsg)) {
            return;
        }
        // (11) Insert FSR Event
        // mod start 2017/02/13 CSA Defect#17109
        // START 2017/06/16 K.Kitachi [QC#19081, MOD]
        if (!SVC_TASK_STS.COMPLETED.equals(this.resultFsrStsCd) && !SVC_TASK_STS.CLOSED.equals(this.resultFsrStsCd)) {
        // END 2017/06/16 K.Kitachi [QC#19081, MOD]
            if (!insertFsrEvent(pMsg)) {
                return;
            }
        }
        // mod end 2017/02/13 CSA Defect#17109

// del start 2016/08/02 CSA Defect#12794 // TODO Temp
//        if (fsrCompleteFlg) {
//            // (12) Modification Status Update
//            if (!updateModProcSts(pMsg)) {
//                return;
//            }
//        }
// del start 2016/08/02 CSA Defect#12794


        // START 2023/02/28 T.Wada    [QC#61231, ADD]
        String svcTaskNum = this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue();
        SVC_TASKTMsg svcTask = getSvcTaskFindByKey(pMsg.glblCmpyCd.getValue(),svcTaskNum);
        if (SVC_TASK_STS.COMPLETED.equals(svcTask.svcTaskStsCd.getValue()) || SVC_TASK_STS.CLOSED.equals(svcTask.svcTaskStsCd.getValue())) {
            if (!updateModProcStsForChargable(pMsg, this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue())) {
                return;
            }
        }
        // END 2023/02/28 T.Wada    [QC#61231, ADD]

        // mod start 2017/02/13 CSA Defect#17109
      // START 2017/06/16 K.Kitachi [QC#19081, MOD]
      if (SVC_TASK_STS.COMPLETED.equals(this.resultFsrStsCd) || SVC_TASK_STS.PENDING_CHARGE.equals(this.resultFsrStsCd) || SVC_TASK_STS.CLOSED.equals(this.resultFsrStsCd)) {
      // END 2017/06/16 K.Kitachi [QC#19081, MOD]
          if (!insertFsrChrg(pMsg)) {
              return;
          }
          // START 2022/05/25 K.Kitachi [QC#59987, ADD]
          // START 2023/02/28 T.Wada    [QC#61231, DEL]
//          if (!updateModProcStsForChargable(pMsg, this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue())) {
//              return;
//          }
          // END 2023/02/28 T.Wada    [QC#61231, DEL]
          // END 2022/05/25 K.Kitachi [QC#59987, ADD]
      }
      // mod end 2017/02/13 CSA Defect#17109

      // add start 2017/02/13 CSA Defect#17109
      // START 2017/06/16 K.Kitachi [QC#19081, MOD]
      if (SVC_TASK_STS.COMPLETED.equals(this.resultFsrStsCd) || SVC_TASK_STS.CLOSED.equals(this.resultFsrStsCd)) {
      // END 2017/06/16 K.Kitachi [QC#19081, MOD]
          updateStsForDebrief(pMsg);
      }
      // add end 2017/02/13 CSA Defect#17109
      //Add Start 2018/11/28 K.Fujimoto QC#28647
      // (12) Update Inventory
      if(!updateInvtyForDebrief(pMsg,this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue())){
          return;
      }
      //Add End   2018/11/28 K.Fujimoto QC#28647
    }

    private boolean init(NSZC005001PMsg pMsg, ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(pMsg);
        this.onbatchType = onBatchType;
        this.sysTs = ZYPDateUtil.getCurrentSystemTime(FORMAT_TS);
        this.sysDtTm = this.sysTs.substring(0, LEN_DT_TM);
        this.sysDt = this.sysDtTm.substring(0, LEN_DT);
        this.sysTm = this.sysDtTm.substring(LEN_DT, this.sysDtTm.length());
        this.fsrCompleteFlg = false;
        this.completionBean = new CompletionBean();
        this.resultFsrStsCd = null;

        if (!inputCheck(pMsg)) {
            return false;
        }

        formatTmItem(pMsg);

        // mod start 2016/02/08 CSA Defect#2863
        if (!frceUpdFlg) {
            CCYTMsg ccyTMsg = getCcyFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.invCcyCd.getValue());
            if (!checkRtnCodeForSearch(pMsg, ccyTMsg, new CCYTMsg())) {
                return false;
            }
            // mod start 2015/12/18 CSA Defect#978
            int stdCcyAftDeclPntDigitNum = getStdCcyDigit(pMsg.glblCmpyCd.getValue());

            this.completionBean.setAcctArthTpCd(ccyTMsg.acctArthTpCd.getValue());
            this.completionBean.setAftDeclPntDigitNum(ccyTMsg.aftDeclPntDigitNum.getValue());
            this.completionBean.setStdCcyAftDeclPntDigitNum(BigDecimal.valueOf(stdCcyAftDeclPntDigitNum));
            // mod end 2015/12/18 CSA Defect#978
        }
        FSR_VISITTMsg fsrVisitTMsg = new FSR_VISITTMsg();
        if (!frceUpdFlg) {
            fsrVisitTMsg = getFsrVisitFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), pMsg.fsrVisitNum.getValue());
        } else {
            FSR_VISITTMsgArray fsrVisitTMsgArray = getFsrVisit(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), pMsg.xxVisitTaskList.no(0).svcTaskNum.getValue());
            fsrVisitTMsg = fsrVisitTMsgArray.no(0);
        }
        // mod end 2016/02/08 CSA Defect#2863
        if (!checkRtnCodeForSearch(pMsg, fsrVisitTMsg, new FSR_VISITTMsg())) {
            return false;
        }

        this.completionBean.setFsrVisitTMsg(fsrVisitTMsg);

        SVC_TASKTMsg svcTaskTMsg = getSvcTaskFindByKey(pMsg.glblCmpyCd.getValue(), fsrVisitTMsg.svcTaskNum.getValue());
        if (!checkRtnCodeForSearch(pMsg, svcTaskTMsg, new SVC_TASKTMsg())) {
            return false;
        }
        this.completionBean.setSvcTaskTMsg(svcTaskTMsg);

        SVC_BILL_TPTMsg svcBillTpTMsg = getSvcBillTpFindByKey(pMsg.glblCmpyCd.getValue(), this.completionBean.getSvcTaskTMsg().svcBillTpCd.getValue());
        if (!checkRtnCodeForSearch(pMsg, svcBillTpTMsg, new SVC_BILL_TPTMsg())) {
            return false;
        }
        this.completionBean.setSvcBillTpTMsg(svcBillTpTMsg);
        DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = getDsSvcCallTpFindByKey(pMsg.glblCmpyCd.getValue(), this.completionBean.getSvcTaskTMsg().dsSvcCallTpCd.getValue());
        if (!checkRtnCodeForSearch(pMsg, dsSvcCallTpTMsg, new DS_SVC_CALL_TPTMsg())) {
            return false;
        }
        this.completionBean.setDsSvcCallTpTMsg(dsSvcCallTpTMsg);

        String intgProdCatgCdDrum = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_INTG_PROD_CATG_CD_DRUM, pMsg.glblCmpyCd.getValue());
        this.completionBean.setIntgProdCatgCdDrum(intgProdCatgCdDrum);

        // START   2019/11/18 K.Fujimoto [QC#54391, ADD]
        this.completionBean.setOnsSprtCall(isOnsSprtCall(pMsg, fsrVisitTMsg.svcTaskNum.getValue()));
        // END     2019/11/18 K.Fujimoto [QC#54391, ADD]
        return true;
    }

    private boolean initChargable(NSZC005001PMsg pMsg, ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(pMsg);
        this.onbatchType = onBatchType;
        this.sysTs = ZYPDateUtil.getCurrentSystemTime(FORMAT_TS);
        this.sysDtTm = this.sysTs.substring(0, LEN_DT_TM);
        this.sysDt = this.sysDtTm.substring(0, LEN_DT);
        this.sysTm = this.sysDtTm.substring(LEN_DT, this.sysDtTm.length());
        this.fsrCompleteFlg = false;
        this.completionBean = new CompletionBean();
        this.resultFsrStsCd = null;

        if (!inputCheckChargableMode(pMsg)) {
            return false;
        }

        // mod start 2016/02/08 CSA Defect#2863
        if (!frceUpdFlg) {
            CCYTMsg ccyTMsg = getCcyFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.invCcyCd.getValue());
            if (!checkRtnCodeForSearch(pMsg, ccyTMsg, new CCYTMsg())) {
                return false;
            }
            // mod start 2015/12/18 CSA Defect#978
            int stdCcyAftDeclPntDigitNum = getStdCcyDigit(pMsg.glblCmpyCd.getValue());

            this.completionBean.setAcctArthTpCd(ccyTMsg.acctArthTpCd.getValue());
            this.completionBean.setAftDeclPntDigitNum(ccyTMsg.aftDeclPntDigitNum.getValue());
            this.completionBean.setStdCcyAftDeclPntDigitNum(BigDecimal.valueOf(stdCcyAftDeclPntDigitNum));
            // mod end 2015/12/18 CSA Defect#978
        }
        // end start 2016/02/08 CSA Defect#2863
        String intgProdCatgCdDrum = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_INTG_PROD_CATG_CD_DRUM, pMsg.glblCmpyCd.getValue());
        this.completionBean.setIntgProdCatgCdDrum(intgProdCatgCdDrum);

        return true;
    }
    private boolean initInstallCheck(NSZC005001PMsg pMsg, ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(pMsg);
        this.onbatchType = onBatchType;
        this.sysTs = ZYPDateUtil.getCurrentSystemTime(FORMAT_TS);
        this.sysDtTm = this.sysTs.substring(0, LEN_DT_TM);
        this.sysDt = this.sysDtTm.substring(0, LEN_DT);
        this.sysTm = this.sysDtTm.substring(LEN_DT, this.sysDtTm.length());
        this.fsrCompleteFlg = false;
        this.completionBean = new CompletionBean();
        this.resultFsrStsCd = null;

        if (!inputCheckIstallCheckMode(pMsg)) {
            return false;
        }
        FSRTMsg fsrTMsg = getFsrFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (!checkRtnCodeForSearch(pMsg, fsrTMsg, new FSRTMsg())) {
            return false;
        }
        // Add Start 2019/01/10 K.Fujimoto QC#26861
        this.currentFsrSts = fsrTMsg.fsrStsCd.getValue();
        // Add End   2019/01/10 K.Fujimoto QC#26861
        CCYTMsg ccyTMsg = getCcyFindByKey(pMsg.glblCmpyCd.getValue(), fsrTMsg.invCcyCd.getValue());
        if (!checkRtnCodeForSearch(pMsg, ccyTMsg, new CCYTMsg())) {
            return false;
        }
        int stdCcyAftDeclPntDigitNum = getStdCcyDigit(pMsg.glblCmpyCd.getValue());

        this.completionBean.setAcctArthTpCd(ccyTMsg.acctArthTpCd.getValue());
        this.completionBean.setAftDeclPntDigitNum(ccyTMsg.aftDeclPntDigitNum.getValue());
        this.completionBean.setStdCcyAftDeclPntDigitNum(BigDecimal.valueOf(stdCcyAftDeclPntDigitNum));

        if (pMsg.xxFsrIstlChkList.getValidCount() > 0) {
            String svcTaskNum = pMsg.xxFsrIstlChkList.no(0).svcTaskNum.getValue();
            SVC_TASKTMsg svcTaskTMsg = getSvcTaskFindByKey(pMsg.glblCmpyCd.getValue(), svcTaskNum);
            if (!checkRtnCodeForSearch(pMsg, svcTaskTMsg, new SVC_TASKTMsg())) {
                return false;
            }
            this.completionBean.setSvcTaskTMsg(svcTaskTMsg);
            FSR_VISITTMsgArray fsrVisitTmsgArray = getFsrVisit(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), svcTaskNum);
            if (fsrVisitTmsgArray == null || fsrVisitTmsgArray.getValidCount() == 0) {
                setErrMsg(pMsg, NSZM0182E);
                    return false;
            }
            this.completionBean.setFsrVisitTMsg(fsrVisitTmsgArray.no(0));
            DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = getDsSvcCallTpFindByKey(pMsg.glblCmpyCd.getValue(), this.completionBean.getSvcTaskTMsg().dsSvcCallTpCd.getValue());
            if (!checkRtnCodeForSearch(pMsg, dsSvcCallTpTMsg, new DS_SVC_CALL_TPTMsg())) {
                return false;
            }
            this.completionBean.setDsSvcCallTpTMsg(dsSvcCallTpTMsg);
        }

        return true;
    }

    private boolean checkRecall(NSZC005001PMsg pMsg) {
        if (pMsg.xxMtrReadList.getValidCount() < 1 || ZYPConstant.FLG_OFF_N.equals(getMtrReqMdlFlg(pMsg))) {
            return true;
        }
        BigDecimal totMtrCntY = BigDecimal.ZERO;
        BigDecimal totMtrCntN = BigDecimal.ZERO;
        BigDecimal totMtrCnt = BigDecimal.ZERO;
        BigDecimal prevTotMtrCntY = BigDecimal.ZERO;
        BigDecimal prevTotMtrCntN = BigDecimal.ZERO;
        BigDecimal prevTotMtrCnt = BigDecimal.ZERO;
        boolean existsPre = true;
        BigDecimal startSvcPhysMtrPk = BigDecimal.ZERO;
        String startMtrLbCd = "";
        boolean outRead = false;

        for (int i = 0; i < pMsg.xxMtrReadList.getValidCount(); i++) {
            NSZC005001_xxMtrReadListPMsg dtlPMsg = pMsg.xxMtrReadList.no(i);

            Map<String, Object> totMtrFlg = getTotMtrFlg(pMsg, dtlPMsg);
            if (totMtrFlg == null) {
                setErrMsg(pMsg, NSZM0726E);
                return false;
            }
            // mod start 2018/06/29 QC#26795
            setValue(dtlPMsg.mtrLbCd, (String) totMtrFlg.get("MTR_LB_CD"));
            setValue(dtlPMsg.svcPhysMtrPk, (BigDecimal) totMtrFlg.get("SVC_PHYS_MTR_PK"));

            if (i == 0) {
                startSvcPhysMtrPk = dtlPMsg.svcPhysMtrPk.getValue();
                startMtrLbCd = dtlPMsg.mtrLbCd.getValue();
            } else {
                if (outRead) {
                    continue;
                }
                if (startSvcPhysMtrPk.compareTo(dtlPMsg.svcPhysMtrPk.getValue()) == 0 || startMtrLbCd.equals(dtlPMsg.mtrLbCd.getValue())) {
                    outRead = true;
                    continue;
                }
            }

            BigDecimal mtrCnt = dtlPMsg.mtrCnt.getValue();
            if (DS_MTR_READ_TP.ROLLOVER.equals(dtlPMsg.dsMtrReadTpCd.getValue())) {
                if (hasValue((BigDecimal) totMtrFlg.get("ROLL_OVER_CNT"))) {
                    mtrCnt = mtrCnt.add((BigDecimal) totMtrFlg.get("ROLL_OVER_CNT"));
                }
            }
            if (ZYPConstant.FLG_ON_Y.equals((String) totMtrFlg.get("TOT_MTR_FLG"))) {
                totMtrCntY = totMtrCntY.add(mtrCnt);
            } else {
                totMtrCntN = totMtrCntN.add(mtrCnt);
            }
            // mod end 2018/06/29 QC#26795

            Map<String, Object> preTotMtrFlg = getPrevTotMtr(pMsg, dtlPMsg);
            if (preTotMtrFlg == null) {
                existsPre = false;
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals((String) preTotMtrFlg.get("TOT_MTR_FLG"))) {
                prevTotMtrCntY = prevTotMtrCntY.add((BigDecimal) preTotMtrFlg.get("READ_MTR_CNT"));
            } else {
                prevTotMtrCntN = prevTotMtrCntN.add((BigDecimal) preTotMtrFlg.get("READ_MTR_CNT"));
            }
        }

        // START 2019/11/18 K.Fujimoto [QC#54391, ADD]
        //In case the call is L2 process, the call type can't be change re-call.
        if (this.completionBean.isOnsSprtCall()) {
            return true;
        }
        // END   2019/11/18 K.Fujimoto [QC#54391, ADD]

        if (!existsPre) {
            return true;
        }

        if (totMtrCntN.compareTo(totMtrCntY) <= 0) {
            totMtrCnt = totMtrCntY;
        } else {
            totMtrCnt = totMtrCntN;
        }
        if (prevTotMtrCntN.compareTo(prevTotMtrCntY) <= 0) {
            prevTotMtrCnt = prevTotMtrCntY;
        } else {
            prevTotMtrCnt = prevTotMtrCntN;
        }

        // add start 2018/06/29 QC#26795
        // adjustment
        if (prevTotMtrCnt.compareTo(totMtrCnt) > 0) {
            return true;
        }
        // add end 2018/06/29 QC#26795

        BigDecimal recallCnt = getRecallCnt(pMsg);
        if (hasValue(recallCnt)) {
            if (prevTotMtrCnt.add(recallCnt).compareTo(totMtrCnt) > 0) {
                if (!updateSvcTaskCallTp(pMsg)) {
                    return false;
                }
                // START 2019/03/28 K.Fujimoto [QC#30546, ADD]
                if (!updateSvcTaskBillTp(pMsg)) {
                    return false;
                }
                // END 2019/03/28 K.Fujimoto [QC#30546, ADD]
            }
        }
        return true;
    }

    private boolean meterCountValidation(NSZC005001PMsg pMsg) {
        // START 2022/12/01 K.Kitachi [QC#60882, DEL]
//        if (pMsg.xxMtrReadList.getValidCount() < 1 || ZYPConstant.FLG_OFF_N.equals(getMtrReqMdlFlg(pMsg))) {
//            return true;
//        }
        // END 2022/12/01 K.Kitachi [QC#60882, DEL]
        // Add Start 2019/10/24 K.Fujimoto QC#53441
        setMtrReadTp(pMsg);
        // Add End   2019/10/24 K.Fujimoto QC#53441
        // add start 2019/08/09 QC#52105
        // mod start 2019/11/27 QC#54837
        //if (!updateSvcPhysMtrReadToInvld(pMsg)) {
        //    setErrMsg(pMsg, NSZM0368E);
        //    return false;
        //}
        List<BigDecimal> svcPhysMtrReadGrpSqList = getSvcPhysMtrReadGrpSq(pMsg, null);
        // mod end 2019/11/27 QC#54837
        // add end 2019/08/09 QC#52105

        // mod start 2016/07/06 CSA Defect#11284
        List<Integer> startCntList = getStartCntList(pMsg);
        // Mod Start 01/17/2017 <QC#16512>
        // mod start 2018/06/29 QC#26795
        //boolean isAdjErrFlg = false;
        // START 2020/07/29 K.Kitachi [QC#57410, ADD]
        boolean isInMeterReadSkip = isMeterReadSkip(pMsg.arvMtrVisTpCd.getValue());
        boolean isOutMeterReadSkip = isMeterReadSkip(pMsg.cpltMtrVisTpCd.getValue());
        // END 2020/07/29 K.Kitachi [QC#57410, ADD]
        for (int startCnt : startCntList) {
            // START 2020/07/29 K.Kitachi [QC#57410, ADD]
            String dsTestCopyClsCd = pMsg.xxMtrReadList.no(startCnt).dsTestCopyClsCd.getValue();
            if (isInMeterRead(dsTestCopyClsCd) && isInMeterReadSkip) {
                continue;
            }
            if (isOutMeterRead(dsTestCopyClsCd) && isOutMeterReadSkip) {
                continue;
            }
            // END 2020/07/29 K.Kitachi [QC#57410, ADD]
            NSZC010001PMsg apiPMsg = setNszc0100PMsg(pMsg, startCnt);
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                return false;
            }
            //if (isAdjErrFlg) {
            //    setInvldMtrEntryPrm(apiPMsg);
            //    new NSZC010001().execute(apiPMsg, onbatchType);
            //    if (!checkErrMsg(pMsg, apiPMsg)) {
            //        return false;
            //    }
            //    continue;
            //}
            new NSZC010001().execute(apiPMsg, onbatchType);
            //Mod Start 06/10/2020 QC#57126
            //if (isVldMtrEntry(pMsg, apiPMsg)) {
            if (needRetryMeterUpdate(pMsg, apiPMsg)) {
                deleteSvcPhysMtrReadByGrpSq(apiPMsg);
                String svcTaskNum = null;
                if (pMsg.xxVisitTaskList.getValidCount() > 0) {
                    svcTaskNum = pMsg.xxVisitTaskList.no(0).svcTaskNum.getValue();
                }
                // del start 2020/03/03 QC#56123
                //String invldMtrEntryErrMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_INVLD_MTR_ENTRY_ERR_MSG, pMsg.glblCmpyCd.getValue());
                //insertSvcMemo(pMsg, svcTaskNum, invldMtrEntryErrMsg);
                // del end 2020/03/03 QC#56123
                if (hasOnlyWarn(apiPMsg)) {
                    setValue(apiPMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                    apiPMsg.xxMsgId.clear();
                    for (int i = 0; i < apiPMsg.xxMsgIdList.getValidCount(); i++) {
                        apiPMsg.xxMsgIdList.no(i).clear();
                    }
                    apiPMsg.xxMsgIdList.setValidCount(0);
                } else {
                    setInvldMtrEntryPrm(apiPMsg);
                }
                new NSZC010001().execute(apiPMsg, onbatchType);
                //isAdjErrFlg = true;
            }

            if (!checkErrMsgExcludeWarn(pMsg, apiPMsg)) {
                return false;
            }
            //Mod End 06/10/2020 QC#57126
        }
        // mod end 2018/06/29 QC#26795
        // Mod End   01/17/2017 <QC#16512>
        // mod end 2016/07/06 CSA Defect#11284

        // add start 2019/11/27 QC#54837
        if (!updateSvcPhysMtrReadToInvld(pMsg, svcPhysMtrReadGrpSqList)) {
            setErrMsg(pMsg, NSZM0368E);
            return false;
        }
        // add end 2019/11/27 QC#54837

        // add start 2019/08/09 QC#52105
        // del start 2019/11/27 QC#54837
        //if (existsAdjMtrEntry(pMsg)) {
        //    if (!updateSvcPhysMtrReadToInvld(pMsg)) {
        //        setErrMsg(pMsg, NSZM0368E);
        //        return false;
        //    }
        //}
        // del end 2019/11/27 QC#54837
        // add end 2019/08/09 QC#52105

        // START 2020/07/29 K.Kitachi [QC#57410, ADD]
        if (!updateSvcTaskToMtrVisTp(pMsg)) {
            return false;
        }
        // END 2020/07/29 K.Kitachi [QC#57410, ADD]

        return true;
    }

    // Mod Start 06/10/2020 QC#56811
    // Mod Start 01/17/2017 <QC#16512>
    private boolean needRetryMeterUpdate(NSZC005001PMsg pMsg, EZDPMsg apiPMsg) {
        if (!S21ApiUtil.isXxMsgId(apiPMsg)) {
            return false;
        }
        List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
        for (String xxMsgId : xxMsgIdList) {
            // mod start 2018/06/29 QC#26795
            // mod start 2020/03/03 QC#56123
            //if (NSZM0541E.equals(xxMsgId) || NSZM1335W.equals(xxMsgId)) {
            //if (NSZM0541E.equals(xxMsgId) || NSZM1370E.equals(xxMsgId) || NSZM1312E.equals(xxMsgId) || xxMsgId.endsWith("W")) {
            // mod end 2020/03/03 QC#56123
            // mod end 2018/06/29 QC#26795
            //    return true;
            //}
            //return false;
            if (!NSZM0541E.equals(xxMsgId) && !NSZM1370E.equals(xxMsgId) && !NSZM1312E.equals(xxMsgId) && !xxMsgId.endsWith("W")) {
                return false;
            }
        }
        return true;
    }

    private boolean hasOnlyWarn(NSZC010001PMsg apiPMsg) {
        List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
        for (String xxMsgId : xxMsgIdList) {
            if (xxMsgId.endsWith("E")) {
                return false;
            }
        }
        return true;
    }
    // Mod End 06/10/2020 QC#56811

    private void deleteSvcPhysMtrReadByGrpSq(NSZC010001PMsg apiPMsg) {
        if (apiPMsg.A.getValidCount() == 0) {
            return;
        }
        if (!hasValue(apiPMsg.A.no(0).svcPhysMtrReadGrpSq)) {
            return;
        }
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("007");
        inMsg.setConditionValue("glblCmpyCd01", apiPMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcPhysMtrReadGrpSq01", apiPMsg.A.no(0).svcPhysMtrReadGrpSq.getValue());
        SVC_PHYS_MTR_READTMsgArray delMsgArray = (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);
        for (int i = 0; i < delMsgArray.getValidCount(); i++) {
            // START 2017/08/04 K.Kitachi [QC#20255, MOD]
//            S21ApiTBLAccessor.remove(delMsgArray.no(i));
            S21ApiTBLAccessor.logicalRemove(delMsgArray.no(i));
            // END 2017/08/04 K.Kitachi [QC#20255, MOD]
        }
    }

    private void setInvldMtrEntryPrm(NSZC010001PMsg apiPMsg) {
        setValue(apiPMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ADJUSTMENT);
        setValue(apiPMsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
        for (int i = 0; i < apiPMsg.A.getValidCount(); i++) {
            setValue(apiPMsg.A.no(i).vldMtrFlg, ZYPConstant.FLG_ON_Y);
            // Add Start 2017/11/22 QC#22073
            setValue(apiPMsg.A.no(i).dsMtrReadTpCd, DS_MTR_READ_TP.ADJUSTMENT);
            // Add End 2017/11/22 QC#22073
            apiPMsg.A.no(i).svcPhysMtrReadPk.clear();
            apiPMsg.A.no(i).svcPhysMtrReadGrpSq.clear();
            apiPMsg.A.no(i).testMtrCnt.clear();
        }
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            apiPMsg.xxMsgId.clear();
            for (int i = 0; i < apiPMsg.xxMsgIdList.getValidCount(); i++) {
                apiPMsg.xxMsgIdList.no(i).clear();
            }
            apiPMsg.xxMsgIdList.setValidCount(0);
        }
    }
    // Mod End   01/17/2017 <QC#16512>

    // mod start 2016/07/06 CSA Defect#11284
    private List<Integer> getStartCntList(NSZC005001PMsg pMsg) {
        List<Integer> startCntList = new ArrayList<Integer>();
        BigDecimal svcPhysMtrPk = BigDecimal.ZERO;
        if (hasValue(pMsg.xxMtrReadList.no(0).svcPhysMtrPk)) {
            svcPhysMtrPk = pMsg.xxMtrReadList.no(0).svcPhysMtrPk.getValue();
        }
        String mtrLbCd = "";
        if (hasValue(pMsg.xxMtrReadList.no(0).mtrLbCd)) {
            mtrLbCd = pMsg.xxMtrReadList.no(0).mtrLbCd.getValue();
        }

        for (int i = 0; i < pMsg.xxMtrReadList.getValidCount(); i++) {
            if (svcPhysMtrPk.compareTo(pMsg.xxMtrReadList.no(i).svcPhysMtrPk.getValue()) == 0 || mtrLbCd.equals(pMsg.xxMtrReadList.no(i).mtrLbCd.getValue())) {
                startCntList.add(i);
            }
            // Add Start 2019/08/30 K.Fujimoto QC#52128
            BigDecimal mtrCnt = pMsg.xxMtrReadList.no(i).mtrCnt.getValue();
            int cntrDigitNum = getCntrDigitNum(pMsg, pMsg.xxMtrReadList.no(i).mtrLbCd.getValue());
            if (cntrDigitNum > 0) {
                if (mtrCnt.compareTo(getMaxMtrCnt(cntrDigitNum)) >= 0) {
                    FSR_VISITTMsg tMsg = this.completionBean.getFsrVisitTMsg();
                    if (tMsg != null && !insertSvcMemo(pMsg, tMsg.svcTaskNum.getValue(), S21MessageFunc.clspGetMessage(NSZM1295E))) {
                        startCntList.clear();
                        return startCntList;
                    }
                    this.isDebreifError = true;
                    startCntList.clear();
                    return startCntList;
                }
            }
            // Add End   2019/08/30 K.Fujimoto QC#52128
        }
        return startCntList;
    }
    // mod end 2016/07/06 CSA Defect#11284

    // Add Start 2019/08/30 K.Fujimoto QC#52128
    private int getCntrDigitNum(NSZC005001PMsg pMsg, String mtrLbCd) {
        BigDecimal cntrDigitNum = getSvcPhysMtrDigitNum(pMsg, mtrLbCd);
        if (hasValue(cntrDigitNum)) {
            return cntrDigitNum.intValue();
        }
        return 0;
    }
    
    private BigDecimal getMaxMtrCnt(int cntrDigitNum) {
        double maxMtrCnt = 0;
        if (cntrDigitNum > 0) {
            maxMtrCnt = Math.pow(10, cntrDigitNum);
        }
        return BigDecimal.valueOf(maxMtrCnt);
    }
    // Add End   2019/08/30 K.Fujimoto QC#52128
    
    // mod start 2016/07/06 CSA Defect#11284
    private NSZC010001PMsg setNszc0100PMsg(NSZC005001PMsg pMsg, int startCnt) {
        // Call Meter Update API
        NSZC010001PMsg apiPMsg = new NSZC010001PMsg();
        // Set Header
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.mtrReadSrcTpCd, ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_DISPT_MTR_READ_SRC_TP, pMsg.glblCmpyCd.getValue()));
        // mod start 2017/09/07 QC#15134
        //setValue(apiPMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
        //QC#53441 Mod Start
        if (hasValue(pMsg.xxMtrReadList.no(0).dsMtrReadTpCd) && startCnt == 0)  {
        //QC#53441 Mod End
            setValue(apiPMsg.dsMtrReadTpCd, pMsg.xxMtrReadList.no(0).dsMtrReadTpCd);
        } else {
            setValue(apiPMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
        }
        // mod end 2017/09/07 QC#15134
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValue(apiPMsg.fsrNum, pMsg.fsrNum);
        setValue(apiPMsg.fsrVisitNum, pMsg.fsrVisitNum);
        setValue(apiPMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
        setValue(apiPMsg.xxStartReadFlg, ZYPConstant.FLG_OFF_N);
        setValue(apiPMsg.dsMtrReadTpGrpCd, pMsg.xxMtrReadList.no(0).dsMtrReadTpGrpCd);
        // START 2016/10/05 T.Kanasaka [QC#12274, ADD]
        if (startCnt == 0) {
            setValue(apiPMsg.dsTestCopyClsCd, DS_TEST_COPY_CLS.TEST_COPY_IN);
        } else {
            setValue(apiPMsg.dsTestCopyClsCd, DS_TEST_COPY_CLS.TEST_COPY_OUT);
        }
        setValue(apiPMsg.svcTaskNum, this.completionBean.getSvcTaskTMsg().svcTaskNum);
        // END 2016/10/05 T.Kanasaka [QC#12274, ADD]

        BigDecimal svcPhysMtrPk = BigDecimal.ZERO;
        if (hasValue(pMsg.xxMtrReadList.no(0).svcPhysMtrPk)) {
            svcPhysMtrPk = pMsg.xxMtrReadList.no(0).svcPhysMtrPk.getValue();
        }
        String mtrLbCd = "";
        if (hasValue(pMsg.xxMtrReadList.no(0).mtrLbCd)) {
            mtrLbCd = pMsg.xxMtrReadList.no(0).mtrLbCd.getValue();
        }
        int idx = 0;
        for (int i = startCnt; i < pMsg.xxMtrReadList.getValidCount(); i++) {
            NSZC005001_xxMtrReadListPMsg dtlPMsg = pMsg.xxMtrReadList.no(i);
            if (i > startCnt && (svcPhysMtrPk.compareTo(dtlPMsg.svcPhysMtrPk.getValue()) == 0 || mtrLbCd.equals(dtlPMsg.mtrLbCd.getValue()))) {
                break;
            }

            // START 2020/07/29 K.Kitachi [QC#57410, ADD]
            if (hasValue(dtlPMsg.dsTestCopyClsCd)) {
                setValue(apiPMsg.dsTestCopyClsCd, dtlPMsg.dsTestCopyClsCd);
            }
            // END 2020/07/29 K.Kitachi [QC#57410, ADD]

            BigDecimal inMtrCnt = BigDecimal.ZERO;
            List<Map<String, Object>> svcPhysMtrReadList = getSvcPhysMtrReadList(pMsg, dtlPMsg);
            if (svcPhysMtrReadList != null && svcPhysMtrReadList.size() > 0) {
                inMtrCnt = (BigDecimal) svcPhysMtrReadList.get(0).get("READ_MTR_CNT");
            }
            // del start 2017/09/07 QC#15134
//            if (dtlPMsg.mtrCnt.getValue().compareTo(inMtrCnt) < 0) {
//                setErrMsg(pMsg, NSZM0760E);
//            }
            // del end 2017/09/07 QC#15134
            BigDecimal testCnt = BigDecimal.ZERO;
            if (hasValue(dtlPMsg.mtrTestCnt)) {
                testCnt = dtlPMsg.mtrTestCnt.getValue();
            } else if (svcPhysMtrReadList != null && svcPhysMtrReadList.size() > 0) {
                testCnt = dtlPMsg.mtrCnt.getValue().subtract(inMtrCnt);
            }

            // del start 2019/08/09 QC#52105
            //if (svcPhysMtrReadList.size() > 1) {
            //    if (!deleteSvcPhysMtrRead(pMsg, svcPhysMtrReadList)) {
            //        setErrMsg(pMsg, NSZM0368E);
            //    }
            //}
            // del end 2019/08/09 QC#52105

            // Set Detail
            // add start 2017/09/07 QC#15134
            if (hasValue(dtlPMsg.dsMtrReadTpCd)) {
                //QC#53441 Mod Start
                if(apiPMsg.dsTestCopyClsCd.getValue().equals(DS_TEST_COPY_CLS.TEST_COPY_OUT)) {
                    setValue(apiPMsg.A.no(idx).dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
                } else {
                    setValue(apiPMsg.A.no(idx).dsMtrReadTpCd, dtlPMsg.dsMtrReadTpCd);
                }
                //QC#53441 Mod End
                // add start 2018/06/29 QC#26795
                if (DS_MTR_READ_TP.ROLLOVER.equals(dtlPMsg.dsMtrReadTpCd.getValue())) {
                    setValue(apiPMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                }
                // add end 2018/06/29 QC#26795
            }
            // add end 2017/09/07 QC#15134
            setValue(apiPMsg.A.no(idx).mtrReadDt, dtlPMsg.mtrReadDt);
            setValue(apiPMsg.A.no(idx).rgtnMtrDt, dtlPMsg.mtrDt);
            setValue(apiPMsg.A.no(idx).readMtrCnt, dtlPMsg.mtrCnt);
            setValue(apiPMsg.A.no(idx).testMtrCnt, testCnt);
            setValue(apiPMsg.A.no(idx).rgtnUsrId, pMsg.userId);
            setValue(apiPMsg.A.no(idx).estFlg, ZYPConstant.FLG_OFF_N);
            setValue(apiPMsg.A.no(idx).invProcFlg, ZYPConstant.FLG_OFF_N);
            setValue(apiPMsg.A.no(idx).svcPhysMtrPk, dtlPMsg.svcPhysMtrPk);
            setValue(apiPMsg.A.no(idx).vldMtrFlg, ZYPConstant.FLG_ON_Y);
            setValue(apiPMsg.A.no(idx).mdlMtrLbCd, dtlPMsg.mtrLbCd);
            idx++;
        }
        apiPMsg.A.setValidCount(idx);
        return apiPMsg;
    }
    // mod end 2016/07/06 CSA Defect#11284

    private boolean checkInterruptTask(NSZC005001PMsg pMsg) {
        if (pMsg.xxTmEventList.getValidCount() < 1) {
            this.completionBean.setSvcLborTmNum(BigDecimal.ZERO);
            return true;
        }
        // Sort svcTmEventFromDt, svcTmEventFromTm
        List<NSZC005001_xxTmEventListPMsg> sortList = new ArrayList<NSZC005001_xxTmEventListPMsg>();
        for (int i = 0; i < pMsg.xxTmEventList.getValidCount(); i++) {
            NSZC005001_xxTmEventListPMsg dtlPMsg = (NSZC005001_xxTmEventListPMsg) pMsg.xxTmEventList.no(i).clone();
            sortList.add(dtlPMsg);
        }
        Collections.sort(sortList, new Comparator<NSZC005001_xxTmEventListPMsg>() {
            public int compare(NSZC005001_xxTmEventListPMsg line1, NSZC005001_xxTmEventListPMsg line2) {
                int compared;
                compared = line1.svcTmEventFromDt.getValue().compareTo(line2.svcTmEventFromDt.getValue());
                if (compared != 0) {
                    return compared;
                }
                compared = line1.svcTmEventFromTm.getValue().compareTo(line2.svcTmEventFromTm.getValue());
                if (compared != 0) {
                    return compared;
                }
                return 0;
            }
        });

        // Changes event of time overlap
        for (int i = 0; i < sortList.size(); i++) {
            NSZC005001_xxTmEventListPMsg dtlPMsg = sortList.get(i);

            SVC_TM_EVENTTMsg svcTmEvntTMsg = getSvcTmEventFindByKey(pMsg.glblCmpyCd.getValue(), dtlPMsg.svcTmEventCd.getValue());
            if (!checkRtnCodeForSearch(pMsg, svcTmEvntTMsg, new SVC_TM_EVENTTMsg())) {
                return false;
            }
            this.completionBean.setPhoneFixFlg(svcTmEvntTMsg.phoneFixFlg.getValue());

            if (ZYPConstant.FLG_OFF_N.equals(svcTmEvntTMsg.inclLborTmFlg.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(svcTmEvntTMsg.inclTrvlTmFlg.getValue())) {
                    this.completionBean.addTmEventListTrvl(dtlPMsg);
                } else {
                    this.completionBean.addTmEventListOther(dtlPMsg);
                }
                continue;
            }

            String laborFromDt = dtlPMsg.svcTmEventFromDt.getValue();
            String laborFromTm = dtlPMsg.svcTmEventFromTm.getValue();
            String laborToDt = dtlPMsg.svcTmEventToDt.getValue();
            String laborToTm = dtlPMsg.svcTmEventToTm.getValue();

            List<SVC_SUPPL_TASKTMsg> supplTaskTMsgList = getSupplTask(pMsg, dtlPMsg);

            for (SVC_SUPPL_TASKTMsg supplTaskTMsg : supplTaskTMsgList) {
                String itrptFromDt = supplTaskTMsg.svcSupplFromDt.getValue();
                String itrptFromTm = supplTaskTMsg.svcSupplFromTm.getValue();
                String itrptToDt = supplTaskTMsg.svcSupplToDt.getValue();
                String itrptToTm = supplTaskTMsg.svcSupplToTm.getValue();
                String itrptFromDtTm = itrptFromDt + itrptFromTm;
                String itrptToDtTm = itrptToDt + itrptToTm;

                if ((laborFromDt + laborFromTm).compareTo(itrptFromDtTm) < 0) {
                    if ((laborToDt + laborToTm).compareTo(itrptFromDtTm) <= 0) {
                        NSZC005001_xxTmEventListPMsg changeDtlPMsg = new NSZC005001_xxTmEventListPMsg();
                        EZDMsg.copy(dtlPMsg, null, changeDtlPMsg, null);
                        setValue(changeDtlPMsg.svcTmEventFromDt, laborFromDt);
                        setValue(changeDtlPMsg.svcTmEventFromTm, laborFromTm);
                        this.completionBean.addTmEventListLbor(changeDtlPMsg);
                        laborFromDt = null;
                        laborFromTm = null;
                    } else if ((laborToDt + laborToTm).compareTo(itrptToDtTm) <= 0) {
                        NSZC005001_xxTmEventListPMsg changeDtlPMsg = new NSZC005001_xxTmEventListPMsg();
                        EZDMsg.copy(dtlPMsg, null, changeDtlPMsg, null);
                        // START 2021/09/10 R.Cabral [QC#58979, ADD]
                        if (hasValue(laborFromDt)) {
                            setValue(changeDtlPMsg.svcTmEventFromDt, laborFromDt);
                            setValue(changeDtlPMsg.svcTmEventFromTm, laborFromTm);
                        }
                        // END   2021/09/10 R.Cabral [QC#58979, ADD]
                        setValue(changeDtlPMsg.svcTmEventToDt, itrptFromDt);
                        setValue(changeDtlPMsg.svcTmEventToTm, itrptFromTm);
                        this.completionBean.addTmEventListLbor(changeDtlPMsg);
                        laborFromDt = null;
                        laborFromTm = null;
                    } else {
                        NSZC005001_xxTmEventListPMsg changeDtlPMsg = new NSZC005001_xxTmEventListPMsg();
                        EZDMsg.copy(dtlPMsg, null, changeDtlPMsg, null);
                        // START 2021/09/10 R.Cabral [QC#58979, ADD]
                        if (hasValue(laborFromDt)) {
                            setValue(changeDtlPMsg.svcTmEventFromDt, laborFromDt);
                            setValue(changeDtlPMsg.svcTmEventFromTm, laborFromTm);
                        }
                        // END   2021/09/10 R.Cabral [QC#58979, ADD]
                        setValue(changeDtlPMsg.svcTmEventToDt, itrptFromDt);
                        setValue(changeDtlPMsg.svcTmEventToTm, itrptFromTm);
                        this.completionBean.addTmEventListLbor(changeDtlPMsg);
                        laborFromDt = itrptToDt;
                        laborFromTm = itrptToTm;
                    }
                } else {
                    if ((laborToDt + laborToTm).compareTo(itrptToDtTm) <= 0) {
                        laborFromDt = null;
                        laborFromTm = null;
                    } else if ((laborFromDt + laborFromTm).compareTo(itrptToDtTm) <= 0) {
                        laborFromDt = itrptToDt;
                        laborFromTm = itrptToTm;
                    }
                }

                if (!hasValue(laborFromDt)) {
                    break;
                }
            }

            if (hasValue(laborFromDt)) {
                NSZC005001_xxTmEventListPMsg changeDtlPMsg = new NSZC005001_xxTmEventListPMsg();
                EZDMsg.copy(dtlPMsg, null, changeDtlPMsg, null);
                setValue(changeDtlPMsg.svcTmEventFromDt, laborFromDt);
                setValue(changeDtlPMsg.svcTmEventFromTm, laborFromTm);
                this.completionBean.addTmEventListLbor(changeDtlPMsg);
            }
        }

        // Sum Labor Time
        Long totDiffTime = 0L;
        for (int i = 0; i < this.completionBean.getTmEventListLbor().size(); i++) {
            NSZC005001_xxTmEventListPMsg dtlPMsg = this.completionBean.getTmEventListLbor().get(i);

            Date fromDateTime = toDate(dtlPMsg.svcTmEventFromDt.getValue() + dtlPMsg.svcTmEventFromTm.getValue(), FORMAT_DT_TM);
            Date toDateTime = toDate(dtlPMsg.svcTmEventToDt.getValue() + dtlPMsg.svcTmEventToTm.getValue(), FORMAT_DT_TM);

            Long diffTime = 0L;
            if (toDateTime != null && fromDateTime != null) {
                diffTime = toDateTime.getTime() - fromDateTime.getTime();
            }
            totDiffTime = totDiffTime + diffTime;
        }
        BigDecimal svcLborTmNum = BigDecimal.valueOf(totDiffTime / RSP_TM_VAL).setScale(0, RoundingMode.CEILING);

        // add start 2016/02/22 CSA Defect#2695
        // subtract modification time
        // del start 2018/08/27 QC#27882
//        for (int i = 0; i < pMsg.xxTmEventList.getValidCount(); i++) {
//            if (isModification(pMsg.glblCmpyCd.getValue(), pMsg.xxTmEventList.no(i))) {
//                svcLborTmNum = svcLborTmNum.subtract(pMsg.xxTmEventList.no(i).durnTmNum.getValue());
//            }
//        }
        // del end 2018/08/27 QC#27882
        // add end 2016/02/22 CSA Defect#2695

        // START 2017/01/30 [QC#17291, ADD]
        if (svcLborTmNum.compareTo(MAX_VALUE_SVC_LBOR_TM_NUM) > 0) {
            svcLborTmNum = MAX_VALUE_SVC_LBOR_TM_NUM;
        }
        // END   2017/01/30 [QC#17291, ADD]
        this.completionBean.setSvcLborTmNum(svcLborTmNum);

        return true;
    }

    private boolean createFollowUpCall(NSZC005001PMsg pMsg) {
        if (pMsg.xxVisitTaskList.getValidCount() < 1) {
            return true;
        }

        NSZC005001_xxVisitTaskListPMsg dtlPMsg = pMsg.xxVisitTaskList.no(0);

        Map<String, Object> follUpInfo = getFollUpInfo(pMsg, dtlPMsg);
        if (follUpInfo == null) {
            return true;
        }
        String onsSprtCallFlg = (String) follUpInfo.get("ONS_SPRT_CALL_FLG");
        // START 2019/11/18 K.Fujimoto [QC#54391, ADD]
        // In case the call is not L2 call and the correction type is L2-Amazon process only,
        // don't create follow up call. and make the call debrief error.
        if (!checkDebriefError(pMsg, DEBRIEF_CHK_ONS_PROC)) {
            return true;
        }

        if (this.isDebreifError) {
            return true;
        }
        // In case the call is L2 call and the correction type is not L2-Amazon(Onsite Support) process,
        // don't create follow up call. And make the call close.(not error.)
        // In case the call is L2 call and the correction type is L2-Amazon(Onsite Support) process,
        // create follow up call that fsr is new number.
        if (this.completionBean.isOnsSprtCall()) {
            return createFollowUpCallForOnsSprt(pMsg, onsSprtCallFlg);
        }

        // END   2019/11/18 K.Fujimoto [QC#54391, ADD]
        Map<String, Object> prtInfo = getPrtInfo(pMsg);
        if (prtInfo == null) {
            return true;
        }

        SVC_TASKTMsg svcTaskTMsg = this.completionBean.getSvcTaskTMsg();
        FSR_VISITTMsg fsrVisitTMsg = this.completionBean.getFsrVisitTMsg();
        // add start 2017/01/11 CSA Defect#17028
        if (hasValue(fsrVisitTMsg.nextFsrVisitNum)) {
            return true;
        }
        // add end 2017/01/11 CSA Defect#17028

        NSZC045001PMsg apiPMsg = new NSZC045001PMsg();

        // Del Start 2018/03/30 QC#16339
//        // Calculate erlStartTs, lateStartTs
//        if (ZYPConstant.FLG_OFF_N.equals((String) follUpInfo.get("REQ_PRT_FLG"))) {
//            String erlStartTs = null;
//            String lateStartTs = null;
//
//            String erlSysDtFlg = (String) follUpInfo.get("ERL_SYS_DT_FLG");
//            String erlSvcRspTmMnAotFlg = (String) follUpInfo.get("ERL_SVC_RSP_TM_MN_AOT_FLG");
//            BigDecimal erlAddlTmNum = (BigDecimal) follUpInfo.get("ERL_ADDL_TM_NUM");
//            String lateSysDtFlg = (String) follUpInfo.get("LATE_SYS_DT_FLG");
//            String lateSvcRspTmMnAotFlg = (String) follUpInfo.get("LATE_SVC_RSP_TM_MN_AOT_FLG");
//            BigDecimal lateAddlTmNum = (BigDecimal) follUpInfo.get("LATE_ADDL_TM_NUM");
//            BigDecimal repTmUpMnAot = (BigDecimal) prtInfo.get("RSP_TM_UP_MN_AOT");
//
//            if (hasValue(erlAddlTmNum) && hasValue(lateAddlTmNum) && hasValue(repTmUpMnAot)) {
//                Date dtSysDtTm = toDate(this.sysTs, FORMAT_TS);
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(dtSysDtTm);
//
//                if (ZYPConstant.FLG_ON_Y.equals(erlSysDtFlg) && hasValue(erlAddlTmNum) && BigDecimal.ZERO.compareTo(erlAddlTmNum) != 0) {
//                    cal.add(Calendar.MINUTE, erlAddlTmNum.intValue());
//                    erlStartTs = toStringDate(cal.getTime(), FORMAT_TS);
//                } else if (ZYPConstant.FLG_ON_Y.equals(erlSysDtFlg) && ZYPConstant.FLG_ON_Y.equals(erlSvcRspTmMnAotFlg) && hasValue(repTmUpMnAot)) {
//                    cal.add(Calendar.MINUTE, repTmUpMnAot.intValue());
//                    erlStartTs = toStringDate(cal.getTime(), FORMAT_TS);
//                } else if (ZYPConstant.FLG_ON_Y.equals(erlSysDtFlg)) {
//                    erlStartTs = this.sysTs;
//                }
//                if (ZYPConstant.FLG_ON_Y.equals(lateSysDtFlg) && BigDecimal.ZERO.compareTo(lateAddlTmNum) != 0) {
//                    cal.add(Calendar.MINUTE, lateAddlTmNum.intValue());
//                    lateStartTs = toStringDate(cal.getTime(), FORMAT_TS);
//                } else if (ZYPConstant.FLG_ON_Y.equals(lateSysDtFlg) && ZYPConstant.FLG_ON_Y.equals(lateSvcRspTmMnAotFlg)) {
//                    cal.add(Calendar.MINUTE, repTmUpMnAot.intValue());
//                    lateStartTs = toStringDate(cal.getTime(), FORMAT_TS);
//                } else if (ZYPConstant.FLG_ON_Y.equals(lateSysDtFlg)) {
//                    lateStartTs = this.sysTs;
//                }
//            }
//            setValue(apiPMsg.xxSvcTaskList.no(0).erlStartTs, erlStartTs);
//            setValue(apiPMsg.xxSvcTaskList.no(0).lateStartTs, lateStartTs);
//        }
        // Del End 2018/03/30 QC#16339

        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        // mod start 2016/04/01 CSA Defect#5083
        setValue(apiPMsg.xxModeCd, NSZC045001.PROCESS_MODE_FOLLOWUP_TASK);
        // mod end 2016/04/01 CSA Defect#5083
        setValue(apiPMsg.fsrNum, pMsg.fsrNum);
        setValue(apiPMsg.fsrVisitNum, pMsg.fsrVisitNum);
        setValue(apiPMsg.userId, pMsg.userId);
        setValue(apiPMsg.bypsPrfTechFlg, ZYPConstant.FLG_OFF_N);
        if (ZYPConstant.FLG_ON_Y.equals((String) follUpInfo.get("REQ_PRT_FLG"))) {
            setValue(apiPMsg.svcTaskStsCd, SVC_TASK_STS.PENDING_PARTS);
        } else {
            setValue(apiPMsg.svcTaskStsCd, (String) follUpInfo.get("SVC_TASK_STS_CD"));
        }
        setValue(apiPMsg.firstProdCtrlCd, (String) prtInfo.get("FIRST_PROD_CTRL_CD"));
        setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValue(apiPMsg.custMachCtrlNum, svcTaskTMsg.custMachCtrlNum);
        setValue(apiPMsg.serNum, svcTaskTMsg.serNum);
        setValue(apiPMsg.custTelNum, svcTaskTMsg.custTelNum);
        setValue(apiPMsg.custTelExtnNum, svcTaskTMsg.custTelExtnNum);
        setValue(apiPMsg.svcCustAttnTxt, svcTaskTMsg.svcCustAttnTxt);
        // mod start 2019/04/19 QC#31238
        //setValue(apiPMsg.custEmlAddr, svcTaskTMsg.custEmlAddr);
        if (hasValue(dtlPMsg.sendRptEmlAddr)) {
            String[] sendRptEmlAddrList = dtlPMsg.sendRptEmlAddr.getValue().split(",");
            setValue(apiPMsg.custEmlAddr, sendRptEmlAddrList[0]);
        } else {
            setValue(apiPMsg.custEmlAddr, svcTaskTMsg.custEmlAddr);
        }
        // mod end 2019/04/19 QC#31238
        setValue(apiPMsg.custPoNum, svcTaskTMsg.custPoNum);
        setValue(apiPMsg.custPoDt, svcTaskTMsg.custPoDt);
        setValue(apiPMsg.dsSvcCallTpCd, (String) follUpInfo.get("DS_SVC_CALL_TP_CD"));
        setValue(apiPMsg.svcTaskRcvDt, this.sysDt);
        setValue(apiPMsg.svcTaskRcvTm, this.sysTm);
        setValue(apiPMsg.machDownFlg, getMachDownFlg(pMsg));
        setValue(apiPMsg.prtChrgFlg, svcTaskTMsg.prtChrgFlg);
        setValue(apiPMsg.svcLborChrgFlg, svcTaskTMsg.svcLborChrgFlg);
        setValue(apiPMsg.firstSvcTaskFlg, ZYPConstant.FLG_OFF_N);
        // mod start 2016/04/01 CSA Defect#5083, 2016/06/07 CSA Defect#9218
        setValue(apiPMsg.svcPblmCrctTpCd, dtlPMsg.svcPblmCrctTpCd);
        // mod end 2016/04/01 CSA Defect#5083, 2016/06/07 CSA Defect#9218
        // mod start 2016/07/01 CSA Defect#9677
        setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // mod end 2016/07/01 CSA Defect#9577

        setValue(apiPMsg.xxSvcTaskList.no(0).svcTaskNum, dtlPMsg.svcTaskNum);
        setValue(apiPMsg.xxSvcTaskList.no(0).svcCallPrtyCd, svcTaskTMsg.svcCallPrtyCd);
        if (ZYPConstant.FLG_ON_Y.equals((String) follUpInfo.get("REQ_TECH_FLG"))) {
            // START 2017/06/23 K.Ochiai [QC#19493, MOD]
            setValue(apiPMsg.xxSvcTaskList.no(0).techCd, fsrVisitTMsg.techCd);
            setValue(apiPMsg.xxSvcTaskList.no(0).svcAsgTechCd, fsrVisitTMsg.techCd);
            setValue(apiPMsg.xxSvcTaskList.no(0).svcAsgTpCd, SVC_ASG_TP.REQUIRED);
            // END 2017/06/23 K.Ochiai [QC#19493, MOD]
        }
        setValue(apiPMsg.xxSvcTaskList.no(0).schdDisptEmlFlg, fsrVisitTMsg.schdDisptEmlFlg);
        setValue(apiPMsg.xxSvcTaskList.no(0).svcBrCd, svcTaskTMsg.svcBrCd);
        setValue(apiPMsg.xxSvcTaskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        setValue(apiPMsg.xxSvcTaskList.no(0).svcLttdNum, fsrVisitTMsg.svcLttdNum);
        setValue(apiPMsg.xxSvcTaskList.no(0).svcLgtdNum, fsrVisitTMsg.svcLgtdNum);
        // TODO set attachedFileList
        apiPMsg.xxSvcTaskList.setValidCount(1);

        new NSZC045001().execute(apiPMsg, onbatchType);
        if (!checkErrMsg(pMsg, apiPMsg)) {
            return false;
        }
        return true;
    }

    private boolean checkIwrStatus(NSZC005001PMsg pMsg) {
        // add start 2016/02/08 CSA Defect#2863
        if (frceUpdFlg) {
            return true;
        }
        // add end 2016/02/08 CSA Defect#2863
        // Add Start 2019/01/10 K.Fujimoto QC#26861
        if (this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)){
            return true;
        }
        // Add End   2019/01/10 K.Fujimoto QC#26861
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstrFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        MDSETMsg mdseTmsg = getMdseFindByKey(pMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.mdseCd.getValue());
        if (mdseTmsg == null) {
            setErrMsg(pMsg, NSZM0861E);
                return false;
        }
        if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.iwrEnblFlg.getValue())) {
            return true;
        }
        String chk100Flg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_SVC_DISPT_CHK_IWR_STS_100_FLG, pMsg.glblCmpyCd.getValue());
        String chk300Flg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_SVC_DISPT_CHK_IWR_STS_300_FLG, pMsg.glblCmpyCd.getValue());

        if (IWR_STS_CD_300.equals(pMsg.iwrStsCd.getValue()) && !ZYPConstant.FLG_OFF_N.equals(chk300Flg)) {
            if (!checkDebriefError(pMsg, DEBRIEF_CHK_IWR)) {
                return false;
            }
        }
        if (IWR_STS_CD_100.equals(pMsg.iwrStsCd.getValue()) && !ZYPConstant.FLG_OFF_N.equals(chk100Flg)) {
            if (!checkIwrStatus100(pMsg)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIwrStatus100(NSZC005001PMsg pMsg) {
        String follUpDays = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_IWR_ROSS_COMM_FOLL_UP_DAYS, pMsg.glblCmpyCd.getValue());
        String iwrLastCommTs = getIwrRgtnCond(pMsg);
        // START 2016/11/04 [QC#15732, MOD]
        // if (!hasValue(iwrLastCommTs) || !hasValue(follUpDays)) {
        //     return true;
        // }
        if (!hasValue(follUpDays)) {
            return true;
        }
        // END 2016/11/04 [QC#15732, MOD]

        // START 2016/11/04 [QC#15732, MOD]
        if (hasValue(iwrLastCommTs)) {
            Date dtIwrLastCommTs = toDate(iwrLastCommTs, FORMAT_TS);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dtIwrLastCommTs);
            cal.add(Calendar.DATE, Integer.parseInt(follUpDays));
            if (toStringDate(cal.getTime(), FORMAT_TS).compareTo(this.sysTs) >= 0) {
                return true;
            }
        }
        // END 2016/11/04 [QC#15732, MOD]
        String svcTaskRcvDt = getSvcTaskRcvDt(pMsg);
        if (hasValue(svcTaskRcvDt)) {
            return true;
        }
        Map<String, Object> prtInfo = getPrtInfo(pMsg);
        if (prtInfo == null) {
            return true;
        }
        SVC_TASKTMsg svcTaskTMsg = this.completionBean.getSvcTaskTMsg();
        FSR_VISITTMsg fsrVisitTMsg = this.completionBean.getFsrVisitTMsg();
        NSZC045001PMsg apiPMsg = new NSZC045001PMsg();

        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NSZC045001.PROCESS_MODE_ADD_TASK);
        // START 2016/11/04 [QC#15732, MOD]
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        // END 2016/11/04 [QC#15732, MOD]
        setValue(apiPMsg.fsrNum, pMsg.fsrNum);
        setValue(apiPMsg.fsrVisitNum, pMsg.fsrVisitNum);
        setValue(apiPMsg.userId, pMsg.userId);
        setValue(apiPMsg.bypsPrfTechFlg, ZYPConstant.FLG_OFF_N);
        setValue(apiPMsg.firstProdCtrlCd, (String) prtInfo.get("FIRST_PROD_CTRL_CD"));
        setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValue(apiPMsg.custMachCtrlNum, svcTaskTMsg.custMachCtrlNum);
        setValue(apiPMsg.serNum, svcTaskTMsg.serNum);
        setValue(apiPMsg.custTelNum, svcTaskTMsg.custTelNum);
        setValue(apiPMsg.custTelExtnNum, svcTaskTMsg.custTelExtnNum);
        setValue(apiPMsg.svcCustAttnTxt, svcTaskTMsg.svcCustAttnTxt);
        setValue(apiPMsg.custEmlAddr, svcTaskTMsg.custEmlAddr);
        setValue(apiPMsg.custPoNum, svcTaskTMsg.custPoNum);
        setValue(apiPMsg.custPoDt, svcTaskTMsg.custPoDt);
        setValue(apiPMsg.dsSvcCallTpCd, DS_SVC_CALL_TP.SYSTEMS);
        setValue(apiPMsg.svcTaskRcvDt, this.sysDt);
        setValue(apiPMsg.svcTaskRcvTm, this.sysTm);
        setValue(apiPMsg.machDownFlg, getMachDownFlg(pMsg));
        setValue(apiPMsg.prtChrgFlg, svcTaskTMsg.prtChrgFlg);
        setValue(apiPMsg.svcLborChrgFlg, svcTaskTMsg.svcLborChrgFlg);
        setValue(apiPMsg.firstSvcTaskFlg, ZYPConstant.FLG_OFF_N);
        // mod start 2016/07/01 CSA Defect#9677
        setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // mod end 2016/07/01 CSA Defect#9577

        setValue(apiPMsg.xxSvcTaskList.no(0).svcTaskNum, svcTaskTMsg.svcTaskNum);
        setValue(apiPMsg.xxSvcTaskList.no(0).svcCallPrtyCd, svcTaskTMsg.svcCallPrtyCd);
        setValue(apiPMsg.xxSvcTaskList.no(0).techCd, svcTaskTMsg.techCd);
        setValue(apiPMsg.xxSvcTaskList.no(0).schdDisptEmlFlg, fsrVisitTMsg.schdDisptEmlFlg);
        setValue(apiPMsg.xxSvcTaskList.no(0).svcBrCd, svcTaskTMsg.svcBrCd);
        setValue(apiPMsg.xxSvcTaskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        setValue(apiPMsg.xxSvcTaskList.no(0).svcBrCd, svcTaskTMsg.svcBrCd);
        setValue(apiPMsg.xxSvcTaskList.no(0).svcLttdNum, fsrVisitTMsg.svcLttdNum);
        setValue(apiPMsg.xxSvcTaskList.no(0).svcLgtdNum, fsrVisitTMsg.svcLgtdNum);
        apiPMsg.xxSvcTaskList.setValidCount(1);

        new NSZC045001().execute(apiPMsg, onbatchType);
        if (!checkErrMsg(pMsg, apiPMsg)) {
            return false;
        }
        return true;
    }

//    private boolean checkIwrStatus300(NSZC005001PMsg pMsg) {
//        String errDays = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_IWR_ROSS_COMM_ERR_DAYS, pMsg.glblCmpyCd.getValue());
//        String iwrLastCommTs = getIwrRgtnCond(pMsg);
//        if (!hasValue(iwrLastCommTs) || !hasValue(errDays)) {
//            return true;
//        }
//
//        Date dtIwrLastCommTs = toDate(iwrLastCommTs, FORMAT_TS);
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(dtIwrLastCommTs);
//        cal.add(Calendar.DATE, Integer.parseInt(errDays));
//        if (toStringDate(cal.getTime(), FORMAT_TS).compareTo(this.sysTs) >= 0) {
//            return true;
//        }
//        isDebreifError = true;
//        String iwrCommErrMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_IWR_COMM_ERR_MSG, pMsg.glblCmpyCd.getValue());
//        if (!insertSvcMemo(pMsg, null, iwrCommErrMsg)) {
//            return false;
//        }
//        if (!updateSvcTaskStsCd(pMsg, SVC_TASK_STS.DEBRIEF_ERROR)) {
//            return false;
//        }
//        return true;
//    }

    private void checkVisitTaskCplt(NSZC005001PMsg pMsg) {
        List<String> svcTaskNumList = getSvcTaskNumByStsCdExSvcTaskNum(pMsg, this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue(), SVC_TASK_STS_LIST_NOT_COMPLETE);
        if ((svcTaskNumList == null || svcTaskNumList.size() == 0) && !this.isDebreifError) {
            fsrCompleteFlg = true;
        } else {
            fsrCompleteFlg = false;
        }
    }

    private boolean confirmUsageParts(NSZC005001PMsg pMsg) {
        // mod start 2016/04/20 CSA Defect#4469
        List<NSZC005001_xxFsrUsgListPMsg> fsrUsgList = new ArrayList<NSZC005001_xxFsrUsgListPMsg>();
        List<NSZC005001_xxPartSurveyListPMsg> fsrUsgSrvyList = new ArrayList<NSZC005001_xxPartSurveyListPMsg>();

        // Get FSR Usage Table
        //Mod Start 2018/11/28 K.Fujimoto QC#28647
        // FSR_USGTMsgArray fsrUsgTMsgList = null;
        // if (MODE_DEBRIEF.equals(pMsg.xxModeCd.getValue())) {
        //   fsrUsgTMsgList = getFsrUsgListNotSvcTaskNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue());
        //   getFsrUsgSrvyList(fsrUsgTMsgList, fsrUsgSrvyList);
        // } else if (MODE_INSTALL_CHECK.equals(pMsg.xxModeCd.getValue())) {
        //     fsrUsgTMsgList = getFsrUsgListByFsrNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        //     getFsrUsgSrvyList(fsrUsgTMsgList, fsrUsgSrvyList);
        // }

        // for (int i = 0; i < fsrUsgTMsgList.getValidCount(); i++) {
        //     FSR_USGTMsg fsrUsgTMsg = fsrUsgTMsgList.no(i);
        //     NSZC005001_xxFsrUsgListPMsg dtlPMsg = new NSZC005001_xxFsrUsgListPMsg();
        //     EZDMsg.copy(fsrUsgTMsg, null, dtlPMsg, null);
        //     fsrUsgList.add(dtlPMsg);
        // }

        // if (MODE_DEBRIEF.equals(pMsg.xxModeCd.getValue())) {
        for (int i = 0; i < pMsg.xxFsrUsgList.getValidCount(); i++) {
            NSZC005001_xxFsrUsgListPMsg dtlPMsg = pMsg.xxFsrUsgList.no(i);
            fsrUsgList.add(dtlPMsg);
        }
        for (int i = 0; i < pMsg.xxPartSurveyList.getValidCount(); i++) {
            NSZC005001_xxPartSurveyListPMsg dtlSrvyPMsg = pMsg.xxPartSurveyList.no(i);
            fsrUsgSrvyList.add(dtlSrvyPMsg);
        }
        // }
        // mod end 2016/04/20 CSA Defect#4469

        // Check Inventory
        // tmpFsrUsgListConf : The list is used for creating FSR_USG.
        List<NSZC005001_xxFsrUsgListPMsg> tmpFsrUsgListConf = new ArrayList<NSZC005001_xxFsrUsgListPMsg>();
        // tmpFsrUsgListConfForInvty : The list is used for delivering from a warehouse/storaging in a warehouse.
        List<NSZC005001_xxFsrUsgListPMsg> tmpFsrUsgListConfForInvty = new ArrayList<NSZC005001_xxFsrUsgListPMsg>();
        // tmpInvtyList :  The value for the current quantity of inventory.
        List<NSZC005002PMsg> tmpInvtyList = new ArrayList<NSZC005002PMsg>();
        // invtyList :  The value for the current quantity of inventory.
        List<NSZC005002PMsg> invtyList = new ArrayList<NSZC005002PMsg>();

        String svcTaskNum = null;
        String preSvcTaskNum = null;
        boolean checkNg = false;

        //get current FSR_USG by the svcTaskNum
        Map<BigDecimal, FSR_USGTMsg> currentFsrUsgMap = getCurrentFsrUsgBySvcTaskNum(null, pMsg, fsrUsgList.get(0).svcTaskNum.getValue());

        for (int i = 0; i < fsrUsgList.size(); i++) {
            NSZC005001_xxFsrUsgListPMsg dtlPMsg = fsrUsgList.get(i);
            svcTaskNum = dtlPMsg.svcTaskNum.getValue();

            // START 2018/01/16 K.Ochiai [QC#18646, MOD]
            // the usage-parts aren't need to check quantity of inventry.
            if (ZYPConstant.FLG_ON_Y.equals(dtlPMsg.svcInvtyExFlg.getValue())) {
                NSZC005001_xxFsrUsgListPMsg tmpFsrUsg = new NSZC005001_xxFsrUsgListPMsg();
                tmpFsrUsg = changeFsrUsg(pMsg, dtlPMsg, null, null, null);
                tmpFsrUsgListConf.add(tmpFsrUsg);
                continue;
            }
            // END 2018/01/16 K.Ochiai [QC#18646, MOD]
            // Change svcTaskNum
            if (i > 0 && !svcTaskNum.equals(preSvcTaskNum)) {
                // Insert Service Memo
                if (checkNg) {
                    //Mod Start 2019/07/30 K.Fujimoto QC#52220
                    //String ngtvQtyMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_PARTS_USG_NGTV_QTY_MSG, pMsg.glblCmpyCd.getValue());
                    //if (!insertSvcMemo(pMsg, svcTaskNum, ngtvQtyMsg)) {
                    if (!insertSvcMemoForPrtUsgNgtvQty(pMsg, fsrUsgList, svcTaskNum)) {
                        return false;
                    }
                    //Mod End   2019/07/30 K.Fujimoto QC#52220
                }
                //put current FSR_USG by the svcTaskNum
                currentFsrUsgMap = getCurrentFsrUsgBySvcTaskNum(null, pMsg, svcTaskNum);

                // Update Service Task Status Code
                String svcTaskStsCd = null;
                if (!checkNg) {
                    svcTaskStsCd = SVC_TASK_STS.COMPLETED;
                } else {
                    svcTaskStsCd = SVC_TASK_STS.DEBRIEF_ERROR;
                    fsrCompleteFlg = false;
                }
                if (!updateSvcTaskStsCd(pMsg, svcTaskStsCd)) {
                    return false;
                }

                // Update invtyList
                if (!checkNg) {
                    invtyList = new ArrayList<NSZC005002PMsg>();
                    for (NSZC005002PMsg tmpInvty : tmpInvtyList) {
                        NSZC005002PMsg invty = new NSZC005002PMsg();
                        EZDMsg.copy(tmpInvty, null, invty, null);
                        invtyList.add(invty);
                    }
                } else {
                    tmpInvtyList = new ArrayList<NSZC005002PMsg>();
                    for (NSZC005002PMsg invty : invtyList) {
                        NSZC005002PMsg tmpInvty = new NSZC005002PMsg();
                        EZDMsg.copy(invty, null, tmpInvty, null);
                        tmpInvtyList.add(tmpInvty);
                    }
                }
                checkNg = false;
            }

            // START 2023/04/12 K.Kitachi [QC#61311, DEL]
//            if (!checkNg) {
            // END 2023/04/12 K.Kitachi [QC#61311, DEL]
                if (!checkInvty(pMsg, dtlPMsg, tmpFsrUsgListConf, tmpFsrUsgListConfForInvty, tmpInvtyList, currentFsrUsgMap)) {
                    checkNg = true;
                }
            // START 2023/04/12 K.Kitachi [QC#61311, DEL]
//            }
            // END 2023/04/12 K.Kitachi [QC#61311, DEL]
            preSvcTaskNum = svcTaskNum;
        }

        // Insert Service Memo
        if (checkNg) {
            //Mod Start 2019/07/30 K.Fujimoto QC#52220
            //String ngtvQtyMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_PARTS_USG_NGTV_QTY_MSG, pMsg.glblCmpyCd.getValue());
            //if (!insertSvcMemo(pMsg, svcTaskNum, ngtvQtyMsg)) {
            if (!insertSvcMemoForPrtUsgNgtvQty(pMsg, fsrUsgList, svcTaskNum)) {
                return false;
            }
            //Mod End   2019/07/30 K.Fujimoto QC#52220
        }
        //Mod End   2018/11/28 K.Fujimoto QC#28647
        // Update Service Task Status Code
        String svcTaskStsCd = null;
        if (!checkNg) {
            svcTaskStsCd = SVC_TASK_STS.COMPLETED;
        } else {
            svcTaskStsCd = SVC_TASK_STS.DEBRIEF_ERROR;
            fsrCompleteFlg = false;
        }
        if (!updateSvcTaskStsCd(pMsg, svcTaskStsCd)) {
            return false;
        }
        //Mod Start 2018/11/28 K.Fujimoto QC#28647
        // if (fsrCompleteFlg) {
        // START 2023/04/12 K.Kitachi [QC#61311, DEL]
//        if (!checkNg) {
        // END 2023/04/12 K.Kitachi [QC#61311, DEL]
            for (int i = 0; i < tmpFsrUsgListConf.size(); i++) {
                // START 2023/04/12 K.Kitachi [QC#61311, ADD]
                if (ZYPConstant.FLG_ON_Y.equals(tmpFsrUsgListConf.get(i).procErrFlg.getValue())) {
                    continue;
                }
                // END 2023/04/12 K.Kitachi [QC#61311, ADD]
                this.completionBean.addFsrUsgListConf(tmpFsrUsgListConf.get(i));
            }
            // add start 2016/04/20 CSA Defect#4469
            for (int i = 0; i < fsrUsgSrvyList.size(); i++) {
                this.completionBean.addFsrUsgSrvyListConf(fsrUsgSrvyList.get(i));
            }
            // add end 2016/04/20 CSA Defect#4469
            for (int i = 0; i < tmpFsrUsgListConfForInvty.size(); i++) {
                // START 2023/04/12 K.Kitachi [QC#61311, ADD]
                if (ZYPConstant.FLG_ON_Y.equals(tmpFsrUsgListConfForInvty.get(i).procErrFlg.getValue())) {
                    continue;
                }
                // END 2023/04/12 K.Kitachi [QC#61311, ADD]
                this.completionBean.addFsrUsgListConfForInvty(tmpFsrUsgListConfForInvty.get(i));
            }
            // Add Start 2019/02/13 K.Fujimoto QC#30310
            modMsgForChangeUsage(currentFsrUsgMap, pMsg);
            // Add End   2019/02/13 K.Fujimoto QC#30310
        // START 2023/04/12 K.Kitachi [QC#61311, DEL]
//        }
        // END 2023/04/12 K.Kitachi [QC#61311, DEL]
        return true;
    }

    //Add Start 2019/07/30 K.Fujimoto QC#52220
    //Add Parts used list at Task & Error Message.
    private boolean insertSvcMemoForPrtUsgNgtvQty(NSZC005001PMsg pMsg, List<NSZC005001_xxFsrUsgListPMsg> fsrUsgList, String svcTaskNum){
        String ngtvQtyMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_PARTS_USG_NGTV_QTY_MSG, pMsg.glblCmpyCd.getValue());

        StringBuffer sb = new StringBuffer();
        String newLine = System.getProperty("line.separator");
        String dtlNoteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_NSZC0050_PARTS_USED_MSG_TMPL, pMsg.glblCmpyCd.getValue());
        sb.append(ngtvQtyMsg);
        for (NSZC005001_xxFsrUsgListPMsg usgPMsg : fsrUsgList) {
            // START 2023/04/12 K.Kitachi [QC#61311, ADD]
            if (!ZYPConstant.FLG_ON_Y.equals(usgPMsg.procErrFlg.getValue())) {
                continue;
            }
            // END 2023/04/12 K.Kitachi [QC#61311, ADD]
            sb.append(newLine);
            sb.append(String.format(dtlNoteTxtTmpl, usgPMsg.mdseCd.getValue(), usgPMsg.svcPrtQty.getValue().intValue()));
        }
        String message = sb.toString();
        if (message.length() > LEN_SVC_CMNT_TXT) {
            message = message.substring(STR_IDX_SVC_CMNT_TXT, LEN_SVC_CMNT_TXT);
        }
        if (!insertSvcMemo(pMsg, svcTaskNum, message)) {
            return false;
        }
        return true;
    }
    //Add End   2019/07/30 K.Fujimoto QC#52220
    
    //Add Start 2018/11/28 K.Fujimoto QC#28647
    private Map<BigDecimal, FSR_USGTMsg> getCurrentFsrUsgBySvcTaskNum(Map<BigDecimal, FSR_USGTMsg> currentFsrUsgMap,NSZC005001PMsg pMsg,String svcTaskNum){
        if(currentFsrUsgMap == null){
            currentFsrUsgMap = new HashMap<BigDecimal, FSR_USGTMsg>();
        }
        //get data that has already been shipped from the warehouse.
        FSR_USGTMsgArray currenFsrUsgList = getFsrUsgListBySvcTaskNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(),svcTaskNum);
        for (int i = 0; i < currenFsrUsgList.getValidCount(); i++){
            FSR_USGTMsg currentFsrUsg = currenFsrUsgList.no(i);
            currentFsrUsgMap.put(currentFsrUsg.fsrUsgPk.getValue(), currentFsrUsg);
        }
        return currentFsrUsgMap;
    }
    // Add End   2018/11/28 K.Fujimoto QC#28647

    // Del Start 2018/11/28 K.Fujimoto QC#28647
    // add 2016/04/20 CSA Defect#4469
    // private void getFsrUsgSrvyList(FSR_USGTMsgArray fsrUsgTMsgList, List<NSZC005001_xxPartSurveyListPMsg> fsrUsgSrvyList) {
    // 
    //     for (int i = 0; i < fsrUsgTMsgList.getValidCount(); i++) {
    //         FSR_USGTMsg fsrUsgTMsg = fsrUsgTMsgList.no(i);
    //         FSR_USG_SRVYTMsgArray tMsgArray = getFsrUsgSrvyList(fsrUsgTMsg.glblCmpyCd.getValue(), fsrUsgTMsg.fsrUsgPk.getValue());
    // 
    //         if (tMsgArray.getValidCount() == 0) {
    //             NSZC005001_xxPartSurveyListPMsg dtlSrvyPMsg = new NSZC005001_xxPartSurveyListPMsg();
    //             EZDMsg.copy(fsrUsgTMsg, null, dtlSrvyPMsg, null);
    //             fsrUsgSrvyList.add(dtlSrvyPMsg);
    //         }
    //         for (int j = 0; j < tMsgArray.getValidCount(); j++) {
    //             FSR_USG_SRVYTMsg fsrUsgSrvyTMsg = tMsgArray.no(j);
    //             NSZC005001_xxPartSurveyListPMsg dtlSrvyPMsg = new NSZC005001_xxPartSurveyListPMsg();
    //             EZDMsg.copy(fsrUsgTMsg, null, dtlSrvyPMsg, null);
    //             EZDMsg.copy(fsrUsgSrvyTMsg, null, dtlSrvyPMsg, null);
    //             fsrUsgSrvyList.add(dtlSrvyPMsg);
    //         }
    //     }
    // }
    // Del End   2018/11/28 K.Fujimoto QC#28647

    // mod start 2015/12/24 CSA Defect#921
    // Mod Start 2018/11/28 K.Fujimoto QC#28647
    // private boolean checkInvty(NSZC005001PMsg pMsg, NSZC005001_xxFsrUsgListPMsg dtlPMsg, List<NSZC005001_xxFsrUsgListPMsg> tmpFsrUsgListConf, List<NSZC005001_xxFsrUsgListPMsg> tmpFsrUsgListConfForInvty, List<NSZC005002PMsg> tmpInvtyList) {
    private boolean checkInvty(NSZC005001PMsg pMsg, NSZC005001_xxFsrUsgListPMsg dtlPMsg, List<NSZC005001_xxFsrUsgListPMsg> tmpFsrUsgListConf, List<NSZC005001_xxFsrUsgListPMsg> tmpFsrUsgListConfForInvty, List<NSZC005002PMsg> tmpInvtyList, Map<BigDecimal, FSR_USGTMsg> currentFsrUsgMap) {
    // Mod End   2018/11/28 K.Fujimoto QC#28647
        String targetMdseCd = dtlPMsg.mdseCd.getValue();
        String targetTechLocCd = dtlPMsg.prtUsedByTechLocCd.getValue();
        //usgQty: Requiered quantity of part-usages.
        BigDecimal usgQty = dtlPMsg.svcPrtQty.getValue();
        BigDecimal diffUsgQty = BigDecimal.ZERO;

        //In order to check the quantity of inventry, translate the unit of package to in each quantity.
        MDSE_STORE_PKGTMsg mdseStorePkgTMsg = getMdseStorePkgFindByKey(pMsg.glblCmpyCd.getValue(), targetMdseCd, dtlPMsg.uomCd.getValue());
        if (!checkRtnCodeForSearch(pMsg, mdseStorePkgTMsg, new MDSE_STORE_PKGTMsg())) {
            return false;
        }
        usgQty = dtlPMsg.svcPrtQty.getValue().multiply(mdseStorePkgTMsg.inEachQty.getValue());

        // Add Start 2018/11/28 K.Fujimoto QC#28647
        // get difference between the quantity of current part-usage and the quantity of inputed part-usage.
        NSZC005001_xxFsrUsgListPMsg diffFsrUsgPMsg = new NSZC005001_xxFsrUsgListPMsg();


        if (currentFsrUsgMap.containsKey(dtlPMsg.fsrUsgPk.getValue())){
            FSR_USGTMsg currentFsrUsgTMsg = currentFsrUsgMap.get(dtlPMsg.fsrUsgPk.getValue());

            // If it is same primary key and mdse cd is different, return it based on the inventry.
            if(!currentFsrUsgTMsg.mdseCd.getValue().equals(dtlPMsg.mdseCd.getValue()) && currentFsrUsgTMsg.svcInvtyExFlg.getValue().equals(ZYPConstant.FLG_OFF_N)){
                NSZC005001_xxFsrUsgListPMsg tmpFsrUsg = new NSZC005001_xxFsrUsgListPMsg();
                //In order to check the quantity of inventry, translate the unit of package to in each quantity.
                MDSE_STORE_PKGTMsg currentMdseStorePkgTMsg = getMdseStorePkgFindByKey(pMsg.glblCmpyCd.getValue(), currentFsrUsgTMsg.mdseCd.getValue(), currentFsrUsgTMsg.uomCd.getValue());
                if (!checkRtnCodeForSearch(pMsg, currentMdseStorePkgTMsg, new MDSE_STORE_PKGTMsg())) {
                    return false;
                }
                BigDecimal currentQty = currentFsrUsgTMsg.svcPrtQty.getValue().multiply(currentMdseStorePkgTMsg.inEachQty.getValue());
                setValue(tmpFsrUsg.fsrUsgPk, currentFsrUsgTMsg.fsrUsgPk);
                setValue(tmpFsrUsg.svcTaskNum, currentFsrUsgTMsg.svcTaskNum);
                setValue(tmpFsrUsg.dsSvcCallTpCd, currentFsrUsgTMsg.dsSvcCallTpCd);
                setValue(tmpFsrUsg.prtFromTechLocCd, currentFsrUsgTMsg.prtFromTechLocCd);
                setValue(tmpFsrUsg.prtFromTechCd, currentFsrUsgTMsg.prtFromTechCd);
                setValue(tmpFsrUsg.prtUsedByTechLocCd, currentFsrUsgTMsg.prtUsedByTechLocCd);
                setValue(tmpFsrUsg.prtUsedByTechCd, currentFsrUsgTMsg.prtUsedByTechCd);
                setValue(tmpFsrUsg.mdseCd, currentFsrUsgTMsg.mdseCd);
                setValue(tmpFsrUsg.mdseNm, currentFsrUsgTMsg.mdseNm);
                setValue(tmpFsrUsg.svcPrtQty, currentQty.negate());
                setValue(tmpFsrUsg.uomCd, currentFsrUsgTMsg.uomCd);
                setValue(tmpFsrUsg.svcExecDt, currentFsrUsgTMsg.svcExecDt);
                setValue(tmpFsrUsg.svcPrtUnitAmt, currentFsrUsgTMsg.svcPrtUnitAmt);
                setValue(tmpFsrUsg.svcPrtDealAmt, currentFsrUsgTMsg.svcPrtDealAmt);
                setValue(tmpFsrUsg.svcPrtFuncAmt, currentFsrUsgTMsg.svcPrtFuncAmt);
                setValue(tmpFsrUsg.svcPrtTaxRate, currentFsrUsgTMsg.svcPrtTaxRate);
                setValue(tmpFsrUsg.svcPrtDealTaxAmt, currentFsrUsgTMsg.svcPrtDealTaxAmt);
                setValue(tmpFsrUsg.svcPrtFuncTaxAmt, currentFsrUsgTMsg.svcPrtFuncTaxAmt);
                setValue(tmpFsrUsg.svcPrtDiscRate, currentFsrUsgTMsg.svcPrtDiscRate);
                setValue(tmpFsrUsg.svcPrtDealDiscAmt, currentFsrUsgTMsg.svcPrtDealDiscAmt);
                setValue(tmpFsrUsg.svcPrtFuncDiscAmt, currentFsrUsgTMsg.svcPrtFuncDiscAmt);
                setValue(tmpFsrUsg.fsrUsgProcFlg, currentFsrUsgTMsg.fsrUsgProcFlg);
                setValue(tmpFsrUsg.svcInvtyExFlg, currentFsrUsgTMsg.svcInvtyExFlg);
                setValue(tmpFsrUsg.svcModPlnId, currentFsrUsgTMsg.svcModPlnId);
                setValue(tmpFsrUsg.svcPrtCmntTxt, currentFsrUsgTMsg.svcPrtCmntTxt);
                setValue(tmpFsrUsg.svcPrtChrgFlg, currentFsrUsgTMsg.svcPrtChrgFlg);
                setValue(tmpFsrUsg.svcChrgChngRsnCd, currentFsrUsgTMsg.svcChrgChngRsnCd);
                setValue(tmpFsrUsg.ovrdSvcPrtUnitAmt, currentFsrUsgTMsg.ovrdSvcPrtUnitAmt);
                setValue(tmpFsrUsg.ovrdChngRsnCd, currentFsrUsgTMsg.ovrdChngRsnCd);
                setValue(tmpFsrUsg.ovrdChngUsrId, currentFsrUsgTMsg.ovrdChngUsrId);
                setValue(tmpFsrUsg.serNumTxt, currentFsrUsgTMsg.serNumTxt);
                setValue(tmpFsrUsg.modItemTxt, currentFsrUsgTMsg.modItemTxt);
                tmpFsrUsgListConfForInvty.add(tmpFsrUsg);
            } else {
                BigDecimal diffSvcPrtQty = dtlPMsg.svcPrtQty.getValue().subtract(currentFsrUsgTMsg.svcPrtQty.getValue());
                EZDMsg.copy(dtlPMsg, null, diffFsrUsgPMsg, null);
                setValue(diffFsrUsgPMsg.svcPrtQty, diffSvcPrtQty);
                diffUsgQty = diffSvcPrtQty.multiply(mdseStorePkgTMsg.inEachQty.getValue());
            }
        }
        // Add End   2018/11/28 K.Fujimoto QC#28647

        // check this Revision INVTY
        NSZC005002PMsg tmpInvty = new NSZC005002PMsg();
        if (!existsTmpInvtyList(tmpInvtyList, targetMdseCd, targetTechLocCd)) {
            // search INVTY
            INVTYTMsg invtyTMsg = getInvtyFindByKey(pMsg.glblCmpyCd.getValue(), targetMdseCd, targetTechLocCd);
            // Mod Start 2019/07/30 K.Fujimoto QC#52220
            if (invtyTMsg == null) {
            //if (!checkRtnCodeForSearch(pMsg, invtyTMsg, new INVTYTMsg())) {
            // Mod End   2019/07/30 K.Fujimoto QC#52220
                // Mod Start 2018/11/28 K.Fujimoto QC#28647
                setValue(tmpInvty.mdseCd, targetMdseCd);
                setValue(tmpInvty.invtyLocCd, targetTechLocCd);
                setValue(tmpInvty.invtyAvalQty, BigDecimal.ZERO);
                // Mod End   2018/11/28 K.Fujimoto QC#28647
            } else {
                setValue(tmpInvty.mdseCd, invtyTMsg.mdseCd);
                setValue(tmpInvty.invtyLocCd, invtyTMsg.invtyLocCd);
                setValue(tmpInvty.invtyAvalQty, invtyTMsg.invtyAvalQty);
            }

            // add tmpInvtyList
            // setValue(tmpInvty.mdseCd, invtyTMsg.mdseCd);
            // setValue(tmpInvty.invtyLocCd, invtyTMsg.invtyLocCd);
            // setValue(tmpInvty.invtyAvalQty, invtyTMsg.invtyAvalQty);
            tmpInvtyList.add(tmpInvty);
        }

        boolean otherRevision = false;
        for (int i = 0; i < tmpInvtyList.size(); i++) {
            tmpInvty = tmpInvtyList.get(i);
            if (tmpInvty.mdseCd.getValue().equals(targetMdseCd) && tmpInvty.invtyLocCd.getValue().equals(targetTechLocCd)) {
                // Mod Start 2018/11/28 K.Fujimoto QC#28647
                // usgQty = checkInvtyQty(pMsg, dtlPMsg, tmpFsrUsgListConf, tmpFsrUsgListConfForInvty, tmpInvty, usgQty, otherRevision);
                usgQty = checkInvtyQty(pMsg, dtlPMsg, tmpFsrUsgListConf, tmpFsrUsgListConfForInvty, tmpInvty, usgQty, otherRevision, diffFsrUsgPMsg, diffUsgQty);
                // Mod End 2018/11/28 K.Fujimoto QC#28647
                break;
            }
        }

        if (BigDecimal.ZERO.compareTo(usgQty) == 0) {
            return true;
        }

        // START 2020/06/22 K.Kitachi [QC#57230, DEL]
//        // check other Revision INVTY
//        NWZC206001PMsg apiPMsg = new NWZC206001PMsg();
//        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//        setValue(apiPMsg.slsDt, pMsg.slsDt);
//        setValue(apiPMsg.mdseCd, dtlPMsg.mdseCd);
//        setValue(apiPMsg.xxModeCd, NWZC206001Constant.SUPD_LIST_MODE);
//        setValue(apiPMsg.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
//        setValue(apiPMsg.xxAvalPrchFlg, ZYPConstant.FLG_OFF_N);
//        setValue(apiPMsg.whCd, dtlPMsg.prtUsedByTechLocCd);
//        setValue(apiPMsg.stkStsCd, STK_STS.GOOD);
//        new NWZC206001().execute(apiPMsg, onbatchType);
//        if (!checkErrMsg(pMsg, apiPMsg)) {
//            return false;
//        }
//
//        // add tmpInvtyList
//        for (int i = 0; i < apiPMsg.A.getValidCount(); i++) {
//            targetMdseCd = apiPMsg.A.no(i).mdseCd.getValue();
//            if (!existsTmpInvtyList(tmpInvtyList, targetMdseCd, targetTechLocCd)) {
//                tmpInvty = new NSZC005002PMsg();
//                setValue(tmpInvty.mdseCd, apiPMsg.A.no(i).mdseCd);
//                setValue(tmpInvty.invtyLocCd, targetTechLocCd);
//                setValue(tmpInvty.invtyAvalQty, apiPMsg.A.no(i).invtyAvalQty);
//                tmpInvtyList.add(tmpInvty);
//            }
//        }
//
//        otherRevision = true;
//        for (int i = 0; i < apiPMsg.A.getValidCount(); i++) {
//            targetMdseCd = apiPMsg.A.no(i).mdseCd.getValue();
//            for (int j = 0; j < tmpInvtyList.size(); j++) {
//                tmpInvty = tmpInvtyList.get(j);
//                if (tmpInvty.mdseCd.getValue().equals(targetMdseCd) && tmpInvty.invtyLocCd.getValue().equals(targetTechLocCd)) {
//                    // Mod Start 2018/11/28 K.Fujimoto QC#28647
//                    // usgQty = checkInvtyQty(pMsg, dtlPMsg, tmpFsrUsgListConf, tmpFsrUsgListConfForInvty, tmpInvty, usgQty, otherRevision);
//                    usgQty = checkInvtyQty(pMsg, dtlPMsg, tmpFsrUsgListConf, tmpFsrUsgListConfForInvty, tmpInvty, usgQty, otherRevision, null, null);
//                    // Mod End 2018/11/28 K.Fujimoto QC#28647
//                    if (BigDecimal.ZERO.compareTo(usgQty) == 0) {
//                        return true;
//                    }
//                    break;
//                }
//            }
//        }
        // END 2020/06/22 K.Kitachi [QC#57230, DEL]

        return false;
    }

    private boolean existsTmpInvtyList(List<NSZC005002PMsg> tmpInvtyList, String targetMdseCd, String targetTechLocCd) {
        NSZC005002PMsg tmpInvty = new NSZC005002PMsg();
        for (int i = 0; i < tmpInvtyList.size(); i++) {
            tmpInvty = tmpInvtyList.get(i);
            if (tmpInvty.mdseCd.getValue().equals(targetMdseCd) && tmpInvty.invtyLocCd.getValue().equals(targetTechLocCd)) {
                return true;
            }
        }
        return false;
    }

    // Mod Start 2018/11/28 K.Fujimoto QC#28647
    // private BigDecimal checkInvtyQty(NSZC005001PMsg pMsg, NSZC005001_xxFsrUsgListPMsg dtlPMsg, List<NSZC005001_xxFsrUsgListPMsg> tmpFsrUsgListConf, List<NSZC005001_xxFsrUsgListPMsg> tmpFsrUsgListConfForInvty, NSZC005002PMsg tmpInvty,
    //         BigDecimal usgQty, boolean otherRevision) {
    private BigDecimal checkInvtyQty(NSZC005001PMsg pMsg, NSZC005001_xxFsrUsgListPMsg dtlPMsg, List<NSZC005001_xxFsrUsgListPMsg> tmpFsrUsgListConf, List<NSZC005001_xxFsrUsgListPMsg> tmpFsrUsgListConfForInvty, NSZC005002PMsg tmpInvty,
            BigDecimal usgQty, boolean otherRevision, NSZC005001_xxFsrUsgListPMsg diffFsrUsgPMsg, BigDecimal diffUsgQty) {
    //Mod End   2018/11/28 K.Fujimoto QC#28647
        // Current Inventry
        BigDecimal invtyAvalQty = tmpInvty.invtyAvalQty.getValue();
        // Del Start 2018/11/28 K.Fujimoto QC#28647
        // if (invtyAvalQty.compareTo(BigDecimal.ZERO) == 0) {
        //     return usgQty;
        // }
        // Del End   2018/11/28 K.Fujimoto QC#28647

        BigDecimal trgtQty = BigDecimal.ZERO;
        NSZC005001_xxFsrUsgListPMsg tmpFsrUsg = new NSZC005001_xxFsrUsgListPMsg();

        if (!otherRevision) {
            // Mod Start 2018/11/28 K.Fujimoto QC#28647
            if (hasValue(diffFsrUsgPMsg.fsrUsgPk.getValue())){
                if (diffUsgQty.compareTo(BigDecimal.ZERO) == 0) {
                    // if quantity of diffUsg is zero, create new parts-usage and don't check inventry.
                    usgQty = BigDecimal.ZERO;
                    tmpFsrUsg = changeFsrUsg(pMsg, dtlPMsg, null, null, null);
                    tmpFsrUsgListConf.add(tmpFsrUsg);
                } else if (diffUsgQty.compareTo(BigDecimal.ZERO) < 0){
                    // if the quantity of current parts-usage is bigger than inputted parts-usage, 
                    // create new parts-usage and return difference between the quantity of both to inventry.
                    trgtQty = diffUsgQty;
                    usgQty = BigDecimal.ZERO;
                    tmpFsrUsg = changeFsrUsg(pMsg, dtlPMsg, null, null, null);
                    tmpFsrUsgListConf.add(tmpFsrUsg);
                    tmpFsrUsg = changeFsrUsg(pMsg, diffFsrUsgPMsg, null, trgtQty, PKG_UOM.EACH);
                    tmpFsrUsgListConfForInvty.add(tmpFsrUsg);
                    invtyAvalQty = invtyAvalQty.subtract(trgtQty);
                    setValue(tmpInvty.invtyAvalQty, invtyAvalQty);
                } else if (diffUsgQty.compareTo(BigDecimal.ZERO) > 0) {
                    // if the quantity of current parts-usage is smaller than inputted parts-usage, 
                    // create new parts-usage and withdraw difference between the quantity of both from inventry.
                    if (invtyAvalQty.compareTo(diffUsgQty) >= 0) {
                        // The inventry is sufficient
                        trgtQty = diffUsgQty;
                        usgQty = BigDecimal.ZERO;
                        tmpFsrUsg = changeFsrUsg(pMsg, dtlPMsg, null, null, null);
                        tmpFsrUsgListConf.add(tmpFsrUsg);
                        tmpFsrUsg = changeFsrUsg(pMsg, dtlPMsg, null, trgtQty, PKG_UOM.EACH);
                        tmpFsrUsgListConfForInvty.add(tmpFsrUsg);
                        invtyAvalQty = invtyAvalQty.subtract(trgtQty);
                        setValue(tmpInvty.invtyAvalQty, invtyAvalQty);
                    } else {
                        // The inventry is insufficient
                        trgtQty = invtyAvalQty;
                        usgQty = diffUsgQty.subtract(invtyAvalQty);
                        // START 2023/04/12 K.Kitachi [QC#61311, ADD]
                        setValue(dtlPMsg.procErrFlg, ZYPConstant.FLG_ON_Y);
                        // END 2023/04/12 K.Kitachi [QC#61311, ADD]
                        tmpFsrUsg = changeFsrUsg(pMsg, dtlPMsg, null, trgtQty, PKG_UOM.EACH);
                        tmpFsrUsgListConf.add(tmpFsrUsg);
                        tmpFsrUsgListConfForInvty.add(tmpFsrUsg);
                        invtyAvalQty = BigDecimal.ZERO;
                        setValue(tmpInvty.invtyAvalQty, invtyAvalQty);
                    }
                }
            } else {
                if (invtyAvalQty.compareTo(usgQty) >= 0) {
                    // The inventry is sufficient
                    trgtQty = usgQty;
                    usgQty = BigDecimal.ZERO;
                    tmpFsrUsg = changeFsrUsg(pMsg, dtlPMsg, null, null, null);
                    tmpFsrUsgListConf.add(tmpFsrUsg);
                    tmpFsrUsg = changeFsrUsg(pMsg, dtlPMsg, null, trgtQty, PKG_UOM.EACH);
                    tmpFsrUsgListConfForInvty.add(tmpFsrUsg);

                    invtyAvalQty = invtyAvalQty.subtract(trgtQty);
                    setValue(tmpInvty.invtyAvalQty, invtyAvalQty);
                } else {
                    // The inventry is insufficient
                    trgtQty = invtyAvalQty;
                    usgQty = usgQty.subtract(invtyAvalQty);
                    // START 2023/04/12 K.Kitachi [QC#61311, ADD]
                    setValue(dtlPMsg.procErrFlg, ZYPConstant.FLG_ON_Y);
                    // END 2023/04/12 K.Kitachi [QC#61311, ADD]
                    tmpFsrUsg = changeFsrUsg(pMsg, dtlPMsg, null, trgtQty, PKG_UOM.EACH);
                    tmpFsrUsgListConf.add(tmpFsrUsg);
                    tmpFsrUsgListConfForInvty.add(tmpFsrUsg);

                    invtyAvalQty = BigDecimal.ZERO;
                    setValue(tmpInvty.invtyAvalQty, invtyAvalQty);
                }
            }
        }
        // Mod End 2018/11/28 K.Fujimoto QC#28647

        if (otherRevision) {
            // Add Start 2019/01/10 K.Fujimoto QC#26861
            if (invtyAvalQty.compareTo(BigDecimal.ZERO) == 0) {
                 return usgQty;
            }
            // Add End   2019/01/10 K.Fujimoto QC#26861
            if (invtyAvalQty.compareTo(usgQty) >= 0) {
                trgtQty = usgQty;
                usgQty = BigDecimal.ZERO;
                tmpFsrUsg = changeFsrUsg(pMsg, dtlPMsg, tmpInvty.mdseCd.getValue(), trgtQty, PKG_UOM.EACH);
                tmpFsrUsgListConf.add(tmpFsrUsg);
                tmpFsrUsgListConfForInvty.add(tmpFsrUsg);

                invtyAvalQty = invtyAvalQty.subtract(trgtQty);
                setValue(tmpInvty.invtyAvalQty, invtyAvalQty);
            } else {
                trgtQty = invtyAvalQty;
                usgQty = usgQty.subtract(invtyAvalQty);
                tmpFsrUsg = changeFsrUsg(pMsg, dtlPMsg, tmpInvty.mdseCd.getValue(), trgtQty, PKG_UOM.EACH);
                tmpFsrUsgListConf.add(tmpFsrUsg);
                tmpFsrUsgListConfForInvty.add(tmpFsrUsg);

                invtyAvalQty = BigDecimal.ZERO;
                setValue(tmpInvty.invtyAvalQty, invtyAvalQty);
            }
        }
        return usgQty;
    }

    private NSZC005001_xxFsrUsgListPMsg changeFsrUsg(NSZC005001PMsg pMsg, NSZC005001_xxFsrUsgListPMsg dtlPMsg, String mdseCd, BigDecimal trgtQty, String uomCd) {
        NSZC005001_xxFsrUsgListPMsg changeDtlPMsg = new NSZC005001_xxFsrUsgListPMsg();
        EZDMsg.copy(dtlPMsg, null, changeDtlPMsg, null);
        if (hasValue(mdseCd)) {
            setValue(changeDtlPMsg.mdseCd, mdseCd);
            // Add Start 2019/02/13 K.Fujimoto QC#30310
            changeDtlPMsg.fsrUsgPk.clear();
            // Add End   2019/02/13 K.Fujimoto QC#30310
            MDSETMsg mdseTMsg = getMdseFindByKey(pMsg.glblCmpyCd.getValue(), mdseCd);
            if (mdseTMsg != null) {
                setValue(changeDtlPMsg.mdseNm, mdseTMsg.mdseNm);
            } else {
                changeDtlPMsg.mdseNm.clear();
            }
        }
        if (hasValue(trgtQty)) {
            setValue(changeDtlPMsg.svcPrtQty, trgtQty);
        }
        if (hasValue(uomCd)) {
            setValue(changeDtlPMsg.uomCd, uomCd);
        }
        return changeDtlPMsg;
    }
    // mod end 2015/12/24 CSA Defect#921

    private boolean calculatePrice(NSZC005001PMsg pMsg) {
        NSZC061001PMsg apiPMsg = new NSZC061001PMsg();

        if (MODE_INSTALL_CHECK.equals(pMsg.xxModeCd.getValue()) && (!this.fsrCompleteFlg || (this.fsrCompleteFlg && this.completionBean.getFsrUsgListConf().size() <= 0))) {
            return true;
        }
        // Header
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NSZC061001Constant.PROCESS_MODE_CALL_CLOSE);
        setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        // add start 2015/12/16 CSA Defect#978
        setValue(apiPMsg.ccyCd, pMsg.invCcyCd);
        // add end 2015/12/16 CSA Defect#978
        setValue(apiPMsg.startDt, pMsg.slsDt);
        setValue(apiPMsg.startTm, this.sysTm);
        setValue(apiPMsg.fsrNum, pMsg.fsrNum);

        // Detail
        if (MODE_DEBRIEF.equals(pMsg.xxModeCd.getValue())) {
            setValue(apiPMsg.svcTaskNumList.no(0).svcTaskNum, this.completionBean.getFsrVisitTMsg().svcTaskNum);

            // mod start 2016/04/26 CSA Defect#7519
            // ConvertTime
            String ctryCd = null;
            String postCd = null;
            String arvDt = null;
            String arvTm = null;
            Map<String, Object> shipToInfoMap = getShipToInfo(pMsg.glblCmpyCd.getValue(), this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue());
            if (shipToInfoMap != null) {
                ctryCd = (String) shipToInfoMap.get("CTRY_CD");
                postCd = (String) shipToInfoMap.get("POST_CD");
            }
            StringBuilder arvTsBuilder = new StringBuilder();
            arvTsBuilder.append(pMsg.fsrVisitArvDt.getValue());
            arvTsBuilder.append(pMsg.fsrVisitArvTm.getValue());
            String arvTs = arvTsBuilder.toString();
            String cnvtArvTs = null;
            SvcTimeZoneInfo tzArvTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, arvTs, ctryCd, postCd);
            if (tzArvTs != null) {
                cnvtArvTs = tzArvTs.getDateTime();
                arvDt = cnvtArvTs.substring(0, LEN_DT);
                arvTm = cnvtArvTs.substring(LEN_DT, LEN_DT_TM);
            }
            setValue(apiPMsg.svcTaskNumList.no(0).fsrVisitArvDt, arvDt);
            setValue(apiPMsg.svcTaskNumList.no(0).fsrVisitArvTm, arvTm);
            // mod end 2016/04/26 CSA Defect#7519
            setValue(apiPMsg.svcTaskNumList.no(0).svcLborTmNum, this.completionBean.getSvcLborTmNum());
            setValue(apiPMsg.svcTaskNumList.no(0).svcTrvlTmNum, pMsg.svcTrvlTmNum);
            apiPMsg.svcTaskNumList.setValidCount(1);
        }

        // add start 2016/04/20 CSA Defect#4469
        if (MODE_INSTALL_CHECK.equals(pMsg.xxModeCd.getValue())) {
            String fsrVisitNum = this.completionBean.getFsrVisitTMsg().fsrVisitNum.getValue();
            String svcTaskNum = this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue();

            FSR_VISIT_TASKTMsg fsrVisitTaskTMsg = getFsrVisiTaskFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), fsrVisitNum, svcTaskNum);
            if (fsrVisitTaskTMsg == null) {
                setErrMsg(pMsg, NSZM0862E);
                return false;
            }
            setValue(apiPMsg.ccyCd, this.completionBean.getSvcTaskTMsg().invCcyCd);
            setValue(apiPMsg.svcTaskNumList.no(0).svcTaskNum, svcTaskNum);
            setValue(apiPMsg.svcTaskNumList.no(0).fsrVisitArvDt, this.completionBean.getFsrVisitTMsg().fsrVisitArvDt);
            setValue(apiPMsg.svcTaskNumList.no(0).fsrVisitArvTm, this.completionBean.getFsrVisitTMsg().fsrVisitArvTm);
            setValue(apiPMsg.svcTaskNumList.no(0).svcLborTmNum, fsrVisitTaskTMsg.svcLborTmNum);
            setValue(apiPMsg.svcTaskNumList.no(0).svcTrvlTmNum, this.completionBean.getFsrVisitTMsg().svcTrvlTmNum);
            apiPMsg.svcTaskNumList.setValidCount(1);
        }
        // add end 2016/04/20 CSA Defect#4469

        if (fsrCompleteFlg) {
            for (int i = 0; i < this.completionBean.getFsrUsgListConf().size(); i++) {
                NSZC005001_xxFsrUsgListPMsg dtlPMsg = this.completionBean.getFsrUsgListConf().get(i);
                setValue(apiPMsg.partsMdseList.no(i).svcTaskNum, dtlPMsg.svcTaskNum);
                setValue(apiPMsg.partsMdseList.no(i).mdseCd, dtlPMsg.mdseCd);
                setValue(apiPMsg.partsMdseList.no(i).svcPrtQty, dtlPMsg.svcPrtQty);
                setValue(apiPMsg.partsMdseList.no(i).pkgUomCd, dtlPMsg.uomCd);
            }
            apiPMsg.partsMdseList.setValidCount(this.completionBean.getFsrUsgListConf().size());
        } else {
            for (int i = 0; i < pMsg.xxFsrUsgList.getValidCount(); i++) {
                NSZC005001_xxFsrUsgListPMsg dtlPMsg = pMsg.xxFsrUsgList.no(i);
                setValue(apiPMsg.partsMdseList.no(i).svcTaskNum, dtlPMsg.svcTaskNum);
                setValue(apiPMsg.partsMdseList.no(i).mdseCd, dtlPMsg.mdseCd);
                setValue(apiPMsg.partsMdseList.no(i).svcPrtQty, dtlPMsg.svcPrtQty);
                setValue(apiPMsg.partsMdseList.no(i).pkgUomCd, dtlPMsg.uomCd);
            }
            apiPMsg.partsMdseList.setValidCount(pMsg.xxFsrUsgList.getValidCount());

        }

        for (int i = 0; i < pMsg.xxExpenseList.getValidCount(); i++) {
            NSZC005001_xxExpenseListPMsg dtlPMsg = pMsg.xxExpenseList.no(i);
            setValue(apiPMsg.expMdseList.no(i).svcTaskNum, dtlPMsg.svcTaskNum);
            setValue(apiPMsg.expMdseList.no(i).mdseCd, dtlPMsg.mdseCd);
            setValue(apiPMsg.expMdseList.no(i).expQty, dtlPMsg.svcExpQty);
            setValue(apiPMsg.expMdseList.no(i).pkgUomCd, dtlPMsg.uomCd);
        }
        apiPMsg.expMdseList.setValidCount(pMsg.xxExpenseList.getValidCount());

        new NSZC061001().execute(apiPMsg, onbatchType);
        if (!checkErrMsg(pMsg, apiPMsg)) {
            return false;
        }
        this.completionBean.setSvcPrcApiPMsg(apiPMsg);

        return true;
    }

    private boolean calculatePriceForFsrChrg(NSZC005001PMsg pMsg, FSRTMsg fsr, List<Map<String, Object>> lborList
            , FSR_USGTMsgArray fsrUsgList, FSR_VISITTMsgArray fsrVisitList, FSR_EXPTMsgArray fsrExpList) {
        NSZC061001PMsg apiPMsg = new NSZC061001PMsg();

        // Header
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NSZC061001Constant.PROCESS_MODE_CALL_CLOSE);
        setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValue(apiPMsg.ccyCd, fsr.invCcyCd);
        setValue(apiPMsg.startDt, pMsg.slsDt);
        setValue(apiPMsg.startTm, this.sysTm);
        setValue(apiPMsg.fsrNum, pMsg.fsrNum);

        // Detail
        int lborIdx = 0;
        for (; lborIdx < lborList.size(); lborIdx++) {
            Map<String, Object> lborInfo = lborList.get(lborIdx);
            String svcTaskNum = (String) lborInfo.get("SVC_TASK_NUM");
            FSR_VISITTMsg fsrVisit = null;
            for (int i = 0; i < fsrVisitList.getValidCount(); i++) {
                if (svcTaskNum.equals(fsrVisitList.no(i).svcTaskNum.getValue())) {
                    fsrVisit = fsrVisitList.no(i);
                    break;
                }
            }
            setValue(apiPMsg.svcTaskNumList.no(lborIdx).svcTaskNum, svcTaskNum);
            setValue(apiPMsg.svcTaskNumList.no(lborIdx).fsrVisitArvDt, fsrVisit.fsrVisitArvDt);
            setValue(apiPMsg.svcTaskNumList.no(lborIdx).fsrVisitArvTm, fsrVisit.fsrVisitArvTm);
            setValue(apiPMsg.svcTaskNumList.no(lborIdx).svcLborTmNum, (BigDecimal) lborInfo.get("SVC_LBOR_TM_NUM"));
            setValue(apiPMsg.svcTaskNumList.no(lborIdx).svcTrvlTmNum, fsrVisit.svcTrvlTmNum);

            // mod start 2016/10/13 CSA Defect#13987
//            // add start 2016/09/20 CSA Defect#13987
//            NSZC005001_xxChargesListPMsg chrg = null;
//            for (int i = 0; i < pMsg.xxChargesList.getValidCount(); i++) {
//                if (svcTaskNum.equals(pMsg.xxChargesList.no(i).svcTaskNum.getValue())) {
//                    chrg = pMsg.xxChargesList.no(i);
//                    break;
//                }
//            }
//            if (chrg != null) {
//                setValue(apiPMsg.svcTaskNumList.no(lborIdx).svcChrgQty, chrg.svcChrgQty);
//                setValue(apiPMsg.svcTaskNumList.no(lborIdx).ovrdSvcChrgUnitAmt, chrg.ovrdSvcChrgUnitAmt);
//            }
//            // add end 2016/09/20 CSA Defect#13987

            for (int i = 0; i < pMsg.xxChargesList.getValidCount(); i++) {
                if (svcTaskNum.equals(pMsg.xxChargesList.no(i).svcTaskNum.getValue())) {
                    if (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(pMsg.xxChargesList.no(i).svcInvChrgTpCd.getValue())) {
                        setValue(apiPMsg.svcTaskNumList.no(lborIdx).svcChrgQty_LB, pMsg.xxChargesList.no(i).svcChrgQty);
                        setValue(apiPMsg.svcTaskNumList.no(lborIdx).ovrdSvcChrgUnitAmt_LB, pMsg.xxChargesList.no(i).ovrdSvcChrgUnitAmt);
                    } else if (SVC_INV_CHRG_TP.TRAVEL_CHARGE.equals(pMsg.xxChargesList.no(i).svcInvChrgTpCd.getValue())) {
                        setValue(apiPMsg.svcTaskNumList.no(lborIdx).svcChrgQty_TR, pMsg.xxChargesList.no(i).svcChrgQty);
                        setValue(apiPMsg.svcTaskNumList.no(lborIdx).ovrdSvcChrgUnitAmt_TR, pMsg.xxChargesList.no(i).ovrdSvcChrgUnitAmt);
                    }
                }
            }
            // mod end 2016/10/13 CSA Defect#13987
        }
        apiPMsg.svcTaskNumList.setValidCount(lborIdx);

        int usgIdx = 0;
        for (; usgIdx < fsrUsgList.getValidCount(); usgIdx++) {
            FSR_USGTMsg fsrUsg = fsrUsgList.no(usgIdx);
            setValue(apiPMsg.partsMdseList.no(usgIdx).svcTaskNum, fsrUsg.svcTaskNum);
            setValue(apiPMsg.partsMdseList.no(usgIdx).mdseCd, fsrUsg.mdseCd);
            setValue(apiPMsg.partsMdseList.no(usgIdx).svcPrtQty, fsrUsg.svcPrtQty);
            setValue(apiPMsg.partsMdseList.no(usgIdx).pkgUomCd, fsrUsg.uomCd);
        }
        apiPMsg.partsMdseList.setValidCount(usgIdx);

        int expIdx = 0;
        for (; expIdx < fsrExpList.getValidCount(); expIdx++) {
            FSR_EXPTMsg fsrExp = fsrExpList.no(expIdx);
            setValue(apiPMsg.expMdseList.no(expIdx).svcTaskNum, fsrExp.svcTaskNum);
            setValue(apiPMsg.expMdseList.no(expIdx).mdseCd, fsrExp.mdseCd);
            setValue(apiPMsg.expMdseList.no(expIdx).expQty, fsrExp.svcExpQty);
            setValue(apiPMsg.expMdseList.no(expIdx).pkgUomCd, fsrExp.uomCd);
        }
        apiPMsg.expMdseList.setValidCount(expIdx);

        new NSZC061001().execute(apiPMsg, onbatchType);
        if (!checkErrMsg(pMsg, apiPMsg)) {
            return false;
        }
        this.completionBean.setSvcPrcApiPMsg(apiPMsg);

        return true;
    }

    private boolean inputCheck(NSZC005001PMsg pMsg) {
        inputCheckHeader(pMsg);
        if (isError(pMsg)) {
            return false;
        }

        inputCheckVisitTask(pMsg);
        if (isError(pMsg)) {
            return false;
        }

        inputCheckFsrUsg(pMsg);
        if (isError(pMsg)) {
            return false;
        }

        inputCheckMtrRead(pMsg);
        if (isError(pMsg)) {
            return false;
        }

        inputCheckTmEvent(pMsg);
        if (isError(pMsg)) {
            return false;
        }

        inputCheckExpense(pMsg);
        if (isError(pMsg)) {
            return false;
        }
        return true;
    }

    private boolean inputCheckChargableMode(NSZC005001PMsg pMsg) {
        inputCheckHeaderChargable(pMsg);
        if (isError(pMsg)) {
            return false;
        }

        inputCheckChargable(pMsg);
        if (isError(pMsg)) {
            return false;
        }
        return true;
    }
    private boolean inputCheckIstallCheckMode(NSZC005001PMsg pMsg) {
        inputCheckHeaderInstallCheck(pMsg);
        if (isError(pMsg)) {
            return false;
        }
        inputCheckFsrInstallCheck(pMsg);
        if (isError(pMsg)) {
            return false;
        }
        return true;
    }
    private void inputCheckHeaderChargable(NSZC005001PMsg pMsg) {
        if (!hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(pMsg, NSZM0001E);
        }
        if (!hasValue(pMsg.slsDt)) {
            setErrMsg(pMsg, NSZM0002E);
        }
        if (!hasValue(pMsg.userId)) {
            setErrMsg(pMsg, NSZM0163E);
        }
        if (!hasValue(pMsg.fsrNum)) {
            setErrMsg(pMsg, NSZM0180E);
        }
        if (!hasValue(pMsg.svcMachMstrPk)) {
            setErrMsg(pMsg, NSZM0074E);
        }
        // mod start 2016/02/08 CSA Defect#2863
        if (!frceUpdFlg) {
            if (!hasValue(pMsg.invCcyCd)) {
                setErrMsg(pMsg, NSZM0070E);
            }
        }
        // mod end 2016/02/08 CSA Defect#2863
        FSRTMsg fsrTMsg = getFsrFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (checkRtnCodeForSearch(pMsg, fsrTMsg, new FSRTMsg())) {
            // Add Start 2019/01/10 K.Fujimoto QC#26861
            this.currentFsrSts = fsrTMsg.fsrStsCd.getValue();
            // Add End   2019/01/10 K.Fujimoto QC#26861
            if (MODE_DEBRIEF.equals(pMsg.xxModeCd.getValue())) {
                // Mod Start 2019/01/10 K.Fujimoto QC#26861
                // if (SVC_TASK_STS.CLOSED.equals(fsrTMsg.fsrStsCd.getValue())
                //         || SVC_TASK_STS.CANCELLED.equals(fsrTMsg.fsrStsCd.getValue())) {
                if (SVC_TASK_STS.CANCELLED.equals(fsrTMsg.fsrStsCd.getValue())) {
                // Mod End   2019/01/10 K.Fujimoto QC#26861
                    setErrMsg(pMsg, NSZM0154E);
                }
            } else if (MODE_CHARGABLE.equals(pMsg.xxModeCd.getValue())) {
                // Mod Start 2019/01/10 K.Fujimoto QC#26861
                // if (!SVC_TASK_STS.PENDING_CHARGE.equals(fsrTMsg.fsrStsCd.getValue())) {
                if (!SVC_TASK_STS.PENDING_CHARGE.equals(fsrTMsg.fsrStsCd.getValue())
                        && !SVC_TASK_STS.CLOSED.equals(fsrTMsg.fsrStsCd.getValue())) {
                // Mod End   2019/01/10 K.Fujimoto QC#26861
                    setErrMsg(pMsg, NSZM0154E);
                }
            }
        }
    }
    private void inputCheckHeaderInstallCheck(NSZC005001PMsg pMsg) {
        if (!hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(pMsg, NSZM0001E);
        }
        if (!hasValue(pMsg.slsDt)) {
            setErrMsg(pMsg, NSZM0002E);
        }
        if (!hasValue(pMsg.userId)) {
            setErrMsg(pMsg, NSZM0163E);
        }
        if (!hasValue(pMsg.fsrNum)) {
            setErrMsg(pMsg, NSZM0180E);
        }
        if (!hasValue(pMsg.svcMachMstrPk)) {
            setErrMsg(pMsg, NSZM0074E);
        }
        // Del Start 2018/10/18 QC#28862
//        FSRTMsg fsrTMsg = getFsrFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
//        if (checkRtnCodeForSearch(pMsg, fsrTMsg, new FSRTMsg())) {
//            if (SVC_TASK_STS.CLOSED.equals(fsrTMsg.fsrStsCd.getValue())
//                    || SVC_TASK_STS.CANCELLED.equals(fsrTMsg.fsrStsCd.getValue())) {
//                setErrMsg(pMsg, NSZM0154E);
//            }
//        }
        // Del End 2018/10/18 QC#28862
    }

    private void inputCheckChargable(NSZC005001PMsg pMsg) {
        int size = pMsg.xxChargesList.getValidCount();

        for (int i = 0; i < size; i++) {
            if (!hasValue(pMsg.xxChargesList.no(i).fsrNum)) {
                setErrMsg(pMsg, NSZM0180E);
            }
            
            // Add Start 2019/01/10 K.Fujimoto QC#26861
            if (!hasValue(pMsg.xxChargesList.no(i).svcTaskNum) && hasValue(pMsg.xxChargesList.no(i).fsrChrgDtlPk)) {
                FSR_CHRG_DTLTMsg tMsg = getFsrChrgDtlFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.xxChargesList.no(i).fsrChrgDtlPk.getValue());
                if(tMsg != null) {
                    setValue(pMsg.xxChargesList.no(i).svcTaskNum, tMsg.svcTaskNum);
                }
            }
            // Add End   2019/01/10 K.Fujimoto QC#26861
            
            // del start 2016/05/19 CSA Defect#8457
            // if (!frceUpdFlg) {
            //    if (!hasValue(pMsg.xxChargesList.no(i).fsrVisitNum)) {
            //        setErrMsg(pMsg, NSZM0213E);
            //    }
            // }
            // if (!hasValue(pMsg.xxChargesList.no(i).svcTaskNum)) {
            //     setErrMsg(pMsg, NSZM0082E);
            // }
            // del end 2016/05/19 CSA Defect#8457
            // mod start 2016/02/08 CSA Defect#2863
            if (!frceUpdFlg) {
                if (!hasValue(pMsg.xxChargesList.no(i).svcChrgTrxTpCd)) {
                    setErrMsg(pMsg, NSZM0807E);
                }
                if (!hasValue(pMsg.xxChargesList.no(i).mdseCd)) {
                    setErrMsg(pMsg, NSZM0249E);
                }
                if (!hasValue(pMsg.xxChargesList.no(i).svcChrgQty)) {
                    setErrMsg(pMsg, NSZM0808E);
                }
                if (!hasValue(pMsg.xxChargesList.no(i).uomCd)) {
                    setErrMsg(pMsg, NSZM0644E);
                }
                if (!hasValue(pMsg.xxChargesList.no(i).svcChrgUnitAmt)) {
                    setErrMsg(pMsg, NSZM0809E);
                }
                // del start 2016/05/19 CSA Defect#8457
                // if (!hasValue(pMsg.xxChargesList.no(i).svcChrgDealAmt)) {
                //     setErrMsg(pMsg, NSZM0810E);
                // }
                // del end 2016/05/19 CSA Defect#8457
            }
            // mod end 2016/02/08 CSA Defect#2863
            if (!hasValue(pMsg.xxChargesList.no(i).svcChrgDiscRate)) {
                setValue(pMsg.xxChargesList.no(i).svcChrgDiscRate, BigDecimal.ZERO);
            }
            if (!hasValue(pMsg.xxChargesList.no(i).svcChrgDealDiscAmt)) {
                setValue(pMsg.xxChargesList.no(i).svcChrgDealDiscAmt, BigDecimal.ZERO);
            }
            // mod start 2016/02/08 CSA Defect#2863
            if (!frceUpdFlg) {
                if (!hasValue(pMsg.xxChargesList.no(i).svcChrgFlg)) {
                    setErrMsg(pMsg, NSZM0811E);
                }
                // del start 08/22/2016 QC#13611
                // if (!hasValue(pMsg.xxChargesList.no(i).prcCatgCd)) {
                //     setErrMsg(pMsg, NSZM0812E);
                // }
                // del end 08/22/2016 QC#13611
                if (!hasValue(pMsg.xxChargesList.no(i).svcInvChrgTpCd)) {
                    setErrMsg(pMsg, NSZM0127E);
                }
            }
            // mod end 2016/02/08 CSA Defect#2863
        }
    }

    private void inputCheckHeader(NSZC005001PMsg pMsg) {
        if (!hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(pMsg, NSZM0001E);
        }
        if (!hasValue(pMsg.slsDt)) {
            setErrMsg(pMsg, NSZM0002E);
        }
        if (!hasValue(pMsg.userId)) {
            setErrMsg(pMsg, NSZM0163E);
        }
        if (!hasValue(pMsg.fsrNum)) {
            setErrMsg(pMsg, NSZM0180E);
        }
        // mod start 2016/02/08 CSA Defect#2863
        if (!frceUpdFlg) {
            if (!hasValue(pMsg.fsrVisitNum)) {
                setErrMsg(pMsg, NSZM0213E);
            }
        }
        // mod end 2016/02/08 CSA Defect#2863
        if (!hasValue(pMsg.svcMachMstrPk)) {
            setErrMsg(pMsg, NSZM0074E);
        }
        // mod start 2016/02/08 CSA Defect#2863
        if (!frceUpdFlg) {
            if (!hasValue(pMsg.fsrVisitArvDt)) {
                setErrMsg(pMsg, NSZM0214E);
            }
            if (!hasValue(pMsg.fsrVisitArvTm)) {
                setErrMsg(pMsg, NSZM0215E);
            }
        }
        // mod end 2016/02/08 CSA Defect#2863
        if (!hasValue(pMsg.fsrVisitCpltDt)) {
            setErrMsg(pMsg, NSZM0216E);
        }
        if (!hasValue(pMsg.fsrVisitCpltTm)) {
            setErrMsg(pMsg, NSZM0217E);
        }
        if (!hasValue(pMsg.svcTrvlTmNum)) {
            setErrMsg(pMsg, NSZM0218E);
        }
        // mod start 2016/02/08 CSA Defect#2863
        if (!frceUpdFlg) {
            if (!hasValue(pMsg.invCcyCd)) {
                setErrMsg(pMsg, NSZM0070E);
            }
            if (!hasValue(pMsg.ccyExchRate)) {
                setErrMsg(pMsg, NSZM0071E);
            }
            if (!hasValue(pMsg.svcBillTpCd)) {
                setErrMsg(pMsg, NSZM0050E);
            }
            if (!hasValue(pMsg.pmtTermCashDiscCd)) {
                setErrMsg(pMsg, NSZM0126E);
            }
        }
        // mod end 2016/02/08 CSA Defect#2863
        // add start 2015/12/16 CSA Defect#978
        FSRTMsg fsrTMsg = getFsrFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (checkRtnCodeForSearch(pMsg, fsrTMsg, new FSRTMsg())) {
            // Add Start 2019/01/10 K.Fujimoto QC#26861
            this.currentFsrSts = fsrTMsg.fsrStsCd.getValue();
            // Add End   2019/01/10 K.Fujimoto QC#26861
            if (MODE_DEBRIEF.equals(pMsg.xxModeCd.getValue())) {
                // Mod Start 2019/01/10 K.Fujimoto QC#26861
                if (SVC_TASK_STS.CANCELLED.equals(fsrTMsg.fsrStsCd.getValue())) {
                // if (SVC_TASK_STS.CLOSED.equals(fsrTMsg.fsrStsCd.getValue())
                //         || SVC_TASK_STS.CANCELLED.equals(fsrTMsg.fsrStsCd.getValue())) {
                // Mod End 2019/01/10 K.Fujimoto QC#26861
                    setErrMsg(pMsg, NSZM0154E);
                }
            } else if (MODE_CHARGABLE.equals(pMsg.xxModeCd.getValue())) {
                // Mod Start 2019/01/10 K.Fujimoto QC#26861
                if (!SVC_TASK_STS.PENDING_CHARGE.equals(fsrTMsg.fsrStsCd.getValue())
                        && !SVC_TASK_STS.CLOSED.equals(fsrTMsg.fsrStsCd.getValue())) {
                // if (!SVC_TASK_STS.PENDING_CHARGE.equals(fsrTMsg.fsrStsCd.getValue())) {
                    setErrMsg(pMsg, NSZM0154E);
                // Mod End 2019/01/10 K.Fujimoto QC#26861
                }
            }
        }
        // add end 2015/12/16 CSA Defect#978
    }

    private void inputCheckVisitTask(NSZC005001PMsg pMsg) {
        int size = pMsg.xxVisitTaskList.getValidCount();

        for (int i = 0; i < size; i++) {
            if (!hasValue(pMsg.xxVisitTaskList.no(i).svcTaskNum)) {
                setErrMsg(pMsg, NSZM0082E);
            }
            // mod start 2016/02/08 CSA Defect#2863
            if (!frceUpdFlg) {
                if (!hasValue(pMsg.xxVisitTaskList.no(i).svcPblmTpCd)) {
                    // Mod Start 2019/08/30 K.Fujimoto QC#52433
                    //setErrMsg(pMsg, NSZM0243E);
                    if (!insertSvcMemo(pMsg, pMsg.xxVisitTaskList.no(i).svcTaskNum.getValue(), S21MessageFunc.clspGetMessage(NSZM0243E))) {
                        return;
                    }
                    this.isDebreifError = true;
                    // Mod End   2019/08/30 K.Fujimoto QC#5215243328
                }
// mod start 2016/10/20 CSA Defect#14682
//                if (!hasValue(pMsg.xxVisitTaskList.no(i).svcPblmLocTpCd)) {
                if (!hasValue(pMsg.xxModeCd_AD) && !hasValue(pMsg.xxVisitTaskList.no(i).svcPblmLocTpCd)) {
// mod end 2016/10/20 CSA Defect#14682
                    // Mod Start 2019/08/30 K.Fujimoto QC#52433
                    // setErrMsg(pMsg, NSZM0244E);
                    if (!insertSvcMemo(pMsg, pMsg.xxVisitTaskList.no(i).svcTaskNum.getValue(), S21MessageFunc.clspGetMessage(NSZM0244E))) {
                        return;
                    }
                    this.isDebreifError = true;
                    // Mod End   2019/08/30 K.Fujimoto QC#52433
                }
// mod start 2016/10/20 CSA Defect#14682
//                if (!hasValue(pMsg.xxVisitTaskList.no(i).svcPblmRsnTpCd)) {
                if (!hasValue(pMsg.xxModeCd_AD) && !hasValue(pMsg.xxVisitTaskList.no(i).svcPblmRsnTpCd)) {
// mod end 2016/10/20 CSA Defect#14682
                    // Mod Start 2019/08/30 K.Fujimoto QC#52433
                    // setErrMsg(pMsg, NSZM0245E);
                    if (!insertSvcMemo(pMsg, pMsg.xxVisitTaskList.no(i).svcTaskNum.getValue(), S21MessageFunc.clspGetMessage(NSZM0245E))) {
                        return;
                    }
                    this.isDebreifError = true;
                    // Mod End   2019/08/30 K.Fujimoto QC#52433
                }
// mod start 2016/10/20 CSA Defect#14682
//                if (!hasValue(pMsg.xxVisitTaskList.no(i).svcPblmCrctTpCd)) {
                if (!hasValue(pMsg.xxModeCd_AD) && !hasValue(pMsg.xxVisitTaskList.no(i).svcPblmCrctTpCd)) {
// mod end 2016/10/20 CSA Defect#14682
                    // Mod Start 2019/08/30 K.Fujimoto QC#52433
                    // setErrMsg(pMsg, NSZM0246E);
                    if (!insertSvcMemo(pMsg, pMsg.xxVisitTaskList.no(i).svcTaskNum.getValue(), S21MessageFunc.clspGetMessage(NSZM0246E))) {
                        return;
                    }
                    this.isDebreifError = true;
                    // Mod End   2019/08/30 K.Fujimoto QC#52433
                }
                if (!hasValue(pMsg.xxVisitTaskList.no(i).svcLborTmNum)) {
                    setErrMsg(pMsg, NSZM0247E);
                }
                if (!hasValue(pMsg.xxVisitTaskList.no(i).xxVisitTaskCpltFlg)) {
                    setErrMsg(pMsg, NSZM0280E);
                }
            }
            // mod end 2016/02/08 CSA Defect#2863
        }
    }

    private void inputCheckFsrUsg(NSZC005001PMsg pMsg) {
        int size = pMsg.xxFsrUsgList.getValidCount();

        for (int i = 0; i < size; i++) {
            if (!hasValue(pMsg.xxFsrUsgList.no(i).svcTaskNum)) {
                setErrMsg(pMsg, NSZM0082E);
            }
            if (!hasValue(pMsg.xxFsrUsgList.no(i).dsSvcCallTpCd)) {
                setErrMsg(pMsg, NSZM0049E);
            }
            // START 2018/01/15 K.Ochiai [QC#18646, MOD]
            if (!hasValue(pMsg.xxFsrUsgList.no(i).prtUsedByTechLocCd) && !ZYPConstant.FLG_ON_Y.equals(pMsg.xxFsrUsgList.no(i).svcInvtyExFlg.getValue())) {
                setErrMsg(pMsg, NSZM0514E);
            }
            // END 2018/01/15 K.Ochiai [QC#18646, MOD]
            if (!hasValue(pMsg.xxFsrUsgList.no(i).prtUsedByTechCd)) {
                setErrMsg(pMsg, NSZM0248E);
            }
            if (!hasValue(pMsg.xxFsrUsgList.no(i).mdseCd)) {
                setErrMsg(pMsg, NSZM0013E);
            }
            if (!hasValue(pMsg.xxFsrUsgList.no(i).svcPrtQty)) {
                setErrMsg(pMsg, NSZM0250E);
            }
            if (!hasValue(pMsg.xxFsrUsgList.no(i).uomCd)) {
                setErrMsg(pMsg, NSZM0738E);
            }
            if (!hasValue(pMsg.xxFsrUsgList.no(i).svcExecDt)) {
                setErrMsg(pMsg, NSZM0739E);
            }
            if (!hasValue(pMsg.xxFsrUsgList.no(i).fsrUsgProcFlg)) {
                setErrMsg(pMsg, NSZM0260E);
            }
            if (!hasValue(pMsg.xxFsrUsgList.no(i).svcInvtyExFlg)) {
                setErrMsg(pMsg, NSZM0261E);
            }
        }
    }

    private void inputCheckMtrRead(NSZC005001PMsg pMsg) {
        int size = pMsg.xxMtrReadList.getValidCount();

        for (int i = 0; i < size; i++) {
            if (!hasValue(pMsg.xxMtrReadList.no(i).mdseCd)) {
                setErrMsg(pMsg, NSZM0013E);
            }
            if (!hasValue(pMsg.xxMtrReadList.no(i).mtrDt)) {
                setErrMsg(pMsg, NSZM0265E);
            }
            if (!hasValue(pMsg.xxMtrReadList.no(i).mtrCnt)) {
                setErrMsg(pMsg, NSZM0266E);
            }
            if (!hasValue(pMsg.xxMtrReadList.no(i).mtrReadDt)) {
                setErrMsg(pMsg, NSZM0268E);
            }
            // del start 2017/09/07 QC#15134
//            if (!hasValue(pMsg.xxMtrReadList.no(i).mtrReadTpCd)) {
//                setErrMsg(pMsg, NSZM0269E);
//            }
            // del end 2017/09/07 QC#15134
            if (!hasValue(pMsg.xxMtrReadList.no(i).mtrReadSrcTpCd)) {
                setErrMsg(pMsg, NSZM0270E);
            }
            // del start 2016/03/17 CSA Defect#5588
//            if (!hasValue(pMsg.xxMtrReadList.no(i).dsContrNum)) {
//                setErrMsg(pMsg, NSZM0271E);
//            }
            // del end 2016/03/17 CSA Defect#5588
            if (!hasValue(pMsg.xxMtrReadList.no(i).dsMtrReadTpGrpCd)) {
                setErrMsg(pMsg, NSZM0741E);
            }
            if (!hasValue(pMsg.xxMtrReadList.no(i).mtrLbCd) && !hasValue(pMsg.xxMtrReadList.no(i).svcPhysMtrPk)) {
                setErrMsg(pMsg, NSZM0745E);
            }
        }
    }

    private void inputCheckTmEvent(NSZC005001PMsg pMsg) {
        int size = pMsg.xxTmEventList.getValidCount();

        for (int i = 0; i < size; i++) {
            if (!hasValue(pMsg.xxTmEventList.no(i).fsrNum)) {
                setErrMsg(pMsg, NSZM0180E);
            }
            if (!hasValue(pMsg.xxTmEventList.no(i).fsrVisitNum)) {
                setErrMsg(pMsg, NSZM0213E);
            }
            if (!hasValue(pMsg.xxTmEventList.no(i).svcTmEventCd)) {
                setErrMsg(pMsg, NSZM0272E);
            }
            if (!hasValue(pMsg.xxTmEventList.no(i).svcTaskNum)) {
                setErrMsg(pMsg, NSZM0082E);
            }
            // mod start 2016/02/22 CSA Defect#2695
            // del start 2018/08/27 CSA Defect#27882
            //if (!isModification(pMsg.glblCmpyCd.getValue(), pMsg.xxTmEventList.no(i))) {
            // del end 2018/08/27 CSA Defect#27882
                if (!hasValue(pMsg.xxTmEventList.no(i).svcTmEventFromDt)) {
                    setErrMsg(pMsg, NSZM0273E);
                }
                if (!hasValue(pMsg.xxTmEventList.no(i).svcTmEventFromTm)) {
                    setErrMsg(pMsg, NSZM0274E);
                }
                if (!hasValue(pMsg.xxTmEventList.no(i).svcTmEventToDt)) {
                    setErrMsg(pMsg, NSZM0275E);
                }
                if (!hasValue(pMsg.xxTmEventList.no(i).svcTmEventToTm)) {
                    setErrMsg(pMsg, NSZM0276E);
                }
            // del start 2018/08/27 CSA Defect#27882
            //}
            // mod end 2016/02/22 CSA Defect#2695
            // del start 2018/08/27 CSA Defect#27882

            if (!hasValue(pMsg.xxTmEventList.no(i).durnTmNum)) {
                setErrMsg(pMsg, NSZM0761E);
            }
            if (!hasValue(pMsg.xxTmEventList.no(i).machDownFlg)) {
                setErrMsg(pMsg, NSZM0278E);
            }
        }
    }

    private void inputCheckExpense(NSZC005001PMsg pMsg) {
        int size = pMsg.xxExpenseList.getValidCount();

        for (int i = 0; i < size; i++) {
            if (!hasValue(pMsg.xxExpenseList.no(i).mdseCd)) {
                setErrMsg(pMsg, NSZM0013E);
            }
            if (!hasValue(pMsg.xxExpenseList.no(i).svcExpQty)) {
                setErrMsg(pMsg, NSZM0744E);
            }
            if (!hasValue(pMsg.xxExpenseList.no(i).uomCd)) {
                setErrMsg(pMsg, NSZM0738E);
            }
            if (!hasValue(pMsg.xxExpenseList.no(i).svcExecDt)) {
                setErrMsg(pMsg, NSZM0739E);
            }
            if (!hasValue(pMsg.xxExpenseList.no(i).techCd)) {
                setErrMsg(pMsg, NSZM0052E);
            }
        }

    }
    private void inputCheckFsrInstallCheck(NSZC005001PMsg pMsg) {
        int size = pMsg.xxFsrIstlChkList.getValidCount();
        // Mod Start 2018/10/18 QC#28862
        boolean errFlg = false;
        for (int i = 0; i < size; i++) {
            if (!hasValue(pMsg.xxFsrIstlChkList.no(i).fsrNum)) {
                setErrMsg(pMsg, NSZM0291E);
                errFlg = true;
            }
            if (!hasValue(pMsg.xxFsrIstlChkList.no(i).svcTaskNum)) {
                setErrMsg(pMsg, NSZM0082E);
                errFlg = true;
            }
            if (!hasValue(pMsg.xxFsrIstlChkList.no(i).svcConfigMstrPk)) {
                setErrMsg(pMsg, NSZM0570E);
                errFlg = true;
            }
            if (!hasValue(pMsg.xxFsrIstlChkList.no(i).mdseCd)) {
                setErrMsg(pMsg, NSZM0842E);
                errFlg = true;
            }
            // Del Start 2017/09/01 QC#20672
//            if (!hasValue(pMsg.xxFsrIstlChkList.no(i).istlChkVerFlg)) {
//                setErrMsg(pMsg, NSZM0843E);
//            }
            // Del End 2017/09/01 QC#20672
        }

        if (errFlg || size <= 0) {
            return;
        }
        String svcTaskNum = pMsg.xxFsrIstlChkList.no(0).svcTaskNum.getValue();
        FSRTMsg fsrTMsg = getFsrFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (checkRtnCodeForSearch(pMsg, fsrTMsg, new FSRTMsg())) {
            if (isDeInstall(pMsg.glblCmpyCd.getValue(), svcTaskNum)) {
                if (SVC_TASK_STS.CANCELLED.equals(fsrTMsg.fsrStsCd.getValue())) {
                    setErrMsg(pMsg, NSZM0154E);
                }
            } else {
                // START 2020/06/01 K.Kitachi [QC#56859, MOD]
//                if (SVC_TASK_STS.CLOSED.equals(fsrTMsg.fsrStsCd.getValue())
//                        || SVC_TASK_STS.CANCELLED.equals(fsrTMsg.fsrStsCd.getValue())) {
                if (SVC_TASK_STS.CANCELLED.equals(fsrTMsg.fsrStsCd.getValue())) {
                // END 2020/06/01 K.Kitachi [QC#56859, MOD]
                    setErrMsg(pMsg, NSZM0154E);
                }
            }
        }
        // Mod End 2018/10/18 QC#28862
    }
    private void formatTmItem(NSZC005001PMsg pMsg) {
        setValue(pMsg.fsrVisitArvTm, formatTm(pMsg.fsrVisitArvTm.getValue()));
        setValue(pMsg.fsrVisitCpltTm, formatTm(pMsg.fsrVisitCpltTm.getValue()));
        for (int i = 0; i < pMsg.xxTmEventList.getValidCount(); i++) {
            NSZC005001_xxTmEventListPMsg dtlPMsg = pMsg.xxTmEventList.no(i);
            setValue(dtlPMsg.svcTmEventFromTm, formatTm(dtlPMsg.svcTmEventFromTm.getValue()));
            setValue(dtlPMsg.svcTmEventToTm, formatTm(dtlPMsg.svcTmEventToTm.getValue()));
        }
    }

    private boolean insertFsrEvent(NSZC005001PMsg pMsg) {
        FSR_VISITTMsg fsrVisitTMsg = this.completionBean.getFsrVisitTMsg();

        FSR_EVENTTMsg fsrEventTMsg = new FSR_EVENTTMsg();
        // START 2022/04/15 [QC#59911, MOD]
//        setFsrEventValue(pMsg, fsrVisitTMsg, fsrEventTMsg);
        if (!setFsrEventValue(pMsg, fsrVisitTMsg, fsrEventTMsg)) {
            return true;
        }
        // END   2022/04/15 [QC#59911, MOD]
        S21ApiTBLAccessor.create(fsrEventTMsg);
        if (!checkRtnCodeForInsert(pMsg, fsrEventTMsg)) {
            return false;
        }
        return true;
    }

    private boolean insertFsrExp(NSZC005001PMsg pMsg) {
        if (pMsg.xxExpenseList.getValidCount() < 1) {
            return true;
        }

        if (!deleteFsrExp(pMsg)) {
            return false;
        }
        NSZC061001PMsg svcPrcApiPMsg = this.completionBean.getSvcPrcApiPMsg();
        for (int i = 0; i < pMsg.xxExpenseList.getValidCount(); i++) {
            NSZC005001_xxExpenseListPMsg dtlPMsg = pMsg.xxExpenseList.no(i);
            NSZC061001_xxExpMdseListPMsg apiDtlPMsg = svcPrcApiPMsg.xxExpMdseList.no(i);

            FSR_EXPTMsg fsrExpTMsg = new FSR_EXPTMsg();
            if (!setFsrExpValue(pMsg, dtlPMsg, apiDtlPMsg, fsrExpTMsg)) {
                return false;
            }
            S21ApiTBLAccessor.create(fsrExpTMsg);
            if (!checkRtnCodeForInsert(pMsg, fsrExpTMsg)) {
                return false;
            }

            // add start 2015/12/21 CSA Defect#1924
            if (!hasValue(fsrExpTMsg.svcExpDealAmt)) {
                fsrCompleteFlg = false;
                if (!updateSvcTaskStsCd(pMsg, SVC_TASK_STS.DEBRIEF_ERROR)) {
                    return false;
                }
            }
            // add end 2015/12/21 CSA Defect#1924
        }
        return true;
    }
    private boolean insertFsrInstallCheckList(NSZC005001PMsg pMsg) {
        if (pMsg.xxFsrIstlChkList.getValidCount() < 1) {
            return true;
        }

        if (!deleteFsrInstallCheckList(pMsg)) {
            return false;
        }

        for (int i = 0; i < pMsg.xxFsrIstlChkList.getValidCount(); i++) {
            NSZC005001_xxFsrIstlChkListPMsg dtlPMsg = pMsg.xxFsrIstlChkList.no(i);

            FSR_ISTL_CHK_LISTTMsg fsrIstlChkTMsg = new FSR_ISTL_CHK_LISTTMsg();
            if (!setFsrIstlChkValue(pMsg, dtlPMsg, fsrIstlChkTMsg)) {
                return false;
            }
            S21ApiTBLAccessor.create(fsrIstlChkTMsg);
            if (!checkRtnCodeForInsert(pMsg, fsrIstlChkTMsg)) {
                return false;
            }
        }
        return true;
    }

    // START 2015/12/14 K.Ochiai [QC#15735, ADD]
    private boolean checkSerNum(NSZC005001PMsg pMsg) {
        // Mail Template
        S21MailTemplate tmpl = getMailTemplate(pMsg, CMPL_MAIL_TMPL_ID);
        if (!hasValue(tmpl.getSubject()) || !hasValue(tmpl.getBody())) {
            setErrMsg(pMsg, NSZM0185E);
            return false;
        }
        Map<String, Object> mailInfo = new HashMap<String, Object>();
        StringBuilder mailBody = new StringBuilder();
        for (int i = 0; i < pMsg.xxFsrIstlChkList.getValidCount(); i++) {
            String correctSerNum = pMsg.xxFsrIstlChkList.no(i).crctSerNum.getValue();
            String currentSerNum = pMsg.xxFsrIstlChkList.no(i).serNum.getValue();
            String svcTaskNum = pMsg.xxFsrIstlChkList.no(i).svcTaskNum.getValue();

            if (hasValue(correctSerNum) && !currentSerNum.equals(correctSerNum)) {
                mailInfo = getMailInfo(pMsg, svcTaskNum);

                mailBody = setMailBody(correctSerNum, svcTaskNum, mailInfo, mailBody);
            }

        }
        if (mailInfo.size() == 0) {
            return true;
        }
        if (!sendMail(pMsg, tmpl, mailBody)) {
            return false;
        }
        return true;
    }

    private StringBuilder setMailBody(String correctSerNum, String svcTaskNum, Map<String, Object> mailInfo, StringBuilder mailBody) {
        // Appending Address
        StringBuilder addrBuilder = new StringBuilder();

        if (hasValue((String) mailInfo.get("FIRST_LINE_ADDR"))) {
            addrBuilder.append(mailInfo.get("FIRST_LINE_ADDR"));
        }
        if (hasValue((String) mailInfo.get("SCD_LINE_ADDR"))) {
            if (!addrBuilder.toString().isEmpty()) {
                addrBuilder.append(" ");
            }
            addrBuilder.append(mailInfo.get("SCD_LINE_ADDR"));
        }
        if (hasValue((String) mailInfo.get("THIRD_LINE_ADDR"))) {
            if (!addrBuilder.toString().isEmpty()) {
                addrBuilder.append(" ");
            }
            addrBuilder.append(mailInfo.get("THIRD_LINE_ADDR"));
        }
        if (hasValue((String) mailInfo.get("FRTH_LINE_ADDR"))) {
            if (!addrBuilder.toString().isEmpty()) {
                addrBuilder.append(" ");
            }
            addrBuilder.append((String) mailInfo.get("FRTH_LINE_ADDR"));
        }
        if (hasValue((String) mailInfo.get("CTY_ADDR"))) {
            if (!addrBuilder.toString().isEmpty()) {
                addrBuilder.append(", ");
            }
            addrBuilder.append(mailInfo.get("CTY_ADDR"));
        }
        if (hasValue((String) mailInfo.get("ST_CD"))) {
            if (!addrBuilder.toString().isEmpty()) {
                addrBuilder.append(", ");
            }
            addrBuilder.append(mailInfo.get("ST_CD"));
        }
        if (hasValue((String) mailInfo.get("POST_CD"))) {
            if (!addrBuilder.toString().isEmpty()) {
                addrBuilder.append(" ");
            }
            addrBuilder.append(mailInfo.get("POST_CD"));
        }
        String addr = addrBuilder.toString();

        String newLine = System.getProperty("line.separator");
        // set Parameter
        mailBody.append(String.format("Current serial#                 : %1$s", getStrFromMap((String) mailInfo.get("SER_NUM"))));
        mailBody.append(newLine);
        mailBody.append(String.format("Correct serial#                 : %1$s", correctSerNum));
        mailBody.append(newLine);
        mailBody.append(String.format("Install Base ID                 : %1$s", getBigFromMap((BigDecimal) mailInfo.get("SVC_MACH_MSTR_PK"))));
        mailBody.append(newLine);
        mailBody.append(String.format("Account Name                    : %1$s", getStrFromMap((String) mailInfo.get("DS_ACCT_NM"))));
        mailBody.append(newLine);
        mailBody.append(String.format("Current Location                : %1$s", getStrFromMap(addr)));
        mailBody.append(newLine);
        mailBody.append(String.format("Customer Telephone#             : %1$s", getStrFromMap((String) mailInfo.get("CUST_TEL_NUM"))));
        mailBody.append(newLine);
        mailBody.append(String.format("Customer Extn.                  : %1$s", getStrFromMap((String) mailInfo.get("CUST_TEL_EXTN_NUM"))));
        mailBody.append(newLine);
        mailBody.append(String.format("Customer Attention Text         : %1$s", getStrFromMap((String) mailInfo.get("SVC_CUST_ATTN_TXT"))));
        mailBody.append(newLine);
        mailBody.append(String.format("Technician                      : %1$s", getStrFromMap((String) mailInfo.get("TECH_NM"))));
        mailBody.append(newLine);
        mailBody.append(String.format("Sevice Task#                    : %1$s", getStrFromMap((String) mailInfo.get("SVC_TASK_NUM"))));
        mailBody.append(newLine);
        mailBody.append(String.format("Service Task Completed Date     : %1$s", getStrFromDate((String) mailInfo.get("SVC_TASK_CPLT_DT"))));
        mailBody.append(newLine);
        mailBody.append(newLine);

        return mailBody;
    }

    private String getBigFromMap(BigDecimal inVal) {
        if (!hasValue(inVal)) {
            return "";
        }
        return inVal.toString();
    }

    private String getStrFromMap(String inVal) {
        if (!hasValue(inVal)) {
            return "";
        }
        return inVal;
    }

    private String getStrFromDate(String inVal) {
        if (!hasValue(inVal)) {
            return "";
        }
        return ZYPDateUtil.formatEzd8ToDisp(inVal);
    }

    private boolean sendMail(NSZC005001PMsg pMsg, S21MailTemplate tmpl, StringBuilder mailBody) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        // From Address
        S21MailAddress fromAddr = getFromMailAddress(glblCmpyCd);
        if (fromAddr == null) {
            setErrMsg(pMsg, NSZM0184E);
            return false;
        }

        // To Address
        S21MailAddress toEmlAddr = getToMailAddress(glblCmpyCd);
        if (toEmlAddr == null) {
            setErrMsg(pMsg, NSZM0436E);
            return false;
        }
        // Set MailBody
        tmpl.setTemplateParameter("message", mailBody.toString());

        S21Mail mail = new S21Mail(glblCmpyCd);

        // set From Address
        mail.setFromAddress(fromAddr);
        // set To Address
        mail.setToAddress(toEmlAddr);

        mail.setMailTemplate(tmpl);
        mail.postMail();
        return true;
    }

    private Map<String, Object> getMailInfo(NSZC005001PMsg pMsg, String svcTaskNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcTaskNum", svcTaskNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("geMailInfo", ssmParam);
    }

    private S21MailTemplate getMailTemplate(NSZC005001PMsg pMsg, String mailTmplId) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        return new S21MailTemplate(glblCmpyCd, mailTmplId);
    }

    private S21MailAddress getFromMailAddress(String glblCmpyCd) {
        S21MailGroup fromGrp = new S21MailGroup(glblCmpyCd, FROM_ADDR_GRP_CD);
        fromGrp.setMailKey1("NSZ");
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();
        if (fromAddrList == null || fromAddrList.isEmpty()) {
            return null;
        }
        return fromAddrList.get(0);
    }

    private S21MailAddress getToMailAddress(String glblCmpyCd) {
        S21MailGroup toGrp = new S21MailGroup(glblCmpyCd, SVC_DATA_MGT_DEPT);
        List<S21MailAddress> toAddrList = toGrp.getMailAddress();
        if (toAddrList == null || toAddrList.isEmpty()) {
            return null;
        }
        return toAddrList.get(0);
    }

    // END 2015/12/14 K.Ochiai [QC#15735, ADD]

    private boolean insertFsrUsg(NSZC005001PMsg pMsg) {
        //Mod Start 2018/11/28 K.Fujimoto QC#28647
        // if (fsrCompleteFlg) {
        if (!insertFsrUsgFsrComplete(pMsg)) {
            return false;
        }
        // } else {
        //     if (!insertFsrUsgNotFsrComplete(pMsg)) {
        //         return false;
        //     }
        // }
        //Mod End   2018/11/28 K.Fujimoto QC#28647
        // Visit Task Complete Check
        List<String> svcTaskNumList = getSvcTaskNumByStsCd(pMsg, null, SVC_TASK_STS_LIST_NOT_COMPLETE);
        if (svcTaskNumList == null || svcTaskNumList.size() == 0) {
            fsrCompleteFlg = true;
        } else {
            fsrCompleteFlg = false;
        }

        return true;
    }

    private boolean insertFsrUsgFsrComplete(NSZC005001PMsg pMsg) {
        if (this.completionBean.getFsrUsgListConf().size() < 1) {
            return true;
        }

        // Get target svcTaskNum
        List<NSZC005001_xxFsrUsgListPMsg> fsrUsgListConf = this.completionBean.getFsrUsgListConf();
        for (int i = 0; i < fsrUsgListConf.size(); i++) {
            String svcTaskNum = fsrUsgListConf.get(i).svcTaskNum.getValue();
            if (!this.completionBean.containsSvcTaskNumListForUsg(svcTaskNum)) {
                this.completionBean.addSvcTaskNumListForUsg(svcTaskNum);
            }
            // START 2023/04/12 K.Kitachi [QC#61311, ADD]
            BigDecimal fsrUsgPk = fsrUsgListConf.get(i).fsrUsgPk.getValue();
            if (hasValue(fsrUsgPk)) {
                if (!deleteFsrUsg(pMsg, fsrUsgPk)) {
                    return false;
                }
            }
            // END 2023/04/12 K.Kitachi [QC#61311, ADD]
        }

        // Delete FSR Usage and FSR Usage Survey
        // START 2023/04/12 K.Kitachi [QC#61311, DEL]
//        for (int i = 0; i < this.completionBean.getSvcTaskNumListForUsg().size(); i++) {
//            String svcTaskNum = this.completionBean.getSvcTaskNumListForUsg().get(i);
//            if (!deleteFsrUsg(pMsg, svcTaskNum)) {
//                return false;
//            }
//        }
        // END 2023/04/12 K.Kitachi [QC#61311, DEL]

        // Add Start 2019/01/10 K.Fujimoto QC#26861
        // Tech PI Check
        for (int i = 0; i < this.completionBean.getSvcTaskNumListForUsg().size(); i++) {
            String svcTaskNum = this.completionBean.getSvcTaskNumListForUsg().get(i);
            if (!checkDebriefError(pMsg, DEBRIEF_CHK_TECH_PI, null, svcTaskNum)) {
                return false;
            }
        }
        // Add End   2019/01/10 K.Fujimoto QC#26861
        // Insert FSR Usage
        NSZC061001PMsg svcPrcApiPMsg = this.completionBean.getSvcPrcApiPMsg();
        // add start 2016/04/20 CSA Defect#4469
        List<NSZC005001_xxPartSurveyListPMsg> fsrUsgSrvyListConf = this.completionBean.getFsrUsgSrvyListConf();
        // add end 2016/04/20 CSA Defect#4469

        for (int i = 0; i < this.completionBean.getFsrUsgListConf().size(); i++) {
            NSZC005001_xxFsrUsgListPMsg dtlPMsg = this.completionBean.getFsrUsgListConf().get(i);
            // mod start 2017/01/15 CSA Defect#17129 
            NSZC061001_xxPartsChrgListPMsg apiPartsDtlPMsg = getPartsChrgListPMsg(svcPrcApiPMsg, dtlPMsg.svcTaskNum.getValue(), dtlPMsg.mdseCd.getValue());
            NSZC061001_xxDrumChrgListPMsg apiDrumDtlPMsg = getDrumChrgListPMsg(svcPrcApiPMsg, dtlPMsg.svcTaskNum.getValue(), dtlPMsg.mdseCd.getValue());
            // mod end 2017/01/15 CSA Defect#17129
            // add start 2016/04/20 CSA Defect#4469
            NSZC005001_xxPartSurveyListPMsg partSurvey = getPartSurveyInfo(dtlPMsg, fsrUsgSrvyListConf);
            // add end 2016/04/20 CSA Defect#4469

            FSR_USGTMsg fsrUsgTMsg = new FSR_USGTMsg();
            // mod start 2017/01/15 CSA Defect#17129
            if (!setFsrUsgValue(pMsg, dtlPMsg, apiPartsDtlPMsg, apiDrumDtlPMsg, fsrUsgTMsg, partSurvey)) {
                return false;
            }
            // mod end 2017/01/15 CSA Defect#17129
            S21ApiTBLAccessor.create(fsrUsgTMsg);
            if (!checkRtnCodeForInsert(pMsg, fsrUsgTMsg)) {
                return false;
            }

            if (!checkDebriefError(pMsg, DEBRIEF_CHK_USG_CHRG, null, fsrUsgTMsg.svcTaskNum.getValue())) {
                return false;
            }
            // add start 2015/12/21 CSA Defect#1924
//            if (!hasValue(fsrUsgTMsg.svcPrtDealAmt)) {
//                fsrCompleteFlg = false;
//                if (!updateSvcTaskStsCd(pMsg, SVC_TASK_STS.DEBRIEF_ERROR)) {
//                    return false;
//                }
//            }
            // add end 2015/12/21 CSA Defect#1924

            // add start 2016/04/20 CSA Defect#4469
            // insert FSR Usage Sarvey
            if (!insertFsrUsgSrvy(pMsg, fsrUsgSrvyListConf, fsrUsgTMsg)) {
                return false;
            }
            // add end 2016/04/20 CSA Defect#4469
        }

        // Del Start 2019/01/10 K.Fujimoto QC#26861
        // add start 2017/01/26 CSA Defect#17114
        // Tech PI Check
//        for (int i = 0; i < this.completionBean.getSvcTaskNumListForUsg().size(); i++) {
//            String svcTaskNum = this.completionBean.getSvcTaskNumListForUsg().get(i);
//            if (!checkDebriefError(pMsg, DEBRIEF_CHK_TECH_PI, null, svcTaskNum)) {
//                
//                return false;
//            }
//        }
        // add end 2017/01/26 CSA Defect#17114
        // Del End   2019/01/10 K.Fujimoto QC#26861

        return true;
    }
    //Del Start 2018/11/28 K.Fujimoto QC#28647
//    private boolean insertFsrUsgNotFsrComplete(NSZC005001PMsg pMsg) {
//        if (pMsg.xxFsrUsgList.getValidCount() < 1) {
//            return true;
//        }
//
//        // Delete FSR Usage
//        if (!deleteFsrUsg(pMsg, pMsg.xxFsrUsgList.no(0).svcTaskNum.getValue())) {
//            return false;
//        }
//
//        // Insert FSR Usage
//        NSZC061001PMsg svcPrcApiPMsg = this.completionBean.getSvcPrcApiPMsg();
//        // add start 2016/04/20 CSA Defect#4469
//        // Mod Start 2017/08/31 QC#19354
//        List<NSZC005001_xxPartSurveyListPMsg> fsrUsgSrvyListConf = convPartSurveyArrayToList(pMsg.xxPartSurveyList);
//        // Mod End 2017/08/31 QC#19354
//        // add end 2016/04/20 CSA Defect#4469
//
//        for (int i = 0; i < pMsg.xxFsrUsgList.getValidCount(); i++) {
//            NSZC005001_xxFsrUsgListPMsg dtlPMsg = pMsg.xxFsrUsgList.no(i);
//            // mod start 2017/01/15 CSA Defect#17129
//            NSZC061001_xxPartsChrgListPMsg apiPartsDtlPMsg = getPartsChrgListPMsg(svcPrcApiPMsg, dtlPMsg.svcTaskNum.getValue(), dtlPMsg.mdseCd.getValue());
//            NSZC061001_xxDrumChrgListPMsg apiDrumDtlPMsg = getDrumChrgListPMsg(svcPrcApiPMsg, dtlPMsg.svcTaskNum.getValue(), dtlPMsg.mdseCd.getValue());
//            // mod end 2017/01/15 CSA Defect#17129
//            // add start 2016/04/20 CSA Defect#4469
//            NSZC005001_xxPartSurveyListPMsg partSurvey = getPartSurveyInfo(dtlPMsg, fsrUsgSrvyListConf);
//            // add end 2016/04/20 CSA Defect#4469
//
//            FSR_USGTMsg fsrUsgTMsg = new FSR_USGTMsg();
//            // mod start 2017/01/15 CSA Defect#17129
//            if (!setFsrUsgValue(pMsg, dtlPMsg, apiPartsDtlPMsg, apiDrumDtlPMsg, fsrUsgTMsg, partSurvey)) {
//                return false;
//            }
//            // mod end 2017/01/15 CSA Defect#17129
//            S21ApiTBLAccessor.create(fsrUsgTMsg);
//            if (!checkRtnCodeForInsert(pMsg, fsrUsgTMsg)) {
//                return false;
//            }
//            if (!checkDebriefError(pMsg, DEBRIEF_CHK_USG_CHRG)) {
//                return false;
//            }
////            // add start 2015/12/21 CSA Defect#1924
////            if (!hasValue(fsrUsgTMsg.svcPrtDealAmt)) {
////                fsrCompleteFlg = false;
////                if (!updateSvcTaskStsCd(pMsg, SVC_TASK_STS.DEBRIEF_ERROR)) {
////                    return false;
////                }
////            }
////            // add end 2015/12/21 CSA Defect#1924
//
//            // add start 2016/04/20 CSA Defect#4469
//            // insert FSR Usage Sarvey
//            if (!insertFsrUsgSrvy(pMsg, fsrUsgSrvyListConf, fsrUsgTMsg)) {
//                return false;
//            }
//            // add end 2016/04/20 CSA Defect#2016/04/20
//        }
//
//        // add start 2017/01/26 CSA Defect#17114
//        // Tech PI Check
//        if (!checkDebriefError(pMsg, DEBRIEF_CHK_TECH_PI, null, pMsg.xxFsrUsgList.no(0).svcTaskNum.getValue())) {
//            return false;
//        }
//        // add end 2017/01/26 CSA Defect#17114
//
//        return true;
//    }
     //Del End   2018/11/28 K.Fujimoto QC#28647

    // Add Start 2017/08/31 QC#19354
    private List<NSZC005001_xxPartSurveyListPMsg> convPartSurveyArrayToList(NSZC005001_xxPartSurveyListPMsgArray array) {
        List<NSZC005001_xxPartSurveyListPMsg> list = new ArrayList<NSZC005001_xxPartSurveyListPMsg>();
        for (int i = 0; i < array.getValidCount(); i++) {
            list.add(array.no(i));
        }
        return list;
    }
    // Add End 2017/08/31 QC#19354

    private boolean insertFsrVisitTask(NSZC005001PMsg pMsg) {
        if (pMsg.xxVisitTaskList.getValidCount() < 1) {
            return true;
        }

        if (!deleteFsrVisitTask(pMsg)) {
            return false;
        }

        NSZC061001PMsg svcPrcApiPMsg = this.completionBean.getSvcPrcApiPMsg();
        for (int i = 0; i < pMsg.xxVisitTaskList.getValidCount(); i++) {
            NSZC005001_xxVisitTaskListPMsg dtlPMsg = pMsg.xxVisitTaskList.no(i);
            NSZC061001_xxSvcTaskChrgListPMsg apiDtlPMsg = svcPrcApiPMsg.xxSvcTaskChrgList.no(i);

            FSR_VISIT_TASKTMsg fsrVisitTaskTMsg = new FSR_VISIT_TASKTMsg();
            setFsrVisitTaskValue(pMsg, dtlPMsg, apiDtlPMsg, fsrVisitTaskTMsg);
            S21ApiTBLAccessor.create(fsrVisitTaskTMsg);
            if (!checkRtnCodeForInsert(pMsg, fsrVisitTaskTMsg)) {
                return false;
            }

            if (!checkDebriefError(pMsg, DEBRIEF_CHK_LBOR_CHRG)) {
                return false;
            }
            // add start 2015/12/21 CSA Defect#1924
//            if (!hasValue(fsrVisitTaskTMsg.svcLborDealAmt)) {
//                fsrCompleteFlg = false;
//                if (!updateSvcTaskStsCd(pMsg, SVC_TASK_STS.DEBRIEF_ERROR)) {
//                    return false;
//                }
//            }
            // add end 2015/12/21 CSA Defect#1924
        }
        return true;
    }

    private boolean insertFsrVisitTmEvent(NSZC005001PMsg pMsg) {
        if (this.completionBean.getTmEventListLbor().size() == 0 && this.completionBean.getTmEventListTrvl().size() == 0 && this.completionBean.getTmEventListOther().size() == 0) {
            return true;
        }

        if (!deleteFsrVisitTmEvent(pMsg)) {
            return false;
        }

        NSZC061001PMsg svcPrcApiPMsg = this.completionBean.getSvcPrcApiPMsg();
        for (int i = 0; i < this.completionBean.getTmEventListLbor().size(); i++) {
            NSZC005001_xxTmEventListPMsg dtlPMsg = this.completionBean.getTmEventListLbor().get(i);

            FSR_VISIT_TM_EVENTTMsg fsrFsrVisitTmEventTMsg = new FSR_VISIT_TM_EVENTTMsg();
            if (!setFsrVisitTmEventValue(pMsg, dtlPMsg, svcPrcApiPMsg.xxSvcTaskChrgList.no(0).intgMdseCd_L.getValue(), fsrFsrVisitTmEventTMsg)) {
                return false;
            }
            S21ApiTBLAccessor.create(fsrFsrVisitTmEventTMsg);
            if (!checkRtnCodeForInsert(pMsg, fsrFsrVisitTmEventTMsg)) {
                return false;
            }
        }
        for (int i = 0; i < this.completionBean.getTmEventListTrvl().size(); i++) {
            NSZC005001_xxTmEventListPMsg dtlPMsg = this.completionBean.getTmEventListTrvl().get(i);

            FSR_VISIT_TM_EVENTTMsg fsrFsrVisitTmEventTMsg = new FSR_VISIT_TM_EVENTTMsg();
            if (!setFsrVisitTmEventValue(pMsg, dtlPMsg, svcPrcApiPMsg.xxSvcTaskChrgList.no(0).intgMdseCd_T.getValue(), fsrFsrVisitTmEventTMsg)) {
                return false;
            }
            S21ApiTBLAccessor.create(fsrFsrVisitTmEventTMsg);
            if (!checkRtnCodeForInsert(pMsg, fsrFsrVisitTmEventTMsg)) {
                return false;
            }
        }
        for (int i = 0; i < this.completionBean.getTmEventListOther().size(); i++) {
            NSZC005001_xxTmEventListPMsg dtlPMsg = this.completionBean.getTmEventListOther().get(i);

            FSR_VISIT_TM_EVENTTMsg fsrFsrVisitTmEventTMsg = new FSR_VISIT_TM_EVENTTMsg();
            // add start 2016/02/22 CSA Defect#2695
            String mdseCd = dtlPMsg.mdseCd.getValue();
            if (isModification(pMsg.glblCmpyCd.getValue(), dtlPMsg)) {
                if (!hasValue(dtlPMsg.mdseCd)) {
                    mdseCd = getModificationMdseCd(pMsg.glblCmpyCd.getValue());
                }
            }
            // add end 2016/02/22 CSA Defect#2695
            if (!setFsrVisitTmEventValue(pMsg, dtlPMsg, mdseCd, fsrFsrVisitTmEventTMsg)) {
                return false;
            }
            S21ApiTBLAccessor.create(fsrFsrVisitTmEventTMsg);
            if (!checkRtnCodeForInsert(pMsg, fsrFsrVisitTmEventTMsg)) {
                return false;
            }
        }
        return true;
    }

    private boolean insertSvcMemo(NSZC005001PMsg pMsg, String svcTaskNum, String message) {

        FSR_VISITTMsgArray fsrVisitTMsgArray = getFsrVisit(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), svcTaskNum);
        String fsrVisitNum = fsrVisitTMsgArray.no(0).fsrVisitNum.getValue();

        SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
        setValue(svcMemoTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(svcMemoTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(svcMemoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.DISPATCH_MEMO);
        setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.DEBRIEF_ERROR);
        setValue(svcMemoTMsg.svcCmntTxt, message);
        setValue(svcMemoTMsg.svcTaskNum, svcTaskNum);
        setValue(svcMemoTMsg.fsrNum, pMsg.fsrNum);
        // Add Start 2019/01/10 K.Fujimoto QC#30250
        setValue(svcMemoTMsg.lastUpdUsrId, pMsg.userId);
        setValue(svcMemoTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(FORMAT_TS));
        // End Start 2019/01/10 K.Fujimoto QC#30250
        setValue(svcMemoTMsg.svcMemoTrxNum, fsrVisitNum);
        setValue(svcMemoTMsg.svcMemoTrxNm, SVC_MEMO_TRX_NM_FSR_VISIT_NUM);
        S21ApiTBLAccessor.create(svcMemoTMsg);
        if (!checkRtnCodeForInsert(pMsg, svcMemoTMsg)) {
            return false;
        }
        return true;
    }

    // Add Start 2019/01/10 K.Fujimoto QC#26861
    private boolean insertSvcMemoForClosedSvcTaskUpdate(NSZC005001PMsg pMsg, String svcTaskNum, String message) {
        // Add Start 2019/02/13 K.Fujimoto QC#30310
        if (completionBean.getChangePartsMsg() != null) {
            StringBuffer sb = new StringBuffer();
            String newLine = System.getProperty("line.separator");
            sb.append(message);
            sb.append(newLine);
            sb.append(completionBean.getChangePartsMsg());
            message = sb.toString();
            if (message.length() > LEN_SVC_CMNT_TXT) {
                message = message.substring(STR_IDX_SVC_CMNT_TXT, LEN_SVC_CMNT_TXT);
            }
        }
        // End Start 2019/02/13 K.Fujimoto QC#30310
        SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
        setValue(svcMemoTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(svcMemoTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(svcMemoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.DISPATCH_MEMO);
        setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.GENERAL);
        setValue(svcMemoTMsg.svcCmntTxt, message);
        setValue(svcMemoTMsg.svcTaskNum, svcTaskNum);
        setValue(svcMemoTMsg.fsrNum, pMsg.fsrNum);
        // Add Start 2019/01/10 K.Fujimoto QC#30250
        setValue(svcMemoTMsg.lastUpdUsrId, pMsg.userId);
        setValue(svcMemoTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(FORMAT_TS));
        // End Start 2019/01/10 K.Fujimoto QC#30250
        S21ApiTBLAccessor.create(svcMemoTMsg);
        if (!checkRtnCodeForInsert(pMsg, svcMemoTMsg)) {
            return false;
        }
        return true;
    }
    // Add End   2019/01/10 K.Fujimoto QC#26861
    // add start 2015/12/16 CSA Defect#978
    private boolean insertFsrChrg(NSZC005001PMsg pMsg) {

        //get basic data for pricing
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String fsrNum = pMsg.fsrNum.getValue();

        FSRTMsg fsr = getFsrFindByKey(glblCmpyCd, fsrNum);
        BigDecimal actlExchRate = getExchRateData(glblCmpyCd, fsr.invCcyCd.getValue(), pMsg.slsDt.getValue());
        String tocCd = getTocCd(glblCmpyCd, fsr.techCd.getValue(), pMsg.slsDt.getValue());

        //get fsr data for pricing
        // START 2022/12/01 K.Kitachi [QC#60882, ADD]
        S21StopWatch sw = new S21StopWatch();
        S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0050 : getLborFsrVisitTask Start." + " FSR#" + fsrNum);
        sw.start();
        // END 2022/12/01 K.Kitachi [QC#60882, ADD]
        List<Map<String, Object>> lborList = getLborFsrVisitTask(pMsg, fsrNum);
        // START 2022/12/01 K.Kitachi [QC#60882, ADD]
        sw.stop();
        S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0050 : getLborFsrVisitTask End." + " FSR#" + fsrNum);
        S21InfoLogOutput.println(String.format("CHK_CPLT_TM : NSZC0050 : getLborFsrVisitTask Elapsed Time [%s]", sw.getElapsedMilliSec()));
        // END 2022/12/01 K.Kitachi [QC#60882, ADD]
        FSR_USGTMsgArray fsrUsgList = getFsrUsgListByFsrNum(glblCmpyCd, fsrNum);
        FSR_VISITTMsgArray fsrVisitList = getFsrVisitByFsrNum(glblCmpyCd, fsrNum);
        FSR_EXPTMsgArray fsrExpList = getFsrExpListByFsrNum(glblCmpyCd, fsrNum);

        if (!calculatePriceForFsrChrg(pMsg, fsr, lborList, fsrUsgList, fsrVisitList, fsrExpList)) {
            return false;
        }

        List<FSR_CHRG_DTLTMsg> lborChrgDtlList = insertLborFsrChrgDtl(pMsg, lborList, actlExchRate, tocCd);
        List<FSR_CHRG_DTLTMsg> trvlChrgDtlList = insertTrvlFsrChrgDtl(pMsg, lborList, fsrVisitList, actlExchRate, tocCd);
        List<FSR_CHRG_DTLTMsg> prtChrgDtlList = insertUsgFsrChrgDtl(pMsg, fsrUsgList, actlExchRate, tocCd);
        List<FSR_CHRG_DTLTMsg> expChrgDtlList = insertExpFsrChrgDtl(pMsg, fsrExpList, actlExchRate, tocCd);

        if (!insertFsrChrg(pMsg, fsr, lborChrgDtlList, trvlChrgDtlList, prtChrgDtlList, expChrgDtlList)) {
            return false;
        }
        return true;
    }

    private boolean insertFsrChrg(NSZC005001PMsg pMsg, FSRTMsg fsr
            , List<FSR_CHRG_DTLTMsg> lborChrgDtlList
            , List<FSR_CHRG_DTLTMsg> trvlChrgDtlList
            , List<FSR_CHRG_DTLTMsg> prtChrgDtlList
            , List<FSR_CHRG_DTLTMsg> expChrgDtlList) {

        FSR_CHRGTMsg fsrChrg = new FSR_CHRGTMsg();
        setValue(fsrChrg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(fsrChrg.fsrNum, pMsg.fsrNum);
        setValue(fsrChrg.fsrChrgSq, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_CHRG_SQ));
        // mod start 2016/02/17 CSA Defect#4134
        if (ZYPConstant.FLG_ON_Y.equals(fsr.billToCustUpdFlg.getValue())) {
            setValue(fsrChrg.billToCustCd, fsr.billToUpdCustCd);
        } else {
            setValue(fsrChrg.billToCustCd, fsr.billToCustCd);
        }
        setValue(fsrChrg.sellToCustCd, fsr.sellToCustCd);
        if (ZYPConstant.FLG_ON_Y.equals(fsr.shipToCustUpdFlg.getValue())) {
            setValue(fsrChrg.shipToCustCd, fsr.shipToUpdCustCd);
        } else {
            setValue(fsrChrg.shipToCustCd, fsr.shipToCustCd);
        }
        // mod end 2016/02/17 CSA Defect#4134
        setValue(fsrChrg.pmtTermCashDiscCd, fsr.pmtTermCashDiscCd);

        summaryFsrChrgDtl(lborChrgDtlList, fsrChrg.svcLborDealAmt, fsrChrg.svcLborFuncAmt, fsrChrg.svcLborDealDiscAmt, fsrChrg.svcLborFuncDiscAmt);
        summaryFsrChrgDtl(trvlChrgDtlList, fsrChrg.svcTrvlDealAmt, fsrChrg.svcTrvlFuncAmt, fsrChrg.svcTrvlDealDiscAmt, fsrChrg.svcTrvlFuncDiscAmt);
        summaryFsrChrgDtl(prtChrgDtlList, fsrChrg.svcPrtDealAmt, fsrChrg.svcPrtFuncAmt, fsrChrg.svcPrtDealDiscAmt, fsrChrg.svcPrtFuncDiscAmt);
        summaryFsrChrgDtl(expChrgDtlList, fsrChrg.svcExpDealAmt, fsrChrg.svcExpFuncAmt, fsrChrg.svcExpDealDiscAmt, fsrChrg.svcExpFuncDiscAmt);

        setValue(fsrChrg.origInvCcyCd, fsr.origInvCcyCd);
        setValue(fsrChrg.invCcyCd, fsr.invCcyCd);
        setValue(fsrChrg.updPsnCd, pMsg.userId);
        setValue(fsrChrg.dsContrPk, this.completionBean.getSvcPrcApiPMsg().dsContrPk);

        S21ApiTBLAccessor.create(fsrChrg);
        if (!checkRtnCodeForInsert(pMsg, fsrChrg)) {
            return false;
        }
        return true;
    }

    private void summaryFsrChrgDtl(List<FSR_CHRG_DTLTMsg> fsrChrgDtlList, EZDTBigDecimalItem dealAmt, EZDTBigDecimalItem funcAmt, EZDTBigDecimalItem dealDiscAmt, EZDTBigDecimalItem funcDiscAmt) {
        for (FSR_CHRG_DTLTMsg fsrChrgDtl : fsrChrgDtlList) {
            // mod start 2016/04/08 CSA Defect#6692
            setValue(dealAmt, addAmt(dealAmt, fsrChrgDtl.svcChrgDealAmt));
            setValue(funcAmt, addAmt(funcAmt, fsrChrgDtl.svcChrgFuncAmt));
            setValue(dealDiscAmt, addAmt(dealDiscAmt, fsrChrgDtl.svcChrgDealDiscAmt));
            setValue(funcDiscAmt, addAmt(funcDiscAmt, fsrChrgDtl.svcChrgFuncDiscAmt));
            // mod end 2016/04/08 CSA Defect#6692
        }
    }

    private BigDecimal addAmt(EZDTBigDecimalItem tot, EZDTBigDecimalItem add) {
        BigDecimal rtnVal = null;
        if (!hasValue(tot) && !hasValue(add)) {
            return rtnVal;
        }

        rtnVal = BigDecimal.ZERO;
        if (hasValue(tot)) {
            rtnVal = rtnVal.add(tot.getValue());
        }

        if (hasValue(add)) {
            rtnVal = rtnVal.add(add.getValue());
        }
        return rtnVal;
    }

    private List<FSR_CHRG_DTLTMsg> insertLborFsrChrgDtl(NSZC005001PMsg pMsg, List<Map<String, Object>> lborList, BigDecimal actlExchRate, String tocCd) {

        NSZC061001PMsg svcPrcApiPMsg = this.completionBean.getSvcPrcApiPMsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String svcLborTrvlUomCd = ZYPCodeDataUtil.getVarCharConstValue(SVC_LBOR_TRVL_UOM_CD, glblCmpyCd);

        List<FSR_CHRG_DTLTMsg> insertList = new ArrayList<FSR_CHRG_DTLTMsg>();
        for (Map<String, Object> lborInfo : lborList) {
            String svcTaskNum = (String) lborInfo.get("SVC_TASK_NUM");

            // mod start 2016/11/30 CSA Defect#15782
            // add start 2016/09/14 CSA Defect#13860
            List<BigDecimal> lborEventList = getLborTmEvent(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), (String) lborInfo.get("FSR_VISIT_NUM"));
            if (lborEventList == null || lborEventList.isEmpty()) {
                continue;
            }
            // add end 2016/09/14 CSA Defect#13860
            // mod end 2016/11/30 CSA Defect#15782

            FSR_CHRG_DTLTMsg fsrChrgDtl = new FSR_CHRG_DTLTMsg();
            setValue(fsrChrgDtl.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(fsrChrgDtl.fsrChrgDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_CHRG_DTL_SQ));
            setValue(fsrChrgDtl.fsrNum, pMsg.fsrNum);
            setValue(fsrChrgDtl.fsrVisitNum, (String) lborInfo.get("FSR_VISIT_NUM"));
            setValue(fsrChrgDtl.svcTaskNum, svcTaskNum);
            // START 2018/06/07 M.Naito [QC#22434,MOD]
//            setValue(fsrChrgDtl.svcChrgTrxTpCd, SVC_CHRG_TRX_TP.COVERAGE_BASED_ON_CONTRACT);
            String svcChrgTrxTpCd = getSvcChrgTrxTpCd(pMsg, svcTaskNum, SVC_INV_CHRG_TP.LABOR_CHARGE);
            setValue(fsrChrgDtl.svcChrgTrxTpCd, svcChrgTrxTpCd);
            // END 2018/06/07 M.Naito [QC#22434,MOD]

            NSZC061001_xxSvcTaskChrgListPMsg chrgPMsg = null;
            for (int i = 0; i < svcPrcApiPMsg.xxSvcTaskChrgList.getValidCount(); i++) {
                NSZC061001_xxSvcTaskChrgListPMsg apiPMsg = svcPrcApiPMsg.xxSvcTaskChrgList.no(i);
                if (svcTaskNum.equals(apiPMsg.svcTaskNum.getValue())) {
                    chrgPMsg = apiPMsg;
                }
            }
            // mod start 2016/02/08 CSA Defect#2863
            if (svcPrcApiPMsg.xxSvcTaskChrgList.getValidCount() > 0) {
                setValue(fsrChrgDtl.mdseCd, chrgPMsg.intgMdseCd_L);
                // mod start 2016/05/24 CSA Defect#8636
                // setValue(fsrChrgDtl.svcChrgQty, BigDecimal.ONE);
                setValue(fsrChrgDtl.svcChrgQty, (BigDecimal) lborInfo.get("SVC_LBOR_TM_NUM"));
                // mod end 2016/05/24 CSA Defect#8636
                setValue(fsrChrgDtl.uomCd, svcLborTrvlUomCd);
                setValue(fsrChrgDtl.svcChrgUnitAot, chrgPMsg.svcLborUnitHrsAot);
                setValue(fsrChrgDtl.svcChrgUnitAmt, chrgPMsg.svcLborUnitAmt);
                setValue(fsrChrgDtl.svcChrgDealAmt, chrgPMsg.svcLborDealAmt);
                BigDecimal svcLborFuncAmt = calcFuncAmt(chrgPMsg.svcLborDealAmt.getValue(), actlExchRate);
                setValue(fsrChrgDtl.svcChrgFuncAmt, svcLborFuncAmt);
                setValue(fsrChrgDtl.svcChrgDiscRate, chrgPMsg.svcLborDiscRate);
                // mod start 2016/05/20 CSA Defect#8480
                // BigDecimal svcLborDiscAmt = calcDiscAmt(chrgPMsg.svcLborDealAmt.getValue(), chrgPMsg.svcLborDiscRate.getValue());
                BigDecimal svcLborDiscAmt = chrgPMsg.svcLborDealDiscAmt.getValue();
                // mod end 2016/05/20 CSA Defect#8480
                setValue(fsrChrgDtl.svcChrgDealDiscAmt, svcLborDiscAmt);
                BigDecimal svcLborFuncDiscAmt = calcFuncAmt(svcLborDiscAmt, actlExchRate);
                setValue(fsrChrgDtl.svcChrgFuncDiscAmt, svcLborFuncDiscAmt);
            }
            // mod end 2016/02/08 CSA Defect#2863
            setValue(fsrChrgDtl.svcChrgFlg, (String) lborInfo.get("SVC_LBOR_CHRG_FLG"));
            String lborCatgCd = ZYPCodeDataUtil.getVarCharConstValue(SVC_LBOR_PRC_CATG_NM, glblCmpyCd);
            setValue(fsrChrgDtl.prcCatgCd, lborCatgCd);
            setValue(fsrChrgDtl.svcInvChrgTpCd, SVC_INV_CHRG_TP.LABOR_CHARGE);
            setValue(fsrChrgDtl.slsRepTocCd, tocCd);

            S21ApiTBLAccessor.create(fsrChrgDtl);
            if (!checkRtnCodeForInsert(pMsg, fsrChrgDtl)) {
                return null;
            }
            insertList.add(fsrChrgDtl);
        }
        return insertList;
    }

    private List<FSR_CHRG_DTLTMsg> insertTrvlFsrChrgDtl(NSZC005001PMsg pMsg, List<Map<String, Object>> lborList, FSR_VISITTMsgArray fsrVisitList,
            BigDecimal actlExchRate, String tocCd) {

        NSZC061001PMsg svcPrcApiPMsg = this.completionBean.getSvcPrcApiPMsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String svcLborTrvlUomCd = ZYPCodeDataUtil.getVarCharConstValue(SVC_LBOR_TRVL_UOM_CD, glblCmpyCd);

        List<FSR_CHRG_DTLTMsg> insertList = new ArrayList<FSR_CHRG_DTLTMsg>();
        for (int i = 0; i < fsrVisitList.getValidCount(); i++) {
            Map<String, Object> lborInfo = lborList.get(i);
            FSR_VISITTMsg fsrVisit = fsrVisitList.no(i);
            String svcTaskNum = fsrVisit.svcTaskNum.getValue();

            // mod start 2016/11/30 CSA Defect#15782
            // add start 2016/09/14 CSA Defect#13860
            List<BigDecimal> trvlEventList = getTrvlTmEvent(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), fsrVisit.fsrVisitNum.getValue());
            if (trvlEventList == null || trvlEventList.isEmpty()) {
                continue;
            }
            // add end 2016/09/14 CSA Defect#13860
            // mod end 2016/11/30 CSA Defect#15782

            FSR_CHRG_DTLTMsg fsrChrgDtl = new FSR_CHRG_DTLTMsg();
            setValue(fsrChrgDtl.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(fsrChrgDtl.fsrChrgDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_CHRG_DTL_SQ));
            setValue(fsrChrgDtl.fsrNum, pMsg.fsrNum);
            setValue(fsrChrgDtl.fsrVisitNum, fsrVisit.fsrVisitNum);
            setValue(fsrChrgDtl.svcTaskNum, svcTaskNum);
            // START 2018/06/07 M.Naito [QC#22434,MOD]
//            setValue(fsrChrgDtl.svcChrgTrxTpCd, SVC_CHRG_TRX_TP.COVERAGE_BASED_ON_CONTRACT);
          String svcChrgTrxTpCd = getSvcChrgTrxTpCd(pMsg, svcTaskNum, SVC_INV_CHRG_TP.TRAVEL_CHARGE);
          setValue(fsrChrgDtl.svcChrgTrxTpCd, svcChrgTrxTpCd);
          // END 2018/06/07 M.Naito [QC#22434,MOD]

            NSZC061001_xxSvcTaskChrgListPMsg chrgPMsg = null;
            for (int j = 0; j < svcPrcApiPMsg.xxSvcTaskChrgList.getValidCount(); j++) {
                NSZC061001_xxSvcTaskChrgListPMsg apiPMsg = svcPrcApiPMsg.xxSvcTaskChrgList.no(j);
                if (svcTaskNum.equals(apiPMsg.svcTaskNum.getValue())) {
                    chrgPMsg = apiPMsg;
                }
            }
            // mod start 2016/02/08 CSA Defect#2863
            if (svcPrcApiPMsg.xxSvcTaskChrgList.getValidCount() > 0) {
                setValue(fsrChrgDtl.mdseCd, chrgPMsg.intgMdseCd_T);
                // mod start 2016/05/24 CSA Defect#8636
                // setValue(fsrChrgDtl.svcChrgQty, BigDecimal.ONE);
                setValue(fsrChrgDtl.svcChrgQty, fsrVisit.svcTrvlTmNum);
                // mod start 2016/05/24 CSA Defect#8636
                setValue(fsrChrgDtl.uomCd, svcLborTrvlUomCd);
                setValue(fsrChrgDtl.svcChrgUnitAot, chrgPMsg.svcTrvlUnitHrsAot);
                setValue(fsrChrgDtl.svcChrgUnitAmt, chrgPMsg.svcTrvlUnitAmt);
                setValue(fsrChrgDtl.svcChrgDealAmt, chrgPMsg.svcTrvlDealAmt);
                BigDecimal svcTrvlFuncAmt = calcFuncAmt(chrgPMsg.svcTrvlDealAmt.getValue(), actlExchRate);
                setValue(fsrChrgDtl.svcChrgFuncAmt, svcTrvlFuncAmt);
                setValue(fsrChrgDtl.svcChrgDiscRate, chrgPMsg.svcTrvlDiscRate);
                BigDecimal svcTrvlDiscAmt = calcDiscAmt(chrgPMsg.svcTrvlDealAmt.getValue(), chrgPMsg.svcTrvlDiscRate.getValue());
                setValue(fsrChrgDtl.svcChrgDealDiscAmt, svcTrvlDiscAmt);
                BigDecimal svcTrvlFuncDiscAmt = calcFuncAmt(svcTrvlDiscAmt, actlExchRate);
                setValue(fsrChrgDtl.svcChrgFuncDiscAmt, svcTrvlFuncDiscAmt);
            }
            // mod end 2016/02/08 CSA Defect#2863
            // START 2018/12/12 [QC#28633, MOD]
            // setValue(fsrChrgDtl.svcChrgFlg, (String) lborInfo.get("SVC_LBOR_CHRG_FLG"));
            setValue(fsrChrgDtl.svcChrgFlg, (String) lborInfo.get("TRVL_CHRG_FLG"));
            // END 2018/12/12 [QC#28633, MOD]
            String trvlCatgCd = ZYPCodeDataUtil.getVarCharConstValue(SVC_TRVL_PRC_CATG_NM, glblCmpyCd);
            setValue(fsrChrgDtl.prcCatgCd, trvlCatgCd);
            setValue(fsrChrgDtl.svcInvChrgTpCd, SVC_INV_CHRG_TP.TRAVEL_CHARGE);
            setValue(fsrChrgDtl.slsRepTocCd, tocCd);

            S21ApiTBLAccessor.create(fsrChrgDtl);
            if (!checkRtnCodeForInsert(pMsg, fsrChrgDtl)) {
                return null;
            }
            insertList.add(fsrChrgDtl);
        }
        return insertList;
    }

    private List<FSR_CHRG_DTLTMsg> insertUsgFsrChrgDtl(NSZC005001PMsg pMsg, FSR_USGTMsgArray fsrUsgList,
            BigDecimal actlExchRate, String tocCd) {

        NSZC061001PMsg svcPrcApiPMsg = this.completionBean.getSvcPrcApiPMsg();

        List<FSR_CHRG_DTLTMsg> insertList = new ArrayList<FSR_CHRG_DTLTMsg>();
        for (int i = 0; i < fsrUsgList.getValidCount(); i++) {
            FSR_USGTMsg fsrUsg = fsrUsgList.no(i);
            String svcTaskNum = fsrUsg.svcTaskNum.getValue();

            FSR_CHRG_DTLTMsg fsrChrgDtl = new FSR_CHRG_DTLTMsg();
            setValue(fsrChrgDtl.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(fsrChrgDtl.fsrChrgDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_CHRG_DTL_SQ));
            setValue(fsrChrgDtl.fsrNum, pMsg.fsrNum);
            setValue(fsrChrgDtl.fsrVisitNum, fsrUsg.fsrVisitNum);
            setValue(fsrChrgDtl.svcTaskNum, svcTaskNum);
            // START 2018/06/07 M.Naito [QC#22434,MOD]
//            setValue(fsrChrgDtl.svcChrgTrxTpCd, SVC_CHRG_TRX_TP.COVERAGE_BASED_ON_CONTRACT);
            String svcChrgTrxTpCd = getSvcChrgTrxTpCd(pMsg, svcTaskNum, SVC_INV_CHRG_TP.PARTS_CHARGE);
            setValue(fsrChrgDtl.svcChrgTrxTpCd, svcChrgTrxTpCd);
            // END 2018/06/07 M.Naito [QC#22434,MOD]

            // mod start 2017/01/15 CSA Defect#17129
            NSZC061001_xxPartsChrgListPMsg usgPartsPMsg = getPartsChrgListPMsg(svcPrcApiPMsg, svcTaskNum, fsrUsg.mdseCd.getValue());
            NSZC061001_xxDrumChrgListPMsg usgDrumPMsg = getDrumChrgListPMsg(svcPrcApiPMsg, svcTaskNum, fsrUsg.mdseCd.getValue());
//            for (int j = 0; j < svcPrcApiPMsg.xxPartsChrgList.getValidCount(); j++) {
//                NSZC061001_xxPartsChrgListPMsg apiPMsg = svcPrcApiPMsg.xxPartsChrgList.no(j);
//                if (svcTaskNum.equals(apiPMsg.svcTaskNum.getValue()) && fsrUsg.mdseCd.getValue().equals(apiPMsg.mdseCd.getValue())) {
//                    usgPartsPMsg = apiPMsg;
//                }
//            }
            // mod start 2016/02/08 CSA Defect#2863
            // Parts
            if (usgPartsPMsg != null && svcPrcApiPMsg.xxSvcTaskChrgList.getValidCount() > 0) {
                setValue(fsrChrgDtl.mdseCd, usgPartsPMsg.mdseCd);
                setValue(fsrChrgDtl.svcChrgQty, fsrUsg.svcPrtQty);
                setValue(fsrChrgDtl.uomCd, fsrUsg.uomCd);
                setValue(fsrChrgDtl.svcChrgUnitAmt, usgPartsPMsg.svcPrtUnitAmt);
                setValue(fsrChrgDtl.svcChrgDealAmt, usgPartsPMsg.svcPrtDealAmt);
                BigDecimal svcPrtFuncAmt = calcFuncAmt(usgPartsPMsg.svcPrtDealAmt.getValue(), actlExchRate);
                setValue(fsrChrgDtl.svcChrgFuncAmt, svcPrtFuncAmt);
                setValue(fsrChrgDtl.svcChrgDiscRate, usgPartsPMsg.svcPrtDiscRate);
                BigDecimal svcPrtDiscAmt = calcDiscAmt(usgPartsPMsg.svcPrtDealAmt.getValue(), usgPartsPMsg.svcPrtDiscRate.getValue());
                setValue(fsrChrgDtl.svcChrgDealDiscAmt, svcPrtDiscAmt);
                BigDecimal svcPrtFuncDiscAmt = calcFuncAmt(svcPrtDiscAmt, actlExchRate);
                setValue(fsrChrgDtl.svcChrgFuncDiscAmt, svcPrtFuncDiscAmt);
                setValue(fsrChrgDtl.prcCatgCd, usgPartsPMsg.prcCatgCd_L1);
            }
            // mod end 2016/02/08 CSA Defect#2863
            // Drum
            if (usgDrumPMsg != null && svcPrcApiPMsg.xxSvcTaskChrgList.getValidCount() > 0) {
                setValue(fsrChrgDtl.mdseCd, usgDrumPMsg.mdseCd);
                setValue(fsrChrgDtl.svcChrgQty, fsrUsg.svcPrtQty);
                setValue(fsrChrgDtl.uomCd, fsrUsg.uomCd);
                setValue(fsrChrgDtl.svcChrgUnitAmt, usgDrumPMsg.xxSvcDrumUnitAmt);
                setValue(fsrChrgDtl.svcChrgDealAmt, usgDrumPMsg.xxSvcDrumDealAmt);
                BigDecimal svcPrtFuncAmt = calcFuncAmt(usgDrumPMsg.xxSvcDrumDealAmt.getValue(), actlExchRate);
                setValue(fsrChrgDtl.svcChrgFuncAmt, svcPrtFuncAmt);
                setValue(fsrChrgDtl.svcChrgDiscRate, usgDrumPMsg.xxSvcDrumDiscRate);
                BigDecimal svcPrtDiscAmt = calcDiscAmt(usgDrumPMsg.xxSvcDrumDealAmt.getValue(), usgDrumPMsg.xxSvcDrumDiscRate.getValue());
                setValue(fsrChrgDtl.svcChrgDealDiscAmt, svcPrtDiscAmt);
                BigDecimal svcPrtFuncDiscAmt = calcFuncAmt(svcPrtDiscAmt, actlExchRate);
                setValue(fsrChrgDtl.svcChrgFuncDiscAmt, svcPrtFuncDiscAmt);
                setValue(fsrChrgDtl.prcCatgCd, usgDrumPMsg.prcCatgCd_L3);
            }
            // mod end 2017/01/15 CSA Defect#17129
            setValue(fsrChrgDtl.svcChrgFlg, fsrUsg.svcPrtChrgFlg);
            setValue(fsrChrgDtl.svcInvChrgTpCd, SVC_INV_CHRG_TP.PARTS_CHARGE);
            setValue(fsrChrgDtl.slsRepTocCd, tocCd);

            S21ApiTBLAccessor.create(fsrChrgDtl);
            if (!checkRtnCodeForInsert(pMsg, fsrChrgDtl)) {
                return null;
            }
            insertList.add(fsrChrgDtl);
        }
        return insertList;
    }

    private List<FSR_CHRG_DTLTMsg> insertExpFsrChrgDtl(NSZC005001PMsg pMsg, FSR_EXPTMsgArray fsrExpList,
            BigDecimal actlExchRate, String tocCd) {

        NSZC061001PMsg svcPrcApiPMsg = this.completionBean.getSvcPrcApiPMsg();

        List<FSR_CHRG_DTLTMsg> insertList = new ArrayList<FSR_CHRG_DTLTMsg>();
        for (int i = 0; i < fsrExpList.getValidCount(); i++) {
            FSR_EXPTMsg fsrExp = fsrExpList.no(i);
            String svcTaskNum = fsrExp.svcTaskNum.getValue();

            FSR_CHRG_DTLTMsg fsrChrgDtl = new FSR_CHRG_DTLTMsg();
            setValue(fsrChrgDtl.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(fsrChrgDtl.fsrChrgDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_CHRG_DTL_SQ));
            setValue(fsrChrgDtl.fsrNum, pMsg.fsrNum);
            setValue(fsrChrgDtl.fsrVisitNum, fsrExp.fsrVisitNum);
            setValue(fsrChrgDtl.svcTaskNum, svcTaskNum);
            // START 2018/06/07 M.Naito [QC#22434,MOD]
//            setValue(fsrChrgDtl.svcChrgTrxTpCd, SVC_CHRG_TRX_TP.COVERAGE_BASED_ON_CONTRACT);
            String svcChrgTrxTpCd = getSvcChrgTrxTpCd(pMsg, svcTaskNum, SVC_INV_CHRG_TP.EXPENSE_CHARGE);
            setValue(fsrChrgDtl.svcChrgTrxTpCd, svcChrgTrxTpCd);
            // END 2018/06/07 M.Naito [QC#22434,MOD]

// del start 2016/02/08 CSA Defect#4644
//            NSZC061001_xxExpMdseListPMsg expPMsg = null;
//            for (int j = 0; j < svcPrcApiPMsg.xxExpMdseList.getValidCount(); j++) {
//                NSZC061001_xxExpMdseListPMsg apiPMsg = svcPrcApiPMsg.xxExpMdseList.no(j);
//                if (svcTaskNum.equals(apiPMsg.svcTaskNum.getValue())) {
//                    expPMsg = apiPMsg;
//                }
//            }
// del start 2016/02/08 CSA Defect#4644
            // mod start 2016/02/08,2016/03/02 CSA Defect#2863,4644
            if (svcPrcApiPMsg.xxSvcTaskChrgList.getValidCount() > 0) {
                setValue(fsrChrgDtl.mdseCd, fsrExp.mdseCd);
                setValue(fsrChrgDtl.svcChrgQty, fsrExp.svcExpQty);
                setValue(fsrChrgDtl.uomCd, fsrExp.uomCd);
                setValue(fsrChrgDtl.svcChrgUnitAmt, fsrExp.svcExpUnitAmt);
                setValue(fsrChrgDtl.svcChrgDealAmt, fsrExp.svcExpDealAmt);
                BigDecimal svcExpFuncAmt = calcFuncAmt(fsrExp.svcExpDealAmt.getValue(), actlExchRate);
                setValue(fsrChrgDtl.svcChrgFuncAmt, svcExpFuncAmt);
                setValue(fsrChrgDtl.svcChrgDiscRate, fsrExp.svcExpDiscRate);
                BigDecimal svcExpDiscAmt = calcDiscAmt(fsrExp.svcExpDealAmt.getValue(), fsrExp.svcExpDiscRate.getValue());
                setValue(fsrChrgDtl.svcChrgDealDiscAmt, svcExpDiscAmt);
                BigDecimal svcExpFuncDiscAmt = calcFuncAmt(svcExpDiscAmt, actlExchRate);
                setValue(fsrChrgDtl.svcChrgFuncDiscAmt, svcExpFuncDiscAmt);
                setValue(fsrChrgDtl.prcCatgCd, fsrExp.prcCatgCd);
            }
            // mod end 2016/02/08,2016/03/02 CSA Defect#2863,4644
            setValue(fsrChrgDtl.svcChrgFlg, fsrExp.svcExpChrgFlg);
            setValue(fsrChrgDtl.svcInvChrgTpCd, SVC_INV_CHRG_TP.EXPENSE_CHARGE);
            setValue(fsrChrgDtl.slsRepTocCd, tocCd);

            S21ApiTBLAccessor.create(fsrChrgDtl);
            if (!checkRtnCodeForInsert(pMsg, fsrChrgDtl)) {
                return null;
            }
            insertList.add(fsrChrgDtl);
        }
        return insertList;
    }
    // add end 2015/12/16 CSA Defect#978

    private boolean updateFsr(NSZC005001PMsg pMsg) {
        FSRTMsg fsrTMsg = getFsrFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());

        if (!checkRtnCodeForSearch(pMsg, fsrTMsg, new FSRTMsg())) {
            return false;
        }

        if (!setFsrValue(pMsg, fsrTMsg)) {
            return false;
        }
        S21ApiTBLAccessor.update(fsrTMsg);
        if (!checkRtnCodeForUpdate(pMsg, fsrTMsg)) {
            return false;
        }

        // Mod Start 2017/08/02 QC#20082
        // mod start 2016/06/28 CSA Defect#10790
        // Update Service Machine Master
        // START 2017/06/16 K.Kitachi [QC#19081, MOD]
//        if (SVC_TASK_STS.PENDING_CHARGE.equals(this.resultFsrStsCd)) {
//        if (SVC_TASK_STS.CLOSED.equals(this.resultFsrStsCd) || SVC_TASK_STS.COMPLETED.equals(this.resultFsrStsCd) || SVC_TASK_STS.PENDING_CHARGE.equals(this.resultFsrStsCd)) {
        if (isCallMachineMasterUpdateAPI(pMsg)) {
        // END 2017/06/16 K.Kitachi [QC#19081, MOD]
        // Mod End 2017/08/02 QC#20082
            if (!updateSvcMachMstr(pMsg)) {
                return false;
            }
        }
        // mod end 2016/06/28 CSA Defect#10790
        // Mod End 2017/08/02 QC#20082

        return true;
    }

    private boolean updateFsrVisit(NSZC005001PMsg pMsg) {
        if (fsrCompleteFlg && this.completionBean.getSvcTaskNumListForUsg().size() > 0) {
            for (int i = 0; i < this.completionBean.getSvcTaskNumListForUsg().size(); i++) {
                String svcTaskNum = this.completionBean.getSvcTaskNumListForUsg().get(i);
                FSR_VISITTMsgArray fsrVisitTMsgList = getFsrVisit(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), svcTaskNum);
                if (fsrVisitTMsgList == null || fsrVisitTMsgList.getValidCount() == 0) {
                    setErrMsg(pMsg, NSZM0182E);
                    return false;
                }
                FSR_VISITTMsg fsrVisitTMsg = getFsrVisitFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), fsrVisitTMsgList.no(0).fsrVisitNum.getValue());
                if (!checkRtnCodeForSearch(pMsg, fsrVisitTMsg, new FSR_VISITTMsg())) {
                    return false;
                }
                if (!setFsrVisitValue(pMsg, svcTaskNum, fsrVisitTMsg)) {
                    return false;
                }
                S21ApiTBLAccessor.update(fsrVisitTMsg);
                if (!checkRtnCodeForUpdate(pMsg, fsrVisitTMsg)) {
                    return false;
                }
                //check current svcTask
                if (!checkDebriefError(pMsg, DEBRIEF_CHK_TRVL_CHRG, fsrVisitTMsg.fsrVisitNum.getValue(), null)) {
                    return false;
                }
//                // add start 2015/12/21 CSA Defect#1924
//                if (!hasValue(fsrVisitTMsg.svcTrvlDealAmt)) {
//                    fsrCompleteFlg = false;
//                    if (!updateSvcTaskStsCd(pMsg, SVC_TASK_STS.DEBRIEF_ERROR)) {
//                        return false;
//                    }
//                }
                // add end 2015/12/21 CSA Defect#1924
            }
        }
        for (int i = 0; i < pMsg.xxVisitTaskList.getValidCount(); i++) {
            String svcTaskNum = pMsg.xxVisitTaskList.no(i).svcTaskNum.getValue();
//            if (this.completionBean.containsSvcTaskNumListForUsg(svcTaskNum)) {
                if (fsrCompleteFlg && this.completionBean.containsSvcTaskNumListForUsg(svcTaskNum)) {
                continue;
            }
            // mod start 2016/02/08 CSA Defect#2863
            String fsrVisitNum = pMsg.fsrVisitNum.getValue();
            if (frceUpdFlg) {
                fsrVisitNum = this.completionBean.getFsrVisitTMsg().fsrVisitNum.getValue();
            }
            FSR_VISITTMsg fsrVisitTMsg = getFsrVisitFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), fsrVisitNum);
            // mod end 2016/02/08 CSA Defect#2863
            if (!checkRtnCodeForSearch(pMsg, fsrVisitTMsg, new FSR_VISITTMsg())) {
                return false;
            }
            if (!setFsrVisitValue(pMsg, svcTaskNum, fsrVisitTMsg)) {
                return false;
            }
            S21ApiTBLAccessor.update(fsrVisitTMsg);
            if (!checkRtnCodeForUpdate(pMsg, fsrVisitTMsg)) {
                return false;
            }
            //check current svcTask
            if (!checkDebriefError(pMsg, DEBRIEF_CHK_TRVL_CHRG)) {
                return false;
            }
            //            // add start 2015/12/21 CSA Defect#1924
            //            if (!hasValue(fsrVisitTMsg.svcTrvlDealAmt)) {
            //                fsrCompleteFlg = false;
            //                if (!updateSvcTaskStsCd(pMsg, SVC_TASK_STS.DEBRIEF_ERROR)) {
            //                    return false;
            //                }
            //            }
            //            // add end 2015/12/21 CSA Defect#1924
        }
        return true;
    }
    private boolean updateFsrVisitForIstlChk(NSZC005001PMsg pMsg) {
        if (fsrCompleteFlg && this.completionBean.getSvcTaskNumListForUsg().size() > 0) {
            for (int i = 0; i < this.completionBean.getSvcTaskNumListForUsg().size(); i++) {
                String svcTaskNum = this.completionBean.getSvcTaskNumListForUsg().get(i);
                FSR_VISITTMsgArray fsrVisitTMsgList = getFsrVisit(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), svcTaskNum);
                if (fsrVisitTMsgList == null || fsrVisitTMsgList.getValidCount() == 0) {
                    setErrMsg(pMsg, NSZM0182E);
                    return false;
                }
                FSR_VISITTMsg fsrVisitTMsg = getFsrVisitFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), fsrVisitTMsgList.no(0).fsrVisitNum.getValue());
                if (!checkRtnCodeForSearch(pMsg, fsrVisitTMsg, new FSR_VISITTMsg())) {
                    return false;
                }
                if (!setFsrVisitValueForIstlChk(pMsg, svcTaskNum, fsrVisitTMsg)) {
                    return false;
                }
                S21ApiTBLAccessor.update(fsrVisitTMsg);
                if (!checkRtnCodeForUpdate(pMsg, fsrVisitTMsg)) {
                    return false;
                }
                //check current svcTask
                if (!checkDebriefError(pMsg, DEBRIEF_CHK_TRVL_CHRG, fsrVisitTMsg.fsrVisitNum.getValue(), null)) {
                    return false;
                }
//                // add start 2015/12/21 CSA Defect#1924
//                if (!hasValue(fsrVisitTMsg.svcTrvlDealAmt)) {
//                    fsrCompleteFlg = false;
//                    if (!updateSvcTaskStsCd(pMsg, SVC_TASK_STS.DEBRIEF_ERROR)) {
//                        return false;
//                    }
//                }
                // add end 2015/12/21 CSA Defect#1924
            }
        }
        String svcTaskNum = this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue();
        String fsrVisitNum = this.completionBean.getFsrVisitTMsg().fsrVisitNum.getValue();
        FSR_VISITTMsg fsrVisitTMsg = getFsrVisitFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), fsrVisitNum);
        if (!checkRtnCodeForSearch(pMsg, fsrVisitTMsg, new FSR_VISITTMsg())) {
            return false;
        }
        if (!setFsrVisitValueForIstlChk(pMsg, svcTaskNum, fsrVisitTMsg)) {
            return false;
        }
        S21ApiTBLAccessor.update(fsrVisitTMsg);
        if (!checkRtnCodeForUpdate(pMsg, fsrVisitTMsg)) {
            return false;
        }
        //check current svcTask
        if (!checkDebriefError(pMsg, DEBRIEF_CHK_TRVL_CHRG)) {
            return false;
                }
        return true;
    }

    private boolean updateFsrVisitStsCd(NSZC005001PMsg pMsg, String fsrVisitNum, String fsrVisitStsCd) {
        FSR_VISITTMsg fsrVisitTMsg = getFsrVisitFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), fsrVisitNum);
        if (!checkRtnCodeForSearch(pMsg, fsrVisitTMsg, new FSR_VISITTMsg())) {
            return false;
        }
        if (fsrVisitStsCd.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
            return true;
        }

        fsrVisitTMsg = getFsrVisitFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), fsrVisitNum);
        if (!checkRtnCodeForSearch(pMsg, fsrVisitTMsg, new FSR_VISITTMsg())) {
            return false;
        }
        setValue(fsrVisitTMsg.fsrVisitStsCd, fsrVisitStsCd);
        if (SVC_TASK_STS.CLOSED.equals(fsrVisitStsCd) || SVC_TASK_STS.PENDING_CHARGE.equals(fsrVisitStsCd) || SVC_TASK_STS.CANCELLED.equals(fsrVisitStsCd)) {
            // START 2024/01/25 t.aizawa [QC#63504, ADD]
            if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitCloDt)) {
            // END   2024/01/25 t.aizawa [QC#63504, ADD]
                setValue(fsrVisitTMsg.fsrVisitCloDt, sysDt);
                setValue(fsrVisitTMsg.fsrVisitCloTm, sysTm);
            // START 2024/01/25 t.aizawa [QC#63504, ADD]
            }
            // END   2024/01/25 t.aizawa [QC#63504, ADD]
        }

        S21ApiTBLAccessor.update(fsrVisitTMsg);
        if (!checkRtnCodeForUpdate(pMsg, fsrVisitTMsg)) {
            return false;
        }
        return true;
    }

    // Del Start 2019/01/10 K.Fujimoto QC#26861
    // Dead Logic.
//    private boolean updateModProcSts(NSZC005001PMsg pMsg) {
        // del start 2016/02/22 CSA Defect#2695
//        FSR_VISIT_TM_EVENTTMsgArray fsrVisitTmEventTmsgArray = getFsrFsrVisitTmEventByFsrNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
//        if (fsrVisitTmEventTmsgArray == null || fsrVisitTmEventTmsgArray.getValidCount() == 0) {
//            return true;
//        }
        // del end 2016/02/22 CSA Defect#2695

//        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstrFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
//        if (!checkRtnCodeForSearch(pMsg, svcMachMstrTMsg, new SVC_MACH_MSTRTMsg())) {
//            return false;
//        }
//        String serNum = svcMachMstrTMsg.serNum.getValue();
//        String mdseCd = svcMachMstrTMsg.mdseCd.getValue();
//
//        List<String> processedSvcMdlPlnList = new ArrayList<String>();
        // mod start 2016/02/22 CSA Defect#2695
//        for (int i = 0; i < fsrVisitTmEventTmsgArray.getValidCount(); i++) {
//            FSR_VISIT_TM_EVENTTMsg fsrVisitTmEventTmsg = fsrVisitTmEventTmsgArray.no(i);
//            if (ZYPCommonFunc.hasValue(fsrVisitTmEventTmsg.svcModPlnId) && !processedSvcMdlPlnList.contains(fsrVisitTmEventTmsg.svcModPlnId.getValue())) {
//                processedSvcMdlPlnList.add(fsrVisitTmEventTmsg.svcModPlnId.getValue());

//                NSZC067001PMsg apiPMsg = new NSZC067001PMsg();
//                setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//                setValue(apiPMsg.svcModPlnId, fsrVisitTmEventTmsg.svcModPlnId.getValue());
//                setValue(apiPMsg.svcTaskNum, fsrVisitTmEventTmsg.svcTaskNum.getValue());
//                setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
//                setValue(apiPMsg.mdseCd, mdseCd);
//                setValue(apiPMsg.serNum, serNum);
//                setValue(apiPMsg.svcModProcStsCd, SVC_MOD_PROC_STS.CLOSED);
//        for (int i = 0; i < pMsg.xxTmEventList.getValidCount(); i++) {
//            if (ZYPCommonFunc.hasValue(pMsg.xxTmEventList.no(i).svcModPlnId) && !processedSvcMdlPlnList.contains(pMsg.xxTmEventList.no(i).svcModPlnId.getValue())) {
//                processedSvcMdlPlnList.add(pMsg.xxTmEventList.no(i).svcModPlnId.getValue());
//
//                NSZC067001PMsg apiPMsg = new NSZC067001PMsg();
//                setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//                setValue(apiPMsg.svcModPlnId, pMsg.xxTmEventList.no(i).svcModPlnId.getValue());
//                setValue(apiPMsg.svcTaskNum, pMsg.xxTmEventList.no(i).svcTaskNum.getValue());
//                setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
//                setValue(apiPMsg.mdseCd, mdseCd);
//                setValue(apiPMsg.serNum, serNum);
//                setValue(apiPMsg.svcModProcStsCd, SVC_MOD_PROC_STS.CLOSED);
        // mod end 2016/02/22 CSA Defect#2695

//                new NSZC067001().execute(apiPMsg, onbatchType);
//                if (!checkErrMsg(pMsg, apiPMsg)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
    // Del End   2019/01/10 K.Fujimoto QC#26861

    private boolean updateModProcStsForChargable(NSZC005001PMsg pMsg, String svcTaskNum) {
        // Mod Start 2019/01/10 K.Fujimoto QC#26861
        if (this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
            return true;
        }
        // Mod End   2019/01/10 K.Fujimoto QC#26861

        // add start 2020/04/08 QC#56328
        List<BigDecimal> removePkList = (List<BigDecimal>) getSvcModSendClickByTaskNum(pMsg.glblCmpyCd.getValue(), svcTaskNum);
        for (int i = 0; i < removePkList.size(); i++) {
            SVC_MOD_SEND_CLICKTMsg svcModSendClickTMsg = new SVC_MOD_SEND_CLICKTMsg();
            setValue(svcModSendClickTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            setValue(svcModSendClickTMsg.svcModSendClickPk, removePkList.get(i));
            S21ApiTBLAccessor.logicalRemove(svcModSendClickTMsg);
        }
        // add end 2020/04/08 QC#56328

        //check mods event exists
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        // START 2017/06/27 N.Arai [QC#19125, MOD]
//        String modPlnId = getModsEventCount(glblCmpyCd, svcTaskNum);
//        if (!hasValue(modPlnId)) {
//            return true;
//        }
        // START 2022/12/01 K.Kitachi [QC#60882, ADD]
        S21StopWatch sw = new S21StopWatch();
        S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0050 : getModsEventCount Start." + " Task#" + svcTaskNum);
        sw.start();
        // END 2022/12/01 K.Kitachi [QC#60882, ADD]
        List<Map<String,Object>> modPlnIdMapList = getModsEventCount(glblCmpyCd, svcTaskNum);
        // START 2022/12/01 K.Kitachi [QC#60882, ADD]
        sw.stop();
        S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0050 : getModsEventCount End." + " Task#" + svcTaskNum);
        S21InfoLogOutput.println(String.format("CHK_CPLT_TM : NSZC0050 : getModsEventCount Elapsed Time [%s]", sw.getElapsedMilliSec()));
        // END 2022/12/01 K.Kitachi [QC#60882, ADD]
        if (modPlnIdMapList == null || modPlnIdMapList.size() == 0) {
            // START 2017/10/18 U.Kim [QC#21794, ADD]
            List<BigDecimal> logicalRemoveTarget = (List<BigDecimal>) getSvcModStsBySvcTaskNumAndSvcMachMstrPk(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), svcTaskNum);
            for (int i = 0; i < logicalRemoveTarget.size(); i++) {
                SVC_MOD_STSTMsg stsTMsg = new SVC_MOD_STSTMsg();
                setValue(stsTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                setValue(stsTMsg.svcModStsPk, logicalRemoveTarget.get(i));
                S21ApiTBLAccessor.logicalRemove(stsTMsg);
            }
            // END 2017/10/18 U.Kim [QC#21794, ADD]
            return true;
        }

        //check labor charge
        // mod start 2017/02/13 CSA Defect#17109
        if (MODE_CHARGABLE.equals(pMsg.xxModeCd.getValue())) {
            boolean isExistsLbor = false;
            for (int i = 0; i < pMsg.xxChargesList.getValidCount(); i++) {
                NSZC005001_xxChargesListPMsg chrg = pMsg.xxChargesList.no(i);
                if (svcTaskNum.equals(chrg.svcTaskNum.getValue())
                        && SVC_INV_CHRG_TP.LABOR_CHARGE.equals(chrg.svcInvChrgTpCd.getValue())) {
                    isExistsLbor = true;
                    break;
                }
            }

            if (!isExistsLbor) {
                return false;
            }
        }
        // mod end 2017/02/13 CSA Defect#17109

        SVC_TASKTMsg svcTask = getSvcTaskFindByKey(glblCmpyCd, svcTaskNum);
        if (!checkRtnCodeForSearch(pMsg, svcTask, new SVC_TASKTMsg())) {
            return false;
        }
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = null;
        for (int i =0; i < modPlnIdMapList.size(); i++) {
            svcMachMstrTMsg = null;
            Map<String,Object> modPlnIdMap = modPlnIdMapList.get(i);
            if (hasValue((BigDecimal) modPlnIdMap.get("SVC_MACH_MSTR_PK"))){
                svcMachMstrTMsg = getSvcMachMstrFindByKey(pMsg.glblCmpyCd.getValue(), (BigDecimal) modPlnIdMap.get("SVC_MACH_MSTR_PK"));
            }
            if (!checkRtnCodeForSearch(pMsg, svcMachMstrTMsg, new SVC_MACH_MSTRTMsg())) {
                return false;
            }
            BigDecimal svcMachMstrPk = svcMachMstrTMsg.svcMachMstrPk.getValue();
            String serNum = svcMachMstrTMsg.serNum.getValue();
            String mdseCd = svcMachMstrTMsg.mdseCd.getValue();

            // START 2023/03/13 T.Wada [QC#61217, ADD]
            // Check SVC_MOD_STS
            if(checkSvcModStsClosed(glblCmpyCd, (String) modPlnIdMap.get("SVC_MOD_PLN_ID"), svcMachMstrPk, svcTaskNum)) {
            	continue;
            }
            // END 2023/03/13 T.Wada [QC#61217, ADD]

            NSZC067001PMsg apiPMsg = new NSZC067001PMsg();
            setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(apiPMsg.svcModPlnId, (String) modPlnIdMap.get("SVC_MOD_PLN_ID"));
            setValue(apiPMsg.svcTaskNum, svcTaskNum);
            setValue(apiPMsg.svcMachMstrPk, svcMachMstrPk);
            setValue(apiPMsg.mdseCd, mdseCd);
            setValue(apiPMsg.serNum, serNum);
            setValue(apiPMsg.svcModProcStsCd, SVC_MOD_PROC_STS.CLOSED);

            new NSZC067001().execute(apiPMsg, onbatchType);
            if (!checkErrMsg(pMsg, apiPMsg)) {
                return false;
            }
        }
        // END 2017/06/27 N.Arai [QC#19125, MOD]

        return true;
    }

    private boolean updateSvcMachMstr(NSZC005001PMsg pMsg) {
        // mod start 2016/06/28 CSA Defect#10790
        // List<String> svcTaskNumList = getSvcTaskNumByStsCd(pMsg, null, new String[] {SVC_TASK_STS.CANCELLED });
        List<String> svcTaskNumList = getSvcTaskNumForMachUpdate(pMsg);
        // mod end 2016/06/28 CSA Defect#10790
        if (svcTaskNumList == null || svcTaskNumList.size() == 0) {
            return true;
        }

        for (String svcTaskNum : svcTaskNumList) {
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstrFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            if (!checkRtnCodeForSearch(pMsg, svcMachMstrTMsg, new SVC_MACH_MSTRTMsg())) {
                return false;
            }
            Map<String, Object> callTp = getCallTp(pMsg, svcTaskNum);
            if (callTp == null) {
                setErrMsg(pMsg, NSZM0752E);
                return false;
            }

            // START 2020/04/07 [QC#56457,MOD]
            //mod start 2016/04/19 CSA Defect#7004
            String mode = null;
            String svcMachMstrStsCd = svcMachMstrTMsg.svcMachMstrStsCd.getValue();
            // Mod Start 2018/06/25 QC#26799
            if (FSR_TP.INSTALL_CALL.equals((String) callTp.get("FSR_TP_CD")) && 
                    (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachMstrStsCd) || SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd))) {
                mode = ProcessMode.INSTALLATION.code;
                NSZC001001PMsg apiPMsg = setSvcMachMstrApiParams(pMsg, svcTaskNum, mode, callTp, svcMachMstrTMsg);
                new NSZC001001().execute(apiPMsg, onbatchType);
                if (!checkErrMsg(pMsg, apiPMsg)) {
                    return false;
                }
            // START 2023/07/14 K.Watanabe [QC#61310, ADD]
            } else if (FSR_TP.INSTALL_CALL.equals((String) callTp.get("FSR_TP_CD")) && 
                    (SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) || SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) && isShowRoomMachine(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.slsDt.getValue())){
                // do nothing
            // END 2023/07/14 K.Watanabe [QC#61310, ADD]
            } else if (FSR_TP.INSTALL_CALL.equals((String) callTp.get("FSR_TP_CD"))) {
                mode = ProcessMode.SERVICE_UPDATE.code;
                NSZC001001PMsg apiPMsg = setSvcMachMstrApiParams(pMsg, svcTaskNum, mode, callTp, svcMachMstrTMsg);
                new NSZC001001().execute(apiPMsg, onbatchType);
                if (!checkErrMsg(pMsg, apiPMsg)) {
                    return false;
                }
            // END 2020/04/07 [QC#56457,MOD]
            } else if (ZYPConstant.FLG_ON_Y.equals((String) callTp.get("SVC_CALL_FLG")) || ZYPConstant.FLG_ON_Y.equals((String) callTp.get("SVC_PRVNT_MAINT_FLG"))) {
                mode = ProcessMode.SERVICE_UPDATE.code;
                NSZC001001PMsg apiPMsg = setSvcMachMstrApiParams(pMsg, svcTaskNum, mode, callTp, svcMachMstrTMsg);
                new NSZC001001().execute(apiPMsg, onbatchType);
                if (!checkErrMsg(pMsg, apiPMsg)) {
                    return false;
                }
            }
            // Mod End 2018/06/25 QC#26799
            //mod end 2016/04/19 CSA Defect#7004
            // START 2017/12/04 U.Kim [QC#19907,ADD]
            sendMailForLocationUpdate(pMsg, svcMachMstrTMsg);
            // END 2017/12/04 U.Kim [QC#19907,ADD]
        }
        return true;
    }

    private List<String> getSvcTaskNumForMachUpdate(NSZC005001PMsg pMsg) {
        List<String> svcTaskNumList = new ArrayList<String>();
        if (MODE_DEBRIEF.equals(pMsg.xxModeCd.getValue())) {
            for (int i = 0; i < pMsg.xxVisitTaskList.getValidCount(); i++) {
                String svcTaskNum = pMsg.xxVisitTaskList.no(i).svcTaskNum.getValue();
                if (hasValue(svcTaskNum) && !svcTaskNumList.contains(svcTaskNum)) {
                    svcTaskNumList.add(svcTaskNum);
                }
            }
        } else if (MODE_INSTALL_CHECK.equals(pMsg.xxModeCd.getValue())) {
            for (int i = 0; i < pMsg.xxFsrIstlChkList.getValidCount(); i++) {
                String svcTaskNum = pMsg.xxFsrIstlChkList.no(i).svcTaskNum.getValue();
                if (hasValue(svcTaskNum) && !svcTaskNumList.contains(svcTaskNum)) {
                    svcTaskNumList.add(svcTaskNum);
                }
            }
        }
        return svcTaskNumList;
    }

    private boolean updateSvcTask(NSZC005001PMsg pMsg) {
        List<String> svcTaskNumList = new ArrayList<String>();
        if (fsrCompleteFlg) {
            // mod start 2016/12/13 CSA Defect#14252
            // mod start 2017/01/13 CSA Defect#17108
            svcTaskNumList = getSvcTaskNumByStsCd(pMsg, null, SVC_TASK_STS_LIST_OPEN);
            // mod end 2017/01/13 CSA Defect#17108
            // mod end 2016/12/13 CSA Defect#14252
            if (svcTaskNumList == null || svcTaskNumList.size() == 0) {
                return true;
            }
        } else {
            svcTaskNumList.add(this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue());
        }

        for (String svcTaskNum : svcTaskNumList) {
            SVC_TASKTMsg svcTaskTMsg = getSvcTaskFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), svcTaskNum);
            if (!checkRtnCodeForSearch(pMsg, svcTaskTMsg, new SVC_TASKTMsg())) {
                return false;
            }
            if (!setSvcTaskValue(pMsg, svcTaskNum, svcTaskTMsg)) {
                return false;
            }
            S21ApiTBLAccessor.update(svcTaskTMsg);
            if (!checkRtnCodeForUpdate(pMsg, svcTaskTMsg)) {
                return false;
            }
            // START 2016/10/03 T.Tomita [QC#11896, ADD]
            if (SVC_TASK_STS.COMPLETED.equals(svcTaskTMsg.svcTaskStsCd.getValue())) {
                if (!callHoldReleaseAPI(pMsg)) {
                    return false;
                }
            }
            // END 2016/10/03 T.Tomita [QC#11896, ADD]
        }

        // del start 2016/06/28 CSA Defect#10790
        // Update Service Machine Master
        // if (fsrCompleteFlg) {
        //     if (!updateSvcMachMstr(pMsg)) {
        //        return false;
        //    }
        // }
        // del end 2016/06/28 CSA Defect#10790

        return true;
    }

    private boolean updateSvcTaskCallTp(NSZC005001PMsg pMsg) {
        SVC_TASKTMsg svcTaskTMsg = getSvcTaskFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue());
        if (!checkRtnCodeForSearch(pMsg, svcTaskTMsg, new SVC_TASKTMsg())) {
            return false;
        }

        if (DS_SVC_CALL_TP.SERV_CALL.equals(svcTaskTMsg.dsSvcCallTpCd.getValue())) {
            setValue(svcTaskTMsg.dsSvcCallTpCd, DS_SVC_CALL_TP.RECALL);
        } else if (DS_SVC_CALL_TP.AHS_SERV_CALL.equals(svcTaskTMsg.dsSvcCallTpCd.getValue())) {
            setValue(svcTaskTMsg.dsSvcCallTpCd, DS_SVC_CALL_TP.AHS_RECALL);
        } else {
            return true;
        }
        S21ApiTBLAccessor.update(svcTaskTMsg);
        if (!checkRtnCodeForUpdate(pMsg, svcTaskTMsg)) {
            return false;
        }
        // add start 2016/12/01 CSA Defect#14252
        this.completionBean.setSvcTaskTMsg(svcTaskTMsg);
        // add end 2016/12/01 CSA Defect#14252
        return true;
    }

    // START 2019/03/28 K.Fujimoto [QC#30546, ADD]

    private boolean updateSvcTaskBillTp(NSZC005001PMsg pMsg) {
        // START  2020/05/18 K.Sakurai [QC#55328, ADD]
        FSRTMsg fsrTMsg = getFsrFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (checkRtnCodeForSearch(pMsg, fsrTMsg, new FSRTMsg())) {
            String callSrcTpCd = fsrTMsg.svcCallSrcTpCd.getValue();
            if (SVC_CALL_SRC_TP.CROSS_SERVICE.equals(callSrcTpCd)) {
                return true;
            }
        }
        // END  2020/05/18 K.Sakurai [QC#55328, ADD]
        SVC_TASKTMsg svcTaskTMsg = getSvcTaskFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue());
        if (!checkRtnCodeForSearch(pMsg, svcTaskTMsg, new SVC_TASKTMsg())) {
            return false;
        }

        // get Bill Type Code from Pricing API
        NSZC061001PMsg apiPMsg = new NSZC061001PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NSZC061001Constant.PROCESS_MODE_CALL_CREATION);
        setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValue(apiPMsg.startDt, pMsg.slsDt);
        setValue(apiPMsg.startTm, this.sysTm);
        setValue(apiPMsg.dsSvcCallTpCd, svcTaskTMsg.dsSvcCallTpCd);
        new NSZC061001().execute(apiPMsg, onbatchType);
        if (!checkErrMsg(pMsg, apiPMsg)) {
            return false;
        }
        setValue(svcTaskTMsg.svcBillTpCd, apiPMsg.svcBillTpCd);
        S21ApiTBLAccessor.update(svcTaskTMsg);
        if (!checkRtnCodeForUpdate(pMsg, svcTaskTMsg)) {
            return false;
        }
        this.completionBean.setSvcTaskTMsg(svcTaskTMsg);
        return true;
    }
    // END  2019/03/28 K.Fujimoto [QC#30546, ADD]
    private boolean updateSvcTaskStsCd(NSZC005001PMsg pMsg, String svcTaskStsCd) {
        SVC_TASKTMsg svcTaskTMsg = getSvcTaskFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue());
        if (!checkRtnCodeForSearch(pMsg, svcTaskTMsg, new SVC_TASKTMsg())) {
            return false;
        }
        // Add Start 2019/01/10 K.Fujimoto QC#26861
        if (this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
            return true;
        }
        // Add End   2019/01/10 K.Fujimoto QC#26861

        setValue(svcTaskTMsg.svcTaskStsCd, svcTaskStsCd);
        S21ApiTBLAccessor.update(svcTaskTMsg);
        if (!checkRtnCodeForUpdate(pMsg, svcTaskTMsg)) {
            return false;
        }
        return true;
    }
    // add start 2015/12/16 CSA Defect#978
    private boolean updateFsrChrg(NSZC005001PMsg pMsg) {

        //get basic data for pricing
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String fsrNum = pMsg.fsrNum.getValue();

        FSRTMsg fsr = getFsrFindByKey(glblCmpyCd, fsrNum);
        BigDecimal actlExchRate = getExchRateData(glblCmpyCd, fsr.invCcyCd.getValue(), pMsg.slsDt.getValue());

        //delete FSR_CHRG_DTL
        if (!deleteFsrChrgDtl(pMsg)) {
            return false;
        }

        // add start 2016/09/14 CSA Defect#13987
        //call Service Pricing API
        // START 2022/12/01 K.Kitachi [QC#60882, ADD]
        S21StopWatch sw = new S21StopWatch();
        S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0050 : getLborFsrVisitTask Start." + " FSR#" + fsrNum);
        sw.start();
        // END 2022/12/01 K.Kitachi [QC#60882, ADD]
        List<Map<String, Object>> lborList = getLborFsrVisitTask(pMsg, fsrNum);
        // START 2022/12/01 K.Kitachi [QC#60882, ADD]
        sw.stop();
        S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0050 : getLborFsrVisitTask End." + " FSR#" + fsrNum);
        S21InfoLogOutput.println(String.format("CHK_CPLT_TM : NSZC0050 : getLborFsrVisitTask Elapsed Time [%s]", sw.getElapsedMilliSec()));
        // END 2022/12/01 K.Kitachi [QC#60882, ADD]
        FSR_VISITTMsgArray fsrVisitList = getFsrVisitByFsrNum(glblCmpyCd, fsrNum);
        FSR_USGTMsgArray fsrUsgList = new FSR_USGTMsgArray();
        FSR_EXPTMsgArray fsrExpList = new FSR_EXPTMsgArray();
        if (!calculatePriceForFsrChrg(pMsg, fsr, lborList, fsrUsgList, fsrVisitList, fsrExpList)) {
            return false;
        }
        // add end 2016/09/14 CSA Defect#13987

        //insert or update FSR_CHRG_DTL
        List<FSR_CHRG_DTLTMsg> fsrChrgDtlList = new ArrayList<FSR_CHRG_DTLTMsg>();
        for (int i = 0; i < pMsg.xxChargesList.getValidCount(); i++) {
            NSZC005001_xxChargesListPMsg chrg = pMsg.xxChargesList.no(i);
            // mod start 2016/05/19 CSA Defect#8457
            String tocCd = null;
            if (hasValue(chrg.fsrVisitNum)) {
                FSR_VISITTMsg fsrVisit = getFsrVisitFindByKey(glblCmpyCd, fsrNum, chrg.fsrVisitNum.getValue());
                tocCd = getTocCd(glblCmpyCd, fsrVisit.techCd.getValue(), pMsg.slsDt.getValue());
            } else {
                FSR_VISITTMsgArray fsrVisitArray = getFsrVisitByFsrNum(glblCmpyCd, fsrNum);
                if (fsrVisitArray.getValidCount() > 0) {
                    tocCd = getTocCd(glblCmpyCd, fsrVisitArray.no(0).techCd.getValue(), pMsg.slsDt.getValue());
                }
            }
            // mod end 2016/05/19 CSA Defect#8457

            FSR_CHRG_DTLTMsg fsrChrgDtl = setFsrChrgDtlValue(pMsg, chrg, tocCd, actlExchRate);
            if (hasValue(chrg.fsrChrgDtlPk)) {
                S21ApiTBLAccessor.update(fsrChrgDtl);
                if (!checkRtnCodeForUpdate(pMsg, fsrChrgDtl)) {
                    return false;
                }
            } else {
                S21ApiTBLAccessor.create(fsrChrgDtl);
                if (!checkRtnCodeForInsert(pMsg, fsrChrgDtl)) {
                    return false;
                }
            }
            fsrChrgDtlList.add(fsrChrgDtl);
        }

        //update FSR_CHRG
        if (!updateFsrChrg(pMsg, fsrChrgDtlList)) {
            return false;
        }
        return true;
    }

    private boolean updateFsrChrg(NSZC005001PMsg pMsg, List<FSR_CHRG_DTLTMsg> fsrChrgDtlList) {

        FSR_CHRGTMsg fsrChrg = getFsrChrgFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        initFsrChrg(fsrChrg);

        for (FSR_CHRG_DTLTMsg fsrChrgDtl : fsrChrgDtlList) {
            if (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(fsrChrgDtl.svcInvChrgTpCd.getValue())) {
                summaryFsrChrgDtl(fsrChrgDtl, fsrChrg.svcLborDealAmt, fsrChrg.svcLborFuncAmt, fsrChrg.svcLborDealDiscAmt, fsrChrg.svcLborFuncDiscAmt);
            } else if (SVC_INV_CHRG_TP.TRAVEL_CHARGE.equals(fsrChrgDtl.svcInvChrgTpCd.getValue())) {
                summaryFsrChrgDtl(fsrChrgDtl, fsrChrg.svcTrvlDealAmt, fsrChrg.svcTrvlFuncAmt, fsrChrg.svcTrvlDealDiscAmt, fsrChrg.svcTrvlFuncDiscAmt);
            } else if (SVC_INV_CHRG_TP.PARTS_CHARGE.equals(fsrChrgDtl.svcInvChrgTpCd.getValue())) {
                summaryFsrChrgDtl(fsrChrgDtl, fsrChrg.svcPrtDealAmt, fsrChrg.svcPrtFuncAmt, fsrChrg.svcPrtDealDiscAmt, fsrChrg.svcPrtFuncDiscAmt);
            } else if (SVC_INV_CHRG_TP.EXPENSE_CHARGE.equals(fsrChrgDtl.svcInvChrgTpCd.getValue())) {
                summaryFsrChrgDtl(fsrChrgDtl, fsrChrg.svcExpDealAmt, fsrChrg.svcExpFuncAmt, fsrChrg.svcExpDealDiscAmt, fsrChrg.svcExpFuncDiscAmt);
            }
        }

        setValue(fsrChrg.updPsnCd, pMsg.userId);

        //update FSR_CHRG
        S21ApiTBLAccessor.update(fsrChrg);
        if (!checkRtnCodeForUpdate(pMsg, fsrChrg)) {
            return false;
        }
        return true;
    }
    private FSR_CHRGTMsg initFsrChrg(FSR_CHRGTMsg fsrChrg) {
        fsrChrg.svcLborDealAmt.clear();
        fsrChrg.svcLborFuncAmt.clear();
        fsrChrg.svcLborDealDiscAmt.clear();
        fsrChrg.svcLborFuncDiscAmt.clear();
        fsrChrg.svcTrvlDealAmt.clear();
        fsrChrg.svcTrvlFuncAmt.clear();
        fsrChrg.svcTrvlDealDiscAmt.clear();
        fsrChrg.svcTrvlFuncDiscAmt.clear();
        fsrChrg.svcPrtDealAmt.clear();
        fsrChrg.svcPrtFuncAmt.clear();
        fsrChrg.svcPrtDealDiscAmt.clear();
        fsrChrg.svcPrtFuncDiscAmt.clear();
        fsrChrg.svcExpDealAmt.clear();
        fsrChrg.svcExpFuncAmt.clear();
        fsrChrg.svcExpDealDiscAmt.clear();
        fsrChrg.svcExpFuncDiscAmt.clear();
        return fsrChrg;
    }

    private boolean updateSts(NSZC005001PMsg pMsg) {

        //get basic data for pricing
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String fsrNum = pMsg.fsrNum.getValue();

        SVC_TASKTMsgArray svcTaskList = getSvcTaskByFsrNumForUpdate(glblCmpyCd, fsrNum);

        //decision status
        boolean isAllComplete = isAllComplete(svcTaskList);
        boolean isExistDebriefErr = isExistDebriefErr(svcTaskList);
        boolean isErrInvtyUpd = false;

        if (isAllComplete) {
            for (int i = 0; i < svcTaskList.getValidCount(); i++) {
                SVC_TASKTMsg svcTask = svcTaskList.no(i);
                if (SVC_TASK_STS.COMPLETED.equals(svcTask.svcTaskStsCd.getValue())) {
                    //update SVC_TASK
                    setValue(svcTask.svcTaskStsCd, SVC_TASK_STS.CLOSED);
                    // add start 2016/02/08 CSA Defect#2863
                    setValue(svcTask.svcTaskCloDt, sysDt);
                    setValue(svcTask.svcTaskCloTm, sysTm);
                    setValue(svcTask.svcTaskCloByUsrId, pMsg.userId);
                    // add end 2016/02/08 CSA Defect#2863

                    S21ApiTBLAccessor.update(svcTask);
                    if (!checkRtnCodeForUpdate(pMsg, svcTask)) {
                        return false;
                    }

                    //update FSR_VISIT
                    FSR_VISITTMsgArray fsrVisitList = getFsrVisitForUpdate(glblCmpyCd, fsrNum, svcTask.svcTaskNum.getValue());
                    for (int j = 0; j < fsrVisitList.getValidCount(); j++) {
                        // mod start 2016/04/28 CSA Defect#7732
                        setValue(fsrVisitList.no(j).fsrVisitStsCd, SVC_TASK_STS.CLOSED);
                        // add start 2016/02/08 CSA Defect#2863
                        setValue(fsrVisitList.no(j).fsrVisitCloDt, sysDt);
                        setValue(fsrVisitList.no(j).fsrVisitCloTm, sysTm);
                        // add end 2016/02/08 CSA Defect#2863
                        S21ApiTBLAccessor.update(fsrVisitList.no(j));
                        if (!checkRtnCodeForUpdate(pMsg, fsrVisitList.no(j))) {
                            return false;
                        }
                        insertFsrEvent(pMsg, fsrVisitList.no(j));
                        // mod end 2016/04/28 CSA Defect#7732
                    }

                    //call MODS update api
                    // START 2022/05/25 K.Kitachi [QC#59987, DEL]
//                    if (!updateModProcStsForChargable(pMsg, svcTask.svcTaskNum.getValue())) {
//                        return false;
//                    }
                    // END 2022/05/25 K.Kitachi [QC#59987, DEL]
                    //update FSR_VISIT_TM_EVENT
                    if (!updateFsrVisitTmEvent(pMsg, svcTask.svcTaskNum.getValue())) {
                        return false;
                    }
                    // mod start 2016/02/08 CSA Defect#2863
                    //Add Start 2018/11/28 K.Fujimoto QC#28647
                    Map<BigDecimal, NSZC005001_xxChargesListPMsg> partsChrgDiffMap = null;
                    //Add End   2018/11/28 K.Fujimoto QC#28647
                    if (!frceUpdFlg) {
                        // call inventory update api
                        //Mod Start 2018/11/28 K.Fujimoto QC#28647
                        partsChrgDiffMap = new HashMap<BigDecimal, NSZC005001_xxChargesListPMsg>();
                        // if (!updateInvtyForChargable(pMsg, svcTask.svcTaskNum.getValue())) {
                        if (!updateInvtyForChargable(pMsg, svcTask.svcTaskNum.getValue(), partsChrgDiffMap)) {
                        //Mod End   2018/11/28 K.Fujimoto QC#28647
                            isErrInvtyUpd = true;
                        }
                    }
                    // mod end 2016/02/08 CSA Defect#2863
                    //update FSR_USG
                    //Mod Start 2018/11/28 K.Fujimoto QC#28647
                    if (!updateFsrUsgForChargable(pMsg, svcTask.svcTaskNum.getValue(), partsChrgDiffMap)) {
                    //Mod End   2018/11/28 K.Fujimoto QC#28647
                        return false;
                    }
                }
            }
        }
        // Add Start 2019/01/10 K.Fujimoto QC#26861
        if (this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
            for (int i = 0; i < svcTaskList.getValidCount(); i++) {
                SVC_TASKTMsg svcTask = svcTaskList.no(i);
                // START  2020/05/25 K.Sakurai [QC#56605, ADD]
                if (SVC_TASK_STS.CANCELLED.equals(svcTask.svcTaskStsCd.getValue())) {
                    continue;
                }
                // END  2020/05/25 K.Sakurai [QC#56605, ADD]
                // START 2024/01/25 t.aizawa [QC#63504, ADD]
                if (!ZYPCommonFunc.hasValue(svcTask.svcTaskCloDt)) {
                // END   2024/01/25 t.aizawa [QC#63504, ADD]
                    // update SVC_TASK
                    setValue(svcTask.svcTaskStsCd, SVC_TASK_STS.CLOSED);
                    setValue(svcTask.svcTaskCloDt, sysDt);
                    setValue(svcTask.svcTaskCloTm, sysTm);
                    setValue(svcTask.svcTaskCloByUsrId, pMsg.userId);
                    S21ApiTBLAccessor.update(svcTask);
                    if (!checkRtnCodeForUpdate(pMsg, svcTask)) {
                        return false;
                    }
                // START 2024/01/25 t.aizawa [QC#63504, ADD]
                }
                // END   2024/01/25 t.aizawa [QC#63504, ADD]
                // update FSR_VISIT
                FSR_VISITTMsgArray fsrVisitList = getFsrVisitForUpdate(glblCmpyCd, fsrNum, svcTask.svcTaskNum.getValue());
                for (int j = 0; j < fsrVisitList.getValidCount(); j++) {
                    // START 2024/01/25 t.aizawa [QC#63504, ADD]
                    if (!ZYPCommonFunc.hasValue(fsrVisitList.no(j).fsrVisitCloDt)) {
                    // END   2024/01/25 t.aizawa [QC#63504, ADD]
                        setValue(fsrVisitList.no(j).fsrVisitStsCd, SVC_TASK_STS.CLOSED);
                        setValue(fsrVisitList.no(j).fsrVisitCloDt, sysDt);
                        setValue(fsrVisitList.no(j).fsrVisitCloTm, sysTm);
                        S21ApiTBLAccessor.update(fsrVisitList.no(j));
                        if (!checkRtnCodeForUpdate(pMsg, fsrVisitList.no(j))) {
                            return false;
                        }
                        // insert FSR_EVENT
                        insertFsrEvent(pMsg, fsrVisitList.no(j));
                    // START 2024/01/25 t.aizawa [QC#63504, ADD]
                    }
                    // END   2024/01/25 t.aizawa [QC#63504, ADD]
                }


                // Add Start 2019/01/10 K.Fujimoto QC#30250
                Map<BigDecimal, NSZC005001_xxChargesListPMsg> partsChrgDiffMap = new HashMap<BigDecimal, NSZC005001_xxChargesListPMsg>();
                updateInvtyForChargable(pMsg, svcTask.svcTaskNum.getValue(), partsChrgDiffMap);
                // Add End   2019/01/10 K.Fujimoto QC#30250

                // update FSR_VISIT_TM_EVENT
                if (!updateFsrVisitTmEvent(pMsg, svcTask.svcTaskNum.getValue())) {
                    return false;
                }
                // Add Start 2019/02/13 K.Fujimoto QC#30310
                modMsgForChangeUsageForChargeble(pMsg, svcTask.svcTaskNum.getValue(), partsChrgDiffMap);
                // Add End   2019/02/13 K.Fujimoto QC#30310
                // Mod Start 2019/01/10 K.Fujimoto QC#30250
                if (!updateFsrUsgForChargable(pMsg, svcTask.svcTaskNum.getValue(), partsChrgDiffMap)) {
                // Mod Start 2019/01/10 K.Fujimoto QC#30250
                    return false;
                }
            }
            // Add Start 2019/01/10 K.Fujimoto QC#30250
            String modCloSvcTaskMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_MOD_CLO_SVC_TASK_MSG, pMsg.glblCmpyCd.getValue());
            insertSvcMemoForClosedSvcTaskUpdate(pMsg, null, modCloSvcTaskMsg);
            // Add Start 2019/01/10 K.Fujimoto QC#30250
        }
        // Add End   2019/01/10 K.Fujimoto QC#26861
        FSRTMsg fsr = getFsrFindByKeyForUpdate(glblCmpyCd, fsrNum);
        if (isAllComplete) {
            // START 2024/01/25 t.aizawa [QC#63504, ADD]
            if (!ZYPCommonFunc.hasValue(fsr.fsrCloDt)) {
            // END   2024/01/25 t.aizawa [QC#63504, ADD]
                setValue(fsr.fsrStsCd, SVC_TASK_STS.CLOSED);
                setValue(fsr.fsrCloDt, sysDt);
                setValue(fsr.fsrCloTm, sysTm);
            // START 2024/01/25 t.aizawa [QC#63504, ADD]
            }
            // END   2024/01/25 t.aizawa [QC#63504, ADD]
        } else if (isExistDebriefErr) {
            setValue(fsr.fsrStsCd, SVC_TASK_STS.DEBRIEF_ERROR);
        }
        if (isErrInvtyUpd) {
            setValue(fsr.fsrStsCd, SVC_TASK_STS.DEBRIEF_ERROR);
        }

        S21ApiTBLAccessor.update(fsr);
        if (!checkRtnCodeForUpdate(pMsg, fsr)) {
            return false;
        }
        return true;
    }

    private boolean updateFsrVisitTmEvent(NSZC005001PMsg pMsg, String svcTaskNum) {

        FSR_VISIT_TM_EVENTTMsgArray fsrVisitTmEventList = getFsrFsrVisitTmEventBySvcTaskNum(pMsg.glblCmpyCd.getValue(), svcTaskNum);
        for (int i = 0; i < fsrVisitTmEventList.getValidCount(); i++) {
            BigDecimal fsrVisitTmEventPk = fsrVisitTmEventList.no(i).fsrVisitTmEventPk.getValue();
            FSR_VISIT_TM_EVENTTMsg outTMsg = getFsrVisiTmEventFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), fsrVisitTmEventPk);
            setValue(outTMsg.fsrVisitTmEventProcFlg, ZYPConstant.FLG_ON_Y);
            S21ApiTBLAccessor.update(outTMsg);
            if (!checkRtnCodeForUpdate(pMsg, outTMsg)) {
                return false;
            }
        }
        return true;
    }
    //Mod Start 2018/11/28 K.Fujimoto QC#28647
    // private boolean updateInvtyForChargable(NSZC005001PMsg pMsg, String svcTaskNum) {
    private boolean updateInvtyForChargable(NSZC005001PMsg pMsg, String svcTaskNum, Map<BigDecimal, NSZC005001_xxChargesListPMsg> partsChrgDiffMap) {
    //Mod End   2018/11/28 K.Fujimoto QC#28647
        // Add Start 2019/01/10 K.Fujimoto QC#30250
        // Add Start 2019/01/10 K.Fujimoto QC#26861
        // if (this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)){
        //     return true;
        // }
        // Add End   2019/01/10 K.Fujimoto QC#26861
        // Add End   2019/01/10 K.Fujimoto QC#30250
        List<NSZC005001_xxChargesListPMsg> partsChrgList = new ArrayList<NSZC005001_xxChargesListPMsg>();
        for (int i = 0; i < pMsg.xxChargesList.getValidCount(); i++) {
            NSZC005001_xxChargesListPMsg chrg = pMsg.xxChargesList.no(i);
            if (svcTaskNum.equals(chrg.svcTaskNum.getValue())
                    && SVC_INV_CHRG_TP.PARTS_CHARGE.equals(chrg.svcInvChrgTpCd.getValue())) {
                partsChrgList.add(chrg);
            }
        }


        //Add Start 2018/11/28 K.Fujimoto QC#28647
        FSR_USGTMsgArray fsrUsgArray = getFsrUsgListBySvcTaskNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue() ,svcTaskNum);
        List<FSR_USGTMsg> fsrUsgList = new ArrayList<FSR_USGTMsg>();
        for (int i = 0; i < fsrUsgArray.getValidCount(); i++){
            fsrUsgList.add(fsrUsgArray.no(i));
        }
        //Add End   2018/11/28 K.Fujimoto QC#28647

        if (partsChrgList.isEmpty()) {
            return true;
        }

        // Call Inventory Update API
        // START 2017/10/13 K.Kim [QC#17430, ADD]
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstrFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        String billToAcctNum = svcMachMstrTMsg.billToAcctNum.getValue();
        // END 2017/10/13 K.Kim [QC#17430, ADD]

        // Add Start 2018/11/28 K.Fujimoto QC#28647
        // Remove withdrawed parts-usage from processing target.
        List<NSZC005001_xxChargesListPMsg> removePartsChrgList = new ArrayList<NSZC005001_xxChargesListPMsg>();
        FSR_USGTMsg removefsrUsgTMsg = null;
        for (NSZC005001_xxChargesListPMsg partsChrg : partsChrgList) {
            // Del Start 2019/01/10 K.Fujimoto QC#26861
            // Dead logic.
            // Boolean fsrUsgChkFlg = true;
            // Del End   2019/01/10 K.Fujimoto QC#26861
            for (FSR_USGTMsg fsrUsgTMsg : fsrUsgList){
                String usgFsrNum = fsrUsgTMsg.fsrNum.getValue();
                String chrgFsrNum = partsChrg.fsrNum.getValue();
                String usgSvcTaskNum = fsrUsgTMsg.svcTaskNum.getValue();
                String chrgSvcTaskNum = partsChrg.svcTaskNum.getValue();
                String usgMdseCd = fsrUsgTMsg.mdseCd.getValue();
                String chrgMdseCd = partsChrg.mdseCd.getValue();
                BigDecimal usgSvcPrtQty = fsrUsgTMsg.svcPrtQty.getValue();
                BigDecimal chrgChrgQty = partsChrg.svcChrgQty.getValue();
                if(usgFsrNum.equals(chrgFsrNum) && usgSvcTaskNum.equals(chrgSvcTaskNum) && usgMdseCd.equals(chrgMdseCd) && usgSvcPrtQty.compareTo(chrgChrgQty) == 0){
                    removePartsChrgList.add(partsChrg);
                    removefsrUsgTMsg = fsrUsgTMsg;
                    // Del Start 2019/01/10 K.Fujimoto QC#26861
                    // Dead logic.
                    // fsrUsgChkFlg = false;
                    // Del End   2019/01/10 K.Fujimoto QC#26861
                }
            }
            fsrUsgList.remove(removefsrUsgTMsg);
        }
        if(removePartsChrgList.size() > 0){
            partsChrgList.removeAll(removePartsChrgList);
        }
        // Add End   2018/11/28 K.Fujimoto QC#28647

        // Mod Start 2018/11/28 K.Fujimoto QC#28647
        for (NSZC005001_xxChargesListPMsg partsChrgPMsg : partsChrgList) {
        // Mod End   2018/11/28 K.Fujimoto QC#28647

            // Add Start 2018/11/28 K.Fujimoto QC#28647
            NSZC005001_xxChargesListPMsg partsChrg = new NSZC005001_xxChargesListPMsg();
            EZDMsg.copy(partsChrgPMsg, null, partsChrg, null);
            // Add Start 2019/02/13 K.Fujimoto QC#30310
            String svcInvtyExFlg = null;
            // Add End 2019/02/13 K.Fujimoto QC#30310
            for (FSR_USGTMsg fsrUsgTMsg : fsrUsgList){
                String usgFsrNum = fsrUsgTMsg.fsrNum.getValue();
                String chrgFsrNum = partsChrg.fsrNum.getValue();
                String usgSvcTaskNum = fsrUsgTMsg.svcTaskNum.getValue();
                String chrgSvcTaskNum = partsChrg.svcTaskNum.getValue();
                String usgMdseCd = fsrUsgTMsg.mdseCd.getValue();
                String chrgMdseCd = partsChrg.mdseCd.getValue();
                if(usgFsrNum.equals(chrgFsrNum) && usgSvcTaskNum.equals(chrgSvcTaskNum) && usgMdseCd.equals(chrgMdseCd)){
                    fsrUsgList.remove(fsrUsgTMsg);
                    BigDecimal diffQty = partsChrg.svcChrgQty.getValue().subtract(fsrUsgTMsg.svcPrtQty.getValue());
                    setValue(partsChrg.svcChrgQty,diffQty);
                    partsChrgDiffMap.put(fsrUsgTMsg.fsrUsgPk.getValue(), partsChrg);
                    svcInvtyExFlg = fsrUsgTMsg.svcInvtyExFlg.getValue();
                    break;
                }
            }
            // Add Start 2019/02/13 K.Fujimoto QC#30310
            if (hasValue(svcInvtyExFlg) && ZYPConstant.FLG_ON_Y.equals(svcInvtyExFlg)) {
                continue;
            }
            // Add End 2019/02/13 K.Fujimoto QC#30310
            // Add Start 2018/11/28 K.Fujimoto QC#28647

            FSR_VISITTMsg fsrVisit = getFsrVisitFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), partsChrg.fsrVisitNum.getValue());

            String tocCd = getTocCd(pMsg.glblCmpyCd.getValue(), fsrVisit.techCd.getValue(), pMsg.slsDt.getValue());
            if (!ZYPCommonFunc.hasValue(tocCd)) {
                setErrMsg(pMsg, NSZM0344E);
                return false;
            }

            MDSETMsg mdseTMsg = getMdseFindByKey(pMsg.glblCmpyCd.getValue(), partsChrg.mdseCd.getValue());
            if (!checkRtnCodeForSearch(pMsg, mdseTMsg, new MDSETMsg())) {
                return false;
            }
            String coaProdCd = mdseTMsg.coaProdCd.getValue();

            //Mod Start 2019/07/30 K.Fujimoto QC#52220
            if (!partsChrg.svcChrgQty.getValue().equals(BigDecimal.ZERO)) {
                // START 2017/10/13 K.Kim [QC#17430, MOD]
                // NLZC002001PMsg invtyUpdApiPMsg = setInventoryUpdateApiParamsUsgForChrgable(pMsg, partsChrg, fsrVisit.techCd.getValue(), tocCd, coaProdCd);
                NLZC002001PMsg invtyUpdApiPMsg = setInventoryUpdateApiParamsUsgForChrgable(pMsg, partsChrg, fsrVisit.techCd.getValue(), tocCd, coaProdCd, billToAcctNum);
                // Add Start 2018/11/28 K.Fujimoto QC#28647
                MDSE_STORE_PKGTMsg currentMdseStorePkgTMsg = getMdseStorePkgFindByKey(pMsg.glblCmpyCd.getValue(), mdseTMsg.mdseCd.getValue(), partsChrg.uomCd.getValue());
                if (!checkRtnCodeForSearch(pMsg, currentMdseStorePkgTMsg, new MDSE_STORE_PKGTMsg())) {
                    return false;
                }
                setValue(invtyUpdApiPMsg.xxRqstQty,invtyUpdApiPMsg.xxRqstQty.getValue().multiply(currentMdseStorePkgTMsg.inEachQty.getValue()));
                // Add Start 2018/11/28 K.Fujimoto QC#28647
                // END 2017/10/13 K.Kim [QC#17430, MOD]
                new NLZC002001().execute(invtyUpdApiPMsg, onbatchType);
                if (!checkErrMsg(pMsg, invtyUpdApiPMsg)) {
                    return false;
                }

                // Register Returnable Parts
                if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.rtrnReqPrtFlg.getValue())) {
                    // START 2017/10/13 K.Kim [QC#17430, MOD]
                    // invtyUpdApiPMsg = setInventoryUpdateApiParamsRtnForChragable(pMsg, partsChrg, fsrVisit.techCd.getValue(), tocCd, coaProdCd);
                    invtyUpdApiPMsg = setInventoryUpdateApiParamsRtnForChragable(pMsg, partsChrg, fsrVisit.techCd.getValue(), tocCd, coaProdCd, billToAcctNum);
                    // END 2017/10/13 K.Kim [QC#17430, MOD]
                    // Add Start 2018/11/28 K.Fujimoto QC#28647
                    MDSE_STORE_PKGTMsg rtnMdseStorePkgTMsg = getMdseStorePkgFindByKey(pMsg.glblCmpyCd.getValue(), mdseTMsg.mdseCd.getValue(), partsChrg.uomCd.getValue());
                    if (!checkRtnCodeForSearch(pMsg, rtnMdseStorePkgTMsg, new MDSE_STORE_PKGTMsg())) {
                        return false;
                    }
                    setValue(invtyUpdApiPMsg.xxRqstQty,invtyUpdApiPMsg.xxRqstQty.getValue().multiply(rtnMdseStorePkgTMsg.inEachQty.getValue()));
                    // Add Start 2018/11/28 K.Fujimoto QC#28647
                    new NLZC002001().execute(invtyUpdApiPMsg, onbatchType);
                    if (!checkErrMsg(pMsg, invtyUpdApiPMsg)) {
                        return false;
                    }
                }
            }
            //Mod End   2019/07/30 K.Fujimoto QC#52220
        }
        return true;
    }

    //Mod Start 2018/11/28 K.Fujimoto QC#28647
    // private boolean updateFsrUsgForChargable(NSZC005001PMsg pMsg, String svcTaskNum) {
    private boolean updateFsrUsgForChargable(NSZC005001PMsg pMsg, String svcTaskNum, Map<BigDecimal, NSZC005001_xxChargesListPMsg> partsChrgDiffMap) {
    //Mod Start 2018/11/28 K.Fujimoto QC#28647

        FSR_USGTMsgArray  fsrUsgList = getFsrUsgListBySvcTaskNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), svcTaskNum);
        for (int j = 0; j < fsrUsgList.getValidCount(); j++) {
            BigDecimal fsrUsgPk = fsrUsgList.no(j).fsrUsgPk.getValue();
            FSR_USGTMsg outfsrUsgTMsg = getFsrUsgtFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), fsrUsgPk);
            setValue(outfsrUsgTMsg.fsrUsgProcFlg, ZYPConstant.FLG_ON_Y);
            // Add Start 2018/11/28 K.Fujimoto QC#28647
            // If the quantity of parts-usage has been changed, update this value.
            if (partsChrgDiffMap != null && partsChrgDiffMap.containsKey(fsrUsgPk)){
                BigDecimal svcPrtQty = outfsrUsgTMsg.svcPrtQty.getValue().add(partsChrgDiffMap.get(fsrUsgPk).svcChrgQty.getValue());
                setValue(outfsrUsgTMsg.svcPrtQty, svcPrtQty);
            }
            // Add End 2018/11/28 K.Fujimoto QC#28647
            S21ApiTBLAccessor.update(outfsrUsgTMsg);
            if (!checkRtnCodeForUpdate(pMsg, outfsrUsgTMsg)) {
                return false;
            }
        }
        return true;
    }

    private boolean insertFsrEvent(NSZC005001PMsg pMsg, FSR_VISITTMsg fsrVisit) {
        FSR_EVENTTMsg fsrEvent = new FSR_EVENTTMsg();
        setValue(fsrEvent.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(fsrEvent.fsrEventPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_EVENT_SQ));
        // add start 2016/02/08 CSA Defect#2863
        String svcDisptEventCd = SVC_DISPT_EVENT.CLOSE;
        if (frceUpdFlg) {
            if (MODE_CHARGABLE.equals(pMsg.xxModeCd.getValue())) {
                svcDisptEventCd = SVC_DISPT_EVENT.FORCE_CLOSE;
            }
        }
        setValue(fsrEvent.svcDisptEventCd, svcDisptEventCd);
        // add end 2016/02/08 CSA Defect#2863
        setValue(fsrEvent.techCd, fsrVisit.techCd);
        setValue(fsrEvent.svcTaskNum, fsrVisit.svcTaskNum);
        setValue(fsrEvent.fsrNum, pMsg.fsrNum);
        setValue(fsrEvent.fsrVisitNum, fsrVisit.fsrVisitNum);
        setValue(fsrEvent.fsrEventExeUsrId, pMsg.userId);
        setValue(fsrEvent.fsrEventExeTs, sysTs);
        // Mod Start 2019/01/10 K.Fujimoto QC#26861
        // mod start 2016/02/08 CSA Defect#2863, 2016/04/01 CSA Defect#6020, 2016/07/01 CSA Defect#11185
        if (!this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
            NSXC001001GetMblIntfcInfoBean mblIntfcInfoBean = new NSXC001001GetMblIntfcInfoBean();
            mblIntfcInfoBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
            mblIntfcInfoBean.setMblIntfcFlg(pMsg.mblIntfcFlg.getValue());
            mblIntfcInfoBean.setSvcDisptEventCd(svcDisptEventCd);
            mblIntfcInfoBean.setSvcTaskStsCd(getFsrVisitStsCd(fsrVisit));
            mblIntfcInfoBean.setTechCd(fsrVisit.techCd.getValue());
            NSXC001001GetMblIntfcInfo.getMblIntfcInfo(mblIntfcInfoBean);
            String mblIntfcProcCd = getMblIntfcProcCd(pMsg.glblCmpyCd.getValue(), fsrVisit.svcTaskNum.getValue(), mblIntfcInfoBean.getMblIntfcProcCd());
            setValue(fsrEvent.mblIntfcProcCd, mblIntfcProcCd);
            setValue(fsrEvent.mblIntfcFlg, mblIntfcInfoBean.getMblIntfcFlg());
        } else {
            setValue(fsrEvent.mblIntfcProcCd, MBL_INTFC_PROC.NO_NEED);
            setValue(fsrEvent.mblIntfcFlg, ZYPConstant.FLG_OFF_N);
        }
        // mod end 2016/02/08 CSA Defect#2863, 2016/04/01 CSA Defect#6020, 2016/07/01 CSA Defect#11185
        // Mod End   2019/01/10 K.Fujimoto QC#26861

        S21ApiTBLAccessor.create(fsrEvent);
        if (!checkRtnCodeForUpdate(pMsg, fsrEvent)) {
            return false;
        }
        return true;
    }

    private boolean isAllComplete(SVC_TASKTMsgArray svcTaskList) {
        boolean isAllComplete = true;
        for (int i = 0; i < svcTaskList.getValidCount(); i++) {
            String svcTaskSts = svcTaskList.no(i).svcTaskStsCd.getValue();
            if (SVC_TASK_STS.CANCELLED.equals(svcTaskSts) || SVC_TASK_STS.CLOSED.equals(svcTaskSts)) {
                continue;
            }
            if (!SVC_TASK_STS.COMPLETED.equals(svcTaskSts)) {
                isAllComplete = false;
                break;
            }
        }
        return isAllComplete;
    }

    private boolean isExistDebriefErr(SVC_TASKTMsgArray svcTaskList) {
        boolean isExistDebriefErr = false;
        for (int i = 0; i < svcTaskList.getValidCount(); i++) {
            String svcTaskSts = svcTaskList.no(i).svcTaskStsCd.getValue();
            if (SVC_TASK_STS.DEBRIEF_ERROR.equals(svcTaskSts)) {
                isExistDebriefErr = true;
                break;
            }
        }
        return isExistDebriefErr;
    }

    // START 2019/08/19 [QC#52380,ADD]
    private boolean isIstlComplete(String glblCmpyCd, String fsrNum, SVC_TASKTMsgArray svcTaskList, boolean isAllComplete) {
        Map<String, Boolean> instlCompleteMap = new HashMap<String, Boolean>();
        boolean isIstlComplete = false;
        if (!isAllComplete) {
            return isIstlComplete;
        }
        for (int i = 0; i < svcTaskList.getValidCount(); i++) {
            String svcTaskNum = svcTaskList.no(i).svcTaskNum.getValue();
            instlCompleteMap.put(svcTaskNum, true);

            FSR_ISTL_CHK_LISTTMsgArray istlChkListTmsgArray = getIstlChkListBySvcTaskNum(glblCmpyCd, fsrNum, svcTaskNum);
            if (istlChkListTmsgArray.getValidCount() == 0) {
                instlCompleteMap.put(svcTaskNum, false);
            } else {
                for(int k = 0; k < istlChkListTmsgArray.getValidCount(); k++){
                    if (ZYPConstant.FLG_OFF_N.equals(istlChkListTmsgArray.no(k).istlCpltFlg.getValue())) {
                        instlCompleteMap.put(svcTaskNum, false);
                    }
                }
            }
        }
        if (instlCompleteMap.containsValue(true)) {
            isIstlComplete = true;
        }
        return isIstlComplete;
    }
    // END 2019/08/19 [QC#52380,ADD]

    private void summaryFsrChrgDtl(FSR_CHRG_DTLTMsg fsrChrgDtl, EZDTBigDecimalItem dealAmt, EZDTBigDecimalItem funcAmt, EZDTBigDecimalItem dealDiscAmt, EZDTBigDecimalItem funcDiscAmt) {
        setValue(dealAmt, addAmt(dealAmt, fsrChrgDtl.svcChrgDealAmt));
        setValue(funcAmt, addAmt(funcAmt, fsrChrgDtl.svcChrgFuncAmt));
        setValue(dealDiscAmt, addAmt(dealDiscAmt, fsrChrgDtl.svcChrgDealDiscAmt));
        setValue(funcDiscAmt, addAmt(funcDiscAmt, fsrChrgDtl.svcChrgFuncDiscAmt));
    }

    private FSR_CHRG_DTLTMsg setFsrChrgDtlValue(NSZC005001PMsg pMsg, NSZC005001_xxChargesListPMsg chrg, String tocCd, BigDecimal actlExchRate) {
        FSR_CHRG_DTLTMsg fsrChrgDtl = null;

        if (hasValue(chrg.fsrChrgDtlPk)) {
            fsrChrgDtl = getFsrChrgDtlFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), chrg.fsrChrgDtlPk.getValue());
        } else {
            fsrChrgDtl = new FSR_CHRG_DTLTMsg();
            setValue(fsrChrgDtl.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(fsrChrgDtl.fsrChrgDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_CHRG_DTL_SQ));
        }

        // add start 2016/09/14 CSA Defect#13987
        String svcTaskNum = chrg.svcTaskNum.getValue();
        BigDecimal apiSvcChrgDealAmt = null;
        if (hasValue(chrg.fsrChrgDtlPk)) {
            NSZC061001PMsg svcPrcApiPMsg = this.completionBean.getSvcPrcApiPMsg();
            String svcInvChrgTpCd = chrg.svcInvChrgTpCd.getValue();

            if (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(svcInvChrgTpCd)) {
                NSZC061001_xxSvcTaskChrgListPMsg chrgPMsg = null;
                for (int i = 0; i < svcPrcApiPMsg.xxSvcTaskChrgList.getValidCount(); i++) {
                    NSZC061001_xxSvcTaskChrgListPMsg apiPMsg = svcPrcApiPMsg.xxSvcTaskChrgList.no(i);
                    if (svcTaskNum.equals(apiPMsg.svcTaskNum.getValue())) {
                        chrgPMsg = apiPMsg;
                    }
                }
                if (chrgPMsg != null) {
                    apiSvcChrgDealAmt = chrgPMsg.svcLborDealAmt.getValue();
                }
            } else if (SVC_INV_CHRG_TP.TRAVEL_CHARGE.equals(svcInvChrgTpCd)) {
                NSZC061001_xxSvcTaskChrgListPMsg chrgPMsg = null;
                for (int i = 0; i < svcPrcApiPMsg.xxSvcTaskChrgList.getValidCount(); i++) {
                    NSZC061001_xxSvcTaskChrgListPMsg apiPMsg = svcPrcApiPMsg.xxSvcTaskChrgList.no(i);
                    if (svcTaskNum.equals(apiPMsg.svcTaskNum.getValue())) {
                        chrgPMsg = apiPMsg;
                    }
                }
                if (chrgPMsg != null) {
                    apiSvcChrgDealAmt = chrgPMsg.svcTrvlDealAmt.getValue();
                }
            }
        }
        // add end 2016/09/14 CSA Defect#13987

        setValue(fsrChrgDtl.fsrNum, chrg.fsrNum);
        setValue(fsrChrgDtl.fsrVisitNum, chrg.fsrVisitNum);
        setValue(fsrChrgDtl.svcTaskNum, chrg.svcTaskNum);
        setValue(fsrChrgDtl.svcChrgTrxTpCd, chrg.svcChrgTrxTpCd);
        setValue(fsrChrgDtl.mdseCd, chrg.mdseCd);
        setValue(fsrChrgDtl.svcChrgQty, chrg.svcChrgQty);
        setValue(fsrChrgDtl.uomCd, chrg.uomCd);
        // mod start 2016/09/20 CSA Defect#13987
        if (hasValue(chrg.svcChrgUnitAot)) {
            setValue(fsrChrgDtl.svcChrgUnitAot, chrg.svcChrgUnitAot);
        }
        // mod end 2016/09/20 CSA Defect#13987
        setValue(fsrChrgDtl.svcChrgUnitAmt, chrg.svcChrgUnitAmt);
        // mod start 2016/05/19 CSA Defect#8457
        // mod start 2016/09/14 CSA Defect#13987
//        BigDecimal svcChrgDealAmt = getSvcChrgDealAmt(chrg);
        BigDecimal svcChrgDealAmt = null;
        if (hasValue(apiSvcChrgDealAmt)) {
            svcChrgDealAmt = apiSvcChrgDealAmt;
        } else {
            svcChrgDealAmt = getSvcChrgDealAmt(chrg);
        }
        // mod end 2016/09/14 CSA Defect#13987
        setValue(fsrChrgDtl.svcChrgDealAmt, svcChrgDealAmt);
        BigDecimal svcChrgFuncAmt = calcFuncAmt(svcChrgDealAmt, actlExchRate);
        setValue(fsrChrgDtl.svcChrgFuncAmt, svcChrgFuncAmt);
        // Mod Start 2017/10/27 QC#22116
        if (isOverridePrice(chrg)) {
            setValue(fsrChrgDtl.svcChrgDiscRate, chrg.svcChrgDiscRate);
            BigDecimal svcChrgDiscAmt = calcDiscAmt(svcChrgDealAmt, chrg.svcChrgDiscRate.getValue());
            // mod start 2016/05/19 CSA Defect#8457
            setValue(fsrChrgDtl.svcChrgDealDiscAmt, svcChrgDiscAmt);
            BigDecimal svcChrgFuncDiscAmt = calcFuncAmt(svcChrgDiscAmt, actlExchRate);
            setValue(fsrChrgDtl.svcChrgFuncDiscAmt, svcChrgFuncDiscAmt);
        }
        // Mod End 2017/10/27 QC#22116
        setValue(fsrChrgDtl.svcChrgFlg, chrg.svcChrgFlg);
        setValue(fsrChrgDtl.ovrdSvcChrgUnitAmt, chrg.ovrdSvcChrgUnitAmt);
        setValue(fsrChrgDtl.ovrdChngRsnCd, chrg.ovrdChngRsnCd);
        setValue(fsrChrgDtl.ovrdChngUsrId, chrg.ovrdChngUsrId);
        // mod start 2016/09/20 CSA Defect#13987
        if (hasValue(chrg.prcCatgCd)) {
            setValue(fsrChrgDtl.prcCatgCd, chrg.prcCatgCd);
        }
        // mod end 2016/09/20 CSA Defect#13987
        setValue(fsrChrgDtl.svcInvChrgTpCd, chrg.svcInvChrgTpCd);
        setValue(fsrChrgDtl.slsRepTocCd, tocCd);
        return fsrChrgDtl;
    }

    // add 2016/05/19 CSA Defect#8457
    private BigDecimal getSvcChrgDealAmt(NSZC005001_xxChargesListPMsg chrg) {
        BigDecimal unitAmt = chrg.svcChrgUnitAmt.getValue();
        if (hasValue(chrg.ovrdSvcChrgUnitAmt)) {
            unitAmt = chrg.ovrdSvcChrgUnitAmt.getValue();
        }
        // mod start 2016/05/24 CSA Defect#8636
        BigDecimal chrgQty = chrg.svcChrgQty.getValue();
        if (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(chrg.svcInvChrgTpCd.getValue()) || SVC_INV_CHRG_TP.TRAVEL_CHARGE.equals(chrg.svcInvChrgTpCd.getValue())) {
            chrgQty = chrgQty.divide(new BigDecimal("60"), 2, BigDecimal.ROUND_HALF_UP);
        }
        unitAmt = unitAmt.multiply(chrgQty);
        return unitAmt.setScale(this.completionBean.getAftDeclPntDigitNum().intValue(), BigDecimal.ROUND_HALF_UP);
        // mod end 2016/05/24 CSA Defect#8636
    }

    private boolean createOneTimeInvoice(NSZC005001PMsg pMsg) {
        // Add Start 2019/01/10 K.Fujimoto QC#26861
        if (this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
            return true;
        }
        // Add End   2019/01/10 K.Fujimoto QC#26861
        NSZC006001PMsg invPMsg = new NSZC006001PMsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String fsrNum = pMsg.fsrNum.getValue();

        FSR_CHRGTMsg fsrChrg = getFsrChrgFindByKeyForUpdate(glblCmpyCd, fsrNum);
        if (isZeroChrg(fsrChrg)) {
            return true;
        }

        setOneTimeInvHdrParam(pMsg, invPMsg);
        setOneTimeInvDtlParam(pMsg, invPMsg);
        new NSZC006001().execute(invPMsg, this.onbatchType);
        if (!checkErrMsg(pMsg, invPMsg)) {
            return false;
        }

        // update inv#
        String svcInvNum = invPMsg.svcInvNum.getValue();

        setValue(fsrChrg.svcInvNum, svcInvNum);
        setValue(fsrChrg.invDt, pMsg.slsDt);
        S21ApiTBLAccessor.update(fsrChrg);
        if (!checkRtnCodeForUpdate(pMsg, fsrChrg)) {
            return false;
        }

        for (int i = 0; i < invPMsg.xxInvLineList.getValidCount(); i++) {
            // mod start 2016/05/19 CSA Defect#8457
            if (hasValue(invPMsg.xxInvLineList.no(i).svcTaskNum)) {

                FSR_VISITTMsgArray fsrVisitList = getFsrVisitForUpdate(glblCmpyCd, fsrNum, invPMsg.xxInvLineList.no(i).svcTaskNum.getValue());
                for (int j = 0; j < fsrVisitList.getValidCount(); j++) {
                    FSR_VISITTMsg fsrVisit = fsrVisitList.no(j);
                    setValue(fsrVisit.svcInvNum, svcInvNum);
                    setValue(fsrVisit.invDt, pMsg.slsDt);
                    S21ApiTBLAccessor.update(fsrVisit);
                    if (!checkRtnCodeForUpdate(pMsg, fsrVisit)) {
                        return false;
                    }
                }
            }
            // mod end 2016/05/19 CSA Defect#8457
        }

        return true;
    }

    private boolean isZeroChrg(FSR_CHRGTMsg fsrChrg) {
        if (fsrChrg == null) {
            return true;
        }
        BigDecimal svcLborDealDiscAmt = BigDecimal.ZERO;
        BigDecimal svcTrvlDealDiscAmt = BigDecimal.ZERO;
        BigDecimal svcPrtDealDiscAmt = BigDecimal.ZERO;
        BigDecimal svcExpDealDiscAmt = BigDecimal.ZERO;
        if (hasValue(fsrChrg.svcLborDealDiscAmt)) {
            svcLborDealDiscAmt = fsrChrg.svcLborDealDiscAmt.getValue();
        }
        if (hasValue(fsrChrg.svcTrvlDealDiscAmt)) {
            svcTrvlDealDiscAmt = fsrChrg.svcTrvlDealDiscAmt.getValue();
        }
        if (hasValue(fsrChrg.svcPrtDealDiscAmt)) {
            svcPrtDealDiscAmt = fsrChrg.svcPrtDealDiscAmt.getValue();
        }
        if (hasValue(fsrChrg.svcExpDealDiscAmt)) {
            svcExpDealDiscAmt = fsrChrg.svcExpDealDiscAmt.getValue();
        }
        if (hasValue(fsrChrg.svcLborDealAmt) && BigDecimal.ZERO.compareTo(fsrChrg.svcLborDealAmt.getValue().subtract(svcLborDealDiscAmt)) < 0) {
            return false;
        }
        if (hasValue(fsrChrg.svcTrvlDealAmt) && BigDecimal.ZERO.compareTo(fsrChrg.svcTrvlDealAmt.getValue().subtract(svcTrvlDealDiscAmt)) < 0) {
            return false;
        }
        if (hasValue(fsrChrg.svcPrtDealAmt) && BigDecimal.ZERO.compareTo(fsrChrg.svcPrtDealAmt.getValue().subtract(svcPrtDealDiscAmt)) < 0) {
            return false;
        }
        if (hasValue(fsrChrg.svcExpDealAmt) && BigDecimal.ZERO.compareTo(fsrChrg.svcExpDealAmt.getValue().subtract(svcExpDealDiscAmt)) < 0) {
            return false;
        }
        return true;
    }

    private boolean isZeroChrg(Map<String, Object> invDtl) {
        BigDecimal svcChrgDealAmt = (BigDecimal) invDtl.get("SVC_CHRG_DEAL_AMT");

        // START 2017/12/25 U.Kim [QC#22524, MOD]
        // if (hasValue(svcChrgDealAmt) && BigDecimal.ZERO.compareTo(svcChrgDealAmt) < 0) {
        //     return false;
        // }
        // return true;

        if (!ZYPCommonFunc.hasValue(svcChrgDealAmt)) {
            return true;
        }
        BigDecimal svcChrgDealDiscAmt = (BigDecimal) invDtl.get("SVC_CHRG_DEAL_DISC_AMT");
        if (!ZYPCommonFunc.hasValue(svcChrgDealDiscAmt)) {
            svcChrgDealDiscAmt = BigDecimal.ZERO;
        }

        if (BigDecimal.ZERO.compareTo(svcChrgDealAmt.subtract(svcChrgDealDiscAmt)) < 0) {
            return false;
        } else {
            return true;
        }
        // END 2017/12/25 U.Kim [QC#22524, MOD]
    }

    private void setOneTimeInvHdrParam(NSZC005001PMsg pMsg, NSZC006001PMsg invPMsg) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String fsrNum = pMsg.fsrNum.getValue();

        //get invoice header information
        Map<String, Object> invHdrInfo = getInvHeaderInfo(pMsg, fsrNum);
        String techCd = getMinVisitTech(glblCmpyCd, fsrNum);
        Map<String, String> taskPoInfo = getMinPoTask(glblCmpyCd, fsrNum);
        // START 2018/09/10 K.Kojima [QC#26149,ADD]
        Map<String, String> tempPoInfo = getPoFromTE(glblCmpyCd, fsrNum);
        // END 2018/09/10 K.Kojima [QC#26149,ADD]

        setValue(invPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(invPMsg.xxModeCd, "C");
        setValue(invPMsg.fsrNum, fsrNum);
        setValue(invPMsg.fsrVisitNum, pMsg.fsrVisitNum);
        setValue(invPMsg.billToCustCd, (String) invHdrInfo.get("BILL_TO_CUST_CD"));
        setValue(invPMsg.sellToCustCd, (String) invHdrInfo.get("SELL_TO_CUST_CD"));
        setValue(invPMsg.shipToCustCd, (String) invHdrInfo.get("SHIP_TO_CUST_CD"));
        setValue(invPMsg.svcMachMstrPk, (BigDecimal) invHdrInfo.get("SVC_MACH_MSTR_PK"));
        setValue(invPMsg.serNum, (String) invHdrInfo.get("SER_NUM"));
        setValue(invPMsg.mdseCd, (String) invHdrInfo.get("MDSE_CD"));
        setValue(invPMsg.mdlId, (BigDecimal) invHdrInfo.get("MDL_ID"));
        setValue(invPMsg.mdlNm, (String) invHdrInfo.get("T_MDL_NM"));
        setValue(invPMsg.dsContrPk, (BigDecimal) invHdrInfo.get("DS_CONTR_PK"));
        setValue(invPMsg.dsContrNum, (String) invHdrInfo.get("DS_CONTR_NUM"));
        setValue(invPMsg.dealCcyCd, pMsg.invCcyCd);
        setValue(invPMsg.pmtTermCashDiscCd, (String) invHdrInfo.get("PMT_TERM_CASH_DISC_CD"));
        setValue(invPMsg.techCd, techCd);
        // mod start 2016/02/17 CSA Defect#4134
        // START 2018/09/10 K.Kojima [QC#26149,MOD]
        // if (taskPoInfo != null) {
        //     if (checkPoRequired(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), (String) invHdrInfo.get("SELL_TO_CUST_CD"), (String) invHdrInfo.get("BILL_TO_CUST_CD"))) {
        //         setValue(invPMsg.custPoNum, (String) taskPoInfo.get("CUST_PO_NUM"));
        //         setValue(invPMsg.custPoDt, (String) taskPoInfo.get("CUST_PO_DT"));
        //     }
        // }
        if (tempPoInfo != null) {
            setValue(invPMsg.custPoNum, (String) tempPoInfo.get("CUST_PO_NUM"));
        } else if (taskPoInfo != null) {
            setValue(invPMsg.custPoNum, (String) taskPoInfo.get("CUST_PO_NUM"));
            setValue(invPMsg.custPoDt, (String) taskPoInfo.get("CUST_PO_DT"));
        }
        // END 2018/09/10 K.Kojima [QC#26149,MOD]
        // mod end 2016/02/17 CSA Defect#4134
    }

    private void setOneTimeInvDtlParam(NSZC005001PMsg pMsg, NSZC006001PMsg invPMsg) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String fsrNum = pMsg.fsrNum.getValue();

        String zeroLineCragFlag = ZYPCodeDataUtil.getVarCharConstValue(SVC_INV_ZERO_LINE_CRAT_FLG, glblCmpyCd);
        List<Map<String, Object>> invDtlInfo = getInvDtlInfo(glblCmpyCd, fsrNum);

        int invLineIdx = 0;
        for (Map<String, Object> invDtl : invDtlInfo) {
            if (ZYPConstant.FLG_OFF_N.equals(zeroLineCragFlag) && isZeroChrg(invDtl)) {
                continue;
            }

            NSZC006001_xxInvLineListPMsg invLinePMsg = invPMsg.xxInvLineList.no(invLineIdx);
            setValue(invLinePMsg.svcInvChrgTpCd, (String) invDtl.get("SVC_INV_CHRG_TP_CD"));
            setValue(invLinePMsg.mdseCd, (String) invDtl.get("MDSE_CD"));
            // mod start 2016/05/24 CSA Defect#8636
            if (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(invLinePMsg.svcInvChrgTpCd.getValue()) || SVC_INV_CHRG_TP.TRAVEL_CHARGE.equals(invLinePMsg.svcInvChrgTpCd.getValue())) {
                setValue(invLinePMsg.svcInvQty, BigDecimal.ONE);
            } else {
                setValue(invLinePMsg.svcInvQty, (BigDecimal) invDtl.get("SVC_CHRG_QTY"));
            }
            // mod end 2016/05/24 CSA Defect#8636
            setValue(invLinePMsg.svcInvUnitHrsAot, (BigDecimal) invDtl.get("SVC_CHRG_UNIT_AOT"));
            setValue(invLinePMsg.dealUnitPrcAmt, (BigDecimal) invDtl.get("SVC_CHRG_UNIT_AMT"));
            setValue(invLinePMsg.svcLborDealAmt, (BigDecimal) invDtl.get("SVC_CHRG_DEAL_AMT"));
            setValue(invLinePMsg.svcLborFuncAmt, (BigDecimal) invDtl.get("SVC_CHRG_FUNC_AMT"));
            setValue(invLinePMsg.invLineDealSlsAmt, (BigDecimal) invDtl.get("SVC_CHRG_DEAL_AMT"));
            setValue(invLinePMsg.shipToCustCd, invPMsg.shipToCustCd);
            setValue(invLinePMsg.invLineDiscRate, (BigDecimal) invDtl.get("SVC_CHRG_DISC_RATE"));
            setValue(invLinePMsg.invLineDealDiscAmt, (BigDecimal) invDtl.get("SVC_CHRG_DEAL_DISC_AMT"));
            setValue(invLinePMsg.slsRepTocCd, (String) invDtl.get("SLS_REP_TOC_CD"));
            setValue(invLinePMsg.svcTaskNum, (String) invDtl.get("SVC_TASK_NUM"));
            setValue(invLinePMsg.svcTaskCpltDt, (String) invDtl.get("SVC_TASK_CPLT_DT"));
            setValue(invLinePMsg.svcTaskCpltTm, (String) invDtl.get("SVC_TASK_CPLT_TM"));
            setValue(invLinePMsg.uomCd, (String) invDtl.get("UOM_CD"));

            if (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(invLinePMsg.svcInvChrgTpCd.getValue())) {
                // mod start 2016/05/24 CSA Defect#8636
                // setValue(invLinePMsg.svcInvMnAot, (BigDecimal) invDtl.get("SVC_LBOR_TM_NUM"));
                setValue(invLinePMsg.svcInvMnAot, (BigDecimal) invDtl.get("SVC_CHRG_QTY"));
                // mod start 2016/05/24 CSA Defect#8636
                setValue(invLinePMsg.dealDiscUnitPrcAmt, (BigDecimal) invDtl.get("SVC_CHRG_DEAL_DISC_AMT"));
                setValue(invLinePMsg.svcLborUnitAmt, (BigDecimal) invDtl.get("SVC_CHRG_UNIT_AMT"));
            }
            if (SVC_INV_CHRG_TP.TRAVEL_CHARGE.equals(invLinePMsg.svcInvChrgTpCd.getValue())) {
                // mod start 2016/05/24 CSA Defect#8636
                // setValue(invLinePMsg.svcInvMnAot, (BigDecimal) invDtl.get("SVC_TRVL_TM_NUM"));
                setValue(invLinePMsg.svcInvMnAot, (BigDecimal) invDtl.get("SVC_CHRG_QTY"));
                // mod start 2016/05/24 CSA Defect#8636
                setValue(invLinePMsg.dealDiscUnitPrcAmt, (BigDecimal) invDtl.get("SVC_CHRG_DEAL_DISC_AMT"));
                setValue(invLinePMsg.svcTrvlUnitAmt, (BigDecimal) invDtl.get("SVC_CHRG_UNIT_AMT"));
            }
            if (SVC_INV_CHRG_TP.PARTS_CHARGE.equals(invLinePMsg.svcInvChrgTpCd.getValue())) {
                if (hasValue(invLinePMsg.invLineDealDiscAmt) && hasValue(invLinePMsg.svcInvQty)) {
                    BigDecimal dealDiscUnitPrcAmt = invLinePMsg.invLineDealDiscAmt.getValue().divide(invLinePMsg.svcInvQty.getValue());
                    setValue(invLinePMsg.dealDiscUnitPrcAmt, dealDiscUnitPrcAmt);
                }
            }
            if (SVC_INV_CHRG_TP.EXPENSE_CHARGE.equals(invLinePMsg.svcInvChrgTpCd.getValue())) {
                if (hasValue(invLinePMsg.invLineDealDiscAmt) && hasValue(invLinePMsg.svcInvQty)) {
                    BigDecimal dealDiscUnitPrcAmt = invLinePMsg.invLineDealDiscAmt.getValue().divide(invLinePMsg.svcInvQty.getValue());
                    setValue(invLinePMsg.dealDiscUnitPrcAmt, dealDiscUnitPrcAmt);
                }
            }
            invLineIdx++;
        }
        invPMsg.xxInvLineList.setValidCount(invLineIdx);
    }

    private boolean deleteFsrChrgDtl(NSZC005001PMsg pMsg) {

        List<BigDecimal> paramChrgList = new ArrayList<BigDecimal>();
        for (int i = 0; i < pMsg.xxChargesList.getValidCount(); i++) {
            NSZC005001_xxChargesListPMsg chrg = pMsg.xxChargesList.no(i);
            if (hasValue(chrg.fsrChrgDtlPk)) {
                paramChrgList.add(chrg.fsrChrgDtlPk.getValue());
            }
        }

        if (paramChrgList.isEmpty()) {
            return true;
        }

        List<Map<String, Object>> deletePkList = getDeleteTargetFsrChrgDtl(pMsg, paramChrgList);
        BigDecimal fsrChrgDtlPk;
        for (Map<String, Object> fsrChrgDtlMap : deletePkList) {
            fsrChrgDtlPk = (BigDecimal) fsrChrgDtlMap.get("FSR_CHRG_DTL_PK");
            FSR_CHRG_DTLTMsg tMsg = getFsrChrgDtlFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), fsrChrgDtlPk);
            if (tMsg != null) {
                S21ApiTBLAccessor.remove(tMsg);
                if (!checkRtnCodeForUpdate(pMsg, tMsg)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean deleteFsrExp(NSZC005001PMsg pMsg) {
        List<BigDecimal> fsrExpPkList = getFsrExpPkListForDelete(pMsg);

        for (BigDecimal fsrExpPk : fsrExpPkList) {
            FSR_EXPTMsg tMsg = getFsrExpFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), fsrExpPk);
            if (tMsg != null) {
                S21ApiTBLAccessor.remove(tMsg);
                if (!checkRtnCodeForUpdate(pMsg, tMsg)) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean deleteFsrInstallCheckList(NSZC005001PMsg pMsg) {
        List<BigDecimal> fsrIstlChkPkList = getFsrIstlChkListForDelete(pMsg);

        for (BigDecimal fsrIstlChkPk : fsrIstlChkPkList) {
            FSR_ISTL_CHK_LISTTMsg tMsg = getFsrIstlChkFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), fsrIstlChkPk);
            if (tMsg != null) {
                S21ApiTBLAccessor.remove(tMsg);
                if (!checkRtnCodeForUpdate(pMsg, tMsg)) {
                    return false;
                }
            }
        }
        return true;
    }
    // START 2023/04/12 K.Kitachi [QC#61311, MOD]
//    private boolean deleteFsrUsg(NSZC005001PMsg pMsg, String svcTaskNum) {
    private boolean deleteFsrUsg(NSZC005001PMsg pMsg, BigDecimal fsrUsgPk) {
    // END 2023/04/12 K.Kitachi [QC#61311, MOD]
        // START 2023/04/12 K.Kitachi [QC#61311, DEL]
//        List<BigDecimal> fsrUsgPkList = getFsrUsgPkListForDelete(pMsg, svcTaskNum);
//
//        for (BigDecimal fsrUsgPk : fsrUsgPkList) {
        // END 2023/04/12 K.Kitachi [QC#61311, DEL]
            FSR_USGTMsg tMsg = getFsrUsgtFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), fsrUsgPk);
            if (tMsg != null) {
                S21ApiTBLAccessor.remove(tMsg);
                if (!checkRtnCodeForUpdate(pMsg, tMsg)) {
                    return false;
                }
            }
            // add start 2016/04/20 CSA Defect#4469
            FSR_USG_SRVYTMsgArray tMsgArray = getFsrUsgSrvyListForUpdate(pMsg.glblCmpyCd.getValue(), fsrUsgPk);
            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                S21ApiTBLAccessor.remove(tMsgArray.no(i));
                if (!checkRtnCodeForUpdate(pMsg, tMsgArray.no(i))) {
                    return false;
                }
            }
            // add start 2016/04/20 CSA Defect#4469
        // START 2023/04/12 K.Kitachi [QC#61311, DEL]
//        }
        // END 2023/04/12 K.Kitachi [QC#61311, DEL]
        return true;
    }

    private boolean deleteFsrVisitTask(NSZC005001PMsg pMsg) {
        FSR_VISIT_TASKTMsg tMsg = getFsrVisiTaskFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), pMsg.fsrVisitNum.getValue(), this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue());
        if (tMsg != null) {
            S21ApiTBLAccessor.remove(tMsg);
            if (!checkRtnCodeForUpdate(pMsg, tMsg)) {
                return false;
            }
        }
        return true;
    }

    private boolean deleteFsrVisitTmEvent(NSZC005001PMsg pMsg) {
        List<BigDecimal> fsrFsrVisitTmEventPkList = getFsrVisitTmEventPkListForDelete(pMsg);

        for (BigDecimal fsrFsrVisitTmEventPk : fsrFsrVisitTmEventPkList) {
            FSR_VISIT_TM_EVENTTMsg tMsg = getFsrVisiTmEventFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), fsrFsrVisitTmEventPk);
            if (tMsg != null) {
                S21ApiTBLAccessor.remove(tMsg);
                if (!checkRtnCodeForUpdate(pMsg, tMsg)) {
                    return false;
                }
            }
        }
        return true;
    }

    // del start 2019/08/09 QC#52105
//    private boolean deleteSvcPhysMtrRead(NSZC005001PMsg pMsg, List<Map<String, Object>> svcPhysMtrReadList) {
//        for (int i = 0; i < svcPhysMtrReadList.size(); i++) {
//            if (i == 0) {
//                continue;
//            }
//            SVC_PHYS_MTR_READTMsg tMsg = getSvcPhysMtrReadFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), (BigDecimal) svcPhysMtrReadList.get(i).get("SVC_PHYS_MTR_READ_PK"));
//            if (tMsg != null) {
//                setValue(tMsg.vldMtrFlg, ZYPConstant.FLG_OFF_N);
//                S21ApiTBLAccessor.update(tMsg);
//                if (!checkRtnCodeForUpdate(pMsg, tMsg)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
    // del end 2019/08/09 QC#52105

    // add start 2015/12/16 CSA Defect#978
    private boolean deleteFsrChrg(NSZC005001PMsg pMsg) {

        //delete FSR_CHRG_DTL
        FSR_CHRG_DTLTMsgArray fsrChrgDtlList = getFsrChrgDtlListByFsrNumForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        for (int i = 0; i < fsrChrgDtlList.getValidCount(); i++) {
            FSR_CHRG_DTLTMsg fsrChrgDtl = fsrChrgDtlList.no(i);
            S21ApiTBLAccessor.remove(fsrChrgDtl);
            if (!checkRtnCodeForUpdate(pMsg, fsrChrgDtl)) {
                return false;
            }
        }

        //delete FSR_CHRG
        FSR_CHRGTMsg fsrChrgTMsg = getFsrChrgFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (fsrChrgTMsg != null) {
            S21ApiTBLAccessor.remove(fsrChrgTMsg);
            if (!checkRtnCodeForUpdate(pMsg, fsrChrgTMsg)) {
                return false;
            }
        }

        return true;
    }
    // add end 2015/12/16 CSA Defect#978

    // ************************************
    // Set API parameters
    // ************************************

    // START 2017/10/13 K.Kim [QC#17430, MOD]
//    private NLZC002001PMsg setInventoryUpdateApiParamsUsg(NSZC005001PMsg pMsg, NSZC005001_xxFsrUsgListPMsg dtlPMsg, String tocCd, String coaProdCd) {
    private NLZC002001PMsg setInventoryUpdateApiParamsUsg(NSZC005001PMsg pMsg, NSZC005001_xxFsrUsgListPMsg dtlPMsg, String tocCd, String coaProdCd, String billToAcctNum) {
    // END 2017/10/13 K.Kim [QC#17430, MOD]
        // Mod Start 2018/11/28 K.Fujimoto QC#28647
        NLZC002001PMsg apiPMsg = new NLZC002001PMsg();
        // setValue(apiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.trxCd, TRX.PARTS_USAGE);
        //Mod Start 2019/02/26 K.Fujimoto QC#30307
        setValue(apiPMsg.trxRsnCd, TRX_RSN.PARTS_USAGE);
        setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
        //Mod End   2019/02/26 K.Fujimoto QC#30307
        setValue(apiPMsg.mdseCd, dtlPMsg.mdseCd);
        setValue(apiPMsg.invtyLocCd, dtlPMsg.prtUsedByTechLocCd);
        setValue(apiPMsg.locStsCd, LOC_STS.DC_STOCK);
        setValue(apiPMsg.stkStsCd, STK_STS.GOOD);
        // setValue(apiPMsg.xxRqstQty, dtlPMsg.svcPrtQty);
        setValue(apiPMsg.invtyTrxDt, pMsg.slsDt);
        // setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
        setValue(apiPMsg.sysSrcCd, SYS_SRC.S21_SERVICE);
        setValue(apiPMsg.tocCd, tocCd);
        setValue(apiPMsg.coaProdCd, coaProdCd);
        setValue(apiPMsg.invtyTrxSlpNum, pMsg.fsrNum);
        setValue(apiPMsg.bolNum, dtlPMsg.svcTaskNum);
        if (dtlPMsg.svcPrtQty.getValue().compareTo(BigDecimal.ZERO) > 0){
            // withdraw from inventory
            setValue(apiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
            //Del Start 2019/02/26 K.Fujimoto QC#30307
            // setValue(apiPMsg.trxRsnCd, TRX_RSN.PARTS_USAGE);
            // setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
            //Del End   2019/02/26 K.Fujimoto QC#30307
            setValue(apiPMsg.xxRqstQty, dtlPMsg.svcPrtQty);
        } else {
            // return to inventory
            setValue(apiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
            //Del Start 2019/02/26 K.Fujimoto QC#30307
            // setValue(apiPMsg.trxRsnCd, TRX_RSN.PARTS_USAGE_RETURN);
            // setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
            //Del End   2019/02/26 K.Fujimoto QC#30307
            setValue(apiPMsg.xxRqstQty, dtlPMsg.svcPrtQty.getValue().negate());
        }
        // Mod End   2018/11/28 K.Fujimoto QC#28647
        // add start 2016/04/20 CSA Defect#4469
        if (hasValue(pMsg.fsrVisitNum)) {
            setValue(apiPMsg.proNum, pMsg.fsrVisitNum);
        } else {
            setValue(apiPMsg.proNum, this.completionBean.getFsrVisitTMsg().fsrVisitNum);
        }
        // add end 2016/04/20 CSA Defect#4469
        // START 2017/10/13 K.Kim [QC#17430, ADD]
        setValue(apiPMsg.sellToCustCd, billToAcctNum);
        // END 2017/10/13 K.Kim [QC#17430, ADD]
        return apiPMsg;
    }

    // START 2017/10/13 K.Kim [QC#17430, MOD]
//    private NLZC002001PMsg setInventoryUpdateApiParamsRtn(NSZC005001PMsg pMsg, NSZC005001_xxFsrUsgListPMsg dtlPMsg, String tocCd, String coaProdCd) {
    private NLZC002001PMsg setInventoryUpdateApiParamsRtn(NSZC005001PMsg pMsg, NSZC005001_xxFsrUsgListPMsg dtlPMsg, String tocCd, String coaProdCd, String billToAcctNum) {
    // END 2017/10/13 K.Kim [QC#17430, MOD]
        // Mod Start 2018/11/28 K.Fujimoto QC#28647
        NLZC002001PMsg apiPMsg = new NLZC002001PMsg();
        // setValue(apiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.trxCd, TRX.PARTS_USAGE);
        //Mod Start 2019/02/26 K.Fujimoto QC#30307
        setValue(apiPMsg.trxRsnCd, TRX_RSN.PARTS_USAGE_RETURN);
        setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        //Mod End   2019/02/26 K.Fujimoto QC#30307
        setValue(apiPMsg.mdseCd, dtlPMsg.mdseCd);
        setValue(apiPMsg.invtyLocCd, dtlPMsg.prtUsedByTechCd.getValue() + RTRN_INVTY_LOC_CD_SFX);
        setValue(apiPMsg.locStsCd, LOC_STS.DC_STOCK);
        setValue(apiPMsg.stkStsCd, STK_STS.GOOD);
        // setValue(apiPMsg.xxRqstQty, dtlPMsg.svcPrtQty);
        setValue(apiPMsg.invtyTrxDt, pMsg.slsDt);
        // setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        setValue(apiPMsg.sysSrcCd, SYS_SRC.S21_SERVICE);
        setValue(apiPMsg.tocCd, tocCd);
        setValue(apiPMsg.coaProdCd, coaProdCd);
        setValue(apiPMsg.invtyTrxSlpNum, pMsg.fsrNum);
        setValue(apiPMsg.bolNum, dtlPMsg.svcTaskNum);
        if (dtlPMsg.svcPrtQty.getValue().compareTo(BigDecimal.ZERO) > 0){
            // return to inventory
            setValue(apiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
            //Del Start 2019/02/26 K.Fujimoto QC#30307
            // setValue(apiPMsg.trxRsnCd, TRX_RSN.PARTS_USAGE_RETURN);
            // setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
            //Del End   2019/02/26 K.Fujimoto QC#30307
            setValue(apiPMsg.xxRqstQty, dtlPMsg.svcPrtQty);
        } else {
            // withdraw from inventory
            setValue(apiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
            //Del Start 2019/02/26 K.Fujimoto QC#30307
            // setValue(apiPMsg.trxRsnCd, TRX_RSN.PARTS_USAGE);
            // setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
            //Del End   2019/02/26 K.Fujimoto QC#30307
            setValue(apiPMsg.xxRqstQty, dtlPMsg.svcPrtQty);
            setValue(apiPMsg.xxRqstQty, dtlPMsg.svcPrtQty.getValue().negate());
        }
        // Mod End   2018/11/28 K.Fujimoto QC#28647
        // mod start 2016/04/20 CSA Defect#4469
        if (hasValue(pMsg.fsrVisitNum)) {
            setValue(apiPMsg.proNum, pMsg.fsrVisitNum);
        } else {
            setValue(apiPMsg.proNum, this.completionBean.getFsrVisitTMsg().fsrVisitNum);
        }
        // mod end 2016/04/20 CSA Defect#4469
        // START 2017/10/13 K.Kim [QC#17430, ADD]
        setValue(apiPMsg.sellToCustCd, billToAcctNum);
        // END 2017/10/13 K.Kim [QC#17430, ADD]
        return apiPMsg;
    }

    // START 2017/10/13 K.Kim [QC#17430, MOD]
//    private NLZC002001PMsg setInventoryUpdateApiParamsUsgForChrgable(NSZC005001PMsg pMsg, NSZC005001_xxChargesListPMsg partsChrg, String techCd, String tocCd, String coaProdCd) {
    private NLZC002001PMsg setInventoryUpdateApiParamsUsgForChrgable(NSZC005001PMsg pMsg, NSZC005001_xxChargesListPMsg partsChrg, String techCd, String tocCd, String coaProdCd, String billToAcctNum) {
    // END 2017/10/13 K.Kim [QC#17430, MOD]
        // Mod Start 2018/11/28 K.Fujimoto QC#28647
        NLZC002001PMsg apiPMsg = new NLZC002001PMsg();
        // setValue(apiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.trxCd, TRX.PARTS_USAGE);
        //Mod Start 2019/02/26 K.Fujimoto QC#30307
        setValue(apiPMsg.trxRsnCd, TRX_RSN.PARTS_USAGE);
        setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
        //Mod End   2019/02/26 K.Fujimoto QC#30307
        setValue(apiPMsg.mdseCd, partsChrg.mdseCd);
        setValue(apiPMsg.invtyLocCd, techCd + PART_USG_INVTY_LOC_SFX);
        setValue(apiPMsg.locStsCd, LOC_STS.DC_STOCK);
        setValue(apiPMsg.stkStsCd, STK_STS.GOOD);
        // setValue(apiPMsg.xxRqstQty, partsChrg.svcChrgQty);
        setValue(apiPMsg.invtyTrxDt, pMsg.slsDt);
        // setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
        setValue(apiPMsg.sysSrcCd, SYS_SRC.S21_SERVICE);
        setValue(apiPMsg.tocCd, tocCd);
        setValue(apiPMsg.coaProdCd, coaProdCd);
        setValue(apiPMsg.invtyTrxSlpNum, pMsg.fsrNum);
        setValue(apiPMsg.bolNum, partsChrg.svcTaskNum);
        setValue(apiPMsg.proNum, pMsg.fsrVisitNum);
        // START 2017/10/13 K.Kim [QC#17430, ADD]
        setValue(apiPMsg.sellToCustCd, billToAcctNum);
        // END 2017/10/13 K.Kim [QC#17430, ADD]
        if (partsChrg.svcChrgQty.getValue().compareTo(BigDecimal.ZERO) > 0){
            // withdraw from the inventory
            setValue(apiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
            //Del Start 2019/02/26 K.Fujimoto QC#30307
            // setValue(apiPMsg.trxRsnCd, TRX_RSN.PARTS_USAGE);
            // setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
            //Del End   2019/02/26 K.Fujimoto QC#30307
            setValue(apiPMsg.xxRqstQty, partsChrg.svcChrgQty);
        } else {
            // return to the inventory
            setValue(apiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
            //Del Start 2019/02/26 K.Fujimoto QC#30307
            // setValue(apiPMsg.trxRsnCd, TRX_RSN.PARTS_USAGE_RETURN);
            // setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
            //Del End   2019/02/26 K.Fujimoto QC#30307
            setValue(apiPMsg.xxRqstQty, partsChrg.svcChrgQty.getValue().negate());
        }
        // Mod End   2018/11/28 K.Fujimoto QC#28647
        return apiPMsg;
    }

    // START 2017/10/13 K.Kim [QC#17430, MOD]
//    private NLZC002001PMsg setInventoryUpdateApiParamsRtnForChragable(NSZC005001PMsg pMsg, NSZC005001_xxChargesListPMsg partsChrg, String techCd, String tocCd, String coaProdCd) {
    private NLZC002001PMsg setInventoryUpdateApiParamsRtnForChragable(NSZC005001PMsg pMsg, NSZC005001_xxChargesListPMsg partsChrg, String techCd, String tocCd, String coaProdCd, String billToAcctNum) {
    // END 2017/10/13 K.Kim [QC#17430, MOD]
        // Mod Start 2018/11/28 K.Fujimoto QC#28647
        NLZC002001PMsg apiPMsg = new NLZC002001PMsg();
        setValue(apiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.trxCd, TRX.PARTS_USAGE);
        setValue(apiPMsg.trxRsnCd, TRX_RSN.PARTS_USAGE_RETURN);
        setValue(apiPMsg.mdseCd, partsChrg.mdseCd);
        setValue(apiPMsg.invtyLocCd, techCd + RTRN_INVTY_LOC_CD_SFX);
        setValue(apiPMsg.locStsCd, LOC_STS.DC_STOCK);
        setValue(apiPMsg.stkStsCd, STK_STS.GOOD);
        setValue(apiPMsg.xxRqstQty, partsChrg.svcChrgQty);
        setValue(apiPMsg.invtyTrxDt, pMsg.slsDt);
        setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
        setValue(apiPMsg.sysSrcCd, SYS_SRC.S21_SERVICE);
        setValue(apiPMsg.tocCd, tocCd);
        setValue(apiPMsg.coaProdCd, coaProdCd);
        setValue(apiPMsg.invtyTrxSlpNum, pMsg.fsrNum);
        setValue(apiPMsg.bolNum, partsChrg.svcTaskNum);
        setValue(apiPMsg.proNum, pMsg.fsrVisitNum);
        // START 2017/10/13 K.Kim [QC#17430, ADD]
        setValue(apiPMsg.sellToCustCd, billToAcctNum);
        // END 2017/10/13 K.Kim [QC#17430, ADD]
        if (partsChrg.svcChrgQty.getValue().compareTo(BigDecimal.ZERO) > 0){
            // return to the inventory
            setValue(apiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
            //Del Start 2019/02/26 K.Fujimoto QC#30307
            // setValue(apiPMsg.trxRsnCd, TRX_RSN.PARTS_USAGE_RETURN);
            // setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
            //Del End   2019/02/26 K.Fujimoto QC#30307
            setValue(apiPMsg.xxRqstQty, partsChrg.svcChrgQty);
        } else {
            // withdraw from the inventory
            setValue(apiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
            //Del Start 2019/02/26 K.Fujimoto QC#30307
            // setValue(apiPMsg.trxRsnCd, TRX_RSN.PARTS_USAGE);
            // setValue(apiPMsg.xxSysTp, NLZC002001.SYS_TP_OTBD);
            //Del End   2019/02/26 K.Fujimoto QC#30307
            setValue(apiPMsg.xxRqstQty, partsChrg.svcChrgQty);
            setValue(apiPMsg.xxRqstQty, partsChrg.svcChrgQty.getValue().negate());
        }
        // Mod End   2018/11/28 K.Fujimoto QC#28647
        return apiPMsg;
    }

    private NSZC001001PMsg setSvcMachMstrApiParams(NSZC005001PMsg pMsg, String svcTaskNum, String mode, Map<String, Object> callTp, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        NSZC001001PMsg apiPMsg = new NSZC001001PMsg();

        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        setValue(apiPMsg.xxModeCd, mode);
        setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValue(apiPMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk);
        if (ProcessMode.INSTALLATION.code.equals(mode)) {
            setValue(apiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.INSTALLED);
            // add start 2016/04/19 CSA Defect#7004
            FSR_VISITTMsgArray fsrVisitTMsgArray = getFsrVisitForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), svcTaskNum);
            setValue(apiPMsg.istlDt, fsrVisitTMsgArray.no(0).fsrVisitCpltDt);
            setValue(apiPMsg.stkStsCd, svcMachMstrTMsg.stkStsCd);
            setValue(apiPMsg.svcMachMstrLocStsCd, svcMachMstrTMsg.svcMachMstrLocStsCd);
            // add end 2016/04/19 CSA Defect#7004
        }
        setValue(apiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.AT_CUSTOMER);

        // Get IWR COND
        String iwrSts = pMsg.iwrStsCd.getValue();
        String iwrCondCd = svcMachMstrTMsg.iwrCondCd.getValue();
        if (IWR_STS.SUCCESS.equals(iwrSts) && !IWR_COND.ACTIVE.equals(iwrCondCd)) {
            iwrCondCd = IWR_COND.ACTIVE;
        } else if (IWR_STS.UNSUCCESS_CUST_NOT_WANT.equals(iwrSts) && !(IWR_COND.DECLINED.equals(iwrCondCd) || IWR_COND.DISABLED.equals(iwrCondCd))) {
            iwrCondCd = IWR_COND.DECLINED;
        } else if (IWR_STS.UNSUCCESS_CUST_WANTS.equals(iwrSts) && IWR_COND.NOT_COMMUNICATING.equals(iwrCondCd)) {
            iwrCondCd = IWR_COND.NOT_COMMUNICATING;
        }
        setValue(apiPMsg.iwrCondCd, iwrCondCd);

        // Get machine master date
        String drumExchDt = svcMachMstrTMsg.drumExchDt.getValue();
        String lastSvcCallDt = svcMachMstrTMsg.lastSvcCallDt.getValue();
        String lastTechVisitDt = svcMachMstrTMsg.lastTechVisitDt.getValue();
        String lastPrvntMaintDt = svcMachMstrTMsg.lastPrvntMaintDt.getValue();
        String lastSvcCallVisitDt = svcMachMstrTMsg.lastSvcCallVisitDt.getValue();

        if (ProcessMode.SERVICE_UPDATE.code.equals(mode)) {
            if (existsDrumInTask(pMsg, svcTaskNum)) {
                drumExchDt = getMaxDate(drumExchDt, pMsg.fsrVisitCpltDt.getValue());
                setValue(apiPMsg.drumExchDt, drumExchDt);
            }

            // Check machine master date with visit completion date
            lastSvcCallDt = getMaxDate(lastSvcCallDt, pMsg.fsrVisitCpltDt.getValue());
            lastTechVisitDt = getMaxDate(lastTechVisitDt, pMsg.fsrVisitCpltDt.getValue());

            if (ZYPConstant.FLG_ON_Y.equals((String) callTp.get("SVC_PRVNT_MAINT_FLG"))) {
                lastPrvntMaintDt = getMaxDate(lastPrvntMaintDt, pMsg.fsrVisitCpltDt.getValue());
            }
            if (ZYPConstant.FLG_ON_Y.equals((String) callTp.get("SVC_CALL_FLG"))) {
                lastSvcCallVisitDt = getMaxDate(lastSvcCallVisitDt, pMsg.fsrVisitCpltDt.getValue());
            }

            setValue(apiPMsg.lastSvcCallDt, lastSvcCallDt);
            if (hasValue(svcMachMstrTMsg.totSvcVisitCnt)) {
                setValue(apiPMsg.totSvcVisitCnt, svcMachMstrTMsg.totSvcVisitCnt.getValue().add(BigDecimal.ONE));
            } else {
                setValue(apiPMsg.totSvcVisitCnt, BigDecimal.ONE);
            }
            setValue(apiPMsg.lastTechVisitDt, lastTechVisitDt);
            setValue(apiPMsg.lastPrvntMaintDt, lastPrvntMaintDt);
            setValue(apiPMsg.lastSvcCallVisitDt, lastSvcCallVisitDt);

            // add start 2016/04/19 CSA Defect#7004
            setValue(apiPMsg.billToAcctNum, svcMachMstrTMsg.billToAcctNum);
            setValue(apiPMsg.billToLocNum, svcMachMstrTMsg.billToLocNum);
            setValue(apiPMsg.curLocAcctNum, svcMachMstrTMsg.curLocAcctNum);
            setValue(apiPMsg.curLocNum, svcMachMstrTMsg.curLocNum);
            // START 2017/12/04 U.Kim [QC#19907,DEL]
            // Map<String, Object> updateCustomerInfoMap = getUpdateCustomerInfo(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
            // if (updateCustomerInfoMap != null) {
            //     if (ZYPConstant.FLG_ON_Y.equals((String) updateCustomerInfoMap.get("BILL_TO_CUST_UPD_FLG"))) {
            //         setValue(apiPMsg.billToAcctNum, (String) updateCustomerInfoMap.get("BILL_TO_ACCT_NUM"));
            //         setValue(apiPMsg.billToLocNum, (String) updateCustomerInfoMap.get("BILL_TO_UPD_CUST_CD"));
            //     }
            //     if (ZYPConstant.FLG_ON_Y.equals((String) updateCustomerInfoMap.get("SHIP_TO_CUST_UPD_FLG"))) {
            //         setValue(apiPMsg.curLocAcctNum, (String) updateCustomerInfoMap.get("CUR_LOC_ACCT_NUM"));
            //         setValue(apiPMsg.curLocNum, (String) updateCustomerInfoMap.get("SHIP_TO_UPD_CUST_CD"));
            //     }
            // }
            // END 2017/12/04 U.Kim [QC#19907,DEL]
            // add end 2016/04/19 CSA Defect#7004
        }

        return apiPMsg;
    }

    // ************************************
    // Set DB values
    // ************************************

    private boolean setFsrValue(NSZC005001PMsg pMsg, FSRTMsg fsrTMsg) {
        FSR_VISITTMsg fsrVisitAmt = getFsrVisitAmt(pMsg);
        if (fsrVisitAmt == null) {
            setErrMsg(pMsg, NSZM0182E);
            return false;
        }
        List<Map<String, Object>> billTpList = getBillTp(pMsg, null);
        if (billTpList == null || billTpList.size() == 0) {
            setErrMsg(pMsg, NSZM0755E);
            return false;
        }

        String fsrStsCd = null;
        String svcTaskStsCd = null;
        boolean existDeb = false;
        boolean existCancel = false;
        boolean existComplete = false;
        boolean existClose = false;
        boolean existOther = false;
        for (Map<String, Object> billTp : billTpList) {
            svcTaskStsCd = (String) billTp.get("SVC_TASK_STS_CD");
            if (SVC_TASK_STS.DEBRIEF_ERROR.equals(svcTaskStsCd)) {
                existDeb = true;
            } else if (SVC_TASK_STS.CANCELLED.equals(svcTaskStsCd)) {
                existCancel = true;
            } else if (SVC_TASK_STS.COMPLETED.equals(svcTaskStsCd)) {
                existComplete = true;
            } else if (SVC_TASK_STS.CLOSED.equals(svcTaskStsCd)) {
                existClose = true;
            } else {
                existOther = true;
            }
        }
        if (existDeb) {
            fsrStsCd = SVC_TASK_STS.DEBRIEF_ERROR;
        } else if (!existDeb && (existComplete || existClose) && !existOther) {
            fsrStsCd = getFsrStsCdForComp(pMsg, fsrVisitAmt, billTpList);
        } else if (!existDeb && existCancel && !existComplete && !existClose && !existOther) {
            fsrStsCd = SVC_TASK_STS.CANCELLED;
        } else {
            fsrStsCd = SVC_TASK_STS.OPEN;
        }
        // START 2017/06/16 K.Kitachi [QC#19081, MOD]
//        // mod start 2016/05/31 CSA Defect#9144
//        if (SVC_TASK_STS.PENDING_CHARGE.equals(fsrStsCd)) {
//            if (existsInstallCheckError(pMsg)) {
//                fsrStsCd = SVC_TASK_STS.COMPLETED;
//            }
//        }
//        // mod end 2016/05/31 CSA Defect#9144
        boolean isStsUpdFlg = true;
        if (SVC_TASK_STS.DEBRIEF_ERROR.equals(fsrStsCd)) {
            isStsUpdFlg = false;
            fsrStsCd = fsrTMsg.fsrStsCd.getValue();
        } else if (ZYPConstant.FLG_ON_Y.equals(this.completionBean.getDsSvcCallTpTMsg().svcIstlReqFlg.getValue())) {
            isStsUpdFlg = false;
            // START 2017/06/23 T.Tomita [QC#19206, MOD]
            if (MODE_INSTALL_CHECK.equals(pMsg.xxModeCd.getValue()) && !existsInstallCheckError(pMsg)) {
                isStsUpdFlg = true;
                if (!SVC_TASK_STS.PENDING_CHARGE.equals(fsrStsCd)) {
                    fsrStsCd = SVC_TASK_STS.COMPLETED;
                }
            } else {
                fsrStsCd = fsrTMsg.fsrStsCd.getValue();
            }
            // END 2017/06/23 T.Tomita [QC#19206, MOD]
        }
        // END 2017/06/16 K.Kitachi [QC#19081, MOD]

        // START 2017/04/07 K.Kitachi [QC#18052, MOD]
        // START 2017/06/16 K.Kitachi [QC#19081, MOD]
//        if (ZYPConstant.FLG_OFF_N.equals(this.completionBean.getDsSvcCallTpTMsg().svcIstlReqFlg.getValue())) {
        if (isStsUpdFlg) {
        // END 2017/06/16 K.Kitachi [QC#19081, MOD]
            // Mod Start 2019/01/10 K.Fujimoto QC#26861
            if (!this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
              setValue(fsrTMsg.fsrStsCd, fsrStsCd);
            }
            // setValue(fsrTMsg.fsrStsCd, fsrStsCd);
            // Mod End   2019/01/10 K.Fujimoto QC#26861
            // START 2017/06/16 K.Kitachi [QC#19081, MOD]
            if (SVC_TASK_STS.COMPLETED.equals(fsrStsCd) || SVC_TASK_STS.CLOSED.equals(fsrStsCd) || SVC_TASK_STS.PENDING_CHARGE.equals(fsrStsCd)) {
            // END 2017/06/16 K.Kitachi [QC#19081, MOD]
                setValue(fsrTMsg.fsrCpltDt, pMsg.fsrVisitCpltDt);
                setValue(fsrTMsg.fsrCpltTm, pMsg.fsrVisitCpltTm);
            }
            if (SVC_TASK_STS.CLOSED.equals(fsrStsCd) || SVC_TASK_STS.CANCELLED.equals(fsrStsCd)) {
                // START 2024/01/25 t.aizawa [QC#63504, ADD]
                if (!ZYPCommonFunc.hasValue(fsrTMsg.fsrCloDt)) {
                // END   2024/01/25 t.aizawa [QC#63504, ADD]
                    setValue(fsrTMsg.fsrCloDt, sysDt);
                    setValue(fsrTMsg.fsrCloTm, sysTm);
                // START 2024/01/25 t.aizawa [QC#63504, ADD]
                }
                // END   2024/01/25 t.aizawa [QC#63504, ADD]
            }
        }
        // END 2017/04/07 K.Kitachi [QC#18052, MOD]
        setValue(fsrTMsg.svcLborDealAmt, fsrVisitAmt.svcLborDealAmt);
        setValue(fsrTMsg.svcLborFuncAmt, fsrVisitAmt.svcLborFuncAmt);
        setValue(fsrTMsg.svcLborDealDiscAmt, fsrVisitAmt.svcLborDealDiscAmt);
        setValue(fsrTMsg.svcLborFuncDiscAmt, fsrVisitAmt.svcLborFuncDiscAmt);
        setValue(fsrTMsg.svcLborInvDealAmt, fsrVisitAmt.svcLborInvDealAmt);
        setValue(fsrTMsg.svcLborInvFuncAmt, fsrVisitAmt.svcLborInvFuncAmt);
        setValue(fsrTMsg.svcLborInvDealDiscAmt, fsrVisitAmt.svcLborInvDealDiscAmt);
        setValue(fsrTMsg.svcLborInvFuncDiscAmt, fsrVisitAmt.svcLborInvFuncDiscAmt);
        setValue(fsrTMsg.svcTrvlDealAmt, fsrVisitAmt.svcTrvlDealAmt);
        setValue(fsrTMsg.svcTrvlFuncAmt, fsrVisitAmt.svcTrvlFuncAmt);
        setValue(fsrTMsg.svcTrvlDealDiscAmt, fsrVisitAmt.svcTrvlDealDiscAmt);
        setValue(fsrTMsg.svcTrvlFuncDiscAmt, fsrVisitAmt.svcTrvlFuncDiscAmt);
        setValue(fsrTMsg.svcPrtDealAmt, fsrVisitAmt.svcPrtDealAmt);
        setValue(fsrTMsg.svcPrtFuncAmt, fsrVisitAmt.svcPrtFuncAmt);
        setValue(fsrTMsg.svcPrtDealDiscAmt, fsrVisitAmt.svcPrtDealDiscAmt);
        setValue(fsrTMsg.svcPrtFuncDiscAmt, fsrVisitAmt.svcPrtFuncDiscAmt);
        // START 2017/04/07 K.Kitachi [QC#18052, DEL]
//        if (SVC_TASK_STS.CLOSED.equals(fsrStsCd) || SVC_TASK_STS.CANCELLED.equals(fsrStsCd)) {
//            setValue(fsrTMsg.fsrCloDt, sysDt);
//            setValue(fsrTMsg.fsrCloTm, sysTm);
//        }
        // END 2017/04/07 K.Kitachi [QC#18052, DEL]
        setValue(fsrTMsg.svcExpDealAmt, fsrVisitAmt.svcExpDealAmt);
        setValue(fsrTMsg.svcExpFuncAmt, fsrVisitAmt.svcExpFuncAmt);
        setValue(fsrTMsg.svcExpDealDiscAmt, fsrVisitAmt.svcExpDealDiscAmt);
        setValue(fsrTMsg.svcExpFuncDiscAmt, fsrVisitAmt.svcExpFuncDiscAmt);
        if (SVC_TASK_STS.PENDING_CHARGE.equals(fsrStsCd) && !hasValue(fsrTMsg.fsrChrgSq)) {
            setValue(fsrTMsg.fsrChrgSq, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_CHRG_SQ));
        }
        // START 2018/05/14 M.Naito [QC#23898, ADD]
        if (hasValue(pMsg.ittNum)) {
            setValue(fsrTMsg.ittNum, pMsg.ittNum);
        }
        // END 2018/05/14 M.Naito [QC#23898, ADD]

        // add start 2015/12/16 CSA Defect#978
        this.resultFsrStsCd = fsrStsCd;
        // add end 2015/12/16 CSA Defect#978
        return true;
    }

    private boolean setFsrEventValue(NSZC005001PMsg pMsg, FSR_VISITTMsg fsrVisitTMsg, FSR_EVENTTMsg fsrEventTMsg) {
        SVC_TASKTMsg svcTaskTMsg = getSvcTaskFindByKey(pMsg.glblCmpyCd.getValue(), fsrVisitTMsg.svcTaskNum.getValue());
        if (!checkRtnCodeForSearch(pMsg, svcTaskTMsg, new SVC_TASKTMsg())) {
            return false;
        }
        String svsTaskStsCd = svcTaskTMsg.svcTaskStsCd.getValue();
        String svcDisptEventCd = null;
        if (SVC_TASK_STS.COMPLETED.equals(svsTaskStsCd)) {
            svcDisptEventCd = SVC_DISPT_EVENT.COMPLETE;
            // START 2022/04/15 [QC#59911, ADD]
            if (checkExistsCompleteFSREvent(pMsg.glblCmpyCd.getValue(), fsrVisitTMsg.svcTaskNum.getValue())) {
                return false;
            }
            // END   2022/04/15 [QC#59911, ADD]
        } else if (SVC_TASK_STS.DEBRIEF_ERROR.equals(svsTaskStsCd)) {
            svcDisptEventCd = SVC_DISPT_EVENT.DEBRIEF_ERROR;
        } else if (SVC_TASK_STS.CLOSED.equals(svsTaskStsCd)) {
            svcDisptEventCd = SVC_DISPT_EVENT.CLOSE;
        } else if (SVC_TASK_STS.CANCELLED.equals(svsTaskStsCd)) {
            svcDisptEventCd = SVC_DISPT_EVENT.CANCEL;
        }
        int cnt = getSvcInvCnt(pMsg);
        // Mod Start 2019/01/10 K.Fujimoto QC#26861
        if (cnt > 0 && !this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
        // if (cnt > 0) {
        // Mod End   2019/01/10 K.Fujimoto QC#26861
            svcDisptEventCd = SVC_DISPT_EVENT.INVOICE;
        }
        // add start 2016/02/08 CSA Defect#2863
        if (frceUpdFlg) {
            if (MODE_DEBRIEF.equals(pMsg.xxModeCd.getValue())) {
                svcDisptEventCd = SVC_DISPT_EVENT.FORCE_COMPLETE;
            }
        }
        // add end 2016/02/08 CSA Defect#2863

        setValue(fsrEventTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(fsrEventTMsg.fsrEventPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_EVENT_SQ));
        setValue(fsrEventTMsg.svcDisptEventCd, svcDisptEventCd);
        setValue(fsrEventTMsg.techCd, fsrVisitTMsg.techCd);
        setValue(fsrEventTMsg.svcTaskNum, fsrVisitTMsg.svcTaskNum);
        setValue(fsrEventTMsg.fsrNum, pMsg.fsrNum);
        setValue(fsrEventTMsg.fsrVisitNum, fsrVisitTMsg.fsrVisitNum);
        setValue(fsrEventTMsg.fsrEventExeUsrId, pMsg.userId);
        setValue(fsrEventTMsg.fsrEventExeTs, sysTs);
        // Mod Start 2019/01/10 K.Fujimoto QC#26861
        // mod start 2016/02/08 CSA Defect#2863, 2016/04/01 CSA Defect#6020, 2016/07/01 CSA Defect#11185
        if (!this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
            NSXC001001GetMblIntfcInfoBean mblIntfcInfoBean = new NSXC001001GetMblIntfcInfoBean();
            mblIntfcInfoBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
            mblIntfcInfoBean.setMblIntfcFlg(pMsg.mblIntfcFlg.getValue());
            mblIntfcInfoBean.setSvcDisptEventCd(svcDisptEventCd);
            mblIntfcInfoBean.setSvcTaskStsCd(getFsrVisitStsCd(fsrVisitTMsg));
            mblIntfcInfoBean.setTechCd(fsrVisitTMsg.techCd.getValue());
            NSXC001001GetMblIntfcInfo.getMblIntfcInfo(mblIntfcInfoBean);
            String mblIntfcProcCd = getMblIntfcProcCd(pMsg.glblCmpyCd.getValue(), fsrVisitTMsg.svcTaskNum.getValue(), mblIntfcInfoBean.getMblIntfcProcCd());
            setValue(fsrEventTMsg.mblIntfcProcCd, mblIntfcProcCd);
            setValue(fsrEventTMsg.mblIntfcFlg, mblIntfcInfoBean.getMblIntfcFlg());
        } else {
            setValue(fsrEventTMsg.mblIntfcProcCd, MBL_INTFC_PROC.NO_NEED);
            setValue(fsrEventTMsg.mblIntfcFlg, ZYPConstant.FLG_OFF_N);
        }
        // mod end 2016/02/08 CSA Defect#2863, 2016/04/01 CSA Defect#6020, 2016/07/01 CSA Defect#11185
        // Mod End   2019/01/10 K.Fujimoto QC#26861
        return true;
    }

    // mod start 2016/07/01 CSA Defect#11185
    private String getFsrVisitStsCd(FSR_VISITTMsg fsrVisitTMsg) {
        FSR_VISITTMsg tMsg = getFsrVisitFindByKey(fsrVisitTMsg.glblCmpyCd.getValue(), fsrVisitTMsg.fsrNum.getValue(), fsrVisitTMsg.fsrVisitNum.getValue());
        if (tMsg != null) {
            return tMsg.fsrVisitStsCd.getValue();
        }
        return null;
    }
    // mod start 2016/07/01 CSA Defect#11185

    private boolean setFsrExpValue(NSZC005001PMsg pMsg, NSZC005001_xxExpenseListPMsg dtlPMsg, NSZC061001_xxExpMdseListPMsg apiDtlPMsg, FSR_EXPTMsg fsrExpTMsg) {
        BigDecimal fsrExpPk = null;
        if (hasValue(dtlPMsg.fsrExpPk)) {
            fsrExpPk = dtlPMsg.fsrExpPk.getValue();
            FSR_EXPTMsg tMsg = getFsrExpFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), dtlPMsg.fsrExpPk.getValue());
            if (tMsg != null) {
                S21ApiTBLAccessor.remove(tMsg);
                if (!checkRtnCodeForUpdate(pMsg, tMsg)) {
                    return false;
                }
            }
        } else {
            fsrExpPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_EXP_SQ);
        }

        // mod start 2016/02/22 CSA Defect#2749
        BigDecimal svcExpDealAmt = dtlPMsg.svcExpDealAmt.getValue();
        // mod end 2016/02/22 CSA Defect#2749
        // add start 2015/12/21 CSA Defect#1924
        if (hasValue(pMsg.xxModeCd_AD) && !hasValue(svcExpDealAmt)) {
            svcExpDealAmt = BigDecimal.ZERO;
        }
        // add end 2015/12/21 CSA Defect#1924
        BigDecimal svcExpFuncAmt = calcFuncAmt(svcExpDealAmt, pMsg.ccyExchRate.getValue());
        // mod start 2016/02/22,2016/03/02 CSA Defect#2749,4644
        BigDecimal svcExpDiscRate = apiDtlPMsg.xxSvcExpDiscRate.getValue();
        BigDecimal svcExpDealDiscAmt = calcDiscAmt(svcExpDealAmt, svcExpDiscRate);
        // mod end 2016/02/22,2016/03/02 CSA Defect#2749,4644
        BigDecimal svcExpFuncDiscAmt = calcFuncAmt(svcExpDealDiscAmt, pMsg.ccyExchRate.getValue());

        setValue(fsrExpTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(fsrExpTMsg.fsrExpPk, fsrExpPk);
        setValue(fsrExpTMsg.fsrNum, pMsg.fsrNum);
        setValue(fsrExpTMsg.fsrVisitNum, pMsg.fsrVisitNum);
        setValue(fsrExpTMsg.svcTaskNum, this.completionBean.getFsrVisitTMsg().svcTaskNum);
        setValue(fsrExpTMsg.mdseCd, dtlPMsg.mdseCd);
        setValue(fsrExpTMsg.svcExecDt, pMsg.slsDt);
        setValue(fsrExpTMsg.svcExpQty, dtlPMsg.svcExpQty);
        setValue(fsrExpTMsg.uomCd, dtlPMsg.uomCd);
        // mod start 2016/02/22 CSA Defect#2749
        setValue(fsrExpTMsg.svcExpUnitAmt, dtlPMsg.svcExpUnitAmt);
        // mod end 2016/02/22 CSA Defect#2749
        setValue(fsrExpTMsg.svcExpDealAmt, svcExpDealAmt);
        setValue(fsrExpTMsg.svcExpFuncAmt, svcExpFuncAmt);
        // mod start 2016/02/22 CSA Defect#2749
        setValue(fsrExpTMsg.svcExpDiscRate, svcExpDiscRate);
        // mod end 2016/02/22 CSA Defect#2749
        setValue(fsrExpTMsg.svcExpDealDiscAmt, svcExpDealDiscAmt);
        setValue(fsrExpTMsg.svcExpFuncDiscAmt, svcExpFuncDiscAmt);
        setValue(fsrExpTMsg.svcExpCmntTxt, dtlPMsg.svcExpCmntTxt);
        // mod start 2016/02/22 CSA Defect#2749
        setValue(fsrExpTMsg.svcExpChrgFlg, this.completionBean.getSvcBillTpTMsg().expChrgFlg.getValue());
        // START 2018/12/13 K.Kitachi [QC#28635, ADD]
        if (!hasValue(svcExpDiscRate) || svcExpDiscRate.compareTo(BigDecimal.ZERO) == 0) {
            setValue(fsrExpTMsg.svcExpChrgFlg, ZYPConstant.FLG_ON_Y);
        }
        // END 2018/12/13 K.Kitachi [QC#28635, ADD]
        setValue(fsrExpTMsg.svcChrgChngRsnCd, dtlPMsg.svcChrgChngRsnCd);
        if (hasValue(dtlPMsg.svcChrgChngRsnCd)) {
            setValue(fsrExpTMsg.svcChrgChngUsrId, pMsg.userId);
        }
        // mod end 2016/02/22 CSA Defect#2749
        return true;
    }
    private boolean setFsrIstlChkValue(NSZC005001PMsg pMsg, NSZC005001_xxFsrIstlChkListPMsg dtlPMsg, FSR_ISTL_CHK_LISTTMsg fsrIstlChkTMsg) {
        BigDecimal fsrIstlChkListPk = null;
        if (hasValue(dtlPMsg.fsrIstlChkListPk)) {
            fsrIstlChkListPk = dtlPMsg.fsrIstlChkListPk.getValue();
            FSR_ISTL_CHK_LISTTMsg tMsg = getFsrIstlChkFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), fsrIstlChkListPk);
            if (tMsg != null) {
                S21ApiTBLAccessor.remove(tMsg);
                if (!checkRtnCodeForUpdate(pMsg, tMsg)) {
                    return false;
                }
            }
        } else {
            fsrIstlChkListPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.FSR_ISTL_CHK_LIST_SQ);
        }

        setValue(fsrIstlChkTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(fsrIstlChkTMsg.fsrIstlChkListPk, fsrIstlChkListPk);
        setValue(fsrIstlChkTMsg.fsrNum, dtlPMsg.fsrNum);
        setValue(fsrIstlChkTMsg.svcTaskNum, dtlPMsg.svcTaskNum);
        setValue(fsrIstlChkTMsg.svcConfigMstrPk, dtlPMsg.svcConfigMstrPk);
        setValue(fsrIstlChkTMsg.mdseCd, dtlPMsg.mdseCd);
        setValue(fsrIstlChkTMsg.mdlId, dtlPMsg.mdlId);
        setValue(fsrIstlChkTMsg.mdlNm, dtlPMsg.mdlNm);
        setValue(fsrIstlChkTMsg.serNum, dtlPMsg.serNum);
        // Mod Start 2017/09/01 QC#20672
        setValue(fsrIstlChkTMsg.istlChkVerFlg, nullToN(dtlPMsg.istlChkVerFlg.getValue()));
        // Mod End 2017/09/01 QC#20672
        setValue(fsrIstlChkTMsg.crctSerNum, dtlPMsg.crctSerNum);
        // Add Start 2017/09/01 QC#20672
        setValue(fsrIstlChkTMsg.istlCpltFlg, nullToN(pMsg.istlCpltFlg.getValue()));
        // Add End 2017/09/01 QC#20672
        return true;
    }

    // mod start 2017/01/15 CSA Defect#17129
    private boolean setFsrUsgValue(NSZC005001PMsg pMsg, NSZC005001_xxFsrUsgListPMsg dtlPMsg, NSZC061001_xxPartsChrgListPMsg apiPartsDtlPMsg, NSZC061001_xxDrumChrgListPMsg apiDrumDtlPMsg, FSR_USGTMsg fsrUsgTMsg, NSZC005001_xxPartSurveyListPMsg partSurvey) {
        FSR_VISITTMsgArray fsrVisitTMsgList = getFsrVisit(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), dtlPMsg.svcTaskNum.getValue());
        if (fsrVisitTMsgList == null || fsrVisitTMsgList.getValidCount() == 0) {
            setErrMsg(pMsg, NSZM0182E);
            return false;
        }

        List<Map<String, Object>> billTpList = getBillTp(pMsg, dtlPMsg.svcTaskNum.getValue());
        if (billTpList == null || billTpList.size() == 0) {
            setErrMsg(pMsg, NSZM0755E);
            return false;
        }

        BigDecimal svcPrtDealAmt = null;
        BigDecimal svcPrtDiscRate = null;
        BigDecimal svcPrtUnitAmt = null;
        String prcCatgCd = null;
        if (apiPartsDtlPMsg != null) {
            // Parts
            svcPrtDealAmt = apiPartsDtlPMsg.svcPrtDealAmt.getValue();
            svcPrtDiscRate = apiPartsDtlPMsg.svcPrtDiscRate.getValue();
            svcPrtUnitAmt = apiPartsDtlPMsg.svcPrtUnitAmt.getValue();
            prcCatgCd = apiPartsDtlPMsg.prcCatgCd_L1.getValue();
        } else if (apiDrumDtlPMsg != null) {
            // Drum
            svcPrtDealAmt = apiDrumDtlPMsg.xxSvcDrumDealAmt.getValue();
            svcPrtDiscRate = apiDrumDtlPMsg.xxSvcDrumDiscRate.getValue();
            svcPrtUnitAmt =  apiDrumDtlPMsg.xxSvcDrumUnitAmt.getValue();
            prcCatgCd = apiDrumDtlPMsg.prcCatgCd_L3.getValue();
        }

        // add start 2015/12/21 CSA Defect#1924
        if (hasValue(pMsg.xxModeCd_AD) && !hasValue(svcPrtDealAmt)) {
            svcPrtDealAmt = BigDecimal.ZERO;
        }
        // add end 2015/12/21 CSA Defect#1924

        // mod start 2016/04/20 CSA Defect#4469
        BigDecimal ccyExchRate = pMsg.ccyExchRate.getValue();
        if (!hasValue(ccyExchRate)) {
            ccyExchRate = this.completionBean.getSvcTaskTMsg().ccyExchRate.getValue();
        }
        BigDecimal svcPrtFuncAmt = calcFuncAmt(svcPrtDealAmt, ccyExchRate);
        BigDecimal svcPrtDealDiscAmt = calcDiscAmt(svcPrtDealAmt, svcPrtDiscRate);
        BigDecimal svcPrtFuncDiscAmt = calcFuncAmt(svcPrtDealDiscAmt, ccyExchRate);
        // mod start 2016/04/20 CSA Defect#4469

        setValue(fsrUsgTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(fsrUsgTMsg.fsrUsgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_USG_SQ));
        setValue(fsrUsgTMsg.fsrNum, pMsg.fsrNum);
        setValue(fsrUsgTMsg.fsrVisitNum, fsrVisitTMsgList.no(0).fsrVisitNum);
        setValue(fsrUsgTMsg.svcTaskNum, dtlPMsg.svcTaskNum);
        setValue(fsrUsgTMsg.dsSvcCallTpCd, dtlPMsg.dsSvcCallTpCd);
        setValue(fsrUsgTMsg.prtFromTechCd, dtlPMsg.prtFromTechCd);
        setValue(fsrUsgTMsg.prtUsedByTechCd, dtlPMsg.prtUsedByTechCd);
        setValue(fsrUsgTMsg.mdseCd, dtlPMsg.mdseCd);
        setValue(fsrUsgTMsg.mdseNm, dtlPMsg.mdseNm);
        setValue(fsrUsgTMsg.svcPrtQty, dtlPMsg.svcPrtQty);
        setValue(fsrUsgTMsg.svcPrtUnitAmt, svcPrtUnitAmt);
        setValue(fsrUsgTMsg.svcPrtDealAmt, svcPrtDealAmt);
        setValue(fsrUsgTMsg.svcPrtFuncAmt, svcPrtFuncAmt);
        setValue(fsrUsgTMsg.svcPrtDiscRate, svcPrtDiscRate);
        setValue(fsrUsgTMsg.svcPrtDealDiscAmt, svcPrtDealDiscAmt);
        setValue(fsrUsgTMsg.svcPrtFuncDiscAmt, svcPrtFuncDiscAmt);
        setValue(fsrUsgTMsg.fsrUsgProcFlg, ZYPConstant.FLG_ON_Y);
        // START 2017/12/28 M.Naito [QC#18646, MOD]
        String svcInvtyExFlg = ZYPConstant.FLG_OFF_N;
        if (hasValue(dtlPMsg.svcInvtyExFlg)) {
            svcInvtyExFlg = dtlPMsg.svcInvtyExFlg.getValue();
        }
        setValue(fsrUsgTMsg.svcInvtyExFlg, svcInvtyExFlg);
        // END 2017/12/28 M.Naito [QC#18646, MOD]
        setValue(fsrUsgTMsg.prtFromTechLocCd, dtlPMsg.prtFromTechLocCd);
        setValue(fsrUsgTMsg.prtUsedByTechLocCd, dtlPMsg.prtUsedByTechLocCd);
        setValue(fsrUsgTMsg.svcExecDt, dtlPMsg.svcExecDt);
        setValue(fsrUsgTMsg.uomCd, dtlPMsg.uomCd);
        setValue(fsrUsgTMsg.svcModPlnId, dtlPMsg.svcModPlnId);
        setValue(fsrUsgTMsg.svcPrtCmntTxt, dtlPMsg.svcPrtCmntTxt);
        if (isDrum(pMsg, dtlPMsg.mdseCd.getValue())) {
            setValue(fsrUsgTMsg.svcPrtChrgFlg, (String) billTpList.get(0).get("DRUM_CHRG_FLG"));
        } else {
            setValue(fsrUsgTMsg.svcPrtChrgFlg, (String) billTpList.get(0).get("PRT_CHRG_FLG"));

        }
        // START 2018/12/13 K.Kitachi [QC#28635, ADD]
        if (!hasValue(svcPrtDiscRate) || svcPrtDiscRate.compareTo(BigDecimal.ZERO) == 0) {
            setValue(fsrUsgTMsg.svcPrtChrgFlg, ZYPConstant.FLG_ON_Y);
        }
        // END 2018/12/13 K.Kitachi [QC#28635, ADD]
        setValue(fsrUsgTMsg.svcChrgChngRsnCd, dtlPMsg.svcChrgChngRsnCd);
        // mod start 2016/02/22 CSA Defect#2749
        if (hasValue(dtlPMsg.svcChrgChngRsnCd)) {
            setValue(fsrUsgTMsg.svcChrgChngUsrId, pMsg.userId);
        }
        // mod end 2016/02/22 CSA Defect#2749
        setValue(fsrUsgTMsg.serNumTxt, dtlPMsg.serNumTxt);
        setValue(fsrUsgTMsg.modItemTxt, dtlPMsg.modItemTxt);
        setValue(fsrUsgTMsg.prcCatgCd, prcCatgCd);

        // mod start 2016/04/20 CSA Defect#4469
        if (partSurvey != null) {
            setValue(fsrUsgTMsg.prtSrvyMdlTxt, partSurvey.prtSrvyMdlTxt);
            setValue(fsrUsgTMsg.prtSrvyWhTxt, partSurvey.prtSrvyWhTxt);
        }
        // mod end 2016/04/20 CSA Defect#4469
        return true;
    }
    // mod end 2017/01/15 CSA Defect#17129

    private boolean setFsrVisitValue(NSZC005001PMsg pMsg, String svcTaskNum, FSR_VISITTMsg fsrVisitTMsg) {
        SVC_TASKTMsg svcTaskTMsg = getSvcTaskFindByKey(pMsg.glblCmpyCd.getValue(), svcTaskNum);
        if (!checkRtnCodeForSearch(pMsg, svcTaskTMsg, new SVC_TASKTMsg())) {
            return false;
        }
        String fsrVisitNum = fsrVisitTMsg.fsrVisitNum.getValue();
        FSR_VISIT_TASKTMsg fsrVisitTaskTmsg = getFsrVisiTaskFindByKey(pMsg.glblCmpyCd.getValue(), fsrVisitTMsg.fsrNum.getValue(), fsrVisitNum, svcTaskNum);
        if (!checkRtnCodeForSearch(pMsg, fsrVisitTaskTmsg, new FSR_VISIT_TASKTMsg())) {
            return false;
        }
        Map<String, Object> fsrUsgAmt = getFsrUsgAmt(pMsg, fsrVisitNum, svcTaskNum);
        Map<String, Object> fsrExpAmt = getFsrExpAmt(pMsg, fsrVisitNum, svcTaskNum);
        NSZC061001PMsg svcPrcApiPMsg = this.completionBean.getSvcPrcApiPMsg();
        // add start 2015/12/21 CSA Defect#1924
        BigDecimal svcTrvlDealAmt = svcPrcApiPMsg.xxSvcTaskChrgList.no(0).svcTrvlDealAmt.getValue();
        if (hasValue(pMsg.xxModeCd_AD) && !hasValue(svcTrvlDealAmt)) {
            svcTrvlDealAmt = BigDecimal.ZERO;
        }
        // add end 2015/12/21 CSA Defect#1924

        String fsrVisitStsCd = fsrVisitTMsg.fsrVisitStsCd.getValue();
        String svcTaskStsCd = svcTaskTMsg.svcTaskStsCd.getValue();
        // add start 2015/12/21 CSA Defect#1924,2863
        if (!hasValue(svcTrvlDealAmt) && !frceUpdFlg) {
            svcTaskStsCd = SVC_TASK_STS.DEBRIEF_ERROR;
        }
        // add end 2015/12/21 CSA Defect#1924,2863

        if (SVC_TASK_STS.DEBRIEF_ERROR.equals(svcTaskStsCd)) {
            fsrVisitStsCd = SVC_TASK_STS.DEBRIEF_ERROR;
        } else if (SVC_TASK_STS.CANCELLED.equals(svcTaskStsCd)) {
            fsrVisitStsCd = SVC_TASK_STS.CANCELLED;
        } else if (SVC_TASK_STS.COMPLETED.equals(svcTaskStsCd)) {
            fsrVisitStsCd = SVC_TASK_STS.COMPLETED;
        } else if (SVC_TASK_STS.CLOSED.equals(svcTaskStsCd)) {
            fsrVisitStsCd = SVC_TASK_STS.CLOSED;
        }
        // Mod Start 2019/01/10 K.Fujimoto QC#26861
        if (this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
            fsrVisitStsCd = fsrVisitTMsg.fsrVisitStsCd.getValue();
        }
        // Mod End   2019/01/10 K.Fujimoto QC#26861

        if (!svcTaskNum.equals(this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue())) {
            setValue(fsrVisitTMsg.fsrVisitStsCd, fsrVisitStsCd);
            if (SVC_TASK_STS.CLOSED.equals(svcTaskStsCd) || SVC_TASK_STS.PENDING_CHARGE.equals(svcTaskStsCd) || SVC_TASK_STS.CANCELLED.equals(svcTaskStsCd)) {
                // START 2024/01/25 t.aizawa [QC#63504, ADD]
                if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitCloDt)) {
                // END   2024/01/25 t.aizawa [QC#63504, ADD]
                    setValue(fsrVisitTMsg.fsrVisitCloDt, sysDt);
                    setValue(fsrVisitTMsg.fsrVisitCloTm, sysTm);
                // START 2024/01/25 t.aizawa [QC#63504, ADD]
                }
                // END   2024/01/25 t.aizawa [QC#63504, ADD]
            }
            if (fsrUsgAmt != null) {
                setValue(fsrVisitTMsg.svcPrtDealAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_DEAL_AMT"));
                setValue(fsrVisitTMsg.svcPrtFuncAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_FUNC_AMT"));
                setValue(fsrVisitTMsg.svcPrtDealDiscAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_DEAL_DISC_AMT"));
                setValue(fsrVisitTMsg.svcPrtFuncDiscAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_FUNC_DISC_AMT"));
            }
            return true;
        }

        if (ZYPCommonFunc.hasValue(pMsg.iwrStsCd)) {
            setValue(fsrVisitTMsg.iwrStsCd, pMsg.iwrStsCd);
        }
        // del start 2015/12/21 CSA Defect#1924
//        BigDecimal svcTrvlDealAmt = svcPrcApiPMsg.xxSvcTaskChrgList.no(0).svcTrvlDealAmt.getValue();
        // del start 2015/12/21 CSA Defect#1924
        BigDecimal svcTrvlFuncAmt = calcFuncAmt(svcTrvlDealAmt, pMsg.ccyExchRate.getValue());
        BigDecimal svcTrvlDealDiscAmt = calcDiscAmt(svcTrvlDealAmt, svcPrcApiPMsg.xxSvcTaskChrgList.no(0).svcTrvlDiscRate.getValue());
        BigDecimal svcTrvlFuncDiscAmt = calcFuncAmt(svcTrvlDealDiscAmt, pMsg.ccyExchRate.getValue());

        setValue(fsrVisitTMsg.fsrVisitStsCd, fsrVisitStsCd);
        // START 2020/06/01 K.Kitachi [QC#56271, ADD]
        setFsrVisitDisptDtTm(pMsg, fsrVisitTMsg);
        // END 2020/06/01 K.Kitachi [QC#56271, ADD]
        setValue(fsrVisitTMsg.fsrVisitArvDt, pMsg.fsrVisitArvDt);
        setValue(fsrVisitTMsg.fsrVisitArvTm, pMsg.fsrVisitArvTm);
        setValue(fsrVisitTMsg.fsrVisitCpltDt, pMsg.fsrVisitCpltDt);
        setValue(fsrVisitTMsg.fsrVisitCpltTm, pMsg.fsrVisitCpltTm);
        if (SVC_TASK_STS.CLOSED.equals(svcTaskStsCd) || SVC_TASK_STS.PENDING_CHARGE.equals(svcTaskStsCd) || SVC_TASK_STS.CANCELLED.equals(svcTaskStsCd)) {
            // START 2024/01/25 t.aizawa [QC#63504, ADD]
            if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitCloDt)) {
            // END   2024/01/25 t.aizawa [QC#63504, ADD]
                setValue(fsrVisitTMsg.fsrVisitCloDt, sysDt);
                setValue(fsrVisitTMsg.fsrVisitCloTm, sysTm);
            // START 2024/01/25 t.aizawa [QC#63504, ADD]
            }
            // END   2024/01/25 t.aizawa [QC#63504, ADD]
        }
        setValue(fsrVisitTMsg.svcTrvlTmNum, pMsg.svcTrvlTmNum);
        setValue(fsrVisitTMsg.svcLborDealAmt, fsrVisitTaskTmsg.svcLborDealAmt);
        setValue(fsrVisitTMsg.svcLborFuncAmt, fsrVisitTaskTmsg.svcLborFuncAmt);
        setValue(fsrVisitTMsg.svcLborDealDiscAmt, fsrVisitTaskTmsg.svcLborDealDiscAmt);
        setValue(fsrVisitTMsg.svcLborFuncDiscAmt, fsrVisitTaskTmsg.svcLborFuncDiscAmt);
        setValue(fsrVisitTMsg.svcLborInvDealAmt, fsrVisitTaskTmsg.svcLborInvDealAmt);
        setValue(fsrVisitTMsg.svcLborInvFuncAmt, fsrVisitTaskTmsg.svcLborInvFuncAmt);
        setValue(fsrVisitTMsg.svcLborInvDealDiscAmt, fsrVisitTaskTmsg.svcLborInvDealDiscAmt);
        setValue(fsrVisitTMsg.svcLborInvFuncDiscAmt, fsrVisitTaskTmsg.svcLborInvFuncDiscAmt);
        setValue(fsrVisitTMsg.svcTrvlUnitHrsAot, svcPrcApiPMsg.xxSvcTaskChrgList.no(0).svcTrvlUnitHrsAot);
        setValue(fsrVisitTMsg.svcTrvlUnitAmt, svcPrcApiPMsg.xxSvcTaskChrgList.no(0).svcTrvlUnitAmt);
        setValue(fsrVisitTMsg.svcTrvlDealAmt, svcTrvlDealAmt);
        setValue(fsrVisitTMsg.svcTrvlFuncAmt, svcTrvlFuncAmt);
        setValue(fsrVisitTMsg.svcTrvlDiscRate, svcPrcApiPMsg.xxSvcTaskChrgList.no(0).svcTrvlDiscRate);
        setValue(fsrVisitTMsg.svcTrvlDealDiscAmt, svcTrvlDealDiscAmt);
        setValue(fsrVisitTMsg.svcTrvlFuncDiscAmt, svcTrvlFuncDiscAmt);
        setValue(fsrVisitTMsg.svcCpltLttdNum, pMsg.svcCpltLttdNum);
        setValue(fsrVisitTMsg.svcCpltLgtdNum, pMsg.svcCpltLgtdNum);
        setValue(fsrVisitTMsg.svcCpltLocGapDstNum, pMsg.svcCpltLocGapDstNum);
        if (fsrUsgAmt != null) {
            setValue(fsrVisitTMsg.svcPrtDealAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_DEAL_AMT"));
            setValue(fsrVisitTMsg.svcPrtFuncAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_FUNC_AMT"));
            setValue(fsrVisitTMsg.svcPrtDealDiscAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_DEAL_DISC_AMT"));
            setValue(fsrVisitTMsg.svcPrtFuncDiscAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_FUNC_DISC_AMT"));
        }
        if (fsrExpAmt != null) {
            setValue(fsrVisitTMsg.svcExpDealAmt, (BigDecimal) fsrExpAmt.get("SVC_EXP_DEAL_AMT"));
            setValue(fsrVisitTMsg.svcExpFuncAmt, (BigDecimal) fsrExpAmt.get("SVC_EXP_FUNC_AMT"));
            setValue(fsrVisitTMsg.svcExpDealDiscAmt, (BigDecimal) fsrExpAmt.get("SVC_EXP_DEAL_DISC_AMT"));
            setValue(fsrVisitTMsg.svcExpFuncDiscAmt, (BigDecimal) fsrExpAmt.get("SVC_EXP_FUNC_DISC_AMT"));
        }
        return true;
    }
    private boolean setFsrVisitValueForIstlChk(NSZC005001PMsg pMsg, String svcTaskNum, FSR_VISITTMsg fsrVisitTMsg) {
        SVC_TASKTMsg svcTaskTMsg = getSvcTaskFindByKey(pMsg.glblCmpyCd.getValue(), svcTaskNum);
        if (!checkRtnCodeForSearch(pMsg, svcTaskTMsg, new SVC_TASKTMsg())) {
            return false;
        }
        String fsrVisitNum = fsrVisitTMsg.fsrVisitNum.getValue();
        Map<String, Object> fsrUsgAmt = getFsrUsgAmt(pMsg, fsrVisitNum, svcTaskNum);

        String fsrVisitStsCd = fsrVisitTMsg.fsrVisitStsCd.getValue();
        String svcTaskStsCd = svcTaskTMsg.svcTaskStsCd.getValue();
        if (!hasValue(fsrVisitTMsg.svcTrvlDealAmt)) {
            svcTaskStsCd = SVC_TASK_STS.DEBRIEF_ERROR;
        }

        if (SVC_TASK_STS.DEBRIEF_ERROR.equals(svcTaskStsCd)) {
            fsrVisitStsCd = SVC_TASK_STS.DEBRIEF_ERROR;
        } else if (SVC_TASK_STS.CANCELLED.equals(svcTaskStsCd)) {
            fsrVisitStsCd = SVC_TASK_STS.CANCELLED;
        } else if (SVC_TASK_STS.COMPLETED.equals(svcTaskStsCd)) {
            fsrVisitStsCd = SVC_TASK_STS.COMPLETED;
        } else if (SVC_TASK_STS.CLOSED.equals(svcTaskStsCd)) {
            fsrVisitStsCd = SVC_TASK_STS.CLOSED;
        }

        if (!svcTaskNum.equals(this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue())) {
            setValue(fsrVisitTMsg.fsrVisitStsCd, fsrVisitStsCd);
            if (SVC_TASK_STS.CLOSED.equals(svcTaskStsCd) || SVC_TASK_STS.PENDING_CHARGE.equals(svcTaskStsCd) || SVC_TASK_STS.CANCELLED.equals(svcTaskStsCd)) {
                // START 2024/01/25 t.aizawa [QC#63504, ADD]
                if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitCloDt)) {
                // END   2024/01/25 t.aizawa [QC#63504, ADD]
                    setValue(fsrVisitTMsg.fsrVisitCloDt, sysDt);
                    setValue(fsrVisitTMsg.fsrVisitCloTm, sysTm);
                // START 2024/01/25 t.aizawa [QC#63504, ADD]
                }
                // END   2024/01/25 t.aizawa [QC#63504, ADD]
            }
            if (fsrUsgAmt != null) {
                setValue(fsrVisitTMsg.svcPrtDealAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_DEAL_AMT"));
                setValue(fsrVisitTMsg.svcPrtFuncAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_FUNC_AMT"));
                setValue(fsrVisitTMsg.svcPrtDealDiscAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_DEAL_DISC_AMT"));
                setValue(fsrVisitTMsg.svcPrtFuncDiscAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_FUNC_DISC_AMT"));
            }
            return true;
        }
        setValue(fsrVisitTMsg.fsrVisitStsCd, fsrVisitStsCd);
        if (SVC_TASK_STS.CLOSED.equals(svcTaskStsCd) || SVC_TASK_STS.PENDING_CHARGE.equals(svcTaskStsCd) || SVC_TASK_STS.CANCELLED.equals(svcTaskStsCd)) {
            // START 2024/01/25 t.aizawa [QC#63504, ADD]
            if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitCloDt)) {
            // END   2024/01/25 t.aizawa [QC#63504, ADD]
                setValue(fsrVisitTMsg.fsrVisitCloDt, sysDt);
                setValue(fsrVisitTMsg.fsrVisitCloTm, sysTm);
            // START 2024/01/25 t.aizawa [QC#63504, ADD]
            }
            // END   2024/01/25 t.aizawa [QC#63504, ADD]
        }
        if (fsrUsgAmt != null) {
            setValue(fsrVisitTMsg.svcPrtDealAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_DEAL_AMT"));
            setValue(fsrVisitTMsg.svcPrtFuncAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_FUNC_AMT"));
            setValue(fsrVisitTMsg.svcPrtDealDiscAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_DEAL_DISC_AMT"));
            setValue(fsrVisitTMsg.svcPrtFuncDiscAmt, (BigDecimal) fsrUsgAmt.get("SVC_PRT_FUNC_DISC_AMT"));
        }
        return true;
    }
//    private boolean checkInstallCheckList(NSZC005001PMsg pMsg) {
//        boolean isErr = false;
//        String svcTaskNum = this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue();
//        if (ZYPConstant.FLG_ON_Y.equals(this.completionBean.getDsSvcCallTpTMsg().svcIstlReqFlg.getValue())) {
//            if (pMsg.xxFsrIstlChkList.getValidCount() < 1) {
//                isErr = true;
//            }
//        } else {
//            return true;
//        }
//        //check verify flag of all list is 'Y'
//        for (int i = 0; i < pMsg.xxFsrIstlChkList.getValidCount(); i++) {
//            NSZC005001_xxFsrIstlChkListPMsg dtlPMsg = pMsg.xxFsrIstlChkList.no(i);
//
//            if (ZYPConstant.FLG_ON_Y.equals(dtlPMsg.istlChkVerFlg.getValue())) {
//                continue;
//            } else {
//                isErr = true;
//                break;
//            }
//        }
//        if (isErr) {
//            isDebreifError = true;
//            if (!updateSvcTaskStsCd(pMsg, SVC_TASK_STS.DEBRIEF_ERROR)) {
//                return false;
//            }
//            String istlChckListMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_INSTALL_CHECK_LIST_MSG, pMsg.glblCmpyCd.getValue());
//            if (!insertSvcMemo(pMsg, svcTaskNum, istlChckListMsg)) {
//                return false;
//            }
//        }
//        return true;
//    }
    private void setFsrVisitTaskValue(NSZC005001PMsg pMsg, NSZC005001_xxVisitTaskListPMsg dtlPMsg, NSZC061001_xxSvcTaskChrgListPMsg apiDtlPMsg, FSR_VISIT_TASKTMsg fsrVisitTaskTMsg) {
        BigDecimal svcLborDealAmt = apiDtlPMsg.svcLborDealAmt.getValue();
        // add start 2015/12/21 CSA Defect#1924
        if (hasValue(pMsg.xxModeCd_AD) && !hasValue(svcLborDealAmt)) {
            svcLborDealAmt = BigDecimal.ZERO;
        }
        // add end 2015/12/21 CSA Defect#1924
        BigDecimal svcLborFuncAmt = calcFuncAmt(svcLborDealAmt, pMsg.ccyExchRate.getValue());
        // mod start 2016/05/20 CSA Defect#8480
        // BigDecimal svcLborDealDiscAmt = calcDiscAmt(svcLborDealAmt, apiDtlPMsg.svcLborDiscRate.getValue());
        BigDecimal svcLborDealDiscAmt = apiDtlPMsg.svcLborDealDiscAmt.getValue();
        // mod end 2016/05/20 CSA Defect#8480
        BigDecimal svcLborFuncDiscAmt = calcFuncAmt(svcLborDealDiscAmt, pMsg.ccyExchRate.getValue());

        setValue(fsrVisitTaskTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(fsrVisitTaskTMsg.svcTaskNum, dtlPMsg.svcTaskNum);
        setValue(fsrVisitTaskTMsg.fsrNum, pMsg.fsrNum);
        // mod start 2016/02/08 CSA Defect#2863
        if (frceUpdFlg) {
            setValue(fsrVisitTaskTMsg.fsrVisitNum, this.completionBean.getFsrVisitTMsg().fsrVisitNum);
        } else {
            setValue(fsrVisitTaskTMsg.fsrVisitNum, pMsg.fsrVisitNum);
        }
        // mod end 2016/02/08 CSA Defect#2863
        setValue(fsrVisitTaskTMsg.svcPblmTpCd, dtlPMsg.svcPblmTpCd);
        setValue(fsrVisitTaskTMsg.svcPblmLocTpCd, dtlPMsg.svcPblmLocTpCd);
        setValue(fsrVisitTaskTMsg.svcPblmRsnTpCd, dtlPMsg.svcPblmRsnTpCd);
        setValue(fsrVisitTaskTMsg.svcPblmCrctTpCd, dtlPMsg.svcPblmCrctTpCd);
        setValue(fsrVisitTaskTMsg.svcLborTmNum, this.completionBean.getSvcLborTmNum());
        setValue(fsrVisitTaskTMsg.svcLborUnitHrsAot, apiDtlPMsg.svcLborUnitHrsAot);
        setValue(fsrVisitTaskTMsg.svcLborUnitAmt, apiDtlPMsg.svcLborUnitAmt);
        setValue(fsrVisitTaskTMsg.svcLborDealAmt, svcLborDealAmt);
        setValue(fsrVisitTaskTMsg.svcLborFuncAmt, svcLborFuncAmt);
        setValue(fsrVisitTaskTMsg.svcLborDiscRate, apiDtlPMsg.svcLborDiscRate);
        setValue(fsrVisitTaskTMsg.svcLborDealDiscAmt, svcLborDealDiscAmt);
        setValue(fsrVisitTaskTMsg.svcLborFuncDiscAmt, svcLborFuncDiscAmt);
        setValue(fsrVisitTaskTMsg.svcLborInvDealAmt, svcLborDealAmt);
        setValue(fsrVisitTaskTMsg.svcLborInvFuncAmt, svcLborFuncAmt);
        setValue(fsrVisitTaskTMsg.svcLborInvDealDiscAmt, svcLborDealDiscAmt);
        setValue(fsrVisitTaskTMsg.svcLborInvFuncDiscAmt, svcLborFuncDiscAmt);
        // mod start 2016/02/08 CSA Defect#2863
        if (hasValue(dtlPMsg.xxVisitTaskCpltFlg)) {
            setValue(fsrVisitTaskTMsg.svcTaskCpltFlg, dtlPMsg.xxVisitTaskCpltFlg);
        } else {
            setValue(fsrVisitTaskTMsg.svcTaskCpltFlg, ZYPConstant.FLG_ON_Y);
        }
        // mod end 2016/02/08 CSA Defect#2863
        if (hasValue(this.completionBean.getPhoneFixFlg())) {
            setValue(fsrVisitTaskTMsg.cpltByTelFixFlg, this.completionBean.getPhoneFixFlg());
        } else {
            setValue(fsrVisitTaskTMsg.cpltByTelFixFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    private boolean setFsrVisitTmEventValue(NSZC005001PMsg pMsg, NSZC005001_xxTmEventListPMsg dtlPMsg, String mdseCd, FSR_VISIT_TM_EVENTTMsg fsrVisitTmEventTMsg) {
        setValue(fsrVisitTmEventTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(fsrVisitTmEventTMsg.fsrVisitTmEventPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_VISIT_TM_EVENT_SQ));
        setValue(fsrVisitTmEventTMsg.fsrNum, pMsg.fsrNum);
        setValue(fsrVisitTmEventTMsg.fsrVisitNum, pMsg.fsrVisitNum);
        setValue(fsrVisitTmEventTMsg.svcTmEventCd, dtlPMsg.svcTmEventCd);
        setValue(fsrVisitTmEventTMsg.svcTaskNum, dtlPMsg.svcTaskNum);
        setValue(fsrVisitTmEventTMsg.svcTmEventFromDt, dtlPMsg.svcTmEventFromDt);
        setValue(fsrVisitTmEventTMsg.svcTmEventFromTm, dtlPMsg.svcTmEventFromTm);
        setValue(fsrVisitTmEventTMsg.svcTmEventToDt, dtlPMsg.svcTmEventToDt);
        setValue(fsrVisitTmEventTMsg.svcTmEventToTm, dtlPMsg.svcTmEventToTm);
        setValue(fsrVisitTmEventTMsg.machDownFlg, dtlPMsg.machDownFlg);
        setValue(fsrVisitTmEventTMsg.mdseCd, mdseCd);
        setValue(fsrVisitTmEventTMsg.svcModPlnId, dtlPMsg.svcModPlnId);
        setValue(fsrVisitTmEventTMsg.svcLborCmntTxt, dtlPMsg.svcLborCmntTxt);
        setValue(fsrVisitTmEventTMsg.svcLborChrgFlg, this.completionBean.getSvcBillTpTMsg().lborChrgFlg.getValue());
        setValue(fsrVisitTmEventTMsg.serNumTxt, dtlPMsg.serNumTxt);
        setValue(fsrVisitTmEventTMsg.modItemTxt, dtlPMsg.modItemTxt);
        // add start 2015/12/16 CSA Defect#978
        setValue(fsrVisitTmEventTMsg.fsrVisitTmEventProcFlg, ZYPConstant.FLG_OFF_N);
        // add end 2015/12/16 CSA Defect#978
        return true;
    }

    private boolean setSvcTaskValue(NSZC005001PMsg pMsg, String svcTaskNum, SVC_TASKTMsg svcTaskTMsg) {
        // change Service Task Status Code
        String svcTaskStsCd = svcTaskTMsg.svcTaskStsCd.getValue();

        // add start 2017/01/15 CSA Defect#17108
        Map<String, Object> fsrVisitAmt = getFsrVisitTotAmt(pMsg, svcTaskNum);
        if (fsrVisitAmt == null) {
            setErrMsg(pMsg, NSZM0182E);
            return false;
        }
        // add end 2017/01/15 CSA Defect#17108

        if ((fsrCompleteFlg) || (!fsrCompleteFlg && svcTaskNum.equals(this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue()) && !SVC_TASK_STS.DEBRIEF_ERROR.equals(svcTaskStsCd))) {
            // del start 2017/01/15 CSA Defect#17108
//            Map<String, Object> fsrVisitAmt = getFsrVisitTotAmt(pMsg, svcTaskNum);
//            if (fsrVisitAmt == null) {
//                setErrMsg(pMsg, NSZM0182E);
//                return false;
//            }
            // del end 2017/01/15 CSA Defect#17108

            // mod start 2015/12/16 CSA Defect#978
            //if (BigDecimal.ZERO.compareTo((BigDecimal) fsrVisitAmt.get("TOT_AMT")) == 0) {
            //    svcTaskStsCd = SVC_TASK_STS.CLOSED;
            //    updateFsrVisitStsCd(pMsg, (String) fsrVisitAmt.get("FSR_VISIT_NUM"), SVC_TASK_STS.CLOSED);
            //} else if (BigDecimal.ZERO.compareTo((BigDecimal) fsrVisitAmt.get("TOT_AMT")) != 0) {
            //    svcTaskStsCd = SVC_TASK_STS.COMPLETED;
            //    updateFsrVisitStsCd(pMsg, (String) fsrVisitAmt.get("FSR_VISIT_NUM"), SVC_TASK_STS.COMPLETED);
            //}
            // Mod Start 2019/01/10 K.Fujimoto QC#26861
            if(!this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
                svcTaskStsCd = SVC_TASK_STS.COMPLETED;
                updateFsrVisitStsCd(pMsg, (String) fsrVisitAmt.get("FSR_VISIT_NUM"), SVC_TASK_STS.COMPLETED);
            }
            // Mod End   2019/01/10 K.Fujimoto QC#26861
            // mod end 2015/12/16 CSA Defect#978

            // del start 2017/01/15 CSA Defect#17108
//            // START 2016/10/20 [QC#14252, ADD]
//            FSR_VISIT_TASKTMsg fsrVisitTaskTMsg = getFsrVisiTaskFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), (String) fsrVisitAmt.get("FSR_VISIT_NUM"), svcTaskNum);
//            // mod start 2016/12/13 CSA Defect#14252
//            if (svcTaskNum.equals(this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue()) && fsrVisitTaskTMsg != null) {
//                String dsSvcCallTpCd = this.completionBean.getSvcTaskTMsg().dsSvcCallTpCd.getValue();
//                String firstProdCtrlCd = this.completionBean.getSvcTaskTMsg().firstProdCtrlCd.getValue();
//                String svcPblmCrctTpCd = fsrVisitTaskTMsg.svcPblmCrctTpCd.getValue();
//                setValue(svcTaskTMsg.dsSvcCallTpCd, convertCallType(pMsg.glblCmpyCd.getValue(), dsSvcCallTpCd, firstProdCtrlCd, svcPblmCrctTpCd));
//            }
//            // mod end 2016/12/13 CSA Defect#14252
//            // END 2016/10/20 [QC#14252, ADD]
            // del end 2017/01/15 CSA Defect#17108
        }

        // add start 2017/01/15 CSA Defect#17108
        FSR_VISIT_TASKTMsg fsrVisitTaskTMsg = getFsrVisiTaskFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), (String) fsrVisitAmt.get("FSR_VISIT_NUM"), svcTaskNum);
        if (svcTaskNum.equals(this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue()) && fsrVisitTaskTMsg != null) {
            String dsSvcCallTpCd = this.completionBean.getSvcTaskTMsg().dsSvcCallTpCd.getValue();
            String firstProdCtrlCd = this.completionBean.getSvcTaskTMsg().firstProdCtrlCd.getValue();
            String svcPblmCrctTpCd = fsrVisitTaskTMsg.svcPblmCrctTpCd.getValue();
            // START 2017/10/25 [QC#22061, MOD]
//            setValue(svcTaskTMsg.dsSvcCallTpCd, convertCallType(pMsg.glblCmpyCd.getValue(), dsSvcCallTpCd, firstProdCtrlCd, svcPblmCrctTpCd));
            setValue(svcTaskTMsg.dsSvcCallTpCd, convertCallType(pMsg.glblCmpyCd.getValue(), dsSvcCallTpCd, firstProdCtrlCd, svcPblmCrctTpCd, pMsg.xxVisitTaskList.no(0).phoneFixFlg.getValue()));
            // END 2017/10/25 [QC#22061, MOD]
        }
        // add end 2017/01/15 CSA Defect#17108

        setValue(svcTaskTMsg.svcTaskStsCd, svcTaskStsCd);
        if (svcTaskNum.equals(this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue())) {
            setValue(svcTaskTMsg.svcTaskCpltDt, pMsg.fsrVisitCpltDt);
            setValue(svcTaskTMsg.svcTaskCpltTm, pMsg.fsrVisitCpltTm);
            // START 2018/03/22 M.Naito [QC#18713, ADD]
            if (ZYPConstant.FLG_OFF_N.equals(pMsg.mblIntfcFlg.getValue())) {
                // Service Task is closed from Click mobile
                // START 2019/11/18 K.Fujimoto [QC#54391, MOD]
                // setValue(svcTaskTMsg.taskPrintStsCd, TASK_PRINT_STS.CREATED);
                if (this.completionBean.isOnsSprtCall()) {
                    setValue(svcTaskTMsg.taskPrintStsCd, TASK_PRINT_STS.PRINT_SKIPPED);
                } else {
                    setValue(svcTaskTMsg.taskPrintStsCd, TASK_PRINT_STS.CREATED);
                }
                // END   2019/11/18 K.Fujimoto [QC#54391, MOD]
                if (ZYPCommonFunc.hasValue(pMsg.xxVisitTaskList.no(0).signaFilePathTxt)) {
                    setValue(svcTaskTMsg.signaFilePathTxt, pMsg.xxVisitTaskList.no(0).signaFilePathTxt.getValue());
                }
                // START 2018/06/01 M.Naito [QC#26272, ADD]
                if (ZYPCommonFunc.hasValue(pMsg.xxVisitTaskList.no(0).sendRptEmlAddr)) {
                    setValue(svcTaskTMsg.sendRptEmlAddr, pMsg.xxVisitTaskList.no(0).sendRptEmlAddr.getValue());
                }
                // END 2018/06/01 M.Naito [QC#26272, ADD]
            }
            // END 2018/03/22 M.Naito [QC#18713, ADD]
            // START 2022/04/27 K.Kitachi [QC#59895, ADD]
            if (hasValue(pMsg.arvMachDownTpCd)) {
                setValue(svcTaskTMsg.arvMachDownTpCd, pMsg.arvMachDownTpCd);
            }
            // END 2022/04/27 K.Kitachi [QC#59895, ADD]
        }
        if (SVC_TASK_STS.CLOSED.equals(svcTaskStsCd) || SVC_TASK_STS.PENDING_CHARGE.equals(svcTaskStsCd) || SVC_TASK_STS.CANCELLED.equals(svcTaskStsCd)) {
            setValue(svcTaskTMsg.svcTaskCloDt, sysDt);
            setValue(svcTaskTMsg.svcTaskCloTm, sysTm);
            setValue(svcTaskTMsg.svcTaskCloByUsrId, pMsg.userId);
        }
        // START 2020/04/13 [QC#56498, MOD]
        if (!hasValue(svcTaskTMsg.svcRspTmNum)) {
            setValue(svcTaskTMsg.svcRspTmNum, getResponseTime(pMsg, svcTaskTMsg, false));
            if (!hasValue(svcTaskTMsg.svcRspTmNum)) {
                svcTaskTMsg.svcRspTmMnAot.clear();
            }
        }
        if (!hasValue(svcTaskTMsg.svcRprTmNum)) {
            setValue(svcTaskTMsg.svcRprTmNum, getResponseTime(pMsg, svcTaskTMsg, true));
        }
        // END 2020/04/13 [QC#56498, MOD]
        if (!hasValue(svcTaskTMsg.svcTaskDbrfSq)) {
            setValue(svcTaskTMsg.svcTaskDbrfSq, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TASK_DBRF_SQ));
        }
        return true;
    }

    private boolean existsLborInFsr(NSZC005001PMsg pMsg) {
        int cnt = getFsrVisitTmEventLborCnt(pMsg);
        if (cnt > 0) {
            return true;
        }
        return false;
    }

    private boolean existsPrtInFsr(NSZC005001PMsg pMsg) {
        FSR_USGTMsgArray fsrUsgTMsgList = getFsrUsgListByFsrNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (fsrUsgTMsgList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    private boolean existsExpInFsr(NSZC005001PMsg pMsg) {
        FSR_EXPTMsgArray fsrExpList = getFsrExpListByFsrNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (fsrExpList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    private boolean existsDrumInFsr(NSZC005001PMsg pMsg) {
        // del start 2017/01/15 CSA Defect#17129
//        String intgProdCatgCdDrum = this.completionBean.getIntgProdCatgCdDrum();
//        if (!hasValue(intgProdCatgCdDrum)) {
//            return false;
//        }
        // del end 2017/01/15 CSA Defect#17129

        FSR_USGTMsgArray fsrUsgTMsgList = getFsrUsgListByFsrNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        for (int i = 0; i < fsrUsgTMsgList.getValidCount(); i++) {
            // mod start 2017/01/15 CSA Defect#17129
//            MDSETMsg mdseTMsg = getMdseFindByKey(pMsg.glblCmpyCd.getValue(), fsrUsgTMsgList.no(i).mdseCd.getValue());
//            if (mdseTMsg == null) {
//                return false;
//            }
//            if (intgProdCatgCdDrum.equals(mdseTMsg.intgProdCatgCd.getValue())) {
//                return true;
//            }

            MDSETMsg mdseTMsg = getMdseFindByKey(pMsg.glblCmpyCd.getValue(), fsrUsgTMsgList.no(i).mdseCd.getValue());
            if (mdseTMsg == null) {
                return false;
            }
            if (COA_MDSE_TP.DRUM.equals(mdseTMsg.coaMdseTpCd.getValue())) {
                return true;
            }
            // mod end 2017/01/15 CSA Defect#17129
        }
        return false;
    }

    private boolean existsDrumInTask(NSZC005001PMsg pMsg, String svcTaskNum) {
        // del start 2017/01/15 CSA Defect#17129
//        String intgProdCatgCdDrum = this.completionBean.getIntgProdCatgCdDrum();
//        if (!hasValue(intgProdCatgCdDrum)) {
//            return false;
//        }
        // del end 2017/01/15 CSA Defect#17129

        FSR_USGTMsgArray fsrUsgTMsgList = getFsrUsgListBySvcTaskNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), svcTaskNum);
        for (int i = 0; i < fsrUsgTMsgList.getValidCount(); i++) {
            // mod start 2017/01/15 CSA Defect#17129
//            MDSETMsg mdseTMsg = getMdseFindByKey(pMsg.glblCmpyCd.getValue(), fsrUsgTMsgList.no(i).mdseCd.getValue());
//            if (mdseTMsg == null) {
//                return false;
//            }
//            if (intgProdCatgCdDrum.equals(mdseTMsg.intgProdCatgCd.getValue())) {
//                return true;
//            }

            MDSETMsg mdseTMsg = getMdseFindByKey(pMsg.glblCmpyCd.getValue(), fsrUsgTMsgList.no(i).mdseCd.getValue());
            if (mdseTMsg == null) {
                return false;
            }
            if (COA_MDSE_TP.DRUM.equals(mdseTMsg.coaMdseTpCd.getValue())) {
                return true;
            }
            // mod end 2017/01/15 CSA Defect#17129
        }
        return false;
    }

    private boolean isDrum(NSZC005001PMsg pMsg, String mdseCd) {
        // mod start 2017/01/16 CSA Defect#17129
//        String intgProdCatgCdDrum = this.completionBean.getIntgProdCatgCdDrum();
//        if (!hasValue(intgProdCatgCdDrum)) {
//            return false;
//        }
//        MDSETMsg mdseTMsg = getMdseFindByKey(pMsg.glblCmpyCd.getValue(), mdseCd);
//        if (mdseTMsg == null) {
//            return false;
//        }
//        if (intgProdCatgCdDrum.equals(mdseTMsg.intgProdCatgCd.getValue())) {
//            return true;
//        }
        MDSETMsg mdseTMsg = getMdseFindByKey(pMsg.glblCmpyCd.getValue(), mdseCd);
        if (mdseTMsg == null) {
            return false;
        }
        if (COA_MDSE_TP.DRUM.equals(mdseTMsg.coaMdseTpCd.getValue())) {
            return true;
        }
        return false;
        // mod end 2017/01/16 CSA Defect#17129
    }

    private BigDecimal calcFuncAmt(BigDecimal dealAmt, BigDecimal ccyExchRate) {
        if (!hasValue(dealAmt)) {
            return null;
        }
        BigDecimal funcAmt = BigDecimal.ZERO;
        if (ACCT_ARTH_TP.MULTIPLY.equals(this.completionBean.getAcctArthTpCd())) {
            funcAmt = dealAmt.multiply(ccyExchRate);
        } else if (ACCT_ARTH_TP.DIVIDE.equals(this.completionBean.getAcctArthTpCd())) {
            funcAmt = dealAmt.divide(ccyExchRate);
        }
        return funcAmt.setScale(this.completionBean.getStdCcyAftDeclPntDigitNum().intValue(), BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal calcDiscAmt(BigDecimal dealAmt, BigDecimal discRate) {
        if (!hasValue(dealAmt)) {
            return null;
        }
        // add start 2015/12/22 CSA Defect#1924
        if (!hasValue(discRate)) {
            discRate = BigDecimal.ZERO;
        }
        // add end 2015/12/22 CSA Defect#1924
        BigDecimal discAmt = dealAmt.multiply(discRate);
        // mod start 2016/02/08 CSA Defect#4644
        discAmt = discAmt.divide(BIGDECIMAL_100);
        // mod start 2016/02/08 CSA Defect#4644
        return discAmt.setScale(this.completionBean.getAftDeclPntDigitNum().intValue(), BigDecimal.ROUND_HALF_UP);
    }

    private static String formatTm(String inTm) {
        // add start 2016/02/22 CSA Defect#2695
        if (!hasValue(inTm)) {
            return null;
        }
        // add end 2016/02/22 CSA Defect#2695
        String formatTm = inTm + "000000";
        return formatTm.substring(0, LEN_TM);
    }

    private static Date toDate(String inDtTm, String inFormat) {
        SimpleDateFormat parser = new SimpleDateFormat(inFormat);
        try {
            return parser.parse(inDtTm);
        } catch (ParseException e) {
            return null;
        }
    }

    private static String toStringDate(Date inDtTm, String inFormat) {
        SimpleDateFormat parser = new SimpleDateFormat(inFormat);
        return parser.format(inDtTm);
    }

    private String getFsrStsCdForComp(NSZC005001PMsg pMsg, FSR_VISITTMsg fsrVisitAmt, List<Map<String, Object>> billTpList) {
        // mod start 2017/02/13 CSA Defect#17109
        //if ((BigDecimal.ZERO.compareTo(fsrVisitAmt.svcLborDealAmt.getValue()) != 0) || (BigDecimal.ZERO.compareTo(fsrVisitAmt.svcLborInvDealAmt.getValue()) != 0) || (BigDecimal.ZERO.compareTo(fsrVisitAmt.svcTrvlDealAmt.getValue()) != 0)
        //        || (BigDecimal.ZERO.compareTo(fsrVisitAmt.svcPrtDealAmt.getValue()) != 0) || (BigDecimal.ZERO.compareTo(fsrVisitAmt.svcExpDealAmt.getValue()) != 0)) {
        // Add Start 2019/01/10 K.Fujimoto QC#26861
        if (this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
            return SVC_TASK_STS.CLOSED;
        }
        // Add End   2019/01/10 K.Fujimoto QC#26861

        if (!isZeroChrg(fsrVisitAmt)) {
        // mod end 2017/02/13 CSA Defect#17109
            return SVC_TASK_STS.PENDING_CHARGE;
        }

        String svcDisptPndChrgTp = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_SVC_DISPT_PND_CHRG_TP, pMsg.glblCmpyCd.getValue());
        if (SVC_DISPT_PND_CHRG_TP_1.equals(svcDisptPndChrgTp)) {
            return SVC_TASK_STS.CLOSED;
        }

        if (SVC_DISPT_PND_CHRG_TP_2.equals(svcDisptPndChrgTp)) {
            for (Map<String, Object> billTp : billTpList) {
                if ((ZYPConstant.FLG_ON_Y.equals((String) billTp.get("LBOR_CHRG_FLG"))) || (ZYPConstant.FLG_ON_Y.equals((String) billTp.get("PRT_CHRG_FLG"))) || (ZYPConstant.FLG_ON_Y.equals((String) billTp.get("EXP_CHRG_FLG")))
                        || (ZYPConstant.FLG_ON_Y.equals((String) billTp.get("DRUM_CHRG_FLG")))) {
                    return SVC_TASK_STS.PENDING_CHARGE;
                }
            }
        }

        if (SVC_DISPT_PND_CHRG_TP_3.equals(svcDisptPndChrgTp)) {
            for (Map<String, Object> billTp : billTpList) {
                if ((ZYPConstant.FLG_ON_Y.equals((String) billTp.get("LBOR_CHRG_FLG")) && existsLborInFsr(pMsg)) || (ZYPConstant.FLG_ON_Y.equals((String) billTp.get("PRT_CHRG_FLG")) && existsPrtInFsr(pMsg))
                        || (ZYPConstant.FLG_ON_Y.equals((String) billTp.get("EXP_CHRG_FLG")) && existsExpInFsr(pMsg)) || (ZYPConstant.FLG_ON_Y.equals((String) billTp.get("DRUM_CHRG_FLG")) && existsDrumInFsr(pMsg))) {
                    return SVC_TASK_STS.PENDING_CHARGE;
                }
            }
        }

        // add start 2015/12/16 CSA Defect#978
        if (SVC_DISPT_PND_CHRG_TP_4.equals(svcDisptPndChrgTp)) {
            return SVC_TASK_STS.PENDING_CHARGE;
        }
        // add end 2015/12/16 CSA Defect#978
        return SVC_TASK_STS.CLOSED;
    }

    private String getMachDownFlg(NSZC005001PMsg pMsg) {
        int size = pMsg.xxTmEventList.getValidCount();
        if (size < 1) {
            return ZYPConstant.FLG_OFF_N;
        }

        List<NSZC005001_xxTmEventListPMsg> sortList = new ArrayList<NSZC005001_xxTmEventListPMsg>();
        for (int i = 0; i < size; i++) {
            NSZC005001_xxTmEventListPMsg dtlPMsg = (NSZC005001_xxTmEventListPMsg) pMsg.xxTmEventList.no(i).clone();
            sortList.add(dtlPMsg);
        }
        Collections.sort(sortList, new Comparator<NSZC005001_xxTmEventListPMsg>() {
            public int compare(NSZC005001_xxTmEventListPMsg line1, NSZC005001_xxTmEventListPMsg line2) {
                int compared;
                compared = line1.svcTmEventFromDt.getValue().compareTo(line2.svcTmEventFromDt.getValue());
                if (compared != 0) {
                    return compared;
                }
                compared = line1.svcTmEventFromTm.getValue().compareTo(line2.svcTmEventFromTm.getValue());
                if (compared != 0) {
                    return compared;
                }
                return 0;
            }
        });
        return (String) sortList.get(sortList.size() - 1).machDownFlg.getValue();
    }

    private String getMaxDate(String date1, String date2) {
        if (ZYPCommonFunc.hasValue(date1) && ZYPCommonFunc.hasValue(date2)) {
            if (date1.compareTo(date2) < 0) {
                return date2;
            } else {
                return date1;
            }
        } else if (ZYPCommonFunc.hasValue(date1)) {
            return date1;
        } else if (ZYPCommonFunc.hasValue(date2)) {
            return date2;
        }
        return "";
    }

    private BigDecimal getResponseTime(NSZC005001PMsg pMsg, SVC_TASKTMsg svcTaskTMsg, boolean repairFlg) {
        BigDecimal svcRspTm = null;
        String arvDt = null;
        String arvTm = null;

        // START 2022/02/10 [QC#57338, ADD]
        // isResponseTimeTargetCall
        if (!repairFlg && !isResponseTimeTarget(pMsg.glblCmpyCd.getValue(), svcTaskTMsg.dsSvcCallTpCd.getValue())) {
            return svcRspTm;
        }
        // END   2022/02/10 [QC#57338, ADD]
        
        // Arrival time
        // START 2022/02/10 [QC#57338, DEL]
//        if (!FSR_VISIT_NUM_01.equals(pMsg.fsrVisitNum.getValue())) {
//            FSR_VISITTMsg fsrVisitTMsg = getFsrVisitFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), FSR_VISIT_NUM_01);
//            if (!checkRtnCodeForSearch(pMsg, fsrVisitTMsg, new FSR_VISITTMsg())) {
//                return null;
//            }
//            // START 2016/08/26 [QC#13661, MOD]
////            if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitArvDt) || !ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitArvTm)) {
////                return BigDecimal.ZERO;
//            if (ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitArvDt) || !ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitArvTm)) {
//                arvDt = fsrVisitTMsg.fsrVisitArvDt.getValue();
//                arvTm = DEF_HHMMSS;
//            } else if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitArvDt) || !ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitArvTm)) {
//                FSR_VISIT_TM_EVENTTMsgArray fsrVisitTmEventTMsgArray = getFsrFsrVisitTmEventByFsrNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), FSR_VISIT_NUM_01, SVC_TM_EVENT.LABOR);
//                if (fsrVisitTmEventTMsgArray.getValidCount() > 0) {
//                    arvDt = fsrVisitTmEventTMsgArray.no(0).svcTmEventFromDt.getValue();
//                    arvTm = fsrVisitTmEventTMsgArray.no(0).svcTmEventFromTm.getValue();
//                }
//            // END 2016/08/26 [QC#13661, MOD]
//            } else {
//                arvDt = fsrVisitTMsg.fsrVisitArvDt.getValue();
//                arvTm = formatTm(fsrVisitTMsg.fsrVisitArvTm.getValue());
//            }
//        } else {
        // END    2022/02/10 [QC#57338, DEL]
            // START 2016/08/26 [QC#13661, MOD]
            if (hasValue(pMsg.fsrVisitArvDt)) {
                arvDt = pMsg.fsrVisitArvDt.getValue();
                arvTm = formatTm(pMsg.fsrVisitArvTm.getValue());
            } else {
                FSR_VISIT_TM_EVENTTMsgArray fsrVisitTmEventTMsgArray = getFsrFsrVisitTmEventByFsrNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), pMsg.fsrVisitNum.getValue(), SVC_TM_EVENT.LABOR);
                if (fsrVisitTmEventTMsgArray.getValidCount() > 0) {
                    arvDt = fsrVisitTmEventTMsgArray.no(0).svcTmEventFromDt.getValue();
                    arvTm = fsrVisitTmEventTMsgArray.no(0).svcTmEventFromTm.getValue();
                }
            }
            // END 2016/08/26 [QC#13661, MOD]
        // START 2022/02/10 [QC#57338, DEL]
//        }
        // END   2022/02/10 [QC#57338, DEL]

        // get Available Time
        // Del Start 2019/01/10 K.Fujimoto QC#26861
        // Dead Logic.
        // String avalFromHourMn = "";
        // String avalToHourMn = "";
        // Del End   2019/01/10 K.Fujimoto QC#26861
        NSXC001001PMsg pmsg = new NSXC001001PMsg();
        setValue(pmsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(pmsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValue(pmsg.slsDt, svcTaskTMsg.svcTaskRcvDt.getValue());

// mod start 2016/03/03 CSA Defect#3390
//        if (pmsg.xxContrList != null && pmsg.xxContrList.length() > 0) {
//            avalFromHourMn = pmsg.xxContrList.no(0).svcCovFromHourMn.getValue();
//            avalToHourMn = pmsg.xxContrList.no(0).svcCovToHourMn.getValue();
//        }
        // Del Start 2019/01/10 K.Fujimoto QC#26861
        // Dead Logic.
        // avalFromHourMn = ZYPCodeDataUtil.getVarCharConstValue(SVC_BIZ_FROM_HOUR, pMsg.glblCmpyCd.getValue());
        // avalToHourMn = ZYPCodeDataUtil.getVarCharConstValue(SVC_BIZ_THRU_HOUR, pMsg.glblCmpyCd.getValue());
        // Del End   2019/01/10 K.Fujimoto QC#26861

        // Convert Date/Time
        String ctryCd = null;
        String postCd = null;
        Map<String, Object> shipToInfoMap = getShipToInfo(pMsg.glblCmpyCd.getValue(), svcTaskTMsg.svcTaskNum.getValue());
        if (shipToInfoMap != null) {
            ctryCd = (String) shipToInfoMap.get("CTRY_CD");
            postCd = (String) shipToInfoMap.get("POST_CD");
        }

        // mod start 2016/09/07 CSA Defect#13661
//        String lateStartDt = null;
//        String lateStartTm = null;
//        String lateStartTs = svcTaskTMsg.lateStartTs.getValue();
//        String cnvtLateStartTs = null;
//        SvcTimeZoneInfo tzLateStartTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, lateStartTs, ctryCd, postCd);
//        if (tzLateStartTs != null) {
//            cnvtLateStartTs = tzLateStartTs.getDateTime();
//            lateStartDt = cnvtLateStartTs.substring(0, LEN_DT);
//            lateStartTm = cnvtLateStartTs.substring(LEN_DT, LEN_DT_TM);
//        }
        String erlStartDt = null;
        String erlStartTm = null;
        String erlStartTs = svcTaskTMsg.erlStartTs.getValue();
        String cnvtErlStartTs = null;
        SvcTimeZoneInfo tzErlStartTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, erlStartTs, ctryCd, postCd);
        if (tzErlStartTs != null) {
            cnvtErlStartTs = tzErlStartTs.getDateTime();
            erlStartDt = cnvtErlStartTs.substring(0, LEN_DT);
            erlStartTm = cnvtErlStartTs.substring(LEN_DT, LEN_DT_TM);
        }
        // mod end 2016/09/07 CSA Defect#13661

        StringBuilder arvTsBuilder = new StringBuilder();
        arvTsBuilder.append(arvDt);
        arvTsBuilder.append(arvTm);
        String arvTs = arvTsBuilder.toString();
        String cnvtArvTs = null;
        SvcTimeZoneInfo tzArvTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, arvTs, ctryCd, postCd);
        if (tzArvTs != null) {
            cnvtArvTs = tzArvTs.getDateTime();
            arvDt = cnvtArvTs.substring(0, LEN_DT);
            arvTm = cnvtArvTs.substring(LEN_DT, LEN_DT_TM);
        }

        String cpltDt = null;
        String cpltTm = null;
        StringBuilder cpltTsBuilder = new StringBuilder();
        cpltTsBuilder.append(svcTaskTMsg.svcTaskCpltDt.getValue());
        cpltTsBuilder.append(svcTaskTMsg.svcTaskCpltTm.getValue());
        String cpltTs = cpltTsBuilder.toString();
        String cnvtCpltTs = null;
        SvcTimeZoneInfo tzCpltTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, cpltTs, ctryCd, postCd);
        if (tzCpltTs != null) {
            cnvtCpltTs = tzCpltTs.getDateTime();
            cpltDt = cnvtCpltTs.substring(0, LEN_DT);
            cpltTm = cnvtCpltTs.substring(LEN_DT, LEN_DT_TM);
        }

        Long diffTime = 0L;
        // mod start 2016/09/07 CSA Defect#13661
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstrFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (!checkRtnCodeForSearch(pMsg, svcMachMstrTMsg, new SVC_MACH_MSTRTMsg())) {
            return svcRspTm;
        }
        if (repairFlg) {
//            diffTime = NSXC001001GetRspTime.getRspTime(pMsg.glblCmpyCd.getValue(), arvDt, arvTm, svcTaskTMsg.svcTaskCpltDt.getValue(), svcTaskTMsg.svcTaskCpltTm.getValue(), avalFromHourMn, avalToHourMn);
//            diffTime = NSXC001001GetRspTime.getRspTime(pMsg.glblCmpyCd.getValue(), arvDt, arvTm, cpltDt, cpltTm, avalFromHourMn, avalToHourMn);
            diffTime = NSXC001001GetRspTime.getRspTime(pMsg.glblCmpyCd.getValue(), arvDt, arvTm, cpltDt, cpltTm, svcMachMstrTMsg);
        } else {
//            diffTime = NSXC001001GetRspTime.getRspTime(pMsg.glblCmpyCd.getValue(), svcTaskTMsg.svcTaskRcvDt.getValue(), svcTaskTMsg.svcTaskRcvTm.getValue(), arvDt, arvTm, avalFromHourMn, avalToHourMn);
//            diffTime = NSXC001001GetRspTime.getRspTime(pMsg.glblCmpyCd.getValue(), lateStartDt, lateStartTm, arvDt, arvTm, avalFromHourMn, avalToHourMn);
            diffTime = NSXC001001GetRspTime.getRspTime(pMsg.glblCmpyCd.getValue(), erlStartDt, erlStartTm, arvDt, arvTm, svcMachMstrTMsg);
        }
        // mod end 2016/09/07 CSA Defect#13661
// mod end 2016/03/03 CSA Defect#3390

        // START 2020/05/29 K.Kitachi [QC#56645, ADD]
        StringBuffer sb = new StringBuffer();
        sb.append("NSZC0050 getResponseTime:");
        sb.append("svcTaskNum:").append(svcTaskTMsg.svcTaskNum.getValue());
        sb.append(",fsrNum:").append(pMsg.fsrNum.getValue());
        sb.append(",fsrVisitNum:").append(pMsg.fsrVisitNum.getValue());
        sb.append(",fsrVisitArvDt:").append(pMsg.fsrVisitArvDt.getValue());
        sb.append(",fsrVisitArvTm:").append(pMsg.fsrVisitArvTm.getValue());
        sb.append(",svcMachMstrPk:").append(pMsg.svcMachMstrPk.getValue());
        sb.append(",ctryCd:").append(ctryCd);
        sb.append(",postCd:").append(postCd);
        sb.append(",erlStartTs:").append(erlStartTs);
        sb.append(",arvTs:").append(arvTs);
        sb.append(",cpltTs:").append(cpltTs);
        sb.append(",cnvtErlStartTs:").append(cnvtErlStartTs);
        sb.append(",cnvtArvTs:").append(cnvtArvTs);
        sb.append(",cnvtCpltTs:").append(cnvtCpltTs);
        sb.append(",diffTime:").append(diffTime);
        // END 2020/05/29 K.Kitachi [QC#56645, ADD]

        if (diffTime == null) {
            diffTime = 0L;
        }
        svcRspTm = BigDecimal.valueOf(diffTime / RSP_TM_VAL).setScale(0, RoundingMode.CEILING);
        if (svcRspTm.intValue() > MAX_RSP_TM) {
            svcRspTm = new BigDecimal(MAX_RSP_TM);
        }

        // START 2020/05/29 K.Kitachi [QC#56645, ADD]
        if (!hasValue(svcRspTm) || BigDecimal.ZERO.compareTo(svcRspTm) == 0) {
            sb.append(",svcRspTm:").append(svcRspTm);
            S21InfoLogOutput.println(sb.toString());
        }
        // END 2020/05/29 K.Kitachi [QC#56645, ADD]

        return svcRspTm;
    }

    // ************************************
    // DB access findByKey
    // ************************************
    private CCYTMsg getCcyFindByKey(String glblCmpyCd, String invCcyCd) {
        CCYTMsg paramTMsg = new CCYTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.ccyCd, invCcyCd);
        return (CCYTMsg) S21CacheTBLAccessor.findByKey(paramTMsg);
    }

    private INVTYTMsg getInvtyFindByKey(String glblCmpyCd, String mdseCd, String invtyLocCd) {
        INVTYTMsg paramTMsg = new INVTYTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.mdseCd, mdseCd);
        setValue(paramTMsg.invtyLocCd, invtyLocCd);
        setValue(paramTMsg.locStsCd, LOC_STS.DC_STOCK);
        setValue(paramTMsg.stkStsCd, STK_STS.GOOD);
        return (INVTYTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    private MDSETMsg getMdseFindByKey(String glblCmpyCd, String mdseCd) {
        MDSETMsg paramTMsg = new MDSETMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.mdseCd, mdseCd);
        return (MDSETMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    private MDSE_STORE_PKGTMsg getMdseStorePkgFindByKey(String glblCmpyCd, String mdseCd, String pkgUomCd) {
        MDSE_STORE_PKGTMsg paramTMsg = new MDSE_STORE_PKGTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.mdseCd, mdseCd);
        setValue(paramTMsg.pkgUomCd, pkgUomCd);
        return (MDSE_STORE_PKGTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    private FSRTMsg getFsrFindByKeyForUpdate(String glblCmpyCd, String fsrNum) {
        FSRTMsg paramTMsg = new FSRTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.fsrNum, fsrNum);
        return (FSRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }

    // add start 2015/12/16 CSA Defect#978
    private FSRTMsg getFsrFindByKey(String glblCmpyCd, String fsrNum) {
        FSRTMsg paramTMsg = new FSRTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.fsrNum, fsrNum);
        return (FSRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }
    // add end 2015/12/16 CSA Defect#978

    private FSR_EXPTMsg getFsrExpFindByKeyForUpdate(String glblCmpyCd, BigDecimal fsrExpPk) {
        FSR_EXPTMsg paramTMsg = new FSR_EXPTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.fsrExpPk, fsrExpPk);
        return (FSR_EXPTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }
    private FSR_ISTL_CHK_LISTTMsg getFsrIstlChkFindByKeyForUpdate(String glblCmpyCd, BigDecimal fsrIstlChkPk) {
        FSR_ISTL_CHK_LISTTMsg paramTMsg = new FSR_ISTL_CHK_LISTTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.fsrIstlChkListPk, fsrIstlChkPk);
        return (FSR_ISTL_CHK_LISTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }
    private FSR_VISITTMsg getFsrVisitFindByKey(String glblCmpyCd, String fsrNum, String fsrVisitNum) {
        FSR_VISITTMsg paramTMsg = new FSR_VISITTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.fsrNum, fsrNum);
        setValue(paramTMsg.fsrVisitNum, fsrVisitNum);
        return (FSR_VISITTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    private FSR_VISITTMsg getFsrVisitFindByKeyForUpdate(String glblCmpyCd, String fsrNum, String fsrVisitNum) {
        FSR_VISITTMsg paramTMsg = new FSR_VISITTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.fsrNum, fsrNum);
        setValue(paramTMsg.fsrVisitNum, fsrVisitNum);
        // START 2018/01/15 U.Kim [QC#19702, MOD]
        // return (FSR_VISITTMsg)
        // S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
        FSR_VISITTMsg fsrVisitTMsg = null;
        try {
            fsrVisitTMsg = (FSR_VISITTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
        } catch (EZDDBRecordLockedException e) {
            fsrVisitTMsg = getfsrVisitTMsgForUpdateWait(paramTMsg);
        }
        return fsrVisitTMsg;
        // END 2018/01/15 U.Kim [QC#19702, MOD]
    }
    // START 2018/01/15 U.Kim [QC#19702, ADD]
    private FSR_VISITTMsg getfsrVisitTMsgForUpdateWait(FSR_VISITTMsg fsrVisitTMsg) {
        try {
            return (FSR_VISITTMsg) EZDTBLAccessor.findByKeyForUpdateWait(fsrVisitTMsg, this.waitSecUpdTaskCompletion);
        } catch (EZDDBRecordLockedException e) {
            return null;
        }
    }

    // END 2018/01/15 U.Kim [QC#19702, ADD]

    private FSR_VISIT_TASKTMsg getFsrVisiTaskFindByKey(String glblCmpyCd, String fsrNum, String fsrVisitNum, String svcTaskNum) {
        FSR_VISIT_TASKTMsg paramTMsg = new FSR_VISIT_TASKTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.fsrNum, fsrNum);
        setValue(paramTMsg.fsrVisitNum, fsrVisitNum);
        setValue(paramTMsg.svcTaskNum, svcTaskNum);
        return (FSR_VISIT_TASKTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    private FSR_VISIT_TASKTMsg getFsrVisiTaskFindByKeyForUpdate(String glblCmpyCd, String fsrNum, String fsrVisitNum, String svcTaskNum) {
        FSR_VISIT_TASKTMsg paramTMsg = new FSR_VISIT_TASKTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.fsrNum, fsrNum);
        setValue(paramTMsg.fsrVisitNum, fsrVisitNum);
        setValue(paramTMsg.svcTaskNum, svcTaskNum);
        return (FSR_VISIT_TASKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }

    private FSR_VISIT_TM_EVENTTMsg getFsrVisiTmEventFindByKeyForUpdate(String glblCmpyCd, BigDecimal fsrVisitTmEventPk) {
        FSR_VISIT_TM_EVENTTMsg paramTMsg = new FSR_VISIT_TM_EVENTTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.fsrVisitTmEventPk, fsrVisitTmEventPk);
        return (FSR_VISIT_TM_EVENTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }

    private FSR_USGTMsg getFsrUsgtFindByKeyForUpdate(String glblCmpyCd, BigDecimal fsrUsgPk) {
        FSR_USGTMsg paramTMsg = new FSR_USGTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.fsrUsgPk, fsrUsgPk);
        return (FSR_USGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }

    private SVC_BILL_TPTMsg getSvcBillTpFindByKey(String glblCmpyCd, String svcBillTpCd) {
        SVC_BILL_TPTMsg paramTMsg = new SVC_BILL_TPTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.svcBillTpCd, svcBillTpCd);
        return (SVC_BILL_TPTMsg) S21CodeTableAccessor.findByKey(paramTMsg);
    }
    private DS_SVC_CALL_TPTMsg getDsSvcCallTpFindByKey(String glblCmpyCd, String dsSvcCallTpCd) {
        DS_SVC_CALL_TPTMsg paramTMsg = new DS_SVC_CALL_TPTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.dsSvcCallTpCd, dsSvcCallTpCd);
        return (DS_SVC_CALL_TPTMsg) S21CodeTableAccessor.findByKey(paramTMsg);
    }
    private SVC_MACH_MSTRTMsg getSvcMachMstrFindByKey(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg paramTMsg = new SVC_MACH_MSTRTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    private SVC_PHYS_MTR_READTMsg getSvcPhysMtrReadFindByKeyForUpdate(String glblCmpyCd, BigDecimal svcPhysMtrReadPk) {
        SVC_PHYS_MTR_READTMsg paramTMsg = new SVC_PHYS_MTR_READTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.svcPhysMtrReadPk, svcPhysMtrReadPk);
        return (SVC_PHYS_MTR_READTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }

    private SVC_TASKTMsg getSvcTaskFindByKey(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg paramTMsg = new SVC_TASKTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.svcTaskNum, svcTaskNum);
        return (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    private SVC_TASKTMsg getSvcTaskFindByKeyForUpdate(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg paramTMsg = new SVC_TASKTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.svcTaskNum, svcTaskNum);
        // START 2018/01/15 U.Kim [QC#19702, MOD]
        // return (SVC_TASKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
        SVC_TASKTMsg svcTaskTMsg = null;
        try {
            svcTaskTMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
        } catch (EZDDBRecordLockedException e) {
            svcTaskTMsg = getSvcTaskTMsgForUpdateWait(paramTMsg);
        }
        return svcTaskTMsg;
        // END 2018/01/15 U.Kim [QC#19702, MOD]
    }
    // START 2018/01/15 U.Kim [QC#19702, ADD]
    private SVC_TASKTMsg getSvcTaskTMsgForUpdateWait(SVC_TASKTMsg svcTaskTMsg){
        try{
            return (SVC_TASKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(svcTaskTMsg, this.waitSecUpdTaskCompletion);
        }catch(EZDDBRecordLockedException e){
            return null;
        }
    }
    // END 2018/01/15 U.Kim [QC#19702, ADD]

    private SVC_TM_EVENTTMsg getSvcTmEventFindByKey(String glblCmpyCd, String svcTmEventCd) {
        SVC_TM_EVENTTMsg paramTMsg = new SVC_TM_EVENTTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.svcTmEventCd, svcTmEventCd);
        return (SVC_TM_EVENTTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    // add start 2015/12/16 CSA Defect#978
    private FSR_CHRGTMsg getFsrChrgFindByKeyForUpdate(String glblCmpyCd, String fsrNum) {
        FSR_CHRGTMsg paramTMsg = new FSR_CHRGTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.fsrNum, fsrNum);
        return (FSR_CHRGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }

    private FSR_CHRG_DTLTMsg getFsrChrgDtlFindByKeyForUpdate(String glblCmpyCd, BigDecimal fsrChrgDtlPk) {
        FSR_CHRG_DTLTMsg paramTMsg = new FSR_CHRG_DTLTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.fsrChrgDtlPk, fsrChrgDtlPk);
        return (FSR_CHRG_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }
    // add end 2015/12/16 CSA Defect#978

    // ************************************
    // DB access findByCondition
    // ************************************
    //Del Start 2018/11/28 K.Fujimoto QC#28647
    // private FSR_USGTMsgArray getFsrUsgListNotSvcTaskNum(String glblCmpyCd, String fsrNum, String svcTaskNum) {
    //     FSR_USGTMsg tMsg = new FSR_USGTMsg();
    //     tMsg.setSQLID("001");
    //     tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
    //     tMsg.setConditionValue("fsrNum01", fsrNum);
    //     tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
    //     return (FSR_USGTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    // }
    //Del End   2018/11/28 K.Fujimoto QC#28647


    private FSR_EXPTMsgArray getFsrExpListByFsrNum(String glblCmpyCd, String fsrNum) {
        FSR_EXPTMsg tMsg = new FSR_EXPTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        return (FSR_EXPTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private FSR_USGTMsgArray getFsrUsgListByFsrNum(String glblCmpyCd, String fsrNum) {
        FSR_USGTMsg tMsg = new FSR_USGTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        return (FSR_USGTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private FSR_USGTMsgArray getFsrUsgListBySvcTaskNum(String glblCmpyCd, String fsrNum, String svcTaskNum) {
        FSR_USGTMsg tMsg = new FSR_USGTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        return (FSR_USGTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }
    private FSR_ISTL_CHK_LISTTMsgArray getIstlChkListBySvcTaskNum(String glblCmpyCd, String fsrNum, String svcTaskNum) {
        FSR_ISTL_CHK_LISTTMsg tMsg = new FSR_ISTL_CHK_LISTTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        return (FSR_ISTL_CHK_LISTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private FSR_VISITTMsgArray getFsrVisit(String glblCmpyCd, String fsrNum, String svcTaskNum) {
        FSR_VISITTMsg tMsg = new FSR_VISITTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        return (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    // add start 2015/12/16 CSA Defect#978
    private FSR_VISITTMsgArray getFsrVisitForUpdate(String glblCmpyCd, String fsrNum, String svcTaskNum) {
        FSR_VISITTMsg tMsg = new FSR_VISITTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        return (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    private FSR_CHRG_DTLTMsgArray getFsrChrgDtlListByFsrNumForUpdate(String glblCmpyCd, String fsrNum) {
        FSR_CHRG_DTLTMsg tMsg = new FSR_CHRG_DTLTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        return (FSR_CHRG_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    private FSR_VISITTMsgArray getFsrVisitByFsrNum(String glblCmpyCd, String fsrNum) {
        FSR_VISITTMsg tMsg = new FSR_VISITTMsg();
        // mod start 2016/09/12 CSA Defect#14206
//        tMsg.setSQLID("004");
        tMsg.setSQLID("006");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        tMsg.setConditionValue("fsrVisitStsCd01", SVC_TASK_STS.CANCELLED);
        // mod end 2016/09/12 CSA Defect#14206
        return (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private SVC_TASKTMsgArray getSvcTaskByFsrNumForUpdate(String glblCmpyCd, String fsrNum) {
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        tMsg.setSQLID("005");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        return (SVC_TASKTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }
    // START 2017/12/04 U.Kim [QC#19907, ADD]
    private SVC_TASKTMsgArray getSvcTaskByFsrNum(String glblCmpyCd, String fsrNum) {
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        tMsg.setSQLID("005");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        return (SVC_TASKTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }
    // END 2017/12/04 U.Kim [QC#19907, ADD]

    private FSR_VISIT_TM_EVENTTMsgArray getFsrFsrVisitTmEventBySvcTaskNum(String glblCmpyCd, String svcTaskNum) {
        FSR_VISIT_TM_EVENTTMsg tMsg = new FSR_VISIT_TM_EVENTTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        return (FSR_VISIT_TM_EVENTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }
    // del start 2016/02/22 CSA Defect#2695
//    private FSR_VISIT_TM_EVENTTMsgArray getFsrFsrVisitTmEventByFsrNum(String glblCmpyCd, String fsrNum) {
//        FSR_VISIT_TM_EVENTTMsg tMsg = new FSR_VISIT_TM_EVENTTMsg();
//        tMsg.setSQLID("001");
//        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        tMsg.setConditionValue("fsrNum01", fsrNum);
//        return (FSR_VISIT_TM_EVENTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
//    }
    // del end 2016/02/22 CSA Defect#2695

    // add end 2015/12/16 CSA Defect#978

    // add start 2016/02/08 CSA Defect#2863, 2016/07/01 CSA Defect#11185
    private String getMblIntfcProcCd(String glblCmpyCd, String svcTaskNum, String inMblIntfcProcCd) {
        FSR_EVENTTMsg tMsg = new FSR_EVENTTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        tMsg.setConditionValue("mblIntfcProcCd01", MBL_INTFC_PROC.PROCESSED);
        tMsg.setConditionValue("mblIntfcProcCd02", MBL_INTFC_PROC.ERROR);
        int cnt = S21ApiTBLAccessor.count(tMsg);
        String mblIntfcProcCd = MBL_INTFC_PROC.NO_NEED;
        if (cnt > 0) {
            mblIntfcProcCd = inMblIntfcProcCd;
        }
        return mblIntfcProcCd;
    }
    // add end 2016/02/08 CSA Defect#2863, 2016/07/01 CSA Defect#11185

    // Del Start 2019/01/10 K.Fujimoto QC#26861
    // Dead Logic.
    // add 2016/04/20 CSA Defect#4469
    // private FSR_USG_SRVYTMsgArray getFsrUsgSrvyList(String glblCmpyCd, BigDecimal fsrUsgPk) {
    //     FSR_USG_SRVYTMsg tMsg = new FSR_USG_SRVYTMsg();
    //     tMsg.setSQLID("001");
    //     tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
    //     tMsg.setConditionValue("fsrUsgPk01", fsrUsgPk);
    //     return (FSR_USG_SRVYTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    // }
    // add 2016/04/20 CSA Defect#4469
    // Del End   2019/01/10 K.Fujimoto QC#26861
    private FSR_USG_SRVYTMsgArray getFsrUsgSrvyListForUpdate(String glblCmpyCd, BigDecimal fsrUsgPk) {
        FSR_USG_SRVYTMsg tMsg = new FSR_USG_SRVYTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrUsgPk01", fsrUsgPk);
        return (FSR_USG_SRVYTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }
    // START 2016/08/26 [QC#13661, ADD]
    private FSR_VISIT_TM_EVENTTMsgArray getFsrFsrVisitTmEventByFsrNum(String glblCmpyCd, String fsrNum, String fsrVisitNum, String svcTmEventCd) {
        FSR_VISIT_TM_EVENTTMsg tMsg = new FSR_VISIT_TM_EVENTTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        tMsg.setConditionValue("fsrVisitNum01", fsrVisitNum);
        tMsg.setConditionValue("svcTmEventCd01", svcTmEventCd);
        return (FSR_VISIT_TM_EVENTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }
    // END 2016/08/26 [QC#13661, ADD]

    // ************************************
    // DB access SSM
    // ************************************
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getBillTp(NSZC005001PMsg pMsg, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("svcTaskNum", svcTaskNum);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBillTp", paramMap);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getCallTp(NSZC005001PMsg pMsg, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcTaskNum", svcTaskNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getCallTp", paramMap);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getFollUpInfo(NSZC005001PMsg pMsg, NSZC005001_xxVisitTaskListPMsg dtlPMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        // mod Start 2016/06/07 CSA Defect#9218
        paramMap.put("svcPblmCrctTpCd", dtlPMsg.svcPblmCrctTpCd.getValue());
        // mod End 2016/06/07 CSA Defect#9218
        return (Map<String, Object>) ssmBatchClient.queryObject("getFollUpInfo", paramMap);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getFsrExpAmt(NSZC005001PMsg pMsg, String fsrVisitNum, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("fsrVisitNum", fsrVisitNum);
        paramMap.put("svcTaskNum", svcTaskNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getFsrExpAmt", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<BigDecimal> getFsrExpPkListForDelete(NSZC005001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("fsrVisitNum", pMsg.fsrVisitNum.getValue());
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getFsrExpPkListForDelete", paramMap);
    }
    @SuppressWarnings("unchecked")
    private List<BigDecimal> getFsrIstlChkListForDelete(NSZC005001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("svcTaskNum", this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue());
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getFsrIstlChkPkListForDelete", paramMap);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getFsrUsgAmt(NSZC005001PMsg pMsg, String fsrVisitNum, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("fsrVisitNum", fsrVisitNum);
        paramMap.put("svcTaskNum", svcTaskNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getFsrUsgAmt", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<BigDecimal> getFsrUsgPkListForDelete(NSZC005001PMsg pMsg, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("svcTaskNum", svcTaskNum);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getFsrUsgPkListForDelete", paramMap);
    }

    private FSR_VISITTMsg getFsrVisitAmt(NSZC005001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        return (FSR_VISITTMsg) ssmBatchClient.queryObject("getFsrVisitAmt", paramMap);
    }

    private int getFsrVisitTmEventLborCnt(NSZC005001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("inclLborTmFlgY", ZYPConstant.FLG_ON_Y);
        return (Integer) ssmBatchClient.queryObject("getFsrVisitTmEventLborCnt", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<BigDecimal> getFsrVisitTmEventPkListForDelete(NSZC005001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("fsrVisitNum", pMsg.fsrVisitNum.getValue());
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getFsrVisitTmEventPkListForDelete", paramMap);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getFsrVisitTotAmt(NSZC005001PMsg pMsg, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("svcTaskNum", svcTaskNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getFsrVisitTotAmt", paramMap);
    }

    private String getIwrRgtnCond(NSZC005001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk);
        return (String) ssmBatchClient.queryObject("getIwrRgtnCond", paramMap);
    }

    private BigDecimal getRecallCnt(NSZC005001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        return (BigDecimal) ssmBatchClient.queryObject("getRecallCnt", paramMap);
    }
    private String getMtrReqMdlFlg(NSZC005001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        return (String) ssmBatchClient.queryObject("getMtrReqMdlFlg", paramMap);
    }
    @SuppressWarnings("unchecked")
    private Map<String, Object> getPrevTotMtr(NSZC005001PMsg pMsg, NSZC005001_xxMtrReadListPMsg dtlPMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        paramMap.put("svcPhysMtrPk", dtlPMsg.svcPhysMtrPk.getValue());
        paramMap.put("dsMtrReadTpGrpCdService", DS_MTR_READ_TP_GRP.SERVICE_READS);
        paramMap.put("mtrReadDt", dtlPMsg.mtrReadDt.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("vldMtrFlgY", ZYPConstant.FLG_ON_Y);
        return (Map<String, Object>) ssmBatchClient.queryObject("getPrevTotMtr", paramMap);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getPrtInfo(NSZC005001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk);
        return (Map<String, Object>) ssmBatchClient.queryObject("getPrtInfo", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<SVC_SUPPL_TASKTMsg> getSupplTask(NSZC005001PMsg pMsg, NSZC005001_xxTmEventListPMsg dtlPMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        // START 2021/09/10 R.Cabral [QC#58979, DEL]
        // paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        // END   2021/09/10 R.Cabral [QC#58979, DEL]
        paramMap.put("svcTaskNum", dtlPMsg.svcTaskNum.getValue());
        paramMap.put("svcItrptFlgY", ZYPConstant.FLG_ON_Y);
        // START 2021/09/10 R.Cabral [QC#58979, ADD]
        paramMap.put("exclLborFlg", ZYPConstant.FLG_ON_Y);
        // END   2021/09/10 R.Cabral [QC#58979, ADD]
        return (List<SVC_SUPPL_TASKTMsg>) ssmBatchClient.queryObjectList("getSupplTask", paramMap);
    }

    private int getSvcInvCnt(NSZC005001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("fsrVisitNum", pMsg.fsrVisitNum.getValue());
        return (Integer) ssmBatchClient.queryObject("getSvcInvCnt", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSvcPhysMtrReadList(NSZC005001PMsg pMsg, NSZC005001_xxMtrReadListPMsg dtlPMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        paramMap.put("svcPhysMtrPk", dtlPMsg.svcPhysMtrPk.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("fsrVisitNum", pMsg.fsrVisitNum.getValue());
        paramMap.put("vldMtrFlgY", ZYPConstant.FLG_ON_Y);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcPhysMtrReadList", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<String> getSvcTaskNumByStsCd(NSZC005001PMsg pMsg, String svcTaskNum, String[] svcTaskStsList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("svcTaskNum", svcTaskNum);
        paramMap.put("svcTaskStsList", svcTaskStsList);
        return (List<String>) ssmBatchClient.queryObjectList("getSvcTaskNumByStsCd", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<String> getSvcTaskNumByStsCdExSvcTaskNum(NSZC005001PMsg pMsg, String svcTaskNum, String[] svcTaskStsList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("svcTaskNumEx", svcTaskNum);
        paramMap.put("svcTaskStsList", svcTaskStsList);
        return (List<String>) ssmBatchClient.queryObjectList("getSvcTaskNumByStsCd", paramMap);
    }

    private String getSvcTaskRcvDt(NSZC005001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk);
        paramMap.put("dsSvcCallTpSystem", DS_SVC_CALL_TP.SYSTEMS);
        paramMap.put("svcTaskStsList", SVC_TASK_STS_LIST_NOT_COMPLETE);
        return (String) ssmBatchClient.queryObject("getSvcTaskRcvDt", paramMap);
    }

    // mod start 2015/12/17 CSA Defect#978
    private String getTocCd(String glblCmpyCd, String psnCd, String slsDt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("psnCd", psnCd);
//        paramMap.put("orgFuncRoleTpTech", ORG_FUNC_ROLE_TP.TECHNICIAN);
        paramMap.put("slsDt", slsDt);
        return (String) ssmBatchClient.queryObject("getTocCd", paramMap);
    }
    // mod end 2015/12/17 CSA Defect#978

    @SuppressWarnings("unchecked")
    private Map<String, Object> getTotMtrFlg(NSZC005001PMsg pMsg, NSZC005001_xxMtrReadListPMsg dtlPMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        if (hasValue(dtlPMsg.mtrLbCd)) {
            paramMap.put("mtrLbCd", dtlPMsg.mtrLbCd.getValue());
        } else {
            paramMap.put("svcPhysMtrPk", dtlPMsg.svcPhysMtrPk.getValue());
        }
        return (Map<String, Object>) ssmBatchClient.queryObject("getTotMtrFlg", paramMap);
    }

    // add start 2015/12/16 CSA Defect#978
    @SuppressWarnings("unchecked")
    private BigDecimal getExchRateData(String glblCmpyCd, String ccyCd, String slsDt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("ccyCd", ccyCd);
        paramMap.put("slsDt", slsDt);
        List<BigDecimal> exchRateList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getExchRateData", paramMap);
        if (!exchRateList.isEmpty()) {
            return exchRateList.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getLborFsrVisitTask(NSZC005001PMsg pMsg, String fsrNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        // add start 2016/09/12 CSA Defect#14206
        paramMap.put("svcTaskStsCd", SVC_TASK_STS.CANCELLED);
        // add end 2016/09/12 CSA Defect#14206
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getLborFsrVisitTask", paramMap);
    }

    private Integer getStdCcyDigit(String glblCmpyCd) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        return (Integer) ssmBatchClient.queryObject("getStdCcyDigit", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getDeleteTargetFsrChrgDtl(NSZC005001PMsg pMsg, List<BigDecimal> fsrChrgDtlPkList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrChrgDtlPkList", fsrChrgDtlPkList.toArray());
        // mod start 2016/04/28 CSA Defect#7737
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        // mod end 2016/04/28 CSA Defect#7737
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDeleteTargetFsrChrgDtl", paramMap);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getInvHeaderInfo(NSZC005001PMsg pMsg, String fsrNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", fsrNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getInvHeaderInfo", paramMap);
    }

    private String getMinVisitTech(String glblCmpyCd, String fsrNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("fsrNum", fsrNum);
        paramMap.put("cancelled", SVC_TASK_STS.CANCELLED);
        return (String) ssmBatchClient.queryObject("getMinVisitTech", paramMap);
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> getMinPoTask(String glblCmpyCd, String fsrNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("fsrNum", fsrNum);
        paramMap.put("cancelled", SVC_TASK_STS.CANCELLED);
        List<Map<String, String>> poList = ssmBatchClient.queryObjectList("getMinPoTask", paramMap);
        if (poList != null && !poList.isEmpty()) {
            return poList.get(0);
        }
        return null;
    }

    // START 2018/09/10 K.Kojima [QC#26149,ADD]
    @SuppressWarnings("unchecked")
    private Map<String, String> getPoFromTE(String glblCmpyCd, String fsrNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("fsrNum", fsrNum);
        Map<String, String> poFromTe = (Map<String, String>) ssmBatchClient.queryObject("getPoFromTE", paramMap);
        return poFromTe;
    }
    // END 2018/09/10 K.Kojima [QC#26149,ADD]

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getInvDtlInfo(String glblCmpyCd, String fsrNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("fsrNum", fsrNum);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getInvDtlInfo", paramMap);
    }

//    private String getModsEventCount(String glblCmpyCd, String svcTaskNum) {
    private List<Map<String, Object>> getModsEventCount(String glblCmpyCd, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        paramMap.put("modification", SVC_TM_EVENT.MODIFICATION);
        // START 2018/08/20 K.Kitachi [QC#27460, ADD]
        List<String> mdseItemRelnTpCdList = new ArrayList<String>();
        mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REFURBISHED);
        mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REMANUFACTURED);
        paramMap.put("mdseItemRelnTpCdList", mdseItemRelnTpCdList);
        // END 2018/08/20 K.Kitachi [QC#27460, ADD]
//        return (String) ssmBatchClient.queryObject("getModsEventCount", paramMap);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getModsEventCount", paramMap);
    }
    // add end 2015/12/16 CSA Defect#978

    // add start 2016/03/03 CSA Defect#3390
    @SuppressWarnings("unchecked")
    private Map<String, Object> getShipToInfo(String glblCmpyCd, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getShipToInfo", paramMap);
    }

    // add 2016/04/19 CSA Defect#7004
    @SuppressWarnings("unchecked")
    private Map<String, Object> getUpdateCustomerInfo(String glblCmpyCd, String fsrNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("fsrNum", fsrNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getUpdateCustomerInfo", paramMap);
    }
    // add start 2016/11/30 CSA Defect#15782
    @SuppressWarnings("unchecked")
    private List<BigDecimal> getLborTmEvent(String glblCmpyCd, String fsrNum, String fsrVisitNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("fsrNum", fsrNum);
        paramMap.put("fsrVisitNum", fsrVisitNum);
        paramMap.put("lborEvent", ZYPConstant.FLG_ON_Y);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getFsrVisitTmEvent", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<BigDecimal> getTrvlTmEvent(String glblCmpyCd, String fsrNum, String fsrVisitNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("fsrNum", fsrNum);
        paramMap.put("fsrVisitNum", fsrVisitNum);
        paramMap.put("trvlEvent", ZYPConstant.FLG_ON_Y);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getFsrVisitTmEvent", paramMap);
    }
    // add end 2016/11/30 CSA Defect#15782

    // START 2017/10/18 U.Kim [QC#21794, ADD]
    private List<BigDecimal> getSvcModStsBySvcTaskNumAndSvcMachMstrPk(String glblCmpyCd, BigDecimal svcMachMstrPk, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        paramMap.put("svcTaskNum", svcTaskNum);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcModStsBySvcTaskNumAndSvcMachMstrPk", paramMap);
    }
    // END 2017/10/18 U.Kim [QC#21794, ADD]
    // START 2017/12/04 U.Kim [QC#19907, ADD]
    private Map<String, String> getUserIdNm(String glblCmpyCd, String fsrNum) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("fsrNum", fsrNum);
        paramMap.put("fsrVisitNum", FSR_VISIT_NUM_01);
        return  (Map<String, String>) ssmBatchClient.queryObject("getUserIdNm", paramMap);
    }
    // END 2017/12/04 U.Kim [QC#19907, ADD]

    // ************************************
    // Error Handling
    // ************************************
    /**
     * checkRtnCodeForSearch
     * @param ezdTMsg EZDTMsg
     * @param tblTMsg EZDTMsg
     * @return boolean
     */
    private boolean checkRtnCodeForSearch(NSZC005001PMsg pMsg, EZDTMsg ezdTMsg, EZDTMsg tblTMsg) {
        // check error code
        // mod start 2015/12/09 CSA Defect#1708
        //if (ezdTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(ezdTMsg.getReturnCode())) {
        if (ezdTMsg == null) {
        // mod end 2015/12/09 CSA Defect#1708
            String errCd = null;

            if (tblTMsg instanceof SVC_TASKTMsg) {
                errCd = NSZM0079E;
            } else if (tblTMsg instanceof FSRTMsg) {
                errCd = NSZM0181E;
            } else if (tblTMsg instanceof FSR_VISITTMsg) {
                errCd = NSZM0182E;
            } else if (tblTMsg instanceof FSR_USGTMsg) {
                errCd = NSZM0345E;
            } else if (tblTMsg instanceof CCYTMsg) {
                errCd = NSZM0099E;
            } else if (tblTMsg instanceof MDSETMsg) {
                errCd = NSZM0105E;
            } else if (tblTMsg instanceof INVTYTMsg) {
                errCd = NSZM0753E;
            } else if (tblTMsg instanceof MDSE_STORE_PKGTMsg) {
                errCd = NSZM0754E;
            } else if (tblTMsg instanceof SVC_BILL_TPTMsg) {
                errCd = NSZM0755E;
            } else if (tblTMsg instanceof SVC_MACH_MSTRTMsg) {
                errCd = NSZM0756E;
            } else if (tblTMsg instanceof SVC_TM_EVENTTMsg) {
                errCd = NSZM0757E;
            } else if (tblTMsg instanceof DS_SVC_CALL_TPTMsg) {
                errCd = NSZM0847E;
            } else {
                errCd = "";
            }
            setErrMsg(pMsg, errCd);
            return false;
        }
        return true;
    }

    /**
     * checkRtnCodeForInsert
     * @param ezdTMsg EZDTMsg
     * @return boolean
     */
    private boolean checkRtnCodeForInsert(NSZC005001PMsg pMsg, EZDTMsg ezdTMsg) {
        // check error code
        // mod start 2015/12/09 CSA Defect#1708
        //if (ezdTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(ezdTMsg.getReturnCode())) {
        if (ezdTMsg == null) {
        // mod end 2015/12/09 CSA Defect#1708
            String errCd = null;

            if (ezdTMsg instanceof FSR_VISIT_TASKTMsg) {
                errCd = NSZM0346E;
            } else if (ezdTMsg instanceof FSR_USGTMsg) {
                errCd = NSZM0347E;
            } else if (ezdTMsg instanceof FSR_EVENTTMsg) {
                errCd = NSZM0173E;
            } else if (ezdTMsg instanceof FSR_VISIT_TM_EVENTTMsg) {
                errCd = NSZM0351E;
            } else if (ezdTMsg instanceof SVC_MEMOTMsg) {
                errCd = NSZM0475E;
            } else if (ezdTMsg instanceof FSR_EXPTMsg) {
                errCd = NSZM0758E;
            // add start 2015/12/16 CSA Defect#978
            } else if (ezdTMsg instanceof FSR_CHRGTMsg) {
                errCd = NSZM0805E;
            } else if (ezdTMsg instanceof FSR_CHRG_DTLTMsg) {
                errCd = NSZM0806E;
            // add end 2015/12/16 CSA Defect#978
            } else {
                errCd = "";
            }
            setErrMsg(pMsg, errCd);
            return false;
        }
        return true;
    }

    /**
     * checkRtnCodeForUpdate
     * @param ezdTMsg EZDTMsg
     * @return boolean
     */
    private boolean checkRtnCodeForUpdate(NSZC005001PMsg pMsg, EZDTMsg ezdTMsg) {
        // check error code
        // mod start 2015/12/09 CSA Defect#1708
        //if (ezdTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(ezdTMsg.getReturnCode())) {
        if (ezdTMsg == null) {
        // mod end 2015/12/09 CSA Defect#1708
            String errCd = null;

            if (ezdTMsg instanceof SVC_TASKTMsg) {
                errCd = NSZM0167E;
            } else if (ezdTMsg instanceof FSRTMsg) {
                errCd = NSZM0168E;
            } else if (ezdTMsg instanceof FSR_VISITTMsg) {
                errCd = NSZM0169E;
            } else if (ezdTMsg instanceof FSR_VISIT_TASKTMsg) {
                errCd = NSZM0348E;
            } else if (ezdTMsg instanceof FSR_USGTMsg) {
                errCd = NSZM0349E;
            } else if (ezdTMsg instanceof FSR_VISIT_TM_EVENTTMsg) {
                errCd = NSZM0350E;
            } else if (ezdTMsg instanceof SVC_PHYS_MTR_READTMsg) {
                errCd = NSZM0368E;
            } else if (ezdTMsg instanceof FSR_EXPTMsg) {
                errCd = NSZM0759E;
            // add start 2015/12/16 CSA Defect#978
            } else if (ezdTMsg instanceof FSR_CHRGTMsg) {
                errCd = NSZM0805E;
            } else if (ezdTMsg instanceof FSR_CHRG_DTLTMsg) {
                errCd = NSZM0806E;
            // add end 2015/12/16 CSA Defect#978
            } else if (ezdTMsg instanceof FSR_ISTL_CHK_LISTTMsg) {
                errCd = NSZM0849E;
            } else {
                errCd = "";
            }
            setErrMsg(pMsg, errCd);
            return false;
        }
        return true;
    }

    private boolean checkErrMsg(NSZC005001PMsg pMsg, EZDPMsg apiPMsg) {
        // add start 2016/02/08 CSA Defect#2863
        if (frceUpdFlg) {
            return true;
        }
        // add end 2016/02/08 CSA Defect#2863
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                setErrMsg(pMsg, xxMsgId);
                return false;
            }
        }
        return true;
    }

    //Add start 2020/05/20 QC#56811
    private boolean checkErrMsgExcludeWarn(NSZC005001PMsg pMsg, EZDPMsg apiPMsg) {
        // add start 2016/02/08 CSA Defect#2863
        if (frceUpdFlg) {
            return true;
        }
        // add end 2016/02/08 CSA Defect#2863
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                if (xxMsgId.endsWith("E")) {
                    setErrMsg(pMsg, xxMsgId);
                    return false;
                }
            }
        }
        return true;
    }
    //Add end 2020/05/20 QC#56811

    private void setErrMsg(NSZC005001PMsg pMsg, String msgId) {
        msgMap.addXxMsgId(msgId);
        msgMap.flush();
    }

    private boolean isError(NSZC005001PMsg pMsg) {
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        return false;
    }
    private boolean checkDebriefError(NSZC005001PMsg pMsg, String checkTarget) {
        //check current task
        return checkDebriefError(pMsg, checkTarget, null, null);
    }
    private boolean checkDebriefError(NSZC005001PMsg pMsg, String checkTarget, String fsrVisitNum, String svcTaskNum) {
        // add start 2016/02/08 CSA Defect#2863
        if (frceUpdFlg) {
            return true;
        }
        // add end 2016/02/08 CSA Defect#2863
        boolean isDebriefError = false;
        if (!ZYPCommonFunc.hasValue(svcTaskNum)) {
            svcTaskNum = this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue();
        }
        //IWR Status Check
        if (DEBRIEF_CHK_IWR.equals(checkTarget) || DEBRIEF_CHK_ALL.equals(checkTarget)) {
            if (existsIwrStatusDebriefError(pMsg)) {
                isDebriefError = true;
                String iwrCommErrMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_IWR_COMM_ERR_MSG, pMsg.glblCmpyCd.getValue());
                if (!insertSvcMemo(pMsg, svcTaskNum, iwrCommErrMsg)) {
                    return false;
                }
            }
        }
//        //Usage Parts
//        if (DEBRIEF_CHK_INVTY.equals(checkTarget) || DEBRIEF_CHK_ALL.equals(checkTarget) ) {
//            if(!confirmUsageParts(pMsg)) {
//                return false;
//            }
//        }
        //Amount Check
        if (DEBRIEF_CHK_LBOR_CHRG.equals(checkTarget) || DEBRIEF_CHK_ALL.equals(checkTarget)) {
            if (existsLaborAmountDebriefError(pMsg, fsrVisitNum, svcTaskNum)) {
                isDebriefError = true;
                String ngtvCommErrMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_NO_LBOR_CHRG_MSG, pMsg.glblCmpyCd.getValue());
                if (!insertSvcMemo(pMsg, svcTaskNum, ngtvCommErrMsg)) {
                    return false;
                }
            }
        }
        if (DEBRIEF_CHK_TRVL_CHRG.equals(checkTarget) || DEBRIEF_CHK_ALL.equals(checkTarget)) {
            if (existsTrvlAmountDebriefError(pMsg, fsrVisitNum)) {
                isDebriefError = true;
                String ngtvCommErrMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_NO_TRVL_CHRG_MSG, pMsg.glblCmpyCd.getValue());
                if (!insertSvcMemo(pMsg, svcTaskNum, ngtvCommErrMsg)) {
                    return false;
                }
            }
        }
        if (DEBRIEF_CHK_USG_CHRG.equals(checkTarget) || DEBRIEF_CHK_ALL.equals(checkTarget)) {
            if (existsUsageAmountDebriefError(pMsg, svcTaskNum)) {
                isDebriefError = true;
                String ngtvCommErrMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_NO_USG_CHRG_MSG, pMsg.glblCmpyCd.getValue());
                if (!insertSvcMemo(pMsg, svcTaskNum, ngtvCommErrMsg)) {
                    return false;
                }
            }
        }
        // add start 2017/01/26 CSA Defect#17114
        // Tech PI Check
        if (DEBRIEF_CHK_TECH_PI.equals(checkTarget) || DEBRIEF_CHK_ALL.equals(checkTarget)) {
            if (existsTechPIDebriefError(pMsg, svcTaskNum)) {
                isDebriefError = true;
                // Add Start 2019/01/10 K.Fujimoto QC#26861
                this.completionBean.getFsrUsgListConfForInvty().clear();
                this.completionBean.getFsrUsgListConf().clear();
                // Add End   2019/01/10 K.Fujimoto QC#26861
                String techPIMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_TECH_PI_MSG, pMsg.glblCmpyCd.getValue());
                if (!insertSvcMemo(pMsg, svcTaskNum, techPIMsg)) {
                    return false;
                }
            }
        }
        // add end 2017/01/26 CSA Defect#17114

        // del start 2016/05/31 CSA Defect#9144
        // if (DEBRIEF_CHK_ISTALL.equals(checkTarget) || DEBRIEF_CHK_ALL.equals(checkTarget)) {
        //    if (existsInstallDebriefError(pMsg)) {
        //        isDebriefError = true;
        //        String istlChckListMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_INSTALL_CHECK_LIST_MSG, pMsg.glblCmpyCd.getValue());
        //        if (!insertSvcMemo(pMsg, svcTaskNum, istlChckListMsg)) {
        //            return false;
        //        }
        //    }
        // }
        // del end 2016/05/31 CSA Defect#9144

        // START 2019/11/18 K.Fujimoto [QC#54391, ADD]
        // L2 Process(Onsite Support Process) Check
        // In case the call is not L2 call and the correction type is L2-Amazon process only,
        // don't create follow up call. and make the call debrief error.
        if (DEBRIEF_CHK_ONS_PROC.equals(checkTarget) || DEBRIEF_CHK_ALL.equals(checkTarget)) {
            if (existsOnsSprtDebriefError(pMsg)) {
                isDebriefError = true;
                String onsSprtErrMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_L2_FOLL_UP_ERR_MSG, pMsg.glblCmpyCd.getValue());
                if (!insertSvcMemo(pMsg, svcTaskNum, onsSprtErrMsg)) {
                    return false;
                }
            }
        }
        // END   2019/11/18 K.Fujimoto [QC#54391, ADD]
        // Mod Start 2019/08/30 K.Fujimoto QC#52433
        // if (isDebriefError) {
        if (isDebriefError || this.isDebreifError) {
        // Mod End 2019/08/30 K.Fujimoto QC#52433
            if (!updateSvcTaskStsCd(pMsg, SVC_TASK_STS.DEBRIEF_ERROR)) {
                return false;
            }
            this.isDebreifError = true;
            this.fsrCompleteFlg = false;
        }
        return true;
    }
    private boolean existsUsageAmountDebriefError(NSZC005001PMsg pMsg, String svcTaskNum) {
        if (!ZYPCommonFunc.hasValue(svcTaskNum)) {
            svcTaskNum = this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue();
        }
        FSR_USGTMsgArray  fsrUsgList = getFsrUsgListBySvcTaskNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), svcTaskNum);
        for (int j = 0; j < fsrUsgList.getValidCount(); j++) {
            FSR_USGTMsg fsrUsgTMsg = fsrUsgList.no(j);
            // START 2018/12/6 S.Kitamura [QC#29042, MOD]
            // Mod Start 2019/01/10 K.Fujimoto QC#26861
            //if (!ZYPCommonFunc.hasValue(fsrUsgTMsg.svcPrtDealAmt) && ZYPConstant.FLG_OFF_N.equals(fsrUsgTMsg.svcInvtyExFlg)) {
            if (!ZYPCommonFunc.hasValue(fsrUsgTMsg.svcPrtDealAmt) && ZYPConstant.FLG_OFF_N.equals(fsrUsgTMsg.svcInvtyExFlg.getValue())) {
            // END 2018/12/6 S.Kitamura [QC#29042, MOD]
            // Mod End   2019/01/10 K.Fujimoto QC#26861
                return true;
            }
        }
        return false;
    }
    private boolean existsTrvlAmountDebriefError(NSZC005001PMsg pMsg, String fsrVisitNum) {
        if (!ZYPCommonFunc.hasValue(fsrVisitNum)) {
            fsrVisitNum = this.completionBean.getFsrVisitTMsg().fsrVisitNum.getValue();
        }
        FSR_VISITTMsg fsrVisitTMsg = getFsrVisitFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), fsrVisitNum);
        if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.svcTrvlDealAmt)) {
            return true;
        }
        return false;
    }
    private boolean existsLaborAmountDebriefError(NSZC005001PMsg pMsg, String fsrVisitNum, String svcTaskNum) {
        if (!ZYPCommonFunc.hasValue(fsrVisitNum)) {
            fsrVisitNum = this.completionBean.getFsrVisitTMsg().fsrVisitNum.getValue();
        }
        if (!ZYPCommonFunc.hasValue(svcTaskNum)) {
            svcTaskNum = this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue();
        }
        FSR_VISIT_TASKTMsg fsrVisitTaskTMsg = getFsrVisiTaskFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), fsrVisitNum, svcTaskNum);
        if (fsrVisitTaskTMsg == null) {
            setErrMsg(pMsg, NSZM0862E);
                return false;
        }
        if (!ZYPCommonFunc.hasValue(fsrVisitTaskTMsg.svcLborDealAmt)) {
            return true;
        }
        return false;
    }
    private boolean existsIwrStatusDebriefError(NSZC005001PMsg pMsg) {
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstrFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        MDSETMsg mdseTmsg = getMdseFindByKey(pMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.mdseCd.getValue());
        if (mdseTmsg == null) {
            setErrMsg(pMsg, NSZM0861E);
                return false;
        }
        if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.iwrEnblFlg.getValue())) {
            return false;
        }
        String chk300Flg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_SVC_DISPT_CHK_IWR_STS_300_FLG, pMsg.glblCmpyCd.getValue());

        String iwrStsCd = null;
        if (MODE_INSTALL_CHECK.equals(pMsg.xxModeCd.getValue()) && pMsg.xxFsrIstlChkList.getValidCount() > 0) {
           FSR_VISITTMsgArray fsrVisitTmsgArray = getFsrVisit(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), pMsg.xxFsrIstlChkList.no(0).svcTaskNum.getValue());
           if (fsrVisitTmsgArray == null || fsrVisitTmsgArray.getValidCount() == 0) {
               setErrMsg(pMsg, NSZM0182E);
               return false;
           }
           iwrStsCd = fsrVisitTmsgArray.no(0).iwrStsCd.getValue();
        }
        if ((IWR_STS_CD_300.equals(pMsg.iwrStsCd.getValue()) || IWR_STS_CD_300.equals(iwrStsCd)) && !ZYPConstant.FLG_OFF_N.equals(chk300Flg)) {
            String errDays = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_IWR_ROSS_COMM_ERR_DAYS, pMsg.glblCmpyCd.getValue());
            String iwrLastCommTs = getIwrRgtnCond(pMsg);
            if (!hasValue(iwrLastCommTs) || !hasValue(errDays)) {
                return false;
            }

            Date dtIwrLastCommTs = toDate(iwrLastCommTs, FORMAT_TS);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dtIwrLastCommTs);
            cal.add(Calendar.DATE, Integer.parseInt(errDays));
            if (toStringDate(cal.getTime(), FORMAT_TS).compareTo(this.sysTs) >= 0) {
                return false;
            }
            return true;
        }
        return false;
    }
    private boolean existsInstallCheckError(NSZC005001PMsg pMsg) {
        // START 2017/07/03 K.Kojima [QC#19732,ADD]
        BigDecimal openTaskCount = getOpenTaskCount(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (openTaskCount.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        // END 2017/07/03 K.Kojima [QC#19732,ADD]
        String svcTaskNum = this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue();
        FSR_ISTL_CHK_LISTTMsgArray istlChkListTmsgArray = getIstlChkListBySvcTaskNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), svcTaskNum);
        if (ZYPConstant.FLG_ON_Y.equals(this.completionBean.getDsSvcCallTpTMsg().svcIstlReqFlg.getValue())) {
            if (istlChkListTmsgArray == null || istlChkListTmsgArray.getValidCount() == 0) {
                return true;
            }
        } else {
            return false;
        }
        // Mod Start 2017/09/01 QC#20672
        //check verify flag of all list is 'Y'
//        for (int i = 0; i < istlChkListTmsgArray.getValidCount(); i++) {
//            FSR_ISTL_CHK_LISTTMsg fsrIstlChkListTmsg = istlChkListTmsgArray.no(i);
//            if (ZYPConstant.FLG_ON_Y.equals(fsrIstlChkListTmsg.istlChkVerFlg.getValue())) {
//                continue;
//            } else {
//                return true;
//            }
//        }
        if (!hasValue(pMsg.istlCpltFlg) || !ZYPConstant.FLG_ON_Y.equals(pMsg.istlCpltFlg.getValue())) {
            return true;
        }
        // Mod End 2017/09/01 QC#20672
        return false;
    }

    // Del Start 2019/01/10 K.Fujimoto QC#26861
    // Dead Logic.
//    private boolean checkPoRequired(String glblCmpyCd, String slsDt, String dsAcctNum, String billToCustCd) {
//
//        NMZC610001PMsg apiMsg = new NMZC610001PMsg();
//        setValue(apiMsg.glblCmpyCd, glblCmpyCd);
//        setValue(apiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
//        setValue(apiMsg.dsTrxRuleTpCd, DS_TRX_RULE_TP.SERVICE);
//        setValue(apiMsg.billToCustCd, billToCustCd);
//
//        NMZC610001 api = new NMZC610001();
//        api.execute(apiMsg, this.onbatchType);
//        if (S21ApiUtil.isXxMsgId(apiMsg)) {
//            return false;
//        }
//
//        if (ZYPConstant.FLG_ON_Y.equals(apiMsg.TransactionRuleList.no(0).dsPoReqFlg.getValue())) {
//            return true;
//        }
//        return false;
//    }
    // Del End   2019/01/10 K.Fujimoto QC#26861

    // add start 2016/02/22 CSA Defect#2695
    private boolean isModification(String glblCmpyCd, NSZC005001_xxTmEventListPMsg param) {
        if (SVC_TM_EVENT.MODIFICATION.equals(param.svcTmEventCd.getValue())) {
            return true;
        }
        if (hasValue(param.mdseCd)) {
            String svcTmEventCd = getSvcTmEventCd(glblCmpyCd, param.mdseCd.getValue());
            if (SVC_TM_EVENT.MODIFICATION.equals(svcTmEventCd)) {
                return true;
            }
        }
        return false;
    }

    // add start 2016/02/22 CSA Defect#2695
    private String getSvcTmEventCd(String glblCmpyCd, String mdseCd) {
        SVC_TM_EVENTTMsg inMsg = new SVC_TM_EVENTTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("intgMdseCd01", mdseCd);
        SVC_TM_EVENTTMsgArray outArray = (SVC_TM_EVENTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0).svcTmEventCd.getValue();
    }
    // add start 2016/02/22 CSA Defect#2695
    private String getModificationMdseCd(String glblCmpyCd) {
        SVC_TM_EVENTTMsg inMsg = new SVC_TM_EVENTTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.svcTmEventCd.setValue(SVC_TM_EVENT.MODIFICATION);
        inMsg.ezCancelFlag.setValue(ZYPConstant.FLG_OFF_0);
        SVC_TM_EVENTTMsg outMsg = (SVC_TM_EVENTTMsg) ZYPCodeDataUtil.findByKey(inMsg);
        if (outMsg == null) {
            return null;
        }
        return outMsg.intgMdseCd.getValue();
    }

    // add 2016/04/20 CSA Defect#4469
    private NSZC005001_xxPartSurveyListPMsg getPartSurveyInfo(NSZC005001_xxFsrUsgListPMsg dtlPMsg, List<NSZC005001_xxPartSurveyListPMsg> fsrUsgSrvyListConf) {
        for (int i = 0; i < fsrUsgSrvyListConf.size(); i++) {
            NSZC005001_xxPartSurveyListPMsg partSurvey = fsrUsgSrvyListConf.get(i);
            if (hasValue(dtlPMsg.mdseCd) && dtlPMsg.mdseCd.getValue().equals(partSurvey.mdseCd.getValue())) {
                return partSurvey;
            }
        }
        return null;
    }

    // add 2016/04/20 CSA Defect#4469
    private boolean insertFsrUsgSrvy(NSZC005001PMsg pMsg, List<NSZC005001_xxPartSurveyListPMsg> fsrUsgSrvyListConf, FSR_USGTMsg fsrUsgTMsg) {
        for (int i = 0; i < fsrUsgSrvyListConf.size(); i++) {
            NSZC005001_xxPartSurveyListPMsg partSurvey = fsrUsgSrvyListConf.get(i);

            if (hasValue(partSurvey.mdseCd) && hasValue(fsrUsgTMsg.mdseCd) && fsrUsgTMsg.mdseCd.getValue().equals(partSurvey.mdseCd.getValue())) {
                FSR_USG_SRVYTMsg fsrUsgSrvyTMsg = new FSR_USG_SRVYTMsg();
                setFsrUsgSrvyValue(fsrUsgTMsg.glblCmpyCd.getValue(), fsrUsgTMsg, fsrUsgSrvyTMsg, partSurvey);

                S21ApiTBLAccessor.create(fsrUsgSrvyTMsg);
                if (!checkRtnCodeForInsert(pMsg, fsrUsgSrvyTMsg)) {
                    return false;

                }
            }
        }
        return true;
    }

    // add 2016/04/20 CSA Defect#4469
    private void setFsrUsgSrvyValue(String glblCmpyCd, FSR_USGTMsg fsrUsgTMsg, FSR_USG_SRVYTMsg fsrUsgSrvyTMsg, NSZC005001_xxPartSurveyListPMsg partSurvey) {

        setValue(fsrUsgSrvyTMsg.glblCmpyCd, glblCmpyCd);
        setValue(fsrUsgSrvyTMsg.fsrUsgSrvyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_USG_SRVY_SQ));
        setValue(fsrUsgSrvyTMsg.fsrUsgPk, fsrUsgTMsg.fsrUsgPk);
        setValue(fsrUsgSrvyTMsg.prtSrvyQstTxt, partSurvey.prtSrvyQstTxt);
        setValue(fsrUsgSrvyTMsg.prtSrvyAnsTxt, partSurvey.prtSrvyAnsTxt);
    }

    // START 2016/10/03 T.Tomita [QC#11896, ADD]
    private boolean callHoldReleaseAPI(NSZC005001PMsg pMsg) {

        // Get Hold Data
        Map<String, Object> holdReleaseData = getHoldData(pMsg);
        if (holdReleaseData == null) {
            return true;
        }

        NWZC033001PMsg nwzc033001PMsg = new NWZC033001PMsg();
        setValue(nwzc033001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(nwzc033001PMsg.slsDt, pMsg.slsDt);
        setValue(nwzc033001PMsg.hldPk, (BigDecimal) holdReleaseData.get("HLD_PK"));
        setValue(nwzc033001PMsg.hldRelRsnCd, (String) holdReleaseData.get("HLD_RSN_CD"));
        setValue(nwzc033001PMsg.cpoOrdNum, (String) holdReleaseData.get("CPO_ORD_NUM"));
        setValue(nwzc033001PMsg.cpoDtlLineNum, (String) holdReleaseData.get("CPO_DTL_LINE_NUM"));
        setValue(nwzc033001PMsg.cpoDtlLineSubNum, (String) holdReleaseData.get("CPO_DTL_LINE_SUB_NUM"));

        // Hold Release API
        NWZC033001 holdRelease = new NWZC033001();
        holdRelease.execute(nwzc033001PMsg, this.onbatchType);

        if (nwzc033001PMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < nwzc033001PMsg.xxMsgIdList.getValidCount(); i++) {
                setErrMsg(pMsg, nwzc033001PMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return false;
        }

        return true;
    }

    private Map<String, Object> getHoldData(NSZC005001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        paramMap.put("svcTaskStsCd", SVC_TASK_STS.COMPLETED);
        paramMap.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES_RETURN);
        return (Map<String, Object>) ssmBatchClient.queryObject("getHoldData", paramMap);
    }
    // END 2016/10/03 T.Tomita [QC#11896, ADD]

    // START 2017/10/25 [QC#22061, MOD]
    // START 2016/10/20 [QC#14252, ADD]
//    private  String convertCallType(String glblCmpyCd, String dsSvcCallTpCd, String firstProdCtrlCd, String svcPblmCrctTpCd) {
    private String convertCallType(String glblCmpyCd, String dsSvcCallTpCd, String firstProdCtrlCd, String svcPblmCrctTpCd, String phoneFixFlg) {

        if (ZYPConstant.FLG_ON_Y.equals(phoneFixFlg)) {

            SVC_PBLM_CRCT_TPTMsg svcPblmCrctTpTMsg = getSvcPblmCrctTpFindyByKey(glblCmpyCd, firstProdCtrlCd, svcPblmCrctTpCd);

            if (svcPblmCrctTpTMsg == null) {
                svcPblmCrctTpTMsg = getSvcPblmCrctTpFindyByKey(glblCmpyCd, MULTIPLICATION, svcPblmCrctTpCd);
            }

            if (svcPblmCrctTpTMsg == null) {
                return dsSvcCallTpCd;
            }

            if (ZYPConstant.FLG_ON_Y.equals(svcPblmCrctTpTMsg.phoFixFlg.getValue())) {
                // START 2018/09/26 W.Honda [QC#28381,MOD]
//                return DS_SVC_CALL_TP.PHONE_FIX_TECHNICIAN;
                DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = getDsSvcCallTpFindByKey(glblCmpyCd, dsSvcCallTpCd);
                if (dsSvcCallTpTMsg != null && ZYPConstant.FLG_ON_Y.equals(dsSvcCallTpTMsg.aftHrsFlg.getValue())) {
                    return DS_SVC_CALL_TP.AHS_PHONE_FIX_TECHNICIAN;
                } else {
                    return DS_SVC_CALL_TP.PHONE_FIX_TECHNICIAN;
                }
                // END 2018/09/26 W.Honda [QC#28381,MOD]
            }
        }

        return dsSvcCallTpCd;
    }
    // END 2017/10/25 [QC#22061, MOD]

    private SVC_PBLM_CRCT_TPTMsg getSvcPblmCrctTpFindyByKey(String glblCmpyCd, String firstProdCtrlCd, String svcPblmCrctTpCd) {
        SVC_PBLM_CRCT_TPTMsg svcPblmCrctTpTMsg = new SVC_PBLM_CRCT_TPTMsg();
        svcPblmCrctTpTMsg.glblCmpyCd.setValue(glblCmpyCd);
        svcPblmCrctTpTMsg.svcPblmCrctTpCd.setValue(svcPblmCrctTpCd);
        svcPblmCrctTpTMsg.firstProdCtrlCd.setValue(firstProdCtrlCd);
        return (SVC_PBLM_CRCT_TPTMsg) S21CacheTBLAccessor.findByKey(svcPblmCrctTpTMsg);
    }
    // END   2016/10/20 [QC#14252, ADD]

    // add start 2017/01/13 CSA Defect#17117
    private boolean updateSvcTaskStsCplt(NSZC005001PMsg pMsg) {
        if (!this.isDebreifError) {
            if (!updateSvcTaskStsCd(pMsg, SVC_TASK_STS.COMPLETED)) {
                return false;
            }
        }
        return true;
    }
    // add end 2017/01/13 CSA Defect#17117

    // add start 2017/01/15 CSA Defect#17129
    private NSZC061001_xxPartsChrgListPMsg getPartsChrgListPMsg(NSZC061001PMsg svcPrcApiPMsg, String svcTaskNum, String mdseCd) {
        if (svcPrcApiPMsg == null || svcPrcApiPMsg.xxPartsChrgList.getValidCount() <= 0) {
            return null;
        }

        if (!hasValue(svcTaskNum) || !hasValue(mdseCd)) {
            return null;
        }

        NSZC061001_xxPartsChrgListPMsg partsChrgListPMsg = null;
        for (int i = 0; i < svcPrcApiPMsg.xxPartsChrgList.getValidCount(); i++) {
            if (svcTaskNum.equals(svcPrcApiPMsg.xxPartsChrgList.no(i).svcTaskNum.getValue()) && mdseCd.equals(svcPrcApiPMsg.xxPartsChrgList.no(i).mdseCd.getValue())) {
                partsChrgListPMsg = svcPrcApiPMsg.xxPartsChrgList.no(i);
                break;
            }
        }
        return partsChrgListPMsg;
    }

    private NSZC061001_xxDrumChrgListPMsg getDrumChrgListPMsg(NSZC061001PMsg svcPrcApiPMsg, String svcTaskNum, String mdseCd) {
        if (svcPrcApiPMsg == null || svcPrcApiPMsg.xxDrumChrgList.getValidCount() <= 0) {
            return null;
        }

        if (!hasValue(svcTaskNum) || !hasValue(mdseCd)) {
            return null;
        }

        NSZC061001_xxDrumChrgListPMsg drumChrgListPMsg = null;
        for (int i = 0; i < svcPrcApiPMsg.xxDrumChrgList.getValidCount(); i++) {
            if (svcTaskNum.equals(svcPrcApiPMsg.xxDrumChrgList.no(i).svcTaskNum.getValue()) && mdseCd.equals(svcPrcApiPMsg.xxDrumChrgList.no(i).mdseCd.getValue())) {
                drumChrgListPMsg = svcPrcApiPMsg.xxDrumChrgList.no(i);
                break;
            }
        }
        return drumChrgListPMsg;
    }
    // add end 2017/01/15 CSA Defect#17129

    // add start 2017/01/26 CSA Defect#17114
    private boolean existsTechPIDebriefError(NSZC005001PMsg pMsg, String svcTaskNum) {
        FSR_VISITTMsgArray fsrVisitTMsgArray = getFsrVisit(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), svcTaskNum);
        if (fsrVisitTMsgArray == null || fsrVisitTMsgArray.getValidCount() == 0) {
            setErrMsg(pMsg, NSZM0182E);
            return false;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("techCd", fsrVisitTMsgArray.no(0).techCd.getValue());
        String physInvtyFlg = (String) ssmBatchClient.queryObject("getPhysInvtyFlg", paramMap);

        if (ZYPConstant.FLG_ON_Y.equals(physInvtyFlg)) {
            return true;
        }
        return false;
    }
    // add end 2017/01/26 CSA Defect#17114

    // add start 2017/02/13 CSA Defect#17109
    private boolean updateStsForDebrief(NSZC005001PMsg pMsg) {

        // get basic data for pricing
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String fsrNum = pMsg.fsrNum.getValue();

        SVC_TASKTMsgArray svcTaskList = getSvcTaskByFsrNumForUpdate(glblCmpyCd, fsrNum);

        // decision status
        boolean isAllComplete = isAllComplete(svcTaskList);
        boolean isExistDebriefErr = isExistDebriefErr(svcTaskList);
        boolean isErrInvtyUpd = false;

        if (isAllComplete) {
            for (int i = 0; i < svcTaskList.getValidCount(); i++) {
                SVC_TASKTMsg svcTask = svcTaskList.no(i);
                if (SVC_TASK_STS.COMPLETED.equals(svcTask.svcTaskStsCd.getValue())) {
                    // START 2017/07/21 K.Kojima [QC#19963,ADD]
                    // update FSR_VISIT
                    if (MODE_DEBRIEF.equals(pMsg.xxModeCd.getValue())) {
                        insertFsrEvent(pMsg);
                    }
                    // END 2017/07/21 K.Kojima [QC#19963,ADD]

                    // update SVC_TASK
                    setValue(svcTask.svcTaskStsCd, SVC_TASK_STS.CLOSED);
                    setValue(svcTask.svcTaskCloDt, sysDt);
                    setValue(svcTask.svcTaskCloTm, sysTm);
                    setValue(svcTask.svcTaskCloByUsrId, pMsg.userId);

                    S21ApiTBLAccessor.update(svcTask);
                    if (!checkRtnCodeForUpdate(pMsg, svcTask)) {
                        return false;
                    }

                    // update FSR_VISIT
                    FSR_VISITTMsgArray fsrVisitList = getFsrVisitForUpdate(glblCmpyCd, fsrNum, svcTask.svcTaskNum.getValue());
                    for (int j = 0; j < fsrVisitList.getValidCount(); j++) {
                        setValue(fsrVisitList.no(j).fsrVisitStsCd, SVC_TASK_STS.CLOSED);
                        setValue(fsrVisitList.no(j).fsrVisitCloDt, sysDt);
                        setValue(fsrVisitList.no(j).fsrVisitCloTm, sysTm);
                        S21ApiTBLAccessor.update(fsrVisitList.no(j));
                        if (!checkRtnCodeForUpdate(pMsg, fsrVisitList.no(j))) {
                            return false;
                        }
                        // insert FSR_EVENT
                        insertFsrEvent(pMsg, fsrVisitList.no(j));
                    }

                    // call MODS update api
                    // START 2022/05/25 K.Kitachi [QC#59987, DEL]
//                    if (!updateModProcStsForChargable(pMsg, svcTask.svcTaskNum.getValue())) {
//                        return false;
//                    }
                    // END 2022/05/25 K.Kitachi [QC#59987, DEL]
                    // update FSR_VISIT_TM_EVENT
                    if (!updateFsrVisitTmEvent(pMsg, svcTask.svcTaskNum.getValue())) {
                        return false;
                    }
                    // Del Start 2018/11/28 K.Fujimoto QC#28647
                    // if (!frceUpdFlg) {
                    //     // call inventory update api
                    //     if (!updateInvtyForDebrief(pMsg, svcTask.svcTaskNum.getValue())) {
                    //         isErrInvtyUpd = true;
                    //     }
                    // }
                    // Del End  2018/11/28 K.Fujimoto QC#28647
                    // update FSR_USG
                    // Mod Start 2018/11/28 K.Fujimoto QC#28647
                    // if (!updateFsrUsgForChargable(pMsg, svcTask.svcTaskNum.getValue())) {
                    if (!updateFsrUsgForChargable(pMsg, svcTask.svcTaskNum.getValue(), null)) {
                    // Mod End   2018/11/28 K.Fujimoto QC#28647
                        return false;
                    }
                }
            }
        }
        // Add Start 2019/01/10 K.Fujimoto QC#26861
        if (this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
            SVC_TASKTMsg svcTask = getSvcTaskFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue());
            if (!checkRtnCodeForSearch(pMsg, svcTask, new SVC_TASKTMsg())) {
                return false;
            }
            // START 2024/01/25 t.aizawa [QC#63504, ADD]
            if (!ZYPCommonFunc.hasValue(svcTask.svcTaskCloDt)) {
            // END   2024/01/25 t.aizawa [QC#63504, ADD]
                // update SVC_TASK
                setValue(svcTask.svcTaskStsCd, SVC_TASK_STS.CLOSED);
                setValue(svcTask.svcTaskCloDt, sysDt);
                setValue(svcTask.svcTaskCloTm, sysTm);
                setValue(svcTask.svcTaskCloByUsrId, pMsg.userId);
                S21ApiTBLAccessor.update(svcTask);
                if (!checkRtnCodeForUpdate(pMsg, svcTask)) {
                    return false;
                }
            // START 2024/01/25 t.aizawa [QC#63504, ADD]
            }
            // END   2024/01/25 t.aizawa [QC#63504, ADD]
            // update FSR_VISIT
            FSR_VISITTMsgArray fsrVisitList = getFsrVisitForUpdate(glblCmpyCd, fsrNum, svcTask.svcTaskNum.getValue());
            for (int j = 0; j < fsrVisitList.getValidCount(); j++) {
                // START 2024/01/25 t.aizawa [QC#63504, ADD]
                if (!ZYPCommonFunc.hasValue(fsrVisitList.no(j).fsrVisitCloDt)) {
                // END   2024/01/25 t.aizawa [QC#63504, ADD]
                    setValue(fsrVisitList.no(j).fsrVisitStsCd, SVC_TASK_STS.CLOSED);
                    setValue(fsrVisitList.no(j).fsrVisitCloDt, sysDt);
                    setValue(fsrVisitList.no(j).fsrVisitCloTm, sysTm);
                    S21ApiTBLAccessor.update(fsrVisitList.no(j));
                    if (!checkRtnCodeForUpdate(pMsg, fsrVisitList.no(j))) {
                        return false;
                    }
                    // insert FSR_EVENT
                    insertFsrEvent(pMsg, fsrVisitList.no(j));
                // START 2024/01/25 t.aizawa [QC#63504, ADD]
                }
                // END   2024/01/25 t.aizawa [QC#63504, ADD]
            }
            // update FSR_VISIT_TM_EVENT
            if (!updateFsrVisitTmEvent(pMsg, svcTask.svcTaskNum.getValue())) {
                return false;
            }
            if (!updateFsrUsgForChargable(pMsg, svcTask.svcTaskNum.getValue(), null)) {
                return false;
            }
            String modCloSvcTaskMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_MOD_CLO_SVC_TASK_MSG, pMsg.glblCmpyCd.getValue());
            if (!insertSvcMemoForClosedSvcTaskUpdate(pMsg, this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue(), modCloSvcTaskMsg)) {
                return false;
            }
        }
        // Add End   2019/01/10 K.Fujimoto QC#26861
        FSRTMsg fsr = getFsrFindByKeyForUpdate(glblCmpyCd, fsrNum);
        if (isAllComplete) {
            // START 2024/01/25 t.aizawa [QC#63504, ADD]
            if (!ZYPCommonFunc.hasValue(fsr.fsrCloDt)) {
            // END   2024/01/25 t.aizawa [QC#63504, ADD]
                setValue(fsr.fsrStsCd, SVC_TASK_STS.CLOSED);
                setValue(fsr.fsrCloDt, sysDt);
                setValue(fsr.fsrCloTm, sysTm);
            // START 2024/01/25 t.aizawa [QC#63504, ADD]
            }
            // END   2024/01/25 t.aizawa [QC#63504, ADD]
        } else if (isExistDebriefErr) {
            setValue(fsr.fsrStsCd, SVC_TASK_STS.DEBRIEF_ERROR);
        }
        if (isErrInvtyUpd) {
            setValue(fsr.fsrStsCd, SVC_TASK_STS.DEBRIEF_ERROR);
        }
        // Add Start 2019/01/10 K.Fujimoto QC#26861
        if (this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
            setValue(fsr.fsrStsCd, SVC_TASK_STS.CLOSED);
        }
        // Add End   2019/01/10 K.Fujimoto QC#26861

        S21ApiTBLAccessor.update(fsr);
        if (!checkRtnCodeForUpdate(pMsg, fsr)) {
            return false;
        }
        return true;
    }
    // add end 2017/02/13 CSA Defect#17109

    // add start 2017/02/13 CSA Defect#17109
    private boolean updateInvtyForDebrief(NSZC005001PMsg pMsg, String svcTaskNum) {
        // Add Start 2019/01/10 K.Fujimoto QC#30250
        // Add Start 2019/01/10 K.Fujimoto QC#26861
        // if (this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
        //     return true;
        // }
        // Add End   2019/01/10 K.Fujimoto QC#26861
        // End Start 2019/01/10 K.Fujimoto QC#30250

        // Call Inventory Update API
        // START 2017/10/13 K.Kim [QC#17430, ADD]
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstrFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        String billToAcctNum = svcMachMstrTMsg.billToAcctNum.getValue();
        // END 2017/10/13 K.Kim [QC#17430, ADD]
        for (int i = 0; i < this.completionBean.getFsrUsgListConfForInvty().size(); i++) {
            NSZC005001_xxFsrUsgListPMsg dtlPMsg = this.completionBean.getFsrUsgListConfForInvty().get(i);
            // Add Start 2017/09/01 QC#20865
            if (!svcTaskNum.equals(dtlPMsg.svcTaskNum.getValue())) {
                continue;
            }
            // Add End 2017/09/01 QC#20865
            // START 2017/12/28 M.Naito [QC#18646, MOD]
            if (ZYPConstant.FLG_ON_Y.equals(dtlPMsg.svcInvtyExFlg.getValue())) {
                continue;
            }
            // END 2017/12/28 M.Naito [QC#18646, MOD]

            String tocCd = getTocCd(pMsg.glblCmpyCd.getValue(), dtlPMsg.prtUsedByTechCd.getValue(), pMsg.slsDt.getValue());
            if (!ZYPCommonFunc.hasValue(tocCd)) {
                setErrMsg(pMsg, NSZM0344E);
                return false;
            }
            MDSETMsg mdseTMsg = getMdseFindByKey(pMsg.glblCmpyCd.getValue(), dtlPMsg.mdseCd.getValue());
            if (!checkRtnCodeForSearch(pMsg, mdseTMsg, new MDSETMsg())) {
                return false;
            }
            String coaProdCd = mdseTMsg.coaProdCd.getValue();

            // START 2017/10/13 K.Kim [QC#17430, MOD]
            //Mod Start 2019/07/30 K.Fujimoto QC#52220
            if (!dtlPMsg.svcPrtQty.getValue().equals(BigDecimal.ZERO)) {
                // NLZC002001PMsg invtyUpdApiPMsg = setInventoryUpdateApiParamsUsg(pMsg, dtlPMsg, tocCd, coaProdCd);
                NLZC002001PMsg invtyUpdApiPMsg = setInventoryUpdateApiParamsUsg(pMsg, dtlPMsg, tocCd, coaProdCd, billToAcctNum);
                // END 2017/10/13 K.Kim [QC#17430, MOD]
                new NLZC002001().execute(invtyUpdApiPMsg, onbatchType);
                if (!checkErrMsg(pMsg, invtyUpdApiPMsg)) {
                    return false;
                }

                // Register Returnable Parts
                if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.rtrnReqPrtFlg.getValue())) {
                    // START 2017/10/13 K.Kim [QC#17430, MOD]
                    // invtyUpdApiPMsg = setInventoryUpdateApiParamsRtn(pMsg, dtlPMsg, tocCd, coaProdCd);
                    invtyUpdApiPMsg = setInventoryUpdateApiParamsRtn(pMsg, dtlPMsg, tocCd, coaProdCd, billToAcctNum);
                    // END 2017/10/13 K.Kim [QC#17430, MOD]
                    new NLZC002001().execute(invtyUpdApiPMsg, onbatchType);
                    if (!checkErrMsg(pMsg, invtyUpdApiPMsg)) {
                        return false;
                    }
                }
            }
            //Mod End   2019/07/30 K.Fujimoto QC#52220
        }
        return true;
    }
    // add end 2017/02/13 CSA Defect#17109

    // add start 2017/02/13 CSA Defect#17109
    private boolean isZeroChrg(FSR_VISITTMsg fsrVisitAmt) {
        if (fsrVisitAmt == null) {
            return true;
        }
        BigDecimal svcLborDealDiscAmt = BigDecimal.ZERO;
        BigDecimal svcLborInvDealDiscAmt = BigDecimal.ZERO;
        BigDecimal svcTrvlDealDiscAmt = BigDecimal.ZERO;
        BigDecimal svcPrtDealDiscAmt = BigDecimal.ZERO;
        BigDecimal svcExpDealDiscAmt = BigDecimal.ZERO;
        if (hasValue(fsrVisitAmt.svcLborDealDiscAmt)) {
            svcLborDealDiscAmt = fsrVisitAmt.svcLborDealDiscAmt.getValue();
        }
        if (hasValue(fsrVisitAmt.svcLborInvDealDiscAmt)) {
            svcLborInvDealDiscAmt = fsrVisitAmt.svcLborInvDealDiscAmt.getValue();
        }
        if (hasValue(fsrVisitAmt.svcTrvlDealDiscAmt)) {
            svcTrvlDealDiscAmt = fsrVisitAmt.svcTrvlDealDiscAmt.getValue();
        }
        if (hasValue(fsrVisitAmt.svcPrtDealDiscAmt)) {
            svcPrtDealDiscAmt = fsrVisitAmt.svcPrtDealDiscAmt.getValue();
        }
        if (hasValue(fsrVisitAmt.svcExpDealDiscAmt)) {
            svcExpDealDiscAmt = fsrVisitAmt.svcExpDealDiscAmt.getValue();
        }
        if (hasValue(fsrVisitAmt.svcLborDealAmt) && BigDecimal.ZERO.compareTo(fsrVisitAmt.svcLborDealAmt.getValue().subtract(svcLborDealDiscAmt)) < 0) {
            return false;
        }
        if (hasValue(fsrVisitAmt.svcLborInvDealAmt) && BigDecimal.ZERO.compareTo(fsrVisitAmt.svcLborInvDealAmt.getValue().subtract(svcLborInvDealDiscAmt)) < 0) {
            return false;
        }
        if (hasValue(fsrVisitAmt.svcTrvlDealAmt) && BigDecimal.ZERO.compareTo(fsrVisitAmt.svcTrvlDealAmt.getValue().subtract(svcTrvlDealDiscAmt)) < 0) {
            return false;
        }
        if (hasValue(fsrVisitAmt.svcPrtDealAmt) && BigDecimal.ZERO.compareTo(fsrVisitAmt.svcPrtDealAmt.getValue().subtract(svcPrtDealDiscAmt)) < 0) {
            return false;
        }
        if (hasValue(fsrVisitAmt.svcExpDealAmt) && BigDecimal.ZERO.compareTo(fsrVisitAmt.svcExpDealAmt.getValue().subtract(svcExpDealDiscAmt)) < 0) {
            return false;
        }
        return true;
    }
    // add end 2017/02/13 CSA Defect#17109

    // START 2017/07/03 K.Kojima [QC#19732,ADD]
    private BigDecimal getOpenTaskCount(String glblCmpyCd, String fsrNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("fsrNum", fsrNum);
        paramMap.put("flgY", ZYPConstant.FLG_ON_Y);
        return (BigDecimal) ssmBatchClient.queryObject("getOpenTaskCount", paramMap);
    }
    // END 2017/07/03 K.Kojima [QC#19732,ADD]

    // Add Start 2017/08/02 QC#20082
    private boolean isCallMachineMasterUpdateAPI(NSZC005001PMsg pMsg) {
        String svcTaskNum = this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue();

        Map<String, Object> svcTaskInfo = getCallTp(pMsg, svcTaskNum);
        if (svcTaskInfo == null) {
            return false;
        }

        String svcTaskStsCd = (String) svcTaskInfo.get("SVC_TASK_STS_CD");
        List<String> compStsMap = new ArrayList<String>();
        compStsMap.add(SVC_TASK_STS.COMPLETED);
        compStsMap.add(SVC_TASK_STS.CLOSED);
        if (!hasValue(svcTaskStsCd) || !compStsMap.contains(svcTaskStsCd)) {
            return false;
        }

        // Mod Start 2018/06/25 QC#26799
        String fsrTpCd = (String) svcTaskInfo.get("FSR_TP_CD");
        if (!hasValue(fsrTpCd)) {
            return false;
        }
        if (!FSR_TP.INSTALL_CALL.equals(fsrTpCd)) {
            // Service Call Complete
            return true;
        }

        // START 2019/10/15 K.Fujimoto [QC#54115,MOD]
        String isFirstVisitDeinsFlg = isFirstVisitDeinsReq(pMsg, (String) svcTaskInfo.get("FSR_NUM"));
        if (ZYPConstant.FLG_ON_Y.equals(isFirstVisitDeinsFlg)) {
            // De-Install Call Complete
            return false;
        }
        // END   2019/10/15 K.Fujimoto [QC#54115,MOD]
        String fsrStsCd = (String) svcTaskInfo.get("FSR_STS_CD");
        List<String> compFsrStsMap = new ArrayList<String>();
        compFsrStsMap.add(SVC_TASK_STS.COMPLETED);
        compFsrStsMap.add(SVC_TASK_STS.PENDING_CHARGE);
        compFsrStsMap.add(SVC_TASK_STS.CLOSED);
        if (!hasValue(fsrStsCd) || !compFsrStsMap.contains(fsrStsCd)) {
            return false;
        }
        // Mod End 2018/06/25 QC#26799

        // START 2019/08/20 [QC#52380,MOD]
        // Install Call
//        FSR_ISTL_CHK_LISTTMsgArray istlChkListTmsgArray = getIstlChkListBySvcTaskNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), svcTaskNum);
//        if (istlChkListTmsgArray.getValidCount() == 0) {
//            return false;
//        }
//        // Mod Start 2017/09/01 QC#20672
//        for (int i = 0; i < istlChkListTmsgArray.getValidCount(); i++) {
//            FSR_ISTL_CHK_LISTTMsg fsrIstlChkListTmsg = istlChkListTmsgArray.no(i);
//            if (ZYPConstant.FLG_ON_Y.equals(fsrIstlChkListTmsg.istlChkVerFlg.getValue())) {
//                continue;
//            } else {
//                return false;
//            }
//        }
//        if (!hasValue(pMsg.istlCpltFlg) || !ZYPConstant.FLG_ON_Y.equals(pMsg.istlCpltFlg.getValue())) {
//            return false;
//        }
//        // Mod End 2017/09/01 QC#20672
//        return true;

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String fsrNum = pMsg.fsrNum.getValue();
        SVC_TASKTMsgArray svcTaskList = getSvcTaskByFsrNumForUpdate(glblCmpyCd, fsrNum);

        boolean isAllComplete = isAllComplete(svcTaskList);
        boolean isIstlComplete = isIstlComplete(glblCmpyCd, fsrNum, svcTaskList, isAllComplete);

        return isIstlComplete;
        // END 2019/08/20 [QC#52380,MOD]
    }
    // Add End 2017/08/02 QC#20082
    // Add Start 2017/09/01 QC#20672
    private String nullToN(String flg) {
        if (hasValue(flg)) {
            return flg;
        }
        return ZYPConstant.FLG_OFF_N;
    }
    // Add End 2017/09/01 QC#20672
    // Add Start 2017/10/27 QC#22116
    private boolean isOverridePrice(NSZC005001_xxChargesListPMsg chrg) {
        if (!hasValue(chrg.ovrdSvcChrgUnitAmt) && hasValue(chrg.svcChrgDiscRate) && BigDecimal.ZERO.compareTo(chrg.svcChrgDiscRate.getValue()) == 0) {
            return false;
        }
        return true;
    }
    // Add End 2017/10/27 QC#22116

    // START 2017/12/04 U.Kim [QC#19907,ADD]
    private void sendMailForLocationUpdate(NSZC005001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        if (!this.fsrCompleteFlg) {
            return;
        }
        Map<String, Object> updateCustomerInfoMap = getUpdateCustomerInfo(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (updateCustomerInfoMap == null) {
            return;
        }
        if (!ZYPConstant.FLG_ON_Y.equals((String) updateCustomerInfoMap.get("BILL_TO_CUST_UPD_FLG")) && !ZYPConstant.FLG_ON_Y.equals((String) updateCustomerInfoMap.get("SHIP_TO_CUST_UPD_FLG"))) {
            return;
        }

        // Mail Template
        S21MailTemplate tmpl = getMailTemplate(pMsg, LOC_UPDT_MAIL_TMPL_ID);
        if (!hasValue(tmpl.getSubject()) || !hasValue(tmpl.getBody())) {
            setErrMsg(pMsg, NSZM0185E);
            return;
        }
        SVC_TASKTMsgArray mailSvcTaskNum = getSvcTaskByFsrNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (mailSvcTaskNum.getValidCount() == 0) {
            return;
        }

        // START 2019/12/16 [QC#52752, ADD]
        // START 2019/12/19 [QC#52752-1, MOD]
        // START 2019/12/24 [QC#52752-1, MOD]
        // if (isAllClosed(mailSvcTaskNum)) {
        if (isAllClosed(mailSvcTaskNum)) {
        // END   2019/12/24 [QC#52752-1, MOD]
        // END   2019/12/19 [QC#52752-1, MOD]
            return;
        }
        // END   2019/12/16 [QC#52752, ADD]

        // START 2019/12/24 [QC#52752-1, MOD]
        if (SVC_TASK_STS.PENDING_CHARGE.equals(this.currentFsrSts)) {
            return;
        }
        // END   2019/12/24 [QC#52752-1, MOD]

        Map<String, String> mailUserInfo = getUserIdNm(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        StringBuilder mailBody = setMailBodyForLocationUpdate(pMsg, updateCustomerInfoMap, mailUserInfo, mailSvcTaskNum, svcMachMstrTMsg);

        if (!sendMail(pMsg, tmpl, mailBody)) {
            return;
        }
        return;
    }

    // START 2019/12/16 [QC#52752, ADD]
    private boolean isAllClosed(SVC_TASKTMsgArray svcTaskList) {
        boolean isAllClosed = true;
        for (int i = 0; i < svcTaskList.getValidCount(); i++) {
            String svcTaskSts = svcTaskList.no(i).svcTaskStsCd.getValue();
            if (SVC_TASK_STS.CANCELLED.equals(svcTaskSts)) {
                continue;
            }
            if (!SVC_TASK_STS.CLOSED.equals(svcTaskSts)) {
                isAllClosed = false;
                break;
            }
        }
        return isAllClosed;
    }
    // END   2019/12/16 [QC#52752, ADD]

    private StringBuilder setMailBodyForLocationUpdate(NSZC005001PMsg pMsg, Map<String, Object> updateCustomerInfoMap, Map<String, String> mailUserInfo, SVC_TASKTMsgArray mailSvcTaskNum, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        StringBuilder mailBody = new StringBuilder();
        // Appending svcTaskNum
        StringBuilder svcTaskBuilder = new StringBuilder();
        StringBuilder fsrUserIdNm = new StringBuilder();
        for (int i = 0; i < mailSvcTaskNum.getValidCount(); i++) {
            SVC_TASKTMsg svcTaskTMsg = mailSvcTaskNum.no(i);
            if (svcTaskBuilder.length() > 0) {
                svcTaskBuilder.append(COMMA);
            }
            svcTaskBuilder.append(svcTaskTMsg.svcTaskNum.getValue());
        }
        fsrUserIdNm.append(getStrFromMap(mailUserInfo.get("USR_ID")));
        if(hasValue(getStrFromMap(mailUserInfo.get("USR_NM")))){
            fsrUserIdNm.append(DIVISION + getStrFromMap(mailUserInfo.get("USR_NM")));
        }
        String svcTaskNum = svcTaskBuilder.toString();

        String newLine = System.getProperty("line.separator");
        // set Parameter
        mailBody.append(String.format("Serial#                                 : %1$s", getStrFromMap(svcMachMstrTMsg.serNum.getValue())));
        mailBody.append(newLine);
        mailBody.append(String.format("Install Base ID                         : %1$s", getBigFromMap(pMsg.svcMachMstrPk.getValue())));
        mailBody.append(newLine);
        mailBody.append(String.format("FSR#                                    : %1$s", getStrFromMap(pMsg.fsrNum.getValue())));
        mailBody.append(newLine);
        mailBody.append(String.format("TASK#                                   : %1$s", svcTaskNum));
        mailBody.append(newLine);
        mailBody.append(String.format("Creation User ID/User Name              : %1$s", fsrUserIdNm));
        mailBody.append(newLine);
        mailBody.append(newLine);
        mailBody.append(String.format("Change Request Address"));
        mailBody.append(newLine);
        if(ZYPConstant.FLG_ON_Y.equals((String) updateCustomerInfoMap.get("BILL_TO_CUST_UPD_FLG"))){
            mailBody.append(String.format("Bill To Account#/Account Name           : %1$s", getStrFromMap((String) updateCustomerInfoMap.get("NEW_BILL_TO_ACCT"))));
            mailBody.append(newLine);
            mailBody.append(String.format("Bill To Location#/Location Address      : %1$s", getStrFromMap((String) updateCustomerInfoMap.get("NEW_BILL_TO_LOC"))));
            mailBody.append(newLine);
        }
        if(ZYPConstant.FLG_ON_Y.equals((String) updateCustomerInfoMap.get("SHIP_TO_CUST_UPD_FLG"))){
            mailBody.append(String.format("Current Location Account#/Account Name  : %1$s", getStrFromMap((String) updateCustomerInfoMap.get("NEW_SHIP_TO_ACCT"))));
            mailBody.append(newLine);
            mailBody.append(String.format("Current Location#/Location Address      : %1$s", getStrFromMap((String) updateCustomerInfoMap.get("NEW_SHIP_TO_LOC"))));
            mailBody.append(newLine);
        }
        mailBody.append(newLine);
        mailBody.append(String.format("Old Address"));
        mailBody.append(newLine);
        if(ZYPConstant.FLG_ON_Y.equals((String) updateCustomerInfoMap.get("BILL_TO_CUST_UPD_FLG"))){
            mailBody.append(String.format("Bill To Account#/Account Name           : %1$s", getStrFromMap((String) updateCustomerInfoMap.get("OLD_BILL_TO_ACCT"))));
            mailBody.append(newLine);
            mailBody.append(String.format("Bill To Location#/Location Address      : %1$s", getStrFromMap((String) updateCustomerInfoMap.get("OLD_BILL_TO_LOC"))));
            mailBody.append(newLine);
        }
        if(ZYPConstant.FLG_ON_Y.equals((String) updateCustomerInfoMap.get("SHIP_TO_CUST_UPD_FLG"))){
            mailBody.append(String.format("Current Location Account#/Account Name  : %1$s", getStrFromMap((String) updateCustomerInfoMap.get("OLD_SHIP_TO_ACCT"))));
            mailBody.append(newLine);
            mailBody.append(String.format("Current Location#/Location Address      : %1$s", getStrFromMap((String) updateCustomerInfoMap.get("OLD_SHIP_TO_LOC"))));
            mailBody.append(newLine);
        }
        return mailBody;
    }

    // END 2017/12/04 U.Kim [QC#19907,ADD]
    // START 2018/01/15 U.Kim [QC#19702, ADD]
    private void setWaitSecUpdTaskOther(String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return;
        }
        BigDecimal numConst = ZYPCodeDataUtil.getNumConstValue(WAIT_SEC_UPD_TASK_COMPLETION, glblCmpyCd);
        if (numConst != null) {
            this.waitSecUpdTaskCompletion = numConst.intValue();
        }
    }
    // END 2018/01/15 U.Kim [QC#19702, ADD]

    // START 2018/06/07 M.Naito [QC#22434,MOD]
    private String getSvcChrgTrxTpCd(NSZC005001PMsg pMsg, String svcTaskNum, String svcInvChrgTpCd) {

        if (!hasValue(svcTaskNum) || !hasValue(svcInvChrgTpCd)) {
            return SVC_CHRG_TRX_TP.COVERAGE_BASED_ON_CONTRACT;
        }
        for (int i = 0; i < pMsg.xxChargesList.getValidCount(); i++) {
            NSZC005001_xxChargesListPMsg chrg = pMsg.xxChargesList.no(i);
            if (svcTaskNum.equals(chrg.svcTaskNum.getValue()) && svcInvChrgTpCd.equals(chrg.svcInvChrgTpCd.getValue())) {
                if (hasValue(chrg.svcChrgTrxTpCd)) {
                    return chrg.svcChrgTrxTpCd.getValue();
                }
            }
        }

        SVC_TASKTMsg svcTaskTMsg = getSvcTaskFindByKey(pMsg.glblCmpyCd.getValue(), svcTaskNum);
        if (!hasValue(svcTaskTMsg.svcBillTpCd) || !hasValue(svcTaskTMsg.svcBillChngRsnCd)) {
            return SVC_CHRG_TRX_TP.COVERAGE_BASED_ON_CONTRACT;
        }

        SVC_BILL_TPTMsg svcBillTpTMsg = getSvcBillTpFindByKey(pMsg.glblCmpyCd.getValue(), svcTaskTMsg.svcBillTpCd.getValue());
        String chrgFlg = null;
        String svcChrgTrxTpCd = null;

        if (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(svcInvChrgTpCd) || SVC_INV_CHRG_TP.TRAVEL_CHARGE.equals(svcInvChrgTpCd)) {
            chrgFlg = svcBillTpTMsg.lborChrgFlg.getValue();
        } else if (SVC_INV_CHRG_TP.PARTS_CHARGE.equals(svcInvChrgTpCd)) {
            chrgFlg = svcBillTpTMsg.prtChrgFlg.getValue();
        } else if (SVC_INV_CHRG_TP.EXPENSE_CHARGE.equals(svcInvChrgTpCd)) {
            chrgFlg = svcBillTpTMsg.expChrgFlg.getValue();
        }

        if (ZYPConstant.FLG_ON_Y.equals(chrgFlg)) {
            svcChrgTrxTpCd = SVC_CHRG_TRX_TP.CHARGE;
        } else if (ZYPConstant.FLG_OFF_N.equals(chrgFlg)) {
            svcChrgTrxTpCd = SVC_CHRG_TRX_TP.NO_CHARGE;
        } else {
            svcChrgTrxTpCd = SVC_CHRG_TRX_TP.COVERAGE_BASED_ON_CONTRACT;
        }
        return svcChrgTrxTpCd;
    }
    // END 2018/06/07 M.Naito [QC#22434,MOD]

    // START 2018/10/04 K.Kojima [QC#28545,ADD]
    private boolean updateSvcTaskPhoneFix(NSZC005001PMsg pMsg) {
        if (pMsg.xxVisitTaskList.getValidCount() == 0) {
            return true;
        }
        if (!hasValue(pMsg.xxVisitTaskList.no(0).phoneFixFlg)) {
            return true;
        }
        if (!pMsg.xxVisitTaskList.no(0).phoneFixFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            return true;
        }

        String svcTaskNum = this.completionBean.getFsrVisitTMsg().svcTaskNum.getValue();
        String dsSvcCallTpCd = this.completionBean.getSvcTaskTMsg().dsSvcCallTpCd.getValue();
        String firstProdCtrlCd = this.completionBean.getSvcTaskTMsg().firstProdCtrlCd.getValue();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String fsrNum = pMsg.fsrNum.getValue();
        String phoneFixFlg = pMsg.xxVisitTaskList.no(0).phoneFixFlg.getValue();

        SVC_TASKTMsg svcTaskTMsg = getSvcTaskFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), svcTaskNum);
        if (!checkRtnCodeForSearch(pMsg, svcTaskTMsg, new SVC_TASKTMsg())) {
            setErrMsg(pMsg, NSZM0182E);
            return false;
        }

        Map<String, Object> fsrVisitAmt = getFsrVisitTotAmt(pMsg, svcTaskNum);
        if (fsrVisitAmt == null) {
            setErrMsg(pMsg, NSZM0182E);
            return false;
        }

        // START 2018/12/26 K.Kitachi [QC#29723, MOD]
//        FSR_VISIT_TASKTMsg fsrVisitTaskTMsg = getFsrVisiTaskFindByKey(glblCmpyCd, fsrNum, (String) fsrVisitAmt.get("FSR_VISIT_NUM"), svcTaskNum);
//        if (fsrVisitTaskTMsg == null) {
//            setErrMsg(pMsg, NSZM0182E);
//            return false;
//        }
        String svcPblmCrctTpCd = getSvcPblmCrctTpCd(pMsg, glblCmpyCd, fsrNum, (String) fsrVisitAmt.get("FSR_VISIT_NUM"), svcTaskNum);
        if (!hasValue(svcPblmCrctTpCd)) {
            setErrMsg(pMsg, NSZM1361E);
            return false;
        }

//        String newDsSvcCallTpCd = convertCallType(glblCmpyCd, dsSvcCallTpCd, firstProdCtrlCd, fsrVisitTaskTMsg.svcPblmCrctTpCd.getValue(), phoneFixFlg);
        String newDsSvcCallTpCd = convertCallType(glblCmpyCd, dsSvcCallTpCd, firstProdCtrlCd, svcPblmCrctTpCd, phoneFixFlg);
        // END 2018/12/26 K.Kitachi [QC#29723, MOD]

        if (newDsSvcCallTpCd == null) {
            return true;
        }
        if (!newDsSvcCallTpCd.equals(DS_SVC_CALL_TP.PHONE_FIX_TECHNICIAN) && !newDsSvcCallTpCd.equals(DS_SVC_CALL_TP.AHS_PHONE_FIX_TECHNICIAN)) {
            return true;
        }

        setValue(svcTaskTMsg.dsSvcCallTpCd, newDsSvcCallTpCd);
        setValue(svcTaskTMsg.svcBillTpCd, SVC_BILL_TP.NO_CHRGE);

        S21ApiTBLAccessor.update(svcTaskTMsg);
        if (!checkRtnCodeForUpdate(pMsg, svcTaskTMsg)) {
            setErrMsg(pMsg, NSZM0167E);
            return false;
        }
        // START 2019/10/24 K.Fujimoto [QC#53441, ADD]
        if (!updateSvcPhysMtrReadToInvld(pMsg)) {
            setErrMsg(pMsg, NSZM0368E);
            return false;
        }
        // END   2019/10/24 K.Fujimoto [QC#53441, ADD]
        return true;
    }
    // END 2018/10/04 K.Kojima [QC#28545,ADD]
    // Add Start 2018/10/18 QC#28862
    private boolean isDeInstall(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg svcTaskTMsg = getSvcTaskFindByKey(glblCmpyCd, svcTaskNum);
        if (svcTaskTMsg == null) {
            return false;
        }

        DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = getDsSvcCallTpFindByKey(glblCmpyCd, svcTaskTMsg.dsSvcCallTpCd.getValue());
        if (dsSvcCallTpTMsg != null) {
            if (ZYPConstant.FLG_ON_Y.equals(dsSvcCallTpTMsg.svcDeinsReqFlg.getValue())) {
                return true;
            }
        }
        return false;
    }
    // Add End 2018/10/18 QC#28862

    // START 2018/12/26 K.Kitachi [QC#29723, ADD]
    private String getSvcPblmCrctTpCd(NSZC005001PMsg pMsg, String glblCmpyCd, String fsrNum, String fsrVisitNum, String svcTaskNum) {
        if (hasValue(pMsg.xxVisitTaskList.no(0).svcPblmCrctTpCd)) {
            return pMsg.xxVisitTaskList.no(0).svcPblmCrctTpCd.getValue();
        }
        FSR_VISIT_TASKTMsg fsrVisitTaskTMsg = getFsrVisiTaskFindByKey(glblCmpyCd, fsrNum, fsrVisitNum, svcTaskNum);
        if (fsrVisitTaskTMsg == null) {
            return null;
        }
        return fsrVisitTaskTMsg.svcPblmCrctTpCd.getValue();
    }
    // END 2018/12/26 K.Kitachi [QC#29723, ADD]

    // Add Start 2019/02/13 K.Fujimoto QC#30310
    private void modMsgForChangeUsage(Map<BigDecimal, FSR_USGTMsg> currentFsrUsgMap, NSZC005001PMsg pMsg) {
        if (!this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
            return;
        }

        List<String> addedList = new  ArrayList<String>();
        List<String> removedList = new  ArrayList<String>();
        List<String> changedList = new  ArrayList<String>();
        for (int i = 0; i < this.completionBean.getFsrUsgListConf().size(); i++) {
            NSZC005001_xxFsrUsgListPMsg dtlPMsg = this.completionBean.getFsrUsgListConf().get(i);
            if (currentFsrUsgMap.containsKey(dtlPMsg.fsrUsgPk.getValue())) {
                FSR_USGTMsg currentFsrUsgTMsg = currentFsrUsgMap.get(dtlPMsg.fsrUsgPk.getValue());
                if (currentFsrUsgTMsg.mdseCd.getValue().equals(dtlPMsg.mdseCd.getValue())) {
                    if (currentFsrUsgTMsg.svcPrtQty.getValue().compareTo(dtlPMsg.svcPrtQty.getValue()) != 0) {
                       if (dtlPMsg.svcPrtQty.getValue().equals(BigDecimal.ZERO)) {
                           //Add message for removed parts.
                           String msg = getModMsg(pMsg, currentFsrUsgTMsg, dtlPMsg, CONST_KEY_NSZC0050_PARTS_DEL_MSG_TMPL);
                           removedList.add(msg);
                       } else {
                           //Add message for changed parts.
                           String msg = getModMsg(pMsg, currentFsrUsgTMsg, dtlPMsg, CONST_KEY_NSZC0050_PARTS_MOD_MSG_TMPL);
                           changedList.add(msg);
                       }
                    }
                } else {
                    //Add message for removed parts.
                    String msg = getModMsg(pMsg, currentFsrUsgTMsg, dtlPMsg, CONST_KEY_NSZC0050_PARTS_DEL_MSG_TMPL);
                    removedList.add(msg);

                    //Add message for added parts.
                    msg = getModMsg(pMsg, currentFsrUsgTMsg, dtlPMsg, CONST_KEY_NSZC0050_PARTS_ADD_MSG_TMPL);
                    addedList.add(msg);
                }

            } else {
                //Add message for added parts.
                String msg = getModMsg(pMsg, null, dtlPMsg, CONST_KEY_NSZC0050_PARTS_ADD_MSG_TMPL);
                addedList.add(msg);

            }
        }
        //combine messages.
        StringBuffer sb = new StringBuffer();
        if (hasValue(this.completionBean.getChangePartsMsg())) {
            sb.append(this.completionBean.getChangePartsMsg());
        }
        //added Msg
        Collections.sort(addedList);
        for (String addedStr : addedList) {
            sb.append(addedStr);
        }
        //removed Msg
        Collections.sort(removedList);
        for (String removedStr : removedList) {
            sb.append(removedStr);
        }
        //changed Msg
        Collections.sort(changedList);
        for (String changedStr : changedList) {
            sb.append(changedStr);
        }
        this.completionBean.setChangePartsMsg(sb.toString());
    }

    private void modMsgForChangeUsageForChargeble(NSZC005001PMsg pMsg, String svcTaskNum, Map<BigDecimal, NSZC005001_xxChargesListPMsg> partsChrgDiffMap) {
        if (!this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
            return;
        }
        List<String> removedList = new  ArrayList<String>();
        List<String> changedList = new  ArrayList<String>();

        FSR_USGTMsgArray  fsrUsgList = getFsrUsgListBySvcTaskNum(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), svcTaskNum);
        for (int i = 0; i < fsrUsgList.getValidCount(); i++) {
            FSR_USGTMsg currentFsrUsgTMsg = fsrUsgList.no(i);
            if (!partsChrgDiffMap.containsKey(currentFsrUsgTMsg.fsrUsgPk.getValue())) {
                continue;
            }
            NSZC005001_xxChargesListPMsg chrgPMsg = partsChrgDiffMap.get(currentFsrUsgTMsg.fsrUsgPk.getValue());
            NSZC005001_xxFsrUsgListPMsg dtlPMsg = new NSZC005001_xxFsrUsgListPMsg();
            setValue(dtlPMsg.fsrUsgPk, currentFsrUsgTMsg.fsrUsgPk);
            setValue(dtlPMsg.mdseCd, currentFsrUsgTMsg.mdseCd);
            setValue(dtlPMsg.svcPrtQty, chrgPMsg.svcChrgQty.getValue().add(currentFsrUsgTMsg.svcPrtQty.getValue()));

            if (currentFsrUsgTMsg.mdseCd.getValue().equals(dtlPMsg.mdseCd.getValue())) {
                if (dtlPMsg.svcPrtQty.getValue().equals(BigDecimal.ZERO)) {
                    //Add message for removed parts.
                    String msg = getModMsg(pMsg, currentFsrUsgTMsg, dtlPMsg, CONST_KEY_NSZC0050_PARTS_DEL_MSG_TMPL);
                    removedList.add(msg);
                } else {
                    //Add message for changed parts.
                    String msg = getModMsg(pMsg, currentFsrUsgTMsg, dtlPMsg, CONST_KEY_NSZC0050_PARTS_MOD_MSG_TMPL);
                    changedList.add(msg);
                }
            }
        }
        //combine messages.
        StringBuffer sb = new StringBuffer();
        if (hasValue(this.completionBean.getChangePartsMsg())) {
            sb.append(this.completionBean.getChangePartsMsg());
        }
        //removed Msg
        Collections.sort(removedList);
        for (String removedStr : removedList) {
            sb.append(removedStr);
        }
        //changed Msg
        Collections.sort(changedList);
        for (String changedStr : changedList) {
            sb.append(changedStr);
        }
        this.completionBean.setChangePartsMsg(sb.toString());
    }

    private String getModMsg(NSZC005001PMsg pMsg, FSR_USGTMsg currentFsrUsgTMsg, NSZC005001_xxFsrUsgListPMsg dtlPMsg, String msgTmplNm) {
        String dtlNoteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue(msgTmplNm, pMsg.glblCmpyCd.getValue());
        String msg = null;
        if (msgTmplNm.equals(CONST_KEY_NSZC0050_PARTS_ADD_MSG_TMPL)) {
            msg = String.format(dtlNoteTxtTmpl, dtlPMsg.mdseCd.getValue(), dtlPMsg.svcPrtQty.getValue().intValue());
        } else if (msgTmplNm.equals(CONST_KEY_NSZC0050_PARTS_MOD_MSG_TMPL)) {
            msg = String.format(dtlNoteTxtTmpl, dtlPMsg.mdseCd.getValue(), currentFsrUsgTMsg.svcPrtQty.getValue().intValue(), dtlPMsg.svcPrtQty.getValue().intValue());
        } else if (msgTmplNm.equals(CONST_KEY_NSZC0050_PARTS_DEL_MSG_TMPL)) {
            msg = String.format(dtlNoteTxtTmpl, currentFsrUsgTMsg.mdseCd.getValue(), currentFsrUsgTMsg.svcPrtQty.getValue().intValue());
        }
        return msg;
    }
    // Add End   2019/02/13 K.Fujimoto QC#30310

    // add start 2019/08/09 QC#52105
    private boolean updateSvcPhysMtrReadToInvld(NSZC005001PMsg pMsg) {
        List<BigDecimal> svcPhysMtrReadGrpSqList = getSvcPhysMtrReadGrpSq(pMsg, null);

        for (BigDecimal svcPhysMtrReadGrpSq : svcPhysMtrReadGrpSqList) {
            SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
            inMsg.setSQLID("007");
            inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
            SVC_PHYS_MTR_READTMsgArray tMsgArray = (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);

            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                SVC_PHYS_MTR_READTMsg tMsg = tMsgArray.no(i);
                setValue(tMsg.vldMtrFlg, ZYPConstant.FLG_OFF_N);
                S21ApiTBLAccessor.update(tMsg);
                if (!checkRtnCodeForUpdate(pMsg, tMsg)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean existsAdjMtrEntry(NSZC005001PMsg pMsg) {
        List<BigDecimal> svcPhysMtrReadGrpSqList = getSvcPhysMtrReadGrpSq(pMsg, DS_MTR_READ_TP.ADJUSTMENT);
        if (svcPhysMtrReadGrpSqList.size() > 0) {
            return true;
        }
        return false;
    }

    private List<BigDecimal> getSvcPhysMtrReadGrpSq(NSZC005001PMsg pMsg, String dsMtrReadTpCd) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        paramMap.put("svcTaskNum", this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue());
        paramMap.put("vldMtrFlgY", ZYPConstant.FLG_ON_Y);
        paramMap.put("dsMtrReadTpCd", dsMtrReadTpCd);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcPhysMtrReadGrpSq", paramMap);
    }
    // add end 2019/08/09 QC#52105
    // Add Start 2019/10/24 K.Fujimoto QC#53441
    private void setMtrReadTp(NSZC005001PMsg pMsg) {
        List<Map<String, Object>> latestMtrReadList = getLatestInSvcPhysMtrReadGrp(pMsg);
        if (latestMtrReadList == null) {
            return;
        }
        Map<String, String> mtrTpMap = new HashMap<String, String>();
        for (Map<String, Object> latestMtrRead : latestMtrReadList) {
            mtrTpMap.put((String) latestMtrRead.get("MTR_LB_CD"), (String) latestMtrRead.get("CNTR_RESET_TP_CD"));
        }
        if (mtrTpMap.isEmpty()) {
            return;
        }

        for (int i = 0; i < pMsg.xxMtrReadList.getValidCount(); i++) {
            NSZC005001_xxMtrReadListPMsg xxMtrRead = pMsg.xxMtrReadList.no(i);
            String cntrResetTpCd = mtrTpMap.get(xxMtrRead.mtrLbCd.getValue());
            if (hasValue(cntrResetTpCd)) {
                if (cntrResetTpCd.equals(CNTR_RESET_TP.METER_ROLLOVER)) {
                    setValue(xxMtrRead.dsMtrReadTpCd, DS_MTR_READ_TP.ROLLOVER);
                } else if (cntrResetTpCd.equals(CNTR_RESET_TP.METER_EXCHANGE)) {
                    setValue(xxMtrRead.dsMtrReadTpCd, DS_MTR_READ_TP.EXCHANGE_METER_TO);
                // add start 2019/11/27 QC#54837
                } else if (cntrResetTpCd.equals(DS_MTR_READ_TP.ADJUSTMENT)) {
                    setValue(xxMtrRead.dsMtrReadTpCd, DS_MTR_READ_TP.ADJUSTMENT);
                // add end 2019/11/27 QC#54837
                }
            }
        }
    }

    private List<Map<String, Object>> getLatestInSvcPhysMtrReadGrp(NSZC005001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        paramMap.put("svcTaskNum", this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue());
        paramMap.put("vldMtrFlgY", ZYPConstant.FLG_ON_Y);
        paramMap.put("inMtr", DS_TEST_COPY_CLS.TEST_COPY_IN);
        // add start 2019/11/27 QC#54837
        paramMap.put("dsMtrReadTpAdjust", DS_MTR_READ_TP.ADJUSTMENT);
        // add end 2019/11/27 QC#54837
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getLatestInSvcPhysMtrReadGrp", paramMap);
    }
    // End Start 2019/10/24 K.Fujimoto QC#53441

    // Add Start 2019/08/30 K.Fujimoto QC#52128
    private BigDecimal getSvcPhysMtrDigitNum(NSZC005001PMsg pMsg, String mtrLbCd) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        paramMap.put("mtrLbCd", mtrLbCd);
        return (BigDecimal) ssmBatchClient.queryObject("getSvcPhysMtrDigitNum", paramMap);
    }
    // Add End   2019/08/30 K.Fujimoto QC#52128

    // START 2019/10/15 K.Fujimoto [QC#54115,MOD]
    private String isFirstVisitDeinsReq(NSZC005001PMsg pMsg, String fsrNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", fsrNum);
        return (String) ssmBatchClient.queryObject("isFirstVisitDeinsReq", paramMap);
    }
    // END   2019/10/15 K.Fujimoto [QC#54115,MOD]
    // START 2019/11/18 K.Fujimoto [QC#54391, ADD]
    private boolean isOnsSprtCall(NSZC005001PMsg pMsg, String svcTask) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcTaskNum", svcTask);
        String onsSprtCallFlg = (String) ssmBatchClient.queryObject("getOnsSprtCallFlg", paramMap);
        if (!ZYPCommonFunc.hasValue(onsSprtCallFlg)) {
            return false;
        }
        if (onsSprtCallFlg.equals(ZYPConstant.FLG_OFF_N)) {
            return false;
        }
        return true;
    }

    private boolean existsOnsSprtDebriefError(NSZC005001PMsg pMsg) {
        if (this.completionBean.isOnsSprtCall()) {
            return false;
        }
        SVC_PBLM_CRCT_TPTMsg tMsg = getSvcPblmCrctTpFindyByKey(pMsg.glblCmpyCd.getValue(), MULTIPLICATION, pMsg.xxVisitTaskList.no(0).svcPblmCrctTpCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(tMsg.onsSprtCallFlg.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * In case the call is L2 call and the correction type is L2-Amazon process,
     * create follow up call that fsr is new number.
     * @param pMsg
     * @return boolean if the method have error, return false.
     */
    private boolean createFollowUpCallForOnsSprt(NSZC005001PMsg pMsg, String onsSprtCallFlg) {
        if(!ZYPCommonFunc.hasValue(onsSprtCallFlg) || onsSprtCallFlg.equals(ZYPConstant.FLG_OFF_N)) {
          return true;
        }
        //If the task has been submitted even once, don't create new task. Because new task has been already created at first submit.
        String svcCmntTxt = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_L2_FOLL_UP_CREATE_MEMO, pMsg.glblCmpyCd.getValue());
        if (isAlreadyCreatedCall(pMsg, svcCmntTxt)) {
            return true;
        }
        //get isAhsFlg
        String dsSvcCallTpCd = getDsSvcCallTpCd(pMsg);

        //Create FSR
        NSZC043001PMsg fsrApiMsg = new NSZC043001PMsg();
        setNSZC0430ForOnsSprt(fsrApiMsg, pMsg, dsSvcCallTpCd);
        new NSZC043001().execute(fsrApiMsg, onbatchType);
        if (!checkErrMsg(pMsg, fsrApiMsg)) {
            return false;
        }

        if (ZYPCommonFunc.hasValue(svcCmntTxt)) {
            StringBuffer sb = new StringBuffer();
            sb.append(svcCmntTxt);
            sb.append(fsrApiMsg.taskList.no(0).svcTaskNum.getValue());
            if (!insertSvcMemoForOnsSprt(pMsg, sb.toString())) {
                return false;
            }
        }
        return true;
    }

    private boolean insertSvcMemoForOnsSprt(NSZC005001PMsg pMsg, String message) {

        SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
        setValue(svcMemoTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(svcMemoTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(svcMemoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.DISPATCH_MEMO);
        setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.GENERAL);
        setValue(svcMemoTMsg.svcCmntTxt, message);
        setValue(svcMemoTMsg.svcTaskNum, this.completionBean.getFsrVisitTMsg().svcTaskNum);
        setValue(svcMemoTMsg.fsrNum, pMsg.fsrNum);
        setValue(svcMemoTMsg.lastUpdUsrId, pMsg.userId);
        setValue(svcMemoTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(FORMAT_TS));
        setValue(svcMemoTMsg.svcMemoTrxNum, this.completionBean.getFsrVisitTMsg().fsrVisitNum);
        setValue(svcMemoTMsg.svcMemoTrxNm, SVC_MEMO_TRX_NM_FSR_VISIT_NUM);
        S21ApiTBLAccessor.create(svcMemoTMsg);
        if (!checkRtnCodeForInsert(pMsg, svcMemoTMsg)) {
            return false;
        }
        return true;
    }

    private boolean isAlreadyCreatedCall(NSZC005001PMsg pMsg, String message) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcTaskNum", this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue());
        paramMap.put("message", message);
        String isAlreadyCreatedCalllFlg = (String) ssmBatchClient.queryObject("isAlreadyCreatedCallFlg", paramMap);
        if (!ZYPCommonFunc.hasValue(isAlreadyCreatedCalllFlg)) {
            return false;
        }
        return true;
    }
    private boolean isRcll(NSZC005001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        paramMap.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        paramMap.put("sysDt", this.sysDt);
        String isRcllFlg = (String) ssmBatchClient.queryObject("isRcllFlg", paramMap);
        if (!ZYPCommonFunc.hasValue(isRcllFlg)) {
            return false;
        }
        if (isRcllFlg.equals(ZYPConstant.FLG_OFF_N)) {
            return false;
        }
        return true;
    }

    private String getDsSvcCallTpCd(NSZC005001PMsg pMsg) {
        boolean isRcll = isRcll(pMsg);
        if (isRcll) {
            return DS_SVC_CALL_TP.RECALL;
        }
        return DS_SVC_CALL_TP.SERV_CALL;
    }

    private void setNSZC0430ForOnsSprt(NSZC043001PMsg fsrApiPmsg, NSZC005001PMsg pMsg, String dsSvcCallTpCd) {
        setValue(fsrApiPmsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(fsrApiPmsg.xxModeCd, NSZC043001Constant.MODE_CREATE_FSR);
        setValue(fsrApiPmsg.userId, pMsg.userId);
        setValue(fsrApiPmsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValue(fsrApiPmsg.svcChrgContFlg, ZYPConstant.FLG_OFF_N);
        setValue(fsrApiPmsg.bypsPrfTechFlg, ZYPConstant.FLG_OFF_N);
        setValue(fsrApiPmsg.dsSvcCallTpCd, dsSvcCallTpCd);
        setValue(fsrApiPmsg.svcTaskRcvDt, this.sysDt);
        setValue(fsrApiPmsg.svcTaskRcvTm, this.sysTm);
        setValue(fsrApiPmsg.machDownFlg, getMachDownFlg(pMsg));
        setValue(fsrApiPmsg.svcPblmTpCd, pMsg.xxVisitTaskList.no(0).svcPblmTpCd);
        setValue(fsrApiPmsg.svcCallSrcTpCd, SVC_CALL_SRC_TP.TECH);
        setValue(fsrApiPmsg.slsDt, pMsg.slsDt);
        setValue(fsrApiPmsg.xxRqstSendFlg, ZYPConstant.FLG_ON_Y);
        setValue(fsrApiPmsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        setValue(fsrApiPmsg.custEmlAddr, getCustEmlAddr(pMsg));
        setValue(fsrApiPmsg.custTelNum, this.completionBean.getSvcTaskTMsg().custTelNum);
        setValue(fsrApiPmsg.custTelExtnNum, this.completionBean.getSvcTaskTMsg().custTelExtnNum);
        setValue(fsrApiPmsg.svcCustAttnTxt, this.completionBean.getSvcTaskTMsg().svcCustAttnTxt);
        fsrApiPmsg.taskList.setValidCount(1);
        //Memo
        String svcCmntTxt = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_L2_FOLL_UP_MEMO, pMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(svcCmntTxt)) {
            StringBuffer sb = new StringBuffer();
            sb.append(svcCmntTxt);
            sb.append(this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue());
            setValue(fsrApiPmsg.svcMemoList.no(0).svcCmntTxt, sb.toString());
            setValue(fsrApiPmsg.svcMemoList.no(0).svcMemoTpCd, SVC_MEMO_TP.MEMO);
            fsrApiPmsg.svcMemoList.setValidCount(1);
        }
    }

    private String getCustEmlAddr(NSZC005001PMsg pMsg) {
        String custEmlAddr = this.completionBean.getSvcTaskTMsg().custEmlAddr.getValue();
        String sendRptEmlAddr = pMsg.xxVisitTaskList.no(0).sendRptEmlAddr.getValue();
        if (hasValue(sendRptEmlAddr)) {
            return sendRptEmlAddr;
        }
        if (ZYPCommonFunc.hasValue(custEmlAddr)) {
            return custEmlAddr;
        }
        String constEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_SVC_ISTL_NOT_EXIST_EML_ADDR, pMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(constEmlAddr)) {
            custEmlAddr = constEmlAddr;
        } else {
            custEmlAddr = NOT_EXIST;
        }
        return custEmlAddr;
    }
    // END   2019/11/18 K.Fujimoto [QC#54391, ADD]

    // add start 2019/11/27 QC#54837
    private boolean updateSvcPhysMtrReadToInvld(NSZC005001PMsg pMsg, List<BigDecimal> svcPhysMtrReadGrpSqList) {
        for (BigDecimal svcPhysMtrReadGrpSq : svcPhysMtrReadGrpSqList) {
            SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
            inMsg.setSQLID("007");
            inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
            SVC_PHYS_MTR_READTMsgArray tMsgArray = (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);

            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                SVC_PHYS_MTR_READTMsg tMsg = tMsgArray.no(i);
                setValue(tMsg.vldMtrFlg, ZYPConstant.FLG_OFF_N);
                S21ApiTBLAccessor.update(tMsg);
                if (!checkRtnCodeForUpdate(pMsg, tMsg)) {
                    return false;
                }
            }
        }
        return true;
    }
   // add end 2019/11/27 QC#54837

    // add start 2020/04/08 QC#56328
    private List<BigDecimal> getSvcModSendClickByTaskNum(String glblCmpyCd, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcModSendClickByTaskNum", paramMap);
    }
    // add end 2020/04/08 QC#56328

    // START 2020/06/01 K.Kitachi [QC#56271, ADD]
    private void setFsrVisitDisptDtTm(NSZC005001PMsg pMsg, FSR_VISITTMsg fsrVisitTMsg) {
        for (int i = 0; i < pMsg.xxTmEventList.getValidCount(); i++) {
            NSZC005001_xxTmEventListPMsg xxTmEvent = pMsg.xxTmEventList.no(i);
            if (SVC_TM_EVENT.TRAVEL.equals(xxTmEvent.svcTmEventCd.getValue())) {
                if (!hasValue(xxTmEvent.svcTmEventFromDt) || !hasValue(xxTmEvent.svcTmEventFromTm)) {
                    continue;
                }
                setValue(fsrVisitTMsg.fsrVisitDisptDt, xxTmEvent.svcTmEventFromDt);
                setValue(fsrVisitTMsg.fsrVisitDisptTm, xxTmEvent.svcTmEventFromTm);
                return;
            }
        }
        if (!hasValue(fsrVisitTMsg.fsrVisitDisptDt) || !hasValue(fsrVisitTMsg.fsrVisitDisptTm) || !hasValue(pMsg.fsrVisitArvDt) || !hasValue(pMsg.fsrVisitArvTm)) {
            return;
        }
        String disptTs = S21StringUtil.concatStrings(fsrVisitTMsg.fsrVisitDisptDt.getValue(), fsrVisitTMsg.fsrVisitDisptTm.getValue());
        String arvTs = S21StringUtil.concatStrings(pMsg.fsrVisitArvDt.getValue(), pMsg.fsrVisitArvTm.getValue());
        if (disptTs.compareTo(arvTs) > 0) {
            setValue(fsrVisitTMsg.fsrVisitDisptDt, pMsg.fsrVisitArvDt);
            setValue(fsrVisitTMsg.fsrVisitDisptTm, pMsg.fsrVisitArvTm);
        }
    }
    // END 2020/06/01 K.Kitachi [QC#56271, ADD]

    // START 2020/07/29 K.Kitachi [QC#57410, ADD]
    private boolean isInMeterRead(String dsTestCopyClsCd) {
        if (DS_TEST_COPY_CLS.TEST_COPY_IN.equals(dsTestCopyClsCd)) {
            return true;
        }
        return false;
    }

    private boolean isOutMeterRead(String dsTestCopyClsCd) {
        if (DS_TEST_COPY_CLS.TEST_COPY_OUT.equals(dsTestCopyClsCd)) {
            return true;
        }
        return false;
    }

    private boolean isMeterReadSkip(String mtrVisTpCd) {
        if (String.valueOf(BigDecimal.ONE).equals(mtrVisTpCd)) {
            return true;
        }
        if (ZYPConstant.FLG_OFF_N.equals(mtrVisTpCd)) {
            return true;
        }
        return false;
    }

    private boolean updateSvcTaskToMtrVisTp(NSZC005001PMsg pMsg) {
        if (!hasValue(pMsg.arvMtrVisTpCd) && !hasValue(pMsg.cpltMtrVisTpCd)) {
            return true;
        }
        SVC_TASKTMsg svcTask = getSvcTaskFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), this.completionBean.getSvcTaskTMsg().svcTaskNum.getValue());
        if (!checkRtnCodeForSearch(pMsg, svcTask, new SVC_TASKTMsg())) {
            return false;
        }
        setValue(svcTask.arvMtrVisTpCd, pMsg.arvMtrVisTpCd);
        setValue(svcTask.cpltMtrVisTpCd, pMsg.cpltMtrVisTpCd);
        S21ApiTBLAccessor.update(svcTask);
        if (!checkRtnCodeForUpdate(pMsg, svcTask)) {
            return false;
        }
        return true;
    }
    // END 2020/07/29 K.Kitachi [QC#57410, ADD]

    // START 2022/02/10 [QC#57338, ADD]
    private boolean isResponseTimeTarget(String glblCmpyCd, String dsSvcCallTpCd) {
        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsCondConstGrpId01", RSP_TM_TGT_CALL_TP);
        inMsg.setConditionValue("dsCondConstCd01", dsSvcCallTpCd);
        DS_COND_CONSTTMsgArray outArray = (DS_COND_CONSTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return false;
        }
        return true;
    }
    // END   2022/02/10 [QC#57338, ADD]

    // START 2022/04/15 [QC#59911, ADD]
    private boolean checkExistsCompleteFSREvent(String glblCmpyCd, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        paramMap.put("svcDisptEventCd", SVC_DISPT_EVENT.COMPLETE);
        int cnt = (Integer) ssmBatchClient.queryObject("countCpltFsrEvent", paramMap);
        if (0 < cnt) {
            return true;
        }
        return false;
    }
    // END   2022/04/15 [QC#59911, ADD]
    // START 2023/03/13 T.Wada [QC#61217, ADD]
    private boolean checkSvcModStsClosed(String glblCmpyCd, String svcModPlnId, BigDecimal svcMachMstrPk, String svcTaskNum ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcModPlnId", svcModPlnId);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        paramMap.put("svcTaskNum", svcTaskNum);
        paramMap.put("svcModProcStsCd", SVC_MOD_PROC_STS.CLOSED);
        int cnt = (Integer)  ssmBatchClient.queryObject("checkSvcModStsClosed", paramMap);
        if (0 < cnt) {
            return true;
        }
        return false;
    }
    // END  2023/03/13 T.Wada [QC#61217, ADD]
    // START 2023/07/14 K.Watanabe [QC#61310, ADD]
    private boolean isShowRoomMachine(String glblCmpyCd, BigDecimal svcMachMstrPk, String startDt) {
        boolean isShowRoomMachine = false;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.CREATED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.REMOVED);
        paramMap.put("svcMachMstrStsCdList", svcMachMstrStsCdList);
        paramMap.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        paramMap.put("rtlWhCatgCd", RTL_WH_CATG.SHOWROOM);
        paramMap.put("startDt", startDt);
        // START 2023/09/22 K.Watanabe [QC#61310, ADD]
        //BigDecimal showRoomMachineCnt = (BigDecimal) this.ssmBatchClient.queryObject("getSvcMachLocCnt", paramMap);
        BigDecimal showRoomMachineCnt = (BigDecimal) this.ssmBatchClient.queryObject("getShowRoomMachineCnt", paramMap);
        // END 2023/09/22 K.Watanabe [QC#61310, ADD]
        if (showRoomMachineCnt != null && showRoomMachineCnt.compareTo(BigDecimal.ZERO) > 0) {
            isShowRoomMachine = true;
        }
        return isShowRoomMachine;
    }
    // END 2023/07/14 K.Watanabe [QC#61310, ADD]
}
