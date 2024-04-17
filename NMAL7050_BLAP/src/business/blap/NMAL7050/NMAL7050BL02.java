/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */

package business.blap.NMAL7050;

import static business.blap.NMAL7050.constant.NMAL7050Constant.CSV_FILE_NAME;
import static business.blap.NMAL7050.constant.NMAL7050Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL7050.constant.NMAL7050Constant.NMAM0007I;
import static business.blap.NMAL7050.constant.NMAL7050Constant.NZZM0001W;
import static business.blap.NMAL7050.constant.NMAL7050Constant.ZZZM9003I;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7050.common.NMAL7050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Fujitsu         W.Honda         Create          N/A
 * 2017/02/15   Fujitsu         R.Nakamura      Update          QC#17529
 *</pre>
 */
public class NMAL7050BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL7050_INIT".equals(screenAplID)) {
                doProcess_NMAL7050_INIT((NMAL7050CMsg) cMsg, (NMAL7050SMsg) sMsg);

            } else if ("NMAL7050Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7050Scrn00_CMN_Clear((NMAL7050CMsg) cMsg, (NMAL7050SMsg) sMsg);

            } else if ("NMAL7050Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL7050Scrn00_PageNext((NMAL7050CMsg) cMsg, (NMAL7050SMsg) sMsg);

            } else if ("NMAL7050Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL7050Scrn00_PagePrev((NMAL7050CMsg) cMsg, (NMAL7050SMsg) sMsg);

            } else if ("NMAL7050Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL7050Scrn00_PageJump((NMAL7050CMsg) cMsg, (NMAL7050SMsg) sMsg);

            } else if ("NMAL7050Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7050Scrn00_Search((NMAL7050CMsg) cMsg, (NMAL7050SMsg) sMsg);

            // Add Start 2017/02/15 QC#17529
            } else if ("NMAL7050Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7050Scrn00_CMN_Download((NMAL7050CMsg) cMsg, (NMAL7050SMsg) sMsg);
            // Add End 2017/02/15 QC#17529

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NMAL7050_INIT(NMAL7050CMsg cMsg, NMAL7050SMsg sMsg) {
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
    }

    private void doProcess_NMAL7050Scrn00_CMN_Clear(NMAL7050CMsg cMsg, NMAL7050SMsg sMsg) {
        doProcess_NMAL7050_INIT(cMsg, sMsg);
    }

    /**
     * doProcess_NMAL7050Scrn00_PageNext
     * @param cMsg NMAL7050CMsg
     * @param sMsg NMAL7050SMsg
     */
    private void doProcess_NMAL7050Scrn00_PageNext(NMAL7050CMsg cMsg, NMAL7050SMsg sMsg) {
        NMAL7050CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);
        ZYPTableUtil.clear(cMsg.A);
        NMAL7050CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowToNum.getValueInt());
    }

    /**
     * doProcess_NMAL7050Scrn00_PagePrev
     * @param cMsg NMAL7050CMsg
     * @param sMsg NMAL7050SMsg
     */
    private void doProcess_NMAL7050Scrn00_PagePrev(NMAL7050CMsg cMsg, NMAL7050SMsg sMsg) {
        NMAL7050CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);
        ZYPTableUtil.clear(cMsg.A);
        NMAL7050CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1);

    }

    /**
     * doProcess_NMAL7050Scrn00_PageJump
     * @param cMsg NMAL7050CMsg
     * @param sMsg NMAL7050SMsg
     */
    private void doProcess_NMAL7050Scrn00_PageJump(NMAL7050CMsg cMsg, NMAL7050SMsg sMsg) {
        NMAL7050CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);
        ZYPTableUtil.clear(cMsg.A);
        NMAL7050CommonLogic.pagenation(cMsg, sMsg, cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1));
    }

    private void doProcess_NMAL7050Scrn00_Search(NMAL7050CMsg cMsg, NMAL7050SMsg sMsg) {

        S21SsmEZDResult ssmResult = NMAL7050Query.getInstance().getServiceMeterPackageDetail(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            NMAL7050CommonLogic.setSearchResultToGlblMsg(ssmResult, sMsg, cMsg, getGlobalCompanyCode());

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }
            //Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);
            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
            cMsg.setMessageInfo(ZZZM9003I, new String[]{"Search"});
        } else {
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.setMessageInfo(NMAM0007I);
        }
    }

    // Add Start 2017/02/15 QC#17529
    /**
     * doProcess_NMAL7050Scrn00_CMN_Download
     * @param cMsg NMAL7050CMsg
     * @param sMsg NMAL7050SMsg
     */
    private void doProcess_NMAL7050Scrn00_CMN_Download(NMAL7050CMsg cMsg, NMAL7050SMsg sMsg) {

        cMsg.xxFileData.clear();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL7050Query.getInstance().getClass());

            // create csv file, parameters
            Map<String, Object> ssmParam = null;
            String ssmId = "getServiceMeterPackageDetail";
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");
            ssmParam = NMAL7050CommonLogic.getServiceMeterPackageParam(cMsg, getGlobalCompanyCode());
            ps = ssmLLClient.createPreparedStatement(ssmId, ssmParam, execParam);
            rs = ps.executeQuery();

            NMAL7050CommonLogic.writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

    }
    // Add End 2017/02/15 QC#17529
}

