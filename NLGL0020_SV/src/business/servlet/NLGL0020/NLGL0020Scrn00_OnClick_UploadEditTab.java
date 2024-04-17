/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0020;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0020.NLGL0020CMsg;
import business.servlet.NLGL0020.common.NLGL0020CommonLogic;
import business.servlet.NLGL0020.constant.NLGL0020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update      Defect No
 * ------------------------------------------------------------------------------------
 * 08/30/2013     CSAI            N.Sekine          Create             MW Replace Initial
 *</pre>
 */
public class NLGL0020Scrn00_OnClick_UploadEditTab extends S21CommonHandler implements NLGL0020Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;

        List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, FIELD_NAME_XXCHKBOX_A1, ZYPConstant.CHKBOX_ON_Y);

        if (outGetSelectedRows.isEmpty()) {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(FLD_VALUE_INT_1, NLGM0036E);
            }
        }

        if (outGetSelectedRows.size() > 1) {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                    scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(FLD_VALUE_INT_1, NLGM0035E);
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

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
        NLGL0020CMsg bizMsg = NLGL0020CommonLogic.setRequestData_NLGL0020Scrn00_Function_20();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
        NLGL0020CMsg bizMsg = (NLGL0020CMsg) cMsg;

        if ((bizMsg.getMessageInfo() == null) || (FLD_VALUE_BLANK.equals(bizMsg.getMessageInfo().getMessage()))) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            NLGL0020CommonLogic.commonBtnControl_NLGL0020Scrn00_UPD_TAB(this);
            NLGL0020CommonLogic.inputFieldControl_NLGL0020Scrn00_Header_Protect(this, scrnMsg);
            NLGL0020CommonLogic.inputFieldControl_NLGL0020Scrn00_Upload_Detail_Protect(scrnMsg);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ID_UPD);
        }
    }
}
