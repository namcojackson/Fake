/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8830;

import static business.servlet.NYEL8830.constant.NYEL8830Constant.BIZ_ID;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0005E;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8830.NYEL8830CMsg;
import business.servlet.NYEL8830.common.NYEL8830CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NYEL8830_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/29   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NYEL8830_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NYEL8830BMsg scrnMsg = (NYEL8830BMsg) bMsg;

        Serializable arg = super.getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length >= 2) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.wfProcPk, ((EZDBBigDecimalItem ) params[0]).getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.wfProcStsCd, ((EZDBStringItem ) params[1]).getValue());
            } else {
                scrnMsg.setMessageInfo(NYEM0005E, new String[] {scrnMsg.wfProcPk.getNameForMessage() });
            }
        } else {
            scrnMsg.setMessageInfo(NYEM0005E, new String[] {scrnMsg.wfProcPk.getNameForMessage() });
        }

        if (scrnMsg.getMessageCode().endsWith("E")) {
            throw new EZDFieldErrorException();
        }

        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);

        NYEL8830CMsg bizMsg = new NYEL8830CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NYEL8830BMsg scrnMsg = (NYEL8830BMsg) bMsg;
        NYEL8830CMsg bizMsg = (NYEL8830CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NYEL8830CommonLogic.initRowCtrlProp(scrnMsg);
        NYEL8830CommonLogic.initCmnBtnProp(this);

        NYEL8830CommonLogic.setRowsBGWithClearForSts(scrnMsg, scrnMsg.A, "A");
        NYEL8830CommonLogic.setRowsBGWithClearForHist(scrnMsg, scrnMsg.B, "B");

        if (scrnMsg.getMessageCode().endsWith("E")) {
            return;
        }

        scrnMsg.setFocusItem(scrnMsg.wfProcNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NYEL8830BMsg scrnMsg = (NYEL8830BMsg) bMsg;

        scrnMsg.wfProcPk.setNameForMessage("Process ID");
    }
}
