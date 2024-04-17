/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1710;

import static business.blap.NWAL1710.constant.NWAL1710Constant.CSV_NAME_CONT_INFO;
import static business.blap.NWAL1710.constant.NWAL1710Constant.DBCOLUMN_ORD_PROC_TP_CD;
import static business.blap.NWAL1710.constant.NWAL1710Constant.DBCOLUMN_ORD_PROC_TP_DESC_TXT;
import static business.blap.NWAL1710.constant.NWAL1710Constant.NZZM0000E;
import static business.blap.NWAL1710.constant.NWAL1710Constant.NZZM0001W;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1710.common.NWAL1710CommonLogic;
import business.file.NWAL1710F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NWAL1710BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         M.Suzuki        Create          N/A
 * 2016/04/04   Fujitsu         M.Suzuki        Create          QC#6336
 *</pre>
 */
public class NWAL1710BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1710CMsg bizMsg = (NWAL1710CMsg) cMsg;
            NWAL1710SMsg glblMsg = (NWAL1710SMsg) sMsg;

            if ("NWAL1710_INIT".equals(screenAplID)) {
                doProcess_NWAL1710_INIT(bizMsg, glblMsg);

            } else if ("NWAL1710Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NWAL1710Scrn00_CMN_Download(bizMsg, glblMsg);
              //2016/04/04 S21_NA#6336 MOD Start --------------
//            } else if ("NWAL1710Scrn00_CMN_Reset".equals(screenAplID)) {
//                doProcess_NWAL1710Scrn00_CMN_Reset(bizMsg, glblMsg);
              //2016/04/04 S21_NA#6336 MOD Start --------------
            } else if ("NWAL1710Scrn00_Create_New".equals(screenAplID)) {
                doProcess_NWAL1710Scrn00_Create_New(bizMsg, glblMsg);

            } else if ("NWAL1710Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL1710Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NWAL1710Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL1710Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NWAL1710Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL1710Scrn00_Search(bizMsg, glblMsg);

            } else if ("NWAL1710Scrn00_Select_Reason".equals(screenAplID)) {
                doProcess_NWAL1710Scrn00_Select_Reason(bizMsg, glblMsg);

            } else if ("NWAL1710Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NWAL1710Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NWAL1710Scrn00_CMN_Clear".equals(screenAplID)) {

                doProcess_NWAL1710Scrn00_CMN_Clear(bizMsg, glblMsg);
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
    private void doProcess_NWAL1710Scrn00_CMN_Clear(NWAL1710CMsg bizMsg, NWAL1710SMsg glblMsg) {
        doProcess_NWAL1710_INIT(bizMsg, glblMsg);
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1710_INIT(NWAL1710CMsg bizMsg, NWAL1710SMsg glblMsg) {

        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_P, bizMsg.lineBizTpDescTxt_P);
        S21SsmEZDResult workflowPulldownList = NWAL1710Query.getInstance().getWorkflowPulldownList();
        if (workflowPulldownList.isCodeNormal()) {

            List<Map> workflowList = (List<Map>) workflowPulldownList.getResultObject();
            NWAL1710CommonLogic.createPulldownList(bizMsg.ordProcTpCd_P, bizMsg.ordProcTpDescTxt_P, workflowList, DBCOLUMN_ORD_PROC_TP_CD, DBCOLUMN_ORD_PROC_TP_DESC_TXT);
        }
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1710Scrn00_CMN_Download(NWAL1710CMsg bizMsg, NWAL1710SMsg glblMsg) {
        NWAL1710Query.getInstance().searchForCSV(bizMsg, new CreateDownloadData(bizMsg));

    }

    /**
     * CreateDownloadData
     *
     */
    private static class CreateDownloadData extends S21SsmBooleanResultSetHandlerSupport {

        /**
         * NWAL1710CMsg
         */
        private NWAL1710CMsg bizMsg;

        public CreateDownloadData(NWAL1710CMsg cMsg) {
            this.bizMsg = cMsg;
        }

        /**
         * CSV_HEADER
         */
        private static final String[] CSV_HEADER = {"Catg #", "Order Category", "Reason Code", "Workflow ", "Sub Reason", "Description", "LOB", "Start Date ", "End Date ", "Active " };

        @Override
        protected Boolean doProcessQueryResult(ResultSet result) throws SQLException {
            if (!result.next()) {
                bizMsg.setMessageInfo(NZZM0000E);
                return Boolean.FALSE;
            }
            NWAL1710F00FMsg fMsg = new NWAL1710F00FMsg();
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_NAME_CONT_INFO), ".CSV");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
            csvOutFile.writeHeader(CSV_HEADER);

            do {

                fMsg.clear();
                ZYPEZDItemValueSetter.setValue(fMsg.dsOrdCatgCd, result.getString("DS_ORD_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsOrdCatgNm, result.getString("DS_ORD_CATG_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsOrdTpNm, result.getString("DS_ORD_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.ordProcTpNm, result.getString("ORD_PROC_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsOrdRsnGrpNm, result.getString("DS_ORD_RSN_GRP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsOrdTpDescTxt, result.getString("DS_ORD_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.lineBizTpNm, result.getString("LINE_BIZ_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_F, NWAL1710CommonLogic.formatDt(result.getString("EFF_FROM_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_T, NWAL1710CommonLogic.formatDt(result.getString("EFF_THRU_DT")));
                String activeFlag = result.getString("ACTV_FLG");
                if (ZYPConstant.FLG_ON_Y.equals(activeFlag)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxYesNoNm, "YES");
                }
                csvOutFile.write();

            } while (result.next());
            csvOutFile.close();
            return Boolean.TRUE;

        }
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     *///2016/04/04 S21_NA#6336 MOD Start --------------
//    private void doProcess_NWAL1710Scrn00_CMN_Reset(NWAL1710CMsg bizMsg, NWAL1710SMsg glblMsg) {
//        doProcess_NWAL1710_INIT(bizMsg, glblMsg);
//
//    }
      //2016/04/04 S21_NA#6336 End Start --------------

    /**
     * Create_New Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1710Scrn00_Create_New(NWAL1710CMsg bizMsg, NWAL1710SMsg glblMsg) {
        //donothing
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1710Scrn00_PageNext(NWAL1710CMsg bizMsg, NWAL1710SMsg glblMsg) {

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWAL1710CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1710Scrn00_PagePrev(NWAL1710CMsg bizMsg, NWAL1710SMsg glblMsg) {
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWAL1710CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1710Scrn00_Search(NWAL1710CMsg bizMsg, NWAL1710SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * Select_Reason Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1710Scrn00_Select_Reason(NWAL1710CMsg bizMsg, NWAL1710SMsg glblMsg) {
        //donothing
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1710Scrn00_TBLColumnSort(NWAL1710CMsg bizMsg, NWAL1710SMsg glblMsg) {

        String sortItemNm = bizMsg.xxSortItemNm_A.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt_A.getValue();
        S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());
        bizMsg.xxPageShowFromNum.setValue(1);
        NWAL1710CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NWAL1710CMsg bizMsg, NWAL1710SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NWAL1710Query.getInstance().getOrderCategoryList(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                if (i == glblMsg.A.length()) {
                    break;
                }
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsOrdCatgCd_A, (String) resultMap.get("DS_ORD_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsOrdCatgNm_A, (String) resultMap.get("DS_ORD_CATG_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsOrdTpCd_A, (String) resultMap.get("DS_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsOrdTpNm_A, (String) resultMap.get("DS_ORD_TP_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ordProcTpNm_A, (String) resultMap.get("ORD_PROC_TP_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsOrdRsnGrpNm_A, (String) resultMap.get("DS_ORD_RSN_GRP_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsOrdTpDescTxt_A, (String) resultMap.get("DS_ORD_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).lineBizTpNm_A, (String) resultMap.get("LINE_BIZ_TP_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).effFromDt_A, (String) resultMap.get("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).effThruDt_A, (String) resultMap.get("EFF_THRU_DT"));

                String activeFlag = (String) resultMap.get("ACTV_FLG");
                if (ZYPConstant.FLG_ON_Y.equals(activeFlag)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxYesNoNm_A, "YES");
                }
            }
            bizMsg.xxPageShowFromNum.setValue(1);
            NWAL1710CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

}
