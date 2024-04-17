/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.BIZ_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/26   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NWAL1840Scrn00_OnBlur_DeriveFromCategory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgDescTxt)) {
            scrnMsg.addCheckItem(scrnMsg.dsOrdCatgDescTxt);
            scrnMsg.putErrorScreen();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgDescTxt)) {
            return null;
        }

        NWAL1840CMsg bizMsg = new NWAL1840CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        setResult(ZYPConstant.FLG_OFF_N);

        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgDescTxt)) {
            scrnMsg.setFocusItem(scrnMsg.dsOrdTpCd);
            return;
        }

        NWAL1840CMsg bizMsg = (NWAL1840CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgDescTxt);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1840CommonLogic.getParamNWAL1130ForOrdCatg(scrnMsg);
            setArgForSubScreen(params);
            return;
        }

        scrnMsg.setFocusItem(scrnMsg.dsOrdTpCd);
        NWAL1840CommonLogic.setProtect(this, scrnMsg);
    }
}
