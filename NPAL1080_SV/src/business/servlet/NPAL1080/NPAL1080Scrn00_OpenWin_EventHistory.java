/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.NMAM8098E;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NPAM0049E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * Function Name : Open Return to PR History Popup(NPAL1440)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1080Scrn00_OpenWin_EventHistory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        int chkCount = 0;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_D1.getValue())) {
                chkCount++;
            }
        }

        if (chkCount == 0) {
            scrnMsg.setMessageInfo(NPAM0049E);
            throw new EZDFieldErrorException();
        }
        if (1 < chkCount) {
            scrnMsg.setMessageInfo(NMAM8098E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        int selectIdx = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_D1.getValue())) {
                selectIdx = i;
                break;
            }
        }
        Object[] params = new Object[3];
        params[0] = scrnMsg.prchReqNum_H1;
        params[1] = scrnMsg.A.no(selectIdx).prchReqLineNum_D1;
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIdx).prchReqLineSubNum_D1)) {
            params[2] = scrnMsg.A.no(selectIdx).prchReqLineSubNum_D1.getValue().toString();
        }
        setArgForSubScreen(params);
    }
}
