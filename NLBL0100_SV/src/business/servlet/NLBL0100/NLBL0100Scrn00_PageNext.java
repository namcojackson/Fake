/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.NLBL0100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL0100.NLBL0100CMsg;
import business.servlet.NLBL0100.common.NLBL0100CommonLogic;
import business.servlet.NLBL0100.constant.NLBL0100Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NLBL0100Scrn00_PageNext extends S21CommonHandler implements NLBL0100Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0100BMsg scrnMsg = (NLBL0100BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowToNum.getValueInt());
        scrnMsg.xxPageShowToNum.clear();

        NLBL0100CMsg bizMsg = new NLBL0100CMsg();
        bizMsg.setBusinessID("NLBL0100");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0100BMsg scrnMsg = (NLBL0100BMsg) bMsg;
        NLBL0100CMsg bizMsg = (NLBL0100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Input Protect
        NLBL0100CommonLogic.setInputProtected(scrnMsg); // 10/14/2015 add
    }

}
