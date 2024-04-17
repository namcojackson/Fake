/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.BIZ_ID;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.FUNC_CD_SRCH;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.TAB_ASG;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.TAB_DTL;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.TAB_FIN;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLEL0060.NLEL0060CMsg;
import business.servlet.NLEL0060.common.NLEL0060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060Scrn00_Adjust
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/13   Hitachi         J.Kim           Update          QC#6581
 * 2016/04/28   Hitachi         J.Kim           Update          QC#7650
 * 2016/09/14   Fujitsu         C.Tanaka        Update          QC#12697
 *</pre>
 */
public class NLEL0060Scrn00_Adjust extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        String tab = scrnMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxBtnFlg_AA, ZYPConstant.FLG_ON_Y);
        } else if (TAB_ASG.equals(tab)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxBtnFlg_BA, ZYPConstant.FLG_ON_Y);
        } else if (TAB_FIN.equals(tab)) {
            scrnMsg.xxBtnFlg_CM.clear();
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxBtnFlg_CA, ZYPConstant.FLG_ON_Y);
        }

        NLEL0060CommonLogic.clearErrMsg(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        NLEL0060CMsg bizMsg = new NLEL0060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;
        NLEL0060CMsg bizMsg = (NLEL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLEL0060CommonLogic.addCheckItemForTab(scrnMsg);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NLEL0060CommonLogic.ctrlCmnSubBtnProp(this, scrnMsg);
    }
}
