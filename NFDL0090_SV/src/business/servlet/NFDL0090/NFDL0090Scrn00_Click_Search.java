/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0090.NFDL0090CMsg;

import business.servlet.NFDL0090.common.NFDL0090CommonLogic;
import business.servlet.NFDL0090.constant.NFDL0090Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/17   Hitachi         Y.Takeno        Create          QC#26989
 * 2018/09/11   Hitachi         Y.Takeno        Update          QC#24884
 *</pre>
 */
public class NFDL0090Scrn00_Click_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;
        NFDL0090CommonLogic.checkHeader(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;
        NFDL0090CMsg bizMsg = new NFDL0090CMsg();

        bizMsg.setBusinessID("NFDL0090");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;
        NFDL0090CMsg bizMsg  = (NFDL0090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFDL0090CommonLogic.initCmnBtnProp(this, scrnMsg);
        // START 2018/07/19 [QC#26989, ADD]
        setButtonProperties(NFDL0090Constant.BTN_08_CLE_NAME, NFDL0090Constant.BTN_08_CLE_GUARD, NFDL0090Constant.BTN_08_CLE_LABEL, 1, null);
        // END   2018/07/19 [QC#26989, ADD]

        // START 2018/09/11 [QC#24884, ADD]
        NFDL0090CommonLogic.protectHeader(scrnMsg);
        // END   2018/09/11 [QC#24884, ADD]
        NFDL0090CommonLogic.protectDetail(scrnMsg);
        S21SortColumnIMGController.clearIMG(NFDL0090Constant.SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxDealApplyAmtNum_A1);
        }
    }
}
