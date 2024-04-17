/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0050;

import static business.servlet.NWCL0050.constant.NWCL0050Constant.BIZ_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0050.NWCL0050CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWCL0050Scrn00_OpenWin_ManualInv
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/19   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWCL0050Scrn00_OpenWin_ManualInv extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;

        NWCL0050CMsg bizMsg = new NWCL0050CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;
        NWCL0050CMsg bizMsg = (NWCL0050CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR || scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) {
            scrnMsg.A.setCheckParam(new String[] {NWCL0050Bean.xxChkBox_A }, 1);
            scrnMsg.addCheckItem(scrnMsg.A);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }
        
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        Object[] params = new Object[1];
        params[0] = scrnMsg.A.no(selectedRows.get(0)).invNum_A;


        setArgForSubScreen(params);

    }
}
