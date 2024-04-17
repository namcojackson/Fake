/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3070;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDItemAttribute;
import parts.common.EZDPMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLBL3070.common.NLBL3070CommonLogic;
import business.blap.NLBL3070.constant.NLBL3070Constant;
import business.db.BOLTMsg;
import business.db.CAL_RELNTMsg;
import business.db.CARR_RSNTMsg;
import business.db.DS_ASSET_MSTRTMsg;
import business.db.DS_ASSET_MSTRTMsgArray;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsgArray;
import business.db.SHPG_ORD_PRO_NUM_LISTTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC210001PMsg;
import business.parts.NLZC210002PMsg;
import business.parts.NLZC402001PMsg;
import business.parts.NSZC043001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NLZ.NLZC210001.NLZC210001;
import com.canon.cusa.s21.api.NLZ.NLZC402001.NLZC402001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC042001.NLXC042001SerialInfo;
import com.canon.cusa.s21.common.NLX.NLXC042001.NLXC042001SvcMachMstrCheck;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.POD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.POD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_COORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TEMP_SCHD_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 * 02/05/2016   CSAI            Y.Imazu         Update          QC#4165
 * 05/03/2016   CSAI            Y.Imazu         Update          QC#7334
 * 07/11/2016   CSAI            Y.Imazu         Update          QC#10917
 * 07/19/2016   CSAI            Y.Imazu         Update          QC#10232
 * 08/31/2016   CSAI            Y.Imazu         Update          QC#9845
 * 11/21/2016   CITS            T.Tokutomi      Update          QC#15145
 * 06/19/2017   CITS            M.Naito         Update          QC#18823
 * 06/20/2017   CITS            R.Shimamoto     Update          QC#18272
 * 07/07/2017   CITS            Y.Imazu         Update          QC#19730
 * 09/07/2017   CITS            S.Katsuma       Update          Sol#032(QC#13117)
 * 09/07/2017   CITS            K.Ogino         Update          QC#20978
 * 12/19/2017   CITS            K.Ogino         Update          QC#23174
 * 12/28/2017   CITS            T.Hakodate      Update          QC#18460(SOL#087)
 * 01/09/2018   CITS            M.Naito         Update          QC#18889
 * 02/21/2018   CITS            T.Tokutomi      Update          QC#18725(Sol#310)
 * 2018/02/23   CITS            K.Ogino         Update          QC#24123,QC#20043
 * 03/07/2018   CITS            T.Tokutomi      Update          QC#21913
 * 07/02/2018   CITS            T.Tokutomi      Update          QC#23726
 * 10/03/2018   CITS            M.Naito         Update          QC#28539
 * 04/23/2019   Hitachi         K.Kitachi       Update          QC#31245
 * 09/26/2019   CITS            K.Ogino         Update          QC#53743
 * 02/04/2020   CITS            M.Furugoori     Update          QC#50121
 * 05/31/2021   CITS            A.Marte         Update          QC#58786
 * 04/04/2022   CITS            R.Azucena       Update          QC#59802
 * 04/25/2022   CITS            R.Azucena       Update          QC#59802-1
 * 05/13/2022   CITS            A.Cullano       Update          QC#59975
 * 08/08/2022   CITS            R.Azucena       Update          QC#60416
 * 12/09/2022   Hitachi         T.Kuroda        Update          QC#60810
 *</pre>
 */
public class NLBL3070BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3070Scrn00_CMN_Submit".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_CMN_Submit((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_Release_SO".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Release_SO((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_Send_Rqst".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Send_Rqst((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_SO_Cancel".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_SO_Cancel((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_Ship".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Ship((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_SO_Close".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_SO_Close((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_Delivery_Conf".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Delivery_Conf((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_SaveSearch".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_SaveSearch((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_DeleteSearch".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_DeleteSearch((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_CMN_ColClear".equals(screenAplID)) {

                ZYPGUITableColumn.clearColData((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_CMN_ColSave".equals(screenAplID)) {

                ZYPGUITableColumn.setColData((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else {

                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {

            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_CMN_Submit
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_CMN_Submit(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        NLBL3070CommonLogic.saveCurrentPageToSMsgScheduling(cMsg, sMsg);

        Map<String, String> schdCoordPsnMap = new HashMap<String, String>();

        // Input check
        if (!inputCheckSubmit(cMsg, sMsg, schdCoordPsnMap)) {

            return;
        }

        // Warning
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue())) {

            if (!inputCheckWaringSubmit(cMsg, sMsg)) {

                ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                cMsg.setMessageInfo(NLBL3070Constant.NATM0001W);
                return;
            }
        }

        cMsg.xxWrnSkipFlg.clear();
        int errIndex = -1;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (NLBL3070CommonLogic.changedValueSchdLine(sMsg.A.no(i))) {

                // START 2018/10/03 M.Naito [QC#28539,MOD]
                // get Tech Meet Truck Flag
                // START 2019/04/23 K.Kitachi [QC#31245, DEL]
//                SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
//                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
//                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soNum, sMsg.A.no(i).soNum_A1);
//                shpgOrdTMsg = (SHPG_ORDTMsg) EZDFastTBLAccessor.findByKey(shpgOrdTMsg);
//                String techMeetTruckFlg = shpgOrdTMsg.techMeetTruckFlg.getValue();
                // END 2019/04/23 K.Kitachi [QC#31245, DEL]
                // END 2018/10/03 M.Naito [QC#28539,MOD]

                // Update
                NLZC205001PMsg soApiPMsg = new NLZC205001PMsg();
                ArrayList<NLZC205001PMsg> soApiParamList = new ArrayList<NLZC205001PMsg>();

                ZYPEZDItemValueSetter.setValue(soApiPMsg.glblCmpyCd, cMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(soApiPMsg.soNum, sMsg.A.no(i).soNum_A1);
                ZYPEZDItemValueSetter.setValue(soApiPMsg.xxModeCd, NLZC205001.MODE_MODIFY);
                ZYPEZDItemValueSetter.setValue(soApiPMsg.rsdDt, sMsg.A.no(i).schdCarrPickUpDt_A1);
                ZYPEZDItemValueSetter.setValue(soApiPMsg.rddDt, sMsg.A.no(i).schdDelyDt_A1);
                ZYPEZDItemValueSetter.setValue(soApiPMsg.schdCoordStsCd, sMsg.A.no(i).schdCoordStsCd_A1);
                ZYPEZDItemValueSetter.setValue(soApiPMsg.schdCoordPsnCd, schdCoordPsnMap.get(sMsg.A.no(i).soNum_A1.getValue()));
                // QC#18272 Add.
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyTsDplyTxt_A2)) {
                    // 2018/01/11 QC#18460(SOL#087) T,Hakodate ADD END
                    String schdDelyTm = NLBL3070CommonLogic.getAllDayTimes(sMsg.A.no(i).schdDelyTsDplyTxt_A2.getValue(), sMsg.A.no(i).rqstRcvDtTxt_S2.getValue());
                    // String schdDelyTs =
                    // sMsg.A.no(i).schdDelyDt_A1.getValue() +
                    // schdDelyTm;

                    String systemTimeZoneTs = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());

                    if (ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyDt_A1)) {
                        systemTimeZoneTs = sMsg.A.no(i).schdDelyDt_A1.getValue();
                    }

                    // Date + time
                    systemTimeZoneTs = systemTimeZoneTs + schdDelyTm;

                    SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, systemTimeZoneTs, sMsg.A.no(i).ctryCd_A1.getValue(), sMsg.A.no(i).postCd_A1.getValue());

                    String date = timeInfo.getDateTime().substring(0, 8);
                    String time = timeInfo.getDateTime().substring(8, 12);

                    // START 2020/03/02 [QC#50121, MOD]
//                    if (ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyDt_A1)) {
//                        ZYPEZDItemValueSetter.setValue(soApiPMsg.rddDt, date);
//                    }
                    ZYPEZDItemValueSetter.setValue(soApiPMsg.rddDt, date);
                    // END   2020/03/02 [QC#50121, MOD]

                    ZYPEZDItemValueSetter.setValue(soApiPMsg.schdDelyTm, time);
                    // 2018/01/11 QC#18460(SOL#087) T,Hakodate ADD END
                }

                // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD START
                if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).techMeetTruckFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(soApiPMsg.techMeetTruckFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(soApiPMsg.techMeetTruckFlg, ZYPConstant.FLG_OFF_N);
                }
                // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD END

                // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
                ZYPEZDItemValueSetter.setValue(soApiPMsg.nextActDt, sMsg.A.no(i).nextActDt_A1.getValue());
                // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]

                ZYPEZDItemValueSetter.setValue(soApiPMsg.carrCd, sMsg.A.no(i).carrCd_A1);
                ZYPEZDItemValueSetter.setValue(soApiPMsg.shpgSvcLvlCd, sMsg.A.no(i).shpgSvcLvlCd_A1);
                ZYPEZDItemValueSetter.setValue(soApiPMsg.tempSchdRsnCd, sMsg.A.no(i).tempSchdRsnCd_A1);
                ZYPEZDItemValueSetter.setValue(soApiPMsg.tempSchdCmntTxt, sMsg.A.no(i).tempSchdCmntTxt_A1);
                ZYPEZDItemValueSetter.setValue(soApiPMsg.schdDurnTmNum, sMsg.A.no(i).schdDurnTmNum_A1);
                ZYPEZDItemValueSetter.setValue(soApiPMsg.schdIstlDt, sMsg.A.no(i).schdIstlDt_A1);
                ZYPEZDItemValueSetter.setValue(soApiPMsg.carrAcctNum, sMsg.A.no(i).carrAcctNum_A1);

                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyTsDplyTxt_A1)) {

                    ZYPEZDItemValueSetter.setValue(soApiPMsg.schdIstlTm, NLBL3070CommonLogic.getAllDayTimes(sMsg.A.no(i).schdDelyTsDplyTxt_A1.getValue(), sMsg.A.no(i).rqstRcvDtTxt_A1.getValue()));
                }

                soApiParamList.add(soApiPMsg);
                NLZC205001 soApi = new NLZC205001();
                soApi.execute(soApiParamList, ONBATCH_TYPE.ONLINE);

                // START 2018/10/03 M.Naito [QC#28539,MOD]
                boolean sendTaskInfoErrFlg = false;
                // START 2018/01/09 M.Naito [QC#18889,ADD]
//                if (NLBL3070CommonLogic.chkDeliveryScheduleMail(cMsg, sMsg.A.no(i).soNum_A1.getValue())) {
                // START 2019/04/23 K.Kitachi [QC#31245, MOD]
//                if (NLBL3070CommonLogic.chkDeliveryScheduleMail(cMsg, sMsg.A.no(i).soNum_A1.getValue())
//                        || NLBL3070CommonLogic.chkChngTechMeetTheTruck(cMsg, sMsg.A.no(i).techMeetTruckFlg_A1.getValue(), sMsg.A.no(i).soNum_A1.getValue(), techMeetTruckFlg)) {
//                    // get Service Branch Manager Address
//                    String svcBrMngrEmlAddr = NLBL3070CommonLogic.getSvcBrMngrEmlAddr(cMsg, sMsg.A.no(i).soNum_A1.getValue());
//                    if (ZYPCommonFunc.hasValue(svcBrMngrEmlAddr)) {
////                        NLBL3070CommonLogic.sendMail(cMsg, sMsg.A.no(i).soNum_A1.getValue(), svcBrMngrEmlAddr);
//                        String afterErlStartTs = NLBL3070CommonLogic.sendMail(cMsg, sMsg.A.no(i).soNum_A1.getValue(), svcBrMngrEmlAddr);
//                        // update early start time and send Task Info to Click Software
//                        NSZC043001PMsg sendTaskInfoApiPMsg = NLBL3070CommonLogic.updateSvcTaskErlStartTs(cMsg, sMsg.A.no(i).soNum_A1.getValue(), afterErlStartTs, getContextUserInfo().getUserId());
//                        if (!NLBL3070CommonLogic.chkApiRslt(cMsg, sMsg.A.no(i).xxChkBox_A1, S21ApiUtil.getXxMsgIdList(sendTaskInfoApiPMsg))) {
//                            sendTaskInfoErrFlg = true;
//                        }
//                    }
//                }
                boolean chkDeliveryScheduleMail = NLBL3070CommonLogic.chkDeliveryScheduleMail(cMsg, sMsg.A.no(i).soNum_A1.getValue());
                boolean chkChngTechMeetTheTruck = NLBL3070CommonLogic.chkChngTechMeetTheTruck(cMsg, sMsg.A.no(i).techMeetTruckFlg_A1.getValue(), sMsg.A.no(i).soNum_A1.getValue());
                if (chkDeliveryScheduleMail || chkChngTechMeetTheTruck) {
                    // get Service Branch Manager Address
                    String svcBrMngrEmlAddr = NLBL3070CommonLogic.getEmlAddrFromTaskSvcBrMgr(cMsg, sMsg.A.no(i).soNum_A1.getValue());
                    if (!ZYPCommonFunc.hasValue(svcBrMngrEmlAddr)) {
                        svcBrMngrEmlAddr = NLBL3070CommonLogic.getSvcBrMngrEmlAddr(cMsg, sMsg.A.no(i).soNum_A1.getValue());
                    }
                    String afterErlStartTs = NLBL3070CommonLogic.sendMail(cMsg, sMsg.A.no(i).soNum_A1.getValue(), svcBrMngrEmlAddr, chkDeliveryScheduleMail);
                    // QC#53743
                    if (chkChngTechMeetTheTruck && afterErlStartTs != null) {
                        // update early start time and send Task Info to Click Software
                        NSZC043001PMsg sendTaskInfoApiPMsg = NLBL3070CommonLogic.updateSvcTaskErlStartTs(cMsg, sMsg.A.no(i).soNum_A1.getValue(), afterErlStartTs, getContextUserInfo().getUserId());
                        if (!NLBL3070CommonLogic.chkApiRslt(cMsg, sMsg.A.no(i).xxChkBox_A1, S21ApiUtil.getXxMsgIdList(sendTaskInfoApiPMsg))) {
                            sendTaskInfoErrFlg = true;
                        }
                    }
                }
                // END 2019/04/23 K.Kitachi [QC#31245, MOD]
                // END 2018/01/09 M.Naito [QC#18889,ADD]

//                if (!NLBL3070CommonLogic.chkApiRslt(cMsg, sMsg.A.no(i).xxChkBox_A1, S21ApiUtil.getXxMsgIdList(soApiPMsg))) {
                if (!NLBL3070CommonLogic.chkApiRslt(cMsg, sMsg.A.no(i).xxChkBox_A1, S21ApiUtil.getXxMsgIdList(soApiPMsg)) || sendTaskInfoErrFlg) {

                    if (errIndex == -1) {

                        errIndex = i;
                    }
                }
                // END 2018/10/03 M.Naito [QC#28539,MOD]
            }
        }

        if (0 <= errIndex) {

            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(errIndex, cMsg, sMsg));
        }

        // Normal End
        cMsg.setMessageInfo(NLBL3070Constant.ZZZM9003I, new String[] {"Submit" });
        return;
    }

    /**
     * doProcess_NLBL3070Scrn00_Release_SO
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Release_SO(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        NLBL3070CommonLogic.saveCurrentPageToSMsgScheduling(cMsg, sMsg);

        // Input check
        if (!inputCheckReleaseSo(cMsg, sMsg)) {

            return;
        }

        // Warning
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue())) {

            if (!NLBL3070CommonLogic.inputCheckWaringForValueChange(cMsg, sMsg)) {

                ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                cMsg.setMessageInfo(NLBL3070Constant.NSBM0029W);
                return;
            }
        }

        cMsg.xxWrnSkipFlg.clear();
        int errIndex = -1;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {

                // Update
                NLZC205001PMsg soApiPMsg = new NLZC205001PMsg();
                ArrayList<NLZC205001PMsg> soApiParamList = new ArrayList<NLZC205001PMsg>();
                ZYPEZDItemValueSetter.setValue(soApiPMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(soApiPMsg.xxModeCd, NLZC205001.MODE_WMS_DROP);
                ZYPEZDItemValueSetter.setValue(soApiPMsg.soNum, sMsg.A.no(i).soNum_A1.getValue());

                soApiParamList.add(soApiPMsg);
                NLZC205001 soApi = new NLZC205001();
                soApi.execute(soApiParamList, ONBATCH_TYPE.ONLINE);

                if (!NLBL3070CommonLogic.chkApiRslt(cMsg, sMsg.A.no(i).xxChkBox_A1, S21ApiUtil.getXxMsgIdList(soApiPMsg))) {

                    if (errIndex == -1) {

                        errIndex = i;
                    }
                }
            }
        }

        if (0 <= errIndex) {

            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(errIndex, cMsg, sMsg));
        }

        // Normal End
        cMsg.setMessageInfo(NLBL3070Constant.ZZZM9003I, new String[] {"Drop Shipping Order" });
        return;
    }

    /**
     * doProcess_NLBL3070Scrn00_Send_Rqst
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Send_Rqst(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        NLBL3070CommonLogic.saveCurrentPageToSMsgScheduling(cMsg, sMsg);

        // Input check
        if (!inputCheckSendRqst(cMsg, sMsg)) {

            return;
        }

        // Warning
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue())) {

            if (!NLBL3070CommonLogic.inputCheckWaringForValueChange(cMsg, sMsg)) {

                ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                cMsg.setMessageInfo(NLBL3070Constant.NSBM0029W);
                return;
            }
        }

        cMsg.xxWrnSkipFlg.clear();
        int errIndex = -1;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {

                // Update
                NLZC205001PMsg soApiPMsg = new NLZC205001PMsg();
                ArrayList<NLZC205001PMsg> soApiParamList = new ArrayList<NLZC205001PMsg>();
                ZYPEZDItemValueSetter.setValue(soApiPMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(soApiPMsg.xxModeCd, NLZC205001.MODE_CARR_RQST);
                ZYPEZDItemValueSetter.setValue(soApiPMsg.soNum, sMsg.A.no(i).soNum_A1.getValue());
                soApiParamList.add(soApiPMsg);

                NLZC205001 soApi = new NLZC205001();
                soApi.execute(soApiParamList, ONBATCH_TYPE.ONLINE);

                if (!NLBL3070CommonLogic.chkApiRslt(cMsg, sMsg.A.no(i).xxChkBox_A1, S21ApiUtil.getXxMsgIdList(soApiPMsg))) {

                    if (errIndex == -1) {

                        errIndex = i;
                    }
                }
            }
        }

        // Having Error
        if (0 <= errIndex) {

            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(errIndex, cMsg, sMsg));
        }

        // Normal End
        cMsg.setMessageInfo(NLBL3070Constant.ZZZM9003I, new String[] {"Send Carrier Request" });
        return;
    }

    /**
     * doProcess_NLBL3070Scrn00_SO_Cancel
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_SO_Cancel(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        NLBL3070CommonLogic.saveCurrentPageToSMsgDeliveries(cMsg, sMsg);
        String cancelDate = getCurrentDateTime();

        // Input check
        if (!inputCheckSoCancel(cMsg, sMsg)) {

            return;
        }

        // Call NLZC2100:SO Confirmation from S21 DC API
        HashSet<String> soNumSet = new HashSet<String>(1000);
        int errIndex = -1;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            NLBL3070_BSMsg sMsgBLineHdr = sMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLineHdr.xxChkBox_B2.getValue()) && !soNumSet.contains(sMsgBLineHdr.soNum_B1.getValue())) {

                String soNum = sMsgBLineHdr.soNum_B1.getValue();

                // Create Header PMsg
                NLZC210001PMsg pmsgHdr = createPMsgSoConfHdrSoCancel(cMsg.glblCmpyCd.getValue(), cancelDate, sMsgBLineHdr);

                // Get Shipping Order Detail
                SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
                shpgOrdDtlTMsg.setSQLID("002");
                shpgOrdDtlTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
                shpgOrdDtlTMsg.setConditionValue("soNum01", sMsgBLineHdr.soNum_B1.getValue());
                SHPG_ORD_DTLTMsgArray shpgOrdDtlList = (SHPG_ORD_DTLTMsgArray) EZDTBLAccessor.findByCondition(shpgOrdDtlTMsg);

                List<NLZC210001PMsg> pmsgDtlList = new ArrayList<NLZC210001PMsg>();

                for (int j = 0; j < sMsg.B.getValidCount(); j++) {

                    NLBL3070_BSMsg sMsgBLineDtl = sMsg.B.no(j);

                    if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLineDtl.xxChkBox_B2.getValue()) && soNum.equals(sMsgBLineDtl.soNum_B1.getValue())) {

                        // Create Detail PMsg
                        pmsgDtlList.add(createPMsgSoConfDtlSoCancel(cMsg.glblCmpyCd.getValue(), cancelDate, sMsgBLineDtl));
                    }
                }

                if (pmsgDtlList.size() == shpgOrdDtlList.getValidCount()) {

                    // If SO All Cancel
                    pmsgDtlList.clear();
                    pmsgDtlList.add(pmsgHdr);
                }

                // Call API
                NLZC210001 soConfAPI = new NLZC210001();
                soConfAPI.execute(pmsgDtlList, null, ONBATCH_TYPE.ONLINE);

                // Check Error in API
                for (NLZC210001PMsg pmsg : pmsgDtlList) {

                    if (!NLBL3070CommonLogic.chkApiRslt(cMsg, sMsgBLineHdr.xxChkBox_B2, S21ApiUtil.getXxMsgIdList(pmsg))) {

                        if (errIndex == -1) {

                            errIndex = i;
                        }

                        break;
                    }
                }

                soNumSet.add(sMsgBLineHdr.soNum_B1.getValue());
            }
        }

        // Having Error
        if (0 <= errIndex) {

            // All Expansion
            NLBL3070CommonLogic.allExpansion(sMsg);
            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(errIndex, cMsg, sMsg));
            cMsg.setMessageInfo(NLBL3070Constant.NLBM0049E);
        }

        // Normal End
        cMsg.setMessageInfo(NLBL3070Constant.ZZZM9003I, new String[] {"Cancel" });
    }

    /**
     * doProcess_NLBL3070Scrn00_Ship
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Ship(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        NLBL3070CommonLogic.saveCurrentPageToSMsgDeliveries(cMsg, sMsg);
        String shipDateTime = getCurrentDateTime();

        // Input check
        if (!inputCheckShip(cMsg, sMsg)) {

            return;
        }

        // Warning
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue())) {

            if (!inputCheckWaringShip(cMsg, sMsg)) {

                ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                cMsg.setMessageInfo(NLBL3070Constant.NATM0001W);
                return;
            }
        }

        cMsg.xxWrnSkipFlg.clear();

        // Call NLZC2100:SO Confirmation from S21 DC API
        HashSet<String> soNumSet = new HashSet<String>(1000);
        int errIndex = -1;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B2.getValue()) && !soNumSet.contains(sMsg.B.no(i).soNum_B1.getValue())) {

                // Get Shipping Order Detail
                SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
                shpgOrdDtlTMsg.setSQLID("002");
                shpgOrdDtlTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
                shpgOrdDtlTMsg.setConditionValue("soNum01", sMsg.B.no(i).soNum_B1.getValue());
                SHPG_ORD_DTLTMsgArray shpgOrdDtlList = (SHPG_ORD_DTLTMsgArray) EZDTBLAccessor.findByCondition(shpgOrdDtlTMsg);

                List<NLZC210001PMsg> pmsgDtlList = new ArrayList<NLZC210001PMsg>();
                List<NLZC210002PMsg> pmsgSerList = new ArrayList<NLZC210002PMsg>();

                boolean isLinePrtlShip = false;

                for (int j = 0; j < sMsg.B.getValidCount(); j++) {

                    NLBL3070_BSMsg sMsgBLineDtl = sMsg.B.no(j);

                    if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLineDtl.xxChkBox_B2.getValue()) && sMsg.B.no(i).soNum_B1.getValue().equals(sMsgBLineDtl.soNum_B1.getValue())) {

                        // Check Line Partial Ship
                        if (!sMsgBLineDtl.xxShipQty_B1.getValue().equals(sMsgBLineDtl.shipQty_BK.getValue())) {

                            isLinePrtlShip = true;
                        }

                        // Create Detail PMsg
                        pmsgDtlList.add(createPMsgSoConfDtlShip(cMsg.glblCmpyCd.getValue(), shipDateTime, sMsgBLineDtl));

                        // Create Serial PMsg
                        List<NLZC210002PMsg> pmsgSerListIn = createPMsgSoConfSerShip(cMsg.glblCmpyCd.getValue(), shipDateTime, sMsg, sMsgBLineDtl);

                        if (pmsgSerListIn != null && !pmsgSerListIn.isEmpty()) {

                            pmsgSerList.addAll(pmsgSerListIn);
                        }
                    }
                }

                if (pmsgDtlList.size() != shpgOrdDtlList.getValidCount() || isLinePrtlShip) {

                    // If not SO all ship
                    for (int a = 0; a < pmsgDtlList.size(); a++) {

                        pmsgDtlList.get(a).soProcStsCd.clear();
                    }
                }

                // Call API
                NLZC210001 soConfAPI = new NLZC210001();
                soConfAPI.execute(pmsgDtlList, pmsgSerList, ONBATCH_TYPE.ONLINE);

                // Check Error in API
                boolean hasDtlErr = false;

                for (NLZC210001PMsg pmsgDtl : pmsgDtlList) {

                    if (!NLBL3070CommonLogic.chkApiRslt(cMsg, sMsg.B.no(i).xxChkBox_B2, S21ApiUtil.getXxMsgIdList(pmsgDtl))) {

                        if (errIndex == -1) {

                            errIndex = i;
                        }

                        hasDtlErr = true;
                        break;
                    }
                }

                if (!hasDtlErr) {

                    for (NLZC210002PMsg pmsgSer : pmsgSerList) {

                        if (!NLBL3070CommonLogic.chkApiRslt(cMsg, sMsg.B.no(i).xxChkBox_B2, S21ApiUtil.getXxMsgIdList(pmsgSer))) {

                            if (errIndex == -1) {

                                errIndex = i;
                            }

                            break;
                        }
                    }
                }

                // QC#21913 insert SHPG_ORD_PRO_NUM_LIST
                if (!insertShipOrdProNumList(cMsg, sMsg, sMsg.B.no(i).soNum_B1.getValue())) {
                    if (errIndex == -1) {

                        errIndex = i;
                    }

                    break;
                }

                soNumSet.add(sMsg.B.no(i).soNum_B1.getValue());
            }
        }

        // Having Error
        if (0 <= errIndex) {

            // All Expansion
            NLBL3070CommonLogic.allExpansion(sMsg);
            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(errIndex, cMsg, sMsg));
            cMsg.setMessageInfo(NLBL3070Constant.NLBM0024E, new String[] {"Ship Confirmation" });

            // Normal End
        } else {

            cMsg.setMessageInfo(NLBL3070Constant.ZZZM9003I, new String[] {"Ship Confirmation" });
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_SO_Close
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_SO_Close(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        NLBL3070CommonLogic.saveCurrentPageToSMsgDeliveries(cMsg, sMsg);
        String closeDate = getCurrentDateTime();

        // Input check
        if (!inputCheckSoClose(cMsg, sMsg)) {

            return;
        }

        // Call NLZC2100:SO Confirmation from S21 DC API
        HashSet<String> soNumSet = new HashSet<String>(1000);
        int errIndex = -1;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            NLBL3070_BSMsg sMsgBLineHdr = sMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLineHdr.xxChkBox_B1.getValue()) && !soNumSet.contains(sMsgBLineHdr.soNum_B1.getValue())) {

                NLZC210001PMsg pmsg = createPMsgSoConfSoClose(cMsg.glblCmpyCd.getValue(), closeDate, sMsgBLineHdr);

                // Call API
                NLZC210001 soConfAPI = new NLZC210001();
                soConfAPI.execute(pmsg, null, ONBATCH_TYPE.ONLINE);

                // Check Error in API
                if (!NLBL3070CommonLogic.chkApiRslt(cMsg, sMsgBLineHdr.xxChkBox_B1, S21ApiUtil.getXxMsgIdList(pmsg))) {

                    if (errIndex == -1) {

                        errIndex = i;
                    }
                }

                soNumSet.add(sMsgBLineHdr.soNum_B1.getValue());
            }
        }

        // Having Error
        if (0 <= errIndex) {

            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(errIndex, cMsg, sMsg));
            cMsg.setMessageInfo(NLBL3070Constant.NLBM0024E, new String[] {"Shipping Order Close" });

            // Normal End
        } else {

            cMsg.setMessageInfo(NLBL3070Constant.ZZZM9003I, new String[] {"Shipping Order Close" });
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Delivery_Conf
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Delivery_Conf(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        // QC#20043
        String actlDelyTm = ZYPDateUtil.getCurrentSystemTime("HHmmss");

        NLBL3070CommonLogic.saveCurrentPageToSMsgDeliveries(cMsg, sMsg);

        // Input check
        if (!inputCheckDeliveryConf(cMsg, sMsg)) {

            return;
        }

        // Call Delivery Confirmation API (NLZC4020)
        Map<String, String> whCarrBolMap = new HashMap<String, String>();
        int errIndex = -1;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            NLBL3070_BSMsg sMsgBLineDtl = sMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLineDtl.xxChkBox_B2.getValue())) {

                // QC#20978 Mod Start
                // Actual Delivery Date
                if (ZYPCommonFunc.hasValue(sMsgBLineDtl.actlDelyDt_B1)
                        && (NLBL3070CommonLogic.changedValue(sMsgBLineDtl.actlDelyDt_B1.getValue(), sMsgBLineDtl.actlDelyDt_BK.getValue()))) { // QC#18272 Mod.

                    String rtlWhAndCarr = ZYPCommonFunc.concatString(sMsgBLineDtl.rtlWhCd_B1.getValue(), ":", sMsgBLineDtl.carrCd_B1.getValue());
                    String delyConfChkKey = ZYPCommonFunc.concatString(rtlWhAndCarr, ":", sMsgBLineDtl.proNum_BD.getValue());

                    if (whCarrBolMap.isEmpty() || !whCarrBolMap.containsKey(delyConfChkKey) || !ZYPCommonFunc.hasValue(sMsgBLineDtl.proNum_BD)) {

                        NLZC402001PMsg pmsg = new NLZC402001PMsg();
                        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(pmsg.ediShpprNm, sMsgBLineDtl.rtlWhNm_B1.getValue());
                        ZYPEZDItemValueSetter.setValue(pmsg.ediShpprAddr, convSubString(pmsg, "ediShpprAddr", sMsgBLineDtl.firstLineAddr_RW));
                        ZYPEZDItemValueSetter.setValue(pmsg.ediShpprCtyNm, sMsgBLineDtl.ctyAddr_RW.getValue());
                        ZYPEZDItemValueSetter.setValue(pmsg.ediShpprStCd, sMsgBLineDtl.stCd_RW.getValue());
                        ZYPEZDItemValueSetter.setValue(pmsg.ediShpprPostCd, convSubString(pmsg, "ediShpprPostCd", sMsgBLineDtl.postCd_RW));
                        ZYPEZDItemValueSetter.setValue(pmsg.ediCnsgnNm, convSubString(pmsg, "ediCnsgnNm", sMsgBLineDtl.dsAcctNm_B1));
                        ZYPEZDItemValueSetter.setValue(pmsg.ediCnsgnAddr, convSubString(pmsg, "ediCnsgnAddr", sMsgBLineDtl.firstLineAddr_B1));
                        ZYPEZDItemValueSetter.setValue(pmsg.ediCnsgnCtyNm, sMsgBLineDtl.ctyAddr_B1);
                        ZYPEZDItemValueSetter.setValue(pmsg.ediCnsgnStCd, sMsgBLineDtl.stCd_B1);
                        ZYPEZDItemValueSetter.setValue(pmsg.ediCnsgnPostCd, convSubString(pmsg, "ediCnsgnPostCd", sMsgBLineDtl.postCd_B1));
                        ZYPEZDItemValueSetter.setValue(pmsg.podSrcTpCd, POD_SRC_TP.SCREEN);
                        ZYPEZDItemValueSetter.setValue(pmsg.ediProNum, sMsgBLineDtl.proNum_BD);
                        ZYPEZDItemValueSetter.setValue(pmsg.ediStsCd, POD_STS.COMPLETED_DEPARTED_DELIVERY_LOCATION);
                        ZYPEZDItemValueSetter.setValue(pmsg.ediStsDt, sMsgBLineDtl.actlDelyDt_B1);
                        ZYPEZDItemValueSetter.setValue(pmsg.ediTmCd, NLBL3070Constant.EDI_TM_CD_LT);
                        ZYPEZDItemValueSetter.setValue(pmsg.ediStsCtyNm, sMsgBLineDtl.ctyAddr_B1);
                        ZYPEZDItemValueSetter.setValue(pmsg.ediStsStCd, sMsgBLineDtl.stCd_B1);
                        ZYPEZDItemValueSetter.setValue(pmsg.soNum, sMsgBLineDtl.soNum_B1.getValue());
                        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, sMsgBLineDtl.soSlpNum_B1.getValue());
                        ZYPEZDItemValueSetter.setValue(pmsg.carrCd, sMsgBLineDtl.carrCd_B1.getValue());
                        ZYPEZDItemValueSetter.setValue(pmsg.carrRsnCd, sMsgBLineDtl.carrRsnCd_B1.getValue());
                        // QC#20043
                        if (ZYPCommonFunc.hasValue(sMsgBLineDtl.schdDelyTsDplyTxt_B2)) {
                            ZYPEZDItemValueSetter.setValue(pmsg.ediStsTm, sMsgBLineDtl.schdDelyTsDplyTxt_B2.getValue().replace(":", ""));
                        } else {
                            ZYPEZDItemValueSetter.setValue(pmsg.ediStsTm, actlDelyTm);
                        }

                        // Call API
                        NLZC402001 deliveryConfAPI = new NLZC402001();
                        deliveryConfAPI.execute(pmsg, ONBATCH_TYPE.ONLINE);

                        if (ZYPCommonFunc.hasValue(sMsgBLineDtl.proNum_BD)) {

                            whCarrBolMap.put(delyConfChkKey, null);
                        }

                        // Check Error in API
                        if (!S21ApiUtil.getXxMsgIdList(pmsg).isEmpty()) {

                            for (String msgId : S21ApiUtil.getXxMsgIdList(pmsg)) {

                                if (ZYPCommonFunc.hasValue(msgId)) {

                                    cMsg.setMessageInfo(msgId, null);

                                    if (msgId.endsWith("E")) {

                                        if (errIndex == -1) {

                                            errIndex = i;
                                        }

                                        sMsgBLineDtl.xxChkBox_B2.setErrorInfo(1, msgId);

                                        if (ZYPCommonFunc.hasValue(sMsgBLineDtl.proNum_BD)) {

                                            whCarrBolMap.put(delyConfChkKey, msgId);
                                        }

                                        break;
                                    }
                                }
                            }
                        }

                    } else {

                        if (ZYPCommonFunc.hasValue(whCarrBolMap.get(delyConfChkKey))) {

                            sMsgBLineDtl.xxChkBox_B2.setErrorInfo(1, whCarrBolMap.get(delyConfChkKey));
                        }
                    }

                } else {

                    // Carrier Reason
                    if (NLBL3070CommonLogic.changedValue(sMsgBLineDtl.carrRsnCd_B1.getValue(), sMsgBLineDtl.carrRsnCd_BK.getValue())) {

                        SHPG_ORD_DTLTMsg shpgOrdDtl = new SHPG_ORD_DTLTMsg();
                        ZYPEZDItemValueSetter.setValue(shpgOrdDtl.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(shpgOrdDtl.soNum, sMsgBLineDtl.soNum_B1.getValue());
                        ZYPEZDItemValueSetter.setValue(shpgOrdDtl.soSlpNum, sMsgBLineDtl.soSlpNum_B1.getValue());
                        shpgOrdDtl = (SHPG_ORD_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(shpgOrdDtl);

                        if (shpgOrdDtl == null) {

                            sMsgBLineDtl.xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM0009E);

                            if (errIndex == -1) {

                                errIndex = i;
                            }

                            continue;
                        }

                        ZYPEZDItemValueSetter.setValue(shpgOrdDtl.carrRsnCd, sMsgBLineDtl.carrRsnCd_B1.getValue());
                        EZDTBLAccessor.update(shpgOrdDtl);

                        String returnCode = shpgOrdDtl.getReturnCode();

                        if (!RTNCD_NORMAL.equals(returnCode)) {

                            sMsgBLineDtl.xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM1295E, new String[] {"Shpping Order Detail" });

                            if (errIndex == -1) {

                                errIndex = i;
                            }

                            continue;
                        }
                    }
                }
            }
        }

        // Having Error
        if (0 <= errIndex) {

            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(errIndex, cMsg, sMsg));
            cMsg.setMessageInfo(NLBL3070Constant.NLBM0024E, new String[] {"Delivery Confirmation" });

            // Normal End
        } else {

            cMsg.setMessageInfo(NLBL3070Constant.ZZZM9003I, new String[] {"Delivery Confirmation" });
        }
    }

    /**
     * inputCheckSubmit
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @param schdCoordPsnMap Map<String, String>
     * @return boolean
     */
    private boolean inputCheckSubmit(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg, Map<String, String> schdCoordPsnMap) {

        boolean hasErr = false;
        boolean firstEvent = false;
        int firstErrIdx = -1;
        int changedLineCnt = 0;

        if (!ZYPCommonFunc.hasValue(cMsg.xxMntEventNm) || !cMsg.xxMntEventNm.getValue().equals("NLBL3070Scrn00_CMN_Submit")) {

            ZYPEZDItemValueSetter.setValue(cMsg.xxMntEventNm, "NLBL3070Scrn00_CMN_Submit");
            firstEvent = true;
            cMsg.xxWrnSkipFlg.clear();
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (firstEvent) {

                NLBL3070CommonLogic.initSchdPrevVal(sMsg.A.no(i));
            }

            boolean hasErrLine = false;

            // START 2020/02/14 [QC#50121, ADD]
            if (NLBL3070CommonLogic.changedValueSchdLinePrev(sMsg.A.no(i))) {
                // Backup for warning check
                NLBL3070CommonLogic.setPrevDataForWrngChk(sMsg.A.no(i));

                cMsg.xxWrnSkipFlg.clear();
            }
            // END 2020/02/14 [QC#50121, ADD]

            if (!NLBL3070CommonLogic.changedValueSchdLine(sMsg.A.no(i))) {

                continue;
            }

            changedLineCnt++;

            // START 2020/02/14 [QC#50121, DEL]
//            if (NLBL3070CommonLogic.changedValueSchdLinePrev(sMsg.A.no(i))) {
//
//                cMsg.xxWrnSkipFlg.clear();
//            }
            // END 2020/02/14 [QC#50121, DEL]

            // Schedule Carrier Pickup Date
            if (NLBL3070CommonLogic.changedValue(sMsg.A.no(i).schdCarrPickUpDt_A1.getValue(), sMsg.A.no(i).schdCarrPickUpDt_BK.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(i).schdCarrPickUpDt_A1)) {

                if (0 < cMsg.slsDt.getValue().compareTo(sMsg.A.no(i).schdCarrPickUpDt_A1.getValue())) {

                    // Past Date
                    sMsg.A.no(i).schdCarrPickUpDt_A1.setErrorInfo(1, NLBL3070Constant.NLBM1231E, new String[] {"Scheduled Carrier Pickup Date" });
                    hasErrLine = true;

                } else if (ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyDt_A1) && 0 < sMsg.A.no(i).schdCarrPickUpDt_A1.getValue().compareTo(sMsg.A.no(i).schdDelyDt_A1.getValue())) {

                    // Carrier Pickup > Delivery
                    sMsg.A.no(i).schdCarrPickUpDt_A1.setErrorInfo(1, NLBL3070Constant.NFCM0780E, new String[] {"Scheduled Delivery Date", "Scheduled Carrier Pickup Date" });
                    hasErrLine = true;
                }
            }

            // Schedule Delivery Date
            if (NLBL3070CommonLogic.changedValue(sMsg.A.no(i).schdDelyDt_A1.getValue(), sMsg.A.no(i).schdDelyDt_BK.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyDt_A1)) {

                if (0 < cMsg.slsDt.getValue().compareTo(sMsg.A.no(i).schdDelyDt_A1.getValue())) {

                    // Past Date
                    sMsg.A.no(i).schdDelyDt_A1.setErrorInfo(1, NLBL3070Constant.NLBM1231E, new String[] {"Scheduled Delivery Date" });
                    hasErrLine = true;

                } else if (ZYPCommonFunc.hasValue(sMsg.A.no(i).schdCarrPickUpDt_A1) && 0 < sMsg.A.no(i).schdCarrPickUpDt_A1.getValue().compareTo(sMsg.A.no(i).schdDelyDt_A1.getValue())) {

                    // Carrier Pickup > Delivery
                    sMsg.A.no(i).schdDelyDt_A1.setErrorInfo(1, NLBL3070Constant.NFCM0780E, new String[] {"Scheduled Delivery Date", "Scheduled Carrier Pickup Date" });
                    hasErrLine = true;

                } else if (ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyDt_BK) && !SCHD_COORD_STS.RE_SCHEDULED.equals(sMsg.A.no(i).schdCoordStsCd_A1.getValue())) {
                    // QC#18725 Add Error check.
                    sMsg.A.no(i).schdCoordStsCd_A1.setErrorInfo(1, NLBL3070Constant.NLBM1018E, new String[] {"Scheduling Status", "If Scheduled Delivery Date changed", "Scheduling Status must be set Re-Scheduled." });
                    hasErrLine = true;
                }

                // QC#18725 Add Error check.
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyDt_BK) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).tempSchdRsnCd_A1)) {
                    sMsg.A.no(i).tempSchdRsnCd_A1.setErrorInfo(1, NLBL3070Constant.NLBM1018E, new String[] {"Scheduling Notes", "If Scheduled Delivery Date changed", "Scheduling Notes is required." });
                    hasErrLine = true;
                }
            }
            // QC#18272 Add.
            // Schedule Delivery Time
            if ((NLBL3070CommonLogic.changedValue(sMsg.A.no(i).schdDelyTsDplyTxt_A2.getValue(), sMsg.A.no(i).schdDelyTsDplyTxt_K1.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyTsDplyTxt_A2))
                    || (NLBL3070CommonLogic.changedValue(sMsg.A.no(i).rqstRcvDtTxt_S2.getValue(), sMsg.A.no(i).rqstRcvDtTxt_BK.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstRcvDtTxt_S2))) {

                if (!NLBL3070CommonLogic.checkTimeAndAmPm(sMsg.A.no(i).schdDelyTsDplyTxt_A2, sMsg.A.no(i).rqstRcvDtTxt_S2)) {

                    hasErrLine = true;

                }

                // QC#18725 Add Error check.
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyTsDplyTxt_K1) && !SCHD_COORD_STS.RE_SCHEDULED.equals(sMsg.A.no(i).schdCoordStsCd_A1.getValue())) {
                    sMsg.A.no(i).schdDelyTsDplyTxt_A2.setErrorInfo(1, NLBL3070Constant.NLBM1018E, new String[] {"Scheduling Status", "If Scheduled Delivery Date changed", "Scheduling Status must be set Re-Scheduled." });
                    hasErrLine = true;
                }
                // QC#18725 Add Error check.
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstRcvDtTxt_BK) && !SCHD_COORD_STS.RE_SCHEDULED.equals(sMsg.A.no(i).schdCoordStsCd_A1.getValue())) {

                    sMsg.A.no(i).rqstRcvDtTxt_S2.setErrorInfo(1, NLBL3070Constant.NLBM1018E, new String[] {"Scheduling Status", "If Scheduled Delivery Date changed", "Scheduling Status must be set Re-Scheduled." });
                    hasErrLine = true;
                }
            }

            // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
            // nextActDt
            if (NLBL3070CommonLogic.changedValue(sMsg.A.no(i).nextActDt_A1.getValue(), sMsg.A.no(i).nextActDt_BK.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(i).nextActDt_A1)) {

                if (0 < cMsg.slsDt.getValue().compareTo(sMsg.A.no(i).nextActDt_A1.getValue())) {

                    // Past Date
                    sMsg.A.no(i).nextActDt_A1.setErrorInfo(1, NLBL3070Constant.NLBM1231E, new String[] {"Next Action Date" });
                    hasErrLine = true;
                }
            }
            // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]

            // 201/12/28 QC#18460(SOL#087) T,Hakodate DEL START
            // Time Stop (Schedule Install Date)
//            if (NLBL3070CommonLogic.changedValue(sMsg.A.no(i).schdIstlDt_A1.getValue(), sMsg.A.no(i).schdIstlDt_BK.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(i).schdIstlDt_A1)) {
//
//                if (0 < cMsg.slsDt.getValue().compareTo(sMsg.A.no(i).schdIstlDt_A1.getValue())) {
//
//                    // Past Date
//                    sMsg.A.no(i).schdIstlDt_A1.setErrorInfo(1, NLBL3070Constant.NLBM1231E, new String[] {"Timestop Date" });
//                    hasErrLine = true;
//                }
//            }
//
//            // Time Stop (Schedule Install Time)
//            if ((NLBL3070CommonLogic.changedValue(sMsg.A.no(i).schdDelyTsDplyTxt_A1.getValue(), sMsg.A.no(i).schdDelyTsDplyTxt_BK.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyTsDplyTxt_A1))
//                    || (NLBL3070CommonLogic.changedValue(sMsg.A.no(i).rqstRcvDtTxt_A1.getValue(), sMsg.A.no(i).rqstRcvDtTxt_BK.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(i).rqstRcvDtTxt_A1))) {
//
//                if (!NLBL3070CommonLogic.checkTimeAndAmPm(sMsg.A.no(i).schdDelyTsDplyTxt_A1, sMsg.A.no(i).rqstRcvDtTxt_A1)) {
//
//                    hasErrLine = true;
//                }
//            }
            // 201/12/28 QC#18460(SOL#087) T,Hakodate DEL START

            // Check Carrier Master
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).carrNm_A1)) {

                sMsg.A.no(i).carrCd_A1.clear();

            } else if (NLBL3070CommonLogic.changedValue(sMsg.A.no(i).carrNm_A1.getValue(), sMsg.A.no(i).carrNm_BK.getValue())) {

                List<String> carrCdList = NLBL3070CommonLogic.getCarrCdList(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).carrNm_A1.getValue());

                if (carrCdList == null || carrCdList.isEmpty()) {

                    sMsg.A.no(i).carrNm_A1.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[] {"Carrier" });
                    sMsg.A.no(i).carrCd_A1.clear();
                    hasErrLine = true;

                } else if (carrCdList.size() > 1) {

                    if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).carrCd_A1) || !carrCdList.contains(sMsg.A.no(i).carrCd_A1.getValue())) {

                        sMsg.A.no(i).carrNm_A1.setErrorInfo(1, NLBL3070Constant.NLBM1341E, new String[] {"Carriers" });
                        sMsg.A.no(i).carrCd_A1.clear();
                        hasErrLine = true;
                    }

                } else {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).carrCd_A1, carrCdList.get(0));
                }
            }

            // Check Shipping Service Level
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).carrCd_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).shpgSvcLvlCd_A1)) {

                if (NLBL3070CommonLogic.changedValue(sMsg.A.no(i).carrCd_A1.getValue(), sMsg.A.no(i).carrCd_BK.getValue())
                        || (NLBL3070CommonLogic.changedValue(sMsg.A.no(i).shpgSvcLvlCd_A1.getValue(), sMsg.A.no(i).shpgSvcLvlCd_BK.getValue()))) {

                    List<String> carrSvcLvlCdList = NLBL3070CommonLogic.getCarrSvcLvlCdList(cMsg, sMsg.A.no(i).shpgSvcLvlCd_A1.getValue(), sMsg.A.no(i).carrCd_A1.getValue());

                    if (carrSvcLvlCdList == null || carrSvcLvlCdList.isEmpty()) {

                        sMsg.A.no(i).carrNm_A1.setErrorInfo(1, NLBL3070Constant.NLBM1308E, new String[] {"Service Level", "Carrier" });
                        sMsg.A.no(i).shpgSvcLvlCd_A1.setErrorInfo(1, NLBL3070Constant.NLBM1308E, new String[] {"Service Level", "Carrier" });
                        hasErrLine = true;
                    }
                }
            }

            // QC#18725 Add check.
            if(TEMP_SCHD_RSN.OTHER.equals(sMsg.A.no(i).tempSchdRsnCd_A1.getValue()) //
                    && ! ZYPCommonFunc.hasValue(sMsg.A.no(i).tempSchdCmntTxt_A1)){
                sMsg.A.no(i).tempSchdCmntTxt_A1.setErrorInfo(1, NLBL3070Constant.NLBM1297E, new String[]{"Other", "Scheduling Notes", "Scheduling Notes Comment"});
                hasErrLine = true;
            }

            // Check time stamp
            SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soNum, sMsg.A.no(i).soNum_A1.getValue());

            shpgOrdTMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(shpgOrdTMsg);

            if (shpgOrdTMsg == null) {

                sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NLBL3070Constant.NLBM0009E);
                hasErrLine = true;

            } else {

                if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime_SO.getValue(), sMsg.A.no(i).ezUpTimeZone_SO.getValue() //
                        , shpgOrdTMsg.ezUpTime.getValue(), shpgOrdTMsg.ezUpTimeZone.getValue())) {

                    // anyone update
                    sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NLBL3070Constant.NLBM0009E);
                    hasErrLine = true;

                } else {

                    schdCoordPsnMap.put(shpgOrdTMsg.soNum.getValue(), shpgOrdTMsg.schdCoordPsnCd.getValue());
                }
            }

            // Error
            if (hasErrLine) {

                hasErr = true;

                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }
            }

            // Backup for warning check
            NLBL3070CommonLogic.setPrevDataForWrngChk(sMsg.A.no(i));
        }

        if (changedLineCnt == 0) {

            cMsg.setMessageInfo(NLBL3070Constant.NLBM1303E, new String[] {"Scheduling Data" });
            return false;
        }

        if (hasErr) {

            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            return false;
        }

        return true;
    }

    /**
     * inputCheckWaringSubmit
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return boolean
     */
    private boolean inputCheckWaringSubmit(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        boolean hasWrng = false;
        int firstErrIdx = -1;

        Map<String, String> whCalTpMap = new HashMap<String, String>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLBL3070_ASMsg sMsgALine = sMsg.A.no(i);

            // START 2020/02/04 [QC#50121, ADD]
            // Tech Meet The Truck
            if (SCHD_COORD_STS.AWAITING_SCHEDULING.equals(sMsg.A.no(i).schdCoordStsCd_A1.getValue()) || SCHD_COORD_STS.SCHEDULED.equals(sMsg.A.no(i).schdCoordStsCd_A1.getValue()) || SCHD_COORD_STS.RE_SCHEDULED.equals(sMsg.A.no(i).schdCoordStsCd_A1.getValue())) {

                if (!ZYPCommonFunc.hasValue(sMsgALine.schdDelyTsDplyTxt_A2) && !ZYPCommonFunc.hasValue(sMsgALine.rqstRcvDtTxt_S2) && ZYPConstant.FLG_ON_Y.equals(sMsgALine.techMeetTruckFlg_A1.getValue())) {
                    sMsgALine.schdDelyDt_A1.setErrorInfo(2, NLBL3070Constant.NMAM8163W, new String[] {"Tech Meet The Truck", "Time Stop" });
                    sMsgALine.schdDelyTsDplyTxt_A2.setErrorInfo(2, NLBL3070Constant.NMAM8163W, new String[] {"Tech Meet The Truck", "Time Stop" });
                    sMsgALine.rqstRcvDtTxt_S2.setErrorInfo(2, NLBL3070Constant.NMAM8163W, new String[] {"Tech Meet The Truck", "Time Stop" });
                    hasWrng = true;
                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }
            }
            // END 2020/02/04 [QC#50121, ADD]

            if (NLBL3070CommonLogic.changedValueSchdLine(sMsgALine)) {

                boolean carrChanged = false;

                if (ZYPCommonFunc.hasValue(sMsgALine.carrCd_A1)) {

                    if (!sMsgALine.carrCd_A1.getValue().equals(sMsgALine.carrCd_BK.getValue())) {

                        // Changed
                        carrChanged = true;
                    }

                } else if (ZYPCommonFunc.hasValue(sMsgALine.carrCd_BK)) {

                    // Changed
                    carrChanged = true;
                }

                if (!carrChanged) {

                    continue;

                } else if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.sendRqstFlg_A1.getValue())) {

                    sMsgALine.carrNm_A1.setErrorInfo(2, NLBL3070Constant.NLBM1327W);
                    hasWrng = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                } else if (ZYPCommonFunc.hasValue(sMsgALine.carrCd_OD) && !sMsgALine.carrCd_OD.getValue().equals(sMsgALine.carrCd_A1.getValue())) {

                    sMsgALine.carrNm_A1.setErrorInfo(2, NLBL3070Constant.NLBM1301W, new String[] {"Carrier", "assigned by order" });
                    hasWrng = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }

                // Carrier Pickup Date
                if (ZYPCommonFunc.hasValue(sMsgALine.schdCarrPickUpDt_A1)) {

                    if (!whCalTpMap.containsKey(sMsgALine.rtlWhCd_A1.getValue())) {

                        CAL_RELNTMsg calRelnTMsg = new CAL_RELNTMsg();
                        ZYPEZDItemValueSetter.setValue(calRelnTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(calRelnTMsg.calSubTpCd, CAL_SUB_TP.WAREHOUSE_CALENDAR);
                        ZYPEZDItemValueSetter.setValue(calRelnTMsg.calMultCd, sMsgALine.rtlWhCd_A1.getValue());
                        calRelnTMsg = (CAL_RELNTMsg) EZDTBLAccessor.findByKey(calRelnTMsg);

                        if (calRelnTMsg != null && ZYPCommonFunc.hasValue(calRelnTMsg.calTpCd)) {

                            whCalTpMap.put(sMsgALine.rtlWhCd_A1.getValue(), calRelnTMsg.calTpCd.getValue());

                        } else {

                            whCalTpMap.put(sMsgALine.rtlWhCd_A1.getValue(), cMsg.glblCmpyCd.getValue());
                        }
                    }

                    if (!ZYPDateUtil.isBusinessDay(whCalTpMap.get(sMsgALine.rtlWhCd_A1.getValue()), sMsgALine.schdCarrPickUpDt_A1.getValue())) {

                        sMsgALine.schdCarrPickUpDt_A1.setErrorInfo(2, NLBL3070Constant.NLCM0092W);
                        hasWrng = true;

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }
                    }
                }
            }

            /**
             * QC#15145 Enable Carrier Account# 11/21/2016 T.Tokutomi
             * Start
             **/
            // Check Carrier Account
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).carrCd_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).carrAcctNum_A1)) {

                if (NLBL3070CommonLogic.changedValue(sMsg.A.no(i).carrCd_A1.getValue(), sMsg.A.no(i).carrCd_BK.getValue())) {

                    BigDecimal acctCarrCnt = NLBL3070CommonLogic.getAcctCarrCnt(cMsg, sMsg.A.no(i).carrCd_A1.getValue(), sMsg.A.no(i).dsAcctNum_A1.getValue(), sMsg.A.no(i).carrAcctNum_A1.getValue());

                    if (acctCarrCnt == null || acctCarrCnt.intValue() == 0) {

                        sMsg.A.no(i).carrNm_A1.setErrorInfo(2, NLBL3070Constant.NLBM1328W, new String[] {"Carrier", "Carrier Account#" });
                        sMsg.A.no(i).carrAcctNum_A1.setErrorInfo(2, NLBL3070Constant.NLBM1328W, new String[] {"Carrier", "Carrier Account#" });
                        hasWrng = true;
                    }
                }
            }
            /**
             * QC#15145 Enable Carrier Account# 11/21/2016 T.Tokutomi
             * END
             **/

            // QC#23726 07/02/2018 Default Carrier Service Level Check.
            if (ZYPCommonFunc.hasValue(sMsgALine.carrCd_A1) //
                    && ZYPCommonFunc.hasValue(sMsgALine.shpgSvcLvlCd_A1)
                    // START 2021/05/31 A.Marte [QC#58786, ADD]
                    // Skip checking for non-CPO order Types
                    && isCPOType(sMsgALine.sceOrdTpCd_A1.getValue())) {
                    // END 2021/05/31 A.Marte [QC#58786, ADD]
                if (NLBL3070CommonLogic.checkCustCarrSvcLvlRelation(//
                        cMsg, sMsgALine.trxHdrNum_A1.getValue(), sMsgALine.shpgSvcLvlCd_A1.getValue(), sMsgALine.carrCd_A1.getValue(), sMsgALine.dsAcctNum_A1.getValue())) {
                    hasWrng = true;
                    sMsgALine.carrCd_A1.setErrorInfo(2, NLBL3070Constant.NLBM1369W, new String[] {"Carrier Assigned", "Service Level" });
                    sMsgALine.shpgSvcLvlCd_A1.setErrorInfo(2, NLBL3070Constant.NLBM1369W, new String[] {"Carrier Assigned", "Service Level" });
                }
            }
        }

        if (hasWrng) {

            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            return false;
        }

        return true;
    }

    // START 2021/05/31 A.Marte [QC#58786, ADD]
    /**
     * isCPOType
     * @param String lineSceOrdTpCd
     * @return boolean
     */
    private boolean isCPOType(String lineSceOrdTpCd) {

        boolean isCPOFlg = true;
        /* If SCE_ORD_TP_CD is DISPOSAL/VENDOR RETURN treat as non-CPO order type */
        if (SCE_ORD_TP.DISPOSAL.equals(lineSceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(lineSceOrdTpCd)){
            isCPOFlg = false;
        }

        return isCPOFlg;
    }
    // END 2021/05/31 A.Marte [QC#58786, ADD]


    /**
     * inputCheckReleaseSo
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return boolean
     */
    private boolean inputCheckReleaseSo(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        boolean chkBoxOn = false;
        boolean hasErr = false;
        boolean firstEvent = false;
        int firstErrIdx = -1;

        if (!ZYPCommonFunc.hasValue(cMsg.xxMntEventNm) || !cMsg.xxMntEventNm.getValue().equals("NLBL3070Scrn00_Release_SO")) {

            ZYPEZDItemValueSetter.setValue(cMsg.xxMntEventNm, "NLBL3070Scrn00_Release_SO");
            firstEvent = true;
            cMsg.xxWrnSkipFlg.clear();
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLBL3070_ASMsg sMsgALine = sMsg.A.no(i);

            if (firstEvent) {

                NLBL3070CommonLogic.initSchdPrevVal(sMsgALine);
            }

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A1.getValue())) {

                chkBoxOn = true;

                if (NLBL3070CommonLogic.changedValueSchdLinePrev(sMsgALine)) {

                    cMsg.xxWrnSkipFlg.clear();
                }

                // Backup for warning check
                NLBL3070CommonLogic.setPrevDataForWrngChk(sMsgALine);

                // Not Open Status
                if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.openFlg_A1.getValue())) {

                    sMsgALine.xxChkBox_A1.setErrorInfo(1, NLBL3070Constant.NLBM1307E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // SHPG_ORD Timestamp Check
                if (!NLBL3070CommonLogic.chkDsShpgOrdTimeStamp(cMsg.glblCmpyCd.getValue(), sMsgALine)) {

                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // SHPG_ORD
                SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soNum, sMsgALine.soNum_A1.getValue());

                shpgOrdTMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(shpgOrdTMsg);

                if (shpgOrdTMsg == null) {

                    sMsgALine.xxChkBox_A1.setErrorInfo(1, NLBL3070Constant.NLBM0009E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // Check time stamp (SHPG_ORD)
                if (!ZYPDateUtil.isSameTimeStamp(sMsgALine.ezUpTime_SO.getValue(), sMsgALine.ezUpTimeZone_SO.getValue(), shpgOrdTMsg.ezUpTime.getValue(), shpgOrdTMsg.ezUpTimeZone.getValue())) {

                    // anyone update
                    sMsgALine.xxChkBox_A1.setErrorInfo(1, NLBL3070Constant.NLBM0009E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }
            }
        }

        if (!chkBoxOn) {

            hasErr = true;

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).openFlg_A1.getValue())) {

                    sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NLBL3070Constant.NLBM0002E);

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }
            }
        }

        if (hasErr) {

            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            return false;
        }

        return true;
    }

    /**
     * inputCheckSendRqst
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return boolean
     */
    private boolean inputCheckSendRqst(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        boolean chkBoxOn = false;
        boolean hasErr = false;
        boolean firstEvent = false;
        int firstErrIdx = -1;

        if (!ZYPCommonFunc.hasValue(cMsg.xxMntEventNm) || !cMsg.xxMntEventNm.getValue().equals("NLBL3070Scrn00_Send_Rqst")) {

            ZYPEZDItemValueSetter.setValue(cMsg.xxMntEventNm, "NLBL3070Scrn00_Send_Rqst");
            firstEvent = true;
            cMsg.xxWrnSkipFlg.clear();
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLBL3070_ASMsg sMsgALine = sMsg.A.no(i);

            if (firstEvent) {

                NLBL3070CommonLogic.initSchdPrevVal(sMsgALine);
            }

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A1.getValue())) {

                chkBoxOn = true;

                if (NLBL3070CommonLogic.changedValueSchdLinePrev(sMsgALine)) {

                    cMsg.xxWrnSkipFlg.clear();
                }

                // Backup for warning check
                NLBL3070CommonLogic.setPrevDataForWrngChk(sMsgALine);

                // Not Open Status
                if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.openFlg_A1.getValue())) {

                    sMsgALine.xxChkBox_A1.setErrorInfo(1, NLBL3070Constant.NLBM1307E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;

                    // Not Target Carrier
                } else if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.manSendRqstFlg_AW.getValue()) && !ZYPConstant.FLG_ON_Y.equals(sMsgALine.manSendRqstFlg_AC.getValue())) {

                    sMsgALine.xxChkBox_A1.setErrorInfo(1, NLBL3070Constant.NLBM1311E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // SHPG_ORD Timestamp Check
                if (!NLBL3070CommonLogic.chkDsShpgOrdTimeStamp(cMsg.glblCmpyCd.getValue(), sMsgALine)) {

                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }
            }
        }

        if (hasErr) {

            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            return false;
        }

        if (!chkBoxOn) {

            cMsg.setMessageInfo(NLBL3070Constant.NLBM0002E);
            return false;
        }

        return true;
    }

    /**
     * inputCheckSoCancel
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return boolean
     */
    private boolean inputCheckSoCancel(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        int firstErrIdx = -1;
        boolean hasErr = false;
        boolean chkBoxOn = false;
        boolean chkHdrTimeStamp = false;

        HashSet<String> soNumSet = new HashSet<String>();
        HashSet<String> soNumCpoLineSet = new HashSet<String>();
        HashSet<String> soNumConfigSet = new HashSet<String>();
        HashSet<String> soNumSetForChkTm = new HashSet<String>();

        // For PI Check
        HashSet<String> piErrSoNumList = new HashSet<String>();
        HashSet<String> notPiSoNumList = new HashSet<String>();

        // Role Function
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL3070Constant.BUSINESS_ID);
        String userId = getContextUserInfo().getUserId();

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B2.getValue())) {

                chkBoxOn = true;

                // PI Activity Status Error
                if (piErrSoNumList.contains(sMsg.B.no(i).soNum_B1.getValue())) {

                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM1347E);
                    hasErr = true;
                    continue;
                }

                // PI Activity Check
                if (!notPiSoNumList.contains(sMsg.B.no(i).soNum_B1.getValue())) {

                    if (!NLBL3070CommonLogic.chkPiActivity(cMsg.glblCmpyCd.getValue(), sMsg.B.no(i).soNum_B1.getValue())) {

                        piErrSoNumList.add(sMsg.B.no(i).soNum_B1.getValue());
                        sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM1347E);
                        hasErr = true;

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }

                        continue;
                    }

                    notPiSoNumList.add(sMsg.B.no(i).soNum_B1.getValue());
                }

                // Check time stamp
                if (soNumSetForChkTm.isEmpty() || !soNumSetForChkTm.contains(sMsg.B.no(i).soNum_B1.getValue())) {

                    chkHdrTimeStamp = true;
                    soNumSetForChkTm.add(sMsg.B.no(i).soNum_B1.getValue());

                } else {

                    chkHdrTimeStamp = false;
                }

                if (!NLBL3070CommonLogic.checkTimeStampSoLine(sMsg.B.no(i), cMsg.glblCmpyCd.getValue(), chkHdrTimeStamp)) {

                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM0009E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // WH Permission check
                if (!NLBL3070CommonLogic.hasWarehousePermission(cMsg, sMsg.B.no(i).rtlWhCd_B1.getValue(), userId, functionList)) {
                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLZM2313E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // Check Open Status
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).soLineOpenFlg_B1.getValue())) {

                    // Not Open Status
                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLZM2283E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // Check So Cancel Available
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).soScrCancAvalFlg_B1.getValue())) {

                    S21SsmEZDResult ssmResult = NLBL3070Query.getInstance().getSoCancelAvalFlg(sMsg.B.no(i), cMsg.glblCmpyCd.getValue());

                    if (ssmResult.getQueryResultCount() == 0) {

                        sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM1352E);

                    } else {

                        Map<String, String> soCancelAvalMap = (Map<String, String>) ssmResult.getResultObject();

                        sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM1351E, new String[] {soCancelAvalMap.get("WH_SYS_TP_DESC_TXT") });
                    }

                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // Check Partial
                if (ZYPConstant.FLG_OFF_N.equals(sMsg.B.no(i).shipAvalFlg_LS.getValue())) {

                    boolean chkOffPartial = false;

                    if (!soNumSet.contains(sMsg.B.no(i).soNum_B1.getValue())) {

                        chkOffPartial = NLBL3070CommonLogic.checkPartial(sMsg, sMsg.B.no(i), cMsg.glblCmpyCd.getValue());
                        soNumSet.add(sMsg.B.no(i).soNum_B1.getValue());
                    }

                    if (chkOffPartial) {

                        sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM1309E);
                        hasErr = true;

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }
                    }
                }

                // Check Set Component
                boolean chkOnSet = true;

                if (!soNumCpoLineSet.contains(sMsg.B.no(i).soNum_B1.getValue() + sMsg.B.no(i).trxLineNum_B1.getValue())) {

                    chkOnSet = NLBL3070CommonLogic.checkSetComponent(sMsg, sMsg.B.no(i), cMsg.glblCmpyCd.getValue());
                    soNumCpoLineSet.add(sMsg.B.no(i).soNum_B1.getValue() + sMsg.B.no(i).trxLineNum_B1.getValue());
                }

                if (!chkOnSet) {

                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM1312E);
                    hasErr = true;

                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                    }
                }

                // Check Configuration Id
                boolean chkOffConfigId = false;

                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).pickSvcConfigMstrPk_B1) && !soNumConfigSet.contains(sMsg.B.no(i).soNum_B1.getValue() + String.valueOf(sMsg.B.no(i).pickSvcConfigMstrPk_B1.getValueInt()))) {

                    chkOffConfigId = NLBL3070CommonLogic.checkConfigId(sMsg, sMsg.B.no(i), cMsg.glblCmpyCd.getValue());
                    soNumConfigSet.add(sMsg.B.no(i).soNum_B1.getValue() + String.valueOf(sMsg.B.no(i).pickSvcConfigMstrPk_B1.getValueInt()));
                }

                if (chkOffConfigId) {

                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLZM2284E, new String[] {sMsg.B.no(i).pickSvcConfigMstrPk_B1.getValue().toString() });
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }
            }
        }

        // No line selected
        if (!chkBoxOn) {

            cMsg.setMessageInfo(NLBL3070Constant.NLBM0002E);
            return false;
        }

        if (hasErr) {

            NLBL3070CommonLogic.allExpansion(sMsg);
            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            return false;
        }

        return true;
    }

    /**
     * inputCheckShip
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return boolean
     */
    private boolean inputCheckShip(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        // QC#20043
        String actlDelyTm = ZYPDateUtil.getCurrentSystemTime("HHmmss");

        int firstErrIdx = -1;
        boolean hasErr = false;
        boolean chkBoxOn = false;
        boolean chkHdrTimeStamp = false;

        HashSet<String> soNumConfigSet = new HashSet<String>();
        HashSet<String> soNumCpoLineSet = new HashSet<String>();
        HashSet<String> soNumSet = new HashSet<String>();
        HashSet<String> soNumSetForChkTm = new HashSet<String>();

        // For PI Check
        HashSet<String> piErrSoNumList = new HashSet<String>();
        HashSet<String> notPiSoNumList = new HashSet<String>();

        // Role Function
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL3070Constant.BUSINESS_ID);
        String userId = getContextUserInfo().getUserId();

        // START 2022/04/04 R.Azucena [QC#59802, ADD]
        // START 2022/08/08 R.Azucena [QC#60416, DEL]
        // Map<String, BigDecimal> totalShipSingleQtyMap = getTotalShipSingleQtyMap(sMsg);
        // END 2022/08/08 R.Azucena [QC#60416, DEL]
        // END 2022/04/04 R.Azucena [QC#59802, ADD]

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B2.getValue())) {

                if (NLBL3070CommonLogic.changedValueDelvLinePrev(sMsg.B.no(i))) {

                    cMsg.xxWrnSkipFlg.clear();
                }

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).shipQty_BW, sMsg.B.no(i).xxShipQty_B1.getValue());
                if (sMsg.B.no(i).shipQty_BW.getValue().abs().intValue() == 1) {
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).serNum_BW, sMsg.B.no(i).serNum_B1.getValue());
                }
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).carrRsnCd_BW, sMsg.B.no(i).carrRsnCd_B1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).actlDelyDt_BW, sMsg.B.no(i).actlDelyDt_B1.getValue());
                // QC#18272 Mod. QC#20043
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).schdDelyTsDplyTxt_W2, actlDelyTm);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).carrNm_BW, sMsg.B.no(i).carrNm_B1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).carrCd_BW, sMsg.B.no(i).carrCd_B1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).bolNum_BW, sMsg.B.no(i).proNum_B1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).totFrtAmt_BW, sMsg.B.no(i).totFrtAmt_B1.getValue());

                chkBoxOn = true;

                // PI Activity Status Error
                if (piErrSoNumList.contains(sMsg.B.no(i).soNum_B1.getValue())) {

                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM1347E);
                    hasErr = true;
                    continue;
                }

                // PI Activity Check
                if (!notPiSoNumList.contains(sMsg.B.no(i).soNum_B1.getValue())) {

                    if (!NLBL3070CommonLogic.chkPiActivity(cMsg.glblCmpyCd.getValue(), sMsg.B.no(i).soNum_B1.getValue())) {

                        piErrSoNumList.add(sMsg.B.no(i).soNum_B1.getValue());
                        sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM1347E);
                        hasErr = true;

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }

                        continue;
                    }

                    notPiSoNumList.add(sMsg.B.no(i).soNum_B1.getValue());
                }

                // Check time stamp
                if (soNumSetForChkTm.isEmpty() || !soNumSetForChkTm.contains(sMsg.B.no(i).soNum_B1.getValue())) {

                    chkHdrTimeStamp = true;
                    soNumSetForChkTm.add(sMsg.B.no(i).soNum_B1.getValue());

                } else {

                    chkHdrTimeStamp = false;
                }

                if (!NLBL3070CommonLogic.checkTimeStampSoLine(sMsg.B.no(i), cMsg.glblCmpyCd.getValue(), chkHdrTimeStamp)) {

                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM0009E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // WH Permission check
                if (!NLBL3070CommonLogic.hasWarehousePermission(cMsg, sMsg.B.no(i).rtlWhCd_B1.getValue(), userId, functionList)) {
                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLZM2313E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // Check Open Status
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).soLineOpenFlg_B1.getValue())) {

                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLZM2283E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // Check Input Value
                // START 2022/04/04 R.Azucena [QC#59802, MOD]
                // START 2022/08/08 R.Azucena [QC#60416, MOD]
                if (!inputCheckValShip(cMsg, sMsg, sMsg.B.no(i))) {
                // String mapKey = getMapKey(sMsg.B.no(i));
                // if (!inputCheckValShip(cMsg, sMsg, sMsg.B.no(i), totalShipSingleQtyMap.get(mapKey))) {
                // END 2022/08/08 R.Azucena [QC#60416, MOD]
                // END 2022/04/04 R.Azucena [QC#59802, MOD]

                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // Check Pick Configuration Id
                boolean chkOffConfigId = false;

                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).pickSvcConfigMstrPk_B1) && !soNumConfigSet.contains(sMsg.B.no(i).soNum_B1.getValue() + String.valueOf(sMsg.B.no(i).pickSvcConfigMstrPk_B1.getValueInt()))) {

                    chkOffConfigId = NLBL3070CommonLogic.checkConfigId(sMsg, sMsg.B.no(i), cMsg.glblCmpyCd.getValue());
                    soNumConfigSet.add(sMsg.B.no(i).soNum_B1.getValue() + String.valueOf(sMsg.B.no(i).pickSvcConfigMstrPk_B1.getValueInt()));
                }

                if (chkOffConfigId) {

                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLZM2284E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }

                // Check Set Component Component
                boolean chkKitSet = true;

                if (!soNumCpoLineSet.contains(sMsg.B.no(i).soNum_B1.getValue() + sMsg.B.no(i).trxLineNum_B1.getValue())) {

                    chkKitSet = NLBL3070CommonLogic.checkSetComponent(sMsg, sMsg.B.no(i), cMsg.glblCmpyCd.getValue());
                    soNumCpoLineSet.add(sMsg.B.no(i).soNum_B1.getValue() + sMsg.B.no(i).trxLineNum_B1.getValue());
                }

                if (!chkKitSet) {

                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM1312E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }

                // Check Line Ship
                if (ZYPConstant.FLG_OFF_N.equals(sMsg.B.no(i).shipAvalFlg_LS.getValue())) {

                    boolean chkOffPartial = false;

                    if (!soNumSet.contains(sMsg.B.no(i).soNum_B1.getValue())) {

                        chkOffPartial = NLBL3070CommonLogic.checkPartial(sMsg, sMsg.B.no(i), cMsg.glblCmpyCd.getValue());
                        soNumSet.add(sMsg.B.no(i).soNum_B1.getValue());
                    }

                    if (chkOffPartial) {

                        sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLZM2315E, new String[] {"Ship" });
                        hasErr = true;

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }
                    }
                }
            }
        }

        // No line selected
        if (!chkBoxOn) {

            cMsg.setMessageInfo("NLBM0002E");
            return false;
        }

        if (hasErr) {

            // All Expansion
            NLBL3070CommonLogic.allExpansion(sMsg);
            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            return false;
        }

        return true;
    }

    /**
     * inputCheckValShip
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @param sMsgBLine NLBL3070_BSMsg
     * @param totalShipSingleQty BigDecimal
     * @return boolean
     */
    // START 2022/04/04 R.Azucena [QC#59802, MOD]
    // START 2022/08/08 R.Azucena [QC#60416, MOD]
    private boolean inputCheckValShip(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg, NLBL3070_BSMsg sMsgBLine) {
    // private boolean inputCheckValShip(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg, NLBL3070_BSMsg sMsgBLine, BigDecimal totalShipSingleQty) {
    // END 2022/08/08 R.Azucena [QC#60416, MOD]
    // END 2022/04/04 R.Azucena [QC#59802, MOD]

        boolean isChkNorm = true;

        // Ship Quantity
        if (!ZYPCommonFunc.hasValue(sMsgBLine.xxShipQty_B1)) {

            sMsgBLine.xxShipQty_B1.setErrorInfo(1, NLBL3070Constant.ZZM9000E, new String[] {"Ship Quantity" });
            isChkNorm = false;

        } else if (sMsgBLine.xxShipQty_B1.getValueInt() <= 0) {

            sMsgBLine.xxShipQty_B1.setErrorInfo(1, NLBL3070Constant.NLZM2277E, new String[] {"Ship Quantity", "0" });
            isChkNorm = false;

        } else if (sMsgBLine.shipQty_BK.getValueInt() < sMsgBLine.xxShipQty_B1.getValueInt()) {

            sMsgBLine.xxShipQty_B1.setErrorInfo(1, NLBL3070Constant.NLZM2316E, new String[] {"Ship Quantity", String.valueOf(sMsgBLine.shipQty_BK.getValueInt()) });
            isChkNorm = false;

        } else if (sMsgBLine.shipQty_BK.getValueInt() != sMsgBLine.xxShipQty_B1.getValueInt() && !ZYPConstant.FLG_ON_Y.equals(sMsgBLine.shipAvalFlg_PS.getValue())) {

            // If Line Partial is not available, Ship Confirm Quantity
            // <> Ship Quantity
            sMsgBLine.xxShipQty_B1.setErrorInfo(1, NLBL3070Constant.NLZM2280E, new String[] {"Ship Quantity", "Order Quantity" });
            isChkNorm = false;
        // START 2022/04/04 R.Azucena [QC#59802, ADD]
        // START 2022/08/08 R.Azucena [QC#60416, DEL]
        // } else if (SCE_ORD_TP.DIRECT_SALES.equals(sMsgBLine.sceOrdTpCd_B1.getValue()) && !ZYPCommonFunc.hasValue(sMsgBLine.serNum_B1)
                // START 2022/04/25 R.Azucena [QC#59802-1, ADD]
        //         && ZYPConstant.FLG_ON_Y.equals(sMsgBLine.instlBaseCtrlFlg_B1.getValue())
                // END 2022/04/25 R.Azucena [QC#59802-1, ADD]
        //         && getAvailSingleQty(sMsgBLine, cMsg.glblCmpyCd.getValue()).compareTo(totalShipSingleQty) < 0) {
        //     sMsgBLine.xxShipQty_B1.setErrorInfo(1, NLBL3070Constant.NLBM1376E);
        //     return false;
        // END 2022/08/08 R.Azucena [QC#60416, DEL]
        // END 2022/04/04 R.Azucena [QC#59802, ADD]
        } else if (ZYPCommonFunc.hasValue(sMsgBLine.xxQty10Num_B1.getValue()) && sMsgBLine.xxQty10Num_B1.getValue().compareTo(BigDecimal.ZERO) > 0) {

            if (sMsgBLine.xxShipQty_B1.getValueInt() % sMsgBLine.xxQty10Num_B1.getValueInt() > 0) {

                sMsgBLine.xxShipQty_B1.setErrorInfo(1, NLBL3070Constant.NLZM2330E);
                isChkNorm = false;
            }
        }

        // Check BOL Number
        if (SCE_ORD_TP.DIRECT_SALES.equals(sMsgBLine.sceOrdTpCd_B1.getValue()) && !ZYPCommonFunc.hasValue(sMsgBLine.proNum_B1)) {

            sMsgBLine.proNum_B1.setErrorInfo(1, NLBL3070Constant.ZZM9000E, new String[] {"Tracking Number" });
            isChkNorm = false;
        }

        // Carrier
        if (!ZYPCommonFunc.hasValue(sMsgBLine.carrNm_B1)) {

            sMsgBLine.carrCd_B1.clear();

            if (SCE_ORD_TP.DIRECT_SALES.equals(sMsgBLine.sceOrdTpCd_B1.getValue())) {

                sMsgBLine.carrNm_B1.setErrorInfo(1, NLBL3070Constant.ZZM9000E, new String[] {"Carrier" });
                isChkNorm = false;
            }

        } else {

            List<String> carrCdList = NLBL3070CommonLogic.getCarrCdList(cMsg.glblCmpyCd.getValue(), sMsgBLine.carrNm_B1.getValue());

            if (carrCdList == null || carrCdList.isEmpty()) {

                sMsgBLine.carrNm_B1.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[] {"Carrier" });
                sMsgBLine.carrCd_B1.clear();
                isChkNorm = false;

            } else if (carrCdList.size() > 1) {

                sMsgBLine.carrNm_B1.setErrorInfo(1, NLBL3070Constant.NLBM1341E, new String[] {"Carriers" });
                sMsgBLine.carrCd_B1.clear();
                isChkNorm = false;

            } else {

                ZYPEZDItemValueSetter.setValue(sMsgBLine.carrCd_B1, carrCdList.get(0));
            }

            // Carrier Relation Check
            if (ZYPCommonFunc.hasValue(sMsgBLine.carrCd_B1)) {

                // Check Shipping Service Level
                if (ZYPCommonFunc.hasValue(sMsgBLine.shpgSvcLvlCd_B1)) {

                    List<String> carrSvcLvlCdList = NLBL3070CommonLogic.getCarrSvcLvlCdList(cMsg, sMsgBLine.shpgSvcLvlCd_B1.getValue(), sMsgBLine.carrCd_B1.getValue());

                    if (carrSvcLvlCdList == null || carrSvcLvlCdList.isEmpty()) {

                        sMsgBLine.carrNm_B1.setErrorInfo(1, NLBL3070Constant.NLBM1308E, new String[] {"Service Level", "Carrier" });
                        sMsgBLine.shpgSvcLvlDescTxt_B1.setErrorInfo(1, NLBL3070Constant.NLBM1308E, new String[] {"Service Level", "Carrier" });
                        isChkNorm = false;
                    }
                }
            }
        }

        // Serial#
        if (!checkSerNum(sMsg, sMsgBLine, cMsg.glblCmpyCd.getValue())) {

            isChkNorm = false;
        }

        return isChkNorm;
    }

    /**
     * inputCheckWaringShip
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return boolean
     */
    private boolean inputCheckWaringShip(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        int firstErrIdx = -1;
        boolean hasWarning = false;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B2.getValue())) {

                boolean hasLineWarning = false;

                // BOL check
                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).proNum_B1)) {

                    BOLTMsg bolTMsg = new BOLTMsg();
                    ZYPEZDItemValueSetter.setValue(bolTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(bolTMsg.whCd, sMsg.B.no(i).rtlWhCd_B1);
                    ZYPEZDItemValueSetter.setValue(bolTMsg.bolNum, sMsg.B.no(i).proNum_B1);
                    bolTMsg = (BOLTMsg) EZDFastTBLAccessor.findByKey(bolTMsg);

                    if (bolTMsg != null) {

                        // Exist BOL
                        sMsg.B.no(i).proNum_B1.setErrorInfo(2, NLBL3070Constant.NLBM1298W, new String[] {"Tracking Number" });
                        hasLineWarning = true;
                    }

                    // Check BOL Duplication
                    String soNum = sMsg.B.no(i).soNum_B1.getValue();
                    String bolNum = sMsg.B.no(i).proNum_B1.getValue();
                    String rWH = sMsg.B.no(i).rtlWhCd_B1.getValue();

                    for (int b = 0; b < sMsg.B.getValidCount(); b++) {

                        NLBL3070_BSMsg sMsgBLineChk = sMsg.B.no(b);

                        if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLineChk.xxChkBox_B2.getValue())) {

                            if (!soNum.equals(sMsgBLineChk.soNum_B1.getValue()) && bolNum.equals(sMsgBLineChk.proNum_B1.getValue())) {

                                // Error
                                sMsg.B.no(i).proNum_B1.setErrorInfo(2, NLBL3070Constant.NLBM1304W, new String[] {"Tracking Number" });
                                hasLineWarning = true;
                            }

                            if (!rWH.equals(sMsgBLineChk.rtlWhCd_B1.getValue()) && bolNum.equals(sMsgBLineChk.proNum_B1.getValue())) {

                                // Error
                                sMsg.B.no(i).proNum_B1.setErrorInfo(2, NLBL3070Constant.NLBM1304W, new String[] {"Tracking Number" });
                                hasLineWarning = true;
                            }
                        }
                    }
                }

                // Carrier validation check
                if ((ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).manSendRqstFlg_BW.getValue()) || ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).manSendRqstFlg_BC.getValue())) && !ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).sendRqstFlg_B1.getValue())) {

                    // If need to send the request to carrier
                    sMsg.B.no(i).carrNm_B1.setErrorInfo(2, NLBL3070Constant.NLBM1273W);
                    hasLineWarning = true;
                }

                // Carrier check
                if (SCE_ORD_TP.DIRECT_SALES.equals(sMsg.B.no(i).sceOrdTpCd_B1.getValue())) {

                    if (ZYPCommonFunc.hasValue(sMsg.B.no(i).proNum_B1) && !ZYPCommonFunc.hasValue(sMsg.B.no(i).carrCd_B1)) {

                        sMsg.B.no(i).carrNm_B1.setErrorInfo(2, NLBL3070Constant.NLBM1300W, new String[] {"Carrier" });
                        hasLineWarning = true;
                    }
                }

                // Machine Master Check
                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).xxMsgId_B1)) {

                    sMsg.B.no(i).serNum_B1.setErrorInfo(2, sMsg.B.no(i).xxMsgId_B1.getValue());
                    hasLineWarning = true;
                }

                // QC#23726 07/02/2018 Default Carrier Service Level Check.
                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).carrCd_B1) //
                        && ZYPCommonFunc.hasValue(sMsg.B.no(i).shpgSvcLvlCd_B1)
                        // START 2021/05/31 A.Marte [QC#58786, ADD]
                        && isCPOType(sMsg.B.no(i).sceOrdTpCd_B1.getValue())) {
                        // END 2021/05/31 A.Marte [QC#58786, ADD]

                    if (NLBL3070CommonLogic.checkCustCarrSvcLvlRelation(//
                            cMsg, sMsg.B.no(i).trxHdrNum_B1.getValue(), sMsg.B.no(i).shpgSvcLvlCd_B1.getValue(), sMsg.B.no(i).carrCd_B1.getValue(), sMsg.B.no(i).dsAcctNum_B1.getValue())) {
                        hasLineWarning = true;
                        sMsg.B.no(i).carrNm_B1.setErrorInfo(2, NLBL3070Constant.NLBM1369W, new String[] {"Carrier", "Service Level" });
                    }
                }

                // START 2022/12/09 T.Kuroda [QC#60810, ADD]
                // WMS Warehouse check
                if (NLBL3070CommonLogic.isWMSWarehouseCheck(cMsg.glblCmpyCd.getValue(), sMsg.B.no(i).rtlWhCd_B1.getValue())) {
                    hasLineWarning = true;
                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(2, NLBL3070Constant.NLBM1378W);
                }
                // END   2022/12/09 T.Kuroda [QC#60810, ADD]

                if (hasLineWarning) {

                    hasWarning = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }
            }
        }

        if (hasWarning) {

            // All Expansion
            NLBL3070CommonLogic.allExpansion(sMsg);
            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            return false;
        }

        return true;
    }

    /**
     * inputCheckSoClose
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean inputCheckSoClose(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        int firstErrIdx = -1;
        boolean hasErr = false;

        // Check count checking-On
        boolean chkBoxOn = false;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B1.getValue())) {

                chkBoxOn = true;
            }
        }

        if (!chkBoxOn) {

            cMsg.setMessageInfo(NLBL3070Constant.NLBM0002E);
            return false;
        }

        // Check time stamp
        if (!checkTimeStampSoHdr(cMsg, sMsg)) {

            return false;
        }

        // For PI Check
        HashSet<String> piErrSoNumList = new HashSet<String>();
        HashSet<String> notPiSoNumList = new HashSet<String>();

        // Role Function
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL3070Constant.BUSINESS_ID);
        String userId = getContextUserInfo().getUserId();

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B1.getValue())) {

                // WH Permission check
                if (!NLBL3070CommonLogic.hasWarehousePermission(cMsg, sMsg.B.no(i).rtlWhCd_B1.getValue(), userId, functionList)) {
                    sMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, NLBL3070Constant.NLZM2313E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // PI Activity Status Error
                if (piErrSoNumList.contains(sMsg.B.no(i).soNum_B1.getValue())) {

                    sMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, NLBL3070Constant.NLBM1347E);
                    hasErr = true;
                    continue;
                }

                // PI Activity Check
                if (!notPiSoNumList.contains(sMsg.B.no(i).soNum_B1.getValue())) {

                    if (!NLBL3070CommonLogic.chkPiActivity(cMsg.glblCmpyCd.getValue(), sMsg.B.no(i).soNum_B1.getValue())) {

                        piErrSoNumList.add(sMsg.B.no(i).soNum_B1.getValue());
                        sMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, NLBL3070Constant.NLBM1347E);
                        hasErr = true;

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }

                        continue;
                    }

                    notPiSoNumList.add(sMsg.B.no(i).soNum_B1.getValue());
                }

                // Check Open Status by SO Header
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).openFlg_B1.getValue())) {

                    sMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, NLBL3070Constant.NLZM2283E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }

                // Check SO Line Status
                SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
                shpgOrdDtlTMsg.setSQLID("002");
                shpgOrdDtlTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
                shpgOrdDtlTMsg.setConditionValue("soNum01", sMsg.B.no(i).soNum_B1.getValue());
                SHPG_ORD_DTLTMsgArray shpgOrdDtlList = (SHPG_ORD_DTLTMsgArray) EZDTBLAccessor.findByCondition(shpgOrdDtlTMsg);
                boolean existShipped = false;

                for (int j = 0; j < shpgOrdDtlList.getValidCount(); j++) {

                    shpgOrdDtlTMsg = shpgOrdDtlList.no(j);

                    // Not enable to Line ship/cancel
                    if (ZYPConstant.FLG_OFF_N.equals(sMsg.B.no(i).shipAvalFlg_LS.getValue())) {

                        if (SHPG_STS.SHIPPED.equals(shpgOrdDtlTMsg.shpgStsCd.getValue())) {

                            sMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, NLBL3070Constant.NLZM2315E, new String[] {"ship/cancel" });
                            hasErr = true;

                            if (firstErrIdx == -1) {

                                firstErrIdx = i;
                            }

                        } else if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.shipQty) && 0 < shpgOrdDtlTMsg.shipQty.getValueInt()) {

                            sMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, NLBL3070Constant.NLZM2315E, new String[] {"ship/cancel" });
                            hasErr = true;

                            if (firstErrIdx == -1) {

                                firstErrIdx = i;
                            }
                        }
                    }

                    if (SHPG_STS.SHIPPED.equals(shpgOrdDtlTMsg.shpgStsCd.getValue())) {

                        existShipped = true;

                    } else if (!ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.shipQty) || 0 == shpgOrdDtlTMsg.shipQty.getValueInt()) {

                        existShipped = true;
                    }
                }

                // Check Shipped Count
                if (!existShipped) {

                    // Not ship any line
                    sMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, NLBL3070Constant.NLBM1314E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }
            }
        }

        if (hasErr) {

            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            return false;
        }

        return true;
    }

    /**
     * inputCheckDeliveryConf
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean inputCheckDeliveryConf(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        // QC#20043
        String actlDelyTm = ZYPDateUtil.getCurrentSystemTime("HHmmss");

        int firstErrIdx = -1;
        boolean hasErr = false;
        boolean chkBoxOn = false;
        boolean chkHdrTimeStamp = false;

        HashSet<String> soNumSet = new HashSet<String>();
        HashMap<String, String> rwhBolDelyDtMap = new HashMap<String, String>();

        String slsDt = ZYPDateUtil.getSalesDate();

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B2.getValue())) {

                chkBoxOn = true;

                // Check SO Line Status
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).shipFlg_B1.getValue())) {

                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLZM2283E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }

                // Not Changed
                if (!NLBL3070CommonLogic.changedValue(sMsg.B.no(i).carrRsnCd_B1.getValue(), sMsg.B.no(i).carrRsnCd_BK.getValue())
                        && !NLBL3070CommonLogic.changedValue(sMsg.B.no(i).actlDelyDt_B1.getValue(), sMsg.B.no(i).actlDelyDt_BK.getValue())) { //QC#18272 Mod.

                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM1303E, new String[] {"Selected Line" });
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // Check Time Stamp
                if (soNumSet.isEmpty() || !soNumSet.contains(sMsg.B.no(i).soNum_B1.getValue())) {

                    chkHdrTimeStamp = true;
                    soNumSet.add(sMsg.B.no(i).soNum_B1.getValue());

                } else {

                    chkHdrTimeStamp = false;
                }

                if (!NLBL3070CommonLogic.checkTimeStampSoLine(sMsg.B.no(i), cMsg.glblCmpyCd.getValue(), chkHdrTimeStamp)) {

                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM0009E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    continue;
                }

                // Check Retail WH/BOL# and Actual Delivery Date
                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).proNum_B1)) {

                    String key = ZYPCommonFunc.concatString(sMsg.B.no(i).rtlWhCd_B1.getValue(), ":", sMsg.B.no(i).proNum_B1.getValue());
                    // QC#18272 Mod. QC#20043
                    String actlDelyDtTm = ZYPCommonFunc.concatString(sMsg.B.no(i).actlDelyDt_B1.getValue(), ":", actlDelyTm);

                    if (rwhBolDelyDtMap.containsKey(key)) {

                        if (!actlDelyDtTm.equals(rwhBolDelyDtMap.get(key))) {

                            // Error
                            sMsg.B.no(i).actlDelyDt_B1.setErrorInfo(1, NLBL3070Constant.NLBM1315E);
                            hasErr = true;

                            if (firstErrIdx == -1) {

                                firstErrIdx = i;
                            }
                        }

                    } else {

                        rwhBolDelyDtMap.put(key, actlDelyDtTm);
                    }
                }

                // Carrier Reason
                String actlConfFlg = ZYPConstant.FLG_OFF_N;
                String carrRsnDescTxt = null;

                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).carrRsnCd_B1)) {

                    CARR_RSNTMsg carrRsnTMsg = new CARR_RSNTMsg();
                    ZYPEZDItemValueSetter.setValue(carrRsnTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(carrRsnTMsg.carrRsnCd, sMsg.B.no(i).carrRsnCd_B1.getValue());

                    carrRsnTMsg = (CARR_RSNTMsg) S21ApiTBLAccessor.findByKey(carrRsnTMsg);

                    if (carrRsnTMsg == null) {

                        sMsg.B.no(i).carrRsnCd_B1.setErrorInfo(1, NLBL3070Constant.NLZM2390E);
                        hasErr = true;

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }

                    } else {

                        actlConfFlg = carrRsnTMsg.actlConfFlg.getValue();
                        carrRsnDescTxt = carrRsnTMsg.carrRsnDescTxt.getValue();
                    }
                }

                boolean isSameShipAndDelyDt = false;

                // Actual Delivery Date is Blank
                if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).actlDelyDt_B1.getValue())) {

                    // Already Arrived
                    if (ZYPCommonFunc.hasValue(sMsg.B.no(i).actlDelyDt_BK.getValue())) {

                        sMsg.B.no(i).actlDelyDt_B1.setErrorInfo(1, NLBL3070Constant.NLBM1350E);
                        hasErr = true;

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }

                        // Carrier Reason
                    } else if (ZYPConstant.FLG_ON_Y.equals(actlConfFlg)) {

                        if (ZYPCommonFunc.hasValue(carrRsnDescTxt)) {

                            sMsg.B.no(i).actlDelyDt_B1.setErrorInfo(1, NLBL3070Constant.NLBM1349E, new String[] {carrRsnDescTxt });

                        } else {

                            sMsg.B.no(i).actlDelyDt_B1.setErrorInfo(1, NLBL3070Constant.ZZM9000E, new String[] {"Actual Delivery Date" });
                        }

                        hasErr = true;

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }
                    }

                    // Check Actual Delivery Date
                } else {

                    if (ZYPCommonFunc.hasValue(sMsg.B.no(i).shipDtTmTs_B1)) {

                        String shipDt = sMsg.B.no(i).shipDtTmTs_B1.getValue().substring(0, 8);

                        if (ZYPDateUtil.compare(shipDt, sMsg.B.no(i).actlDelyDt_B1.getValue()) > 0) {

                            sMsg.B.no(i).actlDelyDt_B1.setErrorInfo(1, NLBL3070Constant.NLBM1316E);
                            hasErr = true;

                            if (firstErrIdx == -1) {

                                firstErrIdx = i;
                            }

                        } else if (ZYPDateUtil.compare(shipDt, sMsg.B.no(i).actlDelyDt_B1.getValue()) == 0) {

                            isSameShipAndDelyDt = true;
                        }
                    }

                    if (sMsg.B.no(i).actlDelyDt_B1.getValue().compareTo(slsDt) > 0) {

                        sMsg.B.no(i).actlDelyDt_B1.setErrorInfo(1, NLBL3070Constant.NLAM1087E);
                        hasErr = true;

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }
                    }
                }
            }
        }

        // No line selected
        if (!chkBoxOn) {

            cMsg.setMessageInfo(NLBL3070Constant.NLBM0002E);
            return false;
        }

        if (hasErr) {

            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            return false;
        }

        return true;
    }

    /**
     * checkSerNum
     * @param sMsg NLBL3070SMsg
     * @param sMsgBLine NLBL3070_BSMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean checkSerNum(NLBL3070SMsg sMsg, NLBL3070_BSMsg sMsgBLine, String glblCmpyCd) {

        if (ZYPConstant.FLG_ON_Y.equals(sMsgBLine.serNumTakeFlg_B1.getValue())) {

            // Mandatory Check
            if (sMsgBLine.xxShipQty_B1.getValueInt() == 1 && !ZYPCommonFunc.hasValue(sMsgBLine.serNum_B1)) {

                sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3070Constant.ZZM9000E, new String[] {"Ship Serial Number" });
                return false;

            } else if (1 < sMsgBLine.xxShipQty_B1.getValueInt()) {

                // Check Serial List
                int serCnt = 0;

                for (int i = 0; i < sMsg.S.getValidCount(); i++) {

                    NLBL3070_SSMsg sMsgSLine = sMsg.S.no(i);

                    if (sMsgSLine.soNum_S1.getValue().equals(sMsgBLine.soNum_B1.getValue()) && sMsgSLine.soSlpNum_S1.getValue().equals(sMsgBLine.soSlpNum_B1.getValue())) {

                        serCnt++;
                    }
                }

                if (serCnt == 0) {

                    sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3070Constant.ZZM9000E, new String[] {"Ship Serial Number" });
                    return false;
                }

                if (sMsgBLine.xxShipQty_B1.getValueInt() != serCnt) {

                    sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3070Constant.NLBM1317E, new String[] {"Ship Serial Number" });
                    return false;
                }
            }

            // Duplication Check
            if (ZYPCommonFunc.hasValue(sMsgBLine.serNum_B1) && sMsgBLine.xxShipQty_B1.getValue().abs().intValue() == 1) {

                // QC#18823 add
                // If QTY = 1
                if (!checkDuplicationSerNum(sMsgBLine.soNum_B1.getValue(), sMsgBLine.soSlpNum_B1.getValue(), sMsgBLine.serNum_B1.getValue(), sMsg, sMsgBLine.mdseCd_B1.getValue())) {

                    sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3070Constant.NLBM1313E);
                    return false;
                }

            } else {

                // If 1 < QTY
                for (int j = 0; j < sMsg.S.getValidCount(); j++) {

                    NLBL3070_SSMsg serLine = sMsg.S.no(j);

                    if (serLine.soNum_S1.getValue().equals(sMsgBLine.soNum_B1.getValue()) && serLine.soSlpNum_S1.getValue().equals(sMsgBLine.soSlpNum_B1.getValue())) {

                        // QC#18823 add
                        if (!checkDuplicationSerNum(serLine.soNum_S1.getValue(), serLine.soSlpNum_S1.getValue(), serLine.serNum_S1.getValue(), sMsg, serLine.mdseCd_S1.getValue())) {

                            sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3070Constant.NLBM1313E);
                            return false;
                        }
                    }
                }
            }

            // Check Machine Master
            if (ZYPCommonFunc.hasValue(sMsgBLine.serNum_B1) && sMsgBLine.xxShipQty_B1.getValue().abs().intValue() == 1) {

                if (!checkMachinMaster(sMsgBLine, sMsgBLine.serNum_B1.getValue(), glblCmpyCd)) {

                    return false;
                }

            } else {

                for (int j = 0; j < sMsg.S.getValidCount(); j++) {

                    NLBL3070_SSMsg serLine = sMsg.S.no(j);

                    if (serLine.soNum_S1.getValue().equals(sMsgBLine.soNum_B1.getValue()) && serLine.soSlpNum_S1.getValue().equals(sMsgBLine.soSlpNum_B1.getValue())) {

                        if (!checkMachinMaster(sMsgBLine, serLine.serNum_S1.getValue(), glblCmpyCd)) {

                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * checkDuplicationSerNum
     * @param soNum String
     * @param soSlpNum String
     * @param serNum String
     * @param sMsg sMsg
     * @return boolean
     */
    private boolean checkDuplicationSerNum(String soNum, String soSlpNum, String serNum, NLBL3070SMsg sMsg, String mdseCd) {

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            NLBL3070_BSMsg line = sMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(line.xxChkBox_B2.getValue())) {

                if (!line.soNum_B1.getValue().equals(soNum) || !line.soSlpNum_B1.getValue().equals(soSlpNum)) {

                    // QC#18823 add
                    if (line.serNum_B1.getValue().equals(serNum) && line.mdseCd_B1.getValue().equals(mdseCd)) {

                        // Duplication
                        return false;
                    }

                }

                // Check Serial List
                for (int j = 0; j < sMsg.S.getValidCount(); j++) {

                    NLBL3070_SSMsg serLine = sMsg.S.no(j);

                    if (!serLine.soNum_S1.getValue().equals(soNum) || !serLine.soSlpNum_S1.getValue().equals(soSlpNum)) {

                        // QC#18823 add
                        if (serLine.serNum_S1.getValue().equals(serNum) && line.mdseCd_B1.getValue().equals(mdseCd)) {

                            // Duplication
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * checkMachinMaster
     * @param sMsgBLine NLBL3070_BSMsg
     * @param serNum String
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean checkMachinMaster(NLBL3070_BSMsg sMsgBLine, String serNum, String glblCmpyCd) {

        // Asset Master
        if (INVTY_ACCT.ASSET.equals(sMsgBLine.invtyAcctCd_B1.getValue())) {

            DS_ASSET_MSTRTMsg dsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
            dsAssetMstrTMsg.setSQLID("003");
            dsAssetMstrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            dsAssetMstrTMsg.setConditionValue("serNum01", serNum);
            dsAssetMstrTMsg.setConditionValue("mdseCd01", sMsgBLine.mdseCd_B1.getValue());
            dsAssetMstrTMsg.setConditionValue("actvAssetFlg01", ZYPConstant.FLG_ON_Y);
            DS_ASSET_MSTRTMsgArray dsAssetMstrList = (DS_ASSET_MSTRTMsgArray) EZDTBLAccessor.findByCondition(dsAssetMstrTMsg);

            if (dsAssetMstrList == null || dsAssetMstrList.getValidCount() == 0) {

                // Not found DS Asset Master
                sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3070Constant.NLBM1290E, new String[] {"Asset" });
                return false;
            }
        }

        // Machine Master
        NLXC042001SerialInfo serialInfo = new NLXC042001SerialInfo();
        serialInfo.setGlblCmpyCd(glblCmpyCd);
        serialInfo.setDupChkCd(ZYPCodeDataUtil.getVarCharConstValue("SER_DUP_CHK_CD", glblCmpyCd));
        serialInfo.setSceOrdTpCd(sMsgBLine.sceOrdTpCd_B1.getValue());
        serialInfo.setMdseCd(sMsgBLine.mdseCd_B1.getValue());
        serialInfo.setSerNum(serNum);
        /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 START */
        // serialInfo.setShipFromLocCd(sMsgBLine.rtlWhCd_B1.getValue());
        serialInfo.setShipFromLocCd(sMsgBLine.invtyLocCd_B1.getValue());
        /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 END */
        serialInfo.setTrxHdrNum(sMsgBLine.trxHdrNum_B1.getValue());

        // START 2021/05/31 A.Marte [QC#58786, ADD]
        String invtyTrxHdrNum = sMsgBLine.trxHdrNum_B1.getValue();
        if (SCE_ORD_TP.DISPOSAL.equals(sMsgBLine.sceOrdTpCd_B1.getValue()) || SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(sMsgBLine.sceOrdTpCd_B1.getValue())){

            //if OrderType is Disposal/Vendor Type get INVTY TRX_HDR_NUM from SO
            invtyTrxHdrNum = NLBL3070CommonLogic.getInvtyTrxHdrNumFromSO(glblCmpyCd, sMsgBLine.soNum_B1.getValue());
            if (ZYPCommonFunc.hasValue(invtyTrxHdrNum)) {
                serialInfo.setTrxHdrNum(invtyTrxHdrNum);
            }
        }
        // END 2021/05/31 A.Marte [QC#587NLBL3086, ADD]

        serialInfo.setSoNum(sMsgBLine.soNum_B1.getValue());
        serialInfo.setOrdSvcConfigMstrPk(sMsgBLine.pickSvcConfigMstrPk_B1.getValue());
        serialInfo.setMdlId(sMsgBLine.mdlId_B1.getValue());
        serialInfo.setOnBatchType(ONBATCH_TYPE.ONLINE);

        // START 2022/05/13 A.Cullano [QC#59975, ADD]
        serialInfo.setStkStsCd(sMsgBLine.stkStsCd_B1.getValue());
        // END 2022/05/13 A.Cullano [QC#59975, ADD]

        serialInfo = NLXC042001SvcMachMstrCheck.chkSvcMachMstrForShipSerial(serialInfo);
        sMsgBLine.xxMsgId_B1.clear();

        if (ZYPCommonFunc.hasValue(serialInfo.getErrMsgId())) {

            if (serialInfo.getErrMsgId().endsWith("E")) {

                sMsgBLine.serNum_B1.setErrorInfo(1, serialInfo.getErrMsgId());
                return false;

            } else if (serialInfo.getErrMsgId().endsWith("W")) {

                ZYPEZDItemValueSetter.setValue(sMsgBLine.xxMsgId_B1, serialInfo.getErrMsgId());
            }
        }

        // QC#23174 SMM Check
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, serialInfo.getSvcMachMstrPk());
        tMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        // START 2021/06/14 A.Marte [QC#58786, MOD]
        if (tMsg != null && invtyTrxHdrNum.equals(tMsg.trxHdrNum.getValue()) && sMsgBLine.trxLineNum_B1.getValue().equals(tMsg.trxLineNum.getValue())) {
        // END 2021/06/14 A.Marte [QC#58786, MOD]
            return true;
        } else if (tMsg == null) {
            return true;
        } else if (!ZYPCommonFunc.hasValue(tMsg.trxHdrNum) && sMsgBLine.invtyLocCd_B1.getValue().equals(tMsg.curLocNum.getValue()) && sMsgBLine.stkStsCd_B1.getValue().equals(tMsg.stkStsCd.getValue())){
            return true;
        } else {
            sMsgBLine.serNum_B1.setErrorInfo(1, NLBL3070Constant.NLZM2317E);
            return false;
        }
    }

    /**
     * checkTimeStampSoHdr
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return
     */
    private boolean checkTimeStampSoHdr(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        int firstErrIdx = -1;
        boolean hasErr = false;

        // Check time stamp
        HashSet<String> soNumSet = new HashSet<String>();

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B1.getValue())) {

                // SHPG_ORD
                if (!soNumSet.contains(sMsg.B.no(i).soNum_B1.getValue())) {

                    // SHPG_ORD
                    SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
                    ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soNum, sMsg.B.no(i).soNum_B1);
                    shpgOrdTMsg = (SHPG_ORDTMsg) EZDFastTBLAccessor.findByKey(shpgOrdTMsg);

                    if (shpgOrdTMsg == null) {

                        sMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, NLBL3070Constant.NLBM0009E);
                        firstErrIdx = i;
                        hasErr = true;
                        break;
                    }

                    if (!ZYPDateUtil.isSameTimeStamp(sMsg.B.no(i).ezUpTime_SO.getValue(), sMsg.B.no(i).ezUpTimeZone_SO.getValue(), shpgOrdTMsg.ezUpTime.getValue(), shpgOrdTMsg.ezUpTimeZone.getValue())) {

                        // anyone update
                        cMsg.setMessageInfo(NLBL3070Constant.NLBM0009E);
                        firstErrIdx = i;
                        break;
                    }

                    soNumSet.add(sMsg.B.no(i).soNum_B1.getValue());
                }
            }
        }

        if (hasErr) {

            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            return false;
        }

        return true;
    }

    /**
     * createPMsgSoConfHdrSoCancel
     * @param glblCmpyCd String
     * @param cancelDate String
     * @param sMsgBLineHdr NLBL3070_BSMsg
     * @return NLZC210001PMsg
     */
    private NLZC210001PMsg createPMsgSoConfHdrSoCancel(String glblCmpyCd, String cancelDate, NLBL3070_BSMsg sMsgBLineHdr) {

        NLZC210001PMsg pmsg = new NLZC210001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmsg.whCd, sMsgBLineHdr.invtyLocCd_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, sMsgBLineHdr.soNum_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.sceOrdTpCd, sMsgBLineHdr.sceOrdTpCd_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.soProcStsCd, SO_PROC_STS.ORDER_VOID);
        ZYPEZDItemValueSetter.setValue(pmsg.shipDtTmTs, cancelDate);

        return pmsg;
    }

    /**
     * createPMsgSoConfDtlSoCancel
     * @param glblCmpyCd String
     * @param cancelDate String
     * @param sMsgBLineDtl NLBL3070_BSMsg
     * @return NLZC210001PMsg
     */
    private NLZC210001PMsg createPMsgSoConfDtlSoCancel(String glblCmpyCd, String cancelDate, NLBL3070_BSMsg sMsgBLineDtl) {

        NLZC210001PMsg pmsg = new NLZC210001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmsg.whCd, sMsgBLineDtl.invtyLocCd_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, sMsgBLineDtl.soNum_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.sceOrdTpCd, sMsgBLineDtl.sceOrdTpCd_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.soProcStsCd, SO_PROC_STS.LINE_VOID);
        ZYPEZDItemValueSetter.setValue(pmsg.shipDtTmTs, cancelDate);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, sMsgBLineDtl.soSlpNum_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.soProcStsCd_DT, SO_PROC_STS.LINE_VOID);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, sMsgBLineDtl.mdseCd_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.fromStkStsCd, sMsgBLineDtl.stkStsCd_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.shipQty, sMsgBLineDtl.xxShipQty_B1);

        return pmsg;
    }

    /**
     * createPMsgSoConfDtlShip
     * @param glblCmpyCd String
     * @param shipDateDate String
     * @param sMsgBLineDtl NLBL3070_BSMsg
     * @return NLZC210001PMsg
     */
    private NLZC210001PMsg createPMsgSoConfDtlShip(String glblCmpyCd, String shipDateDate, NLBL3070_BSMsg sMsgBLineDtl) {

        NLZC210001PMsg pmsg = new NLZC210001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmsg.whCd, sMsgBLineDtl.invtyLocCd_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, sMsgBLineDtl.soNum_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.sceOrdTpCd, sMsgBLineDtl.sceOrdTpCd_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.soProcStsCd, SO_PROC_STS.SHIP);
        ZYPEZDItemValueSetter.setValue(pmsg.shipDtTmTs, shipDateDate);
        ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, sMsgBLineDtl.soSlpNum_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.soProcStsCd_DT, SO_PROC_STS.SHIP);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, sMsgBLineDtl.mdseCd_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.fromStkStsCd, sMsgBLineDtl.stkStsCd_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.shipQty, sMsgBLineDtl.xxShipQty_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.bolNum, sMsgBLineDtl.proNum_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.proNum, sMsgBLineDtl.proNum_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.vndCd, sMsgBLineDtl.carrCd_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.totFrtAmt, sMsgBLineDtl.totFrtAmt_B1);

        return pmsg;
    }

    /**
     * createPMsgSoConfSerShip
     * @param glblCmpyCd String
     * @param shipDateDate String
     * @param sMsg NLBL3070SMsg
     * @param sMsgBLineDtl NLBL3070_BSMsg
     * @return List<NLZC210002PMsg>
     */
    private List<NLZC210002PMsg> createPMsgSoConfSerShip(String glblCmpyCd, String shipDateDate, NLBL3070SMsg sMsg, NLBL3070_BSMsg sMsgBLineDtl) {

        NLZC210002PMsg pmsg = null;
        List<NLZC210002PMsg> pmsgList = new ArrayList<NLZC210002PMsg>();

        if (ZYPCommonFunc.hasValue(sMsgBLineDtl.serNum_B1) && sMsgBLineDtl.xxShipQty_B1.getValue().abs().intValue() == 1) {

            pmsg = new NLZC210002PMsg();
            ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pmsg.whCd, sMsgBLineDtl.invtyLocCd_B1);
            ZYPEZDItemValueSetter.setValue(pmsg.soNum, sMsgBLineDtl.soNum_B1);
            ZYPEZDItemValueSetter.setValue(pmsg.sceOrdTpCd, sMsgBLineDtl.sceOrdTpCd_B1);
            ZYPEZDItemValueSetter.setValue(pmsg.soProcStsCd, SO_PROC_STS.SHIP);
            ZYPEZDItemValueSetter.setValue(pmsg.shipDtTmTs, shipDateDate);
            ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, sMsgBLineDtl.soSlpNum_B1);
            ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, sMsgBLineDtl.mdseCd_B1);
            ZYPEZDItemValueSetter.setValue(pmsg.serNum, sMsgBLineDtl.serNum_B1);
            ZYPEZDItemValueSetter.setValue(pmsg.serTakeDtTmTs, shipDateDate);
            pmsgList.add(pmsg);

        } else if (ZYPConstant.FLG_ON_Y.equals(sMsgBLineDtl.serNumTakeFlg_B1.getValue())) {

            for (int i = 0; i < sMsg.S.getValidCount(); i++) {

                if (sMsg.S.no(i).soNum_S1.getValue().equals(sMsgBLineDtl.soNum_B1.getValue()) && sMsg.S.no(i).soSlpNum_S1.getValue().equals(sMsgBLineDtl.soSlpNum_B1.getValue())) {

                    pmsg = new NLZC210002PMsg();
                    ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pmsg.whCd, sMsgBLineDtl.invtyLocCd_B1);
                    ZYPEZDItemValueSetter.setValue(pmsg.soNum, sMsgBLineDtl.soNum_B1);
                    ZYPEZDItemValueSetter.setValue(pmsg.sceOrdTpCd, sMsgBLineDtl.sceOrdTpCd_B1);
                    ZYPEZDItemValueSetter.setValue(pmsg.soProcStsCd, SO_PROC_STS.SHIP);
                    ZYPEZDItemValueSetter.setValue(pmsg.shipDtTmTs, shipDateDate);
                    ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, sMsgBLineDtl.soSlpNum_B1);
                    ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, sMsgBLineDtl.mdseCd_B1);
                    ZYPEZDItemValueSetter.setValue(pmsg.serNum, sMsg.S.no(i).serNum_S1);
                    ZYPEZDItemValueSetter.setValue(pmsg.serTakeDtTmTs, shipDateDate);
                    pmsgList.add(pmsg);
                }
            }

        } else {

            return null;
        }

        return pmsgList;
    }

    /**
     * createPMsgSoConfSoClose
     * @param glblCmpyCd String
     * @param closeDate String
     * @param sMsgBLineDtl NLBL3070_BSMsg
     * @return NLZC210001PMsg
     */
    private NLZC210001PMsg createPMsgSoConfSoClose(String glblCmpyCd, String closeDate, NLBL3070_BSMsg sMsgBLineDtl) {

        NLZC210001PMsg pmsg = new NLZC210001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmsg.whCd, sMsgBLineDtl.rtlWhCd_B1.getValue() + sMsgBLineDtl.rtlSwhCd_B1.getValue());
        ZYPEZDItemValueSetter.setValue(pmsg.soNum, sMsgBLineDtl.soNum_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.sceOrdTpCd, sMsgBLineDtl.sceOrdTpCd_B1);
        ZYPEZDItemValueSetter.setValue(pmsg.soProcStsCd, SO_PROC_STS.SHIP);
        ZYPEZDItemValueSetter.setValue(pmsg.shipDtTmTs, closeDate);
        return pmsg;
    }

    /**
     * getCancelDate
     * @return String
     */
    private String getCurrentDateTime() {

        String yyyymmdd = ZYPDateUtil.getSalesDate();
        String hhmmss = ZYPDateUtil.getCurrentSystemTime("HHmmss");

        return yyyymmdd + hhmmss;
    }

    /**
     * convSubString
     * @param pMsg EZDPMsg
     * @param itemName String
     * @param item EZDSStringItem
     * @return String
     */
    private static String convSubString(EZDPMsg pMsg, String itemName, EZDSStringItem item) {

        int itemSize = getItemSize(pMsg, "ediCnsgnAddr");
        String itemVal = "";

        if (ZYPCommonFunc.hasValue(item) && itemSize < item.getValue().length()) {

            itemVal = item.getValue().substring(0, itemSize);

        } else {

            itemVal = item.getValue();
        }

        return itemVal;
    }

    /**
     * getItemSize
     * @param pMsg EZDPMsg
     * @param itemName String
     * @return int
     */
    private static int getItemSize(EZDPMsg pMsg, String itemName) {

        EZDItemAttribute attr = pMsg.getAttr(itemName);

        if (attr != null) {

            return attr.getDigit();

        } else {

            return 0;
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_SaveSearch
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_SaveSearch(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {

            cMsg.srchOptNm_S.setErrorInfo(1, NLBL3070Constant.ZZM9000E, new String[] {converter.convLabel2i18nLabel(NLBL3070Constant.SCREEN_ID, "Search Option Name") });
            return;
        }

        if (NLBL3070CommonLogic.isExistSaveSearchName(cMsg)) {

            // You can not [@] this record Because of [@] already
            // exists.
            cMsg.srchOptNm_S.setErrorInfo(1, NLBL3070Constant.NLZM2273E, new String[] {converter.convLabel2i18nLabel(NLBL3070Constant.SCREEN_ID, "Save"), converter.convLabel2i18nLabel(NLBL3070Constant.SCREEN_ID, "Search Option Name") });
            return;
        }

        NLBL3070CommonLogic.callNszc0330forSaveSearch(cMsg, getContextUserInfo().getUserId(), cMsg.glblCmpyCd.getValue());
    }

    /**
     * doProcess_NLBL3070Scrn00_DeleteSearch
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_DeleteSearch(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {

            // [@] is not selected.
            cMsg.srchOptPk_S.setErrorInfo(1, NLBL3070Constant.NLZM2274E, new String[] {converter.convLabel2i18nLabel(NLBL3070Constant.SCREEN_ID, "Saved Search Options") });
            return;
        }

        NLBL3070CommonLogic.callNszc0330forDeleteSearch(cMsg, getContextUserInfo().getUserId(), cMsg.glblCmpyCd.getValue());
    }

    /**
     * insertShipOrdProNumList
     * QC#21913 Add method.
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @param String currentSoNum
     * @return true:success / false:error
     */
    private boolean insertShipOrdProNumList(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg, String currentSoNum){
        ArrayList<String> executeSoNum = new ArrayList<String>();
        HashMap<String, ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg>> insertList = new HashMap<String, ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg>>();

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NLBL3070_BSMsg bSMsgLine = sMsg.B.no(i);

            if (ZYPConstant.FLG_ON_Y.equals(bSMsgLine.xxChkBox_B2.getValue()) && currentSoNum.equals(bSMsgLine.soNum_B1.getValue())) {
                String soNum = bSMsgLine.soNum_B1.getValue();
                String sceOrdTpCd = bSMsgLine.sceOrdTpCd_B1.getValue();

                ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg> list = new ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg>();
                if (insertList.containsKey(soNum)) {
                    list = insertList.get(soNum);
                } else {
                    insertList.put(soNum, list);
                }

                if (!executeSoNum.contains(soNum)) {
                    for (int j = 0; j < sMsg.T.getValidCount(); j++) {
                        if (soNum.equals(sMsg.T.no(j).soNum_T1.getValue())) {
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
                ZYPEZDItemValueSetter.setValue(sopn.trxLineNum, bSMsgLine.soSlpNum_B1);
                ZYPEZDItemValueSetter.setValue(sopn.proNum, bSMsgLine.proNum_B1);
                ZYPEZDItemValueSetter.setValue(sopn.sceOrdTpCd, sceOrdTpCd);
                ZYPEZDItemValueSetter.setValue(sopn.inbdOtbdCd, INBD_OTBD.OUTBOUND);
                ZYPEZDItemValueSetter.setValue(sopn.proCratDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddhhmmss"));
                ZYPEZDItemValueSetter.setValue(sopn.proSendFlg, ZYPConstant.FLG_ON_Y);

                list.add(sopn);
            }
        }

        // sort
        for (Map.Entry<String, ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg>> entry : insertList.entrySet()) {
            Collections.sort(entry.getValue(), new NLBL3070Constant.Comp("proNum"));
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
                    cMsg.setMessageInfo(NLBL3070Constant.ZZMM0015E, new String[] {"Shipping Order Pro Number List", "Shipping Order Pro Number List Pk", tMsg.shpgOrdProNumListPk.getValue().toPlainString() });
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
     * @param sMsg NLBL3070SMsg
     * @return Map<String, BigDecimal>
     */
    // private Map<String, BigDecimal> getTotalShipSingleQtyMap(NLBL3070SMsg sMsg) {
    //     Map<String, BigDecimal> totalShipSingleQtyMap = new HashMap<String, BigDecimal>();

    //     for (int i = 0; i < sMsg.B.getValidCount(); i++) {
    //         NLBL3070_BSMsg sMsgBLine = sMsg.B.no(i);

            // Selected and Single Item
    //         if (ZYPConstant.FLG_ON_Y.equals(sMsgBLine.xxChkBox_B2.getValue()) && !ZYPCommonFunc.hasValue(sMsgBLine.serNum_B1)) {
    //             String key = getMapKey(sMsgBLine);
    //             totalShipSingleQtyMap.put(key, convertNullToZero(totalShipSingleQtyMap.get(key)).add(sMsgBLine.xxShipQty_B1.getValue()));
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
     * @param sMsgBLine NLBL3070_BSMsg
     * @return String
     */
    // private static String getMapKey(NLBL3070_BSMsg sMsgBLine) {
    //     return S21StringUtil.concatStrings(sMsgBLine.mdseCd_B1.getValue(),
    //             ":", sMsgBLine.stkStsCd_B1.getValue(),
    //             ":", sMsgBLine.rtlWhCd_B1.getValue(),
    //             ":", sMsgBLine.rtlSwhCd_B1.getValue(),
    //             ":", convertNullToZero(sMsgBLine.pickSvcConfigMstrPk_B1.getValue()).toString());
    // }

    /**
     * getAvailSingleQty
     * @param asMsg NLBL3070_BSMsg
     * @param glblCmpyCd String
     * @return BigDecimal
     */
    // private static BigDecimal getAvailSingleQty(NLBL3070_BSMsg sMsgBLine, String glblCmpyCd) {
    //     Map<String, Object> ssmParam = new HashMap<String, Object>();
    //     ssmParam.put(NLBL3070Constant.SSM_GLBL_CMPY_CD, glblCmpyCd);
    //     ssmParam.put(NLBL3070Constant.SSM_MDSE_CD, sMsgBLine.mdseCd_B1.getValue());
    //     ssmParam.put(NLBL3070Constant.SSM_STK_STS_CD, sMsgBLine.stkStsCd_B1.getValue());
    //     ssmParam.put(NLBL3070Constant.SSM_CUR_LOC_NUM, S21StringUtil.concatStrings(sMsgBLine.rtlWhCd_B1.getValue(), sMsgBLine.rtlSwhCd_B1.getValue()));
    //     ssmParam.put(NLBL3070Constant.SSM_SVC_MACH_MSTR_STS_CD_LIST, new String[] {SVC_MACH_MSTR_STS.CREATED, SVC_MACH_MSTR_STS.REMOVED });
    //     ssmParam.put(NLBL3070Constant.SSM_SVC_MACH_MAINT_AVAL_FLG, ZYPConstant.FLG_ON_Y);

    //     if (ZYPCommonFunc.hasValue(sMsgBLine.pickSvcConfigMstrPk_B1)) {
    //         ssmParam.put(NLBL3070Constant.SSM_PICK_SVC_CONFIG_MSTR_PK, sMsgBLine.pickSvcConfigMstrPk_B1.getValue());
    //     }

    //     S21SsmEZDResult ssmResult = NLBL3070Query.getInstance().getAvailSingleQty(ssmParam);
    //     return (BigDecimal) ssmResult.getResultObject();
    // }
    // END 2022/08/08 R.Azucena [QC#60416, DEL]
    // END 2022/04/04 R.Azucena [QC#59802, ADD]
}
