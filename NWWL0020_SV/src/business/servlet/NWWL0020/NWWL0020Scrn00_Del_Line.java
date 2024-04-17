/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0020;

import static business.servlet.NWWL0020.constant.NWWL0020Constant.BIZ_ID;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.NWWM0002E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0020.NWWL0020CMsg;
import business.servlet.NWWL0020.common.NWWL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0020Scrn00_Del_Line
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0020Scrn00_Del_Line extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;
        int selectNum = scrnMsg.xxRadioBtn_A0.getValueInt();

        if (scrnMsg.A.getValidCount() == 0 || selectNum < 0) {
            scrnMsg.setMessageInfo(NWWM0002E, new String[] {scrnMsg.xxRadioBtn_A0.getNameForMessage() });
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;
        NWWL0020CMsg bizMsg = new NWWL0020CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;
        NWWL0020CMsg bizMsg = (NWWL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlProtFlg, ZYPConstant.FLG_ON_Y);
        }

        NWWL0020CommonLogic.setControlFieldsActDtl(scrnMsg, this);
        NWWL0020CommonLogic.setControlFieldsActDtlTabSubmitBtn(scrnMsg, this);
        NWWL0020CommonLogic.setControlFieldsActDtlBtn(scrnMsg, this);
        NWWL0020CommonLogic.setControlFieldsActListProt(scrnMsg);
    }
}