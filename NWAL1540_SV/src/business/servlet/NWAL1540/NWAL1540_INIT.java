/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1540;

import static business.servlet.NWAL1540.constant.NWAL1540Constant.BIZ_ID;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.NWAL1540_PRM_CNT;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.ZZM9000E;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.ZZXM0007E;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1540.NWAL1540CMsg;
import business.servlet.NWAL1540.common.NWAL1540CommonLogic;
import business.servlet.NWAL1540.constant.NWAL1540Constant.MODE;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1540_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL1540_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1540BMsg scrnMsg = (NWAL1540BMsg) bMsg;
        NWAL1540CMsg bizMsg = new NWAL1540CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == NWAL1540_PRM_CNT) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_IN, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.trxHdrNum_IN, (EZDBStringItem) params[1]);
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxModeCd_IN)) {
                scrnMsg.setMessageInfo(ZZM9000E, new String[] {scrnMsg.xxModeCd.getNameForMessage() });
                return null;
            }
            if (!MODE.ORDER.getValue().equals(scrnMsg.xxModeCd_IN.getValue()) //
                    && !MODE.QUOTE.getValue().equals(scrnMsg.xxModeCd_IN.getValue())) {
                scrnMsg.setMessageInfo(ZZXM0007E, new String[] {scrnMsg.xxModeCd.getNameForMessage(), scrnMsg.xxModeCd_IN.getValue() });
                return null;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.trxHdrNum_IN)) {
                scrnMsg.setMessageInfo(ZZM9000E, new String[] {scrnMsg.trxHdrNum.getNameForMessage() });
                return null;
            }
        } else {
            // it come from menu screen.
            scrnMsg.xxModeCd_IN.clear();
            scrnMsg.trxHdrNum_IN.clear();
        }
        scrnMsg.xxPageShowCurNum.clear();
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowTotNum.clear();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1540BMsg scrnMsg = (NWAL1540BMsg) bMsg;
        NWAL1540CMsg bizMsg = (NWAL1540CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1540CommonLogic.initCmnBtnProp(this);
        NWAL1540CommonLogic.initBizBtnProp(this, scrnMsg);
        NWAL1540CommonLogic.setInputControl(scrnMsg);
        NWAL1540CommonLogic.setAppFracDigit(scrnMsg);
        NWAL1540CommonLogic.setGpControl(this, scrnMsg);// 2016/03/10 S21_NA#2939

        if (!ZYPCommonFunc.hasValue(scrnMsg.trxHdrNum)) {
            scrnMsg.setFocusItem(scrnMsg.trxHdrNum);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1540BMsg scrnMsg = (NWAL1540BMsg) bMsg;

        scrnMsg.xxModeCd.setNameForMessage("Process Mode");
        scrnMsg.trxHdrNum.setNameForMessage("Transaction Number");
        scrnMsg.ordPrftVrsnNum.setNameForMessage("Version Number");

    }

}
