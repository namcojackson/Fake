/*
 * / <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
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
 * 2016/03/18   Fujitsu         T.Murai         Create          N/A
 * 2016/09/28   Fujitsu         T.Murai         Update          S21_NA#11655
 *</pre>
 */
public class NWAL1840Scrn00_OnBlur_DeriveFromItem extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        int slctLine = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(slctLine);

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(slctLine).mdseCd_A)) {
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

        int slctLine = scrnMsg.xxCellIdx.getValueInt();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(slctLine).mdseCd_A)) {
            return;
        }
        NWAL1840CMsg bizMsg = (NWAL1840CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1840CommonLogic.getParamNMAL6800(scrnMsg);
            setArgForSubScreen(params);
            return;
        }
        // Add Start 2016/09/28 S21_NA#11655
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg_MN.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1840CommonLogic.getParamNMAL6800ForMan(scrnMsg);
            setArgForSubScreen(params);
            return;
        }
        // Add End 2016/09/28 S21_NA#11655

        scrnMsg.addCheckItem(scrnMsg.A.no(slctLine).mdseCd_A);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        NWAL1840CommonLogic.setProtect(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.A.no(slctLine).pkgUomDescTxt_A);
    }
}
