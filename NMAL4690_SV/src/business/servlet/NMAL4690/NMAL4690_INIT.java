/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.NMAL4690;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL4690.NMAL4690CMsg;
import business.servlet.NMAL4690.common.NMAL4690CommonLogic;
import business.servlet.NMAL4690.constant.NMAL4690Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NMAL4690_INIT extends S21INITCommonHandler implements NMAL4690Constant {

    private NMAL4690CommonLogic common = new NMAL4690CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NMAL4690BMsg scrnMsg = (NMAL4690BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL4690BMsg scrnMsg = (NMAL4690BMsg) bMsg;
        NMAL4690CMsg bizMsg = new NMAL4690CMsg();
        bizMsg.setBusinessID("NMAL4690");
        bizMsg.setFunctionCode("20");

        // get argments from main screen.
        Object[] param = (Object[]) getArgForSubScreen();
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                if (i == 0) {
                    scrnMsg.xxAllPsnNm.setValue(((EZDBStringItem) param[i]).getValue());
                } else if (i == 1) {
                    scrnMsg.cmpyNm.setValue(((EZDBStringItem) param[i]).getValue());
                } else if (i == 2) {
                	scrnMsg.ptyLocPk.setValue(NMAL4690CommonLogic.getPtyLocPk(scrnMsg,getArgForSubScreen()));
                } else if (i == 3) {
                	scrnMsg.cmpyPk.setValue(NMAL4690CommonLogic.getCmpyPk(scrnMsg,getArgForSubScreen()));
                }
            }
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL4690BMsg scrnMsg = (NMAL4690BMsg) bMsg;
        NMAL4690CMsg bizMsg = (NMAL4690CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL4690CommonLogic.initCommonButton(scrnMsg, this);
        common.setInputProtectedForListInputFiled(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL4690BMsg scrnMsg = (NMAL4690BMsg) bMsg;
        NMAL4690CommonLogic.setNameForMessage(scrnMsg);
    }

}
