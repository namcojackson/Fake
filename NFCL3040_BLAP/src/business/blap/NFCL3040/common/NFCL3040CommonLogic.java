package business.blap.NFCL3040.common;

import static business.blap.NFCL3040.constant.NFCL3040Constant.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NFCL3040.NFCL3040CMsg;
import business.blap.NFCL3040.NFCL3040Query;
import business.blap.NFCL3040.NFCL3040SMsg;
import business.file.NFCL3040F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * Batch Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/14   Hitachi         J.Kim           Create          QC#25695
 *</pre>
 */
public class NFCL3040CommonLogic {

    /**
     * Display Page
     * @param bizMsg NFCL3040CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NFCL3040CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {

        ZYPTableUtil.clear(bizMsgAry);

        if (bizMsg.xxPageTblNm.getValue().equals("A")) {
            int startIndex = bizMsg.xxPageShowFromNum.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum.getValueInt() < 0) {
                return;
            }
            for (; dispRowNum < bizMsgAry.length() && startIndex + dispRowNum < shareMsgAry.getValidCount(); dispRowNum++) {
                EZDMsg.copy(shareMsgAry.get(startIndex + dispRowNum), null, bizMsgAry.get(dispRowNum), null);
            }
            bizMsgAry.setValidCount(dispRowNum);
            bizMsg.xxPageShowToNum.setValue(startIndex + dispRowNum);
            bizMsg.xxPageShowOfNum.setValue(shareMsgAry.getValidCount());
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NFCL3040CMsg cMsg, NFCL3040SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    public static void searchBatchList(NFCL3040CMsg bizMsg, NFCL3040SMsg glblMsg) {

        Map<String, Object> ssmMap = setParams(bizMsg, glblMsg);
        ssmMap.put("rowNum", String.valueOf(glblMsg.A.length() + 1));

        S21SsmEZDResult ssmResult = NFCL3040Query.getInstance().searchBatchList(glblMsg, ssmMap);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > glblMsg.A.length()) {
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = glblMsg.A.length();
            } else {
                bizMsg.setMessageInfo("NZZM0002I");
            }

            int bizCnt = queryResCnt;
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                if (i < bizMsg.A.length()) {
                    EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);

                } else {
                    if (bizCnt == queryResCnt) {
                        bizCnt = i;
                    }
                }
            }

            bizMsg.A.setValidCount(bizCnt);

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {

            bizMsg.setMessageInfo("NZZM0000E");
            bizMsg.A.setValidCount(0);
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(0);
        }
    }

    private static HashMap<String, Object> setParams(NFCL3040CMsg bizMsg, NFCL3040SMsg glblMsg) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("bizMsg", bizMsg);
        if (ZYPCommonFunc.hasValue(bizMsg.dsBankAcctNm_H)) {
            params.put("dsBankAcctNm_H", bizMsg.dsBankAcctNm_H.getValue().toUpperCase());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsBankBrNm_H)) {
            params.put("dsBankBrNm_H", bizMsg.dsBankBrNm_H.getValue().toUpperCase());
        }

        return params;
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    public static void download(NFCL3040CMsg bizMsg, NFCL3040SMsg glblMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NFCL3040Query.getInstance().getClass());
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_DOWNLOAD), CSV);

            Map<String, Object> params = setParams(bizMsg, glblMsg);
            stmtSelect = ssmLLClient.createPreparedStatement("searchBatchList", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                bizMsg.setMessageInfo(NZZM0000E);
                return;
            }

            writeCsvFile(bizMsg, rs);
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * writeCsvFile
     * @param bizMsg NFCL3040CMsg
     * @param rs ResultSet
     * @exception SQLException
     */
    private static void writeCsvFile(NFCL3040CMsg bizMsg, ResultSet rs) throws SQLException {

        NFCL3040F00FMsg fMsg = new NFCL3040F00FMsg();

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

        // write header
        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        // write contents
        do {

            ZYPEZDItemValueSetter.setValue(fMsg.arBatRcptNm, rs.getString("AR_BAT_RCPT_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.arRcptSrcNm, rs.getString("AR_RCPT_SRC_NM"));
            String rcptDt = rs.getString("AR_BAT_RCPT_DT");
            if (rcptDt != null) {
                rcptDt = ZYPDateUtil.convertFormat(rcptDt, "yyyyMMdd", "MMddyyyy", ZYPDateUtil.SEPARATOR_SLASH);
            }
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt, rcptDt);
            ZYPEZDItemValueSetter.setValue(fMsg.arBatRcptStsNm, rs.getString("AR_BAT_RCPT_STS_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.arBatRcptCnt, rs.getBigDecimal("AR_BAT_RCPT_CNT"));
            ZYPEZDItemValueSetter.setValue(fMsg.arBatRcptAmt, rs.getBigDecimal("AR_BAT_RCPT_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.arLockBoxFileNm, rs.getString("AR_LOCK_BOX_FILE_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.arLockBoxNm, rs.getString("AR_LOCK_BOX_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.arLockBoxBatNum, rs.getString("AR_LOCK_BOX_BAT_NUM"));

            csvOutFile.write();

        } while (rs.next());

        csvOutFile.close();
    }

}
