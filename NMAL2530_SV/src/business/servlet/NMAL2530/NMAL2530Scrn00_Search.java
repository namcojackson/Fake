/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2530;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;

import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL2530.NMAL2530CMsg;
import business.servlet.NMAL2530.common.NMAL2530CommonLogic;
import business.servlet.NMAL2530.constant.NMAL2530Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC#16481
 *</pre>
 */
public class NMAL2530Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2530BMsg scrnMsg = (NMAL2530BMsg) bMsg;
        NMAL2530CommonLogic.checkDate(scrnMsg); // ADD S21_NA QC#16481
        scrnMsg.addCheckItem(scrnMsg.orgNm_H1);
        scrnMsg.addCheckItem(scrnMsg.orgNm_H2);
        scrnMsg.addCheckItem(scrnMsg.tocNm_H1);
        scrnMsg.addCheckItem(scrnMsg.psnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_FR); // MOD S21_NA QC#16481
        scrnMsg.addCheckItem(scrnMsg.effFromDt_TO); // MOD S21_NA QC#16481
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2530BMsg scrnMsg = (NMAL2530BMsg) bMsg;

        NMAL2530CMsg bizMsg = new NMAL2530CMsg();
        bizMsg.setBusinessID(NMAL2530Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NMAL2530Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2530BMsg scrnMsg = (NMAL2530BMsg) bMsg;
        NMAL2530CMsg bizMsg  = (NMAL2530CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21TableColorController tblColor = new S21TableColorController(NMAL2530Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        S21SortColumnIMGController.clearIMG(NMAL2530Constant.SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

    }
}
