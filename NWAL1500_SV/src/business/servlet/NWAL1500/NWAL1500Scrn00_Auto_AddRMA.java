/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CONFIG_ID;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SERIAL_NUM;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0504E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0671E;

import java.util.List;

import parts.common.EZDBMsg;
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
 *</pre>
 */
public class NWAL1500Scrn00_Auto_AddRMA extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        List<Integer> checkConfList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
        List<Integer> checkLineList = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);

        if (checkConfList.size() + checkLineList.size() == 0) {
            scrnMsg.setMessageInfo(NWAM0504E);
            throw new EZDFieldErrorException();
        }

        if (checkConfList.size() > 0) {
            for (int checkConf : checkConfList) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(checkConf).svcConfigMstrPk_RC)) {
                    scrnMsg.C.no(checkConf).svcConfigMstrPk_RC.setErrorInfo(1, NWAM0671E, new String[] {MSG_PARAM_CONFIG_ID });
                }
                scrnMsg.addCheckItem(scrnMsg.C.no(checkConf).svcConfigMstrPk_RC);
            }
        }

        if (checkLineList.size() > 0) {
            for (int checkLine : checkLineList) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(checkLine).serNum_RL)) {
                    scrnMsg.D.no(checkLine).serNum_RL.setErrorInfo(1, NWAM0671E, new String[] {MSG_PARAM_SERIAL_NUM });
                }
                scrnMsg.addCheckItem(scrnMsg.D.no(checkLine).serNum_RL);
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).svcConfigMstrPk_RC);
        }
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.D.no(i).rtlWhNm_RL);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).rtlSwhNm_RL);
        }
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);
        // S21_NA#1634NWAL1500CommonLogic.controlMdseField(scrnMsg);

        // 2016/01/12 S21_NA#2990 Mod Start
        // scrnMsg.setFocusItem(scrnMsg.C.no(scrnMsg.xxCellIdx.getValueInt()).xxChkBox_RC);
        List<Integer> checkConfList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
        List<Integer> checkLineList = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
        int selIdx = -1;
        if (checkConfList.size() > 0) {
            selIdx = checkConfList.get(0);
        }
        if (checkLineList.size() > 0) {
            String dsOrdPosnNum = scrnMsg.D.no(checkLineList.get(0)).dsOrdPosnNum_RL.getValue();
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                if (dsOrdPosnNum.equals(scrnMsg.C.no(i).dsOrdPosnNum_RC.getValue())) {
                    if (selIdx > -1 && selIdx > i) {
                        selIdx = i;
                    }
                    break;
                }
            }
        }
        if (selIdx == -1) {
            selIdx = 0;
        }
        scrnMsg.setFocusItem(scrnMsg.C.no(selIdx).xxChkBox_RC);
        // 2016/01/12 S21_NA#2990 Add End
    }
}
