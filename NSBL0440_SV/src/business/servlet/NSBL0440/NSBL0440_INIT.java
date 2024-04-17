/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0440;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.servlet.NSBL0440.common.NSBL0440CommonLogic.*;
import static business.servlet.NSBL0440.constant.NSBL0440Constant.*;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0440.NSBL0440CMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;


/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Hitachi         O.Okuma         Create          N/A
 * 2016/04/18   Hitachi         M.Gotou         Create          QC#3425
 * 2016/07/12   Hitachi         O.Okuma         Create          QC#11685
 *</pre>
 */
public class NSBL0440_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0440BMsg scrnMsg = (NSBL0440BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();

        int index = 0;
        setValue(scrnMsg.svcModPk, (EZDBBigDecimalItem) params[index]);

        NSBL0440CMsg bizMsg = new NSBL0440CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0440BMsg scrnMsg = (NSBL0440BMsg) bMsg;
        NSBL0440CMsg bizMsg  = (NSBL0440CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.mdseCd_F);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSBL0440BMsg scrnMsg = (NSBL0440BMsg) bMsg;
        // mod start 2016/04/18 CSA Defect#3425
        scrnMsg.mdseCd_F.setNameForMessage("Mdse Code");
        scrnMsg.serNum_F.setNameForMessage("Serial#");
        // mod start 2016/04/18 CSA Defect#3425
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).svcModOptCd_A.setNameForMessage("Option");
            scrnMsg.A.no(i).svcModOptDt_A.setNameForMessage("Option Date");
            scrnMsg.A.no(i).svcModNoteTxt_A.setNameForMessage("Notes");
        }
    }
}
