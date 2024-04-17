/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZVL0020;

import static business.servlet.ZZVL0020.constant.ZZVL0020Constant.BIZ_ID;
import static business.servlet.ZZVL0020.constant.ZZVL0020Constant.GLBL_CMPY_CD;
import static business.servlet.ZZVL0020.constant.ZZVL0020Constant.ROLE_NM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZVL0020.ZZVL0020CMsg;
import business.servlet.ZZVL0020.common.ZZVL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/26   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0020_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZVL0020BMsg scrnMsg = (ZZVL0020BMsg) bMsg;

        // security violation check of this screen.
        this.checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZVL0020BMsg scrnMsg = (ZZVL0020BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.C);
        scrnMsg.xxPageShowFromNum_1.clear();
        scrnMsg.xxPageShowToNum_1.clear();
        scrnMsg.xxPageShowOfNum_1.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd_1, getGlobalCompanyCode());

        ZZVL0020CMsg bizMsg = new ZZVL0020CMsg();
        bizMsg.setBusinessID("ZZVL0020");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

//        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZVL0020BMsg scrnMsg = (ZZVL0020BMsg) bMsg;
        ZZVL0020CMsg bizMsg  = (ZZVL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZVL0020CommonLogic.dspScrn00(scrnMsg, this);

        scrnMsg.setFocusItem(scrnMsg.roleNm_1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        ZZVL0020BMsg scrnMsg = (ZZVL0020BMsg) arg0;

        scrnMsg.glblCmpyCd_1.setNameForMessage(GLBL_CMPY_CD);
        scrnMsg.roleNm_1.setNameForMessage(ROLE_NM);
    }
}
