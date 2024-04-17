/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/06   SRAA            Y.Chen          Create          QC#6184
 *</pre>
 */
public class NMAL6700Scrn00_OnChange_CustBllgTp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        int index = this.getButtonSelectNumber();
        if (CUST_BLLG_TP.CONSOLIDATED.equals(scrnMsg.G.no(index).custBllgTpCd_G3.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.G.no(index).invGrpNum_G1)) {
                scrnMsg.G.no(index).invGrpNum_G1.setValue(NMAL6700Constant.DEF_INV_GRP_NUM);
            }
        }
        scrnMsg.setFocusItem(scrnMsg.G.no(index).custBllgTpCd_G3);
    }
}
