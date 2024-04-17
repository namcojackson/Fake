/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1570;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCItem;
import parts.common.EZDCStringItem;
import parts.common.EZDFBigDecimalItem;
import parts.common.EZDFDateItem;
import parts.common.EZDFItem;
import parts.common.EZDFStringItem;
import parts.common.EZDItem;
import parts.common.EZDMsg;
import parts.common.EZDCMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.common.EZDTMsgArray;
import parts.dbcommon.EZDTBLAccessor;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

import business.blap.NWAL1570.common.NWAL1570CommonLogic;
import business.db.CCYTMsg;
import business.db.DPLY_BY_CONFIGTMsg;
import business.db.DPLY_BY_CONFIGTMsgArray;
import business.db.GLBL_CMPYTMsg;
import static business.blap.NWAL1570.constant.NWAL1570Constant.*;
import static business.blap.NWAL1570.constant.NWAL1570Constant.DPLY_BY_CONFIG;
import static business.blap.NWAL1570.constant.NWAL1570Constant.DPLY_MODE;
import static business.blap.NWAL1570.constant.NWAL1570Constant.RSLT_MODE;
import static business.blap.NWAL1570.constant.NWAL1570Constant.NWAM0786E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.concatString;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtil;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;

/**
 *<pre>
 * NWAL1570BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 * 2017/06/15   Fujitsu         H.Sugawara      Update          QC#17893
 * 2017/06/22   Fujitsu         H.Sugawara      Update          QC#17893
 * 2018/12/13   Fujitsu         Mz.Takahashi    Update          QC#29024
 * 2022/04/18   CITS            J.Evangelista   Update          QC#59934
 *</pre>
 */
public class NWAL1570BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1570CMsg bizMsg = (NWAL1570CMsg) cMsg;
            NWAL1570SMsg glblMsg = (NWAL1570SMsg) sMsg;

            if ("NWAL1570Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NWAL1570Scrn00_CMN_Download(bizMsg, glblMsg);

            // START 2022/04/18 J.Evangelista [QC#59934,DEL]
//            } else if ("NWAL1570Scrn00_CMN_Reset".equals(screenAplID)) {
//                doProcess_NWAL1570Scrn00_CMN_Reset(bizMsg, glblMsg);
            // END 2022/04/18 J.Evangelista [QC#59934,DEL]

            } else if ("NWAL1570Scrn00_SearchOrder".equals(screenAplID)) {
                doProcess_NWAL1570Scrn00_SearchOrder(bizMsg, glblMsg);
                getColDataByScrn(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn00_Select_ResultMode".equals(screenAplID)) {
                doProcess_NWAL1570Scrn00_Select_ResultMode(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn00_OnChange_SavedSearchOption".equals(screenAplID)) {
                doProcess_NWAL1570_OnChange_SavedSearchOption(bizMsg, glblMsg);

            } else if ("NWAL1570_INIT".equals(screenAplID)) {
                doProcess_NWAL1570_INIT(bizMsg, glblMsg);
                if (hasValue(bizMsg.xxRsltModeCd)) {
                    // search order
                    doProcess_NWAL1570Scrn00_SearchOrder(bizMsg, glblMsg);
                    getColDataByScrn(bizMsg, glblMsg);
                }

            } else if ("NWAL1570Scrn01_CMN_Download".equals(screenAplID)) {
                doProcess_NWAL1570Scrn01_CMN_Download(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn02_CMN_Download".equals(screenAplID)) {
                doProcess_NWAL1570Scrn02_CMN_Download(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn01_PageJump".equals(screenAplID)) {
                doProcess_NWAL1570Scrn01_PageJump(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn01_PageNext".equals(screenAplID)) {
                doProcess_NWAL1570Scrn01_PageNext(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn01_PagePrev".equals(screenAplID)) {
                doProcess_NWAL1570Scrn01_PagePrev(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn01_ReSearchOrder".equals(screenAplID)) {
                doProcess_NWAL1570_OnChange_SavedSearchOption(bizMsg, glblMsg);
                doProcess_NWAL1570Scrn01_ReSearchOrder(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn01_TransitionByContractNumber".equals(screenAplID)) {
                doProcess_NWAL1570Scrn01_TransitionByContractNumber(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn01_OnChange_SavedSearchOption".equals(screenAplID)) {
                doProcess_NWAL1570_OnChange_SavedSearchOption(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn01_CMN_Return".equals(screenAplID)) {
                doProcess_NWAL1570Scrn01_CMN_Return(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn02_PageJump".equals(screenAplID)) {
                doProcess_NWAL1570Scrn02_PageJump(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn02_PageNext".equals(screenAplID)) {
                doProcess_NWAL1570Scrn02_PageNext(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn02_PagePrev".equals(screenAplID)) {
                doProcess_NWAL1570Scrn02_PagePrev(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn02_ReSearchOrder".equals(screenAplID)) {
                doProcess_NWAL1570_OnChange_SavedSearchOption(bizMsg, glblMsg);
                doProcess_NWAL1570Scrn02_ReSearchOrder(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn02_toInquiryByStatus".equals(screenAplID)) {
                doProcess_NWAL1570Scrn02_toInquiryByStatus(bizMsg, glblMsg);

            } else if ("NWAL1570Scrn02_OnChange_SavedSearchOption".equals(screenAplID)) {
                doProcess_NWAL1570_OnChange_SavedSearchOption(bizMsg, glblMsg);

            } else if ("NWAL1570_NLBL0020".equals(screenAplID)) {
                doProcess_NWAL1570_NLBL0020(bizMsg, glblMsg);

            } else if ("NWAL1570_NLBL3080".equals(screenAplID)) {
                doProcess_NWAL1570_NLBL3080(bizMsg, glblMsg);

            } else if ("NWAL1570_NMAL6760".equals(screenAplID)) {
                doProcess_NWAL1570_NMAL6760(bizMsg, glblMsg);

            } else if ("NWAL1570_NMAL6800".equals(screenAplID)) {
                doProcess_NWAL1570_NMAL6800(bizMsg, glblMsg);

            } else if ("NWAL1570_NSAL0300".equals(screenAplID)) {
                doProcess_NWAL1570_NSAL0300(bizMsg, glblMsg);

            } else if ("NWAL1570_NSAL1240".equals(screenAplID)) {
                doProcess_NWAL1570_NSAL1240(bizMsg, glblMsg);

            } else if ("NWAL1570_NWAL1130".equals(screenAplID)) {
                doProcess_NWAL1570_NWAL1130(bizMsg, glblMsg);

            } else if ("NWAL1570_NWAL1500".equals(screenAplID)) {
                doProcess_NWAL1570_NWAL1500(bizMsg, glblMsg);

            } else if ("NWAL1570_NWAL2200".equals(screenAplID)) {
                doProcess_NWAL1570_NWAL2200(bizMsg, glblMsg);

            } else if ("NWAL1570_NMAL6870".equals(screenAplID)) {
                doProcess_NWAL1570_NMAL6870(bizMsg, glblMsg);

            } else if ("NWAL1570_NWCL0020".equals(screenAplID)) {
                doProcess_NWAL1570_NWCL0020(bizMsg, glblMsg);

            } else if ("NWAL1570_NYEL8810".equals(screenAplID)) {
                doProcess_NWAL1570_NYEL8810(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * getColDataByScrn Event
     * @param bizMsg NWAL1570CMsg
     * @param glblMsg NWAL1570SMsg
     */
    private void getColDataByScrn(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        if (RSLT_MODE.ORDER_INQUIRY.getRsltModeCd().equals(bizMsg.xxRsltModeCd.getValue())) {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, SCRN_TBL_ID_01);
        } else if (RSLT_MODE.STATUS_SUMMARY.getRsltModeCd().equals(bizMsg.xxRsltModeCd.getValue())) {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, SCRN_TBL_ID_02);
        }
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn00_CMN_Download(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {

        // 1. check organization structure.
        getDisplayByPairItem(bizMsg);
        setValue(bizMsg.dplyByItemLbNm_01, concatString("Display By", " ", bizMsg.dplyByItemLbNm_01.getValue()));
        // QC#11956 ADD Start
        // 2. get Product Line Label Name
        setProductLevelName(bizMsg, bizMsg.glblCmpyCd.getValue());
        // QC#11956 ADD End

        NWAL1570Query.getInstance().findOrderListForCsvDnld(bizMsg, N);
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    // START 2022/4/20 J.Evangelista [QC#59934,DEL]
//    private void doProcess_NWAL1570Scrn00_CMN_Reset(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
//        doProcess_NWAL1570_INIT(bizMsg, glblMsg);
//
//    }
    // END 2022/4/20 J.Evangelista [QC#59934,DEL]

    /**
     * SearchOrder Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn00_SearchOrder(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        if (glblMsg != null) {
            ZYPTableUtil.clear(glblMsg.A);
            glblMsg.clear();
        }
        bizMsg.dplyByItemLbNm_01.clear();
        bizMsg.dplyByItemLbNm_02.clear();
        bizMsg.dplyByItemLbNm_03.clear();

        // 0. check Return/Others
        checkDate(bizMsg, bizMsg.ordFromDt, bizMsg.ordToDt);
        checkDate(bizMsg, bizMsg.xxBookFromDt, bizMsg.xxBookToDt);
        checkDate(bizMsg, bizMsg.xxActlShipFromDt, bizMsg.xxActlShipToDt);
        checkDate(bizMsg, bizMsg.invFromDt, bizMsg.invToDt);
        checkDate(bizMsg, bizMsg.xxOrdSrcImptFromDt, bizMsg.xxOrdSrcImptToDt);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        // 1. check organization structure.
        getDisplayByPairItem(bizMsg);
        setValue(bizMsg.dplyByItemLbNm_01, concatString("Display By", " ", bizMsg.dplyByItemLbNm_01.getValue()));
        // QC#11956 ADD Start
        // 2. get Product Line Label Name
        setProductLevelName(bizMsg, bizMsg.glblCmpyCd.getValue());
        // QC#11956 ADD End
        // 3. save inquiry criteria to "AOM04" table.
        EZDMsg.copy(bizMsg, null, glblMsg, null);
        // 4. search order.
        NWAL1570Query.getInstance().findOrderList(bizMsg, glblMsg, glblMsg.A.length(), N);

        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

    }

    private static void checkDate(NWAL1570CMsg bizMsg, EZDCDateItem fromDt, EZDCDateItem toDt) {

        if (hasValue(fromDt) && hasValue(toDt)) {
            int maxRngDays = ZYPCodeDataUtil.getNumConstValue(NWAL1570_MAX_RNG_DAYS, bizMsg.glblCmpyCd.getValue()).intValue();
            if (S21CalendarUtil.getDistance(bizMsg.glblCmpyCd.getValue()
                    , fromDt.getValue(), toDt.getValue(), S21CalendarUtilConstants.CALENDAR_GENERAL) > maxRngDays) {
                // Mod Start 2017/06/15 QC#17893
                setCheckDateError(bizMsg, fromDt, toDt, maxRngDays);
                // Mod End 2017/06/15 QC#17893
            }
            // Add Start 2017/06/15 QC#17893
        } else if (hasValue(fromDt) && !hasValue(toDt)) {
            // Mod Start 2017/06/22 QC#17893
            //int maxRngDays = ZYPCodeDataUtil.getNumConstValue(NWAL1570_MAX_RNG_DAYS, bizMsg.glblCmpyCd.getValue()).intValue();
            //setCheckDateError(bizMsg, fromDt, toDt, maxRngDays);
            String strFromDate = fromDt.getValue();
            String strSalesDate = bizMsg.slsDt.getValue();

            int maxRngDays = ZYPCodeDataUtil.getNumConstValue(NWAL1570_MAX_RNG_DAYS, bizMsg.glblCmpyCd.getValue()).intValue();
            if (S21CalendarUtil.getDistance(bizMsg.glblCmpyCd.getValue()
                    , strFromDate, strSalesDate, S21CalendarUtilConstants.CALENDAR_GENERAL) > maxRngDays) {
                setCheckDateError(bizMsg, fromDt, toDt, maxRngDays);
            }
            // Mod End 2017/06/22 QC#17893
        } else if (!hasValue(fromDt) && hasValue(toDt)) {
            int maxRngDays = ZYPCodeDataUtil.getNumConstValue(NWAL1570_MAX_RNG_DAYS, bizMsg.glblCmpyCd.getValue()).intValue();
            setCheckDateError(bizMsg, fromDt, toDt, maxRngDays);
            // Add End 2017/06/15 QC#17893
        }
    }

    // Add Start 2017/06/15 QC#17893
    /**
     * @param bizMsg NWAL1570CMsg
     * @param fromDt EZDCDateItem
     * @param toDt EZDCDateItem
     * @param maxRngDays int
     */
    private static void setCheckDateError(NWAL1570CMsg bizMsg, EZDCDateItem fromDt, EZDCDateItem toDt, int maxRngDays) {
        fromDt.setErrorInfo(1, NWAM0786E, new String[] {String.valueOf(maxRngDays) });
        toDt.setErrorInfo(1, NWAM0786E, new String[] {String.valueOf(maxRngDays) });
        bizMsg.setMessageInfo(ZZM9037E);
    }
    // Add End 2017/06/15 QC#17893

    /**
     * Select_ResultMode Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn00_Select_ResultMode(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        //
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570_INIT(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        NWAL1570CommonLogic.setAuthority(bizMsg, userProfSvc);

        /**
         * Setting Initial Values.
         */
        // Common
        bizMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());

        List<String> funcList = new ArrayList<String>();
        for (int i = 0; i < bizMsg.F.length(); i++) {
            funcList.add(bizMsg.F.no(i).xxFuncId.getValue());
        }

        // Team/Zone
        if (funcList.contains(ALL_REF_AUTH)) {
            // nothing
        } else if (funcList.contains(REF_ONLY_TEAM_AUTH)) {
            NWAL1570CommonLogic.getOrdTeamZoneByUser(bizMsg, getContextUserInfo().getUserId());
        } else if (funcList.contains(REF_ONLY_SELF_RGTN_AUTH)) {
            NWAL1570CommonLogic.getOrdTeamZoneByUser(bizMsg, getContextUserInfo().getUserId());
            ZYPEZDItemValueSetter.setValue(bizMsg.xxCratByUsrSrchTxt, getContextUserInfo().getUserId());
        }

        // SearchOptions
        NWAL1570CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());

        // Return Reason
        ZYPCodeDataUtil.createPulldownList(RTRN_RSN.class, bizMsg.rtrnRsnCd_CD, bizMsg.rtrnRsnDescTxt_NM);

        // Delimiter
        bizMsg.xxSplCharTxt.setValue(ZYPCodeDataUtil.getVarCharConstValue(NWAL1570_SPL_CHAR_TXT, bizMsg.glblCmpyCd.getValue()));

        // 2018/12/13 QC#29024 Add Start
        // Real Time
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_DF, ZYPCodeDataUtil.getVarCharConstValue(NWAL1570_REALTIME_INQUIRY_FLG, bizMsg.glblCmpyCd.getValue()));
        // 2018/12/13 QC#29024 Add End

        // ++++++++++++++++++++++++++++++
        // + Status
        // ++++++++++++++++++++++++++++++
        int i = 0;
        // Header Status
        for (HDR_STS hdr : HDR_STS.values()) {
            setValue(bizMsg.H.no(i).ordHdrStsNm_HS, hdr.getHdrStsNm());
            for (int k = 0; k < bizMsg.V.length(); k++) {
                if (hdr.getHdrStsNm().equals(bizMsg.V.no(k).ordHdrStsNm_PR.getValue())) {
                    setValue(bizMsg.H.no(i).xxOrdHdrStsSelFlg_HS, Y);
                }
            }
            i++;
        }
        bizMsg.H.setValidCount(HDR_STS.values().length);

        i = 0;
        // Line Status
        for (LINE_STS line : LINE_STS.values()) {
            setValue(bizMsg.L.no(i).ordLineStsNm_LS, line.getLineStsNm());
            for (int k = 0; k < bizMsg.W.length(); k++) {
                if (line.getLineStsNm().equals(bizMsg.W.no(k).ordLineStsNm_PR.getValue())) {
                    setValue(bizMsg.L.no(i).xxLineStsSelFlg_LS, Y);
                }
            }
            i++;
        }
        bizMsg.L.setValidCount(LINE_STS.values().length);

        i = 0;
        // Return Line Status
        for (RTRN_LINE_STS rtrnLine : RTRN_LINE_STS.values()) {
            setValue(bizMsg.R.no(i).rtrnLineStsNm_RS, rtrnLine.getRtrnLineStsNm());
            for (int k = 0; k < bizMsg.X.length(); k++) {
                if (rtrnLine.getRtrnLineStsNm().equals(bizMsg.X.no(k).rtrnLineStsNm_PR.getValue())) {
                    setValue(bizMsg.R.no(i).xxRtrnLineStsSelFlg_RS, Y);
                }
            }
            i++;
        }
        bizMsg.R.setValidCount(RTRN_LINE_STS.values().length);

        // ++++++++++++++++++++++++++++++
        // + Display Option
        // ++++++++++++++++++++++++++++++
        // Display By - 1, 2, 3
        DPLY_BY_CONFIGTMsg reqTMsg = new DPLY_BY_CONFIGTMsg();
        reqTMsg.setSQLID("001");
        reqTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        reqTMsg.setConditionValue("trnstRteNum01", DPLY_BY_CONFIG.TRNST_RTE_NUM.getValue());
        reqTMsg.setConditionValue("trnstRteOrdNum01", DPLY_BY_CONFIG.TRNST_RTE_ORD_NUM.getValue());

        EZDTMsgArray resTMsgArray = EZDTBLAccessor.findByCondition(reqTMsg);

        if (resTMsgArray.length() > 0) {

            Map<String, String> tMsgKeys = new HashMap<String, String>();
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, DISP_BY_ITEM_NM);
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, DISP_BY_ITEM_LABEL);

            ZYPPulldownValueSetter.set(resTMsgArray, tMsgKeys, bizMsg.dplyBy01ItemNm_CD, bizMsg.dplyBy01ItemNm_NM);
            ZYPPulldownValueSetter.set(resTMsgArray, tMsgKeys, bizMsg.dplyBy02ItemNm_CD, bizMsg.dplyBy02ItemNm_NM);
            ZYPPulldownValueSetter.set(resTMsgArray, tMsgKeys, bizMsg.dplyBy03ItemNm_CD, bizMsg.dplyBy03ItemNm_NM);
        }

        i = 0;
        // Result Mode
        for (RSLT_MODE rsltMode : RSLT_MODE.values()) {
            setValue(bizMsg.xxRsltModeCd_CD.no(i), rsltMode.getRsltModeCd());
            setValue(bizMsg.xxRsltModeNm_NM.no(i), rsltMode.getRsltModeNm());
            i++;
        }

        i = 0;
        // Display Mode
        for (DPLY_MODE dplyMode : DPLY_MODE.values()) {
            setValue(bizMsg.grpByDnldCd_CD.no(i), dplyMode.getDplyModeCd());
            setValue(bizMsg.dplyByItemNm_DM.no(i), dplyMode.getDplyModeNm());
            i++;
        }

        // Other - Function Currency Digit Number
        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        glblTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        glblTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblTMsg);
        if (glblTMsg != null) {
            CCYTMsg ccyMsg = new CCYTMsg();
            ccyMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            ccyMsg.ccyCd.setValue(glblTMsg.stdCcyCd.getValue());
            ccyMsg = (CCYTMsg) EZDTBLAccessor.findByKey(ccyMsg);
            if (ccyMsg != null) {
                bizMsg.aftDeclPntDigitNum.setValue(ccyMsg.aftDeclPntDigitNum.getValueInt());
            }
        }
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn01_CMN_Download(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {

        // 1. check organization structure.
        getDisplayByPairItem(bizMsg);
        setValue(bizMsg.dplyByItemLbNm_01, concatString("Display By", " ", bizMsg.dplyByItemLbNm_01.getValue()));
        // QC#11956 ADD Start
        // 2. get Product Line Label Name
        setProductLevelName(bizMsg, bizMsg.glblCmpyCd.getValue());
        // QC#11956 ADD End

        NWAL1570Query.getInstance().findOrderListForCsvDnld(bizMsg, N);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn02_CMN_Download(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {

        // 1. check organization structure.
        getDisplayByPairItem(bizMsg);
        setValue(bizMsg.dplyByItemLbNm_01, concatString("Display By", " ", bizMsg.dplyByItemLbNm_01.getValue()));

        NWAL1570Query.getInstance().findOrderListForCsvDnld(bizMsg, N);
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn01_CMN_Return(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {

        doProcess_NWAL1570Scrn02_ReSearchOrder(bizMsg, glblMsg);
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn01_PageJump(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NWAL1570CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn01_PageNext(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWAL1570CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn01_PagePrev(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWAL1570CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * ReSearchOrder Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn01_ReSearchOrder(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        // search order
        doProcess_NWAL1570Scrn00_SearchOrder(bizMsg, glblMsg);
        getColDataByScrn(bizMsg, glblMsg);
    }

    /**
     * TransitionByContractNumber Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn01_TransitionByContractNumber(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        //
        NWAL1570CommonLogic.getDsContrPk(bizMsg);
    }

    /**
     * OnChange SavedSearchOption.
     * @param bizMsg
     * @param glblMsg
     */
    private void doProcess_NWAL1570_OnChange_SavedSearchOption(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_H1)) {
            NWAL1570CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn02_PageJump(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NWAL1570CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn02_PageNext(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWAL1570CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn02_PagePrev(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWAL1570CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * ReSearchOrder Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn02_ReSearchOrder(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        // search order
        doProcess_NWAL1570Scrn00_SearchOrder(bizMsg, glblMsg);
        getColDataByScrn(bizMsg, glblMsg);
    }

    /**
     * toInquiryByStatus Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570Scrn02_toInquiryByStatus(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        // search order
        doProcess_NWAL1570Scrn00_SearchOrder(bizMsg, glblMsg);
        getColDataByScrn(bizMsg, glblMsg);
    }

    /**
     * NLBL0020 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570_NLBL0020(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        //
    }

    /**
     * NLBL3080 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570_NLBL3080(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        //
    }

    /**
     * NMAL6760 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570_NMAL6760(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {

//        String scrEventNm = bizMsg.xxScrEventNm.getValue();
//        String dplyTab = bizMsg.xxDplyTab.getValue();
//        int slctLine = bizMsg.xxCellIdx.getValueInt();
//
//        if (BILL_EVENT_LIST.contains(scrEventNm)) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, NWAL1570CommonLogicForCustomer.cmbnAddr(bizMsg, SPACE));
//            ZYPEZDItemValueSetter.setValue(bizMsg.entBillToCustLocAddr, NWAL1570CommonLogicForCustomer.cmbnAddr(bizMsg, ENTER));
//            NWAL1570CommonLogicForCategory.deriveDefaultPmtTerm(bizMsg);
//            NWAL1570CommonLogicForCustomer.deriveDefaultBillToInfoForDtlFld(bizMsg);
//
//        } else if (SHIP_EVENT_LIST.contains(scrEventNm)) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, NWAL1570CommonLogicForCustomer.cmbnAddr(bizMsg, SPACE));
//            ZYPEZDItemValueSetter.setValue(bizMsg.entShipToCustLocAddr, NWAL1570CommonLogicForCustomer.cmbnAddr(bizMsg, ENTER));
//            ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
//            NWAL1570CommonLogicForCustomer.cmnProcForDeriveFromShipTo(bizMsg, glblMsg, false);
//
//        } else if (SOLD_EVENT_LIST.contains(scrEventNm)) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, NWAL1570CommonLogicForCustomer.cmbnAddr(bizMsg, SPACE));
//            ZYPEZDItemValueSetter.setValue(bizMsg.entSoldToCustLocAddr, NWAL1570CommonLogicForCustomer.cmbnAddr(bizMsg, ENTER));
//            NWAL1570CommonLogicForCustomer.cmnProcForDeriveFromSoldTo(bizMsg, glblMsg, false);
//        }
    }

    /**
     * NMAL6800 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570_NMAL6800(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        //
    }

    /**
     * NSAL0300 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570_NSAL0300(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        //
    }

    /**
     * NSAL1240 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570_NSAL1240(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        //
    }

    /**
     * NWAL1130 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570_NWAL1130(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        //
    }

    /**
     * NWAL1500 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570_NWAL1500(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        //
    }

    /**
     * NWAL2200 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570_NWAL2200(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        //
    }

    /**
     * NMAL6870 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570_NMAL6870(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        //
    }

    /**
     * NWCL0020 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570_NWCL0020(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        //
    }

    /**
     * NYEL8810 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1570_NYEL8810(NWAL1570CMsg bizMsg, NWAL1570SMsg glblMsg) {
        //
    }

    private void getDisplayByPairItem(NWAL1570CMsg cMsg) {

        DPLY_BY_CONFIGTMsg reqTMsg = new DPLY_BY_CONFIGTMsg();

        reqTMsg.setSQLID("002");
        reqTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        reqTMsg.setConditionValue("trnstRteNum01", DPLY_BY_CONFIG.TRNST_RTE_NUM.getValue());
        reqTMsg.setConditionValue("trnstRteOrdNum01", DPLY_BY_CONFIG.TRNST_RTE_ORD_NUM.getValue());
        reqTMsg.setConditionValue("dplyByItemNm01", cMsg.dplyBy01ItemNm.getValue());
        DPLY_BY_CONFIGTMsgArray resTMsgArray = (DPLY_BY_CONFIGTMsgArray) EZDTBLAccessor.findByCondition(reqTMsg);

        // if no record by dplyBy01ItemNm on DPLY_BY_CONFIG
        if (resTMsgArray.length() != 1) {
            cMsg.setMessageInfo(NWAM0350E);
            return;
        } else {
            cMsg.pairDplyByItemNm_01.setValue(resTMsgArray.no(0).pairDplyByItemNm.getValue());
            cMsg.dplyByItemLbNm_01.setValue(resTMsgArray.no(0).dplyByItemLbNm.getValue());
        }

        if (hasValue(cMsg.dplyBy02ItemNm)) {

            reqTMsg.setConditionValue("dplyByItemNm01", cMsg.dplyBy02ItemNm.getValue());
            resTMsgArray = (DPLY_BY_CONFIGTMsgArray) EZDTBLAccessor.findByCondition(reqTMsg);

            // if no record by dplyBy02ItemNm on DISP_BY_CONFIG
            if (resTMsgArray.length() == 0) {
                cMsg.setMessageInfo(NWAM0350E);
                return;
            } else {
                cMsg.pairDplyByItemNm_02.setValue(resTMsgArray.no(0).pairDplyByItemNm.getValue());
                cMsg.dplyByItemLbNm_02.setValue(resTMsgArray.no(0).dplyByItemLbNm.getValue());
            }
        }

        if (hasValue(cMsg.dplyBy03ItemNm)) {

            reqTMsg.setConditionValue("dplyByItemNm01", cMsg.dplyBy03ItemNm.getValue());
            resTMsgArray = (DPLY_BY_CONFIGTMsgArray) EZDTBLAccessor.findByCondition(reqTMsg);

            // if no record by dplyBy03ItemNm on DISP_BY_CONFIG
            if (resTMsgArray.length() == 0) {
                cMsg.setMessageInfo(NWAM0350E);
                return;
            } else {
                cMsg.pairDplyByItemNm_03.setValue(resTMsgArray.no(0).pairDplyByItemNm.getValue());
                cMsg.dplyByItemLbNm_03.setValue(resTMsgArray.no(0).dplyByItemLbNm.getValue());
            }

        }

    }

    // QC#11956 ADD Start
    private void setProductLevelName(NWAL1570CMsg bizMsg, String globalCompanyCode) {
        S21SsmEZDResult result = NWAL1570Query.getInstance().getProductLevelName(bizMsg, globalCompanyCode);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            for(Map<String, Object> map : list) {
                if (map != null && map.get("MDSE_STRU_ELMNT_TP_CD") != null) {
                    //PLG
                    if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L1, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                    //PL
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L2, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                    //PL2
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L3, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                    //PL3
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L4, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                    //PL4
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L5, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                    }
                }
            }
        }
    }
    // QC#11956 ADD End

    private BigDecimal getBigDecimal(Serializable obj) {
        if (obj == null) {
            return null;
        } else {
            if (obj instanceof String) {
                try {
                    return new BigDecimal((String) obj);
                } catch (NumberFormatException ex) {
                    return null;
                }
            } else if (obj instanceof BigDecimal) {
                return (BigDecimal) obj;
            } else if (obj instanceof Integer) {
                return new BigDecimal((Integer) obj);
            } else if (obj instanceof Long) {
                return new BigDecimal((Long) obj);
            } else if (obj instanceof Float) {
                return new BigDecimal((Float) obj);
            } else if (obj instanceof Double) {
                return new BigDecimal((Double) obj);
            } else {
                return null;
            }
        }
    }

   private void setValue(EZDItem item, Serializable value) {
        if (item instanceof EZDCItem) {
            if (item instanceof EZDCStringItem) {
                ZYPEZDItemValueSetter.setValue((EZDCStringItem) item, (String) value);
            } else if (item instanceof EZDCBigDecimalItem) {
                ZYPEZDItemValueSetter.setValue((EZDCBigDecimalItem) item, getBigDecimal(value));
            } else if (item instanceof EZDCDateItem) {
                ZYPEZDItemValueSetter.setValue((EZDCDateItem) item, (String) value);
            }
        } else if (item instanceof EZDSItem) {
            if (item instanceof EZDSStringItem) {
                ZYPEZDItemValueSetter.setValue((EZDSStringItem) item, (String) value);
            } else if (item instanceof EZDSBigDecimalItem) {
                ZYPEZDItemValueSetter.setValue((EZDSBigDecimalItem) item, getBigDecimal(value));
            } else if (item instanceof EZDSDateItem) {
                ZYPEZDItemValueSetter.setValue((EZDSDateItem) item, (String) value);
            }
        } else if (item instanceof EZDFItem) {
            if (item instanceof EZDFStringItem) {
                ZYPEZDItemValueSetter.setValue((EZDFStringItem) item, (String) value);
            } else if (item instanceof EZDFBigDecimalItem) {
                ZYPEZDItemValueSetter.setValue((EZDFBigDecimalItem) item, getBigDecimal(value));
            } else if (item instanceof EZDFDateItem) {
                ZYPEZDItemValueSetter.setValue((EZDFDateItem) item, (String) value);
            }
        }
    }

}
