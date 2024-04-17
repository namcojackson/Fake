/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0730;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NFCL0730.constant.NFCL0730Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/11/10   Hitachi         S.Naya          Create          QC#57252
 *</pre>
 */
public class NFCL0730Scrn00_Onchange_PD_Activity extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0730BMsg scrnMsg = (NFCL0730BMsg) bMsg;

        setDisplay(scrnMsg);
    }

    private void setDisplay(NFCL0730BMsg scrnMsg) {
        if (NFCL0730Constant.OTHER_CODE.equals(scrnMsg.arAdjTpCd_H1.getValue())) {
            scrnMsg.xxCmntTxt_H1.setInputProtected(false);
            setButtonEnabled(NFCL0730Constant.BTN_A, true);
        } else {
            scrnMsg.xxCmntTxt_H1.setInputProtected(true);
            setButtonEnabled(NFCL0730Constant.BTN_A, false);
        }
    }
}
