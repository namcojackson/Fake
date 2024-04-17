/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0100.NFDL0100CMsg;
import business.servlet.NFDL0100.common.NFDL0100CommonLogic;
import business.servlet.NFDL0100.constant.NFDL0100Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Print Invoice
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   CSAI            K.Lee           Create          N/A
 * 2016/08/01   Hitachi         K.Kojima        Update          QC#11570
 * 2020/03/09   CITS            M.Furugoori     Update          QC#55489
 * 2023/03/07   Hitachi         S.Fujita        Update          QC#61169
 *</pre>
 */
public class NFDL0100Scrn00_Click_Btn_Refresh extends S21CommonHandler implements NFDL0100Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum_H1);
        // START 2023/03/07 S.Fujita [QC#61169, MOD]
       //  scrnMsg.addCheckItem(scrnMsg.arTrxNum_H1);
        scrnMsg.addCheckItem(scrnMsg.arCustRefNum_H1);
        // END 2023/03/07 S.Fujita [QC#61169, MOD]
        scrnMsg.addCheckItem(scrnMsg.billToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.arTrxDt_H1);
        scrnMsg.addCheckItem(scrnMsg.arTrxDt_H2);
        scrnMsg.addCheckItem(scrnMsg.xxDplyTrxNumTxt_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;
        NFDL0100CMsg bizMsg = new NFDL0100CMsg();
        bizMsg.setBusinessID("NFDL0100");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;
        NFDL0100CMsg bizMsg  = (NFDL0100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        S21TableColorController tblColor = new S21TableColorController("NFDL0100Scrn00", scrnMsg);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
        NFDL0100CommonLogic.initialize(this, scrnMsg);
        // START 2016/08/01 K.Kojima [QC#11570,DEL]
        // NFDL0100CommonLogic.setTableColor(scrnMsg);
        // END 2016/08/01 K.Kojima [QC#11570,DEL]
        tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);

        // START 2020/03/09 M.Furugoori [QC#55489, ADD]
        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG("NFDL0100Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // END 2020/03/09 M.Furugoori [QC#55489, ADD]
    }
}
