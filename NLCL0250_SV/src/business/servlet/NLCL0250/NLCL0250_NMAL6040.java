/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0250;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0250.common.NLCL0250CommonLogic;
import business.servlet.NLCL0250.constant.NLCL0250Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Onhand Inventory Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 *</pre>
 */
public class NLCL0250_NMAL6040 extends S21CommonHandler {

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

        if (!NLCL0250Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.zerothProdCtrlNm_H1, NLCL0250CommonLogic.toUpperCase(scrnMsg.P.no(15).srchOptTxt_P.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.firstProdCtrlNm_H1, NLCL0250CommonLogic.toUpperCase(scrnMsg.P.no(17).srchOptTxt_P.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.scdProdCtrlNm_H1, NLCL0250CommonLogic.toUpperCase(scrnMsg.P.no(19).srchOptTxt_P.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlNm_H1, NLCL0250CommonLogic.toUpperCase(scrnMsg.P.no(21).srchOptTxt_P.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.frthProdCtrlNm_H1, NLCL0250CommonLogic.toUpperCase(scrnMsg.P.no(23).srchOptTxt_P.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdseSrchTxt_H1, NLCL0250CommonLogic.toUpperCase(scrnMsg.P.no(26).srchOptTxt_P.getValue()));
        }

        // Set Focus
        if (NLCL0250Constant.EVENT_NM_PROD_LINE_GRP_LINK.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.zerothProdCtrlNm_H1);

        } else if (NLCL0250Constant.EVENT_NM_PROD_LINE_LVL2_LINK.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.firstProdCtrlNm_H1);

        } else if (NLCL0250Constant.EVENT_NM_PROD_LINE_LVL3_LINK.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.scdProdCtrlNm_H1);

        } else if (NLCL0250Constant.EVENT_NM_PROD_LINE_LVL4_LINK.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.thirdProdCtrlNm_H1);

        } else if (NLCL0250Constant.EVENT_NM_PROD_LINE_LINK.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.frthProdCtrlNm_H1);
        }
    }
}
