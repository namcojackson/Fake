package com.canon.cusa.s21.batch.NFA.NFAB118001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;


import business.db.AJE_INV_ACCT_DISTTMsg;
import business.db.AJE_INV_ACCT_DISTTMsgArray;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INV_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL_RSN_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Credit Memo Journalize
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 02/19/2018   Hitachi         E.Kameishi      Create          N/A
 * 03/13/2018   Hitachi         E.Kameishi      Update          QC#24787
 * 04/04/2018   Hitachi         E.Kameishi      Update          QC#24870
 * 04/13/2018   Hitachi         E.Kameishi      Update          QC#25489
 * 03/25/2019   Hitachi         E.Kameishi      Update          QC#30847
 * 08/12/2021   CITS            D.Morimoto      Update          QC#59035
 *</pre>
 */
public class NFAB118001 extends S21BatchMain implements ZYPConstant, NFAB118001Constant{

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

    /** SSM Batch Clinent */
    private S21SsmBatchClient ssmBatchClient;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Batch Process Date */
    private String batProcDt = null;

    /** Error message */
    private String errMsg = "";

    /** List of Tmsg*/
    List<AJE_INV_ACCT_DISTTMsg> acctDistList = new ArrayList<AJE_INV_ACCT_DISTTMsg> ();

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        new NFAB118001().executeBatch(NFAB118001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        this.profile = S21UserProfileServiceFactory.getInstance().getService();

        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        this.batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd, BUSINESS_ID);

        S21InfoLogOutput.println("initRoutine Method End");

    }

    @SuppressWarnings("unchecked")
    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = getCreditMemo();
            rs = ps.executeQuery();

            mainProcess(rs);

        } catch (SQLException ex) {
            // abend
            throw new S21AbendException(NFAM0035E, new String[] {BUSINESS_ID, ex.getMessage()}); 

        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        commit();
        S21InfoLogOutput.println("mainRoutine Method End");
    }

    @SuppressWarnings("unchecked")
    private void mainProcess(ResultSet rs) throws SQLException{

        while (rs.next()) {
            // START 2018/04/13 E.Kameishi [QC#25489,ADD]
            //if RMA Credit, skip
            DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtl = new DS_CPO_RTRN_DTLTMsg();
            dsCpoRtrnDtl.setSQLID("001");
            dsCpoRtrnDtl.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            dsCpoRtrnDtl.setConditionValue("cpoOrdNum01", rs.getBigDecimal(CPO_ORD_NUM));
            DS_CPO_RTRN_DTLTMsgArray dsCpoRtrnDtlArray = (DS_CPO_RTRN_DTLTMsgArray) EZDTBLAccessor.findByCondition(dsCpoRtrnDtl);
            
            if (dsCpoRtrnDtlArray.length() > 0) {
                continue;
            }
            // END 2018/04/13 E.Kameishi [QC#25489,ADD]
            
            // get Original DS_INV_SLS_CR
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", rs.getString(GLBL_CMPY_CD));
            queryParam.put("invNum", rs.getString(INV_NUM));
            queryParam.put("invBoLineNum", rs.getString(INV_BOL_LINE_NUM));
            queryParam.put("invLineNum", rs.getString(INV_LINE_NUM));
            queryParam.put("invLineSubNum", rs.getString(INV_LINE_SUB_NUM));
            queryParam.put("invLineSubTrxNum", rs.getString(INV_LINE_SUB_TRX_NUM));
            queryParam.put("slsRepTocCd", rs.getString(SLS_REP_TOC_CD));
            queryParam.put("slsRepCrPct", rs.getBigDecimal(SLS_REP_CR_PCT));
            if (hasValue(rs.getString(INV_LINE_SPL_TP_CD))){
                queryParam.put("invLineSplTpCd", rs.getString(INV_LINE_SPL_TP_CD));
            } else {
                queryParam.put("invLineSplTpCd", "NONE");
            }
            queryParam.put("invLineSplPct", rs.getBigDecimal(INV_LINE_SPL_PCT));
            queryParam.put("origInvNum", rs.getString(ORIG_INV_NUM));
            queryParam.put("mdseCd", rs.getString(MDSE_CD));
            queryParam.put("coaBrCd", rs.getString(COA_BR_CD));
            if (hasValue(rs.getString(SVC_INV_CHRG_TP_CD))){
                queryParam.put("svcInvChrgTpCd", rs.getString(SVC_INV_CHRG_TP_CD));
            } else {
                queryParam.put("svcInvChrgTpCd", "NONE");
            }

            if (hasValue(rs.getBigDecimal(SVC_MACH_MSTR_PK))){
                queryParam.put("svcMachMstrPk", rs.getBigDecimal(SVC_MACH_MSTR_PK));
            } else {
                queryParam.put("svcMachMstrPk", 0);
            }

            if (hasValue(rs.getString(INTG_MDSE_CD))){
                queryParam.put("intgMdseCd", rs.getString(INTG_MDSE_CD));
            } else {
                queryParam.put("intgMdseCd", "NONE");
            }

            if (TRX_RSN_LIST.contains(rs.getString(TRX_RSN_CD))) {
                queryParam.put("trxRsnCdFlg", ZYPConstant.FLG_ON_Y);
            }
            // START 2019/03/25 E.Kameishi [QC#30847,ADD]
            queryParam.put("nfc", SYS_SRC.S21_ACCOUNTING_AR);
            // END 2019/03/25 E.Kameishi [QC#30847,ADD]

            Map<String, Object> origDsInvSlsCrMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getOrigDsInvSlsCr", queryParam);

            if (origDsInvSlsCrMap != null) {
                if (ZYPConstant.FLG_OFF_N.equals((String) origDsInvSlsCrMap.get(DFRD_REV_FLG))) {
                    AJE_INV_ACCT_DISTTMsg origAjeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
                    origAjeInvAcctDist.setSQLID("003");
                    origAjeInvAcctDist.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                    origAjeInvAcctDist.setConditionValue("dsInvSlsCrPk01", origDsInvSlsCrMap.get(DS_INV_SLS_CR_PK));
                    AJE_INV_ACCT_DISTTMsgArray origAjeInvAcctDistArray = (AJE_INV_ACCT_DISTTMsgArray) EZDTBLAccessor.findByCondition(origAjeInvAcctDist);

                    // START 2018/04/13 E.Kameishi [QC#25489,MOD]
                    AJE_INV_ACCT_DISTTMsg crAjeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
                    crAjeInvAcctDist.setSQLID("003");
                    crAjeInvAcctDist.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                    crAjeInvAcctDist.setConditionValue("dsInvSlsCrPk01", rs.getBigDecimal(DS_INV_SLS_CR_PK));
                    AJE_INV_ACCT_DISTTMsgArray crAjeInvAcctDistArray = (AJE_INV_ACCT_DISTTMsgArray) EZDTBLAccessor.findByCondition(crAjeInvAcctDist);

                    if (crAjeInvAcctDistArray.length() > 0) {
                        // Delete Credit AJE_INV_ACCT_DIST
                        deleteData(this.glblCmpyCd, rs.getBigDecimal(DS_INV_SLS_CR_PK));

                        setAcctDist(rs, crAjeInvAcctDistArray, origAjeInvAcctDistArray);
                    }

//                    if (origAjeInvAcctDistArray.length() > 0) {
//                        // Delete Credit AJE_INV_ACCT_DIST
//                        deleteData(this.glblCmpyCd, rs.getBigDecimal(DS_INV_SLS_CR_PK));
//
//                        for (int i = 0; i < origAjeInvAcctDistArray.length(); i++) {
//                            setAcctDist(rs, origAjeInvAcctDistArray.no(i));
//                        }
//                    }
                    // END 2018/04/13 E.Kameishi [QC#25489,MOD]
                } else {
                    if (DFRD_ACCTG_RULE.SUBSCRIPTION_SERVICE.equals((String) origDsInvSlsCrMap.get(DFRD_ACCTG_RULE_CD)) 
                            && CR_REBIL_RSN_CATG.EXTERNAL.equals(rs.getString(CR_REBIL_RSN_CATG_CD))) {

                        // get original Unearned AJE_INV_ACCT_DIST
                        AJE_INV_ACCT_DISTTMsg origUnearnedAjeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
                        origUnearnedAjeInvAcctDist.setSQLID("004");
                        origUnearnedAjeInvAcctDist.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                        origUnearnedAjeInvAcctDist.setConditionValue("dsInvSlsCrPk01", origDsInvSlsCrMap.get(DS_INV_SLS_CR_PK));
                        origUnearnedAjeInvAcctDist.setConditionValue("ajeInvAcctClsCd01", AJE_INV_ACCT_CLS.UNEARNED_REVENUE);
                        AJE_INV_ACCT_DISTTMsgArray origUnearnedAjeInvAcctDistArray = (AJE_INV_ACCT_DISTTMsgArray) EZDTBLAccessor.findByCondition(origUnearnedAjeInvAcctDist);

                        // get Credit Revenue Earned AJE_INV_ACCT_DIST
                        AJE_INV_ACCT_DISTTMsg origRevEarnAjeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
                        origRevEarnAjeInvAcctDist.setSQLID("004");
                        origRevEarnAjeInvAcctDist.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                        origRevEarnAjeInvAcctDist.setConditionValue("dsInvSlsCrPk01", origDsInvSlsCrMap.get(DS_INV_SLS_CR_PK));
                        origRevEarnAjeInvAcctDist.setConditionValue("ajeInvAcctClsCd01", AJE_INV_ACCT_CLS.REV_EARN_TMPLT);
                        AJE_INV_ACCT_DISTTMsgArray origRevEarnAjeInvAcctDistArray = (AJE_INV_ACCT_DISTTMsgArray) EZDTBLAccessor.findByCondition(origRevEarnAjeInvAcctDist);

                        // get Credit Revenue AJE_INV_ACCT_DIST
                        AJE_INV_ACCT_DISTTMsg crAjeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
                        crAjeInvAcctDist.setSQLID("004");
                        crAjeInvAcctDist.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                        crAjeInvAcctDist.setConditionValue("dsInvSlsCrPk01", rs.getBigDecimal(DS_INV_SLS_CR_PK));
                        crAjeInvAcctDist.setConditionValue("ajeInvAcctClsCd01", AJE_INV_ACCT_CLS.REVENUE);
                        AJE_INV_ACCT_DISTTMsgArray crAjeInvAcctDistArray = (AJE_INV_ACCT_DISTTMsgArray) EZDTBLAccessor.findByCondition(crAjeInvAcctDist);

                        if (origRevEarnAjeInvAcctDistArray.length() > 0) {
                            for (int i = 0; i < crAjeInvAcctDistArray.length(); i++) {
                                // Delete Credit AJE_INV_ACCT_DIST
                                S21FastTBLAccessor.removePhysical(new AJE_INV_ACCT_DISTTMsg[] {crAjeInvAcctDistArray.no(i)});
                                // Insert Credit AJE_INV_ACCT_DIST
                                //START 2018/04/04 E.Kameishi [QC#24870,MOD]
                                setAcctDist(rs, origRevEarnAjeInvAcctDistArray, origUnearnedAjeInvAcctDistArray, crAjeInvAcctDistArray.no(i));
                                //END 2018/04/04 E.Kameishi [QC#24870,MOD]
                            }
                        }
                    } else {
                        // get original Revenue AJE_INV_ACCT_DIST
                        Map<String, Object> param = new HashMap<String, Object>();
                        param.put("glblCmpyCd", this.glblCmpyCd);
                        param.put("dsInvSlsCrPk", origDsInvSlsCrMap.get(DS_INV_SLS_CR_PK));
                        param.put("ajeInvAcctClsCd", AJE_INV_ACCT_CLS.REVENUE_EARNED);
                        List<AJE_INV_ACCT_DISTTMsg> origRevenueAjeInvAcctDistList = (List<AJE_INV_ACCT_DISTTMsg>) this.ssmBatchClient.queryObjectList("getAcctDistRev", param);

                        // get original Unearned AJE_INV_ACCT_DIST
                        AJE_INV_ACCT_DISTTMsg origUnearnedAjeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
                        origUnearnedAjeInvAcctDist.setSQLID("004");
                        origUnearnedAjeInvAcctDist.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                        origUnearnedAjeInvAcctDist.setConditionValue("dsInvSlsCrPk01", origDsInvSlsCrMap.get(DS_INV_SLS_CR_PK));
                        origUnearnedAjeInvAcctDist.setConditionValue("ajeInvAcctClsCd01", AJE_INV_ACCT_CLS.UNEARNED_REVENUE);
                        AJE_INV_ACCT_DISTTMsgArray origUnearnedAjeInvAcctDistArray = (AJE_INV_ACCT_DISTTMsgArray) EZDTBLAccessor.findByCondition(origUnearnedAjeInvAcctDist);

                        // get Credit Revenue AJE_INV_ACCT_DIST
                        AJE_INV_ACCT_DISTTMsg crAjeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
                        crAjeInvAcctDist.setSQLID("004");
                        crAjeInvAcctDist.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                        crAjeInvAcctDist.setConditionValue("dsInvSlsCrPk01", rs.getBigDecimal(DS_INV_SLS_CR_PK));
                        crAjeInvAcctDist.setConditionValue("ajeInvAcctClsCd01", AJE_INV_ACCT_CLS.REVENUE);
                        AJE_INV_ACCT_DISTTMsgArray crAjeInvAcctDistArray = (AJE_INV_ACCT_DISTTMsgArray) EZDTBLAccessor.findByCondition(crAjeInvAcctDist);

                        if (origRevenueAjeInvAcctDistList.size() != 0) {
                            for (int i = 0; i < crAjeInvAcctDistArray.length(); i++) {
                                // Delete Credit AJE_INV_ACCT_DIST
                                S21FastTBLAccessor.removePhysical(new AJE_INV_ACCT_DISTTMsg[] {crAjeInvAcctDistArray.no(i)});
                                // Insert Credit AJE_INV_ACCT_DIST
                                //START 2018/04/04 E.Kameishi [QC#24870,MOD]
                                setAcctDist(rs, origRevenueAjeInvAcctDistList, origUnearnedAjeInvAcctDistArray, crAjeInvAcctDistArray.no(i));
                                //END 2018/04/04 E.Kameishi [QC#24870,MOD]
                            }
                        } else {
                            // get Credit Revenue Earned AJE_INV_ACCT_DIST
                            AJE_INV_ACCT_DISTTMsg origRevEarnAjeInvAcctDist = new AJE_INV_ACCT_DISTTMsg();
                            origRevEarnAjeInvAcctDist.setSQLID("004");
                            origRevEarnAjeInvAcctDist.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                            origRevEarnAjeInvAcctDist.setConditionValue("dsInvSlsCrPk01", origDsInvSlsCrMap.get(DS_INV_SLS_CR_PK));
                            origRevEarnAjeInvAcctDist.setConditionValue("ajeInvAcctClsCd01", AJE_INV_ACCT_CLS.REV_EARN_TMPLT);
                            AJE_INV_ACCT_DISTTMsgArray origRevEarnAjeInvAcctDistArray = (AJE_INV_ACCT_DISTTMsgArray) EZDTBLAccessor.findByCondition(origRevEarnAjeInvAcctDist);

                            if (origRevEarnAjeInvAcctDistArray.length() > 0) {
                                for (int i = 0; i < crAjeInvAcctDistArray.length(); i++) {
                                    // Delete Credit AJE_INV_ACCT_DIST
                                    S21FastTBLAccessor.removePhysical(new AJE_INV_ACCT_DISTTMsg[] {crAjeInvAcctDistArray.no(i)});
                                    // Insert Credit AJE_INV_ACCT_DIST
                                    //START 2018/04/04 E.Kameishi [QC#24870,MOD]
                                    setAcctDist(rs, origRevEarnAjeInvAcctDistArray, origUnearnedAjeInvAcctDistArray, crAjeInvAcctDistArray.no(i));
                                    //END 2018/04/04 E.Kameishi [QC#24870,MOD]
                                }
                            }
                        }
                    }
                }
            }
        }
        submitInsert(acctDistList, true);
    }

    private PreparedStatement getCreditMemo() throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invTpCdCm", INV_TP.CREDIT_MEMO);
        queryParam.put("nfc", SYS_SRC.S21_ACCOUNTING_AR);
        queryParam.put("ne6", SYS_SRC.CFS);
        queryParam.put("nfm", SYS_SRC.EMSD);
        queryParam.put("batProcDt", this.batProcDt);

        return this.ssmLLClient.createPreparedStatement("getCreditMemo", queryParam);
    }

    private void deleteData(String glblCmpyCd, BigDecimal dsInvSlsCrPk) {

        AJE_INV_ACCT_DISTTMsg tmsg = new AJE_INV_ACCT_DISTTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.dsInvSlsCrPk, dsInvSlsCrPk);

        S21FastTBLAccessor.removeByPartialValue(tmsg, new String[] {"glblCmpyCd", "dsInvSlsCrPk" });
    }

    // START 2018/04/13 E.Kameishi [QC#25489,MOD]
//    private void setAcctDist(ResultSet rs, AJE_INV_ACCT_DISTTMsg inTmsg) throws SQLException{
//
//        if (DR_CD.equals(inTmsg.drCrTpCd.getValue())) {
//            setValue(inTmsg.drCrTpCd, CR_CD);
//        } else {
//            setValue(inTmsg.drCrTpCd, DR_CD);
//        }
//        BigDecimal ajeInvAcctDistSeqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_SQ);
//        setValue(inTmsg.ajeInvAcctDistPk, ajeInvAcctDistSeqNum);
//        setValue(inTmsg.invNum, rs.getString(INV_NUM));
//        setValue(inTmsg.dsInvSlsCrPk, rs.getBigDecimal(DS_INV_SLS_CR_PK));
//        //START 2018/03/13 E.Kameishi [QC#24787,ADD]
//        setValue(inTmsg.jrnlCratDt, "");
//        setValue(inTmsg.procDt, rs.getString(PROC_DT));
//        setValue(inTmsg.glDt, rs.getString(GL_DT));
//        //END 2018/03/13 E.Kameishi [QC#24787,ADD]
//        //START 2018/04/04 E.Kameishi [QC#24870,ADD]
//        if (hasValue(rs.getString(CPO_DTL_LINE_NUM))) {
//            setValue(inTmsg.invBolLineNum, STR_001);
//            setValue(inTmsg.invLineNum, ZYPCommonFunc.leftPad(rs.getString(CPO_DTL_LINE_NUM), STRING_LENGTH_5, "0"));
//        }
//        //END 2018/04/04 E.Kameishi [QC#24870,ADD]
//        acctDistList.add(inTmsg);
//
//        submitInsert(acctDistList, false);
//    }

    private void setAcctDist(ResultSet rs, AJE_INV_ACCT_DISTTMsgArray crAjeInvAcctDistArray, AJE_INV_ACCT_DISTTMsgArray origAjeInvAcctDistArray) throws SQLException{

        for (int i = 0; i < crAjeInvAcctDistArray.length(); i++) {
            AJE_INV_ACCT_DISTTMsg crAjeInvAcctDist = crAjeInvAcctDistArray.no(i);

            for (int j = 0; j < origAjeInvAcctDistArray.length(); j++) {
                AJE_INV_ACCT_DISTTMsg origAjeInvAcctDist = origAjeInvAcctDistArray.no(j);
                
                if (crAjeInvAcctDist.ajeInvAcctClsCd.getValue().equals((origAjeInvAcctDist.ajeInvAcctClsCd.getValue()))
                        && crAjeInvAcctDist.drCrTpCd.getValue().equals(origAjeInvAcctDist.drCrTpCd.getValue())) {
                    BigDecimal ajeInvAcctDistSeqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_SQ);
                    setValue(crAjeInvAcctDist.ajeInvAcctDistPk, ajeInvAcctDistSeqNum);
                    setValue(crAjeInvAcctDist.ajeCoaCmpyCd, origAjeInvAcctDist.ajeCoaCmpyCd);
                    setValue(crAjeInvAcctDist.ajeCoaBrCd, origAjeInvAcctDist.ajeCoaBrCd);
                    setValue(crAjeInvAcctDist.ajeCoaCcCd, origAjeInvAcctDist.ajeCoaCcCd);
                    setValue(crAjeInvAcctDist.ajeCoaAcctCd, origAjeInvAcctDist.ajeCoaAcctCd);
                    setValue(crAjeInvAcctDist.ajeCoaProdCd, origAjeInvAcctDist.ajeCoaProdCd);
                    setValue(crAjeInvAcctDist.ajeCoaChCd, origAjeInvAcctDist.ajeCoaChCd);
                    setValue(crAjeInvAcctDist.ajeCoaAfflCd, origAjeInvAcctDist.ajeCoaAfflCd);
                    setValue(crAjeInvAcctDist.ajeCoaProjCd, origAjeInvAcctDist.ajeCoaProjCd);
                    setValue(crAjeInvAcctDist.ajeCoaExtnCd, origAjeInvAcctDist.ajeCoaExtnCd);
                }
            }
            acctDistList.add(crAjeInvAcctDist);
        }
        submitInsert(acctDistList, false);
    }
    // END 2018/04/13 E.Kameishi [QC#25489,MOD]

    private void setAcctDist(ResultSet rs, List<AJE_INV_ACCT_DISTTMsg> origRevenueAjeInvAcctDistList, AJE_INV_ACCT_DISTTMsgArray origUnearnedAjeInvAcctDistArray, AJE_INV_ACCT_DISTTMsg crAjeInvAcctDist) throws SQLException{

        // Insert Credit CR AJE_INV_ACCT_DIST
        if (CR_CD.equals(crAjeInvAcctDist.drCrTpCd.getValue())) {
            for (int i = 0; i < origRevenueAjeInvAcctDistList.size(); i++) {
                AJE_INV_ACCT_DISTTMsg origRevenueAjeInvAcctDist = origRevenueAjeInvAcctDistList.get(i);
                if (CR_CD.equals(origRevenueAjeInvAcctDist.drCrTpCd.getValue())) {
                    BigDecimal ajeInvAcctDistSeqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_SQ);
                    setValue(crAjeInvAcctDist.ajeInvAcctDistPk, ajeInvAcctDistSeqNum);
                    setValue(crAjeInvAcctDist.ajeCoaCmpyCd, origRevenueAjeInvAcctDist.ajeCoaCmpyCd);
                    setValue(crAjeInvAcctDist.ajeCoaBrCd, origRevenueAjeInvAcctDist.ajeCoaBrCd);
                    setValue(crAjeInvAcctDist.ajeCoaCcCd, origRevenueAjeInvAcctDist.ajeCoaCcCd);
                    setValue(crAjeInvAcctDist.ajeCoaAcctCd, origRevenueAjeInvAcctDist.ajeCoaAcctCd);
                    setValue(crAjeInvAcctDist.ajeCoaProdCd, origRevenueAjeInvAcctDist.ajeCoaProdCd);
                    setValue(crAjeInvAcctDist.ajeCoaChCd, origRevenueAjeInvAcctDist.ajeCoaChCd);
                    setValue(crAjeInvAcctDist.ajeCoaAfflCd, origRevenueAjeInvAcctDist.ajeCoaAfflCd);
                    setValue(crAjeInvAcctDist.ajeCoaProjCd, origRevenueAjeInvAcctDist.ajeCoaProjCd);
                    setValue(crAjeInvAcctDist.ajeCoaExtnCd, origRevenueAjeInvAcctDist.ajeCoaExtnCd);
                }
            }
        } else {
            // Update Credit DR AJE_INV_ACCT_DIST
            for (int i = 0; i < origUnearnedAjeInvAcctDistArray.length(); i++) {
                AJE_INV_ACCT_DISTTMsg origUnearnedAjeInvAcctDist = origUnearnedAjeInvAcctDistArray.no(i);
                if (DR_CD.equals(origUnearnedAjeInvAcctDist.drCrTpCd.getValue())) {
                    BigDecimal ajeInvAcctDistSeqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_SQ);
                    setValue(crAjeInvAcctDist.ajeInvAcctDistPk, ajeInvAcctDistSeqNum);
                    setValue(crAjeInvAcctDist.ajeCoaCmpyCd, origUnearnedAjeInvAcctDist.ajeCoaCmpyCd);
                    setValue(crAjeInvAcctDist.ajeCoaBrCd, origUnearnedAjeInvAcctDist.ajeCoaBrCd);
                    setValue(crAjeInvAcctDist.ajeCoaCcCd, origUnearnedAjeInvAcctDist.ajeCoaCcCd);
                    setValue(crAjeInvAcctDist.ajeCoaAcctCd, origUnearnedAjeInvAcctDist.ajeCoaAcctCd);
                    setValue(crAjeInvAcctDist.ajeCoaProdCd, origUnearnedAjeInvAcctDist.ajeCoaProdCd);
                    setValue(crAjeInvAcctDist.ajeCoaChCd, origUnearnedAjeInvAcctDist.ajeCoaChCd);
                    setValue(crAjeInvAcctDist.ajeCoaAfflCd, origUnearnedAjeInvAcctDist.ajeCoaAfflCd);
                    setValue(crAjeInvAcctDist.ajeCoaProjCd, origUnearnedAjeInvAcctDist.ajeCoaProjCd);
                    setValue(crAjeInvAcctDist.ajeCoaExtnCd, origUnearnedAjeInvAcctDist.ajeCoaExtnCd);
                }
            }
        }
        //START 2021/08/12 D.Morimoto [QC#59035,MOD]
        //START 2018/04/04 E.Kameishi [QC#24870,ADD]
//      if (hasValue(rs.getString(CPO_DTL_LINE_NUM))) {
//          setValue(crAjeInvAcctDist.invBolLineNum, STR_001);
//          setValue(crAjeInvAcctDist.invLineNum, ZYPCommonFunc.leftPad(rs.getString(CPO_DTL_LINE_NUM), STRING_LENGTH_5, "0"));
//      }
        //END 2018/04/04 E.Kameishi [QC#24870,ADD]
        setValue(crAjeInvAcctDist.invBolLineNum, rs.getString(INV_BOL_LINE_NUM));
        setValue(crAjeInvAcctDist.invLineNum, rs.getString(INV_LINE_NUM));
        //END 2021/08/12 D.Morimoto [QC#59035,MOD]
        acctDistList.add(crAjeInvAcctDist);
        submitInsert(acctDistList, false);
    }

    private void setAcctDist(ResultSet rs, AJE_INV_ACCT_DISTTMsgArray origRevEarnAjeInvAcctDistArray, AJE_INV_ACCT_DISTTMsgArray origUnearnedAjeInvAcctDistArray, AJE_INV_ACCT_DISTTMsg crAjeInvAcctDist) throws SQLException{

        // Update Credit CR AJE_INV_ACCT_DIST
        if (CR_CD.equals(crAjeInvAcctDist.drCrTpCd.getValue())) {
            for (int i = 0; i < origRevEarnAjeInvAcctDistArray.length(); i++) {
                AJE_INV_ACCT_DISTTMsg origRevenueAjeInvAcctDist = origRevEarnAjeInvAcctDistArray.no(i);
                if (CR_CD.equals(origRevenueAjeInvAcctDist.drCrTpCd.getValue())) {
                    BigDecimal ajeInvAcctDistSeqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_SQ);
                    setValue(crAjeInvAcctDist.ajeInvAcctDistPk, ajeInvAcctDistSeqNum);
                    setValue(crAjeInvAcctDist.ajeCoaCmpyCd, origRevenueAjeInvAcctDist.ajeCoaCmpyCd);
                    setValue(crAjeInvAcctDist.ajeCoaBrCd, origRevenueAjeInvAcctDist.ajeCoaBrCd);
                    setValue(crAjeInvAcctDist.ajeCoaCcCd, origRevenueAjeInvAcctDist.ajeCoaCcCd);
                    setValue(crAjeInvAcctDist.ajeCoaAcctCd, origRevenueAjeInvAcctDist.ajeCoaAcctCd);
                    setValue(crAjeInvAcctDist.ajeCoaProdCd, origRevenueAjeInvAcctDist.ajeCoaProdCd);
                    setValue(crAjeInvAcctDist.ajeCoaChCd, origRevenueAjeInvAcctDist.ajeCoaChCd);
                    setValue(crAjeInvAcctDist.ajeCoaAfflCd, origRevenueAjeInvAcctDist.ajeCoaAfflCd);
                    setValue(crAjeInvAcctDist.ajeCoaProjCd, origRevenueAjeInvAcctDist.ajeCoaProjCd);
                    setValue(crAjeInvAcctDist.ajeCoaExtnCd, origRevenueAjeInvAcctDist.ajeCoaExtnCd);
                }
            }
        } else {
            // Update Credit DR AJE_INV_ACCT_DIST
            for (int i = 0; i < origUnearnedAjeInvAcctDistArray.length(); i++) {
                AJE_INV_ACCT_DISTTMsg origUnearnedAjeInvAcctDist = origUnearnedAjeInvAcctDistArray.no(i);
                if (DR_CD.equals(origUnearnedAjeInvAcctDist.drCrTpCd.getValue())) {
                    BigDecimal ajeInvAcctDistSeqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_SQ);
                    setValue(crAjeInvAcctDist.ajeInvAcctDistPk, ajeInvAcctDistSeqNum);
                    setValue(crAjeInvAcctDist.ajeCoaCmpyCd, origUnearnedAjeInvAcctDist.ajeCoaCmpyCd);
                    setValue(crAjeInvAcctDist.ajeCoaBrCd, origUnearnedAjeInvAcctDist.ajeCoaBrCd);
                    setValue(crAjeInvAcctDist.ajeCoaCcCd, origUnearnedAjeInvAcctDist.ajeCoaCcCd);
                    setValue(crAjeInvAcctDist.ajeCoaAcctCd, origUnearnedAjeInvAcctDist.ajeCoaAcctCd);
                    setValue(crAjeInvAcctDist.ajeCoaProdCd, origUnearnedAjeInvAcctDist.ajeCoaProdCd);
                    setValue(crAjeInvAcctDist.ajeCoaChCd, origUnearnedAjeInvAcctDist.ajeCoaChCd);
                    setValue(crAjeInvAcctDist.ajeCoaAfflCd, origUnearnedAjeInvAcctDist.ajeCoaAfflCd);
                    setValue(crAjeInvAcctDist.ajeCoaProjCd, origUnearnedAjeInvAcctDist.ajeCoaProjCd);
                    setValue(crAjeInvAcctDist.ajeCoaExtnCd, origUnearnedAjeInvAcctDist.ajeCoaExtnCd);
                }
            }
        }
        //START 2021/08/12 D.Morimoto [QC#59035,MOD]
        //START 2018/04/04 E.Kameishi [QC#24870,ADD]
//      if (hasValue(rs.getString(CPO_DTL_LINE_NUM))) {
//          setValue(crAjeInvAcctDist.invBolLineNum, STR_001);
//          setValue(crAjeInvAcctDist.invLineNum, ZYPCommonFunc.leftPad(rs.getString(CPO_DTL_LINE_NUM), STRING_LENGTH_5, "0"));
//      }
        //END 2018/04/04 E.Kameishi [QC#24870,ADD]
        setValue(crAjeInvAcctDist.invBolLineNum, rs.getString(INV_BOL_LINE_NUM));
        setValue(crAjeInvAcctDist.invLineNum, rs.getString(INV_LINE_NUM));
        //END 2021/08/12 D.Morimoto [QC#59035,MOD]
        acctDistList.add(crAjeInvAcctDist);
        submitInsert(acctDistList, false);
    }

    private void submitInsert(List<AJE_INV_ACCT_DISTTMsg> list, boolean isLast) {

        if (list.size() == 0) {
            return;
        }

        if (list.size() == BULK_INSERT_CNT || isLast) {

            int rtn = S21FastTBLAccessor.insert(list.toArray(new AJE_INV_ACCT_DISTTMsg[list.size()]));

            if (rtn != list.size()) {
                throw new S21AbendException(UNIQUE_ERROR);
            }

            commitCount += rtn;

            // reset
            list.clear();
        }
    }

    @Override
    protected void termRoutine() {
        
        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, errorCount, commitCount + errorCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }

}
