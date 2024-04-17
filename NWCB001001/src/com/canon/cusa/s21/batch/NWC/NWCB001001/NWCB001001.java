/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Revenue Recognition Batch.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/21/2009   Fujitsu         H.Nagashima     Create          N/A
 * 09/14/2009   Fujitsu         H.Nagashima     Update          110,111,112
 * 09/16/2009   Fujitsu         H.Nagashima     Update          120,122
 * 09/23/2009   Fujitsu         H.Nagashima     Update          217,218
 * 09/24/2009   Fujitsu         H.Nagashima     Update          224
 * 10/01/2009   Fujitsu         H.Nagashima     Update          317
 * 10/07/2009   Fujitsu         H.Nagashima     Update          RQ0397
 * 10/09/2009   Fujitsu         H.Nagashima     Update          526
 * 10/13/2009   Fujitsu         H.Nagashima     Update          668
 * 10/13/2009   Fujitsu         H.Nagashima     Update          714,725
 * 10/21/2009   Fujitsu         H.Nagashima     Update          788(RQ461)
 * 10/23/2009   Fujitsu         H.Nagashima     Update          812(RQ464)
 * 10/30/2009   Fujitsu         H.Nagashima     Update          879(RQ507)
 * 11/06/2009   Fujitsu         N.Mitsuishi     Update          1068,1601,1614
 * 11/10/2009   Fujitsu         H.Nagashima     Update          1694
 * 11/17/2009   Fujitsu         H.Nagashima     Update          1886
 * 11/18/2009   Fujitsu         H.Nagashima     Update          RQ0598
 * 11/24/2009   Fujitsu         N.Mitsuishi     Update          1992
 * 11/25/2009   Fujitsu         N.Mitsuishi     Update          2091
 * 12/22/2009   Fujitsu         T.Nagase        Update          2719
 * 12/28/2009   Fujitsu         H.Nagashima     Update          2900
 * 12/30/2009   Fujitsu         H.Nagashima     Update          2920,2921
 * 01/05/2010   Fujitsu         A.Suda          Update          N/A (refactoring : cache and high-performance Tuning)
 * 01/20/2010   Fujitsu         S.Takami        Update          N/A (bag fix : for promotion)
 * 02/02/2010   Fujitsu         H.Nagashima     Update          3336
 * 03/05/2010   Fujitsu         H.Nagashima     Update          3522
 * 03/18/2010   Fujitsu         H.Nagashima     Update          3298
 * 03/31/2010   Fujitsu         H.Nagashima     Update          5327
 * 04/13/2010   Fujitsu         S.Takami        Update          4923, 5677
 * 04/29/2010   Fujitsu         H.Nagashima     Update          6147
 * 05/04/2010   Fujitsu         H.Nagashima     Update          6219
 * 05/12/2010   Fujitsu         H.Nagashima     Update          5359
 * 06/02/2010   Fujitsu         H.Nagashima     Update          6742,6859
 * 06/03/2010   Fujitsu         H.Nagashima     Update          6421
 * 06/11/2010   Fujitsu         K.Tajima        Update          7110
 * 06/11/2010   Fujitsu         K.Tajima        Update          7109
 * 06/11/2010   Fujitsu         K.Tajima        Updae           2575 - Refactoring & Performance Tuning
 * 06/25/2010   Fujitsu         H.Nagashima     Update          7150,7181
 * 09/14/2010   Fujitsu         R.Watanabe      Update          242(All2)
 * 10/26/2010   Fujitsu         R.Watanabe      Update          580(All2)
 * 11/23/2010   Fujitsu         R.Watanabe      Update          793(All2)
 * 12/30/2010   Fujitsu         R.Watanabe      Update          1047(All2)
 * 12/31/2010   Fujitsu         R.Watanabe      Update          1054(All2)
 * 02/05/2010   Fujitsu         R.Watanabe      Update          1324(PRD)
 * 02/13/2010   Fujitsu         R.Watanabe      Update          1440(PRD)
 * 02/17/2010   Fujitsu         R.Watanabe      Update          1571(PRD)
 * 03/02/2010   Fujitsu         R.Watanabe      Update          1725(PRD)
 * 03/04/2010   Fujitsu         R.Watanabe      Update          1725(PRD)
 * 03/07/2010   Fujitsu         R.Watanabe      Update          1799(PRD)
 * 03/10/2010   Fujitsu         R.Watanabe      Update          1865(PRD)
 * 04/14/2011	CSAI			A.Katayama		Update			2047(PRD)
 * 04/28/2011	CSAI			A.Katayama		Update			2096(PRD)
 * 11/09/2011	CSAI			A.Katayama		Update			ITG#369928
 * 03/29/2012   Fujitsu         S.Yamamoto      Update          ITG#388976
 * 11/08/2012   Fujitsu         S.Tsunaki       Update          WDS #101, #108, #109, #111, #134, #110, #117
 * 12/23/2012   Fujitsu         S.Tsunaki       Update          WDS Defect#65
 * 12/27/2012   Fujitsu         S.Tsunaki       Update          WDS Defect#96, 98
 * 01/04/2013   Fujitsu         S.Tsunaki       Update          WDS Defect#145
 * 01/08/2013   Fujitsu         S.Tsunaki       Update          WDS Defect#155
 * 01/16/2013   Fujitsu         S.Tsunaki       Update          WDS Defect#261
 * 01/22/2013   Fujitsu         S.Tsunaki       Update          WDS Defect#304
 * 01/30/2013   Fujitsu         S.Tsunaki       Update          WDS Defect#535
 * 02/08/2013   Fujitsu         S.Tsunaki       Update          WDS Defect#684
 * 02/12/2013   Fujitsu         S.Tsunaki       Update          WDS Defect#673
 * 02/26/2013   Fujitsu         D.Yanagisawa    Update          WDS Defect#673
 * 07/30/2013   Fujitsu         N.Nakazawa      Update          WDS R-OM010,R-OM021, R-OM028, R-OM040
 * 07/30/2013   Fujitsu         N.Nakazawa      Update          WDS R-OM050
 * 10/21/2015   Fujitsu         H.Nagashima     Update          CSA
 * 11/09/2016   Fujitsu         H.Nagashima     Update          CSA QC#15212
 * 01/06/2016   Fujitsu         H.Nagashima     Update          CSA QC#16918
 * 02/06/2017   Fujitsu         T.Yoshida       Update          CSA QC#15510
 * 02/27/2017   Fujitsu         H.Nagashima     Update          CSA QC#17758
 * 10/18/2017   Fujitsu         Mz.takahashi    Update          CSA L3#430 QC#16437
 * 10/24/2017   Fujitsu         H.Sugawara      Update          QC#21773
 * 11/21/2017   Fujitsu         T.Aoi           Update          QC#22508
 * 12/06/2017   Fujitsu         K.Ishizuka      Update          QC#22925
 * 01/26/2018   Hitachi         E.Kameishi      Update          QC#22851
 * 03/05/2018   Fujitsu         A.Kosai         Update          QC#20153 (Sol#152)
 * 05/10/2018   Fujitsu         Mz.Takahashi    Update          QC#20343
 * 05/24/2018   Fujitsu         A.Kosai         Update          QC#22097
 * 06/05/2018   Fujitsu         H.Nagashima     Update          QC#25966
 * 07/20/2018   Fujitsu         A.Kosai         Update          QC#26836
 * 07/27/2018   Hitachi         E.Kameishi      Update          QC#26987
 * 08/03/2018   Fujitsu         Mz.takahashi    Update          QC#27285
 * 08/17/2018   Fujitsu         Mz.takahashi    Update          QC#27830
 * 09/25/2018   Fujitsu         W.Honda         Update          QC#28436
 * 05/14/2019   Fujitsu         C.Hara          Update          QC#50142
 * 07/22/2019   Fujitsu         T.Noguchi       Update          QC#51848
 * 09/04/2019   Fujitsu         W.Honda         Update          QC#53306
 * 11/26/2019   Fujitsu         Y.Kanefusa      Update          QC#54722
 * 08/03/2021   CITS            K.Ogino         Update          QC#59039
 * 03/24/2022   Hitachi         K.Kishimoto     Update          QC#59825
 *</pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKey;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKeyForUpdate;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.removeByPartialValue;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Arrays.asList;
import static parts.dbcommon.EZDTBLAccessor.RTNCD_NORMAL;
import static parts.dbcommon.EZDTBLAccessor.RTNCD_NOT_FOUND;
import static parts.dbcommon.EZDTBLAccessor.update;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_INV_TPTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.INVTMsg;
import business.db.MDSETMsg;
import business.db.REV_RECOG_DTL_WRKTMsg;
import business.db.REV_RECOG_HDR_WRKTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC309001PMsg;
import business.parts.NWZC035001PMsg;
import business.parts.NWZC056001PMsg;
import business.parts.NWZC056001_runningCountUpdateListPMsg;
import business.parts.NWZC188001PMsg;
import business.parts.NWZC188001_shpgPlnNumListPMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC309001.NLZC309001;
import com.canon.cusa.s21.api.NLZ.NLZC309001.constant.NLZC309001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC035001.NWZC035001;
import com.canon.cusa.s21.api.NWZ.NWZC056001.NWZC056001;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.batch.NWC.NWCB001001.NWCB001001.Enum.BizProcLogEventId;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.common.NWX.NWXC100001.NWXC100001CheckHoldCondition;
import com.canon.cusa.s21.common.NWX.NWXC100001.NWXC100001CheckHoldConditionBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONV_PROC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LAK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_COST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.REV_RECOG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

public class NWCB001001 extends S21BatchMain {

    private static final String SET_PARENT_LINE_SUB_NUM = "000";

    private static final int MAX_LIST_SIZE = 1000;

    private static final String NWCM0007E = "NWCM0007E";
    private static final String NWCM0025E = "NWCM0025E";
    private static final String NWCM0026E = "NWCM0026E";
    private static final String NWCM0027E = "NWCM0027E";
    private static final String NWCM0044E = "NWCM0044E";
    private static final String NWCM0047E = "NWCM0047E";
    private static final String NWCM0051E = "NWCM0051E";
    private static final String NWCM0052E = "NWCM0052E";
    private static final String NWCM0053E = "NWCM0053E";
    private static final String NWCM0058E = "NWCM0058E";
    private static final String NWCM0059E = "NWCM0059E";
    private static final String NWCM0060E = "NWCM0060E";
    private static final String NWCM0080E = "NWCM0080E";
    private static final String NWCM0112E = "NWCM0112E";

    private static final String Y = ZYPConstant.FLG_ON_Y;
    private static final String N = ZYPConstant.FLG_OFF_N;
    private static final String NWCB0010_MONTH_END_MODE = "NWCB0010_MONTH_END_MODE";
    private static final String SHIP_FROM_INVTY_LOC_CD_CR = "CR";
    private boolean hasErr;

    private TERM_CD termCd = TERM_CD.NORMAL_END;
    private int totalRecCnt;
    private int normalRecCnt;
    private int errRecCnt;

    private String glblCmpyCd;
    private String slsDt;
    private String mailTemplateId;
    private String batchExecMode;
    private String CONST_KEY_CR_AND_BILL_ONLY_DUMMY_WH_CD = "CR_AND_BILL_ONLY_DUMMY_WH_CD";
    private String CONST_ORD_LINE_CTX_TP_REV_RECOG_EXEM   = "REV_RECOG_EXEM";
    private String CONST_MDSE_TP_CTX_TP_ASSET_CRAT_TRGT   = "ASSET_CRAT_TRGT";
    private List<String> crRebillDummyWhCdList;
    private S21LRUMap<String, Object> s21LRUMap  = new S21LRUMap<String, Object>();

    private final S21SsmBatchClient ssmClient;

    /**
     * NWCB0001001 - Inventory Update
     */
    private NWCB001001_InventoryUpdate invtyUpdObj;
    private NWCB001001() {
        super();
        ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    public static void main(String[] args) {
        new NWCB001001().executeBatch();
    }

    @Override
    protected void initRoutine() {

        // Global Company Code
        glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(NWCM0059E, toArray("Global Company Code"));
        }

        // Sales Date
        slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // User Variable - 2 : Mail Template ID
        mailTemplateId = getUserVariable2();
        if (!hasValue(mailTemplateId)) {
            throw new S21AbendException(NWCM0059E, toArray("Mail Template ID"));
        }

        // User Variable - 3 : Batch Execution Mode
        batchExecMode = getUserVariable3();
        if (!hasValue(batchExecMode)) {
            throw new S21AbendException(NWCM0059E, toArray("Batch Execution Mode"));
        }

        debug(getClass(), "+++++<System/User Variables>+++++++++++++++");
        debug(getClass(), "<System>");
        debug(getClass(), " - glblCmpyCd     = ", this.glblCmpyCd);
        debug(getClass(), " - slsDt          = ", this.slsDt);
        debug(getClass(), "<User>");
        debug(getClass(), " - mailTemplateId = ", this.mailTemplateId);
        debug(getClass(), " - batchExecMode  = ", this.batchExecMode);
        debug(getClass(), "+++++<System/User Variables>+++++++++++++++");
    }

    @Override
    protected void mainRoutine() {

        /* TODO [Def# 2575] K.Tajiam - Refactoring & Performance Tuning
         * 内部の定数、変数、そしてメソッドが多すぎて保守性に欠ける為、それぞれの主処理に特化したインナークラスを作成してそれらに隠蔽し、更にソースを読み易く改善。
         */

        // --------------------------------------------------
        // 1. delete Revenue Recognition Work Table
        //     - REV_RECOG_HDR_WRK
        //     - REV_RECOG_DTL_WRK
        // --------------------------------------------------
        if (!new RevRecogWrkDeletor().exec()) {
            throw new S21AbendException(NWCM0007E);
        } else {
            commit();
        }
        // --------------------------------------------------
        // 2. create Revenue Recognition Work Table
        //     - REV_RECOG_HDR_WRK
        //     - REV_RECOG_DTL_WRK
        // --------------------------------------------------
        if (new RevRecogWrkCreator().exec()) {
            commit();

            // --------------------------------------------------
            // 3. Revenue Recognition.
            // --------------------------------------------------
            new RevRecogExecutor().exec();
        }

        if (this.hasErr) {
            this.termCd = TERM_CD.WARNING_END;
        }
    }

    @Override
    protected void termRoutine() {

        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);

        if (TERM_CD.WARNING_END == this.termCd) {
            // post error mail.
            if (!postErrorMail()) {
                throw new S21AbendException(NWCM0060E);
            }
        }
        // Add : 11/09/2011 Add Month End mode for Revenue Recognition
        if (this.errRecCnt > 0) {
            String monthEndFlg = ZYPCodeDataUtil.getVarCharConstValue(NWCB0010_MONTH_END_MODE, glblCmpyCd);
            if (Y.equals(monthEndFlg)) {
                S21InfoLogOutput.println(NWCM0080E, new String[] {"Month End!!" });
                throw new S21AbendException(NWCM0080E, new String[] {"Month End!!" });
            }
        }
    }

    private MDSETMsg getMdse(String mdseCd) {

        final MDSETMsg reqMdse = new MDSETMsg();
        setValue(reqMdse.glblCmpyCd, glblCmpyCd);
        setValue(reqMdse.mdseCd,     mdseCd);

        return (MDSETMsg) S21CacheTBLAccessor.findByKey(reqMdse);
    }

    private boolean postErrorMail() {
        final String methodNm = "postErrorMail";
        debugMethodStart(getClass(), methodNm);

        try {

            final List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>();

            NWXC001001MailSubstituteString sbsStr;

            sbsStr = new NWXC001001MailSubstituteString();
            sbsStr.setSbstKey("batchId");
            sbsStr.setSbstStr(this.getClass().getSimpleName());
            sbsStrList.add(sbsStr);

            sbsStr = new NWXC001001MailSubstituteString();
            sbsStr.setSbstKey("batchNm");
            sbsStr.setSbstStr("Revenue Recognition");
            sbsStrList.add(sbsStr);

            sbsStr = new NWXC001001MailSubstituteString();
            sbsStr.setSbstKey("batchProcLogId");
            sbsStr.setSbstStr(super.getBatchProcessLogID());
            sbsStrList.add(sbsStr);

            boolean isNormalEnd = new NWXC001001Mail().postMail(glblCmpyCd, "NWCB0010", mailTemplateId, sbsStrList);
            debug(getClass(), "isNormalEnd? = [", isNormalEnd, "]");

            return isNormalEnd;

        } finally {
            debugMethodEnd(getClass(), methodNm);
        }
    }

    private static void clear(Collection... collections) {
        for (Collection col : collections) {
            if (!isEmpty(col)) {
                col.clear();
            }
        }
    }

    private static void debug(Class caller, Object... debugInfos) {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("<<").append(caller.getName()).append(">> ");
        for (Object debugInfo : debugInfos) {
            sb.append(debugInfo);
        }
        S21InfoLogOutput.println(sb.toString());
    }

    private static void debugMethodEnd(Class caller, String methodNm) {
        debug(caller, new StringBuilder().append("[E N D]").append("******************** method : [").append(methodNm).append("]").toString());
    }

    private static void debugMethodStart(Class caller, String methodNm) {
        debug(caller, new StringBuilder().append("[START]").append("******************** method : [").append(methodNm).append("]").toString());
    }

    private static BigDecimal divide(BigDecimal bc1, BigDecimal bc2, int scale, RoundingMode roundingMode) {

        if (bc1 == null || bc2 == null) {
            return ZERO;
        }
        if (bc1.compareTo(ZERO) == 0 || bc2.compareTo(ZERO) == 0) {
            return ZERO;
        }
        return bc1.divide(bc2, scale, roundingMode);
    }

    private static boolean isEmpty(Collection col) {
        return col == null || col.isEmpty();
    }

    private static boolean isEquals(String str1, String str2) {

        if (str1 != null) {
            if (str2 != null) {
                if (str1.equals(str2)) {
                    return true;
                }
            }
        } else {
            if (str2 == null) {
                return true;
            }
        }
        return false;
    }

    private static boolean isIntangibleItem(REV_RECOG_DTL_WRKTMsg dtlWrk) {

        String invtyCtrlFlg = dtlWrk.invtyCtrlFlg.getValue();
        String poReqFlg     = dtlWrk.poReqFlg.getValue();

        // intangible item is not VendorDrop
        boolean isIntangibleNotVendorDropItem = N.equals(invtyCtrlFlg) && N.equals(poReqFlg);

        // set parent is intangible
        boolean isSetParent = isSetParentLineSubNum(dtlWrk.cpoDtlLineSubNum.getValue());

        return isIntangibleNotVendorDropItem || isSetParent;
    }

    private static boolean isSetParentLineSubNum(String lineSubNum) {
        return SET_PARENT_LINE_SUB_NUM.equals(lineSubNum);
    }

    private static String[] toArray(String str) {
        return new String[] {str };
    }

    static interface Enum {

        /**
         * ACCT_ARTH_TP_CD
         * 
         * @author K.Tajima
         */
        enum AcctArthTpCd {
            
            MULTIPLY("*");
            
            private final String cd;
            
            private AcctArthTpCd(String cd) {
                this.cd = cd;
            }

            private String getCd() {
                return cd;
            }
        }

        /**
         * Business Process Log Event ID
         * 
         * @author K.Tajima
         */
        enum BizProcLogEventId {

            SHIPED("Shipped"),

            INVOICED("Invoiced"),

            CLOSE("Close");

            private final String id;

            private BizProcLogEventId(String id) {
                this.id = id;
            }

            private String getId() {
                return id;
            }
        }
    }

    /**
     * Revenue Recognition Interface for Inner Class.
     * 
     * @author K.Tajima
     */
    static interface RevRecogIF extends CPO_ORD_TP, CPO_SRC_TP, TRX_SRC_TP {        

        public boolean exec();
    }

    /**
     * APIs
     * 
     * @author K.Tajima
     */
    private static final class API {

        /**
         * NLZC002001 - Inventory Update API
         */
        private final NLZC002001 inventoryUpdateAPI = new NLZC002001();

        /**
         * NWZC035001 - Invoice API
         */
        private final NWZC035001 invoiceAPI = new NWZC035001();

    }

    /**
     * Revenue Recognition Executor.
     * 
     * @author K.Tajima
     */
    private final class RevRecogExecutor extends S21SsmBooleanResultSetHandlerSupport implements RevRecogIF {

        private static final String SUB_SYSTEM_ID = "NWC";
        private static final String PROCESS_ID    = "OM";

        private final API api = new API();
        private final S21LRUMap<String, List<SetItemData>>  setItemCache        = new S21LRUMap<String, List<SetItemData>>();
        private final S21LRUMap<String, SHPG_ORDTMsg>       shpgOrdCache        = new S21LRUMap<String, SHPG_ORDTMsg>();
        private final S21LRUMap<String, Integer>            stdCcyDigitCache    = new S21LRUMap<String, Integer>();

        private CPOTMsg                  lockedCpo;
        private Map<String, CPO_DTLTMsg> lockedCpoDtl;
        private List<SHPG_PLNTMsg>       lockedShpgPlnList;

        private DS_CPO_CONFIGTMsg        lockedCpoConfig;
        private List<String>             loanToSlsOrdLineCatgCdList;
        private List<String>             cratInvOrdLineCatgCdList;
        private List<String>             tonerCntOrdLineCatgList;
        private List<DS_ORD_TP_PROC_DFNTMsg> dsOrdTpProcDfnTMsgList;
        private List<String>             coaMdseTpCdList;
        private List<String>             itrlDsOrdTpCdList;

        private String CONST_ORD_CATG_CTX_TP_CD_SERVICE_EXCHANGE = "SERVICE_EXCHANGE";
        private String CONST_ORD_LINE_CTX_TP_CD_TONER_CNT_REQ    = "TONER_CNT_REQ";

        private boolean isExistShpgPln = false;

        private List<String> shpgNumList;

        // QC#20343 Add Start
        private class RevRecogExecutorInner{
            private List<RevenueKey> refkeys;
            private REV_RECOG_HDR_WRKTMsg hdrWrk;
            private List<REV_RECOG_DTL_WRKTMsg> dtlWrkList;

            RevRecogExecutorInner(List<RevenueKey> refkeys, REV_RECOG_HDR_WRKTMsg hdrWrk, List<REV_RECOG_DTL_WRKTMsg> dtlWrkList){
                this.refkeys = refkeys;
                this.hdrWrk = hdrWrk;
                this.dtlWrkList = dtlWrkList;
            }

            public List<RevenueKey> getRefKeys(){
                return this.refkeys;
            }
            
            public REV_RECOG_HDR_WRKTMsg getHdrWrk(){
                return this.hdrWrk;
            }
            
            public List<REV_RECOG_DTL_WRKTMsg> getDtlWrkList(){
                return this.dtlWrkList;
            }
        }

        private class RevenueKey{
            private String cpoOrdNum;
            private String dsCpoConfigPk;
            private String cpoDtlLineNum;
            private String cpoDtlLineSubNum;
            
            public RevenueKey(String cpoOrdNum, String dsCpoConfigPk, String cpoDtlLineNum, String cpoDtlLineSubNum){
                this.cpoOrdNum = cpoOrdNum;
                this.dsCpoConfigPk = dsCpoConfigPk;
                this.cpoDtlLineNum = cpoDtlLineNum;
                this.cpoDtlLineSubNum = cpoDtlLineSubNum;
            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((cpoOrdNum == null) ? 0 : cpoOrdNum.hashCode());
                result = prime * result + ((dsCpoConfigPk == null) ? 0 : dsCpoConfigPk.hashCode());
                result = prime * result + ((cpoDtlLineNum == null) ? 0 : cpoDtlLineNum.hashCode());
                result = prime * result + ((cpoDtlLineSubNum == null) ? 0 : cpoDtlLineSubNum.hashCode());
                return result;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }

                if (obj == null) {
                    return false;
                }

                if (getClass() != obj.getClass()) {
                    return false;
                }

                RevenueKey other = (RevenueKey) obj;

                if (cpoOrdNum == null) {
                    if (other.cpoOrdNum != null) {
                        return false;
                    }
                } else if (!cpoOrdNum.equals(other.cpoOrdNum)){
                    return false;
                }

                if (dsCpoConfigPk == null) {
                    if (other.dsCpoConfigPk != null) {
                        return false;
                    }
                } else if (!dsCpoConfigPk.equals(other.dsCpoConfigPk)){
                    return false;
                }

                if (cpoDtlLineNum == null) {
                    if (other.cpoDtlLineNum != null) {
                        return false;
                    }
                } else if (!cpoDtlLineNum.equals(other.cpoDtlLineNum)){
                    return false;
                }

                if (cpoDtlLineSubNum == null) {
                    if (other.cpoDtlLineSubNum != null) {
                        return false;
                    }
                } else if (!cpoDtlLineSubNum.equals(other.cpoDtlLineSubNum)){
                    return false;
                }

                return true;
            }

            public String getCpoOrdNum() {
                return cpoOrdNum;
            }

            public String getDsCpoConfigPk() {
                return dsCpoConfigPk;
            }

            public String getCpoDtlLineNum() {
                return cpoDtlLineNum;
            }

            public String getCpoDtlLineSubNum() {
                return cpoDtlLineSubNum;
            }
        }
        // QC#20343 Add End

        public boolean exec() {
            final String methodNm = "exec";
            debugMethodStart(getClass(), methodNm);

            try {

                final Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);

                return (Boolean) ssmClient.queryObject("getRevRecogHdrWrkList", ssmParam, this);

            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            final String methodNm = "doProcessQueryResult";
            debugMethodStart(getClass(), methodNm);

            try {
                List<RevRecogExecutorInner> waitConfigList = new ArrayList<RevRecogExecutorInner>();
                List<RevenueKey>  processedKeys = new ArrayList<RevenueKey>();

                // records
                rs.last();
                final int records = rs.getRow();
                rs.first();
                debug(getClass(), "records = ", records);

                if (records <= 0) {
                    return hasErr;
                }

                // QC#20343 Add Start

                //
                // Revenue Recognition
                // 

                doProcessQueryResult(rs, processedKeys, waitConfigList);


                //
                // Revenue Recognition(Wait Conifg)
                //

                int rownum = rs.getRow();
                for(RevRecogExecutorInner item : waitConfigList){
                    Boolean flg = false;

                    for (RevenueKey refKey : item.getRefKeys()){
                        for (RevenueKey pRefKey: processedKeys){
                            if (refKey.equals(pRefKey)){
                                flg = true;
                                break;
                            }
                        }

                        if (flg){
                            break;
                        }
                    }

                    if (flg){
                        doProcessQueryResult(item.getHdrWrk(), item.getDtlWrkList(), rownum++);
                    }
                }

                // QC#20343 Add End

                return hasErr;

            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        private Boolean doProcessQueryResult(ResultSet rs, List<RevenueKey>  processedKeys, List<RevRecogExecutorInner> waitConfigList) throws SQLException {

            do {
                // REV_RECOG_HDR_WRK
                final REV_RECOG_HDR_WRKTMsg hdrWrk = toHdrWrk(rs);
                if (hasValue(hdrWrk.reBillPairCpoOrdNum)) {
                    if (!excludeForReBillPairOrder(hdrWrk)) {
                        continue;
                    }
                }

                // REV_RECOG_DTL_WRK
                final List<REV_RECOG_DTL_WRKTMsg> dtlWrkList = getRevRecogDtlWrkList(hdrWrk.cpoOrdNum.getValue(), hdrWrk.dsCpoConfigPk.getValue());
                if (isEmpty(dtlWrkList)) {
                    continue;
                }
                removeExcludeData(hdrWrk, dtlWrkList, waitConfigList);
                if (dtlWrkList.isEmpty()) {
                    continue;
                }

                removeParentOnlyData(dtlWrkList);
                if (dtlWrkList.isEmpty()) {
                    continue;
                }

                // QC#20343 Add Start
                Boolean ret = isWaitSubscriptionSupportServiceData(hdrWrk, dtlWrkList, waitConfigList);
                if (ret) {
                    continue;
                }

                doProcessQueryResult(hdrWrk, dtlWrkList, processedKeys, rs.getRow());
                // QC#20343 Add End

                // QC#20343 Del Start
                /*
                        removeExcludeData(hdrWrk, dtlWrkList);
                        if (dtlWrkList.isEmpty()) {
                            continue;
                        }

                        removeParentOnlyData(dtlWrkList);
                        if (dtlWrkList.isEmpty()) {
                            continue;
                        }

                        debug(getClass(), "++++++++++++++++++++++++++++++++++++++++++++++++++");
                        debug(getClass(), " Row[", rs.getRow(), "]");
                        debug(getClass(), "  - cpoOrdNum           = ", hdrWrk.cpoOrdNum.getValue());
                        debug(getClass(), "  - dsCpoConfigPk       = ", hdrWrk.dsCpoConfigPk.getValue());
                        debug(getClass(), "  - cpoOrdTpCd          = ", hdrWrk.cpoOrdTpCd.getValue());
                        debug(getClass(), "  - cpoSrcTpCd          = ", hdrWrk.cpoSrcTpCd.getValue());
                        debug(getClass(), "  - reBillPairCpoOrdNum = ", hdrWrk.reBillPairCpoOrdNum.getValue());
                        debug(getClass(), "  - dtlWrkList.size()   = ", dtlWrkList.size());
                        debug(getClass(), "++++++++++++++++++++++++++++++++++++++++++++++++++");

                        // get 'SO_NUM', 'BOL_NUM', 'PRO_NUM' from detail data list
                        final List<SoDivideKey>  divLineKeyList = getSoDivKeyList(dtlWrkList);
                        final List<SoDivideKey>  divKeyList     = getKeyList(divLineKeyList);
                        final List<SHPG_PLNTMsg> shpgPlnList    = lockShpgPlnList(dtlWrkList);
                        this.isExistShpgPln = !(isEmpty(shpgPlnList));
                        setShipInfoToParentAndIntangibleComponent(dtlWrkList, divKeyList, shpgPlnList);

                        boolean isErrorOrder = false;
                        boolean doCommit     = false;

                        // Change Status Number List
                        shpgNumList = new ArrayList<String>();

                        for (SoDivideKey divKey : divKeyList) {

                            List<REV_RECOG_DTL_WRKTMsg> divDtlTmsgList = divideDetailDataList(dtlWrkList, divKey, divLineKeyList, shpgPlnList);
                            if (divDtlTmsgList.isEmpty()) {
                                continue;
                            }

                            List<String> setkeyList = getSetKeyList(divDtlTmsgList);
                            for (int j = 0; j < setkeyList.size(); j++) {

                                if (isErrorOrder) {
                                    continue;
                                }
                                final List<REV_RECOG_DTL_WRKTMsg> dividedDtlWrkList = divideSetDataList(divDtlTmsgList, setkeyList.get(j), shpgPlnList);
                                if (endJudgement(dividedDtlWrkList)) {
                                    break;
                                }

                                this.lockedCpo         = lockCpo(hdrWrk);
                                this.lockedCpoConfig   = lockCpoConfig(hdrWrk);
                                this.lockedCpoDtl      = lockCpoDtl(dividedDtlWrkList);
                                this.lockedShpgPlnList = lockShpgPlnList(dividedDtlWrkList);

                                // --------------------------------------------------
                                // Revenue Recognition
                                // --------------------------------------------------
                                if (!doRevenueRecognition(hdrWrk, dtlWrkList, dividedDtlWrkList)) {
                                    isErrorOrder = true;
                                    break;
                                }
                                if (!isEmpty(dividedDtlWrkList)) {
                                    doCommit = true;
                                }
                            }
                        }

                        debug(getClass(), "### Row[", rs.getRow(), "] : cpoOrdNum[", hdrWrk.cpoOrdNum.getValue(), "] isErrorOrder? = [", isErrorOrder, "]");

                        if (!isErrorOrder && shpgNumList.size() > 0) {
                            if (!callStsUpdApi(hdrWrk.cpoOrdNum.getValue())) {
                                isErrorOrder = true;
                            }
                        }

                        // Error.
                        if (isErrorOrder) {
                            S21InfoLogOutput.println(NWCM0058E, toArray(lockedCpo.cpoOrdNum.getValue()));
                            errRecCnt++;
                            rollback();
                        // Success.
                        } else {
                            if (doCommit) {
                                normalRecCnt++;
                                commit();
                            }
                        }
                        totalRecCnt++;
                */
                // QC#20343 Del End
            } while (rs.next());
            return hasErr;
        }

        // QC#20343 Add Start
        private void doProcessQueryResult(REV_RECOG_HDR_WRKTMsg hdr , List<REV_RECOG_DTL_WRKTMsg> dtl, int rownum) throws SQLException {
            List<RevenueKey>  dummy = new ArrayList<RevenueKey>();
            doProcessQueryResult(hdr, dtl, dummy, rownum);
        }

        private void doProcessQueryResult(REV_RECOG_HDR_WRKTMsg hdr , List<REV_RECOG_DTL_WRKTMsg> dtl, List<RevenueKey>  processedKeys, int rownum) throws SQLException {

            final REV_RECOG_HDR_WRKTMsg hdrWrk = hdr;
            final List<REV_RECOG_DTL_WRKTMsg> dtlWrkList = dtl;

            List<RevenueKey>  pKeys = new ArrayList<RevenueKey>();

            debug(getClass(), "++++++++++++++++++++++++++++++++++++++++++++++++++");
            debug(getClass(), " Row[", rownum, "]");
            debug(getClass(), "  - cpoOrdNum           = ", hdrWrk.cpoOrdNum.getValue());
            debug(getClass(), "  - dsCpoConfigPk       = ", hdrWrk.dsCpoConfigPk.getValue());
            debug(getClass(), "  - cpoOrdTpCd          = ", hdrWrk.cpoOrdTpCd.getValue());
            debug(getClass(), "  - cpoSrcTpCd          = ", hdrWrk.cpoSrcTpCd.getValue());
            debug(getClass(), "  - reBillPairCpoOrdNum = ", hdrWrk.reBillPairCpoOrdNum.getValue());
            debug(getClass(), "  - dtlWrkList.size()   = ", dtlWrkList.size());
            debug(getClass(), "++++++++++++++++++++++++++++++++++++++++++++++++++");

            // get 'SO_NUM', 'BOL_NUM', 'PRO_NUM' from detail data list
            final List<SoDivideKey>  divLineKeyList = getSoDivKeyList(dtlWrkList);
            final List<SoDivideKey>  divKeyList     = getKeyList(divLineKeyList);
            final List<SHPG_PLNTMsg> shpgPlnList    = lockShpgPlnList(dtlWrkList);
            this.isExistShpgPln = !(isEmpty(shpgPlnList));
            setShipInfoToParentAndIntangibleComponent(dtlWrkList, divKeyList, shpgPlnList);

            boolean isErrorOrder = false;
            boolean doCommit     = false;

            // Change Status Number List
            shpgNumList = new ArrayList<String>();

            for (SoDivideKey divKey : divKeyList) {

                List<REV_RECOG_DTL_WRKTMsg> divDtlTmsgList = divideDetailDataList(dtlWrkList, divKey, divLineKeyList, shpgPlnList);
                if (divDtlTmsgList.isEmpty()) {
                    continue;
                }

                List<String> setkeyList = getSetKeyList(divDtlTmsgList);
                for (int j = 0; j < setkeyList.size(); j++) {

                    if (isErrorOrder) {
                        continue;
                    }
                    final List<REV_RECOG_DTL_WRKTMsg> dividedDtlWrkList = divideSetDataList(divDtlTmsgList, setkeyList.get(j), shpgPlnList);
                    if (endJudgement(dividedDtlWrkList)) {
                        break;
                    }

                    this.lockedCpo         = lockCpo(hdrWrk);
                    this.lockedCpoConfig   = lockCpoConfig(hdrWrk);
                    this.lockedCpoDtl      = lockCpoDtl(dividedDtlWrkList);
                    this.lockedShpgPlnList = lockShpgPlnList(dividedDtlWrkList);

                    // --------------------------------------------------
                    // Revenue Recognition
                    // --------------------------------------------------
                    if (!doRevenueRecognition(hdrWrk, dtlWrkList, dividedDtlWrkList)) {
                        isErrorOrder = true;
                        break;
                    }
                    if (!isEmpty(dividedDtlWrkList)) {

                        for(REV_RECOG_DTL_WRKTMsg tmsg: dividedDtlWrkList) {
                            pKeys.add( 
                                new RevenueKey(
                                    hdrWrk.cpoOrdNum.getValue()
                                    , hdrWrk.dsCpoConfigPk.getValue().toPlainString()
                                    , tmsg.cpoDtlLineNum.getValue()
                                    , tmsg.cpoDtlLineSubNum.getValue()
                                )
                            );
                        }

                        doCommit = true;
                    }
                }
                }
            debug(getClass(), "### Row[", rownum, "] : cpoOrdNum[", hdrWrk.cpoOrdNum.getValue(), "] isErrorOrder? = [", isErrorOrder, "]");

            if (!isErrorOrder && shpgNumList.size() > 0) {
                if (!callStsUpdApi(hdrWrk.cpoOrdNum.getValue())) {
                    isErrorOrder = true;
                }
            }

            // Error.
            if (isErrorOrder) {
                S21InfoLogOutput.println(NWCM0058E, toArray(lockedCpo.cpoOrdNum.getValue()));
                errRecCnt++;
                rollback();
            // Success.
            } else {
                if (doCommit) {
                    normalRecCnt++;
                    commit();
                    processedKeys.addAll(pKeys);
                }
            }
            totalRecCnt++;
        }
        // QC#20343 Add End

        /**
         * execute revenue recognition process
         * @return
         */
        protected boolean doRevenueRecognition(REV_RECOG_HDR_WRKTMsg hdrWrk, List<REV_RECOG_DTL_WRKTMsg> dtlWrkList, List<REV_RECOG_DTL_WRKTMsg> divDtlWrkList) {
            final String methodNm = "doRevenueRecognition";
            debugMethodStart(getClass(), methodNm);

            try {
                Map<String, String> ssmParam;
                // get loan to sales target line category
                if (loanToSlsOrdLineCatgCdList == null) {
                    ssmParam = new HashMap<String, String>();
                    ssmParam.put("glblCmpyCd", glblCmpyCd);
                    ssmParam.put("convProcTpTxt", CONV_PROC_TP.LOAN_TO_SALES);
                    ssmParam.put("slsDt", slsDt);
                    loanToSlsOrdLineCatgCdList = (List<String>) ssmClient.queryObjectList("getLoanToSalesTargetKey", ssmParam);
                }

                // get create invoice target line category
                if (cratInvOrdLineCatgCdList == null) {
                    ssmParam = new HashMap<String, String>();
                    ssmParam.put("glblCmpyCd", glblCmpyCd);
                    ssmParam.put("invCratProcFlg", ZYPConstant.FLG_ON_Y);
                    ssmParam.put("slsDt", slsDt);
                    cratInvOrdLineCatgCdList = (List<String>) ssmClient.queryObjectList("getCreateInvoiceTargetKey", ssmParam);
                }

                // get internal order target order type
                if (itrlDsOrdTpCdList == null) {
                    ssmParam = new HashMap<String, String>();
                    ssmParam.put("glblCmpyCd", glblCmpyCd);
                    ssmParam.put("itrlOrdProcFlg", ZYPConstant.FLG_ON_Y);
                    ssmParam.put("slsDt", slsDt);
                    itrlDsOrdTpCdList = (List<String>) ssmClient.queryObjectList("getDsOrdTpForInternalOrder", ssmParam);
                }

                // get toner count target line category
                if (tonerCntOrdLineCatgList == null) {
                    ssmParam = new HashMap<String, String>();
                    ssmParam.put("glblCmpyCd", glblCmpyCd);
                    ssmParam.put("ordLineCtxTpCd", CONST_ORD_LINE_CTX_TP_CD_TONER_CNT_REQ);
                    tonerCntOrdLineCatgList = (List<String>) ssmClient.queryObjectList("getTonerCountTargetLineCategory", ssmParam);
                }

                // ----------------------------------------------------------------------
                // 1. Update Shipping Plan
                // ----------------------------------------------------------------------
                if (this.isExistShpgPln) {
                    if (!doProcess_UpdateShpgPln(divDtlWrkList)) {
                        return false;
                    }
                    if (divDtlWrkList.isEmpty()) {
                        return true;
                    }
                }

                // ----------------------------------------------------------------------
                // 6. Create Invoice
                // ----------------------------------------------------------------------
                if (!doProcess_CreateInvoice(hdrWrk, divDtlWrkList)) {
                    return false;
                }

                // ----------------------------------------------------------------------
                // 8. Dummy Stock In/Out For Credit/Debit Order
                // ----------------------------------------------------------------------
                if (!doProcess_CreateDummyStockInOutForCreditDebit(divDtlWrkList)) {
                    return false;
                }

                // ----------------------------------------------------------------------
                // 9. Stock Out From In-transit
                // (Waiting for Installation)
                // ----------------------------------------------------------------------
                if (!doProcess_CreateStockOutFromInTransit(divDtlWrkList)) {
                    return false;
                }

                // ----------------------------------------------------------------------
                // 10. Stock Out From Trial/Loan
                // ----------------------------------------------------------------------
                if (!doProcess_CreateStockOutForTrialLoanSales(hdrWrk, divDtlWrkList)) {
                    return false;
                }

                // ----------------------------------------------------------------------
                // 14. Close CPO
                // ----------------------------------------------------------------------
                doProcess_CloseCpo(hdrWrk, divDtlWrkList);

                // ----------------------------------------------------------------------
                // 15. Asset Staging Process
                // ----------------------------------------------------------------------
                if (!doProcess_AssetStagingProcess(divDtlWrkList)) {
                    return false;
                }

                // ----------------------------------------------------------------------
                // 16. Running Toner Count Update
                // ----------------------------------------------------------------------
                if (!doProcess_RunningTonerCountUpdate(divDtlWrkList)) {
                    return false;
                }
                return true;

            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        /**
         * 16. Running Toner Count Update
         */
        boolean doProcess_RunningTonerCountUpdate(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {
            final String methodNm = "16. doProcess_RunningTonerCountUpdate";
            debugMethodStart(getClass(), methodNm);

            try {
                NWZC056001PMsg pMsg = new NWZC056001PMsg();
                setValue(pMsg.glblCmpyCd, glblCmpyCd);
                setValue(pMsg.slsDt,      slsDt);
                int idx = 0;
                for (int i = 0; i < dtlWrkList.size(); i++) {
                    REV_RECOG_DTL_WRKTMsg dtlWrk = dtlWrkList.get(i);
                    SHPG_PLNTMsg shpgPlnTMsg     = lockedShpgPlnList.get(i);
                    CPO_DTLTMsg cpoDtlTMsg       = getCpoDtl(dtlWrk);

                    // exclude credit&rebill
                    if (hasValue(cpoDtlTMsg.crRebilCd)) {
                        continue;
                    }
                    // exclude internal order
                    if (itrlDsOrdTpCdList.contains(lockedCpo.dsOrdTpCd.getValue())) {
                        continue;
                    }
                    // exclude dummy wh
                    if (crRebillDummyWhCdList.contains(cpoDtlTMsg.rtlWhCd.getValue())) {
                        continue;
                    }

                    // judge line category
                    if (!tonerCntOrdLineCatgList.contains(cpoDtlTMsg.dsOrdLineCatgCd.getValue())) {
                        continue;
                    }

                    // judge imgSplyTpCd
                    String mdseCd = shpgPlnTMsg.mdseCd.getValue();
                    //QC#25966 del Start
//                    MDSETMsg dsMdseInfoTMsg = getMdse(mdseCd);
//                    if (!IMG_SPLY_TP.TONER.equals(dsMdseInfoTMsg.imgSplyTpCd.getValue())) {
//                        continue;
//                    }
                    //QC#25966 del End

                    if (!hasValue(cpoDtlTMsg.dsContrNum)) {
                        continue;
                    }

                    // START 2018/09/25 W.Honda [QC#28436,ADD]
                    // exclude MISC Item
                    if (!isExistsSplyReln(glblCmpyCd, mdseCd, cpoDtlTMsg.svcMachMstrPk.getValue())) {
                        continue;
                    }
                    // END 2018/09/25 W.Honda [QC#28436,ADD]

                    NWZC056001_runningCountUpdateListPMsg listPMsg = pMsg.runningCountUpdateList.no(idx++);
                    setValue(listPMsg.dsContrNum, cpoDtlTMsg.dsContrNum.getValue());
                    setValue(listPMsg.svcMachMstrPk, cpoDtlTMsg.svcMachMstrPk.getValue());
                    setValue(listPMsg.mdseCd, mdseCd);
                    setValue(listPMsg.xxRqstQty, shpgPlnTMsg.ordQty.getValue());

                    pMsg.runningCountUpdateList.setValidCount(idx);
                }

                if (pMsg.runningCountUpdateList.getValidCount() == 0) {
                    return true;
                }

                NWZC056001 runningTonerCountUpdateAPI = new NWZC056001();
                runningTonerCountUpdateAPI.execute(pMsg, ONBATCH_TYPE.BATCH);

                if (pMsg.xxMsgIdList.getValidCount() > 0) {
                    for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                        S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    }
                    hasErr = true;
                    return false;
                }

                return true;
            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        /**
         * 15. Asset Staging Process
         */
        private boolean doProcess_AssetStagingProcess(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {
            final String methodNm = "15. doProcess_AssetProcess";
            debugMethodStart(getClass(), methodNm);

            try {
                // a) get Order Type Process Definition for Asset Update
                String assetNodePrflCd = getAssetNodePrflCd(lockedCpo.dsOrdTpCd.getValue());
                if (!hasValue(assetNodePrflCd)) {
                    return true;
                }

                boolean isServiceExchange = judgeServiceExchange();
                String assetTpCd =  getAssetTpCd(assetNodePrflCd);

                List<NLZC309001PMsg> pMsgList = new ArrayList<NLZC309001PMsg>();
                for (int i = 0; i < dtlWrkList.size(); i++) {
                    REV_RECOG_DTL_WRKTMsg dtlWrk = dtlWrkList.get(i);
                    SHPG_PLNTMsg shpgPlnTMsg = lockedShpgPlnList.get(i);
                    CPO_DTLTMsg cpoDtlTMsg = getCpoDtl(dtlWrk);

                    // exclude credit&rebill
                    if (hasValue(cpoDtlTMsg.crRebilCd)) {
                        continue;
                    }
                    // exclude internal order
                    if (itrlDsOrdTpCdList.contains(lockedCpo.dsOrdTpCd.getValue())) {
                        continue;
                    }

                    // exclude not install base
                    String mdseCd = shpgPlnTMsg.mdseCd.getValue();
                    MDSETMsg dsMdseInfoTMsg = getMdse(mdseCd);
                    if (ZYPConstant.FLG_OFF_N.equals(dsMdseInfoTMsg.instlBaseCtrlFlg.getValue())) {
                        continue;
                    }

                    // b) judge asset target mdse
                    if (!isAssetTargetMdse(dsMdseInfoTMsg.coaMdseTpCd.getValue())) {
                        continue;
                    }

                    // 2018/03/05 S21_NA#20153 Add Start
                    // exclude loan conversion order
                    if (hasValue(cpoDtlTMsg.convProcStsCd)) {
                        continue;
                    }
                    // 2018/03/05 S21_NA#20153 Add End

                    // START 2018/07/27 E.Kameishi [QC#26987, ADD]
                    if (ZYPConstant.FLG_OFF_N.equals(dsMdseInfoTMsg.invtyCtrlFlg.getValue())) {
                        continue;
                    }
                    // END 2018/07/27 E.Kameishi [QC#26987, ADD]

                    // API parameter set
                    NLZC309001PMsg pMsg = new NLZC309001PMsg();
                    setValue(pMsg.glblCmpyCd,          glblCmpyCd);
                    setValue(pMsg.slsDt,               slsDt);
                    setValue(pMsg.shpgPlnNum,          shpgPlnTMsg.shpgPlnNum.getValue());
                    setValue(pMsg.toSvcConfigMstrPk,   cpoDtlTMsg.svcConfigMstrPk.getValue());
                    setValue(pMsg.cpoOrdNum,           cpoDtlTMsg.cpoOrdNum.getValue());
                    setValue(pMsg.cpoDtlLineNum,       cpoDtlTMsg.cpoDtlLineNum.getValue());
                    setValue(pMsg.cpoDtlLineSubNum,    cpoDtlTMsg.cpoDtlLineSubNum.getValue());
                    setValue(pMsg.sellToCustCd,        lockedCpo.sellToCustCd.getValue());
                    setValue(pMsg.soldToCustLocCd,     lockedCpoConfig.billToCustLocCd.getValue());
                    setValue(pMsg.shipToCustCd,        lockedCpoConfig.shipToCustLocCd.getValue());
                    setValue(pMsg.shipToCustAcctCd,    lockedCpoConfig.shipToCustAcctCd.getValue());
                    setValue(pMsg.dsOrdPosnNum,        cpoDtlTMsg.dsOrdPosnNum.getValue());
                    //get invoice date
                    INVTMsg invTMsg = new INVTMsg();
                    setValue(invTMsg.glblCmpyCd, glblCmpyCd);
                    setValue(invTMsg.invNum,     shpgPlnTMsg.invNum.getValue());

                    invTMsg = (INVTMsg) S21CacheTBLAccessor.findByKey(invTMsg);
                    if (invTMsg != null) {
                        setValue(pMsg.invDt,     invTMsg.invDt.getValue());
                    }
                    setValue(pMsg.invNum, shpgPlnTMsg.invNum.getValue());

                    String slsRepTocCd = shpgPlnTMsg.slsRepTocCd.getValue();
                    if (ASSET_TP.RENTAL_ASSET.equals(assetTpCd)) {
                        setValue(pMsg.procModeCd,      NLZC309001Constant.PROC_MODE_RENTAL_SHIP);
                    } else if (ASSET_TP.EMSD_ASSET.equals(assetTpCd)) {
                        setValue(pMsg.procModeCd,      NLZC309001Constant.PROC_MODE_EMSD_SHIP);
                        slsRepTocCd = getSlsRepForEMSD(cpoDtlTMsg);
                    }
                    setValue(pMsg.slsRepTocCd, slsRepTocCd);

                    if (isServiceExchange) {
                        setValue(pMsg.procModeCd,      NLZC309001Constant.PROC_MODE_SERVICE_EXCHANGE);
                        setValue(pMsg.serNum,          getSerNum(dtlWrk, shpgPlnTMsg));
                        // START 2018/02/21 J.Kim [QC#23431,ADD]
                        setValue(pMsg.mdseCd,          shpgPlnTMsg.mdseCd.getValue());
                        // END 2018/02/21 J.Kim [QC#23431,ADD]

                    } else {
                        setValue(pMsg.mdseCd,          shpgPlnTMsg.mdseCd.getValue());
                        setValue(pMsg.baseCmptFlg,     cpoDtlTMsg.baseCmptFlg.getValue());
                    }
                    pMsgList.add(pMsg);
                }

                if (pMsgList.isEmpty()) {
                    return true;
                }

                NLZC309001 api = new NLZC309001();
                api.execute(pMsgList, ONBATCH_TYPE.BATCH);

                for (NLZC309001PMsg pMsg : pMsgList) {
                    if (pMsg.xxMsgIdList.getValidCount() > 0) {
                        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                            S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                        }
                        hasErr = true;
                        return false;
                    }
                }

                return true;
            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        private String getSlsRepForEMSD(CPO_DTLTMsg cpoDtlTMsg) {

            String cpoOrdNum      = cpoDtlTMsg.cpoOrdNum.getValue();
            String dsCpoConfigPk  = cpoDtlTMsg.dsCpoConfigPk.getValue().toString();

            String slsRepRoleTpCd = LINE_BIZ_ROLE_TP.EMSD;

            String key = createCacheKey("getSlsRepForEMSD", cpoOrdNum, dsCpoConfigPk, slsRepRoleTpCd);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd",     glblCmpyCd);
            ssmParam.put("cpoOrdNum",      cpoOrdNum);
            ssmParam.put("dsCpoConfigPk",  dsCpoConfigPk);
            ssmParam.put("slsRepRoleTpCd", slsRepRoleTpCd);

            String slsRepTocCd = (String) searchObjectFirstValue("getSlsRepForEMSD", ssmParam, key);

            return slsRepTocCd;
        }

        private String getAssetTpCd(String assetNodePrflCd) {

            String assetTpCd = null;

            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("ordProcNodePrflCd",  assetNodePrflCd);
            assetTpCd = (String) ssmClient.queryObject("getAssetTpCd", ssmParam);

            return assetTpCd;
        }

        private String getSerNum (REV_RECOG_DTL_WRKTMsg dtlWrkTMsg, SHPG_PLNTMsg shpgPlnTMsg) {
            String serNum = null;

            // get serial number from SHIP_SER_NUM
            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("mdseCd",     shpgPlnTMsg.mdseCd.getValue());
            ssmParam.put("soNum",      shpgPlnTMsg.soNum.getValue());
            ssmParam.put("soSlpNum",   shpgPlnTMsg.soSlpNum.getValue());
            serNum = (String) ssmClient.queryObject("querySHIP_SER_NUM", ssmParam);

            if (serNum == null) {
                // get serial number from PO_SER_NUM
                ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd",  glblCmpyCd);
                ssmParam.put("keyInfoCd01", dtlWrkTMsg.cpoOrdNum.getValue());
                ssmParam.put("keyInfoCd02", dtlWrkTMsg.cpoDtlLineNum.getValue());
                ssmParam.put("keyInfoCd03", dtlWrkTMsg.cpoDtlLineSubNum.getValue());
                serNum = (String) ssmClient.queryObject("queryPO_SER_NUM", ssmParam);
            }

            return serNum;
        }
        private boolean judgeServiceExchange() {

            boolean ret = false;
            String dsOrdTpCd   = lockedCpo.dsOrdTpCd.getValue();
            String dsOrdCatgCd = lockedCpo.dsOrdCatgCd.getValue();
            String dsOrdRsnCd  = lockedCpo.dsOrdRsnCd.getValue();

            String key = createCacheKey("countOrdCatgBizCtxForServiceExchange", dsOrdTpCd, dsOrdCatgCd, dsOrdRsnCd);

            Map<String, Object > ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd",     glblCmpyCd);
            ssmParam.put("ordCatgCtxTpCd", CONST_ORD_CATG_CTX_TP_CD_SERVICE_EXCHANGE);
            ssmParam.put("dsOrdTpCd",      dsOrdTpCd);
            ssmParam.put("dsOrdCatgCd",    dsOrdCatgCd);
            ssmParam.put("dsOrdRsnCd",     dsOrdRsnCd);

            Integer cnt = (Integer) searchObject("countOrdCatgBizCtxForServiceExchange", ssmParam, key);

            if (cnt > 0) {
                ret = true;
            }

            return ret;
        }
        // START 2018/09/25 W.Honda [QC#28436,ADD]
        /**
         * isExistsSplyReln
         * @param glblCmpyCd String
         * @param mdseCd String
         * @return boolean
         */
        private boolean isExistsSplyReln(String glblCmpyCd, String mdseCd, BigDecimal svcMschMstrPk) {
            Map<String, Object> mdseSsmPrm = new HashMap<String, Object>();
            mdseSsmPrm.put("glblCmpyCd", glblCmpyCd);
            mdseSsmPrm.put("svcMschMstrPk", svcMschMstrPk);
            mdseSsmPrm.put("mdseCd", mdseCd);

            BigDecimal count = (BigDecimal) ssmClient.queryObject("isExistsSplyReln", mdseSsmPrm);
            if (count.compareTo(BigDecimal.ZERO) > 0) {
                return true;
            }

            Map<String, Object> ordTakeMdseSsmPrm = new HashMap<String, Object>();
            ordTakeMdseSsmPrm.put("glblCmpyCd", glblCmpyCd);
            ordTakeMdseSsmPrm.put("svcMschMstrPk", svcMschMstrPk);
            String ordTakeMdseCd = mdseCd;
            if (mdseCd.length() > 8) {
                ordTakeMdseCd = mdseCd.substring(0, 8);
            }
            ordTakeMdseSsmPrm.put("ordTakeMdseCd", ordTakeMdseCd);
            count = (BigDecimal) ssmClient.queryObject("isExistsSplyRelnForOrdTakeMdse", ordTakeMdseSsmPrm);
            if (count.compareTo(BigDecimal.ZERO) > 0) {
                return true;
            }
            return false;
        }
        // END 2018/09/25 W.Honda [QC#28436,ADD]

        private boolean isAssetTargetMdse(String coaMdseTpCd) {

            boolean ret = false;

            if (coaMdseTpCdList == null) {
                Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd",     glblCmpyCd);
                ssmParam.put("mdseTpCtxTpCd", CONST_MDSE_TP_CTX_TP_ASSET_CRAT_TRGT);
                coaMdseTpCdList = (List<String>) ssmClient.queryObjectList("getCoaMdseTpCd", ssmParam);
            }

            if (coaMdseTpCdList.contains(coaMdseTpCd)) {
                ret = true;
            }

            return ret;
        }

        private MDSETMsg getMdse(String mdseCd) {

            MDSETMsg dsMdseInfoTMsg = new MDSETMsg();
            setValue(dsMdseInfoTMsg.glblCmpyCd, glblCmpyCd);
            setValue(dsMdseInfoTMsg.mdseCd,     mdseCd);

            return (MDSETMsg) S21CacheTBLAccessor.findByKey(dsMdseInfoTMsg);
        }

        private String getAssetNodePrflCd(String dsOrdTpCd) {

            String assetNodePrflCd = null;

            if (dsOrdTpProcDfnTMsgList == null) {
                Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd",      glblCmpyCd);
                ssmParam.put("assetNodeUseFlg", ZYPConstant.FLG_ON_Y);
                dsOrdTpProcDfnTMsgList = (List<DS_ORD_TP_PROC_DFNTMsg>) ssmClient.queryObjectList("getOrdTpProcDfnForAssetUpdate", ssmParam);
            }

            for (DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg : dsOrdTpProcDfnTMsgList) {
                if (dsOrdTpProcDfnTMsg.dsOrdTpCd.getValue().equals(dsOrdTpCd)) {
                    assetNodePrflCd = dsOrdTpProcDfnTMsg.assetNodePrflCd.getValue();
                    break;
                }
            }

            return assetNodePrflCd;
        }

        /**
         * 14.Close CPO
         */
        boolean doProcess_CloseCpo(REV_RECOG_HDR_WRKTMsg hdrWrk, List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {
            final String methodNm = "14. doProcess_CloseCpo";
            debugMethodStart(getClass(), methodNm);

            try {

                boolean cpoUpdateFlg = false;

                // Close Shipping Plan
                if (this.isExistShpgPln) {
                    closeShpgPln(hdrWrk, dtlWrkList);
                }

                // Close CPO Detail
                cpoUpdateFlg = closeCpoDtl(dtlWrkList);

                if (cpoUpdateFlg) {
                    closeCpo();
                }

                return true;

            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        /**
         * 8.Dummy Stock In/Out For Credit/Debit Order
         */
        boolean doProcess_CreateDummyStockInOutForCreditDebit(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {
            final String methodNm = "8. doProcess_CreateDummyStockInOutForCreditDebit";
            debugMethodStart(getClass(), methodNm);

            try {

                for (int i = 0; i < dtlWrkList.size(); i++) {
                    REV_RECOG_DTL_WRKTMsg dtlWrk = dtlWrkList.get(i);

                    // this process is credit rebill line only
                    String crRebilCd = dtlWrk.crRebilCd.getValue();
                    
                    if (!CR_REBIL.CREDIT.equals(crRebilCd)){
                        continue;
                    }
                    // 2022/03/24 QC#59825 Add Start 
                    if (isCanceledReBillPairOrder(dtlWrk.cpoOrdNum.getValue())){
                        continue;
                    }
                    // 2022/03/24 QC#59825 Add End 

                    if (N.equals(dtlWrk.invtyCtrlFlg.getValue())) {
                        continue;
                    }

                    // skip for exclude data
                    if (N.equals(dtlWrk.revRecogFlg.getValue())) {
                        continue;
                    }
                    final CPO_DTLTMsg cpoDtl = getCpoDtl(dtlWrk);

                    if (isSetParentLineSubNum(cpoDtl.cpoDtlLineSubNum.getValue())) {
                        continue;
                    }

                    // QC#22969 Mod Start
                    if (crRebillDummyWhCdList.contains(cpoDtl.origInvtyLocCd.getValue())) {
                        continue;
                    }
                    // QC#22969 Mod End

                    SHPG_PLNTMsg shpgPln    = lockedShpgPlnList.get(i);

                    // 2018/05/24 QC#22097 Mod Start
                    // get Cost Amount from Original Invoice
//                    BigDecimal origInvCostAmt = getCostAmountFromOriginalInvoice(cpoDtl, dtlWrk);
//                    if (origInvCostAmt == null) {
//                        return false;
//                    }
                    BigDecimal shipCostAmt = getCostAmountFromCostUpdateHistory(cpoDtl, dtlWrk);
                    if (shipCostAmt == null) {
                        return false;
                    }
                    // 2018/05/24 QC#22097 Mod End

                    // --------------------------------------------------
                    // NLZC002001 - Inventory Update API
                    // --------------------------------------------------
                    final NLZC002001PMsg pMsg = new NLZC002001PMsg();
                    //if (CR_REBIL.CREDIT.equals(crRebilCd)) {
                        setValue(pMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
                    //} else {
                    //    setValue(pMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
                    //}
                    setValue(pMsg.glblCmpyCd,       glblCmpyCd);

                    //L3#430 QC#16437 MOD START
                    setValue(pMsg.trxCd,            shpgPln.trxCd.getValue());
                    setValue(pMsg.trxRsnCd,         shpgPln.trxRsnCd.getValue());
                    //L3#430 QC#16437 MOD END

                    setValue(pMsg.mdseCd,           cpoDtl.mdseCd);
                    setValue(pMsg.invtyLocCd,       shpgPln.invtyLocCd.getValue());
                    setValue(pMsg.locStsCd,         LOC_STS.DC_STOCK);
                    setValue(pMsg.stkStsCd,         STK_STS.GOOD);
                    setValue(pMsg.xxRqstQty,        cpoDtl.ordQty);

                    if (CR_REBIL.CREDIT.equals(crRebilCd)) {
                        setValue(pMsg.xxRqstQty,    cpoDtl.ordQty.getValue().abs());
                    }

                    setValue(pMsg.invtyTrxDt,       slsDt);
                    setValue(pMsg.xxSysTp,          NLZC002001.SYS_TP_OM);
                    setValue(pMsg.sysSrcCd,         lockedCpo.sysSrcCd);
                    setValue(pMsg.cpoOrdNum,        cpoDtl.cpoOrdNum);
                    setValue(pMsg.cpoDtlLineNum,    cpoDtl.cpoDtlLineNum);
                    setValue(pMsg.cpoDtlLineSubNum, cpoDtl.cpoDtlLineSubNum);
                    setValue(pMsg.sellToCustCd,     lockedCpo.sellToCustCd);
                    setValue(pMsg.billToCustCd,     lockedCpo.billToCustCd);
                    setValue(pMsg.shipToCustCd,     cpoDtl.shipToCustCd);
                    setValue(pMsg.shipToCustNm,     cpoDtl.shipToLocNm);
                    setValue(pMsg.expProjCd,        cpoDtl.coaProjCd);
                    setValue(pMsg.invNum,           cpoDtl.invNum);
                    // QC#21566 mod Start
//                    setValue(pMsg.shipCostAmt,      origInvCostAmt);
                    // 2018/05/24 QC#22097 Mod Start
//                    setValue(pMsg.shipCostAmt,      origInvCostAmt.multiply(cpoDtl.ordQty.getValue()));
                    setValue(pMsg.shipCostAmt,      shipCostAmt.multiply(cpoDtl.ordQty.getValue()));
                    // 2018/05/24 QC#22097 Mod End
                    // QC#21566 mod End

                    if (!callInventoryUpdateAPI(pMsg)) {
                        return false;
                    }
                }

                return true;

            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        /**
         * 6.CreateInvoice
         */
        boolean doProcess_CreateInvoice(REV_RECOG_HDR_WRKTMsg hdrWrk, List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {
            final String methodNm = "6. doProcess_CreateInvoice";
            debugMethodStart(getClass(), methodNm);

            try {

                // call invoice API
                final List<NWZC035001PMsg> pMsgList = new ArrayList<NWZC035001PMsg>();

                for (int i = 0; i < dtlWrkList.size(); i++) {

                    final REV_RECOG_DTL_WRKTMsg dtlWrk = dtlWrkList.get(i);

                    boolean isRevenuRecognitionProcessed = Y.equals(dtlWrk.revRecogFlg.getValue());
                    if (!isRevenuRecognitionProcessed) {
                        continue;
                    }

                    // exclude not create invoice line category
                    String incCratKey = createCacheKey(hdrWrk.dsOrdTpCd.getValue(), dtlWrk.dsOrdLineCatgCd.getValue());
                    if (!cratInvOrdLineCatgCdList.contains(incCratKey)) {
                        continue;
                    }

                    final CPO_DTLTMsg cpoDtl = getCpoDtl(dtlWrk);
                    String cpoDtlLineNum    = cpoDtl.cpoDtlLineNum.getValue();
                    String cpoDtlLineSubNum = cpoDtl.cpoDtlLineSubNum.getValue();

                    final NWZC035001PMsg pMsg = new NWZC035001PMsg();
                    setValue(pMsg.glblCmpyCd,       glblCmpyCd);
                    setValue(pMsg.cpoOrdNum,        lockedCpo.cpoOrdNum);
                    setValue(pMsg.cpoDtlLineNum,    cpoDtlLineNum);
                    setValue(pMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
                    setValue(pMsg.cpoOrdTpCd,       cpoDtl.cpoOrdTpCd);
                    setValue(pMsg.slsDt,            slsDt);

                    if (this.isExistShpgPln) {
                        SHPG_PLNTMsg shpgPln = lockedShpgPlnList.get(i);

                        if (!crRebillDummyWhCdList.contains(cpoDtl.rtlWhCd.getValue())) {
                            if (SHPG_STS.VALIDATED.equals(shpgPln.shpgStsCd.getValue())) {
                                continue;
                            }
                        }
                        setValue(pMsg.shpgPlnNum, shpgPln.shpgPlnNum);
                    }
                    pMsgList.add(pMsg);
                }

                // no target data
                if (isEmpty(pMsgList)) {
                    return true;
                }

                // --------------------------------------------------
                // NWZC035001 - Invoice API
                // --------------------------------------------------
                if (!callInvoiceAPI(pMsgList)) {
                    return false;
                }

                final Set<String> invNumSet = new HashSet<String>();

                for (int i = 0; i < pMsgList.size(); i++) {

                    NWZC035001PMsg pMsg = pMsgList.get(i);

                    // invoice numberを退避
                    final String invNum = pMsg.invNum.getValue();
                    invNumSet.add(invNum);

                    for (int j = 0; j < dtlWrkList.size(); j++) {

                        REV_RECOG_DTL_WRKTMsg dtlWrk = dtlWrkList.get(j);

                        if (this.isExistShpgPln) {
                            SHPG_PLNTMsg shpgPln = lockedShpgPlnList.get(j);
                            if (shpgPln.shpgPlnNum.getValue().equals(pMsg.shpgPlnNum.getValue())) {
                                setValue(shpgPln.invNum, invNum);
                            }
                        }

                        CPO_DTLTMsg cpoDtl = getCpoDtl(dtlWrk);
                        if (cpoDtl.cpoDtlLineNum.getValue().equals(pMsg.cpoDtlLineNum.getValue()) && cpoDtl.cpoDtlLineSubNum.getValue().equals(pMsg.cpoDtlLineSubNum.getValue())) {
                            setValue(cpoDtl.invNum, invNum);
                        }
                    }
                    // START 2018/01/26 E.Kameishi [QC#22851,ADD]
                    INVTMsg invTmsg = new INVTMsg();
                    ZYPEZDItemValueSetter.setValue(invTmsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(invTmsg.invNum, invNum);
                    invTmsg = (INVTMsg) EZDTBLAccessor.findByKeyForUpdateWait(invTmsg);

                    if (invTmsg.invTotDealNetAmt.getValue().compareTo(BigDecimal.ZERO) < 0) {
                        ZYPEZDItemValueSetter.setValue(invTmsg.invTpCd, INV_TP.CREDIT_MEMO);

                        String dsInvTpCd = invTmsg.dsInvTpCd.getValue();
                        DS_INV_TPTMsg dsInvTp = new DS_INV_TPTMsg();
                        ZYPEZDItemValueSetter.setValue(dsInvTp.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(dsInvTp.dsInvTpCd, dsInvTpCd);
                        dsInvTp = (DS_INV_TPTMsg) EZDTBLAccessor.findByKey(dsInvTp);
                        ZYPEZDItemValueSetter.setValue(invTmsg.dsInvTpCd, dsInvTp.crDsInvTpCd);
                        EZDTBLAccessor.update(invTmsg);
                    }
                    // END 2018/01/26 E.Kameishi [QC#22851,ADD]
                }
                return true;

            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        /**
         * 10.Stock Out From Trial/Loan
         * (loan to sales)
         */
        boolean doProcess_CreateStockOutForTrialLoanSales(REV_RECOG_HDR_WRKTMsg hdrWrk, List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {
            final String methodNm = "10. doProcess_CreateStockOutForTrialLoanSales";
            debugMethodStart(getClass(), methodNm);
            try {

                for (int i = 0; i < dtlWrkList.size(); i++) {

                    REV_RECOG_DTL_WRKTMsg dtlWrk = dtlWrkList.get(i);

                    // 2018/08/03 QC#27285 Mod Start
                    if (CR_REBIL.CREDIT.equals(dtlWrk.crRebilCd.getValue())){
                        continue;
                    }
                    //if (hasValue(dtlWrk.crRebilCd)) {
                    //    continue;
                    //}
                    // 2018/08/03 QC#27285 Mod End

                    String loanToSalesTargetKey = createCacheKey(hdrWrk.dsOrdTpCd.getValue(), dtlWrk.dsOrdLineCatgCd.getValue());
                    if (!loanToSlsOrdLineCatgCdList.contains(loanToSalesTargetKey)) {
                        continue;
                    }

                    boolean isSetParentLine     = isSetParentLineSubNum(dtlWrk.cpoDtlLineSubNum.getValue());
                    boolean isIntangibleItem    = N.equals(dtlWrk.invtyCtrlFlg.getValue());
                    boolean isValueItem         = Y.equals(dtlWrk.invtyValFlg.getValue());
                    boolean isRevRecogProcessed = Y.equals(dtlWrk.revRecogFlg.getValue());

                    if ((isIntangibleItem && !isValueItem) || isSetParentLine || !isRevRecogProcessed) {
                        continue;
                    }

                    CPO_DTLTMsg cpoDtl = getCpoDtl(dtlWrk);

                    String cpoOrdNum        = lockedCpo.cpoOrdNum.getValue();
                    String cpoDtlLineNum    = cpoDtl.cpoDtlLineNum.getValue();
                    String cpoDtlLineSubNum = cpoDtl.cpoDtlLineSubNum.getValue();

                    SHPG_PLNTMsg shpgPlnTMsg = lockedShpgPlnList.get(i);

                    // --------------------------------------------------
                    // NLZC002001 - Inventory Update API
                    // --------------------------------------------------
                    final NLZC002001PMsg pMsg = new NLZC002001PMsg();

                    // 2018/08/06 QC#27285 Mod Start
                    if (CR_REBIL.REBILL.equals(cpoDtl.crRebilCd.getValue())) {
                        BigDecimal shipCostAmt = getCostAmountFromCostUpdateHistory(cpoDtl, dtlWrk);
                        if (shipCostAmt == null) {
                            return false;
                        }

                        setValue(pMsg.xxRqstTpCd,           NLZC002001.RQST_STOCK_OUT);
                        setValue(pMsg.glblCmpyCd,           glblCmpyCd);
                        setValue(pMsg.trxCd,                shpgPlnTMsg.trxCd.getValue());
                        setValue(pMsg.trxRsnCd,             shpgPlnTMsg.trxRsnCd.getValue());
                        setValue(pMsg.mdseCd,               cpoDtl.mdseCd);
                        setValue(pMsg.invtyLocCd,           shpgPlnTMsg.invtyLocCd.getValue());
                        setValue(pMsg.locStsCd,             LOC_STS.DC_STOCK);
                        setValue(pMsg.stkStsCd,             STK_STS.GOOD);
                        setValue(pMsg.xxRqstQty,            cpoDtl.ordQty);
                        setValue(pMsg.xxRqstQty,            cpoDtl.ordQty.getValue().abs());
                        setValue(pMsg.invtyTrxDt,           slsDt);
                        setValue(pMsg.xxSysTp,              NLZC002001.SYS_TP_OM);
                        setValue(pMsg.sysSrcCd,             lockedCpo.sysSrcCd);
                        setValue(pMsg.cpoOrdNum,            cpoDtl.cpoOrdNum);
                        setValue(pMsg.cpoDtlLineNum,        cpoDtl.cpoDtlLineNum);
                        setValue(pMsg.cpoDtlLineSubNum,     cpoDtl.cpoDtlLineSubNum);
                        setValue(pMsg.sellToCustCd,         lockedCpo.sellToCustCd);
                        setValue(pMsg.billToCustCd,         lockedCpo.billToCustCd);
                        setValue(pMsg.shipToCustCd,         cpoDtl.shipToCustCd);
                        setValue(pMsg.shipToCustNm,         cpoDtl.shipToLocNm);
                        setValue(pMsg.expProjCd,            cpoDtl.coaProjCd);
                        setValue(pMsg.invNum,               cpoDtl.invNum);
                        setValue(pMsg.shipCostAmt,          shipCostAmt.multiply(cpoDtl.ordQty.getValue()));
                        setValue(pMsg.tocCd,                cpoDtl.slsRepOrSlsTeamTocCd);
                        setValue(pMsg.ccyCd,                cpoDtl.ccyCd);
                        setValue(pMsg.uomCd,                cpoDtl.custUomCd);
                        getOrigLoan2SellData(pMsg);
                    } else {
                        setValue(pMsg.xxRqstTpCd,           NLZC002001.RQST_STOCK_OUT);
                        setValue(pMsg.glblCmpyCd,           glblCmpyCd);
                        setValue(pMsg.trxCd,                shpgPlnTMsg.trxCd);
                        setValue(pMsg.mdseCd,               shpgPlnTMsg.mdseCd);
                        setValue(pMsg.invtyLocCd,           shpgPlnTMsg.invtyLocCd);
                        setValue(pMsg.stkStsCd,             cpoDtl.stkStsCd);
                        setValue(pMsg.xxRqstQty,            cpoDtl.ordQty);
                        setValue(pMsg.invtyTrxDt,           slsDt);
                        setValue(pMsg.xxSysTp,              NLZC002001.SYS_TP_OM);
                        setValue(pMsg.sysSrcCd,             lockedCpo.sysSrcCd);
                        setValue(pMsg.cpoOrdNum,            cpoOrdNum);
                        setValue(pMsg.cpoDtlLineNum,        cpoDtlLineNum);
                        setValue(pMsg.cpoDtlLineSubNum,     cpoDtlLineSubNum);
                        setValue(pMsg.origCpoOrdNum,        cpoDtl.origCpoOrdNum);
                        setValue(pMsg.origCpoDtlLineNum,    cpoDtl.origCpoDtlLineNum);
                        setValue(pMsg.origCpoDtlLineSubNum, cpoDtl.origCpoDtlLineSubNum);
                        setValue(pMsg.sellToCustCd,         lockedCpo.sellToCustCd);
                        setValue(pMsg.billToCustCd,         lockedCpo.billToCustCd);
                        setValue(pMsg.shipToCustCd,         cpoDtl.shipToCustCd);
                        setValue(pMsg.shipToCustNm,         cpoDtl.shipToLocNm);
                        setValue(pMsg.tocCd,                cpoDtl.slsRepOrSlsTeamTocCd);
                        setValue(pMsg.ccyCd,                cpoDtl.ccyCd);
                        setValue(pMsg.uomCd,                cpoDtl.custUomCd);
                        setValue(pMsg.expProjCd,            cpoDtl.coaProjCd);
                        setValue(pMsg.invNum,               cpoDtl.invNum);
                        setValue(pMsg.trxRsnCd,             shpgPlnTMsg.trxRsnCd);
                        setValue(pMsg.locStsCd, LOC_STS.TRIAL_USE);
    
                        // get Cost Amount from Original Order
                        BigDecimal origCostAmt = getCostAmountFromOriginalOrder(cpoDtl, shpgPlnTMsg);
                        if (origCostAmt == null) {
                            return false;
                        }
                        setValue(pMsg.shipCostAmt,          origCostAmt);
                    }
                    // 2018/08/06 QC#27285 Mod End

                    if (!callInventoryUpdateAPI(pMsg)) {
                        return false;
                    }
                }

                return true;

            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        //2018/08/06 QC#27286 Add Start
        /**
         * getOrigData
         * @param pMsg
         */
        private void getOrigLoan2SellData(NLZC002001PMsg pMsg){

            final Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd",      glblCmpyCd);
            ssmParam.put("cpoOrdNum",       pMsg.cpoOrdNum.getValue());
            ssmParam.put("cpoDtlLineNum",       pMsg.cpoDtlLineNum.getValue());
            ssmParam.put("cpoDtlLineSubNum",       pMsg.cpoDtlLineSubNum.getValue());
            //2018/08/17 QC#27830 Del Start
            //ssmParam.put("cpoSrcTpCd",      CPO_SRC_TP.LOAN_WORKBENCH);
            //2018/08/17 QC#27830 Del End

            List<Map<String, String>> list = (List<Map<String, String>>) ssmClient.queryObjectList("getOrigData", ssmParam);

            if ((list != null) && (!list.isEmpty())){
                Map<String, String> dat = list.get(0);

                setValue(pMsg.origCpoOrdNum,        dat.get("ORIG_CPO_ORD_NUM"));
                setValue(pMsg.origCpoDtlLineNum,    dat.get("ORIG_CPO_DTL_LINE_NUM"));
                setValue(pMsg.origCpoDtlLineSubNum, dat.get("ORIG_CPO_DTL_LINE_SUB_NUM"));
            }
        }
        //2018/08/06 QC#27286 Add End

        /**
         * 9. Stock Out From In-transit
         */
        boolean doProcess_CreateStockOutFromInTransit(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {
            final String methodNm = "9. doProcess_CreateStockOutFromInTransit";
            debugMethodStart(getClass(), methodNm);

            try {

                for (int i = 0; i < dtlWrkList.size(); i++) {
    
                    REV_RECOG_DTL_WRKTMsg dtlWrk = dtlWrkList.get(i);

                    // exclude Credit&Rebill Data
                    if (CR_REBIL.CREDIT.equals(dtlWrk.crRebilCd.getValue())){
                        continue;
                    }
                    //if (hasValue(dtlWrk.crRebilCd)) {
                    //    continue;
                    //}

                    // 2019/07/22 QC#51848 Add Start
                    if (CPO_ORD_TP.TRIAL.equals(lockedCpo.cpoOrdTpCd.getValue())) {
                        continue;
                    }
                    // 2019/07/22 QC#51848 Add End

                    // exclude internal order
                    if (itrlDsOrdTpCdList.contains(lockedCpo.dsOrdTpCd.getValue())) {
                        continue;
                    }
                    // exclude credit rebill dummy wh cd
                    CPO_DTLTMsg dsCpoDtlTMsg  = getCpoDtl(dtlWrk);

                    // QC#22969 Mod Start
                    String whCd = dsCpoDtlTMsg.rtlWhCd.getValue();

                    if (CR_REBIL.REBILL.equals(dtlWrk.crRebilCd.getValue())){
                        whCd = dsCpoDtlTMsg.origInvtyLocCd.getValue();
                    }

                    if (crRebillDummyWhCdList.contains(whCd)) {
                        continue;
                    }

                    //if (crRebillDummyWhCdList.contains(dsCpoDtlTMsg.rtlWhCd.getValue())) {
                    //    if (!CR_REBIL.REBILL.equals(dtlWrk.crRebilCd.getValue())){
                    //        continue;
                    //    }
                    //}
                    // QC#22969 Mod End
                    
                    String lineSubNum = dtlWrk.cpoDtlLineSubNum.getValue();
                    String invCtrlFlg = dtlWrk.invtyCtrlFlg.getValue();

                    boolean isSetParentLine     = isSetParentLineSubNum(lineSubNum);
                    boolean isIntangibleItem    = N.equals(invCtrlFlg);
                    boolean isRevRecogProcessed = Y.equals(dtlWrk.revRecogFlg.getValue());
                    boolean isValueControlItem  = Y.equals(dtlWrk.invtyValFlg.getValue());

                    if ((isIntangibleItem && !isValueControlItem) || isSetParentLine || !isRevRecogProcessed) {
                        continue;
                    }
                    CPO_DTLTMsg  cpoDtl  = getCpoDtl(dtlWrk);
                    SHPG_PLNTMsg shpgPln = lockedShpgPlnList.get(i);

                    String soNum    = shpgPln.soNum.getValue();

                    // get so_num,so_slp_num
                    SHPG_ORDTMsg shpgOrd = null;
                    if (!isIntangibleItem) {
                        if (!hasValue(soNum)) {

                            // search SO_DTL by shpgPlnNum
                            Map<String, String> ssmParam = new HashMap<String, String>();
                            ssmParam.put("glblCmpyCd", glblCmpyCd);
                            ssmParam.put("shpgPlnNum", shpgPln.shpgPlnNum.getValue());

                            SHPG_ORD_DTLTMsg shpgOrdDtl = (SHPG_ORD_DTLTMsg) ssmClient.queryObject("getShpgOrdDtl", ssmParam);

                            if (shpgOrdDtl != null) {
                                setValue(shpgPln.soNum, shpgOrdDtl.soNum);
                                setValue(shpgPln.soSlpNum, shpgOrdDtl.soSlpNum);
                            }
                        }

                        // search SHPG_ORD by soNum
                        String key = createCacheKey(glblCmpyCd, soNum);
                        shpgOrd = (SHPG_ORDTMsg) shpgOrdCache.get(key);
                        if (shpgOrd == null) {
                            shpgOrd = new SHPG_ORDTMsg();
                            setValue(shpgOrd.glblCmpyCd, glblCmpyCd);
                            setValue(shpgOrd.soNum,      soNum);
                            shpgOrd = (SHPG_ORDTMsg) findByKey(shpgOrd);
                            if (shpgOrd != null) {
                                shpgOrdCache.put(key, shpgOrd);
                            }
                        }
                    }
                    // 2018/07/20 QC#26836 Add Start
                    BigDecimal unitCostAmt = null;
                    if (CR_REBIL.REBILL.equals(dtlWrk.crRebilCd.getValue())) {
                        unitCostAmt = getCostAmountFromCostUpdateHistory(cpoDtl, dtlWrk);
                        if (unitCostAmt == null) {
                            return false;
                        }
                    }
                    // 2018/07/20 QC#26836 Add End
                    if (invtyUpdObj == null) {
                        invtyUpdObj = new NWCB001001_InventoryUpdate(ssmClient);
                    }
                    // 2018/07/20 QC#26836 Mod Start
//                    if (!invtyUpdObj.callInvtyUpdApi(glblCmpyCd, slsDt, api.inventoryUpdateAPI, lockedCpo, cpoDtl, shpgPln, shpgOrd, dtlWrk)) {
                    if (!invtyUpdObj.callInvtyUpdApi(glblCmpyCd, slsDt, api.inventoryUpdateAPI, lockedCpo, cpoDtl, shpgPln, shpgOrd, dtlWrk, unitCostAmt)) {
                    // 2018/07/20 QC#26836 Mod End
                        hasErr = true; // 2019/05/14 QC#50142
                        return false;
                    }

                }

                return true;

            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        /**
         * 1.Update Shipping Plan
         */
        boolean doProcess_UpdateShpgPln(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {
            final String methodNm = "1. doProcess_UpdateShpgPln";
            debugMethodStart(getClass(), methodNm);

            try {

                for (int i = 0; i < dtlWrkList.size(); i++) {

                    REV_RECOG_DTL_WRKTMsg dtlWrk  = dtlWrkList.get(i);
                    CPO_DTLTMsg           cpoDtl  = getCpoDtl(dtlWrk);
                    SHPG_PLNTMsg shpgPln          = lockedShpgPlnList.get(i);

                    String lineSubNum = shpgPln.trxLineSubNum.getValue();
                    boolean isSetParentLine = isSetParentLineSubNum(lineSubNum);

                    if (isSetParentLine) {

                        // Set Item
                        String prntMdseCd = cpoDtl.mdseCd.getValue();

                        // Revenue recognition data obtained
                        // previously
                        final Map<String, String> ssmParam = new HashMap<String, String>();
                        ssmParam.put("glblCmpyCd",         glblCmpyCd);
                        ssmParam.put("trxSrcTpWholeSales", WHOLE_SALES);
                        ssmParam.put("cpoOrdNum",          lockedCpo.cpoOrdNum.getValue());
                        ssmParam.put("cpoDtlLineNum",      cpoDtl.cpoDtlLineNum.getValue());
                        ssmParam.put("setMdseCd",          prntMdseCd);
                        ssmParam.put("setShpgPlnNum",      shpgPln.shpgPlnNum.getValue());

                        List<SHPG_PLNTMsg> prcdShpgPlnList = (List<SHPG_PLNTMsg>) ssmClient.queryObjectList("queryProcessedData", ssmParam);

                        List<SetItemData> setItemDataList = getSetItemInfo(dtlWrkList, prntMdseCd, shpgPln, prcdShpgPlnList, cpoDtl.ordQty.getValue());
                        if (setItemDataList == null) {
                            return false;
                        } else if (setItemDataList.isEmpty()) {
                            return true;
                        }
    
                        boolean containTngblFlg   = false;
                        boolean containIntngblFlg = false;
                        for (int j = 1; j < setItemDataList.size(); j++) {
                            if (Y.equals(setItemDataList.get(j).getInvtyCtrlFlg())) {
                                containTngblFlg = true;
                            } else {
                                containIntngblFlg = true;
                            }
                        }
                        if (SHPG_STS.SHIPPED.equals(shpgPln.shpgStsCd.getValue()) //
                         || SHPG_STS.ARRIVED.equals(shpgPln.shpgStsCd.getValue()) //
                         || SHPG_STS.INSTALLED.equals(shpgPln.shpgStsCd.getValue())) {
                            setShpgStsCd(dtlWrk,  shpgPln);
                            if (!updateShpgPln(dtlWrk, shpgPln)) {
                                return false;
                            }
                        }

                        if (containTngblFlg) {
                            // tangible only or tangible and intangible mix Index component to process only one forward
                            i++;

                            while (i < dtlWrkList.size()) {

                                SHPG_PLNTMsg cmpSpTmsg = lockedShpgPlnList.get(i);
                                if (!hasValue(cmpSpTmsg.setMdseCd.getValue())) {
                                    // loop end when next line Positioned component index for the last one back to one
                                    i--;
                                    break;
                                }

                                REV_RECOG_DTL_WRKTMsg tmsg = dtlWrkList.get(i);
                                if (containIntngblFlg && isIntangibleItem(tmsg)) {
                                    //if (updateStatusIntangibleItem(tmsg, setItemDataList, cmpSpTmsg, dtlWrkList)) { // S21_NA#22925 MOD
                                    if (!updateStatusIntangibleItem(tmsg, setItemDataList, cmpSpTmsg, dtlWrkList)) {
                                        return false;
                                    }
                                }
                                i++;
                            }

                        } else if (!containTngblFlg && containIntngblFlg) {

                            // Intangible only set item update parent
                            setShpgStsCd(dtlWrk, shpgPln);
                            if (!updateShpgPln(dtlWrk, shpgPln)) {
                                return false;
                            }
                            i++;

                            // update compornent
                            while (i < dtlWrkList.size()) {

                                SHPG_PLNTMsg intgblSetSpTMsg = lockedShpgPlnList.get(i);
                                if (!hasValue(intgblSetSpTMsg.setMdseCd.getValue())) {
                                    // loop end when next set item Positioned component index for the last one back to one
                                    i--;
                                    break;
                                }

                                REV_RECOG_DTL_WRKTMsg tmsg = dtlWrkList.get(i);
                                setShpgStsCd(tmsg, intgblSetSpTMsg);
                                if (!updateShpgPln(dtlWrk, shpgPln)) {
                                    return false;
                                }
                                i++;
                            }
                        }
                    }
                }

                return true;

            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        private boolean callInventoryUpdateAPI(NLZC002001PMsg pMsg) {

            // --------------------------------------------------
            // NLZC002001 - Inventory Update API
            // --------------------------------------------------
            this.api.inventoryUpdateAPI.execute(pMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);

            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                    S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    hasErr = true;
                }
                return false;
            }

            return true;
        }

        private boolean callInvoiceAPI(List<NWZC035001PMsg> pMsgList) {

            // --------------------------------------------------
            // NWZC035001 - Invoice API
            // --------------------------------------------------
            this.api.invoiceAPI.execute(pMsgList, S21ApiInterface.ONBATCH_TYPE.BATCH);

            for (NWZC035001PMsg pMsg : pMsgList) {
                if (pMsg.xxMsgIdList.getValidCount() > 0) {
                    for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                        S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                        hasErr = true;
                    }
                    return false;
                }
            }

            return true;
        }

        private void closeCpo() {

            // CPO update judgement
            final Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd",            glblCmpyCd);
            ssmParam.put("cpoOrdNum",             lockedCpo.cpoOrdNum.getValue());
            ssmParam.put("dsCpoConfigPk",         lockedCpoConfig.dsCpoConfigPk.getValue().toString());
            ssmParam.put("ordLineStsCdClosed",    ORD_LINE_STS.CLOSED);
            ssmParam.put("ordLineStsCdCancelled", ORD_LINE_STS.CANCELLED);
            Integer count = (Integer) ssmClient.queryObject("queryCpoUpdateJudgement", ssmParam);
            count += (Integer) ssmClient.queryObject("queryCpoUpdateJudgementByReturnDetail", ssmParam);

            if (count == 0) {

                // UPDATE CPO
                setValue(lockedCpo.ordHdrStsCd, ORD_HDR_STS.CLOSED);
                setValue(lockedCpo.openFlg,     N);

                update(lockedCpo);

                if (!RTNCD_NORMAL.equals(lockedCpo.getReturnCode())) {
                    throw new S21AbendException(NWCM0053E, toArray(lockedCpo.cpoOrdNum.getValue()));
                }

                // Process Log Output
                S21BusinessProcessLogMsg bizProcLogMsg = new S21BusinessProcessLogMsg();
                bizProcLogMsg.setSubSysId(SUB_SYSTEM_ID);
                bizProcLogMsg.setProcId(PROCESS_ID);
                bizProcLogMsg.setEventId("Close");
                bizProcLogMsg.setDocTpCd(PROCESS_ID);
                bizProcLogMsg.setDocId("");
                bizProcLogMsg.setPrntDocId(lockedCpo.cpoOrdNum.getValue());
                S21BusinessProcessLog.print(bizProcLogMsg);
            }
        }

        private boolean closeCpoDtl(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {

            boolean cpoUpdateFlg    = false;
            boolean cpoDtlUpdateFlg = false;

            String prevLineNum = null;
            String prevLineSubNum = null;

            for (int i = 0; i < dtlWrkList.size(); i++) {

                final REV_RECOG_DTL_WRKTMsg dtlWrk = dtlWrkList.get(i);
                String cpoDtlLineNum    = dtlWrk.cpoDtlLineNum.getValue();
                String cpoDtlLineSubNum = dtlWrk.cpoDtlLineSubNum.getValue();

                if (cpoDtlLineNum.equals(prevLineNum) && cpoDtlLineSubNum.equals(prevLineSubNum)) {
                    // 同じLineNum,LineSubNumの場合はスキップ
                    // ※収益認識前に分割されたSHPG_PLNが同一タイミング収益認識される場合はWORKに同一LINE、LINE_SUBのデータがある
                    continue;
                }
                final CPO_DTLTMsg cpoDtl = getCpoDtl(dtlWrkList.get(i));

                // CPO_DTL update judgement
                if (this.isExistShpgPln) {

                    final Map<String, String> ssmParam = new HashMap<String, String>();
                    ssmParam.put("glblCmpyCd",         glblCmpyCd);
                    ssmParam.put("trxSrcTpWholeSales", WHOLE_SALES);
                    ssmParam.put("cpoOrdNum",          cpoDtl.cpoOrdNum.getValue());
                    ssmParam.put("cpoDtlLineNum",      cpoDtl.cpoDtlLineNum.getValue());
                    ssmParam.put("cpoDtlLineSubNum",   cpoDtl.cpoDtlLineSubNum.getValue());
                    ssmParam.put("shpgStsCdCancelled", SHPG_STS.CANCELLED);

                    Integer count = (Integer) ssmClient.queryObject("queryCpoDetailUpdateJudgement", ssmParam);

                    if (count == 0) {
                        cpoDtlUpdateFlg = true;
                    } else {
                        cpoDtlUpdateFlg = false;
                    }
                }

                // UPDATE CPO_DTL
                if (!this.isExistShpgPln || (this.isExistShpgPln && cpoDtlUpdateFlg)) {
                    // skip same line
                    if (cpoDtlLineNum.equals(prevLineNum) && cpoDtlLineSubNum.equals(prevLineSubNum)) {
                        continue;
                    }

                    setValue(cpoDtl.ordLineStsCd, ORD_LINE_STS.CLOSED);
                    setValue(cpoDtl.openFlg,      N);

                    update(cpoDtl);

                    if (!RTNCD_NORMAL.equals(cpoDtl.getReturnCode())) {
                        throw new S21AbendException(NWCM0047E, toArray(cpoDtl.cpoOrdNum.getValue() + "," + cpoDtl.cpoDtlLineNum.getValue() + "," + cpoDtl.cpoDtlLineSubNum.getValue()));
                    }

                    // Process Log Output
                    final S21BusinessProcessLogMsg bizProcLogMsg = new S21BusinessProcessLogMsg();
                    bizProcLogMsg.setSubSysId(SUB_SYSTEM_ID);
                    bizProcLogMsg.setProcId(PROCESS_ID);
                    bizProcLogMsg.setEventId(BizProcLogEventId.CLOSE.getId());
                    bizProcLogMsg.setDocTpCd(PROCESS_ID);
                    bizProcLogMsg.setDocId(cpoDtl.cpoDtlLineNum.getValue() + "." + cpoDtl.cpoDtlLineSubNum.getValue());
                    bizProcLogMsg.setPrntDocId(cpoDtl.cpoOrdNum.getValue());
                    S21BusinessProcessLog.print(bizProcLogMsg);

                    cpoUpdateFlg = true;
                }

                prevLineNum    = dtlWrk.cpoDtlLineNum.getValue();
                prevLineSubNum = dtlWrk.cpoDtlLineSubNum.getValue();
            }
            return cpoUpdateFlg && cpoUpdateFlg;
        }

        private void closeShpgPln(REV_RECOG_HDR_WRKTMsg hdrWrk, List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {

            for (int i = 0; i < dtlWrkList.size(); i++) {

                final REV_RECOG_DTL_WRKTMsg dtlWrk = dtlWrkList.get(i);

                if (SHPG_STS.VALIDATED.equals(dtlWrk.shpgStsCd.getValue()) || N.equals(dtlWrk.revRecogFlg.getValue())) {
                    continue;
                }

                // --------------------------------------------------
                // update SHPG_PLN.
                // --------------------------------------------------
                final SHPG_PLNTMsg shpgPln = lockedShpgPlnList.get(i);
                setValue(shpgPln.revRecogFlg, Y);
                String invCratKey = createCacheKey(hdrWrk.dsOrdTpCd.getValue(), dtlWrk.dsOrdLineCatgCd.getValue());

                if (cratInvOrdLineCatgCdList.contains(invCratKey)) {
                    setValue(shpgPln.shpgStsCd, SHPG_STS.INVOICED);
                }

                // ***** update
                update(shpgPln);

                if (!RTNCD_NORMAL.equals(shpgPln.getReturnCode())) {
                    throw new S21AbendException(NWCM0051E, toArray(shpgPln.shpgPlnNum.getValue()));
                }

                // 2017/11/21 QC#22508 Add Start
                // Change Status Number List
                if (!shpgNumList.contains(shpgPln.shpgPlnNum.getValue())) {
                    shpgNumList.add(shpgPln.shpgPlnNum.getValue());
                }
                if (shpgNumList.size() == MAX_LIST_SIZE) {
                    callStsUpdApi(dtlWrk.cpoOrdNum.getValue());
                }
                // 2017/11/21 QC#22508 Add End

                // --------------------------------------------------
                // print Biz Process Log.
                // --------------------------------------------------
                final S21BusinessProcessLogMsg bizProcLogMsg = new S21BusinessProcessLogMsg();
                bizProcLogMsg.setSubSysId(SUB_SYSTEM_ID);
                bizProcLogMsg.setProcId(PROCESS_ID);
                if (SHPG_STS.INVOICED.equals(shpgPln.shpgStsCd.getValue())) {
                    bizProcLogMsg.setEventId(BizProcLogEventId.INVOICED.getId());
                } else {
                    bizProcLogMsg.setEventId(BizProcLogEventId.SHIPED.getId());
                }
                bizProcLogMsg.setDocTpCd(PROCESS_ID);
                bizProcLogMsg.setDocId(shpgPln.trxLineNum.getValue() + "." + shpgPln.trxLineSubNum.getValue());
                bizProcLogMsg.setPrntDocId(shpgPln.trxHdrNum.getValue());
                S21BusinessProcessLog.print(bizProcLogMsg);
            }
        }

        // get so divided detail work
        private List<REV_RECOG_DTL_WRKTMsg> divideDetailDataList(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList, SoDivideKey key, List<SoDivideKey> lineKeyList, List<SHPG_PLNTMsg> shpgPlnList) {

            if (shpgPlnList.isEmpty()) {
                return dtlWrkList;
            }

            String soNumKey  = key.getSoNum();
            String bolNumKey = key.getBolNum();
            String proNumKey = key.getProNum();

            final List<String> lineNumList = new ArrayList<String>();
            for (SoDivideKey lineKey : lineKeyList) {
                String lineNum = lineKey.getCpoDtlLineNum();
                if (isEquals(soNumKey, lineKey.getSoNum()) && isEquals(bolNumKey, lineKey.getBolNum()) && isEquals(proNumKey, lineKey.getProNum())) {
                    if (!lineNumList.contains(lineNum)) {
                        lineNumList.add(lineNum);
                    }
                }
            }

            // select same so,bol,pro data
            final List<REV_RECOG_DTL_WRKTMsg> dtlList = new ArrayList<REV_RECOG_DTL_WRKTMsg>();

            for (int line = 0; line < lineNumList.size(); line++) {

                String lineNumKey = lineNumList.get(line);

                List<String> setSpNumLst = new ArrayList<String>();

                for (REV_RECOG_DTL_WRKTMsg dtlWrk : dtlWrkList) {

                    String lineNum = dtlWrk.cpoDtlLineNum.getValue();

                    if (lineNumKey.equals(lineNum)) {

                        String soNum = dtlWrk.soNum.getValue();
                        String bolNum = dtlWrk.bolNum.getValue();
                        String proNum = dtlWrk.proNum.getValue();

                        final SHPG_PLNTMsg shpgPln = getShpgPln(dtlWrk, shpgPlnList);
                        String spNum = shpgPln.shpgPlnNum.getValue();
                        String setSpNum = shpgPln.setShpgPlnNum.getValue();

                        if (isEquals(soNumKey, soNum) && isEquals(bolNumKey, bolNum) && isEquals(proNumKey, proNum)) {
                            if (!isSetParentLineSubNum(dtlWrk.cpoDtlLineSubNum.getValue()) && !setSpNumLst.contains(setSpNum)) {
                                for (REV_RECOG_DTL_WRKTMsg tmsg : dtlWrkList) {
                                    if (setSpNum.equals(tmsg.shpgPlnNum.getValue())) {

                                        final REV_RECOG_DTL_WRKTMsg ctmsg = new REV_RECOG_DTL_WRKTMsg();
                                        EZDMsg.copy(tmsg, null, ctmsg, null);
                                        setValue(ctmsg.soNum, soNum);
                                        setValue(ctmsg.bolNum, bolNum);
                                        setValue(ctmsg.proNum, proNum);
                                        setValue(ctmsg.revRecogFlg, N);
                                        dtlList.add(ctmsg);
                                        break;
                                    }
                                }
                                setSpNumLst.add(setSpNum);
                            }

                            dtlList.add(dtlWrk);

                            if (isSetParentLineSubNum(dtlWrk.cpoDtlLineSubNum.getValue()) && !setSpNumLst.contains(spNum)) {
                                setSpNumLst.add(spNum);
                            }
                        }
                    } else {
                        setSpNumLst = new ArrayList<String>();
                    }
                }
            }
            return dtlList;
        }

        // get set divided detail work
        private List<REV_RECOG_DTL_WRKTMsg> divideSetDataList(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList, String setShpgPlnNum, List<SHPG_PLNTMsg> shpgPlnList) {

            if (shpgPlnList.isEmpty()) {
                return dtlWrkList;
            }

            final List<SHPG_PLNTMsg> spList = new ArrayList<SHPG_PLNTMsg>();
            for (SHPG_PLNTMsg shpgPln : shpgPlnList) {
                String spNum    = shpgPln.shpgPlnNum.getValue();
                String setSpNum = shpgPln.setShpgPlnNum.getValue();
                if (setShpgPlnNum.equals(spNum) || setShpgPlnNum.equals(setSpNum)) {
                    spList.add(shpgPln);
                }
            }

            final List<REV_RECOG_DTL_WRKTMsg> dtlList = new ArrayList<REV_RECOG_DTL_WRKTMsg>();
            for (int i = 0; i < dtlWrkList.size(); i++) {
                REV_RECOG_DTL_WRKTMsg dtlWrk = dtlWrkList.get(i);
                String shpgPlnNum = dtlWrk.shpgPlnNum.getValue();
                for (SHPG_PLNTMsg shpgPln : spList) {
                    if (shpgPlnNum.equals(shpgPln.shpgPlnNum.getValue())) {
                        dtlList.add(dtlWrk);
                        break;
                    }
                }
            }

            return dtlList;
        }

        private boolean endJudgement(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {

            boolean endFlg = true;
            for (int i = 0; i < dtlWrkList.size(); i++) {
                REV_RECOG_DTL_WRKTMsg dtlWrk = dtlWrkList.get(i);
                if (isSetParentLineSubNum(dtlWrk.cpoDtlLineSubNum.getValue())) {
                    boolean lineEndFlg = true;
                    for (int j = 0; j < dtlWrkList.size(); j++) {
                        REV_RECOG_DTL_WRKTMsg ctmsg = dtlWrkList.get(j);
                        if (dtlWrk.cpoDtlLineNum.getValue().equals(ctmsg.cpoDtlLineNum.getValue()) && !isSetParentLineSubNum(ctmsg.cpoDtlLineSubNum.getValue())) {
                            lineEndFlg = false;
                            endFlg     = false;
                            break;
                        }
                    }
                    if (lineEndFlg && !dtlWrkList.isEmpty()) {
                        dtlWrkList.remove(dtlWrk);
                        i--;
                    }
                } else {
                    endFlg = false;
                }
            }

            if (dtlWrkList.isEmpty()) {
                endFlg = true;
            }

            return endFlg;
        }

        private boolean excludeForReBillPairOrder(REV_RECOG_HDR_WRKTMsg hdrWrk) {

            final Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd",          glblCmpyCd);
            ssmParam.put("reBillPairCpoOrdNum", hdrWrk.reBillPairCpoOrdNum.getValue());
            ssmParam.put("cpoOrdNum",           hdrWrk.cpoOrdNum.getValue());
            ssmParam.put("ordHdrStsClose",      ORD_HDR_STS.CLOSED);
            // 2022/03/24 QC#59825 Add Start 
            ssmParam.put("ordHdrStsCancel",      ORD_HDR_STS.CANCELLED);
            // 2022/03/24 QC#59825 Add End 

            Integer ssmRes = (Integer) ssmClient.queryObject("queryCountForReBillPairOrder", ssmParam);

            if (ssmRes == 0) {
                return false;
            }

            return true;
        }

        // 2022/03/24 QC#59825 Add Start 
        private boolean isCanceledReBillPairOrder(String creditOrdNum) {

            final Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd",          glblCmpyCd);
            ssmParam.put("creditOrdNum",        creditOrdNum);
            ssmParam.put("ordHdrStsCancel",      ORD_HDR_STS.CANCELLED);

            Integer ssmRes = (Integer) ssmClient.queryObject("queryCountForCanceledReBillPairOrder", ssmParam);

            if (ssmRes > 0) {
                return true;
            }

            return false;
        }
        // 2022/03/24 QC#59825 Add End 

       private CPO_DTLTMsg getCpoDtl(REV_RECOG_DTL_WRKTMsg dtlWrk) {

            String cpoOrdNum        = dtlWrk.cpoOrdNum.getValue();
            String cpoDtlLineNum    = dtlWrk.cpoDtlLineNum.getValue();
            String cpoDtlLineSubNum = dtlWrk.cpoDtlLineSubNum.getValue();

            return lockedCpoDtl.get(cpoOrdNum + cpoDtlLineNum + cpoDtlLineSubNum);
        }

        // 2018/05/24 QC#22097 Del Start
//        private BigDecimal getCostAmountFromOriginalInvoice(CPO_DTLTMsg cpoDtl, REV_RECOG_DTL_WRKTMsg dtlWrk) {
//
//            Map<String, String> ssmParam = new HashMap<String, String>();
//            ssmParam.put("glblCmpyCd", glblCmpyCd);
//            ssmParam.put("invNum",           cpoDtl.origInvNum.getValue());
//            ssmParam.put("cpoOrdNum",        lockedCpo.ordSrcRefNum.getValue());
//            ssmParam.put("cpoDtlLineNum",    cpoDtl.ordSrcRefLineNum.getValue());
//            ssmParam.put("cpoDtlLineSubNum", cpoDtl.ordSrcRefLineSubNum.getValue());
//
//            BigDecimal origInvCostAmt = (BigDecimal) ssmClient.queryObject("getCostAmountFromOriginalInvoice", ssmParam);
//            if (origInvCostAmt == null) {
//                origInvCostAmt = getInventoryItemCost(cpoDtl, dtlWrk);
//            }
//
//            return origInvCostAmt;
//        }
        // 2018/05/24 QC#22097 Del End

        // 2018/05/24 QC#22097 Add Start
        private BigDecimal getCostAmountFromCostUpdateHistory(CPO_DTLTMsg cpoDtl, REV_RECOG_DTL_WRKTMsg dtlWrk) {

            BigDecimal origInvCostAmt = null;

            INVTMsg invTMsg = new INVTMsg();
            invTMsg.glblCmpyCd.setValue(glblCmpyCd);
            invTMsg.invNum.setValue(cpoDtl.origInvNum.getValue());
            invTMsg = (INVTMsg) S21CacheTBLAccessor.findByKey(invTMsg);

            if (invTMsg != null) {
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("mdseCd", cpoDtl.mdseCd);
                ssmParam.put("invDt", invTMsg.invDt.getValue());
                ssmParam.put("stsAC", MDSE_CST_UPD_STS.ACTIVE);
                ssmParam.put("stsAR", MDSE_CST_UPD_STS.ARCHIVED);
                ssmParam.put("rowNum", BigDecimal.ONE);
    
                origInvCostAmt = (BigDecimal) ssmClient.queryObject("getCostAmountFromCostUpdateHistory", ssmParam);

                // QC#59039 Add Start
                if ((CR_REBIL.CREDIT.equals(dtlWrk.crRebilCd.getValue()) || CR_REBIL.REBILL.equals(dtlWrk.crRebilCd.getValue())) //
                        && hasValue(cpoDtl.origInvtyLocCd) && hasValue(origInvCostAmt)) {

                    // Calculate Cost
                    NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();
                    bean.setGlblCmpyCd(glblCmpyCd);
                    bean.setInvtyLocCd(cpoDtl.origInvtyLocCd.getValue());
                    Map<String, Object> mdseInventoryCostInfo = getMdseInventoryCostPercent(bean);

                    if (mdseInventoryCostInfo != null) {
                        // check Inventory owner
                        // if this is not inventory owner, amount zero.
                        if (ZYPConstant.FLG_OFF_N.equals((String) mdseInventoryCostInfo.get("CMPY_INVTY_FLG"))) {
                            bean.setUnitPrcAmt(BigDecimal.ZERO);
                            bean.setTotPrcAmt(BigDecimal.ZERO);
                            bean.setDspUnitPrcAmt(BigDecimal.ZERO);
                            bean.setDspTotPrcAmt(BigDecimal.ZERO);
                        } else if (INVTY_ACCT.ASSET.equals((String) mdseInventoryCostInfo.get("INVTY_ACCT_CD"))) {
                            // check Inventory Account
                            // if inventory account is asset, amount zero.
                            bean.setUnitPrcAmt(BigDecimal.ZERO);
                            bean.setTotPrcAmt(BigDecimal.ZERO);
                            bean.setDspUnitPrcAmt(BigDecimal.ZERO);
                            bean.setDspTotPrcAmt(BigDecimal.ZERO);
                        }

                        BigDecimal unitAmt = BigDecimal.ZERO;
                        if (MDSE_COST_TP.STANDARD_COST.equals((String) mdseInventoryCostInfo.get("MDSE_COST_TP_CD"))) {
                            unitAmt = ((BigDecimal) origInvCostAmt).multiply( //
                                    ((BigDecimal) mdseInventoryCostInfo.get("MDSE_INVTY_COST_PCT")).divide(BigDecimal.valueOf(100)));
                        } else {
                            unitAmt = ((BigDecimal) origInvCostAmt).multiply( //
                                    ((BigDecimal) mdseInventoryCostInfo.get("MDSE_INVTY_COST_PCT")).divide(BigDecimal.valueOf(100)));
                        }

                        String costRound = ZYPCodeDataUtil.getVarCharConstValue("COST_ROUND_OPTION", glblCmpyCd);
                        int costPrecision = Integer.valueOf(ZYPCodeDataUtil.getVarCharConstValue("COST_PRECISION", glblCmpyCd));

                        if ("1".equals(costRound)) { // ROUND_UP
                            origInvCostAmt = unitAmt.setScale(costPrecision, BigDecimal.ROUND_UP);

                        } else if ("2".equals(costRound)) { // ROUND
                            origInvCostAmt = unitAmt.setScale(costPrecision, BigDecimal.ROUND_HALF_UP);

                        } else {
                            // Default Round down
                            origInvCostAmt = unitAmt.setScale(costPrecision, BigDecimal.ROUND_DOWN);
                        }
                    }
                }
                // QC#59039 Add End
            }

            if (origInvCostAmt == null) {
                origInvCostAmt = getInventoryItemCost(cpoDtl, dtlWrk);
            }

            return origInvCostAmt;
        }
        // 2018/05/24 QC#22097 Add End

        private BigDecimal getInventoryItemCost(CPO_DTLTMsg cpoDtlTMsg,  REV_RECOG_DTL_WRKTMsg dtlWrk) {
            // get Inventory Item Cost if original order is not invoiced
            String invtyLocCd = dtlWrk.invtyLocCd.getValue();   //original inventory location
            if (!hasValue(invtyLocCd)) {
                return ZERO;
            }

            if (ZYPConstant.FLG_ON_Y.equals(dtlWrk.poReqFlg.getValue())) {
                invtyLocCd = "DS";
            }

            String mdseCd = cpoDtlTMsg.mdseCd.getValue();
            String cacheKey = createCacheKey("getInventoryItemCost", invtyLocCd, mdseCd);

            BigDecimal unitPrcAmt = (BigDecimal) s21LRUMap.get(cacheKey);

            if (unitPrcAmt == null) {
                NLXC001001GetInventoryItemCostBean getInvtyItemCostBean = new NLXC001001GetInventoryItemCostBean();
                getInvtyItemCostBean.setGlblCmpyCd(glblCmpyCd);
                getInvtyItemCostBean.setInvtyLocCd(invtyLocCd);
                getInvtyItemCostBean.setMdseCd(mdseCd);

                NLXC001001GetInventoryItemCost.getInventoryItemCost(getInvtyItemCostBean);

                for (String errId : getInvtyItemCostBean.getErrList()) {
                    S21InfoLogOutput.println(errId);
                    hasErr = true;
                }
                unitPrcAmt = getInvtyItemCostBean.getUnitPrcAmt();
                s21LRUMap.put(cacheKey, unitPrcAmt);
            }

            return unitPrcAmt;
        }

        private BigDecimal getCostAmountFromOriginalOrder(CPO_DTLTMsg cpoDtlTMsg, SHPG_PLNTMsg shpgPlnTMsg) {

            NLXC001001GetInventoryItemCostBean getInvtyItemCostBean = new NLXC001001GetInventoryItemCostBean();
            getInvtyItemCostBean.setGlblCmpyCd(glblCmpyCd);
            getInvtyItemCostBean.setInvtyLocCd(cpoDtlTMsg.rtlWhCd.getValue() + cpoDtlTMsg.rtlSwhCd.getValue());
            getInvtyItemCostBean.setMdseCd(shpgPlnTMsg.mdseCd.getValue());

            NLXC001001GetInventoryItemCost.getInventoryItemCost(getInvtyItemCostBean);

            for (String errId : getInvtyItemCostBean.getErrList()) {
                S21InfoLogOutput.println(errId);
                hasErr = true;
            }

            return getInvtyItemCostBean.getUnitPrcAmt();
        }

        // get so diveide key
        private List<SoDivideKey> getKeyList(List<SoDivideKey> lineKeyList) {

            List<SoDivideKey> keyList = new ArrayList<SoDivideKey>();

            for (int i = 0; i < lineKeyList.size(); i++) {

                SoDivideKey key = lineKeyList.get(i);
                String soNum  = key.getSoNum();
                String bolNum = key.getBolNum();
                String proNum = key.getProNum();

                int j = 0;
                for (; j < keyList.size(); j++) {
                    SoDivideKey lineKey = keyList.get(j);
                    if (isEquals(soNum, lineKey.getSoNum()) && isEquals(bolNum, lineKey.getBolNum()) && isEquals(proNum, lineKey.getProNum())) {
                        break;
                    }
                }

                if (j == keyList.size()) {
                    keyList.add(key);
                }
            }

            return keyList;
        }

        private List<REV_RECOG_DTL_WRKTMsg> getRevRecogDtlWrkList(String cpoOrdNum, BigDecimal dsCpoConfigPk) {

            final Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("cpoOrdNum",  cpoOrdNum);
            ssmParam.put("dsCpoConfigPk",  dsCpoConfigPk.toString());
            return (List<REV_RECOG_DTL_WRKTMsg>) ssmClient.queryObjectList("getRevRecogDtlWrkList", ssmParam);
        }

        private List<SetItemData> getSetItemInfo(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList, String prntMdseCd, SHPG_PLNTMsg prntSpTmsg, List<SHPG_PLNTMsg> prcdShpgPlnList, BigDecimal prntQty) {

            String setItemLineNum = prntSpTmsg.trxLineNum.getValue();

            // parent info
            SetItemData prntMdseData = new SetItemData();
            String cachKey = createCacheKey(glblCmpyCd, prntMdseCd);

            List<SetItemData> setCompList = (List<SetItemData>) setItemCache.get(cachKey);

            if (setCompList == null) {

                // get set compornent ratio from CPO detail
                final Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd",       glblCmpyCd);
                ssmParam.put("cpoOrdNum",        prntSpTmsg.trxHdrNum.getValue());
                ssmParam.put("cpoDtlLineNum",    setItemLineNum);
                ssmParam.put("prntQty",          prntQty);

                setCompList = (List<SetItemData>) ssmClient.queryObjectList("queryCMPSNbyCPO_DTL", ssmParam);

                if (setCompList.isEmpty()) {
                    S21InfoLogOutput.println(NWCM0025E, toArray(prntMdseCd));
                    hasErr = true;
                    return null;
                }

                // parent infomation add to beginning of list
                setCompList.add(0, prntMdseData);
                setItemCache.put(cachKey, setCompList);

            } else {
                prntMdseData = setCompList.get(0);
            }

            // VendorDropMdse are treated as tangible items
            for (SetItemData setItemData : setCompList) {

                if (N.equals(setItemData.getInvtyCtrlFlg())) {
                    final Map<String, String> ssmParam = new HashMap<String, String>();
                    ssmParam.put("glblCmpyCd", glblCmpyCd);
                    ssmParam.put("mdseCd",     setItemData.getChildMdseCd());
                    int vndDrpMdseCnt = (Integer) ssmClient.queryObject("queryVendorDropMdse", ssmParam);
                    if (vndDrpMdseCnt != 0) {
                        setItemData.setInvtyCtrlFlg(Y);
                    }
                }
            }

            // init qty
            for (SetItemData setItemData : setCompList) {
                setItemData.setOrdQty(ZERO);
                setItemData.setSetItemQty(ZERO);
                setItemData.setPrcdQty(ZERO);
            }

            prntMdseData.setPrntMdseCd(prntMdseCd);
            prntMdseData.setSpPrntMdseCd(prntMdseCd);
            prntMdseData.setOrdQty(prntQty);
            prntMdseData.setChildMdseQty(ONE);

            // set Parent quantity of revenue recognition processed
            BigDecimal sumQty = ZERO;
            for (SHPG_PLNTMsg prcdSpTmsg : prcdShpgPlnList) {
                String trxLineNum = prcdSpTmsg.trxLineNum.getValue();
                String mdseCd     = prcdSpTmsg.mdseCd.getValue();
                if (trxLineNum.equals(setItemLineNum) && prntMdseCd.equals(mdseCd)) {
                    sumQty = sumQty.add(prcdSpTmsg.ordQty.getValue());
                }
            }
            prntMdseData.setPrcdQty(sumQty);

            // MDSE matching component to add the quantity of SHPG_PLN
            BigDecimal minQty = null;
            for (int i = 1; i < setCompList.size(); i++) {

                SetItemData setItemData = setCompList.get(i);
                sumQty = ZERO;
                String childMdseCd        = setItemData.getChildMdseCd();
                String childOrdTakeMdseCd = setItemData.getChildOrdTakeMdseCd();

                String invtyCtrlFlg = setItemData.getInvtyCtrlFlg();

                // Quantity of the data set for revenue recognition
                for (int j = 0; j < dtlWrkList.size(); j++) {

                    final REV_RECOG_DTL_WRKTMsg dtlWrk = dtlWrkList.get(j);
                    CPO_DTLTMsg           cpoDtl = getCpoDtl(dtlWrk);
                    SHPG_PLNTMsg          shpgPln = lockedShpgPlnList.get(j);

                    String trxLineNum = shpgPln.trxLineNum.getValue();
                    String mdseCd     = cpoDtl.mdseCd.getValue();

                    if (setItemLineNum.equals(trxLineNum) && (mdseCd.equals(childMdseCd) || mdseCd.equals(childOrdTakeMdseCd))) {
                        setItemData.setSpChildMdseCd(mdseCd);
                        sumQty = sumQty.add(shpgPln.ordQty.getValue());
                    }
                }

                for (int j = 0; j < prcdShpgPlnList.size(); j++) {

                    final SHPG_PLNTMsg prcdSpTmsg = prcdShpgPlnList.get(j);
                    String trxLineNum = prcdSpTmsg.trxLineNum.getValue();
                    String mdseCd     = prcdSpTmsg.mdseCd.getValue();

                    if (setItemLineNum.equals(trxLineNum) && (mdseCd.equals(childMdseCd) || mdseCd.equals(childOrdTakeMdseCd))) {
                        BigDecimal ordQty = prcdSpTmsg.ordQty.getValue();
                        setItemData.setSpChildMdseCd(mdseCd);
                        sumQty = sumQty.add(ordQty);
                        setItemData.setPrcdQty(ordQty);
                    }
                }
                setItemData.setOrdQty(sumQty);

                if (Y.equals(invtyCtrlFlg)) {
                    // 有形商品コンポーネント毎に取り得るSetItem数量を算出する
                    if (ZERO.compareTo(setItemData.getOrdQty()) != 0) {

                        BigDecimal childQty   = setItemData.getChildMdseQty();
                        BigDecimal setItemQty = divide(setItemData.getOrdQty(), childQty, 0, HALF_UP);
                        setItemData.setSetItemQty(setItemQty);

                        // 取り得る最小のSetItemの数量を算出する
                        if (ZERO.compareTo(setItemQty) < 0) {
                            if (minQty == null || minQty.compareTo(setItemQty) > 0) {
                                minQty = setItemQty;
                            }
                        }
                    }
                }
            }

            if (minQty == null) {
                minQty = ZERO;
            }

            // 取り得るSetitem数量のうち最小値を親データにセットする
            // 最小値＝収益認識済みor対象となるset数量
            SetItemData cpBean = setCompList.get(0);
            cpBean.setSetItemQty(minQty);

            return setCompList;
        }

        // get set diveide key
        private List<String> getSetKeyList(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {

            List<String> keyList = new ArrayList<String>();

            for (REV_RECOG_DTL_WRKTMsg dtlWrk : dtlWrkList) {

                String cpoDtlLineSubNum = dtlWrk.cpoDtlLineSubNum.getValue();

                if (isSetParentLineSubNum(cpoDtlLineSubNum)) {
                    if (!keyList.contains(dtlWrk.shpgPlnNum.getValue())) {
                        keyList.add(dtlWrk.shpgPlnNum.getValue());
                    }
                }
            }

            if (this.isExistShpgPln || !this.isExistShpgPln && keyList.isEmpty()) {
                keyList.add("");
            }

            return keyList;
        }

        private SHPG_PLNTMsg getShpgPln(REV_RECOG_DTL_WRKTMsg dtlWrk, List<SHPG_PLNTMsg> shpgPlnList) {

            final String shpgPlnNum = dtlWrk.shpgPlnNum.getValue();

            for (SHPG_PLNTMsg shpgPln : shpgPlnList) {
                if (shpgPlnNum.equals(shpgPln.shpgPlnNum.getValue())) {
                    return shpgPln;
                }
            }

            return null;
        }

        // get line so diveide key
        private List<SoDivideKey> getSoDivKeyList(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {

            final Map<String, SoDivideKey> soDivKeyMap = new LinkedHashMap<String, SoDivideKey>();

            for (REV_RECOG_DTL_WRKTMsg dtlWrk : dtlWrkList) {

                String cpoDtlLineNum = dtlWrk.cpoDtlLineNum.getValue();
                String soNum         = dtlWrk.soNum.getValue();
                String bolNum        = dtlWrk.bolNum.getValue();
                String proNum        = dtlWrk.proNum.getValue();

                final String key = cpoDtlLineNum + "." + soNum + "." + bolNum + "." + proNum;

                if (!soDivKeyMap.containsKey(key)) {
                    final SoDivideKey soDivKey = new SoDivideKey();
                    soDivKey.setCpoDtlLineNum(cpoDtlLineNum);
                    soDivKey.setSoNum(soNum);
                    soDivKey.setBolNum(bolNum);
                    soDivKey.setProNum(proNum);
                    soDivKeyMap.put(key, soDivKey);
                }
            }

            return new ArrayList<SoDivideKey>(soDivKeyMap.values());
        }

        // select cpo from table
        private CPOTMsg lockCpo(REV_RECOG_HDR_WRKTMsg hdrWrk) {

            final CPOTMsg reqCpo = new CPOTMsg();
            setValue(reqCpo.glblCmpyCd, glblCmpyCd);
            setValue(reqCpo.cpoOrdNum,  hdrWrk.cpoOrdNum);

            final CPOTMsg resCpo = (CPOTMsg) findByKeyForUpdate(reqCpo);

            if (resCpo == null) {
                throw new S21AbendException(NWCM0052E, toArray(reqCpo.cpoOrdNum.getValue()));
            }
            return resCpo;
        }

        private DS_CPO_CONFIGTMsg lockCpoConfig(REV_RECOG_HDR_WRKTMsg hdrWrk) {

            final DS_CPO_CONFIGTMsg reqCpoConfig = new DS_CPO_CONFIGTMsg();
            setValue(reqCpoConfig.glblCmpyCd, glblCmpyCd);
            setValue(reqCpoConfig.dsCpoConfigPk,  hdrWrk.dsCpoConfigPk);

            final DS_CPO_CONFIGTMsg resCpoConfig = (DS_CPO_CONFIGTMsg) findByKeyForUpdate(reqCpoConfig);

            if (resCpoConfig == null) {
                throw new S21AbendException(NWCM0052E, toArray(reqCpoConfig.dsCpoConfigPk.getValue().toString()));
            }
            return resCpoConfig;
        }

        private Map<String, CPO_DTLTMsg> lockCpoDtl(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {

            final Map<String, CPO_DTLTMsg> cpoDtlMap = new HashMap<String, CPO_DTLTMsg>();

            for (REV_RECOG_DTL_WRKTMsg dtlWrk : dtlWrkList) {

                String cpoOrdNum        = dtlWrk.cpoOrdNum.getValue();
                String cpoDtlLineNum    = dtlWrk.cpoDtlLineNum.getValue();
                String cpoDtlLineSubNum = dtlWrk.cpoDtlLineSubNum.getValue();

                final String key = createCacheKey(cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);

                if (!cpoDtlMap.containsKey(key)) {

                    CPO_DTLTMsg cpoDtl = new CPO_DTLTMsg();
                    setValue(cpoDtl.glblCmpyCd,       glblCmpyCd);
                    setValue(cpoDtl.cpoOrdNum,        cpoOrdNum);
                    setValue(cpoDtl.cpoDtlLineNum,    cpoDtlLineNum);
                    setValue(cpoDtl.cpoDtlLineSubNum, cpoDtlLineSubNum);

                    cpoDtl = (CPO_DTLTMsg) findByKeyForUpdate(cpoDtl);

                    if (cpoDtl == null) {
                        throw new S21AbendException(NWCM0044E, toArray(cpoOrdNum + "," + cpoDtlLineNum + "," + cpoDtlLineSubNum));
                    }
                    cpoDtlMap.put(key, cpoDtl);
                }
            }

            return cpoDtlMap;
        }

        // select shipping plan from table
        private List<SHPG_PLNTMsg> lockShpgPlnList(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {

            final List<SHPG_PLNTMsg> shpgPlnList = new ArrayList<SHPG_PLNTMsg>();

            for (REV_RECOG_DTL_WRKTMsg dtlWrk : dtlWrkList) {

                SHPG_PLNTMsg shpgPln = new SHPG_PLNTMsg();
                setValue(shpgPln.glblCmpyCd, glblCmpyCd);
                setValue(shpgPln.shpgPlnNum, dtlWrk.shpgPlnNum);

                shpgPln = (SHPG_PLNTMsg) findByKeyForUpdate(shpgPln);

                if (shpgPln != null) {
                    shpgPlnList.add(shpgPln);
                }
            }

            return shpgPlnList;
        }

        private void removeExcludeData (REV_RECOG_HDR_WRKTMsg hdrWrkTMsg, List<REV_RECOG_DTL_WRKTMsg> dtlWrkTMsgList, List<RevRecogExecutorInner> waitConfigList) {

            Iterator<REV_RECOG_DTL_WRKTMsg> it = dtlWrkTMsgList.iterator();
            Map<String, Object> ssmParam;
            String cacheKey = null;

            // Credir&Rebill
            BigDecimal dsCpoConfigPk   = hdrWrkTMsg.dsCpoConfigPk.getValue();
            String cpoOrdNum           = hdrWrkTMsg.cpoOrdNum.getValue();

            // QC#54722 2019/11/26 Del Start
            //while (it.hasNext()) {
            //
            //    final REV_RECOG_DTL_WRKTMsg dtlWrkTMsg = it.next();
            //
            //    String rtlWhCd = getRtlWhCd(dtlWrkTMsg);
            //    List<String> dummyWhCdList = new ArrayList<String>();
            //
            //    for(String whCd : crRebillDummyWhCdList){
            //        if (!SHIP_FROM_INVTY_LOC_CD_CR.equals(whCd)){
            //            dummyWhCdList.add(whCd);
            //        }
            //    }
            //
            //    if (dummyWhCdList.contains(rtlWhCd)) {
            //        // rebill , loan to sales, rental to sales
            //        // with ship item data
            //        String cpoDtlLineNum    = dtlWrkTMsg.cpoDtlLineNum.getValue();
            //        String cpoDtlLineSubNum = dtlWrkTMsg.cpoDtlLineSubNum.getValue();
            //        String refCpoDtlLineNum    = dtlWrkTMsg.refCpoDtlLineNum.getValue();
            //        String refCpoDtlLineSubNum = dtlWrkTMsg.refCpoDtlLineSubNum.getValue();
            //
            //        if (hasValue(refCpoDtlLineNum)) {
            //        } else {
            //            ssmParam = new HashMap<String, Object>();
            //            ssmParam.put("glblCmpyCd",            glblCmpyCd);
            //            ssmParam.put("cpoOrdNum",             cpoOrdNum);
            //            ssmParam.put("dsCpoConfigPk",         dsCpoConfigPk);
            //            ssmParam.put("openFlg",               ZYPConstant.FLG_ON_Y);
            //            ssmParam.put("refCpoDtlLineNum",      cpoDtlLineNum);
            //            ssmParam.put("refCpoDtlLineSubNum",   cpoDtlLineSubNum);
            //            ssmParam.put("crRebillDummyWhCdList", dummyWhCdList);
            //            cacheKey = createCacheKey("countCpoDtlShipItemRecord", cpoOrdNum, dsCpoConfigPk);
            //
            //            Integer cnt = (Integer) searchObject("countCpoDtlShipItemRecord", ssmParam, cacheKey);
            //
            //            if (cnt > 0) {
            //                boolean withShipFlg = false;
            //                for (REV_RECOG_DTL_WRKTMsg tmsg : dtlWrkTMsgList) {
            //                    if (!dummyWhCdList.contains(getRtlWhCd(tmsg))
            //                            && cpoDtlLineNum.equals(tmsg.refCpoDtlLineNum.getValue())
            //                            && cpoDtlLineSubNum.equals(tmsg.refCpoDtlLineSubNum.getValue())
            //                    ) {
            //                        withShipFlg = true;
            //                    }
            //                }
            //                if (!withShipFlg) {
            //                    // exclude if exist open cpo dtl and not exist revenue target
            //                    it.remove();
            //                    continue;
            //                }
            //            }
            //        }
            //    }
            //}
            // QC#54722 2019/11/26 Del End

            it = dtlWrkTMsgList.iterator();
            while (it.hasNext()) {
                REV_RECOG_DTL_WRKTMsg dtlWrkTMsg = it.next();
                String refCpoDtlLineNum    = dtlWrkTMsg.refCpoDtlLineNum.getValue();
                String refCpoDtlLineSubNum = dtlWrkTMsg.refCpoDtlLineSubNum.getValue();

                if (hasValue(refCpoDtlLineNum) && hasValue(refCpoDtlLineSubNum)) {
                    ssmParam = new HashMap<String, Object>();
                    ssmParam.put("glblCmpyCd",            glblCmpyCd);
                    ssmParam.put("cpoOrdNum",             cpoOrdNum);
                    ssmParam.put("dsCpoConfigPk",         dsCpoConfigPk);
                    ssmParam.put("openFlg",               ZYPConstant.FLG_ON_Y);
                    ssmParam.put("cpoDtlLineNum",         refCpoDtlLineNum);
                    ssmParam.put("cpoDtlLineSubNum",      refCpoDtlLineSubNum);
                    ssmParam.put("crRebillDummyWhCdList", crRebillDummyWhCdList);
                    ssmParam.put("ordLineCtxTpCd",        CONST_ORD_LINE_CTX_TP_REV_RECOG_EXEM);   //QC#17758 add

                    List<Map<String, Object>> list =  (List<Map<String, Object>>) ssmClient.queryObjectList("getReferenceRecord", ssmParam);

                    if ((list != null) && (!list.isEmpty())){
                        boolean removeFlg = true;
                        for (REV_RECOG_DTL_WRKTMsg tmsg : dtlWrkTMsgList) {
                            if (refCpoDtlLineNum.equals(tmsg.cpoDtlLineNum.getValue()) && refCpoDtlLineSubNum.equals(tmsg.cpoDtlLineSubNum.getValue())) {
                                removeFlg = false;
                                break;
                            }
                        }

                        if (removeFlg) {
                            it.remove();

                            // QC#20343 Add Start
                            List<RevenueKey> keys = new ArrayList<RevenueKey>();

                            for (Map<String, Object> item: list) {
                                keys.add(
                                    new RevenueKey(
                                        (String)item.get("CPO_ORD_NUM")
                                        , ((BigDecimal)item.get("DS_CPO_CONFIG_PK")).toPlainString()
                                        , (String)item.get("CPO_DTL_LINE_NUM")
                                        , (String)item.get("CPO_DTL_LINE_SUB_NUM")
                                    )
                                );
                            }

                            waitConfigList.add(
                                new RevRecogExecutorInner(
                                    keys,
                                    hdrWrkTMsg,
                                    Arrays.asList(dtlWrkTMsg)
                                )
                            );
                            // QC#20343 Add End
                        }
                    }
                }
            }
        }

        // remove parent only set item data
        private void removeParentOnlyData(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {

            for (int i = 0; i < dtlWrkList.size(); i++) {

                REV_RECOG_DTL_WRKTMsg dtlWrk = dtlWrkList.get(i);

                String cpoDtlLineNum    = dtlWrk.cpoDtlLineNum.getValue();
                String cpoDtlLineSubNum = dtlWrk.cpoDtlLineSubNum.getValue();

                // parent only data remove
                if (isSetParentLineSubNum(cpoDtlLineSubNum)) {

                    boolean revRecogFlg = false;

                    for (int j = 0; j < dtlWrkList.size(); j++) {
                        REV_RECOG_DTL_WRKTMsg cmptDtlWrk = dtlWrkList.get(j);
                        if (cpoDtlLineNum.equals(cmptDtlWrk.cpoDtlLineNum.getValue())) {
                            if (!isSetParentLineSubNum(cmptDtlWrk.cpoDtlLineSubNum.getValue())) {
                                if (Y.equals(cmptDtlWrk.revRecogFlg.getValue())) {
                                    revRecogFlg = true;
                                    break;
                                }
                            }
                        }
                    }

                    if (!revRecogFlg) {
                        dtlWrkList.remove(dtlWrk);
                    }
                }
            }
        }

        // QC#20343 Add Start
        private Boolean isWaitSubscriptionSupportServiceData (REV_RECOG_HDR_WRKTMsg hdrWrkTMsg, List<REV_RECOG_DTL_WRKTMsg> dtlWrkTMsgList, List<RevRecogExecutorInner> waitConfigList) {

            //Check SubscriptionSupportServiceData
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd",            glblCmpyCd);
            ssmParam.put("dsCpoConfigPk",         hdrWrkTMsg.dsCpoConfigPk);

            Integer cnt = (Integer) ssmClient.queryObject("countSubscriptionSupportService", ssmParam);

            if (cnt <= 0) {
                return false;
            }

            //
            // Check Other Equipment Config 
            //
            
            // Processed Equipment
            ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd",  glblCmpyCd);
            ssmParam.put("cpoOrdNum",   hdrWrkTMsg.cpoOrdNum);
            ssmParam.put("openFlg",     ZYPConstant.FLG_OFF_N);
            ssmParam.put("swMdlFlg",    ZYPConstant.FLG_OFF_N);
            ssmParam.put("revRecogFlg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("baseCmptFlg", ZYPConstant.FLG_ON_Y);

            List<Map<String, Object>> list =  (List<Map<String, Object>>) ssmClient.queryObjectList("getEquipmentKeyByOderNumber", ssmParam);

            if ((list != null) && (!list.isEmpty())){
                return false;
            }

            // Not Processed Equipment
            ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd",  glblCmpyCd);
            ssmParam.put("cpoOrdNum",   hdrWrkTMsg.cpoOrdNum);
            ssmParam.put("openFlg",     ZYPConstant.FLG_ON_Y);
            ssmParam.put("swMdlFlg",    ZYPConstant.FLG_OFF_N);
            ssmParam.put("revRecogFlg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("baseCmptFlg", ZYPConstant.FLG_ON_Y);

            list =  (List<Map<String, Object>>) ssmClient.queryObjectList("getEquipmentKeyByOderNumber", ssmParam);

            if ((list == null) || (list.isEmpty())){

                //
                // Check Other No Equipment Config
                //

                // Processed No Equipment
                ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd",  glblCmpyCd);
                ssmParam.put("cpoOrdNum",   hdrWrkTMsg.cpoOrdNum);
                ssmParam.put("openFlg",     ZYPConstant.FLG_OFF_N);
                ssmParam.put("revRecogFlg", ZYPConstant.FLG_ON_Y);

                list =  (List<Map<String, Object>>) ssmClient.queryObjectList("getNonEquipmentKeyByOderNumber", ssmParam);

                if ((list != null) && (!list.isEmpty())){
                    return false;
                }

                // Not Processed No Equipment
                ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd",  glblCmpyCd);
                ssmParam.put("cpoOrdNum",   hdrWrkTMsg.cpoOrdNum);
                ssmParam.put("openFlg",     ZYPConstant.FLG_ON_Y);
                ssmParam.put("revRecogFlg", ZYPConstant.FLG_OFF_N);

                list =  (List<Map<String, Object>>) ssmClient.queryObjectList("getNonEquipmentKeyByOderNumber", ssmParam);

                if ((list == null) || (list.isEmpty())){
                    return false;
                }

            }

            List<RevenueKey> keys = new ArrayList<RevenueKey>();

            for (Map<String, Object> item: list) {
                keys.add(
                    new RevenueKey(
                        (String)item.get("CPO_ORD_NUM")
                        , ((BigDecimal)item.get("DS_CPO_CONFIG_PK")).toPlainString()
                        , (String)item.get("CPO_DTL_LINE_NUM")
                        , (String)item.get("CPO_DTL_LINE_SUB_NUM")
                    )
                );
            }

            List<REV_RECOG_DTL_WRKTMsg> targetList = new ArrayList<REV_RECOG_DTL_WRKTMsg>();

            for (REV_RECOG_DTL_WRKTMsg dtlWrk : dtlWrkTMsgList){
                if (hasValue(dtlWrk.refCpoDtlLineNum) && hasValue(dtlWrk.refCpoDtlLineSubNum)) {
                    continue;
                }
                targetList.add(dtlWrk);
            }

            waitConfigList.add(new RevRecogExecutorInner(keys, hdrWrkTMsg, targetList));

            return true;

        }
        // QC#20343 Add End

        // get same line set item parent data and intangible component data
        private List<REV_RECOG_DTL_WRKTMsg> setShipInfoToParentAndIntangibleComponent(List<REV_RECOG_DTL_WRKTMsg> dtlWrkList, List<SoDivideKey> divKeyList, List<SHPG_PLNTMsg> shpgPlnList) {
            //set So#, Pro#, BOL# of Intangible Item first Inv Bol data in same set
            Map<String, Integer> divKeyMap = new HashMap<String, Integer>();
            Map<String, String> setShpgPlnNumMap = new HashMap<String, String>();
            Map<String, Integer> soBolProMap = new HashMap<String, Integer>();
            Map<String, Integer> intangbleSoBolProMap = new HashMap<String, Integer>();

            //Get minimum divKeyList row num group by SetShpgPlnNum
            //   1.Make Map SO#+BOL#+PRO# -> divKeyList row num         = divKeyMap
            for (int i = 0; i < divKeyList.size(); i++) {
                String key = divKeyList.get(i).getSoNum() + "-" + divKeyList.get(i).getBolNum() + "-" + divKeyList.get(i).getProNum();
                if (!divKeyMap.containsKey(key)) {
                    divKeyMap.put(key, i);
                }
            }
            //   2.Make Map SHPG_PLN_NUM  -> SetShpgPlnNum              = setShpgPlnNumMap
            for (SHPG_PLNTMsg shpgPlnTMsg : shpgPlnList) {
                if (hasValue(shpgPlnTMsg.setShpgPlnNum)) {
                    setShpgPlnNumMap.put(shpgPlnTMsg.shpgPlnNum.getValue(), shpgPlnTMsg.setShpgPlnNum.getValue());
                }
            }
            //   3.Make Map SetShpgPlnNum -> minimum divKeyList row num = soBolProMap or intangbleSoBolProMap
            for (REV_RECOG_DTL_WRKTMsg dtlWrk : dtlWrkList) {
                if (!hasValue(dtlWrk.soNum.getValue()) && !hasValue(dtlWrk.bolNum.getValue()) && !hasValue(dtlWrk.proNum.getValue())) {
                    continue;
                }
                String setShpgPlnNum = setShpgPlnNumMap.get(dtlWrk.shpgPlnNum.getValue());
                if (!hasValue(setShpgPlnNum)) {
                    continue;
                }
                String key = dtlWrk.soNum.getValue() + "-" + dtlWrk.bolNum.getValue() + "-" + dtlWrk.proNum.getValue();
                if (Y.equals(dtlWrk.invtyCtrlFlg.getValue())) {
                    if (soBolProMap.containsKey(setShpgPlnNum)) {
                        soBolProMap.put(setShpgPlnNum, Math.min(divKeyMap.get(key), soBolProMap.get(setShpgPlnNum)));
                    } else {
                        soBolProMap.put(setShpgPlnNum, divKeyMap.get(key));
                    }
                } else if (N.equals(dtlWrk.invtyCtrlFlg.getValue())) {
                    if (intangbleSoBolProMap.containsKey(setShpgPlnNum)) {
                        intangbleSoBolProMap.put(setShpgPlnNum, Math.min(divKeyMap.get(key), intangbleSoBolProMap.get(setShpgPlnNum)));
                    } else {
                        intangbleSoBolProMap.put(setShpgPlnNum, divKeyMap.get(key));
                    }
                }
            }
            //   set So#, Pro#, BOL#
            //   1.get shpgPlnNum       from dtlWrkList
            //   2.get SetShpgPlnNum    from setShpgPlnNumMap
            //   3.get So#, Pro#, BOL#  from soBolProMap or intangbleSoBolProMap
            for (REV_RECOG_DTL_WRKTMsg dtlWrk : dtlWrkList) {
                String setShpgPlnNum = null;
                if (isSetParentLineSubNum(dtlWrk.cpoDtlLineSubNum.getValue())) {
                    setShpgPlnNum = dtlWrk.shpgPlnNum.getValue();
                } else {
                    setShpgPlnNum = setShpgPlnNumMap.get(dtlWrk.shpgPlnNum.getValue());
                }
                if (hasValue(setShpgPlnNum) && N.equals(dtlWrk.invtyCtrlFlg.getValue())) {
                    Integer divKeyNum = null;
                    if (soBolProMap.containsKey(setShpgPlnNum)) {
                        divKeyNum = soBolProMap.get(setShpgPlnNum);
                    } else {
                        divKeyNum = intangbleSoBolProMap.get(setShpgPlnNum);
                    }
                    if (divKeyNum != null) {
                        SoDivideKey divKey = divKeyList.get(divKeyNum);
                        setValue(dtlWrk.soNum,  divKey.getSoNum());
                        setValue(dtlWrk.bolNum, divKey.getBolNum());
                        setValue(dtlWrk.proNum, divKey.getProNum());
                    }

                }
            }
            return dtlWrkList;
        }

        private void setShpgStsCd(REV_RECOG_DTL_WRKTMsg dtlWrk, SHPG_PLNTMsg shpgPln) {

            setValue(dtlWrk.revRecogFlg, Y);

            if (hasValue(dtlWrk.revRecogShpgStsCd)) {
                setValue(shpgPln.soCloseFlg, Y);
                setValue(shpgPln.shpgStsCd,  dtlWrk.revRecogShpgStsCd);
            } else {
                setValue(shpgPln.shpgStsCd,  SHPG_STS.SHIPPED);
            }
        }

        private REV_RECOG_HDR_WRKTMsg toHdrWrk(ResultSet rs) throws SQLException {

            final REV_RECOG_HDR_WRKTMsg hdrWrk = new REV_RECOG_HDR_WRKTMsg();
            setValue(hdrWrk.glblCmpyCd,          rs.getString("GLBL_CMPY_CD"));
            setValue(hdrWrk.cpoOrdNum,           rs.getString("CPO_ORD_NUM"));
            setValue(hdrWrk.cpoOrdTpCd,          rs.getString("CPO_ORD_TP_CD"));
            setValue(hdrWrk.cpoSrcTpCd,          rs.getString("CPO_SRC_TP_CD"));
            setValue(hdrWrk.reBillPairCpoOrdNum, rs.getString("RE_BILL_PAIR_CPO_ORD_NUM"));
            setValue(hdrWrk.dsCpoConfigPk,       rs.getBigDecimal("DS_CPO_CONFIG_PK"));
            setValue(hdrWrk.dsOrdCatgCd,         rs.getString("DS_ORD_CATG_CD"));
            setValue(hdrWrk.dsOrdTpCd,           rs.getString("DS_ORD_TP_CD"));

            return hdrWrk;
        }

        private boolean updateShpgPln(REV_RECOG_DTL_WRKTMsg dtlWrk, SHPG_PLNTMsg shpgPln) {

            setValue(shpgPln.revRecogFlg, Y);

            setValue(shpgPln.soNum,  dtlWrk.soNum.getValue());
            setValue(shpgPln.bolNum, dtlWrk.bolNum.getValue());
            setValue(shpgPln.proNum, dtlWrk.proNum.getValue());

            // ***** update
            update(shpgPln);

            if (!RTNCD_NORMAL.equals(shpgPln.getReturnCode())) {
                throw new S21AbendException(NWCM0051E, toArray(shpgPln.shpgPlnNum.getValue()));
            }

            // Change Status Number List
            if (!shpgNumList.contains(shpgPln.shpgPlnNum.getValue())) {
                shpgNumList.add(shpgPln.shpgPlnNum.getValue());
            }
            if (shpgNumList.size() == MAX_LIST_SIZE) {
                if (!callStsUpdApi(dtlWrk.cpoOrdNum.getValue())) {
                    return false;
                }
            }
            return true;

        }

        // update status for intangible item
        private boolean updateStatusIntangibleItem(REV_RECOG_DTL_WRKTMsg dtlWrk, List<SetItemData> setCompList, SHPG_PLNTMsg shpgPln, List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {

            boolean revRecogFlg = false;
            for (SetItemData setItemData : setCompList) {
                BigDecimal setItemQty = setItemData.getSetItemQty();
                BigDecimal ordQty     = setItemData.getOrdQty();
                BigDecimal childQty  = setItemData.getChildMdseQty();
                if (Y.equals(setItemData.getInvtyCtrlFlg()) && ordQty.compareTo(ZERO) != 0 && ordQty.compareTo(setItemQty.multiply(childQty)) == 0) {
                    setShpgStsCd(dtlWrk, shpgPln);
                    if (!updateShpgPln(dtlWrk, shpgPln)) {
                        return false;
                    }
                    revRecogFlg = true;
                    break;
                }
            }
            if (!revRecogFlg) {
                dtlWrk.revRecogFlg.setValue(N);
            }
            return true;
        }

        /**
         * Call NWZC188001 Display Order Line Status Update API
         * @param cpoOrdNum String
         * @return boolean 
         */
        private boolean callStsUpdApi(String cpoOrdNum) {

            if (shpgNumList.size() == 0) {
                return true;
            }

            NWZC188001PMsg pMsg = new NWZC188001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, cpoOrdNum);

            for (int i = 0; i < shpgNumList.size(); i++) {
                NWZC188001_shpgPlnNumListPMsg shpgNumMsg = pMsg.shpgPlnNumList.no(i);
                ZYPEZDItemValueSetter.setValue(shpgNumMsg.shpgPlnNum, shpgNumList.get(i));
                pMsg.shpgPlnNumList.setValidCount(i + 1);
            }

            new NWZC188001().execute(pMsg, ONBATCH_TYPE.BATCH);

            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                    S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                return false;
            }

            for (int i = 0; i < pMsg.shpgPlnNumList.getValidCount(); i++) {
                // shpg List: Error check
                NWZC188001_shpgPlnNumListPMsg shpgListPmsg = pMsg.shpgPlnNumList.no(i);

                if (hasValue(shpgListPmsg.xxMsgId)) {
                    S21InfoLogOutput.println(shpgListPmsg.xxMsgId.getValue());
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Revenue Recognition Work Table Creator.
     * 
     * <pre>
     * Revenue Recognition Work Table
     * 1. REV_RECOG_HDR_WRK
     * 2. REV_RECOG_DTL_WRK
     * </pre>
     * 
     * @author K.Tajima
     */
    private final class RevRecogWrkCreator extends S21SsmBooleanResultSetHandlerSupport implements RevRecogIF {

        private static final String BATCH_EXEC_NIGHT = "1";

        private final S21LRUMap<String, Integer>      shipCpltCache          = new S21LRUMap<String, Integer>();
        private final S21LRUMap<String, List<String>> revRecogShpgStsCdCache = new S21LRUMap<String, List<String>>();

        public boolean exec() {
            final String methodNm = "exec";
            debugMethodStart(getClass(), methodNm);

            try {

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                // get credit&rebill dummy wh cd
                String crRebillDummyWhCdCsv = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_CR_AND_BILL_ONLY_DUMMY_WH_CD, glblCmpyCd);
                crRebillDummyWhCdList = new ArrayList<String>();
                if (crRebillDummyWhCdCsv != null) {
                    String[] crRebillDummyWhCd = crRebillDummyWhCdCsv.split(",");
                    crRebillDummyWhCdList = Arrays.asList(crRebillDummyWhCd);
                }

                // get revenue recognition target order line category
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("ordLineCtxTpCd", CONST_ORD_LINE_CTX_TP_REV_RECOG_EXEM);
                List<String> dsOrdLineCatgList = (List<String>) ssmClient.queryObjectList("getRevenueRecognitionOrderLineCategoryList", ssmParam);

                // get data
                ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("batchExecMode", batchExecMode);
                ssmParam.put("dsOrdLineCatgCdList", dsOrdLineCatgList);
                ssmParam.put("crRebillDummyWhCdList", crRebillDummyWhCdList);

                // 2019/09/04 QC#53306 Add Start
                ssmParam.put("ordLineStsCdCancel", ORD_LINE_STS.CANCELLED);
                // 2019/09/04 QC#53306 Add End

                return (Boolean) ssmClient.queryObject("getRevRecogTargetData", ssmParam, this);

            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            final String methodNm = "doProcessQueryResult";
            debugMethodStart(getClass(), methodNm);

            try {
                if (!rs.next()) {
                    return false;
                }

                return createRevRecogWrk(rs);

            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        private boolean createRevRecogWrk(ResultSet rs) throws SQLException {
            final String methodNm = "createRevRecogWrk";
            debugMethodStart(getClass(), methodNm);

            try {

                // REV_RECOG_HDR_WRK, REV_RECOG_DTL_WRK
                final List<REV_RECOG_HDR_WRKTMsg> hdrWrkList = new ArrayList<REV_RECOG_HDR_WRKTMsg>();
                final List<REV_RECOG_DTL_WRKTMsg> dtlWrkList = new ArrayList<REV_RECOG_DTL_WRKTMsg>();

                final Set<String> setShpgPlnNumSet = new HashSet<String>();
                String prevCpoOrdNum     = null;
                String prevCpoDtlLineNum = null;
                BigDecimal prevDsCpoConfigPk = null;

                NWXC100001CheckHoldCondition dwxc100001checkHoldCondition = new NWXC100001CheckHoldCondition();

                do {

                    final REV_RECOG_HDR_WRKTMsg hdrWrk  = new REV_RECOG_HDR_WRKTMsg();
                    final REV_RECOG_DTL_WRKTMsg dtlWrk  = new REV_RECOG_DTL_WRKTMsg();
                    final SHPG_PLNTMsg          shpgPln = new SHPG_PLNTMsg();
                    toTMsg(rs, hdrWrk, dtlWrk, shpgPln);

                    String cpoOrdNum         = hdrWrk.cpoOrdNum.getValue();
                    String cpoOrdTpCd        = dtlWrk.cpoOrdTpCd.getValue();
                    String cpoDtlLineNum     = dtlWrk.cpoDtlLineNum.getValue();
                    String cpoDtlLineSubNum  = dtlWrk.cpoDtlLineSubNum.getValue();
                    String shpgPlnNum        = dtlWrk.shpgPlnNum.getValue();
                    String shpgStsCd         = dtlWrk.shpgStsCd.getValue();
                    String setMdseCd         = dtlWrk.setMdseCd.getValue();
                    String shpgPlnShipCpltCd = dtlWrk.shpgPlnShipCpltCd.getValue();
                    String invtyCtrlFlg      = dtlWrk.invtyCtrlFlg.getValue();
                    String poReqFlg          = dtlWrk.poReqFlg.getValue();
                    String revRecogMethCd = rs.getString("REV_RECOG_METH_CD");
                    String setItemShipCpltFlg = rs.getString("SET_ITEM_SHIP_CPLT_FLG");
                    String rtlWhCd = getRtlWhCd(dtlWrk);
                    if (rtlWhCd == null) {
                        continue;
                    }
                    BigDecimal dsCpoConfigPk = hdrWrk.dsCpoConfigPk.getValue();
                    // revRecogShpgStsCd
                    final List<String> revRecogShpgStsCdList = getRevRecogShpgStsCdList(hdrWrk.billToCustCd.getValue(), revRecogMethCd);
                    if (isEmpty(revRecogShpgStsCdList)) {
                        continue;
                    }
                    setValue(dtlWrk.revRecogShpgStsCd, revRecogShpgStsCdList.get(0));
                    if (!crRebillDummyWhCdList.contains(rtlWhCd)) {
                        if (!isIntangibleItem(dtlWrk)) {
                            if (!isSetParentLineSubNum(cpoDtlLineSubNum)) {
                                if (!revRecogShpgStsCdList.contains(shpgStsCd)) {
                                    continue;
                                }
                            }
                        }

                        if (!isLackAssigned(dtlWrk)) {
                            continue;
                        }

                        // Ship Complete Exception
                        if (hasValue(shpgPlnShipCpltCd) && N.equals(invtyCtrlFlg) && N.equals(poReqFlg)) {
                            if (!excludeForShipCplt(dtlWrk)) {
                                continue;
                            }
                        }

                        if (Y.equals(setItemShipCpltFlg)) {
                            if (asList(SALES, EXPENSE, TRIAL, LOAN, RENTAL_DS).contains(cpoOrdTpCd)) {

                                if (!excludeForSetShipCplt(dtlWrk, revRecogShpgStsCdList)) {
                                    continue;
                                }
                            }
                        }
                    }

                    if (isBillingBlock(dwxc100001checkHoldCondition, dtlWrk, shpgPln)) {
                        continue;
                    }

                    if (hasValue(shpgPlnNum)) {

                        if (isSetParentLineSubNum(cpoDtlLineSubNum)) {
                            setShpgPlnNumSet.add(shpgPlnNum);

                        } else if (hasValue(setMdseCd)) {
                            String setShpgPlnNum = shpgPln.setShpgPlnNum.getValue();
                            if (!setShpgPlnNumSet.contains(setShpgPlnNum)) {
                                setShpgPlnNumSet.add(setShpgPlnNum);
                                final REV_RECOG_DTL_WRKTMsg setParentDtlWrk = createSetParentDtlWrkTMsg(setShpgPlnNum, dtlWrk);
                                if (setParentDtlWrk == null) {
                                    continue;
                                } else {
                                    dtlWrkList.add(setParentDtlWrk);
                                }
                            }
                        }

                    } else {
                        if (!cpoOrdNum.equals(prevCpoOrdNum) || !cpoDtlLineNum.equals(prevCpoDtlLineNum)) {
                            if (hasValue(setMdseCd)) {
                                continue;
                            }
                        }
                    }

                    // --------------------------------------------------
                    // Insert Revenue Recognition Work Table.
                    // --------------------------------------------------
                    if (!cpoOrdNum.equals(prevCpoOrdNum) || !dsCpoConfigPk.equals(prevDsCpoConfigPk)) {
                        hdrWrkList.add(hdrWrk);
                    }

                    dtlWrkList.add(dtlWrk);

                    if (dtlWrkList.size() >= 5000) {
                        // ***** insert
                        insert(hdrWrkList, dtlWrkList);
                        clear(hdrWrkList, dtlWrkList);
                    }

                    prevCpoOrdNum     = cpoOrdNum;
                    prevCpoDtlLineNum = cpoDtlLineNum;
                    prevDsCpoConfigPk = dsCpoConfigPk;

                } while (rs.next());

                // ***** insert
                return insert(hdrWrkList, dtlWrkList);

            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        private REV_RECOG_DTL_WRKTMsg createSetParentDtlWrkTMsg(String setShpgPlnNum, REV_RECOG_DTL_WRKTMsg dtlWrk) {

            SHPG_PLNTMsg shpgPln = new SHPG_PLNTMsg();
            setValue(shpgPln.glblCmpyCd, glblCmpyCd);
            setValue(shpgPln.shpgPlnNum, setShpgPlnNum);
            shpgPln = (SHPG_PLNTMsg) findByKey(shpgPln);
            if (shpgPln == null) {
                return null;
            }

            // REV_RECOG_DTL_WRK
            final REV_RECOG_DTL_WRKTMsg parentDtlWrk = new REV_RECOG_DTL_WRKTMsg();
            EZDMsg.copy(dtlWrk, null, parentDtlWrk, null);
            setValue(parentDtlWrk.cpoDtlLineNum,    shpgPln.trxLineNum);
            setValue(parentDtlWrk.cpoDtlLineSubNum, shpgPln.trxLineSubNum);
            setValue(parentDtlWrk.mdseCd,           shpgPln.mdseCd);
            setValue(parentDtlWrk.shpgPlnNum,       setShpgPlnNum);
            setValue(parentDtlWrk.shpgStsCd,        SHPG_STS.VALIDATED);
            setValue(parentDtlWrk.invtyCtrlFlg,     N);
            setValue(parentDtlWrk.invtyValFlg,      N);
            setValue(parentDtlWrk.revRecogFlg,      N);
            setValue(parentDtlWrk.poReqFlg,         shpgPln.poReqFlg);
            parentDtlWrk.setMdseCd.clear();
            parentDtlWrk.soNum.clear();
            parentDtlWrk.bolNum.clear();
            parentDtlWrk.proNum.clear();

            return parentDtlWrk;
        }

        private boolean excludeForShipCplt(REV_RECOG_DTL_WRKTMsg dtlWrk) {

            String cpoOrdNum         = dtlWrk.cpoOrdNum.getValue();
            String shpgPlnShipCpltCd = dtlWrk.shpgPlnShipCpltCd.getValue();

            final String cacheKey = createCacheKey(glblCmpyCd, cpoOrdNum, shpgPlnShipCpltCd);

            Integer shipCpltCnt = (Integer) shipCpltCache.get(cacheKey);

            if (shipCpltCnt == null) {

                final Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd",         glblCmpyCd);
                ssmParam.put("trxSrcTpWholeSales", WHOLE_SALES);
                ssmParam.put("cpoOrdNum",          cpoOrdNum);
                ssmParam.put("shpgStsCd",          SHPG_STS.VALIDATED);
                ssmParam.put("shpgStsShipped",     SHPG_STS.SHIPPED);
                ssmParam.put("shpgStsArrived",     SHPG_STS.ARRIVED);
                ssmParam.put("shpgCpltCd",         shpgPlnShipCpltCd);

                shipCpltCnt = (Integer) ssmClient.queryObject("getExcludeCountForShipCplt", ssmParam);
                shipCpltCache.put(cacheKey, shipCpltCnt);
            }

            return shipCpltCnt == 0;
        }

        private boolean excludeForSetShipCplt(REV_RECOG_DTL_WRKTMsg dtlWrk, List<String> shpgStsList) {

            final Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd",         glblCmpyCd);
            ssmParam.put("cpoOrdNum",          dtlWrk.cpoOrdNum.getValue());
            ssmParam.put("cpoDtlLineNum",      dtlWrk.cpoDtlLineNum.getValue());
            ssmParam.put("shpgPlnNum",         dtlWrk.shpgPlnNum.getValue());

            List<Map<String, String>> result = (List<Map<String, String>>) ssmClient.queryObjectList("getSetShipCpltDetail", ssmParam);
            final List<String> cpoOrdTpList = asList(new String[] {TRIAL_TO_SALES, LOAN_TO_SALES, RENTAL_TO_SALES });

            for (Map<String, String> resultMap : result) {
                String invtyCtrlFlg = resultMap.get("INVTY_CTRL_FLG");
                String autoLicAllocFlg = resultMap.get("AUTO_LIC_ALLOC_FLG");
                String shpgStsCd = resultMap.get("SHPG_STS_CD");
                String poReqFlg = resultMap.get("PO_REQ_FLG");
                String cpoHldFlg = resultMap.get("CPO_HLD_FLG");
                String cpoDtlHldFlg = resultMap.get("CPO_DTL_HLD_FLG");
                String shipPlnHldFlg = resultMap.get("SHIP_PLN_HLD_FLG");

                if (Y.equals(invtyCtrlFlg)) {
                    if (!shpgStsList.contains(shpgStsCd)) {
                        return false;
                    }
                } else {

                    // Vendor Drop
                    if (Y.equals(poReqFlg)) {
                        if (!shpgStsList.contains(shpgStsCd)) {
                            return false;
                        }
                    } else {

                        if (!shpgStsList.contains(shpgStsCd)) {
                            return false;
                        }

                        if (asList(cpoHldFlg, cpoDtlHldFlg, shipPlnHldFlg).contains(Y)) {
                            return false;
                        }

                        if (Y.equals(autoLicAllocFlg)) {
                            final Map<String, Object> ssmLakParam = new HashMap<String, Object>();
                            ssmLakParam.put("glblCmpyCd",       dtlWrk.glblCmpyCd.getValue());
                            ssmLakParam.put("mdseCd",           resultMap.get("MDSE_CD"));
                            ssmLakParam.put("cpoOrdNum",        resultMap.get("CPO_ORD_NUM"));
                            ssmLakParam.put("cpoDtlLineNum",    resultMap.get("CPO_DTL_LINE_NUM"));
                            ssmLakParam.put("cpoDtlLineSubNum", resultMap.get("CPO_DTL_LINE_SUB_NUM"));
                            ssmLakParam.put("lakStsCd",         LAK_STS.USED);
                            ssmLakParam.put("cpoOrdTpList",     cpoOrdTpList);

                            if ((Integer) ssmClient.queryObject("getLakAssignedNum", ssmLakParam) == 0) {
                                return false;
                            }
                        }
                    }
                }
            }

            return true;
        }

        private List<String> getRevRecogShpgStsCdList(String billToCustCd, String revRecogMethCd) {

            final String cacheKey = createCacheKey(glblCmpyCd, revRecogMethCd);

            List<String> revRecogShpgStsCdList = (List<String>) revRecogShpgStsCdCache.get(cacheKey);

            if (revRecogShpgStsCdList == null) {

                final Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd",      glblCmpyCd);
                if (!hasValue(revRecogMethCd)) {
                    revRecogMethCd = REV_RECOG_METH.BOL;
                }
                ssmParam.put("revRecogMethCd",  revRecogMethCd);

                revRecogShpgStsCdList = (List<String>) ssmClient.queryObjectList("getRevRecogShpgStsCdList", ssmParam);
                revRecogShpgStsCdCache.put(cacheKey, revRecogShpgStsCdList);
            }

            return revRecogShpgStsCdList;
        }

        private boolean insert(List<REV_RECOG_HDR_WRKTMsg> hdrWrkList, List<REV_RECOG_DTL_WRKTMsg> dtlWrkList) {

            // HDR
            if (!isEmpty(hdrWrkList)) {
                final int resRecords = S21FastTBLAccessor.insert(hdrWrkList.toArray(new EZDTMsg[0]));
                debug(getClass(), "S21FastTBLAccessor.insert(", hdrWrkList.get(0).getTableName(), "). results = [", resRecords, "]lines.");
                if (resRecords != hdrWrkList.size()) {
                    throw new S21AbendException(NWCM0026E);
                }
            }

            // DTL
            if (!isEmpty(dtlWrkList)) {
                for (REV_RECOG_DTL_WRKTMsg dtlWrk : dtlWrkList) {
                    if (!hasValue(dtlWrk.shpgPlnNum)) {
                        setValue(dtlWrk.shpgPlnNum, "0000");
                    }
                }
                final int resRecords = S21FastTBLAccessor.insert(dtlWrkList.toArray(new EZDTMsg[0]));
                debug(getClass(), "S21FastTBLAccessor.insert(", dtlWrkList.get(0).getTableName(), "). results = [", resRecords, "]lines.");
                if (resRecords != dtlWrkList.size()) {
                    throw new S21AbendException(NWCM0027E);
                }
            }

            return true;
        }

        /**
         * This method investigate the merchandise code of<br>
         * REV_RECOG_DTL_WRKTMsg is needing LAK Assign or not.<br>
         * If the merchandise needs LAK Assign, it investigates LAK
         * Repository Status Table. If the merchandise doesn't need
         * LAK, it returns true. If the merchandise need LAK and LAK
         * is assigned already, it returns true. If the merchandise
         * need LAK and LAK is not assigned already, it returns false.
         * @param dtlWrk CPO_DTL data
         * @return retFlg true/false(Detail is above)
         */
        private boolean isLackAssigned(final REV_RECOG_DTL_WRKTMsg dtlWrk) {

            // TRIAL_TO_SALES, LOAN_TO_SALES
            // Mod Start 2017/10/24 QC#21773
            //final List<String> cpoOrdTpList = asList(new String[] {TRIAL_TO_SALES, LOAN_TO_SALES, RENTAL_TO_SALES, DEBIT, CREDIT });
            final List<String> cpoOrdTpList = asList(new String[] {TRIAL_TO_SALES, LOAN_TO_SALES, RENTAL_TO_SALES, DEBIT, CPO_ORD_TP.CREDIT });
            // Mod End 2017/10/24 QC#21773

            if (cpoOrdTpList.contains(dtlWrk.cpoOrdTpCd.getValue())) {
                return true;
            }

            final MDSETMsg mdseTMsg = getMdse(dtlWrk.mdseCd.getValue());
            if (mdseTMsg != null) {
                if (Y.equals(mdseTMsg.autoLicAllocFlg.getValue())) {

                    final Map<String, Object> ssmParam = new HashMap<String, Object>();
                    ssmParam.put("glblCmpyCd", dtlWrk.glblCmpyCd.getValue());
                    ssmParam.put("mdseCd", dtlWrk.mdseCd.getValue());
                    ssmParam.put("cpoOrdNum", dtlWrk.cpoOrdNum.getValue());
                    ssmParam.put("cpoDtlLineNum", dtlWrk.cpoDtlLineNum.getValue());
                    ssmParam.put("cpoDtlLineSubNum", dtlWrk.cpoDtlLineSubNum.getValue());
                    ssmParam.put("lakStsCd", LAK_STS.USED);
                    ssmParam.put("cpoOrdTpList", cpoOrdTpList);

                    Integer lakAssignedNum = (Integer) ssmClient.queryObject("getLakAssignedNum", ssmParam);
                    if (lakAssignedNum == 0) {
                        return false;
                    }
                }
            }

            return true;
        }

        private void toTMsg(ResultSet rs, REV_RECOG_HDR_WRKTMsg hdrWrk, REV_RECOG_DTL_WRKTMsg dtlWrk, SHPG_PLNTMsg shpgPln) throws SQLException {

            // REV_RECOG_HDR_WRK
            setValue(hdrWrk.glblCmpyCd,          glblCmpyCd);
            setValue(hdrWrk.cpoOrdTpCd,          rs.getString("CPO_ORD_TP_CD"));
            setValue(hdrWrk.cpoOrdNum,           rs.getString("CPO_ORD_NUM"));
            setValue(hdrWrk.reBillPairCpoOrdNum, rs.getString("RE_BILL_PAIR_CPO_ORD_NUM"));
            setValue(hdrWrk.cpoSrcTpCd,          rs.getString("CPO_SRC_TP_CD"));
            setValue(hdrWrk.billToCustCd,        rs.getString("BILL_TO_CUST_CD"));
            setValue(hdrWrk.dsOrdCatgCd,         rs.getString("DS_ORD_CATG_CD"));
            setValue(hdrWrk.dsOrdTpCd,           rs.getString("DS_ORD_TP_CD"));
            setValue(hdrWrk.dsCpoConfigPk,       rs.getBigDecimal("DS_CPO_CONFIG_PK"));

            // REV_RECOG_DTL_WRK
            setValue(dtlWrk.glblCmpyCd,          glblCmpyCd);
            setValue(dtlWrk.cpoOrdNum,           rs.getString("CPO_ORD_NUM"));
            setValue(dtlWrk.cpoOrdTpCd,          rs.getString("CPO_ORD_TP_CD"));
            setValue(dtlWrk.cpoDtlLineNum,       rs.getString("CPO_DTL_LINE_NUM"));
            setValue(dtlWrk.cpoDtlLineSubNum,    rs.getString("CPO_DTL_LINE_SUB_NUM"));
            setValue(dtlWrk.cpoOrdTpCd,          rs.getString("CPO_ORD_TP_CD"));
            setValue(dtlWrk.mdseCd,              rs.getString("MDSE_CD"));
            setValue(dtlWrk.setMdseCd,           rs.getString("SET_MDSE_CD"));
            setValue(dtlWrk.shpgPlnNum,          rs.getString("SHPG_PLN_NUM"));
            setValue(dtlWrk.shpgStsCd,           rs.getString("SHPG_STS_CD"));
            setValue(dtlWrk.shpgPlnShipCpltCd,   rs.getString("SHIP_CPLT_CD"));
            setValue(dtlWrk.invTpCd,             rs.getString("INV_TP_CD"));
            setValue(dtlWrk.trialLoanFlg,        rs.getString("TRIAL_LOAN_FLG"));
            setValue(dtlWrk.trxRsnCd,            rs.getString("TRX_RSN_CD"));
            setValue(dtlWrk.inTrnstOutFlg,       rs.getString("IN_TRNST_OUT_FLG"));
            setValue(dtlWrk.trxCd,               rs.getString("TRX_CD"));
            setValue(dtlWrk.invtyCtrlFlg,        rs.getString("INVTY_CTRL_FLG"));
            setValue(dtlWrk.invtyValFlg,         rs.getString("INVTY_VAL_FLG"));
            setValue(dtlWrk.revRecogFlg,         Y);
            setValue(dtlWrk.poReqFlg,            rs.getString("PO_REQ_FLG"));
            setValue(dtlWrk.soNum,               rs.getString("SO_NUM"));
            setValue(dtlWrk.bolNum,              rs.getString("BOL_NUM"));
            setValue(dtlWrk.proNum,              rs.getString("PRO_NUM"));
            setValue(dtlWrk.refCpoDtlLineNum,    rs.getString("REF_CPO_DTL_LINE_NUM"));
            setValue(dtlWrk.refCpoDtlLineSubNum, rs.getString("REF_CPO_DTL_LINE_SUB_NUM"));
            setValue(dtlWrk.dsOrdLineCatgCd,     rs.getString("DS_ORD_LINE_CATG_CD"));
            setValue(dtlWrk.crRebilCd,           rs.getString("CR_REBIL_CD"));
            setValue(dtlWrk.invtyLocCd,          rs.getString("INVTY_LOC_CD"));
            setValue(dtlWrk.dsCpoConfigPk,       rs.getBigDecimal("DS_CPO_CONFIG_PK"));

            // SHPG_PLN
            setValue(shpgPln.poReqFlg,           rs.getString("PO_REQ_FLG"));
            setValue(shpgPln.setShpgPlnNum,      rs.getString("SET_SHPG_PLN_NUM"));
        }
    }

    /**
     * Revenue Recognition Work Table Deletor.
     * 
     * <pre>
     * Revenue Recognition Work Table
     * 1. REV_RECOG_HDR_WRK
     * 2. REV_RECOG_DTL_WRK
     * </pre>
     * 
     * @author K.Tajima
     */
    private final class RevRecogWrkDeletor implements RevRecogIF {

        public boolean exec() {
            final String methodNm = "exec";
            debugMethodStart(getClass(), methodNm);

            try {

                final List<EZDTMsg> wrkTMsgList = new ArrayList<EZDTMsg>();
                wrkTMsgList.add(new REV_RECOG_HDR_WRKTMsg());
                wrkTMsgList.add(new REV_RECOG_DTL_WRKTMsg());

                for (EZDTMsg wrkTMsg : wrkTMsgList) {

                    wrkTMsg.setValue("glblCmpyCd", -1, glblCmpyCd);

                    // ***** removeByPartialValue
                    removeByPartialValue(wrkTMsg, new String[]{"glblCmpyCd"});

                    final String rtnCd = wrkTMsg.getReturnCode();
                    debug(getClass(), "S21FastTBLAccessor.removeByPartialValue(", wrkTMsg.getTableName(), ").getReturnCode = ", rtnCd);

                    if (!RTNCD_NORMAL.equals(rtnCd)) {
                        if (!RTNCD_NOT_FOUND.equals(rtnCd)) {
                            return false;
                        }
                    }
                }

                return true;

            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }
    }

    /**
     * Check Billing Block.
     * @param Hold check Common module
     * @param dtlWrk dtl
     * @param shpgPln Shipping Plan
     * @return true:Billing Block / false:Non block
     */
    private boolean isBillingBlock(NWXC100001CheckHoldCondition dwxc100001checkHoldCondition, REV_RECOG_DTL_WRKTMsg dtlWrk, SHPG_PLNTMsg shpgPln) {

        NWXC100001CheckHoldConditionBean bean = new NWXC100001CheckHoldConditionBean(
                dtlWrk.glblCmpyCd.getValue(),
                dtlWrk.cpoOrdNum.getValue(),
                dtlWrk.cpoDtlLineNum.getValue(),
                dtlWrk.cpoDtlLineSubNum.getValue(),
                TRX_SRC_TP.WHOLE_SALES,
                dtlWrk.shpgPlnNum.getValue(),
                dtlWrk.poReqFlg.getValue(),
                dtlWrk.mdseCd.getValue(),
                dtlWrk.shpgPlnShipCpltCd.getValue(),
                shpgPln.setShpgPlnNum.getValue()
                );

        boolean ret = dwxc100001checkHoldCondition.checkHoldCondition(bean, NWXC100001CheckHoldCondition.PROCESS_MODE_REVENUE);

        String errMsgId = dwxc100001checkHoldCondition.getMessageId();
        if (errMsgId != null) {
            throw new S21AbendException(errMsgId, toArray("Revenue Recognition"));
        }

        return !ret;
    }

      private String getRtlWhCd(REV_RECOG_DTL_WRKTMsg tmsg) {

          String rtlWhCd = null;
          String invtyLocCd = tmsg.invtyLocCd.getValue();

          if (ZYPConstant.FLG_ON_Y.equals(tmsg.poReqFlg.getValue())) {
              return "DS";
          }

          if (crRebillDummyWhCdList.contains(invtyLocCd)) {
              return invtyLocCd;
          }

          if (hasValue(invtyLocCd)) {
              String cacheKey = createCacheKey("getRtlWhCd", invtyLocCd);
              Map<String, Object> ssmParam = new HashMap<String, Object>();
              ssmParam.put("glblCmpyCd", glblCmpyCd);
              ssmParam.put("invtyLocCd", invtyLocCd);

              rtlWhCd = (String) searchObject("getRtlWhCd", ssmParam, cacheKey);
            if (rtlWhCd == null) {
                S21InfoLogOutput.println(NWCM0112E, new String[] {"RTL_WH_CD", invtyLocCd });
                return null;
            }

          } else {
              CPO_DTLTMsg dsCpoDtlTMsg = new CPO_DTLTMsg();
              setValue(dsCpoDtlTMsg.glblCmpyCd, glblCmpyCd);
            setValue(dsCpoDtlTMsg.cpoOrdNum, tmsg.cpoOrdNum);
            setValue(dsCpoDtlTMsg.cpoDtlLineNum, tmsg.cpoDtlLineNum);
            setValue(dsCpoDtlTMsg.cpoDtlLineSubNum, tmsg.cpoDtlLineSubNum);

            dsCpoDtlTMsg = (CPO_DTLTMsg) findByKey(dsCpoDtlTMsg);
            if (dsCpoDtlTMsg != null) {
                rtlWhCd = dsCpoDtlTMsg.rtlWhCd.getValue();
            }

            if (rtlWhCd == null) {
                S21InfoLogOutput.println(NWCM0112E, new String[] {"RTL_WH_CD", tmsg.cpoOrdNum.getValue() });
                return null;
            }
        }

        return rtlWhCd;
    }

    private Object searchObject(String ssmQuery, Map<String, Object> ssmParam, String cacheKey) {

        Object ssmRes = s21LRUMap.get(cacheKey);
        if (ssmRes == null) {
            ssmRes = ssmClient.queryObject(ssmQuery, ssmParam);
            s21LRUMap.put(cacheKey, ssmRes);
          }
          return ssmRes;
      }

      private Object searchObjectFirstValue(String ssmQuery, Map<String, Object> ssmParam, String cacheKey) {

          Object ssmRes = s21LRUMap.get(cacheKey);
          if (ssmRes == null) {
              List<Object> ssmResList = ssmClient.queryObjectList(ssmQuery, ssmParam);
              if (!ssmResList.isEmpty()) {
                  ssmRes = ssmResList.get(0);
                  s21LRUMap.put(cacheKey, ssmRes);
              }
          }
          return ssmRes;
      }

    /**
     * QC#59039 Add. 
     * Get Merchandise Inventory Cost Percent
     * @param bean NLXC001001GetInventoryItemCostBean
     * @return result Map<String, Object>
     */
    private Map<String, Object> getMdseInventoryCostPercent(NLXC001001GetInventoryItemCostBean bean) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", bean.getGlblCmpyCd());
        param.put("invtyLocCd", bean.getInvtyLocCd());

        Map<String, Object> result = (Map) ssmClient.queryObject("getMdseInventoryCostPercent", param);

        if (result == null) {
            return null;
        } else {
            if (result.get("MDSE_COST_TP_CD") == null) {
                result.put("MDSE_COST_TP_CD", MDSE_COST_TP.STANDARD_COST);
            }
            if (result.get("MDSE_INVTY_COST_PCT") == null) {
                result.put("MDSE_INVTY_COST_PCT", BigDecimal.valueOf(100));
            }
        }

        return result;
    }
}
