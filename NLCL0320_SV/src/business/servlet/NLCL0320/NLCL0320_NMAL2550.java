/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0320;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLCL0320.NLCL0320CMsg;
//import business.servlet.NLCL0320.constant.NLCL0320Constant;

import business.servlet.NLCL0320.common.NLCL0320CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NLCL0320_NMAL2550 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0320BMsg scrnMsg = (NLCL0320BMsg) bMsg;
        NLCL0320CommonLogic.setAccountFromParameter(scrnMsg);
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
    }
}