/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0060;

import static business.servlet.NWCL0060.constant.NWCL0060Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWCL0060.NWCL0060CMsg;
import business.servlet.NWCL0060.common.NWCL0060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.mail.ZYPMail;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/25   Fujitsu         M.Nakamura      Create          N/A
 * 2020/02/14   Fujitsu         C.Hara          Update          QC#55547
 *</pre>
 */
public class NWCL0060Scrn00_SendMail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0060BMsg scrnMsg = (NWCL0060BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.emlAddr_H1)) {
            scrnMsg.emlAddr_H1.setErrorInfo(1, "ZZM9000E", new String[] {scrnMsg.emlAddr_H1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.emlAddr_H1);
        // 2020/02/14 QC#55547 Add Start
        } else {
            boolean addrErr = true;
            String noSpace = scrnMsg.emlAddr_H1.getValue().replaceAll(" ", "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.emlAddr_H1, noSpace);
            String[] mlAddrList = scrnMsg.emlAddr_H1.getValue().split(",");
            for (int i = 0; i < mlAddrList.length; i++) {
                addrErr = ZYPMail.checkEmailFormat(mlAddrList[i]);
                if (!addrErr) {
                    scrnMsg.emlAddr_H1.setErrorInfo(1, "NWCM0163E", new String[] {mlAddrList[i]});
                    scrnMsg.addCheckItem(scrnMsg.emlAddr_H1);
                    break;
                }
            }
        // 2020/02/14 QC#55547 Add End
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxArMlBodyTmplTxt_H1)) {
            scrnMsg.xxArMlBodyTmplTxt_H1.setErrorInfo(1, "ZZM9000E", new String[] {scrnMsg.xxArMlBodyTmplTxt_H1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.xxArMlBodyTmplTxt_H1);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0060BMsg scrnMsg = (NWCL0060BMsg) bMsg;
        NWCL0060CMsg bizMsg = new NWCL0060CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0060BMsg scrnMsg = (NWCL0060BMsg) bMsg;
        NWCL0060CMsg bizMsg  = (NWCL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.emlAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.mlSubjTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxArMlBodyTmplTxt_H1);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.setMessageInfo("ZZM8100I");
        NWCL0060CommonLogic.setScreenControl(scrnMsg, true, this);
    }
}
