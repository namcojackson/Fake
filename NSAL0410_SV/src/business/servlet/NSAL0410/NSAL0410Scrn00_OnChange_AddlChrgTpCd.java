/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0410;

import static business.servlet.NSAL0410.constant.NSAL0410Constant.BIZ_ID;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0410.NSAL0410CMsg;
//import business.servlet.NSAL0410.constant.NSAL0410Constant;

import business.blap.NSAL0410.NSAL0410CMsg;
import business.servlet.NSAL0410.common.NSAL0410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Additional Charge
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/24   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL0410Scrn00_OnChange_AddlChrgTpCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;

        // for Delete Event
        scrnMsg.xxPgFlg_DE.clear();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;

        NSAL0410CMsg bizMsg = new NSAL0410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // Select Row Number
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum_H, new BigDecimal(getButtonSelectNumber()));
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;
        NSAL0410CMsg bizMsg = (NSAL0410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0410CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID(), getGlobalCompanyCode(), false);
        // set Focus
        scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).addlChrgTpCd_SE);

        // has error
        if (scrnMsg.getMessageCode().endsWith("E")) {
            throw new EZDFieldErrorException();
        }
    }
}
