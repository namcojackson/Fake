/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1550;

import static business.servlet.NWAL1550.constant.NWAL1550Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1550.NWAL1550CMsg;
import business.servlet.NWAL1550.common.NWAL1550CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1550_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         S.Ohki          Create          N/A
 * 2017/02/27   Fujitsu         T.Yoshida       Update          S21_NA#16035
 *</pre>
 */
public class NWAL1550_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1550BMsg scrnMsg = (NWAL1550BMsg) bMsg;
        NWAL1550CMsg bizMsg = new NWAL1550CMsg();

        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 1) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, (EZDBStringItem) params[0]);
        } else {
            return null;
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1550BMsg scrnMsg = (NWAL1550BMsg) bMsg;
        NWAL1550CMsg bizMsg = (NWAL1550CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        
        NWAL1550CommonLogic.initControlFields(scrnMsg);
        NWAL1550CommonLogic.initCmnBtnProp(this);
        NWAL1550CommonLogic.setIncorrectBackGroundColor(scrnMsg);
        NWAL1550CommonLogic.setControlFields(this, scrnMsg);
        NWAL1550CommonLogic.setProtectByAuthority(this, scrnMsg); // S21_NA#16035 Add

        if (ZYPCommonFunc.hasValue(scrnMsg.diChkVrsnNum_SL)) {
            scrnMsg.cpoOrdNum.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).diChkNm_A.setInputProtected(true);
            scrnMsg.A.no(i).diChkLvlNm_A.setInputProtected(true);
            scrnMsg.A.no(i).xxLineNum_A.setInputProtected(true);
            scrnMsg.A.no(i).diChkErrTxt_A.setInputProtected(true);
        }

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1550BMsg scrnMsg = (NWAL1550BMsg) bMsg;

        scrnMsg.cpoOrdNum.setNameForMessage("Order Number");
        scrnMsg.diChkVrsnNum_SL.setNameForMessage("Version Number");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setNameForMessage("Accept");
        }
    }
}
