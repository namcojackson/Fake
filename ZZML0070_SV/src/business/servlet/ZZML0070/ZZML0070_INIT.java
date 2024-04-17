/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0070;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0070.ZZML0070CMsg;
import business.servlet.ZZML0070.common.ZZML0070Scrn00CommonLogic;
import business.servlet.ZZML0070.constant.ZZML0070Constant;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class ZZML0070_INIT extends S21CommonHandler implements ZZML0070Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

        ZZML0070BMsg scrnMsg = (ZZML0070BMsg) bMsg;

        scrnMsg.glblCmpyCd_S.setNameForMessage("Global Company CD");
        scrnMsg.mlGrpId_S.setNameForMessage("Mail Group ID");
        scrnMsg.mlKeyFirstCd_S.setNameForMessage("Mail Key First");
        scrnMsg.mlKeyScdCd_S.setNameForMessage("Mail Key Second");
        scrnMsg.mlKeyThirdCd_S.setNameForMessage("Mail Key Third");
        scrnMsg.mlUsrId_S.setNameForMessage("User ID");
        scrnMsg.mlUsrAddr_S.setNameForMessage("User Address");

        if (getArgForSubScreen() == null) { return; }

        Object[] args = (Object[]) getArgForSubScreen();
        scrnMsg.glblCmpyCd_S.setValue(args[0].toString());
        scrnMsg.mlGrpId_S.setValue(args[1].toString());
        scrnMsg.mlGrpId_H.setValue(scrnMsg.mlGrpId_S.getValue());
        if (args[2] != null) {
            this.setButtonEnabled("Group", false);
        }

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd_S);
        scrnMsg.addCheckItem(scrnMsg.mlGrpId_S);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if (getArgForSubScreen() == null) { return null; }

        ZZML0070BMsg scrnMsg = (ZZML0070BMsg) bMsg;
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        ZZML0070CMsg bizMsg = new ZZML0070CMsg();
        bizMsg.setBusinessID("ZZML0070");
        bizMsg.setFunctionCode("20");

        bizMsg.glblCmpyCd_S.setValue(scrnMsg.glblCmpyCd_S.getValue());
        bizMsg.mlGrpId_S.setValue(scrnMsg.mlGrpId_S.getValue());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0070BMsg scrnMsg = (ZZML0070BMsg) bMsg;
        ZZML0070CMsg bizMsg  = (ZZML0070CMsg) cMsg;

        ZZML0070Scrn00CommonLogic.setButtonPropertiesInit(this);

        if (getArgForSubScreen() == null) {
            String gcc = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
            if (S21StringUtil.isNotEmpty(gcc)) {
                scrnMsg.glblCmpyCd_S.setValue(gcc);
            }
        } else {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            if ("E".equals(bizMsg.getMessageKind())) {
                ZZML0070Scrn00CommonLogic.setButtonPropertiesSearchNotFound(this);
            } else {
                ZZML0070Scrn00CommonLogic.setButtonPropertiesSearchFound(this);
            }
        }
        for (int i=0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mlUsrNm_A.setInputProtected(true);
            scrnMsg.A.no(i).mlUsrAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).mlUsrDescTxt_A.setInputProtected(true);
        }

        scrnMsg.xxScrNm_S.setValue("ZZML0070Scrn00");

        scrnMsg.setFocusItem(scrnMsg.glblCmpyCd_S);
    }
}
