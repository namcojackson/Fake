/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3160;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3160.NLBL3160CMsg;
import business.blap.NLBL3160.constant.NLBL3160Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NLBL3160_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;
        NLBL3160CMsg bizMsg = new NLBL3160CMsg();
        bizMsg.setBusinessID(NLBL3160Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;
        NLBL3160CMsg bizMsg = (NLBL3160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, scrnMsg.P.no(0).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseNm, scrnMsg.P.no(1).xxPopPrm);
        scrnMsg.setFocusItem(scrnMsg.mdseCd);
    }
}
