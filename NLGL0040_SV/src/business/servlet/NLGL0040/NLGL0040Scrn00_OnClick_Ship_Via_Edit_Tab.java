/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0040;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0040.NLGL0040CMsg;
import business.servlet.NLGL0040.common.NLGL0040CommonLogic;
import business.servlet.NLGL0040.constant.NLGL0040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Ship Via Mapping from HOST to WMS
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/12/2013     CSAI            N.Sekine          Create             MW Replace Initial
 *</pre>
 */
public class NLGL0040Scrn00_OnClick_Ship_Via_Edit_Tab extends S21CommonHandler implements NLGL0040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0040BMsg scrnMsg = (NLGL0040BMsg) bMsg;

        List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, FIELD_NAME_XXCHKBOX_D1, ZYPConstant.CHKBOX_ON_Y);

        if (outGetSelectedRows.isEmpty()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_D1);
                scrnMsg.A.no(i).xxChkBox_D1.setErrorInfo(1, NLGM0036E);
            }
        }

        if (outGetSelectedRows.size() > 1) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_D1.getValue())) {
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_D1);
                    scrnMsg.A.no(i).xxChkBox_D1.setErrorInfo(1, NLGM0035E);
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

        NLGL0040BMsg scrnMsg = (NLGL0040BMsg) bMsg;
        NLGL0040CMsg bizMsg = NLGL0040CommonLogic.setRequestData_NLGL0040Scrn00_Function_20();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0040BMsg scrnMsg = (NLGL0040BMsg) bMsg;
        NLGL0040CMsg bizMsg = (NLGL0040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ((bizMsg.getMessageInfo() == null) || (bizMsg.getMessageInfo().getMessage().equals(FLD_VALUE_BLANK))) {
            NLGL0040CommonLogic.inputFieldControl_NLGL0040Scrn00_Edit(this, scrnMsg);
            NLGL0040CommonLogic.commonBtnControl_NLGL0040Scrn00_CMN_EDIT(this);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ID_EDIT);
        }
    }
}
