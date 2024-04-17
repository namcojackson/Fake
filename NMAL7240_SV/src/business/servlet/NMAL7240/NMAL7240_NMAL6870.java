/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7240;

import static business.servlet.NMAL7240.constant.NMAL7240Constant.EVENT_NM_OPENWIN_MULTISHPGSVCLVL;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.NMAM8090W;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7240_NMAL6870
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7240_NMAL6870 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;

        StringBuilder resStr = new StringBuilder();

        if (!ZYPCommonFunc.hasValue(scrnMsg.P.no(0).xxComnScrColValTxt_0.getValue())
                && !ZYPCommonFunc.hasValue(scrnMsg.P.no(0).xxComnScrColValTxt_1.getValue())) {
            if (EVENT_NM_OPENWIN_MULTISHPGSVCLVL.equals(scrnMsg.xxScrEventNm.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.xxDsMultMsgDplyTxt_HL);

            }

        } else if (EVENT_NM_OPENWIN_MULTISHPGSVCLVL.equals(scrnMsg.xxScrEventNm.getValue())) {

            for (int i = 0; i < scrnMsg.P.getValidCount(); i++) {

                if (i != 0) {
                    resStr.append(",");
                }

                resStr.append(scrnMsg.P.no(i).xxComnScrColValTxt_1.getValue());
            }

            if (resStr.length() <= scrnMsg.getAttr(NMAL7240Bean.xxDsMultMsgDplyTxt_HL).getDigit()) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsMultMsgDplyTxt_HL, resStr.toString());
            } else {
                scrnMsg.setMessageInfo(NMAM8090W, new String[] {scrnMsg.xxDsMultMsgDplyTxt_HL.getNameForMessage()});
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsMultMsgDplyTxt_HL, resStr.toString().substring(0, scrnMsg.getAttr(NMAL7240Bean.xxDsMultMsgDplyTxt_HL).getDigit()));
            }

            scrnMsg.setFocusItem(scrnMsg.xxDsMultMsgDplyTxt_HL);
        }
    }
}
