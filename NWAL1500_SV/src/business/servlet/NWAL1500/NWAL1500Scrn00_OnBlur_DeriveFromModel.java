/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/21   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/18   Fujitsu         T.Ishii         Update          QC#1634
 *</pre>
 */
public class NWAL1500Scrn00_OnBlur_DeriveFromModel extends S21CommonHandler {

    // TODO
    // UnUsed T.Yoshida

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).mdlDescTxt_LC)) {
            scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).mdlDescTxt_LC);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        scrnMsg.A.no(selectIndex).mdlId_LC.clear();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).mdlDescTxt_LC)) {
            return null;
        }

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        setResult(ZYPConstant.FLG_OFF_N);

        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).mdlDescTxt_LC)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).shipToCustCd_LC);
            return;
        }

        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).mdlDescTxt_LC);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            // Object[] params = NWAL1500CommonLogic.getParamNMAL6050ForMdl(scrnMsg);
            // setArgForSubScreen(params);
            return;
        }

        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);
        // S21_NA#1634NWAL1500CommonLogic.controlMdseField(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).mdlDescTxt_LC);
    }
}
