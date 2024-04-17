/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1360;

import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1360Scrn00_CMN_Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/06   Hitachi         Y.Osawa         Create          N/A
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#20162
 *</pre>
 */
public class NSAL1360Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1360BMsg scrnMsg = (NSAL1360BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.mtrReadMethCd);
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum);
        scrnMsg.addCheckItem(scrnMsg.custIssPoDt);
        // 2018/04/16 QC#20162 Add Start
        scrnMsg.addCheckItem(scrnMsg.dsPoExprDt);
        // 2018/04/16 QC#20162 Add End
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1360BMsg scrnMsg = (NSAL1360BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.mtrReadMethCd_P, scrnMsg.mtrReadMethCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.custIssPoNum_P, scrnMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.custIssPoDt_P, scrnMsg.custIssPoDt);
        // 2018/04/16 QC#20162 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsPoExprDt_P, scrnMsg.dsPoExprDt);
        // 2018/04/16 QC#20162 Add End

        int ixP = 0;
        Object[] arg = (Object[]) getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            // Set Parameter
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], scrnMsg.mtrReadMethCd_P);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], scrnMsg.custIssPoNum_P);
            ZYPEZDItemValueSetter.setValue((EZDBDateItem) params[ixP++], scrnMsg.custIssPoDt_P);
            // 2018/04/16 QC#20162 Add Start
            ZYPEZDItemValueSetter.setValue((EZDBDateItem) params[ixP++], scrnMsg.dsPoExprDt_P);
            // 2018/04/16 QC#20162 Add End
        }
    }
}
