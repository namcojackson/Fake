/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL2020;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL2020.common.NLBL2020CommonLogic;
import business.blap.NLBL2020.constant.NLBL2020Constant;
import business.db.EXPT_DOC_CA_WRKTMsg;
import business.db.EXPT_DOC_WRKTMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_HDRTMsgArray;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_PRO_NUM_LISTTMsg;
import business.parts.NLZC206001PMsg;
import business.parts.NLZC207001PMsg;
import business.parts.NLZC210001PMsg;
import business.parts.NLZC210002PMsg;
import business.parts.NLZC400001PMsg;
import business.parts.NLZC401001PMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_DefaultBillShipListPMsg;

import com.canon.cusa.s21.api.NLZ.NLZC206001.NLZC206001;
import com.canon.cusa.s21.api.NLZ.NLZC207001.NLZC207001;
import com.canon.cusa.s21.api.NLZ.NLZC210001.NLZC210001;
import com.canon.cusa.s21.api.NLZ.NLZC400001.NLZC400001;
import com.canon.cusa.s21.api.NLZ.NLZC401001.NLZC401001;
import com.canon.cusa.s21.api.NLZ.NLZC401001.constant.NLZC401001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC019001.NLXSOReport;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 05/04/2016   CSAI            Y.Imazu         Update          QC#7334
 * 06/30/2016   CSAI            K.Lee           Update          Configuration Change
 * 12/07/2016   CITS            Y.Fujii         Update          R360
 * 07/24/2017   CITS            Y.Iwasaki       Update          QC#20030
 * 02/19/2018   CITS            T.Tokutomi      Update          QC#18367
 * 03/07/2018   CITS            T.Tokutomi      Update          QC#21913
 * 03/30/2018   CITS            K.Fukumura      Update          QC#25023
 * 06/19/2018   CITS            Y.Iwasaki       Update          QC#21717
 * 07/02/2018   CITS            Y.Iwasaki       Update          QC#27042
 * 11/22/2018   CITS            K.Ogino         Update          QC#29325
 * 07/24/2019   Fujitsu         T.Ogura         Update          QC#51444
 * 01/31/2020   Fujitsu         T.Ogura         Update          QC#55570
 * 04/08/2020   CITS            K.Fukumura      Update          QC#56289
 * 04/04/2022   CITS            R.Azucena       Update          QC#59802
 * 08/08/2022   CITS            R.Azucena       Update          QC#60416
 *</pre>
 */
public class NLBL2020BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL2020Scrn00_Save_Search".equals(screenAplID)) {
                doProcess_SaveSearch((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Delete_Search".equals(screenAplID)) {
                doProcess_DeleteSearch((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Cancel".equals(screenAplID)) {
                doProcess_Cancel((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_SO_Close".equals(screenAplID)) {
                doProcess_SoClose((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Ship".equals(screenAplID)) {
                doProcess_Ship((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_Submit((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_Print".equals(screenAplID)) {
                doProcess_Print((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else if ("NLBL2020Scrn00_CustomDocPrint".equals(screenAplID)) {
                doProcess_CustomDocPrint((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // Error or Warning Table ctrl
            if ("E".equals(cMsg.getMessageKind()) || "W".equals(cMsg.getMessageKind())) {

                NLBL2020CommonLogic.viewErrorRecordPage((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_SaveSearch
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_SaveSearch(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        NLBL2020CommonLogic.setCommonValues(cMsg, sMsg, getGlobalCompanyCode(), getContextUserInfo().getUserId());

        if (NLBL2020CommonLogic.isExistSaveSearchName(cMsg)) {

            cMsg.srchOptNm_H1.setErrorInfo(1, NLBL2020Constant.NLZM2273E, new String[] {"Save", cMsg.srchOptNm_H1.getValue() });
            cMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
            return;
        }

        NLBL2020CommonLogic.callNszc0330forSaveSearchOption(cMsg, sMsg);
    }

    /**
     * doProcess_DeleteSearch
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_DeleteSearch(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        NLBL2020CommonLogic.setCommonValues(cMsg, sMsg, getGlobalCompanyCode(), getContextUserInfo().getUserId());
        NLBL2020CommonLogic.callNszc0330forDeleteSearchOption(cMsg, sMsg);
        NLBL2020CommonLogic.setPulldownSaveSearch(cMsg);
    }

    /**
     * doProcess_Cancel
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_Cancel(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        NLBL2020CommonLogic.saveCurrentPageToSMsgSoList(cMsg, sMsg);

        NLBL2020CommonLogic.setCommonValues(cMsg, sMsg, getGlobalCompanyCode(), getContextUserInfo().getUserId());

        boolean errFlg = false;
        boolean warnFlg = false;
        ArrayList<NLZC210001PMsg> apiPMsgList = new ArrayList<NLZC210001PMsg>();
        ArrayList<String> soNumList = new ArrayList<String>();
        ArrayList<String> notPiSoNumList = new ArrayList<String>();
        ArrayList<String> piErrSoNumList = new ArrayList<String>();

        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);

        // Role Function
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL2020Constant.BUSINESS_ID);

        // SO Cancel must be selected headers
        if (!NLBL2020CommonLogic.chkSoHdrCheck(cMsg, sMsg)) {
            errFlg = true;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLBL2020_ASMsg sMsgALine = sMsg.A.no(i);

            // check so selected lines
            if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxExstFlg_A2.getValue())) {

                continue;
            }

            // Timestamp check
            if (!NLBL2020CommonLogic.chkTimeStampForShpgOrd(cMsg.glblCmpyCd.getValue(), sMsgALine.soNum_HI.getValue(), sMsgALine.ezUpTime_SO.getValue(), sMsgALine.ezUpTimeZone_SO.getValue())
                    || !NLBL2020CommonLogic.chkTimeStampForShpgOrdDtl(cMsg.glblCmpyCd.getValue(), sMsgALine.soNum_HI.getValue(), sMsgALine.soSlpNum_HI.getValue(), sMsgALine.ezUpTime_SD.getValue(), sMsgALine.ezUpTimeZone_SD.getValue())) {

                sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLBM0009E);
                errFlg = true;
                continue;
            }

            // WH Permission check
            if (!NLBL2020CommonLogic.isWarehousePermission(cMsg, sMsgALine.shipFromRtlWhCd_AH.getValue(), functionList)) {
                sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2313E);
                errFlg = true;
                continue;
            }

            // PI Activity Status Error
            if (piErrSoNumList.contains(sMsgALine.soNum_HI.getValue())) {

                sMsgALine.xxExstFlg_A1.setErrorInfo(1, NLBL2020Constant.NLBM1347E);
                errFlg = true;
                continue;
            }

            // PI Activity Check
            if (!notPiSoNumList.contains(sMsgALine.soNum_HI.getValue())) {

                if (!NLBL2020CommonLogic.chkPiActivity(cMsg.glblCmpyCd.getValue(), sMsgALine.soNum_HI.getValue())) {

                    piErrSoNumList.add(sMsgALine.soNum_HI.getValue());
                    sMsgALine.xxExstFlg_A1.setErrorInfo(1, NLBL2020Constant.NLBM1347E);
                    errFlg = true;
                    continue;
                }

                notPiSoNumList.add(sMsgALine.soNum_HI.getValue());
            }

            // QC#17922 Add.
            // Line Cancel Check
            if (!NLBL2020CommonLogic.chkLineCancel(sMsgALine, sMsg)) {

                errFlg = true;
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxWrnSkipFlg_A1.getValue())) {
                warnFlg = true;
                sMsgALine.xxExstFlg_A2.setErrorInfo(2, NLBL2020Constant.NLAM1342W);
                ZYPEZDItemValueSetter.setValue(sMsgALine.xxWrnSkipFlg_A1, ZYPConstant.FLG_ON_Y);
                continue;
            }

            // Order Cancel
            if (ZYPCommonFunc.hasValue(sMsgALine.xxExstFlg_A1)) {

                if (soNumList.isEmpty() || !soNumList.contains(sMsgALine.soNum_HI.getValue())) {

                    apiPMsgList.add(NLBL2020CommonLogic.setSoCancelParam(cMsg.glblCmpyCd.getValue(), sMsgALine, true));
                    soNumList.add(sMsgALine.soNum_A1.getValue());
                }

            // Line Cancel
            } else {

                if (soNumList.isEmpty() || !soNumList.contains(sMsgALine.soNum_HI.getValue())) {
                    apiPMsgList.add(NLBL2020CommonLogic.setSoCancelParam(cMsg.glblCmpyCd.getValue(), sMsgALine, false));
                }
            }
        }

        if (errFlg) {

            cMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
            return;
        } else if (warnFlg) {

            cMsg.setMessageInfo(NLBL2020Constant.NLAM1342W);
            return;
        }

        // execute SO Confirmation from S21 DC : 1SO - 1Execute
        for (NLZC210001PMsg pMsg : apiPMsgList) {
            NLZC210001 api = new NLZC210001();
            api.execute(pMsg, null, ONBATCH_TYPE.ONLINE);

            if (S21ApiUtil.isXxMsgId(pMsg)) {
                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                    String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);
                    if (msgId != null && msgId.endsWith("E")) {
                        return;
                    }
                }
            }
        }

        // IB Allocation off
        if (!NLBL2020CommonLogic.callMachMstrApiAllocOff(soNumList, cMsg)) {

            return;
        }

    }

    /**
     * doProcess_SoClose
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_SoClose(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        NLBL2020CommonLogic.setCommonValues(cMsg, sMsg, getGlobalCompanyCode(), getContextUserInfo().getUserId());

        boolean errFlg = false;
        HashMap<String, NLZC210001PMsg> soApiParamList = new HashMap<String, NLZC210001PMsg>();
        HashMap<String, NLZC207001PMsg> rwsCompApiPList = new HashMap<String, NLZC207001PMsg>();
        ArrayList<String> soNumList = new ArrayList<String>();
        ArrayList<String> notPiSoNumList = new ArrayList<String>();
        ArrayList<String> piErrSoNumList = new ArrayList<String>();

        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);

        // Role Function
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL2020Constant.BUSINESS_ID);

        // SO Close must be selected details
        if (!NLBL2020CommonLogic.chkSoLineCheck(cMsg, sMsg)) {
            errFlg = true;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLBL2020_ASMsg sMsgALine = sMsg.A.no(i);

            // Check selected lines
            if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxExstFlg_A1.getValue())) {

                continue;
            }

            // Timestamp Check
            if (!NLBL2020CommonLogic.chkTimeStampForShpgOrd(cMsg.glblCmpyCd.getValue(), sMsgALine.soNum_HI.getValue(), sMsgALine.ezUpTime_SO.getValue(), sMsgALine.ezUpTimeZone_SO.getValue())) {

                sMsgALine.xxExstFlg_A1.setErrorInfo(1, NLBL2020Constant.NLBM0009E);
                errFlg = true;
                continue;
            }

            // WH Permission check
            if (!NLBL2020CommonLogic.isWarehousePermission(cMsg, sMsgALine.shipFromRtlWhCd_AH.getValue(), functionList)) {
                sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2313E);
                errFlg = true;
                continue;
            }

            // PI Activity Status Error
            if (piErrSoNumList.contains(sMsgALine.soNum_HI.getValue())) {

                sMsgALine.xxExstFlg_A1.setErrorInfo(1, NLBL2020Constant.NLBM1347E);
                errFlg = true;
                continue;
            }

            // PI Activity Check
            if (!notPiSoNumList.contains(sMsgALine.soNum_HI.getValue())) {

                if (!NLBL2020CommonLogic.chkPiActivity(cMsg.glblCmpyCd.getValue(), sMsgALine.soNum_HI.getValue())) {

                    piErrSoNumList.add(sMsgALine.soNum_HI.getValue());
                    sMsgALine.xxExstFlg_A1.setErrorInfo(1, NLBL2020Constant.NLBM1347E);
                    errFlg = true;
                    continue;
                }

                notPiSoNumList.add(sMsgALine.soNum_HI.getValue());
            }

            if (!NLBL2020CommonLogic.chkSoClose(sMsgALine, sMsg)) {

                errFlg = true;
                continue;
            }

            // set API
            if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxExstFlg_C1.getValue())) {

                if (!soApiParamList.containsKey(sMsgALine.soNum_HI.getValue())) {

                    soApiParamList.put(sMsgALine.soNum_HI.getValue(), NLBL2020CommonLogic.setSoCloseParam(cMsg.glblCmpyCd.getValue(), sMsgALine));
                }

            } else {

                if (!rwsCompApiPList.containsKey(sMsgALine.soNum_HI.getValue())) {

                    RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
                    rwsHdrTMsg.setSQLID("013");
                    rwsHdrTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
                    rwsHdrTMsg.setConditionValue("trxOrdNum01", sMsgALine.soNum_HI.getValue());
                    RWS_HDRTMsgArray updateRwsHdr = (RWS_HDRTMsgArray) EZDTBLAccessor.findByCondition(rwsHdrTMsg);

                    for (int j = 0; j < updateRwsHdr.length(); j++) {

                        RWS_HDRTMsg targetRws = updateRwsHdr.no(j);

                        // Lock
                        targetRws = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(targetRws);

                        if (EZDTBLAccessor.RTNCD_NORMAL.equals(targetRws.getReturnCode())) {

                            Map<String, Object> rwsHdrMap = new HashMap<String, Object>();
                            rwsHdrMap.put("RWS_NUM", targetRws.rwsNum.getValue());
                            rwsHdrMap.put("SCE_ORD_TP_CD", sMsgALine.sceOrdTpCd_AH.getValue());
                            rwsHdrMap.put("RTL_WH_CD", sMsgALine.shipFromRtlWhCd_AH.getValue());
                            rwsHdrMap.put("RWS_REF_NUM", targetRws.rwsRefNum.getValue());
                            rwsCompApiPList.put(sMsgALine.soNum_HI.getValue(), NLBL2020CommonLogic.setRwsCompApiParam(cMsg, rwsHdrMap));

                        } else {

                            sMsgALine.xxExstFlg_A1.setErrorInfo(1, NLBL2020Constant.NLBM0009E);
                            errFlg = true;
                        }
                    }

                }
            }

            if (soNumList.isEmpty() || !soNumList.contains(sMsgALine.soNum_HI.getValue())) {

                soNumList.add(sMsgALine.soNum_HI.getValue());
            }
        }

        if (errFlg) {

            cMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
            return;
        }

        if (!soApiParamList.isEmpty()) {

            ArrayList<NLZC210001PMsg> pMsgList = new ArrayList<NLZC210001PMsg>();

            for (NLZC210001PMsg pMsg : soApiParamList.values()) {

                pMsgList.add(pMsg);
            }

            NLZC210001 api = new NLZC210001();
            api.execute(pMsgList, null, ONBATCH_TYPE.ONLINE);

            if (!NLBL2020CommonLogic.chkShipConfApiResult(cMsg, pMsgList, null)) {

                return;
            }
        }

        if (!rwsCompApiPList.isEmpty()) {

            ArrayList<NLZC207001PMsg> pMsgList = new ArrayList<NLZC207001PMsg>();

            for (NLZC207001PMsg pMsg : rwsCompApiPList.values()) {

                pMsgList.add(pMsg);
            }

            NLZC207001 api = new NLZC207001();
            api.execute(pMsgList, ONBATCH_TYPE.ONLINE);

            for (NLZC207001PMsg pMsg : pMsgList) {

                if (!NLBL2020CommonLogic.chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

                    return;
                }
            }
        }

        // IB Allocation off
        if (!NLBL2020CommonLogic.callMachMstrApiAllocOff(soNumList, cMsg)) {

            return;
        }
    }

    /**
     * doProcess_Ship
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_Ship(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        NLBL2020CommonLogic.saveCurrentPageToSMsgSoList(cMsg, sMsg);
        NLBL2020CommonLogic.setCommonValues(cMsg, sMsg, getGlobalCompanyCode(), getContextUserInfo().getUserId());

        boolean errFlg = false;
        boolean warningFlg = false;

        // For PI Check
        ArrayList<String> notPiSoNumList = new ArrayList<String>();
        ArrayList<String> piErrSoNumList = new ArrayList<String>();

        ArrayList<NLBL2020_ASMsg> sMsgALineListShip = new ArrayList<NLBL2020_ASMsg>();
        ArrayList<NLBL2020_ASMsg> sMsgALineListRWS = new ArrayList<NLBL2020_ASMsg>();

        // Role Function
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL2020Constant.BUSINESS_ID);

        // SO Ship can be selected eather headers/details
        if (!NLBL2020CommonLogic.chkSoLineCheck(cMsg, sMsg)) {
            errFlg = true;
        }

        // START 2022/04/04 R.Azucena [QC#59802, ADD]
        // START 2022/08/08 R.Azucena [QC#60416, DEL]
        // Map<String, BigDecimal> totalShipSingleQtyMap = getTotalShipSingleQtyMap(sMsg);
        // END 2022/08/08 R.Azucena [QC#60416, DEL]
        // END 2022/04/04 R.Azucena [QC#59802, ADD]

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLBL2020_ASMsg sMsgALine = sMsg.A.no(i);

            // Warning Skip Check
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxWrnSkipFlg_A1, chkShipWrnSkip(cMsg, sMsgALine));

            // Check selected lines
            if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxExstFlg_A2.getValue())) {

                // Values for warning check are clear
                sMsgALine.proNum_AB.clear();
                sMsgALine.carrCd_AB.clear();
                sMsgALine.carrNm_AB.clear();
                sMsgALine.serNum_AB.clear();
                continue;
            }

            // Timestamp check
            if (!NLBL2020CommonLogic.chkTimeStampForShpgOrd(cMsg.glblCmpyCd.getValue(), sMsgALine.soNum_HI.getValue(), sMsgALine.ezUpTime_SO.getValue(), sMsgALine.ezUpTimeZone_SO.getValue())
                    || !NLBL2020CommonLogic.chkTimeStampForShpgOrdDtl(cMsg.glblCmpyCd.getValue(), sMsgALine.soNum_HI.getValue(), sMsgALine.soSlpNum_HI.getValue(), sMsgALine.ezUpTime_SD.getValue(), sMsgALine.ezUpTimeZone_SD.getValue())) {

                sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLBM0009E);
                errFlg = true;
                continue;
            }

            // WH Permission check
            if (!NLBL2020CommonLogic.isWarehousePermission(cMsg, sMsgALine.shipFromRtlWhCd_AH.getValue(), functionList)) {
                sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2313E);
                errFlg = true;
                continue;
            }

            // PI Activity Status Error
            if (piErrSoNumList.contains(sMsgALine.soNum_HI.getValue())) {

                sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLBM1347E);
                errFlg = true;
                continue;
            }

            // PI Activity Check
            if (!notPiSoNumList.contains(sMsgALine.soNum_HI.getValue())) {

                if (!NLBL2020CommonLogic.chkPiActivity(cMsg.glblCmpyCd.getValue(), sMsgALine.soNum_HI.getValue())) {

                    piErrSoNumList.add(sMsgALine.soNum_HI.getValue());
                    sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLBM1347E);
                    errFlg = true;
                    continue;
                }

                notPiSoNumList.add(sMsgALine.soNum_HI.getValue());
            }

            // Error Check
            // START 2022/04/04 R.Azucena [QC#59802, MOD]
            // START 2022/08/08 R.Azucena [QC#60416, MOD]
            if (!NLBL2020CommonLogic.chkShipRecord(sMsgALine, sMsg)) {
            // String mapKey = getMapKey(sMsgALine);
            // if (!NLBL2020CommonLogic.chkShipRecord(sMsgALine, sMsg, totalShipSingleQtyMap.get(mapKey))) {
            // START 2022/08/08 R.Azucena [QC#60416, MOD]
            // END 2022/04/04 R.Azucena [QC#59802, MOD]

                errFlg = true;
                continue;
            }

            // Warning Check
            if (!ZYPCommonFunc.hasValue(sMsgALine.xxWrnSkipFlg_A1) || !ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxWrnSkipFlg_A1.getValue())) {

                if (NLBL2020CommonLogic.chkShipWarning(cMsg.glblCmpyCd.getValue(), sMsgALine)) {

                    ZYPEZDItemValueSetter.setValue(sMsgALine.xxWrnSkipFlg_A1, ZYPConstant.FLG_ON_Y);
                    warningFlg = true;
                    continue;
                }
            }

            if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxExstFlg_C1.getValue())) {

                sMsgALineListShip.add(sMsgALine);

            } else {

                sMsgALineListRWS.add(sMsgALine);
            }
        }

        if (errFlg) {

            cMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
            return;

        } else if (warningFlg) {

            ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
            cMsg.setMessageInfo(NLBL2020Constant.NATM0001W);
            return;

        } else {

            if (!executeShipConfirm(cMsg, sMsgALineListShip)) {

                return;
            }

            if (!executePutAwayRwsComp(cMsg, sMsgALineListRWS)) {

                return;
            }

            // QC#21913 Add method.
            if (!insertShipOrdProNumList(cMsg, sMsg)) {

                return;
            }
        }

        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
    }

    /**
     * chkShipWrnSkip
     * @param cMsg NLBL2020CMsg
     * @param sMsgALine NLBL2020_ASMsg
     * @return String Y : Skip, N : Not Skip
     */
    private String chkShipWrnSkip(NLBL2020CMsg cMsg, NLBL2020_ASMsg sMsgALine) {

        if (!ZYPCommonFunc.hasValue(sMsgALine.xxExstFlg_A2) || !ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxExstFlg_A2.getValue())) {

            return ZYPConstant.FLG_OFF_N;

        } else if (!ZYPCommonFunc.hasValue(cMsg.xxWrnSkipFlg) || !ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue())) {

            return ZYPConstant.FLG_OFF_N;

        } else if (!ZYPCommonFunc.hasValue(sMsgALine.xxWrnSkipFlg_A1) || !ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxWrnSkipFlg_A1.getValue())) {

            return ZYPConstant.FLG_OFF_N;

        } else if (!NLBL2020CommonLogic.isSameVal(sMsgALine.proNum_A1.getValue(), sMsgALine.proNum_AB.getValue())
                || !NLBL2020CommonLogic.isSameVal(sMsgALine.serNum_A1.getValue(), sMsgALine.serNum_AB.getValue())
                || !NLBL2020CommonLogic.isSameVal(sMsgALine.carrCd_A1.getValue(), sMsgALine.carrCd_AB.getValue())
                || !NLBL2020CommonLogic.isSameVal(sMsgALine.carrNm_A1.getValue(), sMsgALine.carrNm_AB.getValue())) {

            return ZYPConstant.FLG_OFF_N;
        }

        return ZYPConstant.FLG_ON_Y;
    }

    /**
     * executeShipConfirm
     * @param cMsg NLBL2020CMsg
     * @param sMsgALineList ArrayList<NLBL2020_ASMsg>
     * @return boolean true : OK, false : NG
     */
    private boolean executeShipConfirm(NLBL2020CMsg cMsg, ArrayList<NLBL2020_ASMsg> sMsgALineList) {

        ArrayList<String> soNumList = new ArrayList<String>();

        for (NLBL2020_ASMsg sMsgALineHdr : sMsgALineList) {

            if (!soNumList.isEmpty() && soNumList.contains(sMsgALineHdr.soNum_HI.getValue())) {

                continue;
            }

            soNumList.add(sMsgALineHdr.soNum_HI.getValue());

            int count = 0;
            int recordSumQty = 0;
            int shpgOrdDtlCnt = 0;
            int shpgOrdDtlSumQty = 0;

            // Get Shipping Order Information
            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
            ssmParam.put("soNum", sMsgALineHdr.soNum_HI.getValue());

            S21SsmEZDResult result = NLBL2020Query.getInstance().getSoSum(ssmParam);

            if (result.isCodeNormal()) {

                Map<String, Object> rsMap = (Map<String, Object>) result.getResultObject();

                // Create Record
                shpgOrdDtlCnt = ((BigDecimal) rsMap.get("COUNT")).intValue();
                shpgOrdDtlSumQty = ((BigDecimal) rsMap.get("SUM_ORD_QTY")).intValue();

            } else {

                cMsg.setMessageInfo(NLBL2020Constant.ZZZM9006E, new String[]{"Shipping Order Data"});
                return false;
            }

            ArrayList<NLZC210001PMsg> soConfPList = new ArrayList<NLZC210001PMsg>();
            ArrayList<NLZC210002PMsg> soApiSerPList = new ArrayList<NLZC210002PMsg>();

            // set param in same SO
            for (NLBL2020_ASMsg sMsgALineDtl : sMsgALineList) {

                if (sMsgALineHdr.soNum_HI.getValue().equals(sMsgALineDtl.soNum_HI.getValue())) {

                    // so serial param set
                    if (ZYPCommonFunc.hasValue(sMsgALineDtl.serNum_A1) || ZYPCommonFunc.hasValue(sMsgALineDtl.serNum_AH.no(0))) {

                        soApiSerPList.addAll(NLBL2020CommonLogic.setShipSerialParam(cMsg.glblCmpyCd.getValue(), sMsgALineDtl));
                    }

                    // so conf param set
                    soConfPList.add(NLBL2020CommonLogic.setShipParam(cMsg.glblCmpyCd.getValue(), sMsgALineDtl));

                    count++;
                    recordSumQty = recordSumQty + sMsgALineDtl.shipQty_A1.getValue().intValue();
                }
            }

            // if fully SO is shipped, set proc status code is SHIP
            if (shpgOrdDtlCnt == count && shpgOrdDtlSumQty == recordSumQty) {

                for (int i = 0; i < soConfPList.size(); i++) {

                    ZYPEZDItemValueSetter.setValue(soConfPList.get(i).soProcStsCd, SO_PROC_STS.SHIP);
                }
            }

            // Call API
            NLZC210001 soConfApi = new NLZC210001();
            soConfApi.execute(soConfPList, soApiSerPList, ONBATCH_TYPE.ONLINE);

            if (!NLBL2020CommonLogic.chkShipConfApiResult(cMsg, soConfPList, soApiSerPList)) {

                return false;
            }
        }

        return true;
    }

    /**
     * executePutAwayRwsComp
     * @param cMsg NLBL2020CMsg
     * @param sMsgALineList ArrayList<NLBL2020_ASMsg>
     * @return boolean true : OK, false : NG
     */
    private boolean executePutAwayRwsComp(NLBL2020CMsg cMsg, ArrayList<NLBL2020_ASMsg> sMsgALineList) {

        ArrayList<String> rwsNumList = new ArrayList<String>();
        ArrayList<Map<String, Object>> rwsHdrMapList = new ArrayList<Map<String, Object>>();

        // QC#20030
        // Inspect SO Details.
        // If record is "Remove Config Change" and "Main Machine", this record should be processed at last.
        // When "Main Machine" is removed from config, all corresponding components are forcibly removed as well.
        for (int n = sMsgALineList.size() - 1; n >= 0; --n) {
            NLBL2020_ASMsg sMsgALine = sMsgALineList.get(n);
            if (SCE_ORD_TP.CONFIG_CHANGE.equals(sMsgALine.sceOrdTpCd_AH.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsgALine.rmvConfigFlg_A1.getValue())) {
                // Confirm if the machine is "Main Machine".
                HashMap<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
                ssmParam.put("serNum", sMsgALine.serNum_A1.getValue());
                ssmParam.put("mdseCd", sMsgALine.mdseCd_A1.getValue());
                ssmParam.put("svcMachMstrStsCdTerm", SVC_MACH_MSTR_STS.TERMINATED);
                ssmParam.put("svcMachMstrStsCdDup", SVC_MACH_MSTR_STS.DUPLICATE);

                S21SsmEZDResult result = NLBL2020Query.getInstance().getSvcMachMstr(ssmParam);
                if (result.isCodeNormal()) {
                    List<Map<String, Object>> machList = (List<Map<String, Object>>) result.getResultObject();
                    for (Map<String, Object> mach : machList) {
                        if (ZYPConstant.FLG_ON_Y.equals(mach.get("MAIN_MACH_FLG"))) {
                            // This record is "Main Machine".
                            // Move the record to the end.
                            sMsgALineList.remove(n);
                            sMsgALineList.add(sMsgALine);
                            break;
                        }
                    }
                }
            }
        }

        for (NLBL2020_ASMsg sMsgALine : sMsgALineList) {

            if (SCE_ORD_TP.ITEM_CHANGE.equals(sMsgALine.sceOrdTpCd_AH.getValue()) || SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sMsgALine.sceOrdTpCd_AH.getValue())) {

                if (BigDecimal.ZERO.compareTo(sMsgALine.shipQty_A1.getValue()) < 0) {

                    continue;
                }
            }

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
            ssmParam.put("soNum", sMsgALine.soNum_HI.getValue());
            ssmParam.put("soSlpNum", sMsgALine.soSlpNum_HI.getValue());

            S21SsmEZDResult result = NLBL2020Query.getInstance().getRwsNumListBySo(ssmParam);

            if (result.isCodeNormal()) {
                List<String> rwsTargetList = (List<String>) result.getResultObject();

                for (int i = 0; i < rwsTargetList.size(); i++) {

                    RWS_HDRTMsg inMsg = new RWS_HDRTMsg();
                    ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(inMsg.rwsNum, rwsTargetList.get(i));
                    RWS_HDRTMsg targetRws = (RWS_HDRTMsg) EZDTBLAccessor.findByKey(inMsg); 

                    if (rwsNumList.isEmpty() || !rwsNumList.contains(targetRws.rwsNum.getValue())) {

                        Map<String, Object> rwsHdrMap = new HashMap<String, Object>();
                        rwsHdrMap.put("RWS_NUM", targetRws.rwsNum.getValue());
                        rwsHdrMap.put("SCE_ORD_TP_CD", sMsgALine.sceOrdTpCd_AH.getValue());
                        rwsHdrMap.put("RTL_WH_CD", sMsgALine.shipFromRtlWhCd_AH.getValue());
                        rwsHdrMap.put("RWS_REF_NUM", targetRws.rwsRefNum.getValue());

                        rwsHdrMapList.add(rwsHdrMap);
                        rwsNumList.add(targetRws.rwsNum.getValue());
                    }

                    ArrayList<RWS_DTLTMsg> rwsDtlArray = NLBL2020CommonLogic.getRwsDtlArray(cMsg.glblCmpyCd.getValue(), targetRws.rwsNum.getValue(), sMsgALine.soSlpNum_HI.getValue());

                    // Lock check
                    boolean lockErrFlg = false;

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(targetRws.getReturnCode())) {

                        lockErrFlg = true;
                    }

                    for (RWS_DTLTMsg rwsDtl : rwsDtlArray) {

                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtl.getReturnCode())) {

                            lockErrFlg = true;
                        }
                    }

                    if (lockErrFlg) {

                        sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLBM0009E);
                        cMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
                        return false;
                    }

                    // Call Rws Put Away API
                    NLZC206001PMsg putAwayPMsg = NLBL2020CommonLogic.setRwsPutAwayConfApiParam(cMsg.glblCmpyCd.getValue(), targetRws, rwsDtlArray, sMsgALine);

                    NLZC206001 putAwayApi = new NLZC206001();
                    putAwayApi.execute(putAwayPMsg, ONBATCH_TYPE.ONLINE);

                    if (!NLBL2020CommonLogic.chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(putAwayPMsg))) {

                        return false;
                    }
                }
            }
        }

        // RWS Completion
        for (Map<String, Object> rwsHdrMap : rwsHdrMapList) {

            NLZC207001PMsg rwsCompMsg = NLBL2020CommonLogic.setRwsCompApiParam(cMsg, rwsHdrMap);

            NLZC207001 rwsCompApi = new NLZC207001();
            rwsCompApi.execute(rwsCompMsg, ONBATCH_TYPE.ONLINE);

            if (!NLBL2020CommonLogic.chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(rwsCompMsg))) {

                return false;
            }
        }

        ArrayList<String> soNumList = new ArrayList<String>();

        for (NLBL2020_ASMsg sMsgALine : sMsgALineList) {

            if (soNumList.isEmpty() || !soNumList.contains(sMsgALine.soNum_HI.getValue())) {
                soNumList.add(sMsgALine.soNum_HI.getValue());
            }
        }

        // Allocation off
        if (!NLBL2020CommonLogic.callMachMstrApiAllocOff(soNumList, cMsg)) {

            return false;
        }

        return true;
    }

    /**
     * doProcess_Submit (Pickup)
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_Submit(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        NLBL2020CommonLogic.setCommonValues(cMsg, sMsg, getGlobalCompanyCode(), getContextUserInfo().getUserId());
        NLBL2020CommonLogic.saveCurrentPageToSMsgPickList(cMsg, sMsg);

        ArrayList<NLBL2020_BSMsg> sMsgBLineList = new ArrayList<NLBL2020_BSMsg>();

        boolean hasErrFlg = false;
        boolean hasWrngFlg = false;
        int serialPickked = 0;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            NLBL2020_BSMsg sMsgBLine = sMsg.B.no(i);

            // Edit check And get Record
            if (!NLBL2020CommonLogic.isSameVal(sMsgBLine.pickConfQty_B1.getValue(), sMsgBLine.pickConfQty_HD.getValue()) //
                    || !NLBL2020CommonLogic.isSameVal(sMsgBLine.serNum_B1.getValue(), sMsgBLine.serNum_HD.getValue())) {

                if (ZYPCommonFunc.hasValue(sMsgBLine.pickConfQty_B1) //
                        && ZYPCommonFunc.hasValue(sMsgBLine.pickConfQty_DB) //
                        && ZYPCommonFunc.hasValue(sMsgBLine.shipQty_B1)) {

                    BigDecimal totQty = BigDecimal.ZERO;
                    if (BigDecimal.ZERO.compareTo(sMsgBLine.pickConfQty_B1.getValue()) > 0) {
                        totQty = sMsgBLine.pickConfQty_B1.getValue().add(sMsgBLine.pickCpltQty_B1.getValue().subtract(sMsgBLine.shipQty_B1.getValue()));
                    } else {
                        totQty = sMsgBLine.pickConfQty_B1.getValue().add(sMsgBLine.pickCpltQty_B1.getValue());
                    }
                    if (totQty.compareTo(sMsgBLine.shpgQty_B1.getValue()) > 0 || BigDecimal.ZERO.compareTo(totQty) > 0) {
                        sMsgBLine.pickConfQty_B1.setErrorInfo(1, NLBL2020Constant.NLZM2316E, new String[] {"Pick Qty", "Order Qty" });
                        hasErrFlg = true;
                        continue;
                    }
                }

                sMsgBLineList.add(sMsg.B.no(i));

            } else {

                if (ZYPCommonFunc.hasValue(sMsgBLine.pickConfQty_B1) //
                        && ZYPCommonFunc.hasValue(sMsgBLine.pickConfQty_DB) //
                        && ZYPCommonFunc.hasValue(sMsgBLine.shipQty_B1)) {

                    BigDecimal totQty = sMsgBLine.pickConfQty_B1.getValue().add(sMsgBLine.pickCpltQty_B1.getValue().add(sMsgBLine.shipQty_B1.getValue()));
                    if (totQty.compareTo(sMsgBLine.shpgQty_B1.getValue()) > 0 ) {
                        sMsgBLine.pickConfQty_B1.setErrorInfo(1, NLBL2020Constant.NLZM2316E, new String[] {"Pick Qty", "Order Qty" });
                        hasErrFlg = true;
                        continue;
                    }
                }

                if (NLBL2020CommonLogic.isSameVal(sMsgBLine.serNumTakeFlg_B1.getValue(), ZYPConstant.FLG_ON_Y) //
                        && sMsgBLine.pickConfQty_HD.getValueInt() != 0) {
                    serialPickked++;
                }
                continue;
            }

            // Timestamp check
            if (!NLBL2020CommonLogic.chkTimeStampForShpgOrd(cMsg.glblCmpyCd.getValue(), sMsgBLine.soNum_HD.getValue(), sMsgBLine.ezUpTime_H1.getValue(), sMsgBLine.ezUpTimeZone_H1.getValue())
                    || !NLBL2020CommonLogic.chkTimeStampForShpgOrdDtl(cMsg.glblCmpyCd.getValue(), sMsgBLine.soNum_HD.getValue(), sMsgBLine.soSlpNum_HD.getValue(), sMsgBLine.ezUpTime_H2.getValue(), sMsgBLine.ezUpTimeZone_H2.getValue())) {

                sMsgBLine.pickConfQty_B1.setErrorInfo(1, NLBL2020Constant.NLBM0009E);
                hasErrFlg = true;
                continue;
            }

            // Error Check
            if (!NLBL2020CommonLogic.chkSubmitRecord(sMsgBLine, sMsg)) {

                hasErrFlg = true;
                continue;
            }

            // Warning Check
            if (!ZYPCommonFunc.hasValue(cMsg.xxWrnSkipFlg) || !ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue())
                    || !NLBL2020CommonLogic.isSameVal(sMsgBLine.serNum_B1.getValue(), sMsgBLine.serNum_BB.getValue())) {

                ZYPEZDItemValueSetter.setValue(sMsgBLine.serNum_BB, sMsgBLine.serNum_B1.getValue());

                if (ZYPCommonFunc.hasValue(sMsgBLine.xxMsgId_B1)) {

                    sMsgBLine.serNum_B1.setErrorInfo(2, sMsgBLine.xxMsgId_B1.getValue());
                    hasWrngFlg = true;
                    continue;
                }
            }
        }

        if (sMsgBLineList.isEmpty()) {

            cMsg.setMessageInfo(NLBL2020Constant.NLCM0123E);
            return;
        }

        if (hasErrFlg) {

            cMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
            return;

        } else if (hasWrngFlg) {

            ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
            cMsg.setMessageInfo(NLBL2020Constant.NATM0001W);
            return;

        } else {

            // Set param for PKT Line Update API
            NLZC400001PMsg pktLineUpdPMsg = new NLZC400001PMsg();

            int pktCnt = 0;
            HashSet<String> pickSoSlpNumSet = new HashSet<String>();

            ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.slsDt, cMsg.slsDt.getValue());

            for (NLBL2020_BSMsg sMsgBLine : sMsgBLineList) {

                String pickSoSlpNum = ZYPCommonFunc.concatString(sMsgBLine.soNum_HD.getValue(), ":", sMsgBLine.soSlpNum_HD.getValue());

                if (!pickSoSlpNumSet.contains(pickSoSlpNum)) {

                    BigDecimal linePickConfQty = BigDecimal.valueOf(serialPickked);
                    BigDecimal allPickConfQty = BigDecimal.ZERO;

                    for (int j = 0; j < sMsgBLineList.size(); j++) {

                        BigDecimal minPickConfQty = BigDecimal.ZERO;

                        if (NLBL2020CommonLogic.isSameVal(sMsgBLine.soNum_HD.getValue(), sMsgBLineList.get(j).soNum_HD.getValue())
                                && NLBL2020CommonLogic.isSameVal(sMsgBLine.soSlpNum_HD.getValue(), sMsgBLineList.get(j).soSlpNum_HD.getValue())) {

                            if (BigDecimal.ZERO.compareTo(sMsgBLineList.get(j).pickConfQty_B1.getValue()) > 0) {
                                minPickConfQty = sMsgBLineList.get(j).pickConfQty_B1.getValue();
                                linePickConfQty = linePickConfQty.add(minPickConfQty);
                            } else {
                                BigDecimal pickConfQty = sMsgBLineList.get(j).pickConfQty_B1.getValue();
                                linePickConfQty = linePickConfQty.add(pickConfQty).add(minPickConfQty);
                            }

                            allPickConfQty = allPickConfQty.add(sMsgBLineList.get(j).pickCpltQty_B1.getValue());
                        }
                    }

                    if (linePickConfQty.intValue() != 0) {

                        ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.pktStsUpdateInfo.no(pktCnt).soNum, sMsgBLine.soNum_HD);
                        ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.pktStsUpdateInfo.no(pktCnt).soSlpNum, sMsgBLine.soSlpNum_HD);
                        ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.pktStsUpdateInfo.no(pktCnt).dsSoLineStsCd, DS_SO_LINE_STS.PICK_CONFIRMED);
                        ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.pktStsUpdateInfo.no(pktCnt).pktStsTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(NLBL2020Constant.FORMAT_TIMESTAMP_TIME)));
                        ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.pktStsUpdateInfo.no(pktCnt).pktStsQty, linePickConfQty);
                        pktCnt++;
                    }

                    pickSoSlpNumSet.add(pickSoSlpNum);
                }
            }

            // Call PKT Line Update API
            if (pktCnt > 0) {

                NLZC400001 pktApi = new NLZC400001();
                pktLineUpdPMsg.pktStsUpdateInfo.setValidCount(pktCnt);

                pktApi.execute(pktLineUpdPMsg, ONBATCH_TYPE.ONLINE);

                if (!NLBL2020CommonLogic.chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(pktLineUpdPMsg))) {

                    return;
                }
            }

            // Set param for SO Serial Update API
            NLZC401001PMsg soSerUpMsg = new NLZC401001PMsg();

            int soSerUpCnt = 0;

            ZYPEZDItemValueSetter.setValue(soSerUpMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(soSerUpMsg.slsDt, cMsg.slsDt.getValue());

            // API Msg Setup
            for (NLBL2020_BSMsg sMsgBLine : sMsgBLineList) {

                if (ZYPConstant.FLG_ON_Y.equals(sMsgBLine.serNumTakeFlg_B1.getValue())) {

                    BigDecimal pickConfQty = sMsgBLine.pickConfQty_B1.getValue();
                    BigDecimal bfrPickConfQty = sMsgBLine.pickConfQty_HD.getValue();

                    // Serial Changed
                    if (!NLBL2020CommonLogic.isSameVal(sMsgBLine.serNum_B1.getValue(), sMsgBLine.serNum_HD.getValue())) {

                        // New serial is picked
                        if (ZYPCommonFunc.hasValue(sMsgBLine.serNum_B1.getValue()) && pickConfQty.compareTo(BigDecimal.ZERO) >= 0) {

                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).xxProcTpCd, NLZC401001Constant.MODE_PICKUP_SERIAL);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).mdseCd, sMsgBLine.mdseCd_B1);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).serNum, sMsgBLine.serNum_B1);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).soNum, sMsgBLine.soNum_HD);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).soSlpNum, sMsgBLine.soSlpNum_HD);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxHdrNum, sMsgBLine.trxHdrNum_HT);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxLineNum, sMsgBLine.trxLineNum_HT);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxLineSubNum, sMsgBLine.trxLineSubNum_HT);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxSrcTpCd, sMsgBLine.trxSrcTpCd_HD);
                            soSerUpCnt++;
                        }

                        // Already picked serial is canceled.
                        if (ZYPCommonFunc.hasValue(sMsgBLine.serNum_HD.getValue()) && bfrPickConfQty.compareTo(BigDecimal.ZERO) > 0) {

                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).xxProcTpCd, NLZC401001Constant.MODE_PICKUP_CANCEL);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).mdseCd, sMsgBLine.mdseCd_B1);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).serNum, sMsgBLine.serNum_HD);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).soNum, sMsgBLine.soNum_HD);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).soSlpNum, sMsgBLine.soSlpNum_HD);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxHdrNum, sMsgBLine.trxHdrNum_HT);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxLineNum, sMsgBLine.trxLineNum_HT);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxLineSubNum, sMsgBLine.trxLineSubNum_HT);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxSrcTpCd, sMsgBLine.trxSrcTpCd_HD);
                            soSerUpCnt++;
                        }

                    } else {

                        // Assigned serial is picked.
                        if (pickConfQty.compareTo(BigDecimal.ZERO) > 0 && bfrPickConfQty.compareTo(BigDecimal.ONE) < 0) {

                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).xxProcTpCd, NLZC401001Constant.MODE_PICKUP_SERIAL);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).mdseCd, sMsgBLine.mdseCd_B1);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).serNum, sMsgBLine.serNum_B1);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).soNum, sMsgBLine.soNum_HD);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).soSlpNum, sMsgBLine.soSlpNum_HD);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxHdrNum, sMsgBLine.trxHdrNum_HT);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxLineNum, sMsgBLine.trxLineNum_HT);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxLineSubNum, sMsgBLine.trxLineSubNum_HT);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxSrcTpCd, sMsgBLine.trxSrcTpCd_HD);
                            soSerUpCnt++;
                        }

                        // Already picked serial is canceled.
                        if (bfrPickConfQty.compareTo(BigDecimal.ZERO) > 0 && pickConfQty.compareTo(BigDecimal.ZERO) < 0 && ZYPCommonFunc.hasValue(sMsgBLine.serNum_HD)) {

                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).xxProcTpCd, NLZC401001Constant.MODE_PICKUP_CANCEL);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).mdseCd, sMsgBLine.mdseCd_B1);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).serNum, sMsgBLine.serNum_B1);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).soNum, sMsgBLine.soNum_HD);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).soSlpNum, sMsgBLine.soSlpNum_HD);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxHdrNum, sMsgBLine.trxHdrNum_HT);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxLineNum, sMsgBLine.trxLineNum_HT);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxLineSubNum, sMsgBLine.trxLineSubNum_HT);
                            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxSrcTpCd, sMsgBLine.trxSrcTpCd_HD);
                            soSerUpCnt++;
                        }
                    }
                }
            }

            if (soSerUpCnt > 0) {

                // Call SO Serial Update API
                NLZC401001 soSerUpApi = new NLZC401001();
                soSerUpMsg.serInfo.setValidCount(soSerUpCnt);

                soSerUpApi.execute(soSerUpMsg, ONBATCH_TYPE.ONLINE);

                if (S21ApiUtil.isXxMsgId(soSerUpMsg)) {

                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(soSerUpMsg);

                    for (S21ApiMessage msg : msgList) {

                        String msgId = msg.getXxMsgid();
                        String[] msgPrms = msg.getXxMsgPrmArray();

                        if (ZYPCommonFunc.hasValue(msgId)) {

                            cMsg.setMessageInfo(msgId, msgPrms);

                            if (msgId.endsWith("E")) {

                                return;
                            }
                        }
                    }
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
        // Set Tab Control
        ZYPEZDItemValueSetter.setValue(sMsg.xxModeCd_TB, ZYPConstant.FLG_ON_Y);
    }

    /**
     * doProcess_Print
     * @param bizMsg NLBL2020CMsg
     * @param bizMsg NLBL2020SMsg
     */
    private void doProcess_Print(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        // QC#25023 2018/03/30 Start
        NLBL2020CommonLogic.saveCurrentPageToSMsgSoList(cMsg, sMsg);
        boolean nonTarget = true;
        // QC#25023 2018/03/30 End
        String glblCmpyCd = getGlobalCompanyCode();
        String usrId = getContextUserInfo().getUserId();

        // QC#25023 2018/03/30 Start
        // String soNum = null;
        // QC#25023 2018/03/30 End
        
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLBL2020_ASMsg record = sMsg.A.no(i);

            if (!ZYPCommonFunc.hasValue(record.xxExstFlg_A1) || !ZYPCommonFunc.hasValue(record.soNum_A1)) {
                continue;
            }
            
            // QC#25023 2018/03/30 Start
            // soNum = record.soNum_A1.getValue();
            nonTarget = false;
            // QC#25023 2018/03/30 End
            break;
        }
        // QC#25023 2018/03/30 Start
        //if (!ZYPCommonFunc.hasValue(soNum)) {
        //    for (int i = 0; i < sMsg.A.getValidCount(); i++) {
        //        NLBL2020_ASMsg record = sMsg.A.no(i);
        //        record.xxExstFlg_A1.setErrorInfo(2, NLBL2020Constant.NLBM1360W);
        //    }
        //    cMsg.setMessageInfo(NLBL2020Constant.NATM0001W, null);
        //    return;
        //}
        if (nonTarget) {
            // NLBM0017E:It has not been selected.  Please select.  
            for(int i = 0; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).xxExstFlg_A1.setErrorInfo(2, NLBL2020Constant.NLBM1360W);
            }
            cMsg.setMessageInfo(NLBL2020Constant.NATM0001W);
            return;
        }
        // QC#25023 2018/03/30 End

        NLXSOReport report = new NLXSOReport();
        List<String> xxMsgIdList = new ArrayList<String>();

        try {
            // Create Work table
            // QC#25023 2018/03/30 Multi Select
            List<String> printedSoList = new ArrayList<String>();
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                String soNum = sMsg.A.no(i).soNum_A1.getValue();
                if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxExstFlg_A1.getValue()) && 
                    ZYPCommonFunc.hasValue(soNum)) {
                    
                    if (!printedSoList.contains(soNum)) {
                        printedSoList.add(soNum);

                        SHPG_ORDTMsg soInfo = new SHPG_ORDTMsg();
                        ZYPEZDItemValueSetter.setValue(soInfo.soNum, soNum);
                        ZYPEZDItemValueSetter.setValue(soInfo.glblCmpyCd, glblCmpyCd);
                        soInfo = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdate(soInfo);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(soInfo.getReturnCode())) {
                            cMsg.setMessageInfo(NLBL2020Constant.NLBM0009E);
                            return;
                        }
                        report = new NLXSOReport();
                        BigDecimal reportPk =  report.setSoRptWrk(glblCmpyCd, usrId, ZYPDateUtil.getCurrentSystemTime(NLBL2020Constant.FORMAT_TIMESTAMP), soNum, xxMsgIdList);
                        if (xxMsgIdList.size() != 0) {
                            cMsg.setMessageInfo(NLBL2020Constant.NLBM1257E, new String[] {"Shipping Order Print API" });
                            return;
                        }
                        // QC#25023 2018/03/30 Start
                        //ZYPEZDItemValueSetter.setValue(cMsg.soNum_PR, soNum);
                        //ZYPEZDItemValueSetter.setValue(cMsg.shpgOrdRptPrintRqstSq_PR, reportPk);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shpgOrdRptPrintRqstSq_A1, reportPk);
                        // QC#25023 2018/03/30 End
                        ZYPEZDItemValueSetter.setValue(soInfo.soPrtFlg, ZYPConstant.FLG_ON_Y);
                        EZDTBLAccessor.update(soInfo);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(soInfo.getReturnCode())) {
                            cMsg.setMessageInfo(NLBL2020Constant.NLBM0009E);
                            return;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            cMsg.setMessageInfo(NLBL2020Constant.NLBM1257E, new String[] {"Shipping Order Print API" });
            return;
        }
    }

    /**
     * doProcess_CustomDocPrint
     * QC#18367 Add method.
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    private void doProcess_CustomDocPrint(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        NLBL2020CommonLogic.saveCurrentPageToSMsgSoList(cMsg, sMsg);

        // target data check
        boolean nonTarget = true;
        boolean hasError = false;
        String varCharUsDomesticStCd = ZYPCodeDataUtil.getVarCharConstValue(NLBL2020Constant.VAR_CONST_US_DOMESTIC_ST_CD, getGlobalCompanyCode());                             // 2019/07/24 T.Ogura [QC#51444,ADD]
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxExstFlg_A1.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(i).soNum_A1)) {
                nonTarget = false;

                if (CTRY.UNITED_STATES_OF_AMERICA.equals(sMsg.A.no(i).shipToCtryCd_A1.getValue())) {
                    if (ZYPCommonFunc.hasValue(varCharUsDomesticStCd) && Arrays.asList(varCharUsDomesticStCd.split(",")).contains(sMsg.A.no(i).shipToStCd_A1.getValue())) {    // 2019/07/24 T.Ogura [QC#51444,ADD]
                        // NLBM1366E : Export Pring process can not
                        // include Ship to US record.
                        sMsg.A.no(i).xxExstFlg_A1.setErrorInfo(1, "NLBM1366E");
                        hasError = true;
                    }
                }

                // START 01/31/2020 T.Ogura [QC#55570,DEL]
//                if (!(SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(sMsg.A.no(i).sceOrdTpCd_AH.getValue()) || SCE_ORD_TP.DIRECT_SALES.equals(sMsg.A.no(i).sceOrdTpCd_AH.getValue()))) {
//                    // NLBM1367E : [@] is not subject to processing.
//                    // [@] and [@] are subject to processing.
//                    sMsg.A.no(i).xxExstFlg_A1.setErrorInfo(1, "NLBM1367E" //
//                            , new String[] {sMsg.A.no(i).sceOrdTpNm_A1.getValue(), "Return to Vendor", "Customer Order" });
//                    hasError = true;
//                }
                // END   01/31/2020 T.Ogura [QC#55570,DEL]
            }
        }

        // QC#25023 2018/03/30 Start
//        if (nonTarget) {
//            // NLBM0017E:It has not been selected.  Please select.  
//            cMsg.setMessageInfo("NLBM0017E");
//            return;
//        }
        if (nonTarget) {
            // NLBM0017E:It has not been selected.  Please select.  
            for(int i = 0; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).xxExstFlg_A1.setErrorInfo(2, NLBL2020Constant.NLBM1360W);
            }
            cMsg.setMessageInfo(NLBL2020Constant.NATM0001W);
            return;
        }
        // QC#25023 2018/03/30 End
        
        if (hasError) {
            NLBL2020CommonLogic.viewErrorRecordPage(cMsg, sMsg);
            cMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
            return;
        }

        // Create Work table
        String glblCmpyCd = getGlobalCompanyCode();
        // Confirm if SO# is already in request
        List<String> printedSoList = new ArrayList<String>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String soNum = sMsg.A.no(i).soNum_A1.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxExstFlg_A1.getValue()) && ZYPCommonFunc.hasValue(soNum) && !printedSoList.contains(soNum)) {
                if (CTRY.CANADA.equals(sMsg.A.no(i).shipToCtryCd_A1.getValue())) {
                    BigDecimal printPk = createWorkDataOfExportCanada(soNum);

                    if (printPk.signum() == -1) {
                        // Error
                        cMsg.setMessageInfo(NLBL2020Constant.NLBM1257E, new String[] {"Insert Worktable of Export report for Canada" });
                        return;
                    } else {
                        // Success
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).exptDocPrintRqstSq_A1, printPk);
                    }
                } else {
                    BigDecimal printPk = createWorkDataOfExportNonCanada(soNum);

                    if (printPk.signum() == -1) {
                        // Error
                        cMsg.setMessageInfo(NLBL2020Constant.NLBM1257E, new String[] {"Insert Worktable of Export report for non-Canada" });
                        return;
                    } else {
                        // Success
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).exptDocPrintRqstSq_A1, printPk);
                    }
                }

                // Update Shpg_Order_print_flg.
                SHPG_ORDTMsg soInfo = new SHPG_ORDTMsg();
                ZYPEZDItemValueSetter.setValue(soInfo.soNum, soNum);
                ZYPEZDItemValueSetter.setValue(soInfo.glblCmpyCd, glblCmpyCd);
                soInfo = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdate(soInfo);
                if (soInfo == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(soInfo.getReturnCode())) {
                    cMsg.setMessageInfo(NLBL2020Constant.NLBM0009E);
                    return;
                }
                ZYPEZDItemValueSetter.setValue(soInfo.soPrtFlg, ZYPConstant.FLG_ON_Y);
                EZDTBLAccessor.update(soInfo);

                printedSoList.add(soNum);
            }
        }
    }

    private BigDecimal createWorkDataOfExportCanada(String soNum) {

        List<Map<String, Object>> records = getExportPdfData(soNum);

        if (records == null) {
            // Error
            return new BigDecimal(-1);
        }

        // Total
        BigDecimal totalWeightLb = BigDecimal.ZERO;
        BigDecimal totalWeightKg = BigDecimal.ZERO;
        BigDecimal totalAmt = BigDecimal.ZERO;
        HashSet<String> madeCtryList = new HashSet<String>();
        int lineCount = 0;

        for (Map<String, Object> record : records) {
            totalWeightLb = totalWeightLb.add((BigDecimal) record.get("LINE_POUND_WT"));
            totalWeightKg = totalWeightKg.add((BigDecimal) record.get("LINE_KG_WT"));
            totalAmt = totalAmt.add((BigDecimal) record.get("SP_TOT_DEAL_SLS_AMT"));
            madeCtryList.add((String) record.get("MADE_IN_CTRY_CD"));
            lineCount++;
        }

        boolean isMultipleMadeCtry = false;
        if (madeCtryList.size() > 1) {
            isMultipleMadeCtry = true;
        }

        // Const_value
        String glblCmpyCd = getGlobalCompanyCode();
        String multiMadeCtryMsg = ZYPCodeDataUtil.getVarCharConstValue("EXPT_DOC_MULTI_SEE_BELOW", glblCmpyCd);
        String numOfPkg = ZYPCodeDataUtil.getVarCharConstValue("EXPT_DOC_NUM_OF_PKG", glblCmpyCd);
        BigDecimal firstPk = BigDecimal.ZERO;

        for (Map<String, Object> record : records) {

            EXPT_DOC_CA_WRKTMsg work = new EXPT_DOC_CA_WRKTMsg();

            ZYPEZDItemValueSetter.setValue(work.glblCmpyCd, glblCmpyCd);
            // QC#29325
            ZYPEZDItemValueSetter.setValue(work.exptDocCaWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EXPT_DOC_CA_WRK_SQ));
            if(BigDecimal.ZERO.compareTo(firstPk) == 0){
                // First record. Mod QC#29325
                firstPk = work.exptDocCaWrkPk.getValue();
            }
            ZYPEZDItemValueSetter.setValue(work.soNum, (String) record.get("SO_NUM"));
            ZYPEZDItemValueSetter.setValue(work.soSlpNum, (String) record.get("SO_SLP_NUM"));
            ZYPEZDItemValueSetter.setValue(work.custIssPoVndRtrnNum, (String) record.get("CUSTOMER_PO_OR_VND_RTN_NUM"));
            String cpoDateOrVndRtnDt = (String) record.get("CPO_DATE_OR_VND_RTN_DATE");
            if (ZYPCommonFunc.hasValue(cpoDateOrVndRtnDt)) {
                String dateFormat = "yyyyMMddhhmmss";
                if (cpoDateOrVndRtnDt.length() < 14) {
                    dateFormat = "yyyyMMdd";
                }
                ZYPEZDItemValueSetter.setValue(work.vndRtrnDtlSubmtDtTxt, // 
                        ZYPDateUtil.DateFormatter(cpoDateOrVndRtnDt, dateFormat, "MM/dd/yyyy"));
            }
            ZYPEZDItemValueSetter.setValue(work.pmtTermCashDiscDescTxt, (String) record.get("PMT_TERM_CASH_DISC_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(work.srcOrdNum, (String) record.get("SRC_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(work.dplyLineNum, (String) record.get("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(work.sceOrdTpCd, (String) record.get("SCE_ORD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(work.shipFromRtlWhNm, (String) record.get("SHIP_FROM_RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(work.shipToLocNm, (String) record.get("SHIP_TO_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(work.firstLineAddr, (String) record.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(work.scdLineAddr, (String) record.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(work.thirdLineAddr, (String) record.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(work.frthLineAddr, (String) record.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(work.ctyAddr, (String) record.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(work.stCd, (String) record.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(work.postCd, (String) record.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(work.ctryCd, (String) record.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(work.telNum, (String) record.get("TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(work.mdseCd, (String) record.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(work.mdseDescShortTxt, (String) record.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(work.shpgQtyTxt, formatNumberComma((BigDecimal) record.get("SHPG_QTY")));
            ZYPEZDItemValueSetter.setValue(work.dplyPkgUomCd, (String) record.get("DPLY_PKG_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(work.dealUnitPrcAmtTxt, formatNumberCurrencyOfUsd((BigDecimal) record.get("SP_DEAL_UNIT_PRC_AMT")));
            ZYPEZDItemValueSetter.setValue(work.totDealSlsAmtTxt, formatNumberCurrencyOfUsd((BigDecimal) record.get("SP_TOT_DEAL_SLS_AMT")));
            String rddDt = (String) record.get("RDD_DT");
            if(ZYPCommonFunc.hasValue(rddDt)){
                ZYPEZDItemValueSetter.setValue(work.rddDtTxt, //
                        ZYPDateUtil.DateFormatter(rddDt, "yyyyMMdd", "yyyy/MM/dd"));
            }
            ZYPEZDItemValueSetter.setValue(work.shpgSvcLvlDescTxt, (String) record.get("SHPG_SVC_LVL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(work.soCratTs, (String) record.get("SO_CRAT_TS"));
            ZYPEZDItemValueSetter.setValue(work.carrNm, (String) record.get("CARR_NM"));
            ZYPEZDItemValueSetter.setValue(work.carrCd, (String) record.get("CARR_CD"));
            ZYPEZDItemValueSetter.setValue(work.shipToCustAcctCd, (String) record.get("SHIP_TO_CUST_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(work.shipToLocCd, (String) record.get("SHIP_TO_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(work.shipSoCustLineLocNm_01, (String) record.get("SHIP_SO_CUST_LINE_LOC_NM_01"));
            ZYPEZDItemValueSetter.setValue(work.shipSoCustLineLocNm_02, (String) record.get("SHIP_SO_CUST_LINE_LOC_NM_02"));
            ZYPEZDItemValueSetter.setValue(work.shipToCtacPsnNm, (String) record.get("SHIP_TO_CTAC_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(work.shipSoCustLineAddr_01, (String) record.get("SHIP_SO_CUST_LINE_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(work.shipSoCustLineAddr_02, (String) record.get("SHIP_SO_CUST_LINE_ADDR_02"));
            ZYPEZDItemValueSetter.setValue(work.shipSoCustLineAddr_03, (String) record.get("SHIP_SO_CUST_LINE_ADDR_03"));
            ZYPEZDItemValueSetter.setValue(work.shipSoCustLineAddr_04, (String) record.get("SHIP_SO_CUST_LINE_ADDR_04"));
            ZYPEZDItemValueSetter.setValue(work.shipToCtyAddr, (String) record.get("SHIP_TO_CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(work.shipToStCd, (String) record.get("SHIP_TO_ST_CD"));
            ZYPEZDItemValueSetter.setValue(work.shipToPostCd, (String) record.get("SHIP_TO_POST_CD"));

            // START 01/31/2020 T.Ogura [QC#55570,MOD]
//            ZYPEZDItemValueSetter.setValue(work.billSoCustLineLocNm_01, (String) record.get("BILL_SO_CUST_LINE_LOC_NM_01"));
//            ZYPEZDItemValueSetter.setValue(work.billSoCustLineLocNm_02, (String) record.get("BILL_SO_CUST_LINE_LOC_NM_02"));
//            ZYPEZDItemValueSetter.setValue(work.billToCtacPsnNm, (String) record.get("BILL_TO_CTAC_PSN_NM"));
//            ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_01, (String) record.get("BILL_SO_CUST_LINE_ADDR_01"));
//            ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_02, (String) record.get("BILL_SO_CUST_LINE_ADDR_02"));
//            ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_03, (String) record.get("BILL_SO_CUST_LINE_ADDR_03"));
//            ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_04, (String) record.get("BILL_SO_CUST_LINE_ADDR_04"));
//            ZYPEZDItemValueSetter.setValue(work.billToCtyAddr, (String) record.get("BILL_TO_CTY_ADDR"));
//            ZYPEZDItemValueSetter.setValue(work.billToStCd, (String) record.get("BILL_TO_ST_CD"));
//            ZYPEZDItemValueSetter.setValue(work.billToPostCd, (String) record.get("BILL_TO_POST_CD"));
            Map<String, Object> techReqBillToMap = null;
            if (SCE_ORD_TP.TECH_REQUEST.equals((String) record.get("SCE_ORD_TP_CD"))) {
                techReqBillToMap = getTechReqBillToData(glblCmpyCd, record);
                if (techReqBillToMap != null) {
                    ZYPEZDItemValueSetter.setValue(work.billSoCustLineLocNm_01, (String) techReqBillToMap.get("LOC_NM"));
                    ZYPEZDItemValueSetter.setValue(work.billSoCustLineLocNm_02, (String) techReqBillToMap.get("ADDL_LOC_NM"));
                    ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_01, (String) techReqBillToMap.get("FIRST_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_02, (String) techReqBillToMap.get("SCD_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_03, (String) techReqBillToMap.get("THIRD_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_04, (String) techReqBillToMap.get("FRTH_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(work.billToCtyAddr, (String) techReqBillToMap.get("CTY_ADDR"));
                    ZYPEZDItemValueSetter.setValue(work.billToStCd, (String) techReqBillToMap.get("ST_CD"));
                    ZYPEZDItemValueSetter.setValue(work.billToPostCd, (String) techReqBillToMap.get("POST_CD"));
                }
            } else {
                ZYPEZDItemValueSetter.setValue(work.billSoCustLineLocNm_01, (String) record.get("BILL_SO_CUST_LINE_LOC_NM_01"));
                ZYPEZDItemValueSetter.setValue(work.billSoCustLineLocNm_02, (String) record.get("BILL_SO_CUST_LINE_LOC_NM_02"));
                ZYPEZDItemValueSetter.setValue(work.billToCtacPsnNm, (String) record.get("BILL_TO_CTAC_PSN_NM"));
                ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_01, (String) record.get("BILL_SO_CUST_LINE_ADDR_01"));
                ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_02, (String) record.get("BILL_SO_CUST_LINE_ADDR_02"));
                ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_03, (String) record.get("BILL_SO_CUST_LINE_ADDR_03"));
                ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_04, (String) record.get("BILL_SO_CUST_LINE_ADDR_04"));
                ZYPEZDItemValueSetter.setValue(work.billToCtyAddr, (String) record.get("BILL_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(work.billToStCd, (String) record.get("BILL_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(work.billToPostCd, (String) record.get("BILL_TO_POST_CD"));
            }
            // END   01/31/2020 T.Ogura [QC#55570,MOD]

            ZYPEZDItemValueSetter.setValue(work.shipFromLocCd, (String) record.get("SHIP_FROM_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(work.shipWtDplyTxt_01, formatNumberComma((BigDecimal) record.get("LINE_POUND_WT")));
            ZYPEZDItemValueSetter.setValue(work.shipWtDplyTxt_02, formatNumberComma((BigDecimal) record.get("LINE_KG_WT")));
            ZYPEZDItemValueSetter.setValue(work.trfCd, (String) record.get("TRF_CD"));
            ZYPEZDItemValueSetter.setValue(work.madeInCtryCd, (String) record.get("MADE_IN_CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(work.madeInCtryNm, (String) record.get("MADE_IN_CTRY_NM"));
            ZYPEZDItemValueSetter.setValue(work.serNum, (String) record.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(work.shipToCtryNm, (String) record.get("SHIP_TO_CTRY_NM"));

            // START 01/31/2020 T.Ogura [QC#55570,MOD]
//            ZYPEZDItemValueSetter.setValue(work.billToCtryNm, (String) record.get("BILL_TO_CTRY_NM"));
            if (SCE_ORD_TP.TECH_REQUEST.equals((String) record.get("SCE_ORD_TP_CD"))) {
                if (techReqBillToMap != null) {
                    ZYPEZDItemValueSetter.setValue(work.billToCtryNm, (String) techReqBillToMap.get("CTRY_NM"));
                }
            } else {
                ZYPEZDItemValueSetter.setValue(work.billToCtryNm, (String) record.get("BILL_TO_CTRY_NM"));
            }
            // END   01/31/2020 T.Ogura [QC#55570,MOD]

            ZYPEZDItemValueSetter.setValue(work.frtCondDescTxt, (String) record.get("FRT_COND_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(work.soConfDtTxt, //
                    ZYPDateUtil.DateFormatter(ZYPDateUtil.getSalesDate(glblCmpyCd), "yyyyMMdd", "yyyy/MM/dd"));
            ZYPEZDItemValueSetter.setValue(work.mnfItemCd, (String) record.get("MNF_ITEM_CD"));
            BigDecimal grossWt = (BigDecimal) record.get("LINE_GRS_WT");
            if (ZYPCommonFunc.hasValue(grossWt) && BigDecimal.ZERO.compareTo(grossWt) < 0) {
                ZYPEZDItemValueSetter.setValue(work.totShipWtPrintTxt, formatNumberComma(grossWt));
            }
            ZYPEZDItemValueSetter.setValue(work.exptDocCaLineNum, new BigDecimal(lineCount));
            ZYPEZDItemValueSetter.setValue(work.pkgNumTxt, numOfPkg);
            ZYPEZDItemValueSetter.setValue(work.dplyCmntFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(work.exptTotAmtTxt, formatNumberCurrencyOfUsd(totalAmt));
            ZYPEZDItemValueSetter.setValue(work.proNum, (String) record.get("PRO_NUM"));
            if (isMultipleMadeCtry) {
                ZYPEZDItemValueSetter.setValue(work.madeInCtryCdTxt, multiMadeCtryMsg);
            } else {
                ZYPEZDItemValueSetter.setValue(work.madeInCtryCdTxt, (String) record.get("MADE_IN_CTRY_CD"));
            }

            ZYPEZDItemValueSetter.setValue(work.exptDocCaPrintRqstSq, firstPk);

            EZDTBLAccessor.insert(work);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(work.getReturnCode())) {
                // Error
                return new BigDecimal(-1);
            }
        }
        return firstPk;
    }

    private BigDecimal createWorkDataOfExportNonCanada(String soNum) {

        List<Map<String, Object>> records = getExportPdfData(soNum);

        if (records == null) {
            // Error
            return new BigDecimal(-1);
        }

        // Total
        BigDecimal totalWeightLb = BigDecimal.ZERO;
        BigDecimal totalWeightKg = BigDecimal.ZERO;
        BigDecimal totalAmt = BigDecimal.ZERO;
        int lineCount = 0;

        for (Map<String, Object> record : records) {
            totalWeightLb = totalWeightLb.add((BigDecimal) record.get("LINE_POUND_WT"));
            totalWeightKg = totalWeightKg.add((BigDecimal) record.get("LINE_KG_WT"));
            totalAmt = totalAmt.add((BigDecimal) record.get("SP_TOT_DEAL_SLS_AMT"));
            lineCount++;
        }

        boolean isCustomsPurpose = false;
        if (totalAmt.compareTo(new BigDecimal("0.01")) <= 0) {
            isCustomsPurpose = true;
        }

        // Const_value
        String glblCmpyCd = getGlobalCompanyCode();
        String numOfPkg = ZYPCodeDataUtil.getVarCharConstValue("EXPT_DOC_NUM_OF_PKG", glblCmpyCd);
        BigDecimal firstPk = BigDecimal.ZERO;

        for (Map<String, Object> record : records) {

            EXPT_DOC_WRKTMsg work = new EXPT_DOC_WRKTMsg();

            ZYPEZDItemValueSetter.setValue(work.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(work.exptDocWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EXPT_DOC_WRK_SQ));
            if(BigDecimal.ZERO.compareTo(firstPk) == 0){
                // First record.
                firstPk = work.exptDocWrkPk.getValue();
            }
            ZYPEZDItemValueSetter.setValue(work.soNum, (String) record.get("SO_NUM"));
            ZYPEZDItemValueSetter.setValue(work.soSlpNum, (String) record.get("SO_SLP_NUM"));
            ZYPEZDItemValueSetter.setValue(work.custIssPoVndRtrnNum, (String) record.get("CUSTOMER_PO_OR_VND_RTN_NUM"));
            String cpoDateOrVndRtnDate = (String) record.get("CPO_DATE_OR_VND_RTN_DATE");
            if (ZYPCommonFunc.hasValue(cpoDateOrVndRtnDate)) {
                String dateFormat = "yyyyMMddhhmmss";
                if (cpoDateOrVndRtnDate.length() < 14) {
                    dateFormat = "yyyyMMdd";
                }
                ZYPEZDItemValueSetter.setValue(work.vndRtrnDtlSubmtDtTxt, // 
                        ZYPDateUtil.DateFormatter(cpoDateOrVndRtnDate, dateFormat, "MM/dd/yyyy"));
            }
            ZYPEZDItemValueSetter.setValue(work.pmtTermCashDiscDescTxt, (String) record.get("PMT_TERM_CASH_DISC_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(work.srcOrdNum, (String) record.get("SRC_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(work.dplyLineNum, (String) record.get("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(work.sceOrdTpCd, (String) record.get("SCE_ORD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(work.shipFromRtlWhNm, (String) record.get("SHIP_FROM_RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(work.shipToLocNm, (String) record.get("SHIP_TO_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(work.firstLineAddr, (String) record.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(work.scdLineAddr, (String) record.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(work.thirdLineAddr, (String) record.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(work.frthLineAddr, (String) record.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(work.ctyAddr, (String) record.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(work.stCd, (String) record.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(work.postCd, (String) record.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(work.ctryCd, (String) record.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(work.telNum, (String) record.get("TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(work.mdseCd, (String) record.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(work.mdseDescShortTxt, (String) record.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(work.shpgQtyTxt, formatNumberComma((BigDecimal) record.get("SHPG_QTY")));
            ZYPEZDItemValueSetter.setValue(work.dplyPkgUomCd, (String) record.get("DPLY_PKG_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(work.dealUnitPrcAmtTxt, formatNumberCurrencyOfUsd((BigDecimal) record.get("SP_DEAL_UNIT_PRC_AMT")));
            ZYPEZDItemValueSetter.setValue(work.totDealSlsAmtTxt, formatNumberCurrencyOfUsd((BigDecimal) record.get("SP_TOT_DEAL_SLS_AMT")));
            String rddDt = (String) record.get("RDD_DT");
            if (ZYPCommonFunc.hasValue(rddDt)) {
                ZYPEZDItemValueSetter.setValue(work.rddDtTxt, //
                        ZYPDateUtil.DateFormatter(rddDt, "yyyyMMdd", "MM/dd/yyyy"));
            }
            ZYPEZDItemValueSetter.setValue(work.shpgSvcLvlDescTxt, (String) record.get("SHPG_SVC_LVL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(work.soCratTs, (String) record.get("SO_CRAT_TS"));
            ZYPEZDItemValueSetter.setValue(work.carrNm, (String) record.get("CARR_NM"));
            ZYPEZDItemValueSetter.setValue(work.carrCd, (String) record.get("CARR_CD"));
            ZYPEZDItemValueSetter.setValue(work.shipToCustAcctCd, (String) record.get("SHIP_TO_CUST_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(work.shipToLocCd, (String) record.get("SHIP_TO_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(work.shipSoCustLineLocNm_01, (String) record.get("SHIP_SO_CUST_LINE_LOC_NM_01"));
            ZYPEZDItemValueSetter.setValue(work.shipSoCustLineLocNm_02, (String) record.get("SHIP_SO_CUST_LINE_LOC_NM_02"));
            ZYPEZDItemValueSetter.setValue(work.shipToCtacPsnNm, (String) record.get("SHIP_TO_CTAC_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(work.shipSoCustLineAddr_01, (String) record.get("SHIP_SO_CUST_LINE_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(work.shipSoCustLineAddr_02, (String) record.get("SHIP_SO_CUST_LINE_ADDR_02"));
            ZYPEZDItemValueSetter.setValue(work.shipSoCustLineAddr_03, (String) record.get("SHIP_SO_CUST_LINE_ADDR_03"));
            ZYPEZDItemValueSetter.setValue(work.shipSoCustLineAddr_04, (String) record.get("SHIP_SO_CUST_LINE_ADDR_04"));
            ZYPEZDItemValueSetter.setValue(work.shipToCtyAddr, (String) record.get("SHIP_TO_CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(work.shipToStCd, (String) record.get("SHIP_TO_ST_CD"));
            ZYPEZDItemValueSetter.setValue(work.shipToPostCd, (String) record.get("SHIP_TO_POST_CD"));

            // START 01/31/2020 T.Ogura [QC#55570,MOD]
//            ZYPEZDItemValueSetter.setValue(work.billSoCustLineLocNm_01, (String) record.get("BILL_SO_CUST_LINE_LOC_NM_01"));
//            ZYPEZDItemValueSetter.setValue(work.billSoCustLineLocNm_02, (String) record.get("BILL_SO_CUST_LINE_LOC_NM_02"));
//            ZYPEZDItemValueSetter.setValue(work.billToCtacPsnNm, (String) record.get("BILL_TO_CTAC_PSN_NM"));
//            ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_01, (String) record.get("BILL_SO_CUST_LINE_ADDR_01"));
//            ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_02, (String) record.get("BILL_SO_CUST_LINE_ADDR_02"));
//            ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_03, (String) record.get("BILL_SO_CUST_LINE_ADDR_03"));
//            ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_04, (String) record.get("BILL_SO_CUST_LINE_ADDR_04"));
//            ZYPEZDItemValueSetter.setValue(work.billToCtyAddr, (String) record.get("BILL_TO_CTY_ADDR"));
//            ZYPEZDItemValueSetter.setValue(work.billToStCd, (String) record.get("BILL_TO_ST_CD"));
//            ZYPEZDItemValueSetter.setValue(work.billToPostCd, (String) record.get("BILL_TO_POST_CD"));
            Map<String, Object> techReqBillToMap = null;
            if (SCE_ORD_TP.TECH_REQUEST.equals((String) record.get("SCE_ORD_TP_CD"))) {
                techReqBillToMap = getTechReqBillToData(glblCmpyCd, record);
                if (techReqBillToMap != null) {
                    ZYPEZDItemValueSetter.setValue(work.billSoCustLineLocNm_01, (String) techReqBillToMap.get("LOC_NM"));
                    ZYPEZDItemValueSetter.setValue(work.billSoCustLineLocNm_02, (String) techReqBillToMap.get("ADDL_LOC_NM"));
                    ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_01, (String) techReqBillToMap.get("FIRST_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_02, (String) techReqBillToMap.get("SCD_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_03, (String) techReqBillToMap.get("THIRD_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_04, (String) techReqBillToMap.get("FRTH_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(work.billToCtyAddr, (String) techReqBillToMap.get("CTY_ADDR"));
                    ZYPEZDItemValueSetter.setValue(work.billToStCd, (String) techReqBillToMap.get("ST_CD"));
                    ZYPEZDItemValueSetter.setValue(work.billToPostCd, (String) techReqBillToMap.get("POST_CD"));
                }
            } else {
                ZYPEZDItemValueSetter.setValue(work.billSoCustLineLocNm_01, (String) record.get("BILL_SO_CUST_LINE_LOC_NM_01"));
                ZYPEZDItemValueSetter.setValue(work.billSoCustLineLocNm_02, (String) record.get("BILL_SO_CUST_LINE_LOC_NM_02"));
                ZYPEZDItemValueSetter.setValue(work.billToCtacPsnNm, (String) record.get("BILL_TO_CTAC_PSN_NM"));
                ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_01, (String) record.get("BILL_SO_CUST_LINE_ADDR_01"));
                ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_02, (String) record.get("BILL_SO_CUST_LINE_ADDR_02"));
                ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_03, (String) record.get("BILL_SO_CUST_LINE_ADDR_03"));
                ZYPEZDItemValueSetter.setValue(work.billSoCustLineAddr_04, (String) record.get("BILL_SO_CUST_LINE_ADDR_04"));
                ZYPEZDItemValueSetter.setValue(work.billToCtyAddr, (String) record.get("BILL_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(work.billToStCd, (String) record.get("BILL_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(work.billToPostCd, (String) record.get("BILL_TO_POST_CD"));
            }
            // END   01/31/2020 T.Ogura [QC#55570,MOD]

            ZYPEZDItemValueSetter.setValue(work.shipFromLocCd, (String) record.get("SHIP_FROM_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(work.shipWtDplyTxt_01, formatNumberComma((BigDecimal) record.get("LINE_POUND_WT")));
            ZYPEZDItemValueSetter.setValue(work.shipWtDplyTxt_02, formatNumberComma((BigDecimal) record.get("LINE_KG_WT")));
            ZYPEZDItemValueSetter.setValue(work.trfCd, (String) record.get("TRF_CD"));
            ZYPEZDItemValueSetter.setValue(work.madeInCtryCd, (String) record.get("MADE_IN_CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(work.madeInCtryNm, (String) record.get("MADE_IN_CTRY_NM"));
            ZYPEZDItemValueSetter.setValue(work.serNum, (String) record.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(work.shipToCtryNm, (String) record.get("SHIP_TO_CTRY_NM"));

            // START 01/31/2020 T.Ogura [QC#55570,MOD]
//            ZYPEZDItemValueSetter.setValue(work.billToCtryNm, (String) record.get("BILL_TO_CTRY_NM"));
            if (SCE_ORD_TP.TECH_REQUEST.equals((String) record.get("SCE_ORD_TP_CD"))) {
                if (techReqBillToMap != null) {
                    ZYPEZDItemValueSetter.setValue(work.billToCtryNm, (String) techReqBillToMap.get("CTRY_NM"));
                }
            } else {
                ZYPEZDItemValueSetter.setValue(work.billToCtryNm, (String) record.get("BILL_TO_CTRY_NM"));
            }
            // END   01/31/2020 T.Ogura [QC#55570,MOD]

            ZYPEZDItemValueSetter.setValue(work.frtCondDescTxt, (String) record.get("FRT_COND_DESC_TXT"));
            String soConfDt = (String) record.get("SO_CONF_DATE");
            if(ZYPCommonFunc.hasValue(soConfDt)){
                String dateFormat = "yyyyMMddhhmmss";
                if (soConfDt.length() < 14) {
                    dateFormat = "yyyyMMdd";
                }
                ZYPEZDItemValueSetter.setValue(work.soConfDtTxt, //
                        ZYPDateUtil.DateFormatter(soConfDt, dateFormat, "MM/dd/yyyy"));
            }
            ZYPEZDItemValueSetter.setValue(work.mnfItemCd, (String) record.get("MNF_ITEM_CD"));
            BigDecimal grossWt = (BigDecimal) record.get("LINE_GRS_WT");
            if (ZYPCommonFunc.hasValue(grossWt) && BigDecimal.ZERO.compareTo(grossWt) < 0) {
                ZYPEZDItemValueSetter.setValue(work.totShipWtPrintTxt, formatNumberComma(grossWt));
            }
            ZYPEZDItemValueSetter.setValue(work.exptDocLineNum, new BigDecimal(lineCount));
            ZYPEZDItemValueSetter.setValue(work.pkgNumTxt, numOfPkg);
            if (isCustomsPurpose) {
                ZYPEZDItemValueSetter.setValue(work.dplyCmntFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(work.dplyCmntFlg, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(work.exptTotAmtTxt, formatNumberCurrencyOfUsd(totalAmt));
            ZYPEZDItemValueSetter.setValue(work.proNum, (String) record.get("PRO_NUM"));

            ZYPEZDItemValueSetter.setValue(work.exptDocPrintRqstSq, firstPk);

            EZDTBLAccessor.insert(work);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(work.getReturnCode())) {
                // Error
                return new BigDecimal(-1);
            }
        }
        return firstPk;
    }

    // START 01/31/2020 T.Ogura [QC#55570,ADD]
    /**
     * getTechReqBillToData
     * @param glblCmpyCd String
     * @param record Map<String, Object>
     * @return Map<String, Object>
     */
    private Map<String, Object> getTechReqBillToData(String glblCmpyCd, Map<String, Object> record) {

        Map<String, Object> map = null;

        S21SsmEZDResult sellToCustResult = NLBL2020Query.getInstance().getSellToCustCd(glblCmpyCd, (String) record.get("SHIP_TO_LOC_CD"));

        if (sellToCustResult.isCodeNormal()) {
            NMZC610001PMsg pMsg = new NMZC610001PMsg();
            pMsg.glblCmpyCd.setValue(glblCmpyCd);
            pMsg.xxModeCd.setValue("04");
            pMsg.dsAcctNum_I1.setValue(sellToCustResult.getResultObject().toString());
            NMZC610001 nMZC610001 = new NMZC610001();
            nMZC610001.execute(pMsg, ONBATCH_TYPE.ONLINE);

            for (int i = pMsg.DefaultBillShipList.getValidCount() - 1; i >= 0; i--) {
                NMZC610001_DefaultBillShipListPMsg dpMsg = pMsg.DefaultBillShipList.no(i);
                S21SsmEZDResult ssmResult = NLBL2020Query.getInstance().getBillToLocation(glblCmpyCd, dpMsg.billToCustCd.getValue());
                if (ssmResult.isCodeNormal()) {
                    List<Map<String, Object>> list = (List) ssmResult.getResultObject();
                    if (list != null && 0 < list.size()) {
                        map = list.get(0);
                        break;
                    }
                }
            }
        }

        return map;
    }
    // END   01/31/2020 T.Ogura [QC#55570,ADD]

    private List<Map<String, Object>> getExportPdfData(String soNum) {

        Map<String, String> ssmParam = new HashMap<String, String>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("soNum", soNum);
        // START 01/31/2020 T.Ogura [QC#55570,DEL]
//        ssmParam.put("ReturnToVendor", SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC);
//        ssmParam.put("DirectSales", SCE_ORD_TP.DIRECT_SALES);
        // END   01/31/2020 T.Ogura [QC#55570,DEL]
        ssmParam.put("shipTo", SO_CUST_DATA_TP.SHIP_TO);
        ssmParam.put("billTo", SO_CUST_DATA_TP.BILL_TO);
        // 1 lb = 0.453592 kg
        ssmParam.put("poundToKg", "0.453592");
        ssmParam.put("scaleFactor", "3");
        ssmParam.put("regexNum", "[0-9]+$");
        // QC#56289 Start 2020/04/08
        ssmParam.put("sceOrdTp_TR", SCE_ORD_TP.TECH_REQUEST);
        // QC#56289 End
        S21SsmEZDResult result = NLBL2020Query.getInstance().getExportPdfData(ssmParam);

        if (result.isCodeNormal()) {
            return (List<Map<String, Object>>) result.getResultObject();
        } else {
            return null;
        }
    }
    
    private String formatNumberCurrencyOfUsd(BigDecimal amt) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return nf.format(amt.doubleValue()).replace("$", "");
    }

    private String formatNumberComma(BigDecimal num) {
        NumberFormat nf = NumberFormat.getInstance();
        return nf.format(num.doubleValue());
    }

    /**
     * insertShipOrdProNumList
     * QC#21913 Add method
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     * @return true:success / false:error
     */
    private boolean insertShipOrdProNumList(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg){

        ArrayList<String> executeSoNum = new ArrayList<String>();
        HashMap<String, ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg>> insertList = new HashMap<String, ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg>>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL2020_ASMsg aSMsgLine = sMsg.A.no(i);

            if (ZYPConstant.FLG_ON_Y.equals(aSMsgLine.xxExstFlg_A2.getValue())) {
                String soNum = aSMsgLine.soNum_HI.getValue();
                String trxHdrNum = aSMsgLine.trxHdrNum_AH.getValue();
                String sceOrdTpCd = aSMsgLine.sceOrdTpCd_AH.getValue();

                ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg> list = new ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg>();
                if (insertList.containsKey(soNum)) {
                    list = insertList.get(soNum);
                } else {
                    insertList.put(soNum, list);
                }

                if (!executeSoNum.contains(soNum)) {
                    for (int j = 0; j < sMsg.T.getValidCount(); j++) {
                        if (soNum.equals(sMsg.T.no(j).soNum.getValue())) {
                            SHPG_ORD_PRO_NUM_LISTTMsg sopnAdditional = new SHPG_ORD_PRO_NUM_LISTTMsg();

                            ZYPEZDItemValueSetter.setValue(sopnAdditional.trxHdrNum, soNum);
                            ZYPEZDItemValueSetter.setValue(sopnAdditional.proNum, sMsg.T.no(j).proNum);
                            ZYPEZDItemValueSetter.setValue(sopnAdditional.sceOrdTpCd, sceOrdTpCd);
                            ZYPEZDItemValueSetter.setValue(sopnAdditional.inbdOtbdCd, INBD_OTBD.OUTBOUND);
                            ZYPEZDItemValueSetter.setValue(sopnAdditional.proCratDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddhhmmss"));
                            ZYPEZDItemValueSetter.setValue(sopnAdditional.proSendFlg, ZYPConstant.FLG_ON_Y);

                            list.add(sopnAdditional);

                            // After data registration, it becomes unnecessary.
                            sMsg.T.no(j).clear();
                        }
                    }
                    executeSoNum.add(soNum);
                }

                SHPG_ORD_PRO_NUM_LISTTMsg sopn = new SHPG_ORD_PRO_NUM_LISTTMsg();

                ZYPEZDItemValueSetter.setValue(sopn.trxHdrNum, soNum);
                ZYPEZDItemValueSetter.setValue(sopn.trxLineNum, aSMsgLine.soSlpNum_A1);
                ZYPEZDItemValueSetter.setValue(sopn.proNum, aSMsgLine.proNum_A1);
                ZYPEZDItemValueSetter.setValue(sopn.sceOrdTpCd, sceOrdTpCd);
                ZYPEZDItemValueSetter.setValue(sopn.inbdOtbdCd, INBD_OTBD.OUTBOUND);
                ZYPEZDItemValueSetter.setValue(sopn.proCratDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddhhmmss"));
                ZYPEZDItemValueSetter.setValue(sopn.proSendFlg, ZYPConstant.FLG_ON_Y);

                list.add(sopn);
            }
        }

        // sort
        for (Map.Entry<String, ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg>> entry : insertList.entrySet()) {
            Collections.sort(entry.getValue(), new NLBL2020Constant.Comp("proNum"));
        }

        // insert
        for (Map.Entry<String, ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg>> entry : insertList.entrySet()) {
            ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg> list = entry.getValue();

            String mstrProNum = null;
            for (SHPG_ORD_PRO_NUM_LISTTMsg tMsg : list) {
                if (mstrProNum == null) {
                    mstrProNum = tMsg.proNum.getValue();
                }

                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.shpgOrdProNumListPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SHPG_ORD_PRO_NUM_LIST_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.mstrProNum, mstrProNum);

                EZDTBLAccessor.insert(tMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NLBL2020Constant.NLBM1257E, new String[] {"Insert Shipping Order Pro Number List." });
                    return false;
                }
            }
        }

        return true;
    }

    // START 2022/04/04 R.Azucena [QC#59802, ADD]
    // START 2022/08/08 R.Azucena [QC#60416, DEL]
    /**
     * getTotalShipSingleQtyMap
     * @param sMsg NLBL2020SMsg
     * @return Map<String, BigDecimal>
     */
    // private Map<String, BigDecimal> getTotalShipSingleQtyMap(NLBL2020SMsg sMsg) {
    //     Map<String, BigDecimal> totalShipSingleQtyMap = new HashMap<String, BigDecimal>();

    //     for (int i = 0; i < sMsg.A.getValidCount(); i++) {
    //         NLBL2020_ASMsg sMsgALine = sMsg.A.no(i);

            // Selected and Single Item
    //         if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxExstFlg_A2.getValue()) && !ZYPCommonFunc.hasValue(sMsgALine.serNum_A1)) {
    //             String key = getMapKey(sMsgALine);
    //             totalShipSingleQtyMap.put(key, convertNullToZero(totalShipSingleQtyMap.get(key)).add(sMsgALine.shipQty_A1.getValue()));
    //         }
    //     }

    //     return totalShipSingleQtyMap;
    // }

    /**
     * convertNullToZero
     * @param val BigDecimal
     * @return BigDecimal
     */
    // private static BigDecimal convertNullToZero(BigDecimal val) {
    //     if (val == null) {
    //         return BigDecimal.ZERO;
    //     }

    //     return val;
    // }

    /**
     * getMapKey
     * @param sMsgALine NLBL2020_ASMsg
     * @return String
     */
    // private static String getMapKey(NLBL2020_ASMsg sMsgALine) {
    //     return S21StringUtil.concatStrings(sMsgALine.mdseCd_A1.getValue(),
    //             ":", sMsgALine.fromStkStsCd_A1.getValue(),
    //             ":", sMsgALine.shipFromRtlWhCd_AH.getValue(),
    //             ":", sMsgALine.shipFromRtlSwhCd_A1.getValue(),
    //             ":", convertNullToZero(sMsgALine.pickSvcConfigMstrPk_A1.getValue()).toString());
    // }
    // END 2022/08/08 R.Azucena [QC#60416, DEL]
    // END 2022/04/04 R.Azucena [QC#59802, ADD]
}
