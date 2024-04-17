/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0060;

import static business.servlet.NWCL0060.constant.NWCL0060Constant.BIZ_ID;
import static business.servlet.NWCL0060.constant.NWCL0060Constant.FROM_EML_ADDR_DEF;
import static business.servlet.NWCL0060.constant.NWCL0060Constant.NWCL0060_PARAMS_LENGTH;
import static business.servlet.NWCL0060.constant.NWCL0060Constant.NFDL0100_PARAMS_LENGTH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0060.NWCL0060CMsg;
import business.servlet.NWCL0060.common.NWCL0060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Mail Entry.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/25   Fujitsu         M.Nakamura      Create          N/A
 * 2022/03/31   CITS            A.Cullano       Update          QC#59828
 * 2023/04/07   Hitachi         S.Nakatani      Update          QC#61270
 *</pre>
 */
public class NWCL0060_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0060BMsg scrnMsg = (NWCL0060BMsg) bMsg;
        // START 2023/04/07 S.Nakatani [QC#61270,ADD]
        NWCL0060CMsg bizMsg = new NWCL0060CMsg();
        // END 2023/04/07 S.Nakatani [QC#61270,ADD]

        // IN Parameter -Get
        Object[] params = (Object[]) getArgForSubScreen();
//CSA MOD Start
//        final int prmLength = 2;
        // START 2023/04/07 S.Nakatani [QC#61270,MOD]
//        final int prmLength = 6;
//CSA MOD End
//        if (params != null && params.length == 6) {
        if(params != null && (params.length == NWCL0060_PARAMS_LENGTH || params.length == NFDL0100_PARAMS_LENGTH)){
        // END 2023/04/07 S.Nakatani [QC#61270,MOD]
            EZDMsg.copy((EZDMsgArray) params[0], (String) params[1], scrnMsg.A, "A1");
            // START 2023/04/07 S.Nakatani [QC#61270,ADD]
            if (params.length == NFDL0100_PARAMS_LENGTH){
                ZYPEZDItemValueSetter.setValue(scrnMsg.conslInvFlg_H1,(String) params[6]);
            }
            // END 2023/04/07 S.Nakatani [QC#61270,ADD]
        } else {
            scrnMsg.setMessageInfo("NWCM0094E", new String[] {scrnMsg.emlAddr_H1.getNameForMessage()});
            return null;
        }

        // START 2023/04/07 S.Nakatani [QC#61270,DEL]
//        NWCL0060CMsg bizMsg = new NWCL0060CMsg();
        // END 2023/04/07 S.Nakatani [QC#61270,DEL]
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0060BMsg scrnMsg = (NWCL0060BMsg) bMsg;
        NWCL0060CMsg bizMsg  = (NWCL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.emlAddr_H1);
        scrnMsg.putErrorScreen();

        NWCL0060CommonLogic.initCmnBtnProp(this);
        NWCL0060CommonLogic.setScreenControl(scrnMsg, false, this);

        // START 2022/03/31 A.Cullano [QC#59828, ADD]
        scrnMsg.xxRadioBtn_H1.setValue(FROM_EML_ADDR_DEF);
        // END 2022/03/31 A.Cullano [QC#59828, ADD]

        scrnMsg.setFocusItem(scrnMsg.xxMlCmntTxt_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWCL0060BMsg scrnMsg = (NWCL0060BMsg) bMsg;

        // Header
        // START 2022/03/31 A.Cullano [QC#59828, ADD]
        scrnMsg.xxRadioBtn_H1.setNameForMessage("From Address");
        scrnMsg.fromEmlAddr_H1.setNameForMessage("Your Email Address");
        scrnMsg.fromEmlAddr_H2.setNameForMessage("Business Area Email Address");
        // END 2022/03/31 A.Cullano [QC#59828, ADD]
        scrnMsg.emlAddr_H1.setNameForMessage("To Address");
        scrnMsg.mlSubjTxt_H1.setNameForMessage("Subject");
        scrnMsg.xxArMlBodyTmplTxt_H1.setNameForMessage("Attachment");
        scrnMsg.xxMlCmntTxt_H1.setNameForMessage("Comments");

    }

}
