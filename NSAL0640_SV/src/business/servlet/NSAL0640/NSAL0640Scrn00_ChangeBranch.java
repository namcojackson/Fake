/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0640;

import static business.servlet.NSAL0640.common.NSAL0640CommonLogic.addCheckItem;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.BUSINESS_ID;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.NSAL0420_PRM_LENGTH;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.DIV_STR;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.SELECT_BRANCH_DTL;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.SELECT_BRANCH_HEAD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0640.NSAL0640CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/31   Hitachi         M.Gotou         Create          N/A
 *</pre>
 */
public class NSAL0640Scrn00_ChangeBranch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;
        int index = getButtonSelectNumber();
        scrnMsg.xxRowNum.setValue(index);
        if (index >= 0) {
            scrnMsg.xxScrEventNm.setValue(SELECT_BRANCH_DTL);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).xxGenlFldAreaTxt_A2);
        } else {
            scrnMsg.xxScrEventNm.setValue(SELECT_BRANCH_HEAD);
            scrnMsg.addCheckItem(scrnMsg.xxDplyByCdNmCnctTxt_H);
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;

        NSAL0640CMsg bizMsg = new NSAL0640CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;
        NSAL0640CMsg bizMsg  = (NSAL0640CMsg) cMsg;

        this.setResult("no");
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg.getValue())) {
            this.setResult("yes");
            scrnMsg.svcRgNm_P.clear();
            scrnMsg.svcLineBizDescTxt_P.clear();
            scrnMsg.svcContrBrCd_P.clear();
            scrnMsg.svcContrBrDescTxt_P.clear();
            scrnMsg.xxGenlFldAreaTxt_P.clear();
            scrnMsg.orgFuncRoleTpNm_P.clear();
            scrnMsg.svcRgPk_P.clear();
            scrnMsg.psnCd_P.clear();
            scrnMsg.svcLineBizCd_P.clear();

            String st = "";
            int index = getButtonSelectNumber();
            if (index >= 0) {
                st = scrnMsg.A.no(index).xxGenlFldAreaTxt_A2.getValue();
            } else {
                st = scrnMsg.xxDplyByCdNmCnctTxt_H.getValue();
            }
            // mod start 2016/04/07 CSA Defect#4916
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
            // mod end 2016/04/07 CSA Defect#4916

            Object[] prm = new Object[NSAL0420_PRM_LENGTH];
            int i = 0;
            prm[i++] = scrnMsg.svcRgNm_P;
            setValue(scrnMsg.svcLineBizCd_P, lob);
            prm[i++] = scrnMsg.svcLineBizCd_P;
            prm[i++] = scrnMsg.svcContrBrCd_P;
            setValue(scrnMsg.svcContrBrDescTxt_P, branchName);
            prm[i++] = scrnMsg.svcContrBrDescTxt_P;
            prm[i++] = scrnMsg.xxGenlFldAreaTxt_P;
            prm[i++] = scrnMsg.orgFuncRoleTpNm_P;
            prm[i++] = scrnMsg.svcRgPk_P;
            prm[i++] = scrnMsg.psnCd_P;
            prm[i++] = scrnMsg.svcLineBizCd_P;
            setArgForSubScreen(prm);

            return;
        }
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            throw new EZDFieldErrorException();
        }
    }
}
