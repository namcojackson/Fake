/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3070.NLBL3070CMsg;
import business.servlet.NLBL3070.common.NLBL3070CommonLogic;
import business.servlet.NLBL3070.constant.NLBL3070Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 * 02/26/2016   CSAI            Y.Imazu         Update          QC#2046, 2201
 * 08/31/2016   CSAI            Y.Imazu         Update          QC#9845
 * 06/14/2017   CITS            R.Shimamoto     Update          QC#18272
 * 09/07/2017   CITS            S.Katsuma       Update          Sol#032(QC#13117)
 *</pre>
 */
public class NLBL3070_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NLBL3070Constant.BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        NLBL3070CMsg bizMsg = new NLBL3070CMsg();

        // Set global parameter
        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(scrnMsg.slsDt, ZYPDateUtil.getSalesDate());

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            int paramCnt = params.length;

            if (paramCnt > 0) {
                EZDBStringItem param00 = (EZDBStringItem) params[0];
                if (ZYPCommonFunc.hasValue(param00)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.trxHdrNum_BK, param00);
                }
            }

            if (paramCnt > 1) {
                EZDBStringItem param01 = (EZDBStringItem) params[1];
                if (ZYPCommonFunc.hasValue(param01)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.t_MdlNm_BK, param01);
                }
            }

            if (paramCnt > 2) {
                EZDBBigDecimalItem param02 = (EZDBBigDecimalItem) params[2];
                if (ZYPCommonFunc.hasValue(param02)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_BK, param02);
                }
            }

            if (paramCnt > 3) {
                EZDBStringItem param03 = (EZDBStringItem) params[3];
                if (ZYPCommonFunc.hasValue(param03)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.soNum_BK, param03);
                }
            }

            if (paramCnt > 4) {
                EZDBStringItem param04 = (EZDBStringItem) params[4];
                if (ZYPCommonFunc.hasValue(param04)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.bolNum_BK, param04);
                }
            }

            if (paramCnt > 5) {
                EZDBStringItem param05 = (EZDBStringItem) params[5];
                if (ZYPCommonFunc.hasValue(param05)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_BK, param05);
                }
            }

            if (paramCnt > 6) {
                EZDBStringItem param06 = (EZDBStringItem) params[6];
                if (ZYPCommonFunc.hasValue(param06)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_BK, param06);
                }
            }

            if (paramCnt > 7) {
                EZDBStringItem param07 = (EZDBStringItem) params[7];
                if (ZYPCommonFunc.hasValue(param07)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_BK, param07);
                }
            }

            if (paramCnt > 8) {
                EZDBStringItem param08 = (EZDBStringItem) params[8];
                if (ZYPCommonFunc.hasValue(param08)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.schdCoordPsnCd_BK, param08);
                }
            }

            if (paramCnt > 9) {
                EZDBStringItem param09 = (EZDBStringItem) params[9];
                if (ZYPCommonFunc.hasValue(param09)) {

                    if ("S".equals(param09.getValue())) {

                        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_BK, NLBL3070Constant.TAB_ID_SCHD);

                    } else if ("D".equals(param09.getValue())) {

                        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_BK, NLBL3070Constant.TAB_ID_DELV);

                    } else {

                        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_BK, NLBL3070Constant.TAB_ID_SCHD);
                    }

                } else {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_BK, NLBL3070Constant.TAB_ID_SCHD);
                }
            }
        }

        bizMsg.setBusinessID(NLBL3070Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        NLBL3070CMsg bizMsg = (NLBL3070CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3070CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.trxHdrNum);

        scrnMsg.totFrtAmt_BP.setAppFracDigit(2);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;

        // Search Option
        scrnMsg.srchOptPk_S.setNameForMessage("Search Option");
        scrnMsg.srchOptNm_S.setNameForMessage("Search Option Name");

        // Search Condition
        scrnMsg.trxHdrNum.setNameForMessage("Source Order");
        scrnMsg.dsOrdCatgCd.setNameForMessage("Order Category");
        scrnMsg.dsOrdTpCd.setNameForMessage("Order Reason");
        scrnMsg.t_MdlNm.setNameForMessage("Model");
        scrnMsg.svcConfigMstrPk.setNameForMessage("Config ID");
        scrnMsg.soNum.setNameForMessage("Shipping Order");
        scrnMsg.dsSoLineStsCd.setNameForMessage("Shipping Order Status");
        scrnMsg.shpgSvcLvlCd.setNameForMessage("Service Level");
        scrnMsg.schdCoordStsCd.setNameForMessage("Scheduling Status");
        scrnMsg.bolNum.setNameForMessage("Tracking Number");
        scrnMsg.rtlWhCd.setNameForMessage("Source WH");
        scrnMsg.rtlSwhCd.setNameForMessage("Source SWH");
        scrnMsg.shipToCustCd.setNameForMessage("Ship-to Customer");
        scrnMsg.carrCd.setNameForMessage("Carrier");
        scrnMsg.schdCoordPsnCd.setNameForMessage("Call Coordinator");
        scrnMsg.rddDt_FR.setNameForMessage("Request Delivery Date (From)");
        scrnMsg.rddDt_TO.setNameForMessage("Request Delivery Date (Through)");
        scrnMsg.schdCarrPickUpDt_FR.setNameForMessage("Scheduled Carrier Pickup Date (From)");
        scrnMsg.schdCarrPickUpDt_TO.setNameForMessage("Scheduled Carrier Pickup Date (Through)");
        scrnMsg.schdDelyDt_FR.setNameForMessage("Scheduled Delivery Date (From)");
        scrnMsg.schdDelyDt_TO.setNameForMessage("Scheduled Delivery Date (Through)");
        scrnMsg.actlDelyDt_FR.setNameForMessage("Actual Delivery Date (From)");
        scrnMsg.actlDelyDt_TO.setNameForMessage("Actual Delivery Date (Through)");
        scrnMsg.wmsDropFlg_Y.setNameForMessage("WMS Dropped (ON)");
        scrnMsg.wmsDropFlg_N.setNameForMessage("WMS Dropped (OFF)");
        scrnMsg.delyReqFlg_Y.setNameForMessage("Carrier Request (ON)");
        scrnMsg.delyReqFlg_N.setNameForMessage("Carrier Request (OFF)");

        scrnMsg.xxPageShowCurNum_A.setNameForMessage("Current Page Number");
        scrnMsg.xxPageShowCurNum_B.setNameForMessage("Current Page Number");

        for (int i = 0; i < scrnMsg.A.length(); i++) {

            scrnMsg.A.no(i).schdCarrPickUpDt_A1.setNameForMessage("Scheduled Carrier Pickup Date");
            scrnMsg.A.no(i).schdDelyDt_A1.setNameForMessage("Scheduled Delivery Date");
            
            // START 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
            scrnMsg.A.no(i).techMeetTruckFlg_A1.setNameForMessage("Tech Meet The Truck");
           //    END 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
            
            // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
            scrnMsg.A.no(i).nextActDt_A1.setNameForMessage("Next Action Date");
            // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
            // QC#18272 Add.
            scrnMsg.A.no(i).schdDelyTsDplyTxt_A2.setNameForMessage("Scheduled Delivery Time");
            scrnMsg.A.no(i).rqstRcvDtTxt_S2.setNameForMessage("Scheduled Delivery Time AM/PM");

            scrnMsg.A.no(i).carrNm_A1.setNameForMessage("Carrier Assigned");
            scrnMsg.A.no(i).schdCoordStsCd_A1.setNameForMessage("Scheduling Status");
            scrnMsg.A.no(i).shpgSvcLvlCd_A1.setNameForMessage("Service Level");
            scrnMsg.A.no(i).tempSchdRsnCd_A1.setNameForMessage("Scheduling Notes");
            scrnMsg.A.no(i).tempSchdCmntTxt_A1.setNameForMessage("Scheduling Notes Comment");
            scrnMsg.A.no(i).schdDurnTmNum_A1.setNameForMessage("Call Duration");
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {

            scrnMsg.B.no(i).xxShipQty_B1.setNameForMessage("Quantity");
            scrnMsg.B.no(i).serNum_B1.setNameForMessage("Serial Number");
            scrnMsg.B.no(i).carrRsnCd_B1.setNameForMessage("Carrier Reason");
            scrnMsg.B.no(i).actlDelyDt_B1.setNameForMessage("Actual Delivery Date");
            // QC#18272 Mod.
            scrnMsg.B.no(i).schdDelyTsDplyTxt_B2.setNameForMessage("Actual Delivery Time");
            scrnMsg.B.no(i).rqstRcvDtTxt_B2.setNameForMessage("Actual Delivery Time AM/PM");
            scrnMsg.B.no(i).carrNm_B1.setNameForMessage("Carrier");
            scrnMsg.B.no(i).proNum_B1.setNameForMessage("Tracking Number");
            scrnMsg.B.no(i).totFrtAmt_B1.setNameForMessage("Ship Cost");
        }

        // Apply to Detail Lines
        scrnMsg.schdCarrPickUpDt_AP.setNameForMessage("Scheduled Carrier Pickup Date");
        scrnMsg.schdDelyDt_AP.setNameForMessage("Scheduled Delivery Date");
        // QC#18272 Mod.
        scrnMsg.schdDelyTsDplyTxt_AP.setNameForMessage("Scheduled Delivery Time");
        scrnMsg.rqstRcvDtTxt_P2.setNameForMessage("Scheduled Delivery Time AM/PM");

        scrnMsg.shpgSvcLvlCd_AP.setNameForMessage("Service Level");
        scrnMsg.carrNm_AP.setNameForMessage("Carrier Assigned");
        scrnMsg.carrNm_BP.setNameForMessage("Carrier");
        scrnMsg.proNum_BP.setNameForMessage("Tracking Number");
        scrnMsg.totFrtAmt_BP.setNameForMessage("Ship Cost");
        scrnMsg.carrRsnCd_BP.setNameForMessage("Carrier Reason");
        scrnMsg.actlDelyDt_BP.setNameForMessage("Actual Delivery Date");
        // QC#18272 Mod.
        scrnMsg.schdDelyTsDplyTxt_BP.setNameForMessage("Actual Delivery Time");
        scrnMsg.rqstRcvDtTxt_BP.setNameForMessage("Actual Delivery Time AM/PM");
    }
}
