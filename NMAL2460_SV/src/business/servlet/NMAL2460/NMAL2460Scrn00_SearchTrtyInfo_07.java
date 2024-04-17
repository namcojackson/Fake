/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460;

import static business.servlet.NMAL2460.constant.NMAL2460Constant.BIZ_ID;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.EZD_FUNC_CD_INQ;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.MSG_PARAM_TRTY_NM;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.NMAM8368E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2460.NMAL2460CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASG_TRTY_ATTRB;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/28   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NMAL2460Scrn00_SearchTrtyInfo_07 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        int eventLine = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_EV, BigDecimal.valueOf(eventLine));

        // Error if territory Name are not entered
        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(eventLine).orgNm_B7)) {
            scrnMsg.B.no(eventLine).orgNm_B7.setErrorInfo(1, NMAM8368E, new String[] {MSG_PARAM_TRTY_NM });
            scrnMsg.addCheckItem(scrnMsg.B.no(eventLine).orgNm_B7);
            scrnMsg.putErrorScreen();
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.asgTrtyAttrbCd, ASG_TRTY_ATTRB.ATTRIBUTE7_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        NMAL2460CMsg bizMsg = new NMAL2460CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;
        NMAL2460CMsg bizMsg = (NMAL2460CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(cMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.xxNum_EV.getValueInt()).orgNm_B7);
    }
}
