/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2600;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2600.common.NMAL2600CommonLogic;
import business.servlet.NMAL2600.constant.NMAL2600Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/03   Fujitsu         C.Yokoi         Update          CSA-QC#4539
 *</pre>
 */
public class NMAL2600Scrn00_DeleteRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;
        // 2016/03/03 CSA-QC#4539 Add Start
        NMAL2600CommonLogic.addCheckItems(scrnMsg);
        // 2016/03/03 CSA-QC#4539 Add End

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        if (selectedRows == null || selectedRows.isEmpty()) {
            scrnMsg.setMessageInfo(NMAL2600Constant.NMAM8054E);
            return;
        }

        ZYPTableUtil.deleteRows(scrnMsg.A, selectedRows);

        S21SortColumnIMGController.clearIMG(NMAL2600Constant.SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        if (scrnMsg.A.getValidCount() < scrnMsg.A.length()) {
            setButtonEnabled(NMAL2600Constant.BTN_INSERT, true);
        }
        if (scrnMsg.A.getValidCount() <= 0) {
            setButtonEnabled(NMAL2600Constant.BTN_DELETE, false);
        }

    }
}
