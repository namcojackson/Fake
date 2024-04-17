/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3120;

import static business.servlet.NFCL3120.constant.NFCL3120Constant.SCREEN_ID;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3120.NFCL3120CMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Fujitsu         T.Tanaka        Create          Def#2569
 * 2016/08/02   Hitachi         K.Kojima        Update          QC#5521
 *</pre>
 */
public class NFCL3120Scrn00_OnChangeRadio extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;

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
        
        if(scrnMsg.xxRadioBtn.getValue().equals(BigDecimal.ONE)) {
            scrnMsg.xxChkBox_H1.setInputProtected(false);
            scrnMsg.dsAcctNm_H1.setInputProtected(false);

            scrnMsg.dsAcctNum_L1.setInputProtected(false);
        } else {
            scrnMsg.xxChkBox_H1.setInputProtected(true);
            scrnMsg.dsAcctNm_H1.setInputProtected(true);
            scrnMsg.dsAcctNum_H1.clear();
            scrnMsg.dsAcctNm_H1.clear();

            scrnMsg.dsAcctNum_L1.setInputProtected(true);
        }
        // START 2016/08/02 K.Kojima [QC#5521,ADD]
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // END 2016/08/02 K.Kojima [QC#5521,ADD]
    }
}
