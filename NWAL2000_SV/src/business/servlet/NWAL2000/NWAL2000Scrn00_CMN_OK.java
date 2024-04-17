/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2000;

import static business.servlet.NWAL2000.constant.NWAL2000Constant.LINE_MODE;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2000.common.NWAL2000CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2000Scrn00_CMN_OK
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/08   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL2000Scrn00_CMN_OK extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2000BMsg scrnMsg = (NWAL2000BMsg) bMsg;
        if (LINE_MODE.equals(scrnMsg.xxModeCd.getValue())) {

            if (ZYPCommonFunc.hasValue(scrnMsg.ordQty) && ZYPCommonFunc.hasValue(scrnMsg.cancQty)) {
                NWAL2000CommonLogic.checkQuantity(scrnMsg);
            }
            scrnMsg.addCheckItem(scrnMsg.cancQty);
        }
        scrnMsg.addCheckItem(scrnMsg.chngRsnTpCd);
        scrnMsg.addCheckItem(scrnMsg.bizProcCmntTxt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL2000BMsg scrnMsg = (NWAL2000BMsg) bMsg;

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        Object[] arg = (Object[]) getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            // Set Parameter
            int ixP= 0;
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], scrnMsg.xxModeCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], scrnMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], scrnMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], scrnMsg.configCatgCd);
//            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[4], scrnMsg.dsOrdPosnNum);
//            ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[5], scrnMsg.dsCpoLineNum);
//            ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[6], scrnMsg.dsCpoLineSubNum);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], scrnMsg.xxPopPrm_DI);
            ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[ixP++], scrnMsg.ordQty);
            ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[ixP++], scrnMsg.cancQty);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], scrnMsg.chngRsnTpCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], scrnMsg.bizProcCmntTxt);
        }
    }
}
