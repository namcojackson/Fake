/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0200;

import static business.servlet.NMAL0200.constant.NMAL0200Constant.BIZ_APP_ID;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.SCRN_ID;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.EVENT_NM_NMAL0200_CMN_SUBMIT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0200.NMAL0200CMsg;
import business.servlet.NMAL0200.common.NMAL0200CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NMAL0200 Product Categorization Maintenance
 * Function Name : AddLine
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2016   CITS            K.Ogino         Create          N/A
 * 12/27/2018   Fujitsu         C.Hara          Update          QC#29695
 *</pre>
 */
public class NMAL0200Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0200BMsg scrnMsg = (NMAL0200BMsg) bMsg;

        // 2018/12/27 QC#29695 Del Start
        //if (scrnMsg.A.getValidCount() == 0) {
        //    scrnMsg.setMessageInfo(NMAM8105E);
        //    throw new EZDFieldErrorException();
        //}
        // 2018/12/27 QC#29695 Del End

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseStruElmntTpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prodCtrlCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prodCtrlNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).scdProdHrchCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDplyByCtrlItemCdNm_A1);
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0200BMsg scrnMsg = (NMAL0200BMsg) bMsg;

        NMAL0200CMsg bizMsg = new NMAL0200CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        scrnMsg.xxScrEventNm.setValue(EVENT_NM_NMAL0200_CMN_SUBMIT); // 2018/12/27 QC#29695 Add
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0200BMsg scrnMsg = (NMAL0200BMsg) bMsg;
        NMAL0200CMsg bizMsg = (NMAL0200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxScrEventNm.clear(); // 2018/12/27 QC#29695 Add

        NMAL0200CommonLogic.chkErrorInfo(scrnMsg);
        NMAL0200CommonLogic.setTableColumnAttr(scrnMsg);

        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, bMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        scrnMsg.putErrorScreen();
    }
}
