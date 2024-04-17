/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import static business.servlet.NSAL1320.constant.NSAL1320Constant.BIZ_ID;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import business.blap.NSAL1320.NSAL1320CMsg;
import business.servlet.NSAL1320.NSAL1320BMsg;
import business.servlet.NSAL1320.common.NSAL1320CommonLogic;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1320_NSAL0110
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2017/10/24   Hitachi         Y.Takeno        Update          QC#21556
 * 2018/05/17   Fujitsu         A.Kosai         Update          QC#22482
 *</pre>
 */
public class NSAL1320_NSAL0110 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;

        NSAL1320CMsg bizMsg = new NSAL1320CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        NSAL1320CMsg bizMsg  = (NSAL1320CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // 2018/05/17 QC#22482 Add Start
        NSAL1320CommonLogic.setScrnManOvrdDspCtrl(scrnMsg);
        // 2018/05/17 QC#22482 Add End
        NSAL1320CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();
        // START 2017/10/24 [QC#21556, ADD]
        int ixA = scrnMsg.xxCellIdx.getValueInt();
        // 2018/05/17 QC#22482 Mod Start
//        NSAL1320CommonLogic.protectLineItemForAddContr(scrnMsg, scrnMsg.A.no(ixA));
        NSAL1320CommonLogic.protectLineItemForAddContr(this, scrnMsg, scrnMsg.A.no(ixA), ixA);
        // 2018/05/17 QC#22482 Mod End
        // END   2017/10/24 [QC#21556, ADD]
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        // START 2017/10/24 [QC#21556, DEL]
        // int ixA = scrnMsg.xxCellIdx.getValueInt();
        // END   2017/10/24 [QC#21556, DEL]

        scrnMsg.setFocusItem(scrnMsg.A.no(ixA).dsContrNum_AD);
    }
}
