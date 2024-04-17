/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2030.NLAL2030CMsg;
import business.servlet.NLAL2030.common.NLAL2030CommonLogic;
import business.servlet.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NLAL2030_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 * 02/07/2018   CITS            T.Tokutomi      Update          QC#18461-Sol#085
 * 2023/02/28   Hitachi         TZ.Win          Update          QC#61160
 *</pre>
 */
public class NLAL2030_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NLAL2030Constant.BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;
        NLAL2030CMsg bizMsg = new NLAL2030CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 7) {

            // Source Document Number
            EZDBStringItem param00 = (EZDBStringItem) params[0];
            if (ZYPCommonFunc.hasValue(param00)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.trxOrdNum_BK, param00);
            }
            // Shipment Number
            EZDBStringItem param01 = (EZDBStringItem) params[1];
            if (ZYPCommonFunc.hasValue(param01)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rwsRefNum_BK, param01);
            }
            // RWS Number
            EZDBStringItem param02 = (EZDBStringItem) params[2];
            if (ZYPCommonFunc.hasValue(param02)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rwsNum_BK, param02);
            }
            // Configuration ID
            EZDBStringItem param03 = (EZDBStringItem) params[3];
            if (ZYPCommonFunc.hasValue(param03)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_BK, param03);
            }
            // Tracking Number
            EZDBStringItem param04 = (EZDBStringItem) params[4];
            if (ZYPCommonFunc.hasValue(param04)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.imptInvBolNum_BK, param04);
            }
            // Retail Warehouse Code
            EZDBStringItem param05 = (EZDBStringItem) params[5];
            if (ZYPCommonFunc.hasValue(param05)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_BK, param05);
            }
            // Tab
            EZDBStringItem param06 = (EZDBStringItem) params[6];
            if (ZYPCommonFunc.hasValue(param06)) {
                if ("R".equals(param06.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_BK, NLAL2030Constant.TAB_ID_RWS);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_BK, NLAL2030Constant.TAB_ID_ORD);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_BK, NLAL2030Constant.TAB_ID_ORD);
            }
        }

        bizMsg.setBusinessID(NLAL2030Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;
        NLAL2030CMsg bizMsg = (NLAL2030CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLAL2030CommonLogic.initCmnBtnProp(this);
        NLAL2030CommonLogic.controlScreenFields(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.trxOrdNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        scrnMsg.trxOrdNum.setNameForMessage("Source Document Number ");
        scrnMsg.mdseCd.setNameForMessage("Item Number");
        scrnMsg.fromLocCd.setNameForMessage("Party");
        scrnMsg.sceOrdTpCd.setNameForMessage("Source Document Type");
        scrnMsg.svcConfigMstrPk.setNameForMessage("Config ID");
        scrnMsg.rtlWhCd.setNameForMessage("Warehouse");
        scrnMsg.rwsRefNum.setNameForMessage("Shipment Number");
        scrnMsg.serNum.setNameForMessage("Serial Number");
        scrnMsg.rtlSwhCd.setNameForMessage("Sub Warehouse");
        scrnMsg.whInEtaDt_FR.setNameForMessage("EAT Date(From)");
        scrnMsg.whInEtaDt_TO.setNameForMessage("ETA Date(Through)");
        scrnMsg.imptInvBolNum.setNameForMessage("Tracking Number");
        scrnMsg.carrCd.setNameForMessage("Carrier");
        scrnMsg.rwsNum.setNameForMessage("RWS Number");
        scrnMsg.rwsStsCd.setNameForMessage("RWS Status");
        scrnMsg.flipMdseCd.setNameForMessage("Alternate Item");
        scrnMsg.whCd.setNameForMessage("Received WH");
        scrnMsg.rwsRefNum_AP.setNameForMessage("Shipment Number");
        scrnMsg.imptInvBolNum_AP.setNameForMessage("Tracking Number");
        scrnMsg.xxPageShowCurNum_A.setNameForMessage("Current Page Number");
        scrnMsg.xxPageShowCurNum_B.setNameForMessage("Current Page Number");

        // QC#18461-Sol#085 Add.
        scrnMsg.rtlWhCd_AP.setNameForMessage("Warehouse");
        scrnMsg.thirdPtyDspTpCd_AP.setNameForMessage("RMA Disposition");
        // START 2023/02/28 TZ.Win [QC#61160, ADD]
        scrnMsg.rtlSwhCd_AP.setNameForMessage("Sub Warehouse");
        // END 2023/02/28 TZ.Win [QC#61160, ADD]

        for (int i = 0; i < scrnMsg.A.length(); i++) {

            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).xxChkBox_A2.setNameForMessage("Check Box");
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {

            scrnMsg.B.no(i).xxChkBox_B1.setNameForMessage("Check Box");
            // QC#18461-Sol#085 Add.
            scrnMsg.B.no(i).rtlWhCd_B1.setNameForMessage("Warehouse");
            scrnMsg.B.no(i).thirdPtyDspTpCd_B1.setNameForMessage("RMA Disposition");
        }
    }
}
