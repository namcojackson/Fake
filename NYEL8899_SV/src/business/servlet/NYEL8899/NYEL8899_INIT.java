/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8899;

import static business.servlet.NYEL8899.constant.NYEL8899Constant.BIZ_ID;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0005E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8899.NYEL8899CMsg;
import business.servlet.NYEL8899.common.NYEL8899CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NYEL8899_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8899_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // checkBusinessAppGranted(getContextUserInfo().getUserId(),
        // BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8899BMsg scrnMsg = (NYEL8899BMsg) bMsg;
        NYEL8899CMsg bizMsg = new NYEL8899CMsg();

        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length >= 2) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.wfProcNm, (String) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.wfDataNm, (String) params[1]);

            if (!ZYPCommonFunc.hasValue(scrnMsg.wfProcNm)) {
                scrnMsg.setMessageInfo(NYEM0005E, new String[] {scrnMsg.wfProcNm.getNameForMessage() });
                return null;
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.wfDataNm)) {
                scrnMsg.setMessageInfo(NYEM0005E, new String[] {scrnMsg.wfDataNm.getNameForMessage() });
                return null;
            }
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8899BMsg scrnMsg = (NYEL8899BMsg) bMsg;
        NYEL8899CMsg bizMsg = (NYEL8899CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NYEL8899CommonLogic.initCmnBtnProp(scrnMsg, this);

        scrnMsg.setFocusItem(scrnMsg.wfClsNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        // do nothing
    }
}
