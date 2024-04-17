/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7050;

import static business.servlet.NMAL7050.constant.NMAL7050Constant.NMAM8090W;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   CUSA            W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7050_NMAL6870 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7050BMsg scrnMsg = (NMAL7050BMsg) bMsg;
        StringBuilder mdl = new StringBuilder();
        for (int i = 0; i < scrnMsg.P.getValidCount(); i++) {
            if (i != 0) {
                mdl.append(",");
            }
            mdl.append(scrnMsg.P.no(i).xxComnScrColValTxt_0.getValue());
        }

        if (mdl.length() <= scrnMsg.getAttr(NMAL7050Bean.xxDsMultMsgDplyTxt).getDigit()) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsMultMsgDplyTxt, mdl.toString());
        } else {
            scrnMsg.setMessageInfo(NMAM8090W, new String[] {scrnMsg.xxDsMultMsgDplyTxt.getNameForMessage()});
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsMultMsgDplyTxt, mdl.toString().substring(0, scrnMsg.getAttr(NMAL7050Bean.xxDsMultMsgDplyTxt).getDigit()));
        }

        scrnMsg.setFocusItem(scrnMsg.xxDsMultMsgDplyTxt);
    }
}
