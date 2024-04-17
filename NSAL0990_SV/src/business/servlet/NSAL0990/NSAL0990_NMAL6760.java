/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0990.NSAL0990CMsg;
import business.servlet.NSAL0990.common.NSAL0990CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2016/03/29   Hitachi         A.Kohinata      Update          QC5790
 * 2018/07/30   Hitachi         T.Tomita        Update          QC#14037
 *</pre>
 */
public class NSAL0990_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        if (!"CMN_Close".equals(getLastGuard())) {
            if ("OpenWin_BillTo".equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.billToAcctNum, scrnMsg.xxPopPrm_P0.getValue());
                setValue(scrnMsg.billToLocNum, scrnMsg.xxPopPrm_PF.getValue());
            } else if ("OpenWin_ShipTo".equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.curLocAcctNum, scrnMsg.xxPopPrm_P0.getValue());
                setValue(scrnMsg.curLocNum, scrnMsg.xxPopPrm_PG.getValue());
            }
            NSAL0990CMsg bizMsg = new NSAL0990CMsg();
            bizMsg.setBusinessID("NSAL0990");
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        if (!"CMN_Close".equals(getLastGuard())) {
            NSAL0990CMsg bizMsg = (NSAL0990CMsg) cMsg;

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            NSAL0990CommonLogic.addCheckItem(scrnMsg);

            scrnMsg.putErrorScreen();
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
            // Add Start 2018/07/30 QC#14307
            NSAL0990CommonLogic.activeSpclInstructionBtn(this, scrnMsg);
            // Add End 2018/07/30 QC#14307
        }
        scrnMsg.xxScrEventNm.clear();

    }
}
