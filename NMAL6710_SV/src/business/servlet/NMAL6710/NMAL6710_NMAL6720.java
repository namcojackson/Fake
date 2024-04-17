/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6720.NMAL6720CMsg;
//import business.servlet.NMAL6720.constant.NMAL6720Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/20/2015   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL6710_NMAL6720 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing.

       }

       @Override
       protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

           return null;
       }

       @Override
       protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

       }
}
