/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2570;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2570.NMAL2570CMsg;
import business.servlet.NMAL2570.common.NMAL2570CommonLogic;
import business.servlet.NMAL2570.constant.NMAL2570Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/06/07   SRAA            Y.Chen          Update          QC#7781
 *</pre>
 */
public class NMAL2570Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2570BMsg scrnMsg = (NMAL2570BMsg) bMsg;

        NMAL2570CMsg bizMsg = new NMAL2570CMsg();
        bizMsg.setBusinessID(NMAL2570Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2570Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2570BMsg scrnMsg = (NMAL2570BMsg) bMsg;
        NMAL2570CMsg bizMsg = (NMAL2570CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2570CommonLogic.initialControlScreen(this, scrnMsg);

        S21TableColorController tblColor = new S21TableColorController(NMAL2570Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        // QC#7781
        if (NMAL2570Constant.MULT_SEL_MODE_CD.equals(scrnMsg.xxModeCd_H1.getValue())) {
            tblColor.setAlternateRowsBG("B", scrnMsg.B);
        }
        S21SortColumnIMGController.clearIMG(NMAL2570Constant.SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.xxPsnNm_H1);

    }
}
