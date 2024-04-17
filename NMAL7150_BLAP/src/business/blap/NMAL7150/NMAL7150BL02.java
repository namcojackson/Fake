/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7150;

import static business.blap.NMAL7150.constant.NMAL7150Constant.CHK_BOX_A;
import static business.blap.NMAL7150.constant.NMAL7150Constant.CSV_EXTENSION;
import static business.blap.NMAL7150.constant.NMAL7150Constant.CSV_FILE_NAME;
import static business.blap.NMAL7150.constant.NMAL7150Constant.CSV_HDR;
import static business.blap.NMAL7150.constant.NMAL7150Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL7150.constant.NMAL7150Constant.MESSAGE_KIND_ERR;
import static business.blap.NMAL7150.constant.NMAL7150Constant.MESSAGE_KIND_WARN;
import static business.blap.NMAL7150.constant.NMAL7150Constant.NMAM0007I;
import static business.blap.NMAL7150.constant.NMAL7150Constant.NMAM8054E;
import static business.blap.NMAL7150.constant.NMAL7150Constant.NMAM8518I;
import static business.blap.NMAL7150.constant.NMAL7150Constant.SEARCH_ROW_NUM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7150.common.NMAL7150CommonLogic;
import business.db.CSMP_PROC_STSTMsg;
import business.file.NMAL7150F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * CSMP Contract Synchronization  Errors Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7150BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7150CMsg bizMsg = (NMAL7150CMsg) cMsg;
            NMAL7150SMsg glblMsg = (NMAL7150SMsg) sMsg;

            if ("NMAL7150Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7150Scrn00_Search(bizMsg, glblMsg, false);
            } else if ("NMAL7150Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL7150Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NMAL7150Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL7150Scrn00_PagePrev(bizMsg, glblMsg);
            } else if ("NMAL7150Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NMAL7150Scrn00_SelectAll(bizMsg, glblMsg);
            } else if ("NMAL7150Scrn00_UnSelectAll".equals(screenAplID)) {
                doProcess_NMAL7150Scrn00_UnSelectAll(bizMsg, glblMsg);
            } else if ("NMAL7150Scrn00_Delete".equals(screenAplID)) {
                doProcess_NMAL7150Scrn00_Delete(bizMsg, glblMsg);
            } else if ("NMAL7150Scrn00_Reprocess".equals(screenAplID)) {
                doProcess_NMAL7150Scrn00_Reprocess(bizMsg, glblMsg);
            } else if ("NMAL7150Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7150Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else if ("NMAL7150Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7150Scrn00_CMN_Download(bizMsg, glblMsg);
            } else if ("NMAL7150Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7150Scrn00_CMN_Clear(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NMAL7150Scrn00_Search(NMAL7150CMsg bizMsg, NMAL7150SMsg glblMsg, boolean isSubmit) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        S21SsmEZDResult ssmResult = NMAL7150Query.getInstance().getCsmpContrIntfc(bizMsg, glblMsg);
        if (ssmResult.isCodeNotFound() && !isSubmit) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NMAM0007I);
            return;
        }
        if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
            glblMsg.A.setValidCount(glblMsg.A.length());
            if (!isSubmit) {
                bizMsg.setMessageInfo(NMAM8518I);
            }
        } else {
            glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
        }

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        NMAL7150CommonLogic.setSeachResult(resultList, glblMsg);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NMAL7150_ASMsg glblLineMsg = glblMsg.A.no(i);

            if (i < bizMsg.A.length()) {

                EZDMsg.copy(glblLineMsg, null, bizMsg.A.no(i), null);
                bizMsg.A.setValidCount(i + 1);
            } else {
                break;
            }
        }

        bizMsg.xxPageShowFromNum.setValue(1);
        bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7150Scrn00_PageNext(NMAL7150CMsg bizMsg, NMAL7150SMsg glblMsg) {
        NMAL7150CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL7150CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7150Scrn00_PagePrev(NMAL7150CMsg bizMsg, NMAL7150SMsg glblMsg) {
        NMAL7150CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL7150CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * SelectAll Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7150Scrn00_SelectAll(NMAL7150CMsg bizMsg, NMAL7150SMsg glblMsg) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NMAL7150_ACMsg bizLineMsg = bizMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(bizLineMsg.xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
        }
    }

    /**
     * UnSelectAll Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7150Scrn00_UnSelectAll(NMAL7150CMsg bizMsg, NMAL7150SMsg glblMsg) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NMAL7150_ACMsg bizLineMsg = bizMsg.A.no(i);
            bizLineMsg.xxChkBox_A.clear();
        }
    }

    /**
     * Delete Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7150Scrn00_Delete(NMAL7150CMsg bizMsg, NMAL7150SMsg glblMsg) {
        NMAL7150CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.A, CHK_BOX_A, ZYPConstant.FLG_ON_Y);
        if ( selectedRows.isEmpty() ) {
            //NMAM8054E=0,Please select item(s).
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NMAL7150_ASMsg glblLineMsg = glblMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(glblLineMsg.xxChkBox_A)) {
                CSMP_PROC_STSTMsg inTMsg = new CSMP_PROC_STSTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inTMsg.csmpProcStsCd, CSMP_PROC_STS.DELETED);
                inTMsg = (CSMP_PROC_STSTMsg) S21CodeTableAccessor.findByKey(inTMsg);

                ZYPEZDItemValueSetter.setValue(glblLineMsg.csmpProcStsCd_A, CSMP_PROC_STS.DELETED);
                String processFlag = CSMP_PROC_STS.DELETED + ":" + inTMsg.csmpProcStsDescTxt.getValue();
                ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem54Txt_A, processFlag);
            }
        }
        NMAL7150CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * Reprocess Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7150Scrn00_Reprocess(NMAL7150CMsg bizMsg, NMAL7150SMsg glblMsg) {
        NMAL7150CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.A, CHK_BOX_A, ZYPConstant.FLG_ON_Y);
        if ( selectedRows.isEmpty() ) {
            //NMAM8054E=0,Please select item(s).
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NMAL7150_ASMsg glblLineMsg = glblMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(glblLineMsg.xxChkBox_A)) {
                CSMP_PROC_STSTMsg inTMsg = new CSMP_PROC_STSTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inTMsg.csmpProcStsCd, CSMP_PROC_STS.REPROCESS);
                inTMsg = (CSMP_PROC_STSTMsg) S21CodeTableAccessor.findByKey(inTMsg);

                ZYPEZDItemValueSetter.setValue(glblLineMsg.csmpProcStsCd_A, CSMP_PROC_STS.REPROCESS);
                String processFlag = CSMP_PROC_STS.REPROCESS + ":" + inTMsg.csmpProcStsDescTxt.getValue();
                ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem54Txt_A, processFlag);
            }
        }
        NMAL7150CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7150Scrn00_CMN_Submit(NMAL7150CMsg bizMsg, NMAL7150SMsg glblMsg) {
        if (MESSAGE_KIND_WARN.equals(bizMsg.getMessageKind()) //
                || MESSAGE_KIND_ERR.equals(bizMsg.getMessageKind())) {
            return;
        }

        doProcess_NMAL7150Scrn00_Search(bizMsg, glblMsg, true);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7150Scrn00_CMN_Download(NMAL7150CMsg bizMsg, NMAL7150SMsg glblMsg) {
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_EXTENSION);
        NMAL7150F00FMsg fMsg = new NMAL7150F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        // write header
        csvOutFile.writeHeader(CSV_HDR);

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL7150Query.getInstance().getClass());

            Map<String, Object> queryParam = getSerchParam(bizMsg, glblMsg);
            ps = ssmLLClient.createPreparedStatement("getCsmpContrIntfc", queryParam, execParam);
            rs = ps.executeQuery();

            if (!rs.next()) {
                bizMsg.setMessageInfo(NMAM0007I, null);
                csvOutFile.close();
                return;
            }

            // write contents
            do {
                if (rs.getRow() >= SEARCH_ROW_NUM) {
                    bizMsg.setMessageInfo(NMAM8518I, null);
                    break;
                }
                NMAL7150CommonLogic.setSeachResultForDownload(rs, fMsg);
                csvOutFile.write();
                fMsg.clear();
            } while (rs.next());

            csvOutFile.close();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private Map<String, Object> getSerchParam(NMAL7150CMsg bizMsg, NMAL7150SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dateFrom", bizMsg.effFromDt_TD.getValue());
        params.put("dateTo", bizMsg.effThruDt_TD.getValue());
        List<String> flagList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_E)) {
            flagList.add(CSMP_PROC_STS.ERROR);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_R)) {
            flagList.add(CSMP_PROC_STS.REPROCESS);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_D)) {
            flagList.add(CSMP_PROC_STS.DELETED);
        }
        if (flagList != null) {
            String[] flagCd = (String[]) flagList.toArray(new String[0]);
            params.put("flagCd", flagCd);
        } else {
            params.put("flagCd", null);
        }
        params.put("rowNum", SEARCH_ROW_NUM);
        return params;
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7150Scrn00_CMN_Clear(NMAL7150CMsg bizMsg, NMAL7150SMsg glblMsg) {
        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
    }
}
