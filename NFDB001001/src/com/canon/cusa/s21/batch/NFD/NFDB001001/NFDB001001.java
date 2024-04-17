package com.canon.cusa.s21.batch.NFD.NFDB001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CLT_PRMS_DTLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_PRMS_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/22   Fujitsu         K.Fujita        Create          CSA
 *</pre>
 */
public class NFDB001001 extends S21BatchMain {

    /**
     * Error Message: [@] is mandatory.
     */
    public static final String ZZZM9025E = "ZZZM9025E";

    /**
     * Error Message: Failed to update [@].
     */
    public static final String NFDM0004E = "NFDM0004E";

    /**
     * Global Company Code
     */
    private String glblCmpyCd;

    /**
     * Batch Process Date
     */
    private String bpDt;

    /**
     * Count of committed records
     */
    private int commitCount;

    /**
     * Batch Client
     */
    private S21SsmBatchClient ssmBatchClient;

    /**
     * Termination Code
     */
    private TERM_CD termCd;

    @Override
    protected void initRoutine() {

        // Global Company Code
        glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, toArray("Global Company Code"));
        }

        // Batch Process Date
        bpDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);

        this.commitCount = 0;
        this.termCd = TERM_CD.NORMAL_END;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {

        boolean doUpdateFlag = false;

        //Update Promise Broken
        List<BigDecimal> cltPrmsBrkDtlPkList = selectPromiseBroken();
        if (cltPrmsBrkDtlPkList != null && !cltPrmsBrkDtlPkList.isEmpty()) {
            if (!updatePromiseBroken(cltPrmsBrkDtlPkList)) {
                throw new S21AbendException(NFDM0004E, toArray("Update Promise Broken"));
            }
            doUpdateFlag = true;
        }

        //Update Promise Close
        List<BigDecimal> cltPrmsClsDtlPkList = selectPromiseClose();
        if (cltPrmsClsDtlPkList != null && !cltPrmsClsDtlPkList.isEmpty()) {
            if (!updatePromiseClose(cltPrmsClsDtlPkList)) {
                throw new S21AbendException(NFDM0004E, toArray("Update Promise Close"));
            }
            doUpdateFlag = true;
        }

        if (doUpdateFlag) {
            commit();
        }
    }

    @Override
    protected void termRoutine() {
        super.setTermState(this.termCd, this.commitCount, 0, 0);
    }

    /** 
     * @param args (unused)
     */
    public static void main(String[] args) {
        new NFDB001001().executeBatch(NFDB001001.class.getSimpleName());
    }

    private static String[] toArray(String str) {
        return new String[] {str };
    }

    /**
     * List Up Promise Broken
     * @return Collection Promise SEQ PK List
     */
    @SuppressWarnings("unchecked")
    private List<BigDecimal> selectPromiseBroken() {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("bpDt", bpDt);
        param.put("cltPrmsBrknFlgN", ZYPConstant.FLG_OFF_N);
        param.put("arTrxTpCd", AR_TRX_TP.RECEIPT);
        param.put("cltPrmsStsCd", CLT_PRMS_STS.COLLECTIBLE);

        List<BigDecimal> resultList = (List) this.ssmBatchClient.queryObjectList("selectPrmsBrknList", param);

        return resultList;
    }

    /**
     * List Up Promise Close
     * @return Collection Promise SEQ PK List
     */
    @SuppressWarnings("unchecked")
    private List<BigDecimal> selectPromiseClose() {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("arTrxTpCd", AR_TRX_TP.RECEIPT);
        param.put("cltPrmsStsCd", CLT_PRMS_STS.COLLECTIBLE);

        List<BigDecimal> resultList = (List) this.ssmBatchClient.queryObjectList("selectPrmsCloseList", param);

        return resultList;
    }

    /**
     * Do update Promise Broken
     * @param cltPrmsDtlPkList CltPrmsDtlPk value list for update
     * @return false for error
     */
    public boolean updatePromiseBroken(List<BigDecimal> cltPrmsDtlPkList) {

        List<CLT_PRMS_DTLTMsg> tMsgs = new ArrayList<CLT_PRMS_DTLTMsg>(cltPrmsDtlPkList.size());

        for (BigDecimal cltPrmsDtlPk : cltPrmsDtlPkList) {
            CLT_PRMS_DTLTMsg tMsg = selectForUpdateCLT_PRMS_DTLTMsg(cltPrmsDtlPk);
            if (tMsg == null) {
                return false;
            }

            tMsg.cltPrmsBrknFlg.setValue(ZYPConstant.FLG_ON_Y);
            tMsg.cltPrmsBrknDt.setValue(bpDt);

            this.commitCount++;

            tMsgs.add(tMsg);
        }

        int result = EZDFastTBLAccessor.update(tMsgs.toArray(new CLT_PRMS_DTLTMsg[tMsgs.size()]));
        if (result != tMsgs.size()) {
            return false;
        }

        return true;
    }

    /**
     * Do update Promise Close
     * @param cltPrmsDtlPkList CltPrmsDtlPk value list for update
     * @return false for error
     */
    public boolean updatePromiseClose(List<BigDecimal> cltPrmsDtlPkList) {

        List<CLT_PRMS_DTLTMsg> tMsgs = new ArrayList<CLT_PRMS_DTLTMsg>(cltPrmsDtlPkList.size());

        for (BigDecimal cltPrmsDtlPk : cltPrmsDtlPkList) {
            CLT_PRMS_DTLTMsg tMsg = selectForUpdateCLT_PRMS_DTLTMsg(cltPrmsDtlPk);
            if (tMsg == null) {
                return false;
            }

            tMsg.cltPrmsStsCd.setValue(CLT_PRMS_STS.CLOSED);

            this.commitCount++;

            tMsgs.add(tMsg);
        }

        int result = EZDFastTBLAccessor.update(tMsgs.toArray(new CLT_PRMS_DTLTMsg[tMsgs.size()]));
        if (result != tMsgs.size()) {
            return false;
        }

        return true;
    }

    /**
     * Select For Update CLT_PRMS_DTLTMsg
     * @param acMsg acMsg
     * @return CLT_PRMS_DTLTMsg
     */
    private CLT_PRMS_DTLTMsg selectForUpdateCLT_PRMS_DTLTMsg(BigDecimal cltPrmsDtlPk) {

        CLT_PRMS_DTLTMsg inMsg = new CLT_PRMS_DTLTMsg();
        inMsg.cltPrmsDtlPk.setValue(cltPrmsDtlPk);        inMsg.glblCmpyCd.setValue(glblCmpyCd);

        CLT_PRMS_DTLTMsg outMsg = (CLT_PRMS_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);

        return outMsg;
    }
}
