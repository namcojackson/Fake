/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0250;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0250.common.NLCL0250CommonLogic;
import business.servlet.NLCL0250.constant.NLCL0250Constant;

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
public class NLCL0250Scrn00_OpenWin_InvMaterial extends S21CommonHandler {

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

        ZYPTableUtil.clear(scrnMsg.P);
        int i = 0;

        if (ZYPCommonFunc.hasValue(scrnMsg.xxMdseSrchTxt_H1) && scrnMsg.xxMdseSrchTxt_H1.getValue().length() > 16) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.xxMdseSrchTxt_H1.getValue().substring(0, 16));

        } else {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.xxMdseSrchTxt_H1);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, NLCL0250Constant.ITEM_SEARCH_AL);
        scrnMsg.P.setValidCount(i);

        Object[] params = NLCL0250CommonLogic.toArray_popup(scrnMsg.P, i);
        setArgForSubScreen(params);
    }
}
