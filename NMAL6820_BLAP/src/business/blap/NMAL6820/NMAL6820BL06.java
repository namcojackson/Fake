/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6820;

import static business.blap.NMAL6820.constant.NMAL6820Constant.BUSINESS_ID;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_CMN_SUBMIT;
import static business.blap.NMAL6820.constant.NMAL6820Constant.MESSAGE_KIND_ERROR;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NZZM0003E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.RTL_WH_SETUP_API_MODE_CREATE;
import static business.blap.NMAL6820.constant.NMAL6820Constant.RTL_WH_SETUP_API_MODE_UPDATE;
import static business.blap.NMAL6820.constant.NMAL6820Constant.TIMESTAMP_PATTERN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.WH;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DEF_EFF_THRU_DT;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DEF_SHPG_CLO_TM_TS;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DEF_PICK_PACK_AOT;
import static business.blap.NMAL6820.constant.NMAL6820Constant.ZZPM0068E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DEF_END_MTH_BIZ_DAYS_AOT;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DEF_WH_DROP_LINE_NUM;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DEF_FROM_WH_DROP_TM_TS;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DEF_TO_WH_DROP_TM_TS;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DEF_EFF_FROM_DT;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DEF_DELY_LEAD_AOT;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6820.common.NMAL6820CommonLogic;
import business.db.AREA_LEAD_TMTMsg;
import business.db.RTL_WHTMsg;
import business.db.WH_DROP_TMTMsg;
import business.db.WH_END_MTHTMsg;
import business.db.WH_LEAD_TMTMsg;
import business.parts.NMZC601001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC601001.NMZC601001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeData;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeException;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * Business ID : NMAL6820 Warehouse Setup
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC#280
 * 02/04/2016   CSAI            D.Fukaya        Update          QC#1590
 * 02/11/2016   CSAI            D.Fukaya        Update          QC#1598
 * 02/16/2016   CSAI            D.Fukaya        Delete          QC#2368
 * 02/18/2016   CSAI            D.Fukaya        Update          QC#3436
 * 02/22/2016   CSAI            D.Fukaya        Update          QC#2369
 * 04/25/2016   CSAI            D.Fukaya        Update          QC#6406
 * 08/05/2016   CITS            S.Endo          Update          QC#10838
 * 08/26/2016   CITS            S.Endo          Update          QC#12668
 * 10/19/2016   Fujitsu         C.Yokoi         Update          QC#4096
 * 10/25/2016   CITS            Y.IWASAKI       Update          QC#15120
 * 02/07/2017   CITS            Y.IWASAKI       Update          QC#17233
 * 11/21/2017   CITS            K.Ogino         Update          QC#22212
 * 01/19/2018   CITS            T.Tokutomi      Update          QC#21852
 * 09/17/2020   CITS            J.Evangelista   Update          QC#57659
 * 08/31/2021   CITS            K.Watanabe      Update          QC#59099 
 *</pre>
 */
public class NMAL6820BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NMAL6820SCRN00_CMN_SUBMIT.equals(screenAplID)) {

                doProcess_NMAL6820Scrn00_Cmn_Submit((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else {

                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: Entered Retail Warehouse info is
     * registered/
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820Scrn00_Cmn_Submit(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        List<S21DataSecurityAttributeData> dataSecurityList = getDataSecurityList();

        // if Return to Address Check Box is on, copy from Ship to
        // Address.
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_R1.getValue())) {
            cMsg = copyAddress(cMsg);
        }

        // Check Master
        if (!NMAL6820CommonLogic.checkInputForSubmit(cMsg)) {
            return;
        }

        // Check for Inventory Master
        if (!NMAL6820CommonLogic.checkForInvtyMstr(cMsg)) {
            return;
        }

        // Check for WH Existence
        if (!NMAL6820CommonLogic.checkForWhExist(cMsg)) {
            return;
        }

        // Check for RTL_WH consistency
        if (!NMAL6820CommonLogic.checkForRtlWh(cMsg)) {
            return;
        }

        // Check for Default Return-To Existence
        if (!NMAL6820CommonLogic.checkForDefaultRtrnToExist(cMsg)) {
            return;
        }

        // QC#17233
        // Check for Carrier existence
        if (!NMAL6820CommonLogic.checkForCarrierExist(cMsg)) {
            return;
        }

        // // Sub Warehouse should be set when the wh is registered.
        // if (cMsg.A.getValidCount() == 0) {
        //
        // cMsg.setMessageInfo(NMAM0014E, new String[]
        // {NAME_FOR_MESSAGE_PRTY_FLG_IN_SWH });
        // return;
        // }

        // check Sourcing Tab
        if (!NMAL6820CommonLogic.checkSourcingTab(cMsg, dataSecurityList)) {
            return;
        }

        // check SubWh Tab
        if (!NMAL6820CommonLogic.checkSubWhTab(cMsg, dataSecurityList)) {
            return;
        }

        // check transaction existence for update mode
        if (!NMAL6820CommonLogic.isNoErrorForUpdateMode(cMsg, sMsg)) {
            return;
        }

        // check address
        if (!NMAL6820CommonLogic.checkAddress(getGlobalCompanyCode(), cMsg, sMsg)) {
            return;
        }

        // 2016/10/14 CSA-QC#4096 add Start
        // Geo Code Check
        if (!NMAL6820CommonLogic.checkExistsGeoCodeByVertex(cMsg, sMsg)) {
            return;
        }
        // 2016/10/14 CSA-QC#4096 add End

        // Record Lock for update
        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, cMsg.rtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1);

        try {
            rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rtlWhTMsg);

        } catch (EZDDBRecordLockedException e) {
            cMsg.setMessageInfo(NZZM0003E);
            return;
        }

        // compare uptime
        if (rtlWhTMsg != null) {

            String ezUpTimeOfScrn = cMsg.ezUpTime_G1.getValue();
            String ezUpTimeZoneOfScrn = cMsg.ezUpTimeZone_G1.getValue();
            String ezUpTimeOfCurrent = rtlWhTMsg.ezUpTime.getValue();
            String ezUpTimeZoneOfCurrent = rtlWhTMsg.ezUpTimeZone.getValue();

            if (!ZYPCommonFunc.hasValue(ezUpTimeOfScrn)) {
                ezUpTimeOfScrn = "";
            }
            if (!ZYPCommonFunc.hasValue(ezUpTimeZoneOfScrn)) {
                ezUpTimeZoneOfScrn = "";
            }
            if (!ZYPCommonFunc.hasValue(ezUpTimeOfCurrent)) {
                ezUpTimeOfCurrent = "";
            }
            if (!ZYPCommonFunc.hasValue(ezUpTimeZoneOfCurrent)) {
                ezUpTimeZoneOfCurrent = "";
            }

            if (!ezUpTimeOfScrn.equals(ezUpTimeOfCurrent) || !ezUpTimeZoneOfScrn.equals(ezUpTimeZoneOfCurrent)) {
                cMsg.setMessageInfo(NZZM0003E);
                return;
            }
        }

        // get Local TimeZone Name
        String lclTzId = "";
        try {
            /* QC#59099 start */
            String postCd = "";
            postCd = subStrPostCd(cMsg.postCd_S1.getValue());
            lclTzId = ZYPLocalTimeUtil.getTZId(cMsg.ctryCd_S1.getValue(), postCd);
            /* QC#59099 end */
//            lclTzId = ZYPLocalTimeUtil.getTZId(cMsg.ctryCd_S1.getValue(), cMsg.postCd_S1.getValue());
        } catch (ZYPLocalTimeException e1) {
            // nothing to do.
        }
        if (ZYPCommonFunc.hasValue(lclTzId)) {
            ZYPLocalTimeData locTmDt = ZYPLocalTimeUtil.convertTimeSys2Lcl(lclTzId, ZYPDateUtil.getCurrentSystemTime(TIMESTAMP_PATTERN));
            ZYPEZDItemValueSetter.setValue(cMsg.tmZoneCd_H1, locTmDt.getTZDspName());
        }

        // ---------------------------------------------
        // Retail Warehouse Setup Update API(NMZC6010)
        // ---------------------------------------------
        NMZC601001PMsg nmzc601001PMsg = getRetailWarehouseSetupUpdatePMsg(cMsg, sMsg);

        // Retail Warehouse Setup Update API(NMZC6010) is executed
        NMZC601001 nmzc601001 = new NMZC601001();
        nmzc601001.execute(nmzc601001PMsg, ONBATCH_TYPE.ONLINE);

        // Retail WH Setup Update API(NMZC601001) result check
        boolean apiResult = isRtlWhSetupUpdResult(cMsg, nmzc601001PMsg);
        if (apiResult) {
            return;
        }

        // QC#21852 Add method.
        if (RTL_WH_SETUP_API_MODE_CREATE.equals(nmzc601001PMsg.xxModeCd.getValue())) {
            defaultWhAreaSettingSetup(cMsg);
        }
    }

    /* QC#59099 start */
    /**
     * subStrPostCd
     * @param postCd String 
     * @return String
     */
    public static String subStrPostCd(String postCd) {
        if (!ZYPCommonFunc.hasValue(postCd)) {
            return null;
        }
        if (postCd.length() > 5) {
            return postCd.substring(0, 5);
        }
        return postCd;
    }
    /* QC#59099 end */

    /**
     * getRetailWarehouseSetupUpdatePMsg <dd>The method explanation:
     * The business peculiarity processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @return NMZC601001PMsg
     */
    private NMZC601001PMsg getRetailWarehouseSetupUpdatePMsg(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        // Retail Warehouse Setup Update PMsg setValue
        NMZC601001PMsg nmzc601001PMsg = new NMZC601001PMsg();

        if (ZYPCommonFunc.hasValue(cMsg.whCd_H1)) {
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.xxModeCd, RTL_WH_SETUP_API_MODE_UPDATE);
        } else {
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.xxModeCd, RTL_WH_SETUP_API_MODE_CREATE);
        }

        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.glblCmpyCd, cMsg.glblCmpyCd_G1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtlWhCd, cMsg.rtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtlWhNm, cMsg.rtlWhNm_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtlWhDescTxt, cMsg.rtlWhDescTxt_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtlWhCatgCd, cMsg.rtlWhCatgCd_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.locTpCd, cMsg.locTpCd_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.physWhCd, cMsg.physWhCd_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.wmsWhCd, cMsg.wmsWhCd_H1);
        if (LOC_TP.TECHNICIAN.equals(cMsg.locTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.techTocCd, cMsg.whMgrPsnCd_H1);
        } else {
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.whMgrPsnCd, cMsg.whMgrPsnCd_H1);
        }
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.altPsnCd, cMsg.altPsnCd_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.invtyAcctCd, cMsg.invtyAcctCd_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.invtyOwnrCd, cMsg.invtyOwnrCd_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.whOwnrCd, cMsg.whOwnrCd_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.firstRefCmntTxt, cMsg.firstRefCmntTxt_H1);
        nmzc601001PMsg.scdRefCmntTxt.clear();
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.autoSoDropFlg, cMsg.autoSoDropFlg_H1);
        nmzc601001PMsg.soPrinReqFlg.clear();
        nmzc601001PMsg.rwsPrinReqFlg.clear();
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.whSysTpCd, cMsg.whSysTpCd_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.whStartTm, cMsg.whStartTm_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.whEndTm, cMsg.whEndTm_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.whCutOffTm, cMsg.whCutOffTm_H1);
        // 2020/09/17 QC#57659, Add Start
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.fedexCutOffTm, cMsg.fedexCutOffTm_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.upsCutOffTm, cMsg.upsCutOffTm_H1);
        // 2020/09/17 QC#57659, Add End
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.tmZoneCd, cMsg.tmZoneCd_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.geoCd, cMsg.geoCd_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.srcZnCd, cMsg.srcZnCd_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.defSrcProcrTpCd, cMsg.procrTpCd_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.defSrcLocCd, cMsg.rtlWhCd_SD.getValue() + cMsg.rtlSwhCd_SD.getValue());
        if (PROCR_TP.SUPPLIER.equals(cMsg.procrTpCd_S1.getValue())) {
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.prntVndCd_SD, cMsg.prntVndCd_SD);
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.vndCd_SD, cMsg.vndCd_SD);
            nmzc601001PMsg.defSrcRtlWhCd.clear();
            nmzc601001PMsg.defSrcRtlSwhCd.clear();
        }
        if (PROCR_TP.WAREHOUSE.equals(cMsg.procrTpCd_S1.getValue())) {
            nmzc601001PMsg.prntVndCd_SD.clear();
            nmzc601001PMsg.vndCd_SD.clear();
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.defSrcRtlWhCd, cMsg.rtlWhCd_SD);
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.defSrcRtlSwhCd, cMsg.rtlSwhCd_SD);
        }

        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.emerSrcProcrTpCd, cMsg.procrTpCd_E1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.emerSrcLocCd, cMsg.rtlWhCd_SE.getValue() + cMsg.rtlSwhCd_SE.getValue());
        if (PROCR_TP.SUPPLIER.equals(cMsg.procrTpCd_E1.getValue())) {
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.prntVndCd_SE, cMsg.prntVndCd_SE);
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.vndCd_SE, cMsg.vndCd_SE);
            nmzc601001PMsg.emerSrcRtlWhCd.clear();
            nmzc601001PMsg.emerSrcRtlSwhCd.clear();
        }
        if (PROCR_TP.WAREHOUSE.equals(cMsg.procrTpCd_E1.getValue())) {
            nmzc601001PMsg.prntVndCd_SE.clear();
            nmzc601001PMsg.vndCd_SE.clear();
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.emerSrcRtlWhCd, cMsg.rtlWhCd_SE);
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.emerSrcRtlSwhCd, cMsg.rtlSwhCd_SE);
        }

        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.defRtrnToProcrTpCd, cMsg.procrTpCd_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.defRtrnToLocCd, cMsg.rtlWhCd_SR.getValue() + cMsg.rtlSwhCd_SR.getValue());
        if (PROCR_TP.SUPPLIER.equals(cMsg.procrTpCd_R1.getValue())) {
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.prntVndCd_SR, cMsg.prntVndCd_SR);
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.vndCd_SR, cMsg.vndCd_SR);
            nmzc601001PMsg.defRtrnToRtlWhCd.clear();
            nmzc601001PMsg.defRtrnToRtlSwhCd.clear();
        }
        if (PROCR_TP.WAREHOUSE.equals(cMsg.procrTpCd_R1.getValue())) {
            nmzc601001PMsg.prntVndCd_SR.clear();
            nmzc601001PMsg.vndCd_SR.clear();
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.defRtrnToRtlWhCd, cMsg.rtlWhCd_SR);
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.defRtrnToRtlSwhCd, cMsg.rtlSwhCd_SR);
        }

        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.prfCarrCd, cMsg.carrCd_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.coaBrCd, cMsg.coaBrCd_H1);
//QC#17362
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.prntVndCd, cMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.vndCd, cMsg.vndCd);
        // QC#22212
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.techMblMsgAddr, cMsg.techMblMsgAddr);

        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.plnItemInsrcCd, cMsg.plnItemInsrcCd_S1);
        nmzc601001PMsg.gndSvcLvlCd.clear();
        nmzc601001PMsg.firstOvngtSvcLvlCd.clear();
        nmzc601001PMsg.scdOvngtSvcLvlCd.clear();
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.xxChkBox, cMsg.xxChkBox_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.locNum_S1, cMsg.locNum_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.shipToCustCd, cMsg.shipToCustCd_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.dsLocNm_S1, cMsg.dsLocNm_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.firstLineAddr, cMsg.firstLineAddr_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.scdLineAddr, cMsg.scdLineAddr_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.thirdLineAddr, cMsg.thirdLineAddr_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.frthLineAddr, cMsg.frthLineAddr_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.ctyAddr, cMsg.ctyAddr_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.cntyPk, cMsg.cntyPk_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.provNm, cMsg.provNm_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.stCd, cMsg.stCd_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.postCd, cMsg.postCd_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.ctryCd, cMsg.ctryCd_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.addlLocNm, cMsg.addlLocNm_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.faxNum, cMsg.faxNum_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.telNum, cMsg.telNum_H1);
        nmzc601001PMsg.attnNm.clear();
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.dsAcctNum_S1, cMsg.dsAcctNum_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.vndLocCd_S1, cMsg.vndLocCd_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.locNum_R1, cMsg.locNum_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtrnToCustCd, cMsg.rtrnToCustCd_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.dsLocNm_R1, cMsg.dsLocNm_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtrnToFirstLineAddr, cMsg.rtrnToFirstLineAddr_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtrnToScdLineAddr, cMsg.rtrnToScdLineAddr_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtrnToThirdLineAddr, cMsg.rtrnToThirdLineAddr_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtrnToFrthLineAddr, cMsg.rtrnToFrthLineAddr_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtrnToCtyAddr, cMsg.rtrnToCtyAddr_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtrnToCntyPk, cMsg.cntyPk_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtrnToProvNm, cMsg.rtrnToProvNm_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtrnToStCd, cMsg.rtrnToStCd_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtrnToPostCd, cMsg.rtrnToPostCd_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtrnToCtryCd, cMsg.ctryCd_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rtrnToAddlLocNm, cMsg.rtrnToAddlLocNm_R1);
        nmzc601001PMsg.rtrnToFaxNum.clear();
        nmzc601001PMsg.rtrnToTelNum.clear();
        nmzc601001PMsg.rtrnToAttnNm.clear();
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.dsAcctNum_R1, cMsg.dsAcctNum_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.vndLocCd_R1, cMsg.vndLocCd_R1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.poRcptRteTpCd, cMsg.poRcptRteTpCd_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.rmaRcptRteTpCd, cMsg.rmaRcptRteTpCd_S1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.effFromDt, cMsg.effFromDt_H1);
        ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.effThruDt, cMsg.effThruDt_H1);

        // SWH
        int i = 0;
        for (; i < cMsg.A.getValidCount(); i++) {

            if (i == cMsg.A.length()) {
                break;
            }

            if (ZYPCommonFunc.hasValue(cMsg.whCd_H1)) {
                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).xxModeCd, RTL_WH_SETUP_API_MODE_UPDATE);
            } else {
                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).xxModeCd, RTL_WH_SETUP_API_MODE_CREATE);
            }

            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).rtlSwhCd, cMsg.A.no(i).rtlSwhCd_A1);
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).rtlSwhNm, cMsg.A.no(i).rtlSwhNm_A1);
            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).rtlSwhDescTxt, cMsg.A.no(i).rtlSwhDescTxt_A1);

            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_P1.getValue())) {

                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).prtyLocFlg, cMsg.A.no(i).xxChkBox_P1);
            } else {

                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).prtyLocFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_P1, ZYPConstant.FLG_OFF_N);
            }

            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_D1.getValue())) {

                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).rtlSwhDsblFlg, cMsg.A.no(i).xxChkBox_D1);
            } else {

                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).rtlSwhDsblFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_D1, ZYPConstant.FLG_OFF_N);
            }

            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_M1.getValue())) {

                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).mrpEnblFlg, cMsg.A.no(i).xxChkBox_M1);
            } else {

                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).mrpEnblFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_M1, ZYPConstant.FLG_OFF_N);
            }

            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).defSrcProcrTpCd, cMsg.A.no(i).procrTpCd_A1);
            if (PROCR_TP.SUPPLIER.equals(cMsg.A.no(i).procrTpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).prntVndCd_AS, cMsg.A.no(i).prntVndCd_AS);
                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).vndCd_AS, cMsg.A.no(i).vndCd_AS);
                nmzc601001PMsg.swhInfoList.no(i).defSrcRtlWhCd.clear();
                nmzc601001PMsg.swhInfoList.no(i).defSrcRtlSwhCd.clear();
            }
            if (PROCR_TP.WAREHOUSE.equals(cMsg.A.no(i).procrTpCd_A1.getValue())) {
                nmzc601001PMsg.swhInfoList.no(i).prntVndCd_AS.clear();
                nmzc601001PMsg.swhInfoList.no(i).vndCd_AS.clear();
                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).defSrcRtlWhCd, cMsg.A.no(i).rtlWhCd_AS);
                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).defSrcRtlSwhCd, cMsg.A.no(i).rtlSwhCd_AS);
            }

            ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).defRtrnToProcrTpCd, cMsg.A.no(i).procrTpCd_A2);
            if (PROCR_TP.SUPPLIER.equals(cMsg.A.no(i).procrTpCd_A2.getValue())) {
                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).prntVndCd_AR, cMsg.A.no(i).prntVndCd_AR);
                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).vndCd_AR, cMsg.A.no(i).vndCd_AR);
                nmzc601001PMsg.swhInfoList.no(i).defRtrnToRtlWhCd.clear();
                nmzc601001PMsg.swhInfoList.no(i).defRtrnToRtlSwhCd.clear();
            }
            if (PROCR_TP.WAREHOUSE.equals(cMsg.A.no(i).procrTpCd_A2.getValue())) {
                nmzc601001PMsg.swhInfoList.no(i).prntVndCd_AR.clear();
                nmzc601001PMsg.swhInfoList.no(i).vndCd_AR.clear();
                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).defRtrnToRtlWhCd, cMsg.A.no(i).rtlWhCd_AR);
                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).defRtrnToRtlSwhCd, cMsg.A.no(i).rtlSwhCd_AR);
            }

            // set EFF_FROM_DT
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effFromDt_S1)) {

                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).effFromDt, cMsg.effFromDt_H1);
            } else {

                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).effFromDt, cMsg.A.no(i).effFromDt_S1);
            }

            // set EFF_THRU_DT
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_S1) && ZYPConstant.FLG_OFF_N.equals(cMsg.A.no(i).xxChkBox_D1.getValue())) {

                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).effThruDt, cMsg.effThruDt_H1);

            } else if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_S1) && ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_D1.getValue())) {

                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).effThruDt, ZYPDateUtil.getSalesDate());

            } else if (ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_S1) && ZYPConstant.FLG_OFF_N.equals(cMsg.A.no(i).xxChkBox_D1.getValue())) {

                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).effThruDt, cMsg.effThruDt_H1);

            } else if (ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_S1) && ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_D1.getValue())) {
                if (ZYPDateUtil.compare(cMsg.A.no(i).effThruDt_S1.getValue(), ZYPDateUtil.getSalesDate()) == 1) {

                    ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).effThruDt, ZYPDateUtil.getSalesDate());
                } else {

                    ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).effThruDt, cMsg.A.no(i).effThruDt_S1);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(nmzc601001PMsg.swhInfoList.no(i).effThruDt, cMsg.effThruDt_H1);
            }

        }

        nmzc601001PMsg.swhInfoList.setValidCount(i);

        return nmzc601001PMsg;
    }

    /**
     * isRtlWhSetupUpdResult <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param List nmzc601001PMsgArray
     * @return boolean
     */
    private boolean isRtlWhSetupUpdResult(NMAL6820CMsg bizMsg, NMZC601001PMsg nmzc601001PMsg) {

        String msgId = "";

        if (!S21ApiUtil.getXxMsgIdList(nmzc601001PMsg).isEmpty()) {

            for (int i = 0; i < nmzc601001PMsg.xxMsgIdList.length(); i++) {

                msgId = nmzc601001PMsg.xxMsgIdList.no(i).xxMsgId.getValue();

                if (ZYPCommonFunc.hasValue(msgId)) {

                    if (msgId.endsWith(MESSAGE_KIND_ERROR)) {

                        bizMsg.setMessageInfo(msgId);
                        return true;

                    } else {

                        bizMsg.setMessageInfo(msgId);
                        break;
                    }
                }
            }
        }
        return false;
    }

    /**
     * isRtlWhSetupUpdResult <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NMAL6820CMsg
     * @return boolean
     */
    public NMAL6820CMsg copyAddress(NMAL6820CMsg cMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.locNum_R1, cMsg.locNum_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.dsLocNm_R1, cMsg.dsLocNm_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnToFirstLineAddr_R1, cMsg.firstLineAddr_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnToScdLineAddr_R1, cMsg.scdLineAddr_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnToThirdLineAddr_R1, cMsg.thirdLineAddr_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnToFrthLineAddr_R1, cMsg.frthLineAddr_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnToCtyAddr_R1, cMsg.ctyAddr_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_R1, cMsg.cntyPk_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_R1, cMsg.cntyNm_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnToProvNm_R1, cMsg.provNm_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnToStCd_R1, cMsg.stCd_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnToPostCd_R1, cMsg.postCd_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.ctryCd_R1, cMsg.ctryCd_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnToAddlLocNm_R1, cMsg.addlLocNm_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_R1, cMsg.dsAcctNum_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_R1, cMsg.dsAcctNm_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnToCustCd_R1, cMsg.shipToCustCd_S1);

        return cMsg;
    }

    /**
     * @return Data Security List
     */
    private List<S21DataSecurityAttributeData> getDataSecurityList() {

        S21UserProfileService userProfile = getUserProfileService();
        return userProfile.getDataSecurityProfileFor(BUSINESS_ID).getDataSecurityAttributeDataListFor(WH);
    }


    /**
     * defaultWhAreaSettingSetup
     * @param cMsg
     */
    private void defaultWhAreaSettingSetup(NMAL6820CMsg cMsg){
        String glblCmpyCd = getGlobalCompanyCode();
        List<String> shpgModeList = NMAL6820Query.getInstance().getShpgModeCdList(glblCmpyCd);
        
        defaultSettingAreaLeadTm(glblCmpyCd, shpgModeList, cMsg);
        defaultSettingWhDropTm(glblCmpyCd, cMsg);
        defaultSettingWhEndMth(glblCmpyCd, cMsg);
        defaultSettingWhLeadTm(glblCmpyCd, shpgModeList, cMsg);
    }

    /**
     * defaultSettingAreaLeadTm
     * QC#21852 Add method.
     * @param glblCmpyCd String
     * @param shpgModeList List<String>
     * @param cMsg NMAL6820CMsg
     */
    private void defaultSettingAreaLeadTm(String glblCmpyCd, List<String> shpgModeList, NMAL6820CMsg cMsg) {
        List<String> stCdList = NMAL6820Query.getInstance().getStCdList(glblCmpyCd);

        for (String stCd : stCdList) {
            for (String shpgModeCd : shpgModeList) {
                AREA_LEAD_TMTMsg tMsg = new AREA_LEAD_TMTMsg();

                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.whCd, cMsg.rtlWhCd_H1);
                ZYPEZDItemValueSetter.setValue(tMsg.stCd, stCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shpgModeCd, shpgModeCd);
                ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, DEF_EFF_FROM_DT);
                ZYPEZDItemValueSetter.setValue(tMsg.delyLeadAot, DEF_DELY_LEAD_AOT);
                ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, DEF_EFF_THRU_DT);

                EZDTBLAccessor.insert(tMsg);

                String result = tMsg.getReturnCode();
                if (EZDTBLAccessor.RTNCD_DUPLICATE.equals(result)) {
                    // Duplicate Primary key Error.
                    String errMsg = "WH_CD:[" + tMsg.whCd.getValue() + "] ST_CD:[" + stCd + "] SHPG_MODE_CD:[" + shpgModeCd + "]";
                    cMsg.setMessageInfo(ZZPM0068E, new String[] {"Area Lead Time", errMsg });
                    return;
                }
            }
        }
    }

    /**
     * defaultSettingWhDropTm
     * QC#21852 Add method.
     * @param glblCmpyCd String
     * @param cMsg NMAL6820CMsg
     */
    private void defaultSettingWhDropTm(String glblCmpyCd, NMAL6820CMsg cMsg){
        WH_DROP_TMTMsg tMsg = new WH_DROP_TMTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, cMsg.rtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.whDropLineNum, DEF_WH_DROP_LINE_NUM);
        ZYPEZDItemValueSetter.setValue(tMsg.fromWhDropTmTs, DEF_FROM_WH_DROP_TM_TS);
        ZYPEZDItemValueSetter.setValue(tMsg.toWhDropTmTs, DEF_TO_WH_DROP_TM_TS);

        EZDTBLAccessor.insert(tMsg);

        String result = tMsg.getReturnCode();
        if (EZDTBLAccessor.RTNCD_DUPLICATE.equals(result)) {
            // Duplicate Primary key Error.
            String errMsg = "WH_CD:[" + tMsg.whCd.getValue() + "]";
            cMsg.setMessageInfo(ZZPM0068E, new String[] {"Warehouse Drop Time", errMsg });
            return;
        }
    }

    /**
     * defaultSettingWhEndMth
     * QC#21852 Add method.
     * @param glblCmpyCd String
     * @param cMsg NMAL6820CMsg
     */
    private void defaultSettingWhEndMth(String glblCmpyCd, NMAL6820CMsg cMsg) {
        WH_END_MTHTMsg tMsg = new WH_END_MTHTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, cMsg.rtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.endMthBizDaysAot, DEF_END_MTH_BIZ_DAYS_AOT);

        EZDTBLAccessor.insert(tMsg);

        String result = tMsg.getReturnCode();
        if (EZDTBLAccessor.RTNCD_DUPLICATE.equals(result)) {
            // Duplicate Primary key Error.
            String errMsg = "WH_CD:[" + tMsg.whCd.getValue() + "]";
            cMsg.setMessageInfo(ZZPM0068E, new String[] {"Warehouse End Of Month", errMsg });
            return;
        }
    }

    /**
     * defaultSettingWhLeadTm
     * QC#21852 Add method.
     * @param glblCmpyCd String
     * @param whCd String
     * @param shpgModeList List
     */
    private void defaultSettingWhLeadTm(String glblCmpyCd,  List<String> shpgModeList, NMAL6820CMsg cMsg) {
        String whCd = cMsg.rtlWhCd_H1.getValue();
        List<String> shpgSvcLvlCdList = NMAL6820Query.getInstance().getShpgSvcLvlCdList(glblCmpyCd);
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        for (String shpgSvcLvlCd : shpgSvcLvlCdList) {
            for (String shpgModeCd : shpgModeList) {
                WH_LEAD_TMTMsg tMsg = new WH_LEAD_TMTMsg();

                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.whCd, whCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, shpgSvcLvlCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shpgModeCd, shpgModeCd);
                ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, slsDt);
                ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, DEF_EFF_THRU_DT);
                ZYPEZDItemValueSetter.setValue(tMsg.shpgCloTmTs, DEF_SHPG_CLO_TM_TS);
                ZYPEZDItemValueSetter.setValue(tMsg.pickPackAot, DEF_PICK_PACK_AOT);

                EZDTBLAccessor.insert(tMsg);

                String result = tMsg.getReturnCode();
                if (EZDTBLAccessor.RTNCD_DUPLICATE.equals(result)) {
                    // Duplicate Primary key Error.
                    String errMsg = "WH_CD:[" + whCd + "] SHPG_SVC_LVL_CD:[" + shpgSvcLvlCd + "] SHPG_MODE_CD:[" + shpgModeCd + "]";
                    cMsg.setMessageInfo(ZZPM0068E, new String[] {"Warehouse Lead Time", errMsg });
                    return;
                }
            }
        }
    }
}
