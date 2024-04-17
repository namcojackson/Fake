/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1640;

import static business.servlet.NWAL1640.constant.NWAL1640Constant.BIZ_ID;
import static business.servlet.NWAL1640.constant.NWAL1640Constant.MODE_REFERENCE;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1640.NWAL1640CMsg;
import business.servlet.NWAL1640.common.NWAL1640CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1640Scrn00_CMN_Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2016/03/03   Fujitsu         M.suzuki        Update          S21_NA#2140
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 *</pre>
 */
public class NWAL1640Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {


        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
        // 2016/03/03 S21_NA#2140 Mod Start ----------
        if (!MODE_REFERENCE.equals(scrnMsg.xxModeCd.getValue())) {
            NWAL1640CommonLogic.addCheckItem(scrnMsg);
        }
        // 2016/03/03 S21_NA#2140 Mod End ------------
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
        // 2016/03/03 S21_NA#2140 Mod Start ----------
        if (MODE_REFERENCE.equals(scrnMsg.xxModeCd.getValue())) {
            return null;
        }
        // 2016/03/03 S21_NA#2140 Mod End ------------
        NWAL1640CMsg bizMsg = new NWAL1640CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
        // 2016/03/03 S21_NA#2140 Add Start ----------
        if (MODE_REFERENCE.equals(scrnMsg.xxModeCd.getValue())) {
            return;
        }
        // 2016/03/03 S21_NA#2140 Add End ------------

        NWAL1640CMsg bizMsg = (NWAL1640CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1640CommonLogic.addCheckItem(scrnMsg);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxWrnSkipFlg.getValue())) {
            if (EZDMessageInfo.MSGTYPE_WARNING == scrnMsg.getMessageType()) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                throw new EZDFieldErrorException();
            }
        }

        Object[] arg = (Object[]) getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[18], scrnMsg.splyTpCd);
            // 2019/10/04 S21_NA#51372 Mod Start
            //ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[19], scrnMsg.splyNm);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[19], scrnMsg.prntVndNm);
            // 2019/10/04 S21_NA#51372 Mod Start
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[20], scrnMsg.splyFirstAddr);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[21], scrnMsg.splyCtyAddr);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[22], scrnMsg.splyStCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[23], scrnMsg.splyPostCd);
        }
    }
}
