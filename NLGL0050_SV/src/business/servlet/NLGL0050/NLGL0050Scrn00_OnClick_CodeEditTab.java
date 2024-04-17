/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0050;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0050.NLGL0050CMsg;
import business.servlet.NLGL0050.common.NLGL0050CommonLogic;
import business.servlet.NLGL0050.constant.NLGL0050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Ship Via Mapping from WMS to HOST
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/16   CSAI            Y.Miyauchi      Create          MW Replace Initial
 *</pre>
 */
public class NLGL0050Scrn00_OnClick_CodeEditTab extends S21CommonHandler implements NLGL0050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0050BMsg scrnMsg = (NLGL0050BMsg) bMsg;

        List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, FIELD_NAME_XXCHKBOX_A1, ZYPConstant.CHKBOX_ON_Y);

        if (outGetSelectedRows.isEmpty()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NLGM0036E);
            }
        }

        if (outGetSelectedRows.size() > 1) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                    scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NLGM0035E);
                }
            }
        }
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0050BMsg scrnMsg = (NLGL0050BMsg) bMsg;
        NLGL0050CMsg bizMsg = NLGL0050CommonLogic.setRequestData_NLGL0050Scrn00_Function_20();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0050BMsg scrnMsg = (NLGL0050BMsg) bMsg;
        NLGL0050CMsg bizMsg = (NLGL0050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode()) && scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            return;
        }

        NLGL0050CommonLogic.inputFieldControl_NLGL0050Scrn00_Edit(this, scrnMsg);
        NLGL0050CommonLogic.commonBtnControl_NLGL0050Scrn00_CMN_EDIT(this);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ID_EDIT);
    }
}
