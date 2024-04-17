package business.blap.NSAL1140;

import static business.blap.NSAL1140.constant.NSAL1140Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL1140.common.NSAL1140CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Supply Watch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Hitachi         T.Nishimura     Create          N/A
 * 2016/03/25   Hitachi         A.Kohinata      Update          QC#6051
 * 2016/10/14   Hitachi         T.Tomita        Update          QC#15196
 * 2018/04/06   Hitachi         K.Yamada        Update          QC#25155
 *</pre>
 */
public class NSAL1140BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        super.preDoProcess(arg0, arg1);
        NSAL1140CMsg cMsg = (NSAL1140CMsg) arg0;
        NSAL1140SMsg sMsg = (NSAL1140SMsg) arg1;

        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1140_INIT".equals(screenAplID)) {
                doProcess_NSAL1140_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NSAL1140Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NSAL1140Scrn00_SelectAll(cMsg, sMsg);
            } else if ("NSAL1140Scrn00_UnselectAll".equals(screenAplID)) {
                doProcess_NSAL1140Scrn00_UnselectAll(cMsg, sMsg);
            } else if ("NSAL1140Scrn00_ClickCheckbox".equals(screenAplID)) {
                doProcess_NSAL1140Scrn00_ClickCheckbox(cMsg, sMsg);
            } else if ("NSAL1140Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NSAL1140_ChgSavedSrchOpt(cMsg, sMsg);
            } else if ("NSAL1140Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1140Scrn00_Search(cMsg, sMsg);
            } else if ("NSAL1140Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1140Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL1140Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1140Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL1140Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL1140Scrn00_TBLColumnSort(cMsg, sMsg);
            } else if ("NSAL1140Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL1140Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NSAL1140Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1140Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSAL1140_NSAL0540".equals(screenAplID)) {
                doProcess_NSAL1140_NSAL0540(cMsg, sMsg);
            }
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1140Scrn00_SelectAll(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).frzFlg_A.getValue())) {
                setValue(sMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
            }
        }
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1140CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);

        doProcess_NSAL1140Scrn00_ClickCheckbox(cMsg, sMsg);
    }

    private void doProcess_NSAL1140Scrn00_UnselectAll(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            sMsg.A.no(i).xxChkBox_A.clear();
        }
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1140CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);

        cMsg.svcSplyContrStsCd_1V.clear();
        cMsg.xxChkBox_F.clear();
        cMsg.B.clear();
    }

    private void doProcess_NSAL1140Scrn00_ClickCheckbox(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1140CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        cMsg.svcSplyContrStsCd_1V.clear();
        cMsg.xxChkBox_F.clear();
        cMsg.B.clear();

        Integer target = null;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                if (target != null) {
                    return;
                } else {
                    target = i;
                }
            }
        }
        if (target != null) {
            NSAL1140CommonLogic.getFotterData(sMsg.A.no(target), cMsg);
        }
    }

    private void doProcess_NSAL1140_ChgSavedSrchOpt(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_1V)) {
            NSAL1140CommonLogic.callNszc0330forSearchOption(cMsg, sMsg, getContextUserInfo().getUserId());
        }
    }

    private void doProcess_NSAL1140_INIT(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

        NSAL1140CommonLogic.createPullDown(cMsg, getContextUserInfo().getUserId());
        NSAL1140CommonLogic.clearMsgForInit(cMsg, sMsg);

        // add start 2018/04/06 QC#25155
        if (ZYPCommonFunc.hasValue(cMsg.dsContrNum)) {
            // add start 2016/02/24 CSA Defect#4122
            NSAL1140CommonLogic.getSearchData(cMsg, sMsg);
            NSAL1140CommonLogic.pagenation(cMsg, sMsg, 0);
            // add end 2016/02/24 CSA Defect#4122
        }
        // add end 2018/04/06 QC#25155
//        EZDMsg.copy(cMsg, null, sMsg, null);
    }

    private void doProcess_NSAL1140Scrn00_Search(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {

        if (NSAL1140CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }

        NSAL1140CommonLogic.clearMsgForSearch(cMsg, sMsg);
        NSAL1140CommonLogic.getSearchData(cMsg, sMsg);
        NSAL1140CommonLogic.pagenation(cMsg, sMsg, 0);
    }

    private void doProcess_NSAL1140Scrn00_PagePrev(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1140CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL1140CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1140Scrn00_PageNext(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1140CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL1140CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1140Scrn00_TBLColumnSort(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if (TABLE_A.equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Show 1st page
            setValue(cMsg.xxPageShowFromNum, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum, new BigDecimal(cMsg.A.getValidCount()));
        }
    }

    private void doProcess_NSAL1140Scrn00_CMN_Download(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {

        if (NSAL1140CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE_MAX);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NSAL1140Query.getInstance().getClass());

            // create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + CSV_FILE_NAME), CSV);

            Map<String, Object> ssMParam = NSAL1140CommonLogic.createSearchCondition(cMsg, DOWNLOAD_LIMIT_COUNT + 1);
            ps = ssmLLClient.createPreparedStatement("getSearchData", ssMParam, execParam);
            rs = ps.executeQuery();
            if (!rs.next()) {
                // START 2016/10/14 T.Tomita [QC#15196, MOD]
//                cMsg.setMessageInfo(NSAM0194I, null);
                cMsg.setMessageInfo(NSAM0013E);
                // END 2016/10/14 T.Tomita [QC#15196, MOD]
                return;
            }
            NSAL1140CommonLogic.writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private void doProcess_NSAL1140Scrn00_CMN_Clear(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {
        NSAL1140CommonLogic.clearMsgForInit(cMsg, sMsg);
        cMsg.procDt_01.clear();
        cMsg.procDt_02.clear();
        cMsg.dsContrNum.clear();
    }

    private void doProcess_NSAL1140_NSAL0540(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {
        doProcess_NSAL1140Scrn00_Search(cMsg, sMsg);
    }
}
