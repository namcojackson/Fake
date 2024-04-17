/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3100;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3100.NLBL3100CMsg;
import business.servlet.NLBL3100.common.NLBL3100CommonLogic;
import business.servlet.NLBL3100.constant.NLBL3100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/13/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NLBL3100_INIT extends S21INITCommonHandler implements NLBL3100Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3100BMsg scrnMsg = (NLBL3100BMsg) bMsg;
        NLBL3100CMsg bizMsg = new NLBL3100CMsg();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            EZDBStringItem param0 = (EZDBStringItem) params[0];
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            EZDBStringItem param2 = (EZDBStringItem) params[2];
            EZDBStringItem param3 = (EZDBStringItem) params[3];
            EZDBStringItem param4 = (EZDBStringItem) params[4];
            EZDBStringItem param5 = (EZDBStringItem) params[5];
            EZDBStringItem param6 = (EZDBStringItem) params[6];
            EZDBStringItem param7 = (EZDBStringItem) params[7];
            EZDBStringItem param8 = (EZDBStringItem) params[8];
            EZDBStringItem param9 = (EZDBStringItem) params[9];

            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H1, param0);
            ZYPEZDItemValueSetter.setValue(scrnMsg.schdCoordPsnCd_H1, param1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.mgrPsnCd_H1, param2);
            ZYPEZDItemValueSetter.setValue(scrnMsg.supvPsnCd_H1, param3);
            ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_H1, param4);
            ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_H1, param5);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_H1, param6);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_H2, param7);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_H3, param8);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_H4, param9);

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

        NLBL3100BMsg scrnMsg = (NLBL3100BMsg) bMsg;
        NLBL3100CMsg bizMsg = (NLBL3100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3100CommonLogic.cntrlScrnItemDispInit(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NLBL3100CommonLogic.setNameForMessage((NLBL3100BMsg) arg0);

    }
}
