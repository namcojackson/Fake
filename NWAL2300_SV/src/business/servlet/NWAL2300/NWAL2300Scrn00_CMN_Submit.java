/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2300;

import static business.servlet.NWAL2300.constant.NWAL2300Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.NZZM0002I;
import static business.servlet.NWAL2300.constant.NWAL2300Constant.BIZ_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2300.NWAL2300CMsg;
import business.servlet.NWAL2300.common.NWAL2300CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/26   CUSA            Fujitsu         Create          N/A
 * 2018/04/06   Fujitsu         T.Aoi           Update          QC#22122
 *</pre>
 */
public class NWAL2300Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2300BMsg scrnMsg = (NWAL2300BMsg) bMsg;
        NWAL2300CommonLogic.submitErrorCheck(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2300BMsg scrnMsg = (NWAL2300BMsg) bMsg;

        NWAL2300CMsg bizMsg = new NWAL2300CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2300BMsg scrnMsg = (NWAL2300BMsg) bMsg;
        NWAL2300CMsg bizMsg = (NWAL2300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        boolean authorityFlg = NWAL2300CommonLogic.isEditable(scrnMsg);
        if (authorityFlg && 0 < scrnMsg.C.getValidCount()) {
            NWAL2300CommonLogic.submitCmnBtnProp(this);
        } else {
            NWAL2300CommonLogic.initCmnBtnProp(this);
        }
        // 2018/04/06 QC#22122 Mod Start
        //NWAL2300CommonLogic.setProtect(this, scrnMsg);
        boolean submitProtectFlag = NWAL2300CommonLogic.setProtect(this, scrnMsg);
        // 2018/04/06 QC#22122 Mod End
        NWAL2300CommonLogic.setControlFieldsForDigit(scrnMsg);
        NWAL2300CommonLogic.addCheckItem(scrnMsg);
        NWAL2300CommonLogic.setRowsBGWithClear(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == 1 || MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // 2018/04/06 QC#22122 Mod Start
        //if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
        //    scrnMsg.setMessageInfo(NZZM0002I);
        //    NWAL2300CommonLogic.initCmnBtnProp(this);
        //}
        if (submitProtectFlag) {
            scrnMsg.setMessageInfo(NZZM0002I);
            NWAL2300CommonLogic.initCmnBtnProp(this);
        } else {
            scrnMsg.setMessageInfo(NZZM0002I);
            NWAL2300CommonLogic.submitCmnBtnProp(this);
        }
        // 2018/04/06 QC#22122 Mod End
        // focus set
        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum_H1);
    }
}
