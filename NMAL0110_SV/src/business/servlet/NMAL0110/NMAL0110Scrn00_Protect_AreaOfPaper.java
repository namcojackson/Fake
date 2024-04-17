/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.EASY_PACK_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/29/2017   Fujitsu         K.Ishizuka      Update          QC#17149(Sol#259)
 *</pre>
 */
public class NMAL0110Scrn00_Protect_AreaOfPaper extends S21CommonHandler {

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

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.easyPackTpCd_H1)) {
            if (EASY_PACK_TP.EASYPAC_TONER.equals(scrnMsg.easyPackTpCd_H1.getValue())) {
                scrnMsg.areaOfPaperNum_H1.setInputProtected(true);
                scrnMsg.areaOfPaperNum_H1.clear();
            } else {
                scrnMsg.areaOfPaperNum_H1.setInputProtected(false);
            }
        }

    }
}
