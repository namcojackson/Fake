/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.constant.NSAL0300Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2015/10/22   Hitachi         T.Kanasaka      Update          N/A
 * 2015/11/10   Hitachi         T.Kanasaka      Update          QC497
 * 2015/11/17   Hitachi         T.Kanasaka      Update          QC777
 * 2016/02/17   Hitachi         T.Kanasaka      Update          QC2879
 * 2016/03/29   Hitachi         M.Gotou         Update          QC#4954
 * 2016/08/01   Hitachi         T.Kanasaka      Update          QC#4806
 * 2016/12/19   Hitachi         K.Kojima        Update          QC#16600
 * 2018/06/18   Fujitsu         T.Murai         Update          QC#26567
 *</pre>
 */
public class NSAL0300Scrn00_Add_Detail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        // START 2016/03/29 M.Gotou [QC#4954, MOD]
        // START 2016/02/17 T.Kanasaka [QC2879, ADD]
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsContrStsCd)) {
            scrnMsg.setMessageInfo(NSAM0426E);
            throw new EZDFieldErrorException();
        }
        // END 2016/02/17 T.Kanasaka [QC2879, ADD]

        if (!ZYPCommonFunc.hasValue(scrnMsg.serNum)) {
            scrnMsg.serNum.setErrorInfo(1, ZZM9000E, new String[] {"Serial#" });
        }
        scrnMsg.addCheckItem(scrnMsg.serNum);

        scrnMsg.putErrorScreen();
        // END 2016/03/29 M.Gotou [QC#4954, MOD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = (NSAL0300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/12/19 K.Kojima [QC#16600,ADD]
        this.setResult("no");
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg.getValue())) {
            this.setResult("yes");
            NSAL0300CommonLogic.clearPopupParam(scrnMsg);
            // START 2018/06/18 T.Murai [QC#26567, MOD]
//            Object[] prm = new Object[3];
            Object[] prm = new Object[6];
            // START 2018/06/18 T.Murai [QC#26567, MOD]
            prm[0] = scrnMsg.dsContrPk;
            prm[1] = scrnMsg.P;
            prm[2] = scrnMsg.serNum;
            // START 2018/06/18 T.Murai [QC#26567, ADD]
            prm[3] = scrnMsg.xxPopPrm_00;
            prm[4] = scrnMsg.xxPopPrm_01;
            prm[5] = scrnMsg.xxPopPrm_02;
            // END 2018/06/18 T.Murai [QC#26567, ADD]
            setArgForSubScreen(prm);
            return;
        }
        // END 2016/12/19 K.Kojima [QC#16600,ADD]

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        NSAL0300CommonLogic.addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        // TODO focus B
        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).contrEffFromDt_A);
        }
    }
}
