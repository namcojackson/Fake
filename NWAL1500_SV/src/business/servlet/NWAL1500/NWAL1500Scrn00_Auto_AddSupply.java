/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_MDL;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0671E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/13   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/18   Fujitsu         T.Ishii         Update          QC#1634
 * 2016/02/22   Fujitsu         S.Ohki          Update          QC#1866
 *</pre>
 */
public class NWAL1500Scrn00_Auto_AddSupply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);

        // 2016/02/22 S21_NA#1866 Mod Start
        for (int i=0; i<scrnMsg.A.getValidCount(); i++) {
            if (checkList.size() == 0 || ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_LC.getValue())) {
                EZDBStringItem mdlDescTxt = scrnMsg.A.no(i).mdlDescTxt_LC;

                if (!ZYPCommonFunc.hasValue(mdlDescTxt)) {
                    mdlDescTxt.setErrorInfo(1, NWAM0671E, new String[] {MSG_PARAM_MDL });
                    scrnMsg.addCheckItem(mdlDescTxt);
                    scrnMsg.putErrorScreen();
                }
            }
        }
        // 2016/02/22 S21_NA#1866 Mod Start
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);
        // S21_NA#1634NWAL1500CommonLogic.controlMdseField(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).xxChkBox_LC);
    }
}
