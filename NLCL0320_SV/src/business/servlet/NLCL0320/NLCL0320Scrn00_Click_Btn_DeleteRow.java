/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0320;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0320.NLCL0320CMsg;
import business.servlet.NLCL0320.common.NLCL0320CommonLogic;
import business.servlet.NLCL0320.constant.NLCL0320Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NLCL0320Scrn00_Click_Btn_DeleteRow extends S21CommonHandler implements NLCL0320Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0320BMsg scrnMsg = (NLCL0320BMsg) bMsg;

        NLCL0320CMsg bizMsg = new NLCL0320CMsg();
        bizMsg.setBusinessID("NLCL0320");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0320BMsg scrnMsg = (NLCL0320BMsg) bMsg;
        NLCL0320CMsg bizMsg = (NLCL0320CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NLCL0320CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());

        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[] {NLCL0320Bean.xxChkBox_A }, 1);
        scrnMsg.putErrorScreen();

        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
        scrnMsg.setMessageInfo(MESSAGE_ID.NZZM0002I.toString());

    }

}
