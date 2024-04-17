/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.NLBL0110;


import parts.common.EZDBItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL0110.NLBL0110CMsg;
import business.servlet.NLBL0110.common.NLBL0110CommonLogic;
import business.servlet.NLBL0110.constant.NLBL0110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * When Event[View] in BusinessID NLBL0110 is generated, this class processes it. 
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/02/05   Fujitsu         S.Uehara        Create          N/A
 * </pre>
 */
public class NLBL0110Scrn00_View extends S21CommonHandler implements NLBL0110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0110BMsg scrnMsg = (NLBL0110BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn)) {

            EZDBItem radioButton = scrnMsg.xxRadioBtn;
            radioButton.setErrorInfo(1, NLBM0017E);
            scrnMsg.addCheckItem(radioButton);
        } else {

            int selectedRow = scrnMsg.xxRadioBtn.getValue().intValue();
            scrnMsg.addCheckItem(scrnMsg.A.no(selectedRow).endMthBizDaysAot_A1);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0110BMsg scrnMsg = (NLBL0110BMsg) bMsg;

        NLBL0110CMsg bizMsg = new NLBL0110CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0110BMsg scrnMsg = (NLBL0110BMsg) bMsg;
        NLBL0110CMsg bizMsg  = (NLBL0110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL0110CommonLogic.cntrlDispScrnItem(this, scrnMsg);
        NLBL0110CommonLogic.setTblBackColor(scrnMsg);
    }

}
