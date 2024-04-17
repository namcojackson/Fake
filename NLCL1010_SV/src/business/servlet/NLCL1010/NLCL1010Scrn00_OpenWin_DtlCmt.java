/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1010;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1010.common.NLCL1010CommonLogic;
import business.servlet.NLCL1010.constant.NLCL1010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MSG_CTRL_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/19   Fujitsu         Tozuka          Create          R-WH002
 *</pre>
 */
public class NLCL1010Scrn00_OpenWin_DtlCmt extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;
        int selectedIndex = getButtonSelectNumber();

        NLCL1010CommonLogic.clearPopUpParam(scrnMsg);

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLCL1010Constant.BUSINESS_ID);

        String xxOpsTp;
        if (NLCL1010CommonLogic.hasRegisterAuthority(functionList)) {
            xxOpsTp = NLCL1010Constant.XX_OPS_TP_EDIT;
        } else {
            xxOpsTp = NLCL1010Constant.XX_OPS_TP_INQUIRY;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, xxOpsTp);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, MSG_CTRL_TP.SERIAL_TRANSACTION_COMMENT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, scrnMsg.A.no(selectedIndex).serTrxCmntTxt_A1);

        setArgForSubScreen(NLCL1010CommonLogic.toArray_popup(scrnMsg.P, 3));
    }
}
