/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3120;

import static business.servlet.NLBL3120.constant.NLBL3120Constant.BIZ_ID;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3120.NLBL3120CMsg;
import business.servlet.NLBL3120.common.NLBL3120CommonLogic;
import business.servlet.NLBL3120.constant.NLBL3120Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         S.Yoshida       Create          N/A
 *</pre>
 */
public class NLBL3120_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;
        NLBL3120CMsg bizMsg = new NLBL3120CMsg();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length == 0) {
                return null;
            }

            switch (params.length) {

                // Sales Rep Code
                case 10:
                    EZDBStringItem param09 = (EZDBStringItem) params[9];
                    if (ZYPCommonFunc.hasValue(param09)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepOrSlsTeamTocCd_BK, param09);
                    }

                // Manager Parson Code
                case 9:
                    EZDBStringItem param08 = (EZDBStringItem) params[8];
                    if (ZYPCommonFunc.hasValue(param08)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.mgrPsnCd_BK, param08);
                    }

                // Supervisor Person Code
                case 8:
                    EZDBStringItem param07 = (EZDBStringItem) params[7];
                    if (ZYPCommonFunc.hasValue(param07)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.supvPsnCd_BK, param07);
                    }

                // Schedule Coordinator Parson Code
                case 7:
                    EZDBStringItem param06 = (EZDBStringItem) params[6];
                    if (ZYPCommonFunc.hasValue(param06)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.schdCoordPsnCd_BK, param06);
                    }

                // Retail Warehouse Code
                case 6:
                    EZDBStringItem param05 = (EZDBStringItem) params[5];
                    if (ZYPCommonFunc.hasValue(param05)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_BK, param05);
                    }

                // RWS Number
                case 5:
                    EZDBStringItem param04 = (EZDBStringItem) params[4];
                    if (ZYPCommonFunc.hasValue(param04)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.rwsNum_BK, param04);
                    }

                // SO Number
                case 4:
                    EZDBStringItem param03 = (EZDBStringItem) params[3];
                    if (ZYPCommonFunc.hasValue(param03)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.soNum_BK, param03);
                    }

                // Service Configuration Master PK
                case 3:
                    if (params[2] instanceof EZDBStringItem) {
                        EZDBStringItem param02 = (EZDBStringItem) params[2];
                        if (ZYPCommonFunc.hasValue(param02)) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_BK, new BigDecimal(param02.getValue()));
                        }
                    } else if (params[2] instanceof EZDBBigDecimalItem) {
                        EZDBBigDecimalItem param02 = (EZDBBigDecimalItem) params[2];
                        if (ZYPCommonFunc.hasValue(param02)) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_BK, param02);
                        }
                    }

                // Model Name
                case 2:
                    EZDBStringItem param01 = (EZDBStringItem) params[1];
                    if (ZYPCommonFunc.hasValue(param01)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.t_MdlNm_BK, param01);
                    }

                // Transaction Header Number
                case 1:
                    EZDBStringItem param00 = (EZDBStringItem) params[0];
                    if (ZYPCommonFunc.hasValue(param00)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.trxHdrNum_BK, param00);
                    }

                default:
                    break;
            }
        }

        bizMsg.setBusinessID(NLBL3120Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;
        NLBL3120CMsg bizMsg = (NLBL3120CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NLBL3120CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.trxHdrNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;

        // Header
        //// Search Option
        scrnMsg.srchOptPk_S.setNameForMessage("Search Option");
        scrnMsg.srchOptNm_S.setNameForMessage("Search Option Name");

        //// Search Condition
        scrnMsg.trxHdrNum.setNameForMessage("Order Number");
        scrnMsg.dsOrdCatgCd.setNameForMessage("Order Category");
        scrnMsg.dsOrdTpCd.setNameForMessage("Order Reason");
        scrnMsg.t_MdlNm.setNameForMessage("Model");
        scrnMsg.svcConfigMstrPk.setNameForMessage("Config ID");

        scrnMsg.soNum.setNameForMessage("Shipping Order Number");
        scrnMsg.dsSoLineStsCd.setNameForMessage("Shipping Order Status");
        scrnMsg.rwsNum.setNameForMessage("RWS Number");
        scrnMsg.rwsStsCd.setNameForMessage("RWS Status");
        scrnMsg.schdCoordStsCd.setNameForMessage("Scheduling Status");

        scrnMsg.rtlWhCd.setNameForMessage("Warehouse");
        scrnMsg.schdCoordPsnCd.setNameForMessage("Coordinator");
        scrnMsg.supvPsnCd .setNameForMessage("Supervisor");
        scrnMsg.mgrPsnCd .setNameForMessage("Manager");
        scrnMsg.slsRepOrSlsTeamTocCd.setNameForMessage("Sales Rep");

        scrnMsg.rddDt_FR.setNameForMessage("Request Date (From)");
        scrnMsg.rddDt_TO.setNameForMessage("Request Date (Through)");

        scrnMsg.schdCoordDt_FR.setNameForMessage("Schedule Date (From)");
        scrnMsg.schdCoordDt_TO.setNameForMessage("Schedule Date (Through)");

        scrnMsg.xxPageShowCurNum_A.setNameForMessage("Current Page Number");

        // Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).schdCoordPsnCd_A1.setNameForMessage("Coordinator");
            scrnMsg.A.no(i).shpgSvcLvlCd_A1.setNameForMessage("Shipping /Pickup Service Level");
            scrnMsg.A.no(i).carrCd_A1.setNameForMessage("Carrier Code");
            scrnMsg.A.no(i).schdPickUpDt_A1.setNameForMessage("Scheduled Date");
        }

        // Bottom
        scrnMsg.schdCoordPsnNm_BT.setNameForMessage("Coordinator");
        scrnMsg.schdCoordDt_BT.setNameForMessage("Schedule Date");
        scrnMsg.shpgSvcLvlCd_BT.setNameForMessage("Shipping Service Level");
        scrnMsg.carrNm_BT.setNameForMessage("Assigned Carrier");

    }
}
