/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/22   CSAI            K.Uramori       Create          N/A
 * 2016/07/12   Hitachi         K.Kojima        Update          QC#11049
 *</pre>
 */
public class NFCL3050Scrn00_btnUnslctAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFCL3050BMsg scrnMsg = (NFCL3050BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFCL3050BMsg scrnMsg = (NFCL3050BMsg) bMsg;

        // NFCL3050CMsg bizMsg = new NFCL3050CMsg();
        // bizMsg.setBusinessID("NFCL3050");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3050BMsg scrnMsg = (NFCL3050BMsg) bMsg;
        // START 2016/07/12 K.Kojima [QC#11049,DEL]
        // NFCL3050CMsg bizMsg = (NFCL3050CMsg) cMsg;
        //
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // END 2016/07/12 K.Kojima [QC#11049,DEL]

        ZYPTableUtil.unSelectAll(scrnMsg.A, "xxChkBox_A");

    }
}
