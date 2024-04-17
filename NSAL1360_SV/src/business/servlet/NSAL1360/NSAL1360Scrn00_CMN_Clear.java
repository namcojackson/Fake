/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1360;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL1360.common.NSAL1360CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1360Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/06   Hitachi         Y.Osawa         Create          N/A
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#20162
 *</pre>
 */
public class NSAL1360Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1360BMsg scrnMsg = (NSAL1360BMsg) bMsg;

        scrnMsg.mtrReadMethCd.clear();
        scrnMsg.custIssPoNum.clear();
        scrnMsg.custIssPoDt.clear();
        // 2018/04/16 QC#20162 Add Start
        scrnMsg.dsPoExprDt.clear();
        // 2018/04/16 QC#20162 Add End

        NSAL1360CommonLogic.initCmnBtnProp(this);
        scrnMsg.setFocusItem(scrnMsg.mtrReadMethCd);
    }
}
