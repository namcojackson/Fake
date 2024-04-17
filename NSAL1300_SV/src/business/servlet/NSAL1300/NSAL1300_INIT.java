/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1300;

import static business.servlet.NSAL1300.constant.NSAL1300Constant.BIZ_ID;
import static business.servlet.NSAL1300.constant.NSAL1300Constant.FUNCTION_INQUIRY;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1300.NSAL1300CMsg;
import business.servlet.NSAL1300.common.NSAL1300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/01   Hitachi         N.Arai          Create          N/A
 *</pre>
 */
public class NSAL1300_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1300BMsg scrnMsg = (NSAL1300BMsg) bMsg;

        BigDecimal svcContrBllgPk = null;

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBBigDecimalItem) {
                    svcContrBllgPk = ((EZDBBigDecimalItem) prm[0]).getValue();
                }
            }
        }

        scrnMsg.clear();
        if (ZYPCommonFunc.hasValue(svcContrBllgPk)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBllgPk, svcContrBllgPk);
        }

        NSAL1300CMsg bizMsg = new NSAL1300CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_INQUIRY);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1300BMsg scrnMsg = (NSAL1300BMsg) bMsg;
        NSAL1300CMsg bizMsg  = (NSAL1300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1300CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1300BMsg scrnMsg = (NSAL1300BMsg) bMsg;

        scrnMsg.mtrLbDescTxt_FN.setNameForMessage("Counter Type");
        scrnMsg.mtrLbDescTxt_FT.setNameForMessage("Counter Name");
        scrnMsg.serNum_F.setNameForMessage("Serial#");

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).readMtrCnt_BN.setNameForMessage("New End Read");
            scrnMsg.B.no(i).mtrEntryCmntTxt_BN.setNameForMessage("Comment");
        }

    }
}
