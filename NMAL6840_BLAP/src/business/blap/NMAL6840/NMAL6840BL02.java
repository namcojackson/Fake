/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6840;

import static business.blap.NMAL6840.constant.NMAL6840Constant.EVENT_NM_CMN_CLEAR;
import static business.blap.NMAL6840.constant.NMAL6840Constant.EVENT_NM_CMN_RESET;
import static business.blap.NMAL6840.constant.NMAL6840Constant.EVENT_NM_CMN_SUBMIT;
import static business.blap.NMAL6840.constant.NMAL6840Constant.EVENT_NM_INIT;
import static business.blap.NMAL6840.constant.NMAL6840Constant.EVENT_NM_ONCLICK_DELETE_ROW;
import static business.blap.NMAL6840.constant.NMAL6840Constant.EVENT_NM_ONCLICK_INSERT_ROW;
import static business.blap.NMAL6840.constant.NMAL6840Constant.EVENT_NM_ONCLICK_SEARCH;
import static business.blap.NMAL6840.constant.NMAL6840Constant.MDSE_COST_TP_NM_DBCOLUMN;
import static business.blap.NMAL6840.constant.NMAL6840Constant.MDSE_INVTY_COST_PCT_DBCOLUMN;
import static business.blap.NMAL6840.constant.NMAL6840Constant.MESSAGE_CD_NMAM0038I;
import static business.blap.NMAL6840.constant.NMAL6840Constant.MESSAGE_CD_NMAM0186E;
import static business.blap.NMAL6840.constant.NMAL6840Constant.MESSAGE_CD_NMAM0835E;
import static business.blap.NMAL6840.constant.NMAL6840Constant.MESSAGE_CD_NMAM8114E;
import static business.blap.NMAL6840.constant.NMAL6840Constant.MESSAGE_CD_NMAM8181W;
import static business.blap.NMAL6840.constant.NMAL6840Constant.MESSAGE_CD_NZZM0002I;
import static business.blap.NMAL6840.constant.NMAL6840Constant.RTL_SWH_CD_DBCOLUMN;
import static business.blap.NMAL6840.constant.NMAL6840Constant.RTL_SWH_DESC_TXT_DBCOLUMN;
import static business.blap.NMAL6840.constant.NMAL6840Constant.RTL_SWH_NM_DBCOLUMN;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL6840.common.NMAL6840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <p>
 * NMAL6840 Sub Warehouse Setup.
 * </p>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/27/2015   CITS            M.Ito           Create          N/A
 * </pre>
 */
public class NMAL6840BL02 extends S21BusinessHandler {

    /*
     * (non-Javadoc)
     * @see
     * parts.ejbcommon.EZDBusinessHandler#doProcess(parts.common.EZDCMsg
     * , parts.common.EZDSMsg)
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String scrnAplId = cMsg.getScreenAplID();

            NMAL6840CMsg bizMsg = (NMAL6840CMsg) cMsg;
            NMAL6840SMsg glblMsg = (NMAL6840SMsg) sMsg;

            if (EVENT_NM_INIT.equals(scrnAplId)) {
                doProcessInit(bizMsg, glblMsg);
            } else if (EVENT_NM_ONCLICK_SEARCH.equals(scrnAplId)) {
                doProcessOnClickSearch(bizMsg, glblMsg);
            } else if (EVENT_NM_ONCLICK_INSERT_ROW.equals(scrnAplId)) {
                doProcessOnClickInsertRow(bizMsg, glblMsg);
            } else if (EVENT_NM_ONCLICK_DELETE_ROW.equals(scrnAplId)) {
                doProcessOnClickDeleteRow(bizMsg, glblMsg);
            } else if (EVENT_NM_CMN_SUBMIT.equals(scrnAplId)) {
                doProcessCmnSubmit(bizMsg, glblMsg);
            } else if (EVENT_NM_CMN_CLEAR.equals(scrnAplId)) {
                doProcessCmnClear(bizMsg, glblMsg);
            } else if (EVENT_NM_CMN_RESET.equals(scrnAplId)) {
                doProcessCmnReset(bizMsg, glblMsg);
            } else {
                throw new S21AbendException("Illegal Screen Application Id. : " + scrnAplId);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <p>
     * Initialization.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessInit(NMAL6840CMsg cMsg, NMAL6840SMsg sMsg) {
        // Initializes.
        sMsg.clear();
        NMAL6840CommonLogic.clearTable(cMsg, sMsg);

        // Sets the global company code.
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd_C1, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(sMsg.glblCmpyCd_C1, getGlobalCompanyCode());

        // Creates the pull-down lists.
        ZYPCodeDataUtil.createPulldownList(RTL_WH_CATG.class, cMsg.rtlWhCatgCd_L1, cMsg.rtlWhCatgNm_L1);

        S21SsmEZDResult searchSubWHList = NMAL6840Query.getInstance().searchSubWarehouse(cMsg, sMsg);
        if (searchSubWHList.isCodeNormal()) {
            List<Map> subWHList = (List<Map>) searchSubWHList.getResultObject();
            NMAL6840CommonLogic.createPulldownListForSWH(cMsg, subWHList, new String[] {RTL_SWH_CD_DBCOLUMN
                    , RTL_SWH_CD_DBCOLUMN, RTL_SWH_NM_DBCOLUMN, RTL_SWH_DESC_TXT_DBCOLUMN, MDSE_COST_TP_NM_DBCOLUMN, MDSE_INVTY_COST_PCT_DBCOLUMN });
        }

        ZYPCodeDataUtil.createPulldownList(PROCR_TP.class, cMsg.procrTpCd_L1, cMsg.procrTpNm_L1);
        ZYPCodeDataUtil.createPulldownList(PROCR_TP.class, cMsg.procrTpCd_L2, cMsg.procrTpNm_L2);
//        ZYPCodeDataUtil.createPulldownList(MDSE_COST_TP.class, cMsg.mdseCostTpCd_L1, cMsg.mdseCostTpNm_L1);

        // In the case of transition from other screen,
        // runs the search process. (onClickSearch)
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCatgCd_H1)) {
            doProcessOnClickSearch(cMsg, sMsg);
        }
    }

    /**
     * <p>
     * Runs the onclick event of "Search" button.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessOnClickSearch(NMAL6840CMsg cMsg, NMAL6840SMsg sMsg) {
        // Initializes.
        sMsg.clear();
        NMAL6840CommonLogic.clearTable(cMsg, sMsg);

        // Copies the pull-down lists.
        EZDMsg.copy(cMsg, null, sMsg, null);

        // Searches WH Category and Sub WH Relations.
        S21SsmEZDResult result = NMAL6840Query.getInstance().searchSubWarehouseRelations(cMsg, sMsg);

        // Checks if the row counts exceeds the maximum counts.
        final int maxCount = sMsg.A.length();
        int displayCount = result.getQueryResultCount();
        if (result.getQueryResultCount() == 0) {
            // No search results.
            NMAL6840CommonLogic.setMessageInfo(cMsg, MESSAGE_CD_NMAM0038I);
        } else if (result.getQueryResultCount() > maxCount) {
            // Search results exceeds max count for display.
            displayCount = maxCount;
            NMAL6840CommonLogic.setMessageInfo(cMsg, MESSAGE_CD_NMAM8181W, maxCount, maxCount);
        } else {
            // Search results does NOT exceed max count for display.
            NMAL6840CommonLogic.setMessageInfo(cMsg, MESSAGE_CD_NZZM0002I);
        }

        // Sets the search result to the table.
        for (int rowIndex = 0; rowIndex < displayCount; rowIndex++) {
            EZDMsg.copy(sMsg.A.no(rowIndex), null, cMsg.A.no(rowIndex), null);
        }

        cMsg.A.setValidCount(displayCount);
    }

    /**
     * <p>
     * Runs the onclick event of "Insert Row" button.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessOnClickInsertRow(NMAL6840CMsg cMsg, NMAL6840SMsg sMsg) {

        // Checks if WH Category is changed after search.
        if (NMAL6840CommonLogic.isChangedAfterSearch(sMsg.rtlWhCatgCd_H1, cMsg.rtlWhCatgCd_H1)) {
            NMAL6840CommonLogic.setMessageInfo(cMsg, MESSAGE_CD_NMAM0186E);
            return;
        }

        // Checks if the row counts exceeds the maximum counts.
        if (cMsg.A.getValidCount() >= cMsg.A.length()) {
            NMAL6840CommonLogic.setMessageInfo(cMsg, MESSAGE_CD_NMAM8114E);
            return;
        }

        // Adds the valid count.
        cMsg.A.setValidCount(cMsg.A.getValidCount() + 1);

        NMAL6840CommonLogic.setMessageInfo(cMsg, MESSAGE_CD_NZZM0002I);
    }

    /**
     * <p>
     * Runs the onclick event of "Delete Row" button.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessOnClickDeleteRow(NMAL6840CMsg cMsg, NMAL6840SMsg sMsg) {

        // Checks if WH Category is changed after search.
        if (NMAL6840CommonLogic.isChangedAfterSearch(sMsg.rtlWhCatgCd_H1, cMsg.rtlWhCatgCd_H1)) {
            cMsg.setMessageInfo(MESSAGE_CD_NMAM0186E);
            return;
        }

        int newRowCount = 0;
        for (int rowIndex = 0; rowIndex < cMsg.A.getValidCount(); rowIndex++) {

            // If is checked, NOT copy the row.
            if (NMAL6840CommonLogic.isChecked(cMsg.A.no(rowIndex).xxChkBox_A1)) {
                continue;
            }

            EZDMsg.copy(cMsg.A.no(rowIndex), null, cMsg.A.no(newRowCount), null);
            newRowCount++;
        }

        // Checks if the check box is selected.
        if (newRowCount == cMsg.A.getValidCount()) {
            cMsg.setMessageInfo(MESSAGE_CD_NMAM0835E);
            return;
        }

        // Clears the value of deleted row.
        for (int rowIndex = newRowCount; rowIndex < cMsg.A.getValidCount(); rowIndex++) {
            cMsg.A.no(rowIndex).clear();
        }

        cMsg.A.setValidCount(newRowCount);

        cMsg.setMessageInfo(MESSAGE_CD_NZZM0002I);
    }

    /**
     * <p>
     * Runs the onclick event of "SUBMIT" button.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessCmnSubmit(NMAL6840CMsg cMsg, NMAL6840SMsg sMsg) {

        // Runs the search process.
        doProcessOnClickSearch(cMsg, sMsg);
    }

    /**
     * <p>
     * Runs the onclick event of "CLEAR" button.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessCmnClear(NMAL6840CMsg cMsg, NMAL6840SMsg sMsg) {
        // Clears the pull-down of WH Category.
        cMsg.rtlWhCatgCd_H1.clear();

        // Runs the initialize process.
        doProcessInit(cMsg, sMsg);

        NMAL6840CommonLogic.setMessageInfo(cMsg, MESSAGE_CD_NZZM0002I);
    }

    /**
     * <p>
     * Runs the onclick event of "RESET" button.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessCmnReset(NMAL6840CMsg cMsg, NMAL6840SMsg sMsg) {
        // Clears the pull-down of WH Category.
        cMsg.rtlWhCatgCd_H1.clear();

        // Runs the initialize process.
        doProcessInit(cMsg, sMsg);

        NMAL6840CommonLogic.setMessageInfo(cMsg, MESSAGE_CD_NZZM0002I);
    }
}
