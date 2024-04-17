/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2030;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLAL2030.common.NLAL2030CommonLogic;
import business.blap.NLAL2030.constant.NLAL2030Constant;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.INBD_VISTMsg;
import business.db.INBD_VISTMsgArray;
import business.db.PO_DTLTMsg;
import business.db.PO_RCV_DTLTMsg;
import business.db.PO_RCV_HDRTMsg;
import business.db.RTL_WHTMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_DTLTMsgArray;
import business.db.RWS_HDRTMsg;
import business.db.RWS_HDRTMsgArray;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORDTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTR_HISTTMsg;
import business.db.WH_SCHDTMsg;
import business.db.WH_SCHDTMsgArray;
import business.db.WHTMsg;
import business.db.WHTMsgArray;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC201001PMsg;
import business.parts.NLZC210001PMsg;
import business.parts.NLZC304001PMsg;
import business.parts.NPZC109001PMsg;
import business.parts.NPZC109001_detailListPMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC153001PMsg;
import business.parts.NWZC153001_ordRtrnDtlListPMsg;
import business.parts.NWZC153002PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NLZ.NLZC201001.NLZC201001;
import com.canon.cusa.s21.api.NLZ.NLZC210001.NLZC210001;
import com.canon.cusa.s21.api.NLZ.NLZC304001.NLZC304001;
import com.canon.cusa.s21.api.NPZ.NPZC109001.NPZC109001;
import com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC153001.NWZC153001;
import com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC018001.NLXRWSReport;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DRCT_SHIP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PUT_AWAY_CHK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NLAL2030BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 * 06/06/2016   CSAI            Y.Imazu         Update          QC#9553
 * 07/08/2016   CSAI            Y.Imazu         Update          QC#7755
 * 08/10/2017   CITS            S.Katsuma       Update          QC#19232
 * 09/21/2017   CITS            S.Endo          Update          Sol#069(QC#18270)
 * 01/15/2018   CITS            K.Ogino         Update          QC#23438
 * 02/06/2018   CITS            T.Tokutomi      Update          QC#18461-Sol#085
 * 02/21/2018   CITS            K.Ogino         Update          QC#24070
 * 03/07/2018   CITS            K.Ogino         Update          QC#24413
 * 08/15/2018   CITS            K.Ogino         Update          QC#27775
 * 12/03/2018   Fujitsu         S.Ohki          Update          QC#29461
 * 02/25/2019   CITS            K.Ogino         Update          QC#30456
 * 09/28/2019   CITS            M.Naito         Update          QC#53299
 * 01/27/2020   Fujitsu         T.Ogura         Update          QC#55497
 * 02/01/2020   CITS            K.Ogino         Update          QC#55313
 * 12/14/2020   CITS            A.Marte         Update          QC#58069
 * 03/09/2021   CITS            F.Deola         Update          QC#58375
 * 03/22/2021   CITS            A.Marte         Update          QC#57124
 * 08/09/2021   CITS            A.Marte         Update          QC#58968
 * 11/11/2021   CITS            A.Marte         Update          QC#59350
 * 10/08/2022   CITS            K.Iwamoto       Update          QC#60364
 * 10/26/2022   Hitachi         M.Kikushima     Update          QC#60413
 * 11/23/2022   CITS            R.Azucena       Update          QC#60835
 * 12/13/2022   CITS            R.Azucena       Update          QC#60905
 * 03/02/2023   CITS            F.Fadriquela    Update          QC#61225
 *</pre>
 */
public class NLAL2030BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NLAL2030CMsg bizMsg = (NLAL2030CMsg) cMsg;
            NLAL2030SMsg glblMsg = (NLAL2030SMsg) sMsg;

            if ("NLAL2030Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_SaveSearch(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_RWS_Create".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_RWS_Create(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_RWS_Cancel".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_RWS_Cancel(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_Print".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_Print(bizMsg, glblMsg);

            } else if ("NLAL2030Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_DeleteSearch(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {

            bizMsg.srchOptPk_S.setErrorInfo(1, NLAL2030Constant.NLZM2274E, new String[] {converter.convLabel2i18nLabel(NLAL2030Constant.SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NLAL2030CommonLogic.callNszc0330forDeleteSearch(bizMsg, glblMsg, getContextUserInfo().getUserId());
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_SaveSearch(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {

            bizMsg.srchOptNm_S.setErrorInfo(1, NLAL2030Constant.ZZM9000E, new String[] {converter.convLabel2i18nLabel(NLAL2030Constant.SCRN_ID_00, "Search Option Name") });
            return;
        }

        if (NLAL2030CommonLogic.isExistSaveSearchName(bizMsg)) {

            bizMsg.srchOptNm_S.setErrorInfo(1, NLAL2030Constant.NLZM2273E, new String[] {converter.convLabel2i18nLabel(NLAL2030Constant.SCRN_ID_00, "Search Option Name") });
            return;
        }

        NLAL2030CommonLogic.callNszc0330forSaveSearch(bizMsg, glblMsg, getContextUserInfo().getUserId());
    }

    /**
     * RWS_Create Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_RWS_Create(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        NLAL2030CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // Input Check
        if (!checkInputRWSCreate(bizMsg, glblMsg)) {

            return;
        }

        // TimeStamp Check
        if (!checkTimeStampRWSCreate(bizMsg, glblMsg)) {

            return;
        }

        // Create RWS
        if (!createRWS(bizMsg, glblMsg)) {

            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    /**
     * RWS_Cancel Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_RWS_Cancel(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        NLAL2030CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // Input Check
        if (!checkInputRWSCancel(bizMsg, glblMsg)) {

            bizMsg.xxWrnSkipFlg.clear();
            // START 2022/10/26 M.Kikushima [QC#60413,ADD]
            bizMsg.xxWrnSkipFlg_CA.clear();
            // END 2022/10/26 M.Kikushima [QC#60413,ADD]
            return;
        }

        // Warning
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue())) {

            if (!checkInputWarningRWSCancel(bizMsg, glblMsg)) {

                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                return;
            }

        } else if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue()) && changeValAfterWarning(bizMsg, glblMsg)) {

            // Not Skip warning process.
            if (!checkInputWarningRWSCancel(bizMsg, glblMsg)) {

                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                return;
            }
        // START 2022/10/26 M.Kikushima [QC#60413,ADD]
        } else if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_CA.getValue())) {

            if (validateForCancel(bizMsg, glblMsg)) {

                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_CA, ZYPConstant.FLG_ON_Y);
                return;
            }

        // END 2022/10/26 M.Kikushima [QC#60413,ADD]
        }

        bizMsg.xxWrnSkipFlg.clear();
        // START 2022/10/26 M.Kikushima [QC#60413,ADD]
        bizMsg.xxWrnSkipFlg_CA.clear();
        // END 2022/10/26 M.Kikushima [QC#60413,ADD]

        // Cancel RWS
        if (!cancelRws(bizMsg, glblMsg)) {

            return;
        }

        bizMsg.setMessageInfo(NLAL2030Constant.ZZZM9003I, new String[] {"Cancel RWS" });
    }

    /**
     * Print Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_Print(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        NLAL2030CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // Input Check
        if (!NLAL2030CommonLogic.checkInputRWSPrint(bizMsg, glblMsg)) {

            return;
        }

        List<String> xxMsgIdList = new ArrayList<String>();
        HashSet<String> rwsNumSet = new HashSet<String>();

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            NLAL2030_BSMsg lineMsg = glblMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(lineMsg.xxChkBox_B1.getValue()) && !rwsNumSet.contains(lineMsg.rwsNum_B1.getValue())) {

                rwsNumSet.add(lineMsg.rwsNum_B1.getValue());

                // Check TimeStamp
                RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.rwsNum, lineMsg.rwsNum_B1.getValue());
                rwsHdrTMsg = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rwsHdrTMsg);

                if (rwsHdrTMsg != null) {

                    if (!ZYPDateUtil.isSameTimeStamp(lineMsg.ezUpTime_RH.getValue(), lineMsg.ezUpTimeZone_RH.getValue(), rwsHdrTMsg.ezUpTime.getValue(), rwsHdrTMsg.ezUpTimeZone.getValue())) {

                        lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                        bizMsg.setMessageInfo(NLAL2030Constant.NLBM0009E);
                        return;
                    }

                } else {

                    lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                    bizMsg.setMessageInfo(NLAL2030Constant.NLBM0009E);
                    return;
                }
            }
        }

        NLXRWSReport rwsReport = new NLXRWSReport();
        String procStartTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstTs, procStartTs);
        List<String> rwsNumList = new ArrayList<String>(rwsNumSet);

        try {

            String returnCode = rwsReport.setRwsRptWrk(bizMsg.glblCmpyCd.getValue(), getContextUserInfo().getUserId(), procStartTs, rwsNumList, xxMsgIdList);

            if (NLXRWSReport.RTN_CD_ERROR.equals(returnCode)) {

                bizMsg.setMessageInfo(NLAL2030Constant.NLAM0043E, new String[] {"Print" });
                return;
            }

        } catch (SQLException ex) {

            bizMsg.setMessageInfo(NLAL2030Constant.NLAM0043E, new String[] {"Print" });
            return;
        }
    }

    /**
     * checkInputRWSCreate
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @return boolean
     */
    private boolean checkInputRWSCreate(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        int cntChkOn = 0;
        HashSet<String> unitRwsSet = new HashSet<String>();
        StringBuilder unitRwsBf = new StringBuilder();
        String rtlWhCd = "";
        int firstErrIdx = -1;

        // Count CheckBox-On
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A2.getValue())) {

                cntChkOn++;

                unitRwsBf = new StringBuilder();

                if (NLAL2030Constant.RWS_CREATE_MODE_PO.equals(glblMsg.A.no(i).procModeCd_A1.getValue())) {

                    unitRwsBf.append(glblMsg.A.no(i).trxOrdNum_A1.getValue());
                    unitRwsBf.append(glblMsg.A.no(i).rtlWhCd_A1.getValue());

                } else if (NLAL2030Constant.RWS_CREATE_MODE_RTRN.equals(glblMsg.A.no(i).procModeCd_A1.getValue())) {

                    unitRwsBf.append(glblMsg.A.no(i).trxOrdNum_A1.getValue());
                    unitRwsBf.append(glblMsg.A.no(i).dsOrdPosnNum_A1.getValue());
                    unitRwsBf.append(glblMsg.A.no(i).rtlWhCd_A1.getValue());

                } else {

                    // Can not create RWS
                    glblMsg.A.no(i).xxChkBox_A2.setErrorInfo(1, NLAL2030Constant.NLAM1322E);

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }

                unitRwsSet.add(unitRwsBf.toString());
                rtlWhCd = glblMsg.A.no(i).rtlWhCd_A1.getValue();
            }
        }

        // All Un-Select
        if (cntChkOn == 0) {

            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                glblMsg.A.no(i).xxChkBox_A2.setErrorInfo(1, NLAL2030Constant.NLAM1288E);
            }

            bizMsg.setMessageInfo(NLAL2030Constant.NLAM1288E);
            firstErrIdx = 0;
        }

        if (0 <= firstErrIdx) {

            bizMsg.xxPageShowFromNum_A.setValue(firstErrIdx);
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return false;
        }

        if (1 < cntChkOn && 1 < unitRwsSet.size()) {

            // Different unit for creating RWS.
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A2.getValue())) {

                    glblMsg.A.no(i).xxChkBox_A2.setErrorInfo(1, NLAL2030Constant.NLAM1323E);

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }
            }

            bizMsg.xxPageShowFromNum_A.setValue(firstErrIdx);
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return false;
        }

        // START 2019/09/28 M.Naito [QC#53299,MOD]
        // Check Shipment Number for DB
        RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
//        rwsHdrTMsg.setSQLID("011");
        rwsHdrTMsg.setSQLID("010");
        rwsHdrTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        rwsHdrTMsg.setConditionValue("rwsRefNum01", bizMsg.rwsRefNum_AP.getValue());
//        rwsHdrTMsg.setConditionValue("whCd01", rtlWhCd);
        RWS_HDRTMsgArray rwsHdrList = (RWS_HDRTMsgArray) EZDTBLAccessor.findByCondition(rwsHdrTMsg);

        // Exist same Shipment#
        if (rwsHdrList != null && 0 < rwsHdrList.getValidCount()) {

//            bizMsg.rwsRefNum_AP.setErrorInfo(1, NLAL2030Constant.NLAM1337E, new String[] {rtlWhCd });
            bizMsg.rwsRefNum_AP.setErrorInfo(1, NLAL2030Constant.NLAM1337E);
            return false;
        }
        // END 2019/09/28 M.Naito [QC#53299,MOD]

        // START 2017/08/10 S.Katsuma [QC#19232,ADD]
        // Check Order Open Qty - RWS Open Qty
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NLAL2030_ASMsg lineMsg = glblMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(lineMsg.xxChkBox_A2.getValue())) {
                if (lineMsg.xxQty10Num_A1.getValueInt() > 0) {
                    List<String> querySsmTpList = NLAL2030CommonLogic.getSsmIdOrTpBySceOrdTp(bizMsg.glblCmpyCd.getValue(),
                            new ArrayList<String>(Arrays.asList(lineMsg.sceOrdTpCd_A1.getValue())),
                            null,
                            NLAL2030Constant.TAB_ID_RWS);

                    S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().checkOrdRwsQty(bizMsg.glblCmpyCd.getValue(),
                            lineMsg.trxOrdNum_A1.getValue(),
                            lineMsg.trxLineNum_A1.getValue(),
                            lineMsg.trxLineSubNum_A1.getValue(),
                            querySsmTpList.get(0));

                    if (!ssmResult.isCodeNotFound()) {
                        Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();

                        BigDecimal openQty = lineMsg.xxQty10Num_A1.getValue().subtract((BigDecimal)resultMap.get("RWS_OPEN_QTY"));
                        if (openQty.intValue() <= 0) {
                            bizMsg.setMessageInfo(NLAL2030Constant.NLAM1342E, new String[] { lineMsg.trxOrdNum_A1.getValue(), lineMsg.dplyLineNum_A1.getValue() });
                            return false;
                        }
                    }
                }
            }
        }
        // END 2017/08/10 S.Katsuma [QC#19232,ADD]

        return true;
    }

    /**
     * checkTimeStampRWSCreate
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @return boolean
     */
    private boolean checkTimeStampRWSCreate(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        int firstErrIdx = -1;

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NLAL2030_ASMsg lineMsg = glblMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(lineMsg.xxChkBox_A2.getValue())) {

                if (!ZYPCommonFunc.hasValue(lineMsg.ezUpTime_A1) || !ZYPCommonFunc.hasValue(lineMsg.ezUpTimeZone_A1)) {

                    lineMsg.xxChkBox_A2.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                    break;
                }

                if (NLAL2030Constant.RWS_CREATE_MODE_PO.equals(lineMsg.procModeCd_A1.getValue())) {

                    PO_DTLTMsg poDtlTMsg = new PO_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(poDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(poDtlTMsg.poOrdNum, lineMsg.trxOrdNum_A1);
                    ZYPEZDItemValueSetter.setValue(poDtlTMsg.poOrdDtlLineNum, lineMsg.trxLineNum_A1);
                    poDtlTMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(poDtlTMsg);

                    if (poDtlTMsg != null) {

                        // anyone update
                        if (!ZYPDateUtil.isSameTimeStamp(lineMsg.ezUpTime_A1.getValue(), lineMsg.ezUpTimeZone_A1.getValue(), poDtlTMsg.ezUpTime.getValue(), poDtlTMsg.ezUpTimeZone.getValue())) {

                            lineMsg.xxChkBox_A2.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                            break;
                        }
                    }

                } else if (NLAL2030Constant.RWS_CREATE_MODE_RTRN.equals(lineMsg.procModeCd_A1.getValue())) {

                    DS_CPO_RTRN_DTLTMsg cpiRtrnDtl = new DS_CPO_RTRN_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(cpiRtrnDtl.glblCmpyCd, bizMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cpiRtrnDtl.cpoOrdNum, lineMsg.trxOrdNum_A1);
                    ZYPEZDItemValueSetter.setValue(cpiRtrnDtl.dsCpoLineNum, new BigDecimal(lineMsg.trxLineNum_A1.getValue()));
                    ZYPEZDItemValueSetter.setValue(cpiRtrnDtl.dsCpoLineSubNum, new BigDecimal(lineMsg.trxLineSubNum_A1.getValue()));
                    cpiRtrnDtl = (DS_CPO_RTRN_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(cpiRtrnDtl);

                    if (cpiRtrnDtl != null) {

                        // anyone update
                        if (!ZYPDateUtil.isSameTimeStamp(lineMsg.ezUpTime_A1.getValue(), lineMsg.ezUpTimeZone_A1.getValue(), cpiRtrnDtl.ezUpTime.getValue(), cpiRtrnDtl.ezUpTimeZone.getValue())) {

                            lineMsg.xxChkBox_A2.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                            break;
                        }
                    }
                }
            }
        }

        if (0 <= firstErrIdx) {

            bizMsg.xxPageShowFromNum_A.setValue(firstErrIdx);
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return false;
        }

        return true;
    }

    /**
     * createRWS
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @return boolean true : OK, false : NG
     */
    private boolean createRWS(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        List<NLAL2030_ASMsg> poRwsList = new ArrayList<NLAL2030_ASMsg>();
        List<NLAL2030_ASMsg> rtrnRwsList = new ArrayList<NLAL2030_ASMsg>();
        // QC#30456
        List<String> configRtrnList = new ArrayList<String>();
        String preRwsNum = "";
        boolean isLineErr = false;
        int confHdrLine = -1;

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NLAL2030_ASMsg lineMsg = glblMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(lineMsg.xxChkBox_A2.getValue())) {

                if (NLAL2030Constant.RWS_CREATE_MODE_PO.equals(lineMsg.procModeCd_A1.getValue())) {

                    poRwsList.add(lineMsg);

                } else if (NLAL2030Constant.RWS_CREATE_MODE_RTRN.equals(lineMsg.procModeCd_A1.getValue())) {

                    rtrnRwsList.add(lineMsg);
                    // QC#30456
                    if (ZYPCommonFunc.hasValue(lineMsg.svcConfigMstrPk_A1)) {
                        // get RWS_DTL_LINE_NUM
                        String rwsLineNum = NLAL2030Query.getInstance().getRwsLineNum(bizMsg.glblCmpyCd.getValue(), lineMsg);

                        if (ZYPCommonFunc.hasValue(rwsLineNum)) {
                            configRtrnList.add(rwsLineNum);
                            if (ZYPCommonFunc.hasValue(lineMsg.xxChkBox_A1)) {
                                confHdrLine = i;
                            }
                        }
                    }
                }
                // QC#30456
                if ((ZYPCommonFunc.hasValue(preRwsNum) && !preRwsNum.equals(lineMsg.rwsNum_A1.getValue()))) {
                    String errMsgId = "NLAM1351E";
                    S21InfoLogOutput.println(errMsgId);
                    lineMsg.xxChkBox_A1.setErrorInfo(1, errMsgId);
                    isLineErr = true;
                }

                if (ZYPCommonFunc.hasValue(lineMsg.rwsNum_A1) && !lineMsg.rwsNum_A1.getValue().equals(preRwsNum)) {
                    preRwsNum = lineMsg.rwsNum_A1.getValue();
                }
            }
            // QC#30456
            if (glblMsg.A.getValidCount() == i + 1 && !configRtrnList.isEmpty() && ZYPCommonFunc.hasValue(preRwsNum)) {
                // Last Record
                BigDecimal smmCnt = NLAL2030Query.getInstance().getRwsCnt(bizMsg.glblCmpyCd.getValue(), preRwsNum, configRtrnList);
                if (BigDecimal.ZERO.compareTo(smmCnt) != 0) {
                    String errMsgId = "NLAM1350E";
                    S21InfoLogOutput.println(errMsgId);
                    glblMsg.A.no(confHdrLine).xxChkBox_A1.setErrorInfo(1, errMsgId);
                    isLineErr = true;
                }
                configRtrnList.clear();
            }
        }
        // QC#30456
        if (isLineErr) {
            return false;
        }

        // PO
        if (!poRwsList.isEmpty()) {

            // Call PO Receiving API (NLZC2010)
            NLZC201001PMsg poRcvPMsg = new NLZC201001PMsg();

            if (!callAPIPoRcv(bizMsg, poRcvPMsg, poRwsList)) {

                return false;
            }

            // Call RWS API (NLZC2000)
            NLZC200001PMsg rwsPMsg = new NLZC200001PMsg();

            if (!callAPIRwsCreate(bizMsg, poRcvPMsg, rwsPMsg)) {

                return false;
            }

            // Call RWS Serial API (NLZC3040)
            if (!callAPIRwsSerial(bizMsg, glblMsg, rwsPMsg, poRwsList)) {

                return false;
            }
        }

        // Return
        if (!rtrnRwsList.isEmpty()) {

            // QC#24095
            if (!changeSmmStatusW4R(bizMsg, rtrnRwsList)) {

                return false;
            }
            // Call DS Return RWS API (NLZC4040)
            if (!callRwsApiForReturn(bizMsg, rtrnRwsList)) {

                return false;
            }

            // Call DS CPO Return Update API (NWZC1530)
            if (!callAPIDsCpoRtrn(bizMsg, rtrnRwsList)) {

                return false;
            }
        }

        bizMsg.setMessageInfo(NLAL2030Constant.ZZZM9003I, new String[] {"Create RWS" });
        return true;
    }

    /**
     * checkInputRWSCancel
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @return boolean
     */
    private boolean checkInputRWSCancel(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        int cntChkOn = 0;
        int firstErrIdx = -1;
        HashSet<String> unitRwsSet = new HashSet<String>();
        HashSet<String> rwsNumSet = new HashSet<String>();

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            NLAL2030_BSMsg lineMsg = glblMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(lineMsg.xxChkBox_B1.getValue())) {

                // Count CheckBox-On
                cntChkOn++;

                // Cancel not available
                if (!ZYPConstant.FLG_ON_Y.equals(lineMsg.rwsScrCancAvalFlg_B1.getValue())) {

                    lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLAM0085E);

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    // RWS Header is not open.
                } else if (!ZYPConstant.FLG_ON_Y.equals(lineMsg.openFlg_BH.getValue())) {

                    lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLZM2283E);

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    // RWS Header is received.
                } else if (ZYPConstant.FLG_ON_Y.equals(lineMsg.rcvFlg_BH.getValue())) {

                    lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLAM1325E);

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    // Check Shipping Order Status for Repair
                } else if (SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(lineMsg.sceOrdTpCd_B1.getValue()) && !unitRwsSet.contains(lineMsg.rwsNum_B1.getValue())) {

                    unitRwsSet.add(lineMsg.rwsNum_B1.getValue());

                    // Get Shipping Order
                    S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().checkShpgStsForRP(bizMsg.glblCmpyCd.getValue(), lineMsg);

                    if (!ssmResult.isCodeNotFound()) {

                        // shipping order is not shipped or canceled.
                        lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLAM1327E);

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }
                    }
                }

                // Check Timestamp
                if (rwsNumSet.isEmpty() || !rwsNumSet.contains(lineMsg.rwsNum_B1.getValue())) {

                    rwsNumSet.add(lineMsg.rwsNum_B1.getValue());

                    if (firstErrIdx == -1) {

                        if (!checkTimeStampRWSCancel(bizMsg, glblMsg, lineMsg)) {

                            if (firstErrIdx == -1) {

                                firstErrIdx = i;
                            }
                        }
                    }
                }
            }
        }

        // All Un-Select
        if (cntChkOn == 0) {

            bizMsg.setMessageInfo(NLAL2030Constant.NLAM1288E);
            return false;
        }

        // Has Error
        if (0 <= firstErrIdx) {

            bizMsg.xxPageShowFromNum_B.setValue(firstErrIdx);
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);
            return false;
        }

        return true;
    }

    /**
     * checkInputWarningRWSCancel
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @return boolean
     */
    private boolean checkInputWarningRWSCancel(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        int firstErrIdx = -1;

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            NLAL2030_BSMsg lineMsg = glblMsg.B.no(i);
            lineMsg.xxChkBox_BK.clear();

            if (ZYPConstant.CHKBOX_ON_Y.equals(lineMsg.xxChkBox_B1.getValue())) {

                // Copy
                ZYPEZDItemValueSetter.setValue(lineMsg.xxChkBox_BK, lineMsg.xxChkBox_B1);

                // Check Dropped WMS
                if (ZYPConstant.FLG_ON_Y.equals(lineMsg.wmsDropFlg_B1.getValue())) {

                    // Warning
                    lineMsg.xxChkBox_B1.setErrorInfo(2, NLAL2030Constant.NLAM1278W);

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }

                // Check Sent Request
                if (ZYPConstant.FLG_ON_Y.equals(lineMsg.sendRqstFlg_B1.getValue())) {

                    // Warning
                    // The selected RWS has already been sent request
                    // to carrier.
                    lineMsg.xxChkBox_B1.setErrorInfo(2, NLAL2030Constant.NLAM1328W);

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }

                // START 2020/12/14 A.Marte [QC#58069, MOD]
                // START 2022/12/13 R.Azucena [QC#60905, MOD]
                //Check PO Cancel(PDLT) Record
                // S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getWmsPdltRecord(bizMsg.glblCmpyCd.getValue(), lineMsg.rwsRefNum_B1.getValue());

                // if (!ssmResult.isCodeNormal()) {
                    //NO PDLT Record
                //     if (firstErrIdx == -1) {
                //         firstErrIdx = i;
                //         bizMsg.setMessageInfo(NLAL2030Constant.NLAM1355W);
                //     }
                // }
                if (firstErrIdx == -1 && !pdltCheck(bizMsg, lineMsg)) {
                    firstErrIdx = i;
                    bizMsg.setMessageInfo(NLAL2030Constant.NLAM1355W);
                }
                // END 2022/12/13 R.Azucena [QC#60905, MOD]
                // END 2020/12/14 A.Marte [QC#58069, MOD]
            }
        }

        if (0 <= firstErrIdx) {

            bizMsg.xxPageShowFromNum_B.setValue(firstErrIdx);
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);
            return false;
        }

        return true;
    }

    /**
     * changeValAfterWarning
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @return boolean
     */
    private boolean changeValAfterWarning(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        boolean changedVal = false;

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            NLAL2030_BSMsg lineMsg = glblMsg.B.no(i);

            // No changed
            if (!ZYPCommonFunc.hasValue(lineMsg.xxChkBox_B1) && !ZYPCommonFunc.hasValue(lineMsg.xxChkBox_BK)) {

                continue;

                // Changed
            } else if (ZYPCommonFunc.hasValue(lineMsg.xxChkBox_B1) && !lineMsg.xxChkBox_B1.getValue().equals(lineMsg.xxChkBox_BK.getValue())) {

                changedVal = true;

                // Changed
            } else if (ZYPCommonFunc.hasValue(lineMsg.xxChkBox_BK) && !lineMsg.xxChkBox_B1.getValue().equals(lineMsg.xxChkBox_BK.getValue())) {

                changedVal = true;
            }
        }

        return changedVal;
    }

    /**
     * checkTimeStampRWSCancel
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param rwsHdrLineMsg NLAL2030_BSMsg
     * @return boolean
     */
    private boolean checkTimeStampRWSCancel(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, NLAL2030_BSMsg rwsHdrLineMsg) {

        // RWS_HDR
        if (!ZYPCommonFunc.hasValue(rwsHdrLineMsg.ezUpTime_RH) || !ZYPCommonFunc.hasValue(rwsHdrLineMsg.ezUpTimeZone_RH)) {

            rwsHdrLineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
            return false;
        }

        RWS_HDRTMsg rwsHdr = new RWS_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(rwsHdr.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsHdr.rwsNum, rwsHdrLineMsg.rwsNum_B1);
        rwsHdr = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rwsHdr);

        if (rwsHdr != null) {

            // anyone update
            if (!ZYPDateUtil.isSameTimeStamp(rwsHdrLineMsg.ezUpTime_RH.getValue(), rwsHdrLineMsg.ezUpTimeZone_RH.getValue(), rwsHdr.ezUpTime.getValue(), rwsHdr.ezUpTimeZone.getValue())) {

                rwsHdrLineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                return false;
            }

        } else {

            rwsHdrLineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
            return false;
        }

        // PO_RCV_HDR
        if (!SCE_ORD_TP.RETURN.equals(rwsHdrLineMsg.sceOrdTpCd_B1.getValue())) {

            if (!ZYPCommonFunc.hasValue(rwsHdrLineMsg.ezUpTime_PH) || !ZYPCommonFunc.hasValue(rwsHdrLineMsg.ezUpTimeZone_PH)) {

                rwsHdrLineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                return false;
            }

            PO_RCV_HDRTMsg poRcvHdr = new PO_RCV_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(poRcvHdr.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(poRcvHdr.poRcvNum, rwsHdrLineMsg.trxOrdNum_B2);
            poRcvHdr = (PO_RCV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(poRcvHdr);

            if (poRcvHdr != null) {

                // anyone update
                if (!ZYPDateUtil.isSameTimeStamp(rwsHdrLineMsg.ezUpTime_PH.getValue(), rwsHdrLineMsg.ezUpTimeZone_PH.getValue(), poRcvHdr.ezUpTime.getValue(), poRcvHdr.ezUpTimeZone.getValue())) {

                    rwsHdrLineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                    return false;
                }

            } else {

                rwsHdrLineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                return false;
            }
        }

        // RWS_DTL
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            NLAL2030_BSMsg rwsDtlLineMsg = glblMsg.B.no(i);

            if (rwsHdrLineMsg.rwsNum_B1.getValue().equals(rwsDtlLineMsg.rwsNum_B1.getValue())) {

                if (!ZYPCommonFunc.hasValue(rwsDtlLineMsg.ezUpTime_RD) || !ZYPCommonFunc.hasValue(rwsDtlLineMsg.ezUpTimeZone_RD)) {

                    rwsHdrLineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                    return false;
                }

                RWS_DTLTMsg rwsDtl = new RWS_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(rwsDtl.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rwsDtl.rwsNum, rwsDtlLineMsg.rwsNum_B1);
                ZYPEZDItemValueSetter.setValue(rwsDtl.rwsDtlLineNum, rwsDtlLineMsg.rwsDtlLineNum_B1);
                rwsDtl = (RWS_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rwsDtl);

                if (rwsDtl != null) {

                    // anyone update
                    if (!ZYPDateUtil.isSameTimeStamp(rwsDtlLineMsg.ezUpTime_RD.getValue(), rwsDtlLineMsg.ezUpTimeZone_RD.getValue(), rwsDtl.ezUpTime.getValue(), rwsDtl.ezUpTimeZone.getValue())) {

                        rwsHdrLineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                        return false;
                    }

                    // Received line exists.
                    if (ZYPConstant.FLG_ON_Y.equals(rwsDtlLineMsg.rcvFlg_BD.getValue())) {

                        rwsHdrLineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLAM1326E);
                        return false;
                    }

                } else {

                    rwsHdrLineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                    return false;
                }

                // PO_RCV_DTL
                if (!SCE_ORD_TP.RETURN.equals(rwsHdrLineMsg.sceOrdTpCd_B1.getValue())) {

                    if (!ZYPCommonFunc.hasValue(rwsDtlLineMsg.ezUpTime_PD) || !ZYPCommonFunc.hasValue(rwsDtlLineMsg.ezUpTimeZone_PD)) {

                        rwsHdrLineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                        return false;
                    }

                    PO_RCV_DTLTMsg poRcvDtl = new PO_RCV_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(poRcvDtl.glblCmpyCd, bizMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(poRcvDtl.poRcvNum, rwsDtlLineMsg.trxOrdNum_B2);
                    ZYPEZDItemValueSetter.setValue(poRcvDtl.poRcvLineNum, rwsDtlLineMsg.trxLineNum_B2);
                    poRcvDtl = (PO_RCV_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(poRcvDtl);

                    if (poRcvDtl != null) {

                        // anyone update
                        if (!ZYPDateUtil.isSameTimeStamp(rwsDtlLineMsg.ezUpTime_PD.getValue(), rwsDtlLineMsg.ezUpTimeZone_PD.getValue(), poRcvDtl.ezUpTime.getValue(), poRcvDtl.ezUpTimeZone.getValue())) {

                            rwsHdrLineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                            return false;
                        }

                    } else {

                        rwsHdrLineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM0009E);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * callAPIPoRcv
     * @param bizMsg NLAL2030CMsg
     * @param poRcvPMsg NLZC201001PMsg
     * @param poRwsList List<NLAL2030_ASMsg>
     * @return boolean
     */
    private boolean callAPIPoRcv(NLAL2030CMsg bizMsg, NLZC201001PMsg poRcvPMsg, List<NLAL2030_ASMsg> poRwsList) {

        int poRcvSize = 0;

        for (NLAL2030_ASMsg lineMsg : poRwsList) {

            if (poRcvSize == 0) {

                ZYPEZDItemValueSetter.setValue(poRcvPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(poRcvPMsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
                ZYPEZDItemValueSetter.setValue(poRcvPMsg.sceOrdTpCd, lineMsg.sceOrdTpCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(poRcvPMsg.rwsRefNum, bizMsg.rwsRefNum_AP.getValue());
                ZYPEZDItemValueSetter.setValue(poRcvPMsg.poRcvFromLocTpCd, LOC_TP.VENDOR);
                ZYPEZDItemValueSetter.setValue(poRcvPMsg.poRcvFromLocCd, lineMsg.fromLocCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(poRcvPMsg.whCd, lineMsg.rtlWhCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(poRcvPMsg.poRcvDrctShipTpCd, DRCT_SHIP_TP.N_OR_A);
                ZYPEZDItemValueSetter.setValue(poRcvPMsg.poRcvEtaDt, ZYPDateUtil.getSalesDate());
                ZYPEZDItemValueSetter.setValue(poRcvPMsg.poRcvTrxHdrNum, lineMsg.trxOrdNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(poRcvPMsg.domInvNum, bizMsg.rwsRefNum_AP.getValue());
                ZYPEZDItemValueSetter.setValue(poRcvPMsg.svcConfigMstrPk, lineMsg.svcConfigMstrPk_A1.getValue());
            }

            // START 2021/03/22 A.Marte [QC#57124, ADD]
            S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getPrchReqRef(bizMsg.glblCmpyCd.getValue(), lineMsg.rwsNum_A1.getValue(), lineMsg.trxLineNum_A1.getValue(), lineMsg.trxLineSubNum_A1.getValue());

            if (ssmResult.isCodeNormal()) {

                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

                for (Map<String, Object> result : resultList) {

                    ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(poRcvSize).prchReqNum, (String) result.get("PRCH_REQ_NUM"));
                    ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(poRcvSize).prchReqLineNum, (String) result.get("PRCH_REQ_LINE_NUM"));
                    // START 2021/08/09 A.Marte [QC#58968, MOD]
                    if (result.get("PRCH_REQ_LINE_SUB_NUM") != null) {
                        ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(poRcvSize).prchReqLineSubNum, (new BigDecimal(result.get("PRCH_REQ_LINE_SUB_NUM").toString())));
                    }
                    // END 2021/08/09 A.Marte [QC#58968, MOD]
                }
            }
            // START 2021/03/22 A.Marte [QC#57124, ADD]

            // Detail
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(poRcvSize).mdseCd, lineMsg.mdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(poRcvSize).stkStsCd, STK_STS.GOOD);
            // QC#24413
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(poRcvSize).poRcvQty, getOpenRwsQty(bizMsg.glblCmpyCd.getValue(), lineMsg));
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(poRcvSize).poRcvTrxLineNum, lineMsg.trxLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(poRcvSize).proNum, bizMsg.imptInvBolNum_AP.getValue());
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(poRcvSize).invtyLocCd, NLAL2030CommonLogic.getInvtyLocCd(bizMsg, lineMsg.rtlWhCd_A1.getValue(), lineMsg.rtlSwhCd_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(poRcvSize).shipFromInvtyLocCd, lineMsg.fromLocCd_A1.getValue());
            poRcvSize++;
            poRcvPMsg.OrdInfoLIst.setValidCount(poRcvSize);
        }

        if (0 < poRcvSize) {

            // Call API
            NLZC201001 poRcvAPI = new NLZC201001();
            poRcvAPI.execute(poRcvPMsg, ONBATCH_TYPE.ONLINE);

            // Check Error in API
            if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(poRcvPMsg))) {

                bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"PO Receiving" });
                bizMsg.xxPageShowFromNum_A.setValue(0);
                return false;
            }

        } else {

            poRcvPMsg = null;
        }

        return true;
    }

    /**
     * callAPIRwsCreate
     * @param bizMsg NLAL2030CMsg
     * @param poRcvPMsg NLZC201001PMsg
     * @param rwsPMsg NLZC200001PMsg
     * @return boolean
     */
    private boolean callAPIRwsCreate(NLAL2030CMsg bizMsg, NLZC201001PMsg poRcvPMsg, NLZC200001PMsg rwsPMsg) {

        boolean hasErr = false;

        if (poRcvPMsg == null || !ZYPCommonFunc.hasValue(poRcvPMsg.poRcvNum)) {

            hasErr = true;

        } else {

            // Create API Parameter for RWS API (NLZC2000)
            ZYPEZDItemValueSetter.setValue(rwsPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPMsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
            ZYPEZDItemValueSetter.setValue(rwsPMsg.inbdSrcTpCd, INBD_SRC_TP.PO_RECEIVING);
            ZYPEZDItemValueSetter.setValue(rwsPMsg.poRcvNum, poRcvPMsg.poRcvNum.getValue());

            // Call RWS API (NLZC2000)
            NLZC200001 rwsAPI = new NLZC200001();
            rwsAPI.execute(rwsPMsg, ONBATCH_TYPE.ONLINE);

            // Check Error in API
            if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(rwsPMsg))) {

                hasErr = true;
            }
        }

        if (hasErr) {

            bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"RWS" });
            bizMsg.xxPageShowFromNum_A.setValue(0);
            return false;
        }

        return true;
    }

    /**
     * callAPIRwsSerial
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param rwsPMsg NLZC200001PMsg
     * @param poRwsList List<NLAL2030_ASMsg>
     * @return boolean
     */
    private boolean callAPIRwsSerial(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, NLZC200001PMsg rwsPMsg, List<NLAL2030_ASMsg> poRwsList) {

        List<Map<String, Object>> rwsDtlList = null;
        boolean hasErr = false;

        if (rwsPMsg != null) {

            rwsDtlList = getRwsDtl(bizMsg, rwsPMsg.RWSNumList.no(0).rwsNum.getValue());
        }

        if (rwsDtlList == null) {

            hasErr = true;

        } else {

            // Header
            NLZC304001PMsg rwsSerPMsg = new NLZC304001PMsg();
            ZYPEZDItemValueSetter.setValue(rwsSerPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rwsSerPMsg.rwsNum, rwsPMsg.RWSNumList.no(0).rwsNum.getValue());

            int size = 0;

            // Detail
            for (Map<String, Object> rwsDtlMap : rwsDtlList) {

                for (NLAL2030_ASMsg lineMsg : poRwsList) {

                    String poOrdNum = (String) rwsDtlMap.get("PO_ORD_NUM");
                    String poOrdDtlLineNum = (String) rwsDtlMap.get("PO_ORD_DTL_LINE_NUM");

                    if ((ZYPCommonFunc.hasValue(poOrdNum) && !poOrdNum.equals(lineMsg.trxOrdNum_A1.getValue())) //
                            || (ZYPCommonFunc.hasValue(poOrdDtlLineNum) && !poOrdDtlLineNum.equals(lineMsg.trxLineNum_A1.getValue()))) {

                        continue;
                    }

                    // Serial
                    List<String> serNumList = new ArrayList<String>();
                    // QC#24413
                    if (1 < lineMsg.xxQty10Num_A1.getValueInt() || 1 < lineMsg.rwsQty_A1.getValueInt()) {

                        serNumList = getSerNumList(bizMsg.glblCmpyCd.getValue(), glblMsg, lineMsg);

                    } else if (1 == lineMsg.xxQty10Num_A1.getValueInt() && ZYPCommonFunc.hasValue(lineMsg.serNum_A1)) {

                        serNumList.add(lineMsg.serNum_A1.getValue());
                    }

                    // Set Serial information to PMsg
                    if (!serNumList.isEmpty()) {

                        for (String serNum : serNumList) {

                            ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).rwsDtlLineNum, (String) rwsDtlMap.get("RWS_DTL_LINE_NUM"));
                            ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).mdseCd, lineMsg.mdseCd_A1.getValue());
                            ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).putAwayChkStsCd, PUT_AWAY_CHK_STS.NO_NEED);
                            ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).serNumSendFlg, ZYPConstant.FLG_OFF_N);
                            ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).serNum, serNum);
                            size++;
                        }

                        rwsSerPMsg.SerialList.setValidCount(size);
                    }
                }
            }

            if (0 < size) {

                // Call RWS Serial API (NLZC3040)
                NLZC304001 rwsSerAPI = new NLZC304001();
                rwsSerAPI.execute(rwsSerPMsg, ONBATCH_TYPE.ONLINE);

                // Check Error in API
                if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(rwsSerPMsg))) {

                    hasErr = true;
                }
            }
        }

        if (hasErr) {

            bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"RWS Serial" });
            bizMsg.xxPageShowFromNum_A.setValue(0);
            return false;
        }

        return true;
    }

    /**
     * getSerNumList
     * @param glblCmpyCd String
     * @param glblMsg NLAL2030SMsg
     * @param lineMsg NLAL2030_ASMsg
     * @return List<String>
     */
    private List<String> getSerNumList(String glblCmpyCd, NLAL2030SMsg glblMsg, NLAL2030_ASMsg lineMsg) {

        List<String> serNumList = new ArrayList<String>();

        // Search Serial# in SMsg
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {

            NLAL2030_CSMsg serMsg = glblMsg.C.no(i);

            if (serMsg.trxOrdNum.getValue().equals(lineMsg.trxOrdNum_A1.getValue()) && serMsg.trxLineNum.getValue().equals(lineMsg.trxLineNum_A1.getValue())) {

                if (ZYPCommonFunc.hasValue(lineMsg.trxLineSubNum_A1) && lineMsg.trxLineSubNum_A1.equals(serMsg.trxLineSubNum.getValue())) {

                    serNumList.add(serMsg.serNum.getValue());

                } else if (!ZYPCommonFunc.hasValue(lineMsg.trxLineSubNum_A1)) {

                    serNumList.add(serMsg.serNum.getValue());
                }
            }
        }

        if (serNumList.size() == 0) {

            S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getSerNumListForOrd(glblCmpyCd, lineMsg.trxOrdNum_A1.getValue(), lineMsg.trxLineNum_A1.getValue(), lineMsg.trxLineSubNum_A1.getValue());

            if (!ssmResult.isCodeNotFound()) {

                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

                for (Map<String, Object> serNumMap : resultList) {

                    serNumList.add((String) serNumMap.get("SER_NUM"));
                }
            }
        }

        return serNumList;
    }

    /**
     * callRwsApiForReturn
     * @param bizMsg NLAL2030CMsg
     * @param rtrnRwsList List<NLAL2030_ASMsg>
     * @return boolean
     */
    private boolean callRwsApiForReturn(NLAL2030CMsg bizMsg, List<NLAL2030_ASMsg> rtrnRwsList) {

        int dtlSize = 0;
        NLZC200001PMsg rtrnRwsPMsg = new NLZC200001PMsg();

        for (NLAL2030_ASMsg lineMsg : rtrnRwsList) {

            // Header
            if (dtlSize == 0) {

                ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
                ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.inbdSrcTpCd, INBD_SRC_TP.RETURN);
                ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.slsDt, ZYPDateUtil.getSalesDate());
                ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.cpoOrdNum, lineMsg.trxOrdNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.dsOrdPosnNum, lineMsg.dsOrdPosnNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.rwsRefNum, bizMsg.rwsRefNum_AP.getValue());
                ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.bolNum, bizMsg.imptInvBolNum_AP.getValue());
            }

            // Detail
            ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.xxDetailList.no(dtlSize).dsCpoRtrnLineNum, lineMsg.trxLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.xxDetailList.no(dtlSize).dsCpoRtrnLineSubNum, lineMsg.trxLineSubNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.xxDetailList.no(dtlSize).ordQty, lineMsg.xxQty10Num_A1.getValue());
            ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.xxDetailList.no(dtlSize).serNum, lineMsg.serNum_A1.getValue());

            dtlSize++;
            rtrnRwsPMsg.xxDetailList.setValidCount(dtlSize);
        }

        if (0 < dtlSize) {

            // Call RWS API (NLZC2000)
            NLZC200001 rwsApi = new NLZC200001();
            rwsApi.execute(rtrnRwsPMsg, ONBATCH_TYPE.ONLINE);

            // Check Error in API
            if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(rtrnRwsPMsg))) {

                bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"Return RWS" });
                bizMsg.xxPageShowFromNum_A.setValue(0);
                return false;
            }
        }

        return true;
    }

    /**
     * callAPIDsCpoRtrn
     * @param bizMsg NLAL2030CMsg
     * @param rtrnRwsList List<NLAL2030_ASMsg>
     * @return boolean
     */
    private boolean callAPIDsCpoRtrn(NLAL2030CMsg bizMsg, List<NLAL2030_ASMsg> rtrnRwsList) {

        int dtlSize = 0;
        NWZC153001PMsg dsCpoRtrnPMsg = new NWZC153001PMsg();
        List<NWZC153002PMsg> apiDtlMsgList = new ArrayList<NWZC153002PMsg>();

        for (NLAL2030_ASMsg lineMsg : rtrnRwsList) {

            // Header
            if (dtlSize == 0) {

                ZYPEZDItemValueSetter.setValue(dsCpoRtrnPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_UPD_STS);
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnPMsg.cpoOrdNum, lineMsg.trxOrdNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnPMsg.slsDt, ZYPDateUtil.getSalesDate());
            }

            // Detail
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnPMsg.ordRtrnDtlList.no(dtlSize).xxRqstTpCd, NWZC153001Constant.RQST_DTL_RWS_CREATE);
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnPMsg.ordRtrnDtlList.no(dtlSize).dsCpoRtrnLineNum, lineMsg.trxLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(dsCpoRtrnPMsg.ordRtrnDtlList.no(dtlSize).dsCpoRtrnLineSubNum, lineMsg.trxLineSubNum_A1.getValue());

            dtlSize++;
            dsCpoRtrnPMsg.ordRtrnDtlList.setValidCount(dtlSize);
        }

        if (0 < dtlSize) {

            // Call DS CPO Return API (NWZC1530)
            NWZC153001 dsCpoRtrnApi = new NWZC153001();
            dsCpoRtrnApi.execute(dsCpoRtrnPMsg, apiDtlMsgList, ONBATCH_TYPE.BATCH);

            // Check Error in API
            if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(dsCpoRtrnPMsg))) {

                bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"DS CPO Return" });
                bizMsg.xxPageShowFromNum_A.setValue(0);
                return false;
            }
        }

        return true;
    }

    /**
     * getStdCcy
     * @param glblCmpyCd String
     * @return String
     */
    private String getStdCcy(String glblCmpyCd) {

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {

            return null;
        }

        return glblCmpyTMsg.stdCcyCd.getValue();
    }

    /**
     * cancelRws
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @return boolean
     */
    private boolean cancelRws(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        HashSet<String> rwsNumSet = new HashSet<String>();
        HashSet<String> poRcvNumSet = new HashSet<String>();
        HashSet<String> srcDocNumSet = new HashSet<String>();

        String stdCcyCd = getStdCcy(bizMsg.glblCmpyCd.getValue());
        String cancelDt = NLAL2030CommonLogic.getDateTime14();

        List<Map<String, Object>> rwsDtlList = null;

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            NLAL2030_BSMsg lineMsg = glblMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(lineMsg.xxChkBox_B1.getValue()) && !rwsNumSet.contains(lineMsg.rwsNum_B1.getValue())) {

                // Update RWS_HDR
                if (!updateRwsHdr(bizMsg, lineMsg)) {

                    bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"RWS" });
                    break;
                }

                rwsNumSet.add(lineMsg.rwsNum_B1.getValue());

                // Update RWS_DTL
                if (!updateRwsDtl(bizMsg, lineMsg)) {

                    bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"RWS" });
                    break;
                }

                // Update PO_RCV_HDR
                if (ZYPCommonFunc.hasValue(lineMsg.trxOrdNum_B2) && !poRcvNumSet.contains(lineMsg.trxOrdNum_B2.getValue())) {

                    if (!updatePoRcvHdr(bizMsg, lineMsg)) {

                        bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"PO Receiving" });
                        break;
                    }

                    if (!updatePoRcvDtl(bizMsg, lineMsg)) {

                        bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"PO Receiving" });
                        break;
                    }

                    poRcvNumSet.add(lineMsg.trxOrdNum_B2.getValue());
                }

                // Update /Delete INBD_VIS
                if (!updateDeleteInbdVis(bizMsg, lineMsg)) {

                    bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"Inbound Visibility" });
                    break;
                }

                // Delete WH_SCHD
                if (!deleteWhSchd(bizMsg, lineMsg)) {

                    bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"Warehouse Schedule" });
                    break;
                }

                // Search RWS_DTL
                rwsDtlList = getRwsDtl(bizMsg, lineMsg.rwsNum_B1.getValue());

                // Call Inventory Update API(NLZC002001)
                if (!callAPIInvtyUpdForCancel(bizMsg, lineMsg, rwsDtlList, stdCcyCd)) {

                    bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"Inventory" });
                    break;
                }

                // Call CPO Return Line Update API (NWZC153001)
                if (!callAPICpoRtrnLineUpd(bizMsg, lineMsg, rwsDtlList)) {

                    // START 01/27/2020 T.Ogura [QC#55497,MOD]
//                    bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"CPO" });
                    bizMsg.setMessageInfo(bizMsg.xxMsgId.getValue());
                    // END   01/27/2020 T.Ogura [QC#55497,MOD]
                    break;
                }

                // Call POYO Update API (NPZC1090) [Create Inbounds
                // Visibility]
                if (!callAPIPoyoUpd(bizMsg, lineMsg, rwsDtlList)) {

                    bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"POYO" });
                    break;
                }

                // Call SO Confirmation from S21 DC API(NLZC210001)
                if (!srcDocNumSet.contains(lineMsg.trxLineNum_B1.getValue())) {

                    if (!callAPISoConf(bizMsg, lineMsg, cancelDt)) {

                        bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"Shipping Order Confirmation" });
                        break;
                    }

                    srcDocNumSet.add(lineMsg.trxLineNum_B1.getValue());
                }
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {

            bizMsg.xxPageShowFromNum_A.setValue(0);
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return false;
        }

        return true;
    }

    /**
     * updateRwsHdr
     * @param bizMsg NLAL2030CMsg
     * @param lineMsg NLAL2030_BSMsg
     * @return boolean
     */
    private boolean updateRwsHdr(NLAL2030CMsg bizMsg, NLAL2030_BSMsg lineMsg) {

        if (SCE_ORD_TP.BUY_BACK.equals(lineMsg.sceOrdTpCd_B1.getValue())) {

            return true;
        }

        // Update RWS_HDR
        RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.rwsNum, lineMsg.rwsNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.rwsStsCd, RWS_STS.CANCELED);
        EZDTBLAccessor.updateSelectionField(rwsHdrTMsg, new String[] {"rwsStsCd" });

        if (!RTNCD_NORMAL.equals(rwsHdrTMsg.getReturnCode())) {

            lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM1295E, new String[] {"RWS" });
            return false;
        }

        return true;
    }

    /**
     * updateRwsDtl
     * @param bizMsg NLAL2030CMsg
     * @param lineMsg NLAL2030_BSMsg
     * @return boolean
     */
    private boolean updateRwsDtl(NLAL2030CMsg bizMsg, NLAL2030_BSMsg lineMsg) {

        if (SCE_ORD_TP.BUY_BACK.equals(lineMsg.sceOrdTpCd_B1.getValue())) {

            return true;
        }

        // Update RWS_DTL
        RWS_DTLTMsg rwsDtlTMsg = new RWS_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rwsNum, lineMsg.rwsNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rwsStsCd, RWS_STS.CANCELED);
        EZDTBLAccessor.updateByPartialKey(rwsDtlTMsg, new String[] {"rwsStsCd" });

        if (!RTNCD_NORMAL.equals(rwsDtlTMsg.getReturnCode()) || RTNCD_NOT_FOUND.equals(rwsDtlTMsg.getReturnCode())) {

            lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM1295E, new String[] {"RWS" });
            return false;
        }

        return true;
    }

    /**
     * updatePoRcvHdr
     * @param bizMsg NLAL2030CMsg
     * @param lineMsg NLAL2030_BSMsg
     * @return boolean
     */
    private boolean updatePoRcvHdr(NLAL2030CMsg bizMsg, NLAL2030_BSMsg lineMsg) {

        if (SCE_ORD_TP.BUY_BACK.equals(lineMsg.sceOrdTpCd_B1.getValue()) || SCE_ORD_TP.RETURN.equals(lineMsg.sceOrdTpCd_B1.getValue())) {

            return true;
        }

        // Update PO_RCV_HDR
        PO_RCV_HDRTMsg poRcvHdrTMsg = new PO_RCV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(poRcvHdrTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrTMsg.poRcvNum, lineMsg.trxOrdNum_B2.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrTMsg.rwsStsCd, RWS_STS.CANCELED);
        EZDTBLAccessor.updateSelectionField(poRcvHdrTMsg, new String[] {"rwsStsCd" });

        if (!RTNCD_NORMAL.equals(poRcvHdrTMsg.getReturnCode())) {

            lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM1295E, new String[] {"PO Receiving" });
            return false;
        }

        return true;
    }

    /**
     * updatePoRcvDtl
     * @param bizMsg NLAL2030CMsg
     * @param lineMsg NLAL2030CMsg
     * @return boolean
     */
    private boolean updatePoRcvDtl(NLAL2030CMsg bizMsg, NLAL2030_BSMsg lineMsg) {

        if (SCE_ORD_TP.BUY_BACK.equals(lineMsg.sceOrdTpCd_B1.getValue()) || SCE_ORD_TP.RETURN.equals(lineMsg.sceOrdTpCd_B1.getValue())) {

            return true;
        }

        // Update PO_RCV_DTL
        PO_RCV_DTLTMsg poRcvDtlTMsg = new PO_RCV_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(poRcvDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvDtlTMsg.poRcvNum, lineMsg.trxOrdNum_B2.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvDtlTMsg.rwsStsCd, RWS_STS.CANCELED);
        EZDTBLAccessor.updateByPartialKey(poRcvDtlTMsg, new String[] {"rwsStsCd" });

        if (!RTNCD_NORMAL.equals(poRcvDtlTMsg.getReturnCode()) || RTNCD_NOT_FOUND.equals(poRcvDtlTMsg.getReturnCode())) {

            lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM1295E, new String[] {"PO Receiving" });
            return false;
        }

        return true;
    }

    /**
     * updateDeleteInbdVis
     * @param bizMsg NLAL2030CMsg
     * @param lineMsg NLAL2030_BSMsg
     * @return boolean
     */
    private boolean updateDeleteInbdVis(NLAL2030CMsg bizMsg, NLAL2030_BSMsg lineMsg) {
        // QC#27775
        if (SCE_ORD_TP.RETURN.equals(lineMsg.sceOrdTpCd_B1.getValue())) {

            return true;
        }

        // Search INBD_VIS
        INBD_VISTMsg inbdVisTMsg = new INBD_VISTMsg();
        inbdVisTMsg.setSQLID("011");
        inbdVisTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inbdVisTMsg.setConditionValue("poRcvNum01", lineMsg.trxOrdNum_B2.getValue());
        inbdVisTMsg.setConditionValue("inbdLtstRecFlg01", ZYPConstant.FLG_ON_Y);
        INBD_VISTMsgArray inbdVisTMsgList = (INBD_VISTMsgArray) EZDTBLAccessor.findByCondition(inbdVisTMsg);

        if (inbdVisTMsgList == null || inbdVisTMsgList.getValidCount() < 1) {

            lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM1295E, new String[] {"Inbound Visibility" });
            return false;
        }

        for (int i = 0; i < inbdVisTMsgList.getValidCount(); i++) {

            // Update INBD_VIS
            inbdVisTMsg = inbdVisTMsgList.no(i);
            ZYPEZDItemValueSetter.setValue(inbdVisTMsg.inbdLtstRecFlg, ZYPConstant.FLG_OFF_N);
            EZDTBLAccessor.update(inbdVisTMsg);

            if (!RTNCD_NORMAL.equals(inbdVisTMsg.getReturnCode())) {

                lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM1295E, new String[] {"Inbound Visibility" });
                return false;
            }

            // Delete INBD_VIS
            EZDTBLAccessor.logicalRemove(inbdVisTMsg);

            if (!RTNCD_NORMAL.equals(inbdVisTMsg.getReturnCode())) {

                lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM1295E, new String[] {"Inbound Visibility" });
                return false;
            }
        }

        return true;
    }

    /**
     * deleteWhSchd
     * @param bizMsg NLAL2030CMsg
     * @param lineMsg NLAL2030_BSMsg
     * @return boolean
     */
    private boolean deleteWhSchd(NLAL2030CMsg bizMsg, NLAL2030_BSMsg lineMsg) {

        if (SCE_ORD_TP.BUY_BACK.equals(lineMsg.sceOrdTpCd_B1.getValue()) || SCE_ORD_TP.RETURN.equals(lineMsg.sceOrdTpCd_B1.getValue())) {

            return true;
        }

        // Search WH_SCHD
        WH_SCHDTMsg whSchdTMsg = new WH_SCHDTMsg();
        whSchdTMsg.setSQLID("011");
        whSchdTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        whSchdTMsg.setConditionValue("poRcvNum01", lineMsg.trxOrdNum_B2.getValue());
        WH_SCHDTMsgArray whSchdTMsgList = (WH_SCHDTMsgArray) EZDTBLAccessor.findByCondition(whSchdTMsg);

        if (whSchdTMsgList == null || whSchdTMsgList.getValidCount() < 1) {

            lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM1295E, new String[] {"Warehouse Schedule" });
            return false;
        }

        // Delete WH_SCHD
        for (int i = 0; i < whSchdTMsgList.getValidCount(); i++) {

            whSchdTMsg = whSchdTMsgList.no(i);
            EZDTBLAccessor.logicalRemove(whSchdTMsg);

            if (!RTNCD_NORMAL.equals(whSchdTMsg.getReturnCode())) {

                lineMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NLBM1295E, new String[] {"Warehouse Schedule" });
                return false;
            }
        }

        return true;
    }

    /**
     * callAPIInvtyUpdForCancel
     * @param bizMsg NLAL2030CMsg
     * @param lineMsg NLAL2030_BSMsg
     * @param rwsDtlList List<Map<String, Object>>
     * @param stdCcyCd String
     * @return boolean
     */
    private boolean callAPIInvtyUpdForCancel(NLAL2030CMsg bizMsg, NLAL2030_BSMsg lineMsg, List<Map<String, Object>> rwsDtlList, String stdCcyCd) {

        if (!SCE_ORD_TP.DOMESTIC_CANON_GROUP.equals(lineMsg.sceOrdTpCd_B1.getValue())) {

            return true;
        }

        for (Map<String, Object> rwsDtl : rwsDtlList) {

            // Call Inventory Update API(NLZC002001)
            NLZC002001PMsg invtyUpdPMsg = new NLZC002001PMsg();
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.trxCd, TRX.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.trxRsnCd, TRX_RSN.INBOUND_IN_TRANSIT_STOCK_OUT);
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_DOM);
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.mdseCd, (String) rwsDtl.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.invtyLocCd, NLAL2030CommonLogic.getInvtyLocCd(bizMsg, (String) rwsDtl.get("RCV_RTL_WH_CD"), (String) rwsDtl.get("RCV_RTL_SWH_CD")));
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.locStsCd, LOC_STS.IN_TRANSIT);
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.stkStsCd, (String) rwsDtl.get("INVTY_STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.xxRqstQty, (BigDecimal) rwsDtl.get("RWS_BAL_QTY"));
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.invtyTrxDt, ZYPDateUtil.getSalesDate());
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.poRcvNum, (String) rwsDtl.get("TRX_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.poRcvLineNum, (String) rwsDtl.get("TRX_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.rwsNum, (String) rwsDtl.get("RWS_NUM"));
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.rwsRefNum, (String) rwsDtl.get("RWS_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.rwsDtlLineNum, (String) rwsDtl.get("RWS_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.poOrdNum, (String) rwsDtl.get("PO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.poOrdDtlLineNum, (String) rwsDtl.get("PO_ORD_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.invtyTrxSlpNum, (String) rwsDtl.get("RWS_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.vndCd, (String) rwsDtl.get("FROM_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.ccyCd, stdCcyCd);
            ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.uomCd, PKG_UOM.EACH);

            // Call Inventory Update API (NLZC0020)
            NLZC002001 rwsSerAPI = new NLZC002001();
            rwsSerAPI.execute(invtyUpdPMsg, ONBATCH_TYPE.ONLINE);

            if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(invtyUpdPMsg))) {

                return false;
            }
        }

        return true;
    }

    /**
     * callAPICpoRtrnLineUpd
     * @param bizMsg NLAL2030CMsg
     * @param lineMsg NLAL2030_BSMsg
     * @param rwsDtlList List<Map<String, Object>>
     * @return boolean
     */
    private boolean callAPICpoRtrnLineUpd(NLAL2030CMsg bizMsg, NLAL2030_BSMsg lineMsg, List<Map<String, Object>> rwsDtlList) {

        if (!SCE_ORD_TP.RETURN.equals(lineMsg.sceOrdTpCd_B1.getValue())) {

            return true;
        }

        for (Map<String, Object> rwsDtl : rwsDtlList) {

            // Call CPO Return Line Update API (NWZC153001)
            NWZC153001PMsg cpoRtrnLineUpdPMsg = new NWZC153001PMsg();
            ZYPEZDItemValueSetter.setValue(cpoRtrnLineUpdPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(cpoRtrnLineUpdPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_UPD_STS);
            ZYPEZDItemValueSetter.setValue(cpoRtrnLineUpdPMsg.cpoOrdNum, (String) rwsDtl.get("SRC_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(cpoRtrnLineUpdPMsg.slsDt, ZYPDateUtil.getSalesDate());

            NWZC153001_ordRtrnDtlListPMsg cpoDtl = cpoRtrnLineUpdPMsg.ordRtrnDtlList.no(0);
            ZYPEZDItemValueSetter.setValue(cpoDtl.xxRqstTpCd, NWZC153001Constant.RQST_DTL_RWC_CANCEL);
            ZYPEZDItemValueSetter.setValue(cpoDtl.dsCpoRtrnLineNum, (String) rwsDtl.get("SRC_ORD_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(cpoDtl.dsCpoRtrnLineSubNum, (String) rwsDtl.get("SRC_ORD_LINE_SUB_NUM"));
            cpoRtrnLineUpdPMsg.ordRtrnDtlList.setValidCount(1);

            List<NWZC153002PMsg> linePMsgList = new ArrayList<NWZC153002PMsg>();
            NWZC153002PMsg cpoDtl2 = new NWZC153002PMsg();
            ZYPEZDItemValueSetter.setValue(cpoDtl2.dsCpoRtrnLineNum, (String) rwsDtl.get("SRC_ORD_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(cpoDtl2.dsCpoRtrnLineSubNum, (String) rwsDtl.get("SRC_ORD_LINE_SUB_NUM"));
            linePMsgList.add(cpoDtl2);

            NWZC153001 cpoRtrnLineUpdAPI = new NWZC153001();
            cpoRtrnLineUpdAPI.execute(cpoRtrnLineUpdPMsg, linePMsgList, ONBATCH_TYPE.ONLINE);

            // Check API Result (NWZC153001PMsg)
            if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(cpoRtrnLineUpdPMsg))) {

                return false;
            }

            // Check API Result (NWZC153002PMsg)
            for (NWZC153002PMsg linePMsg : linePMsgList) {

                if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(linePMsg))) {

                    return false;
                }
            }

            // QC#23438
            // Cancel Machine Master
            NSZC001001PMsg rmaCancMachMstrUpdMsg = new NSZC001001PMsg();
            addRmaCancModeMachMstrUpdMsg(bizMsg.glblCmpyCd.getValue(), ZYPDateUtil.getSalesDate(), rwsDtl, rmaCancMachMstrUpdMsg);
            if (rmaCancMachMstrUpdMsg != null && ZYPCommonFunc.hasValue(rmaCancMachMstrUpdMsg.svcMachMstrPk)) {
                // START 01/27/2020 T.Ogura [QC#55497,MOD]
//                if (!callMachMstrUpdApi(rmaCancMachMstrUpdMsg)) {
                if (!callMachMstrUpdApi(bizMsg, rmaCancMachMstrUpdMsg)) {
                // END   01/27/2020 T.Ogura [QC#55497,MOD]
                    return false;
                }

                // Cancel Allocation
                NSZC001001PMsg allocOffMachMstrUpdMsg = new NSZC001001PMsg();
                addAllocOffModeMachMstrUpdMsg(bizMsg.glblCmpyCd.getValue(), ZYPDateUtil.getSalesDate(), rmaCancMachMstrUpdMsg.svcMachMstrPk.getValue(), allocOffMachMstrUpdMsg);
                // START 01/27/2020 T.Ogura [QC#55497,MOD]
//                if (!callMachMstrUpdApi(allocOffMachMstrUpdMsg)) {
                if (!callMachMstrUpdApi(bizMsg, allocOffMachMstrUpdMsg)) {
                // END   01/27/2020 T.Ogura [QC#55497,MOD]
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * callAPIPoyoUpd
     * @param bizMsg NLAL2030CMsg
     * @param lineMsg NLAL2030_BSMsg
     * @param rwsDtlList List<Map<String, Object>>
     * @return boolean
     */
    private boolean callAPIPoyoUpd(NLAL2030CMsg bizMsg, NLAL2030_BSMsg lineMsg, List<Map<String, Object>> rwsDtlList) {

        if (SCE_ORD_TP.RETURN.equals(lineMsg.sceOrdTpCd_B1.getValue())) {

            return true;
        }

        for (Map<String, Object> rwsDtl : rwsDtlList) {

            // Call POYO Update API (NPZC1090) [Create Inbounds
            // Visibility]
            NPZC109001PMsg poyoVisPMsg = new NPZC109001PMsg();
            ZYPEZDItemValueSetter.setValue(poyoVisPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(poyoVisPMsg.xxModeCd, NPZC109001Constant.POYO_INSERT_MODE);

            NPZC109001_detailListPMsg detail = poyoVisPMsg.detailList.no(0);
            ZYPEZDItemValueSetter.setValue(detail.poOrdNum, (String) rwsDtl.get("PO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(detail.poOrdDtlLineNum, (String) rwsDtl.get("PO_ORD_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(detail.xxQty10Num, (BigDecimal) rwsDtl.get("RWS_BAL_QTY"));
            poyoVisPMsg.detailList.setValidCount(1);

            NPZC109001 poyoVisAPI = new NPZC109001();
            poyoVisAPI.execute(poyoVisPMsg, ONBATCH_TYPE.ONLINE);

            if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(poyoVisPMsg))) {

                return false;
            }
        }

        return true;
    }

    /**
     * callAPISoConf
     * @param bizMsg NLAL2030CMsg
     * @param lineMsg NLAL2030_BSMsg
     * @param cancelDate String
     * @return boolean
     */
    private boolean callAPISoConf(NLAL2030CMsg bizMsg, NLAL2030_BSMsg lineMsg, String cancelDate) {

        if (!SCE_ORD_TP.BUY_BACK.equals(lineMsg.sceOrdTpCd_B1.getValue())) {

            return true;
        }

        SHPG_ORDTMsg shpgOrdTMsg = getShpgOrd(bizMsg, lineMsg.trxOrdNum_B1.getValue());

        if (shpgOrdTMsg == null) {

            return false;
        }

        // Call SO Confirmation from S21 DC API(NLZC210001)
        NLZC210001PMsg soConfPMsg = new NLZC210001PMsg();
        ZYPEZDItemValueSetter.setValue(soConfPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());

        //2017/09/21 S.Endo Mod Sol#069(QC#18270) START
        S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getBuyBackFromRtlWh(bizMsg.glblCmpyCd.getValue(), lineMsg.rwsNum_B1.getValue(), lineMsg.rwsDtlLineNum_B1.getValue());
        if (!ssmResult.isCodeNotFound()) {
            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(soConfPMsg.whCd, NLAL2030CommonLogic.getInvtyLocCd(bizMsg, (String)resultMap.get("SHIP_FROM_RTL_WH_CD"), (String)resultMap.get("SHIP_FROM_RTL_SWH_CD")));
        }
        //ZYPEZDItemValueSetter.setValue(soConfPMsg.whCd, NLAL2030CommonLogic.getInvtyLocCd(bizMsg, lineMsg.rtlWhCd_B1.getValue(), lineMsg.rtlSwhCd_B1.getValue()));
        //2017/09/21 S.Endo Mod Sol#069(QC#18270) END

        ZYPEZDItemValueSetter.setValue(soConfPMsg.soNum, shpgOrdTMsg.soNum.getValue());
        ZYPEZDItemValueSetter.setValue(soConfPMsg.sceOrdTpCd, lineMsg.sceOrdTpCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(soConfPMsg.soProcStsCd, SO_PROC_STS.ORDER_VOID);
        ZYPEZDItemValueSetter.setValue(soConfPMsg.shipDtTmTs, cancelDate);

        NLZC210001 soConfAPI = new NLZC210001();
        soConfAPI.execute(soConfPMsg, null, ONBATCH_TYPE.ONLINE);

        if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(soConfPMsg))) {

            return false;
        }

        return true;
    }

    /**
     * getShpgOrd
     * @param bizMsg NLAL2030CMsg
     * @param trxHdrNum String
     * @return SHPG_ORDTMsg
     */
    private SHPG_ORDTMsg getShpgOrd(NLAL2030CMsg bizMsg, String trxHdrNum) {

        SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
        shpgOrdTMsg.setSQLID("001");
        shpgOrdTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        shpgOrdTMsg.setConditionValue("trxHdrNum01", trxHdrNum);
        SHPG_ORDTMsgArray shpgOrdTMsgList = (SHPG_ORDTMsgArray) EZDTBLAccessor.findByCondition(shpgOrdTMsg);

        if (shpgOrdTMsgList == null || shpgOrdTMsgList.getValidCount() < 1) {

            return null;
        }

        return shpgOrdTMsgList.no(0);
    }

    /**
     * getRwsDtl
     * @param bizMsg NLAL2030CMsg
     * @param rwsNum String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getRwsDtl(NLAL2030CMsg bizMsg, String rwsNum) {

        S21SsmEZDResult result = NLAL2030Query.getInstance().getRwsAllView(bizMsg.glblCmpyCd.getValue(), rwsNum);

        if (result.isCodeNormal()) {
            return (List<Map<String, Object>>) result.getResultObject();
        } else {
            return null;
        }
    }

    /**
     * QC#23438
     * addRmaCancModeMachMstrUpdMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param rwsDtl Map<String, Object>
     * @param machMstrUpdMsg NSZC001001PMsg
     */
    private static void addRmaCancModeMachMstrUpdMsg(String glblCmpyCd, String slsDt, Map<String, Object> rwsDtl, NSZC001001PMsg machMstrUpdMsg) {

        String trxHdrNum = (String) rwsDtl.get("SRC_ORD_NUM");
        String trxLineNum = (String) rwsDtl.get("SRC_ORD_LINE_NUM");
        String trxLineSubNum = (String) rwsDtl.get("SRC_ORD_LINE_SUB_NUM");

        if (!ZYPCommonFunc.hasValue(trxHdrNum) || !ZYPCommonFunc.hasValue(trxLineNum) || !ZYPCommonFunc.hasValue(trxLineSubNum)) {
            return;
        }

        // START 2023/03/02 F.Fadriquela [QC#61225, MOD]
        //BigDecimal svcMachMstrPk = NLAL2030Query.getInstance().getSvcMachMstrPk(glblCmpyCd, trxHdrNum, trxLineNum, trxLineSubNum);
        DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.cpoOrdNum, trxHdrNum);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum, trxLineNum);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum, trxLineSubNum);
        dsCpoRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) EZDTBLAccessor.findByKey(dsCpoRtrnDtlTMsg);

        //if (svcMachMstrPk == null) {
        if (dsCpoRtrnDtlTMsg == null || !ZYPCommonFunc.hasValue(dsCpoRtrnDtlTMsg.svcMachMstrPk)) {
        // END 2023/03/02 F.Fadriquela [QC#61225, MOD]
            return;
        }

        // START 2023/03/02 F.Fadriquela [QC#61225, ADD]
        BigDecimal svcMachMstrPk = dsCpoRtrnDtlTMsg.svcMachMstrPk.getValue();
        // END 2023/03/02 F.Fadriquela [QC#61225, ADD]

        machMstrUpdMsg.glblCmpyCd.setValue(glblCmpyCd);
        machMstrUpdMsg.slsDt.setValue(slsDt);
        machMstrUpdMsg.xxModeCd.setValue(ProcessMode.RMA_CANCEL.code);
        machMstrUpdMsg.svcMachMstrPk.setValue(svcMachMstrPk);

        machMstrUpdMsg.svcMachMstrStsCd.setValue(getLatestStatusBeforeWaitingForRemoval(glblCmpyCd, svcMachMstrPk));

        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.stkStsCd, (String) rwsDtl.get("INVTY_STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxHdrNum, trxHdrNum);
        machMstrUpdMsg.trxLineNum.setValue(trxLineNum);
        machMstrUpdMsg.trxLineSubNum.setValue(trxLineSubNum);
        machMstrUpdMsg.svcMachUsgStsCd.setValue(SVC_MACH_USG_STS.AT_CUSTOMER);
    }

    /**
     * QC#23438
     * addAllocOffModeMachMstrUpdMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param svcMachMstrPk BigDecimal
     * @param machMstrUpdMsg NSZC001001PMsg
     */
    private static void addAllocOffModeMachMstrUpdMsg(String glblCmpyCd, String slsDt, BigDecimal svcMachMstrPk, NSZC001001PMsg machMstrUpdMsg) {
        machMstrUpdMsg.glblCmpyCd.setValue(glblCmpyCd);
        machMstrUpdMsg.slsDt.setValue(slsDt);
        machMstrUpdMsg.xxModeCd.setValue(ProcessMode.ALLOCATION_OFF.code);
        machMstrUpdMsg.svcMachMstrPk.setValue(svcMachMstrPk);
    }

    /**
     * QC#23438
     * getLatestStatusBeforeWaitingForRemoval
     * @param glblCmpyCd
     * @param svcMachMstrPk
     * @return
     */
    private static String getLatestStatusBeforeWaitingForRemoval(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        List<SVC_MACH_MSTR_HISTTMsg> svcMachMstrHistList = getSvcMachMstrHist(glblCmpyCd, svcMachMstrPk);
        if (svcMachMstrHistList.size() < 2) {
            return SVC_MACH_MSTR_STS.INSTALLED;
        }

        for (int recCnt = 0; recCnt < svcMachMstrHistList.size(); recCnt++) {
            SVC_MACH_MSTR_HISTTMsg svcMachMstrHistTMsg = svcMachMstrHistList.get(recCnt);
            String svcMachMstrStsCd = svcMachMstrHistTMsg.svcMachMstrStsCd.getValue();
            if (!SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachMstrStsCd)) {
                return svcMachMstrHistList.get(recCnt).svcMachMstrStsCd.getValue();
            }
        }
        return SVC_MACH_MSTR_STS.INSTALLED;
    }

    /**
     * QC#23438
     * getSvcMachMstrHist
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return
     */
    private static List<SVC_MACH_MSTR_HISTTMsg> getSvcMachMstrHist(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        S21SsmEZDResult ssmRslt = NLAL2030Query.getInstance().getSvcMachMstrHist(glblCmpyCd, svcMachMstrPk);
        if (ssmRslt.isCodeNormal()) {
            return (List<SVC_MACH_MSTR_HISTTMsg>) ssmRslt.getResultObject();
        } else {
            return null;
        }
    }

    /**
     * QC#23438
     * callAddHoldInfoAPI
     * @param bizMsg NLAL2030CMsg
     * @param machMstrUpdMsg NSZC001001PMsg
     * @return boolean
     */
    // START 01/27/2020 T.Ogura [QC#55497,MOD]
//    private static boolean callMachMstrUpdApi(NSZC001001PMsg machMstrUpdMsg) {
    private static boolean callMachMstrUpdApi(NLAL2030CMsg bizMsg, NSZC001001PMsg machMstrUpdMsg) {
    // END   01/27/2020 T.Ogura [QC#55497,MOD]
        NSZC001001 machMstrUpdApi = new NSZC001001();
        machMstrUpdApi.execute(machMstrUpdMsg, ONBATCH_TYPE.ONLINE);

        boolean isApiResultSuccess = true;
        if (machMstrUpdMsg.xxMsgIdList.getValidCount() > 0) {
            // START 01/27/2020 T.Ogura [QC#55497,MOD]
//            isApiResultSuccess = false;
            String errMsgId = null;
            for (int n = 0; n < machMstrUpdMsg.xxMsgIdList.getValidCount(); n++) {
                errMsgId = machMstrUpdMsg.xxMsgIdList.no(n).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(errMsgId)) {
                    if (errMsgId.endsWith("E")) {
                        S21InfoLogOutput.println(errMsgId);
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxMsgId, errMsgId);
                        isApiResultSuccess = false;
                        break;
                    }
                }
            }
            // END   01/27/2020 T.Ogura [QC#55497,MOD]
        }
        return isApiResultSuccess;
    }
    
    /**
     * doProcess_NLAL2030Scrn00_CMN_Submit
     * QC#18461-Sol#085 Add Method.
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     */
    private void doProcess_NLAL2030Scrn00_CMN_Submit(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        NLAL2030CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // Check.
        if(isInputErrorForSubmit(bizMsg, glblMsg)){
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);
            return;
        }

        // Warning
        if (isNotUpdateLine(glblMsg) //
                || !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_SB.getValue())) {
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_SB, ZYPConstant.FLG_ON_Y);
            bizMsg.setMessageInfo(NLAL2030Constant.NLAM1343W);
            return;
        }

        // Warning flag init.
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_SB, ZYPConstant.FLG_OFF_N);
        bizMsg.clearErrorInfo();

        // RWS Re-Create
        if (!reCreateRWS(bizMsg, glblMsg)) {
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);
            return;
        }

        bizMsg.setMessageInfo(NLAL2030Constant.NZZM0002I);
    }

    private boolean isInputErrorForSubmit(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg){

        boolean chkBoxSelect = false;
        boolean isError = false;
        HashMap<String, String> rwsWhMap = new HashMap<String, String>();
        HashMap<String, String> rwsRmaMap = new HashMap<String, String>();

        // All Warehouse Permit Setting
        // Role Function
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLAL2030Constant.BIZ_ID);
        String userId = getContextUserInfo().getUserId();

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        param.put("schdCoordPsnCd", userId);
        param.put("salesDate", ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));

        S21SsmEZDResult whPermitRs = NLAL2030Query.getInstance().getPermissionWhList(param);

        HashSet<String> targetRwsNumList = new HashSet<String>();

        for(int i = 0; i<glblMsg.B.getValidCount(); i++){
            NLAL2030_BSMsg bSMsg = glblMsg.B.no(i);

            String rwsNum = bSMsg.rwsNum_B1.getValue();
            String rtlWhCd = bSMsg.rtlWhCd_B1.getValue();
            String oldRtlWhCd = bSMsg.rtlWhCd_B2.getValue();

            if(!rwsWhMap.containsKey(rwsNum)){
                rwsWhMap.put(rwsNum, rtlWhCd);
            }

            if(!rwsRmaMap.containsKey(rwsNum)){
                rwsRmaMap.put(rwsNum, bSMsg.thirdPtyDspTpCd_B1.getValue());
            }

            if(ZYPConstant.CHKBOX_ON_Y.equals(bSMsg.xxChkBox_B1.getValue())//
                    || targetRwsNumList.contains(bSMsg.rwsNum_B1.getValue())){
                targetRwsNumList.add(bSMsg.rwsNum_B1.getValue());
                chkBoxSelect = true;
                // QC#55313
                if(SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(bSMsg.sceOrdTpCd_B1.getValue())){
                    bSMsg.xxChkBox_B1.setErrorInfo(1, NLAL2030Constant.NMAM0269E, new String[]{"Subcontract", "RWS"});
                    isError = true;
                }

                if(!RWS_STS.PRINTED.equals(bSMsg.rwsStsCd_B1.getValue())){
                    bSMsg.rtlWhCd_B1.setErrorInfo(1, NLAL2030Constant.NLAM1344E, new String[]{"Save"});
                    isError = true;
                }

                // check master
                RTL_WHTMsg rtlWhTmsg = getRtlWhTMsg(bizMsg.glblCmpyCd.getValue(), rtlWhCd);
                if (rtlWhTmsg == null) {
                    bSMsg.rtlWhCd_B1.setErrorInfo(1, NLAL2030Constant.NMAM0269E, new String[] {"warehouse only", rtlWhCd });
                    isError = true;
                    continue;
                }
                // WH Permit
                if (!hasWarehousePermission(bizMsg, whPermitRs, functionList, rtlWhCd, oldRtlWhCd)) {
                    bSMsg.rtlWhCd_B1.setErrorInfo(1, NLAL2030Constant.NLZM2313E);
                    isError = true;
                    continue;
                }
                // check same wh
                if (!isSameWhOfRWS(rwsWhMap, rwsNum, rtlWhCd)) {
                    bSMsg.rtlWhCd_B1.setErrorInfo(1, NLAL2030Constant.NLAM1345E, new String[]{"Warehouse"});
                    isError = true;
                    continue;
                }

                // check same Rma
                if (!isSameRmaOfRWS(rwsRmaMap, rwsNum, bSMsg.thirdPtyDspTpCd_B1.getValue())) {
                    bSMsg.thirdPtyDspTpCd_B1.setErrorInfo(1, NLAL2030Constant.NLAM1345E, new String[]{"RMA Disposition"});
                    isError = true;
                    continue;
                }

                // check wh ownr
                if(!isSameWhOwnr(rtlWhTmsg, bSMsg.invtyOwnrCd_B1.getValue())){
                    bSMsg.rtlWhCd_B1.setErrorInfo(1, NLAL2030Constant.NLAM1345E, new String[]{"Warehouse Owner"});
                    isError = true;
                    continue;
                }

                // check time stamp
                if(!checkTimeStampRWSCancel(bizMsg, glblMsg, bSMsg)){
                    isError = true;
                    continue;
                }

                // START 2021/11/11 A.Marte [QC#59350, ADD]
                // check RTL+SWH valid combination
                if (!NLAL2030CommonLogic.isValidRwhSwhCobination(bizMsg.glblCmpyCd.getValue(), bSMsg.rtlWhCd_B1.getValue() , bSMsg.rtlSwhCd_B1.getValue())) {
                    isError = true;
                    bSMsg.rtlWhCd_B1.setErrorInfo(1, NLAL2030Constant.NLAM1357E, new String[]{bSMsg.rtlWhCd_B1.getValue().concat(bSMsg.rtlSwhCd_B1.getValue())});
                }
                // END 2021/11/11 A.Marte [QC#59350, ADD]
            }
        }

        if(!chkBoxSelect){
            bizMsg.setMessageInfo(NLAL2030Constant.NLAM1288E);
            return true;
        }

        return isError;
    }

    private RTL_WHTMsg getRtlWhTMsg(String glblCmpyCd, String rtlWhCd) {

        RTL_WHTMsg cond = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cond.rtlWhCd, rtlWhCd);

        RTL_WHTMsg rslt = (RTL_WHTMsg) EZDTBLAccessor.findByKey(cond);

        // START 2018/12/03 S.Ohki [QC#29461,ADD]
        if (rslt == null) {
        	return null;
        }
        // END 2018/12/03 S.Ohki [QC#29461,ADD]

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String effFromDt = rslt.effFromDt.getValue();
        String effThruDt = rslt.effThruDt.getValue();

        if (!ZYPCommonFunc.hasValue(effThruDt)) {
            effThruDt = "99991231";
        }

        if (slsDt.compareTo(effFromDt) < 0 //
                || slsDt.compareTo(effThruDt) > 0) {
            // Not target wh.
            return null;
        }

        if (!LOC_TP.WAREHOUSE.equals(rslt.locTpCd.getValue())) {
            // Not target wh.
            return null;
        }

        return rslt;
    }

    public boolean hasWarehousePermission(NLAL2030CMsg bizMsg, S21SsmEZDResult whPermitRs, List<String> functionList, String rtlWhCd, String oldRtlWhCd) {

        if (functionList.contains(NLAL2030Constant.ROLE_ALL_WH_PERMISSION)) {
            return true;
        }

        if (whPermitRs.isCodeNormal()) {

            List<String> permissionWhList = (List<String>) whPermitRs.getResultObject();

            if (permissionWhList.isEmpty()) {

                return false;
            }

            // All Wh permission
            if (permissionWhList.contains("*")) {

                return true;
            }

            if (permissionWhList.contains(rtlWhCd) && permissionWhList.contains(oldRtlWhCd)) {

                return true;
            }
        }

        return false;
    }

    public boolean isSameWhOfRWS(HashMap<String, String> rwsWhMap, String rwsNum, String rtlWhCd) {

        String rwsHdrWhCd = rwsWhMap.get(rwsNum);

        if (rwsHdrWhCd.equals(rtlWhCd)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSameRmaOfRWS(HashMap<String, String> rwsRmaMap, String rwsNum, String thirdPtyDspTpCd) {

        String rwsThirdPtyDspTpCd = rwsRmaMap.get(rwsNum);

        if (rwsThirdPtyDspTpCd.equals(thirdPtyDspTpCd)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isSameWhOwnr(RTL_WHTMsg rtlWhTmsg, String invtyOwnrCd) {

        String ownrCd = rtlWhTmsg.invtyOwnrCd.getValue();

        if (ZYPCommonFunc.hasValue(ownrCd) && ownrCd.equals(invtyOwnrCd)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isNotUpdateLine(NLAL2030SMsg glblMsg) {

        boolean update = false;

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NLAL2030_BSMsg bLine = glblMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(bLine.xxChkBox_B1.getValue())) {

                String updRtlWhCd = bLine.rtlWhCd_B1.getValue();
                String oldRtlWhCd = bLine.rtlWhCd_B2.getValue();

                if (!updRtlWhCd.equals(oldRtlWhCd)) {
                    ZYPEZDItemValueSetter.setValue(bLine.rtlWhCd_B2, updRtlWhCd);
                    update = true;
                }

                String updthirdPtyDspTpCd = bLine.thirdPtyDspTpCd_B1.getValue();
                String oldthirdPtyDspTpCd = bLine.thirdPtyDspTpCd_B2.getValue();

                if (!updthirdPtyDspTpCd.equals(oldthirdPtyDspTpCd)) {
                    ZYPEZDItemValueSetter.setValue(bLine.thirdPtyDspTpCd_B2, updthirdPtyDspTpCd);
                    update = true;
                }
            }
        }

        return update;
    }
    
    /**
     * cancelRws
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @return boolean
     */
    private boolean reCreateRWS(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        HashSet<String> rwsNumSet = new HashSet<String>();
        HashSet<String> poRcvNumSet = new HashSet<String>();

        // QC#24413
        String stdCcyCd = getStdCcy(bizMsg.glblCmpyCd.getValue());

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            NLAL2030_BSMsg lineMsg = glblMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(lineMsg.xxChkBox_B1.getValue()) && !rwsNumSet.contains(lineMsg.rwsNum_B1.getValue())) {

                // Update RWS_HDR Cancel
                if (!updateRwsHdr(bizMsg, lineMsg)) {

                    bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"RWS" });
                    break;
                }

                rwsNumSet.add(lineMsg.rwsNum_B1.getValue());

                // Update RWS_DTL Cancel
                if (!updateRwsDtl(bizMsg, lineMsg)) {

                    bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"RWS" });
                    break;
                }

                // get RWS
                RWS_HDRTMsg rwsHdr = new RWS_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(rwsHdr.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rwsHdr.rwsNum, lineMsg.rwsNum_B1);
                rwsHdr = (RWS_HDRTMsg) EZDTBLAccessor.findByKey(rwsHdr);

                RWS_DTLTMsg cond = new RWS_DTLTMsg();
                cond.setSQLID("001");
                cond.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
                cond.setConditionValue("rwsNum01", lineMsg.rwsNum_B1.getValue());
                RWS_DTLTMsgArray rwsDtlList = (RWS_DTLTMsgArray) EZDTBLAccessor.findByCondition(cond);

                // Create RWS
                if(SCE_ORD_TP.RETURN.equals(lineMsg.sceOrdTpCd_B1.getValue())){
                	//QC#60364 K.Iwamoto 2022/10/08 MOD START
                	//createReturnRwsForSubmit(bizMsg, rwsHdr, rwsDtlList, lineMsg.dsOrdPosnNum_B1.getValue(), lineMsg.rtlWhCd_B1.getValue(), lineMsg.thirdPtyDspTpCd_B1.getValue());
                    createReturnRwsForSubmit(bizMsg, rwsHdr, rwsDtlList, lineMsg.dsOrdPosnNum_B1.getValue(), lineMsg.rtlWhCd_B1.getValue(), lineMsg.rtlSwhCd_B1.getValue(), lineMsg.thirdPtyDspTpCd_B1.getValue());
                    //QC#60364 K.Iwamoto 2022/10/08 MOD END
                } else {
                    // PO

                    // Cancel PO_RCV
                    if (ZYPCommonFunc.hasValue(lineMsg.trxOrdNum_B2) && !poRcvNumSet.contains(lineMsg.trxOrdNum_B2.getValue())) {

                        if (!updatePoRcvHdr(bizMsg, lineMsg)) {
                            bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"PO Receiving" });
                            break;
                        }

                        if (!updatePoRcvDtl(bizMsg, lineMsg)) {
                            bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"PO Receiving" });
                            break;
                        }

                        poRcvNumSet.add(lineMsg.trxOrdNum_B2.getValue());
                    }

                    // Update /Delete INBD_VIS
                    if (!updateDeleteInbdVis(bizMsg, lineMsg)) {
                        bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"Inbound Visibility" });
                        break;
                    }

                    // Delete WH_SCHD
                    if (!deleteWhSchd(bizMsg, lineMsg)) {
                        bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"Warehouse Schedule" });
                    }

                    // Search RWS_DTL
                    List<Map<String, Object>> rwsDtlMap = getRwsDtl(bizMsg, lineMsg.rwsNum_B1.getValue());

                    // Call POYO Update API (NPZC1090) [Create Inbounds
                    // Visibility]
                    if (!callAPIPoyoUpd(bizMsg, lineMsg, rwsDtlMap)) {
                        bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"POYO" });
                        break;
                    }

                    // Create PO_RCV
                    // Call PO Receiving API (NLZC2010)
                    NLZC201001PMsg poRcvPMsg = new NLZC201001PMsg();
                    // QC#24413
                    if (!callAPIPoRcvForSubmit(bizMsg, poRcvPMsg, lineMsg, rwsDtlMap)) {
                        return false;
                    }

                    // Call RWS API (NLZC2000)
                    NLZC200001PMsg rwsPMsg = new NLZC200001PMsg();
                    if (!callAPIRwsCreate(bizMsg, poRcvPMsg, rwsPMsg)) {
                        return false;
                    }

                    // START 2021/03/09 F.Deola [QC#58375, MOD]
                    // Call RWS Serial API (NLZC3040)
                    /*if (!callAPIRwsSerialForSubmit(bizMsg, glblMsg, rwsPMsg, lineMsg)) {
                        return false;
                    }*/
                    i = callAPIRwsSerialForSubmit(bizMsg, glblMsg, rwsPMsg, i);
                    if (i == -1) {
                        return false;
                    }
                    // END 2021/03/09 F.Deola [QC#58375, MOD]

                    // QC#24413
                    // Call Inventory Update API(NLZC002001)
                    if (!callAPIInvtyUpdForCancel(bizMsg, lineMsg, rwsDtlMap, stdCcyCd)) {

                        bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"Inventory" });
                        break;
                    }
                }
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {

            bizMsg.xxPageShowFromNum_A.setValue(0);
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return false;
        }

        return true;
    }
    //QC#60364 K.Iwamoto 2022/10/08 MOD START
    /**
     * createReturnRwsForSubmit
     * @param bizMsg NLAL2030CMsg
     * @param rtrnRwsList List<NLAL2030_ASMsg>
     * @return boolean
     */
//    private boolean createReturnRwsForSubmit(NLAL2030CMsg bizMsg, RWS_HDRTMsg cancelRwsHdr,  RWS_DTLTMsgArray cancelRwsDtlList, String dsOrdPosnNum, String whCd, String thirdPtyDspTpCd) {
//
//        int dtlSize = 0;
//        NLZC200001PMsg rtrnRwsPMsg = new NLZC200001PMsg();
//
//        // set Header
//        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.glblCmpyCd, cancelRwsHdr.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
//        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.inbdSrcTpCd, INBD_SRC_TP.RETURN);
//        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.slsDt, ZYPDateUtil.getSalesDate());
//        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.cpoOrdNum, cancelRwsHdr.trxOrdNum);
//        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.dsOrdPosnNum, dsOrdPosnNum);
//        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.rwsRefNum, cancelRwsHdr.rwsRefNum);
//        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.bolNum, cancelRwsHdr.imptInvBolNum);
//
//        for (int i = 0; i < cancelRwsDtlList.getValidCount(); i++) {
//            RWS_DTLTMsg dtl = cancelRwsDtlList.no(i);
//
//            ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.xxDetailList.no(dtlSize).dsCpoRtrnLineNum, dtl.trxLineNum);
//            ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.xxDetailList.no(dtlSize).dsCpoRtrnLineSubNum, dtl.trxLineSubNum);
//            ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.xxDetailList.no(dtlSize).ordQty, dtl.rwsQty);
//            String serNum = NLAL2030Query.getInstance().getSerialFromReturnOrdRws(cancelRwsHdr.glblCmpyCd.getValue(), dtl.rwsNum.getValue(), dtl.rwsDtlLineNum.getValue(), dtl.mdseCd.getValue());
//            ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.xxDetailList.no(dtlSize).serNum, serNum);
//
//            dtlSize++;
//            rtrnRwsPMsg.xxDetailList.setValidCount(dtlSize);
//        }
//
//        if (0 < dtlSize) {
//
//            // Call RWS API (NLZC2000)
//            NLZC200001 rwsApi = new NLZC200001();
//            rwsApi.execute(rtrnRwsPMsg, ONBATCH_TYPE.ONLINE);
//
//            // Check Error in API
//            if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(rtrnRwsPMsg))) {
//
//                bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"Return RWS" });
//                bizMsg.xxPageShowFromNum_A.setValue(0);
//                return false;
//            }
//
//            // Update RWS
//            for (int i = 0; i < rtrnRwsPMsg.RWSNumList.getValidCount(); i++) {
//                String rwsNum = rtrnRwsPMsg.RWSNumList.no(i).rwsNum.getValue();
//                updateRws(bizMsg.glblCmpyCd.getValue(), rwsNum, whCd, thirdPtyDspTpCd, true);
//            }
//        }
//
//        return true;
//    }

    /**
     * createReturnRwsForSubmit
     * @param bizMsg NLAL2030CMsg
     * @param rtrnRwsList List<NLAL2030_ASMsg>
     * @return boolean
     */
    private boolean createReturnRwsForSubmit(NLAL2030CMsg bizMsg, RWS_HDRTMsg cancelRwsHdr,  RWS_DTLTMsgArray cancelRwsDtlList, String dsOrdPosnNum, String whCd, String sWhCd, String thirdPtyDspTpCd) {

        int dtlSize = 0;
        NLZC200001PMsg rtrnRwsPMsg = new NLZC200001PMsg();

        // set Header
        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.glblCmpyCd, cancelRwsHdr.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.inbdSrcTpCd, INBD_SRC_TP.RETURN);
        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.cpoOrdNum, cancelRwsHdr.trxOrdNum);
        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.dsOrdPosnNum, dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.rwsRefNum, cancelRwsHdr.rwsRefNum);
        ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.bolNum, cancelRwsHdr.imptInvBolNum);

        for (int i = 0; i < cancelRwsDtlList.getValidCount(); i++) {
            RWS_DTLTMsg dtl = cancelRwsDtlList.no(i);

            ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.xxDetailList.no(dtlSize).dsCpoRtrnLineNum, dtl.trxLineNum);
            ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.xxDetailList.no(dtlSize).dsCpoRtrnLineSubNum, dtl.trxLineSubNum);
            ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.xxDetailList.no(dtlSize).ordQty, dtl.rwsQty);
            String serNum = NLAL2030Query.getInstance().getSerialFromReturnOrdRws(cancelRwsHdr.glblCmpyCd.getValue(), dtl.rwsNum.getValue(), dtl.rwsDtlLineNum.getValue(), dtl.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtrnRwsPMsg.xxDetailList.no(dtlSize).serNum, serNum);

            dtlSize++;
            rtrnRwsPMsg.xxDetailList.setValidCount(dtlSize);
        }

        if (0 < dtlSize) {

            // Call RWS API (NLZC2000)
            NLZC200001 rwsApi = new NLZC200001();
            rwsApi.execute(rtrnRwsPMsg, ONBATCH_TYPE.ONLINE);

            // Check Error in API
            if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(rtrnRwsPMsg))) {

                bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"Return RWS" });
                bizMsg.xxPageShowFromNum_A.setValue(0);
                return false;
            }

            // Update RWS
            for (int i = 0; i < rtrnRwsPMsg.RWSNumList.getValidCount(); i++) {
                String rwsNum = rtrnRwsPMsg.RWSNumList.no(i).rwsNum.getValue();
                updateRws(bizMsg.glblCmpyCd.getValue(), rwsNum, whCd, sWhCd, thirdPtyDspTpCd, true);
            }
        }

        return true;
    }
    //#QC60364 K.Iwamoto 2022/10/08/ MOD END
    //#QC60364 K.Iwamoto 2022/10/08/ MOD START
//    private void updateRws(String glblCmpyCd , String rwsNum, String whCd, String thirdPtyDspTpCd, boolean isRtrnOrd){
//        
//        RWS_HDRTMsg rwsHdr = new RWS_HDRTMsg();
//        ZYPEZDItemValueSetter.setValue(rwsHdr.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(rwsHdr.rwsNum, rwsNum);
//        rwsHdr = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rwsHdr);
//
//        ZYPEZDItemValueSetter.setValue(rwsHdr.whCd, whCd);
//
//        EZDTBLAccessor.update(rwsHdr);
//
//        RWS_DTLTMsg cond = new RWS_DTLTMsg();
//        cond.setSQLID("001");
//        cond.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        cond.setConditionValue("rwsNum01", rwsNum);
//        RWS_DTLTMsgArray rwsDtlList = (RWS_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(cond);
//
//        for (int j = 0; j < rwsDtlList.getValidCount(); j++) {
//            RWS_DTLTMsg rwsDtl = rwsDtlList.no(j);
//
//            ZYPEZDItemValueSetter.setValue(rwsDtl.rtlWhCd, whCd);
//            String invtyLocCd = whCd + rwsDtl.rtlSwhCd.getValue();
//            ZYPEZDItemValueSetter.setValue(rwsDtl.invtyLocCd, invtyLocCd);
//            if (isRtrnOrd) {
//                ZYPEZDItemValueSetter.setValue(rwsDtl.thirdPtyDspTpCd, thirdPtyDspTpCd);
//            }
//
//            EZDTBLAccessor.update(rwsDtl);
//        }
//    }

    private void updateRws(String glblCmpyCd , String rwsNum, String whCd, String sWhCd, String thirdPtyDspTpCd, boolean isRtrnOrd){
        
        RWS_HDRTMsg rwsHdr = new RWS_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(rwsHdr.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsHdr.rwsNum, rwsNum);
        rwsHdr = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rwsHdr);

        ZYPEZDItemValueSetter.setValue(rwsHdr.whCd, whCd);

        EZDTBLAccessor.update(rwsHdr);

        RWS_DTLTMsg cond = new RWS_DTLTMsg();
        cond.setSQLID("001");
        cond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cond.setConditionValue("rwsNum01", rwsNum);
        RWS_DTLTMsgArray rwsDtlList = (RWS_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(cond);

        for (int j = 0; j < rwsDtlList.getValidCount(); j++) {
            RWS_DTLTMsg rwsDtl = rwsDtlList.no(j);

            ZYPEZDItemValueSetter.setValue(rwsDtl.rtlWhCd, whCd);
            ZYPEZDItemValueSetter.setValue(rwsDtl.rtlSwhCd, sWhCd);
            String invtyLocCd = whCd + sWhCd;
            ZYPEZDItemValueSetter.setValue(rwsDtl.invtyLocCd, invtyLocCd);
            if (isRtrnOrd) {
                ZYPEZDItemValueSetter.setValue(rwsDtl.thirdPtyDspTpCd, thirdPtyDspTpCd);
            }

            EZDTBLAccessor.update(rwsDtl);
        }
    }
    //QC#60364 K.Iwamoto 2022/10/08 ADD END

    /**
     * callAPIRwsSerial
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param rwsPMsg NLZC200001PMsg
     * @param poRwsList List<NLAL2030_ASMsg>
     * @return boolean
     */
    private boolean createRwsSerialForSubmit(NLAL2030CMsg bizMsg, String cancelRwsNum, String rwsNum) {

        boolean hasErr = false;

        // Header
        NLZC304001PMsg rwsSerPMsg = new NLZC304001PMsg();
        ZYPEZDItemValueSetter.setValue(rwsSerPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsSerPMsg.rwsNum, rwsNum);

        int size = 0;

        List<Map<String, Object>> rwsSerList = NLAL2030Query.getInstance().getSerialFromPoRws(bizMsg.glblCmpyCd.getValue(), cancelRwsNum, rwsNum);

        if (rwsSerList != null) {
            for (Map<String, Object> serial : rwsSerList) {
                ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).rwsDtlLineNum, (String) serial.get("RWS_DTL_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).mdseCd, (String) serial.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).putAwayChkStsCd, PUT_AWAY_CHK_STS.NO_NEED);
                ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).serNumSendFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).serNum, (String) serial.get("SER_NUM"));
                size++;
            }

            rwsSerPMsg.SerialList.setValidCount(size);
        }

        if (0 < size) {

            // Call RWS Serial API (NLZC3040)
            NLZC304001 rwsSerAPI = new NLZC304001();
            rwsSerAPI.execute(rwsSerPMsg, ONBATCH_TYPE.ONLINE);

            // Check Error in API
            if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(rwsSerPMsg))) {

                hasErr = true;
            }
        }

        if (hasErr) {

            bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"RWS Serial" });
            bizMsg.xxPageShowFromNum_A.setValue(0);
            return false;
        }

        return true;
    }
    
    /**
     * callAPIPoyoUpd
     * @param bizMsg NLAL2030CMsg
     * @param lineMsg NLAL2030_BSMsg
     * @param rwsDtlList List<Map<String, Object>>
     * @return boolean
     */
    private boolean callAPIPoyoUpdForSubmit(NLAL2030CMsg bizMsg, String rwsNum, String poOrdNum) {

        RWS_DTLTMsg cond = new RWS_DTLTMsg();
        cond.setSQLID("001");
        cond.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        cond.setConditionValue("rwsNum01", rwsNum);
        RWS_DTLTMsgArray rwsDtlList = (RWS_DTLTMsgArray) EZDTBLAccessor.findByCondition(cond);

        for (int i = 0; i < rwsDtlList.getValidCount(); i++) {
            RWS_DTLTMsg dtl = rwsDtlList.no(i);

            // Call POYO Update API (NPZC1090) [Create Inbounds
            // Visibility]
            NPZC109001PMsg poyoVisPMsg = new NPZC109001PMsg();
            ZYPEZDItemValueSetter.setValue(poyoVisPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(poyoVisPMsg.xxModeCd, NPZC109001Constant.POYO_INSERT_MODE);

            NPZC109001_detailListPMsg detail = poyoVisPMsg.detailList.no(0);
            ZYPEZDItemValueSetter.setValue(detail.poOrdNum, poOrdNum);
            ZYPEZDItemValueSetter.setValue(detail.poOrdDtlLineNum, dtl.poRcvTrxLineNum);
            ZYPEZDItemValueSetter.setValue(detail.xxQty10Num, dtl.rwsQty);
            poyoVisPMsg.detailList.setValidCount(1);

            NPZC109001 poyoVisAPI = new NPZC109001();
            poyoVisAPI.execute(poyoVisPMsg, ONBATCH_TYPE.ONLINE);

            if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(poyoVisPMsg))) {

                return false;
            }
        }

        return true;
    }
    
    
    /**
     * callAPIPoRcv Mod QC#24413
     * @param bizMsg NLAL2030CMsg
     * @param poRcvPMsg NLZC201001PMsg
     * @param bMsg NLAL2030_BSMsg
     * @param rwsDtlMapList List<Map<String, Object>
     * @return boolean
     */
    private boolean callAPIPoRcvForSubmit(NLAL2030CMsg bizMsg, NLZC201001PMsg poRcvPMsg, NLAL2030_BSMsg bMsg, List<Map<String, Object>> rwsDtlMapList) {

        String rwsRefNum = "";
        ZYPEZDItemValueSetter.setValue(poRcvPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvPMsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(poRcvPMsg.sceOrdTpCd, bMsg.sceOrdTpCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvPMsg.poRcvFromLocTpCd, LOC_TP.VENDOR);
        ZYPEZDItemValueSetter.setValue(poRcvPMsg.poRcvFromLocCd, bMsg.fromLocCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvPMsg.whCd, bMsg.rtlWhCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvPMsg.poRcvDrctShipTpCd, DRCT_SHIP_TP.N_OR_A);
        ZYPEZDItemValueSetter.setValue(poRcvPMsg.poRcvEtaDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(poRcvPMsg.poRcvTrxHdrNum, bMsg.trxOrdNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvPMsg.domInvNum, bMsg.rwsRefNum_B1);
        ZYPEZDItemValueSetter.setValue(poRcvPMsg.svcConfigMstrPk, bMsg.svcConfigMstrPk_B1.getValue());

        // Detail
        for ( int i = 0; i < rwsDtlMapList.size(); i++) {
            Map<String, Object> rwsDtlMap = rwsDtlMapList.get(i);

            if (i == 0) {

                // START 2022/11/23 R.Azucena [QC#60835, MOD]
                // rwsRefNum = NLAL2030CommonLogic.getRwsRefNum((String) rwsDtlMap.get("RWS_REF_NUM"));
                rwsRefNum = NLAL2030CommonLogic.getRwsRefNum(bizMsg.glblCmpyCd.getValue(), bMsg.rtlWhCd_B1.getValue(), (String) rwsDtlMap.get("RWS_REF_NUM"));

                if (!ZYPCommonFunc.hasValue(rwsRefNum)) {
                    bizMsg.setMessageInfo(NLAL2030Constant.NLAM1359E, new String[]{(String) rwsDtlMap.get("RWS_REF_NUM")});
                    return false;
                }
                // END 2022/11/23 R.Azucena [QC#60835, MOD]

                if (rwsRefNum.length() > poRcvPMsg.getAttr("rwsRefNum").getDigit()) {

                    bizMsg.setMessageInfo("NPAM1601E");
                    return false;
                }
            }

            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(i).mdseCd, (String) rwsDtlMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(i).stkStsCd, STK_STS.GOOD);
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(i).poRcvQty, (BigDecimal) rwsDtlMap.get("RWS_BAL_QTY"));
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(i).poRcvTrxLineNum, (String) rwsDtlMap.get("SRC_ORD_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(i).invtyLocCd, NLAL2030CommonLogic.getInvtyLocCd(bizMsg, bMsg.rtlWhCd_B1.getValue(), bMsg.rtlSwhCd_B1.getValue()));
            ZYPEZDItemValueSetter.setValue(poRcvPMsg.OrdInfoLIst.no(i).shipFromInvtyLocCd, (String) rwsDtlMap.get("FROM_LOC_CD"));
            poRcvPMsg.OrdInfoLIst.setValidCount(i + 1);
        }

        ZYPEZDItemValueSetter.setValue(poRcvPMsg.rwsRefNum, rwsRefNum);

        // Call API
        NLZC201001 poRcvAPI = new NLZC201001();
        // START 2018/12/03 S.Ohki [QC#29461,MOD]
//        poRcvAPI.execute(poRcvPMsg, ONBATCH_TYPE.ONLINE);
        try {
            poRcvAPI.execute(poRcvPMsg, ONBATCH_TYPE.ONLINE);
        } catch (S21AbendException e) {
        	bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"PO Receiving" });
            return false;
        }
        // END 2018/12/03 S.Ohki [QC#29461,MOD]

        // Check Error in API
        if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(poRcvPMsg))) {

            bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"PO Receiving" });
            bizMsg.xxPageShowFromNum_A.setValue(0);
            return false;

        } else {

            poRcvPMsg = null;
        }

        return true;
    }

    /**
     * callAPIRwsSerial
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param rwsPMsg NLZC200001PMsg
     * @param currTableIndex int
     * @return int
     */
    // START 2021/03/09 F.Deola [QC#58375, MOD]
    // private boolean callAPIRwsSerialForSubmit(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, NLZC200001PMsg rwsPMsg, NLAL2030_BSMsg lineMsg) {
    private int callAPIRwsSerialForSubmit(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, NLZC200001PMsg rwsPMsg, int currTableIndex) {
    // END 2021/03/09 F.Deola [QC#58375, MOD]

        List<Map<String, Object>> rwsDtlList = null;
        boolean hasErr = false;

        if (rwsPMsg != null) {
            rwsDtlList = getRwsDtl(bizMsg, rwsPMsg.RWSNumList.no(0).rwsNum.getValue());
        }

        if (rwsDtlList == null) {
            hasErr = true;
        } else {

            // Header
            NLZC304001PMsg rwsSerPMsg = new NLZC304001PMsg();
            ZYPEZDItemValueSetter.setValue(rwsSerPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rwsSerPMsg.rwsNum, rwsPMsg.RWSNumList.no(0).rwsNum.getValue());

            int size = 0;

            // Detail
            for (Map<String, Object> rwsDtlMap : rwsDtlList) {

                String poOrdNum = (String) rwsDtlMap.get("PO_ORD_NUM");
                String poOrdDtlLineNum = (String) rwsDtlMap.get("PO_ORD_DTL_LINE_NUM");

                // START 2021/03/09 F.Deola [QC#58375, ADD]
                NLAL2030_BSMsg lineMsg = glblMsg.B.no(currTableIndex);
                // END 2021/03/09 F.Deola [QC#58375, ADD]

                if ((ZYPCommonFunc.hasValue(poOrdNum) && !poOrdNum.equals(lineMsg.trxOrdNum_B1.getValue())) //
                        || (ZYPCommonFunc.hasValue(poOrdDtlLineNum) && !poOrdDtlLineNum.equals(lineMsg.trxLineNum_B1.getValue()))) {
                    continue;
                }

                // Serial
                List<String> serNumList = new ArrayList<String>();

                if (1 < lineMsg.xxQty10Num_B1.getValueInt()) {
                    serNumList = getSerNumListForSubmit(bizMsg.glblCmpyCd.getValue(), glblMsg, lineMsg);
                } else if (1 == lineMsg.xxQty10Num_B1.getValueInt() && ZYPCommonFunc.hasValue(lineMsg.serNum_B1)) {
                    serNumList.add(lineMsg.serNum_B1.getValue());
                }

                // Set Serial information to PMsg
                if (!serNumList.isEmpty()) {

                    for (String serNum : serNumList) {
                        ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).rwsDtlLineNum, (String) rwsDtlMap.get("RWS_DTL_LINE_NUM"));
                        ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).mdseCd, lineMsg.mdseCd_B1.getValue());
                        ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).putAwayChkStsCd, PUT_AWAY_CHK_STS.NO_NEED);
                        ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).serNumSendFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(rwsSerPMsg.SerialList.no(size).serNum, serNum);
                        size++;
                    }

                    rwsSerPMsg.SerialList.setValidCount(size);
                }

                // START 2021/03/09 F.Deola [QC#58375, ADD]
                currTableIndex++;
                // END 2021/03/09 F.Deola [QC#58375, ADD]
            }

            if (0 < size) {

                // Call RWS Serial API (NLZC3040)
                NLZC304001 rwsSerAPI = new NLZC304001();
                rwsSerAPI.execute(rwsSerPMsg, ONBATCH_TYPE.ONLINE);

                // Check Error in API
                if (!NLAL2030CommonLogic.chkApiExecRslt(bizMsg, S21ApiUtil.getXxMsgIdList(rwsSerPMsg))) {

                    hasErr = true;
                }
            }
        }

        if (hasErr) {

            bizMsg.setMessageInfo(NLAL2030Constant.NLBM1295E, new String[] {"RWS Serial" });
            bizMsg.xxPageShowFromNum_A.setValue(0);
            // START 2021/03/09 F.Deola [QC#58375, MOD]
            //return false;
            return -1;
            // END 2021/03/09 F.Deola [QC#58375, MOD]
        }

        // START 2021/03/09 F.Deola [QC#58375, MOD]
        //return true;
        return --currTableIndex;
        // END 2021/03/09 F.Deola [QC#58375, MOD]
    }
    
    /**
     * getSerNumList
     * @param glblCmpyCd String
     * @param glblMsg NLAL2030SMsg
     * @param lineMsg NLAL2030_ASMsg
     * @return List<String>
     */
    private List<String> getSerNumListForSubmit(String glblCmpyCd, NLAL2030SMsg glblMsg, NLAL2030_BSMsg lineMsg) {

        List<String> serNumList = new ArrayList<String>();

        // Search Serial# in SMsg
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {

            NLAL2030_CSMsg serMsg = glblMsg.C.no(i);

            if (serMsg.trxOrdNum.getValue().equals(lineMsg.trxOrdNum_B1.getValue()) && serMsg.trxLineNum.getValue().equals(lineMsg.trxLineNum_B1.getValue())) {

                if (ZYPCommonFunc.hasValue(lineMsg.trxLineSubNum_B1) && lineMsg.trxLineSubNum_B1.equals(serMsg.trxLineSubNum.getValue())) {

                    serNumList.add(serMsg.serNum.getValue());

                } else if (!ZYPCommonFunc.hasValue(lineMsg.trxLineSubNum_B1)) {

                    serNumList.add(serMsg.serNum.getValue());
                }
            }
        }

        if (serNumList.size() == 0) {

            S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getSerNumListForOrd(glblCmpyCd, lineMsg.trxOrdNum_B1.getValue(), lineMsg.trxLineNum_B1.getValue(), lineMsg.trxLineSubNum_B1.getValue());

            if (!ssmResult.isCodeNotFound()) {

                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

                for (Map<String, Object> serNumMap : resultList) {

                    serNumList.add((String) serNumMap.get("SER_NUM"));
                }
            }
        }

        return serNumList;
    }
    /**
     * Change Service Machine Master Add QC#24070
     * @param bizMsg NLAL2030CMsg
     * @param rtrnRwsList List<NLAL2030_ASMsg>
     * @return boolean
     */
    private boolean changeSmmStatusW4R(NLAL2030CMsg bizMsg, List<NLAL2030_ASMsg> rtrnRwsList) {

        for (NLAL2030_ASMsg rtrnLine : rtrnRwsList) {

            Map<String, Object> rtrnOrderMap = NLAL2030Query.getInstance().getDsCpoRtrnInfo(bizMsg.glblCmpyCd.getValue(), rtrnLine);

            if (rtrnOrderMap == null || rtrnOrderMap.isEmpty()) {
                return true;
            }

            BigDecimal svcMachMstrPk = (BigDecimal) rtrnOrderMap.get("SVC_MACH_MSTR_PK");
            if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                return true;
            }

            SVC_MACH_MSTRTMsg smmTMsg = new SVC_MACH_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(smmTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(smmTMsg.svcMachMstrPk, svcMachMstrPk);
            smmTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdate(smmTMsg);

            if (smmTMsg == null) {
                return false;
            }

            if (!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, smmTMsg.svcMachMaintAvalFlg.getValue()) //
                    || !S21StringUtil.isEquals(SVC_MACH_MSTR_STS.INSTALLED, smmTMsg.svcMachMstrStsCd.getValue())) {
                String errMsgId = "NLZM2424E";
                S21InfoLogOutput.println(errMsgId);
                rtrnLine.xxChkBox_A2.setErrorInfo(1, errMsgId);
                return false;
            }
            if (!callMachMstrUpdApi(bizMsg, getRmaModeMachMstrUpdMsg(bizMsg, rtrnOrderMap), rtrnLine)) {
                return false;
            }

            if (!callMachMstrUpdApi(bizMsg, getAllocOnModeMachMstrUpdMsg(bizMsg, rtrnOrderMap), rtrnLine)) {
                return false;
            }
        }

        return true;
    }

    /**
     * getRmaModeMachMstrUpdMsg Add QC#24070
     * @param bizMsg NLAL2030CMsg
     * @param rtrnRwsList List<NLAL2030_ASMsg>
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg getRmaModeMachMstrUpdMsg(NLAL2030CMsg bizMsg, Map<String, Object> rtrnOrderMap) {

        NSZC001001PMsg machMstrUpdMsg = new NSZC001001PMsg();

        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.slsDt, ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.xxModeCd, ProcessMode.RMA.code);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachMstrPk, (BigDecimal) rtrnOrderMap.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.stkStsCd, (String) rtrnOrderMap.get("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.rtrnToWhCd, (String) rtrnOrderMap.get("RTRN_TO_WH_CD"));

        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxHdrNum, (String) rtrnOrderMap.get("CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxLineNum, (String) rtrnOrderMap.get("DS_CPO_RTRN_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxLineSubNum, (String) rtrnOrderMap.get("DS_CPO_RTRN_LINE_SUB_NUM"));
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_TRANSIT);

        return machMstrUpdMsg;
    }

    /**
     * getAllocOnModeMachMstrUpdMsg Add QC#24070
     * @param bizMsg NLAL2030CMsg
     * @param rtrnRwsList List<NLAL2030_ASMsg>
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg getAllocOnModeMachMstrUpdMsg(NLAL2030CMsg bizMsg, Map<String, Object> rtrnOrderMap) {

        NSZC001001PMsg machMstrUpdMsg = new NSZC001001PMsg();
        machMstrUpdMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        machMstrUpdMsg.slsDt.setValue(ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
        machMstrUpdMsg.xxModeCd.setValue(ProcessMode.ALLOCATION_ON.code);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachMstrPk, (BigDecimal) rtrnOrderMap.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxHdrNum, (String) rtrnOrderMap.get("CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxLineNum, (String) rtrnOrderMap.get("DS_CPO_RTRN_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxLineSubNum, (String) rtrnOrderMap.get("DS_CPO_RTRN_LINE_SUB_NUM"));

        return machMstrUpdMsg;
    }

    /**
     * callMachMstrUpdApi Add QC#24070
     * @param bizMsg NLAL2030CMsg
     * @param machMstrUpdMsg NSZC001001PMsg
     * @param rtrnLine NLAL2030_ASMsg
     * @return NSZC001001PMsg
     */
    private boolean callMachMstrUpdApi(NLAL2030CMsg bizMsg, NSZC001001PMsg machMstrUpdMsg, NLAL2030_ASMsg rtrnLine) {
        NSZC001001 machMstrUpdApi = new NSZC001001();
        machMstrUpdApi.execute(machMstrUpdMsg, ONBATCH_TYPE.BATCH);

        boolean isApiResultSuccess = true;
        if (machMstrUpdMsg.xxMsgIdList.getValidCount() > 0) {
            String errMsgId = null;
            for (int n = 0; n < machMstrUpdMsg.xxMsgIdList.getValidCount(); n++) {
                errMsgId = machMstrUpdMsg.xxMsgIdList.no(n).xxMsgId.getValue();
                S21InfoLogOutput.println(errMsgId);
                rtrnLine.xxChkBox_A2.setErrorInfo(1, errMsgId);
            }
            isApiResultSuccess = false;
        }
        return isApiResultSuccess;
    }

    /**
     * Add QC#24413. getOpenRwsQty
     * @param glblCmpyCd String
     * @param lineMsg NLAL2030_ASMsg
     * @return List<String>
     */
    private BigDecimal getOpenRwsQty(String glblCmpyCd, NLAL2030_ASMsg lineMsg) {

        BigDecimal createRwsQty = lineMsg.xxQty10Num_A1.getValue();

        S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getOpenRwsQty(glblCmpyCd, lineMsg.trxOrdNum_A1.getValue(), lineMsg.trxLineNum_A1.getValue());

        if (ssmResult.isCodeNotFound()) {
            return createRwsQty;
        } else if (!ZYPCommonFunc.hasValue((BigDecimal) ssmResult.getResultObject())){
            return createRwsQty;
        } else {
            return createRwsQty.subtract((BigDecimal) ssmResult.getResultObject());
        }
    }

    // START 2022/10/26 M.Kikushima [QC#60413,ADD]
    /**
     * validatePO for cancel
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @return boolean
     */
    public static boolean validateForCancel(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        NLAL2030Query query = NLAL2030Query.getInstance();
        boolean chkWrn = false;
        int firstErrIdx = -1;

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsg.B.no(i).xxChkBox_B1.getValue())) {

                BigDecimal APInvCnt = query.countValidAPInvoiceForCancel(bizMsg.glblCmpyCd.getValue(), glblMsg.B.no(i).trxOrdNum_B1.getValue());

                if (BigDecimal.ZERO.compareTo(APInvCnt) < 0) {
                    chkWrn = true;
                    if(firstErrIdx == -1) {
                        firstErrIdx = i;
                    }

                    glblMsg.B.no(i).xxChkBox_B1.setErrorInfo(2, NLAL2030Constant.NLAM1358W, new String[] {"Cansel"});

                } else {
                    chkWrn = false;
                }

            }

        }

        if (chkWrn) {
            bizMsg.xxPageShowFromNum_B.setValue(firstErrIdx);
            NLAL2030CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);
        }

        return chkWrn;
    }
    // END 2022/10/26 M.Kikushima [QC#60413,ADD]

    // START 2022/12/13 R.Azucena [QC#60905, ADD]
    /**
     * pdltCheck
     * @param bizMsg NLAL2020CMsg
     * @param lineMsg NLAL2030_BSMsg
     * @return boolean
     */
    private static boolean pdltCheck(NLAL2030CMsg bizMsg, NLAL2030_BSMsg lineMsg) {
        WHTMsg whTMsg = new WHTMsg();
        whTMsg.setSQLID("007");
        whTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        whTMsg.setConditionValue("whCd01", S21StringUtil.concatStrings(lineMsg.rtlWhCd_B1.getValue(), lineMsg.rtlSwhCd_B1.getValue()));
        WHTMsgArray whTMsgList = (WHTMsgArray) EZDTBLAccessor.findByCondition(whTMsg);

        if (whTMsgList != null && 0 < whTMsgList.getValidCount()) {
            String whSysTpCd = whTMsgList.no(0).whSysTpCd.getValue();

            if (WH_SYS_TP.WMS.equals(whSysTpCd)) {
                // Check PO Cancel(PDLT) Record
                S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getWmsPdltRecord(bizMsg.glblCmpyCd.getValue(), lineMsg.rwsRefNum_B1.getValue());

                if (!ssmResult.isCodeNormal()) {
                    // NO PDLT Record
                    return false;
                }
            }
        }

        return true;
    }
    // END 2022/12/13 R.Azucena [QC#60905, ADD]
}
