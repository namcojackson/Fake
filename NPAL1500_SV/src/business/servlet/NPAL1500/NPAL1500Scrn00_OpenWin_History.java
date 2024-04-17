/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.NPAM1215E;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.NPAM1268E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          n/a
 *</pre>
 */
public class NPAL1500Scrn00_OpenWin_History extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.poHdrStsCd)) {
            scrnMsg.setMessageInfo(NPAM1268E);
            throw new EZDFieldErrorException();
        }

        boolean isChked = false;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                if (isChked) {
                    scrnMsg.setMessageInfo(NPAM1215E);
                    throw new EZDFieldErrorException();
                }
                isChked = true;
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        boolean isChked = false;
        int selectIndex = -1;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                isChked = true;
                selectIndex = i;
                break;
            }
        }

        Object[] params = new Object[2];
        if (isChked) {
            params[0] = scrnMsg.poNum;
            params[1] = scrnMsg.A.no(selectIndex).poOrdDtlLineNum_A1;

        } else {
            params[0] = scrnMsg.poNum;
        }

        setArgForSubScreen(params);
    }
}
