/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0260;

import static business.servlet.NMAL0260.constant.NMAL0260Constant.NMAM8335E;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0260Scrn00_Continue
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Fujitsu         q09083          Create          N/A
 *</pre>
 */
public class NMAL0260Scrn00_Continue extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0260BMsg scrnMsg = (NMAL0260BMsg) bMsg;
        String salesDt = ZYPDateUtil.getSalesDate();
        //if (0 <= ZYPDateUtil.compare(salesDt, scrnMsg.effFromDt.getValue())) {
        if (0 < ZYPDateUtil.compare(salesDt, scrnMsg.effFromDt.getValue())) {
        	//NMAM8335E=0,A Past Date cannot be entered into the "Date Effective Date"
            scrnMsg.effFromDt.setErrorInfo(1, NMAM8335E);
        }
        
//        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_A1)) {
//        	if (0 <= ZYPDateUtil.compare(scrnMsg.effFromDt_A1.getValue(), scrnMsg.effFromDt.getValue())) {
//            	//NMAM8335E=0,A Past Date cannot be entered into the "Date Effective Date"
//                scrnMsg.effFromDt.setErrorInfo(1, NMAM8335E);
//        	}
//        }

        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0260BMsg scrnMsg = (NMAL0260BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params instanceof Object[]) {
            EZDBDateItem param2 = (EZDBDateItem) params[2];
            ZYPEZDItemValueSetter.setValue(param2, scrnMsg.effFromDt.getValue());
        }
        
    }
}
