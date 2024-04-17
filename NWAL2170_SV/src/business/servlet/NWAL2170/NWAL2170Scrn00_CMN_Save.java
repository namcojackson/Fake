/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2170;

import static business.servlet.NWAL2170.constant.NWAL2170Constant.BIZ_ID;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.NZZM0002I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2170.NWAL2170CMsg;
import business.servlet.NWAL2170.NWAL2170BMsg;
import business.servlet.NWAL2170.NWAL2170_ABMsg;
import business.servlet.NWAL2170.common.NWAL2170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2170Scrn00_CMN_Save
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/12   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL2170Scrn00_CMN_Save extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL2170_ABMsg aScrnMsg = scrnMsg.A.no(i);
            NWAL2170CommonLogic.checkMandatoryForSave(aScrnMsg, scrnMsg);
        }
        NWAL2170CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;

        NWAL2170CMsg bizMsg = new NWAL2170CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;
        NWAL2170CMsg bizMsg = (NWAL2170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2170CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        NWAL2170CommonLogic.initBizItemProp(this, scrnMsg);
        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
    }
}
