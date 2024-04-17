/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLCL0090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0090.NLCL0090CMsg;
import business.servlet.NLCL0090.common.NLCL0090CommonLogic;
import business.servlet.NLCL0090.constant.NLCL0090Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Item Change Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/30/2009   Fujitsu         FXS)KF.Qian     Create          N/A
 * 03/03/2016   CSAI            Y.Imazu         Update          CSA
 *</pre>
 */
public class NLCL0090Scrn00_CMN_Reset extends S21CommonHandler implements NLCL0090Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0090BMsg scrnMsg = (NLCL0090BMsg) bMsg;
        NLCL0090CMsg bizMsg = new NLCL0090CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0090CMsg bizMsg = (NLCL0090CMsg) cMsg;
        NLCL0090BMsg scrnMsg = (NLCL0090BMsg) bMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL0090CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            NLCL0090CommonLogic.initScrnControlErr(this, scrnMsg);
        }

        scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
    }
}
