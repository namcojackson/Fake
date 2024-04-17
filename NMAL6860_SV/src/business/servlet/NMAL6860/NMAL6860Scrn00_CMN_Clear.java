/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.copyScrnMsgToBizMsgForSearch;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.isEntryGranted;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setDisplayNameForMessageOfSupplierSite;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.TAB_NM_GENERAL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6860.NMAL6860CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID   : NMAL6860 Supplier Entry
 * Function Name : The business process for Clear.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/04   CITS            M.Ouchi         Create          N/A
 * 2021/03/01   CITS            G.Delgado       Update          QC#56057
 * </pre>
 */
public class NMAL6860Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return copyScrnMsgToBizMsgForSearch((NMAL6860BMsg) bMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // copies bizMsg to scrnMsg.
        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
        EZDMsg.copy((NMAL6860CMsg) cMsg, null, scrnMsg, null);

        // focuses on Supplier Number/Supplier Name on General Tab.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_NM_GENERAL);
        if (isEntryGranted() && !ZYPCommonFunc.hasValue(scrnMsg.prntVndCd)) {
            scrnMsg.setFocusItem(scrnMsg.prntVndCd);
        } else {
            scrnMsg.setFocusItem(scrnMsg.prntVndNm);
        }

        // START 2021/03/01 G.Delgado [QC#56057,DEL]
        // sets the input fields of Common Header.
        // setInputProtectedForCommonHeader(scrnMsg);
        // sets the input fields of General Tab.
        // setInputProtectedForGeneralTab(scrnMsg);
        // sets the input fields of Detail Tab.
        // setInputProtectedForDetailTab(scrnMsg);
        // END 2021/03/01 G.Delgado [QC#56057,DEL]

        // sets the display name.
        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            setDisplayNameForMessageOfSupplierSite(scrnMsg.A.no(index));
        }
    }
}
