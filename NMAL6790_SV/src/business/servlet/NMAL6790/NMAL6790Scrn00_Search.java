/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6790;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6790.NMAL6790CMsg;
import business.servlet.NMAL6790.common.NMAL6790CommonLogic;
import business.servlet.NMAL6790.constant.NMAL6790Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/04/18   Hitachi         T.Tomita        Update          QC#6218
 * 2017/02/16   Fujitsu         K.Ishizuka      Update          QC#17610
 *</pre>
 */
public class NMAL6790Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6790BMsg scrnMsg = (NMAL6790BMsg) bMsg;

        // ADD START S21_NA QC#17610
        NMAL6790CommonLogic.checkMandatorySearchCondition(scrnMsg);
        NMAL6790CommonLogic.checkWildCardOnly(scrnMsg);
        // ADD END S21_NA QC#17610

        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
        scrnMsg.addCheckItem(scrnMsg.locNum_H1);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnFirstNm_H1);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnLastNm_H1);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6790BMsg scrnMsg = (NMAL6790BMsg) bMsg;

        NMAL6790CMsg bizMsg = new NMAL6790CMsg();
        bizMsg.setBusinessID(NMAL6790Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NMAL6790Constant.FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6790BMsg scrnMsg = (NMAL6790BMsg) bMsg;
        NMAL6790CMsg bizMsg = (NMAL6790CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // add start 2016/04/18 CSA Defect#6218
        NMAL6790CommonLogic.initialControlScreen(this, scrnMsg);
        // add end 2016/04/18 CSA Defect#6218

        S21TableColorController tblColor = new S21TableColorController(NMAL6790Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        S21SortColumnIMGController.clearIMG(NMAL6790Constant.SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        tblColor.setRowStyle("A", 0, NMAL6790Constant.BACKGROUND_COLOR_YELLOW);

    }

}
