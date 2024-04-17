/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0370E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0707E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0915E;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/01   Fujitsu         S.Takami        Create          S21_NA#19808
 *</pre>
 */
public class NWAL1500Scrn00_ConfigJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        checkJumpingConfigNum(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        if (hasJumpingConfig(scrnMsg)) {
            return null;
        } else {

            NWAL1500CMsg bizMsg = new NWAL1500CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg  = (NWAL1500CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        hasJumpingConfig(scrnMsg);

        NWAL1500CommonLogic.activeRegistrationButton(this, scrnMsg);
        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);
        NWAL1500CommonLogic.inactiveAddButton(this);
    }

    private boolean hasJumpingConfig(NWAL1500BMsg scrnMsg) {

        String currentTab = scrnMsg.xxDplyTab.getValue();
        if (S21StringUtil.isEquals(TAB_LINE_CONFIG, currentTab)) {
            String jumpingDsOrdPosnNum = scrnMsg.dsOrdPosnNum_AS.getValue();
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (S21StringUtil.isEquals(jumpingDsOrdPosnNum, scrnMsg.A.no(i).dsOrdPosnNum_LC.getValue())) {
                    scrnMsg.setFocusItem(scrnMsg.A.no(i).xxChkBox_LC);
                    return true;
                }
            }
        } else if (S21StringUtil.isEquals(TAB_RMA, currentTab)) {
            String jumpingDsOrdPosnNum = scrnMsg.dsOrdPosnNum_CS.getValue();
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                if (S21StringUtil.isEquals(jumpingDsOrdPosnNum, scrnMsg.C.no(i).dsOrdPosnNum_RC.getValue())) {
                    scrnMsg.setFocusItem(scrnMsg.C.no(i).xxChkBox_RC);
                    return true;
                }
            }
        }
        return false;
    }

    private void checkJumpingConfigNum(NWAL1500BMsg scrnMsg) {

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (!isConfigNumNumericCheckError(scrnMsg.dsOrdPosnNum_AS.getValue())) {
                int dsOrdPosnNum = Integer.parseInt(scrnMsg.dsOrdPosnNum_AS.getValue());
                int totalPosnNum = Integer.parseInt(scrnMsg.dsOrdPosnNum_AT.getValue());
                if(dsOrdPosnNum < 1){
                    scrnMsg.dsOrdPosnNum_AS.setErrorInfo(1, NWAM0370E);
                }
                if (dsOrdPosnNum > totalPosnNum) {
                    scrnMsg.dsOrdPosnNum_AS.setErrorInfo(1, NWAM0707E, new String[] {"Config#" });
                }
            } else {
                scrnMsg.dsOrdPosnNum_AS.setErrorInfo(1, NWAM0915E, new String[] {"Config#" });
            }
            scrnMsg.addCheckItem(scrnMsg.dsOrdPosnNum_AS);
        } else if (TAB_RMA.equals(dplyTab)) {
            if (!isConfigNumNumericCheckError(scrnMsg.dsOrdPosnNum_CS.getValue())) {
                int dsOrdPosnNum = Integer.parseInt(scrnMsg.dsOrdPosnNum_CS.getValue());
                int totalPosnNum = Integer.parseInt(scrnMsg.dsOrdPosnNum_CT.getValue());
                if(dsOrdPosnNum < 1){
                    scrnMsg.dsOrdPosnNum_CS.setErrorInfo(1, NWAM0370E);
                }
                if (dsOrdPosnNum > totalPosnNum) {
                    scrnMsg.dsOrdPosnNum_CS.setErrorInfo(1, NWAM0707E, new String[] {"Config#" });
                }
            }else{
                scrnMsg.dsOrdPosnNum_CS.setErrorInfo(1, NWAM0915E, new String[] {"Config#" });
            }
            scrnMsg.addCheckItem(scrnMsg.dsOrdPosnNum_CS);
        }
        scrnMsg.putErrorScreen();
    }

    private boolean isConfigNumNumericCheckError(String dsOrdPosnNum) {

        boolean rslt = false;
        try {
            Integer.parseInt(dsOrdPosnNum);
        } catch (Exception ex) {
            rslt = true;
        }
        return rslt;
    }
}
