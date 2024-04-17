/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2830;

import static business.servlet.NMAL2830.constant.NMAL2830Constant.BIZ_ID;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2830.NMAL2830CMsg;
import business.servlet.NMAL2830.common.NMAL2830CommonLogic;
import business.servlet.NMAL2830.constant.NMAL2830Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2830Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2830Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2830BMsg scrnMsg = (NMAL2830BMsg) bMsg;

        NMAL2830CommonLogic.checkMandatoryHeader(scrnMsg);
        NMAL2830CommonLogic.addCheckItemHeader(scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.xxFromDt) && ZYPCommonFunc.hasValue(scrnMsg.xxToDt)) {
            if (scrnMsg.xxFromDt.getValue().compareTo(scrnMsg.xxToDt.getValue()) > 0) {
                scrnMsg.xxFromDt.setErrorInfo(1, NMAL2830Constant.NMAM0185E);
                scrnMsg.xxToDt.setErrorInfo(1, NMAL2830Constant.NMAM0185E);
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2830BMsg scrnMsg = (NMAL2830BMsg) bMsg;

        NMAL2830CMsg bizMsg = new NMAL2830CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2830BMsg scrnMsg = (NMAL2830BMsg) bMsg;
        NMAL2830CMsg bizMsg = (NMAL2830CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL2830CommonLogic.initCmnBtnProp(this);
        NMAL2830CommonLogic.controlScreenFields(getUserProfileService(), this, scrnMsg);
    }
}
