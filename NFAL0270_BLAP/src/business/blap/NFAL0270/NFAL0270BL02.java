/*
 * <Pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0270;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFAL0270.common.NFAL0270CommonLogic;
import business.file.NFAL0270F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import static business.blap.NFAL0270.constant.NFAL0270Constant.*;


/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/17   Hitachi         G.Quan          Create          QC#61387
 *</pre>
 */
public class NFAL0270BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            // NFAL0270_INIT
            if (NFAL0270_INIT.equals(screenAplID)) {
                doProcess_NFAL0270_INIT((NFAL0270CMsg) cMsg, (NFAL0270SMsg) sMsg);
                ZYPGUITableColumn.getColData((NFAL0270CMsg) cMsg, (NFAL0270SMsg) sMsg);

                // NFAL0270Scrn00_Add_Line
            } else if (NFAL0270SCRN00_ADD_LINE.equals(screenAplID)) {
                doProcess_NFAL0270Scrn00_Add_Line((NFAL0270CMsg) cMsg, (NFAL0270SMsg) sMsg);

                // NFAL0270Scrn00_CMN_Clear
            } else if (NFAL0270SCRN00_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NFAL0270Scrn00_CMN_Clear((NFAL0270CMsg) cMsg, (NFAL0270SMsg) sMsg);

                // NFAL0270Scrn00_CMN_Download
            } else if (NFAL0270SCRN00_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_NFAL0270_CMN_Download((NFAL0270CMsg) cMsg, (NFAL0270SMsg) sMsg);

                // NFAL0270Scrn00_Del_Line
            } else if (NFAL0270SCRN00_DEL_LINE.equals(screenAplID)) {
                doProcess_NFAL0270Scrn00_Delete_Line((NFAL0270CMsg) cMsg, (NFAL0270SMsg) sMsg);

                // NFAL0270Scrn00_PageNext
            } else if (NFAL0270SCRN00_PAGENEXT.equals(screenAplID)) {
                doProcess_NFAL0270Scrn00_PageNext((NFAL0270CMsg) cMsg, (NFAL0270SMsg) sMsg);

                // NFAL0270Scrn00_PagePrev
            } else if (NFAL0270SCRN00_PAGEPREV.equals(screenAplID)) {
                doProcess_NFAL0270Scrn00_PagePrev((NFAL0270CMsg) cMsg, (NFAL0270SMsg) sMsg);

                // NFAL0270Scrn00_Search
            } else if (NFAL0270SCRN00_SEARCH.equals(screenAplID)) {
                doProcess_NFAL0270Scrn00_Search((NFAL0270CMsg) cMsg, (NFAL0270SMsg) sMsg);

                // NFAL0270Scrn00_TBLColumnSort
            } else if (NFAL0270SCRN00_TBLCOLUMNSORT.equals(screenAplID)) {
                doProcess_NFAL0270Scrn00_TBLColumnSort((NFAL0270CMsg) cMsg, (NFAL0270SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);

            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_INIT
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFAL0270_INIT(NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NFAL0270_INIT START ----- ", this);

        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        NFAL0270CommonLogic.setPulldownSvcInvChrgTpCdList(getGlobalCompanyCode(), cMsg);
        ZYPCodeDataUtil.createPulldownList("SVC_ALLOC_TP", cMsg.svcAllocTpCd_PD, cMsg.svcAllocTpDescTxt_PD, ":");

        EZDDebugOutput.println(1, "----- doProcess_NFAL0270_INIT END ----- ", this);
    }

    /**
     * doProcess_Add_Line
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFAL0270Scrn00_Add_Line(NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NFAL0270Scrn00_Add_Line START ----- ", this);

        NFAL0270CommonLogic.copyPage(cMsg, cMsg.A, sMsg.A);

        int no = sMsg.A.getValidCount();

        if (no == sMsg.A.length()) {
            cMsg.setMessageInfo(NFAM0210E, new String[] {String.valueOf(sMsg.A.length()) });
            return;
        }

        sMsg.A.setValidCount(no + 1);

        NFAL0270CommonLogic.lastPage(cMsg, sMsg);
        cMsg.setMessageInfo(NZZM0002I);
        EZDDebugOutput.println(1, "----- doProcess_NFAL0270Scrn00_Add_Line END ----- ", this);
    }

    /**
     * doProcess_Delete_Line
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFAL0270Scrn00_Delete_Line(NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NFAL0270Scrn00_Delete_Line START ----- ", this);

        NFAL0270CommonLogic.copyPage(cMsg, cMsg.A, sMsg.A);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NFAM0075E);
            return;
        }

        NFAL0270SMsg tmpMsg = new NFAL0270SMsg();
        EZDMsg.copy(sMsg, null, tmpMsg, null);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);

        int index = 0;
        int deleteIndex = 0;
        for (int i = 0; i < tmpMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(tmpMsg.A.no(i).xxChkBox_A.getValue())) {
                EZDMsg.copy(tmpMsg.A.no(i), null, sMsg.A.no(index), null);
                index++;
            } else {
                setValue(sMsg.B.no(deleteIndex).mdlGrpId_B, tmpMsg.A.no(i).mdlGrpId_A.getValue());
                setValue(sMsg.B.no(deleteIndex).mdlGrpNm_B, tmpMsg.A.no(i).mdlGrpNm_A.getValue());
                setValue(sMsg.B.no(deleteIndex).svcInvChrgTpCd_B, tmpMsg.A.no(i).svcInvChrgTpCd_A.getValue());
                setValue(sMsg.B.no(deleteIndex).svcAllocTpCd_B, tmpMsg.A.no(i).svcAllocTpCd_A.getValue());
                setValue(sMsg.B.no(deleteIndex).equipAllocPct_B, tmpMsg.A.no(i).equipAllocPct_A.getValue());
                setValue(sMsg.B.no(deleteIndex).svcAllocPct_B, tmpMsg.A.no(i).svcAllocPct_A.getValue());
                setValue(sMsg.B.no(deleteIndex).splyAllocPct_B, tmpMsg.A.no(i).splyAllocPct_A.getValue());
                deleteIndex++;
            }
        }
        sMsg.A.setValidCount(index);
        sMsg.B.setValidCount(deleteIndex);
        BigDecimal lastPageFromNum = NFAL0270CommonLogic.getLastPageFromNum(cMsg, sMsg);
        if (cMsg.xxPageShowFromNum_A.getValue().compareTo(lastPageFromNum) < 0) {
            NFAL0270CommonLogic.dispPage(cMsg, cMsg.A, sMsg.A);
        } else {
            NFAL0270CommonLogic.lastPage(cMsg, sMsg);
        }

        cMsg.setMessageInfo(NZZM0002I);
        EZDDebugOutput.println(1, "----- doProcess_NFAL0270Scrn00_Delete_Line END ----- ", this);
    }

    /**
     * doProcess_PageNext
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFAL0270Scrn00_PageNext(NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NFAL0270Scrn00_PageNext START ----- ", this);


        sMsg.A.clearErrorInfo();

        NFAL0270CommonLogic.copyPage(cMsg, cMsg.A, sMsg.A);
        NFAL0270CommonLogic.nextPage(cMsg, sMsg);

        EZDDebugOutput.println(1, "----- doProcess_NFAL0270Scrn00_PageNext END ----- ", this);
    }

    /**
     * doProcess_PagePrev
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFAL0270Scrn00_PagePrev(NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NFAL0270Scrn00_PagePrev START ----- ", this);

        sMsg.A.clearErrorInfo();

        NFAL0270CommonLogic.copyPage(cMsg, cMsg.A, sMsg.A);
        NFAL0270CommonLogic.prevPage(cMsg, sMsg);

        EZDDebugOutput.println(1, "----- doProcess_NFAL0270Scrn00_PagePrev END ----- ", this);
    }

    /**
     * doProcess_CMN_Clear
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFAL0270Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NFAL0270Scrn00_CMN_Clear START ----- ", this);
        NFAL0270CMsg bizMsg = (NFAL0270CMsg) cMsg;
        NFAL0270SMsg globalMsg = (NFAL0270SMsg) sMsg;

        bizMsg.clear();
        globalMsg.clear();

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(globalMsg.A);

        doProcess_NFAL0270_INIT(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "----- doProcess_NFAL0270Scrn00_CMN_Clear END ----- ", this);
        }

    /**
     * doProcess_Search
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFAL0270Scrn00_Search(NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NFAL0270Scrn00_Search START ----- ", this);

        String glblCmpyCd = getGlobalCompanyCode();

        NFAL0270CommonLogic.search(glblCmpyCd, cMsg, sMsg);

        EZDDebugOutput.println(1, "----- doProcess_NFAL0270Scrn00_Search END ----- ", this);
    }

    /**
     * doProcess_TBLColumnSort
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFAL0270Scrn00_TBLColumnSort(NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm_B1.getValue();
        String sortItemNm = cMsg.xxSortItemNm_B1.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt_B1.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // Copy
            int i = 0;

            for (; i < sMsg.A.getValidCount(); i++) {

                if (i == cMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            cMsg.A.setValidCount(i);

            // set Page
            cMsg.xxPageShowFromNum_A.setValue(1);
            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
        }
    }

    /**
     * doProcess_CMN_Download
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFAL0270_CMN_Download(NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        S21SsmEZDResult ssmResult = NFAL0270Query.getInstance().getAllocation(glblCmpyCd, cMsg, sMsg, LIMIT_DL_ROWNUM);
        writeCsvFile(cMsg, ssmResult);
    }

    /**
     * writeCsvFile
     * @param cMsg
     * @param rs
     */
    private void writeCsvFile(NFAL0270CMsg cMsg, S21SsmEZDResult ssmResult) {
        // set CSV File
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");

        // Create FMsg
        NFAL0270F00FMsg fMsg = new NFAL0270F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
        fMsg.addExclusionItem("xxChkBox_A");

        // Set Header record
        final String[] csvHeader = new String[] {"", "Model Group ID", "Model Group Name", "Charge Type", "Allocation Type", "Equipment Percentage", "Service Percentage", "Supply Percentage"};
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

            if (resultList.size() == 0 || cMsg.A.getValidCount() == 0) {
                cMsg.setMessageInfo(NZZM0000E);
                csvOutFile.close();
                return;
            }

            // copy invtyTrxMap to fMsg
            int index = 0;
            for (Map<String, Object> resultMap : resultList) {
                if (index < LIMIT_DL_ROWNUM) {
                    ZYPEZDItemValueSetter.setValue(fMsg.mdlGrpId_A, (BigDecimal) resultMap.get("MDL_GRP_ID"));
                    ZYPEZDItemValueSetter.setValue(fMsg.mdlGrpNm_A, (String) resultMap.get("MDL_GRP_NM"));
                    ZYPEZDItemValueSetter.setValue(fMsg.svcInvChrgTpDescTxt_A, ZYPCommonFunc.concatString((String) resultMap.get("SVC_INV_CHRG_TP_CD"), ":", (String) resultMap.get("SVC_INV_CHRG_TP_DESC_TXT")));
                    ZYPEZDItemValueSetter.setValue(fMsg.svcAllocTpDescTxt_A, ZYPCommonFunc.concatString((String) resultMap.get("SVC_ALLOC_TP_CD"), ":", (String) resultMap.get("SVC_ALLOC_TP_DESC_TXT")));
                    ZYPEZDItemValueSetter.setValue(fMsg.equipAllocPct_A, (BigDecimal) resultMap.get("EQUIP_ALLOC_PCT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.svcAllocPct_A, (BigDecimal) resultMap.get("SVC_ALLOC_PCT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.splyAllocPct_A, (BigDecimal) resultMap.get("SPLY_ALLOC_PCT"));
                    csvOutFile.write();
                } else {
                    break;
                }
                index++;
            }

            if (index < LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0002I);
            } else {
                cMsg.setMessageInfo(ZZZM9002W);
            }
        } else {
            cMsg.setMessageInfo(NZZM0000E);
        }

        // output
        csvOutFile.close();
    }
}
