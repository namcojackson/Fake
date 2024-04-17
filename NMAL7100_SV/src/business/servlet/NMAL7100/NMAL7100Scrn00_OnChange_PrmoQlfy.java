/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7100.common.NMAL7100CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/12   Fujitsu         T.Murai         Create          #4042
 * 2020/01/08   Fujitsu         C.Hara          Update          QC#55221
 *</pre>
 */
public class NMAL7100Scrn00_OnChange_PrmoQlfy extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();

        scrnMsg.A.no(selectIdx).prcQlfyValTxt_DA.clear();

        NMAL7100CommonLogic.promQifyCatgBtnCtrl(selectIdx, scrnMsg.A.no(selectIdx).prcPrmoQlfyTpCd_DA.getValue(), scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).prcQlfyValTxt_DA); // 2020/01/08 QC#55221 Add
    }
}
