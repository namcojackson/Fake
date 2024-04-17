/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2020;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2020.NLAL2020CMsg;
import business.servlet.NLAL2020.common.NLAL2020CommonLogic;
import business.servlet.NLAL2020.constant.NLAL2020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NLAL2020_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2015   Fujitsu         Y.Taoka         Create          N/A
 * 04/13/2016   CITS            T.Tokutomi      Update          Merge DS
 * 06/13/2017   CITS            Y.Imazu         Update          QC#19058
 *</pre>
 */
public class NLAL2020_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NLAL2020Constant.BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;
        NLAL2020CMsg bizMsg = new NLAL2020CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null) {

            // Shipment Number
            if (params.length > 0) {
                EZDBStringItem param00 = (EZDBStringItem) params[0];
                if (ZYPCommonFunc.hasValue(param00)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rwsRefNum_BK, param00);
                }
            }
            // Tracking Number
            if (params.length > 1) {
                EZDBStringItem param01 = (EZDBStringItem) params[1];
                if (ZYPCommonFunc.hasValue(param01)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.bolNum_BK, param01);
                }
            }
            // RWS Number
            if (params.length > 2) {
                EZDBStringItem param02 = (EZDBStringItem) params[2];
                if (ZYPCommonFunc.hasValue(param02)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rwsNum_BK, param02);
                }
            }
            // Source Document Number
            if (params.length > 3) {
                EZDBStringItem param03 = (EZDBStringItem) params[3];
                if (ZYPCommonFunc.hasValue(param03)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.srcOrdNum_BK, param03);
                }
            }
            // Configuration ID
            if (params.length > 4) {
                EZDBStringItem param04 = (EZDBStringItem) params[4];
                if (ZYPCommonFunc.hasValue(param04)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_BK, param04);
                }
            }
            // Retail Warehouse Code
            if (params.length > 5) {
                EZDBStringItem param05 = (EZDBStringItem) params[5];
                if (ZYPCommonFunc.hasValue(param05)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.shipToRtlWhCd_BK, param05);
                }
            }
            // Party Code
            if (params.length > 6) {
                EZDBStringItem param06 = (EZDBStringItem) params[6];
                if (ZYPCommonFunc.hasValue(param06)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.shipLocCd_BK, param06);
                }
            }
            // Serial Number
            if (params.length > 7) {
                EZDBStringItem param07 = (EZDBStringItem) params[7];
                if (ZYPCommonFunc.hasValue(param07)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_BK, param07);
                }
            }
            // Retail Warehouse Code
            if (params.length > 8) {
                EZDBStringItem param08 = (EZDBStringItem) params[8];
                if (ZYPCommonFunc.hasValue(param08)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_BK, param08);
                }
            }
        }

        bizMsg.setBusinessID(NLAL2020Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;
        NLAL2020CMsg bizMsg = (NLAL2020CMsg) cMsg;

        if (bizMsg != null) {

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NLAL2020CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NLAL2020CommonLogic.initCmnBtnProp(this);
        NLAL2020CommonLogic.controlScreenFields(this, scrnMsg);

        if (0 < scrnMsg.A.getValidCount()) {

            NLAL2020CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, NLAL2020Bean.A);
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A1);

        } else {

            scrnMsg.setFocusItem(scrnMsg.rwsRefNum_H);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;

        scrnMsg.rwsRefNum_H.setNameForMessage("Shipment Number");
        scrnMsg.rwsNum_H.setNameForMessage("RWS Number");
        scrnMsg.bolNum_H.setNameForMessage("Tracking Number");
        scrnMsg.rwsStsCd_H.setNameForMessage("RWS Status");
        scrnMsg.srcOrdNum_H.setNameForMessage("Source Document Number");
        scrnMsg.sceOrdTpCd_H.setNameForMessage("Source Document Type");
        scrnMsg.svcConfigMstrPk_H.setNameForMessage("Config ID");
        scrnMsg.serNum_H.setNameForMessage("Serial Number");
        scrnMsg.shipToRtlWhCd_H.setNameForMessage("Warehouse/Tech");
        scrnMsg.rtlWhCd_H.setNameForMessage("Received WH");
        scrnMsg.shipLocCd_H.setNameForMessage("Party");
        scrnMsg.whInEtaDt_FR.setNameForMessage("ETA Date(From)");
        scrnMsg.whInEtaDt_TO.setNameForMessage("ETA Date(Through)");

        scrnMsg.xxPageShowCurNum.setNameForMessage("Current Page Number");

        for (int i = 0; i < scrnMsg.A.length(); i++) {

            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).rtlWhCd_A1.setNameForMessage("Received WH");
            scrnMsg.A.no(i).invtyStkStsCd_A1.setNameForMessage("Receiving Stock Status");
            scrnMsg.A.no(i).poBalQty_A1.setNameForMessage("Receiving Quantity");
            scrnMsg.A.no(i).serNum_A1.setNameForMessage("Serial Number");
        }
    }
}
