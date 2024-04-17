/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3120;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3120.NFCL3120CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/02   Hitachi         K.Kojima        Create          QC#5521
 *</pre>
 */
public class NFCL3120Scrn00_OnChangeSavedSearchOption extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
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
        NFCL3120CMsg bizMsg = (NFCL3120CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.putErrorScreen();

        scrnMsg.xxChkBox_H1.setInputProtected(false);
        scrnMsg.dsAcctNm_H1.setInputProtected(false);
        scrnMsg.dsAcctNum_L1.setInputProtected(false);
        if (!scrnMsg.xxRadioBtn.getValue().equals(BigDecimal.ONE)) {
            scrnMsg.xxChkBox_H1.setInputProtected(true);
            scrnMsg.dsAcctNm_H1.setInputProtected(true);
            scrnMsg.dsAcctNum_L1.setInputProtected(true);
            scrnMsg.dsAcctNm_H1.clear();
            scrnMsg.dsAcctNum_H1.clear();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H1.getValue())) {
            scrnMsg.dsAcctNm_H1.setInputProtected(true);
            scrnMsg.dsAcctNum_L1.setInputProtected(true);
        }
    }

}
