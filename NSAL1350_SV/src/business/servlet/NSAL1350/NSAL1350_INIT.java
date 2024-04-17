/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1350;

import static business.servlet.NSAL1350.constant.NSAL1350Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1350.NSAL1350CMsg;
import business.servlet.NSAL1350.common.NSAL1350CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NSAL1350_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/26   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSAL1350_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1350BMsg scrnMsg = (NSAL1350BMsg) bMsg;
        NSAL1350CMsg bizMsg = new NSAL1350CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length >= NSAL1350_PRM_CNT) {
            NSAL1350CommonLogic.getInputParam(scrnMsg, params);
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1350BMsg scrnMsg = (NSAL1350BMsg) bMsg;
        NSAL1350CMsg bizMsg = (NSAL1350CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1350CommonLogic.initCmnBtnProp(this);
        NSAL1350CommonLogic.initBizItemProp(this, scrnMsg);
        NSAL1350CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        scrnMsg.setFocusItem(scrnMsg.mdseItemTpCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1350BMsg scrnMsg = (NSAL1350BMsg) bMsg;

        scrnMsg.mdseItemTpCd.setNameForMessage("Item Classification");
        scrnMsg.mdlId.setNameForMessage("Model");
        scrnMsg.dsOrdPosnNum.setNameForMessage("Configration");
        scrnMsg.mdseCd.setNameForMessage("Item Code");
    }

}
