/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.createGLCommonPopupPrams;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;


import business.blap.NMAL6860.NMAL6860CMsg;
import business.servlet.NMAL6860.constant.NMAL6860Constant.FUNC_CD;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NMAL6860 Supplier Entry.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/04   CITS            M.Ouchi         Create          N/A
 * 2016/09/30   CITS            R.Shimamoto     Update          QC#12768
 * 2018/08/07   CITS            K.Ogino         Update          QC#27280
 *</pre>
 */
public class NMAL6860Scrn00_OnClick_LiabilityAccount extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;

        NMAL6860CMsg bizMsg = new NMAL6860CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD.SEARCH.getCode());
        scrnMsg.xxNum_AD.setValue(getButtonSelectNumber());
        scrnMsg.eventNm.setValue(LIABILITY_ACCOUNT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
        NMAL6860CMsg bizMsg = (NMAL6860CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // creates the parameter.
        // QC#27280
        Object[] params = createGLCommonPopupPrams(scrnMsg, scrnMsg.A.no(getButtonSelectNumber()).xxComnScrFirstValTxt_AL, ACC_TYPE_LIABILITY, scrnMsg.A.no(getButtonSelectNumber()).coaEnblFlg_A);

        setArgForSubScreen(params);

        scrnMsg.eventNm.setValue(LIABILITY_ACCOUNT);
    }
}
