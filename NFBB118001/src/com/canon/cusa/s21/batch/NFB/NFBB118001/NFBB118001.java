package com.canon.cusa.s21.batch.NFB.NFBB118001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttribute;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CM_AP_INV_DTLTMsg;
import business.db.CM_AP_INV_DTLTMsgArray;
import business.db.CM_AP_INV_HDRTMsg;
import business.db.CM_INV_ACCT_DISTTMsg;
import business.db.CM_INV_PMT_HLDTMsg;
import business.db.CM_INV_PMT_HLDTMsgArray;
import business.db.COA_ACCTTMsg;
import business.db.COA_CONSTTMsg;
import business.parts.NFBCommonBusiness;
import business.parts.NPZC004001PMsg;
import business.parts.NFBCommonBusiness.DefCoaValues;

import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_AP_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_DEF_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_HLD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Create AP Distribution
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   CUSA            Y.Aikawa        Create          N/A
 * 09/08/2016   Hitachi         K.Kasai         Update          QC#14161
 * 09/29/2016   Hitachi         K.Kasai         Update          QC#14799
 * 09/30/2016   Hitachi         K.Kasai         Update          QC#14797
 * 10/19/2016   Hitachi         Y.Tsuchimoto    Update          QC#14810
 * 11/10/2016   Hitachi         E.Kameishi      Update          QC#14800
 * 11/15/2016   Hitachi         T.Tsuchida      Update          QC#15897
 * 11/15/2016   Hitachi         T.Tsuchida      Update          QC#15982
 * 12/09/2016   Hitachi         K.Kasai         Update          QC#16132, 16139
 * 01/23/2017   Hitachi         E.Kameishi      Update          QC#17090
 * 05/26/2017   CITS            R.Shimamoto     Update          QC#18557
 * 06/14/2017   CITS            K.Ogino         Update          QC#19129
 * 06/16/2017   CITS            K.Ogino         Update          QC#19251
 * 07/26/2017   CITS            S.Katsuma       Update          QC#19335
 * 09/12/2017   CITS            K.Ogino         Update          QC#18093
 * 10/20/2017   CITS            T.Tokutomi      Update          QC#21653
 * 11/14/2017   CITS            K.Ogino         Update          QC#21875
 * 12/13/2017   CITS            T.Hakodate      Update          QC#23038
 * 12/14/2017   CITS            T.Hakodate      Update          SOL#060 QC#14858 , 16920 , 17050
 * 12/18/2017   CITS            T.Hakodate      Update          QC#18422
 * 01/30/2018   CITS            K.Kameoka       Update          QC#18602-Sol#102
 * 02/22/2018   Hitachi         Y.Takeno        Update          QC#23505
 * 03/26/2018   Hitachi         Y.Takeno        Update          QC#20316
 * 03/30/2018   Hitachi         Y.Takeno        Update          QC#24277
 * 04/13/2018   Hitachi         Y.Takeno        Update          QC#25531
 * 05/07/2018   Hitachi         Y.Takeno        Update          QC#25828
 * 05/08/2018   Hitachi         Y.Takeno        Update          QC#24981
 * 05/11/2018   Hitachi         Y.Takeno        Update          QC#26080
 * 05/16/2018   Hitachi         Y.Takeno        Update          QC#26080
 * 07/30/2018   Hitachi         Y.Takeno        Update          QC#27025-1
 * 08/29/2018   Hitachi         Y.Takeno        Update          QC#27886
 * 09/05/2018   Fujitsu         S.Ohki          Update          QC#28040
 * 10/18/2018   Hitachi         Y.Takeno        Update          QC#28734
 * 10/24/2018   Hitachi         Y.Takeno        Update          QC#28857
 * 10/29/2018   Hitachi         Y.Takeno        Update          QC#28988
 * 11/01/2018   Hitachi         Y.Takeno        Update          QC#29022
 * 11/08/2018   Hitachi         Y.Takeno        Update          QC#28982
 * 11/15/2018   Hitachi         Y.Takeno        Update          QC#28982
 * 11/20/2018   Fujitsu         S.Ohki          Update          QC#28660
 * 12/20/2018   Hitachi         Y.Takeno        Update          QC#29679
 * 12/21/2018   Hitachi         Y.Takeno        Update          QC#29679
 * 2019/02/01   Fujitsu         H.Ikeda         Update          QC#30142
 * 08/28/2019   Hitachi         Y.Takeno        Update          QC#52280
 * 08/29/2019   Hitachi         Y.Takeno        Update          QC#52280
 * 10/24/2019   Hitachi         Y.Takeno        Update          QC#54275
 * 11/06/2019   Hitachi         Y.Takeno        Update          QC#54275-1
 * 11/11/2019   Hitachi         Y.Takeno        Update          QC#54275-1
 * 02/22/2019   Fujitsu         Y.Matsui        Update          QC#55833
 * 09/29/2020   CITS            R.Kurahashi     Update          QC#57795
 * 06/09/2021   CITS            H.Dimay         Update          QC#58883
 * 2023/01/05   Hitachi         S.Nakatani      Update          QC#60248
 * </pre>
 */

public class NFBB118001 extends S21BatchMain implements NFBB118001Constant {
    /** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** CM_PROC_DT */
    private String cmProcDt;
    // START 2018/10/29 [QC#28988, ADD]
    /** SLS_DT */
    private String slsDt;
    // END   2018/10/29 [QC#28988, ADD]
    /** VAR_CHAR_CONST : AP_LINE_TP_ITEM */
    private String varCharConstApLineTpItem;
    /** VAR_CHAR_CONST : AP_LINE_TP_TAX */
    private String varCharConstApLineTpTax;
    /** VAR_CHAR_CONST : AP_LINE_TP_FREIGHT */
    private String varCharConstApLineTpFreight;
    /** VAR_CHAR_CONST : AP_LINE_TP_VARIANCE */
    private String varCharConstApLineTpVariance;
    // START 2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
    /** VAR_CHAR_CONST : AUTO_PMT_REL_RSN_CD */
    private String varCharConstAutoPmtHldRelRsnCd;
    // END   2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
    // START 2016/11/10 E.Kameishi [QC#14800, ADD]
    /** VAR_CHAR_CONST : AP_VND_CD */
    private String[] varCharConstApVndCdList;
    // END 2016/11/10 E.Kameishi [QC#14800, ADD]
    
    // START 2017/12/13T.Hakodate[QC# 23038 ,  ADD]
    /** VAR_CHAR_CONST : AUTO_HLD_RELEASE_CATG_CD */
    private String[] apInvCatgCdIsEDIList;
    // END 2017 12/13 T.Hakodate [QC#23038, ADD]

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    // START 2018/03/26 [QC#20316, MOD]
    // /** Commit Count (CM_INV_ACCT_HDR Insert) */
    // private int cmInvAcctHdrInsertCommitCount;
    /** Commit Count (CM_AP_INV_HDR Update for register Distribution) */
    private int cmApInvHdrForRegDistCommitCount;
    // END   2018/03/26 [QC#20316, MOD]

    /** Commit Count (CM_INV_ACCT_DIST Insert) */
    private int cmInvAcctDistInsertCommitCount;
    /** Commit Count (CM_INV_PMT_HLD Insert) */
    private int cmInvPmtHldInsertCommitCount;
    /** Commit Count (CM_INV_ACCT_HDR Update) */

    // START 2018/03/26 [QC#20316, DEL]
    // private int cmInvAcctHdrUpdateCommitCount;
    // END   2018/03/26 [QC#20316, DEL]

    /** Commit Count (CM_INV_PMT_HLD Delete) */
    private int cmInvPmtHldDeleteCommitCount;
    // START 2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
    private int cmInvPmtHldUpdateCommitCount = 0;
    // END   2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
    
    // START 2018/11/09 [QC#28982, ADD]
    private int cmApInvDtlUpdateCommitCount = 0;
    private int cmInvAcctDistUpdateCommitCount = 0;
    private int cmInvAcctDistDeleteCommitCount = 0;
    // END   2018/11/09 [QC#28982, ADD]
    /** Total Commit Count */
    private int totalCommitCount;

    /** For handling CM_INV_ACCT_HDR Insert Bulk TBL Accessor */
    // START 2018/03/26 [QC#20316, MOD]
    // private CM_INV_ACCT_HDRTMsg[] cmInvAcctHdrInsertTMsgs;
    private CM_AP_INV_HDRTMsg[] cmApInvHdrUpdateForRegDistTMsgs;
    // START 2018/03/26 [QC#20316, MOD]
    /** For handling CM_INV_ACCT_DIST Insert Bulk TBL Accessor */
    private CM_INV_ACCT_DISTTMsg[] cmInvAcctDistInsertTMsgs;
    /** For handling CM_INV_PMT_HLD Insert Bulk TBL Accessor */
    private CM_INV_PMT_HLDTMsg[] cmInvPmtHldInsertTMsgs;
    /** For handling CM_INV_ACCT_HDR Update Bulk TBL Accessor */
    // START 2018/03/26 [QC#20316, DEL]
    // private CM_INV_ACCT_HDRTMsg[] cmInvAcctHdrUpdateTMsgs;
    // END   2018/03/26 [QC#20316, DEL]

    /** CM_INV_ACCT_HDR Bulk Insert Count */
    // START 2018/03/26 [QC#20316, MOD]
    // private int iInsertCntCmInvAcctHdr;
    private int iUpdateCntCmApInvHdrForRegDist;
    // START 2018/03/26 [QC#20316, MOD]
    /** CM_INV_ACCT_DIST Bulk Insert Count */
    private int iInsertCntCmInvAcctDist;
    /** CM_INV_PMT_HLD Bulk Insert Count */
    private int iInsertCntCmInvPmtHld;
    /** CM_INV_ACCT_HDR Bulk Insert Count */
    // START 2018/03/26 [QC#20316, DEL]
    // private int iUpdateCntCmInvAcctHdr;
    // END   2018/03/26 [QC#20316, DEL]

    /** Invoice Hold Map */
    private Map<List<String>,Map<String, Object>> invHldMap;
    /** CM_INV_PMT_HLD Key List */
    private List<List<String>> cmInvPmtHldKeyList;
    /** CM_INV_PMT_HLD Map */
    private Map<List<String>,Map<String,Object>> cmInvPmtHldMap;
    /** Invoice Total Amount Map */
    private Map<List<String>,BigDecimal> invTotAmtMap;
    /** PO Total Amount Map */
    private Map<List<String>,BigDecimal> poTotAmtMap;
    // START 2018/11/08 [QC#28982, ADD]
    private Map<List<String>,BigDecimal> varAmtMap;
    // END   2018/11/08 [QC#28982, ADD]
    /** CM_INV_ACCT_HDR PK List */
    private List<List<String>> cmInvAcctHdrKeyList;

    /** ACCT_INV_STS_CD ENTERED*/
    private String acctInvStsCdEntered;
    /** ACCT_INV_STS_CD AUTHORIZED*/
    private String acctInvStsCdAuthorized;

    /** Varchar Const value(NFBL2040_AP_INV_CATG_CD) */
    private String apInvCatgCd;

    // QC#19129 START
    /** Commit Count (CM_AP_INV_HDR Update) */
    private int cmApInvHdrUpdateCommitCount;
    /** For handling CM_AP_INV_HDR Update Bulk TBL Accessor */
    private CM_AP_INV_HDRTMsg[] cmApInvHdrUpdateTMsgs;
    /** CM_AP_INV_HDR Bulk Insert Count */
    private int iUpdateCntCmApInvHdr;
    // QC#19129 END

    /**
     * main method, which be called by Batch Shell
     * @param args argument
     */
    public static void main(final String[] args) {
        /** Initialize S21BatchMain */
        new NFBB118001().executeBatch(NFBB118001.class.getSimpleName());
    }
    @Override
    protected void initRoutine() {
        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        /** Get Global Company Code */
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        /** Get CM_PROC_DT */
        this.cmProcDt = NFBCommonBusiness.getCmProcDt(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cmProcDt)) {
            throw new S21AbendException(NFBM0028E);
        }
        // START 2018/10/29 [QC#28988, ADD]
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(slsDt)) {
            throw new S21AbendException(NFBM0028E);
        }
        // END   2018/10/29 [QC#28988, ADD]
        /** Get VAR_CHAR_CONST : AP_LINE_TP_ITEM */
        varCharConstApLineTpItem = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_AP_LINE_TP_ITEM, glblCmpyCd);
        /** Get VAR_CHAR_CONST : AP_LINE_TP_TAX */
        varCharConstApLineTpTax = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_AP_LINE_TP_TAX, glblCmpyCd);
        /** Get VAR_CHAR_CONST : AP_LINE_TP_FREIGHT */
        varCharConstApLineTpFreight = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_AP_LINE_TP_FREIGHT, glblCmpyCd);
        /** Get VAR_CHAR_CONST : AP_LINE_TP_VARIANCE */
        varCharConstApLineTpVariance = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_AP_LINE_TP_VARIANCE, glblCmpyCd);
        // START 2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
        /** Get VAR_CHAR_CONST : AUTO_PMT_HLD_REL_RSN_CD */
        varCharConstAutoPmtHldRelRsnCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_AUTO_PMT_HLD_REL_RSN_CD, glblCmpyCd);
        // START 2016/11/10 E.Kameishi [QC#14800, ADD]
        varCharConstApVndCdList = S21StringUtil.toStringArray(ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_COA_FROM_WH_VND_CD, glblCmpyCd), ",");
        // END 2016/11/10 E.Kameishi [QC#14800, ADD]
        
        // START 2017 12/13 T.Hakodate [QC#23038, ADD]
        apInvCatgCdIsEDIList = S21StringUtil.toStringArray(ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_AUTO_HLD_RELEASE_CATG_CD, glblCmpyCd), ",");
        // END 2017 12/13 T.Hakodate [QC#23038, ADD]
        
        // Varchar exists check.
        if (!ZYPCommonFunc.hasValue(varCharConstApLineTpItem)) {
            throw new S21AbendException(NFBM0268E, new String[] {VARCHAR_CONST_AP_LINE_TP_ITEM });
        }
        if (!ZYPCommonFunc.hasValue(varCharConstApLineTpTax)) {
            throw new S21AbendException(NFBM0268E, new String[] {VARCHAR_CONST_AP_LINE_TP_TAX });
        }
        if (!ZYPCommonFunc.hasValue(varCharConstApLineTpFreight)) {
            throw new S21AbendException(NFBM0268E, new String[] {VARCHAR_CONST_AP_LINE_TP_FREIGHT });
        }
        if (!ZYPCommonFunc.hasValue(varCharConstApLineTpVariance)) {
            throw new S21AbendException(NFBM0268E, new String[] {VARCHAR_CONST_AP_LINE_TP_VARIANCE });
        }
        if (!ZYPCommonFunc.hasValue(varCharConstAutoPmtHldRelRsnCd)) {
            throw new S21AbendException(NFBM0268E, new String[] {VARCHAR_CONST_AUTO_PMT_HLD_REL_RSN_CD });
        }
        // END   2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
        // START 2016/11/10 E.Kameishi [QC#14800, ADD]
        if (varCharConstApVndCdList.length <= 0) {
            throw new S21AbendException(NFBM0268E, new String[] {VARCHAR_CONST_COA_FROM_WH_VND_CD });
        }
        // END 2016/11/10 E.Kameishi [QC#14800, ADD]   
        
        // START 2017 12/13 T.Hakodate [QC#23038, ADD]
        if (apInvCatgCdIsEDIList.length <= 0) {
            throw new S21AbendException(NFBM0268E, new String[] {VARCHAR_CONST_AUTO_HLD_RELEASE_CATG_CD });
        }
        // END   2017 12/13 T.Hakodate [QC#23038, ADD]
        
        /** Initialize SSM Batch client. */
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Initialize Commit Count */
        initCommitCount();
        /** Initialize Bulk TBL Accessor */
        initBulkTBLAccessor();
        /** Initialize Bulk Update Count */
        initBulkCount();
        /** Initialize Invoice Map */
        invHldMap = new HashMap<List<String>,Map<String, Object>>();
        /** Initialize CM_INV_PMT_HLD Key List */
        cmInvPmtHldKeyList = new ArrayList<List<String>>();
        /** Initialize CM_INV_PMT_HLD Map */
        cmInvPmtHldMap = new HashMap<List<String>,Map<String,Object>>();
        /** Initialize Invoice Total Amount Map */
        invTotAmtMap = new HashMap<List<String>,BigDecimal>();
        /** Initialize PO Total Amount Map */
        poTotAmtMap = new HashMap<List<String>,BigDecimal>();
        // START 2018/11/08 [QC#28982, ADD]
        /** Initialize PO Variance Amount Map */
        varAmtMap = new HashMap<List<String>,BigDecimal>();
        // END   2018/11/08 [QC#28982, ADD]
        /** Initialize CM_INV_ACCT_HDR PK List */
        cmInvAcctHdrKeyList = new ArrayList<List<String>>();
        /** Initialize ACCT_INV_STS_CD(Entered) */
        acctInvStsCdEntered = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_ACCT_INV_STS_CD_ENTERED, this.glblCmpyCd);
        /** Initialize ACCT_INV_STS_CD(Authorized) */
        acctInvStsCdAuthorized = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_ACCT_INV_STS_CD_AUTHORIZED, this.glblCmpyCd);
        /** Initialize Varchar Const value(NFBB1180_AP_INV_CATG_CD) */
        apInvCatgCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NFBB1180_AP_INV_CATG_CD, this.glblCmpyCd);
        // START 2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
        if (!ZYPCommonFunc.hasValue(acctInvStsCdEntered)) {
            throw new S21AbendException(NFBM0268E, new String[] {VARCHAR_CONST_ACCT_INV_STS_CD_ENTERED });
        }
        if (!ZYPCommonFunc.hasValue(acctInvStsCdAuthorized)) {
            throw new S21AbendException(NFBM0268E, new String[] {VARCHAR_CONST_ACCT_INV_STS_CD_AUTHORIZED });
        }
        if (!ZYPCommonFunc.hasValue(apInvCatgCd)) {
            throw new S21AbendException(NFBM0268E, new String[] {VARCHAR_CONST_NFBB1180_AP_INV_CATG_CD });
        }
        // END   2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
    }

    @Override
    protected void mainRoutine() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cmDefAcctCdFrt", CM_DEF_ACCT_CD_FREIGHT);
        queryParam.put("cmDefAcctCdTax", CM_DEF_ACCT_CD_TAX);
        // START 2023/01/05 S.Nakatani [QC#60248,ADD]
        queryParam.put("cmDefAcctCdHdlg", CM_DEF_ACCT_CD_HDLG);
        // END 2023/01/05 S.Nakatani [QC#60248,ADD];
        queryParam.put("cmDefAcctCdVar", CM_DEF_ACCT_CD_VAR);
        // START 2016/12/09 [QC#16132, MOD]
        // START 2016/09/08 [QC#14161, ADD]
        queryParam.put("poAcctTpCd", PO_ACCT_TP.CHARGE);
        queryParam.put("poLineTpItt", PO_LINE_TP.ITT);
        queryParam.put("poLineTpExp", PO_LINE_TP.EXPENSE);
        queryParam.put("coaProjAcctTpAccrual", COA_PROJ_ACCT_TP.ACCRUAL);
        // END 2016/09/08 [QC#14161, ADD]
        // END 2016/12/09 [QC#16132, MOD]
        // START 2016/11/15 T.Tsuchida [QC#15897,ADD]
        // START 2017/12/13 T.Hakodate [QC#23038,MOD]
        // queryParam.put("apInvCatgCdIsEDI", apInvCatgCd);
        queryParam.put("apInvCatgCdIsEDI", apInvCatgCdIsEDIList);
        // END 2017/12/13 T.Hakodate [QC#23038,MOD]
        // END 2016/11/15 T.Tsuchida [QC#15897,ADD]
        
        // START 2018/01/30 K.Kameoka [QC#18602-Sol#102,ADD]
        queryParam.put("dsPoTpCd", DS_PO_TP.BLANKET_PO);
        // END 2018/01/30 K.Kameoka [QC#18602-Sol#102,ADD]

        // START 2018/05/11 [QC#26080,ADD]
        queryParam.put("cancel", ACCT_INV_STS.CANCEL);
        // END   2018/05/11 [QC#26080,ADD]

        // START 2018/11/08 [QC#28982, ADD]
        queryParam.put("acctInvStsCdList", new String[] { acctInvStsCdEntered });
        queryParam.put("poHdrStsCdIsOpen", PO_HDR_STS.OPEN);
        queryParam.put("poLineStsCdIsOpen", PO_LINE_STS.OPEN);
        queryParam.put("poStsCdIsOpenList", new String[] { PO_STS.VALIDATED, PO_STS.PO_CONFIRMED });
        queryParam.put("poLinsStsCdIsOpenForXXList", new String[] { PO_LINE_STS.OPEN_FOR_RECEIPT, PO_LINE_STS.OPEN_FOR_INVOICE });
        queryParam.put("apLineTpCdItem", AP_LINE_TP.ITEM);
        // END   2018/11/08 [QC#28982, ADD]
        // START 2018/11/20 [QC#28660, ADD]
        queryParam.put("cmApInvTpCdCrMemo", CM_AP_INV_TP.CREDIT_MEMO);
        // END   2018/11/20 [QC#28660, ADD]

        // START 2018/11/08 [QC#28982, ADD]
        // PO Variance Update process
        Boolean bPoUpdRet = (Boolean) ssmBatchClient.queryObject(SELECT_PO_VARIANCE_UPDATE_RECORD, queryParam, new SelectRecordPOVarianceUpdateHandler());
        if (bPoUpdRet == Boolean.TRUE) {

            for (List<String> invLineKeyList : this.varAmtMap.keySet()) {
                BigDecimal varAmt = varAmtMap.get(invLineKeyList);
                BigDecimal oldVarAmt = BigDecimal.ZERO;
                CM_INV_ACCT_DISTTMsg distTMsg = getInvAcctDistVarRecord(invLineKeyList);
                if (distTMsg != null && ZYPCommonFunc.hasValue(distTMsg.acInvJrnlCostAmt)) {
                    oldVarAmt = distTMsg.acInvJrnlCostAmt.getValue();
                }

                if (oldVarAmt.compareTo(varAmt) == 0) {
                    continue;
                }

                if (varAmt.compareTo(BigDecimal.ZERO) != 0) {
                    if (distTMsg != null) {
                        // update CM_AP_INV_DTL, CM_INV_ACCT_DIST
                        updateInvAcctDistForVar(invLineKeyList, varAmt, distTMsg);
                    } else {
                        // update CM_AP_INV_DTL, insert CM_INV_ACCT_DIST
                        createInvAcctDistForVar(invLineKeyList);
                    }
                } else {
                    if (distTMsg != null) {
                        // update CM_AP_INV_DTL, delete CM_INV_ACCT_DIST
                        removeInvAcctDistForVar(invLineKeyList, distTMsg);
                    }
                }
            }
        }

        /** Initialize SSM Batch client. */
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Initialize Bulk TBL Accessor */
        initBulkTBLAccessor();
        /** Initialize Bulk Update Count */
        initBulkCount();
        /** Initialize Invoice Map */
        invHldMap = new HashMap<List<String>,Map<String, Object>>();
        /** Initialize CM_INV_PMT_HLD Key List */
        cmInvPmtHldKeyList = new ArrayList<List<String>>();
        /** Initialize CM_INV_PMT_HLD Map */
        cmInvPmtHldMap = new HashMap<List<String>,Map<String,Object>>();
        /** Initialize Invoice Total Amount Map */
        invTotAmtMap = new HashMap<List<String>,BigDecimal>();
        /** Initialize PO Total Amount Map */
        poTotAmtMap = new HashMap<List<String>,BigDecimal>();
        /** Initialize PO Variance Amount Map */
        varAmtMap = new HashMap<List<String>,BigDecimal>();
        /** Initialize CM_INV_ACCT_HDR PK List */
        cmInvAcctHdrKeyList = new ArrayList<List<String>>();
        // END   2018/11/08 [QC#28982, ADD]

        // START 2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
        // Automatic hold release process
        Boolean bHoldeRet = (Boolean) ssmBatchClient.queryObject(SELECT_HOLD_RECORD, queryParam, new SelectRecordHandler(true));
        if (bHoldeRet == Boolean.TRUE) {
            for (int i = 0; i < cmInvAcctHdrKeyList.size(); i++) {
                List<String> invKeyList = (List<String>) cmInvAcctHdrKeyList.get(i);
                String apVndCd = invKeyList.get(0);
                String apVndInvNum = invKeyList.get(1);
                String apVndInvSqNum = invKeyList.get(2);
                CM_INV_PMT_HLDTMsgArray dbHoldList = getCmInvPmtHldList(apVndCd, apVndInvNum, apVndInvSqNum);
                for (int j = 0; j < dbHoldList.getValidCount(); j++) {
                    CM_INV_PMT_HLDTMsg dbTMsgHld = (CM_INV_PMT_HLDTMsg) dbHoldList.get(j);
                    // START 2018/04/13 [QC#25531, MOD]
                    if (AP_VND_INV_LINE_NUM_0000.equals(dbTMsgHld.apVndInvLineNum.getValue())) {
                        // Header Level Hold
                        boolean headerReleaseFlg = true;
                        for (List<String> keyList : cmInvPmtHldMap.keySet()) {
                            String hldApVndCd = keyList.get(0);
                            String hldApVndInvNum = keyList.get(1);
                            String hldApVndInvSqNum = keyList.get(2);
                            // String hldApVndInvLineNum = keyList.get(3);
                            String hldPmtHldCd = keyList.get(4);
                            String hldPmtHldRsnCd = keyList.get(5);
                            if (!hldApVndCd.equals(dbTMsgHld.apVndCd.getValue()) || !hldApVndInvNum.equals(dbTMsgHld.apVndInvNum.getValue()) || !hldApVndInvSqNum.equals(dbTMsgHld.apVndInvSqNum.getValue())
                                    || !hldPmtHldCd.equals(dbTMsgHld.pmtHldCd.getValue()) || !hldPmtHldRsnCd.equals(dbTMsgHld.pmtHldRsnCd.getValue())) {
                                continue;
                            }
                            Map<String, Object> newHoldMap = cmInvPmtHldMap.get(keyList);
                            if (ZYPConstant.FLG_ON_Y.equals((String) newHoldMap.get(PMT_HLD_FLG))) {
                                headerReleaseFlg = false;
                                break;
                            }
                        }
                        if (!headerReleaseFlg) {
                            continue;
                        }

                    } else {
                        // Line Level Hold
                        List<String> keyList = new ArrayList<String>();
                        keyList.add(dbTMsgHld.apVndCd.getValue());
                        keyList.add(dbTMsgHld.apVndInvNum.getValue());
                        keyList.add(dbTMsgHld.apVndInvSqNum.getValue());
                        keyList.add(dbTMsgHld.apVndInvLineNum.getValue());
                        keyList.add(dbTMsgHld.pmtHldCd.getValue());
                        keyList.add(dbTMsgHld.pmtHldRsnCd.getValue());
                        Map<String, Object> newHoldMap = cmInvPmtHldMap.get(keyList);
                        if (newHoldMap != null && ZYPConstant.FLG_ON_Y.equals((String) newHoldMap.get(PMT_HLD_FLG))) {
                            continue;
                        }
                    }

                    // Automatic hold release
                    ZYPEZDItemValueSetter.setValue(dbTMsgHld.pmtHldFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(dbTMsgHld.pmtHldRelPsnCd, PSN_CD_NFBB1180);
                    // START 2018/10/29 [QC#28988, MOD]
                    // ZYPEZDItemValueSetter.setValue(dbTMsgHld.pmtHldRelDt, currentSystemTim);
                    ZYPEZDItemValueSetter.setValue(dbTMsgHld.pmtHldRelDt, slsDt);
                    // END   2018/10/29 [QC#28988, MOD]
                    ZYPEZDItemValueSetter.setValue(dbTMsgHld.pmtHldRelRsnCd, varCharConstAutoPmtHldRelRsnCd);
                    EZDTBLAccessor.update(dbTMsgHld);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dbTMsgHld.getReturnCode())) {
                        throw new S21AbendException(NFBM0028E);
                    } else {
                        cmInvPmtHldUpdateCommitCount = cmInvPmtHldUpdateCommitCount + 1;
                    }
//                    List<String> keyList = new ArrayList<String>();
//                    keyList.add(dbTMsgHld.apVndCd.getValue());
//                    keyList.add(dbTMsgHld.apVndInvNum.getValue());
//                    keyList.add(dbTMsgHld.apVndInvSqNum.getValue());
//                    // START 2018/03/30 [QC#24277, ADD]
//                    keyList.add(dbTMsgHld.apVndInvLineNum.getValue());
//                    // END   2018/03/30 [QC#24277, ADD]
//                    keyList.add(dbTMsgHld.pmtHldCd.getValue());
//                    keyList.add(dbTMsgHld.pmtHldRsnCd.getValue());
//                    Map<String, Object> newHoldMap = cmInvPmtHldMap.get(keyList);
//                    if (newHoldMap == null || ZYPConstant.FLG_OFF_N.equals((String) newHoldMap.get(PMT_HLD_FLG))) {
//                        // Automatic hold release
//                        ZYPEZDItemValueSetter.setValue(dbTMsgHld.pmtHldFlg, ZYPConstant.FLG_OFF_N);
//                        ZYPEZDItemValueSetter.setValue(dbTMsgHld.pmtHldRelPsnCd, PSN_CD_NFBB1180);
//                        ZYPEZDItemValueSetter.setValue(dbTMsgHld.pmtHldRelDt, currentSystemTim);
//                        ZYPEZDItemValueSetter.setValue(dbTMsgHld.pmtHldRelRsnCd, varCharConstAutoPmtHldRelRsnCd);
//                        EZDTBLAccessor.update(dbTMsgHld);
//                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dbTMsgHld.getReturnCode())) {
//                            throw new S21AbendException(NFBM0028E);
//                        } else {
//                            cmInvPmtHldUpdateCommitCount = cmInvPmtHldUpdateCommitCount + 1;
//                        }
//                    }
                    // END   2018/04/13 [QC#25531, MOD]
                }

                // START 2018/11/12 [QC#28982, MOD]
                for (int j = 0; j < cmInvPmtHldKeyList.size(); j++) {
                    List<String> keyList = (List<String>) cmInvPmtHldKeyList.get(j);
                    String hldKeyApVndCd = keyList.get(0);
                    String hldKeyApVndInvNum = keyList.get(1);
                    String hldKeyApVndInvSqNum = keyList.get(2);
                    String hldKeyApVndInvLineNum = keyList.get(3);
                    String hldKeyPmtHldCd = keyList.get(4);
                    String hldKeyPmtHldRsnCd = keyList.get(5);

                    if (!hldKeyApVndCd.equals(apVndCd) 
                            || !hldKeyApVndInvNum.equals(apVndInvNum)
                            || !hldKeyApVndInvSqNum.equals(apVndInvSqNum)) {
                        continue;
                    }

                    Map<String,Object> map = cmInvPmtHldMap.get(keyList);
                    if(!ZYPConstant.FLG_ON_Y.equals((String)map.get(PMT_HLD_FLG))) {
                        continue;
                    }

                    CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, hldKeyApVndCd);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, hldKeyApVndInvNum);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, hldKeyApVndInvSqNum);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, hldKeyApVndInvLineNum);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, hldKeyPmtHldCd);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, hldKeyPmtHldRsnCd);
                    cmInvPmtHldTMsg = (CM_INV_PMT_HLDTMsg) S21FastTBLAccessor.findByKey(cmInvPmtHldTMsg);
                    if (cmInvPmtHldTMsg != null) {
                        continue;
                    }

                    cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, hldKeyApVndCd);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, hldKeyApVndInvNum);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, hldKeyApVndInvSqNum);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, hldKeyApVndInvLineNum);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, hldKeyPmtHldCd);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, hldKeyPmtHldRsnCd);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, (String) map.get(PO_NUM));
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, (String) map.get(PMT_HLD_FLG));
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, slsDt);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldPsnCd, PSN_CD_NFBB1180);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelPsnCd, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelDt, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelRsnCd, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelCmntTxt, EMPTY_STRING);

                    // insert CM_INV_PMT_HLD
                    EZDTBLAccessor.insert(cmInvPmtHldTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvPmtHldTMsg.getReturnCode())) {
                        throw new S21AbendException(NFBM0028E);
                    }
                    cmInvPmtHldUpdateCommitCount = cmInvPmtHldUpdateCommitCount + 1;
                }
                // END   2018/11/12 [QC#28982, MOD]

                // Update CM_INV_ACCT_HDR
                CM_INV_PMT_HLDTMsgArray dbHoldListAfterUpdate = getCmInvPmtHldList(apVndCd, apVndInvNum, apVndInvSqNum);
                if (dbHoldListAfterUpdate.getValidCount() == 0) {
                    // START 2018/03/26 [QC#20316, DEL]
//                    CM_INV_ACCT_HDRTMsg hdrTmsg = getCmInvAcctHdr(apVndCd, apVndInvNum, apVndInvSqNum);
//                    if (hdrTmsg != null) {
//                        ZYPEZDItemValueSetter.setValue(hdrTmsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
//                        ZYPEZDItemValueSetter.setValue(hdrTmsg.pmtHldRelPsnCd, PSN_CD_NFBB1180);
//                        ZYPEZDItemValueSetter.setValue(hdrTmsg.pmtHldRelDt, currentSystemTim);
//                        ZYPEZDItemValueSetter.setValue(hdrTmsg.pmtHldRelRsnCd, varCharConstAutoPmtHldRelRsnCd);
//                        // START 2018/02/22 [QC#23505, MOD]
//                        // ZYPEZDItemValueSetter.setValue(hdrTmsg.acctInvStsCd, acctInvStsCdAuthorized);
//                        if (ACCT_INV_STS.ENTERED.equals(hdrTmsg.acctInvStsCd.getValue())) {
//                            ZYPEZDItemValueSetter.setValue(hdrTmsg.acctInvStsCd, acctInvStsCdAuthorized);
//                        }
//                        // END   2018/02/22 [QC#23505, MOD]
//                        EZDTBLAccessor.update(hdrTmsg);
//                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hdrTmsg.getReturnCode())) {
//                            throw new S21AbendException(NFBM0028E);
//                        } else {
//                            cmInvAcctHdrUpdateCommitCount = cmInvAcctHdrUpdateCommitCount + 1;
//                        }
                    // END   2018/03/26 [QC#20316, DEL]

                    // START 2018/03/26 [QC#20316, ADD]
                    CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, apVndCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, apVndInvNum);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, apVndInvSqNum);
                    cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
                    if (cmApInvHdrTMsg != null) {
                    // END   2018/03/26 [QC#20316, ADD]
                    
                        // START 2017/05/26 R.Shimamoto [QC#18557,ADD]
                        CM_AP_INV_DTLTMsgArray cmApInvDtlTMsgArray = findCmApInvDtlList(glblCmpyCd, apVndCd, apVndInvNum, apVndInvSqNum);
                        for (int index = 0; index < cmApInvDtlTMsgArray.length(); index++) {
                            CM_AP_INV_DTLTMsg cmApInvDtlTMsg = cmApInvDtlTMsgArray.no(index);
                            // START 2019/08/29 [QC#52280, MOD]
                            // String msgId = callPoStsUpdateAPI(cmApInvDtlTMsg);
                            String msgId = callPoStsUpdateAPI(cmApInvHdrTMsg, cmApInvDtlTMsg);
                            // END   2019/08/29 [QC#52280, MOD]
                            if (msgId != null) {
                                throw new S21AbendException(msgId);
                            }
                        }
                        // END 2017/05/26 R.Shimamoto [QC#18557,ADD]

                        // START 2018/03/26 [QC#20316, DEL]
                        // QC#19129 START
//                        CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
//                        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, this.glblCmpyCd);
//                        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, apVndCd);
//                        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, apVndInvNum);
//                        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, apVndInvSqNum);
//                        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apInvTpCd, AP_INV_TP_CD_00);
//                        cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
                        // END   2018/03/26 [QC#20316, DEL]
                        if (cmApInvHdrTMsg != null) {
                            if (ZYPConstant.FLG_ON_Y.equals(cmApInvHdrTMsg.pmtHldFlg.getValue())) {
                                // Release
                                ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
                                // START 2018/03/26 [QC#20316, ADD]
                                if (ACCT_INV_STS.ENTERED.equals(cmApInvHdrTMsg.acctInvStsCd.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctInvStsCd, acctInvStsCdAuthorized);
                                }
                                // END   2018/03/26 [QC#20316, ADD]
                                EZDTBLAccessor.update(cmApInvHdrTMsg);
                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmApInvHdrTMsg.getReturnCode())) {
                                    throw new S21AbendException(NFBM0028E);
                                } else {
                                    cmApInvHdrUpdateCommitCount = cmApInvHdrUpdateCommitCount + 1;
                                }
                            }
                        }
                        // QC#19129 END
                    }
                }
            }
        }

        /** Initialize SSM Batch client. */
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Initialize Bulk TBL Accessor */
        initBulkTBLAccessor();
        /** Initialize Bulk Update Count */
        initBulkCount();
        /** Initialize Invoice Map */
        invHldMap = new HashMap<List<String>,Map<String, Object>>();
        /** Initialize CM_INV_PMT_HLD Key List */
        cmInvPmtHldKeyList = new ArrayList<List<String>>();
        /** Initialize CM_INV_PMT_HLD Map */
        cmInvPmtHldMap = new HashMap<List<String>,Map<String,Object>>();
        /** Initialize Invoice Total Amount Map */
        invTotAmtMap = new HashMap<List<String>,BigDecimal>();
        /** Initialize PO Total Amount Map */
        poTotAmtMap = new HashMap<List<String>,BigDecimal>();
        // START 2018/11/08 [QC#28982, ADD]
        /** Initialize PO Variance Amount Map */
        varAmtMap = new HashMap<List<String>,BigDecimal>();
        // END   2018/11/08 [QC#28982, ADD]
        /** Initialize CM_INV_ACCT_HDR PK List */
        cmInvAcctHdrKeyList = new ArrayList<List<String>>();
        // END   2016/10/19 Y.Tsuchimoto [QC#14810,ADD]

        Boolean bRet = (Boolean) ssmBatchClient.queryObject(SELECT_RECORD, queryParam, new SelectRecordHandler(false));
        if (bRet == Boolean.TRUE) {
            // START 2018/03/30 [QC#20316, MOD]
            // if (iInsertCntCmInvAcctHdr > 0) {
            //     int iRet = NFBCommonBusiness.bulkInsertRestOfRecords(cmInvAcctHdrInsertTMsgs, iInsertCntCmInvAcctHdr);
            //     if (iRet > 0) {
            //         cmInvAcctHdrInsertCommitCount = cmInvAcctHdrInsertCommitCount + iRet;
            //     } else {
            //         cmInvAcctHdrInsertCommitCount = 0;
            //         throw new S21AbendException(ZZBM0074E);
            //     }
            // }
            if (iUpdateCntCmApInvHdrForRegDist > 0) {
                // START 2018/04/02 [QC#20316, MOD]
                // int iRet = NFBCommonBusiness.bulkInsertRestOfRecords(cmApInvHdrUpdateForRegDistTMsgs, iUpdateCntCmApInvHdrForRegDist);
                int iRet = NFBCommonBusiness.bulkUpdateRestOfRecords(cmApInvHdrUpdateForRegDistTMsgs, iUpdateCntCmApInvHdrForRegDist);
                // END   2018/04/02 [QC#20316, MOD]
                if (iRet > 0) {
                    cmApInvHdrForRegDistCommitCount = cmApInvHdrForRegDistCommitCount + iRet;
                } else {
                    cmApInvHdrForRegDistCommitCount = 0;
                    throw new S21AbendException(NFBM0028E);
                }
            }
            // END   2018/03/30 [QC#20316, MOD]

            if (iInsertCntCmInvAcctDist > 0) {
                int iRet = NFBCommonBusiness.bulkInsertRestOfRecords(cmInvAcctDistInsertTMsgs, iInsertCntCmInvAcctDist);
                if (iRet > 0) {
                    cmInvAcctDistInsertCommitCount = cmInvAcctDistInsertCommitCount + iRet;
                } else {
                    cmInvAcctDistInsertCommitCount = 0;
                    throw new S21AbendException(ZZBM0074E);
                }
            }
        } else {
            rollback();
            return;
        }
        // Check Invoice Total Amount and PO Total Amount
        //START 2016/09/29 [QC#14799, DEL]
        //checkTotalAmount();
        //END 2016/09/29 [QC#14799, DEL]
        // Update payment hold information on CM_INV_ACCT_HDR table
        updateCmInvAcctHdrHoldInfo();

        // Delete records from CM_INV_PMT_HLD table.
        deleteCmInvPmtHld();
        // Create records on CM_INV_PMT_HLD table.
        createCmInvPmtHld();
        // START 2018/03/30 [QC#20316, DEL]
//        if (iUpdateCntCmInvAcctHdr > 0) {
//            int iRet = NFBCommonBusiness.bulkUpdateRestOfRecords(cmInvAcctHdrUpdateTMsgs, iUpdateCntCmInvAcctHdr);
//            if (iRet > 0) {
//                cmInvAcctHdrUpdateCommitCount = cmInvAcctHdrUpdateCommitCount + iRet;
//            } else {
//                cmInvAcctHdrUpdateCommitCount = 0;
//                throw new S21AbendException(NFBM0028E);
//            }
//        }
        // END   2018/03/30 [QC#20316, DEL]
        if (iInsertCntCmInvPmtHld > 0) {
            int iRet = NFBCommonBusiness.bulkInsertRestOfRecords(cmInvPmtHldInsertTMsgs, iInsertCntCmInvPmtHld);
            if (iRet > 0) {
                cmInvPmtHldInsertCommitCount = cmInvPmtHldInsertCommitCount + iRet;
            } else {
                cmInvPmtHldInsertCommitCount = 0;
                throw new S21AbendException(ZZBM0074E);
            }
        }
        if (iUpdateCntCmApInvHdr > 0) {
            int iRet = NFBCommonBusiness.bulkUpdateRestOfRecords(cmApInvHdrUpdateTMsgs, iUpdateCntCmApInvHdr);
            if (iRet > 0) {
                cmApInvHdrUpdateCommitCount = cmApInvHdrUpdateCommitCount + iRet;
            } else {
                cmApInvHdrUpdateCommitCount = 0;
                throw new S21AbendException(NFBM0028E);
            }
        }
        commit();
    }

    // START 2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
    private CM_INV_PMT_HLDTMsgArray getCmInvPmtHldList(String apVndCd, String apVndInvNum, String apVndInvSqNum) {
        CM_INV_PMT_HLDTMsg param = new CM_INV_PMT_HLDTMsg();
        // MOD Start 2018/02/13 S21_NA#24087
        param.setSQLID("003");
//        param.setSQLID("002");
        // MOD End 2018/02/13 S21_NA#24087
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("apVndCd01", apVndCd);
        param.setConditionValue("apVndInvNum01", apVndInvNum);
        param.setConditionValue("apVndInvSqNum01", apVndInvSqNum);
        param.setConditionValue("pmtHldFlg01", ZYPConstant.FLG_ON_Y);
        param.setConditionValue("pmtHldCd01", PMT_HLD.THEREFORE); // ADD 2018/02/13 S21_NA#24087
        return (CM_INV_PMT_HLDTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(param);
    }

    // START 2018/03/26 [QC#20316, DEL]
    // private CM_INV_ACCT_HDRTMsg getCmInvAcctHdr(String apVndCd, String apVndInvNum, String apVndInvSqNum) {
    //     CM_INV_ACCT_HDRTMsg tMsg = new CM_INV_ACCT_HDRTMsg();
    //     ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
    //     ZYPEZDItemValueSetter.setValue(tMsg.apVndCd, apVndCd);
    //     ZYPEZDItemValueSetter.setValue(tMsg.apVndInvNum, apVndInvNum);
    //     ZYPEZDItemValueSetter.setValue(tMsg.apVndInvSqNum, apVndInvSqNum);
    //     ZYPEZDItemValueSetter.setValue(tMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
    //
    //     return (CM_INV_ACCT_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
    // }
    // END   2018/03/26 [QC#20316, DEL]
    // END   2016/10/19 Y.Tsuchimoto [QC#14810,ADD]

    @Override
    protected void termRoutine() {
        // START 2016/10/19 Y.Tsuchimoto [QC#14810,MOD]
        this.totalCommitCount = 
                                // START 2018/03/26 [QC#20316, DEL]
                                // cmInvAcctHdrInsertCommitCount + 
                                // END   2018/03/26 [QC#20316, DEL]
                                cmInvAcctDistInsertCommitCount + 
                                // START 2018/11/09 [QC#28982, ADD]
                                cmApInvDtlUpdateCommitCount + 
                                cmInvAcctDistUpdateCommitCount +
                                cmInvAcctDistDeleteCommitCount +
                                // END   2018/11/09 [QC#28982, ADD]
                                cmInvPmtHldInsertCommitCount + 
                                // START 2018/03/26 [QC#20316, DEL]
                                // cmInvAcctHdrUpdateCommitCount + 
                                // END   2018/03/26 [QC#20316, DEL]
                                cmInvPmtHldDeleteCommitCount +
                                cmInvPmtHldUpdateCommitCount +
                                cmApInvHdrUpdateCommitCount;
        // END   2016/10/19 Y.Tsuchimoto [QC#14810,MOD]
        /** Normal End this process */
        setTermState(TERM_CD.NORMAL_END, totalCommitCount, 0, totalCommitCount);
    }
    /**
     * Private class: SelectRecordHandler
     */
    @SuppressWarnings("unchecked")
    private class SelectRecordHandler extends S21SsmBooleanResultSetHandlerSupport {

        // QC#21875 Start
        /** Hold Release Process Flag */
        boolean hldRelProcFlg = false;

        public SelectRecordHandler(boolean hldRelProcFlg) {
            this.hldRelProcFlg = hldRelProcFlg;
        }
        // QC#21875 End

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            List<List> hdrPkCombinationList = new ArrayList<List>();
            //START 2016/09/30 [QC#14797, ADD]
            int apVndInvAcctDistNum = 1;
            //END 2016/09/30 [QC#14797, ADD]
            // START 2017/01/23 E.Kameishi [QC#17090,ADD]
            int varCnt = 1;
            // START 2018/04/02 [QC#20316, DEL]
            // int apVndInvLineNumItem = 1;
            // END   2018/04/02 [QC#20316, DEL]
            Map<String, Integer> apVndInvLineNumOtherMap = new HashMap();
            while (rs.next()) {
                String apVndInvNum = rs.getString(AP_VND_INV_NUM);
                if (apVndInvLineNumOtherMap.containsKey(apVndInvNum)) {
                    int count = apVndInvLineNumOtherMap.get(apVndInvNum) + 1;
                    apVndInvLineNumOtherMap.put(apVndInvNum, count++);
                } else {
                    apVndInvLineNumOtherMap.put(apVndInvNum, 1);
                }
            }
            rs.beforeFirst();
            int apVndInvLineNumOther = 1;
            // END 2017/01/23 E.Kameishi [QC#17090,ADD]
            while (rs.next()) {
                List hdrPkList = new ArrayList(); 
                hdrPkList.add(rs.getString(AP_VND_CD));
                hdrPkList.add(rs.getString(AP_VND_INV_NUM));
                hdrPkList.add(rs.getString(AP_VND_INV_SQ_NUM));
                // START 2018/04/02 [QC#20316, ADD]
                String apVndInvLineNum = rs.getString(AP_VND_INV_LINE_NUM);
                // END   2018/04/02 [QC#20316, ADD]

                // START 2018/04/02 [QC#24277, ADD]
                // For Payment Hold
                List<String> invLineKeyList = new ArrayList<String>();
                invLineKeyList.add(rs.getString(AP_VND_CD));
                invLineKeyList.add(rs.getString(AP_VND_INV_NUM));
                invLineKeyList.add(rs.getString(AP_VND_INV_SQ_NUM));
                invLineKeyList.add(rs.getString(AP_VND_INV_LINE_NUM));
                Map<String, Object> hldMap = new HashMap<String, Object>();
                hldMap.put(PMT_HLD_FLG, ZYPConstant.FLG_OFF_N);
                hldMap.put(PMT_HLD_CD, PMT_HLD_CD_000);
                hldMap.put(PMT_HLD_RSN_CD, PMT_HLD_RSN_CD_000);
                hldMap.put(PMT_HLD_REL_RSN_CD, PMT_HLD_REL_RSN_CD_000);
                invHldMap.put(invLineKeyList, hldMap);
                // END   2018/04/02 [QC#24277, ADD]

                if (!hdrPkCombinationList.contains(hdrPkList)) {
                    //START 2016/09/30 [QC#14797, ADD]
                    // START 2018/04/02 [QC#20316, DEL]
                    // apVndInvLineNumItem = 1;
                    // END   2018/04/02 [QC#20316, DEL]
                    varCnt =1;
                    apVndInvAcctDistNum = 1;
                    //END 2016/09/30 [QC#14797, ADD]

                    // For Payment Hold
                    List<String> invKeyList = new ArrayList<String>();
                    invKeyList.add(rs.getString(AP_VND_CD));
                    invKeyList.add(rs.getString(AP_VND_INV_NUM));
                    invKeyList.add(rs.getString(AP_VND_INV_SQ_NUM));
                    // START 2018/04/02 [QC#24277, DEL]
                    // Map<String, Object> hldMap = new HashMap<String, Object>();
                    // hldMap.put(PMT_HLD_FLG, ZYPConstant.FLG_OFF_N);
                    //hldMap.put(PMT_HLD_CD, PMT_HLD_CD_000);
                    // hldMap.put(PMT_HLD_RSN_CD, PMT_HLD_RSN_CD_000);
                    // hldMap.put(PMT_HLD_REL_RSN_CD, PMT_HLD_REL_RSN_CD_000);
                    // invHldMap.put(invKeyList, hldMap);
                    // END   2018/04/02 [QC#24277, DEL]
                    invTotAmtMap.put(invKeyList, rs.getBigDecimal(AC_SC_TOT_FOB_COST_AMT));
                    cmInvAcctHdrKeyList.add(invKeyList);

                    // START 2018/03/26 [QC#20316, MOD]
                    // // Create CM_INV_ACCT_HDR record
                    // createCmInvAcctHdr(rs);
                    // Update CM_AP_INV_HDR record
                    updateCmApInvHdr(rs);
                    // END   2018/03/26 [QC#20316, MOD]

                    if (rs.getBigDecimal(AC_SC_TOT_FRT_COST_AMT).compareTo(BigDecimal.ZERO) != 0) {
                        // START 2017/01/23 E.Kameishi [QC#17090,MOD]
                        // START 2016/11/10 E.Kameishi [QC#14800, MOD]
                        // Create CM_INV_ACCT_DIST(FRT) record
                        apVndInvLineNumOther = apVndInvLineNumOtherMap.get(rs.getString(AP_VND_INV_NUM));
                        createInvAcctDistForFreight(rs, apVndInvAcctDistNum, apVndInvLineNumOther + 1, this.hldRelProcFlg);
                        // END 2016/11/10 E.Kameishi {[QC#14800, MOD]
                        //START 2016/09/30 [QC#14797, ADD]
                        apVndInvLineNumOtherMap.put(rs.getString(AP_VND_INV_NUM), apVndInvLineNumOther + 1);
                        //END 2016/09/30 [QC#14797, ADD]
                        // END 2017/01/23 E.Kameishi [QC#17090,MOD]
                    }
                    if (rs.getBigDecimal(AC_SC_TOT_TAX_AMT).compareTo(BigDecimal.ZERO) != 0) {
                        // START 2017/01/23 E.Kameishi [QC#17090,MOD]
                        // Create CM_INV_ACCT_DIST(TAX) record
                        apVndInvLineNumOther = apVndInvLineNumOtherMap.get(rs.getString(AP_VND_INV_NUM));
                        createInvAcctDistForTax(rs, apVndInvAcctDistNum, apVndInvLineNumOther + 1);
                        // START 2017/01/23 E.Kameishi [QC#17090,DEL]
                        //START 2016/09/30 [QC#14797, ADD]
                        apVndInvLineNumOtherMap.put(rs.getString(AP_VND_INV_NUM), apVndInvLineNumOther + 1);
                        //END 2016/09/30 [QC#14797, ADD]
                        // END 2017/01/23 E.Kameishi [QC#17090,MOD]
                    }
                    // START 2023/01/05 S.Nakatani [QC#60248,ADD]
                    if (rs.getBigDecimal(AC_SC_TOT_HDLG_COST_AMT).compareTo(BigDecimal.ZERO) != 0) {
                        // Create CM_INV_ACCT_DIST(HDLG) record
                        apVndInvLineNumOther = apVndInvLineNumOtherMap.get(rs.getString(AP_VND_INV_NUM));
                        createInvAcctDistForHandlingFee(rs, apVndInvAcctDistNum, apVndInvLineNumOther + 1, this.hldRelProcFlg);
                        apVndInvLineNumOtherMap.put(rs.getString(AP_VND_INV_NUM), apVndInvLineNumOther + 1);
                    }
                    // END 2023/01/05 S.Nakatani [QC#60248,ADD]
                    // START 2017/01/23 E.Kameishi [QC#17090,DEL]
                    //if (rs.getBigDecimal(AC_INV_VAR_COST_AMT).compareTo(BigDecimal.ZERO) != 0) {
                        // Create CM_INV_ACCT_DIST(VAR) record
                    //    createInvAcctDistForVar(rs, apVndInvAcctDistNum);
                        //START 2016/09/30 [QC#14797, ADD]
                    //    apVndInvAcctDistNum++;
                        //END 2016/09/30 [QC#14797, ADD]
                    //}
                    // END 2017/01/23 E.Kameishi [QC#17090,DEL]
                    hdrPkCombinationList.add(hdrPkList);
                }
                // Set PO Total Amount
                List<String> invKeyList = setPoTotAmt(rs);

                // Compare Ordered Quantity to Invoice Quantity.(PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)
                // START 2017/07/26 S.Katsuma [QC#19335,ADD]
//                if (NFBCommonBusiness.chkNull(rs.getBigDecimal(PO_QTY)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(INV_QTY))) != 0) {
                // START 2018/05/08 [QC#24981, MOD]
                // if (NFBCommonBusiness.chkNull(rs.getBigDecimal(PO_QTY)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(SUM_INV_QTY))) != 0) {
                // START 2018/05/16 [QC#26080, MOD]
                // if (NFBCommonBusiness.chkNull(rs.getBigDecimal(PO_QTY)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(SUM_INV_QTY))) < 0) {
                if (NFBCommonBusiness.chkNull(rs.getBigDecimal(PO_QTY)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(TOT_INV_QTY))) < 0) {
                // END   2018/05/16 [QC#26080, MOD]
                // END   2018/05/08 [QC#24981, MOD]
                // END 2017/07/26 S.Katsuma [QC#19335,ADD]
                    // START 2018/04/02 [QC#24277, MOD]
                    // setPmtHld(rs, invKeyList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012);
                    setPmtHld(rs, invLineKeyList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012);
                    // END   2018/04/02 [QC#24277, MOD]
                }
                // START 2018/11/20 [QC#28660, ADD]
                if ((NFBCommonBusiness.chkNull(rs.getBigDecimal(TOT_INV_QTY))).compareTo(BigDecimal.ZERO) < 0) {
                    setPmtHld(rs, invLineKeyList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012);
                }
                // END 2018/11/20 [QC#28660, ADD]

                // START 2017/10/20 T.Tokutomi [QC#21653,MOD]
                if (NFBCommonBusiness.chkNull(rs.getString(PO_MATCH_TP_CD)).equals(PO_MATCH_TP_CD_R)) {

                    // START 2017/12/14 T.Hakodate [SOL#060, DELETE]
                    // Compare Ordered Quantity to Invoice Quantity.(PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)
                    //if (NFBCommonBusiness.chkNull(rs.getBigDecimal(PO_QTY)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(INV_QTY))) != 0) {
                    //    setPmtHld(rs, invKeyList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012);
                    //}
                    // START 2017/12/14 T.Hakodate [SOL#060, DELETE]

                    // START 2017/12/14 T.Hakodate [SOL#060, MOD]
                    if (PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(rs.getString("PO_LINE_TP_CD")) || PO_LINE_TP.ITT.equals(rs.getString("PO_LINE_TP_CD"))) {

                        // Compare Po Detail.PO Received Quantity to Invoice Quantity.(PMT_HLD_CD_010, PMT_HLD_RSN_CD_013)
                        // START 2018/05/08 [QC#24981, MOD]
                        // if (NFBCommonBusiness.chkNull(rs.getBigDecimal("PO_RCV_QTY")).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(SUM_INV_QTY))) != 0) {
                        // START 2018/11/20 [QC#28660, MOD]
//                        if (NFBCommonBusiness.chkNull(rs.getBigDecimal("PO_RCV_QTY")).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(SUM_INV_QTY))) < 0) {
                        if (NFBCommonBusiness.chkNull(rs.getBigDecimal("PO_RCV_QTY")).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(TOT_INV_QTY))) < 0) {	
                        // END   2018/11/20 [QC#28660, MOD]
                        // END   2018/05/08 [QC#24981, MOD]
                            // START 2018/04/02 [QC#24277, MOD]
                            // setPmtHld(rs, invKeyList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013);
                            setPmtHld(rs, invLineKeyList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013);
                            // END   2018/04/02 [QC#24277, MOD]
                        }

                    } else {

                        // Compare Received Quantity to Invoice Quantity.(PMT_HLD_CD_010,PMT_HLD_RSN_CD_013)
                        // START 2018/05/16 [QC#26080, MOD]
                        // START 2018/04/02 [QC#20316, MOD]
                        // START 2018/10/18 [QC#28734, MOD]
                        // if (rs.getBigDecimal(INV_LINE_CNT).compareTo(BigDecimal.ONE) == 0) {
                        //       // START 2018/05/08 [QC#24981, MOD]
                        //       // if (NFBCommonBusiness.chkNull(rs.getBigDecimal(INV_RCV_QTY)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(INV_QTY))) != 0) {
                        //       if (NFBCommonBusiness.chkNull(rs.getBigDecimal(INV_RCV_QTY)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(INV_QTY))) < 0) {
                        //       // END   2018/05/08 [QC#24981, MOD]
                        //           // START 2018/04/02 [QC#24277, MOD]
                        //           // setPmtHld(rs, invKeyList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013);
                        //           setPmtHld(rs, invLineKeyList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013);
                        //           // END   2018/04/02 [QC#24277, MOD]
                        //       }
                        // } else {
                        //     // START 2018/05/08 [QC#24981, MOD]
                        //     // if (NFBCommonBusiness.chkNull(rs.getBigDecimal(INV_RCV_QTY)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(SUM_INV_QTY))) != 0) {
                        //     if (NFBCommonBusiness.chkNull(rs.getBigDecimal(INV_RCV_QTY)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(SUM_INV_QTY))) < 0) {
                        //     // END   2018/05/08 [QC#24981, MOD]
                        //         // START 2018/04/02 [QC#24277, MOD]
                        //         // setPmtHld(rs, invKeyList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013);
                        //         setPmtHld(rs, invLineKeyList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013);
                        //         // END   2018/04/02 [QC#24277, MOD]
                        //     }
                        // }
                        // START 2018/11/20 [QC#28660, MOD]
//                        if (NFBCommonBusiness.chkNull(rs.getBigDecimal(SUM_CSI_RCV_QTY)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(SUM_INV_QTY))) < 0) {
                        if (NFBCommonBusiness.chkNull(rs.getBigDecimal(SUM_CSI_RCV_QTY)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(TOT_INV_QTY))) < 0) {
                        // END   2018/11/20 [QC#28660, MOD]
                            setPmtHld(rs, invLineKeyList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013);
                        }
                        // END   2018/10/18 [QC#28734, MOD]
                        // END   2018/04/02 [QC#20316, MOD]
                        // END   2018/05/16 [QC#26080, MOD]

                        // Compare Ordered Quantity to Received  Quantity.(PMT_HLD_CD_010, PMT_HLD_RSN_CD_011)
                        // START 2018/05/08 [QC#24981, MOD]
                        // if (NFBCommonBusiness.chkNull(rs.getBigDecimal(PO_QTY)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(SUM_INV_RCV_QTY))) != 0) {
                        if (NFBCommonBusiness.chkNull(rs.getBigDecimal(PO_QTY)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(SUM_INV_RCV_QTY))) < 0) {
                        // END   2018/05/08 [QC#24981, MOD]
                            // START 2018/04/02 [QC#24277, MOD]
                            // setPmtHld(rs, invKeyList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011);
                            setPmtHld(rs, invLineKeyList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011);
                            // END   2018/04/02 [QC#24277, MOD]
                        }
                    }
                    // END 2017/12/14 T.Hakodate [SOL#060,MOD]
                } 

             // START 2017/12/14 T.Hakodate [SOL#060, DELETE]
//                else {
//                    // PO_MATCH_TP_CD = 'N'(No Goods Receipt)
//                    if (NFBCommonBusiness.chkNull(rs.getBigDecimal(PO_QTY)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(INV_QTY))) != 0) {
//                        setPmtHld(rs, invKeyList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012);
//                    }
//                }
             // END 2017/12/14 T.Hakodate [SOL#060, DELETE]
                
                // END 2017/10/20 T.Tokutomi [QC#21653,END]
                // START 2018/05/07 [QC#25828, MOD]
                // //START 2016/09/29 [QC#14799, ADD]
                // // Compare Invoice Amount and PO Amount with tolerance %
                // if ((NFBCommonBusiness.chkNull(rs.getBigDecimal(AC_OC_FOB_COST_AMT)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(FROM_TOL_NET_PRC_AMT))) < 0)
                //         || (NFBCommonBusiness.chkNull(rs.getBigDecimal(THRU_TOL_NET_PRC_AMT)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(AC_OC_FOB_COST_AMT))) < 0)) {
                //     // START 2018/04/02 [QC#24277, MOD]
                //     // setPmtHld(rs, invKeyList, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021);
                //     setPmtHld(rs, invLineKeyList, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021);
                //     // END   2018/04/02 [QC#24277, MOD]
                // }
                //END 2016/09/29 [QC#14799, ADD]
                // Compare Invoice Amount and PO Amount with tolerance %
                // START 2018/07/31 [QC#27025-1, MOD]
                // if ((NFBCommonBusiness.chkNull(rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT)).multiply(NFBCommonBusiness.chkNull(rs.getBigDecimal(INV_QTY))).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(FROM_TOL_NET_PRC_AMT))) < 0)
                //         || (NFBCommonBusiness.chkNull(rs.getBigDecimal(THRU_TOL_NET_PRC_AMT)).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT)).multiply(NFBCommonBusiness.chkNull(rs.getBigDecimal(INV_QTY)))) < 0)) {
                //     setPmtHld(rs, invLineKeyList, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021);
                // }
                // START 2020/02/17 [QC#55833, MOD]
                if ((NFBCommonBusiness.chkNull(rs.getBigDecimal(PD_ENT_DEAL_NET_UNIT_PRC_AMT)).multiply(NFBCommonBusiness.chkNull(rs.getBigDecimal(INV_QTY))).abs().compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(FROM_TOL_NET_PRC_AMT)).abs()) < 0)
                        || (NFBCommonBusiness.chkNull(rs.getBigDecimal(THRU_TOL_NET_PRC_AMT).abs()).compareTo(NFBCommonBusiness.chkNull(rs.getBigDecimal(PD_ENT_DEAL_NET_UNIT_PRC_AMT)).multiply(NFBCommonBusiness.chkNull(rs.getBigDecimal(INV_QTY))).abs()) < 0)) {
                    setPmtHld(rs, invLineKeyList, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021);
                }
                // END   2020/02/17 [QC#55833, MOD]
                // END   2018/07/31 [QC#27025-1, MOD]
                // END   2018/05/07 [QC#25828, MOD]

                // Create CM_INV_ACCT_DIST(MDSE) record
                // START 2017/01/23 E.Kameishi [QC#17090,MOD]
                apVndInvAcctDistNum = 1;
                // START 2018/03/26 [QC#20316, MOD]
                // createCmInvAcctDistForMdse(rs, apVndInvAcctDistNum, apVndInvLineNumItem);
                createCmInvAcctDistForMdse(rs, apVndInvAcctDistNum, apVndInvLineNum);
                // END   2018/03/26 [QC#20316, MOD]
                //START 2016/09/30 [QC#14797, ADD]
                apVndInvAcctDistNum++;
                //END 2016/09/30 [QC#14797, ADD]
                // START 2017/12/14 T.Hakodate [SOL#103,MOD]
                // BigDecimal entDealNetPrcAmt =
                // rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT).multiply(rs.getBigDecimal(INV_QTY));
                // ENT_DEAL_NET_UNIT_PRC_AMT from CM Stock IN
                BigDecimal entDealNetPrcAmt = null;
                Map<String, Object> cmStockInMap = new HashMap<String, Object>();
                cmStockInMap = getCmStockIn(rs);
                if (cmStockInMap != null) {
                    entDealNetPrcAmt = ((BigDecimal) cmStockInMap.get(ENT_DEAL_NET_UNIT_PRC_AMT)).multiply(rs.getBigDecimal(INV_QTY));
                // START 2018/11/08 [QC#28982, MOD]
                // } else {
                //     entDealNetPrcAmt = rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT).multiply(rs.getBigDecimal(INV_QTY));
                // }
                } else if (ZYPCommonFunc.hasValue(rs.getBigDecimal(PD_ENT_DEAL_NET_UNIT_PRC_AMT))) {
                    entDealNetPrcAmt = rs.getBigDecimal(PD_ENT_DEAL_NET_UNIT_PRC_AMT).multiply(rs.getBigDecimal(INV_QTY));
                } else {
                    entDealNetPrcAmt = rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT).multiply(rs.getBigDecimal(INV_QTY));
                }
                // END   2018/11/08 [QC#28982, MOD]
                // END 2017/12/14 T.Hakodate [SOL#103,MOD]

                BigDecimal varAmt = rs.getBigDecimal(AC_OC_FOB_COST_AMT).subtract(entDealNetPrcAmt);
                if (varAmt.compareTo(BigDecimal.ZERO) != 0) {
                    // Create CM_INV_ACCT_DIST(VAR) record
                    // START 2018/03/26 [QC#20316, MOD]
                    // createInvAcctDistForVar(rs, apVndInvAcctDistNum, varAmt, varCnt, apVndInvLineNumItem);
                    createInvAcctDistForVar(rs, apVndInvAcctDistNum, varAmt, varCnt, apVndInvLineNum);
                    // END   2018/03/26 [QC#20316, MOD]
                    varCnt++;
                    //apVndInvAcctDistNum++;
                }
                // START 2018/04/02 [QC#20316, MOD]
                // apVndInvLineNumItem++;
                // END   2018/04/02 [QC#20316, MOD]
                // END 2017/01/23 E.Kameishi [QC#17090,MOD]
            }
            return Boolean.TRUE;
        }

        private void setPmtHld(ResultSet rs, List<String> invKeyList, String pmtHldCd, String pmtHldRsnCd) throws SQLException {
            List<String> keyList = new ArrayList<String>();
            keyList.add(rs.getString(AP_VND_CD));
            keyList.add(rs.getString(AP_VND_INV_NUM));
            keyList.add(rs.getString(AP_VND_INV_SQ_NUM));
            // START 2018/04/02 [QC#24277, ADD]
            keyList.add(rs.getString(AP_VND_INV_LINE_NUM));
            // END   2018/04/02 [QC#24277, ADD]
            keyList.add(pmtHldCd);
            keyList.add(pmtHldRsnCd);
            if (!cmInvPmtHldKeyList.contains(keyList)) {
                Map<String, Object> hldMap = invHldMap.get(invKeyList);
                String pmtHldFlg = (String) hldMap.get(PMT_HLD_FLG);
                if (ZYPConstant.FLG_OFF_N.equals(pmtHldFlg)) {
                    hldMap.put(PMT_HLD_FLG, ZYPConstant.FLG_ON_Y);
                    hldMap.put(PMT_HLD_CD, pmtHldCd);
                    hldMap.put(PMT_HLD_RSN_CD, pmtHldRsnCd);
                    hldMap.put(PMT_HLD_REL_RSN_CD, EMPTY_STRING);
                    invHldMap.put(invKeyList, hldMap);
                }
                cmInvPmtHldKeyList.add(keyList);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(PO_NUM, rs.getString(CAID_PO_NUM));
                map.put(PMT_HLD_FLG, ZYPConstant.FLG_ON_Y);
                cmInvPmtHldMap.put(keyList, map);
            }
        }

        private List<String> setPoTotAmt(ResultSet rs) throws SQLException {
            List<String> invKeyList = new ArrayList<String>();
            invKeyList.add(rs.getString(AP_VND_CD));
            invKeyList.add(rs.getString(AP_VND_INV_NUM));
            invKeyList.add(rs.getString(AP_VND_INV_SQ_NUM));
            BigDecimal poTotAmt = (BigDecimal) poTotAmtMap.get(invKeyList);
            if (ZYPCommonFunc.hasValue(poTotAmt)) {
                poTotAmtMap.put(invKeyList, poTotAmt.add(rs.getBigDecimal(ENT_PO_DTL_DEAL_NET_AMT)));
            } else {
                poTotAmtMap.put(invKeyList, rs.getBigDecimal(ENT_PO_DTL_DEAL_NET_AMT));
            }
            return invKeyList;
        }

        // START 2018/03/26 [QC#20316, MOD]
        // private void createInvAcctDistForVar(ResultSet rs, int apVndInvAcctDistNum, BigDecimal varAmt, int varCnt, int apVndInvLineNumItem) throws SQLException {
        // START 2018/11/08 [QC#28982, MOD]
        // private void createInvAcctDistForVar(ResultSet rs, int apVndInvAcctDistNum, BigDecimal varAmt, int varCnt, String apVndInvLineNum) throws SQLException {
        protected void createInvAcctDistForVar(ResultSet rs, int apVndInvAcctDistNum, BigDecimal varAmt, int varCnt, String apVndInvLineNum) throws SQLException {
        // END   2018/11/08 [QC#28982, MOD]
        // END   2018/03/26 [QC#20316, MOD]
            CM_INV_ACCT_DISTTMsg cmInvAcctDist = new CM_INV_ACCT_DISTTMsg();
            NFBCommonBusiness.setZeroIntoCmInvAcctDist(cmInvAcctDist);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndCd, rs.getString(AP_VND_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvNum, rs.getString(AP_VND_INV_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
            // START 2017/01/23 E.Kameishi [QC#17090,MOD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.mdseCd, ZYPCommonFunc.concatString(VAR, ZYPCommonFunc.leftPad(String.valueOf(varCnt), 3, "0"), null));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvTpCd, AP_INV_TP_CD_00);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.poNum, rs.getString(CAID_PO_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.delyOrdNum, NONE);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.acInvJrnlCostAmt, varAmt);
            String coaCcCd = null;
            String coaAcctCd = null;
            String coaProdCd = null;
            String coaMdseCd = null;
            // START 2018/10/24 [QC#28857, ADD]
            String coaBrCd = null;
            String coaChCd = null;
            String coaAfflCd = null;
            String coaExtnCd = null;
            // END   2018/10/24 [QC#28857, ADD]
            List<Map> resultList = getCoaCdForVar(rs.getString(MDSE_CD));
            
            if (resultList != null) {
                coaCcCd = (String) resultList.get(0).get("COA_CC_CD");
                coaAcctCd = (String) resultList.get(0).get("COA_ACCT_CD");
                coaProdCd = (String) resultList.get(0).get("COA_PROD_CD");
                coaMdseCd = (String) resultList.get(0).get("COA_MDSE_TP_CD");
                // START 2018/10/24 [QC#28857, ADD]
                coaBrCd = (String) resultList.get(0).get("CM_COA_BR_CD");
                coaChCd = (String) resultList.get(0).get("CM_COA_CH_CD");
                coaAfflCd = (String) resultList.get(0).get("CM_COA_AFFL_CD");
                coaExtnCd = (String) resultList.get(0).get("CM_COA_EXTN_CD");
                // END   2018/10/24 [QC#28857, ADD]
            }

            DefCoaValues defCoa = NFBCommonBusiness.getDefCoaValues(glblCmpyCd);

            // START 2018/03/26 [QC#20316, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaCmpyCd, defCoa.getDefCoaCmpy());
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaBrCd, defCoa.getDefCoaBr());
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaCcCd, coaCcCd);
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaAcctCd, coaAcctCd);
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaProdCd, coaProdCd);
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaChCd, defCoa.getDefCoaCh());
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaAfflCd, defCoa.getDefCoaAffl());
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaProjCd, coaMdseCd);
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaExtnCd, VAR_COA_EXTN_CD_010);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCmpyCd, defCoa.getDefCoaCmpy());
            // START 2018/10/24 [QC#28857, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, defCoa.getDefCoaBr());
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, coaBrCd);
            // END   2018/10/24 [QC#28857, MOD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, coaCcCd);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAcctCd, coaAcctCd);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, coaProdCd);
            // START 2018/10/24 [QC#28857, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaChCd, defCoa.getDefCoaCh());
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAfflCd, defCoa.getDefCoaAffl());
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaChCd, coaChCd);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAfflCd, coaAfflCd);
            // END   2018/10/24 [QC#28857, MOD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProjCd, coaMdseCd);
            // START 2018/10/24 [QC#28857, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaExtnCd, VAR_COA_EXTN_CD_010);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaExtnCd, coaExtnCd);
            // END   2018/10/24 [QC#28857, MOD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCmpyCd, rs.getString(SPLY_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaBrCd, rs.getString(SPLY_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCcCd, rs.getString(SPLY_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAcctCd, rs.getString(SPLY_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProdCd, rs.getString(SPLY_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaChCd, rs.getString(SPLY_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAfflCd, rs.getString(SPLY_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProjCd, rs.getString(SPLY_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaExtnCd, rs.getString(SPLY_COA_EXTN_CD));
            // END   2018/03/26 [QC#20316, MOD]

            // END 2017/01/23 E.Kameishi [QC#17090,MOD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.ifProcStsCd, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apLineTpCd, varCharConstApLineTpVariance);
            //START 2016/09/30 [QC#14797, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.cmInvAcctDistLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvAcctDistNum), 3, "0"));
            //END 2016/09/30 [QC#14797, ADD]
            // START 2018/03/26 [QC#20316, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvLineNumItem), 3, "0"));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvLineNum, apVndInvLineNum);
            // END   2018/03/26 [QC#20316, MOD]
            // START 2018/09/05 [QC#28040, ADD]
            String coaAcctDescTxt = getCoaAcctDescTxt(glblCmpyCd, coaAcctCd);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apAcctDescTxt, coaAcctDescTxt);

            EZDItemAttribute getDigitApInvDescTxt = new CM_INV_ACCT_DISTTMsg().getAttr("apInvDescTxt");
            int txtDigit = getDigitApInvDescTxt.getDigit();

            if (ZYPCommonFunc.hasValue(coaAcctDescTxt) && coaAcctDescTxt.length() > txtDigit) {
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, S21StringUtil.subStringByLength(coaAcctDescTxt, 0, txtDigit));
            } else {
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, coaAcctDescTxt);
            }

            // START 2019/02/01 [QC#30142, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.poOrdDtlLineNum, rs.getString(PO_ORD_DTL_LINE_NUM));
            // END   2019/02/01 [QC#30142, ADD]

            // END 2018/09/05 [QC#28040, ADD]
            cmInvAcctDistInsertTMsgs[iInsertCntCmInvAcctDist] = cmInvAcctDist;
            iInsertCntCmInvAcctDist++;
            if (iInsertCntCmInvAcctDist == INT_BULK_COM_LIM) {
                int iRet = S21FastTBLAccessor.insert(cmInvAcctDistInsertTMsgs);
                if (iRet > 0) {
                    cmInvAcctDistInsertTMsgs = new CM_INV_ACCT_DISTTMsg[INT_BULK_COM_LIM];
                    cmInvAcctDistInsertCommitCount = cmInvAcctDistInsertCommitCount + iRet;
                    iInsertCntCmInvAcctDist = 0;
                } else {
                    cmInvAcctDistInsertCommitCount = 0;
                    throw new S21AbendException(ZZBM0074E);
                }
            }
        }

        private int createInvAcctDistForTax(ResultSet rs, int apVndInvAcctDistNum, int apVndInvLineNumOther) throws SQLException {
            CM_INV_ACCT_DISTTMsg cmInvAcctDist = new CM_INV_ACCT_DISTTMsg();
            NFBCommonBusiness.setZeroIntoCmInvAcctDist(cmInvAcctDist);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndCd, rs.getString(AP_VND_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvNum, rs.getString(AP_VND_INV_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.mdseCd, TAX);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvTpCd, AP_INV_TP_CD_00);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.poNum, rs.getString(CAID_PO_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.delyOrdNum, NONE);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.acInvJrnlCostAmt, rs.getBigDecimal(AC_SC_TOT_TAX_AMT));

            // START 2018/03/26 [QC#20316, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaCmpyCd, rs.getString(TAX_COA_CMPY_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaBrCd, rs.getString(TAX_COA_BR_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaCcCd, rs.getString(TAX_COA_CC_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaAcctCd, rs.getString(TAX_COA_ACCT_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaProdCd, rs.getString(TAX_COA_PROD_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaChCd, rs.getString(TAX_COA_CH_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaAfflCd, rs.getString(TAX_COA_AFFL_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaProjCd, rs.getString(TAX_COA_PROJ_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaExtnCd, rs.getString(TAX_COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCmpyCd, rs.getString(TAX_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, rs.getString(TAX_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, rs.getString(TAX_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAcctCd, rs.getString(TAX_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, rs.getString(TAX_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaChCd, rs.getString(TAX_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAfflCd, rs.getString(TAX_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProjCd, rs.getString(TAX_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaExtnCd, rs.getString(TAX_COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCmpyCd, rs.getString(SPLY_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaBrCd, rs.getString(SPLY_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCcCd, rs.getString(SPLY_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAcctCd, rs.getString(SPLY_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProdCd, rs.getString(SPLY_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaChCd, rs.getString(SPLY_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAfflCd, rs.getString(SPLY_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProjCd, rs.getString(SPLY_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaExtnCd, rs.getString(SPLY_COA_EXTN_CD));
            // END   2018/03/26 [QC#20316, MOD]

            // START 2018/12/21 [QC#29679, ADD]
            if (ZYPCommonFunc.hasValue(rs.getString(CAIH_PO_NUM))) {
                Map<String, Object> chrgAcct = getChargeAccountByLOB(rs.getString(CAIH_PO_NUM));
                if (chrgAcct != null) {
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, (String) chrgAcct.get(COA_BR_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, (String) chrgAcct.get(COA_CC_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, (String) chrgAcct.get(COA_PROD_CD));
                }
            }
            // END   2018/12/21 [QC#29679, ADD]

            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.ifProcStsCd, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apLineTpCd, varCharConstApLineTpTax);
            //START 2016/09/30 [QC#14797, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.cmInvAcctDistLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvAcctDistNum), 3, "0"));
            //END 2016/09/30 [QC#14797, ADD]
            // START 2018/03/26 [QC#20316, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvLineNumOther), 3, "0"));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvLineNumOther), 4, "0"));
            // END   2018/03/26 [QC#20316, MOD]
            // START 2018/09/05 [QC#28040, ADD]
            String coaAcctDescTxt = getCoaAcctDescTxt(glblCmpyCd, rs.getString(TAX_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apAcctDescTxt, coaAcctDescTxt);

            EZDItemAttribute getDigitApInvDescTxt = new CM_INV_ACCT_DISTTMsg().getAttr("apInvDescTxt");
            int txtDigit = getDigitApInvDescTxt.getDigit();

            if (ZYPCommonFunc.hasValue(coaAcctDescTxt) && coaAcctDescTxt.length() > txtDigit) {
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, S21StringUtil.subStringByLength(coaAcctDescTxt, 0, txtDigit));
            } else {
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, coaAcctDescTxt);
            }
            // END 2018/09/05 [QC#28040, ADD]
            cmInvAcctDistInsertTMsgs[iInsertCntCmInvAcctDist] = cmInvAcctDist;
            iInsertCntCmInvAcctDist++;
            if (iInsertCntCmInvAcctDist == INT_BULK_COM_LIM) {
                int iRet = S21FastTBLAccessor.insert(cmInvAcctDistInsertTMsgs);
                if (iRet > 0) {
                    cmInvAcctDistInsertTMsgs = new CM_INV_ACCT_DISTTMsg[INT_BULK_COM_LIM];
                    cmInvAcctDistInsertCommitCount = cmInvAcctDistInsertCommitCount + iRet;
                    iInsertCntCmInvAcctDist = 0;
                } else {
                    cmInvAcctDistInsertCommitCount = 0;
                    throw new S21AbendException(ZZBM0074E);
                }
            }
            return apVndInvAcctDistNum;
        }

        // START 2018/03/26 [QC#20316, DEL]
//        private void createCmInvAcctHdr(ResultSet rs) throws SQLException {
//            CM_INV_ACCT_HDRTMsg cmInvAcctHdr = new CM_INV_ACCT_HDRTMsg();
//            NFBCommonBusiness.setZeroIntoCmInvAcctHdr(cmInvAcctHdr);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.apVndCd, rs.getString(AP_VND_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.apVndInvNum, rs.getString(AP_VND_INV_NUM));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
//            // TODO start
//            // ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.locNum, rs.getString(LOC_NUM));
//            cmInvAcctHdr.locNum.clear(); // TODO temporary
//            // TODO end
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.invDt, rs.getString(INV_DT));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.poNum, rs.getString(CAIH_PO_NUM));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtDueDt, rs.getString(PMT_DUE_DT));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.vndPmtTermCd, rs.getString(VND_PMT_TERM_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.acctBankCd, rs.getString(ACCT_BANK_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.acctBankAcctPayTpCd, rs.getString(ACCT_BANK_ACCT_PAY_TP_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.invTpCd, rs.getString(INV_TP_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.acInvTotCostAmt, rs.getBigDecimal(AC_SC_TOT_GND_INV_AMT));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.payAloneFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaCmpyCd, rs.getString(SPLY_COA_CMPY_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaBrCd, rs.getString(SPLY_COA_BR_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaCcCd, rs.getString(SPLY_COA_CC_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaAcctCd, rs.getString(SPLY_COA_ACCT_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaProdCd, rs.getString(SPLY_COA_PROD_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaChCd, rs.getString(SPLY_COA_CH_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaAfflCd, rs.getString(SPLY_COA_AFFL_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaProjCd, rs.getString(SPLY_COA_PROJ_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaExtnCd, rs.getString(SPLY_COA_EXTN_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldFlg, rs.getString(PMT_HLD_FLG));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.ifProcStsCd, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.apInvCatgCd, apInvCatgCd);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.prntVndCd, rs.getString(PRNT_VND_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.jrnlLinkFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.cancJrnlLinkFlg, ZYPConstant.FLG_OFF_N);
//            cmInvAcctHdrInsertTMsgs[iInsertCntCmInvAcctHdr] = cmInvAcctHdr;
//            iInsertCntCmInvAcctHdr++;
//            if (iInsertCntCmInvAcctHdr == INT_BULK_COM_LIM) {
//                int iRet = S21FastTBLAccessor.insert(cmInvAcctHdrInsertTMsgs);
//                if (iRet > 0) {
//                    cmInvAcctHdrInsertTMsgs = new CM_INV_ACCT_HDRTMsg[INT_BULK_COM_LIM];
//                    cmInvAcctHdrInsertCommitCount = cmInvAcctHdrInsertCommitCount + iRet;
//                    iInsertCntCmInvAcctHdr = 0;
//                } else {
//                    cmInvAcctHdrInsertCommitCount = 0;
//                    throw new S21AbendException(ZZBM0074E);
//                }
//            }
//        }
        // END   2018/03/26 [QC#20316, DEL]

        // START 2018/03/26 [QC#20316, ADD]
        private void updateCmApInvHdr(ResultSet rs) throws SQLException {

            CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, rs.getString(AP_VND_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, rs.getString(AP_VND_INV_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
            cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
            if (cmApInvHdrTMsg != null) {

                ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acInvTotCostAmt, rs.getBigDecimal(AC_SC_TOT_GND_INV_AMT));
                ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.payAloneFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, rs.getString(PMT_HLD_FLG));
                ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.ifProcStsCd, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apInvCatgCd, apInvCatgCd);
                ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.prntVndCd, rs.getString(PRNT_VND_CD));
                ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.jrnlLinkFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.cancJrnlLinkFlg, ZYPConstant.FLG_OFF_N);

                cmApInvHdrUpdateForRegDistTMsgs[iUpdateCntCmApInvHdrForRegDist] = cmApInvHdrTMsg;
                iUpdateCntCmApInvHdrForRegDist++;
                if (iUpdateCntCmApInvHdrForRegDist == INT_BULK_COM_LIM) {
                    int iRet = S21FastTBLAccessor.update(cmApInvHdrUpdateForRegDistTMsgs);
                    if (iRet > 0) {
                        cmApInvHdrUpdateForRegDistTMsgs = new CM_AP_INV_HDRTMsg[INT_BULK_COM_LIM];
                        cmApInvHdrForRegDistCommitCount = cmApInvHdrForRegDistCommitCount + iRet;
                        iUpdateCntCmApInvHdrForRegDist = 0;
                    } else {
                        cmApInvHdrForRegDistCommitCount = 0;
                        throw new S21AbendException(NFBM0028E);
                    }
                }
            }
        }
        // END   2018/03/26 [QC#20316, ADD]

        // START 2018/03/26 [QC#20316, MOD]
        // private void createCmInvAcctDistForMdse(ResultSet rs, int apVndInvAcctDistNum, int apVndInvLineNumItem) throws SQLException {
        private void createCmInvAcctDistForMdse(ResultSet rs, int apVndInvAcctDistNum, String apVndInvLineNum) throws SQLException {
        // END   2018/03/26 [QC#20316, MOD]
            CM_INV_ACCT_DISTTMsg cmInvAcctDist = new CM_INV_ACCT_DISTTMsg();
            NFBCommonBusiness.setZeroIntoCmInvAcctDist(cmInvAcctDist);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndCd, rs.getString(AP_VND_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvNum, rs.getString(AP_VND_INV_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.mdseCd, rs.getString(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvTpCd, rs.getString(AP_INV_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.poNum, rs.getString(CAID_PO_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.delyOrdNum, rs.getString(DELY_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.invTpCd, rs.getString(INV_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.invQty, rs.getBigDecimal(INV_QTY));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.poQty, rs.getBigDecimal(PO_QTY));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.invRcvQty, rs.getBigDecimal(INV_RCV_QTY));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apBillQty, rs.getBigDecimal(INV_QTY));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apRejQty, rs.getBigDecimal(AP_REJ_QTY));
            // QC#18093 Mod Start
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.uomCd, rs.getString(UOM_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.mdseDescShortTxt, rs.getString(MDSE_DESC_SHORT_TXT));
            // QC#18093 Mod End
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.thisMthFobCostAmt, rs.getBigDecimal(THIS_MTH_FOB_COST_AMT));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.acInvTaxAmt, rs.getBigDecimal(AC_INV_TAX_AMT));

            // START 2017/12/14 T.Hakodate [SOL#103,MOD]
            // START 2017/01/23 E.Kameishi [QC#17090,ADD]
            // ENT_DEAL_NET_UNIT_PRC_AMT from CM Stock In
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.acInvJrnlCostAmt,
            // rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT).multiply(rs.getBigDecimal(INV_QTY)));
            Map<String, Object> cmStockInMap = getCmStockIn(rs);
            BigDecimal entDealNetUnitPrcAmt = null;
            BigDecimal entFuncNetUnitPrcAmt = null;

            if (cmStockInMap != null) {
                entDealNetUnitPrcAmt = (BigDecimal) cmStockInMap.get(ENT_DEAL_NET_UNIT_PRC_AMT);
                entFuncNetUnitPrcAmt = (BigDecimal) cmStockInMap.get(ENT_FUNC_NET_UNIT_PRC_AMT);
            // START 2018/11/15  [QC#28982, MOD]
            // } else {
            //     entDealNetUnitPrcAmt = rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT);
            //     entFuncNetUnitPrcAmt = rs.getBigDecimal(ENT_FUNC_NET_UNIT_PRC_AMT);
            } else if (ZYPCommonFunc.hasValue(rs.getString(PD_ENT_DEAL_NET_UNIT_PRC_AMT))) {
                entDealNetUnitPrcAmt = rs.getBigDecimal(PD_ENT_DEAL_NET_UNIT_PRC_AMT);
                entFuncNetUnitPrcAmt = rs.getBigDecimal(PD_ENT_FUNC_NET_UNIT_PRC_AMT);
            // END   2018/11/15  [QC#28982, MOD]
            } else {
                entDealNetUnitPrcAmt = rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT);
                entFuncNetUnitPrcAmt = rs.getBigDecimal(ENT_FUNC_NET_UNIT_PRC_AMT);
            }
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.acInvJrnlCostAmt, entDealNetUnitPrcAmt.multiply(rs.getBigDecimal(INV_QTY)));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.acInvJrnlCostAmt,
            // rs.getBigDecimal(AC_INV_JRNL_COST_AMT));
            // END 2017/01/23 E.Kameishi [QC#17090,ADD]
            // END 2017/12/14 T.Hakodate [SOL#103,MOD]
            
            // START 2016/09/08 [QC#14161, MOD]
            // START 2016/12/09 [QC#16132, MOD]
            DefCoaValues defCoa = NFBCommonBusiness.getDefCoaValues(glblCmpyCd);

            // START 2018/03/26 [QC#20316, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaCmpyCd, setCoaVal(rs.getString(MDSE_COA_CMPY_CD), defCoa.getDefCoaCmpy()));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaBrCd, setCoaVal(rs.getString(MDSE_COA_BR_CD), defCoa.getDefCoaBr()));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaCcCd, setCoaVal(rs.getString(MDSE_COA_CC_CD), defCoa.getDefCoaCc()));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaAcctCd, rs.getString(MDSE_COA_ACCT_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaProdCd, setCoaVal(rs.getString(MDSE_COA_PROD_CD), defCoa.getDefCoaProd()));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaChCd, setCoaVal(rs.getString(MDSE_COA_CH_CD), defCoa.getDefCoaCh()));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaAfflCd, rs.getString(MDSE_COA_AFFL_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaProjCd, setCoaVal(rs.getString(MDSE_COA_PROJ_CD), defCoa.getDefCoaProj()));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaExtnCd, setCoaVal(rs.getString(MDSE_COA_EXTN_CD), defCoa.getDefCoaExtn()));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCmpyCd, setCoaVal(rs.getString(MDSE_COA_CMPY_CD), defCoa.getDefCoaCmpy()));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, setCoaVal(rs.getString(MDSE_COA_BR_CD), defCoa.getDefCoaBr()));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, setCoaVal(rs.getString(MDSE_COA_CC_CD), defCoa.getDefCoaCc()));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAcctCd, rs.getString(MDSE_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, setCoaVal(rs.getString(MDSE_COA_PROD_CD), defCoa.getDefCoaProd()));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaChCd, setCoaVal(rs.getString(MDSE_COA_CH_CD), defCoa.getDefCoaCh()));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAfflCd, rs.getString(MDSE_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProjCd, setCoaVal(rs.getString(MDSE_COA_PROJ_CD), defCoa.getDefCoaProj()));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaExtnCd, setCoaVal(rs.getString(MDSE_COA_EXTN_CD), defCoa.getDefCoaExtn()));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCmpyCd, rs.getString(SPLY_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaBrCd, rs.getString(SPLY_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCcCd, rs.getString(SPLY_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAcctCd, rs.getString(SPLY_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProdCd, rs.getString(SPLY_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaChCd, rs.getString(SPLY_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAfflCd, rs.getString(SPLY_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProjCd, rs.getString(SPLY_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaExtnCd, rs.getString(SPLY_COA_EXTN_CD));
            // END 2018/03/26 [QC#20316, MOD]

            // END 2016/12/09 [QC#16132, MOD]
            // END 2016/09/08 [QC#14161, MOD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.ifProcStsCd, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);

            // START 2017/12/14 T.Hakodate [SOL#103,MOD]
            // ENT_DEAL_NET_UNIT_PRC_AMT from CM Stock In
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.entDealNetUnitPrcAmt,
            // rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.entDealNetUnitPrcAmt, entDealNetUnitPrcAmt);

            // END 2017/12/14 T.Hakodate [SOL#103,MOD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.entPoDtlDealNetAmt, rs.getBigDecimal(ENT_PO_DTL_DEAL_NET_AMT));

            // START 2017/12/14 T.Hakodate [SOL#103,MOD]
            // ENT_FUNC_NET_UNIT_PRC_AMT from CM Stock In
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.entFuncNetUnitPrcAmt,
            // rs.getBigDecimal(ENT_FUNC_NET_UNIT_PRC_AMT));
            // START 2018/11/15  [QC#28982, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.entDealNetUnitPrcAmt, entFuncNetUnitPrcAmt);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.entFuncNetUnitPrcAmt, entFuncNetUnitPrcAmt);
            // END   2018/11/15  [QC#28982, MOD]
            // END 2017/12/14 T.Hakodate [SOL#103,MOD]
            
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.entPoDtlFuncNetAmt, rs.getBigDecimal(ENT_PO_DTL_FUNC_NET_AMT));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.exchRate, rs.getBigDecimal(EXCH_RATE));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apLineTpCd, varCharConstApLineTpItem);
            //START 2016/09/30 [QC#14797, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.cmInvAcctDistLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvAcctDistNum), 3, "0"));
            //END 2016/09/30 [QC#14797, ADD]
            // START 2018/03/26 [QC#20316, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvLineNumItem), 3, "0"));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvLineNum, apVndInvLineNum);
            // END   2018/03/26 [QC#20316, MOD]
            // START 2018/09/05 [QC#28040, ADD]
            String coaAcctDescTxt = getCoaAcctDescTxt(glblCmpyCd, rs.getString(MDSE_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apAcctDescTxt, coaAcctDescTxt);

            EZDItemAttribute getDigitApInvDescTxt = new CM_INV_ACCT_DISTTMsg().getAttr("apInvDescTxt");
            int txtDigit = getDigitApInvDescTxt.getDigit();

            if (ZYPCommonFunc.hasValue(coaAcctDescTxt) && coaAcctDescTxt.length() > txtDigit) {
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, S21StringUtil.subStringByLength(coaAcctDescTxt, 0, txtDigit));
            } else {
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, coaAcctDescTxt);
            }
            // END 2018/09/05 [QC#28040, ADD]

            // START 2019/02/01 [QC#30142, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.poOrdDtlLineNum, rs.getString(PO_ORD_DTL_LINE_NUM));
            // END   2019/02/01 [QC#30142, ADD]

            cmInvAcctDistInsertTMsgs[iInsertCntCmInvAcctDist] = cmInvAcctDist;
            iInsertCntCmInvAcctDist++;
            if (iInsertCntCmInvAcctDist == INT_BULK_COM_LIM) {
                int iRet = S21FastTBLAccessor.insert(cmInvAcctDistInsertTMsgs);
                if (iRet > 0) {
                    cmInvAcctDistInsertTMsgs = new CM_INV_ACCT_DISTTMsg[INT_BULK_COM_LIM];
                    cmInvAcctDistInsertCommitCount = cmInvAcctDistInsertCommitCount + iRet;
                    iInsertCntCmInvAcctDist = 0;
                } else {
                    cmInvAcctDistInsertCommitCount = 0;
                    throw new S21AbendException(ZZBM0074E);
                }
            }
        }
    }
    /**
     * Initialize Commit Count.
     */
    private void initCommitCount() {
        // START 2018/03/26 [QC#20316, DEL]
        // this.cmInvAcctHdrInsertCommitCount = 0;
        // END   2018/03/26 [QC#20316, DEL]
        this.cmInvAcctDistInsertCommitCount = 0;
        this.cmInvPmtHldInsertCommitCount = 0;
        // START 2018/03/26 [QC#20316, DEL]
        // this.cmInvAcctHdrUpdateCommitCount = 0;
        // END   2018/03/26 [QC#20316, DEL]
        this.cmInvPmtHldDeleteCommitCount = 0;
        this.totalCommitCount = 0;
        this.cmApInvHdrUpdateCommitCount = 0;
        // START 2018/11/09 [QC#28982, ADD]
        this.cmApInvDtlUpdateCommitCount = 0;
        this.cmInvAcctDistUpdateCommitCount = 0;
        this.cmInvAcctDistDeleteCommitCount = 0;
        // END   2018/11/09 [QC#28982, ADD]
    }
    /**
     * Initialize Bulk TBL Accessor.
     */
    private void initBulkTBLAccessor() {
        // START 2018/03/26 [QC#20316, MOD]
        // this.cmInvAcctHdrInsertTMsgs = new CM_INV_ACCT_HDRTMsg[INT_BULK_COM_LIM];
        this.cmApInvHdrUpdateForRegDistTMsgs = new CM_AP_INV_HDRTMsg[INT_BULK_COM_LIM];
        // END   2018/03/26 [QC#20316, MOD]
        this.cmInvAcctDistInsertTMsgs = new CM_INV_ACCT_DISTTMsg[INT_BULK_COM_LIM];
        this.cmInvPmtHldInsertTMsgs = new CM_INV_PMT_HLDTMsg[INT_BULK_COM_LIM];
        // START 2018/03/26 [QC#20316, DEL]
        // this.cmInvAcctHdrUpdateTMsgs = new CM_INV_ACCT_HDRTMsg[INT_BULK_COM_LIM];
        // END   2018/03/26 [QC#20316, DEL]
        this.cmApInvHdrUpdateTMsgs = new CM_AP_INV_HDRTMsg[INT_BULK_COM_LIM];
    }
    /**
     * Initialize Bulk Count.
     */
    private void initBulkCount() {
        // START 2018/03/30 [QC#20316, MOD]
        // this.iInsertCntCmInvAcctHdr = 0;
        this.iUpdateCntCmApInvHdrForRegDist = 0;
        // END   2018/03/30 [QC#20316, MOD]
        this.iInsertCntCmInvAcctDist = 0;
        this.iInsertCntCmInvPmtHld = 0;
        // START 2018/03/30 [QC#20316, DEL]
        // this.iUpdateCntCmInvAcctHdr = 0;
        // START 2018/03/30 [QC#20316, DEL]
        this.iUpdateCntCmApInvHdr = 0;
    }

    // START 2016/11/15 T.Tsuchida [QC#15982,DEL]
//    /**
//     * updatePoInformation
//     * @param poOrdNum String
//     * @param poOrdDtlLineNum String
//     * @param poInvQty BigDecimal
//     * @param mdseCd String
//     * @return xxMsgId String
//     */
//    private String updatePoInformation(String poOrdNum, String poOrdDtlLineNum, BigDecimal poInvQty, String mdseCd) {
//        // String xxRqstTs = ZYPDateUtil.getCurrentSystemTime(YYYYMMDDHHMMSSSSS);
//        // String poSubmtPsnCd = PSN_CD_NFBB1180;
//        // 0) Invoice Receive
//        NPZC004001PMsg pMsg = new NPZC004001PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, poOrdNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, poOrdDtlLineNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.poInvQty, poInvQty);
//        NPZC004001 apiPOUpdate = new NPZC004001();
//        apiPOUpdate.execute(pMsg, ONBATCH_TYPE.BATCH);
//        if (S21ApiUtil.isXxMsgId(pMsg)) {
//            // Error
//            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
//                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)
//                && !pMsg.xxMsgIdList.no(i).xxMsgId.getValue().equals(NPZM0021E)
//                ) {
//                    return pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
//                }
//            }
//        }
////        if (invtyCtrlFlg.equals(ZYPConstant.FLG_OFF_N)) {
////            // 1) Stock-in
////            pMsg = new NPZC004001PMsg();
////            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
////            ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, poOrdNum);
////            ZYPEZDItemValueSetter.setValue(pMsg.poStsCd, PO_STS.RECEIVING);
////            ZYPEZDItemValueSetter.setValue(pMsg.poRcvQty, poInvQty);
////            ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, poOrdDtlLineNum);
////            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseCd);
////            apiPOUpdate = new NPZC004001();
////            apiPOUpdate.execute(pMsg, ONBATCH_TYPE.BATCH);
////            if (S21ApiUtil.isXxMsgId(pMsg)) {
////                // Error
////                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
////                    if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)
////                    && !pMsg.xxMsgIdList.no(i).xxMsgId.getValue().equals(NPZM0021E)
////                    ) {
////                        return pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
////                    }
////                }
////            }
////            // 2) Stock-in Completion
////            pMsg = new NPZC004001PMsg();
////            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
////            ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, poOrdNum);
////            ZYPEZDItemValueSetter.setValue(pMsg.poStsCd, PO_STS.RECEIVING_COMPLETION);
////            ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, poOrdDtlLineNum);
////            apiPOUpdate = new NPZC004001();
////            apiPOUpdate.execute(pMsg, ONBATCH_TYPE.BATCH);
////            if (S21ApiUtil.isXxMsgId(pMsg)) {
////                // Error
////                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
////                    if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)
////                    && !pMsg.xxMsgIdList.no(i).xxMsgId.getValue().equals(NPZM0021E)
////                    ) {
////                        return pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
////                    }
////                }
////            }
////        }
//        return null;
//    }
    // END 2016/11/15 T.Tsuchida [QC#15982,DEL]

    // START 2016/09/29 [QC#14799, DEL]
//    /**
//     * checkTotalAmount
//     * @return boolean
//     */
//    private void checkTotalAmount() {
//        for (int i = 0; i < cmInvAcctHdrKeyList.size(); i++) {
//            List<String> invKeyList = (List<String>) cmInvAcctHdrKeyList.get(i);
//            Map<String, Object> hldMap = invHldMap.get(invKeyList);
//            String pmtHldFlg = (String) hldMap.get(PMT_HLD_FLG);
//            if (ZYPConstant.FLG_OFF_N.equals(pmtHldFlg)) {
//                // String apVndCd = invKeyList.get(0);
//                // String apVndInvNum = invKeyList.get(1);
//                // String apVndInvSqNum = invKeyList.get(2);
//                BigDecimal invTotAmt = (BigDecimal)invTotAmtMap.get(invKeyList);
//                BigDecimal poTotAmt = (BigDecimal)poTotAmtMap.get(invKeyList);
//                if (invTotAmt.compareTo(poTotAmt) != 0) {
//                    hldMap.put(PMT_HLD_FLG, ZYPConstant.FLG_ON_Y);
//                    hldMap.put(PMT_HLD_CD, PMT_HLD_CD_020);
//                    hldMap.put(PMT_HLD_RSN_CD, PMT_HLD_RSN_CD_021);
//                    hldMap.put(PMT_HLD_REL_RSN_CD, EMPTY_STRING);
//                    invHldMap.put(invKeyList, hldMap);
//
//                    String apVndCd = invKeyList.get(0);
//                    String apVndInvNum = invKeyList.get(1);
//                    String apVndInvSqNum = invKeyList.get(2);
//                    List<String> keyList = new ArrayList<String>();
//                    keyList.add(apVndCd);
//                    keyList.add(apVndInvNum);
//                    keyList.add(apVndInvSqNum);
//                    keyList.add(PMT_HLD_CD_020);
//                    keyList.add(PMT_HLD_RSN_CD_021);
//                    if (!cmInvPmtHldKeyList.contains(keyList)) {
//                        cmInvPmtHldKeyList.add(keyList);
//                        Map<String, Object> map = new HashMap<String, Object>();
//                        map.put(PO_NUM, EMPTY_STRING);
//                        map.put(PMT_HLD_FLG, ZYPConstant.FLG_ON_Y);
//                        cmInvPmtHldMap.put(keyList, map);
//                    }
//                }
//            }
//        }
//    }
    // END 2016/09/29 [QC#14799, DEL]

    /**
     * updateHoldInfo
     */
    @SuppressWarnings("unchecked")
    private void updateCmInvAcctHdrHoldInfo() {

        for (int i = 0; i < cmInvAcctHdrKeyList.size(); i++) {
            List<String> invKeyList = (List<String>) cmInvAcctHdrKeyList.get(i);
            String apVndCd = invKeyList.get(0);
            String apVndInvNum = invKeyList.get(1);
            String apVndInvSqNum = invKeyList.get(2);

            // START 2018/04/02 [QC#24277, MOD]
            // Map<String, Object> hldMap = invHldMap.get(invKeyList);
            // String pmtHldFlg = (String) hldMap.get(PMT_HLD_FLG);
            // String pmtHldCd = (String) hldMap.get(PMT_HLD_CD);
            // String pmtHldRsnCd = (String) hldMap.get(PMT_HLD_RSN_CD);
            // String pmtHldRelRsnCd = (String) hldMap.get(PMT_HLD_REL_RSN_CD);
            String pmtHldFlg = ZYPConstant.FLG_OFF_N;
            for (List<String> hldMapKeyList : invHldMap.keySet()) {
                // START 2018/11/01 [QC#29022, MOD]
                // String hldApVndCd = invKeyList.get(0);
                // String hldApVndInvNum = invKeyList.get(1);
                // String hldApVndInvSqNum = invKeyList.get(2);
                String hldApVndCd = hldMapKeyList.get(0);
                String hldApVndInvNum = hldMapKeyList.get(1);
                String hldApVndInvSqNum = hldMapKeyList.get(2);
                // END   2018/11/01 [QC#29022, MOD]
                if (!hldApVndCd.equals(apVndCd) || !hldApVndInvNum.equals(apVndInvNum) || !hldApVndInvSqNum.equals(apVndInvSqNum)) {
                    continue;
                }
                Map<String, Object> hldMap = invHldMap.get(hldMapKeyList);
                if (ZYPConstant.FLG_ON_Y.equals((String) hldMap.get(PMT_HLD_FLG))) {
                    pmtHldFlg =ZYPConstant.FLG_ON_Y;
                    break;
                }
            }
            // END   2018/04/02 [QC#24277, MOD]

            // START 2018/03/30 [QC#20316, MOD]
//            CM_INV_ACCT_HDRTMsg cmInvAcctHdr = new CM_INV_ACCT_HDRTMsg();
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.apVndCd, apVndCd);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.apVndInvNum, apVndInvNum);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.apVndInvSqNum, apVndInvSqNum);
//            cmInvAcctHdr = (CM_INV_ACCT_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmInvAcctHdr);
//            if (cmInvAcctHdr != null) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldFlg, pmtHldFlg);
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldCd, pmtHldCd);
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldDt, cmProcDt);
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldRsnCd, pmtHldRsnCd);
//                if (pmtHldFlg.equals(ZYPConstant.FLG_ON_Y)) {
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldPsnCd, PSN_CD_NFBB1180);
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.acctInvStsCd, acctInvStsCdEntered);
//                } else {
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldPsnCd, EMPTY_STRING);
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.acctInvStsCd, acctInvStsCdAuthorized);
            CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, apVndCd);
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, apVndInvNum);
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, apVndInvSqNum);
            cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
            if (cmApInvHdrTMsg != null) {
                ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, pmtHldFlg);
                if (pmtHldFlg.equals(ZYPConstant.FLG_ON_Y)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctInvStsCd, acctInvStsCdEntered);
                } else {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctInvStsCd, acctInvStsCdAuthorized);
            // END   2018/03/30 [QC#20316, MOD]
                    
                    // START 2016/11/15 T.Tsuchida [QC#15982,MOD]
                    //List<Map> listCmApInvDtl = (List<Map>)getListCmApInvDtlByPartialKey(apVndCd, apVndInvNum, apVndInvSqNum);
                    //for (int j = 0; j < listCmApInvDtl.size(); j++) {
                    //    Map map = (Map)listCmApInvDtl.get(j);
                    //    String msgId = updatePoInformation((String)map.get(PO_NUM), (String)map.get(PO_ORD_DTL_LINE_NUM), (BigDecimal)map.get(INV_QTY), (String)map.get(MDSE_CD));
                    //    if (msgId != null) {
                    //        throw new S21AbendException(msgId);
                    //    }
                    //}
                    // 2017/06/16 QC#19251 MOD (QC#18557 DEL) START
                    CM_AP_INV_DTLTMsgArray cmApInvDtlTMsgArray = findCmApInvDtlList(glblCmpyCd, apVndCd, apVndInvNum, apVndInvSqNum);
                    for (int index = 0; index < cmApInvDtlTMsgArray.length(); index++) {
                        CM_AP_INV_DTLTMsg cmApInvDtlTMsg = cmApInvDtlTMsgArray.no(index);
                        // START 2019/08/29 [QC#52280, MOD]
                        // String msgId = callPoStsUpdateAPI(cmApInvDtlTMsg);
                        String msgId = callPoStsUpdateAPI(cmApInvHdrTMsg, cmApInvDtlTMsg);
                        // END   2019/08/29 [QC#52280, MOD]
                        if (msgId != null) {
                            throw new S21AbendException(msgId);
                        }
                    }
                    // 2017/06/16 QC#19251 MOD (QC#18557 DEL) START
                    // END 2016/11/15 T.Tsuchida [QC#15982,MOD]
                    // QC#19129 START
                    // START 2018/03/30 [QC#20316, DEL]
//                    CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
//                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, this.glblCmpyCd);
//                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, cmInvAcctHdr.apVndCd);
//                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, cmInvAcctHdr.apVndInvNum);
//                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, cmInvAcctHdr.apVndInvSqNum);
//                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apInvTpCd, AP_INV_TP_CD_00);
//                    cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
//                    if (cmApInvHdrTMsg != null) {
//                        if (ZYPConstant.FLG_ON_Y.equals(cmApInvHdrTMsg.pmtHldFlg.getValue())) {
//                            // Release
//                            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
//                            cmApInvHdrUpdateTMsgs[iUpdateCntCmApInvHdr] = cmApInvHdrTMsg;
//                            iUpdateCntCmApInvHdr++;
//                            if (iUpdateCntCmApInvHdr == INT_BULK_COM_LIM) {
//                                int iRet = S21FastTBLAccessor.update(cmApInvHdrUpdateTMsgs);
//                                if (iRet > 0) {
//                                    cmApInvHdrUpdateTMsgs = new CM_AP_INV_HDRTMsg[INT_BULK_COM_LIM];
//                                    cmApInvHdrUpdateCommitCount = cmApInvHdrUpdateCommitCount + iRet;
//                                    iUpdateCntCmInvAcctHdr = 0;
//                                } else {
//                                    cmApInvHdrUpdateCommitCount = 0;
//                                    throw new S21AbendException(NFBM0028E);
//                                }
//                            }
//                        }
//                    }
                    // END   2018/03/30 [QC#20316, DEL]
                    // QC#19129 END
                }

                // START 2018/03/30 [QC#20316, MOD]
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldRelPsnCd, EMPTY_STRING);
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldRelDt, EMPTY_STRING);
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldRelRsnCd, pmtHldRelRsnCd);
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldRelCmntTxt, EMPTY_STRING);
//                cmInvAcctHdrUpdateTMsgs[iUpdateCntCmInvAcctHdr] = cmInvAcctHdr;
//                iUpdateCntCmInvAcctHdr++;
//                if (iUpdateCntCmInvAcctHdr == INT_BULK_COM_LIM) {
//                    int iRet = S21FastTBLAccessor.update(cmInvAcctHdrUpdateTMsgs);
//                    if (iRet > 0) {
//                        cmInvAcctHdrUpdateTMsgs = new CM_INV_ACCT_HDRTMsg[INT_BULK_COM_LIM];
//                        cmInvAcctHdrUpdateCommitCount = cmInvAcctHdrUpdateCommitCount + iRet;
//                        iUpdateCntCmInvAcctHdr = 0;
//                    } else {
//                        cmInvAcctHdrUpdateCommitCount = 0;
//                        throw new S21AbendException(NFBM0028E);
//                    }
//                }
                cmApInvHdrUpdateTMsgs[iUpdateCntCmApInvHdr] = cmApInvHdrTMsg;
                iUpdateCntCmApInvHdr++;
                if (iUpdateCntCmApInvHdr == INT_BULK_COM_LIM) {
                    int iRet = S21FastTBLAccessor.update(cmApInvHdrUpdateTMsgs);
                    if (iRet > 0) {
                        cmApInvHdrUpdateTMsgs = new CM_AP_INV_HDRTMsg[INT_BULK_COM_LIM];
                        cmApInvHdrUpdateCommitCount = cmApInvHdrUpdateCommitCount + iRet;
                        iUpdateCntCmApInvHdr = 0;
                    } else {
                        cmApInvHdrUpdateCommitCount = 0;
                        throw new S21AbendException(NFBM0028E);
                    }
                }
                // END   2018/03/30 [QC#20316, MOD]

            }
        }
    }

    /**
     * deleteCmInvPmtHld
     */
    private void deleteCmInvPmtHld() {
        for (int i = 0; i < cmInvAcctHdrKeyList.size(); i++) {
            List<String> invKeyList = (List<String>) cmInvAcctHdrKeyList.get(i);
            String apVndCd = invKeyList.get(0);
            String apVndInvNum = invKeyList.get(1);
            String apVndInvSqNum = invKeyList.get(2);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("apVndCd", apVndCd);
            ssmParam.put("apVndInvNum", apVndInvNum);
            ssmParam.put("apVndInvSqNum", apVndInvSqNum);

            List<Map<String, String>> listCmInvPmtHld = (List<Map<String, String>>) ssmBatchClient.queryObjectList(SELECT_CM_INV_PMT_HLD_BY_PARTIAL_KEY, ssmParam);
            if (listCmInvPmtHld != null) {
                for (int j = 0; j < listCmInvPmtHld.size(); j++) {
                    Map<String, String> mapCmInvPmtHld = (Map<String, String>)listCmInvPmtHld.get(j);
                    String apVndInvLineNum = (String) mapCmInvPmtHld.get(AP_VND_INV_LINE_NUM);
                    String pmtHldCd = (String) mapCmInvPmtHld.get(PMT_HLD_CD);
                    String pmtHldRsnCd = (String) mapCmInvPmtHld.get(PMT_HLD_RSN_CD);

                    CM_INV_PMT_HLDTMsg cmInvPmtHld = new CM_INV_PMT_HLDTMsg();
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHld.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndCd, apVndCd);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvNum, apVndInvNum);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvSqNum, apVndInvSqNum);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvLineNum, apVndInvLineNum);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHld.pmtHldCd, pmtHldCd);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHld.pmtHldRsnCd, pmtHldRsnCd);
                    EZDTBLAccessor.remove(cmInvPmtHld);
                    cmInvPmtHldDeleteCommitCount++;
                }
            }
        }
    }

    /**
     * createCmInvPmtHld
     */
    private void createCmInvPmtHld() {

        // Create CM_INV_PMT_HLD records having PMT_HLD_CD 000, PMT_HLD_RSN_CD 000.
        for (int i = 0; i < cmInvAcctHdrKeyList.size(); i++) {
            List<String> invKeyList = (List<String>) cmInvAcctHdrKeyList.get(i);
            String apVndCd = invKeyList.get(0);
            String apVndInvNum = invKeyList.get(1);
            String apVndInvSqNum = invKeyList.get(2);

            // START 2018/04/02 [QC#24277, MOD]
            // Map<String, Object> hldMap = invHldMap.get(invKeyList);
            // String pmtHldFlg = (String) hldMap.get(PMT_HLD_FLG);
            // String pmtHldCd = (String) hldMap.get(PMT_HLD_CD);
            // String pmtHldRsnCd = (String) hldMap.get(PMT_HLD_RSN_CD);
            // // String pmtHldRelRsnCd = (String) hldMap.get(PMT_HLD_REL_RSN_CD);
            // if (ZYPConstant.FLG_OFF_N.equals(pmtHldFlg)) {
            //      CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
            //      ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, glblCmpyCd);
            //      ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, apVndCd);
            //      ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, apVndInvNum);
            //      ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, apVndInvSqNum);
            //      ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, pmtHldCd);
            //      ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, pmtHldRsnCd);
            //      cmInvPmtHldTMsg = (CM_INV_PMT_HLDTMsg) S21FastTBLAccessor.findByKey(cmInvPmtHldTMsg);
            //      if (cmInvPmtHldTMsg == null) {
            //          cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
            //          ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, glblCmpyCd);
            //          ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, apVndCd);
            //          ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, apVndInvNum);
            //          ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, apVndInvSqNum);
            //          ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, pmtHldCd);
            //          ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, pmtHldRsnCd);
            //          ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, EMPTY_STRING);
            //          ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
            //          ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, EMPTY_STRING);
            //          ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldPsnCd, EMPTY_STRING);
            //          ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelPsnCd, EMPTY_STRING);
            //          ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelDt, EMPTY_STRING);
            //          ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelRsnCd, EMPTY_STRING);
            //          ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelCmntTxt, EMPTY_STRING);
            //          insertCmInvPmtHldCommon(cmInvPmtHldTMsg);
            //      }
            //  }
            String pmtHldFlg = ZYPConstant.FLG_OFF_N;
            for (List<String> hldMapKeyList : invHldMap.keySet()) {
                String hldApVndCd = hldMapKeyList.get(0);
                String hldApVndInvNum = hldMapKeyList.get(1);
                String hldApVndInvSqNum = hldMapKeyList.get(2);
                if (!hldApVndCd.equals(apVndCd) || !hldApVndInvNum.equals(apVndInvNum) || !hldApVndInvSqNum.equals(apVndInvSqNum)) {
                    continue;
                }
                Map<String, Object> hldMap = invHldMap.get(hldMapKeyList);
                pmtHldFlg = (String) hldMap.get(PMT_HLD_FLG);
                if (ZYPConstant.FLG_ON_Y.equals(pmtHldFlg)) {
                    break;
                }
            }
            // END   2018/04/02 [QC#24277, MOD]

            if (ZYPConstant.FLG_OFF_N.equals(pmtHldFlg)) {
                CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, apVndCd);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, apVndInvNum);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, apVndInvSqNum);
                // START 2018/04/02 [QC#24277, MOD]
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, AP_VND_INV_LINE_NUM_0000);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, PMT_HLD_CD_000);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, PMT_HLD_RSN_CD_000);
                // END   2018/04/02 [QC#24277, MOD]
                cmInvPmtHldTMsg = (CM_INV_PMT_HLDTMsg) S21FastTBLAccessor.findByKey(cmInvPmtHldTMsg);
                if (cmInvPmtHldTMsg == null) {
                    cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, apVndCd);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, apVndInvNum);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, apVndInvSqNum);
                    // START 2018/04/02 [QC#24277, MOD]
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, AP_VND_INV_LINE_NUM_0000);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, PMT_HLD_CD_000);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd,  PMT_HLD_RSN_CD_000);
                    // END   2018/04/02 [QC#24277, MOD]
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldPsnCd, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelPsnCd, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelDt, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelRsnCd, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelCmntTxt, EMPTY_STRING);
                    insertCmInvPmtHldCommon(cmInvPmtHldTMsg);
                }
            }
        }

        // Create CM_INV_PMT_HLD records not having PMT_HLD_CD 000, PMT_HLD_RSN_CD 000.
        for (int i = 0; i < cmInvPmtHldKeyList.size(); i++) {
            List<String> keyList = (List<String>) cmInvPmtHldKeyList.get(i);
            String apVndCd = keyList.get(0);
            String apVndInvNum = keyList.get(1);
            String apVndInvSqNum = keyList.get(2);
            // START 2018/04/02 [QC#24277, MOD]
            // String pmtHldCd = keyList.get(3);
            // String pmtHldRsnCd = keyList.get(4);
            String apVndInvLineNum = keyList.get(3);
            String pmtHldCd = keyList.get(4);
            String pmtHldRsnCd = keyList.get(5);
            // END   2018/04/02 [QC#24277, ADD]
            CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, apVndCd);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, apVndInvNum);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, apVndInvSqNum);
            // START 2018/04/02 [QC#24277, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, apVndInvLineNum);
            // END   2018/04/02 [QC#24277, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, pmtHldCd);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, pmtHldRsnCd);
            cmInvPmtHldTMsg = (CM_INV_PMT_HLDTMsg) S21FastTBLAccessor.findByKey(cmInvPmtHldTMsg);
            if (cmInvPmtHldTMsg == null) {
                cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, apVndCd);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, apVndInvNum);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, apVndInvSqNum);
                // START 2018/04/02 [QC#24277, ADD]
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, apVndInvLineNum);
                // END   2018/04/02 [QC#24277, ADD]
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, pmtHldCd);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, pmtHldRsnCd);
                Map<String,Object> map = cmInvPmtHldMap.get(keyList);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, (String) map.get(PO_NUM));
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, (String) map.get(PMT_HLD_FLG));
                if (ZYPConstant.FLG_ON_Y.equals((String)map.get(PMT_HLD_FLG))) {
                    // START 2018/10/29 [QC#28988, MOD]
                    // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, cmProcDt);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, slsDt);
                    // END   2018/10/29 [QC#28988, MOD]
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldPsnCd, PSN_CD_NFBB1180);
                } else {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldPsnCd, EMPTY_STRING);
                }
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelPsnCd, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelDt, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelRsnCd, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelCmntTxt, EMPTY_STRING);
                insertCmInvPmtHldCommon(cmInvPmtHldTMsg);
            }
        }
    }

    /**
     * insertCmInvPmtHldCommon
     */
    private void insertCmInvPmtHldCommon(CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg) {
        cmInvPmtHldInsertTMsgs[iInsertCntCmInvPmtHld] = cmInvPmtHldTMsg;
        iInsertCntCmInvPmtHld++;
        if (iInsertCntCmInvPmtHld == INT_BULK_COM_LIM) {
            int iRet = S21FastTBLAccessor.insert(cmInvPmtHldInsertTMsgs);
            if (iRet > 0) {
                cmInvPmtHldInsertTMsgs = new CM_INV_PMT_HLDTMsg[INT_BULK_COM_LIM];
                cmInvPmtHldInsertCommitCount = cmInvPmtHldInsertCommitCount + iRet;
                iInsertCntCmInvPmtHld = 0;
            } else {
                cmInvPmtHldInsertCommitCount = 0;
                throw new S21AbendException(ZZBM0074E);
            }
        }
    }

    // START 2016/11/15 T.Tsuchida [QC#15982,DEL]
//    /**
//     * Get CM_AP_INV_DTL list by partial keys.
//     * @param apVndCd String 
//     * @param apVndInvNum String 
//     * @param apVndInvSqNum String 
//     */
//    @SuppressWarnings("unchecked")
//    private List getListCmApInvDtlByPartialKey(String apVndCd, String apVndInvNum, String apVndInvSqNum) {
//        Map<String, String> queryMap = new HashMap<String, String>();
//        queryMap.put("glblCmpyCd", glblCmpyCd);
//        queryMap.put("apVndCd", apVndCd);
//        queryMap.put("apVndInvNum", apVndInvNum);
//        queryMap.put("apVndInvSqNum", apVndInvSqNum);
//        List<Map> ssmResult = (List<Map>) ssmBatchClient.queryObjectList(SELECT_CM_AP_INV_DTL_BY_PARTIAL_KEY, queryMap);
//        if (ssmResult.size() > 0) {
//            return ssmResult;
//        }
//        return new ArrayList<Map>();
//    }
    // END 2016/11/15 T.Tsuchida [QC#15982,DEL]
    // START 2016/11/10 E.Kameishi [QC#14800, ADD]
    /**
     * getFreightCoaConst
     */
    @SuppressWarnings("unchecked")
    private void createInvAcctDistForFreight(ResultSet rs, int apVndInvAcctDistNum, int apVndInvLineNumOther, boolean hldRelProcFlg) throws SQLException {
        // Create CM_INV_ACCT_DIST(FRT) record
        CM_INV_ACCT_DISTTMsg cmInvAcctDist = new CM_INV_ACCT_DISTTMsg();
        NFBCommonBusiness.setZeroIntoCmInvAcctDist(cmInvAcctDist);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndCd, rs.getString(AP_VND_CD));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvNum, rs.getString(AP_VND_INV_NUM));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.mdseCd, FREIGHT);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvTpCd, AP_INV_TP_CD_00);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.poNum, rs.getString(CAID_PO_NUM));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.delyOrdNum, NONE);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.acInvJrnlCostAmt, rs.getBigDecimal(AC_SC_TOT_FRT_COST_AMT));
        Boolean errFlg = false;
        // START 2018/09/05 [QC#28040, ADD]
        String coaAcctCd = null;
        // END 2018/09/05 [QC#28040, ADD]

        // START 2019/10/25 [QC#54275, ADD]
        String lineBizTpCd = null;
        if (ZYPCommonFunc.hasValue(rs.getString(CAIH_PO_NUM))) {
            lineBizTpCd = getLOBByPO(rs.getString(CAIH_PO_NUM));
        }
        // END   2019/10/25 [QC#54275, ADD]
        // START 2020/09/29 R.Kurahashi [QC#57795,DEL]
        //if (Arrays.asList(varCharConstApVndCdList).contains(rs.getString(AP_VND_CD))) {
        //    // Azerty Invoice
        //    // QC#21875 Start
        //    if (!hldRelProcFlg) {
        //        String destRtlWhCd = rs.getString(DEST_RTL_WH_CD);
        //        // START 2019/10/25 [QC#54275, MOD]
        //        // if (!ZYPCommonFunc.hasValue(destRtlWhCd)) {
        //        if (!ZYPCommonFunc.hasValue(destRtlWhCd) || !ZYPCommonFunc.hasValue(lineBizTpCd)) {
        //        // END   2019/10/25 [QC#54275, MOD]
        //            errFlg = true;
        //        } else {
        //            COA_CONSTTMsg inCoaConstTMsg = new COA_CONSTTMsg();
        //            ZYPEZDItemValueSetter.setValue(inCoaConstTMsg.glblCmpyCd, glblCmpyCd);
        //            ZYPEZDItemValueSetter.setValue(inCoaConstTMsg.coaConstCd, destRtlWhCd);
        //            ZYPEZDItemValueSetter.setValue(inCoaConstTMsg.coaConstGrpId, I05_CHARGES_ACCT);
        //            // START 2019/10/25 [QC#54275, MOD]
        //            // ZYPEZDItemValueSetter.setValue(inCoaConstTMsg.coaConstResrcId, ALL);
        //            ZYPEZDItemValueSetter.setValue(inCoaConstTMsg.coaConstResrcId, lineBizTpCd);
        //            // END   2019/10/25 [QC#54275, MOD]
        //            COA_CONSTTMsg outCoaConstTMsg = (COA_CONSTTMsg) S21FastTBLAccessor.findByKey(inCoaConstTMsg);
        //            if (outCoaConstTMsg == null) {
        //                errFlg = true;
        //            } else {
        //                // START 2018/03/26 [QC#20316, MOD]
        //                // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaCmpyCd, outCoaConstTMsg.coaCmpyCd);
        //                // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaBrCd, outCoaConstTMsg.coaBrCd);
        //                // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaCcCd, outCoaConstTMsg.coaCcCd);
        //                // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaAcctCd, outCoaConstTMsg.coaAcctCd);
        //                // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaProdCd, outCoaConstTMsg.coaProdCd);
        //                // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaChCd, outCoaConstTMsg.coaChCd);
        //                // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaAfflCd, outCoaConstTMsg.coaAfflCd);
        //                // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaProjCd, outCoaConstTMsg.coaProjCd);
        //                // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaExtnCd, outCoaConstTMsg.coaExtnCd);
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCmpyCd, outCoaConstTMsg.coaCmpyCd);
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, outCoaConstTMsg.coaBrCd);
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, outCoaConstTMsg.coaCcCd);
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAcctCd, outCoaConstTMsg.coaAcctCd);
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, outCoaConstTMsg.coaProdCd);
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaChCd, outCoaConstTMsg.coaChCd);
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAfflCd, outCoaConstTMsg.coaAfflCd);
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProjCd, outCoaConstTMsg.coaProjCd);
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaExtnCd, outCoaConstTMsg.coaExtnCd);
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCmpyCd, rs.getString(SPLY_COA_CMPY_CD));
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaBrCd, rs.getString(SPLY_COA_BR_CD));
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCcCd, rs.getString(SPLY_COA_CC_CD));
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAcctCd, rs.getString(SPLY_COA_ACCT_CD));
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProdCd, rs.getString(SPLY_COA_PROD_CD));
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaChCd, rs.getString(SPLY_COA_CH_CD));
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAfflCd, rs.getString(SPLY_COA_AFFL_CD));
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProjCd, rs.getString(SPLY_COA_PROJ_CD));
        //                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaExtnCd, rs.getString(SPLY_COA_EXTN_CD));
        //                // END   2018/03/26 [QC#20316, MOD]
        //                // START 2018/09/05 [QC#28040, ADD]
        //                coaAcctCd = outCoaConstTMsg.coaAcctCd.getValue();
        //                // END 2018/09/05 [QC#28040, ADD]
        //            }
        //        }
        //    }
        //    // QC#21875 End
        //} else {
        //    // Not Azerty Invoice(CUSA Invoice)
        //    // START 2019/10/25 [QC#54275, MOD]
        //    List<Map> ssmResult = new ArrayList<Map>();
        //    if (ZYPCommonFunc.hasValue(lineBizTpCd)) {
        //        Map<String, Object> ssmParam = new HashMap<String, Object>();
        //        ssmParam.put("glblCmpyCd", glblCmpyCd);
        //        // ssmParam.put("apVndCd",  rs.getString(AP_VND_CD));
        //        ssmParam.put("apVndCd",  rs.getString(PO_VND_CD));
        //        ssmParam.put("apVndInvNum", rs.getString(AP_VND_INV_NUM));
        //        ssmParam.put("coaConstGrpId", I05_CHARGES_ACCT);
        //        // ssmParam.put("coaConstResrcId", ALL);
        //        ssmParam.put("coaConstResrcId", lineBizTpCd);
        //        ssmResult = (List<Map>) ssmBatchClient.queryObjectList(SELECT_COA_CONST_BY_SHIP_TO_CUST, ssmParam);
        //    }
        //    // END   2019/10/25 [QC#54275, MOD]
        //
        //    if (ssmResult.size() == 0) {
        //        errFlg = true;
        //    } else {
        //        Map map = (Map) ssmResult.get(0);
        //        // START 2018/03/26 [QC#20316, MOD]
        //        // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaCmpyCd, (String) map.get(COA_CMPY_CD));
        //        // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaBrCd, (String) map.get(COA_BR_CD));
        //        // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaCcCd, (String) map.get(COA_CC_CD));
        //        // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaAcctCd, (String) map.get(COA_ACCT_CD));
        //        // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaProdCd, (String) map.get(COA_PROD_CD));
        //        // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaChCd, (String) map.get(COA_CH_CD));
        //        // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaAfflCd, (String) map.get(COA_AFFL_CD));
        //        // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaProjCd, (String) map.get(COA_PROJ_CD));
        //        // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaExtnCd, (String) map.get(COA_EXTN_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCmpyCd, (String) map.get(COA_CMPY_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, (String) map.get(COA_BR_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, (String) map.get(COA_CC_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAcctCd, (String) map.get(COA_ACCT_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, (String) map.get(COA_PROD_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaChCd, (String) map.get(COA_CH_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAfflCd, (String) map.get(COA_AFFL_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProjCd, (String) map.get(COA_PROJ_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaExtnCd, (String) map.get(COA_EXTN_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCmpyCd, rs.getString(SPLY_COA_CMPY_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaBrCd, rs.getString(SPLY_COA_BR_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCcCd, rs.getString(SPLY_COA_CC_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAcctCd, rs.getString(SPLY_COA_ACCT_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProdCd, rs.getString(SPLY_COA_PROD_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaChCd, rs.getString(SPLY_COA_CH_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAfflCd, rs.getString(SPLY_COA_AFFL_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProjCd, rs.getString(SPLY_COA_PROJ_CD));
        //        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaExtnCd, rs.getString(SPLY_COA_EXTN_CD));
        //        // END   2018/03/26 [QC#20316, MOD]
        //        // START 2018/09/05 [QC#28040, ADD]
        //        coaAcctCd = (String) map.get(COA_ACCT_CD);
        //        // END 2018/09/05 [QC#28040, ADD]
        //    }
        //}
        // END 2020/09/29 R.Kurahashi [QC#57795,DEL]

        // START 2021/06/09 H.Dimay [QC#58883,ADD]
        Map<String, Object> ssmResult = null;
        String apVndInvNum = rs.getString(AP_VND_INV_NUM);
        String apVndCd = rs.getString(PO_VND_CD);

        // Check if invoice is from DIETZGEN
        if (SHIP_TO_CUST_CD_DIETZGEN.equals(getShipToCustCd(apVndInvNum, apVndCd))) {
        // END 2021/06/09 H.Dimay [QC#58883,ADD]
            // START 2021/06/09 H.Dimay [QC#58883,MOD]
            // START 2020/09/29 R.Kurahashi [QC#57795,ADD]
            //Map<String, Object> ssmResult = getCoaCombination(rs.getString(AP_VND_INV_NUM));
            ssmResult = getCoaCombination(rs.getString(AP_VND_INV_NUM));
            // END 2021/06/09 H.Dimay [QC#58883,MOD]
            // START 2021/06/09 H.Dimay [QC#58883,ADD]
        } else {
            // TST processing
            List<Map> ssmListResult = new ArrayList<Map>();
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            if (ZYPCommonFunc.hasValue(lineBizTpCd)) {
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("apVndCd",  apVndCd);
                ssmParam.put("apVndInvNum", apVndInvNum);
                ssmParam.put("coaConstGrpId", I05_CHARGES_ACCT);
                ssmParam.put("coaConstResrcId", lineBizTpCd);
                ssmListResult = (List<Map>) ssmBatchClient.queryObjectList(SELECT_COA_CONST_BY_SHIP_TO_CUST, ssmParam);
            }
            if (ssmListResult.size() > 0) {
                ssmResult = (Map) ssmListResult.get(0);
            }
        }
        // END 2021/06/09 H.Dimay [QC#58883,ADD]
        if (ssmResult != null) {
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCmpyCd, (String) ssmResult.get(COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, (String) ssmResult.get(COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, (String) ssmResult.get(COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAcctCd, (String) ssmResult.get(COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, (String) ssmResult.get(COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaChCd, (String) ssmResult.get(COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAfflCd, (String) ssmResult.get(COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProjCd, (String) ssmResult.get(COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaExtnCd, (String) ssmResult.get(COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCmpyCd, rs.getString(SPLY_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaBrCd, rs.getString(SPLY_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCcCd, rs.getString(SPLY_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAcctCd, rs.getString(SPLY_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProdCd, rs.getString(SPLY_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaChCd, rs.getString(SPLY_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAfflCd, rs.getString(SPLY_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProjCd, rs.getString(SPLY_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaExtnCd, rs.getString(SPLY_COA_EXTN_CD));

            coaAcctCd = (String) ssmResult.get(COA_ACCT_CD);
        }
        else {
            errFlg = true;
        }
        // END 2020/09/29 R.Kurahashi [QC#57795,ADD]
        if (errFlg) {
            // Not exist COA_CONST records
            // START 2018/03/26 [QC#20316, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaCmpyCd, rs.getString(FRT_COA_CMPY_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaBrCd, rs.getString(FRT_COA_BR_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaCcCd, rs.getString(FRT_COA_CC_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaAcctCd, rs.getString(FRT_COA_ACCT_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaProdCd, rs.getString(FRT_COA_PROD_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaChCd, rs.getString(FRT_COA_CH_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaAfflCd, rs.getString(FRT_COA_AFFL_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaProjCd, rs.getString(FRT_COA_PROJ_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaExtnCd, rs.getString(FRT_COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCmpyCd, rs.getString(FRT_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, rs.getString(FRT_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, rs.getString(FRT_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAcctCd, rs.getString(FRT_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, rs.getString(FRT_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaChCd, rs.getString(FRT_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAfflCd, rs.getString(FRT_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProjCd, rs.getString(FRT_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaExtnCd, rs.getString(FRT_COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCmpyCd, rs.getString(SPLY_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaBrCd, rs.getString(SPLY_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCcCd, rs.getString(SPLY_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAcctCd, rs.getString(SPLY_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProdCd, rs.getString(SPLY_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaChCd, rs.getString(SPLY_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAfflCd, rs.getString(SPLY_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProjCd, rs.getString(SPLY_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaExtnCd, rs.getString(SPLY_COA_EXTN_CD));
            // END   2018/03/26 [QC#20316, MOD]

            // START 2018/12/20 [QC#29679, ADD]
            if (ZYPCommonFunc.hasValue(rs.getString(CAIH_PO_NUM))) {
                Map<String, Object> chrgAcct = getChargeAccountByLOB(rs.getString(CAIH_PO_NUM));
                if (chrgAcct != null) {
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, (String) chrgAcct.get(COA_BR_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, (String) chrgAcct.get(COA_CC_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, (String) chrgAcct.get(COA_PROD_CD));
                }

                // START 2019/10/24 [QC#54275, ADD]
                chrgAcct = getChargeAccountByPO(rs.getString(AP_VND_CD), rs.getString(AP_VND_INV_NUM), rs.getString(AP_VND_INV_SQ_NUM), rs.getString(CAIH_PO_NUM));
                if (chrgAcct != null) {
                    // START 2019/11/06 [QC#54275-1, MOD]
                    // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, (String) chrgAcct.get(COA_PROD_CD));
                    // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProjCd, (String) chrgAcct.get(COA_PROJ_CD));
                    // START 2019/11/11 [QC#54275-1, ADD]
                    if (ZYPCommonFunc.hasValue((String) chrgAcct.get(COA_CC_CD))) {
                        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, (String) chrgAcct.get(COA_CC_CD));
                    }
                    // END   2019/11/11 [QC#54275-1, ADD]
                    if (ZYPCommonFunc.hasValue((String) chrgAcct.get(COA_PROD_CD))) {
                        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, (String) chrgAcct.get(COA_PROD_CD));
                    }
                    if (ZYPCommonFunc.hasValue((String) chrgAcct.get(COA_PROJ_CD))) {
                        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProjCd, (String) chrgAcct.get(COA_PROJ_CD));
                    }
                    // END   2019/11/06 [QC#54275-1, MOD]
                }
                // END   2019/10/24 [QC#54275, ADD]
            }
            // END   2018/12/20 [QC#29679, ADD]

            // START 2018/09/05 [QC#28040, ADD]
            coaAcctCd = rs.getString(FRT_COA_ACCT_CD);
            // END 2018/09/05 [QC#28040, ADD]
        }
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.ifProcStsCd, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apLineTpCd, varCharConstApLineTpFreight);
        //START 2016/09/30 [QC#14797, ADD]
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.cmInvAcctDistLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvAcctDistNum), 3, "0"));
        //END 2016/09/30 [QC#14797, ADD]
        // START 2018/03/26 [QC#20316, MOD]
        // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvLineNumOther), 3, "0"));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvLineNumOther), 4, "0"));
        // END   2018/03/26 [QC#20316, MOD]
        // START 2018/09/05 [QC#28040, ADD]
        String coaAcctDescTxt = getCoaAcctDescTxt(glblCmpyCd, coaAcctCd);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apAcctDescTxt, coaAcctDescTxt);

        EZDItemAttribute getDigitApInvDescTxt = new CM_INV_ACCT_DISTTMsg().getAttr("apInvDescTxt");
        int txtDigit = getDigitApInvDescTxt.getDigit();

        if (ZYPCommonFunc.hasValue(coaAcctDescTxt) && coaAcctDescTxt.length() > txtDigit) {
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, S21StringUtil.subStringByLength(coaAcctDescTxt, 0, txtDigit));
        } else {
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, coaAcctDescTxt);
        }
        // END 2018/09/05 [QC#28040, ADD]

        cmInvAcctDistInsertTMsgs[iInsertCntCmInvAcctDist] = cmInvAcctDist;
        iInsertCntCmInvAcctDist++;
        if (iInsertCntCmInvAcctDist == INT_BULK_COM_LIM) {
            int iRet = S21FastTBLAccessor.insert(cmInvAcctDistInsertTMsgs);
            if (iRet > 0) {
                cmInvAcctDistInsertTMsgs = new CM_INV_ACCT_DISTTMsg[INT_BULK_COM_LIM];
                cmInvAcctDistInsertCommitCount = cmInvAcctDistInsertCommitCount + iRet;
                iInsertCntCmInvAcctDist = 0;
            } else {
                cmInvAcctDistInsertCommitCount = 0;
                throw new S21AbendException(ZZBM0074E);
            }
        }
    }
    // END 2016/11/10 E.Kameishi [QC#14800, ADD]

    // START 2023/01/05 S.Nakatani [QC#60248,ADD]
    /**
     * getHandlingFeeCoaConst
     */
    private void createInvAcctDistForHandlingFee(ResultSet rs, int apVndInvAcctDistNum, int apVndInvLineNumOther, boolean hldRelProcFlg) throws SQLException {
        // Create CM_INV_ACCT_DIST(HDLG) record
        CM_INV_ACCT_DISTTMsg cmInvAcctDist = new CM_INV_ACCT_DISTTMsg();
        NFBCommonBusiness.setZeroIntoCmInvAcctDist(cmInvAcctDist);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndCd, rs.getString(AP_VND_CD));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvNum, rs.getString(AP_VND_INV_NUM));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.mdseCd, NONE0001);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvTpCd, AP_INV_TP_CD_00);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.poNum, rs.getString(CAID_PO_NUM));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.delyOrdNum, NONE);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.acInvJrnlCostAmt, rs.getBigDecimal(AC_SC_TOT_HDLG_COST_AMT));
        Boolean errFlg = false;
        String coaAcctCd = null;

        String lineBizTpCd = null;
        if (ZYPCommonFunc.hasValue(rs.getString(CAIH_PO_NUM))) {
            lineBizTpCd = getLOBByPO(rs.getString(CAIH_PO_NUM));
        }

        Map<String, Object> ssmResult = null;
        String apVndInvNum = rs.getString(AP_VND_INV_NUM);
        String apVndCd = rs.getString(PO_VND_CD);

        // Check if invoice is from DIETZGEN
        if (SHIP_TO_CUST_CD_DIETZGEN.equals(getShipToCustCd(apVndInvNum, apVndCd))) {
            ssmResult = getCoaCombinationHandlingFee(rs.getString(AP_VND_INV_NUM));
        } else {
            List<Map> ssmListResult = new ArrayList<Map>();
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            if (ZYPCommonFunc.hasValue(lineBizTpCd)) {
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("apVndCd",  apVndCd);
                ssmParam.put("apVndInvNum", apVndInvNum);
                ssmParam.put("coaConstGrpId", I05_CHARGES_ACCT);
                ssmParam.put("coaConstResrcId", lineBizTpCd);
                ssmListResult = (List<Map>) ssmBatchClient.queryObjectList(SELECT_COA_CONST_BY_SHIP_TO_CUST, ssmParam);
            }
            if (ssmListResult.size() > 0) {
                ssmResult = (Map) ssmListResult.get(0);
            }
        }
        if (ssmResult != null) {
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCmpyCd, (String) ssmResult.get(COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, (String) ssmResult.get(COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, (String) ssmResult.get(COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAcctCd, (String) ssmResult.get(COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, (String) ssmResult.get(COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaChCd, (String) ssmResult.get(COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAfflCd, (String) ssmResult.get(COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProjCd, (String) ssmResult.get(COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaExtnCd, (String) ssmResult.get(COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCmpyCd, rs.getString(SPLY_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaBrCd, rs.getString(SPLY_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCcCd, rs.getString(SPLY_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAcctCd, rs.getString(SPLY_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProdCd, rs.getString(SPLY_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaChCd, rs.getString(SPLY_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAfflCd, rs.getString(SPLY_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProjCd, rs.getString(SPLY_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaExtnCd, rs.getString(SPLY_COA_EXTN_CD));

            coaAcctCd = (String) ssmResult.get(COA_ACCT_CD);
        }
        else {
            errFlg = true;
        }
        if (errFlg) {
            // Not exist COA_CONST records
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCmpyCd, rs.getString(HDLG_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, rs.getString(HDLG_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, rs.getString(HDLG_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAcctCd, rs.getString(HDLG_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, rs.getString(HDLG_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaChCd, rs.getString(HDLG_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAfflCd, rs.getString(HDLG_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProjCd, rs.getString(HDLG_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaExtnCd, rs.getString(HDLG_COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCmpyCd, rs.getString(SPLY_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaBrCd, rs.getString(SPLY_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCcCd, rs.getString(SPLY_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAcctCd, rs.getString(SPLY_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProdCd, rs.getString(SPLY_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaChCd, rs.getString(SPLY_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAfflCd, rs.getString(SPLY_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProjCd, rs.getString(SPLY_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaExtnCd, rs.getString(SPLY_COA_EXTN_CD));

            if (ZYPCommonFunc.hasValue(rs.getString(CAIH_PO_NUM))) {
                Map<String, Object> chrgAcct = getChargeAccountByLOB(rs.getString(CAIH_PO_NUM));
                if (chrgAcct != null) {
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, (String) chrgAcct.get(COA_BR_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, (String) chrgAcct.get(COA_CC_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, (String) chrgAcct.get(COA_PROD_CD));
                }

                chrgAcct = getChargeAccountByPO(rs.getString(AP_VND_CD), rs.getString(AP_VND_INV_NUM), rs.getString(AP_VND_INV_SQ_NUM), rs.getString(CAIH_PO_NUM));
                if (chrgAcct != null) {
                    if (ZYPCommonFunc.hasValue((String) chrgAcct.get(COA_CC_CD))) {
                        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, (String) chrgAcct.get(COA_CC_CD));
                    }
                    if (ZYPCommonFunc.hasValue((String) chrgAcct.get(COA_PROD_CD))) {
                        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, (String) chrgAcct.get(COA_PROD_CD));
                    }
                    if (ZYPCommonFunc.hasValue((String) chrgAcct.get(COA_PROJ_CD))) {
                        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProjCd, (String) chrgAcct.get(COA_PROJ_CD));
                    }
                }
            }

            coaAcctCd = rs.getString(HDLG_COA_ACCT_CD);
        }
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.ifProcStsCd, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apLineTpCd, AP_LINE_TP.MISC);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.cmInvAcctDistLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvAcctDistNum), 3, "0"));
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(apVndInvLineNumOther), 4, "0"));
        String coaAcctDescTxt = getCoaAcctDescTxt(glblCmpyCd, coaAcctCd);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apAcctDescTxt, coaAcctDescTxt);

        EZDItemAttribute getDigitApInvDescTxt = new CM_INV_ACCT_DISTTMsg().getAttr("apInvDescTxt");
        int txtDigit = getDigitApInvDescTxt.getDigit();

        if (ZYPCommonFunc.hasValue(coaAcctDescTxt) && coaAcctDescTxt.length() > txtDigit) {
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, S21StringUtil.subStringByLength(coaAcctDescTxt, 0, txtDigit));
        } else {
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, coaAcctDescTxt);
        }

        cmInvAcctDistInsertTMsgs[iInsertCntCmInvAcctDist] = cmInvAcctDist;
        iInsertCntCmInvAcctDist++;
        if (iInsertCntCmInvAcctDist == INT_BULK_COM_LIM) {
            int iRet = S21FastTBLAccessor.insert(cmInvAcctDistInsertTMsgs);
            if (iRet > 0) {
                cmInvAcctDistInsertTMsgs = new CM_INV_ACCT_DISTTMsg[INT_BULK_COM_LIM];
                cmInvAcctDistInsertCommitCount = cmInvAcctDistInsertCommitCount + iRet;
                iInsertCntCmInvAcctDist = 0;
            } else {
                cmInvAcctDistInsertCommitCount = 0;
                throw new S21AbendException(ZZBM0074E);
            }
        }
    }
    // END 2023/01/05 S.Nakatani [QC#60248,ADD]
    
    // START 2016/11/15 T.Tsuchida [QC#15982,ADD]
    /**
     * callPoStsUpdateAPI
     * @param cmApInvDtlTMsg CM_AP_INV_DTLTMsg
     * @return msgId
     */
    // START 2019/08/29 [QC#52280, MOD]
    // private String callPoStsUpdateAPI(CM_AP_INV_DTLTMsg cmApInvDtlTMsg) {
    private String callPoStsUpdateAPI(CM_AP_INV_HDRTMsg cmApInvHdrTMsg, CM_AP_INV_DTLTMsg cmApInvDtlTMsg) {
    // END   2019/08/29 [QC#52280, MOD]
        String poNum = cmApInvDtlTMsg.poNum.getValue();
        String mdseCd = cmApInvDtlTMsg.mdseCd.getValue();
        // START 2018/08/26 [QC#27886, MOD]
        // String poOrdDtlLineNum = "";
        String poOrdDtlLineNum = cmApInvDtlTMsg.poOrdDtlLineNum.getValue();
        // END   2018/08/26 [QC#27886, MOD]
        BigDecimal invQty = cmApInvDtlTMsg.invQty.getValue();
        // START 2018/08/26 [QC#27886, DEL]
        // BigDecimal invBalQty = BigDecimal.ZERO;
        // boolean callAPIFlg = false;
        // boolean overQtyFlg = false;
        // END   2018/08/26 [QC#27886, DEL]

        // START 2019/08/29 [QC#52280, MOD]
        // List<Map<String, Object>> listCmApInvDtl = (List<Map<String, Object>>) getListCmApInvDtlByPartialKey(poNum);
        List<Map<String, Object>> listCmApInvDtl = new ArrayList<Map<String, Object>>();
        if (INV_TP.CREDIT_MEMO.equals(cmApInvHdrTMsg.invTpCd.getValue()) 
                || CM_AP_INV_TP.CREDIT_MEMO.equals(cmApInvHdrTMsg.cmApInvTpCd.getValue())) {
            listCmApInvDtl = (List<Map<String, Object>>) getListCmApInvDtlByPartialKeyForCreditMemo(poNum);
        } else {
            listCmApInvDtl = (List<Map<String, Object>>) getListCmApInvDtlByPartialKey(poNum);
        }
        // END   2019/08/29 [QC#52280, MOD]
        // START 2018/08/23 [QC#27886, MOD]
        for (int j = 0; j < listCmApInvDtl.size(); j++) {
            Map<String, Object> map = (Map<String, Object>) listCmApInvDtl.get(j);
            String poOrdDtlLineNumOfPO = (String) map.get(PO_ORD_DTL_LINE_NUM);

            if (!poOrdDtlLineNumOfPO.equals(poOrdDtlLineNum)) {
                continue;
            }

            // START 2019/08/28 [QC#52280, MOD]
            // if (BigDecimal.ZERO.compareTo(invQty) < 0) {
            //     String msgId = updatePoInformation(poNum, poOrdDtlLineNum, invQty, mdseCd);
            //     if (msgId != null) {
            //         return msgId;
            //     }
            // }
            if (BigDecimal.ZERO.compareTo(invQty) != 0) {
                String msgId = updatePoInformation(poNum, poOrdDtlLineNum, invQty, mdseCd);
                if (msgId != null) {
                    return msgId;
                }
            }
            // END   2019/08/28 [QC#52280, MOD]
        }
        // for (int j = 0; j < listCmApInvDtl.size(); j++) {
        //     Map<String, Object> map = (Map<String, Object>) listCmApInvDtl.get(j);
        //     String mdseCdOfPO = (String) map.get(MDSE_CD);
        //     String poOrdDtlLineNumOfPO = (String) map.get(PO_ORD_DTL_LINE_NUM);
        //     BigDecimal invBalQtyOfPO = (BigDecimal) map.get(PO_INV_BAL_QTY);
        // 
        //     if (callAPIFlg) {
        //         if (overQtyFlg && !mdseCd.equals(mdseCdOfPO)) {
        //             invQty = invQty.add(invBalQty);
        //         }
        //         if (BigDecimal.ZERO.compareTo(invQty) < 0) {
        //             String msgId = updatePoInformation(poNum, poOrdDtlLineNum, invQty, mdseCd);
        //             if (msgId != null) {
        //                 return msgId;
        //             }
        //         }
        //         invQty = invBalQty;
        //         callAPIFlg = false;
        //     }
        // 
        //     if (mdseCd.equals(mdseCdOfPO)) {
        //         callAPIFlg = true;
        //         poOrdDtlLineNum = poOrdDtlLineNumOfPO;
        //         if (invBalQtyOfPO.compareTo(invQty) >= 0) {
        //             invBalQty = BigDecimal.ZERO;
        //             overQtyFlg = false;
        //         } else {
        //             invBalQty = invQty.subtract(invBalQtyOfPO);
        //             invQty = invBalQtyOfPO;
        //             overQtyFlg = true;
        //         }
        //     }
        // }
        // 
        // if (callAPIFlg) {
        //     if (overQtyFlg) {
        //         invQty = invQty.add(invBalQty);
        //     }
        //     if (BigDecimal.ZERO.compareTo(invQty) < 0) {
        //         String msgId = updatePoInformation(poNum, poOrdDtlLineNum, invQty, mdseCd);
        //         if (msgId != null) {
        //             return msgId;
        //         }
        //     }
        // }
        // END   2018/08/23 [QC#27886, MOD]

        return null;
    }

    /**
     * Get CM_AP_INV_DTL list by partial keys.
     * @param poOrdNum String 
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getListCmApInvDtlByPartialKey(String poOrdNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("poOrdNum", poOrdNum);
        queryParam.put("poLineStsCdIsOpen", PO_LINE_STS.OPEN);
        queryParam.put("poStsCdIsOpenList", new String[] {PO_STS.VALIDATED, PO_STS.PO_CONFIRMED});
        queryParam.put("poLinsStsCdIsOpenForXXList", new String[] {PO_LINE_STS.OPEN_FOR_RECEIPT, PO_LINE_STS.OPEN_FOR_INVOICE });

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SELECT_POUPDATE_RECORD, queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return new ArrayList<Map<String, Object>>();
    }

    /**
     * updatePoInformation
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @param poInvQty BigDecimal
     * @param mdseCd String
     * @return xxMsgId String
     */
    private String updatePoInformation(String poOrdNum, String poOrdDtlLineNum, BigDecimal poInvQty, String mdseCd) {
        NPZC004001PMsg pMsg = new NPZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poInvQty, poInvQty);
        NPZC004001 apiPOUpdate = new NPZC004001();
        apiPOUpdate.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            // Error
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)
                && !pMsg.xxMsgIdList.no(i).xxMsgId.getValue().equals(NPZM0021E)
                ) {
                    return pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Find Cost Management Accounts Payable Invoice Detail
     * @param glblCmpyCd String
     * @param apVndCd String
     * @param apVndInvNum String
     * @param apVndInvSqNum String
     * @return CM_AP_INV_DTLTMsgArray
     */
    private CM_AP_INV_DTLTMsgArray findCmApInvDtlList(String glblCmpyCd, String apVndCd, String apVndInvNum, String apVndInvSqNum) {
        CM_AP_INV_DTLTMsg inMsg = new CM_AP_INV_DTLTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("apVndCd01", apVndCd);
        inMsg.setConditionValue("apVndInvNum01", apVndInvNum);
        inMsg.setConditionValue("apVndInvSqNum01", apVndInvSqNum);
        return (CM_AP_INV_DTLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    // END 2016/11/15 T.Tsuchida [QC#15982,ADD]
//    NPZM0021E=0,Specified PO Information is invalid or does not exist.

    // START 2016/12/09 [QC#16132, MOD]
    private String setCoaVal(String mdseCoaCd, String defCoaCd) {

        if (ZYPCommonFunc.hasValue(mdseCoaCd)) {
            return mdseCoaCd;
        }
        return defCoaCd;
    }
    // END 2016/12/09 [QC#16132, ADD]

    // START 2017/01/23 [QC#17090, ADD]
    /**
     * get COA_CC_CD,COA_ACCT_CD,COA_PROD_CD,MDSE.COA_MDSE_TP_CD
     * @param mdseCd String 
     * @return List<Map<String, Object>>
     */
    private List<Map> getCoaCdForVar(String mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("coaProjAcctTpCd", COA_PROJ_ACCT_TP.IPV);
        // START 2018/10/24 [QC#28857, ADD]
        queryParam.put("cmDefAcctCdVar", CM_DEF_ACCT.INV_RATE_VARIANCE_COA);
        // END   2018/10/24 [QC#28857, ADD]
        List<Map> list = (List<Map>) ssmBatchClient.queryObjectList(SELECT_COA_CD_FOR_VAR, queryParam);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }
    // END 2017/01/23 [QC#17090, ADD]

    // START 2017/12/18 T.Hakodate [QC#18442, ADD]
    /**
     * getCmStockIn
     */
    private Map<String, Object> getCmStockIn(ResultSet rs) throws SQLException {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("apVndCd", rs.getString(AP_VND_CD));
        queryParam.put("apVndInvNum", rs.getString(AP_VND_INV_NUM));
        queryParam.put("apVndInvSqNum", rs.getString(AP_VND_INV_SQ_NUM));
        queryParam.put("vndInvNum", rs.getString(VND_INV_NUM));
        // START 2018/04/02 [QC#20316, MOD]
        // queryParam.put("mdseCd", rs.getString(MDSE_CD));
        // queryParam.put("delyOrdNum", rs.getString(DELY_ORD_NUM));
        // queryParam.put("apInvTpCd", rs.getString(AP_INV_TP_CD));
        // queryParam.put("poNum", rs.getString(CAID_PO_NUM));
        queryParam.put("apVndInvLineNum", rs.getString(AP_VND_INV_LINE_NUM));
        // END  START 2018/04/02 [QC#20316, MOD]

        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getCmStockIn", queryParam);

        return result;

    }
    // END 2017/12/18 T.Hakodate [QC#18442, ADD]

    // START 2018/09/05 [QC#28040, ADD]
    /**
     * getCoaAcctDescTxt
     * @param glblCmpyCd String
     * @param coaAcctCd String
     * @retrun coaAcctDescTxt String
     */
    public static String getCoaAcctDescTxt(String glblCmpyCd, String coaAcctCd) {

        String rtnCoaAcctDescTxt = null;
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(coaAcctCd)) {
            return null;
        }
        COA_ACCTTMsg coaAcct = new COA_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(coaAcct.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(coaAcct.coaAcctCd, coaAcctCd);

        coaAcct = (COA_ACCTTMsg) EZDTBLAccessor.findByKey(coaAcct);
        if (coaAcct != null) {
            rtnCoaAcctDescTxt = coaAcct.coaAcctDescTxt.getValue();
        }
        return rtnCoaAcctDescTxt;
    }
    // END 2018/09/05 [QC#28040, ADD]


    // START 2018/11/08 [QC#28982, ADD]
    /**
     * Private class: SelectRecordPOVarianceUpdateHandler
     */
    @SuppressWarnings("unchecked")
    private class SelectRecordPOVarianceUpdateHandler extends SelectRecordHandler {

        public SelectRecordPOVarianceUpdateHandler() {
            super(false);
        }

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            List<List> hdrPkCombinationList = new ArrayList<List>();
            Map<String, Integer> apVndInvLineNumOtherMap = new HashMap();
            while (rs.next()) {
                String apVndInvNum = rs.getString(AP_VND_INV_NUM);
                if (apVndInvLineNumOtherMap.containsKey(apVndInvNum)) {
                    int count = apVndInvLineNumOtherMap.get(apVndInvNum) + 1;
                    apVndInvLineNumOtherMap.put(apVndInvNum, count++);
                } else {
                    apVndInvLineNumOtherMap.put(apVndInvNum, 1);
                }
            }
            rs.beforeFirst();
            while (rs.next()) {
                BigDecimal entDealNetPrcAmt = null;

                Map<String, Object> cmStockInMap = new HashMap<String, Object>();
                cmStockInMap = getCmStockIn(rs);

                if (cmStockInMap != null) {
                    entDealNetPrcAmt = ((BigDecimal) cmStockInMap.get(ENT_DEAL_NET_UNIT_PRC_AMT)).multiply(rs.getBigDecimal(INV_QTY));
                } else if (ZYPCommonFunc.hasValue(rs.getBigDecimal(PD_ENT_DEAL_NET_UNIT_PRC_AMT))) {
                    entDealNetPrcAmt = rs.getBigDecimal(PD_ENT_DEAL_NET_UNIT_PRC_AMT).multiply(rs.getBigDecimal(INV_QTY));
                } else {
                    entDealNetPrcAmt = rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT).multiply(rs.getBigDecimal(INV_QTY));
                }

                BigDecimal varAmt = rs.getBigDecimal(AC_OC_FOB_COST_AMT).subtract(entDealNetPrcAmt);

                List<String> invLineKeyList = new ArrayList<String>();
                invLineKeyList.add(rs.getString(AP_VND_CD));
                invLineKeyList.add(rs.getString(AP_VND_INV_NUM));
                invLineKeyList.add(rs.getString(AP_VND_INV_SQ_NUM));
                invLineKeyList.add(rs.getString(AP_VND_INV_LINE_NUM));
                varAmtMap.put(invLineKeyList, varAmt);

                if (varAmt.compareTo(BigDecimal.ZERO) != 0) {
                    CM_INV_ACCT_DISTTMsg distTMsg = getInvAcctDistVarRecord(invLineKeyList);
                    if (distTMsg == null) {
                        // create CM_INV_ACCT_DIST record.
                        String apVndInvLineNum = rs.getString(AP_VND_INV_LINE_NUM);
                        int varCnt = Integer.valueOf(apVndInvLineNum, 10);
                        int apVndInvAcctDistNum = Integer.valueOf(getMaxAcctDistLineNumber(invLineKeyList), 10) + 1;
                        createInvAcctDistForVar(rs, apVndInvAcctDistNum, varAmt, varCnt, apVndInvLineNum);
                    }
                }
            }
            return Boolean.TRUE;
        }
    }

    private void createInvAcctDistForVar(List<String> invLineKeyList) {

        for(int idx = 0 ; idx < cmInvAcctDistInsertTMsgs.length ; idx++) {
            String apVndCd = invLineKeyList.get(0);
            String apVndInvNum = invLineKeyList.get(1);
            String apVndInvSqNum = invLineKeyList.get(2);
            String apVndInvLineNum = invLineKeyList.get(3);
            CM_INV_ACCT_DISTTMsg distTMsg = cmInvAcctDistInsertTMsgs[idx];

            if (!apVndCd.equals(distTMsg.apVndCd.getValue())
                || !apVndInvNum.equals(distTMsg.apVndInvNum.getValue())
                || !apVndInvSqNum.equals(distTMsg.apVndInvSqNum.getValue())
                || !apVndInvLineNum.equals(distTMsg.apVndInvLineNum.getValue())
                || !varCharConstApLineTpVariance.equals(distTMsg.apLineTpCd.getValue())  ) {
                continue;
            }

            BigDecimal varAmt = distTMsg.acInvJrnlCostAmt.getValue().negate();
            updateInvAcctDistForMdse(invLineKeyList, varAmt);

            EZDTBLAccessor.insert(distTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(distTMsg.getReturnCode())) {
                throw new S21AbendException(NFBM0028E);
            }
            cmInvAcctDistUpdateCommitCount++;
            break;
        }
    }

    private void updateInvAcctDistForVar(List<String> invLineKeyList, BigDecimal varAmt, CM_INV_ACCT_DISTTMsg distTMsg) {
        BigDecimal diffAmt = distTMsg.acInvJrnlCostAmt.getValue().subtract(varAmt);
        updateInvAcctDistForMdse(invLineKeyList, diffAmt);

        ZYPEZDItemValueSetter.setValue(distTMsg.acInvJrnlCostAmt, varAmt);
        EZDTBLAccessor.update(distTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(distTMsg.getReturnCode())) {
            throw new S21AbendException(NFBM0028E);
        }
        cmInvAcctDistUpdateCommitCount++;
    }

    private void removeInvAcctDistForVar(List<String> invLineKeyList, CM_INV_ACCT_DISTTMsg distTMsg) {
        BigDecimal varAmt = distTMsg.acInvJrnlCostAmt.getValue();
        updateInvAcctDistForMdse(invLineKeyList, varAmt);

        EZDTBLAccessor.remove(distTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(distTMsg.getReturnCode())) {
            throw new S21AbendException(NFBM0028E);
        }
        cmInvAcctDistDeleteCommitCount++;
    }

    private void updateInvAcctDistForMdse(List<String> invLineKeyList, BigDecimal varAmt) {
        CM_INV_ACCT_DISTTMsg distTMsg = getInvAcctDistMdseRecord(invLineKeyList);
        if (distTMsg == null) {
            return;
        }

        BigDecimal acInvJrnlCostAmt = distTMsg.acInvJrnlCostAmt.getValue().add(varAmt);
        ZYPEZDItemValueSetter.setValue(distTMsg.acInvJrnlCostAmt, acInvJrnlCostAmt);
        EZDTBLAccessor.update(distTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(distTMsg.getReturnCode())) {
            throw new S21AbendException(NFBM0028E);
        }
        cmApInvDtlUpdateCommitCount++;
    }

    private String getMaxAcctDistLineNumber(List<String> invLineKeyList) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("apVndCd", invLineKeyList.get(0));
        queryParam.put("apVndInvNum", invLineKeyList.get(1));
        queryParam.put("apVndInvSqNum", invLineKeyList.get(2));
        queryParam.put("apVndInvLineNum", invLineKeyList.get(3));

        return (String) this.ssmBatchClient.queryObject("getMaxAcctDistLineNumber", queryParam);
    }

    private CM_INV_ACCT_DISTTMsg getInvAcctDistMdseRecord(List<String> invLineKeyList) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("apVndCd", invLineKeyList.get(0));
        queryParam.put("apVndInvNum", invLineKeyList.get(1));
        queryParam.put("apVndInvSqNum", invLineKeyList.get(2));
        queryParam.put("apVndInvLineNum", invLineKeyList.get(3));
        queryParam.put("apLineTpCd", varCharConstApLineTpItem);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getInvAcctDistRecord", queryParam);
        if (resultList != null && resultList.size() > 0) {
            Map<String, Object> result = resultList.get(0);

            CM_INV_ACCT_DISTTMsg distMsg = new CM_INV_ACCT_DISTTMsg();
            ZYPEZDItemValueSetter.setValue(distMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(distMsg.apVndCd, (String)result.get("AP_VND_CD"));
            ZYPEZDItemValueSetter.setValue(distMsg.apVndInvNum, (String)result.get("AP_VND_INV_NUM"));
            ZYPEZDItemValueSetter.setValue(distMsg.apVndInvSqNum, (String)result.get("AP_VND_INV_SQ_NUM"));
            ZYPEZDItemValueSetter.setValue(distMsg.apVndInvLineNum, (String)result.get("AP_VND_INV_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(distMsg.cmInvAcctDistLineNum, (String)result.get("CM_INV_ACCT_DIST_LINE_NUM"));

            return (CM_INV_ACCT_DISTTMsg) S21FastTBLAccessor.findByKeyForUpdate(distMsg);
        }

        return null;
    }

    private CM_INV_ACCT_DISTTMsg getInvAcctDistVarRecord(List<String> invLineKeyList) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("apVndCd", invLineKeyList.get(0));
        queryParam.put("apVndInvNum", invLineKeyList.get(1));
        queryParam.put("apVndInvSqNum", invLineKeyList.get(2));
        queryParam.put("apVndInvLineNum", invLineKeyList.get(3));
        queryParam.put("apLineTpCd", varCharConstApLineTpVariance);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getInvAcctDistRecord", queryParam);
        if (resultList != null && resultList.size() > 0) {
            Map<String, Object> result = resultList.get(0);

            CM_INV_ACCT_DISTTMsg distMsg = new CM_INV_ACCT_DISTTMsg();
            ZYPEZDItemValueSetter.setValue(distMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(distMsg.apVndCd, (String)result.get("AP_VND_CD"));
            ZYPEZDItemValueSetter.setValue(distMsg.apVndInvNum, (String)result.get("AP_VND_INV_NUM"));
            ZYPEZDItemValueSetter.setValue(distMsg.apVndInvSqNum, (String)result.get("AP_VND_INV_SQ_NUM"));
            ZYPEZDItemValueSetter.setValue(distMsg.apVndInvLineNum, (String)result.get("AP_VND_INV_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(distMsg.cmInvAcctDistLineNum, (String)result.get("CM_INV_ACCT_DIST_LINE_NUM"));

            return (CM_INV_ACCT_DISTTMsg) S21FastTBLAccessor.findByKeyForUpdate(distMsg);
        }

        return null;
    }
    // END   2018/11/08 [QC#28982, ADD]

    // START 2018/12/08 [QC#29679, ADD]
    private Map<String, Object> getChargeAccountByLOB(String poNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("poNum", poNum);
        queryParam.put("dsCondConstGrpId", NFBB1180_PRCH_GRP);

        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getChargeAccountByLOB", queryParam);
        if (result != null 
                && ZYPCommonFunc.hasValue((String)result.get(COA_BR_CD))
                && ZYPCommonFunc.hasValue((String)result.get(COA_CC_CD))
                && ZYPCommonFunc.hasValue((String)result.get(COA_PROD_CD))) {
            return result;
        }

        return null;
    }
    // END   2018/12/08 [QC#29679, ADD]

    // START 2019/08/29 [QC#52280, ADD]
    /**
     * Get CM_AP_INV_DTL list by partial keys. (For Credit Memo)
     * @param poOrdNum String 
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getListCmApInvDtlByPartialKeyForCreditMemo(String poOrdNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("poOrdNum", poOrdNum);
        queryParam.put("poLineStsCdIsOpen", PO_LINE_STS.OPEN);
        queryParam.put("poStsCdIsOpenList", new String[] {PO_STS.VALIDATED, PO_STS.PO_CONFIRMED});
        queryParam.put("poLinsStsCdIsOpenForXXList", new String[] {PO_LINE_STS.OPEN_FOR_RECEIPT, PO_LINE_STS.OPEN_FOR_INVOICE });
        queryParam.put("poStsCdIsClosed", PO_STS.CLOSED);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SELECT_POUPDATE_RECORD_FOR_CREDIT_MEMO, queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return new ArrayList<Map<String, Object>>();
    }
    // END   2019/08/29 [QC#52280, ADD]

    // START 2019/10/24 [QC#54275, ADD]
    private String getLOBByPO(String poNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("poNum", poNum);
        queryParam.put("dsCondConstGrpId", NFBB1180_PRCH_GRP);

        return (String)this.ssmBatchClient.queryObject("getLOBByPO", queryParam);
    }

    private Map<String, Object> getChargeAccountByPO(String apVndCd, String apVndInvNum, String apVndInvSeqNum, String poNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        // START 2019/11/06 [QC#54275-1, MOD]
        // START 2019/11/11 [QC#54275-1, MOD]
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("apVndCd", apVndCd);
        queryParam.put("apVndInvNum", apVndInvNum);
        queryParam.put("apVndInvSqNum", apVndInvSeqNum);
        queryParam.put("poNum", poNum);
        queryParam.put("poAcctTpCd", PO_ACCT_TP.CHARGE);
        queryParam.put("poLineTpExp", PO_LINE_TP.EXPENSE);
        queryParam.put("poLineTpExpWR", PO_LINE_TP.EXPENSE_WITH_RECEIPT);
        queryParam.put("prodCd99", "99");
        queryParam.put("prodCdZZ", "ZZ");
        queryParam.put("rptCondConstGrpId", PRT_ORD_LOB);
        // END   2019/11/11 [QC#54275-1, MOD]
        // END   2019/11/06 [QC#54275-1, MOD]

        // START 2019/11/06 [QC#54275-1, DEL]
        // DefCoaValues defCoa = NFBCommonBusiness.getDefCoaValues(glblCmpyCd);
        // END   2019/11/06 [QC#54275-1, DEL]

        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getChargeAccountByPO", queryParam);
        if (result != null) {
            // START 2019/11/06 [QC#54275-1, DEL]
            // result.put(COA_PROD_CD, setCoaVal((String)result.get(COA_PROD_CD), defCoa.getDefCoaProd()));
            // result.put(COA_PROJ_CD, setCoaVal((String)result.get(COA_PROJ_CD), defCoa.getDefCoaProj()));
            // END   2019/11/06 [QC#54275-1, DEL]
            return result;
        }

        return null;
    }
    // END   2019/10/24 [QC#54275, ADD]
    // START 2020/09/29 R.Kurahashi [QC#57795,ADD]
    private Map<String, Object> getCoaCombination(String apVndInvNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("apVndInvNum", apVndInvNum);

        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getCoaCombination", queryParam);
        if (result != null && result.get("COA_CMPY_CD") != null) {
            return result;
        }
        return null;
    }
    // END 2020/09/29 R.Kurahashi [QC#57795,ADD]
    // START 2023/01/05 S.Nakatani [QC#60248,ADD]
    private Map<String, Object> getCoaCombinationHandlingFee(String apVndInvNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("apVndInvNum", apVndInvNum);

        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getCoaCombinationHandlingFee", queryParam);
        if (result != null && result.get("COA_CMPY_CD") != null) {
            return result;
        }
        return null;
    }
    // END 2023/01/05 S.Nakatani [QC#60248,ADD]
    // START 2021/06/09 H.Dimay [QC#58883, ADD]
    /**
     * Get SHIP_TO_CUST_CD value
     * @param apVndInvNum String VND_INV_NUM
     * @param apVndCd String VND_CD
     * @return String
     **/
    private String getShipToCustCd(String apVndInvNum, String apVndCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("apVndInvNum", apVndInvNum);
        queryParam.put("apVndCd", apVndCd);

        return (String) this.ssmBatchClient.queryObject("getShipToCustCd", queryParam);
    }
    // END 2021/06/09 H.Dimay [QC#58883, ADD]
}
