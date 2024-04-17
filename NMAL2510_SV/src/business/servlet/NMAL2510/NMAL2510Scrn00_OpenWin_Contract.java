/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2510.NMAL2510CMsg;
import business.servlet.NMAL2510.common.NMAL2510CommonLogic;
import business.servlet.NMAL2510.constant.NMAL2510Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Crerate          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update           S21_NA#4539
 * 2016/09/19/  SRAA            Y.Chen          Update           QC#14595
 *</pre>
 */
public class NMAL2510Scrn00_OpenWin_Contract extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/03/04 S21_NA#4539 Add Start --------------
        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.moveOrdLimitAmt_D1);
        scrnMsg.addCheckItem(scrnMsg.tmZoneCd_P1);
        scrnMsg.addCheckItem(scrnMsg.costPerHourAmt_D1);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnPk_D1);
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_D1);
        scrnMsg.putErrorScreen();
        // 2016/03/04 S21_NA#4539 Add Start --------------

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        NMAL2510CMsg bizMsg = new NMAL2510CMsg();
        bizMsg.setBusinessID(NMAL2510Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2510Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        NMAL2510CMsg bizMsg = (NMAL2510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Making of sub screen delivery information
        // QC#14595
        Object[] params = NMAL2510CommonLogic.setParamForContractSearchPopup(scrnMsg, this.getGlobalCompanyCode());

        // Sub screen transition
        setArgForSubScreen(params);

    }
}
