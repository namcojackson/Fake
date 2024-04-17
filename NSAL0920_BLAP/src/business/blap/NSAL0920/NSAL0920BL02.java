/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0920;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static business.blap.NSAL0920.constant.NSAL0920Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0920.common.NSAL0920CommonLogic;
import business.db.BILL_TO_CUSTTMsg;
import business.db.S21_PSNTMsg;
import business.db.SVC_CONTR_BRTMsg;
import business.file.NSAL0920F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Contract Billing Search
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         K.Kasai         Create          N/A
 * 2016/01/06   Hitachi         T.Tomita        Update          QC#1029
 * 2016/04/13   Hitachi         T.Kanasaka      Update          QC#6657
 * 2016/08/01   Hitachi         K.Yamada        Update          CSA QC#12504
 * 2016/08/09   Hitachi         T.Tomita        Update          QC#13149
 * 2016/10/18   Hitachi         N.Arai          Update          QC#15216
 * 2017/01/23   Hitachi         K.Kitachi       Update          QC#17219
 * 2018/02/08   Hitachi         K.Kojima        Update          QC#20793
 * 2018/07/04   Hitachi         K.Kitachi       Update          QC#26891
 *</pre>
 */
public class NSAL0920BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplId = cMsg.getScreenAplID();

            if ("NSAL0920_INIT".equals(screenAplId)) {
                doProcess_NSAL0920_INIT((NSAL0920CMsg) cMsg, (NSAL0920SMsg) sMsg);
                ZYPGUITableColumn.getColData((NSAL0920CMsg) cMsg, (NSAL0920SMsg) sMsg);
                // events of NSAL0920Scrn00.
            } else if (screenAplId.startsWith("NSAL0920Scrn00")) {

                if (screenAplId.endsWith("_Search")) {
                    doProcess_NSAL0920_Search((NSAL0920CMsg) cMsg, (NSAL0920SMsg) sMsg);

                } else if (screenAplId.endsWith("CMN_Download")) {
                    doProcess_NSAL0920_CMN_Download((NSAL0920CMsg) cMsg, (NSAL0920SMsg) sMsg);

                } else if (screenAplId.endsWith("_TBLColumnSort")) {
                    doProcess_NSAL0920Scrn00_TBLColumnSort((NSAL0920CMsg) cMsg, (NSAL0920SMsg) sMsg);

                } else if (screenAplId.endsWith("_CMN_Clear")) {
                    doProcess_NSAL0920_CMN_Clear((NSAL0920CMsg) cMsg, (NSAL0920SMsg) sMsg);
                    ZYPGUITableColumn.getColData((NSAL0920CMsg) cMsg, (NSAL0920SMsg) sMsg);

                } else if (screenAplId.endsWith("_PagePrev")) {
                    doProcess_NSAL0920_PagePrev((NSAL0920CMsg) cMsg, (NSAL0920SMsg) sMsg);

                } else if (screenAplId.endsWith("_PageNext")) {
                    doProcess_NSAL0920_PageNext((NSAL0920CMsg) cMsg, (NSAL0920SMsg) sMsg);

                } else if (screenAplId.endsWith("_PageJump")) {
                    doProcess_NSAL0920Scrn00_PageJump((NSAL0920CMsg) cMsg, (NSAL0920SMsg) sMsg);

                }
            // START 2016/01/06 T.Tomita [QC#1029, ADD]
            } else if ("NSAL0920_NMAL6760".equals(screenAplId)) {
                doProcess_NSAL0920_NMAL6760((NSAL0920CMsg) cMsg, (NSAL0920SMsg) sMsg);
            // END 2016/01/06 T.Tomita [QC#1029, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NSAL0920_INIT
     * @param cMsg NSAL0920CMsg
     * @param sMsg NSAL0920SMsg
     */
    private void doProcess_NSAL0920_INIT(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg) {

        init(cMsg, sMsg);

        // START 2018/07/04 K.Kitachi [QC#26891, ADD]
        if (isSearch(cMsg)) {
            doProcess_NSAL0920_Search(cMsg, sMsg);
        }
        // END 2018/07/04 K.Kitachi [QC#26891, ADD]

    }

    private void doProcess_NSAL0920_CMN_Download(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSAL0920Query query = NSAL0920Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE_MAX);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());

            // create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");

            stmtSelect = ssmLLClient.createPreparedStatement("getSearchData", NSAL0920Query.getSsmParam(cMsg, sMsg, DOWNLOAD), execParam);
            rs = stmtSelect.executeQuery();
            NSAL0920F00FMsg fMsg = new NSAL0920F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
            fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
            writeCsvFile(cMsg, rs, fMsg, csvOutFile);
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

    }

    // START 04/13/2016 T.Kanasaka [QC#6657, MOD]
    private void writeCsvFile(NSAL0920CMsg cMsg, ResultSet rs, NSAL0920F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        // contacts
        final String[] csvHeader = new String[] {"Interface Date", "Branch", "Contract#", "Type", "Status", "Machine", "Located Party", "Counter Name", "Period Start", "Period End", "Allowance", "Rate", "Start Read", "End Read", "Amount",
                "Notification" };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        BigDecimal preSvcContrBllgPk = BigDecimal.ZERO;
        BigDecimal svcContrBllgPk = null;
        // add start 2016/08/09 CSA Defect#13149
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String dsAcctNum = null;
        String billToCustCd = null;
        String[] defCurLoc = null;
        Map<String, String[]> defCurLocMap = new HashMap<String, String[]>();
        // add end 2016/08/09 CSA Defect#13149
        // write contents
        while (rs.next()) {
            if (rs.getRow() > DOWNLOAD_LIMIT_CNT) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }

            svcContrBllgPk = rs.getBigDecimal("SVC_CONTR_BLLG_PK");

            if (hasValue(svcContrBllgPk) && preSvcContrBllgPk.compareTo(svcContrBllgPk) == 0) {
                fMsg.xxDtTxt_A1.clear();
                fMsg.svcContrBrDescTxt_A.clear();
                fMsg.dsContrNum_A.clear();
                fMsg.dsContrCatgDescTxt_A.clear();
                fMsg.dsContrCtrlStsNm_A.clear();
                fMsg.serNum_A.clear();
                fMsg.locNm_A.clear();
                fMsg.mtrLbDescTxt_A.clear();
                fMsg.xxDtTxt_A2.clear();
                fMsg.xxDtTxt_A3.clear();
                fMsg.readMtrCnt_AS.clear();
                fMsg.readMtrCnt_AE.clear();
                fMsg.mtrChrgDealAmt_A.clear();
                fMsg.xxYesNoNm_A.clear();
            } else {
                // resultset -> fMsg
                setValue(fMsg.xxDtTxt_A1, convertDateFormat(rs.getString("OVRD_NEXT_BLLG_DT")));
                setValue(fMsg.svcContrBrDescTxt_A, rs.getString("SVC_CONTR_BR_DESC_TXT"));
                setValue(fMsg.dsContrNum_A, rs.getString("DS_CONTR_NUM"));
                setValue(fMsg.dsContrCatgDescTxt_A, rs.getString("DS_CONTR_CATG_DESC_TXT"));
                setValue(fMsg.dsContrCtrlStsNm_A, rs.getString("DS_CONTR_CTRL_STS_NM"));
                setValue(fMsg.serNum_A, rs.getString("SER_NUM"));
                // mod start 2016/08/09 CSA Defect#13149
                // START 2018/02/08 K.Kojima [QC#20793,MOD]
                // dsAcctNum = rs.getString("DS_ACCT_NUM");
                // billToCustCd = rs.getString("BILL_TO_CUST_CD");
                // if (hasValue(dsAcctNum) && hasValue(billToCustCd)) {
                //     defCurLoc = NSAL0920CommonLogic.getDefCurLoc(defCurLocMap, glblCmpyCd, dsAcctNum, billToCustCd);
                //     setValue(fMsg.locNm_A, defCurLoc[1]);
                // } else {
                //     fMsg.locNm_A.clear();
                // }
                setValue(fMsg.locNm_A, rs.getString("LOC_NM"));
                // END 2018/02/08 K.Kojima [QC#20793,MOD]
                // mod end 2016/08/09 CSA Defect#13149
                setValue(fMsg.mtrLbDescTxt_A, rs.getString("MTR_LB_DESC_TXT"));
                setValue(fMsg.xxDtTxt_A2, convertDateFormat(rs.getString("MTR_BLLG_FROM_DT")));
                setValue(fMsg.xxDtTxt_A3, convertDateFormat(rs.getString("MTR_BLLG_THRU_DT")));
                setValue(fMsg.readMtrCnt_AS, rs.getBigDecimal("START_READ_MTR_CNT"));
                setValue(fMsg.readMtrCnt_AE, rs.getBigDecimal("END_READ_MTR_CNT"));
                setValue(fMsg.mtrChrgDealAmt_A, rs.getBigDecimal("MTR_CHRG_DEAL_AMT"));
                setValue(fMsg.xxYesNoNm_A, rs.getString("YES_NO_NM"));
            }
            setValue(fMsg.xsMtrFromCopyQty_A, rs.getBigDecimal("XS_MTR_FROM_COPY_QTY"));
            setValue(fMsg.xsMtrAmtRate_A, rs.getBigDecimal("XS_MTR_AMT_RATE"));

            preSvcContrBllgPk = svcContrBllgPk;
            csvOutFile.write();
        }

        csvOutFile.close();
    }
    // END 04/13/2016 T.Kanasaka [QC#6657, MOD]

    private String convertDateFormat(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }

    private void doProcess_NSAL0920_CMN_Clear(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg) {

        // START 2018/07/04 K.Kitachi [QC#26891, MOD]
//        init(cMsg, sMsg);
        clear(cMsg, sMsg);
        // END 2018/07/04 K.Kitachi [QC#26891, MOD]

    }

    private void doProcess_NSAL0920Scrn00_TBLColumnSort(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg) {

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

            // （SMsg -> CMsg）
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

    private void doProcess_NSAL0920_Search(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg) {

        // START 2017/01/23 K.Kitachi [QC#17219, ADD]
        setSvcContrBrDescText(cMsg);
        setXxAllPsnNm(cMsg);
        // END 2017/01/23 K.Kitachi [QC#17219, ADD]

        NSAL0920CommonLogic.getSearchData(cMsg, sMsg);

        NSAL0920CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum);
        NSAL0920CommonLogic.preSetToPageOne(cMsg.xxPageShowCurNum);
        NSAL0920CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSAL0920CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowTotNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0920_PageNext(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg) {

        if (!hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }

        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValue().add(BigDecimal.ONE));
        NSAL0920CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0920CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowTotNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0920_PagePrev(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg) {

        if (!hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }

        NSAL0920CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0920CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowTotNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());
    }

    private void init(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg) {
        // START 2018/07/04 K.Kitachi [QC#26891, DEL]
//        cMsg.dsContrNum.clear();
//        cMsg.dsAcctNum.clear();
//        cMsg.dsAcctNm.clear();
//        cMsg.locNum.clear();
//        cMsg.locNm.clear();
//        cMsg.svcRgPk_H3.clear();
//// START 2016/10/18 N.Arai [QC#15216, MOD]
//        cMsg.slsDt.clear();
//        cMsg.svcContrBrDescTxt_H2.clear();
//// END 2016/10/18 N.Arai [QC#15216, MOD]
//        cMsg.svcContrBrCd_H3.clear();
//        cMsg.psnCd_H3.clear();
//        // START 2017/01/23 K.Kitachi [QC#17219, ADD]
//        cMsg.xxAllPsnNm_H2.clear();
//        // END 2017/01/23 K.Kitachi [QC#17219, ADD]
//        cMsg.xxFromDt.clear();
//        cMsg.xxThruDt.clear();
//        // mod start 2016/08/01 CSA Defect#12504
//        cMsg.xxChkBox_HY.clear();
//        cMsg.xxChkBox_HN.clear();
//        // mod end 2016/08/01 CSA Defect#12504
//        cMsg.xxPageShowFromNum.clear();
//        cMsg.xxPageShowToNum.clear();
//        cMsg.xxPageShowOfNum.clear();
//        cMsg.xxPageShowCurNum.clear();
//        cMsg.xxPageShowTotNum.clear();
//        ZYPTableUtil.clear(cMsg.A);
//        ZYPTableUtil.clear(sMsg.A);
        // END 2018/07/04 K.Kitachi [QC#26891, DEL]

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        // START 2017/01/23 K.Kitachi [QC#17219, ADD]
        setValue(cMsg.svcOrgFuncRoleTpCd_VC, ZYPCodeDataUtil.getVarCharConstValue(SVC_ORG_FUNC_ROLE_TP_SUPERVR, cMsg.glblCmpyCd.getValue()));
        // END 2017/01/23 K.Kitachi [QC#17219, ADD]
        NSAL0920CommonLogic.createPullDown(cMsg);
        EZDMsg.copy(cMsg, null, sMsg, null);
    }

    private void doProcess_NSAL0920Scrn00_PageJump(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    // START 2016/01/06 T.Tomita [QC#1029, ADD]
    private void doProcess_NSAL0920_NMAL6760(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg) {
        if (!hasValue((cMsg.locNum))) {
            return;
        }
        NSAL0920Query query = NSAL0920Query.getInstance();
        BILL_TO_CUSTTMsg tMsg = query.getBillToCust(getGlobalCompanyCode(), cMsg.locNum.getValue());
        if (tMsg == null) {
            return;
        }
        setValue(cMsg.locNm, tMsg.locNm);
    }
    // END 2016/01/06 T.Tomita [QC#1029, ADD]

    // START 2017/01/23 K.Kitachi [QC#17219, ADD]
    private void setSvcContrBrDescText(NSAL0920CMsg cMsg) {
        cMsg.svcContrBrDescTxt_H2.clear();
        NSAL0920Query query = NSAL0920Query.getInstance();
        SVC_CONTR_BRTMsg tMsg = query.getSvcContrBr(cMsg.glblCmpyCd.getValue(), cMsg.svcContrBrCd_H3.getValue());
        if (tMsg == null) {
            return;
        }
        setValue(cMsg.svcContrBrDescTxt_H2, tMsg.svcContrBrDescTxt);
    }

    private void setXxAllPsnNm(NSAL0920CMsg cMsg) {
        cMsg.xxAllPsnNm_H2.clear();
        NSAL0920Query query = NSAL0920Query.getInstance();
        S21_PSNTMsg tMsg = query.getS21Psn(cMsg.glblCmpyCd.getValue(), cMsg.psnCd_H3.getValue());
        if (tMsg == null) {
            return;
        }
        String xxAllPsnNm = tMsg.psnFirstNm.getValue() + " " + tMsg.psnLastNm.getValue();
        setValue(cMsg.xxAllPsnNm_H2, xxAllPsnNm);
    }
    // END 2017/01/23 K.Kitachi [QC#17219, ADD]

    // START 2018/07/04 K.Kitachi [QC#26891, ADD]
    private boolean isSearch(NSAL0920CMsg cMsg) {
        if (hasValue(cMsg.dsContrNum)) {
            return true;
        }
        if (hasValue(cMsg.xxFromDt)) {
            return true;
        }
        if (hasValue(cMsg.xxThruDt)) {
            return true;
        }
        if (hasValue(cMsg.xxChkBox_HY)) {
            return true;
        }
        return false;
    }

    private void clear(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg) {
        cMsg.dsContrNum.clear();
        cMsg.dsAcctNum.clear();
        cMsg.dsAcctNm.clear();
        cMsg.locNum.clear();
        cMsg.locNm.clear();
        cMsg.svcRgPk_H3.clear();
        cMsg.slsDt.clear();
        cMsg.svcContrBrDescTxt_H2.clear();
        cMsg.svcContrBrCd_H3.clear();
        cMsg.psnCd_H3.clear();
        cMsg.xxAllPsnNm_H2.clear();
        cMsg.xxFromDt.clear();
        cMsg.xxThruDt.clear();
        cMsg.xxChkBox_HY.clear();
        cMsg.xxChkBox_HN.clear();
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
        cMsg.xxPageShowCurNum.clear();
        cMsg.xxPageShowTotNum.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
    }
    // END 2018/07/04 K.Kitachi [QC#26891, ADD]
}
