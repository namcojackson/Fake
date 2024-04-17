package business.servlet.ZZOL0130.common;

import business.servlet.ZZOL0130.ZZOL0130BMsg;
import business.servlet.ZZOL0130.constant.ZZOL0130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZOL0130CommonLogic implements ZZOL0130Constant {

    public static void dspScrn(S21CommonHandler handler, ZZOL0130BMsg scrnMsg) {
        // Color Settings
        S21TableColorController tblColor = new S21TableColorController(SCREEN_NAME, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        
        if (scrnMsg.delTblConfigPk.isClear()) {
            handler.setButtonEnabled("Upd", false);
        } else {
            handler.setButtonEnabled("Upd", true);
        }
        
        for(int i=0;i<scrnMsg.A.getValidCount();i++){
            scrnMsg.A.no(i).delTblCmntTxt_A1.setInputProtected(true);
        }
    }

    
}
