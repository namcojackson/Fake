/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1310;

import static business.servlet.NSAL1310.constant.NSAL1310Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1310.NSAL1310CMsg;
import business.servlet.NSAL1310.common.NSAL1310CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.pagenation.S21SequentialSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/31   Hitachi         K.Kojima        Create          QC#15136
 *</pre>
 */
public class NSAL1310_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1310BMsg scrnMsg = (NSAL1310BMsg) bMsg;

        NSAL1310CMsg bizMsg = new NSAL1310CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1310BMsg scrnMsg = (NSAL1310BMsg) bMsg;
        NSAL1310CMsg bizMsg = (NSAL1310CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1310CommonLogic.setupScreenItemsInit(this, scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H, ZYPConstant.CHKBOX_ON_Y);

        scrnMsg.setFocusItem(scrnMsg.svcCovTmplTpCd_SV);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL1310BMsg scrnMsg = (NSAL1310BMsg) bMsg;
        scrnMsg.svcCovTmplTpCd_SV.setNameForMessage("Coverage Template");
        scrnMsg.svcTermAttrbDescTxt_H.setNameForMessage("T&C Attrb");
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21SequentialSearchPagenationScrnSupport.getCurrentPageFieldName());
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).svcCovTmplTpCd_A1.setNameForMessage("Coverage Template");
            scrnMsg.A.no(i).svcTermAttrbDescTxt_A1.setNameForMessage("T&C Attrb");
            scrnMsg.A.no(i).svcTermCondDataValCd_D1.setNameForMessage("Default Value");
            scrnMsg.A.no(i).svcTermAttrbDefValTxt_A1.setNameForMessage("Default Value");
            scrnMsg.A.no(i).condValNum_A1.setNameForMessage("Default Value");
            scrnMsg.A.no(i).xxTrxDt_A1.setNameForMessage("Default Value");
            scrnMsg.A.no(i).svcTermCondDataValCd_L1.setNameForMessage("Default Value");
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Effective From Date");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("Effective Thru Date");
        }
    }
}
