/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3130;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3130.NLBL3130CMsg;
import business.servlet.NLBL3130.common.NLBL3130CommonLogic;
import business.servlet.NLBL3130.constant.NLBL3130Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/18/2015   CITS            M.Ito           Create          N/A
 * 01/12/2018   CITS            T.Tokutomi      Update          QC#18460_Sol#087
 * 01/23/2018   CITS            K.Ogino         Update          QC#23044
 *</pre>
 */
public class NLBL3130_INIT extends S21INITCommonHandler implements NLBL3130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3130BMsg scrnMsg = (NLBL3130BMsg) bMsg;
        NLBL3130CMsg bizMsg = new NLBL3130CMsg();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;

            EZDBStringItem param0 = (EZDBStringItem) params[0];
            ZYPEZDItemValueSetter.setValue(scrnMsg.soNum_H1, param0);
            // QC#23044
            ZYPEZDItemValueSetter.setValue(scrnMsg.rwsNum_H1, param0);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd_G1, this.getUserProfileService().getGlobalCompanyCode());

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3130BMsg scrnMsg = (NLBL3130BMsg) bMsg;
        NLBL3130CMsg bizMsg = (NLBL3130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3130CommonLogic.cntrlScrnItemDispInit(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.trxHdrNum_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        NLBL3130CommonLogic.setNameForMessage((NLBL3130BMsg) arg0);
    }
}
