/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static business.servlet.NSAL0990.constant.NSAL0990Constant.ONBLUR_DERIVEFROM_FREIGHT_TERMS;
import static business.servlet.NSAL0990.constant.NSAL0990Constant.OPENWIN_FREIGHT_TERMS;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
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
 * 2016/03/14   Hitachi         A.Kohinata      Update          QC#5375
 * 2017/11/22   Hitachi         M.Kidokoro      Update          QC#20497
 *</pre>
 */
public class NSAL0990_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2017/11/22 M.Kidokoro [QC#20497, MOD]
        // return null;
        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        if (!"CMN_Close".equals(getLastGuard())) {
            NSAL0990CMsg bizMsg = new NSAL0990CMsg();
            bizMsg.setBusinessID("NSAL0990");
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        } else {
            return null;
        }
        // START 2017/11/22 M.Kidokoro [QC#20497, MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        if (OPENWIN_FREIGHT_TERMS.equals(scrnMsg.xxScrEventNm.getValue()) || ONBLUR_DERIVEFROM_FREIGHT_TERMS.equals(scrnMsg.xxScrEventNm.getValue())) {
            // START 2017/11/22 M.Kidokoro [QC#20497,ADD]
            NSAL0990CMsg bizMsg = (NSAL0990CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            // END 2017/11/22 M.Kidokoro [QC#20497,ADD]
            NSAL0990CommonLogic.setProtectByFrtCond(scrnMsg);
            // START 2017/11/22 M.Kidokoro [QC#20497,ADD]
            scrnMsg.addCheckItem(scrnMsg.frtCondDescTxt);
            scrnMsg.putErrorScreen();
            // END 2017/11/22 M.Kidokoro [QC#20497,ADD]
            scrnMsg.setFocusItem(scrnMsg.shpgSvcLvlCd);
        }
        // START 2017/11/22 M.Kidokoro [QC#20497,ADD]
        scrnMsg.xxScrEventNm.clear();
        // END 2017/11/22 M.Kidokoro [QC#20497,ADD]
    }
}
