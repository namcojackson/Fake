/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7420;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Fujitsu         K.Ishizuka         Create          N/A
 *</pre>
 */
public class NMAL7420Scrn00_CMN_Clear extends S21CommonHandler {

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

        NMAL7420BMsg scrnMsg = (NMAL7420BMsg) bMsg;
        BigDecimal ajstId = scrnMsg.prcRuleHdrPk_H1.getValue();
        String tblNm = scrnMsg.prcRuleNm_H1.getValue();
        String visibleAttrb = scrnMsg.xxComnColOrdTxt.getValue();
        scrnMsg.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleHdrPk_H1, ajstId);
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleNm_H1, tblNm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnColOrdTxt, visibleAttrb);

    }
}
