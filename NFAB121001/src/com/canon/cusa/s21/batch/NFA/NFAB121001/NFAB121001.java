package com.canon.cusa.s21.batch.NFA.NFAB121001;

import static com.canon.cusa.s21.batch.NFA.NFAB121001.NFAB121001Constant.AJE_COA_CC_CHANGE_BY_PROD;
import static com.canon.cusa.s21.batch.NFA.NFAB121001.NFAB121001Constant.BULK_INSERT_COUNT;
import static com.canon.cusa.s21.batch.NFA.NFAB121001.NFAB121001Constant.CR;
import static com.canon.cusa.s21.batch.NFA.NFAB121001.NFAB121001Constant.DR;
import static com.canon.cusa.s21.batch.NFA.NFAB121001.NFAB121001Constant.GRP_ID_PRT_ORD_LOB;
import static com.canon.cusa.s21.batch.NFA.NFAB121001.NFAB121001Constant.ZZZM9025E;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AJE_INV_ACCT_DISTTMsg;
import business.db.JRNL_ENTRYTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INTFC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INV_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Change Journal Entry Batch.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/12/2019   Fujitsu         T.Murai         Create          QC#52589
 * </pre>
 */

public class NFAB121001 extends S21BatchMain implements NFACommonJrnlEntryConstant {

    /** SSM Batch Clinent */
    private S21SsmBatchClient ssmBatchClient;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Count */
    private int commitJrnlCount = 0;

    /** Termination Code */
    private TERM_CD termCd;

    /** Error message */
    private String errMsg = "";

    /** Array of TMsg */
    private String[] changeTargetCcList;

    /** Map<ProdCd, CcCd> */
    private Map<String, String> prodCcMap;

    /** Array of TMsg */
    private EZDTMsg[] jrnlMsgs;

    /** Array of TMsg */
    private EZDTMsg[] acctDistMsgs;

    /** Size of Array */
    private int jrnlMsgCount = 0;

    /** Size of Array */
    private int acctDistMsgsCount = 0;

    /** NFACommonJrnlEntry */
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

    @Override
    protected void initRoutine() {

        this.termCd = TERM_CD.NORMAL_END;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        jrnlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        acctDistMsgs = new EZDTMsg[BULK_INSERT_COUNT];
    }

    @Override
    protected void mainRoutine() {

        String ajeCoaCcChangeList = ZYPCodeDataUtil.getVarCharConstValue(AJE_COA_CC_CHANGE_BY_PROD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(ajeCoaCcChangeList)) {
            return;
        }
        this.changeTargetCcList = ajeCoaCcChangeList.split(",");

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rptCondConstGrpId", GRP_ID_PRT_ORD_LOB);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPordCcMap", ssmParam);

        this.prodCcMap = convertMap(resultList);

        if (prodCcMap.isEmpty()) {
            return;
        }

        boolean result = doUpdateJrnlEntry();
        if (result) {
            commit();
        } else {
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {errMsg });
        }
    }

    @Override
    protected void termRoutine() {

        setTermState(this.termCd, commitJrnlCount, 0, commitJrnlCount);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        new NFAB121001().executeBatch(NFAB121001.class.getSimpleName());
    }

    @SuppressWarnings("unchecked")
    private Boolean doUpdateJrnlEntry() {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("glSendCpltFlgN", ZYPConstant.FLG_OFF_N);
        queryParam.put("changeTargetCcList", changeTargetCcList);

        Boolean result = (Boolean) ssmBatchClient.queryObject("getJrnlEntry", queryParam, new UpdateJrnlEntry());

        return result;
    }

    private final class UpdateJrnlEntry extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rsJrnlEntry) throws SQLException {

            try {
                while (rsJrnlEntry.next()) {

                    BigDecimal jrnlEntrypk = rsJrnlEntry.getBigDecimal("JRNL_ENTRY_PK");

                    JRNL_ENTRYTMsg inMsg = new JRNL_ENTRYTMsg();
                    ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(inMsg.jrnlEntryPk, jrnlEntrypk);
                    JRNL_ENTRYTMsg updMsg = (JRNL_ENTRYTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);

                    String drCoaCcCd = updMsg.drCoaCcCd.getValue();
                    String crCoaCcCd = updMsg.crCoaCcCd.getValue();

                    if (contain(changeTargetCcList, drCoaCcCd)) {
                        updateJrnlEntry(updMsg, DR);
                    }
                    if (contain(changeTargetCcList, crCoaCcCd)) {
                        updateJrnlEntry(updMsg, CR);
                    }
                }

                if (jrnlMsgCount != 0) {
                    submitUpdateJrnlEntry(null);
                }

                if (acctDistMsgsCount != 0) {
                    submitUpdateAjeInvAcctDist(null);
                }

            } catch (SQLException exSql) {
                errMsg = exSql.getMessage();
                return Boolean.FALSE;
            } catch (Exception exc) { // to catch any other error
                errMsg = exc.getMessage();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }

        private void updateJrnlEntry(JRNL_ENTRYTMsg updMsg, String crDrTp) throws JrnlCommonException {

            String updateCcCd = null;

            if ("CR".equals(crDrTp)) {
                updateCcCd = prodCcMap.get(updMsg.crCoaProdCd.getValue());
            } else if ("DR".equals(crDrTp)) {
                updateCcCd = prodCcMap.get(updMsg.drCoaProdCd.getValue());
            }

            if (!ZYPCommonFunc.hasValue(updateCcCd)) {
                return;
            }

            if ("CR".equals(crDrTp)) {
                ZYPEZDItemValueSetter.setValue(updMsg.crCoaCcCd, updateCcCd);
            } else if ("DR".equals(crDrTp)) {
                ZYPEZDItemValueSetter.setValue(updMsg.drCoaCcCd, updateCcCd);
            }

            submitUpdateJrnlEntry(updMsg);

            if (AJE_INTFC_TP.OM.equals(updMsg.ajeIntfcTpCd.getValue())) {
                updateAjeInvAcctDist(updMsg, crDrTp, updateCcCd);
            }
        }

        private void updateAjeInvAcctDist(JRNL_ENTRYTMsg jrnlEntryMsg, String crDrTp, String updateCcCd) throws JrnlCommonException {

            Map<String, Object> resultMap = null;

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("ajeIntfcPk", jrnlEntryMsg.ajeIntfcPk.getValue());
            ssmParam.put("ajeInvAcctClsCd88", AJE_INV_ACCT_CLS.REVENUE_EARNED);

            String statementId = null;
            if ("CR".equals(crDrTp)) {
                statementId = "getAjeInvAcctDistPkCr";
                ssmParam.put("crDrTpC", "C");

            } else if ("DR".equals(crDrTp)) {
                statementId = "getAjeInvAcctDistPkDr";
            }
            resultMap = (Map<String, Object>) ssmBatchClient.queryObject(statementId, ssmParam);

            if (resultMap != null) {

                BigDecimal ajeInvAcctDistPk = (BigDecimal) resultMap.get("AJE_INV_ACCT_DIST_PK");

                AJE_INV_ACCT_DISTTMsg inMsg = new AJE_INV_ACCT_DISTTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inMsg.ajeInvAcctDistPk, ajeInvAcctDistPk);
                AJE_INV_ACCT_DISTTMsg updMsg = (AJE_INV_ACCT_DISTTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);

                ZYPEZDItemValueSetter.setValue(updMsg.ajeCoaCcCd, updateCcCd);

                submitUpdateAjeInvAcctDist(updMsg);
            }
        }

        private void submitUpdateJrnlEntry(JRNL_ENTRYTMsg updMsg) throws JrnlCommonException {

            if (updMsg != null) {
                jrnlMsgs[jrnlMsgCount] = updMsg;
                jrnlMsgCount += 1;
            } else {
                jrnlMsgs = commonJrnlEntry.changeArraySize(jrnlMsgs, jrnlMsgCount);
            }

            if (jrnlMsgCount >= BULK_INSERT_COUNT || updMsg == null) {

                int retCnt = S21FastTBLAccessor.update(jrnlMsgs);

                // if passed records' count and return count don't match, error
                if (retCnt != jrnlMsgCount) {
                    throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
                }
                commitJrnlCount += jrnlMsgCount;
                // initialize
                jrnlMsgCount = 0;
                jrnlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
            }
        }

        private void submitUpdateAjeInvAcctDist(AJE_INV_ACCT_DISTTMsg updMsg) throws JrnlCommonException {

            if (updMsg != null) {
                acctDistMsgs[acctDistMsgsCount] = updMsg;
                acctDistMsgsCount += 1;
            } else {
                acctDistMsgs = commonJrnlEntry.changeArraySize(acctDistMsgs, acctDistMsgsCount);
            }

            if (acctDistMsgsCount >= BULK_INSERT_COUNT || updMsg == null) {

                int retCnt = S21FastTBLAccessor.update(acctDistMsgs);
                // if passed records' count and return count don't match, error
                if (retCnt != acctDistMsgsCount) {
                    throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
                }
                // initialize
                acctDistMsgsCount = 0;
                acctDistMsgs = new EZDTMsg[BULK_INSERT_COUNT];
            }
        }

        private boolean contain(String[] strArray, String strTarget) {
            for (int i = 0; i < strArray.length; i++) {
                if (strArray[i].equals(strTarget)) {
                    return true;
                }
            }
            return false;
        }

    }

    private Map<String, String> convertMap(List<Map<String, Object>> resultList) {
        Map<String, String> prodCcMap = new HashMap<String, String>();
        if (resultList != null && resultList.size() >= 0) {
            for (Map<String, Object> resultMap : resultList) {
                String prodCd = (String) resultMap.get("COA_PROD_CD");
                String ccCd = (String) resultMap.get("COA_CC_CD");

                if (ZYPCommonFunc.hasValue(prodCd) && !prodCcMap.containsKey(prodCd)) {
                    prodCcMap.put(prodCd, ccCd);
                }
            }
        }
        return prodCcMap;
    }

}
