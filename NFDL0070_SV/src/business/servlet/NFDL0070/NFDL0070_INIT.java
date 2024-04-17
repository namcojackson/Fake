/**
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFDL0070;

import static business.servlet.NFDL0070.constant.NFDL0070Constant.BIZ_APP_ID;
import static business.servlet.NFDL0070.constant.NFDL0070Constant.NUM_OF_PARAMS_I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0070.NFDL0070CMsg;
import business.servlet.NFDL0070.common.NFDL0070CommonLogic;
import business.servlet.NFDL0070.constant.NFDL0070Constant.MSG_ID;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * NFDL0070_INIT.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/16   Fujitsu         M.Yamada        Create          N/A
 * 2018/07/06   Hitachi         Y.Takeno        Update          QC#26989
 * 2018/07/24   Hitachi         Y.Takeno        Update          QC#26989-1
 *</pre>
 */
public class NFDL0070_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0070CMsg bizMsg = null;

        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;
        Object[] params = (Object[]) getArgForSubScreen();

        if (params == null || params.length < NUM_OF_PARAMS_I) {
            return null;
        }

        NFDL0070CommonLogic.otherBusConnectFrom_NFDL0070_INIT(params, scrnMsg);

        bizMsg = NFDL0070CommonLogic.setRequestData_NFDL0070_INIT(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;
        NFDL0070CMsg bizMsg = (NFDL0070CMsg) cMsg;

        Object[] params = (Object[]) getArgForSubScreen();

        if (params == null || params.length < NUM_OF_PARAMS_I //
                || !ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd)) {
            EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
            scrnMsg.setMessageInfo(MSG_ID.NFDM0001E.toString() //
                    , new String[] {converter.convLabel2i18nLabel("NFDL0070Scrn00", "Account Number") });

            this.setButtonProperties("Continue", "Continue", "Continue", 0, null);
            this.setButtonProperties("btn1", "", "Save", 0, null);
            this.setButtonProperties("btn2", "", "Submit", 0, null);
            this.setButtonProperties("btn3", "", "Apply", 0, null);
            this.setButtonProperties("btn4", "", "Approve", 0, null);
            this.setButtonProperties("btn5", "", "Reject", 0, null);
            this.setButtonProperties("btn6", "", "Download", 0, null);
            this.setButtonProperties("btn7", "", "Delete", 0, null);
            this.setButtonProperties("btn8", "", "Clear", 0, null);
            this.setButtonProperties("btn9", "", "Reset", 0, null);
            this.setButtonProperties("btn10", "CMN_Return", "Close", 1, null);

            scrnMsg.setInputProtected(true);
            scrnMsg.putErrorScreen();
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFDL0070CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();

        NFDL0070CommonLogic.initialize(this, scrnMsg);

        NFDL0070CommonLogic.busBtnControl_NFDL0070_INIT(this, scrnMsg);

        if (scrnMsg.A.getValidCount() > 0) {
            NFDL0070CommonLogic.setRowColors(scrnMsg);
        }
        // START 2018/07/19 [QC#26989, ADD]
        S21SortColumnIMGController.clearIMG("NFDL0070Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // END   2018/07/19 [QC#26989, ADD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());
        // START 2018/07/06 [QC#26989, ADD]
        scrnMsg.arCustRefNum.setNameForMessage("Transaction#");
        // END   2018/07/06 [QC#26989, ADD]
        // START 2018/07/24 [QC#26989-1, ADD]
        scrnMsg.custIssPoNum.setNameForMessage("PO Number");
        // END   2018/07/24 [QC#26989-1, ADD]
    }

}
