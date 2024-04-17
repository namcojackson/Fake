/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0110;

import static business.servlet.NSAL0110.constant.NSAL0110Constant.ARGS_CONTRACT_CATEGORY;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.ARGS_CONTRACT_NAME;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.ARGS_CONTRACT_NUM;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.ARGS_ACCOUNT_CODE;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.ARGS_CONTRACT_STATUS;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.ARGS_CONTRACT_DTL_TYPE;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.ARGS_DISPLAY_MODE;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.ARGS_MODEL_NAME;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.ARGS_CONTRACT_DTL_STATUS;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.ARGS_SERIAL_NUM;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.BUTTON_GUARD_CLEAR;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.BUTTON_GUARD_CLOSE;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.BUTTON_LABEL_CLEAR;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.BUTTON_LABEL_CLOSE;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.BUTTON_NAME_CLEAR;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.BUTTON_NAME_RETURN;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0110.NSAL0110CMsg;
import business.servlet.NSAL0110.common.NSAL0110CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 *
 * Contract Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/12   Hitachi         Y.Igarashi         Create          N/A
 * 2015/11/02   Hitachi         K.Kasai            Update          N/A
 *</pre>
 */
public class NSAL0110_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0110BMsg scrnMsg = (NSAL0110BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            setValue(scrnMsg.dsContrNum_SC, (EZDBStringItem) params[ARGS_CONTRACT_NUM]);
            setValue(scrnMsg.dsContrCtrlStsCd_HV, (EZDBStringItem) params[ARGS_CONTRACT_STATUS]);
            setValue(scrnMsg.dsContrNm_SC, (EZDBStringItem) params[ARGS_CONTRACT_NAME]);
            setValue(scrnMsg.dsContrCatgCd_SV, (EZDBStringItem) params[ARGS_CONTRACT_CATEGORY]);
            setValue(scrnMsg.sellToCustCd_SC, (EZDBStringItem) params[ARGS_ACCOUNT_CODE]);
            setValue(scrnMsg.dsContrDtlTpCd_SV, (EZDBStringItem) params[ARGS_CONTRACT_DTL_TYPE]);
            setValue(scrnMsg.dsContrCtrlStsCd_DV, (EZDBStringItem) params[ARGS_CONTRACT_DTL_STATUS]);
            setValue(scrnMsg.serNum_SC, (EZDBStringItem) params[ARGS_SERIAL_NUM]);
            setValue(scrnMsg.mdlNm_SC, (EZDBStringItem) params[ARGS_MODEL_NAME]);
            setValue(scrnMsg.xxScrItem10Txt_SV, (EZDBStringItem) params[ARGS_DISPLAY_MODE]);
        }
        NSAL0110CMsg bizMsg = new NSAL0110CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0110BMsg scrnMsg = (NSAL0110BMsg) bMsg;
        NSAL0110CMsg bizMsg  = (NSAL0110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initControl();

        NSAL0110CommonLogic.setBgColor(scrnMsg);
        NSAL0110CommonLogic.setFocusGrp(scrnMsg);
        NSAL0110CommonLogic.setProtected(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.dsContrNum_SC);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0110BMsg scrnMsg = (NSAL0110BMsg) bMsg;
        scrnMsg.dsContrNum_SC.setNameForMessage("Contract #");
        scrnMsg.svcContrBrCd_SC.setNameForMessage("Branch");
        scrnMsg.dsContrClsCd_SV.setNameForMessage("Contract Category");
        scrnMsg.dsContrNm_SC.setNameForMessage("Description");
        scrnMsg.sellToCustCd_SC.setNameForMessage("Account Code");
        scrnMsg.dsContrCatgCd_SV.setNameForMessage("Contract Type");
        scrnMsg.dsContrCtrlStsCd_HV.setNameForMessage("Contract Status");
        scrnMsg.dsContrDtlTpCd_SV.setNameForMessage("Detail type");
        scrnMsg.dsContrCtrlStsCd_DV.setNameForMessage("Detail Status");
        scrnMsg.serNum_SC.setNameForMessage("Mach Serial #");
        scrnMsg.mdlNm_SC.setNameForMessage("Mach Model Name");
        scrnMsg.xxScrItem10Txt_SV.setNameForMessage("Display Mode");
    }

    private void initControl() {
        setButtonProperties(BUTTON_NAME_CLEAR, BUTTON_GUARD_CLEAR, BUTTON_LABEL_CLEAR, 1, null);
        setButtonProperties(BUTTON_NAME_RETURN, BUTTON_GUARD_CLOSE, BUTTON_LABEL_CLOSE, 1, null);
    }
}

