/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3080;

import static business.servlet.NLBL3080.constant.NLBL3080Constant.BUSINESS_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NLBL3080.NLBL3080CMsg;
import business.servlet.NLBL3080.constant.NLBL3080Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 * 2016/03/22   CITS            T.Tokutomi      Update          QC#4596
 *</pre>
 */
public class NLBL3080_NLCL0270 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;
        int index = scrnMsg.xxCellIdx.getValueInt();
        if (!NLBL3080Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).mdseCd_B1, scrnMsg.P.no(0).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).xxDplyByItemNm_B1, scrnMsg.P.no(0).xxPopPrm);

            NLBL3080CMsg bizMsg = new NLBL3080CMsg();
            bizMsg.setBusinessID(BUSINESS_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;
        int index = scrnMsg.xxCellIdx.getValueInt();
        if (!NLBL3080Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            NLBL3080CMsg bizMsg = (NLBL3080CMsg) cMsg;

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        scrnMsg.setFocusItem(scrnMsg.B.no(index).mdseCd_B1);
    }
}
