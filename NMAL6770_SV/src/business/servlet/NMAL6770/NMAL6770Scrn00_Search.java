/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6770;

import static business.servlet.NMAL6770.constant.NMAL6770Constant.BUSINESS_ID;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.NMAM0288E;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.SCREEN_ID;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6770.NMAL6770CMsg;
import business.servlet.NMAL6770.common.NMAL6770CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/23   Fujitsu         M.Nakamura      Create          N/A
 * 2015/10/01   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/16   Fujitsu         C.Tanaka        Update          QC#2041
 * 2017/02/16   Fujitsu         K.Ishizuka      Update          QC#17610
 *</pre>
 */
public class NMAL6770Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6770BMsg scrnMsg = (NMAL6770BMsg) bMsg;

        // ADD START S21_NA QC#17610
        NMAL6770CommonLogic.checkMandatorySearchCondition(scrnMsg);
        NMAL6770CommonLogic.checkWildCardOnly(scrnMsg);
        // ADD END S21_NA QC#17610

        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
        scrnMsg.addCheckItem(scrnMsg.locNum_H1);
        scrnMsg.addCheckItem(scrnMsg.dsLocNm_H1);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnFirstNm_H1);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnLastNm_H1);
        scrnMsg.addCheckItem(scrnMsg.billToCustCd_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6770BMsg scrnMsg = (NMAL6770BMsg) bMsg;

        NMAL6770CMsg bizMsg = new NMAL6770CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6770BMsg scrnMsg = (NMAL6770BMsg) bMsg;
        NMAL6770CMsg bizMsg = (NMAL6770CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }

}
