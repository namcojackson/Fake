/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0110;

import static business.servlet.NWCL0110.constant.NWCL0110Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0110.NWCL0110CMsg;
import business.servlet.NWCL0110.common.NWCL0110CommonLogic;
import business.servlet.NWCL0110.constant.NWCL0110Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/24   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
public class NWCL0110Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0110BMsg scrnMsg = (NWCL0110BMsg) bMsg;
        NWCL0110CMsg bizMsg = new NWCL0110CMsg();

        bizMsg.setBusinessID(NWCL0110Constant.BIZ_ID);
        bizMsg.setFunctionCode(NWCL0110Constant.FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0110BMsg scrnMsg = (NWCL0110BMsg) bMsg;
        NWCL0110CMsg bizMsg = (NWCL0110CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWCL0110CommonLogic.controlDtl(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxTpCd);

        // clear sort icons
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
