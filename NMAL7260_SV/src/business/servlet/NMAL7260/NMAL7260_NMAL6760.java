/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7260.NMAL7260CMsg;
//import business.servlet.NMAL7260.constant.NMAL7260Constant;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_BILLTO;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_ACCTNUMCUSTSOLD;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2018/06/15   Fujitsu         M.Ishii         Update          N/A
 * 
 *</pre>
 */
public class NMAL7260_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // 2018/06/15 Add Start QC#22594
        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        String scrEvntNm = scrnMsg.xxScrEventNm.getValue();
        int idx = scrnMsg.xxCellIdx.getValueInt();
        if (OPENWIN_BILLTO.equals(scrEvntNm)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_16);
        } else if (OPENWIN_ACCTNUMCUSTSOLD.equals(scrEvntNm)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_54);
        }
        // 2018/06/15 Add End QC#22594
    }
}
