/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0010;

import parts.common.*;
import parts.servletcommon.*;
// import business.blap.ZZOL0010.ZZOL0010CMsg;

import business.servlet.ZZOL0010.common.ZZOL0010CommonLogic;
import business.servlet.ZZOL0010.constant.ZZOL0010Constant;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

public class ZZOL0010_INIT extends S21INITCommonHandler implements ZZOL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), aplID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {


        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;

        // Table Color Controll
        S21TableColorController tblColor = new S21TableColorController(ZZOL0010Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        // Button Controll
        ZZOL0010CommonLogic.initCommonButton(this);

        // PullDown
        ZZOL0010CommonLogic.initPullDown00(scrnMsg);
        ZZOL0010CommonLogic.initPullDown01(scrnMsg);

        //scrnMsg.ezCompanyCode.setInputProtected(true);
        
        // Focus Controll
        scrnMsg.setFocusItem(scrnMsg.ezBusinessID_00);

        S21UserInfo userInfo = getContextUserInfo();
        scrnMsg.ezCompanyCode.setValue(userInfo.getUserCompanyCode());
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) arg0;
        scrnMsg.ezBusinessID_00.setNameForMessage("Business Application ID");
        scrnMsg.ezCompanyCode.setNameForMessage("User Company Code");
        scrnMsg.ezAcctInfoMode_SV.setNameForMessage("Online Event Process Log");
        scrnMsg.ezOnlStopFlag_SV.setNameForMessage("Online Stop Flag");
        scrnMsg.ezDebugLevel_SV.setNameForMessage("Debug Log");
        scrnMsg.ezBusinessID_01.setNameForMessage("Business Application ID");
        scrnMsg.ezBusinessID_SB.setNameForMessage("Business Application ID");
        scrnMsg.ezCompanyCode_01.setNameForMessage("User Company Code");
    }

}
