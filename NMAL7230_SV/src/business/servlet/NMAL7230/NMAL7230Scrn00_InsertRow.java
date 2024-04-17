/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7230;

import static business.servlet.NMAL7230.constant.NMAL7230Constant.FREIGHT_ZONE;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NMAM8187E;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7230.common.NMAL7230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7230Scrn00_InsertRow
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7230Scrn00_InsertRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;

        NMAL7230CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        NMAL7230CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL7230CommonLogic.addCheckItemForDetail(scrnMsg);

        if (scrnMsg.A.length() < scrnMsg.A.getValidCount() + 1) {
            scrnMsg.setMessageInfo(NMAM8187E, new String[] {FREIGHT_ZONE, String.valueOf(scrnMsg.A.length())});
            throw new EZDFieldErrorException();
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;

        int idx = scrnMsg.A.getValidCount();
        if (ZYPCommonFunc.hasValue(scrnMsg.lineBizTpCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).lineBizTpCd_A1, scrnMsg.lineBizTpCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).lineBizTpDescTxt_A1, ZYPCodeDataUtil.getName(LINE_BIZ_TP.class, getGlobalCompanyCode(), scrnMsg.lineBizTpCd.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).effFromDt_A1, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        scrnMsg.A.setValidCount(scrnMsg.A.getValidCount() + 1);

        NMAL7230CommonLogic.controlScreen(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.A.no(idx).lineBizTpDescTxt_A1);
    }
}
