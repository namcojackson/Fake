/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7140.NMAL7140CMsg;
//import business.servlet.NMAL7140.constant.NMAL7140Constant;

import business.servlet.NMAL7140.common.NMAL7140CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/12/06   Fujitsu         S.Kosaka        Create          N/A
 *</pre>
 */
public class NMAL7140_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7140BMsg scrnMsg = (NMAL7140BMsg) bMsg;
        Object[] arg = (Object[]) getArgForSubScreen();
        NMAL7140CommonLogic.setInputParam(scrnMsg, arg);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7140BMsg scrnMsg = (NMAL7140BMsg) bMsg;

        NMAL7140CMsg bizMsg = new NMAL7140CMsg();
        bizMsg.setBusinessID("NMAL7140");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7140BMsg scrnMsg = (NMAL7140BMsg) bMsg;
        NMAL7140CMsg bizMsg  = (NMAL7140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL7140CommonLogic.initCmnBtnProp(this);
        NMAL7140CommonLogic.promQifyCatgBtnCtrl(scrnMsg);

    }
}
