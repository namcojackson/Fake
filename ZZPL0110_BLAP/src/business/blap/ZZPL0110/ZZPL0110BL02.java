/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.ZZPL0110;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZZPL0110.common.ZZPL0110CommonLogic;
import business.blap.ZZPL0110.constant.ZZPL0110Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/13   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0110BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (ZZPL0110Constant.EVENT_PAGE_NEXT_SCRN00.equals(screenAplID)) {
                doProcess_ZZPL0110Scrn00_PageNext((ZZPL0110CMsg) cMsg, (ZZPL0110SMsg) sMsg);
            } else if (ZZPL0110Constant.EVENT_PAGE_PREV_SCRN00.equals(screenAplID)) {
                doProcess_ZZPL0110Scrn00_PagePrev((ZZPL0110CMsg) cMsg, (ZZPL0110SMsg) sMsg);
            } else if (ZZPL0110Constant.EVENT_SEARCH_SCRN00.equals(screenAplID)) {
                doProcess_ZZPL0110Scrn00_Search((ZZPL0110CMsg) cMsg, (ZZPL0110SMsg) sMsg);
            } else if (ZZPL0110Constant.EVENT_TBL_COLUMN_SORT_SCRN00.equals(screenAplID)) {
                doProcess_ZZPL0110Scrn00_TBLColumnSort((ZZPL0110CMsg) cMsg, (ZZPL0110SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_ZZPL0110Scrn00_PageNext(ZZPL0110CMsg cMsg, ZZPL0110SMsg sMsg) {
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

    private void doProcess_ZZPL0110Scrn00_PagePrev(ZZPL0110CMsg cMsg, ZZPL0110SMsg sMsg) {
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
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);

    }

    private void doProcess_ZZPL0110Scrn00_Search(ZZPL0110CMsg cMsg, ZZPL0110SMsg sMsg) {
        // check existence of Global Company Code
        if (!ZZPL0110CommonLogic.checkGlblCmpCd(cMsg)) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {"Global Company Code" });
            return;
        }

        searchProcLogList(cMsg, sMsg);
    }

    private void doProcess_ZZPL0110Scrn00_TBLColumnSort(ZZPL0110CMsg cMsg, ZZPL0110SMsg sMsg) {
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

            // number the "No" of table A
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).xxRowNum_A.setValue(i + 1);
            }

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
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        }

    }

    private void searchProcLogList(ZZPL0110CMsg cMsg, ZZPL0110SMsg sMsg) {
        S21SsmEZDResult ssmResult = ZZPL0110Query.getInstance().getProcLogList(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.A.length();
            }

            // number the "No" of table A, and set job process time
            String jobStartTime = null;
            String jobEndTime = null;
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).xxRowNum_A.setValue(i + 1);

                jobStartTime = sMsg.A.no(i).xxDtTm_FR.getValue();
                jobEndTime = sMsg.A.no(i).xxDtTm_TO.getValue();
                if (jobStartTime != null && !jobStartTime.equals("") && jobEndTime != null && !jobEndTime.equals("")) {
                    sMsg.A.no(i).xxRptProcJobAot_A.setValue(new BigDecimal(getProcTime(jobStartTime, jobEndTime)));
                }
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
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo("ZZPM0037W");
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    private String getProcTime(String jobStartTime, String jobEndTime) {

        String diffMinString = null;
        SimpleDateFormat dbFormat = new SimpleDateFormat(ZZPL0110Constant.SYSTEM_DATE_FORMAT);
        Date startTime = null;
        Date endTime = null;
        double diffMin = 0;
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern("0");
        decimalFormat.setMaximumFractionDigits(1);
        decimalFormat.setMinimumFractionDigits(1);

        try {
            startTime = dbFormat.parse(jobStartTime);
        } catch (java.text.ParseException e) {
            throw new S21AbendException("ZZXM0022E", new String[] {ZZPL0110Constant.SYSTEM_DATE_FORMAT, jobStartTime });
        }

        try {
            endTime = dbFormat.parse(jobEndTime);
        } catch (java.text.ParseException e) {
            throw new S21AbendException("ZZXM0022E", new String[] {ZZPL0110Constant.SYSTEM_DATE_FORMAT, jobEndTime });
        }

        // subtract startTime from endTime, and convert milli second into minute
        diffMin = (double) (endTime.getTime() - startTime.getTime()) / 1000 / 60;
        diffMinString = decimalFormat.format(diffMin);
        return diffMinString;
    }

}
