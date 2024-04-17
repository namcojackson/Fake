/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0440;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0440.NSAL0440CMsg;
import static business.servlet.NSAL0440.common.NSAL0440CommonLogic.initialControlScreen;
import static business.servlet.NSAL0440.constant.NSAL0440Constant.*;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Terms & Conditions
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Hitachi         T.Iwamoto         Create          N/A
 * 2016/02/17   Hitachi         K.Kasai           Update          QC#3018
 * 2016/07/06   Hitachi         T.Kanasaka        Update          QC#11458
 *</pre>
 */
public class NSAL0440_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

        Object[] params = (Object[]) getArgForSubScreen();
        if (params == null || params.length == 0 || params[0] == null) {
            bMsg.setMessageInfo(NSAM0353E, new String[] {ERR_PRAM_NO_INPUT });
            bMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0440BMsg scrnMsg = (NSAL0440BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0 && params[0] != null && params[0] instanceof EZDBBigDecimalItem) {
            setValue(scrnMsg.dsContrPk, (EZDBBigDecimalItem) params[0]);
        }
        // mod start 2016/02/17 CSA Defect#3018
//        if (params != null && params.length > 0 && params[1] != null && params[1] instanceof EZDBBigDecimalItem) {
//            setValue(scrnMsg.dsContrDtlPk_P, (EZDBBigDecimalItem) params[1]);
//        }
        if (params != null && params.length > 1 && params[1] != null && params[1] instanceof List) {
            List<BigDecimal> dsContrDtlPks = (List<BigDecimal>) params[1];
            for (int i = 0; i < dsContrDtlPks.size(); i++) {
                scrnMsg.R.no(i).dsContrDtlPk_R.setValue(dsContrDtlPks.get(i));
            }
            scrnMsg.R.setValidCount(dsContrDtlPks.size());
        }
        // mod end 2016/02/17 CSA Defect#3018
        if (params != null && params.length > 2 && params[2] != null && params[2] instanceof EZDBStringItem) {
            setValue(scrnMsg.xxModeCd_P, (EZDBStringItem) params[2]);
        }
        scrnMsg.xxChkBox_H1.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H2, ZYPConstant.CHKBOX_ON_Y);

        NSAL0440CMsg bizMsg = new NSAL0440CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0440BMsg scrnMsg = (NSAL0440BMsg) bMsg;
        NSAL0440CMsg bizMsg = (NSAL0440CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);

        // del start 2016/07/066 CSA Defect#11458
//        if ("E".equals(bizMsg.getMessageKind())) {
//            throw new EZDFieldErrorException();
//        }
        // del end 2016/07/066 CSA Defect#11458
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0440BMsg scrnMsg = (NSAL0440BMsg) bMsg;
        scrnMsg.serNum.setNameForMessage("Serial#");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).svcTermAttrbMapValCd_A.setNameForMessage("Contract T & Cs");
            scrnMsg.A.no(i).svcTermAttrbMapValCd_M.setNameForMessage("Machine T & Cs");
            scrnMsg.A.no(i).svcTermCondDataValCd_PS.setNameForMessage("Contract T & Cs");
            scrnMsg.A.no(i).svcTermCondDataValCd_MS.setNameForMessage("Machine T & Cs");
            scrnMsg.A.no(i).condValNum_A.setNameForMessage("Contract T & Cs");
            scrnMsg.A.no(i).condValNum_M.setNameForMessage("Machine T & Cs");
            scrnMsg.A.no(i).xxTrxDt_A.setNameForMessage("Contract T & Cs");
            scrnMsg.A.no(i).xxTrxDt_M.setNameForMessage("Machine T & Cs");
        }
    }
}
