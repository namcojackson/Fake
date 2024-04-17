/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1240;

import static business.servlet.NPAL1240.common.NPAL1240CommonLogic.copyScrnMsgToBizMsgForSearch;
import static business.servlet.NPAL1240.common.NPAL1240CommonLogic.deactivateApplicationButtons;
import static business.servlet.NPAL1240.common.NPAL1240CommonLogic.deactivateButton;
import static business.servlet.NPAL1240.common.NPAL1240CommonLogic.initializeCommonButtons;
import static business.servlet.NPAL1240.common.NPAL1240CommonLogic.protectAllInputFields;
import static business.servlet.NPAL1240.common.NPAL1240CommonLogic.setDisplayName;
import static business.servlet.NPAL1240.constant.NPAL1240Constant.INPUT_PARAM_INDEX_ASL_QLFY_RELN_LIST;
import static business.servlet.NPAL1240.constant.NPAL1240Constant.INPUT_PARAM_INDEX_GLBL_CMPY_CD;
import static business.servlet.NPAL1240.constant.NPAL1240Constant.INPUT_PARAM_INDEX_MODE;
import static business.servlet.NPAL1240.constant.NPAL1240Constant.MAX_NUM_INPUT_PARAM;
import static business.servlet.NPAL1240.constant.NPAL1240Constant.MODE_INQUIRY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1240.NPAL1240CMsg;
import business.servlet.NPAL1240.constant.NPAL1240Constant.BTN_APP;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID   : NPAL1240 Qualifier Setup
 * Function Name : The business process for Initialization.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/25   CITS            M.Ouchi         Create          N/A
 *</pre>
 */
public class NPAL1240_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1240BMsg scrnMsg = (NPAL1240BMsg) bMsg;

        // sets the parameters from previous screen.
        Serializable args = getArgForSubScreen();
        if (args instanceof Object[]) {
            setInputParameters(scrnMsg, (Object[]) args);
        }

        return copyScrnMsgToBizMsgForSearch(scrnMsg);
    }

    /**
     * <p>
     * Sets the input parameters.
     * </p>
     * @param scrnMsg BMsg
     * @param params input parameters.
     */
    private void setInputParameters(NPAL1240BMsg scrnMsg, Object[] params) {

        if (params.length < MAX_NUM_INPUT_PARAM) {
            return;
        }

        // 1 : Mode.(*)
        if (params[INPUT_PARAM_INDEX_MODE] instanceof EZDBStringItem) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxMd, (EZDBStringItem) params[INPUT_PARAM_INDEX_MODE]);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxMd, (String) params[INPUT_PARAM_INDEX_MODE]);
        }

        // 2 : Global Company Code.(*)
        if (params[INPUT_PARAM_INDEX_GLBL_CMPY_CD] instanceof EZDBStringItem) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, (EZDBStringItem) params[INPUT_PARAM_INDEX_GLBL_CMPY_CD]);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, (String) params[INPUT_PARAM_INDEX_GLBL_CMPY_CD]);
        }

        // 3 : ASL Qualifier Relation List.
        if (params[INPUT_PARAM_INDEX_ASL_QLFY_RELN_LIST] instanceof EZDMsgArray) {
            EZDMsg.copy((EZDMsgArray) params[INPUT_PARAM_INDEX_ASL_QLFY_RELN_LIST], "Q", scrnMsg.R, "");
            for (int index = 0; index < scrnMsg.R.getValidCount(); index++) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).aslQlfyRelnPk_A, scrnMsg.R.no(index).aslQlfyRelnPk);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).aslQlfyTpCd_A, scrnMsg.R.no(index).aslQlfyTpCd);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).aslQlfyRefCd_A, scrnMsg.R.no(index).aslQlfyRefCd);
                scrnMsg.A.setValidCount(index + 1);
            }

        } else if (params[INPUT_PARAM_INDEX_ASL_QLFY_RELN_LIST] instanceof List) {
            List<?> paramList = (List<?>) params[INPUT_PARAM_INDEX_ASL_QLFY_RELN_LIST];
            int index = 0;
            for (Object param : paramList) {
                if (param instanceof Object[]) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).aslQlfyRelnPk_A, new BigDecimal((String) ((Object[]) param)[0]));
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).aslQlfyTpCd_A, (String) ((Object[]) param)[1]);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).aslQlfyTpCd_A, (String) ((Object[]) param)[2]);
                }
            }
            scrnMsg.A.setValidCount(index);
        } else {
            // Do nothing.
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1240BMsg scrnMsg = (NPAL1240BMsg) bMsg;
        EZDMsg.copy((NPAL1240CMsg) cMsg, null, scrnMsg, null);

        // initializes the common buttons
        initializeCommonButtons(scrnMsg, this);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            // deactivates all application buttons.
            deactivateApplicationButtons(scrnMsg, this);
            return;
        }

        if (MODE_INQUIRY.equals(scrnMsg.xxMd.getValue())) {
            // protects the input fields.
            protectAllInputFields(scrnMsg);

            // deactivates all application buttons.
            deactivateApplicationButtons(scrnMsg, this);

        } else {
            if (scrnMsg.A.getValidCount() == 0) {
                // deactivates "Delete Row" button.
                deactivateButton(BTN_APP.DELETE_ROW, this);
            } else {
                // focus on ASL Qualifier Type Code.
                scrnMsg.setFocusItem(scrnMsg.A.no(0).aslQlfyTpCd_A);
            }
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        setDisplayName((NPAL1240BMsg) bMsg);
    }
}
