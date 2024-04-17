/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0090;

import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_0;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_1;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_2;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_3;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_4;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 *</pre>
 */
public class NLBL0090Scrn00_OpenWin_BOLHistory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_A1)) {
            scrnMsg.xxRadioBtn_A1.setErrorInfo(1, "NLBM0001E");
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_A1);
        } else {

            // select index at Radio Button
            int index = scrnMsg.xxRadioBtn_A1.getValue().intValue();

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(index).proNum_A1) || !ZYPCommonFunc.hasValue(scrnMsg.A.no(index).carrCd_A1)) {
                scrnMsg.setMessageInfo("NLBM0018I");
                throw new EZDFieldErrorException();
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        // select index at Radio Button
        int index = scrnMsg.xxRadioBtn_A1.getValue().intValue();

        Object[] params = new Object[IDX_4];
        params[IDX_0] = scrnMsg.A.no(index).carrCd_A1;
        params[IDX_1] = scrnMsg.A.no(index).locNm_A1;
        params[IDX_2] = scrnMsg.A.no(index).bolNum_A1;
        params[IDX_3] = scrnMsg.A.no(index).proNum_A1;
        setArgForSubScreen(params);
    }

}
