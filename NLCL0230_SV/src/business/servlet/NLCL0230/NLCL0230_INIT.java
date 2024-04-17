/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/05   Fujitsu         S.Yoshida       Create          N/A
 * 2009/10/29   Fujitsu         S.Yoshida       Update          1306
 *</pre>
 */
package business.servlet.NLCL0230;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0230.NLCL0230CMsg;
import business.servlet.NLCL0230.common.NLCL0230CommonLogic;
import business.servlet.NLCL0230.constant.NLCL0230Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

public class NLCL0230_INIT extends S21INITCommonHandler implements NLCL0230Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Get the transition parameters.
        NLCL0230BMsg scrnMsg = (NLCL0230BMsg) bMsg;
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param1 = (EZDBStringItem) params[0];
            EZDBStringItem param2 = (EZDBStringItem) params[1];
            EZDBStringItem param3 = (EZDBStringItem) params[2];
            scrnMsg.invtyLocTpCd_P1.setValue(param1.getValue());
            scrnMsg.invtyLocCd.setValue(param2.getValue());
            scrnMsg.invtyLocNm.setValue(param3.getValue());
            if (params.length > 3) {
                EZDBStringItem param4 = (EZDBStringItem) params[3];
                if (ZYPCommonFunc.hasValue(param4)) {
                    scrnMsg.mdseCd.setValue(param4.getValue());
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.invtyLocTpCd_P1)) {
            scrnMsg.invtyLocTpCd_P1.setValue("--");
        }

        // Get the Global Company Code.
        S21UserProfileService ups = getUserProfileService();
        String glblCmpyCd = ups.getGlobalCompanyCode();
        scrnMsg.glblCmpyCd.setValue(glblCmpyCd);

        // Call Blap.
        NLCL0230CMsg bizMsg = new NLCL0230CMsg();
        bizMsg.setBusinessID("NLCL0230");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // Copy bizMsg to scrnMsg
        NLCL0230BMsg scrnMsg = (NLCL0230BMsg) bMsg;
        NLCL0230CMsg bizMsg  = (NLCL0230CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Focus the Inventory Location Code.
        scrnMsg.setFocusItem(scrnMsg.invtyLocCd);

        // Initialization button.
        NLCL0230CommonLogic.initCommonButton(this);
        NLCL0230CommonLogic.initButton(this);

        // Set button confirm message.
        setButtonConfirmMsg(BTN_CMN_BTN_8[1], "ZZM8102I", new String[]{BTN_CMN_BTN_8[2]}, 0);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
    }
}
