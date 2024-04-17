/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0250;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0250.common.NLCL0250CommonLogic;
import business.servlet.NLCL0250.constant.NLCL0250Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Onhand Inventory Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3134
 *</pre>
 */
public class NLCL0250_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0250BMsg scrnMsg = (NLCL0250BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCdSrchTxt_RW, scrnMsg.P.no(6).srchOptTxt_P.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNmSrchTxt_RW, NLCL0250CommonLogic.toUpperCase(scrnMsg.P.no(7).srchOptTxt_P.getValue()));

        if (ZYPCommonFunc.hasValue(scrnMsg.P.no(8).srchOptTxt_P)) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCdSrchTxt_SW, scrnMsg.P.no(8).srchOptTxt_P.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNmSrchTxt_SW, NLCL0250CommonLogic.toUpperCase(scrnMsg.P.no(9).srchOptTxt_P.getValue()));
        }

        // Set Focus
        if (NLCL0250Constant.EVENT_NM_OPENWIN_LOCINFO_RTLWH.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.rtlWhCdSrchTxt_RW);

        } else if (NLCL0250Constant.EVENT_NM_OPENWIN_LOCINFO_RTLSWH.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.rtlWhCdSrchTxt_SW);
        }
    }
}
