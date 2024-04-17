/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.SUCCESS;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6720.NMAL6720CMsg;
import business.servlet.NMAL6720.common.NMAL6720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   SRAA            Y.Chen          Create          QC#4505
 * 2018/07/31   Fujitsu         W.Honda         Update          QC#27488
 *</pre>
 */
public class NMAL6720Scrn00_GetAddress extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.postCd_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CMsg bizMsg = new NMAL6720CMsg();
        bizMsg.setBusinessID("NMAL6720");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CMsg bizMsg = (NMAL6720CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.ctyAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.stCd_P1);
        scrnMsg.addCheckItem(scrnMsg.postCd_H1);
        scrnMsg.addCheckItem(scrnMsg.cntyNm_H1);
        scrnMsg.putErrorScreen();

        // 2018/07/31 S21_NA#27169 Mod Start
        if (SUCCESS == scrnMsg.xxRsltCd.getValue()) {
            setResult(ZYPConstant.FLG_OFF_N);
        } else {
            setResult(ZYPConstant.FLG_ON_Y);
            Object[] params = NMAL6720CommonLogic.prepareAddressLookupPopupParameter(scrnMsg, this.getGlobalCompanyCode(), ctx.getEventName());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRsltCd, SUCCESS);
            setArgForSubScreen(params);
        }
        // 2018/07/31 S21_NA#27169 Mod End
        scrnMsg.setFocusItem(scrnMsg.postCd_H1);
    }
}
