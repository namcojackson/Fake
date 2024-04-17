/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3120;

import static business.servlet.NFCL3120.constant.NFCL3120Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3120.NFCL3120CMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/07/27   Hitachi         K.Kojima        Update          QC#4870
 * 2016/08/02   Hitachi         K.Kojima        Update          QC#5521
 *</pre>
 */
public class NFCL3120Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;

//        if(scrnMsg.xxRadioBtn.getValue().equals(BigDecimal.ONE)) {
//            scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
//            scrnMsg.putErrorScreen();
//        }

        // START 2016/07/27 K.Kojima [QC#4870,ADD]
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsBankAcctNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsBankBrNm_H1);
        scrnMsg.putErrorScreen();
        // END 2016/07/27 K.Kojima [QC#4870,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;

        NFCL3120CMsg bizMsg = new NFCL3120CMsg();
        bizMsg.setBusinessID("NFCL3120");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;
        NFCL3120CMsg bizMsg  = (NFCL3120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        if(scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).dsBankAcctNum_A1);
        }

        // START 2016/07/27 K.Kojima [QC#4870,ADD]
        S21TableColorController tblColor = new S21TableColorController("NFCL3120Scrn00", scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        // END 2016/07/27 K.Kojima [QC#4870,ADD]

        // START 2016/08/02 K.Kojima [QC#5521,ADD]
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // END 2016/08/02 K.Kojima [QC#5521,ADD]
    }
}
