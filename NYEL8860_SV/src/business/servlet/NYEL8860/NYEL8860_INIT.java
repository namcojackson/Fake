/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8860;

import static business.servlet.NYEL8810.constant.NYEL8810Constant.SCRN_ID_00;
import static business.servlet.NYEL8860.constant.NYEL8860Constant.BIZ_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NYEL8860.NYEL8860CMsg;
import business.servlet.NYEL8860.common.NYEL8860CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NYEL8860_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NYEL8860BMsg scrnMsg = (NYEL8860BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8860BMsg scrnMsg = (NYEL8860BMsg) bMsg;

        Serializable arg = super.getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length >= 5) {
                if (params[0] instanceof String) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxGrpFlg, (String) params[0]);
                }

                if (params[1] instanceof String) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.wfUsrGrpNm, (String) params[1]);
                }

                if (params[3] instanceof String) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, (String) params[3]);
                }
            }
        }

        if (scrnMsg.getMessageCode().endsWith("E")) {
            throw new EZDFieldErrorException();
        }

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
        
        NYEL8860CMsg bizMsg = new NYEL8860CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8860BMsg scrnMsg = (NYEL8860BMsg) bMsg;
        NYEL8860CMsg bizMsg  = (NYEL8860CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NYEL8860CommonLogic.initCmnBtnProp(this);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A, 1);
    }
}
