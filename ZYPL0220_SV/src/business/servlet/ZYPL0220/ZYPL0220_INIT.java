package business.servlet.ZYPL0220;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZYPL0220.common.ZYPL0220CommonLogic;
import business.servlet.ZYPL0220.constant.ZYPL0220Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZYPL0220_INIT extends S21CommonHandler implements ZYPL0220Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // none
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // --------------------------------------------
        // 1) set Upload CSV ID
        // --------------------------------------------
        ZYPL0220BMsg scrnMsg = (ZYPL0220BMsg) bMsg;

        Object[] arg4Sub = (Object[]) getArgForSubScreen();
        if (arg4Sub != null && arg4Sub.length > 0) {
            String upldCsvId = ((EZDBStringItem) arg4Sub[0]).getValue();
            scrnMsg.upldCsvId.setValue(upldCsvId);
            scrnMsg.upldCsvId_DF.setValue(upldCsvId);
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // --------------------------------------------
        // 2) set screen mode
        // --------------------------------------------
        ZYPL0220BMsg scrnMsg = (ZYPL0220BMsg) bMsg;
        ZYPL0220CommonLogic.setInitMode(this, scrnMsg);
    }

}
