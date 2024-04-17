/**
 * <pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0010;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0010.NLGL0010CMsg;
import business.servlet.NLGL0010.common.NLGL0010CommonLogic;
import business.servlet.NLGL0010.constant.NLGL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * SO Maintenance
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 02/06/2014     CSAI            M.Shimamura      Create            MW Replace Initial
 *</pre>
 */
public class NLGL0010Scrn00_CMN_Delete  extends S21CommonHandler implements NLGL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {


        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;

        List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.O, FIELD_NAME_XXCHKBOX_O1, ZYPConstant.CHKBOX_ON_Y);

        if (outGetSelectedRows.isEmpty()) {

            for (int i = 0; i < scrnMsg.O.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.O.no(i).xxChkBox_O1);
                scrnMsg.O.no(i).xxChkBox_O1.setErrorInfo(FLD_VALUE_INT_1, NLGM0036E);
            }
        }

        int newRecCnt = 0;

        for (int i = 0; i < scrnMsg.O.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.O.no(i).ezInTime_O1)) {
                newRecCnt++;
            }
        }

        if (newRecCnt == 0) {
            scrnMsg.setMessageInfo(NLGM0036E);
        }

        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;
        NLGL0010CMsg bizMsg = NLGL0010CommonLogic.setScrnBizFun_40();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;
        NLGL0010CMsg bizMsg = (NLGL0010CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode()) && scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_SO_UPLD_EDT);
        NLGL0010CommonLogic.busHdrControl_Enabel(this, scrnMsg);
        NLGL0010CommonLogic.busUPDControl_DataList(scrnMsg);
        NLGL0010CommonLogic.commonBtnControl_Tab_SO_UPL(this);
    }
}
