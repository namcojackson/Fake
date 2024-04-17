/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NPBM0012I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Tech Parts Request Entry
 * Function Name : Button Action to Close
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/10/2017   CITS            K.Ogino         Create          QC#16296
 *</pre>
 */
public class NPBL0020Scrn00_HeaderClose extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxBtnFlg) || ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxBtnFlg.getValue())) {
            scrnMsg.setMessageInfo(NPBM0012I);
            scrnMsg.putErrorScreen();
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxBtnFlg, ZYPConstant.FLG_ON_Y);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        NPBL0020CMsg bizMsg = new NPBL0020CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;

        if (!"E".equals(bizMsg.getMessageKind())) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            NPBL0020CommonLogic.setCtrlScrnItemDispAfterSearch(this, scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.prchReqNum);
        }

    }
}
