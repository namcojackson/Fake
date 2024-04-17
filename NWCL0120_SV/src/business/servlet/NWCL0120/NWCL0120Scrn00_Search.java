/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0120;

import static business.servlet.NWCL0120.common.NWCL0120CommonLogic.getStartOrEndOfMonth;
import static business.servlet.NWCL0120.common.NWCL0120CommonLogic.setAppFracDigit;
import static business.servlet.NWCL0120.common.NWCL0120CommonLogic.setDataClear;
import static business.servlet.NWCL0120.common.NWCL0120CommonLogic.setErrDataBackGroundColor;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.BIZ_ID;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.MONTH_GET_DAY_MIN_DAY;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.NFCM0039E;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.NWCM0002E;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.NWCM0116E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0120.NWCL0120CMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWCL0120Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0120BMsg scrnMsg = (NWCL0120BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.invDt_FM)) {
            scrnMsg.invDt_FM.setErrorInfo(1, NFCM0039E);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.invDt_TO)) {
            scrnMsg.invDt_TO.setErrorInfo(1, NFCM0039E);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.invDt_FM) && ZYPCommonFunc.hasValue(scrnMsg.invDt_TO)) {

            String startDt = getStartOrEndOfMonth(scrnMsg.slsDt.getValue(), 0, MONTH_GET_DAY_MIN_DAY);
            if (ZYPDateUtil.compare(startDt, scrnMsg.invDt_FM.getValue()) <= 0) {
                scrnMsg.invDt_FM.setErrorInfo(1, NWCM0116E);
            }

            if (ZYPDateUtil.compare(startDt, scrnMsg.invDt_TO.getValue()) <= 0) {
                scrnMsg.invDt_TO.setErrorInfo(1, NWCM0116E);
            }

            if (ZYPDateUtil.compare(scrnMsg.invDt_FM.getValue(), scrnMsg.invDt_TO.getValue()) > 0) {
                scrnMsg.invDt_FM.setErrorInfo(1, NWCM0002E, new String[] {"Invoice Date" });
                scrnMsg.invDt_TO.setErrorInfo(1, NWCM0002E, new String[] {"Invoice Date" });
            }
        }

        scrnMsg.addCheckItem(scrnMsg.invDt_FM);
        scrnMsg.addCheckItem(scrnMsg.invDt_TO);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0120BMsg scrnMsg = (NWCL0120BMsg) bMsg;

        setDataClear(scrnMsg);
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NWCL0120CMsg bizMsg = new NWCL0120CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0120BMsg scrnMsg = (NWCL0120BMsg) bMsg;
        NWCL0120CMsg bizMsg = (NWCL0120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setAppFracDigit(scrnMsg, getGlobalCompanyCode());
        setErrDataBackGroundColor(scrnMsg);
    }
}
