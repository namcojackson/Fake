/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0320;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Hitachi         T.Tomita        Create          N/A
 * 2016/05/20   Hitachi         T.Tomita        Update          QC#5472
 * 2016/07/11   Hitachi         M.Gotou         Update          QC#11521
 * 2016/07/12   Hitachi         M.Gotou         Update          QC#11527
 *</pre>
 */
public class NSAL0320Scrn00_OnClick_BillableFlag extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0320BMsg scrnMsg = (NSAL0320BMsg) bMsg;
        int rowNum = getButtonSelectNumber();
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(rowNum).bllblFlg.getValue())) {
            scrnMsg.A.no(rowNum).contrMtrMultRate.setInputProtected(false);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.actvFlg.getValue())) {
                scrnMsg.A.no(rowNum).bllgMtrLbCd.setInputProtected(false);
            } else {
                scrnMsg.A.no(rowNum).bllgMtrLbCd.setInputProtected(true);
            }
        } else {
            scrnMsg.A.no(rowNum).contrMtrMultRate.setInputProtected(true);
            scrnMsg.A.no(rowNum).bllgMtrLbCd.setInputProtected(true);
            // add start 2016/05/20 CSA Defect#5472
            scrnMsg.A.no(rowNum).contrMtrMultRate.clear();
            scrnMsg.A.no(rowNum).bllgMtrLbCd.clear();
            // add end 2016/05/20 CSA Defect#5472
        }
        // START 2016/07/11 M.Gotou [QC#11521, ADD]
        scrnMsg.A.no(rowNum).bllgMtrLbCd_PR.clear();
        // END 2016/07/11 M.Gotou [QC#11521, ADD]
        // START 2016/07/12 M.Gotou [QC#11527, ADD]
        scrnMsg.setFocusItem(scrnMsg.A.no(rowNum).bllblFlg);
        // END 2016/07/12 M.Gotou [QC#11527, ADD]
    }
}

