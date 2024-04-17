/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0300;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSBL0300.constant.NSBL0300Constant.*;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0300.common.NSBL0300CommonLogic;
import business.db.SVC_SKILL_LVLTMsgArray;
import business.db.SVC_SKILL_LVL_GRPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * Skill Level Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         K.Kasai         Create          N/A
 * 2017/01/25   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSBL0300BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0300CMsg cMsg = (NSBL0300CMsg) arg0;
        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0300_INIT".equals(screenAplID)) {
                doProcess_NSBL0300_INIT(cMsg);
            } else if ("NSBL0300Scrn00_Search".equals(screenAplID)) {
                doProcess_NSBL0300Scrn00_Search(cMsg);
            } else if ("NSBL0300Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0300Scrn00_CMN_Submit(cMsg);
            } else if ("NSBL0300Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSBL0300Scrn00_CMN_Clear(cMsg);
            // START 2017/01/25 K.Ochiai [QC#16331,ADD]
            } else if ("NSBL0300Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSBL0300Scrn00_CMN_Reset(cMsg);
            // END 2017/01/25 K.Ochiai [QC#16331,ADD]
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSBL0300_INIT(NSBL0300CMsg cMsg) {

        NSBL0300CommonLogic.clearMsg(cMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
        NSBL0300CommonLogic.createPullDown(cMsg);
    }

    private void doProcess_NSBL0300Scrn00_Search(NSBL0300CMsg cMsg) {

        getSearchData(cMsg, false);
    }

    private void doProcess_NSBL0300Scrn00_CMN_Submit(NSBL0300CMsg cMsg) {

        getSearchData(cMsg, true);
    }

    private void doProcess_NSBL0300Scrn00_CMN_Clear(NSBL0300CMsg cMsg) {

        doProcess_NSBL0300_INIT(cMsg);
    }

    // START 2017/01/25 K.Ochiai [QC#16331,ADD]
    private void doProcess_NSBL0300Scrn00_CMN_Reset(NSBL0300CMsg cMsg) {

        getSearchData(cMsg, false);
    }
    // END 2017/01/25 K.Ochiai [QC#16331,ADD]

    private void getSearchData(NSBL0300CMsg cMsg, boolean isSubmit) {
        //get Header Info
        SVC_SKILL_LVL_GRPTMsg outSvcSkillTpTMsg = NSBL0300CommonLogic.getSvcSkillLvlGrpInfo(cMsg);
        if (outSvcSkillTpTMsg == null) {
            cMsg.setMessageInfo(NZZM0000E);
            return;
        }
        //get Detail Info
        SVC_SKILL_LVLTMsgArray outSvcSkillLvlTMsgArray = NSBL0300CommonLogic.getSvcSkillLvlInfo(cMsg);
        if (outSvcSkillLvlTMsgArray.getValidCount() == 0 && !isSubmit) {
            cMsg.setMessageInfo(ZZZM9005W);
        }
        if (outSvcSkillLvlTMsgArray.getValidCount() > cMsg.A.length() && !isSubmit) {
            cMsg.setMessageInfo(NZZM0001W);
        }
        NSBL0300CommonLogic.setHeaderInfo(cMsg, outSvcSkillTpTMsg);
        NSBL0300CommonLogic.setDetailInfo(cMsg, outSvcSkillLvlTMsgArray);
    }

}
