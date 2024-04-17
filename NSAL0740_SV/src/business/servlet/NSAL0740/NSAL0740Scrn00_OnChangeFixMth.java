/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0740;

import static business.servlet.NSAL0740.constant.NSAL0740Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0740.NSAL0740CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/16   Hitachi         K.Kitachi       Create          QC#28638
 *</pre>
 */
public class NSAL0740Scrn00_OnChangeFixMth extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;

        int idx = getButtonSelectNumber();
        NSAL0740_ABMsg aBMsg = scrnMsg.A.no(idx);
        scrnMsg.addCheckItem(aBMsg.fixTermInMthAot_D1);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;

        int idx = getButtonSelectNumber();
        NSAL0740_ABMsg aBMsg = scrnMsg.A.no(idx);
        if (!hasValue(aBMsg.fixTermInMthAot_D1)) {
            aBMsg.uplftFixedDt_D1.clear();
            aBMsg.uplftPcyDt_D1.clear();
            scrnMsg.setFocusItem(aBMsg.fixTermInMthAot_D1);
            return null;
        }

        NSAL0740CMsg bizMsg = new NSAL0740CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        setValue(bizMsg.xxRowNum, BigDecimal.valueOf(idx));

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;
        NSAL0740CMsg bizMsg = (NSAL0740CMsg) cMsg;
        if (bizMsg == null) {
            return;
        }
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int idx = getButtonSelectNumber();
        NSAL0740_ABMsg aBMsg = scrnMsg.A.no(idx);
        scrnMsg.addCheckItem(aBMsg.fixTermInMthAot_D1);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.setFocusItem(aBMsg.fixTermInMthAot_D1);
    }
}
