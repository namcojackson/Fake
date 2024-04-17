/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0450;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;

import business.blap.NSBL0450.common.NSBL0450CommonLogic;
import business.file.NSBL0450F00FMsg;
import static business.blap.NSBL0450.constant.NSBL0450Constant.*;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_SUPPL_TASK_TP;
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
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Supplemental Task
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/05   Hitachi         T.Iwamoto         Create          N/A
 * 2017/03/01   Hitachi         A.Kohinata        Update          QC#17608
 *</pre>
 */
public class NSBL0450BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSBL0450_INIT".equals(screenAplID)) {
                doProcess_NSBL0450_INIT((NSBL0450CMsg) cMsg, (NSBL0450SMsg) sMsg);
            } else if ("NSBL0450Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0450Scrn00_PagePrev((NSBL0450CMsg) cMsg, (NSBL0450SMsg) sMsg);
            } else if ("NSBL0450Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0450Scrn00_PageNext((NSBL0450CMsg) cMsg, (NSBL0450SMsg) sMsg);
            } else if ("NSBL0450Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSBL0450Scrn00_Clear((NSBL0450CMsg) cMsg, (NSBL0450SMsg) sMsg);
            } else if ("NSBL0450Scrn00_Search".equals(screenAplID)) {
                doProcess_NSBL0450Scrn00_Search((NSBL0450CMsg) cMsg, (NSBL0450SMsg) sMsg);
            } else if ("NSBL0450Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0450Scrn00_Submit((NSBL0450CMsg) cMsg, (NSBL0450SMsg) sMsg);
            } else if ("NSBL0450Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSBL0450Scrn00_TBLColumnSort((NSBL0450CMsg) cMsg, (NSBL0450SMsg) sMsg);
            } else if ("NSBL0450Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSBL0450Scrn00_Download((NSBL0450CMsg) cMsg, (NSBL0450SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSBL0450CMsg
     * @param sMsg NSBL0450SMsg
     */
    private void doProcess_NSBL0450_INIT(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg) {

        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        cMsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPCodeDataUtil.createPulldownList(SVC_SUPPL_TASK_TP.class, cMsg.svcSupplTaskTpCd_SC, cMsg.svcSupplTaskTpDescTxt_SC);
    }

    /**
     * do process (Page Prev)
     * @param cMsg NSBL0450CMsg
     * @param sMsg NSBL0450SMsg
     */
    private void doProcess_NSBL0450Scrn00_PagePrev(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg) {

        NSBL0450CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSBL0450CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    /**
     * do process (Page Next)
     * @param cMsg NSBL0450CMsg
     * @param sMsg NSBL0450SMsg
     */
    private void doProcess_NSBL0450Scrn00_PageNext(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg) {

        NSBL0450CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSBL0450CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    /**
     * do process (Clear)
     * @param cMsg NSBL0450CMsg
     * @param sMsg NSBL0450SMsg
     */
    private void doProcess_NSBL0450Scrn00_Clear(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg) {

        doProcess_NSBL0450_INIT(cMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
        }
    }

    /**
     * do process (Search)
     * @param cMsg NSBL0450CMsg
     * @param sMsg NSBL0450SMsg
     */
    private void doProcess_NSBL0450Scrn00_Search(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

        // get Detail Data and Set SMsg
        getSearchData(cMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
        }

    }

    /**
     * do process (Submit)
     * @param cMsg NSBL0450CMsg
     * @param sMsg NSBL0450SMsg
     */
    private void doProcess_NSBL0450Scrn00_Submit(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

        // get Detail Data and Set SMsg
        getSearchData(cMsg, sMsg);

    }
    /**
     * do process (download)
     * @param cMsg NSBL0450CMsg
     * @param sMsg NSBL0450SMsg
     */
    private void doProcess_NSBL0450Scrn00_TBLColumnSort(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg) {

        NSBL0450CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        }
    }
    /**
     * do process (download)
     * @param cMsg NSBL0450CMsg
     * @param sMsg NSBL0450SMsg
     */
    private void doProcess_NSBL0450Scrn00_Download(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSBL0450Query dsbl0450Query = NSBL0450Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(dsbl0450Query.getClass());

            // create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
            Map<String, Object> params = setDownloadPram(cMsg, sMsg);
            stmtSelect = ssmLLClient.createPreparedStatement("getMainData", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(NZZM0000E, null);
                return;
            }
            NSBL0450F00FMsg fMsg = new NSBL0450F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
            writeCsvFileForHeaderTab(cMsg, sMsg, rs, fMsg, csvOutFile);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * get Search Data List
     * @param cMsg NSBL0450CMsg
     * @return Data List
     */
    private void getSearchData(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg) {

        sMsg.clear();
        S21SsmEZDResult ssmResult = NSBL0450Query.getInstance().getSearchData(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            // add start 2017/03/01 CSA Defect#17608
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_S.getValue())) {
                convertTimeSearchData(sMsg.A);
            }
            // add end 2017/03/01 CSA Defect#17608

            // Result > 2000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            // Copy one page from SAMsg to CMsg
            if (cMsg.A.length() > sMsg.A.getValidCount()) {
                cMsg.A.setValidCount(sMsg.A.getValidCount());
            } else {
                cMsg.A.setValidCount(cMsg.A.length());
            }
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            // set Paging Data
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        } else {
            // No result
            cMsg.setMessageInfo(NZZM0000E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * Write csv file
     * @param bizMsg NSBL0450CMsg
     * @param globalMsg NSBL0450SMsg
     * @param rs ResultSet
     * @param fMsg NSBL0450F00FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @throws SQLException
     */
    private void writeCsvFileForHeaderTab(NSBL0450CMsg bizMsg, NSBL0450SMsg globalMsg, ResultSet rs, NSBL0450F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        rs.last();
        if (rs.getRow() > LIMIT_DOWNLOAD) {
            bizMsg.setMessageInfo(NZZM0001W);
        }
        rs.beforeFirst();

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, bizMsg);

        // write contents
        while (rs.next()) {
            if (rs.getRow() > LIMIT_DOWNLOAD) {
                break;
            }

            // resultset -> fMsg
            setValue(fMsg.svcSupplTaskNum_A, rs.getString("SVC_SUPPL_TASK_NUM"));
            setValue(fMsg.svcSupplTaskTpDescTxt_A, rs.getString("SVC_SUPPL_TASK_TP_DESC_TXT"));
            setValue(fMsg.techPsnNm_A, rs.getString("TECH_PSN_NM"));
            setValue(fMsg.mgrNm_A, rs.getString("MGR_NM"));
            setValue(fMsg.xxCmntTxt_A, rs.getString("XX_CMNT_TXT"));
            setValue(fMsg.xxDtTm_FR, rs.getString("SVC_SUPPL_FROM_DT_DL"));
            setValue(fMsg.xxDtTm_A1, rs.getString("SVC_SUPPL_FROM_TM"));
            setValue(fMsg.xxDtTm_TO, rs.getString("SVC_SUPPL_TO_DT_DL"));
            setValue(fMsg.xxDtTm_A2, rs.getString("SVC_SUPPL_TO_TM"));
            setValue(fMsg.xxDtTm_A3, rs.getString("SVC_TRVL_TM_NUM"));
            setValue(fMsg.xxDtTm_A4, rs.getString("SVC_SUPPL_TM_NUM"));
            setValue(fMsg.xxDtTm_A5, rs.getString("DURN_TM_NUM"));
            setValue(fMsg.svcTaskNum_A, rs.getString("SVC_TASK_NUM"));

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSBL0450F00FMsg fMsg, NSBL0450CMsg cMsg) {
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        final String[] csvHeader = new String[] {labelConv.convLabel2i18nLabel(SCRN_ID, "Supplemental Task#"), labelConv.convLabel2i18nLabel(SCRN_ID, "Task Name"), labelConv.convLabel2i18nLabel(SCRN_ID, "Technician Name"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "Resource Manager"), labelConv.convLabel2i18nLabel(SCRN_ID, "Category"), labelConv.convLabel2i18nLabel(SCRN_ID, "Start Date"), labelConv.convLabel2i18nLabel(SCRN_ID, "Start Time"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "End Date"), labelConv.convLabel2i18nLabel(SCRN_ID, "End Time"), labelConv.convLabel2i18nLabel(SCRN_ID, "Travel Time"), labelConv.convLabel2i18nLabel(SCRN_ID, "Supplemental Time"),
                labelConv.convLabel2i18nLabel(SCRN_ID, "Duration"), labelConv.convLabel2i18nLabel(SCRN_ID, "Sourse Task#") };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));
    }

    /**
     * setDownloadPram
     * @param cMsg NSBL0450CMsg
     * @param sMsg NSBL0450SMsg
     * @return S21SsmEZDResult
     */
    private Map<String, Object> setDownloadPram(NSBL0450CMsg cMsg, NSBL0450SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("categoryY", CATEGORY_Y);
        params.put("categoryN", CATEGORY_N);
        params.put("maxMinute", MAX_MINUTE);
        params.put("minute", MINUTE);
        params.put("maxTime", MAX_TIME);
        params.put("timeFormatPre", TIME_FORMAT_PRE);
        params.put("timeFormat", TIME_FORMAT);
        params.put("pad00", PAD00);
        params.put("dtFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        params.put("dlFormat", DL_FORMAT);
        if (ZYPCommonFunc.hasValue(cMsg.svcSupplTaskNum_S)) {
            params.put("svcSupplTaskNum", (String) cMsg.svcSupplTaskNum_S.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.svcSupplTaskTpCd_SS)) {
            params.put("svcSupplTaskTpCd", (String) cMsg.svcSupplTaskTpCd_SS.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.cratDt_S)) {
            params.put("cratDt", (String) cMsg.cratDt_S.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.techPsnNm_S)) {
            params.put("techPsnNm", (String) cMsg.techPsnNm_S.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.mgrNm_S)) {
            params.put("mgrNm", (String) cMsg.mgrNm_S.getValue());
        }
        params.put("limitNum", LIMIT_DOWNLOAD);

        return params;
    }

    // add start 2017/03/01 CSA Defect#17608
    private void convertTimeSearchData(NSBL0450_ASMsgArray asMsgArray) {
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            NSBL0450_ASMsg asMsg = (NSBL0450_ASMsg) asMsgArray.no(i);

            String[] convertTime = NSBL0450CommonLogic.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, asMsg.svcSupplFromDt_A.getValue(), asMsg.xxDtTm_W1.getValue(), asMsg.techCd_A.getValue());
            if (convertTime != null) {
                setValue(asMsg.svcSupplFromDt_A, convertTime[0]);
                setValue(asMsg.xxDtTm_A1, NSBL0450CommonLogic.formatTime(convertTime[1], LENGTH_4));
            } else {
                asMsg.svcSupplFromDt_A.clear();
                asMsg.xxDtTm_A1.clear();
            }

            convertTime = NSBL0450CommonLogic.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, asMsg.svcSupplToDt_A.getValue(), asMsg.xxDtTm_W2.getValue(), asMsg.techCd_A.getValue());
            if (convertTime != null) {
                setValue(asMsg.svcSupplToDt_A, convertTime[0]);
                setValue(asMsg.xxDtTm_A2, NSBL0450CommonLogic.formatTime(convertTime[1], LENGTH_4));
            } else {
                asMsg.svcSupplToDt_A.clear();
                asMsg.xxDtTm_A2.clear();
            }

            convertTime = NSBL0450CommonLogic.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, asMsg.cratDt_A.getValue(), asMsg.xxDtTm_W6.getValue(), asMsg.techCd_A.getValue());
            if (convertTime != null) {
                setValue(asMsg.cratDt_A, convertTime[0]);
                setValue(asMsg.xxDtTm_A6, NSBL0450CommonLogic.formatTime(convertTime[1], LENGTH_6));
            } else {
                asMsg.cratDt_A.clear();
                asMsg.xxDtTm_A2.clear();
            }

            convertTime = NSBL0450CommonLogic.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, asMsg.updDt_A.getValue(), asMsg.xxDtTm_W7.getValue(), asMsg.techCd_A.getValue());
            if (convertTime != null) {
                setValue(asMsg.updDt_A, convertTime[0]);
                setValue(asMsg.xxDtTm_A7, NSBL0450CommonLogic.formatTime(convertTime[1], LENGTH_6));
            } else {
                asMsg.updDt_A.clear();
                asMsg.xxDtTm_A7.clear();
            }
        }
    }
    // add end 2017/03/01 CSA Defect#17608
}
