/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0060;

import static business.servlet.NWWL0060.constant.NWWL0060Constant.BIZ_ID;
import static business.servlet.NWWL0060.constant.NWWL0060Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0060.NWWL0060CMsg;
import business.servlet.NWWL0060.common.NWWL0060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWWL0060_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/05   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0060_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0060BMsg scrnMsg = (NWWL0060BMsg) bMsg;
        NWWL0060CMsg bizMsg = new NWWL0060CMsg();

        ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd, getContextUserInfo().getUserId());

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0060BMsg scrnMsg = (NWWL0060BMsg) bMsg;
        NWWL0060CMsg bizMsg = (NWWL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWWL0060CommonLogic.initCmnBtnProp(this);
        NWWL0060CommonLogic.controlScreenFields(scrnMsg);
        NWWL0060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        scrnMsg.setFocusItem(scrnMsg.A.no(0).ntfySbscrFlg_A);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWWL0060BMsg scrnMsg = (NWWL0060BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).ntfySbscrFlg_A.setNameForMessage("Sbscr");
        }
    }
}
