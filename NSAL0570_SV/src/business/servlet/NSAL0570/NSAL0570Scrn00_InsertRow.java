/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0570;

import static business.servlet.NSAL0570.common.NSAL0570CommonLogic.initialControlScreen;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0570.NSAL0570CMsg;
import business.servlet.NSAL0570.common.NSAL0570CommonLogic;
//import business.servlet.NSAL0570.constant.NSAL0570Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Hitachi         K.Kasai         Create          N/A
 * 2016/02/16   Hitachi         K.Kishimoto     Update          QC#3846
 * 2020/01/15   Hitachi         Y.Takeno        Update          QC#55434
 *</pre>
 */
public class NSAL0570Scrn00_InsertRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2020/01/15 [QC#55434, ADD]
        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;
        NSAL0570CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        // END   2020/01/15 [QC#55434, ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;

        NSAL0570CMsg bizMsg = new NSAL0570CMsg();
        bizMsg.setBusinessID("NSAL0570");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;
        NSAL0570CMsg bizMsg  = (NSAL0570CMsg) cMsg;

        // START 2020/01/15 [QC#55434, ADD]
        boolean hasError = false;
        if (scrnMsg.A.getValidCount() == bizMsg.A.getValidCount()) {
            hasError = true;
        }
        // END   2020/01/15 [QC#55434, ADD]

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // START 2020/01/15 [QC#55434, ADD]
        if (hasError) {
            NSAL0570CommonLogic.addCheckItem(scrnMsg);
            scrnMsg.putErrorScreen();
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
        }
        // END   2020/01/15 [QC#55434, ADD]

        initialControlScreen(this, scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_A, new BigDecimal(scrnMsg.A.getValidCount() - 1));
        //Add Start 02/16/2016 <QC#3846>
        ZYPEZDItemValueSetter.setValue(scrnMsg.rowSqNum_PV, scrnMsg.xxRadioBtn_A);
        //Add End   02/16/2016 <QC#3846>
    }
}
