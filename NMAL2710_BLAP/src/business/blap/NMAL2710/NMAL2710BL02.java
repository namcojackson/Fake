/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2710;

import static business.blap.NMAL2710.constant.NMAL2710Constant.CSV;
import static business.blap.NMAL2710.constant.NMAL2710Constant.CSV_FILE_NAME;
import static business.blap.NMAL2710.constant.NMAL2710Constant.FETCH_SIZE;
import static business.blap.NMAL2710.constant.NMAL2710Constant.MESSAGE_KIND_ERROR;
import static business.blap.NMAL2710.constant.NMAL2710Constant.MESSAGE_KIND_WARN;
import static business.blap.NMAL2710.constant.NMAL2710Constant.NMAM0007I;
import static business.blap.NMAL2710.constant.NMAL2710Constant.NMAM8182I;
import static business.blap.NMAL2710.constant.NMAL2710Constant.NMAM8518I;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL2710.common.NMAL2710CommonLogic;
import business.db.BIZ_AREA_ORGTMsg;
import business.db.BIZ_AREA_ORGTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
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
 * NMAL2710BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         W.Honda         Create          N/A
 * 2017/11/16   Fujitsu         N.Sugiura       Update          CSA-QC#20597
 *</pre>
 */
public class NMAL2710BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2710CMsg bizMsg = (NMAL2710CMsg) cMsg;

            if ("NMAL2710_INIT".equals(screenAplID)) {
                doProcess_NMAL2710_INIT(bizMsg);

            } else if ("NMAL2710Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2710Scrn00_CMN_Clear(bizMsg);

            } else if ("NMAL2710Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL2710Scrn00_CMN_Download(bizMsg);

            } else if ("NMAL2710Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2710Scrn00_CMN_Submit(bizMsg);

            } else if ("NMAL2710Scrn00_GetOrgCd".equals(screenAplID)) {
                doProcess_NMAL2710Scrn00_GetOrgCd(bizMsg);

            } else if ("NMAL2710Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2710Scrn00_Search(bizMsg);

            } else if ("NMAL2710Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL2710Scrn00_TBLColumnSort(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2710_INIT(NMAL2710CMsg bizMsg) {
        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);

        // Business Area
        BIZ_AREA_ORGTMsg inTMsg = new BIZ_AREA_ORGTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.ezCancelFlag, "0");
        ZYPEZDItemValueSetter.setValue(inTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
        ZYPEZDItemValueSetter.setValue(inTMsg.slsFlg, ZYPConstant.FLG_ON_Y);

        BIZ_AREA_ORGTMsgArray tMsgAry = (BIZ_AREA_ORGTMsgArray) ZYPCodeDataUtil.findByCondition(inTMsg);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "bizAreaOrgCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "bizAreaOrgDescTxt");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, bizMsg.bizAreaOrgCd_P, bizMsg.bizAreaOrgDescTxt_P);

        // Territory Group
        ZYPCodeDataUtil.createPulldownList(TRTY_GRP_TP.class, bizMsg.trtyGrpTpCd_P, bizMsg.trtyGrpTpDescTxt_P);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2710Scrn00_CMN_Clear(NMAL2710CMsg bizMsg) {
        doProcess_NMAL2710_INIT(bizMsg);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2710Scrn00_CMN_Download(NMAL2710CMsg bizMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL2710Query.getInstance().getClass());
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV);

            Map<String, Object> params = NMAL2710Query.getInstance().createParamsTrtyRuleForPostal(bizMsg, true);

            stmtSelect = ssmLLClient.createPreparedStatement("getPostalRuleForDownload", params, execParam);
            rs = stmtSelect.executeQuery();

            NMAL2710CommonLogic.writeCsvFile(bizMsg, rs, getGlobalCompanyCode());

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2710Scrn00_CMN_Submit(NMAL2710CMsg bizMsg) {
        // 2017/11/16 CSA-QC#20597 Mod Start
        // if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind()) || MESSAGE_KIND_WARN.equals(bizMsg.getMessageKind())) {
        // 2017/11/16 CSA-QC#20597 Mod End
            return;
        }

        NMAL2710CommonLogic.clearDetailControl(bizMsg);
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            bizMsg.A.no(i).xxChkBox_A1.clear();
        }

        bizMsg.setMessageInfo(NMAM8182I, new String[] {"Submit"});
    }

    /**
     * Get Org Cd Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2710Scrn00_GetOrgCd(NMAL2710CMsg bizMsg) {
        NMAL2710CommonLogic.getOrgCd(bizMsg);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2710Scrn00_Search(NMAL2710CMsg bizMsg) {
        // Detail Clear
        bizMsg.xxChkBox_DH.clear();
        ZYPTableUtil.clear(bizMsg.A);

        S21SsmEZDResult ssmResult = NMAL2710Query.getInstance().getTrtyRuleForPostal(bizMsg, false);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0007I);
            return;
        } else {
            if (ssmResult.getQueryResultCount() > bizMsg.A.length()) {
                bizMsg.A.setValidCount(bizMsg.A.length());
                bizMsg.setMessageInfo(NMAM8518I);
            } else {
                bizMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            NMAL2710CommonLogic.setSeachResult(ssmResult, bizMsg);
        }
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2710Scrn00_TBLColumnSort(NMAL2710CMsg bizMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy  = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(bizMsg.A, bizMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, bizMsg.A.getValidCount());
        }
    }
}
