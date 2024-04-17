package com.canon.cusa.s21.batch.NFA.NFAB120001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.db.AJE_INV_ACCT_DISTTMsg;
import business.db.AJE_INV_ACCT_DIST_ERRTMsg;
import business.db.DS_INV_SLS_CRTMsg;
import business.db.INVTMsg;
import business.db.JRNL_ENTRYTMsg;
import business.parts.MdseTpAcct;
import business.parts.NFAC000101PMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFZC102001PMsg;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;
import business.parts.NFACommonJrnlEntryConstant.CoaSegment;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INV_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Invoice Distribution Create Batch
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 03/12/2018   Hitachi         E.Kameishi      Create          N/A
 * 03/30/2018   Hitachi         E.Kameishi      Update          QC#25130
 * 04/04/2018   Hitachi         E.Kameishi      Update          QC#25237
 * 05/21/2018   Hitachi         E.Kameishi      Update          QC#23166
 * 05/21/2018   Hitachi         E.Kameishi      Update          QC#21841
 * 06/12/2018   Hitachi         E.Kameishi      Update          QC#26627
 * 10/02/2018   Hitachi         E.Kameishi      Update          QC#28364
 * 10/29/2018   Hitachi         E.Kameishi      Update          QC#28976
 * 11/20/2010   Fujitsu         Y.Matsui        Update          QC#54357   add parameter mtMap to commonJrnlEntry.setValueToJrnlEntryByAjePtrn
 *</pre>
 */
public class NFAB120001 extends S21BatchMain implements ZYPConstant, NFAB120001Constant{

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Count */
    private int commitCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Termination Code */
    private TERM_CD termCd;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** Batch Process Date */
    private String batProcDt = null;

    /**  Journal Entry Common Module */
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

    /** Array of TMsg */
    private List<AJE_INV_ACCT_DISTTMsg> listAcctDist = new ArrayList<AJE_INV_ACCT_DISTTMsg>();

    /** Array of TMsg */
    private List<AJE_INV_ACCT_DIST_ERRTMsg> listAcctDistErr = new ArrayList<AJE_INV_ACCT_DIST_ERRTMsg>();

    private static final int INIT_AJE_LINE_IDX_NUM = 1;
    
    /** List of Tmsg*/
    List<AJE_INV_ACCT_DISTTMsg> acctDistList = new ArrayList<AJE_INV_ACCT_DISTTMsg> ();

    /** List of Invoice Number*/
    List<String> invNumList = null;

    /** List of Invoice Number Error List*/
    List<String> invNumErrorList = null;

    // START 2018/06/12 E.Kameishi [QC#26627,MOD]
    // START 2018/05/21 E.Kameishi [QC#21841,DEL]
    /** */
    private String prevBolNum_Tax = "";
    // END 2018/05/21 E.Kameishi [QC#21841,DEL]
    // END 2018/06/12 E.Kameishi [QC#26627,MOD]

    /** */
    private String prevLineNum_Tax = "";

    // START 2018/05/21 E.Kameishi [QC#21841,DEL]
    /** */
    //private String prevBolNum_Frt_Tax = "";

    /** */
    //private String prevBolNum_Frt = "";
    // END 2018/05/21 E.Kameishi [QC#21841,DEL]

    /** */
    private String defCoaBrCd;

    /** */
    private String defCoaCcCd;

    /** */
    private String defCoaProdCd;

    /** */
    private String defCoaChCd;

    /** */
    private String defCoaAfflCd;

    /** */
    private String defCoaProjCd;

    /** */
    private String defCoaExtnCd;

    /** */
    boolean taxCrBreakFlg = false;

    /** */
    boolean taxDrBreakFlg = false;

    // START 2018/05/21 E.Kameishi [QC#21841,DEL]
    /** */
    //boolean frtTaxCrBreakFlg = false;

    /** */
    //boolean frtTaxDrBreakFlg = false;

    /** */
    //boolean frtCrBreakFlg = false;

    /** */
    //boolean frtDrBreakFlg = false;
    // END 2018/05/21 E.Kameishi [QC#21841,DEL]

    /** */
    private List<String> ajeLineIndTpLink = new ArrayList<String>();

    /** */
    int ajeInvAcctDistSqNum = 0;

    /** Divide Number */
    private String divideNum = null;

    /** */
    String glDt = null;

    /** */
    List<String> glCmbnList = new ArrayList<String>();
    // START 2018/05/21 E.Kameishi [QC#21841,ADD]
    private String coaMdseTPCdFrt;
    // END 2018/05/21 E.Kameishi [QC#21841,ADD]

    // START 2019/11/20 [QC#54357,ADD]
    /** Merchandise Type Map */
    private Map<String, List<MdseTpAcct>> mtMap;
    // END 2019/11/20 [QC#54357,ADD]

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        new NFAB120001().executeBatch(NFAB120001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.profile = S21UserProfileServiceFactory.getInstance().getService();

        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }
        this.batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd, BUSINESS_ID);

        String lineIndTps = ZYPCodeDataUtil.getVarCharConstValue("AJE_LINE_IND_TP_LINK", this.glblCmpyCd);
        divLineIndTp(lineIndTps);

        String ajeCoaDefValuesCsv = ZYPCodeDataUtil.getVarCharConstValue("AJE_COA_DEF_VALUES", this.glblCmpyCd);
        String[] ajeCoaDefValues = ajeCoaDefValuesCsv.split(",");
        this.defCoaBrCd   = ajeCoaDefValues[1];
        this.defCoaCcCd   = ajeCoaDefValues[2];
        this.defCoaProdCd = ajeCoaDefValues[4];
        this.defCoaChCd   = ajeCoaDefValues[5];
        this.defCoaAfflCd = ajeCoaDefValues[6];
        this.defCoaProjCd = ajeCoaDefValues[7];
        this.defCoaExtnCd = ajeCoaDefValues[8];
        
        this.divideNum = getUserVariable1();

        // START 2018/05/21 E.Kameishi [QC#21841,ADD]
        this.coaMdseTPCdFrt = ZYPCodeDataUtil.getVarCharConstValue("AJE_COA_MDSE_TP_FRT", this.glblCmpyCd);
        // END 2018/05/21 E.Kameishi [QC#21841,ADD]

        // START 2019/11/20 [QC#54357,ADD]
        // get COA Project Account Master
        mtMap = commonJrnlEntry.getMdseTpAcct(glblCmpyCd);
        // END 2019/11/20 [QC#54357,ADD]

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        PreparedStatement ps = null;
        ResultSet rs = null;

        // START 2018/05/21 E.Kameishi [QC#23166,ADD]
        setSvcInvChrgTp();
        // END 2018/05/21 E.Kameishi [QC#23166,ADD]
        
        try {
            ps = (PreparedStatement) commonJrnlEntry.getInvoice(this.glblCmpyCd, null, batProcDt, ps, this.divideNum);
            rs = ps.executeQuery();

            mainProcess(rs);
        } catch (SQLException ex) {
            throw new S21AbendException(NFAM0032E, new String[] {BUSINESS_ID, ex.getMessage()});

        } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
            throw new S21AbendException(ZZBM0074E, new String[] {BUSINESS_ID, ex.getMessage()});
            
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        
        commit();
        S21InfoLogOutput.println("mainRoutine Method End");
    }

    private boolean mainProcess(ResultSet rsNotJrnlized) {
        
        invNumList = new ArrayList<String>();
        invNumErrorList = new ArrayList<String>();
        String invNum = null;
        try {
            while (rsNotJrnlized.next()) {
                if (invNum == null || !invNum.equals(rsNotJrnlized.getString(INV_NUM))) {
                    this.glDt = getGlDt(this.glblCmpyCd, this.batProcDt, rsNotJrnlized.getString(ACCT_DT));
                    this.ajeInvAcctDistSqNum = 0;
                    // START 2018/03/30 E.Kameishi [QC#25130,ADD]
                    taxCrBreakFlg = false;
                    taxDrBreakFlg = false;
                    // START 2018/05/21 E.Kameishi [QC#21841,DEL]
                    //frtTaxCrBreakFlg = false;
                    //frtTaxDrBreakFlg = false;
                    //frtCrBreakFlg = false;
                    //frtDrBreakFlg = false;
                    // END 2018/05/21 E.Kameishi [QC#21841,DEL]
                    // END 2018/03/30 E.Kameishi [QC#25130,ADD]
                    // START 2018/04/04 E.Kameishi [QC#25237,MOD]
                    invNum = rsNotJrnlized.getString(INV_NUM);
                    // before processing, delete existing data
                    deleteExistingData(this.glblCmpyCd, rsNotJrnlized.getString(INV_NUM));
                    deleteExistingErrData(this.glblCmpyCd, rsNotJrnlized.getString(INV_NUM));
                    // END 2018/04/04 E.Kameishi [QC#25237,MOD]
                }
                // AJE Pattern to be determined by pattern
                // indicator
                String ajeId = commonJrnlEntry.generateAjeId(rsNotJrnlized.getString(SYS_SRC_CD), rsNotJrnlized.getString(TRX_CD), rsNotJrnlized.getString(TRX_RSN_CD));

                // Get AJE Pattern List for Debit
                List<Map> resAjePtrnList = (List<Map>) commonJrnlEntry.getAjePtrn2(this.glblCmpyCd, rsNotJrnlized.getString(SYS_SRC_CD), rsNotJrnlized.getString(TRX_CD), rsNotJrnlized.getString(TRX_RSN_CD), DR_CD);

                // refine AJE Pattern by pattern indicator codes
                List<NFAC000101PMsg> ajePtrnListByAjeIdAndIndTp = commonJrnlEntry.getAJEPtrnByAjeIdAndIndTp(ajeId, rsNotJrnlized, resAjePtrnList);

                // if pattern could not be obtained go to next
                // record. (No need to create one more error
                // record.)
                if (!doProcessPerPattern(ajePtrnListByAjeIdAndIndTp, rsNotJrnlized, DR_CD)) {
                    continue;
                }

                // Get AJE Pattern List for Credit
                resAjePtrnList = (List<Map>) commonJrnlEntry.getAjePtrn2(this.glblCmpyCd, rsNotJrnlized.getString(SYS_SRC_CD), rsNotJrnlized.getString(TRX_CD), rsNotJrnlized.getString(TRX_RSN_CD), CR_CD);

                // refine AJE Pattern by pattern indicator codes
                ajePtrnListByAjeIdAndIndTp = commonJrnlEntry.getAJEPtrnByAjeIdAndIndTp(ajeId, rsNotJrnlized, resAjePtrnList);

                doProcessPerPattern(ajePtrnListByAjeIdAndIndTp, rsNotJrnlized, CR_CD);
            }


            submitInsertAcctDist(listAcctDist, true);

            submitInsertAcctDistErr(listAcctDistErr, true);

            // Update AJE_LINE_IDX_NUM in AJE_INV_ACCT_DIST
            if(!updateInvoice_ACCT_DISTWithIdxNum()) {
                return false;
            }

            // Update INV.ITRL_INV_ERR_FLG
            updateInvoice();

        } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
            return Boolean.FALSE;
        } catch (SQLException ex) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private void deleteExistingData(String glblCmpyCd, String invNum) {

        AJE_INV_ACCT_DISTTMsg tmsg = new AJE_INV_ACCT_DISTTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.invNum, invNum);

        S21FastTBLAccessor.removeByPartialValue(tmsg, new String[] {"glblCmpyCd", "invNum" });

    }

    private void deleteExistingErrData(String glblCmpyCd, String invNum) {

        AJE_INV_ACCT_DIST_ERRTMsg tmsg = new AJE_INV_ACCT_DIST_ERRTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.invNum, invNum);

        S21FastTBLAccessor.removeByPartialValue(tmsg, new String[] {"glblCmpyCd", "invNum" });

    }

    private void divLineIndTp(String str) {
        if (str == null) {
            str = "";
        }
        StringTokenizer st = new StringTokenizer(str, ",");

        while (st.hasMoreTokens()) {
            ajeLineIndTpLink.add(st.nextToken());
        }

    }
    

    private boolean doProcessPerPattern(List<NFAC000101PMsg> ajePtrnListByAjeIdAndIndTp, ResultSet rsNotJrnlized, String drCrTpCd) throws SQLException, NFACommonJrnlEntry.JrnlCommonException {

        // If AJE pattern could not be obtained, create error data
        // only.
        if (ajePtrnListByAjeIdAndIndTp.size() == 0) {

            AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr = new AJE_INV_ACCT_DIST_ERRTMsg();

            BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_ERR_SQ);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.glblCmpyCd, rsNotJrnlized.getString(GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistErrPk, pk);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invNum, rsNotJrnlized.getString(INV_NUM));
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invBolLineNum, rsNotJrnlized.getString(INV_BOL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineNum, rsNotJrnlized.getString(INV_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineSubNum, rsNotJrnlized.getString(INV_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invTrxLineSubNum, rsNotJrnlized.getString(INV_TRX_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, S21MessageFunc.clspGetMessage(NO_AJE_PTRN_ERR));

            createAjeInvAcctDistErr(ajeInvAcctDistErr);

            if (!invNumErrorList.contains(ajeInvAcctDistErr.invNum.getValue())) {
                invNumErrorList.add(ajeInvAcctDistErr.invNum.getValue());
            }
            return false;
        }

        for (NFAC000101PMsg resAjePtrn : ajePtrnListByAjeIdAndIndTp) {

            AJE_INV_ACCT_DISTTMsg ajeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
            AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr = new AJE_INV_ACCT_DIST_ERRTMsg();

            // Set Common Data
            setAjeInvAcctDistCommonValues(ajeInvAcctDist, rsNotJrnlized, resAjePtrn, drCrTpCd);

            // Set Amount Data
            setAjeInvAcctDistAmountValues(ajeInvAcctDist, rsNotJrnlized, resAjePtrn, drCrTpCd);

            // Set 9 Segments Data
            if (!setAjeInvAcctDist9SegmentsValues(ajeInvAcctDist, ajeInvAcctDistErr, rsNotJrnlized, resAjePtrn, drCrTpCd)) {
                // error
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procStsCd, PROC_STS.ERROR);
            } else {
                // Check 9 Segments
                glCodeCombinationCheck(ajeInvAcctDist, ajeInvAcctDistErr, rsNotJrnlized);
            }

            // START 2018/05/21 E.Kameishi [QC#21841,MOD]
            createAjeInvAcctDist(ajeInvAcctDist, rsNotJrnlized);
            // END 2018/05/21 E.Kameishi [QC#21841,MOD]

            // If any error, create error record
            if (PROC_STS.ERROR.equals(ajeInvAcctDist.procStsCd.getValue())) {

                // set value other than error message
                BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_ERR_SQ);
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.glblCmpyCd, ajeInvAcctDist.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistErrPk, pk);
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistPk, ajeInvAcctDist.ajeInvAcctDistPk.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invNum, ajeInvAcctDist.invNum.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invBolLineNum, ajeInvAcctDist.invBolLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineNum, ajeInvAcctDist.invLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineSubNum, ajeInvAcctDist.invLineSubNum.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invTrxLineSubNum, ajeInvAcctDist.invLineSubTrxNum.getValue());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistSqNum, ajeInvAcctDist.ajeInvAcctDistSqNum.getValue());

                String coaConcat = nvl(ajeInvAcctDist.ajeCoaCmpyCd.getValue()).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaBrCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaCcCd.getValue())).concat(",").concat(
                        nvl(ajeInvAcctDist.ajeCoaAcctCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaProdCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaChCd.getValue())).concat(",").concat(
                        nvl(ajeInvAcctDist.ajeCoaAfflCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaProjCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaExtnCd.getValue()));

                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt, coaConcat);

                if (!invNumErrorList.contains(ajeInvAcctDistErr.invNum.getValue())) {
                    invNumErrorList.add(ajeInvAcctDistErr.invNum.getValue());
                }

                createAjeInvAcctDistErr(ajeInvAcctDistErr);
            }
            
            if (!invNumList.contains(ajeInvAcctDist.invNum.getValue())) {
                invNumList.add(ajeInvAcctDist.invNum.getValue());
            }
        }

        return true;
    }

    private String nvl(String val) {
        if (hasValue(val)) {
            return val;
        } else {
            return "";
        }
    }

    /**
     * Set Common Values
     * @param jrnlEntry JRNL_ENTRYTMsg
     * @param invSlsCr ResultSet
     * @param resAjePtrn Map
     * @param drCrIndTpCd
     * @param glDt
     * @throws SQLException
     */
    private void setAjeInvAcctDistCommonValues(AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, ResultSet invSlsCr, NFAC000101PMsg resAjePtrn, String drCrIndTpCd) throws SQLException {

        BigDecimal ajeInvAcctDistSeqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_SQ);

        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistPk, ajeInvAcctDistSeqNum);
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invNum, invSlsCr.getString(INV_NUM));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invBolLineNum, invSlsCr.getString(INV_BOL_LINE_NUM).toString());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invLineNum, invSlsCr.getString(INV_LINE_NUM).toString());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invLineSubNum, invSlsCr.getString(INV_LINE_SUB_NUM).toString());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.invLineSubTrxNum, invSlsCr.getString(INV_TRX_LINE_SUB_NUM).toString());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistSqNum, String.format("%06d", this.ajeInvAcctDistSqNum));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procDt, this.batProcDt);
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctClsCd, resAjePtrn.ajeLineIndTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.drCrTpCd, drCrIndTpCd);
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dealCcyCd, invSlsCr.getString(DEAL_CCY_CD));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.funcCcyCd, invSlsCr.getString(DEAL_CCY_CD));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeInvAcctDistPct, calcAjeInvAcctDistPct(invSlsCr, resAjePtrn.ajeLineIndTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.glDt, glDt);
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dsInvSlsCrPk, invSlsCr.getBigDecimal(DS_INV_SLS_CR_PK));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeOmIntfcPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procStsCd, PROC_STS.COMPLEATED);

        // newly added fields
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.sysSrcCd, invSlsCr.getString(SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.sysSrcNm, invSlsCr.getString(SYS_SRC_NM));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxCd, resAjePtrn.trxCd.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxNm, resAjePtrn.trxNm.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxRsnCd, resAjePtrn.trxRsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.trxRsnNm, resAjePtrn.trxRsnNm.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpCd_01, resAjePtrn.ajePtrnIndTpCd_01.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpNm_01, resAjePtrn.ajePtrnIndTpNm_01.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlCd_01, resAjePtrn.ajePtrnActlCd_01.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlNm_01, resAjePtrn.ajePtrnActlNm_01.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpCd_02, resAjePtrn.ajePtrnIndTpCd_02.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpNm_02, resAjePtrn.ajePtrnIndTpNm_02.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlCd_02, resAjePtrn.ajePtrnActlCd_02.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlNm_02, resAjePtrn.ajePtrnActlNm_02.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpCd_03, resAjePtrn.ajePtrnIndTpCd_03.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnIndTpNm_03, resAjePtrn.ajePtrnIndTpNm_03.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlCd_03, resAjePtrn.ajePtrnActlCd_03.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajePtrnActlNm_03, resAjePtrn.ajePtrnActlNm_03.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlSrcCd, resAjePtrn.jrnlSrcCd.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlSrcNm, resAjePtrn.jrnlSrcNm.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlCatgCd, resAjePtrn.jrnlCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlCatgNm, resAjePtrn.jrnlCatgNm.getValue());
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeLineIdxNum, resAjePtrn.ajeLineIdxNum.getValue());
        if (DR_CD.equals(drCrIndTpCd)) {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeLineIdxDescTxt, resAjePtrn.drAjeLineIdxDescTxt.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeLineIdxDescTxt, resAjePtrn.crAjeLineIdxDescTxt.getValue());
        }

        // If line indicator type of AJE pattern is "99", set the
        // flag on.
        if (AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(resAjePtrn.ajeLineIndTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dfrdRevGlStrgFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.dfrdRevGlStrgFlg, ZYPConstant.FLG_OFF_N);
        }

        this.ajeInvAcctDistSqNum++;
    }

    /**
     * @param invSlsCr
     * @param ajeLineIndTpCd
     * @return
     * @throws SQLException
     */
    private BigDecimal calcAjeInvAcctDistPct(ResultSet invSlsCr, String ajeLineIndTpCd) throws SQLException {

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
        // START 2018/05/21 E.Kameishi [QC#21841,MOD]
        if(AJE_INV_ACCT_CLS.REVENUE.equals(ajeLineIndTpCd) || AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(ajeLineIndTpCd) || AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(ajeLineIndTpCd) || AJE_INV_ACCT_CLS.FREIGHT.equals(ajeLineIndTpCd)){
            ajeInvAcctDistPct = ajeInvAcctDistPct.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        } else {
            ajeInvAcctDistPct = new BigDecimal(100);
        }
        // END 2018/05/21 E.Kameishi [QC#21841,MOD]

        return ajeInvAcctDistPct;
    }

    /**
     * Set Amount Values
     * @param param
     * @param ajeInvAcctDist
     * @param invSlsCr
     * @param resAjePtrn
     * @param drCrTpCd
     * @return booolean
     * @throws SQLException
     * @throws JrnlCommonException 
     */
    private boolean setAjeInvAcctDistAmountValues(AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, ResultSet invSlsCr, NFAC000101PMsg resAjePtrn, String drCrTpCd) throws SQLException, JrnlCommonException {

        String invTpCd = invSlsCr.getString(INV_TP_CD);

        boolean chkTaxFlg;
        // START 2018/05/21 E.Kameishi [QC#21841,DEL]
        //boolean chkFrtTaxFlg;
        //boolean chkFrtFlg;
        // END 2018/05/21 E.Kameishi [QC#21841,DEL]

        if (DR_CD.equals(drCrTpCd)) {
            chkTaxFlg = taxDrBreakFlg;
            // START 2018/05/21 E.Kameishi [QC#21841,DEL]
            //chkFrtTaxFlg = frtTaxDrBreakFlg;
            //chkFrtFlg = frtDrBreakFlg;
            // END 2018/05/21 E.Kameishi [QC#21841,DEL]
        } else {
            chkTaxFlg = taxCrBreakFlg;
            // START 2018/05/21 E.Kameishi [QC#21841,DEL]
            //chkFrtTaxFlg = frtTaxCrBreakFlg;
            //chkFrtFlg = frtCrBreakFlg;
            // END 2018/05/21 E.Kameishi [QC#21841,DEL]
        }

        // START 2018/06/12 E.Kameishi [QC#26627,MOD]
        // START 2018/05/21 E.Kameishi [QC#21841,MOD]
        // TAX by INV_LINE
        if (resAjePtrn.ajeLineIndTpCd.getValue().equals(AJE_INV_ACCT_CLS.TAX)) {
            if (!prevBolNum_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM)) 
                    || !prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM)) || !chkTaxFlg) {
            //if (!prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM)) || !chkTaxFlg) {

                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(INV_LINE_DEAL_TAX_AMT)));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(INV_LINE_FUNC_TAX_AMT)));

                // Freight TAX by INV_BOL
//                    if (!prevBolNum_Frt_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM)) || !chkFrtTaxFlg) {
//                        if (!"0".equals(invSlsCr.getString(FRT_DEAL_TAX_AMT))) {
//                            setAjeInvAcctDistAmountValuesForFrtTax(resAjePtrn, invSlsCr, drCrTpCd);
//
//                            if (CR_CD.equals(drCrTpCd)) {
//                                this.frtTaxCrBreakFlg = true;
//
//                                // turn off the other side of flag
//                                // when BOL breaks so that the value will be set to the other side too
//                                if (!prevBolNum_Frt_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM))) {
//                                    prevBolNum_Frt_Tax = invSlsCr.getString(INV_BOL_LINE_NUM);
//                                    this.frtTaxDrBreakFlg = false;
//                                }
//
//                            } else {
//                                this.frtTaxDrBreakFlg = true;
//
//                                // turn off the other side of flag
//                                // when BOL breaks so that the value will be set to the other side too
//                                if (!prevBolNum_Frt_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM))) {
//                                    prevBolNum_Frt_Tax = invSlsCr.getString(INV_BOL_LINE_NUM);
//                                    this.frtTaxCrBreakFlg = false;
//                                }
//                            }
//                        }
//                    }

                if (CR_CD.equals(drCrTpCd)) {
                    this.taxCrBreakFlg = true;

                    // turn off the other side of flag
                    // when Line breaks so that the value will be set to the other side too
                    if (!prevBolNum_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM))
                            || !prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM))) {
                    //if (!prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM))) {
                        prevBolNum_Tax = invSlsCr.getString(INV_BOL_LINE_NUM);
                        prevLineNum_Tax = invSlsCr.getString(INV_LINE_NUM);
                        this.taxDrBreakFlg = false;
                    }

                } else {
                    this.taxDrBreakFlg = true;

                    // turn off the other side of flag
                    // when BOL breaks so that the value will be set to the other side too
                    if (!prevBolNum_Tax.equals(invSlsCr.getString(INV_BOL_LINE_NUM))
                            || !prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM))) {
                    //if (!prevLineNum_Tax.equals(invSlsCr.getString(INV_LINE_NUM))) {
                        prevBolNum_Tax = invSlsCr.getString(INV_BOL_LINE_NUM);
                        prevLineNum_Tax = invSlsCr.getString(INV_LINE_NUM);
                        this.taxCrBreakFlg = false;
                    }
                }
                // END 2018/06/12 E.Kameishi [QC#26627,MOD]
            } else {
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, BigDecimal.ZERO);
            }
            return true;
        // Freight by BOL
        } else if (resAjePtrn.ajeLineIndTpCd.getValue().equals(AJE_INV_ACCT_CLS.FREIGHT)) {

            //if (!prevBolNum_Frt.equals(invSlsCr.getString(INV_BOL_LINE_NUM)) || !chkFrtFlg) {
            if (this.coaMdseTPCdFrt.equals(invSlsCr.getString(COA_MDSE_TP_CD))) {

                //ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(SHIP_DEAL_FRT_AMT)));
                //ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(SHIP_FUNC_FRT_AMT)));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(DEAL_SLS_CR_AMT)));
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(FUNC_SLS_CR_AMT)));
//                if (CR_CD.equals(drCrTpCd)) {
//
//                    frtCrBreakFlg = true;
//
//                    // turn off the other side of flag when BOL
//                    // breaks so that the value will be set to the
//                    // other side too
//                    if (!prevBolNum_Frt.equals(invSlsCr.getString(INV_BOL_LINE_NUM))) {
//                        prevBolNum_Frt = invSlsCr.getString(INV_BOL_LINE_NUM);
//                        frtDrBreakFlg = false;
//                    }
//
//                } else {
//                    frtDrBreakFlg = true;
//
//                    // turn off the other side of flag when BOL
//                    // breaks so that the value will be set to the
//                    // other side too
//                    if (!prevBolNum_Frt.equals(invSlsCr.getString(INV_BOL_LINE_NUM))) {
//                        prevBolNum_Frt = invSlsCr.getString(INV_BOL_LINE_NUM);
//                        frtCrBreakFlg = false;
//                    }
//                }
            } else {
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, BigDecimal.ZERO);
            }

            return true;
        } else {
            if (!this.coaMdseTPCdFrt.equals(invSlsCr.getString(COA_MDSE_TP_CD))) {
                // Sales
                // If line indicator type of AJE pattern is "99", jrnlDeal/FuncAmt set ZERO.
                if (AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(resAjePtrn.ajeLineIndTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(DEAL_SLS_CR_AMT)));
                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(FUNC_SLS_CR_AMT)));
                }
            } else {
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, BigDecimal.ZERO);
            }
        }
        // END 2018/05/21 E.Kameishi [QC#21841,MOD]
        return true;
    }

    // Otherwise, tax / freight/ unearned revenue lines are also
    // overwritten.
     
    private boolean setValuesAtLink(String crDrTpCd, String valInTrx, String ajeLineIndTp) {
        
        return false;
    }

    /**
     * Set 9 Segments Values
     * @param param
     * @param jrnlEntry
     * @param invSlsCr
     * @param resAjePtrn
     * @param drCrTpCd
     * @return boolean
     * @throws SQLException
     */
    private boolean setAjeInvAcctDist9SegmentsValues(AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr, ResultSet invSlsCr, NFAC000101PMsg resAjePtrn, String drCrTpCd)
            throws SQLException {

        String resultCd;
        String glblCmpyCd = this.glblCmpyCd;

        String coaCmpyCd;
        String coaBrCd;
        String coaCcCd;
        String coaAcctCd;
        String coaProdCd;
        String coaChCd;
        String coaAfflCd;
        String coaProjCd;
        String coaExtnCd;

        if (DR_CD.equals(drCrTpCd)) {
            coaCmpyCd = resAjePtrn.drAjeCoaCmpyCd.getValue();
            coaBrCd = resAjePtrn.drAjeCoaBrCd.getValue();
            coaCcCd = resAjePtrn.drAjeCoaCcCd.getValue();
            coaAcctCd = resAjePtrn.drAjeCoaAcctCd.getValue();
            coaProdCd = resAjePtrn.drAjeCoaProdCd.getValue();
            coaChCd = resAjePtrn.drAjeCoaChCd.getValue();
            coaAfflCd = resAjePtrn.drAjeCoaAfflCd.getValue();
            coaProjCd = resAjePtrn.drAjeCoaProjCd.getValue();
            coaExtnCd = resAjePtrn.drAjeCoaExtnCd.getValue();
        } else {
            coaCmpyCd = resAjePtrn.crAjeCoaCmpyCd.getValue();
            coaBrCd = resAjePtrn.crAjeCoaBrCd.getValue();
            coaCcCd = resAjePtrn.crAjeCoaCcCd.getValue();
            coaAcctCd = resAjePtrn.crAjeCoaAcctCd.getValue();
            coaProdCd = resAjePtrn.crAjeCoaProdCd.getValue();
            coaChCd = resAjePtrn.crAjeCoaChCd.getValue();
            coaAfflCd = resAjePtrn.crAjeCoaAfflCd.getValue();
            coaProjCd = resAjePtrn.crAjeCoaProjCd.getValue();
            coaExtnCd = resAjePtrn.crAjeCoaExtnCd.getValue();
        }

        boolean defaultSetFlg = false;
        JRNL_ENTRYTMsg tMsg = new JRNL_ENTRYTMsg();
        String crOrDr = "cr";
        if (DR_CD.equals(drCrTpCd)) {
            crOrDr = "dr";
        }
        for (int i = 0; i < 9; i++) {
            switch (i) {
                case 0:// Company COA_CMPY
                    // {
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_CMPY_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_CMPY_CD);
                    } else if (coaCmpyCd.substring(0, 1).equals("@") || coaCmpyCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CMPY, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaCmpyCd");
                            
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                            
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaCmpyCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaCmpyCd, resultCd);
                    
                    break;

                case 1:// Branch COA_BR
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_BR_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_BR_CD);
                    } else if (coaBrCd.substring(0, 1).equals("@") || coaBrCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.BR, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaBrCd");
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                            
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaBrCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaBrCd, resultCd);
                    break;

                case 2:// Cost Center COA_CC
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_CC_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_CC_CD);
                    } else if (coaCcCd.substring(0, 1).equals("@") || coaCcCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CC, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaCcCd");
                            
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaCcCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaCcCd, resultCd);
                    break;

                case 3:// Account COA_ACCT
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_ACCT_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_ACCT_CD);
                    } else if (coaAcctCd.substring(0, 1).equals("@") || coaAcctCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.ACCT, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaAcctCd");
                            
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                            
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaAcctCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaAcctCd, resultCd);
                    if (COA_ACCT_CD_ITEM_REV.equals(coaAcctCd)) {
                        defaultSetFlg = checkBsPlTp(glblCmpyCd, resultCd);
                    }
                    break;

                case 4:// Product COA_PROD
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_PROD_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_PROD_CD);
                    } else if (coaProdCd.substring(0, 1).equals("@") || coaProdCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.PROD, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaProdCd");
                            
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaProdCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProdCd, resultCd);
                    break;

                case 5:// Channel COA_CH
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_CH_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_CH_CD);
                    } else if (coaChCd.substring(0, 1).equals("@") || coaChCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CH, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaChCd");
                            
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaChCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaChCd, resultCd);
                    break;

                case 6:// Affiliate COA_AFFL
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_AFFL_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_AFFL_CD);
                    } else if (coaAfflCd.substring(0, 1).equals("@") || coaAfflCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.AFFL, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaAfflCd");
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaAfflCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaAfflCd, resultCd);
                    break;

                case 7:// Merchandise Type COA_PROJ
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_PROJ_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_PROJ_CD);
                    } else if (coaProjCd.substring(0, 1).equals("@") || coaProjCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.PROJ, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaProjCd");
                            
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaProjCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProjCd, resultCd);
                    break;

                case 8:// Business Unit COA_EXTN
                    if (setValuesAtLink(drCrTpCd, invSlsCr.getString(COA_EXTN_CD), resAjePtrn.ajeLineIndTpCd.getValue())) {
                        resultCd = invSlsCr.getString(COA_EXTN_CD);
                    } else if (coaExtnCd.substring(0, 1).equals("@") || coaExtnCd.substring(0, 1).equals("#")) {
                        if (commonJrnlEntry.setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.EXTN, crOrDr, resAjePtrn, invSlsCr, mtMap)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaExtnCd");
                            
                            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeIntfcCmntTxt, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaExtnCd;
                    }

                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaExtnCd, resultCd);
                    break;

                default:
                    break;
            }
            if (defaultSetFlg) {
                break;
            }
        }
        if (defaultSetFlg) {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaBrCd, defCoaBrCd);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaCcCd, defCoaCcCd);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProdCd, defCoaProdCd);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaChCd, defCoaChCd);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaAfflCd, defCoaAfflCd);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaProjCd, defCoaProjCd);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.ajeCoaExtnCd, defCoaExtnCd);
        }

        boolean isError = false;

        // If value was not set, error
        if (!hasValue(ajeInvAcctDist.ajeCoaCmpyCd)) {
            isError = true;

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CMPY })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaBrCd)) {
            isError = true;

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_BR })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaCcCd)) {
            isError = true;

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CC })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaAcctCd)) {
            isError = true;

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_ACCT })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaProdCd)) {
            isError = true;

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_PROD })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaChCd)) {
            isError = true;

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_CH })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaAfflCd)) {
            isError = true;

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_AFFL })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaProjCd)) {
            isError = true;

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_PROJ })));
        }
        if (!hasValue(ajeInvAcctDist.ajeCoaExtnCd)) {
            isError = true;

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(COA_SEG_ERR, new String[] {NFACommonJrnlEntryConstant.COA_ERROR_EXTN })));
        }

        return !isError;
    }

    private String getErrorMsg(AJE_INV_ACCT_DIST_ERRTMsg tmsg, String newMsg) {
        String msg;
        if (hasValue(tmsg.invErrMsgTxt)) {
            if ((tmsg.invErrMsgTxt.getValue() + ", " + newMsg).length() > ERR_MSG_TXT_LEN) {
                // if length exceeds, not to add new message.
                msg = tmsg.invErrMsgTxt.getValue();
            } else {
                msg = tmsg.invErrMsgTxt.getValue() + ", " + newMsg;
            }

        } else {
            msg = newMsg;
        }
        return msg;
    }

    @SuppressWarnings("unused")
    private String getInvalidVal(AJE_INV_ACCT_DIST_ERRTMsg tmsg, String newVal) {
        String val;
        if (hasValue(tmsg.invldValTxt)) {
            if ((tmsg.invldValTxt.getValue() + ", " + newVal).length() > INVLD_VAL_TXT_LEN) {
                // if length exceeds, not to add new message.
                val = newVal;
            } else {
                val = tmsg.invldValTxt.getValue() + ", " + newVal;
            }
        } else {
            val = newVal;
        }
        return val;
    }

    @SuppressWarnings("unused")
    private String getInvalidVal2(AJE_INV_ACCT_DIST_ERRTMsg tmsg, String coaTpCd) {
        String val;
        String coaVal = "";

        if (COAID_COA_CMPY.equals(coaTpCd)) {
            coaVal = COAMSG_COA_CMPY;
        } else if (COAID_COA_BR.equals(coaTpCd)) {
            coaVal = COAMSG_COA_BR;
        } else if (COAID_COA_CC.equals(coaTpCd)) {
            coaVal = COAMSG_COA_CC;
        } else if (COAID_COA_ACCT.equals(coaTpCd)) {
            coaVal = COAMSG_COA_ACCT;
        } else if (COAID_COA_PROD.equals(coaTpCd)) {
            coaVal = COAMSG_COA_PROD;
        } else if (COAID_COA_CH.equals(coaTpCd)) {
            coaVal = COAMSG_COA_CH;
        } else if (COAID_COA_AFFL.equals(coaTpCd)) {
            coaVal = COAMSG_COA_AFFL;
        } else if (COAID_COA_PROJ.equals(coaTpCd)) {
            coaVal = COAMSG_COA_PROJ;
        } else if (COAID_COA_EXTN.equals(coaTpCd)) {
            coaVal = COAMSG_COA_EXTN;
        }

        if (hasValue(tmsg.invldValTxt)) {
            if ((tmsg.invldValTxt.getValue() + ", " + coaVal).length() > INVLD_VAL_TXT_LEN) {
                // if length exceeds, not to add new message.
                val = coaVal;
            } else {
                val = tmsg.invldValTxt.getValue() + ", " + coaVal;
            }
        } else {
            val = coaVal;
        }
        return val;
    }

    /**
     * @param invTpCd
     * @param amount
     * @return
     */
    private BigDecimal getAmount(String invTpCd, BigDecimal amount) {

        if (invTpCd.equals(INV_TP.CREDIT_MEMO)) {
            amount = amount.negate();
        }
        return amount;
    }

    /**
     * @param ajeInvAcctDist
     * @throws SQLException
     */
    private void createAjeInvAcctDist(AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, ResultSet invSlsCr) throws SQLException, NFACommonJrnlEntry.JrnlCommonException {
        // START 2018/05/21 E.Kameishi [QC#21841,MOD]
        if (ajeInvAcctDist != null) {
                if ((!(AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue())
                        || AJE_INV_ACCT_CLS.REVENUE.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue())
                        || AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue()))
                            && BigDecimal.ZERO.compareTo(ajeInvAcctDist.jrnlDealAmt.getValue()) == 0)
                    || (AJE_INV_ACCT_CLS.REVENUE.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue()) 
                            && this.coaMdseTPCdFrt.equals(invSlsCr.getString(COA_MDSE_TP_CD)))) {
                    // skip
                    return;
                }
            // add to list
            listAcctDist.add(ajeInvAcctDist);
        }
        // END 2018/05/21 E.Kameishi [QC#21841,MOD]
        submitInsertAcctDist(listAcctDist, false);
    }

    private void submitInsertAcctDist(List<AJE_INV_ACCT_DISTTMsg> list, boolean isLast) {

        if (list.size() == 0) {
            return;
        }

        if (list.size() == BULK_INSERT_CNT || isLast) {
            AJE_INV_ACCT_DISTTMsg[] ajeInvAcctDistMsgs = new AJE_INV_ACCT_DISTTMsg[list.size()];
            listAcctDist.toArray(ajeInvAcctDistMsgs);

            ajeInvAcctDistMsgs = setProcStsPerInv(ajeInvAcctDistMsgs);
            int rtn = S21FastTBLAccessor.insert(ajeInvAcctDistMsgs);

            if (rtn != list.size()) {
                throw new S21AbendException(UNIQUE_ERROR);
            }

            commitCount += rtn;

            // reset
            list.clear();
        }
    }

    private AJE_INV_ACCT_DISTTMsg[] setProcStsPerInv(AJE_INV_ACCT_DISTTMsg[] tmsgs) {

        AJE_INV_ACCT_DISTTMsg tmsg = new AJE_INV_ACCT_DISTTMsg();

        // If any of the record has error, entire invoice to be
        // logged as error.
        for (int i = 0; i < tmsgs.length; i++) {
            tmsg = (AJE_INV_ACCT_DISTTMsg) tmsgs[i];

            if (invNumErrorList.contains(tmsg.invNum.getValue())) {
                tmsg.procStsCd.setValue(PROC_STS.ERROR);
                tmsgs[i] = tmsg;
            }
        }

        return tmsgs;
    }

    /**
     * @param ajeInvAcctDist
     * @throws SQLException
     */
    private void createAjeInvAcctDistErr(AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr) throws NFACommonJrnlEntry.JrnlCommonException {

        if (ajeInvAcctDistErr != null) {
            listAcctDistErr.add(ajeInvAcctDistErr);
        }
        submitInsertAcctDistErr(listAcctDistErr, false);

    }

    private void submitInsertAcctDistErr(List<AJE_INV_ACCT_DIST_ERRTMsg> list, boolean isLast) {

        if (list.size() == 0) {
            return;
        }

        if (list.size() == BULK_INSERT_CNT || isLast) {

            int rtn = S21FastTBLAccessor.insert(list.toArray(new AJE_INV_ACCT_DIST_ERRTMsg[list.size()]));

            if (rtn != list.size()) {
                throw new S21AbendException(UNIQUE_ERROR);
            }

            commitCount += rtn;

            // reset
            list.clear();
        }
    }
    /**
     * @param param
     * @param ajeInvAcctDist
     * @param ajeInvAcctDistErr
     */
    private void glCodeCombinationCheck(AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr, ResultSet invSlsCr) throws SQLException, NFACommonJrnlEntry.JrnlCommonException {
        // START 2018/10/02 E.Kameishi [QC#28364,ADD]
        if ((!(AJE_INV_ACCT_CLS.REV_EARN_TMPLT.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue())
                || AJE_INV_ACCT_CLS.REVENUE.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue())
                || AJE_INV_ACCT_CLS.UNEARNED_REVENUE.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue()))
                    && BigDecimal.ZERO.compareTo(ajeInvAcctDist.jrnlDealAmt.getValue()) == 0)
            || (AJE_INV_ACCT_CLS.REVENUE.equals(ajeInvAcctDist.ajeInvAcctClsCd.getValue()) 
                    && this.coaMdseTPCdFrt.equals(invSlsCr.getString(COA_MDSE_TP_CD)))) {
            // skip
            return;
        }
        // END 2018/10/02 E.Kameishi [QC#28364,ADD]
        NFZC102001 api = new NFZC102001();
        NFZC102001PMsg apiMsg = new NFZC102001PMsg();

        apiMsg.glblCmpyCd.setValue(ajeInvAcctDist.glblCmpyCd.getValue());
        // If there is no error in 9segments master check, the same 9segments are not checked.
        String glCmbn = S21StringUtil.concatStrings(
                ajeInvAcctDist.ajeCoaCmpyCd.getValue(), DOT,
                ajeInvAcctDist.ajeCoaBrCd.getValue(), DOT,
                ajeInvAcctDist.ajeCoaCcCd.getValue(), DOT,
                ajeInvAcctDist.ajeCoaAcctCd.getValue(), DOT,
                ajeInvAcctDist.ajeCoaProdCd.getValue(), DOT,
                ajeInvAcctDist.ajeCoaChCd.getValue(), DOT,
                ajeInvAcctDist.ajeCoaAfflCd.getValue(), DOT,
                ajeInvAcctDist.ajeCoaProjCd.getValue(), DOT,
                ajeInvAcctDist.ajeCoaExtnCd.getValue());
        if (glCmbnList.contains(glCmbn)) {
            return;
        } else {
            apiMsg.xxMstChkFlg.setValue(ZYPConstant.FLG_ON_Y);
        }
        apiMsg.xxGlCmbnChkFlg.setValue(ZYPConstant.FLG_ON_Y);
        apiMsg.xxArcsApiChkFlg.setValue(ZYPConstant.FLG_OFF_N);

        apiMsg.coaCmpyCd.setValue(ajeInvAcctDist.ajeCoaCmpyCd.getValue());
        apiMsg.coaBrCd.setValue(ajeInvAcctDist.ajeCoaBrCd.getValue());
        apiMsg.coaCcCd.setValue(ajeInvAcctDist.ajeCoaCcCd.getValue());
        apiMsg.coaAcctCd.setValue(ajeInvAcctDist.ajeCoaAcctCd.getValue());
        apiMsg.coaProdCd.setValue(ajeInvAcctDist.ajeCoaProdCd.getValue());
        apiMsg.coaChCd.setValue(ajeInvAcctDist.ajeCoaChCd.getValue());
        apiMsg.coaAfflCd.setValue(ajeInvAcctDist.ajeCoaAfflCd.getValue());
        apiMsg.coaProjCd.setValue(ajeInvAcctDist.ajeCoaProjCd.getValue());
        apiMsg.coaExtnCd.setValue(ajeInvAcctDist.ajeCoaExtnCd.getValue());
        apiMsg.bizAppId.setValue("NFAB1200");

        api.execute(apiMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);

        int i = 0;
        String msgId;
        String msgTxt;
        boolean mstrChkFlg = false;
        for (; i < apiMsg.xxMsgIdList.getValidCount(); i++) {
            msgId = apiMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            msgTxt = apiMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue();

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invErrMsgTxt, getErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(msgId, new String[] {msgTxt })));
            
            if (ERR_MSG_LIST.contains(msgId)) {
                mstrChkFlg = true;
            }
        }
        if (!mstrChkFlg) {
            glCmbnList.add(glCmbn);
        }
        this.errorCount += i;
        if (apiMsg.xxMsgIdList.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procStsCd, PROC_STS.ERROR);
        }
    }

    private boolean setAjeInvAcctDistAmountValuesForFrtTax(NFAC000101PMsg resAjePtrn, ResultSet rsNotJrnlized, String drCrTpCd) throws SQLException, JrnlCommonException {

        AJE_INV_ACCT_DISTTMsg ajeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
        AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr = new AJE_INV_ACCT_DIST_ERRTMsg();

        // Set Common Data
        setAjeInvAcctDistCommonValues(ajeInvAcctDist, rsNotJrnlized, resAjePtrn, drCrTpCd);

        // Set Amount FREIGHT TAX Date
        setAjeInvAcctDistAmountValuesForFrtTax(ajeInvAcctDist, rsNotJrnlized);

        // Set 9 Segments Data
        if (!setAjeInvAcctDist9SegmentsValues(ajeInvAcctDist, ajeInvAcctDistErr, rsNotJrnlized, resAjePtrn, drCrTpCd)) {
            // error
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.procStsCd, PROC_STS.ERROR);
        } else {
            // Check 9 Segments
            glCodeCombinationCheck(ajeInvAcctDist, ajeInvAcctDistErr, rsNotJrnlized);
        }

        // START 2018/05/21 E.Kameishi [QC#21841,MOD]
        createAjeInvAcctDist(ajeInvAcctDist, rsNotJrnlized);
        // END 2018/05/21 E.Kameishi [QC#21841,MOD]

        // If any error, create error record
        if (PROC_STS.ERROR.equals(ajeInvAcctDist.procStsCd.getValue())) {

            // set value other than error message
            BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_ERR_SQ);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.glblCmpyCd, ajeInvAcctDist.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistErrPk, pk);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistPk, ajeInvAcctDist.ajeInvAcctDistPk.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invNum, ajeInvAcctDist.invNum.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invBolLineNum, ajeInvAcctDist.invBolLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineNum, ajeInvAcctDist.invLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invLineSubNum, ajeInvAcctDist.invLineSubNum.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invTrxLineSubNum, ajeInvAcctDist.invLineSubTrxNum.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.ajeInvAcctDistSqNum, ajeInvAcctDist.ajeInvAcctDistSqNum.getValue());

            String coaConcat = nvl(ajeInvAcctDist.ajeCoaCmpyCd.getValue()).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaBrCd.getValue())).concat(",").concat(
                    nvl(ajeInvAcctDist.ajeCoaCcCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaAcctCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaProdCd.getValue()))
                    .concat(",").concat(nvl(ajeInvAcctDist.ajeCoaChCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaAfflCd.getValue())).concat(",").concat(
                            nvl(ajeInvAcctDist.ajeCoaProjCd.getValue())).concat(",").concat(nvl(ajeInvAcctDist.ajeCoaExtnCd.getValue()));

            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistErr.invldValTxt, coaConcat);

            createAjeInvAcctDistErr(ajeInvAcctDistErr);
        }

        return true;
    }

    /**
     * Set Amount Freight TAX by INV_BOL
     * @param ajeInvAcctDist
     * @param invSlsCr
     * @return booolean
     * @throws SQLException
     * @throws JrnlCommonException
     */
    private boolean setAjeInvAcctDistAmountValuesForFrtTax(AJE_INV_ACCT_DISTTMsg ajeInvAcctDist, ResultSet invSlsCr) throws SQLException, JrnlCommonException {

        String invTpCd = invSlsCr.getString(INV_TP_CD);

        // Freight TAX by INV_BOL
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlDealAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(FRT_DEAL_TAX_AMT)));
        ZYPEZDItemValueSetter.setValue(ajeInvAcctDist.jrnlFuncAmt, getAmount(invTpCd, invSlsCr.getBigDecimal(FRT_FUNC_TAX_AMT)));

        return true;
    }
    /**
     * Update index Number
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean updateInvoice_ACCT_DISTWithIdxNum() {

        EZDTMsg tmsgs[] = new EZDTMsg[BULK_INSERT_CNT];
        int cnt = 0;
        // ----------------------------------------------
        // Update AJE_LINE_IDX_NUM in AJE_INV_ACCT_DIST
        // ----------------------------------------------
        for (String invNum : invNumList) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("invNum", invNum);
            List<Map<String, Object>> ajeInvAcctDistList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getAjeInvAcctDistList", ssmParam);

            int ajeLineIdxNum = INIT_AJE_LINE_IDX_NUM;
            BigDecimal dsInvSlsCrPk = BigDecimal.ZERO;
            String preDrCrCd = "";

            if (ajeInvAcctDistList == null) {
                return false;
            }

            for (Map<String, Object> ajeInvAcctDistListData : ajeInvAcctDistList) {

                BigDecimal ajeInvAcctDistPk = (BigDecimal) ajeInvAcctDistListData.get(AJE_INV_ACCT_DIST_PK);
                String drCrTpCd = (String) ajeInvAcctDistListData.get(DR_CR_TP_CD);

                if (preDrCrCd.equals(drCrTpCd)) {
                    if (dsInvSlsCrPk.compareTo((BigDecimal) ajeInvAcctDistListData.get(DS_INV_SLS_CR_PK)) == 0) {
                        ajeLineIdxNum++;
                    } else {
                        ajeLineIdxNum = INIT_AJE_LINE_IDX_NUM;
                    }
                } else {
                    ajeLineIdxNum = INIT_AJE_LINE_IDX_NUM;
                }
                dsInvSlsCrPk = (BigDecimal) ajeInvAcctDistListData.get(DS_INV_SLS_CR_PK);
                preDrCrCd = drCrTpCd;

                AJE_INV_ACCT_DISTTMsg inMsg = new AJE_INV_ACCT_DISTTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inMsg.ajeInvAcctDistPk, ajeInvAcctDistPk);
                AJE_INV_ACCT_DISTTMsg updMsg = (AJE_INV_ACCT_DISTTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
                ZYPEZDItemValueSetter.setValue(updMsg.ajeLineIdxNum, String.format("%02d", ajeLineIdxNum));

                tmsgs[cnt] = updMsg;
                cnt = cnt + 1;

                if (cnt >= BULK_INSERT_CNT) {
                    // bulk update
                    S21FastTBLAccessor.update(tmsgs);
                    // initialize
                    tmsgs = new AJE_INV_ACCT_DISTTMsg[BULK_INSERT_CNT];
                    cnt = 0;
                }
            }
        }
        // update remaining data
        if (cnt != 0) {
            tmsgs = commonJrnlEntry.changeArraySize(tmsgs, cnt);
            S21FastTBLAccessor.update(tmsgs);
        }
        
        return true;
    }

    private void updateInvoice() {

        EZDTMsg tmsgs[] = new EZDTMsg[BULK_INSERT_CNT];
        int cnt = 0;

        for (String invNum : invNumErrorList) {
            INVTMsg tmsg = new INVTMsg();
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmsg.invNum, invNum);
            INVTMsg updMsg = (INVTMsg) EZDTBLAccessor.findByKeyForUpdate(tmsg);
            ZYPEZDItemValueSetter.setValue(updMsg.itrlInvErrFlg, ZYPConstant.FLG_ON_Y);
            
            tmsgs[cnt] = updMsg;
            cnt = cnt + 1;

            if (cnt >= BULK_INSERT_CNT) {
                // bulk update
                S21FastTBLAccessor.update(tmsgs);
                // initialize
                tmsgs = new INVTMsg[BULK_INSERT_CNT];
                cnt = 0;
            }
        }
        // update remaining data
        if (cnt != 0) {
            tmsgs = commonJrnlEntry.changeArraySize(tmsgs, cnt);
            S21FastTBLAccessor.update(tmsgs);
        }
    }

    private boolean checkBsPlTp(String glblCmpyCd, String coaAcctCd) throws SQLException {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("coaAcctCd", coaAcctCd);

        String bsPlTpCd = (String) ssmBatchClient.queryObject("getBsPlTp", queryParam);

        if (BS_PL_TP_CD_BS.equals(bsPlTpCd)) {
            return true;
        }
        return false;
    }


    /**
     * @param procDt
     * @return
     */
    private String getGlDt(String glblCmpyCd, String procDt, String acctDt) {
        String glDt = null;
        BigDecimal days = BigDecimal.ONE;
        BigDecimal constDays = ZYPCodeDataUtil.getNumConstValue("NFZC1030_PERIOD_CHNG_PRE_MONTH", glblCmpyCd);
        if (constDays != null) {
            days = constDays;
        }
        String preMonthStartDay = getPreMonthStartDay(procDt);
        String preMonthEndDay = getPreMonthEndDay(procDt);
        String businessDay = ZYPDateUtil.addBusinessDay(glblCmpyCd, preMonthEndDay, days.intValue());
        boolean preChangeFlag = false;
        if (procDt.compareTo(businessDay) <= 0) {
            preChangeFlag = true;
        }
        if (preChangeFlag == true) {
            if (acctDt.compareTo(preMonthStartDay) < 0) {
                glDt = procDt;
            } else {
                glDt = acctDt;
            }
        } else {
            if (acctDt.compareTo(preMonthEndDay) <= 0) {
                glDt = procDt;
            } else {
                glDt = acctDt;
            }
        }
        return glDt;
    }

    /**
     * getPreMonthStartDay
     * @param date String "YYYYMMDD'
     * @return lastDay String "YYYYMMDD"
     */
    private String getPreMonthStartDay(String date) {
        String preMonthStartDay = null;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        c.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 2);
        c.set(Calendar.DATE, 1);
        preMonthStartDay = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
        return preMonthStartDay;
    }

    /**
     * getPreMonthEndDay
     * @param date String "YYYYMMDD'
     * @return preMonthEndDay String "YYYYMMDD"
     */
    private String getPreMonthEndDay(String date) {
        String preMonthEndDay = null;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        c.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 2);
        c.set(Calendar.DATE, 1);
        int day = c.getActualMaximum(Calendar.DATE);
        preMonthEndDay = new SimpleDateFormat("yyyyMM").format(c.getTime()) + day;
        return preMonthEndDay;
    }

    // START 2018/05/21 E.Kameishi [QC#23166,ADD]
    @SuppressWarnings("unchecked")
    private void setSvcInvChrgTp() {
        EZDTMsg tmsgs[] = new EZDTMsg[BULK_INSERT_CNT];
        int cnt = 0;

        String manAllocTrxRsnCd = ZYPCodeDataUtil.getVarCharConstValue("MAN_ALLOC_TRX_RSN_CD", this.glblCmpyCd);
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("trxCd", TRX.SALES);
        queryParam.put("trxRsnCdList", manAllocTrxRsnCd.split(","));
        queryParam.put("flgN", ZYPConstant.FLG_OFF_N);
        queryParam.put("nfc", SYS_SRC.S21_ACCOUNTING_AR);
        queryParam.put("procYrMth", ZYPDateUtil.DateFormatter(this.batProcDt, "yyyyMMdd", "yyyyMM") + "%");
        BigDecimal multiCalcCnt = ZYPCodeDataUtil.getNumConstValue("MULTI_INV_CALC_CNT", glblCmpyCd);
        queryParam.put("divideNum", this.divideNum);
        queryParam.put("multiCalcCnt", multiCalcCnt);
        // START 2018/10/26 E.Kameishi [QC#28976,ADD]
        queryParam.put("creditMemo", INV_TP.CREDIT_MEMO);
        // END 2018/10/26 E.Kameishi [QC#28976,ADD]

        List<Map<String, Object>> svcInvChrgTpList = (List<Map<String, Object>>)  ssmBatchClient.queryObjectList("getSvcInvChrgTp", queryParam);

        if (svcInvChrgTpList != null) {
            for (Map<String, Object> svcInvChrgTp : svcInvChrgTpList) {
                String invSvcInvChrgTp = (String) svcInvChrgTp.get("INV_SVC_INV_CHRG_TP_CD");
                String svcSvcInvChrgTp = (String) svcInvChrgTp.get("SVC_INV_CHRG_TP_CD");
                BigDecimal dsInvSlsCrPk = (BigDecimal) svcInvChrgTp.get("DS_INV_SLS_CR_PK");
                
                if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(invSvcInvChrgTp) || 
                        (SVC_INV_CHRG_TP.ADDITIONAL_CHARGE.equals(invSvcInvChrgTp) && SVC_INV_CHRG_TP.BASE_CHARGE.equals(svcSvcInvChrgTp))) {
                    DS_INV_SLS_CRTMsg dsInvSlsCrTMsg = new DS_INV_SLS_CRTMsg();

                    ZYPEZDItemValueSetter.setValue(dsInvSlsCrTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dsInvSlsCrTMsg.dsInvSlsCrPk, dsInvSlsCrPk);
                    dsInvSlsCrTMsg = (DS_INV_SLS_CRTMsg) EZDTBLAccessor.findByKeyForUpdate(dsInvSlsCrTMsg);

                    ZYPEZDItemValueSetter.setValue(dsInvSlsCrTMsg.dfrdAcctgRuleCd, DFRD_ACCTG_RULE.DEFERRED);

                    tmsgs[cnt] = dsInvSlsCrTMsg;
                    cnt = cnt + 1;

                    if (cnt >= BULK_INSERT_CNT) {
                        // bulk update
                        S21FastTBLAccessor.update(tmsgs);
                        // initialize
                        tmsgs = new DS_INV_SLS_CRTMsg[BULK_INSERT_CNT];
                        cnt = 0;
                    }
                }
            }
            // update remaining data
            if (cnt != 0) {
                tmsgs = commonJrnlEntry.changeArraySize(tmsgs, cnt);
                S21FastTBLAccessor.update(tmsgs);
            }
        }
    }
    // END 2018/05/21 E.Kameishi [QC#23166,ADD]    
    
    @Override
    protected void termRoutine() {
        
        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, errorCount, commitCount + errorCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }
}
