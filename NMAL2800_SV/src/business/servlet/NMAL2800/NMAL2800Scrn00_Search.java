/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import static business.servlet.NMAL2800.constant.NMAL2800Constant.BIZ_ID;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.MODE;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.MODE_CD_UPL;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.MODE_SRCH;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.NMAM0288E;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.NMAM8552E;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.SCRN_ID_00;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2800.NMAL2800CMsg;
import business.servlet.NMAL2800.common.NMAL2800CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2800Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL2800Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxTpCd_H1)) {
            scrnMsg.xxTpCd_H1.setErrorInfo(1, ZZM9000E, new String[] {MODE });
        } else if (MODE_CD_UPL.equals(scrnMsg.xxTpCd_H1.getValue())) {
            scrnMsg.xxTpCd_H1.setErrorInfo(1, NMAM8552E, new String[] {MODE_SRCH });
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.xxDt10Dt_H1) // 
                && !ZYPCommonFunc.hasValue(scrnMsg.xxDt10Dt_H2) //
                && !ZYPCommonFunc.hasValue(scrnMsg.befDsAcctNm_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.rvwProsNum_H1)) {
            scrnMsg.xxDt10Dt_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.xxDt10Dt_H2.setErrorInfo(1, NMAM0288E);
            scrnMsg.befDsAcctNm_H1.setErrorInfo(1, NMAM0288E);
            scrnMsg.rvwProsNum_H1.setErrorInfo(1, NMAM0288E);
        }

        scrnMsg.addCheckItem(scrnMsg.xxTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.xxDt10Dt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxDt10Dt_H2);
        scrnMsg.addCheckItem(scrnMsg.befDsAcctNm_H1);
        scrnMsg.addCheckItem(scrnMsg.rvwProsNum_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        NMAL2800CMsg bizMsg = new NMAL2800CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;
        NMAL2800CMsg bizMsg = (NMAL2800CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL2800CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A_Left");
        NMAL2800CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A_Right");
        NMAL2800CommonLogic.ctrlDtlFieldProp(scrnMsg, false);
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.xxTpCd_H1);
    }
}
