/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7110;

import static business.blap.NMAL7110.constant.NMAL7110Constant.CSV_DOWNLOAD_FILE_NAME;
import static business.blap.NMAL7110.constant.NMAL7110Constant.CSV_DOWNLOAD_HEADER;
import static business.blap.NMAL7110.constant.NMAL7110Constant.NZZM0000E;
import static business.blap.NMAL7110.constant.NMAL7110Constant.NZZM0001W;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7110.common.NMAL7110CommonLogic;
import business.file.NMAL7110F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NMAL7110BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NMAL7110BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7110CMsg bizMsg = (NMAL7110CMsg) cMsg;
            NMAL7110SMsg glblMsg = (NMAL7110SMsg) sMsg;

            if ("NMAL7110_INIT".equals(screenAplID)) {
                doProcess_NMAL7110_INIT(bizMsg, glblMsg);
            } else if ("NMAL7110Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7110Scrn00_CMN_Download(bizMsg, glblMsg);
            } else if ("NMAL7110Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL7110Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NMAL7110Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL7110Scrn00_PagePrev(bizMsg, glblMsg);
            } else if ("NMAL7110Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL7110Scrn00_PageJump(bizMsg, glblMsg);
            } else if ("NMAL7110Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7110Scrn00_Search(bizMsg, glblMsg);
            } else if ("NMAL7110Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL7110Scrn00_TBLColumnSort(bizMsg, glblMsg);
            } else if ("NMAL7110Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7110Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else if ("NMAL7110Scrn00_OnChange_SavedSearchOption".equals(screenAplID)) {
                doProcess_NMAL7110_OnChange_SavedSearchOption(bizMsg, glblMsg);
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
    private void doProcess_NMAL7110_INIT(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg) {

        NMAL7110CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());

        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_L1, bizMsg.lineBizTpDescTxt_L1);

        ZYPGUITableColumn.getColData(bizMsg, glblMsg);

    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7110Scrn00_CMN_Download(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg) {
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_DOWNLOAD_FILE_NAME), ".csv");

        NMAL7110F00FMsg fMsg = new NMAL7110F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            fMsg.clear();
            EZDMsg.copy(glblMsg.A.no(i), null, fMsg, null);
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).actvFlg_A)
                    && ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).actvFlg_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxSelChkBoxTxt_A, "ACTIVE");
            } else {
                ZYPEZDItemValueSetter.setValue(fMsg.xxSelChkBoxTxt_A, "INACTIVE");
            }

            csvOutFile.write();
        }

        csvOutFile.close();

    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7110Scrn00_CMN_Clear(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg) {
        bizMsg.clear();
        bizMsg.A.setValidCount(0);
        glblMsg.clear();
        glblMsg.A.setValidCount(0);

        doProcess_NMAL7110_INIT(bizMsg, glblMsg);
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7110Scrn00_PageNext(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg) {

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL7110CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7110Scrn00_PagePrev(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg) {

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL7110CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7110Scrn00_PageJump(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg) {

        bizMsg.xxPageShowFromNum.setValue(bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1));
        NMAL7110CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7110Scrn00_Search(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7110Scrn00_TBLColumnSort(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL7110CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcContrNum", bizMsg.prcContrNum.getValue());
        params.put("prcContrNm", bizMsg.prcContrNm.getValue());
        params.put("assnPgmContrFlg", bizMsg.assnPgmContrFlg.getValue());
        params.put("prcContrCustBidNum", bizMsg.prcContrCustBidNum.getValue());
        params.put("lineBizTpCd", bizMsg.lineBizTpCd.getValue());
        params.put("effFromDt", bizMsg.effFromDt.getValue());
        params.put("effThruDt", bizMsg.effThruDt.getValue());
        params.put("actvFlg", bizMsg.actvFlg.getValue());

        S21SsmEZDResult ssmResult = NMAL7110Query.getInstance().searchPrdcContrList(params, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length() - 1) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length() - 1);
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL7110CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    /**
     * OnChange SavedSearchOption.
     * @param bizMsg
     * @param glblMsg
     */
    private void doProcess_NMAL7110_OnChange_SavedSearchOption(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            NMAL7110CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

}
