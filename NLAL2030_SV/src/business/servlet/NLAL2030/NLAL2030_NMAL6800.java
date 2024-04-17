/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2030_NMAL6800
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 * 06/09/2016   CSAI            Y.Imazu         Update          QC#7977
 *</pre>
 */
public class NLAL2030_NMAL6800 extends S21CommonHandler {

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

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        if (!NLAL2030Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (NLAL2030Constant.EVENT_NM_OPENWIN_MDSE.equals(scrnMsg.xxMntEventNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, scrnMsg.P.no(0).xxPopPrm);

            } else if (NLAL2030Constant.EVENT_NM_OPENWIN_FLIP_MDSE.equals(scrnMsg.xxMntEventNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.flipMdseCd, scrnMsg.P.no(0).xxPopPrm);
            }
        }

        if (NLAL2030Constant.EVENT_NM_OPENWIN_MDSE.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.mdseCd);

        } else if (NLAL2030Constant.EVENT_NM_OPENWIN_FLIP_MDSE.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.flipMdseCd);

        } else {

            scrnMsg.setFocusItem(scrnMsg.trxOrdNum);
        }
    }
}
