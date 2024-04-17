/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB561001;

import static com.canon.cusa.s21.batch.NMA.NMAB561001.Constant.NMAB561001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ACCT_LOCTMsg;
import business.db.ALT_PAYERTMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.DS_ACCT_PROSTMsg;
import business.db.DS_ACCT_PROSTMsgArray;
import business.db.DS_ACCT_RELNTMsg;
import business.db.DS_CTAC_PNTTMsg;
import business.db.DS_CUST_TRX_RULETMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.INV_RCPNTTMsg;
import business.db.PRIN_CUSTTMsg;
import business.db.PTY_LOC_WRKTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.VNDTMsg;
import business.parts.NFZC202001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Customer Effective Check Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/17   Fujitsu         K.Minamide      Create          N/A
 * 2016/02/12   Fujitsu         K.Minamide      Update          S21-QC#3792
 * 2016/03/02   Fujitsu         C.Tanaka        Update          QC#4779
 * 2016/05/25   SRAA            Y.Chen          Update          QC#8590
 * 2016/09/08   Fujitsu         N.Sugiura       Update          QC#3569
 * 2016/09/12   SRAA            Y.Chen          Update          QC#9448
 * 2016/10/24   Fujitsu         C.Yokoi         Update          QC#14893
 * 2017/05/23   Fujitsu         N.Sugiura       Update          QC#18662
 * 2019/05/23   Fujitsu         Hd.Sugawara     Update          QC#50101
 * 2022/04/28   CITS            S.go            Update          QC#59983
 * 2022/09/15   CITS            A.Cullano       Update          QC#60368
 *</pre>
 */
public class NMAB561001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** commitUnit */
    private int commitUnit = 0;

    /** process date time */
    private String procDt = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // 2022/04/28 QC#59983 Add Start
    /** Mail List */
    private List<Map<String, String>> skipCustomerList = new ArrayList<Map<String, String>>();
    // 2022/04/28 QC#59983 Add End

    @Override
    protected void initRoutine() {
        // blank check(Global Company Code)
        this.glblCmpyCd = super.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        // value check(Global Company Code)
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
        }

        this.commitUnit = getCommitCount();

        if (this.commitUnit <= 0 || DEFAULT_COMMIT_UNIT < this.commitUnit) {
            this.commitUnit = DEFAULT_COMMIT_UNIT;
        }

        // 2016/02/12 S21-QC#3792 Mod Start
        this.procDt = ZYPDateUtil.addDays(ZYPDateUtil.getBatProcDate(), 1);
        // 2016/02/12 S21-QC#3792 Mod End
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        this.updateReadyAccountLoocation();
        this.updateTerminatedAccountLocation();
        // 2016/09/08 QC#3569 Add Start
        this.updatePtyLocWrkReady();
        this.updatePtyLocWrkTerminated();
        // 2016/09/08 QC#3569 Add End

        this.updateBillToCustReady();
        this.updateBillToCustTerminated();
        // 2016/10/24 QC#14893 Add Start
        this.updateDsShipToCustDeleteRelatedBillTo();
        this.updateDsCustTrxRuleDeleteDefBillTo();
        // 2016/10/24 QC#14893 Add End

        this.updateShipToCustReady();
        this.updateShipToCustTerminated();
        // 2016/10/24 QC#14893 Add Start
        this.updateDsCustTrxRuleDeleteDefShipTo();
        // 2016/10/24 QC#14893 Add End

        this.updateAccountRelationReady();
        this.updateAccountRelationTerminated();
       // this.updateContactPersonActive();
       // this.updateContactPersonInactive();
        this.updateContactPoints();
        this.updateVendorMaster();
    }

    /**
     * @param dsAcctNum
     * @return
     */
    private NFZC202001PMsg makePMsg(String dsAcctNum) {
        NFZC202001PMsg pMsg = new NFZC202001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, MODE_CUST_ACCT);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, dsAcctNum);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, this.procDt);
        return pMsg;
    }

    /**
     * 
     */
    private void updateReadyAccountLoocation() {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamAcctLoc = new HashMap<String, String>();
        ssmParamAcctLoc.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamAcctLoc.put("procDt", this.procDt);
        ssmParamAcctLoc.put("rgtnStsCdPendingCompletion", RGTN_STS.PENDING_COMPLETION);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getAcctLocUpdateReady", ssmParamAcctLoc, execParam);

            rsSet = stmt.executeQuery();
            int commitCount = 0;
            while (rsSet.next()) {
                this.totalCount++;
                BigDecimal acctLocPk = rsSet.getBigDecimal("ACCT_LOC_PK");
                ACCT_LOCTMsg updateTMsgAcctLoc = new ACCT_LOCTMsg();

                ZYPEZDItemValueSetter.setValue(updateTMsgAcctLoc.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsgAcctLoc.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(updateTMsgAcctLoc.acctLocPk, acctLocPk);
                updateTMsgAcctLoc = (ACCT_LOCTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgAcctLoc);
                if (updateTMsgAcctLoc != null) {
                    ZYPEZDItemValueSetter.setValue(updateTMsgAcctLoc.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                    S21FastTBLAccessor.update(updateTMsgAcctLoc);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgAcctLoc.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"ACCT_LOC" });
                    }
                }

                Map<String, String> ssmParamCreditBalanceUpdate = new HashMap<String, String>();
                String newDsAcctNum = rsSet.getString("DS_ACCT_NUM");

                ssmParamCreditBalanceUpdate.put("glblCmpyCd", this.glblCmpyCd);
                ssmParamCreditBalanceUpdate.put("locNum", rsSet.getString("LOC_NUM"));
                ssmParamCreditBalanceUpdate.put("dsAcctNum", newDsAcctNum);

                String oldDsAcctNum = (String) this.ssmBatchClient.queryObject("getCreditBalanceUpdate", ssmParamCreditBalanceUpdate);

                if (ZYPCommonFunc.hasValue(oldDsAcctNum)) {
                    NFZC202001 api = new NFZC202001();
                    NFZC202001PMsg pMsgNew = makePMsg(newDsAcctNum);
                    api.execute(pMsgNew, ONBATCH_TYPE.BATCH);
                    if (S21ApiUtil.isXxMsgId(pMsgNew)) {
                        List<String> errIdList = S21ApiUtil.getXxMsgIdList(pMsgNew);
                        this.totalErrCount++;
                        this.setTermState(TERM_CD.ABNORMAL_END);
                        super.rollback();
                        throw new S21AbendException(errIdList.get(0));
                    }

                    NFZC202001PMsg pMsgOld = makePMsg(oldDsAcctNum);
                    api.execute(pMsgOld, ONBATCH_TYPE.BATCH);
                    if (S21ApiUtil.isXxMsgId(pMsgOld)) {
                        List<String> errIdList = S21ApiUtil.getXxMsgIdList(pMsgOld);
                        this.totalErrCount++;
                        this.setTermState(TERM_CD.ABNORMAL_END);
                        super.rollback();
                        throw new S21AbendException(errIdList.get(0));
                    }
                }

                // QC#8590
                switchAccountForLocationUsage(rsSet.getString("LOC_NUM"), newDsAcctNum);
                // QC#9448
                switchAccountCoa(rsSet.getString("LOC_NUM"), newDsAcctNum);

                // Add Start 2019/05/23 QC#50101
                String locNum = rsSet.getString("LOC_NUM");
                BigDecimal ptyLocPk = rsSet.getBigDecimal("PTY_LOC_PK");
                BigDecimal cmpyPk = rsSet.getBigDecimal("CMPY_PK");

                switchCmpyPk(newDsAcctNum, locNum, ptyLocPk, cmpyPk);
                // Add End 2019/05/23 QC#50101

                commitCount++;

                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }
            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    // QC#8590
    private void switchAccountForLocationUsage(String locNum, String newDsAcctNum) {
        String dsAcctNm = getAccountName(newDsAcctNum);
        switchAccountForBillTo(locNum, newDsAcctNum, dsAcctNm);
        switchAccountForInvRcpnt(locNum, newDsAcctNum, dsAcctNm);
        switchAccountForAltPayer(locNum, newDsAcctNum, dsAcctNm);
        switchAccountForShipTo(locNum, newDsAcctNum, dsAcctNm);
    }

    private String getAccountName(String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        String dsAcctNm = (String) this.ssmBatchClient.queryObject("getAcctNmByAcctNum", ssmParam);
        return dsAcctNm;
    }

    private void switchAccountForBillTo(String locNum, String newDsAcctNum, String dsAcctNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("locNum", locNum);
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getBillToByLocNum", ssmParam);
        for (Map<String, Object> map : list) {
            BigDecimal billToCustPk = (BigDecimal) map.get("BILL_TO_CUST_PK");

            switchAccountUpdateBillToCust(billToCustPk, dsAcctNm, newDsAcctNum);
        }
    }

    private void switchAccountForInvRcpnt(String locNum, String newDsAcctNum, String dsAcctNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("locNum", locNum);
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getInvRcpntByLocNum", ssmParam);
        for (Map<String, Object> map : list) {
            BigDecimal custPk = (BigDecimal) map.get("CUST_PK");

            switchAccountUpdateInvRcpnt(custPk, getLocNmFromAcctNm(dsAcctNm));
        }
    }

    private void switchAccountForAltPayer(String locNum, String newDsAcctNum, String dsAcctNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("locNum", locNum);
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getAltPayerByLocNum", ssmParam);
        for (Map<String, Object> map : list) {
            String billToCustCd = (String) map.get("BILL_TO_CUST_CD");
            String oldSellToCustCd = (String) map.get("SELL_TO_CUST_CD");

            switchAccountUpdateAltPayer(billToCustCd, oldSellToCustCd, newDsAcctNum, dsAcctNm);
        }
    }

    private void switchAccountForShipTo(String locNum, String newDsAcctNum, String dsAcctNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("locNum", locNum);
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getShipToByLocNum", ssmParam);
        for (Map<String, Object> map : list) {
            BigDecimal shipToCustPk = (BigDecimal) map.get("SHIP_TO_CUST_PK");

            switchAccountUpdateShipToCust(shipToCustPk, newDsAcctNum, dsAcctNm);
        }
    }

    private void switchAccountUpdateBillToCust(BigDecimal billToCustPk, String dsAcctNm, String newDsAcctNum) {
        BILL_TO_CUSTTMsg tMsg = new BILL_TO_CUSTTMsg();
        tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        tMsg.billToCustPk.setValue(billToCustPk);
        tMsg = (BILL_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
        if (tMsg != null) {

            ZYPEZDItemValueSetter.setValue(tMsg.locNm, getLocNmFromAcctNm(dsAcctNm));
            ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, newDsAcctNum);
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, dsAcctNm);

            S21FastTBLAccessor.update(tMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                S21InfoLogOutput.println("switchAccountForBillToCust failed. billToCustPk: [" + billToCustPk + "]");
                throw new S21AbendException(MMAM0004E, new String[] {"BILL_TO_CUST" });
            }
        }
    }

    private void switchAccountUpdateInvRcpnt(BigDecimal custPk, String dsAcctNm) {
        INV_RCPNTTMsg tMsg = new INV_RCPNTTMsg();
        tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        tMsg.custPk.setValue(custPk);
        tMsg = (INV_RCPNTTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
        if (tMsg != null) {

            ZYPEZDItemValueSetter.setValue(tMsg.locNm, getLocNmFromAcctNm(dsAcctNm));

            S21FastTBLAccessor.update(tMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                S21InfoLogOutput.println("switchAccountForInvRcpnt failed. custPk: [" + custPk + "]");
                throw new S21AbendException(MMAM0004E, new String[] {"INV_RCPNT" });
            }
        }
    }

    private void switchAccountUpdateAltPayer(String billToCustCd, String oldSellToCustCd, String newDsAcctNum, String dsAcctNm) {
        ALT_PAYERTMsg tMsgOld = new ALT_PAYERTMsg();
        ALT_PAYERTMsg tMsgNew = new ALT_PAYERTMsg();

        tMsgOld.glblCmpyCd.setValue(this.glblCmpyCd);
        tMsgOld.sellToCustCd.setValue(oldSellToCustCd);
        tMsgOld.billToCustCd.setValue(billToCustCd);
        tMsgOld = (ALT_PAYERTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsgOld);
        if (tMsgOld != null) {

            tMsgNew.glblCmpyCd.setValue(this.glblCmpyCd);
            tMsgNew.sellToCustCd.setValue(newDsAcctNum);
            tMsgNew.billToCustCd.setValue(billToCustCd);
            tMsgNew = (ALT_PAYERTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsgNew);

            if (tMsgNew == null) {
                // START 2022/09/15 A.Cullano [QC#60368, MOD]
                // Check if the account was logically deleted
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", this.glblCmpyCd);
                ssmParam.put("sellToCustCd", newDsAcctNum);
                ssmParam.put("billToCustCd", billToCustCd);
                Map< ? , ? > map = (Map< ? , ? >) this.ssmBatchClient.queryObject("getLogicalRemovedAltPayer", ssmParam);
                if (map != null) {
                    // If it was logically deleted, physically delete the data first the re-create it
                    ALT_PAYERTMsg tMsgCreate = new ALT_PAYERTMsg();

                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ezTableID, (String) map.get("EZTABLEID"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ezInTime, (String) map.get("EZINTIME"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ezInTimeZone, (String) map.get("EZINTIMEZONE"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ezInCompanyCode, (String) map.get("EZINCOMPANYCODE"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ezInUserID, (String) map.get("EZINUSERID"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ezInAplID, (String) map.get("EZINAPLID"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ezUpTime, (String) map.get("EZUPTIME"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ezUpTimeZone, (String) map.get("EZUPTIMEZONE"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ezUpCompanyCode, (String) map.get("EZUPCOMPANYCODE"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ezUpAplID, (String) map.get("EZUPAPLID"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ezUpCompanyCode, (String) map.get("EZUPCOMPANYCODE"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.glblCmpyCd, (String) map.get("GLBL_CMPY_CD"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.sellToCustCd, newDsAcctNum);
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.billToCustCd, (String) map.get("BILL_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.firstLineAddr, (String) map.get("FIRST_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.scdLineAddr, (String) map.get("SCD_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.thirdLineAddr, (String) map.get("THIRD_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.frthLineAddr, (String) map.get("FRTH_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ctyAddr, (String) map.get("CTY_ADDR"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.cntyPk, (BigDecimal) map.get("CNTY_PK"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.provNm, (String) map.get("PROV_NM"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.stCd, (String) map.get("ST_CD"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.postCd, (String) map.get("POST_CD"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ctryCd, (String) map.get("CTRY_CD"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.locNum, (String) map.get("LOC_NUM"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.locNm, getLocNmFromAcctNm(dsAcctNm));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.addlLocNm, (String) map.get("ADDL_LOC_NUM"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.glnNum, (BigDecimal) map.get("GLN_NUM"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.firstRefCmntTxt, (String) map.get("FIRST_REF_CMN_TXT"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.scdRefCmntTxt, (String) map.get("SCD_REF_CMN_TXT"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.dbaNm, (String) map.get("DBA_NM"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.dunsNum, (String) map.get("DUNS_NUM"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.rgtnStsCd, (String) map.get("RGTN_STS_CD"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.flPlnCmpyFlg, (String) map.get("FL_PLN_CMPY_FLG"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.ptyLocPk, (BigDecimal) map.get("PTY_LOC_PK"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.locRoleTpCd, (String) map.get("LOC_ROLE_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.locGrpCd, (String) map.get("LOC_GRP_CD"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.effFromDt, (String) map.get("EFF_FROM_DT"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.effThruDt, (String) map.get("EFF_THRU_DT"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.remId, (String) map.get("REM_ID"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.cmpyPk, (BigDecimal) map.get("CMPY_PK"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.prinCustPk, (BigDecimal) map.get("PRIN_CUST_PK"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.faxNum, (String) map.get("FAX_NUM"));
                    ZYPEZDItemValueSetter.setValue(tMsgCreate.telNum, (String) map.get("TEL_NUM"));

                    // Delete the logical removed data
                    ALT_PAYERTMsg tMsgDelete = new ALT_PAYERTMsg();
                    tMsgDelete.glblCmpyCd.setValue(this.glblCmpyCd);
                    tMsgDelete.sellToCustCd.setValue(newDsAcctNum);
                    tMsgDelete.billToCustCd.setValue(billToCustCd);
                    int deleteCount = S21FastTBLAccessor.removePhysical(new ALT_PAYERTMsg[] {tMsgDelete});
                    if (deleteCount != 1) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"ALT_PAYER" });
                    }

                    // Re-create the logical removed data
                    EZDTBLAccessor.create(tMsgCreate);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsgCreate.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        S21InfoLogOutput.println("switchAccountForAltPayer failed to create new record. billToCustCd: [" + billToCustCd + "], oldSellToCustCd: [" + oldSellToCustCd + "], newDsAcctNum: [" + newDsAcctNum + "]");
                        throw new S21AbendException(MMAM0004E, new String[] {"ALT_PAYER" });
                    }
                } else {
                    tMsgNew = new ALT_PAYERTMsg();
                    EZDMsg.copy(tMsgOld, null, tMsgNew, null);

                    ZYPEZDItemValueSetter.setValue(tMsgNew.sellToCustCd, newDsAcctNum);
                    ZYPEZDItemValueSetter.setValue(tMsgNew.locNm, getLocNmFromAcctNm(dsAcctNm));

                    EZDTBLAccessor.create(tMsgNew);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsgNew.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        S21InfoLogOutput.println("switchAccountForAltPayer failed to create new record. billToCustCd: [" + billToCustCd + "], oldSellToCustCd: [" + oldSellToCustCd + "], newDsAcctNum: [" + newDsAcctNum + "]");
                        throw new S21AbendException(MMAM0004E, new String[] {"ALT_PAYER" });
                    }
                }
//              tMsgNew = new ALT_PAYERTMsg();
//              EZDMsg.copy(tMsgOld, null, tMsgNew, null);
//
//              ZYPEZDItemValueSetter.setValue(tMsgNew.sellToCustCd, newDsAcctNum);
//              ZYPEZDItemValueSetter.setValue(tMsgNew.locNm, getLocNmFromAcctNm(dsAcctNm));
//
//              S21FastTBLAccessor.create(tMsgNew);
//              if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsgNew.getReturnCode())) {
//                  this.totalErrCount++;
//                  super.rollback();
//                  S21InfoLogOutput.println("switchAccountForAltPayer failed to create new record. billToCustCd: [" + billToCustCd + "], oldSellToCustCd: [" + oldSellToCustCd + "], newDsAcctNum: [" + newDsAcctNum + "]");
//                  throw new S21AbendException(MMAM0004E, new String[] {"ALT_PAYER" });
//              }
                // END 2022/09/15 A.Cullano [QC#60368, MOD]
            }

            EZDTBLAccessor.logicalRemove(tMsgOld);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsgOld.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                S21InfoLogOutput.println("switchAccountForAltPayer failed to remove old record. billToCustCd: [" + billToCustCd + "], oldSellToCustCd: [" + oldSellToCustCd + "], newDsAcctNum: [" + newDsAcctNum + "]");
                throw new S21AbendException(MMAM0004E, new String[] {"ALT_PAYER" });
            }
        }
    }

    private void switchAccountUpdateShipToCust(BigDecimal shipToCustPk, String newDsAcctNum, String dsAcctNm) {
        SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
        tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        tMsg.shipToCustPk.setValue(shipToCustPk);
        tMsg = (SHIP_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
        if (tMsg != null) {

            ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, newDsAcctNum);
            ZYPEZDItemValueSetter.setValue(tMsg.locNm, getLocNmFromAcctNm(dsAcctNm));
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, dsAcctNm);

            S21FastTBLAccessor.update(tMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                S21InfoLogOutput.println("switchAccountForShipToCust failed. shipToCustPk: [" + shipToCustPk + "]");
                throw new S21AbendException(MMAM0004E, new String[] {"SHIP_TO_CUST" });
            }
        }
    }

    // Add Start 2019/05/23 QC#50101
    private void switchCmpyPk(String newDsAcctNum, String locNum, BigDecimal ptyLocPk, BigDecimal cmpyPk) {
        BigDecimal newCmpyPk = getAcctCmpyPkByAcctNum(newDsAcctNum);

        if (newCmpyPk.compareTo(cmpyPk) == 0) {
            // Account is not changed.
            return;
        }

        // If Account is changed, update CMPY_PK of PTY_LOC_WRK
        PTY_LOC_WRKTMsg updatePtyLocWrk = new PTY_LOC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(updatePtyLocWrk.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(updatePtyLocWrk.ptyLocPk, ptyLocPk);
        updatePtyLocWrk = (PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(updatePtyLocWrk);

        if (updatePtyLocWrk != null) {
            ZYPEZDItemValueSetter.setValue(updatePtyLocWrk.cmpyPk, newCmpyPk);

            S21FastTBLAccessor.update(updatePtyLocWrk);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updatePtyLocWrk.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(MMAM0004E, new String[] {"PTY_LOC_WRK" });
            }
        }

        // Clear PRIN_CUST
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParamPrinCust = new HashMap<String, Object>();
        ssmParamPrinCust.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamPrinCust.put("procDt", this.procDt);
        ssmParamPrinCust.put("ptyLocPk", ptyLocPk);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrinCustPk", ssmParamPrinCust, execParam);

            rsSet = stmt.executeQuery();
            while (rsSet.next()) {
                BigDecimal prinCustPk = rsSet.getBigDecimal("PRIN_CUST_PK");

                PRIN_CUSTTMsg prinCustTMsg = new PRIN_CUSTTMsg();
                ZYPEZDItemValueSetter.setValue(prinCustTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prinCustTMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(prinCustTMsg.prinCustPk, prinCustPk);
                prinCustTMsg = (PRIN_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(prinCustTMsg);

                if (prinCustTMsg != null) {
                    prinCustTMsg.firstLineAddr.clear();
                    prinCustTMsg.scdLineAddr.clear();
                    prinCustTMsg.thirdLineAddr.clear();
                    prinCustTMsg.frthLineAddr.clear();
                    prinCustTMsg.ctyAddr.clear();
                    prinCustTMsg.cntyPk.clear();
                    prinCustTMsg.provNm.clear();
                    prinCustTMsg.stCd.clear();
                    prinCustTMsg.postCd.clear();
                    prinCustTMsg.ctryCd.clear();
                    prinCustTMsg.locNum.clear();
                    prinCustTMsg.ptyLocPk.clear();

                    S21FastTBLAccessor.update(prinCustTMsg);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(prinCustTMsg.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"PRIN_CUST" });
                    }
                }
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private BigDecimal getAcctCmpyPkByAcctNum(String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        BigDecimal cmpyPk = (BigDecimal) this.ssmBatchClient.queryObject("getAcctCmpyPkByAcctNum", ssmParam);
        return cmpyPk;
    }
    // Add End 2019/05/23 QC#50101

    private String getLocNmFromAcctNm(String dsAcctNm) {
        if (!ZYPCommonFunc.hasValue(dsAcctNm)) {
            return "";
        }
        return S21StringUtil.subStringByLength(dsAcctNm, 0, MAX_LEN_LOC_NM);
    }

    /**
     * 
     */
    private void updateTerminatedAccountLocation() {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamAcctLoc = new HashMap<String, String>();
        ssmParamAcctLoc.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamAcctLoc.put("procDt", this.procDt);
        ssmParamAcctLoc.put("rgtnStsCdPendingCompletion", RGTN_STS.PENDING_COMPLETION); // 2016/09/08 QC#3569 Add
        ssmParamAcctLoc.put("rgtnStsCdReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getAcctLocUpdateTerminated", ssmParamAcctLoc, execParam);

            rsSet = stmt.executeQuery();
            int commitCount = 0;
            while (rsSet.next()) {
                this.totalCount++;
                BigDecimal acctLocPk = rsSet.getBigDecimal("ACCT_LOC_PK");
                BigDecimal ptyLocPk = rsSet.getBigDecimal("PTY_LOC_PK");
                String dsAcctNum = rsSet.getString("DS_ACCT_NUM");
                String locNum = rsSet.getString("LOC_NUM");

                this.updateAcctLoc(acctLocPk, ptyLocPk, dsAcctNum, locNum);
                commitCount++;

                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }
            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    /**
     * @param acctLocPk
     * @param ptyLocPk
     * @param dsAcctNum
     * @param locNum
     */
    private void updateAcctLoc(BigDecimal acctLocPk, BigDecimal ptyLocPk, String dsAcctNum, String locNum) {
        ACCT_LOCTMsg updateTMsgAcctLoc = new ACCT_LOCTMsg();
        ZYPEZDItemValueSetter.setValue(updateTMsgAcctLoc.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(updateTMsgAcctLoc.ezCancelFlag, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(updateTMsgAcctLoc.acctLocPk, acctLocPk);
        updateTMsgAcctLoc = (ACCT_LOCTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgAcctLoc);
        if (updateTMsgAcctLoc != null) {
            ZYPEZDItemValueSetter.setValue(updateTMsgAcctLoc.rgtnStsCd, RGTN_STS.TERMINATED);
            S21FastTBLAccessor.update(updateTMsgAcctLoc);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgAcctLoc.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(MMAM0004E, new String[] {"ACCT_LOC" });
            }
            this.updatePrimary(ptyLocPk, dsAcctNum, locNum);
        }
    }

    /**
     * @param ptyLocPk
     * @param dsAcctNum
     * @param locNum
     */
    private void updatePrimary(BigDecimal ptyLocPk, String dsAcctNum, String locNum) {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParamPrinCust = new HashMap<String, Object>();
        ssmParamPrinCust.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamPrinCust.put("procDt", this.procDt);
        ssmParamPrinCust.put("ptyLocPk", ptyLocPk);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrinCustPk", ssmParamPrinCust, execParam);

            rsSet = stmt.executeQuery();
            while (rsSet.next()) {

                BigDecimal prinCustPk = rsSet.getBigDecimal("PRIN_CUST_PK");

                Map<String, String> ssmParamPrimaryUpdate = new HashMap<String, String>();
                ssmParamPrimaryUpdate.put("glblCmpyCd", this.glblCmpyCd);
                ssmParamPrimaryUpdate.put("dsAcctNum", dsAcctNum);
                // Mod Start 2019/05/23 QC#50101
                //ssmParamPrimaryUpdate.put("locNum", locNum);
                // Because terminate process is not finished at this time, use EFF_THRU_DT for judge 'Active'.
                ssmParamPrimaryUpdate.put("maxDt", MAX_DT);
                ssmParamPrimaryUpdate.put("procDt", this.procDt);
                ssmParamPrimaryUpdate.put("rgtnStsCdPendingCompletion", RGTN_STS.PENDING_COMPLETION);
                ssmParamPrimaryUpdate.put("rgtnStsCdReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
                // Mod End 2019/05/23 QC#50101

                Map<String, Object> ptyLocWrkMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrimaryUpdate", ssmParamPrimaryUpdate);
                if (ptyLocWrkMap != null) {
                    PRIN_CUSTTMsg updateTMsgPrinCust = new PRIN_CUSTTMsg();
                    ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                    ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.prinCustPk, prinCustPk);
                    updateTMsgPrinCust = (PRIN_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgPrinCust);
                    if (updateTMsgPrinCust != null) {
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.firstLineAddr, (String) ptyLocWrkMap.get("FIRST_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.scdLineAddr, (String) ptyLocWrkMap.get("SCD_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.thirdLineAddr, (String) ptyLocWrkMap.get("THIRD_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.frthLineAddr, (String) ptyLocWrkMap.get("FRTH_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.ctyAddr, (String) ptyLocWrkMap.get("CTY_ADDR"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.cntyPk, (BigDecimal) ptyLocWrkMap.get("CNTY_PK"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.provNm, (String) ptyLocWrkMap.get("PROV_NM"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.stCd, (String) ptyLocWrkMap.get("ST_CD"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.postCd, (String) ptyLocWrkMap.get("POST_CD"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.ctryCd, (String) ptyLocWrkMap.get("CTRY_CD"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.locNum, (String) ptyLocWrkMap.get("LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.locNm, (String) ptyLocWrkMap.get("LOC_NM"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.rgtnStsCd, (String) ptyLocWrkMap.get("RGTN_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.ptyLocPk, (BigDecimal) ptyLocWrkMap.get("PTY_LOC_PK"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.effFromDt, (String) ptyLocWrkMap.get("EFF_FROM_DT"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.effThruDt, (String) ptyLocWrkMap.get("EFF_THRU_DT"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.telNum, (String) ptyLocWrkMap.get("TEL_NUM"));
                        ZYPEZDItemValueSetter.setValue(updateTMsgPrinCust.geoCd, (String) ptyLocWrkMap.get("GEO_CD"));

                        S21FastTBLAccessor.update(updateTMsgPrinCust);
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgPrinCust.getReturnCode())) {
                            this.totalErrCount++;
                            super.rollback();
                            throw new S21AbendException(MMAM0004E, new String[] {"PRIN_CUST" });
                        }
                    }

                    DS_ACCT_PROSTMsg selectDsAcctPros = new DS_ACCT_PROSTMsg();

                    selectDsAcctPros.setSQLID("001");
                    selectDsAcctPros.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                    selectDsAcctPros.setConditionValue("dsAcctNum01", dsAcctNum);
                    DS_ACCT_PROSTMsgArray selectTMsgDsProsCustArray = (DS_ACCT_PROSTMsgArray) EZDTBLAccessor.findByCondition(selectDsAcctPros);

                    if (selectTMsgDsProsCustArray.getValidCount() > 0) {
                        this.updateAcctPros(selectTMsgDsProsCustArray.no(0), ptyLocWrkMap);
                    }

                    SELL_TO_CUSTTMsg selectSellToCust = new SELL_TO_CUSTTMsg();
                    selectSellToCust.setSQLID("003");
                    selectSellToCust.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                    selectSellToCust.setConditionValue("sellToCustCd01", dsAcctNum);
                    SELL_TO_CUSTTMsgArray selectTMsgSellToCustArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(selectSellToCust);

                    if (selectTMsgSellToCustArray.getValidCount() > 0) {
                        BigDecimal updateSellToCustPk = selectTMsgSellToCustArray.no(0).sellToCustPk.getValue();

                        SELL_TO_CUSTTMsg updateSellToCust = new SELL_TO_CUSTTMsg();
                        ZYPEZDItemValueSetter.setValue(updateSellToCust.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(updateSellToCust.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                        ZYPEZDItemValueSetter.setValue(updateSellToCust.sellToCustPk, updateSellToCustPk);
                        updateSellToCust = (SELL_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(updateSellToCust);

                        if (updateSellToCust != null) {
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.firstLineAddr, (String) ptyLocWrkMap.get("FIRST_LINE_ADDR"));
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.scdLineAddr, (String) ptyLocWrkMap.get("SCD_LINE_ADDR"));
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.thirdLineAddr, (String) ptyLocWrkMap.get("THIRD_LINE_ADDR"));
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.frthLineAddr, (String) ptyLocWrkMap.get("FRTH_LINE_ADDR"));
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.ctyAddr, (String) ptyLocWrkMap.get("CTY_ADDR"));
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.cntyPk, (BigDecimal) ptyLocWrkMap.get("CNTY_PK"));
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.provNm, (String) ptyLocWrkMap.get("PROV_NM"));
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.stCd, (String) ptyLocWrkMap.get("ST_CD"));
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.postCd, (String) ptyLocWrkMap.get("POST_CD"));
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.ctryCd, (String) ptyLocWrkMap.get("CTRY_CD"));
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.locNum, (String) ptyLocWrkMap.get("LOC_NUM"));
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.locNm, (String) ptyLocWrkMap.get("LOC_NM"));
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.ptyLocPk, (BigDecimal) ptyLocWrkMap.get("PTY_LOC_PK"));
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.telNum, (String) ptyLocWrkMap.get("TEL_NUM"));
                            ZYPEZDItemValueSetter.setValue(updateSellToCust.geoCd, (String) ptyLocWrkMap.get("GEO_CD"));

                            S21FastTBLAccessor.update(updateSellToCust);
                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgPrinCust.getReturnCode())) {
                                this.totalErrCount++;
                                super.rollback();
                                throw new S21AbendException(MMAM0004E, new String[] {"SELL_TO_CUST" });
                            }
                        }
                    }
                } else {
                    PRIN_CUSTTMsg updateTerminatedPrinCust = new PRIN_CUSTTMsg();
                    ZYPEZDItemValueSetter.setValue(updateTerminatedPrinCust.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(updateTerminatedPrinCust.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                    ZYPEZDItemValueSetter.setValue(updateTerminatedPrinCust.prinCustPk, prinCustPk);
                    updateTerminatedPrinCust = (PRIN_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTerminatedPrinCust);

                    if (updateTerminatedPrinCust != null) {
                        ZYPEZDItemValueSetter.setValue(updateTerminatedPrinCust.rgtnStsCd, RGTN_STS.TERMINATED);
                        // Mod Start 2019/05/23 QC#50101
                        //ZYPEZDItemValueSetter.setValue(updateTerminatedPrinCust.effThruDt, this.procDt);
                        ZYPEZDItemValueSetter.setValue(updateTerminatedPrinCust.effThruDt, ZYPDateUtil.addDays(this.procDt, -1));
                        // Mod End 2019/05/23 QC#50101

                        S21FastTBLAccessor.update(updateTerminatedPrinCust);
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTerminatedPrinCust.getReturnCode())) {
                            this.totalErrCount++;
                            super.rollback();
                            throw new S21AbendException(MMAM0004E, new String[] {"PRIN_CUST" });
                        }
                    }
                }
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    /**
     * @param selectTMsgDsProsCust
     * @param ptyLocWrkMap
     */
    private void updateAcctPros(DS_ACCT_PROSTMsg selectTMsgDsProsCust, Map<String, Object> ptyLocWrkMap) {
        BigDecimal updateDsAcctProsPk = selectTMsgDsProsCust.dsAcctProsPk.getValue();

        DS_ACCT_PROSTMsg updateDsAcctPros = new DS_ACCT_PROSTMsg();
        ZYPEZDItemValueSetter.setValue(updateDsAcctPros.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(updateDsAcctPros.ezCancelFlag, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(updateDsAcctPros.dsAcctProsPk, updateDsAcctProsPk);
        updateDsAcctPros = (DS_ACCT_PROSTMsg) EZDTBLAccessor.findByKeyForUpdate(updateDsAcctPros);

        if (updateDsAcctPros != null) {

            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.firstLineAddr, (String) ptyLocWrkMap.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.scdLineAddr, (String) ptyLocWrkMap.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.thirdLineAddr, (String) ptyLocWrkMap.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.frthLineAddr, (String) ptyLocWrkMap.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.ctyAddr, (String) ptyLocWrkMap.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.cntyPk, (BigDecimal) ptyLocWrkMap.get("CNTY_PK"));
            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.provNm, (String) ptyLocWrkMap.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.stCd, (String) ptyLocWrkMap.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.postCd, (String) ptyLocWrkMap.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.ctryCd, (String) ptyLocWrkMap.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.locNum, (String) ptyLocWrkMap.get("LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.locNm, (String) ptyLocWrkMap.get("LOC_NM"));
            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.ptyLocPk, (BigDecimal) ptyLocWrkMap.get("PTY_LOC_PK"));
            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.telNum, (String) ptyLocWrkMap.get("TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(updateDsAcctPros.geoCd, (String) ptyLocWrkMap.get("GEO_CD"));

            S21FastTBLAccessor.update(updateDsAcctPros);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateDsAcctPros.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(MMAM0004E, new String[] {"DS_ACCT_PROS" });
            }
        }
    }
    // 2016/09/08 QC#3569 Add Start
    /**
     * 
     */
    private void updateAccountRelationReady() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamAccountRelationReadyUpdate = new HashMap<String, String>();
        ssmParamAccountRelationReadyUpdate.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamAccountRelationReadyUpdate.put("procDt", this.procDt);
        ssmParamAccountRelationReadyUpdate.put("rgtnStsCdPendingCompletion", RGTN_STS.PENDING_COMPLETION);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getAccountRelationUpdateReadyList", ssmParamAccountRelationReadyUpdate, execParam);

            rsSet = stmt.executeQuery();
            int commitCount = 0;
            while (rsSet.next()) {
                this.totalCount++;

                BigDecimal dsAcctRelnPk = rsSet.getBigDecimal("DS_ACCT_RELN_PK");

                DS_ACCT_RELNTMsg updateTMsgDsAcctReln = new DS_ACCT_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(updateTMsgDsAcctReln.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsgDsAcctReln.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(updateTMsgDsAcctReln.dsAcctRelnPk, dsAcctRelnPk);
                updateTMsgDsAcctReln = (DS_ACCT_RELNTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgDsAcctReln);
                if (updateTMsgDsAcctReln != null) {
                    ZYPEZDItemValueSetter.setValue(updateTMsgDsAcctReln.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                    S21FastTBLAccessor.update(updateTMsgDsAcctReln);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgDsAcctReln.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"DS_ACCT_RELN" });
                    }
                }
                commitCount++;
                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }
            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }
    // 2016/09/08 QC#3569 Add End
    /**
     * 
     */
    private void updateBillToCustReady() {
        int commitCount = 0;
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamBillToCustReadyUpdate = new HashMap<String, String>();

        ssmParamBillToCustReadyUpdate.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamBillToCustReadyUpdate.put("procDt", this.procDt);
        ssmParamBillToCustReadyUpdate.put("rgtnStsCdPendingCompletion", RGTN_STS.PENDING_COMPLETION);
        ssmParamBillToCustReadyUpdate.put("rgtnStsCdReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getBillToCustUpdateReadyList", ssmParamBillToCustReadyUpdate, execParam);

            rsSet = stmt.executeQuery();

            while (rsSet.next()) {

                BigDecimal billToCustPk = rsSet.getBigDecimal("BILL_TO_CUST_PK");

                this.totalCount++;

                BILL_TO_CUSTTMsg updateTMsgBillToCust = new BILL_TO_CUSTTMsg();

                ZYPEZDItemValueSetter.setValue(updateTMsgBillToCust.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsgBillToCust.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(updateTMsgBillToCust.billToCustPk, billToCustPk);
                updateTMsgBillToCust = (BILL_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgBillToCust);
                if (updateTMsgBillToCust != null) {
                    ZYPEZDItemValueSetter.setValue(updateTMsgBillToCust.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                    S21FastTBLAccessor.update(updateTMsgBillToCust);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgBillToCust.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"BILL_TO_CUST" });
                    }
                }
                commitCount++;
                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }
            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

    }

    /**
     * 
     */
    private void updateBillToCustTerminated() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamBillToCustTerminatedUpdate = new HashMap<String, String>();

        ssmParamBillToCustTerminatedUpdate.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamBillToCustTerminatedUpdate.put("procDt", this.procDt);
        ssmParamBillToCustTerminatedUpdate.put("rgtnStsCdPendingCompletion", RGTN_STS.PENDING_COMPLETION); // 2016/09/08 QC#3569 Add
        ssmParamBillToCustTerminatedUpdate.put("rgtnStsCdReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);

        // 2022/04/28 QC#59983 Add Start
        boolean skipFlg = false;
        // 2022/04/28 QC#59983 Add End

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getBillToCustUpdateTerminatedList", ssmParamBillToCustTerminatedUpdate, execParam);

            rsSet = stmt.executeQuery();
            int commitCount = 0;
            // 2022/04/28 QC#59983 Add Start
            int skipCount = 0;
            // 2022/04/28 QC#59983 Add End
            while (rsSet.next()) {
                this.totalCount++;

                // 2022/04/28 QC#59983 Add Start
                skipFlg = selectOpenTrxWhereCustomer(rsSet, skipFlg, LOC_ROLE_TP.BILL_TO);
                if (skipFlg) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("BILL_TO_CUST_CD", rsSet.getString("BILL_TO_CUST_CD"));
                    map.put("BILL_TO_CUST_NM", rsSet.getString("BILL_TO_CUST_NM"));
                    skipCustomerList.add(map);
                    skipCount++;
                    this.totalNmlCount += skipCount;
                    skipCount = 0;
                    continue;
                }
                // 2022/04/28 QC#59983 Add End

                BigDecimal billToCustPk = rsSet.getBigDecimal("BILL_TO_CUST_PK");

                BILL_TO_CUSTTMsg updateTMsgBillToCust = new BILL_TO_CUSTTMsg();

                ZYPEZDItemValueSetter.setValue(updateTMsgBillToCust.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsgBillToCust.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(updateTMsgBillToCust.billToCustPk, billToCustPk);
                updateTMsgBillToCust = (BILL_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgBillToCust);
                if (updateTMsgBillToCust != null) {
                    ZYPEZDItemValueSetter.setValue(updateTMsgBillToCust.rgtnStsCd, RGTN_STS.TERMINATED);
                    S21FastTBLAccessor.update(updateTMsgBillToCust);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgBillToCust.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"BILL_TO_CUST" });
                    }
                }

                commitCount++;
                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }

            // 2022/04/28 QC#59983 Add Start
            if (skipCustomerList.size() > 0) {
                postSkipMail(LOC_ROLE_TP.BILL_TO);
                skipCustomerList.clear();
            }
            // 2022/04/28 QC#59983 Add End

            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    // 2022/04/28 QC#59983 Add Start
    /**
     * Returns true if BILL_TO_CUST_CD or SHIP_TO_CUST_CD is associated with an open transaction
     * @param rsSet ResultSet
     * @param skipFlg boolean
     * @param locRoleTp String
     * @return boolean
     */
    private boolean selectOpenTrxWhereCustomer(ResultSet rsSet, boolean skipFlg, String locRoleTp) {

        PreparedStatement stmtOpenTrx = null;
        ResultSet rsSetOpenTrx = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        String customerCd = null;

        try {

            Map<String, String> ssmParamCustomerSelectOpenTrx = new HashMap<String, String>();
            ssmParamCustomerSelectOpenTrx.put("glblCmpyCd", this.glblCmpyCd);
            ssmParamCustomerSelectOpenTrx.put("openFlg", ZYPConstant.FLG_ON_Y);
            ssmParamCustomerSelectOpenTrx.put("svcTaskStsClosed", SVC_TASK_STS.CLOSED);
            ssmParamCustomerSelectOpenTrx.put("svcTaskStsCancelled", SVC_TASK_STS.CANCELLED);
            ssmParamCustomerSelectOpenTrx.put("dsContrCtrlStsTerminated", DS_CONTR_CTRL_STS.TERMINATED);
            ssmParamCustomerSelectOpenTrx.put("dsContrCtrlStsDraft", DS_CONTR_CTRL_STS.DRAFT);
            ssmParamCustomerSelectOpenTrx.put("dsContrCtrlStsCancelled", DS_CONTR_CTRL_STS.CANCELLED);
            ssmParamCustomerSelectOpenTrx.put("flgN", ZYPConstant.FLG_OFF_N);
            ssmParamCustomerSelectOpenTrx.put("flgY", ZYPConstant.FLG_ON_Y);
            ssmParamCustomerSelectOpenTrx.put("w4i", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
            ssmParamCustomerSelectOpenTrx.put("instl", SVC_MACH_MSTR_STS.INSTALLED);
            ssmParamCustomerSelectOpenTrx.put("w4r", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
            ssmParamCustomerSelectOpenTrx.put("dlrsv", SVC_MACH_MSTR_STS.DEALER_SERVICE);
            ssmParamCustomerSelectOpenTrx.put("svcMachTpMachine", SVC_MACH_TP.MACHINE);

            if (LOC_ROLE_TP.SHIP_TO.equals(locRoleTp)) {
                customerCd = rsSet.getString("SHIP_TO_CUST_CD");
                ssmParamCustomerSelectOpenTrx.put("shipToCustCd", customerCd);
            } else if (LOC_ROLE_TP.BILL_TO.equals(locRoleTp)) {
                customerCd = rsSet.getString("BILL_TO_CUST_CD");
                ssmParamCustomerSelectOpenTrx.put("billToCustCd", customerCd);
            } else {
                return false;
            }

            stmtOpenTrx = this.ssmLLClient.createPreparedStatement("getCustomerSelectOpenTrx", ssmParamCustomerSelectOpenTrx, execParam);
            rsSetOpenTrx = stmtOpenTrx.executeQuery();

            while (rsSetOpenTrx.next()) {

                if (BigDecimal.ZERO.compareTo(rsSetOpenTrx.getBigDecimal("COUNT")) != 0) {
                    // Skip and Mail Send
                    skipFlg =  true;
                } else {
                    skipFlg =  false;
                }
            }

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtOpenTrx, rsSetOpenTrx);
        }
        return skipFlg;
    }

    /**
     * Inserts entry in ML_SEND
     * @param locRoleTp String
     * @return
     */
    private void postSkipMail(String locRoleTp) {
        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_KEY_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException("NMAM8028E", new String[] {MAIL_TYPE_FROM, MAIL_GROUP_ID_FROM, MAIL_KEY_FROM });
        }
        S21MailAddress mailAddrFrom = addrFromList.get(0);

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> mailAddrToList = groupTo.getMailAddress();
        if (mailAddrToList == null || mailAddrToList.isEmpty()) {
            throw new S21AbendException("NMAM8028E", new String[] {MAIL_TYPE_TO, MAIL_GROUP_ID_TO, HYPHEN });
        }

        // Get Template
        S21MailTemplate maiTemplate = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!ZYPCommonFunc.hasValue(maiTemplate.getBody())) {
            throw new S21AbendException("NMAM8031E", new String[] {MAIL_TEMPLATE_ID });
        }

        String skipMsg = addNoSuggrtlInf(locRoleTp);

        maiTemplate.setTemplateParameter(MAIL_FIELD_ERR_MSG, skipMsg);
        maiTemplate.setTemplateParameter(MAIL_FIELD_BAT_ID, BATCH_PROGRAM_ID);
        maiTemplate.setTemplateParameter(MAIL_FIELD_BAT_NM, BATCH_PROGRAM_NAME);

        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(mailAddrFrom);
        mail.setToAddressList(mailAddrToList);
        mail.setMailTemplate(maiTemplate);
        mail.setSubject(maiTemplate.getSubject(), MAIL_CHARSET);
        mail.postMail();

    }

    /**
     * Formatting email body in ML_SEND
     * @param locRoleTp String
     * @return
     */
    private String addNoSuggrtlInf(String locRoleTp) {
        StringBuilder skipMsg = new StringBuilder();

        if (LOC_ROLE_TP.SHIP_TO.equals(locRoleTp)) {
            skipMsg.append(String.format("%-21s", "Ship To Cust"));
            skipMsg.append(String.format("%-31s", "Ship To Cust Name"));
            skipMsg.append(CRLF);
            for (Map<String, String> skipShipTo : skipCustomerList) {
                skipMsg.append("    ").append(String.format("%-21s", skipShipTo.get("SHIP_TO_CUST_CD")));
                skipMsg.append(String.format("%-31s", skipShipTo.get("SHIP_TO_CUST_NM")));
                skipMsg.append(CRLF);
            }
        } else if (LOC_ROLE_TP.BILL_TO.equals(locRoleTp)) {
            skipMsg.append(String.format("%-21s", "Bill To Cust"));
            skipMsg.append(String.format("%-31s", "Bill To Cust Name"));
            skipMsg.append(CRLF);
            for (Map<String, String> skipBillTo : skipCustomerList) {
                skipMsg.append("    ").append(String.format("%-21s", skipBillTo.get("BILL_TO_CUST_CD")));
                skipMsg.append(String.format("%-31s", skipBillTo.get("BILL_TO_CUST_NM")));
                skipMsg.append(CRLF);
            }
        }

        return skipMsg.toString();
    }
    // 2022/04/28 QC#59983 Add End

    /**
     * Update DS_SHIP_TO_CUST For Delete Related Bill To
     */
    private void updateDsShipToCustDeleteRelatedBillTo() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamDsShipToCustUpdate = new HashMap<String, String>();

        ssmParamDsShipToCustUpdate.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamDsShipToCustUpdate.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getUpdateDsShipToCustList", ssmParamDsShipToCustUpdate, execParam);

            rsSet = stmt.executeQuery();
            int commitCount = 0;
            while (rsSet.next()) {
                this.totalCount++;

                BigDecimal shipToCustPk = rsSet.getBigDecimal("SHIP_TO_CUST_PK");

                SHIP_TO_CUSTTMsg updateTMsgDsShipToCust = new SHIP_TO_CUSTTMsg();

                ZYPEZDItemValueSetter.setValue(updateTMsgDsShipToCust.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsgDsShipToCust.shipToCustPk, shipToCustPk);
                updateTMsgDsShipToCust = (SHIP_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgDsShipToCust);
                if (updateTMsgDsShipToCust != null) {
                    updateTMsgDsShipToCust.relnBillToCustCd.clear();
                    S21FastTBLAccessor.update(updateTMsgDsShipToCust);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgDsShipToCust.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"SHIP_TO_CUST" });
                    }
                }

                commitCount++;
                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }
            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    /**
     * Update DS_CUST_TRX_RULE For Delete Default Bill To
     */
    private void updateDsCustTrxRuleDeleteDefBillTo() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamDsShipToCustUpdate = new HashMap<String, String>();

        ssmParamDsShipToCustUpdate.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamDsShipToCustUpdate.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getUpdateDsCustTrxRuleForDeleteDefBillToList", ssmParamDsShipToCustUpdate, execParam);

            rsSet = stmt.executeQuery();
            int commitCount = 0;
            while (rsSet.next()) {
                this.totalCount++;

                BigDecimal dsCustTrxRulePk = rsSet.getBigDecimal("DS_CUST_TRX_RULE_PK");

                DS_CUST_TRX_RULETMsg updateTMsgDsCustTrxRule = new DS_CUST_TRX_RULETMsg();

                ZYPEZDItemValueSetter.setValue(updateTMsgDsCustTrxRule.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsgDsCustTrxRule.dsCustTrxRulePk, dsCustTrxRulePk);
                updateTMsgDsCustTrxRule = (DS_CUST_TRX_RULETMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgDsCustTrxRule);
                if (updateTMsgDsCustTrxRule != null) {
                    updateTMsgDsCustTrxRule.dsDefBillToCd.clear();
                    S21FastTBLAccessor.update(updateTMsgDsCustTrxRule);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgDsCustTrxRule.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"DS_CUST_TRX_RULE" });
                    }
                }

                commitCount++;
                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }
            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    /**
     * 
     */
    private void updateShipToCustReady() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamShipToCustReadyUpdate = new HashMap<String, String>();

        ssmParamShipToCustReadyUpdate.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamShipToCustReadyUpdate.put("procDt", this.procDt);
        ssmParamShipToCustReadyUpdate.put("rgtnStsCdPendingCompletion", RGTN_STS.PENDING_COMPLETION);
        ssmParamShipToCustReadyUpdate.put("rgtnStsCdReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
        try {
            stmt = this.ssmLLClient.createPreparedStatement("getShipToCustUpdateReadyList", ssmParamShipToCustReadyUpdate, execParam);
            rsSet = stmt.executeQuery();
            int commitCount = 0;
            while (rsSet.next()) {
                this.totalCount++;
                BigDecimal shipToCustPk = rsSet.getBigDecimal("SHIP_TO_CUST_PK");
                SHIP_TO_CUSTTMsg updateTMsgShipToCust = new SHIP_TO_CUSTTMsg();
                ZYPEZDItemValueSetter.setValue(updateTMsgShipToCust.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsgShipToCust.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(updateTMsgShipToCust.shipToCustPk, shipToCustPk);
                updateTMsgShipToCust = (SHIP_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgShipToCust);
                if (updateTMsgShipToCust != null) {
                    ZYPEZDItemValueSetter.setValue(updateTMsgShipToCust.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                    S21FastTBLAccessor.update(updateTMsgShipToCust);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgShipToCust.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"SHIP_TO_CUST" });
                    }
                }
                commitCount++;
                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }
            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    /**
     * 
     */
    private void updateShipToCustTerminated() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamShipToCustTerminatedUpdate = new HashMap<String, String>();
        ssmParamShipToCustTerminatedUpdate.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamShipToCustTerminatedUpdate.put("procDt", this.procDt);
        ssmParamShipToCustTerminatedUpdate.put("rgtnStsCdPendingCompletion", RGTN_STS.PENDING_COMPLETION); // 2016/09/08 QC#3569 Add
        ssmParamShipToCustTerminatedUpdate.put("rgtnStsCdReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);

        // 2022/04/28 QC#59983 Add Start
        boolean skipFlg = false;
        // 2022/04/28 QC#59983 Add End

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getShipToCustUpdateTerminatedList", ssmParamShipToCustTerminatedUpdate, execParam);

            rsSet = stmt.executeQuery();
            int commitCount = 0;
            // 2022/04/28 QC#59983 Add Start
            int skipCount = 0;
            // 2022/04/28 QC#59983 Add End
            while (rsSet.next()) {
                this.totalCount++;

                // 2022/04/28 QC#59983 Add Start
                skipFlg = selectOpenTrxWhereCustomer(rsSet, skipFlg, LOC_ROLE_TP.SHIP_TO);
                if (skipFlg) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("SHIP_TO_CUST_CD", rsSet.getString("SHIP_TO_CUST_CD"));
                    map.put("SHIP_TO_CUST_NM", rsSet.getString("SHIP_TO_CUST_NM"));
                    skipCustomerList.add(map);
                    skipCount++;
                    this.totalNmlCount += skipCount;
                    skipCount = 0;
                    continue;
                }
                // 2022/04/28 QC#59983 Add End

                BigDecimal shipToCustPk = rsSet.getBigDecimal("SHIP_TO_CUST_PK");
                SHIP_TO_CUSTTMsg updateTMsgShipToCust = new SHIP_TO_CUSTTMsg();

                ZYPEZDItemValueSetter.setValue(updateTMsgShipToCust.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsgShipToCust.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(updateTMsgShipToCust.shipToCustPk, shipToCustPk);
                updateTMsgShipToCust = (SHIP_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgShipToCust);
                if (updateTMsgShipToCust != null) {
                    ZYPEZDItemValueSetter.setValue(updateTMsgShipToCust.rgtnStsCd, RGTN_STS.TERMINATED);
                    S21FastTBLAccessor.update(updateTMsgShipToCust);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgShipToCust.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"SHIP_TO_CUST" });
                    }
                }
                commitCount++;
                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }

            // 2022/04/28 QC#59983 Add Start
            if (skipCustomerList.size() > 0) {
                postSkipMail(LOC_ROLE_TP.SHIP_TO);
                skipCustomerList.clear();
            }
            // 2022/04/28 QC#59983 Add End

            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    /**
     * Update DS_CUST_TRX_RULE For Delete Default Ship To
     */
    private void updateDsCustTrxRuleDeleteDefShipTo() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamDsShipToCustUpdate = new HashMap<String, String>();

        ssmParamDsShipToCustUpdate.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamDsShipToCustUpdate.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getUpdateDsCustTrxRuleForDeleteDefShipToList", ssmParamDsShipToCustUpdate, execParam);

            rsSet = stmt.executeQuery();
            int commitCount = 0;
            while (rsSet.next()) {
                this.totalCount++;

                BigDecimal dsCustTrxRulePk = rsSet.getBigDecimal("DS_CUST_TRX_RULE_PK");

                DS_CUST_TRX_RULETMsg updateTMsgDsCustTrxRule = new DS_CUST_TRX_RULETMsg();

                ZYPEZDItemValueSetter.setValue(updateTMsgDsCustTrxRule.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsgDsCustTrxRule.dsCustTrxRulePk, dsCustTrxRulePk);
                updateTMsgDsCustTrxRule = (DS_CUST_TRX_RULETMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgDsCustTrxRule);
                if (updateTMsgDsCustTrxRule != null) {
                    updateTMsgDsCustTrxRule.dsDefShipToCd.clear();
                    S21FastTBLAccessor.update(updateTMsgDsCustTrxRule);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgDsCustTrxRule.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"DS_CUST_TRX_RULE" });
                    }
                }

                commitCount++;
                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }
            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    // 2016/09/08 QC#3569 Add Start
    /**
     * 
     */
    private void updatePtyLocWrkReady() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamUpdatePtyLocWrkReadyList = new HashMap<String, String>();
        ssmParamUpdatePtyLocWrkReadyList.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamUpdatePtyLocWrkReadyList.put("procDt", this.procDt);
        ssmParamUpdatePtyLocWrkReadyList.put("rgtnStsCdPendingCompletion", RGTN_STS.PENDING_COMPLETION);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getUpdatePtyLocWrkReadyList", ssmParamUpdatePtyLocWrkReadyList, execParam);

            rsSet = stmt.executeQuery();
            int commitCount = 0;
            while (rsSet.next()) {
                this.totalCount++;

                BigDecimal ptyLocPk = rsSet.getBigDecimal("PTY_LOC_PK");

                PTY_LOC_WRKTMsg updatePtyLocWrk = new PTY_LOC_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(updatePtyLocWrk.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updatePtyLocWrk.ptyLocPk, ptyLocPk);
                updatePtyLocWrk = (PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(updatePtyLocWrk);
                if (updatePtyLocWrk != null) {
                    ZYPEZDItemValueSetter.setValue(updatePtyLocWrk.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                    S21FastTBLAccessor.update(updatePtyLocWrk);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updatePtyLocWrk.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"PTY_LOC_WRK" });
                    }
                }
                commitCount++;
                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }
            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }
    /**
     * 
     */
    private void updatePtyLocWrkTerminated() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamUpdatePtyLocWrkTerminatedList = new HashMap<String, String>();
        ssmParamUpdatePtyLocWrkTerminatedList.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamUpdatePtyLocWrkTerminatedList.put("procDt", this.procDt);
        ssmParamUpdatePtyLocWrkTerminatedList.put("rgtnStsCdPendingCompletion", RGTN_STS.PENDING_COMPLETION);
        ssmParamUpdatePtyLocWrkTerminatedList.put("rgtnStsCdReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getUpdatePtyLocWrkTerminatedList", ssmParamUpdatePtyLocWrkTerminatedList, execParam);

            rsSet = stmt.executeQuery();
            int commitCount = 0;
            while (rsSet.next()) {
                this.totalCount++;
                BigDecimal ptyLocPk = rsSet.getBigDecimal("PTY_LOC_PK");
                PTY_LOC_WRKTMsg updatePtyLocWrk = new PTY_LOC_WRKTMsg();

                ZYPEZDItemValueSetter.setValue(updatePtyLocWrk.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updatePtyLocWrk.ptyLocPk, ptyLocPk);
                updatePtyLocWrk = (PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(updatePtyLocWrk);
                if (updatePtyLocWrk != null) {
                    ZYPEZDItemValueSetter.setValue(updatePtyLocWrk.rgtnStsCd, RGTN_STS.TERMINATED);
                    S21FastTBLAccessor.update(updatePtyLocWrk);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updatePtyLocWrk.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"PTY_LOC_WRK" });
                    }
                }
                commitCount++;
                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }
            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }
    // 2016/09/08 QC#3569 Add End

    /**
     * 
     */
    private void updateAccountRelationTerminated() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamAccountRelationTerminateedUpdate = new HashMap<String, String>();
        ssmParamAccountRelationTerminateedUpdate.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamAccountRelationTerminateedUpdate.put("procDt", this.procDt);
        ssmParamAccountRelationTerminateedUpdate.put("rgtnStsCdPendingCompletion", RGTN_STS.PENDING_COMPLETION); // 2016/09/08 QC#3569 Add
        ssmParamAccountRelationTerminateedUpdate.put("rgtnStsCdReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getAccountRelationUpdateTerminatedList", ssmParamAccountRelationTerminateedUpdate, execParam);

            rsSet = stmt.executeQuery();
            int commitCount = 0;
            while (rsSet.next()) {
                this.totalCount++;
                BigDecimal dsAcctRelnPk = rsSet.getBigDecimal("DS_ACCT_RELN_PK");
                DS_ACCT_RELNTMsg updateTMsgDsAcctReln = new DS_ACCT_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(updateTMsgDsAcctReln.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsgDsAcctReln.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(updateTMsgDsAcctReln.dsAcctRelnPk, dsAcctRelnPk);
                updateTMsgDsAcctReln = (DS_ACCT_RELNTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgDsAcctReln);
                if (updateTMsgDsAcctReln != null) {
                    ZYPEZDItemValueSetter.setValue(updateTMsgDsAcctReln.rgtnStsCd, RGTN_STS.TERMINATED);
                    S21FastTBLAccessor.update(updateTMsgDsAcctReln);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgDsAcctReln.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"DS_ACCT_RELN" });
                    }
                }
                commitCount++;
                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }
            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    /**
     * 
     */
    private void updateContactPoints() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamContactPoints = new HashMap<String, String>();

        ssmParamContactPoints.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamContactPoints.put("procDt", this.procDt);
        ssmParamContactPoints.put("dsCtacPntActvFlg", ZYPConstant.FLG_ON_Y);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getContactPointsList", ssmParamContactPoints, execParam);

            rsSet = stmt.executeQuery();
            int commitCount = 0;
            while (rsSet.next()) {
                this.totalCount++;

                BigDecimal dsCtacPntPk = rsSet.getBigDecimal("DS_CTAC_PNT_PK");
                DS_CTAC_PNTTMsg updateTMsgContactPoints = new DS_CTAC_PNTTMsg();
                ZYPEZDItemValueSetter.setValue(updateTMsgContactPoints.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsgContactPoints.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(updateTMsgContactPoints.dsCtacPntPk, dsCtacPntPk);
                updateTMsgContactPoints = (DS_CTAC_PNTTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgContactPoints);
                if (updateTMsgContactPoints != null) {
                    ZYPEZDItemValueSetter.setValue(updateTMsgContactPoints.dsCtacPntActvFlg, ZYPConstant.FLG_OFF_N);
                    S21FastTBLAccessor.update(updateTMsgContactPoints);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgContactPoints.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"DS_CTAC_PNT" });
                    }
                }
                commitCount++;
                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }
            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    /**
     * 
     */
    private void updateVendorMaster() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamVnd = new HashMap<String, String>();
        ssmParamVnd.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamVnd.put("procDt", this.procDt);
        ssmParamVnd.put("rgtnStsCdReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getVndList", ssmParamVnd, execParam);

            rsSet = stmt.executeQuery();
            int commitCount = 0;
            while (rsSet.next()) {

                this.totalCount++;

                BigDecimal vndPk = rsSet.getBigDecimal("VND_PK");
                String locNum = rsSet.getString("LOC_NUM");

                VNDTMsg updateTMsgVnd = new VNDTMsg();
                ZYPEZDItemValueSetter.setValue(updateTMsgVnd.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsgVnd.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(updateTMsgVnd.vndPk, vndPk);
                updateTMsgVnd = (VNDTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgVnd);
                if (updateTMsgVnd != null) {
                    ZYPEZDItemValueSetter.setValue(updateTMsgVnd.rgtnStsCd, RGTN_STS.TERMINATED);
                    S21FastTBLAccessor.update(updateTMsgVnd);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgVnd.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"VND" });
                    }
                }

                if (ZYPCommonFunc.hasValue(locNum)) {
                    updatePtyLocWrk(locNum);
                    updateShipToCust(locNum);
                }

                commitCount++;
                if (commitCount == this.commitUnit) {
                    super.commit();
                    this.totalNmlCount += commitCount;
                    commitCount = 0;
                }
            }
            if (commitCount > 0) {
                super.commit();
                this.totalNmlCount += commitCount;
                commitCount = 0;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    /**
     * @param locNum
     */
    private void updatePtyLocWrk(String locNum) {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamPtyLocWrk = new HashMap<String, String>();
        ssmParamPtyLocWrk.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamPtyLocWrk.put("locNum", locNum);
        ssmParamPtyLocWrk.put("rgtnStsCdReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPtyLocWrkList", ssmParamPtyLocWrk, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {
                BigDecimal ptyLocPk = rsSet.getBigDecimal("PTY_LOC_PK");
                PTY_LOC_WRKTMsg updateTMsgPtyLocWrk = new PTY_LOC_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(updateTMsgPtyLocWrk.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsgPtyLocWrk.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(updateTMsgPtyLocWrk.ptyLocPk, ptyLocPk);
                updateTMsgPtyLocWrk = (PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgPtyLocWrk);
                if (updateTMsgPtyLocWrk != null) {
                    ZYPEZDItemValueSetter.setValue(updateTMsgPtyLocWrk.rgtnStsCd, RGTN_STS.TERMINATED);
                    S21FastTBLAccessor.update(updateTMsgPtyLocWrk);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgPtyLocWrk.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"PTY_LOC_WRK" });
                    }
                }
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    /**
     * @param locNum
     */
    private void updateShipToCust(String locNum) {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitUnit);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> ssmParamShipToCust = new HashMap<String, String>();
        ssmParamShipToCust.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamShipToCust.put("locNum", locNum);
        ssmParamShipToCust.put("rgtnStsCdReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getShipToCustList", ssmParamShipToCust, execParam);

            rsSet = stmt.executeQuery();
            while (rsSet.next()) {

                BigDecimal shipToCustPk = rsSet.getBigDecimal("SHIP_TO_CUST_PK");
                SHIP_TO_CUSTTMsg updateTMsgShipToCust = new SHIP_TO_CUSTTMsg();
                ZYPEZDItemValueSetter.setValue(updateTMsgShipToCust.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsgShipToCust.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(updateTMsgShipToCust.ptyLocPk, shipToCustPk);
                updateTMsgShipToCust = (SHIP_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgShipToCust);
                if (updateTMsgShipToCust != null) {
                    ZYPEZDItemValueSetter.setValue(updateTMsgShipToCust.rgtnStsCd, RGTN_STS.TERMINATED);
                    S21FastTBLAccessor.update(updateTMsgShipToCust);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsgShipToCust.getReturnCode())) {
                        this.totalErrCount++;
                        super.rollback();
                        throw new S21AbendException(MMAM0004E, new String[] {"SHIP_TO_CUST" });
                    }
                }
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    @Override
    protected void termRoutine() {
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB561001().executeBatch(NMAB561001.class.getSimpleName());
    }
    
    // QC#9448
    private void switchAccountCoa(String locNum, String newDsAcctNum) {
        SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();
        tMsg.setSQLID("501");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("sellToCustCd01", newDsAcctNum);
        SELL_TO_CUSTTMsgArray tMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        for (int i = 0; i < tMsgArray.length(); i++) {
            tMsg = tMsgArray.no(i);
            String coaAfflCd = tMsg.coaAfflCd.getValue();
            String coaChCd = tMsg.coaChCd.getValue();
            switchAccountCoaBillTo(locNum, coaAfflCd, coaChCd);
            switchAccountCoaShipTo(locNum, coaAfflCd, coaChCd);
        }
    }

    private void switchAccountCoaBillTo(String locNum, String coaAfflCd, String coaChCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("locNum", locNum);
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getBillToByLocNum", ssmParam);
        for (Map<String, Object> map : list) {
            BigDecimal billToCustPk = (BigDecimal) map.get("BILL_TO_CUST_PK");

            
            // update BILL_TO_CUST
            BILL_TO_CUSTTMsg btcMsg = new BILL_TO_CUSTTMsg();
            btcMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            btcMsg.billToCustPk.setValue(billToCustPk);
            btcMsg = (BILL_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdate(btcMsg);
            if (btcMsg != null) {

                ZYPEZDItemValueSetter.setValue(btcMsg.coaAfflCd, coaAfflCd);
                ZYPEZDItemValueSetter.setValue(btcMsg.coaChCd, coaChCd);

                S21FastTBLAccessor.update(btcMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(btcMsg.getReturnCode())) {
                    this.totalErrCount++;
                    super.rollback();
                    S21InfoLogOutput.println("switchAccountCoaBillTo failed. billToCustPk: [" + billToCustPk + "]");
                    throw new S21AbendException(MMAM0004E, new String[] {"BILL_TO_CUST" });
                }
            }
        }
    }

    private void switchAccountCoaShipTo(String locNum, String coaAfflCd, String coaChCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("locNum", locNum);
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getShipToByLocNum", ssmParam);
        for (Map<String, Object> map : list) {
            BigDecimal shipToCustPk = (BigDecimal) map.get("SHIP_TO_CUST_PK");

            SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
            tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            tMsg.shipToCustPk.setValue(shipToCustPk);
            tMsg = (SHIP_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
            if (tMsg != null) {

                ZYPEZDItemValueSetter.setValue(tMsg.coaAfflCd, coaAfflCd);
                ZYPEZDItemValueSetter.setValue(tMsg.coaChCd, coaChCd);

                S21FastTBLAccessor.update(tMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    this.totalErrCount++;
                    super.rollback();
                    S21InfoLogOutput.println("switchAccountCoaShipTo failed. shipToCustPk: [" + shipToCustPk + "]");
                    throw new S21AbendException(MMAM0004E, new String[] {"DS_SHIP_TO_CUST" });
                }
            }
        }
    }

}
