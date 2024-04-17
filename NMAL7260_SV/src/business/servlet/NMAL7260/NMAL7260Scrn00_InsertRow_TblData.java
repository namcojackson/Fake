/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import static business.servlet.NMAL7260.constant.NMAL7260Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7260.NMAL7260CMsg;
import business.servlet.NMAL7260.common.NMAL7260CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/10/20   Fujitsu         R.Nakamura      Update          QC#14936
 * 2016/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 * 2018/06/15   Fujitsu         M.Ishii         Update          QC#22594
 *</pre>
 */
public class NMAL7260Scrn00_InsertRow_TblData extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Add Start 2016/10/24 QC#14936
        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        if (scrnMsg.B.getValidCount() >= scrnMsg.B.length()) {
            NMAL7260CommonLogic.detailCheck(scrnMsg);
        }

        scrnMsg.putErrorScreen();
        // Add Start 2016/10/24 QC#14936
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

        NMAL7260CMsg bizMsg = new NMAL7260CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        NMAL7260CMsg bizMsg  = (NMAL7260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Mod Start 2017/02/27 QC#16011
//        NMAL7260CommonLogic.initCmnBtnProp(this, scrnMsg);
//        NMAL7260CommonLogic.setBtnProp(this, scrnMsg);
        // Add Start 2016/10/20 QC#14936
//        NMAL7260CommonLogic.scrnProtect(scrnMsg);
        NMAL7260CommonLogic.initCmnBtnProp(this, scrnMsg, getUserProfileService());
        NMAL7260CommonLogic.setBtnProp(this, scrnMsg, getUserProfileService());
        NMAL7260CommonLogic.scrnProtect(scrnMsg, getUserProfileService());
        // Mod End 2017/02/27 QC#16011
        NMAL7260CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
        // Add End 2016/10/20 QC#14936
        // 2018/06/15 QC#22594 Add Start
        int idx = scrnMsg.B.getValidCount() - 1;
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).xxChkBox_B1);
        // 2018/06/15 QC#22594 Add End
    }
}
