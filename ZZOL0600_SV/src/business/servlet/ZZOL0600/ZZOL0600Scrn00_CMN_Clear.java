/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0600;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0600.ZZOL0600CMsg;
import business.servlet.ZZOL0600.common.ZZOL0600CommonLogic;
import business.servlet.ZZOL0600.constant.ZZOL0600Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

public class ZZOL0600Scrn00_CMN_Clear extends S21CommonHandler implements ZZOL0600Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;

        ZZOL0600CMsg bizMsg = new ZZOL0600CMsg();
        bizMsg.setBusinessID("ZZOL0600");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        ZZOL0600BMsg scrnMsg = (ZZOL0600BMsg) bMsg;
        scrnMsg.clear();
        scrnMsg.A.setValidCount(0);

        S21TableColorController tblColor = new S21TableColorController(ZZOL0600Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        S21SortColumnIMGController.clearIMG("ZZOL0600Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.glblCmpyCd);
        ZZOL0600CommonLogic.initCommonButton(this);
        ZZOL0600CommonLogic.initPullDown00(scrnMsg);

        S21UserInfo userInfo = getContextUserInfo();
        scrnMsg.glblCmpyCd.setValue(userInfo.getUserCompanyCode());

    }

}
