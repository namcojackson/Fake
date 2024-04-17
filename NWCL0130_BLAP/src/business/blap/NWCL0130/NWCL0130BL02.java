package business.blap.NWCL0130;

import static business.blap.NWCL0130.constant.NWCL0130Constant.COMMA;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWCL0130.common.NWCL0130CommonLogic;
import business.blap.NWCL0130.constant.NWCL0130Constant;
import business.db.CONSL_RGNR_ACT_TPTMsg;
import business.file.NWCL0130F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_RGNR_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2016   SRAA            K.Aratani       Create          
 * 2016/11/21   Fujitsu         M.Ohno          Update          S21_NA#16082
 *</pre>
 */
public class NWCL0130BL02 extends S21BusinessHandler implements NWCL0130Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        NWCL0130CMsg bizMsg = (NWCL0130CMsg) cMsg;
        String scrnAplID = bizMsg.getScreenAplID();
        try {
            if ("NWCL0130_INIT".equals(scrnAplID)) {
                doProcess_NWCL0130_INIT(bizMsg, (NWCL0130SMsg) sMsg);
            } else if ("NWCL0130Scrn00_Search".equals(scrnAplID)) {
                doProcess_NWCL0130_Search(bizMsg, (NWCL0130SMsg) sMsg);
            } else if ("NWCL0130Scrn00_TBLColumnSort".equals(scrnAplID)) {
                doProcess_NWCL0130_TBLColumnSort(bizMsg, (NWCL0130SMsg) sMsg);
            } else if ("NWCL0130Scrn00_Add".equals(scrnAplID)) {
                doProcess_NWCL0130_Add(bizMsg, (NWCL0130SMsg) sMsg);
            } else if ("NWCL0130Scrn00_AddToList".equals(scrnAplID)) {
                doProcess_NWCL0130_AddToList(bizMsg, (NWCL0130SMsg) sMsg);
            } else if ("NWCL0130Scrn00_Del".equals(scrnAplID)) {
                doProcess_NWCL0130_Del(bizMsg, (NWCL0130SMsg) sMsg);
            } else if ("NWCL0130Scrn00_Drop".equals(scrnAplID)) {
                doProcess_NWCL0130_Drop(bizMsg, (NWCL0130SMsg) sMsg);
            } else if ("NWCL0130Scrn00_Retransmit".equals(scrnAplID)) {
                doProcess_NWCL0130_Retransmit(bizMsg, (NWCL0130SMsg) sMsg);
            } else if ("NWCL0130Scrn00_CMN_Clear".equals(scrnAplID)) {
                doProcess_NWCL0130_CLEAR(bizMsg, (NWCL0130SMsg) sMsg);
            } else if ("NWCL0130Scrn00_CMN_Reset".equals(scrnAplID)) {
                doProcess_NWCL0130_RESET(bizMsg, (NWCL0130SMsg) sMsg);
            } else if ("NWCL0130Scrn00_Click_SelAll_UnSelAll_Checkbox".equals(scrnAplID)) {
                doProcess_NWCL0130_Click_SelAll_UnSelAll_Checkbox(bizMsg, (NWCL0130SMsg) sMsg);
            } else if ("NWCL0130Scrn00_PagePrev".equals(scrnAplID)) {
                doProcess_NWCL0130_PagePrev(bizMsg, (NWCL0130SMsg) sMsg);
            } else if ("NWCL0130Scrn00_PageNext".equals(scrnAplID)) {
                doProcess_NWCL0130_PageNext(bizMsg, (NWCL0130SMsg) sMsg);
            } else if ("NWCL0130Scrn00_PageJump".equals(scrnAplID)) {
                doProcess_NWCL0130_PageJump(bizMsg, (NWCL0130SMsg) sMsg);
            } else if ("NWCL0130Scrn00_CMN_Download".equals(scrnAplID)) {
                doProcess_NWCL0130_CMN_Download(bizMsg, (NWCL0130SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // Add Start 2016/11/24 M.Ohno S21_NA#16082
    private void doProcess_NWCL0130_CMN_Download(NWCL0130CMsg bizMsg, NWCL0130SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NWCL0130Query.getInstance().getClass());

            // create csv file, parameters
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_FILE_EXTENSION);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("rowNum", sMsg.A.length() + 1);
            ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
            ssmParam.put("conslRgnrProcCd", "00"); // un process

            ps = ssmLLClient.createPreparedStatement("search", ssmParam, execParam);

            rs = ps.executeQuery();
            writeCsvFile(bizMsg, rs);
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

    }

    /**
     * Download Event
     * @param cMsg NWCL0130CMsg
     * @param ResultSet ResultSet
     */
    private void writeCsvFile(NWCL0130CMsg cMsg, ResultSet rs) throws SQLException {

        NWCL0130F00FMsg fMsg = new NWCL0130F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        // write header
        csvOutFile.writeHeader(CSV_HDR_DOWNLOAD, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (!rs.next()) {
            csvOutFile.close();
            return;
        }

        // write contents
        do {

            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd, rs.getString("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.billToDsAcctNum, rs.getString("BILL_TO_CUST_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.billToDsAcctNm, rs.getString("BILL_TO_CUST_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.conslBillNum, rs.getString("CONSL_BILL_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.invNum, rs.getString("INV_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.custCareTktNum, rs.getString("CUST_CARE_TKT_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxUrnNum, rs.getString("URN_NUMBER"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt, rs.getString("INV_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.invTotDealNetAmt, rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.conslRgnrActTpNm, rs.getString("CONSL_RGNR_ACT_TP_NM"));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }
    // Add End   2016/11/24 M.Ohno S21_NA#16082

    private void doProcess_NWCL0130_INIT(NWCL0130CMsg bizMsg, NWCL0130SMsg sMsg) {

        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(bizMsg.A);

        //Action Regeneration Type
        String pullDownValue = ZYPCodeDataUtil.getVarCharConstValue(NWCL0130_PULLDOEN_ACT_RGRN_TP, getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(pullDownValue)) {
            return;
        }
        
        String[] recPerPageArray = pullDownValue.split(COMMA);

        for (int i = 0; i < recPerPageArray.length; i++) {
            CONSL_RGNR_ACT_TPTMsg tm = new CONSL_RGNR_ACT_TPTMsg();
            tm.glblCmpyCd.setValue(getGlobalCompanyCode());
            tm.conslRgnrActTpCd.setValue(recPerPageArray[i]);
            tm = (CONSL_RGNR_ACT_TPTMsg)EZDTBLAccessor.findByKey(tm);
            if (tm != null) {
                bizMsg.conslRgnrActTpCd_PL.no(i).setValue(tm.conslRgnrActTpCd.getValue());
                bizMsg.conslRgnrActTpDescTxt_PL.no(i).setValue(tm.conslRgnrActTpDescTxt.getValue());
            }
        }
        
        // Search
        NWCL0130CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode());

    }

    private void doProcess_NWCL0130_Search(NWCL0130CMsg bizMsg, NWCL0130SMsg sMsg) {
        // Search
        NWCL0130CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode());
    }

    private void doProcess_NWCL0130_TBLColumnSort(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm_01.getValue();
        String sortItemNm = cMsg.xxSortItemNm_01.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt_01.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(cMsg.A, cMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, cMsg.A.getValidCount());
        }
    }

    private void doProcess_NWCL0130_AddToList(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg) {

        // Save from cMsg to sMsg
        NWCL0130CommonLogic.copyFromCMsgToSMsg(cMsg, sMsg, false);
        
        if (CONSL_RGNR_ACT_TP.ADD.equals(cMsg.conslRgnrActTpCd_H1.getValue())) {
            doProcess_NWCL0130_Add(cMsg, sMsg);
        } else if (CONSL_RGNR_ACT_TP.DROP.equals(cMsg.conslRgnrActTpCd_H1.getValue())) {
            doProcess_NWCL0130_Drop(cMsg, sMsg);
        } else if (CONSL_RGNR_ACT_TP.RETRANSMIT.equals(cMsg.conslRgnrActTpCd_H1.getValue())) {
            doProcess_NWCL0130_Retransmit(cMsg, sMsg);
        }

    }
    
    
    @SuppressWarnings("unchecked")
    private void doProcess_NWCL0130_Add(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg) {

        // Save from cMsg to sMsg
        NWCL0130CommonLogic.copyFromCMsgToSMsg(cMsg, sMsg, false);

        if (NWCL0130CommonLogic.checAddDropTransmit(cMsg, sMsg, getGlobalCompanyCode(), CONSL_RGNR_ACT_TP.ADD)) {
            return;
        }
        ZYPTableUtil.clear(cMsg.A);
        S21SsmEZDResult result = NWCL0130Query.getInstance().getInvInfo(getGlobalCompanyCode(), cMsg.invNum_H1.getValue());
        Map<String, Object> invMap = (Map<String, Object>) result.getResultObject();

        int count = sMsg.A.getValidCount() + 1;
        sMsg.A.setValidCount(count);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).billToCustCd_A1, (String) invMap.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).billToDsAcctNum_A1, (String) invMap.get("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).billToDsAcctNm_A1, (String) invMap.get("BILL_TO_CUST_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).conslBillNum_A1, cMsg.conslBillNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).invNum_A1, cMsg.invNum_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).custCareTktNum_A1, (String) invMap.get("CUST_CARE_TKT_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).xxUrnNum_A1, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).invDt_A1, (String) invMap.get("INV_DT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).invTotDealNetAmt_A1, (BigDecimal) invMap.get("INV_TOT_DEAL_NET_AMT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).conslRgnrActTpCd_A1, CONSL_RGNR_ACT_TP.ADD);
        CONSL_RGNR_ACT_TPTMsg msg = new CONSL_RGNR_ACT_TPTMsg();
        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(msg.conslRgnrActTpCd, CONSL_RGNR_ACT_TP.ADD);
        msg = (CONSL_RGNR_ACT_TPTMsg) S21CacheTBLAccessor.findByKey(msg);
        if (msg != null) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).conslRgnrActTpNm_A1, msg.conslRgnrActTpNm.getValue());
        }

        S21SsmEZDResult resultOrig = NWCL0130Query.getInstance().getOriginalConslBillForAdd(getGlobalCompanyCode(), cMsg.invNum_H1.getValue());
        if (resultOrig.isCodeNormal()) {
            Map<String, Object> invOrigMap = (Map<String, Object>) resultOrig.getResultObject();
            result = NWCL0130Query.getInstance().getInvInfo(getGlobalCompanyCode(), cMsg.invNum_H1.getValue());
            invMap = (Map<String, Object>) result.getResultObject();
            count = sMsg.A.getValidCount() + 1;
            sMsg.A.setValidCount(count);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).billToCustCd_A1, (String) invMap.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).billToDsAcctNum_A1, (String) invMap.get("BILL_TO_CUST_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).billToDsAcctNm_A1, (String) invMap.get("BILL_TO_CUST_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).conslBillNum_A1, (String) invOrigMap.get("CONSL_BILL_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).invNum_A1, cMsg.invNum_H1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).custCareTktNum_A1, (String) invMap.get("CUST_CARE_TKT_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).xxUrnNum_A1, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).invDt_A1, (String) invMap.get("INV_DT"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).invTotDealNetAmt_A1, (BigDecimal) invMap.get("INV_TOT_DEAL_NET_AMT"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).conslRgnrActTpCd_A1, CONSL_RGNR_ACT_TP.DROP);
            msg = new CONSL_RGNR_ACT_TPTMsg();
            ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(msg.conslRgnrActTpCd, CONSL_RGNR_ACT_TP.DROP);
            msg = (CONSL_RGNR_ACT_TPTMsg) S21CacheTBLAccessor.findByKey(msg);
            if (msg != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).conslRgnrActTpNm_A1, msg.conslRgnrActTpNm.getValue());
            }
        }

        // Add Record
        int specificIdx = sMsg.A.getValidCount() - 1;
        cMsg.xxPageShowOfNum_10.setValue(sMsg.A.getValidCount());
        int page = specificIdx / cMsg.A.length();
        int pageFrom = page * cMsg.A.length();
        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum_10.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_10.setValue(pageFrom + cMsg.A.getValidCount());

    }

    @SuppressWarnings("unchecked")
    private void doProcess_NWCL0130_Drop(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg) {

        // Save from cMsg to sMsg
        NWCL0130CommonLogic.copyFromCMsgToSMsg(cMsg, sMsg, false);

        if (NWCL0130CommonLogic.checAddDropTransmit(cMsg, sMsg, getGlobalCompanyCode(), CONSL_RGNR_ACT_TP.DROP)) {
            return;
        }
        ZYPTableUtil.clear(cMsg.A);
        S21SsmEZDResult result = NWCL0130Query.getInstance().getInvInfo(getGlobalCompanyCode(), cMsg.invNum_H1.getValue());
        Map<String, Object> invMap = (Map<String, Object>) result.getResultObject();

        int count = sMsg.A.getValidCount() + 1;
        sMsg.A.setValidCount(count);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).billToCustCd_A1, (String) invMap.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).billToDsAcctNum_A1, (String) invMap.get("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).billToDsAcctNm_A1, (String) invMap.get("BILL_TO_CUST_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).conslBillNum_A1, cMsg.conslBillNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).invNum_A1, cMsg.invNum_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).custCareTktNum_A1, (String) invMap.get("CUST_CARE_TKT_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).xxUrnNum_A1, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).invDt_A1, (String) invMap.get("INV_DT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).invTotDealNetAmt_A1, (BigDecimal) invMap.get("INV_TOT_DEAL_NET_AMT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).conslRgnrActTpCd_A1, CONSL_RGNR_ACT_TP.DROP);
        CONSL_RGNR_ACT_TPTMsg msg = new CONSL_RGNR_ACT_TPTMsg();
        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(msg.conslRgnrActTpCd, CONSL_RGNR_ACT_TP.DROP);
        msg = (CONSL_RGNR_ACT_TPTMsg) S21CacheTBLAccessor.findByKey(msg);
        if (msg != null) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).conslRgnrActTpNm_A1, msg.conslRgnrActTpNm.getValue());
        }
        // Add Record
        int specificIdx = sMsg.A.getValidCount() - 1;
        cMsg.xxPageShowOfNum_10.setValue(sMsg.A.getValidCount());
        int page = specificIdx / cMsg.A.length();
        int pageFrom = page * cMsg.A.length();
        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum_10.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_10.setValue(pageFrom + cMsg.A.getValidCount());
    }

    @SuppressWarnings("unchecked")
    private void doProcess_NWCL0130_Retransmit(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg) {

        // Save from cMsg to sMsg
        NWCL0130CommonLogic.copyFromCMsgToSMsg(cMsg, sMsg, false);

        if (NWCL0130CommonLogic.checAddDropTransmit(cMsg, sMsg, getGlobalCompanyCode(), CONSL_RGNR_ACT_TP.RETRANSMIT)) {
            return;
        }
        ZYPTableUtil.clear(cMsg.A);
        S21SsmEZDResult result = NWCL0130Query.getInstance().getInvInfo(getGlobalCompanyCode(), cMsg.invNum_H1.getValue());
        Map<String, Object> invMap = (Map<String, Object>) result.getResultObject();

        int count = sMsg.A.getValidCount() + 1;
        sMsg.A.setValidCount(count);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).billToCustCd_A1, (String) invMap.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).billToDsAcctNum_A1, (String) invMap.get("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).billToDsAcctNm_A1, (String) invMap.get("BILL_TO_CUST_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).conslBillNum_A1, cMsg.conslBillNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).invNum_A1, cMsg.invNum_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).custCareTktNum_A1, (String) invMap.get("CUST_CARE_TKT_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).xxUrnNum_A1, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).invDt_A1, (String) invMap.get("INV_DT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).invTotDealNetAmt_A1, (BigDecimal) invMap.get("INV_TOT_DEAL_NET_AMT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).conslRgnrActTpCd_A1, CONSL_RGNR_ACT_TP.RETRANSMIT);
        CONSL_RGNR_ACT_TPTMsg msg = new CONSL_RGNR_ACT_TPTMsg();
        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(msg.conslRgnrActTpCd, CONSL_RGNR_ACT_TP.RETRANSMIT);
        msg = (CONSL_RGNR_ACT_TPTMsg) S21CacheTBLAccessor.findByKey(msg);
        if (msg != null) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(count - 1).conslRgnrActTpNm_A1, msg.conslRgnrActTpNm.getValue());
        }
        // Add Record
        int specificIdx = sMsg.A.getValidCount() - 1;
        cMsg.xxPageShowOfNum_10.setValue(sMsg.A.getValidCount());
        int page = specificIdx / cMsg.A.length();
        int pageFrom = page * cMsg.A.length();
        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum_10.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_10.setValue(pageFrom + cMsg.A.getValidCount());
    }

    private void doProcess_NWCL0130_Del(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg) {

        // Save from cMsg to sMsg
        NWCL0130CommonLogic.copyFromCMsgToSMsg(cMsg, sMsg, true);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        if (selectedRows.isEmpty()) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                // NMAM8054E=0,Please select item(s).
                cMsg.setMessageInfo("NMAM8054E");
                return;
            }
        } else {

            List<Integer> editSelectedRows = new java.util.ArrayList<Integer>();
            for (Integer integer : selectedRows) {
                NWCL0130_ASMsg delLine = sMsg.A.no(integer.intValue());
                for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                    NWCL0130_ASMsg line = sMsg.A.no(i);
                    if (delLine.invNum_A1.getValue().equals(line.invNum_A1.getValue())) {
                        editSelectedRows.add(new Integer(i));
                    }
                }
            }
            ZYPTableUtil.deleteRows(sMsg.A, editSelectedRows);

            int specificIdx = sMsg.A.getValidCount() - 1;
            cMsg.xxPageShowOfNum_10.setValue(sMsg.A.getValidCount());
            int page = specificIdx / cMsg.A.length();
            int pageFrom = page * cMsg.A.length();
            int i = pageFrom;
            for (; i < pageFrom + cMsg.A.length(); i++) {
                if (i < sMsg.A.getValidCount()) {
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
                } else {
                    break;
                }
            }
            cMsg.A.setValidCount(i - pageFrom);
            cMsg.xxPageShowFromNum_10.setValue(pageFrom + 1);
            cMsg.xxPageShowToNum_10.setValue(pageFrom + cMsg.A.getValidCount());

        }
    }

    private void doProcess_NWCL0130_CLEAR(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);

        // Search Filter
        cMsg.conslBillNum_H1.clear();
        cMsg.invNum_H1.clear();
    }

    private void doProcess_NWCL0130_RESET(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg) {
        doProcess_NWCL0130_CLEAR(cMsg, sMsg);
    }

    private void doProcess_NWCL0130_PageNext(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg) {

        // Save from cMsg to sMsg
        NWCL0130CommonLogic.copyFromCMsgToSMsg(cMsg, sMsg, true);

        cMsg.xxPageShowFromNum_10.setValue(cMsg.xxPageShowToNum_10.getValueInt());
        cMsg.xxPageShowToNum_10.clear();

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to page items
        cMsg.xxPageShowFromNum_10.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_10.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    private void doProcess_NWCL0130_PagePrev(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg) {

        // Save from cMsg to sMsg
        NWCL0130CommonLogic.copyFromCMsgToSMsg(cMsg, sMsg, true);

        cMsg.xxPageShowFromNum_10.setValue(cMsg.xxPageShowFromNum_10.getValueInt() - cMsg.A.length() - 1);
        cMsg.xxPageShowToNum_10.clear();

        // copy data from SMsg to CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to page items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum_10.setValue(pagenationFrom);
        cMsg.xxPageShowToNum_10.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    private void doProcess_NWCL0130_PageJump(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg) {

        // Save from cMsg to sMsg
        NWCL0130CommonLogic.copyFromCMsgToSMsg(cMsg, sMsg, true);

        int nextFromNum = (cMsg.xxPageShowCurNum_10.getValueInt() - 1) * cMsg.A.length();
        cMsg.xxPageShowFromNum_10.setValue(nextFromNum);
        cMsg.xxPageShowToNum_10.clear();

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to page items
        cMsg.xxPageShowFromNum_10.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_10.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    private void doProcess_NWCL0130_Click_SelAll_UnSelAll_Checkbox(NWCL0130CMsg cMsg, NWCL0130SMsg sMsg) {

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (FLG_ON_Y.equals(cMsg.xxChkBox_AL.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_A1, FLG_ON_Y);
            } else {
                cMsg.A.no(i).xxChkBox_A1.clear();
            }
        }
    }

}
