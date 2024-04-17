/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0060.ZZML0060CMsg;
import business.servlet.ZZML0060.common.ZZML0060Scrn00CommonLogic;
import business.servlet.ZZML0060.constant.ZZML0060Constant;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * @author q02673
 */
public class ZZML0060_INIT extends S21CommonHandler implements ZZML0060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

        ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;
        scrnMsg.glblCmpyCd_S.setNameForMessage("Global Company CD");
        scrnMsg.mlGrpId_S.setNameForMessage("Mail Group ID");
        scrnMsg.mlGrpNm_S.setNameForMessage("Mail Group Name");

        if (getArgForSubScreen() == null) { return; }

        Object[] args = (Object[]) getArgForSubScreen();
        scrnMsg.glblCmpyCd_S.setValue(args[0].toString());
        scrnMsg.mlGrpId_S.setValue(args[1].toString());
        if (args[2] != null) {
            this.setButtonEnabled("User", false);
        }

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd_S);
        scrnMsg.addCheckItem(scrnMsg.mlGrpId_S);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if (getArgForSubScreen() == null) { return null; }

        ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        ZZML0060CMsg bizMsg = new ZZML0060CMsg();
        bizMsg.setBusinessID("ZZML0060");
        bizMsg.setFunctionCode("20");

        bizMsg.glblCmpyCd_S.setValue(scrnMsg.glblCmpyCd_S.getValue());
        bizMsg.mlGrpId_S.setValue(scrnMsg.mlGrpId_S.getValue());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;
        ZZML0060CMsg bizMsg  = (ZZML0060CMsg) cMsg;

        ZZML0060Scrn00CommonLogic.setButtonPropertiesInit(this);

        if (getArgForSubScreen() == null) {
            String gcc = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
            if (S21StringUtil.isNotEmpty(gcc)) {
                scrnMsg.glblCmpyCd_S.setValue(gcc);
            }
        } else {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            if ("E".equals(bizMsg.getMessageKind())) {
                ZZML0060Scrn00CommonLogic.setButtonPropertiesSearchNotFound(this);
            } else {
                ZZML0060Scrn00CommonLogic.setButtonPropertiesSearchFound(this);
            }
        }

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            ZZML0060_ABMsg childMsg = (ZZML0060_ABMsg) scrnMsg.A.get(i);
            childMsg.mlGrpNm_A.setInputProtected(true);
            childMsg.mlKeyFirstNm_A.setInputProtected(true);
            childMsg.mlKeyScdNm_A.setInputProtected(true);
            childMsg.mlKeyThirdNm_A.setInputProtected(true);
            childMsg.mlGrpDescTxt_A.setInputProtected(true);
        }

        scrnMsg.xxScrNm_S.setValue("ZZML0060Scrn00");

        scrnMsg.setFocusItem(scrnMsg.glblCmpyCd_S);
    }
}
