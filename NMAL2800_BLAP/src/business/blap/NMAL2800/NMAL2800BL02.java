/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2800;

import static business.blap.NMAL2800.constant.NMAL2800Constant.BIZ_ID;
import static business.blap.NMAL2800.constant.NMAL2800Constant.CSV_FILE_NAME_UPLOAD;
import static business.blap.NMAL2800.constant.NMAL2800Constant.CSV_LIMIT_COUNT;
import static business.blap.NMAL2800.constant.NMAL2800Constant.CSV_UPLOAD_HEADER;
import static business.blap.NMAL2800.constant.NMAL2800Constant.DT_LEN;
import static business.blap.NMAL2800.constant.NMAL2800Constant.EXT;
import static business.blap.NMAL2800.constant.NMAL2800Constant.FETCH_SIZE;
import static business.blap.NMAL2800.constant.NMAL2800Constant.FUNC_ID_LOC;
import static business.blap.NMAL2800.constant.NMAL2800Constant.FUNC_ID_TRTY;
import static business.blap.NMAL2800.constant.NMAL2800Constant.HDR_LOC;
import static business.blap.NMAL2800.constant.NMAL2800Constant.HDR_TRTY;
import static business.blap.NMAL2800.constant.NMAL2800Constant.KEY_FIELD_LOC;
import static business.blap.NMAL2800.constant.NMAL2800Constant.KEY_FIELD_TRTY;
import static business.blap.NMAL2800.constant.NMAL2800Constant.LOC_NUM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.MODE_CD_SRCH;
import static business.blap.NMAL2800.constant.NMAL2800Constant.MODE_CD_UPL;
import static business.blap.NMAL2800.constant.NMAL2800Constant.MODE_NM_LOC;
import static business.blap.NMAL2800.constant.NMAL2800Constant.MODE_NM_TRTY;
import static business.blap.NMAL2800.constant.NMAL2800Constant.MODE_SRCH;
import static business.blap.NMAL2800.constant.NMAL2800Constant.MODE_UPL;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM0007I;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NZZM0000E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NZZM0001W;
import static business.blap.NMAL2800.constant.NMAL2800Constant.PROS_ACCT;
import static business.blap.NMAL2800.constant.NMAL2800Constant.PROS_RVW_STS_CD_ALL;
import static business.blap.NMAL2800.constant.NMAL2800Constant.PROS_RVW_STS_CD_BLANK;
import static business.blap.NMAL2800.constant.NMAL2800Constant.RESRC_TRTY_ORG_NM_LEN;
import static business.blap.NMAL2800.constant.NMAL2800Constant.TM_TS_FROM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.TM_TS_TO;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ZZZM9006E;

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
import business.blap.NMAL2800.common.NMAL2800CommonLogic;
import business.db.CTRYTMsg;
import business.db.DS_ACCT_RVW_PROSTMsgArray;
import business.db.STTMsg;
import business.db.STTMsgArray;
import business.file.NMAL2800F01FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
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
 * NMAL2800BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/26   Fujitsu         N.Sugiura       Update          QC#12417
 * 2016/07/26   SRAA            Y.Chen          Update          QC#13666
 * 2016/09/08   Fujitsu         C.Yokoi         Update          QC#12418
 * 2016/10/03   Hitachi         T.Mizuki        Update          QC#12418
 * 2016/11/02   Fujitsu         C.Yokoi         Update          QC#15294
 * 2017/10/12   Fujitsu         Y.Kanefusa      Update          S21_NA#21787
 * 2017/10/17   Fujitsu         M.Ohno          Update          QC#21782
 * 2017/10/20   Fujitsu         M.Ohno          Update          QC#21866
 * 2017/11/17   Fujitsu         M.Ohno          Update          QC#22632
 * 2017/11/17   Fujitsu         M.Ohno          Update          QC#22525
 * 2018/01/16   Fujitsu         Hd.Sugawara     Update          QC#23148
 * 2018/03/02   Fujitsu         Hd.Sugawara     Update          QC#23148-1
 * 2018/03/29   Fujitsu         R.Nakamura      Update          QC#23149
 * 2018/04/11   Fujitsu         R.Nakamura      Update          QC#23149-2
 * 2019/08/25   Fujitsu         T.Aoi           Update          QC#27457
 *</pre>
 */
public class NMAL2800BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2800CMsg bizMsg = (NMAL2800CMsg) cMsg;
            NMAL2800SMsg glblMsg = (NMAL2800SMsg) sMsg;

            if ("NMAL2800_INIT".equals(screenAplID)) {
                doProcess_NMAL2800_INIT(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_CMN_Submit(bizMsg, glblMsg);

                // Del Start 2018/01/16 QC#23148
            //} else if ("NMAL2800Scrn00_Download_Template".equals(screenAplID)) {
                //doProcess_NMAL2800Scrn00_Download_Template(bizMsg, glblMsg);
                // Del End 2018/01/16 QC#23148

            } else if ("NMAL2800Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_Search(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_Select_Search".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_Select_Search(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_OpenWin_CandiLoc".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_OpenWin_CandiLoc(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_OpenWin_CandiTrtyResrc".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_OpenWin_CandiTrtyResrc(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_OpenWin_CandiTrtyRule".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_OpenWin_CandiTrtyRule(bizMsg, glblMsg);

            // 2016/11/02 CSA-QC#15294 Add Start
            } else if ("NMAL2800Scrn00_OpenWin_MergeProsAsIs".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_OpenWin_MergeProsAsIs(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_OpenWin_MergeProsToBe".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_OpenWin_MergeProsToBe(bizMsg, glblMsg);
            // 2016/11/02 CSA-QC#15294 Add End

            } else if ("NMAL2800Scrn00_Select_Country".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_Select_Country(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_Copy_Address".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_Copy_Address(bizMsg, glblMsg);

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
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800_INIT(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NMAL2800CommonLogic.createPulldownListSaveOpt(bizMsg, getContextUserInfo().getUserId(), glblCmpyCd);
        NMAL2800CommonLogic.createPulldownListProsRvwSts(bizMsg, glblCmpyCd);

        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_L, bizMsg.lineBizTpDescTxt_L);
        // 2018/08/25 QC#27457 Mod Start
        //ZYPCodeDataUtil.createPulldownList(ST.class, bizMsg.stCd_L, bizMsg.stDescTxt_L);
        STTMsg condition = new STTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ctryCd01", CTRY.UNITED_STATES_OF_AMERICA);

        STTMsgArray tMsgArray = (STTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                bizMsg.stCd_L.no(i).setValue(tMsgArray.no(i).stCd.getValue());
                bizMsg.stDescTxt_L.no(i).setValue(tMsgArray.no(i).stDescTxt.getValue());
            }
        }
        // 2018/08/25 QC#27457 Mod End
        NMAL2800CommonLogic.createStatePulldownList(bizMsg);
        ZYPCodeDataUtil.createPulldownList(CTRY.class, bizMsg.ctryCd_L, bizMsg.ctryDescTxt_L);

        // QC#13666
        // ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_H1, LINE_BIZ_TP.ESS);

        // create Mode PullDown list
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTpCd_L.no(0), MODE_CD_SRCH);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTpNm_L.no(0), MODE_SRCH);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTpCd_L.no(1), MODE_CD_UPL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTpNm_L.no(1), MODE_UPL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTpCd_H1, MODE_CD_SRCH);

        // create Potential Duplicate PullDown list
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_L.no(0), ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoNm_L.no(0), "Yes");
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_CMN_Clear(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);

        bizMsg.clear();
        glblMsg.clear();

        doProcess_NMAL2800_INIT(bizMsg, glblMsg);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_CMN_Download(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        // Mod Start 2018/01/16 QC#23148
        doProcess_NMAL2800Scrn00_Download_Template(bizMsg, glblMsg);
        // Mod End 2018/01/16 QC#23148

        // Del Start 2018/01/16 QC#23148
//        ResultSet rs = null;
//        PreparedStatement stmtSelect = null;
//        try {
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//            bizMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_DOWNLOAD), EXT);
//
//            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL2800Query.getInstance().getClass());
//            HashMap<String, Object> params = new HashMap<String, Object>();
//            params.put("glblCmpyCd", getGlobalCompanyCode());
//            params.put("bizMsg", bizMsg);
//            // mod start 2016/10/03 CSA QC#12418
//            params.put("gnrnTpCd", GNRN_TP.CURRENT);
//            // mod end 2016/10/03 CSA QC#12418
//            params.put("rowNum", String.valueOf(CSV_LIMIT_COUNT + 1));
//            // QC#21787 2017/10/12 Mod Start
//            params.put("tmTsFrom", bizMsg.xxDt10Dt_H1.getValue() + TM_TS_FROM);
//            params.put("tmTsTo", bizMsg.xxDt10Dt_H2.getValue() + TM_TS_TO);
//            // QC#21787 2017/10/12 Mod End
//            if (PROS_RVW_STS_CD_BLANK.equals(bizMsg.prosRvwStsCd_H1.getValue())) {
//                params.put("blankFlg", ZYPConstant.FLG_ON_Y);
//            } else if (PROS_RVW_STS_CD_ALL.equals(bizMsg.prosRvwStsCd_H1.getValue())) {
//                params.put("allFlg", ZYPConstant.FLG_ON_Y);
//            }
//            stmtSelect = ssmLLClient.createPreparedStatement("search", params, execParam);
//            rs = stmtSelect.executeQuery();
//            if (!rs.next()) {
//                bizMsg.setMessageInfo(NZZM0000E);
//                return;
//            }
//
//            writeDownloadCsvFile(bizMsg, rs);
//        } catch (SQLException e) {
//            throw new S21AbendException(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
//        }
        // Del End 2018/01/16 QC#23148
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_CMN_Submit(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        if (MODE_CD_SRCH.equals(bizMsg.xxTpCd_H1.getValue())) {
            search(bizMsg, glblMsg);
        }
        EZDMsg.copy(glblMsg.A, null, glblMsg.B, null);
    }

    /**
     * GetTemplate Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_Download_Template(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            bizMsg.xxFileData_U.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_UPLOAD), EXT);

            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL2800Query.getInstance().getClass());
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", getGlobalCompanyCode());
            // 2017/11/17 QC#22632 add start
            params.put("bizMsg", bizMsg);
            params.put("gnrnTpCd", GNRN_TP.CURRENT);
            params.put("tmTsFrom", bizMsg.xxDt10Dt_H1.getValue() + TM_TS_FROM);
            params.put("tmTsTo", bizMsg.xxDt10Dt_H2.getValue() + TM_TS_TO);

            if (PROS_RVW_STS_CD_BLANK.equals(bizMsg.prosRvwStsCd_H1.getValue())) {
                params.put("blankFlg", ZYPConstant.FLG_ON_Y);
            } else if (PROS_RVW_STS_CD_ALL.equals(bizMsg.prosRvwStsCd_H1.getValue())) {
                params.put("allFlg", ZYPConstant.FLG_ON_Y);
            }
            // 2017/11/17 QC#22632 add end
            params.put("rowNum", String.valueOf(CSV_LIMIT_COUNT + 1));
            stmtSelect = ssmLLClient.createPreparedStatement("searchUpldTrgt", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                bizMsg.setMessageInfo(NZZM0000E);
                return;
            }

            writeUploadCsvFile(bizMsg, rs);
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_PageJump(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        NMAL2800CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NMAL2800CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_PageNext(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        NMAL2800CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL2800CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_PagePrev(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        NMAL2800CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL2800CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_Search(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        search(bizMsg, glblMsg);
        NMAL2800CommonLogic.createStatePulldownList(bizMsg);
        EZDMsg.copy(glblMsg.A, null, glblMsg.B, null);
    }

    /**
     * Select_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_Select_Search(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            return;
        }

        // set API parameter
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        // call Search Option API
        if (!NMAL2800CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            return;
        }

        // set saved search option value to search fields
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTpCd_H1, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_H1, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDt10Dt_H1, pMsg.srchOptTxt_03.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDt10Dt_H2, pMsg.srchOptTxt_04.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.befDsAcctNm_H1, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.rvwProsNum_H1, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem61Txt_H1, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.befPsnNum_H1, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.orgNm_H1, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.prosRvwStsCd_H1, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(bizMsg.dupAcctLocFlg_H1, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipAddrAllTxt_H1, pMsg.srchOptTxt_12);
        // 2017/10/17 QC#21782 mod start
//        ZYPEZDItemValueSetter.setValue(bizMsg.befBillToCtyAddr_H1, pMsg.srchOptTxt_13);
//        ZYPEZDItemValueSetter.setValue(bizMsg.befBillToStCd_H1, pMsg.srchOptTxt_14);
//        ZYPEZDItemValueSetter.setValue(bizMsg.befBillToPostCd_H1, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(bizMsg.befShipToCtyAddr_H1, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(bizMsg.befShipToStNm_H1, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(bizMsg.befShipToPostCd_H1, pMsg.srchOptTxt_15);
        // 2017/10/17 QC#21782 mod end

    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_TBLColumnSort(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            // set pagination
            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL2800CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    /**
     * OpenWin_CandiLoc
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_OpenWin_CandiLoc(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        int index = bizMsg.xxRowNum.getValueInt();
        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(index).matchAcctLocNum_A1)) {
            return;
        }

        String[] locList = bizMsg.A.no(index).matchAcctLocNum_A1.getValue().split(",", 0);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeNm23Txt, MODE_NM_LOC);
        ZYPEZDItemValueSetter.setValue(bizMsg.bizId, FUNC_ID_LOC);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem61Txt, HDR_LOC);

        int cnt = 0;
        for (int i = 0; i < locList.length && cnt < bizMsg.C.length(); i++) {
            String locNum = locList[i].trim();
            S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getDsAcctNm(locNum);
            if (ssmResult.isCodeNormal()) {

                Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();

                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxScrItem10Txt_C, KEY_FIELD_LOC);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).locNum_C, locNum);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxDsMsgEntryTxt_C, locNum + " , " + (String) map.get("DS_ACCT_NM"));

                cnt++;
            }
        }
        bizMsg.C.setValidCount(cnt);
    }

    /**
     * OpenWin_CandiTrtyResrc
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_OpenWin_CandiTrtyResrc(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        int index = bizMsg.xxRowNum.getValueInt();
        String[] orgList = bizMsg.A.no(index).resrcTrtyOrgNm_A1.getValue().split(",", 0);

        setMultiCandiParam(bizMsg, orgList);
    }

    /**
     * OpenWin_CandiTrtyRule
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_OpenWin_CandiTrtyRule(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        int index = bizMsg.xxRowNum.getValueInt();
        String[] orgList = bizMsg.A.no(index).candiTrtyNm_A1.getValue().split(",", 0);

        setMultiCandiParam(bizMsg, orgList);
    }

    /**
     * OpenWin_MergeProsAsIs
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_OpenWin_MergeProsAsIs(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {
        // 2016/11/02 CSA-QC#15294 Add Start
        NMAL2800_ACMsg aCMsg = bizMsg.A.no(bizMsg.xxRowNum.getValueInt());
        DS_ACCT_RVW_PROSTMsgArray dsAcctRvwProsTMsgArray = NMAL2800CommonLogic.getDsAcctRvwPros(getGlobalCompanyCode(), aCMsg.aftDsXrefAcctCd_A1.getValue());
        if (dsAcctRvwProsTMsgArray == null || dsAcctRvwProsTMsgArray.getValidCount() < 1) {
            aCMsg.aftDsXrefAcctCd_A1.setErrorInfo(1, ZZZM9006E, new String[] {PROS_ACCT });
        }
        // 2016/11/02 CSA-QC#15294 Add End
    }

    /**
     * OpenWin_MergeProsToBe
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_OpenWin_MergeProsToBe(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {
        // 2016/11/02 CSA-QC#15294 Add Start
        NMAL2800_ACMsg aCMsg = bizMsg.A.no(bizMsg.xxRowNum.getValueInt());

        if (!NMAL2800CommonLogic.existLocNum(aCMsg.aftLocNum_A1.getValue())){
            aCMsg.aftLocNum_A1.setErrorInfo(1, ZZZM9006E, new String[] {LOC_NUM });
        }
        // 2016/11/02 CSA-QC#15294 Add End
    }

    /**
     * Select Country
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_Select_Country(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        NMAL2800CommonLogic.createStatePulldownList(bizMsg);

    }

    /**
     * Copy Address
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_Copy_Address(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        int index = bizMsg.xxCellIdx_H1.getValueInt();
        NMAL2800_ACMsg aCMsg = bizMsg.A.no(index);

        // 2017/10/17 QC#21782 mod start
//        ZYPEZDItemValueSetter.setValue(aCMsg.aftLocFirstLineAddr_A1, aCMsg.befBillToFirstLineAddr_A1);
//        ZYPEZDItemValueSetter.setValue(aCMsg.aftLocScdLineAddr_A1, aCMsg.befBillToScdLineAddr_A1);
//        ZYPEZDItemValueSetter.setValue(aCMsg.aftLocThirdLineAddr_A1, aCMsg.befBillToThirdLineAddr_A1);
//        ZYPEZDItemValueSetter.setValue(aCMsg.aftLocFrthLineAddr_A1, aCMsg.befBillToFrthLineAddr_A1);
//        // 2016/09/08 CSA-QC#12418 Mod Start
//        ZYPEZDItemValueSetter.setValue(aCMsg.aftLocCtyAddr_A1, aCMsg.befBillToCtyAddr_A1.getValue().toUpperCase());
//        // ZYPEZDItemValueSetter.setValue(aCMsg.aftLocCtyAddr_A1, aCMsg.befBillToCtyAddr_A1);
//        // 2016/09/08 CSA-QC#12418 Mod End
//        ZYPEZDItemValueSetter.setValue(aCMsg.aftLocStCd_A1, aCMsg.befBillToStCd_A1);
//        ZYPEZDItemValueSetter.setValue(aCMsg.aftLocPostCd_A1, aCMsg.befBillToPostCd_A1);
//        // 2016/09/08 CSA-QC#12418 Mod Start
//        ZYPEZDItemValueSetter.setValue(aCMsg.cntyNm_A1, aCMsg.befBillToCntyNm_A1.getValue().toUpperCase());
//        // ZYPEZDItemValueSetter.setValue(aCMsg.cntyNm_A1, aCMsg.befBillToCntyNm_A1);
//        // 2016/09/08 CSA-QC#12418 Mod End
        ZYPEZDItemValueSetter.setValue(aCMsg.aftLocFirstLineAddr_A1, aCMsg.befLocFirstLineAddr_A1);
        ZYPEZDItemValueSetter.setValue(aCMsg.aftLocScdLineAddr_A1, aCMsg.befLocScdLineAddr_A1);
        ZYPEZDItemValueSetter.setValue(aCMsg.aftLocThirdLineAddr_A1, aCMsg.befLocThirdLineAddr_A1);
        ZYPEZDItemValueSetter.setValue(aCMsg.aftLocFrthLineAddr_A1, aCMsg.befLocFrthLineAddr_A1);
        ZYPEZDItemValueSetter.setValue(aCMsg.aftLocCtyAddr_A1, aCMsg.befShipToCtyAddr_A1.getValue().toUpperCase());
        ZYPEZDItemValueSetter.setValue(aCMsg.aftLocStCd_A1, aCMsg.befShipToStNm_A1);
        ZYPEZDItemValueSetter.setValue(aCMsg.aftLocPostCd_A1, aCMsg.befShipToPostCd_A1);
        ZYPEZDItemValueSetter.setValue(aCMsg.cntyNm_A1, aCMsg.befShipToCntyNm_A1.getValue().toUpperCase());
        // 2017/10/17 QC#21782 mod end
        ZYPEZDItemValueSetter.setValue(aCMsg.aftCtryCd_A1, NMAL2800CommonLogic.getCtryCd(aCMsg.befCtryNm_A1.getValue()));
        NMAL2800CommonLogic.createStatePulldownList(bizMsg);

    }
    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NMAM0007I);

        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                NMAL2800_ASMsg aSMsg = glblMsg.A.no(i);

                // set default country
                if (!ZYPCommonFunc.hasValue(aSMsg.aftCtryCd_A1)) {
                    ZYPEZDItemValueSetter.setValue(aSMsg.aftCtryCd_A1, CTRY.UNITED_STATES_OF_AMERICA);
                }

                // get Territory Candidate (Resource Base)
                setResrcTrtyOrgNm(aSMsg);

                // set date
                if (ZYPCommonFunc.hasValue(aSMsg.xtrnlCratDtTmTs_A1)) {
                    String dt1 = aSMsg.xtrnlCratDtTmTs_A1.getValue().substring(0, DT_LEN);
                    ZYPEZDItemValueSetter.setValue(aSMsg.xxDtTxt_A1, ZYPDateUtil.formatEzd8ToDisp(dt1));
                }
                if (ZYPCommonFunc.hasValue(aSMsg.xtrnlLastCratDtTmTs_A1)) {
                    String dt2 = aSMsg.xtrnlLastCratDtTmTs_A1.getValue().substring(0, DT_LEN);
                    ZYPEZDItemValueSetter.setValue(aSMsg.xxDtTxt_A2, ZYPDateUtil.formatEzd8ToDisp(dt2));
                }

            }

            // set pagination.
            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL2800CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    private void setResrcTrtyOrgNm(NMAL2800_ASMsg aSMsg) {

        if (ZYPCommonFunc.hasValue(aSMsg.befPsnNum_A1)) {
            S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getTrtyCandiByResrc(aSMsg.befPsnNum_A1.getValue());
            if (ssmResult.isCodeNormal()) {
                StringBuilder sb = new StringBuilder();
                List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();

                if (list == null || list.isEmpty()) {
                    return;
                }

                for (int j = 0; j < list.size(); j++) {
                    Map map = list.get(j);
                    if (j != 0) {
                        sb.append(",");
                    }
                    sb.append((String) map.get("ORG_NM"));
                }
                String str = sb.toString();
                if (sb.length() > RESRC_TRTY_ORG_NM_LEN) {
                    str = str.substring(0, RESRC_TRTY_ORG_NM_LEN);
                }
                ZYPEZDItemValueSetter.setValue(aSMsg.resrcTrtyOrgNm_A1, str);
            }
        }
    }
 
    // 2017/11/21 QC#22525 add start
    // Del Start 2018/01/16 QC#23148
    // Add Start 2018/03/02 QC#23148-1
    private String setResrcTrtyOrgNm(String befPsnNum) {

        if (ZYPCommonFunc.hasValue(befPsnNum)) {
            S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getTrtyCandiByResrc(befPsnNum);
            if (ssmResult.isCodeNormal()) {
                StringBuilder sb = new StringBuilder();
                List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();

                if (list == null || list.isEmpty()) {
                    return null;
                }

                for (int j = 0; j < list.size(); j++) {
                    Map map = list.get(j);
                    if (j != 0) {
                        sb.append(",");
                    }
                    sb.append((String) map.get("ORG_NM"));
                }
                String str = sb.toString();
                if (sb.length() > RESRC_TRTY_ORG_NM_LEN) {
                    str = str.substring(0, RESRC_TRTY_ORG_NM_LEN);
                }
                return str;
            }
        }
        return null;
    }
    // Add End 2018/03/02 QC#23148-1
    // Del End 2018/01/16 QC#23148
    // 2017/11/21 QC#22525 add end

    private void setMultiCandiParam(NMAL2800CMsg bizMsg, String[] orgList) {

        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeNm23Txt, MODE_NM_TRTY);
        ZYPEZDItemValueSetter.setValue(bizMsg.bizId, FUNC_ID_TRTY);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem61Txt, HDR_TRTY);

        int cnt = 0;
        String txt = "";
        for (int i = 0; i < orgList.length && cnt < bizMsg.C.length(); i++) {
            String orgNm = orgList[i].trim();
            S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getOrgInfo(orgNm);
            if (ssmResult.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();

                txt = orgNm + " , ";
                if (ZYPCommonFunc.hasValue((String) map.get("ORG_DESC_TXT"))) {
                    txt += (String) map.get("ORG_DESC_TXT") + " , ";
                } else {
                    txt += " , ";
                }
                if (ZYPCommonFunc.hasValue((String) map.get("TRTY_GRP_TP_DESC_TXT"))) {
                    txt += (String) map.get("TRTY_GRP_TP_DESC_TXT");
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxScrItem10Txt_C, KEY_FIELD_TRTY);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).orgCd_C, (String) map.get("ORG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxDsMsgEntryTxt_C, txt);

                cnt++;
            }
        }
        bizMsg.C.setValidCount(cnt);
    }

    // Del Start 2018/01/16 QC#23148
//    private void writeDownloadCsvFile(NMAL2800CMsg bizMsg, ResultSet rs) throws SQLException {
//
//        NMAL2800F00FMsg fMsg = new NMAL2800F00FMsg();
//        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_D.getTempFilePath(), fMsg);
//
//        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
//
//        // write header
//        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));
//
//        // write contents
//        do {
//            if (rs.getRow() >= CSV_LIMIT_COUNT) {
//                bizMsg.setMessageInfo(NZZM0001W);
//                break;
//            }
//
//            ZYPEZDItemValueSetter.setValue(fMsg.prosRvwStsCd, rs.getString("PROS_RVW_STS_CD"));
//            ZYPEZDItemValueSetter.setValue(fMsg.rvwProsNum, rs.getString("RVW_PROS_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.befDsAcctNm, rs.getString("BEF_DS_ACCT_NM"));
//            // 2017/10/17 QC#21782 mod start
////            ZYPEZDItemValueSetter.setValue(fMsg.befBillToFirstLineAddr, rs.getString("BEF_BILL_TO_FIRST_LINE_ADDR"));
////            ZYPEZDItemValueSetter.setValue(fMsg.befBillToScdLineAddr, rs.getString("BEF_BILL_TO_SCD_LINE_ADDR"));
////            ZYPEZDItemValueSetter.setValue(fMsg.befBillToThirdLineAddr, rs.getString("BEF_BILL_TO_THIRD_LINE_ADDR"));
////            ZYPEZDItemValueSetter.setValue(fMsg.befBillToFrthLineAddr, rs.getString("BEF_BILL_TO_FRTH_LINE_ADDR"));
////            ZYPEZDItemValueSetter.setValue(fMsg.befBillToCtyAddr, rs.getString("BEF_BILL_TO_CTY_ADDR"));
////            ZYPEZDItemValueSetter.setValue(fMsg.befBillToStCd, rs.getString("BEF_BILL_TO_ST_CD"));
////            ZYPEZDItemValueSetter.setValue(fMsg.befBillToPostCd, rs.getString("BEF_BILL_TO_POST_CD"));
////            ZYPEZDItemValueSetter.setValue(fMsg.befBillToCntyNm, rs.getString("BEF_BILL_TO_CNTY_NM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.befLocFirstLineAddr, rs.getString("BEF_LOC_FIRST_LINE_ADDR"));
//            ZYPEZDItemValueSetter.setValue(fMsg.befLocScdLineAddr, rs.getString("BEF_LOC_SCD_LINE_ADDR"));
//            ZYPEZDItemValueSetter.setValue(fMsg.befLocThirdLineAddr, rs.getString("BEF_LOC_THIRD_LINE_ADDR"));
//            ZYPEZDItemValueSetter.setValue(fMsg.befLocFrthLineAddr, rs.getString("BEF_LOC_FRTH_LINE_ADDR"));
//            ZYPEZDItemValueSetter.setValue(fMsg.befShipToCtyAddr, rs.getString("BEF_SHIP_TO_CTY_ADDR"));
//            ZYPEZDItemValueSetter.setValue(fMsg.befShipToStNm, rs.getString("BEF_SHIP_TO_ST_NM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.befShipToPostCd, rs.getString("BEF_SHIP_TO_POST_CD"));
//            ZYPEZDItemValueSetter.setValue(fMsg.befShipToCntyNm, rs.getString("BEF_SHIP_TO_CNTY_NM"));
//            // 2017/10/17 QC#21782 mod end
//            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A1, rs.getString("XX_SCR_ITEM_61_TXT"));
//            ZYPEZDItemValueSetter.setValue(fMsg.lineBizTpDescTxt, rs.getString("LINE_BIZ_TP_DESC_TXT"));
//            ZYPEZDItemValueSetter.setValue(fMsg.resrcTrtyOrgNm, rs.getString("RESRC_TRTY_ORG_NM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.candiTrtyNm, rs.getString("CANDI_TRTY_NM"));
//            String dt1 = rs.getString("XTRNL_CRAT_DT_TM_TS");
//            if (dt1 != null && !dt1.isEmpty()) {
//                dt1 = dt1.substring(0, DT_LEN);
//                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A1, ZYPDateUtil.formatEzd8ToDisp(dt1));
//            }
//            ZYPEZDItemValueSetter.setValue(fMsg.matchAcctLocNum, rs.getString("MATCH_ACCT_LOC_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.trtyOrgNm, rs.getString("TRTY_ORG_NM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A2, rs.getString("XX_SCR_ITEM_61_TXT_A2"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftLocNum, rs.getString("AFT_LOC_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftDsXrefAcctCd, rs.getString("AFT_DS_XREF_ACCT_CD"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftLocFirstLineAddr, rs.getString("AFT_LOC_FIRST_LINE_ADDR"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftLocScdLineAddr, rs.getString("AFT_LOC_SCD_LINE_ADDR"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftLocThirdLineAddr, rs.getString("AFT_LOC_THIRD_LINE_ADDR"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftLocFrthLineAddr, rs.getString("AFT_LOC_FRTH_LINE_ADDR"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftLocCtyAddr, rs.getString("AFT_LOC_CTY_ADDR"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftLocStCd, rs.getString("AFT_LOC_ST_CD"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftLocPostCd, rs.getString("AFT_LOC_POST_CD"));
//            ZYPEZDItemValueSetter.setValue(fMsg.cntyNm, rs.getString("CNTY_NM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftCtryCd, rs.getString("AFT_CTRY_CD"));
//            ZYPEZDItemValueSetter.setValue(fMsg.befTelNum, rs.getString("BEF_TEL_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftTelNum, rs.getString("AFT_TEL_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.befFaxNum, rs.getString("BEF_FAX_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftFaxNum, rs.getString("AFT_FAX_NUM"));
//            String dt2 = rs.getString("XTRNL_LAST_CRAT_DT_TM_TS");
//            if (dt2 != null && !dt2.isEmpty()) {
//                dt2 = dt2.substring(0, DT_LEN);
//                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A2, ZYPDateUtil.formatEzd8ToDisp(dt2));
//            }
//            ZYPEZDItemValueSetter.setValue(fMsg.befDunsNum, rs.getString("BEF_DUNS_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.befDsCustSicCd, rs.getString("BEF_DS_CUST_SIC_CD"));
//            ZYPEZDItemValueSetter.setValue(fMsg.befDsUltDunsNum, rs.getString("BEF_DS_ULT_DUNS_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftDsAcctDunsNm, rs.getString("AFT_DS_ACCT_DUNS_NM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftDsLocRevAmt, rs.getBigDecimal("AFT_DS_LOC_REV_AMT"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftDunsNum, rs.getString("AFT_DUNS_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftDsCustSicDescTxt, rs.getString("AFT_DS_CUST_SIC_DESC_TXT"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftDsLocEmpNum, rs.getBigDecimal("AFT_DS_LOC_EMP_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftDsCustSicCd, rs.getString("AFT_DS_CUST_SIC_CD"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftDsUltDunsNum, rs.getString("AFT_DS_ULT_DUNS_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftGlnNum, rs.getBigDecimal("AFT_GLN_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftDsPrntDunsNum, rs.getString("AFT_DS_PRNT_DUNS_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftHqDunsNum, rs.getString("AFT_HQ_DUNS_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftDsCmpySicCd, rs.getString("AFT_DS_CMPY_SIC_CD"));
//            ZYPEZDItemValueSetter.setValue(fMsg.aftDsCmpySicDescTxt, rs.getString("AFT_DS_CMPY_SIC_DESC_TXT"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.locNum, rs.getString("LOC_NUM"));
//
//            // 2017/11/21 QC#22525 add start
//            ZYPEZDItemValueSetter.setValue(fMsg.resrcTrtyOrgNm, setResrcTrtyOrgNm(rs.getString("BEF_PSN_NUM")));
//            // 2017/11/21 QC#22525 add end
//
//            csvOutFile.write();
//        } while (rs.next());
//
//        csvOutFile.close();
//    }
    // Del End 2018/01/16 QC#23148

    private void writeUploadCsvFile(NMAL2800CMsg bizMsg, ResultSet rs) throws SQLException {

        // Add Start 2018/01/16 QC#23148
        String glblCmpyCd = getGlobalCompanyCode();
        // Add End 2018/01/16 QC#23148

        NMAL2800F01FMsg fMsg = new NMAL2800F01FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_U.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

        // write header
        csvOutFile.writeHeader(CSV_UPLOAD_HEADER, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        // Add Start 2018/04/11 QC#23149-2
        CTRYTMsg ctryTMsg = new CTRYTMsg();
        ZYPEZDItemValueSetter.setValue(ctryTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(ctryTMsg.ctryCd, CTRY.UNITED_STATES_OF_AMERICA);
        ctryTMsg = (CTRYTMsg) ZYPCodeDataUtil.findByKey(ctryTMsg);
        // Add End 2018/04/11 QC#23149-2

        // write contents
        do {
            if (rs.getRow() >= CSV_LIMIT_COUNT) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }

            // 2017/10/20 QC#21866 add start
            ZYPEZDItemValueSetter.setValue(fMsg.prosRvwStsCd, rs.getString("PROS_RVW_STS_CD"));
            // 2017/10/20 QC#21866 add end
            ZYPEZDItemValueSetter.setValue(fMsg.rvwProsNum, rs.getString("RVW_PROS_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.befDsAcctNm, rs.getString("BEF_DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.befBillToFirstLineAddr, rs.getString("BEF_BILL_TO_FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.befBillToScdLineAddr, rs.getString("BEF_BILL_TO_SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.befBillToThirdLineAddr, rs.getString("BEF_BILL_TO_THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.befBillToFrthLineAddr, rs.getString("BEF_BILL_TO_FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.befBillToCtyAddr, rs.getString("BEF_BILL_TO_CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.befBillToStCd, rs.getString("BEF_BILL_TO_ST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.befBillToPostCd, rs.getString("BEF_BILL_TO_POST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.befBillToCntyNm, rs.getString("BEF_BILL_TO_CNTY_NM"));
            // Mod Start 2018/01/16 QC#23148
            //ZYPEZDItemValueSetter.setValue(fMsg.befLocFirstLineAddr, rs.getString("BEF_LOC_FIRST_LINE_ADDR"));
            //ZYPEZDItemValueSetter.setValue(fMsg.befLocScdLineAddr, rs.getString("BEF_LOC_SCD_LINE_ADDR"));
            //ZYPEZDItemValueSetter.setValue(fMsg.befLocThirdLineAddr, rs.getString("BEF_LOC_THIRD_LINE_ADDR"));
            //ZYPEZDItemValueSetter.setValue(fMsg.befLocFrthLineAddr, rs.getString("BEF_LOC_FRTH_LINE_ADDR"));
            //ZYPEZDItemValueSetter.setValue(fMsg.befShipToCtyAddr, rs.getString("BEF_SHIP_TO_CTY_ADDR"));
            //ZYPEZDItemValueSetter.setValue(fMsg.befShipToStNm, rs.getString("BEF_SHIP_TO_ST_NM"));
            //ZYPEZDItemValueSetter.setValue(fMsg.befShipToPostCd, rs.getString("BEF_SHIP_TO_POST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.befLocFirstLineAddr_A1, rs.getString("BEF_LOC_FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.befLocScdLineAddr_A1, rs.getString("BEF_LOC_SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.befLocThirdLineAddr_A1, rs.getString("BEF_LOC_THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.befLocFrthLineAddr_A1, rs.getString("BEF_LOC_FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.befShipToCtyAddr_A1, rs.getString("BEF_SHIP_TO_CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.befShipToStNm_A1, rs.getString("BEF_SHIP_TO_ST_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.befShipToPostCd_A1, rs.getString("BEF_SHIP_TO_POST_CD"));
            // Mod End 2018/01/16 QC#23148
            ZYPEZDItemValueSetter.setValue(fMsg.befShipToCntyNm, rs.getString("BEF_SHIP_TO_CNTY_NM"));
            // Mod Start 2018/01/16 QC#23148
            //ZYPEZDItemValueSetter.setValue(fMsg.befCtryNm, rs.getString("BEF_CTRY_NM"));
            // Mod Start 2018/04/11 QC#23149-2
            if (ZYPCommonFunc.hasValue(rs.getString("BEF_CTRY_NM"))) {
                ZYPEZDItemValueSetter.setValue(fMsg.befCtryNm_A1, rs.getString("BEF_CTRY_NM"));
            } else {
                ZYPEZDItemValueSetter.setValue(fMsg.befCtryNm_A1, ctryTMsg.ctryDescTxt);
            }
            // Mod End 2018/04/11 QC#23149-2
            // Mod End 2018/01/16 QC#23148
            ZYPEZDItemValueSetter.setValue(fMsg.befDunsNum, rs.getString("BEF_DUNS_NUM"));
            // Del Start 2018/01/16 QC#23148
            //ZYPEZDItemValueSetter.setValue(fMsg.befDsLocEmpNum, rs.getBigDecimal("BEF_DS_LOC_EMP_NUM"));
            // Del End 2018/01/16 QC#23148
            ZYPEZDItemValueSetter.setValue(fMsg.befDsCustSicCd, rs.getString("BEF_DS_CUST_SIC_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.befDsUltDunsNum, rs.getString("BEF_DS_ULT_DUNS_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.befTelNum, rs.getString("BEF_TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.befFaxNum, rs.getString("BEF_FAX_NUM"));
            // Del Start 2018/01/16 QC#23148
            //ZYPEZDItemValueSetter.setValue(fMsg.acctIdAddrRvwProsFlg, rs.getString("ACCT_ID_ADDR_RVW_PROS_FLG"));
            // Del End 2018/01/16 QC#23148
            ZYPEZDItemValueSetter.setValue(fMsg.aftDsXrefAcctCd, rs.getString("AFT_DS_XREF_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.trtyOrgNm, rs.getString("TRTY_ORG_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.befPsnNum, rs.getString("BEF_PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt, rs.getString("XX_SCR_ITEM_61_TXT"));
            // Del Start 2018/01/16 QC#23148
            //ZYPEZDItemValueSetter.setValue(fMsg.aftLocNum, rs.getString("AFT_LOC_NUM"));
            // Del End 2018/01/16 QC#23148
            ZYPEZDItemValueSetter.setValue(fMsg.candiTrtyNm, rs.getString("CANDI_TRTY_NM"));
            String dt1 = rs.getString("XTRNL_CRAT_DT_TM_TS");
            if (dt1 != null && !dt1.isEmpty()) {
                dt1 = dt1.substring(0, DT_LEN);
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A1, ZYPDateUtil.formatEzd8ToDisp(dt1));
            }
            String dt2 = rs.getString("XTRNL_LAST_CRAT_DT_TM_TS");
            if (dt2 != null && !dt2.isEmpty()) {
                dt2 = dt2.substring(0, DT_LEN);
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A2, ZYPDateUtil.formatEzd8ToDisp(dt2));
            }
            ZYPEZDItemValueSetter.setValue(fMsg.aftDsAcctNm, rs.getString("AFT_DS_ACCT_NM"));
            // Mod Start 2018/01/16 QC#23148
            //ZYPEZDItemValueSetter.setValue(fMsg.aftLocFirstLineAddr, rs.getString("AFT_LOC_FIRST_LINE_ADDR"));
            //ZYPEZDItemValueSetter.setValue(fMsg.aftLocScdLineAddr, rs.getString("AFT_LOC_SCD_LINE_ADDR"));
            //ZYPEZDItemValueSetter.setValue(fMsg.aftLocThirdLineAddr, rs.getString("AFT_LOC_THIRD_LINE_ADDR"));
            //ZYPEZDItemValueSetter.setValue(fMsg.aftLocFrthLineAddr, rs.getString("AFT_LOC_FRTH_LINE_ADDR"));
            //ZYPEZDItemValueSetter.setValue(fMsg.aftLocCtyAddr, rs.getString("AFT_LOC_CTY_ADDR"));
            //ZYPEZDItemValueSetter.setValue(fMsg.aftLocStCd, rs.getString("AFT_LOC_ST_CD"));
            //ZYPEZDItemValueSetter.setValue(fMsg.aftLocPostCd, rs.getString("AFT_LOC_POST_CD"));
            //ZYPEZDItemValueSetter.setValue(fMsg.cntyNm, rs.getString("CNTY_NM"));
            //ZYPEZDItemValueSetter.setValue(fMsg.aftCtryCd, rs.getString("AFT_CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.befLocFirstLineAddr_A2, rs.getString("BEF_LOC_FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.befLocScdLineAddr_A2, rs.getString("BEF_LOC_SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.befLocThirdLineAddr_A2, rs.getString("BEF_LOC_THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.befLocFrthLineAddr_A2, rs.getString("BEF_LOC_FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.befShipToCtyAddr_A2, rs.getString("BEF_SHIP_TO_CTY_ADDR"));
            // Mod Start 2018/03/28 QC#23149
//            ZYPEZDItemValueSetter.setValue(fMsg.aftLocStCd, rs.getString("BEF_SHIP_TO_ST_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.stDescTxt, rs.getString("BEF_SHIP_TO_ST_NM"));
            // Mod End 2018/03/28 QC#23149
            ZYPEZDItemValueSetter.setValue(fMsg.befShipToPostCd_A2, rs.getString("BEF_SHIP_TO_POST_CD"));

            String cntyNm = NMAL2800CommonLogic.getCntyNmForDownload(glblCmpyCd, rs);
            ZYPEZDItemValueSetter.setValue(fMsg.cntyNm, cntyNm);

            // Mod Start 2018/04/11 QC#23149-2
            if (ZYPCommonFunc.hasValue(rs.getString("BEF_CTRY_NM"))) {
                ZYPEZDItemValueSetter.setValue(fMsg.befCtryNm_A2, rs.getString("BEF_CTRY_NM"));
            } else {
                ZYPEZDItemValueSetter.setValue(fMsg.befCtryNm_A2, ctryTMsg.ctryDescTxt);
            }
            // Mod Start 2018/04/11 QC#23149-2
            // Mod End 2018/01/16 QC#23148
            // Del Start 2018/01/16 QC#23148
            //ZYPEZDItemValueSetter.setValue(fMsg.aftTrtyOrgCd, rs.getString("AFT_TRTY_ORG_CD"));
            // Del End 2018/01/16 QC#23148
            ZYPEZDItemValueSetter.setValue(fMsg.aftTelNum, rs.getString("AFT_TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.aftFaxNum, rs.getString("AFT_FAX_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.aftDsAcctDunsNm, rs.getString("AFT_DS_ACCT_DUNS_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.aftDsLocRevAmt, rs.getBigDecimal("AFT_DS_LOC_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.aftDunsNum, rs.getString("AFT_DUNS_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.aftDsCustSicDescTxt, rs.getString("AFT_DS_CUST_SIC_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.aftDsLocEmpNum, rs.getBigDecimal("AFT_DS_LOC_EMP_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.aftDsCustSicCd, rs.getString("AFT_DS_CUST_SIC_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.aftDsUltDunsNum, rs.getString("AFT_DS_ULT_DUNS_NUM"));
            // Del Start 2018/01/16 QC#23148
            //ZYPEZDItemValueSetter.setValue(fMsg.aftGlnNum, rs.getBigDecimal("AFT_GLN_NUM"));
            // Del End 2018/01/16 QC#23148
            ZYPEZDItemValueSetter.setValue(fMsg.aftDsPrntDunsNum, rs.getString("AFT_DS_PRNT_DUNS_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.aftHqDunsNum, rs.getString("AFT_HQ_DUNS_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.aftDsCmpySicCd, rs.getString("AFT_DS_CMPY_SIC_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.aftDsCmpySicDescTxt, rs.getString("AFT_DS_CMPY_SIC_DESC_TXT"));
            // Del Start 2018/01/16 QC#23148
            //ZYPEZDItemValueSetter.setValue(fMsg.matchAcctLocNum, rs.getString("MATCH_ACCT_LOC_NUM"));
            //ZYPEZDItemValueSetter.setValue(fMsg.retanFldTxt, rs.getString("RETAN_FLD_TXT"));
            // Del End 2018/01/16 QC#23148

            // Add Start 2018/01/16 QC#23148
            String lineBizTpDescTxt = NMAL2800CommonLogic.getLineBizTpDescTxt(rs.getString("BEF_PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.lineBizTpDescTxt, lineBizTpDescTxt);
            // Mod Start 2018/03/02 QC#23148-1
            //ZYPEZDItemValueSetter.setValue(fMsg.resrcTrtyOrgNm, rs.getString("RESRC_TRTY_ORG_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.resrcTrtyOrgNm, setResrcTrtyOrgNm(rs.getString("BEF_PSN_NUM")));
            // Mod End 2018/03/02 QC#23148-1
            // Add End 2018/01/16 QC#23148

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }
}
