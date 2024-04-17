/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0640;

import static business.servlet.NLCL0640.constant.NLCL0640Constant.BIZ_APP_ID;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.FUNCTION_CD_UPDATE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0640.NLCL0640CMsg;
import business.servlet.NLCL0640.common.NLCL0640CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0640 Tech PI Count
 * Function Name : Init
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/09/2016   CITS            Makoto Okigami  Create          N/A
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 *</pre>
 */
public class NLCL0640_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();

        // START 2018/12/11 T.Ogura [QC#28755,MOD]
//        if (params != null && params.length == 5) {
        if (params != null && params.length == 7) {
        // END   2018/12/11 T.Ogura [QC#28755,MOD]

            String param01 = (String) params[0];
            ZYPEZDItemValueSetter.setValue(scrnMsg.techTocCd, param01);
            String param02 = (String) params[1];
            ZYPEZDItemValueSetter.setValue(scrnMsg.techNm, param02);
            String param03 = (String) params[2];
            ZYPEZDItemValueSetter.setValue(scrnMsg.physInvtyDt, param03);
            String param04 = (String) params[3];
            ZYPEZDItemValueSetter.setValue(scrnMsg.physInvtyCtrlNm, param04);
            String param05 = (String) params[4];
            ZYPEZDItemValueSetter.setValue(scrnMsg.physInvtyNum, param05);
            // START 2018/12/11 T.Ogura [QC#28755,ADD]
            String param06 = (String) params[5];
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, param06);
            String param07 = (String) params[6];
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, param07);
            // END   2018/12/11 T.Ogura [QC#28755,ADD]
        }

        NLCL0640CMsg bizMsg = new NLCL0640CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;
        NLCL0640CMsg bizMsg  = (NLCL0640CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL0640CommonLogic.ctrlScrnItemDisp(this, scrnMsg);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.mdseCd);

    }

    @Override
    protected void setNameForMessage(EZDBMsg scrnMsg) {
        NLCL0640CommonLogic.setNameForMessage((NLCL0640BMsg) scrnMsg);
    }

}
