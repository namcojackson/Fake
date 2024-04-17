/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0110.NWCL0110CMsg;
import business.servlet.NWCL0110.common.NWCL0110CommonLogic;
import business.servlet.NWCL0110.constant.NWCL0110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/21   SRAA            K.Aratani       Create          QC#18692
 *</pre>
 */
public class NWCL0110Scrn00_PrintMassRequest extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0110BMsg scrnMsg = (NWCL0110BMsg) bMsg;

        //Check Input
        //Only 1 date  procDt_FR/procDt_TO
        if (!ZYPCommonFunc.hasValue(scrnMsg.procDt_FR) 
            || !ZYPCommonFunc.hasValue(scrnMsg.procDt_TO)
            || !scrnMsg.procDt_FR.getValue().equals(scrnMsg.procDt_TO.getValue())) {
            //Output Date should be only 1 date in case of Print Mass Request.
            scrnMsg.procDt_FR.setErrorInfo(1, "NWCM0157E");
            scrnMsg.procDt_TO.setErrorInfo(1, "NWCM0157E");
            scrnMsg.addCheckItem(scrnMsg.procDt_FR);
            scrnMsg.addCheckItem(scrnMsg.procDt_TO);
        }
        //Mandatory reprRptBrNum
        if (!ZYPCommonFunc.hasValue(scrnMsg.reprRptBrNum)) {
            //Printer Name is mandatory in case of Print Mass Request.
            scrnMsg.reprRptBrNum.setErrorInfo(1, "NWCM0158E");
            scrnMsg.addCheckItem(scrnMsg.reprRptBrNum);
        }
        //Only Print xxTpCd 
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxTpCd)
            || !"1".equals(scrnMsg.xxTpCd.getValue())) {
            //Output Type should be only Print in case of Print Mass Request.
            scrnMsg.xxTpCd.setErrorInfo(1, "NWCM0159E");
            scrnMsg.addCheckItem(scrnMsg.xxTpCd);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0110BMsg scrnMsg = (NWCL0110BMsg) bMsg;

        NWCL0110CMsg bizMsg = new NWCL0110CMsg();
        bizMsg.setBusinessID(NWCL0110Constant.BIZ_ID);
        bizMsg.setFunctionCode(NWCL0110Constant.FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0110BMsg scrnMsg = (NWCL0110BMsg) bMsg;
        NWCL0110CMsg bizMsg = (NWCL0110CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        NWCL0110CommonLogic.controlDtl(this, scrnMsg);
    }
}
