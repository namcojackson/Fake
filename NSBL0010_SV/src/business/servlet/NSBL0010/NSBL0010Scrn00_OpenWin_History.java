/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSBL0010.common.NSBL0010CommonLogic;
import business.servlet.NSBL0010.constant.NSBL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 * 2016/10/19   Hitachi         N.Arai          Update          QC#13901
 *</pre>
 */
public class NSBL0010Scrn00_OpenWin_History extends S21CommonHandler implements NSBL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

        NSBL0010CommonLogic.checkUniqSelect(scrnMsg);

        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            // Search do not set error message on fields.
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

        int size = scrnMsg.A.getValidCount();
        for (int i = 0; i < size; i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
// START 2016/10/19 N.Arai [QC#13901, MOD]
//                Object[] params = new Object[1];
                Object[] params = new Object[2];
                params[0] = scrnMsg.A.no(i).svcMachMstrPk_A;
                params[1] = scrnMsg.A.no(i).svcTaskNum_A;
// END 2016/10/19 N.Arai [QC#13901, MOD]

                setArgForSubScreen(params);
                return;
            }
        }
    }
}
