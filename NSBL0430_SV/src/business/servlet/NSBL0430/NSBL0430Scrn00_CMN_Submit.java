/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0430;

import static business.servlet.NSBL0430.constant.NSBL0430Constant.SCREEN_ID;
import static business.servlet.NSBL0430.constant.NSBL0430Constant.BIZ_ID;
import static business.servlet.NSBL0430.constant.NSBL0430Constant.EZD_FUNC_CD_UPD;
import static business.servlet.NSBL0430.common.NSBL0430CommonLogic.validationMandatory;
import static business.servlet.NSBL0430.common.NSBL0430CommonLogic.addCheckItemDtl;
import static business.servlet.NSBL0430.common.NSBL0430CommonLogic.controlScreenFields;
import static business.servlet.NSBL0430.common.NSBL0430CommonLogic.setFocus;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSBL0430.NSBL0430CMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/28   Hitachi         O.Okuma         Create          N/A
 * 2016/02/29   Hitachi         O.Okuma         Update          QC4721
 * 2016/07/12   Hitachi         O.Okuma         Update          QC#11685
 * 2017/02/15   Hitachi         N.Arai          Update          QC#17562
 *</pre>
 */
public class NSBL0430Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0430BMsg scrnMsg = (NSBL0430BMsg) bMsg;
        addCheckItemDtl(scrnMsg);
        // 2017/02/15 N.Arai [QC#17562, ADD]
        validationMandatory(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0430BMsg scrnMsg = (NSBL0430BMsg) bMsg;

        NSBL0430CMsg bizMsg = new NSBL0430CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0430BMsg scrnMsg = (NSBL0430BMsg) bMsg;
        NSBL0430CMsg bizMsg  = (NSBL0430CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        controlScreenFields(scrnMsg);
        addCheckItemDtl(scrnMsg);
        scrnMsg.putErrorScreen();
        setFocus(scrnMsg);
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
