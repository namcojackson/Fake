/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0250;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
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
 * 07/09/2016   CSAI            Y.Imazu         Create          QC#6610
 *</pre>
 */
public class NLCL0250_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //no process;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (!NLCL0250Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            NLCL0250BMsg scrnMsg = (NLCL0250BMsg) bMsg;

            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(16).srchOptTxt_P)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxFldValTxt_HN, scrnMsg.P.no(1).srchOptTxt_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxFldValTxt_HC, scrnMsg.P.no(16).srchOptTxt_P);

            } else {

                scrnMsg.xxFldValTxt_HN.clear();
                scrnMsg.xxFldValTxt_HC.clear();
            }
        }
    }
}
