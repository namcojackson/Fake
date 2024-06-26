/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2630;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL2630.NMAL2630CMsg;
//import business.servlet.NMAL2630.constant.NMAL2630Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL2630Scrn00_Link_AdvancedSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2630BMsg scrnMsg = (NMAL2630BMsg) bMsg;

        if ("AdvancedSearch".equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.xxDplyTab.setValue("");
        } else {
            scrnMsg.xxDplyTab.setValue("AdvancedSearch");
        }
    }
}
