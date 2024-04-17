/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2020;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2020.NLAL2020CMsg;
import business.servlet.NLAL2020.common.NLAL2020CommonLogic;
import business.servlet.NLAL2020.constant.NLAL2020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2020Scrn00_OnChange_RecvQty
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 * 2019/10/02   Fujitsu         T.Ogura         Update          QC#52805
 *</pre>
 */
public class NLAL2020Scrn00_OnChange_RecvQty extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;

        final int eventLine = getButtonSelectNumber();

        // START 2019/10/02 T.Ogura [QC#52805,ADD]
        scrnMsg.addCheckItem(scrnMsg.A.no(eventLine).poBalQty_A1);
        scrnMsg.putErrorScreen();
        // END   2019/10/02 T.Ogura [QC#52805,ADD]

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(eventLine).serNumTakeFlg_A1.getValue())) {

            // START 2019/10/02 T.Ogura [QC#52805,DEL]
//            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(eventLine).poBalQty_A1)) {
//
//                scrnMsg.addCheckItem(scrnMsg.A.no(eventLine).poBalQty_A1);
//                scrnMsg.putErrorScreen();
//            }
            // END   2019/10/02 T.Ogura [QC#52805,DEL]

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(eventLine));
            return;

        } else {

            scrnMsg.xxNum.clear();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.xxNum)) {

            NLAL2020CMsg bizMsg = new NLAL2020CMsg();
            bizMsg.setBusinessID(NLAL2020Constant.BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;

        } else {

            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;

        final int eventLine = getButtonSelectNumber();

        if (ZYPCommonFunc.hasValue(scrnMsg.xxNum)) {

            NLAL2020CMsg bizMsg  = (NLAL2020CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            NLAL2020CommonLogic.controlScreenFields(this, scrnMsg);
            NLAL2020CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, NLAL2020Bean.A);

            scrnMsg.addCheckItem(scrnMsg.A.no(eventLine).poBalQty_A1);
            scrnMsg.putErrorScreen();

            if (NLAL2020Constant.MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {

                throw new EZDFieldErrorException();
            }
        }

        scrnMsg.setFocusItem(scrnMsg.A.no(eventLine).serNum_A1);
    }
}
