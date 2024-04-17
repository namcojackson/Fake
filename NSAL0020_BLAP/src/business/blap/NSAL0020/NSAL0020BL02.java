/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0020;

import static business.blap.NSAL0020.constant.NSAL0020Constant.BUSINESS_ID;
import static business.blap.NSAL0020.constant.NSAL0020Constant.DOWNLOAD_LIMIT_COUNT;
import static business.blap.NSAL0020.constant.NSAL0020Constant.MAX_DATE;
import static business.blap.NSAL0020.constant.NSAL0020Constant.NZZM0000E;
import static business.blap.NSAL0020.constant.NSAL0020Constant.NZZM0001W;
import static business.blap.NSAL0020.constant.NSAL0020Constant.SCRN_ID;
import static business.blap.NSAL0020.constant.NSAL0020Constant.SEARCH_LIMIT_COUNT;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSAL0020.common.NSAL0020CommonLogic;
import business.file.NSAL0020F00FMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.ContrInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001Contr;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;


/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   Hitachi         T.Yonekura      Create          N/A
 * 2013/08/28   Hitachi         A.Kohinata      Update          QC1830
 * 2013/11/21   Hitachi         T.Aoyagi        Update          QC2852
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Update          N/A(No Mark up comment)
 * 2015/12/14   Hitachi         T.Tomita        Update          CSA QC#1032
 * 2016/02/04   Hitachi         T.Tomita        Update          CSA QC#545
 * 2016/02/18   Hitachi         A.Kohinata      Update          CSA QC#956
 * 2016/05/11   Hitachi         M.Gotou         Update          CSA QC#7820
 * 2016/05/13   Hitachi         T.Tomita        Update          QC#4578
 * 2016/05/24   Hitachi         M.Gotou         Update          QC#8465
 * 2016/07/07   Hitachi         T.Tomita        Update          QC#11368
 * 2016/09/15   Hitachi         N.Arai          Update          QC#11616
 * 2016/10/28   Hitachi         T.Tomita        Update          QC#15468
 * 2016/12/13   Hitachi         K.Ochiai        Update          QC#16563
 * 2018/03/05   Hitachi         K.Kojima        Update          QC#24477
 * 2018/04/13   Hitachi         K.Kojima        Update          QC#25167
 * 2018/07/23   Hitachi         K.Kojima        Update          QC#27326
 * 2018/07/27   Hitachi         K.Kojima        Update          QC#27326-1
 * 2018/10/01   Hitachi         K.Kojima        Update          QC#28389
 *</pre>
 */
public class NSAL0020BL02 extends S21BusinessHandler implements ZYPConstant {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0020CMsg cMsg = (NSAL0020CMsg) arg0;
        NSAL0020SMsg sMsg = (NSAL0020SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0020_INIT".equals(screenAplID)) {
                doProcess_NSAL0020_INIT(cMsg, sMsg);
                // add start 2016/05/11 CSA Defect#7820
                ZYPGUITableColumn.getColData(cMsg, sMsg);
                // add end 2016/05/11 CSA Defect#7820
            } else if ("NSAL0020Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0020Scrn00_Search(cMsg, sMsg);
            } else if ("NSAL0020Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL0020Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NSAL0020Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0020Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSAL0020Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0020Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0020Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0020Scrn00_PagePrev(cMsg, sMsg);
            } else if (screenAplID.endsWith("_OnChangeSavedSearchOption")) {
                doProcess_NSAL0020_OnChangeSavedSearchOption((NSAL0020CMsg) cMsg, (NSAL0020SMsg) sMsg);
            } else if (screenAplID.endsWith("_SaveSearch")) {
                doProcess_NSAL0020_SaveSearch((NSAL0020CMsg) cMsg, (NSAL0020SMsg) sMsg);
            } else if (screenAplID.endsWith("_DeleteSearch")) {
                doProcess_NSAL0020_DeleteSearch((NSAL0020CMsg) cMsg, (NSAL0020SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0020_DeleteSearch(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {
        return;
    }

    private void doProcess_NSAL0020_SaveSearch(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {
        return;
    }

    /**
     * Initialize event handler
     * @param cMsg NSAL0020CMsg
     * @param sMsg NSAL0020SMsg
     */
    private void doProcess_NSAL0020_INIT(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {
        // get global company code
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        // get sales date
        cMsg.slsDt.setValue(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        // create pulldown
        // Machine Status
        createSvcMachMstrStsTpPulldown(cMsg);
        // Mdse Type
        createCoaMdseTpCdPulldown(cMsg);
        // Contact Type
        createSvcCtacTpCdPulldown(cMsg);
        // Saved Search Options Pulldown
        NSAL0020CommonLogic.createSavedSearchOptionsPullDown(cMsg, getContextUserInfo().getUserId());
        // set check box
        setCheckBox(cMsg);
    }


    /**
     * Create SvcMachMstrStsTp Pulldown
     * @param cMsg NSAL0020CMsg
     */
    private void createSvcMachMstrStsTpPulldown(NSAL0020CMsg cMsg) {
        S21SsmEZDResult ssmResult = NSAL0020Query.getInstance().getSvcMachMstrStsTpList(cMsg);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> map = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrStsCd_P.no(i), (String) map.get("SVC_MACH_MSTR_STS_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrStsDescTxt_P.no(i), (String) map.get("SVC_MACH_MSTR_STS_DESC_TXT"));
            }
        }
    }

    /**
     * Create createCoaMdseTpCd Pulldown
     * @param cMsg NSAL0020CMsg
     */
    private void createCoaMdseTpCdPulldown(NSAL0020CMsg cMsg) {
        // START 2016/02/04 T.Tomita [QC#545, MOD]
//        S21SsmEZDResult ssmResult = NSAL0020Query.getInstance().getCoaMdseTpCdList(cMsg);
//        if (ssmResult.isCodeNormal()) {
//            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
//            for (int i = 0; i < resultList.size(); i++) {
//                Map<String, String> map = (Map<String, String>) resultList.get(i);
//                ZYPEZDItemValueSetter.setValue(cMsg.coaMdseTpCd_P.no(i), (String) map.get("COA_MDSE_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(cMsg.coaMdseTpDescTxt_P.no(i), (String) map.get("COA_MDSE_TP_DESC_TXT"));
//            }
//        }
        ZYPCodeDataUtil.createPulldownList(COA_PROJ.class, cMsg.coaMdseTpCd_P, cMsg.coaMdseTpDescTxt_P);
        // END 2016/02/04 T.Tomita [QC#545, MOD]
    }

    /**
     * Create SvcCtacTpCd Pulldown
     * @param cMsg NSAL0020CMsg
     */
    private void createSvcCtacTpCdPulldown(NSAL0020CMsg cMsg) {
        S21SsmEZDResult ssmResult = NSAL0020Query.getInstance().getSvcCtacTpCdList(cMsg);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> map = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.svcCtacTpCd_P.no(i), (String) map.get("SVC_CTAC_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.svcCtacTpNm_P.no(i), (String) map.get("SVC_CTAC_TP_NM"));
            }
        }
    }

    private void setCheckBox(NSAL0020CMsg cMsg) {
        cMsg.xxChkBox.setValue(FLG_OFF_N);
    }

    private void doProcess_NSAL0020_OnChangeSavedSearchOption(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            NSAL0020CommonLogic.callNszc0330forSearchOption(cMsg, sMsg, getContextUserInfo().getUserId());
        }
    }

    /**
     * Search event handler
     * @param cMsg NSAL0020CMsg
     * @param sMsg NSAL0020SMsg
     */
    private void doProcess_NSAL0020Scrn00_Search(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {

        clearSearchResult(cMsg, sMsg);

        HashMap<String, Object> params = setMachineMasterSearchParam(cMsg, SEARCH_LIMIT_COUNT + 1);
        // START 2018/03/23 K.Kojima [QC#22722,ADD]
        outputSearchLog(cMsg);
        // END 2018/03/23 K.Kojima [QC#22722,ADD]
        // START 2018/03/05 K.Kojima [QC#24477,MOD]
        // S21SsmEZDResult ssmResult = NSAL0020Query.getInstance().getMachMstrInfoList(params, sMsg);
        S21SsmEZDResult ssmResult = NSAL0020Query.getInstance().getMachMstrInfoListForScreen(params, sMsg);
        // END 2018/03/05 K.Kojima [QC#24477,MOD]
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            return;
        }
        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_COUNT) {
            cMsg.setMessageInfo(NZZM0001W, null);
            sMsg.A.setValidCount(SEARCH_LIMIT_COUNT);
        }

        if (hasValue(cMsg.dsContrNum)) {
            resetSMsg(sMsg);
        }

        cMsg.xxRadioBtn.setValue(0);

        NSAL0020CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

    }

    // QC1830 Add Start
    private void resetSMsg(NSAL0020SMsg globalMsg) {

        NSAL0020SMsg sMsg = new NSAL0020SMsg();
        NSAL0020_ASMsgArray sMsgArray = sMsg.A;
        ZYPTableUtil.clear(sMsgArray);
        int j = 0;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            NSXC001001Contr dsxc001001Contr = new NSXC001001Contr();
            ContrInfoBean contrInfoBean = dsxc001001Contr.getContrInfo(getGlobalCompanyCode(), globalMsg.A.no(i).svcConfigMstrPk_A0.getValue(), globalMsg.A.no(i).svcMachMstrPk_A0.getValue(), null);
            if (contrInfoBean != null && hasValue(contrInfoBean.getContrNum())) {
                EZDMsg.copy(globalMsg.A.no(i), null, sMsgArray.no(j), null);
                j++;
            }
        }

        ZYPTableUtil.clear(globalMsg.A);
        EZDMsg.copy(sMsgArray, null, globalMsg.A, null);
        globalMsg.A.setValidCount(j);
    }

    /**
     * Create Machine Master search query parameter
     * @param bizMsg NSAL0020CMsg
     * @param limitCount int
     * @return HashMap<String, Object> Machine Master search parameter
     */
    private HashMap<String, Object> setMachineMasterSearchParam(NSAL0020CMsg bizMsg, int limitCount) {

        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("bizMsg", bizMsg);
        params.put("svcMachMstrSts", SVC_MACH_MSTR_STS.TERMINATED);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("maxDate", MAX_DATE);
        // add start 2016/07/07 CSA Defect#11368
        params.put("dsContrDtlStsCanc", DS_CONTR_DTL_STS.CANCELLED);
        params.put("warranty", DS_CONTR_CATG.WARRANTY);
        // add end 2016/07/07 CSA Defect#11368
        params.put("limitCount", limitCount);
        // START 2018/03/05 K.Kojima [QC#24477,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum) || ZYPCommonFunc.hasValue(bizMsg.tocNm)) {
            params.put("cpoSearchFlag", ZYPConstant.FLG_ON_Y);
        }
        // END 2018/03/05 K.Kojima [QC#24477,ADD]
        // START 2018/07/23 K.Kojima [QC#27326,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.serNum) && bizMsg.serNum.getValue().indexOf("%") >= 0) {
            params.put("serNumLikeFlag", ZYPConstant.FLG_ON_Y);
        } else {
            params.put("serNumLikeFlag", ZYPConstant.FLG_OFF_N);
        }
        // END 2018/07/23 K.Kojima [QC#27326,ADD]
        // START 2018/10/01 K.Kojima [QC#28389,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.xxScrItem30Txt_02) && bizMsg.xxScrItem30Txt_02.getValue().indexOf("%") >= 0) {
            params.put("svcConfigMstrPkLikeFlag", ZYPConstant.FLG_ON_Y);
        } else {
            params.put("svcConfigMstrPkLikeFlag", ZYPConstant.FLG_OFF_N);
        }
        // END 2018/10/01 K.Kojima [QC#28389,ADD]
        // START 2018/07/27 K.Kojima [QC#27326-1,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.xxScrItem30Txt_02) || ZYPCommonFunc.hasValue(bizMsg.xxScrItem30Txt_01) || ZYPCommonFunc.hasValue(bizMsg.svcSlnNm) || ZYPCommonFunc.hasValue(bizMsg.t_MdlNm)) {
            params.put("svcConfigMstrSearchFlag", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd) || ZYPCommonFunc.hasValue(bizMsg.mdseDescShortTxt) || ZYPCommonFunc.hasValue(bizMsg.coaMdseTpCd_PS)) {
            params.put("mdseSearchFlag", ZYPConstant.FLG_ON_Y);
        }
        // END 2018/07/27 K.Kojima [QC#27326-1,ADD]

        return params;

    }
    // QC1830 Add End

    /**
     * CMN Reset event handler
     * @param cMsg NSAL0020CMsg
     * @param sMsg NSAL0020SMsg
     */
    private void doProcess_NSAL0020Scrn00_CMN_Clear(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {

        clearSearchResult(cMsg, sMsg);
        // START 2016/05/24 M.Gotou [QC#8465, MOD]
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        cMsg.clear();
        setValue(cMsg.xxComnColOrdTxt, xxComnColOrdTxt);
        // END 2016/05/24 M.Gotou [QC#8465, MOD]
        doProcess_NSAL0020_INIT(cMsg, sMsg);

    }

    /**
     * Download event handler
     * @param cMsg NSAL0020CMsg
     * @param sMsg NSAL0020SMsg
     */
    private void doProcess_NSAL0020Scrn00_CMN_Download(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSAL0020Query dsal0020Query = NSAL0020Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(dsal0020Query.getClass());

            // create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
            HashMap<String, Object> params = setMachineMasterSearchParam(cMsg, DOWNLOAD_LIMIT_COUNT + 1);
            stmtSelect = ssmLLClient.createPreparedStatement("getMachMstrInfoList", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(NZZM0000E, null);
                return;
            }
            NSAL0020F00FMsg fMsg = new NSAL0020F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
            writeCsvFileForHeaderTab(cMsg, sMsg, rs, fMsg, csvOutFile);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * Write csv file
     * @param bizMsg NSAL0020CMsg
     * @param globalMsg NSAL0020SMsg
     * @param rs ResultSet
     * @param fMsg NSAL0020F00FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @throws SQLException
     */
    private void writeCsvFileForHeaderTab(NSAL0020CMsg bizMsg, NSAL0020SMsg globalMsg, ResultSet rs, NSAL0020F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        rs.last();
        if (rs.getRow() > DOWNLOAD_LIMIT_COUNT) {
            bizMsg.setMessageInfo(NZZM0001W, null);
        }
        rs.beforeFirst();

        // START 2016/02/18 [QC#956, DEL]
        //fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        // Set don't display column
        //fMsg.addExclusionItem("xxBizAppId_A0");
        //fMsg.addExclusionItem("xxBizAppId_A1");
        //fMsg.addExclusionItem("xxBizAppId_A2");
        // END 2016/02/18 [QC#956, DEL]

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, bizMsg);

        // write contents
        while (rs.next()) {
            if (rs.getRow() > DOWNLOAD_LIMIT_COUNT) {
                break;
            }

            // resultset -> fMsg
            // START 2016/02/18 [QC#956, MOD]
            // START 2016/05/13 T.Tomita [QC#4578, MOD]
            setValue(fMsg.serNum_A0, rs.getString("SER_NUM"));
            setValue(fMsg.mdseCd_A0, rs.getString("MDSE_CD"));
            // START 2016/09/15 N.Arai [QC#11616, MOD]
            // setValue(fMsg.mdseNm_A0, rs.getString("MDSE_NM"));
            setValue(fMsg.mdseDescShortTxt_A0, rs.getString("MDSE_DESC_SHORT_TXT"));
            // END 2016/09/15 N.Arai [QC#11616, MOD]
            setValue(fMsg.coaMdseTpCd_A0, rs.getString("COA_MDSE_TP_CD"));
            setValue(fMsg.svcMachMstrStsDescTxt_A0, rs.getString("SVC_MACH_MSTR_STS_DESC_TXT"));
            setValue(fMsg.t_MdlNm_A0, rs.getString("T_MDL_NM"));
            setValue(fMsg.svcConfigMstrPk_A0, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
            setValue(fMsg.svcSlnSq_A0, rs.getBigDecimal("SVC_SLN_SQ"));
            setValue(fMsg.svcSlnNm_A0, rs.getString("SVC_SLN_NM"));
             // START 2016/10/28 T.Tomita [QC#15468, MOD]
//            setValue(fMsg.istlDt_A0, rs.getString("ISTL_DT"));
            setValue(fMsg.xxDtTxt_A0, convertDateFormat(rs.getString("ISTL_DT")));
             // END 2016/10/28 T.Tomita [QC#15468, MOD]
            setValue(fMsg.svcMachMstrPk_A0, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
            setValue(fMsg.xxComnScrColValTxt_AO, rs.getString("XX_COMN_SCR_COL_VAL_TXT_O"));
            setValue(fMsg.ownrAcctNum_A0, rs.getString("OWNR_ACCT_NUM"));
//            setValue(fMsg.ownrLocNum_A0, rs.getString("OWNR_LOC_NUM"));
            setValue(fMsg.xxComnScrColValTxt_AC, rs.getString("XX_COMN_SCR_COL_VAL_TXT_C"));
            setValue(fMsg.curLocAcctNum_A0, rs.getString("CUR_LOC_ACCT_NUM"));
            setValue(fMsg.indCurLocNum_A0, rs.getString("IND_CUR_LOC_NUM"));
            setValue(fMsg.xxComnScrColValTxt_AB, rs.getString("XX_COMN_SCR_COL_VAL_TXT_B"));
            setValue(fMsg.billToAcctNum_A0, rs.getString("BILL_TO_ACCT_NUM"));
            setValue(fMsg.indBillToLocNum_A0, rs.getString("IND_BILL_TO_LOC_NUM"));
            setValue(fMsg.svcLicCnt_A0, rs.getBigDecimal("SVC_LIC_CNT"));
            setValue(fMsg.cpoOrdNum_A0, rs.getString("CPO_ORD_NUM"));
            setValue(fMsg.custMachCtrlNum_A0, rs.getString("CUST_MACH_CTRL_NUM"));
            // START 2016/12/13 K.Ochiai [QC#16563, MOD]
            setValue(fMsg.custIssPoNum_A0, rs.getString("CUST_ISS_PO_NUM"));
            // END 2016/12/13 K.Ochiai [QC#16563, MOD]
            setValue(fMsg.dsContrNum_A0, rs.getString("DS_CONTR_NUM"));
            setValue(fMsg.tocNm_A0, rs.getString("TOC_NM"));
            //setValue(fMsg.svcMachMstrStsCd_A0, rs.getString("SVC_MACH_MSTR_STS_CD"));
            //setValue(fMsg.ctacPsnLastNm_A0, rs.getString("CTAC_PSN_LAST_NM"));
            //setValue(fMsg.svcCtacTpNm_A0, rs.getString("SVC_CTAC_TP_NM"));
            // END 2016/05/13 T.Tomita [QC#4578, MOD]
            // END 2016/02/18 [QC#956, MOD]

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSAL0020F00FMsg fMsg, NSAL0020CMsg cMsg) {
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        // START 2016/02/18 [QC#956, MOD]
        // START 2015/12/14 T.Tomita [QC#1032, MOD]
        // START 2016/05/13 T.Tomita [QC#4578, MOD]
        final String[] csvHeader = new String[] {labelConv.convLabel2i18nLabel(SCRN_ID, "Serial#"), labelConv.convLabel2i18nLabel(SCRN_ID, "Mdse Cd"), labelConv.convLabel2i18nLabel(SCRN_ID, "Mdse Name"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "Mdse Type"), labelConv.convLabel2i18nLabel(SCRN_ID, "Machine Status"), labelConv.convLabel2i18nLabel(SCRN_ID, "Service Model"), labelConv.convLabel2i18nLabel(SCRN_ID, "Config ID"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "Solution#"), labelConv.convLabel2i18nLabel(SCRN_ID, "Solution Name"), labelConv.convLabel2i18nLabel(SCRN_ID, "Install Date"), labelConv.convLabel2i18nLabel(SCRN_ID, "Machine ID"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "IB Owner"), labelConv.convLabel2i18nLabel(SCRN_ID, "Owner Account#"), labelConv.convLabel2i18nLabel(SCRN_ID, "IB Current Location"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "Current Location Account#"), labelConv.convLabel2i18nLabel(SCRN_ID, "Current Loc#"), labelConv.convLabel2i18nLabel(SCRN_ID, "IB Bill To"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "Bill To Account#"), labelConv.convLabel2i18nLabel(SCRN_ID, "IB Bill To Loc#"), labelConv.convLabel2i18nLabel(SCRN_ID, "Software License#"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "Sales Order#"), labelConv.convLabel2i18nLabel(SCRN_ID, "External Reference#"), labelConv.convLabel2i18nLabel(SCRN_ID, "End Customer PO#"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "Contract#"), labelConv.convLabel2i18nLabel(SCRN_ID, "Sales Rep Name") };
        // END 2016/05/13 T.Tomita [QC#4578, MOD]
        // END 2015/12/14 T.Tomita [QC#1032, MOD]
        csvOutFile.writeHeader(csvHeader);
        // END 2016/02/18 [QC#956, MOD]
    }

    /**
     * Page next event handler
     * @param cMsg NSAL0020CMsg
     * @param sMsg NSAL0020SMsg
     */
    private void doProcess_NSAL0020Scrn00_PageNext(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {
        int pageFrom = 0;
        pageFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL0020CommonLogic.pagenation(cMsg, sMsg, pageFrom);
    }

    /**
     * Page prev event handler
     * @param cMsg NSAL0020CMsg
     * @param sMsg NSAL0020SMsg
     */
    private void doProcess_NSAL0020Scrn00_PagePrev(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {
        int pageFrom = 0;
        pageFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL0020CommonLogic.pagenation(cMsg, sMsg, pageFrom);
    }

    /**
     * clear Search Result
     * @param cMsg NSAL0020CMsg
     */
    private void clearSearchResult(NSAL0020CMsg bizMsg, NSAL0020SMsg globalMsg) {

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        globalMsg.A.clear();
        globalMsg.A.setValidCount(0);

        bizMsg.xxPageShowFromNum.setValue(0);
        bizMsg.xxPageShowToNum.setValue(0);
        bizMsg.xxPageShowOfNum.setValue(0);
    }

    // Old NSAB0020 Start----------------------------------------
//    /**
//     * CMN Reset event handler
//     * @param cMsg NSAL0020CMsg
//     * @param sMsg NSAL0020SMsg
//     */
//    private void doProcess_NSAL0020Scrn00_CMN_Reset(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {
//
//        clearSearchResult(cMsg, sMsg);
//        cMsg.clear();
//        doProcess_NSAL0020_INIT(cMsg, sMsg);
//
//    }
//
//    /**
//     * Page next event handler
//     * @param cMsg NSAL0020CMsg
//     * @param sMsg NSAL0020SMsg
//     */
//    private void doProcess_NSAL0020Scrn00_PageNext(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {
//        int pageFrom = 0;
//        pageFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
//        NSAL0020CommonLogic.pagenation(cMsg, sMsg, pageFrom);
//    }
//
//    /**
//     * Page prev event handler
//     * @param cMsg NSAL0020CMsg
//     * @param sMsg NSAL0020SMsg
//     */
//    private void doProcess_NSAL0020Scrn00_PagePrev(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {
//        int pageFrom = 0;
//        pageFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
//        NSAL0020CommonLogic.pagenation(cMsg, sMsg, pageFrom);
//    }
//
//    /**
//     * Search event handler
//     * @param cMsg NSAL0020CMsg
//     * @param sMsg NSAL0020SMsg
//     */
//    private void doProcess_NSAL0020Scrn00_Search(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {
//
//        clearSearchResult(cMsg, sMsg);
//
//        NSAL0020CommonLogic.setLayerNm(cMsg);
//
//        ArrayList<BigDecimal> svcMachMstrPkList = null;
//
//        if (hasValue(cMsg.dsContrNum)) {
//            // TODO call Contract Common Parts
//            svcMachMstrPkList = NSAL0020CommonLogic.searchContrInfo(cMsg);
//            if (svcMachMstrPkList == null || svcMachMstrPkList.size() == 0) {
//                cMsg.setMessageInfo(NZZM0000E, null);
//                return;
//            }
//        }
//
//        HashMap<String, Object> params = setMachineMasterSearchParam(cMsg, svcMachMstrPkList, SEARCH_LIMIT_COUNT + 1);
//        S21SsmEZDResult ssmResult = NSAL0020Query.getInstance().getMachMstrInfoList(params, sMsg);
//        if (ssmResult.isCodeNotFound()) {
//            cMsg.setMessageInfo(NZZM0000E, null);
//            return;
//        }
//        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_COUNT) {
//            cMsg.setMessageInfo(NZZM0001W, null);
//            sMsg.A.setValidCount(SEARCH_LIMIT_COUNT);
//        }
//
//        // QC1830 Add Start
//        if (hasValue(cMsg.dsContrNum)) {
//            resetSMsg(sMsg);
//        }
//        // QC1830 Add End
//
//        // TODO call Contract Common Parts
//        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
//            // Stub
//            NSAL0020CommonLogic.setContrInfo(getGlobalCompanyCode(), sMsg, cMsg.dsContrNum.getValue(), index);
//        }
//
//        setCdTpNm(cMsg, sMsg);
//
//        cMsg.xxRadioBtn.setValue(0);
//
//        NSAL0020CommonLogic.pagenation(cMsg, sMsg, 0);
//        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
//
//    }
//
//    /**
//     * Download event handler
//     * @param cMsg NSAL0020CMsg
//     * @param sMsg NSAL0020SMsg
//     */
//    private void doProcess_NSAL0020Scrn00_CMN_Download(NSAL0020CMsg cMsg, NSAL0020SMsg sMsg) {
//
//        ArrayList<BigDecimal> svcMachMstrPkList = null;
//        
//        NSAL0020CommonLogic.setLayerNm(cMsg);
//
//        if (hasValue(cMsg.dsContrNum)) {
//            // TODO call Contract Common Parts
//            svcMachMstrPkList = NSAL0020CommonLogic.searchContrInfo(cMsg);
//            if (svcMachMstrPkList == null || svcMachMstrPkList.size() == 0) {
//                cMsg.setMessageInfo(NZZM0000E, null);
//                return;
//            }
//        }
//
//        ResultSet rs = null;
//        PreparedStatement stmtSelect = null;
//
//        try {
//            NSAL0020Query dsal0020Query = NSAL0020Query.getInstance();
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(dsal0020Query.getClass());
//
//            // create csv file
//            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
//            HashMap<String, Object> params = setMachineMasterSearchParam(cMsg, svcMachMstrPkList, DOWNLOAD_LIMIT_COUNT + 1);
//            stmtSelect = ssmLLClient.createPreparedStatement("getMachMstrInfoList", params, execParam);
//            rs = stmtSelect.executeQuery();
//            if (!rs.next()) {
//                cMsg.setMessageInfo(NZZM0000E, null);
//                return;
//            }
//            NSAL0020F00FMsg fMsg = new NSAL0020F00FMsg();
//            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
//            writeCsvFileForHeaderTab(cMsg, sMsg, rs, fMsg, csvOutFile);
//
//        } catch (SQLException e) {
//            throw new S21AbendException(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
//        }
//    }
//
//    /**
//     * Write csv file
//     * @param bizMsg NSAL0020CMsg
//     * @param globalMsg NSAL0020SMsg
//     * @param rs ResultSet
//     * @param fMsg NSAL0020F00FMsg
//     * @param csvOutFile ZYPCSVOutFile
//     * @throws SQLException
//     */
//    private void writeCsvFileForHeaderTab(NSAL0020CMsg bizMsg, NSAL0020SMsg globalMsg, ResultSet rs, NSAL0020F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {
//
//        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
//
//        rs.last();
//        if (rs.getRow() > DOWNLOAD_LIMIT_COUNT) {
//            bizMsg.setMessageInfo(NZZM0001W, null);
//        }
//        rs.beforeFirst();
//
//        // write header
//        writeCsvFileHeader(csvOutFile);
//
//        // write contents
//        while (rs.next()) {
//            if (rs.getRow() > DOWNLOAD_LIMIT_COUNT) {
//                break;
//            }
//
//            // QC1830 Add Start
//            NSXC001001Contr dsxc001001Contr = new NSXC001001Contr();
//            ContrInfoBean contrInfoBean = dsxc001001Contr.getContrInfo(glblCmpyCd, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"), rs.getBigDecimal("SVC_MACH_MSTR_PK"), null);
//            if (hasValue(bizMsg.dsContrNum)) {
//                if (contrInfoBean == null || !hasValue(contrInfoBean.getContrNum())) {
//                    continue;
//                }
//            }
//            // QC1830 Add End
//
//            // resultset -> fMsg
//            setValue(fMsg.svcConfigMstrPk_A0, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
//            setValue(fMsg.svcConfigTpCd_A0, rs.getString("SVC_CONFIG_TP_CD"));
//            setValue(fMsg.serNum_A0, rs.getString("SER_NUM"));
//            setValue(fMsg.mdlNm_A0, rs.getString("MDL_NM"));
//            setValue(fMsg.mdseCd_A0, rs.getString("MDSE_CD"));
//            setValue(fMsg.mdseNm_A0, getMdseNm(glblCmpyCd, rs.getString("MDSE_CD")));
//            setValue(fMsg.custMachCtrlNum_A0, rs.getString("CUST_MACH_CTRL_NUM"));
//            setValue(fMsg.svcMachTpNm_A0, getSvcMachTpNm(glblCmpyCd, rs.getString("SVC_MACH_TP_CD")));
//            setValue(fMsg.usrDlrTpNm_A0, getUsrDlrTpNm(glblCmpyCd, rs.getString("USR_DLR_TP_CD")));
//            setValue(fMsg.postCd_A0, rs.getString("POST_CD"));
//            setValue(fMsg.locNm_A0, rs.getString("LOC_NM"));
//            setValue(fMsg.svcMachMstrStsNm_A0, getMachMstrStsNm(glblCmpyCd, rs.getString("SVC_MACH_MSTR_STS_CD")));
//            setValue(fMsg.orgLayerNum_A0, rs.getBigDecimal("ORG_LAYER_NUM"));
//            setValue(fMsg.orgNm_A0, rs.getString("ORG_NM"));
//            setValue(fMsg.orgCd_A0, rs.getString("ORG_CD"));
//            // QC1830 Del Start
//            //NSXC001001Contr dsxc001001Contr = new NSXC001001Contr();
//            //ContrInfoBean contrInfoBean = dsxc001001Contr.getContrInfo(glblCmpyCd, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"), rs.getBigDecimal("SVC_MACH_MSTR_PK"), rs.getString("ISTL_DT"));
//            // QC1830 Del End
//            if (contrInfoBean != null) {
//                setValue(fMsg.dsContrNum_A0, contrInfoBean.getContrNum());
//            } else {
//                setValue(fMsg.dsContrNum_A0, "");
//            }
//            setValue(fMsg.cpoOrdNum_A0, rs.getString("CPO_ORD_NUM"));
//            setValue(fMsg.sellToCustCd_A0, rs.getString("SELL_TO_CUST_CD"));
//            setValue(fMsg.locNm_SE, getSellToNm(glblCmpyCd, rs.getString("SELL_TO_CUST_CD")));
//            setValue(fMsg.soNum_A0, rs.getString("SO_NUM"));
//            setValue(fMsg.xxDtTxt_A0, toMMddyyyy(rs.getString("SHIP_DT")));
//            setValue(fMsg.xxDtTxt_A1, toMMddyyyy(rs.getString("ISTL_DT")));
//            setValue(fMsg.xxDtTxt_A2, toMMddyyyy(rs.getString("SVC_MACH_RMV_DT")));
//            setValue(fMsg.shipFromWhCd_A0, rs.getString("SHIP_FROM_WH_CD"));
//            setValue(fMsg.rtrnToWhCd_A0, rs.getString("RTRN_TO_WH_CD"));
//
//            csvOutFile.write();
//        }
//
//        csvOutFile.close();
//    }
//
//    private String toMMddyyyy(String date) {
//        if (hasValue(date)) {
//            // START 2013/11/21 T.Aoyagi [QC2852,MOD]
//            // date = ZYPDateUtil.DateFormatter(date, YYYY_MM_DD, FMT_YYYY_MM_DD);
//            date = ZYPDateUtil.formatEzd8ToDisp(date, true);
//            // END 2013/11/21 T.Aoyagi [QC2852,MOD]
//        }
//        return date;
//    }
//
//    /**
//     * Write csv header info for download Header tab.
//     * @param csvOutFile ZYPCSVOutFile
//     */
//    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile) {
//
//        final String[] csvHeader = new String[] {"Config#", "Config Type", "Serial#", "Model Name", "Mdse Code", "Mdse Name", "Machine#", "Machine Type", "User Dealer Type", "Post Code", "Location Name", "Machine Status",
//                "Service Org Code", "Service Org Name", "Contract#", "Order Num", "Sell To Code", "Sell To Name", "SO Num", "Shipped Date", "Installation Date", "Removed Date", "Ship WH", "Return WH", "Org Code", "Org Name", "Org Layer Num" };
//
//        csvOutFile.writeHeader(csvHeader);
//    }
//
//    /**
//     * Create User Dealer Type Pulldown
//     * @param cMsg NSAL0020CMsg
//     */
//    private void createUserDealerTpPulldown(NSAL0020CMsg cMsg) {
//        try {
//            ZYPCodeDataUtil.createPulldownList(USR_DLR_TP.class, cMsg.usrDlrTpCd_01, cMsg.usrDlrTpNm);
//        } catch (IllegalArgumentException e) {
//            cMsg.setMessageInfo(NSAM0004E, new String[] {"USR_DLR_TP", "USR_DLR_TP_CD" });
//            return;
//        }
//    }
//
//
//    /**
//     * set Check Box
//     * @param cMsg NSAL0020CMsg
//     */
//    private void setCheckBox(NSAL0020CMsg cMsg) {
//
//        cMsg.xxChkBox_01.setValue(FLG_ON_Y);
//        cMsg.xxChkBox_02.setValue(FLG_ON_Y);
//        cMsg.xxChkBox_03.setValue(FLG_ON_Y);
//        cMsg.xxChkBox_04.setValue(FLG_ON_Y);
//        cMsg.xxChkBox_05.setValue(FLG_ON_Y);
//        cMsg.xxChkBox_06.setValue(FLG_ON_Y);
//    }
//
//    /**
//     * clear Search Result
//     * @param cMsg NSAL0020CMsg
//     */
//    private void clearSearchResult(NSAL0020CMsg bizMsg, NSAL0020SMsg globalMsg) {
//
//        bizMsg.A.clear();
//        bizMsg.A.setValidCount(0);
//        globalMsg.A.clear();
//        globalMsg.A.setValidCount(0);
//
//        bizMsg.xxPageShowFromNum.setValue(0);
//        bizMsg.xxPageShowToNum.setValue(0);
//        bizMsg.xxPageShowOfNum.setValue(0);
//    }
//
//    /**
//     * Create Machine Master search query parameter
//     * @param bizMsg NSAL0020CMsg
//     * @param limitCount int
//     * @return HashMap<String, Object> Machine Master search parameter
//     */
//    private HashMap<String, Object> setMachineMasterSearchParam(NSAL0020CMsg bizMsg, ArrayList<BigDecimal> svcMachMstrPkList, int limitCount) {
//
//        HashMap<String, Object> params = new HashMap<String, Object>();
//        List<String> svcMachTpList = new ArrayList<String>();
//        List<String> svcMachStsList = new ArrayList<String>();
//        if (hasValue(bizMsg.xxChkBox_01)) {
//            svcMachStsList.add(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
//        }
//        if (hasValue(bizMsg.xxChkBox_02)) {
//            svcMachStsList.add(SVC_MACH_MSTR_STS.INSTALLED);
//        }
//        if (hasValue(bizMsg.xxChkBox_03)) {
//            svcMachStsList.add(SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
//        }
//        if (hasValue(bizMsg.xxChkBox_04)) {
//            svcMachStsList.add(SVC_MACH_MSTR_STS.REMOVED);
//        }
//        if (hasValue(bizMsg.xxChkBox_05)) {
//            svcMachTpList.add(SVC_MACH_TP.MACHINE);
//        }
//        if (hasValue(bizMsg.xxChkBox_06)) {
//            svcMachTpList.add(SVC_MACH_TP.ACCESSORY);
//        }
//
//        params.put("bizMsg", bizMsg);
//        params.put("svcMachStsList", svcMachStsList);
//        params.put("svcMachTpList", svcMachTpList);
//        params.put("svcMachMstrPkList", svcMachMstrPkList);
//        params.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
//        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        if (ZYPCommonFunc.hasValue(bizMsg.firstOrgCd)
//                || ZYPCommonFunc.hasValue(bizMsg.scdOrgCd)
//                || ZYPCommonFunc.hasValue(bizMsg.thirdOrgCd)
//                || ZYPCommonFunc.hasValue(bizMsg.frthOrgCd)
//                || ZYPCommonFunc.hasValue(bizMsg.fifthOrgCd)
//                ) {
//            params.put("searchLayerFlg", ZYPConstant.FLG_ON_Y);
//            if (ZYPCommonFunc.hasValue(bizMsg.firstOrgCd)) {
//                params.put("layerNum", BigDecimal.ONE);
//            }
//            if (ZYPCommonFunc.hasValue(bizMsg.scdOrgCd)) {
//                params.put("layerNum", new BigDecimal("2"));
//            }
//            if (ZYPCommonFunc.hasValue(bizMsg.thirdOrgCd)) {
//                params.put("layerNum", new BigDecimal("3"));
//            }
//            if (ZYPCommonFunc.hasValue(bizMsg.frthOrgCd)) {
//                params.put("layerNum", new BigDecimal("4"));
//            }
//            if (ZYPCommonFunc.hasValue(bizMsg.fifthOrgCd)) {
//                params.put("layerNum", new BigDecimal("5"));
//            }
//        }
//        params.put("limitCount", limitCount);
//
//        return params;
//
//    }

//    /**
//     * Set Code Type Name
//     * @param bizMsg
//     */
//    private void setCdTpNm(NSAL0020CMsg bizMsg, NSAL0020SMsg globalMsg) {
//
//        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
//
//        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
//            setValue(globalMsg.A.no(i).mdseNm_A0, getMdseNm(glblCmpyCd, globalMsg.A.no(i).mdseCd_A0.getValue()));
//            setValue(globalMsg.A.no(i).locNm_SE, getSellToNm(glblCmpyCd, globalMsg.A.no(i).sellToCustCd_A0.getValue()));
//            setValue(globalMsg.A.no(i).svcMachTpNm_A0, getSvcMachTpNm(glblCmpyCd, globalMsg.A.no(i).svcMachTpCd_A0.getValue()));
//            setValue(globalMsg.A.no(i).svcMachMstrStsNm_A0, getMachMstrStsNm(glblCmpyCd, globalMsg.A.no(i).svcMachMstrStsCd_A0.getValue()));
//            setValue(globalMsg.A.no(i).usrDlrTpNm_A0, getUsrDlrTpNm(glblCmpyCd, globalMsg.A.no(i).usrDlrTpCd_A0.getValue()));
//            //setValue(globalMsg.A.no(i).orgNm_A0, getTocNm(glblCmpyCd, globalMsg.A.no(i).orgCd_A0.getValue()));
//        }
//    }

//    private String getSellToNm(String glblCmpyCd, String sellToCustCd) {
//
//        String sellToNm = null;
//
//        if (!hasValue(sellToCustCd)) {
//            return sellToNm;
//        }
//
//        SELL_TO_CUSTTMsg sellToCustInMsg = new SELL_TO_CUSTTMsg();
//        sellToCustInMsg.setSQLID("001");
//        sellToCustInMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        sellToCustInMsg.setConditionValue("sellToCustCd01", sellToCustCd);
//        sellToCustInMsg.setMaxCount(1);
//
//        SELL_TO_CUSTTMsgArray sellToCustArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(sellToCustInMsg);
//
//        if (sellToCustArray.length() != 0) {
//            SELL_TO_CUSTTMsg sellToCustTMsg = (SELL_TO_CUSTTMsg) sellToCustArray.get(0);
//            sellToNm = sellToCustTMsg.locNm.getValue();
//        }
//
//        return sellToNm;
//    }
//
//    private String getTocNm(String glblCmpyCd, String svcOrgCd) {
//
//        String tocNm = null;
//
//        if (!hasValue(svcOrgCd)) {
//            return tocNm;
//        }
//
//        TOCTMsg inMsg = new TOCTMsg();
//        inMsg.glblCmpyCd.setValue(glblCmpyCd);
//        inMsg.tocCd.setValue(svcOrgCd);
//
//        TOCTMsg outMsg = (TOCTMsg) S21CodeTableAccessor.findByKey(inMsg);
//
//        if (outMsg != null) {
//            tocNm = outMsg.tocNm.getValue();
//        }
//
//        return tocNm;
//    }
//
//    private String getMdseNm(String glblCmpyCd, String mdseCd) {
//
//        String mdseNm = null;
//
//        if (!hasValue(mdseCd)) {
//            return mdseNm;
//        }
//
//        MDSETMsg inMsg = new MDSETMsg();
//        inMsg.glblCmpyCd.setValue(glblCmpyCd);
//        inMsg.mdseCd.setValue(mdseCd);
//
//        MDSETMsg outMsg = (MDSETMsg) EZDFastTBLAccessor.findByKey(inMsg);
//
//        if (outMsg != null) {
//            mdseNm = outMsg.mdseNm.getValue();
//        }
//
//        return mdseNm;
//    }
//
//    private String getSvcMachTpNm(String glblCmpyCd, String svcMachTpCd) {
//
//        String svcMachTpNm = null;
//
//        if (!hasValue(svcMachTpCd)) {
//            return svcMachTpNm;
//        }
//
//        SVC_MACH_TPTMsg inMsg = new SVC_MACH_TPTMsg();
//
//        inMsg.glblCmpyCd.setValue(glblCmpyCd);
//        inMsg.svcMachTpCd.setValue(svcMachTpCd);
//
//        SVC_MACH_TPTMsg outMsg = (SVC_MACH_TPTMsg) S21CodeTableAccessor.findByKey(inMsg);
//
//        if (outMsg != null) {
//            svcMachTpNm = outMsg.svcMachTpNm.getValue();
//        }
//
//        return svcMachTpNm;
//    }
//
//    private String getMachMstrStsNm(String glblCmpyCd, String machMstrStsCd) {
//
//        String machMstrStsNm = null;
//
//        if (!hasValue(machMstrStsCd)) {
//            return machMstrStsNm;
//        }
//
//        SVC_MACH_MSTR_STSTMsg inMsg = new SVC_MACH_MSTR_STSTMsg();
//
//        inMsg.glblCmpyCd.setValue(glblCmpyCd);
//        inMsg.svcMachMstrStsCd.setValue(machMstrStsCd);
//
//        SVC_MACH_MSTR_STSTMsg outMsg = (SVC_MACH_MSTR_STSTMsg) S21CodeTableAccessor.findByKey(inMsg);
//
//        if (outMsg != null) {
//            machMstrStsNm = outMsg.svcMachMstrStsNm.getValue();
//        }
//
//        return machMstrStsNm;
//    }
//
//    private String getUsrDlrTpNm(String glblCmpyCd, String usrDirTpCd) {
//
//        String usrDlrTpNm = null;
//
//        if (!hasValue(usrDirTpCd)) {
//            return usrDlrTpNm;
//        }
//
//        USR_DLR_TPTMsg inMsg = new USR_DLR_TPTMsg();
//
//        inMsg.glblCmpyCd.setValue(glblCmpyCd);
//        inMsg.usrDlrTpCd.setValue(usrDirTpCd);
//
//        USR_DLR_TPTMsg outMsg = (USR_DLR_TPTMsg) S21CodeTableAccessor.findByKey(inMsg);
//
//        if (outMsg != null) {
//            usrDlrTpNm = outMsg.usrDlrTpNm.getValue();
//        }
//
//        return usrDlrTpNm;
//    }
//
//    // QC1830 Add Start
//    private void resetSMsg(NSAL0020SMsg globalMsg) {
//
//        NSAL0020SMsg sMsg = new NSAL0020SMsg();
//        NSAL0020_ASMsgArray sMsgArray = sMsg.A;
//        ZYPTableUtil.clear(sMsgArray);
//        int j = 0;
//
//        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
//            NSXC001001Contr dsxc001001Contr = new NSXC001001Contr();
//            ContrInfoBean contrInfoBean = dsxc001001Contr.getContrInfo(getGlobalCompanyCode(), globalMsg.A.no(i).svcConfigMstrPk_A0.getValue(), globalMsg.A.no(i).svcMachMstrPk_A0.getValue(), null);
//            if (contrInfoBean != null && hasValue(contrInfoBean.getContrNum())) {
//                EZDMsg.copy(globalMsg.A.no(i), null, sMsgArray.no(j), null);
//                j++;
//            }
//        }
//
//        ZYPTableUtil.clear(globalMsg.A);
//        EZDMsg.copy(sMsgArray, null, globalMsg.A, null);
//        globalMsg.A.setValidCount(j);
//    }
//    // QC1830 Add End

    // START 2016/10/28 T.Tomita [QC#15468, ADD]
    private String convertDateFormat(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }
    // END 2016/10/28 T.Tomita [QC#15468, ADD]

    // START 2018/04/13 K.Kojima [QC#25167,ADD]
    private static void outputSearchLog(NSAL0020CMsg cMsg) {
        StringBuffer sb = new StringBuffer();
        sb.append("NSAL0020 Search Condition - Search Filters : ");
        if (hasValue(cMsg.serNum)) {
            sb.append(" Serial#[").append(cMsg.serNum.getValue()).append("]");
        }
        if (hasValue(cMsg.xxScrItem30Txt_01)) {
            sb.append(" Solution#[").append(cMsg.xxScrItem30Txt_01.getValue()).append("]");
        }
        if (hasValue(cMsg.svcSlnNm)) {
            sb.append(" Solution Name[").append(cMsg.svcSlnNm.getValue()).append("]");
        }
        if (hasValue(cMsg.xxScrItem30Txt_02)) {
            sb.append(" Configuration#[").append(cMsg.xxScrItem30Txt_02.getValue()).append("]");
        }
        if (hasValue(cMsg.xxScrItem30Txt_03)) {
            sb.append(" Install Base ID#[").append(cMsg.xxScrItem30Txt_03.getValue()).append("]");
        }
        if (hasValue(cMsg.xxChkBox) && cMsg.xxChkBox.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            sb.append(" Show Terminated Products[").append(cMsg.xxChkBox.getValue()).append("]");
        }
        if (hasValue(cMsg.istlDt_FR) || hasValue(cMsg.istlDt_TO)) {
            sb.append(" Install Date[").append(cMsg.istlDt_FR.getValue()).append("-").append(cMsg.istlDt_TO.getValue()).append("]");
        }
        if (hasValue(cMsg.svcMachMstrStsCd_PS)) {
            sb.append(" Install Base Status[").append(cMsg.svcMachMstrStsCd_PS.getValue()).append("]");
        }
        if (hasValue(cMsg.xxScrItem30Txt_04)) {
            sb.append(" Software License#[").append(cMsg.xxScrItem30Txt_04.getValue()).append("]");
        }
        if (hasValue(cMsg.xxComnScrColValTxt_O)) {
            sb.append(" Account Owner Name[").append(cMsg.xxComnScrColValTxt_O.getValue()).append("]");
        }
        if (hasValue(cMsg.ownrAcctNum)) {
            sb.append(" Account Owner Account#[").append(cMsg.ownrAcctNum.getValue()).append("]");
        }
        if (hasValue(cMsg.xxComnScrColValTxt_B)) {
            sb.append(" Account Bill To Name/Address[").append(cMsg.xxComnScrColValTxt_B.getValue()).append("]");
        }
        if (hasValue(cMsg.billToAcctNum)) {
            sb.append(" Account Bill To Account#[").append(cMsg.billToAcctNum.getValue()).append("]");
        }
        if (hasValue(cMsg.indBillToLocNum)) {
            sb.append(" Account Bill To Address(Loc#)[").append(cMsg.indBillToLocNum.getValue()).append("]");
        }
        if (hasValue(cMsg.xxComnScrColValTxt_C)) {
            sb.append(" Current Location Name/Address[").append(cMsg.xxComnScrColValTxt_C.getValue()).append("]");
        }
        if (hasValue(cMsg.curLocAcctNum)) {
            sb.append(" Current Location Account#[").append(cMsg.curLocAcctNum.getValue()).append("]");
        }
        if (hasValue(cMsg.indCurLocNum)) {
            sb.append(" Current Location Address(Loc#)[").append(cMsg.indCurLocNum.getValue()).append("]");
        }
        S21InfoLogOutput.println(sb.toString());

        sb = new StringBuffer();
        sb.append("NSAL0020 Search Condition - Advanced Search : ");
        if (hasValue(cMsg.t_MdlNm)) {
            sb.append(" Service Model Name[").append(cMsg.t_MdlNm.getValue()).append("]");
        }
        if (hasValue(cMsg.mdseCd)) {
            sb.append(" Item#[").append(cMsg.mdseCd.getValue()).append("]");
        }
        if (hasValue(cMsg.mdseDescShortTxt)) {
            sb.append(" Item Description[").append(cMsg.mdseDescShortTxt.getValue()).append("]");
        }
        if (hasValue(cMsg.coaMdseTpCd_PS)) {
            sb.append(" Mdse Type[").append(cMsg.coaMdseTpCd_PS.getValue()).append("]");
        }
        if (hasValue(cMsg.cpoOrdNum)) {
            sb.append(" Order#[").append(cMsg.cpoOrdNum.getValue()).append("]");
        }
        if (hasValue(cMsg.custMachCtrlNum)) {
            sb.append(" IB External Ref#[").append(cMsg.custMachCtrlNum.getValue()).append("]");
        }
        if (hasValue(cMsg.custIssPoNum)) {
            sb.append(" End Customer PO#[").append(cMsg.custIssPoNum.getValue()).append("]");
        }
        if (hasValue(cMsg.dsContrNum)) {
            sb.append(" Contract#[").append(cMsg.dsContrNum.getValue()).append("]");
        }
        if (hasValue(cMsg.ctacPsnLastNm)) {
            sb.append(" Contact Last Name[").append(cMsg.ctacPsnLastNm.getValue()).append("]");
        }
        if (hasValue(cMsg.svcCtacTpCd_PS)) {
            sb.append(" IB Contact Type[").append(cMsg.svcCtacTpCd_PS.getValue()).append("]");
        }
        if (hasValue(cMsg.tocNm)) {
            sb.append(" Sales Rep Name[").append(cMsg.tocNm.getValue()).append("]");
        }
        S21InfoLogOutput.println(sb.toString());
    }
    // END 2018/04/13 K.Kojima [QC#25167,ADD]

}
