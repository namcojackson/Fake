/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * -----------------------------------------------------------------------------------
 * 07/28/2009   Fujitsu         FAP)H.Aoki      Create          N/A
 * 10/27/2009   Fujitsu         FSOL)A.Akabane  Update          760
 * 10/29/2009   Fujitsu         T.Motoyama      Update          N/A
 * 11/03/2009   Fujitsu         T.Motoyama      Update          N/A
 * 12/29/2009   Fujitsu         A.Akabane       Update          N/A
 * 02/24/2010   Fujitsu         A.Akabane       Update          Workshop RQ No.233-237
 * 06/01/2010   Fujitsu         T.Ishii         Update          6860
 * 08/02/2010   Fujitsu         S.Yoshida       Update          79
 * 10/13/2010   Fujitsu         S.Yoshida       Update          N/A
 * 06/03/2013   Hitachi         T.Tomita        Update          QC1209
 * 10/29/2013   Hitachi         K.Kasai         Update          QC2852
 * 12/16/2016   CITS            T.Kikuhara      Update          QC15446
 *</pre>
 */
package business.blap.NLAL0070;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import business.blap.NLAL0070.common.NLAL0070CommonLogic;
import business.blap.NLAL0070.constant.NLAL0070Constant;
import business.db.ALL_MDSE_VTMsg;
import business.db.MDSETMsg;
import business.file.NLAL0070F00FMsg;
import business.file.NLAL0070F01FMsg;
import business.file.NLAL0070F02FMsg;

import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INLND_SHPG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LGSC_DELY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityProfile;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

public class NLAL0070BL02 extends S21BusinessHandler implements NLAL0070Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            // NLAL0070_INIT
            if ("NLAL0070_INIT".equals(screenAplID)) {
                doProcess_NLAL0070_INIT(cMsg, sMsg);

            // NLAL0070Scrn00_CMN_Download
            } else if ("NLAL0070Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NLAL0070Scrn00_CMN_Download(cMsg, sMsg);

            // NLAL0070Scrn00_CMN_Reset
            } else if ("NLAL0070Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NLAL0070Scrn00_CMN_Reset(cMsg, sMsg);

            // NLAL0070Scrn00_PageNext
            } else if ("NLAL0070Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLAL0070Scrn00_PageNext(cMsg, sMsg);

            // NLAL0070Scrn00_PagePrev
            } else if ("NLAL0070Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLAL0070Scrn00_PagePrev(cMsg, sMsg);

            // NLAL0070Scrn00_NextWeek
            } else if ("NLAL0070Scrn00_NextWeek".equals(screenAplID)) {
                doProcess_NLAL0070Scrn00_NextWeek(cMsg, sMsg);

            // NLAL0070Scrn00_PrevWeek
            } else if ("NLAL0070Scrn00_PrevWeek".equals(screenAplID)) {
                doProcess_NLAL0070Scrn00_PrevWeek(cMsg, sMsg);

            // NLAL0070Scrn00_Search
            } else if ("NLAL0070Scrn00_Search".equals(screenAplID)) {
                doProcess_NLAL0070Scrn00_Search(cMsg, sMsg);

            // NLAL0070_NLAL0140
            } else if ("NLAL0070_NLAL0140".equals(screenAplID)) {
                return;

            // NLAL0070Scrn00_CMN_Submit
            } else if ("NLAL0070Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLAL0070Scrn00_Submit(cMsg, sMsg);

            // NLAL0070Scrn00_TAB_Schedule
            } else if ("NLAL0070Scrn00_TAB_Schedule".equals(screenAplID)) {
                doProcess_NLAL0070Scrn00_TAB_Schedule(cMsg, sMsg);

            // NLAL0070Scrn00_TAB_Summary
            } else if ("NLAL0070Scrn00_TAB_Summary".equals(screenAplID)) {
                doProcess_NLAL0070Scrn00_TAB_Summary(cMsg, sMsg);

            // NLAL0070Scrn00_OnClick_MdseNmSearch
            } else if ("NLAL0070Scrn00_OnClick_MdseNmSearch".equals(screenAplID)) {
                doProcess_NLAL0070Scrn00_OnClick_MdseNmSearch(cMsg, sMsg);

            // NLAL0070_NMAL6050
            } else if ("NLAL0070_NMAL6050".equals(screenAplID)) {
                return;

            // 2013/06/03 QC1209 T.Tomita Add Start
            // NLAL0070Scrn00_OpenSubWin_Inventory_Location
            } else if ("NLAL0070Scrn00_OpenSubWin_Inventory_Location".equals(screenAplID)) {
                doProcess_NLAL0070Scrn00_OpenSubWin_Inventory_Location(cMsg);
            // 2013/06/03 QC1209 T.Tomita Add End
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL0070_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLAL0070_INIT================================START", this);

        NLAL0070CMsg bizMsg = (NLAL0070CMsg) cMsg;
        NLAL0070SMsg globalMsg = (NLAL0070SMsg) sMsg;

        // Get the security information of the user login data
        S21DataSecurityProfile dsProfile = getUserProfileService().getDataSecurityProfileFor(bizMsg.getBusinessID());
        // WH security attribute data to obtain a list of attribute
        // values

        // 2013/06/03 QC1209 T.Tomita Delete Start
//        // Create a WH list box
//        int result = NLXInboundLocation.createWhListForPullDown(bizMsg.glblCmpyCd_BK.getValue(), dsProfile, bizMsg.invtyLocCd, bizMsg.locNm, NLXInboundLocation.ALL_Y);
//
//        // Search failed
//        if (NLXInboundLocation.RESULT_OK == result) {
//
//            // WH initial values in the list box
//            bizMsg.invtyLocCd_P1.setValue(bizMsg.invtyLocCd.no(0).getValue());
//        } else {
//            // do nothing
//        }
        // 2013/06/03 QC1209 T.Tomita Delete End

        // Get the date operations
        String wkDate = ZYPDateUtil.getBatProcDate();
        bizMsg.xxOrdDt_F1.setValue(wkDate);
        bizMsg.xxOrdDt_T1.setValue(ZYPDateUtil.addDays(wkDate, AFTER_WEEK));

        List<S21DataSecurityAttributeData> attrDataList = dsProfile.getDataSecurityAttributeDataListFor(S21DataSecurityAttributeData.NAME_WAREHOUSE);

        if (attrDataList != null && !attrDataList.isEmpty()) {

            try {
                // QC#15446
                ZYPCodeDataUtil.createPulldownList(SCHD_TP.class, bizMsg.schdTpCd, bizMsg.xxScrItem61Txt, COLON);
            } catch (S21AbendException e) {
                // no process
            }
            bizMsg.schdTpCd_P1.clear();
            bizMsg.schdTpCd_P1.setValue("03");

            Map<String, Object> inMap = new HashMap<String, Object>();
            inMap.put("cMsg", bizMsg);
            inMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            inMap.put("vndTpCd", new String[] {VND_TP.CROSSDOCK, VND_TP.DIVERSION, VND_TP.TRANSLOADING });

            S21SsmEZDResult ssmRsltListCarrier = NLAL0070Query.getInstance().getPullDownListCarrier(inMap, globalMsg);

            if (ssmRsltListCarrier.isCodeNormal()) {

                int queryResCnt = ssmRsltListCarrier.getQueryResultCount();

                for (int i = 0; i < queryResCnt; i++) {
                    if (i >= bizMsg.carrCd.length()) {
                        break;
                    } else {
                        // no process
                    }
                    bizMsg.carrCd.no(i).setValue(globalMsg.C.no(i).carrCd_C1.getValue());
                    bizMsg.xxEdtCdNm.no(i).setValue(globalMsg.C.no(i).carrCd_C1.getValue() + COLON + globalMsg.C.no(i).carrNm_C1.getValue());
                }
            } else {
                // no process
            }

        } else {
            // do nothing
        }
        globalMsg.glblCmpyCd_BK.setValue(bizMsg.glblCmpyCd_BK.getValue());

        EZDDebugOutput.println(1, "doProcess_NLAL0070_INIT================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL0070Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_CMN_Download================================START", this);

        NLAL0070CMsg bizMsg = (NLAL0070CMsg) cMsg;
        NLAL0070SMsg globalMsg = (NLAL0070SMsg) sMsg;

        // 2013/06/03 QC1209 T.Tomita Add Start
        getInvtyLocNm(bizMsg);
        // 2013/06/03 QC1209 T.Tomita Add End

        if (TAB_NAME_SCHEDULE.equals(bizMsg.xxDplyTab_BK.getValue())) {
            if (RADIO_MDSE == bizMsg.xxRadioBtn_A1.getValueInt()) {
                getScheduleDownloadListForMdse(bizMsg, globalMsg);
            } else if (RADIO_CNTNR_INV == bizMsg.xxRadioBtn_A1.getValueInt()) {
                getScheduleDownloadListForCntnrInv(bizMsg, globalMsg);
            }
        } else if (TAB_NAME_SUMMARY.equals(bizMsg.xxDplyTab_BK.getValue())) {
            getSummaryDownloadList(bizMsg, globalMsg);
        }

        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_CMN_Download================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL0070Scrn00_CMN_Reset(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_CMN_Reset================================START", this);

        NLAL0070CMsg bizMsg = (NLAL0070CMsg) cMsg;
        NLAL0070SMsg globalMsg = (NLAL0070SMsg) sMsg;

        String glblCmpyCd = bizMsg.glblCmpyCd_BK.getValue();

        bizMsg.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.B.setValidCount(0);
        bizMsg.C.setValidCount(0);
        bizMsg.A.clear();
        bizMsg.B.clear();
        bizMsg.C.clear();

        bizMsg.glblCmpyCd_BK.setValue(glblCmpyCd);

        globalMsg.clear();
        globalMsg.A.setValidCount(0);
        globalMsg.B.setValidCount(0);
        globalMsg.C.setValidCount(0);
        globalMsg.D.setValidCount(0);
        globalMsg.G.setValidCount(0);
        globalMsg.H.setValidCount(0);
        globalMsg.A.clear();
        globalMsg.B.clear();
        globalMsg.C.clear();
        globalMsg.D.clear();
        globalMsg.G.clear();
        globalMsg.H.clear();

        // INIT call processing
        doProcess_NLAL0070_INIT(cMsg, sMsg);

        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_CMN_Reset================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL0070Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_PageNext================================START", this);

        NLAL0070CMsg bizMsg = (NLAL0070CMsg) cMsg;
        NLAL0070SMsg globalMsg = (NLAL0070SMsg) sMsg;

        if (TAB_NAME_SCHEDULE.equals(bizMsg.xxDplyTab_BK.getValue())) {

            NLAL0070CommonLogic.setScrnInputData(bizMsg, globalMsg);

            ZYPTableUtil.clear(bizMsg.A);
            bizMsg.xxPageShowFromNum_A1.setValue(bizMsg.xxPageShowToNum_A1.getValueInt());
            bizMsg.xxPageShowToNum_A1.clear();
            bizMsg.A.setValidCount(0);

            int pagenationFrom = bizMsg.xxPageShowFromNum_A1.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + bizMsg.A.length(); i++) {
                if (i < globalMsg.A.getValidCount()) {
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            bizMsg.A.setValidCount(i - pagenationFrom);
            bizMsg.xxPageShowFromNum_A1.setValue(pagenationFrom + 1);
            bizMsg.xxPageShowToNum_A1.setValue(pagenationFrom + bizMsg.A.getValidCount());

        } else if (TAB_NAME_SUMMARY.equals(bizMsg.xxDplyTab_BK.getValue())) {

            ZYPTableUtil.clear(bizMsg.B);
            bizMsg.xxPageShowFromNum_B1.setValue(bizMsg.xxPageShowToNum_B1.getValueInt());
            bizMsg.xxPageShowToNum_B1.clear();
            bizMsg.B.setValidCount(0);

            int pagenationFrom = bizMsg.xxPageShowFromNum_B1.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + bizMsg.B.length(); i++) {
                if (i < globalMsg.B.getValidCount()) {
                    EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            bizMsg.B.setValidCount(i - pagenationFrom);
            bizMsg.xxPageShowFromNum_B1.setValue(pagenationFrom + 1);
            bizMsg.xxPageShowToNum_B1.setValue(pagenationFrom + bizMsg.B.getValidCount());

        } else {
            // do nothing
        }

        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_PageNext================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL0070Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_PagePrev================================START", this);

        NLAL0070CMsg bizMsg = (NLAL0070CMsg) cMsg;
        NLAL0070SMsg globalMsg = (NLAL0070SMsg) sMsg;

        if (TAB_NAME_SCHEDULE.equals(bizMsg.xxDplyTab_BK.getValue())) {

            NLAL0070CommonLogic.setScrnInputData(bizMsg, globalMsg);

            ZYPTableUtil.clear(bizMsg.A);
            bizMsg.xxPageShowFromNum_A1.setValue(bizMsg.xxPageShowFromNum_A1.getValueInt() - bizMsg.A.length() - 1);
            bizMsg.xxPageShowToNum_A1.clear();
            bizMsg.A.setValidCount(0);

            int pagenationFrom = bizMsg.xxPageShowFromNum_A1.getValueInt();
            int i = pagenationFrom;

            for (; i < pagenationFrom + bizMsg.A.length(); i++) {
                if (i < globalMsg.A.getValidCount()) {
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }

            bizMsg.A.setValidCount(i - pagenationFrom);

            pagenationFrom = pagenationFrom + 1;
            bizMsg.xxPageShowFromNum_A1.setValue(pagenationFrom);
            bizMsg.xxPageShowToNum_A1.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

        } else if (TAB_NAME_SUMMARY.equals(bizMsg.xxDplyTab_BK.getValue())) {

            ZYPTableUtil.clear(bizMsg.B);
            bizMsg.xxPageShowFromNum_B1.setValue(bizMsg.xxPageShowFromNum_B1.getValueInt() - bizMsg.B.length() - 1);
            bizMsg.xxPageShowToNum_B1.clear();
            bizMsg.B.setValidCount(0);

            int pagenationFrom = bizMsg.xxPageShowFromNum_B1.getValueInt();
            int i = pagenationFrom;

            for (; i < pagenationFrom + bizMsg.B.length(); i++) {
                if (i < globalMsg.B.getValidCount()) {
                    EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            bizMsg.B.setValidCount(i - pagenationFrom);

            pagenationFrom = pagenationFrom + 1;
            bizMsg.xxPageShowFromNum_B1.setValue(pagenationFrom);
            bizMsg.xxPageShowToNum_B1.setValue(pagenationFrom + bizMsg.B.getValidCount() - 1);

        } else {
            // do nothing
        }

        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_PagePrev================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL0070Scrn00_NextWeek(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_NextWeek================================START", this);

        NLAL0070CMsg bizMsg = (NLAL0070CMsg) cMsg;
        NLAL0070SMsg globalMsg = (NLAL0070SMsg) sMsg;

        // Call setRefreshTabel
        getSummaryList(bizMsg, globalMsg, EVENT_TYPE_NEXTWEEK);

        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_NextWeek================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL0070Scrn00_PrevWeek(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_PrevWeek================================START", this);

        NLAL0070CMsg bizMsg = (NLAL0070CMsg) cMsg;
        NLAL0070SMsg globalMsg = (NLAL0070SMsg) sMsg;

        // Call setRefreshTabel
        getSummaryList(bizMsg, globalMsg, EVENT_TYPE_PREVWEEK);

        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_PrevWeek================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL0070Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_Search================================START", this);

        NLAL0070CMsg bizMsg = (NLAL0070CMsg) cMsg;
        NLAL0070SMsg globalMsg = (NLAL0070SMsg) sMsg;

        // 2013/06/03 QC1209 T.Tomita Add Start
        getInvtyLocNm(bizMsg);
        // 2013/06/03 QC1209 T.Tomita Add End

        if (TAB_NAME_SCHEDULE.equals(bizMsg.xxDplyTab_BK.getValue())) {
            getScheduleList(bizMsg, globalMsg, EVENT_TYPE_SEARCH);
        } else if (TAB_NAME_SUMMARY.equals(bizMsg.xxDplyTab_BK.getValue())) {
            getSummaryList(bizMsg, globalMsg, EVENT_TYPE_SEARCH);
        }

        // Search Successful MSG
        bizMsg.setMessageInfo("NZZM0002I", null);

        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_Search================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL0070Scrn00_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_Submit================================START", this);

        NLAL0070CMsg bizMsg = (NLAL0070CMsg) cMsg;
        NLAL0070SMsg globalMsg = (NLAL0070SMsg) sMsg;

        setDispDataForUpdate(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_Submit================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL0070Scrn00_TAB_Schedule(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_TAB_Schedule================================START", this);

        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_TAB_Schedule================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL0070Scrn00_TAB_Summary(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_TAB_Summary================================START", this);

        NLAL0070CMsg bizMsg = (NLAL0070CMsg) cMsg;
        NLAL0070SMsg globalMsg = (NLAL0070SMsg) sMsg;

        getSummaryList(bizMsg, globalMsg, EVENT_TYPE_SEARCH);

        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_TAB_Summary================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL0070Scrn00_OnClick_MdseNmSearch(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "NLAL0070Scrn00_OnClick_MdseNmSearch================================START", this);

        NLAL0070CMsg bizMsg = (NLAL0070CMsg) cMsg;
        NLAL0070SMsg globalMsg = (NLAL0070SMsg) sMsg;

        globalMsg.mdseCd_P1.setValue(bizMsg.mdseCd_P1.getValue());

        MDSETMsg inMDSEMsg = new MDSETMsg();

        ALL_MDSE_VTMsg outMDSEMsg = NLAL0070CommonLogic.findMdseInfo(bizMsg);

        if (outMDSEMsg == null) {
            bizMsg.mdseDescShortTxt.clear();
            globalMsg.mdseDescShortTxt.clear();
        } else {
            bizMsg.mdseDescShortTxt.setValue(outMDSEMsg.mdseDescShortTxt.getValue());
        }

        EZDDebugOutput.println(1, "NLAL0070Scrn00_OnClick_MdseNmSearch================================END", this);
    }

    // 2013/06/03 QC1209 T.Tomita Add Start
    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NLAL0070Scrn00_OpenSubWin_Inventory_Location(EZDCMsg cMsg) {
        NLAL0070CMsg bizMsg = (NLAL0070CMsg) cMsg;
        String locRoleTypeArr = NMXC100001EnableWH.getLocRoleTpForPopup(bizMsg.glblCmpyCd_BK.getValue(), BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLocRoleTpCdListTxt, locRoleTypeArr);
    }
    // 2013/06/03 QC1209 T.Tomita Add End
    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLAL0070CMsg
     * @param globalMsg NLAL0070SMsg
     */
    @SuppressWarnings("unchecked")
    private void getScheduleList(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg, int eventType) {

        initializeSchedule(bizMsg, globalMsg);

        if (eventType == EVENT_TYPE_SEARCH) {
            initializeScheduleForSearch(bizMsg, globalMsg);
        }

        // Schedule Table for each set of records to create the List
        // Box
        Map<String, Object> apptCarrMap = new HashMap<String, Object>();

        apptCarrMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        apptCarrMap.put("vndTpCd", VND_TP.DRAYAGE_CARRIER);
        apptCarrMap.put("cMsg", bizMsg);

        // Drayage Carrier to get the value of the components listbox
        // SQL calls
        S21SsmEZDResult drayageCarrierSsmResult = NLAL0070Query.getInstance().getApptCarrDrygList(apptCarrMap, globalMsg);
        // Present results
        if (drayageCarrierSsmResult.isCodeNormal()) {
            int queryResCnt = drayageCarrierSsmResult.getQueryResultCount();

            for (int i = 0; i < queryResCnt; i++) {
                    bizMsg.apptDrygCarrCd_A1.no(i).setValue(globalMsg.D.no(i).apptDrygCarrCd_D1.getValue());
                    bizMsg.xxEdtCdNm_A1.no(i).setValue(globalMsg.D.no(i).apptDrygCarrCd_D1.getValue() + COLON + globalMsg.D.no(i).locNm_D1.getValue());
            }
        }

        Map<String, Object> whSchdMsp = new HashMap<String, Object>();

        setParamForSchedule(bizMsg, globalMsg, whSchdMsp);

        // WH Schedule to get the value of the components listbox
        // SQL calls
        S21SsmEZDResult whScheduleSsmResult = NLAL0070Query.getInstance().getScheduleList(whSchdMsp, globalMsg);

        if (whScheduleSsmResult.isCodeNormal()) {

            // Input Mdse Name When user input Mdse Code for Search
            bizMsg.mdseDescShortTxt.clear();
            if (ZYPCommonFunc.hasValue(bizMsg.mdseCd_P1)) {
                bizMsg.mdseDescShortTxt.setValue(globalMsg.A.no(0).mdseDescShortTxt_A1.getValue());
            }

            List<Map> resAry = (List<Map>) whScheduleSsmResult.getResultObject();
            String invtyLocCd = "";
            String rwsRefNum  = "";
            int jsCnt = 0;  // JSMsg
            int asCnt = 0;  // ASMsg
            int abCnt = 0;  // ABMsg
            boolean overRimitFlg = false;
            for (Map resMap : resAry) {

                if (asCnt >= globalMsg.A.length()
                        || jsCnt >= globalMsg.J.length()) {
                    overRimitFlg = true;
                    break;
                }

                ZYPEZDItemValueSetter.setValue(globalMsg.J.no(jsCnt).ezUpTime_J1,          (String) resMap.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(globalMsg.J.no(jsCnt).invtyLocCd_J1,        (String) resMap.get("INVTY_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(globalMsg.J.no(jsCnt).rwsRefNum_J1,         (String) resMap.get("RWS_REF_NUM"));
                ZYPEZDItemValueSetter.setValue(globalMsg.J.no(jsCnt).whSchdRefKeyNumSq_J1, (BigDecimal) resMap.get("WH_SCHD_REF_KEY_NUM_SQ"));
                jsCnt++;

                if (!invtyLocCd.equals((String) resMap.get("INVTY_LOC_CD"))
                        || !rwsRefNum.equals((String) resMap.get("RWS_REF_NUM"))) {
                    invtyLocCd = (String) resMap.get("INVTY_LOC_CD");
                    rwsRefNum  = (String) resMap.get("RWS_REF_NUM");
                } else {
                    continue;
                }

                // Set ASMsg from SSM results.
                setASMsg(resMap, globalMsg.A.no(asCnt));

                boolean scheduleCheckFlg = false;

                // Set Schedule ETA Check Flag
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(asCnt).schdWhInEtaDt_A1)) {
                    globalMsg.A.no(asCnt).schdEtaChkFlg_A1.setValue(ZYPConstant.CHKBOX_ON_Y);
                    scheduleCheckFlg = true;
                } else {
                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(asCnt).tempWhInEtaDt_A1)
                            && ZYPCommonFunc.hasValue(globalMsg.A.no(asCnt).imptTrmToWhDaysAot_A1)) {
                        String trmEtaDtPlusLT = ZYPDateUtil.addDays(globalMsg.A.no(asCnt).tempWhInEtaDt_A1.getValue()
                                , globalMsg.A.no(asCnt).imptTrmToWhDaysAot_A1.getValue().intValue());
                        // Set Temporary Date for WH Schedule
                        globalMsg.A.no(asCnt).schdWhInEtaDt_A1.setValue(trmEtaDtPlusLT);
                    } else if (ZYPCommonFunc.hasValue(globalMsg.A.no(asCnt).tempWhInEtaDt_A1)
                            && !ZYPCommonFunc.hasValue(globalMsg.A.no(asCnt).imptTrmToWhDaysAot_A1)) {
                        globalMsg.A.no(asCnt).schdWhInEtaDt_A1.setValue(globalMsg.A.no(asCnt).tempWhInEtaDt_A1.getValue());
                    } else {
                        // Set latest Date for WH Schedule
                        globalMsg.A.no(asCnt).schdWhInEtaDt_A1.setValue(globalMsg.A.no(asCnt).ltstWhInEtaDt_A1.getValue());
                    }
                }

                // Set Final Schedule Check Flag
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(asCnt).finalWhInEtaDt_A1.getValue())) {
                    globalMsg.A.no(asCnt).finalEtaChkFlg_A1.setValue(ZYPConstant.CHKBOX_ON_Y);
                } else {
                    // Set latest Date for Final WH Schedule
                    if (scheduleCheckFlg) {
                        globalMsg.A.no(asCnt).finalWhInEtaDt_A1.setValue(globalMsg.A.no(asCnt).ltstWhInEtaDt_A1.getValue());
                    } else {
                        // do nothing
                    }
                }

                // If LGSC_DELY_CD equals "01", convert "NEF" to "" for LGSC_DELY_NM
                if (LGSC_DELY_TP.DEF.equals(globalMsg.A.no(asCnt).lgscDelyTpCd_A1.getValue())) {
                    globalMsg.A.no(asCnt).lgscDelyTpNm_A1.clear();
                }

                // If INLND_SHPG_METH_CD equals "", convert "" to "RA" for INLND_SHPG_METH_CD
                if (!SCHD_TP.AIR.equals(globalMsg.A.no(asCnt).schdTpCd_A1.getValue())
                        && !SCHD_TP.DOMESTIC.equals(globalMsg.A.no(asCnt).schdTpCd_A1.getValue())) {
                    if (!ZYPCommonFunc.hasValue(globalMsg.A.no(asCnt).inlndShpgMethCd_A1)) {
                        globalMsg.A.no(asCnt).inlndShpgMethCd_A1.setValue(INLND_SHPG_METH.RAIL);
                    }
                }

                // If RWS_STS_CD equals "99", Delete RWS Infomation
                if (RWS_STS.CANCELED.equals(globalMsg.A.no(asCnt).rwsStsCd_A1.getValue())) {
                    globalMsg.A.no(asCnt).rwsRefNum_A2.clear();
                }

                if (asCnt < bizMsg.A.length()) {
                    EZDMsg.copy(globalMsg.A.no(asCnt), null, bizMsg.A.no(abCnt), null);
                    abCnt++;
                }

                asCnt++;
            }

            if (overRimitFlg) {
                bizMsg.setMessageInfo("NZZM0001W", null);
            }

            globalMsg.J.setValidCount(jsCnt);
            globalMsg.A.setValidCount(asCnt);
            bizMsg.A.setValidCount(abCnt);

            // Simply choose a page to the item page breaks.
            bizMsg.xxPageShowFromNum_A1.setValue(BigDecimal.ONE);
            bizMsg.xxPageShowToNum_A1.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_A1.setValue(globalMsg.A.getValidCount());

        } else {

            if (!"NLAL0070Scrn00_CMN_Submit".equals(bizMsg.getScreenAplID())) {

                bizMsg.setMessageInfo("NZZM0000E");

            } else {
                // no process
            }
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param resMap Map
     * @param aSMsg  NLAL0070_ASMsg
     */
    private void setASMsg(Map resMap, NLAL0070_ASMsg aSMsg) {
        ZYPEZDItemValueSetter.setValue(aSMsg.whSchdRefKeyNumSq_A1,  (BigDecimal) resMap.get("WH_SCHD_REF_KEY_NUM_SQ"));
        ZYPEZDItemValueSetter.setValue(aSMsg.invtyLocCd_A1,         (String) resMap.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(aSMsg.rwsRefNum_A1,          (String) resMap.get("RWS_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(aSMsg.imptCntnrLotNum_A1,    (String) resMap.get("IMPT_CNTNR_LOT_NUM"));
        ZYPEZDItemValueSetter.setValue(aSMsg.asnNum_A1,             (String) resMap.get("ASN_NUM"));
        ZYPEZDItemValueSetter.setValue(aSMsg.lgscDelyTpCd_A1,       (String) resMap.get("LGSC_DELY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(aSMsg.lgscDelyTpNm_A1,       (String) resMap.get("LGSC_DELY_TP_NM"));
        ZYPEZDItemValueSetter.setValue(aSMsg.whInPrtyFlg_A1,        (String) resMap.get("WH_IN_PRTY_FLG"));
        ZYPEZDItemValueSetter.setValue(aSMsg.inbdVisEventCd_A1,     (String) resMap.get("INBD_VIS_EVENT_CD"));
        ZYPEZDItemValueSetter.setValue(aSMsg.inbdVisEventNm_A1,     (String) resMap.get("INBD_VIS_EVENT_NM"));
        ZYPEZDItemValueSetter.setValue(aSMsg.imptTrmCd_A1,          (String) resMap.get("IMPT_TRM_CD"));
        ZYPEZDItemValueSetter.setValue(aSMsg.imptTrmEtaDt_A1,       (String) resMap.get("IMPT_TRM_ETA_DT"));
        ZYPEZDItemValueSetter.setValue(aSMsg.ltstWhInEtaDt_A1,      (String) resMap.get("LTST_WH_IN_ETA_DT"));
        ZYPEZDItemValueSetter.setValue(aSMsg.tempWhInEtaDt_A1,      (String) resMap.get("TEMP_WH_IN_ETA_DT"));
        ZYPEZDItemValueSetter.setValue(aSMsg.schdWhInEtaDt_A1,      (String) resMap.get("SCHD_WH_IN_ETA_DT"));
        ZYPEZDItemValueSetter.setValue(aSMsg.finalWhInEtaDt_A1,     (String) resMap.get("FINAL_WH_IN_ETA_DT"));
        ZYPEZDItemValueSetter.setValue(aSMsg.railAvalDt_A1,         (String) resMap.get("RAIL_AVAL_DT"));
        ZYPEZDItemValueSetter.setValue(aSMsg.apptTmTxt_A1,          (String) resMap.get("APPT_TM_TXT"));
        ZYPEZDItemValueSetter.setValue(aSMsg.apptDrygCarrCd_A2,     (String) resMap.get("APPT_DRYG_CARR_CD"));
        ZYPEZDItemValueSetter.setValue(aSMsg.mdseCd_A1,             (String) resMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(aSMsg.mdseDescShortTxt_A1,   (String) resMap.get("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(aSMsg.reqStkInQty_A1,        (BigDecimal) resMap.get("REQ_STK_IN_QTY"));
        ZYPEZDItemValueSetter.setValue(aSMsg.qtyPkgApvlStsCd_A1,    (String) resMap.get("MASTER_FLG"));
        ZYPEZDItemValueSetter.setValue(aSMsg.carrCd_A1,             (String) resMap.get("CARR_CD"));
        ZYPEZDItemValueSetter.setValue(aSMsg.locNm_A1,              (String) resMap.get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(aSMsg.imptInvVeslNm_A1,      (String) resMap.get("IMPT_INV_VESL_NM"));
        ZYPEZDItemValueSetter.setValue(aSMsg.imptInvBolNum_A1,      (String) resMap.get("IMPT_INV_BOL_NUM"));
        ZYPEZDItemValueSetter.setValue(aSMsg.rwsRefNum_A2,          (String) resMap.get("RWS_REF_NUM_A2"));
        ZYPEZDItemValueSetter.setValue(aSMsg.rwsStsCd_A1,           (String) resMap.get("RWS_STS_CD"));
        ZYPEZDItemValueSetter.setValue(aSMsg.sceOrdTpCd_A1,         (String) resMap.get("SCE_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(aSMsg.schdTpCd_A1,           (String) resMap.get("SCHD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(aSMsg.schdTpNm_A1,           (String) resMap.get("SCHD_TP_NM"));
        ZYPEZDItemValueSetter.setValue(aSMsg.orgWhInEtaDt_A1,       (String) resMap.get("ORG_WH_IN_ETA_DT"));
        ZYPEZDItemValueSetter.setValue(aSMsg.imptTrmNm_A1,          (String) resMap.get("IMPT_TRM_NM"));
        ZYPEZDItemValueSetter.setValue(aSMsg.whCd_A1,               (String) resMap.get("MULTI_WH_CD"));
        ZYPEZDItemValueSetter.setValue(aSMsg.schdWhInEtaDt_A2,      (String) resMap.get("SCHD_DT"));
        ZYPEZDItemValueSetter.setValue(aSMsg.finalWhInEtaDt_A2,     (String) resMap.get("FINAL_DT"));
        ZYPEZDItemValueSetter.setValue(aSMsg.whSchdCmntTxt_A1,      (String) resMap.get("WH_SCHD_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(aSMsg.ezUpTime_A1,           (String) resMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(aSMsg.imptInvLclFlg_A1,      (String) resMap.get("IMPT_INV_LCL_FLG"));
        ZYPEZDItemValueSetter.setValue(aSMsg.imptInvNum_A1,         (String) resMap.get("IMPT_INV_NUM"));
        ZYPEZDItemValueSetter.setValue(aSMsg.imptInvCnsgnCd_A1,     (String) resMap.get("IMPT_INV_CNSGN_CD"));
        ZYPEZDItemValueSetter.setValue(aSMsg.imptCntnrNum_A1,       (String) resMap.get("IMPT_CNTNR_NUM"));
        ZYPEZDItemValueSetter.setValue(aSMsg.inlndShpgMethCd_A1,    (String) resMap.get("INLND_SHPG_METH_CD"));
        ZYPEZDItemValueSetter.setValue(aSMsg.shpgMethTpCd_A1,       (String) resMap.get("SHPG_METH_TP_CD"));
        ZYPEZDItemValueSetter.setValue(aSMsg.imptTrmToWhDaysAot_A1, (BigDecimal) resMap.get("IMPT_TRM_TO_WH_DAYS_AOT"));
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLAL0070CMsg
     * @param globalMsg NLAL0070SMsg
     */
    private void getSummaryList(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg, int eventType) {

        initializeSummary(bizMsg, globalMsg);

        Map<String, Object> whSmryMap = new HashMap<String, Object>();

        String whEtaStart = bizMsg.xxOrdDt_F1.getValue();
        if (eventType == EVENT_TYPE_SEARCH) {
            globalMsg.xxFromDt_B1.setValue(whEtaStart);
        }
        if (eventType == EVENT_TYPE_NEXTWEEK || eventType == EVENT_TYPE_PREVWEEK) {
            whEtaStart = ZYPDateUtil.addDays(globalMsg.xxFromDt_B1.getValue(), eventType);
        }
        globalMsg.xxFromDt_B1.setValue(whEtaStart);
        whSmryMap.put("whEtaStart", whEtaStart);
        whSmryMap.put("whEtaEnd", ZYPDateUtil.addDays(whEtaStart, AFTER_6DAYS));

        setCommonParamForSummary(bizMsg, globalMsg, whSmryMap);

        // WH Schedule Summary to get the value of the components listbox
        // SQL calls
        S21SsmEZDResult summarySsmResult = NLAL0070Query.getInstance().getSummaryList(whSmryMap);

        int queryResCnt = summarySsmResult.getQueryResultCount();
        if (queryResCnt > globalMsg.B.length()) {
            bizMsg.setMessageInfo("NZZM0001W", null);
        } else {
            // do nothing
        }
        List summaryInfoList = (List) summarySsmResult.getResultObject();

        String day1 = whEtaStart;
        String day2 = ZYPDateUtil.addDays(whEtaStart, AFTER_1DAY);
        String day3 = ZYPDateUtil.addDays(whEtaStart, AFTER_2DAYS);
        String day4 = ZYPDateUtil.addDays(whEtaStart, AFTER_3DAYS);
        String day5 = ZYPDateUtil.addDays(whEtaStart, AFTER_4DAYS);
        String day6 = ZYPDateUtil.addDays(whEtaStart, AFTER_5DAYS);
        String day7 = ZYPDateUtil.addDays(whEtaStart, AFTER_6DAYS);

        bizMsg.xxRefTblDtTxt_B1.setValue(getDateAndDay(day1));
        bizMsg.xxRefTblDtTxt_B2.setValue(getDateAndDay(day2));
        bizMsg.xxRefTblDtTxt_B3.setValue(getDateAndDay(day3));
        bizMsg.xxRefTblDtTxt_B4.setValue(getDateAndDay(day4));
        bizMsg.xxRefTblDtTxt_B5.setValue(getDateAndDay(day5));
        bizMsg.xxRefTblDtTxt_B6.setValue(getDateAndDay(day6));
        bizMsg.xxRefTblDtTxt_B7.setValue(getDateAndDay(day7));

        if (!summaryInfoList.isEmpty()) {

            String invtyLocCd = "";
            String schdTpCd = "";
            int num = -1;

            for (int i = 0; i < summaryInfoList.size(); i++) {

                NLAL0070_BSMsg summaryInfo = (NLAL0070_BSMsg) summaryInfoList.get(i);

                if (!(invtyLocCd.equals(summaryInfo.invtyLocCd_B1.getValue())
                        && schdTpCd.equals(summaryInfo.schdTpCd_B1.getValue()))) {
                    invtyLocCd = summaryInfo.invtyLocCd_B1.getValue();
                    schdTpCd = summaryInfo.schdTpCd_B1.getValue();
                    num++;

                    globalMsg.B.no(num).invtyLocCd_B1.setValue(summaryInfo.invtyLocCd_B1.getValue());
                    globalMsg.B.no(num).schdTpCd_B1.setValue(summaryInfo.schdTpCd_B1.getValue());
                    globalMsg.B.no(num).schdTpNm_B1.setValue(summaryInfo.schdTpNm_B1.getValue());
                } else {
                    // do nothing
                }

                if (day1.equals(summaryInfo.ltstWhInEtaDt_B1.getValue())) {

                    if (RWS_QTY_MAX >= summaryInfo.rwsQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).rwsQty_B1.setValue(summaryInfo.rwsQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).rwsQty_B1.setValue(RWS_QTY_MAX);
                    }

                    if (PALLET_QTY_MAX >= summaryInfo.pltQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).pltQty_B1.setValue(summaryInfo.pltQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).pltQty_B1.setValue(PALLET_QTY_MAX);
                    }

                    if (CASE_QTY_MAX >= summaryInfo.cseQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).cseQty_B1.setValue(summaryInfo.cseQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).cseQty_B1.setValue(CASE_QTY_MAX);
                    }

                } else if (day2.equals(summaryInfo.ltstWhInEtaDt_B1.getValue())) {

                    if (RWS_QTY_MAX >= summaryInfo.rwsQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).rwsQty_B2.setValue(summaryInfo.rwsQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).rwsQty_B2.setValue(RWS_QTY_MAX);
                    }

                    if (PALLET_QTY_MAX >= summaryInfo.pltQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).pltQty_B2.setValue(summaryInfo.pltQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).pltQty_B2.setValue(PALLET_QTY_MAX);
                    }

                    if (CASE_QTY_MAX >= summaryInfo.cseQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).cseQty_B2.setValue(summaryInfo.cseQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).cseQty_B2.setValue(CASE_QTY_MAX);
                    }

                } else if (day3.equals(summaryInfo.ltstWhInEtaDt_B1.getValue())) {

                    if (RWS_QTY_MAX >= summaryInfo.rwsQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).rwsQty_B3.setValue(summaryInfo.rwsQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).rwsQty_B3.setValue(RWS_QTY_MAX);
                    }

                    if (PALLET_QTY_MAX >= summaryInfo.pltQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).pltQty_B3.setValue(summaryInfo.pltQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).pltQty_B3.setValue(PALLET_QTY_MAX);
                    }

                    if (CASE_QTY_MAX >= summaryInfo.cseQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).cseQty_B3.setValue(summaryInfo.cseQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).cseQty_B3.setValue(CASE_QTY_MAX);
                    }

                } else if (day4.equals(summaryInfo.ltstWhInEtaDt_B1.getValue())) {

                    if (RWS_QTY_MAX >= summaryInfo.rwsQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).rwsQty_B4.setValue(summaryInfo.rwsQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).rwsQty_B4.setValue(RWS_QTY_MAX);
                    }

                    if (PALLET_QTY_MAX >= summaryInfo.pltQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).pltQty_B4.setValue(summaryInfo.pltQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).pltQty_B4.setValue(PALLET_QTY_MAX);
                    }

                    if (CASE_QTY_MAX >= summaryInfo.cseQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).cseQty_B4.setValue(summaryInfo.cseQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).cseQty_B4.setValue(CASE_QTY_MAX);
                    }

                } else if (day5.equals(summaryInfo.ltstWhInEtaDt_B1.getValue())) {

                    if (RWS_QTY_MAX >= summaryInfo.rwsQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).rwsQty_B5.setValue(summaryInfo.rwsQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).rwsQty_B5.setValue(RWS_QTY_MAX);
                    }

                    if (PALLET_QTY_MAX >= summaryInfo.pltQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).pltQty_B5.setValue(summaryInfo.pltQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).pltQty_B5.setValue(PALLET_QTY_MAX);
                    }

                    if (CASE_QTY_MAX >= summaryInfo.cseQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).cseQty_B5.setValue(summaryInfo.cseQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).cseQty_B5.setValue(CASE_QTY_MAX);
                    }

                } else if (day6.equals(summaryInfo.ltstWhInEtaDt_B1.getValue())) {

                    if (RWS_QTY_MAX >= summaryInfo.rwsQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).rwsQty_B6.setValue(summaryInfo.rwsQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).rwsQty_B6.setValue(RWS_QTY_MAX);
                    }

                    if (PALLET_QTY_MAX >= summaryInfo.pltQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).pltQty_B6.setValue(summaryInfo.pltQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).pltQty_B6.setValue(PALLET_QTY_MAX);
                    }

                    if (CASE_QTY_MAX >= summaryInfo.cseQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).cseQty_B6.setValue(summaryInfo.cseQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).cseQty_B6.setValue(CASE_QTY_MAX);
                    }

                } else if (day7.equals(summaryInfo.ltstWhInEtaDt_B1.getValue())) {

                    if (RWS_QTY_MAX >= summaryInfo.rwsQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).rwsQty_B7.setValue(summaryInfo.rwsQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).rwsQty_B7.setValue(RWS_QTY_MAX);
                    }

                    if (PALLET_QTY_MAX >= summaryInfo.pltQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).pltQty_B7.setValue(summaryInfo.pltQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).pltQty_B7.setValue(PALLET_QTY_MAX);
                    }

                    if (CASE_QTY_MAX >= summaryInfo.cseQty_BZ.getValueInt()) {
                        globalMsg.B.no(num).cseQty_B7.setValue(summaryInfo.cseQty_BZ.getValue());
                    } else {
                        globalMsg.B.no(num).cseQty_B7.setValue(CASE_QTY_MAX);
                    }

                } else {
                    // do nothing
                }

            }
            globalMsg.B.setValidCount(num + 1);
            // Posted a one-page data
            for (int i = 0; i < num + 1; i++) {
                if (i < bizMsg.B.length()) {
                    EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i), null);
                } else {
                    break;
                }
            }

            if (num + 1 > bizMsg.B.length()) {
                bizMsg.B.setValidCount(bizMsg.B.length());
            } else {
                bizMsg.B.setValidCount(num + 1);
            }

            // Simply choose a page to the item page breaks.
            bizMsg.xxPageShowFromNum_B1.setValue(BigDecimal.ONE);
            bizMsg.xxPageShowToNum_B1.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_B1.setValue(num + 1);

        } else {
            // do nothing
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLAL0070CMsg
     * @param globalMsg NLAL0070SMsg
     */
    private void initializeSchedule(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(globalMsg.A);

        bizMsg.xxPageShowFromNum_A1.clear();
        bizMsg.xxPageShowToNum_A1.clear();
        bizMsg.xxPageShowOfNum_A1.clear();
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLAL0070CMsg
     * @param globalMsg NLAL0070SMsg
     */
    private void initializeScheduleForSearch(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg) {

        globalMsg.invtyLocCd_P1.setValue(bizMsg.invtyLocCd_P1.getValue());
        globalMsg.xxOrdDt_F1.setValue(bizMsg.xxOrdDt_F1.getValue());
        globalMsg.xxOrdDt_T1.setValue(bizMsg.xxOrdDt_T1.getValue());
        globalMsg.rwsRefNum_P1.setValue(bizMsg.rwsRefNum_P1.getValue());
        globalMsg.carrCd_P1.setValue(bizMsg.carrCd_P1.getValue());
        globalMsg.schdTpCd_P1.setValue(bizMsg.schdTpCd_P1.getValue());
        globalMsg.imptInvNum_P1.setValue(bizMsg.imptInvNum_P1.getValue());
        globalMsg.asnNotRcvFlg_P1.setValue(bizMsg.asnNotRcvFlg_P1.getValue());
        globalMsg.mdseCd_P1.setValue(bizMsg.mdseCd_P1.getValue());
        globalMsg.asnNotRcvFlg_P1.setValue(bizMsg.asnNotRcvFlg_P1.getValue());
        globalMsg.whInPrtyFlg_P1.setValue(bizMsg.whInPrtyFlg_P1.getValue());
        globalMsg.imptInvLclFlg_P1.setValue(bizMsg.imptInvLclFlg_P1.getValue());

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLAL0070CMsg
     * @param globalMsg NLAL0070SMsg
     */
    private void initializeSummary(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg) {

        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(globalMsg.B);

        bizMsg.xxPageShowFromNum_B1.clear();
        bizMsg.xxPageShowToNum_B1.clear();
        bizMsg.xxPageShowOfNum_B1.clear();

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param date String
     * @return
     */
    private String getDateAndDay(String date) {

        //START 2013/10/29 K.Kasai[MOD, Defect.#2852]
        //String newDate = ZYPDateUtil.convertFormat(date, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, ZYPDateUtil.SEPARATOR_SLASH);
        String newDate = ZYPDateUtil.formatEzd8ToDisp(date);
        //END 2013/10/29 K.Kasai[MOD, Defect.#2852]
        String day = setDayOfWeek(date);
        String newDateAndDay = newDate + day;

        return newDateAndDay;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param startWeekDate String
     * @return String
     */
    private String setDayOfWeek(String startWeekDate) {

        // To calculate the day, yyyy / mm / dd then converted to a
        // consolidated return
        int outGetDayOfWeek = ZYPDateUtil.getDayOfWeek(startWeekDate);

        String day = "";
        switch (outGetDayOfWeek) {

            case ZYPDateUtil.WEEK_SUNDAY:

                //START 2013/10/29 K.Kasai[MOD, Defect.#2852]
//                day = " Sun";
                day = " " + EZDI18NContext.getInstance().getI18NAccessor().getLabelConv().convLabel2i18nLabel("", "Sun");
                //END 2013/10/29 K.Kasai[MOD, Defect.#2852]
                break;

            case ZYPDateUtil.WEEK_MONDAY:

                //START 2013/10/29 K.Kasai[MOD, Defect.#2852]
//                day = " Mon";
                day = " " + EZDI18NContext.getInstance().getI18NAccessor().getLabelConv().convLabel2i18nLabel("", "Mon");
                //END 2013/10/29 K.Kasai[MOD, Defect.#2852]
                break;

            case ZYPDateUtil.WEEK_TUESDAY:

                //START 2013/10/29 K.Kasai[MOD, Defect.#2852]
//                day = " Tue";
                day = " " + EZDI18NContext.getInstance().getI18NAccessor().getLabelConv().convLabel2i18nLabel("", "Tue");
                //END 2013/10/29 K.Kasai[MOD, Defect.#2852]
                break;

            case ZYPDateUtil.WEEK_WEDNESDAY:

                //START 2013/10/29 K.Kasai[MOD, Defect.#2852]
//                day = " Wed";
                day = " " + EZDI18NContext.getInstance().getI18NAccessor().getLabelConv().convLabel2i18nLabel("", "Wed");
                //END 2013/10/29 K.Kasai[MOD, Defect.#2852]
                break;

            case ZYPDateUtil.WEEK_THURSDAY:

                //START 2013/10/29 K.Kasai[MOD, Defect.#2852]
//                day = " Thu";
                day = " " + EZDI18NContext.getInstance().getI18NAccessor().getLabelConv().convLabel2i18nLabel("", "Thu");
                //END 2013/10/29 K.Kasai[MOD, Defect.#2852]
                break;

            case ZYPDateUtil.WEEK_FRIDAY:

                //START 2013/10/29 K.Kasai[MOD, Defect.#2852]
//                day = " Fri";
                day = " " + EZDI18NContext.getInstance().getI18NAccessor().getLabelConv().convLabel2i18nLabel("", "Fri");
                //END 2013/10/29 K.Kasai[MOD, Defect.#2852]
                break;

            case ZYPDateUtil.WEEK_SATURDAY:

                //START 2013/10/29 K.Kasai[MOD, Defect.#2852]
//                day = " Sat";
                day = " " + EZDI18NContext.getInstance().getI18NAccessor().getLabelConv().convLabel2i18nLabel("", "Sat");
                //END 2013/10/29 K.Kasai[MOD, Defect.#2852]
                break;

            default:
                break;
        }

        return day;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg
     * @param globalMsg
     */
    private void getScheduleDownloadListForMdse(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg) {

        if (globalMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo("NLAM0116E", null);
            return;
        } else {
            // do nothing
        }

        Map<String, Object> whSchdMsp = new HashMap<String, Object>();

        setParamForScheduleDL(bizMsg, globalMsg, whSchdMsp);

        // Set the name of CSV file
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_SCHEDULE_FOR_MDSE), DOWNLOAD_FILE_EXTENSION);

        NLAL0070F00FMsg fMsg = new NLAL0070F00FMsg();

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        csvOutFile.writeHeader(DOWNLOAD_HEADER_NAME_FOR_SCHEDULE_OF_MDSE);

        try {
            final boolean isNormalEnd = NLAL0070Query.getInstance().getWhScheduleDownloadListForMdse(whSchdMsp, bizMsg, fMsg, csvOutFile);

            // nothing data.
            if (!isNormalEnd) {
                return;
            }
        } finally {
            csvOutFile.close();
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg
     * @param globalMsg
     */
    private void getScheduleDownloadListForCntnrInv(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg) {

        if (globalMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo("NLAM0116E", null);
            return;
        } else {
            // do nothing
        }

        Map<String, Object> whSchdMsp = new HashMap<String, Object>();

        setParamForScheduleDL(bizMsg, globalMsg, whSchdMsp);

        // Set the name of CSV file
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_SCHEDULE_FOR_CNTNR_INV), DOWNLOAD_FILE_EXTENSION);

        NLAL0070F02FMsg fMsg = new NLAL0070F02FMsg();

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        csvOutFile.writeHeader(DOWNLOAD_HEADER_NAME_FOR_SCHEDULE_OF_CNTNR_INV);

        try {
            final boolean isNormalEnd = NLAL0070Query.getInstance().getWhScheduleDownloadListForCntnrInv(whSchdMsp, bizMsg, fMsg, csvOutFile);

            // nothing data.
            if (!isNormalEnd) {
                return;
            }
        } finally {
            csvOutFile.close();
        }
    }

    /**
     * @param bizMsg NLAL0070CMsg
     * @param globalMsg NLAL0070SMsg
     * @param whSchdMsp Map<String, Object>
     */
    private void setParamForSchedule(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg, Map<String, Object> whSchdMsp) {

        whSchdMsp.put("glblCmpyCd", bizMsg.glblCmpyCd_BK.getValue());
        whSchdMsp.put("whEtaStart", globalMsg.xxOrdDt_F1.getValue());
        whSchdMsp.put("whEtaEnd", globalMsg.xxOrdDt_T1.getValue());

        if (ZYPConstant.FLG_ON_Y.equals(globalMsg.asnNotRcvFlg_P1.getValue())) {
            whSchdMsp.put("asnNotRcvFlg", ZYPConstant.FLG_ON_Y);
        } else {
            whSchdMsp.put("asnNotRcvFlg", ZYPConstant.FLG_OFF_N);
        }

        if (ZYPConstant.FLG_ON_Y.equals(globalMsg.whInPrtyFlg_P1.getValue())) {
            whSchdMsp.put("whInPrtyFlg", ZYPConstant.FLG_ON_Y);
        } else {
            whSchdMsp.put("whInPrtyFlg", ZYPConstant.FLG_OFF_N);
        }

        if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.imptInvLclFlg_P1.getValue())) {
            whSchdMsp.put("imptInvLclFlg", ZYPConstant.FLG_OFF_N);
        }

        whSchdMsp.put("sceOrdTpCd01", NLXSceConst.SCE_ORD_TP_CD_IV);
        whSchdMsp.put("sceOrdTpCd02", NLXSceConst.SCE_ORD_TP_CD_IO);
        whSchdMsp.put("sceOrdTpCd03", NLXSceConst.SCE_ORD_TP_CD_CA);
        whSchdMsp.put("sceOrdTpCd04", NLXSceConst.SCE_ORD_TP_CD_CT);
        whSchdMsp.put("sceOrdTpCd05", NLXSceConst.SCE_ORD_TP_CD_CO);
        whSchdMsp.put("inbdSrcTpCd01", INBD_SRC_TP.PO_RECEIVING);
        whSchdMsp.put("ltstRecFlg", ZYPConstant.FLG_ON_Y);
        whSchdMsp.put("whChngRqstFlg", ZYPConstant.FLG_OFF_N);
        whSchdMsp.put("rwsStsCd01", RWS_STS.SAVED);
        whSchdMsp.put("rwsStsCd02", RWS_STS.PRINTED);
        whSchdMsp.put("rwsStsCd03", RWS_STS.CANCELED);
        whSchdMsp.put("imptInvActvVrsnFlg", ZYPConstant.FLG_ON_Y);

        // 2013/06/03 QC1209 T.Tomita Update Start
//        if (WH_CD_ALL.equals(globalMsg.invtyLocCd_P1.getValue())) {
//            int whCdLength = bizMsg.invtyLocCd.length() - 1;
//            ArrayList<String> invtyLocCdList = new ArrayList<String>();
//
//            for (int i = 0; i < whCdLength; i++) {
//                if (ZYPCommonFunc.hasValue(bizMsg.invtyLocCd.no(i + 1).getValue())) {
//                    invtyLocCdList.add(bizMsg.invtyLocCd.no(i + 1).getValue());
//                }
//            }
//            Object[] invtyLocCdArr = (Object[]) invtyLocCdList.toArray();
//            whSchdMsp.put("invtyLocCd", (Object) invtyLocCdArr);
        if (!ZYPCommonFunc.hasValue(bizMsg.invtyLocCd_P1.getValue())) {
            String locRoleTp = NMXC100001EnableWH.getLocRoleTpForPopup(bizMsg.glblCmpyCd_BK.getValue(), BUSINESS_ID);
            if (ZYPCommonFunc.hasValue(locRoleTp)) {
                String[] locRoleTpArr = locRoleTp.split(COMMA);
                whSchdMsp.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
                whSchdMsp.put("whSysTpCd", WH_SYS_TP.NA);
                whSchdMsp.put("locRoleTp", locRoleTpArr);
                whSchdMsp.put("dataSecurityWh", getDataSecurityWhList());
                whSchdMsp.put("invtyLocCd", null);
            } else {
                whSchdMsp.put("locRoleTp", null);
                whSchdMsp.put("invtyLocCd", null);
            }
        // 2013/06/03 QC1209 T.Tomita Update End
        } else {
            Object[] invtyLocCdArr = new String[1];
            invtyLocCdArr[0] = globalMsg.invtyLocCd_P1.getValue();
            whSchdMsp.put("invtyLocCd", invtyLocCdArr);
            // 2013/06/03 QC1209 T.Tomita Add Start
            whSchdMsp.put("locRoleTp", null);
            // 2013/06/03 QC1209 T.Tomita Add End
        }

        if (ZYPCommonFunc.hasValue(globalMsg.rwsRefNum_P1)) {
            whSchdMsp.put("rwsRefNum", globalMsg.rwsRefNum_P1.getValue());
        } else {
            whSchdMsp.put("rwsRefNum", null);
        }

        if (ZYPCommonFunc.hasValue(globalMsg.mdseCd_P1)) {
            whSchdMsp.put("mdseCd", globalMsg.mdseCd_P1.getValue());
        } else {
            whSchdMsp.put("mdseCd", null);
        }

        if (ZYPCommonFunc.hasValue(globalMsg.schdTpCd_P1)) {
            whSchdMsp.put("schdTpCd", globalMsg.schdTpCd_P1.getValue());
        } else {
            whSchdMsp.put("schdTpCd", null);
        }

        if (ZYPCommonFunc.hasValue(globalMsg.imptInvNum_P1)) {
            whSchdMsp.put("imptInvNum", globalMsg.imptInvNum_P1.getValue());
        } else {
            whSchdMsp.put("imptInvNum", null);
        }

        if (ZYPCommonFunc.hasValue(globalMsg.carrCd_P1)) {
            whSchdMsp.put("carrCd", globalMsg.carrCd_P1.getValue());
        } else {
            whSchdMsp.put("carrCd", null);
        }
    }

    /**
     * @param bizMsg NLAL0070CMsg
     * @param globalMsg NLAL0070SMsg
     * @param whSchdMsp Map<String, Object>
     */
    private void setParamForScheduleDL(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg, Map<String, Object> whSchdMsp) {

        whSchdMsp.put("glblCmpyCd", bizMsg.glblCmpyCd_BK.getValue());
        whSchdMsp.put("whEtaStart", globalMsg.xxOrdDt_F1.getValue());
        whSchdMsp.put("whEtaEnd", globalMsg.xxOrdDt_T1.getValue());

        whSchdMsp.put("sceOrdTpCd01", NLXSceConst.SCE_ORD_TP_CD_IV);
        whSchdMsp.put("sceOrdTpCd02", NLXSceConst.SCE_ORD_TP_CD_IO);
        whSchdMsp.put("sceOrdTpCd03", NLXSceConst.SCE_ORD_TP_CD_CA);
        whSchdMsp.put("sceOrdTpCd04", NLXSceConst.SCE_ORD_TP_CD_CT);
        whSchdMsp.put("sceOrdTpCd05", NLXSceConst.SCE_ORD_TP_CD_CO);
        whSchdMsp.put("inbdSrcTpCd01", INBD_SRC_TP.PO_RECEIVING);
        whSchdMsp.put("ltstRecFlg", ZYPConstant.FLG_ON_Y);
        whSchdMsp.put("whChngRqstFlg", ZYPConstant.FLG_OFF_N);
        whSchdMsp.put("rwsStsCd01", RWS_STS.SAVED);
        whSchdMsp.put("rwsStsCd02", RWS_STS.PRINTED);
        whSchdMsp.put("rwsStsCd03", RWS_STS.CANCELED);
        whSchdMsp.put("imptInvActvVrsnFlg", ZYPConstant.FLG_ON_Y);

        // 2013/06/03 QC1209 T.Tomita Update Start
//        if (!WH_CD_ALL.equals(globalMsg.invtyLocCd_P1.getValue())) {
        if (ZYPCommonFunc.hasValue(globalMsg.invtyLocCd_P1.getValue())) {
        // 2013/06/03 QC1209 T.Tomita Update End
            whSchdMsp.put("invtyLocCd", globalMsg.invtyLocCd_P1.getValue());
        }

        List<String> rwsRefNumList = new ArrayList<String>();
        String rwsRefNum = null;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            rwsRefNum = globalMsg.A.no(i).rwsRefNum_A1.getValue();
            if (!rwsRefNumList.contains(rwsRefNum)) {
                rwsRefNumList.add(rwsRefNum);
            }
        }
        whSchdMsp.put("rwsRefNumList", rwsRefNumList);
    }

    /**
     * @param bizMsg NLAL0070CMsg
     * @param globalMsg NLAL0070SMsg
     * @param CommandType String
     */
    private void setCommonParamForSummary(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg, Map<String, Object> whSmryMap) {

        whSmryMap.put("glblCmpyCd", bizMsg.glblCmpyCd_BK.getValue());
        whSmryMap.put("scheTpCd", bizMsg.schdTpCd_P1);
        whSmryMap.put("asnNotRcvFlg", bizMsg.asnNotRcvFlg_P1);
        whSmryMap.put("ltstRecFlg", ZYPConstant.FLG_ON_Y);
        whSmryMap.put("whChngRqstFlg", ZYPConstant.FLG_OFF_N);
        whSmryMap.put("rwsStsCd01", RWS_STS.RECEIVING);
        whSmryMap.put("rwsStsCd02", RWS_STS.RECEIVED);
        whSmryMap.put("inbdSrcTpCd01", INBD_SRC_TP.INVOICE);
        whSmryMap.put("inbdSrcTpCd02", INBD_SRC_TP.CONTAINER);
        whSmryMap.put("inbdSrcTpCd03", INBD_SRC_TP.SCP_ORDER);
        whSmryMap.put("inbdSrcTpCd04", INBD_SRC_TP.SCP_ASN);
        whSmryMap.put("inbdSrcTpCd05", INBD_SRC_TP.PO_RECEIVING);

        // 2013/06/03 QC1209 T.Tomita Update Start
//        if (WH_CD_ALL.equals(bizMsg.invtyLocCd_P1.getValue())) {
//            int whCdLength = bizMsg.invtyLocCd.length() - 1;
//            ArrayList<String> invtyLocCdList = new ArrayList<String>();
//
//            for (int i = 0; i < whCdLength; i++) {
//                if (ZYPCommonFunc.hasValue(bizMsg.invtyLocCd.no(i + 1).getValue())) {
//                    invtyLocCdList.add(bizMsg.invtyLocCd.no(i + 1).getValue());
//                }
//            }
//            Object[] invtyLocCdArr = (Object[]) invtyLocCdList.toArray();
//            whSmryMap.put("invtyLocCd", (Object) invtyLocCdArr);
        if (!ZYPCommonFunc.hasValue(bizMsg.invtyLocCd_P1.getValue())) {
            String locRoleTp = NMXC100001EnableWH.getLocRoleTpForPopup(bizMsg.glblCmpyCd_BK.getValue(), BUSINESS_ID);
            if (ZYPCommonFunc.hasValue(locRoleTp)) {
                String[] locRoleTpArr = locRoleTp.split(COMMA);
                whSmryMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
                whSmryMap.put("whSysTpCd", WH_SYS_TP.NA);
                whSmryMap.put("locRoleTp", locRoleTpArr);
                whSmryMap.put("dataSecurityWh", getDataSecurityWhList());
                whSmryMap.put("invtyLocCd", null);
            } else {
                whSmryMap.put("locRoleTp", null);
                whSmryMap.put("invtyLocCd", null);
            }
        // 2013/06/03 QC1209 T.Tomita Update End
        } else {
            Object[] invtyLocCdArr = new String[1];
            invtyLocCdArr[0] = bizMsg.invtyLocCd_P1.getValue();
            whSmryMap.put("invtyLocCd", invtyLocCdArr);
            // 2013/06/03 QC1209 T.Tomita Add Start
            whSmryMap.put("locRoleTp", null);
            // 2013/06/03 QC1209 T.Tomita Add End
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg
     * @param globalMsg
     */
    private void getSummaryDownloadList(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg) {

        if (bizMsg.B.getValidCount() == 0) {
            bizMsg.setMessageInfo("NLAM0116E", null);
            return;
        } else {
            // do nothing
        }

        Map<String, Object> whSmryMap = new HashMap<String, Object>();

        whSmryMap.put("whEtaStart", globalMsg.xxFromDt_B1.getValue());
        whSmryMap.put("whEtaEnd", ZYPDateUtil.addDays(globalMsg.xxFromDt_B1.getValue(), AFTER_6DAYS));

        setCommonParamForSummary(bizMsg, globalMsg, whSmryMap);

        // Set the name of CSV file
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_SUMMARY), DOWNLOAD_FILE_EXTENSION);

        NLAL0070F01FMsg fMsg = new NLAL0070F01FMsg();

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        csvOutFile.writeHeader(DOWNLOAD_HEADER_NAME_FOR_SUMMARY);

        try {
            final boolean isNormalEnd = NLAL0070Query.getInstance().getSummaryDownloadList(whSmryMap, bizMsg, fMsg, csvOutFile);

            // nothing data.
            if (!isNormalEnd) {
                return;
            }
        } finally {
            csvOutFile.close();
        }
    }

    /**
     * @param bizMsg NLAL0070CMsg
     * @param globalMsg NLAL0070SMsg
     */
    private void setDispDataForUpdate(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        int pagenationFrom = bizMsg.xxPageShowFromNum_A1.getValueInt() - 1;
        int count = 0;
        int endCnt = pagenationFrom + bizMsg.A.length();

        for (int i = pagenationFrom; i < endCnt; i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
                count++;
            } else {
                break;
            }
        }

        bizMsg.A.setValidCount(count);

        bizMsg.xxPageShowFromNum_A1.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum_A1.setValue(pagenationFrom + bizMsg.A.getValidCount());

    }

    // 2013/06/03 QC1209 T.Tomita Add Start
    /**
     * @param bizMsg NLAL0070CMsg
     */
    private void getInvtyLocNm(NLAL0070CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.invtyLocCd_P1)) {
            List<S21DataSecurityAttributeData> dataSecurityList = getDataSecurityList();
            NMXC100001EnableWHData enableWHData = NMXC100001EnableWH.checkEnableWH(bizMsg.glblCmpyCd_BK.getValue(), bizMsg.invtyLocCd_P1.getValue(), BUSINESS_ID, dataSecurityList, ZYPConstant.FLG_ON_Y);
            if (enableWHData == null || !ZYPCommonFunc.hasValue(enableWHData.getInvtyLocNm())) {
                bizMsg.invtyLocNm_P1.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.invtyLocNm_P1, enableWHData.getInvtyLocNm());
            }
        } else {
            bizMsg.invtyLocNm_P1.clear();
        }
    }

    /**
     * @return Data Security WH_CD List
     */
    private String[] getDataSecurityWhList() {
        List<S21DataSecurityAttributeData> dataSecurityList = getDataSecurityList();
        String[] rtnArray = new String[dataSecurityList.size()];
        boolean whAllFlg = false;
        int loopCount = 0;
        for (S21DataSecurityAttributeData dataSecurity : dataSecurityList) {
            if (PROFILE_ALL.equals(dataSecurity.getValue())) {
                whAllFlg = true;
                break;
            }
            rtnArray[loopCount] = dataSecurity.getValue();
            loopCount++;
        }

        if (whAllFlg) {
            return null;
        }
        return rtnArray;
    }

    /**
     * @return Data Security List
     */
    private List<S21DataSecurityAttributeData> getDataSecurityList() {
        S21UserProfileService userProfile = getUserProfileService();
        return userProfile.getDataSecurityProfileFor(BUSINESS_ID).getDataSecurityAttributeDataListFor(WH);
    }
    // 2013/06/03 QC1209 T.Tomita Add End
}
