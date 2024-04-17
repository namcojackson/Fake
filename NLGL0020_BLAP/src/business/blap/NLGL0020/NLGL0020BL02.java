/**
 * <pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0020;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLGL0020.common.NLGL0020CommonLogic;
import business.blap.NLGL0020.constant.NLGL0020Constant;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_PRCH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update      Defect No
 * ------------------------------------------------------------------------------------
 * 08/30/2013     CSAI            N.Sekine          Create             MW Replace Initial
 * 11/10/2015     CSAI            K.Lee             Update             S21NA Initial
 * 05/25/2017     CITS            S.Endo            Update             RS#3173
 * 07/03/2017     CITS            S.Endo            Update             QC#19042
 *</pre>
 */
public class NLGL0020BL02 extends S21BusinessHandler implements NLGL0020Constant {

    /**
     * The method explanation: this is a method of the execution after
     * the SV enent(setRequestData).
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLGL0040SCRN00_INIT.equals(screenAplID)) {
                doProcess_NLGL0020_INIT((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_PAGENEXT.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_PageNext((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_PAGEPREV.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_PagePrev((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_CMN_RESET.equals(screenAplID)) {
                doProcess_NLGL0020_INIT((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_CMN_RETURN.equals(screenAplID)) {
                ;// Do Nothing.
            } else if (EVENT_NM_NLGL0020SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_OnClick_Submit((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCHANGE_SUBMITLIST.equals(screenAplID)) {
                ;// Do Nothing
            } else if (EVENT_NM_NLGL0020SCRN00_ONCHANGE_UPL_TASKLIST.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_ONCHANGE_UPL_TASKLIST((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_CHECKALL.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_CHECK_ALL((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_DNLD_DELETEROW.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_DNLD_DELETE((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_DNLD_INSERTROW.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_DNLD_INSERT((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_DOWNLOADEDITTAB.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_DownloadEditTab((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_POLISTTAB.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_OnClick_POListTab((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_POSTATUSTAB.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_OnClick_POStatusTab((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_SEARCH.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_OnClick_Search((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_UNCHECKALL.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_UNCHECK_ALL((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_UPL_COPYROW.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_UPD_COPY((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_UPL_DELETEROW.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_UPD_DELETE((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_UPL_INSERTROW.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_UPD_INSERT((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_UPLOADEDITTAB.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_UPD_EDITTAB((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_UPD_CHECKALL.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_UPD_CHECK_ALL((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_UPD_UNCHECK_ALL.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_UPD_UNCHECK_ALL((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_UPD_SEARCH.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_OnClick_UPD_Search((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_DELETE.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_OnClick_Delete((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_SELECT_RETAIL_WH.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_SelectRetailWh((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_OPENWIN_RTLWH.equals(screenAplID)){
                doProcess_NLGL0020Scrn00_OpenWinRtlWh((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_GET_RTLWHNM.equals(screenAplID)){
                doProcess_NLGL0020Scrn00_OnClick_Get_RtlWhNm((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020_NWAL1130.equals(screenAplID)){
                doProcess_NLGL0020_NWAL1130((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else if (EVENT_NM_NLGL0020Scrn00_SelectWh.equals(screenAplID)){
                doProcess_NLGL0020Scrn00_SelectWh((NLGL0020CMsg) cMsg, (NLGL0020SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLGL0020Scrn00_SelectWh(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {
        createRelatedRtlWhPulldownList(bizMsg, getGlobalCompanyCode(), bizMsg.whCd_E2.getValue());
        bizMsg.invtyOwnrCd_E1.clear();
    }

    private void doProcess_NLGL0020_NWAL1130(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {
        // TODO 自動生成されたメソッドスタブ
        
    }

    private void doProcess_NLGL0020Scrn00_OnClick_Get_RtlWhNm(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_WH_CD, bizMsg.rtlWhCd_01.getValue());
        S21SsmEZDResult resultQuery = NLGL0020Query.getInstance().getRtlWhNm(ssmParam);
        if (!resultQuery.isCodeNotFound()) {
            String rtlWhNm = (String) resultQuery.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_01, rtlWhNm);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_01, "");
            bizMsg.rtlWhCd_01.setErrorInfo(1, NLZM2278E, new String[]{"WH Code"} );
        }
    }

    private void doProcess_NLGL0020Scrn00_OpenWinRtlWh(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {
        // TODO 自動生成されたメソッドスタブ
        
    }

    /**
     * The method explanation: The event[INIT] processing is executed.
     * @param bizMsg cMsg Business Component Interface Message
     * @param globalMsg sMsg Global area information
     */
    private void doProcess_NLGL0020_INIT(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        bizMsg.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_ID_LIST);
        createWHPulldownList(bizMsg, getGlobalCompanyCode());
        createRtlWhPulldownList(bizMsg, getGlobalCompanyCode());
        createDTPulldownList(bizMsg);
        createSerApvlReqFlgPulldownList(bizMsg, getGlobalCompanyCode());
        NLGL0020CommonLogic.createOrderTypePulldownList(bizMsg, getGlobalCompanyCode(), TAB_ID_LIST); 
        ZYPCodeDataUtil.createPulldownList(INVTY_OWNR.class, bizMsg.invtyOwnrCd_LC, bizMsg.invtyOwnrDescTxt_LD, DELIMITER_COLON);
        ZYPCodeDataUtil.createPulldownList("THIRD_PTY_DSP_TP", bizMsg.thirdPtyDspTpCd_LC, bizMsg.thirdPtyDspTpDescTxt_LD, DELIMITER_COLON); //TODO
        NLGL0020CommonLogic.createWmsTrxPulldownList(bizMsg.wmsTrxCd_01, bizMsg.fill40Txt_02, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCntDplyFlg_01, BLANK);
        bizMsg.xxSoSrchFromDt_01.setValue(NLGL0020CommonLogic.getBeforeMonth(ZYPDateUtil.getBatProcDate()));
        bizMsg.xxSoSrchThruDt_01.setValue(ZYPDateUtil.getBatProcDate());
        bizMsg.xxPageShowFromNum_A1.setValue(BigDecimal.ZERO);
        bizMsg.xxPageShowToNum_A1.setValue(BigDecimal.ZERO);
        bizMsg.xxPageShowOfNum_A1.setValue(BigDecimal.ZERO);
        bizMsg.xxChkBox_01.setValue(ZYPConstant.CHKBOX_ON_Y);
    }

    /**
     * PagePrev Event
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_PagePrev(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        // set values to items of pageNation for previous page
        // pageNation
        ZYPTableUtil.clear(bizMsg.A);
        bizMsg.xxPageShowFromNum_A1.setValue(bizMsg.xxPageShowFromNum_A1.getValueInt() - bizMsg.A.length());
        bizMsg.xxPageShowToNum_A1.clear();
        NLGL0020CommonLogic.copyFromSMsgOntoCmsg(bizMsg, globalMsg, getGlobalCompanyCode());
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0020Scrn00_SelectRetailWh(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLGL0020Scrn00_SelectRetailWh================================START", this);
        NLGL0020CMsg bizMsg = (NLGL0020CMsg) cMsg;
        for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
            bizMsg.F.no(i).packCdTxt_F1.clear();
        }
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_RTL_WH_CD, bizMsg.rtlWhCd_E1.getValue());
        S21SsmEZDResult resultQuery = NLGL0020Query.getInstance().getRelatedInvtyOwnerCd(ssmParam);
        if (!resultQuery.isCodeNotFound()) {
            String invtyOwnrCd = (String)resultQuery.getResultObject();
            bizMsg.invtyOwnrCd_E1.setValue(invtyOwnrCd);
            bizMsg.invtyOwnrCd_LC.no(0).setValue(invtyOwnrCd);
            bizMsg.invtyOwnrDescTxt_LD.no(0).setValue(invtyOwnrCd);
            
        }
        
        createPackCdPulldownList(bizMsg, getGlobalCompanyCode());
        EZDDebugOutput.println(1, "doProcess_NLGL0020Scrn00_SelectRetailWh================================END", this);
    }

    /**
     * PageNext Event
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_PageNext(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(bizMsg.A);
        bizMsg.xxPageShowFromNum_A1.setValue(bizMsg.xxPageShowToNum_A1.getValueInt() + 1);
        bizMsg.xxPageShowToNum_A1.clear();
        NLGL0020CommonLogic.copyFromSMsgOntoCmsg(bizMsg, globalMsg, getGlobalCompanyCode());
    }

    /**
     * Check All Click Event Check All List check box
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_CHECK_ALL(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        NLGL0020CommonLogic.setScrnItemValue_Check_UNCheck(bizMsg, true);
    }

    /**
     * UNCheck All Click Event UNCheck All List check box
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_UNCHECK_ALL(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        NLGL0020CommonLogic.setScrnItemValue_Check_UNCheck(bizMsg, false);
    }

    /**
     * Delete Click Event(DownLoad)
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_DNLD_DELETE(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        int rowcount = 0;

        for (int i = 0; i < bizMsg.F.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.F.no(i).xxChkBox_F1.getValue())) {
                continue;
            }
            EZDMsg.copy(bizMsg.F.no(i), null, bizMsg.F.no(rowcount), null);
            bizMsg.F.no(rowcount).wmsLineNum_F1.setValue(rowcount + 1);
            rowcount++;
        }

        for (int i = rowcount; i < bizMsg.F.getValidCount(); i++) {
            bizMsg.F.no(i).clear();
        }
        bizMsg.F.setValidCount(rowcount);
    }

    /**
     * Insert Click Event(DownLoad)
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_DNLD_INSERT(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        int newRownum = bizMsg.F.getValidCount();

        if (!ZYPCommonFunc.hasValue(bizMsg.xxSrchRqstDtTpCd_E2)) {
            bizMsg.setMessageInfo(NLGM0070E, new String[] {DATA_VALUE_SUBMIT_TYPE });
            return;
        }

        if (newRownum >= bizMsg.F.length()) {
            bizMsg.setMessageInfo(NLGM0054E, new String[] {FIELD_NAME_ITEM_INPUT });
            return;
        }
        bizMsg.F.setValidCount(newRownum + 1);

        EZDMsg.copy(globalMsg.F.no(newRownum), null, bizMsg.F.no(newRownum), null);

        bizMsg.F.no(newRownum).wmsUomCd_F1.clear();
        bizMsg.F.no(newRownum).wmsUomDescTxt_F1.clear();
        bizMsg.F.no(newRownum).wmsStkStsCd_F1.clear();
        bizMsg.F.no(newRownum).wmsStkStsNm_F1.clear();
        bizMsg.F.no(newRownum).wmsLineNum_F1.setValue(newRownum + 1);
        ZYPCodeDataUtil.createPulldownList(WMS_UOM.class, bizMsg.F.no(newRownum).wmsUomCd_F1 //
                , bizMsg.F.no(newRownum).wmsUomDescTxt_F1, DELIMITER_COLON);
        
        //check WMS Wh
        boolean tplLocFlag=true;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        S21SsmEZDResult dsWH = NLGL0020Query.getInstance().getWHPullDownList(ssmParam);
        List<Map> dsPoTpOutList = (List<Map>) dsWH.getResultObject();
        for (int i = 0; i < dsPoTpOutList.size(); i++) {
            Map pullDownData = dsPoTpOutList.get(i);
            if (bizMsg.whCd_E2.getValue().equals((String) pullDownData.get(DB_WH_CD))){
                tplLocFlag=false;
                break;
            }
        }
        if (tplLocFlag){
            bizMsg.F.no(newRownum).xxChkBox_F9.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            bizMsg.F.no(newRownum).xxChkBox_F9.setValue(ZYPConstant.FLG_OFF_N);
        }
            bizMsg.F.no(newRownum).wmsUomCd_F2.setValue(WMS_UOM.EACH);
        ZYPCodeDataUtil.createPulldownList(WMS_STK_STS.class, bizMsg.F.no(newRownum).wmsStkStsCd_F1 //
                , bizMsg.F.no(newRownum).wmsStkStsNm_F1, DELIMITER_COLON);
        bizMsg.F.no(newRownum).wmsStkStsCd_F2.setValue(WMS_STK_STS.GOOD);
    }

    /**
     * DownloadEditTab Click Event
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_DownloadEditTab(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        if (!NLGL0020CommonLogic.checkListCheckBoxCount(bizMsg)) {
            return;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_A1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.wmsPoId_E1, bizMsg.A.no(i).wmsPoId_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.wmsSqNum_G1, bizMsg.A.no(i).wmsSqNum_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.whCd_02, bizMsg.A.no(i).whCd_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_01, bizMsg.A.no(i).rtlWhCd_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.invtyOwnrCd_01, bizMsg.A.no(i).invtyOwnrCd_A1);
                break;
            }
        }

        if (!NLGL0020CommonLogic.isExistOrder(getGlobalCompanyCode(), bizMsg.whCd_02.getValue(), bizMsg.wmsSqNum_G1.getValue())) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, BLANK);
        NLGL0020CommonLogic.getDNLDOrderHeader(bizMsg, globalMsg, getGlobalCompanyCode());
        NLGL0020CommonLogic.copyFromSMsgOntoCmsgDNLD(bizMsg);
        NLGL0020CommonLogic.getDNLDOrderDetail(bizMsg, globalMsg, getGlobalCompanyCode());
        createWHPulldownList(bizMsg, getGlobalCompanyCode());
        createRelatedRtlWhPulldownList(bizMsg, getGlobalCompanyCode(), bizMsg.whCd_02.getValue());
        createSubmitPulldownList(bizMsg);

        String validCopyFlag = ZYPCodeDataUtil.getVarCharConstValue(NLGL_VALID_COPY_FLG, getGlobalCompanyCode());
        if (ZYPConstant.FLG_OFF_N.equals(validCopyFlag)) {
            bizMsg.xxSrchRqstDtTpCd_E2.setValue(TP_CD_RESEND);
        }

        bizMsg.whCd_E2.setValue(bizMsg.whCd_02.getValue());
    }

    /**
     * POListTab Click Event
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_OnClick_POListTab(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_ID_LIST);

        if (ZYPConstant.FLG_ON_1.equals(bizMsg.xxCntDplyFlg_01.getValue())) {
            doProcess_NLGL0020Scrn00_OnClick_Search(bizMsg, globalMsg);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCntDplyFlg_01, BLANK);
    }

    /**
     * POListTab Click Event
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_OnClick_POStatusTab(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_ID_STATUS);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_ID_STATUS);

        if (!NLGL0020CommonLogic.checkListCheckBoxCount(bizMsg)) {
            return;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_A1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.wmsPoId_B1, bizMsg.A.no(i).wmsPoId_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.wmsSqNum_B1, bizMsg.A.no(i).wmsSqNum_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.whCd_02, bizMsg.A.no(i).whCd_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_01, bizMsg.A.no(i).rtlWhCd_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.invtyOwnrCd_01, bizMsg.A.no(i).invtyOwnrCd_A1);
                break;
            }
        }

        if (!NLGL0020CommonLogic.isExistOrder(getGlobalCompanyCode(), bizMsg.whCd_02.getValue(), bizMsg.wmsSqNum_B1.getValue())) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        NLGL0020CommonLogic.getSTSOrderHeader(bizMsg, globalMsg, getGlobalCompanyCode());
        NLGL0020CommonLogic.getSTSOrderDetail(bizMsg, globalMsg, getGlobalCompanyCode());
        NLGL0020CommonLogic.getSTSUPDTransaction(bizMsg, globalMsg, getGlobalCompanyCode());
        NLGL0020CommonLogic.copyFromSMsgOntoCmsgSTATUS(bizMsg, globalMsg);
    }

    /**
     * Search Click Event
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_OnClick_Search(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        // get SO List Data
        NLGL0020CommonLogic.getPoList(bizMsg, globalMsg, getGlobalCompanyCode());
    }

    /**
     * Copy Click Event(Upload)
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_UPD_COPY(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        int newRownum = bizMsg.I.getValidCount();

        if (newRownum >= bizMsg.I.length()) {
            bizMsg.setMessageInfo(NLGM0054E, new String[] {NAME_FOR_MESSAGE_LIST });
            return;
        }

        for (int i = 0; i < bizMsg.I.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.I.no(i).xxChkBox_I1.getValue())) {
                EZDMsg.copy(bizMsg.I.no(i), null, bizMsg.I.no(newRownum), null);
                bizMsg.I.no(newRownum).xxChkBox_I1.clear();
                bizMsg.I.no(newRownum).wmsRecId_I1.clear();
                bizMsg.I.no(newRownum).wmsTrxDtTmTs_I1.clear();
                bizMsg.I.no(newRownum).xxDtTm_I1.clear();
                bizMsg.I.no(newRownum).ezInTime_I1.clear();
                bizMsg.I.no(newRownum).xxDtTm_I2.clear();
                bizMsg.I.no(newRownum).xxProcFlgNm_I1.setValue(UNPROCESSED);
                bizMsg.I.no(newRownum).procStsCd_I1.setValue(PROC_STS.IN_COMPLETED);
                bizMsg.I.no(newRownum).sceOrdTpCd_I2.setValue(bizMsg.sceOrdTpCd_I3.getValue());
                bizMsg.I.setValidCount(newRownum + 1);
                break;
            }
        }
        EZDMsg.copy(bizMsg, null, globalMsg, null);
    }

    /**
     * Delete Click Event(Upload)
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_UPD_DELETE(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        for (int i = 0; i < bizMsg.I.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.I.no(i).xxChkBox_I1.getValue()) // 
                    && (ZYPCommonFunc.hasValue(bizMsg.I.no(i).ezInTime_I1))) {
                bizMsg.setMessageInfo(NLGM0069E, new String[] {DATA_VALUE_DELETE });
                return;
            }
        }
        int rowcount = 0;

        EZDMsg.copy(bizMsg.I, null, bizMsg.I, null);

        for (int i = 0; i < bizMsg.I.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.I.no(i).xxChkBox_I1.getValue())) {
                continue;
            }
            EZDMsg.copy(bizMsg.I.no(i), null, bizMsg.I.no(rowcount), null);
            rowcount++;
        }

        for (int i = rowcount; i < bizMsg.I.getValidCount(); i++) {
            bizMsg.I.no(i).clear();
        }
        bizMsg.I.setValidCount(rowcount);
    }

    /**
     * Insert Click Event(Upload)
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_UPD_INSERT(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        int newRownum = bizMsg.I.getValidCount();

        if (newRownum >= bizMsg.I.length()) {
            bizMsg.setMessageInfo(NLGM0054E, new String[] {NAME_FOR_MESSAGE_LIST });
            return;
        }
        bizMsg.I.setValidCount(newRownum + 1);

        EZDMsg.copy(globalMsg.I.no(newRownum), null, bizMsg.I.no(newRownum), null);

        NLGL0020CommonLogic.createWmsTaskCodePulldownList(bizMsg.I.no(newRownum).wmsTaskCd_I1, bizMsg.I.no(newRownum).crsSvcTaskNm_I1, getGlobalCompanyCode(), false);
        ZYPCodeDataUtil.createCodePulldownList(WMS_STK_STS.class, bizMsg.I.no(newRownum).wmsStkStsCd_I1, bizMsg.I.no(newRownum).wmsStkStsNm_I1);
        ZYPCodeDataUtil.createCodePulldownList(WMS_PRCH_TP.class, bizMsg.I.no(newRownum).wmsOrdTpCd_I1, bizMsg.I.no(newRownum).wmsPrchTpNm_I2);
        NLGL0020CommonLogic.createOrderTypePulldownList(bizMsg, getGlobalCompanyCode(), TAB_ID_UPD);
        bizMsg.I.no(newRownum).xxProcFlgNm_I1.setValue(UNPROCESSED);
        bizMsg.I.no(newRownum).procStsCd_I1.setValue(PROC_STS.IN_COMPLETED);
        bizMsg.I.no(newRownum).sceOrdTpCd_I2.setValue(bizMsg.sceOrdTpCd_I3.getValue());
    }

    /**
     * UploadEditTab Click Event
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_UPD_EDITTAB(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        if (!NLGL0020CommonLogic.checkListCheckBoxCount(bizMsg)) {
            return;
        }

        bizMsg.xxChkBox_I2.setValue(ZYPConstant.CHKBOX_ON_Y);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_A1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.wmsPoId_I1, bizMsg.A.no(i).wmsPoId_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.wmsSqNum_I1, bizMsg.A.no(i).wmsSqNum_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.sceOrdTpCd_I3, bizMsg.A.no(i).sceOrdTpCd_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.whCd_02, bizMsg.A.no(i).whCd_A1);
                break;
            }
        }

        if (!NLGL0020CommonLogic.isExistOrder(getGlobalCompanyCode(), bizMsg.whCd_02.getValue(), bizMsg.wmsSqNum_I1.getValue())) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.wmsTaskCd_I4, VAL_WMS_TASK_CD_ALL);
        NLGL0020CommonLogic.getUPDDetail(bizMsg, globalMsg, getGlobalCompanyCode());
        NLGL0020CommonLogic.copyFromSMsgOntoCmsgUPD(bizMsg, globalMsg, getGlobalCompanyCode());
        NLGL0020CommonLogic.createWmsTaskPulldownList(bizMsg.wmsTaskCd_I3, bizMsg.crsSvcTaskNm_I3, getGlobalCompanyCode(), true, DELIMITER_COLON);
    }

    /**
     * Check All Click Event Check All List check box(Uplaod Edit)
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_UPD_CHECK_ALL(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        NLGL0020CommonLogic.setScrnItemValue_UPD_Check_UNCheck(bizMsg, true);
    }

    /**
     * UNCheck All Click Event UNCheck All List check box (Uplaod
     * Edit)
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_UPD_UNCHECK_ALL(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        NLGL0020CommonLogic.setScrnItemValue_UPD_Check_UNCheck(bizMsg, false);
    }

    /**
     * Upload search Click Event
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_OnClick_UPD_Search(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        if (!NLGL0020CommonLogic.checkListCheckBoxCount(bizMsg)) {
            return;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_A1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.wmsPoId_I1, bizMsg.A.no(i).wmsPoId_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.wmsSqNum_I1, bizMsg.A.no(i).wmsSqNum_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.sceOrdTpCd_I3, bizMsg.A.no(i).wmsOrdTpCd_A1);
                break;
            }
        }

        NLGL0020CommonLogic.getUPDDetail(bizMsg, globalMsg, getGlobalCompanyCode());
        NLGL0020CommonLogic.copyFromSMsgOntoCmsgUPD(bizMsg, globalMsg, getGlobalCompanyCode());
    }

    /**
     * Delete Click Event
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_OnClick_Submit(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        if (TAB_ID_UPD.equals(bizMsg.xxDplyTab.getValue())) {
            NLGL0020CommonLogic.getUPDDetail(bizMsg, globalMsg, getGlobalCompanyCode());
            NLGL0020CommonLogic.copyFromSMsgOntoCmsgUPD(bizMsg, globalMsg, getGlobalCompanyCode());
            NLGL0020CommonLogic.createWmsTaskPulldownList(bizMsg.wmsTaskCd_I3, bizMsg.crsSvcTaskNm_I3, getGlobalCompanyCode(), true, DELIMITER_COLON);
        }
    }

    /**
     * Delete Click Event
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_OnClick_Delete(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        if (!NLGL0020CommonLogic.checkListCheckBoxCount(bizMsg)) {
            return;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_A1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.wmsPoId_I1, bizMsg.A.no(i).wmsPoId_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.wmsSqNum_I1, bizMsg.A.no(i).wmsSqNum_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.sceOrdTpCd_I3, bizMsg.A.no(i).wmsOrdTpCd_A1);
                break;
            }
        }

        NLGL0020CommonLogic.getUPDDetail(bizMsg, globalMsg, getGlobalCompanyCode());
        NLGL0020CommonLogic.copyFromSMsgOntoCmsgUPD(bizMsg, globalMsg, getGlobalCompanyCode());
    }

    /**
     * Onchange Upload Edit Task Event
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     */
    private void doProcess_NLGL0020Scrn00_ONCHANGE_UPL_TASKLIST(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg) {

        NLGL0020CommonLogic.getUPDDetail(bizMsg, globalMsg, getGlobalCompanyCode());
        NLGL0020CommonLogic.copyFromSMsgOntoCmsgUPD(bizMsg, globalMsg, getGlobalCompanyCode());
        NLGL0020CommonLogic.createWmsTaskPulldownList(bizMsg.wmsTaskCd_I3, bizMsg.crsSvcTaskNm_I3, getGlobalCompanyCode(), true, DELIMITER_COLON);
    }

    /**
     * To create Pull down List(WHcode List for Search)
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     * @param glgbCmpyCd Global Company Code
     */
    private void createWHPulldownList(NLGL0020CMsg bizMsg, String glgbCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        S21SsmEZDResult dsWH = NLGL0020Query.getInstance().getWHPullDownList(ssmParam);

        List<Map> dsPoTpOutList = (List<Map>) dsWH.getResultObject();

        S21SsmEZDResult dsAdditionalWH = NLGL0020Query.getInstance().getAdditionalWHPullDownList(ssmParam);
        List<Map> dsAdditionalPoTpOutList = (List<Map>) dsAdditionalWH.getResultObject();
        
        if (TAB_ID_LIST.equals(bizMsg.xxDplyTab.getValue())) {
            bizMsg.whCd_01.clear();
            bizMsg.xxEdtCdNm_01.clear();

//            // Set ALL Value
//            bizMsg.whCd_01.no(0).setValue(WH_ALL_SELECTION_VALUE);
//            bizMsg.xxEdtCdNm_01.no(0).setValue(ZYPCommonFunc.concatString(WH_ALL_SELECTION_VALUE, DELIMITER_COLON, VAL_WH_CD_NM_ALL));

            for (int i = 0; i < dsPoTpOutList.size(); i++) {
                Map pullDownData = dsPoTpOutList.get(i);
                bizMsg.whCd_01.no(i).setValue((String) pullDownData.get(DB_WH_CD));
                bizMsg.xxEdtCdNm_01.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_WH_CD) //
                        , DELIMITER_COLON, (String) pullDownData.get(DB_WMS_DESC_NM)));
            }
            for (int i = 0; i < dsAdditionalPoTpOutList.size(); i++) {
                Map pullDownData = dsAdditionalPoTpOutList.get(i);
                bizMsg.whCd_01.no(dsPoTpOutList.size() + i).setValue((String) pullDownData.get(DB_WH_OWNR_CD));
                bizMsg.xxEdtCdNm_01.no(dsPoTpOutList.size() + i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_WH_OWNR_CD) //
                        , DELIMITER_COLON, (String) pullDownData.get(DB_WH_OWNR_DESC_TXT)));
            }
            
        } else {
            bizMsg.whCd_E1.clear();
            bizMsg.whCd_E2.clear();
            bizMsg.xxEdtCdNm_E1.clear();
            int cntdel = 0;

            for (int i = 0; i < dsPoTpOutList.size(); i++) {
                Map pullDownData = dsPoTpOutList.get(i);

                if (WH_ALL_VALUE.equals((pullDownData.get(DB_WH_CD)))) {
                    cntdel = cntdel + 1;
                    continue;
                }
                bizMsg.whCd_E1.no(i - cntdel).setValue((String) pullDownData.get(DB_WH_CD));
                bizMsg.xxEdtCdNm_E1.no(i - cntdel).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_WH_CD) //
                        , DELIMITER_COLON, (String) pullDownData.get(DB_WMS_DESC_NM)));
            }
            for (int i = 0; i < dsAdditionalPoTpOutList.size(); i++) {
                Map pullDownData = dsAdditionalPoTpOutList.get(i);
                bizMsg.whCd_E1.no(dsPoTpOutList.size() + i - cntdel).setValue((String) pullDownData.get(DB_WH_OWNR_CD));
                bizMsg.xxEdtCdNm_E1.no(dsPoTpOutList.size() + i - cntdel).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_WH_OWNR_CD) //
                        , DELIMITER_COLON, (String) pullDownData.get(DB_WH_OWNR_DESC_TXT)));
            }
            
            
        }
    }

    /**
     * To create Pull down List(WHcode List for Search)
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     * @param glgbCmpyCd Global Company Code
     */
    private void createRtlWhPulldownList(NLGL0020CMsg bizMsg, String glgbCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glgbCmpyCd);
        S21SsmEZDResult rtlWh = NLGL0020Query.getInstance().getRtlWhPullDownList(ssmParam);

        List<Map> rtlWhList = (List<Map>) rtlWh.getResultObject();

        if (TAB_ID_LIST.equals(bizMsg.xxDplyTab.getValue())) {
            bizMsg.rtlWhCd_LC.clear();
            bizMsg.xxEdtCdNm_LD.clear();

//            // Set ALL Value
//            bizMsg.rtlWhCd_LC.no(0).setValue(WH_ALL_SELECTION_VALUE);
//            bizMsg.xxEdtCdNm_LD.no(0).setValue(ZYPCommonFunc.concatString(WH_ALL_SELECTION_VALUE, DELIMITER_COLON, VAL_WH_CD_NM_ALL));

            for (int i = 0; i < rtlWhList.size(); i++) {
                Map pullDownData = rtlWhList.get(i);
                bizMsg.rtlWhCd_LC.no(i).setValue((String) pullDownData.get(DB_RTL_WH_CD));
                bizMsg.xxEdtCdNm_LD.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_RTL_WH_CD) //
                        , DELIMITER_COLON, (String) pullDownData.get(DB_RTL_WH_NM)));
            }
        }
    }

    /**
     * To create Pull down List(Retail WHcode List for Search)
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     * @param glblCmpyCd Global Company Code
     * @param whCd Warehouse Cd
     */
    private void createRelatedRtlWhPulldownList(NLGL0020CMsg bizMsg, String glblCmpyCd, String whCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_WH_CD, whCd );
        S21SsmEZDResult rtlWh = NLGL0020Query.getInstance().getRelatedRtlWhPullDownList(ssmParam);

        List<Map> rtlWhList = (List<Map>) rtlWh.getResultObject();
            bizMsg.rtlWhCd_LC.clear();
            bizMsg.xxEdtCdNm_LD.clear();

            for (int i = 0; i < rtlWhList.size(); i++) {
                Map pullDownData = rtlWhList.get(i);
                bizMsg.rtlWhCd_LC.no(i).setValue((String) pullDownData.get(DB_RTL_WH_CD));
                bizMsg.xxEdtCdNm_LD.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_RTL_WH_CD) //
                        , DELIMITER_COLON, (String) pullDownData.get(DB_RTL_WH_NM)));
            }
    }

    /**
     * To create Pull down List(WHcode List for Search)
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     * @param glgbCmpyCd Global Company Code
     */
    private void createPackCdPulldownList(NLGL0020CMsg bizMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(bizMsg.rtlWhCd_E1)) {
            bizMsg.fill40Txt_LC.clear();
            bizMsg.fill40Txt_LD.clear();
            return;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, bizMsg.rtlWhCd_E1.getValue());
        String flg = ZYPCodeDataUtil.getVarCharConstValue(WMS_PACK_CD_SET_OWNER_CD_FLG, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(flg)) {
            ssmParam.put(DB_PARAM_WMS_PACK_CD_SET_OWNER_CD_FLG, flg);
        } else {
            ssmParam.put(DB_PARAM_WMS_PACK_CD_SET_OWNER_CD_FLG, ZYPConstant.FLG_OFF_N);
        }

        S21SsmEZDResult packCd = NLGL0020Query.getInstance().getPackCdPullDownList(ssmParam);

        List<Map> packCdList = (List<Map>) packCd.getResultObject();

        for (int i = 0; i < packCdList.size() && i < bizMsg.fill40Txt_LC.length(); i++) {
            Map pullDownData = packCdList.get(i);
            bizMsg.fill40Txt_LC.no(i).setValue((String) pullDownData.get(DB_PACK_CD_TXT));
            bizMsg.fill40Txt_LD.no(i).setValue((String) pullDownData.get(DB_PACK_CD_TXT));
        }
    }

    /**
     * To create Pull down List(WHcode List for Search)
     * @param bizMsg NLGL0020CMsg
     * @param globalMsg NLGL0020SMsg
     * @param glgbCmpyCd Global Company Code
     */
    private void createSerApvlReqFlgPulldownList(NLGL0020CMsg bizMsg, String glgbCmpyCd) {

        bizMsg.serApvlReqFlg_LC.clear();
        bizMsg.serApvlReqFlg_LD.clear();

        // Set ALL Value
        bizMsg.serApvlReqFlg_LC.no(0).setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.serApvlReqFlg_LD.no(0).setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.serApvlReqFlg_LC.no(1).setValue(ZYPConstant.FLG_ON_Y);
        bizMsg.serApvlReqFlg_LD.no(1).setValue(ZYPConstant.FLG_ON_Y);
    }

    /**
     * To create Pull down List(Date List for Search)
     * @param bizMsg NLGL0020CMsg
     */
    private void createDTPulldownList(NLGL0020CMsg bizMsg) {

        bizMsg.xxSrchRqstDtTpCd_01.clear();
        bizMsg.xxEdtCdNm_02.clear();

        for (int i = 0; i < DATE_TYPE_CODE_LIST.length; i++) {
            bizMsg.xxEdtCdNm_02.no(i).setValue(DATE_TYPE_NAME_LIST[i]);
            bizMsg.xxSrchRqstDtTpCd_01.no(i).setValue(DATE_TYPE_CODE_LIST[i]);
        }
    }

    /**
     * To create Pull down List(Submit Type List for Download Screen)
     * @param bizMsg NLGL0020CMsg
     */
    private void createSubmitPulldownList(NLGL0020CMsg bizMsg) {

        bizMsg.xxSrchRqstDtTpCd_E2.clear();
        bizMsg.xxSrchRqstDtTpCd_E1.clear();
        bizMsg.xxEdtCdNm_E2.clear();

        for (int i = 0; i < SUBMIT_TYPE_CODE_LIST.length; i++) {
            bizMsg.xxEdtCdNm_E2.no(i).setValue(SUBMIT_TYPE_NAME_LIST[i]);
            bizMsg.xxSrchRqstDtTpCd_E1.no(i).setValue(SUBMIT_TYPE_CODE_LIST[i]);
        }
    }


}
