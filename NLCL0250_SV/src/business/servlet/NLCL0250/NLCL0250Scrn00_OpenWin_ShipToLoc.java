/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0250;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0250.common.NLCL0250CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Onhand Inventory Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3134
 * 07/08/2016   CSAI            Y.Imazu         Update          QC#6610
 *</pre>
 */
public class NLCL0250Scrn00_OpenWin_ShipToLoc extends S21CommonHandler {

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

        ZYPTableUtil.clear(scrnMsg.P);
        int i = 0;

        scrnMsg.P.no(i++).srchOptTxt_P.clear();

        if (ZYPCommonFunc.hasValue(scrnMsg.xxFldValTxt_HN) && scrnMsg.xxFldValTxt_HN.getValue().length() > 360) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.xxFldValTxt_HN.getValue().substring(0, 360));

        } else {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.xxFldValTxt_HN);
        }

        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, "03");
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();

        if (ZYPCommonFunc.hasValue(scrnMsg.xxFldValTxt_HC) && scrnMsg.xxFldValTxt_HC.getValue().length() > 20) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.xxFldValTxt_HC.getValue().substring(0, 20));

        } else {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.xxFldValTxt_HC);
        }

        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.setValidCount(i);

        Object[] params = NLCL0250CommonLogic.toArray_popup(scrnMsg.P, i);
        setArgForSubScreen(params);
    }
}
