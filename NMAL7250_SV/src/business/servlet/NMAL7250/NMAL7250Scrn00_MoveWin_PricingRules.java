/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7250;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Fujitsu         W.Honda         Create          N/A
 * 2016/04/27   Fujitsu         W.Honda         Update          QC#5932
 *</pre>
 */
public class NMAL7250Scrn00_MoveWin_PricingRules extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // QC#6738 2016/04/27 Del start
        // do nothing.
//        NMAL7250BMsg scrnMsg = (NMAL7250BMsg) bMsg;
//
//        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, CHECK_BOX_NAME, ZYPConstant.CHKBOX_ON_Y);
//
//        if (selectedRows.size() > 1) {
//            for (int idx : selectedRows) {
//                scrnMsg.A.no(idx).xxChkBox_A1.setErrorInfo(1, NMAM8098E);
//                scrnMsg.addCheckItem(scrnMsg.A.no(idx).xxChkBox_A1);
//            }
//        }
//
//        scrnMsg.putErrorScreen();
        // QC#6738 2016/04/27 Del start
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // QC#6738 2016/04/27 Del start
//        NMAL7250BMsg scrnMsg = (NMAL7250BMsg) bMsg;

        Object[] param = new Object[1];
//        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, CHECK_BOX_NAME, ZYPConstant.CHKBOX_ON_Y);
//        if (selectedRows.size() == 1) {
//            param[0] = scrnMsg.A.no(selectedRows.get(0)).prcRuleHdrPk_A1;
//        }
        // QC#6738 2016/04/27 Del end

        setArgForSubScreen(param);
    }
}
