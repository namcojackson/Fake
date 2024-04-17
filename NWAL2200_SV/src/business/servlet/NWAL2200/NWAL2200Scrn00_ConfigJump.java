/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.blap.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL2200.constant.NWAL2200Constant.TAB_RMA;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;
import business.servlet.NWAL2200.constant.NWAL2200Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/25   Fujitsu         Y.Kanefusa      Create          S21_NA#19808
 *</pre>
 */
public class NWAL2200Scrn00_ConfigJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, true, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (ZYPCommonFunc.isNumberCheck(scrnMsg.dsOrdPosnNum_DL.getValue())) {
                int dsOrdPosnNum = Integer.parseInt(scrnMsg.dsOrdPosnNum_DL.getValue());
                int totalPosnNum = Integer.parseInt(scrnMsg.dsOrdPosnNum_TL.getValue());
                if(dsOrdPosnNum < 1){
                    scrnMsg.dsOrdPosnNum_DL.setErrorInfo(1, NWAL2200Constant.NWAM0370E);
                }
                if (dsOrdPosnNum > totalPosnNum) {
                    scrnMsg.dsOrdPosnNum_DL.setErrorInfo(1, NWAL2200Constant.NWAM0707E, new String[] {"Config#" });
                }
            } else {
                scrnMsg.dsOrdPosnNum_DL.setErrorInfo(1, NWAL2200Constant.NWAM0915E, new String[] {"Config#" });
            }
            scrnMsg.addCheckItem(scrnMsg.dsOrdPosnNum_DL);
        } else if (TAB_RMA.equals(dplyTab)) {
            if (ZYPCommonFunc.isNumberCheck(scrnMsg.dsOrdPosnNum_DR.getValue())) {
                int dsOrdPosnNum = Integer.parseInt(scrnMsg.dsOrdPosnNum_DR.getValue());
                int totalPosnNum = Integer.parseInt(scrnMsg.dsOrdPosnNum_TR.getValue());
                if(dsOrdPosnNum < 1){
                    scrnMsg.dsOrdPosnNum_DR.setErrorInfo(1, NWAL2200Constant.NWAM0370E);
                }
                if (dsOrdPosnNum > totalPosnNum) {
                    scrnMsg.dsOrdPosnNum_DR.setErrorInfo(1, NWAL2200Constant.NWAM0707E, new String[] {"Config#" });
                }
            }else{
                scrnMsg.dsOrdPosnNum_DR.setErrorInfo(1, NWAL2200Constant.NWAM0915E, new String[] {"Config#" });
            }
            scrnMsg.addCheckItem(scrnMsg.dsOrdPosnNum_DR);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID("NWAL2200");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg  = (NWAL2200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL2200CommonLogicForScreenFields.setConditionForHeader(this, scrnMsg);
        NWAL2200CommonLogicForScreenFields.setConditionForCurrentTab(this, scrnMsg, scrnMsg.xxDplyTab.getValue());
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        String dsOrdPosnNum = bizMsg.dsOrdPosnNum_DL.getValue();

        //Focus Setting
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            dsOrdPosnNum = bizMsg.dsOrdPosnNum_DL.getValue();
            for(int i = 0; i < scrnMsg.A.getValidCount(); i++){
                if(S21StringUtil.isEquals(dsOrdPosnNum, scrnMsg.A.no(i).dsOrdPosnNum_LC.getValue())){
                    scrnMsg.setFocusItem(scrnMsg.A.no(i).xxChkBox_LC);
                    break;
                }
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            dsOrdPosnNum = bizMsg.dsOrdPosnNum_DR.getValue();
            for(int i = 0; i < scrnMsg.C.getValidCount(); i++){
                if(S21StringUtil.isEquals(dsOrdPosnNum, scrnMsg.C.no(i).dsOrdPosnNum_RC.getValue())){
                    scrnMsg.setFocusItem(scrnMsg.C.no(i).xxChkBox_RC);
                    break;
                }
            }
        }

    }
}
