/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1060;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1060.common.NLCL1060CommonLogic;
import business.servlet.NLCL1060.constant.NLCL1060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/15   Hitachi         S.Dong          Create          QC#61398
 *</pre>
 */
public class NLCL1060Scrn00_Item_Link extends S21CommonHandler {

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

        NLCL1060BMsg scrnMsg = (NLCL1060BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);
        int i = 0;

        if (ZYPCommonFunc.hasValue(scrnMsg.mdseCd_H) && scrnMsg.mdseCd_H.getValue().length() > 16) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.mdseCd_H.getValue().substring(0, 16));

        } else {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.mdseCd_H);
        }

        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, NLCL1060Constant.ITEM_SEARCH_AL);
        scrnMsg.P.setValidCount(i);

        Object[] params = NLCL1060CommonLogic.toArray_popup(scrnMsg.P, i);
        setArgForSubScreen(params);
    }
}
