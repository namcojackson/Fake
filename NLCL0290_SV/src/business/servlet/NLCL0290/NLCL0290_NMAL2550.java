/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0290;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLCL0290.NLCL0290CMsg;
//import business.servlet.NLCL0290.constant.NLCL0290Constant;

import business.blap.NLCL0290.NLCL0290CMsg;
import business.servlet.NLCL0290.NLCL0290BMsg;
import business.servlet.NLCL0290.common.NLCL0290CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   CSAI            K.Lee           Create          N/A
 * 2018/04/06   CITS            K.Masaki        Update          QC#18472
 *</pre>
 */
public class NLCL0290_NMAL2550 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;
        // QC:18472 Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).xxScrItem130Txt_A, scrnMsg.P.no(5).xxPopPrm);
        // QC:18472 End

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC:18472 Start
        return null;
        // QC:18472 End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;

        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).xxScrItem130Txt_A);

    }
}
