/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1600;

import static business.servlet.NWAL1600.constant.NWAL1600Constant.BIZ_ID;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.BTN_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1600.NWAL1600CMsg;
import business.servlet.NWAL1600.common.NWAL1600CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL1600_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;
        int index = getButtonSelectNumber();

        // has parameter
        if (!BTN_CMN_CLOSE.equals(getLastGuard())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm_P)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).slsRepTocCd_B, scrnMsg.P.no(9).xxPopPrm_P);
                scrnMsg.B.no(index).tocNm_B.clear();
                scrnMsg.B.no(index).coaBrNm_B.clear();
                scrnMsg.B.no(index).coaCcNm_B.clear();
                scrnMsg.B.no(index).coaExtnNm_B.clear();
            }

            NWAL1600CMsg bizMsg = new NWAL1600CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;
        int index = getButtonSelectNumber();

        // has parameter
        if (!BTN_CMN_CLOSE.equals(getLastGuard())) {
            NWAL1600CMsg bizMsg = (NWAL1600CMsg) cMsg;

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NWAL1600CommonLogic.addCheckNonQuoteItems(scrnMsg);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.A.no(index).lineBizRoleTpCd_A);
    }
}
