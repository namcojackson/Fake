/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
*/
package business.servlet.NLCL1020;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1020.common.NLCL1020CommonLogic;
import business.servlet.NLCL1020.constant.NLCL1020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         Y.Taoka         Create          R-WH001
 *</pre>
 */
public class NLCL1020Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
     // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        NLCL1020CommonLogic.initialize(this, scrnMsg);

        NLCL1020CommonLogic.setScrnItemValue_NLCL1020Scrn00_CMN_Reset(this, scrnMsg);

        S21TableColorController tblColor = new S21TableColorController(NLCL1020Constant.SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
    }

}
