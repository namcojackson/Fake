/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0010;

import parts.common.*;
import parts.servletcommon.*;
// import business.blap.ZZOL0010.ZZOL0010CMsg;

import business.servlet.ZZOL0010.common.ZZOL0010CommonLogic;
import business.servlet.ZZOL0010.constant.ZZOL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZOL0010Scrn01_CMN_Return extends S21CommonHandler implements ZZOL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;
        
        // Activate Delete button when serch result is remaining.
        if(scrnMsg.A.getValidCount() > 0){
            ZZOL0010CommonLogic.deleteCommonButton(this);
        }
        
        setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
        setButtonProperties(CMN_BTN2[0], null, null, 0, null);
        setButtonProperties(CMN_BTN9[0], null, null, 0, null);
        setButtonConfirmMsg(CMN_BTN10[1], null, null, 0);
        
        
        // Table Color Controll
        S21TableColorController tblColor = new S21TableColorController(ZZOL0010Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

}
