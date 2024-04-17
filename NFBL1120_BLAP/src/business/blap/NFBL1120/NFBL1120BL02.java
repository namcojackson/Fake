/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL1120;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFBL1120.common.NFBL1120CommonLogic;
import business.blap.NFBL1120.constant.NFBL1120Constant;
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
 * AP Invoice Maintenance Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/13   Hitachi         K.Kojima        Update          QC#12725
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * </pre>
 */
public class NFBL1120BL02 extends S21BusinessHandler implements NFBL1120Constant {

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
            NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;
            NFBL1120SMsg glblMsg = (NFBL1120SMsg) sMsg;

            // +++++ [START] : Scrn00
            if ("NFBL1120_INIT".equals(screenAplID)) {
                doProcess_NFBL1120_INIT(cMsg, sMsg);
                // START 2016/10/05 J.Kim [QC#5521,ADD]
                ZYPGUITableColumn.getColData(bizMsg, glblMsg);
                // END 2016/10/05 J.Kim [QC#5521,ADD]
            } else if ("NFBL1120Scrn00_Search".equals(screenAplID)) {
                doProcess_NFBL1120Scrn00_Search(cMsg, sMsg);
            } else if ("NFBL1120Scrn00_OpenWin_PrntVnd".equals(screenAplID)) {
                doProcess_NFBL1120Scrn00_OpenWin_PrntVnd(cMsg, sMsg);
            } else if ("NFBL1120Scrn00_OpenWin_Vnd".equals(screenAplID)) {
                doProcess_NFBL1120Scrn00_OpenWin_Vnd(cMsg, sMsg);
            } else if ("NFBL1120Scrn00_SearchSupplierName".equals(screenAplID)) {
                doProcess_NFBL1120Scrn00_SearchSupplierName(cMsg, sMsg);
                // START 2016/09/13 K.Kojima [QC#12725,DEL]
                // } else if
                // ("NFBL1120Scrn00_SearchApproverName".equals(screenAplID))
                // {
                // doProcess_NFBL1120Scrn00_SearchApproverName(cMsg,
                // sMsg);
                // END 2016/09/13 K.Kojima [QC#12725,DEL]
            } else if ("NFBL1120Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFBL1120Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NFBL1120Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFBL1120Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NFBL1120Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFBL1120Scrn00_PageNext(cMsg, sMsg);
            // START 2016/10/05 J.Kim [QC#5521,ADD]
            } else if ("NFBL1120Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NFBL1120Scrn00_OnChangeSavedSearchOption(bizMsg, glblMsg);
            } else if ("NFBL1120Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFBL1120Scrn00_TBLColumnSort(bizMsg, glblMsg);
            // END 2016/10/05 J.Kim [QC#5521,ADD]
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
     * Method name: doProcess_NFBL1120_INIT
     * <dd>The method explanation: Init
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1120_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1120_INIT================================START", this);
        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;
        NFBL1120SMsg globalMsg = (NFBL1120SMsg) sMsg;
        String glblCmpyCd = getGlobalCompanyCode();

        // Create [Invoice Status] pulldown.
        NFBL1120CommonLogic.createInvoiceStatusPulldownList(bizMsg);

        // START 2016/09/13 K.Kojima [QC#12725,ADD]
        NFBL1120CommonLogic.createCurrentApproverPulldownList(bizMsg);
        // END 2016/09/13 K.Kojima [QC#12725,ADD]

        NFBL1120CommonLogic.createPulldownListSaveOpt(bizMsg, getContextUserInfo().getUserId(), glblCmpyCd);

        if (ZYPCommonFunc.hasValue(bizMsg.apBatNum)) {
            // Clear detail information
            NFBL1120CommonLogic.clearLines(bizMsg, globalMsg);
            // Search records
            NFBL1120CommonLogic.searchRecord(bizMsg, globalMsg);
        }

        EZDDebugOutput.println(5, "doProcess_NFBL1120_INIT================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1120Scrn00_Search
     * <dd>The method explanation: [Search] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1120Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1120Scrn00_Search================================START", this);
        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;
        NFBL1120SMsg globalMsg = (NFBL1120SMsg) sMsg;

        // Clear detail information
        NFBL1120CommonLogic.clearLines(bizMsg, globalMsg);
        // Search records
        NFBL1120CommonLogic.searchRecord(bizMsg, globalMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL1120Scrn00_Search================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1120Scrn00_OpenWin_PrntVnd
     * <dd>The method explanation: [Supplier] link event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1120Scrn00_OpenWin_PrntVnd(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1120Scrn00_OpenWin_PrntVnd================================START", this);
        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.locNm)) {
            if (!NFBL1120CommonLogic.checkLocNm(bizMsg)) {
                // START 2017/12/22 [QC#22831, MOD]
                bizMsg.locNm.setErrorInfo(1, NFBM0041E, new String[] {"Supplier Site Name" });
                // END   2017/12/22 [QC#22831, MOD]
                return;
            }
        }

        EZDDebugOutput.println(5, "doProcess_NFBL1120Scrn00_OpenWin_PrntVnd================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1120Scrn00_OpenWin_Vnd
     * <dd>The method explanation: [Supplier] link event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1120Scrn00_OpenWin_Vnd(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1120Scrn00_OpenWin_Vnd================================START", this);
        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.prntVndCd)) {
            if (!NFBL1120CommonLogic.checkPrntVnd(bizMsg)) {
                bizMsg.prntVndCd.setErrorInfo(1, NFBM0041E, new String[] {"Supplier" });
                return;
            }
        }

        EZDDebugOutput.println(5, "doProcess_NFBL1120Scrn00_OpenWin_Vnd================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1120Scrn00_SearchSupplierName
     * <dd>The method explanation: [>>] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1120Scrn00_SearchSupplierName(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1120Scrn00_SearchSupplierName================================START", this);
        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;

        NFBL1120CommonLogic.setPrntVndNm(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL1120Scrn00_SearchSupplierName================================END", this);
    }

    // START 2016/09/13 K.Kojima [QC#12725,DEL]
    // /**
    // * Method name: doProcess_NFBL1120Scrn00_SearchApproverName
    // * <dd>The method explanation: [>>] button event
    // * <dd>Remarks:
    // * @param cMsg Business Component Interface Message
    // * @param sMsg Global area information
    // */
    // private void
    // doProcess_NFBL1120Scrn00_SearchApproverName(EZDCMsg cMsg,
    // EZDSMsg sMsg) {
    // EZDDebugOutput.println(5,
    // "doProcess_NFBL1120Scrn00_SearchApproverName================================START",
    // this);
    // NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;
    //
    // NFBL1120CommonLogic.setApvrUsrNm(bizMsg);
    //
    // EZDDebugOutput.println(5,
    // "doProcess_NFBL1120Scrn00_SearchApproverName================================END",
    // this);
    // }
    // END 2016/09/13 K.Kojima [QC#12725,DEL]

    /**
     * Method name: doProcess_NFBL1120Scrn00_CMN_Clear
     * <dd>The method explanation: Clear values.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1120Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL1120Scrn00_CMN_Clear================================START", this);

        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;
        NFBL1120SMsg globalMsg = (NFBL1120SMsg) sMsg;

        // Clear information on HEADER information
        NFBL1120CommonLogic.clearHeader(bizMsg);
        // Clear information on LINES information
        NFBL1120CommonLogic.clearLines(bizMsg, globalMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL1120Scrn00_CMN_Clear================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1120Scrn00_PagePrev
     * <dd>The method explanation: Move to previous page.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1120Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL1120Scrn00_PagePrev================================START", this);

        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;
        NFBL1120SMsg globalMsg = (NFBL1120SMsg) sMsg;

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

        EZDDebugOutput.println(5, "doProcess_NFBL1120Scrn00_PagePrev================================END", this);
        return;

    }

    /**
     * Method name: doProcess_NFBL1120Scrn00_PageNext
     * <dd>The method explanation: Move to next page.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1120Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL1120Scrn00_PageNext================================START", this);

        NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;
        NFBL1120SMsg globalMsg = (NFBL1120SMsg) sMsg;

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

        EZDDebugOutput.println(5, "doProcess_NFBL1120Scrn00_PageNext================================END", this);

        return;
    }

    // START 2016/10/05 J.Kim [QC#5521,ADD]
    private void doProcess_NFBL1120Scrn00_OnChangeSavedSearchOption(NFBL1120CMsg bizMsg, NFBL1120SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!NFBL1120CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.apBatNum, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.prntVndCd, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, pMsg.srchOptTxt_03);
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_04.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.apBatDt, pMsg.srchOptTxt_04.getValue());
        } else {
            bizMsg.apBatDt.clear();
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.locNm, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.apMaintInvStsCd_S, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstNm_S, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.apInvNum, pMsg.srchOptTxt_08);
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_09.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invDt, pMsg.srchOptTxt_09.getValue());
        } else {
            bizMsg.invDt.clear();
        }
    }

    private void doProcess_NFBL1120Scrn00_TBLColumnSort(NFBL1120CMsg bizMsg, NFBL1120SMsg glblMsg) {

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

        NFBL1120CommonLogic.pagination(bizMsg, glblMsg, 0);
    }
    // END 2016/10/05 J.Kim [QC#5521,ADD]

}