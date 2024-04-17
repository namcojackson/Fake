/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0071;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZML0071.common.ZZML0071Scrn00CommonLogic;
import business.servlet.ZZML0071.constant.ZZML0071Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0071Scrn00_AddGroup extends S21CommonHandler implements ZZML0071Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.mlUsrId);
        scrnMsg.addCheckItem(scrnMsg.mlUsrAddr);
        scrnMsg.addCheckItem(scrnMsg.mlUsrNm);
        scrnMsg.addCheckItem(scrnMsg.mlUsrLocId);
        scrnMsg.addCheckItem(scrnMsg.mlUsrDescTxt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;

        int row = scrnMsg.A.getValidCount();
        if (row >= scrnMsg.A.length()) {
            scrnMsg.setMessageInfo("ZZZM9011E", new String[] { Integer.toString(scrnMsg.A.length())  });
            return;
        }

        scrnMsg.A.no(row).clear();
        scrnMsg.A.no(row).mlGrpId_A.setNameForMessage("Mail Group ID");
        scrnMsg.A.no(row).mlGrpId_A.setIndispensable(true);
//        scrnMsg.A.no(row).mlGrpId_A.setInputProtected(true);
        scrnMsg.A.no(row).mlGrpDescTxt_A.setInputProtected(true);
        scrnMsg.A.setValidCount(row + 1);
        this.setButtonEnabled("MailGroupLookup", row, true);
        ZZML0071Scrn00CommonLogic.setTabFocusRule( scrnMsg );

    }
}
