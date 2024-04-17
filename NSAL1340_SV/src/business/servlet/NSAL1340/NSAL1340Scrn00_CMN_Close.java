/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1340;

import static business.servlet.NSAL1340.constant.NSAL1340Constant.NSAL1340_OUT_PRM_START_IX;
import static business.servlet.NSAL1340.constant.NSAL1340Constant.NZZM0009E;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1340Scrn00_CMN_Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/09   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public class NSAL1340Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1340BMsg scrnMsg = (NSAL1340BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn)) {
            scrnMsg.setMessageInfo(NZZM0009E);
            throw new EZDFieldErrorException();
        }

        int idx = scrnMsg.xxRadioBtn.getValueInt();
        Object[] arg = (Object[]) getArgForSubScreen();
        NSAL1340_ABMsg abMsg = scrnMsg.A.no(idx);
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            // Set Parameter
            int ixP = NSAL1340_OUT_PRM_START_IX;    // 20160308 mod
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], abMsg.prcListBandDescTxt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], abMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[ixP++], abMsg.minCopyVolCnt);
            ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[ixP++], abMsg.maxCopyVolCnt);
            ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[ixP++], abMsg.cpcAmtRate);
            ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[ixP++], abMsg.totBaseAmt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], abMsg.prcSvcTierTpCd);
        }
    }
}
