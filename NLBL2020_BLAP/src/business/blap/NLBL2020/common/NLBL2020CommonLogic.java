/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL2020.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL2020.NLBL2020CMsg;
import business.blap.NLBL2020.NLBL2020Query;
import business.blap.NLBL2020.NLBL2020SMsg;
import business.blap.NLBL2020.NLBL2020_ASMsg;
import business.blap.NLBL2020.NLBL2020_BSMsg;
import business.blap.NLBL2020.constant.NLBL2020Constant;
import business.db.CPOTMsg;
import business.db.DS_ASSET_MSTRTMsg;
import business.db.DS_SO_LINE_STSTMsg;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.OTBD_CARR_VTMsg;
import business.db.OTBD_CARR_VTMsgArray;
import business.db.RWS_DTLTMsg;
import business.db.RWS_DTLTMsgArray;
import business.db.RWS_HDRTMsg;
import business.db.SAVE_SRCH_OPTTMsg;
import business.db.SAVE_SRCH_OPTTMsgArray;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsgArray;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.parts.NLZC060001PMsg;
import business.parts.NLZC206001PMsg;
import business.parts.NLZC207001PMsg;
import business.parts.NLZC210001PMsg;
import business.parts.NLZC210002PMsg;
import business.parts.NLZC302001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC060001.NLZC060001;
import com.canon.cusa.s21.api.NLZ.NLZC060001.constant.NLZC060001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC302001.NLZC302001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC042001.NLXC042001SerialInfo;
import com.canon.cusa.s21.common.NLX.NLXC042001.NLXC042001SvcMachMstrCheck;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_ORD_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 05/04/2016   CSAI            Y.Imazu         Update          QC#7334
 * 06/30/2016   CSAI            K.Lee           Update          Configuration Change
 * 07/08/2016   CSAI            Y.Imazu         Update          QC#10232
 * 07/12/2016   CSAI            K.Lee           Update          QC#11767
 * 07/14/2016   CSAI            Y.Imazu         Update          QC#11900
 * 07/27/2016   CSAI            Y.Imazu         Update          QC#9621
 * 08/19/2016   CSAI            Y.Imazu         Update          QC#5876
 * 10/17/2016   CITS            K.Ogino         Update          QC#10406
 * 03/29/2017   CITS            R.Shimamoto     Update          QC#17922
 * 07/07/2017   CITS            Y.Imazu         Update          QC#19730
 * 07/21/2017   CITS            K.Ogino         UPDATE          QC#20008
 * 10/16/2017   CITS            T.Kikuhara      Update          QC#20246(Sol#454)
 * 03/13/2018   CITS            K.Ogino         Update          QC#18794
 * 06/19/2018   CITS            Y.Iwasaki       Update          QC#21717
 * 07/02/2018   CITS            T.Tokutomi      Update          QC#23726
 * 01/07/2020   Fujitsu         R.Nakamura      Update          QC#55308
 * 05/15/2020   Fujitsu         T.Ogura         Update          QC#56880
 * 04/04/2022   CITS            R.Azucena       Update          QC#59802
 * 04/25/2022   CITS            R.Azucena       Update          QC#59802-1
 * 08/08/2022   CITS            R.Azucena       Update          QC#60416
 * 2022/10/18   Hitachi         A.Kohinata      Update          QC#60559
 * 2022/12/07   Hitachi         T.Kuroda        Update          QC#60810
 *</pre>
 */
public class NLBL2020CommonLogic {

    /**
     * setPulldownSaveSearch
     * @param cMsg NLBL2020CMsg
     */
    public static void setPulldownSaveSearch(NLBL2020CMsg cMsg) {

        cMsg.srchOptPk_PD.clear();
        cMsg.srchOptNm_PD.clear();

        SAVE_SRCH_OPTTMsg saveSrchOptTMsg = new SAVE_SRCH_OPTTMsg();
        saveSrchOptTMsg.setSQLID("001");
        saveSrchOptTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        saveSrchOptTMsg.setConditionValue("srchOptAplId01", NLBL2020Constant.BUSINESS_ID);
        saveSrchOptTMsg.setConditionValue("srchOptUsrId01", cMsg.usrId.getValue());

        SAVE_SRCH_OPTTMsgArray saveSrchOptTMsgList = (SAVE_SRCH_OPTTMsgArray) EZDTBLAccessor.findByCondition(saveSrchOptTMsg);

        for (int i = 0; i < saveSrchOptTMsgList.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_PD.no(i), saveSrchOptTMsgList.no(i).srchOptPk);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_PD.no(i), saveSrchOptTMsgList.no(i).srchOptNm);
        }
    }

    /**
     * setPulldownOrderLineStatus
     * @param cMsg NLBL2020CMsg
     */
    public static void setPulldownOrderLineStatus(NLBL2020CMsg cMsg) {

        cMsg.dsSoLineStsCd_PD.clear();
        cMsg.dsSoLineStsDescTxt_PD.clear();

        ZYPCodeDataUtil.createPulldownList(NLBL2020Constant.TBL_DS_SO_LINE_STS, cMsg.dsSoLineStsCd_PD, cMsg.dsSoLineStsDescTxt_PD);
    }

    /**
     * setPulldownOrderLineStatus
     * @param cMsg NLBL2020CMsg
     */
    public static void setPulldownSceOrdType(NLBL2020CMsg cMsg) {

        cMsg.sceOrdTpCd_PD.clear();
        cMsg.sceOrdTpNm_PD.clear();

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        param.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
        param.put("bizAppId", NLBL2020Constant.BUSINESS_ID);

        // Execute
        S21SsmEZDResult result = NLBL2020Query.getInstance().getSceOrdTypeList(param);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < resultMap.size(); i++) {

                ZYPEZDItemValueSetter.setValue(cMsg.sceOrdTpCd_PD.no(i), (String) resultMap.get(i).get("SCE_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.sceOrdTpNm_PD.no(i), (String) resultMap.get(i).get("SCE_ORD_TP_NM"));
            }
        }
    }

    /**
     * setPulldownShippingSearviceLevel
     * @param cMsg NLBL2020CMsg
     */
    public static void setPulldownShippingSearviceLevel(NLBL2020CMsg cMsg) {

        cMsg.shpgSvcLvlCd_PD.clear();
        cMsg.shpgSvcLvlDescTxt_PD.clear();

        ZYPCodeDataUtil.createPulldownList(NLBL2020Constant.TBL_SHPG_SVC_LVL, cMsg.shpgSvcLvlCd_PD, cMsg.shpgSvcLvlDescTxt_PD);
    }

    /**
     * Create setPulldownTimeAmPm
     * @param cMsg NLBL2020CMsg
     */
    public static void setPulldownTimeAmPm(NLBL2020CMsg cMsg) {

        cMsg.rqstRcvDtTxt_CD.no(0).setValue(NLBL2020Constant.TIME_AM);
        cMsg.rqstRcvDtTxt_CD.no(1).setValue(NLBL2020Constant.TIME_PM);
        cMsg.rqstRcvDtTxt_DI.no(0).setValue(NLBL2020Constant.TIME_AM);
        cMsg.rqstRcvDtTxt_DI.no(1).setValue(NLBL2020Constant.TIME_PM);
    }

    /**
     * getRtlWhName
     * @param cMsg NLBL2020CMsg
     * @return rtlWhNm or null
     */
    public static String getRtlWhName(NLBL2020CMsg cMsg) {

        // Mod Start 2020/01/07 QC#55308
//        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
//        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, cMsg.xxRtrnInvtyLocSrchTxt_RW);
//
//        rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);
//
//        if (rtlWhTMsg != null) {
//
//            return rtlWhTMsg.rtlWhNm.getValue();
//        }
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        param.put("rtlWhCd", cMsg.xxRtrnInvtyLocSrchTxt_RW.getValue());

        S21SsmEZDResult result = NLBL2020Query.getInstance().getWHName(param);

        if (result.isCodeNormal()) {

            return (String) result.getResultObject();
        }
        // Mod End 2020/01/07 QC#55308

        return null;
    }

    /**
     * getRtlSubWhName
     * @param cMsg NLBL2020CMsg
     * @return rtlSubWhNm or null
     */
    public static String getRtlSubWhName(NLBL2020CMsg cMsg) {

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        param.put("rtlSwhCd", cMsg.xxRtrnInvtyLocSrchTxt_SW.getValue());

        S21SsmEZDResult result = NLBL2020Query.getInstance().getSWHName(param);

        if (result.isCodeNormal()) {

            return (String) result.getResultObject();
        }

        return null;
    }

    /**
     * getRtlSubWhName
     * @param cMsg NLBL2020CMsg
     * @return mdseNm or null
     */
    public static String getMdseName(NLBL2020CMsg cMsg) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());

        if (cMsg.mdseCd_H1.getValue().length() == NLBL2020Constant.MDSE_DIGIT_8) {

            ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
            ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.ordTakeMdseCd, cMsg.mdseCd_H1);

            ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeMdseTMsg);

            if (ordTakeMdseTMsg != null) {

                ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, ordTakeMdseTMsg.mdseCd.getValue());

            } else {

                ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, cMsg.mdseCd_H1.getValue());
            }

        } else {

            ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, cMsg.mdseCd_H1.getValue());
        }

        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg != null) {

            return mdseTMsg.mdseNm.getValue();
        }

        return null;
    }

    /**
     * getCarrName
     * @param cMsg NLBL2020CMsg
     * @return carrNm or null
     */
    public static String getCarrName(NLBL2020CMsg cMsg) {

        OTBD_CARR_VTMsg otbdCarrVTMsg = new OTBD_CARR_VTMsg();
        otbdCarrVTMsg.setSQLID("004");
        otbdCarrVTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        otbdCarrVTMsg.setConditionValue("carrCd01", cMsg.carrCd_H1.getValue());

        OTBD_CARR_VTMsgArray otbdCarrVTMsgArray = (OTBD_CARR_VTMsgArray) EZDTBLAccessor.findByCondition(otbdCarrVTMsg);

        if (otbdCarrVTMsgArray.length() == 0) {

            return null;

        } else {

            return otbdCarrVTMsgArray.no(0).carrNm.getValue();
        }
    }

    /**
     * getShipToCustName
     * @param cMsg NLBL2020CMsg
     * @return shipToCustName or null
     */
    public static String getShipToCustName(NLBL2020CMsg cMsg) {

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        param.put("shipToCustCd", cMsg.shipToCustCd_H1.getValue());
        param.put("locTpCust", LOC_TP.CUSTOMER);

        S21SsmEZDResult result = NLBL2020Query.getInstance().getShipToCustName(param);

        if (result.isCodeNormal()) {

            return (String) result.getResultObject();
        }

        return null;
    }

    /**
     * check duplicate search name
     * @param cMsg NPAL1220CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NLBL2020CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_H1)) {

            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {

                return false;
            }

            if (cMsg.srchOptNm_H1.getValue().equals(cMsg.srchOptNm_PD.no(i).getValue())) {

                if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_PS)
                        && cMsg.srchOptPk_PS.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {

                    return false;
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Call API NSZC033001 (Save SearchOption)
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    public static void callNszc0330forSaveSearchOption(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_H1) || isSameSaveSearchName(cMsg)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_PS);
        }

        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_H1)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_H1);

        } else {

            setSelectSaveSearchName(pMsg, cMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLBL2020Constant.BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, cMsg.usrId.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.trxHdrNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.soNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.dsSoLineStsCd_PS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.sceOrdTpCd_PS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.shpgSvcLvlCd_PS.getValue());

        if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk_H1)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.svcConfigMstrPk_H1.getValue().toString());
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.xxRtrnInvtyLocSrchTxt_RW.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.xxRtrnInvtyLocSrchTxt_SW.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.shipToCustCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.carrCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.proNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.mdseCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.xxTrxDt_FR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.xxDtNm_F1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.rqstRcvDtTxt_F1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.xxTrxDt_TO.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.xxDtNm_T1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.rqstRcvDtTxt_T1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, cMsg.rddDt_FR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, cMsg.xxDtNm_F2.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, cMsg.rqstRcvDtTxt_F2.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, cMsg.rddDt_TO.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, cMsg.xxDtNm_T2.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, cMsg.rqstRcvDtTxt_T2.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, cMsg.xxTrxDt_F3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, cMsg.xxDtNm_F3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, cMsg.rqstRcvDtTxt_F3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_28, cMsg.xxTrxDt_T3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_29, cMsg.xxDtNm_T3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_30, cMsg.rqstRcvDtTxt_T3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_31, cMsg.wmsDropFlg_Y.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_32, cMsg.wmsDropFlg_N.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_33, cMsg.xxExstFlg_BO.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_34, cMsg.xxExstFlg_NO.getValue());

        if (executeNszc0330(cMsg, pMsg)) {

            setPulldownSaveSearch(cMsg);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_PS, pMsg.srchOptPk.getValue());
            // Message ; The process [@] has been successfully completed.
            cMsg.setMessageInfo(NLBL2020Constant.ZZZM9003I, new String[] {"Save Search" });
        }
    }

    /**
     * Call API NSZC033001 (Delete SearchOption)
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    public static void callNszc0330forDeleteSearchOption(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_PS);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLBL2020Constant.BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, cMsg.usrId.getValue());

        if (executeNszc0330(cMsg, pMsg)) {

            setPulldownSaveSearch(cMsg);
            cMsg.srchOptNm_H1.clear();
            cMsg.srchOptPk_PS.clear();
            cMsg.setMessageInfo(NLBL2020Constant.ZZZM9003I, new String[] {"Delete Search" });
        }
    }

    /**
     * Call API NSZC033001 (Search SearchOption)
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    public static void callNszc0330forSearchSearchOption(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_PS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLBL2020Constant.BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, cMsg.usrId.getValue());

        if (!executeNszc0330(cMsg, pMsg)) {

            return;
        }

        // Set Result
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_H1, pMsg.srchOptNm.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.trxHdrNum_H1, pMsg.srchOptTxt_01.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.soNum_H1, pMsg.srchOptTxt_02.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.dsSoLineStsCd_PS, pMsg.srchOptTxt_03.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.sceOrdTpCd_PS, pMsg.srchOptTxt_04.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_PS, pMsg.srchOptTxt_05.getValue());

        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_06)) {

            ZYPEZDItemValueSetter.setValue(cMsg.svcConfigMstrPk_H1, new BigDecimal(pMsg.srchOptTxt_06.getValue()));

        } else {

            cMsg.svcConfigMstrPk_H1.clear();
        }

        ZYPEZDItemValueSetter.setValue(cMsg.xxRtrnInvtyLocSrchTxt_RW, pMsg.srchOptTxt_07.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxRtrnInvtyLocSrchTxt_SW, pMsg.srchOptTxt_08.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_H1, pMsg.srchOptTxt_09.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.carrCd_H1, pMsg.srchOptTxt_10.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.proNum_H1, pMsg.srchOptTxt_11.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_H1, pMsg.srchOptTxt_12.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxTrxDt_FR, pMsg.srchOptTxt_13.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtNm_F1, pMsg.srchOptTxt_14.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDtTxt_F1, pMsg.srchOptTxt_15.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxTrxDt_TO, pMsg.srchOptTxt_16.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtNm_T1, pMsg.srchOptTxt_17.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDtTxt_T1, pMsg.srchOptTxt_18.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rddDt_FR, pMsg.srchOptTxt_19.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtNm_F2, pMsg.srchOptTxt_20.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDtTxt_F2, pMsg.srchOptTxt_21.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rddDt_TO, pMsg.srchOptTxt_22.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtNm_T2, pMsg.srchOptTxt_23.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDtTxt_T2, pMsg.srchOptTxt_24.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxTrxDt_F3, pMsg.srchOptTxt_25.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtNm_F3, pMsg.srchOptTxt_26.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDtTxt_F3, pMsg.srchOptTxt_27.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxTrxDt_T3, pMsg.srchOptTxt_28.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtNm_T3, pMsg.srchOptTxt_29.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDtTxt_T3, pMsg.srchOptTxt_30.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.wmsDropFlg_Y, pMsg.srchOptTxt_31.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.wmsDropFlg_N, pMsg.srchOptTxt_32.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxExstFlg_BO, pMsg.srchOptTxt_33.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxExstFlg_NO, pMsg.srchOptTxt_34.getValue());
    }

    /**
     * isSameSaveSearchName
     * @param cMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NLBL2020CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_PS)) {

            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_H1)) {

            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {

                return false;
            }

            if (cMsg.srchOptPk_PS.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {

                if (cMsg.srchOptNm_H1.getValue().equals(cMsg.srchOptNm_PD.no(i).getValue())) {

                    return true;
                }

                return false;
            }
        }

        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param cMsg NLBL2020CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NLBL2020CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_PS)) {

            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {

                return;
            }

            if (cMsg.srchOptPk_PS.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {

                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_PD.no(i));
            }
        }

        return;
    }

    /**
     * Execute API NSZC033001
     * @param bizMsg NLBL2020CMsg
     * @param pMsg NSZC033001PMsg
     * @return
     */
    private static boolean executeNszc0330(NLBL2020CMsg cMsg, NSZC033001PMsg pMsg) {

        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {

            for (int i = 0; i < pMsg.xxMsgIdList.length(); i++) {

                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)) {

                    msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);

                    if (msgId.endsWith("E")) {

                        cMsg.srchOptPk_PS.setErrorInfo(1, msgId);
                        cMsg.srchOptNm_H1.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * chkWarehousePermission
     * @param cMsg NLBL2020CMsg
     * @param rtlWhCd String
     * @param functionList List<String>
     * @return true:OK false:NG
     */
    public static boolean isWarehousePermission(NLBL2020CMsg cMsg, String rtlWhCd, List<String> functionList) {

        if (chkWarehousePermtReq(functionList)) {
            // All Wh Permission User
            return true;
        }

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        param.put("schdCoordPsnCd", cMsg.usrId.getValue());
        param.put("salesDate", cMsg.slsDt.getValue());

        S21SsmEZDResult result = NLBL2020Query.getInstance().getPermissionWhList(param);

        if (result.isCodeNormal()) {

            List<String> permissionWhList = (List<String>) result.getResultObject();

            if (permissionWhList.isEmpty()) {
                return false;
            }

            if (permissionWhList.contains("*")) {
                // All Wh permission
                return true;
            }

            if (permissionWhList.contains(rtlWhCd)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks for Warehouse Permission Required.
     * @param functionList List<String>
     * @return true, if successful
     */
    public static boolean chkWarehousePermtReq(List<String> functionList) {
        return functionList.contains(NLBL2020Constant.ROLE_ALL_WH_PERMISSION);
    }

    /**
     * Clear All
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    public static void clearAllTbl(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(cMsg.P);
        ZYPTableUtil.clear(cMsg.S);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(sMsg.P);
        ZYPTableUtil.clear(sMsg.S);
    }

    /**
     * setSearchSoListParam
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     * @return Map<String, Object>
     */
    public static Map<String, Object> setSearchSoListParam(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        Map<String, Object> param = new HashMap<String, Object>();
        setSearchSoListCommonParam(cMsg.glblCmpyCd.getValue(), param);

        boolean boSrchAval = true;

        if (ZYPCommonFunc.hasValue(cMsg.trxHdrNum_H1)) {

            param.put("srcOrdNum", cMsg.trxHdrNum_H1.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.soNum_H1)) {

            param.put("soNum", cMsg.soNum_H1.getValue());
            boSrchAval = false;
        }

        if (ZYPCommonFunc.hasValue(cMsg.dsSoLineStsCd_PS)) {

            param.put("dsSoLineStsCd", cMsg.dsSoLineStsCd_PS.getValue());

            if (!DS_SO_LINE_STS.BACKORDERED.equals(cMsg.dsSoLineStsCd_PS.getValue())) {

                boSrchAval = false;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.sceOrdTpCd_PS)) {

            param.put("sceOrdTpCd", cMsg.sceOrdTpCd_PS.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_PS)) {

            param.put("shpgSvcLvlCd", cMsg.shpgSvcLvlCd_PS.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk_H1)) {

            param.put("svcConfigMstrPk", cMsg.svcConfigMstrPk_H1.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxRtrnInvtyLocSrchTxt_RW)) {

            param.put("whCdList", splitSrchTxt(cMsg.xxRtrnInvtyLocSrchTxt_RW));
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxRtrnInvtyLocSrchTxt_SW)) {

            param.put("swhCdList", splitSrchTxt(cMsg.xxRtrnInvtyLocSrchTxt_SW));
        }

        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_H1)) {

            param.put("shipToCdList", splitSrchTxt(cMsg.shipToCustCd_H1));
        }

        if (ZYPCommonFunc.hasValue(cMsg.carrCd_H1)) {

            param.put("carrCdList", splitSrchTxt(cMsg.carrCd_H1));
        }

        if (ZYPCommonFunc.hasValue(cMsg.proNum_H1)) {

            param.put("proNum", cMsg.proNum_H1.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.mdseCd_H1)) {

            param.put("mdseCdList", addOrdTakeMdse(cMsg.glblCmpyCd.getValue(), splitSrchTxt(cMsg.mdseCd_H1)));
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxTrxDt_FR)) {

            if (ZYPCommonFunc.hasValue(cMsg.xxDtNm_F1) && ZYPCommonFunc.hasValue(cMsg.rqstRcvDtTxt_F1)) {

                String timeHHMM = getTime_HHMM(cMsg.xxDtNm_F1.getValue(), cMsg.rqstRcvDtTxt_F1.getValue());

                if (ZYPCommonFunc.hasValue(timeHHMM)) {

                    param.put("soCratTsFrm", S21StringUtil.concatStrings(cMsg.xxTrxDt_FR.getValue(), timeHHMM));
                }

            } else {

                param.put("soCratTsFrm", cMsg.xxTrxDt_FR.getValue());
            }

            boSrchAval = false;
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxTrxDt_TO)) {

            if (ZYPCommonFunc.hasValue(cMsg.xxDtNm_T1) && ZYPCommonFunc.hasValue(cMsg.rqstRcvDtTxt_T1)) {

                String timeHHMM = getTime_HHMM(cMsg.xxDtNm_T1.getValue(), cMsg.rqstRcvDtTxt_T1.getValue());

                if (ZYPCommonFunc.hasValue(timeHHMM)) {

                    param.put("soCratTsTo", S21StringUtil.concatStrings(cMsg.xxTrxDt_TO.getValue(), timeHHMM));
                }

            } else {

                param.put("soCratTsTo", ZYPDateUtil.addDays(cMsg.xxTrxDt_TO.getValue(), 1));
            }

            boSrchAval = false;
        }

        if (ZYPCommonFunc.hasValue(cMsg.rddDt_FR)) {

            param.put("rddDtFrm", cMsg.rddDt_FR.getValue());
        }


        if (ZYPCommonFunc.hasValue(cMsg.rddDt_TO)) {

            param.put("rddDtTo", cMsg.rddDt_TO.getValue());
        }


        if (ZYPCommonFunc.hasValue(cMsg.xxTrxDt_F3)) {

            param.put("needByDtFrom", cMsg.xxTrxDt_F3.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxDtNm_F3) && ZYPCommonFunc.hasValue(cMsg.rqstRcvDtTxt_F3)) {

            String timeHHMM = getTime_HHMM(cMsg.xxDtNm_F3.getValue(), cMsg.rqstRcvDtTxt_F3.getValue());

            if (ZYPCommonFunc.hasValue(timeHHMM)) {

                param.put("needByTmFrom", timeHHMM);
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxTrxDt_T3)) {

            param.put("needByDtTo", cMsg.xxTrxDt_T3.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxDtNm_T3) && ZYPCommonFunc.hasValue(cMsg.rqstRcvDtTxt_T3)) {

            String timeHHMM = getTime_HHMM(cMsg.xxDtNm_T3.getValue(), cMsg.rqstRcvDtTxt_T3.getValue());

            if (ZYPCommonFunc.hasValue(timeHHMM)) {

                param.put("needByTmTo", timeHHMM);
            }
        }

        // WMS Drop Flag
        if ((ZYPCommonFunc.hasValue(cMsg.wmsDropFlg_Y) && !ZYPCommonFunc.hasValue(cMsg.wmsDropFlg_N))) {

            param.put("wmsDropFlg", ZYPConstant.FLG_ON_Y);
            boSrchAval = false;
        }

        if ((!ZYPCommonFunc.hasValue(cMsg.wmsDropFlg_Y) && ZYPCommonFunc.hasValue(cMsg.wmsDropFlg_N))) {

            param.put("wmsDropFlg", ZYPConstant.FLG_OFF_N);
        }

        // SO_LINE_BACK_ORD_FLG (checkBox) check
        if (ZYPCommonFunc.hasValue(cMsg.xxExstFlg_BO) && boSrchAval) {

            param.put("soLineBackOrdFlg", ZYPConstant.FLG_ON_Y);
        }

        // SO_LINE_OPEN_FLG (checkBox) no check
        if (!ZYPCommonFunc.hasValue(cMsg.xxExstFlg_NO)) {

            param.put("soLineOpenFlg", ZYPConstant.FLG_ON_Y);
        }
        // QC#18794
        param.put("itemChange", SCE_ORD_TP.ITEM_CHANGE);

        return param;
    }

    /**
     * setReSearchSoListParam
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     * @return Map<String, Object>
     */
    public static Map<String, Object> setReSearchSoListParam(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        Map<String, Object> param = new HashMap<String, Object>();
        setSearchSoListCommonParam(cMsg.glblCmpyCd.getValue(), param);

        ArrayList<String> srcBoNumList = new ArrayList<String>();
        ArrayList<String> soNumList = new ArrayList<String>();
        ArrayList<String> soNumSlpNumList = new ArrayList<String>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // So Number List
            if (soNumList.isEmpty() || !soNumList.contains(sMsg.A.no(i).soNum_HI.getValue())) {

                soNumList.add(sMsg.A.no(i).soNum_HI.getValue());
            }

            // SO Number + Slip Number List
            soNumSlpNumList.add(sMsg.A.no(i).soNum_HI.getValue().concat(sMsg.A.no(i).soSlpNum_HI.getValue()));

            // Sorce Order Number List (For Back Order)
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).soNum_HI)
                    && (srcBoNumList.isEmpty() || !srcBoNumList.contains(sMsg.A.no(i).trxHdrNum_HI.getValue()))) {

                srcBoNumList.add(sMsg.A.no(i).trxHdrNum_HI.getValue());
            }
        }

        param.put("srcBoNumList", srcBoNumList);
        param.put("soNumList", soNumList);
        param.put("soNumSlpNumList", soNumSlpNumList);
        // QC#18794
        param.put("itemChange", SCE_ORD_TP.ITEM_CHANGE);

        // Exist Back Order
        if (!srcBoNumList.isEmpty()) {

            param.put("soLineBackOrdFlg", ZYPConstant.FLG_ON_Y);
        }

        return param;
    }

    /**
     * setSearchSoListCommonParam
     * @param glblCmpyCd String
     * @param param Map<String, Object>
     */
    private static void setSearchSoListCommonParam(String glblCmpyCd, Map<String, Object> param) {

        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsCondConstGrpId", NLBL2020Constant.DCC_GRP_ID_NLZC2110_SHIP_CTRL);
        param.put("bizAppId", NLBL2020Constant.BUSINESS_ID);
        param.put("pickConfSts", DS_SO_LINE_STS.PICK_CONFIRMED);
        param.put("packConfSts", DS_SO_LINE_STS.AWAITING_SHIPPING);
        param.put("partiallyShipped", DS_SO_LINE_STS.PARTIALLY_SHIPPED);
        param.put("soProcStsCdShip", SO_PROC_STS.SHIP);
        param.put("soPrintFlg", ZYPConstant.FLG_ON_Y);
        param.put("shipFlg", ZYPConstant.FLG_OFF_N);
        param.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
        param.put("soCustDataTpShipTo", SO_CUST_DATA_TP.SHIP_TO);
        param.put("flgY", ZYPConstant.FLG_ON_Y);
        param.put("flgN", ZYPConstant.FLG_OFF_N);
        param.put("dsSoLineStsBo", DS_SO_LINE_STS.BACKORDERED);
        param.put("dsSoLineStsBoDesc", getDsSoLineStsDescTxt(glblCmpyCd, DS_SO_LINE_STS.BACKORDERED));
        param.put("shpgStsValid", SHPG_STS.VALIDATED);
        param.put("dsOrdTpList", getDsOrdTpCdForDT(glblCmpyCd));
        //QC#20246 DEL START
        //param.put("ctacPsnTpShipTo", CTAC_TP.DELIVERY_INSTALL);
        //QC#20246 DEL END
        param.put("soMsgTpCd", SHPG_ORD_MSG_TP.CPO_INFORMATION);
        param.put("wmsTaskCdShip", WMS_TASK.SHIP);
    }

    /**
     * getDsSoLineStsDescTxt
     * @param glblCmpyCd String
     * @param dsSoLineStsCd String
     * @return String
     */
    private static String getDsSoLineStsDescTxt(String glblCmpyCd, String dsSoLineStsCd) {

        DS_SO_LINE_STSTMsg dsSoLineStsTMsg = new DS_SO_LINE_STSTMsg();
        ZYPEZDItemValueSetter.setValue(dsSoLineStsTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsSoLineStsTMsg.dsSoLineStsCd, dsSoLineStsCd);
        dsSoLineStsTMsg = (DS_SO_LINE_STSTMsg) EZDTBLAccessor.findByKey(dsSoLineStsTMsg);

        if (dsSoLineStsTMsg == null) {

            return null;
        }

        return dsSoLineStsTMsg.dsSoLineStsDescTxt.getValue();
    }

    /**
     * getDsOrdCatgCdForDt
     * @param glblCmpyCd String
     * @return String
     */
    private static String[] getDsOrdTpCdForDT(String glblCmpyCd) {

        String dsOrdTpCdForWHT = ZYPCodeDataUtil.getVarCharConstValue(NLBL2020Constant.VAR_CONST_CPO_DS_ORD_TP_FOR_WH_TRNSF, glblCmpyCd);
        return dsOrdTpCdForWHT.split(",");

    }

    /**
     * getTime_HHMM
     * @param time String
     * @param timeAmPm String
     * @return String (HHMM)
     */
    public static String getTime_HHMM(String time, String timeAmPm) {

        int h = 0;
        int m = 0;

        if (0 <= time.indexOf(":")) {

            String[] temp = time.split(":");

            if (temp.length < 2) {

                return null;
            }

            h = Integer.valueOf(temp[0]);
            m = Integer.valueOf(temp[1]);

        } else {

            if (time.length() == NLBL2020Constant.HOUR_MINUTE_STR_LENGTH) {

                h = Integer.valueOf(time.substring(0, 2));
                m = Integer.valueOf(time.substring(2));

            } else if (time.length() == NLBL2020Constant.HOUR_MINUTE_STR_LENGTH - 1) {

                time = "0" + time;
                h = Integer.valueOf(time.substring(0, 2));
                m = Integer.valueOf(time.substring(2));

            } else {

                h = 0;
                m = 0;
            }
        }

        if (h < 0) {

            h = 0;

        } else if (NLBL2020Constant.HALF_DAY_HOURS < h) {

            h = NLBL2020Constant.HALF_DAY_HOURS;
        }

        if (NLBL2020Constant.TIME_PM.equals(timeAmPm)) {

            h += NLBL2020Constant.HALF_DAY_HOURS;
        }

        if (NLBL2020Constant.ONE_DAY_HOURS <= h) {

            h = 0;
        }

        if (m < 0) {

            m = 0;
        }

        if (NLBL2020Constant.ONE_HOUR_MINUTES <= m) {

            m = 0;
        }

        return String.format("%02d%02d", h, m);
    }

    /**
     * chkTimeStampForShpgOrd
     * @param glblCmpyCd String
     * @param soNum String
     * @param ezUpTime String
     * @param ezUpTimeZone String
     * @return true:OK false:NG(Another User update)
     */
    public static boolean chkTimeStampForShpgOrd(String glblCmpyCd, String soNum, String ezUpTime, String ezUpTimeZone) {

        SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soNum, soNum);
        shpgOrdTMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdate(shpgOrdTMsg);

        return ZYPDateUtil.isSameTimeStamp(ezUpTime, ezUpTimeZone, shpgOrdTMsg.ezUpTime.getValue(), shpgOrdTMsg.ezUpTimeZone.getValue());
    }

    /**
     * chkTimeStampForShpgOrdDtl
     * @param glblCmpyCd String
     * @param soNum String
     * @param soSlpNum String
     * @param ezUpTime String
     * @param ezUpTimeZone String
     * @return true:OK false:NG(Another User update)
     */
    public static boolean chkTimeStampForShpgOrdDtl(String glblCmpyCd, String soNum, String soSlpNum, String ezUpTime, String ezUpTimeZone) {

        SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.soSlpNum, soSlpNum);
        shpgOrdDtlTMsg = (SHPG_ORD_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(shpgOrdDtlTMsg);

        return ZYPDateUtil.isSameTimeStamp(ezUpTime, ezUpTimeZone, shpgOrdDtlTMsg.ezUpTime.getValue(), shpgOrdDtlTMsg.ezUpTimeZone.getValue());
    }

    /**
     * chkLineCancle
     * @param sMsgALine NLBL2020_ASMsg
     * @param sMsg NLBL2020SMsg
     * @return true:OK false:NG
     */
    public static boolean chkLineCancel(NLBL2020_ASMsg sMsgALine, NLBL2020SMsg sMsg) {

        boolean hasErr = false;

        // Check Open Status
        if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.soLineOpenFlg_AH.getValue()) || ZYPConstant.FLG_ON_Y.equals(sMsgALine.shipFlg_AH.getValue())) {

            sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2283E);
            return false;
        }

        // QC#17922 Add.
        if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxWrnSkipFlg_A1.getValue())) {
            sMsgALine.xxExstFlg_A2.clearErrorInfo();
            sMsgALine.xxWrnSkipFlg_A1.clear();
            return true;
        }

        if (warnLineCancel(sMsgALine, sMsg)) {
            return true;
        }

        // Check So Cancel Available
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", sMsg.glblCmpyCd.getValue());
        ssmParam.put("rtlWhCd", sMsgALine.shipFromRtlWhCd_AH.getValue());
        ssmParam.put("sceOrdTpCd", sMsgALine.sceOrdTpCd_AH.getValue());

        S21SsmEZDResult ssmResult = NLBL2020Query.getInstance().getSoCancelAvalFlg(ssmParam);

        if (ssmResult.getQueryResultCount() == 0) {

            sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLBM1352E);
            hasErr = true;

        } else {

            Map<String, String> soCancelAvalMap = (Map<String, String>) ssmResult.getResultObject();

            String soCancelAvalFlg = soCancelAvalMap.get("SO_SCR_CANC_AVAL_FLG");
            String whSysTpDescTxt = soCancelAvalMap.get("WH_SYS_TP_DESC_TXT");

            if (!ZYPConstant.FLG_ON_Y.equals(soCancelAvalFlg)) {

                sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLBM1351E, new String[]{whSysTpDescTxt});
                hasErr = true;

            // Check Partial
            } else if (!checkPartial(sMsg, sMsgALine)) {

                sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2315E, new String[]{"cancel"});
                hasErr = true;

            // Check Set Component
            } else if (!checkSetComponent(sMsg, sMsgALine)) {

                sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLBM1320E);
                hasErr = true;

            // Check Configuration Id
            } else if (!checkConfigId(sMsg, sMsgALine)) {

                sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2284E, new String[] {sMsgALine.pickSvcConfigMstrPk_A1.getValue().toPlainString() });
                hasErr = true;
            }
        }

        if (hasErr) {

            return false;
        }

        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param sceOrdTpCd String
     * @return SoCancelAvailableFlg or null
     */
    private static int countSetItem(String glblCmpyCd, String trxHdrNum, String trxLineNum, String setMdseCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxHdrNum", trxHdrNum);
        ssmParam.put("trxLineNum", trxLineNum);
        ssmParam.put("setMdseCd", setMdseCd);

        S21SsmEZDResult result = NLBL2020Query.getInstance().countSetItem(ssmParam);

        if (result.isCodeNormal()) {

            Integer count = (Integer) result.getResultObject();
            return count.intValue();

        } else {

            return -1;
        }
    }

    /**
     * checkPartial
     * @param sMsg NLBL2020SMsg
     * @param sMsgBLine NLBL2020_ASMsg
     * @return boolean true : OK, false : NG
     */
    private static boolean checkPartial(NLBL2020SMsg sMsg, NLBL2020_ASMsg sMsgALine) {

        if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxExstFlg_C4.getValue())) {

            return true;
        }

        int shpgOrdDtlCnt = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxExstFlg_A2.getValue()) && sMsgALine.soNum_HI.getValue().equals(sMsg.A.no(i).soNum_HI.getValue())) {

                shpgOrdDtlCnt++;
            }
        }

        SHPG_ORD_DTLTMsg shpgOrdDtl = new SHPG_ORD_DTLTMsg();
        shpgOrdDtl.setSQLID("002");
        shpgOrdDtl.setConditionValue("glblCmpyCd01", sMsg.glblCmpyCd.getValue());
        shpgOrdDtl.setConditionValue("soNum01", sMsgALine.soNum_HI.getValue());
        int count = EZDTBLAccessor.count(shpgOrdDtl);

        // If not display data having same So#
        if (shpgOrdDtlCnt != count) {

            return false;
        }

        return true;
    }

    /**
     * checkSetComponent
     * @param sMsg NLBL2020SMsg
     * @param sMsgALine NLBL2020_ASMsg
     * @return boolean
     */
    private static boolean checkSetComponent(NLBL2020SMsg sMsg, NLBL2020_ASMsg sMsgALine) {

        int kitSetCnt = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLBL2020_ASMsg line = sMsg.A.no(i);

            if (ZYPConstant.FLG_ON_Y.equals(line.xxExstFlg_A2.getValue())) {

                if (sMsgALine.trxHdrNum_AH.getValue().equals(line.trxHdrNum_AH.getValue()) && sMsgALine.trxLineNum_AH.getValue().equals(line.trxLineNum_AH.getValue())
                        && ZYPCommonFunc.hasValue(sMsgALine.setMdseCd_AH) && sMsgALine.setMdseCd_AH.getValue().equals(line.setMdseCd_AH.getValue())) {

                    kitSetCnt++;
                }
            }
        }

        int count = countSetItem(sMsg.glblCmpyCd.getValue(), sMsgALine.trxHdrNum_AH.getValue(), sMsgALine.trxLineNum_AH.getValue(), sMsgALine.setMdseCd_AH.getValue());

        if (count != kitSetCnt) {

            return false;
        }

        return true;
    }

    /**
     * checkConfigId
     * @param sMsg NLBL2020SMsg
     * @param sMsgALine NLBL2020_ASMsg
     * @return boolean
     */
    private static boolean checkConfigId(NLBL2020SMsg sMsg, NLBL2020_ASMsg sMsgALine) {

        if (ZYPCommonFunc.hasValue(sMsgALine.pickSvcConfigMstrPk_A1)) {

            int configIdCnt = 0;

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                NLBL2020_ASMsg line = sMsg.A.no(i);

                if (ZYPConstant.FLG_ON_Y.equals(line.xxExstFlg_A2.getValue()) && sMsgALine.soNum_HI.getValue().equals(line.soNum_HI.getValue())
                        && sMsgALine.pickSvcConfigMstrPk_A1.getValue().equals(line.pickSvcConfigMstrPk_A1.getValue())) {

                    configIdCnt++;
                }
            }

            // Count SHPG_ORD_DTL
            SHPG_ORD_DTLTMsg dsShpgOrdDtl = new SHPG_ORD_DTLTMsg();
            dsShpgOrdDtl.setSQLID("502");
            dsShpgOrdDtl.setConditionValue("glblCmpyCd01", sMsg.glblCmpyCd.getValue());
            dsShpgOrdDtl.setConditionValue("soNum01", sMsgALine.soNum_HI.getValue());
            dsShpgOrdDtl.setConditionValue("pickSvcConfigMstrPk01", sMsgALine.pickSvcConfigMstrPk_A1.getValue());
            int count = EZDTBLAccessor.count(dsShpgOrdDtl);

            // If not display data having same Configuration Id
            if (configIdCnt != count) {

                return false;
            }
        }

        return true;
    }

    /**
     * setSoCancelParam
     * @param glblCmpyCd String
     * @param sMsgALine NLBL2020_ASMsg
     * @param headCancelFlg boolean
     * @return NLZC210001PMsg
     */
    public static NLZC210001PMsg setSoCancelParam(String glblCmpyCd, NLBL2020_ASMsg sMsgALine, boolean headCancelFlg) {

        NLZC210001PMsg pMsg = new NLZC210001PMsg();

        // Set Param
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, sMsgALine.invtyLocCd_AH);
        ZYPEZDItemValueSetter.setValue(pMsg.soNum, sMsgALine.soNum_HI);
        ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, sMsgALine.sceOrdTpCd_AH);
        ZYPEZDItemValueSetter.setValue(pMsg.shipDtTmTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(NLBL2020Constant.FORMAT_TIMESTAMP_TIME)));

        if (headCancelFlg) {

            ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd, SO_PROC_STS.ORDER_VOID);

        } else {

            // Line Cancel
            ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd, SO_PROC_STS.LINE_VOID);
            ZYPEZDItemValueSetter.setValue(pMsg.soSlpNum, sMsgALine.soSlpNum_HI);
            ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd_DT, SO_PROC_STS.LINE_VOID);
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, sMsgALine.mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.fromStkStsCd, sMsgALine.fromStkStsCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.shipQty, sMsgALine.shpgBalQty_AH);
        }

        return pMsg;
    }

    /**
     * chkSoClose
     * @param sMsgALine NLBL2020_ASMsg
     * @param sMsg NLBL2020SMsg
     * @return true:OK false:NG
     */
    public static boolean chkSoClose(NLBL2020_ASMsg sMsgALine, NLBL2020SMsg sMsg) {

        boolean hasErr = false;

        // Check Open Status by SO Header
        if (ZYPConstant.FLG_OFF_N.equals(sMsgALine.soPrintFlg_AH.getValue()) || ZYPConstant.FLG_ON_Y.equals(sMsgALine.shipFlg_AH.getValue())) {

            sMsgALine.xxExstFlg_A1.setErrorInfo(1, NLBL2020Constant.NLZM2283E);
            hasErr = true;
        }

        // Check SO Line Status
        SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
        shpgOrdDtlTMsg.setSQLID("002");
        shpgOrdDtlTMsg.setConditionValue("glblCmpyCd01", sMsg.glblCmpyCd.getValue());
        shpgOrdDtlTMsg.setConditionValue("soNum01", sMsgALine.soNum_HI.getValue());
        SHPG_ORD_DTLTMsgArray shpgOrdDtlArray = (SHPG_ORD_DTLTMsgArray) EZDTBLAccessor.findByCondition(shpgOrdDtlTMsg);

        boolean existShipped = false;

        for (int i = 0; i < shpgOrdDtlArray.getValidCount(); i++) {

            // Not enable to partial ship/cancel
            if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxExstFlg_C4.getValue())) {

                if (SHPG_STS.SHIPPED.equals(shpgOrdDtlArray.no(i).shpgStsCd.getValue())) {

                    sMsgALine.xxExstFlg_A1.setErrorInfo(1, NLBL2020Constant.NLZM2315E, new String[] {"ship/cancel" });
                    hasErr = true;

                } else if (ZYPCommonFunc.hasValue(shpgOrdDtlArray.no(i).shipQty) && 0 < shpgOrdDtlArray.no(i).shipQty.getValue().abs().intValue()) {

                    sMsgALine.xxExstFlg_A1.setErrorInfo(1, NLBL2020Constant.NLZM2315E, new String[] {"ship/cancel" });
                    hasErr = true;
                }
            }

            if (SHPG_STS.SHIPPED.equals(shpgOrdDtlArray.no(i).shpgStsCd.getValue())) {

                existShipped = true;

            } else if (!ZYPCommonFunc.hasValue(shpgOrdDtlArray.no(i).shipQty) || 0 < shpgOrdDtlArray.no(i).shipQty.getValue().abs().intValue()) {

                existShipped = true;
            }
        }

        // Check Shipped Count
        if (!existShipped) {

            // Not ship any line
            sMsgALine.xxExstFlg_A1.setErrorInfo(1, NLBL2020Constant.NLBM1314E);
            hasErr = true;
        }

        if (hasErr) {

            return false;
        }

        return true;
    }

    /**
     * setSoCloseParam
     * @param glblCmpyCd String
     * @param sMsgALine NLBL2020_ASMsg
     * @return NLZC210001PMsg
     */
    public static NLZC210001PMsg setSoCloseParam(String glblCmpyCd, NLBL2020_ASMsg sMsgALine) {

        NLZC210001PMsg pMsg = new NLZC210001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, sMsgALine.invtyLocCd_AH);
        ZYPEZDItemValueSetter.setValue(pMsg.soNum, sMsgALine.soNum_HI);
        ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, sMsgALine.sceOrdTpCd_AH);
        ZYPEZDItemValueSetter.setValue(pMsg.shipDtTmTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(NLBL2020Constant.FORMAT_TIMESTAMP_TIME)));
        ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd, SO_PROC_STS.SHIP);

        return pMsg;
    }

    /**
     * setSoCloseParam
     * @param cMsg NLBL2020CMsg
     * @param rwsHdrMap Map<String, Object>
     * @return NLZC207001PMsg
     */
    public static NLZC207001PMsg setRwsCompApiParam(NLBL2020CMsg cMsg, Map<String, Object> rwsHdrMap) {

        NLZC207001PMsg pMsg = new NLZC207001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, (String) rwsHdrMap.get("RWS_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, (String) rwsHdrMap.get("SCE_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.rwsCloDtTmTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(NLBL2020Constant.FORMAT_TIMESTAMP_TIME)));
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, (String) rwsHdrMap.get("RTL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.rwsRefNum, (String) rwsHdrMap.get("RWS_REF_NUM"));

        return pMsg;
    }

    /**
     * chkShipRecord
     * @param sMsgALine NLBL2020_ASMsg
     * @param sMsg NLBL2020SMsg
     * @return boolean true:OK false:NG
     */
    // START 2022/04/04 R.Azucena [QC#59802, MOD]
    // START 2022/08/08 R.Azucena [QC#60416, MOD]
    public static boolean chkShipRecord(NLBL2020_ASMsg sMsgALine, NLBL2020SMsg sMsg) {
    // public static boolean chkShipRecord(NLBL2020_ASMsg sMsgALine, NLBL2020SMsg sMsg, BigDecimal totalShipSingleQty) {
    // END 2022/08/08 R.Azucena [QC#60416, MOD]
    // END 2022/04/04 R.Azucena [QC#59802, MOD]

        // Not Open Status
        if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.soLineOpenFlg_AH.getValue()) || ZYPConstant.FLG_ON_Y.equals(sMsgALine.shipFlg_AH.getValue())) {

            sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2283E);
            return false;
        }

        boolean hasErr = false;

        // Ship Quantity Check
        // START 2022/04/04 R.Azucena [QC#59802, MOD]
        // START 2022/08/08 R.Azucena [QC#60416, MOD]
        if (!isShipQtyChk(sMsgALine, sMsg)) {
        // if (!isShipQtyChk(sMsgALine, sMsg, totalShipSingleQty)) {
        // END 2022/08/08 R.Azucena [QC#60416, MOD]
        // END 2022/04/04 R.Azucena [QC#59802, MOD]

            hasErr =  true;
        }

        // Carrier Check
        if (!isCarrChk(sMsgALine, sMsg)) {

            hasErr =  true;
        }

        // Tracking Number Check
        if (SCE_ORD_TP.DIRECT_SALES.equals(sMsgALine.sceOrdTpCd_AH.getValue()) && !ZYPCommonFunc.hasValue(sMsgALine.proNum_A1)) {

            sMsgALine.proNum_A1.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Tracking Number" });
            hasErr =  true;
        }

        // Serial Check
        if (!isSerialChk(sMsgALine, sMsg)) {

            hasErr =  true;
        }

        if (hasErr) {

            return false;
        }

        // Check Configuration Id
        if (!checkConfigId(sMsg, sMsgALine)) {

            sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2284E, new String[] {sMsgALine.pickSvcConfigMstrPk_A1.getValue().toPlainString() });
            return false;
        }

        // Check Set Component
        if (!checkSetComponent(sMsg, sMsgALine)) {

            sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLBM1320E);
            return false;
        }

        // Check Partial
        if (ZYPConstant.FLG_OFF_N.equals(sMsgALine.xxExstFlg_C4.getValue()) && !checkPartial(sMsg, sMsgALine)) {

            sMsgALine.xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLZM2315E, new String[] {"ship" });
            return false;
        }

        return true;
    }

    /**
     * isShipQtyChk
     * @param sMsgALine NLBL2020_ASMsg
     * @param sMsg NLBL2020SMsg
     * @param totalShipSingleQty BigDecimal
     * @return boolean true:OK false:NG
     */
    // START 2022/04/04 R.Azucena [QC#59802, MOD]
    // START 2022/08/08 R.Azucena [QC#60416, MOD]
    private static boolean isShipQtyChk(NLBL2020_ASMsg sMsgALine, NLBL2020SMsg sMsg) {
    // private static boolean isShipQtyChk(NLBL2020_ASMsg sMsgALine, NLBL2020SMsg sMsg, BigDecimal totalShipSingleQty) {
    // END 2022/08/08 R.Azucena [QC#60416, MOD]
    // END 2022/04/04 R.Azucena [QC#59802, MOD]

        if (!ZYPCommonFunc.hasValue(sMsgALine.shipQty_A1)) {

            sMsgALine.shipQty_A1.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Ship Quantity" });
            return false;

        } else if (sMsgALine.pickConfQty_A1.getValue().compareTo(sMsgALine.shipQty_A1.getValue()) < 0) {

            sMsgALine.shipQty_A1.setErrorInfo(1, NLBL2020Constant.NLZM2316E, new String[] {"Ship Quantity", "Pick Quantity" });
            return false;

        } else if (BigDecimal.ZERO.equals(sMsgALine.shipQty_A1.getValue())) {

            sMsgALine.shipQty_A1.setErrorInfo(1, NLBL2020Constant.NLZM2277E, new String[] {"Ship Quantity", "0" });
            return false;

        } else if (sMsgALine.shipQty_A1.getValue().compareTo(sMsgALine.shpgBalQty_AH.getValue()) > 0) {

            sMsgALine.shipQty_A1.setErrorInfo(1, NLBL2020Constant.NLZM2316E, new String[] {"Ship Quantity", sMsgALine.shpgBalQty_AH.getValue().toPlainString() });
            return false;

        } else if (ZYPConstant.FLG_OFF_N.equals(sMsgALine.xxExstFlg_C5.getValue()) && sMsgALine.shipQty_A1.getValue().compareTo(sMsgALine.shpgBalQty_AH.getValue()) < 0) {

            sMsgALine.shipQty_A1.setErrorInfo(1, NLBL2020Constant.NLZM2316E, new String[] {"Ship Quantity", "Order Quantity" });
            return false;

        } else if (SCE_ORD_TP.DIRECT_SALES.equals(sMsgALine.sceOrdTpCd_AH.getValue()) && ZYPCommonFunc.hasValue(sMsgALine.inEachQty_A1.getValue())
                && BigDecimal.ZERO.compareTo(sMsgALine.inEachQty_A1.getValue()) < 0 && !BigDecimal.ZERO.equals(sMsgALine.shipQty_A1.getValue().remainder(sMsgALine.inEachQty_A1.getValue()))) {

            sMsgALine.shipQty_A1.setErrorInfo(1, NLBL2020Constant.NLZM2330E);
            return false;
        // START 2022/04/04 R.Azucena [QC#59802, ADD]
        // START 2022/08/08 R.Azucena [QC#60416, DEL]
        // } else if (SCE_ORD_TP.DIRECT_SALES.equals(sMsgALine.sceOrdTpCd_AH.getValue()) && !ZYPCommonFunc.hasValue(sMsgALine.serNum_A1)
                // START 2022/04/25 R.Azucena [QC#59802-1, ADD]
        //         && ZYPConstant.FLG_ON_Y.equals(sMsgALine.instlBaseCtrlFlg_AH.getValue())
        //         // END 2022/04/25 R.Azucena [QC#59802-1, ADD]
        //         && getAvailSingleQty(sMsgALine, sMsg.glblCmpyCd.getValue()).compareTo(totalShipSingleQty) < 0) {
        //     sMsgALine.shipQty_A1.setErrorInfo(1, NLBL2020Constant.NLBM1376E);
        //     return false;
        // END 2022/08/08 R.Azucena [QC#60416, DEL]
        // END 2022/04/04 R.Azucena [QC#59802, ADD]
        }

        return true;
    }

    // START 2022/04/04 R.Azucena [QC#59802, ADD]
    // START 2022/08/08 R.Azucena [QC#60416, DEL]
    /**
     * getAvailSingleQty
     * @param asMsg NPBL0020_ASMsg
     * @param glblCmpyCd String
     * @return BigDecimal
     */
    // private static BigDecimal getAvailSingleQty(NLBL2020_ASMsg sMsgALine, String glblCmpyCd) {
    //     Map<String, Object> ssmParam = new HashMap<String, Object>();
    //     ssmParam.put(NLBL2020Constant.SSM_GLBL_CMPY_CD, glblCmpyCd);
    //     ssmParam.put(NLBL2020Constant.SSM_MDSE_CD, sMsgALine.mdseCd_A1.getValue());
    //     ssmParam.put(NLBL2020Constant.SSM_STK_STS_CD, sMsgALine.fromStkStsCd_A1.getValue());
    //     ssmParam.put(NLBL2020Constant.SSM_CUR_LOC_NUM, S21StringUtil.concatStrings(sMsgALine.shipFromRtlWhCd_AH.getValue(), sMsgALine.shipFromRtlSwhCd_A1.getValue()));
    //     ssmParam.put(NLBL2020Constant.SSM_SVC_MACH_MSTR_STS_CD_LIST, new String[] {SVC_MACH_MSTR_STS.CREATED, SVC_MACH_MSTR_STS.REMOVED });
    //     ssmParam.put(NLBL2020Constant.SSM_SVC_MACH_MAINT_AVAL_FLG, ZYPConstant.FLG_ON_Y);

    //     if (ZYPCommonFunc.hasValue(sMsgALine.pickSvcConfigMstrPk_A1)) {
    //         ssmParam.put(NLBL2020Constant.SSM_PICK_SVC_CONFIG_MSTR_PK, sMsgALine.pickSvcConfigMstrPk_A1.getValue());
    //     }

    //     S21SsmEZDResult ssmResult = NLBL2020Query.getInstance().getAvailSingleQty(ssmParam);
    //     return (BigDecimal) ssmResult.getResultObject();
    // }
    // END 2022/08/08 R.Azucena [QC#60416, DEL]
    // END 2022/04/04 R.Azucena [QC#59802, ADD]

    /**
     * isCarrChk
     * @param sMsgALine NLBL2020_ASMsg
     * @param sMsg NLBL2020SMsg
     * @return boolean true:OK false:NG
     */
    private static boolean isCarrChk(NLBL2020_ASMsg sMsgALine, NLBL2020SMsg sMsg) {

        boolean hasErr = false;

        // Carrier Check
        if (SCE_ORD_TP.DIRECT_SALES.equals(sMsgALine.sceOrdTpCd_AH.getValue()) && !ZYPCommonFunc.hasValue(sMsgALine.carrNm_A1)) {

            sMsgALine.carrNm_A1.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Carrier" });
            sMsgALine.carrCd_A1.clear();
            hasErr =  true;

        } else if (ZYPCommonFunc.hasValue(sMsgALine.carrNm_A1)) {

            List<String> carrCdList = getCarrCdList(sMsg.glblCmpyCd.getValue(), sMsgALine.carrCd_A1.getValue(), sMsgALine.carrNm_A1.getValue());

            if (carrCdList == null || carrCdList.isEmpty()) {

                sMsgALine.carrNm_A1.setErrorInfo(1, NLBL2020Constant.NLZM2278E, new String[]{"Carrier"});
                sMsgALine.carrCd_A1.clear();
                hasErr = true;

            } else if (carrCdList.size() > 1) {

                if (!carrCdList.contains(sMsgALine.carrCd_A1.getValue())) {

                    sMsgALine.carrNm_A1.setErrorInfo(1, NLBL2020Constant.NLBM1341E, new String[]{"Carriers"});
                    sMsgALine.carrCd_A1.clear();
                    hasErr = true;
                }

            } else {

                ZYPEZDItemValueSetter.setValue(sMsgALine.carrCd_A1, carrCdList.get(0));
            }

        } else {

            sMsgALine.carrCd_A1.clear();
        }

        // Carrier Relation Check
        if (ZYPCommonFunc.hasValue(sMsgALine.carrCd_A1)) {
            // Check Shipping Service Level
            if (ZYPCommonFunc.hasValue(sMsgALine.shpgSvcLvlCd_A1)) {

                List<String> carrSvcLvlCdList = getCarrSvcLvlCdList(sMsg, sMsgALine.actlShpgSvcLvlCd_A1.getValue(), sMsgALine.carrCd_A1.getValue());

                if (carrSvcLvlCdList == null || carrSvcLvlCdList.isEmpty()) {

                    sMsgALine.carrNm_A1.setErrorInfo(1, NLBL2020Constant.NLBM1308E, new String[]{"Service Level", "Carrier"});
                    sMsgALine.actlShpgSvcLvlCd_A1.setErrorInfo(1, NLBL2020Constant.NLBM1308E, new String[]{"Service Level", "Carrier"});
                    hasErr = true;
                }
            }
        }

        if (hasErr) {

            return false;
        }

        return true;
    }

    /**
     * isSerialChk
     * @param sMsgALine NLBL2020_ASMsg
     * @param sMsg NLBL2020SMsg
     * @return boolean true:OK false:NG
     */
    private static boolean isSerialChk(NLBL2020_ASMsg sMsgALine, NLBL2020SMsg sMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.serNumTakeFlg_AH.getValue()) && !ZYPCommonFunc.hasValue(sMsgALine.serNum_A1) && !ZYPCommonFunc.hasValue(sMsgALine.serNum_AH.no(0))) {

            sMsgALine.serNum_A1.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Serial Number" });
            return false;

        } else if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.serNumTakeFlg_AH.getValue())
                && (ZYPCommonFunc.hasValue(sMsgALine.serNum_A1) || ZYPCommonFunc.hasValue(sMsgALine.serNum_AH.no(0)))) {

            ArrayList<String> serNumList = setSerNumList(sMsgALine);

            if (duplicateSerial(sMsgALine, serNumList, sMsg)) {

                sMsgALine.serNum_A1.setErrorInfo(1, NLBL2020Constant.NLBM1313E);
                return false;
            }

            // QC#18794
            if (ZYPCommonFunc.hasValue(sMsgALine.shipQty_A1) && !(SCE_ORD_TP.ITEM_CHANGE.equals(sMsgALine.sceOrdTpCd_AH.getValue()) && sMsgALine.shipQty_A1.getValue().compareTo(BigDecimal.ZERO) < 0)) {
                int shipQty = sMsgALine.shipQty_A1.getValue().abs().intValue();
                int serCnt = 0;
                for (String serNum : serNumList) {
                    if (ZYPCommonFunc.hasValue(serNum)) {
                        serCnt++;
                    }
                    if (shipQty < serCnt) {
                        sMsgALine.shipQty_A1.setErrorInfo(1, NLBL2020Constant.NLBM1355E);
                        return false;
                    }
                }
                if (shipQty != serCnt) {
                    sMsgALine.shipQty_A1.setErrorInfo(1, NLBL2020Constant.NLBM1355E, new String[] {"Serial Number" });
                    return false;
                }
            }

            for (String serNum : serNumList) {

                // Machine Master
                NLXC042001SerialInfo serialInfo = new NLXC042001SerialInfo();
                serialInfo.setGlblCmpyCd(sMsg.glblCmpyCd.getValue());
                serialInfo.setDupChkCd(ZYPCodeDataUtil.getVarCharConstValue("SER_DUP_CHK_CD", sMsg.glblCmpyCd.getValue()));
                serialInfo.setSceOrdTpCd(sMsgALine.sceOrdTpCd_AH.getValue());
                serialInfo.setMdseCd(sMsgALine.mdseCd_A1.getValue());
                serialInfo.setSerNum(serNum);
                /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 START */
                // serialInfo.setShipFromLocCd(sMsgALine.shipFromRtlWhCd_AH.getValue());
                serialInfo.setShipFromLocCd(sMsgALine.invtyLocCd_AH.getValue());
                /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 END */
                serialInfo.setTrxHdrNum(sMsgALine.trxHdrNum_AH.getValue());
                serialInfo.setSoNum(sMsgALine.soNum_HI.getValue());
                serialInfo.setOrdSvcConfigMstrPk(sMsgALine.pickSvcConfigMstrPk_A1.getValue());
                serialInfo.setMdlId(sMsgALine.mdlId_A1.getValue());
                // QC#20008 Start
                serialInfo.setStkStsCd(sMsgALine.fromStkStsCd_A1.getValue());
                // QC#20008 End
                serialInfo.setOnBatchType(ONBATCH_TYPE.ONLINE);

                if ((SCE_ORD_TP.ITEM_CHANGE.equals(sMsgALine.sceOrdTpCd_AH.getValue()) || SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sMsgALine.sceOrdTpCd_AH.getValue()))
                        && sMsgALine.shipQty_A1.getValue().signum() < 0) {

                    serialInfo.setRcvRtlWhCd(sMsgALine.shipFromRtlWhCd_AH.getValue());
                    if (SCE_ORD_TP.ITEM_CHANGE.equals(sMsgALine.sceOrdTpCd_AH.getValue())) {
                        Map<String, Object> ssmParam = new HashMap<String, Object>();
                        ssmParam.put("glblCmpyCd", sMsg.glblCmpyCd.getValue());
                        ssmParam.put("soNum", sMsgALine.soNum_HI.getValue());
                        ssmParam.put("soSlpNum", sMsgALine.soSlpNum_HI.getValue());

                        S21SsmEZDResult result = NLBL2020Query.getInstance().getRwsNumListBySo(ssmParam);

                        if (result.isCodeNormal()) {
                            List<String> rwsTargetList = (List<String>) result.getResultObject();
                            serialInfo.setRwsNum(rwsTargetList.get(0));
                        }
                    }
                    serialInfo = NLXC042001SvcMachMstrCheck.chkSvcMachMstrForRcvSerial(serialInfo);

                } else {

                    serialInfo = NLXC042001SvcMachMstrCheck.chkSvcMachMstrForShipSerial(serialInfo);
                }

                sMsgALine.xxMsgId_A1.clear();

                if (ZYPCommonFunc.hasValue(serialInfo.getErrMsgId())) {

                    if (serialInfo.getErrMsgId().endsWith("E")) {

                        sMsgALine.serNum_A1.setErrorInfo(1, serialInfo.getErrMsgId());
                        return false;

                    } else if (serialInfo.getErrMsgId().endsWith("W")) {

                        ZYPEZDItemValueSetter.setValue(sMsgALine.xxMsgId_A1, serialInfo.getErrMsgId());
                    }
                }

                // Asset check
                if (INVTY_ACCT.ASSET.equals(sMsgALine.invtyAcctCd_A1.getValue())) {

                    if (!chkAsset(sMsg.glblCmpyCd.getValue(), serNum, sMsgALine.mdseCd_A1.getValue())) {

                        sMsgALine.serNum_A1.setErrorInfo(1, NLBL2020Constant.NLBM1290E);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * duplicateSerial
     * @param sMsgALine NLBL2020_ASMsg
     * @param serNumList ArrayList<String>
     * @param sMsg NLBL2020SMsg
     * @return true:duplicate false:not duplicate
     */
    private static boolean duplicateSerial(NLBL2020_ASMsg sMsgALine, ArrayList<String> serNumList, NLBL2020SMsg sMsg) {

        for (String serNum : serNumList) {

            int serCnt = 0;

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxExstFlg_A2.getValue()) && sMsgALine.mdseCd_A1.getValue().equals(sMsg.A.no(i).mdseCd_A1.getValue())) {

                    if (serNum.equals(sMsg.A.no(i).serNum_A1.getValue()) && !sMsgALine.shipQty_A1.isError() && sMsgALine.shipQty_A1.getValue().abs().intValue() == 1) {

                        serCnt++;

                    } else {

                        for (int j = 0; j < sMsg.A.no(i).serNum_AH.length(); j++) {

                            if (serNum.equals(sMsg.A.no(i).serNum_AH.no(j).getValue())) {

                                serCnt++;
                            }
                        }
                    }

                    if (serCnt > 1) {

                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * duplicateSerial
     * @param sMsgBLine NLBL2020_BSMsg
     * @param sMsg NLBL2020SMsg
     * @return true:duplicate false:not duplicate
     */
    private static boolean duplicateSerial(NLBL2020_BSMsg sMsgBLine, NLBL2020SMsg sMsg) {

        int count = 0;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (sMsgBLine.mdseCd_B1.getValue().equals(sMsg.B.no(i).mdseCd_B1.getValue()) && sMsgBLine.serNum_B1.getValue().equals(sMsg.B.no(i).serNum_B1.getValue())) {

                count++;
            }

            if (count > 1) {

                return true;
            }
        }

        return false;
    }

    /**
     * chkAsset
     * @param glblCmpyCd String
     * @param serNum String
     * @param mdseCd String
     * @return boolean true:OK false:NG
     */
    private static boolean chkAsset(String glblCmpyCd, String serNum, String mdseCd) {

        DS_ASSET_MSTRTMsg countCond = new DS_ASSET_MSTRTMsg();
        countCond.setSQLID("006");
        countCond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        countCond.setConditionValue("serNum01", serNum);
        countCond.setConditionValue("mdseCd01", mdseCd);
        countCond.setConditionValue("actvAssetFlg01", ZYPConstant.FLG_ON_Y);

        int count = EZDTBLAccessor.count(countCond);

        if (count == 0) {

            return false;
        }

        return true;
    }

    /**
     * chkShipWarning
     * @param glblCmpyCd String
     * @param sMsgALine NLBL2020_ASMsg
     * @return true:warning false:not warning
     */
    public static boolean chkShipWarning(String glblCmpyCd, NLBL2020_ASMsg sMsgALine) {

        boolean warningFlg = false;

        // BOL_RELN check
        if (SCE_ORD_TP.DIRECT_SALES.equals(sMsgALine.sceOrdTpCd_AH.getValue()) && ZYPCommonFunc.hasValue(sMsgALine.proNum_A1)) {

            HashMap<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("carrCd", sMsgALine.carrNm_A1.getValue());
            ssmParam.put("proNum", sMsgALine.proNum_A1.getValue());

            S21SsmEZDResult result = NLBL2020Query.getInstance().getBolRelnCnt(ssmParam);

            if (result.isCodeNormal()) {

                BigDecimal bolCnt = (BigDecimal) result.getResultObject();

                if (bolCnt.intValue() > 0) {
                    sMsgALine.proNum_A1.setErrorInfo(2, NLBL2020Constant.NLBM1298W, new String[] {"Tracking Number" });
                    warningFlg = true;
                }
            }
        }

        // Carrier Check
        if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxExstFlg_MS.getValue()) && ZYPConstant.FLG_OFF_N.equals(sMsgALine.xxExstFlg_SR.getValue())) {

            sMsgALine.carrNm_A1.setErrorInfo(2, NLBL2020Constant.NLBM1273W);
            warningFlg = true;
        }

        // QC#23726 07/02/2018 Default Carrier Service Level Check.
        if (ZYPCommonFunc.hasValue(sMsgALine.carrCd_A1) && ZYPCommonFunc.hasValue(sMsgALine.actlShpgSvcLvlCd_A1)) {
            if (checkCustCarrSvcLvlRelation(glblCmpyCd, sMsgALine.trxHdrNum_A1.getValue(), sMsgALine.actlShpgSvcLvlCd_A1.getValue(), sMsgALine.carrCd_A1.getValue(), sMsgALine.shipToCustAcctCd_AH.getValue())) {
                sMsgALine.carrNm_A1.setErrorInfo(2, NLBL2020Constant.NLBM1369W, new String[] {"Actual Carrier", "Actual Service Level" });
                sMsgALine.actlShpgSvcLvlCd_A1.setErrorInfo(2, NLBL2020Constant.NLBM1369W, new String[] {"Actual Carrier", "Actual Service Level" });
                warningFlg = true;
            }
        }

        // Serial
        if (ZYPCommonFunc.hasValue(sMsgALine.xxMsgId_A1)) {

            sMsgALine.serNum_A1.setErrorInfo(2, sMsgALine.xxMsgId_A1.getValue());
            warningFlg = true;
        }

        // START 2022/12/08 T.Kuroda [QC#60810, ADD]
        // WMS Warehouse check
        if (isWMSWarehouseCheck(glblCmpyCd, sMsgALine)) {
            sMsgALine.xxExstFlg_A2.setErrorInfo(2, NLBL2020Constant.NLBM1378W);
            warningFlg = true;
        }
        // END   2022/12/08 T.Kuroda [QC#60810, ADD]

        // Set Backup Value for next warning check
        ZYPEZDItemValueSetter.setValue(sMsgALine.proNum_AB, sMsgALine.proNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsgALine.carrCd_AB, sMsgALine.carrCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsgALine.carrNm_AB, sMsgALine.carrNm_A1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsgALine.serNum_AB, sMsgALine.serNum_A1.getValue());

        return warningFlg;
    }

    /**
     * setShipParam
     * @param glblCmpyCd String
     * @param sMsgALine NLBL2020_ASMsg
     * @return NLZC210001PMsg
     */
    public static NLZC210001PMsg setShipParam(String glblCmpyCd, NLBL2020_ASMsg sMsgALine) {

        NLZC210001PMsg pMsg = new NLZC210001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, sMsgALine.invtyLocCd_AH);
        ZYPEZDItemValueSetter.setValue(pMsg.soNum, sMsgALine.soNum_HI);
        ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, sMsgALine.sceOrdTpCd_AH);
        ZYPEZDItemValueSetter.setValue(pMsg.shipDtTmTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(NLBL2020Constant.FORMAT_TIMESTAMP_TIME)));
        ZYPEZDItemValueSetter.setValue(pMsg.soSlpNum, sMsgALine.soSlpNum_HI);
        ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd_DT, SO_PROC_STS.SHIP);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, sMsgALine.mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.fromStkStsCd, sMsgALine.fromStkStsCd_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.shipQty, sMsgALine.shipQty_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.proNum, sMsgALine.proNum_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.vndCd, sMsgALine.carrCd_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.proNum, sMsgALine.proNum_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, sMsgALine.actlShpgSvcLvlCd_A1);

        if (ZYPCommonFunc.hasValue(sMsgALine.totFrtAmt_A1)) {

            ZYPEZDItemValueSetter.setValue(pMsg.totFrtAmt, sMsgALine.totFrtAmt_A1);

        } else {

            ZYPEZDItemValueSetter.setValue(pMsg.totFrtAmt, BigDecimal.ZERO);
        }

        return pMsg;
    }

    /**
     * setShipSerialParam
     * @param glblCmpyCd String
     * @param sMsgALine NLBL2020_ASMsg
     * @return ArrayList<NLZC210002PMsg>
     */
    public static ArrayList<NLZC210002PMsg> setShipSerialParam(String glblCmpyCd, NLBL2020_ASMsg sMsgALine) {

        ArrayList<String> serNumList = setSerNumList(sMsgALine);
        ArrayList<NLZC210002PMsg> pMsgArray = new ArrayList<NLZC210002PMsg>();

        for (String serNum : serNumList) {

            NLZC210002PMsg pMsg = new NLZC210002PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.whCd, sMsgALine.invtyLocCd_AH);
            ZYPEZDItemValueSetter.setValue(pMsg.soNum, sMsgALine.soNum_HI);
            ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, sMsgALine.sceOrdTpCd_AH);
            ZYPEZDItemValueSetter.setValue(pMsg.shipDtTmTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(NLBL2020Constant.FORMAT_TIMESTAMP_TIME)));
            ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd, SO_PROC_STS.SHIP);
            ZYPEZDItemValueSetter.setValue(pMsg.soSlpNum, sMsgALine.soSlpNum_HI);
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, sMsgALine.mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.serNum, serNum);
            ZYPEZDItemValueSetter.setValue(pMsg.serTakeDtTmTs, pMsg.shipDtTmTs.getValue());
            pMsgArray.add(pMsg);
        }

        return pMsgArray;
    }

    /**
     * setSerNumList
     * @param sMsgALine NLBL2020_ASMsg
     * @return ArrayList<String>
     */
    public static ArrayList<String> setSerNumList(NLBL2020_ASMsg sMsgALine) {

        ArrayList<String> serNumList = new ArrayList<String>();

        if (ZYPCommonFunc.hasValue(sMsgALine.serNum_A1) && ZYPCommonFunc.hasValue(sMsgALine.shipQty_A1) && sMsgALine.shipQty_A1.getValue().abs().intValue() == 1 && sMsgALine.pickConfQty_A1.getValue().abs().intValue() > 0) {

            if (ZYPCommonFunc.hasValue(sMsgALine.serNum_AH.no(0))) {

                for (int i = 0; i < sMsgALine.serNum_AH.length(); i++) {

                    if (ZYPCommonFunc.hasValue(sMsgALine.serNum_AH.no(i))) {

                        serNumList.add(sMsgALine.serNum_AH.no(i).getValue());
                    }
                }

            } else {
                serNumList.add(sMsgALine.serNum_A1.getValue());
            }

        } else {

            for (int i = 0; i < sMsgALine.serNum_AH.length(); i++) {

                if (ZYPCommonFunc.hasValue(sMsgALine.serNum_AH.no(i))) {

                    serNumList.add(sMsgALine.serNum_AH.no(i).getValue());
                }
            }
        }

        return serNumList;
    }

    /**
     * chkShipConfApiResult
     * @param cMsg NLBL2020CMsg
     * @param shipConfApiPMsgList ArrayList<NLZC210001PMsg>
     * @param shipConfApiPMsgList ArrayList<NLZC210002PMsg>
     * @return boolean
     */
    public static boolean chkShipConfApiResult(NLBL2020CMsg cMsg, ArrayList<NLZC210001PMsg> shipConfApiPMsgList, ArrayList<NLZC210002PMsg> shipConfSerApiPMsgList) {

        if (shipConfApiPMsgList != null && !shipConfApiPMsgList.isEmpty()) {

            for (NLZC210001PMsg shipConfApiPMsg : shipConfApiPMsgList) {

                if (!chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(shipConfApiPMsg))) {

                    return false;
                }
            }
        }

        if (shipConfSerApiPMsgList != null && !shipConfSerApiPMsgList.isEmpty()) {

            for (NLZC210002PMsg shipConfSerApiPMsg : shipConfSerApiPMsgList) {

                if (!chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(shipConfSerApiPMsg))) {

                    return false;
                }
            }
        }

        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param rwsHdrTMsg RWS_HDRTMsg
     * @param rwsDtlTMsgList ArrayList<RWS_DTLTMsg>
     * @param sMsgALine NLBL2020_ASMsg
     * @return NLZC206001PMsg
     */
    public static NLZC206001PMsg setRwsPutAwayConfApiParam(String glblCmpyCd, RWS_HDRTMsg rwsHdrTMsg, ArrayList<RWS_DTLTMsg> rwsDtlTMsgList, NLBL2020_ASMsg sMsgALine) {

        NLZC206001PMsg putAwayS21DcApiPMsg = new NLZC206001PMsg();

        // Set Param
        ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.machMstrUpdFlg, ZYPConstant.FLG_ON_Y);

        int count = 0;
        int serCount = 0;

        for (RWS_DTLTMsg rwsDtl : rwsDtlTMsgList) {

            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(count).rwsNum, rwsDtl.rwsNum);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(count).rwsDtlLineNum, rwsDtl.rwsDtlLineNum);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(count).invtyStkStsCd, rwsDtl.invtyStkStsCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(count).sceOrdTpCd, sMsgALine.sceOrdTpCd_AH);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(count).rwsStkDtTmTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(NLBL2020Constant.FORMAT_TIMESTAMP_TIME)));
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(count).rwsStkQty, sMsgALine.shipQty_A1.getValue().abs());
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(count).whCd, rwsDtl.invtyLocCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(count).mdseCd, sMsgALine.mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(count).rwsRefNum, rwsHdrTMsg.rwsRefNum);
            putAwayS21DcApiPMsg.RWSPutAwayList.no(count).destInvtyLocCd.clear();

            // Set Serial
            if (ZYPConstant.FLG_ON_Y.equals(rwsDtl.serNumTakeFlg.getValue())) {

                if (ZYPCommonFunc.hasValue(sMsgALine.serNum_A1) && sMsgALine.shipQty_A1.getValue().abs().intValue() == 1) {

                    ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(serCount).rwsDtlLineNum, rwsDtl.rwsDtlLineNum);
                    ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(serCount).serNum, sMsgALine.serNum_A1);
                    ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(serCount).mdseCd, sMsgALine.mdseCd_A1);
                    serCount++;

                } else if (ZYPCommonFunc.hasValue(sMsgALine.serNum_AH.no(0))) {

                    for (int i = 0; i < sMsgALine.serNum_AH.length(); i++) {

                        if (ZYPCommonFunc.hasValue(sMsgALine.serNum_AH.no(i))) {

                            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(serCount).rwsDtlLineNum, rwsDtl.rwsDtlLineNum);
                            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(serCount).serNum, sMsgALine.serNum_AH.no(i));
                            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(serCount).mdseCd, sMsgALine.mdseCd_A1);
                            serCount++;
                        }
                    }
                }
            }

            count++;
        }

        putAwayS21DcApiPMsg.RWSPutAwayList.setValidCount(count);
        putAwayS21DcApiPMsg.RcvSerNumList.setValidCount(serCount);

        return putAwayS21DcApiPMsg;
    }

    /**
     * callSerialTransactionAPI
     * @param cMsg NLBL2020CMsg
     * @param sMsgALine NLBL2020_ASMsg
     * @param serNumList ArrayList<String>
     * @return boolean
     */
    public static boolean callSerialTransactionAPI(NLBL2020CMsg cMsg, NLBL2020_ASMsg sMsgALine, ArrayList<String> serNumList) {

        String sceOrdTpCd = sMsgALine.sceOrdTpCd_AH.getValue();
        int serListIndex = 0;

        NLZC302001PMsg serTrxApipMsg = new NLZC302001PMsg();
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());

        for (String serNum : serNumList) {

            ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serNum, serNum);
            ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).mdseCd, sMsgALine.mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(NLBL2020Constant.FORMAT_TIMESTAMP_TIME)));
            ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxSrcTpCd, SER_TRX_SRC_TP.SO);
            ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxSrcHdrNum, sMsgALine.soNum_HI);
            ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxSrcLineNum, sMsgALine.soSlpNum_HI);
            ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxRefNum, sMsgALine.trxHdrNum_HI);
            ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).fromLocCd, sMsgALine.invtyLocCd_AH);
            ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).toLocCd, sMsgALine.toInvtyLocCd_A1);
            ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).manCratFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).toStkStsCd, sMsgALine.fromStkStsCd_A1);
            ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).locStsCd, LOC_STS.DC_STOCK);

            if (SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxEventCd, SER_TRX_EVENT.STOCK_STATUS_CHANGE);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).toStkStsCd, sMsgALine.toStkStsCd_AH);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).fromStkStsCd, sMsgALine.fromStkStsCd_A1);

            } else if (SCE_ORD_TP.SUB_WH_CHANGE.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxEventCd, SER_TRX_EVENT.SUB_WAREHOUSE_CHANGE);

            } else if (SCE_ORD_TP.ITEM_CHANGE.equals(sceOrdTpCd)) {

                if (BigDecimal.ZERO.compareTo(sMsgALine.shipQty_A1.getValue()) < 0) {

                    ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxEventCd, SER_TRX_EVENT.ITEM_CHANGE_STOCK_OUT);

                } else {

                    ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxEventCd, SER_TRX_EVENT.ITEM_CHANGE_STOCK_IN);
                }

            } else if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)) {

                if (BigDecimal.ZERO.compareTo(sMsgALine.shipQty_A1.getValue()) < 0) {

                    ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxEventCd, SER_TRX_EVENT.ITEM_CHANGE_STOCK_OUT);

                } else {

                    ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxEventCd, SER_TRX_EVENT.ITEM_CHANGE_STOCK_IN);
                }

            } else if (SCE_ORD_TP.INTERNAL_TRANSFER.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxEventCd, SER_TRX_EVENT.WH_TRANSFER_STOCK_OUT);
            }

            if (SCE_ORD_TP.INTERNAL_TRANSFER.equals(sceOrdTpCd)) {

                serListIndex++;

                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serNum, serNum);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).mdseCd, sMsgALine.mdseCd_A1);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(NLBL2020Constant.FORMAT_TIMESTAMP_TIME)));
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxEventCd, SER_TRX_EVENT.WH_TRANSFER_STOCK_IN);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxSrcTpCd, SER_TRX_SRC_TP.SO);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxSrcHdrNum, sMsgALine.soNum_HI);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxSrcLineNum, sMsgALine.soSlpNum_HI);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).serTrxRefNum, sMsgALine.trxHdrNum_A1);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).fromLocCd, sMsgALine.invtyLocCd_AH);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).toLocCd, sMsgALine.toInvtyLocCd_A1);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).manCratFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).toStkStsCd, sMsgALine.fromStkStsCd_A1);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serListIndex).locStsCd, LOC_STS.DC_STOCK);
            }

            serListIndex++;
        }

        serTrxApipMsg.UpdateDetailList.setValidCount(serListIndex);

        NLZC302001 serTranApi = new NLZC302001();
        serTranApi.execute(serTrxApipMsg, ONBATCH_TYPE.ONLINE);

        if (!chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(serTrxApipMsg))) {

            return false;
        }

        return true;
    }

    /**
     * getAllocSvcMachMstr
     * @param glblCmpyCd String
     * @param record NLBL2020_ASMsg
     * @return SVC_MACH_MSTRTMsgArray
     */
    private static SVC_MACH_MSTRTMsgArray getAllocSvcMachMstr(String glblCmpyCd, NLBL2020_ASMsg sMsgALine) {

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        svcMachMstrTMsg.setSQLID("013");
        svcMachMstrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        svcMachMstrTMsg.setConditionValue("trxHdrNum01", sMsgALine.trxHdrNum_AH.getValue());
        svcMachMstrTMsg.setConditionValue("trxLineNum01", sMsgALine.trxLineNum_AH.getValue());
        svcMachMstrTMsg.setConditionValue("trxLineSubNum01", sMsgALine.trxLineSubNum_AH.getValue());
        svcMachMstrTMsg.setConditionValue("svcMachMaintAvalFlg01", ZYPConstant.FLG_OFF_N);

        return (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(svcMachMstrTMsg);
    }

    /**
     * callMachMstrApiForSerial
     * @param scrnMsgALine NLBL2020_ASMsg
     * @param cMsg NLBL2020CMsg
     * @param serNumList ArrayList<String>
     * @return true:Success false:
     */
    public static boolean callMachMstrApiForSerial(NLBL2020_ASMsg scrnMsgALine, NLBL2020CMsg cMsg, ArrayList<String> serNumList) {

        String sceOrdTpCd = scrnMsgALine.sceOrdTpCd_AH.getValue();

        for (String serNum : serNumList) {

            List<Map<String, Object>> svcMachMstrMapList = new ArrayList<Map<String, Object>>();

            HashMap<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
            ssmParam.put("serNum", serNum);
            ssmParam.put("mdseCd", scrnMsgALine.mdseCd_A1.getValue());
            ssmParam.put("svcMachMstrStsCdTerm", SVC_MACH_MSTR_STS.TERMINATED);
            ssmParam.put("svcMachMstrStsCdDup", SVC_MACH_MSTR_STS.DUPLICATE);

            S21SsmEZDResult result = NLBL2020Query.getInstance().getSvcMachMstr(ssmParam);

            if (result.isCodeNormal()) {

                svcMachMstrMapList = (ArrayList<Map<String, Object>>) result.getResultObject();

            } else {

                svcMachMstrMapList = null;
            }

            if (svcMachMstrMapList == null || svcMachMstrMapList.size() == 0) {

                if (SCE_ORD_TP.ITEM_CHANGE.equals(scrnMsgALine.sceOrdTpCd_AH.getValue()) || SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(scrnMsgALine.sceOrdTpCd_AH.getValue())) {

                    if (scrnMsgALine.shipQty_A1.getValue().signum() >= 0) {

                        continue;
                    }
                }

                NSZC001001PMsg pMsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cMsg.slsDt.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.serNum, serNum);
                ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, scrnMsgALine.mdseCd_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
                ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, cMsg.slsDt.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, scrnMsgALine.toInvtyLocCd_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
                ZYPEZDItemValueSetter.setValue(pMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, scrnMsgALine.fromStkStsCd_A1);

                if (SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(sceOrdTpCd)) {

                    ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, scrnMsgALine.toStkStsCd_AH);
                }

                // Call
                NSZC001001 api = new NSZC001001();
                api.execute(pMsg, ONBATCH_TYPE.ONLINE);

                if (!chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

                    return false;
                }

            } else {

                Map<String, Object> svcMachMstrMap = svcMachMstrMapList.get(0);

                String svcMachMaintAvalFlg = (String) svcMachMstrMap.get("SVC_MACH_MAINT_AVAL_FLG");
                String stkStsCd = (String) svcMachMstrMap.get("STK_STS_CD");
                String svcMachMstrStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD");
                String svcMachUsgStsCd = (String) svcMachMstrMap.get("SVC_MACH_USG_STS_CD");
                BigDecimal svcMachMstrPk = (BigDecimal) svcMachMstrMap.get("SVC_MACH_MSTR_PK");
                BigDecimal svcConfigMstrPk = (BigDecimal) svcMachMstrMap.get("SVC_CONFIG_MSTR_PK");

                if (ZYPConstant.FLG_OFF_N.equals(svcMachMaintAvalFlg)) {

                    NSZC001001PMsg pMsg = new NSZC001001PMsg();
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cMsg.slsDt.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
                    ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);

                    // Call
                    NSZC001001 api = new NSZC001001();
                    api.execute(pMsg, ONBATCH_TYPE.ONLINE);

                    if (!chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

                        return false;
                    }
                }

                // Update
                NSZC001001PMsg pMsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cMsg.slsDt.getValue());

                if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd) || SCE_ORD_TP.ITEM_CHANGE.equals(sceOrdTpCd)) {

                    if (scrnMsgALine.shipQty_A1.getValue().signum() > 0) {

                        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.DISPOSAL.code);
                        ZYPEZDItemValueSetter.setValue(pMsg.serNum, serNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, svcConfigMstrPk);
                        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
                        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.TERMINATED);
                        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, stkStsCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.effThruDt, cMsg.slsDt.getValue());
                        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, svcMachUsgStsCd);

                    } else {

                        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.ITEM_CHANGE.code);
                        ZYPEZDItemValueSetter.setValue(pMsg.serNum, serNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, scrnMsgALine.mdseCd_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
                        ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, cMsg.slsDt.getValue());
                        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
                        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                        ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, scrnMsgALine.toInvtyLocCd_A1);
                    }

                } else {

                    ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.UPDATE_INVENTORY.code);
                    ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, svcConfigMstrPk);
                    ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
                    ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, svcMachMstrStsCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                    ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, scrnMsgALine.toInvtyLocCd_A1);
                    ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, scrnMsgALine.fromStkStsCd_A1);

                    if (SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(sceOrdTpCd)) {

                        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, scrnMsgALine.toStkStsCd_AH);
                    }
                }

                // Call
                NSZC001001 api = new NSZC001001();
                api.execute(pMsg, ONBATCH_TYPE.ONLINE);

                if (!chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

                    return false;
                }
            }
        }

        return true;
    }

    /**
     * callMachMstrApiForNonSerial
     * @param sMsgLineA NLBL2020_ASMsg
     * @param cMsg NLBL2020CMsg
     * @return true:Success false:Error
     */
    public static boolean callMachMstrApiForNonSerial(NLBL2020_ASMsg sMsgLineA, NLBL2020CMsg cMsg) {

        int shipQty = sMsgLineA.shipQty_A1.getValue().abs().intValue();

        SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = getAllocSvcMachMstr(cMsg.glblCmpyCd.getValue(), sMsgLineA);
        String sceOrdTpCd = sMsgLineA.sceOrdTpCd_AH.getValue();

        if (SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {
            return true;
        }

        // Allocated Machine Master
        for (int i = 0; i < svcMachMstrTMsgArray.length(); i++) {

            // Allocation Off
            if (ZYPConstant.FLG_OFF_N.equals(svcMachMstrTMsgArray.no(i).svcMachMaintAvalFlg.getValue())) {

                NSZC001001PMsg machMstrUpdApiPMsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.slsDt, cMsg.slsDt.getValue());
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachMstrPk, svcMachMstrTMsgArray.no(i).svcMachMstrPk);

                // Call
                NSZC001001 api = new NSZC001001();
                api.execute(machMstrUpdApiPMsg, ONBATCH_TYPE.ONLINE);

                if (!chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(machMstrUpdApiPMsg))) {

                    return false;
                }
            }

            // Update
            if (i < shipQty) {

                NSZC001001PMsg machMstrUpdApiPMsg = setSvcMachMstrApiPmsgForUpd(sMsgLineA, cMsg, svcMachMstrTMsgArray.no(i));

                // Call
                NSZC001001 api = new NSZC001001();
                api.execute(machMstrUpdApiPMsg, ONBATCH_TYPE.ONLINE);

                if (!chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(machMstrUpdApiPMsg))) {

                    return false;
                }
            }
        }

        int updateCount = svcMachMstrTMsgArray.length();
        shipQty = shipQty - updateCount;

        // Machine Master located at ship from WH
        if (shipQty > 0) {

            svcMachMstrTMsgArray.clear();

            SVC_MACH_MSTRTMsg notAlocCond = new SVC_MACH_MSTRTMsg();
            notAlocCond.setSQLID("009");
            notAlocCond.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            notAlocCond.setConditionValue("mdseCd01", sMsgLineA.mdseCd_A1.getValue());
            notAlocCond.setConditionValue("curLocNum01", sMsgLineA.toInvtyLocCd_A1.getValue());
            notAlocCond.setConditionValue("svcMachMaintAvalFlg01", ZYPConstant.FLG_ON_Y);
            notAlocCond.setConditionValue("svcMachMstrStsCd01A", SVC_MACH_MSTR_STS.CREATED);
            notAlocCond.setConditionValue("svcMachMstrStsCd01B", SVC_MACH_MSTR_STS.REMOVED);

            svcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(notAlocCond);

            int loopCnt = svcMachMstrTMsgArray.length();

            if (loopCnt > shipQty) {

                loopCnt = shipQty;
            }

            for (int i = 0; i < loopCnt; i++) {

                NSZC001001PMsg machMstrUpdApiPMsg = setSvcMachMstrApiPmsgForUpd(sMsgLineA, cMsg, svcMachMstrTMsgArray.no(i));

                // Call
                NSZC001001 api = new NSZC001001();
                api.execute(machMstrUpdApiPMsg, ONBATCH_TYPE.ONLINE);

                if (!chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(machMstrUpdApiPMsg))) {

                    return false;
                }
            }

            shipQty = shipQty - loopCnt;
        }

        // New Machine Master Created
        for (int i = 0; i < shipQty; i++) {

            if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd) || SCE_ORD_TP.ITEM_CHANGE.equals(sceOrdTpCd)) {

                if (sMsgLineA.shipQty_A1.getValue().signum() > 0) {

                    continue;
                }
            }

            NSZC001001PMsg machMstrUpdApiPMsg = new NSZC001001PMsg();
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.slsDt, cMsg.slsDt.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.mdseCd, sMsgLineA.mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.effFromDt, cMsg.slsDt.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.curLocNum, sMsgLineA.toInvtyLocCd_A1);
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.stkStsCd, sMsgLineA.fromStkStsCd_A1);

            if (SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.stkStsCd, sMsgLineA.toStkStsCd_AH);
            }

            // Call
            NSZC001001 api = new NSZC001001();
            api.execute(machMstrUpdApiPMsg, ONBATCH_TYPE.ONLINE);

            if (!chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(machMstrUpdApiPMsg))) {

                return false;
            }
        }

        return true;
    }

    /**
     * setSvcMachMstrApiPmsgForUpd
     * @param sMsgLineA NLBL2020_ASMsg
     * @param cMsg NLBL2020CMsg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @return NSZC001001PMsg
     */
    private static NSZC001001PMsg setSvcMachMstrApiPmsgForUpd(NLBL2020_ASMsg sMsgLineA, NLBL2020CMsg cMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

        String sceOrdTpCd = sMsgLineA.sceOrdTpCd_AH.getValue();

        NSZC001001PMsg machMstrUpdApiPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.slsDt, cMsg.slsDt.getValue());

        if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd) || SCE_ORD_TP.ITEM_CHANGE.equals(sceOrdTpCd)) {

            if (sMsgLineA.shipQty_A1.getValue().signum() > 0) {

                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.xxModeCd, ProcessMode.DISPOSAL.code);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.serNum, svcMachMstrTMsg.serNum);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.TERMINATED);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.stkStsCd, svcMachMstrTMsg.stkStsCd);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.effThruDt, cMsg.slsDt.getValue());
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachUsgStsCd, svcMachMstrTMsg.svcMachUsgStsCd);

            } else {

                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.xxModeCd, ProcessMode.ITEM_CHANGE.code);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.serNum, svcMachMstrTMsg.serNum);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.mdseCd, svcMachMstrTMsg.mdseCd);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.effFromDt, cMsg.slsDt.getValue());
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.curLocNum, sMsgLineA.toInvtyLocCd_A1);
            }

        } else {

            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.xxModeCd, ProcessMode.UPDATE_INVENTORY.code);
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachMstrStsCd, svcMachMstrTMsg.svcMachMstrStsCd);
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.curLocNum, sMsgLineA.toInvtyLocCd_A1);
            ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.stkStsCd, sMsgLineA.fromStkStsCd_A1);

            if (SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.stkStsCd, sMsgLineA.toStkStsCd_AH);
            }
        }

        return machMstrUpdApiPMsg;
    }

    /**
     * callMachMstrApiAllocOff
     * @param soNumList ArrayList<String>
     * @param cMsg NLBL2020CMsg
     * @return boolean
     */
    public static boolean callMachMstrApiAllocOff(ArrayList<String> soNumList, NLBL2020CMsg cMsg) {

        for (String soNum : soNumList) {

            HashMap<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
            ssmParam.put("soNum", soNum);
            ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);

            S21SsmEZDResult result = NLBL2020Query.getInstance().getSvcMachMstrPk(ssmParam);

            if (result.isCodeNormal()) {

                List<BigDecimal> svcMachMstrPkList = (List<BigDecimal>) result.getResultObject();

                for (BigDecimal svcMachMstrPk : svcMachMstrPkList) {

                    NSZC001001PMsg machMstrUpdApiPMsg = new NSZC001001PMsg();
                    ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.slsDt, cMsg.slsDt.getValue());
                    ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
                    ZYPEZDItemValueSetter.setValue(machMstrUpdApiPMsg.svcMachMstrPk, svcMachMstrPk);

                    NSZC001001 api = new NSZC001001();
                    api.execute(machMstrUpdApiPMsg, ONBATCH_TYPE.ONLINE);

                    if (!chkApiResult(cMsg, S21ApiUtil.getXxMsgIdList(machMstrUpdApiPMsg))) {

                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * setSearchPickListRslt
     * @param result S21SsmEZDResult
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    public static void setSearchPickListRslt(S21SsmEZDResult result, NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        List<Map<String, Object>> pickMapList = (List<Map<String, Object>>) result.getResultObject();

        int cnt = 0;
        int shpgBalQty = 0;
        int pickConfQty = 0;

        String preSoNum = "";
        String preSoSlipNum = "";

        List<String> pickSerNumList = new ArrayList<String>();
        List<String> asgSerNumList = new ArrayList<String>();

        pick : for (Map<String, Object> pickMap : pickMapList) {

            if (NLBL2020Constant.MAX_RECORD == cnt) {

                cMsg.setMessageInfo(NLBL2020Constant.NZZM0001W);
                break;
            }

            if (!preSoNum.equals((String) pickMap.get("SO_NUM")) || !preSoSlipNum.equals((String) pickMap.get("SO_SLP_NUM"))) {

                if (shpgBalQty > 0) {

                    for (int i = 0; i < shpgBalQty; i++) {

                        if (NLBL2020Constant.MAX_RECORD == cnt) {

                            cMsg.setMessageInfo(NLBL2020Constant.NZZM0001W);
                            break pick;
                        }

                        EZDMsg.copy(sMsg.B.no(cnt - 1), null, sMsg.B.no(cnt), null);

                        if (pickConfQty > 0) {

                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickConfQty_B1, BigDecimal.ONE);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickConfQty_HD, BigDecimal.ONE);

                            if (!pickSerNumList.isEmpty()) {

                                ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_B1, pickSerNumList.get(0));
                                ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_HD, pickSerNumList.get(0));
                                ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_BB, pickSerNumList.get(0));

                                pickSerNumList.remove(0);

                            } else {

                                sMsg.B.no(cnt).serNum_B1.clear();
                                sMsg.B.no(cnt).serNum_HD.clear();
                                sMsg.B.no(cnt).serNum_BB.clear();
                            }

                            pickConfQty--;

                        } else {

                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickConfQty_B1, BigDecimal.ZERO);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickConfQty_HD, BigDecimal.ZERO);

                            if (!asgSerNumList.isEmpty()) {

                                ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_B1, asgSerNumList.get(0));
                                ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_HD, asgSerNumList.get(0));
                                ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_BB, asgSerNumList.get(0));

                                asgSerNumList.remove(0);

                            } else {

                                sMsg.B.no(cnt).serNum_B1.clear();
                                sMsg.B.no(cnt).serNum_HD.clear();
                                sMsg.B.no(cnt).serNum_BB.clear();

                            }
                        }

                        cnt++;
                    }
                }

                shpgBalQty = ((BigDecimal) pickMap.get("SHPG_BAL_QTY")).intValue();
                pickConfQty = ((BigDecimal) pickMap.get("PICK_CONF_QTY")).intValue();

                pickSerNumList = new ArrayList<String>();
                asgSerNumList = new ArrayList<String>();
            }

            // Add Serial Info
            if (ZYPCommonFunc.hasValue((String) pickMap.get("SER_NUM"))) {

                if (ZYPConstant.FLG_ON_Y.equals((String) pickMap.get("WH_PICK_FLG"))) {

                    pickSerNumList.add((String) pickMap.get("SER_NUM"));

                } else if (ZYPConstant.FLG_ON_Y.equals((String) pickMap.get("ORD_ASG_FLG"))) {

                    asgSerNumList.add((String) pickMap.get("SER_NUM"));
                }
            }

            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).trxHdrNum_B1, (String) pickMap.get("SRC_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).dplyLineNum_B1, (String) pickMap.get("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).soNum_B1, (String) pickMap.get("SO_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).soSlpNum_B1, (String) pickMap.get("SO_SLP_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).rtlWhNm_B1, (String) pickMap.get("SHIP_FROM_RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).shipFromRtlSwhCd_B1, (String) pickMap.get("SHIP_FROM_RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).mdseCd_B1, (String) pickMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).mdseDescShortTxt_B1, (String) pickMap.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickConfQty_B1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickCpltQty_B1, (BigDecimal) pickMap.get("PICK_CONF_QTY"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickSvcConfigMstrPk_B1, (BigDecimal) pickMap.get("PICK_SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).shpgQty_B1, (BigDecimal) pickMap.get("SHPG_QTY"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).shipQty_B1, (BigDecimal) pickMap.get("SHIP_QTY"));

            // Hidden
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).trxHdrNum_HD, (String) pickMap.get("SRC_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).trxLineNum_B1, (String) pickMap.get("SRC_ORD_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).trxLineSubNum_B1, (String) pickMap.get("SRC_ORD_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).dplyLineNum_HD, (String) pickMap.get("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).trxHdrNum_HT, (String) pickMap.get("TRX_HDR_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).trxLineNum_HT, (String) pickMap.get("TRX_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).trxLineSubNum_HT, (String) pickMap.get("TRX_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).soNum_HD, (String) pickMap.get("SO_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).soSlpNum_HD, (String) pickMap.get("SO_SLP_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).sceOrdTpCd_B1, (String) pickMap.get("SCE_ORD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).shipFromRtlWhCd_HD, (String) pickMap.get("SHIP_FROM_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).invtyLocCd_HD, (String) pickMap.get("SHIP_FROM_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).invtyAcctCd_B1, (String) pickMap.get("INVTY_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).mdlId_B1, (BigDecimal) pickMap.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickConfQty_HD, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickConfQty_DB, (BigDecimal) pickMap.get("DB_PICK_QTY"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).shpgBalQty_HD, (BigDecimal) pickMap.get("SHPG_BAL_QTY"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).soLineOpenFlg_B1, (String) pickMap.get("SO_LINE_OPEN_FLG"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNumTakeFlg_B1, (String) pickMap.get("SER_NUM_TAKE_FLG"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).trxSrcTpCd_HD, (String) pickMap.get("TRX_SRC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).ezUpTime_H1, (String) pickMap.get("SO_EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).ezUpTimeZone_H1, (String) pickMap.get("SO_EZUPTIMEZONE"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).ezUpTime_H2, (String) pickMap.get("SOD_EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).ezUpTimeZone_H2, (String) pickMap.get("SOD_EZUPTIMEZONE"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).shipFlg_HD, (String) pickMap.get("SHIP_FLG"));

            // Who Column
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).xxRecHistCratTs_B1, (String) pickMap.get("SOD_EZINTIME"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).xxRecHistCratByNm_B1, ZYPRecHistUtil.getFullNameForRecHist((String) pickMap.get("SOD_EZINUSERID")));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).xxRecHistUpdTs_B1, (String) pickMap.get("SOD_EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).xxRecHistUpdByNm_B1, ZYPRecHistUtil.getFullNameForRecHist((String) pickMap.get("SOD_EZUPUSERID")));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).xxRecHistTblNm_B1, "SHPG_ORD_DTL");

            // Serialized
            if (ZYPConstant.FLG_ON_Y.equals((String) pickMap.get("SER_NUM_TAKE_FLG"))) {

                if (pickConfQty > 0) {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickCpltQty_B1, BigDecimal.ONE);
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickConfQty_HD, BigDecimal.ONE);

                    if (!pickSerNumList.isEmpty()) {

                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_B1, pickSerNumList.get(0));
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_HD, pickSerNumList.get(0));
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_BB, pickSerNumList.get(0));

                        pickSerNumList.remove(0);
                    }

                    pickConfQty--;

                } else {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickCpltQty_B1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickConfQty_HD, BigDecimal.ZERO);

                    if (!asgSerNumList.isEmpty()) {

                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_B1, asgSerNumList.get(0));
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_HD, asgSerNumList.get(0));
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_BB, asgSerNumList.get(0));

                        asgSerNumList.remove(0);
                    }
                }

                shpgBalQty--;

            } else {

                pickConfQty = 0;
                shpgBalQty = 0;
            }

            preSoNum = (String) pickMap.get("SO_NUM");
            preSoSlipNum = (String) pickMap.get("SO_SLP_NUM");

            cnt++;
        }

        // Remaining Qty
        if (NLBL2020Constant.MAX_RECORD > cnt && shpgBalQty > 0) {

            for (int i = 0; i < shpgBalQty; i++) {

                if (NLBL2020Constant.MAX_RECORD == cnt) {

                    cMsg.setMessageInfo(NLBL2020Constant.NZZM0001W);
                    break;
                }

                EZDMsg.copy(sMsg.B.no(cnt - 1), null, sMsg.B.no(cnt), null);

                if (pickConfQty > 0) {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickCpltQty_B1, BigDecimal.ONE);
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickConfQty_HD, BigDecimal.ONE);

                    if (!pickSerNumList.isEmpty()) {

                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_B1, pickSerNumList.get(0));
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_HD, pickSerNumList.get(0));
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_BB, pickSerNumList.get(0));

                        pickSerNumList.remove(0);
                    } else {

                        sMsg.B.no(cnt).serNum_B1.clear();
                        sMsg.B.no(cnt).serNum_HD.clear();
                        sMsg.B.no(cnt).serNum_BB.clear();

                    }

                    pickConfQty--;

                } else {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickCpltQty_B1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).pickConfQty_HD, BigDecimal.ZERO);

                    if (!asgSerNumList.isEmpty()) {

                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_B1, asgSerNumList.get(0));
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_HD, asgSerNumList.get(0));
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(cnt).serNum_BB, asgSerNumList.get(0));

                        asgSerNumList.remove(0);
                    } else {

                        sMsg.B.no(cnt).serNum_B1.clear();
                        sMsg.B.no(cnt).serNum_HD.clear();
                        sMsg.B.no(cnt).serNum_BB.clear();
                    }
                }

                cnt++;
            }
        }

        sMsg.B.setValidCount(cnt);

        // 1 page copy
        int i = 0;

        for (; i < sMsg.B.getValidCount(); i++) {

            if (i == cMsg.B.length()) {

                break;
            }

            EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
        }

        cMsg.B.setValidCount(i);

        // set Pagenation
        cMsg.xxPageShowFromNum_B.setValue(1);
        cMsg.xxPageShowToNum_B.setValue(cMsg.B.getValidCount());
        cMsg.xxPageShowOfNum_B.setValue(cnt);
    }

    /**
     * chkSubmitRecord
     * @param sMsgBLine NLBL2020_BCMsg
     * @param sMsg NLBL2020SMsg
     * @return true:OK false:NG
     */
    public static boolean chkSubmitRecord(NLBL2020_BSMsg sMsgBLine, NLBL2020SMsg sMsg) {

        // Pick Qty check
        if (!ZYPCommonFunc.hasValue(sMsgBLine.pickConfQty_B1)) {

            sMsgBLine.pickConfQty_B1.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Pick Quantity" });
            return false;

        } else if (BigDecimal.ZERO.compareTo(sMsgBLine.pickConfQty_B1.getValue()) > 0) {

            if (BigDecimal.ZERO.compareTo(sMsgBLine.pickConfQty_B1.getValue().add(sMsgBLine.pickCpltQty_B1.getValue())) > 0) {
                sMsgBLine.pickConfQty_B1.setErrorInfo(1, NLBL2020Constant.NLZM2277E, new String[] {"Pick Qty", "Picked Qty" });
                return false;
            }
        }

        // Non-Serialized
        if (!ZYPConstant.FLG_ON_Y.equals(sMsgBLine.serNumTakeFlg_B1.getValue())) {

            BigDecimal pickQty = sMsgBLine.pickConfQty_B1.getValue();
            BigDecimal ordQty = sMsgBLine.shpgQty_B1.getValue();
            BigDecimal shipQty = sMsgBLine.shipQty_B1.getValue();
            BigDecimal pickQtyBk = sMsgBLine.pickCpltQty_B1.getValue();
            BigDecimal avalPickQty = ordQty.subtract(pickQtyBk);

            if (pickQty.compareTo(ordQty) > 0) {

                sMsgBLine.pickConfQty_B1.setErrorInfo(1, NLBL2020Constant.NLZM2316E, new String[] {"Pick Qty", "Order Qty" });
                return false;
            }

            if (pickQty.compareTo(avalPickQty) > 0) {

                sMsgBLine.pickConfQty_B1.setErrorInfo(1, NLBL2020Constant.NLZM2316E, new String[] {"Pick Qty", avalPickQty.toPlainString() });
                return false;
            }

        // Serialized
        } else {

            // Qty Check
            if (sMsgBLine.pickConfQty_B1.getValue().compareTo(BigDecimal.ONE) > 0) {

                sMsgBLine.pickConfQty_B1.setErrorInfo(1, NLBL2020Constant.NLZM2316E, new String[] {"Pick Qty", "1" });
                return false;
            }

            // Serial check
            if (sMsgBLine.pickConfQty_B1.getValue().compareTo(BigDecimal.ZERO) > 0) {

                if (!ZYPCommonFunc.hasValue(sMsgBLine.serNum_B1)) {

                    sMsgBLine.serNum_B1.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Pickup Serial Number" });
                    return false;

                } else {

                    if (duplicateSerial(sMsgBLine, sMsg)) {

                        sMsgBLine.serNum_B1.setErrorInfo(1, NLBL2020Constant.NLBM1313E);
                        return false;
                    }

                    // Machine Master
                    NLXC042001SerialInfo serialInfo = new NLXC042001SerialInfo();
                    serialInfo.setGlblCmpyCd(sMsg.glblCmpyCd.getValue());
                    serialInfo.setDupChkCd(ZYPCodeDataUtil.getVarCharConstValue("SER_DUP_CHK_CD", sMsg.glblCmpyCd.getValue()));
                    serialInfo.setSceOrdTpCd(sMsgBLine.sceOrdTpCd_B1.getValue());
                    serialInfo.setMdseCd(sMsgBLine.mdseCd_B1.getValue());
                    serialInfo.setSerNum(sMsgBLine.serNum_B1.getValue());
                    /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 START */
                    // serialInfo.setShipFromLocCd(sMsgBLine.shipFromRtlWhCd_HD.getValue());
                    serialInfo.setShipFromLocCd(sMsgBLine.invtyLocCd_HD.getValue());
                    /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 END */
                    serialInfo.setTrxHdrNum(sMsgBLine.trxHdrNum_HT.getValue());
                    serialInfo.setSoNum(sMsgBLine.soNum_HD.getValue());
                    serialInfo.setOrdSvcConfigMstrPk(sMsgBLine.pickSvcConfigMstrPk_B1.getValue());
                    serialInfo.setMdlId(sMsgBLine.mdlId_B1.getValue());
                    serialInfo.setOnBatchType(ONBATCH_TYPE.ONLINE);

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

                    // Asset check
                    if (INVTY_ACCT.ASSET.equals(sMsgBLine.invtyAcctCd_B1.getValue())) {

                        if (!chkAsset(sMsg.glblCmpyCd.getValue(), sMsgBLine.serNum_B1.getValue(), sMsgBLine.mdseCd_B1.getValue())) {

                            sMsgBLine.serNum_B1.setErrorInfo(1, NLBL2020Constant.NLBM1290E);
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * saveCurrentPageToSMsgScheduling
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    public static void saveCurrentPageToSMsgSoList(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        cMsg.clearErrorInfo();
        cMsg.A.clearErrorInfo();
        sMsg.clearErrorInfo();
        sMsg.A.clearErrorInfo();

        int fromIdx = cMsg.xxPageShowFromNum_A.getValueInt() - 1;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            // add start 2022/10/18 QC#60559
            String trxHdrNum_A1 = sMsg.A.no(fromIdx + i).trxHdrNum_A1.getValue();
            String dplyLineNum_A1 = sMsg.A.no(fromIdx + i).dplyLineNum_A1.getValue();
            String soNum_A1 = sMsg.A.no(fromIdx + i).soNum_A1.getValue();
            String soSlpNum_A1 = sMsg.A.no(fromIdx + i).soSlpNum_A1.getValue();
            // add end 2022/10/18 QC#60559

            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(fromIdx + i), null);

            // add start 2022/10/18 QC#60559
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(fromIdx + i).trxHdrNum_A1, trxHdrNum_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(fromIdx + i).dplyLineNum_A1, dplyLineNum_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(fromIdx + i).soNum_A1, soNum_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(fromIdx + i).soSlpNum_A1, soSlpNum_A1);
            // add end 2022/10/18 QC#60559
        }
    }

    /**
     * saveCurrentPageToSMsgScheduling
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    public static void saveCurrentPageToSMsgPickList(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        cMsg.clearErrorInfo();
        cMsg.B.clearErrorInfo();
        sMsg.clearErrorInfo();
        sMsg.B.clearErrorInfo();

        int fromIdx = cMsg.xxPageShowFromNum_B.getValueInt() - 1;

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            // add start 2022/10/18 QC#60559
            String trxHdrNum_B1 = sMsg.B.no(fromIdx + i).trxHdrNum_B1.getValue();
            String dplyLineNum_B1 = sMsg.B.no(fromIdx + i).dplyLineNum_B1.getValue();
            String soNum_B1 = sMsg.B.no(fromIdx + i).soNum_B1.getValue();
            String soSlpNum_B1 = sMsg.B.no(fromIdx + i).soSlpNum_B1.getValue();
            // add end 2022/10/18 QC#60559

            EZDMsg.copy(cMsg.B.no(i), null, sMsg.B.no(fromIdx + i), null);

            // add start 2022/10/18 QC#60559
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(fromIdx + i).trxHdrNum_B1, trxHdrNum_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(fromIdx + i).dplyLineNum_B1, dplyLineNum_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(fromIdx + i).soNum_B1, soNum_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(fromIdx + i).soSlpNum_B1, soSlpNum_B1);
            // add end 2022/10/18 QC#60559
        }
    }

    /**
     * fillCheckBoxForSoList
     * @param sMsg NLBL2020SMsg
     * @return NLBL2020SMsg
     */
    public static NLBL2020SMsg fillCheckBoxForSoList(NLBL2020SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (selectedLinesForSoList(sMsg.A.no(i))) {

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxExstFlg_A2, ZYPConstant.FLG_ON_Y);
            }
        }

        return sMsg;
    }

    /**
     * selectedLinesForSoList
     * @param record NLBL2020_ASMsg
     * @return true:Selected Line, false:not selected line
     */
    public static boolean selectedLinesForSoList(NLBL2020_ASMsg record) {

        String headNum = "";
        boolean targetRecords = false;

        // check so status for selected lines
        if (ZYPCommonFunc.hasValue(record.xxExstFlg_A1)) {

            headNum = record.soNum_A1.getValue();
            targetRecords = true;

        } else if (ZYPCommonFunc.hasValue(record.xxExstFlg_A2)) {

            targetRecords = true;

        } else if (!ZYPCommonFunc.hasValue(record.xxExstFlg_A1) && ZYPCommonFunc.hasValue(record.soNum_A1.getValue())) {

            headNum = "";

        } else if (ZYPCommonFunc.hasValue(headNum) && !ZYPCommonFunc.hasValue(record.soNum_A1.getValue())) {

            targetRecords = true;
        }

        return targetRecords;
    }

    /**
     * getRwsDtlArray
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param trxLineNum String
     * @return ArrayList<RWS_DTLTMsg>
     */
    public static ArrayList<RWS_DTLTMsg> getRwsDtlArray(String glblCmpyCd, String rwsNum, String trxLineNum) {

        RWS_DTLTMsg dtlCond = new RWS_DTLTMsg();

        dtlCond.setSQLID("004");
        dtlCond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dtlCond.setConditionValue("rwsNum01", rwsNum);
        dtlCond.setConditionValue("trxLineNum01", trxLineNum);

        RWS_DTLTMsgArray rwsDtlTMsgArray = (RWS_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdate(dtlCond);

        ArrayList<RWS_DTLTMsg> rwsDtlTMsgList = new ArrayList<RWS_DTLTMsg>();

        for (int i = 0; i < rwsDtlTMsgArray.length(); i++) {

            rwsDtlTMsgList.add(rwsDtlTMsgArray.no(i));
        }

        return rwsDtlTMsgList;
    }

    /**
     * viewErrorRecordPage
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    public static void viewErrorRecordPage(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        int index = 0;
        int msgCode = NLBL2020Constant.MESSAGE_CODE_NORMAL;

        if ("E".equals(cMsg.getMessageKind())) {

            msgCode = NLBL2020Constant.MESSAGE_CODE_ERROR;

        } else if ("W".equals(cMsg.getMessageKind())) {

            msgCode = NLBL2020Constant.MESSAGE_CODE_WARNING;
        }

        // SO List Tab
        if (NLBL2020Constant.TAB_SO_LIST.equals(cMsg.xxDplyTab.getValue())) {

            // Search First Error Record Number
            int i = 0;
            int pageRow = cMsg.A.length();

            for (; i < sMsg.A.getValidCount(); i++) {

                if (sMsg.A.no(i).xxExstFlg_A1.getErrorCode() == msgCode //
                        || sMsg.A.no(i).xxExstFlg_A2.getErrorCode() == msgCode //
                        || sMsg.A.no(i).pickConfQty_A1.getErrorCode() == msgCode //
                        || sMsg.A.no(i).shipQty_A1.getErrorCode() == msgCode //
                        || sMsg.A.no(i).serNum_A1.getErrorCode() == msgCode //
                        || sMsg.A.no(i).proNum_A1.getErrorCode() == msgCode //
                        || sMsg.A.no(i).carrNm_A1.getErrorCode() == msgCode // 05/15/2020 T.Ogura [QC#56880,ADD]
                        || sMsg.A.no(i).actlShpgSvcLvlCd_A1.getErrorCode() == msgCode) {

                    break;
                }
            }

            // Calculate target Page.
            index = (i / pageRow) * pageRow;

            // Set View Page (SO List)
            int pagenationFrom = index;
            int j = pagenationFrom;

            for (; j < pagenationFrom + cMsg.A.length(); j++) {

                if (j < sMsg.A.getValidCount()) {

                    EZDMsg.copy(sMsg.A.no(j), null, cMsg.A.no(j - pagenationFrom), null);

                } else {

                    break;
                }
            }

            cMsg.A.setValidCount(j - pagenationFrom);

            // set value to paging items
            cMsg.xxPageShowFromNum_A.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum_A.setValue(pagenationFrom + cMsg.A.getValidCount());

        // Pick List Tab
        } else {

            // Search First Error Record Number
            int i = 0;
            int pageRow = cMsg.B.length();

            for (; i < sMsg.B.getValidCount(); i++) {

                if (sMsg.B.no(i).pickConfQty_B1.getErrorCode() == msgCode || sMsg.B.no(i).serNum_B1.getErrorCode() == msgCode) {

                    break;
                }
            }

            // Calculate target Page.
            index = (i / pageRow) * pageRow;

            // Set View Page (SO List)
            int pagenationFrom = index;
            int j = pagenationFrom;

            for (; j < pagenationFrom + cMsg.B.length(); j++) {

                if (j < sMsg.B.getValidCount()) {

                    EZDMsg.copy(sMsg.B.no(j), null, cMsg.B.no(j - pagenationFrom), null);

                } else {

                    break;
                }
            }

            cMsg.B.setValidCount(j - pagenationFrom);

            // set value to paging items
            cMsg.xxPageShowFromNum_B.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum_B.setValue(pagenationFrom + cMsg.B.getValidCount());
        }
    }

    /**
     * convDplyDtTm
     * @param timeStamp String
     * @return String
     */
    public static String convDplyDtTm(String timeStamp) {

        if (ZYPCommonFunc.hasValue(timeStamp)) {

            return ZYPDateUtil.formatEzd14ToDisp(timeStamp);
        }

        return null;
    }

    /**
     * getPageStartRowIndex
     * @param index  int
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     * @return int
     */
    public static int getPageStartRowIndexForTabSoList(int index, NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        return (index / cMsg.A.length()) * cMsg.A.length();
    }

    /**
     * page nation
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     * @param fromIdx int
     */
    public static void pagenationForTabSoList(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg, int fromIdx) {

        int i = fromIdx;

        for ( ; i < fromIdx + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - fromIdx), null);

            } else {

                break;
            }
        }

        // set value to pagenation items
        cMsg.A.setValidCount(i - fromIdx);
        cMsg.xxPageShowFromNum_A.setValue(fromIdx + 1);
        cMsg.xxPageShowToNum_A.setValue(fromIdx + cMsg.A.getValidCount());
    }

    /**
     * Set Common Values to cMsg and sMsg
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     * @param glblCmpyCd String
     * @param userId String
     */
    public static void setCommonValues(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg, String glblCmpyCd, String userId) {

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(sMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(sMsg.slsDt, cMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.usrId, userId);
        ZYPEZDItemValueSetter.setValue(sMsg.usrId, cMsg.usrId.getValue());
    }

    /**
     * getCarrCdList
     * @param glblCmpyCd String
     * @param carrCd String
     * @param carrNm String
     * @return List<String>
     */
    public static List<String> getCarrCdList(String glblCmpyCd, String carrCd, String carrNm) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("carrNm", carrNm);

        S21SsmEZDResult ssmResultCarr = NLBL2020Query.getInstance().getCarrCdList(ssmParam);

        if (!ssmResultCarr.isCodeNormal()) {

            return null;

        } else {

            List<String> carrCdList = (List<String>) ssmResultCarr.getResultObject();

            if (carrCdList == null || carrCdList.isEmpty()) {

                return null;
            }

            if (carrCdList.size() > 1) {

                ssmParam.put("carrCd", carrCd);

                ssmResultCarr = NLBL2020Query.getInstance().getCarrCdList(ssmParam);

                if (!ssmResultCarr.isCodeNormal()) {

                    return null;
                }
            }
        }

        return (List<String>) ssmResultCarr.getResultObject();
    }

    /**
     * getAcctCarrCnt
     * @param sMsg NLBL2020SMsg
     * @param carrCd String
     * @param dsAcctNum String
     * @param carrAcctNum String
     * @return BigDecimal
     */
    public static BigDecimal getAcctCarrCnt(NLBL2020SMsg sMsg, String carrCd, String dsAcctNum, String carrAcctNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", sMsg.glblCmpyCd.getValue());
        ssmParam.put("slsDt", sMsg.slsDt.getValue());
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("carrCd", carrCd);
        ssmParam.put("carrAcctNum", carrAcctNum);

        S21SsmEZDResult ssmResult = NLBL2020Query.getInstance().getAcctCarrCnt(ssmParam);

        if (!ssmResult.isCodeNormal()) {

            return null;
        }

        return (BigDecimal) ssmResult.getResultObject();
    }

    /**
     * getCarrSvcLvlCdList
     * @param sMsg NLBL2020SMsg
     * @param shpgSvcLvlCd String
     * @param carrCd String
     * @return List<String>
     */
    public static List<String> getCarrSvcLvlCdList(NLBL2020SMsg sMsg, String shpgSvcLvlCd, String carrCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", sMsg.glblCmpyCd.getValue());
        ssmParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
        ssmParam.put("carrCd", carrCd);

        S21SsmEZDResult ssmResult = NLBL2020Query.getInstance().getCarrSvcLvlCdList(ssmParam);

        if (!ssmResult.isCodeNormal()) {

            return null;
        }

        return (List<String>) ssmResult.getResultObject();
    }

    /**
     * get array from search text if it has "," in text field.
     * splitSrchTxt
     * @param srchTxtItem EZDCStringItemring
     * @return ArrayList<String>
     */
    public static ArrayList<String> splitSrchTxt(EZDCStringItem srchTxtItem) {

        ArrayList<String> splitSrchTxtList = new ArrayList<String>();
        boolean isSplit = false;

        if (ZYPCommonFunc.hasValue(srchTxtItem)) {

            if (srchTxtItem.getValue().indexOf(",") != -1) {

                String[] srchTxtArray = srchTxtItem.getValue().split(",");

                for (int i = 0; i < srchTxtArray.length; i++) {

                    if (!srchTxtArray[i].trim().equals("") && srchTxtArray[i].length() > 0) {

                        String chkTxt = srchTxtArray[i].trim();

                        if (ZYPCommonFunc.hasValue(chkTxt) && chkTxt.length() > 0) {

                            splitSrchTxtList.add(srchTxtArray[i].trim());
                        }

                        isSplit = true;
                    }
                }
            }

            if (!isSplit) {

                String chkTxt = srchTxtItem.getValue().trim();

                if (ZYPCommonFunc.hasValue(chkTxt) && chkTxt.length() > 0) {

                    splitSrchTxtList.add(srchTxtItem.getValue().trim());
                }
            }
        }

        if (splitSrchTxtList != null && !splitSrchTxtList.isEmpty()) {

            return splitSrchTxtList;
        }

        return null;
    }

    /**
     * addOrdTakeMdse
     * @param glblCmpyCd  String
     * @param srchMdseCdList ArrayList<String>
     * @return ArrayList<String>
     */
    private static ArrayList<String> addOrdTakeMdse(String glblCmpyCd, ArrayList<String> srchMdseCdList) {

        if (srchMdseCdList == null || srchMdseCdList.isEmpty()) {

            return null;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("itemList", srchMdseCdList);

        S21SsmEZDResult ordTakeMdseResult = NLBL2020Query.getInstance().getOrdTakeMdseList(params);

        if (ordTakeMdseResult.isCodeNormal() && !ordTakeMdseResult.isCodeNotFound()) {

            List<ORD_TAKE_MDSETMsg> ordTakeMdseList = (List<ORD_TAKE_MDSETMsg>) ordTakeMdseResult.getResultObject();

            if (ordTakeMdseList != null && !ordTakeMdseList.isEmpty()) {

                for (ORD_TAKE_MDSETMsg ordTakeMdse : ordTakeMdseList) {

                    srchMdseCdList.add(ordTakeMdse.ordTakeMdseCd.getValue().concat("%"));
                }
            }
        }

        return srchMdseCdList;
    }

    /**
     * chkPiActivity
     * @param glblCmpyCd  String
     * @param soNum String
     * @return boolean true : OK, false : NG
     */
    public static boolean chkPiActivity(String glblCmpyCd, String soNum) {

        NLZC060001PMsg piActivityStsApiPMsg = new NLZC060001PMsg();
        ZYPEZDItemValueSetter.setValue(piActivityStsApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(piActivityStsApiPMsg.soNum, soNum);

        NLZC060001 piActivityStsApi = new NLZC060001();
        piActivityStsApi.execute(piActivityStsApiPMsg, ONBATCH_TYPE.ONLINE);

        if (NLZC060001Constant.RETURN_CODE_ERROR.equals(piActivityStsApiPMsg.xxRsltStsCd.getValue())) {

            return false;
        }

        return true;
    }

    /**
     * isSameVal
     * @param val2 String
     * @param val2 String
     * @return boolean true : Same, false : different
     */
    public static boolean isSameVal(String val1, String val2) {

        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return true;

        } else if (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (!val1.equals(val2)) {

            return false;
        }

        return true;
    }

    /**
     * isSameVal
     * @param val2 BigDecimal
     * @param val2 BigDecimal
     * @return boolean true : Same, false : different
     */
    public static boolean isSameVal(BigDecimal val1, BigDecimal val2) {

        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return true;

        } else if (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (val1.compareTo(val2) == 0) {

            return true;
        }

        return false;
    }

    /**
     * chkApiResult
     * @param cMsg NLBL2020CMsg
     * @param apiMsgList List<String>
     * @return boolean true : OK, false : NG
     */
    public static boolean chkApiResult(NLBL2020CMsg cMsg, List<String> apiMsgList) {

        if (!apiMsgList.isEmpty()) {

            for (String msgId : apiMsgList) {

                if (ZYPCommonFunc.hasValue(msgId)) {

                    cMsg.setMessageInfo(msgId);

                    if (msgId.endsWith("E")) {

                        return false;
                    }
                }
            }
        }

        return true;
    }
//
//    /**
//     * callMachMstrApiForConfigChange
//     * @param bizMsg NLAL2020CMsg
//     * @param soNum String
//     * @return List<NSZC001001PMsg>
//     */
//    public static boolean callMachMstrApiForConfigChange(NLBL2020CMsg bizMsg, List<NLBL2020_ASMsg> asMsglist) {
//
//        List<BigDecimal> allocOnSvcMachMstrPkList = new ArrayList<BigDecimal>();
//        List<NLZC309001PMsg> assetStgnApiPMsgList = new ArrayList<NLZC309001PMsg>();
//        NSZC001001PMsg machMstrPMsg = new NSZC001001PMsg();
//
//        int cmptIndex = 0;
//
//        for (NLBL2020_ASMsg asMsg : asMsglist) {
//            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//            params.put("soNum", asMsg.soNum_HI.getValue());
//            params.put("soSlpNum", asMsg.soSlpNum_HI.getValue());
//            params.put("serNum", asMsg.serNum_A1.getValue());
//            params.put("svcMachMstrStsCdList", new String[]{SVC_MACH_MSTR_STS.CREATED, SVC_MACH_MSTR_STS.REMOVED});
//            params.put("svcMachMaintAvalFlg", ZYPConstant.FLG_OFF_N);
//            params.put("trxHdrNum", asMsg.trxHdrNum_A1.getValue());
//            params.put("trxLineNum", asMsg.trxLineNum_A1.getValue());
//            params.put("trxLineSubNum", asMsg.trxLineSubNum_A1.getValue());
//            S21SsmEZDResult ssmResult = NLBL2020Query.getInstance().getConfigCmptList(params);
//
//            // Configuration Component Information is not found.
//            if (ssmResult.isCodeNotFound()) {
//                bizMsg.setMessageInfo("NSZM0006E");
//                return false;
//            }
//
//            List<Map<String, Object>> svcMachMstrList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();
//
//            // Configuration Component Information is not found.
//            if (svcMachMstrList == null || svcMachMstrList.isEmpty()) {
//                bizMsg.setMessageInfo("NSZM0006E");
//                return  false;
//            }
//
//            BigDecimal svcMachMstrPk = (BigDecimal) svcMachMstrList.get(0).get("SVC_MACH_MSTR_PK");
//
//            if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
//                if (ZYPCommonFunc.hasValue(asMsg.serNum_A1)) {
//                    bizMsg.setMessageInfo("NSZM0006E");
//                    return false;
//                } else {
//                    params.put("svcMachMaintAvalFlg", ZYPConstant.FLG_ON_Y);
//                    ssmResult = NLBL2020Query.getInstance().getConfigCmptList(params);
//
//                    // Configuration Component Information is not found.
//                    if (ssmResult.isCodeNotFound()) {
//                        bizMsg.setMessageInfo("NSZM0006E");
//                        return false;
//                    }
//                    svcMachMstrList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();
//                    svcMachMstrPk = (BigDecimal) svcMachMstrList.get(0).get("SVC_MACH_MSTR_PK");
//                    if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
//                        NSZC001001PMsg pMsg = new NSZC001001PMsg();
//                        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
//                        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
//                        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, asMsg.mdseCd_A1);
//                        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
//                        ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, bizMsg.slsDt);
//                        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
//                        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
//                        ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, asMsg.toInvtyLocCd_A1);
//                        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
//                        ZYPEZDItemValueSetter.setValue(pMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
//                        ZYPEZDItemValueSetter.setValue(pMsg.svcMachQty, BigDecimal.ONE);
//                        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, asMsg.fromStkStsCd_A1);
//
//                        // Call
//                        NSZC001001 api = new NSZC001001();
//                        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
//
//                        if (!chkApiResult(bizMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {
//                            return false;
//                        }
//                        ssmResult = NLBL2020Query.getInstance().getConfigCmptList(params);
//                        if (ssmResult.isCodeNotFound()) {
//                            bizMsg.setMessageInfo("NSZM0006E");
//                            return false;
//                        }
//                        svcMachMstrList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();
//                    }
//                }
//            }
//
//            svcMachMstrPk = (BigDecimal) svcMachMstrList.get(0).get("SVC_MACH_MSTR_PK");
//            BigDecimal svcConfigMstrPk = (BigDecimal) svcMachMstrList.get(0).get("SVC_CONFIG_MSTR_PK");
//            String mdseCd = (String) svcMachMstrList.get(0).get("MDSE_CD");
//            String serNum = (String) svcMachMstrList.get(0).get("SER_NUM");
//            String svcMachMstrStsCd = (String) svcMachMstrList.get(0).get("SVC_MACH_MSTR_STS_CD");
//            String effFromDt = (String) svcMachMstrList.get(0).get("EFF_FROM_DT");
//            String svcMachUsgStsCd = (String) svcMachMstrList.get(0).get("SVC_MACH_USG_STS_CD");
//            String prntSerNum = (String) svcMachMstrList.get(0).get("PRNT_SER_NUM");
//            String svcMachTrxTpCd = (String) svcMachMstrList.get(0).get("SVC_MACH_TRX_TP_CD");
//            BigDecimal svcMachQty = (BigDecimal) svcMachMstrList.get(0).get("SVC_MACH_QTY");
//            String curLocNum = (String) svcMachMstrList.get(0).get("CUR_LOC_NUM");
//            String svcMachMstrLocStsCd = (String) svcMachMstrList.get(0).get("SVC_MACH_MSTR_LOC_STS_CD");
//            String rmvConfigFlg = (String ) svcMachMstrList.get(0).get("RMV_CONFIG_FLG");
//            String svcMachMaintAvalFlg = (String) svcMachMstrList.get(0).get("SVC_MACH_MAINT_AVAL_FLG");
//            String invtyAcctCd = (String) svcMachMstrList.get(0).get("INVTY_ACCT_CD");
//            BigDecimal pickSvcConfigMstrPk = (BigDecimal) svcMachMstrList.get(0).get("PICK_SVC_CONFIG_MSTR_PK");
//
//            if (cmptIndex == 0) {
//                // Main Machine
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.slsDt, ZYPDateUtil.getSalesDate());
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxModeCd, ProcessMode.UPDATE_WAREHOUSE.code);
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcConfigMstrPk, svcConfigMstrPk);
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrPk, svcMachMstrPk);
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.mdseCd, mdseCd);
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.serNum, serNum);
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachTpCd, SVC_MACH_TP.MACHINE);
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, svcMachMstrStsCd);
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.effFromDt, effFromDt);
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, svcMachUsgStsCd);
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.prntSerNum, prntSerNum);
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachTrxTpCd, svcMachTrxTpCd);
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachQty, svcMachQty);
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.curLocNum, curLocNum);
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrLocStsCd, svcMachMstrLocStsCd);
//            }
//
//            // Component
//            ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxCmptMachList.no(cmptIndex).svcMachMstrPk, svcMachMstrPk);
//            ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxCmptMachList.no(cmptIndex).mdseCd, mdseCd);
//            if (cmptIndex == 0) {
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.MACHINE);
//            } else {
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
//            }
//            if (ZYPConstant.FLG_ON_Y.equals(rmvConfigFlg)) {
//                ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxCmptMachList.no(cmptIndex).effThruDt, ZYPDateUtil.getSalesDate());
//            }
//            machMstrPMsg.xxCmptMachList.setValidCount(cmptIndex + 1);
//
//            if (ZYPConstant.FLG_OFF_N.equals(svcMachMaintAvalFlg)) {
//                allocOnSvcMachMstrPkList.add(svcMachMstrPk);
//            } else if (!ZYPCommonFunc.hasValue(serNum)) {
//                NSZC001001PMsg allocOnPMsg = new NSZC001001PMsg();
//                ZYPEZDItemValueSetter.setValue(allocOnPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
//                ZYPEZDItemValueSetter.setValue(allocOnPMsg.slsDt, bizMsg.slsDt);
//                ZYPEZDItemValueSetter.setValue(allocOnPMsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
//                ZYPEZDItemValueSetter.setValue(allocOnPMsg.svcMachMstrPk, svcMachMstrPk);
//                ZYPEZDItemValueSetter.setValue(allocOnPMsg.trxHdrNum, asMsg.trxHdrNum_A1);
//                ZYPEZDItemValueSetter.setValue(allocOnPMsg.trxLineNum, asMsg.trxLineNum_A1);
//                ZYPEZDItemValueSetter.setValue(allocOnPMsg.trxLineSubNum, asMsg.trxLineSubNum_A1);
//
//                // Call
//                NSZC001001 api = new NSZC001001();
//                api.execute(allocOnPMsg, ONBATCH_TYPE.ONLINE);
//                if (!chkApiResult(bizMsg, S21ApiUtil.getXxMsgIdList(allocOnPMsg))) {
//                    return false;
//                }
//                allocOnSvcMachMstrPkList.add(svcMachMstrPk);
//            }
//
//            if (INVTY_ACCT.ASSET.equals(invtyAcctCd)) {
//                BigDecimal fromSvcConfigMstrPk = null;
//                BigDecimal toSvcConfigMstrPk = null;
//                //Configuration Master PK From
//                if (ZYPCommonFunc.hasValue(pickSvcConfigMstrPk)) {
//                    fromSvcConfigMstrPk = pickSvcConfigMstrPk;
//                }
//                //Configuration Master PK To
//                if (ZYPConstant.FLG_ON_Y.equals(rmvConfigFlg)) {
//                    toSvcConfigMstrPk = null;
//                } else {
//                    toSvcConfigMstrPk = svcConfigMstrPk;
//                }
//                NLZC309001PMsg assetStgnApiPMsg = new NLZC309001PMsg();
//                ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.slsDt,  ZYPDateUtil.getSalesDate());
//                ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.procModeCd, NLZC309001Constant.PROC_MODE_CONFIGRATION_CHANGE);
//                ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.mdseCd, mdseCd);
//                ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.serNum, serNum);
//                ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.svcMachMstrPk, svcMachMstrPk);
//                ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.fromSvcConfigMstrPk, fromSvcConfigMstrPk);
//                ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.toSvcConfigMstrPk, toSvcConfigMstrPk);
//                assetStgnApiPMsgList.add(assetStgnApiPMsg);
//            }
//            cmptIndex++;
//        }
//
//        // Call
//        NSZC001001 api = new NSZC001001();
//        api.execute(machMstrPMsg, ONBATCH_TYPE.ONLINE);
//
//        if (!NLBL2020CommonLogic.chkApiResult(bizMsg, S21ApiUtil.getXxMsgIdList(machMstrPMsg))) {
//            return false;
//        }
//
//        //Allocation Off
//        for (BigDecimal svcMachMstrPk : allocOnSvcMachMstrPkList) {
//            NSZC001001PMsg pMsg = new NSZC001001PMsg();
//            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
//            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
//
//            // Call
//            api = new NSZC001001();
//            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
//
//            if (!chkApiResult(bizMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {
//                return false;
//            }
//        }
//
//
//        if (!assetStgnApiPMsgList.isEmpty()) {
//
//            NLZC309001 assetStgnApi = new NLZC309001();
//            assetStgnApi.execute(assetStgnApiPMsgList, ONBATCH_TYPE.ONLINE);
//
//            for (NLZC309001PMsg assetStgnApiPMsg : assetStgnApiPMsgList) {
//                if (!S21ApiUtil.getXxMsgIdList(assetStgnApiPMsg).isEmpty()) {
//                    for (String apiErrMsg : S21ApiUtil.getXxMsgIdList(assetStgnApiPMsg)) {
//                        if (apiErrMsg.endsWith("E")) {
//                            bizMsg.setMessageInfo(apiErrMsg);
//                            return false;
//                        }
//                    }
//                }
//            }
//        }
//        return true;
//    }

    /**
     * ctrlShipConfButton
     * @param glblMsg NLBL2020SMsg
     * @return true:Ship Ok / false:Not ship
     */
    public static boolean ctrlShipConfButton(NLBL2020SMsg glblMsg) {
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).xxExstFlg_A2.getValue())) {
                if (glblMsg.A.no(i).pickConfQty_A1.getValueInt() > 0) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * isPickQtyChk
     * @param sMsgALine NLBL2020_ASMsg
     * @return boolean true:OK false:NG
     */
    private static boolean isPickQtyChk(NLBL2020_ASMsg sMsgALine) {

        if (!ZYPCommonFunc.hasValue(sMsgALine.pickConfQty_A1)) {

            sMsgALine.pickConfQty_A1.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Pick Quantity" });
            return false;

        } else if (BigDecimal.ZERO.equals(sMsgALine.pickConfQty_A1.getValue())) {

            sMsgALine.pickConfQty_A1.setErrorInfo(1, NLBL2020Constant.NLBM1018E, //
                    new String[] {"this record", "Because pick quantity is zero", "please pick confirmation." });
            return false;

        }

        return true;
    }

    /**
     * chkSoHdrCheck
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    public static boolean chkSoHdrCheck(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        boolean chkBoxOn = false;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxExstFlg_A1)) {

                chkBoxOn = true;
                break;
            }
        }

        if (!chkBoxOn) {

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                sMsg.A.no(i).xxExstFlg_A1.setErrorInfo(1, NLBL2020Constant.NLBM0002E);
            }
        }
        
        return chkBoxOn;
    }

    /**
     * chkSoLineCheck
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     */
    public static boolean chkSoLineCheck(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {

        boolean chkBoxOn = false;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxExstFlg_A2)) {

                chkBoxOn = true;
                break;
            }
        }

        if (!chkBoxOn) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                sMsg.A.no(i).xxExstFlg_A2.setErrorInfo(1, NLBL2020Constant.NLBM0002E);
            }
        }
        
        return chkBoxOn;
    }

    /**
     * copyHeaderCMsgToSMsg
     * @param cMsg
     * @param sMsg
     */
    public static void copyHeaderCMsgToSMsg(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg){
        ZYPEZDItemValueSetter.setValue(sMsg.trxHdrNum_H1, cMsg.trxHdrNum_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.soNum_H1, cMsg.soNum_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.dsSoLineStsCd_PS, cMsg.dsSoLineStsCd_PS);
        ZYPEZDItemValueSetter.setValue(sMsg.sceOrdTpCd_PS, cMsg.sceOrdTpCd_PS);
        ZYPEZDItemValueSetter.setValue(sMsg.shpgSvcLvlCd_PS, cMsg.shpgSvcLvlCd_PS);
        ZYPEZDItemValueSetter.setValue(sMsg.svcConfigMstrPk_H1, cMsg.svcConfigMstrPk_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.xxRtrnInvtyLocSrchTxt_RW, cMsg.xxRtrnInvtyLocSrchTxt_RW);
        ZYPEZDItemValueSetter.setValue(sMsg.xxRtrnInvtyLocSrchTxt_SW, cMsg.xxRtrnInvtyLocSrchTxt_SW);
        ZYPEZDItemValueSetter.setValue(sMsg.shipToCustCd_H1, cMsg.shipToCustCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.carrCd_H1, cMsg.carrCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.proNum_H1, cMsg.proNum_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.mdseCd_H1, cMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.xxTrxDt_FR, cMsg.xxTrxDt_FR);
        ZYPEZDItemValueSetter.setValue(sMsg.xxDtNm_F1, cMsg.xxDtNm_F1);
        ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDtTxt_F1, cMsg.rqstRcvDtTxt_F1);
        ZYPEZDItemValueSetter.setValue(sMsg.xxTrxDt_TO, cMsg.xxTrxDt_TO);
        ZYPEZDItemValueSetter.setValue(sMsg.xxDtNm_T1, cMsg.xxDtNm_T1);
        ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDtTxt_T1, cMsg.rqstRcvDtTxt_T1);
        ZYPEZDItemValueSetter.setValue(sMsg.rddDt_FR, cMsg.rddDt_FR);
        ZYPEZDItemValueSetter.setValue(sMsg.xxDtNm_F2, cMsg.xxDtNm_F2);
        ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDtTxt_F2, cMsg.rqstRcvDtTxt_F2);
        ZYPEZDItemValueSetter.setValue(sMsg.rddDt_TO, cMsg.rddDt_TO);
        ZYPEZDItemValueSetter.setValue(sMsg.xxDtNm_T2, cMsg.xxDtNm_T2);
        ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDtTxt_T2, cMsg.rqstRcvDtTxt_T2);
        ZYPEZDItemValueSetter.setValue(sMsg.xxTrxDt_F3, cMsg.xxTrxDt_F3);
        ZYPEZDItemValueSetter.setValue(sMsg.xxDtNm_F3, cMsg.xxDtNm_F3);
        ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDtTxt_F3, cMsg.rqstRcvDtTxt_F3);
        ZYPEZDItemValueSetter.setValue(sMsg.xxTrxDt_T3, cMsg.xxTrxDt_T3);
        ZYPEZDItemValueSetter.setValue(sMsg.xxDtNm_T3, cMsg.xxDtNm_T3);
        ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDtTxt_T3, cMsg.rqstRcvDtTxt_T3);
        ZYPEZDItemValueSetter.setValue(sMsg.wmsDropFlg_Y, cMsg.wmsDropFlg_Y);
        ZYPEZDItemValueSetter.setValue(sMsg.wmsDropFlg_N, cMsg.wmsDropFlg_N);
        ZYPEZDItemValueSetter.setValue(sMsg.xxExstFlg_BO, cMsg.xxExstFlg_BO);
        ZYPEZDItemValueSetter.setValue(sMsg.xxExstFlg_NO, cMsg.xxExstFlg_NO);
    }

    /**
     * copyHeaderCMsgToSMsg
     * @param cMsg
     * @param sMsg
     */
    public static void copyHeaderSMsgToCMsg(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg){
        ZYPEZDItemValueSetter.setValue(cMsg.trxHdrNum_H1, sMsg.trxHdrNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.soNum_H1, sMsg.soNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.dsSoLineStsCd_PS, sMsg.dsSoLineStsCd_PS);
        ZYPEZDItemValueSetter.setValue(cMsg.sceOrdTpCd_PS, sMsg.sceOrdTpCd_PS);
        ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_PS, sMsg.shpgSvcLvlCd_PS);
        ZYPEZDItemValueSetter.setValue(cMsg.svcConfigMstrPk_H1, sMsg.svcConfigMstrPk_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRtrnInvtyLocSrchTxt_RW, sMsg.xxRtrnInvtyLocSrchTxt_RW);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRtrnInvtyLocSrchTxt_SW, sMsg.xxRtrnInvtyLocSrchTxt_SW);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_H1, sMsg.shipToCustCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.carrCd_H1, sMsg.carrCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.proNum_H1, sMsg.proNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_H1, sMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTrxDt_FR, sMsg.xxTrxDt_FR);
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtNm_F1, sMsg.xxDtNm_F1);
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDtTxt_F1, sMsg.rqstRcvDtTxt_F1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTrxDt_TO, sMsg.xxTrxDt_TO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtNm_T1, sMsg.xxDtNm_T1);
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDtTxt_T1, sMsg.rqstRcvDtTxt_T1);
        ZYPEZDItemValueSetter.setValue(cMsg.rddDt_FR, sMsg.rddDt_FR);
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtNm_F2, sMsg.xxDtNm_F2);
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDtTxt_F2, sMsg.rqstRcvDtTxt_F2);
        ZYPEZDItemValueSetter.setValue(cMsg.rddDt_TO, sMsg.rddDt_TO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtNm_T2, sMsg.xxDtNm_T2);
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDtTxt_T2, sMsg.rqstRcvDtTxt_T2);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTrxDt_F3, sMsg.xxTrxDt_F3);
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtNm_F3, sMsg.xxDtNm_F3);
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDtTxt_F3, sMsg.rqstRcvDtTxt_F3);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTrxDt_T3, sMsg.xxTrxDt_T3);
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtNm_T3, sMsg.xxDtNm_T3);
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDtTxt_T3, sMsg.rqstRcvDtTxt_T3);
        ZYPEZDItemValueSetter.setValue(cMsg.wmsDropFlg_Y, sMsg.wmsDropFlg_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.wmsDropFlg_N, sMsg.wmsDropFlg_N);
        ZYPEZDItemValueSetter.setValue(cMsg.xxExstFlg_BO, sMsg.xxExstFlg_BO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxExstFlg_NO, sMsg.xxExstFlg_NO);
    }

    /**QC#17922 Add.
     * warnLineCancel
     * @param sMsgALine
     * @param sMsg
     * @return
     */
    public static boolean warnLineCancel(NLBL2020_ASMsg sMsgALine, NLBL2020SMsg sMsg) {

        boolean warnFlg = false;
        if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxWrnSkipFlg_A1.getValue())) {
            String soCancelWhOwnrList = ZYPCodeDataUtil.getVarCharConstValue("NLBL2020_SO_CANCEL_WH_OWNR", sMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(sMsgALine.whOwnrCd_A1) && ZYPCommonFunc.hasValue(soCancelWhOwnrList)) {

                String[] itemArray = soCancelWhOwnrList.split(",");

                if (Arrays.asList(itemArray).contains(sMsgALine.whOwnrCd_A1.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsgALine.wmsDropFlg_A1.getValue())) {

                    // The selected SO has already been sent to WMS. Manual cancel is needed from within WMS. Press button again to continue.
                    sMsgALine.xxExstFlg_A2.setErrorInfo(2, NLBL2020Constant.NLAM1342W);
                    ZYPEZDItemValueSetter.setValue(sMsgALine.xxWrnSkipFlg_A1, ZYPConstant.FLG_ON_Y);
                    warnFlg = true;

                } else if (Arrays.asList(itemArray).contains(sMsgALine.whOwnrCd_A1.getValue()) && !ZYPConstant.FLG_ON_Y.equals(sMsgALine.wmsDropFlg_A1.getValue())) {
                    warnFlg = true;
                }
            }
        }

        return warnFlg;
    }
    
    /**
     * checkCustCarrSvcLvlRelation
     * QC#23726 Add method.
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param shpgSvcLvlCd String
     * @param carrCd String
     * @param dsAcctNum String
     * @return boolean if error then return true.
     */
    public static boolean checkCustCarrSvcLvlRelation(String glblCmpyCd, String cpoOrdNum, String shpgSvcLvlCd, String carrCd, String dsAcctNum) {
        
        // Get CPO
        CPOTMsg cpo = getCPOTmsg(glblCmpyCd, cpoOrdNum);
        
        if(cpo == null){
            // Not target data.
            return false;
        }
        
        // Get Carrier Account Service Level Code
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
        ssmParam.put("carrCd", carrCd);
        S21SsmEZDResult result = NLBL2020Query.getInstance().getCarrSvcLvlCdList(ssmParam);
        
        String carrAcctSvcLvlCd = null;
        if (result.isCodeNormal()) {
            List<String> caslList = (List<String>) result.getResultObject();
            // Search Primary key.
            carrAcctSvcLvlCd = caslList.get(0);
        }

        // Check Customer Carrier Service Level Relation
        return NWXC150001DsCheck.checkCustCarrSvcLvlRelation(glblCmpyCd//
                , cpo.dsOrdCatgCd.getValue()//
                , cpo.dsOrdTpCd.getValue()//
                , cpo.dsOrdRsnCd.getValue()//
                , dsAcctNum//
                , carrAcctSvcLvlCd//
                , null);
    }
    
    /**
     * getCPOTmsg
     * QC#23726 Add Method.
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return CPOTMsg
     */
    public static CPOTMsg getCPOTmsg(String glblCmpyCd, String cpoOrdNum) {

        CPOTMsg param = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.cpoOrdNum, cpoOrdNum);

        return (CPOTMsg) EZDTBLAccessor.findByKey(param);
    }

    // add start 2022/10/18 QC#60559
    /**
     * getShpgSvcLvlDescTxt
     * @param glblCmpyCd String
     * @param shpgSvcLvlCd String
     * @return String
     */
    public static String getShpgSvcLvlDescTxt(String glblCmpyCd, String shpgSvcLvlCd) {
        if (!ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {
            return null;
        }
        SHPG_SVC_LVLTMsg tmsg = new SHPG_SVC_LVLTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.shpgSvcLvlCd.setValue(shpgSvcLvlCd);
        tmsg = (SHPG_SVC_LVLTMsg) S21FastTBLAccessor.findByKey(tmsg);
        if (tmsg == null) {
            return null;
        }
        return tmsg.shpgSvcLvlDescTxt.getValue();
    }
    // add end 2022/10/18 QC#60559

    // add start 2022/12/07 QC#60810
    /**
     * isWMSWarehouseCheck
     * @param glblCmpyCd String
     * @param sMsgALine NLBL2020_ASMsg
     * @return boolean true:Exist false:Not Exist
     */
    public static boolean isWMSWarehouseCheck(String glblCmpyCd, NLBL2020_ASMsg sMsgALine) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rtlWhCd", sMsgALine.shipFromRtlWhCd_AH.getValue());

        // Execute
        S21SsmEZDResult result = NLBL2020Query.getInstance().getCountWMSWarehouseList(param);

        BigDecimal count = BigDecimal.ZERO;
        if (result.isCodeNormal()) {
            count = (BigDecimal) result.getResultObject();
        }

        return BigDecimal.ZERO.compareTo(count) != 0;
    }
    // add end 2022/12/07 QC#60810
}
