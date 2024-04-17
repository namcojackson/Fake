/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2060;


import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFBL2060.common.NFBL2060CommonLogic;
import business.blap.NFBL2060.constant.NFBL2060Constant;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
 * Invoice Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/05   Fujitsu         C.Tanaka        Update          QC#12951
 * 2016/08/30   Fujitsu         C.Tanaka        Update          QC#5521
 * 2016/09/30   Hitachi         T.Tsuchida      Update          QC#14498
 * 2016/10/03   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/24   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2018/03/26   Hitachi         Y.Takeno        Update          QC#22350
 * 2018/05/28   CITS            K.Ogino         Update          QC#25902
 * 2018/09/25   Fujitsu         S.Ohki          Update          QC#28441
 * 2018/10/18   Hitachi         J.Kim           Update          QC#28816
 * 2020/03/03   Fujitsu         H.Ikeda         Update          QC#53413
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 * 2024/02/05   Hitachi         S.Ikariya       Update          QC#63451
 * </pre>
 */
public class NFBL2060BL02 extends S21BusinessHandler implements NFBL2060Constant {

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
            NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
            NFBL2060SMsg glblMsg = (NFBL2060SMsg) sMsg;

            // +++++ [START] : Scrn00
            if ("NFBL2060_INIT".equals(screenAplID)) {
                doProcess_NFBL2060_INIT(cMsg, sMsg);
                // QC#5521 ADD Start
                // START 2018/09/25 [QC#28441, MOD]
//                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "AHEAD");
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "BHEAD");
                // END 2018/09/25 [QC#28441, MOD]
                // QC#5521 ADD End
            } else if ("NFBL2060Scrn00_OnClick_Search".equals(screenAplID)) {
                doProcess_NFBL2060Scrn00_Search(cMsg, sMsg);
            } else if ("NFBL2060Scrn00_OnClick_DetailedTabDownloadButton".equals(screenAplID)) {
                doProcess_NFBL2060Scrn00_OnClick_DetailedTabDownloadButton(cMsg, sMsg);
            } else if ("NFBL2060Scrn00_OnClick_SummaryTabDownloadButton".equals(screenAplID)) {
                doProcess_NFBL2060Scrn00_OnClick_SummaryTabDownloadButton(cMsg, sMsg);
            // QC#12951 ADD Start
            } else if ("NFBL2060Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFBL2060Scrn00_CMN_Download(cMsg, sMsg);
            // QC#12951 ADD End
            } else if ("NFBL2060Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFBL2060Scrn00_Clear(cMsg, sMsg);
            } else if ("NFBL2060Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFBL2060Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NFBL2060Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFBL2060Scrn00_PageNext(cMsg, sMsg);
            // QC#5521 ADD Start
            } else if ("NFBL2060Scrn00_TAB_Detailed".equals(screenAplID)) {
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "AHEAD");
            } else if ("NFBL2060Scrn00_TAB_Summary".equals(screenAplID)) {
                ZYPGUITableColumn.getColData(bizMsg, glblMsg, "BHEAD");
            } else if ("NFBL2060Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NFBL2060Scrn00_OnChangeSavedSearchOption(bizMsg, glblMsg);
            } else if ("NFBL2060Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFBL2060Scrn00_TBLColumnSort(bizMsg, glblMsg);
            // QC#5521 ADD End
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
     * Method name: doProcess_NFBL2060_INIT
     * <dd>The method explanation: Init
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2060_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2060_INIT================================START", this);
        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
        
        /** Create [Status] Pulldown. */
        ZYPCodeDataUtil.createPulldownList("ACCT_INV_STS", bizMsg.acctInvStsCd_C, bizMsg.acctInvStsDescTxt_D);
        /** Create [Source] Pulldown. */
        NFBL2060CommonLogic.createApInvCatgPulldownList(bizMsg);

        // QC#5521 ADD Start
        NFBL2060CommonLogic.createPulldownListSaveOpt(bizMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        // QC#5521 ADD End
        EZDDebugOutput.println(5, "doProcess_NFBL2060_INIT================================END", this);
    }
    
    /**
     * Method name: doProcess_NFBL2060Scrn00_Search
     * <dd>The method explanation: [Search] button event
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2060Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2060Scrn00_Search================================START", this);
        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
        NFBL2060SMsg globalMsg = (NFBL2060SMsg) sMsg;

        // START 2018/10/24 J.Kim [QC#28869,ADD]
        NFBL2060CommonLogic.outputSearchLog(bizMsg);
        // END 2018/10/24 J.Kim [QC#28869,ADD]

        /** Get Detail Record */
        NFBL2060CommonLogic.getDetailedTabRecord(bizMsg, globalMsg, true);
        NFBL2060CommonLogic.getSummaryTabRecord(bizMsg, globalMsg, true);
        // START 2020/03/03 [QC#53413, ADD]
        // mod start 2022/02/15 QC#57090
        //NFBL2060CommonLogic.chkPoNumCreate(bizMsg);
        NFBL2060CommonLogic.chkMultiPoOrMultiVndRtn(bizMsg);
        // mod end 2022/02/15 QC#57090
        // END   2020/03/03 [QC#53413, ADD]
        EZDDebugOutput.println(5, "doProcess_NFBL2060Scrn00_Search================================END", this);
    }

    /**
     * Method name: doProcess_NFBL2060Scrn00_Clear
     * <dd>The method explanation: Clear values.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2060Scrn00_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL2060Scrn00_Clear================================START", this);

        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
        NFBL2060SMsg globalMsg = (NFBL2060SMsg) sMsg;

        NFBL2060CommonLogic.clearHeader(bizMsg);
        NFBL2060CommonLogic.clearDetail(bizMsg, globalMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL2060Scrn00_Clear================================END", this);
    }

    /**
     * Method name: doProcess_NFBL2060Scrn00_PagePrev
     * <dd>The method explanation: Move to previous page.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2060Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL2060Scrn00_PagePrev================================START", this);

        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
        NFBL2060SMsg globalMsg = (NFBL2060SMsg) sMsg;

        if (TAB_DETAILED.equals(bizMsg.xxDplyTab.getValue())) {
            bizMsg.D.clear();
            bizMsg.xxPageShowFromNum_D.setValue(bizMsg.xxPageShowFromNum_D.getValueInt() - bizMsg.D.length() - 1);
            int pagenationFrom = bizMsg.xxPageShowFromNum_D.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + bizMsg.D.length(); i++) {
                if (i < globalMsg.D.getValidCount()) {
                    // copy data from SMsg onto CMsg
                    EZDMsg.copy(globalMsg.D.no(i), null, bizMsg.D.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            bizMsg.D.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            pagenationFrom = pagenationFrom + 1;
            bizMsg.xxPageShowFromNum_D.setValue(pagenationFrom);
            bizMsg.xxPageShowToNum_D.setValue(pagenationFrom + bizMsg.D.getValidCount() - 1);
        } else {
            bizMsg.S.clear();
            bizMsg.xxPageShowFromNum_S.setValue(bizMsg.xxPageShowFromNum_S.getValueInt() - bizMsg.S.length() - 1);
            int pagenationFrom = bizMsg.xxPageShowFromNum_S.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + bizMsg.S.length(); i++) {
                if (i < globalMsg.S.getValidCount()) {
                    // copy data from SMsg onto CMsg
                    EZDMsg.copy(globalMsg.S.no(i), null, bizMsg.S.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            bizMsg.S.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            pagenationFrom = pagenationFrom + 1;
            bizMsg.xxPageShowFromNum_S.setValue(pagenationFrom);
            bizMsg.xxPageShowToNum_S.setValue(pagenationFrom + bizMsg.S.getValidCount() - 1);
        }

        EZDDebugOutput.println(5, "doProcess_NFBL2060Scrn00_PagePrev================================END", this);
        return;

    }

    /**
     * Method name: doProcess_NFBL2060Scrn00_PageNext
     * <dd>The method explanation: Move to next page.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2060Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL2060Scrn00_PageNext================================START", this);

        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
        NFBL2060SMsg globalMsg = (NFBL2060SMsg) sMsg;

        if (TAB_DETAILED.equals(bizMsg.xxDplyTab.getValue())) {
            bizMsg.D.clear();
            bizMsg.xxPageShowFromNum_D.setValue(bizMsg.xxPageShowToNum_D.getValueInt());
            int pagenationFrom = bizMsg.xxPageShowFromNum_D.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + bizMsg.D.length(); i++) {
                if (i < globalMsg.D.getValidCount()) {
                    // copy data from SMsg onto CMsg
                    EZDMsg.copy(globalMsg.D.no(i), null, bizMsg.D.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            bizMsg.D.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            bizMsg.xxPageShowFromNum_D.setValue(pagenationFrom + 1);
            bizMsg.xxPageShowToNum_D.setValue(pagenationFrom + bizMsg.D.getValidCount());
        	
        } else {
            bizMsg.S.clear();
            bizMsg.xxPageShowFromNum_S.setValue(bizMsg.xxPageShowToNum_S.getValueInt());
            int pagenationFrom = bizMsg.xxPageShowFromNum_S.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + bizMsg.S.length(); i++) {
                if (i < globalMsg.S.getValidCount()) {
                    // copy data from SMsg onto CMsg
                    EZDMsg.copy(globalMsg.S.no(i), null, bizMsg.S.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            bizMsg.S.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            bizMsg.xxPageShowFromNum_S.setValue(pagenationFrom + 1);
            bizMsg.xxPageShowToNum_S.setValue(pagenationFrom + bizMsg.S.getValidCount());
        	
        }

        EZDDebugOutput.println(5, "doProcess_NFBL2060Scrn00_PageNext================================END", this);

        return;
    }

    /**
     * Method name: doProcess_NFBL2060Scrn00_OnClick_DetailedTabDownloadButton
     * <dd>The method explanation: CSV Download
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2060Scrn00_OnClick_DetailedTabDownloadButton(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL2060Scrn00_OnClick_DetailedTabDownloadButton================================START", this);

        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
        NFBL2060SMsg globalMsg = (NFBL2060SMsg) sMsg;

        /** Create CSV data */
        NFBL2060CommonLogic.csvDownLoadDetailedTab(bizMsg, globalMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL2060Scrn00_OnClick_DetailedTabDownloadButton================================END", this);

        return;
    }

    /**
     * Method name: doProcess_NFBL2060Scrn00_OnClick_SummaryTabDownloadButton
     * <dd>The method explanation: CSV Download
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2060Scrn00_OnClick_SummaryTabDownloadButton(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL2060Scrn00_OnClick_SummaryTabDownloadButton================================START", this);

        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
        NFBL2060SMsg globalMsg = (NFBL2060SMsg) sMsg;

        /** Create CSV data */
        NFBL2060CommonLogic.csvDownLoadSummaryTab(bizMsg, globalMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL2060Scrn00_OnClick_SummaryTabDownloadButton================================END", this);

        return;
    }
    
    // QC#12951 ADD Start
    /**
     * Method name: doProcess_NFBL2060Scrn00_CMN_Download
     * <dd>The method explanation: CSV Download
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2060Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
        
        if(TAB_DETAILED.equals(bizMsg.xxDplyTab.getValue())){
            doProcess_NFBL2060Scrn00_OnClick_DetailedTabDownloadButton(cMsg, sMsg);
        }else {
            doProcess_NFBL2060Scrn00_OnClick_SummaryTabDownloadButton(cMsg, sMsg);
        }
    }
    // QC#12951 ADD End

    // QC#5521 ADD Start
    private void doProcess_NFBL2060Scrn00_OnChangeSavedSearchOption(NFBL2060CMsg bizMsg, NFBL2060SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!NFBL2060CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.dplyVndNm, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.apVndCd, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvNum, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.acctInvStsCd_S, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.prntVndCd, pMsg.srchOptTxt_05);
        // START 2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum, pMsg.srchOptTxt_06);
        // END   2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        ZYPEZDItemValueSetter.setValue(bizMsg.delyOrdNum, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtTermDescTxt, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCmntTxt_FR, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCmntTxt_TO, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtMethDescTxt, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(bizMsg.apInvCatgCd_S, pMsg.srchOptTxt_16);
        ZYPEZDItemValueSetter.setValue(bizMsg.apPmtChkNum, pMsg.srchOptTxt_17);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_01, pMsg.srchOptTxt_20);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_02, pMsg.srchOptTxt_21);
        // START 2018/03/26 [QC#22350, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.dispPoDtlLineNum, pMsg.srchOptTxt_22);
        // END   2018/03/26 [QC#22350, ADD]
        // START 2024/02/05 S.Ikariya [QC#63451, ADD]
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_28.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.acInvTotCostAmt_FR, new BigDecimal(pMsg.srchOptTxt_28.getValue()));
        } else {
            bizMsg.acInvTotCostAmt_FR.clear();
        }
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_29.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.acInvTotCostAmt_TO, new BigDecimal(pMsg.srchOptTxt_29.getValue()));
        } else {
            bizMsg.acInvTotCostAmt_TO.clear();
        }
        // END 2024/02/05 S.Ikariya [QC#63451, ADD]

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_08.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invDt_FR, pMsg.srchOptTxt_08.getValue());
        } else {
            bizMsg.invDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_09.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invDt_TO, pMsg.srchOptTxt_09.getValue());
        } else {
            bizMsg.invDt_TO.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_10.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.poDt_FR, pMsg.srchOptTxt_08.getValue());
        } else {
            bizMsg.poDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_11.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.poDt_TO, pMsg.srchOptTxt_09.getValue());
        } else {
            bizMsg.poDt_TO.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_18.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.pmtDt_FR, pMsg.srchOptTxt_08.getValue());
        } else {
            bizMsg.pmtDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_19.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.pmtDt_TO, pMsg.srchOptTxt_19.getValue());
        } else {
            bizMsg.pmtDt_TO.clear();
        }
        // QC#25902
        ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum, pMsg.srchOptTxt_23);
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_24.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnSubmtDt_FR, pMsg.srchOptTxt_24.getValue());
        } else {
            bizMsg.vndRtrnSubmtDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_25.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnSubmtDt_TO, pMsg.srchOptTxt_25.getValue());
        } else {
            bizMsg.vndRtrnSubmtDt_TO.clear();
        }
        // QC#25902 End
        // START 2018/10/18 J.Kim [QC#28816,ADD]
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_26.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invAuthDt_FR, pMsg.srchOptTxt_26.getValue());
        } else {
            bizMsg.invAuthDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_27.getValue(), YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invAuthDt_TO, pMsg.srchOptTxt_27.getValue());
        } else {
            bizMsg.invAuthDt_TO.clear();
        }
        // END 2018/10/18 J.Kim [QC#28816,ADD]
    }

    private void doProcess_NFBL2060Scrn00_TBLColumnSort(NFBL2060CMsg bizMsg, NFBL2060SMsg glblMsg) {
 
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("D".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.D, glblMsg.D.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.D.getValidCount());

            bizMsg.xxPageShowFromNum_D.setValue(1);
        } else if ("S".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.S, glblMsg.S.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.S.getValidCount());

            bizMsg.xxPageShowFromNum_S.setValue(1);
        }

        NFBL2060CommonLogic.pagination(bizMsg, glblMsg, 0);
    }
    // QC#5521 ADD End
}

