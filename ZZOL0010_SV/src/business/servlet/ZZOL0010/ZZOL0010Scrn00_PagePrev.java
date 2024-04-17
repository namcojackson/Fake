/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/18   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0010;

import parts.common.*;
import parts.servletcommon.*;
//import parts.servletcommon.gui.*;
import business.blap.ZZOL0010.ZZOL0010CMsg;

import business.servlet.ZZOL0010.common.ZZOL0010CommonLogic;
import business.servlet.ZZOL0010.constant.ZZOL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZOL0010Scrn00_PagePrev extends S21CommonHandler implements ZZOL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;
        ZZOL0010CMsg bizMsg = new ZZOL0010CMsg();

        // set values to items of pagenation for prev page
//        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt() - scrnMsg.A.length() - 1);
//        scrnMsg.xxPageShowToNum.clear();

        bizMsg.setBusinessID("ZZOL0010");
        bizMsg.setFunctionCode("20");
        
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        bizMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt() - scrnMsg.A.length() - 1);
        
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;
        ZZOL0010CMsg bizMsg = (ZZOL0010CMsg) cMsg;

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        ZZOL0010CommonLogic.convertFlagDisplay(scrnMsg, bizMsg);
        ZZOL0010CommonLogic.convertTimeDisplay(scrnMsg);
        
        // Table Color Controll
        S21TableColorController tblColor = new S21TableColorController(ZZOL0010Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

    }

}
