/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2020;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2020.NLAL2020CMsg;
import business.servlet.NLAL2020.common.NLAL2020CommonLogic;
import business.servlet.NLAL2020.constant.NLAL2020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2020Scrn00_Open_MtrEnt
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 * 2018/03/09   CITS            K.Ogino         Update          QC#15629
 * 2019/04/03   Fujitsu         T.Ogura         Update          QC#31000
 *</pre>
 */
public class NLAL2020Scrn00_Open_MtrEnt extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;
        scrnMsg.xxWrnSkipFlg_RC.clear();    // 2019/04/03 T.Ogura [QC#31000,ADD]

        int chkNum = 0;
        int chkOnCnt = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A2.getValue())) {
                chkOnCnt++;
                chkNum = i;
            }
        }
        if (chkOnCnt == 0 || 1 < chkOnCnt) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A2.setErrorInfo(1, "NLAM1316E");
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A2);
            }
        } else if (chkOnCnt == 1) {
            // QC#15629
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(chkNum).mtrReqMdlFlg_A1.getValue())) {
                scrnMsg.A.no(chkNum).xxChkBox_A2.setErrorInfo(1, "NLAM1317E");
                scrnMsg.addCheckItem(scrnMsg.A.no(chkNum).xxChkBox_A2);
            } else if (scrnMsg.A.no(chkNum).poBalQty_A1.getValueInt() == 1 && !ZYPCommonFunc.hasValue(scrnMsg.A.no(chkNum).serNum_A1)) {
                scrnMsg.A.no(chkNum).xxChkBox_A2.setErrorInfo(1, "NLAM1317E");
                scrnMsg.addCheckItem(scrnMsg.A.no(chkNum).xxChkBox_A2);
            }
        }
        scrnMsg.putErrorScreen();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(chkNum));
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;

        NLAL2020CMsg bizMsg = new NLAL2020CMsg();
        bizMsg.setBusinessID(NLAL2020Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;
        NLAL2020CMsg bizMsg  = (NLAL2020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (NLAL2020Constant.MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        NLAL2020CommonLogic.addCheckItemLine(scrnMsg);
        scrnMsg.putErrorScreen();

        Object[] params = new Object[2];
        params[0] = scrnMsg.P.no(0).xxTrxRefPk;
        params[1] = scrnMsg.P.no(0).xxPopPrm;
        setArgForSubScreen(params);
    }
}
