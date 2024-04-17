/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFD.NFDB004001;

import static com.canon.cusa.s21.batch.NFD.NFDB004001.NFDB004001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NFD.NFDB004001.NFDB004001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NFD.NFDB004001.NFDB004001Constant.NFDM0004E;
import static com.canon.cusa.s21.batch.NFD.NFDB004001.NFDB004001Constant.UPDATE_PROFILE_STRATEGY_ITEM_CD;
import static com.canon.cusa.s21.batch.NFD.NFDB004001.NFDB004001Constant.UPDATE_CNT;
import static com.canon.cusa.s21.batch.NFD.NFDB004001.NFDB004001Constant.ZZZM9025E;
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

import business.db.BILL_TO_CUSTTMsg;
import business.db.CLT_STRGY_WRK_ITEM_TRXTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_STRGY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CLT_ACCT_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 04/27/2015   Fujitsu         Y.Kamide        Create          N/A
 * 01/12/2016   CSAI            K.Uramori       Update          Apply Finalized Specification
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13004
 * 2017/01/05   Hitachi         E.Kameishi      Update          QC#16817
 * 2017/06/26   Hitachi         E.Kameishi      Update          QC#18754
 *</pre>
 */
public class NFDB004001 extends S21BatchMain implements ZYPConstant {

    /** Normal Counter */
    private int normCnt = 0;

    /** Error Counter */
    private int errCnt = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Process Date */
    private String batProcDt = null;

    /** Term Code */
    private TERM_CD termCd = null;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        this.batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd, BUSINESS_ID);
    }

    @Override
    protected void mainRoutine() {
        updateProfileStrategy();
    }

    @Override
    protected void termRoutine() {
        setTermState(termCd, normCnt, errCnt, normCnt + errCnt);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFDB004001().executeBatch(NFDB004001.class.getSimpleName());
    }

    /**
     * updateProfileStrategy
     */
    private void updateProfileStrategy() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getWorkItemTran();
            rs = ps.executeQuery();

            updateBillToCust(rs);

            rs.beforeFirst();
            updateCltStrtgyWrkItemTrx(rs);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private void updateBillToCust(ResultSet rs) throws SQLException {
        List<BigDecimal> billToList = new ArrayList<BigDecimal>();
        List<BILL_TO_CUSTTMsg> updateList = new ArrayList<BILL_TO_CUSTTMsg>();
        int cnt = 0;

        while (rs.next()) {
            BigDecimal billToCustPk = rs.getBigDecimal("BILL_TO_CUST_PK");
            if (!billToList.contains(billToCustPk)) {
                BILL_TO_CUSTTMsg billToCustTMsg = getBillToCustForUpdate(billToCustPk);
                if (billToCustTMsg == null) {
                    continue;
                }
                setValue(billToCustTMsg.dsCltAcctStsCd, DS_CLT_ACCT_STS.LEGAL_ACTION);
                updateList.add(billToCustTMsg);
                cnt++;
                if (updateList.size() >= UPDATE_CNT) {
                    updateBillToCust(updateList);
                    updateList.clear();
                    this.normCnt = cnt;
                }
                billToList.add(billToCustPk);
            }
        }
        if (updateList.size() > 0) {
            updateBillToCust(updateList);
        }
        this.normCnt = cnt;
    }

    /**
     * updateCltStrtgyWrkItemTrx
     * @param rs ResultSet
     * @throws SQLException
     */
    private void updateCltStrtgyWrkItemTrx(ResultSet rs) throws SQLException {

        List<CLT_STRGY_WRK_ITEM_TRXTMsg> updateList = new ArrayList<CLT_STRGY_WRK_ITEM_TRXTMsg>();
        while (rs.next()) {
            BigDecimal cltStrgyWorkItemTrxPk = rs.getBigDecimal("CLT_STRGY_WRK_ITEM_TRX_PK");
            CLT_STRGY_WRK_ITEM_TRXTMsg updateMsg = getCltStrtgyWrkItemTrxForUpdate(cltStrgyWorkItemTrxPk);
            if (updateMsg == null) {
                continue;
            }

            setValue(updateMsg.cltWrkItemWsrdDt, this.batProcDt);
            //---- start mod 2016/01/12 update to Close
            // START 2016/09/26 K.Kojima [QC#13004,MOD]
            // setValue(updateMsg.cltWrkItemStsCd,
            // CLT_WRK_ITEM_STS.CLOSE);
            setValue(updateMsg.cltWrkItemStsCd, CLT_WRK_ITEM_STS.COMPLETED);
            // END 2016/09/26 K.Kojima [QC#13004,MOD]
            //---- end 2016/01/12
            // START 2017/01/05 E.Kameishi [QC#16817,ADD]
            setValue(updateMsg.cltWrkItemWerdDt, this.batProcDt);
            // END 2017/01/05 E.Kameishi [QC#16817,ADD]

            updateList.add(updateMsg);
            if (updateList.size() >= UPDATE_CNT) {
                updateCltStrtgyWrkItemTrx(updateList);
                updateList.clear();
            }
        }
        if (updateList.size() > 0) {
            updateCltStrtgyWrkItemTrx(updateList);
        }
    }

    /**
     * updateBillToCust
     * @param updateList List<BILL_TO_CUSTTMsg>
     */
    private void updateBillToCust(List<BILL_TO_CUSTTMsg> updateList) {
        int cnt = S21FastTBLAccessor.update(updateList.toArray(new BILL_TO_CUSTTMsg[0]));
        if (cnt != updateList.size()) {
            String[] args = {"BILL_TO_CUST" };
            throw new S21AbendException(NFDM0004E, args);
        }
        commit();
    }

    /**
     * updateCltStrtgyWrkItemTrx
     * @param updateList List<CLT_STRGY_WRK_ITEM_TRXTMsg>
     */
    private void updateCltStrtgyWrkItemTrx(List<CLT_STRGY_WRK_ITEM_TRXTMsg> updateList) {
        int cnt = S21FastTBLAccessor.update(updateList.toArray(new CLT_STRGY_WRK_ITEM_TRXTMsg[0]));
        if (cnt != updateList.size()) {
            String[] args = {"CLT_STRGY_WRK_ITEM_TRX" };
            throw new S21AbendException(NFDM0004E, args);
        }
        commit();
    }

    /**
     * getWorkItemTran
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getWorkItemTran() throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cltStrgyStsCd", CLT_STRGY_STS.OPEN);
        queryParam.put("batProcDt", batProcDt);
        queryParam.put("cltWrkItemCd", UPDATE_PROFILE_STRATEGY_ITEM_CD);
        queryParam.put("cltWrkItemStsCd", CLT_WRK_ITEM_STS.PENDING);
        // START 2017/06/26 E.Kameishi [QC#18754, ADD]
        queryParam.put("wrkItmStsOpen", CLT_WRK_ITEM_STS.OPEN);
        // END 2017/06/26 E.Kameishi [QC#18754, ADD]
        queryParam.put("flgY", FLG_ON_Y);
        // START 2017/08/03 J.Kim [QC#18754, ADD]
        queryParam.put("wrkTpCd", CLT_WRK_TP.MANUAL);
        // END 2017/08/03 J.Kim [QC#18754, ADD]

        return this.ssmLLClient.createPreparedStatement("getWorkItemTran", queryParam, getExecPrm());
    }

    /**
     * getCltStrtgyWrkItemTrxForUpdate
     * @param cltStrgyWorkItemTrxPk BigDecimal
     * @return CLT_STRGY_WRK_ITEM_TRXTMsg
     */
    private CLT_STRGY_WRK_ITEM_TRXTMsg getCltStrtgyWrkItemTrxForUpdate(BigDecimal cltStrgyWorkItemTrxPk) {
        CLT_STRGY_WRK_ITEM_TRXTMsg inMsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.cltStrgyWrkItemTrxPk, cltStrgyWorkItemTrxPk);

        return (CLT_STRGY_WRK_ITEM_TRXTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * getBillToCustForUpdate
     * @param billToCustPk BigDecimal
     * @return BILL_TO_CUSTTMsg
     */
    private BILL_TO_CUSTTMsg getBillToCustForUpdate(BigDecimal billToCustPk) {
        BILL_TO_CUSTTMsg inMsg = new BILL_TO_CUSTTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.billToCustPk, billToCustPk);

        return (BILL_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * get S21SsmExecutionParameter
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter getExecPrm() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

}
