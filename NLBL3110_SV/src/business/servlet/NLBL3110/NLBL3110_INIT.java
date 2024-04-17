/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3110;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3110.NLBL3110CMsg;
import business.servlet.NLBL3110.common.NLBL3110CommonLogic;
import business.servlet.NLBL3110.constant.NLBL3110Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 *</pre>
 */
public class NLBL3110_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3110BMsg scrnMsg = (NLBL3110BMsg) bMsg;

        // Set global parameter
        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(scrnMsg.slsDt, ZYPDateUtil.getSalesDate());

        NLBL3110CMsg bizMsg = new NLBL3110CMsg();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            int paramCnt = params.length;

            // Outbound Inbound Code
            if (paramCnt > 0) {

                EZDBStringItem param00 = (EZDBStringItem) params[0];

                if (ZYPCommonFunc.hasValue(param00)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.inbdOtbdCd, param00);
                }
            }

            // SO or RWS Number
            if (paramCnt > 1) {

                EZDBStringItem param01 = (EZDBStringItem) params[1];

                if (ZYPCommonFunc.hasValue(param01)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.trxHdrNum_H1, param01);
                }
            }

            // Line Number
            if (paramCnt > 2) {

                EZDBStringItem param02 = (EZDBStringItem) params[2];

                if (ZYPCommonFunc.hasValue(param02)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.trxLineNum_H1, param02);
                }
            }
        }

        bizMsg.setBusinessID(NLBL3110Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3110BMsg scrnMsg = (NLBL3110BMsg) bMsg;
        NLBL3110CMsg bizMsg  = (NLBL3110CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3110CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.cpoNum_H1);
    }

    protected void setNameForMessage(EZDBMsg bMsg) {

        NLBL3110BMsg scrnMsg = (NLBL3110BMsg) bMsg;

        scrnMsg.cpoNum_H1.setNameForMessage("Sales Order Number");
        scrnMsg.trxLineNum_H1.setNameForMessage("Line Number");
        scrnMsg.shipToCustCd_H1.setNameForMessage("Customer Code");
        scrnMsg.dsAcctNm_H1.setNameForMessage("Customer Name");
        scrnMsg.xxAllLineAddr_H1.setNameForMessage("Customer Address");
        scrnMsg.ctyAddr_H1.setNameForMessage("City Address");
        scrnMsg.stCd_H1.setNameForMessage("State");

        if (INBD_OTBD.INBOUND.equals(scrnMsg.trxHdrNum_H1.getValue())) {

            scrnMsg.trxHdrNum_H1.setNameForMessage("RWS Number");

        } else if (INBD_OTBD.OUTBOUND.equals(scrnMsg.trxHdrNum_H1.getValue())) {

            scrnMsg.trxHdrNum_H1.setNameForMessage("Shipping Order Number");
        }
    }
}