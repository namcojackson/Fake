/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1240;

import static business.servlet.NPAL1240.common.NPAL1240CommonLogic.copyScrnMsgToBizMsgForSearch;
import static business.servlet.NPAL1240.constant.NPAL1240Constant.ASL_QLFY_TP_BIG_DEAL_SPECIFIC;
import static business.servlet.NPAL1240.constant.NPAL1240Constant.GUARD_CONDITION_CMN_CLOSE;
import static business.servlet.NPAL1240.constant.NPAL1240Constant.INDEX_FOR_BIG_DEAL_SPECIFIC;
import static business.servlet.NPAL1240.constant.NPAL1240Constant.INDEX_FOR_CUSTOMER_SPECIFIC;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1240.NPAL1240CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NPAL1240 Qualifier Setup
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/25   CITS            M.Ouchi         Create          N/A
 *</pre>
 */
public class NPAL1240_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1240BMsg scrnMsg = (NPAL1240BMsg) bMsg;

        if (!GUARD_CONDITION_CMN_CLOSE.equals(getLastGuard())) {

            NPAL1240_ABMsg selectRowMsg = scrnMsg.A.no(getButtonSelectNumber());

            // gets the selected ASL Qualifier Reference Code.
            EZDBStringItem aslQlfyRefCd = null;
            if (ASL_QLFY_TP_BIG_DEAL_SPECIFIC.equals(selectRowMsg.aslQlfyTpCd_A.getValue())) {
                aslQlfyRefCd = scrnMsg.P.no(INDEX_FOR_BIG_DEAL_SPECIFIC).xxComnScrColValTxt;
            } else {
                aslQlfyRefCd = scrnMsg.P.no(INDEX_FOR_CUSTOMER_SPECIFIC).xxComnScrColValTxt;
            }
            // sets the ASL Qualifier Reference Code.
            ZYPEZDItemValueSetter.setValue(selectRowMsg.aslQlfyRefCd_A, aslQlfyRefCd);
        }
        return copyScrnMsgToBizMsgForSearch(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1240BMsg scrnMsg = (NPAL1240BMsg) bMsg;
        NPAL1240CMsg bizMsg = (NPAL1240CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // focus on ASL Qualifier Reference Code.
        scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).aslQlfyRefCd_A);
    }
}
