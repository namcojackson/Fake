/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0200;

import static business.servlet.NSBL0200.constant.NSBL0200Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSBL0200.NSBL0200CMsg;
import business.servlet.NSBL0200.common.NSBL0200CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *
 * Technician Recommend Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/03   Hitachi         Y.Igarashi         Create          N/A
 * 2013/08/30   WDS Team        K.Aratani          Update          QC1457
 * 2015/11/25   Hitachi         T.Harada           Update          CSA
 *</pre>
 */
public class NSBL0200Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSBL0200BMsg scrnMsg = (NSBL0200BMsg) bMsg;
        NSBL0200CommonLogic.checkInput(scrnMsg);
        //START 2015/11/25 [CSA,DELETE]
        //NSBL0200CommonLogic.chackTimeCompare(scrnMsg.xxHrsMn_SF, scrnMsg.xxHrsMn_ST);
        //NSBL0200CommonLogic.checkOrgSearch(scrnMsg);
        //END 2015/11/25 [CSA,DELETE]
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0200BMsg scrnMsg = (NSBL0200BMsg) bMsg;

        NSBL0200CMsg bizMsg = new NSBL0200CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0200BMsg scrnMsg = (NSBL0200BMsg) bMsg;
        NSBL0200CMsg bizMsg  = (NSBL0200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0200CommonLogic.setProtected(scrnMsg);
        //QC1457
        // START 2015/11/25 [CSA,CHANGE]
        //scrnMsg.setFocusItem(scrnMsg.orgLayerNum_TC);
        scrnMsg.setFocusItem(scrnMsg.fldSvcBrCd_SC);
        // END 2015/11/25 [CSA,CHANGE]
    }
}
