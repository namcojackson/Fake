/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2030;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLAL2030.common.NLAL2030CommonLogic;
import business.blap.NLAL2030.constant.NLAL2030Constant;
import business.db.OTBD_CARR_VTMsg;
import business.db.OTBD_CARR_VTMsgArray;
import business.db.RTL_WHTMsg;
import business.db.SCE_ORD_TPTMsg;
import business.db.SWHTMsg;
import business.file.NLAL2030F00FMsg;
import business.file.NLAL2030F01FMsg;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.THIRD_PTY_DSP_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NLAL2030BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 * 05/19/2016   CSAI            Y.Imazu         Update          QC#8476
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#8663
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#9582
 * 06/06/2016   CSAI            Y.Imazu         Update          QC#8475
 * 06/09/2016   CSAI            Y.Imazu         Update          QC#7977
 * 06/09/2016   CSAI            Y.Imazu         Update          QC#7981
 * 06/15/2016   CSAI            Y.Imazu         Create          QC#6086
 * 06/08/2017   CITS            M.Naito         Update          QC#18382
 * 06/26/2017   CITS            M.Naito         Update          QC#19314
 * 02/06/2018   CITS            T.Tokutomi      Update          QC#18461-Sol#085
 * 11/19/2018   CITS            K.Ogino         Update          QC#28711
 * 12/03/2018   Fujitsu         S.Ohki          Update          QC#29461
 * 11/11/2021   CITS            A.Marte         Update          QC#59350
 * 10/26/2022   Hitachi         M.Kikushima     Update          QC#60413
 * 02/22/2023   Hitachi         TZ.Win          Update          QC#61161
 * 02/28/2023   Hitachi         TZ.Win          Update          QC#61160
 *</pre>
 */
public class NLAL2030BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NLAL2030CMsg bizMsg = (NLAL2030CMsg) cMsg;
            NLAL2030SMsg glblMsg = (NLAL2030SMsg) sMsg;

            if ("NLAL2030_INIT".equals(screenAplID)) {
                doProcess_NLAL2030_INIT(bizMsg, glblMsg);

                if (NLAL2030Constant.TAB_ID_ORD.equals(bizMsg.xxDplyTab.getValue())) {
                    ZYPGUITableColumn.getColData(bizMsg, glblMsg, "AHEAD");

                } else if (NLAL2030Constant.TAB_ID_RWS.equals(bizMsg.xxDplyTab.getValue())) {
                    ZYPGUITableColumn.getColData(bizMsg, glblMsg, "BHEAD");
                }

            } else if ("NLAL2030Scrn00_CMN_ColClear".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_CMN_ColClear(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_CMN_ColSave".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_CMN_ColSave(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_OnChangeSavedSearchOption(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_OnChange_ChkBoxHdr".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_OnChange_ChkBoxHdr(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_OnChange_ChkBoxLine".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_OnChange_ChkBoxLine(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_OnClick_CarrNm".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_OnClick_CarrNm(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_OnClick_PartyNm".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_OnClick_PartyNm(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_OnClick_SwhNm".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_OnClick_SwhNm(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_OnClick_WhNm".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_OnClick_WhNm(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_OnClick_RcvWhNm".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_OnClick_RcvWhNm(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_OpenWin_Party".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_OpenWin_Party(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_OpenWin_SerEntry".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_OpenWin_SerEntry(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_Print".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_Print(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_RWS_Create".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_RWS_Create(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_RWS_Cancel".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_RWS_Cancel(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_SaveSearch(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_Search".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_Search(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_Select_All".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_Select_All(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_Tab_Ord".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_Tab_Ord(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_Tab_Rws".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_Tab_Rws(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_UnSelect_All".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_UnSelect_All(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_CMN_Submit".equals(screenAplID)) {
                if (!"E".equals(bizMsg.getMessageKind()) //
                        && !"W".equals(bizMsg.getMessageKind())) {
                    doProcess_NLAL2030Scrn00_Tab_Rws(bizMsg, glblMsg);
                }

            } else if ("NLAL2030Scrn00_Apply".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_Apply(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_getWhNameFromApply".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_getWhNameFromApply(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_getWhNameFromRwsList".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_getWhNameFromRwsList(bizMsg, glblMsg);

            // START 2023/03/06 TZ.Win [QC#61160, ADD]
            } else if ("NLAL2030Scrn00_getSwhNameFromApply".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_getSwhNameFromApply(bizMsg, glblMsg);

            // END 2023/03/06 TZ.Win [QC#61160, ADD]
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     */
    private void doProcess_NLAL2030_INIT(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        // Clear
        clearAll(bizMsg, glblMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());

        // Search Option
        NLAL2030CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());

        // Create PulldownList
        ZYPCodeDataUtil.createPulldownList(RWS_STS.class, bizMsg.rwsStsCd_P, bizMsg.rwsStsDescTxt_P);
        // QC#18461-Sol#085 Add PulldownList.
        ZYPCodeDataUtil.createPulldownList(THIRD_PTY_DSP_TP.class, bizMsg.thirdPtyDspTpCd, bizMsg.thirdPtyDspTpDescTxt);

        // Source Document Type
        createPulldownListSceOrdTp(bizMsg);

        // Location List
        ZYPEZDItemValueSetter.setValue(bizMsg.srchOptTxt, NMXC100001EnableWH.getLocRoleTpForPopup(bizMsg.glblCmpyCd.getValue(), NLAL2030Constant.BIZ_ID));

        boolean doSearch = false;

        if (ZYPCommonFunc.hasValue(bizMsg.trxOrdNum_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.trxOrdNum, bizMsg.trxOrdNum_BK.getValue());
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.rwsRefNum_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rwsRefNum, bizMsg.rwsRefNum_BK.getValue());
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.rwsNum_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum, bizMsg.rwsNum_BK.getValue());
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk, bizMsg.svcConfigMstrPk_BK.getValue());
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.imptInvBolNum_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.imptInvBolNum, bizMsg.imptInvBolNum_BK.getValue());
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.rtlWhCd_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd, bizMsg.rtlWhCd_BK.getValue());
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxDplyTab_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, bizMsg.xxDplyTab_BK.getValue());
        }

        if (doSearch) {

            NLAL2030CommonLogic.search(bizMsg, glblMsg, NLAL2030Constant.SEARCH_MODE_HDR);

        } else {

            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, NLAL2030Constant.TAB_ID_ORD);
        }
    }

    /**
     * CMN_ColClear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_CMN_ColClear(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {
        return;
    }

    /**
     * CMN_ColSave Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_CMN_ColSave(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {
        return;
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_CMN_Download(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        if (!inputCheckSearch(bizMsg)) {

            return;
        }

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(NLAL2030Constant.MAX_FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NLAL2030Query.getInstance().getClass());

        //create csv file, parameters
        List<String> querySsmIdOrTpList = new ArrayList<String>();
        List<String> sceOrdTpList = new  ArrayList<String>();

        if (ZYPCommonFunc.hasValue(bizMsg.sceOrdTpCd)) {

            sceOrdTpList = NLAL2030CommonLogic.getSceOrdTpListBefMarg(bizMsg);
        }

        if (NLAL2030Constant.TAB_ID_ORD.equalsIgnoreCase(bizMsg.xxDplyTab.getValue())) {

            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NLAL2030Constant.CSV_FILE_NAME_ORD), ".csv");
            querySsmIdOrTpList = NLAL2030CommonLogic.getSsmIdOrTpList(bizMsg, sceOrdTpList, NLAL2030Constant.TAB_ID_ORD);

            Map<String, Object> ssMParam = NLAL2030Query.getSsmParamDownLoadOrd(bizMsg, NLAL2030Constant.LIMIT_DL_ROWNUM + 1, sceOrdTpList);
            writeCsvFileOrd(bizMsg, querySsmIdOrTpList, ssMParam, execParam, ssmLLClient);

        } else if (NLAL2030Constant.TAB_ID_RWS.equalsIgnoreCase(bizMsg.xxDplyTab.getValue())) {

            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NLAL2030Constant.CSV_FILE_NAME_RWS), ".csv");
            querySsmIdOrTpList = NLAL2030CommonLogic.getSsmIdOrTpList(bizMsg, sceOrdTpList, NLAL2030Constant.TAB_ID_RWS);

            writeCsvFileRws(bizMsg, glblMsg, querySsmIdOrTpList, execParam, ssmLLClient, sceOrdTpList);
        }
    }

    /**
     * writeCsvFileOrd
     * @param bizMsg NLAL2030CMsg
     * @param querySsmIdList List<String>
     * @param Map<String, Object> ssMParam
     * @param execParam S21SsmExecutionParameter
     * @param ssmLLClient S21SsmLowLevelCodingClient
     * @throws SQLException
     */
    private void writeCsvFileOrd(NLAL2030CMsg bizMsg, List<String> querySsmIdList, Map<String, Object> ssMParam, S21SsmExecutionParameter execParam, S21SsmLowLevelCodingClient ssmLLClient) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {

            NLAL2030F00FMsg fMsg = new NLAL2030F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
            fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

            fMsg.addExclusionItem("xxChkBox_A1");
            fMsg.addExclusionItem("xxChkBox_A2");

            // write header
            csvOutFile.writeHeader(NLAL2030Constant.CSV_HDR_ORD, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

            int queryCnt = 0;
            boolean noQueryData = true;

            ps = ssmLLClient.createPreparedStatement("searchOrdDl", ssMParam, execParam);
            rs = ps.executeQuery();
            // START 2018/12/03 S.Ohki [QC#29461,ADD]
            boolean rsNoDataflg = false;
            // END 2018/12/03 S.Ohki [QC#29461,ADD]

            if (!rs.next()) {

                if (noQueryData && queryCnt + 1 >= querySsmIdList.size()) {

                    // START 2018/12/03 S.Ohki [QC#29461,DEL]
//                    bizMsg.setMessageInfo("NZZM0000E", null);
                    // END 2018/12/03 S.Ohki [QC#29461,DEL]
                    csvOutFile.close();
                    return;

                }
                // START 2018/12/03 S.Ohki [QC#29461,ADD]
                rsNoDataflg = true;
                // END 2018/12/03 S.Ohki [QC#29461,ADD]
            }

            noQueryData = false;

            // write contents
            do {
                // START 2018/12/03 S.Ohki [QC#29461,ADD]
                if (rsNoDataflg) {
                	break;
                }
                // END 2018/12/03 S.Ohki [QC#29461,ADD]

                if (queryCnt + rs.getRow() >= NLAL2030Constant.LIMIT_DL_ROWNUM) {

                    bizMsg.setMessageInfo("NZZM0001W", null);
                    break;
                }

                // resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.sceOrdTpNm, rs.getString("SCE_ORD_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.trxOrdNum, rs.getString("SRC_DOC_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.dplyLineNum, rs.getString("DPLY_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.fromLocCd, rs.getString("PTY_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString("PTY_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm, rs.getString("RTL_WH_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.flipMdseCd, rs.getString("FLIP_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.rwsQty, rs.getBigDecimal("ORD_QTY"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxQty10Num, rs.getBigDecimal("BAL_QTY"));
                ZYPEZDItemValueSetter.setValue(fMsg.serNum, rs.getString("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.rwsOpenFlg, rs.getString("RWS_OPEN_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.coaMdseTpCd, rs.getString("COA_MDSE_TP_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.splyItemNum, rs.getString("ASL_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd, rs.getString("RTL_SWH_CD"));
                // add QC#18382
                // check Kitting Cancel
                if (ZYPCommonFunc.hasValue(bizMsg.sceOrdTpCd.getValue()) && !bizMsg.sceOrdTpCd.getValue().equals(rs.getString("SCE_ORD_TP_CD"))) {
                    continue;
                }
                S21SsmEZDResult sceOrdTp = null;
                if (NLAL2030Constant.DCC_UNKITTING_CANCEL.equals(rs.getString("SCE_ORD_TP_CD"))) {
                    sceOrdTp = NLAL2030Query.getInstance().checkKittingCancel(bizMsg.glblCmpyCd.getValue(), rs.getString("RWS_NUM"));
                    if (!sceOrdTp.isCodeNotFound() && !NLAL2030Constant.DCC_UNKITTING_CANCEL.equals(sceOrdTp.getResultObject().toString())) {
                        continue;
                    }
                } else if (NLAL2030Constant.DCC_KITTING_CANCEL.equals(rs.getString("SCE_ORD_TP_CD"))) {
                    sceOrdTp = NLAL2030Query.getInstance().checkKittingCancel(bizMsg.glblCmpyCd.getValue(), rs.getString("RWS_NUM"));
                    if (!sceOrdTp.isCodeNotFound() && !NLAL2030Constant.DCC_KITTING_CANCEL.equals(sceOrdTp.getResultObject().toString())) {
                        continue;
                    }
                }
                csvOutFile.write();

            } while (rs.next());

            queryCnt++;
            S21SsmLowLevelCodingClient.closeResource(ps, rs);

            csvOutFile.close();

        } catch (SQLException e) {

            throw new S21AbendException(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * writeCsvFileRws
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param querySsmTpList List<String>
     * @param execParam S21SsmExecutionParameter
     * @param ssmLLClient S21SsmLowLevelCodingClient
     * @param sceOrdTpList List<String>
     */
    private void writeCsvFileRws(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, List<String> querySsmTpList, S21SsmExecutionParameter execParam, S21SsmLowLevelCodingClient ssmLLClient, List<String> sceOrdTpList) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {

            NLAL2030F01FMsg fMsg = new NLAL2030F01FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
            fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

            fMsg.addExclusionItem("xxChkBox");

            //write header
            csvOutFile.writeHeader(NLAL2030Constant.CSV_HDR_RWS, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

            int queryCnt = 0;
            boolean noQueryData = true;

            for (String querySsmTp : querySsmTpList) {

                Map<String, Object> ssMParam = NLAL2030Query.getSsmParamDownLoadRws(bizMsg, glblMsg, NLAL2030Constant.LIMIT_DL_ROWNUM + 1, querySsmTp, sceOrdTpList);

                ps = ssmLLClient.createPreparedStatement("searchRws", ssMParam, execParam);
                rs = ps.executeQuery();

                if (!rs.next()) {

                    if (noQueryData && queryCnt + 1 >= querySsmTpList.size()) {

                        bizMsg.setMessageInfo("NZZM0000E", null);
                        csvOutFile.close();
                        return;

                    } else {

                        continue;
                    }
                }

                noQueryData = false;

                //write contents
                do {

                    if (queryCnt + rs.getRow() >= NLAL2030Constant.LIMIT_DL_ROWNUM) {

                        bizMsg.setMessageInfo("NZZM0001W", null);
                        break;
                    }

                    //resultSet -> fMsg
                    ZYPEZDItemValueSetter.setValue(fMsg.rwsStsDescTxt, rs.getString("RWS_HDR_STS_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.rwsNum, rs.getString("RWS_NUM"));
                    // START 2023/02/22 TZ.Win [QC#61161, ADD]
                    ZYPEZDItemValueSetter.setValue(fMsg.xxCratDt, rs.getString("ASN_CRAT_DT"));
                    // END 2023/02/22 TZ.Win [QC#61161, ADD]
                    ZYPEZDItemValueSetter.setValue(fMsg.rwsDtlLineNum, rs.getString("RWS_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm, rs.getString("RCV_RTL_WH_NM"));
                    ZYPEZDItemValueSetter.setValue(fMsg.rwsRefNum, rs.getString("RWS_REF_NUM"));
                    // QC#18461-Sol#085 Add.
                    ZYPEZDItemValueSetter.setValue(fMsg.thirdPtyDspTpDescTxt, rs.getString("THIRD_PTY_DSP_TP_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.imptInvBolNum, rs.getString("BOL_NUM"));
                    ZYPEZDItemValueSetter.setValue(fMsg.sceOrdTpNm, rs.getString("SCE_ORD_TP_NM"));
                    ZYPEZDItemValueSetter.setValue(fMsg.trxOrdNum, rs.getString("SRC_ORD_NUM"));
                    ZYPEZDItemValueSetter.setValue(fMsg.dplyLineNum, rs.getString("DPLY_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(fMsg.fromLocCd, rs.getString("SHIP_FROM_LOC_CD"));
                    ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString("SHIP_FROM_LOC_NM"));
                    ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
                    ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(fMsg.flipMdseCd, rs.getString("FLIP_MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.rwsQty, rs.getBigDecimal("RWS_QTY"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxQty10Num, rs.getBigDecimal("RWS_BAL_QTY"));
                    ZYPEZDItemValueSetter.setValue(fMsg.serNum, rs.getString("SER_NUM"));
                    ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd, rs.getString("RCV_RTL_SWH_CD"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxRtlWhSrchTxt, rs.getString("ACTL_RTL_WH_NM"));
                    csvOutFile.write();

                } while (rs.next());

                queryCnt++;
                S21SsmLowLevelCodingClient.closeResource(ps, rs);
            }

            csvOutFile.close();

        } catch (SQLException e) {

            throw new S21AbendException(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_CMN_Reset(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {
        doProcess_NLAL2030_INIT(bizMsg, glblMsg);
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_CMN_Clear(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        // START 2018/12/03 S.Ohki [QC#29461,MOD]
//        if (NLAL2030Constant.TAB_ID_ORD.equalsIgnoreCase(bizMsg.xxDplyTab.getValue())) {
//
//            ZYPTableUtil.clear(bizMsg.A);
//            ZYPTableUtil.clear(glblMsg.A);
//            ZYPTableUtil.clear(glblMsg.C);
//            bizMsg.rwsRefNum_AP.clear();
//            bizMsg.imptInvBolNum_AP.clear();
//
//        } else if (NLAL2030Constant.TAB_ID_RWS.equalsIgnoreCase(bizMsg.xxDplyTab.getValue())) {
//
//            ZYPTableUtil.clear(bizMsg.B);
//            ZYPTableUtil.clear(glblMsg.B);
//            ZYPTableUtil.clear(glblMsg.D);
//        }

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.C);
        bizMsg.rwsRefNum_AP.clear();
        bizMsg.imptInvBolNum_AP.clear();

        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.D);

        bizMsg.rtlWhCd_AP.clear();
        bizMsg.rtlWhNm_AP.clear();
        bizMsg.thirdPtyDspTpCd_AP.clear();
        // END 2018/12/03 S.Ohki [QC#29461,MOD]
        // START 2023/02/28 TZ.Win [QC#61160, ADD]
        bizMsg.rtlSwhCd_AP.clear();
        bizMsg.rtlSwhNm_AP.clear();
        // END 2023/02/28 TZ.Win [QC#61160, ADD]

        bizMsg.xxWrnSkipFlg.clear();
        ZYPTableUtil.clear(bizMsg.S);
        ZYPTableUtil.clear(bizMsg.P);

        // Header
        bizMsg.trxOrdNum.clear();
        bizMsg.mdseCd.clear();
        bizMsg.fromLocCd.clear();
        bizMsg.dsAcctNm.clear();
        bizMsg.sceOrdTpCd.clear();
        bizMsg.svcConfigMstrPk.clear();
        bizMsg.rtlWhCd.clear();
        bizMsg.rtlWhNm.clear();
        bizMsg.rwsRefNum.clear();
        bizMsg.serNum.clear();
        bizMsg.rtlSwhCd.clear();
        bizMsg.rtlSwhNm.clear();
        bizMsg.whInEtaDt_FR.clear();
        bizMsg.whInEtaDt_TO.clear();
        bizMsg.imptInvBolNum.clear();
        bizMsg.carrCd.clear();
        bizMsg.carrNm.clear();
        bizMsg.rwsNum.clear();
        bizMsg.rwsStsCd.clear();
        bizMsg.flipMdseCd.clear();
        bizMsg.whCd.clear();
        bizMsg.whNm.clear();

        // Search Option
        bizMsg.srchOptPk_S.clear();
        bizMsg.srchOptNm_S.clear();
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_DeleteSearch(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {
        return;
    }

    /**
     * OnChangeSavedSearchOption Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_OnChangeSavedSearchOption(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            NLAL2030CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId());
        }
    }

    /**
     * doProcess_NLAL2030Scrn00_OnChange_ChkBoxHdr
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     */
    private void doProcess_NLAL2030Scrn00_OnChange_ChkBoxHdr(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        int eventLine = bizMsg.xxNum.getValueInt();
        String val = "";
        String trxOrdNum = "";
        String sceOrdTpNm = "";
        String dsOrdPosnNum = "";

        for (int i = eventLine; i < bizMsg.A.getValidCount(); i++) {

            NLAL2030_ACMsg cMsgALine = bizMsg.A.no(i);

            if (eventLine == i) {

                val = cMsgALine.xxChkBox_A1.getValue();
                trxOrdNum = cMsgALine.trxOrdNum_A1.getValue();
                sceOrdTpNm = cMsgALine.sceOrdTpNm_A1.getValue();
                dsOrdPosnNum = cMsgALine.dsOrdPosnNum_A1.getValue();
                ZYPEZDItemValueSetter.setValue(cMsgALine.xxChkBox_A2, val);

                if (ZYPConstant.FLG_ON_Y.equals(cMsgALine.rwsOpenFlg_A1.getValue()) || !ZYPConstant.FLG_ON_Y.equals(cMsgALine.rwsFlg_A1.getValue())) {

                    cMsgALine.xxChkBox_A2.clear();
                }

                continue;
            }

            if (trxOrdNum.equals(cMsgALine.trxOrdNum_A1.getValue())
                    && sceOrdTpNm.equals(cMsgALine.sceOrdTpNm_A1.getValue())
                    && isSameVal(dsOrdPosnNum, cMsgALine.dsOrdPosnNum_A1.getValue())) {

                ZYPEZDItemValueSetter.setValue(cMsgALine.xxChkBox_A2, val);

                if (ZYPConstant.FLG_ON_Y.equals(cMsgALine.rwsOpenFlg_A1.getValue()) || !ZYPConstant.FLG_ON_Y.equals(cMsgALine.rwsFlg_A1.getValue())) {

                    cMsgALine.xxChkBox_A2.clear();
                }

            } else {

                break;
            }
        }

        int pageNum = (bizMsg.xxPageShowFromNum_A.getValueInt() - 1) + eventLine;

        for (int i = pageNum; i < glblMsg.A.getValidCount(); i++) {

            NLAL2030_ASMsg sMsgALine = glblMsg.A.no(i);

            if (trxOrdNum.equals(sMsgALine.trxOrdNum_A1.getValue())
                    && sceOrdTpNm.equals(sMsgALine.sceOrdTpNm_A1.getValue())
                    && isSameVal(dsOrdPosnNum, sMsgALine.dsOrdPosnNum_A1.getValue())) {

                ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A1, val);
                ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A2, val);

                if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.rwsOpenFlg_A1.getValue()) || !ZYPConstant.FLG_ON_Y.equals(sMsgALine.rwsFlg_A1.getValue())) {

                    sMsgALine.xxChkBox_A2.clear();
                }

            } else {

                break;
            }
        }
    }

    /**
     * doProcess_NLAL2030Scrn00_OnChange_ChkBoxLine
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     */
    private void doProcess_NLAL2030Scrn00_OnChange_ChkBoxLine(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        int eventLine = bizMsg.xxNum.getValueInt();
        String val = bizMsg.A.no(eventLine).xxChkBox_A2.getValue();
        int sLineNum = (bizMsg.xxPageShowFromNum_A.getValueInt() - 1) + eventLine;
        int trxOrdLineNum = sLineNum;

        // ON --> OFF
        String preTrxOrdNum = glblMsg.A.no(sLineNum).trxOrdNum_A1.getValue();
        String preSceOrdTpNm = glblMsg.A.no(sLineNum).sceOrdTpNm_A1.getValue();
        String preDsOrdPosnNum = glblMsg.A.no(sLineNum).dsOrdPosnNum_A1.getValue();

        if (!ZYPConstant.FLG_ON_Y.equals(val)) {

            // sMsg BackLine
            for (int i = sLineNum; i >= 0; i--) {

                NLAL2030_ASMsg sMsgALine = glblMsg.A.no(i);

                if (i == sLineNum) {

                    glblMsg.A.no(i).xxChkBox_A2.clear();
                }

                if (preTrxOrdNum.equals(sMsgALine.trxOrdNum_A1.getValue())
                        && preSceOrdTpNm.equals(sMsgALine.sceOrdTpNm_A1.getValue())
                        && isSameVal(preDsOrdPosnNum, sMsgALine.dsOrdPosnNum_A1.getValue())) {

                    glblMsg.A.no(i).xxChkBox_A1.clear();
                    trxOrdLineNum = i;

                } else {

                    trxOrdLineNum = i + 1;
                    break;
                }
            }

            // Ahead Line
            preTrxOrdNum = glblMsg.A.no(sLineNum).trxOrdNum_A1.getValue();
            preSceOrdTpNm = glblMsg.A.no(sLineNum).sceOrdTpNm_A1.getValue();
            preDsOrdPosnNum = glblMsg.A.no(sLineNum).dsOrdPosnNum_A1.getValue();

            for (int i = sLineNum; i < glblMsg.A.getValidCount(); i++) {

                NLAL2030_ASMsg sMsgALine = glblMsg.A.no(i);

                if (preTrxOrdNum.equals(sMsgALine.trxOrdNum_A1.getValue())
                        && preSceOrdTpNm.equals(sMsgALine.sceOrdTpNm_A1.getValue())
                        && isSameVal(preDsOrdPosnNum, sMsgALine.dsOrdPosnNum_A1.getValue())) {

                    sMsgALine.xxChkBox_A1.clear();

                } else {

                    break;
                }
            }

            // cMsg
            if (trxOrdLineNum - (bizMsg.xxPageShowFromNum_A.getValueInt() - 1) >= 0) {

                int cMsgHdrRcrd = trxOrdLineNum - (bizMsg.xxPageShowFromNum_A.getValueInt() - 1);
                bizMsg.A.no(cMsgHdrRcrd).xxChkBox_A1.clear();
            }

            return;
        }

        // OFF --> ON
        int offCount = 0;
        trxOrdLineNum = sLineNum;

        if (ZYPConstant.FLG_ON_Y.equals(val)) {

            // Back Line
            preTrxOrdNum = glblMsg.A.no(sLineNum).trxOrdNum_A1.getValue();
            preSceOrdTpNm = glblMsg.A.no(sLineNum).sceOrdTpNm_A1.getValue();
            preDsOrdPosnNum = glblMsg.A.no(sLineNum).dsOrdPosnNum_A1.getValue();

            for (int i = sLineNum; i >= 0; i--) {

                NLAL2030_ASMsg sMsgALine = glblMsg.A.no(i);

                if (i == sLineNum) {

                    ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A2, ZYPConstant.FLG_ON_Y);
                    continue;
                }

                if (preTrxOrdNum.equals(sMsgALine.trxOrdNum_A1.getValue())
                        && preSceOrdTpNm.equals(sMsgALine.sceOrdTpNm_A1.getValue())
                        && isSameVal(preDsOrdPosnNum, sMsgALine.dsOrdPosnNum_A1.getValue())) {

                    // Off
                    if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue())
                            && !ZYPConstant.FLG_ON_Y.equals(sMsgALine.rwsOpenFlg_A1.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsgALine.rwsFlg_A1.getValue())) {

                        offCount++;
                    }

                    trxOrdLineNum = i;

                } else {

                    trxOrdLineNum = i + 1;
                    break;
                }
            }

            // Ahead Line
            preTrxOrdNum = glblMsg.A.no(sLineNum).trxOrdNum_A1.getValue();
            preSceOrdTpNm = glblMsg.A.no(sLineNum).sceOrdTpNm_A1.getValue();
            preDsOrdPosnNum = glblMsg.A.no(sLineNum).dsOrdPosnNum_A1.getValue();

            for (int i = sLineNum; i < bizMsg.A.getValidCount(); i++) {

                NLAL2030_ASMsg sMsgALine = glblMsg.A.no(i);

                if (i == sLineNum) {

                    ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A2, ZYPConstant.FLG_ON_Y);
                    continue;
                }

                if (preTrxOrdNum.equals(sMsgALine.trxOrdNum_A1.getValue())
                        && preSceOrdTpNm.equals(sMsgALine.sceOrdTpNm_A1.getValue())
                        && isSameVal(preDsOrdPosnNum, sMsgALine.dsOrdPosnNum_A1.getValue())) {

                    // Off
                    if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue())
                            && !ZYPConstant.FLG_ON_Y.equals(sMsgALine.rwsOpenFlg_A1.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsgALine.rwsFlg_A1.getValue())) {

                        offCount++;
                    }

                } else {

                    break;
                }
            }

            if (offCount == 0) {

                for (int i = trxOrdLineNum; i < glblMsg.A.getValidCount(); i++) {

                    NLAL2030_ASMsg sMsgALine = glblMsg.A.no(i);

                    if (preTrxOrdNum.equals(sMsgALine.trxOrdNum_A1.getValue())
                            && preSceOrdTpNm.equals(sMsgALine.sceOrdTpNm_A1.getValue())
                            && isSameVal(preDsOrdPosnNum, sMsgALine.dsOrdPosnNum_A1.getValue())) {

                        ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A1, ZYPConstant.FLG_ON_Y);

                    } else {

                        break;
                    }
                }
            }

            // cMsg
            if (offCount == 0) {

                if (trxOrdLineNum - (bizMsg.xxPageShowFromNum_A.getValueInt() - 1) >= 0) {

                    int cMsgHdrRcrd = trxOrdLineNum - (bizMsg.xxPageShowFromNum_A.getValueInt() - 1);
                    bizMsg.A.no(cMsgHdrRcrd).xxChkBox_A1.setValue(ZYPConstant.FLG_ON_Y);
                }
            }

            return;
        }
    }

    /**
     * isSameVal
     * @param val1 String
     * @param val2 String
     * @return boolean
     */
    private static boolean isSameVal(String val1, String val2) {

        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (val1.equals(val2)) {

            return true;
        }

        return false;
    }

    /**
     * OnClick_CarrNm Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_OnClick_CarrNm(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.carrCd)) {

            OTBD_CARR_VTMsg otbdCarr = getCarr(bizMsg);

            if (otbdCarr != null) {

                ZYPEZDItemValueSetter.setValue(bizMsg.carrNm, otbdCarr.carrNm.getValue());

            } else {

                bizMsg.carrCd.setErrorInfo(1, "NLZM2278E", new String[]{"Carrier"});
                bizMsg.carrNm.clear();
                return;
            }

        } else {

            bizMsg.carrCd.setErrorInfo(1, "ZZM9000E", new String[]{"Carrier"});
            bizMsg.carrNm.clear();
        }
    }

    /**
     * OnClick_PartyNm Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_OnClick_PartyNm(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.fromLocCd)) {

            Map<String, Object> fromLocMap = getFromLoc(bizMsg);

            if (fromLocMap != null) {

                ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm, (String) fromLocMap.get("RTL_WH_NM"));

            } else {

                bizMsg.fromLocCd.setErrorInfo(1, "NLZM2278E", new String[]{"Party"});
                bizMsg.dsAcctNm.clear();
                return;
            }

        } else {

            bizMsg.fromLocCd.setErrorInfo(1, "ZZM9000E", new String[]{"Party"});
            bizMsg.dsAcctNm.clear();
        }
    }

    /**
     * OnClick_SwhNm Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_OnClick_SwhNm(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.rtlSwhCd)) {

            SWHTMsg swhTMsg = getRtlSwh(bizMsg);

            if (swhTMsg != null) {

                ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhNm, swhTMsg.rtlSwhNm.getValue());

            } else {

                bizMsg.rtlSwhCd.setErrorInfo(1, "NLZM2278E", new String[]{"Sub Warehouse"});
                bizMsg.rtlSwhNm.clear();
                return;
            }

        } else {

            bizMsg.rtlSwhCd.setErrorInfo(1, "ZZM9000E", new String[]{"Sub Warehouse"});
            bizMsg.rtlSwhNm.clear();
        }
    }

    /**
     * OnClick_WhNm Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_OnClick_WhNm(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.rtlWhCd)) {

            RTL_WHTMsg rtlWh = getRtlWh(bizMsg.glblCmpyCd.getValue(), bizMsg.rtlWhCd.getValue());

            if (rtlWh != null) {

                ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm, rtlWh.rtlWhNm.getValue());

            } else {

                bizMsg.rtlWhCd.setErrorInfo(1, "NLZM2278E", new String[]{"Warehouse"});
                bizMsg.rtlWhNm.clear();
                return;
            }

        } else {

            bizMsg.rtlWhCd.setErrorInfo(1, "ZZM9000E", new String[]{"Warehouse"});
            bizMsg.rtlWhNm.clear();
        }
    }

    /**
     * OnClick_RcvWhNm Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_OnClick_RcvWhNm(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.whCd)) {

            RTL_WHTMsg rtlWh = getRtlWh(bizMsg.glblCmpyCd.getValue(), bizMsg.whCd.getValue());

            if (rtlWh != null) {

                ZYPEZDItemValueSetter.setValue(bizMsg.whNm, rtlWh.rtlWhNm.getValue());

            } else {

                bizMsg.whCd.setErrorInfo(1, "NLZM2278E", new String[]{"Received WH"});
                bizMsg.whNm.clear();
                return;
            }

        } else {

            bizMsg.whCd.setErrorInfo(1, "ZZM9000E", new String[]{"Received WH"});
            bizMsg.whNm.clear();
        }
    }

    /**
     * OpenWin_Party Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_OpenWin_Party(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopSqlTxt, getPartyCdSelectSql(bizMsg, null));
    }

    /**
     * OpenWin_SerEntry Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_OpenWin_SerEntry(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.S);
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();

        if (NLAL2030Constant.TAB_ID_ORD.equalsIgnoreCase(bizMsg.xxDplyTab.getValue())) {

            int index = bizMsg.xxNum.getValueInt();
            String ordNum = bizMsg.A.no(index).trxOrdNum_A1.getValue();
            String ordLineNum = bizMsg.A.no(index).trxLineNum_A1.getValue();
            String ordLineSubNum = bizMsg.A.no(index).trxLineSubNum_A1.getValue();
            NLAL2030_ACMsg bizMsgLine = bizMsg.A.no(index);
            String invtyLocCd = null;

            if (!ZYPConstant.FLG_ON_Y.equals(bizMsgLine.serNumTakeFlg_A1.getValue())) {

                return;
            }

            int sIndex = 0;

            //Search in glblMsg
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {

                NLAL2030_CSMsg serNumInfo = glblMsg.C.no(i);

                if (ordNum.equals(serNumInfo.trxOrdNum.getValue()) && ordLineNum.equals(serNumInfo.trxLineNum.getValue())) {

                    if (!ZYPCommonFunc.hasValue(ordLineSubNum) || (ZYPCommonFunc.hasValue(ordLineSubNum) && ordLineSubNum.equals(serNumInfo.trxLineSubNum.getValue()))) {

                        // Found -> Copy
                        NLAL2030_SCMsg serEntryPop = bizMsg.S.no(sIndex);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.xxEdtModeFlg_SN, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.xxHdrNum_SN, bizMsgLine.trxOrdNum_A1);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.xxDplyTrxNumTxt_SN, bizMsgLine.dplyLineNum_A1);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.mdseCd_SN, bizMsgLine.mdseCd_A1);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.rtlSwhCd_SN, bizMsgLine.rtlSwhCd_A1);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.svcConfigMstrPk_SN, bizMsgLine.svcConfigMstrPk_A1);

                        if (invtyLocCd == null) {

                            invtyLocCd = NLAL2030CommonLogic.getInvtyLocCd(bizMsg, bizMsgLine.rtlWhCd_A1.getValue(), bizMsgLine.rtlSwhCd_A1.getValue());
                        }

                        ZYPEZDItemValueSetter.setValue(serEntryPop.invtyLocCd_SN, invtyLocCd);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.serNumTakeFlg_SN, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.serNum_SN, serNumInfo.serNum);
                        sIndex++;
                    }
                }
            }

            bizMsg.S.setValidCount(sIndex);

            if (bizMsg.S.getValidCount() < 1) {

                // Search in DB
                S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getSerNumListForOrd(glblCmpyCd, ordNum, ordLineNum, ordLineSubNum);

                if (!ssmResult.isCodeNotFound()) {

                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

                    for (Map<String, Object> serNumMap : resultList) {

                        // Copy Popup parameter
                        NLAL2030_SCMsg serEntryPop = bizMsg.S.no(sIndex);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.xxEdtModeFlg_SN, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.xxHdrNum_SN, bizMsgLine.trxOrdNum_A1);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.xxDplyTrxNumTxt_SN, bizMsgLine.dplyLineNum_A1);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.mdseCd_SN, bizMsgLine.mdseCd_A1);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.rtlSwhCd_SN, bizMsgLine.rtlSwhCd_A1);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.svcConfigMstrPk_SN, bizMsgLine.svcConfigMstrPk_A1);

                        if (invtyLocCd == null) {

                            invtyLocCd = NLAL2030CommonLogic.getInvtyLocCd(bizMsg, bizMsgLine.rtlWhCd_A1.getValue(), bizMsgLine.rtlSwhCd_A1.getValue());
                        }

                        ZYPEZDItemValueSetter.setValue(serEntryPop.invtyLocCd_SN, invtyLocCd);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.serNumTakeFlg_SN, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.serNum_SN, (String) serNumMap.get("SER_NUM"));
                        sIndex++;

                        // Copy glblMsg
                        NLAL2030_CSMsg serNumInfo = glblMsg.C.no(glblMsg.C.getValidCount());
                        ZYPEZDItemValueSetter.setValue(serNumInfo.trxOrdNum, ordNum);
                        ZYPEZDItemValueSetter.setValue(serNumInfo.trxLineNum, ordLineNum);
                        ZYPEZDItemValueSetter.setValue(serNumInfo.trxLineSubNum, ordLineSubNum);
                        ZYPEZDItemValueSetter.setValue(serNumInfo.mdseCd, (String) serNumMap.get("MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(serNumInfo.serNum, (String) serNumMap.get("SER_NUM"));
                    }
                }

                bizMsg.S.setValidCount(sIndex);
            }

        } else if (NLAL2030Constant.TAB_ID_RWS.equalsIgnoreCase(bizMsg.xxDplyTab.getValue())) {

            int index = bizMsg.xxNum.getValueInt();
            String rwsNum = bizMsg.B.no(index).rwsNum_B1.getValue();
            String rwsLineNum = bizMsg.B.no(index).rwsDtlLineNum_B1.getValue();
            NLAL2030_BCMsg bizMsgLine = bizMsg.B.no(index);

            if (!ZYPConstant.FLG_ON_Y.equals(bizMsgLine.serNumTakeFlg_B1.getValue())) {

                return;
            }

            int sIndex = 0;

            //Search in glblMsg
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {

                NLAL2030_DSMsg serNumInfo = glblMsg.D.no(i);

                if (rwsNum.equals(serNumInfo.rwsNum.getValue()) && rwsLineNum.equals(serNumInfo.rwsDtlLineNum.getValue())) {

                    // Found -> Copy
                    NLAL2030_SCMsg serEntryPop = bizMsg.S.no(sIndex);
                    ZYPEZDItemValueSetter.setValue(serEntryPop.xxEdtModeFlg_SN, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(serEntryPop.xxHdrNum_SN, bizMsgLine.rwsNum_B1);
                    ZYPEZDItemValueSetter.setValue(serEntryPop.xxDplyTrxNumTxt_SN, bizMsgLine.rwsDtlLineNum_B1);
                    ZYPEZDItemValueSetter.setValue(serEntryPop.mdseCd_SN, bizMsgLine.mdseCd_B1);
                    ZYPEZDItemValueSetter.setValue(serEntryPop.rtlSwhCd_SN, bizMsgLine.rtlSwhCd_B1);
                    ZYPEZDItemValueSetter.setValue(serEntryPop.svcConfigMstrPk_SN, bizMsgLine.svcConfigMstrPk_B1);
                    ZYPEZDItemValueSetter.setValue(serEntryPop.invtyLocCd_SN, bizMsgLine.rcvInvtyLocCd_B1);
                    ZYPEZDItemValueSetter.setValue(serEntryPop.serNumTakeFlg_SN, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(serEntryPop.serNum_SN, serNumInfo.serNum);
                    sIndex++;
                }
            }

            bizMsg.S.setValidCount(sIndex);

            if (bizMsg.S.getValidCount() < 1) {

                String ssmId = "";
                int serCnt = 0;

                if (BigDecimal.ZERO.compareTo(bizMsgLine.rwsPutAwayQty_B1.getValue()) < 0) {

                    ssmId = "getPutAwaySerNumList";
                    serCnt = bizMsgLine.rwsPutAwayQty_B1.getValueInt();

                } else {

                    ssmId = "getRwsSerNumList";
                    serCnt = bizMsgLine.rwsQty_B1.getValueInt();
                }

                // Search in DB
                S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getSerNumListForRws(ssmId, glblCmpyCd, rwsNum, rwsLineNum);

                if (!ssmResult.isCodeNotFound()) {

                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

                    for (Map<String, Object> serNumMap : resultList) {

                        // Copy Popup parameter
                        if (sIndex == serCnt) {

                            break;
                        }

                        NLAL2030_SCMsg serEntryPop = bizMsg.S.no(sIndex);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.xxEdtModeFlg_SN, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.xxHdrNum_SN, bizMsgLine.rwsNum_B1);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.xxDplyTrxNumTxt_SN, bizMsgLine.rwsDtlLineNum_B1);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.mdseCd_SN, bizMsgLine.mdseCd_B1);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.rtlSwhCd_SN, bizMsgLine.rtlSwhCd_B1);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.svcConfigMstrPk_SN, bizMsgLine.svcConfigMstrPk_B1);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.invtyLocCd_SN, bizMsgLine.rcvInvtyLocCd_B1);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.serNumTakeFlg_SN, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(serEntryPop.serNum_SN, (String) serNumMap.get("SER_NUM"));
                        sIndex++;

                        // Copy glblMsg
                        NLAL2030_DSMsg serNumInfo = glblMsg.D.no(glblMsg.D.getValidCount());
                        ZYPEZDItemValueSetter.setValue(serNumInfo.rwsNum, rwsNum);
                        ZYPEZDItemValueSetter.setValue(serNumInfo.rwsDtlLineNum, rwsLineNum);
                        ZYPEZDItemValueSetter.setValue(serNumInfo.mdseCd, (String) serNumMap.get("MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(serNumInfo.serNum, (String) serNumMap.get("SER_NUM"));
                    }
                }

                bizMsg.S.setValidCount(sIndex);
            }
        }
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_PageJump(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        NLAL2030CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        if (NLAL2030Constant.TAB_ID_ORD.equalsIgnoreCase(bizMsg.xxDplyTab.getValue())) {

            bizMsg.xxPageShowFromNum_A.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum_A.getValueInt() - 1)) + 1);
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        } else if (NLAL2030Constant.TAB_ID_RWS.equalsIgnoreCase(bizMsg.xxDplyTab.getValue())) {

            bizMsg.xxPageShowFromNum_B.setValue((bizMsg.B.length() * (bizMsg.xxPageShowCurNum_B.getValueInt() - 1)) + 1);
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);
        }
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_PageNext(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        NLAL2030CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        if (NLAL2030Constant.TAB_ID_ORD.equalsIgnoreCase(bizMsg.xxDplyTab.getValue())) {

            bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowToNum_A.getValueInt() + 1);
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        } else if (NLAL2030Constant.TAB_ID_RWS.equalsIgnoreCase(bizMsg.xxDplyTab.getValue())) {

            bizMsg.xxPageShowFromNum_B.setValue(bizMsg.xxPageShowToNum_B.getValueInt() + 1);
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);
        }
    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_PagePrev(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        NLAL2030CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        if (NLAL2030Constant.TAB_ID_ORD.equalsIgnoreCase(bizMsg.xxDplyTab.getValue())) {

            bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() - bizMsg.A.length());
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        } else if (NLAL2030Constant.TAB_ID_RWS.equalsIgnoreCase(bizMsg.xxDplyTab.getValue())) {

            bizMsg.xxPageShowFromNum_B.setValue(bizMsg.xxPageShowFromNum_B.getValueInt() - bizMsg.B.length());
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);
        }
    }

    /**
     * Print Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_Print(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        if ("40".equals(bizMsg.getFunctionCode())) {
            return;
        }

        // Re-Search
        NLAL2030CommonLogic.search(bizMsg, glblMsg, NLAL2030Constant.SEARCH_MODE_RESEARCH);
        bizMsg.setMessageInfo("ZZZM9003I", new String[]{"Print RWS"});
    }

    /**
     * RWS_Create Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_RWS_Create(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        // Re-Search
        NLAL2030CommonLogic.search(bizMsg, glblMsg, NLAL2030Constant.SEARCH_MODE_RESEARCH);
        bizMsg.setMessageInfo("ZZZM9003I", new String[]{"Create RWS"});
    }

    /**
     * RWS_Cancel Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_RWS_Cancel(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        // Re-Search
        // START 2022/10/26 M.Kikushima [QC#60413,MOD]
        //if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue())) {
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_CA.getValue())) {

            return;

        } else if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_CA.getValue())) {

            bizMsg.setMessageInfo(NLAL2030Constant.NLAM1358W, new String[] {"Cansel"});

            return;

        // END 2022/10/26 M.Kikushima [QC#60413,MOD]
        } else {

            NLAL2030CommonLogic.search(bizMsg, glblMsg, NLAL2030Constant.SEARCH_MODE_RESEARCH);
            bizMsg.setMessageInfo("ZZZM9003I", new String[]{"Cancel RWS"});
        }
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_SaveSearch(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {
        return;
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_Search(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        boolean errorExists = !inputCheckSearch(bizMsg);

        if (errorExists) {

            return;
        }

        NLAL2030CommonLogic.search(bizMsg, glblMsg, NLAL2030Constant.SEARCH_MODE_HDR);
    }

    /**
     * Select_All Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_Select_All(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        NLAL2030CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        if (NLAL2030Constant.TAB_ID_ORD.equals(bizMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                NLAL2030_ASMsg lineMsg = glblMsg.A.no(i);

                ZYPEZDItemValueSetter.setValue(lineMsg.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);

                if (!ZYPConstant.FLG_ON_Y.equals(lineMsg.rwsOpenFlg_A1.getValue()) && ZYPConstant.FLG_ON_Y.equals(lineMsg.rwsFlg_A1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(lineMsg.xxChkBox_A2, ZYPConstant.CHKBOX_ON_Y);

                } else {

                    lineMsg.xxChkBox_A2.clear();
                }
            }

            bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValue());
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        } else if (NLAL2030Constant.TAB_ID_RWS.equals(bizMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

                NLAL2030_BSMsg lineMsg = glblMsg.B.no(i);
                ZYPEZDItemValueSetter.setValue(lineMsg.xxChkBox_B1, ZYPConstant.CHKBOX_ON_Y);
            }

            bizMsg.xxPageShowFromNum_B.setValue(bizMsg.xxPageShowFromNum_B.getValue());
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);
        }
    }

    /**
     * Tab_Ord Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_Tab_Ord(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, NLAL2030Constant.TAB_ID_ORD);
        ZYPGUITableColumn.getColData(bizMsg, glblMsg, "AHEAD");
    }

    /**
     * Tab_Ord Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_Tab_Rws(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        NLAL2030CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        if (glblMsg.A.getValidCount() < 1) {

            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, NLAL2030Constant.TAB_ID_RWS);

        } else {

            boolean isChkOrd = false;

            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A1.getValue())) {

                    isChkOrd = true;
                    break;
                }
            }

            if (isChkOrd) {

                NLAL2030CommonLogic.searchRws(bizMsg, glblMsg, NLAL2030Constant.SEARCH_MODE_TAB, new ArrayList<String>());

            } else {

                ZYPTableUtil.clear(bizMsg.B);
                ZYPTableUtil.clear(glblMsg.B);
                ZYPTableUtil.clear(glblMsg.D);
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, NLAL2030Constant.TAB_ID_RWS);
        }

        ZYPGUITableColumn.getColData(bizMsg, glblMsg, "BHEAD");
    }

    /**
     * UnSelect_All Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_UnSelect_All(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        NLAL2030CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        if (NLAL2030Constant.TAB_ID_ORD.equals(bizMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                NLAL2030_ASMsg lineMsg = glblMsg.A.no(i);
                lineMsg.xxChkBox_A1.clear();
                lineMsg.xxChkBox_A2.clear();
            }

            bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValue());
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        } else if (NLAL2030Constant.TAB_ID_RWS.equals(bizMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

                NLAL2030_BSMsg lineMsg = glblMsg.B.no(i);
                lineMsg.xxChkBox_B1.clear();
            }

            bizMsg.xxPageShowFromNum_B.setValue(bizMsg.xxPageShowFromNum_B.getValue());
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);
        }
    }

    /**
     * createPulldownListSceOrdTp
     * @param bizMsg NLAL2030CMsg
     */
    private void createPulldownListSceOrdTp(NLAL2030CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getSceOrdTpList(bizMsg.glblCmpyCd.getValue());

        if (ssmResult.isCodeNotFound()) {

            return;
        }

        List<SCE_ORD_TPTMsg> outMsg = (List<SCE_ORD_TPTMsg>) ssmResult.getResultObject();

        for (int i = 0; i < outMsg.size(); i++) {

            if (i >= bizMsg.sceOrdTpCd_P.length()) {

                break;
            }

            SCE_ORD_TPTMsg sceOrdTpTMsg = (SCE_ORD_TPTMsg) outMsg.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.sceOrdTpCd_P.no(i), sceOrdTpTMsg.sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.sceOrdTpNm_P.no(i), sceOrdTpTMsg.sceOrdTpNm);
        }
    }

    /**
     * clearAll
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     */
    private void clearAll(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);
        ZYPTableUtil.clear(glblMsg.D);
        ZYPTableUtil.clear(bizMsg.S);
        ZYPTableUtil.clear(bizMsg.P);
        glblMsg.clear();

        // Search Option
        bizMsg.srchOptPk_S.clear();
        bizMsg.srchOptNm_S.clear();

        // Header
        bizMsg.trxOrdNum.clear();
        bizMsg.mdseCd.clear();
        bizMsg.fromLocCd.clear();
        bizMsg.dsAcctNm.clear();
        bizMsg.sceOrdTpCd.clear();
        bizMsg.svcConfigMstrPk.clear();
        bizMsg.rtlWhCd.clear();
        bizMsg.rtlWhNm.clear();
        bizMsg.rwsRefNum.clear();
        bizMsg.serNum.clear();
        bizMsg.rtlSwhCd.clear();
        bizMsg.rtlSwhNm.clear();
        bizMsg.whInEtaDt_FR.clear();
        bizMsg.whInEtaDt_TO.clear();
        bizMsg.imptInvBolNum.clear();
        bizMsg.carrCd.clear();
        bizMsg.carrNm.clear();
        bizMsg.rwsNum.clear();
        bizMsg.rwsStsCd.clear();
        // add QC#19314 
        bizMsg.flipMdseCd.clear();
        bizMsg.whCd.clear();

        // List Box
        bizMsg.rwsStsCd_P.clear();
        bizMsg.rwsStsDescTxt_P.clear();
        bizMsg.sceOrdTpCd_P.clear();
        bizMsg.sceOrdTpNm_P.clear();

        // Create RWS
        bizMsg.rwsRefNum_AP.clear();
        bizMsg.imptInvBolNum_AP.clear();

        bizMsg.xxWrnSkipFlg.clear();
        bizMsg.xxDplyTab.clear();
        bizMsg.xxNum.clear();
        bizMsg.srchOptTxt.clear();

        // START 2018/12/03 S.Ohki [QC#29461,ADD]
        // Apply Detail Lines
        bizMsg.rtlWhCd_AP.clear();
        bizMsg.rtlWhNm_AP.clear();
        bizMsg.thirdPtyDspTpCd_AP.clear();
        // END 2018/12/03 S.Ohki [QC#29461,ADD]
        // START 2023/02/28 TZ.Win [QC#61160, ADD]
        bizMsg.rtlSwhCd_AP.clear();
        bizMsg.rtlSwhNm_AP.clear();
        // END 2023/02/28 TZ.Win [QC#61160, ADD]
    }

    /**
     * inputCheckSearch
     * @param bizMsg NLAL2030CMsg
     * @return boolean
     */
    private boolean inputCheckSearch(NLAL2030CMsg bizMsg) {

        boolean check = true;

        bizMsg.dsAcctNm.clear();
        bizMsg.rtlWhNm.clear();
        bizMsg.rtlSwhNm.clear();
        bizMsg.carrNm.clear();
        bizMsg.whNm.clear();

        // Party
        if (ZYPCommonFunc.hasValue(bizMsg.fromLocCd)) {

            Map<String, Object> fromLocMap = getFromLoc(bizMsg);

            if (fromLocMap == null) {

                // Error
                bizMsg.fromLocCd.setErrorInfo(1, "NLZM2278E", new String[]{"Party"});
                check = false;

            } else {

                ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm, (String) fromLocMap.get("RTL_WH_NM"));
            }
        }

        // Warehouse
        if (ZYPCommonFunc.hasValue(bizMsg.rtlWhCd)) {

            RTL_WHTMsg rtlWhTMsg = getRtlWh(bizMsg.glblCmpyCd.getValue(), bizMsg.rtlWhCd.getValue());

            if (rtlWhTMsg == null) {

                // Error
                bizMsg.rtlWhCd.setErrorInfo(1, "NLZM2278E", new String[]{"Warehouse"});
                check = false;

            } else {

                ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm, rtlWhTMsg.rtlWhNm.getValue());
            }
        }

        // Received WH
        if (ZYPCommonFunc.hasValue(bizMsg.whCd)) {

            RTL_WHTMsg rtlWhTMsg = getRtlWh(bizMsg.glblCmpyCd.getValue(), bizMsg.whCd.getValue());

            if (rtlWhTMsg == null) {

                // Error
                bizMsg.whCd.setErrorInfo(1, "NLZM2278E", new String[]{"Received WH"});
                check = false;

            } else {

                ZYPEZDItemValueSetter.setValue(bizMsg.whNm, rtlWhTMsg.rtlWhNm.getValue());
            }
        }

        // Sub Warehouse
        if (ZYPCommonFunc.hasValue(bizMsg.rtlSwhCd)) {

            SWHTMsg swhTMsg = getRtlSwh(bizMsg);

            if (swhTMsg == null) {

                // Error
                bizMsg.rtlSwhCd.setErrorInfo(1, "NLZM2278E", new String[]{"Sub Warehouse"});
                check = false;

            } else {

                ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhNm, swhTMsg.rtlSwhNm.getValue());
            }
        }

        // Carrier
        if (ZYPCommonFunc.hasValue(bizMsg.carrCd)) {

            OTBD_CARR_VTMsg otbdCarrTMsg = getCarr(bizMsg);

            if (otbdCarrTMsg == null) {

                // Error
                bizMsg.carrCd.setErrorInfo(1, "NLZM2278E", new String[]{"Carrier"});
                check = false;

            } else {

                ZYPEZDItemValueSetter.setValue(bizMsg.carrNm, otbdCarrTMsg.carrNm.getValue());
            }
        }

        return check;
    }

    /**
     * getFromLoc
     * @param bizMsg NLAL2030CMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getFromLoc(NLAL2030CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sqlTxt", getPartyCdSelectSql(bizMsg, bizMsg.fromLocCd.getValue()));

        S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getParty(params);

        if (ssmResult.isCodeNormal()) {

            return (Map<String, Object>) ssmResult.getResultObject();
        }

        return null;
    }

    /**
     * getRtlWh
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return RTL_WHTMsg
     */
    private RTL_WHTMsg getRtlWh(String glblCmpyCd, String rtlWhCd) {

        RTL_WHTMsg rtlWh = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWh.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWh.rtlWhCd, rtlWhCd);
        return (RTL_WHTMsg) EZDFastTBLAccessor.findByKey(rtlWh);
    }

    /**
     * getRtlSwh
     * @param bizMsg NLAL2030CMsg
     * @return SWHTMsg
     */
    private SWHTMsg getRtlSwh(NLAL2030CMsg bizMsg) {

        SWHTMsg swhTMsg = new SWHTMsg();
        ZYPEZDItemValueSetter.setValue(swhTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(swhTMsg.rtlSwhCd, bizMsg.rtlSwhCd);
        return (SWHTMsg) EZDFastTBLAccessor.findByKey(swhTMsg);
    }

    /**
     * getCarr
     * @param bizMsg NLAL2030CMsg
     * @return OTBD_CARR_VTMsg
     */
    private OTBD_CARR_VTMsg getCarr(NLAL2030CMsg bizMsg) {

        OTBD_CARR_VTMsg otbdCarrV = new OTBD_CARR_VTMsg();
        otbdCarrV.setSQLID("001");
        otbdCarrV.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        otbdCarrV.setConditionValue("carrCd01", bizMsg.carrCd.getValue());
        OTBD_CARR_VTMsgArray otbdCarrVList = (OTBD_CARR_VTMsgArray) EZDTBLAccessor.findByCondition(otbdCarrV);

        if (otbdCarrVList == null || otbdCarrVList.getValidCount() == 0) {

            return null;
        }

        return (OTBD_CARR_VTMsg) otbdCarrVList.no(0);
    }

    /**
     * getPartyCdSelectSql
     * @param bizMsg NLAL2030CMsg
     * @return String
     */
    private String getPartyCdSelectSql(NLAL2030CMsg bizMsg, String fromLocCd) {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT DISTINCT").append("\n");
        sb.append(" RW.RTL_WH_CD").append("\n");
        sb.append(",RW.RTL_WH_NM").append("\n");
        sb.append(",LT.LOC_TP_CD").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",RW.FIRST_LINE_ADDR || ' ' || RW.SCD_LINE_ADDR AS LINE_ADDR").append("\n");
        sb.append(",RW.CTY_ADDR").append("\n");
        sb.append(",RW.ST_CD").append("\n");
        sb.append(",RW.POST_CD").append("\n");
        sb.append(",RW.GLBL_CMPY_CD").append("\n");
        sb.append(",RW.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" RTL_WH  RW").append("\n");
        sb.append(",RTL_SWH RSW").append("\n");
        sb.append(",WH      W").append("\n");
        sb.append(",LOC_TP  LT").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("RW.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'").append("\n");
        if (ZYPCommonFunc.hasValue(fromLocCd)) {
            sb.append("AND RW.RTL_WH_CD = '#SHIP_FROM_LOC_CD#'").append("\n");
        }
        sb.append("AND RW.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND RSW.GLBL_CMPY_CD = RW.GLBL_CMPY_CD").append("\n");
        sb.append("AND RSW.RTL_WH_CD = RW.RTL_WH_CD").append("\n");
        sb.append("AND RSW.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND W.GLBL_CMPY_CD = RSW.GLBL_CMPY_CD").append("\n");
        sb.append("AND W.WH_CD = RSW.INVTY_LOC_CD").append("\n");
        sb.append("AND W.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND LT.GLBL_CMPY_CD = W.GLBL_CMPY_CD").append("\n");
        sb.append("AND LT.LOC_GRP_CD = W.LOC_GRP_CD").append("\n");
        sb.append("AND LT.LOC_ROLE_TP_CD = W.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");

        //  WAREHOUSE (DUMMY)
        // QC#28711
        sb.append("UNION ALL").append("\n");
        sb.append("SELECT DISTINCT").append("\n");
        sb.append(" RW.RTL_WH_CD").append("\n");
        sb.append(",RW.RTL_WH_NM").append("\n");
        sb.append(",LT.LOC_TP_CD").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",RW.FIRST_LINE_ADDR || ' ' || RW.SCD_LINE_ADDR AS LINE_ADDR").append("\n");
        sb.append(",RW.CTY_ADDR").append("\n");
        sb.append(",RW.ST_CD").append("\n");
        sb.append(",RW.POST_CD").append("\n");
        sb.append(",RW.GLBL_CMPY_CD").append("\n");
        sb.append(",RW.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" RTL_WH  RW").append("\n");
        sb.append(",WH      W").append("\n");
        sb.append(",LOC_TP  LT").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("    RW.GLBL_CMPY_CD= '#GLBL_CMPY_CD#'").append("\n");
        if (ZYPCommonFunc.hasValue(fromLocCd)) {
            sb.append("AND RW.RTL_WH_CD = '#SHIP_FROM_LOC_CD#'").append("\n");
        }
        sb.append("AND RW.EZCANCELFLAG= '0'").append("\n");
        sb.append("AND W.GLBL_CMPY_CD = RW.GLBL_CMPY_CD").append("\n");
        sb.append("AND W.WH_CD = RW.RTL_WH_CD").append("\n");
        sb.append("AND W.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND LT.GLBL_CMPY_CD= W.GLBL_CMPY_CD").append("\n");
        sb.append("AND LT.LOC_GRP_CD = W.LOC_GRP_CD").append("\n");
        sb.append("AND LT.LOC_ROLE_TP_CD= W.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");

        // Technician
        // QC#28711
        sb.append("UNION ALL").append("\n");
        sb.append("SELECT DISTINCT").append("\n");
        sb.append(" RW.RTL_WH_CD").append("\n");
        sb.append(",RW.RTL_WH_NM").append("\n");
        sb.append(",LT.LOC_TP_CD").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",RW.FIRST_LINE_ADDR || ' ' || RW.SCD_LINE_ADDR AS LINE_ADDR").append("\n");
        sb.append(",RW.CTY_ADDR").append("\n");
        sb.append(",RW.ST_CD").append("\n");
        sb.append(",RW.POST_CD").append("\n");
        sb.append(",RW.GLBL_CMPY_CD").append("\n");
        sb.append(",RW.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" RTL_WH   RW").append("\n");
        sb.append(",RTL_SWH  RSW").append("\n");
        sb.append(",TECH_LOC TL").append("\n");
        sb.append(",LOC_TP   LT").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("    RW.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'").append("\n");
        if (ZYPCommonFunc.hasValue(fromLocCd)) {
            sb.append("AND RW.RTL_WH_CD = '#SHIP_FROM_LOC_CD#'").append("\n");
        }
        sb.append("AND RW.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND RSW.GLBL_CMPY_CD = RW.GLBL_CMPY_CD").append("\n");
        sb.append("AND RSW.RTL_WH_CD = RW.RTL_WH_CD").append("\n");
        sb.append("AND RSW.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND TL.GLBL_CMPY_CD = RSW.GLBL_CMPY_CD").append("\n");
        sb.append("AND TL.TECH_LOC_CD  = RSW.INVTY_LOC_CD").append("\n");
        sb.append("AND TL.EZCANCELFLAG  = '0'").append("\n");
        sb.append("AND LT.GLBL_CMPY_CD = TL.GLBL_CMPY_CD").append("\n");
        sb.append("AND LT.LOC_GRP_CD = TL.LOC_GRP_CD").append("\n");
        sb.append("AND LT.LOC_ROLE_TP_CD = TL.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");

        // Vendor
        // QC#28711
        sb.append("UNION ALL").append("\n");
        sb.append("SELECT").append("\n");
        sb.append(" V.VND_CD").append("\n");
        sb.append(",PVV.DPLY_VND_NM").append("\n");
        sb.append(",LT.LOC_TP_CD").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",V.FIRST_LINE_ADDR || ' ' || V.SCD_LINE_ADDR AS LINE_ADDR").append("\n");
        sb.append(",V.CTY_ADDR").append("\n");
        sb.append(",V.ST_CD").append("\n");
        sb.append(",V.POST_CD").append("\n");
        sb.append(",V.GLBL_CMPY_CD").append("\n");
        sb.append(",V.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" VND  V").append("\n");
        sb.append(",LOC_TP LT").append("\n");
        sb.append(",PO_VND_V PVV").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("    V.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'").append("\n");
        if (ZYPCommonFunc.hasValue(fromLocCd)) {
            sb.append("AND V.VND_CD = '#SHIP_FROM_LOC_CD#'").append("\n");
        }
        sb.append("AND V.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND LT.GLBL_CMPY_CD = V.GLBL_CMPY_CD").append("\n");
        sb.append("AND LT.LOC_GRP_CD = V.LOC_GRP_CD").append("\n");
        sb.append("AND LT.LOC_ROLE_TP_CD = V.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND V.GLBL_CMPY_CD  = PVV.GLBL_CMPY_CD(+)").append("\n");
        sb.append("AND V.VND_CD = PVV.VND_CD (+)").append("\n");
        sb.append("AND PVV.EZCANCELFLAG(+) = '0'").append("\n");

        // Ship To Customer
        // QC#28711
        sb.append("UNION ALL").append("\n");
        sb.append("SELECT").append("\n");
        sb.append(" ST.SHIP_TO_CUST_CD").append("\n");
        sb.append(",DAC.DS_ACCT_NM").append("\n");
        sb.append(",LT.LOC_TP_CD").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",ST.FIRST_LINE_ADDR || ' ' || ST.SCD_LINE_ADDR AS LINE_ADDR").append("\n");
        sb.append(",ST.CTY_ADDR").append("\n");
        sb.append(",ST.ST_CD").append("\n");
        sb.append(",ST.POST_CD").append("\n");
        sb.append(",ST.GLBL_CMPY_CD").append("\n");
        sb.append(",ST.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" SHIP_TO_CUST ST").append("\n");
        sb.append(",SELL_TO_CUST DAC").append("\n");
        sb.append(",LOC_TP       LT").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("    ST.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'").append("\n");
        if (ZYPCommonFunc.hasValue(fromLocCd)) {
            sb.append("AND ST.SHIP_TO_CUST_CD = '#SHIP_FROM_LOC_CD#'").append("\n");
        }
        sb.append("AND ST.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND DAC.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND DAC.SELL_TO_CUST_CD = ST.SELL_TO_CUST_CD").append("\n");
        sb.append("AND DAC.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND LT.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND LT.LOC_GRP_CD = ST.LOC_GRP_CD").append("\n");
        sb.append("AND LT.LOC_ROLE_TP_CD = ST.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    RTL_WH NR").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    NR.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND NR.RTL_WH_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND NR.EZCANCELFLAG = '0')").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    WH NW").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    NW.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND NW.WH_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND NW.EZCANCELFLAG = '0')").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    TECH_LOC NT").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    NT.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND NT.TECH_LOC_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND NT.EZCANCELFLAG = '0')").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    VND NV").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    NV.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND NV.VND_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND NV.EZCANCELFLAG = '0')").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    BR B").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    B.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND B.BR_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND B.EZCANCELFLAG = '0')").append("\n");

        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", bizMsg.glblCmpyCd.getValue());
        sql = sql.replaceAll("#SHIP_FROM_LOC_CD#", fromLocCd);
        return sql;
    }

    private void doProcess_NLAL2030Scrn00_Apply(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        NLAL2030CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // master check
        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        if (ZYPCommonFunc.hasValue(bizMsg.rtlWhCd_AP)) {
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, bizMsg.rtlWhCd_AP);
            rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);

            if (rtlWhTMsg == null) {
                bizMsg.rtlWhCd_AP.setErrorInfo(1, NLAL2030Constant.ZZZM9006E, new String[] {bizMsg.rtlWhCd_AP.getValue() });
                return;
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_AP, rtlWhTMsg.rtlWhNm);
            }
        }
        // START 2023/03/02 TZ.Win [QC#61160, ADD]
        SWHTMsg swhTMsg = new SWHTMsg();
        if (ZYPCommonFunc.hasValue(bizMsg.rtlSwhCd_AP)) {
            ZYPEZDItemValueSetter.setValue(swhTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(swhTMsg.rtlSwhCd, bizMsg.rtlSwhCd_AP);
            swhTMsg = (SWHTMsg) EZDTBLAccessor.findByKey(swhTMsg);

            if (swhTMsg == null) {
                bizMsg.rtlSwhCd_AP.setErrorInfo(1, NLAL2030Constant.ZZZM9006E, new String[] {bizMsg.rtlSwhCd_AP.getValue() });
                return;
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhNm_AP, swhTMsg.rtlSwhNm);
            }
        }
        // END 2023/03/02 TZ.Win [QC#61160, ADD]

        boolean isUpd = false;
        HashSet<String> targetRwsNumList = new HashSet<String>();
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NLAL2030_BSMsg line = glblMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(line.xxChkBox_B1.getValue()) //
                    || targetRwsNumList.contains(line.rwsNum_B1.getValue())) {

                targetRwsNumList.add(line.rwsNum_B1.getValue());

                // START 2023/03/02 TZ.Win [QC#61160, MOD]
                if (ZYPConstant.FLG_ON_Y.equals(line.xxDplyCtrlFlg_WH.getValue()) && ZYPCommonFunc.hasValue(bizMsg.rtlWhCd_AP)) {
                // END 2023/03/02 TZ.Win [QC#61160, MOD]
                    ZYPEZDItemValueSetter.setValue(line.rtlWhCd_B1, bizMsg.rtlWhCd_AP);
                    ZYPEZDItemValueSetter.setValue(line.rtlWhNm_B1, rtlWhTMsg.rtlWhNm);
                    isUpd = true;
                }
                // START 2023/03/02 TZ.Win [QC#61160, MOD]
                if (ZYPConstant.FLG_ON_Y.equals(line.xxDplyCtrlFlg_TP.getValue()) && ZYPCommonFunc.hasValue(bizMsg.thirdPtyDspTpCd_AP)) {
                // END 2023/03/02 TZ.Win [QC#61160, MOD]
                    ZYPEZDItemValueSetter.setValue(line.thirdPtyDspTpCd_B1, bizMsg.thirdPtyDspTpCd_AP);
                    isUpd = true;
                }
                // START 2023/03/02 TZ.Win [QC#61160, ADD]
                if (ZYPCommonFunc.hasValue(bizMsg.rtlSwhCd_AP)) {
                    ZYPEZDItemValueSetter.setValue(line.rtlSwhCd_B1, bizMsg.rtlSwhCd_AP);
                    ZYPEZDItemValueSetter.setValue(line.rtlSwhNm_B1, swhTMsg.rtlSwhNm);
                    isUpd = true;
                }
                // END 2023/03/02 TZ.Win [QC#61160, ADD]
            }
        }

        if (!isUpd) {
            bizMsg.setMessageInfo(NLAL2030Constant.NLCM0123E);
        }

        NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);
    }

    private void doProcess_NLAL2030Scrn00_getWhNameFromApply(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {
        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, bizMsg.rtlWhCd_AP);
        rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);

        if (rtlWhTMsg == null) {
            bizMsg.rtlWhCd_AP.setErrorInfo(1, NLAL2030Constant.ZZZM9006E, new String[] {bizMsg.rtlWhCd_AP.getValue() });
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_AP, rtlWhTMsg.rtlWhNm);
        }
    }

    private void doProcess_NLAL2030Scrn00_getWhNameFromRwsList(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        int index = bizMsg.xxNum.getValueInt();

        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, bizMsg.B.no(index).rtlWhCd_B1);
        rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);

        if (rtlWhTMsg == null) {
            bizMsg.B.no(index).rtlWhCd_B1.setErrorInfo(1, NLAL2030Constant.ZZZM9006E, new String[] {bizMsg.B.no(index).rtlWhCd_B1.getValue() });
            return;
        } else {
            // START 2021/11/11 A.Marte [QC#59350, ADD]
            //check valid RTL_HW + SWH combination
            if (!NLAL2030CommonLogic.isValidRwhSwhCobination(rtlWhTMsg.glblCmpyCd.getValue(), rtlWhTMsg.rtlWhCd.getValue(), bizMsg.B.no(index).rtlSwhCd_B1.getValue())) {
                bizMsg.B.no(index).rtlWhCd_B1.setErrorInfo(1, NLAL2030Constant.NLAM1357E, new String[] {rtlWhTMsg.rtlWhCd.getValue().concat(bizMsg.B.no(index).rtlSwhCd_B1.getValue())});
            }
            // END 2021/11/11 A.Marte [QC#59350, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).rtlWhNm_B1, rtlWhTMsg.rtlWhNm);
        }
    }
    // START 2023/03/06 TZ.Win [QC#61160, ADD]
    private void doProcess_NLAL2030Scrn00_getSwhNameFromApply(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {
        SWHTMsg swhTMsg = new SWHTMsg();
        ZYPEZDItemValueSetter.setValue(swhTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(swhTMsg.rtlSwhCd, bizMsg.rtlSwhCd_AP);
        swhTMsg = (SWHTMsg) EZDTBLAccessor.findByKey(swhTMsg);

        if (swhTMsg == null) {
            bizMsg.rtlSwhCd_AP.setErrorInfo(1, NLAL2030Constant.ZZZM9006E, new String[] {bizMsg.rtlSwhCd_AP.getValue() });
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhNm_AP, swhTMsg.rtlSwhNm);
        }
    }
    // END 2023/03/06 TZ.Win [QC#61160, ADD]

}
