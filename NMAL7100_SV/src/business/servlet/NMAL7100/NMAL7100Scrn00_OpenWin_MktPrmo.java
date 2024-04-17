/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7100.NMAL7100CMsg;
//import business.servlet.NMAL7100.constant.NMAL7100Constant;

import business.servlet.NMAL7100.common.NMAL7100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_OPENWIN_MKTPRMO;;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL7100Scrn00_OpenWin_MktPrmo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;
        //
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_H1, EVT_OPENWIN_MKTPRMO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx_H1, BigDecimal.ZERO);

        Object[] argParam = NMAL7100CommonLogic.createArgumentNWAL1130ForHdr(scrnMsg, getGlobalCompanyCode());

        if (argParam != null) {
            setArgForSubScreen(argParam);
        }
    }
}
