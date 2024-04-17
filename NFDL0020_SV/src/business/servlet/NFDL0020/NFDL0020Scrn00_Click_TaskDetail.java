/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

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
 * 2018/07/25   Hitachi         Y.Takeno        Update          QC#25767
 *</pre>
 */
public class NFDL0020Scrn00_Click_TaskDetail extends S21CommonHandler {

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
        int selectIdx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskPk_GH, scrnMsg.G.no(selectIdx).cltTaskPk_GD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskStsCd_GH, scrnMsg.G.no(selectIdx).cltTaskStsCd_GD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskTpCd_GH, scrnMsg.G.no(selectIdx).cltTaskTpCd_GD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskPrtyCd_GH, scrnMsg.G.no(selectIdx).cltTaskPrtyCd_GD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskOwnrId_GH, scrnMsg.G.no(selectIdx).cltTaskOwnrId_GD);
        // START 2016/07/07 K.Kojima [QC#10337,MOD]
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_G1,
        // scrnMsg.G.no(selectIdx).xxPsnNm_G3);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltPsnNm_G1, scrnMsg.G.no(selectIdx).cltPsnNm_G3);
        // END 2016/07/07 K.Kojima [QC#10337,MOD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.cratUsrId_GH, scrnMsg.G.no(selectIdx).cratUsrId_GD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_G2, scrnMsg.G.no(selectIdx).xxPsnNm_G4);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskDescTxt_GH, scrnMsg.G.no(selectIdx).cltTaskDescTxt_GD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskSubjTxt_GH, scrnMsg.G.no(selectIdx).cltTaskSubjTxt_GD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskRwsdDt_GH, scrnMsg.G.no(selectIdx).cltTaskRwsdDt_GD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskRwedDt_GH, scrnMsg.G.no(selectIdx).cltTaskRwedDt_GD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskCtacNm_GH, scrnMsg.G.no(selectIdx).cltTaskCtacNm_GD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskCtacPhoNum_GH, scrnMsg.G.no(selectIdx).cltTaskCtacPhoNum_GD);
        // START 2018/07/25 [QC#25767, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.updUsrId_GH, scrnMsg.G.no(selectIdx).updUsrId_GD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_G5, scrnMsg.G.no(selectIdx).xxPsnNm_G6);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskUpdDt_GH, scrnMsg.G.no(selectIdx).cltTaskUpdDt_GD);
        // END  2018/07/25 [QC#25767, ADD]

        // START 2016/06/16 K.Kojima [QC#10200,MOD]
        // scrnMsg.xxYesNoCd_GH.setValue("0");
        // scrnMsg.cltTaskPk_GH.setInputProtected(true);
        // scrnMsg.cltTaskStsCd_GH.setInputProtected(false);
        // scrnMsg.cltTaskTpCd_GH.setInputProtected(true);
        // scrnMsg.cltTaskPrtyCd_GH.setInputProtected(true);
        // scrnMsg.cltTaskOwnrId_GH.setInputProtected(true);
        // scrnMsg.xxPsnNm_G1.setInputProtected(true);
        // scrnMsg.cratUsrId_GH.setInputProtected(true);
        // scrnMsg.xxPsnNm_G2.setInputProtected(true);
        // scrnMsg.cltTaskDescTxt_GH.setInputProtected(true);
        // scrnMsg.cltTaskSubjTxt_GH.setInputProtected(true);
        // scrnMsg.cltTaskRwsdDt_GH.setInputProtected(true);
        // scrnMsg.cltTaskRwedDt_GH.setInputProtected(true);
        // scrnMsg.cltTaskCtacNm_GH.setInputProtected(true);
        // scrnMsg.cltTaskCtacPhoNum_GH.setInputProtected(true);
        if (scrnMsg.G.no(selectIdx).cltTaskStsCd_GD.getValue().equals(CLT_TASK_STS.OPEN)) {
            scrnMsg.xxYesNoCd_GH.setValue("1");
            // START 2016/06/22 K.Kojima [QC#10529,MOD]
            // NFDL0020CommonLogic.setTaskInputProtectFalse(scrnMsg);
            NFDL0020CommonLogic.setTaskInputProtectFalse(this, scrnMsg);
            // END 2016/06/22 K.Kojima [QC#10529,MOD]
        } else {
            scrnMsg.xxYesNoCd_GH.setValue("0");
            // START 2016/06/22 K.Kojima [QC#10529,MOD]
            // NFDL0020CommonLogic.setTaskInputProtectTrue(scrnMsg);
            NFDL0020CommonLogic.setTaskInputProtectTrue(this, scrnMsg);
            // END 2016/06/22 K.Kojima [QC#10529,MOD]
        }
        // END 2016/06/16 K.Kojima [QC#10200,MOD]
    }
}
