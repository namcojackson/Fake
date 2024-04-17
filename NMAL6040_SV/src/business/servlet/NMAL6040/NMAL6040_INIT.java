/*
 * <pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6040;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6040.NMAL6040CMsg;
import business.servlet.NMAL6040.common.NMAL6040CommonLogic;
import business.servlet.NMAL6040.constant.NMAL6040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * NMAL6040 P&L Product Structure Pop Up
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ----------------------------------------------------------------------
 * 09/05/2012   Fujitsu         H.Mizutani      Update          N/A 
 *</pre>
 */
public class NMAL6040_INIT extends S21INITCommonHandler implements NMAL6040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // No Operation
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6040BMsg scrnMsg = (NMAL6040BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        scrnMsg.xxChkBox.setValue(ZYPConstant.CHKBOX_ON_Y);
        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            EZDBStringItem param00 = (EZDBStringItem) params[0];
            EZDBStringItem param01 = (EZDBStringItem) params[1];
            EZDBStringItem param02 = (EZDBStringItem) params[2];
            EZDBStringItem param03 = (EZDBStringItem) params[3];
            EZDBStringItem param04 = (EZDBStringItem) params[4];
            EZDBStringItem param05 = (EZDBStringItem) params[5];
            EZDBStringItem param06 = (EZDBStringItem) params[6];
            EZDBStringItem param07 = (EZDBStringItem) params[7];
            EZDBStringItem param08 = (EZDBStringItem) params[8];
            EZDBStringItem param09 = (EZDBStringItem) params[9];
            EZDBStringItem param10 = (EZDBStringItem) params[10];
            EZDBStringItem param11 = (EZDBStringItem) params[11];
            EZDBStringItem param12 = (EZDBStringItem) params[12];
            EZDBStringItem param13 = (EZDBStringItem) params[13];

            // include merchandise <defect#5533 T.Ishii 20100428>
            if (params.length > 28) {
                EZDBStringItem param28 = (EZDBStringItem) params[28];
                if (ZYPCommonFunc.hasValue(param28)) {
                    scrnMsg.xxChkBox.setValue(param28.getValue());
                } else {
                    scrnMsg.xxChkBox.clear();
                }
            }
            if (params.length > 29) {
                EZDBStringItem param29 = (EZDBStringItem) params[29];
                if (ZYPCommonFunc.hasValue(param29)) {
                    scrnMsg.xxModeCd.setValue(param29.getValue());
                } else {
                    scrnMsg.xxModeCd.clear();
                }
            }

            scrnMsg.zerothProdCtrlCd.setValue(param00.getValue());
            scrnMsg.zerothProdCtrlNm.setValue(param01.getValue());
            scrnMsg.firstProdCtrlCd.setValue(param02.getValue());
            scrnMsg.firstProdCtrlNm.setValue(param03.getValue());
            scrnMsg.scdProdCtrlCd.setValue(param04.getValue());
            scrnMsg.scdProdCtrlNm.setValue(param05.getValue());
            scrnMsg.thirdProdCtrlCd.setValue(param06.getValue());
            scrnMsg.thirdProdCtrlNm.setValue(param07.getValue());
            scrnMsg.frthProdCtrlCd.setValue(param08.getValue());
            scrnMsg.frthProdCtrlNm.setValue(param09.getValue());
            scrnMsg.fifthProdCtrlCd.setValue(param10.getValue());
            scrnMsg.fifthProdCtrlNm.setValue(param11.getValue());
            scrnMsg.mdseCd.setValue(param12.getValue());
            scrnMsg.mdseNm.setValue(param13.getValue());
        }

        NMAL6040CMsg bizMsg = new NMAL6040CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6040BMsg scrnMsg = (NMAL6040BMsg) bMsg;
        NMAL6040CMsg bizMsg = (NMAL6040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL6040CommonLogic.initCommonButton(this);
        NMAL6040CommonLogic.initButton(this);
        NMAL6040CommonLogic.initScrn(scrnMsg, scrnMsg.A.no(0).getBaseContents());

    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

    }

}
