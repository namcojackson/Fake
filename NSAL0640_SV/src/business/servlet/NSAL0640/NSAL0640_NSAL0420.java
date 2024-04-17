/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0640;

import static business.servlet.NSAL0640.common.NSAL0640CommonLogic.addCheckItem;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.DIV_STR;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.SELECT_BRANCH_HEAD;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.SELECT_BRANCH_DTL;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Update Contract Branch/Representative
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4702,4703,4915
 * 2016/03/31   Hitachi         M.Gotou         Update          QC#4916
 *</pre>
 */
public class NSAL0640_NSAL0420 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
     // del 2016/04/04 CSA Defect#4916
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;

        // add start 2016/04/04 CSA Defect#4916
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if (SELECT_BRANCH_HEAD.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrCd_H, scrnMsg.svcContrBrCd_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrDescTxt_H, scrnMsg.svcContrBrDescTxt_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcLineBizCd_H, scrnMsg.svcLineBizCd_P);

            String brNm = "";
            brNm = brNm.concat(scrnMsg.svcLineBizCd_H.getValue());
            if (hasValue(brNm)) {
                brNm = brNm.concat(DIV_STR);
            }
            brNm = brNm.concat(scrnMsg.svcContrBrDescTxt_H.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyByCdNmCnctTxt_H, brNm);
        } else if (SELECT_BRANCH_DTL.equals(scrEventNm)) {
            int index = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).svcContrBrCd_A2, scrnMsg.svcContrBrCd_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).svcContrBrDescTxt_A2, scrnMsg.svcContrBrDescTxt_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).svcLineBizCd_A2, scrnMsg.svcLineBizCd_P);

            String brNm = "";
            brNm = brNm.concat(scrnMsg.A.no(index).svcLineBizCd_A2.getValue());
            if (hasValue(brNm)) {
                brNm = brNm.concat(DIV_STR);
            }
            brNm = brNm.concat(scrnMsg.A.no(index).svcContrBrDescTxt_A2.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).xxGenlFldAreaTxt_A2, brNm);
        }
        // add end 2016/04/04 CSA Defect#4916
        // del start 2016/04/04 CSA Defect#4916
        //NSAL0640CMsg bizMsg = (NSAL0640CMsg) cMsg;
        //EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // del end 2016/04/04 CSA Defect#4916

        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }
}
