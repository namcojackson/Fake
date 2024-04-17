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

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539 
 * 2016/05/24   SRAA            Y.Chen          Update          QC#7962
 *</pre>
 */
public class NMAL2510Scrn00_OnChange_LineOfBusiness extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // 2016/03/04 S21_NA#4539 Mod Start ------------
        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        NMAL2510CommonLogic.clearMandantoryCheck(scrnMsg);
        NMAL2510CommonLogic.addCheckHeaderItems(scrnMsg);
        scrnMsg.putErrorScreen();
        // 2016/03/04 S21_NA#4539 Mod End --------------
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

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.otherResrcTrtyFlg_H1.getValue())) {
                scrnMsg.B.no(i).acctTeamRoleTpCd_P3.setInputProtected(false);
            } else {
                scrnMsg.B.no(i).acctTeamRoleTpCd_P3.setInputProtected(true);
            }
        }
        
        // QC#7962
        scrnMsg.setFocusItem(scrnMsg.lineBizTpCd_P1);
    }
}
