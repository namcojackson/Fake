/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2720;

import static business.servlet.NMAL2720.constant.NMAL2720Constant.BIZ_ID;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.NMAM0042E;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.NMAM0043E;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.NMAM0835E;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.NZZM0002I;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2720.NMAL2720CMsg;
import business.servlet.NMAL2720.common.NMAL2720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2720Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2720Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;
        NMAL2720CommonLogic.addCheckItemScreen(scrnMsg, true);

        List<Integer> selectedList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        if (selectedList == null || selectedList.isEmpty()) {
            scrnMsg.setMessageInfo(NMAM0835E);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_D1)
                && ZYPCommonFunc.hasValue(scrnMsg.effThruDt_D1)) {
            if (ZYPDateUtil.compare(scrnMsg.effFromDt_D1.getValue(), scrnMsg.effThruDt_D1.getValue()) > 0) {
                scrnMsg.effFromDt_D1.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.effFromDt_D1.getNameForMessage(), scrnMsg.effThruDt_D1.getNameForMessage()});
                scrnMsg.effThruDt_D1.setErrorInfo(1, NMAM0042E, new String[] {scrnMsg.effThruDt_D1.getNameForMessage(), scrnMsg.effFromDt_D1.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.effFromDt_D1);
                scrnMsg.addCheckItem(scrnMsg.effThruDt_D1);
            }
        }

        scrnMsg.putErrorScreen();
        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;

        NMAL2720CMsg bizMsg = new NMAL2720CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;
        NMAL2720CMsg bizMsg = (NMAL2720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2720CommonLogic.addCheckItemScreen(scrnMsg, false);

        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_H);

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
    }
}
