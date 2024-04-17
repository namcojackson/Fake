/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0270;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0270.NLCL0270CMsg;
import business.servlet.NLCL0270.common.NLCL0270CommonLogic;
import business.servlet.NLCL0270.constant.NLCL0270Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NLCL0270_INIT extends S21INITCommonHandler implements NLCL0270Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0270BMsg scrnMsg = (NLCL0270BMsg) bMsg;
        NLCL0270CMsg bizMsg = new NLCL0270CMsg();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            EZDBStringItem param0 = (EZDBStringItem) params[0];
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            EZDBStringItem param2 = (EZDBStringItem) params[2];
            EZDBStringItem param3 = (EZDBStringItem) params[3];
            EZDBStringItem param4 = (EZDBStringItem) params[4];
            EZDBStringItem param5 = (EZDBStringItem) params[5];

            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_H1, param0);
            ZYPEZDItemValueSetter.setValue(scrnMsg.stkStsCd_H1, param1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H1, param2);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_H1, param3);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxReadOnlyFlg_G1, param4);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_G1, param5);

            ZYPTableUtil.clear(scrnMsg.A);
            scrnMsg.xxPageShowFromNum.clear();
            scrnMsg.xxPageShowToNum.clear();
            scrnMsg.xxPageShowOfNum.clear();
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd_G1, this.getUserProfileService().getGlobalCompanyCode());

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0270BMsg scrnMsg = (NLCL0270BMsg) bMsg;
        NLCL0270CMsg bizMsg = (NLCL0270CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL0270CommonLogic.cntrlScrnItemDispInit(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.mdseCd_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NLCL0270CommonLogic.setNameForMessage((NLCL0270BMsg) arg0);

    }
}
