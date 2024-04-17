package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.BIZ_ID;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.SCRN_ID_00;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL7190.NMAL7190CMsg;
import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * NMAL7190_NMAL7440
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/12/05   Fujitsu         T.Noguchi       Create          QC#29324
 *</pre>
 */
public class NMAL7190_NMAL7440 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        NMAL7190CMsg bizMsg = new NMAL7190CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        NMAL7190CMsg bizMsg  = (NMAL7190CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7190CommonLogic.initControlScreen(this, scrnMsg);
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL7190CommonLogic.controlScreen(this, scrnMsg);
        NMAL7190CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.prcGrpNm);
        } else {
            scrnMsg.setFocusItem(scrnMsg.prcGrpPk);
        }
    }
}
