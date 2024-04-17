/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.ZZPL0030;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import business.blap.ZZPL0030.constant.ZZPL0030Constant;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.userprofile.reportprofile.S21ArchivedReportInfo;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/23   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0030BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (ZZPL0030Constant.EVENT_INIT.equals(screenAplID)) {
                doProcess_ZZPL0030_INIT((ZZPL0030CMsg) cMsg, (ZZPL0030SMsg) sMsg);
            } else if (ZZPL0030Constant.EVENT_CMN_CLEAR_SCRN00.equals(screenAplID)) {
                doProcess_ZZPL0030Scrn00_CMN_Clear((ZZPL0030CMsg) cMsg, (ZZPL0030SMsg) sMsg);
            } else if (ZZPL0030Constant.EVENT_OPEN_RPT_NM_ROW.equals(screenAplID)) {
                doProcess_ZZPL0030_OpenRptNm_Row((ZZPL0030CMsg) cMsg, (ZZPL0030SMsg) sMsg);
            } else if (ZZPL0030Constant.EVENT_PAGE_NEXT_SCRN00.equals(screenAplID)) {
                doProcess_ZZPL0030Scrn00_PageNext((ZZPL0030CMsg) cMsg, (ZZPL0030SMsg) sMsg);
            } else if (ZZPL0030Constant.EVENT_PAGE_PREV_SCRN00.equals(screenAplID)) {
                doProcess_ZZPL0030Scrn00_PagePrev((ZZPL0030CMsg) cMsg, (ZZPL0030SMsg) sMsg);
            } else if (ZZPL0030Constant.EVENT_SEARCH_SCRN00.equals(screenAplID)) {
                doProcess_ZZPL0030Scrn00_Search((ZZPL0030CMsg) cMsg, (ZZPL0030SMsg) sMsg);
            } else if (ZZPL0030Constant.EVENT_TBL_COLUMN_SORT_SCRN00.equals(screenAplID)) {
                doProcess_ZZPL0030Scrn00_TBLColumnSort((ZZPL0030CMsg) cMsg, (ZZPL0030SMsg) sMsg);
            } else if (ZZPL0030Constant.EVENT_CMN_CLEAR_SCRN01.equals(screenAplID)) {
                doProcess_ZZPL0030Scrn01_CMN_Clear((ZZPL0030CMsg) cMsg, (ZZPL0030SMsg) sMsg);
            } else if (ZZPL0030Constant.EVENT_OPEN_RPT_TTL_ROW.equals(screenAplID)) {
                doProcess_ZZPL0030_OpenRptTtl((ZZPL0030CMsg) cMsg, (ZZPL0030SMsg) sMsg);
            } else if (ZZPL0030Constant.EVENT_PAGE_NEXT_SCRN01.equals(screenAplID)) {
                doProcess_ZZPL0030Scrn01_PageNext((ZZPL0030CMsg) cMsg, (ZZPL0030SMsg) sMsg);
            } else if (ZZPL0030Constant.EVENT_PAGE_PREV_SCRN01.equals(screenAplID)) {
                doProcess_ZZPL0030Scrn01_PagePrev((ZZPL0030CMsg) cMsg, (ZZPL0030SMsg) sMsg);
            } else if (ZZPL0030Constant.EVENT_SEARCH_SCRN01.equals(screenAplID)) {
                doProcess_ZZPL0030Scrn01_Search((ZZPL0030CMsg) cMsg, (ZZPL0030SMsg) sMsg);
            } else if (ZZPL0030Constant.EVENT_TBL_COLUMN_SORT_SCRN01.equals(screenAplID)) {
                doProcess_ZZPL0030Scrn01_TBLColumnSort((ZZPL0030CMsg) cMsg, (ZZPL0030SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_ZZPL0030_INIT(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        searchRptList(cMsg, sMsg);
    }

    private void doProcess_ZZPL0030Scrn00_CMN_Clear(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        doProcess_ZZPL0030_INIT(cMsg, sMsg);
    }

    private void doProcess_ZZPL0030_OpenRptNm_Row(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        searchRptParam(cMsg);

        // set creation time(from) to 2 years ago
        SimpleDateFormat creationDtFormat = new SimpleDateFormat(ZZPL0030Constant.DATE_FORMAT);
        Date currentDate;
        try {
            currentDate = creationDtFormat.parse(cMsg.getOnlOperationDate());
            Calendar creationDtFrom = Calendar.getInstance();
            creationDtFrom.setTime(currentDate);
            creationDtFrom.add(Calendar.YEAR, -2);
            cMsg.xxCratDt_FR.setValue(creationDtFormat.format(creationDtFrom.getTime()));
        } catch (ParseException e) {
            throw new S21AbendException("ZZPM0045E", new String[] {e.getMessage() });
        }

        searchRptHistList(cMsg, sMsg);
    }

    private void doProcess_ZZPL0030Scrn00_PageNext(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_0.getValueInt();
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
        cMsg.xxPageShowFromNum_0.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_0.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    private void doProcess_ZZPL0030Scrn00_PagePrev(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_0.getValueInt();
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
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum_0.setValue(pagenationFrom);
        cMsg.xxPageShowToNum_0.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    private void doProcess_ZZPL0030Scrn00_Search(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        searchRptList(cMsg, sMsg);
    }

    private void doProcess_ZZPL0030Scrn00_TBLColumnSort(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        String sortTblNm = cMsg.xxSortTblNm_0.getValue();
        String sortItemNm = cMsg.xxSortItemNm_0.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt_0.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // copy data from SMsg onto CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // set value to pagenation items
            cMsg.xxPageShowFromNum_0.setValue(1);
            cMsg.xxPageShowToNum_0.setValue(cMsg.A.getValidCount());
        }
    }

    private void doProcess_ZZPL0030Scrn01_CMN_Clear(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        doProcess_ZZPL0030_OpenRptNm_Row(cMsg, sMsg);
    }

    private void doProcess_ZZPL0030_OpenRptTtl(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        long fileId = 0;
        byte[] report = null;

        S21EIPPrintingService eipService = new S21EIPPrintingService();
        if (cMsg.B.no(cMsg.xxCellIdx.getValueInt()).xxRptFileId != null) {
            fileId = cMsg.B.no(cMsg.xxCellIdx.getValueInt()).xxRptFileId.getValueInt();
            report = eipService.downloadReport(fileId);

            if (report != null) {
                cMsg.xxFileData.setTempFilePath(null, String.valueOf(fileId), ZZPL0030Constant.EXTENSION_PDF);
                ZYPFileWriter.writeFile(cMsg.xxFileData.getTempFilePath(), report);
            } else {
                cMsg.setMessageInfo("ZZPM0050E", new String[] {String.valueOf(fileId) });
            }
        }
    }

    private void doProcess_ZZPL0030Scrn01_PageNext(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_1.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.B.length(); i++) {
            if (i < sMsg.B.getValidCount()) {
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.B.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum_1.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_1.setValue(pagenationFrom + cMsg.B.getValidCount());
    }

    private void doProcess_ZZPL0030Scrn01_PagePrev(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_1.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.B.length(); i++) {
            if (i < sMsg.B.getValidCount()) {
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.B.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum_1.setValue(pagenationFrom);
        cMsg.xxPageShowToNum_1.setValue(pagenationFrom + cMsg.B.getValidCount() - 1);
    }

    private void doProcess_ZZPL0030Scrn01_Search(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        searchRptHistList(cMsg, sMsg);
    }

    private void doProcess_ZZPL0030Scrn01_TBLColumnSort(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        String sortTblNm = cMsg.xxSortTblNm_1.getValue();
        String sortItemNm = cMsg.xxSortItemNm_1.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt_1.getValue();

        // Table:B
        if ("B".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.B, sMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.B.getValidCount());

            // copy data from SMsg onto CMsg
            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }
            cMsg.B.setValidCount(i);

            // set value to pagenation items
            cMsg.xxPageShowFromNum_1.setValue(1);
            cMsg.xxPageShowToNum_1.setValue(cMsg.B.getValidCount());
        }
    }

    private void searchRptList(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        // Retrieve the list of authorized report
        // Get Userprofile service
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        // Get Authorized report list
        List<S21ArchivedReportInfo> reportInfoList = profileService.getAuthorizedArchivedReports();

        // get report list
        if(reportInfoList !=null && reportInfoList.size() != 0){
            S21SsmEZDResult ssmResult = ZZPL0030Query.getInstance().getReportList(cMsg, sMsg, reportInfoList);

            if (ssmResult.isCodeNormal()) {

                int queryResCnt = ssmResult.getQueryResultCount();
                if (queryResCnt > sMsg.A.length()) {
                    cMsg.setMessageInfo("ZZZM9002W");
                    queryResCnt = sMsg.A.length();
                }

                // copy data from SMsg (of 1st page) onto CMsg
                int i = 0;
                for (; i < sMsg.A.getValidCount(); i++) {
                    if (i == cMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
                cMsg.A.setValidCount(i);

                // set value to pagenation items
                cMsg.xxPageShowFromNum_0.setValue(1);
                cMsg.xxPageShowToNum_0.setValue(cMsg.A.getValidCount());
                cMsg.xxPageShowOfNum_0.setValue(queryResCnt);

            } else {
                cMsg.setMessageInfo("ZZPM0037W");
            }
        } else {
            cMsg.setMessageInfo("ZZPM0037W");
        }
    }

    private void searchRptParam(ZZPL0030CMsg cMsg) {
        // get parameters of advanced search pulldown
        S21SsmEZDResult ssmResult = ZZPL0030Query.getInstance().getAdvSrchPulldownList(cMsg);

        // set pulldown information
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                cMsg.xxRptSrchCd_O1.no(i).setValue((String) map.get("RPT_PRM_NM"));
                cMsg.xxRptSrchNm_O1.no(i).setValue((String) map.get("RPT_PRM_DPLY_NM"));
                cMsg.xxRptSrchCd_O2.no(i).setValue((String) map.get("RPT_PRM_NM"));
                cMsg.xxRptSrchNm_O2.no(i).setValue((String) map.get("RPT_PRM_DPLY_NM"));
                cMsg.xxRptSrchCd_O3.no(i).setValue((String) map.get("RPT_PRM_NM"));
                cMsg.xxRptSrchNm_O3.no(i).setValue((String) map.get("RPT_PRM_DPLY_NM"));
                cMsg.xxRptSrchCd_O4.no(i).setValue((String) map.get("RPT_PRM_NM"));
                cMsg.xxRptSrchNm_O4.no(i).setValue((String) map.get("RPT_PRM_DPLY_NM"));
            }
        }
    }

    private void searchRptHistList(ZZPL0030CMsg cMsg, ZZPL0030SMsg sMsg) {
        // get report history list
        S21SsmEZDResult ssmResult = ZZPL0030Query.getInstance().getReportHistList(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.B.length();
            }

            // copy data from SMsg (of 1st page) onto CMsg
            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }
            cMsg.B.setValidCount(i);

            // set value to pagenation items
            cMsg.xxPageShowFromNum_1.setValue(1);
            cMsg.xxPageShowToNum_1.setValue(cMsg.B.getValidCount());
            cMsg.xxPageShowOfNum_1.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo("ZZPM0002W");
        }
    }
}
