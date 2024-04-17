package com.canon.cusa.s21.batch.NFB.NFBB133001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import business.db.AJE_CM_INTFCTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_CM_INTFC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INTFC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * AP Accrual Write-Off AJE Link File Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/04/05   Hitachi         K.Kim           Create          QC#57935
 *</pre>
 */

public class NFBB133001 extends S21BatchMain implements NFBB133001Constant, ZYPConstant, NFACommonJrnlEntryConstant {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Termination Code */
    private TERM_CD termCd;

    /** Error message */
    private String errMsg = "";

    /** Process Date */
    private static String procDt = ""; // it is not in use for now

    /** Array of TMsg */
    private EZDTMsg[] intfcMsgs;

    /** Array of TMsg */
    private EZDTMsg[] rmvMsgs;

    /** Size of Array */
    private int intfcMsgCount = 0;

    /** Size of Array */
    private int rmvMsgCount = 0;

    /** Journal Entry Common Module */
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

    /** Total Commit Count */
    private int totalCommitCount = 0;

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        /* Initialize S21BatchMain */
        new NFBB133001().executeBatch(NFBB133001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        procDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);

        initVariables();

        S21InfoLogOutput.println("initRoutine Method End");
    }

    private void initVariables() {
        intfcMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];

        intfcMsgCount = 0;
        rmvMsgCount = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        List<Map> map = getAjeCmIntfcListNotJrnlized();

        if (map.size() > 0) {
            if (!removeNotJrnlized(map)) {
                // error
                rollback();
                throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg });
            }
        }

        if (!doEntryToAjeCmIntfc()) {
            // error
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        }

        commit();
        S21InfoLogOutput.println("Insert record count : " + Integer.toString(totalCommitCount));
        S21InfoLogOutput.println("mainRoutine Method End");
    }

    /**
     * <pre>
     *  Get AJE Interface Key List that is not journalized from AJE Interface Control.
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private List<Map> getAjeCmIntfcListNotJrnlized() {

        // ** Get All Transaction Pattern in AJE Cost Management
        // Interface. **
        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("ajeIntfcTpCd", AJE_INTFC_TP.COST_MANAGEMENT);
        map.put("jrnlCpltFlg", FLG_ON_Y);

        List<Map> listNotJrnlized = (List<Map>) ssmBatchClient.queryObjectList("getAjeCmIntfcListNotJrnlized", map);

        return listNotJrnlized;
    }

    /**
     * <pre>
     *  Remove AJE Cost Management Interface that is not journalized.
     * </pre>
     * @param List<Map> AJE Interface Key List that is not
     * journalized.
     * @return true: OK false: NG
     */
    @SuppressWarnings("unchecked")
    private boolean removeNotJrnlized(List<Map> listNotJrnlized) {
        try {
            for (Map<String, Object> mapNotJorlized : listNotJrnlized) {

                AJE_CM_INTFCTMsg tMsg = new AJE_CM_INTFCTMsg();
                String glblCmpyCd = (String) mapNotJorlized.get(GLBL_CMPY_CD);
                BigDecimal ajeCmIntfcPk = new BigDecimal(mapNotJorlized.get(AJE_CM_INTFC_PK).toString());

                commonJrnlEntry.setFieldValue(tMsg, "glblCmpyCd", glblCmpyCd);
                commonJrnlEntry.setFieldValue(tMsg, "ajeCmIntfcPk", ajeCmIntfcPk);

                removeAjeIntfcNotJrnlizedHelper(tMsg);
            }

            if (rmvMsgCount != 0) {
                removeAjeIntfcNotJrnlizedHelper(null);
            }

        } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
            errMsg = ex.getMessage();
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * <pre>
     *  Remove AJE Interface Control per a certain amount of records.
     *  @param rmvRec TMsg to be removed
     *  @throws JrnlCommonException JrnlCommonException
     * </pre>
     */
    private void removeAjeIntfcNotJrnlizedHelper(EZDTMsg rmvRec) throws NFACommonJrnlEntry.JrnlCommonException {

        if (rmvRec != null) {
            rmvMsgs[rmvMsgCount] = rmvRec;
            rmvMsgCount += 1;
        } else {
            rmvMsgs = commonJrnlEntry.changeArraySize(rmvMsgs, rmvMsgCount);
        }

        // per 1000 lines
        if (rmvMsgCount >= BULK_INSERT_COUNT || rmvRec == null) {

            int retCnt = S21FastTBLAccessor.removePhysical(rmvMsgs);

            // if passed records' count and return count don't match,
            // error
            if (retCnt != rmvMsgCount) {
                throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
            }
            // initialize
            rmvMsgCount = 0;
            rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        }
    }

    /**
     * <pre>
     *  Store Target Transactions in AJE Cost Management Interface.
     * </pre>
     */
    private boolean doEntryToAjeCmIntfc() {

        // ** Get Target Transactions in CM_ACRL_WRITE_OFF. **
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("ajeCmIntfcTpCd", AJE_CM_INTFC_TP.NOT_JOURNALIZED);
        map.put("ajeIntfcTpCd", AJE_INTFC_TP.COST_MANAGEMENT);
        map.put("jrnlCpltFlg", FLG_ON_Y);

        Boolean success = (Boolean) ssmBatchClient.queryObject("getAcrlWriteOffTrxNotJrnlized", map, new AjeCmIntfcHandler());

        return success;
    }

    /**
     * <pre>
     *  AJE Cost Management Interfaces.
     *  Store Target Transactions in AJE Cost Management Interface.
     * </pre>
     */
    private class AjeCmIntfcHandler extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                while (rs.next()) {

                    BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_CM_INTFC_SQ);

                    AJE_CM_INTFCTMsg tMsg = new AJE_CM_INTFCTMsg();

                    commonJrnlEntry.setFieldValue(tMsg, "glblCmpyCd", rs.getString(GLBL_CMPY_CD));
                    commonJrnlEntry.setFieldValue(tMsg, "ajeCmIntfcPk", seqNum);
                    commonJrnlEntry.setFieldValue(tMsg, "glDt", rs.getString(WRT_OFF_DT));
                    commonJrnlEntry.setFieldValue(tMsg, "cmAcrlWriteOffPk", rs.getBigDecimal(CM_ACRL_WRITE_OFF_PK));
                    commonJrnlEntry.setFieldValue(tMsg, "sysSrcCd", SYS_SRC.S21_ACCOUNTING_COST);
                    commonJrnlEntry.setFieldValue(tMsg, "trxCd", TRX.COST_CALCULATION);
                    if (ZYPConstant.FLG_ON_Y.equals(rs.getString(WRT_OFF_FLG))) {
                        commonJrnlEntry.setFieldValue(tMsg, "trxRsnCd", TRX_RSN.ACCRUAL_WRITE_OFF);
                    } else {
                        commonJrnlEntry.setFieldValue(tMsg, "trxRsnCd", TRX_RSN.ACCRUAL_WRITE_OFF_RVRSL);
                    }
                    commonJrnlEntry.setFieldValue(tMsg, "drCrTpCd", rs.getString(DR_CR_TP_CD));
                    commonJrnlEntry.setFieldValue(tMsg, "vndCd", rs.getString(VND_CD));
                    commonJrnlEntry.setFieldValue(tMsg, "poNum", rs.getString(PO_NUM));
                    commonJrnlEntry.setFieldValue(tMsg, "poOrdDtlLineNum", rs.getString(PO_ORD_DTL_LINE_NUM));
                    commonJrnlEntry.setFieldValue(tMsg, "mdseCd", rs.getString(MDSE_CD));
                    commonJrnlEntry.setFieldValue(tMsg, "apVndInvNum", rs.getString(AP_VND_INV_NUM));
                    commonJrnlEntry.setFieldValue(tMsg, "apVndInvSqNum", rs.getString(AP_VND_INV_SQ_NUM));
                    commonJrnlEntry.setFieldValue(tMsg, "apVndInvLineNum", rs.getString(AP_VND_INV_LINE_NUM));
                    commonJrnlEntry.setFieldValue(tMsg, "writeOffQty", rs.getBigDecimal(WRITE_OFF_QTY));
                    commonJrnlEntry.setFieldValue(tMsg, "cmAjeTotCostAmt", rs.getBigDecimal(CM_AJE_TOT_COST_AMT));

                    createIntfc(tMsg);
                }

                if (intfcMsgCount != 0) {
                    createIntfc(null);
                }

            } catch (EZDAbendException exEz) { // null exception
                errMsg = exEz.getMessage();
                return Boolean.FALSE;
            } catch (S21AbendException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException sqlEx) {
                errMsg = sqlEx.getMessage();
                return Boolean.FALSE;
            } catch (NFACommonJrnlEntry.JrnlCommonException exJ) {
                errMsg = exJ.getMessage();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }

    }

    /**
     * @param EZDTMsg AJE Cost Management Interface.
     * @throws JrnlCommonException JrnlCommonException
     */
    protected void createIntfc(EZDTMsg tMsgInvty) throws NFACommonJrnlEntry.JrnlCommonException {

        if (tMsgInvty != null) {
            intfcMsgs[intfcMsgCount] = tMsgInvty;
            intfcMsgCount += 1;

        } else { // array may be not full
            intfcMsgs = commonJrnlEntry.changeArraySize(intfcMsgs, intfcMsgCount);
        }

        // per 1000 lines
        if (intfcMsgCount >= BULK_INSERT_COUNT || tMsgInvty == null) {

            int retCnt = S21FastTBLAccessor.insert(intfcMsgs);

            // if passed records' count and return count don't match,
            // error
            if (retCnt != intfcMsgCount) {
                throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
            }
            totalCommitCount += intfcMsgCount;
            // initialize
            intfcMsgCount = 0;
            intfcMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        }
    }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, totalCommitCount, 0, totalCommitCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }
}
