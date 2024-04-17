/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0090;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0090.common.NLCL0090CommonLogic;
import business.servlet.NLCL0090.constant.NLCL0090Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Item Change Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/03/2016   CSAI            Y.Imazu         Create          CSA
 *</pre>
 */
public class NLCL0090Scrn00_Del_Detail_To extends S21CommonHandler implements NLCL0090Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0090BMsg scrnMsg = (NLCL0090BMsg) bMsg;

        List<Integer> selectedRowsB = ZYPTableUtil.getSelectedRows(scrnMsg.B, CHKBOX_DT, ZYPConstant.CHKBOX_ON_Y);

        if (selectedRowsB.isEmpty()) {

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

                scrnMsg.B.no(i).xxChkBox_DT.setErrorInfo(1, NLBM0002E);
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0090BMsg scrnMsg = (NLCL0090BMsg) bMsg;
        NLCL0090CommonLogic.delTableTo(scrnMsg);

        NLCL0090CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.mdseCd_HT);
    }
}
