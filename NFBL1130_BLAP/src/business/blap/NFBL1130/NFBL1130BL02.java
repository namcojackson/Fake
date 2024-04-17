/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL1130;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFBL1130.common.NFBL1130CommonLogic;
import business.blap.NFBL1130.constant.NFBL1130Constant;
import business.blap.NFBL1130.NFBL1130CMsg;
import business.blap.NFBL1130.NFBL1130SMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Accrual Write-off Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/10/10   Hitachi         J.Kim           Update          QC#5521
 * 2022/08/05   Hitachi         A.Kohinata      Update          QC#59245
 * </pre>
 */
public class NFBL1130BL02 extends S21BusinessHandler implements NFBL1130Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    /**
     * Method name: doProcess
     * <dd>The method explanation: Call each process by screen id.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();
            NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
            NFBL1130SMsg glblMsg = (NFBL1130SMsg) sMsg;

            // +++++ [START] : Scrn00
            if ("NFBL1130_INIT".equals(screenAplID)) {
                doProcess_NFBL1130_INIT(cMsg, sMsg);
                // START 2016/10/05 J.Kim [QC#5521,ADD]
                ZYPGUITableColumn.getColData(bizMsg, glblMsg);
                // END 2016/10/05 J.Kim [QC#5521,ADD]
            } else if ("NFBL1130Scrn00_Search".equals(screenAplID)) {
                doProcess_NFBL1130Scrn00_Search(cMsg, sMsg);
            } else if ("NFBL1130Scrn00_SearchSupplierName".equals(screenAplID)) {
                doProcess_NFBL1130Scrn00_SearchSupplierName(cMsg, sMsg);
            } else if ("NFBL1130Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFBL1130Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NFBL1130Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFBL1130Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NFBL1130Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFBL1130Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NFBL1130Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFBL1130Scrn00_PageNext(cMsg, sMsg);
             // START 2016/10/10 J.Kim [QC#5521,ADD]
            } else if ("NFBL1130Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NFBL1130Scrn00_OnChangeSavedSearchOption(bizMsg, glblMsg);
            } else if ("NFBL1130Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFBL1130Scrn00_TBLColumnSort(bizMsg, glblMsg);
            // END 2016/10/10 J.Kim [QC#5521,ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Scrn01
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NFBL1130_INIT
     * <dd>The method explanation: Init
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1130_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1130_INIT================================START", this);
        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
        String glblCmpyCd = getGlobalCompanyCode();

        // Create [Accrual Account Code] pulldown.
        NFBL1130CommonLogic.createAcrlCoaAcctCdPulldownList(bizMsg);

        NFBL1130CommonLogic.createPulldownListSaveOpt(bizMsg, getContextUserInfo().getUserId(), glblCmpyCd);

        EZDDebugOutput.println(5, "doProcess_NFBL1130_INIT================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1130Scrn00_Search
     * <dd>The method explanation: [Search] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1130Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1130Scrn00_Search================================START", this);
        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
        NFBL1130SMsg globalMsg = (NFBL1130SMsg) sMsg;

        // Clear detail information
        NFBL1130CommonLogic.clearLines(bizMsg, globalMsg);
        // Search records
        NFBL1130CommonLogic.searchRecord(bizMsg, globalMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL1130Scrn00_Search================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1130Scrn00_Search
     * <dd>The method explanation: [Search] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1130Scrn00_SearchSupplierName(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1130Scrn00_SearchSupplierName================================START", this);
        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;

        NFBL1130CommonLogic.setPrntVndNm(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL1130Scrn00_SearchSupplierName================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1130Scrn00_CMN_Download
     * <dd>The method explanation: Clear values.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1130Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL1130Scrn00_CMN_Download================================START", this);

        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
        NFBL1130SMsg globalMsg = (NFBL1130SMsg) sMsg;

        /** Create CSV data */
        NFBL1130CommonLogic.csvDownLoad(bizMsg, globalMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL1130Scrn00_CMN_Download================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1130Scrn00_CMN_Clear
     * <dd>The method explanation: Clear values.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1130Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL1130Scrn00_CMN_Clear================================START", this);

        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
        NFBL1130SMsg globalMsg = (NFBL1130SMsg) sMsg;

        // Clear information on HEADER tab
        NFBL1130CommonLogic.clearHeader(bizMsg);
        // Clear information on LINES tab
        NFBL1130CommonLogic.clearLines(bizMsg, globalMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL1130Scrn00_CMN_Clear================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1130Scrn00_PagePrev
     * <dd>The method explanation: Move to previous page.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1130Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL1130Scrn00_PagePrev================================START", this);

        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
        NFBL1130SMsg globalMsg = (NFBL1130SMsg) sMsg;

        NFBL1130CommonLogic.saveDetailInfo(bizMsg, globalMsg);
        bizMsg.A.clear();
        bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() - bizMsg.A.length() - 1);
        int pagenationFrom = bizMsg.xxPageShowFromNum_A.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                // copy data from SMsg onto CMsg
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);
        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum_A.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum_A.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

        EZDDebugOutput.println(5, "doProcess_NFBL1130Scrn00_PagePrev================================END", this);
        return;

    }

    /**
     * Method name: doProcess_NFBL1130Scrn00_PageNext
     * <dd>The method explanation: Move to next page.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1130Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL1130Scrn00_PageNext================================START", this);

        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
        NFBL1130SMsg globalMsg = (NFBL1130SMsg) sMsg;

        NFBL1130CommonLogic.saveDetailInfo(bizMsg, globalMsg);
        bizMsg.A.clear();
        bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowToNum_A.getValueInt());
        int pagenationFrom = bizMsg.xxPageShowFromNum_A.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                // copy data from SMsg onto CMsg
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);
        // set value to pagenation items
        bizMsg.xxPageShowFromNum_A.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum_A.setValue(pagenationFrom + bizMsg.A.getValidCount());

        EZDDebugOutput.println(5, "doProcess_NFBL1130Scrn00_PageNext================================END", this);

        return;
    }

    // START 2016/10/05 J.Kim [QC#5521,ADD]
    private void doProcess_NFBL1130Scrn00_OnChangeSavedSearchOption(NFBL1130CMsg bizMsg, NFBL1130SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!NFBL1130CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.poNum, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.delyOrdNum, pMsg.srchOptTxt_03);
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_04.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invRcvQty, new BigDecimal(pMsg.srchOptTxt_04.getValue()));
        } else {
            bizMsg.invRcvQty.clear();
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_WO, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvNum, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.prntVndCd, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, pMsg.srchOptTxt_08);
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_09.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invQty, new BigDecimal(pMsg.srchOptTxt_09.getValue()));
        } else {
            bizMsg.invQty.clear();
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_PM, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(bizMsg.acrlCoaAcctCd_S, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum, pMsg.srchOptTxt_12);
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_13.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invDt_FR, pMsg.srchOptTxt_13.getValue());
        } else {
            bizMsg.invDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_14.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invDt_TO, pMsg.srchOptTxt_14.getValue());
        } else {
            bizMsg.invDt_TO.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_15.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.stkInDt_FR, pMsg.srchOptTxt_15.getValue());
        } else {
            bizMsg.stkInDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_16.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.stkInDt_TO, pMsg.srchOptTxt_16.getValue());
        } else {
            bizMsg.stkInDt_TO.clear();
        }
        // add start 2022/08/05 QC#59245
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CV, pMsg.srchOptTxt_17);
        // add end 2022/08/05 QC#59245
    }

    private void doProcess_NFBL1130Scrn00_TBLColumnSort(NFBL1130CMsg bizMsg, NFBL1130SMsg glblMsg) {

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            bizMsg.xxPageShowFromNum_A.setValue(1);
        }

        NFBL1130CommonLogic.pagination(bizMsg, glblMsg, 0);
    }
    // END 2016/10/05 J.Kim [QC#5521,ADD]
}