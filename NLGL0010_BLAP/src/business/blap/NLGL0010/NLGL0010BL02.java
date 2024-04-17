/**
 * <pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0010;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLGL0010.common.NLGL0010CommonLogic;
import business.blap.NLGL0010.constant.NLGL0010Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_FRT_OUT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_SHIP_VIA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * SO Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/20/2013   CSAI            Y.Miyauchi      Create          MW Replace Initial
 * 11/10/2015   CSAI            K.Lee           Update          S21NA Initial
 * 05/30/2017   CITS            S.Endo          Update          RS#3168
 * 07/03/2017   CITS            S.Endo          Update          QC#19042
 *</pre>
 */
public class NLGL0010BL02 extends S21BusinessHandler implements NLGL0010Constant {

    /**
     * this is a method of the execution after the SV
     * enent(setRequestData).
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLGL0010_INIT.equals(screenAplID)) {
                doProcess_NLGL0010_INIT((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_SOLIST_TAB.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_SOListTab((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_SOSTATUS_TAB.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_SOStatusTab((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_DOWNLOAD_EDIT_TAB.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_DownloadEditTab((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_UPLOAD_EDIT_TAB.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_UploadEditTab((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_SEARCH.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_Search((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_PAGE_PRE.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_PagePrev((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_PAGE_NEXT.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_PageNext((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_DNLD_INSERT_ROW.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_DNLD_InsertRow((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_DNLD_DELETE_ROW.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_DNLD_DeleteRow((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_CHECKALL.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_CheckAll((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_UNCHECK_ALL.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_UncheckAll((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCHANGE_SELECTSENDTYPE.equals(screenAplID)) {
                return;
            } else if (EVENT_NM_ONCLICK_UPD_INSERT_ROW.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_UPD_InsertRow((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_UPD_DELETE_ROW.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_UPD_DeleteRow((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_UPD_COPY_ROW.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_UPD_CopyRow((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_CMN_Submit((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_CMN_RESET.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_CMN_Reset((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_CMN_DELETE.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_CMN_Delete((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCHANGE_UPD_TASK_LIST.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnChange_UPD_TaskList((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_UPD_CHECKALL.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_UPD_CheckAll((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_UPD_UNCHECK_ALL.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_UPD_UncheckAll((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_UPD_SEARCH.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OnClick_UPD_Search((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_ONCLICK_GET_RTL_WH_NM.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_GetRetailWhNm((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_OPEN_WIN_WH.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_OpenWinWh((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_SELECT_WH.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_SelectWh((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_SELECT_SHIP_VIA.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_SelectShipVia((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLGL0010Scrn00_SelectShipVia(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        params.put(DB_PARAM_INVTY_OWNR_CD, cMsg.whCd_J2.getValue());
        S21SsmEZDResult result = NLGL0010Query.getInstance().get3PLWhCode(params);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        if (result.isCodeNotFound()) {
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            ssmParam.put(DB_PARAM_SHIP_VIA_CD, cMsg.tplSvcLvlCd_J2.getValue());

            S21SsmEZDResult ssl = NLGL0010Query.getInstance().getRelatedSSL(ssmParam);
            if (!ssl.isCodeNotFound()) {
                List<String> sslList = (List<String>) ssl.getResultObject();
                if (sslList.size() == 1) {
                    ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_J1, sslList.get(0));
                }
            }
        } else {
            //when 3PL
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            ssmParam.put(DB_PARAM_SHIP_VIA_CD, cMsg.tplSvcLvlCd_J2.getValue());
            ssmParam.put(DB_PARAM_INVTY_OWNR_CD, cMsg.whCd_J2.getValue());

            S21SsmEZDResult sslCarrier = NLGL0010Query.getInstance().get3PLRelatedSSLCarrier(ssmParam);
            if (!sslCarrier.isCodeNotFound()) {
                List<Map<String, String>> sslCarrierList = (List<Map<String, String>>) sslCarrier.getResultObject();
                if (sslCarrierList.size() == 1) {
                    ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_J1, sslCarrierList.get(0).get(DB_SHPG_SVC_LVL_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.carrCd_J1, sslCarrierList.get(0).get(DB_CARR_CD));
                }
            }
        }

    }

    private void doProcess_NLGL0010Scrn00_SelectWh(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_WH_CD, cMsg.whCd_J2.getValue());
        S21SsmEZDResult rtlWh = NLGL0010Query.getInstance().getRelatedRtlWhPullDownList(ssmParam);

        List<Map> rtlWhList = (List<Map>) rtlWh.getResultObject();
            cMsg.rtlWhCd_LC.clear();
            cMsg.xxEdtCdNm_LD.clear();

            for (int i = 0; i < rtlWhList.size(); i++) {
                Map pullDownData = rtlWhList.get(i);
                cMsg.rtlWhCd_LC.no(i).setValue((String) pullDownData.get(DB_RTL_WH_CD));
                cMsg.xxEdtCdNm_LD.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_RTL_WH_CD) //
                        , DELIMITER_COLON, (String) pullDownData.get(DB_RTL_WH_NM)));
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            params.put(DB_PARAM_INVTY_OWNR_CD, cMsg.whCd_J2.getValue());
            S21SsmEZDResult result = NLGL0010Query.getInstance().get3PLWhCode(params);
            if (!result.isCodeNotFound()) {
                NLGL0010CommonLogic.create3PLShipViaPulldownList(cMsg, getGlobalCompanyCode(), cMsg.whCd_J2.getValue());
            } else {
                ZYPCodeDataUtil.createPulldownList(WMS_SHIP_VIA_TP.class, cMsg.tplSvcLvlCd_J1, cMsg.xxEdtCdNm_J2, DELIMITER_COLON);
            }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[INIT] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010_INIT(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        cMsg.clear();
        cMsg.xxChkBox_01.setValue(ZYPConstant.CHKBOX_ON_Y);
        cMsg.xxPageShowFromNum_A1.setValue(BigDecimal.ZERO);
        cMsg.xxPageShowToNum_A1.setValue(BigDecimal.ZERO);
        cMsg.xxPageShowOfNum_A1.setValue(BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SO_LIST);
        NLGL0010CommonLogic.createWHPulldownList(cMsg, getGlobalCompanyCode());
        NLGL0010CommonLogic.createRtlWhPulldownList(cMsg, getGlobalCompanyCode());
        NLGL0010CommonLogic.createFlgPulldownList(cMsg, getGlobalCompanyCode());
        NLGL0010CommonLogic.createDTPulldownList(cMsg, getGlobalCompanyCode());
        NLGL0010CommonLogic.createWmsOrdTpPulldownList(cMsg.sceOrdTpCd_01, cMsg.xxEdtCdNm_02, getGlobalCompanyCode(), true, DELIMITER_COLON);
        NLGL0010CommonLogic.createWmsTrxPulldownList(cMsg.wmsTrxCd_01, cMsg.xxEdtCdNm_04, getGlobalCompanyCode());
        ZYPCodeDataUtil.createPulldownList(WMS_FRT_OUT.class, cMsg.wmsFrtOutCd_01, cMsg.xxEdtCdNm_03, DELIMITER_COLON);
        ZYPCodeDataUtil.createPulldownList(WMS_SHIP_VIA_TP.class, cMsg.wmsShipViaTpCd_01, cMsg.xxEdtCdNm_05, DELIMITER_COLON);
        ZYPCodeDataUtil.createPulldownList(INVTY_OWNR.class, cMsg.invtyOwnrCd_LC, cMsg.invtyOwnrDescTxt_LD, DELIMITER_COLON);
        cMsg.xxSoSrchFromDt_01.setValue(NLGL0010CommonLogic.getBeforeMonth(ZYPDateUtil.getBatProcDate()));
        cMsg.xxSoSrchThruDt_01.setValue(ZYPDateUtil.getBatProcDate());
        ZYPEZDItemValueSetter.setValue(cMsg.xxCntDplyFlg_01, BLANK);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick SO List Tab] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_SOListTab(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);

        if (ZYPConstant.FLG_ON_1.equals(cMsg.xxCntDplyFlg_01.getValue())) {
            doProcess_NLGL0010Scrn00_OnClick_Search(cMsg, sMsg);
        }
        ZYPEZDItemValueSetter.setValue(cMsg.xxCntDplyFlg_01, BLANK);

        if (sMsg.A.getValidCount() != 0) {
            NLGL0010CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick SO Status Tab] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_SOStatusTab(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        if (!NLGL0010CommonLogic.getChkNumOfCheckList(cMsg)) {
            // When it has error, finish the progress.
            return;
        }

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue())) {

                sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).xxChkBox_A1.setValue(cMsg.A.no(i).xxChkBox_A1.getValue());

                if (!NLGL0010CommonLogic.isExistOrder(getGlobalCompanyCode(), sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).whCd_A1.getValue(), sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).wmsSqNum_A1.getValue())) {
                    cMsg.setMessageInfo(NZZM0000E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(cMsg.whCd_02, cMsg.A.no(i).whCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_01, cMsg.A.no(i).rtlWhCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.invtyOwnrCd_01, cMsg.A.no(i).invtyOwnrCd_A1);
                // This method gets/sets SO Header Of Status
                NLGL0010CommonLogic.getSOHdrOfSts(cMsg, sMsg, getGlobalCompanyCode(), i);
                // This method gets/sets SO detail address Of Status
                NLGL0010CommonLogic.getSODtlAddr(cMsg, sMsg, getGlobalCompanyCode(), i);
                // This method gets/sets SO detail address Of Status
                NLGL0010CommonLogic.getSODtlInstn(cMsg, sMsg, getGlobalCompanyCode(), i);
                // This mesthod gets/sets SO detail detail Of Status
                NLGL0010CommonLogic.getSODtlDtl(cMsg, sMsg, getGlobalCompanyCode(), i);
                // This mesthod gets/sets SO detail upload transaction
                // Of Status.
                NLGL0010CommonLogic.getSODtlUpldTrn(cMsg, sMsg, getGlobalCompanyCode(), i);
                // This mesthod gets/sets SO detail ASN Of Status
                NLGL0010CommonLogic.getSODtlAsn(cMsg, sMsg, getGlobalCompanyCode(), i);
                // This mesthod gets/sets SO detail ASN Data Error Of
                // Status
                NLGL0010CommonLogic.getSODtlAsnErr(cMsg, sMsg, getGlobalCompanyCode(), i);

                break;
            } else {
                sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick Download Edti Tab] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_DownloadEditTab(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        if (!NLGL0010CommonLogic.getChkNumOfCheckList(cMsg)) {
            // When it has error, finish the progress.
            return;
        }

        ZYPTableUtil.clear(sMsg.K);
        ZYPTableUtil.clear(cMsg.K);
        sMsg.xxTpCd_J2.clear();
        cMsg.xxTpCd_J2.clear();
        sMsg.whCd_J2.clear();
        sMsg.xxRsdDt_J1.clear();
        sMsg.xxRddDt_J1.clear();
        sMsg.custOrdNum_J1.clear();
        sMsg.tplSvcLvlCd_J2.clear();
        sMsg.wmsFrtOutCd_J2.clear();

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue())) {
                sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).xxChkBox_A1.setValue(cMsg.A.no(i).xxChkBox_A1.getValue());

                if (!NLGL0010CommonLogic.isExistOrder(getGlobalCompanyCode(), sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).whCd_A1.getValue(), sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).wmsSqNum_A1.getValue())) {
                    cMsg.setMessageInfo(NZZM0000E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(cMsg.whCd_02, cMsg.A.no(i).whCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_01, cMsg.A.no(i).rtlWhCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.invtyOwnrCd_01, cMsg.A.no(i).invtyOwnrCd_A1);

                // This method gets/sets SO Header Of download
                NLGL0010CommonLogic.getSOHdrOfDNLD(cMsg, sMsg, getGlobalCompanyCode(), i);
                // This method gets/sets SO detail address Of DownLoad
                NLGL0010CommonLogic.getSODtlAddrOfDNLD(cMsg, sMsg, getGlobalCompanyCode(), i);
                // This method gets/sets SO detail Shipping
                // Instructions Of download
                NLGL0010CommonLogic.getSODtlInstnOfDNLD(cMsg, sMsg, getGlobalCompanyCode(), i);
                // This mesthod gets/sets SO detail detail Of download
                NLGL0010CommonLogic.getSODtlDtlOfDNLD(cMsg, sMsg, getGlobalCompanyCode(), i);

                NLGL0010CommonLogic.createWHPulldownList_DNLD(cMsg, getGlobalCompanyCode());

                Map<String, Object> params = new HashMap<String, Object>();
                params.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
                params.put(DB_PARAM_INVTY_OWNR_CD, cMsg.whCd_02.getValue());
                S21SsmEZDResult result = NLGL0010Query.getInstance().get3PLWhCode(params);
                if (!result.isCodeNotFound()) {
                    NLGL0010CommonLogic.create3PLShipViaPulldownList(cMsg, getGlobalCompanyCode(), cMsg.whCd_02.getValue());
                } else {
                    ZYPCodeDataUtil.createPulldownList(WMS_SHIP_VIA_TP.class, cMsg.tplSvcLvlCd_J1, cMsg.xxEdtCdNm_J2, DELIMITER_COLON);
                }

                ZYPCodeDataUtil.createPulldownList(WMS_FRT_OUT.class, cMsg.wmsFrtOutCd_J1, cMsg.xxEdtCdNm_J3, DELIMITER_COLON);
                NLGL0010CommonLogic.createSendTypePulldownList(cMsg);

                cMsg.whCd_J2.setValue(cMsg.whCd_02.getValue());
                cMsg.rtlWhCd_J1.setValue(cMsg.rtlWhCd_01.getValue());
                cMsg.invtyOwnrCd_J1.setValue(cMsg.invtyOwnrCd_01.getValue());

                NLGL0010CommonLogic.createPackCdPulldownList(cMsg, getGlobalCompanyCode());

                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).estShipDtTmTs_A1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.xxRsdDt_J1, NLGL0010CommonLogic.changeDateformat(cMsg.A.no(i).estShipDtTmTs_A1.getValue(), UNSET_MMSS));
                }

                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).estShipDtTmTs_A1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.xxRddDt_J1, NLGL0010CommonLogic.changeDateformat(cMsg.A.no(i).wmsRqstDtTmTs_A1.getValue(), UNSET_MMSS));
                }
                ZYPEZDItemValueSetter.setValue(cMsg.custOrdNum_J1, cMsg.A.no(i).custOrdNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.tplSvcLvlCd_J2, cMsg.A.no(i).soShipViaCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.wmsFrtOutCd_J2, cMsg.A.no(i).frtOutCd_A1.getValue());

                ZYPEZDItemValueSetter.setValue(cMsg.invtyOwnrCd_J1, cMsg.A.no(i).invtyOwnrCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.schdDelyDt_J1, cMsg.A.no(i).schdDelyDt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.carrCd_J1, cMsg.A.no(i).carrCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_J1, cMsg.A.no(i).shpgSvcLvlCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.rtrnItemInclFlg_J1, cMsg.A.no(i).rtrnItemInclFlg_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.svcConfigMstrPk_J1, cMsg.A.no(i).svcConfigMstrPk_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.asmReqFlg_J1, cMsg.A.no(i).asmReqFlg_A1.getValue());

                String validCopyFlag = ZYPCodeDataUtil.getVarCharConstValue(NLGL_VALID_COPY_FLG, getGlobalCompanyCode());
                if (ZYPConstant.FLG_OFF_N.equals(validCopyFlag)) {
                    cMsg.xxTpCd_J2.setValue(TP_CD_RESEND);
                }
            } else {
                sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
        doProcess_NLGL0010Scrn00_SelectWh(cMsg, sMsg);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick Upload Edit Tab] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_UploadEditTab(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        if (!NLGL0010CommonLogic.getChkNumOfCheckList(cMsg)) {
            // When it has error, finish the progress.
            return;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.wmsTaskCd_O4, VAL_WMS_TASK_CD_ALL);
        NLGL0010CommonLogic.createWmsTaskPulldownList(cMsg.wmsTaskCd_O3, cMsg.xxEdtCdNm_O6, getGlobalCompanyCode(), true, DELIMITER_COLON);

        ZYPTableUtil.clear(sMsg.O);
        ZYPTableUtil.clear(cMsg.O);

        cMsg.xxChkBox_O2.setValue(ZYPConstant.CHKBOX_ON_Y);

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue())) {
                sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).xxChkBox_A1.setValue(cMsg.A.no(i).xxChkBox_A1.getValue());

                if (!NLGL0010CommonLogic.isExistOrder(getGlobalCompanyCode(), sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).whCd_A1.getValue(), sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).wmsSqNum_A1.getValue())) {
                    cMsg.setMessageInfo(NZZM0000E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(cMsg.whCd_02, cMsg.A.no(i).whCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_01, cMsg.A.no(i).rtlWhCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.invtyOwnrCd_01, cMsg.A.no(i).invtyOwnrCd_A1);

                // This method gets/sets Upload data list
                NLGL0010CommonLogic.getUploadEditList(cMsg, sMsg, getGlobalCompanyCode(), i);
            } else {
                sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[Onchange Upload Edit Task List] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnChange_UPD_TaskList(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        ZYPTableUtil.clear(sMsg.O);
        ZYPTableUtil.clear(cMsg.O);
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue())) {
                sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).xxChkBox_A1.setValue(cMsg.A.no(i).xxChkBox_A1.getValue());

                // This method gets/sets Upload data list
                NLGL0010CommonLogic.getUploadEditList(cMsg, sMsg, getGlobalCompanyCode(), i);
            } else {
                sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick UPD Search] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_UPD_Search(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        ZYPTableUtil.clear(sMsg.O);
        ZYPTableUtil.clear(cMsg.O);
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue())) {
                sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).xxChkBox_A1.setValue(cMsg.A.no(i).xxChkBox_A1.getValue());

                // This method gets/sets Upload data list
                NLGL0010CommonLogic.getUploadEditList(cMsg, sMsg, getGlobalCompanyCode(), i);
            } else {
                sMsg.A.no(cMsg.xxPageShowFromNum_A1.getValueInt() + i - 1).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick UPD  CheckAll] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_UPD_CheckAll(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        NLGL0010CommonLogic.setUPDListChkValue(cMsg, ZYPConstant.FLG_ON_Y);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick UPD UnCheckAll] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_UPD_UncheckAll(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        NLGL0010CommonLogic.setUPDListChkValue(cMsg, ZYPConstant.FLG_OFF_N);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick Search] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_Search(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        // This method gets SO List Data
        NLGL0010CommonLogic.getSOList(cMsg, sMsg, getGlobalCompanyCode());
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick Page Pre] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_PagePrev(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        // This method resets(sets Flag Off) the Check Flag
        NLGL0010CommonLogic.setUnChk_SOList(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum_A1.setValue(cMsg.xxPageShowFromNum_A1.getValueInt() - cMsg.A.length());
        cMsg.xxPageShowToNum_A1.clear();
        NLGL0010CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick Page Next] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_PageNext(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        // This method resets(sets Flag Off) the Check Flag
        NLGL0010CommonLogic.setUnChk_SOList(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum_A1.setValue(cMsg.xxPageShowToNum_A1.getValueInt() + 1);
        cMsg.xxPageShowToNum_A1.clear();
        NLGL0010CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick DNLD InsertRow] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_DNLD_InsertRow(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        if (cMsg.K.getValidCount() == cMsg.K.length()) {
            cMsg.setMessageInfo(NLGM0054E, new String[] {DATA_ROW });
            return;
        }

        cMsg.K.no(cMsg.K.getValidCount()).wmsLineNum_K1.setValue(cMsg.K.getValidCount() + 1);
        ZYPCodeDataUtil.createPulldownList(WMS_STK_STS.class, cMsg.K.no(cMsg.K.getValidCount()).wmsStkStsCd_K1, cMsg.K.no(cMsg.K.getValidCount()).xxEdtCdNm_K2, DELIMITER_COLON);
        ZYPCodeDataUtil.createPulldownList(WMS_STK_STS.class, cMsg.K.no(cMsg.K.getValidCount()).wmsStkStsCd_K3, cMsg.K.no(cMsg.K.getValidCount()).xxEdtCdNm_K4, DELIMITER_COLON);
        ZYPCodeDataUtil.createPulldownList(WMS_UOM.class, cMsg.K.no(cMsg.K.getValidCount()).wmsUomCd_K1, cMsg.K.no(cMsg.K.getValidCount()).xxEdtCdNm_K1, DELIMITER_COLON);
        cMsg.K.no(cMsg.K.getValidCount()).wmsStkStsCd_K2.setValue(WMS_STK_STS.GOOD);
        cMsg.K.no(cMsg.K.getValidCount()).wmsUomCd_K2.setValue(WMS_UOM.EACH);

        cMsg.K.setValidCount(cMsg.K.getValidCount() + 1);

        EZDMsg.copy(cMsg.K, null, sMsg.K, null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick DNLD DeleteRow] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_DNLD_DeleteRow(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        int rowcount = 0;

        for (int i = 0; i < cMsg.K.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.K.no(i).xxChkBox_K1.getValue())) {
                continue;
            }
            EZDMsg.copy(cMsg.K.no(i), null, cMsg.K.no(rowcount), null);
            cMsg.K.no(rowcount).wmsLineNum_K1.setValue(rowcount + 1);
            rowcount++;
        }

        for (int i = rowcount; i < cMsg.K.getValidCount(); i++) {
            cMsg.K.no(i).clear();
        }
        cMsg.K.setValidCount(rowcount);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick CheckAll] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_CheckAll(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        NLGL0010CommonLogic.setDNLDItemInputChkValue(cMsg, ZYPConstant.FLG_ON_Y);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick UnCheckAll] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_UncheckAll(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        NLGL0010CommonLogic.setDNLDItemInputChkValue(cMsg, ZYPConstant.FLG_OFF_N);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick DNLD InsertRow] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_UPD_InsertRow(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        int newRownum = cMsg.O.getValidCount();
        ZYPEZDItemValueSetter.setValue(sMsg.xxBtnFlg_01 , ZYPConstant.FLG_OFF_0);

        if (newRownum >= cMsg.O.length()) {
            cMsg.setMessageInfo(NLGM0054E, new String[] {DATA_ROW });
            return;
        }
        cMsg.O.setValidCount(newRownum + 1);
        EZDMsg.copy(sMsg.O.no(newRownum), null, cMsg.O.no(newRownum), null);

        cMsg.O.no(newRownum).fill30Txt_O1.setValue(UN_PROCESSED);
        cMsg.O.no(newRownum).otbdOrdTpCd_O2.setValue(NLGL0010CommonLogic.getChksceOrdTpCdFromSOList(cMsg));
        cMsg.O.no(newRownum).wmsOrgHostId_O1.setValue(NLGL0010CommonLogic.getOrgHostIdFromWH(cMsg, getGlobalCompanyCode()));

        NLGL0010CommonLogic.createWmsTaskCodePulldownList(cMsg.O.no(newRownum).wmsTaskCd_O1, cMsg.O.no(newRownum).xxEdtCdNm_O1, getGlobalCompanyCode(), false);
        ZYPCodeDataUtil.createCodePulldownList(WMS_ORD_STS.class, cMsg.O.no(newRownum).wmsOrdStsCd_O1, cMsg.O.no(newRownum).xxEdtCdNm_O2);
        ZYPCodeDataUtil.createCodePulldownList(WMS_STK_STS.class, cMsg.O.no(newRownum).wmsStkStsCd_O1, cMsg.O.no(newRownum).xxEdtCdNm_O3);
        NLGL0010CommonLogic.createWmsOrdTpCodePulldownList(cMsg.O.no(newRownum).otbdOrdTpCd_O1, cMsg.O.no(newRownum).xxEdtCdNm_O4, getGlobalCompanyCode(), true);
        NLGL0010CommonLogic.createWmsOrdTpCodePulldownList(cMsg.O.no(newRownum).inbdOrdTpCd_O1, cMsg.O.no(newRownum).xxEdtCdNm_O5, getGlobalCompanyCode(), false);

        EZDMsg.copy(cMsg.O, null, sMsg.O, null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick DNLD DeleteRow] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_UPD_DeleteRow(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(sMsg.xxBtnFlg_01 , ZYPConstant.FLG_OFF_0);
        for (int i = 0; i < cMsg.O.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.O.no(i).xxChkBox_O1.getValue()) // 
                    && (ZYPCommonFunc.hasValue(cMsg.O.no(i).ezInTime_O1))) {
                cMsg.setMessageInfo(NLGM0069E, new String[] {DATA_VALUE_DELETE });
                return;
            }
        }

        int rowcount = 0;
        for (int i = 0; i < cMsg.O.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.O.no(i).xxChkBox_O1.getValue())) {
                continue;
            }
            EZDMsg.copy(cMsg.O.no(i), null, cMsg.O.no(rowcount), null);
            rowcount++;
        }

        for (int i = rowcount; i < cMsg.O.getValidCount(); i++) {
            cMsg.O.no(i).clear();
        }
        cMsg.O.setValidCount(rowcount);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[Update CopyRow] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OnClick_UPD_CopyRow(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        int newRownum = cMsg.O.getValidCount();
        ZYPEZDItemValueSetter.setValue(sMsg.xxBtnFlg_01 , ZYPConstant.FLG_OFF_0);

        if (newRownum >= cMsg.O.length()) {
            cMsg.setMessageInfo(NLGM0054E, new String[] {DATA_ROW });
            return;
        }

        for (int i = 0; i < cMsg.O.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.O.no(i).xxChkBox_O1.getValue())) {
                EZDMsg.copy(cMsg.O.no(i), null, cMsg.O.no(newRownum), null);
                ZYPEZDItemValueSetter.setValue(cMsg.O.no(newRownum).xxChkBox_O1, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cMsg.O.no(newRownum).fill30Txt_O1, UN_PROCESSED);
                ZYPEZDItemValueSetter.setValue(cMsg.O.no(newRownum).wmsOrgHostId_O1, NLGL0010CommonLogic.getOrgHostIdFromWH(cMsg, getGlobalCompanyCode()));
                cMsg.O.no(newRownum).wmsRecId_O1.clear();
                cMsg.O.no(newRownum).wmsTrxDtTmTs_O1.clear();
                cMsg.O.no(newRownum).xxDtTm_O1.clear();
                cMsg.O.no(newRownum).ezInTime_O1.clear();
                cMsg.O.no(newRownum).xxDtTm_O2.clear();
                cMsg.O.setValidCount(newRownum + 1);
                break;
            }
        }
        EZDMsg.copy(cMsg, null, sMsg, null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[Reset] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_CMN_Reset(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        doProcess_NLGL0010_INIT(cMsg, sMsg);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[Submit] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_CMN_Submit(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        if (TAB_SO_UPLD_EDT.equals(cMsg.xxDplyTab.getValue())) {

            // When Tab is Update, do same as onclick upload edit tab
            doProcess_NLGL0010Scrn00_OnClick_UPD_Search(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[Delete] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_CMN_Delete(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        if (TAB_SO_UPLD_EDT.equals(cMsg.xxDplyTab.getValue())) {

            // When Tab is Update, do same as onclick upload edit tab
            doProcess_NLGL0010Scrn00_OnClick_UPD_Search(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_GetRetailWhNm(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLGL0010Scrn00_GetRetailWhNm================================START", this);
        NLGL0010CMsg bizMsg = (NLGL0010CMsg) cMsg;
        bizMsg.rtlWhNm_01.clear();

        NLGL0010CommonLogic.getRetailWhNm(bizMsg, getGlobalCompanyCode());
        EZDDebugOutput.println(1, "doProcess_NLGL0010Scrn00_GetRetailWhNm================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_SelectRetailWh(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLGL0020Scrn00_SelectRetailWh================================START", this);
        NLGL0010CMsg bizMsg = (NLGL0010CMsg) cMsg;
        for (int i = 0; i < bizMsg.K.getValidCount(); i++) {
            bizMsg.K.no(i).packCdTxt_K1.clear();
        }

        NLGL0010CommonLogic.createPackCdPulldownList(bizMsg, getGlobalCompanyCode());
        EZDDebugOutput.println(1, "doProcess_NLGL0020Scrn00_SelectRetailWh================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_OpenWinWh(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLGL0010Scrn00_OpenWinWh================================START", this);
        NLGL0010CMsg bizMsg = (NLGL0010CMsg) cMsg;
/*
        for (int i = 0; i < bizMsg.K.getValidCount(); i++) {
            bizMsg.K.no(i).packCdTxt_K1.clear();
        }

        NLGL0010CommonLogic.createPackCdPulldownList(bizMsg, getGlobalCompanyCode());
*/
        EZDDebugOutput.println(1, "doProcess_NLGL0010Scrn00_OpenWinWh================================END", this);
    }


}
