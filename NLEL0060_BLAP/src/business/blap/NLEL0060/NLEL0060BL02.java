/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLEL0060;

import static business.blap.NLEL0060.constant.NLEL0060Constant.BIZ_ID;
import static business.blap.NLEL0060.constant.NLEL0060Constant.CSV;
import static business.blap.NLEL0060.constant.NLEL0060Constant.CSV_LIMIT_COUNT;
import static business.blap.NLEL0060.constant.NLEL0060Constant.CSV_NAME;
import static business.blap.NLEL0060.constant.NLEL0060Constant.FETCH_SIZE;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_CUSTOMER;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_ITEM;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_VND;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_WH;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NZZM0000E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NZZM0001W;
import static business.blap.NLEL0060.constant.NLEL0060Constant.PROC_MODE_51;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_ASG;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_ASSET_HIST;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_DEP_SIM;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_DTL;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_FIN;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_TRX;
import static business.blap.NLEL0060.constant.NLEL0060Constant.ZZZM9006E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMessageInfo;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLEL0060.common.NLEL0060CommonLogic;
import business.blap.NLEL0060.constant.NLEL0060Constant;
import business.db.ACCT_MTH_CTRLTMsg;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.DS_ASSET_MSTRTMsg;
import business.db.PRNT_VNDTMsg;
import business.db.PRNT_VNDTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.file.NLEL0060F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_MTH_CTRL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AMT_CHNG_RSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
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
 * NLEL0060BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/11   Hitachi         J.Kim           Update          QC#6579
 * 2016/04/15   Hitachi         J.Kim           Update          QC#6581
 * 2016/04/18   Hitachi         T.Tsuchida      Update          QC#7138
 * 2016/04/19   Hitachi         J.Kim           Update          QC#6774
 * 2016/05/12   Hitachi         K.Kojima        Update          QC#7113
 * 2016/06/09   Hitachi         T.Tsuchida      Update          QC#9376
 * 2016/06/14   Hitachi         T.Tsuchida      Update          QC#9757
 * 2016/06/30   Hitachi         J.Kim           Update          QC#10864
 * 2016/08/30   Fujitsu         C.Tanaka        Update          QC#13360
 * 2016/09/14   Fujitsu         C.Tanaka        Update          QC#12697
 * 2016/09/27   Hitachi         J.Kim           Update          QC#13372
 * 2016/10/07   Hitachi         J.Kim           Update          QC#5521
 * 2016/12/14   Hitachi         E.Kameishi      Update          QC#15988
 * 2017/10/26   CITS            M.Naito         Update          QC#22052
 * 2017/11/08   CITS            T.Wada          Update          QC#22267
 * 2017/11/09   Hitachi         J.Kim           Update          QC#16345
 * 2018/02/07   Hitachi         J.Kim           Update          QC#23890
 * 2018/04/16   Hitachi         J.Kim           Update          QC#22807
 * 2018/05/15   CITS            K.Ogino         Update          QC#25162
 * 2018/05/17   Hitachi         J.Kim           Update          QC#25879
 * 2018/07/31   Hitachi         J.Kim           Update          QC#27021
 * 2018/07/24   Hitachi         K.Kojima        Update          QC#27233
 * 2018/08/28   Fujitsu         S.Ohki          Update          QC#28000
 *</pre>
 */
public class NLEL0060BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NLEL0060CMsg bizMsg = (NLEL0060CMsg) cMsg;
            NLEL0060SMsg glblMsg = (NLEL0060SMsg) sMsg;

            if ("NLEL0060_INIT".equals(screenAplID)) {
                doProcess_NLEL0060_INIT(bizMsg, glblMsg);
                // START 2016/10/07 J.Kim [QC#5521,ADD]
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "AHEAD");
                // END 2016/10/07 J.Kim [QC#5521,ADD]

            } else if ("NLEL0060Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Depreciation_Search".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Depreciation_Search(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Import".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Import(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Retire".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Retire(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Run_Depreciation".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Run_Depreciation(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Search_CustInfo".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Search_CustInfo(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Search_ItemInfo".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Search_ItemInfo(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Search_VndInfo".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Search_VndInfo(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Search_WHInfo".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Search_WHInfo(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Search".equals(screenAplID)) {
                // QC#25162
                doProcess_NLEL0060Scrn00_Search(bizMsg, glblMsg, "NLEL0060Scrn00_Search");

            } else if ("NLEL0060Scrn00_Select_Search".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Select_Search(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_TAB_Assignment".equals(screenAplID)) {
                // START 2016/10/07 J.Kim [QC#5521,ADD]
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "BHEAD");
                // END 2016/10/07 J.Kim [QC#5521,ADD]
                doProcess_NLEL0060Scrn00_TAB_Assignment(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_TAB_DepSim".equals(screenAplID)) {
                // START 2016/10/07 J.Kim [QC#5521,ADD]
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "EHEAD");
                // END 2016/10/07 J.Kim [QC#5521,ADD]
                doProcess_NLEL0060Scrn00_TAB_DepSim(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_TAB_Detail".equals(screenAplID)) {
                // START 2016/10/07 J.Kim [QC#5521,ADD]
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "AHEAD");
                // END 2016/10/07 J.Kim [QC#5521,ADD]
                doProcess_NLEL0060Scrn00_TAB_Detail(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_TAB_Financial".equals(screenAplID)) {
                // START 2016/10/07 J.Kim [QC#5521,ADD]
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "CHEAD");
                // END 2016/10/07 J.Kim [QC#5521,ADD]
                doProcess_NLEL0060Scrn00_TAB_Financial(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_TAB_Trx".equals(screenAplID)) {
                // START 2016/10/07 J.Kim [QC#5521,ADD]
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "DHEAD");
                // END 2016/10/07 J.Kim [QC#5521,ADD]
                doProcess_NLEL0060Scrn00_TAB_Trx(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_TAB_AssetHist".equals(screenAplID)) {
                // START 2016/10/07 J.Kim [QC#5521,ADD]
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "FHEAD");
                // END 2016/10/07 J.Kim [QC#5521,ADD]
                doProcess_NLEL0060Scrn00_TAB_AssetHist(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Adjust".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Adjust(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Merge".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Merge(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NLEL0060_NWAL1130".equals(screenAplID)) {
                doProcess_NLEL0060_NWAL1130(bizMsg, glblMsg);

            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     */
    private void doProcess_NLEL0060_INIT(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        // START 2016/06/14 T.Tsuchida [QC#9757,MOD]
        // clearAll(bizMsg, glblMsg);
        // createPulldownList(bizMsg);
        // ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_DTL);

        if (ZYPCommonFunc.hasValue(bizMsg.xxModeInd_P)) {

            BigDecimal dsAssetMstrPk = bizMsg.dsAssetMstrPk_P.getValue();
            String xxModeInd = bizMsg.xxModeInd_P.getValue();
            clearAll(bizMsg, glblMsg);
            createPulldownList(bizMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAssetMstrPk_P, dsAssetMstrPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxModeInd_P, xxModeInd);

            DS_ASSET_MSTRTMsg dsAssetMstrTMsg = NLEL0060CommonLogic.getDsAssetMstrInfo(getGlobalCompanyCode(), dsAssetMstrPk);

            if (dsAssetMstrTMsg != null) {

                ZYPEZDItemValueSetter.setValue(bizMsg.dsAssetMstrPk_H1, dsAssetMstrPk);
                ZYPEZDItemValueSetter.setValue(bizMsg.assetTpCd_H1, dsAssetMstrTMsg.assetTpCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_DTL);
                // QC#25162
                doProcess_NLEL0060Scrn00_Search(bizMsg, glblMsg, "NLEL0060_INIT");
            }

        } else {

            clearAll(bizMsg, glblMsg);
            createPulldownList(bizMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_DTL);
        }
        // END 2016/06/14 T.Tsuchida [QC#9757,MOD]
    }

    /**
     * CMN_Clear Event
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     */
    private void doProcess_NLEL0060Scrn00_CMN_Clear(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        String tab = bizMsg.xxDplyTab.getValue();
        // START 2016/06/14 T.Tsuchida [QC#9757,ADD]
        BigDecimal dsAssetMstrPk = bizMsg.dsAssetMstrPk_P.getValue();
        String xxModeInd = bizMsg.xxModeInd_P.getValue();
        // END 2016/06/14 T.Tsuchida [QC#9757,ADD]

        clearAll(bizMsg, glblMsg);
        createPulldownList(bizMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, tab);
        // START 2016/06/14 T.Tsuchida [QC#9757,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAssetMstrPk_P, dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeInd_P, xxModeInd);
        // END 2016/06/14 T.Tsuchida [QC#9757,ADD]
    }

    /**
     * CMN_Download Event
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     */
    private void doProcess_NLEL0060Scrn00_CMN_Download(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NLEL0060Query.getInstance().getClass());
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_NAME), CSV);

            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", getGlobalCompanyCode());
            params.put("bizMsg", bizMsg);
            params.put("slsDt", ZYPDateUtil.getSalesDate());
            params.put("svcMachStsCRAT", SVC_MACH_MSTR_STS.CREATED);
            params.put("svcMachStsRMV", SVC_MACH_MSTR_STS.REMOVED);
            params.put("dsCondConstGrpId", "NLEL0060_ASSET_STS");
            params.put("procMode", PROC_MODE_51);
            // QC#25162
            params.put("depcCoaAcctCdFlg", NLEL0060CommonLogic.existCoaFlg(bizMsg.depcCoaAcctCd_S1, bizMsg.depcCoaAcctCd_S2));
            params.put("existCoaFlg", NLEL0060CommonLogic.existCoaFlg(bizMsg));
            params.put("rowNum", String.valueOf(CSV_LIMIT_COUNT + 2));
            stmtSelect = ssmLLClient.createPreparedStatement("searchDepSim", params, execParam);
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
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_CMN_Reset(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        doProcess_NLEL0060_INIT(bizMsg, glblMsg);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_CMN_Submit(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        // START 2017/10/26 M.Naito [QC#22052,MOD]
//        doProcess_NLEL0060Scrn00_Search(bizMsg, glblMsg);
        // START 2017/10/26 M.Naito [QC#22052,MOD]
        if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {

            EZDMessageInfo msgInfo = bizMsg.getMessageInfo();
            // QC#25162
            doProcess_NLEL0060Scrn00_Search(bizMsg, glblMsg, "NLEL0060Scrn00_CMN_Submit");

            if (msgInfo != null) {
                bizMsg.setMessageInfo(null);
                bizMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
        // END 2017/10/26 M.Naito [QC#22052,MOD]
    }

    /**
     * Depriciation_Search Event
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     */
    private void doProcess_NLEL0060Scrn00_Depreciation_Search(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        // START 2016/04/18 T.Tsuchida [QC#7138,MOD]
        // searchDepSim(bizMsg, glblMsg);
        // getDepcSmltnRqstDtTmTs(bizMsg, glblMsg);
        getDepcSmltnRqstDtTmTs(bizMsg, glblMsg);
        searchDepSim(bizMsg, glblMsg);
        // END 2016/04/18 T.Tsuchida [QC#7138,MOD]
        // START 2016/06/09 T.Tsuchida [QC#9376,ADD]
        NLEL0060CommonLogic.showPageUpdate(bizMsg, 1);
        // END 2016/06/09 T.Tsuchida [QC#9376,ADD]
        NLEL0060CommonLogic.showPage(bizMsg, glblMsg);
    }

    /**
     * Import Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Import(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        // START 2017/10/26 M.Naito [QC#22052,MOD]
//        doProcess_NLEL0060Scrn00_Search(bizMsg, glblMsg);
      // START 2017/10/26 M.Naito [QC#22052,MOD]
        if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {

            EZDMessageInfo msgInfo = bizMsg.getMessageInfo();
            // QC#25162
            doProcess_NLEL0060Scrn00_Search(bizMsg, glblMsg, "NLEL0060Scrn00_Import");

            if (msgInfo != null) {
                bizMsg.setMessageInfo(null);
                bizMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
        // END 2017/10/26 M.Naito [QC#22052,MOD]
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_PageJump(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLEL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        EZDCMsgArray cMsgArray = NLEL0060CommonLogic.setCMsgArray(bizMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((cMsgArray.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NLEL0060CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_PageNext(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLEL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NLEL0060CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_PagePrev(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLEL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        EZDCMsgArray cMsgArray = NLEL0060CommonLogic.setCMsgArray(bizMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - cMsgArray.length());
        NLEL0060CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);

    }

    /**
     * Retire Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Retire(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

     // START 2017/10/26 M.Naito [QC#22052,MOD]
//      doProcess_NLEL0060Scrn00_Search(bizMsg, glblMsg);
      // START 2017/10/26 M.Naito [QC#22052,MOD]
      // START 2018/04/06 J.Kim [QC#24561, ADD]
      if (bizMsg.C.getValidCount() == 0) {
          return;
      }
      // END 2018/04/06 J.Kim [QC#24561, ADD]

      if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {

          EZDMessageInfo msgInfo = bizMsg.getMessageInfo();
          // QC#25162
          doProcess_NLEL0060Scrn00_Search(bizMsg, glblMsg, "NLEL0060Scrn00_Retire");

          if (msgInfo != null) {
              bizMsg.setMessageInfo(null);
              bizMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
          }
      }
      // END 2017/10/26 M.Naito [QC#22052,MOD]
    }

    /**
     * Run_Depriciation Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Run_Depreciation(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {
        // do nothing
    }

    /**
     * Search_CustInfo Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Search_CustInfo(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        // START 2016/04/11 J.Kim [QC#6579,ADD]
        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd_H1)) {
            bizMsg.dsAcctNm_H1.clear();
            return;
        }
        // END 2016/04/11 J.Kim [QC#6579,ADD]

        SELL_TO_CUSTTMsg dsAcctCustTMsg = new SELL_TO_CUSTTMsg();
        dsAcctCustTMsg.setSQLID("001");
        dsAcctCustTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        // START 2016/04/18 J.Kim [QC#6581,MOD]
        // dsAcctCustTMsg.setConditionValue("dsAcctNum01", bizMsg.sellToCustCd_H1.getValue());
        dsAcctCustTMsg.setConditionValue("sellToCustCd01", bizMsg.shipToCustAcctCd_H1.getValue());
        // END 2016/04/18 J.Kim [QC#6581,MOD]

        SELL_TO_CUSTTMsgArray dsAcctCustTMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(dsAcctCustTMsg);
        if (dsAcctCustTMsgArray.length() > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H1, dsAcctCustTMsgArray.no(0).dsAcctNm);
        } else {
            // START 2016/04/18 J.Kim [QC#6581,MOD]
            // bizMsg.sellToCustCd_H1.setErrorInfo(1, ZZZM9006E, new String[] {MSG_CUSTOMER });
            bizMsg.shipToCustAcctCd_H1.setErrorInfo(1, ZZZM9006E, new String[] {MSG_CUSTOMER });
            // END 2016/04/18 J.Kim [QC#6581,MOD]
        }
    }

    /**
     * Search_ItemInfo Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Search_ItemInfo(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        // START 2016/04/11 J.Kim [QC#6579,ADD]
        if (!ZYPCommonFunc.hasValue(bizMsg.mdseCd_H1)) {
            bizMsg.mdseDescShortTxt_H1.clear();
            return;
        }
        // END 2016/04/11 J.Kim [QC#6579,ADD]

//        MDSETMsg mdseTMsg = new MDSETMsg();
//        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, bizMsg.mdseCd_H1.getValue());
//
//        mdseTMsg = (MDSETMsg) S21FastTBLAccessor.findByKey(mdseTMsg);

        ALL_MDSE_VTMsg cond = new ALL_MDSE_VTMsg();
        ALL_MDSE_VTMsgArray outAllMdseVTMsg = null;
        cond.setSQLID("003");
        cond.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        cond.setConditionValue("mdseCd01", bizMsg.mdseCd_H1.getValue());

        outAllMdseVTMsg = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(cond);

        if (outAllMdseVTMsg != null && outAllMdseVTMsg.length() != 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_H1, ((ALL_MDSE_VTMsg) outAllMdseVTMsg.get(0)).mdseDescShortTxt);
        } else {
            bizMsg.mdseCd_H1.setErrorInfo(1, ZZZM9006E, new String[] {MSG_ITEM });
        }
    }

    /**
     * Search_VndInfo Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Search_VndInfo(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        // START 2016/04/11 J.Kim [QC#6579,ADD]
        if (!ZYPCommonFunc.hasValue(bizMsg.vndCd_H1)) {
            bizMsg.vndNm_H1.clear();
            return;
        }
        // END 2016/04/11 J.Kim [QC#6579,ADD]

        PRNT_VNDTMsg prntVndTMsg = new PRNT_VNDTMsg();
        prntVndTMsg.setSQLID("002");
        prntVndTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        prntVndTMsg.setConditionValue("prntVndCd01", bizMsg.vndCd_H1.getValue());

        PRNT_VNDTMsgArray prntVndTMsgArray = (PRNT_VNDTMsgArray) EZDTBLAccessor.findByCondition(prntVndTMsg);
        if (prntVndTMsgArray.length() > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.vndNm_H1, prntVndTMsgArray.no(0).prntVndNm);
        } else {
            bizMsg.vndCd_H1.setErrorInfo(1, ZZZM9006E, new String[] {MSG_VND });
        }
    }

    /**
     * Search_WHInfo Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Search_WHInfo(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        // START 2016/04/11 J.Kim [QC#6579,ADD]
        if (!ZYPCommonFunc.hasValue(bizMsg.rtnWhCd_H1)) {
            bizMsg.rtlWhNm_H1.clear();
            return;
        }
        // END 2016/04/11 J.Kim [QC#6579,ADD]

        // START 2016/06/03 T.Tsuchida [QC#8981,MOD]
//        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
//        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, bizMsg.rtnWhCd_H1.getValue());
//
//        rtlWhTMsg = (RTL_WHTMsg) S21FastTBLAccessor.findByKey(rtlWhTMsg);
//        if (rtlWhTMsg != null) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H1, rtlWhTMsg.rtlWhNm);
//        } else {
//            bizMsg.rtnWhCd_H1.setErrorInfo(1, ZZZM9006E, new String[] {MSG_WH });
//        }
        S21SsmEZDResult ssmResult = NLEL0060Query.getInstance().getWhNm(bizMsg.rtnWhCd_H1.getValue());
        if (!ssmResult.isCodeNotFound()) {
            Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H1, (String) map.get("RTL_WH_NM"));
        } else {
            bizMsg.rtnWhCd_H1.setErrorInfo(1, ZZZM9006E, new String[] {MSG_WH });
        }
        // END 2016/06/03 T.Tsuchida [QC#8981,MOD]
    }

    /**
     * Modify QC#25162
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param eventNm String
     */
    private void doProcess_NLEL0060Scrn00_Search(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg, String eventNm) {
        //START 2016/12/14 E.Kameishi [QC#15988, ADD]
        bizMsg.setCommitSMsg(true);
        String tab = bizMsg.xxDplyTab.getValue();
        //END 2016/12/14 E.Kameishi [QC#15988, ADD]
        if ("NLEL0060Scrn00_Search".equals(eventNm) || "NLEL0060_INIT".equals(eventNm)) {
            NLEL0060CommonLogic.setSearchCondBackUp(bizMsg);
        }
        searchDetail(bizMsg, glblMsg);
        // START 2018/07/24 K.Kojima [QC#27233,DEL]
        // searchAssignment(bizMsg, glblMsg);
        // searchFinancial(bizMsg, glblMsg);
        // END 2018/07/24 K.Kojima [QC#27233,DEL]
        // QC#25162 Performance QC
        if (!"NLEL0060Scrn00_Search".equals(eventNm)) {
            // START 2018/07/24 K.Kojima [QC#27233,ADD]
            searchAssignment(bizMsg, glblMsg);
            searchFinancial(bizMsg, glblMsg);
            // END 2018/07/24 K.Kojima [QC#27233,ADD]
            searchTransaction(bizMsg, glblMsg);
            // START 2016/09/27 J.Kim [QC#13372,ADD]
            searchAssetHistory(bizMsg, glblMsg);
            // END 2016/09/27 J.Kim [QC#13372,ADD]
            // START 2016/06/09 T.Tsuchida [QC#9376,ADD]
            // QC#22267
            getDepcSmltnRqstDtTmTs(bizMsg, glblMsg);
            searchDepSim(bizMsg, glblMsg);
            // START 2018/07/24 K.Kojima [QC#27233,MOD]
            // } else if (TAB_TRX.equals(tab)) {
            //     ZYPTableUtil.clear(bizMsg.E);
            //     ZYPTableUtil.clear(glblMsg.E);
            //     ZYPTableUtil.clear(bizMsg.F);
            //     ZYPTableUtil.clear(glblMsg.F);
            //     searchTransaction(bizMsg, glblMsg);
            // } else if (TAB_DEP_SIM.equals(tab)) {
            //     ZYPTableUtil.clear(bizMsg.D);
            //     ZYPTableUtil.clear(glblMsg.D);
            //     ZYPTableUtil.clear(bizMsg.F);
            //     ZYPTableUtil.clear(glblMsg.F);
            //     getDepcSmltnRqstDtTmTs(bizMsg, glblMsg);
            //     searchDepSim(bizMsg, glblMsg);
            // } else if (TAB_ASSET_HIST.equals(tab)) {
            //     ZYPTableUtil.clear(bizMsg.D);
            //     ZYPTableUtil.clear(glblMsg.D);
            //     ZYPTableUtil.clear(bizMsg.E);
            //     ZYPTableUtil.clear(glblMsg.E);
            //     searchAssetHistory(bizMsg, glblMsg);
            // } else {
            //     ZYPTableUtil.clear(bizMsg.D);
            //     ZYPTableUtil.clear(glblMsg.D);
            //     ZYPTableUtil.clear(bizMsg.E);
            //     ZYPTableUtil.clear(glblMsg.E);
            //     ZYPTableUtil.clear(bizMsg.F);
            //     ZYPTableUtil.clear(glblMsg.F);
            // }
        } else {
            ZYPTableUtil.clear(bizMsg.B);
            ZYPTableUtil.clear(glblMsg.B);
            ZYPTableUtil.clear(bizMsg.C);
            ZYPTableUtil.clear(glblMsg.C);
            ZYPTableUtil.clear(bizMsg.D);
            ZYPTableUtil.clear(glblMsg.D);
            ZYPTableUtil.clear(bizMsg.E);
            ZYPTableUtil.clear(glblMsg.E);
            ZYPTableUtil.clear(bizMsg.F);
            ZYPTableUtil.clear(glblMsg.F);
            if (TAB_ASG.equals(tab) && glblMsg.A.getValidCount() > 0) {
                searchAssignment(bizMsg, glblMsg);
            } else if (TAB_FIN.equals(tab) && glblMsg.A.getValidCount() > 0) {
                searchFinancial(bizMsg, glblMsg);
            } else if (TAB_TRX.equals(tab)) {
                searchTransaction(bizMsg, glblMsg);
            } else if (TAB_DEP_SIM.equals(tab)) {
                getDepcSmltnRqstDtTmTs(bizMsg, glblMsg);
                searchDepSim(bizMsg, glblMsg);
            } else if (TAB_ASSET_HIST.equals(tab)) {
                searchAssetHistory(bizMsg, glblMsg);
            }
        }
        // END 2018/07/24 K.Kojima [QC#27233,MOD]
        // QC#25162

        NLEL0060CommonLogic.showPageClear(bizMsg);
        NLEL0060CommonLogic.showPageUpdate(bizMsg, 1);
        // END 2016/06/09 T.Tsuchida [QC#9376,ADD]

        // START 2018/04/06 J.Kim [QC#24561,MOD]
        setFinancialRetire(bizMsg, glblMsg);
        // END 2018/04/06 J.Kim [QC#24561,MOD]

        NLEL0060CommonLogic.showPage(bizMsg, glblMsg);

        // QC#25162 Search Condition Backup
        
    }

    /**
     * Select_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Select_Search(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            return;
        }

        NSZC033001PMsg pMsg = new NSZC033001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!NLEL0060CommonLogic.callNszc0330(bizMsg, pMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.assetTpCd_H1, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.assetStsCd_H1, pMsg.srchOptTxt_02);

        ZYPEZDItemValueSetter.setValue(bizMsg.assetTagNum_H1, pMsg.srchOptTxt_04);

        ZYPEZDItemValueSetter.setValue(bizMsg.serNum_H1, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_H1, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_H1, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum_H1, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtnWhCd_H1, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H1, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAssetDescTxt_H1, pMsg.srchOptTxt_14);
        // START 2016/04/18 J.Kim [QC#6581,MOD]
        // ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_H1, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_H1, pMsg.srchOptTxt_15);
        // END 2016/04/18 J.Kim [QC#6581,MOD]
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H1, pMsg.srchOptTxt_16);
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_H1, pMsg.srchOptTxt_17);
        ZYPEZDItemValueSetter.setValue(bizMsg.vndCd_H1, pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(bizMsg.vndNm_H1, pMsg.srchOptTxt_19);

        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_03.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAssetMstrPk_H1, new BigDecimal(pMsg.srchOptTxt_03.getValue()));
        } else {
            bizMsg.dsAssetMstrPk_H1.clear();
        }

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_05.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.depcStartDt_H1, pMsg.srchOptTxt_05.getValue());
        } else {
            bizMsg.depcStartDt_H1.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_06.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.depcStartDt_H2, pMsg.srchOptTxt_06.getValue());
        } else {
            bizMsg.depcStartDt_H2.clear();
        }

        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_07.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk_H1, new BigDecimal(pMsg.srchOptTxt_07.getValue()));
        } else {
            bizMsg.svcConfigMstrPk_H1.clear();
        }
        // START 2018/04/13 J.Kim [QC#22807,ADD]
        //// START 2016/06/30 J.Kim [QC#10864,ADD]
        //if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_20.getValue())) {
        //    ZYPEZDItemValueSetter.setValue(bizMsg.depcCoaAcctCd_H1, pMsg.srchOptTxt_20);
        //} else {
        //    bizMsg.depcCoaAcctCd_H1.clear();
        //}
        //// END 2016/06/30 J.Kim [QC#10864,ADD]
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_20.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.depcCoaAcctCd_F, pMsg.srchOptTxt_20);
        } else {
            bizMsg.depcCoaAcctCd_F.clear();
        }
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_21.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.depcCoaAcctCd_T, pMsg.srchOptTxt_21);
        } else {
            bizMsg.depcCoaAcctCd_T.clear();
        }
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_22.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.expCoaBrCd_F, pMsg.srchOptTxt_22);
        } else {
            bizMsg.expCoaBrCd_F.clear();
        }
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_23.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.expCoaBrCd_T, pMsg.srchOptTxt_23);
        } else {
            bizMsg.expCoaBrCd_T.clear();
        }
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_24.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.expCoaCcCd_F, pMsg.srchOptTxt_24);
        } else {
            bizMsg.expCoaCcCd_F.clear();
        }
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_25.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.expCoaCcCd_T, pMsg.srchOptTxt_25);
        } else {
            bizMsg.expCoaCcCd_T.clear();
        }
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_26.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.expCoaExtnCd_F, pMsg.srchOptTxt_26);
        } else {
            bizMsg.expCoaExtnCd_F.clear();
        }
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_27.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.expCoaExtnCd_T, pMsg.srchOptTxt_27);
        } else {
            bizMsg.expCoaExtnCd_T.clear();
        }
        // END 2018/04/13 J.Kim [QC#22807,ADD]
    }

    /**
     * TAB_Assignment Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_TAB_Assignment(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLEL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_ASG);
        // START 2018/07/24 K.Kojima [QC#27233,ADD]
        if (glblMsg.A.getValidCount() != 0 && glblMsg.B.getValidCount() == 0) {
            searchAssignment(bizMsg, glblMsg);
        }
        // END 2018/07/24 K.Kojima [QC#27233,ADD]
        NLEL0060CommonLogic.showPage(bizMsg, glblMsg);
    }

    /**
     * TAB_DepSim Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_TAB_DepSim(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLEL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_DEP_SIM);
        // QC#25162
        if (glblMsg.A.getValidCount() != 0) {
            getDepcSmltnRqstDtTmTs(bizMsg, glblMsg);
            searchDepSim(bizMsg, glblMsg);
        }
        NLEL0060CommonLogic.showPage(bizMsg, glblMsg);
    }

    /**
     * TAB_Detail Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_TAB_Detail(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLEL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_DTL);
        NLEL0060CommonLogic.showPage(bizMsg, glblMsg);
    }

    /**
     * TAB_Financial Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_TAB_Financial(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLEL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_FIN);
        // START 2018/07/24 K.Kojima [QC#27233,ADD]
        if (glblMsg.A.getValidCount() != 0 && glblMsg.C.getValidCount() == 0) {
            searchFinancial(bizMsg, glblMsg);
        }
        // END 2018/07/24 K.Kojima [QC#27233,ADD]
        NLEL0060CommonLogic.showPage(bizMsg, glblMsg);
        // START 2018/04/06 J.Kim [QC#24561,MOD]
        setFinancialRetire(bizMsg, glblMsg);
        // END 2018/04/06 J.Kim [QC#24561,MOD]
    }

    /**
     * TAB_Trx Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_TAB_Trx(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLEL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_TRX);
        // QC#25162
        if (glblMsg.A.getValidCount() != 0) {
            searchTransaction(bizMsg, glblMsg);
        }
        NLEL0060CommonLogic.showPage(bizMsg, glblMsg);
    }

    // START 2016/09/27 J.Kim [QC#13372,ADD]
    /**
     * TAB_AssetHist Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_TAB_AssetHist(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLEL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_ASSET_HIST);
        // QC#25162
        if (glblMsg.A.getValidCount() != 0) {
            searchAssetHistory(bizMsg, glblMsg);
        }
        NLEL0060CommonLogic.showPage(bizMsg, glblMsg);
    }
    // END 2016/09/27 J.Kim [QC#13372,ADD]

    /**
     *Adjust
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Adjust(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLEL0060CommonLogic.checkInput(bizMsg, glblMsg);
        // START 2018/04/06 J.Kim [QC#24561,MOD]
        setFinancialRetire(bizMsg, glblMsg);
        // END 2018/04/06 J.Kim [QC#24561,MOD]
    }

    /**
     *Merge
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Merge(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLEL0060CommonLogic.checkInput(bizMsg, glblMsg);
        // START 2018/04/06 J.Kim [QC#24561,MOD]
        setFinancialRetire(bizMsg, glblMsg);
        // END 2018/04/06 J.Kim [QC#24561,MOD]
    }

   // START 2016/10/07 J.Kim [QC#5521,ADD]
    private void doProcess_NLEL0060Scrn00_TBLColumnSort(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            bizMsg.xxPageShowFromNum_A.setValue(1);
        } else if ("B".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.B, glblMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.B.getValidCount());

            bizMsg.xxPageShowFromNum_B.setValue(1);
        } else if ("C".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.C, glblMsg.C.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.C.getValidCount());

            bizMsg.xxPageShowFromNum_C.setValue(1);
        } else if ("D".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.D, glblMsg.D.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.D.getValidCount());

            bizMsg.xxPageShowFromNum_D.setValue(1);
        } else if ("E".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.E, glblMsg.E.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.E.getValidCount());

            bizMsg.xxPageShowFromNum_E.setValue(1);
        } else if ("F".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.F, glblMsg.F.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.F.getValidCount());

            bizMsg.xxPageShowFromNum_F.setValue(1);
        }

        NLEL0060CommonLogic.showPage(bizMsg, glblMsg);
    }
    // END 2016/10/07 J.Kim [QC#5521,ADD]

    // START 2018/05/17 J.Kim [QC#25879,ADD]
    /**
     * NLEL0060_NWAL1130
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060_NWAL1130(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String slsRepTocCd = bizMsg.psnNum.getValue(); // 2018/08/28 S.Ohki [QC#28000,MOD] slsRepTocCd -> psnNum
        int index = bizMsg.xxNum.getValueInt();
        Map<String, String> saleRepInfo = NLEL0060Query.getInstance().getSalesRep(glblCmpyCd, slsRepTocCd);
        if (saleRepInfo != null) {
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(index).coaBrCd_B, saleRepInfo.get("COA_BR_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(index).coaCcCd_B, saleRepInfo.get("COA_CC_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(index).coaExtnCd_B, saleRepInfo.get("COA_EXTN_CD"));
        }

        NLEL0060CommonLogic.showPage(bizMsg, glblMsg);
    }
    // END 2018/05/17 J.Kim [QC#25879,ADD]

    private void clearAll(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(glblMsg.C);
        ZYPTableUtil.clear(bizMsg.D);
        ZYPTableUtil.clear(glblMsg.D);
        ZYPTableUtil.clear(bizMsg.E);
        ZYPTableUtil.clear(glblMsg.E);
        // START 2016/09/27 J.Kim [QC#13372,ADD]
        ZYPTableUtil.clear(bizMsg.F);
        ZYPTableUtil.clear(glblMsg.F);
        // END 2016/09/27 J.Kim [QC#13372,ADD]

        bizMsg.clear();
        glblMsg.clear();
    }

    private void createPulldownList(NLEL0060CMsg bizMsg) {

        NLEL0060CommonLogic.createSaveOptPulldownList(bizMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

        ZYPCodeDataUtil.createPulldownList(ASSET_TP.class, bizMsg.assetTpCd_L, bizMsg.assetTpDescTxt_L);

        // Asset Status
        S21SsmEZDResult ssmResult = NLEL0060Query.getInstance().getAvalAssetStsList(getGlobalCompanyCode());

        if (ssmResult.isCodeNotFound()) {

            return;
        }

        List<Map<String, String>> avalAssetStsList = (List<Map<String, String>>) ssmResult.getResultObject();

        for (int i = 0; i < avalAssetStsList.size(); i++) {

            if (i >= bizMsg.assetStsCd_L.length()) {

                break;
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.assetStsCd_L.no(i), avalAssetStsList.get(i).get("DS_COND_CONST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.assetStsDescTxt_L.no(i), avalAssetStsList.get(i).get("DS_COND_CONST_DESC_TXT"));
        }
        // START 2018/08/1 J.Kim [QC#26901,ADD]
        ZYPCodeDataUtil.createPulldownList(AMT_CHNG_RSN_TP.class, bizMsg.amtChngRsnTpCd_C, bizMsg.amtChngRsnTpNm_D);
        // END 2018/08/1 J.Kim [QC#26901,ADD]
    }

    private void searchDetail(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        // START 2018/07/31 J.Kim [QC#27021,ADD]
        NLEL0060CommonLogic.setPrntDsAssetMstrPk(bizMsg);
        // END 2018/07/31 J.Kim [QC#27021,ADD]
        NLEL0060Query.getInstance().searchDetail(bizMsg, glblMsg);
        // START 2016/12/14 E.Kameishi [QC#15988,MOD]
        //boolean peFlg = false;
        //for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
        //    if (ASSET_STS.PENDING.equals(glblMsg.A.no(i).assetStsCd_A1.getValue())) {
        //        peFlg = true;
        //        break;
        //    }
        //}
        //if (peFlg) {
        //    ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_T1, ZYPConstant.FLG_ON_Y);
        //}
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_T1, ZYPConstant.FLG_ON_Y);
        // END 2016/12/14 E.Kameishi [QC#15988,MOD]
    }

    private void searchAssignment(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);
        NLEL0060Query.getInstance().searchAssignment(bizMsg, glblMsg);
        // START 2016/04/19 J.Kim [QC#6774,ADD]
        NLEL0060CommonLogic.setAssignmentAddress(glblMsg, getGlobalCompanyCode());
        // END 2016/04/19 J.Kim [QC#6774,ADD]

        String glblCmpyCd = getGlobalCompanyCode();
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NLEL0060_BSMsg bSMsg = glblMsg.B.no(i);
            // START 2016/11/01 J.Kim [QC#16345,DEL]
            // StringBuffer assetAcct = new StringBuffer();
            // StringBuffer expAcct = new StringBuffer();
            // END 2016/11/01 J.Kim [QC#16345,DEL]
            // START 2018/02/06 J.Kim [QC#23890,DEL]
            // BigDecimal svcMachMstrPk = bSMsg.svcMachMstrPk_B1.getValue();
            // END 2018/02/06 J.Kim [QC#23890,DEL]

            if (ASSET_TP.RENTAL_ASSET.equals(bSMsg.assetTpCd_B1.getValue()) || ASSET_TP.EMSD_ASSET.equals(bSMsg.assetTpCd_B1.getValue())) {
                // START 2016/11/01 J.Kim [QC#16345,MOD]
                String assetTpCd = bSMsg.assetTpCd_B1.getValue();
                // START 2018/02/08 J.Kim [QC#23123,DEL]
                // ASSET_TPTMsg  assetTpTMsg = NLEL0060CommonLogic.getAssetTpInfo(glblCmpyCd, assetTpCd);
                // END 2018/02/08 J.Kim [QC#23123,DEL]
                if (ASSET_TP.RENTAL_ASSET.equals(assetTpCd)) {
                    bSMsg.xxScrItem50Txt_B2.clear();
                    ZYPEZDItemValueSetter.setValue(bSMsg.coaAcctCd_B, bSMsg.assetCoaAcctCd_B2.getValue());
                    // START 2018/05/17 J.Kim [QC#25879,MO]
                    // ZYPEZDItemValueSetter.setValue(bSMsg.coaBrCd_B, NLEL0060CommonLogic.getAjeDefaultCoa(CoaSegment.BR, bSMsg));
                    // ZYPEZDItemValueSetter.setValue(bSMsg.coaCcCd_B, NLEL0060CommonLogic.getAjeDefaultCoa(CoaSegment.CC, bSMsg));
                    // ZYPEZDItemValueSetter.setValue(bSMsg.coaExtnCd_B, NLEL0060CommonLogic.getAjeDefaultCoa(CoaSegment.EXTN, bSMsg));
                    ZYPEZDItemValueSetter.setValue(bSMsg.coaBrCd_B, bSMsg.expCoaBrCd_B1);
                    ZYPEZDItemValueSetter.setValue(bSMsg.coaCcCd_B, bSMsg.expCoaCcCd_B1);
                    ZYPEZDItemValueSetter.setValue(bSMsg.coaExtnCd_B, bSMsg.expCoaExtnCd_B1);
                    // END 2018/05/17 J.Kim [QC#25879,ADD]
                } else {
                    // START 2018/02/06 J.Kim [QC#23890,MOD]
                    // if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                    String procModeCd = bSMsg.procModeCd_B1.getValue();
                    if (!NLEL0060Constant.PROC_MODE_51.equals(procModeCd)) {
                        // END 2018/02/06 J.Kim [QC#23890,MOD]
                        // EMSD(Order)
                        bSMsg.xxScrItem50Txt_B2.clear();
                        // START 2018/05/17 J.Kim [QC#25879,MO]
                        // ZYPEZDItemValueSetter.setValue(bSMsg.coaBrCd_B, NLEL0060CommonLogic.getAjeDefaultCoa(CoaSegment.BR, bSMsg));
                        // ZYPEZDItemValueSetter.setValue(bSMsg.coaCcCd_B, NLEL0060CommonLogic.getAjeDefaultCoa(CoaSegment.CC, bSMsg));
                        // ZYPEZDItemValueSetter.setValue(bSMsg.coaExtnCd_B, NLEL0060CommonLogic.getAjeDefaultCoa(CoaSegment.EXTN, bSMsg));
                        ZYPEZDItemValueSetter.setValue(bSMsg.coaBrCd_B, bSMsg.expCoaBrCd_B1);
                        ZYPEZDItemValueSetter.setValue(bSMsg.coaCcCd_B, bSMsg.expCoaCcCd_B1);
                        ZYPEZDItemValueSetter.setValue(bSMsg.coaExtnCd_B, bSMsg.expCoaExtnCd_B1);
                        // END 2018/05/17 J.Kim [QC#25879,ADD]
                    } else {
                        // EMSE(PO)
                        bSMsg.coaBrCd_B.clear();
                        bSMsg.coaCcCd_B.clear();
                        bSMsg.coaExtnCd_B.clear();
                        // Set Expense COA
                        String defExpCoa = "";
                        if (ZYPCommonFunc.hasValue(bSMsg.expCoaBrCd_B1)) {
                            defExpCoa = NLEL0060CommonLogic.setExpenseCOA(bSMsg, glblCmpyCd);
                        } else {
                            defExpCoa = NLEL0060CommonLogic.editDefaultExpenseCOA(bizMsg, bSMsg, glblCmpyCd);
                        }
                        ZYPEZDItemValueSetter.setValue(bSMsg.xxScrItem50Txt_B2, defExpCoa);
                        ZYPEZDItemValueSetter.setValue(bSMsg.xxScrItem50Txt_BC, defExpCoa);
                    }
                    ZYPEZDItemValueSetter.setValue(bSMsg.coaAcctCd_B, bSMsg.assetCoaAcctCd_B2.getValue());
                }
                // END 2016/11/01 J.Kim [QC#16345,MOD]

                // START 2016/11/07 J.Kim [QC#16345,DEL]
                //// Set Asset COA
                //if (ZYPCommonFunc.hasValue(bSMsg.assetCoaCmpyCd_B1)) {
                //    assetAcct.append(bSMsg.assetCoaCmpyCd_B1.getValue());
                //} else {
                //    assetAcct.append(glblCmpyCd);
                //}
                //assetAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.assetCoaBrCd_B1)) {
                //    assetAcct.append(bSMsg.assetCoaBrCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.coaBrCd_B1, bSMsg.assetCoaBrCd_B1.getValue());
                //} else {
                //    assetAcct.append(bSMsg.coaBrCd_B1.getValue());
                //}
                //assetAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.assetCoaCcCd_B1)) {
                //    assetAcct.append(bSMsg.assetCoaCcCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.coaCcCd_B1, bSMsg.assetCoaCcCd_B1.getValue());
                //} else {
                //    assetAcct.append(bSMsg.coaCcCd_B1.getValue());
                //}
                //assetAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.assetCoaAcctCd_B1)) {
                //    assetAcct.append(bSMsg.assetCoaAcctCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.assetCoaAcctCd_B2, bSMsg.assetCoaAcctCd_B1.getValue());
                //} else {
                //    assetAcct.append(bSMsg.assetCoaAcctCd_B2.getValue());
                //}
                //assetAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.assetCoaProdCd_B1)) {
                //    assetAcct.append(bSMsg.assetCoaProdCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.coaProdCd_B1, bSMsg.assetCoaProdCd_B1.getValue());
                //} else {
                //    assetAcct.append(bSMsg.coaProdCd_B1.getValue());
                //}
                //assetAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.assetCoaChCd_B1)) {
                //    assetAcct.append(bSMsg.assetCoaChCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.coaChCd_B1, bSMsg.assetCoaChCd_B1.getValue());
                //} else {
                //    assetAcct.append(bSMsg.coaChCd_B1.getValue());
                //}
                //assetAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.assetCoaAfflCd_B1)) {
                //    assetAcct.append(bSMsg.assetCoaAfflCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.coaAfflCd_B1, bSMsg.assetCoaAfflCd_B1.getValue());
                //} else {
                //    assetAcct.append(bSMsg.coaAfflCd_B1.getValue());
                //}
                //assetAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.assetCoaProjCd_B1)) {
                //    assetAcct.append(bSMsg.assetCoaProjCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.coaProjCd_B1, bSMsg.assetCoaProjCd_B1.getValue());
                //} else {
                //    assetAcct.append(bSMsg.coaProjCd_B1.getValue());
                //}
                //assetAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.assetCoaExtnCd_B1)) {
                //    assetAcct.append(bSMsg.assetCoaExtnCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.coaExtnCd_B1, bSMsg.assetCoaExtnCd_B1.getValue());
                //} else {
                //    assetAcct.append(bSMsg.coaExtnCd_B1.getValue());
                //}
                //ZYPEZDItemValueSetter.setValue(bSMsg.xxScrItem50Txt_B1, assetAcct.toString());
                //ZYPEZDItemValueSetter.setValue(bSMsg.xxScrItem50Txt_BB, assetAcct.toString());
                // END 2016/11/01 J.Kim [QC#16345,DEL]

                // START 2016/11/07 J.Kim [QC#16345,DEL]
                //// Set Expense COA
                //if (ZYPCommonFunc.hasValue(bSMsg.expCoaCmpyCd_B1)) {
                //    expAcct.append(bSMsg.expCoaCmpyCd_B1.getValue());
                //} else {
                //    expAcct.append(glblCmpyCd);
                //}
                //expAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.expCoaBrCd_B1)) {
                //    expAcct.append(bSMsg.expCoaBrCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.coaBrCd_B2, bSMsg.expCoaBrCd_B1.getValue());
                //} else {
                //    expAcct.append(bSMsg.coaBrCd_B2.getValue());
                //}
                //expAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.expCoaCcCd_B1)) {
                //    expAcct.append(bSMsg.expCoaCcCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.coaCcCd_B2, bSMsg.expCoaCcCd_B1.getValue());
                //} else {
                //    expAcct.append(bSMsg.coaCcCd_B2.getValue());
                //}
                //expAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.expCoaAcctCd_B1)) {
                //    expAcct.append(bSMsg.expCoaAcctCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.depcCoaAcctCd_B2, bSMsg.expCoaAcctCd_B1.getValue());
                //} else {
                //    expAcct.append(bSMsg.depcCoaAcctCd_B2.getValue());
                //}
                //expAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.expCoaProdCd_B1)) {
                //    expAcct.append(bSMsg.expCoaProdCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.coaProdCd_B1, bSMsg.expCoaProdCd_B1.getValue());
                //} else {
                //    expAcct.append(bSMsg.coaProdCd_B1.getValue());
                //}
                //expAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.expCoaChCd_B1)) {
                //    expAcct.append(bSMsg.expCoaChCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.coaChCd_B1, bSMsg.expCoaChCd_B1.getValue());
                //} else {
                //    expAcct.append(bSMsg.coaChCd_B1.getValue());
                //}
                //expAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.expCoaAfflCd_B1)) {
                //    expAcct.append(bSMsg.expCoaAfflCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.coaAfflCd_B1, bSMsg.expCoaAfflCd_B1.getValue());
                //} else {
                //    expAcct.append(bSMsg.coaAfflCd_B1.getValue());
                //}
                //expAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.expCoaProjCd_B1)) {
                //    expAcct.append(bSMsg.expCoaProjCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.coaProjCd_B1, bSMsg.expCoaProjCd_B1.getValue());
                //} else {
                //    expAcct.append(bSMsg.coaProjCd_B1.getValue());
                //}
                //expAcct.append(STR_COMMA);
                //if (ZYPCommonFunc.hasValue(bSMsg.expCoaExtnCd_B1)) {
                //    expAcct.append(bSMsg.expCoaExtnCd_B1.getValue());
                //    ZYPEZDItemValueSetter.setValue(bSMsg.coaExtnCd_B1, bSMsg.expCoaExtnCd_B1.getValue());
                //} else {
                //    expAcct.append(bSMsg.coaExtnCd_B1.getValue());
                //}
                //ZYPEZDItemValueSetter.setValue(bSMsg.xxScrItem50Txt_B2, expAcct.toString());
                //ZYPEZDItemValueSetter.setValue(bSMsg.xxScrItem50Txt_BC, expAcct.toString());
                // END 2016/11/07 J.Kim [QC#16345,DEL]
            }
        }
    }

    private void searchFinancial(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(glblMsg.C);
        // START 2018/07/31 J.Kim [QC#27021,ADD]
        NLEL0060CommonLogic.setPrntDsAssetMstrPk(bizMsg);
        // END 2018/07/31 J.Kim [QC#27021,ADD]
        NLEL0060Query.getInstance().searchFinancial(bizMsg, glblMsg);
    }

    /**
     * searchTransaction
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     */
    private void searchTransaction(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.D);
        ZYPTableUtil.clear(glblMsg.D);
        NLEL0060Query.getInstance().searchTransaction(bizMsg, glblMsg);
    }

    /**
     * searchDepSim
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     */
    private void searchDepSim(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.E);
        ZYPTableUtil.clear(glblMsg.E);

        S21SsmEZDResult ssmResult = NLEL0060Query.getInstance().searchDepSim(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            // START 2017/11/16 J.Kim [QC#16345,DEL]
            // bizMsg.setMessageInfo(NZZM0000E);
            // END 2017/11/16 J.Kim [QC#16345, DEL]

        } else {

            if (ssmResult.getQueryResultCount() > glblMsg.E.length()) {

                bizMsg.setMessageInfo(NZZM0001W);
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NLEL0060CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);
        }
    }

    // START 2016/09/27 J.Kim [QC#13372,ADD]
    /**
     * searchAssetHistory
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     */
    private void searchAssetHistory(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.F);
        ZYPTableUtil.clear(glblMsg.F);

        S21SsmEZDResult ssmResult = NLEL0060Query.getInstance().searchAssetHistory(bizMsg, glblMsg);

        if (!ssmResult.isCodeNotFound()) {

            for (int index = 0; index < glblMsg.F.getValidCount(); index++) {

                NLEL0060_FSMsg fsMsg = glblMsg.F.no(index);
                String trxRgtnTs = fsMsg.xxDtTm_F1.getValue();
                ZYPEZDItemValueSetter.setValue(fsMsg.xxDtTm_F1, getTrxRgtnTs(trxRgtnTs));
            }
        }
    }

    /**
     * getTrxRgtnTs
     * @param trxRgtnTs String
     * @return String
     */
    private String getTrxRgtnTs(String trxRgtnTs) {

        String yyyMmDd = trxRgtnTs.substring(0, 8);
        String h = trxRgtnTs.substring(8, 10);
        String m = trxRgtnTs.substring(10, 12);
        String s = trxRgtnTs.substring(12, 14);
        String trxRgtn = ZYPDateUtil.convertFormat(yyyMmDd, "yyyy/MM/dd", "MM/dd/yyyy", ZYPDateUtil.SEPARATOR_SLASH);
        String ts = ZYPDateUtil.formatDispHHmmss(h, m, s, true);
        trxRgtnTs = trxRgtn.concat(" ").concat(ts);

        return trxRgtnTs;
    }
    // END 2016/09/27 J.Kim [QC#13372,ADD]

    /**
     * getDepcSmltnRqstDtTmTs
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     */
    private void getDepcSmltnRqstDtTmTs(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        bizMsg.xxDt10Dt_T1.clear();

        // QC#25162
        S21SsmEZDResult ssmResult = NLEL0060Query.getInstance().getDepcSmltnRqstDtTmTs(bizMsg.assetTpCd_S1.getValue());

        if (!ssmResult.isCodeNotFound()) {

            Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
            // START 2016/04/18 T.Tsuchida [QC#7138,MOD]
            //String dt = S21StringUtil.subStringByLength((String) map.get("DEPC_SMLTN_RQST_DT_TM_TS"), 0, 8);
            String dt = (String) map.get("ASSET_TRX_DT");
            // END 2016/04/18 T.Tsuchida [QC#7138,MOD]
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDt10Dt_T1, dt);
        }
    }

    /**
     * writeCsvFile
     * @param bizMsg NLEL0060CMsg
     * @param rs ResultSet
     * @exception SQLException
     */
    private void writeCsvFile(NLEL0060CMsg bizMsg, ResultSet rs) throws SQLException {

        NLEL0060F00FMsg fMsg = new NLEL0060F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

        // write header
        final String[] csvHeader = new String[] {"Asset Num", "Book Type", "Period", "DR Amount", "DR Account", "CR Amount", "CR Account" };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        // write contents
        do {
            if (rs.getRow() >= CSV_LIMIT_COUNT) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }

            ZYPEZDItemValueSetter.setValue(fMsg.prntDsAssetMstrPk_E1, rs.getBigDecimal("PRNT_DS_ASSET_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.assetTpNm_E1, rs.getString("ASSET_TP_NM"));
            // START 2016/04/11 J.Kim [QC#6581,MOD]
            //ZYPEZDItemValueSetter.setValue(fMsg.assetTrxDt_E1, rs.getString("ASSET_TRX_DT"));
            String assetTrxDt = rs.getString("ASSET_TRX_DT");
            if (assetTrxDt != null) {
                assetTrxDt = ZYPDateUtil.convertFormat(rs.getString("ASSET_TRX_DT"), "yyyyMMdd", "MMddyyyy", ZYPDateUtil.SEPARATOR_SLASH);
            }
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_E1, assetTrxDt);
            // END 2016/04/11 J.Kim [QC#6581,MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.depcValAmt_E1, rs.getBigDecimal("DEPC_VAL_AMT1"));
            ZYPEZDItemValueSetter.setValue(fMsg.drCoaAcctCd_E1, rs.getString("DR_COA_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.depcValAmt_E2, rs.getBigDecimal("DEPC_VAL_AMT2"));
            ZYPEZDItemValueSetter.setValue(fMsg.crCoaAcctCd_E1, rs.getString("CR_COA_ACCT_CD"));

            csvOutFile.write();

        } while (rs.next());

        csvOutFile.close();
    }

    // START 2018/04/06 J.Kim [QC#24561,ADD]
    private void setFinancialRetire(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate();
        String acctYrMth1 = slsDt;
        String acctYrMth2 = "";
        bizMsg.acctYrMth_T1.clear();

        // Get date from ACCT_MTH_CTRL
        ACCT_MTH_CTRLTMsg acctMthCtrlTMsg = new ACCT_MTH_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(acctMthCtrlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(acctMthCtrlTMsg.acctMthCtrlCd, ACCT_MTH_CTRL.DEPRICIATION_CONTROL);

        acctMthCtrlTMsg = (ACCT_MTH_CTRLTMsg) S21FastTBLAccessor.findByKey(acctMthCtrlTMsg);
        if (RTNCD_NORMAL.equals(acctMthCtrlTMsg.getReturnCode())) {
            acctYrMth2 = acctMthCtrlTMsg.acctYrMth.getValue();
        }

        // Check if Retire Date is valid
        acctYrMth1 = S21StringUtil.subStringByLength(acctYrMth1, 0, 6);
        acctYrMth2 = S21StringUtil.subStringByLength(acctYrMth2, 0, 6);
        if (acctYrMth1.equals(acctYrMth2)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.acctYrMth_T1, acctYrMth1);
        }
    }
    // END 2018/04/06 J.Kim [QC#24561,ADD]
}
