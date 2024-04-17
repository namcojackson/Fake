/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7010.common.NMAL7010CommonLogic;
import business.servlet.NMAL7010.constant.NMAL7010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/16   SRA             E.Inada         Create          N/A
 *</pre>
 */
public class NMAL7010Scrn00_OpenWin_Filter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcCatgCd_BK)) {
            scrnMsg.setMessageInfo(NMAL7010Constant.NMAM8367E);
        }

        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith("E")) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        setArgForSubScreen(NMAL7010CommonLogic.setArgumentNWAL7040(scrnMsg, getGlobalCompanyCode()));
    }
}
