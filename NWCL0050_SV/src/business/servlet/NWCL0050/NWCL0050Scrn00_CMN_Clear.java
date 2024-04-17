/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0050;

import static business.servlet.NWCL0050.constant.NWCL0050Constant.BIZ_ID;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_DISPLAY;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_EMAIL;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_PAY;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0050.NWCL0050CMsg;
import business.servlet.NWCL0050.common.NWCL0050CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/26   Fujitsu         T.Yoshida       Create          N/A
 * 2017/03/08   Fujitsu         K.Ishizuka      Update          QC#13856
 * 2018/07/11   Fujitsu         M.Ohno          Update          QC#19801
 *</pre>
 */
public class NWCL0050Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // ADD START S21_NA QC#13856
        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;
        scrnMsg.xxDplyCtrlFlg_EM.clear();

        NWCL0050CMsg bizMsg = new NWCL0050CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // ADD END S21_NA QC#13856
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        
        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;
        NWCL0050CMsg bizMsg = (NWCL0050CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 218/07/11 QC#19801 add start
        setButtonEnabled(BTN_DISPLAY, false);
        // 218/07/11 QC#19801 add end
        setButtonEnabled(BTN_PAY, false);
        setButtonEnabled(BTN_EMAIL, false);
        scrnMsg.setFocusItem(scrnMsg.invNum);

        // clear sort icons
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
