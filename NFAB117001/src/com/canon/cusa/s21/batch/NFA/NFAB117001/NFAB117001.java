/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB117001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDTMsg;

import business.db.JRNL_ENTRYTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Update Journal Entry Batch.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/06/2017   Hitachi         Y.Takeno        Create          QC#20644
 * </pre>
 */

public class NFAB117001 extends S21BatchMain implements NFAB117001Constant, NFACommonJrnlEntryConstant {

    /** SSM Batch Clinent */
    private S21SsmBatchClient ssmBatchClient;

    /** User Profile */
    private S21UserProfileService profile;

    /** Commit Count */
    private int commitCount = 0;

    /** Termination Code */
    private TERM_CD termCd;

    /** Error message */
    private String errMsg = "";

    /** Array of TMsg */
    private EZDTMsg[] jrnlMsgs;

    /** Array of TMsg */
    private EZDTMsg[] rmvMsgs;

    /** Size of Array */
    private int jrnlMsgCount = 0;

    /** Size of Array */
    private int rmvMsgCount = 0;

    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

    @Override
	protected void initRoutine() {

        this.termCd = TERM_CD.NORMAL_END;

        this.profile = S21UserProfileServiceFactory.getInstance().getService();

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        
        jrnlMsgs = new EZDTMsg[BULK_INSERT_COUNT];

        rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];

	}

	@Override
	protected void mainRoutine() {
	    
	    boolean result = doUpdateJrnlEntry();
	    if(result) {
	        commit();
	    } else {
	        rollback();
            throw new S21AbendException(NFAM0035E, new String[] {errMsg});
	    }
	}

	@Override
	protected void termRoutine() {

		setTermState(this.termCd, commitCount, 0, commitCount);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new NFAB117001().executeBatch(NFAB117001.class.getSimpleName());
	}

    @SuppressWarnings("unchecked")
    private Boolean doUpdateJrnlEntry() {

        Map<String, String>queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.profile.getGlobalCompanyCode());
        queryParam.put("glSendCpltFlg", ZYPConstant.FLG_OFF_N);

        Boolean result = (Boolean) ssmBatchClient.queryObject("getJrnlEntry", queryParam, new UpdateJrnlEntry());

        return result;
    }

    private final class UpdateJrnlEntry extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rsJrnlEntry) throws SQLException {
            
            try {
                while (rsJrnlEntry.next()) {
                    BigDecimal newSeqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(JRNL_ENTRY_SQ);
                    
                    JRNL_ENTRYTMsg jrnlEntry = new JRNL_ENTRYTMsg();
                    
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.glblCmpyCd, rsJrnlEntry.getString("GLBL_CMPY_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryPk, newSeqNum);
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeIntfcTpCd, rsJrnlEntry.getString("AJE_INTFC_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeIntfcPk, rsJrnlEntry.getBigDecimal("AJE_INTFC_PK"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.procDt, rsJrnlEntry.getString("PROC_DT"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.glDt, rsJrnlEntry.getString("GL_DT"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.glSendCpltFlg, rsJrnlEntry.getString("GL_SEND_CPLT_FLG"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeId, rsJrnlEntry.getString("AJE_ID"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.sysSrcCd, rsJrnlEntry.getString("SYS_SRC_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.sysSrcNm, rsJrnlEntry.getString("SYS_SRC_NM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.trxCd, rsJrnlEntry.getString("TRX_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.trxNm, rsJrnlEntry.getString("TRX_NM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.trxRsnCd, rsJrnlEntry.getString("TRX_RSN_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.trxRsnNm, rsJrnlEntry.getString("TRX_RSN_NM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnIndTpCd_01, rsJrnlEntry.getString("AJE_PTRN_IND_TP_CD_01"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnIndTpNm_01, rsJrnlEntry.getString("AJE_PTRN_IND_TP_NM_01"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnActlCd_01, rsJrnlEntry.getString("AJE_PTRN_ACTL_CD_01"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnActlNm_01, rsJrnlEntry.getString("AJE_PTRN_ACTL_NM_01"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnIndTpCd_02, rsJrnlEntry.getString("AJE_PTRN_IND_TP_CD_02"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnIndTpNm_02, rsJrnlEntry.getString("AJE_PTRN_IND_TP_NM_02"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnActlCd_02, rsJrnlEntry.getString("AJE_PTRN_ACTL_CD_02"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnActlNm_02, rsJrnlEntry.getString("AJE_PTRN_ACTL_NM_02"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnIndTpCd_03, rsJrnlEntry.getString("AJE_PTRN_IND_TP_CD_03"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnIndTpNm_03, rsJrnlEntry.getString("AJE_PTRN_IND_TP_NM_03"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnActlCd_03, rsJrnlEntry.getString("AJE_PTRN_ACTL_CD_03"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnActlNm_03, rsJrnlEntry.getString("AJE_PTRN_ACTL_NM_03"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlSrcCd, rsJrnlEntry.getString("JRNL_SRC_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlSrcNm, rsJrnlEntry.getString("JRNL_SRC_NM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlCatgCd, rsJrnlEntry.getString("JRNL_CATG_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlCatgNm, rsJrnlEntry.getString("JRNL_CATG_NM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeLineIdxNum, rsJrnlEntry.getString("AJE_LINE_IDX_NUM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeItemCd, rsJrnlEntry.getString("AJE_ITEM_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeItemDescTxt, rsJrnlEntry.getString("AJE_ITEM_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.crAjeLineIdxDescTxt, rsJrnlEntry.getString("DR_AJE_LINE_IDX_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaCmpyCd, rsJrnlEntry.getString("DR_COA_CMPY_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaBrCd, rsJrnlEntry.getString("DR_COA_BR_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaCcCd, rsJrnlEntry.getString("DR_COA_CC_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaAcctCd, rsJrnlEntry.getString("DR_COA_ACCT_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaProdCd, rsJrnlEntry.getString("DR_COA_PROD_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaChCd, rsJrnlEntry.getString("DR_COA_CH_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaAfflCd, rsJrnlEntry.getString("DR_COA_AFFL_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaProjCd, rsJrnlEntry.getString("DR_COA_PROJ_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaExtnCd, rsJrnlEntry.getString("DR_COA_EXTN_CD"));

                    ZYPEZDItemValueSetter.setValue(jrnlEntry.drAjeLineIdxDescTxt, rsJrnlEntry.getString("CR_AJE_LINE_IDX_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaCmpyCd, rsJrnlEntry.getString("CR_COA_CMPY_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaBrCd, rsJrnlEntry.getString("CR_COA_BR_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaCcCd, rsJrnlEntry.getString("CR_COA_CC_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaAcctCd, rsJrnlEntry.getString("CR_COA_ACCT_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaProdCd, rsJrnlEntry.getString("CR_COA_PROD_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaChCd, rsJrnlEntry.getString("CR_COA_CH_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaAfflCd, rsJrnlEntry.getString("CR_COA_AFFL_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaProjCd, rsJrnlEntry.getString("CR_COA_PROJ_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaExtnCd, rsJrnlEntry.getString("CR_COA_EXTN_CD"));

                    if(rsJrnlEntry.getBigDecimal("JRNL_QTY").compareTo(BigDecimal.ZERO) < 0){
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlQty, rsJrnlEntry.getBigDecimal("JRNL_QTY").negate());
                    }else{
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlQty, rsJrnlEntry.getBigDecimal("JRNL_QTY"));
                    }

                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlDealDrAmt, rsJrnlEntry.getBigDecimal("JRNL_DEAL_DR_AMT").negate());
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlDealCrAmt, rsJrnlEntry.getBigDecimal("JRNL_DEAL_CR_AMT").negate());
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlFuncDrAmt, rsJrnlEntry.getBigDecimal("JRNL_FUNC_DR_AMT").negate());
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlFuncCrAmt, rsJrnlEntry.getBigDecimal("JRNL_FUNC_CR_AMT").negate());

                    ZYPEZDItemValueSetter.setValue(jrnlEntry.dealCcyCd, rsJrnlEntry.getString("DEAL_CCY_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.funcCcyCd, rsJrnlEntry.getString("FUNC_CCY_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.orclCcyCd, rsJrnlEntry.getString("ORCL_CCY_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.exchRate, rsJrnlEntry.getBigDecimal("EXCH_RATE"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.acctArthTpCd, rsJrnlEntry.getString("ACCT_ARTH_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.coaProdCd, rsJrnlEntry.getString("COA_PROD_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.tocCd, rsJrnlEntry.getString("TOC_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.billToCustCd, rsJrnlEntry.getString("BILL_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.shipToCustCd, rsJrnlEntry.getString("SHIP_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.sellToCustCd, rsJrnlEntry.getString("SELL_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.vndCd, rsJrnlEntry.getString("VND_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeInvNum, rsJrnlEntry.getString("AJE_INV_NUM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.prmoPk, rsJrnlEntry.getBigDecimal("PRMO_PK"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.rcptChkNum, rsJrnlEntry.getString("RCPT_CHK_NUM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.cpoOrdNum, rsJrnlEntry.getString("CPO_ORD_NUM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.soNum, rsJrnlEntry.getString("SO_NUM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.orclRef10Txt, rsJrnlEntry.getString("ORCL_REF_10_TXT"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.orclAttrb11Txt, rsJrnlEntry.getString("ORCL_ATTRB_11_TXT"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeIntfcCmntTxt, rsJrnlEntry.getString("AJE_INTFC_CMNT_TXT"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.machConfigNum, rsJrnlEntry.getString("MACH_CONFIG_NUM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.dsOrdTpCd, rsJrnlEntry.getString("DS_ORD_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.dsOrdRsnCd, rsJrnlEntry.getString("DS_ORD_RSN_CD"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.rcptNum, rsJrnlEntry.getString("RCPT_NUM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.dsAcctNum, rsJrnlEntry.getString("DS_ACCT_NUM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.invtyTrxPk, rsJrnlEntry.getBigDecimal("INVTY_TRX_PK"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.arTrxNum, rsJrnlEntry.getString("AR_TRX_NUM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.serNum, rsJrnlEntry.getString("SER_NUM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.svcMachMstrPk, rsJrnlEntry.getBigDecimal("SVC_MACH_MSTR_PK"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.dsContrPk, rsJrnlEntry.getBigDecimal("DS_CONTR_PK"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.dsContrDtlPk, rsJrnlEntry.getBigDecimal("DS_CONTR_DTL_PK"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryFirstRefTxt, rsJrnlEntry.getString("JRNL_ENTRY_FIRST_REF_TXT"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryScdRefTxt, rsJrnlEntry.getString("JRNL_ENTRY_SCD_REF_TXT"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryThirdRefTxt, rsJrnlEntry.getString("JRNL_ENTRY_THIRD_REF_TXT"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryFrthRefTxt, rsJrnlEntry.getString("JRNL_ENTRY_FRTH_REF_TXT"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryFifthRefTxt, rsJrnlEntry.getString("JRNL_ENTRY_FIFTH_REF_TXT"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryFirstAttrbNm, rsJrnlEntry.getString("JRNL_ENTRY_FIRST_ATTRB_NM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryScdAttrbNm, rsJrnlEntry.getString("JRNL_ENTRY_SCD_ATTRB_NM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryThirdAttrbNm, rsJrnlEntry.getString("JRNL_ENTRY_THIRD_ATTRB_NM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryFrthAttrbNm, rsJrnlEntry.getString("JRNL_ENTRY_FRTH_ATTRB_NM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryFifthAttrbNm, rsJrnlEntry.getString("JRNL_ENTRY_FIFTH_ATTRB_NM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.svcConfigMstrPk, rsJrnlEntry.getBigDecimal("SVC_CONFIG_MSTR_PK"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryRefTxt, rsJrnlEntry.getString("JRNL_ENTRY_REF_TXT"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.manJrnlEntryHdrPk, rsJrnlEntry.getBigDecimal("MAN_JRNL_ENTRY_HDR_PK"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.manJrnlEntryDtlPk, rsJrnlEntry.getBigDecimal("MAN_JRNL_ENTRY_DTL_PK"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.poOrdNum, rsJrnlEntry.getString("PO_ORD_NUM"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.prntDsAssetMstrPk, rsJrnlEntry.getBigDecimal("PRNT_DS_ASSET_MSTR_PK"));
                    ZYPEZDItemValueSetter.setValue(jrnlEntry.glIfCratRqstCd, rsJrnlEntry.getString("GL_IF_CRAT_RQST_CD"));

                    createJrnlEntry(jrnlEntry);
                    
                    JRNL_ENTRYTMsg rmvJrnlEntry = new JRNL_ENTRYTMsg();
                    ZYPEZDItemValueSetter.setValue(rmvJrnlEntry.glblCmpyCd, rsJrnlEntry.getString("GLBL_CMPY_CD"));
                    ZYPEZDItemValueSetter.setValue(rmvJrnlEntry.jrnlEntryPk, rsJrnlEntry.getBigDecimal("JRNL_ENTRY_PK"));

                    removeJrnlEntry(rmvJrnlEntry);

                }
                
                if (jrnlMsgCount != 0) {
                    createJrnlEntry(null);
                }

                if (rmvMsgCount != 0) {
                    removeJrnlEntry(null);
                }

            } catch (SQLException exSql) {
                errMsg = exSql.getMessage();
                return Boolean.FALSE;
            } catch (Exception exc) {  // to catch any other error
                errMsg = exc.getMessage();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }

        private void createJrnlEntry(EZDTMsg jrnlEntry) throws NFACommonJrnlEntry.JrnlCommonException {
            if (jrnlEntry != null) {
                jrnlMsgs[jrnlMsgCount] = jrnlEntry;
                jrnlMsgCount += 1;

            } else {  // array may be not full
                jrnlMsgs = commonJrnlEntry.changeArraySize(jrnlMsgs, jrnlMsgCount);
            }

            // per 10000 lines
            if (jrnlMsgCount >= BULK_INSERT_COUNT || jrnlEntry == null) {

                int retCnt = S21FastTBLAccessor.insert(jrnlMsgs);

                // if passed records' count and return count don't match, error
                if (retCnt != jrnlMsgCount) {
                    throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
                }
                commitCount += jrnlMsgCount;
                // initialize
                jrnlMsgCount = 0;
                jrnlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
            }
            
        }

        
        private void removeJrnlEntry(EZDTMsg rmvRec) throws NFACommonJrnlEntry.JrnlCommonException {

            if (rmvRec != null) {
                rmvMsgs[rmvMsgCount] = rmvRec;
                rmvMsgCount += 1;
            } else {
                rmvMsgs = commonJrnlEntry.changeArraySize(rmvMsgs, rmvMsgCount);
            }

            // per 10000 lines
            if (rmvMsgCount >= BULK_INSERT_COUNT || rmvRec == null) {

                int retCnt = S21FastTBLAccessor.removeLogical(rmvMsgs);

                // if passed records' count and return count don't match, error
                if (retCnt != rmvMsgCount) {
                    throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
                }
                // initialize
                rmvMsgCount = 0;
                rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];
            }
        }

    }

}
