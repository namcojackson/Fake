/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.constant.NSAL0300Constant.DIV_STR;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/26   Hitachi         T.Kanasaka      Update          QC4092
 * 2016/04/07   Hitachi         M.Gotou         Update          QC5312,5313
 * 2018/06/18   Hitachi         K.Kim           Update          QC#25195
 *</pre>
 */
public class NSAL0300Scrn00_ChangeSvcContrBrCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.svcContrBrCd)) {
            scrnMsg.addCheckItem(scrnMsg.svcContrBrCd);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
        // START 2018/06/18 K.Kim [QC#25195, ADD]
        scrnMsg.svcContrBrCd.clear();
        // END 2018/06/18 K.Kim [QC#25195, ADD]
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg  = (NSAL0300CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // add start 2016/04/07 CSA Defect#5312,5313
        this.setResult("no");
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg.getValue())) {
            this.setResult("yes");
            NSAL0300CommonLogic.clearPopupParam(scrnMsg);

            String st = scrnMsg.xxDplyByCdNmCnctTxt.getValue();
            String[] st2 = st.split(DIV_STR, 2);
            String[] para = new String[st2.length];
            for (int i = 0; i < st2.length; i++) {
                para[i] = st2[i];
            }
            String lob = para[0].trim();
            String branchName = "%";
            if (st2.length > 1) {
                para[1] = para[1].trim();
                branchName = para[1] + branchName;
            }
            setValue(scrnMsg.xxPopPrm_01, lob);
            setValue(scrnMsg.svcContrBrDescTxt, branchName);
            scrnMsg.svcContrBrCd.clear();

            Object[] prm = new Object[9];
            prm[0] = scrnMsg.xxPopPrm_00;
            prm[1] = scrnMsg.xxPopPrm_01;
            prm[2] = scrnMsg.svcContrBrCd;
            prm[3] = scrnMsg.svcContrBrDescTxt;
            prm[4] = scrnMsg.xxPopPrm_04;
            prm[5] = scrnMsg.xxPopPrm_05;
            prm[6] = scrnMsg.dsContrDtlPk_P;
            prm[7] = scrnMsg.xxPopPrm_07;
            prm[8] = scrnMsg.svcLineBizCd;
            setArgForSubScreen(prm);
            return;
        }
        // add end 2016/04/07 CSA Defect#5312,5313

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        // mod start 2016/04/07 CSA Defect#5312,5313
        //scrnMsg.addCheckItem(scrnMsg.svcContrBrCd);
        scrnMsg.addCheckItem(scrnMsg.xxDplyByCdNmCnctTxt);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        //scrnMsg.setFocusItem(scrnMsg.contrAdminPsnCd);
        scrnMsg.setFocusItem(scrnMsg.xxPsnNm);
        // mod end 2016/04/07 CSA Defect#5312,5313
    }
}
