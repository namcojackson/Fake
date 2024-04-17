package com.canon.cusa.s21.batch.NFA.NFAB119001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.db.AJE_INVTY_INTFCTMsg;
import business.db.AJE_INVTY_INTFCTMsgArray;
import business.db.AJE_INV_ACCT_DISTTMsg;
import business.db.AJE_INV_ACCT_DISTTMsgArray;
import business.parts.NFACommonJrnlEntry;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_RSN;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchMain.TERM_CD;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Credit Memo Journalize For Inventory
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 02/19/2018   Hitachi         E.Kameishi      Create          N/A
 * 05/25/2018   Hitachi         E.Kameishi      Update          QC#26098
 * 05/13/2019   Hitachi         E.Kameishi      Update          QC#50173
 *</pre>
 */
public class NFAB119001 extends S21BatchMain implements ZYPConstant, NFAB119001Constant{

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

    /**  Journal Entry Common Module */
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        new NFAB119001().executeBatch(NFAB119001.class.getSimpleName());

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
            ps = getOrigJrnlEntry();
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

    private void mainProcess(ResultSet rs) throws SQLException{

        // update Credit Journal Entry
        int cnt = 0;
        BigDecimal invtyTrxPk;
        // START 2018/05/25 E.Kameishi [QC#26098,ADD]
        String tocCd;
        // END 2018/05/25 E.Kameishi [QC#26098,ADD]
        EZDTMsg tmsgs[] = new EZDTMsg[BULK_INSERT_CNT];
        // START 2019/05/13 E.Kameishi [QC#50173,ADD]
        EZDTMsg rmvTmsgs[] = new EZDTMsg[BULK_INSERT_CNT];
        // END 2019/05/13 E.Kameishi [QC#50173,ADD]
        while (rs.next()) {
            invtyTrxPk = rs.getBigDecimal(INVTY_TRX_PK);
            // START 2018/05/25 E.Kameishi [QC#26098,MOD]
            tocCd = rs.getString(TOC_CD);
            
            AJE_INVTY_INTFCTMsg tmsg = new AJE_INVTY_INTFCTMsg();
            tmsg.setSQLID("002"); 
            tmsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            tmsg.setConditionValue("invtyTrxPk01", invtyTrxPk);
            tmsg.setConditionValue("tocCd01", tocCd);

            AJE_INVTY_INTFCTMsgArray ajeInvtyIntfcArray = (AJE_INVTY_INTFCTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(tmsg);
            // END 2018/05/25 E.Kameishi [QC#26098,MOD]

            if (ajeInvtyIntfcArray.length() > 0) {
                for (int i = 0; i < ajeInvtyIntfcArray.length(); i++) {
                    // START 2019/05/13 E.Kameishi [QC#50173,MOD]
                    BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INVTY_INTFC_SQ);
                    AJE_INVTY_INTFCTMsg ajeInvtyInfc = ajeInvtyIntfcArray.no(i);
                    AJE_INVTY_INTFCTMsg createAjeInvtyInfc = new AJE_INVTY_INTFCTMsg();
                    EZDMsg.copy(ajeInvtyInfc, null, createAjeInvtyInfc, null);
                    setValue(createAjeInvtyInfc.ajeInvtyIntfcPk, seqNum);
                    setValue(createAjeInvtyInfc.trxRsnCd, TRX_RSN.CREDIT);
                    setValue(createAjeInvtyInfc.coaAcctCd, rs.getString(DR_COA_ACCT_CD));
                    setValue(createAjeInvtyInfc.coaProdCd, rs.getString(DR_COA_PROD_CD));
                    setValue(createAjeInvtyInfc.expProjCd, rs.getString(DR_COA_PROJ_CD));
                    setValue(createAjeInvtyInfc.coaBrCd, rs.getString(DR_COA_BR_CD));
                    setValue(createAjeInvtyInfc.coaCcCd, rs.getString(DR_COA_CC_CD));
                    setValue(createAjeInvtyInfc.coaChCd, rs.getString(DR_COA_CH_CD));
                    setValue(createAjeInvtyInfc.coaAfflCd, rs.getString(DR_COA_AFFL_CD));
                    setValue(createAjeInvtyInfc.coaExtnCd, rs.getString(DR_COA_EXTN_CD));

                    setValue(createAjeInvtyInfc.drInvtyOrdAcctCd, rs.getString(CR_COA_ACCT_CD));
                    setValue(createAjeInvtyInfc.drInvtyOrdProdCd, rs.getString(CR_COA_PROD_CD));
                    setValue(createAjeInvtyInfc.drInvtyOrdProjCd, rs.getString(CR_COA_PROJ_CD));
                    setValue(createAjeInvtyInfc.drInvtyOrdBrCd, rs.getString(CR_COA_BR_CD));
                    setValue(createAjeInvtyInfc.drInvtyOrdCcCd, rs.getString(CR_COA_CC_CD));
                    setValue(createAjeInvtyInfc.drInvtyOrdChCd, rs.getString(CR_COA_CH_CD));
                    setValue(createAjeInvtyInfc.drInvtyOrdAfflCd, rs.getString(CR_COA_AFFL_CD));
                    setValue(createAjeInvtyInfc.drInvtyOrdExtnCd, rs.getString(CR_COA_EXTN_CD));

                    tmsgs[cnt] = createAjeInvtyInfc;
                    rmvTmsgs[cnt] = ajeInvtyInfc;
                    // END 2019/05/13 E.Kameishi [QC#50173,MOD]
                    cnt = cnt + 1;

                    if (cnt >= BULK_INSERT_CNT) {
                        // bulk update
                        // START 2019/05/13 E.Kameishi [QC#50173,MOD]
                        S21FastTBLAccessor.insert(tmsgs);
                        S21FastTBLAccessor.removeLogical(rmvTmsgs);

                        commitCount += cnt;
                        // initialize
                        tmsgs = new AJE_INVTY_INTFCTMsg[BULK_INSERT_CNT];
                        rmvTmsgs = new AJE_INVTY_INTFCTMsg[BULK_INSERT_CNT];
                        // END 2019/05/13 E.Kameishi [QC#50173,MOD]
                        cnt = 0;
                    }
                }
            }
        }

        // update remaining data
        if (cnt != 0) {
            commitCount += cnt;
            tmsgs = commonJrnlEntry.changeArraySize(tmsgs, cnt);
            // START 2019/05/13 E.Kameishi [QC#50173,MOD]
            rmvTmsgs = commonJrnlEntry.changeArraySize(rmvTmsgs, cnt);
            S21FastTBLAccessor.insert(tmsgs);
            S21FastTBLAccessor.removeLogical(rmvTmsgs);
            // END 2019/05/13 E.Kameishi [QC#50173,MOD]
        }
    }

    private PreparedStatement getOrigJrnlEntry() throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invTpCdCm", INV_TP.CREDIT_MEMO);
        queryParam.put("batProcDt", this.batProcDt);

        return this.ssmLLClient.createPreparedStatement("getOrigJrnlEntry", queryParam);
    }

    @Override
    protected void termRoutine() {
        
        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, errorCount, commitCount + errorCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }
}
