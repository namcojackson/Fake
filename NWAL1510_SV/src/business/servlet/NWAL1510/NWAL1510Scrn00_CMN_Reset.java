/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import static business.servlet.NWAL1510.constant.NWAL1510Constant.BUSINESS_ID;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0662E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.ORD_POSN_NUM_VIEW_SELECT;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_0;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_1;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_2;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.TAB_INSTALL;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1510.NWAL1510CMsg;
import business.servlet.NWAL1510.common.NWAL1510CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Fujitsu         S.Ohki          Create          N/A
 * 2016/02/09   Fujitsu         S.Ohki          Update          S21_NA#1622
 * 2017/02/27   Fujitsu         T.Yoshida       Update          S21_NA#16035
 * 2018/07/20   Fujitsu         K.Ishizuka      Update          S21_NA#26188
 *</pre>
 */
public class NWAL1510Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;
        
        String origTab = scrnMsg.xxDplyTab.getValue(); // 2018/07/20 S21_NA#26188 Add
        scrnMsg.clear();
        scrnMsg.xxDplyTab.setValue(origTab); // 2018/07/20 S21_NA#26188 Add

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            setValue(scrnMsg.cpoOrdNum_H0, (EZDBStringItem) params[POP_PAR_0]);
            setValue(scrnMsg.configCatgCd_H0, (EZDBStringItem) params[POP_PAR_2]);

            if (params[POP_PAR_1] instanceof EZDMsgArray) {
                EZDMsg.copy((EZDMsgArray) params[POP_PAR_1], null, scrnMsg.H, null);
            } else {
                List<Object> paramList = (List<Object>) params[POP_PAR_1];

                if (paramList != null && paramList.size() > 0) {
                    int index = 0;
                    for (Object param : paramList) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(index++).dsOrdPosnNum_H1, (String) param);
                    }
                    scrnMsg.H.setValidCount(index);

                    if (index == 1) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum_H0, scrnMsg.H.no(0).dsOrdPosnNum_H1);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.configTpNm_LK, ORD_POSN_NUM_VIEW_SELECT);
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum_H0)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.configCatgCd_H0)) {
                    scrnMsg.setMessageInfo(NWAM0662E);
                }
            }
        }
        
        NWAL1510CMsg bizMsg = new NWAL1510CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        NWAL1510CommonLogic.initControlFields(this, scrnMsg);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;
        NWAL1510CMsg bizMsg = (NWAL1510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // scrnMsg.xxDplyTab.setValue(TAB_INSTALL); // 2018/07/20 S21_NA#26188 Del
        // 2018/07/19 S21_NA#26188 Add Start
        scrnMsg.xxEdtModeFlg_SS.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxEdtModeFlg_CT.setValue(ZYPConstant.FLG_ON_Y);
        // 2018/07/19 S21_NA#26188 Add End

        NWAL1510CommonLogic.setDelyIstlDispFlg(scrnMsg);
        NWAL1510CommonLogic.setNameForMessageDeryDisp(scrnMsg);
        NWAL1510CommonLogic.initCommonButton(this);
        NWAL1510CommonLogic.setTabProtect(this, scrnMsg);
        NWAL1510CommonLogic.setAppFracDigit(scrnMsg);
        NWAL1510CommonLogic.convTimeforScreen(scrnMsg, bizMsg);
        NWAL1510CommonLogic.setScrnTm(scrnMsg);
        NWAL1510CommonLogic.setProtectByAuthority(this, scrnMsg); // S21_NA#16035 Add

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum_H0);
    }
}
