/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.NWDL0260;

import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.addCheckAllItems;
import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.createCMsgForUpdate;
import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.hasError;
import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.setGuiAttr;
import static business.servlet.NWDL0260.common.NWDL0260CommonLogic.toArray;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWDL0260.NWDL0260CMsg;
import business.servlet.NWDL0260.constant.NWDL0260Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NWDL0260Scrn00_Cancel extends S21CommonHandler implements NWDL0260Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWDL0260BMsg scrnMsg = (NWDL0260BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NWDL0260_ABMsg lineMsg = scrnMsg.A.no(i);

            // Check Box
            if (Y.equals(lineMsg.xxChkBox_A1.getValue())) {
                return;
            }
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NWDL0260_ABMsg lineMsg = scrnMsg.A.no(i);

            // Check Box
            EZDBStringItem xxChkBoxItem = lineMsg.xxChkBox_A1;
            xxChkBoxItem.setErrorInfo(1, MsgId.ZZM9000E.name(), toArray(xxChkBoxItem.getNameForMessage()));
            scrnMsg.addCheckItem(xxChkBoxItem);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWDL0260BMsg scrnMsg = (NWDL0260BMsg) bMsg;

        return createCMsgForUpdate(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWDL0260BMsg scrnMsg = (NWDL0260BMsg) bMsg;
        NWDL0260CMsg bizMsg = (NWDL0260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // set GUI attributes.
        setGuiAttr(this, scrnMsg, ScrnId.Scrn00);
        
        // error judgement.
        if (hasError(bizMsg)) {
            return;
        } else {
            addCheckAllItems(scrnMsg, scrnMsg.getBaseContents());
            scrnMsg.putErrorScreen();
        }

        // set focus.
        scrnMsg.setFocusItem(scrnMsg.mdseCd);
    }

}
