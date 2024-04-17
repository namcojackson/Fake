/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0300.NLCL0300CMsg;
import business.servlet.NLCL0300.common.NLCL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   CSAI            K.Lee           Create          N/A
 * 2019/08/13   CITS            M.Naito         Update          QC#52185
 *</pre>
 */
public class NLCL0300Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;
        scrnMsg.clearErrorInfo();
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_H);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).ordQty_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlSwhCd_A);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;

        NLCL0300CMsg bizMsg = new NLCL0300CMsg();
        bizMsg.setBusinessID("NLCL0300");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;
        NLCL0300CMsg bizMsg  = (NLCL0300CMsg) cMsg;
        // START 2019/08/13 M.Naito [QC#52185,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.xxWrnSkipFlg) && ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue())) {
            scrnMsg.setMessageInfo(bizMsg.getMessageCode());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg, ZYPConstant.CHKBOX_ON_Y);
        } else {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        // END 2019/08/13 M.Naito [QC#52185,ADD]

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_H);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).ordQty_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlSwhCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rmvConfigFlg_A);
        }
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        NLCL0300CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());
    }
}
