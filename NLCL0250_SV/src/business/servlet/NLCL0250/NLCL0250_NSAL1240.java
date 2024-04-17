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
 * 07/08/2016   CSAI            Y.Imazu         Update          N/A
 *</pre>
 */
public class NLCL0250_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0250BMsg scrnMsg = (NLCL0250BMsg) bMsg;

        if (NLCL0250Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk_P2)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.srchOptTxt_CF, scrnMsg.svcConfigMstrPk_P2.getValue().toString());
            }

            if (scrnMsg.O.getValidCount() > 0) {

                String xxSerNumSrchTxt = null;

                for (int i = 0; i < scrnMsg.O.getValidCount(); i++) {

                    if (ZYPCommonFunc.hasValue(scrnMsg.O.no(i).serNum_O)) {

                        if (xxSerNumSrchTxt != null) {

                            int length = xxSerNumSrchTxt.length() + NLCL0250Constant.COMMA.length() + scrnMsg.O.no(i).serNum_O.getValue().length();

                            if (length > 1000) {

                                break;
                            }

                            xxSerNumSrchTxt = ZYPCommonFunc.concatString(xxSerNumSrchTxt, NLCL0250Constant.COMMA, scrnMsg.O.no(i).serNum_O.getValue());

                        } else {

                            xxSerNumSrchTxt = scrnMsg.O.no(i).serNum_O.getValue();
                        }
                    }
                }

                if (xxSerNumSrchTxt != null) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxSerNumSrchTxt_H1, xxSerNumSrchTxt);

                } else {

                    scrnMsg.xxSerNumSrchTxt_H1.clear();
                }

                if (!ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk_P2)) {

                    scrnMsg.srchOptTxt_CF.clear();
                }
            }
        }

        if (NLCL0250Constant.EVENT_NM_OPENWIN_SERIAL.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.xxSerNumSrchTxt_H1);

        } else if (NLCL0250Constant.EVENT_NM_OPENWIN_CONFIG.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.srchOptTxt_CF);
        }
    }
}
