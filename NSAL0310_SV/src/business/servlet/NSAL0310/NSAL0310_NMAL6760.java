/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0310;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0310.NSAL0310CMsg;
import business.servlet.NSAL0310.constant.NSAL0310Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Hitachi         T.Tomita        Create          QC#1029
 * 2018/10/30   Hitachi         K.Kim           Update          QC#28890
 *</pre>
 */
public class NSAL0310_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    // START 2018/10/30 [QC#28890, MOD]
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if (!"CMN_Close".equals(getLastGuard())) {
            if ("OpenWin_Customer".equals(scrEventNm)) {
                setValue(scrnMsg.dsAcctNum_H, scrnMsg.xxPopPrm_00);
                setValue(scrnMsg.dsAcctNm, scrnMsg.xxPopPrm_01);
            }
            NSAL0310CMsg bizMsg = new NSAL0310CMsg();
            bizMsg.setBusinessID(NSAL0310Constant.BIZ_ID);
            bizMsg.setFunctionCode(NSAL0310Constant.EZD_FUNC_CD_INQ);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        } else {
            return null;
        }
    }
    // END 2018/10/30 [QC#28890, MOD]

    // START 2018/10/30 [QC#28890, MOD]
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;

        if (!"CMN_Close".equals(getLastGuard())) {
            NSAL0310CMsg bizMsg = (NSAL0310CMsg) cMsg;

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            scrnMsg.putErrorScreen();
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
        }
        scrnMsg.xxScrEventNm.clear();
    }
    // END 2018/10/30 [QC#28890, MOD]
}
