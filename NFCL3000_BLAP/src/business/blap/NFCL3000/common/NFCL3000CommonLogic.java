/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3000.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL3000.NFCL3000BL06;
import business.blap.NFCL3000.NFCL3000CMsg;
import business.blap.NFCL3000.NFCL3000Query;
import business.blap.NFCL3000.NFCL3000SMsg;
import business.blap.NFCL3000.NFCL3000_ACMsg;
import business.blap.NFCL3000.NFCL3000_BCMsg;
import business.blap.NFCL3000.NFCL3000_BCMsgArray;
import business.blap.NFCL3000.NFCL3000_CCMsg;
import business.blap.NFCL3000.NFCL3000_DCMsgArray;
import business.blap.NFCL3000.constant.NFCL3000Constant;
import business.db.AJE_INV_ACCT_DISTTMsg;
import business.db.AJE_INV_ACCT_DIST_ERRTMsg;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.CNTYTMsg;
import business.db.DFRD_ACCTG_RULETMsg;
import business.db.DS_INV_LINE_TAX_DTLTMsg;
import business.db.DS_INV_SLS_CRTMsg;
import business.db.DS_INV_TPTMsg;
import business.db.INVTMsg;
import business.db.INV_BOLTMsg;
import business.db.INV_LINETMsg;
import business.db.INV_PRMO_INFOTMsg;
import business.db.INV_PRT_SLS_PART_SUB_TOTTMsg;
import business.db.JRNL_ENTRYTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PKG_UOMTMsg;
import business.db.PMT_TERMTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.S21_ORGTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SVC_ALLOC_TPTMsg;
import business.db.SVC_INVTMsg;
import business.db.SVC_INV_LINETMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SYS_SRCTMsg;
import business.db.TAX_ADJ_ITEMTMsg;
import business.db.TOCTMsg;
import business.parts.MdseTpAcct;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFZC102001PMsg;
import business.parts.NFZC309001PMsg;
import business.parts.NWZC036101PMsg;
import business.parts.NWZC036101_taxCalculateInputLinePMsg;
import business.parts.NWZC036101_taxCalculateOutputLinePMsg;
import business.parts.NFACommonJrnlEntryConstant.CoaSegment;
import business.blap.NFCL3080.NFCL3080CMsg;
import business.blap.NFCL3080.NFCL3080SMsg;
import business.blap.NFCL3080.NFCL3080Query;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NFA.NFZC103001.common.NFZC103001Common;
import com.canon.cusa.s21.api.NFC.NFZC309001.NFZC309001;
import com.canon.cusa.s21.api.NWZ.NWZC036101.NWZC036101;
import com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_FM_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_GENL_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INV_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_DEF_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SLS_TAX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_INVTY_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_SPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PRFT_TRX_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ALLOC_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/12/21   Fujitsu         T.Tanaka        Create          N/A
 * 2016/04/05   Fujitsu         T.Tanaka        Update          Def#6469
 * 2016/05/04   CSAI            K.Uramori       Update          QC#7864
 * 2016/05/17   Fujitsu         S.Fujita        Update          QC#8373
 * 2016/05/17   Fujitsu         S.Fujita        Update          QC#8366
 * 2016/05/17   Fujitsu         S.Fujita        Update          QC#8375
 * 2016/05/18   Fujitsu         S.Fujita        Update          QC#7780
 * 2016/05/19   Fujitsu         S.Fujita        Update          QC#8478
 * 2016/05/24   Fujitsu         S.Fujita        Update          QC#8522
 * 2016/06/03   Fujitsu         S.Fujita        Update          QC#9157
 * 2016/06/03   Fujitsu         S.Fujita        Update          QC#9452
 * 2016/06/16   Fujitsu         S.Fujita        Update          QC#10176
 * 2016/06/16   Fujitsu         S.Fujita        Update          QC#10138
 * 2016/06/20   Fujitsu         S.Fujita        Update          QC#9454
 * 2016/06/30   Fujitsu         S.Fujita        Update          QC#11012
 * 2016/07/05   Fujitsu         S.Fujita        Update          QC#11210
 * 2016/07/05   Fujitsu         S.Fujita        Update          QC#9992
 * 2016/07/06   Fujitsu         S.Fujita        Update          QC#10870
 * 2016/07/08   Fujitsu         S.Fujita        Update          QC#10160
 * 2016/07/08   Fujitsu         S.Fujita        Update          QC#11262
 * 2016/07/14   Fujitsu         S.Fujita        Update          QC#11157
 * 2016/07/19   Fujitsu         S.Yoshida       Update          QC#12006
 * 2016/07/20   Fujitsu         S.Yoshida       Update          QC#12149
 * 2016/07/20   Fujitsu         S.Fujita        Update          QC#10148
 * 2016/07/29   Fujitsu         S.Fujita        Update          QC#12503
 * 2016/08/03   Fujitsu         S.Fujita        Update          QC#12864
 * 2016/08/03   Fujitsu         S.Fujita        Update          QC#12870
 * 2016/08/05   Fujitsu         S.Fujita        Update          QC#13110
 * 2016/08/01   Hitachi         Y.Takeno        Update          QC#9192
 * 2016/09/05   Fujitsu         S.Fujita        Update          QC#10156
 * 2016/09/05   Fujitsu         S.Fujita        Update          QC#13648
 * 2016/09/07   Fujitsu         S.Fujita        Update          QC#11212
 * 2016/09/08   Fujitsu         S.Fujita        Update          QC#14115
 * 2016/09/12   Fujitsu         S.Fujita        Update          QC#14112
 * 2016/09/15   Fujitsu         S.Yoshida       Update          QC#13956
 * 2016/09/20   Fujitsu         S.Fujita        Update          QC#13795
 * 2016/09/21   Fujitsu         S.Yoshida       Update          QC#11049,14463
 * 2016/09/26   Fujitsu         S.Fujita        Update          QC#13362
 * 2016/10/03   Fujitsu         S.Fujita        Update          QC#14120
 * 2016/10/03   Fujitsu         S.Fujita        Update          QC#13206
 * 2016/10/05   Fujitsu         S.Fujita        Update          QC#15013
 * 2016/10/06   Fujitsu         S.Fujita        Update          QC#10522
 * 2016/10/14   Fujitsu         S.Fujita        Update          QC#10281
 * 2016/10/18   Fujitsu         S.Fujita        Update          QC#13641
 * 2016/10/24   Fujitsu         T.Murai         Update          QC#13656
 * 2016/10/24   Fujitsu         S.Fujita        Update          QC#15530
 * 2016/10/25   Fujitsu         S.Fujita        Update          QC#13645
 * 2016/10/26   Fujitsu         T.Murai         Update          QC#13639,15495
 * 2016/11/17   Fujitsu         T.Murai         Update          QC#14096,15987
 * 2016/11/22   Fujitsu         S.Fujita        Update          QC#16154
 * 2016/11/29   Fujitsu         S.Fujita        Update          QC#16075
 * 2016/12/08   Fujitsu         T.Murai         Update          QC#16174,16387
 * 2016/12/20   Fujitsu         S.Yoshida       Update          QC#16181
 * 2017/01/19   Fujitsu         S.Fujita        Update          QC#17136
 * 2017/03/09   Fujitsu         T.Murai         Update          QC#17901
 * 2017/03/13   Fujitsu         T.Murai         Update          QC#17933
 * 2017/03/17   Fujitsu         T.Murai         Update          QC#14205
 * 07/18/2017   CITS            K.Ogino         Update          QC#19433
 * 2017/07/18   Fujitsu         H.Ikeda         Update          QC#19781
 * 2017/10/23   Fujitsu         T.Aoi           Update          QC#20719
 * 2017/12/25   Hitachi         E.Kameishi      Update          QC#20312
 * 2018/01/24   Hitachi         E.Kameishi      Update          QC#20312-1
 * 2018/01/25   Hitachi         E.Kameishi      Update          QC#22697
 * 2018/02/23   Hitachi         E.Kameishi      Update          QC#24390
 * 2018/03/14   Hitachi         Y.Takeno        Update          QC#24680
 * 2018/03/29   Hitachi         E.Kameishi      Update          QC#24390
 * 2018/05/22   Fujitsu         Y.Matsui        Update          QC#21841
 * 2018/05/28   Fujitsu         Y.Matsui        Update          QC#26342
 * 2018/06/01   Fujitsu         Y.Matsui        Update          QC#21841
 * 2018/06/06   Fujitsu         T.Ogura         Update          QC#21159
 * 2018/07/17   Hitachi         E.Kameishi      Update          QC#27007
 * 2018/08/15   Fujitsu         Y.Matsui        Update          QC#27651
 * 2018/08/21   Hitachi         E.Kameishi      Update          QC#27791
 * 2018/08/30   Fujitsu         Y.Matsui        Update          QC#27829
 * 2018/09/04   Hitachi         E.Kameishi      Update          QC#27848
 * 2018/09/19   Fujitsu         S.Ohki          Update          QC#28089
 * 2018/09/20   Fujitsu         S.Ohki          Update          QC#28228
 * 2018/09/28   Fujitsu         T.Ogura         Update          QC#28526
 * 2018/10/24   Fujitsu         S.Takami        Update          QC#27069
 * 2018/11/02   Fujitsu         S.Ohki          Update          QC#29059
 * 2018/11/06   Fujitsu         S.Ohki          Update          QC#29059-2
 * 2018/12/12   Fujitsu         Y.Matsui        Update          QC#29587
 * 2019/01/15   Hitachi         E.Kameishi      Update          QC#29734
 * 2019/04/11   Fujitsu         S.Takami        Update          QC#31165
 * 2019/04/15   Fujitsu         S.Takami        Update          QC#31188
 * 2019/04/15   Fujitsu         S.Takami        Update          QC#31190
 * 2019/04/25   Fujitsu         S.Takami        Update          QC#50078
 * 2019/05/10   Fujitsu         S.Takami        Update          QC#50148
 * 2019/05/16   Fujitsu         S.Takami        Update          QC#50374
 * 2019/05/27   Fujitsu         S.Takami        Update          QC#50541
 * 2019/05/29   Fujitsu         S.Takami        Update          QC#50542
 * 2019/05/29   Fujitsu         Y.Matsui        Update          QC#50078
 * 2019/05/31   Fujitsu         S.Takami        Update          QC#50633
 * 2019/06/04   Fujitsu         S.Takami        Update          QC#50601
 * 2019/06/05   Fujitsu         S.Takami        Update          QC#50685
 * 2019/06/06   Fujitsu         S.Takami        Update          QC#50691
 * 2019/07/11   Fujitsu         S.Takami        Update          QC#51334
 * 2019/07/17   Fujitsu         S.Takami        Update          QC#51516
 * 2019/08/07   Fujitsu         S.Takami        Update          QC#52447
 * 2019/08/07   Fujitsu         S.Takami        Update          QC#52453
 * 2019/09/09   Fujitsu         H.Ikeda         Update          QC#53251
 * 2019/09/21   Fujitsu         S.Takami        Update          QC#53633
 * 2019/10/02   Fujitsu         S.Takami        Update          QC#53633-2
 * 2019/10/02   Fujitsu         T.Murai         Update          QC#53639
 * 2019/10/03   Fujitsu         S.Takami        Update          QC#53881
 * 2019/11/02   Fujitsu         S.Matsui        Update          QC#54463
 * 2019/11/15   Fujitsu         H.Ikeda         Update          QC#54697
 * 2019/11/21   Fujitsu         H.Nagashima     Update          QC#54746
 * 2019/11/26   Fujitsu         H.Nagashima     Update          QC#54644
 * 2020/01/29   Fujitsu         H.Mizukami      Update          QC#55493
 * 2020/03/27   Fujitsu         H.Mizukami      Update          QC#56158
 * 2020/04/14   Fujitsu         H.Mizukami      Update          QC#56319
 * 2020/04/22   Fujitsu         H.Mizukami      Update          QC#56117
 * 2021/01/04   CITS            R.Kurahashi     Update          QC#56282
 * 2021/10/25   CITS            S.Go            Update          QC#55215
 * 2023/11/08   CITS            M.Kobayashi     Update          QC#62071
 *</pre>
 */
public class NFCL3000CommonLogic implements NFCL3000Constant {

    private static DefCoaValues defCoa = null;

    /** Print Tax Calculation API parameters if this value is true. */
    private static final boolean TAX_CALC_PRM_PRINT = false;
    /**
     * initPullDownCreate
     * @param pulldownCd
     * @param pulldownName
     * @param pullDownList
     * @param dbColumn
     */
    private static void initPullDownCreate(EZDCStringItemArray pulldownCd, EZDCStringItemArray pulldownName, List<Map> pullDownList, String[] dbColumn) {
        pulldownCd.clear();
        pulldownName.clear();

        for (int i = 0; i < pullDownList.size(); i++) {

            Map pullDownData = pullDownList.get(i);

            pulldownCd.no(i).setValue((String) pullDownData.get(dbColumn[0]));
            ZYPEZDItemValueSetter.setValue(pulldownName.no(i), (String)pullDownData.get(dbColumn[1]));
        }
    }

// START 2016/08/01 S.Fujita [QC#10148,DEL]
//    /**
//     * initPullDownCreate
//     * @param pulldownCd
//     * @param pulldownName
//     * @param pullDownList
//     * @param dbColumn
//     */
//    private static void initPullDownCreate(EZDCBigDecimalItemArray pulldownCd, EZDCStringItemArray pulldownName, List<Map> pullDownList, String[] dbColumn) {
//
//        pulldownCd.clear();
//        pulldownName.clear();
//
//        for (int i = 0; i < pullDownList.size(); i++) {
//
//            Map pullDownData = pullDownList.get(i);
//
//            pulldownCd.no(i).setValue((BigDecimal) pullDownData.get(dbColumn[0]));
//            pulldownName.no(i).setValue((String) pullDownData.get(dbColumn[1]));
//        }
//    }
// END   2016/08/01 S.Fujita [QC#10148,DEL]

    /**
     * createPulldownListInvTp
     * @param bizMsg
     */
    public static void createPulldownListInvTp(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invTpCd", INV_TP.DEBIT_MEMO);
        S21SsmEZDResult invTp = NFCL3000Query.getInstance().getInvTpPullDownList(bizMsg, ssmParam);

        if (invTp.isCodeNormal()) {
            List<Map> invTpList = (List<Map>) invTp.getResultObject();
            // START 2016/08/01 S.Fujita [QC#10148,MOD]
            initPullDownCreate(bizMsg.invTpCd_LC, bizMsg.invTpNm_LD, invTpList, new String[] {"INV_TP_CD", "INV_TP_NM" });
//            initPullDownCreate(bizMsg.invTpCd_LC, bizMsg.invTpDescTxt_LD, invTpList, new String[] {"INV_TP_CD", "INV_TP_DESC_TXT" });
            // END   2016/08/01 S.Fujita [QC#10148,MOD]

            // START 2016/07/06 S.Fujita [QC#10870,DEL]
//            bizMsg.invTpCd_H1.setValue(invTpList.get(0).get("INV_TP_CD").toString());
            // END   2016/07/06 S.Fujita [QC#10870,DEL]
        }
    }

    /**
     * createPulldownListDsInvTp
     * @param bizMsg
     */
    public static void createPulldownListDsInvTp(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invTpCd", bizMsg.invTpCd_H1.getValue());
        S21SsmEZDResult dsInvTp = NFCL3000Query.getInstance().getDsInvTpPullDownList(bizMsg, ssmParam);

        if (dsInvTp.isCodeNormal()) {
            List<Map> dsInvTpList = (List<Map>) dsInvTp.getResultObject();
            // START 2016/08/01 S.Fujita [QC#10148,MOD]
            initPullDownCreate(bizMsg.dsInvTpCd_LC, bizMsg.dsInvTpNm_LD, dsInvTpList, new String[] {"DS_INV_TP_CD", "DS_INV_TP_NM" });
//            initPullDownCreate(bizMsg.dsInvTpCd_LC, bizMsg.dsInvTpDescTxt_LD, dsInvTpList, new String[] {"DS_INV_TP_CD", "DS_INV_TP_DESC_TXT" });
            // END   2016/08/01 S.Fujita [QC#10148,MOD]

            // START 2016/07/06 S.Fujita [QC#10870,DEL]
//            bizMsg.dsInvTpCd_H1.setValue(dsInvTpList.get(0).get("DS_INV_TP_CD").toString());
            // END   2016/07/06 S.Fujita [QC#10870,DEL]
        }
    }

    /**
     * createPulldownListPmtTermCashDisc
     * @param bizMsg
     */
    public static void createPulldownListPmtTermCashDisc(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        // START 2016/06/16 S.Fujita [QC#10176,QC#11212,ADD]
        // Get PMT_TERM_CASH_DISC
        String pmtTermCashDiscCd = ZYPCodeDataUtil.getVarCharConstValue("PMT_TERM_IM", bizMsg.glblCmpyCd.getValue());
        String ccPmtTermCashDisc = ZYPCodeDataUtil.getVarCharConstValue("CC_CASH_DISC_CD", bizMsg.glblCmpyCd.getValue());
        // START 2018/09/20 S.Ohki [QC#28228,MOD]
//        if(bizMsg.invTpCd_H1.getValue().equals(INV_TP.CREDIT_MEMO)) {
        if(bizMsg.invTpCd_H1.getValue().equals(INV_TP.CREDIT_MEMO) && SYS_SRC.S21_ACCOUNTING_AR.equals(bizMsg.sysSrcCd_H1.getValue())) {
        // END 2018/09/20 S.Ohki [QC#28228,MOD]
            ssmParam.put("pmtTermCashDiscCd", pmtTermCashDiscCd);
        } else {
            ssmParam.put("pmtTermCashDiscCd", "");
        }

        if(NFCL3000CommonLogic.isManualInvoice(bizMsg)) {
            ssmParam.put("ccPmtTermCashDisc", ccPmtTermCashDisc);
        } else {
            ssmParam.put("ccPmtTermCashDisc", "");
        }
        // END   2016/06/16 S.Fujita [QC#10176,QC#11212,ADD]
        S21SsmEZDResult pmtTermCashDisc = NFCL3000Query.getInstance().getPmtTermCashDiscPullDownList(bizMsg, ssmParam);

        if (pmtTermCashDisc.isCodeNormal()) {
            List<Map> pmtTermCashDiscList = (List<Map>) pmtTermCashDisc.getResultObject();
            // START 2016/08/01 S.Fujita [QC#10148,MOD]
            initPullDownCreate(bizMsg.pmtTermCashDiscCd_LC, bizMsg.pmtTermCashDiscNm_LD, pmtTermCashDiscList, new String[] {"PMT_TERM_CASH_DISC_CD", "PMT_TERM_CASH_DISC_NM" });
//            initPullDownCreate(bizMsg.pmtTermCashDiscCd_LC, bizMsg.pmtTermCashDiscDescTxt_LD, pmtTermCashDiscList, new String[] {"PMT_TERM_CASH_DISC_CD", "PMT_TERM_CASH_DISC_DESC_TXT" });
            // END   2016/08/01 S.Fujita [QC#10148,MOD]

            // START 2016/06/16 S.Fujita [QC#10176,MOD]
            if(bizMsg.invTpCd_H1.getValue().equals(INV_TP.CREDIT_MEMO)) {
//              bizMsg.pmtTermCashDiscCd_H1.setValue(pmtTermCashDiscList.get(0).get("PMT_TERM_CASH_DISC_CD").toString());
                bizMsg.pmtTermCashDiscCd_H1.setValue(pmtTermCashDiscCd);
            }
            // END   2016/06/16 S.Fujita [QC#10176,MOD]
        }
    }

    /**
     * createPulldownListDfrdInvRule
     * @param bizMsg
     */
    public static void createPulldownListDfrdInvRule(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        S21SsmEZDResult dfrdInvRule = NFCL3000Query.getInstance().getDfrdInvRulePullDownList(bizMsg, ssmParam);

        if (dfrdInvRule.isCodeNormal()) {
            List<Map> dfrdInvRuleList = (List<Map>) dfrdInvRule.getResultObject();
            // START 2016/08/01 S.Fujita [QC#10148,MOD]
            initPullDownCreate(bizMsg.dfrdInvRuleCd_LC, bizMsg.dfrdInvRuleNm_LD, dfrdInvRuleList, new String[] {"DFRD_INV_RULE_CD", "DFRD_INV_RULE_NM" });
//            initPullDownCreate(bizMsg.dfrdInvRuleCd_LC, bizMsg.dfrdInvRuleDescTxt_LD, dfrdInvRuleList, new String[] {"DFRD_INV_RULE_CD", "DFRD_INV_RULE_DESC_TXT" });
            // END   2016/08/01 S.Fujita [QC#10148,MOD]
        }
    }

    // START 2018/08/15 Y.Matsui [QC#27651,DEL]
//    /**
//     * createPulldownListPkgUom
//     * @param bizMsg
//     */
//    public static void createPulldownListPkgUom(NFCL3000CMsg bizMsg) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        S21SsmEZDResult pkgUom = NFCL3000Query.getInstance().getPkgUomPullDownList(bizMsg, ssmParam);
//
//        if (pkgUom.isCodeNormal()) {
//            List<Map> pkgUomList = (List<Map>) pkgUom.getResultObject();
//            initPullDownCreate(bizMsg.pkgUomCd_AC, bizMsg.pkgUomNm_AD, pkgUomList, new String[] {"PKG_UOM_CD", "PKG_UOM_NM" });
//        }
//    }
    // END   2018/08/15 Y.Matsui [QC#27651,DEL]

    /**
     * createPulldownListBolLineNum
     * @param bizMsg
     */
    public static void createPulldownListBolLineNum(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());        
        S21SsmEZDResult bolLineNum = NFCL3000Query.getInstance().getBolLineNumPullDownList(bizMsg, ssmParam);

        if (bolLineNum.isCodeNormal()) {
            List<Map> bolLineNumList = (List<Map>) bolLineNum.getResultObject();
            initPullDownCreate(bizMsg.invBolLineNum_AC, bizMsg.invBolLineNum_AD, bolLineNumList, new String[] {"INV_BOL_LINE_NUM", "INV_BOL_LINE_NUM" });
        }
    }

    /**
     * createPulldownListInvLineSplTp
     * @param bizMsg
     */
    public static void createPulldownListInvLineSplTp(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        S21SsmEZDResult invLineSpltp = NFCL3000Query.getInstance().getInvLineSplTpPullDownList(bizMsg, ssmParam);

        if (invLineSpltp.isCodeNormal()) {
            List<Map> invLineSpltpList = (List<Map>) invLineSpltp.getResultObject();
            // START 2016/08/01 S.Fujita [QC#10148,MOD]
            initPullDownCreate(bizMsg.invLineSplTpCd_BC, bizMsg.invLineSplTpNm_BD, invLineSpltpList, new String[] {"INV_LINE_SPL_TP_CD", "INV_LINE_SPL_TP_NM" });
//            initPullDownCreate(bizMsg.invLineSplTpCd_BC, bizMsg.invLineSplTpDescTxt_BD, invLineSpltpList, new String[] {"INV_LINE_SPL_TP_CD", "INV_LINE_SPL_TP_DESC_TXT" });
            // END   2016/86/01 S.Fujita [QC#10148,MOD]
        }
    }

    /**
     * createPulldownListDfrdAcctgRule
     * @param bizMsg
     */
    public static void createPulldownListDfrdAcctgRule(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        // START 2016/05/24 S.Fujita [QC#8522,ADD]
        if(bizMsg.invTpCd_H1.getValue().equals(INV_TP.CREDIT_MEMO)) {
            ssmParam.put("dfrdAcctgRuleCd", DFRD_ACCTG_RULE.IMMEDIATE);
        } else {
            ssmParam.put("dfrdAcctgRuleCd", "");
        }
        // END   2016/05/24 S.Fujita [QC#8522,ADD]
        S21SsmEZDResult dfrdAcctgRule = NFCL3000Query.getInstance().getDfrdAcctgRulePullDownList(bizMsg, ssmParam);

        if (dfrdAcctgRule.isCodeNormal()) {
            List<Map> dfrdAcctgRuleList = (List<Map>) dfrdAcctgRule.getResultObject();
            // START 2016/08/01 S.Fujita [QC#10148,MOD]
            initPullDownCreate(bizMsg.dfrdAcctgRuleCd_BC, bizMsg.dfrdAcctgRuleNm_BD, dfrdAcctgRuleList, new String[] {"DFRD_ACCTG_RULE_CD", "DFRD_ACCTG_RULE_NM" });
//            initPullDownCreate(bizMsg.dfrdAcctgRuleCd_BC, bizMsg.dfrdAcctgRuleDescTxt_BD, dfrdAcctgRuleList, new String[] {"DFRD_ACCTG_RULE_CD", "DFRD_ACCTG_RULE_DESC_TXT" });
            // END   2016/08/01 S.Fujita [QC#10148,MOD]
        }
    }

    /**
     * createPulldownListShpgSvcLvl
     * @param bizMsg
     */
    public static void createPulldownListShpgSvcLvl(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        S21SsmEZDResult shpgSvcLvl = NFCL3000Query.getInstance().getShpgSvcLvlPullDownList(bizMsg, ssmParam);

        if (shpgSvcLvl.isCodeNormal()) {
            List<Map> shpgSvcLvlList = (List<Map>) shpgSvcLvl.getResultObject();
            initPullDownCreate(bizMsg.shpgSvcLvlCd_DC, bizMsg.shpgSvcLvlNm_DD, shpgSvcLvlList, new String[] {"SHPG_SVC_LVL_CD", "SHPG_SVC_LVL_NM" });
        }
    }

// START 2016/09/16 S.Yoshida [QC#13956,DEL]
//    /**
//     * createPulldownListDsPmtMeth
//     * @param bizMsg
//     */
//    public static void createPulldownListDsPmtMeth(NFCL3000CMsg bizMsg) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        S21SsmEZDResult dsPmtMeth = NFCL3000Query.getInstance().getDsPmtMethPullDownList(bizMsg, ssmParam);
//
//        if (dsPmtMeth.isCodeNormal()) {
//            List<Map> dsPmtMethList = (List<Map>) dsPmtMeth.getResultObject();
//            // START 2016/08/01 S.Fujita [QC#10148,MOD]
//            initPullDownCreate(bizMsg.dsPmtMethCd_EC, bizMsg.dsPmtMethNm_ED, dsPmtMethList, new String[] {"DS_PMT_METH_CD", "DS_PMT_METH_NM" });
////            initPullDownCreate(bizMsg.dsPmtMethCd_EC, bizMsg.dsPmtMethDescTxt_ED, dsPmtMethList, new String[] {"DS_PMT_METH_CD", "DS_PMT_METH_DESC_TXT" });
//            // END   2016/08/01 S.Fujita [QC#10148,MOD]
//        }
//    }
// END   2016/09/16 S.Yoshida [QC#13956,DEL]

    /**
     * createPulldownListAjeInvAcctCls
     * @param bizMsg
     */
    public static void createPulldownListAjeInvAcctCls(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        S21SsmEZDResult ajeInvAcctCls = NFCL3000Query.getInstance().getAjeInvAcctClsPullDownList(bizMsg, ssmParam);

        if (ajeInvAcctCls.isCodeNormal()) {
            List<Map> ajeInvAcctClsList = (List<Map>) ajeInvAcctCls.getResultObject();
            // START 2016/08/01 S.Fujita [QC#10148,MOD]
            initPullDownCreate(bizMsg.ajeInvAcctClsCd_CC, bizMsg.ajeInvAcctClsNm_CD, ajeInvAcctClsList, new String[] {"AJE_INV_ACCT_CLS_CD", "AJE_INV_ACCT_CLS_NM" });
//            initPullDownCreate(bizMsg.ajeInvAcctClsCd_CC, bizMsg.ajeInvAcctClsDescTxt_CD, ajeInvAcctClsList, new String[] {"AJE_INV_ACCT_CLS_CD", "AJE_INV_ACCT_CLS_DESC_TXT" });
            // END   2016/08/01 S.Fujita [QC#10148,MOD]
        }
    }

    /**
     * createPulldownListInvProcTp
     * @param bizMsg
     */
    public static void createPulldownListInvProcTp(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        S21SsmEZDResult invProcTp = NFCL3000Query.getInstance().getInvProcTpPullDownList(bizMsg, ssmParam);

        if (invProcTp.isCodeNormal()) {
            List<Map> invProcTpList = (List<Map>) invProcTp.getResultObject();
            // START 2016/08/01 S.Fujita [QC#10148,MOD]
            initPullDownCreate(bizMsg.invProcTpCd_EC, bizMsg.invProcTpNm_ED, invProcTpList, new String[] {"INV_PROC_TP_CD", "INV_PROC_TP_NM" });
//            initPullDownCreate(bizMsg.invProcTpCd_EC, bizMsg.invProcTpDescTxt_ED, invProcTpList, new String[] {"INV_PROC_TP_CD", "INV_PROC_TP_DESC_TXT" });
            // END   2016/08/01 S.Fujita [QC#10148,MOD]
        }
    }

    /**
     * createPulldownListDsContrCatg
     * @param bizMsg
     */
    public static void createPulldownListDsContrCatg(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        S21SsmEZDResult dsContrCatg = NFCL3000Query.getInstance().getDsContrCatgPullDownList(bizMsg, ssmParam);

        if (dsContrCatg.isCodeNormal()) {
            List<Map> dsContrCatgList = (List<Map>) dsContrCatg.getResultObject();
            // START 2016/08/01 S.Fujita [QC#10148,MOD]
            // START 2018/06/06 T.Ogura [QC#21159,MOD]
//            initPullDownCreate(bizMsg.dsContrCatgCd_EC, bizMsg.dsContrCatgNm_ED, dsContrCatgList, new String[] {"DS_CONTR_CATG_CD", "DS_CONTR_CATG_NM" });
            initPullDownCreate(bizMsg.dsContrCatgCd_EC, bizMsg.dsContrCatgDescTxt_ED, dsContrCatgList, new String[] {"DS_CONTR_CATG_CD", "DS_CONTR_CATG_DESC_TXT" });
            // END   2018/06/06 T.Ogura [QC#21159,MOD]
//            initPullDownCreate(bizMsg.dsContrCatgCd_EC, bizMsg.dsContrCatgDescTxt_ED, dsContrCatgList, new String[] {"DS_CONTR_CATG_CD", "DS_CONTR_CATG_DESC_TXT" });
            // END   2016/08/01 S.Fujita [QC#10148,MOD]
        }
    }

    /**
     * createPulldownListDsOrdTp
     * @param bizMsg
     */
    public static void createPulldownListDsOrdTp(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        S21SsmEZDResult dsOrdTp = NFCL3000Query.getInstance().getDsOrdTpPullDownList(bizMsg, ssmParam);

        if (dsOrdTp.isCodeNormal()) {
            List<Map> dsOrdTpList = (List<Map>) dsOrdTp.getResultObject();
            initPullDownCreate(bizMsg.dsOrdTpCd_EC, bizMsg.dsOrdTpDescTxt_ED, dsOrdTpList, new String[] {"DS_ORD_TP_CD", "DS_ORD_TP_DESC_TXT" });
        }
    }

    /**
     * createPulldownListArCashApplySys
     * @param bizMsg
     */
    public static void createPulldownListArCashApplySys(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        S21SsmEZDResult arCashApplySts = NFCL3000Query.getInstance().getArCashApplyStsPullDownList(bizMsg, ssmParam);

        if (arCashApplySts.isCodeNormal()) {
            List<Map> arCashApplyStsList = (List<Map>) arCashApplySts.getResultObject();
            // START 2016/08/01 S.Fujita [QC#10148,MOD]
            initPullDownCreate(bizMsg.arCashApplyStsCd_EC, bizMsg.arCashApplyStsNm_ED, arCashApplyStsList, new String[] {"AR_CASH_APPLY_STS_CD", "AR_CASH_APPLY_STS_NM" });
//            initPullDownCreate(bizMsg.arCashApplyStsCd_EC, bizMsg.arCashApplyStsDescTxt_ED, arCashApplyStsList, new String[] {"AR_CASH_APPLY_STS_CD", "AR_CASH_APPLY_STS_DESC_TXT" });
            // END   2016/08/01 S.Fujita [QC#10148,MOD]
        }
    }

    /**
     * createPulldownListDrCrTp
     * @param bizMsg
     */
    public static void createPulldownListDrCrTp(NFCL3000CMsg bizMsg) {

        bizMsg.drCrTpCd_CC.no(0).setValue(DR_CR_TP_DEBIT);
        bizMsg.drCrTpCd_CD.no(0).setValue(DR_CR_TP_DEBIT);
        bizMsg.drCrTpCd_CC.no(1).setValue(DR_CR_TP_CREDIT);
        bizMsg.drCrTpCd_CD.no(1).setValue(DR_CR_TP_CREDIT);
    }

    /**
     * createPulldownListinvPrintSts
     * @param bizMsg
     */
    public static void createPulldownListinvPrintSts(NFCL3000CMsg bizMsg) {
        // START 2018/05/22 Y.Matsui [QC#26342,MOD]
        bizMsg.invPrintStsCd_EC.no(0).setValue(INV_PRINT_STS_DO_NOT_PRINT);
        bizMsg.invPrintStsCd_EC.no(1).setValue(INV_PRINT_STS_NOT_PRINT);
        bizMsg.invPrintStsCd_EC.no(2).setValue(INV_PRINT_STS_PRINTED);
        bizMsg.xxScrItem30Txt_ED.no(0).setValue(INV_PRINT_STS_DO_NOT_PRINT_DESC);
        bizMsg.xxScrItem30Txt_ED.no(1).setValue(INV_PRINT_STS_NOT_PRINT_DESC);
        bizMsg.xxScrItem30Txt_ED.no(2).setValue(INV_PRINT_STS_PRINTED_DESC);
        // END   2018/05/22 Y.Matsui [QC#26342,MOD]

        bizMsg.invPrintStsCd_E1.setValue(INV_PRINT_STS_NOT_PRINT);
    }

    /**
     * createPulldownListInvBolLineNum_A
     * @param bizMsg
     */
    public static void createPulldownListInvBolLineNum_A(NFCL3000CMsg bizMsg) {
        bizMsg.invBolLineNum_AC.clear();
        bizMsg.invBolLineNum_AD.clear();

        if(bizMsg.D.getValidCount() < 1) {
            bizMsg.invBolLineNum_AC.no(0).setValue(INIT_INV_BOL_LINE_NUM);
            bizMsg.invBolLineNum_AD.no(0).setValue(INIT_INV_BOL_LINE_NUM);
        } else {
            for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(bizMsg.invBolLineNum_AC.no(i), bizMsg.D.no(i).invBolLineNum_D1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.invBolLineNum_AD.no(i), bizMsg.D.no(i).invBolLineNum_D1.getValue());
            }
        }
    }

    /**
     * createPulldownListInvBolLineNum_B
     * @param bizMsg
     */
    public static void createPulldownListInvBolLineNum_B(NFCL3000CMsg bizMsg) {
        bizMsg.invBolLineNum_BC.clear();
        bizMsg.invBolLineNum_BD.clear();

        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invBolLineNum_BC.no(i), bizMsg.D.no(i).invBolLineNum_D1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.invBolLineNum_BD.no(i), bizMsg.D.no(i).invBolLineNum_D1.getValue());
        }
    }

    /**
     * createPulldownListInvLineNum_B
     * @param bizMsg
     */
    public static void createPulldownListInvLineNum_B(NFCL3000CMsg bizMsg) {
        bizMsg.invLineNum_BC.clear();
        bizMsg.invLineNum_BD.clear();
        
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invLineNum_BC.no(i), bizMsg.A.no(i).invLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.invLineNum_BD.no(i), bizMsg.A.no(i).invLineNum_A1.getValue());
        }
    }

    /**
     * createPulldownListSalesCreditLineNum_C
     * @param bizMsg
     */
    public static void createPulldownListSalesCreditLineNum_C(NFCL3000CMsg bizMsg) {
        bizMsg.xxLineNum_CC.clear();
        bizMsg.xxLineNum_CD.clear();
        int idx = 0;
        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            String xxLineNum = bizMsg.B.no(i).xxLineNum_B1.getValue();
            if(NFCL3000CommonLogic.isSalesCreditLineNum_C(bizMsg, xxLineNum)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxLineNum_CC.no(idx), xxLineNum);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxLineNum_CD.no(idx), xxLineNum);
                idx++;
            }
        }
    }

    /**
     * isSalesCreditLineNum_C
     * @param bizMsg
     * @param xxLineNum
     * @return
     */
    public static boolean isSalesCreditLineNum_C(NFCL3000CMsg bizMsg, String xxLineNum) {
        for(int i = 0; i < bizMsg.xxLineNum_CC.length(); i++) {
            if(xxLineNum.equals(bizMsg.xxLineNum_CC.get(i).toString())) {
                return false;
            }
        }
        return true;
    }

    /**
     * setSalesCreditLineNum_B
     * @param bizMsg
     */
    public static void setSalesCreditLineNum_B(NFCL3000CMsg bizMsg) {

        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            bizMsg.B.no(i).xxLineNum_B1.setValue(String.format("%05d", i + 1));
        }
    }

    /**
     * isSalesCreditLineNum_C
     * @param bizMsg
     */
    public static void setDetailLineNumber(NFCL3000CMsg bizMsg) {

        NFCL3000CommonLogic.createPulldownListInvBolLineNum_A(bizMsg);
        NFCL3000CommonLogic.createPulldownListInvBolLineNum_B(bizMsg);
        // START 2016/06/03 S.Fujita [QC#9157,MOD]
//        NFCL3000CommonLogic.createPulldownListInvLineNum_B(bizMsg);
        NFCL3000CommonLogic.setSalesCreditLineNum_B(bizMsg);
//        NFCL3000CommonLogic.createPulldownListSalesCreditLineNum_C(bizMsg);
        // END   2016/06/03 S.Fujita [QC#9157,MOD]
    }

    /**
     * reSetBolLineNumber
     * @param bizMsg
     */
    public static void reSetBolLineNumber(NFCL3000CMsg bizMsg) {

        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
            String invBolLineNumber = String.valueOf(i+1);
            invBolLineNumber = ZYPCommonFunc.leftPad(invBolLineNumber,3, "0");
            // START 2019/04/15 S.Takami [QC#31190,ADD]
            String origBolLineNum = bizMsg.D.no(i).invBolLineNum_D1.getValue();
            if (!S21StringUtil.isEquals(origBolLineNum, invBolLineNumber)) {

                // Reset Line Tab BOL Line Number
                for (int n = 0; n < bizMsg.A.getValidCount(); n++) {
                    NFCL3000_ACMsg lineBizMsg = bizMsg.A.no(n);
                    if (S21StringUtil.isEquals(lineBizMsg.invBolLineNum_A1.getValue(), origBolLineNum)) {
                        lineBizMsg.invBolLineNum_A1.setValue(invBolLineNumber);
                    }
                }

                // Reset Sales Credit Tab BOL Line Number
                for (int n = 0; n < bizMsg.B.getValidCount(); n++) {
                    NFCL3000_BCMsg slsCrBizMsg = bizMsg.B.no(n);
                    if (S21StringUtil.isEquals(slsCrBizMsg.invBolLineNum_B1.getValue(), origBolLineNum)) {
                        slsCrBizMsg.invBolLineNum_B1.setValue(invBolLineNumber);
                    }
                }

                // Reset Account Tab BOL Line Number
                for (int n = 0; n < bizMsg.C.getValidCount(); n++) {
                    NFCL3000_CCMsg acctBizMsg = bizMsg.C.no(n);
                    if (S21StringUtil.isEquals(acctBizMsg.invBolLineNum_C1.getValue(), origBolLineNum)) {
                        acctBizMsg.invBolLineNum_C1.setValue(invBolLineNumber);
                    }
                }
            }
            // END 2019/04/15 S.Takami [QC#31190,ADD]
            bizMsg.D.no(i).invBolLineNum_D1.setValue(invBolLineNumber);
        }
    }

    /**
     * getArAcctDt
     * @param bizMsg
     * @return
     */
    public static boolean getArAcctDt(NFCL3000CMsg bizMsg) {

        String arSubSysId = ZYPCodeDataUtil.getVarCharConstValue("AR_SUB_SYS_ID", bizMsg.glblCmpyCd.getValue());

        AR_ACCT_DTTMsg inMsg = new AR_ACCT_DTTMsg();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.subSysCd.setValue(arSubSysId);
        inMsg.onlBatTpCd.setValue("1");

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);
        if(outMsg == null) {
            return false;
        }

        bizMsg.acctDt.setValue(outMsg.acctDt.getValue());

        return true;
    }


    /**
     * searchShipToAddr
     * @param bizMsg
     * @return
     */
    public static boolean searchShipToAddr(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("dsAcctNum", bizMsg.shipToCustAcctCd_H2.getValue());
        ssmParam.put("locNum", bizMsg.locNum_H2.getValue());
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        // START 2016/10/18 S.Fujita [QC#13641,MOD]
        if (ZYPCommonFunc.hasValue(bizMsg.locNum_H2.getValue())) {
            S21SsmEZDResult ssmResult = NFCL3000Query.getInstance().searchShipToAddr(bizMsg, ssmParam);
            if (!ssmResult.isCodeNormal()) {
                bizMsg.locNum_H2.setErrorInfo(1, "ZZZM9006E", new String[]{"Ship To Location"});
                return false;
            }
        }
        // END   2016/10/18 S.Fujita [QC#13641,MOD]
        return true;
    }

    /**
     * searchShipToAddr_D
     * @param bizMsg
     * @param idx
     * @return
     */
    public static boolean searchShipToAddr_D(NFCL3000CMsg bizMsg, int idx) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("dsAcctNum", bizMsg.D.no(idx).shipToCustAcctCd_D1.getValue());
        ssmParam.put("locNum", bizMsg.D.no(idx).locNum_D1.getValue());
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        // START 2016/10/18 S.Fujita [QC#13641,MOD] 
        if (ZYPCommonFunc.hasValue(bizMsg.D.no(idx).locNum_D1.getValue())) {
            S21SsmEZDResult ssmResult = NFCL3000Query.getInstance().searchShipToAddr_D(bizMsg, ssmParam, idx);
            if (!ssmResult.isCodeNormal()) {
                bizMsg.D.no(idx).locNum_D1.setErrorInfo(1, "ZZZM9006E", new String[]{"Ship To Location"});
                return false;
            }
        }
        // END   2016/10/18 S.Fujita [QC#13641,MOD]
        return true;
    }

    /**
     * searchBillToAddr
     * @param bizMsg
     * @return
     */
    public static boolean searchBillToAddr(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("dsAcctNum", bizMsg.billToCustAcctCd_H3.getValue());
        ssmParam.put("locNum", bizMsg.locNum_H3.getValue());
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        // START 2016/10/18 S.Fujita [QC#13641,MOD]
        if (ZYPCommonFunc.hasValue(bizMsg.locNum_H3.getValue())) {
            S21SsmEZDResult ssmResult = NFCL3000Query.getInstance().searchBillToAddr(bizMsg, ssmParam);
            if (!ssmResult.isCodeNormal()) {
                bizMsg.locNum_H3.setErrorInfo(1, "ZZZM9006E", new String[]{"Bill To Location"});
                return false;
            }
        }
        // END   2016/10/18 S.Fujita [QC#13641,MOD]
        return true;
    }

    /**
     * searchInv
     * @param bizMsg
     * @return
     */
    public static boolean searchInv(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        ssmParam.put("invBolLineNum", "001");
        //---- start add 2016/05/04
        ssmParam.put("cm", INV_TP.CREDIT_MEMO);
        //---- end add 2016/05/04
        // START 2016/05/19 S.Fujita [QC#8478,ADD]
        ssmParam.put("Applied", AR_CASH_APPLY_STS.APPLIED);
        ssmParam.put("Unpplied", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmParam.put("Partial", AR_CASH_APPLY_STS.PARTIAL);
        ssmParam.put("Void", AR_CASH_APPLY_STS.VOID);
        // END 2016/05/19 S.Fujita [QC#8478,ADD]
        // START 2016/09/15 S.Yoshida [QC#13956,ADD]
        ssmParam.put("Invoice", CR_CARD_TRX_TP.INVOICE);
        ssmParam.put("CCScrnTxt", CR_CARD_SCRN_TXT);
        // END   2016/09/15 S.Yoshida [QC#13956,ADD]
        // START 2018/01/25 E.Kameishi [QC#22697,ADD]
        ssmParam.put("inv", INV_TP.INVOICE);
        // END   2018/01/25 E.Kameishi [QC#22697,ADD]
        S21SsmEZDResult ssmResult = NFCL3000Query.getInstance().searchInv(bizMsg, ssmParam);
        if (ssmResult.isCodeNormal()) {
            if(ZYPCommonFunc.hasValue(bizMsg.fnlzInvFlg_H4.getValue())) {
                if(bizMsg.fnlzInvFlg_H4.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                    bizMsg.xxChkBox_H3.setValue(ZYPConstant.FLG_ON_Y);
                }
            }
        } else {
            bizMsg.setMessageInfo("NZZM0000E");
            bizMsg.A.setValidCount(0);
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * searchInvLine
     * please call this method after searchInv() QC#50078
     * this method require Invoice Header data.
     * @param bizMsg Biz. Message
     * @return true: no error false: error
     * </pre>
     */
    public static boolean searchInvLine(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        ssmParam.put("cm", INV_TP.CREDIT_MEMO);
        // START 2018/09/28 T.Ogura [QC#28526,ADD]
        ssmParam.put("nsv", SYS_SRC.S21_SERVICE);
        ssmParam.put("dspt", SVC_INV_SRC_TP.DISPATCH);
        // END   2018/09/28 T.Ogura [QC#28526,ADD]
        // START 2018/12/12 Y.Matsui [QC#29587,ADD]
        ssmParam.put("trxSrcTpSalesReturn", TRX_SRC_TP.WHOLE_SALES_RETURN);
        ssmParam.put("ordPrftTrxCatgInbound", ORD_PRFT_TRX_CATG.INBOUND);
        ssmParam.put("ordPrftTrxCatgOutnbound", ORD_PRFT_TRX_CATG.OUTBOUND);
        // END   2018/12/12 Y.Matsui [QC#29587,ADD]

        S21SsmEZDResult ssmResult = NFCL3000Query.getInstance().searchInvLine(bizMsg, ssmParam);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt < 1) {
                return true;
            }
            else if (queryResCnt > bizMsg.A.length()) {
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = bizMsg.A.length();
            } else {
                bizMsg.setMessageInfo("NZZM0002I");
            }
            bizMsg.A.setValidCount(queryResCnt);
        } else {
            bizMsg.setMessageInfo("NZZM0000E");
            bizMsg.A.setValidCount(0);
            return false;
        }

        // START 2016/10/14 S.Fujita [QC#10281,ADD]
        NFCL3000CommonLogic.setAmtCsmp(bizMsg);
        // END   2016/10/14 S.Fujita [QC#10281,ADD]

        // START 2016/10/26 T.Murai [QC#13639,ADD]
        NFCL3000CommonLogic.setCpoNum(bizMsg);
        NFCL3000CommonLogic.setCpoDtlLineNum(bizMsg);
        // END   2016/10/26 T.Murai [QC#13639,ADD]

        // START 2016/11/17 T.Murai [QC#14096,ADD]
        NFCL3000CommonLogic.setSerNum(bizMsg);
        // START 2016/11/17 T.Murai [QC#14096,ADD]

        // START 2019/04/25 S.Takami [QC#50078,ADD]
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // START 2019/07/17 S.Takami [QC#51516,ADD]
            BigDecimal ordCustUomQty = bizMsg.A.no(i).ordCustUomQty_A1.getValue();
            boolean hasOrdCustUomQty = ZYPCommonFunc.hasValue(ordCustUomQty);
            if (hasOrdCustUomQty //
                    && BigDecimal.ZERO.compareTo(ordCustUomQty) == 0) {
                hasOrdCustUomQty = false;
            }
            boolean hasInvDplyQty = ZYPCommonFunc.hasValue(bizMsg.A.no(i).invDplyQty_A1);
            if (hasOrdCustUomQty && !hasInvDplyQty) {
                BigDecimal shipQty = bizMsg.A.no(i).shipQty_A1.getValue();
                MDSE_STORE_PKGTMsg mdseStorePkgTMsg = getMdseStorePkg(bizMsg, i);
                if (mdseStorePkgTMsg != null) {
                    BigDecimal inEachQty = mdseStorePkgTMsg.inEachQty.getValue();
                    boolean isEnableDeviding = shipQty.remainder(inEachQty).compareTo(BigDecimal.ZERO) == 0;
                    if (ordCustUomQty.multiply(inEachQty).compareTo(shipQty) != 0 //
                            && isEnableDeviding) {
                        ordCustUomQty = shipQty.divide(inEachQty);
                    } else if (!isEnableDeviding) {
                        bizMsg.A.no(i).pkgUomCd_A1.setValue(PKG_UOM.EACH);
                        ordCustUomQty = bizMsg.A.no(i).shipQty_A1.getValue();
                    }
                }
                if (bizMsg.A.no(i).ordCustUomQty_A1.getValue().compareTo(ordCustUomQty) != 0) {
                    bizMsg.A.no(i).ordCustUomQty_A1.setValue(ordCustUomQty);
                }
            }
            // End 2019/07/17 S.Takami [QC#51516,ADD]
            setDplyCtrlFlg(bizMsg, i);
        }
        // END 2019/04/25 S.Takami [QC#50078,ADD]

        return true;
    }

    /**
     * searchInvSlsCr
     * @param bizMsg
     * @return
     */
    public static boolean searchInvSlsCr(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        ssmParam.put("cm", INV_TP.CREDIT_MEMO);
        // START 2016/10/31 T.Murai [QC#14546, ADD]
        ssmParam.put("splTp40", INV_LINE_SPL_TP.DEVIATION);
        ssmParam.put("splTp50", INV_LINE_SPL_TP.ALLOCATION);
        // END   2016/10/31 T.Murai [QC#14546, ADD]

        S21SsmEZDResult ssmResult = NFCL3000Query.getInstance().searchInvSlsCr(bizMsg, ssmParam);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt < 1) {
                return true;
            }
            else if (queryResCnt > bizMsg.B.length()) {
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = bizMsg.B.length();
            } else {
                bizMsg.setMessageInfo("NZZM0002I");
            }
            bizMsg.B.setValidCount(queryResCnt);
        } else {
            bizMsg.setMessageInfo("NZZM0000E");
            bizMsg.B.setValidCount(0);
            return false;
        }
        return true;
    }

    /**
     * searchInvAcctDist
     * @param bizMsg
     * @return
     */
    public static boolean searchInvAcctDist(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        ssmParam.put("drCrtpCd_D", DR_CR_TP_DEBIT);
        ssmParam.put("drCrtpCd_C", DR_CR_TP_CREDIT);
        // START 2016/12/09 T.Murai  [QC#16175,ADD]
        // START 2016/09/26 S.Fujita [QC#13362,DEL]
        ssmParam.put("dfrdFlg", DFRD_REV_GL_STRG_FLG);
        // END   2016/09/26 S.Fujita [QC#13362,DEL]
        // END   2016/12/09 T.Murai  [QC#16175,ADD]

        S21SsmEZDResult ssmResult = null;
        ssmResult = NFCL3000Query.getInstance().searchInvAcctDist_Edit(bizMsg, ssmParam);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt < 1) {
                return true;

            } else if (queryResCnt > bizMsg.C.length()) {
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = bizMsg.C.length();

            } else {
                bizMsg.setMessageInfo("NZZM0002I");
            }
            bizMsg.C.setValidCount(queryResCnt);

        } else {
            bizMsg.C.setValidCount(0);
        }

        // Set COA 9 segment Text
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxScrItem61Txt_C1, NFCL3000CommonLogic.setCOA9SegString(bizMsg.C.no(i)));
        }

        NFCL3000CommonLogic.calcAmount_Acct_DrCr(bizMsg, true);
        NFCL3000CommonLogic.setSalesCreditLineNum(bizMsg);
        NFCL3000CommonLogic.copyCtoF(bizMsg, globalMsg);
        NFCL3000CommonLogic.setSummaryMode(bizMsg, globalMsg, true);

        return true;
    }

    /**
     * searchInvBOL
     * @param bizMsg
     * @return
     */
    public static boolean searchInvBOL(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        ssmParam.put("cm", INV_TP.CREDIT_MEMO);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        S21SsmEZDResult ssmResult = NFCL3000Query.getInstance().searchInvBOL(bizMsg, ssmParam);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt < 1) {
                return true;
            }
            else if (queryResCnt > bizMsg.D.length()) {
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = bizMsg.D.length();
            } else {
                bizMsg.setMessageInfo("NZZM0002I");
            }
            bizMsg.D.setValidCount(queryResCnt);
        } else {
            bizMsg.setMessageInfo("NZZM0000E");
            bizMsg.D.setValidCount(0);
            return false;
        }
        return true;
    }

    /**
     * setAddress
     * @param bizMsg
     * @return
     */
    public static void setAddress(NFCL3000CMsg bizMsg) {

        // Ship to Address
        bizMsg.xxDsMsgEntryTxt_H2.clear();
        String address = new String();
        
        if (ZYPCommonFunc.hasValue(bizMsg.shipToAddlLocNm_H2.getValue())) {
            address = address.concat(bizMsg.shipToAddlLocNm_H2.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.shipToFirstLineAddr_H2.getValue())) {
            address = address.concat(bizMsg.shipToFirstLineAddr_H2.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.shipToScdLineAddr_H2.getValue())) {
            address = address.concat(bizMsg.shipToScdLineAddr_H2.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.shipToThirdLineAddr_H2.getValue())) {
            address = address.concat(bizMsg.shipToThirdLineAddr_H2.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.shipToFrthLineAddr_H2.getValue())) {
            address = address.concat(bizMsg.shipToFrthLineAddr_H2.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.shipToCtyAddr_H2.getValue())) {
            address = address.concat(bizMsg.shipToCtyAddr_H2.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.shipToCntyNm_H2.getValue())) {
            address = address.concat(bizMsg.shipToCntyNm_H2.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.shipToStCd_H2.getValue())) {
            address = address.concat(bizMsg.shipToStCd_H2.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.shipToPostCd_H2.getValue())) {
            address = address.concat(bizMsg.shipToPostCd_H2.getValue());
            address = address.concat("\n");
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsMsgEntryTxt_H2, address);

        bizMsg.xxDsMsgEntryTxt_H3.clear();
        address = "";
        if (ZYPCommonFunc.hasValue(bizMsg.rcpntAddlLocNm_H3.getValue())) {
            address = address.concat(bizMsg.rcpntAddlLocNm_H3.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.rcpntFirstLineAddr_H3.getValue())) {
            address = address.concat(bizMsg.rcpntFirstLineAddr_H3.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.rcpntScdLineAddr_H3.getValue())) {
            address = address.concat(bizMsg.rcpntScdLineAddr_H3.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.rcpntThirdLineAddr_H3.getValue())) {
            address = address.concat(bizMsg.rcpntThirdLineAddr_H3.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.rcpntFrthLineAddr_H3.getValue())) {
            address = address.concat(bizMsg.rcpntFrthLineAddr_H3.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.rcpntCtyAddr_H3.getValue())) {
            address = address.concat(bizMsg.rcpntCtyAddr_H3.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.rcpntCntyNm_H3.getValue())) {
            address = address.concat(bizMsg.rcpntCntyNm_H3.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.rcpntStCd_H3.getValue())) {
            address = address.concat(bizMsg.rcpntStCd_H3.getValue());
            address = address.concat("\n");
        }
        if (ZYPCommonFunc.hasValue(bizMsg.rcpntPostCd_H3.getValue())) {
            address = address.concat(bizMsg.rcpntPostCd_H3.getValue());
            address = address.concat("\n");
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsMsgEntryTxt_H3, address);
    }

    /**
     * setCheckBox
     * @param bizMsg
     */
    public static void setCheckBox(NFCL3000CMsg bizMsg) {

        // Multiple BOL
        bizMsg.xxChkBox_H2.clear();
        if(bizMsg.D.getValidCount() > 1) {
            bizMsg.xxChkBox_H2.setValue(ZYPConstant.FLG_ON_Y);
        }

        // Complete
        //Def#5180
//        bizMsg.xxChkBox_H3.clear();
//        if(bizMsg.fnlzInvFlg_H4.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//            bizMsg.xxChkBox_H3.setValue(ZYPConstant.FLG_ON_Y);
//        }

        // Credit Applied
        bizMsg.xxChkBox_H4.clear();
        if(ZYPCommonFunc.hasValue(bizMsg.dealApplyCrAmt_H4.getValue()) && ZYPCommonFunc.hasValue(bizMsg.dealApplyAdjAmt_H4.getValue())) {
            BigDecimal creditApplyAmt = bizMsg.dealApplyCrAmt_H4.getValue().add(bizMsg.dealApplyAdjAmt_H4.getValue());
            if(creditApplyAmt.compareTo(BigDecimal.ZERO) != 0) {
                bizMsg.xxChkBox_H4.setValue(ZYPConstant.FLG_ON_Y);
            }
        }

        // START 2018/09/19 S.Ohki [QC#28089,ADD]
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H4.getValue())) {
            if (NFCL3000Query.getInstance().isCreditApplied(bizMsg)) {
                bizMsg.xxChkBox_H4.setValue(ZYPConstant.FLG_ON_Y);
            }
        }
        // END 2018/09/19 S.Ohki [QC#28089,ADD]

        // Cash Applied
        bizMsg.xxChkBox_H5.clear();
        if(ZYPCommonFunc.hasValue(bizMsg.dealApplyGrsAmt_H4.getValue())) {
            if(bizMsg.dealApplyGrsAmt_H4.getValue().compareTo(BigDecimal.ZERO) != 0) {
                bizMsg.xxChkBox_H5.setValue(ZYPConstant.FLG_ON_Y);
            }
        }
    }

    /**
     * calcNetDueDate
     * @param bizMsg
     * @return
     */
    public static boolean calcNetDueDate(NFCL3000CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.pmtTermCashDiscCd_H1.getValue())) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam.put("pmtTermCashDiscCd", bizMsg.pmtTermCashDiscCd_H1.getValue());
            ssmParam.put("invDt", bizMsg.invDt_H1.getValue());

            S21SsmEZDResult pmtTerm = NFCL3000Query.getInstance().calcNetDueDate(bizMsg, ssmParam);
            if (pmtTerm.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) pmtTerm.getResultObject();
                // START 2016/07/05 S.Fujita [QC#11210,DEL]
//                String netDueDt = (String) map.get("NET_DUE_DT");
//                bizMsg.netDueDt_H1.setValue(netDueDt);
                // END   2016/07/05 S.Fujita [QC#11210,DEL]
                String pmtTermCd = (String) map.get("PMT_TERM_CD");
                bizMsg.pmtTermCd_H1.setValue(pmtTermCd);
            }
            // START 2016/07/05 S.Fujita [QC#11210,ADD]
            NFZC309001PMsg param = new NFZC309001PMsg();
            ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(param.pmtTermCashDiscCd, bizMsg.pmtTermCashDiscCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(param.trxDt, bizMsg.invDt_H1.getValue());

            NFZC309001 dueDateCalculationAPI = new NFZC309001();
            dueDateCalculationAPI.execute(param, ONBATCH_TYPE.ONLINE);
            if (param.xxMsgIdList.getValidCount() > 0) {
                bizMsg.setMessageInfo(param.xxMsgIdList.no(0).xxMsgId.getValue());
                return false;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.netDueDt_H1, param.dueDt.getValue());
            // END   2016/07/05 S.Fujita [QC#11210,ADD]
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.netDueDt_H1, bizMsg.invDt_H1.getValue());
        }
        return true;
    }

    /**
     * getItemName_A
     * @param bizMsg
     * @param checkUpd
     * @return
     */
    public static boolean getItemName_A(NFCL3000CMsg bizMsg, int idx, boolean checkUpd) {
        bizMsg.A.no(idx).mdseNm_A1.clear();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", bizMsg.A.no(idx).mdseCd_A1.getValue());
        // START 2019/05/27 S.Takami [QC#50541,MOD]
//        S21SsmEZDResult mdse = NFCL3000Query.getInstance().getItemName(bizMsg, ssmParam);
        S21SsmEZDResult mdse = NFCL3000Query.getInstance().getItemInfo(bizMsg, ssmParam);
        // END 2019/05/27 S.Takami [QC#50541,MOD]
        // START 2016/06/03 S.Fujita [QC#9157,MOD]
//        if (mdse.isCodeNormal()) {
//            String mdseName = (String) mdse.getResultObject();
//            bizMsg.A.no(idx).mdseNm_A1.setValue(mdseName);
//        } else {
//            bizMsg.A.no(idx).mdseNm_A1.setErrorInfo(1, "NFCM0029E");
//            return false;
//        }
        if (mdse.isCodeNormal()) {
            // START 2016/06/20 S.Fujita [QC#9454,ADD]
            Map<String, Object> map = (Map<String, Object>) mdse.getResultObject();
            if(!ZYPCommonFunc.hasValue(bizMsg.A.no(idx).mdseNm_A1.getValue()) || checkUpd) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).mdseNm_A1, (String)map.get(MDSE_NM));
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).unitCostAmt_A1, (BigDecimal)map.get(THIS_MTH_TOT_STD_COST_AMT));
            // END   2016/06/20 S.Fujita [QC#9454,ADD]
        } else {
            bizMsg.A.no(idx).mdseCd_A1.setErrorInfo(1, "ZZZM9006E", new String[]{"Item Code"});
            return false;
        }
        // END   2016/06/03 S.Fujita [QC#9157,MOD]
        return true;
    }

    /**
     * searchSalesRep
     * @param bizMsg
     * @return
     */
    public static boolean searchSalesRep(NFCL3000CMsg bizMsg) {
        bizMsg.slsRepTocCd_H1.clear();
        bizMsg.slsRepTocNm_H1.clear();
        bizMsg.coaBrNm_H1.clear();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        // START 2016/07/14 S.Fujita [QC#11157,MOD]
        ssmParam.put("slsDt", bizMsg.procDt.getValue());
        ssmParam.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("psnNum", bizMsg.psnNum_H1.getValue());
//        ssmParam.put("tocCd", bizMsg.slsRepTocCd_H1.getValue());
        // END   2016/07/14 S.Fujita [QC#11157,MOD]
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // START 2018/09/19 S.Ohki [QC#28089,ADD]
        ssmParam.put("techFlg", ZYPConstant.FLG_ON_Y);
        // END 2018/09/19 S.Ohki [QC#28089,ADD]

        S21SsmEZDResult toc = NFCL3000Query.getInstance().searchSalesRep(bizMsg, ssmParam);
        if (toc.isCodeNormal()) {
            Map<String, Object> map = (Map<String, Object>) toc.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocCd_H1, (String)map.get("TOC_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocNm_H1, (String)map.get("TOC_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.coaBrNm_H1, (String)map.get("COA_BR_NM"));
            return true;
        } else {
            // START 2016/07/14 S.Fujita [QC#11157,MOD]
//            bizMsg.slsRepTocCd_H1.setErrorInfo(1, "NFCM0029E");
            bizMsg.psnNum_H1.setErrorInfo(1, "ZZZM9006E", new String[]{"Sales Rep"});
            // END   2016/07/14 S.Fujita [QC#11157,MOD]
        }
        return false;
    }

    /**
     * getCoaBrNm
     * @param bizMsg
     */
    public static void getCoaBrNm(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("slsDt", bizMsg.procDt.getValue());
        ssmParam.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("psnNum", bizMsg.psnNum_H1.getValue());
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // START 2018/09/19 S.Ohki [QC#28089,ADD]
        ssmParam.put("techFlg", ZYPConstant.FLG_ON_Y);
        // END 2018/09/19 S.Ohki [QC#28089,ADD]

        S21SsmEZDResult toc = NFCL3000Query.getInstance().searchSalesRep(bizMsg, ssmParam);
        if (toc.isCodeNormal()) {
            Map<String, Object> map = (Map<String, Object>) toc.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.coaBrNm_H1, (String)map.get("COA_BR_NM"));
        }
    }

    /**
     * getSalesRepName
     * @param bizMsg
     * @param idx
     * @param checkUpd
     * @return
     */
    public static boolean getSalesRepName(NFCL3000CMsg bizMsg, int idx, boolean checkUpd) {

        // START 2016/07/14 S.Fujita [QC#11157,MOD]
//        TOCTMsg inMsg = new TOCTMsg();
//        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(inMsg.tocCd, bizMsg.B.no(idx).slsRepTocCd_B1.getValue());
//        TOCTMsg outMsg = (TOCTMsg) EZDTBLAccessor.findByKey(inMsg);
//        if (outMsg!=null) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).tocNm_B1, outMsg.tocNm.getValue());
//        }

        bizMsg.B.no(idx).slsRepTocCd_B1.clear();
        bizMsg.B.no(idx).coaBrNm_B1.clear();
        bizMsg.B.no(idx).tocNm_B1.clear();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("slsDt", bizMsg.procDt.getValue());
        ssmParam.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("psnNum", bizMsg.B.no(idx).psnNum_B1.getValue());
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // START 2018/09/19 S.Ohki [QC#28089,ADD]
        ssmParam.put("techFlg", ZYPConstant.FLG_ON_Y);
        // END 2018/09/19 S.Ohki [QC#28089,ADD]

        S21SsmEZDResult toc = NFCL3000Query.getInstance().searchSalesRep(bizMsg, ssmParam);
        if (toc.isCodeNormal()) {
            Map<String, Object> map = (Map<String, Object>) toc.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).slsRepTocCd_B1, (String)map.get("TOC_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).coaBrNm_B1, (String)map.get("COA_BR_NM"));
            if(!ZYPCommonFunc.hasValue(bizMsg.B.no(idx).tocNm_B1.getValue()) || checkUpd) {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).tocNm_B1, (String)map.get("TOC_NM"));
            }
            return true;
        } else {
            bizMsg.B.no(idx).psnNum_B1.setErrorInfo(1, "ZZZM9006E", new String[]{"Sales Rep"});
        }
        // END   2016/07/14 S.Fujita [QC#11157,MOD]
        return false;
    }

    /**
     * clearNew
     * @param bizMsg
     */
    public static void clearNew(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        // Clear Header Data
        bizMsg.invNum_H1.clear();
        bizMsg.invTpCd_H1.clear();
        bizMsg.dsInvTpCd_H1.clear();
        bizMsg.invDt_H1.clear();
        bizMsg.pmtTermCashDiscCd_H1.clear();
        bizMsg.pmtTermCd_H1.clear();
        bizMsg.netDueDt_H1.clear();
        // START 2016/10/03 S.Fujita [QC#14120,DEL]
//        bizMsg.dfrdInvRuleCd_H1.clear();
        // END   2016/10/03 S.Fujita [QC#14120,DEL] 
        bizMsg.srcSysDocNum_H1.clear();
        bizMsg.custIssPoNum_H1.clear();
        bizMsg.slsRepTocCd_H1.clear();
        // START 2016/07/14 S.Fujita [QC#11157,ADD]
        bizMsg.psnNum_H1.clear();
        // END   2016/07/14 S.Fujita [QC#11157,ADD]
        bizMsg.slsRepTocNm_H1.clear();
        bizMsg.custCareTktNum_H1.clear();
        bizMsg.xxChkBox_H2.clear();
        bizMsg.shipToCustAcctCd_H2.clear();
        bizMsg.shipToCustAcctNm_H2.clear();
        bizMsg.shipToCustCd_H2.clear();
        bizMsg.locNum_H2.clear();
        bizMsg.shipToAddlLocNm_H2.clear();
        bizMsg.shipToFirstLineAddr_H2.clear();
        bizMsg.shipToScdLineAddr_H2.clear();
        bizMsg.shipToThirdLineAddr_H2.clear();
        bizMsg.shipToFrthLineAddr_H2.clear();
        bizMsg.shipToStCd_H2.clear();
        bizMsg.shipToProvNm_H2.clear();
        bizMsg.shipToCntyNm_H2.clear();
        bizMsg.shipToPostCd_H2.clear();
        bizMsg.shipToCtyAddr_H2.clear();
        bizMsg.shipToCtryCd_H2.clear();
        bizMsg.xxDsMsgEntryTxt_H2.clear();
        bizMsg.ctacPsnPk_H2.clear();
        bizMsg.shipToCtacPsnFirstNm_H2.clear();
        bizMsg.shipToCtacPsnLastNm_H2.clear();
        bizMsg.xxPsnNm_H2.clear();
        bizMsg.lineBizTpCd_H2.clear();
        bizMsg.billToCustAcctCd_H3.clear();
        bizMsg.billToCustAcctNm_H3.clear();
        bizMsg.billToCustCd_H3.clear();
        bizMsg.locNum_H3.clear();
        bizMsg.rcpntFirstLineAddr_H3.clear();
        bizMsg.rcpntScdLineAddr_H3.clear();
        bizMsg.rcpntThirdLineAddr_H3.clear();
        bizMsg.rcpntFrthLineAddr_H3.clear();
        bizMsg.rcpntCtyAddr_H3.clear();
        bizMsg.rcpntStCd_H3.clear();
        bizMsg.rcpntProvNm_H3.clear();
        bizMsg.rcpntCntyNm_H3.clear();
        bizMsg.rcpntPostCd_H3.clear();
        bizMsg.rcpntAddlLocNm_H3.clear();
        bizMsg.rcpntCtryCd_H3.clear();
        bizMsg.xxDsMsgEntryTxt_H3.clear();
        bizMsg.ctacPsnPk_H3.clear();
        bizMsg.billToCtacPsnFirstNm_H3.clear();
        bizMsg.billToCtacPsnLastNm_H3.clear();
        bizMsg.xxPsnNm_H3.clear();
        bizMsg.sellToCustCd_H3.clear();
        bizMsg.lineBizTpCd_H3.clear();
        bizMsg.shipFromInvtyLocCd_H4.clear();
        bizMsg.rtlWhNm_H4.clear();
        bizMsg.shipDt_H4.clear();
        bizMsg.xxTotAmt_T1.clear();
        bizMsg.xxTotAmt_T2.clear();
        bizMsg.xxTotAmt_T3.clear();
        bizMsg.xxTotAmt_T4.clear();
        bizMsg.xxTotAmt_T5.clear();
        bizMsg.xxChkBox_H3.clear();
        bizMsg.xxChkBox_H4.clear();
        bizMsg.xxChkBox_H5.clear();
        bizMsg.xxChkBox_H6.clear();
        bizMsg.fnlzInvFlg_H4.clear();
        bizMsg.dealApplyCrAmt_H4.clear();
        bizMsg.dealApplyAdjAmt_H4.clear();
        bizMsg.dealApplyGrsAmt_H4.clear();
        bizMsg.invErrMsgTxt_H4.clear();
        bizMsg.invldValTxt_H4.clear();

        bizMsg.xxTotAmt_T1.setValue(BigDecimal.ZERO);
        bizMsg.xxTotAmt_T2.setValue(BigDecimal.ZERO);
        bizMsg.xxTotAmt_T3.setValue(BigDecimal.ZERO);
        bizMsg.xxTotAmt_T4.setValue(BigDecimal.ZERO);
        bizMsg.xxTotAmt_T5.setValue(BigDecimal.ZERO);
        bizMsg.dealApplyCrAmt_H4.setValue(BigDecimal.ZERO);
        bizMsg.dealApplyAdjAmt_H4.setValue(BigDecimal.ZERO);
        bizMsg.dealApplyGrsAmt_H4.setValue(BigDecimal.ZERO);

        //More Info
        bizMsg.invProcTpCd_E1.clear();
        bizMsg.invPrintProcDt_E1.clear();
        // START 2019/05/10 S.Takami [QC#50148,MOD]
//        bizMsg.invFirstCmntTxt_E1.clear();
        bizMsg.xxInvMemoTxt_E1.clear();
        // END 2019/05/10 S.Takami [QC#50148,MOD]
        bizMsg.xxScrItem61Txt_E1.clear();
        bizMsg.arCashApplyStsCd_E1.clear();
        bizMsg.dealCltDsptAmt_E1.clear();
        bizMsg.cltDsptDt_E1.clear();
        bizMsg.contrRnwTotCnt_E1.clear();
        bizMsg.dsContrCatgCd_E1.clear();
        bizMsg.origInvNum_E1.clear();
        bizMsg.prePmtAmt_E1.clear();
        // START 2016/09/16 S.Yoshida [QC#13956,DEL]
//        bizMsg.dsPmtMethCd_E1.clear();
        // END   2016/09/16 S.Yoshida [QC#13956,DEL]
        bizMsg.crCardTrxPk_E1.clear();
        bizMsg.crCardTpCd_E1.clear();
        bizMsg.crCardHldNm_E1.clear();
        bizMsg.crCardLastDigitNum_E1.clear();
        bizMsg.crCardExprYrMth_E1.clear();
        bizMsg.xxScrItem10Txt_E1.clear();
        bizMsg.crCardTrxTpCd_E1.clear();
        // START 2016/09/16 S.Yoshida [QC#13956,ADD]
        bizMsg.xxScrItem19Txt_E1.clear();
        // END   2016/09/16 S.Yoshida [QC#13956,ADD]

        // START 2016/09/08 S.Fujita [QC#14115,ADD]
        bizMsg.acctDt_H1.clear();
        bizMsg.xxDtTm_IN.clear();
        bizMsg.ezUserID_H1.clear();
        bizMsg.xxDtTm_UP.clear();
        bizMsg.ezUpUserID_H1.clear();
        // END   2016/09/08 S.Fujita [QC#14115,ADD]

        // Clear TAB Data
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(bizMsg.D);

        ZYPTableUtil.clear(globalMsg.A);
        ZYPTableUtil.clear(globalMsg.B);
        ZYPTableUtil.clear(globalMsg.C);
        ZYPTableUtil.clear(globalMsg.D);

        bizMsg.invDt_H1.setValue(bizMsg.acctDt.getValue());
        bizMsg.xxNum_AL.setValue(BigDecimal.ONE);
        bizMsg.xxRadioBtn_C.setValue(ACCT_DIST_SMRY);
        bizMsg.xxRadioBtn_CB.setValue(ACCT_DIST_SMRY);
        bizMsg.xxPgFlg_C.clear();
    }

    /**
     * clearSubmit
     * @param cMsg
     * @param sMsg
     */
    public static void clearSubmit(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        // Clear Header Data
        bizMsg.invTpCd_H1.clear();
        bizMsg.dsInvTpCd_H1.clear();
        bizMsg.invDt_H1.clear();
        bizMsg.pmtTermCashDiscCd_H1.clear();
        bizMsg.pmtTermCd_H1.clear();
        bizMsg.netDueDt_H1.clear();
        // START 2016/10/03 S.Fujita [QC#14120,DEL]
//        bizMsg.dfrdInvRuleCd_H1.clear();
        // END   2016/10/03 S.Fujita [QC#14120,DEL]
        bizMsg.srcSysDocNum_H1.clear();
        bizMsg.custIssPoNum_H1.clear();
        bizMsg.slsRepTocCd_H1.clear();
        // START 2016/07/14 S.Fujita [QC#11157,ADD]
        bizMsg.psnNum_H1.clear();
        // END   2016/07/14 S.Fujita [QC#11157,ADD]
        bizMsg.slsRepTocNm_H1.clear();
        bizMsg.custCareTktNum_H1.clear();
        bizMsg.xxChkBox_H2.clear();
        bizMsg.shipToCustAcctCd_H2.clear();
        bizMsg.shipToCustAcctNm_H2.clear();
        bizMsg.shipToCustCd_H2.clear();
        bizMsg.locNum_H2.clear();
        bizMsg.shipToAddlLocNm_H2.clear();
        bizMsg.shipToFirstLineAddr_H2.clear();
        bizMsg.shipToScdLineAddr_H2.clear();
        bizMsg.shipToThirdLineAddr_H2.clear();
        bizMsg.shipToFrthLineAddr_H2.clear();
        bizMsg.shipToStCd_H2.clear();
        bizMsg.shipToProvNm_H2.clear();
        bizMsg.shipToCntyNm_H2.clear();
        bizMsg.shipToPostCd_H2.clear();
        bizMsg.shipToCtyAddr_H2.clear();
        bizMsg.shipToCtryCd_H2.clear();
        bizMsg.xxDsMsgEntryTxt_H2.clear();
        bizMsg.ctacPsnPk_H2.clear();
        bizMsg.shipToCtacPsnFirstNm_H2.clear();
        bizMsg.shipToCtacPsnLastNm_H2.clear();
        bizMsg.xxPsnNm_H2.clear();
        bizMsg.lineBizTpCd_H2.clear();
        bizMsg.billToCustAcctCd_H3.clear();
        bizMsg.billToCustAcctNm_H3.clear();
        bizMsg.billToCustCd_H3.clear();
        bizMsg.locNum_H3.clear();
        bizMsg.rcpntFirstLineAddr_H3.clear();
        bizMsg.rcpntScdLineAddr_H3.clear();
        bizMsg.rcpntThirdLineAddr_H3.clear();
        bizMsg.rcpntFrthLineAddr_H3.clear();
        bizMsg.rcpntCtyAddr_H3.clear();
        bizMsg.rcpntStCd_H3.clear();
        bizMsg.rcpntProvNm_H3.clear();
        bizMsg.rcpntCntyNm_H3.clear();
        bizMsg.rcpntPostCd_H3.clear();
        bizMsg.rcpntAddlLocNm_H3.clear();
        bizMsg.rcpntCtryCd_H3.clear();
        bizMsg.xxDsMsgEntryTxt_H3.clear();
        bizMsg.ctacPsnPk_H3.clear();
        bizMsg.billToCtacPsnFirstNm_H3.clear();
        bizMsg.billToCtacPsnLastNm_H3.clear();
        bizMsg.xxPsnNm_H3.clear();
        bizMsg.sellToCustCd_H3.clear();
        bizMsg.lineBizTpCd_H3.clear();
        bizMsg.shipFromInvtyLocCd_H4.clear();
        bizMsg.rtlWhNm_H4.clear();
        bizMsg.shipDt_H4.clear();
        bizMsg.xxTotAmt_T1.clear();
        bizMsg.xxTotAmt_T2.clear();
        bizMsg.xxTotAmt_T3.clear();
        bizMsg.xxTotAmt_T4.clear();
        bizMsg.xxTotAmt_T5.clear();
        bizMsg.xxChkBox_H3.clear();
        bizMsg.xxChkBox_H4.clear();
        bizMsg.xxChkBox_H5.clear();
        bizMsg.xxChkBox_H6.clear();
        bizMsg.fnlzInvFlg_H4.clear();
        bizMsg.dealApplyCrAmt_H4.clear();
        bizMsg.dealApplyAdjAmt_H4.clear();
        bizMsg.dealApplyGrsAmt_H4.clear();
        bizMsg.invErrMsgTxt_H4.clear();
        bizMsg.invldValTxt_H4.clear();

        bizMsg.xxTotAmt_T1.setValue(BigDecimal.ZERO);
        bizMsg.xxTotAmt_T2.setValue(BigDecimal.ZERO);
        bizMsg.xxTotAmt_T3.setValue(BigDecimal.ZERO);
        bizMsg.xxTotAmt_T4.setValue(BigDecimal.ZERO);
        bizMsg.xxTotAmt_T5.setValue(BigDecimal.ZERO);
        bizMsg.dealApplyCrAmt_H4.setValue(BigDecimal.ZERO);
        bizMsg.dealApplyAdjAmt_H4.setValue(BigDecimal.ZERO);
        bizMsg.dealApplyGrsAmt_H4.setValue(BigDecimal.ZERO);

        //More Info
        bizMsg.invProcTpCd_E1.clear();
        bizMsg.invPrintProcDt_E1.clear();
        // START 2019/05/10 S.Takami [QC#50148,MOD]
//        bizMsg.invFirstCmntTxt_E1.clear();
        bizMsg.xxInvMemoTxt_E1.clear();
        // START 2019/05/10 S.Takami [QC#50148,MOD]
        bizMsg.xxScrItem61Txt_E1.clear();
        bizMsg.arCashApplyStsCd_E1.clear();
        bizMsg.dealCltDsptAmt_E1.clear();
        bizMsg.cltDsptDt_E1.clear();
        bizMsg.contrRnwTotCnt_E1.clear();
        bizMsg.dsContrCatgCd_E1.clear();
        bizMsg.origInvNum_E1.clear();
        bizMsg.prePmtAmt_E1.clear();
        // START 2016/09/16 S.Yoshida [QC#13956,DEL]
//        bizMsg.dsPmtMethCd_E1.clear();
        // END   2016/09/16 S.Yoshida [QC#13956,DEL]
        bizMsg.crCardTrxPk_E1.clear();
        bizMsg.crCardTpCd_E1.clear();
        bizMsg.crCardHldNm_E1.clear();
        bizMsg.crCardLastDigitNum_E1.clear();
        bizMsg.crCardExprYrMth_E1.clear();
        bizMsg.xxScrItem10Txt_E1.clear();
        bizMsg.crCardTrxTpCd_E1.clear();
        // START 2016/09/16 S.Yoshida [QC#13956,ADD]
        bizMsg.xxScrItem19Txt_E1.clear();
        // END   2016/09/16 S.Yoshida [QC#13956,ADD]
        // START 2016/09/08 S.Fujita [QC#14115,ADD]
        bizMsg.acctDt_H1.clear();
        bizMsg.xxDtTm_IN.clear();
        bizMsg.ezUserID_H1.clear();
        bizMsg.xxDtTm_UP.clear();
        bizMsg.ezUpUserID_H1.clear();
        // END   2016/09/08 S.Fujita [QC#14115,ADD]

        // Clear TAB Data
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(bizMsg.D);

        ZYPTableUtil.clear(globalMsg.A);
        ZYPTableUtil.clear(globalMsg.B);
        ZYPTableUtil.clear(globalMsg.C);
        ZYPTableUtil.clear(globalMsg.D);

        bizMsg.invDt_H1.setValue(bizMsg.acctDt.getValue());
        bizMsg.xxNum_AL.setValue(BigDecimal.ONE);

        bizMsg.xxRadioBtn_C.setValue(ACCT_DIST_SMRY);
        bizMsg.xxRadioBtn_CB.setValue(ACCT_DIST_SMRY);
        bizMsg.xxPgFlg_C.clear();
    }

    /**
     * initialSetup_SlsCrTab
     * @param bizMsg
     */
    public static void initialSetup_SlsCrTab(NFCL3000CMsg bizMsg) {

        if(bizMsg.B.getValidCount() > 0) {
            return;
        }
        // START 2016/06/03 S.Fujita [QC#9157,MOD]
//        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).invBolLineNum_B1, bizMsg.A.no(i).invBolLineNum_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).invLineNum_B1, bizMsg.A.no(i).invLineNum_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).invLineSubNum_B1, bizMsg.A.no(i).invLineSubNum_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).invTrxLineSubNum_B1, bizMsg.A.no(i).invLineSubTrxNum_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).mdseCd_B1, bizMsg.A.no(i).mdseCd_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).slsRepTocCd_B1, bizMsg.slsRepTocCd_H1.getValue());
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).tocNm_B1, bizMsg.slsRepTocNm_H1.getValue());
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).invLineSplPct_B1, BigDecimal.ONE);
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).slsRepCrPct_B1, BigDecimal.ONE);
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).invLineSplPct_B2, new BigDecimal(100));
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).slsRepCrPct_B2, new BigDecimal(100));
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dealSlsCrAmt_B1, bizMsg.A.no(i).invLineDealNetAmt_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).xxTotAmt_B1, bizMsg.A.no(i).invLineDealNetAmt_A1.getValue());
//            // START 2016/05/24 S.Fujita [QC#8522,ADD]
//            bizMsg.B.no(i).dfrdAcctgRuleCd_B1.setValue(DFRD_ACCTG_RULE.IMMEDIATE);
//            DFRD_ACCTG_RULETMsg outMsg = NFCL3000CommonLogic.getDfrdAcctgrule(bizMsg, DFRD_ACCTG_RULE.IMMEDIATE);
//            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dfrdRevFlg_B1, outMsg.dfrdRevFlg.getValue());
//
//            if (bizMsg.B.no(i).dfrdRevFlg_B1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dfrdAcctgRuleDurnAot_B1, outMsg.dfrdAcctgRuleDurnAot.getValue());
//                bizMsg.B.no(i).durnStartDt_B1.setValue(bizMsg.invDt_H1.getValue());
//            } else {
//                bizMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.clear();
//                bizMsg.B.no(i).durnStartDt_B1.clear();
//            }
//            // END   2016/05/24 S.Fujita [QC#8522,ADD]
//        }
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NFCL3000CommonLogic.initialSetup_SlsCrTab_Idx(bizMsg, i, i);
        }
        // END   2016/06/03 S.Fujita [QC#9157,MOD]
        bizMsg.B.setValidCount(bizMsg.A.getValidCount());
    }

    // START 2016/06/03 S.Fujita [QC#9157,ADD]
    /**
     * initialSetup_SlsCrTab_Idx
     * @param bizMsg
     */
    public static void initialSetup_SlsCrTab_Idx(NFCL3000CMsg bizMsg, int aIdx, int bIdx) {

        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).invBolLineNum_B1, bizMsg.A.no(aIdx).invBolLineNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).invLineNum_B1, bizMsg.A.no(aIdx).invLineNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).invLineSubNum_B1, bizMsg.A.no(aIdx).invLineSubNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).invTrxLineSubNum_B1, bizMsg.A.no(aIdx).invLineSubTrxNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).mdseCd_B1, bizMsg.A.no(aIdx).mdseCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).slsRepTocCd_B1, bizMsg.slsRepTocCd_H1.getValue());
        // START 2016/07/14 S.Fujita [QC#11157,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).psnNum_B1, bizMsg.psnNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).coaBrNm_B1, bizMsg.coaBrNm_H1.getValue());
        // END   2016/07/14 S.Fujita [QC#11157,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).tocNm_B1, bizMsg.slsRepTocNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).invLineSplPct_B1, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).slsRepCrPct_B1, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).invLineSplPct_B2, new BigDecimal(100));
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).slsRepCrPct_B2, new BigDecimal(100));
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).dealSlsCrAmt_B1, bizMsg.A.no(aIdx).invLineDealNetAmt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).xxTotAmt_B1, bizMsg.A.no(aIdx).invLineDealNetAmt_A1.getValue());

        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).trxCd_B1, bizMsg.A.no(aIdx).trxCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).trxRsnCd_B1, bizMsg.A.no(aIdx).trxRsnCd_A1.getValue());

        bizMsg.B.no(bIdx).dfrdAcctgRuleCd_B1.setValue(DFRD_ACCTG_RULE.IMMEDIATE);
        DFRD_ACCTG_RULETMsg outMsg = NFCL3000CommonLogic.getDfrdAcctgrule(bizMsg, DFRD_ACCTG_RULE.IMMEDIATE);
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).dfrdRevFlg_B1, outMsg.dfrdRevFlg.getValue());

        // START 2017/03/17 T.Murai [QC#14205,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).xxRecHistTblNm_B,"DS_INV_SLS_CR");
        // END   2017/03/17 T.Murai [QC#14205,ADD]

        if (bizMsg.B.no(bIdx).dfrdRevFlg_B1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).dfrdAcctgRuleDurnAot_B1, outMsg.dfrdAcctgRuleDurnAot.getValue());
            bizMsg.B.no(bIdx).durnStartDt_B1.setValue(bizMsg.invDt_H1.getValue());
        } else {
            bizMsg.B.no(bIdx).dfrdAcctgRuleDurnAot_B1.clear();
            bizMsg.B.no(bIdx).durnStartDt_B1.clear();
        }
    }
    // END   2016/06/03 S.Fujita [QC#9157,ADD]

    /**
     * initialSetup_BOLTab
     * @param bizMsg
     * @param idx
     */
    public static void initialSetup_BOLTab(NFCL3000CMsg bizMsg) {

        int idx = 0;

        if(bizMsg.D.getValidCount() > 0) {
            return;
        }

        bizMsg.D.setValidCount(1);

        bizMsg.D.no(idx).invBolLineNum_D1.setValue(INIT_INV_BOL_LINE_NUM);

        bizMsg.D.no(idx).shipDealNetAmt_D1.setValue(BigDecimal.ZERO);
        bizMsg.D.no(idx).shipDealSlsAmt_D1.setValue(BigDecimal.ZERO);
        bizMsg.D.no(idx).shipDealFrtAmt_D1.setValue(BigDecimal.ZERO);
        bizMsg.D.no(idx).frtDealTaxAmt_D1.setValue(BigDecimal.ZERO);
        bizMsg.D.no(idx).shipDealDiscAmt_D1.setValue(BigDecimal.ZERO);
        bizMsg.D.no(idx).shipDealHdlgChrgAmt_D1.setValue(BigDecimal.ZERO);
        bizMsg.D.no(idx).totBolDealTaxAmt_D1.setValue(BigDecimal.ZERO);
        bizMsg.D.no(idx).frtTaxPct_D1.setValue(BigDecimal.ZERO);

        // Set Defaults from Header
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCustAcctCd_D1, bizMsg.shipToCustAcctCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCustAcctNm_D1, bizMsg.shipToCustAcctNm_H2.getValue());
//        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToLocNm_D1, bizMsg.shipToCustAcctNm_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCustCd_D1, bizMsg.shipToCustCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToAddlLocNm_D1, bizMsg.shipToAddlLocNm_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToFirstLineAddr_D1, bizMsg.shipToFirstLineAddr_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToScdLineAddr_D1, bizMsg.shipToScdLineAddr_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToThirdLineAddr_D1, bizMsg.shipToThirdLineAddr_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToFrthLineAddr_D1, bizMsg.shipToFrthLineAddr_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToStCd_D1, bizMsg.shipToStCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToProvNm_D1, bizMsg.shipToProvNm_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCntyNm_D1, bizMsg.shipToCntyNm_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCtyAddr_D1, bizMsg.shipToCtyAddr_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCtryCd_D1, bizMsg.shipToCtryCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToPostCd_D1, bizMsg.shipToPostCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).lineBizTpCd_D1, bizMsg.lineBizTpCd_H2.getValue());

        // START 2016/10/28 T.Murai [QC#15495,DEL]
        // ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipDt_D1, bizMsg.shipDt_H4.getValue());
        // ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipFromInvtyLocCd_D1, bizMsg.shipFromInvtyLocCd_H4.getValue());
        // END   2016/10/28 T.Murai [QC#15495,DEL]
        // START 2016/08/01 S.Fujita [QC#10148,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).locNum_D1, bizMsg.locNum_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).xxPsnNm_D1, bizMsg.xxPsnNm_H2.getValue());
        // END   2016/08/01 S.Fujita [QC#10148,ADD]

        // START 2016/07/08 S.Fujita [QC#10160,MOD]
        //---- start 2016/05/04 If it's credit memo, negate amount.
//        bizMsg.D.no(idx).shipDealNetAmt_D1.setValue(bizMsg.xxTotAmt_T1.getValue().multiply(getSignByInvtp(bizMsg)));
//        bizMsg.D.no(idx).shipDealSlsAmt_D1.setValue(bizMsg.xxTotAmt_T1.getValue().multiply(getSignByInvtp(bizMsg)));
//        bizMsg.D.no(idx).shipDealFrtAmt_D1.setValue(bizMsg.xxTotAmt_T2.getValue().multiply(getSignByInvtp(bizMsg)));
        bizMsg.D.no(idx).shipDealNetAmt_D1.setValue(bizMsg.xxTotAmt_T1.getValue());
        bizMsg.D.no(idx).shipDealSlsAmt_D1.setValue(bizMsg.xxTotAmt_T1.getValue());
        bizMsg.D.no(idx).shipDealFrtAmt_D1.setValue(bizMsg.xxTotAmt_T2.getValue());
        //---- end 2016/05/04
        // END   2016/07/08 S.Fujita [QC#10160,MOD]

        // START 2016/05/24 S.Fujita [QC#8522,MOD]
        bizMsg.D.no(idx).dsSlsTaxTpCd_D1.setValue(DS_SLS_TAX_TP.FREIGHT_STATE_TAX);
        bizMsg.D.no(idx).dealSlsTaxAmt_D1.setValue(BigDecimal.ZERO);
        bizMsg.D.no(idx).slsTaxPct_D1.setValue(BigDecimal.ZERO);
        bizMsg.D.no(idx).dsSlsTaxTpCd_D2.setValue(DS_SLS_TAX_TP.FREIGHT_COUNTY_TAX);
        bizMsg.D.no(idx).dealSlsTaxAmt_D2.setValue(BigDecimal.ZERO);
        bizMsg.D.no(idx).slsTaxPct_D2.setValue(BigDecimal.ZERO);
        bizMsg.D.no(idx).dsSlsTaxTpCd_D3.setValue(DS_SLS_TAX_TP.FREIGHT_CITY_TAX);
        bizMsg.D.no(idx).dealSlsTaxAmt_D3.setValue(BigDecimal.ZERO);
        bizMsg.D.no(idx).slsTaxPct_D3.setValue(BigDecimal.ZERO);
        // END  2016/05/24 S.Fujita [QC#8522,MOD]

        // START 2017/03/17 T.Murai [QC#14205,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).xxRecHistTblNm_D,"INV_BOL");
        // END   2017/03/17 T.Murai [QC#14205,ADD]

    }

    /**
     * setDefaultBOL
     * @param bizMsg
     * @param idx
     * @param updHeadFlg
     */
    public static void setDefaultBOL(NFCL3000CMsg bizMsg, int idx, boolean updHeadFlg) {

        // Set Defaults from Header
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCustAcctCd_D1, bizMsg.shipToCustAcctCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCustAcctNm_D1, bizMsg.shipToCustAcctNm_H2.getValue());
//        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToLocNm_D1, bizMsg.shipToCustAcctNm_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCustCd_D1, bizMsg.shipToCustCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToAddlLocNm_D1, bizMsg.shipToAddlLocNm_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToFirstLineAddr_D1, bizMsg.shipToFirstLineAddr_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToScdLineAddr_D1, bizMsg.shipToScdLineAddr_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToThirdLineAddr_D1, bizMsg.shipToThirdLineAddr_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToFrthLineAddr_D1, bizMsg.shipToFrthLineAddr_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToStCd_D1, bizMsg.shipToStCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToProvNm_D1, bizMsg.shipToProvNm_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCntyNm_D1, bizMsg.shipToCntyNm_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCtyAddr_D1, bizMsg.shipToCtyAddr_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCtryCd_D1, bizMsg.shipToCtryCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToPostCd_D1, bizMsg.shipToPostCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).lineBizTpCd_D1, bizMsg.lineBizTpCd_H2.getValue());

        // START 2016/10/28 T.Murai [QC#15495, DEL]
        // ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipDt_D1, bizMsg.shipDt_H4.getValue());
        // ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipFromInvtyLocCd_D1, bizMsg.shipFromInvtyLocCd_H4.getValue());
        // END   2016/10/28 T.Murai [QC#15495, DEL]

        // START 2016/08/01 S.Fujita [QC#10148,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).locNum_D1, bizMsg.locNum_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).xxPsnNm_D1, bizMsg.xxPsnNm_H2.getValue());
        // END   2016/08/01 S.Fujita [QC#10148,ADD]

        // START 2017/03/17 T.Murai [QC#14205,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).xxRecHistTblNm_D,"INV_BOL");
        // END   2017/03/17 T.Murai [QC#14205,ADD]

        if (!updHeadFlg) {
            String strInvBolLineNum = String.valueOf(idx+1);
            strInvBolLineNum = ZYPCommonFunc.leftPad(strInvBolLineNum, 3, "0");

            bizMsg.D.no(idx).invBolLineNum_D1.setValue(strInvBolLineNum);

            bizMsg.D.no(idx).shipDealNetAmt_D1.setValue(BigDecimal.ZERO);
            bizMsg.D.no(idx).shipDealSlsAmt_D1.setValue(BigDecimal.ZERO);
            bizMsg.D.no(idx).shipDealFrtAmt_D1.setValue(BigDecimal.ZERO);
            bizMsg.D.no(idx).frtDealTaxAmt_D1.setValue(BigDecimal.ZERO);
            bizMsg.D.no(idx).shipDealDiscAmt_D1.setValue(BigDecimal.ZERO);
            bizMsg.D.no(idx).shipDealHdlgChrgAmt_D1.setValue(BigDecimal.ZERO);
            bizMsg.D.no(idx).totBolDealTaxAmt_D1.setValue(BigDecimal.ZERO);
            bizMsg.D.no(idx).frtTaxPct_D1.setValue(BigDecimal.ZERO);

            // START 2016/06/20 S.Fujita [QC#9454,MOD]
            bizMsg.D.no(idx).dsSlsTaxTpCd_D1.setValue(DS_SLS_TAX_TP.FREIGHT_STATE_TAX);
            bizMsg.D.no(idx).dealSlsTaxAmt_D1.setValue(BigDecimal.ZERO);
            bizMsg.D.no(idx).slsTaxPct_D1.setValue(BigDecimal.ZERO);
            bizMsg.D.no(idx).dsSlsTaxTpCd_D2.setValue(DS_SLS_TAX_TP.FREIGHT_COUNTY_TAX);
            bizMsg.D.no(idx).dealSlsTaxAmt_D2.setValue(BigDecimal.ZERO);
            bizMsg.D.no(idx).slsTaxPct_D2.setValue(BigDecimal.ZERO);
            bizMsg.D.no(idx).dsSlsTaxTpCd_D3.setValue(DS_SLS_TAX_TP.FREIGHT_CITY_TAX);
            bizMsg.D.no(idx).dealSlsTaxAmt_D3.setValue(BigDecimal.ZERO);
            bizMsg.D.no(idx).slsTaxPct_D3.setValue(BigDecimal.ZERO);
            // END   2016/06/20 S.Fujita [QC#9454,MOD]

            // START 2017/03/17 T.Murai [QC#14205,ADD]
            bizMsg.D.no(idx).xxRecHistCratTs_D.clear();
            bizMsg.D.no(idx).xxRecHistCratByNm_D.clear();
            bizMsg.D.no(idx).xxRecHistUpdTs_D.clear();
            bizMsg.D.no(idx).xxRecHistUpdByNm_D.clear();
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).xxRecHistTblNm_D,"INV_BOL");
            // END   2017/03/17 T.Murai [QC#14205,ADD]
        }
    }

    /**
     * setCOA9SegString
     * @param bizMsg
     * @param idx
     * @return
     */
    public static String setCOA9SegString(NFCL3000_CCMsg cBizMsg) {
        String strCOA9Seg = null;
        if(ZYPCommonFunc.hasValue(cBizMsg.ajeCoaCmpyCd_C1.getValue())) {
            if(!ZYPCommonFunc.hasValue(strCOA9Seg)) {
                strCOA9Seg = cBizMsg.ajeCoaCmpyCd_C1.getValue();
                strCOA9Seg = strCOA9Seg.concat(".");
            } 
        } else{
            strCOA9Seg = ".";
        }

        if(ZYPCommonFunc.hasValue(cBizMsg.ajeCoaBrCd_C1.getValue())) {
            if(!ZYPCommonFunc.hasValue(strCOA9Seg)) {
                strCOA9Seg = cBizMsg.ajeCoaBrCd_C1.getValue();
            } else {
                strCOA9Seg = strCOA9Seg.concat(cBizMsg.ajeCoaBrCd_C1.getValue());
                strCOA9Seg = strCOA9Seg.concat(".");
            }
        } else {
            strCOA9Seg = strCOA9Seg.concat(".");
        }

        if(ZYPCommonFunc.hasValue(cBizMsg.ajeCoaCcCd_C1.getValue())) {
            if(!ZYPCommonFunc.hasValue(strCOA9Seg)) {
                strCOA9Seg = cBizMsg.ajeCoaCcCd_C1.getValue();
            } else {
                strCOA9Seg = strCOA9Seg.concat(cBizMsg.ajeCoaCcCd_C1.getValue());
                strCOA9Seg = strCOA9Seg.concat(".");
            }
        } else {
            strCOA9Seg = strCOA9Seg.concat(".");
        }

        if(ZYPCommonFunc.hasValue(cBizMsg.ajeCoaAcctCd_C1.getValue())) {
            if(!ZYPCommonFunc.hasValue(strCOA9Seg)) {
                strCOA9Seg = cBizMsg.ajeCoaAcctCd_C1.getValue();
            } else {
                strCOA9Seg = strCOA9Seg.concat(cBizMsg.ajeCoaAcctCd_C1.getValue());
                strCOA9Seg = strCOA9Seg.concat(".");
            }
        } else {
            strCOA9Seg = strCOA9Seg.concat(".");
        }

        if(ZYPCommonFunc.hasValue(cBizMsg.ajeCoaProdCd_C1.getValue())) {
            if(!ZYPCommonFunc.hasValue(strCOA9Seg)) {
                strCOA9Seg = cBizMsg.ajeCoaProdCd_C1.getValue();
            } else {
                strCOA9Seg = strCOA9Seg.concat(cBizMsg.ajeCoaProdCd_C1.getValue());
                strCOA9Seg = strCOA9Seg.concat(".");
            }
        } else {
            strCOA9Seg = strCOA9Seg.concat(".");
        }

        if(ZYPCommonFunc.hasValue(cBizMsg.ajeCoaChCd_C1.getValue())) {
            if(!ZYPCommonFunc.hasValue(strCOA9Seg)) {
                strCOA9Seg = cBizMsg.ajeCoaChCd_C1.getValue();
            } else {
                strCOA9Seg = strCOA9Seg.concat(cBizMsg.ajeCoaChCd_C1.getValue());
                strCOA9Seg = strCOA9Seg.concat(".");
            }
        } else {
            strCOA9Seg = strCOA9Seg.concat(".");
        }

        if(ZYPCommonFunc.hasValue(cBizMsg.ajeCoaAfflCd_C1.getValue())) {
            if(!ZYPCommonFunc.hasValue(strCOA9Seg)) {
                strCOA9Seg = cBizMsg.ajeCoaAfflCd_C1.getValue();
            } else {
                strCOA9Seg = strCOA9Seg.concat(cBizMsg.ajeCoaAfflCd_C1.getValue());
                strCOA9Seg = strCOA9Seg.concat(".");
            }
        } else {
            strCOA9Seg = strCOA9Seg.concat(".");
        }

        if(ZYPCommonFunc.hasValue(cBizMsg.ajeCoaProjCd_C1.getValue())) {
            if(!ZYPCommonFunc.hasValue(strCOA9Seg)) {
                strCOA9Seg = cBizMsg.ajeCoaProjCd_C1.getValue();
            } else {
                strCOA9Seg = strCOA9Seg.concat(cBizMsg.ajeCoaProjCd_C1.getValue());
                strCOA9Seg = strCOA9Seg.concat(".");
            }
        } else {
            strCOA9Seg = strCOA9Seg.concat(".");
        }

        if(ZYPCommonFunc.hasValue(cBizMsg.ajeCoaExtnCd_C1.getValue())) {
            if(!ZYPCommonFunc.hasValue(strCOA9Seg)) {
                strCOA9Seg = cBizMsg.ajeCoaExtnCd_C1.getValue();
            } else {
                strCOA9Seg = strCOA9Seg.concat(cBizMsg.ajeCoaExtnCd_C1.getValue());
            }
        } else {
            strCOA9Seg = strCOA9Seg.concat(".");
        }
        return strCOA9Seg;
    }

    /**
     * splitCOA9SegString
     * @param bizMsg
     * @param idx
     */
    public static boolean splitCOA9SegString(NFCL3000_CCMsg cBizMsg) {

        String coa[] = cBizMsg.xxScrItem61Txt_C1.getValue().split("\\.", 9);
        if(coa.length != 9) {
            String errMsg = "9 segments";
            errMsg = errMsg.concat("(");
            errMsg = errMsg.concat(String.valueOf(coa.length));
            errMsg = errMsg.concat(")");
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, "NFCM0833E", new String[]{errMsg});
            return false;
        }
        if(COA_LENGTH[0] != coa[0].length()) {
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, "NFCM0833E", new String[]{"Company"});
            return false;
        }
        if(COA_LENGTH[1] != coa[1].length()) {
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, "NFCM0833E", new String[]{"Branch"});
            return false;
        }
        if(COA_LENGTH[2] != coa[2].length()) {
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, "NFCM0833E", new String[]{"Cost Center"});
            return false;
        }
        if(COA_LENGTH[3] != coa[3].length()) {
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, "NFCM0833E", new String[]{"Account"});
            return false;
        }
        if(COA_LENGTH[4] != coa[4].length()) {
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, "NFCM0833E", new String[]{"Product"});
            return false;
        }
        if(COA_LENGTH[5] != coa[5].length()) {
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, "NFCM0833E", new String[]{"Channel"});
            return false;
        }
        if(COA_LENGTH[6] != coa[6].length()) {
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, "NFCM0833E", new String[]{"Intercompany"});
            return false;
        }
        if(COA_LENGTH[7] != coa[7].length()) {
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, "NFCM0833E", new String[]{"Merchandise"});
            return false;
        }
        if(COA_LENGTH[8] != coa[8].length()) {
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, "NFCM0833E", new String[]{"Business Unit"});
            return false;
        }

        cBizMsg.ajeCoaCmpyCd_C1.setValue(coa[0]);
        cBizMsg.ajeCoaBrCd_C1.setValue(coa[1]);
        cBizMsg.ajeCoaCcCd_C1.setValue(coa[2]);
        cBizMsg.ajeCoaAcctCd_C1.setValue(coa[3]);
        cBizMsg.ajeCoaProdCd_C1.setValue(coa[4]);
        cBizMsg.ajeCoaChCd_C1.setValue(coa[5]);
        cBizMsg.ajeCoaAfflCd_C1.setValue(coa[6]);
        cBizMsg.ajeCoaProjCd_C1.setValue(coa[7]);
        cBizMsg.ajeCoaExtnCd_C1.setValue(coa[8]);

        return true;
    }

    /**
     * calcAmount
     * @param bizMsg
     * @param taxCalcFlg
     */
    // START 2016/06/20 S.Fujita [QC#9454,MOD] taxCalc add parameter Delete taxCalc_BOL
    public static boolean calcAmount(NFCL3000CMsg bizMsg, boolean taxCalcFlg) {

        NFCL3000CommonLogic.getBilltoInfo(bizMsg);

        boolean isError = false;
        // START 2017/12/25 E.Kameishi [QC#20312,ADD]
        boolean isTaxAdj = false;
        // END 2017/12/25 E.Kameishi [QC#20312,ADD]
        // Invoice Line
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // START 2018/07/19 E.Kameishi [QC#27007,MOD]
            // START 2019/04/25 S.Takami [QC#50078,DEL]
//            S21SsmEZDResult invLine = NFCL3000Query.getInstance().getInvLine(bizMsg, i);
//            BigDecimal origShipQty =null;
//            BigDecimal origUnitAmt =null;
//            BigDecimal origTotNetAmt =null;
//            String originvCatgCd =null;
//            Map<String, Object> map = null;
//            
//            if(invLine.isCodeNormal()) {
//                map = (Map<String, Object>) invLine.getResultObject();
//                
//                origShipQty = (BigDecimal) map.get("SHIP_QTY");
//                origUnitAmt = (BigDecimal) map.get("DEAL_NET_UNIT_PRC_AMT");
//                origTotNetAmt = (BigDecimal) map.get("INV_LINE_DEAL_NET_AMT");
//                originvCatgCd = (String) map.get("INV_LINE_CATG_CD");
//            }
            // END 2019/04/25 S.Takami [QC#50078,DEL]
            // START 2016/06/30 S.Fujita [QC#11012,ADD]
            if(isManualInvoice(bizMsg) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue())) {
                // START 2019/04/25 S.Takami [QC#50078,MOD]
//                BigDecimal invLineNetAmt = BigDecimal.ZERO;
//                if (map == null || map.size() == 0
//                        || (!INV_LINE_CATG.ITEM.equals(originvCatgCd)
//                                && (bizMsg.A.no(i).shipQty_A1.getValue().compareTo(origShipQty) != 0
//                                        || getAmount(bizMsg.A.no(i).dealNetUnitPrcAmt_A1).compareTo(origUnitAmt) != 0))
//                                        || INV_LINE_CATG.ITEM.equals(originvCatgCd)) {
//                    invLineNetAmt = bizMsg.A.no(i).shipQty_A1.getValue().multiply(getAmount(bizMsg.A.no(i).dealNetUnitPrcAmt_A1));
//                } else {
//                    invLineNetAmt = origTotNetAmt;
//                }
                BigDecimal invLineNetAmt = getInvLineNetAmount(bizMsg, i);
                // End 2019/04/25 S.Takami [QC#50078,MOD]
            // END 2018/07/19 E.Kameishi [QC#27007,MOD]
                if (invLineNetAmt != null && (invLineNetAmt.precision() - invLineNetAmt.scale()) > AMT_SIZE) {
                    bizMsg.A.no(i).invLineDealNetAmt_A1.setErrorInfo(1, "ZZM9001E", new String[] {"Sales Amount(Qty * Unit Price)"});
                    isError = true;
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invLineDealNetAmt_A1, invLineNetAmt);
                }
                // START 2017/12/25 E.Kameishi [QC#20312,ADD]
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxMstChkFlg_A1.getValue())) {
                    isTaxAdj = true;
                }
                // END 2017/12/25 E.Kameishi [QC#20312,ADD]
            }
            // END   2016/06/30 S.Fujita [QC#11012,ADD]

            // START 2016/12/20 S.Yoshida [QC#16181,DEL]
//            // START 2016/10/24 S.Fujita [QC#15530,ADD]
//            // Merchandise check
//            if(!NFCL3000CommonLogic.getItemName_A(bizMsg, i, false)) {
//                isError = true;
//            }
//            // END   2016/10/24 S.Fujita [QC#15530,ADD]
            // END   2016/12/20 S.Yoshida [QC#16181,DEL]

            // START 2016/12/20 S.Yoshida [QC#16181,MOD]
            if (!NFCL3000CommonLogic.getItemInfo(bizMsg, i)) {
                // START 2019/05/27 S.Takami [QC#50541,ADD]
                bizMsg.xxDplyTab.setValue(TAB_Line);
                // END 2019/05/27 S.Takami [QC#50541,ADD]
                isError = true;
            }
            // END   2016/12/20 S.Yoshida [QC#16181,MOD]

        }

        if (isError) {
            return false;
        }

        // START 2016/10/06 S.Fujita [QC#10522,MOD]
        // get tax from TaxCalculationAPI
        if(ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H3.getValue()) && taxCalcFlg) {
            // START 2017/12/25 E.Kameishi [QC#20312,ADD]
            if (isTaxAdj) {
                if(!NFCL3000CommonLogic.taxCalc(bizMsg, NWZC036101Constant.PROC_MODE_DISTRIBUTE_TAX)) {
                    return false;
                }
            } else {
                if(!NFCL3000CommonLogic.taxCalc(bizMsg, NWZC036101Constant.PROC_MODE_INVOICE)) {
                    return false;
                }
            }
        } else if (!isTaxAdj) {
            if(!NFCL3000CommonLogic.taxCalc(bizMsg, NWZC036101Constant.PROC_MODE_QUOTATION)) {
                return false;
            }
        }
        // START 2019/11/02 [QC#54463,ADD]
        else {
            taxCalcForTaxAdjustmentIfNotFinalized(bizMsg);
        }
        // END   2019/11/02 [QC#54463,ADD]

        // END 2017/12/25 E.Kameishi [QC#20312,ADD]
        // END   2016/10/06 S.Fujita [QC#10522,MOD]

        if (!NFCL3000CommonLogic.calcAmount_InvLine(bizMsg)) {
            return false;
        }
        // START 2016/12/06 T.Murai [QC#16387, MOD]
//         if(!ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue()) ) {
        if(!ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue()) && isManualInvoice(bizMsg)) {
        // END 2016/12/06 T.Murai [QC#16387, MOD]
            // copy line amount onto sales credit amount.
            NFCL3000CommonLogic.calcAmount_SlsCr(bizMsg);

            // START 2016/09/12 S.Fujita [QC#14112,ADD]
            // copy sales credit amount onto account amount.
            if (!NFCL3000CommonLogic.calcAmount_Acct(bizMsg)) {
                return false;
            }
            NFCL3000CommonLogic.setAutoDebitAmount(bizMsg);
            // END   2016/09/12 S.Fujita [QC#14112,ADD]
        }

        return true;
    }

    /**
     * calcAmount_InvLine
     * @param bizMsg
     * @return
     */
    public static boolean calcAmount_InvLine(NFCL3000CMsg bizMsg) {

        // START 2016/09/20 S.Fujita [QC#13795,ADD]
        if(!isManualInvoice(bizMsg) || ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue())) {
            return true;
        }
        // END   2016/09/20 S.Fujita [QC#13795,ADD]

        // START 2018/05/22 Y.Matsui [QC#21841,ADD]
        if (bizMsg.D.getValidCount() > 0) {
            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                bizMsg.D.no(i).shipDealFrtAmt_D1.setValue(BigDecimal.ZERO);
            }
        }
        // END   2018/05/22 Y.Matsui [QC#21841,ADD]

        boolean isError = false;
        BigDecimal totSlsAmt = new BigDecimal(0);
        BigDecimal totFrtAmt = new BigDecimal(0);
        BigDecimal totTaxAmt = new BigDecimal(0);
        BigDecimal totInvAmt = new BigDecimal(0);
        BigDecimal invLineNetAmt = new BigDecimal(0);
        BigDecimal invLineTaxAmt = new BigDecimal(0);
        /************************************/
        /* Line TAB Amount                  */
        /************************************/
        if(bizMsg.A.getValidCount() > 0) {
            // Calc Sales Amount
            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
             // START 2018/07/19 E.Kameishi [QC#27007,MOD]
                // START 2019/04/25 S.Takami [QC#50078,DEL]
//                S21SsmEZDResult invLine = NFCL3000Query.getInstance().getInvLine(bizMsg, i);
//                BigDecimal origShipQty =null;
//                BigDecimal origUnitAmt =null;
//                BigDecimal origTotNetAmt =null;
//                String originvCatgCd =null;
//                Map<String, Object> map = null;
//                
//                if(invLine.isCodeNormal()) {
//                    map = (Map<String, Object>) invLine.getResultObject();
//                    
//                    origShipQty = (BigDecimal) map.get("SHIP_QTY");
//                    origUnitAmt = (BigDecimal) map.get("DEAL_NET_UNIT_PRC_AMT");
//                    origTotNetAmt = (BigDecimal) map.get("INV_LINE_DEAL_NET_AMT");
//                    originvCatgCd = (String) map.get("INV_LINE_CATG_CD");
//                }
                // END 2019/04/25 S.Takami [QC#50078,DEL]
                // START 2017/12/25 E.Kameishi [QC#20312,MOD]
                if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxMstChkFlg_A1.getValue())) {
                    // START 2019/04/25 S.Takami [QC#50078,MOD]
//                    if (map == null || map.size() == 0
//                            || (!INV_LINE_CATG.ITEM.equals(originvCatgCd)
//                                    && (bizMsg.A.no(i).shipQty_A1.getValue().compareTo(origShipQty) != 0
//                                            || getAmount(bizMsg.A.no(i).dealNetUnitPrcAmt_A1).compareTo(origUnitAmt) != 0))
//                                            || INV_LINE_CATG.ITEM.equals(originvCatgCd)) {
//                        invLineNetAmt = bizMsg.A.no(i).shipQty_A1.getValue().multiply(getAmount(bizMsg.A.no(i).dealNetUnitPrcAmt_A1));
//                    } else {
//                        invLineNetAmt = origTotNetAmt;
//                    }
                    invLineNetAmt = getInvLineNetAmount(bizMsg, i);
                    // END 2019/04/25 S.Takami [QC#50078,MOD]
                }
                // END 2017/12/25 E.Kameishi [QC#20312,MOD]
                bizMsg.A.no(i).invLineDealNetAmt_A1.setValue(invLineNetAmt);
                // END 2018/07/19 E.Kameishi [QC#27007,MOD]
                // START 2018/05/22 Y.Matsui [QC#21841,MOD]
                if (!isFreightInvoiceLine(bizMsg, bizMsg.A.no(i))) {
                    totSlsAmt = totSlsAmt.add(invLineNetAmt);
                } else {
                    // Calc Freight Amount
                    for (int j = 0; j < bizMsg.D.getValidCount(); j++) {
                        if (bizMsg.A.no(i).invBolLineNum_A1.getValue().equals(bizMsg.D.no(j).invBolLineNum_D1.getValue())) {
                            BigDecimal shipDealFrtAmt = bizMsg.D.no(j).shipDealFrtAmt_D1.getValue();
                            bizMsg.D.no(j).shipDealFrtAmt_D1.setValue(shipDealFrtAmt.add(invLineNetAmt));
                            break;
                        }
                    }
                }
                // END 2018/05/22 Y.Matsui [QC#21841,MOD]
            }

            // START 2016/10/07 S.Fujita [QC#10522,DEL]
            // Calc Tax Amount
//            NFCL3000CommonLogic.calcLine_Tax_Percent(bizMsg);
            // END   2016/10/07 S.Fujita [QC#10522,DEL]

            // Calc Total Amount
            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                invLineNetAmt = bizMsg.A.no(i).invLineDealNetAmt_A1.getValue();
                // START 2018/02/23 E.Kameishi [QC#24390,MOD]
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxMstChkFlg_A1.getValue())
                        && INV_TP.CREDIT_MEMO.equals(bizMsg.invTpCd_H1.getValue())) {
                    invLineTaxAmt = bizMsg.A.no(i).invLineDealTaxAmt_A1.getValue().negate();
                } else {
                    invLineTaxAmt = bizMsg.A.no(i).invLineDealTaxAmt_A1.getValue();
                }
                // END 2018/02/23 E.Kameishi [QC#24390,MOD]

                // START 2018/05/22 Y.Matsui [QC#21841,MOD]
                if (!isFreightInvoiceLine(bizMsg, bizMsg.A.no(i))) {
                    totTaxAmt = totTaxAmt.add(invLineTaxAmt);
                    totInvAmt = totInvAmt.add(invLineNetAmt).add(invLineTaxAmt);
                }
                // END   2018/05/22 Y.Matsui [QC#21841,MOD]

                // START 2016/06/30 S.Fujita [QC#11012,ADD]
                invLineNetAmt = invLineNetAmt.add(invLineTaxAmt);
                if (invLineNetAmt != null && (invLineNetAmt.precision() - invLineNetAmt.scale()) > AMT_SIZE) {
                    bizMsg.A.no(i).xxTotAmt_A1.setErrorInfo(1, "ZZM9001E", new String[] {"Total Amount(Sales Amount + Tax Amount)"});
                    isError = true;
                } else {
                    bizMsg.A.no(i).xxTotAmt_A1.setValue(invLineNetAmt);
                }
            }
            if (isError) {
                return false;
            }
            // END   2016/06/30 S.Fujita [QC#11012,ADD]
        }
        /************************************/
        /* BOL TAB Amount                   */
        /************************************/
        if(bizMsg.D.getValidCount() > 0) {

            // START 2016/10/07 S.Fujita [QC#10522,DEL]
            // Calc Tax Amount
//            NFCL3000CommonLogic.calcBOL_Tax_Percent(bizMsg);
           // END   2016/10/07 S.Fujita [QC#10522,DEL]

            // Calc Total Amount
            for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
                totFrtAmt = totFrtAmt.add(bizMsg.D.no(i).shipDealFrtAmt_D1.getValue());
                totTaxAmt = totTaxAmt.add(bizMsg.D.no(i).frtDealTaxAmt_D1.getValue());
                totInvAmt = totInvAmt.add(bizMsg.D.no(i).shipDealFrtAmt_D1.getValue());
                totInvAmt = totInvAmt.add(bizMsg.D.no(i).frtDealTaxAmt_D1.getValue());
            }
        }
        // START 2016/06/30 S.Fujita [QC#11012,ADD]
        if (totSlsAmt != null && (totSlsAmt.precision() - totSlsAmt.scale()) > AMT_SIZE) {
            bizMsg.setMessageInfo("ZZM9001E", new String[] {"Sales Amount"});
            isError = true;
        }
        if (totFrtAmt != null && (totFrtAmt.precision() - totFrtAmt.scale()) > AMT_SIZE) {
            bizMsg.setMessageInfo("ZZM9001E", new String[] {"Freight Amount"});
            isError = true;
        }
        if (totTaxAmt != null && (totTaxAmt.precision() - totTaxAmt.scale()) > AMT_SIZE) {
            bizMsg.setMessageInfo("ZZM9001E", new String[] {"Tax Amount"});
            isError = true;
        }
        if (totInvAmt != null && (totInvAmt.precision() - totInvAmt.scale()) > AMT_SIZE) {
            bizMsg.setMessageInfo("ZZM9001E", new String[] {"Invoice Total"});
            isError = true;
        }
        if (isError) {
            return false;
        }
        // END   2016/06/30 S.Fujita [QC#11012,ADD]
        /************************************/
        /* Header  Amount                   */
        /************************************/
        // START 2016/07/08 S.Fujita [QC#10160,MOD]
        //---- start 2016/05/04 QC#7864 If it's credit memo, display in negative amount.  
//        bizMsg.xxTotAmt_T1.setValue(totSlsAmt.multiply(getSignByInvtp(bizMsg)));
//        bizMsg.xxTotAmt_T2.setValue(totFrtAmt.multiply(getSignByInvtp(bizMsg)));
//        bizMsg.xxTotAmt_T3.setValue(totTaxAmt.multiply(getSignByInvtp(bizMsg)));
//        bizMsg.xxTotAmt_T4.setValue(totInvAmt.multiply(getSignByInvtp(bizMsg)));
        bizMsg.xxTotAmt_T1.setValue(totSlsAmt);
        bizMsg.xxTotAmt_T2.setValue(totFrtAmt);
        bizMsg.xxTotAmt_T3.setValue(totTaxAmt);
        bizMsg.xxTotAmt_T4.setValue(totInvAmt);
        //---- end 2016/05/04
        // END   2016/07/08 S.Fujita [QC#10160,MOD]

        // START 2016/07/08 S.Fujita [QC#11262,ADD]
        // Check Header Amount by Invoice Type
        if(!NFCL3000CommonLogic.checkAmtByInvtp(bizMsg)) {
            return false;
        }
        // END   2016/07/08 S.Fujita [QC#11262,ADD]
        return true;
    }

    // START 2016/06/03 S.Fujita [QC#9157,ADD]
    /**
     * calcAmount_SlsCr
     * @param bizMsg
     */
    public static void calcAmount_SlsCr(NFCL3000CMsg bizMsg) {

        if(bizMsg.B.getValidCount() < 1) {
            return;
        }

        NFCL3000CommonLogic.setLineAmt(bizMsg);

        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (!(INV_LINE_SPL_TP.ALLOCATION.equals(bizMsg.B.no(i).invLineSplTpCd_B1.getValue()) || INV_LINE_SPL_TP.DEVIATION.equals(bizMsg.B.no(i).invLineSplTpCd_B1.getValue()))) {
                NFCL3000CommonLogic.calcAmount_SlsCr_Amount_Idx(bizMsg, i);
            }
        }
    }
    // END   2016/06/03 S.Fujita [QC#9157,ADD]

    // START 2016/09/05 S.Fujita [QC#13648,DEL]
//    /**
//     * checkRep_SlsCr
//     * @param bizMsg
//     * @param idx
//     */
//    public static boolean checkRep_SlsCr(NFCL3000CMsg bizMsg, int idx) {
//
//        if(!NFCL3000CommonLogic.isManualInvoice(bizMsg)) {
//            return true;
//        }
//        String invBolLineNum = bizMsg.A.no(idx).invBolLineNum_A1.getValue();
//        String invLineNum = bizMsg.A.no(idx).invLineNum_A1.getValue();
//        String rep = null;
//        boolean isDup = false;
//        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            if(invBolLineNum.equals(bizMsg.B.no(i).invBolLineNum_B1.getValue())) {
//                if(invLineNum.equals(bizMsg.B.no(i).invLineNum_B1.getValue())) {
//                    if(rep == null) {
//                        rep = bizMsg.B.no(i).slsRepTocCd_B1.getValue();
//                    } else {
//                        if(bizMsg.B.no(i).slsRepCrPct_B2.getValue().compareTo(DIV100) != 0) {
//                            if(rep.equals(bizMsg.B.no(i).slsRepTocCd_B1.getValue())) {
//                                isDup = true;
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        if(isDup) {
//            for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
//                if(invBolLineNum.equals(bizMsg.B.no(i).invBolLineNum_B1.getValue())) {
//                    if(invLineNum.equals(bizMsg.B.no(i).invLineNum_B1.getValue())) {
//                        if(rep.equals(bizMsg.B.no(i).slsRepTocCd_B1.getValue())) {
//                            // START 2016/07/14 S.Fujita [QC#11157,MOD]
////                            bizMsg.B.no(i).slsRepTocCd_B1.setErrorInfo(1, "NFCM0580E", new String[] {rep});
//                            bizMsg.B.no(i).psnNum_B1.setErrorInfo(1, "NFCM0580E", new String[] {rep});
//                            // END   2016/07/14 S.Fujita [QC#11157,MOD]
//                        }
//                    }
//                }
//            }
//            return false;
//        }
//        return true;
//    }
    // END   2016/09/05 S.Fujita [QC#13648,DEL]

    // START 2016/06/03 S.Fujita [QC#9157,ADD]
    /**
     * calcAmount_SlsCr_Amount_Idx
     * @param bizMsg
     * @param idx
     */
    public static void calcAmount_SlsCr_Amount_Idx(NFCL3000CMsg bizMsg, int idx) {

        if(!ZYPCommonFunc.hasValue(bizMsg.xxChkBox_BA)) {
            BigDecimal invLineDealNetAmt = getAmount(bizMsg.B.no(idx).xxTotAmt_B1);
            BigDecimal invLineSplPct = getAmount(bizMsg.B.no(idx).invLineSplPct_B2);

            if (invLineSplPct.compareTo(BigDecimal.ZERO) == 0) {
                invLineSplPct = new BigDecimal(100);
            }

            BigDecimal slsRepCrPct = getAmount(bizMsg.B.no(idx).slsRepCrPct_B2);
            int scale = bizMsg.aftDeclPntDigitNum.getValueInt();
            BigDecimal dealSlsCrAmt = invLineDealNetAmt.multiply(invLineSplPct.divide(DIV100)).multiply(slsRepCrPct.divide(DIV100));
            dealSlsCrAmt = dealSlsCrAmt.setScale(scale, BigDecimal.ROUND_HALF_UP);
            bizMsg.B.no(idx).dealSlsCrAmt_B1.setValue(dealSlsCrAmt);
        }
    }
    // END   2016/06/03 S.Fujita [QC#9157,ADD]

    // START 2016/06/03 S.Fujita [QC#9157,ADD]
    /**
     * check_SlsCrTAB
     * @param bizMsg
     * @return
     */
    public static boolean check_SlsCrTAB(NFCL3000CMsg bizMsg){
        boolean isSuccess = true;

        // Check line
        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if(!NFCL3000CommonLogic.checkLine_SlsCr_Idx(bizMsg, i)) {
                isSuccess = false;
            }
        }
        if(!isSuccess) {
            return false;
        }

        // Check line
        if(!NFCL3000CommonLogic.check_SalesCredit(bizMsg, false)) {
            for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
                bizMsg.B.no(i).invLineNum_B1.setErrorInfo(1, NOT_ASSIGNED_ERROR, new String[]{PRM_LINE_S, PRM_SLS_CR});
            }
            isSuccess = false;
        }
        if(!isSuccess) {
            return false;
        }

        // START 2016/08/01 S.Fujita [QC#10148,DEL]
        // Check Salesrep
//        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            if(!NFCL3000CommonLogic.getSalesRepName_Idx(bizMsg, i)) {
//                isSuccess = false;
//            }
//        }
        // END   2016/08/01 S.Fujita [QC#10148,DEL]
        if(!isSuccess) {
            return false;
        }

        // Calc Amount
        NFCL3000CommonLogic.calcAmount_SlsCr(bizMsg);

        // Check Split
        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {

            if (!(INV_LINE_SPL_TP.ALLOCATION.equals(bizMsg.B.no(i).invLineSplTpCd_B1.getValue()) || INV_LINE_SPL_TP.DEVIATION.equals(bizMsg.B.no(i).invLineSplTpCd_B1.getValue()))) {
                // Check Amount and Percentage 
                if(!NFCL3000CommonLogic.checkSlsCr_Percent_Idx(bizMsg, i)) {
                    NFCL3000CommonLogic.setErrorSlsCr_Percent(bizMsg, i);
                    isSuccess = false;
                }
            }
        }
        return  isSuccess;
    }

    /**
     * checkSlsCr_Percent_Idx
     * @param bizMsg
     * @return
     */
    public static boolean checkSlsCr_Percent_Idx(NFCL3000CMsg bizMsg, int idx) {
        boolean isSuccess = true;
        String invBolLineNum = bizMsg.B.no(idx).invBolLineNum_B1.getValue();
        String invLineNum = bizMsg.B.no(idx).invLineNum_B1.getValue();
        String invLineSubNum = bizMsg.B.no(idx).invLineSubNum_B1.getValue();
        BigDecimal invLineDealNetAmt = bizMsg.B.no(idx).xxTotAmt_B1.getValue();
        BigDecimal totAmt = BigDecimal.ZERO;
        //START 2018/07/17 E.Kameishi [QC#27007,ADD]
        BigDecimal totPct = BigDecimal.ZERO;
        BigDecimal totPctHund = new BigDecimal(100);
        //END 2018/07/17 E.Kameishi [QC#27007,ADD]

        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if(invBolLineNum.equals(bizMsg.B.no(i).invBolLineNum_B1.getValue())) {
                if(invLineNum.equals(bizMsg.B.no(i).invLineNum_B1.getValue())) {
                    if(invLineSubNum.equals(bizMsg.B.no(i).invLineSubNum_B1.getValue())) {
                        totAmt = totAmt.add(getAmount(bizMsg.B.no(i).dealSlsCrAmt_B1));
                        //START 2018/07/17 E.Kameishi [QC#27007,ADD]
                        totPct = totPct.add(getAmount(bizMsg.B.no(i).slsRepCrPct_B2));
                        //END 2018/07/17 E.Kameishi [QC#27007,ADD]
                    }
                }
            }
        }
        //START 2018/07/17 E.Kameishi [QC#27007,MOD]
        if(invLineDealNetAmt.compareTo(totAmt) != 0 || totPctHund.compareTo(totPct) < 0) {
            isSuccess = false;
        }
        //END 2018/07/17 E.Kameishi [QC#27007,MOD]
        return  isSuccess;
    }

    /**
     * setErrorSlsCr_Percent
     * @param bizMsg
     * @param idx
     */
    public static void setErrorSlsCr_Percent(NFCL3000CMsg bizMsg, int idx) {
        String invBolLineNum = bizMsg.B.no(idx).invBolLineNum_B1.getValue();
        String invLineNum = bizMsg.B.no(idx).invLineNum_B1.getValue();
        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if(invBolLineNum.equals(bizMsg.B.no(i).invBolLineNum_B1.getValue())) {
                if(invLineNum.equals(bizMsg.B.no(i).invLineNum_B1.getValue())) {
                    // START 2016/09/05 S.Fujita [QC#13648,DEL]
//                    bizMsg.B.no(i).invLineSplPct_B2.setErrorInfo(1, PERCENTAGE_ERROR,  new String[] {PRM_100_PERCENT});
                    // END   2016/09/05 S.Fujita [QC#13648,DEL]
                    bizMsg.B.no(i).slsRepCrPct_B2.setErrorInfo(1, PERCENTAGE_ERROR,  new String[] {PRM_100_PERCENT});
                    bizMsg.B.no(i).dealSlsCrAmt_B1.setErrorInfo(1, MISMATCH_ERROR,  new String[] {PRM_LINE_AMOUNT, PRM_SLS_CR_AMOUNT});
                }
            }
        }
    }
    
    /**
     * getSalesRepName_Idx
     * @param bizMsg
     * @param idx
     */
    public static boolean getSalesRepName_Idx(NFCL3000CMsg bizMsg, int idx) {

        TOCTMsg inMsg = new TOCTMsg();

        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inMsg.tocCd, bizMsg.B.no(idx).slsRepTocCd_B1.getValue());
        TOCTMsg outMsg = (TOCTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0864E", new String[]{"Sales Rep Name", bizMsg.B.no(idx).slsRepTocCd_B1.getValue()});
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).tocNm_B1, outMsg.tocNm.getValue());
        return true;
    }
    // END   2016/06/03 S.Fujita [QC#9157,ADD]

    /**
     * calcAmountByPct
     * @param totalAmt
     * @param srcAmt
     * @param dstAmt
     * @param scale
     * @return
     */
    public static BigDecimal calcAmountByPct(BigDecimal srcAmt, BigDecimal pct, int scale) {
        
        BigDecimal resultAmt = new BigDecimal(0);

        if (srcAmt.intValue() == 0) {
            return BigDecimal.ZERO;
        }

        resultAmt = srcAmt.multiply(pct);

        resultAmt = resultAmt.setScale(scale, BigDecimal.ROUND_HALF_UP);

        return resultAmt;
    }

    /**
     * setLineNumber
     * @param bizMsg
     */
    public static void setLineNumber(NFCL3000CMsg bizMsg) {
        int invLineNum = 1;

        // Numbering Line# for each BOL Line#.
        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
            invLineNum = 1;
            for(int j = 0 ; j < bizMsg.A.getValidCount(); j++) {
                if(bizMsg.D.no(i).invBolLineNum_D1.getValue().equals(bizMsg.A.no(j).invBolLineNum_A1.getValue())) {
                    String strInvLineNum = String.valueOf(invLineNum);
                    strInvLineNum = ZYPCommonFunc.leftPad(strInvLineNum, 5, "0");
                    // START 2019/04/15 S.Takami [QC#31190,ADD]
                    String origLineNum = bizMsg.A.no(j).invLineNum_A1.getValue();
                    if (!S21StringUtil.isEquals(origLineNum, strInvLineNum)) {
                        // Reset Sales Credit Tab Line Number
                        for (int n = 0; n < bizMsg.B.getValidCount(); n++) {
                            NFCL3000_BCMsg slsCrBizMsg = bizMsg.B.no(n);
                            if (S21StringUtil.isEquals(slsCrBizMsg.invLineNum_B1.getValue(), origLineNum)) {
                                slsCrBizMsg.invLineNum_B1.setValue(strInvLineNum);
                            }
                        }

                        // Reset Account Tab Line Number
                        for (int n = 0; n < bizMsg.C.getValidCount(); n++) {
                            NFCL3000_CCMsg acctBizMsg = bizMsg.C.no(n);
                            if (S21StringUtil.isEquals(acctBizMsg.invLineNum_C1.getValue(), origLineNum)) {
                                acctBizMsg.invLineNum_C1.setValue(strInvLineNum);
                            }
                        }
                    }
                    // End 2019/04/15 S.Takami [QC#31190,ADD]
                    bizMsg.A.no(j).invLineNum_A1.setValue(strInvLineNum);
                    bizMsg.A.no(j).invLineSubNum_A1.setValue(INIT_INV_LINE_SUB_NUM);
                    bizMsg.A.no(j).invLineSubTrxNum_A1.setValue(INIT_INV_LINE_SUB_TRX_NUM);
                    invLineNum++;
                }
            }
        }
    }

    /**
     * setAcctDistSqNumber
     * @param bizMsg
     */
    public static void setAcctDistSqNumber(NFCL3000CMsg bizMsg) {
        int acctDistSqNum = 1;
        for(int i = 0 ; i < bizMsg.C.getValidCount(); i++) {
            String strAcctDistSqNum = String.valueOf(acctDistSqNum);
            strAcctDistSqNum = ZYPCommonFunc.leftPad(strAcctDistSqNum, 6, "0");
            bizMsg.C.no(i).ajeInvAcctDistSqNum_C1.setValue(strAcctDistSqNum);
            acctDistSqNum++;
        }    }

    /**
     * taxCalc
     * @param bizMsg NFCL3000CMsg
     * @param xxModeCd String
     * @return boolean
     */
    public static boolean taxCalc(NFCL3000CMsg bizMsg, String xxModeCd) {

        // START 2016/09/20 S.Fujita [QC#13795,ADD]
        if(!isManualInvoice(bizMsg) || ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue())) {
            return true;
        }
        // END   2016/09/20 S.Fujita [QC#13795,ADD]

        // START 2016/10/06 S.Fujita [QC#10522,ADD]
        // Initialize values of tax amount
        // START 2019/11/21 H.Nagashima [QC#54746,MOD]
//        // START 2017/12/25 E.Kameishi [QC#20312,MOD]
//        if (!NWZC036101Constant.PROC_MODE_DISTRIBUTE_TAX.equals(xxModeCd)) {
//            initialTaxAmt(bizMsg);
//        }
//        // END 2017/12/25 E.Kameishi [QC#20312,MOD]
        initialTaxAmt(bizMsg, xxModeCd);
        // END 2019/11/21 H.Nagashima [QC#54746,MOD]

        List<NWZC036101PMsg> taxcalcPMsgList = makeTaxCalculationAPIParam(bizMsg, xxModeCd);
        // START 2019/10/02 S.Takami [QC#53633-2,ADD] add for debugging
        NFCL3000CommonLogicForPrintTaxCalcParam.printTaxCalcApiPMsg(taxcalcPMsgList);
        // END 2019/10/02 S.Takami [QC#53633-2,ADD]

        if (taxcalcPMsgList.size() > 0) {
            NWZC036101 taxCalculationAPI = new NWZC036101();
            for (NWZC036101PMsg taxcalcPMsg : taxcalcPMsgList) {
                taxCalculationAPI.execute(taxcalcPMsg, ONBATCH_TYPE.ONLINE);
            }

            Iterator<NWZC036101PMsg> taxcalcPMsgItr = taxcalcPMsgList.iterator();
            // START 2018/01/24 E.Kameishi [QC#20312-1,MOD]
            if (!getTaxCalculationAPIResult(bizMsg, taxcalcPMsgItr, xxModeCd)) {
                return false;
            }
            // END   2018/01/24 E.Kameishi [QC#20312-1,MOD]
        }
        // END   2016/10/06 S.Fujita [QC#10522,ADD]
        return true;
    }

    /**
     * getItemInfo
     * @param bizMsg
     * @param idx
     * @return
     */
    public static boolean getItemInfo(NFCL3000CMsg bizMsg, int idx) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", bizMsg.A.no(idx).mdseCd_A1.getValue());
        S21SsmEZDResult itemInfo = NFCL3000Query.getInstance().getItemInfo(bizMsg, ssmParam);

        // START 2016/12/20 S.Yoshida [QC#16181,MOD]
        if (!itemInfo.isCodeNormal()) {
            bizMsg.A.no(idx).mdseCd_A1.setErrorInfo(1, "ZZZM9006E", new String[]{"Item Code"});
            return false;
        }

        Map<String, Object> map = (Map<String, Object>) itemInfo.getResultObject();

        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(idx).mdseNm_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).mdseNm_A1, (String)map.get(MDSE_NM));
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).unitCostAmt_A1, (BigDecimal)map.get(THIS_MTH_TOT_STD_COST_AMT));
        // END   2016/12/20 S.Yoshida [QC#16181,MOD]

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).taxExemTpCd_A1, (String)map.get("TAX_EXEM_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).svcAllocTpCd_A1, (String)map.get("SVC_ALLOC_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).svcAllocTrxTpNm_A1, (String)map.get("SVC_ALLOC_TRX_TP_NM"));
        return true;
    }

    /**
     * searchWarehouse
     * @param bizMsg
     * @return
     */
    public static boolean searchWarehouse(NFCL3000CMsg bizMsg) {
        bizMsg.rtlWhNm_H4.clear();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("whCd", bizMsg.shipFromInvtyLocCd_H4.getValue());
        ssmParam.put("procDt", bizMsg.procDt.getValue());
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        S21SsmEZDResult whInfo = NFCL3000Query.getInstance().searchWarehouse(bizMsg, ssmParam);
        if (!whInfo.isCodeNormal()) {
            bizMsg.shipFromInvtyLocCd_H4.setErrorInfo(1, "ZZZM9006E", new String[]{"Warehouse"});
            return false;
        } else {
            Map<String, Object> map = (Map<String, Object>) whInfo.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.shipFromInvtyLocCd_H4, (String)map.get("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H4, (String)map.get("RTL_WH_NM"));
        }
        return true;
    }

    /**
     * getBilltoInfo
     * @param bizMsg
     * @return
     */
    public static boolean getBilltoInfo(NFCL3000CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("billToCustCd", bizMsg.billToCustCd_H3.getValue());
        ssmParam.put("procDt", bizMsg.procDt.getValue());
        
        S21SsmEZDResult billToInfo = NFCL3000Query.getInstance().getBillToInfo(bizMsg, ssmParam);
        if (billToInfo.isCodeNormal()) {
            Map<String, Object> map = (Map<String, Object>) billToInfo.getResultObject();
            // START 2016/06/20 S.Fujita [QC#9454,MOD]
            //ZYPEZDItemValueSetter.setValue(bizMsg.dsTaxGrpExemCd_H3, (String)map.get("DS_TAX_GRP_EXEM_CD"));
            if(ZYPCommonFunc.hasValue((String)map.get("DS_TAX_GRP_EXEM_CD"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsTaxGrpExemCd_H3, (String)map.get("DS_TAX_GRP_EXEM_CD"));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsTaxGrpExemCd_H3, (String)map.get("DS_TAX_GRP_EXEM_CD_2"));
            }
            // END   2016/06/20 S.Fujita [QC#9454,MOD]
            ZYPEZDItemValueSetter.setValue(bizMsg.dsTaxGrpExemReslFlg_H3, (String)map.get("DS_TAX_GRP_EXEM_RESL_FLG"));
        }
        return true;
    }

    /**
     * searchShipTo
     * @param bizMsg
     * @return
     */
    public static boolean searchShipTo(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("shipToCustAcctCd", bizMsg.shipToCustAcctCd_H2.getValue());
        ssmParam.put("locNum", bizMsg.locNum_H2.getValue());
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        S21SsmEZDResult shipToInfo = NFCL3000Query.getInstance().searchShipTo(bizMsg, ssmParam);
        if (!shipToInfo.isCodeNormal()) {
            bizMsg.shipToCustAcctCd_H2.setErrorInfo(1, "ZZZM9006E", new String[]{"Ship To Cust Account Number"});
            bizMsg.locNum_H2.setErrorInfo(1, "ZZZM9006E", new String[]{"Location"});
            return false;
        } else {
            Map<String, Object> map = (Map<String, Object>) shipToInfo.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_H2, (String)map.get("SHIP_TO_CUST_CD"));

            if(!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctNm_H2.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm_H2, (String)map.get("DS_ACCT_NM"));
            }
        }
        return true;
    }

    /**
     * searchBillTo
     * @param bizMsg
     * @return
     */
    public static boolean searchBillTo(NFCL3000CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("billToCustAcctCd", bizMsg.billToCustAcctCd_H3.getValue());
        ssmParam.put("locNum", bizMsg.locNum_H3.getValue());
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        S21SsmEZDResult billToInfo = NFCL3000Query.getInstance().searchBillTo(bizMsg, ssmParam);
        if (!billToInfo.isCodeNormal()) {
            bizMsg.billToCustAcctCd_H3.setErrorInfo(1, "ZZZM9006E", new String[]{"Bill To Cust Account Number"});
            bizMsg.locNum_H3.setErrorInfo(1, "ZZZM9006E", new String[]{"Location"});
            return false;
        } else {
            Map<String, Object> map = (Map<String, Object>) billToInfo.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_H3, (String)map.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_H3, (bizMsg.billToCustAcctCd_H3.getValue()));

            if(!ZYPCommonFunc.hasValue(bizMsg.billToCustAcctNm_H3.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm_H3, (String)map.get("DS_ACCT_NM"));
            }
        }
        return true;
    }

    /**
     * getDsInvTpInfo
     * @param bizMsg
     * @return
     */
    public static boolean getDsInvTpInfo(NFCL3000CMsg bizMsg) {

        DS_INV_TPTMsg inMsg = new DS_INV_TPTMsg();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.dsInvTpCd.setValue(bizMsg.dsInvTpCd_H1.getValue());

        DS_INV_TPTMsg outMsg = (DS_INV_TPTMsg) EZDTBLAccessor.findByKey(inMsg);
        if(outMsg==null) {
            bizMsg.taxCalcFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
            bizMsg.taxExemFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.taxCalcFlg_H1, outMsg.taxCalcFlg.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.taxExemFlg_H1, outMsg.taxExemFlg.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.taxExemRsnCd_H1, outMsg.taxExemRsnCd.getValue());

            ZYPEZDItemValueSetter.setValue(bizMsg.arCoaCmpyCd_H1, outMsg.arCoaCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.arCoaBrCd_H1, outMsg.arCoaBrCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.arCoaCcCd_H1, outMsg.arCoaCcCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.arCoaAcctCd_H1, outMsg.arCoaAcctCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.arCoaProdCd_H1, outMsg.arCoaProdCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.arCoaChCd_H1, outMsg.arCoaChCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.arCoaAfflCd_H1, outMsg.arCoaAfflCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.arCoaProjCd_H1, outMsg.arCoaProjCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.arCoaExtnCd_H1, outMsg.arCoaExtnCd.getValue());
        }
        // START 2019/05/16 S.Takami [QC#50374,DEL]
//        // START 2019/04/25 S.Takami [QC#50078,ADD]
//        boolean isManualInvoice = S21StringUtil.isEquals(SYS_SRC.S21_ACCOUNTING_AR, bizMsg.sysSrcCd_H1.getValue());
//        boolean isNotFinalized = !S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.fnlzInvFlg_H4.getValue());
//        boolean isRecreateUomPullDown = isManualInvoice && isNotFinalized;
//        boolean isInvoiceWithContract = isInvoiceWithContract(bizMsg);
//        for (int idx = 0; idx < bizMsg.A.getValidCount(); idx++) {
//            setDplyCtrlFlg(bizMsg, idx);
//            if (isRecreateUomPullDown) {
//                bizMsg.A.no(idx).pkgUomCd_AC.clear();
//                bizMsg.A.no(idx).pkgUomDescTxt_AD.clear();
//                if (isInvoiceWithContract) {
//                    createPulldownListPkgUomFromBllgCycleUOM(bizMsg, idx);
//                } else {
//                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(idx).mdseCd_A1)) {
//                        createPulldownListPkgUomByMdse(bizMsg, idx);
//                    } else {
//                        createPulldownListPkgUomDefault(bizMsg, idx);
//                    }
//                }
//            }
//        }
//        // END 2019/04/25 S.Takami [QC#50078,ADD]
        // END 2019/05/16 S.Takami [QC#50374,DEL]
        return true;
    }

    /**
     * getAcctAffl
     * @param bizMsg
     * @return
     */
    public static boolean getAcctAffl(NFCL3000CMsg bizMsg) {

        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("sellToCustCd01", bizMsg.sellToCustCd_H3.getValue());

        SELL_TO_CUSTTMsgArray outMsg = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if(outMsg!=null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.arCoaAfflCd_H1, outMsg.no(0).coaAfflCd.getValue());
        }
        return true;
    }

    /**
     * checkHeader
     * @param bizMsg
     * @return
     */
    public static boolean checkHeader(NFCL3000CMsg bizMsg) {

        boolean isNotError = true;

        if (isManualInvoice(bizMsg) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue())) {
         // START 2016/07/08 S.Fujita [QC#9992,DEL]
            // Date Check
//            if(!bizMsg.invDt_H1.getValue().substring(0, 6).equals(bizMsg.acctDt.getValue().substring(0, 6))) {
//                bizMsg.invDt_H1.setErrorInfo(1, "NFCM0045E");
//                isNotError = false;
//            }
            // END   2016/07/08 S.Fujita [QC#9992,DEL]
            // START 2020/04/14 [QC#56319, ADD]
            if(!NFCL3000CommonLogic.calcNetDueDate(bizMsg)){
                isNotError = false;
            }
            // END   2020/04/14 [QC#56319, ADD]
            // Sales Rep Check
            if(!NFCL3000CommonLogic.searchSalesRep(bizMsg)) {
                isNotError = false;
            }

            // ShipTo Check
            if(ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd_H2.getValue()) || ZYPCommonFunc.hasValue(bizMsg.locNum_H2.getValue())) {
                if(NFCL3000CommonLogic.searchShipTo(bizMsg)) {
                    if (!NFCL3000CommonLogic.searchShipToAddr(bizMsg)) {
                        isNotError = false;
                    }
                } else {
                    isNotError = false;
                }
            } else {
                bizMsg.shipToCustCd_H2.clear();
                isNotError = true;
            }

            // BillTo Check
            if(ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd_H3.getValue()) && ZYPCommonFunc.hasValue(bizMsg.locNum_H3.getValue())) {
                if(NFCL3000CommonLogic.searchBillTo(bizMsg)) {
                    if (!NFCL3000CommonLogic.searchBillToAddr(bizMsg)) {
                        isNotError = false;
                    }
                } else {
                    isNotError = false;
                }
            } else {
                isNotError = false;
            }

            // START 2016/08/01 S.Fujita [QC#10148,ADD]
            NFCL3000CommonLogic.splitContactPsnNm(bizMsg, bizMsg.xxPsnNm_H2.getValue(), bizMsg.xxPsnNm_H3.getValue());
            // END   2016/08/01 S.Fujita [QC#10148,ADD]

            // Warehouse Check (not mandatory)
            if(ZYPCommonFunc.hasValue(bizMsg.shipFromInvtyLocCd_H4.getValue())) {
                if(!NFCL3000CommonLogic.searchWarehouse(bizMsg)) {
                    isNotError = false;
                }
            } else {
                bizMsg.rtlWhNm_H4.clear();
            }
            NFCL3000CommonLogic.setAddress(bizMsg);

            // START 2019/05/16 S.Takami [QC#50374,ADD]
            // Check Source Number length.
            if (isDsInvTpFsr(bizMsg)) {
                String asFsrNum = bizMsg.srcSysDocNum_H1.getValue();
                INV_PRT_SLS_PART_SUB_TOTTMsg invPrtSlsPartSubTotTMsg = new INV_PRT_SLS_PART_SUB_TOTTMsg();
                int maxLen = invPrtSlsPartSubTotTMsg.getAttr("cpoOrdNum").getDigit();
                if (ZYPCommonFunc.hasValue(asFsrNum) && asFsrNum.length() > maxLen) {
                    bizMsg.srcSysDocNum_H1.setErrorInfo(1, NFCM0905E, new String[] {String.valueOf(maxLen)});
                    isNotError = false;
                }
            }
            // END 2019/05/16 S.Takami [QC#50374,ADD]
            // START 2019/06/05 S.Takami [QC#50685,ADD]
            boolean hasTaxAdjItem = false;
            for (int lineIdx = 0; lineIdx < bizMsg.A.getValidCount(); lineIdx++) {
                String xxMstChkFlg = bizMsg.A.no(lineIdx).xxMstChkFlg_A1.getValue();
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, xxMstChkFlg)) {
                    hasTaxAdjItem = true;
                    break;
                }
            }
            if (hasTaxAdjItem && !isAvalTaxAdjustItem(bizMsg)) {
                List<String> taxAdjDsInvTpNmList = getAvalTaxAdjustmentDsInvTypeName(bizMsg);
                String[] taxAdjDsInvTpNmAray = null;
                if (taxAdjDsInvTpNmList == null || taxAdjDsInvTpNmList.isEmpty()) {
                    taxAdjDsInvTpNmAray = new String[] {"Standard Invoice Type"};
                } else {
                    StringBuilder addMsgBuilder = new StringBuilder("");
                    for (String taxAdjDsInvTpNm : taxAdjDsInvTpNmList) {
                        addMsgBuilder.append(" or " + taxAdjDsInvTpNm);
                    }
                    String addMsg = addMsgBuilder.toString().substring(4);
                    taxAdjDsInvTpNmAray = new String[] {addMsg};
                }
                bizMsg.dsInvTpCd_H1.setErrorInfo(1, NFCM0906E, taxAdjDsInvTpNmAray);
                isNotError = false;
            }
            // END 2019/06/05 S.Takami [QC#50685,ADD]

        }
        return isNotError;
    }

    /**
     * calcAmount_Acct_DrCr
     * @param bizMsg
     * @param calcMode
     */
    public static void calcAmount_Acct_DrCr(NFCL3000CMsg bizMsg, boolean calcMode) {
        BigDecimal dr = BigDecimal.ZERO;
        BigDecimal cr = BigDecimal.ZERO;

        if(bizMsg.C.getValidCount() < 1) {
            return;
        }

        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {

            // START 2016/09/26 S.Fujita [QC#13362,ADD]
            if (calcMode) {
                if (AJE_INV_ACCT_CLS.REVENUE_EARNED.equals(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue())) {
                    continue;
                }
            } else {
                if (AJE_INV_ACCT_CLS.REVENUE_EARNED.equals(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue()) 
                        || (DR_CR_TP_DEBIT.equals(bizMsg.C.no(i).drCrTpCd_C1.getValue()) && AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue()))) {
                    continue;
                }
            }
            // END   2016/09/26 S.Fujita [QC#13362,ADD]

            if(bizMsg.C.no(i).drCrTpCd_C1.getValue().equals(DR_CR_TP_DEBIT)) {
                dr = dr.add(getAmount(bizMsg.C.no(i).jrnlDealAmt_C1));
            } else {
                cr = cr.add(getAmount( bizMsg.C.no(i).jrnlDealAmt_C2));
            }
        }
        bizMsg.xxTotAmt_C1.setValue(dr);
        bizMsg.xxTotAmt_C2.setValue(cr);
    }

    /**
     * checkAcctDist
     * @param bizMsg
     * @return
     */
    public static boolean checkAcctDist(NFCL3000CMsg bizMsg) {
        boolean isSuccess = true;

        if(!ZYPCommonFunc.hasValue(bizMsg.invNum_H1.getValue())){
            return isSuccess;
        }
 
        if(bizMsg.C.getValidCount() < 1) {
            return isSuccess;
        }

        NFCL3000CommonLogic.calcAmount_Acct_DrCr(bizMsg, false);

        // START 2016/09/26 S.Fujita [QC#13362,ADD]
        if(!NFCL3000CommonLogic.checkAjeInvAcctCls(bizMsg)) {
            isSuccess = false;
        }
        // END   2016/09/26 S.Fujita [QC#13362,ADD]

        if(!NFCL3000CommonLogic.check9Segment(bizMsg)) {
            isSuccess = false;
        }

        // START 2016/07/20 S.Fujita [QC#10148,DEL]
//        if(!NFCL3000CommonLogic.checkAmount_AcctDist_DrCr_Line(bizMsg)) {
//            isSuccess = false;
//            return isSuccess;
//        }
        // END   2016/07/20 S.Fujita [QC#10148,DEL]
        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if(!NFCL3000CommonLogic.checkAmount_AcctDist_DrCr_Line_SalesCredit(bizMsg, bizMsg.B.no(i).xxLineNum_B1.getValue(), bizMsg.B.no(i).dealSlsCrAmt_B1.getValue())) {
                isSuccess = false;
            }
        }

// START 2018/05/22 Y.Matsui [QC#21841,DEL]
//        if(!NFCL3000CommonLogic.checkAmount_AcctDist_DrCr_Line_Freight(bizMsg)) {
//            isSuccess = false;
//        }
// END 2018/05/22 Y.Matsui [QC#21841,DEL]

        if(!NFCL3000CommonLogic.checkAmount_AcctDist_DrCr_Line_Tax(bizMsg)) {
            isSuccess = false;
        }
        if(!NFCL3000CommonLogic.checkAmount_AcctDist_DrCr_Totoal(bizMsg)) {
            isSuccess = false;
        }

        // START 2016/05/17 S.Fujita [QC#8373,ADD]
        NFCL3000CommonLogic.setAjeInvAcctDistPct(bizMsg);
        // END 2016/05/17 S.Fujita [QC#8373,ADD]

//START 2016/09/21 S.Yoshida [QC#11049,DEL]
//        // START 2016/05/18 S.Fujita [QC#7780,ADD]
//        if(!NFCL3000CommonLogic.checkItems_AcctDist(bizMsg)) {
//            isSuccess = false;
//        }
//        // END 2016/05/18 S.Fujita [QC#7780,ADD]
//END   2016/09/21 S.Yoshida [QC#11049,DEL]

        //START 2016/09/21 S.Yoshida [QC#11049,ADD]
        if (!checkGLDate(bizMsg)) {
            isSuccess = false;
        }
        //END   2016/09/21 S.Yoshida [QC#11049,ADD]

        return isSuccess;
    }

    // START 2016/07/20 S.Fujita [QC#10148,DEL]
//    /**
//     * checkAmount_AcctDist_DrCr_Line
//     * @param bizMsg
//     * @return
//     */
//    public static boolean checkAmount_AcctDist_DrCr_Line(NFCL3000CMsg bizMsg) {
//        boolean isNotError = true;
//
//        for(int i = 0; i < bizMsg.C.getValidCount(); i+=2) {
//            if(bizMsg.C.no(i).drCrTpCd_C1.getValue().equals(DR_CR_TP_DEBIT)) {
//                if(bizMsg.C.no(i).jrnlDealAmt_C1.getValue().compareTo(bizMsg.C.no(i+1).jrnlDealAmt_C2.getValue()) != 0) {
//                    bizMsg.C.no(i).jrnlDealAmt_C1.setErrorInfo(1, "NFCM0800E", new String[]{"Debit Amount", "Credit Amount"});
//                    bizMsg.C.no(i+1).jrnlDealAmt_C2.setErrorInfo(1, "NFCM0800E", new String[]{"Debit Amount", "Credit Amount"});
//                    isNotError = false;
//                }
//            }
//        }
//        return isNotError;
//    }
    // END   2016/07/20 S.Fujita [QC#10148,DEL]

    /**
     * checkAmount_AcctDist_DrCr_Line_SalesCredit
     * @param bizMsg
     * @param xxLineNum_B1
     * @param dealSlsCrAmt
     * @return
     */
    public static boolean checkAmount_AcctDist_DrCr_Line_SalesCredit(NFCL3000CMsg bizMsg, String xxLineNum_B1, BigDecimal dealSlsCrAmt) {
        boolean isNotError = true;

        if(bizMsg.C.getValidCount() < 1) {
            return true;
        }

        BigDecimal totSlsCrAmt = BigDecimal.ZERO;

        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if(bizMsg.C.no(i).drCrTpCd_C1.getValue().equals(DR_CR_TP_DEBIT)) {
                continue;
            }

            if(xxLineNum_B1.equals(bizMsg.C.no(i).xxLineNum_C1.getValue())) {
                if(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue().equals(AJE_INV_ACCT_CLS.FREIGHT)){
                    // START 2018/05/22 Y.Matsui [QC#21841,ADD]
                    totSlsCrAmt = totSlsCrAmt.add(getAmount(bizMsg.C.no(i).jrnlDealAmt_C2));
                    // END   2018/05/22 Y.Matsui [QC#21841,ADD]
                    // do nothing
                } else if(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue().equals(AJE_INV_ACCT_CLS.TAX)){
                    // do nothing
                // START 2016/09/26 S.Fujita [QC#13362,ADD]
                } else if(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue().equals(AJE_INV_ACCT_CLS.REVENUE_EARNED)){
                    // do nothing
                // END   2016/09/26 S.Fujita [QC#13362,ADD]
                } else {
                    totSlsCrAmt = totSlsCrAmt.add(getAmount(bizMsg.C.no(i).jrnlDealAmt_C2));
                }
            }
        }
        if(dealSlsCrAmt.compareTo(totSlsCrAmt) != 0) {
            NFCL3000CommonLogic.setErrorAcctDist_DrCr_Line_SalesCredit(bizMsg, xxLineNum_B1);
            isNotError = false;
        }
        return isNotError;
    }

    /**
     * checkAmount_AcctDist_DrCr_Line_Tax
     * @param bizMsg
     * @return
     */
    public static boolean checkAmount_AcctDist_DrCr_Line_Tax(NFCL3000CMsg bizMsg) {
        boolean isNotError = true;

        if(bizMsg.C.getValidCount() < 1) {
            return true;
        }

        bizMsg.xxTotAmt_T2.clearErrorInfo();
        bizMsg.xxTotAmt_T3.clearErrorInfo();

        // 2016/05/04 remove unused variables
       //String scNum = bizMsg.C.no(0).xxLineNum_C1.getValue();
       //BigDecimal frtAmt = bizMsg.xxTotAmt_T2.getValue();

        //---- start 2016/05/04 If it's credit memo, negate the amount.
        // START 2016/05/17 S.Fujita [QC#8366,MOD]
//        BigDecimal taxAmt = bizMsg.xxTotAmt_T3.getValue().multiply(getSignByInvtp(bizMsg));
        BigDecimal taxAmt = bizMsg.xxTotAmt_T3.getValue();
        // END 2016/05/17 S.Fujita [QC#8366,MOD]
        //---- end 2016/05/04

        BigDecimal totTaxAmt = BigDecimal.ZERO;
        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if(bizMsg.C.no(i).drCrTpCd_C1.getValue().equals(DR_CR_TP_DEBIT)) {
                continue;
            }

            if(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue().equals(AJE_INV_ACCT_CLS.TAX)){
                totTaxAmt = totTaxAmt.add(getAmount(bizMsg.C.no(i).jrnlDealAmt_C2));
            }
        }
        if(taxAmt.compareTo(totTaxAmt) != 0) {
            NFCL3000CommonLogic.setErrorAcctDist_DrCr_Line_Tax(bizMsg);
            isNotError = false;
        }
        return isNotError;
    }

// START 2018/05/22 Y.Matsui [QC#21841,DEL]
//    /**
//     * checkAmount_AcctDist_DrCr_Line_Freight
//     * @param bizMsg
//     * @return
//     */
//    public static boolean checkAmount_AcctDist_DrCr_Line_Freight(NFCL3000CMsg bizMsg) {
//        boolean isSuccess = true;
//
//        if(bizMsg.C.getValidCount() < 1) {
//            return true;
//        }
//
//        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
//            String invBolNum = bizMsg.D.no(i).invBolLineNum_D1.getValue();
//            BigDecimal frtAmt = bizMsg.D.no(i).shipDealFrtAmt_D1.getValue();
//            BigDecimal totAmt = NFCL3000CommonLogic.getAmount_AcctDist_DrCr_Line_Freight(bizMsg, invBolNum);
//            if(frtAmt.compareTo(totAmt)!=0) {
//                NFCL3000CommonLogic.setErrorAcctDist_DrCr_Line_Freight(bizMsg, invBolNum);
//                isSuccess = false;
//            }
//        }
//        return isSuccess;
//    }
//
//    /**
//     * getAmount_AcctDist_DrCr_Line_Freight
//     * @param bizMsg
//     * @param invBolNum
//     * @return
//     */
//    public static BigDecimal getAmount_AcctDist_DrCr_Line_Freight(NFCL3000CMsg bizMsg, String invBolNum) {
//        BigDecimal totAmt = BigDecimal.ZERO;
//
//        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
//            if(bizMsg.C.no(i).drCrTpCd_C1.getValue().equals(DR_CR_TP_DEBIT)) {
//                continue;
//            }
//            if(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue().equals(AJE_INV_ACCT_CLS.FREIGHT)){
//                if(bizMsg.C.no(i).invBolLineNum_C1.getValue().equals(invBolNum)) {
//                    totAmt = totAmt.add(getAmount(bizMsg.C.no(i).jrnlDealAmt_C2));
//                }
//            }
//        }
//        return totAmt;
//    }
// END   2018/05/22 Y.Matsui [QC#21841,DEL]

    /**
     * setErrorAcctDist_DrCr_Line_SalesCredit
     * @param bizMsg
     * @param xxLineNum_B1
     */
    public static void setErrorAcctDist_DrCr_Line_SalesCredit(NFCL3000CMsg bizMsg, String xxLineNum_B1) {

        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if(xxLineNum_B1.equals(bizMsg.C.no(i).xxLineNum_C1.getValue())) {
                if(bizMsg.C.no(i).drCrTpCd_C1.getValue().equals(DR_CR_TP_CREDIT)) {
                    if(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue().equals(AJE_INV_ACCT_CLS.REVENUE) || bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue().equals(AJE_INV_ACCT_CLS.UNEARNED_REVENUE)){
                        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg_SC.getValue())) {
                            // if accounting lines are split intentionally, indicate error message.
                            bizMsg.C.no(i).jrnlDealAmt_C2.setErrorInfo(2, "NFCM0867W");
                        } else {
                            bizMsg.C.no(i).jrnlDealAmt_C2.setErrorInfo(1, "NFCM0800E", new String[]{"Debit/Credit Total Amount", "Sales Credit Amount"});
                        }
                    }
                    // START 2018/05/22 Y.Matsui [QC#21841,ADD]
                    if(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue().equals(AJE_INV_ACCT_CLS.FREIGHT)){
                        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg_SC.getValue())) {
                            // if accounting lines are split intentionally, indicate error message.
                            bizMsg.C.no(i).jrnlDealAmt_C2.setErrorInfo(2, "NFCM0867W");
                        } else {
                            bizMsg.C.no(i).jrnlDealAmt_C2.setErrorInfo(1, "NFCM0800E", new String[]{"Debit/Credit Total Amount", "Sales Credit Amount"});
                        }
                    }
                    // END   2018/05/22 Y.Matsui [QC#21841,ADD]
                }
            }
        }
    }

// START 2018/05/22 Y.Matsui [QC#21841,DEL]
//    /**
//     * setErrorAcctDist_DrCr_Line_Freight
//     * @param bizMsg
//     * @param invBolNum
//     */
//    public static void setErrorAcctDist_DrCr_Line_Freight(NFCL3000CMsg bizMsg, String invBolNum) {
//
//        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
//            if(bizMsg.C.no(i).drCrTpCd_C1.getValue().equals(DR_CR_TP_CREDIT)) {
//                if(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue().equals(AJE_INV_ACCT_CLS.FREIGHT)){
//                    if(bizMsg.C.no(i).invBolLineNum_C1.getValue().equals(invBolNum)) {
//                        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg_FR.getValue())) {
//                            // if accounting lines are split intentionally, indicate error message.
//                            bizMsg.C.no(i).jrnlDealAmt_C2.setErrorInfo(2, "NFCM0867W");
//                        } else {
//                            bizMsg.C.no(i).jrnlDealAmt_C2.setErrorInfo(1, "NFCM0800E", new String[]{"Debit/Credit Total Freight Amount", "BOL Freight Amount"});
//                        }
//                    }
//                }
//            }
//        }
//    }
// END   2018/05/22 Y.Matsui [QC#21841,DEL]

    /**
     * setErrorAcctDist_DrCr_Line_Tax
     * @param bizMsg
     */
    public static void setErrorAcctDist_DrCr_Line_Tax(NFCL3000CMsg bizMsg) {

        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if(bizMsg.C.no(i).drCrTpCd_C1.getValue().equals(DR_CR_TP_CREDIT)) {
                if(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue().equals(AJE_INV_ACCT_CLS.TAX)){
                    if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg_TX.getValue())) {
                        // if accounting lines are split intentionally, indicate error message.
                        bizMsg.C.no(i).jrnlDealAmt_C2.setErrorInfo(2, "NFCM0867W");
                    } else {
                        bizMsg.C.no(i).jrnlDealAmt_C2.setErrorInfo(1, "NFCM0800E", new String[]{"Debit/Credit Total Tax Amount", "Total Tax Amount"});
                    }
                }
            }
        }
    }

//    /**
//     * checkAmount_AcctDist_DrCr_Line_Cr
//     * @param bizMsg
//     * @param idx
//     * @return
//     */
//    public static boolean checkAmount_AcctDist_DrCr_Line_Cr(NFCL3000CMsg bizMsg, int idx) {
//
//        boolean isNotError = true;
//        String invBolLineNum = bizMsg.C.no(idx).invBolLineNum_C1.getValue();
//        String invLineNum = bizMsg.C.no(idx).invLineNum_C1.getValue();
//        String ajeLineIdxNum = bizMsg.C.no(idx).ajeLineIdxNum_C1.getValue();
//        BigDecimal jrnlDealAmt = bizMsg.C.no(idx).jrnlDealAmt_C1.getValue();
//
//        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
//            if(!bizMsg.C.no(i).drCrTpCd_C1.getValue().equals(DR_CR_TP_CREDIT)) {
//                continue;
//            }
//            if(!invBolLineNum.equals(bizMsg.C.no(i).invBolLineNum_C1.getValue())) {
//                continue;
//            }
//            if(!invLineNum.equals(bizMsg.C.no(i).invLineNum_C1.getValue())) {
//                continue;
//            }
//            if(!ajeLineIdxNum.equals(bizMsg.C.no(i).ajeLineIdxNum_C1.getValue())) {
//                continue;
//            }
//
//            if(jrnlDealAmt.compareTo(bizMsg.C.no(i).jrnlDealAmt_C2.getValue()) != 0) {
//                bizMsg.C.no(idx).jrnlDealAmt_C1.setErrorInfo(1, "NFCM0800E", new String[]{"Debit Amount", "Credit Amount"});
//                bizMsg.C.no(i).jrnlDealAmt_C2.setErrorInfo(1, "NFCM0800E", new String[]{"Debit Amount", "Credit Amount"});
//                isNotError = false;
//            }
//        }
//        return isNotError;
//    }

    /**
     * checkAmount_AcctDist_DrCr_Totoal
     * @param bizMsg
     * @return
     */
    public static boolean checkAmount_AcctDist_DrCr_Totoal(NFCL3000CMsg bizMsg) {
        boolean isNotError = true;
        if(bizMsg.xxTotAmt_C1.getValue().compareTo(bizMsg.xxTotAmt_C2.getValue())!=0) {
            for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
                bizMsg.xxTotAmt_C1.setErrorInfo(1, "NFCM0800E", new String[]{"Debit Total Amount", "Credit Total Amount"});
                bizMsg.xxTotAmt_C2.setErrorInfo(1, "NFCM0800E", new String[]{"Debit Total Amount", "Credit Total Amount"});
            }
            isNotError = false;
        }
        //---- start 2016/05/04 If it's credit memo negate the amount
        // START 2016/05/17 S.Fujita [QC#8366,MOD]
//        if(bizMsg.xxTotAmt_T4.getValue().multiply(getSignByInvtp(bizMsg)).compareTo(bizMsg.xxTotAmt_C1.getValue()) != 0) {
        if(bizMsg.xxTotAmt_T4.getValue().compareTo(bizMsg.xxTotAmt_C1.getValue()) != 0) {
        // END 2016/05/17 S.Fujita [QC#8366,MOD]
        //---- end 2016/05/04
            for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
                bizMsg.xxTotAmt_C1.setErrorInfo(1, "NFCM0800E", new String[]{"Debit/Credit Total Amount", "Invoice Total Amount"});
                bizMsg.xxTotAmt_C2.setErrorInfo(1, "NFCM0800E", new String[]{"Debit/Credit Total Amount", "Invoice Total Amount"});
            }
            isNotError = false;
        }
        //---- start 2016/05/04 if it's credit memo negate the amount.
        // START 2016/05/17 S.Fujita [QC#8366,MOD]
//        if(bizMsg.xxTotAmt_T4.getValue().multiply(getSignByInvtp(bizMsg)).compareTo(bizMsg.xxTotAmt_C2.getValue()) != 0) {
        if(bizMsg.xxTotAmt_T4.getValue().compareTo(bizMsg.xxTotAmt_C2.getValue()) != 0) {
        // END 2016/05/17 S.Fujita [QC#8366,MOD]
        //---- end 2016/05/04
            for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
                bizMsg.xxTotAmt_C1.setErrorInfo(1, "NFCM0800E", new String[]{"Debit/Credit Total Amount", "Invoice Total Amount"});
                bizMsg.xxTotAmt_C2.setErrorInfo(1, "NFCM0800E", new String[]{"Debit/Credit Total Amount", "Invoice Total Amount"});
                bizMsg.xxTotAmt_T4.setErrorInfo(1, "NFCM0800E", new String[]{"Debit/Credit Total Amount", "Invoice Total Amount"});
            }
            isNotError = false;
        }
        return isNotError;
    }

    /**
     * getinvLineIdx
     * @param bizMsg
     * @return
     */
    public static int getinvLineIdx(NFCL3000CMsg bizMsg,String invLineNum) {
        int idx = 0;
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if(invLineNum.equals(bizMsg.A.no(i).invLineNum_A1.getValue())) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    /**
     * getInvBolIdx
     * @param bizMsg
     * @return
     */
    public static int getInvBolIdx(NFCL3000CMsg bizMsg,String invBolLineNum) {
        int idx = 0;
        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
            if(invBolLineNum.equals(bizMsg.D.no(i).invBolLineNum_D1.getValue())) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    /**
     * calcAmount_InvoiceTotal
     * @param bizMsg
     */
    public static void calcAmount_InvoiceTotal(NFCL3000CMsg bizMsg) {
        BigDecimal totAmt = BigDecimal.ZERO;
        // START 2016/07/08 S.Fujita [QC#10160,MOD]
        //---- start 2016/05/04 If it's credit memo, negate the amount
//        totAmt = totAmt.add(bizMsg.xxTotAmt_T1.getValue().multiply(getSignByInvtp(bizMsg)));
//        totAmt = totAmt.add(bizMsg.xxTotAmt_T2.getValue().multiply(getSignByInvtp(bizMsg)));
//        totAmt = totAmt.add(bizMsg.xxTotAmt_T3.getValue().multiply(getSignByInvtp(bizMsg)));
//        bizMsg.xxTotAmt_T4.setValue(totAmt.multiply(getSignByInvtp(bizMsg)));
        totAmt = totAmt.add(getAmount(bizMsg.xxTotAmt_T1));
        totAmt = totAmt.add(getAmount(bizMsg.xxTotAmt_T2));
        totAmt = totAmt.add(getAmount(bizMsg.xxTotAmt_T3));
        bizMsg.xxTotAmt_T4.setValue(totAmt);
      //---- end 2016/05/04
        // END   2016/07/08 S.Fujita [QC#10160,MOD]
    }

    /**
     * calcPct
     * @param totAmt
     * @param srcAmt
     * @param scale
     */
    public static BigDecimal calcPct(BigDecimal totAmt, BigDecimal srcAmt, int scale) {
        
        BigDecimal resultPct = new BigDecimal(0);

        if (srcAmt.intValue() == 0) {
            return BigDecimal.ZERO;
        }
        
        resultPct = srcAmt.divide(totAmt);
        resultPct = resultPct.multiply(new BigDecimal(100));
        resultPct = resultPct.setScale(scale, BigDecimal.ROUND_HALF_UP);
        
        return resultPct;
    }

//START 2016/06/03 S.Fujita [QC#9157,DEL]
//    /**
//     * reCalcAmount_SlsCr
//     * @param bizMsg
//     */
//    public static void reCalcAmount_SlsCr(NFCL3000CMsg bizMsg) {
//
//        
//        BigDecimal invTotSlsAmt = BigDecimal.ZERO;
//        BigDecimal invTotSlsTaxAmt = BigDecimal.ZERO;
//        BigDecimal invTotFrtAmt = BigDecimal.ZERO;
//        BigDecimal invTotFrtTaxAmt = BigDecimal.ZERO;
//        int bolIdx = 0;
//        int lineIdx = 0;
//        
//        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            String invBolLineNum = bizMsg.B.no(i).invBolLineNum_B1.getValue();
//            String invLineNum = bizMsg.B.no(i).invLineNum_B1.getValue();
//
//            bolIdx = NFCL3000CommonLogic.getInvBolIdx(bizMsg, invBolLineNum);
//            lineIdx = NFCL3000CommonLogic.getinvLineIdx(bizMsg, invLineNum);
//            
//            invTotSlsAmt = bizMsg.A.no(lineIdx).invLineDealNetAmt_A1.getValue();
//            invTotSlsTaxAmt = bizMsg.A.no(lineIdx).invLineDealTaxAmt_A1.getValue();
//            
//            invTotFrtAmt = bizMsg.D.no(bolIdx).shipDealFrtAmt_D1.getValue();
//            invTotFrtTaxAmt = bizMsg.D.no(bolIdx).frtDealTaxAmt_D1.getValue();
//            
//            BigDecimal slsAmt = invTotSlsAmt;
//            BigDecimal pct = bizMsg.B.no(i).slsRepCrPct_B2.getValue().divide(new BigDecimal(100));
//            int scale = bizMsg.aftDeclPntDigitNum.getValueInt();
//            slsAmt = NFCL3000CommonLogic.calcAmountByPct(slsAmt, pct, scale);
//            bizMsg.B.no(i).dealSlsCrAmt_B1.setValue(slsAmt);
//        }
//    }
//END   2016/06/03 S.Fujita [QC#9157,DEL]

    /**
     * setCodeName
     * @param bizMsg
     */
    public static void setCodeName(NFCL3000CMsg bizMsg) {

        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NFCL3000CommonLogic.setCodeNameUom(bizMsg, i);
            // START 2018/05/22 Y.Matsui [QC#21841,ADD]
            NFCL3000CommonLogic.setCodeNameInvLineCatg(bizMsg, i);
            // END   2018/05/22 Y.Matsui [QC#21841,ADD]
        }
        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NFCL3000CommonLogic.setCodeNameAcctClass(bizMsg, i);
        }
    }

    /**
     * setCodeNameUom
     * @param bizMsg
     */
    public static void setCodeNameUom(NFCL3000CMsg bizMsg, int idx) {
        // START 2018/08/15 Y.Matsui [QC#27651,MOD]
        for(int i = 0; i < bizMsg.A.no(idx).pkgUomCd_AC.length(); i++) {
            if(ZYPCommonFunc.hasValue(bizMsg.A.no(idx).pkgUomCd_AC.no(i).getValue())) {
                if(bizMsg.A.no(idx).pkgUomCd_A1.getValue().equals(bizMsg.A.no(idx).pkgUomCd_AC.no(i).getValue())) {
                    // START 2018/09/28 T.Ogura [QC#28526,ADD]
//                    bizMsg.A.no(idx).pkgUomNm_A1.setValue(bizMsg.A.no(idx).pkgUomNm_AD.no(i).getValue());
                    bizMsg.A.no(idx).pkgUomDescTxt_A1.setValue(bizMsg.A.no(idx).pkgUomDescTxt_AD.no(i).getValue());
                    // END   2018/09/28 T.Ogura [QC#28526,ADD]
                }
            }
        }
        // END   2018/08/15 Y.Matsui [QC#27651,MOD]

        // START 2016/08/01 S.Fujita [QC#10148,ADD]
        for(int i = 0; i < bizMsg.svcInvChrgTpCd_AC.length(); i++) {
            if(ZYPCommonFunc.hasValue(bizMsg.svcInvChrgTpCd_AC.no(i).getValue())) {
                if(bizMsg.A.no(idx).svcInvChrgTpCd_A1.getValue().equals(bizMsg.svcInvChrgTpCd_AC.no(i).getValue())) {
                    bizMsg.A.no(idx).svcInvChrgTpNm_A1.setValue(bizMsg.svcInvChrgTpNm_AD.no(i).getValue());
                }
            }
        }
        for(int i = 0; i < bizMsg.crDrRsnCd_AC.length(); i++) {
            if(ZYPCommonFunc.hasValue(bizMsg.crDrRsnCd_AC.no(i).getValue())) {
                if(bizMsg.A.no(idx).crDrRsnCd_A1.getValue().equals(bizMsg.crDrRsnCd_AC.no(i).getValue())) {
                    bizMsg.A.no(idx).crDrRsnNm_A1.setValue(bizMsg.crDrRsnNm_AD.no(i).getValue());
                }
            }
        }
        // END   2016/08/01 S.Fujita [QC#10148,ADD]
    }

    /**
     * setCodeNameAcctClass
     * @param bizMsg
     */
    public static void setCodeNameAcctClass(NFCL3000CMsg bizMsg, int idx) {
        for(int i = 0; i < bizMsg.ajeInvAcctClsCd_CC.length(); i++) {
            if(ZYPCommonFunc.hasValue(bizMsg.ajeInvAcctClsCd_CC.no(i).getValue())) {
                if(bizMsg.C.no(idx).ajeInvAcctClsCd_C1.getValue().equals(bizMsg.ajeInvAcctClsCd_CC.no(i).getValue())) {
                    bizMsg.C.no(idx).ajeInvAcctClsNm_C1.setValue(bizMsg.ajeInvAcctClsNm_CD.no(i).getValue());
                }
            }
        }
    }

    /**
     * check_LineTAB
     * @param bizMsg
     * @param idx
     */
    public static boolean check_LineTAB(NFCL3000CMsg bizMsg) {
        boolean isSuccess = true;

        if (isManualInvoice(bizMsg) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue())) {
            // Line Check
            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if(!NFCL3000CommonLogic.checkLine_Line_Idx(bizMsg, i)) {
                    isSuccess = false;
                }
            }
            if(!isSuccess) {
                return isSuccess;
            }
            if(!NFCL3000CommonLogic.check_BOL(bizMsg, false)) {
                for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                    bizMsg.A.no(i).invBolLineNum_A1.setErrorInfo(1, NOT_ASSIGNED_ERROR, new String[]{PRM_BOL_S, PRM_LINE});
                }
                isSuccess = false;
            }
            if(!isSuccess) {
                return isSuccess;
            }

            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                // Merchandise
                if(!NFCL3000CommonLogic.getItemName_A(bizMsg, i, false)) {
                    isSuccess = false;
                }
            }
            // START 2017/12/25 E.Kameishi [QC#20312,ADD]
            if (!NFCL3000CommonLogic.checkTaxAdjustmentItem(bizMsg)) {
                isSuccess = false;
            }
            // END 2017/12/25 E.Kameishi [QC#20312,ADD]

            // START 2018/05/22 Y.Matsui [QC#21841,ADD]
            if (!NFCL3000CommonLogic.checkMdseAndInvLineCatg(bizMsg)) {
                isSuccess = false;
            }
            // END   2018/05/22 Y.Matsui [QC#21841,ADD]
        }
        return isSuccess;
    }

    /**
     * updateInvBol
     * @param bizMsg
     * @return
     */
    public static boolean updateInvBol(NFCL3000CMsg bizMsg) {

        boolean isUpdate = false;
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            INV_BOLTMsg inMsg = new INV_BOLTMsg();
            inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            inMsg.invNum.setValue(bizMsg.invNum_H1.getValue());
            inMsg.invBolLineNum.setValue(bizMsg.D.no(i).invBolLineNum_D1.getValue());
            INV_BOLTMsg updMsg = (INV_BOLTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0864E", new String[] {"INV_BOL", bizMsg.D.no(i).invBolLineNum_D1.getValue() });
                return false;
            }

            if (!updMsg.bolNum.getValue().equals(bizMsg.D.no(i).bolNum_D1.getValue())) {
                ZYPEZDItemValueSetter.setValue(updMsg.bolNum, bizMsg.D.no(i).bolNum_D1.getValue());
                isUpdate = true;
            }

            if (!updMsg.shipToCustAcctNm.getValue().equals(bizMsg.D.no(i).shipToCustAcctNm_D1.getValue())) {
                ZYPEZDItemValueSetter.setValue(updMsg.shipToCustAcctNm, bizMsg.D.no(i).shipToCustAcctNm_D1.getValue());
                isUpdate = true;
            }
            if (!updMsg.shipToCtacPsnFirstNm.getValue().equals(bizMsg.D.no(i).shipToCtacPsnFirstNm_D1.getValue())) {
                ZYPEZDItemValueSetter.setValue(updMsg.shipToCtacPsnFirstNm, bizMsg.D.no(i).shipToCtacPsnFirstNm_D1.getValue());
                isUpdate = true;
            }
            if (!updMsg.shipToCtacPsnLastNm.getValue().equals(bizMsg.D.no(i).shipToCtacPsnLastNm_D1.getValue())) {
                ZYPEZDItemValueSetter.setValue(updMsg.shipToCtacPsnLastNm, bizMsg.D.no(i).shipToCtacPsnLastNm_D1.getValue());
                isUpdate = true;
            }

            if (isUpdate) {
                EZDTBLAccessor.update(updMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0862E", new String[] {"INV_BOL" });
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * updateInvoiceStatus
     * @param bizMsg
     * @return
     */
    public static boolean updateInvoiceStatus(NFCL3000CMsg bizMsg) {

        INVTMsg inMsg = new INVTMsg();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.invNum.setValue(bizMsg.invNum_H1.getValue());
        INVTMsg updMsg = (INVTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0864E", new String[]{"INV", bizMsg.invNum_H1.getValue()});
            return false;
        }

        String fnlzInvFlg = ZYPConstant.FLG_OFF_N;
        if(ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H3.getValue())) {
            fnlzInvFlg = ZYPConstant.FLG_ON_Y;
        }
        updMsg.fnlzInvFlg.setValue(fnlzInvFlg);
        // START 2019/05/10 S.Takami [QC#50148,MOD]
//        if(!updMsg.invFirstCmntTxt.getValue().equals(bizMsg.invFirstCmntTxt_E1.getValue())) {
//            ZYPEZDItemValueSetter.setValue(updMsg.invFirstCmntTxt, bizMsg.invFirstCmntTxt_E1.getValue());
//        }
        isUpdateComment(updMsg, bizMsg.xxInvMemoTxt_E1.getValue());
        // END 2019/05/10 S.Takami [QC#50148,MOD]

        EZDTBLAccessor.update(updMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0862E", new String[]{"INV"});
            return false;
        }
        return true;
    }

//START 2016/08/03 S.Fujita [QC#12864,DEL]
//    /**
//     * updateAjeInvAcctDist
//     * @param bizMsg
//     * @return
//     */
//    public static boolean updateAjeInvAcctDist(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {
//
//        globalMsg.E.clear();
//
//        NFCL3000CommonLogic.searchAjeInvAcctDist(bizMsg, globalMsg);
//
//        for(int i = 0; i < globalMsg.E.getValidCount(); i++) {
//            AJE_INV_ACCT_DISTTMsg inMsg = new AJE_INV_ACCT_DISTTMsg();
//            inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//            inMsg.ajeInvAcctDistPk.setValue(globalMsg.E.no(i).ajeInvAcctDistPk_E1.getValue());
//            AJE_INV_ACCT_DISTTMsg updMsg = (AJE_INV_ACCT_DISTTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
//                bizMsg.setMessageInfo("NFCM0864E", new String[]{"AJE_INV_ACCT_DIST", null});
//                return false;
//            }
//
//            // START 2016/05/17 S.Fujita [QC#8375,MOD]
////            updMsg.procStsCd.setValue(PROC_STS.ERROR);
//            if(ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H3.getValue())) {
//                updMsg.procStsCd.setValue(PROC_STS.COMPLEATED);
//            } else {
//                updMsg.procStsCd.setValue(PROC_STS.ERROR);
//            }
//            // END 2016/05/17 S.Fujita [QC#8375,MOD]
//
//            EZDTBLAccessor.update(updMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
//                bizMsg.setMessageInfo("NFCM0862E", new String[]{"AJE_INV_ACCT_DIST"});
//                return false;
//            }
//        }
//        return true;
//    }
//END   2016/08/03 S.Fujita [QC#12864,DEL]
    
    /**
     * searchAjeInvAcctDist
     * @param bizMsg
     * @param globalMsg
     * @return
     */
    public static boolean searchAjeInvAcctDist(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());

        S21SsmEZDResult ssmResult = NFCL3000Query.getInstance().updateAjeInvAcctDist(globalMsg, ssmParam);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt < 1) {
                return true;
            }
            else if (queryResCnt > globalMsg.E.length()) {
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = globalMsg.E.length();
            } else {
                bizMsg.setMessageInfo("NZZM0002I");
            }
            globalMsg.E.setValidCount(queryResCnt);
        } else {
            bizMsg.setMessageInfo("NZZM0000E");
            globalMsg.E.setValidCount(0);
        }
        return true;
    }

    /**
     * setAcctDistError
     * @param bizMsg
     * @return
     */
    public static boolean setAcctDistError(NFCL3000CMsg bizMsg) {
        boolean isNotError = true;
        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if(ZYPCommonFunc.hasValue(bizMsg.C.no(i).invErrMsgTxt_C1.getValue())) {
                isNotError = false;
            }
        }
        return isNotError;
    }

    /**
     * glCodeCombinationCheck
     * @param bizMsg
     * @return
     */
    public static boolean glCodeCombinationCheck(NFCL3000CMsg bizMsg, NFCL3000_CCMsg cBizMsg) {
        boolean isNotError = true;

        NFZC102001 api = new NFZC102001();
        NFZC102001PMsg apiMsg = new NFZC102001PMsg();

        ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(apiMsg.xxMstChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxArcsApiChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxGlCmbnChkFlg, ZYPConstant.FLG_ON_Y);

        ZYPEZDItemValueSetter.setValue(apiMsg.coaCmpyCd, cBizMsg.ajeCoaCmpyCd_C1.getValue());
        ZYPEZDItemValueSetter.setValue(apiMsg.coaBrCd, cBizMsg.ajeCoaBrCd_C1.getValue());
        ZYPEZDItemValueSetter.setValue(apiMsg.coaCcCd, cBizMsg.ajeCoaCcCd_C1.getValue());
        ZYPEZDItemValueSetter.setValue(apiMsg.coaAcctCd, cBizMsg.ajeCoaAcctCd_C1.getValue());
        ZYPEZDItemValueSetter.setValue(apiMsg.coaProdCd, cBizMsg.ajeCoaProdCd_C1.getValue());
        ZYPEZDItemValueSetter.setValue(apiMsg.coaChCd, cBizMsg.ajeCoaChCd_C1.getValue());
        ZYPEZDItemValueSetter.setValue(apiMsg.coaAfflCd, cBizMsg.ajeCoaAfflCd_C1.getValue());
        ZYPEZDItemValueSetter.setValue(apiMsg.coaProjCd, cBizMsg.ajeCoaProjCd_C1.getValue());
        ZYPEZDItemValueSetter.setValue(apiMsg.coaExtnCd, cBizMsg.ajeCoaExtnCd_C1.getValue());

        // START 2019/05/29 S.Takami [QC#50542,MOD]
//        // QC#19433 Start
//        ZYPEZDItemValueSetter.setValue(apiMsg.resrcObjNm, "NFCL3000");
//        // QC#19433 End
        String drCrTpCd = cBizMsg.drCrTpCd_C1.getValue();
        if (S21StringUtil.isEquals(DR_CR_TP_DEBIT, drCrTpCd)) {
            apiMsg.resrcObjNm.setValue(RESRC_OBJ_NM_DEBIT);
        } else {
            apiMsg.resrcObjNm.setValue(RESRC_OBJ_NM_CREDIT);
        }
        // END 2019/05/29 S.Takami [QC#50542,MOD]

        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        String result = apiMsg.getReturnCode();
        if(apiMsg.xxMsgIdList.getValidCount() > 0) {
            String msgId;
            String msgTxt;
            msgId = apiMsg.xxMsgIdList.no(0).xxMsgId.getValue();
            msgTxt = apiMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue();

            cBizMsg.xxScrItem61Txt_C1.clearErrorInfo();
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, msgId, new String[] {msgTxt});
            ZYPEZDItemValueSetter.setValue(cBizMsg.invErrMsgTxt_C1, S21MessageFunc.clspGetMessage(msgId, new String[] {msgTxt }));

            isNotError = false;
        // START 2016/06/16 S.Fujita [QC#10254,ADD]
        } else {
            cBizMsg.xxScrItem61Txt_C1.clearErrorInfo();
            cBizMsg.invErrMsgTxt_C1.clear();
            cBizMsg.invldValTxt_C1.clear();
        // END   2016/06/16 S.Fujita [QC#10254,ADD]
        }
        return isNotError;
    }

    /**
     * check9Segment
     * @param bizMsg
     * @return
     */
    public static boolean check9Segment(NFCL3000CMsg bizMsg) {
        // Check 9 Segment
        boolean isNotError = true;
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if (!NFCL3000CommonLogic.check9Segment_idx(bizMsg, bizMsg.C.no(i))) {
                isNotError = false;
            }
        }
        return isNotError;
    }

    /**
     * check9Segment_idx
     * @param bizMsg
     * @return
     */
    public static boolean check9Segment_idx(NFCL3000CMsg bizMsg, NFCL3000_CCMsg cBizMsg) {
        // Check 9 Segment
        if(!NFCL3000CommonLogic.splitCOA9SegString(cBizMsg)) {
            return false;
        }
        if(!NFCL3000CommonLogic.glCodeCombinationCheck(bizMsg, cBizMsg)) {
            return false;
        }
        return true;
    }

    /**
     * isFinalize
     * @param bizMsg
     * @return
     */
    public static boolean isFinalize(NFCL3000CMsg bizMsg) {

        INVTMsg inMsg = new INVTMsg();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.invNum.setValue(bizMsg.invNum_H1.getValue());
        INVTMsg outMsg = (INVTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0864E", new String[]{"INV", bizMsg.invNum_H1.getValue()});
            return false;
        }
        if(outMsg.fnlzInvFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            return true;
        }
        return false;
    }

    /**
     * setSalesCreditLineNum
     * @param bizMsg
     */
    public static void setSalesCreditLineNum(NFCL3000CMsg bizMsg) {

        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
            // START 2019/04/11 S.Takami [QC#31165,MOD]
            // if(ZYPCommonFunc.hasValue(bizMsg.C.no(i).dsInvSlsCrPk_C1.getValue()) && bizMsg.C.no(i).dsInvSlsCrPk_C1.getValue().compareTo(BigDecimal.ZERO)!=0) {
            if(ZYPCommonFunc.hasValue(bizMsg.C.no(i).dsInvSlsCrPk_C1.getValue()) //
                    && BigDecimal.ZERO.compareTo(bizMsg.C.no(i).dsInvSlsCrPk_C1.getValue()) != 0) { // END 2019/04/11 S.Takami [QC#31165,MOD]
                String xxLineNum = NFCL3000CommonLogic.getSalesCreditLineNum(bizMsg, i);
                if(xxLineNum != null) {
                    bizMsg.C.no(i).xxLineNum_C1.setValue(xxLineNum);
                }
            }
        }
    }

    /**
     * getSalesCreditLineNum
     * @param bizMsg
     * @param idx
     * @return
     */
    public static String getSalesCreditLineNum(NFCL3000CMsg bizMsg, int idx) {

        BigDecimal srcPk = bizMsg.C.no(idx).dsInvSlsCrPk_C1.getValue();
        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if(ZYPCommonFunc.hasValue(bizMsg.B.no(i).dsInvSlsCrPk_B1) // 2019/04/11 S.Takami [QC#31165,ADD Null Check]
                    && srcPk.compareTo(bizMsg.B.no(i).dsInvSlsCrPk_B1.getValue()) == 0) {
                return bizMsg.B.no(i).xxLineNum_B1.getValue();
            }
        }
        return null;
    }

    /**
     * getSysSrcNm
     * @param bizMsg
     * @return
     */
    public static String getSysSrcNm(NFCL3000CMsg bizMsg) {
        
        SYS_SRCTMsg inMsg = new SYS_SRCTMsg();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.sysSrcCd.setValue(SYS_SRC.S21_ACCOUNTING_AR);
        SYS_SRCTMsg outMsg = (SYS_SRCTMsg)S21CodeTableAccessor.findByKey(inMsg);
        return outMsg.sysSrcNm.getValue();
    }

    /**
     * setSummaryMode
     * @param bizMsg
     * @param globalMsg
     * @param sumMode
     */
    public static void setSummaryMode(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg, boolean sumMode) {
        
        if(globalMsg.F.getValidCount() < 1 ) {
            return ;
        }
        // Debit
        List<String> segTxtList = new ArrayList<String>();

        bizMsg.C.clear();
        bizMsg.C.setValidCount(0);
        String segTxt = null;
        String acctClsCd = null;
        BigDecimal segAmt = BigDecimal.ZERO;
        int idx = 0;
        int no = 0;
        boolean isSet = false;
        for(no = 0; no < globalMsg.F.getValidCount(); no++) {
            if(globalMsg.F.no(no).drCrTpCd_F1.getValue().equals(DR_CR_TP_CREDIT)) {
                continue;
            }
            segTxt = globalMsg.F.no(no).xxScrItem61Txt_F1.getValue();
            acctClsCd = globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue();
            if(NFCL3000CommonLogic.isExistSegTxt(segTxtList, segTxt)) {
                continue;
            }
            segTxtList.add(segTxt);
            segAmt = BigDecimal.ZERO;
            for(int j = 0; j < globalMsg.F.getValidCount(); j++) {
                if(globalMsg.F.no(j).drCrTpCd_F1.getValue().equals(DR_CR_TP_CREDIT)) {
                    continue;
                }
                if(segTxt.equals(globalMsg.F.no(j).xxScrItem61Txt_F1.getValue())) {
                    segAmt = segAmt.add(getAmount(globalMsg.F.no(j).jrnlDealAmt_F1));
                }
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).drCrTpCd_C1, DR_CR_TP_DEBIT);

            // START 2016/09/26 S.Fujita [QC#13362,MOD]
            if (sumMode) {
                if(AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue()) //
                        || AJE_INV_ACCT_CLS.REVENUE_EARNED.equals(globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctClsCd_C1, AJE_INV_ACCT_CLS.UNEARNED_REVENUE);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctClsCd_C1, AJE_INV_ACCT_CLS.RECEIVABLES);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctClsCd_C1, globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue());
            }
            // END   2016/09/26 S.Fujita [QC#13362,MOD]

            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxScrItem61Txt_C1, segTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlDealAmt_C1, segAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invErrMsgTxt_C1, globalMsg.F.no(no).invErrMsgTxt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invldValTxt_C1, globalMsg.F.no(no).invldValTxt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).glDt_C1, globalMsg.F.no(no).glDt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlCratDt_C1, globalMsg.F.no(no).jrnlCratDt_F1.getValue());
            // START 2017/03/17 T.Murai [QC#14205,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistTblNm_C, globalMsg.F.no(no).xxRecHistTblNm_F.getValue());
            // END   2017/03/17 T.Murai [QC#14205,ADD]
            isSet = true;
            idx++;
        }
        if(!isSet) {
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).drCrTpCd_C1, DR_CR_TP_DEBIT);

            // START 2016/09/26 S.Fujita [QC#13362,MOD]
            if (sumMode) {
                if(AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(bizMsg.C.no(idx).ajeInvAcctClsCd_C1.getValue()) || AJE_INV_ACCT_CLS.REVENUE_EARNED.equals(bizMsg.C.no(idx).ajeInvAcctClsCd_C1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctClsCd_C1, AJE_INV_ACCT_CLS.UNEARNED_REVENUE);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctClsCd_C1, AJE_INV_ACCT_CLS.RECEIVABLES);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctClsCd_C1, globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue());
            }
            // END   2016/09/26 S.Fujita [QC#13362,MOD]

            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxScrItem61Txt_C1, segTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlDealAmt_C1, segAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invErrMsgTxt_C1, globalMsg.F.no(no).invErrMsgTxt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invldValTxt_C1, globalMsg.F.no(no).invldValTxt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).glDt_C1, globalMsg.F.no(no).glDt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlCratDt_C1, globalMsg.F.no(no).jrnlCratDt_F1.getValue());
            // START 2017/03/17 T.Murai [QC#14205,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistTblNm_C, globalMsg.F.no(no).xxRecHistTblNm_F.getValue());
            // END   2017/03/17 T.Murai [QC#14205,ADD]
            idx++;
        }

        // Credit
        segTxtList.clear();
        isSet = false;
        for(no = 0; no < globalMsg.F.getValidCount(); no++) {
            if(globalMsg.F.no(no).drCrTpCd_F1.getValue().equals(DR_CR_TP_DEBIT)) {
                continue;
            }
            segTxt = globalMsg.F.no(no).xxScrItem61Txt_F1.getValue();
            if(NFCL3000CommonLogic.isExistSegTxt(segTxtList, segTxt)) {
                continue;
            }
            segTxtList.add(segTxt);
            segAmt = BigDecimal.ZERO;
            for(int j = 0; j < globalMsg.F.getValidCount(); j++) {
                if(globalMsg.F.no(j).drCrTpCd_F1.getValue().equals(DR_CR_TP_DEBIT)) {
                    continue;
                }
                if(segTxt.equals(globalMsg.F.no(j).xxScrItem61Txt_F1.getValue())) {
                    segAmt = segAmt.add(getAmount(globalMsg.F.no(j).jrnlDealAmt_F2));
                }
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).drCrTpCd_C1, DR_CR_TP_CREDIT);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctClsCd_C1, globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxScrItem61Txt_C1, segTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlDealAmt_C2, segAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invErrMsgTxt_C1, globalMsg.F.no(no).invErrMsgTxt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invldValTxt_C1, globalMsg.F.no(no).invldValTxt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).glDt_C1, globalMsg.F.no(no).glDt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlCratDt_C1, globalMsg.F.no(no).jrnlCratDt_F1.getValue());
            // START 2017/03/17 T.Murai [QC#14205,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistTblNm_C, globalMsg.F.no(no).xxRecHistTblNm_F.getValue());
            // END   2017/03/17 T.Murai [QC#14205,ADD]
            isSet = true;
            idx++;
        }
        if(!isSet) {
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).drCrTpCd_C1, DR_CR_TP_CREDIT);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctClsCd_C1, globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxScrItem61Txt_C1, segTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlDealAmt_C2, segAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invErrMsgTxt_C1, globalMsg.F.no(no).invErrMsgTxt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invldValTxt_C1, globalMsg.F.no(no).invldValTxt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).glDt_C1, globalMsg.F.no(no).glDt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlCratDt_C1, globalMsg.F.no(no).jrnlCratDt_F1.getValue());
            // START 2017/03/17 T.Murai [QC#14205,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistTblNm_C, globalMsg.F.no(no).xxRecHistTblNm_F.getValue());
            // END   2017/03/17 T.Murai [QC#14205,ADD]
        }
        bizMsg.C.setValidCount(idx);
    }

    /**
     * isExistSegTxt
     * @param segTxtList
     * @param segTxt
     * @return
     */
    public static boolean isExistSegTxt(List<String> segTxtList, String segTxt) {

        for(int i = 0; i < segTxtList.size(); i++) {
            if(segTxt.equals(segTxtList.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * setEditMode
     * @param bizMsg
     * @param globalMsg
     */
    public static void setEditMode(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        if(globalMsg.F.getValidCount() < 1) {
            return;
        }
        NFCL3000CommonLogic.copyFtoC(bizMsg, globalMsg);
    }

    /**
     * copyCtoF
     * @param bizMsg
     * @param globalMsg
     */
    public static void copyCtoF(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        globalMsg.F.clear();
        EZDMsg.copy(bizMsg.C, "C1", globalMsg.F, "F1");
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).jrnlDealAmt_F2, bizMsg.C.no(i).jrnlDealAmt_C2.getValue());
            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).ajePtrnIndTpCd_F2, bizMsg.C.no(i).ajePtrnIndTpCd_C2.getValue());
            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).ajePtrnIndTpNm_F2, bizMsg.C.no(i).ajePtrnIndTpNm_C2.getValue());
            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).ajePtrnActlCd_F2, bizMsg.C.no(i).ajePtrnActlCd_C2.getValue());
            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).ajePtrnActlNm_F2, bizMsg.C.no(i).ajePtrnActlNm_C2.getValue());
            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).ajePtrnIndTpCd_F3, bizMsg.C.no(i).ajePtrnIndTpCd_C3.getValue());
            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).ajePtrnIndTpNm_F3, bizMsg.C.no(i).ajePtrnIndTpNm_C3.getValue());
            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).ajePtrnActlCd_F3, bizMsg.C.no(i).ajePtrnActlCd_C3.getValue());
            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).ajePtrnActlNm_F3, bizMsg.C.no(i).ajePtrnActlNm_C3.getValue());
            // START 2017/03/17 T.Murai [QC#14205,ADD]
            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).xxRecHistCratTs_F, bizMsg.C.no(i).xxRecHistCratTs_C.getValue());
            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).xxRecHistCratByNm_F, bizMsg.C.no(i).xxRecHistCratByNm_C.getValue());
            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).xxRecHistUpdTs_F, bizMsg.C.no(i).xxRecHistUpdTs_C.getValue());
            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).xxRecHistUpdByNm_F, bizMsg.C.no(i).xxRecHistUpdByNm_C.getValue());
            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).xxRecHistTblNm_F, bizMsg.C.no(i).xxRecHistTblNm_C.getValue());
            // END   2017/03/17 T.Murai [QC#14205,ADD]
        }
        bizMsg.setCommitSMsg(true);
    }

    /**
     * copyFtoC
     * @param bizMsg
     * @param globalMsg
     */
    public static void copyFtoC(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        bizMsg.C.clear();
        EZDMsg.copy(globalMsg.F, "F1", bizMsg.C, "C1");
        for(int i = 0; i < globalMsg.F.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).jrnlDealAmt_C2, globalMsg.F.no(i).jrnlDealAmt_F2.getValue());

            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).ajePtrnIndTpCd_C2, globalMsg.F.no(i).ajePtrnIndTpCd_F2.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).ajePtrnIndTpNm_C2, globalMsg.F.no(i).ajePtrnIndTpNm_F2.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).ajePtrnActlCd_C2, globalMsg.F.no(i).ajePtrnActlCd_F2.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).ajePtrnActlNm_C2, globalMsg.F.no(i).ajePtrnActlNm_F2.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).ajePtrnIndTpCd_C3, globalMsg.F.no(i).ajePtrnIndTpCd_F3.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).ajePtrnIndTpNm_C3, globalMsg.F.no(i).ajePtrnIndTpNm_F3.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).ajePtrnActlCd_C3, globalMsg.F.no(i).ajePtrnActlCd_F3.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).ajePtrnActlNm_C3, globalMsg.F.no(i).ajePtrnActlNm_F3.getValue());
            // START 2017/03/17 T.Murai [QC#14205,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistCratTs_C, globalMsg.F.no(i).xxRecHistCratTs_F.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistCratByNm_C, globalMsg.F.no(i).xxRecHistCratByNm_F.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistUpdTs_C, globalMsg.F.no(i).xxRecHistUpdTs_F.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistUpdByNm_C, globalMsg.F.no(i).xxRecHistUpdByNm_F.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxRecHistTblNm_C, globalMsg.F.no(i).xxRecHistTblNm_F.getValue());
            // END   2017/03/17 T.Murai [QC#14205,ADD]
        }
        bizMsg.setCommitSMsg(true);
    }

    /**
     * isManualInvoice
     * @param bizMsg
     * @return
     */
    public static boolean isManualInvoice(NFCL3000CMsg bizMsg) {

        if(ZYPCommonFunc.hasValue(bizMsg.invNum_H1.getValue())) {
            if(!ZYPCommonFunc.hasValue(bizMsg.sysSrcCd_H1.getValue()) || bizMsg.sysSrcCd_H1.getValue().equals(SYS_SRC.S21_ACCOUNTING_AR)) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }


    /**
     * deleteSalesCredit
     * @param bizMsg
     * @param invLine
     */
    public static void deleteSalesCredit(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg, String invLineNum) {
// START 2016/10/13 S.Yoshida [QC#14575,MOD]
//        if(bizMsg.B.getValidCount() < 1) {
//            return;
//        }
//        globalMsg.B.clear();
//        globalMsg.B.setValidCount(0);
//        int cnt = 0;
//        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            if(!invLineNum.equals(bizMsg.B.no(i).invLineNum_B1.getValue())) {
//                EZDMsg.copy(bizMsg.B.no(i),null,globalMsg.B.no(cnt), null);
//                cnt++;
//            }
//        }
//        bizMsg.B.clear();
//        bizMsg.B.setValidCount(cnt);
//        EZDMsg.copy(globalMsg.B, null, bizMsg.B, null);

        deleteMsgAry(bizMsg.B, "invLineNum_B1", invLineNum);
// END   2016/10/13 S.Yoshida [QC#14575,MOD]
    }

    // START 2016/10/07 S.Fujita [QC#10522,DEL]
//    /**
//     * calcLine_Tax_Percent
//     * @param bizMsg
//     */
//    public static void calcLine_Tax_Percent(NFCL3000CMsg bizMsg) {
//
//        if(ZYPCommonFunc.hasValue(bizMsg.xxChkBox_AA)) {
//            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
//                if(bizMsg.A.no(i).invLineDealTaxAmt_A1.getValue().compareTo(BigDecimal.ZERO) != 0) {
//                    BigDecimal taxPct = bizMsg.A.no(i).invLineDealTaxAmt_A1.getValue().divide(getAmount(bizMsg.A.no(i).invLineDealNetAmt_A1));
//                    taxPct = taxPct.multiply(new BigDecimal(100));
//                    taxPct = taxPct.setScale(2, BigDecimal.ROUND_HALF_UP);
//                    bizMsg.A.no(i).taxPct_A1.setValue(taxPct);
//                }
//            }
//        } else {
//            int scale = bizMsg.aftDeclPntDigitNum.getValueInt();
//            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
//                BigDecimal taxPct = bizMsg.A.no(i).taxPct_A1.getValue();
//                BigDecimal taxAmt = bizMsg.A.no(i).invLineDealNetAmt_A1.getValue().multiply(taxPct.divide(new BigDecimal(100)));
//                taxAmt = taxAmt.setScale(scale, BigDecimal.ROUND_HALF_UP);
//                bizMsg.A.no(i).invLineDealTaxAmt_A1.setValue(taxAmt);
//            }
//        }
//    }
    // END   2016/10/07 S.Fujita [QC#10522,DEL]

    // START 2016/10/07 S.Fujita [QC#10522,DEL]
//    /**
//     * calcBOL_Tax_Percent
//     * @param bizMsg
//     */
//    public static void calcBOL_Tax_Percent(NFCL3000CMsg bizMsg) {
//
//        if(ZYPCommonFunc.hasValue(bizMsg.xxChkBox_DA)) {
//            for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
//                if(bizMsg.D.no(i).frtDealTaxAmt_D1.getValue().compareTo(BigDecimal.ZERO) != 0) {
//                    BigDecimal taxPct = bizMsg.D.no(i).frtDealTaxAmt_D1.getValue().divide(getAmount(bizMsg.D.no(i).shipDealFrtAmt_D1));
//                    taxPct = taxPct.multiply(new BigDecimal(100));
//                    taxPct = taxPct.setScale(2, BigDecimal.ROUND_HALF_UP);
//                    bizMsg.D.no(i).frtTaxPct_D1.setValue(taxPct);
//                }
//            }
//        } else {
//            int scale = bizMsg.aftDeclPntDigitNum.getValueInt();
//            for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
//                BigDecimal taxPct = bizMsg.D.no(i).frtTaxPct_D1.getValue();
//                BigDecimal taxAmt = bizMsg.D.no(i).shipDealFrtAmt_D1.getValue().multiply(taxPct.divide(new BigDecimal(100)));
//                taxAmt = taxAmt.setScale(scale, BigDecimal.ROUND_HALF_UP);
//                bizMsg.D.no(i).frtDealTaxAmt_D1.setValue(taxAmt);
//            }
//        }
//    }
    // END   2016/10/07 S.Fujita [QC#10522,DEL]

    /**
     * check_BOL
     * @param bizMsg
     * @return
     */
    public static boolean check_BOL(NFCL3000CMsg bizMsg, boolean msgFlg) {
        boolean isSuccess = true;
        // BOL vs Line
        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
            if(!NFCL3000CommonLogic.check_BOL_Line(bizMsg, bizMsg.D.no(i).invBolLineNum_D1.getValue())) {
                if(msgFlg) {
                    //---- start 2016/05/06 QC#7860
                    //bizMsg.D.no(i).invBolLineNum_D1.setErrorInfo(2, "NFCM0832W", new String[]{"BOL Line#"});
                    bizMsg.D.no(i).invBolLineNum_D1.setErrorInfo(2, NOT_ASSIGNED_WARNING, new String[]{PRM_LINE_ONE});
                    //---- end 2016/05/06
                }
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * check_BOL_Line
     * @param bizMsg
     * @param invBolLineNum
     * @return
     */
    public static boolean check_BOL_Line(NFCL3000CMsg bizMsg, String invBolLineNum) {

        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if(invBolLineNum.equals(bizMsg.A.no(i).invBolLineNum_A1.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * check_SalesCredit
     * @param bizMsg
     * @param msgFlg
     * @return
     */
    public static boolean check_SalesCredit(NFCL3000CMsg bizMsg, boolean msgFlg) {
        boolean isSuccess = true;

        if(bizMsg.B.getValidCount() < 1) {
            return  isSuccess;
        }

        // Line vs Sales Credit
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if(!NFCL3000CommonLogic.check_Line_SalesCredit(bizMsg, bizMsg.A.no(i).invBolLineNum_A1.getValue(), bizMsg.A.no(i).invLineNum_A1.getValue())) {
                if(msgFlg) {
                  //---- start 2016/05/06 QC#7860
                    //bizMsg.A.no(i).invLineNum_A1.setErrorInfo(2, "NFCM0832W", new String[]{"Line#"});
                    bizMsg.A.no(i).invLineNum_A1.setErrorInfo(2, NOT_ASSIGNED_WARNING, new String[]{PRM_SLS_CR_ONE});
                    //---- end 2016/05/06
                }
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * check_Line_SalesCredit
     * @param bizMsg
     * @param invBolLineNum
     * @return
     */
    public static boolean check_Line_SalesCredit(NFCL3000CMsg bizMsg, String invBolLineNum, String invLineNum) {

        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if(invBolLineNum.equals(bizMsg.B.no(i).invBolLineNum_B1.getValue())) {
                if(invLineNum.equals(bizMsg.B.no(i).invLineNum_B1.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check_AccountDist
     * @param bizMsg
     * @param msgFlg
     * @return
     */
    public static boolean check_AccountDist(NFCL3000CMsg bizMsg, boolean msgFlg) {

        boolean isSuccess = true;

        if(bizMsg.C.getValidCount() < 1) {
            return  isSuccess;
        }

        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            // START 2018/07/23 E.Kameishi [QC#27007,ADD]
            if (BigDecimal.ZERO.compareTo(bizMsg.B.no(i).dealSlsCrAmt_B1.getValue()) == 0) {
                continue;
            }
            // END 2018/07/23 E.Kameishi [QC#27007,ADD]
            if(!NFCL3000CommonLogic.check_SC_AccountDist(bizMsg, bizMsg.B.no(i).xxLineNum_B1.getValue())) {
                if(msgFlg) {
                    bizMsg.B.no(i).xxLineNum_B1.setErrorInfo(2, "NFCM0832W", new String[]{"SC#"});
                }
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * check_SC_AccountDist
     * @param bizMsg
     * @param xxLineNum
     * @return
     */
    public static boolean check_SC_AccountDist(NFCL3000CMsg bizMsg, String xxLineNum) {

        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if(xxLineNum.equals(bizMsg.C.no(i).xxLineNum_C1.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * setShipAmt
     * @param bizMsg
     */
    public static void setShipAmt(NFCL3000CMsg bizMsg) {
        
        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
            String invBolLineNum = bizMsg.D.no(i).invBolLineNum_D1.getValue();
            
            BigDecimal shipAmt = NFCL3000CommonLogic.getShipAmt(bizMsg, invBolLineNum);
            
            bizMsg.D.no(i).shipDealNetAmt_D1.setValue(shipAmt);
            bizMsg.D.no(i).shipDealSlsAmt_D1.setValue(shipAmt);
            
        }
    }

    /**
     * getShipAmt
     * @param bizMsg
     */
    public static BigDecimal getShipAmt(NFCL3000CMsg bizMsg, String invBolLineNum) {
        
        BigDecimal shipAmt = BigDecimal.ZERO;
        
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // START 2018/05/22 Y.Matsui [QC#21841,ADD]
            if (isFreightInvoiceLine(bizMsg, bizMsg.A.no(i))) {
                continue;
            }
            // END   2018/05/22 Y.Matsui [QC#21841,ADD]
            if(bizMsg.A.no(i).invBolLineNum_A1.getValue().equals(invBolLineNum)) {
                shipAmt = shipAmt.add(bizMsg.A.no(i).invLineDealNetAmt_A1.getValue());
            }
        }
        return shipAmt;
    }

    /**
     * setLineAmt
     * @param bizMsg
     */
    public static void setLineAmt(NFCL3000CMsg bizMsg) {

        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            String invBolLineNum = bizMsg.B.no(i).invBolLineNum_B1.getValue();
            String invLineNum = bizMsg.B.no(i).invLineNum_B1.getValue();
            String invLineSubNum = bizMsg.B.no(i).invLineSubNum_B1.getValue();

            BigDecimal lineAmt = NFCL3000CommonLogic.getLineAmt(bizMsg, invBolLineNum, invLineNum, invLineSubNum);

            bizMsg.B.no(i).xxTotAmt_B1.setValue(lineAmt);
        }
    }

    // START 2016/06/03 S.Fujita [QC#9157,ADD]
    /**
     * getLineAmt
     * @param bizMsg
     * @param invBolLineNum
     * @param invLineNum
     * @param invLineSubNum
     */
    public static BigDecimal getLineAmt(NFCL3000CMsg bizMsg, String invBolLineNum, String invLineNum, String invLineSubNum) {
 
        BigDecimal lineAmt = BigDecimal.ZERO;

        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if(bizMsg.A.no(i).invBolLineNum_A1.getValue().equals(invBolLineNum)) {
                if(bizMsg.A.no(i).invLineNum_A1.getValue().equals(invLineNum)) {
                    if(bizMsg.A.no(i).invLineSubNum_A1.getValue().equals(invLineSubNum)) {
                        lineAmt = bizMsg.A.no(i).invLineDealNetAmt_A1.getValue();
                        break;
                    }
                }
            }
        }
        return lineAmt;
    }
    // END   2016/06/03 S.Fujita [QC#9157,ADD]

    // START 2016/07/19 S.Yoshida [QC#12006,MOD]
//    //---- start add 2016/05/04
//    public static BigDecimal getSignByInvtp(NFCL3000CMsg bizMsg) {
//        if (INV_TP.CREDIT_MEMO.equals(bizMsg.invTpCd_H1.getValue())) {
//            return BigDecimal.ONE.negate();
//        }
//        return BigDecimal.ONE;
//    }
//    //---- end 2016/05/04

    /**
     * getSignByInvtp
     * @param invTpCd
     * @param amt
     */
    public static BigDecimal getSignByInvtp(String invTpCd, EZDCBigDecimalItem amt) {

        if (!ZYPCommonFunc.hasValue(amt)) {
            return BigDecimal.ZERO;
        }

        BigDecimal sign = BigDecimal.ONE;
        if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
            sign = sign.negate();
        }

        return amt.getValue().multiply(sign);
    }
    // END   2016/07/19 S.Yoshida [QC#12006,MOD]

    // START 2016/05/17 S.Fujita [QC#8373,ADD]
    /**
     * setAjeInvAcctDistPct
     * @param bizMsg
     */
    public static void setAjeInvAcctDistPct(NFCL3000CMsg bizMsg) {

        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
            BigDecimal val = NFCL3000CommonLogic.calcAjeInvAcctDistPct(bizMsg, i);
            bizMsg.C.no(i).ajeInvAcctDistPct_C1.setValue(val);
        }
    }

    /**
     * calcAjeInvAcctDistPct
     * @param bizMsg
     * @param idx
     */
    public static BigDecimal calcAjeInvAcctDistPct(NFCL3000CMsg bizMsg, int idx) {

        BigDecimal invLineSplPct = BigDecimal.ZERO;
        BigDecimal slsRepCrPct = BigDecimal.ZERO;
        String slsCrLineNum = bizMsg.C.no(idx).xxLineNum_C1.getValue();

        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if(bizMsg.B.no(i).xxLineNum_B1.getValue().equals(slsCrLineNum)) {
                invLineSplPct = bizMsg.B.no(i).invLineSplPct_B2.getValue();
                slsRepCrPct = bizMsg.B.no(i).slsRepCrPct_B2.getValue();
                break;
            }
        }

        if (!ZYPCommonFunc.hasValue(invLineSplPct)) {
            invLineSplPct = new BigDecimal(100);
        }

        invLineSplPct = invLineSplPct.divide(new BigDecimal(100));
        slsRepCrPct = slsRepCrPct.divide(new BigDecimal(100));
        BigDecimal ajeInvAcctDistPct = invLineSplPct.multiply(slsRepCrPct);
        // START 2018/05/22 Y.Matsui [QC#21841,MOD]
        // START 2016/09/05 S.Fujita [QC#10156,MOD]
        if(AJE_INV_ACCT_CLS.REVENUE.equals(bizMsg.C.no(idx).ajeInvAcctClsCd_C1.getValue()) || AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(bizMsg.C.no(idx).ajeInvAcctClsCd_C1.getValue()) || AJE_INV_ACCT_CLS.FREIGHT.equals(bizMsg.C.no(idx).ajeInvAcctClsCd_C1.getValue())){
            ajeInvAcctDistPct = ajeInvAcctDistPct.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        } else {
            ajeInvAcctDistPct = new BigDecimal(100);
        }
        // END   2016/09/05 S.Fujita [QC#10156,MOD]
        // END   2018/05/22 Y.Matsui [QC#21841,MOD]

        return ajeInvAcctDistPct;
    }

    /**
     * getAjePtrn
     * @param resAjePtrnList
     * @param ajeInvAcctClsCd
     * @param globalMsg
     * @param bizMsg
     * @param idx
     * @return
     */
    public static Map<String, String> getAjePtrn(List<Map> resAjePtrnList, String ajeInvAcctClsCd, NFCL3000SMsg globalMsg, NFCL3000CMsg bizMsg, int idx) {

        // START 2016/10/05 S.Fujita [QC#15013,MOD]
//        for(Map<String, String>resAjePtrn: resAjePtrnList) {
//            if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_INTFC_COL_TXT_02)) && resAjePtrn.get(AJE_INTFC_COL_TXT_02).equals(AJE_ACCTG_RULE_CD)) {
//                for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
//                    if(bizMsg.B.no(i).dsInvSlsCrPk_B1.getValue().equals(globalMsg.F.no(idx).dsInvSlsCrPk_F1.getValue())) {
//                        if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_PTRN_ACTL_CD_02))) {
//                            if (bizMsg.B.no(i).dfrdRevFlg_B1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//                                if (resAjePtrn.get(AJE_PTRN_ACTL_CD_02).equals(DFRD_ACCTG_RULE.DEFERRED)) {
//                                    String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
//                                    if (valAjePtrn != null) {
//                                        if(valAjePtrn.equals(ajeInvAcctClsCd)) {
//                                            return resAjePtrn;
//                                        }
//                                    }
//                                }
//                            } else {
//                                if (resAjePtrn.get(AJE_PTRN_ACTL_CD_02).equals(DFRD_ACCTG_RULE.IMMEDIATE)) {
//                                    String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
//                                    if (valAjePtrn != null) {
//                                        if(valAjePtrn.equals(ajeInvAcctClsCd)) {
//                                            return resAjePtrn;
//                                        }
//                                    }
//                                }
//                            }
//                        } else {
//                            String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
//                            if (valAjePtrn != null) {
//                                if(valAjePtrn.equals(ajeInvAcctClsCd)) {
//                                    return resAjePtrn;
//                                }
//                            }
//                        }
//                    }
//                }
//            } else {
//                return resAjePtrn;
//            }
//        }

        for(Map<String, String>resAjePtrn: resAjePtrnList) {
            if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_INTFC_COL_TXT_01)) && resAjePtrn.get(AJE_INTFC_COL_TXT_01).equals(AJE_ACCTG_RULE_CD)) {
                for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
                    if(bizMsg.B.no(i).dsInvSlsCrPk_B1.getValue().equals(globalMsg.F.no(idx).dsInvSlsCrPk_F1.getValue())) {
                        if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_PTRN_ACTL_CD_01))) {
                            if (bizMsg.B.no(i).dfrdRevFlg_B1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                                if (resAjePtrn.get(AJE_PTRN_ACTL_CD_01).equals(DFRD_ACCTG_RULE.DEFERRED)) {
                                    String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                                    if (valAjePtrn != null) {
                                        if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                            return resAjePtrn;
                                        }
                                    }
                                }
                            } else {
                                if (resAjePtrn.get(AJE_PTRN_ACTL_CD_01).equals(DFRD_ACCTG_RULE.IMMEDIATE)) {
                                    String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                                    if (valAjePtrn != null) {
                                        if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                            return resAjePtrn;
                                        }
                                    }
                                }
                            }
                        } else {
                            String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                            if (valAjePtrn != null) {
                                if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                    return resAjePtrn;
                                }
                            }
                        }
                    }
                }
            } else if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_INTFC_COL_TXT_02)) && resAjePtrn.get(AJE_INTFC_COL_TXT_02).equals(AJE_ACCTG_RULE_CD)) {
                for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
                    if(bizMsg.B.no(i).dsInvSlsCrPk_B1.getValue().equals(globalMsg.F.no(idx).dsInvSlsCrPk_F1.getValue())) {
                        if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_PTRN_ACTL_CD_02))) {
                            if (bizMsg.B.no(i).dfrdRevFlg_B1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                                if (resAjePtrn.get(AJE_PTRN_ACTL_CD_02).equals(DFRD_ACCTG_RULE.DEFERRED)) {
                                    String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                                    if (valAjePtrn != null) {
                                        if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                            return resAjePtrn;
                                        }
                                    }
                                }
                            } else {
                                if (resAjePtrn.get(AJE_PTRN_ACTL_CD_02).equals(DFRD_ACCTG_RULE.IMMEDIATE)) {
                                    String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                                    if (valAjePtrn != null) {
                                        if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                            return resAjePtrn;
                                        }
                                    }
                                }
                            }
                        } else {
                            String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                            if (valAjePtrn != null) {
                                if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                    return resAjePtrn;
                                }
                            }
                        }
                    }
                }
            } else if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_INTFC_COL_TXT_03)) && resAjePtrn.get(AJE_INTFC_COL_TXT_03).equals(AJE_ACCTG_RULE_CD)) {
                for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
                    if(bizMsg.B.no(i).dsInvSlsCrPk_B1.getValue().equals(globalMsg.F.no(idx).dsInvSlsCrPk_F1.getValue())) {
                        if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_PTRN_ACTL_CD_03))) {
                            if (bizMsg.B.no(i).dfrdRevFlg_B1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                                if (resAjePtrn.get(AJE_PTRN_ACTL_CD_03).equals(DFRD_ACCTG_RULE.DEFERRED)) {
                                    String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                                    if (valAjePtrn != null) {
                                        if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                            return resAjePtrn;
                                        }
                                    }
                                }
                            } else {
                                if (resAjePtrn.get(AJE_PTRN_ACTL_CD_03).equals(DFRD_ACCTG_RULE.IMMEDIATE)) {
                                    String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                                    if (valAjePtrn != null) {
                                        if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                            return resAjePtrn;
                                        }
                                    }
                                }
                            }
                        } else {
                            String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                            if (valAjePtrn != null) {
                                if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                    return resAjePtrn;
                                }
                            }
                        }
                    }
                }
            } else {
                String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                if (valAjePtrn != null) {
                    if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                        return resAjePtrn;
                    }
                }
            }
        }
        // END   2016/10/05 S.Fujita [QC#15013,MOD]
        return null;
    }

    /**
     * getDfrdAndImeAjePtrn
     * @param resAjePtrnList
     * @param ajeInvAcctClsCd
     * @param bizMsg
     * @return
     */
    public static Map<String, String> getDfrdAndImeAjePtrn(List<Map> resAjePtrnList, String ajeInvAcctClsCd, NFCL3000CMsg bizMsg) {

        // START 2016/10/05 S.Fujita [QC#15013,MOD]
//        for(Map<String, String>resAjePtrn: resAjePtrnList) {
//            if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_INTFC_COL_TXT_02)) && resAjePtrn.get(AJE_INTFC_COL_TXT_02).equals(AJE_ACCTG_RULE_CD)) {
//                if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_PTRN_ACTL_CD_02))) {
//                    if (resAjePtrn.get(AJE_PTRN_ACTL_CD_02).equals(DFRD_ACCTG_RULE.DEFERRED)) {
//                        String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
//                        if (valAjePtrn != null) {
//                            if(valAjePtrn.equals(ajeInvAcctClsCd)) {
//                                return resAjePtrn;
//                            }
//                        }
//                    } else if (resAjePtrn.get(AJE_PTRN_ACTL_CD_02).equals(DFRD_ACCTG_RULE.IMMEDIATE)) {
//                        String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
//                        if (valAjePtrn != null) {
//                            if(valAjePtrn.equals(ajeInvAcctClsCd)) {
//                                return resAjePtrn;
//                            }
//                        }
//                    }
//                }
//            }
//        }
        for(Map<String, String>resAjePtrn: resAjePtrnList) {
            if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_INTFC_COL_TXT_01)) && resAjePtrn.get(AJE_INTFC_COL_TXT_01).equals(AJE_ACCTG_RULE_CD)) {
                if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_PTRN_ACTL_CD_01))) {
                    if (resAjePtrn.get(AJE_PTRN_ACTL_CD_01).equals(DFRD_ACCTG_RULE.DEFERRED)) {
                        String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                        if (valAjePtrn != null) {
                            if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                return resAjePtrn;
                            }
                        }
                    } else if (resAjePtrn.get(AJE_PTRN_ACTL_CD_01).equals(DFRD_ACCTG_RULE.IMMEDIATE)) {
                        String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                        if (valAjePtrn != null) {
                            if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                return resAjePtrn;
                            }
                        }
                    }
                }
            } else if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_INTFC_COL_TXT_02)) && resAjePtrn.get(AJE_INTFC_COL_TXT_02).equals(AJE_ACCTG_RULE_CD)) {
                if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_PTRN_ACTL_CD_02))) {
                    if (resAjePtrn.get(AJE_PTRN_ACTL_CD_02).equals(DFRD_ACCTG_RULE.DEFERRED)) {
                        String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                        if (valAjePtrn != null) {
                            if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                return resAjePtrn;
                            }
                        }
                    } else if (resAjePtrn.get(AJE_PTRN_ACTL_CD_02).equals(DFRD_ACCTG_RULE.IMMEDIATE)) {
                        String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                        if (valAjePtrn != null) {
                            if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                return resAjePtrn;
                            }
                        }
                    }
                }
            } else if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_INTFC_COL_TXT_03)) && resAjePtrn.get(AJE_INTFC_COL_TXT_03).equals(AJE_ACCTG_RULE_CD)) {
                if (ZYPCommonFunc.hasValue(resAjePtrn.get(AJE_PTRN_ACTL_CD_03))) {
                    if (resAjePtrn.get(AJE_PTRN_ACTL_CD_03).equals(DFRD_ACCTG_RULE.DEFERRED)) {
                        String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                        if (valAjePtrn != null) {
                            if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                return resAjePtrn;
                            }
                        }
                    } else if (resAjePtrn.get(AJE_PTRN_ACTL_CD_03).equals(DFRD_ACCTG_RULE.IMMEDIATE)) {
                        String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                        if (valAjePtrn != null) {
                            if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                                return resAjePtrn;
                            }
                        }
                    }
                }
            } else {
                String valAjePtrn = resAjePtrn.get(AJE_LINE_IND_TP_CD);
                if (valAjePtrn != null) {
                    if(valAjePtrn.equals(ajeInvAcctClsCd)) {
                        return resAjePtrn;
                    }
                }
            }
        }
        // END   2016/10/05 S.Fujita [QC#15013,MOD]
        return null;
    }
    // END 2016/05/17 S.Fujita [QC#8373,ADD]

//START 2016/09/21 S.Yoshida [QC#11049,DEL]
//    // START 2016/05/18 S.Fujita [QC#7780,ADD]
//    /**
//     * checkItems_AcctDist
//     * @param bizMsg
//     * @return
//     */
//    public static boolean checkItems_AcctDist(NFCL3000CMsg bizMsg) {
//        boolean isNotError = true;
//
//        String slsProcDt = ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue());
//        String slsProcYM = getYearMonth(slsProcDt);
//        String preSlsProcYM = getYearMonth(getBeforeMonth(slsProcDt, "yyyyMMdd"));
//
//        String acctDtYM = getYearMonth(bizMsg.acctDt.getValue());
//        String invAcctDtYM = getYearMonth(bizMsg.acctDt_H1.getValue());
//
//        if (acctDtYM.equals(slsProcYM)) {
//            if (!slsProcYM.equals(invAcctDtYM)) {
//                if (!checkItems_AcctDistLine(bizMsg)) {
//                    isNotError = false;
//                }
//            } else {
//                // do nothing.
//            }
//        } else {
//            if (!slsProcYM.equals(invAcctDtYM) && !preSlsProcYM.equals(invAcctDtYM)) {
//                if (!checkItems_AcctDistLine(bizMsg)) {
//                    isNotError = false;
//                }
//            } else {
//                // do nothing.
//            }
//        }
//        return isNotError;
//    }
//
//    /**
//     * checkItems_AcctDistLine
//     * @param bizMsg
//     * @return
//     */
//    public static boolean checkItems_AcctDistLine(NFCL3000CMsg bizMsg) {
//        boolean isNotError = true;
//
//        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
//            if(ZYPCommonFunc.hasValue(bizMsg.C.no(i).ajeInvAcctDistPk_C1.getValue())) {
//
//                AJE_INV_ACCT_DISTTMsg inMsg = new AJE_INV_ACCT_DISTTMsg();
//                inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
//                inMsg.ajeInvAcctDistPk.setValue(bizMsg.C.no(i).ajeInvAcctDistPk_C1.getValue());
//                AJE_INV_ACCT_DISTTMsg updMsg = (AJE_INV_ACCT_DISTTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
//                    bizMsg.setMessageInfo("NFCM0864E", new String[]{"AJE_INV_ACCT_DIST", null});
//                    isNotError = false;
//                    break;
//                }
//
//                if (!updMsg.dsInvSlsCrPk.getValue().equals(bizMsg.C.no(i).dsInvSlsCrPk_C1.getValue())) {
//                    isNotError = false;
//                    break;
//                }
//                if (!updMsg.ajeInvAcctClsCd.getValue().equals(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue())) {
//                    isNotError = false;
//                    break;
//                }
//                if (!updMsg.ajeCoaCmpyCd.getValue().equals(bizMsg.C.no(i).ajeCoaCmpyCd_C1.getValue())) {
//                    isNotError = false;
//                    break;
//                }
//                if (!updMsg.ajeCoaBrCd.getValue().equals(bizMsg.C.no(i).ajeCoaBrCd_C1.getValue())) {
//                    isNotError = false;
//                    break;
//                }
//                if (!updMsg.ajeCoaCcCd.getValue().equals(bizMsg.C.no(i).ajeCoaCcCd_C1.getValue())) {
//                    isNotError = false;
//                    break;
//                }
//                if (!updMsg.ajeCoaAcctCd.getValue().equals(bizMsg.C.no(i).ajeCoaAcctCd_C1.getValue())) {
//                    isNotError = false;
//                    break;
//                }
//                if (!updMsg.ajeCoaProdCd.getValue().equals(bizMsg.C.no(i).ajeCoaProdCd_C1.getValue())) {
//                    isNotError = false;
//                    break;
//                }
//                if (!updMsg.ajeCoaChCd.getValue().equals(bizMsg.C.no(i).ajeCoaChCd_C1.getValue())) {
//                    isNotError = false;
//                    break;
//                }
//                if (!updMsg.ajeCoaAfflCd.getValue().equals(bizMsg.C.no(i).ajeCoaAfflCd_C1.getValue())) {
//                    isNotError = false;
//                    break;
//                }
//                if (!updMsg.ajeCoaProjCd.getValue().equals(bizMsg.C.no(i).ajeCoaProjCd_C1.getValue())) {
//                    isNotError = false;
//                    break;
//                }
//                if (!updMsg.ajeCoaExtnCd.getValue().equals(bizMsg.C.no(i).ajeCoaExtnCd_C1.getValue())) {
//                    isNotError = false;
//                    break;
//                }
//                if(DR_CR_TP_DEBIT.equals(bizMsg.C.no(i).drCrTpCd_C1.getValue())) {
//                    if (updMsg.jrnlDealAmt.getValue().compareTo(bizMsg.C.no(i).jrnlDealAmt_C1.getValue()) != 0) {
//                        isNotError = false;
//                        break;
//                    }
//                } else {
//                    if (updMsg.jrnlDealAmt.getValue().compareTo(bizMsg.C.no(i).jrnlDealAmt_C2.getValue()) != 0) {
//                        isNotError = false;
//                        break;
//                    }
//                }
//            } else {
//                isNotError = false;
//                break;
//            }
//        }
//        if (!isNotError) {
//            bizMsg.setMessageInfo("NFCM0844E");
//        }
//        return isNotError;
//    }
//
//    /**
//     * <dd>The method explanation: The business peculiarity
//     * processing is executed.
//     * @param param String
//     * @return String
//     */
//    public static String getYearMonth(String param) {
//
//        // START 2016/08/03 S.Fujita [QC#12864,MOD]
//        String rtnVal = "";
//        if(ZYPCommonFunc.hasValue(param)) {
//            StringBuilder yearMonth = new StringBuilder();
//            String yymmdd = ZYPDateUtil.convertFormat(param, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_YYMMDD, null);
//            String[] ymd = ZYPDateUtil.getSplitDay(yymmdd);
//            yearMonth.append(ymd[YEAR_INDEX]);
//            yearMonth.append(ymd[MONTH_INDEX]);
//            return yearMonth.toString();
//        }
//        return rtnVal;
//        // END   2016/08/03 S.Fujita [QC#12864,MOD]
//    }
//
//    /**
//     * <dd>The method explanation: The business peculiarity
//     * processing is executed.
//     * @param param String
//     * @param format String
//     * @return String
//     */
//    public static String getBeforeMonth(String param, String format) {
//
//        String retVal = "";
//
//        SimpleDateFormat ft = new SimpleDateFormat(format);
//        Calendar cal = Calendar.getInstance();
//        try {
//
//            cal.setTime(ft.parse(param));
//            cal.add(Calendar.MONTH, -1);
//            retVal = ft.format(cal.getTime());
//
//        } catch (ParseException pe) {
//            EZDDebugOutput.println(1, "getBeforeMonth() param:" + param + ", format:" + format, new NFCL3000CommonLogic());
//        }
//        return retVal;
//    }
//    // END 2016/05/18 S.Fujita [QC#7780,ADD]
//END   2016/09/21 S.Yoshida [QC#11049,DEL]

    // START 2016/05/24 S.Fujita [QC#8522,ADD]
    /**
     * getDfrdAcctgrule
     * @param bizMsg
     * @param dfrdAcctgRuleCd String
     * @return outMsg DFRD_ACCTG_RULETMsg
     */
    public static DFRD_ACCTG_RULETMsg getDfrdAcctgrule(NFCL3000CMsg bizMsg, String dfrdAcctgRuleCd) {

        DFRD_ACCTG_RULETMsg inMsg = new DFRD_ACCTG_RULETMsg();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.dfrdAcctgRuleCd.setValue(dfrdAcctgRuleCd);
        DFRD_ACCTG_RULETMsg outMsg = (DFRD_ACCTG_RULETMsg) EZDTBLAccessor.findByKey(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0864E", new String[]{"DFRD_ACCTG_RULE", dfrdAcctgRuleCd});
            return null;
        }
        return outMsg;
    }

    /**
     * updateSalseCredit
     * @param bizMsg
     * @return
     */
    public static boolean updateSalseCredit(NFCL3000CMsg bizMsg) {

        // START 2016/05/24 S.Fujita [QC#8522,MOD]
//        if(!NFCL3000CommonLogic.searchInvSlsCr(bizMsg)) {
        if(!NFCL3000CommonLogic.searchInvSlsCrPk(bizMsg)) {
        // END   2016/05/24 S.Fujita [QC#8522,MOD]
            return false;
        }

        // START 2016/07/20 S.Yoshida [QC#12149,ADD]
        String invTpCd = bizMsg.invTpCd_H1.getValue();
        // END   2016/07/20 S.Yoshida [QC#12149,ADD]

        boolean isUpdate = false;
        int scale = bizMsg.aftDeclPntDigitNum.getValueInt();
        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {

            DS_INV_SLS_CRTMsg inMsg = new DS_INV_SLS_CRTMsg();
            inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            inMsg.dsInvSlsCrPk.setValue(bizMsg.G.no(i).dsInvSlsCrPk_G1.getValue());
            DS_INV_SLS_CRTMsg updMsg = (DS_INV_SLS_CRTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0864E", new String[]{"DS_INV_SLS_CR", null});
                return false;
            }
            if (bizMsg.B.no(i).dfrdRevFlg_B1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                updMsg.revRecogCnt.setValue(BigDecimal.ZERO);
                updMsg.durnStartDt.setValue(bizMsg.B.no(i).durnStartDt_B1.getValue());

                if (bizMsg.B.no(i).durnStartDt_B1.getValue().compareTo(bizMsg.procDt.getValue()) < 0) {
                    updMsg.nextRevRecogDt.setValue(bizMsg.procDt.getValue());
                } else {
                    updMsg.nextRevRecogDt.setValue(bizMsg.B.no(i).durnStartDt_B1.getValue());
                }

                updMsg.revRecogProcStsCd.setValue("0");

                // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//                // START 2016/07/11 S.Fujita [QC#10160,MOD]
//                updMsg.dealOrigDfrdAmt.setValue(bizMsg.B.no(i).dealSlsCrAmt_B1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//                updMsg.funcOrigDfrdAmt.setValue(bizMsg.B.no(i).dealSlsCrAmt_B1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//                updMsg.dealDfrdBalAmt.setValue(bizMsg.B.no(i).dealSlsCrAmt_B1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//                updMsg.funcDfrdBalAmt.setValue(bizMsg.B.no(i).dealSlsCrAmt_B1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//                // END   2016/07/11 S.Fujita [QC#10160,MOD]
                updMsg.dealOrigDfrdAmt.setValue(NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.B.no(i).dealSlsCrAmt_B1));
                updMsg.funcOrigDfrdAmt.setValue(NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.B.no(i).dealSlsCrAmt_B1));
                updMsg.dealDfrdBalAmt.setValue(NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.B.no(i).dealSlsCrAmt_B1));
                updMsg.funcDfrdBalAmt.setValue(NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.B.no(i).dealSlsCrAmt_B1));
                // END   2016/07/20 S.Yoshida [QC#12149,MOD]

                BigDecimal schdRevAmt = updMsg.dealDfrdBalAmt.getValue();
                if (!ZYPCommonFunc.hasValue(bizMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.getValue()) || BigDecimal.ZERO.equals(bizMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dfrdAcctgRuleDurnAot_B1, BigDecimal.ZERO);
                    updMsg.dealSchdRevAmt.setValue(BigDecimal.ZERO);
                    updMsg.funcSchdRevAmt.setValue(BigDecimal.ZERO);
                } else {
                    schdRevAmt = schdRevAmt.divide(getAmount(bizMsg.B.no(i).dfrdAcctgRuleDurnAot_B1), scale, BigDecimal.ROUND_HALF_UP);
                    updMsg.dealSchdRevAmt.setValue(schdRevAmt);
                    updMsg.funcSchdRevAmt.setValue(schdRevAmt);
                }

                // START 2016/06/20 S.Fujita [QC#9454,ADD]
                S21_ORGTMsg s21OrgTmsg = new S21_ORGTMsg();
                ZYPEZDItemValueSetter.setValue(s21OrgTmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(s21OrgTmsg.tocCd,      bizMsg.B.no(i).slsRepTocCd_B1.getValue());
                s21OrgTmsg = (S21_ORGTMsg) S21CacheTBLAccessor.findByKey(s21OrgTmsg);
                if (!S21CacheTBLAccessor.RTNCD_NORMAL.equals(s21OrgTmsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0864E", new String[]{"S21_ORG", bizMsg.B.no(i).slsRepTocCd_B1.getValue()});
                    return false;
                }
                if(ZYPCommonFunc.hasValue(s21OrgTmsg.coaBrCd.getValue())) {
                    updMsg.slsRepBrCd.setValue(s21OrgTmsg.coaBrCd.getValue());
                }
                isUpdate = true;
                // END   2016/06/20 S.Fujita [QC#9454,ADD]
            }
            if(ZYPCommonFunc.hasValue(bizMsg.B.no(i).manInvCratCmntTxt_B1.getValue())) {
                updMsg.manInvCratCmntTxt.setValue(bizMsg.B.no(i).manInvCratCmntTxt_B1.getValue());
                isUpdate = true;
            }

            if(isUpdate) {
                EZDTBLAccessor.update(updMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0862E", new String[]{"DS_INV_SLS_CR"});
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * searchInvSlsCrPk
     * @param bizMsg
     * @return
     */
    public static boolean searchInvSlsCrPk(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());

        S21SsmEZDResult ssmResult = NFCL3000Query.getInstance().searchInvSlsCrPk(bizMsg, ssmParam);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt < 1) {
                return true;
            }
            else if (queryResCnt > bizMsg.B.length()) {
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = bizMsg.B.length();
            } else {
                bizMsg.setMessageInfo("NZZM0002I");
            }
            bizMsg.B.setValidCount(queryResCnt);
        } else {
            bizMsg.setMessageInfo("NZZM0000E");
            bizMsg.B.setValidCount(0);
            return false;
        }
        return true;
    }
    // END   2016/05/24 S.Fujita [QC#8522,ADD]

    // START 2016/06/03 S.Fujita [QC#9157,ADD]
    /**
     * checkLine_Line_Idx
     * @param bizMsg
     * @param idx
     * @return
     */
    public static boolean checkLine_Line_Idx(NFCL3000CMsg bizMsg, int idx) {
        boolean isSuccess = false;
        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
            if(bizMsg.A.no(idx).invBolLineNum_A1.getValue().equals(bizMsg.D.no(i).invBolLineNum_D1.getValue())) {
                isSuccess = true;
                break;
            }
        }
        if(!isSuccess) {
            bizMsg.A.no(idx).invBolLineNum_A1.setErrorInfo(1, NOT_EXISTS_ERROR);
        }

        return isSuccess;
    }

    /**
     * checkLine_SlsCr_Idx
     * @param bizMsg
     * @param idx
     * @return
     */
    public static boolean checkLine_SlsCr_Idx(NFCL3000CMsg bizMsg, int idx) {
        boolean isSuccess = false;
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if(bizMsg.B.no(idx).invBolLineNum_B1.getValue().equals(bizMsg.A.no(i).invBolLineNum_A1.getValue())) {
                if(bizMsg.B.no(idx).invLineNum_B1.getValue().equals(bizMsg.A.no(i).invLineNum_A1.getValue())) {
                    isSuccess = true;
                    break;
                }
            }
        }
        if(!isSuccess) {
            bizMsg.B.no(idx).invLineNum_B1.setErrorInfo(1, NOT_EXISTS_ERROR);
        }

        return isSuccess;
    }

    /**
     * setSlsCr
     * @param bizMsg
     * @param idx
     * @return
     */
    public static boolean setSlsCr(NFCL3000CMsg bizMsg, int idx) {

        int no = 0;
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if(bizMsg.B.no(idx).invBolLineNum_B1.getValue().equals(bizMsg.A.no(i).invBolLineNum_A1.getValue())) {
                if(bizMsg.B.no(idx).invLineNum_B1.getValue().equals(bizMsg.A.no(i).invLineNum_A1.getValue())) {
                    no = i;
                    break;
                }
            }
        }

        NFCL3000CommonLogic.initialSetup_SlsCrTab_Idx(bizMsg, no, idx);

        NFCL3000CommonLogic.setSalesCreditLineNum_B(bizMsg);

        return true;
    }

    /**
     * checkLine_AccountDist_Idx
     * @param bizMsg
     * @param idx
     * @return
     */
    public static boolean checkLine_AccountDist_Idx(NFCL3000_BCMsgArray bBizMsgAry, NFCL3000_CCMsg cBizMsg) {
        boolean isSuccess = false;

        for (int i = 0; i < bBizMsgAry.getValidCount(); i++) {
            if (cBizMsg.xxLineNum_C1.getValue().equals(bBizMsgAry.no(i).xxLineNum_B1.getValue())) {
                isSuccess = true;
                break;
            }
        }

        if (!isSuccess) {
            cBizMsg.xxLineNum_C1.setErrorInfo(1, NOT_EXISTS_ERROR);
        }
        return isSuccess;
    }

    /**
     * setAccountDist
     * @param bizMsg
     * @param idx
     * @return
     */
    public static boolean setAccountDist(NFCL3000_BCMsgArray bBizMsgAry, NFCL3000_CCMsg cBizMsg, String procDt) {

        NFCL3000_BCMsg bBizMsg = null;
        for (int i = 0; i < bBizMsgAry.getValidCount(); i++) {
            if (cBizMsg.xxLineNum_C1.getValue().equals(bBizMsgAry.no(i).xxLineNum_B1.getValue())) {
                bBizMsg = bBizMsgAry.no(i);
                break;
            }
        }

        NFCL3000CommonLogic.initialSetup_AcctLTab_Idx(bBizMsg, cBizMsg, bBizMsg.dealSlsCrAmt_B1.getValue(), procDt);
        return true;
    }

    /**
     * initialSetup_AcctLTab_Idx
     * @param bizMsg
     * @param bIdx
     * @param cIdx
     * @param jrnlDealAmt
     */
    public static void initialSetup_AcctLTab_Idx(NFCL3000_BCMsg bBizMsg, NFCL3000_CCMsg cBizMsg, BigDecimal jrnlDealAmt, String procDt) {

        cBizMsg.clear();
        ZYPEZDItemValueSetter.setValue(cBizMsg.drCrTpCd_C1, DR_CR_TP_CREDIT);
        ZYPEZDItemValueSetter.setValue(cBizMsg.xxLineNum_C1, bBizMsg.xxLineNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(cBizMsg.invBolLineNum_C1, bBizMsg.invBolLineNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(cBizMsg.invLineNum_C1, bBizMsg.invLineNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(cBizMsg.invLineSubNum_C1, bBizMsg.invLineSubNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(cBizMsg.invLineSubTrxNum_C1, bBizMsg.invTrxLineSubNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(cBizMsg.glDt_C1, procDt);
        ZYPEZDItemValueSetter.setValue(cBizMsg.jrnlDealAmt_C2, jrnlDealAmt);
        ZYPEZDItemValueSetter.setValue(cBizMsg.dsInvSlsCrPk_C1, bBizMsg.dsInvSlsCrPk_B1);

        ZYPEZDItemValueSetter.setValue(cBizMsg.trxCd_C1, bBizMsg.trxCd_B1);
        ZYPEZDItemValueSetter.setValue(cBizMsg.trxRsnCd_C1, bBizMsg.trxRsnCd_B1);
        // START 2016/07/20 S.Fujita [QC#10148,ADD]
        ZYPEZDItemValueSetter.setValue(cBizMsg.slsRepTocCd_C1, bBizMsg.slsRepTocCd_B1);
        ZYPEZDItemValueSetter.setValue(cBizMsg.mdseCd_C1, bBizMsg.mdseCd_B1);
        // END   2016/07/20 S.Fujita [QC#10148,ADD]

        // START 2016/07/20 S.Fujita [QC#10148,DEL]
//        bizMsg.C.no(cIdx+1).clear();
//        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cIdx+1).drCrTpCd_C1, DR_CR_TP_CREDIT);
//        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cIdx+1).xxLineNum_C1, .xxLineNum_B1.getValue());
//        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cIdx+1).invBolLineNum_C1, .invBolLineNum_B1.getValue());
//        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cIdx+1).invLineNum_C1, .invLineNum_B1.getValue());
//        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cIdx+1).invLineSubNum_C1, .invLineSubNum_B1.getValue());
//        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cIdx+1).invLineSubTrxNum_C1, .invTrxLineSubNum_B1.getValue());
//        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cIdx+1).glDt_C1, bizMsg.procDt.getValue());
//        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cIdx+1).jrnlDealAmt_C2, .dealSlsCrAmt_B1.getValue());
//        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cIdx+1).dsInvSlsCrPk_C1, .dsInvSlsCrPk_B1);
//
//        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cIdx+1).trxCd_C1, .trxCd_B1);
//        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cIdx+1).trxRsnCd_C1, .trxRsnCd_B1);
        // END   2016/07/20 S.Fujita [QC#10148,DEL]
    }

    /**
     *  setNumStr
     * @param val
     * @param len
     * @return numStr
     */
    public static String setNumStr(String val, int len) {
        String numStr = null;

        val = val.trim();

        if (!NFCL3000CommonLogic.isNumber(val)) {
            return null;
        }

        int no = Integer.parseInt(val);
        if (no < 1) {
            return null;
        }
        numStr = String.valueOf(no);
        numStr = ZYPCommonFunc.leftPad(numStr, len, "0");
        return numStr;
    }

    /**
     *  isNumber
     * @param val
     * @return
     */
    public static boolean isNumber(String val) {
        String regex = "\\A[-]?[0-9]+\\z";
        Pattern p = Pattern.compile(regex);
        Matcher m1 = p.matcher(val);
        return m1.find();
    }

    /**
     *  check_AccountingTAB
     * @param bizMsg
     * @return
     */
    public static boolean check_AccountingTAB(NFCL3000CMsg bizMsg){
        boolean isSuccess = true;
 
        if (!ZYPCommonFunc.hasValue(bizMsg.invNum_H1.getValue())) {
            return isSuccess;
        }
        // xxLineNum Check
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if (!NFCL3000CommonLogic.checkLine_AccountDist_Idx(bizMsg.B, bizMsg.C.no(i))) {
                isSuccess = false;
            }
        }
        if (!isSuccess) {
            return isSuccess;
        }

        // xxLineNum Check
        if (!NFCL3000CommonLogic.check_AccountDist(bizMsg, false)) {
            bizMsg.xxDplyTab.setValue(TAB_Accounting);
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                bizMsg.C.no(i).xxLineNum_C1.setErrorInfo(1, NOT_ASSIGNED_ERROR, new String[] {PRM_SLS_CR_S, PRM_ACCT_DIST });
            }
            isSuccess = false;
        }
        if (!isSuccess) {
            return isSuccess;
        }

        if (!NFCL3000CommonLogic.checkAcctDist(bizMsg)) {
            isSuccess = false;
        }
        return isSuccess;
    }
    // END   2016/06/03 S.Fujita [QC#9157,ADD]

    // START 2016/06/20 S.Fujita [QC#9454,ADD]
    /**
     * updateInvoice_INV
     * @param cMsg
     * @param sMsg
     * @return
     */
    public static boolean updateInvoice_INV(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        boolean isSuccess = true;
        boolean isUpdate = false;

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        /************************************************/
        /* update INV                                   */
        /************************************************/
        INVTMsg invMsg = new INVTMsg();
        invMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        invMsg.invNum.setValue(bizMsg.invNum_H1.getValue());
        INVTMsg updInvMsg = (INVTMsg) EZDTBLAccessor.findByKeyForUpdate(invMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updInvMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0864E", new String[]{"INV", bizMsg.invNum_H1.getValue()});
            return false;
        }

        if(!updInvMsg.invDt.getValue().equals(bizMsg.invDt_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.invDt, bizMsg.invDt_H1.getValue());
            isUpdate = true;
        }
        // START 2016/08/01 S.Fujita [QC#10148,MOD]
//        if(!updInvMsg.acctDt.getValue().equals(bizMsg.acctDt.getValue())) {
//            ZYPEZDItemValueSetter.setValue(updInvMsg.acctDt, bizMsg.acctDt.getValue());
//            isUpdate = true;
//        }
        if(ZYPCommonFunc.hasValue(bizMsg.acctDt_H1.getValue())) {
            if(!updInvMsg.acctDt.getValue().equals(bizMsg.acctDt_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(updInvMsg.acctDt, bizMsg.acctDt_H1.getValue());
                isUpdate = true;
            }
        }
        // END   2016/08/01 S.Fujita [QC#10148,MOD]
        if(!updInvMsg.invTpCd.getValue().equals(bizMsg.invTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.invTpCd, bizMsg.invTpCd_H1.getValue());
            isUpdate = true;
        }
        if(!updInvMsg.netDueDt.getValue().equals(bizMsg.netDueDt_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.netDueDt, bizMsg.netDueDt_H1.getValue());
            isUpdate = true;
        }
        if(!updInvMsg.custIssPoNum.getValue().equals(bizMsg.custIssPoNum_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.custIssPoNum, bizMsg.custIssPoNum_H1.getValue());
            isUpdate = true;
        }

        String invRcpntCustCd = null;
//        if(!updInvMsg.billToCustCd.getValue().equals(bizMsg.billToCustCd_H3.getValue())) { 2019/09/09 H.Ikeda [QC#53251, DEL]
            ZYPEZDItemValueSetter.setValue(updInvMsg.billToCustCd, bizMsg.billToCustCd_H3.getValue());
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam.put("billToCustCd", bizMsg.billToCustCd_H3.getValue());
            ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            S21SsmEZDResult invRcpntCust = NFCL3000Query.getInstance().searchInvRcpntCust(bizMsg, ssmParam);
            if (invRcpntCust.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) invRcpntCust.getResultObject();
                invRcpntCustCd = (String)map.get(INV_RCPNT_CUST_CD);
                if(ZYPCommonFunc.hasValue(invRcpntCustCd)) {
                    ssmParam.clear();
                    ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
                    ssmParam.put("invRcpntCustCd", invRcpntCustCd);
                    ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
                    S21SsmEZDResult invRcpnt = NFCL3000Query.getInstance().searchInvRcpnt(bizMsg, ssmParam);
                    if (invRcpnt.isCodeNormal()) {
                        Map<String, Object> map2 = (Map<String, Object>) invRcpnt.getResultObject();
                        ZYPEZDItemValueSetter.setValue(updInvMsg.rcpntLocNm, (String)map2.get(LOC_NM));
                        ZYPEZDItemValueSetter.setValue(updInvMsg.rcpntAddlLocNm, (String)map2.get(ADDL_LOC_NM));
                        ZYPEZDItemValueSetter.setValue(updInvMsg.rcpntFirstLineAddr, (String)map2.get(FIRST_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(updInvMsg.rcpntScdLineAddr, (String)map2.get(SCD_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(updInvMsg.rcpntThirdLineAddr, (String)map2.get(THIRD_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(updInvMsg.rcpntFrthLineAddr, (String)map2.get(FRTH_LINE_ADDR));
                        ZYPEZDItemValueSetter.setValue(updInvMsg.rcpntCtyAddr, (String)map2.get(CTY_ADDR));
                        ZYPEZDItemValueSetter.setValue(updInvMsg.rcpntCntyNm, (String)map2.get(CNTY_NM));
                        ZYPEZDItemValueSetter.setValue(updInvMsg.rcpntProvNm, (String)map2.get(PROV_NM));
                        ZYPEZDItemValueSetter.setValue(updInvMsg.rcpntStCd, (String)map2.get(ST_CD));
                        ZYPEZDItemValueSetter.setValue(updInvMsg.rcpntPostCd, (String)map2.get(POST_CD));
                        ZYPEZDItemValueSetter.setValue(updInvMsg.rcpntCtryCd, (String)map2.get(CTRY_CD));
                    }
                }
            }
            isUpdate = true;
//        } 2019/09/09 H.Ikeda [QC#53251, DEL]
        if(!updInvMsg.sellToCustCd.getValue().equals(bizMsg.sellToCustCd_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.sellToCustCd, bizMsg.sellToCustCd_H3.getValue());
            isUpdate = true;
            // START 2019/09/09 H.Ikeda [QC#53251, MOD]
            //Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam = new HashMap<String, Object>();
            // END   2019/09/09 H.Ikeda [QC#53251, MOD]
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam.put("sellToCustCd", bizMsg.sellToCustCd_H3.getValue());
            S21SsmEZDResult sellTo = NFCL3000Query.getInstance().searchSellto(bizMsg, ssmParam);
            if (sellTo.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) sellTo.getResultObject();
                ZYPEZDItemValueSetter.setValue(updInvMsg.sellToLocNm, (String)map.get(LOC_NM));
                ZYPEZDItemValueSetter.setValue(updInvMsg.sellToAddlLocNm, (String)map.get(ADDL_LOC_NM));
                ZYPEZDItemValueSetter.setValue(updInvMsg.sellToFirstLineAddr, (String)map.get(FIRST_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(updInvMsg.sellToScdLineAddr, (String)map.get(SCD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(updInvMsg.sellToThirdLineAddr, (String)map.get(THIRD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(updInvMsg.sellToFrthLineAddr, (String)map.get(FRTH_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(updInvMsg.sellToCtyAddr, (String)map.get(CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(updInvMsg.sellToCntyNm, (String)map.get(CNTY_NM));
                ZYPEZDItemValueSetter.setValue(updInvMsg.sellToProvNm, (String)map.get(PROV_NM));
                ZYPEZDItemValueSetter.setValue(updInvMsg.sellToStCd, (String)map.get(ST_CD));
                ZYPEZDItemValueSetter.setValue(updInvMsg.sellToPostCd, (String)map.get(POST_CD));
                ZYPEZDItemValueSetter.setValue(updInvMsg.sellToCtryCd, (String)map.get(CTRY_CD));
                ZYPEZDItemValueSetter.setValue(updInvMsg.sellToFirstRefCmntTxt, (String)map.get(FIRST_REF_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(updInvMsg.sellToScdRefCmntTxt, (String)map.get(SCD_REF_CMNT_TXT));
            }
        }

        if(!updInvMsg.pmtTermCd.getValue().equals(bizMsg.pmtTermCd_H1.getValue())) {
            PMT_TERMTMsg pmtTermMsg = new PMT_TERMTMsg();
            ZYPEZDItemValueSetter.setValue(pmtTermMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pmtTermMsg.pmtTermCd, bizMsg.pmtTermCd_H1.getValue());
            PMT_TERMTMsg outMsg = (PMT_TERMTMsg) EZDTBLAccessor.findByKey(pmtTermMsg);
            if (outMsg != null ) {
                ZYPEZDItemValueSetter.setValue(updInvMsg.pmtTermNm, outMsg.pmtTermNm.getValue());
            }
            ZYPEZDItemValueSetter.setValue(updInvMsg.pmtTermCd, bizMsg.pmtTermCd_H1.getValue());
            isUpdate = true;
        }

        // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//        if(updInvMsg.invTotDealNetAmt.getValue().compareTo(bizMsg.xxTotAmt_T4.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg))) != 0) {
//            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotDealNetAmt, bizMsg.xxTotAmt_T4.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotFuncNetAmt, bizMsg.xxTotAmt_T4.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            isUpdate = true;
//        }
//
//        if(updInvMsg.invTotDealSlsAmt.getValue().compareTo(bizMsg.xxTotAmt_T1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg))) != 0) {
//            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotDealSlsAmt, bizMsg.xxTotAmt_T1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotFuncSlsAmt, bizMsg.xxTotAmt_T1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            isUpdate = true;
//        }
//
//        if(updInvMsg.invTotDealFrtAmt.getValue().compareTo(bizMsg.xxTotAmt_T2.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg))) != 0) {
//            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotDealFrtAmt, bizMsg.xxTotAmt_T2.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotFuncFrtAmt, bizMsg.xxTotAmt_T2.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            isUpdate = true;
//        }
//
//        if(updInvMsg.invTotDealTaxAmt.getValue().compareTo(bizMsg.xxTotAmt_T3.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg))) != 0) {
//            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotDealTaxAmt, bizMsg.xxTotAmt_T3.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotFuncTaxAmt, bizMsg.xxTotAmt_T3.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            isUpdate = true;
//        }

        String invTpCd = bizMsg.invTpCd_H1.getValue();
        BigDecimal xxTotAmt_T4 = NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.xxTotAmt_T4);
        if(updInvMsg.invTotDealNetAmt.getValue().compareTo(xxTotAmt_T4) != 0) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotDealNetAmt, xxTotAmt_T4);
            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotFuncNetAmt, xxTotAmt_T4);
            isUpdate = true;
        }

        BigDecimal xxTotAmt_T1 = NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.xxTotAmt_T1);
        // START 2018/08/21 E.Kameishi [QC#27791,MOD]
        BigDecimal totSlsAmt = updInvMsg.invTotDealSlsAmt.getValue().add(updInvMsg.invTotDealDiscAmt.getValue());
        //if(updInvMsg.invTotDealSlsAmt.getValue().compareTo(xxTotAmt_T1) != 0) {
        if(totSlsAmt.compareTo(xxTotAmt_T1) != 0) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotDealSlsAmt, xxTotAmt_T1);
            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotFuncSlsAmt, xxTotAmt_T1);
            // Start 2019/04/15 S.Takami [QC#31188,ADD]
            updInvMsg.invTotDealDiscAmt.setValue(BigDecimal.ZERO);
            updInvMsg.invTotFuncDiscAmt.setValue(BigDecimal.ZERO);
            // END 2019/04/15 S.Takami [QC#31188,ADD]
            isUpdate = true;
        }
        // END 2018/08/21 E.Kameishi [QC#27791,MOD]

        BigDecimal xxTotAmt_T2 = NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.xxTotAmt_T2);
        if(updInvMsg.invTotDealFrtAmt.getValue().compareTo(xxTotAmt_T2) != 0) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotDealFrtAmt, xxTotAmt_T2);
            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotFuncFrtAmt, xxTotAmt_T2);
            isUpdate = true;
        }

        BigDecimal xxTotAmt_T3 = NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.xxTotAmt_T3);
        if(updInvMsg.invTotDealTaxAmt.getValue().compareTo(xxTotAmt_T3) != 0) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotDealTaxAmt, xxTotAmt_T3);
            ZYPEZDItemValueSetter.setValue(updInvMsg.invTotFuncTaxAmt, xxTotAmt_T3);
            isUpdate = true;
        }
        // END   2016/07/20 S.Yoshida [QC#12149,MOD]

        // START 2019/05/10 S.Takami [QC#50148,MOD]
//        if(!updInvMsg.invFirstCmntTxt.getValue().equals(bizMsg.invFirstCmntTxt_E1.getValue())) {
//            ZYPEZDItemValueSetter.setValue(updInvMsg.invFirstCmntTxt, bizMsg.invFirstCmntTxt_E1.getValue());
//            // START 2017/03/13 [QC#17933,ADD]
//            isUpdate = true;
//            // END   2017/03/13 [QC#17933,ADD]
//        }
        if(isUpdateComment(updInvMsg, bizMsg.xxInvMemoTxt_E1.getValue())) {
            isUpdate = true;
        }
        // END 2019/05/10 S.Takami [QC#50148,MOD]
        // START 2016/10/27 T.Murai [QC#13639, ADD]
        if (isManualInvoice(bizMsg)) {
            if (!updInvMsg.ordDt.getValue().equals(bizMsg.A.no(0).ordDt_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(updInvMsg.ordDt, bizMsg.A.no(0).ordDt_A1.getValue());
                isUpdate = true;
            }
        }
        // END   2016/10/27 T.Murai [QC#13639, ADD]

        if(!updInvMsg.payerCustCd.getValue().equals(bizMsg.billToCustCd_H3.getValue())) {
            // START 2019/09/09 H.Ikeda [QC#53251, MOD]
            //Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam = new HashMap<String, Object>();
            // END   2019/09/09 H.Ikeda [QC#53251, MOD]
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam.put("payerCustCd", bizMsg.billToCustCd_H3.getValue());
            S21SsmEZDResult flPln = NFCL3000Query.getInstance().searchFlPlnCmpyFlg(bizMsg, ssmParam);
            if(flPln.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) flPln.getResultObject();
                ZYPEZDItemValueSetter.setValue(updInvMsg.flPlnFlg, (String)map.get(FL_PLN_CMPY_FLG));
            }
        }

        if(!updInvMsg.invPrintStsCd.getValue().equals(bizMsg.invPrintStsCd_E1.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.invPrintStsCd, bizMsg.invPrintStsCd_E1.getValue());
            isUpdate = true;
        }

        if(!updInvMsg.payerCustCd.getValue().equals(bizMsg.billToCustCd_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.payerCustCd, bizMsg.billToCustCd_H3.getValue());
            isUpdate = true;
        }

        if(NFCL3000CommonLogic.isManualInvoice(bizMsg)) {
            if(ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H3.getValue()) && updInvMsg.fnlzInvFlg.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                ZYPEZDItemValueSetter.setValue(updInvMsg.fnlzInvFlg, ZYPConstant.FLG_ON_Y);
                isUpdate = true;
            }
        } else {
            if(updInvMsg.fnlzInvFlg.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                ZYPEZDItemValueSetter.setValue(updInvMsg.fnlzInvFlg, ZYPConstant.FLG_ON_Y);
                isUpdate = true;
            }
        }

        if(!updInvMsg.pmtTermCashDiscCd.getValue().equals(bizMsg.pmtTermCashDiscCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.pmtTermCashDiscCd, bizMsg.pmtTermCashDiscCd_H1.getValue());
            isUpdate = true;
            
            PMT_TERM_CASH_DISCTMsg pmtTermCashTMsg = new PMT_TERM_CASH_DISCTMsg();
            pmtTermCashTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            pmtTermCashTMsg.pmtTermCashDiscCd.setValue(bizMsg.pmtTermCashDiscCd_H1.getValue()); 
            pmtTermCashTMsg = (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(pmtTermCashTMsg);
            if (pmtTermCashTMsg != null) {
                ZYPEZDItemValueSetter.setValue(updInvMsg.pmtTermCashDiscDescTxt, pmtTermCashTMsg.pmtTermCashDiscDescTxt.getValue());
            }
        }

        if(ZYPCommonFunc.hasValue(invRcpntCustCd) && (!updInvMsg.billToCustCd.getValue().equals(bizMsg.billToCustCd_H3.getValue()))) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.invRcpntCustCd, invRcpntCustCd);
            isUpdate = true;
        }

        if(!updInvMsg.dsInvTpCd.getValue().equals(bizMsg.dsInvTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.dsInvTpCd, bizMsg.dsInvTpCd_H1.getValue());
            isUpdate = true;
        }

        if(!updInvMsg.srcSysDocNum.getValue().equals(bizMsg.srcSysDocNum_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.srcSysDocNum, bizMsg.srcSysDocNum_H1.getValue());
            isUpdate = true;
        }

        if(!updInvMsg.custCareTktNum.getValue().equals(bizMsg.custCareTktNum_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.custCareTktNum, bizMsg.custCareTktNum_H1.getValue());
            isUpdate = true;
        }

        if(!updInvMsg.slsRepTocCd.getValue().equals(bizMsg.slsRepTocCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepTocCd, bizMsg.slsRepTocCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepTocNm, bizMsg.slsRepTocNm_H1.getValue());
            // START 2021/10/25 S.Go [QC#55215,ADD]
            ZYPEZDItemValueSetter.setValue(updInvMsg.lineBizTpCd, NFCL3000Query.getInstance().getCOALineBizTpCd(bizMsg));
            // END 2021/10/25 S.Go [QC#55215,ADD]

            S21_ORGTMsg s21OrgTmsg = new S21_ORGTMsg();
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.tocCd,      bizMsg.slsRepTocCd_H1.getValue());

            s21OrgTmsg = (S21_ORGTMsg) S21CacheTBLAccessor.findByKey(s21OrgTmsg);
            if (s21OrgTmsg != null ) {
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepFirstOrgCd, s21OrgTmsg.firstOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepFirstOrgNm, s21OrgTmsg.firstOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepScdOrgCd, s21OrgTmsg.scdOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepScdOrgNm, s21OrgTmsg.scdOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepThirdOrgCd, s21OrgTmsg.thirdOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepThirdOrgNm, s21OrgTmsg.thirdOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepFrthOrgCd, s21OrgTmsg.frthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepFrthOrgNm, s21OrgTmsg.frthOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepFifthOrgCd, s21OrgTmsg.fifthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepFifthOrgNm, s21OrgTmsg.fifthOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepSixthOrgCd, s21OrgTmsg.sixthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepSixthOrgNm, s21OrgTmsg.sixthOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepSvnthOrgCd, s21OrgTmsg.svnthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepSvnthOrgNm, s21OrgTmsg.svnthOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepEighthOrgCd, s21OrgTmsg.eighthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepEighthOrgNm, s21OrgTmsg.eighthOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepNinthOrgCd, s21OrgTmsg.ninthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepNinthOrgNm, s21OrgTmsg.ninthOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepTenthOrgCd, s21OrgTmsg.tenthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepTenthOrgNm, s21OrgTmsg.tenthOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepElvthOrgCd, s21OrgTmsg.elvthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(updInvMsg.slsRepElvthOrgNm, s21OrgTmsg.elvthOrgNm.getValue());
            }
            isUpdate = true;
        }

        if(!updInvMsg.billToCtacPsnFirstNm.getValue().equals(bizMsg.billToCtacPsnFirstNm_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.billToCtacPsnFirstNm, bizMsg.billToCtacPsnFirstNm_H3.getValue());
            isUpdate = true;
        }

        if(!updInvMsg.billToCtacPsnLastNm.getValue().equals(bizMsg.billToCtacPsnLastNm_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.billToCtacPsnLastNm, bizMsg.billToCtacPsnLastNm_H3.getValue());
            isUpdate = true;
        }
        
        if(!updInvMsg.billToCustAcctCd.getValue().equals(bizMsg.billToCustAcctCd_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.billToCustAcctCd, bizMsg.billToCustAcctCd_H3.getValue());
            ZYPEZDItemValueSetter.setValue(updInvMsg.billToCustAcctNm, bizMsg.billToCustAcctNm_H3.getValue());
            isUpdate = true;
        }

        if(!updInvMsg.soldToCustLocCd.getValue().equals(bizMsg.billToCustCd_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(updInvMsg.soldToCustLocCd, bizMsg.billToCustCd_H3.getValue());
            // START 2019/09/09 H.Ikeda [QC#53251, MOD]
            //Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam = new HashMap<String, Object>();
            // END   2019/09/09 H.Ikeda [QC#53251, MOD]
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam.put("billToCustCd", bizMsg.billToCustCd_H3.getValue());
            ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            S21SsmEZDResult locNm = NFCL3000Query.getInstance().getSoldToCustLocNm(bizMsg, ssmParam);
            if(locNm.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) locNm.getResultObject();
                ZYPEZDItemValueSetter.setValue(updInvMsg.soldToCustLocNm, (String)map.get(LOC_NM));
            }
            isUpdate = true;
        }

        String itrlInvErrFlg = updInvMsg.itrlInvErrFlg.getValue();
        if(NFCL3000CommonLogic.isManualInvoice(bizMsg)) {
            if(ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H3.getValue()) && ZYPConstant.FLG_ON_Y.equals(itrlInvErrFlg)) {
                itrlInvErrFlg = ZYPConstant.FLG_OFF_N;
                ZYPEZDItemValueSetter.setValue(updInvMsg.itrlInvErrFlg, itrlInvErrFlg);
                isUpdate = true;
            }
        } else {
            if(ZYPConstant.FLG_ON_Y.equals(itrlInvErrFlg)) {
                itrlInvErrFlg = ZYPConstant.FLG_OFF_N;
                ZYPEZDItemValueSetter.setValue(updInvMsg.itrlInvErrFlg, itrlInvErrFlg);
                isUpdate = true;
            }
        }

        // START 2019/06/06 S.Takami [QC#50691,ADD]
        if (isManualInvoice(bizMsg) && !ZYPCommonFunc.hasValue(bizMsg.origInvNum_E1)) {
            String supplyDsOrdCatgCd = supplyInvoicdOrdCatgCd(bizMsg);
            if (!S21StringUtil.isEquals(updInvMsg.dsOrdCatgCd.getValue(), supplyDsOrdCatgCd)) {
                ZYPEZDItemValueSetter.setValue(updInvMsg.dsOrdCatgCd, supplyDsOrdCatgCd);
                isUpdate = true;
            }
        }
        // END 2019/06/06 S.Takami [QC#50691,ADD]
        if(isUpdate) {
            EZDTBLAccessor.update(updInvMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updInvMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0862E", new String[]{"INV"});
                return false;
            }
        }
        return isSuccess;
    }

    /**
     * updateInvoice_INV_BOL
     * @param cMsg
     * @param sMsg
     * @return
     */
    public static boolean updateInvoice_INV_BOL(EZDCMsg cMsg, EZDSMsg sMsg) {

        boolean isSuccess = true;
        int insertCnt = 0;

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        /************************************************/
        /* remove INV_BOL                               */
        /************************************************/
        INV_BOLTMsg rmvInvBolMsg = new INV_BOLTMsg();
        ZYPEZDItemValueSetter.setValue(rmvInvBolMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rmvInvBolMsg.invNum, bizMsg.invNum_H1.getValue());
        EZDTBLAccessor.removeByPartialKey(rmvInvBolMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rmvInvBolMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0863E", new String[]{"INV_BOL"});
            return false;
        }

        // START 2016/07/20 S.Yoshida [QC#12149,ADD]
        String invTpCd = bizMsg.invTpCd_H1.getValue();
        // END   2016/07/20 S.Yoshida [QC#12149,ADD]

        /************************************************/
        /* insert INV_BOL                               */
        /************************************************/
        final List<INV_BOLTMsg> invBolList = new ArrayList<INV_BOLTMsg>();
        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
            INV_BOLTMsg insInvBolMsg = new INV_BOLTMsg();
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.invNum, bizMsg.invNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.invBolLineNum, bizMsg.D.no(i).invBolLineNum_D1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.bolNum, bizMsg.D.no(i).bolNum_D1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.soNum, bizMsg.D.no(i).soNum_D1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipFromInvtyLocCd, bizMsg.D.no(i).shipFromInvtyLocCd_D1.getValue());

            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToCustCd, bizMsg.D.no(i).shipToCustCd_D1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.dropShipFlg, ZYPConstant.FLG_ON_Y);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam.put("shipToCustCd", bizMsg.D.no(i).shipToCustCd_D1.getValue());
            ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            S21SsmEZDResult shipTo = NFCL3000Query.getInstance().searchShipto2(bizMsg, ssmParam);
            if (shipTo.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) shipTo.getResultObject();
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToLocNm, (String)map.get(LOC_NM));
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToAddlLocNm, (String)map.get(ADDL_LOC_NM));
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToFirstLineAddr, (String)map.get(FIRST_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToScdLineAddr, (String)map.get(SCD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToThirdLineAddr, (String)map.get(THIRD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToFrthLineAddr, (String)map.get(FRTH_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToCtyAddr, (String)map.get(CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToCntyNm, (String)map.get(CNTY_NM));
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToProvNm, (String)map.get(PROV_NM));
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToStCd, (String)map.get(ST_CD));
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToPostCd, (String)map.get(POST_CD));
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToCtryCd, (String)map.get(CTRY_CD));
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToFirstRefCmntTxt, (String)map.get(FIRST_REF_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToScdRefCmntTxt, (String)map.get(SCD_REF_CMNT_TXT));
            }

            ZYPEZDItemValueSetter.setValue(insInvBolMsg.exptFlg, ZYPConstant.FLG_OFF_N);

            // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipDealSlsAmt, bizMsg.D.no(i).shipDealSlsAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipFuncSlsAmt, bizMsg.D.no(i).shipDealSlsAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//
//            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipDealNetAmt, bizMsg.D.no(i).shipDealNetAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipFuncNetAmt, bizMsg.D.no(i).shipDealNetAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//
//            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipDealFrtAmt, bizMsg.D.no(i).shipDealFrtAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipFuncFrtAmt, bizMsg.D.no(i).shipDealFrtAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//
//            ZYPEZDItemValueSetter.setValue(insInvBolMsg.totBolDealTaxAmt, bizMsg.xxTotAmt_T3.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insInvBolMsg.totBolFuncTaxAmt, bizMsg.xxTotAmt_T3.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//
//            ZYPEZDItemValueSetter.setValue(insInvBolMsg.frtDealTaxAmt, bizMsg.D.no(i).frtDealTaxAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insInvBolMsg.frtFuncTaxAmt, bizMsg.D.no(i).frtDealTaxAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));

            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipDealSlsAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealSlsAmt_D1));
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipFuncSlsAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealSlsAmt_D1));

            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipDealNetAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealNetAmt_D1));
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipFuncNetAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealNetAmt_D1));

            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipDealFrtAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealFrtAmt_D1));
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipFuncFrtAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).shipDealFrtAmt_D1));

            // START 2018/05/22 Y.Matsui [QC#21841,MOD]
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.totBolDealTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).totBolDealTaxAmt_D1));
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.totBolFuncTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd,bizMsg.D.no(i).totBolDealTaxAmt_D1));
            // END   2018/05/22 Y.Matsui [QC#21841,MOD]

            ZYPEZDItemValueSetter.setValue(insInvBolMsg.frtDealTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).frtDealTaxAmt_D1));
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.frtFuncTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).frtDealTaxAmt_D1));
            // END   2016/07/20 S.Yoshida [QC#12149,MOD]

            ZYPEZDItemValueSetter.setValue(insInvBolMsg.frtTaxPct, bizMsg.D.no(i).frtTaxPct_D1.getValue());

            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipDealDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipDealHdlgChrgAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipFuncDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipFuncHdlgChrgAmt, BigDecimal.ZERO);

            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipDt, bizMsg.D.no(i).shipDt_D1.getValue());

            ZYPEZDItemValueSetter.setValue(insInvBolMsg.dropShipFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.exptFlg, ZYPConstant.FLG_OFF_N);

            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToCustAcctCd, bizMsg.D.no(i).shipToCustAcctCd_D1.getValue());

            ssmParam.clear();
            if(!ZYPCommonFunc.hasValue(bizMsg.D.no(i).shipToCustAcctNm_D1.getValue())) {
                ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
                ssmParam.put("sellToCustCd", bizMsg.D.no(i).shipToCustAcctCd_D1.getValue());
                S21SsmEZDResult dsAcct = NFCL3000Query.getInstance().searchSellToCust(bizMsg, ssmParam);
                if(dsAcct.isCodeNormal()) {
                    Map<String, Object> map = (Map<String, Object>) dsAcct.getResultObject();
                    ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToCustAcctNm,(String)map.get(DS_ACCT_NM));
                }
            } else {
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToCustAcctNm,bizMsg.D.no(i).shipToCustAcctNm_D1.getValue());
            }

            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToCtacPsnFirstNm, bizMsg.D.no(i).shipToCtacPsnFirstNm_D1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.shipToCtacPsnLastNm, bizMsg.D.no(i).shipToCtacPsnLastNm_D1.getValue());

            ssmParam.clear();
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam.put("ctacPsnFirstNm", bizMsg.D.no(i).shipToCtacPsnFirstNm_D1.getValue());
            ssmParam.put("ctacPsnLastNm", bizMsg.D.no(i).shipToCtacPsnLastNm_D1.getValue());
            S21SsmEZDResult ctacPsn = NFCL3000Query.getInstance().searchCtacPsnList(bizMsg, ssmParam);
            if (ctacPsn.isCodeNormal()) {
                List<Map> ctacPsnList = (List<Map>) ctacPsn.getResultObject();
                ZYPEZDItemValueSetter.setValue(insInvBolMsg.ctacPsnPk, new BigDecimal(ctacPsnList.get(0).get(CTAC_PSN_PK).toString()));
            }
            // START 2019/08/07 S.Takami [QC#52453,ADD]
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.carrCd, bizMsg.D.no(i).carrCd_D1);
            ZYPEZDItemValueSetter.setValue(insInvBolMsg.carrNm, bizMsg.D.no(i).carrNm_D1);
            // END 2019/08/07 S.Takami [QC#52453,ADD]
            invBolList.add(insInvBolMsg);
        }

        INV_BOLTMsg[] invBolTmsgs = new INV_BOLTMsg[invBolList.size()];
        for (int i = 0; i < invBolList.size(); i++) {
            INV_BOLTMsg invBolTmsg = invBolList.get(i);
            invBolTmsgs[i] = invBolTmsg;
        }
        insertCnt = S21FastTBLAccessor.insert(invBolTmsgs);
        if (invBolTmsgs.length != insertCnt) {
            bizMsg.setMessageInfo("NFCM0861E", new String[]{"INV_BOL"});
            return false;
        }

        return isSuccess;
    }

    /**
     * updateInvoice_INV_LINE
     * @param cMsg
     * @param sMsg
     * @return
     */
    public static boolean updateInvoice_INV_LINE(EZDCMsg cMsg, EZDSMsg sMsg) {

        boolean isSuccess = true;
        int insertCnt = 0;

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        /************************************************/
        /* remove INV_LINE                  */
        /************************************************/
        INV_LINETMsg rmvInvLineMsg = new INV_LINETMsg();
        ZYPEZDItemValueSetter.setValue(rmvInvLineMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rmvInvLineMsg.invNum, bizMsg.invNum_H1.getValue());
        EZDTBLAccessor.removeByPartialKey(rmvInvLineMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rmvInvLineMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0863E", new String[]{"INV_LINE"});
            return false;
        }

        /************************************************/
        /* insert INV_LINE                              */
        /************************************************/

        // START 2016/07/20 S.Yoshida [QC#12149,ADD]
        String invTpCd = bizMsg.invTpCd_H1.getValue();
        // END   2016/07/20 S.Yoshida [QC#12149,ADD]

        final List<INV_LINETMsg> invLineList = new ArrayList<INV_LINETMsg>();
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            INV_LINETMsg insInvLineMsg = new INV_LINETMsg();
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.invNum, bizMsg.invNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.invBolLineNum, bizMsg.A.no(i).invBolLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.invLineNum, bizMsg.A.no(i).invLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.invLineSubNum, bizMsg.A.no(i).invLineSubNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.invLineSubTrxNum, bizMsg.A.no(i).invLineSubTrxNum_A1.getValue());

            S21_ORGTMsg s21OrgTmsg = new S21_ORGTMsg();
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.tocCd,      bizMsg.slsRepTocCd_H1.getValue());
            s21OrgTmsg = (S21_ORGTMsg) S21CacheTBLAccessor.findByKey(s21OrgTmsg);

            if (s21OrgTmsg != null ) {
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.firstOrgCd, s21OrgTmsg.firstOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.firstOrgNm, s21OrgTmsg.firstOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.scdOrgCd, s21OrgTmsg.scdOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.scdOrgNm, s21OrgTmsg.scdOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.thirdOrgCd, s21OrgTmsg.thirdOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.thirdOrgNm, s21OrgTmsg.thirdOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.frthOrgCd, s21OrgTmsg.frthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.frthOrgNm, s21OrgTmsg.frthOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.fifthOrgCd, s21OrgTmsg.fifthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.fifthOrgNm, s21OrgTmsg.fifthOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.sixthOrgCd, s21OrgTmsg.sixthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.sixthOrgNm, s21OrgTmsg.sixthOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.svnthOrgCd, s21OrgTmsg.svnthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.svnthOrgNm, s21OrgTmsg.svnthOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.eighthOrgCd, s21OrgTmsg.eighthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.eighthOrgNm, s21OrgTmsg.eighthOrgNm.getValue());

                ZYPEZDItemValueSetter.setValue(insInvLineMsg.coaCmpyCd, ZYPCodeDataUtil.getVarCharConstValue(COA_CMPY_CD, bizMsg.glblCmpyCd.getValue()));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.coaExtnCd, ZYPCodeDataUtil.getVarCharConstValue(COA_EXTN_CD, bizMsg.glblCmpyCd.getValue()));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.coaBrCd, s21OrgTmsg.coaBrCd.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.coaChCd, s21OrgTmsg.coaChCd.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.coaCcCd, s21OrgTmsg.coaCcCd.getValue());

                ZYPEZDItemValueSetter.setValue(insInvLineMsg.ninthOrgCd, s21OrgTmsg.ninthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.ninthOrgNm, s21OrgTmsg.ninthOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.tenthOrgCd, s21OrgTmsg.tenthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.tenthOrgNm, s21OrgTmsg.tenthOrgNm.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.elvthOrgCd, s21OrgTmsg.elvthOrgCd.getValue());
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.elvthOrgNm, s21OrgTmsg.elvthOrgNm.getValue());
            }

            ZYPEZDItemValueSetter.setValue(insInvLineMsg.slsRepTocCd, bizMsg.slsRepTocCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.slsRepTocNm, bizMsg.slsRepTocNm_H1.getValue());

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam.put("mdseCd", bizMsg.A.no(i).mdseCd_A1.getValue());
            // START 2019/05/27 S.Takami [QC#50541,MOD]
//            S21SsmEZDResult mdse = NFCL3000Query.getInstance().searchMdse(bizMsg, ssmParam);
            S21SsmEZDResult mdse = NFCL3000Query.getInstance().getItemInfo(bizMsg, ssmParam);
            // END 2019/05/27 S.Takami [QC#50541,MOD]
            if (mdse.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) mdse.getResultObject();

                ZYPEZDItemValueSetter.setValue(insInvLineMsg.zerothProdCtrlCd, (String)map.get(ZEROTH_PROD_CTRL_CD));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.zerothProdCtrlNm, (String)map.get(ZEROTH_PROD_CTRL_NM));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.firstProdCtrlCd, (String)map.get(FIRST_PROD_CTRL_CD));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.firstProdCtrlNm, (String)map.get(FIRST_PROD_CTRL_NM));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.scdProdCtrlCd, (String)map.get(SCD_PROD_CTRL_CD));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.scdProdCtrlNm, (String)map.get(SCD_PROD_CTRL_NM));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.thirdProdCtrlCd, (String)map.get(THIRD_PROD_CTRL_CD));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.thirdProdCtrlNm, (String)map.get(THIRD_PROD_CTRL_NM));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.frthProdCtrlCd, (String)map.get(FRTH_PROD_CTRL_CD));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.frthProdCtrlNm, (String)map.get(FRTH_PROD_CTRL_NM));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.fifthProdCtrlCd, (String)map.get(FIFTH_PROD_CTRL_CD));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.fifthProdCtrlNm, (String)map.get(FIFTH_PROD_CTRL_NM));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.sixthProdCtrlCd, (String)map.get(SIXTH_PROD_CTRL_CD));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.sixthProdCtrlNm, (String)map.get(SIXTH_PROD_CTRL_NM));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.svnthProdCtrlCd, (String)map.get(SVNTH_PROD_CTRL_CD));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.svnthProdCtrlNm, (String)map.get(SVNTH_PROD_CTRL_NM));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.mdseTpCd, (String)map.get(MDSE_TP_CD));

                ZYPEZDItemValueSetter.setValue(insInvLineMsg.mdseCd, bizMsg.A.no(i).mdseCd_A1.getValue());
                if(ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdseNm_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(insInvLineMsg.mdseNm, bizMsg.A.no(i).mdseNm_A1.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(insInvLineMsg.mdseNm, (String)map.get(MDSE_NM));
                }

                ZYPEZDItemValueSetter.setValue(insInvLineMsg.coaProdCd, (String)map.get(COA_PROD_CD));
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.coaProdNm, (String)map.get(COA_PROD_NM));

            }
            // START 2017/12/25 E.Kameishi [QC#20312,ADD]
            boolean isTaxAdj = false;
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxMstChkFlg_A1.getValue())) {
                isTaxAdj = true;
            }
            // END 2017/12/25 E.Kameishi [QC#20312,ADD]
            if (isTaxAdj) {
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.trxCd, TRX_TAX_ADJUSTMENT);
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.trxRsnCd, TRX_RSN_TAX_ADJUSTMENT);
            } else {
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.trxCd, TRX_MANUAL_INVOICE);
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.trxRsnCd, TRX_RSN_MANUAL_INVOICE);
            }

            ZYPEZDItemValueSetter.setValue(insInvLineMsg.ordQty, BigDecimal.ZERO);
            // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//            ZYPEZDItemValueSetter.setValue(insInvLineMsg.shipQty, bizMsg.A.no(i).shipQty_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.shipQty, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).shipQty_A1));
            // END   2016/07/20 S.Yoshida [QC#12149,MOD]
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.boQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.manPrcFlg, ZYPConstant.FLG_OFF_N);

            ZYPEZDItemValueSetter.setValue(insInvLineMsg.dealNetUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
            // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//            ZYPEZDItemValueSetter.setValue(insInvLineMsg.invLineDealTaxAmt, bizMsg.A.no(i).invLineDealTaxAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insInvLineMsg.invLineDealNetAmt, bizMsg.A.no(i).invLineDealNetAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));

            // START 2018/03/29 E.Kameishi [QC#24390,MOD]
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxMstChkFlg_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.invLineDealTaxAmt, bizMsg.A.no(i).invLineDealTaxAmt_A1);
            } else {
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.invLineDealTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invLineDealTaxAmt_A1));
            }
            // END 2018/03/29 E.Kameishi [QC#24390,MOD]
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.invLineDealNetAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invLineDealNetAmt_A1));
            // END   2016/07/20 S.Yoshida [QC#12149,MOD]
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.dealDiscUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.funcNetUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
            // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//            ZYPEZDItemValueSetter.setValue(insInvLineMsg.invLineFuncTaxAmt, bizMsg.A.no(i).invLineDealTaxAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insInvLineMsg.invLineFuncNetAmt, bizMsg.A.no(i).invLineDealNetAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));

            // START 2018/03/29 E.Kameishi [QC#24390,MOD]
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxMstChkFlg_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.invLineFuncTaxAmt, bizMsg.A.no(i).invLineDealTaxAmt_A1);
            } else {
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.invLineFuncTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invLineDealTaxAmt_A1));
            }
            // END 2018/03/29 E.Kameishi [QC#24390,MOD]
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.invLineFuncNetAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invLineDealNetAmt_A1));
            // END   2016/07/20 S.Yoshida [QC#12149,MOD]
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.funcDiscUnitPrcAmt, BigDecimal.ZERO);

            ZYPEZDItemValueSetter.setValue(insInvLineMsg.taxFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.taxPct, bizMsg.A.no(i).taxPct_A1.getValue());

            ZYPEZDItemValueSetter.setValue(insInvLineMsg.mercCntnFlg, ZYPConstant.FLG_OFF_N);

            // 2017/10/23 QC#20719 Del Start
            //if(ZYPCommonFunc.hasValue(bizMsg.A.no(i).unitCostAmt_A1.getValue())) {
                // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//                BigDecimal shipCmplCostAmt = bizMsg.A.no(i).unitCostAmt_A1.getValue().multiply(bizMsg.A.no(i).shipQty_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
                //BigDecimal shipCmplCostAmt = bizMsg.A.no(i).unitCostAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).shipQty_A1));
                // END   2016/07/20 S.Yoshida [QC#12149,MOD]

                //ZYPEZDItemValueSetter.setValue(insInvLineMsg.shipCmplCostAmt, shipCmplCostAmt);
            //}
            // START 2016/07/20 S.Yoshida [QC#12149,MOD]
            // 2017/10/23 QC#20719 Del End
//            ZYPEZDItemValueSetter.setValue(insInvLineMsg.dealGrsUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(insInvLineMsg.dealGrsTotPrcAmt, bizMsg.A.no(i).invLineDealNetAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insInvLineMsg.funcGrsUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(insInvLineMsg.funcGrsTotPrcAmt, bizMsg.A.no(i).invLineDealNetAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.dealGrsUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.dealGrsTotPrcAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invLineDealNetAmt_A1));
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.funcGrsUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.funcGrsTotPrcAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invLineDealNetAmt_A1));
            // END   2016/07/20 S.Yoshida [QC#12149,MOD]
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.setRatioKeepFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.cpoOrdNum, bizMsg.A.no(i).cpoOrdNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.histCratCpltFlg, ZYPConstant.FLG_OFF_N);

            // START 2016/08/01 S.Fujita [QC#10148,ADD]
//            ZYPEZDItemValueSetter.setValue(insInvLineMsg.cpoDtlLineNum, bizMsg.A.no(i).cpoDtlLineNum_A1.getValue()); // DEL 2016/11/17 [QC#15987]
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.ordQty, bizMsg.A.no(i).ordQty_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.crDrRsnCd, bizMsg.A.no(i).crDrRsnCd_A1.getValue());
            // END   2016/08/01 S.Fujita [QC#10148,ADD]

            ZYPEZDItemValueSetter.setValue(insInvLineMsg.uomCd, bizMsg.A.no(i).pkgUomCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.custIssPoNum, bizMsg.custIssPoNum_H1.getValue());

            ZYPEZDItemValueSetter.setValue(insInvLineMsg.baseCmptFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.taxCalcGeoCd, bizMsg.A.no(i).taxCalcGeoCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.compProcStsCd, ZERO);

            ZYPEZDItemValueSetter.setValue(insInvLineMsg.dsContrNum, bizMsg.A.no(i).dsContrNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.dsContrSqNum, bizMsg.A.no(i).dsContrSqNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.svcConfigMstrPk, bizMsg.A.no(i).svcConfigMstrPk_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.mdlNm, bizMsg.A.no(i).mdlNm_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.svcInvChrgTpCd, bizMsg.A.no(i).svcInvChrgTpCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.bllgPerFromDt, bizMsg.A.no(i).bllgPerFromDt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.bllgPerThruDt, bizMsg.A.no(i).bllgPerThruDt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.bllgCopyQty, bizMsg.A.no(i).bllgCopyQty_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.dsContrDtlPk, bizMsg.A.no(i).dsContrDtlPk_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.manInvCratCmntTxt, bizMsg.A.no(i).manInvCratCmntTxt_A1.getValue());

            ZYPEZDItemValueSetter.setValue(insInvLineMsg.firstBllgAttrbValTxt, bizMsg.A.no(i).firstBllgAttrbValTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.scdBllgAttrbValTxt, bizMsg.A.no(i).scdBllgAttrbValTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.thirdBllgAttrbValTxt, bizMsg.A.no(i).thirdBllgAttrbValTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.frthBllgAttrbValTxt, bizMsg.A.no(i).frthBllgAttrbValTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.fifthBllgAttrbValTxt, bizMsg.A.no(i).fifthBllgAttrbValTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.sixthBllgAttrbValTxt, bizMsg.A.no(i).sixthBllgAttrbValTxt_A1.getValue());

            ZYPEZDItemValueSetter.setValue(insInvLineMsg.svcInvLinePk, bizMsg.A.no(i).svcInvLinePk_A1.getValue());

            // START 2018/05/22 Y.Matsui [QC#21841,ADD]
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.invLineCatgCd, bizMsg.A.no(i).invLineCatgCd_A1.getValue());
            // END   2018/05/22 Y.Matsui [QC#21841,ADD]

            // START 2018/09/28 T.Ogura [QC#28526,ADD]
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.ordCustUomQty, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).ordCustUomQty_A1));
            // END   2018/09/28 T.Ogura [QC#28526,ADD]

            // START 2019/04/25 S.Takami [QC#50078,ADD]
            boolean isSetInvDplyQty = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.A.no(i).xxDplyCtrlFlg_A1.getValue());
            isSetInvDplyQty |= NFCL3000CommonLogic.isDsInvTpFsr(bizMsg);
            if (isSetInvDplyQty) {
                ZYPEZDItemValueSetter.setValue(insInvLineMsg.invDplyQty, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invDplyQty_A1));
            } else {
                insInvLineMsg.invDplyQty.clear();
            }
            // END 2019/04/25 S.Takami [QC#50078,ADD]

            // START 2018/10/24 S.Takami [QC#27069, Add]
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.svcMachMstrPk, bizMsg.A.no(i).svcMachMstrPk_A1);
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.mdlId, bizMsg.A.no(i).mdlId_A1);
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.mdlNm, bizMsg.A.no(i).mdlNm_A1);
            // END   2018/10/24 S.Takami [QC#27069, Add]

            // START 2019/08/07 S.Takami [QC#52453,ADD]
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.cpoDtlLineNum, bizMsg.A.no(i).cpoDtlLineNum_A1);
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.cpoDtlLineSubNum, bizMsg.A.no(i).cpoDtlLineSubNum_A1);
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.origOrCustMdseCd, bizMsg.A.no(i).origOrCustMdseCd_A1);
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.prcCatgCd, bizMsg.A.no(i).prcCatgCd_A1);
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.stkStsCd, bizMsg.A.no(i).stkStsCd_A1);
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.dsOrdPosnNum, bizMsg.A.no(i).dsOrdPosnNum_A1);
            ZYPEZDItemValueSetter.setValue(insInvLineMsg.dsOrdLineCatgCd, bizMsg.A.no(i).dsOrdLineCatgCd_A1);
            // TODO QC#52453 Option Start ->
//            ZYPEZDItemValueSetter.setValue(insInvLineMsg.coaAfflCd, bizMsg.A.no(i).coaAfflCd_A1);
//            ZYPEZDItemValueSetter.setValue(insInvLineMsg.custIssPoDt, bizMsg.A.no(i).custIssPoDt_A1);
//            ZYPEZDItemValueSetter.setValue(insInvLineMsg.csmpInvProcStsCd, bizMsg.A.no(i).csmpInvProcStsCd_A1);
            // TODO QC#52453 Option End <-
            // END 2019/08/07 S.Takami [QC#52453,ADD]

            invLineList.add(insInvLineMsg);
        }

        INV_LINETMsg[] invLineTmsgs = new INV_LINETMsg[invLineList.size()];
        for (int i = 0; i < invLineList.size(); i++) {
            INV_LINETMsg invLineTmsg = invLineList.get(i);
            invLineTmsgs[i] = invLineTmsg;
        }

        insertCnt = S21FastTBLAccessor.insert(invLineTmsgs);
        if (invLineTmsgs.length != insertCnt) {
            bizMsg.setMessageInfo("NFCM0861E", new String[]{"INV_LINE"});
            return false;
        }

        return isSuccess;
    }

    /**
     * updateInvoice_INV_PRMO_INFO
     * @param cMsg
     * @param sMsg
     * @return
     */
    public static boolean updateInvoice_INV_PRMO_INFO(EZDCMsg cMsg, EZDSMsg sMsg) {

        boolean isSuccess = true;
        int insertCnt = 0;

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        /************************************************/
        /* remove INV_PRMO_INFO                         */
        /************************************************/
        if(!NFCL3000CommonLogic.removeInvPrmoInfo(bizMsg)) {
            return false;
        }

        // START 2016/07/20 S.Yoshida [QC#12149,ADD]
        String invTpCd = bizMsg.invTpCd_H1.getValue();
        // END   2016/07/20 S.Yoshida [QC#12149,ADD]

        /************************************************/
        /* insert INV_PRMO_INFO                         */
        /************************************************/
        final List<INV_PRMO_INFOTMsg> invPrmoInfoList = new ArrayList<INV_PRMO_INFOTMsg>();
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            INV_PRMO_INFOTMsg insInvPrmoInfoMsg = new INV_PRMO_INFOTMsg();

            BigDecimal invPrmoInfoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.INV_PRMO_INFO_SQ);
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.invPrmoInfoPk, invPrmoInfoPk);

            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.invNum, bizMsg.invNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.invBolLineNum, bizMsg.A.no(i).invBolLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.invLineNum, bizMsg.A.no(i).invLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.invLineSubNum, bizMsg.A.no(i).invLineSubNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.invLineSubTrxNum, bizMsg.A.no(i).invLineSubTrxNum_A1.getValue());

            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.prcDt, bizMsg.invDt_H1.getValue());

            // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.prmoQty, bizMsg.A.no(i).shipQty_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.dealUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.dealLastNetUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.dealNetAmt, bizMsg.A.no(i).invLineDealNetAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.dealDiscAmt, BigDecimal.ZERO);
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.dealPrmoNetUnitPrcAmt, BigDecimal.ZERO);
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.dealPerUnitFixAmt, BigDecimal.ZERO);
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.dealSlsPctNum, BigDecimal.ZERO);
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.funcUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.funcLastNetUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.funcNetAmt, bizMsg.A.no(i).invLineDealNetAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.funcDiscAmt, BigDecimal.ZERO);
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.funcPrmoNetUnitPrcAmt, BigDecimal.ZERO);
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.funcPerUnitFixAmt, BigDecimal.ZERO);
//            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.invPrmoInfoSqNum, FIXED_INV_PRMO_INFO_SEQ_NUM);
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.prmoQty, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).shipQty_A1));
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.dealUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.dealLastNetUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.dealNetAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invLineDealNetAmt_A1));
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.dealDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.dealPrmoNetUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.dealPerUnitFixAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.dealSlsPctNum, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.funcUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.funcLastNetUnitPrcAmt, bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.funcNetAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).invLineDealNetAmt_A1));
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.funcDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.funcPrmoNetUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.funcPerUnitFixAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoMsg.invPrmoInfoSqNum, FIXED_INV_PRMO_INFO_SEQ_NUM);
            // END   2016/07/20 S.Yoshida [QC#12149,MOD]

            invPrmoInfoList.add(insInvPrmoInfoMsg);
        }

        INV_PRMO_INFOTMsg[] invPrmoInfoTmsgs = new INV_PRMO_INFOTMsg[invPrmoInfoList.size()];
        for (int i = 0; i < invPrmoInfoList.size(); i++) {
            INV_PRMO_INFOTMsg invPrmoInfoTmsg = invPrmoInfoList.get(i);
            invPrmoInfoTmsgs[i] = invPrmoInfoTmsg;
        }

        insertCnt = S21FastTBLAccessor.insert(invPrmoInfoTmsgs);
        if (invPrmoInfoTmsgs.length != insertCnt) {
            bizMsg.setMessageInfo("NFCM0861E", new String[]{"INV_PRMO_INFO"});
            return false;
        }

        return isSuccess;
    }

    /**
     * updateInvoice_DS_INV_SLS_CR
     * @param cMsg
     * @param sMsg
     * @return
     */
    public static boolean updateInvoice_DS_INV_SLS_CR(EZDCMsg cMsg, EZDSMsg sMsg) {

        boolean isSuccess = true;
        int insertCnt = 0;

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;
        NFCL3000SMsg globalMsg = (NFCL3000SMsg) sMsg;

        /************************************************/
        /* remove DS_INV_SLS_CR                         */
        /************************************************/
        if(!NFCL3000CommonLogic.removeDsInvSlsCr(bizMsg)) {
            return false;
        }

        // START 2016/07/20 S.Yoshida [QC#12149,ADD]
        String invTpCd = bizMsg.invTpCd_H1.getValue();
        // END   2016/07/20 S.Yoshida [QC#12149,ADD]

        /************************************************/
        /* insert DS_INV_SLS_CR                         */
        /************************************************/
        int scale = bizMsg.aftDeclPntDigitNum.getValueInt();
        final List<DS_INV_SLS_CRTMsg> dsInvSlsCrList = new ArrayList<DS_INV_SLS_CRTMsg>();
        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            DS_INV_SLS_CRTMsg insDsInvSlsCrMsg = new DS_INV_SLS_CRTMsg();

            BigDecimal dsInvSlsCrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_INV_SLS_CR_SQ);
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.dsInvSlsCrPk, dsInvSlsCrPk);

            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.invNum, bizMsg.invNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.invBolLineNum, bizMsg.B.no(i).invBolLineNum_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.invLineNum, bizMsg.B.no(i).invLineNum_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.invLineSubNum, bizMsg.B.no(i).invLineSubNum_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.invTrxLineSubNum, bizMsg.B.no(i).invTrxLineSubNum_B1.getValue());

            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.mdseCd, bizMsg.B.no(i).mdseCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.invLineSplTpCd, bizMsg.B.no(i).invLineSplTpCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.invLineSplPct, bizMsg.B.no(i).invLineSplPct_B2.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.slsRepTocCd, bizMsg.B.no(i).slsRepTocCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.slsRepCrPct, bizMsg.B.no(i).slsRepCrPct_B2.getValue());

            // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.dealSlsCrAmt, bizMsg.B.no(i).dealSlsCrAmt_B1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.funcSlsCrAmt, bizMsg.B.no(i).dealSlsCrAmt_B1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.dealSlsCrAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.B.no(i).dealSlsCrAmt_B1));
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.funcSlsCrAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.B.no(i).dealSlsCrAmt_B1));
            // END   2016/07/20 S.Yoshida [QC#12149,MOD]

            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.dealCcyCd, bizMsg.stdCcyCd.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.dfrdAcctgRuleCd, bizMsg.B.no(i).dfrdAcctgRuleCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.dfrdAcctgRuleDurnAot, bizMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.getValue());

            if(bizMsg.B.no(i).dfrdRevFlg_B1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.dealOrigDfrdAmt, bizMsg.B.no(i).dealSlsCrAmt_B1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.dealOrigDfrdAmt, bizMsg.B.no(i).dealSlsCrAmt_B1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.dealDfrdBalAmt, bizMsg.B.no(i).dealSlsCrAmt_B1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.funcDfrdBalAmt, bizMsg.B.no(i).dealSlsCrAmt_B1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.dealOrigDfrdAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.B.no(i).dealSlsCrAmt_B1));
                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.funcOrigDfrdAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.B.no(i).dealSlsCrAmt_B1));
                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.dealDfrdBalAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.B.no(i).dealDfrdBalAmt_B1));
                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.funcDfrdBalAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.B.no(i).funcDfrdBalAmt_B1));
                // END   2016/07/20 S.Yoshida [QC#12149,MOD]

                // START 2016/07/20 S.Yoshida [QC#12149,MOD]
//                BigDecimal schdRevAmt = bizMsg.B.no(i).dealSlsCrAmt_B1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg));
                BigDecimal schdRevAmt = NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.B.no(i).dealSlsCrAmt_B1);
                // END   2016/07/20 S.Yoshida [QC#12149,MOD]

                if (!ZYPCommonFunc.hasValue(bizMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.getValue()) || BigDecimal.ZERO.equals(bizMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dfrdAcctgRuleDurnAot_B1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.dealSchdRevAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.funcSchdRevAmt, BigDecimal.ZERO);
                } else {
                    schdRevAmt = schdRevAmt.divide(getAmount(bizMsg.B.no(i).dfrdAcctgRuleDurnAot_B1), scale, BigDecimal.ROUND_HALF_UP);
                    ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.dealSchdRevAmt, schdRevAmt);
                    ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.funcSchdRevAmt, schdRevAmt);
                }
            }

            if(bizMsg.B.no(i).dfrdRevFlg_B1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.durnStartDt, bizMsg.B.no(i).durnStartDt_B1.getValue());
                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.firstRevRecogDt, bizMsg.B.no(i).firstRevRecogDt_B1.getValue());
                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.revRecogCnt, bizMsg.B.no(i).revRecogCnt_B1);
                ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.revRecogProcStsCd, bizMsg.B.no(i).revRecogProcStsCd_B1);

                if (bizMsg.B.no(i).durnStartDt_B1.getValue().compareTo(bizMsg.procDt.getValue()) < 0) {
                    ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.nextRevRecogDt, bizMsg.procDt.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.nextRevRecogDt, bizMsg.B.no(i).durnStartDt_B1.getValue());
                }
            }

            S21_ORGTMsg s21OrgTmsg = new S21_ORGTMsg();
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(s21OrgTmsg.tocCd,      bizMsg.B.no(i).slsRepTocCd_B1.getValue());
            s21OrgTmsg = (S21_ORGTMsg) S21CacheTBLAccessor.findByKey(s21OrgTmsg);
            if (!S21CacheTBLAccessor.RTNCD_NORMAL.equals(s21OrgTmsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0864E", new String[]{"S21_ORG", bizMsg.B.no(i).slsRepTocCd_B1.getValue()});
                return false;
            }
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.slsRepBrCd, s21OrgTmsg.coaBrCd.getValue());

            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.manInvCratCmntTxt, bizMsg.B.no(i).manInvCratCmntTxt_B1.getValue());

            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.trxCd, bizMsg.B.no(i).trxCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.trxRsnCd, bizMsg.B.no(i).trxRsnCd_B1.getValue());

            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.coaCmpyCd, bizMsg.B.no(i).coaCmpyCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.coaBrCd, bizMsg.B.no(i).coaBrCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.coaCcCd, bizMsg.B.no(i).coaCcCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.coaAcctCd, bizMsg.B.no(i).coaAcctCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.coaProdCd, bizMsg.B.no(i).coaProdCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.coaChCd, bizMsg.B.no(i).coaChCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.coaAfflCd, bizMsg.B.no(i).coaAfflCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.coaProjCd, bizMsg.B.no(i).coaProjCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.coaExtnCd, bizMsg.B.no(i).coaExtnCd_B1.getValue());

            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.dsCpoConfigPk, bizMsg.B.no(i).dsCpoConfigPk_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.slsRepRoleTpCd, bizMsg.B.no(i).slsRepRoleTpCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrMsg.cpltRevRecogDt, bizMsg.B.no(i).cpltRevRecogDt_B1.getValue());

            dsInvSlsCrList.add(insDsInvSlsCrMsg);

            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsInvSlsCrPk_B1, dsInvSlsCrPk);
            NFCL3000CommonLogic.setDsInvSlsCrPkToAcctDist(bizMsg, dsInvSlsCrPk, i);
        }

        DS_INV_SLS_CRTMsg[] dsInvSlsCrTmsgs = new DS_INV_SLS_CRTMsg[dsInvSlsCrList.size()];
        for (int i = 0; i < dsInvSlsCrList.size(); i++) {
            DS_INV_SLS_CRTMsg dsInvSlsCrTmsg = dsInvSlsCrList.get(i);
            dsInvSlsCrTmsgs[i] = dsInvSlsCrTmsg;
        }

        insertCnt = S21FastTBLAccessor.insert(dsInvSlsCrTmsgs);
        if (dsInvSlsCrTmsgs.length != insertCnt) {
            bizMsg.setMessageInfo("NFCM0861E", new String[]{"DS_INV_SLS_CR"});
            return false;
        }

        return isSuccess;
    }

    /**
     * setDsInvSlsCrPkToAcctDist
     * @param bizMsg
     * @param dsInvSlsCrPk
     * @param idx
     * @return
     */
    public static void setDsInvSlsCrPkToAcctDist(NFCL3000CMsg bizMsg, BigDecimal dsInvSlsCrPk, int idx) {

        String xxLineNum = bizMsg.B.no(idx).xxLineNum_B1.getValue();
        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if(xxLineNum.equals(bizMsg.C.no(i).xxLineNum_C1.getValue())) {
                bizMsg.C.no(i).dsInvSlsCrPk_C1.setValue(dsInvSlsCrPk);
            }
        }
    }

    /**
     * updateInvoice_DS_INV_LINE_TAX_DTL
     * @param cMsg
     * @param sMsg
     * @return
     */
    public static boolean updateInvoice_DS_INV_LINE_TAX_DTL(EZDCMsg cMsg, EZDSMsg sMsg) {

        boolean isSuccess = true;
        int insertCnt = 0;

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        /************************************************/
        /* remove DS_INV_LINE_TAX_DTL                   */
        /************************************************/
        if(!NFCL3000CommonLogic.removeDsInvLineTaxDtl(bizMsg)) {
            return false;
        }

        /************************************************/
        /* insert DS_INV_LINE_TAX_DTL                   */
        /************************************************/
        final List<DS_INV_LINE_TAX_DTLTMsg> dsInvLineTaxDtlList = new ArrayList<DS_INV_LINE_TAX_DTLTMsg>();
        BigDecimal dsInvLineTaxDtlPk = BigDecimal.ZERO;
        // START 2016/07/20 S.Fujita [QC#12006,ADD]
        String invTpCd = bizMsg.invTpCd_H1.getValue();
        // END   2016/07/20 S.Fujita [QC#12006,ADD]

// START 2018/05/22 Y.Matsui [QC#21841,ADD]
//        // Invoice BOL
//        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
//            // State Tax
//            DS_INV_LINE_TAX_DTLTMsg insDsInvLineTaxDtlMsg_Freight_01 = new DS_INV_LINE_TAX_DTLTMsg();
//            dsInvLineTaxDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_INV_LINE_TAX_DTL_SQ);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.dsInvLineTaxDtlPk, dsInvLineTaxDtlPk);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.invNum, bizMsg.invNum_H1.getValue());
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.invBolLineNum, bizMsg.D.no(i).invBolLineNum_D1.getValue());
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.invLineNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.invLineSubNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.invTrxLineSubNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.dsSlsTaxTpCd, DS_SLS_TAX_TP.FREIGHT_STATE_TAX);
//            // START 2016/07/20 S.Fujita [QC#12006,MOD]
////            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.dealSlsTaxAmt, bizMsg.D.no(i).dealSlsTaxAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
////            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.funcSlsTaxAmt, bizMsg.D.no(i).dealSlsTaxAmt_D1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.dealSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).dealSlsTaxAmt_D1));
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.funcSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).dealSlsTaxAmt_D1));
//            // END   2016/07/20 S.Fujita [QC#12006,MOD]
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.slsTaxPct, bizMsg.D.no(i).slsTaxPct_D1.getValue());
//
//            // START 2017/03/09 T.Murai [QC#17901,ADD]
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.taxAreaId, bizMsg.D.no(i).taxAreaId_D1.getValue());
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_01.taxRsltDescTxt, bizMsg.D.no(i).taxRsltDescTxt_D1.getValue());
//            // END   2017/03/09 T.Murai [QC#17901,ADD]
//            // START 2016/07/29 S.Fujita [QC#12503,MOD]
//            if(ZYPCommonFunc.hasValue(insDsInvLineTaxDtlMsg_Freight_01.funcSlsTaxAmt) && ZYPCommonFunc.hasValue(insDsInvLineTaxDtlMsg_Freight_01.slsTaxPct) && insDsInvLineTaxDtlMsg_Freight_01.funcSlsTaxAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
//                dsInvLineTaxDtlList.add(insDsInvLineTaxDtlMsg_Freight_01);
//            }
//            // END   2016/07/29 S.Fujita [QC#12503,MOD]
//
//            // County Tax
//            DS_INV_LINE_TAX_DTLTMsg insDsInvLineTaxDtlMsg_Freight_02 = new DS_INV_LINE_TAX_DTLTMsg();
//            dsInvLineTaxDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_INV_LINE_TAX_DTL_SQ);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.dsInvLineTaxDtlPk, dsInvLineTaxDtlPk);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.invNum, bizMsg.invNum_H1.getValue());
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.invBolLineNum, bizMsg.D.no(i).invBolLineNum_D1.getValue());
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.invLineNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.invLineSubNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.invTrxLineSubNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.dsSlsTaxTpCd, DS_SLS_TAX_TP.FREIGHT_COUNTY_TAX);
//            // START 2016/07/20 S.Fujita [QC#12006,MOD]
////            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.dealSlsTaxAmt, bizMsg.D.no(i).dealSlsTaxAmt_D2.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
////            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.funcSlsTaxAmt, bizMsg.D.no(i).dealSlsTaxAmt_D2.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.dealSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).dealSlsTaxAmt_D2));
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.funcSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).dealSlsTaxAmt_D2));
//            // END   2016/07/20 S.Fujita [QC#12006,MOD]
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.slsTaxPct, bizMsg.D.no(i).slsTaxPct_D2.getValue());
//
//            // START 2017/03/09 T.Murai [QC#17901,ADD]
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.taxAreaId, bizMsg.D.no(i).taxAreaId_D1.getValue());
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_02.taxRsltDescTxt, bizMsg.D.no(i).taxRsltDescTxt_D2.getValue());
//            // END   2017/03/09 T.Murai [QC#17901,ADD]
//            // START 2016/07/29 S.Fujita [QC#12503,MOD]
//            if(ZYPCommonFunc.hasValue(insDsInvLineTaxDtlMsg_Freight_02.funcSlsTaxAmt) && ZYPCommonFunc.hasValue(insDsInvLineTaxDtlMsg_Freight_02.slsTaxPct) && insDsInvLineTaxDtlMsg_Freight_02.funcSlsTaxAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
//                dsInvLineTaxDtlList.add(insDsInvLineTaxDtlMsg_Freight_02);
//            }
//            // END   2016/07/29 S.Fujita [QC#12503,MOD]
//
//            // City Tax
//            DS_INV_LINE_TAX_DTLTMsg insDsInvLineTaxDtlMsg_Freight_03 = new DS_INV_LINE_TAX_DTLTMsg();
//            dsInvLineTaxDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_INV_LINE_TAX_DTL_SQ);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.dsInvLineTaxDtlPk, dsInvLineTaxDtlPk);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.invNum, bizMsg.invNum_H1.getValue());
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.invBolLineNum, bizMsg.D.no(i).invBolLineNum_D1.getValue());
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.invLineNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.invLineSubNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.invTrxLineSubNum, FREIGHT_TAX_LINE_NUM);
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.dsSlsTaxTpCd, DS_SLS_TAX_TP.FREIGHT_CITY_TAX);
//            // START 2016/07/20 S.Fujita [QC#12006,MOD]
////            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.dealSlsTaxAmt, bizMsg.D.no(i).dealSlsTaxAmt_D3.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
////            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.funcSlsTaxAmt, bizMsg.D.no(i).dealSlsTaxAmt_D3.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.dealSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).dealSlsTaxAmt_D3));
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.funcSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.D.no(i).dealSlsTaxAmt_D3));
//            // END   2016/07/20 S.Fujita [QC#12006,MOD]
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.slsTaxPct, bizMsg.D.no(i).slsTaxPct_D3.getValue());
//
//            // START 2017/03/09 T.Murai [QC#17901,ADD]
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.taxAreaId, bizMsg.D.no(i).taxAreaId_D1.getValue());
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Freight_03.taxRsltDescTxt, bizMsg.D.no(i).taxRsltDescTxt_D3.getValue());
//            // END   2017/03/09 T.Murai [QC#17901,ADD]
//            // START 2016/07/29 S.Fujita [QC#12503,MOD]
//            if(ZYPCommonFunc.hasValue(insDsInvLineTaxDtlMsg_Freight_03.funcSlsTaxAmt) && ZYPCommonFunc.hasValue(insDsInvLineTaxDtlMsg_Freight_03.slsTaxPct) && insDsInvLineTaxDtlMsg_Freight_03.funcSlsTaxAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
//                dsInvLineTaxDtlList.add(insDsInvLineTaxDtlMsg_Freight_03);
//            }
//            // END   2016/07/29 S.Fujita [QC#12503,MOD]
//        }
// END   2018/05/22 Y.Matsui [QC#21841,ADD]

        // Invoice Line
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // State Tax
            DS_INV_LINE_TAX_DTLTMsg insDsInvLineTaxDtlMsg_Sales_01 = new DS_INV_LINE_TAX_DTLTMsg();
            dsInvLineTaxDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_INV_LINE_TAX_DTL_SQ);
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.dsInvLineTaxDtlPk, dsInvLineTaxDtlPk);
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.invNum, bizMsg.invNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.invBolLineNum, bizMsg.A.no(i).invBolLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.invLineNum, bizMsg.A.no(i).invLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.invLineSubNum, bizMsg.A.no(i).invLineSubNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.invTrxLineSubNum, bizMsg.A.no(i).invLineSubTrxNum_A1.getValue());
            // START 2018/06/01 Y.Matsui [QC#21841,MOD]
            if (isFreightInvoiceLine(bizMsg,  bizMsg.A.no(i))) {
                ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.dsSlsTaxTpCd, DS_SLS_TAX_TP.FREIGHT_STATE_TAX);
            } else {
                ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.dsSlsTaxTpCd, DS_SLS_TAX_TP.STATE_TAX);
            }
            // END   2018/06/01 Y.Matsui [QC#21841,MOD]
            // START 2016/07/20 S.Fujita [QC#12006,MOD]
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.dealSlsTaxAmt, bizMsg.A.no(i).dealSlsTaxAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.funcSlsTaxAmt, bizMsg.A.no(i).dealSlsTaxAmt_A1.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.dealSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).dealSlsTaxAmt_A1));
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.funcSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).dealSlsTaxAmt_A1));
            // END   2016/07/20 S.Fujita [QC#12006,MOD]
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.slsTaxPct, bizMsg.A.no(i).slsTaxPct_A1.getValue());

            // START 2017/03/09 T.Murai [QC#17901,ADD]
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.taxAreaId, bizMsg.A.no(i).taxAreaId_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_01.taxRsltDescTxt, bizMsg.A.no(i).taxRsltDescTxt_A1.getValue());
            // END   2017/03/09 T.Murai [QC#17901,ADD]
            // START 2016/07/29 S.Fujita [QC#12503,MOD]
            if(ZYPCommonFunc.hasValue(insDsInvLineTaxDtlMsg_Sales_01.funcSlsTaxAmt) && ZYPCommonFunc.hasValue(insDsInvLineTaxDtlMsg_Sales_01.slsTaxPct) && insDsInvLineTaxDtlMsg_Sales_01.funcSlsTaxAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
                dsInvLineTaxDtlList.add(insDsInvLineTaxDtlMsg_Sales_01);
            }
            // END   2016/07/29 S.Fujita [QC#12503,MOD]

            // County Tax
            DS_INV_LINE_TAX_DTLTMsg insDsInvLineTaxDtlMsg_Sales_02 = new DS_INV_LINE_TAX_DTLTMsg();
            dsInvLineTaxDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_INV_LINE_TAX_DTL_SQ);
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.dsInvLineTaxDtlPk, dsInvLineTaxDtlPk);
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.invNum, bizMsg.invNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.invBolLineNum, bizMsg.A.no(i).invBolLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.invLineNum, bizMsg.A.no(i).invLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.invLineSubNum, bizMsg.A.no(i).invLineSubNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.invTrxLineSubNum, bizMsg.A.no(i).invLineSubTrxNum_A1.getValue());
            // START 2018/06/01 Y.Matsui [QC#21841,MOD]
            if (isFreightInvoiceLine(bizMsg,  bizMsg.A.no(i))) {
                ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.dsSlsTaxTpCd, DS_SLS_TAX_TP.FREIGHT_COUNTY_TAX);
            } else {
                ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.dsSlsTaxTpCd, DS_SLS_TAX_TP.COUNTY_TAX);
            }
            // END   2018/06/01 Y.Matsui [QC#21841,MOD]
            // START 2016/07/20 S.Fujita [QC#12006,MOD]
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.dealSlsTaxAmt, bizMsg.A.no(i).dealSlsTaxAmt_A2.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.funcSlsTaxAmt, bizMsg.A.no(i).dealSlsTaxAmt_A2.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.dealSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).dealSlsTaxAmt_A2));
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.funcSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).dealSlsTaxAmt_A2));
            // END   2016/07/20 S.Fujita [QC#12006,MOD]
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.slsTaxPct, bizMsg.A.no(i).slsTaxPct_A2.getValue());

            // START 2017/03/09 T.Murai [QC#17901,ADD]
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.taxAreaId, bizMsg.A.no(i).taxAreaId_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_02.taxRsltDescTxt, bizMsg.A.no(i).taxRsltDescTxt_A2.getValue());
            // END   2017/03/09 T.Murai [QC#17901,ADD]
            // START 2016/07/29 S.Fujita [QC#12503,MOD]
            if(ZYPCommonFunc.hasValue(insDsInvLineTaxDtlMsg_Sales_02.funcSlsTaxAmt) && ZYPCommonFunc.hasValue(insDsInvLineTaxDtlMsg_Sales_02.slsTaxPct) && insDsInvLineTaxDtlMsg_Sales_02.funcSlsTaxAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
                dsInvLineTaxDtlList.add(insDsInvLineTaxDtlMsg_Sales_02);
            }
            // END   2016/07/29 S.Fujita [QC#12503,MOD]

            // City Tax
            DS_INV_LINE_TAX_DTLTMsg insDsInvLineTaxDtlMsg_Sales_03 = new DS_INV_LINE_TAX_DTLTMsg();
            dsInvLineTaxDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_INV_LINE_TAX_DTL_SQ);
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.dsInvLineTaxDtlPk, dsInvLineTaxDtlPk);
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.invNum, bizMsg.invNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.invBolLineNum, bizMsg.A.no(i).invBolLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.invLineNum, bizMsg.A.no(i).invLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.invLineSubNum, bizMsg.A.no(i).invLineSubNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.invTrxLineSubNum, bizMsg.A.no(i).invLineSubTrxNum_A1.getValue());
            // START 2018/06/01 Y.Matsui [QC#21841,MOD]
            if (isFreightInvoiceLine(bizMsg,  bizMsg.A.no(i))) {
                ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.dsSlsTaxTpCd, DS_SLS_TAX_TP.FREIGHT_CITY_TAX);
            } else {
                ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.dsSlsTaxTpCd, DS_SLS_TAX_TP.CITY_TAX);
            }
            // END   2018/06/01 Y.Matsui [QC#21841,MOD]
            // START 2016/07/20 S.Fujita [QC#12006,MOD]
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.dealSlsTaxAmt, bizMsg.A.no(i).dealSlsTaxAmt_A3.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
//            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.funcSlsTaxAmt, bizMsg.A.no(i).dealSlsTaxAmt_A3.getValue().multiply(NFCL3000CommonLogic.getSignByInvtp(bizMsg)));
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.dealSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).dealSlsTaxAmt_A3));
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.funcSlsTaxAmt, NFCL3000CommonLogic.getSignByInvtp(invTpCd, bizMsg.A.no(i).dealSlsTaxAmt_A3));
            // END   2016/07/20 S.Fujita [QC#12006,MOD]
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.slsTaxPct, bizMsg.A.no(i).slsTaxPct_A3.getValue());

            // START 2017/03/09 T.Murai [QC#17901,ADD]
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.taxAreaId, bizMsg.A.no(i).taxAreaId_A1.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvLineTaxDtlMsg_Sales_03.taxRsltDescTxt, bizMsg.A.no(i).taxRsltDescTxt_A3.getValue());
            // END   2017/03/09 T.Murai [QC#17901,ADD]
            // START 2016/07/29 S.Fujita [QC#12503,MOD]
            if(ZYPCommonFunc.hasValue(insDsInvLineTaxDtlMsg_Sales_03.funcSlsTaxAmt) && ZYPCommonFunc.hasValue(insDsInvLineTaxDtlMsg_Sales_03.slsTaxPct) && insDsInvLineTaxDtlMsg_Sales_03.funcSlsTaxAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
                dsInvLineTaxDtlList.add(insDsInvLineTaxDtlMsg_Sales_03);
            }
            // END   2016/07/29 S.Fujita [QC#12503,MOD]
        }

        DS_INV_LINE_TAX_DTLTMsg[] dsInvLineTaxDtlTmsgs = new DS_INV_LINE_TAX_DTLTMsg[dsInvLineTaxDtlList.size()];
        for (int i = 0; i < dsInvLineTaxDtlList.size(); i++) {
            DS_INV_LINE_TAX_DTLTMsg dsInvLineTaxDtlTmsg = dsInvLineTaxDtlList.get(i);
            dsInvLineTaxDtlTmsgs[i] = dsInvLineTaxDtlTmsg;
        }

        // START 2016/08/03 S.Fujita [QC#12870,MOD]
        if (dsInvLineTaxDtlTmsgs.length != 0) {
            insertCnt = S21FastTBLAccessor.insert(dsInvLineTaxDtlTmsgs);
            if (dsInvLineTaxDtlTmsgs.length != insertCnt) {
                bizMsg.setMessageInfo("NFCM0861E", new String[]{"DS_INV_LINE_TAX_DTL"});
                return false;
            }
        }
        // END   2016/08/03 S.Fujita [QC#12870,MOD]

        return isSuccess;
    }

    /**
     * getCountyName
     * 
     * @param bizMsg NFCL3070CMsg
     * @param cntyPk BigDecimal
     * @return cntyNm String
     */
    public static String getCountyName(NFCL3000CMsg bizMsg, BigDecimal cntyPk) {

        String cntyNm = null;
        CNTYTMsg cntyTMsg = new CNTYTMsg();
        ZYPEZDItemValueSetter.setValue(cntyTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(cntyTMsg.cntyPk, cntyPk);
        cntyTMsg = (CNTYTMsg) S21CacheTBLAccessor.findByKey(cntyTMsg);
        if (cntyTMsg != null) {
            cntyNm = cntyTMsg.cntyNm.getValue();
        }
        return cntyNm;
    }

    /**
     * removeDsInvSlsCr
     * @param bizMsg NFCL3000CMsg
     * @return
     */
    public static boolean removeDsInvSlsCr(NFCL3000CMsg bizMsg) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        S21SsmEZDResult dsInvSlsCr = NFCL3000Query.getInstance().searchDsInvSlsCrList(bizMsg, ssmParam);
        if (dsInvSlsCr.isCodeNormal()) {
            List<Map> dsInvSlsCrList = (List<Map>) dsInvSlsCr.getResultObject();
            for(int i = 0; i < dsInvSlsCrList.size(); i++) {
                
                Map dsInvSlsCrData = dsInvSlsCrList.get(i);
                BigDecimal dsInvSlsCrPk = (BigDecimal)dsInvSlsCrData.get(DS_INV_SLS_CR_PK);
                DS_INV_SLS_CRTMsg rmvMsg = new DS_INV_SLS_CRTMsg();
                ZYPEZDItemValueSetter.setValue(rmvMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rmvMsg.dsInvSlsCrPk, dsInvSlsCrPk);
                EZDTBLAccessor.remove(rmvMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rmvMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0863E", new String[]{"DS_INV_SLS_CR"});
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * removeInvPrmoInfo
     * @param bizMsg NFCL3000CMsg
     * @return
     */
    public static boolean removeInvPrmoInfo(NFCL3000CMsg bizMsg) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        S21SsmEZDResult invPrmoInfo = NFCL3000Query.getInstance().searchInvPrmoInfoList(bizMsg, ssmParam);
        if (invPrmoInfo.isCodeNormal()) {
            List<Map> invPrmoInfoList = (List<Map>) invPrmoInfo.getResultObject();
            for(int i = 0; i < invPrmoInfoList.size(); i++) {

                Map invPrmoInfoData = invPrmoInfoList.get(i);
                BigDecimal invPrmoInfoPk = (BigDecimal)invPrmoInfoData.get(INV_PRMO_INFO_PK);
                INV_PRMO_INFOTMsg rmvMsg = new INV_PRMO_INFOTMsg();
                ZYPEZDItemValueSetter.setValue(rmvMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rmvMsg.invPrmoInfoPk, invPrmoInfoPk);
                EZDTBLAccessor.remove(rmvMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rmvMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0863E", new String[]{"INV_PRMO_INFO"});
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * removeAjeInvAcctDistDfrd
     * @param bizMsg NFCL3000CMsg
     * @return
     */
    public static boolean removeAjeInvAcctDistDfrd(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        ssmParam.put("dfrd", AJE_INV_ACCT_CLS.REV_EARN_TMPLT);
        S21SsmEZDResult ajeInvAcctDistDfrd = NFCL3000Query.getInstance().searchAjeInvAcctDistDfrdList(bizMsg, ssmParam);
        if (ajeInvAcctDistDfrd.isCodeNormal()) {
            List<Map> ajeInvAcctDistDfrdList = (List<Map>) ajeInvAcctDistDfrd.getResultObject();
            for(int i = 0; i < ajeInvAcctDistDfrdList.size(); i++) {

                Map ajeInvAcctDistDfrdData = ajeInvAcctDistDfrdList.get(i);
                BigDecimal ajeInvAcctDistPk = (BigDecimal)ajeInvAcctDistDfrdData.get(AJE_INV_ACCT_DIST_PK);
                AJE_INV_ACCT_DISTTMsg rmvMsg = new AJE_INV_ACCT_DISTTMsg();
                ZYPEZDItemValueSetter.setValue(rmvMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rmvMsg.ajeInvAcctDistPk, ajeInvAcctDistPk);
                EZDTBLAccessor.remove(rmvMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rmvMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0863E", new String[]{"AJE_INV_ACCT_DIST"});
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * removeDsInvLineTaxDtl
     * @param bizMsg NFCL3000CMsg
     * @return
     */
    public static boolean removeDsInvLineTaxDtl(NFCL3000CMsg bizMsg) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        S21SsmEZDResult dsInvLineTaxDtl = NFCL3000Query.getInstance().searchDsInvLineTaxDtlList(bizMsg, ssmParam);
        if (dsInvLineTaxDtl.isCodeNormal()) {
            List<Map> dsInvLineTaxDtlList = (List<Map>) dsInvLineTaxDtl.getResultObject();
            for(int i = 0; i < dsInvLineTaxDtlList.size(); i++) {
                
                Map dsInvLineTaxDtlData = dsInvLineTaxDtlList.get(i);
                BigDecimal dsInvLineTaxDtlPk = (BigDecimal)dsInvLineTaxDtlData.get(DS_INV_LINE_TAX_DTL_PK);
                DS_INV_LINE_TAX_DTLTMsg rmvMsg = new DS_INV_LINE_TAX_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(rmvMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rmvMsg.dsInvLineTaxDtlPk, dsInvLineTaxDtlPk);
                EZDTBLAccessor.remove(rmvMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rmvMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0863E", new String[]{"DS_INV_LINE_TAX_DTL"});
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * setupMdseCd
     * @param bizMsg
     */
    public static void setupMdseCd(NFCL3000CMsg bizMsg) {
        // START 2019/05/27 S.Takami [QC#50541,DEL]
//        // START 2018/09/04 E.Kameishi [QC#27848,MOD]
//        if (NFCL3000CommonLogic.isManualInvoice(bizMsg) && ZYPCommonFunc.hasValue(bizMsg.invNum_H1)) {
//            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
//                if (hasValue(bizMsg.A.no(i).invLineNum_A1)) {
//                    for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
//                        if (bizMsg.A.no(i).invLineNum_A1.equals(bizMsg.B.no(j).invLineNum_B1.getValue())
//                                && bizMsg.A.no(i).invBolLineNum_A1.equals(bizMsg.B.no(j).invBolLineNum_B1.getValue())) {
//                            DS_INV_SLS_CRTMsg tMsg = new DS_INV_SLS_CRTMsg();
//                            tMsg.setSQLID("002");
//                            tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//                            tMsg.setConditionValue("invNum01", bizMsg.invNum_H1.getValue());
//                            tMsg.setConditionValue("invLineNum01", bizMsg.A.no(j).invLineNum_A1.getValue());
//                            tMsg.setConditionValue("invBolLineNum01", bizMsg.A.no(j).invBolLineNum_A1.getValue());
//                            tMsg.setConditionValue("invLineSubNum01", bizMsg.A.no(j).invLineSubNum_A1.getValue());
//                            tMsg.setConditionValue("invTrxLineSubNum01", bizMsg.A.no(j).invLineSubTrxNum_A1.getValue());
//                            
//                            DS_INV_SLS_CRTMsgArray dsInvSlsCrTMsgAry = (DS_INV_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
//                            
//                            if (dsInvSlsCrTMsgAry.length() == 0) {
//                                NFCL3000CommonLogic.setupMdseCd_Idx(bizMsg, i, j);
//                            }
//                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(j).mdseCd_B1, dsInvSlsCrTMsgAry.no(0).mdseCd.getValue());
//                        }
//                    }
//                } else {
//                    NFCL3000CommonLogic.setupMdseCd_Idx(bizMsg, i, i);
//                }
//            }
//        }
//        //for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
//        //    NFCL3000CommonLogic.setupMdseCd_Idx(bizMsg, i, i);
//        //}
//        // END 2018/09/04 E.Kameishi [QC#27848,MOD]
        // END 2019/05/27 S.Takami [QC#50541,DEL]
        // START 2019/05/27 S.Takami [QC#50541,ADD]
//        boolean isOrigSvcInv = S21StringUtil.isEquals(SYS_SRC.S21_SERVICE, bizMsg.sysSrcCd_OR.getValue());
        boolean hasOrigInvNum = ZYPCommonFunc.hasValue(bizMsg.origInvNum_E1);
        boolean isFinalized = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.fnlzInvFlg_H4.getValue());
        boolean isManInv = isManualInvoice(bizMsg);
        if (!isManInv || isFinalized) {
            return;
        }
        for(int idxLine = 0; idxLine < bizMsg.A.getValidCount(); idxLine++) {
            NFCL3000_ACMsg lineMsg = bizMsg.A.no(idxLine);
            // START 2019/05/31 S.Takami [QC#50633,ADD]
            boolean isModifiedItem = !S21StringUtil.isEquals(lineMsg.mdseCd_A1.getValue(), lineMsg.mdseCd_AB.getValue());
            if (hasOrigInvNum && !isModifiedItem) {
                continue;
            }
            // END 2019/05/31 S.Takami [QC#50633,ADD]
            for (int idxSlsCr = 0; idxSlsCr < bizMsg.B.getValidCount(); idxSlsCr++) {
                NFCL3000_BCMsg slsCrMsg = bizMsg.B.no(idxSlsCr);
                if (S21StringUtil.isEquals(lineMsg.invLineNum_A1.getValue(), slsCrMsg.invLineNum_B1.getValue())
                        && S21StringUtil.isEquals(lineMsg.invBolLineNum_A1.getValue(), slsCrMsg.invBolLineNum_B1.getValue())) {
                    // START 2019/05/31 S.Takami [QC#50633,DEL]
//                    if (hasOrigInvNum && hasOrigInvSlsCrLine(bizMsg, idxSlsCr)) {
//                        continue;
//                    }
                    // END 2019/05/31 S.Takami [QC#50633,DEL]
                    ZYPEZDItemValueSetter.setValue(slsCrMsg.mdseCd_B1, lineMsg.mdseCd_A1);
                }
            }
        }
        // END 2019/05/27 S.Takami [QC#50541,ADD]
    }

    // START 2019/05/27 S.Takami [QC#50541,DEL]
//    /**
//     * setupMdseCd_Idx
//     * @param bizMsg
//     */
//    public static void setupMdseCd_Idx(NFCL3000CMsg bizMsg, int aIdx, int bIdx) {
//        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bIdx).mdseCd_B1, bizMsg.A.no(aIdx).mdseCd_A1.getValue());
//    }
    // END 2019/05/27 S.Takami [QC#50541,DEL]

    // START 2016/06/20 S.Fujita [QC#9454,MOD]
    /**
     * createAjeInvAcctDistDfrd
     * @param bizMsg NFCL3000CMsg
     * @param globalMsg NFCL3000SMsg
     * @param dsInvSlsCr ResultSet
     * @param drCrTpCd String
     * @param resAjePtrn Map
     * @param ajeSqNum int
     * @param idx int
     * @throws SQLException
     * @return
     */
    public static boolean createAjeInvAcctDistDfrd(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg, ResultSet dsInvSlsCr, String drCrTpCd, Map<String, String> resAjePtrn, int ajeSqNum, int idx) throws SQLException {

        AJE_INV_ACCT_DISTTMsg ajeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();

        // Set Common Data and Amount Data
        setAjeInvAcctDistCommonValues(bizMsg, ajeInvAcctDist, dsInvSlsCr, resAjePtrn, drCrTpCd, ajeSqNum);

        // START 2016/09/26 S.Fujita [QC#13362,ADD]
        Boolean thruFlg = false;
        for(int i = 0; i < globalMsg.F.getValidCount(); i++) {
            if(globalMsg.F.no(i).xxLineNum_F1.getValue().equals(bizMsg.B.no(idx).xxLineNum_B1.getValue()) //
                    && AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(globalMsg.F.no(i).ajeInvAcctClsCd_F1.getValue())) {
                if (drCrTpCd.equals(globalMsg.F.no(i).drCrTpCd_F1.getValue())) {
                    // Set 9 Segments Data
                    setAjeInvAcctDist9SegmentsValuesByAcctInfo(globalMsg, ajeInvAcctDist, i);
                    thruFlg = true;
                    break;
                }
            }
        }
        // END   2016/09/26 S.Fujita [QC#13362,ADD]

        if(!thruFlg) {
            // Set 9 Segments Data
            if (!setAjeInvAcctDist9SegmentsValues(bizMsg, ajeInvAcctDist, dsInvSlsCr, resAjePtrn, drCrTpCd, bizMsg.C.no(0))) {
                return false;
            }
        }

        EZDTBLAccessor.create(ajeInvAcctDist);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ajeInvAcctDist.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0861E", new String[]{"AJE_INV_ACCT_DIST"});
            return false;
        }

        return true;
    }

    /**
     * Set Common Values
     * @param bizMsg NFCL3000CMsg
     * @param ajeInvAcctDist AJE_INV_ACCT_DISTTMsg
     * @param invSlsCr ResultSet
     * @param resAjePtrn Map
     * @param drCrTpCd String
     * @param ajeSqNum int
     * @throws SQLException
     */
    public static void setAjeInvAcctDistCommonValues(NFCL3000CMsg bizMsg, AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, ResultSet invSlsCr, Map<String, String> resAjePtrn, String drCrTpCd, int ajeSqNum) throws SQLException {

        BigDecimal ajeInvAcctDistSeqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_SQ);

        //START 2016/09/21 S.Yoshida [QC#11049,ADD]
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String procDt = bizMsg.procDt.getValue();
        String acctDt = invSlsCr.getString(ACCT_DT);
        String glDt = NFZC103001Common.getGlDt(glblCmpyCd, procDt, acctDt);
        //END   2016/09/21 S.Yoshida [QC#11049,ADD]

        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistPk, ajeInvAcctDistSeqNum);
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invNum, invSlsCr.getString(INV_NUM));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invBolLineNum, invSlsCr.getString(INV_BOL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invLineNum, invSlsCr.getString(INV_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invLineSubNum,  invSlsCr.getString(INV_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invLineSubTrxNum,  invSlsCr.getString(INV_TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistSqNum, String.format("%06d", ajeSqNum));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procDt, bizMsg.procDt.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctClsCd, resAjePtrn.get(AJE_LINE_IND_TP_CD));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.drCrTpCd, drCrTpCd);
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dealCcyCd, invSlsCr.getString(DEAL_CCY_CD));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.funcCcyCd, invSlsCr.getString(DEAL_CCY_CD));
        // START 2016/09/05 S.Fujita [QC#10156,MOD]
//        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistPct, calcAjeInvAcctDistPct(invSlsCr));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistPct, calcAjeInvAcctDistPct(invSlsCr, resAjePtrn.get(AJE_LINE_IND_TP_CD)));
        // END   2016/09/05 S.Fujita [QC#10156,MOD]
        //START 2016/09/21 S.Yoshida [QC#11049,MOD]
//        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.glDt, invSlsCr.getString(ACCT_DT));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.glDt, glDt);
        //END   2016/09/21 S.Yoshida [QC#11049,MOD]
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dsInvSlsCrPk, invSlsCr.getBigDecimal(DS_INV_SLS_CR_PK));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeOmIntfcPk, BigDecimal.ZERO);

        if(ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procStsCd, PROC_STS.COMPLEATED);
        } else {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procStsCd, PROC_STS.IN_COMPLETED);
        }

        // newly added fields
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.sysSrcCd, invSlsCr.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.sysSrcNm, invSlsCr.getString(SYS_SRC_NM));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxCd, resAjePtrn.get(TRX_CD));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxNm, resAjePtrn.get(TRX_NM));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxRsnCd, resAjePtrn.get(TRX_RSN_CD));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxRsnNm, resAjePtrn.get(TRX_RSN_NM));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpCd_01, resAjePtrn.get(AJE_PTRN_IND_TP_CD_01));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpNm_01, resAjePtrn.get(AJE_PTRN_IND_TP_NM_01));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlCd_01, resAjePtrn.get(AJE_PTRN_ACTL_CD_01));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlNm_01, resAjePtrn.get(AJE_PTRN_ACTL_NM_01));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpCd_02, resAjePtrn.get(AJE_PTRN_IND_TP_CD_02));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpNm_02, resAjePtrn.get(AJE_PTRN_IND_TP_NM_02));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlCd_02, resAjePtrn.get(AJE_PTRN_ACTL_CD_02));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlNm_02, resAjePtrn.get(AJE_PTRN_ACTL_NM_02));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpCd_03, resAjePtrn.get(AJE_PTRN_IND_TP_CD_03));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpNm_03, resAjePtrn.get(AJE_PTRN_IND_TP_NM_03));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlCd_03, resAjePtrn.get(AJE_PTRN_ACTL_CD_03));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlNm_03, resAjePtrn.get(AJE_PTRN_ACTL_NM_03));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlSrcCd, invSlsCr.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlSrcNm, invSlsCr.getString(SYS_SRC_NM));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlCatgCd, resAjePtrn.get(JRNL_CATG_CD));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlCatgNm, resAjePtrn.get(JRNL_CATG_NM));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeLineIdxNum, resAjePtrn.get(AJE_LINE_IDX_NUM));

        // START 2016/09/26 S.Fujita [QC#13362,MOD]
//        String invTpCd = invSlsCr.getString(INV_TP_CD);
//        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(DEAL_SLS_CR_AMT)));
//        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(FUNC_SLS_CR_AMT)));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, BigDecimal.ZERO);
        // END   2016/09/26 S.Fujita [QC#13362,MOD]

        if (DR_CR_TP_DEBIT.equals(drCrTpCd)) {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeLineIdxDescTxt, resAjePtrn.get(DR_AJE_LINE_IDX_DESC_TXT));
        } else {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeLineIdxDescTxt, resAjePtrn.get(CR_AJE_LINE_IDX_DESC_TXT));
        }

        // Because line indicator type of AJE pattern is "99", set the flag on.
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dfrdRevGlStrgFlg, ZYPConstant.FLG_ON_Y);
    }

    /**
     * calcAjeInvAcctDistPct
     * @param invSlsCr
     * @param ajeLineIndTpCd
     * @return
     * @throws SQLException
     */
    public static BigDecimal calcAjeInvAcctDistPct(ResultSet invSlsCr, String ajeLineIndTpCd) throws SQLException {

        BigDecimal invLineSplPct = BigDecimal.ZERO;
        BigDecimal slsRepCrPct = BigDecimal.ZERO;

        invLineSplPct = invSlsCr.getBigDecimal(INV_LINE_SPL_PCT);
        slsRepCrPct = invSlsCr.getBigDecimal(SLS_REP_CR_PCT);

        if (invLineSplPct == null || slsRepCrPct == null) {
            return null;
        }
        invLineSplPct = invLineSplPct.divide(new BigDecimal(100));
        slsRepCrPct = slsRepCrPct.divide(new BigDecimal(100));
        BigDecimal ajeInvAcctDistPct = invLineSplPct.multiply(slsRepCrPct);

        // START 2018/05/22 Y.Matsui [QC#21841,MOD]
        // START 2016/09/05 S.Fujita [QC#10156,MOD]
        if(AJE_INV_ACCT_CLS.REVENUE.equals(ajeLineIndTpCd) || AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(ajeLineIndTpCd) || AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(ajeLineIndTpCd) || AJE_INV_ACCT_CLS.FREIGHT.equals(ajeLineIndTpCd)){
            ajeInvAcctDistPct = ajeInvAcctDistPct.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        } else {
            ajeInvAcctDistPct = new BigDecimal(100);
        }
        // END   2016/09/05 S.Fujita [QC#10156,MOD]
        // END   2018/05/22 Y.Matsui [QC#21841,MOD]

        return ajeInvAcctDistPct;
    }

    /**
     * setValuesAtLink
     * @param crDrTpCd String
     * @param valInTrx String
     * @param ajeLineIndTp String
     * @return
     * @throws SQLException
     */
    public static boolean setValuesAtLink(String crDrTpCd, String valInTrx, String ajeLineIndTp) {

        if (DR_CR_TP_DEBIT.equals(crDrTpCd)) {
            return false;
        }

//        if (ajeLineIndTpLink.contains(ajeLineIndTp) && hasValue(valInTrx)) {
//            return true;
//        }

        return false;
    }

    /**
     * Set 9 Segments Values
     * @param bizMsg NFCL3000CMsg
     * @param ajeInvAcctDist AJE_INV_ACCT_DISTTMsg
     * @param invSlsCr ResultSet
     * @param resAjePtrn Map
     * @param drCrTpCd String
     * @param idx int
     * @return boolean
     * @throws SQLException
     */
    public static boolean setAjeInvAcctDist9SegmentsValues(NFCL3000CMsg bizMsg, AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, ResultSet invSlsCr, Map<String, String> resAjePtrn, String drCrTpCd, NFCL3000_CCMsg cBizMsg) throws SQLException {

        String resultCd;

        String coaCmpyCd;
        String coaBrCd;
        String coaCcCd;
        String coaAcctCd;
        String coaProdCd;
        String coaChCd;
        String coaAfflCd;
        String coaProjCd;
        String coaExtnCd;

        if (DR_CR_TP_DEBIT.equals(drCrTpCd)) {
            coaCmpyCd = resAjePtrn.get(DR_AJE_COA_CMPY_CD);
            coaBrCd = resAjePtrn.get(DR_AJE_COA_BR_CD);
            coaCcCd = resAjePtrn.get(DR_AJE_COA_CC_CD);
            coaAcctCd = resAjePtrn.get(DR_AJE_COA_ACCT_CD);
            coaProdCd = resAjePtrn.get(DR_AJE_COA_PROD_CD);
            coaChCd = resAjePtrn.get(DR_AJE_COA_CH_CD);
            coaAfflCd = resAjePtrn.get(DR_AJE_COA_AFFL_CD);
            coaProjCd = resAjePtrn.get(DR_AJE_COA_PROJ_CD);
            coaExtnCd = resAjePtrn.get(DR_AJE_COA_EXTN_CD);
        } else {
            coaCmpyCd = resAjePtrn.get(CR_AJE_COA_CMPY_CD);
            coaBrCd = resAjePtrn.get(CR_AJE_COA_BR_CD);
            coaCcCd = resAjePtrn.get(CR_AJE_COA_CC_CD);
            coaAcctCd = resAjePtrn.get(CR_AJE_COA_ACCT_CD);
            coaProdCd = resAjePtrn.get(CR_AJE_COA_PROD_CD);
            coaChCd = resAjePtrn.get(CR_AJE_COA_CH_CD);
            coaAfflCd = resAjePtrn.get(CR_AJE_COA_AFFL_CD);
            coaProjCd = resAjePtrn.get(CR_AJE_COA_PROJ_CD);
            coaExtnCd = resAjePtrn.get(CR_AJE_COA_EXTN_CD);
        }

        // START 2016/07/20 S.Fujita [QC#10148,MOD]
//        NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();
//        List<Map> coaSegLkupList;
        // COA Segment Lookup List
//        coaSegLkupList = (List<Map>) commonJrnlEntry.getCoaSegLkupAll(bizMsg.glblCmpyCd.getValue());

        JRNL_ENTRYTMsg tMsg = new JRNL_ENTRYTMsg();
        String crOrDr = "cr";
        if (DR_CR_TP_DEBIT.equals(drCrTpCd)) {
            crOrDr = "dr";
        }
        // END   2016/07/20 S.Fujita [QC#10148,MOD]

        for (int i = 0; i < 9; i++) {
            switch (i) {
                case 0:// Company COA_CMPY
                    // if (CR_CD.equals(drCrTpCd) &&
                    // hasValue(invSlsCr.getString(COA_CMPY_CD)))
                    // {
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_CMPY_CD), resAjePtrn.get(AJE_LINE_IND_TP_CD))) {
                        resultCd = invSlsCr.getString(COA_CMPY_CD);
                    } else if (coaCmpyCd.substring(0, 1).equals("@") || coaCmpyCd.substring(0, 1).equals("#")) {
                        // START 2016/07/20 S.Fujita [QC#10148,MOD]
//                        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaCmpyCd, COA_ATTRB_NM_01, invSlsCr);
//                        if (resultCd != null) {
//                            if (resultCd.subSequence(0, 1).equals("@")) {
//                                resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_01, invSlsCr);
//                            }
//                        }
                        if (NFCL3000CommonLogic.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CMPY, crOrDr, resAjePtrn, invSlsCr, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaCmpyCd");
                        } else {
                            resultCd = null;
                        }
                        // END   2016/07/20 S.Fujita [QC#10148,MOD]
                    } else {
                        resultCd = coaCmpyCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaCmpyCd, resultCd);
                    ZYPEZDItemValueSetter.setValue(cBizMsg.ajeCoaCmpyCd_C1, resultCd);
                    break;

                case 1:// Branch COA_BR
                    // if (CR_CD.equals(drCrTpCd) &&
                    // hasValue(invSlsCr.getString(COA_BR_CD))) {
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_BR_CD), resAjePtrn.get(AJE_LINE_IND_TP_CD))) {
                        resultCd = invSlsCr.getString(COA_BR_CD);
                    } else if (coaBrCd.substring(0, 1).equals("@") || coaBrCd.substring(0, 1).equals("#")) {
                        // START 2016/07/20 S.Fujita [QC#10148,MOD]
//                        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaBrCd, COA_ATTRB_NM_02, (ResultSet)invSlsCr);
//                        if (resultCd != null) {
//                            if (resultCd.subSequence(0, 1).equals("@")) {
//                                resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_02, (ResultSet)invSlsCr);
//                            }
//                        }
                        if (NFCL3000CommonLogic.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.BR, crOrDr, resAjePtrn, invSlsCr, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaBrCd");
                        } else {
                            resultCd = null;
                        }
                        // END   2016/07/20 S.Fujita [QC#10148,MOD]
                    } else {
                        resultCd = coaBrCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaBrCd, resultCd);
                    ZYPEZDItemValueSetter.setValue(cBizMsg.ajeCoaBrCd_C1, resultCd);
                    break;

                case 2:// Cost Center COA_CC
                    // if (CR_CD.equals(drCrTpCd) &&
                    // hasValue(invSlsCr.getString(COA_CC_CD))) {
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_CC_CD), resAjePtrn.get(AJE_LINE_IND_TP_CD))) {
                        resultCd = invSlsCr.getString(COA_CC_CD);
                    } else if (coaCcCd.substring(0, 1).equals("@") || coaCcCd.substring(0, 1).equals("#")) {
                        // START 2016/07/20 S.Fujita [QC#10148,MOD]
//                        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaCcCd, COA_ATTRB_NM_03, (ResultSet)invSlsCr);
//                        if (resultCd != null) {
//                            if (resultCd.subSequence(0, 1).equals("@")) {
//                                resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_03, (ResultSet)invSlsCr);
//                            }
//                        }
                        if (NFCL3000CommonLogic.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CC, crOrDr, resAjePtrn, invSlsCr, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaCcCd");
                        } else {
                            resultCd = null;
                        }
                        // END   2016/07/20 S.Fujita [QC#10148,MOD]
                    } else {
                        resultCd = coaCcCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaCcCd, resultCd);
                    ZYPEZDItemValueSetter.setValue(cBizMsg.ajeCoaCcCd_C1, resultCd);
                    break;

                case 3:// Account COA_ACCT
                    // if (CR_CD.equals(drCrTpCd) &&
                    // hasValue(invSlsCr.getString(COA_ACCT_CD)))
                    // {
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_ACCT_CD), resAjePtrn.get(AJE_LINE_IND_TP_CD))) {
                        resultCd = invSlsCr.getString(COA_ACCT_CD);
                    } else if (coaAcctCd.substring(0, 1).equals("@") || coaAcctCd.substring(0, 1).equals("#")) {
                        // START 2016/07/20 S.Fujita [QC#10148,MOD]
//                        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaAcctCd, COA_ATTRB_NM_04, (ResultSet)invSlsCr);
//                        if (resultCd != null) {
//                            if (resultCd.subSequence(0, 1).equals("@")) {
//                                resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_04, (ResultSet)invSlsCr);
//                            }
//                        }
                        if (NFCL3000CommonLogic.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.ACCT, crOrDr, resAjePtrn, invSlsCr, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaAcctCd");
                        } else {
                            resultCd = null;
                        }
                        // END   2016/07/20 S.Fujita [QC#10148,MOD]
                    } else {
                        resultCd = coaAcctCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaAcctCd, resultCd);
                    ZYPEZDItemValueSetter.setValue(cBizMsg.ajeCoaAcctCd_C1, resultCd);
                    break;

                case 4:// Product COA_PROD
                    // if (CR_CD.equals(drCrTpCd) &&
                    // hasValue(invSlsCr.getString(COA_PROD_CD)))
                    // {
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_PROD_CD), resAjePtrn.get(AJE_LINE_IND_TP_CD))) {
                        resultCd = invSlsCr.getString(COA_PROD_CD);
                    } else if (coaProdCd.substring(0, 1).equals("@") || coaProdCd.substring(0, 1).equals("#")) {
                        // START 2016/07/20 S.Fujita [QC#10148,MOD]
//                        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaProdCd, COA_ATTRB_NM_05, (ResultSet)invSlsCr);
//                        if (resultCd != null) {
//                            if (resultCd.subSequence(0, 1).equals("@")) {
//                                resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_05, (ResultSet)invSlsCr);
//                            }
//                        }
                        if (NFCL3000CommonLogic.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.PROD, crOrDr, resAjePtrn, invSlsCr, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaProdCd");
                        } else {
                            resultCd = null;
                        }
                        // END   2016/07/20 S.Fujita [QC#10148,MOD]
                    } else {
                        resultCd = coaProdCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProdCd, resultCd);
                    ZYPEZDItemValueSetter.setValue(cBizMsg.ajeCoaProdCd_C1, resultCd);
                    break;

                case 5:// Channel COA_CH
                    // if (CR_CD.equals(drCrTpCd) &&
                    // hasValue(invSlsCr.getString(COA_CH_CD))) {
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_CH_CD), resAjePtrn.get(AJE_LINE_IND_TP_CD))) {
                        resultCd = invSlsCr.getString(COA_CH_CD);
                    } else if (coaChCd.substring(0, 1).equals("@") || coaChCd.substring(0, 1).equals("#")) {
                        // START 2016/07/20 S.Fujita [QC#10148,MOD]
//                        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaChCd, COA_ATTRB_NM_06, (ResultSet)invSlsCr);
//                        if (resultCd != null) {
//                            if (resultCd.subSequence(0, 1).equals("@")) {
//                                resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_06, (ResultSet)invSlsCr);
//                            }
//                        }
                        if (NFCL3000CommonLogic.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CH, crOrDr, resAjePtrn, invSlsCr, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaChCd");
                        } else {
                            resultCd = null;
                        }
                        // END   2016/07/20 S.Fujita [QC#10148,MOD]
                    } else {
                        resultCd = coaChCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaChCd, resultCd);
                    ZYPEZDItemValueSetter.setValue(cBizMsg.ajeCoaChCd_C1, resultCd);
                    break;

                case 6:// Affiliate COA_AFFL
                    // if (CR_CD.equals(drCrTpCd) &&
                    // hasValue(invSlsCr.getString(COA_AFFL_CD)))
                    // {
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_AFFL_CD), resAjePtrn.get(AJE_LINE_IND_TP_CD))) {
                        resultCd = invSlsCr.getString(COA_AFFL_CD);
                    } else if (coaAfflCd.substring(0, 1).equals("@") || coaAfflCd.substring(0, 1).equals("#")) {
                        // START 2016/07/20 S.Fujita [QC#10148,MOD]
//                        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaAfflCd, COA_ATTRB_NM_07, (ResultSet)invSlsCr);
//                        if (resultCd != null) {
//                            if (resultCd.subSequence(0, 1).equals("@")) {
//                                resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_07, (ResultSet)invSlsCr);
//                            }
//                        }
                        if (NFCL3000CommonLogic.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.AFFL, crOrDr, resAjePtrn, invSlsCr, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaAfflCd");
                        } else {
                            resultCd = null;
                        }
                        // END   2016/07/20 S.Fujita [QC#10148,MOD]
                    } else {
                        resultCd = coaAfflCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaAfflCd, resultCd);
                    ZYPEZDItemValueSetter.setValue(cBizMsg.ajeCoaAfflCd_C1, resultCd);
                    break;

                case 7:// Merchandise Type COA_PROJ
                    // if (CR_CD.equals(drCrTpCd) &&
                    // hasValue(invSlsCr.getString(COA_PROJ_CD)))
                    // {
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_PROJ_CD), resAjePtrn.get(AJE_LINE_IND_TP_CD))) {
                        resultCd = invSlsCr.getString(COA_PROJ_CD);
                    } else if (coaProjCd.substring(0, 1).equals("@") || coaProjCd.substring(0, 1).equals("#")) {
                        // START 2016/07/20 S.Fujita [QC#10148,MOD]
//                        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaProjCd, COA_ATTRB_NM_08, (ResultSet)invSlsCr);
//                        if (resultCd != null) {
//                            if (resultCd.subSequence(0, 1).equals("@")) {
//                                resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_08, (ResultSet)invSlsCr);
//                            }
//                        }
                        if (NFCL3000CommonLogic.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.PROJ, crOrDr, resAjePtrn, invSlsCr, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaProjCd");
                        } else {
                            resultCd = null;
                        }
                        // END   2016/07/20 S.Fujita [QC#10148,MOD]
                    } else {
                        resultCd = coaProjCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProjCd, resultCd);
                    ZYPEZDItemValueSetter.setValue(cBizMsg.ajeCoaProjCd_C1, resultCd);
                    break;

                case 8:// Business Unit COA_EXTN
                    // if (CR_CD.equals(drCrTpCd) &&
                    // hasValue(invSlsCr.getString(COA_EXTN_CD)))
                    // {
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_EXTN_CD), resAjePtrn.get(AJE_LINE_IND_TP_CD))) {
                        resultCd = invSlsCr.getString(COA_EXTN_CD);
                    } else if (coaExtnCd.substring(0, 1).equals("@") || coaExtnCd.substring(0, 1).equals("#")) {
                        // START 2016/07/20 S.Fujita [QC#10148,MOD]
//                        resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, coaExtnCd, COA_ATTRB_NM_09, (ResultSet)invSlsCr);
//                        if (resultCd != null) {
//                            if (resultCd.subSequence(0, 1).equals("@")) {
//                                resultCd = commonJrnlEntry.getDistData(glblCmpyCd, coaSegLkupList, resultCd, COA_ATTRB_NM_09, (ResultSet)invSlsCr);
//                            }
//                        }
                        if (NFCL3000CommonLogic.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.EXTN, crOrDr, resAjePtrn, invSlsCr, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaExtnCd");
                        } else {
                            resultCd = null;
                        }
                        // END   2016/07/20 S.Fujita [QC#10148,MOD]
                    } else {
                        resultCd = coaExtnCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaExtnCd, resultCd);
                    ZYPEZDItemValueSetter.setValue(cBizMsg.ajeCoaExtnCd_C1, resultCd);
                    break;

                default:
                    break;
            }
        }

        boolean isError = false;

        // If value was not set, error
        if (!hasValue(ajeInvAcctDist.ajeCoaCmpyCd)) {
            isError = true;
            cBizMsg.xxScrItem61Txt_C1.clear();
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CMPY });
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaBrCd)) {
            isError = true;
            cBizMsg.xxScrItem61Txt_C1.clear();
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_BR });
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaCcCd)) {
            isError = true;
            cBizMsg.xxScrItem61Txt_C1.clear();
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CC });
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaAcctCd)) {
            isError = true;
            cBizMsg.xxScrItem61Txt_C1.clear();
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_ACCT });
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaProdCd)) {
            isError = true;
            cBizMsg.xxScrItem61Txt_C1.clear();
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_PROD });
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaChCd)) {
            isError = true;
            cBizMsg.xxScrItem61Txt_C1.clear();
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CH });
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaAfflCd)) {
            isError = true;
            cBizMsg.xxScrItem61Txt_C1.clear();
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_AFFL });
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaProjCd)) {
            isError = true;
            cBizMsg.xxScrItem61Txt_C1.clear();
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_PROJ });
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaExtnCd)) {
            isError = true;
            cBizMsg.xxScrItem61Txt_C1.clear();
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_EXTN });
        }
        return !isError;
    }

    /**
     * getAmount
     * @param invTpCd @param amount
     * @param amount BigDecimal
     * @return
     */
    public static BigDecimal getAmount(String invTpCd, BigDecimal amount) {

        if (invTpCd.equals(INV_TP.CREDIT_MEMO)) {
            amount = amount.negate();
        }
        return amount;
    }
    // END   2016/06/20 S.Fujita [QC#9454,ADD]

    // START 2016/07/05 S.Fujita [QC#10990,MOD]
    /**
     * getInvTpCd
     * @param bizMsg
     * @return
     */
    public static boolean getInvTpCd(NFCL3000CMsg bizMsg) {

        INVTMsg inMsg = new INVTMsg();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.invNum.setValue(bizMsg.invNum_H1.getValue());

        INVTMsg outMsg = (INVTMsg) EZDTBLAccessor.findByKey(inMsg);
        if(outMsg != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invTpCd_H1, outMsg.invTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.sysSrcCd_H1, outMsg.sysSrcCd.getValue());
        }
        return true;
    }
    // END   2016/07/05 S.Fujita [QC#10990,MOD]

    // START 2016/07/08 S.Fujita [QC#11262,ADD]
    /**
     * checkAmtByInvtp
     * @param bizMsg
     * @return
     */
    public static boolean checkAmtByInvtp(NFCL3000CMsg bizMsg) {

        boolean isSuccess = true;

        // START 2016/10/03 S.Fujita [QC#13206,ADD]
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // START 2019/04/25 S.Takami [QC#50078,MOD] without any comments.
            EZDCBigDecimalItem item = bizMsg.A.no(i).ordCustUomQty_A1;
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.A.no(i).xxDplyCtrlFlg_A1.getValue())) {
                item = bizMsg.A.no(i).invDplyQty_A1;
            } else if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, bizMsg.A.no(i).xxDplyCtrlFlg_A1.getValue())) {
                item = bizMsg.A.no(i).shipQty_A1;
            }
            if (INV_TP.CREDIT_MEMO.equals(bizMsg.invTpCd_H1.getValue())) {
                // if Invoice Type is Credit Memo, Qty must be negative number.
                if (item.getValue().signum() > 0) {
                    item.setErrorInfo(1, "NFCM0849E", new String[] {"Qty"});
                    // START 2019/04/25 S.Takami [QC#50078,ADD]
                    bizMsg.A.no(i).adjQtyDplyTxt_A1.setErrorInfo(1, "NFCM0849E", new String[] {"Qty"});
                    // END 2019/04/25 S.Takami [QC#50078,ADD]
                    isSuccess = false;
                }
                // if Invoice Type is Credit Memo, Unit Price must be positive number.
                if (bizMsg.A.no(i).dealNetUnitPrcAmt_A1.getValue().signum() < 0) {
                    bizMsg.A.no(i).dealNetUnitPrcAmt_A1.setErrorInfo(1, "NFCM0869E", new String[] {"Unit Price"});
                    isSuccess = false;
                }
            } else {
                // if Invoice Type is Invoice, Qty must be positive number.
                if (item.getValue().signum() < 0) {
                    item.setErrorInfo(1, "NFCM0848E", new String[] {"Qty"});
                    // START 2019/04/25 S.Takami [QC#50078,ADD]
                    bizMsg.A.no(i).adjQtyDplyTxt_A1.setErrorInfo(1, "NFCM0848E", new String[] {"Qty"});
                    // END 2019/04/25 S.Takami [QC#50078,ADD]
                    isSuccess = false;
                }
            }
            // END 2019/04/25 S.Takami [QC#50078,MOD] without any comments.
        }
        // END   2016/10/03 S.Fujita [QC#13206,ADD]

        if (INV_TP.CREDIT_MEMO.equals(bizMsg.invTpCd_H1.getValue())) {
            if (bizMsg.xxTotAmt_T1.getValue().signum() > 0) {
                bizMsg.xxTotAmt_T1.setErrorInfo(1, "NFCM0849E", new String[] {"Sales Amount"});
                isSuccess = false;
            }
            if (bizMsg.xxTotAmt_T3.getValue().signum() > 0) {
                bizMsg.xxTotAmt_T3.setErrorInfo(1, "NFCM0849E", new String[] {"Tax Amount"});
                isSuccess = false;
            }
            if (bizMsg.xxTotAmt_T4.getValue().signum() > 0) {
                bizMsg.xxTotAmt_T4.setErrorInfo(1, "NFCM0849E", new String[] {"Invoice Total"});
                isSuccess = false;
            }
        } else {
            if (bizMsg.xxTotAmt_T1.getValue().signum() < 0) {
                bizMsg.xxTotAmt_T1.setErrorInfo(1, "NFCM0848E", new String[] {"Sales Amount"});
                isSuccess = false;
            }
            if (bizMsg.xxTotAmt_T3.getValue().signum() < 0) {
                bizMsg.xxTotAmt_T3.setErrorInfo(1, "NFCM0848E", new String[] {"Tax Amount"});
                isSuccess = false;
            }
            if (bizMsg.xxTotAmt_T4.getValue().signum() < 0) {
                bizMsg.xxTotAmt_T4.setErrorInfo(1, "NFCM0848E", new String[] {"Invoice Total"});
                isSuccess = false;
            }
        }
        return isSuccess;
    }
    // END   2016/07/08 S.Fujita [QC#11262,ADD]

    // START 2016/07/20 S.Fujita [QC#10148,ADD]
    /**
     * splitCOA9SegString_PopUp
     * @param bizMsg
     * @param idx
     */
    public static boolean splitCOA9SegString_PopUp(NFCL3000_CCMsg cBizMsg) {

        String coa[] = cBizMsg.xxScrItem61Txt_C1.getValue().split("\\.", 9);
        if(coa.length != 9) {
            String errMsg = "9 segments";
            errMsg = errMsg.concat("(");
            errMsg = errMsg.concat(String.valueOf(coa.length));
            errMsg = errMsg.concat(")");
            cBizMsg.xxScrItem61Txt_C1.setErrorInfo(1, "NFCM0833E", new String[]{errMsg});
            return false;
        }

        cBizMsg.ajeCoaCmpyCd_C1.setValue(coa[0]);
        cBizMsg.ajeCoaBrCd_C1.setValue(coa[1]);
        cBizMsg.ajeCoaCcCd_C1.setValue(coa[2]);
        cBizMsg.ajeCoaAcctCd_C1.setValue(coa[3]);
        cBizMsg.ajeCoaProdCd_C1.setValue(coa[4]);
        cBizMsg.ajeCoaChCd_C1.setValue(coa[5]);
        cBizMsg.ajeCoaAfflCd_C1.setValue(coa[6]);
        cBizMsg.ajeCoaProjCd_C1.setValue(coa[7]);
        cBizMsg.ajeCoaExtnCd_C1.setValue(coa[8]);

        return true;
    }

    /**
     * <pre>
     *  Set value to Journal Entry by the value in AJE Pattern
     *  Changed for CSA
     * </pre>
     * @param jrnlEntry TMsg of Journal Entry
     * @param coa COA segment enum class
     * @param crOrDr string of "Cr" or "Dr"
     * @param rsAjePtrnList pMsg of AJE Pattern
     * @param rs result set
     * @param mtList List of COA_PROJ_ACCT
     * @return boolean
     */
    public static boolean setValueToJrnlEntryByAjePtrn(JRNL_ENTRYTMsg jrnlEntry, CoaSegment coa, String crOrDr, Map<String, String> resAjePtrn, ResultSet rs, Map<String, List<MdseTpAcct>> mtMap) throws SQLException {
        NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

        String fieldNm = commonJrnlEntry.getCoAFieldNm(coa);
        String dbColNm = NFCL3000CommonLogic.getCoADbColNm(coa);

        String ajePtrnFieldNm = crOrDr.toUpperCase() + "_" + "AJE" + "_" + dbColNm;

        // Get value of AJE pattern
        String ptrnVal = resAjePtrn.get(ajePtrnFieldNm);

        String val;
        String outOfTrtryBr = null;

        // if the value has prefix '@' then, get the designated value
        // from result set.
        if (ptrnVal.startsWith("@", 0)) {

            val = NFCL3000CommonLogic.getPtrnValFromRs(crOrDr, ptrnVal, coa, rs, mtMap);

            if (val == null) {
                // START 2016/11/29 S.Fujita [QC#16075,MOD]
                if ("@IB".equals(ptrnVal) && coa == CoaSegment.BR) {
                    // get COA Branch for Out Of Territory
                    if (outOfTrtryBr == null) {
                        outOfTrtryBr = getOutOfTrtryBr(resAjePtrn.get(GLBL_CMPY_CD));
                    }
                    val = outOfTrtryBr;
                }
//                // failure
//                return false;
                // END   2016/11/29 S.Fujita [QC#16075,MOD]
            }

            // if the retrieved value has prefix '@' then, retrieve the value from result set again.
            if (val != null && val.startsWith("@", 0)) {
                ptrnVal = val;
                val = getPtrnValFromRs(crOrDr, val, coa, rs, mtMap);

                if (val == null) {
                    // START 2016/11/29 S.Fujita [QC#16075,MOD]
                    if ("@IB".equals(ptrnVal) && coa == CoaSegment.BR) {
                        // get COA Branch for Out Of Territory
                        if (outOfTrtryBr == null) {
                            outOfTrtryBr = getOutOfTrtryBr(resAjePtrn.get(GLBL_CMPY_CD));
                        }
                        val = outOfTrtryBr;
                    }
//                    // failure
//                    return false;
                    // END   2016/11/29 S.Fujita [QC#16075,MOD]
                }
            }

        } else if (DEF_COA_VAL.equals(ptrnVal)) {

            val = getDefaultCoa(resAjePtrn.get(GLBL_CMPY_CD), coa);

        } else {
            // Use fixed value in AJE pattern
            val = ptrnVal;
        }

        // START 2016/11/29 S.Fujita [QC#16075,ADD]
        if (val == null || "".equals(val)) {
            // set default value
            val = getDefaultCoa(resAjePtrn.get(GLBL_CMPY_CD), coa);

            // set AJE_INTFC_CMNT_TXT
            String msg = "";
            if (hasValue(jrnlEntry.ajeIntfcCmntTxt.getValue())) {
                msg = jrnlEntry.ajeIntfcCmntTxt.getValue() + " |" + coa.toString();
            } else {
                msg = "**Default Value is Set: " + coa.toString();
            }
            if (msg.length() > 240) {
                msg = msg.substring(0, 240);
            }
            jrnlEntry.setDBValue("ajeIntfcCmntTxt", msg);
        }
        // END   2016/11/29 S.Fujita [QC#16075,ADD]

        // set the value to T message of JRNL_ENTRY
        jrnlEntry.setDBValue(crOrDr + fieldNm, checkNull(val));

        return true;
    }

    /**
     *  getDefaultCoa
     * @param glblCmpyCd
     * @param coa
     * @return val
     */
    public static String getDefaultCoa(String glblCmpyCd, CoaSegment coa) {
        String val = null;
        
        if (defCoa == null) {
            getDefCoaValues(glblCmpyCd);
        }

        switch (coa) {
            case CMPY:
                val = defCoa.getDefCoaCmpy();
                break;
            case BR:
                val = defCoa.getDefCoaBr();
                break;
            case CC:
                val = defCoa.getDefCoaCc();
                break;
            case ACCT:
                val = defCoa.getDefCoaAcct();
                break;
            case PROD:
                val = defCoa.getDefCoaProd();
                break;
            case CH:
                val = defCoa.getDefCoaCh();
                break;
            case AFFL:
                val = defCoa.getDefCoaAffl();
                break;
            case PROJ:
                val = defCoa.getDefCoaProj();
                break;
            case EXTN:
                val = defCoa.getDefCoaExtn();
                break;
            default:
                val = null;
                break;
        }
        return val;
    }

    /**
     * <pre>
     *  Get COA Db Column Name
     * </pre>
     * @param coa COA segment enum class
     * @return String db column name of COA
     */
    public static String getCoADbColNm(CoaSegment coa) {
        switch (coa) {
            case CMPY:
                return COA_CMPY_CD;
            case BR:
                return COA_BR_CD;
            case CC:
                return COA_CC_CD;
            case ACCT:
                return COA_ACCT_CD;
            case PROD:
                return COA_PROD_CD;
            case CH:
                return COA_CH_CD;
            case AFFL:
                return COA_AFFL_CD;
            case PROJ:
                return COA_PROJ_CD;
            case EXTN:
                return COA_EXTN_CD;
            default:
                return "";
        }
    }

    /**
     * <pre>
     *  Get Pattern value from Result Set
     * </pre>
     * @param crOrDr
     * @param ptrn
     * @param coa
     * @param rs
     * @param mtMap
     * @return val
     */
    public static String getPtrnValFromRs(String crOrDr, String ptrn, CoaSegment coa, ResultSet rs, Map<String, List<MdseTpAcct>> mtMap) throws SQLException {
        String val = null;
        String fieldNmOfAt = "";
        String mtCd = "";

        // If the pattern is "@MT-***" from COA_PROJ_ACCT
        if (ptrn.indexOf("@MT") != -1 || ptrn.indexOf("@MMT") != -1) {
            if (mtMap == null) { // if list of COA_PROJ_ACCT is not set, error.
                return null;
            }
            try {
                if (ptrn.indexOf("@MT") != -1) {
                    mtCd = rs.getString("ITEM_PROJ_CD");
                } else {
                    mtCd = rs.getString("MACH_PROJ_CD");
                }

                if (mtCd == null) {
                    return null;  // Merchandise Type Code is null
                }

                val = NFCL3000CommonLogic.getAtMtValue(mtMap, ptrn, mtCd);
            } catch (SQLException sqlEx) {
                return null; // If COA_PROJ_CD is not in result set, error.
            }
        } else {
         // this is a field name to be retrieved and which is pre-determined by rule.
            fieldNmOfAt = ptrn.substring(1) + "_" + coa.toString() + "_CD"; 

            try {
                val = rs.getString(fieldNmOfAt);
            } catch (SQLException sqlEx) {

                try {
                    // retry the field name with "DR" or "CR"
                    fieldNmOfAt = crOrDr.toUpperCase() + "_" + ptrn.substring(1) + "_" + coa.toString() + "_CD";
                    val = rs.getString(fieldNmOfAt);
                } catch (SQLException ex) {
                    return null;
                }
            }
        }
        return val;
    }

    /**
     * <pre>
     *  Get values of COA segment of @MT.
     * </pre>
     * @param mtList List of COA_PROJ_ACCT
     * @param ptrn AJE Pattern String
     * @param mtCd MDSE Type Code
     * @return String retrieved value
     */
    public static String getAtMtValue(Map<String, List<MdseTpAcct>> mtMap, String ptrn, String mtCd) {
        MdseTpAcct mtAcct = null;
        String ptrnString = "";

        // getting pattern string excludes '@MT-' or '@MMT'.
        if (ptrn.indexOf("@MT") != -1) {
            ptrnString = ptrn.substring("@MT-".length(), ptrn.length());
        } else {
            ptrnString = ptrn.substring("@MMT-".length(), ptrn.length());
        }

        // get list of COA_PROJ_ACCT for this merchandise type
        List<MdseTpAcct> mtList = mtMap.get(mtCd);

        for (MdseTpAcct row : mtList) {
            // search for designated COA_PROJ_ACCT. (Type Name should match with second part of AJE pattern string.) 
            //ex. In case of @MT-INV, type name should be "INV".
            // With this rule, AJE can retrieve COA values even if type is added.
            if (row.getCoaProjAcctTpNm().equals(ptrnString)) {
                mtAcct = row;
                break;
            }
        }

        if (mtAcct == null) {
            return null;
        } else {
            return mtAcct.getCoaAcctCd();
        }
    }

    /**
     * <pre>
     *  Get default values of COA
     * </pre>
     * @param glblCmpyCd
     */
    public static void getDefCoaValues(String glblCmpyCd) {

        String defVals = ZYPCodeDataUtil.getVarCharConstValue(AJE_COA_DEF_VALUES, glblCmpyCd);

        StringTokenizer st = new StringTokenizer(defVals, ",");

        defCoa = new DefCoaValues();

        int cnt = 0;
        String val;
        while (st.hasMoreTokens()) {
            val = st.nextToken();
            cnt++;
            switch (cnt) {
                case 1:
                    defCoa.setDefCoaCmpy(val);
                    break;
                case 2:
                    defCoa.setDefCoaBr(val);
                    break;
                case 3:
                    defCoa.setDefCoaCc(val);
                    break;
                case 4:
                    defCoa.setDefCoaAcct(val);
                    break;
                case 5:
                    defCoa.setDefCoaProd(val);
                    break;
                case 6:
                    defCoa.setDefCoaCh(val);
                    break;
                case 7:
                    defCoa.setDefCoaAffl(val);
                    break;
                case 8:
                    defCoa.setDefCoaProj(val);
                    break;
                case 9:
                    defCoa.setDefCoaExtn(val);
                    break;
                default:
                    break;
            }
        }
    }

    private static class DefCoaValues {
        String defCoaCmpy;

        String defCoaBr;

        String defCoaCc;

        String defCoaAcct;

        String defCoaProd;

        String defCoaCh;

        String defCoaAffl;

        String defCoaProj;

        String defCoaExtn;

        public String getDefCoaCmpy() {
            return defCoaCmpy;
        }

        public void setDefCoaCmpy(String defCoaCmpy) {
            this.defCoaCmpy = defCoaCmpy;
        }

        public String getDefCoaBr() {
            return defCoaBr;
        }

        public void setDefCoaBr(String defCoaBr) {
            this.defCoaBr = defCoaBr;
        }

        public String getDefCoaCc() {
            return defCoaCc;
        }

        public void setDefCoaCc(String defCoaCc) {
            this.defCoaCc = defCoaCc;
        }

        public String getDefCoaAcct() {
            return defCoaAcct;
        }

        public void setDefCoaAcct(String defCoaAcct) {
            this.defCoaAcct = defCoaAcct;
        }

        public String getDefCoaProd() {
            return defCoaProd;
        }

        public void setDefCoaProd(String defCoaProd) {
            this.defCoaProd = defCoaProd;
        }

        public String getDefCoaCh() {
            return defCoaCh;
        }

        public void setDefCoaCh(String defCoaCh) {
            this.defCoaCh = defCoaCh;
        }

        public String getDefCoaAffl() {
            return defCoaAffl;
        }

        public void setDefCoaAffl(String defCoaAffl) {
            this.defCoaAffl = defCoaAffl;
        }

        public String getDefCoaProj() {
            return defCoaProj;
        }

        public void setDefCoaProj(String defCoaProj) {
            this.defCoaProj = defCoaProj;
        }

        public String getDefCoaExtn() {
            return defCoaExtn;
        }

        public void setDefCoaExtn(String defCoaExtn) {
            this.defCoaExtn = defCoaExtn;
        }
    }
    /**
     * checkNull
     *  If the value is null, then return ""
     * @param val String
     * @return String
     */
    public static String checkNull(String val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        } else {
            return BLANK;
        }
    }

    /**
     * setDefaultAcctDist
     * @param bizMsg
     * @param idx
     * @param ajeInvAcctClsCd
     * @return
     */
    public static boolean setDefaultAcctDist(NFCL3000CMsg bizMsg, NFCL3000_CCMsg cBizMsg, String ajeInvAcctClsCd) {

        // Set SHIP_TO_ST_CD and SHIP_TO_CTRY_CD
        NFCL3000CommonLogic.setshipToStCdAndCtryCd(bizMsg.D, cBizMsg);

        try {

            // Set AJE_INV_ACCT_DIST
            if(!NFCL3000CommonLogic.setDefaultAcctDist_Idx(bizMsg, cBizMsg, ajeInvAcctClsCd)) {
                return false;
            }

        } catch (SQLException ex){
            bizMsg.setMessageInfo("NFCM0624E");
        }

        // Set COA 9 segment Text
        // Credit Record
        ZYPEZDItemValueSetter.setValue(cBizMsg.xxScrItem61Txt_C1, NFCL3000CommonLogic.setCOA9SegString(cBizMsg));
        return true;
    }

    /**
     * setDefaultAcctDist_Idx
     * @param bizMsg
     * @param idx
     * @param ajeInvAcctClsCd
     * @return
     */
    public static boolean setDefaultAcctDist_Idx(NFCL3000CMsg bizMsg, NFCL3000_CCMsg cBizMsg, String ajeInvAcctClsCd) throws SQLException {

        final NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();
        AJE_INV_ACCT_DISTTMsg ajeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();

        PreparedStatement stmtSelect = null;
        ResultSet ajeSeg = null;
        NFCL3000BL06 query = NFCL3000BL06.getInstance();
        S21SsmLowLevelCodingClient ssmLLClient = null;
        S21SsmExecutionParameter execParam;
        // Set SSM Cursor Parameter
        execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        // Initialize ssmLLClient
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());
        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam.put("dsInvTpCd", bizMsg.dsInvTpCd_H1.getValue());
            ssmParam.put("slsReptocCd", cBizMsg.slsRepTocCd_C1.getValue());
            ssmParam.put("mdseCd", cBizMsg.mdseCd_C1.getValue());
            ssmParam.put("shipToStCd", cBizMsg.shipToStCd_C1.getValue());
            ssmParam.put("shipToCtryCd", cBizMsg.shipToCtryCd_C1.getValue());
            ssmParam.put("billToCustAcctCd", bizMsg.billToCustAcctCd_H3.getValue());
            ssmParam.put("billToCustCd", bizMsg.billToCustCd_H3.getValue());
            ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
            ssmParam.put("csmp", CM_DEF_ACCT.CSMP_COA);
            
            // START 2018/08/30 Y.Matsui [QC#27829,ADD]
            ssmParam.put("invBolLineNum", cBizMsg.invBolLineNum_C1.getValue());
            ssmParam.put("invLineNum", cBizMsg.invLineNum_C1.getValue());
            ssmParam.put("invLineSubNum", cBizMsg.invLineSubNum_C1.getValue());
            ssmParam.put("sellToCustCd", bizMsg.sellToCustCd_H3.getValue());
            ssmParam.put("soldToCustLocCd", bizMsg.billToCustAcctCd_H3.getValue());
            ssmParam.put("invoice", INV_INVTY_IND_TP.INVOICE);
            ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
            ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);
            ssmParam.put("dfrd", DFRD_ACCTG_RULE.DEFERRED);
            ssmParam.put("immediate", DFRD_ACCTG_RULE.IMMEDIATE);
            ssmParam.put("dev", INV_LINE_SPL_TP.DEVIATION);

            String newcore = ZYPCodeDataUtil.getVarCharConstValue("AJE_NEW_CORE_CD", bizMsg.glblCmpyCd.getValue());
            String nonNewCoreBr = ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_BR_NONCORE", bizMsg.glblCmpyCd.getValue());
            String nonNewCoreCc = ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_CC_NONCORE", bizMsg.glblCmpyCd.getValue());
            String stAcctCd = ZYPCodeDataUtil.getVarCharConstValue("AJE_COA_DEF_TAX_ACCT", bizMsg.glblCmpyCd.getValue());
            BigDecimal multiCalcCnt = ZYPCodeDataUtil.getNumConstValue("MULTI_INV_CALC_CNT", bizMsg.glblCmpyCd.getValue());

            ssmParam.put("newcore", newcore);
            ssmParam.put("defBrNewcore", nonNewCoreBr);
            ssmParam.put("defCcNewcore", nonNewCoreCc);
            ssmParam.put("bsd", DS_CONTR_CLS.BSD);
            ssmParam.put("fm", AJE_FM_IND_TP.FM);
            ssmParam.put("nonFm", AJE_FM_IND_TP.NON_FM);
            ssmParam.put("stAcctCd", stAcctCd);
            ssmParam.put("dsContrCatgFLEET", DS_CONTR_CATG.FLEET);
            ssmParam.put("svcAllocTpOPTIMA", SVC_ALLOC_TP.OPTIMA_SUPPLY_INCLUSIVE);
            ssmParam.put("multiCalcCnt", multiCalcCnt);
            ssmParam.put("configInd", AJE_GENL_IND_TP.CONFIG_INDICATOR);
            ssmParam.put("none", AJE_GENL_IND_TP.NONE);

            NFCL3000_BCMsg bBizMsg = getSlsCrBizMsg(bizMsg, cBizMsg);
            ssmParam.put("dfrdAcctgRuleCd", bBizMsg.dfrdAcctgRuleCd_B1.getValue());
            ssmParam.put("invLineSplTpCd", bBizMsg.invLineSplTpCd_B1.getValue());

            NFCL3000_ACMsg aBizMsg = getInvLineBizMsg(bizMsg, cBizMsg);
            ssmParam.put("svcConfigMstrPk", checkNull(aBizMsg.svcConfigMstrPk_A1.getValue()));
            ssmParam.put("svcInvLinePk", checkNull(aBizMsg.svcInvLinePk_A1.getValue()));
            // END   2018/08/30 Y.Matsui [QC#27829,ADD]
            // START 2019/01/15 E.Kameishi [QC#29734,ADD]
            String varRental = ZYPCodeDataUtil.getVarCharConstValue("AJE_RENTAL_SVC_ALLOC_TP_CD", bizMsg.glblCmpyCd.getValue());
            List<String> varRentalCdList = new ArrayList<String>();

            if (varRental != null) {
                String[] varRentalList = varRental.split(",");
                varRentalCdList = Arrays.asList(varRentalList);
            }
            ssmParam.put("varRental", varRentalCdList);
            ssmParam.put("re", SVC_ALLOC_IND_TP.RENTAL);
            ssmParam.put("nonRe", SVC_ALLOC_IND_TP.NON_RENTAL);
            // END 2019/01/15 E.Kameishi [QC#29734,ADD]
            // START 2019/10/02 T.MURAI [QC#53639,ADD]
            ssmParam.put("coaMdseTpAA", COA_PROJ.FREIGHT_RECOVERY);
            // END 2019/10/02 T.MURAI [QC#53639,ADD]
            // START 2021/01/04 R.Kurahashi [QC#56282, ADD]
            ssmParam.put("defFinBrCd", ZYPCodeDataUtil.getVarCharConstValue(DEFAULT_FIN_BR, bizMsg.glblCmpyCd.getValue()));
            // END 2021/01/04 R.Kurahashi [QC#56282, ADD]

            stmtSelect = ssmLLClient.createPreparedStatement("getDefaultAcctDist", ssmParam, execParam);
            ajeSeg = stmtSelect.executeQuery();

            while (ajeSeg.next()) {
                // Get AJE Pattern List for Credit
                List<Map> resAjePtrnList_Credit = (List<Map>) commonJrnlEntry.getAjePtrn2(bizMsg.glblCmpyCd.getValue(), bizMsg.sysSrcCd_H1.getValue(), cBizMsg.trxCd_C1.getValue(), cBizMsg.trxRsnCd_C1.getValue(), DR_CR_TP_CREDIT);

                // get AJE Pattern by Account Class Code
                Map<String, String>resAjePtrn_Credit = NFCL3000CommonLogic.getDfrdAndImeAjePtrn(resAjePtrnList_Credit, ajeInvAcctClsCd, bizMsg);
                if (resAjePtrn_Credit == null ) {
                    cBizMsg.ajeInvAcctClsCd_C1.setErrorInfo(1, "NFAM0037E");
                    return false;
                }

                // Credit Record
                if (!NFCL3000CommonLogic.setAjeInvAcctDist9SegmentsValues(bizMsg, ajeInvAcctDist, ajeSeg, resAjePtrn_Credit, DR_CR_TP_CREDIT, cBizMsg)) {
                    return false;
                }
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, ajeSeg);
        }
        return true;
    }

    /**
     * defaultSetup_SlsCrTab
     * @param bizMsg
     * @return
     */
    public static void defaultSetup_SlsCrTab(NFCL3000CMsg bizMsg) {

        if(isManualInvoice(bizMsg) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue())) {
            boolean isAdd;
            int count = bizMsg.B.getValidCount();

            if(bizMsg.B.getValidCount() < 1) {
                NFCL3000CommonLogic.initialSetup_SlsCrTab(bizMsg);
            } else {
                for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                    isAdd = true;
                    for(int j = 0; j < bizMsg.B.getValidCount(); j++) {
                        if(bizMsg.B.no(j).invBolLineNum_B1.getValue().equals(bizMsg.A.no(i).invBolLineNum_A1.getValue())) {
                            if(bizMsg.B.no(j).invLineNum_B1.getValue().equals(bizMsg.A.no(i).invLineNum_A1.getValue())) {
                                isAdd = false;
                                break;
                            }
                        }
                    }
                    if(isAdd){
                        NFCL3000CommonLogic.initialSetup_SlsCrTab_Idx(bizMsg, i, count);
                        count++;
                    }
                }
                bizMsg.B.setValidCount(count);
            }
        }
    }

    /**
     * defaultSetup_AcctTab
     * @param bizMsg
     * @param globalMsg
     * @return
     */
    public static void defaultSetup_AcctTab(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        if (ACCT_DIST_SMRY.equals(bizMsg.xxRadioBtn_C.getValue())) {
            bizMsg.xxRadioBtn_C.setValue(ACCT_DIST_EDIT);
            bizMsg.xxRadioBtn_CB.setValue(ACCT_DIST_EDIT);
            // START 2016/07/20 S.Fujita [QC#10148,ADD]
            NFCL3000CommonLogic.setAcctEditMode(bizMsg, globalMsg);
            // END   2016/07/20 S.Fujita [QC#10148,ADD]
        }

        String procDt = bizMsg.procDt.getValue();

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue())) {
            boolean isAdd;
            int count = bizMsg.C.getValidCount();

            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                isAdd = true;
                for (int j = 0; j < bizMsg.C.getValidCount(); j++) {
                    // START 2016/12/08 [QC#16174, MOD]
                    //if(bizMsg.C.no(j).xxLineNum_C1.getValue().equals(bizMsg.B.no(i).xxLineNum_B1.getValue()) && DR_CR_TP_CREDIT.equals(bizMsg.C.no(j).drCrTpCd_C1.getValue())) {
                    if(bizMsg.C.no(j).xxLineNum_C1.getValue().equals(bizMsg.B.no(i).xxLineNum_B1.getValue()) 
//                            && bizMsg.C.no(j).invBolLineNum_C1.getValue().equals(bizMsg.B.no(i).invBolLineNum_B1.getValue()) 
//                            && bizMsg.C.no(j).invLineNum_C1.getValue().equals(bizMsg.B.no(i).invLineNum_B1.getValue()) 
                            && DR_CR_TP_CREDIT.equals(bizMsg.C.no(j).drCrTpCd_C1.getValue())) {
                    // END   2016/12/08 [QC#16174, MOD]

                        isAdd = false;
                        break;
                    }
                }
                if (isAdd){
                    // START 2018/05/22 Y.Matsui [QC#21841,MOD]
                    String ajeAcctCls = AJE_INV_ACCT_CLS.REVENUE;
                    if (isFreightAccountLine(bizMsg, bizMsg.B.no(i))) {
                        ajeAcctCls = AJE_INV_ACCT_CLS.FREIGHT;
                    }
                    // START 2018/07/17 E.Kameishi [QC#27007,ADD]
                    if (BigDecimal.ZERO.compareTo(bizMsg.B.no(i).dealSlsCrAmt_B1.getValue()) == 0) {
                        continue;
                    }
                    // END 2018/07/17 E.Kameishi [QC#27007,ADD]
                    // Add Revenue Line
                    NFCL3000CommonLogic.initialSetup_AcctLTab_Idx(bizMsg.B.no(i), bizMsg.C.no(count), bizMsg.B.no(i).dealSlsCrAmt_B1.getValue(), procDt);
                    // Set AJE_INV_ACCT_DIST
                    if(!NFCL3000CommonLogic.setDefaultAcctDist(bizMsg, bizMsg.C.no(count), ajeAcctCls)) {
                        return;
                    }
                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).ajeInvAcctClsCd_C1, ajeAcctCls);
                    // END   2018/05/22 Y.Matsui [QC#21841,MOD]

                    count = count + 1;
                }
            }

            // Add Tax Line
            boolean isTaxAdd;
            String preInvBolLineNum = DUMMY_INV_BOL_LINE_NUM;
            String preInvLineNum = DUMMY_INV_BOL_LINE_NUM; // ADD 2016/12/08 [QC#16174]

            if (isManualInvoice(bizMsg)) { // ADD 2016/12/09 [QC#16174] 

                for (int k = 0; k < bizMsg.A.getValidCount(); k++) {
                    isTaxAdd = true;
                    for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

                        if (bizMsg.B.no(i).invBolLineNum_B1.getValue().equals(bizMsg.A.no(k).invBolLineNum_A1.getValue())
                                && bizMsg.B.no(i).invLineNum_B1.getValue().equals(bizMsg.A.no(k).invLineNum_A1.getValue())) { // ADD 2016/12/08 [QC#16174]

                            for (int l = 0; l < bizMsg.C.getValidCount(); l++) {
                                if (AJE_INV_ACCT_CLS.TAX.equals(bizMsg.C.no(l).ajeInvAcctClsCd_C1.getValue()) //
                                        && bizMsg.C.no(l).invBolLineNum_C1.getValue().equals(bizMsg.B.no(i).invBolLineNum_B1.getValue()) // ADD 2016/12/08 [QC#16174]
                                        && bizMsg.C.no(l).invLineNum_C1.getValue().equals(bizMsg.B.no(i).invLineNum_B1.getValue())) {
                                    isTaxAdd = false;
                                    break;
                                }
                            }

                            if (isTaxAdd //
                                    && (!preInvBolLineNum.equals(bizMsg.A.no(k).invBolLineNum_A1.getValue()) //
                                            || !preInvLineNum.equals(bizMsg.A.no(k).invLineNum_A1.getValue())) // ADD 2016/12/08 [QC#16174]                                                    // [QC#16174]
                            ) {
                                if (bizMsg.A.no(k).invLineDealTaxAmt_A1.getValue().compareTo(BigDecimal.ZERO) != 0) {

                                    preInvBolLineNum = bizMsg.A.no(k).invBolLineNum_A1.getValue();
                                    preInvLineNum = bizMsg.A.no(k).invLineNum_A1.getValue(); // ADD 2016/12/08 [QC#16174]
                                    NFCL3000CommonLogic.initialSetup_AcctLTab_Idx(bizMsg.B.no(i), bizMsg.C.no(count), bizMsg.A.no(k).invLineDealTaxAmt_A1.getValue(), procDt);
                                    // Set AJE_INV_ACCT_DIST
                                    if (!NFCL3000CommonLogic.setDefaultAcctDist(bizMsg, bizMsg.C.no(count), AJE_INV_ACCT_CLS.TAX)) {
                                        return;
                                    }
                                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).ajeInvAcctClsCd_C1, AJE_INV_ACCT_CLS.TAX);
                                    count = count + 1;
                                }
                            }
                            break;
                        }
                    }
                }

// START 2018/05/22 Y.Matsui [QC#21841,DEL]
//                // START 2016/10/25 S.Fujita [QC#13645,ADD]
//                // Add Freight Line
//                boolean isFrtAdd;
//                preInvBolLineNum = DUMMY_INV_BOL_LINE_NUM;
//
//                for (int m = 0; m < bizMsg.D.getValidCount(); m++) {
//                    isFrtAdd = true;
//                    for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//
//                        if (bizMsg.B.no(i).invBolLineNum_B1.getValue().equals(bizMsg.D.no(m).invBolLineNum_D1.getValue())) {
//                            for (int l = 0; l < bizMsg.C.getValidCount(); l++) {
//                                if (AJE_INV_ACCT_CLS.FREIGHT.equals(bizMsg.C.no(l).ajeInvAcctClsCd_C1.getValue())
//                                        && bizMsg.C.no(l).invBolLineNum_C1.getValue().equals(bizMsg.B.no(i).invBolLineNum_B1.getValue())) {
//                                    isFrtAdd = false;
//                                    break;
//                                }
//                            }
//
//                            if (isFrtAdd && !preInvBolLineNum.equals(bizMsg.D.no(m).invBolLineNum_D1.getValue())) {
//                                if (bizMsg.D.no(m).shipDealFrtAmt_D1.getValue().compareTo(BigDecimal.ZERO) != 0) {
//
//                                    preInvBolLineNum = bizMsg.D.no(m).invBolLineNum_D1.getValue();
//                                    NFCL3000CommonLogic.initialSetup_AcctLTab_Idx(bizMsg.B.no(i), bizMsg.C.no(count), bizMsg.D.no(m).shipDealFrtAmt_D1.getValue(), procDt);
//                                    // Set AJE_INV_ACCT_DIST
//                                    if (!NFCL3000CommonLogic.setDefaultAcctDist(bizMsg, bizMsg.C.no(count), AJE_INV_ACCT_CLS.FREIGHT)) {
//                                        return;
//                                    }
//                                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).ajeInvAcctClsCd_C1, AJE_INV_ACCT_CLS.FREIGHT);
//                                    count = count + 1;
//                                    // START 2016/12/08 [QC#16174, ADD]
//                                    // Set Freight Tax
//                                    NFCL3000CommonLogic.initialSetup_AcctLTab_Idx(bizMsg.B.no(i), bizMsg.C.no(count), bizMsg.D.no(m).frtDealTaxAmt_D1.getValue(), procDt);
//                                    // Set AJE_INV_ACCT_DIST
//                                    if (!NFCL3000CommonLogic.setDefaultAcctDist(bizMsg, bizMsg.C.no(count), AJE_INV_ACCT_CLS.TAX)) {
//                                        return;
//                                    }
//                                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).ajeInvAcctClsCd_C1, AJE_INV_ACCT_CLS.TAX);
//                                    count = count + 1;
//                                    // END 2016/12/08 [QC#16174, ADD]
//                                }
//                            }
//                            break;
//                        }
//                    }
//                }
// END   2018/05/22 Y.Matsui [QC#21841,DEL]
            } // ADD 2016/12/09 [QC#16174] 
            // END   2016/10/25 S.Fujita [QC#13645,ADD]

            bizMsg.xxPgFlg_C.setValue(ZYPConstant.FLG_ON_Y);
            bizMsg.C.setValidCount(count);
        }
    }

    /**
     * setAcctEditMode
     * @param bizMsg
     * @param globalMsg
     */
    public static void setAcctEditMode(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        if(globalMsg.F.getValidCount() < 1) {
            return;
        }
        NFCL3000CommonLogic.copyFtoC(bizMsg, globalMsg);

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxPgFlg_C.getValue())){
            return;
        }

        // Debit
        ZYPTableUtil.clear(bizMsg.C);
        BigDecimal segAmt = BigDecimal.ZERO;
        int idx = 0;
        int no = 0;

        for(int j = 0; j < globalMsg.F.getValidCount(); j++) {

            if(DR_CR_TP_CREDIT.equals(globalMsg.F.no(j).drCrTpCd_F1.getValue()) || AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(globalMsg.F.no(j).ajeInvAcctClsCd_F1.getValue()) 
                    || AJE_INV_ACCT_CLS.REVENUE_EARNED.equals(globalMsg.F.no(j).ajeInvAcctClsCd_F1.getValue())) {
                continue;
            }
            segAmt = segAmt.add(getAmount(globalMsg.F.no(j).jrnlDealAmt_F1));
        }

        for(no = 0; no < globalMsg.F.getValidCount(); no++) {
            // START 2016/09/26 S.Fujita [QC#13362,MOD]
            // Receivables Only
            if(DR_CR_TP_CREDIT.equals(globalMsg.F.no(no).drCrTpCd_F1.getValue()) || AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue()) 
                    || AJE_INV_ACCT_CLS.REVENUE_EARNED.equals(globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue())) {
                continue;
            }
            // END   2016/09/26 S.Fujita [QC#13362,MOD]

            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxLineNum_C1, globalMsg.F.no(no).xxLineNum_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invBolLineNum_C1, globalMsg.F.no(no).invBolLineNum_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invLineNum_C1, globalMsg.F.no(no).invLineNum_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invLineSubNum_C1, globalMsg.F.no(no).invLineSubNum_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invLineSubTrxNum_C1, globalMsg.F.no(no).invLineSubTrxNum_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).drCrTpCd_C1, DR_CR_TP_DEBIT);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctClsCd_C1, AJE_INV_ACCT_CLS.RECEIVABLES);

            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxScrItem61Txt_C1, globalMsg.F.no(no).xxScrItem61Txt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlDealAmt_C1, segAmt);
            // START 2016/09/05 S.Fujita [QC#10156,MOD] 
//            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctDistPct_C1, globalMsg.F.no(no).ajeInvAcctDistPct_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctDistPct_C1, new BigDecimal(100));
            // END   2016/09/05 S.Fujita [QC#10156,MOD]
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invErrMsgTxt_C1, globalMsg.F.no(no).invErrMsgTxt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invldValTxt_C1, globalMsg.F.no(no).invldValTxt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).glDt_C1, globalMsg.F.no(no).glDt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlCratDt_C1, globalMsg.F.no(no).jrnlCratDt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).trxCd_C1, globalMsg.F.no(no).trxCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).trxRsnCd_C1, globalMsg.F.no(no).trxRsnCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).dsInvSlsCrPk_C1, globalMsg.F.no(no).dsInvSlsCrPk_F1.getValue());

            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).dfrdRevGlStrgFlg_C1, globalMsg.F.no(no).dfrdRevGlStrgFlg_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctDistPk_C1, globalMsg.F.no(no).ajeInvAcctDistPk_F1.getValue());
            // START 2017/03/17 T.Murai [QC#14205,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistCratTs_C, globalMsg.F.no(no).xxRecHistCratTs_F.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistCratByNm_C, globalMsg.F.no(no).xxRecHistCratByNm_F.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistUpdTs_C, globalMsg.F.no(no).xxRecHistUpdTs_F.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistUpdByNm_C, globalMsg.F.no(no).xxRecHistUpdByNm_F.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistTblNm_C, globalMsg.F.no(no).xxRecHistTblNm_F.getValue());
            // END   2017/03/17 T.Murai [QC#14205,ADD]
            break;
        }

        // Credit
        idx++;
        segAmt = BigDecimal.ZERO;
        for(no = 1; no < globalMsg.F.getValidCount(); no++) {
            // START 2016/09/26 S.Fujita [QC#13362,MOD]
            if(DR_CR_TP_DEBIT.equals(globalMsg.F.no(no).drCrTpCd_F1.getValue()) || AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue()) 
                    || AJE_INV_ACCT_CLS.REVENUE_EARNED.equals(globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue())) {
                continue;
            }
            // END   2016/09/26 S.Fujita [QC#13362,MOD]

            segAmt = globalMsg.F.no(no).jrnlDealAmt_F2.getValue();

            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxLineNum_C1, globalMsg.F.no(no).xxLineNum_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invBolLineNum_C1, globalMsg.F.no(no).invBolLineNum_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invLineNum_C1, globalMsg.F.no(no).invLineNum_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invLineSubNum_C1, globalMsg.F.no(no).invLineSubNum_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invLineSubTrxNum_C1, globalMsg.F.no(no).invLineSubTrxNum_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).drCrTpCd_C1, DR_CR_TP_CREDIT);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctClsCd_C1, globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue());

            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxScrItem61Txt_C1, globalMsg.F.no(no).xxScrItem61Txt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlDealAmt_C2, segAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctDistPct_C1, globalMsg.F.no(no).ajeInvAcctDistPct_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invErrMsgTxt_C1, globalMsg.F.no(no).invErrMsgTxt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invldValTxt_C1, globalMsg.F.no(no).invldValTxt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).glDt_C1, globalMsg.F.no(no).glDt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlCratDt_C1, globalMsg.F.no(no).jrnlCratDt_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).trxCd_C1, globalMsg.F.no(no).trxCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).trxRsnCd_C1, globalMsg.F.no(no).trxRsnCd_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).dsInvSlsCrPk_C1, globalMsg.F.no(no).dsInvSlsCrPk_F1.getValue());

            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).dfrdRevGlStrgFlg_C1, globalMsg.F.no(no).dfrdRevGlStrgFlg_F1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctDistPk_C1, globalMsg.F.no(no).ajeInvAcctDistPk_F1.getValue());
            // START 2017/03/17 T.Murai [QC#14205,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistCratTs_C, globalMsg.F.no(no).xxRecHistCratTs_F.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistCratByNm_C, globalMsg.F.no(no).xxRecHistCratByNm_F.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistUpdTs_C, globalMsg.F.no(no).xxRecHistUpdTs_F.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistUpdByNm_C, globalMsg.F.no(no).xxRecHistUpdByNm_F.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistTblNm_C, globalMsg.F.no(no).xxRecHistTblNm_F.getValue());
            // END   2017/03/17 T.Murai [QC#14205,ADD]
            idx++;
        }

        // START 2016/09/26 S.Fujita [QC#13362,ADD]
        // Deferred Revenue or Deferred Revenue Recognition Record
        for(no = 1; no < globalMsg.F.getValidCount(); no++) {

            if(AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue()) || AJE_INV_ACCT_CLS.REVENUE_EARNED.equals(globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue())) {

                if(globalMsg.F.no(no).drCrTpCd_F1.getValue().equals(DR_CR_TP_DEBIT)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlDealAmt_C1, globalMsg.F.no(no).jrnlDealAmt_F1.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctClsCd_C1, AJE_INV_ACCT_CLS.UNEARNED_REVENUE);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlDealAmt_C2, globalMsg.F.no(no).jrnlDealAmt_F2.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctClsCd_C1, globalMsg.F.no(no).ajeInvAcctClsCd_F1.getValue());
                }

                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxLineNum_C1, globalMsg.F.no(no).xxLineNum_F1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invBolLineNum_C1, globalMsg.F.no(no).invBolLineNum_F1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invLineNum_C1, globalMsg.F.no(no).invLineNum_F1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invLineSubNum_C1, globalMsg.F.no(no).invLineSubNum_F1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invLineSubTrxNum_C1, globalMsg.F.no(no).invLineSubTrxNum_F1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).drCrTpCd_C1, globalMsg.F.no(no).drCrTpCd_F1.getValue());

                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxScrItem61Txt_C1, globalMsg.F.no(no).xxScrItem61Txt_F1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctDistPct_C1, globalMsg.F.no(no).ajeInvAcctDistPct_F1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invErrMsgTxt_C1, globalMsg.F.no(no).invErrMsgTxt_F1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).invldValTxt_C1, globalMsg.F.no(no).invldValTxt_F1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).glDt_C1, globalMsg.F.no(no).glDt_F1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).jrnlCratDt_C1, globalMsg.F.no(no).jrnlCratDt_F1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).trxCd_C1, globalMsg.F.no(no).trxCd_F1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).trxRsnCd_C1, globalMsg.F.no(no).trxRsnCd_F1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).dsInvSlsCrPk_C1, globalMsg.F.no(no).dsInvSlsCrPk_F1.getValue());

                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).dfrdRevGlStrgFlg_C1, globalMsg.F.no(no).dfrdRevGlStrgFlg_F1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ajeInvAcctDistPk_C1, globalMsg.F.no(no).ajeInvAcctDistPk_F1.getValue());
                // START 2017/03/17 T.Murai [QC#14205,ADD]
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistCratTs_C, globalMsg.F.no(no).xxRecHistCratTs_F.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistCratByNm_C, globalMsg.F.no(no).xxRecHistCratByNm_F.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistUpdTs_C, globalMsg.F.no(no).xxRecHistUpdTs_F.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistUpdByNm_C, globalMsg.F.no(no).xxRecHistUpdByNm_F.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRecHistTblNm_C, globalMsg.F.no(no).xxRecHistTblNm_F.getValue());
                // END   2017/03/17 T.Murai [QC#14205,ADD]
                idx++;
            }
        }
        // END   2016/09/26 S.Fujita [QC#13362,ADD]

        bizMsg.C.setValidCount(idx);
    }

    /**
     * copyEditCtoF
     * @param bizMsg
     * @param globalMsg
     */
    public static void copyEditCtoF(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        globalMsg.F.clear();
        EZDMsg.copy(bizMsg.C, "C1", globalMsg.F, "F1");

        int idx = 0;
        int no = 1;
        String segTxt = bizMsg.C.no(0).xxScrItem61Txt_C1.getValue();
        String errMsg = bizMsg.C.no(0).invErrMsgTxt_C1.getValue();
        String errVal = bizMsg.C.no(0).invldValTxt_C1.getValue();
        // start roop from next record of Receivables Record
        for(no = 1; no < bizMsg.C.getValidCount(); no++) {
            // START 2016/09/26 S.Fujita [QC#13362,MOD]
            if(!(bizMsg.C.no(no).ajeInvAcctClsCd_C1.getValue().equals(AJE_INV_ACCT_CLS.REV_EARN_TMPLT) 
                    || (DR_CR_TP_DEBIT.equals(bizMsg.C.no(no).drCrTpCd_C1.getValue()) && AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(bizMsg.C.no(no).ajeInvAcctClsCd_C1.getValue())))) {
                // Debit
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxLineNum_F1, bizMsg.C.no(no).xxLineNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invBolLineNum_F1, bizMsg.C.no(no).invBolLineNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invLineNum_F1, bizMsg.C.no(no).invLineNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invLineSubNum_F1, bizMsg.C.no(no).invLineSubNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invLineSubTrxNum_F1, bizMsg.C.no(no).invLineSubTrxNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).drCrTpCd_F1, DR_CR_TP_DEBIT);
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeInvAcctClsCd_F1, AJE_INV_ACCT_CLS.RECEIVABLES);
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxScrItem61Txt_F1, segTxt);
                // Debit Amount is equal to Credit Amount.
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).jrnlDealAmt_F1, bizMsg.C.no(no).jrnlDealAmt_C2.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeInvAcctDistPct_F1, bizMsg.C.no(no).ajeInvAcctDistPct_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invErrMsgTxt_F1, errMsg);
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invldValTxt_F1, errVal);
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).glDt_F1, bizMsg.C.no(no).glDt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).jrnlCratDt_F1, bizMsg.C.no(no).jrnlCratDt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeLineIdxNum_F1, bizMsg.C.no(no).ajeLineIdxNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeInvAcctClsCd_F1, bizMsg.C.no(no).ajeInvAcctClsCd_C1.getValue());

                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaCmpyCd_F1, bizMsg.C.no(0).ajeCoaCmpyCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaBrCd_F1, bizMsg.C.no(0).ajeCoaBrCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaCcCd_F1, bizMsg.C.no(0).ajeCoaCcCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaAcctCd_F1, bizMsg.C.no(0).ajeCoaAcctCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaProdCd_F1, bizMsg.C.no(0).ajeCoaProdCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaChCd_F1, bizMsg.C.no(0).ajeCoaChCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaAfflCd_F1, bizMsg.C.no(0).ajeCoaAfflCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaProjCd_F1, bizMsg.C.no(0).ajeCoaProjCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaExtnCd_F1, bizMsg.C.no(0).ajeCoaExtnCd_C1.getValue());

                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).dsInvSlsCrPk_F1, bizMsg.C.no(no).dsInvSlsCrPk_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).trxCd_F1, bizMsg.C.no(no).trxCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).trxRsnCd_F1, bizMsg.C.no(no).trxRsnCd_C1.getValue());
                // START 2017/03/17 T.Murai [QC#14205,ADD]
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistCratTs_F, bizMsg.C.no(no).xxRecHistCratTs_C.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistCratByNm_F, bizMsg.C.no(no).xxRecHistCratByNm_C.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistUpdTs_F, bizMsg.C.no(no).xxRecHistUpdTs_C.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistUpdByNm_F, bizMsg.C.no(no).xxRecHistUpdByNm_C.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistTblNm_F, bizMsg.C.no(no).xxRecHistTblNm_C.getValue());
                // END   2017/03/17 T.Murai [QC#14205,ADD]
                idx++;

                // Credit
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxLineNum_F1, bizMsg.C.no(no).xxLineNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invBolLineNum_F1, bizMsg.C.no(no).invBolLineNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invLineNum_F1, bizMsg.C.no(no).invLineNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invLineSubNum_F1, bizMsg.C.no(no).invLineSubNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invLineSubTrxNum_F1, bizMsg.C.no(no).invLineSubTrxNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).drCrTpCd_F1, bizMsg.C.no(no).drCrTpCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeInvAcctClsCd_F1, bizMsg.C.no(no).ajeInvAcctClsCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxScrItem61Txt_F1, bizMsg.C.no(no).xxScrItem61Txt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).jrnlDealAmt_F2, bizMsg.C.no(no).jrnlDealAmt_C2.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeInvAcctDistPct_F1, bizMsg.C.no(no).ajeInvAcctDistPct_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invErrMsgTxt_F1, bizMsg.C.no(no).invErrMsgTxt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invldValTxt_F1, bizMsg.C.no(no).invldValTxt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).glDt_F1, bizMsg.C.no(no).glDt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).jrnlCratDt_F1, bizMsg.C.no(no).jrnlCratDt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeLineIdxNum_F1, bizMsg.C.no(no).ajeLineIdxNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeInvAcctClsCd_F1, bizMsg.C.no(no).ajeInvAcctClsCd_C1.getValue());

                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaCmpyCd_F1, bizMsg.C.no(no).ajeCoaCmpyCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaBrCd_F1, bizMsg.C.no(no).ajeCoaBrCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaCcCd_F1, bizMsg.C.no(no).ajeCoaCcCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaAcctCd_F1, bizMsg.C.no(no).ajeCoaAcctCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaProdCd_F1, bizMsg.C.no(no).ajeCoaProdCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaChCd_F1, bizMsg.C.no(no).ajeCoaChCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaAfflCd_F1, bizMsg.C.no(no).ajeCoaAfflCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaProjCd_F1, bizMsg.C.no(no).ajeCoaProjCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaExtnCd_F1, bizMsg.C.no(no).ajeCoaExtnCd_C1.getValue());

                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).dsInvSlsCrPk_F1, bizMsg.C.no(no).dsInvSlsCrPk_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).trxCd_F1, bizMsg.C.no(no).trxCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).trxRsnCd_F1, bizMsg.C.no(no).trxRsnCd_C1.getValue());
                // START 2017/03/17 T.Murai [QC#14205,ADD]
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistCratTs_F, bizMsg.C.no(no).xxRecHistCratTs_C.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistCratByNm_F, bizMsg.C.no(no).xxRecHistCratByNm_C.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistUpdTs_F, bizMsg.C.no(no).xxRecHistUpdTs_C.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistUpdByNm_F, bizMsg.C.no(no).xxRecHistUpdByNm_C.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistTblNm_F, bizMsg.C.no(no).xxRecHistTblNm_C.getValue());
                // END   2017/03/17 T.Murai [QC#14205,ADD]
                idx++;
            } else {
                // Deferred Revenue Record
                if(DR_CR_TP_DEBIT.equals(bizMsg.C.no(no).drCrTpCd_C1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).jrnlDealAmt_F1, bizMsg.C.no(no).jrnlDealAmt_C1.getValue());
                    // Deferred Revenue
                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeInvAcctClsCd_F1, AJE_INV_ACCT_CLS.REV_EARN_TMPLT);
                } else {
                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).jrnlDealAmt_F2, bizMsg.C.no(no).jrnlDealAmt_C2.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeInvAcctClsCd_F1, bizMsg.C.no(no).ajeInvAcctClsCd_C1.getValue());
                }

                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxLineNum_F1, bizMsg.C.no(no).xxLineNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invBolLineNum_F1, bizMsg.C.no(no).invBolLineNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invLineNum_F1, bizMsg.C.no(no).invLineNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invLineSubNum_F1, bizMsg.C.no(no).invLineSubNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invLineSubTrxNum_F1, bizMsg.C.no(no).invLineSubTrxNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).drCrTpCd_F1, bizMsg.C.no(no).drCrTpCd_C1.getValue());

                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxScrItem61Txt_F1, bizMsg.C.no(no).xxScrItem61Txt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeInvAcctDistPct_F1, bizMsg.C.no(no).ajeInvAcctDistPct_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invErrMsgTxt_F1, bizMsg.C.no(no).invErrMsgTxt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).invldValTxt_F1, bizMsg.C.no(no).invldValTxt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).glDt_F1, bizMsg.C.no(no).glDt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).jrnlCratDt_F1, bizMsg.C.no(no).jrnlCratDt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeLineIdxNum_F1, bizMsg.C.no(no).ajeLineIdxNum_C1.getValue());

                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaCmpyCd_F1, bizMsg.C.no(no).ajeCoaCmpyCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaBrCd_F1, bizMsg.C.no(no).ajeCoaBrCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaCcCd_F1, bizMsg.C.no(no).ajeCoaCcCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaAcctCd_F1, bizMsg.C.no(no).ajeCoaAcctCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaProdCd_F1, bizMsg.C.no(no).ajeCoaProdCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaChCd_F1, bizMsg.C.no(no).ajeCoaChCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaAfflCd_F1, bizMsg.C.no(no).ajeCoaAfflCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaProjCd_F1, bizMsg.C.no(no).ajeCoaProjCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaExtnCd_F1, bizMsg.C.no(no).ajeCoaExtnCd_C1.getValue());

                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).dsInvSlsCrPk_F1, bizMsg.C.no(no).dsInvSlsCrPk_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).trxCd_F1, bizMsg.C.no(no).trxCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).trxRsnCd_F1, bizMsg.C.no(no).trxRsnCd_C1.getValue());
                // START 2017/03/17 T.Murai [QC#14205,ADD]
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistCratTs_F, bizMsg.C.no(no).xxRecHistCratTs_C.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistCratByNm_F, bizMsg.C.no(no).xxRecHistCratByNm_C.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistUpdTs_F, bizMsg.C.no(no).xxRecHistUpdTs_C.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistUpdByNm_F, bizMsg.C.no(no).xxRecHistUpdByNm_C.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).xxRecHistTblNm_F, bizMsg.C.no(no).xxRecHistTblNm_C.getValue());
                // END   2017/03/17 T.Murai [QC#14205,ADD]
                idx++;
            }
            // END   2016/09/26 S.Fujita [QC#13362,MOD]
        }
        bizMsg.setCommitSMsg(true);
        globalMsg.F.setValidCount(idx);
    }

    /**
     * setAutoDebitAmount
     * @param bizMsg
     * @param globalMsg
     * @return
     */
    public static void setAutoDebitAmount(NFCL3000CMsg bizMsg) {

        if(bizMsg.C.no(0).drCrTpCd_C1.getValue().equals(DR_CR_TP_DEBIT)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(0).jrnlDealAmt_C1, bizMsg.xxTotAmt_T4.getValue());
        }
    }

    /**
     * removeAjeInvAcctDist
     * @param bizMsg NFCL3000CMsg
     * @return
     */
    public static boolean removeAjeInvAcctDist(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        S21SsmEZDResult ajeInvAcctDist = NFCL3000Query.getInstance().searchAjeInvAcctDistList(bizMsg, ssmParam);
        if (ajeInvAcctDist.isCodeNormal()) {
            List<Map> ajeInvAcctDistList = (List<Map>) ajeInvAcctDist.getResultObject();
            for(int i = 0; i < ajeInvAcctDistList.size(); i++) {

                Map ajeInvAcctDistListData = ajeInvAcctDistList.get(i);
                BigDecimal ajeInvAcctDistPk = (BigDecimal)ajeInvAcctDistListData.get(AJE_INV_ACCT_DIST_PK);
                AJE_INV_ACCT_DISTTMsg rmvMsg = new AJE_INV_ACCT_DISTTMsg();
                ZYPEZDItemValueSetter.setValue(rmvMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rmvMsg.ajeInvAcctDistPk, ajeInvAcctDistPk);
                EZDTBLAccessor.remove(rmvMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rmvMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0863E", new String[]{"AJE_INV_ACCT_DIST"});
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * removeAjeInvAcctDistErr
     * @param bizMsg NFCL3000CMsg
     * @return
     */
    public static boolean removeAjeInvAcctDistErr(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        S21SsmEZDResult ajeInvAcctDistErr = NFCL3000Query.getInstance().searchAjeInvAcctDistErrList(bizMsg, ssmParam);
        if (ajeInvAcctDistErr.isCodeNormal()) {
            List<Map> ajeInvAcctDistErrList = (List<Map>) ajeInvAcctDistErr.getResultObject();
            for(int i = 0; i < ajeInvAcctDistErrList.size(); i++) {

                Map ajeInvAcctDistErrListData = ajeInvAcctDistErrList.get(i);
                BigDecimal ajeInvAcctDistErrPk = (BigDecimal)ajeInvAcctDistErrListData.get(AJE_INV_ACCT_DIST_ERR_PK);
                AJE_INV_ACCT_DIST_ERRTMsg rmvMsg = new AJE_INV_ACCT_DIST_ERRTMsg();
                ZYPEZDItemValueSetter.setValue(rmvMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rmvMsg.ajeInvAcctDistErrPk, ajeInvAcctDistErrPk);
                EZDTBLAccessor.remove(rmvMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rmvMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0863E", new String[]{"AJE_INV_ACCT_DIST_ERR"});
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * getAmount
     * @param amt
     */
    public static BigDecimal getAmount(EZDCBigDecimalItem amt) {

        if (!ZYPCommonFunc.hasValue(amt)) {
            return BigDecimal.ZERO;
        }

        return amt.getValue();
    }

    /**
     * getAmount
     * @param amt
     */
    public static BigDecimal getAmount(EZDSBigDecimalItem amt) {

        if (!ZYPCommonFunc.hasValue(amt)) {
            return BigDecimal.ZERO;
        }

        return amt.getValue();
    }

    /**
     * getAmount
     * @param amt
     */
    public static BigDecimal getAmount(BigDecimal amt) {

        if (!ZYPCommonFunc.hasValue(amt)) {
            return BigDecimal.ZERO;
        }

        return amt;
    }

    /**
     * setMdseAndSlsRep
     * @param bizMsg
     * @param idx
     * @return
     */
    public static void setMdseAndSlsRep(NFCL3000_BCMsgArray bBizMsgAry, NFCL3000_CCMsg cBizMsg) {

        String xxLineNum_C1 = cBizMsg.xxLineNum_C1.getValue();
        String slsRepTocCd_B1 = "";
        String mdseCd_B1 = "";

        for (int i = 0; i < bBizMsgAry.getValidCount(); i++) {

            if (xxLineNum_C1.equals(bBizMsgAry.no(i).xxLineNum_B1.getValue())) {
                slsRepTocCd_B1 = bBizMsgAry.no(i).slsRepTocCd_B1.getValue();
                mdseCd_B1 = bBizMsgAry.no(i).mdseCd_B1.getValue();
                break;
            }

        }

        ZYPEZDItemValueSetter.setValue(cBizMsg.slsRepTocCd_C1, slsRepTocCd_B1);
        ZYPEZDItemValueSetter.setValue(cBizMsg.mdseCd_C1, mdseCd_B1);
    }
 
      /**
       * setshipToStCdAndCtryCd
       * @param bizMsg
       * @param idx
       * @return
       */
      public static void setshipToStCdAndCtryCd(NFCL3000_DCMsgArray dBizMsgAry, NFCL3000_CCMsg cBizMsg) {

          String invBolLineNum_C1 = cBizMsg.invBolLineNum_C1.getValue();
          String shipToStCd_D1 = "";
          String shipToCtryCd_D1 = "";

          for (int i = 0; i < dBizMsgAry.getValidCount(); i++) {

              if (invBolLineNum_C1.equals(dBizMsgAry.no(i).invBolLineNum_D1.getValue())) {
                  shipToStCd_D1 = dBizMsgAry.no(i).shipToStCd_D1.getValue();
                  shipToCtryCd_D1 = dBizMsgAry.no(i).shipToCtryCd_D1.getValue();
                  break;
              }

          }

          ZYPEZDItemValueSetter.setValue(cBizMsg.shipToStCd_C1, shipToStCd_D1);
          ZYPEZDItemValueSetter.setValue(cBizMsg.shipToCtryCd_C1, shipToCtryCd_D1);
      }

    /**
     * defaultSetup_SlsCrTabAndAcctTab
     * @param bizMsg
     * @param globalMsg
     */
    public static void defaultSetup_SlsCrTabAndAcctTab(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        NFCL3000CommonLogic.defaultSetup_SlsCrTab(bizMsg);
        NFCL3000CommonLogic.setSalesCreditLineNum_B(bizMsg);
        // START 2018/07/19 E.Kameishi [QC#27007,ADD]
        NFCL3000CommonLogic.setSalesCreditLineNum(bizMsg);
        // END 2018/07/19 E.Kameishi [QC#27007,ADD]
        NFCL3000CommonLogic.defaultSetup_AcctTab(bizMsg, globalMsg);
    }

    /**
     * getDsAcctNm
     * @param glblCmpyCd
     * @param dsAcctNum
     * @return DS Account Name
     */
    public static String getDsAcctNm(String glblCmpyCd, String dsAcctNum) {

        if (ZYPCommonFunc.hasValue(dsAcctNum)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("dsAcctNum", dsAcctNum);
            ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            ssmParam.put("rowNum", String.valueOf(BigDecimal.ONE));
            S21SsmEZDResult dsAcct = NFCL3000Query.getInstance().getDsAcctNm(null, ssmParam);
            if (dsAcct.isCodeNormal()) {
                return (String) dsAcct.getResultObject();
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * clearShipToAddr
     * @param bizMsg
     */
    public static void clearShipToAddr(NFCL3000CMsg bizMsg) {

        // Clear Ship To Address
        bizMsg.shipToAddlLocNm_H2.clear();
        bizMsg.shipToFirstLineAddr_H2.clear();
        bizMsg.shipToScdLineAddr_H2.clear();
        bizMsg.shipToThirdLineAddr_H2.clear();
        bizMsg.shipToFrthLineAddr_H2.clear();
        bizMsg.shipToStCd_H2.clear();
        bizMsg.shipToProvNm_H2.clear();
        bizMsg.shipToCntyNm_H2.clear();
        bizMsg.shipToPostCd_H2.clear();
        bizMsg.shipToCtyAddr_H2.clear();
        bizMsg.shipToCtryCd_H2.clear();
        bizMsg.lineBizTpCd_H2.clear();
    }

    /**
     * clearBillToAddr
     * @param bizMsg
     */
    public static void clearBillToAddr(NFCL3000CMsg bizMsg) {

        // Clear Bill To Address
        bizMsg.rcpntAddlLocNm_H3.clear();
        bizMsg.rcpntFirstLineAddr_H3.clear();
        bizMsg.rcpntScdLineAddr_H3.clear();
        bizMsg.rcpntThirdLineAddr_H3.clear();
        bizMsg.rcpntFrthLineAddr_H3.clear();
        bizMsg.rcpntCtyAddr_H3.clear();
        bizMsg.rcpntStCd_H3.clear();
        bizMsg.rcpntProvNm_H3.clear();
        bizMsg.rcpntCntyNm_H3.clear();
        bizMsg.rcpntPostCd_H3.clear();
        bizMsg.rcpntCtryCd_H3.clear();
        bizMsg.lineBizTpCd_H3.clear();
    }

    /**
     * updateDsInv
     * @param bizMsg
     * @return
     */
    public static boolean updateDsInv(NFCL3000CMsg bizMsg) {

        boolean isUpdate = false;
        INVTMsg inMsg = new INVTMsg();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.invNum.setValue(bizMsg.invNum_H1.getValue());
        INVTMsg updMsg = (INVTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
        if (updMsg == null) {
            bizMsg.setMessageInfo("NFCM0864E", new String[]{"INV", bizMsg.invNum_H1.getValue()});
            return false;
        }

        if(!updMsg.billToCustAcctNm.getValue().equals(bizMsg.billToCustAcctNm_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(updMsg.billToCustAcctNm, bizMsg.billToCustAcctNm_H3.getValue());
            isUpdate = true;
        }

        if (isUpdate) {
            EZDTBLAccessor.update(updMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0862E", new String[]{"INV"});
                return false;
            }
        }
        return true;
    }

    /**
     * updateInvLine
     * @param bizMsg
     * @return
     */
    public static boolean updateInvLine(NFCL3000CMsg bizMsg) {

        boolean isUpdate = false;
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            INV_LINETMsg inMsg = new INV_LINETMsg();
            inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            inMsg.invNum.setValue(bizMsg.invNum_H1.getValue());
            inMsg.invBolLineNum.setValue(bizMsg.A.no(i).invBolLineNum_A1.getValue());
            inMsg.invLineNum.setValue(bizMsg.A.no(i).invLineNum_A1.getValue());
            inMsg.invLineSubNum.setValue(bizMsg.A.no(i).invLineSubNum_A1.getValue());
            inMsg.invLineSubTrxNum.setValue(bizMsg.A.no(i).invLineSubTrxNum_A1.getValue());
            INV_LINETMsg updMsg = (INV_LINETMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0864E", new String[]{"INV_LINE", bizMsg.A.no(i).invLineNum_A1.getValue()});
                return false;
            }

            if(!updMsg.crDrRsnCd.getValue().equals(bizMsg.A.no(i).crDrRsnCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(updMsg.crDrRsnCd, bizMsg.A.no(i).crDrRsnCd_A1.getValue());
                isUpdate = true;
            }

            if(!updMsg.mdlNm.getValue().equals(bizMsg.A.no(i).mdlNm_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(updMsg.mdlNm, bizMsg.A.no(i).mdlNm_A1.getValue());
                isUpdate = true;
            }

            if(!updMsg.manInvCratCmntTxt.getValue().equals(bizMsg.A.no(i).manInvCratCmntTxt_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(updMsg.manInvCratCmntTxt, bizMsg.A.no(i).manInvCratCmntTxt_A1.getValue());
                isUpdate = true;
            }

            if (isUpdate) {
                EZDTBLAccessor.update(updMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0862E", new String[]{"INV_LINE"});
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * createPulldownListChrgTp
     * @param bizMsg
     */
    public static void createPulldownListChrgTp(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        S21SsmEZDResult chrgTp = NFCL3000Query.getInstance().getChrgTpPullDownList(bizMsg, ssmParam);

        if (chrgTp.isCodeNormal()) {
            List<Map> chrgTpList = (List<Map>) chrgTp.getResultObject();
            initPullDownCreate(bizMsg.svcInvChrgTpCd_AC, bizMsg.svcInvChrgTpNm_AD, chrgTpList, new String[] {"SVC_INV_CHRG_TP_CD", "SVC_INV_CHRG_TP_NM" });
        }
    }

    /**
     * createPulldownListCrDrRsn
     * @param bizMsg
     */
    public static void createPulldownListCrDrRsn(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        S21SsmEZDResult crDrRsn = NFCL3000Query.getInstance().getCrDrRsnPullDownList(bizMsg, ssmParam);

        if (crDrRsn.isCodeNormal()) {
            List<Map> crDrRsnList = (List<Map>) crDrRsn.getResultObject();
            initPullDownCreate(bizMsg.crDrRsnCd_AC, bizMsg.crDrRsnNm_AD, crDrRsnList, new String[] {"CR_DR_RSN_CD", "CR_DR_RSN_NM" });
        }
    }

    /**
     * clearShipToAddr_D
     * @param bizMsg
     */
    public static void clearShipToAddr_D(NFCL3000CMsg bizMsg) {

        int idx = bizMsg.xxCellIdx.getValueInt();
        // Clear Ship To Address
        bizMsg.D.no(idx).shipToAddlLocNm_D1.clear();
        bizMsg.D.no(idx).shipToFirstLineAddr_D1.clear();
        bizMsg.D.no(idx).shipToScdLineAddr_D1.clear();
        bizMsg.D.no(idx).shipToThirdLineAddr_D1.clear();
        bizMsg.D.no(idx).shipToFrthLineAddr_D1.clear();
        bizMsg.D.no(idx).shipToStCd_D1.clear();
        bizMsg.D.no(idx).shipToProvNm_D1.clear();
        bizMsg.D.no(idx).shipToCntyNm_D1.clear();
        bizMsg.D.no(idx).shipToPostCd_D1.clear();
        bizMsg.D.no(idx).shipToCtyAddr_D1.clear();
        bizMsg.D.no(idx).shipToCtryCd_D1.clear();
        bizMsg.D.no(idx).lineBizTpCd_D1.clear();
    }

    /**
     * createPulldownListDsOrdCatg
     * @param bizMsg
     */
    public static void createPulldownListDsOrdCatg(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        S21SsmEZDResult dsOrdCatg = NFCL3000Query.getInstance().getDsOrdCatgPullDownList(bizMsg, ssmParam);

        if (dsOrdCatg.isCodeNormal()) {
            List<Map> dsOrdCatgList = (List<Map>) dsOrdCatg.getResultObject();
            initPullDownCreate(bizMsg.dsOrdCatgCd_EC, bizMsg.dsOrdCatgNm_ED, dsOrdCatgList, new String[] {"DS_ORD_CATG_CD", "DS_ORD_CATG_NM" });
        }
    }

    // START 2020/01/29 H.Mizukami [QC#55493,ADD]
    /**
     * getPositionNumber
     * @param bizMsg
     * @param invBolLineNum
     * @return List<String>
     */
    public static List<String> getPositionNumbers(NFCL3000CMsg bizMsg, String invBolLineNum) {
        List<String> posNumList = new ArrayList<String>();
        for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
            if (invBolLineNum.equals(bizMsg.A.no(j).invBolLineNum_A1.getValue())) {
                boolean found = false;
                for (int k = 0; k < posNumList.size(); k++) {
                    if (posNumList.get(k).equals(bizMsg.A.no(j).dsOrdPosnNum_A1.getValue())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    posNumList.add(bizMsg.A.no(j).dsOrdPosnNum_A1.getValue());
                }
            }
        }
        return posNumList;
    }
    // END   2020/01/29 H.Mizukami [QC#55493,ADD]

    /**
     * checkLine
     * @param bizMsg
     * @return
     */
    public static boolean checkSalesCrBolLine(NFCL3000CMsg bizMsg) {
         boolean isNotError = true;

         for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
             // Sales Rep Check
             if(!NFCL3000CommonLogic.getSalesRepName(bizMsg, i, false)) {
                 isNotError = false;
             }
         }

         if (isManualInvoice(bizMsg) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue())) {
             // START 2016/09/12 S.Fujita [QC#14112,ADD]
             if(bizMsg.D.getValidCount() > 0) {
                 NFCL3000CommonLogic.setDefaultBOL(bizMsg, 0, true);
             }
             // END   2016/09/12 S.Fujita [QC#14112,ADD]

             // START 2020/01/29 H.Mizukami [QC#55493,ADD]
             List<String> dsOrdPosnNumPreList = new ArrayList<String>();
             String shipToLocNmPre = "";
             // END   2020/01/29 H.Mizukami [QC#55493,ADD]
             for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                 // ShipTo Check
                 // START 2020/01/29 H.Mizukami [QC#55493,ADD]
                 if (ZYPCommonFunc.hasValue(bizMsg.D.no(i).locNum_D1.getValue())
                         && ZYPCommonFunc.hasValue(bizMsg.D.no(i).invBolLineNum_D1.getValue())) {
                     if (ZYPCommonFunc.hasValue(shipToLocNmPre)
                             && !shipToLocNmPre.equals(bizMsg.D.no(i).locNum_D1.getValue())) {
                         for (int j = 0; j < dsOrdPosnNumPreList.size(); j++) {
                             if (ZYPCommonFunc.hasValue(dsOrdPosnNumPreList.get(j))) {
                                 for (String dsOrdPosnNum : getPositionNumbers(bizMsg, bizMsg.D.no(i).invBolLineNum_D1.getValue())) {
                                     if (dsOrdPosnNum.equals(dsOrdPosnNumPreList.get(j))) {
                                         String msg = bizMsg.D.no(i).locNum_D1.getValue();
                                         bizMsg.D.no(i).locNum_D1.setErrorInfo(1, "NFCM0912E", new String[]{msg});
                                         isNotError = false;
                                     }
                                 }
                             }
                         }
                     }
                 }
                 // END   2020/01/29 H.Mizukami [QC#55493,ADD]
                 if(ZYPCommonFunc.hasValue(bizMsg.D.no(i).shipToCustAcctCd_D1.getValue()) || ZYPCommonFunc.hasValue(bizMsg.D.no(i).locNum_D1.getValue())) {
                     if(NFCL3000CommonLogic.searchShipTo_D(bizMsg, i)) {
                         NFCL3000CommonLogic.searchShipToAddr_D(bizMsg, i);
                     } else {
                         isNotError = false;
                     }
                 } else {
                     bizMsg.D.no(i).shipToCustCd_D1.clear();
                     isNotError = true;
                 }

                 // START 2016/08/01 S.Fujita [QC#10148,ADD]
                 NFCL3000CommonLogic.splitContactPsnNm_D(bizMsg, bizMsg.D.no(i).xxPsnNm_D1.getValue(), i);
                 // END   2016/08/01 S.Fujita [QC#10148,ADD]

                 // Warehouse Check (not mandatory)
                 if(ZYPCommonFunc.hasValue(bizMsg.D.no(i).shipFromInvtyLocCd_D1.getValue())) {
                     if(!NFCL3000CommonLogic.searchWarehouse_D(bizMsg, i)) {
                         isNotError = false;
                     }
                 }
                 // START 2020/01/29 H.Mizukami [QC#55493,ADD]
                 if (ZYPCommonFunc.hasValue(bizMsg.D.no(i).locNum_D1.getValue())
                         && ZYPCommonFunc.hasValue(bizMsg.D.no(i).invBolLineNum_D1.getValue())) {
                     dsOrdPosnNumPreList = getPositionNumbers(bizMsg, bizMsg.D.no(i).invBolLineNum_D1.getValue());
                     shipToLocNmPre = bizMsg.D.no(i).locNum_D1.getValue();
                 }
                 // END   2020/01/29 H.Mizukami [QC#55493,ADD]
             }
         }
         return isNotError;
    }

    /**
     * searchShipTo_D
     * @param bizMsg
     * @param idx
     * @return
     */
    public static boolean searchShipTo_D(NFCL3000CMsg bizMsg, int idx) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("shipToCustAcctCd", bizMsg.D.no(idx).shipToCustAcctCd_D1.getValue());
        ssmParam.put("locNum", bizMsg.D.no(idx).locNum_D1.getValue());
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        S21SsmEZDResult shipToInfo = NFCL3000Query.getInstance().searchShipTo(bizMsg, ssmParam);
        if (!shipToInfo.isCodeNormal()) {
            bizMsg.D.no(idx).shipToCustAcctCd_D1.setErrorInfo(1, "ZZZM9006E", new String[]{"Ship To Cust Account Number"});
            bizMsg.D.no(idx).locNum_D1.setErrorInfo(1, "ZZZM9006E", new String[]{"Ship To Location"});
            return false;
        } else {
            Map<String, Object> map = (Map<String, Object>) shipToInfo.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCustCd_D1, (String)map.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToLocNm_D1, (String)map.get("LOC_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToFirstRefCmntTxt_D1, (String)map.get("FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToScdRefCmntTxt_D1, (String)map.get("SCD_REF_CMNT_TXT"));
            if(!ZYPCommonFunc.hasValue(bizMsg.D.no(idx).shipToCustAcctNm_D1.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipToCustAcctNm_D1, (String)map.get("DS_ACCT_NM"));
            }
        }
        return true;
    }

    /**
     * setDefaultDebitLine
     * @param bizMsg
     * @param count
     */
    public static void setDefaultDebitLine(NFCL3000CMsg bizMsg, int count) {

        bizMsg.C.no(count).drCrTpCd_C1.setValue(DR_CR_TP_DEBIT);
        bizMsg.C.no(count).ajeInvAcctClsCd_C1.setValue(AJE_INV_ACCT_CLS.RECEIVABLES);

        // START 2017/03/17 T.Murai [QC#14205,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).xxRecHistTblNm_C,"AJE_INV_ACCT_DIST");
        // END   2017/03/17 T.Murai [QC#14205,ADD]

        // set default COA9Seg
        String strCOA9Seg = NFCL3000CommonLogic.setAjeDefaultValues(bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).xxScrItem61Txt_C1, strCOA9Seg);

        // set default S/C#
        NFCL3000CommonLogic.setDefaultLineNum(bizMsg, count);
    }

    /**
     * setAjeDefaultValues
     * @param bizMsg
     */
    public static String setAjeDefaultValues(NFCL3000CMsg bizMsg) {

        String defVals = ZYPCodeDataUtil.getVarCharConstValue(AJE_COA_DEF_VALUES, bizMsg.glblCmpyCd.getValue());

        StringTokenizer st = new StringTokenizer(defVals, ",");

        String strCOA9Seg = null;

        String val;
        while (st.hasMoreTokens()) {
            val = st.nextToken();
            if(!ZYPCommonFunc.hasValue(strCOA9Seg)) {
                strCOA9Seg = val;
            } else {
                strCOA9Seg = strCOA9Seg.concat(".");
                strCOA9Seg = strCOA9Seg.concat(val);
            }
        }
        return strCOA9Seg;
    }

    /**
     * setDefaultLineNum
     * @param bizMsg
     * @param count
     */
    public static void setDefaultLineNum(NFCL3000CMsg bizMsg, int count) {

        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).xxLineNum_C1, bizMsg.B.no(0).xxLineNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).invBolLineNum_C1, bizMsg.B.no(0).invBolLineNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).invLineNum_C1, bizMsg.B.no(0).invLineNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).invLineSubNum_C1, bizMsg.B.no(0).invLineSubNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).invLineSubTrxNum_C1, bizMsg.B.no(0).invTrxLineSubNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).glDt_C1, bizMsg.procDt.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).jrnlDealAmt_C1, bizMsg.B.no(0).dealSlsCrAmt_B1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(count).dsInvSlsCrPk_C1, bizMsg.B.no(0).dsInvSlsCrPk_B1.getValue());
    }

// no use
//    /**
//     * searchWarehouse_D
//     * @param bizMsg
//     * @return
//     */
//    public static boolean searchWarehouse_D(NFCL3000CMsg bizMsg) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        ssmParam.put("whCd", bizMsg.shipFromInvtyLocCd_H4.getValue());
//        ssmParam.put("procDt", bizMsg.procDt.getValue());
//        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        S21SsmEZDResult whInfo = NFCL3000Query.getInstance().searchWarehouse(bizMsg, ssmParam);
//        if (!whInfo.isCodeNormal()) {
//            bizMsg.shipFromInvtyLocCd_H4.setErrorInfo(1, "NFCM0029E");
//            return false;
//        } else {
//            Map<String, Object> map = (Map<String, Object>) whInfo.getResultObject();
//            ZYPEZDItemValueSetter.setValue(bizMsg.shipFromInvtyLocCd_H4, (String)map.get("RTL_WH_CD"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H4, (String)map.get("RTL_WH_NM"));
//        }
//        return true;
//    }

    /**
     * searchWarehouse_D
     * @param bizMsg
     * @param idx
     * @return
     */
    public static boolean searchWarehouse_D(NFCL3000CMsg bizMsg, int idx) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("whCd", bizMsg.D.no(idx).shipFromInvtyLocCd_D1.getValue());
        ssmParam.put("procDt", bizMsg.procDt.getValue());
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        S21SsmEZDResult whInfo = NFCL3000Query.getInstance().searchWarehouse(bizMsg, ssmParam);
        if (!whInfo.isCodeNormal()) {
            bizMsg.D.no(idx).shipFromInvtyLocCd_D1.setErrorInfo(1, "ZZZM9006E", new String[]{"Warehouse"});
            return false;
        } else {
            Map<String, Object> map = (Map<String, Object>) whInfo.getResultObject();
            // START 2016/10/28 T.Murai [QC#15495,MOD]
            // ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipFromInvtyLocCd_D1, (String)map.get("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).shipFromInvtyLocCd_D1, (String)map.get("INVTY_LOC_CD"));
            // END   2016/10/28 T.Murai [QC#15495,MOD]
        }
        return true;
    }

    /**
     *  splitContactPsnNm
     * @param bizMsg
     * @param shipToPsnNm
     * @param billToPsnNm
     */
    public static void splitContactPsnNm(NFCL3000CMsg bizMsg, String shipToPsnNm, String billToPsnNm) {

        // Ship To Contact Person
        if(ZYPCommonFunc.hasValue(shipToPsnNm)) {

            StringTokenizer splitShipToPsnNm = new StringTokenizer(shipToPsnNm, ",");

            int cnt = 0;
            String val;
            while (splitShipToPsnNm.hasMoreTokens()) {
                val = splitShipToPsnNm.nextToken();
                cnt++;
                switch (cnt) {
                    case 1:
                        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtacPsnFirstNm_H2, val);
                        break;
                    case 2:
                        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtacPsnLastNm_H2, val);
                        break;
                    default:
                        break;
                }
            }
        }

        // Bill To Contact Person
        if(ZYPCommonFunc.hasValue(billToPsnNm)) {

            StringTokenizer splitBillToPsnNm = new StringTokenizer(billToPsnNm, ",");

            int cnt = 0;
            String val;
            while (splitBillToPsnNm.hasMoreTokens()) {
                val = splitBillToPsnNm.nextToken();
                cnt++;
                switch (cnt) {
                    case 1:
                        ZYPEZDItemValueSetter.setValue(bizMsg.billToCtacPsnFirstNm_H3, val);
                        break;
                    case 2:
                        ZYPEZDItemValueSetter.setValue(bizMsg.billToCtacPsnLastNm_H3, val);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     *  splitContactPsnNm_D
     * @param bizMsg
     * @param shipToPsnNm
     * @param billToPsnNm
     */
    public static void splitContactPsnNm_D(NFCL3000CMsg bizMsg, String shipToPsnNm, int i) {

        // Ship To Contact Person
        if(ZYPCommonFunc.hasValue(shipToPsnNm)) {

            StringTokenizer splitShipToPsnNm = new StringTokenizer(shipToPsnNm, ",");

            int cnt = 0;
            String val;
            while (splitShipToPsnNm.hasMoreTokens()) {
                val = splitShipToPsnNm.nextToken();
                cnt++;
                switch (cnt) {
                    case 1:
                        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).shipToCtacPsnFirstNm_D1, val);
                        break;
                    case 2:
                        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).shipToCtacPsnLastNm_D1, val);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    // END   2016/07/20 S.Fujita [QC#10148,MOD]

    // START 2016/08/01 [QC#9192, ADD]
    /**
     * getTaxAreaId
     * @param glblCmpyCd
     * @param ctyAddr
     * @param cntyPk
     * @param stCd
     * @return taxAreaId
     */
    private static String getTaxAreaId(String glblCmpyCd, String ctyAddr, BigDecimal cntyPk, String stCd) {
        String taxAreaId = null;

        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", glblCmpyCd);
        inParam.put("ctyAddr", ctyAddr);
        inParam.put("cntyPk", cntyPk);
        inParam.put("stCd", stCd);

        taxAreaId = NFCL3000Query.getInstance().getTaxAreaId(inParam);
        if (taxAreaId == null) {
            inParam.put("ctyAddr", null);
            taxAreaId = NFCL3000Query.getInstance().getTaxAreaId(inParam);
        }
        if (taxAreaId == null) {
            inParam.put("cntyPk", null);
            taxAreaId = NFCL3000Query.getInstance().getTaxAreaId(inParam);
        }

        return taxAreaId;
    }
    // END   2016/08/01 [QC#9192, ADD]

    // START 2016/09/06 S.Fujita [QC#14111,ADD]
    /**
     * convertTimeToDisplay
     * @param bizMsg
     * @return
     */
    public static void convertTimeToDisplay(NFCL3000CMsg bizMsg) {

        if(ZYPCommonFunc.hasValue(bizMsg.ezInTime_H1.getValue())) {
            // EzIn Date
            String ezInYear = bizMsg.ezInTime_H1.getValue().substring(0, 4);
            String ezInMonth = bizMsg.ezInTime_H1.getValue().substring(4, 6);
            String ezInDay = bizMsg.ezInTime_H1.getValue().substring(6, 8);

            String ezInDate = ezInYear + ezInMonth + ezInDay;

            // EzIn Time
            String ezInHH = bizMsg.ezInTime_H1.getValue().substring(8, 10);
            String ezInMM = bizMsg.ezInTime_H1.getValue().substring(10, 12);
            String ezInSS = bizMsg.ezInTime_H1.getValue().substring(12, 14);

            String ezInTime = ezInHH + ":" + ezInMM + ":" + ezInSS;

            String convDate = ZYPDateUtil.convertFormat(ezInDate, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, '/');
            String ezInDateTime = convDate + " " + ezInTime;
            bizMsg.xxDtTm_IN.setValue(ezInDateTime);
        }

        if(ZYPCommonFunc.hasValue(bizMsg.ezUpTime_H1.getValue())) {
            // EzUp Date
            String ezUpYear = bizMsg.ezUpTime_H1.getValue().substring(0, 4);
            String ezUpMonth = bizMsg.ezUpTime_H1.getValue().substring(4, 6);
            String ezUpDay = bizMsg.ezUpTime_H1.getValue().substring(6, 8);

            String ezUpDate = ezUpYear + ezUpMonth + ezUpDay;

            // EzUp Time
            String ezUpHH = bizMsg.ezUpTime_H1.getValue().substring(8, 10);
            String ezUpMM = bizMsg.ezUpTime_H1.getValue().substring(10, 12);
            String ezUpSS = bizMsg.ezUpTime_H1.getValue().substring(12, 14);

            String ezUpTime = ezUpHH + ":" + ezUpMM + ":" + ezUpSS;

            String convDate = ZYPDateUtil.convertFormat(ezUpDate, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, '/');
            String ezUpDateTime = convDate + " " + ezUpTime;
            bizMsg.xxDtTm_UP.setValue(ezUpDateTime);
        }
    }
    // END   2016/09/06 S.Fujita [QC#14111,ADD]

    // START 2016/09/12 S.Fujita [QC#14112,ADD]
    /**
     * deleteLine_SlsCrTabAndAcctTab
     * @param bizMsg
     * @param globalMsg
     */
    public static void deleteLine_SlsCrTabAndAcctTab(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        NFCL3000CommonLogic.deleteLine_LineTab(bizMsg, globalMsg);
        NFCL3000CommonLogic.deleteLine_SlsCrTab(bizMsg, globalMsg);
        NFCL3000CommonLogic.setSalesCreditLineNum_B(bizMsg);
        NFCL3000CommonLogic.deleteLine_AcctTab(bizMsg, globalMsg);
    }

    /**
     * deleteLine_SlsCrTab
     * @param bizMsg
     * @param globalMsg
     * @return
     */
    public static void deleteLine_SlsCrTab(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        if (isManualInvoice(bizMsg) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue())) {

// START 2016/10/20 S.Yoshida [QC#14575,MOD]
//            globalMsg.B.clear();
//            EZDMsg.copy(bizMsg.B, null, globalMsg.B, null);
//
//            bizMsg.B.clear();
//            int cnt = 0;
//
//            for(int i = 0; i < globalMsg.B.getValidCount(); i++) {
//                for(int j = 0; j < bizMsg.A.getValidCount(); j++) {
//                    if(bizMsg.A.no(j).invBolLineNum_A1.getValue().equals(globalMsg.B.no(i).invBolLineNum_B1.getValue())) {
//                        if(bizMsg.A.no(j).invLineNum_A1.getValue().equals(globalMsg.B.no(i).invLineNum_B1.getValue())) {
//                            EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(cnt), null);
//                            cnt++;
//                            break;
//                        }
//                    }
//                }
//            }
//            bizMsg.B.setValidCount(cnt);

            List<Integer> deleteRows = new ArrayList<Integer>();
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

                String invBolLineNum_B1 = bizMsg.B.no(i).invBolLineNum_B1.getValue();
                String invLineNum_B1 = bizMsg.B.no(i).invLineNum_B1.getValue();

                boolean isDelete = true;
                for (int j = 0; j < bizMsg.A.getValidCount(); j++) {

                    if (bizMsg.A.no(j).invBolLineNum_A1.getValue().equals(invBolLineNum_B1) //
                            && bizMsg.A.no(j).invLineNum_A1.getValue().equals(invLineNum_B1)) {
                        isDelete = false;
                    }
                }

                if (isDelete) {
                    deleteRows.add(i);
                }
            }
            ZYPTableUtil.deleteRows(bizMsg.B, deleteRows);
// END   2016/10/20 S.Yoshida [QC#14575,MOD]
        }
    }

    /**
     * deleteLine_LineTab
     * @param bizMsg
     * @param globalMsg
     * @return
     */
    public static void deleteLine_LineTab(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        if (isManualInvoice(bizMsg) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue())) {

// START 2016/10/20 S.Yoshida [QC#14575,MOD]
//            globalMsg.A.clear();
//            EZDMsg.copy(bizMsg.A, null, globalMsg.A, null);
//
//            bizMsg.A.clear();
//            int cnt = 0;
//
//            for(int i = 0; i < globalMsg.A.getValidCount(); i++) {
//                for(int j = 0; j < bizMsg.D.getValidCount(); j++) {
//                    if(bizMsg.D.no(j).invBolLineNum_D1.getValue().equals(globalMsg.A.no(i).invBolLineNum_A1.getValue())) {
//                        EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(cnt), null);
//                        cnt++;
//                        break;
//                    }
//                }
//            }
//            bizMsg.A.setValidCount(cnt);

            List<Integer> deleteRows = new ArrayList<Integer>();
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

                String invBolLineNum_A1 = bizMsg.A.no(i).invBolLineNum_A1.getValue();

                boolean isDelete = true;
                for (int j = 0; j < bizMsg.D.getValidCount(); j++) {

                    if (bizMsg.D.no(j).invBolLineNum_D1.getValue().equals(invBolLineNum_A1)) {
                        isDelete = false;
                    }
                }

                if (isDelete) {
                    deleteRows.add(i);
                }
            }
            ZYPTableUtil.deleteRows(bizMsg.A, deleteRows);
// END   2016/10/20 S.Yoshida [QC#14575,MOD]
        }
    }

    /**
     * deleteLine_AcctTab
     * @param bizMsg
     * @param globalMsg
     * @return
     */
    public static void deleteLine_AcctTab(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        if(!ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue())) {

// START 2016/10/13 S.Yoshida [QC#14575,MOD]
//            globalMsg.C.clear();
//            EZDMsg.copy(bizMsg.C, null, globalMsg.C, null);
//
//            bizMsg.C.clear();
//
//            int cnt = 0;
//            for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
//
//                for(int j = 0; j < bizMsg.B.getValidCount(); j++) {
//                    if(bizMsg.B.no(j).xxLineNum_B1.getValue().equals(globalMsg.C.no(i).xxLineNum_C1.getValue())) {
//                        EZDMsg.copy(globalMsg.C.no(i), null, bizMsg.C.no(cnt), null);
//                        cnt++;
//                        break;
//                    }
//                }
//            }
//            bizMsg.C.setValidCount(cnt);

            List<Integer> deleteRows = new ArrayList<Integer>();
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {

                String xxLineNum_C1 = bizMsg.C.no(i).xxLineNum_C1.getValue();
                // START 2016/12/08 [QC#16174, ADD]
                String invBolLineNum = bizMsg.C.no(i).invBolLineNum_C1.getValue();
                String invLineNum = bizMsg.C.no(i).invLineNum_C1.getValue();
                // END   2016/12/08 [QC#16174, ADD] 
                boolean isDelete = true;

                for (int j = 0; j < bizMsg.B.getValidCount(); j++) {
                    // START 2016/12/08 [QC#16174, MOD]
                    // if (bizMsg.B.no(j).xxLineNum_B1.getValue().equals(xxLineNum_C1)) {
                    if (xxLineNum_C1.equals(bizMsg.B.no(j).xxLineNum_B1.getValue()) //
//                            && invBolLineNum.equals(bizMsg.B.no(j).invBolLineNum_B1.getValue()) //
//                            && invLineNum.equals(bizMsg.B.no(j).invLineNum_B1.getValue())
                            ) {
                        // END 2016/12/08 [QC#16174, MOD]
                        isDelete = false;
                        break;
                    }
                }
                if (isDelete) {
                    deleteRows.add(i);
                }
            }

            if (deleteRows.isEmpty()) {
                return;
            }

            ZYPTableUtil.deleteRows(bizMsg.C, deleteRows);
// END   2016/10/13 S.Yoshida [QC#14575,MOD]
        }
    }

    /**
     * calcAmount_Acct
     * @param bizMsg
     * @return boolean
     */
    public static boolean calcAmount_Acct(NFCL3000CMsg bizMsg) {

        if(bizMsg.C.getValidCount() > 0) {

            if (!NFCL3000CommonLogic.changeAcctCls(bizMsg)) {
                return false;
            }
            NFCL3000CommonLogic.setAcctAmt(bizMsg);
        }
        return true;
    }

    /**
     * setSlsCrAmt
     * @param bizMsg
     */
    public static void setAcctAmt(NFCL3000CMsg bizMsg) {

        // set Revenue or Unearned Revenue Amount
        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {

            String xxLineNum = bizMsg.B.no(i).xxLineNum_B1.getValue();
            String invBolLineNum = bizMsg.B.no(i).invBolLineNum_B1.getValue();
            String invLineNum = bizMsg.B.no(i).invLineNum_B1.getValue();

            NFCL3000CommonLogic.getSlsCrAmt(bizMsg, xxLineNum, invBolLineNum, invLineNum, i);
        }

        // set Revenue Tax Amount
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {

            String invBolLineNum = bizMsg.A.no(i).invBolLineNum_A1.getValue();
            String invLineNum = bizMsg.A.no(i).invLineNum_A1.getValue();

            NFCL3000CommonLogic.getRevTaxAmt(bizMsg, invBolLineNum, invLineNum, i);
        }

        // START 2016/12/08 [QC#16174, DEL] 
        // set Freight Tax Amount
//        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
//
//            String invBolLineNum = bizMsg.D.no(i).invBolLineNum_D1.getValue();
//
//            NFCL3000CommonLogic.getFrtTaxAmt(bizMsg, invBolLineNum, i);
//        }
        // END 2016/12/08 [QC#16174, DEL]

        // START 2019/11/26 [QC#54644, DEL] 
//        // adjust Revenue Tax Amount
//        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
//
//            String invBolLineNum = bizMsg.A.no(i).invBolLineNum_A1.getValue();
//            String invLineNum = bizMsg.A.no(i).invLineNum_A1.getValue();
//            // START 2017/12/25 E.Kameishi [QC#20312,MOD]
//            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxMstChkFlg_A1.getValue())) {
//                NFCL3000CommonLogic.adjRevTaxAmt(bizMsg, invBolLineNum, invLineNum, i);
//            }
//            // END 2017/12/25 E.Kameishi [QC#20312,MOD]
//        }
        // END 2019/11/26 [QC#54644, DEL] 

// START 2018/05/22 Y.Matsui [QC#21841,DEL]
//        // set Freight Amount
//        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
//
//            String invBolLineNum = bizMsg.D.no(i).invBolLineNum_D1.getValue();
//
//            NFCL3000CommonLogic.getFrtAmt(bizMsg, invBolLineNum, i);
//        }
// END   2018/05/22 Y.Matsui [QC#2184(QC#54644)1,DEL]
    }

    /**
     * getSlsCrAmt
     * @param bizMsg
     * @param xxLineNum
     * @param invBolLineNum
     * @param invLineNum
     * @param bIdx
     * @return boolean
     */
    public static boolean getSlsCrAmt(NFCL3000CMsg bizMsg, String xxLineNum, String invBolLineNum, String invLineNum, int bIdx) {

        BigDecimal setSlsCrAmt = BigDecimal.ZERO;
        int count = 0;
        int no = 0;

        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {

            String ajeInvAcctCls = bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue();

            if (AJE_INV_ACCT_CLS.REVENUE.equals(ajeInvAcctCls) || (DR_CR_TP_CREDIT.equals(bizMsg.C.no(i).drCrTpCd_C1.getValue()) && AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(ajeInvAcctCls))) {

                if(bizMsg.C.no(i).xxLineNum_C1.getValue().equals(xxLineNum)) {
                    if(bizMsg.C.no(i).invBolLineNum_C1.getValue().equals(invBolLineNum)) {
                        if(bizMsg.C.no(i).invLineNum_C1.getValue().equals(invLineNum)) {

                            if (count == 0) {
                                setSlsCrAmt = bizMsg.B.no(bIdx).dealSlsCrAmt_B1.getValue();
                                no = i;
                            // if accounting lines are split intentionally, when sales credit and revenue amount check indicate error message.
                            } else {
                                ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_SC, ZYPConstant.FLG_ON_Y);
                            }
                            count = count + 1;
                        }
                    }
                }
            }

            // START 2018/05/22 Y.Matsui [QC#21841,ADD]
            if (AJE_INV_ACCT_CLS.FREIGHT.equals(ajeInvAcctCls)) {

                if(bizMsg.C.no(i).xxLineNum_C1.getValue().equals(xxLineNum)) {
                    if(bizMsg.C.no(i).invBolLineNum_C1.getValue().equals(invBolLineNum)) {
                        if(bizMsg.C.no(i).invLineNum_C1.getValue().equals(invLineNum)) {

                            if (count == 0) {
                                setSlsCrAmt = bizMsg.B.no(bIdx).dealSlsCrAmt_B1.getValue();
                                no = i;
                            // if accounting lines are split intentionally, when sales credit and revenue amount check indicate error message.
                            } else {
                                ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_SC, ZYPConstant.FLG_ON_Y);
                            }
                            count = count + 1;
                        }
                    }
                }
            }
            // END   2018/05/22 Y.Matsui [QC#21841,ADD]
        }

        if (count == 0) {
            return true;
        } else if (count == 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(no).jrnlDealAmt_C2, setSlsCrAmt);
            return true;
        } else {
            return false;
        }
    }

    /**
     * getRevTaxAmt
     * @param bizMsg
     * @param invBolLineNum
     * @param invLineNum
     * @param aIdx
     * @return boolean
     */
    public static boolean getRevTaxAmt(NFCL3000CMsg bizMsg, String invBolLineNum, String invLineNum, int aIdx) {

        BigDecimal setTaxAmt = BigDecimal.ZERO;
        int count = 0;
        int no = 0;

        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {

            String ajeInvAcctCls = bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue();

            if (AJE_INV_ACCT_CLS.TAX.equals(ajeInvAcctCls)) {

                if(bizMsg.C.no(i).invBolLineNum_C1.getValue().equals(invBolLineNum)) {
                    if(bizMsg.C.no(i).invLineNum_C1.getValue().equals(invLineNum)) {

                        if (count == 0) {
                            // START 2018/03/29 E.Kameishi [QC#24390,MOD]
                            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(aIdx).xxMstChkFlg_A1.getValue())){
                                setTaxAmt = bizMsg.A.no(aIdx).invLineDealTaxAmt_A1.getValue().multiply(bizMsg.A.no(aIdx).shipQty_A1.getValue());
                            } else {
                                setTaxAmt = bizMsg.A.no(aIdx).invLineDealTaxAmt_A1.getValue();
                            }
                            // END 2018/03/29 E.Kameishi [QC#24390,MOD]
                            no = i;
                        // if accounting lines are split intentionally, when tax of line and that of accounting check indicate error message.
                        } else {
                            ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_TX, ZYPConstant.FLG_ON_Y);
                        }
                        count = count + 1;
                    }
                }
            }
        }

        if (count == 0) {
            return true;
        } else if (count == 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(no).jrnlDealAmt_C2, setTaxAmt);
            return true;
        } else {
            return false;
        }
    }

    /**
     * getFrtTaxAmt
     * @param bizMsg
     * @param invBolLineNum
     * @param dIdx
     */
    public static void getFrtTaxAmt(NFCL3000CMsg bizMsg, String invBolLineNum, int dIdx) {

        BigDecimal setTaxAmt = BigDecimal.ZERO;
        BigDecimal setFrtTaxAmt = BigDecimal.ZERO;
        int count = 0;
        int no = 0;

        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {

            String ajeInvAcctCls = bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue();

            if (AJE_INV_ACCT_CLS.TAX.equals(ajeInvAcctCls)) {

                if(bizMsg.C.no(i).invBolLineNum_C1.getValue().equals(invBolLineNum)) {
                    // get Freight Tax Amount and add Credit Amount.
                    setFrtTaxAmt = bizMsg.D.no(dIdx).frtDealTaxAmt_D1.getValue();
                    if(ZYPCommonFunc.hasValue(bizMsg.C.no(i).jrnlDealAmt_C2.getValue())) {
                        setTaxAmt = bizMsg.C.no(i).jrnlDealAmt_C2.getValue().add(getAmount(setFrtTaxAmt));
                    }
                    no = i;
                    break;
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(no).jrnlDealAmt_C2, setTaxAmt);
    }

    /**
     * adjRevTaxAmt
     * @param bizMsg
     * @param invBolLineNum
     * @param invLineNum
     * @param aIdx
     */
    public static void adjRevTaxAmt(NFCL3000CMsg bizMsg, String invBolLineNum, String invLineNum, int aIdx) {

        BigDecimal setTaxAmt = BigDecimal.ZERO;
        int no = 0;
        String taxAdjFlg = ZYPConstant.FLG_OFF_N;

        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {

            String ajeInvAcctCls = bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue();

            if (AJE_INV_ACCT_CLS.TAX.equals(ajeInvAcctCls)) {

                if(bizMsg.C.no(i).invBolLineNum_C1.getValue().equals(invBolLineNum)) {
                    // when tax adjust, INV_BOL_LINE_NUM of Accounting line equals to that of Line TAB.  
                    no = i;
                    if(bizMsg.C.no(i).invLineNum_C1.getValue().equals(invLineNum)) {
                        return;
                    }
                }
                taxAdjFlg = ZYPConstant.FLG_ON_Y;
            }
        }

        // adjust Tax Amount to Credit Amount.
        if(ZYPConstant.FLG_ON_Y.equals(taxAdjFlg)) {
            if(ZYPCommonFunc.hasValue(bizMsg.C.no(no).jrnlDealAmt_C2.getValue())) {
                setTaxAmt = bizMsg.C.no(no).jrnlDealAmt_C2.getValue().add(getAmount(bizMsg.A.no(aIdx).invLineDealTaxAmt_A1));
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(no).jrnlDealAmt_C2, setTaxAmt);
        }
    }

// START 2018/05/22 Y.Matsui [QC#21841,DEL]
//    /**
//     * getFrtAmt
//     * @param bizMsg
//     * @param invBolLineNum
//     * @param dIdx
//     * @return boolean
//     */
//    public static boolean getFrtAmt(NFCL3000CMsg bizMsg, String invBolLineNum, int dIdx) {
//
//        BigDecimal setFrtAmt = BigDecimal.ZERO;
//        int count = 0;
//        int no = 0;
//
//        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
//
//            String ajeInvAcctCls = bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue();
//
//            if (AJE_INV_ACCT_CLS.FREIGHT.equals(ajeInvAcctCls)) {
//
//                if(bizMsg.C.no(i).invBolLineNum_C1.getValue().equals(invBolLineNum)) {
//
//                    if (count == 0) {
//                        setFrtAmt = bizMsg.D.no(dIdx).shipDealFrtAmt_D1.getValue();
//                        no = i;
//                    // if accounting lines are split intentionally, when freight of shipping and that of accounting check indicate error message.
//                    } else {
//                        ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_FR, ZYPConstant.FLG_ON_Y);
//                    }
//                    count = count + 1;
//                }
//            }
//        }
//
//        if (count == 0) {
//            return true;
//        } else if (count == 1) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(no).jrnlDealAmt_C2, setFrtAmt);
//            return true;
//        } else {
//            return false;
//        }
//    }
//    // END   2016/09/12 S.Fujita [QC#14112,ADD]
 // END   2018/05/22 Y.Matsui [QC#21841,DEL]

    // START 2016/09/26 S.Fujita [QC#13362,ADD]
    /**
     * Set 9 Segments Values By Account Information
     * @param globalMsg NFCL3000SMsg
     * @param ajeInvAcctDist AJE_INV_ACCT_DISTTMsg
     * @param idx int
     */
    public static void setAjeInvAcctDist9SegmentsValuesByAcctInfo(NFCL3000SMsg globalMsg, AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, int idx) {

        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaCmpyCd, globalMsg.F.no(idx).ajeCoaCmpyCd_F1.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaBrCd, globalMsg.F.no(idx).ajeCoaBrCd_F1.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaCcCd, globalMsg.F.no(idx).ajeCoaCcCd_F1.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaAcctCd, globalMsg.F.no(idx).ajeCoaAcctCd_F1.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProdCd, globalMsg.F.no(idx).ajeCoaProdCd_F1.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaChCd, globalMsg.F.no(idx).ajeCoaChCd_F1.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaAfflCd, globalMsg.F.no(idx).ajeCoaAfflCd_F1.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProjCd, globalMsg.F.no(idx).ajeCoaProjCd_F1.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaExtnCd, globalMsg.F.no(idx).ajeCoaExtnCd_F1.getValue());
    }

    /**
     * checkAjeInvAcctCls
     * @param bizMsg
     * @return
     */
    public static boolean checkAjeInvAcctCls(NFCL3000CMsg bizMsg) {
        // Check Accounting Class
        boolean isNotError = true;
        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {
            // Receivables in Accounting Class must be set Type of Debit.
            if(DR_CR_TP_CREDIT.equals(bizMsg.C.no(i).drCrTpCd_C1.getValue()) && AJE_INV_ACCT_CLS.RECEIVABLES.equals(bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue())) {
                bizMsg.C.no(i).ajeInvAcctClsCd_C1.setErrorInfo(1, "NFCM0868E" );
                isNotError = false;
            }
        }
        return isNotError;
    }

    /**
     * updateInvoice_ACCT_DISTForDeferred
     * @param bizMsg
     * @param globalMsg
     * @return
     */
    public static boolean updateInvoice_ACCT_DISTForDeferred(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        for(int i = 0; i < globalMsg.F.getValidCount(); i++) {
            if(AJE_INV_ACCT_CLS.REVENUE_EARNED.equals(globalMsg.F.no(i).ajeInvAcctClsCd_F1.getValue())) {

                if(ZYPCommonFunc.hasValue(globalMsg.F.no(i).ajeInvAcctDistPk_F1.getValue())) {

                    //---------------------------------------
                    // Update AJE_INV_ACCT_DIST For Deferred
                    //---------------------------------------
                    AJE_INV_ACCT_DISTTMsg inMsg = new AJE_INV_ACCT_DISTTMsg();
                    inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
                    inMsg.ajeInvAcctDistPk.setValue(globalMsg.F.no(i).ajeInvAcctDistPk_F1.getValue());
                    AJE_INV_ACCT_DISTTMsg updMsg = (AJE_INV_ACCT_DISTTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                        bizMsg.setMessageInfo("NFCM0864E", new String[]{"AJE_INV_ACCT_DIST", bizMsg.invNum_H1.getValue()});
                        return false;
                    }

                    // START 2019/07/11 S.Takami [QC#51334,ADD]
                    boolean isSame9Segment = S21StringUtil.isEquals(updMsg.ajeCoaCmpyCd.getValue(), globalMsg.F.no(i).ajeCoaCmpyCd_F1.getValue());
                    isSame9Segment = isSame9Segment & S21StringUtil.isEquals(updMsg.ajeCoaBrCd.getValue(), globalMsg.F.no(i).ajeCoaBrCd_F1.getValue());
                    isSame9Segment = isSame9Segment & S21StringUtil.isEquals(updMsg.ajeCoaCcCd.getValue(), globalMsg.F.no(i).ajeCoaCcCd_F1.getValue());
                    isSame9Segment = isSame9Segment & S21StringUtil.isEquals(updMsg.ajeCoaAcctCd.getValue(), globalMsg.F.no(i).ajeCoaAcctCd_F1.getValue());
                    isSame9Segment = isSame9Segment & S21StringUtil.isEquals(updMsg.ajeCoaProdCd.getValue(), globalMsg.F.no(i).ajeCoaProdCd_F1.getValue());
                    isSame9Segment = isSame9Segment & S21StringUtil.isEquals(updMsg.ajeCoaChCd.getValue(), globalMsg.F.no(i).ajeCoaChCd_F1.getValue());
                    isSame9Segment = isSame9Segment & S21StringUtil.isEquals(updMsg.ajeCoaAfflCd.getValue(), globalMsg.F.no(i).ajeCoaAfflCd_F1.getValue());
                    isSame9Segment = isSame9Segment & S21StringUtil.isEquals(updMsg.ajeCoaProjCd.getValue(), globalMsg.F.no(i).ajeCoaProjCd_F1.getValue());
                    isSame9Segment = isSame9Segment & S21StringUtil.isEquals(updMsg.ajeCoaExtnCd.getValue(), globalMsg.F.no(i).ajeCoaExtnCd_F1.getValue());
                    if (isSame9Segment) {
                        continue;
                    }
                    // END 2019/07/11 S.Takami [QC#51334,ADD]
                    updMsg.ajeCoaCmpyCd.setValue(globalMsg.F.no(i).ajeCoaCmpyCd_F1.getValue());
                    updMsg.ajeCoaBrCd.setValue(globalMsg.F.no(i).ajeCoaBrCd_F1.getValue());
                    updMsg.ajeCoaCcCd.setValue(globalMsg.F.no(i).ajeCoaCcCd_F1.getValue());
                    updMsg.ajeCoaAcctCd.setValue(globalMsg.F.no(i).ajeCoaAcctCd_F1.getValue());
                    updMsg.ajeCoaProdCd.setValue(globalMsg.F.no(i).ajeCoaProdCd_F1.getValue());
                    updMsg.ajeCoaChCd.setValue(globalMsg.F.no(i).ajeCoaChCd_F1.getValue());
                    updMsg.ajeCoaAfflCd.setValue(globalMsg.F.no(i).ajeCoaAfflCd_F1.getValue());
                    updMsg.ajeCoaProjCd.setValue(globalMsg.F.no(i).ajeCoaProjCd_F1.getValue());
                    updMsg.ajeCoaExtnCd.setValue(globalMsg.F.no(i).ajeCoaExtnCd_F1.getValue());

                    EZDTBLAccessor.update(updMsg);
                   if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                       bizMsg.setMessageInfo("NFCM0862E", new String[]{"AJE_INV_ACCT_DIST"});
                       return false;
                   }
                }
            }
        }
        return true;
    }

    /**
     * copyEditCtoF For Deferred
     * @param bizMsg
     * @param globalMsg
     */
    public static void copyEditCtoFForDeferred(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        int idx = 0;
        // start roop from next record of Receivables Record
        for(int no = 1; no < bizMsg.C.getValidCount(); no++) {
            if (AJE_INV_ACCT_CLS.REVENUE_EARNED.equals(bizMsg.C.no(no).ajeInvAcctClsCd_C1.getValue()) 
                    || (DR_CR_TP_DEBIT.equals(bizMsg.C.no(no).drCrTpCd_C1.getValue()) && AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(bizMsg.C.no(no).ajeInvAcctClsCd_C1.getValue()))) {

                // Deferred Revenue Recognition Record
                if (ZYPConstant.FLG_OFF_N.equals(bizMsg.C.no(no).dfrdRevGlStrgFlg_C1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeInvAcctClsCd_F1, AJE_INV_ACCT_CLS.REVENUE_EARNED);
                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeInvAcctDistPk_F1, bizMsg.C.no(no).ajeInvAcctDistPk_C1.getValue());

                    // START 2019/07/11 S.Takami [QC#51334,ADD]
                    if (!ZYPCommonFunc.hasValue(bizMsg.C.no(no).ajeCoaCmpyCd_C1) //
                        || !ZYPCommonFunc.hasValue(bizMsg.C.no(no).ajeCoaBrCd_C1) //
                        || !ZYPCommonFunc.hasValue(bizMsg.C.no(no).ajeCoaCcCd_C1) //
                        || !ZYPCommonFunc.hasValue(bizMsg.C.no(no).ajeCoaAcctCd_C1)//
                        || !ZYPCommonFunc.hasValue(bizMsg.C.no(no).ajeCoaProdCd_C1) //
                        || !ZYPCommonFunc.hasValue(bizMsg.C.no(no).ajeCoaChCd_C1) //
                        || !ZYPCommonFunc.hasValue(bizMsg.C.no(no).ajeCoaAfflCd_C1) //
                        || !ZYPCommonFunc.hasValue(bizMsg.C.no(no).ajeCoaProjCd_C1) //
                        || !ZYPCommonFunc.hasValue(bizMsg.C.no(no).ajeCoaExtnCd_C1)) {
                        String[] nineSegArray = bizMsg.C.no(no).xxScrItem61Txt_C1.getValue().split("\\.");
                        if (nineSegArray.length == 9) {
                            splitCOA9SegString(bizMsg.C.no(no));
                        }
                    }
                    // START 2019/07/11 S.Takami [QC#51334,End]
                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaCmpyCd_F1, bizMsg.C.no(no).ajeCoaCmpyCd_C1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaBrCd_F1, bizMsg.C.no(no).ajeCoaBrCd_C1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaCcCd_F1, bizMsg.C.no(no).ajeCoaCcCd_C1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaAcctCd_F1, bizMsg.C.no(no).ajeCoaAcctCd_C1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaProdCd_F1, bizMsg.C.no(no).ajeCoaProdCd_C1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaChCd_F1, bizMsg.C.no(no).ajeCoaChCd_C1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaAfflCd_F1, bizMsg.C.no(no).ajeCoaAfflCd_C1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaProjCd_F1, bizMsg.C.no(no).ajeCoaProjCd_C1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.F.no(idx).ajeCoaExtnCd_F1, bizMsg.C.no(no).ajeCoaExtnCd_C1.getValue());
                    idx++;
                }
            }
        }
        bizMsg.setCommitSMsg(true);
        globalMsg.F.setValidCount(idx);
    }
    
    /**
     * changeAcctCls
     * @param bizMsg
     * @return boolean
     */
    public static boolean changeAcctCls(NFCL3000CMsg bizMsg) {

        // change Accounting Class depending on Accounting Rule
        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {

            String xxLineNum = bizMsg.B.no(i).xxLineNum_B1.getValue();
            String invBolLineNum = bizMsg.B.no(i).invBolLineNum_B1.getValue();
            String invLineNum = bizMsg.B.no(i).invLineNum_B1.getValue();
            String AcctgRule = bizMsg.B.no(i).dfrdAcctgRuleCd_B1.getValue();

            if (!NFCL3000CommonLogic.setAcctCls(bizMsg, xxLineNum, invBolLineNum, invLineNum, AcctgRule, i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * setAcctCls
     * @param bizMsg
     * @param xxLineNum
     * @param invBolLineNum
     * @param invLineNum
     * @param AcctgRule
     * @param bIdx
     * @return boolean
     */
    public static boolean setAcctCls(NFCL3000CMsg bizMsg, String xxLineNum, String invBolLineNum, String invLineNum, String AcctgRule, int bIdx) {

        for(int i = 0; i < bizMsg.C.getValidCount(); i++) {

            String ajeInvAcctCls = bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue();
            Boolean changeFlg = false;

            if (AJE_INV_ACCT_CLS.REVENUE.equals(ajeInvAcctCls) || (DR_CR_TP_CREDIT.equals(bizMsg.C.no(i).drCrTpCd_C1.getValue()) && AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(ajeInvAcctCls))) {

                if(bizMsg.C.no(i).xxLineNum_C1.getValue().equals(xxLineNum)) {
                    if(bizMsg.C.no(i).invBolLineNum_C1.getValue().equals(invBolLineNum)) {
                        if(bizMsg.C.no(i).invLineNum_C1.getValue().equals(invLineNum)) {

                            // if Accounting Rule is deferred, set 'Unearned Revenue' to Accounting Class.
                            if (AJE_INV_ACCT_CLS.REVENUE.equals(ajeInvAcctCls) && DFRD_ACCTG_RULE.DEFERRED.equals(AcctgRule)) {
                                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).ajeInvAcctClsCd_C1, AJE_INV_ACCT_CLS.UNEARNED_REVENUE);
                                changeFlg = true;
                            } else if (AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(ajeInvAcctCls) && !DFRD_ACCTG_RULE.DEFERRED.equals(AcctgRule) ) {
                                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).ajeInvAcctClsCd_C1, AJE_INV_ACCT_CLS.REVENUE);
                                changeFlg = true;
                            }

                            if (changeFlg) {
                                // Set MDSE_CD and TOC_CD
                                NFCL3000CommonLogic.setMdseAndSlsRep(bizMsg.B, bizMsg.C.no(i));

                                // Set AJE_INV_ACCT_DIST
                                if(!NFCL3000CommonLogic.setDefaultAcctDist(bizMsg, bizMsg.C.no(i), bizMsg.C.no(i).ajeInvAcctClsCd_C1.getValue())) {
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
    // END   2016/09/26 S.Fujita [QC#13362,ADD]

    //START 2016/09/21 S.Yoshida [QC#11049,ADD]
    /**
     * checkGLDate
     * @param bizMsg NFCL3000CMsg
     * @return check result
     */
    private static boolean checkGLDate(NFCL3000CMsg bizMsg) {

        if(!ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H3.getValue())) {
            return true;
        }

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        BigDecimal periodAmt = ZYPCodeDataUtil.getNumConstValue("NFZC1030_PERIOD_CHNG_PRE_MONTH", glblCmpyCd);

        String procDt = bizMsg.procDt.getValue();
        String glDt = bizMsg.acctDt_H1.getValue();

        String thisMonthStartDt = procDt.substring(0, 6).concat("01");
        String thisMonthLastDt = getThisMonthLastDt(glblCmpyCd, procDt);
        String prevMonthPeriodDt = getPrevMonthPeriodDt(glblCmpyCd, procDt, periodAmt);

        String procDtYM = procDt.substring(0, 6);
        String prevMonthPeriodYM = prevMonthPeriodDt.substring(0, 6);

        if (procDtYM.equals(prevMonthPeriodYM)
                && procDt.compareTo(prevMonthPeriodDt) <= 0) {
            thisMonthStartDt = addMonth(procDt, -1, 1);
        }

        if (glDt.compareTo(thisMonthStartDt) >= 0
                && glDt.compareTo(thisMonthLastDt) <= 0) {
            return true;
        }

        bizMsg.acctDt_H1.setErrorInfo(1, "NFCM0801E", new String[]{"GL Date"});
        return false;
    }

    /**
     * getMonthPeriodDt
     * @param procDt Process Date
     * @param periodAmt Period Amount
     * @return Month Period Date
     */
    private static String getThisMonthLastDt(String glblCmpyCd, String procDt) {
        return addMonth(procDt, 0, 2);
    }

    /**
     * getMonthPeriodDt
     * @param procDt Process Date
     * @param periodAmt Period Amount
     * @return Month Period Date
     */
    private static String getPrevMonthPeriodDt(String glblCmpyCd, String procDt, BigDecimal periodAmt) {
        String dt = addMonth(procDt, -1, 2);
        return ZYPDateUtil.addBusinessDay(glblCmpyCd, dt, periodAmt.intValue());
    }

    /**
     * addMonth
     * @param procDt Process Date
     * @param addAmt Additional Amount for Month
     * @param dtOpt 1:first day, 2:last day, other:process day
     * @return after added month
     */
    private static String addMonth(String procDt, int addAmt, int dtOpt) {
        Calendar cal = Calendar.getInstance();
        int procDtYNum = Integer.parseInt(procDt.substring(0, 4));
        int procDtMNum = Integer.parseInt(procDt.substring(4, 6));

        cal.set(procDtYNum, procDtMNum, 1);
        cal.add(Calendar.MONTH, addAmt - 1);

        int dNum = 1;
        switch (dtOpt) {
            case 1:
                dNum = 1;
                break;
            case 2:
                dNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                break;
            default:
                dNum = Integer.parseInt(procDt.substring(6, 8));
        }
        cal.set(Calendar.DAY_OF_MONTH, dNum);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String a = sdf.format(cal.getTime());

        return sdf.format(cal.getTime());
    }
    //END   2016/09/21 S.Yoshida [QC#11049,ADD]

    // START 2016/10/06 S.Fujita [QC#10522,ADD]
    /**
     * makeTaxCalculationAPIParam
     * @param bizMsg NFCL3000CMsg
     * @param xxModeCd String
     * @return List<NWZC036101PMsg>
     */
    public static List<NWZC036101PMsg> makeTaxCalculationAPIParam(NFCL3000CMsg bizMsg, String xxModeCd) {

        List<NWZC036101PMsg> taxcalcPMsgList = new ArrayList<NWZC036101PMsg>();
        String frtTaxDummyMdseCd = ZYPCodeDataUtil.getVarCharConstValue("FRT_TAX_DUMMY_MDSE_CD", bizMsg.glblCmpyCd.getValue());
        if (frtTaxDummyMdseCd == null) {
            throw new S21AbendException("[Error]Not Found 'VAR_CHAR_CONST' : varCharConstNm=" + "FRT_TAX_DUMMY_MDSE_CD");
        }

        NWZC036101_taxCalculateInputLinePMsg taxCalcInputLinePMsg;

        // The parameter of Tax Calculation API is made by the unit of INV_BOL.
        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {

            if(!ZYPCommonFunc.hasValue(bizMsg.D.no(i).shipToCustAcctCd_D1.getValue())) {
                continue;
            }
            // START 2017/12/25 E.Kameishi [QC#20312,ADD]
            if (i == 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).shipToCustCd_D1, bizMsg.shipToCustCd_H2.getValue());
            }
            // END 2017/12/25 E.Kameishi [QC#20312,ADD]
            NWZC036101PMsg taxcalcPMsg = new NWZC036101PMsg();
            int taxCalcLineNum = 0;
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.slsDt, bizMsg.procDt.getValue());
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.xxModeCd,  xxModeCd);
            //Sell To Account Number
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsAcctNum_SE, bizMsg.sellToCustCd_H3.getValue());
            //Bill To Account Number
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.billToAcctNum, bizMsg.billToCustAcctCd_H3.getValue());
            //Bill To Location Number
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.billToLocNum, bizMsg.billToCustCd_H3.getValue());
            //Bill to  Vertex Group Exemption
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsTaxGrpExemCd_B, bizMsg.dsTaxGrpExemCd_H3.getValue());
            //Bill to  Vertex Group Exemption Resale Flg
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsTaxGrpExemReslFlg_B, bizMsg.dsTaxGrpExemReslFlg_H3.getValue());
            // START 2019/08/07 S.Takami [QC#52447,MOD]
            //Ship To Account Number
//            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsAcctNum_ST, bizMsg.shipToCustAcctCd_H2.getValue());
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsAcctNum_ST, bizMsg.D.no(i).shipToCustAcctCd_D1);
            //Ship To Location Number
//            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.shipToLocNum, bizMsg.shipToCustCd_H2.getValue());
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.shipToLocNum, bizMsg.D.no(i).shipToCustCd_D1);
            // END 2019/08/07 S.Takami [QC#52447,MOD]
            //Ship to Vertex Group Exemption
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam.put("shipToCustCd", bizMsg.D.no(i).shipToCustCd_D1.getValue());
            ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            S21SsmEZDResult shipto = NFCL3000Query.getInstance().searchTaxShipto(bizMsg, ssmParam);
            if (shipto.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) shipto.getResultObject();
                if(ZYPCommonFunc.hasValue((String)map.get(DS_TAX_GRP_EXEM_CD))) {
                    ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsTaxGrpExemCd_ST, (String)map.get(DS_TAX_GRP_EXEM_CD));
                // Account Level
                } else {
                    Map<String, Object> ssmParam2 = new HashMap<String, Object>();
                    ssmParam2.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
                    ssmParam2.put("acctNum", bizMsg.sellToCustCd_H3.getValue());
                    ssmParam2.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
                    S21SsmEZDResult acct = NFCL3000Query.getInstance().searchTaxAcct(bizMsg, ssmParam2);
                    if (acct.isCodeNormal()) {
                        Map<String, Object> map2 = (Map<String, Object>) acct.getResultObject();
                        if(ZYPCommonFunc.hasValue((String)map2.get(DS_TAX_GRP_EXEM_CD))) {
                            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsTaxGrpExemCd_ST, (String)map2.get(DS_TAX_GRP_EXEM_CD));
                        }
                    }
                }
                //Ship To Tax Area ID 
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.geoCd_ST, (String)map.get(GEO_CD));
                //Ship To Inside City Limit Flag
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsInsdCtyLimitFlg_ST, (String)map.get(DS_INSD_CTY_LIMIT_FLG));
                //Ship To Special Tax Area ID
                // START 2019/08/07 S.Takami [QC#52447,MOD]
//                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.taxAreaId_ST,
//                        getTaxAreaId(bizMsg.glblCmpyCd.getValue(), bizMsg.shipToCtyAddr_H2.getValue(), (BigDecimal)map.get(CNTY_PK), bizMsg.shipToStCd_H2.getValue()));
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.taxAreaId_ST,
                        getTaxAreaId(bizMsg.glblCmpyCd.getValue(), bizMsg.D.no(i).shipToCtyAddr_D1.getValue(), (BigDecimal)map.get(CNTY_PK), bizMsg.D.no(i).shipToStCd_D1.getValue()));
                // START 2019/08/07 S.Takami [QC#52447,MOD]
            }

            //Tax Calculation Flag
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.taxCalcFlg, bizMsg.taxCalcFlg_H1.getValue());
            //Tax Exemption
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.taxExemFlg, bizMsg.taxExemFlg_H1.getValue());
            //Tax Exemption Reason Code
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.taxExemRsnCd, bizMsg.taxExemRsnCd_H1.getValue());
            //Transaction Date
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.trxDt, bizMsg.invDt_H1.getValue());
            //Tax Calculate Header Num
            if(ZYPCommonFunc.hasValue(bizMsg.invNum_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.xxTaxCalcHdrNum, bizMsg.invNum_H1.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.xxTaxCalcHdrNum, TAX_HEADER_NUM);
            }

            //Ship to First Line Address
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.firstLineAddr_ST, bizMsg.D.no(i).shipToFirstLineAddr_D1.getValue());
            //Ship to Second Line Address
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.scdLineAddr_ST, bizMsg.D.no(i).shipToScdLineAddr_D1.getValue());
            //Ship to City Address
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.ctyAddr_ST, bizMsg.D.no(i).shipToCtyAddr_D1.getValue());
            //Ship to State Code
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.stCd_ST, bizMsg.D.no(i).shipToStCd_D1.getValue());
            //Ship To County Name
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.cntyNm_ST, bizMsg.D.no(i).shipToCntyNm_D1.getValue());
            //Ship To Post Code
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.postCd_ST, bizMsg.D.no(i).shipToPostCd_D1.getValue());
            //Ship To Country Code
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.ctryCd_ST, bizMsg.D.no(i).shipToCtryCd_D1.getValue());

            Map<String, Object> ssmParam3 = new HashMap<String, Object>();
            ssmParam3.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            // Add Start  2017/07/18 CSA Defect#19781
            String invBolLineNumD1 = bizMsg.D.no(i).invBolLineNum_D1.getValue();
            String slsRepTocCdB1 = null;
            for(int j = 0; j < bizMsg.B.getValidCount(); j++) {
                if (invBolLineNumD1.equals(bizMsg.B.no(j).invBolLineNum_B1.getValue())) {
                    slsRepTocCdB1 = bizMsg.B.no(j).slsRepTocCd_B1.getValue();
                    break;
                }
            }
            if (slsRepTocCdB1 == null) {
                slsRepTocCdB1 = bizMsg.slsRepTocCd_H1.getValue();
            }
            // Add End    2017/07/18 CSA Defect#19781
            // Mod Start  2017/07/18 CSA Defect#19781
            //ssmParam3.put("tocCd", bizMsg.slsRepTocCd_H1.getValue());
            ssmParam3.put("tocCd", slsRepTocCdB1);
            // Mod End    2017/07/18 CSA Defect#19781
            ssmParam3.put("invDt", bizMsg.invDt_H1.getValue());
            S21SsmEZDResult slsrep = NFCL3000Query.getInstance().searchSalesRepAdress(bizMsg, ssmParam3);
            if (slsrep.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) slsrep.getResultObject();

                //Sales Rep Tax Area ID
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.geoCd_SR, (String)map.get(GEO_CD));
                //Sales Rep Inside City Limit Flag
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.dsInsdCtyLimitFlg_SR, (String)map.get(DS_INSD_CTY_LIMIT_FLG));
                //Sales Rep First Line Address
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.firstLineAddr_SR, (String)map.get(FIRST_LINE_ADDR));
                //Sales Rep Second Line Address
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.scdLineAddr_SR, (String)map.get(SCD_LINE_ADDR));
                //Sales Rep City Address
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.ctyAddr_SR, (String)map.get(CTY_ADDR));
                //Sales Rep State Code
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.stCd_SR, (String)map.get(ST_CD));
                if(ZYPCommonFunc.hasValue((BigDecimal)map.get(CNTY_PK))) {
                    //Sales Rep County Name
                    ZYPEZDItemValueSetter.setValue(taxcalcPMsg.cntyNm_SR, getCountyName(bizMsg,(BigDecimal)map.get(CNTY_PK)));
                }
                //Sales Rep Post Code
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.postCd_SR, (String)map.get(POST_CD));
                //Sales Rep Country Code
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.ctryCd_SR, (String)map.get(CTRY_CD));
            }

            // get ship from
            Map<String, Object> ssmParam2 = new HashMap<String, Object>();
            ssmParam2.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam2.put("invtyLocCd", bizMsg.D.no(i).shipFromInvtyLocCd_D1.getValue());
            S21SsmEZDResult shipFrom = NFCL3000Query.getInstance().searchDsInvtyLoc(bizMsg, ssmParam2);
            if (shipFrom.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) shipFrom.getResultObject();
                //Ship from Tax Area ID
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.geoCd_SF, (String)map.get(GEO_CD));
                //Ship from First Line Address
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.firstLineAddr_SF, (String)map.get(FIRST_LINE_ADDR));
                //Ship from Second Line Address
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.scdLineAddr_SF, (String)map.get(SCD_LINE_ADDR));
                //Ship from City Address
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.ctyAddr_SF, (String)map.get(CTY_ADDR));
                //Ship from State Code
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.stCd_SF, (String)map.get(ST_CD));
                if(ZYPCommonFunc.hasValue((BigDecimal)map.get(CNTY_PK))) {
                    //Ship from County Name
                    ZYPEZDItemValueSetter.setValue(taxcalcPMsg.cntyNm_SF, getCountyName(bizMsg,(BigDecimal)map.get(CNTY_PK)));
                }
                //Ship from Post Code
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.postCd_SF, (String)map.get(POST_CD));
                //Ship from Country Code
                ZYPEZDItemValueSetter.setValue(taxcalcPMsg.ctryCd_SF, (String)map.get(CTRY_CD));
            }

// START 2018/05/22 Y.Matsui [QC#21841,DEL]
//            // START 2017/12/25 E.Kameishi [QC#20312,ADD]
//            if (!NWZC036101Constant.PROC_MODE_DISTRIBUTE_TAX.equals(xxModeCd)) {
//                // first line is freight
//                taxCalcInputLinePMsg = (NWZC036101_taxCalculateInputLinePMsg) taxcalcPMsg.taxCalculateInputLine.no(taxCalcLineNum++);
//                //Tax Calculate Line Number
//                ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.xxTaxCalcLineNum_A, Integer.toString(taxCalcLineNum));
//                //Merchandise Code
//                ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.mdseCd_A, frtTaxDummyMdseCd);
//                //Shipped Quantity
//                ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.shipQty_A, BigDecimal.ONE);
//                //Function Net Unit Price Amount
//                ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, bizMsg.D.no(i).shipDealFrtAmt_D1.getValue());
//                //Sales Amount
//                ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.slsAmt_A, bizMsg.D.no(i).shipDealFrtAmt_D1.getValue());
//                // Add Start   2017/07/18 CSA Defect#19781
//                if (slsrep.isCodeNormal()) {
//                    Map<String, Object> map = (Map<String, Object>) slsrep.getResultObject();
//    
//                    //Sales Rep Tax Area ID
//                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.geoCd_AR, (String)map.get(GEO_CD));
//                    //Sales Rep Inside City Limit Flag
//                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.dsInsdCtyLimitFlg_AR, (String)map.get(DS_INSD_CTY_LIMIT_FLG));
//                    //Sales Rep First Line Address
//                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.firstLineAddr_AR, (String)map.get(FIRST_LINE_ADDR));
//                    //Sales Rep Second Line Address
//                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.scdLineAddr_AR, (String)map.get(SCD_LINE_ADDR));
//                    //Sales Rep City Address
//                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.ctyAddr_AR, (String)map.get(CTY_ADDR));
//                    //Sales Rep State Code
//                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.stCd_AR, (String)map.get(ST_CD));
//                    if(ZYPCommonFunc.hasValue((BigDecimal)map.get(CNTY_PK))) {
//                        //Sales Rep County Name
//                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.cntyNm_AR, getCountyName(bizMsg,(BigDecimal)map.get(CNTY_PK)));
//                    }
//                    //Sales Rep Post Code
//                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.postCd_AR, (String)map.get(POST_CD));
//                    //Sales Rep Country Code
//                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.ctryCd_AR, (String)map.get(CTRY_CD));
//                }
//                // Add End   2017/07/18 CSA Defect#19781
//    
//                //get mdse info
//                getMdseInfoForTaxParam(bizMsg, frtTaxDummyMdseCd, taxCalcInputLinePMsg);
//    
//                taxcalcPMsg.taxCalculateInputLine.setValidCount(taxCalcLineNum);
//            }
//            // END 2017/12/25 E.Kameishi [QC#20312,ADD]
// END   2018/05/22 Y.Matsui [QC#21841,DEL]

            // START 2019/06/04 S.Takami [QC#50601,ADD]
            ZYPEZDItemValueSetter.setValue(taxcalcPMsg.leasePrchOptCd, bizMsg.leasePrchOptCd_H1);
            // END 2019/06/04 S.Takami [QC#50601,ADD]

            // Tax Calculate Input Line
            for(int j = 0; j < bizMsg.A.getValidCount(); j++) {
                if(bizMsg.A.no(j).invBolLineNum_A1.getValue().equals(bizMsg.D.no(i).invBolLineNum_D1.getValue())) {

                    taxCalcInputLinePMsg = (NWZC036101_taxCalculateInputLinePMsg) taxcalcPMsg.taxCalculateInputLine.no(taxCalcLineNum++);
                    //Tax Calculate Line Number
                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.xxTaxCalcLineNum_A, Integer.toString(taxCalcLineNum));
                    //Merchandise Code
                    // START 2019/06/04 S.Takami [QC#50601,MOD]
//                   ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.mdseCd_A, bizMsg.A.no(j).mdseCd_A1.getValue());
                    String apiMdseCd = getTaxCalcItemCode(bizMsg, j);
                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.mdseCd_A, apiMdseCd);
                    // END 2019/06/04 S.Takami [QC#50601,MOD]

                    //get mdse info
                    // START 2019/06/04 S.Takami [QC#50601,MOD]
//                    getMdseInfoForTaxParam(bizMsg, bizMsg.A.no(j).mdseCd_A1.getValue(), taxCalcInputLinePMsg);
                    getMdseInfoForTaxParam(bizMsg, taxCalcInputLinePMsg.mdseCd_A.getValue(), taxCalcInputLinePMsg);
                    // END 2019/06/04 S.Takami [QC#50601,MOD]

                    //Shipped Quantity
                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.shipQty_A, bizMsg.A.no(j).shipQty_A1.getValue());
                    //Function Net Unit Price Amount
                    // START 2019/09/21 S.Takami [QC#53633,MOD]
//                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, bizMsg.A.no(j).dealNetUnitPrcAmt_A1.getValue());
                    if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.A.no(j).xxMstChkFlg_A1.getValue())) {
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, bizMsg.A.no(j).invLineDealTaxAmt_A1.getValue());
                    } else {
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, bizMsg.A.no(j).dealNetUnitPrcAmt_A1.getValue());
                    }
                    // END 2019/09/21 S.Takami [QC#53633,MOD]
                    //Sales Amount
                    // START 2019/10/02 S.Takami [QC#53633-2,MOD]
//                    ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.slsAmt_A, bizMsg.A.no(j).invLineDealNetAmt_A1.getValue());
                    if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.A.no(j).xxMstChkFlg_A1.getValue())) {
                        BigDecimal invLineDealNetAmt = getInvLineNetAmount(bizMsg, j, true);
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.slsAmt_A, invLineDealNetAmt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.slsAmt_A, bizMsg.A.no(j).invLineDealNetAmt_A1.getValue());
                    }
                    // END 2019/10/02 S.Takami [QC#53633-2,MOD]
                    // START 2017/12/25 E.Kameishi [QC#20312,ADD]
                    if (NWZC036101Constant.PROC_MODE_DISTRIBUTE_TAX.equals(xxModeCd)) {
                        // START 2020/03/27 [QC#56158,MOD]
                        if (INV_TP.CREDIT_MEMO.equals(bizMsg.invTpCd_H1.getValue())) {
                            if (bizMsg.A.no(j).invLineDealTaxAmt_A1.getValue().compareTo(BigDecimal.ZERO) > 0) {
                                ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.taxAmt_A, bizMsg.A.no(j).invLineDealTaxAmt_A1.getValue().negate());
                            } else {
                                ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.taxAmt_A, bizMsg.A.no(j).invLineDealTaxAmt_A1.getValue());
                            }
                        } else {
                            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.taxAmt_A, bizMsg.A.no(j).invLineDealTaxAmt_A1.getValue());
                        }
                        // END  2020/03/27 [QC#56158,MOD]
                    }
                    // END 2017/12/25 E.Kameishi [QC#20312,ADD]
                    // Add Start  2017/07/18 CSA Defect#19781
                    if (slsrep.isCodeNormal()) {
                        Map<String, Object> map = (Map<String, Object>) slsrep.getResultObject();

                        //Sales Rep Tax Area ID
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.geoCd_AR, (String)map.get(GEO_CD));
                        //Sales Rep Inside City Limit Flag
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.dsInsdCtyLimitFlg_AR, (String)map.get(DS_INSD_CTY_LIMIT_FLG));
                        //Sales Rep First Line Address
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.firstLineAddr_AR, (String)map.get(FIRST_LINE_ADDR));
                        //Sales Rep Second Line Address
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.scdLineAddr_AR, (String)map.get(SCD_LINE_ADDR));
                        //Sales Rep City Address
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.ctyAddr_AR, (String)map.get(CTY_ADDR));
                        //Sales Rep State Code
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.stCd_AR, (String)map.get(ST_CD));
                        if(ZYPCommonFunc.hasValue((BigDecimal)map.get(CNTY_PK))) {
                            //Sales Rep County Name
                            ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.cntyNm_AR, getCountyName(bizMsg,(BigDecimal)map.get(CNTY_PK)));
                        }
                        //Sales Rep Post Code
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.postCd_AR, (String)map.get(POST_CD));
                        //Sales Rep Country Code
                        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.ctryCd_AR, (String)map.get(CTRY_CD));
                    }
                    // Add End  2017/07/18 CSA Defect#19781

                    taxcalcPMsg.taxCalculateInputLine.setValidCount(taxCalcLineNum);
                }
            }

            // START 2018/05/22 Y.Matsui [QC#21841,ADD]
            if (taxcalcPMsg.taxCalculateInputLine.getValidCount() == 0) {
                continue;
            }
            // END   2018/05/22 Y.Matsui [QC#21841,ADD]

            taxcalcPMsgList.add(taxcalcPMsg);
        }
        return taxcalcPMsgList;
    }

    /**
     * getMdseInfoForTaxParam
     * @param bizMsg NFCL3000CMsg
     * @param mdseCd String
     * @param taxCalcInputLinePMsg NWZC036101_taxCalculateInputLinePMsg
     */
    public static void getMdseInfoForTaxParam(NFCL3000CMsg bizMsg, String mdseCd, NWZC036101_taxCalculateInputLinePMsg taxCalcInputLinePMsg) {

        MDSETMsg mdseTMsg = getMdseInfo(bizMsg, mdseCd);

        String svcAllocTpCd = null;
        String svcAllocTrxTpNm = null;
        String taxExemTpCd  = null;
        if (mdseTMsg != null) {
            svcAllocTpCd = mdseTMsg.svcAllocTpCd.getValue();
            taxExemTpCd  = mdseTMsg.taxExemTpCd.getValue();
        }
        if (svcAllocTpCd != null) {
            SVC_ALLOC_TPTMsg svcAllocTpTMsg = new SVC_ALLOC_TPTMsg();
            ZYPEZDItemValueSetter.setValue(svcAllocTpTMsg.glblCmpyCd,   bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(svcAllocTpTMsg.svcAllocTpCd, svcAllocTpCd);
            svcAllocTpTMsg = (SVC_ALLOC_TPTMsg) S21CacheTBLAccessor.findByKey(svcAllocTpTMsg);
            if (svcAllocTpTMsg != null) {
                svcAllocTrxTpNm = svcAllocTpTMsg.svcAllocTrxTpNm.getValue();
            }
        }
        if (!hasValue(svcAllocTrxTpNm)) {
            // default set if value is null
            svcAllocTrxTpNm = ZYPCodeDataUtil.getVarCharConstValue("DEFAULT_TAX_TRX_TP", bizMsg.glblCmpyCd.getValue());
        }

        //Service Allocation Type
        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.svcAllocTpCd_A, svcAllocTpCd);
        //Trx Type
        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.svcAllocTrxTpNm_A, svcAllocTrxTpNm);
        //Product Tax Code
        ZYPEZDItemValueSetter.setValue(taxCalcInputLinePMsg.taxExemTpCd_A, taxExemTpCd);
    }

    /**
     * getMdseInfo
     * @param bizMsg NFCL3000CMsg
     * @param mdseCd String
     * @return getMdseInfo MDSETMsg
     */
    public static MDSETMsg getMdseInfo(NFCL3000CMsg bizMsg, String mdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd,     mdseCd);
        mdseTMsg =(MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            // get mdsecd
            ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
            ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.ordTakeMdseCd, mdseCd);
            ordTakeMdseTMsg =(ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseTMsg);

            mdseTMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd,     ordTakeMdseTMsg.mdseCd.getValue());
            mdseTMsg =(MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        }

        return mdseTMsg;
    }

    /**
     * getTaxCalculationAPIResult
     * @param bizMsg NFCL3000CMsg
     * @param taxcalcPMsgItr Iterator<NWZC036101PMsg>
     * @return boolean
     */
    public static boolean getTaxCalculationAPIResult(NFCL3000CMsg bizMsg, Iterator<NWZC036101PMsg> taxcalcPMsgItr, String xxModeCd) {

        boolean isSuccess = true;

        NWZC036101_taxCalculateOutputLinePMsg taxcalcOutputLinePMsg;
        BigDecimal taxAmt;
        BigDecimal taxPct;

        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {

            // START 2018/05/22 Y.Matsui [QC#21841,ADD]
            if (!taxcalcPMsgItr.hasNext()) {
                break;
            }
            // END   2018/05/22 Y.Matsui [QC#21841,ADD]

            NWZC036101PMsg taxcalcPMsg = taxcalcPMsgItr.next();

            if (taxcalcPMsg.xxMsgIdList.getValidCount() > 0) {
                for (int j = 0; j < taxcalcPMsg.xxMsgIdList.getValidCount(); j++) {
                    String errId =taxcalcPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(errId);
                    return false;
                }
            }

            boolean firstLineFlg = true;
            int taxCalcLineNum = 0;

            for(int k = 0; k < bizMsg.A.getValidCount(); k++) {
                if(bizMsg.A.no(k).invBolLineNum_A1.getValue().equals(bizMsg.D.no(i).invBolLineNum_D1.getValue())) {
// START 2018/05/22 Y.Matsui [QC#21841,DEL]
//                    // tax for InvBol
//                    // START 2018/01/24 E.Kameishi [QC#20312-1,MOD]
//                    if (!NWZC036101Constant.PROC_MODE_DISTRIBUTE_TAX.equals(xxModeCd)) {
//                        if (firstLineFlg) {
//                            firstLineFlg = false;
//    
//                            taxcalcOutputLinePMsg = (NWZC036101_taxCalculateOutputLinePMsg) taxcalcPMsg.taxCalculateOutputLine.no(taxCalcLineNum++);
//                            taxAmt  = taxcalcOutputLinePMsg.invLineFuncTaxAmt.getValue();
//                            if (taxAmt == null) {
//                                taxAmt = BigDecimal.ZERO;
//                            }
//                            taxPct  = taxcalcOutputLinePMsg.xxTaxCalcLineTaxPct.getValue();
//                            if (taxPct == null) {
//                                taxPct = BigDecimal.ZERO;
//                            }
//    
//                            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).frtDealTaxAmt_D1, taxAmt);
//                            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).frtTaxPct_D1, taxPct);
//    
//                            // tax detail
//                            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).dealSlsTaxAmt_D1, taxcalcOutputLinePMsg.taxAmt_01.getValue());
//                            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).dealSlsTaxAmt_D2, taxcalcOutputLinePMsg.taxAmt_02.getValue());
//                            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).dealSlsTaxAmt_D3, taxcalcOutputLinePMsg.taxAmt_03.getValue());
//                            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).slsTaxPct_D1, taxcalcOutputLinePMsg.taxPct_01.getValue());
//                            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).slsTaxPct_D2, taxcalcOutputLinePMsg.taxPct_02.getValue());
//                            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).slsTaxPct_D3, taxcalcOutputLinePMsg.taxPct_03.getValue());
//                            // START 2017/03/09 [QC#17901,ADD]
//                            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).taxAreaId_D1, taxcalcOutputLinePMsg.taxAreaId.getValue()); 
//                            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).taxRsltDescTxt_D1, taxcalcOutputLinePMsg.xxVtxRsltCd_01.getValue());
//                            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).taxRsltDescTxt_D2, taxcalcOutputLinePMsg.xxVtxRsltCd_02.getValue());
//                            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).taxRsltDescTxt_D3, taxcalcOutputLinePMsg.xxVtxRsltCd_03.getValue());
//                            // END   2017/03/09 [QC#17901,ADD]
//                        }
//                    }
//                    // END   2018/01/24 E.Kameishi [QC#20312-1,MOD]
// END   2018/05/22 Y.Matsui [QC#21841,DEL]

                    // tax for InvLine
                    taxcalcOutputLinePMsg = (NWZC036101_taxCalculateOutputLinePMsg) taxcalcPMsg.taxCalculateOutputLine.no(taxCalcLineNum++);
                    taxAmt  = taxcalcOutputLinePMsg.invLineFuncTaxAmt.getValue();
                    if (taxAmt == null) {
                        taxAmt = BigDecimal.ZERO;
                    }
                    taxPct  = taxcalcOutputLinePMsg.xxTaxCalcLineTaxPct.getValue();
                    if (taxPct == null) {
                        taxPct = BigDecimal.ZERO;
                    }
                    // START 2018/03/29 E.Kameishi [QC#24390,ADD]
                    if (NWZC036101Constant.PROC_MODE_DISTRIBUTE_TAX.equals(xxModeCd)) {
                        if (INV_TP.CREDIT_MEMO.equals(bizMsg.invTpCd_H1.getValue()) && taxAmt.compareTo(BigDecimal.ZERO) < 0) {
                            taxAmt = taxAmt.negate();
                        }
                    }
                    // END 2018/03/29 E.Kameishi [QC#24390,ADD]
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(k).invLineDealTaxAmt_A1, taxAmt);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(k).taxPct_A1, taxPct);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(k).taxCalcGeoCd_A1, taxcalcOutputLinePMsg.taxAreaId.getValue());

                    // tax detail
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(k).dealSlsTaxAmt_A1, taxcalcOutputLinePMsg.taxAmt_01.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(k).dealSlsTaxAmt_A2, taxcalcOutputLinePMsg.taxAmt_02.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(k).dealSlsTaxAmt_A3, taxcalcOutputLinePMsg.taxAmt_03.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(k).slsTaxPct_A1, taxcalcOutputLinePMsg.taxPct_01.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(k).slsTaxPct_A2, taxcalcOutputLinePMsg.taxPct_02.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(k).slsTaxPct_A3, taxcalcOutputLinePMsg.taxPct_03.getValue());
                    // START 2017/03/09 [QC#17901,ADD]
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(k).taxAreaId_A1, taxcalcOutputLinePMsg.taxAreaId.getValue()); 
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(k).taxRsltDescTxt_A1, taxcalcOutputLinePMsg.xxVtxRsltCd_01.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(k).taxRsltDescTxt_A2, taxcalcOutputLinePMsg.xxVtxRsltCd_02.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(k).taxRsltDescTxt_A3, taxcalcOutputLinePMsg.xxVtxRsltCd_03.getValue());
                    // END   2017/03/09 [QC#17901,ADD]

                    // START 2018/05/22 Y.Matsui [QC#21841,ADD]
                    taxAmt = taxcalcOutputLinePMsg.invLineFuncTaxAmt.getValue();
                    if (taxAmt == null) {
                        taxAmt = BigDecimal.ZERO;
                    }
                    if (isFreightInvoiceLine(bizMsg, bizMsg.A.no(k))) {
                        BigDecimal frtDealTaxAmt = bizMsg.D.no(i).frtDealTaxAmt_D1.getValue();
                        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).frtDealTaxAmt_D1, frtDealTaxAmt.add(taxAmt));
                    }
                    BigDecimal totBolDealTaxAmt = bizMsg.D.no(i).totBolDealTaxAmt_D1.getValue();
                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).totBolDealTaxAmt_D1, totBolDealTaxAmt.add(taxAmt));
                    // END   2018/05/22 Y.Matsui [QC#21841,ADD]
                }
            }
        }
        return isSuccess;
    }

    /**
     * initialTaxAmt
     * @param bizMsg NFCL3000CMsg
     * @param xxModeCd String
     */
//    public static void initialTaxAmt(NFCL3000CMsg bizMsg) {
    public static void initialTaxAmt(NFCL3000CMsg bizMsg, String xxModeCd) {

        // START 2019/11/21 H.Nagashima [QC#54746,ADD]
        if (NWZC036101Constant.PROC_MODE_DISTRIBUTE_TAX.equals(xxModeCd)) {
            for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).totBolDealTaxAmt_D1, BigDecimal.ZERO);
            }
            return;
        }
        // END 2019/11/21 H.Nagashima [QC#54746,ADD]

        // initial tax for InvBol
        for(int i = 0; i < bizMsg.D.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).frtDealTaxAmt_D1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).frtTaxPct_D1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).dealSlsTaxAmt_D1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).dealSlsTaxAmt_D2, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).dealSlsTaxAmt_D3, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).slsTaxPct_D1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).slsTaxPct_D2, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).slsTaxPct_D3, BigDecimal.ZERO);
            // START 2018/05/22 Y.Matsui [QC#21841,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).totBolDealTaxAmt_D1, BigDecimal.ZERO);
            // END   2018/05/22 Y.Matsui [QC#21841,ADD]
        }

        // initial tax for InvLine
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invLineDealTaxAmt_A1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).taxPct_A1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dealSlsTaxAmt_A1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dealSlsTaxAmt_A2, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dealSlsTaxAmt_A3, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).slsTaxPct_A1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).slsTaxPct_A2, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).slsTaxPct_A3, BigDecimal.ZERO);

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).taxCalcGeoCd_A1, BLANK);
        }
    }
    // END   2016/10/06 S.Fujita [QC#10522,ADD]

    // START 2016/10/13 S.Yoshida [QC#14575,ADD]
    /**
     * 
     * @param msgAry
     * @param checkItemNm
     */
    public static void deleteMsgAry(EZDMsgArray msgAry, String checkItemNm, String checkedValue) {

        if (msgAry.getValidCount() == 0) {
            return;
        }

        List<Integer> selectedRows = //
            ZYPTableUtil.getSelectedRows(msgAry, checkItemNm, checkedValue);

        if (selectedRows == null //
                || selectedRows.isEmpty()) {
            return;
        }

        ZYPTableUtil.deleteRows(msgAry, selectedRows);
    }

//    /**
//     * loadOnePageToCMsg
//     * @param cMsg EZDCMsg
//     * @param cMsgArray EZDCMsgArray
//     * @param sMsgArray EZDSMsgArray
//     * @param xxPageShowFromNum EZDCBigDecimalItem
//     * @param xxPageShowToNum EZDCBigDecimalItem
//     * @param xxPageShowOfNum EZDCBigDecimalItem
//     */
//    public static void loadOnePageToCMsg(EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray,
//            EZDCBigDecimalItem xxPageShowFromNum, EZDCBigDecimalItem xxPageShowToNum, EZDCBigDecimalItem xxPageShowOfNum) {
//
//        ZYPTableUtil.clear(cMsgArray);
//        xxPageShowToNum.clear();
//        xxPageShowOfNum.clear();
//
//        int maxDisplayRows = cMsgArray.length();
//        int startIndex = (xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;
//
//        int i = startIndex;
//        for (; i < startIndex + cMsgArray.length(); i++) {
//
//            if (i < sMsgArray.getValidCount()) {
//
//                EZDMsg sLineMsg = sMsgArray.get(i);
//                int indexOfCMsg = i - startIndex;
//
//                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);
//
//            } else {
//
//                break;
//            }
//        }
//
//        cMsgArray.setValidCount(i - startIndex);
//        xxPageShowFromNum.setValue(startIndex + 1);
//        xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
//        xxPageShowOfNum.setValue(sMsgArray.getValidCount());
//    }
//
//    /**
//     * Update the global Message.
//     * @param bizMsg NFCL3000CMsg
//     * @param glblMsg NFCL3000SMsg
//     * @param xxPageShowFromNum EZDCBigDecimalItem
//     */
//    public static void updateGlblMsg(EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray,
//            EZDCBigDecimalItem xxPageShowFromNum) {
//
//        int ixG = xxPageShowFromNum.getValueInt() - 1;
//        for (int i = 0; i < cMsgArray.getValidCount(); i++) {
//            EZDMsg.copy(cMsgArray.get(i), null, sMsgArray.get(ixG + i), null);
//        }
//    }
    // END   2016/10/13 S.Yoshida [QC#14575,ADD]

    // START 2016/10/14 S.Fujita [QC#10281,ADD]
    /**
     * setAmtCsmp
     * @param bizMsg NFCL3000CMsg
     */
    public static void setAmtCsmp(NFCL3000CMsg bizMsg) {

        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (DS_INV_TP.CM_CSMP_CREDITS.equals(bizMsg.dsInvTpCd_H1.getValue()) || DS_INV_TP.INVOICE_CSMP_CREDITS.equals(bizMsg.dsInvTpCd_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).funcCsmpCrAmt_A1, BigDecimal.ZERO);
            } else {
                if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).dlrRefNum_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).funcCsmpCrAmt_A1, BigDecimal.ZERO);
                }
            }
        }
    }
    // END   2016/10/14 S.Fujita [QC#10281,ADD]

    // START 2016/10/21 T.Murai [QC#13656, ADD]
    /**
     * has AR_TRX_BAL Date by InvNum
     * @param bizMsg NFCL3000CMsg
     * @return true - has
     */
    public static boolean hasTrxBal(NFCL3000CMsg bizMsg) {
        AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("arTrxNum01", bizMsg.invNum_H1.getValue());
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");

        AR_TRX_BALTMsgArray arTrxBalTMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (arTrxBalTMsg == null || arTrxBalTMsg.getValidCount() < 1) {
            return false;
        }
        return true;

    }
    // END 2016/10/21 T.Murai [QC#13656, ADD]

    // START 2016/10/26 T.Murai [QC#13639,ADD]
    /**
     * setCpoNum by Header source Num
     * @param bizMsg NFCL3000CMsg
     */
    public static void setCpoNum(NFCL3000CMsg bizMsg) {
        if (SYS_SRC.S21_ORDER.equals(bizMsg.sysSrcCd_H1.getValue()) && ZYPCommonFunc.hasValue(bizMsg.srcSysDocNum_H1)) {
            String cpoOrderNum = bizMsg.srcSysDocNum_H1.getValue();
            
            if (ZYPCommonFunc.isNumeric(cpoOrderNum) //
                    && cpoOrderNum.length() < new INV_LINETMsg().getAttr("cpoOrdNum").getDigit()) {
                for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).cpoOrdNum_A1, cpoOrderNum);
                }
            }
        }
    }

    /**
     * set Display CpoDtlLineNum by lineNum and lineSubNum
     * @param bizMsg NFCL3000CMsg
     */
    public static void setCpoDtlLineNum(NFCL3000CMsg bizMsg) {
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            String dplyLineNum = "";
            
            // START 2016/11/17 [QC#15987, MOD]
//            String lineNum = bizMsg.A.no(i).cpoDtlLineNum_A1.getValue();
//            String lineSubNum = bizMsg.A.no(i).cpoDtlLineSubNum_A1.getValue();
//            
//            if (ZYPCommonFunc.hasValue(lineNum) && ZYPCommonFunc.hasValue(lineSubNum)) {
//
//                lineNum = lineNum.replaceFirst("0+", "");
//                lineSubNum = lineSubNum.replaceFirst("0+", "");
//                dplyLineNum = ZYPCommonFunc.concatString(lineNum, ".", lineSubNum);
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxDplyOrdLineNum_A1, dplyLineNum);
//            }
            String posnNumStr = bizMsg.A.no(i).dsOrdPosnNum_A1.getValue();
            BigDecimal lineNum = bizMsg.A.no(i).dsCpoLineNum_A1.getValue();
            BigDecimal lineSubNum = bizMsg.A.no(i).dsCpoLineSubNum_A1.getValue();
            
            if (ZYPCommonFunc.hasValue(posnNumStr)) {
                dplyLineNum = posnNumStr;

                if (ZYPCommonFunc.hasValue(lineNum)) {
                    dplyLineNum = ZYPCommonFunc.concatString(dplyLineNum, ".", lineNum.toPlainString());
                }
                if (ZYPCommonFunc.hasValue(lineSubNum)) {
                    dplyLineNum = ZYPCommonFunc.concatString(dplyLineNum, ".", lineSubNum.toPlainString());
                }
                
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxDplyOrdLineNum_A1, dplyLineNum);
            }
            
            // START 2016/11/17 [QC#15987, MOD]
        }
    }
//
//    /**
//     * set CpoDtlLineNum and SubNum by Display Number
//     * @param bizMsg NFCL3000CMsg
//     */
//    public static void setLineAndSubLineNum(NFCL3000_ACMsg aLineMsg) {
//        String xxLineNum = aLineMsg.xxDplyOrdLineNum_A1.getValue();
//
//        String[] lineNumArray = xxLineNum.split("\\.");
//
//        String lineNum = "";
//        String lineSubNum = "";
//
//        if (lineNumArray.length == 2) {
//            lineNum = lineNumArray[0];
//            lineSubNum = lineNumArray[1];
//
//            if (lineNum.length() <= CPO_LINE_NUM_LENG && lineSubNum.length() <= CPO_LINE_NUM_LENG) {
//                lineNum = ZYPCommonFunc.leftPad(lineNum, CPO_LINE_NUM_LENG, NFCL3000Constant.ZERO);
//                lineSubNum = ZYPCommonFunc.leftPad(lineSubNum, CPO_LINE_NUM_LENG, NFCL3000Constant.ZERO);
//            }
//        }
//        ZYPEZDItemValueSetter.setValue(aLineMsg.cpoDtlLineNum_A1, lineNum);
//        ZYPEZDItemValueSetter.setValue(aLineMsg.cpoDtlLineSubNum_A1, lineSubNum);
//    }
    // END   2016/10/26 T.Murai [QC#13639,ADD]

    // START 2016/11/17 T.Murai [QC#14096,ADD]
    /**
     * set Serial Number
     * @param bizMsg NFCL3000CMsg
     */
    public static void setSerNum(NFCL3000CMsg bizMsg) {

        if (SYS_SRC.S21_ORDER.equals(bizMsg.sysSrcCd_H1.getValue())) {

            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NFCL3000_ACMsg lineMsg = bizMsg.A.no(i);

                String serNum = NFCL3000Query.getInstance().getSerNumINV(bizMsg, lineMsg);
                ZYPEZDItemValueSetter.setValue(lineMsg.serNum_A1, serNum);
            }
        } else if (SYS_SRC.S21_SERVICE.equals(bizMsg.sysSrcCd_H1.getValue())) {

            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NFCL3000_ACMsg lineMsg = bizMsg.A.no(i);

                String serNum = NFCL3000Query.getInstance().getSerNumSvcINV(bizMsg, lineMsg);
                ZYPEZDItemValueSetter.setValue(lineMsg.serNum_A1, serNum);
            }
        // START 2018/03/14 [QC#24680, ADD]
        } else if (SYS_SRC.CFS.equals(bizMsg.sysSrcCd_H1.getValue())) {

            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NFCL3000_ACMsg lineMsg = bizMsg.A.no(i);

                String serNum = NFCL3000Query.getInstance().getSerNumSvcINV(bizMsg, lineMsg);
                ZYPEZDItemValueSetter.setValue(lineMsg.serNum_A1, serNum);
            }
        // START 2018/10/24 S.Takami [QC#27069, Add]
        } else if (SYS_SRC.S21_ACCOUNTING_AR.equals(bizMsg.sysSrcCd_H1.getValue())) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NFCL3000_ACMsg lineMsg = bizMsg.A.no(i);

                String serNum = getSerNumByIB(bizMsg, lineMsg);
                ZYPEZDItemValueSetter.setValue(lineMsg.serNum_A1, serNum);
            }
        // END   2018/10/24 S.Takami [QC#27069, Add]
        }
        // END   2018/03/14 [QC#24680, ADD]
    }
    // END   2016/11/17 T.Murai [QC#14096,ADD]

    // START 2016/11/22 S.Fujita [QC#16154,ADD]
    /**
     * updateInvoice_ACCT_DISTWithIdxNum
     * @param bizMsg
     * @param globalMsg
     * @return
     */
    public static boolean updateInvoice_ACCT_DISTWithIdxNum(NFCL3000CMsg bizMsg, NFCL3000SMsg globalMsg) {

        //----------------------------------------------
        // Update AJE_LINE_IDX_NUM in AJE_INV_ACCT_DIST
        //----------------------------------------------
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        S21SsmEZDResult ajeInvAcctDist = NFCL3000Query.getInstance().getAjeInvAcctDistList(bizMsg, ssmParam);
        int ajeLineIdxNum = INIT_AJE_LINE_IDX_NUM;
        BigDecimal dsInvSlsCrPk = BigDecimal.ZERO;
        int cnt = 0;

        if (ajeInvAcctDist.isCodeNormal()) {
            List<Map> ajeInvAcctDistList = (List<Map>) ajeInvAcctDist.getResultObject();
            for(int i = 0; i < ajeInvAcctDistList.size(); i++) {

                Map ajeInvAcctDistListData = ajeInvAcctDistList.get(i);
                BigDecimal ajeInvAcctDistPk = (BigDecimal)ajeInvAcctDistListData.get(AJE_INV_ACCT_DIST_PK);

                if (cnt == 0 || cnt == 2) {
                    if (dsInvSlsCrPk.equals((BigDecimal)ajeInvAcctDistListData.get(DS_INV_SLS_CR_PK))) {
                        ajeLineIdxNum ++;
                    } else {
                        ajeLineIdxNum = INIT_AJE_LINE_IDX_NUM;
                    }
                    cnt = 0;
                    dsInvSlsCrPk = (BigDecimal)ajeInvAcctDistListData.get(DS_INV_SLS_CR_PK);
                }

                AJE_INV_ACCT_DISTTMsg inMsg = new AJE_INV_ACCT_DISTTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(inMsg.ajeInvAcctDistPk, ajeInvAcctDistPk);
                AJE_INV_ACCT_DISTTMsg updMsg = (AJE_INV_ACCT_DISTTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0864E", new String[]{"AJE_INV_ACCT_DIST", bizMsg.invNum_H1.getValue()});
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(updMsg.ajeLineIdxNum, String.format("%02d", ajeLineIdxNum));
                EZDTBLAccessor.update(updMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0862E", new String[]{"AJE_INV_ACCT_DIST"});
                    return false;
                }
                cnt++;
            }
        }
        return true;
    }
    // END   2016/11/22 S.Fujita [QC#16154,ADD]

    // START 2016/11/29 S.Fujita [QC#16075,ADD]
    /**
     * getOutOfTrtryBr
     * @param glblCmpyCd
     * @return val
     */
    public static  String getOutOfTrtryBr (String glblCmpyCd) {
        return ZYPCodeDataUtil.getVarCharConstValue(OUT_OF_TERRITORY_BR, glblCmpyCd);
    }
    // END   2016/11/29 S.Fujita [QC#16075,ADD]

    // START 2017/03/17 T.Murai [QC#14205,ADD]
    /**
     * getOutOfTrtryBr
     * @param glblCmpyCd
     * @return val
     */
    public static void convertForDisplay(NFCL3000CMsg bizMsg) {

        // Header
        bizMsg.xxRecHistCratByNm.setValue(ZYPRecHistUtil.getFullNameForRecHist(bizMsg.xxRecHistCratByNm.getValue()));
        bizMsg.xxRecHistUpdByNm.setValue(ZYPRecHistUtil.getFullNameForRecHist(bizMsg.xxRecHistUpdByNm.getValue()));

        // Line
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            bizMsg.A.no(i).xxRecHistCratByNm_A.setValue(ZYPRecHistUtil.getFullNameForRecHist(bizMsg.A.no(i).xxRecHistCratByNm_A.getValue()));
            bizMsg.A.no(i).xxRecHistUpdByNm_A.setValue(ZYPRecHistUtil.getFullNameForRecHist(bizMsg.A.no(i).xxRecHistUpdByNm_A.getValue()));
        }
        // Sales Credit
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            bizMsg.B.no(i).xxRecHistCratByNm_B.setValue(ZYPRecHistUtil.getFullNameForRecHist(bizMsg.B.no(i).xxRecHistCratByNm_B.getValue()));
            bizMsg.B.no(i).xxRecHistUpdByNm_B.setValue(ZYPRecHistUtil.getFullNameForRecHist(bizMsg.B.no(i).xxRecHistUpdByNm_B.getValue()));
        }
        // AccountDist
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            bizMsg.C.no(i).xxRecHistCratByNm_C.setValue(ZYPRecHistUtil.getFullNameForRecHist(bizMsg.C.no(i).xxRecHistCratByNm_C.getValue()));
            bizMsg.C.no(i).xxRecHistUpdByNm_C.setValue(ZYPRecHistUtil.getFullNameForRecHist(bizMsg.C.no(i).xxRecHistUpdByNm_C.getValue()));
        }
        // Shipping
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            bizMsg.D.no(i).xxRecHistCratByNm_D.setValue(ZYPRecHistUtil.getFullNameForRecHist(bizMsg.D.no(i).xxRecHistCratByNm_D.getValue()));
            bizMsg.D.no(i).xxRecHistUpdByNm_D.setValue(ZYPRecHistUtil.getFullNameForRecHist(bizMsg.D.no(i).xxRecHistUpdByNm_D.getValue()));
        }
    }
    // END   2017/03/17 T.Murai [QC#14205,ADD]
    // START 2017/12/25 E.Kameishi [QC#20312,ADD]
    /**
     * setPercentageSplit
     * @param glblCmpyCd
     * @return val
     */
    public static void setPercentageSplit(NFCL3000CMsg bizMsg) {
     // Sales Credit
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            BigDecimal tmpPercentageSplit = bizMsg.B.no(i).invLineSplPct_B2.getValue().multiply(bizMsg.B.no(i).slsRepCrPct_B2.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).slsRepCrPct_B2, tmpPercentageSplit.divide(new BigDecimal(100)));
            // START 2018/05/28 Y.Matsui [QC#26342,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).invLineSplPct_B2, new BigDecimal(100));
            // END   2018/05/28 Y.Matsui [QC#26342,ADD]
        }
    }

    /**
    * createPulldownListinvPrintCratSts
    * @param bizMsg
    */
    public static void createPulldownListinvPrintCratSts(NFCL3000CMsg bizMsg) {

       bizMsg.invPrintCratStsCd_EC.no(0).setValue(INV_PRINT_CRAT_STS_NOT_PRINT);
       bizMsg.invPrintCratStsCd_EC.no(1).setValue(INV_PRINT_CRAT_STS_PRINTED);
       bizMsg.invPrintCratStsCd_EC.no(2).setValue(INV_PRINT_CRAT_STS_RE_PRINTED);
       bizMsg.xxScrItem10Txt_ED.no(1).setValue(INV_PRINT_CRAT_STS_PRINTED_DESC);
       bizMsg.xxScrItem10Txt_ED.no(2).setValue(INV_PRINT_CRAT_STS_RE_PRINTED_DESC);
       
       bizMsg.invPrintCratStsCd_E1.setValue(INV_PRINT_CRAT_STS_NOT_PRINT);
   }

    /**
     * isTaxAdjustmentItem
     * @param bizMsg
     * @param idx
     * @return
     */
    public static void isTaxAdjustmentItem(NFCL3000CMsg bizMsg, int idx) {
        // START 2019/05/27 S.Takami [QC#50541,MOD]
//        TAX_ADJ_ITEMTMsg taxAdjItemTMsg = new TAX_ADJ_ITEMTMsg();
//        ZYPEZDItemValueSetter.setValue(taxAdjItemTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(taxAdjItemTMsg.mdseCd, bizMsg.A.no(idx).mdseCd_A1);
//        taxAdjItemTMsg = (TAX_ADJ_ITEMTMsg) S21CacheTBLAccessor.findByKey(taxAdjItemTMsg);

        TAX_ADJ_ITEMTMsg taxAdjItemTMsg = NFCL3000Query.getInstance().getTaxAdjItem(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(idx).mdseCd_A1.getValue());
        // END 2019/05/27 S.Takami [QC#50541,MOD]
        if (taxAdjItemTMsg == null) {
            bizMsg.A.no(idx).xxMstChkFlg_A1.setValue(ZYPConstant.FLG_OFF_N);
        } else {
            bizMsg.A.no(idx).xxMstChkFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
            // clear
            // START 2018/02/23 E.Kameishi [QC#24390,MOD]
            if (INV_TP.CREDIT_MEMO.equals(bizMsg.invTpCd_H1.getValue())) {
                bizMsg.A.no(idx).shipQty_A1.setValue(BigDecimal.ONE.negate());
                // START 2019/05/27 S.Takami [QC#50541,ADD]
                bizMsg.A.no(idx).ordCustUomQty_A1.setValue(BigDecimal.ONE.negate());
                // END 2019/05/27 S.Takami [QC#50541,ADD]
            } else {
                bizMsg.A.no(idx).shipQty_A1.setValue(BigDecimal.ONE);
                // START 2019/05/27 S.Takami [QC#50541,ADD]
                bizMsg.A.no(idx).ordCustUomQty_A1.setValue(BigDecimal.ONE);
                // END 2019/05/27 S.Takami [QC#50541,ADD]
            }
            // END 2018/02/23 E.Kameishi [QC#24390,MOD]
            bizMsg.A.no(idx).dealNetUnitPrcAmt_A1.setValue(BigDecimal.ZERO);
            bizMsg.A.no(idx).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
            bizMsg.A.no(idx).invBolLineNum_A1.setValue(INIT_INV_BOL_LINE_NUM);
            bizMsg.A.no(idx).invLineDealNetAmt_A1.setValue(BigDecimal.ZERO);
            bizMsg.A.no(idx).xxTotAmt_A1.setValue(BigDecimal.ZERO);
            bizMsg.A.no(idx).unitCostAmt_A1.setValue(BigDecimal.ZERO);
            bizMsg.A.no(idx).dsSlsTaxTpCd_A1.setValue(DS_SLS_TAX_TP.STATE_TAX);
            bizMsg.A.no(idx).dealSlsTaxAmt_A1.setValue(BigDecimal.ZERO);
            bizMsg.A.no(idx).slsTaxPct_A1.setValue(BigDecimal.ZERO);
            bizMsg.A.no(idx).dsSlsTaxTpCd_A2.setValue(DS_SLS_TAX_TP.COUNTY_TAX);
            bizMsg.A.no(idx).dealSlsTaxAmt_A2.setValue(BigDecimal.ZERO);
            bizMsg.A.no(idx).slsTaxPct_A2.setValue(BigDecimal.ZERO);
            bizMsg.A.no(idx).dsSlsTaxTpCd_A3.setValue(DS_SLS_TAX_TP.CITY_TAX);
            bizMsg.A.no(idx).dealSlsTaxAmt_A3.setValue(BigDecimal.ZERO);
            bizMsg.A.no(idx).slsTaxPct_A3.setValue(BigDecimal.ZERO);
            bizMsg.A.no(idx).pkgUomCd_A1.setValue(UOM_CD_EA);
            bizMsg.A.no(idx).trxCd_A1.setValue(TRX_TAX_ADJUSTMENT);
            bizMsg.A.no(idx).trxRsnCd_A1.setValue(TRX_RSN_TAX_ADJUSTMENT);
            bizMsg.A.no(idx).dsContrNum_A1.clear();
            bizMsg.A.no(idx).dsContrSqNum_A1.clear();
            bizMsg.A.no(idx).mdlNm_A1.clear();
            bizMsg.A.no(idx).svcInvChrgTpCd_A1.clear();
            bizMsg.A.no(idx).bllgPerFromDt_A1.clear();
            bizMsg.A.no(idx).bllgPerThruDt_A1.clear();
            bizMsg.A.no(idx).bllgCopyQty_A1.clear();
            bizMsg.A.no(idx).ordQty_A1.clear();
            bizMsg.A.no(idx).crDrRsnCd_A1.clear();
            bizMsg.A.no(idx).dsContrDtlPk_A1.clear();
            bizMsg.A.no(idx).firstBllgAttrbValTxt_A1.clear();
            bizMsg.A.no(idx).scdBllgAttrbValTxt_A1.clear();
            bizMsg.A.no(idx).thirdBllgAttrbValTxt_A1.clear();
            bizMsg.A.no(idx).frthBllgAttrbValTxt_A1.clear();
            bizMsg.A.no(idx).fifthBllgAttrbValTxt_A1.clear();
            bizMsg.A.no(idx).sixthBllgAttrbValTxt_A1.clear();
            // START 2019/11/02 [QC#54463,ADD]
            bizMsg.A.no(idx).taxPct_A1.setValue(BigDecimal.ZERO);
            bizMsg.A.no(idx).invLineDealTaxAmt_A1.setValue(BigDecimal.ZERO);
            // END   2019/11/02 [QC#54463,ADD]
        }
    }


    /**
     * checkTaxAdjustmentItem
     * @param bizMsg
     * @param idx
     * @return
     */
    public static boolean checkTaxAdjustmentItem(NFCL3000CMsg bizMsg) {
        boolean isTaxAdjItem = false;
        boolean isNotTaxAdjItem = false;
        
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // START 2019/05/27 S.Takami [QC#50541,MOD]
//            TAX_ADJ_ITEMTMsg taxAdjItemTMsg = new TAX_ADJ_ITEMTMsg();
//
//            ZYPEZDItemValueSetter.setValue(taxAdjItemTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(taxAdjItemTMsg.mdseCd, bizMsg.A.no(i).mdseCd_A1);
//            taxAdjItemTMsg = (TAX_ADJ_ITEMTMsg) S21CacheTBLAccessor.findByKey(taxAdjItemTMsg);
            TAX_ADJ_ITEMTMsg taxAdjItemTMsg = NFCL3000Query.getInstance().getTaxAdjItem(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(i).mdseCd_A1.getValue());
            // END 2019/05/27 S.Takami [QC#50541,MOD]

            if (taxAdjItemTMsg != null && !ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxMstChkFlg_A1.getValue())) {
                bizMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NFCM0878E);
                return false;
            }
            
            if (taxAdjItemTMsg == null) {
                isNotTaxAdjItem = true;
            } else {
                isTaxAdjItem = true;
            }
        }
        if (isTaxAdjItem && isNotTaxAdjItem) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                bizMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NFCM0877E);
            }
            return false;
        }
        return true;
    }
    
    public static void setTaxAdjustmentItem(NFCL3000CMsg bizMsg) {
        if(ZYPCommonFunc.hasValue(bizMsg.invNum_H1.getValue())) {
            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                // START 2019/05/27 S.Takami [QC#50541,MOD]
//                TAX_ADJ_ITEMTMsg taxAdjItemTMsg = new TAX_ADJ_ITEMTMsg();
//                ZYPEZDItemValueSetter.setValue(taxAdjItemTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(taxAdjItemTMsg.mdseCd, bizMsg.A.no(i).mdseCd_A1);
//                taxAdjItemTMsg = (TAX_ADJ_ITEMTMsg) S21CacheTBLAccessor.findByKey(taxAdjItemTMsg);

                TAX_ADJ_ITEMTMsg taxAdjItemTMsg = NFCL3000Query.getInstance().getTaxAdjItem(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(i).mdseCd_A1.getValue());
                // End 2019/05/27 S.Takami [QC#50541,MOD]
    
                if (taxAdjItemTMsg == null) {
                    bizMsg.A.no(i).xxMstChkFlg_A1.setValue(ZYPConstant.FLG_OFF_N);
                } else {
                    bizMsg.A.no(i).xxMstChkFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
                }
            }
        }
    }
    public static void setUnitPrice(NFCL3000CMsg bizMsg){
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxMstChkFlg_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dealNetUnitPrcAmt_A1, bizMsg.A.no(i).invLineDealTaxAmt_A1); 
            }
        }
    }
   // END 2017/12/25 E.Kameishi [QC#20312,ADD]
    
    // START 2018/05/22 Y.Matsui [QC#21841,ADD]
    /**
     * createPulldownListInvLineCatg
     * @param bizMsg NFCL3000CMsg
     */
    public static void createPulldownListInvLineCatg(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        S21SsmEZDResult invLineCatg = NFCL3000Query.getInstance().getInvLineCatgPullDownList(bizMsg, ssmParam);

        if (invLineCatg.isCodeNormal()) {
            List<Map> invLineCatgList = (List<Map>) invLineCatg.getResultObject();
            initPullDownCreate(bizMsg.invLineCatgCd_AC, bizMsg.invLineCatgNm_AD, invLineCatgList, new String[] {"INV_LINE_CATG_CD", "INV_LINE_CATG_NM" });
        }
    }

    /**
     * setCodeNameInvLineCatg
     * @param bizMsg NFCL3000CMsg
     * @param idx int
     */
    public static void setCodeNameInvLineCatg(NFCL3000CMsg bizMsg, int idx) {
        for (int i = 0; i < bizMsg.invLineCatgCd_AC.length(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.invLineCatgCd_AC.no(i).getValue())) {
                if (bizMsg.A.no(idx).invLineCatgCd_A1.getValue().equals(bizMsg.invLineCatgCd_AC.no(i).getValue())) {
                    bizMsg.A.no(idx).invLineCatgNm_A1.setValue(bizMsg.invLineCatgNm_AD.no(i).getValue());
                }
            }
        }
    }

    private static boolean checkMdseAndInvLineCatg(NFCL3000CMsg bizMsg) {
        boolean result = true;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (INV_LINE_CATG.ITEM.equals(bizMsg.A.no(i).invLineCatgCd_A1.getValue())) {
                continue;
            }
            if (isFreightAccountMdse(bizMsg, bizMsg.A.no(i).mdseCd_A1.getValue())) {
                continue;
            }
            bizMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NFCM0885E);
            bizMsg.A.no(i).invLineCatgCd_A1.setErrorInfo(1, NFCM0885E);
            result = false;
        }
        return result;
    }

    private static boolean isFreightAccountLine(NFCL3000CMsg bizMsg, NFCL3000_BCMsg bLineMsg) {
        if (isFreightAccountMdse(bizMsg, bLineMsg.mdseCd_B1.getValue())) {
            return true;
        }
        return false;
    }

    public static boolean isFreightInvoiceLine(NFCL3000CMsg bizMsg, NFCL3000_ACMsg aLineMsg) {
        if (INV_LINE_CATG.FREIGHT.equals(aLineMsg.invLineCatgCd_A1.getValue())) {
            return true;
        }
        return false;
    }

    private static boolean isFreightAccountMdse(NFCL3000CMsg bizMsg, String mdseCd) {
        String coaMdseTpCdFreight = ZYPCodeDataUtil.getVarCharConstValue(AJE_COA_MDSE_TP_FRT, bizMsg.glblCmpyCd.getValue());
        MDSETMsg mdseInfo = getMdseInfo(bizMsg, mdseCd);
        if (coaMdseTpCdFreight != null && mdseInfo != null) {
            if (coaMdseTpCdFreight.equals(mdseInfo.coaMdseTpCd.getValue())) {
                return true;
            }
        }
        return false;
    }
    // END   2018/05/22 Y.Matsui [QC#21841,ADD]
    // START 2018/07/19 E.Kameishi [QC#27007,ADD]
    public static void setPmtTerm(NFCL3000CMsg bizMsg) {

        // START 2018/09/20 S.Ohki [QC#28228,ADD]
        if(bizMsg.invTpCd_H1.getValue().equals(INV_TP.CREDIT_MEMO) && SYS_SRC.S21_ACCOUNTING_AR.equals(bizMsg.sysSrcCd_H1.getValue())) {
            return;
        }
        // END 2018/09/20 S.Ohki [QC#28228,MOD]

        String billToCustCd = null;
        if (hasValue(bizMsg.P.no(15).xxPopPrm.getValue())) {
            billToCustCd = bizMsg.P.no(15).xxPopPrm.getValue();
        }
        Map<String, Object> ssmParam1 = new HashMap<String, Object>();
        ssmParam1.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam1.put("billToCustCd", billToCustCd);
        ssmParam1.put("billToCustAcctCd", bizMsg.billToCustAcctCd_H3.getValue());
        ssmParam1.put("locNum", bizMsg.locNum_H3.getValue());
        String pmtTermCd = NFCL3000Query.getInstance().getPmtTermFromCustCrPrfl(bizMsg, ssmParam1);
        
        if (pmtTermCd == null) {
            Map<String, Object> ssmParam2 = new HashMap<String, Object>();
            ssmParam2.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam2.put("billToCustAcctCd", bizMsg.billToCustAcctCd_H3.getValue());
            pmtTermCd = NFCL3000Query.getInstance().getPmtTermFromDsAcctCrPrfl(bizMsg, ssmParam2);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscCd_H1, pmtTermCd);
        calcNetDueDate(bizMsg);
    }

    public static void delAcctDist(NFCL3000CMsg bizMsg) {
        List<Integer> deleteRows = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            BigDecimal tmp = bizMsg.C.no(i).jrnlDealAmt_C2.getValue();
            if (hasValue(bizMsg.C.no(i).jrnlDealAmt_C2) && BigDecimal.ZERO.compareTo(bizMsg.C.no(i).jrnlDealAmt_C2.getValue()) == 0) {
                deleteRows.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.C, deleteRows);
    }
    // END 2018/07/19 E.Kameishi [QC#27007,ADD]

    // START 2018/08/15 Y.Matsui [QC#27651,ADD]
    /**
     * createPulldownListPkgUomByMdse
     * @param bizMsg NFCL3000CMsg
     * @param i index of NFCL3000_ACMsgArray
     */
    public static void createPulldownListPkgUomByMdse(NFCL3000CMsg bizMsg, int i) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", bizMsg.A.no(i).mdseCd_A1.getValue());

        // START 2019/05/16 S.Takami [QC#50374,ADD]
        if (S21StringUtil.isEquals(SYS_SRC.S21_SERVICE, bizMsg.sysSrcCd_OR.getValue())) {
            ssmParam.put("baseOnly", ZYPConstant.FLG_ON_Y);
        }
        // END 2019/05/16 S.Takami [QC#50374,ADD]

        S21SsmEZDResult pkgUom = NFCL3000Query.getInstance().getPkgUomPullDownList(bizMsg, ssmParam);

        if (pkgUom.isCodeNormal()) {
            List<Map> pkgUomList = (List<Map>) pkgUom.getResultObject();
            // START 2018/09/28 T.Ogura [QC#28526,MOD]
//            initPullDownCreate(bizMsg.A.no(i).pkgUomCd_AC, bizMsg.A.no(i).pkgUomNm_AD, pkgUomList, new String[] {"PKG_UOM_CD", "PKG_UOM_NM" });
            initPullDownCreate(bizMsg.A.no(i).pkgUomCd_AC, bizMsg.A.no(i).pkgUomDescTxt_AD, pkgUomList, new String[] {"PKG_UOM_CD", "PKG_UOM_DESC_TXT" });
            // END   2018/09/28 T.Ogura [QC#28526,MOD]
            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).pkgUomCd_A1)) {
                for (Map uom : pkgUomList) {
                    if (ZYPConstant.FLG_ON_Y.equals(uom.get("PRIM_PKG_UOM_FLG"))) {
                        bizMsg.A.no(i).pkgUomCd_A1.setValue((String) uom.get("PKG_UOM_CD"));
                    }
                }
            }
        }
    }

    /**
     * createPulldownListPkgUomByUOM
     * @param bizMsg NFCL3000CMsg
     * @param i index of NFCL3000_ACMsgArray
     */
    public static void createPulldownListPkgUomByUOM(NFCL3000CMsg bizMsg, int i) {
        bizMsg.A.no(i).pkgUomCd_AC.clear();
        // START 2018/09/28 T.Ogura [QC#28526,MOD]
//        bizMsg.A.no(i).pkgUomNm_AD.clear();
        bizMsg.A.no(i).pkgUomDescTxt_AD.clear();
        // END   2018/09/28 T.Ogura [QC#28526,MOD]
        String code = bizMsg.A.no(i).pkgUomCd_A1.getValue();
        // START 2018/09/28 T.Ogura [QC#28526,MOD]
//        String name = ZYPCodeDataUtil.getName(PKG_UOM.class, bizMsg.glblCmpyCd.getValue(), code);
        String name = getPkgUomDescTxt(bizMsg.glblCmpyCd.getValue(), code);
        // END   2018/09/28 T.Ogura [QC#28526,MOD]
        if (ZYPCommonFunc.hasValue(name)) {
            bizMsg.A.no(i).pkgUomCd_AC.no(0).setValue(code);
            // START 2018/09/28 T.Ogura [QC#28526,MOD]
//            bizMsg.A.no(i).pkgUomNm_AD.no(0).setValue(name);
            bizMsg.A.no(i).pkgUomDescTxt_AD.no(0).setValue(name);
            // END   2018/09/28 T.Ogura [QC#28526,MOD]
        } else {
            name = ZYPCodeDataUtil.getName(BLLG_CYCLE_UOM.class, bizMsg.glblCmpyCd.getValue(), code);
            if (ZYPCommonFunc.hasValue(name)) {
                bizMsg.A.no(i).pkgUomCd_AC.no(0).setValue(code);
                // START 2018/09/28 T.Ogura [QC#28526,MOD]
//                bizMsg.A.no(i).pkgUomNm_AD.no(0).setValue(name);
                bizMsg.A.no(i).pkgUomDescTxt_AD.no(0).setValue(name);
                // END   2018/09/28 T.Ogura [QC#28526,MOD]
            }
        }
    }

    /**
     * createPulldownListPkgUomDefault
     * @param bizMsg NFCL3000CMsg
     * @param i index of NFCL3000_ACMsgArray
     */
    public static void createPulldownListPkgUomDefault(NFCL3000CMsg bizMsg, int i) {
        bizMsg.A.no(i).pkgUomCd_A1.setValue(PKG_UOM.EACH);
        createPulldownListPkgUomByUOM(bizMsg, i);
    }

    /**
     * createPulldownListPkgUom
     * @param bizMsg NFCL3000CMsg
     */
    public static void createPulldownListPkgUom(NFCL3000CMsg bizMsg) {
        // START 2019/04/25 S.Takami [QC#50078,ADD]
        boolean isInvoiceWithContract = isInvoiceWithContract(bizMsg);
        // END 2019/04/25 S.Takami [QC#50078,ADD]
        // START 2019/05/16 S.Takami [QC#50374,MOD]
        String sysSrcCd = bizMsg.sysSrcCd_H1.getValue();
        if (ZYPCommonFunc.hasValue(bizMsg.sysSrcCd_OR)) {
            sysSrcCd = bizMsg.sysSrcCd_OR.getValue();
        }
        boolean isNoFinalize = !S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.fnlzInvFlg_H4.getValue());
        // START 2018/11/02 S.Ohki [QC#29059,MOD]
//        if (SYS_SRC.S21_ACCOUNTING_AR.equals(bizMsg.sysSrcCd_H1.getValue()) && ZYPConstant.FLG_OFF_N.equals(bizMsg.fnlzInvFlg_H4.getValue())) {
//        if (SYS_SRC.S21_ACCOUNTING_AR.equals(bizMsg.sysSrcCd_OR.getValue()) && ZYPConstant.FLG_OFF_N.equals(bizMsg.fnlzInvFlg_H4.getValue())) {
        // END 2018/11/02 S.Ohki [QC#29059,MOD]
        if (SYS_SRC.S21_ACCOUNTING_AR.equals(sysSrcCd) && isNoFinalize) {
        // END 2019/05/16 S.Takami [QC#50374,MOD]
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                // START 2019/04/25 S.Takami [QC#50078,MOD]
//                createPulldownListPkgUomByMdse(bizMsg, i);
                if (isInvoiceWithContract) {
                    createPulldownListPkgUomFromBllgCycleUOM(bizMsg, i);
                } else {
                    createPulldownListPkgUomByMdse(bizMsg, i);
                }
                // END 2019/04/25 S.Takami [QC#50078,MOD]
            }
        } else {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                createPulldownListPkgUomByUOM(bizMsg, i);
            }
        }
    }
    // END   2018/08/15 Y.Matsui [QC#27651,ADD]

    // START 2018/08/30 Y.Matsui [QC#27829,ADD]
    private static NFCL3000_BCMsg getSlsCrBizMsg(NFCL3000CMsg bizMsg, NFCL3000_CCMsg cBizMsg) {
        NFCL3000_BCMsg bBizMsg = null;
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            bBizMsg = bizMsg.B.no(i);
            if (!cBizMsg.invBolLineNum_C1.getValue().equals(bBizMsg.invBolLineNum_B1.getValue())) {
                continue;
            }
            if (!cBizMsg.invLineNum_C1.getValue().equals(bBizMsg.invLineNum_B1.getValue())) {
                continue;
            }
            if (!cBizMsg.invLineSubTrxNum_C1.getValue().equals(bBizMsg.invLineSubNum_B1.getValue())) {
                continue;
            }
            break;
        }
        return bBizMsg;
    }
    
    private static NFCL3000_ACMsg getInvLineBizMsg(NFCL3000CMsg bizMsg, NFCL3000_CCMsg cBizMsg) {
        NFCL3000_ACMsg aBizMsg = null;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            aBizMsg = bizMsg.A.no(i);
            if (!cBizMsg.invBolLineNum_C1.getValue().equals(aBizMsg.invBolLineNum_A1.getValue())) {
                continue;
            }
            if (!cBizMsg.invLineNum_C1.getValue().equals(aBizMsg.invLineNum_A1.getValue())) {
                continue;
            }
            if (!cBizMsg.invLineSubTrxNum_C1.getValue().equals(aBizMsg.invLineSubNum_A1.getValue())) {
                continue;
            }
            break;
        }
        return aBizMsg;
    }
    
    private static BigDecimal checkNull(BigDecimal val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        } else {
            return BigDecimal.ZERO;
        }
    }
    // END   2018/08/30 Y.Matsui [QC#27829,ADD]

    // START 2018/09/28 T.Ogura [QC#28526,ADD]
    /**
     * getPkgUomDescTxt
     * @param glblCmpyCd String
     * @param pkgUomCd String
     * @return String
     */
    public static String getPkgUomDescTxt(String glblCmpyCd, String pkgUomCd) {

        PKG_UOMTMsg inMsg = new PKG_UOMTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.pkgUomCd.setValue(pkgUomCd);

        PKG_UOMTMsg outMsg = (PKG_UOMTMsg) EZDTBLAccessor.findByKey(inMsg);
        if(outMsg == null) {
            return null;
        }

        return outMsg.pkgUomDescTxt.getValue();
    }
    // END   2018/09/28 T.Ogura [QC#28526,ADD]

    // START 2018/09/28 T.Ogura [QC#28526,ADD]
    /**
     * setEachQtyToShipQty
     * @param cMsg EZDCMsg
     */
    public static void setEachQtyToShipQty(EZDCMsg cMsg) {

        NFCL3000CMsg bizMsg = (NFCL3000CMsg) cMsg;

        // START 2019/04/25 S.Takami [QC#50078,ADD]
        if (bizMsg.A.getValidCount() < 1) {
            return;
        }
        boolean isDsInvTpFsr = false;
        if (ZYPCommonFunc.hasValue(bizMsg.glblCmpyCd) && ZYPCommonFunc.hasValue(bizMsg.dsInvTpCd_H1)) {
            isDsInvTpFsr = isDsInvTpFsr(bizMsg);
        }
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            String strQty = bizMsg.A.no(i).adjQtyDplyTxt_A1.getValue();
            if (!ZYPCommonFunc.hasValue(strQty)) {
                bizMsg.A.no(i).invDplyQty_A1.clear();
                bizMsg.A.no(i).shipQty_A1.clear();
                bizMsg.A.no(i).ordCustUomQty_A1.clear();
                continue;
            } else {
                strQty = strQty.replace(",", "");
                try {
                    BigDecimal checkNum = new BigDecimal(strQty);
                    checkNum = null;
                } catch(Exception ex) {
                    bizMsg.A.no(i).adjQtyDplyTxt_A1.clear();
                    continue;
                }
                // START 2019/10/03 S.Takami [QC#53881,ADD]
                if (hasErrorOnQty(bizMsg, i, strQty)) {
                    continue;
                }
                // END 2019/10/03 S.Takami [QC#53881,ADD]
            }
            NumberFormat fmt = NumberFormat.getNumberInstance();
            boolean isDecimal = strQty.indexOf(".") >= 0;
            try {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.A.no(i).xxDplyCtrlFlg_A1.getValue())) {
                    bizMsg.A.no(i).shipQty_A1.setValue(BigDecimal.ONE);
                    bizMsg.A.no(i).shipQty_A1.setValue(getSignByInvtp(bizMsg.invTpCd_H1.getValue(), bizMsg.A.no(i).shipQty_A1));
                    bizMsg.A.no(i).invDplyQty_A1.setValue(new BigDecimal(strQty));
                    bizMsg.A.no(i).adjQtyDplyTxt_A1.setValue(fmt.format(bizMsg.A.no(i).invDplyQty_A1.getValue()));
                    if (isDsInvTpFsr) {
                        bizMsg.A.no(i).ordCustUomQty_A1.clear();
                    }
                } else if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, bizMsg.A.no(i).xxDplyCtrlFlg_A1.getValue())) {
                    if (!isDecimal) {
                        bizMsg.A.no(i).shipQty_A1.setValue(new BigDecimal(strQty));
                        if (isDsInvTpFsr) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invDplyQty_A1, bizMsg.A.no(i).shipQty_A1);
                            bizMsg.A.no(i).ordCustUomQty_A1.clear();
                        }
                        bizMsg.A.no(i).adjQtyDplyTxt_A1.setValue(fmt.format(bizMsg.A.no(i).shipQty_A1.getValue()));
                    }
                } else {
                    if (!isDecimal) {
                        bizMsg.A.no(i).ordCustUomQty_A1.setValue(new BigDecimal(strQty));
                        if (isDsInvTpFsr) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invDplyQty_A1, bizMsg.A.no(i).ordCustUomQty_A1);
                            bizMsg.A.no(i).adjQtyDplyTxt_A1.setValue(fmt.format(bizMsg.A.no(i).ordCustUomQty_A1.getValue()));
                        }
                    }
                }
            } catch (Exception ex) {
                continue;
            }
        }
        // END 2019/04/25 S.Takami [QC#50078,ADD]
        // START 2018/11/06 S.Ohki [QC#29059-2,MOD]
//        if (isManualInvoice(bizMsg) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue())) {
        if (!SYS_SRC.S21_SERVICE.equals(bizMsg.sysSrcCd_OR.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.fnlzInvFlg_H4.getValue())) {	
            // START 2018/11/06 S.Ohki [QC#29059-2,MOD]
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).ordCustUomQty_A1)) {
                    continue;
                }
                MDSE_STORE_PKGTMsg inMsg = new MDSE_STORE_PKGTMsg();
                inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
                inMsg.mdseCd.setValue(bizMsg.A.no(i).mdseCd_A1.getValue());
                inMsg.pkgUomCd.setValue(bizMsg.A.no(i).pkgUomCd_A1.getValue());

                // START 2019/04/25 S.Takami [QC#50078,MOD]
//                MDSE_STORE_PKGTMsg outMsg = (MDSE_STORE_PKGTMsg) EZDTBLAccessor.findByKey(inMsg);
                MDSE_STORE_PKGTMsg outMsg = (MDSE_STORE_PKGTMsg) S21CacheTBLAccessor.findByKey(inMsg);
                // END 2019/04/25 S.Takami [QC#50078,MOD]

                // START 2019/04/25 S.Takami [QC#50078,ADD]
                if (outMsg == null) {
                    S21SsmEZDResult ssmRslt = NFCL3000Query.getInstance().getPkgUomByBaseUomCd(bizMsg.glblCmpyCd.getValue(), //
                            bizMsg.A.no(i).mdseCd_A1.getValue(), //
                            bizMsg.A.no(i).pkgUomCd_A1.getValue());

                    if (ssmRslt.isCodeNormal()) {
                        outMsg = ((List<MDSE_STORE_PKGTMsg>) ssmRslt.getResultObject()).get(0);
                    } else {
                        outMsg = null;
                    }
                }
                // END 2019/04/25 S.Takami [QC#50078,ADD]
                BigDecimal inEachQty = BigDecimal.ONE;
                if(outMsg != null && ZYPCommonFunc.hasValue(outMsg.inEachQty)) {
                    inEachQty = outMsg.inEachQty.getValue();
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).shipQty_A1, bizMsg.A.no(i).ordCustUomQty_A1.getValue().multiply(inEachQty));
                if (isDsInvTpFsr(bizMsg)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invDplyQty_A1, bizMsg.A.no(i).shipQty_A1);
                }
            }
        }
    }
    // END   2018/09/28 T.Ogura [QC#28526,ADD]

    // START 2018/10/24 S.Takami [QC#27069, Add]
    private static String getSerNumByIB(NFCL3000CMsg bizMsg, NFCL3000_ACMsg lineMsg) {

        String serNum = "";
        if (ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_A1)) {
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(bizMsg.glblCmpyCd.getValue(), lineMsg.svcMachMstrPk_A1.getValue());
            if (svcMachMstrTMsg != null && ZYPCommonFunc.hasValue(svcMachMstrTMsg.serNum)) {
                serNum = svcMachMstrTMsg.serNum.getValue();
            }
        }
        return serNum;
    }

    private static SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, svcMachMstrPk);

        return (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(svcMachMstrTMsg);
    }
    // END   2018/10/24 S.Takami [QC#27069, Add]

    // START 2019/04/25 S.Takami [QC#50078,ADD]
    /**
     * <pre>
     * Set xxDplyCtrlFlg_A1 Value.
     * Header dsInvTpCd is inclueded in NFCL3000_FSR_INV_TP and Line Uom Code is included in NFCL3000_DECL_UOM_CD -> Y
     * @param bizMsg Biz Message
     * @param idx index of bizMsg.A for proceeding.
     * </pre>
     */
    public static void setDplyCtrlFlg(NFCL3000CMsg bizMsg, int idx) {

        boolean isFsrInv = isDsInvTpFsr(bizMsg);

        String decimalUomCdVal = NFCL3000CommonLogicForConst.getInstance().getVarCharConstVal(bizMsg.glblCmpyCd.getValue(), NFCL3000_DECL_UOM_CD);
        if (!ZYPCommonFunc.hasValue(decimalUomCdVal)) {
            decimalUomCdVal = NFCL3000_DECL_UOM_CD_VAL;
        }
        String[] decimalUomCdArray = decimalUomCdVal.split(",");
        boolean isDeicmalPkgUom = false;
        String pkgUomCd = bizMsg.A.no(idx).pkgUomCd_A1.getValue();
        for (String decimalUomCd : decimalUomCdArray) {
            if (S21StringUtil.isEquals(decimalUomCd, pkgUomCd)) {
                isDeicmalPkgUom = true;
                break;
            }
        }

        NumberFormat fmt = NumberFormat.getNumberInstance();
        String strQty = bizMsg.A.no(idx).adjQtyDplyTxt_A1.getValue();
        BigDecimal checkQty = null;
        if (ZYPCommonFunc.hasValue(strQty)) {
            try {
                checkQty = new BigDecimal(strQty.replace(",", ""));
            } catch (Exception ex) {
                checkQty = null;
            }
        }
        if (isFsrInv && isDeicmalPkgUom) {
            bizMsg.A.no(idx).xxDplyCtrlFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
            bizMsg.A.no(idx).ordCustUomQty_A1.clear();
            if (checkQty != null) {
                try {
                    bizMsg.A.no(idx).invDplyQty_A1.setValue(checkQty);
                } catch (Exception ex) {
                    bizMsg.A.no(idx).adjQtyDplyTxt_A1.clear();
                    bizMsg.A.no(idx).invDplyQty_A1.clear();
                }
            }
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(idx).invDplyQty_A1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).adjQtyDplyTxt_A1, fmt.format(bizMsg.A.no(idx).invDplyQty_A1.getValue()));
            } else {
                bizMsg.A.no(idx).adjQtyDplyTxt_A1.clear();
            }
            return;
        }

        String sysSrcCd = bizMsg.sysSrcCd_H1.getValue();
        String orSysSrcCd = bizMsg.sysSrcCd_OR.getValue();
        if (ZYPCommonFunc.hasValue(orSysSrcCd)) {
            sysSrcCd = orSysSrcCd;
        }

        String svcInvSrcTpCd = null;
        if (ZYPCommonFunc.hasValue(bizMsg.A.no(idx).svcInvLinePk_A1)) {
            SVC_INV_LINETMsg svcInvLineTMsg = new SVC_INV_LINETMsg();
            ZYPEZDItemValueSetter.setValue(svcInvLineTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcInvLineTMsg.svcInvLinePk, bizMsg.A.no(idx).svcInvLinePk_A1);
            svcInvLineTMsg = (SVC_INV_LINETMsg) S21CacheTBLAccessor.findByKey(svcInvLineTMsg);
            if (svcInvLineTMsg != null) {
                SVC_INVTMsg svcInvTMsg = new SVC_INVTMsg();
                ZYPEZDItemValueSetter.setValue(svcInvTMsg.glblCmpyCd, svcInvLineTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(svcInvTMsg.svcInvNum, svcInvLineTMsg.svcInvNum);
                svcInvTMsg = (SVC_INVTMsg) S21CacheTBLAccessor.findByKey(svcInvTMsg);
                if (svcInvTMsg != null) {
                    svcInvSrcTpCd = svcInvTMsg.svcInvSrcTpCd.getValue();
                }
            }
        }
        if (S21StringUtil.isEquals(SYS_SRC.S21_SERVICE, sysSrcCd) //
                && !S21StringUtil.isEquals(SVC_INV_SRC_TP.DISPATCH, svcInvSrcTpCd)) {
            bizMsg.A.no(idx).xxDplyCtrlFlg_A1.setValue(ZYPConstant.FLG_OFF_N);
            bizMsg.A.no(idx).ordCustUomQty_A1.clear();
            bizMsg.A.no(idx).invDplyQty_A1.clear();
            if (checkQty != null) {
                try {
                    bizMsg.A.no(idx).shipQty_A1.setValue(checkQty);
                } catch (Exception ex) {
                    bizMsg.A.no(idx).adjQtyDplyTxt_A1.clear();
                    bizMsg.A.no(idx).shipQty_A1.clear();
                }
            }
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(idx).shipQty_A1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).adjQtyDplyTxt_A1, fmt.format(bizMsg.A.no(idx).shipQty_A1.getValue()));
            } else {
                bizMsg.A.no(idx).adjQtyDplyTxt_A1.clear();
            }
            return;
        }
        bizMsg.A.no(idx).xxDplyCtrlFlg_A1.clear();
        bizMsg.A.no(idx).invDplyQty_A1.clear();
        if (checkQty != null) {
            try {
                bizMsg.A.no(idx).ordCustUomQty_A1.setValue(checkQty);
            } catch (Exception ex) {
                bizMsg.A.no(idx).adjQtyDplyTxt_A1.clear();
                bizMsg.A.no(idx).ordCustUomQty_A1.clear();
            }
        }

        // START 2019/05/29 Y.Matsui [QC#50078,ADD]
        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(idx).ordCustUomQty_A1)) {
            if (PKG_UOM.EACH.equals(bizMsg.A.no(idx).pkgUomCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).ordCustUomQty_A1, bizMsg.A.no(idx).shipQty_A1);
            }
        }
        // END 2019/05/29 Y.Matsui [QC#50078,ADD]

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(idx).ordCustUomQty_A1)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).adjQtyDplyTxt_A1, fmt.format(bizMsg.A.no(idx).ordCustUomQty_A1.getValue()));
        } else {
            bizMsg.A.no(idx).adjQtyDplyTxt_A1.clear();
        }
    }

    /**
     * Check DS_INV_TP_CD as FSR Invoice or not
     * @param bizMsg Biz. Message
     * @return true: DS_INV_TP is FSR Invoice false: not FSR Invoice
     */
    public static boolean isDsInvTpFsr(NFCL3000CMsg bizMsg) {

        boolean isDsInvTpFsr = false;
        String fsrInvTpCdVal = NFCL3000CommonLogicForConst.getInstance().getVarCharConstVal(bizMsg.glblCmpyCd.getValue(), NFCL3000_FSR_INV_TP);
        if (!ZYPCommonFunc.hasValue(fsrInvTpCdVal)) {
            fsrInvTpCdVal = NFCL3000_FSR_INV_TP_VAL;
        }
        String[] fsrInvTpCdArray = fsrInvTpCdVal.split(",");
        boolean isFsrInv = false;
        String dsInvTpCd = bizMsg.dsInvTpCd_H1.getValue();
        for (String fsrInvTpCd : fsrInvTpCdArray) {
            if (S21StringUtil.isEquals(fsrInvTpCd, dsInvTpCd)) {
                isDsInvTpFsr = true;
                break;
            }
        }
        return isDsInvTpFsr;
    }

    // START 2019/10/02 S.Takami [QC#53633-2,ADD]
    // For Calling Original Logic, not tax adjustment.
    private static BigDecimal getInvLineNetAmount(NFCL3000CMsg bizMsg, int invLineIdx) {

        return getInvLineNetAmount(bizMsg, invLineIdx, false);
    }
    // End 2019/10/02 S.Takami [QC#53633-2,ADD]

    // 2019/10/02 S.Takami [QC#53633-2,MOD] Add Param: isTaxAdj
    private static BigDecimal getInvLineNetAmount(NFCL3000CMsg bizMsg, int invLineIdx, boolean isTaxAdj) {

        INV_LINETMsg invLineTMsg = NFCL3000Query.getInstance().getInvLine(bizMsg, invLineIdx);
        BigDecimal origShipQty = null;
        BigDecimal origInvDplyQty = null;
        BigDecimal origUnitAmt = null;
        BigDecimal origTotNetAmt = null;
        String originvCatgCd = null;

        BigDecimal invLineNetAmt = BigDecimal.ZERO;
        if(invLineTMsg != null) {
            origShipQty = invLineTMsg.shipQty.getValue();
            origInvDplyQty = invLineTMsg.invDplyQty.getValue();
            origUnitAmt = invLineTMsg.dealNetUnitPrcAmt.getValue();
            origTotNetAmt = invLineTMsg.invLineDealNetAmt.getValue();
            originvCatgCd = invLineTMsg.invLineCatgCd.getValue();
        }

        NFCL3000_ACMsg lineMsg = bizMsg.A.no(invLineIdx);

        boolean isNoInvLineData = invLineTMsg == null;
        boolean isCatgItem = INV_LINE_CATG.ITEM.equals(originvCatgCd);
        boolean isDiffQty = false;

        boolean isFsrLine = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.xxDplyCtrlFlg_A1.getValue());
        if (isFsrLine) {
            isDiffQty = !isSameValueOfBigDecimalItem(lineMsg.invDplyQty_A1.getValue(), origInvDplyQty);
        } else {
            isDiffQty = !isSameValueOfBigDecimalItem(lineMsg.shipQty_A1.getValue(), origShipQty);
        }

        boolean isDiffDealNetUnitPrcAmt = !isSameValueOfBigDecimalItem(getAmount(lineMsg.dealNetUnitPrcAmt_A1), origUnitAmt);

        boolean isCalc = isNoInvLineData || isCatgItem;
        isCalc |= !isCatgItem && (isDiffQty || isDiffDealNetUnitPrcAmt);

        if (isCalc) {
            BigDecimal calcQty = lineMsg.shipQty_A1.getValue();
            if (isFsrLine) {
                calcQty = lineMsg.invDplyQty_A1.getValue();
            }
            // START 2019/10/02 S.Takami [QC#53633-2,MOD]
//            if (ZYPCommonFunc.hasValue(lineMsg.dealNetUnitPrcAmt_A1) && ZYPCommonFunc.hasValue(calcQty)) {
//                invLineNetAmt = getAmount(lineMsg.dealNetUnitPrcAmt_A1).multiply(calcQty);
//            }
            if (isTaxAdj) {
                if (ZYPCommonFunc.hasValue(lineMsg.invLineDealTaxAmt_A1) && ZYPCommonFunc.hasValue(calcQty)) {
                    invLineNetAmt = getAmount(lineMsg.invLineDealTaxAmt_A1).multiply(calcQty);
                }
            } else {
                if (ZYPCommonFunc.hasValue(lineMsg.dealNetUnitPrcAmt_A1) && ZYPCommonFunc.hasValue(calcQty)) {
                    invLineNetAmt = getAmount(lineMsg.dealNetUnitPrcAmt_A1).multiply(calcQty);
                }
            }
            // END 2019/10/02 S.Takami [QC#53633-2,MOD]
        } else {
            invLineNetAmt = origTotNetAmt;
        }
        // START 2023/11/08 M.Kobayashi [QC#62071,ADD]
        int scale = bizMsg.aftDeclPntDigitNum.getValueInt();
        invLineNetAmt = invLineNetAmt.setScale(scale, BigDecimal.ROUND_HALF_UP);
        // END 2023/11/08 M.Kobayashi [QC#62071,ADD]

        return invLineNetAmt;
    }

    private static boolean isSameValueOfBigDecimalItem(BigDecimal comp1, BigDecimal comp2) {

        boolean rslt = true;
        if (comp1 != null && comp2 != null) {
            rslt = comp1.compareTo(comp2) == 0;
        }
        if (comp1 == null && comp2 != null) {
            rslt = false;
        }
        if (comp1 != null && comp2 == null) {
            rslt = false;
        }
        return rslt;
    }

    /**
     * <pre>
     * Create PkgUom Pulldown From BLLG_CYCLE_UOM
     * @param bizMsg Biz. Message
     * @param i index of setting line
     * </pre>
     */
    public static void createPulldownListPkgUomFromBllgCycleUOM(NFCL3000CMsg bizMsg, int i) {

        if (isInvoiceWithContract(bizMsg)) {
            S21SsmEZDResult ssmRslt = NFCL3000Query.getInstance().getBllgCycleUomPullDownList(bizMsg);
            if (ssmRslt != null && ssmRslt.isCodeNormal()) {
                List<Map> pkgUomList = (List<Map>) ssmRslt.getResultObject();
                initPullDownCreate(bizMsg.A.no(i).pkgUomCd_AC, //
                        bizMsg.A.no(i).pkgUomDescTxt_AD, //
                        pkgUomList, //
                        new String[] {"BLLG_CYCLE_UOM_CD", "BLLG_CYCLE_UOM_DESC_TXT"});
            }
        }
    }

    /**
     * <pre>
     * Check the manual Invoice requires contract number from DS_INV_TP.
     * @param bizMsg Biz. Message
     * @return true: Contract false: no contract
     * </pre>
     */
    public static boolean isInvoiceWithContract(NFCL3000CMsg bizMsg) {

        boolean isInvoiceWithContract = false;
        String contrDsInvTpList = NFCL3000CommonLogicForConst.getInstance().getVarCharConstVal(bizMsg.glblCmpyCd.getValue(), NFCL3000_CONTR_DS_INV_TP);
        if (contrDsInvTpList == null) {
            contrDsInvTpList = NFCL3000_CONTR_DS_INV_TP_VAL;
        }
        String[] contrDsInvTpArry = contrDsInvTpList.split(",");
        for (String contrDsInvTp : contrDsInvTpArry) {
            if (S21StringUtil.isEquals(contrDsInvTp, bizMsg.dsInvTpCd_H1.getValue())) {
                isInvoiceWithContract = true;
                break;
            }
        }
        return isInvoiceWithContract;
    }
    // END 2019/04/25 S.Takami [QC#50078,ADD]

    // START 2019/05/10 S.Takami [QC#50148,ADD]
    private static boolean isUpdateComment(INVTMsg updInvMsg, String comment) {

        boolean rslt = false;

        String[] devidedComments = devideMoreInfoComment(comment);
        int i = 0;
        if (!isEqualString(updInvMsg.invFirstCmntTxt.getValue(), devidedComments[i])) {
            updInvMsg.invFirstCmntTxt.setValue(devidedComments[i]);
            rslt = true;
        }
        i++;
        if (!isEqualString(updInvMsg.invScdCmntTxt.getValue(), devidedComments[i])) {
            updInvMsg.invScdCmntTxt.setValue(devidedComments[i]);
            rslt = true;
        }
        i++;
        if (!isEqualString(updInvMsg.invThirdCmntTxt.getValue(), devidedComments[i])) {
            updInvMsg.invThirdCmntTxt.setValue(devidedComments[i]);
            rslt = true;
        }
        i++;
        if (!isEqualString(updInvMsg.invFrthCmntTxt.getValue(), devidedComments[i])) {
            updInvMsg.invFrthCmntTxt.setValue(devidedComments[i]);
            rslt = true;
        }

        return rslt;
    }

    /**
     * <pre>
     * Divide comments for 4 elements.
     * Even if the "Comment"'s length less then 65 or 130 or 185, 260, this method create 4 elements of array.
     * @param invTMsg using get INV.INV_FIRST_CMNT_TXT length. not update INV data.
     * @param comment More Infor "Comment" Data.
     * @return devided comments. 
     * </pre>
     */
    public static String[] devideMoreInfoComment(String comment) {

        String[] rsltCommentArray = new String[4];
        for (int i = 0; i < rsltCommentArray.length; i++) {
            rsltCommentArray[i] = "";
        }

        if (!ZYPCommonFunc.hasValue(comment)) {
            return rsltCommentArray;
        }

        INVTMsg invTMsg = new INVTMsg();
        int commentLen = invTMsg.getAttr("invFirstCmntTxt").getDigit();
        int comLen = comment.length();
        int cols = comLen / commentLen;
        int colsMod = comLen % commentLen;
        if (colsMod > 0) {
            cols++;
        }
        invTMsg = null;

        int start = 0;
        for (; start < cols - 1; start++) {
            String subComment = comment.substring(start * commentLen, start * commentLen + commentLen);
            rsltCommentArray[start] = subComment;
        }
        String subComment = comment.substring(start * commentLen);
        rsltCommentArray[start] = subComment;
        return rsltCommentArray;
    }

    private static boolean isEqualString(String cmp1, String cmp2) {

        boolean hasVal1 = ZYPCommonFunc.hasValue(cmp1);
        boolean hasVal2 = ZYPCommonFunc.hasValue(cmp2);

        if (!hasVal1 && !hasVal2) {
            return true;
        } else if (hasVal1 && !hasVal2) {
            return false;
        } else if (!hasVal1 && hasVal2) {
            return false;
        }
        return cmp1.equals(cmp2);
    }
    // End 2019/05/10 S.Takami [QC#50148,ADD]
    // START 2019/05/31 S.Takami [QC#50633,DEL]
//    // START 2019/05/27 S.Takami [QC#50541,ADD]
//    private static boolean hasOrigInvSlsCrLine(NFCL3000CMsg bizMsg, int idxSlsCr) {
//
//        if (!ZYPCommonFunc.hasValue(bizMsg.origInvNum_E1)) {
//            return false;
//        }
//        NFCL3000_BCMsg slsCrMsg = bizMsg.B.no(idxSlsCr);
//        DS_INV_SLS_CRTMsg dsInvSlsCrTMsg = new DS_INV_SLS_CRTMsg();
//        dsInvSlsCrTMsg.setSQLID("002");
//        dsInvSlsCrTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        dsInvSlsCrTMsg.setConditionValue("invNum01", bizMsg.origInvNum_E1.getValue());
//        dsInvSlsCrTMsg.setConditionValue("invBolLineNum01", slsCrMsg.invBolLineNum_B1.getValue());
//        dsInvSlsCrTMsg.setConditionValue("invLineNum01", slsCrMsg.invLineNum_B1.getValue());
//        dsInvSlsCrTMsg.setConditionValue("invLineSubNum01", slsCrMsg.invLineSubNum_B1.getValue());
//        dsInvSlsCrTMsg.setConditionValue("invTrxLineSubNum01", slsCrMsg.invTrxLineSubNum_B1.getValue());
//
//        DS_INV_SLS_CRTMsgArray dsInvSlsCrTMsgAry = (DS_INV_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(dsInvSlsCrTMsg);
//        if (dsInvSlsCrTMsgAry == null || dsInvSlsCrTMsgAry.getValidCount() == 0) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//    // END 2019/05/27 S.Takami [QC#50541,ADD]
    // END 2019/05/31 S.Takami [QC#50633,DEL]
    // START 2019/06/04 S.Takami [QC#50601,ADD]
    private static String getTaxCalcItemCode(NFCL3000CMsg bizMsg, int lineIdx) {

        NFCL3000_ACMsg lineMsg = bizMsg.A.no(lineIdx);
        String rsltMdseCd = lineMsg.mdseCd_A1.getValue();

        if (!isInvoiceWithContract(bizMsg)) {
            return rsltMdseCd;
        }
        String invBolLineNum = lineMsg.invBolLineNum_A1.getValue();
        String invLineNum = lineMsg.invLineNum_A1.getValue();

        String serviceItemCd = null;
        for (int slsCrIdx = 0; slsCrIdx < bizMsg.B.getValidCount(); slsCrIdx++) {
            NFCL3000_BCMsg slsCrLineMsg = bizMsg.B.no(slsCrIdx);
            if (S21StringUtil.isEquals(invBolLineNum, slsCrLineMsg.invBolLineNum_B1.getValue()) //
                    && S21StringUtil.isEquals(invLineNum, slsCrLineMsg.invLineNum_B1.getValue()) //
                    && ZYPCommonFunc.hasValue(slsCrLineMsg.mdseCd_B1)) {
                serviceItemCd = slsCrLineMsg.mdseCd_B1.getValue();
                break;
            }
        }
        if (ZYPCommonFunc.hasValue(serviceItemCd)) {
            return serviceItemCd;
        }
        return rsltMdseCd;
    }
    // END 2019/06/04 S.Takami [QC#50601,ADD]
    // START 2019/06/05 S.Takami [QC#50685,ADD]
    private static boolean isAvalTaxAdjustItem(NFCL3000CMsg bizMsg) {

        List<String> taxAdjDsInvTpList = getAvalTaxAdjustmentDsInvTypeCode(bizMsg);
        if (taxAdjDsInvTpList.isEmpty()) {
            return true;
        }
        return taxAdjDsInvTpList.contains(bizMsg.dsInvTpCd_H1.getValue());
    }

    private static List<String> getAvalTaxAdjustmentDsInvTypeName(NFCL3000CMsg bizMsg) {

        List<String> taxAdjDsInvTpList = getAvalTaxAdjustmentDsInvTypeCode(bizMsg);
        List<String> taxAdjDsInvTpNmList = new ArrayList<String>(0);

        String invTpCd = bizMsg.invTpCd_H1.getValue();
        if (!taxAdjDsInvTpList.isEmpty()) {
            for (String taxAdjDsInvTp : taxAdjDsInvTpList) {
                DS_INV_TPTMsg dsInvTpTMsg = getDsInvTpTMsg(bizMsg.glblCmpyCd.getValue(), taxAdjDsInvTp);
                if (dsInvTpTMsg != null && S21StringUtil.isEquals(invTpCd, dsInvTpTMsg.invTpCd.getValue())) {
                    taxAdjDsInvTpNmList.add(dsInvTpTMsg.dsInvTpNm.getValue());
                }
            }
        }
        return taxAdjDsInvTpNmList;
    }

    private static List<String> getAvalTaxAdjustmentDsInvTypeCode(NFCL3000CMsg bizMsg) {

        List<String> avalTaxAdjDsInvTpCdList = new ArrayList<String>(0);

        String taxAdjDsInvTpList = NFCL3000CommonLogicForConst.getInstance().getVarCharConstVal(bizMsg.glblCmpyCd.getValue(), NFCL3000_TAX_ADJ_DS_INV_TP);
        if (taxAdjDsInvTpList != null) {
            String[] taxAdjDsInvTpArray = taxAdjDsInvTpList.split(",");
            for (String taxAdjDsInvTp : taxAdjDsInvTpArray) {
                avalTaxAdjDsInvTpCdList.add(taxAdjDsInvTp);
            }
        }
        return avalTaxAdjDsInvTpCdList;
    }

    private static DS_INV_TPTMsg getDsInvTpTMsg(String glblCmpyCd, String dsInvTpCd) {

        DS_INV_TPTMsg dsInvTpTMsg = new DS_INV_TPTMsg();
        ZYPEZDItemValueSetter.setValue(dsInvTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsInvTpTMsg.dsInvTpCd, dsInvTpCd);

        return (DS_INV_TPTMsg) S21CacheTBLAccessor.findByKey(dsInvTpTMsg);
    }
    // END 2019/06/05 S.Takami [QC#50685,ADD]
    // START 2019/06/06 S.Takami [QC#50691,ADD]
    /**
     * <pre>
     * Get Supply Invoice DS_ORD_CATG_CD
     * @param bizMsg Biz. Message
     * @return supply DS_ORD_CATG_CD. if the invoice is not supply invoice, this mesthod will return null.
     */
    public static String supplyInvoicdOrdCatgCd(NFCL3000CMsg bizMsg) {

        if (isDsInvTpSupply(bizMsg)) {
            String supplyDsOrdCatgCdList = NFCL3000CommonLogicForConst.getInstance().getVarCharConstVal(bizMsg.glblCmpyCd.getValue(), NFCL3000_SPLY_DS_ORD_CATG);
            if (supplyDsOrdCatgCdList == null) {
                supplyDsOrdCatgCdList = NFCL3000_SPLY_DS_ORD_CATG_VAL;
            }
            String[] supplyDsOrdCatgCdArray = supplyDsOrdCatgCdList.split(",");
            return supplyDsOrdCatgCdArray[0];
        }
        return null;
    }

    private static boolean isDsInvTpSupply(NFCL3000CMsg bizMsg) {

        String dsInvTpSupplyList = NFCL3000CommonLogicForConst.getInstance().getVarCharConstVal(bizMsg.glblCmpyCd.getValue(), NFCL3000_SPLY_INV_TP);
        if (dsInvTpSupplyList == null) {
            dsInvTpSupplyList = NFCL3000_SPLY_INV_TP_VAL;
        }
        String[] dsInvTpSupplyArray = dsInvTpSupplyList.split(",");
        String curDsInvTpCd = bizMsg.dsInvTpCd_H1.getValue();
        for (String dsInvTpCd : dsInvTpSupplyArray) {
            if (S21StringUtil.isEquals(dsInvTpCd, curDsInvTpCd)) {
                return true;
            }
        }
        return false;
    }
    // END 2019/06/06 S.Takami [QC#50691,ADD]

    // Start 2019/07/17 S.Takami [QC#51516,ADD]
    private static MDSE_STORE_PKGTMsg getMdseStorePkg(NFCL3000CMsg bizMsg, int idx) {

        NFCL3000_ACMsg lineMsg = bizMsg.A.no(idx);
        MDSE_STORE_PKGTMsg mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.mdseCd, lineMsg.mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.pkgUomCd, lineMsg.pkgUomCd_A1);
        mdseStorePkgTMsg = (MDSE_STORE_PKGTMsg) S21CacheTBLAccessor.findByKey(mdseStorePkgTMsg);
        if (mdseStorePkgTMsg == null) {
            S21SsmEZDResult ssmRslt = NFCL3000Query.getInstance().getPkgUomByBaseUomCd(bizMsg.glblCmpyCd.getValue(), //
                    lineMsg.mdseCd_A1.getValue(), //
                    lineMsg.pkgUomCd_A1.getValue());

            if (ssmRslt.isCodeNormal()) {
                mdseStorePkgTMsg = ((List<MDSE_STORE_PKGTMsg>) ssmRslt.getResultObject()).get(0);
            } else {
                mdseStorePkgTMsg = null;
            }
        }
        return mdseStorePkgTMsg;
    }
    // End 2019/07/17 S.Takami [QC#51516,ADD]

    // START 2019/10/03 S.Takami [QC#53881,ADD]
    private static boolean hasErrorOnQty(NFCL3000CMsg bizMsg, int idx, String strQty) {

        boolean rslt = false;
        NFCL3000_ACMsg lineMsg = bizMsg.A.no(idx);
        int decimalDigits = 0;
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.xxDplyCtrlFlg_A1.getValue())) {
            decimalDigits = lineMsg.getAttr("invDplyQty_A1").getFracDigit();
        } else if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, lineMsg.xxDplyCtrlFlg_A1.getValue())) {
            decimalDigits = lineMsg.getAttr("shipQty_A1").getFracDigit();
        } else {
            decimalDigits = lineMsg.getAttr("ordCustUomQty_A1").getFracDigit();
        }

        String[] decimalQtyArray = strQty.split("\\.");
        if (decimalQtyArray.length > 2) {
            rslt = true;
        } else if (decimalQtyArray.length > 1 && decimalDigits == 0) {
            rslt = true;
        } else if (decimalQtyArray.length > 1 && decimalDigits < decimalQtyArray[1].length()) {
            rslt = true;
        }
        return rslt;
    }
    // END 2019/10/03 S.Takami [QC#53881,ADD]

    // START 2019/11/02 [QC#54463,ADD]
    private static void taxCalcForTaxAdjustmentIfNotFinalized(NFCL3000CMsg bizMsg) {
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            BigDecimal totBolDealTaxAmt = BigDecimal.ZERO;
            for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                if (bizMsg.A.no(j).invBolLineNum_A1.getValue().equals(bizMsg.D.no(i).invBolLineNum_D1.getValue())) {
                    totBolDealTaxAmt = totBolDealTaxAmt.add(bizMsg.A.no(j).invLineDealTaxAmt_A1.getValue());
                }
            }
            // START 2019/11/15 [QC#54697, MOD]
            if (INV_TP.CREDIT_MEMO.equals(bizMsg.invTpCd_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).totBolDealTaxAmt_D1, totBolDealTaxAmt.negate());
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).totBolDealTaxAmt_D1, totBolDealTaxAmt);
            }
            // END   2019/11/15 [QC#54697, MOD]
        }
    }
    // END   2019/11/02 [QC#54463,ADD]
    
    // START 2020/04/22 [QC#56117,ADD]
    /**
     * chkMeterChargeAmount
     * @param bizMsg
     */
    public static boolean chkMeterChargeAmount(NFCL3000CMsg bizMsg) {

        if(isManualInvoice(bizMsg) 
            && ZYPCommonFunc.hasValue(bizMsg.origInvNum_E1.getValue())
            && INV_TP.INVOICE.equals(bizMsg.invTpCd_H1.getValue())) {

            boolean meterChargeFlg = false;
            BigDecimal total_salesAmount = BigDecimal.ZERO;
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (SVC_INV_CHRG_TP.METER_CHARGE.equals(bizMsg.A.no(i).svcInvChrgTpCd_A1.getValue())) {
                    meterChargeFlg = true;
                    total_salesAmount = total_salesAmount.add(bizMsg.A.no(i).invLineDealNetAmt_A1.getValue());
                }
            }

            if (!meterChargeFlg) {
                return true;
            }

            BigDecimal total_meterDetail = BigDecimal.ZERO;
            NFCL3080CMsg nfcl3080cMsg = new NFCL3080CMsg();
            NFCL3080SMsg nfcl3080sMsg = new NFCL3080SMsg();

            ZYPTableUtil.clear(nfcl3080cMsg.A);
            ZYPTableUtil.clear(nfcl3080sMsg.A);

            ZYPEZDItemValueSetter.setValue(nfcl3080cMsg.invNum, bizMsg.invNum_H1.getValue());

            S21SsmEZDResult ssmResult = NFCL3080Query.getInstance().meterSearch(nfcl3080cMsg, nfcl3080sMsg);

            if (ssmResult.isCodeNotFound()) {
                return false;
            } else {
                if (ssmResult.getQueryResultCount() > nfcl3080sMsg.A.length()) {
                    //bizMsg.setMessageInfo(NZZM0001W);
                    nfcl3080sMsg.A.setValidCount(nfcl3080sMsg.A.length());
                } else {
                    nfcl3080sMsg.A.setValidCount(ssmResult.getQueryResultCount());
                }

                for (int i = 0; i < nfcl3080sMsg.A.getValidCount(); i++) {
                    total_meterDetail = total_meterDetail.add(nfcl3080sMsg.A.no(i).mtrChrgDealAmt_A.getValue());
                }
            }
            
            if (total_salesAmount.compareTo(total_meterDetail) != 0) {
                for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                    if (SVC_INV_CHRG_TP.METER_CHARGE.equals(bizMsg.A.no(i).svcInvChrgTpCd_A1.getValue())) {
                        bizMsg.A.no(i).invLineDealNetAmt_A1.setErrorInfo(1, NFCM0913E);
                    }
                }
                return false;
            }
        }

        return true;
    }
    //END   2020/04/22 [QC#56117,ADD]
    // START 2021/01/04 R.Kurahashi [QC#56282, ADD]
    /**
     * checkContract
     * @param bizMsg NFCL3000CMsg
     * @return
     */
    public static  boolean checkContract(NFCL3000CMsg bizMsg) {
        boolean isNotError = true;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NFCL3000_ACMsg lineMsg = bizMsg.A.no(i);
            String srcTpCd = NFCL3000Query.getInstance().getSrcTpCd(bizMsg, lineMsg);
            if (SVC_INV_SRC_TP.CONTRACT.equals(srcTpCd)) {
                bizMsg.setMessageInfo(NFCM0916E);
                return false;
            }
        }
        return isNotError;
    }
    // END 2021/01/04 R.Kurahashi [QC#56282, ADD]

}
