/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0040;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSBL0040.constant.NSBL0040Constant;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.FSRTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_TASKTMsg;
import business.db.TECH_MSTRTMsg;
import business.db.TECH_MSTRTMsgArray;
import business.file.NSBL0040F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

//import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

/**
 * Credit Approval
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/01   SRA             Otsuji          Create          N/A
 * 2013/09/10   SRA             Yanada          Update          QC2131
 * 2014/01/17   FUJITSU         T.Tanaka        update          QC3336 set fetch size
 * 2017/09/01   Hitachi         K.Kim           Update          QC#19896
 *</pre>
 */
public final class NSBL0040Query extends S21SsmEZDQuerySupport implements NSBL0040Constant {

    /**
     * Instance
     */
    private static final NSBL0040Query INSTANCE = new NSBL0040Query();

    private NSBL0040Query() {
    }

    /**
     * Get instance
     * @return NSBL0040Query
     */
    public static NSBL0040Query getInstance() {
        return INSTANCE;
    }

    /**
     * Get hold list.
     * @param cMsg NSBL0040CMsg
     */
    public void getHoldList(NSBL0040CMsg cMsg) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("billToCustCd", cMsg.billToCustCd.getValue());
        param.put("sellToCustCd", cMsg.sellToCustCd.getValue());
        param.put("shipToCustCd", cMsg.shipToCustCd.getValue());
        //MOD START 11/19/2015 for CSA
        //param.put("svcTaskNum", cMsg.svcTaskNum.getValue());
        param.put("fsrNum", cMsg.fsrNum.getValue());
        // START 2017/09/01 K.Kim [QC#19896, DEL]
        //param.put("hldExstFlg", FLG_ON_Y);
        // END 2017/09/01 K.Kim [QC#19896, DEL]
        //MOD START 11/19/2015 for CSA
        //MOD END 11/19/2015 for CSA
        param.put("techCd", cMsg.techCd.getValue());
        param.put("ezInUserID", cMsg.ezInUserID.getValue());
        param.put("svcBillTpCd", cMsg.svcBillTpCd_01.getValue());
        param.put("svcTaskStsCd", SVC_TASK_STS.WAITING_FOR_CREDIT_APPROVAL);
        param.put("svcMemoCatgCd", SVC_MEMO_CATG.DISPATCH_MEMO);
        param.put("svcMemoTpCd", SVC_MEMO_TP.CREDIT);
        param.put("rowNum", MAX_CSV_ROWS + 1);
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_REVERSE);
        execParam.setResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        PreparedStatement ps = null;
        ResultSet rs = null;
        ZYPCSVOutFile csv = null;

        try {
            ps = S21SsmLowLevelCodingClient.getClient(NSBL0040Query.class).createPreparedStatement("getHoldList", param, execParam);
            rs = ps.executeQuery();

            if (!rs.next()) {
                cMsg.setMessageInfo(NZZM0000E);
                return;
            }

            BigDecimal rowCount = rs.getBigDecimal("CNT");
            if (new BigDecimal(MAX_CSV_ROWS).compareTo(rowCount) < 0) {
                cMsg.setMessageInfo(NZZM0007E);
                return;
            }

            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("WaitingForCreditApproval"), ".csv");

            NSBL0040F00FMsg fMsg = new NSBL0040F00FMsg();
            csv = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
            csv.writeHeader(HEADER);

            rs.beforeFirst();

            while (rs.next()) {
                fMsg.clear();
                ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd, rs.getString("BILL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.locNm, rs.getString("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.fsrNum, rs.getString("FSR_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.pmtTermCashDiscCd, rs.getString("PMT_TERM_CASH_DISC_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.pmtTermCashDiscNm, rs.getString("PMT_TERM_CASH_DISC_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.techCd, rs.getString("TECH_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.origInvCcyCd, rs.getString("ORIG_INV_CCY_CD"));
                BigDecimal svcLborDealAmt = rs.getBigDecimal("SVC_LBOR_DEAL_AMT");
                if (svcLborDealAmt != null) {
                    svcLborDealAmt = svcLborDealAmt.setScale(0, BigDecimal.ROUND_DOWN);
                }
                ZYPEZDItemValueSetter.setValue(fMsg.svcLborDealAmt, svcLborDealAmt);
                ZYPEZDItemValueSetter.setValue(fMsg.ezInUserID, rs.getString("EZINUSERID"));
                ZYPEZDItemValueSetter.setValue(fMsg.hrTtlNm, rs.getString("HR_TTL_NM"));
                csv.write();
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
            if (csv != null) {
                csv.close();
            }
        }
    }

    /**
     * Get hold list.
     * @param cMsg NSBL0040CMsg
     * @param sMsg NSBL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHoldList(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {
        // TODO HOW TO DERIVE TOC_CD FROM TECH_CD
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("billToCustCd", cMsg.billToCustCd.getValue());
        param.put("sellToCustCd", cMsg.sellToCustCd.getValue());
        param.put("shipToCustCd", cMsg.shipToCustCd.getValue());
        //MOD START 11/19/2015 for CSA
        //param.put("svcTaskNum", cMsg.svcTaskNum.getValue());
        param.put("fsrNum", cMsg.fsrNum.getValue());
        // START 2017/09/01 K.Kim [QC#19896, DEL]
        //param.put("hldExstFlg", FLG_ON_Y);
        // START 2017/09/01 K.Kim [QC#19896, DEL]
        //MOD END 11/19/2015 for CSA
        param.put("techCd", cMsg.techCd.getValue());
        param.put("ezInUserID", cMsg.ezInUserID.getValue());
        param.put("svcBillTpCd", cMsg.svcBillTpCd_01.getValue());
        param.put("svcTaskStsCd", SVC_TASK_STS.WAITING_FOR_CREDIT_APPROVAL);
        param.put("svcMemoCatgCd", SVC_MEMO_CATG.DISPATCH_MEMO);
        param.put("svcMemoTpCd", SVC_MEMO_TP.CREDIT);
        param.put("rowNum", getMaxRowNum(sMsg) + 1);
        return getSsmEZDClient().queryEZDMsgArray("getHoldList", param, sMsg.A);
    }

    private int getMaxRowNum(NSBL0040SMsg sMsg) {
        return Integer.parseInt(sMsg.getBaseContents()[0][3]);
    }

    /**
     * Get service task hold count
     * @param glblCmpyCd String
     * @param svcTaskNum String
     * @return int
     */
    public int getHoldCnt(String glblCmpyCd, String svcTaskNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcTaskNum", svcTaskNum);
        param.put("svcMemoCatgCd", SVC_MEMO_CATG.DISPATCH_MEMO);
        param.put("svcMemoTpCd", SVC_MEMO_TP.CREDIT);
        BigDecimal holdCnt = (BigDecimal) getSsmEZDClient().queryObject("getHoldCnt", param).getResultObject();
        return holdCnt.intValueExact();
    }

    /**
     * Get bill to location name
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @return String
     */
    public String getBillToLocNm(String glblCmpyCd, String billToCustCd) {
        BILL_TO_CUSTTMsg tMsg = new BILL_TO_CUSTTMsg();
        tMsg.setSQLID("019");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("billToCustCd01", billToCustCd);
        BILL_TO_CUSTTMsgArray tMsgs = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgs.length() == 0) {
            return null;
        } else {
            return tMsgs.no(0).locNm.getValue();
        }
    }

    /**
     * Get sell to location name
     * @param glblCmpyCd String
     * @param sellToCustCd String
     * @return String
     */
    public String getSellToLocNm(String glblCmpyCd, String sellToCustCd) {
        SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();
        tMsg.setSQLID("100");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("sellToCustCd01", sellToCustCd);
        SELL_TO_CUSTTMsgArray tMsgs = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgs.length() == 0) {
            return null;
        } else {
            return tMsgs.no(0).locNm.getValue();
        }
    }

    /**
     * Get ship to location name
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return String
     */
    public String getShipToLocNm(String glblCmpyCd, String shipToCustCd) {
        SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray tMsgs = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgs.length() == 0) {
            return null;
        } else {
            return tMsgs.no(0).locNm.getValue();
        }
    }

    /**
     * Get service task for update
     * @param glblCmpyCd String
     * @param svcTaskNum String
     * @return SVC_TASKTMsg
     */
    public SVC_TASKTMsg getSvcTaskForUpdate(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, svcTaskNum);
        return (SVC_TASKTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsg);
    }

    /**
     * Get technician master
     * @param glblCmpyCd String
     * @param techCd String
     * @return TECH_MSTRTMsg
     */
    public TECH_MSTRTMsg getTechMstr(String glblCmpyCd, String techCd) {
        TECH_MSTRTMsg tMsg = new TECH_MSTRTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("techTocCd01", techCd);
        TECH_MSTRTMsgArray tMsgs = (TECH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgs.length() == 0) {
            return null;
        } else {
            return tMsgs.no(0);
        }
    }

    // add start 2015/12/14 CSA Defect#895
    public FSRTMsg getFsrForUpdate(String glblCmpyCd, String fsrNum) {
        FSRTMsg tMsg = new FSRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, fsrNum);
        return (FSRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsg);
    }
    // add end 2015/12/14 CSA Defect#895

}
