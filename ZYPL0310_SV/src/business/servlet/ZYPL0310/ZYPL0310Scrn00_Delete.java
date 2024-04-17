/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZYPL0310;

import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZYPL0310.ZYPL0310CMsg;
import business.servlet.ZYPL0310.common.ZYPL0310CommonLogic;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class ZYPL0310Scrn00_Delete extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0310BMsg scrnMsg = (ZYPL0310BMsg) bMsg;

        // validate check-boxes.
        List selectedList = ZYPTableUtil.getSelectedRows(scrnMsg.A, ZYPL0310Bean.xxChkBox, ZYPConstant.CHKBOX_ON_Y);
        if (selectedList.isEmpty()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                EZDBItem checkBox = scrnMsg.A.no(i).xxChkBox;
                checkBox.setErrorInfo(1, "ZZM9000E", new String[] {checkBox.getNameForMessage() });
                scrnMsg.addCheckItem(checkBox);
            }
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return ZYPL0310CommonLogic.createCMsgForUpdate((ZYPL0310BMsg) bMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZYPL0310BMsg scrnMsg = (ZYPL0310BMsg) bMsg;
        ZYPL0310CMsg bizMsg = (ZYPL0310CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxAttDataCmntTxt_O.clear();

        ZYPL0310CommonLogic.setGUIComponentsProperties(this, getArgForSubScreen(), scrnMsg);

        ZYPL0310CommonLogic.setAlternateRowsBG(ZYPL0310Constant.MY_SCRN_ID, ZYPL0310Bean.A, scrnMsg);

        S21SortColumnIMGController.clearIMG(ZYPL0310Constant.MY_SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.xxFileData);
    }

}
