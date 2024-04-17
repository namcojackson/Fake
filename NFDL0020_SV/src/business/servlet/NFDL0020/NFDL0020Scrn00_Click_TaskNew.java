/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFDL0020.NFDL0020CMsg;
//import business.servlet.NFDL0020.constant.NFDL0020Constant;

import business.servlet.NFDL0020.common.NFDL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_TASK_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/06/16   Hitachi         K.Kojima        Update          QC#10200
 * 2016/06/22   Hitachi         K.Kojima        Update          QC#10529
 * 2016/07/07   Hitachi         K.Kojima        Update          QC#10337
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2018/07/25   Hitachi         Y.Takeno        Update          QC#25767
 *</pre>
 */
public class NFDL0020Scrn00_Click_TaskNew extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        scrnMsg.cltTaskPk_GH.clear();
        // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
        //scrnMsg.cltTaskStsCd_GH.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskStsCd_GH, CLT_TASK_STS.OPEN);
        // END 2017/08/07 T.Tsuchida [QC#19576,MOD]
        scrnMsg.cltTaskTpCd_GH.clear();
        scrnMsg.cltTaskPrtyCd_GH.clear();
        // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
        //scrnMsg.cltTaskOwnrId_GH.clear();
        //// START 2016/07/07 K.Kojima [QC#10337,MOD]
        //// scrnMsg.xxPsnNm_G1.clear();
        //scrnMsg.cltPsnNm_G1.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskOwnrId_GH, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltPsnNm_G1, getContextUserInfo().getUserDetails().getUserName());
        // END 2017/08/07 T.Tsuchida [QC#19576,MOD]
        // END 2016/07/07 K.Kojima [QC#10337,MOD]
        scrnMsg.cratUsrId_GH.clear();
        scrnMsg.xxPsnNm_G2.clear();
        scrnMsg.cltTaskDescTxt_GH.clear();
        scrnMsg.cltTaskSubjTxt_GH.clear();
        scrnMsg.cltTaskRwsdDt_GH.clear();
        scrnMsg.cltTaskRwedDt_GH.clear();
        scrnMsg.cltTaskCtacNm_GH.clear();
        scrnMsg.cltTaskCtacPhoNum_GH.clear();
        scrnMsg.xxYesNoCd_GH.setValue("1");
        // START 2018/07/25 [QC#25767, ADD]
        scrnMsg.updUsrId_GH.clear();
        scrnMsg.xxPsnNm_G5.clear();
        scrnMsg.cltTaskUpdDt_GH.clear();
        // END   2018/07/25 [QC#25767, ADD]
        
        // START 2016/06/16 K.Kojima [QC#10200,DEL]
        // scrnMsg.cltTaskPk_GH.setInputProtected(false);
        // scrnMsg.cltTaskStsCd_GH.setInputProtected(false);
        // scrnMsg.cltTaskTpCd_GH.setInputProtected(false);
        // scrnMsg.cltTaskPrtyCd_GH.setInputProtected(false);
        // scrnMsg.cltTaskOwnrId_GH.setInputProtected(false);
        // scrnMsg.xxPsnNm_G1.setInputProtected(true);
        // scrnMsg.cratUsrId_GH.setInputProtected(true);
        // scrnMsg.xxPsnNm_G2.setInputProtected(true);
        // scrnMsg.cltTaskDescTxt_GH.setInputProtected(false);
        // scrnMsg.cltTaskSubjTxt_GH.setInputProtected(false);
        // scrnMsg.cltTaskRwsdDt_GH.setInputProtected(false);
        // scrnMsg.cltTaskRwedDt_GH.setInputProtected(false);
        // scrnMsg.cltTaskCtacNm_GH.setInputProtected(false);
        // scrnMsg.cltTaskCtacPhoNum_GH.setInputProtected(false);
        // END 2016/06/16 K.Kojima [QC#10200,DEL]

        // START 2016/06/16 K.Kojima [QC#10200,ADD]
        // START 2016/06/22 K.Kojima [QC#10529,MOD]
        // NFDL0020CommonLogic.setTaskInputProtectFalse(scrnMsg);
        NFDL0020CommonLogic.setTaskInputProtectFalse(this, scrnMsg);
        // END 2016/06/22 K.Kojima [QC#10529,MOD]
        // END 2016/06/16 K.Kojima [QC#10200,ADD]

        // START 2016/06/22 K.Kojima [QC#10529,ADD]
        scrnMsg.setFocusItem(scrnMsg.cltTaskTpCd_GH);
        // END 2016/06/22 K.Kojima [QC#10529,ADD]

    }
}
