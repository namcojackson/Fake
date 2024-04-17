/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import static business.servlet.NYEL8810.constant.NYEL8810Constant.BIZ_ID;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg; 
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8810.NYEL8810CMsg;
import business.servlet.NYEL8810.common.NYEL8810CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8810Scrn00_CMN_Approve
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/29   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8810Scrn00_CMN_Approve extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        NYEL8810CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.wfCmntTxt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;

        NYEL8810CMsg bizMsg = new NYEL8810CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        NYEL8810CMsg bizMsg = (NYEL8810CMsg) cMsg;

        if (!MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            ZYPTableUtil.clear(scrnMsg.A);
        }
        
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxWfAprChkFlg_A);
        }

        NYEL8810CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
//            throw new EZDFieldErrorException();
            return;
        }

        NYEL8810CommonLogic.initCmnBtnProp(this, scrnMsg);
        NYEL8810CommonLogic.initRowCtrlProp(this, scrnMsg);
        NYEL8810CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        scrnMsg.wfCmntTxt.clear();
    }
}
