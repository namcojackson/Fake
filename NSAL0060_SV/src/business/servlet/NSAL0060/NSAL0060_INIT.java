/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0060;

import static business.servlet.NSAL0060.constant.NSAL0060Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0060.common.NSAL0060CommonLogic;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 *
 * Model Group Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/08/2013   Hitachi         Y.Igarashi      Create          N/A
 * 08/29/2013   Hitachi         T.Aoyagi        Update          QC1955
 * 2018/06/18   Fujitsu         T.Ogura         Update          QC#24512
 *</pre>
 */
public class NSAL0060_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);

        S21UserProfileService userProfileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        if (functionIds == null || functionIds.isEmpty()) {
            throw new S21AbendException("You can't operate Model Group Maintenance(NSAL0060). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START 2018/06/18 T.Ogura [QC#24512,MOD]
//        NSAL0060BMsg scrnMsg = (NSAL0060BMsg) bMsg;
//
//        NSAL0060CMsg bizMsg = new NSAL0060CMsg();
//        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
        return null;
        // END   2018/06/18 T.Ogura [QC#24512,MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0060BMsg scrnMsg = (NSAL0060BMsg) bMsg;
        // START 2018/06/18 T.Ogura [QC#24512,DEL]
//        NSAL0060CMsg bizMsg  = (NSAL0060CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // END   2018/06/18 T.Ogura [QC#24512,DEL]

        initialize();
        NSAL0060CommonLogic.setProtected(scrnMsg, this);
        NSAL0060CommonLogic.checkAuth(scrnMsg, this);
        // START 2013/08/29 T.Aoyagi [QC1955,MOD]
        // scrnMsg.setFocusItem(scrnMsg.mdlGrpNm_SC);
        NSAL0060CommonLogic.setBgColor(scrnMsg);

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_SR);
        } else {
            scrnMsg.setFocusItem(scrnMsg.mdlGrpNm_SC);
        }
        // END 2013/08/29 T.Aoyagi [QC1955,MOD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0060BMsg scrnMsg = (NSAL0060BMsg) bMsg;
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());
        scrnMsg.mdlGrpNm_SC.setNameForMessage("Model Group");
        scrnMsg.mdlGrpDescTxt_SC.setNameForMessage("Description");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mdlGrpNm_SR.setNameForMessage("Model Group");
            scrnMsg.A.no(i).mdlGrpDescTxt_SR.setNameForMessage("Description");
        }
    }

    private void initialize() {
        // Control
        setButtonProperties(BUTTON_NAME_SAVE, BUTTON_GUARD_SAVE, BUTTON_LABEL_SAVE, 0, null);
        setButtonProperties(BUTTON_NAME_SUBMIT, BUTTON_GUARD_SUBMIT, BUTTON_LABEL_SUBMIT, 1, null);
        setButtonProperties(BUTTON_NAME_APPLY, BUTTON_GUARD_APPLY, BUTTON_LABEL_APPLY, 0, null);
        setButtonProperties(BUTTON_NAME_APPROVE, BUTTON_GUARD_APPROVE, BUTTON_LABEL_APPROVE, 0, null);
        setButtonProperties(BUTTON_NAME_REJECT, BUTTON_GUARD_REJECT, BUTTON_LABEL_REJECT, 0, null);
        setButtonProperties(BUTTON_NAME_DOWNLOAD, BUTTON_GUARD_DOWNLOAD, BUTTON_LABEL_DOWNLOAD, 0, null);
        setButtonProperties(BUTTON_NAME_DELETE, BUTTON_GUARD_DELETE, BUTTON_LABEL_DELETE, 0, null);
        setButtonProperties(BUTTON_NAME_CLEAR, BUTTON_GUARD_CLEAR, BUTTON_LABEL_CLEAR, 1, null);
        setButtonProperties(BUTTON_NAME_RESET, BUTTON_GUARD_RESET, BUTTON_LABEL_RESET, 0, null);
        setButtonProperties(BUTTON_NAME_RETURN, BUTTON_GUARD_RETURN, BUTTON_LABEL_RETURN, 1, null);
    }

}
